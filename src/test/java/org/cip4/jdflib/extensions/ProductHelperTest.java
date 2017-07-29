/*
 * The CIP4 Software License, Version 1.0
 *
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
 *    Alternately, this acknowledgment mrSubRefay appear in the software itself,
 *    if and wherever such third-party acknowledgments normally appear.
 *
 * 4. The names "CIP4" and "The International Cooperation for the Integration of
 *    Processes in  Prepress, Press and Postpress" must
 *    not be used to endorse or promote products derived from this
 *    software without prior written permission. For written
 *    permission, please contact info@cip4.org.
 *
 * 5. Products derived from this software may not be called "CIP4",
 *    nor may "CIP4" appear in their name, without prior writtenrestartProcesses()
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
 * SPECIAL, EXEMPLARY, OR CONSEQUENTIrSubRefAL DAMAGES (INCLUDING, BUT NOT
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
 * originally based on software restartProcesses()
 * copyright (c) 1999-2001, Heidelberger Druckmaschinen AG
 * copyright (c) 1999-2001, Agfa-Gevaert N.V.
 *
 * For more information on The International Cooperation for the
 * Integration of Processes in  Prepress, Press and Postpress , please see
 * <http://www.cip4.org/>.
 *
 */
package org.cip4.jdflib.extensions;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import org.cip4.jdflib.JDFTestCaseBase;
import org.cip4.jdflib.core.AttributeName;
import org.cip4.jdflib.core.ElementName;
import org.cip4.jdflib.core.KElement;
import org.junit.Test;

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
		XJDFHelper theHelper = new XJDFHelper("jID", "jpID", null);
		KElement root = theHelper.getRoot();
		KElement productList = root.appendElement("ProductList");
		KElement product = productList.appendElement("Product");
		ProductHelper ph = new ProductHelper(product);
		KElement cover = productList.appendElement("Product");
		cover.setAttribute(AttributeName.PRODUCTTYPE, "Cover");
		KElement body = productList.appendElement("Product");
		body.setAttribute(AttributeName.PRODUCTTYPE, "Body");
		ProductHelper phCover = new ProductHelper(cover);
		ph.setChild(phCover, 1);
		ProductHelper phBody = new ProductHelper(body);
		ph.setChild(phBody, 1);
		assertEquals(ph.getChild(0).getProduct(), phCover.getProduct());
		assertEquals(ph.getChild(1).getProduct(), phBody.getProduct());
		assertEquals(ph.getChild("Body", 0).getProduct(), phBody.getProduct());
		assertNull(ph.getChild("Body", 1));
	}

	/**
	 *
	 */
	@Test
	public void testSetRoot()
	{
		XJDFHelper theHelper = new XJDFHelper("jID", "jpID", null);
		KElement root = theHelper.getRoot();
		KElement productList = root.appendElement("ProductList");
		KElement product = productList.appendElement("Product");
		ProductHelper ph = new ProductHelper(product);
		ph.setRoot();
		assertEquals("true", product.getAttribute("IsRoot", null, null));
	}

	/**
	*
	*/
	@Test
	public void testAppendProduct()
	{
		XJDFHelper theHelper = new XJDFHelper("jID", "jpID", null);
		ProductHelper ph = theHelper.appendProduct();
		assertTrue(ph.isRootProduct());
		ProductHelper ph2 = theHelper.appendProduct();
		assertFalse(ph2.isRootProduct());
	}

	/**
	*
	*/
	@Test
	public void testAppendIntent()
	{
		XJDFHelper theHelper = new XJDFHelper("jID", "jpID", null);
		ProductHelper ph = theHelper.appendProduct();
		assertNull(ph.getIntent(XJDFConstants.AssemblingIntent));
		ph.appendIntent(XJDFConstants.AssemblingIntent);
		assertNotNull(ph.getIntent(XJDFConstants.AssemblingIntent));
	}

	/**
	*
	*/
	@Test
	public void testGetIntentAtt()
	{
		XJDFHelper theHelper = new XJDFHelper("jID", "jpID", null);
		ProductHelper ph = theHelper.appendProduct();
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
	public void testGetCreateRootProduct()
	{
		XJDFHelper theHelper = new XJDFHelper("jID", "jpID", null);
		ProductHelper ph = theHelper.getCreateRootProduct(0);
		assertTrue(ph.isRootProduct());
		ProductHelper ph2 = theHelper.getCreateRootProduct(1);
		assertTrue(ph2.isRootProduct());
	}

	/**
	 *
	 */
	@Test
	public void testSetRootFalse()
	{
		XJDFHelper theHelper = new XJDFHelper("jID", "jpID", null);
		KElement root = theHelper.getRoot();
		KElement productList = root.appendElement("ProductList");
		KElement product = productList.appendElement("Product");
		ProductHelper ph = new ProductHelper(product);
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
		XJDFHelper theHelper = new XJDFHelper("jID", "jpID", null);
		KElement root = theHelper.getRoot();
		KElement productList = root.appendElement("ProductList");
		KElement product = productList.appendElement("Product");
		ProductHelper ph = new ProductHelper(product);
		assertTrue(ph.isRootProduct());
		KElement product2 = productList.appendElement("Product");
		ProductHelper ph2 = new ProductHelper(product2);
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
		XJDFHelper theHelper = new XJDFHelper("jID", "jpID", null);
		KElement root = theHelper.getRoot();
		KElement productList = root.appendElement("ProductList");
		KElement product = productList.appendElement("Product");
		ProductHelper ph = new ProductHelper(product);
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
		XJDFHelper theHelper = new XJDFHelper("jID", "jpID", null);
		KElement root = theHelper.getRoot();
		KElement productList = root.appendElement("ProductList");
		KElement product = productList.appendElement("Product");
		ProductHelper ph = new ProductHelper(product);
		assertEquals(ph.getOverproduction(), 0.0);
		ph.setAmount(42);
		assertEquals(ph.getOverproduction(), 0.0);
		ph.setMaxAmount(84);
		assertEquals(ph.getOverproduction(), 100.0);
	}

}
