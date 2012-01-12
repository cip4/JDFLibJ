/*
 *
 * The CIP4 Software License, Version 1.0
 *
 *
 * Copyright (c) 2001-2011 The International Cooperation for the Integration of 
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
package org.cip4.jdflib.jmf;

import org.cip4.jdflib.JDFTestCaseBase;
import org.cip4.jdflib.core.ElementName;
import org.cip4.jdflib.core.JDFDoc;
import org.cip4.jdflib.core.JDFElement.EnumValidationLevel;
import org.cip4.jdflib.jmf.JDFMessage.EnumType;
import org.cip4.jdflib.resource.JDFResource.EnumResStatus;
import org.cip4.jdflib.resource.process.JDFMedia;

/**
 * @author Rainer Prosi
 * 
 *         Test of the ResourceInfo JMF element
 */
public class JMFResourceInfoTest extends JDFTestCaseBase
{
	private JDFResourceInfo ri;
	private JDFResponse r;

	/**
	 * 
	 */
	public void testGetResource()
	{
		assertNull(ri.getResource(ElementName.MEDIA));
		JDFMedia m = (JDFMedia) ri.appendResource(ElementName.MEDIA);
		JDFMedia m2 = (JDFMedia) ri.getResource(ElementName.MEDIA);
		assertEquals(m, m2);
		assertTrue(ri.isValid(EnumValidationLevel.Complete));
	}

	// ///////////////////////////////////////////////////////////////////

	/**
	 * 
	 */
	public void testGetResourceNull()
	{
		JDFMedia m = (JDFMedia) ri.appendResource(ElementName.MEDIA);
		JDFMedia m2 = (JDFMedia) ri.getResource(null);
		assertEquals(m, m2);
		assertTrue(ri.isValid(EnumValidationLevel.Complete));
	}

	// ///////////////////////////////////////////////////////////////////
	/**
	 * 
	 */
	public void testGetResourceName()
	{
		// JDFMedia m=(JDFMedia)
		ri.appendResource(ElementName.MEDIA);
		String name = ri.getResourceName();
		assertEquals(name, ElementName.MEDIA);
	}

	// ///////////////////////////////////////////////////////////////////
	/**
	 * 
	 */
	public void testGetResourceID()
	{
		JDFMedia m = (JDFMedia) ri.appendResource(ElementName.MEDIA);
		String name = ri.getResourceID();
		assertEquals(name, m.getID());
	}

	// //////////////////////////////////////////////////////////////////////
	/**
	 * 
	 */
	public void testGetProductID()
	{
		JDFMedia m = (JDFMedia) ri.appendResource(ElementName.MEDIA);
		m.setProductID("p1");
		String name = ri.getProductID();
		assertEquals(name, m.getProductID());
	}

	// ///////////////////////////////////////////////////////////////////
	/**
	 * 
	 */
	public void testGetResStatus()
	{
		JDFMedia m = (JDFMedia) ri.appendResource(ElementName.MEDIA);
		EnumResStatus name = ri.getResStatus();
		assertEquals(name, m.getResStatus(false));
	}

	// ///////////////////////////////////////////////////////////////////

	/**
	 *  
	 * 
	 * @see org.cip4.jdflib.JDFTestCaseBase#setUp()
	 */
	@Override
	protected void setUp() throws Exception
	{
		super.setUp();
		JDFDoc doc = new JDFDoc(ElementName.JMF);
		JDFJMF jmf = doc.getJMFRoot();

		r = jmf.appendResponse();
		jmf.setSenderID("DeviceSenderID");
		r.setType(EnumType.Resource);
		ri = r.getCreateResourceInfo(0);
	}

	/**
	 * 
	 * test the matches algorithm for various data types
	 */
	public void testMatches()
	{
		ri.appendResource("Media");

		assertTrue(ri.matches(null));
		assertTrue(ri.matches("Media"));
		assertTrue(ri.matches(ri.getResource("Media")));
		assertFalse(ri.matches("Ink"));

		JDFResourceQuParams rqp = (JDFResourceQuParams) new JDFDoc(ElementName.RESOURCEQUPARAMS).getRoot();
		assertTrue(ri.matches(rqp));
		rqp.setResourceName("Media");
		assertTrue(ri.matches(rqp));
		rqp.setResourceName("Ink");
		assertFalse(ri.matches(rqp));
	}
}