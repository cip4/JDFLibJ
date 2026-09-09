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
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.cip4.jdflib.JDFTestCaseBase;
import org.cip4.jdflib.core.AttributeName;
import org.cip4.jdflib.core.ElementName;
import org.cip4.jdflib.core.JDFDoc;
import org.cip4.jdflib.core.JDFElement;
import org.cip4.jdflib.core.JDFResourceLink.EnumUsage;
import org.cip4.jdflib.core.KElement;
import org.cip4.jdflib.extensions.IntentHelper;
import org.cip4.jdflib.extensions.IntentHelper.EIntentType;
import org.cip4.jdflib.extensions.ProductHelper;
import org.cip4.jdflib.extensions.XJDFConstants;
import org.cip4.jdflib.extensions.XJDFHelper;
import org.cip4.jdflib.extensions.xjdfwalker.XJDFToJDFConverter;
import org.cip4.jdflib.node.JDFNode;
import org.cip4.jdflib.node.JDFNode.EnumType;
import org.junit.jupiter.api.Test;

class WalkLooseBindingTest extends JDFTestCaseBase
{
	@Test
	void testGetElementNames()
	{
		final WalkLooseBinding walker = new WalkLooseBinding();
		assertTrue(walker.getElementNames().contains(XJDFConstants.LooseBinding));
	}

	@Test
	void testWalkWithoutBindingType()
	{
		final WalkLooseBinding walker = new WalkLooseBinding();
		walker.setParent(new XJDFToJDFImpl(null));
		final KElement looseBinding = new org.cip4.jdflib.core.JDFDoc(XJDFConstants.LooseBinding).getRoot();
		final KElement parent = new org.cip4.jdflib.core.JDFDoc(ElementName.BINDINGINTENT).getRoot();
		assertNull(walker.walk(looseBinding, parent));
	}

	@Test
	void testMatchesTrueAndFalse()
	{
		final WalkLooseBinding walker = new WalkLooseBinding();
		assertTrue(walker.matches(new JDFDoc(XJDFConstants.LooseBinding).getRoot()));
		assertFalse(walker.matches(new JDFDoc(ElementName.BINDINGINTENT).getRoot()));
	}

	@Test
	void testWalkRenamesBrandForChannelBinding()
	{
		final WalkLooseBinding walker = new WalkLooseBinding();
		walker.setParent(new XJDFToJDFImpl(null));
		final KElement parent = new JDFDoc(ElementName.BINDINGINTENT).getRoot();
		parent.setAttribute(ElementName.BINDINGTYPE, ElementName.CHANNELBINDING);
		final KElement looseBinding = parent.appendElement(XJDFConstants.LooseBinding);
		looseBinding.setAttribute(ElementName.BINDINGTYPE, ElementName.CHANNELBINDING);
		looseBinding.setAttribute(AttributeName.BRAND, "BrandA");
		final KElement ret = walker.walk(looseBinding, parent);
		assertNotNull(ret);
		assertEquals(ElementName.CHANNELBINDING, ret.getLocalName());
		assertNull(ret.getNonEmpty(AttributeName.BRAND));
		assertEquals("BrandA", ret.getAttribute(ElementName.CHANNELBRAND));
	}

	@Test
	void testRoundTrip()
	{
		final XJDFHelper helper = new XJDFHelper("WalkLooseBinding", "p1", null);
		helper.setTypes(EnumType.Product.getName());
		final ProductHelper product = helper.getCreateRootProduct(0);
		final IntentHelper bindingIntent = product.getCreateIntent(EIntentType.BindingIntent);
		final KElement bindingIntentResource = bindingIntent.getCreateResource();
		bindingIntentResource.setAttribute("BindingType", ElementName.RINGBINDING);
		final KElement looseBinding = bindingIntentResource.appendElement(XJDFConstants.LooseBinding);
		looseBinding.setAttribute("BindingType", ElementName.RINGBINDING);

		final JDFDoc convertedDoc = new XJDFToJDFConverter(null).convert(helper);
		final JDFElement root = convertedDoc.getJDFRoot();
		assertNotNull(root);
		assertTrue(root instanceof JDFNode);
		final KElement jdfBindingIntent = ((JDFNode) root).getResource(ElementName.BINDINGINTENT, EnumUsage.Input, 0);
		assertNotNull(jdfBindingIntent);
		assertNull(jdfBindingIntent.getElement(XJDFConstants.LooseBinding));
	}
}
