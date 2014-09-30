/**
 * The CIP4 Software License, Version 1.0
 *
 * Copyright (c) 2001-2014 The International Cooperation for the Integration of 
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
import org.cip4.jdflib.core.AttributeName;
import org.cip4.jdflib.core.ElementName;
import org.cip4.jdflib.core.JDFDoc;
import org.cip4.jdflib.core.JDFElement.EnumValidationLevel;
import org.cip4.jdflib.core.JDFResourceLink;
import org.cip4.jdflib.core.JDFResourceLink.EnumUsage;
import org.cip4.jdflib.core.KElement;
import org.cip4.jdflib.core.XMLDoc;
import org.cip4.jdflib.datatypes.JDFAttributeMap;
import org.cip4.jdflib.extensions.PartitionHelper;
import org.cip4.jdflib.extensions.ProductHelper;
import org.cip4.jdflib.extensions.SetHelper;
import org.cip4.jdflib.extensions.XJDFHelper;
import org.cip4.jdflib.jmf.JDFJMF;
import org.cip4.jdflib.node.JDFNode;
import org.cip4.jdflib.resource.intent.JDFDeliveryIntent;
import org.cip4.jdflib.resource.intent.JDFIntentResource;
import org.cip4.jdflib.resource.process.JDFContact;
import org.cip4.jdflib.resource.process.JDFContact.EnumContactType;
import org.cip4.jdflib.resource.process.JDFMedia;
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
		KElement c = e.appendElement("ParameterSet");
		c.setAttribute("Name", "Contact");
		c.setAttribute("Usage", "Input");
		c.appendElement("Parameter").appendElement(ElementName.CONTACT).appendElement(ElementName.COMPANY).setAttribute("CompanyID", "company_id");
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
		SetHelper sh = h.getCreateSet("Parameter", "Contact", null);
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

}
