/**
 * The CIP4 Software License, Version 1.0
 *
 * Copyright (c) 2001-2015 The International Cooperation for the Integration of 
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
package org.cip4.jdflib.extensions.xjdfwalker;

import org.cip4.jdflib.JDFTestCaseBase;
import org.cip4.jdflib.auto.JDFAutoLayoutIntent.EnumSides;
import org.cip4.jdflib.core.AttributeName;
import org.cip4.jdflib.core.ElementName;
import org.cip4.jdflib.core.JDFDoc;
import org.cip4.jdflib.core.JDFElement.EnumValidationLevel;
import org.cip4.jdflib.core.JDFResourceLink;
import org.cip4.jdflib.core.JDFResourceLink.EnumUsage;
import org.cip4.jdflib.core.KElement;
import org.cip4.jdflib.core.VString;
import org.cip4.jdflib.core.XMLDoc;
import org.cip4.jdflib.datatypes.JDFAttributeMap;
import org.cip4.jdflib.extensions.PartitionHelper;
import org.cip4.jdflib.extensions.ProductHelper;
import org.cip4.jdflib.extensions.SetHelper;
import org.cip4.jdflib.extensions.XJDFHelper;
import org.cip4.jdflib.jmf.JDFJMF;
import org.cip4.jdflib.node.JDFNode;
import org.cip4.jdflib.resource.JDFResource;
import org.cip4.jdflib.resource.JDFStrippingParams;
import org.cip4.jdflib.resource.intent.JDFColorIntent;
import org.cip4.jdflib.resource.intent.JDFDeliveryIntent;
import org.cip4.jdflib.resource.intent.JDFIntentResource;
import org.cip4.jdflib.resource.intent.JDFLayoutIntent;
import org.cip4.jdflib.resource.process.JDFContact;
import org.cip4.jdflib.resource.process.JDFContact.EnumContactType;
import org.cip4.jdflib.resource.process.JDFDeliveryParams;
import org.cip4.jdflib.resource.process.JDFLayout;
import org.cip4.jdflib.resource.process.JDFMedia;
import org.cip4.jdflib.resource.process.JDFRunList;
import org.cip4.jdflib.resource.process.postpress.JDFHoleMakingParams;
import org.junit.Test;

/**
 * @author rainer prosi
 * @date Dec 10, 2012
 */
public class XJDFToJDFConverterTest extends JDFTestCaseBase
{
	/**
	 *  
	 *  
	 */
	@Test
	public void testCompany()
	{
		final XJDFToJDFConverter xCon = new XJDFToJDFConverter(null);
		KElement e = new XMLDoc("XJDF", null).getRoot();
		KElement c = e.appendElement(SetHelper.RESOURCE_SET);
		c.setAttribute("Name", "Contact");
		c.setAttribute("Usage", "Input");
		c.appendElement(XJDFHelper.RESOURCE).appendElement(ElementName.CONTACT).appendElement(ElementName.COMPANY).setAttribute("CompanyID", "company_id");
		final JDFDoc d = xCon.convert(e);
		assertNotNull(d);
		JDFNode root = d.getJDFRoot();
		JDFContact contact = (JDFContact) root.getResource("Contact", EnumUsage.Input, 0);
		assertEquals(contact.getCompany().getProductID(), "company_id");
	}

	/**
	 *  
	 *  
	 */
	@Test
	public void testParameterSet()
	{
		final XJDFToJDFConverter xCon = new XJDFToJDFConverter(null);
		KElement e = new XMLDoc("XJDF", null).getRoot();
		KElement c = e.appendElement(SetHelper.PARAMETER_SET);
		c.setAttribute("Name", "Contact");
		c.setAttribute("Usage", "Input");
		c.appendElement(XJDFHelper.PARAMETER).appendElement(ElementName.CONTACT).appendElement(ElementName.COMPANY).setAttribute("CompanyID", "company_id");
		final JDFDoc d = xCon.convert(e);
		assertNotNull(d);
		JDFNode root = d.getJDFRoot();
		JDFContact contact = (JDFContact) root.getResource("Contact", EnumUsage.Input, 0);
		assertEquals(contact.getCompany().getProductID(), "company_id");
	}

	/**
	 *  
	 *  
	 */
	@Test
	public void testPrintedPages()
	{
		final XJDFToJDFConverter xCon = new XJDFToJDFConverter(null);
		KElement e = new XMLDoc("XJDF", null).getRoot();
		KElement c = e.getCreateXPathElement("ProductList/Product/Intent[@Name=\"LayoutIntent\"]/LayoutIntent");
		c.setAttribute("Sides", EnumSides.OneSided.getName(), null);
		c.setAttribute("PrintedPages", "21");
		final JDFDoc d = xCon.convert(e);
		assertNotNull(d);
		JDFNode root = d.getJDFRoot();
		JDFLayoutIntent loi = (JDFLayoutIntent) root.getResource(ElementName.LAYOUTINTENT, EnumUsage.Input, 0);
		assertEquals(loi.getPages().getActual(), 42);
	}

