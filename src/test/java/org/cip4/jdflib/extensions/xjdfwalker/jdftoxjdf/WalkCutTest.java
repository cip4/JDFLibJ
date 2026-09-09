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

import org.cip4.jdflib.JDFTestCaseBase;
import org.cip4.jdflib.core.AttributeName;
import org.cip4.jdflib.core.ElementName;
import org.cip4.jdflib.core.JDFDoc;
import org.cip4.jdflib.core.JDFElement;
import org.cip4.jdflib.core.KElement;
import org.cip4.jdflib.core.JDFElement.EnumValidationLevel;
import org.cip4.jdflib.core.JDFElement.EnumVersion;
import org.cip4.jdflib.core.JDFResourceLink.EnumUsage;
import org.cip4.jdflib.datatypes.JDFXYPair;
import org.cip4.jdflib.node.JDFNode;
import org.cip4.jdflib.node.JDFNode.EnumProcessUsage;
import org.cip4.jdflib.node.JDFNode.EnumType;
import org.cip4.jdflib.resource.JDFCuttingParams;
import org.cip4.jdflib.resource.process.postpress.JDFCut;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class WalkCutTest extends JDFTestCaseBase
{
	@Test
	void testGetElementNames()
	{
		final WalkCut walkCut = new WalkCut();
		Assertions.assertTrue(walkCut.getElementNames().contains(ElementName.CUT));
	}

	@Test
	void testWalk()
	{
		final KElement cut = new JDFDoc(ElementName.CUT).getRoot();
		cut.setAttribute(AttributeName.LOWERRIBBONNAME, "low");
		cut.setAttribute(AttributeName.RELATIVESTARTPOSITION, "5");
		cut.setAttribute(AttributeName.RELATIVEWORKINGPATH, "2");
		cut.setAttribute(AttributeName.UPPERRIBBONNAME, "up");

		final WalkCut walkCut = new WalkCut();
		walkCut.setParent(new JDFToXJDF());
		Assertions.assertTrue(walkCut.matches(cut));

		final KElement target = new JDFDoc(ElementName.RESOURCE).getRoot();
		final KElement converted = walkCut.walk(cut, target);

		Assertions.assertNotNull(converted);
		Assertions.assertNull(converted.getNonEmpty(AttributeName.LOWERRIBBONNAME));
		Assertions.assertNull(converted.getNonEmpty(AttributeName.RELATIVESTARTPOSITION));
		Assertions.assertNull(converted.getNonEmpty(AttributeName.RELATIVEWORKINGPATH));
		Assertions.assertNull(converted.getNonEmpty(AttributeName.UPPERRIBBONNAME));
	}

	@Test
	void testRoundTrip()
	{
		final JDFElement root = runRoundTrip("walkcutj3");
		Assertions.assertNotNull(root);
		Assertions.assertTrue(root.isValid(EnumValidationLevel.Incomplete));
	}

	private JDFElement runRoundTrip(final String fileBase)
	{
		final JDFNode node = new JDFDoc(ElementName.JDF).getJDFRoot();
		node.setType(EnumType.Cutting);
		node.setVersion(EnumVersion.Version_1_5);
		final JDFElement inputComponent = node.appendMatchingResource(ElementName.COMPONENT, EnumProcessUsage.AnyInput, null);
		inputComponent.setAttribute(AttributeName.COMPONENTTYPE, "PartialProduct");
		final JDFElement outputComponent = node.appendMatchingResource(ElementName.COMPONENT, EnumProcessUsage.AnyOutput, null);
		outputComponent.setAttribute(AttributeName.COMPONENTTYPE, "FinalProduct");
		final JDFCuttingParams cuttingParams = (JDFCuttingParams) node.addResource(ElementName.CUTTINGPARAMS, EnumUsage.Input);
		final JDFCut cut = cuttingParams.getCreateCut();
		cut.setLowerRibbonName("low");
		cut.setRelativeStartPosition(new JDFXYPair(5, 0));
		cut.setRelativeWorkingPath(new JDFXYPair(2, 0));
		cut.setUpperRibbonName("up");

		return writeRoundTrip(node, fileBase, getDefaultXJDFVersion(), EnumValidationLevel.Incomplete).b;
	}
}
