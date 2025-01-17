/*
 * The CIP4 Software License, Version 1.0
 *
 *
 * Copyright (c) 2001-2025 The International Cooperation for the Integration of
 * Processes in  Prepress, Press and Postpress (CIP4).  All rights
 * reserved.
 *
 * Redistribution and use in source and binary forms, with or without
 * modification, are permitted provided that the following conditions
 * are met:
 *
 * 1. Redistributions of source code must retain the above copyright
 *    notice, this list of conditions and the following disclaimer.
 *
 * 2. Redistributions in binary form must reproduce the above copyright
 *    notice, this list of conditions and the following disclaimer in
 *    the documentation and/or other materials provided with the
 *    distribution.
 *
 * 3. The end-user documentation included with the redistribution,
 *    if any, must include the following acknowledgment:
 *       "This product includes software developed by the
 *        The International Cooperation for the Integration of
 *        Processes in  Prepress, Press and Postpress (www.cip4.org)"
 *    Alternately, this acknowledgment may appear in the software itself,
 *    if and wherever such third-party acknowledgments normally appear.
 *
 * 4. The names "CIP4" and "The International Cooperation for the Integration of
 *    Processes in  Prepress, Press and Postpress" must
 *    not be used to endorse or promote products derived from this
 *    software without prior written permission. For written
 *    permission, please contact info@cip4.org.
 *
 * 5. Products derived from this software may not be called "CIP4",
 *    nor may "CIP4" appear in their name, without prior written
 *    permission of the CIP4 organization
 *
 * Usage of this software in commercial products is subject to restrictions. For
 * details please consult info@cip4.org.
 *
 * THIS SOFTWARE IS PROVIDED ``AS IS'' AND ANY EXPRESSED OR IMPLIED
 * WARRANTIES, INCLUDING, BUT NOT LIMITED TO, THE IMPLIED WARRANTIES
 * OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE ARE
 * DISCLAIMED.  IN NO EVENT SHALL THE INTERNATIONAL COOPERATION FOR
 * THE INTEGRATION OF PROCESSES IN PREPRESS, PRESS AND POSTPRESS OR
 * ITS CONTRIBUTORS BE LIABLE FOR ANY DIRECT, INDIRECT, INCIDENTAL,
 * SPECIAL, EXEMPLARY, OR CONSEQUENTIAL DAMAGES (INCLUDING, BUT NOT
 * LIMITED TO, PROCUREMENT OF SUBSTITUTE GOODS OR SERVICES; LOSS OF
 * USE, DATA, OR PROFITS; OR BUSINESS INTERRUPTION) HOWEVER CAUSED AND
 * ON ANY THEORY OF LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY,
 * OR TORT (INCLUDING NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT
 * OF THE USE OF THIS SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY OF
 * SUCH DAMAGE.
 * ====================================================================
 *
 * This software consists of voluntary contributions made by many
 * individuals on behalf of the The International Cooperation for the Integration
 * of Processes in Prepress, Press and Postpress and was
 * originally based on software
 * copyright (c) 1999-2001, Heidelberger Druckmaschinen AG
 * copyright (c) 1999-2001, Agfa-Gevaert N.V.
 *
 * For more information on The International Cooperation for the
 * Integration of Processes in  Prepress, Press and Postpress , please see
 * <http://www.cip4.org/>.
 *
 *
 */
package org.cip4.jdflib.examples;

import org.cip4.jdflib.auto.JDFAutoAssembly.EnumOrder;
import org.cip4.jdflib.auto.JDFAutoBinderySignature.EnumBinderySignatureType;
import org.cip4.jdflib.core.ElementName;
import org.cip4.jdflib.core.JDFResourceLink.EnumUsage;
import org.cip4.jdflib.core.VString;
import org.cip4.jdflib.datatypes.JDFRectangle;
import org.cip4.jdflib.extensions.examples.ExampleTest;
import org.cip4.jdflib.node.JDFNode;
import org.cip4.jdflib.node.JDFNode.EnumType;
import org.cip4.jdflib.resource.JDFResource.EnumPartIDKey;
import org.cip4.jdflib.resource.JDFStrippingParams;
import org.cip4.jdflib.resource.process.JDFAssembly;
import org.cip4.jdflib.resource.process.JDFBinderySignature;
import org.cip4.jdflib.resource.process.JDFDieLayout;
import org.cip4.jdflib.resource.process.JDFLayout;
import org.cip4.jdflib.resource.process.JDFPosition;
import org.cip4.jdflib.resource.process.JDFStation;
import org.junit.jupiter.api.Test;