	/**
	 *  
	 *  
	 */
	@Test
	public void testExternalImpositionTemplate()
	{
		final XJDFToJDFConverter xCon = new XJDFToJDFConverter(null);
		KElement e = new XMLDoc("XJDF", null).getRoot();
		KElement c = e.appendElement(SetHelper.RESOURCE_SET);
		c.setAttribute("Name", "Layout");
		c.setAttribute("Usage", "Input");
		c.appendElement(XJDFHelper.RESOURCE).appendElement(ElementName.LAYOUT).appendElement(ElementName.EXTERNALIMPOSITIONTEMPLATE).appendElement(ElementName.FILESPEC).setAttribute("URL", "file://foo.xml");
		final JDFDoc d = xCon.convert(e);
		assertNotNull(d);
		JDFNode root = d.getJDFRoot();
		JDFStrippingParams sp = (JDFStrippingParams) root.getResource(ElementName.STRIPPINGPARAMS, EnumUsage.Input, 0);
		assertEquals(sp.getExternalImpositionTemplate().getFileSpec(0).getURL(), "file://foo.xml");
		assertNull("Layout is zapped", root.getResource(ElementName.LAYOUT, EnumUsage.Input, 0));
	}

	/**
	 * check that signame gets automagically inserted below sheetname
	 */
	@Test
	public void testSignatureName()
	{
		XJDFHelper h = new XJDFHelper("j1", null, null);
		SetHelper lh = h.appendResourceSet(ElementName.LAYOUT, EnumUsage.Input);
		PartitionHelper ph = lh.appendPartition(new JDFAttributeMap(AttributeName.SHEETNAME, "S1"), true);
		ph.setAttribute(AttributeName.DESCRIPTIVENAME, "d1", null);
		XJDFToJDFConverter c = new XJDFToJDFConverter(null);
		JDFDoc docJDF = c.convert(h);
		JDFNode jdf = docJDF.getJDFRoot();
		JDFLayout lo = (JDFLayout) jdf.getResource(ElementName.LAYOUT, EnumUsage.Input, 0);
		assertNotNull(lo);
		assertEquals(lo.getPartIDKeys().get(0), AttributeName.SIGNATURENAME);
	}

	/**
	*  
	*  
	*/
	@Test
	public void testAmountPool()
	{
		final XJDFToJDFConverter xCon = new XJDFToJDFConverter(null);
		XJDFHelper xjdf = new XJDFHelper("j1", null, null);
		SetHelper sh = xjdf.getCreateResourceSet("Media", EnumUsage.Input);
		PartitionHelper ph = sh.getCreatePartition(null, true);
		ph.setAmount(33, null, true);
		KElement e = xjdf.getRoot();
		final JDFDoc d = xCon.convert(e);
		assertNotNull(d);
		JDFNode root = d.getJDFRoot();
		JDFMedia m = (JDFMedia) root.getResource("Media", EnumUsage.Input, 0);
		assertNotNull(m);
		JDFResourceLink rl = root.getLink(m, null);
		assertNull(rl.getAmountPool());
		assertNull(m.getElement("AmountPool"));
	}

	/**
	*  
	*  
	*/
	@Test
	public void testOverage()
	{
		final XJDFToJDFConverter xCon = new XJDFToJDFConverter(null);
		XJDFHelper xjdf = new XJDFHelper("j1", null, null);
		ProductHelper ph = xjdf.appendProduct();
		ph.setAmount(400);
		ph.setXPathValue("@MaxAmount", "600");
		ph.setXPathValue("@MinAmount", "300");
		JDFDoc d = xCon.convert(xjdf.getRoot());
		JDFDeliveryIntent di = (JDFDeliveryIntent) d.getJDFRoot().getResource(ElementName.DELIVERYINTENT, EnumUsage.Input, 0);
		assertNotNull(di);
		assertEquals(JDFIntentResource.guessActual(di, "Overage"), "50");
		assertEquals(JDFIntentResource.guessActual(di, "Underage"), "25");
	}

