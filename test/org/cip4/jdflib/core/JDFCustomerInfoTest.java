/*
 *
 * The CIP4 Software License, Version 1.0
 *
 *
 * Copyright (c) 2001-2007 The International Cooperation for the Integration of 
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
/**
 * JDFCustomerInfoTest.java
 * 
 * @author Dietrich Mucha
 *
 * Copyright (C) 2004 Heidelberger Druckmaschinen AG. All Rights Reserved.
 */
package org.cip4.jdflib.core;

import java.util.Vector;

import org.cip4.jdflib.JDFTestCaseBase;
import org.cip4.jdflib.node.JDFNode;
import org.cip4.jdflib.resource.process.JDFContact;

public class JDFCustomerInfoTest extends JDFTestCaseBase
{
	public void testgetContactVector()
	{
		JDFDoc doc = new JDFDoc("JDF");
		JDFCustomerInfo info = prepareInfo(doc);

		Vector v = null;
		info = doc.getJDFRoot().getCustomerInfo();
		if (info != null)
		{
			v = info.getChildElementVector(ElementName.CONTACT, null, null,
					true, 0, false);
			assertEquals("v does not contain 4 contact", v.size(), 4);
		}

		v = null;
		info = doc.getJDFRoot().getCustomerInfo();
		if (info != null)
		{
			v = info.getChildElementVector(ElementName.CONTACT, null, null,
					true, 0, false);
			assertTrue("v does not contain 4 contacts", v.size() == 4);
		}
	}

	// ///////////////////////////////////////////////////////////////////////

	public void testGetContactWithContactType()
	{
		JDFDoc doc = new JDFDoc("JDF");
		JDFCustomerInfo info = prepareInfo(doc);

		JDFContact cc = info.getContactWithContactType("Customer", 0);
		assertNotNull("cc", cc);
		cc = info.getContactWithContactType("Customer", 2);
		assertNotNull("cc", cc);
		cc = info.getContactWithContactType("Customer", 1);
		assertNotNull("cc", cc);
		JDFContact cc2 = info.getContactWithContactType("Administrator", 0);
		assertNotNull("cc2", cc2);
		assertEquals("cc2", cc, cc2);
		cc = info.getContactWithContactType("Delivery", 0);
		assertNotNull("cc", cc);
		cc = info.getContactWithContactType("fnarf", 0);
		assertNull("cc", cc);
	}

	// ///////////////////////////////////////////////////////////////////////

	public void testGetContactVectorWithContactType()
	{
		JDFDoc doc = new JDFDoc("JDF");
		JDFCustomerInfo info = prepareInfo(doc);

		VElement v = info.getContactVectorWithContactType("Customer");
		assertNotNull(v);
		assertEquals(v.size(), 3);
		v = info.getContactVectorWithContactType("Administrator");
		assertNotNull(v);
		assertEquals(v.size(), 1);
		v = info.getContactVectorWithContactType("beagle");
		assertNull(v);
	}

	// ///////////////////////////////////////////////////////////////////////

	private JDFCustomerInfo prepareInfo(JDFDoc doc)
	{
		JDFNode n = doc.getJDFRoot();
		JDFCustomerInfo info = n.appendCustomerInfo();
		VString vct = new VString();
		vct.add("Customer");
		info.appendContact().setContactTypes(vct);
		vct.add("Administrator");
		info.appendContact().setContactTypes(vct);
		JDFContact c = info.appendContact();
		vct = new VString();
		vct.add("Delivery");
		c.setContactTypes(vct);
		c.makeRootResource(null, null, true);
		vct.add("Customer");
		info.appendContact().setContactTypes(vct);
		return info;
	}

	// ///////////////////////////////////////////////////////////////////////

	public void testGetContact()
	{
		JDFDoc doc = new JDFDoc("JDF");
		JDFNode n = doc.getJDFRoot();
		JDFCustomerInfo info = n.appendCustomerInfo();
		info.appendContact().setContactTypes(new VString("foo", null));
		assertNotNull(info.getContact(0));
	}

}