class StrippingExampleTest extends ExampleTest
{
	@Test
	void testMultiDieLayout()
	{
		final JDFNode n = JDFNode.createRoot();
		n.setType(EnumType.Stripping);
		final JDFStrippingParams sp = (JDFStrippingParams) n.addResource(ElementName.STRIPPINGPARAMS, EnumUsage.Input);
		final JDFAssembly as = (JDFAssembly) n.addResource(ElementName.ASSEMBLY, EnumUsage.Input);
		as.setAssemblyIDs(new VString("a1 a2 b1 b2"));
		as.setOrder(EnumOrder.None);

		final JDFPosition p1 = sp.appendPosition();
		p1.setRelativeBox(new JDFRectangle(0, 0, 0.5, 1));

		final JDFPosition p2 = sp.appendPosition();
		p2.setRelativeBox(new JDFRectangle(0.5, 0, 1, 1));

		final JDFBinderySignature bs = sp.appendBinderySignature();
		final JDFDieLayout dlo = bs.appendDieLayout();
		final JDFStation s1 = dlo.appendStation();
		s1.setAssemblyIDs(new VString("a1 b1"));
		s1.setStationName("S1");
		final JDFStation s2 = dlo.appendStation();
		s2.setAssemblyIDs(new VString("a2 b2"));
		s2.setStationName("S2");

		final JDFLayout lo = (JDFLayout) n.addResource(ElementName.LAYOUT, EnumUsage.Output);
		lo.setDescriptiveName("dummy layout");
		writeRoundTrip(n, "MultiDieLayout");

	}

	@Test
	void testMultiPosition()
	{
		final JDFNode n = JDFNode.createRoot();
		n.setType(EnumType.Stripping);
		final JDFStrippingParams sp = (JDFStrippingParams) n.addResource(ElementName.STRIPPINGPARAMS, EnumUsage.Input);
		final JDFStrippingParams spLeaf = (JDFStrippingParams) sp.getCreatePartition(EnumPartIDKey.SignatureName, "Sig1", null).getCreatePartition(EnumPartIDKey.SheetName,
				"Sheet1", null);
		final JDFAssembly as = (JDFAssembly) n.addResource(ElementName.ASSEMBLY, EnumUsage.Input);
		as.setAssemblyIDs(new VString("BS1"));
		as.setOrder(EnumOrder.Collecting);

		final JDFPosition p1 = spLeaf.appendPosition();
		p1.setRelativeBox(new JDFRectangle(0, 0, 0.5, 1));

		final JDFPosition p2 = spLeaf.appendPosition();
		p2.setRelativeBox(new JDFRectangle(0.5, 0, 1, 1));

		spLeaf.setAssemblyIDs(new VString("BS1"));

		final JDFBinderySignature bs = spLeaf.appendBinderySignature();
		bs.setBinderySignatureType(EnumBinderySignatureType.Fold);
		bs.setFoldCatalog(8, 1);
		bs.makeRootResource();

		final JDFLayout lo = (JDFLayout) n.addResource(ElementName.LAYOUT, EnumUsage.Output);
		lo.setDescriptiveName("dummy layout");
		writeRoundTrip(n, "MultiPosition");
	}

	@Test
	void testMultiSection()
	{
		final JDFNode n = JDFNode.createRoot();
		n.setType(EnumType.Stripping);
		final JDFStrippingParams sp = (JDFStrippingParams) n.addResource(ElementName.STRIPPINGPARAMS, EnumUsage.Input);
		final JDFStrippingParams spLeaf = (JDFStrippingParams) sp.getCreatePartition(EnumPartIDKey.SignatureName, "Sig1", null).getCreatePartition(EnumPartIDKey.SheetName,
				"Sheet1", null);
		final JDFAssembly as = (JDFAssembly) n.addResource(ElementName.ASSEMBLY, EnumUsage.Input);
		as.setAssemblyIDs(new VString("BS1 BS2"));
		as.setOrder(EnumOrder.Collecting);

		final JDFStrippingParams spLeaf1 = (JDFStrippingParams) spLeaf.getCreatePartition(EnumPartIDKey.BinderySignatureName, "BS1", null);
		final JDFPosition p1 = spLeaf1.appendPosition();
		p1.setRelativeBox(new JDFRectangle(0, 0, 0.5, 1));
		spLeaf1.setAssemblyIDs(new VString("BS1"));

		final JDFStrippingParams spLeaf2 = (JDFStrippingParams) spLeaf.getCreatePartition(EnumPartIDKey.BinderySignatureName, "BS1", null);
		final JDFPosition p2 = spLeaf2.appendPosition();
		p2.setRelativeBox(new JDFRectangle(0.5, 0, 1, 1));
		spLeaf2.setAssemblyIDs(new VString("BS2"));

		JDFBinderySignature bs = spLeaf.appendBinderySignature();
		bs.setBinderySignatureType(EnumBinderySignatureType.Fold);
		bs.setFoldCatalog(8, 1);
		bs = (JDFBinderySignature) bs.makeRootResource();

		final JDFLayout lo = (JDFLayout) n.addResource(ElementName.LAYOUT, EnumUsage.Output);
		lo.setDescriptiveName("dummy layout");
		writeRoundTrip(n, "MultiSection");
	}
}