	/**
	*  
	*  
	*/
	@Test
	public void testDeliveryIntent()
	{
		KElement xjdf = new JDFToXJDFConverterTest()._testDeliveryIntent();
		final XJDFToJDFConverter xCon = new XJDFToJDFConverter(null);
		JDFDoc d = xCon.convert(xjdf);
		JDFDeliveryParams dp = (JDFDeliveryParams) d.getJDFRoot().getResource(ElementName.DELIVERYPARAMS, EnumUsage.Input, 0);
		assertNull(dp);
		JDFDeliveryIntent di = (JDFDeliveryIntent) d.getJDFRoot().getResource(ElementName.DELIVERYINTENT, EnumUsage.Input, 0);
		assertNotNull(di);
		assertNotNull("The ProductRef was not translated", di.getDropIntent(1).getDropItemIntent(0).getComponent());
	}

	/**
	*  
	*  
	*/
	@Test
	public void testHolePattern()
	{
		KElement xjdf = new JDFToXJDFConverterTest()._testHoleLine();
		final XJDFToJDFConverter xCon = new XJDFToJDFConverter(null);
		JDFDoc d = xCon.convert(xjdf);
		JDFHoleMakingParams hp = (JDFHoleMakingParams) d.getJDFRoot().getResource(ElementName.HOLEMAKINGPARAMS, EnumUsage.Input, 0);
		assertNotNull(hp);
		assertNotNull(hp.getHoleLine(0));
		assertNotNull(hp.getHoleLine(0).getHole());
	}

	/**
	 * 
	 * @return
	 */
	@Test
	public void testPageList()
	{
		KElement xjdf = new JDFToXJDFConverterTest()._testPageList(false);
		final XJDFToJDFConverter xCon = new XJDFToJDFConverter(null);
		JDFDoc d = xCon.convert(xjdf);
		JDFNode root = d.getJDFRoot();
		d.write2File(sm_dirTestDataTemp + "PageList.xjdf.jdf", 2, false);
		assertTrue(root.isValid(EnumValidationLevel.Incomplete));
	}

	/**
	 * 
	 * @return
	 */
	@Test
	public void testPageListEmpty()
	{
		KElement xjdf = new JDFToXJDFConverterTest()._testPageListEmpty();
		final XJDFToJDFConverter xCon = new XJDFToJDFConverter(null);
		JDFDoc d = xCon.convert(xjdf);
		JDFNode root = d.getJDFRoot();
		d.write2File(sm_dirTestDataTemp + "PageListEmpty.xjdf.jdf", 2, false);
		assertTrue(root.isValid(EnumValidationLevel.Incomplete));
	}

	/**
	*  
	*  
	*/
	@Test
	public void testPartIDKeys()
	{
		XJDFHelper h = new XJDFHelper("j1", "root", null);
		SetHelper rls = h.appendResourceSet(ElementName.RUNLIST, EnumUsage.Input);
		for (int i = 0; i < 3; i++)
		{
			PartitionHelper ph = rls.appendPartition(new JDFAttributeMap("Run", "R" + i), true);
			JDFRunList rl = (JDFRunList) ph.getCreateResource();
			rl.setFileURL("url");
		}
		XJDFToJDFConverter xc = new XJDFToJDFConverter(null);
		JDFDoc jdfd = xc.convert(h);
		JDFResource rl = jdfd.getJDFRoot().getResource(ElementName.RUNLIST, EnumUsage.Input, 0);
		assertEquals(rl.getPartIDKeys(), new VString("Run", null));
	}

	/**
	*  
	*  
	*/
	@Test
	public void testAmountPoolPart()
	{
		final XJDFToJDFConverter xCon = new XJDFToJDFConverter(null);
		XJDFHelper xjdf = new XJDFHelper("j1", null, null);
		SetHelper sh = xjdf.getCreateResourceSet("Media", EnumUsage.Input);
		PartitionHelper ph = sh.getCreatePartition(new JDFAttributeMap("SheetName", "S1"), true);
		ph.setAmount(33, new JDFAttributeMap("SheetName", "S1"), true);
		KElement e = xjdf.getRoot();
		final JDFDoc d = xCon.convert(e);
		assertNotNull(d);
		JDFNode root = d.getJDFRoot();
		JDFMedia m = (JDFMedia) root.getResource("Media", EnumUsage.Input, 0);
		assertNotNull(m);
		JDFResourceLink rl = root.getLink(m, null);
		assertNotNull(rl.getAmountPool());
		assertNull(m.getElement("AmountPool"));
	}

	/**
	 *  
	 *  
	 */
	@Test
	public void testCustomerInfoContacts()
	{
		final XJDFToJDFConverter xCon = new XJDFToJDFConverter(null);
		XJDFHelper h = new XJDFHelper("j1", "root", null);
		KElement e = h.getRoot();
		SetHelper sh = h.getCreateResourceSet("Contact", null);
		PartitionHelper ph = sh.getCreatePartition(0, true);
		ph.getResource().setAttribute(AttributeName.CONTACTTYPES, EnumContactType.Customer.getName());
		final JDFDoc d = xCon.convert(e);
		assertNotNull(d);
	}

