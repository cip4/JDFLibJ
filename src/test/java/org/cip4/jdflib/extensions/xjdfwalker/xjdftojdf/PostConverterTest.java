/**
 * The CIP4 Software License, Version 1.0
 *
 * Copyright (c) 2001-2023 The International Cooperation for the Integration of
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
package org.cip4.jdflib.extensions.xjdfwalker.xjdftojdf;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;

import org.cip4.jdflib.JDFTestCaseBase;
import org.cip4.jdflib.core.ElementName;
import org.cip4.jdflib.core.JDFDoc;
import org.cip4.jdflib.core.JDFResourceLink.EnumUsage;
import org.cip4.jdflib.core.VString;
import org.cip4.jdflib.extensions.XJDFHelper;
import org.cip4.jdflib.extensions.xjdfwalker.XJDFToJDFConverter;
import org.cip4.jdflib.extensions.xjdfwalker.xjdftojdf.PostConverter.LinkAmountCleaner;
import org.cip4.jdflib.node.JDFNode;
import org.cip4.jdflib.node.JDFNode.EnumType;
import org.cip4.jdflib.resource.JDFResource;
import org.cip4.jdflib.resource.JDFStrippingParams;
import org.cip4.jdflib.resource.process.JDFBinderySignature;
import org.cip4.jdflib.resource.process.JDFColorPool;
import org.cip4.jdflib.resource.process.JDFColorantControl;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

class PostConverterTest extends JDFTestCaseBase
{

	/**
	*
	*
	*/
	@Test
	@Disabled
	void testColorantControlSpace()
	{
		final JDFNode n = new JDFDoc(ElementName.JDF).getJDFRoot();
		n.setType(EnumType.ImageSetting);
		final JDFColorPool cp = (JDFColorPool) n.addResource(ElementName.COLORPOOL, EnumUsage.Input);
		cp.appendColorWithName("sep_1", null).setActualColorName("sep 1");
		cp.appendColorWithName("sep_2", null).setActualColorName("sep 2");

		final JDFColorantControl cc = (JDFColorantControl) n.addResource(ElementName.COLORANTCONTROL, EnumUsage.Input);
		cc.appendColorantParams().setSeparations(new VString("sep_1,sep_2", ","));

		final PostConverter pc = new PostConverter(null, n);
		pc.new ResourceCleaner().cleanResources();

		assertEquals("sep 1", cc.getColorantParams().getSeparation(0));
		assertNotNull(cp.getColorWithName("sep 1"));
		assertNull(cp.getColorWithName("sep_1"));
	}

	/**
	*
	*
	*/
	@Test
	void testBinderySignature()
	{
		final XJDFToJDFConverter xCon = new XJDFToJDFConverter(null);
		final XJDFHelper xjdf = XJDFHelper.parseFile(sm_dirTestData + "xjdf/tnr.strip.red.xjdf");
		final JDFDoc jdf = xCon.convert(xjdf);
		jdf.write2File(sm_dirTestDataTemp + "tnr.strip.red.jdf", 2, false);
		final JDFNode root = jdf.getJDFRoot();
		JDFResource bs = root.getResource(ElementName.BINDERYSIGNATURE, EnumUsage.Input, 0);
		assertNull(bs);
		final JDFStrippingParams sp = (JDFStrippingParams) root.getResource(ElementName.STRIPPINGPARAMS, EnumUsage.Input, 0).getLeaf(0);
		assertNotNull(sp.getBinderySignature());
		final PostConverter pc = new PostConverter(null, null);
		LinkAmountCleaner lac = pc.new LinkAmountCleaner();
		bs = (JDFResource) root.getResourcePool().getElement(ElementName.BINDERYSIGNATURE);
		lac.findLeaf((JDFBinderySignature) bs, sp);
	}

	/**
	*
	*
	*/
	@Test
	void testBinderySignature2()
	{
		final XJDFToJDFConverter xCon = new XJDFToJDFConverter(null);
		final XJDFHelper xjdf = XJDFHelper.parseFile(sm_dirTestData + "xjdf/tnr.strip.red.xjdf");
		final JDFDoc jdf = xCon.convert(xjdf);
		jdf.write2File(sm_dirTestDataTemp + "tnr.strip.red.jdf", 2, false);
		final JDFNode root = jdf.getJDFRoot();
		JDFResource bs = root.getResource(ElementName.BINDERYSIGNATURE, EnumUsage.Input, 0);
		assertNull(bs);
		final JDFStrippingParams sp = (JDFStrippingParams) root.getResource(ElementName.STRIPPINGPARAMS, EnumUsage.Input, 0).getLeaf(0);
		sp.removeChild("BinderySignatureRef", null, 0);
		final PostConverter pc = new PostConverter(null, null);
		LinkAmountCleaner lac = pc.new LinkAmountCleaner();
		bs = (JDFResource) root.getResourcePool().getElement(ElementName.BINDERYSIGNATURE);
		lac.cleanSingleStripping(bs, sp);
	}
}
