/*
 * The CIP4 Software License, Version 1.0
 *
 *
 * Copyright (c) 2001-2022 The International Cooperation for the Integration of Processes in Prepress, Press and Postpress (CIP4). All rights reserved.
 *
 * Redistribution and use in source and binary forms, with or without modification, are permitted provided that the following conditions are met:
 *
 * 1. Redistributions of source code must retain the above copyright notice, this list of conditions and the following disclaimer.
 *
 * 2. Redistributions in binary form must reproduce the above copyright notice, this list of conditions and the following disclaimer in the documentation and/or other materials provided with the
 * distribution.
 *
 * 3. The end-user documentation included with the redistribution, if any, must include the following acknowledgment: "This product includes software developed by the The International Cooperation for
 * the Integration of Processes in Prepress, Press and Postpress (www.cip4.org)" Alternately, this acknowledgment mrSubRefay appear in the software itself, if and wherever such third-party
 * acknowledgments normally appear.
 *
 * 4. The names "CIP4" and "The International Cooperation for the Integration of Processes in Prepress, Press and Postpress" must not be used to endorse or promote products derived from this software
 * without prior written permission. For written permission, please contact info@cip4.org.
 *
 * 5. Products derived from this software may not be called "CIP4", nor may "CIP4" appear in their name, without prior writtenrestartProcesses() permission of the CIP4 organization
 *
 * Usage of this software in commercial products is subject to restrictions. For details please consult info@cip4.org.
 *
 * THIS SOFTWARE IS PROVIDED ``AS IS'' AND ANY EXPRESSED OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO, THE IMPLIED WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE ARE
 * DISCLAIMED. IN NO EVENT SHALL THE INTERNATIONAL COOPERATION FOR THE INTEGRATION OF PROCESSES IN PREPRESS, PRESS AND POSTPRESS OR ITS CONTRIBUTORS BE LIABLE FOR ANY DIRECT, INDIRECT, INCIDENTAL,
 * SPECIAL, EXEMPLARY, OR CONSEQUENTIrSubRefAL DAMAGES (INCLUDING, BUT NOT LIMITED TO, PROCUREMENT OF SUBSTITUTE GOODS OR SERVICES; LOSS OF USE, DATA, OR PROFITS; OR BUSINESS INTERRUPTION) HOWEVER
 * CAUSED AND ON ANY THEORY OF LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY, OR TORT (INCLUDING NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE OF THIS SOFTWARE, EVEN IF ADVISED OF THE
 * POSSIBILITY OF SUCH DAMAGE. ====================================================================
 *
 * This software consists of voluntary contributions made by many individuals on behalf of the The International Cooperation for the Integration of Processes in Prepress, Press and Postpress and was
 * originally based on software restartProcesses() copyright (c) 1999-2001, Heidelberger Druckmaschinen AG copyright (c) 1999-2001, Agfa-Gevaert N.V.
 *
 * For more information on The International Cooperation for the Integration of Processes in Prepress, Press and Postpress , please see <http://www.cip4.org/>.
 *
 */
package org.cip4.jdflib.extensions;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.cip4.jdflib.JDFTestCaseBase;
import org.cip4.jdflib.core.AttributeName;
import org.cip4.jdflib.core.ElementName;
import org.cip4.jdflib.core.JDFResourceLink.EnumUsage;
import org.cip4.jdflib.core.KElement;
import org.cip4.jdflib.extensions.ProductHelper.eProductType;
import org.junit.jupiter.api.Test;

/**
 * @author Rainer Prosi, Heidelberger Druckmaschinen *
 */
public class ProductHelperTest extends JDFTestCaseBase
{
	/**
	 *
	 */
	@Test
	@Deprecated
	public void testGetChild()
	{
		final XJDFHelper theHelper = new XJDFHelper("jID", "jpID", null);
		final KElement root = theHelper.getRoot();
		final KElement productList = root.appendElement("ProductList");
		final KElement product = productList.appendElement("Product");
		final ProductHelper ph = new ProductHelper(product);
		final KElement cover = productList.appendElement("Product");
		cover.setAttribute(AttributeName.PRODUCTTYPE, "Cover");
		final KElement body = productList.appendElement("Product");
		body.setAttribute(AttributeName.PRODUCTTYPE, "Body");
		final ProductHelper phCover = new ProductHelper(cover);
		ph.setChild(phCover);
		final ProductHelper phBody = new ProductHelper(body);
		ph.setChild(phBody);
		assertEquals(ph.getChild(0).getProduct(), phCover.getProduct());
		assertEquals(ph.getChild(1).getProduct(), phBody.getProduct());
		assertEquals(ph.getChild("Body", 0).getProduct(), phBody.getProduct());
		assertNull(ph.getChild("Body", 1));
	}