	/**
	 *  
	 *  
	 */
	@Test
	public void testXJMFKnownMessages()
	{
		KElement root = new JDFDoc(XJDFHelper.XJMF).getRoot();
		root.appendElement("QueryKnownMessages");
		final XJDFToJDFConverter xCon = new XJDFToJDFConverter(null);
		final JDFDoc d = xCon.convert(root);
		JDFJMF jmfRoot = d.getJMFRoot();
		assertNotNull(jmfRoot);
		assertTrue(jmfRoot.isValid(EnumValidationLevel.Complete));
	}

	/**
	*  
	*  
	*/
	@Test
	public void testBackwardProduct()
	{
		XJDFHelper h = new XJDFHelper("j", "root", null);
		ProductHelper cover = h.appendProduct();
		ProductHelper body = h.appendProduct();
		ProductHelper book = h.appendProduct();
		book.setRoot();
		book.setChild(cover, 1);
		book.setChild(body, 1);
		final XJDFToJDFConverter xCon = new XJDFToJDFConverter(null);
		JDFDoc d = xCon.convert(h);
		assertEquals(d.getJDFRoot().getJobPartID(true), "root");
	}

	/**
	*  
	*  
	*/
	@Test
	public void testMultiBackwardProduct()
	{
		XJDFHelper h = new XJDFHelper("j", "root", null);
		for (int i = 0; i < 2; i++)
		{
			ProductHelper cover = h.appendProduct();
			ProductHelper body = h.appendProduct();
			ProductHelper book = h.appendProduct();
			book.setRoot();
			book.setChild(cover, 1);
			book.setChild(body, 1);
		}
		final XJDFToJDFConverter xCon = new XJDFToJDFConverter(null);
		JDFDoc d = xCon.convert(h);
		assertEquals(d.getJDFRoot().getJobPartID(true), "root");
		d.write2File(sm_dirTestDataTemp + "backproduct.jdf", 2, false);
	}

	/**
	 *  
	 */
	@Test
	public void testFromXJDFColorIntentSurfaceColor()
	{
		for (int i = 0; i < 3; i += 2)
		{
			final XJDFToJDFConverter xCon = new XJDFToJDFConverter(null);
			KElement e = new XMLDoc("XJDF", null).getRoot();
			e.setAttribute("Types", "Product");
			e.setXPathAttribute("ProductList/Product/Intent[@Name=\"ColorIntent\"]/ColorIntent/@NumColors", "4/1");
			e.setXPathAttribute("ProductList/Product/Intent[@Name=\"ColorIntent\"]/ColorIntent/SurfaceColor[@Surface=\"Front\"]/@Coatings", "DullVarnish");
			e.setXPathAttribute("ProductList/Product/Intent[@Name=\"ColorIntent\"]/ColorIntent/SurfaceColor[@Surface=\"Back\"]/@Coatings", "GlossVarnish");
			if (i != 0)
			{
				e.setXPathAttribute("ProductList/Product/Intent[@Name=\"ColorIntent\"]/ColorIntent/SurfaceColor[@Surface=\"Front\"]/@ColorsUsed", "Spot1 Spot");
				e.setXPathAttribute("ProductList/Product/Intent[@Name=\"ColorIntent\"]/ColorIntent/SurfaceColor[@Surface=\"Back\"]/@ColorsUsed", "Spot2 Spot");
			}
			final JDFDoc d = xCon.convert(e);
			assertNotNull(d);
			JDFNode root = d.getJDFRoot();
			JDFColorIntent ci = (JDFColorIntent) root.getResource(ElementName.COLORINTENT, EnumUsage.Input, 0);
			JDFColorIntent cif = (JDFColorIntent) ci.getPartition(new JDFAttributeMap("Side", "Front"), null);
			JDFColorIntent cib = (JDFColorIntent) ci.getPartition(new JDFAttributeMap("Side", "Back"), null);
			assertNull(ci.getColorsUsed());
			if (i > 0)
			{
				assertEquals(cib.getColorsUsed().getSeparations().size(), i);
				assertEquals(cif.getColorsUsed().getSeparations().size(), i);
			}
			else
			{
				assertNull(cif.getColorsUsed());
				assertNull(cib.getColorsUsed());
			}
			assertEquals(cif.getCoatings().getActual(), "DullVarnish");
		}
	}

}
