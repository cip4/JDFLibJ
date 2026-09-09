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
package org.cip4.jdflib.extensions.xjdfwalker.xjdftojdf;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.cip4.jdflib.JDFTestCaseBase;
import org.cip4.jdflib.core.AttributeName;
import org.cip4.jdflib.core.ElementName;
import org.cip4.jdflib.core.JDFDoc;
import org.cip4.jdflib.core.JDFElement;
import org.cip4.jdflib.core.JDFResourceLink.EnumUsage;
import org.cip4.jdflib.extensions.PartitionHelper;
import org.cip4.jdflib.extensions.ResourceHelper;
import org.cip4.jdflib.extensions.SetHelper;
import org.cip4.jdflib.extensions.XJDFHelper;
import org.cip4.jdflib.extensions.xjdfwalker.XJDFToJDFConverter;
import org.cip4.jdflib.node.JDFNode;
import org.cip4.jdflib.resource.process.JDFLayoutElementProductionParams;
import org.junit.jupiter.api.Test;

class WalkLayoutElementProductionParamsTest extends JDFTestCaseBase
{
	@Test
	void testGetElementNamesAndMatches()
	{
		final WalkLayoutElementProductionParams walker = new WalkLayoutElementProductionParams();
		final JDFLayoutElementProductionParams params = (JDFLayoutElementProductionParams) new org.cip4.jdflib.core.JDFDoc(ElementName.LAYOUTELEMENTPRODUCTIONPARAMS).getRoot();
		assertTrue(walker.getElementNames().contains(ElementName.LAYOUTELEMENTPRODUCTIONPARAMS));
		assertTrue(walker.matches(params));
	}

	@Test
	void testRoundTrip()
	{
		final XJDFHelper helper = new XJDFHelper("walklayoutelementproductionparams", null, null);
		helper.setTypes("Stripping");
		final SetHelper set = helper.getCreateSet(ElementName.LAYOUTELEMENTPRODUCTIONPARAMS, EnumUsage.Input, null);
		final ResourceHelper resourceHelper = set.appendPartition(AttributeName.SHEETNAME, "S1", true);
		final PartitionHelper partitionHelper = new PartitionHelper(resourceHelper.getRoot());
		final JDFLayoutElementProductionParams params = (JDFLayoutElementProductionParams) partitionHelper.getCreateResource();
		params.setDescriptiveName("layout-element-production-params");

		helper.cleanUp();
		final JDFDoc convertedDoc = new XJDFToJDFConverter(null).convert(helper.getRoot());
		final JDFElement converted = convertedDoc.getJDFRoot();
		assertNotNull(converted);
		assertTrue(converted instanceof JDFNode);
		final JDFNode node = (JDFNode) converted;
		assertNotNull(node.getResource(ElementName.LAYOUTELEMENTPRODUCTIONPARAMS, EnumUsage.Input, 0));
		assertEquals(ElementName.JDF, node.getLocalName());
	}
}