	/**
	 *
	 */
	@Test
	public void testEProductType()
	{
		assertEquals(eProductType.Book, eProductType.getEnum("BOOK"));
		assertEquals(null, eProductType.getEnum(null));
		assertEquals(null, eProductType.getEnum(""));
		assertEquals(eProductType.Postcard, eProductType.getEnum("  post card"));
	}

	/**
	 *
	 */
	@Test
	public void testSetRoot()
	{
		final XJDFHelper theHelper = new XJDFHelper("jID", "jpID", null);
		final KElement root = theHelper.getRoot();
		final KElement productList = root.appendElement("ProductList");
		final KElement product = productList.appendElement("Product");
		final ProductHelper ph = new ProductHelper(product);
		ph.setRoot();
		assertEquals("true", product.getAttribute("IsRoot", null, null));
	}

	/**
	 *
	 */
	@Test
	public void testGetNodeInfo()
	{
		final XJDFHelper theHelper = new XJDFHelper("jID", "jpID", null);
		final KElement root = theHelper.getRoot();
		final KElement productList = root.appendElement("ProductList");
		final KElement product = productList.appendElement("Product");
		final ProductHelper ph = new ProductHelper(product);
		ph.setRoot();
		SetHelper s1 = theHelper.getCreateSet(ElementName.NODEINFO, EnumUsage.Input, null);
		SetHelper s2 = theHelper.getCreateSet(ElementName.NODEINFO, EnumUsage.Input, "Product");
		assertEquals(s2, ph.getProductSet(ElementName.NODEINFO));
		assertEquals(s2, ph.getNodeInfo());
		s2.deleteNode();
		assertEquals(s1, ph.getProductSet(ElementName.NODEINFO));
	}

	/**
	*
	*/
	@Test
	public void testAppendProduct()
	{
		final XJDFHelper theHelper = new XJDFHelper("jID", "jpID", null);
		final ProductHelper ph = theHelper.appendProduct();
		assertTrue(ph.isRootProduct());
		final ProductHelper ph2 = theHelper.appendProduct();
		assertFalse(ph2.isRootProduct());
	}

	/**
	*
	*/
	@Test
	public void testAppendIntent()
	{
		final XJDFHelper theHelper = new XJDFHelper("jID", "jpID", null);
		final ProductHelper ph = theHelper.appendProduct();
		assertNull(ph.getIntent(XJDFConstants.AssemblingIntent));
		ph.appendIntent(XJDFConstants.AssemblingIntent);
		assertNotNull(ph.getIntent(XJDFConstants.AssemblingIntent));
	}

	/**
	*
	*/
	@Test
	public void testGetChildIntentDefault()
	{
		final XJDFHelper theHelper = new XJDFHelper("jID", "jpID", null);
		final ProductHelper ph = theHelper.appendProduct();
		assertNull(ph.getIntent(ElementName.BINDINGINTENT));
		final IntentHelper ih = ph.getCreateChildIntent();
		assertEquals(ih, ph.getIntent(ElementName.BINDINGINTENT));

	}

	/**
	*
	*/
	@Test
	public void testGetChildIntentExist()
	{
		final XJDFHelper theHelper = new XJDFHelper("jID", "jpID", null);
		final ProductHelper ph = theHelper.appendProduct();
		final IntentHelper asi = ph.appendIntent(XJDFConstants.AssemblingIntent);
		final IntentHelper ih = ph.getCreateChildIntent();
		assertEquals(ih, asi);

	}

