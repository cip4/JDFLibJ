/**
 * The CIP4 Software License, Version 1.0
 *
 * Copyright (c) 2001-2026 The International Cooperation for the Integration of Processes in Prepress, Press and Postpress (CIP4). All rights reserved.
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
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.cip4.jdflib.JDFTestCaseBase;
import org.cip4.jdflib.core.AttributeName;
import org.cip4.jdflib.core.ElementName;
import org.cip4.jdflib.core.JDFDoc;
import org.cip4.jdflib.core.JDFElement;
import org.cip4.jdflib.core.JDFResourceLink.EnumUsage;
import org.cip4.jdflib.core.KElement;
import org.cip4.jdflib.datatypes.JDFAttributeMap;
import org.cip4.jdflib.extensions.IntentHelper;
import org.cip4.jdflib.extensions.IntentHelper.EIntentType;
import org.cip4.jdflib.extensions.ProductHelper;
import org.cip4.jdflib.extensions.XJDFConstants;
import org.cip4.jdflib.extensions.XJDFHelper;
import org.cip4.jdflib.extensions.xjdfwalker.XJDFToJDFConverter;
import org.cip4.jdflib.node.JDFNode;
import org.cip4.jdflib.node.JDFNode.EnumType;
import org.cip4.jdflib.resource.intent.JDFColorIntent;
import org.junit.jupiter.api.Test;

class WalkColorIntentTest extends JDFTestCaseBase
{
	@Test
	void testGetElementNames()
	{
		final WalkColorIntent walker = new WalkColorIntent();
		assertTrue(walker.getElementNames().contains(ElementName.COLORINTENT));
	}

	@Test
	void testMatches()
	{
		final WalkColorIntent walker = new WalkColorIntent();
		final XJDFHelper helper = new XJDFHelper("WalkColorIntentMatches", "p1", null);
		helper.setTypes(EnumType.Product.getName());
		final ProductHelper rootProduct = helper.getCreateRootProduct(0);
		final IntentHelper colorIntentHelper = rootProduct.getCreateIntent(EIntentType.ColorIntent);
		final KElement colorIntent = colorIntentHelper.getCreateResource();
		assertTrue(walker.matches(colorIntent));
	}

	@Test
	void testMatchesFalseForDifferentIntentResource()
	{
		final WalkColorIntent walker = new WalkColorIntent();
		final XJDFHelper helper = new XJDFHelper("WalkColorIntentMatchesFalse", "p1", null);
		helper.setTypes(EnumType.Product.getName());
		final ProductHelper product = helper.getCreateRootProduct(0);
		final KElement proofingIntent = product.getCreateIntent(EIntentType.ContentCheckIntent).getCreateResource();
		assertFalse(walker.matches(proofingIntent));
	}

	@Test
	void testWalkSetsScalarNumColorsWhenFrontEqualsBack()
	{
		final WalkColorIntent walker = new WalkColorIntent();
		walker.setParent(new XJDFToJDFImpl(null));
		final JDFColorIntent source = (JDFColorIntent) new JDFDoc(ElementName.COLORINTENT).getRoot();
		source.setAttribute(AttributeName.NUMCOLORS, "4 4");
		source.setAttribute("ColorsUsed", "Cyan Magenta");
		final JDFColorIntent target = (JDFColorIntent) new JDFDoc(ElementName.COLORINTENT).getRoot();

		walker.walk(source, target);

		assertEquals("4", target.getAttribute(AttributeName.NUMCOLORS));
		assertNull(source.getNonEmpty(AttributeName.NUMCOLORS));
	}

	@Test
	void testWalkCreatesFrontAndBackNumColorsPartitions()
	{
		final WalkColorIntent walker = new WalkColorIntent();
		walker.setParent(new XJDFToJDFImpl(null));
		final JDFColorIntent source = (JDFColorIntent) new JDFDoc(ElementName.COLORINTENT).getRoot();
		source.setAttribute(AttributeName.NUMCOLORS, "4/1");
		source.setAttribute("ColorsUsed", "Spot1 Spot2");
		source.setAttribute("ColorsUsedBack", "Spot3");
		final JDFColorIntent target = (JDFColorIntent) new JDFDoc(ElementName.COLORINTENT).getRoot();

		walker.walk(source, target);

		final JDFColorIntent frontPart = (JDFColorIntent) target.getPartition(new JDFAttributeMap("Side", "Front"), null);
		final JDFColorIntent backPart = (JDFColorIntent) target.getPartition(new JDFAttributeMap("Side", "Back"), null);
		assertNotNull(frontPart);
		assertNotNull(backPart);
		assertEquals("4", frontPart.getAttribute(AttributeName.NUMCOLORS));
		assertEquals("1", backPart.getAttribute(AttributeName.NUMCOLORS));
	}

	@Test
	void testWalkHandlesBackOnlyColorsUsedAndBackAttributes()
	{
		final WalkColorIntent walker = new WalkColorIntent();
		walker.setParent(new XJDFToJDFImpl(null));
		final JDFColorIntent source = (JDFColorIntent) new JDFDoc(ElementName.COLORINTENT).getRoot();
		source.setAttribute("ColorsUsedBack", "SpotBack");
		source.setAttribute("CoatingsBack", "GlossVarnish");
		final JDFColorIntent target = (JDFColorIntent) new JDFDoc(ElementName.COLORINTENT).getRoot();

		walker.walk(source, target);

		final JDFColorIntent backPart = (JDFColorIntent) target.getPartition(new JDFAttributeMap("Side", "Back"), null);
		assertNotNull(backPart);
		assertNotNull(backPart.getElement("ColorsUsedBack"));
		assertNotNull(backPart.getElement("Coatings"));
	}

	@Test
	void testWalkRemovesEmptyFrontColorsUsedAndInvalidNumColors()
	{
		final WalkColorIntent walker = new WalkColorIntent();
		walker.setParent(new XJDFToJDFImpl(null));
		final JDFColorIntent source = (JDFColorIntent) new JDFDoc(ElementName.COLORINTENT).getRoot();
		source.setAttribute("ColorsUsed", "");
		source.setAttribute(AttributeName.NUMCOLORS, "invalid");
		final JDFColorIntent target = (JDFColorIntent) new JDFDoc(ElementName.COLORINTENT).getRoot();

		walker.walk(source, target);

		assertNull(source.getElement("ColorsUsed"));
		assertNull(source.getNonEmpty(AttributeName.NUMCOLORS));
	}

	@Test
	void testRoundTrip()
	{
		final XJDFHelper helper = new XJDFHelper("WalkColorIntent", "p1", null);
		helper.setTypes(EnumType.Product.getName());
		final ProductHelper rootProduct = helper.getCreateRootProduct(0);
		final IntentHelper colorIntentHelper = rootProduct.getCreateIntent(EIntentType.ColorIntent);
		final KElement colorIntent = colorIntentHelper.getCreateResource();
		colorIntent.setAttribute(AttributeName.NUMCOLORS, "4/1");

		final KElement front = colorIntent.appendElement(XJDFConstants.SurfaceColor);
		front.setAttribute(AttributeName.SURFACE, "Front");
		front.setAttribute("ColorsUsed", "Spot1 Spot2");
		front.setAttribute("Coatings", "DullVarnish");

		final KElement back = colorIntent.appendElement(XJDFConstants.SurfaceColor);
		back.setAttribute(AttributeName.SURFACE, "Back");
		back.setAttribute("ColorsUsed", "Spot3");
		back.setAttribute("Coatings", "GlossVarnish");

		final JDFDoc convertedDoc = new XJDFToJDFConverter(null).convert(helper);
		final JDFElement root = convertedDoc.getJDFRoot();
		assertNotNull(root);
		assertTrue(root instanceof JDFNode);
		final JDFColorIntent converted = (JDFColorIntent) ((JDFNode) root).getResource(ElementName.COLORINTENT, EnumUsage.Input, 0);
		assertNotNull(converted);
		final JDFColorIntent frontPart = (JDFColorIntent) converted.getPartition(new JDFAttributeMap("Side", "Front"), null);
		final JDFColorIntent backPart = (JDFColorIntent) converted.getPartition(new JDFAttributeMap("Side", "Back"), null);
		assertNotNull(frontPart);
		assertNotNull(backPart);
		assertEquals("DullVarnish", frontPart.getCoatings().getActual());
	}
}
