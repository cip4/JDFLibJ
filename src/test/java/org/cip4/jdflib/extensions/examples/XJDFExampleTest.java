/**
 * The CIP4 Software License, Version 1.0
 *
 * Copyright (c) 2001-2017 The International Cooperation for the Integration of
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
package org.cip4.jdflib.extensions.examples;

import org.cip4.jdflib.auto.JDFAutoMedia.EnumMediaType;
import org.cip4.jdflib.core.AttributeName;
import org.cip4.jdflib.core.ElementName;
import org.cip4.jdflib.core.JDFResourceLink.EnumUsage;
import org.cip4.jdflib.core.KElement;
import org.cip4.jdflib.datatypes.JDFAttributeMap;
import org.cip4.jdflib.extensions.IntentHelper;
import org.cip4.jdflib.extensions.ProductHelper;
import org.cip4.jdflib.extensions.ResourceHelper;
import org.cip4.jdflib.extensions.SetHelper;
import org.cip4.jdflib.extensions.XJDFConstants;
import org.cip4.jdflib.extensions.XJDFHelper;
import org.cip4.jdflib.node.JDFNode.EnumType;
import org.cip4.jdflib.resource.process.JDFMedia;
import org.cip4.jdflib.util.JDFDate;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Assertions;
import org.w3c.dom.Comment;

/**
 *
 * @author rainer prosi
 *
 */
class XJDFExampleTest extends ExampleTest
{
	/**
	 *
	 */
	@Test
	void testNamespace()
	{
		final XJDFHelper xjdfHelper = new XJDFHelper("Extension", null, null);
		xjdfHelper.setTypes(EnumType.Product.getName());
		final SetHelper shMedia = xjdfHelper.getCreateSet(XJDFConstants.Resource, ElementName.MEDIA, EnumUsage.Input);
		final ResourceHelper rh = shMedia.appendPartition(AttributeName.SHEETNAME, "S1", true);
		final JDFMedia m = (JDFMedia) rh.getResource();
		m.setAttribute("foo:FooAtt", "FooVal", "http://www.foo.org");
		m.setMediaType(EnumMediaType.Paper);
		xjdfHelper.cleanUp();
		setSnippet(rh, true);
		writeTest(xjdfHelper, "structure/namespacesExtendAttribute.xjdf");
	}

	/**
	 *
	 */
	@Test
	void testSnippet()
	{
		final XJDFHelper xjdfHelper = new XJDFHelper("Extension", null, null);
		final SetHelper shMedia = xjdfHelper.getCreateSet(XJDFConstants.Resource, ElementName.MEDIA, EnumUsage.Input);
		final ResourceHelper rh = shMedia.appendPartition(AttributeName.SHEETNAME, "S1", true);
		setSnippet(rh.getRoot(), true);
		Assertions.assertTrue(xjdfHelper.toString().indexOf("<!-- START SNIPPET -->") > 0);
		Assertions.assertTrue(xjdfHelper.toString().indexOf("<!-- END SNIPPET -->") > 0);
	}

	/**
	 *
	 */
	@Test
	void testSnippetAuditPool()
	{
		final XJDFHelper xjdfHelper = new XJDFHelper("Extension", null, null);
		setSnippet(xjdfHelper, true);
		setSnippet(xjdfHelper.getAuditPool(), false);
		final KElement ap = xjdfHelper.getAuditPool().getRoot();
		Assertions.assertTrue(ap.getPreviousSibling() instanceof Comment);
		Assertions.assertEquals(ap.getPreviousSibling().getNodeValue(), " END SNIPPET ");
		Assertions.assertTrue(ap.getNextSibling() instanceof Comment);
		Assertions.assertEquals(ap.getNextSibling().getNodeValue(), " START SNIPPET ");
	}

	/**
	 *
	 */
	@Test
	void testSnippetAuditPoolFirst()
	{
		final XJDFHelper xjdfHelper = new XJDFHelper("Extension", null, null);
		setSnippet(xjdfHelper.getAuditPool(), false);
		setSnippet(xjdfHelper, true);
		final KElement ap = xjdfHelper.getAuditPool().getRoot();
		Assertions.assertTrue(ap.getPreviousSibling() instanceof Comment);
		Assertions.assertEquals(ap.getPreviousSibling().getNodeValue(), " END SNIPPET ");
		Assertions.assertTrue(ap.getNextSibling() instanceof Comment);
		Assertions.assertEquals(ap.getNextSibling().getNodeValue(), " START SNIPPET ");
	}