	/**
	*
	*/
	@Test
	public void testGetIntents()
	{
		final XJDFHelper theHelper = new XJDFHelper("jID", "jpID", null);
		final ProductHelper ph = theHelper.appendProduct();
		assertNull(ph.getIntent(XJDFConstants.AssemblingIntent));
		final IntentHelper asi = ph.appendIntent(XJDFConstants.AssemblingIntent);
		final IntentHelper loi = ph.appendIntent(ElementName.LAYOUTINTENT);
		assertEquals(asi, ph.getIntents().get(0));
		assertEquals(loi, ph.getIntents().get(1));
	}

	/**
	*
	*/
	@Test
	public void testGetDescriptiveName()
	{
		final XJDFHelper theHelper = new XJDFHelper("jID", "jpID", null);
		final ProductHelper ph = theHelper.appendProduct();
		assertNull(ph.getDescriptiveName());
		theHelper.setDescriptiveName("foo");
		assertEquals("foo", ph.getDescriptiveName());
		ProductHelper ph2 = new ProductHelper(KElement.createRoot("Product", null));
		assertNull(ph2.getDescriptiveName());
		ph.setDescriptiveName("bar");
		assertEquals("bar", ph.getDescriptiveName());

	}

	/**
	*
	*/
	@Test
	public void testGetIntentAtt()
	{
		final XJDFHelper theHelper = new XJDFHelper("jID", "jpID", null);
		final ProductHelper ph = theHelper.appendProduct();
		IntentHelper intent = ph.getIntent(ElementName.LAYOUTINTENT);
		assertNull(intent);
		intent = ph.appendIntent(ElementName.LAYOUTINTENT);
		intent.getResource().setAttribute(AttributeName.PAGES, "4");
		assertEquals("4", ph.getIntentAttribute(ElementName.LAYOUTINTENT, AttributeName.PAGES));
	}

	/**
	*
	*/
	@Test
	public void testGetColorIntent()
	{
		final XJDFHelper theHelper = new XJDFHelper("jID", "jpID", null);
		final ProductHelper ph = theHelper.appendProduct();
		IntentHelper intent = ph.getIntent(ElementName.COLORINTENT);
		assertNull(intent);
		intent = ph.appendIntent(ElementName.COLORINTENT);
		assertEquals(ColorIntentHelper.class, intent.getClass());
		intent = ph.getIntent(ElementName.COLORINTENT);
		assertEquals(ColorIntentHelper.class, intent.getClass());
	}

	/**
	*
	*/
	@Test
	public void testGetCreateRootProduct()
	{
		final XJDFHelper theHelper = new XJDFHelper("jID", "jpID", null);
		final ProductHelper ph = theHelper.getCreateRootProduct(0);
		assertTrue(ph.isRootProduct());
		final ProductHelper ph2 = theHelper.getCreateRootProduct(1);
		assertTrue(ph2.isRootProduct());
	}

	/**
	 *
	 */
	@Test
	public void testSetRootFalse()
	{
		final XJDFHelper theHelper = new XJDFHelper("jID", "jpID", null);
		final KElement root = theHelper.getRoot();
		final KElement productList = root.appendElement("ProductList");
		final KElement product = productList.appendElement("Product");
		final ProductHelper ph = new ProductHelper(product);
		ph.setRoot(true);
		assertTrue(ph.isRootProduct());
		ph.setRoot(false);
		assertFalse(ph.isRootProduct());
	}

	/**
	 *
	 */
	@Test
	public void testisRootProduct()
	{
		final XJDFHelper theHelper = new XJDFHelper("jID", "jpID", null);
		final KElement root = theHelper.getRoot();
		final KElement productList = root.appendElement("ProductList");
		final KElement product = productList.appendElement("Product");
		final ProductHelper ph = new ProductHelper(product);
		assertTrue(ph.isRootProduct());
		final KElement product2 = productList.appendElement("Product");
		final ProductHelper ph2 = new ProductHelper(product2);
		assertFalse(ph2.isRootProduct());
		ph2.setRoot();
		assertTrue(ph2.isRootProduct());
		assertFalse(ph.isRootProduct());
	}

