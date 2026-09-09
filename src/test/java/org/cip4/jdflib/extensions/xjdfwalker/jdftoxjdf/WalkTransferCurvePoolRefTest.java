/**
 * The CIP4 Software License, Version 1.0
 *
 * Copyright (c) 2001-2025 The International Cooperation for the Integration of Processes in Prepress, Press and Postpress (CIP4). All rights reserved.
 *
 * Redistribution and use in source and binary forms, with or without modification, are permitted provided that the following conditions are met:
 *
 * 1. Redistributions of source code must retain the above copyright notice, this list of conditions and the following disclaimer.
 *
 * 2. Redistributions in binary form must reproduce the above copyright notice, this list of conditions and the following disclaimer in the documentation and/or other materials provided with the
 * distribution.
 *
 * 3. The end-user documentation included with the redistribution, if any, must include the following acknowledgment: "This product includes software developed by the The International Cooperation for
 * the Integration of Processes in Prepress, Press and Postpress (www.cip4.org)" Alternately, this acknowledgment may appear in the software itself, if and wherever such third-party acknowledgments
 * normally appear.
 *
 * 4. The names "CIP4" and "The International Cooperation for the Integration of Processes in Prepress, Press and Postpress" must not be used to endorse or promote products derived from this software
 * without prior written permission. For written permission, please contact info@cip4.org.
 *
 * 5. Products derived from this software may not be called "CIP4", nor may "CIP4" appear in their name, without prior written permission of the CIP4 organization
 *
 * Usage of this software in commercial products is subject to restrictions. For details please consult info@cip4.org.
 *
 * THIS SOFTWARE IS PROVIDED ``AS IS'' AND ANY EXPRESSED OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO, THE IMPLIED WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE ARE
 * DISCLAIMED. IN NO EVENT SHALL THE INTERNATIONAL COOPERATION FOR THE INTEGRATION OF PROCESSES IN PREPRESS, PRESS AND POSTPRESS OR ITS CONTRIBUTORS BE LIABLE FOR ANY DIRECT, INDIRECT, INCIDENTAL,
 * SPECIAL, EXEMPLARY, OR CONSEQUENTIAL DAMAGES (INCLUDING, BUT NOT LIMITED TO, PROCUREMENT OF SUBSTITUTE GOODS OR SERVICES; LOSS OF USE, DATA, OR PROFITS; OR BUSINESS INTERRUPTION) HOWEVER CAUSED AND
 * ON ANY THEORY OF LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY, OR TORT (INCLUDING NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE OF THIS SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY
 * OF SUCH DAMAGE. ====================================================================
 *
 * This software consists of voluntary contributions made by many individuals on behalf of the The International Cooperation for the Integration of Processes in Prepress, Press and Postpress and was
 * originally based on software copyright (c) 1999-2001, Heidelberger Druckmaschinen AG copyright (c) 1999-2001, Agfa-Gevaert N.V.
 *
 * For more information on The International Cooperation for the Integration of Processes in Prepress, Press and Postpress , please see <http://www.cip4.org/>.
 *
 *
 */
package org.cip4.jdflib.extensions.xjdfwalker.jdftoxjdf;

import java.util.List;