	/**
	 *
	 */
	@Test
	void testSnippetRoot()
	{
		final XJDFHelper xjdfHelper = new XJDFHelper("Extension", null, null);
		final SetHelper shMedia = xjdfHelper.getCreateSet(XJDFConstants.Resource, ElementName.MEDIA, EnumUsage.Input);
		shMedia.appendPartition(AttributeName.SHEETNAME, "S1", true);
		final KElement root = xjdfHelper.getRoot();
		setSnippet(root, true);
		Assertions.assertTrue(root.getOwnerDocument_KElement().write2String(2).indexOf("<!-- START SNIPPET -->") > 0);
		Assertions.assertTrue(root.getOwnerDocument_KElement().write2String(2).indexOf("<!-- END SNIPPET -->") > 0);
	}

	/**
	 *
	 */
	@Test
	void testIntentNamespace()
	{
		final XJDFHelper xjdfHelper = new XJDFHelper("IntentExtension", null, null);
		xjdfHelper.setTypes(EnumType.Product.getName());
		final ProductHelper product = xjdfHelper.getCreateRootProduct(0);
		xjdfHelper.getRoot().addNameSpace("foo", "http://www.foo.org");
		final IntentHelper ih = product.appendIntent("foo:FooIntent");
		ih.getResource().setAttribute("FooAtt", "FooVal");
		xjdfHelper.getRoot().removeAttribute("xmlns:foo");
		xjdfHelper.cleanUp();
		setSnippet(product.getRoot(), true);
		writeTest(xjdfHelper, "structure/namespacesExtendIntent.xjdf");
	}

	/**
	 *
	 */
	@Test
	void testResourceNamespace()
	{
		final XJDFHelper xjdfHelper = new XJDFHelper("ResourceExtension", null, null);
		xjdfHelper.setTypes("foo:Fooing");
		final SetHelper sh = xjdfHelper.appendResourceSet("foo:FooParams", EnumUsage.Input);
		sh.getSet().addNameSpace("foo", "http://www.foo.org");
		xjdfHelper.cleanUp();
		setSnippet(sh, true);
		final ResourceHelper res = sh.getCreatePartition("Run", "R1", true);
		res.getResource().setAttribute("FooAtt", "FooVal");
		writeTest(xjdfHelper, "structure/namespacesExtendSet.xjdf");
	}

	/**
	 *
	 */
	@Test
	void testCPI()
	{
		final XJDFHelper xjdfHelper = new XJDFHelper("CPI_Example", null, null);
		xjdfHelper.setTypes("Cutting Folding");
		final SetHelper sh1 = xjdfHelper.getCreateSet(XJDFConstants.Resource, ElementName.NODEINFO, EnumUsage.Input);
		final JDFDate jdfDate = new JDFDate().setTime(13, 0, 0);
		sh1.getPartition((JDFAttributeMap) null).getResource().setAttribute(AttributeName.START, jdfDate.getDateTimeISO());
		sh1.setAttribute(AttributeName.COMBINEDPROCESSINDEX, "0");
		final SetHelper sh2 = xjdfHelper.appendResourceSet(ElementName.NODEINFO, EnumUsage.Input);
		jdfDate.addOffset(0, 0, 4, 0);
		sh2.appendPartition(null, true).getResource().setAttribute(AttributeName.START, jdfDate.getDateTimeISO());
		sh2.setAttribute(AttributeName.COMBINEDPROCESSINDEX, "1");
		xjdfHelper.appendResourceSet(ElementName.CUTTINGPARAMS, EnumUsage.Input).appendPartition(null, false);
		xjdfHelper.appendResourceSet(ElementName.FOLDINGPARAMS, EnumUsage.Input).appendPartition(null, false);
		xjdfHelper.cleanUp();
		setSnippet(xjdfHelper, true);
		setSnippet(xjdfHelper.getAuditPool(), false);
		writeTest(xjdfHelper, "structure/CPI.xjdf");
	}

	/**
	 *
	 */
	@Test
	void testProcessNamespace()
	{
		final XJDFHelper xjdfHelper = new XJDFHelper("IntentExtension", null, null);
		final KElement root = xjdfHelper.getRoot();
		root.addNameSpace("foo", "http://www.foo.org");
		xjdfHelper.setTypes("foo:FooMaking");
		cleanSnippets(xjdfHelper);
		writeTest(xjdfHelper, "structure/namespacesExtendProcess.xjdf");
	}

	/**
	 * @see org.cip4.jdflib.JDFTestCaseBase#setUp()
	 */
	@Override
	@BeforeEach
	public void setUp() throws Exception
	{
		super.setUp();
		KElement.setLongID(false);
	}
}