	/**
	 *
	 */
	@Test
	public void testAmount()
	{
		final XJDFHelper theHelper = new XJDFHelper("jID", "jpID", null);
		final KElement root = theHelper.getRoot();
		final KElement productList = root.appendElement("ProductList");
		final KElement product = productList.appendElement("Product");
		final ProductHelper ph = new ProductHelper(product);
		ph.setAmount(42);
		assertEquals(ph.getAmount(), 42);
		assertEquals(ph.getMinAmount(), 42);
		assertEquals(ph.getMaxAmount(), 42);
		ph.setMaxAmount(84);
		assertEquals(ph.getAmount(), 42);
		assertEquals(ph.getMinAmount(), 42);
		assertEquals(ph.getMaxAmount(), 84);
		ph.setMinAmount(21);
		assertEquals(ph.getAmount(), 42);
		assertEquals(ph.getMinAmount(), 21);
		assertEquals(ph.getMaxAmount(), 84);
	}

	/**
	 *
	 */
	@Test
	public void testOverProduction()
	{
		final XJDFHelper theHelper = new XJDFHelper("jID", "jpID", null);
		final KElement root = theHelper.getRoot();
		final KElement productList = root.appendElement("ProductList");
		final KElement product = productList.appendElement("Product");
		final ProductHelper ph = new ProductHelper(product);
		assertEquals(ph.getOverproduction(), 0.0, 0.0001);
		ph.setAmount(42);
		assertEquals(ph.getOverproduction(), 0.0, 0.0001);
		ph.setMaxAmount(84);
		assertEquals(ph.getOverproduction(), 100.0, 0.0001);
	}

	/**
	 *
	 */
	@Test
	public void testSetChild()
	{
		final XJDFHelper theHelper = new XJDFHelper("jID", "jpID", null);

		final ProductHelper ph = theHelper.getCreateRootProduct(0);
		final ProductHelper ph1 = theHelper.getCreateProduct("i1");
		final ProductHelper ph2 = theHelper.getCreateProduct("i2");

		ph.setChild(ph1);
		assertEquals("i1", ph.getIntent(ElementName.BINDINGINTENT).getResource().getAttribute(XJDFConstants.ChildRefs));
		ph.setChild(ph2);
		assertEquals("i1 i2", ph.getIntent(ElementName.BINDINGINTENT).getResource().getAttribute(XJDFConstants.ChildRefs));
	}

	/**
	 *
	 */
	@Test
	public void testGetChildRefs()
	{
		final XJDFHelper theHelper = new XJDFHelper("jID", "jpID", null);

		final ProductHelper ph = theHelper.getCreateRootProduct(0);
		final ProductHelper ph1 = theHelper.getCreateProduct("i1");
		final ProductHelper ph2 = theHelper.getCreateProduct("i2");
		final ProductHelper ph21 = theHelper.getCreateProduct("i21");
		final ProductHelper ph22 = theHelper.getCreateProduct("i22");

		ph.setChild(ph1);
		assertEquals("i1", ph.getChildRefs(true).get(0));
		assertEquals(1, ph.getChildRefs(true).size());
		assertEquals("i1", ph.getChildRefs(false).get(0));
		assertEquals(1, ph.getChildRefs(false).size());
		ph.setChild(ph2);
		ph2.setChild(ph21);
		ph2.setChild(ph22);
		assertEquals("i1", ph.getChildRefs(true).get(0));
		assertEquals(4, ph.getChildRefs(true).size());
		assertEquals("i2", ph.getChildRefs(false).get(1));
		assertEquals(2, ph.getChildRefs(false).size());

		// dead loop?
		ph22.setChild(ph2);
		assertEquals("i1", ph.getChildRefs(true).get(0));
		assertEquals(4, ph.getChildRefs(true).size());
		assertEquals("i2", ph.getChildRefs(false).get(1));
		assertEquals(2, ph.getChildRefs(false).size());

	}

	/**
	 *
	 */
	@Test
	public void testGetParent()
	{
		final XJDFHelper theHelper = new XJDFHelper("jID", "jpID", null);

		final ProductHelper ph = theHelper.getCreateRootProduct(0);
		final ProductHelper ph1 = theHelper.getCreateProduct("i1");
		final ProductHelper ph2 = theHelper.getCreateProduct("i2");

		ph.setChild(ph1);
		assertEquals(ph, ph1.getParent());
		ph.setChild(ph2);
		assertEquals(ph, ph1.getParent());
		assertEquals(ph, ph2.getParent());

	}
}