import org.cip4.jdflib.JDFTestCaseBase;
import org.cip4.jdflib.core.ElementName;
import org.cip4.jdflib.core.JDFDoc;
import org.cip4.jdflib.core.JDFElement;
import org.cip4.jdflib.core.JDFElement.EnumValidationLevel;
import org.cip4.jdflib.core.JDFRefElement;
import org.cip4.jdflib.core.JDFResourceLink.EnumUsage;
import org.cip4.jdflib.core.KElement;
import org.cip4.jdflib.extensions.ResourceHelper;
import org.cip4.jdflib.extensions.SetHelper;
import org.cip4.jdflib.extensions.XJDFHelper;
import org.cip4.jdflib.node.JDFNode;
import org.cip4.jdflib.node.JDFNode.EnumType;
import org.cip4.jdflib.resource.process.JDFLayout;
import org.cip4.jdflib.resource.process.JDFTransferCurvePool;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class WalkTransferCurvePoolRefTest extends JDFTestCaseBase
{
	@Test
	void testGetElementNames()
	{
		final WalkTransferCurvePoolRef w = new WalkTransferCurvePoolRef();
		Assertions.assertTrue(w.getElementNames().contains("TransferCurvePoolRef"));
	}

	@Test
	void testWalk()
	{
		final JDFNode n = JDFNode.createRoot();
		n.setType(EnumType.Stripping);
		final JDFLayout layout = (JDFLayout) n.addResource(ElementName.LAYOUT, EnumUsage.Input);
		final JDFTransferCurvePool tcp = (JDFTransferCurvePool) n.addResource(ElementName.TRANSFERCURVEPOOL, EnumUsage.Input);
		final JDFRefElement ref = layout.refElement(tcp);

		final JDFToXJDF conv = new JDFToXJDF();
		conv.oldRoot = n;
		conv.newRoot = new JDFDoc("XJDF").getRoot();
		final WalkTransferCurvePoolRef w = new WalkTransferCurvePoolRef();
		w.setParent(conv);

		Assertions.assertTrue(w.matches(ref));
		final KElement converted = w.walk(ref, conv.newRoot);

		Assertions.assertNull(converted);
		Assertions.assertNull(n.getResource(ElementName.TRANSFERCURVEPOOL, EnumUsage.Input, 0));
	}

	@Test
	void testMatchesRetainAll()
	{
		final JDFNode n = JDFNode.createRoot();
		n.setType(EnumType.Stripping);
		final JDFLayout layout = (JDFLayout) n.addResource(ElementName.LAYOUT, EnumUsage.Input);
		final JDFTransferCurvePool tcp = (JDFTransferCurvePool) n.addResource(ElementName.TRANSFERCURVEPOOL, EnumUsage.Input);
		final JDFRefElement ref = layout.refElement(tcp);

		final JDFToXJDF conv = new JDFToXJDF();
		conv.setRetainAll(true);
		conv.newRoot = new JDFDoc("XJDF").getRoot();
		final WalkTransferCurvePoolRef w = new WalkTransferCurvePoolRef();
		w.setParent(conv);

		Assertions.assertFalse(w.matches(ref));
	}

	@Test
	void testWalkNoTarget()
	{
		final JDFRefElement ref = (JDFRefElement) new JDFDoc("TransferCurvePoolRef").getRoot();

		final JDFToXJDF conv = new JDFToXJDF();
		conv.newRoot = new JDFDoc("XJDF").getRoot();
		final WalkTransferCurvePoolRef w = new WalkTransferCurvePoolRef();
		w.setParent(conv);

		Assertions.assertNull(w.walk(ref, conv.newRoot));
	}

	@Test
	void testMakeRefAttribute()
	{
		final JDFNode n = JDFNode.createRoot();
		n.setType(EnumType.Stripping);
		final JDFLayout layout = (JDFLayout) n.addResource(ElementName.LAYOUT, EnumUsage.Input);
		final JDFTransferCurvePool tcp = (JDFTransferCurvePool) n.addResource(ElementName.TRANSFERCURVEPOOL, EnumUsage.Input);
		final JDFRefElement ref = layout.refElement(tcp);

		final JDFToXJDF conv = new JDFToXJDF();
		conv.newRoot = new JDFDoc("XJDF").getRoot();
		final WalkTransferCurvePoolRef w = new WalkTransferCurvePoolRef();
		w.setParent(conv);

		final KElement xjdf = new JDFDoc("ConventionalPrintingParams").getRoot();
		w.makeRefAttribute(ref, xjdf);

		Assertions.assertTrue(xjdf.hasAttribute("TransferCurvePoolRef"));
		final String id = xjdf.getAttribute("TransferCurvePoolRef");
		Assertions.assertNotNull(conv.newRoot.getXPathElement("*[@ID='" + id + "']"));
	}

	@Test
	void testSetResourceSetsUsage()
	{
		final JDFNode n = JDFNode.createRoot();
		n.setType(EnumType.Stripping);
		final JDFLayout layout = (JDFLayout) n.addResource(ElementName.LAYOUT, EnumUsage.Input);
		final JDFTransferCurvePool tcp = (JDFTransferCurvePool) n.addResource(ElementName.TRANSFERCURVEPOOL, EnumUsage.Input);
		final JDFRefElement ref = layout.refElement(tcp);

		final JDFToXJDF conv = new JDFToXJDF();
		conv.newRoot = new JDFDoc("XJDF").getRoot();
		final WalkTransferCurvePoolRef w = new WalkTransferCurvePoolRef();
		w.setParent(conv);

		final List<KElement> resources = w.setResource(ref, tcp, conv.newRoot);
		Assertions.assertNotNull(resources);
		Assertions.assertFalse(resources.isEmpty());

		final SetHelper setHelper = new ResourceHelper(resources.get(0)).getSet();
		Assertions.assertNotNull(setHelper);
		Assertions.assertEquals(EnumUsage.Input, setHelper.getUsage());
	}

	@Test
	void testRoundTrip()
	{
		final XJDFHelper h = new XJDFHelper("WalkTransferCurvePoolRef", null);
		h.setTypes(EnumType.ConventionalPrinting.getName());
		final SetHelper outSet = h.getCreateSet(ElementName.COMPONENT, EnumUsage.Output);
		outSet.getCreatePartition(0, true).setAmount(1, null, true);
		final JDFElement jdf = writeRoundTripX(h, "walktransfercurvepoolref", EnumValidationLevel.Incomplete, true);
		Assertions.assertNotNull(jdf);
	}
}
