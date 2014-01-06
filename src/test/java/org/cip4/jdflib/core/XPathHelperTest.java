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
package org.cip4.jdflib.core;

import org.cip4.jdflib.JDFTestCaseBase;
import org.cip4.jdflib.datatypes.JDFAttributeMap;
import org.cip4.jdflib.util.CPUTimer;
import org.cip4.jdflib.util.StatusCounterTest;
import org.junit.Test;

/**
 * 
 * @author rainer prosi
 * @date Dec 23, 2012
 */
public class XPathHelperTest extends JDFTestCaseBase
{
	private XPathHelper theHelper;

	/**
	 * 
	 * 
	 */
	public void testSetNull()
	{
		theHelper.setXPathAttribute("@bar", null);
		assertFalse(theHelper.hasXPathNode("@bar"));
	}

	/**
	 * 
	 * 
	 */
	public void testSetNamespaces()
	{
		theHelper.setXPathAttribute("@foo:bar", "b1");
		theHelper.setXPathAttribute("@xmlns:foo", "www.foo.com");
		theHelper.setXPathAttribute("@foo:bar2", "b2");
		assertFalse(theHelper.hasXPathNode("@bar"));
		assertFalse(theHelper.hasXPathNode("@bar2"));
	}

	/**
	 * @see org.cip4.jdflib.JDFTestCaseBase#setUp()
	 */
	@Override
	protected void setUp() throws Exception
	{
		super.setUp();
		this.theHelper = new XPathHelper(KElement.createRoot("foo", null));
	}

	/**
	 * 
	 */
	@Test
	public void testSetXPathValuesPerformance()
	{
		CPUTimer ct = new CPUTimer(false);
		JDFDoc d = new JDFParser().parseFile(sm_dirTestData + "bigWhite.jdf");
		JDFAttributeMap map = d.getRoot().getXPathValueMap();

		for (int i = 0; i < 3; i++)
		{
			ct.start();
			JDFDoc dNew = new JDFDoc("JDF");
			dNew.setXPathValues(map);
			log.info(i + " " + ct.getSingleSummary());
			ct.stop();
		}
	}

	/**
	* 
	*/
	@Test
	public void testSetXPathValuesJMFPerformance()
	{
		CPUTimer ct = new CPUTimer(false);
		JDFDoc d = StatusCounterTest.getJMF();

		for (int i = 0; i < 30000; i++)
		{
			ct.start();
			JDFAttributeMap map = d.getRoot().getXPathValueMap();
			JDFDoc dNew = new JDFDoc();
			dNew.setXPathValues(map);
			if (i % 100 == 0)
				log.info(i + " " + ct.getSingleSummary());
			ct.stop();
		}
		assertEquals(ct.getTotalCPUTime(), 20.0E9, 20.0E9);
	}

	/**
	 * 
	 */
	@Test
	public void testSetXPathValues()
	{
		JDFDoc d = creatXMDoc();
		KElement root = d.getRoot();
		JDFAttributeMap map = root.getXPathValueMap();

		JDFDoc dNew = new JDFDoc("JDF");
		dNew.setXPathValues(map);
		assertTrue(dNew.getRoot().getLocalName().equals("JDF"));
	}

	/**
	 * 
	 */
	@Test
	public void testSetXPathValuesEmptyElem()
	{
		XMLDoc d = new XMLDoc("x", null);
		d.getRoot().appendElement("y");
		KElement root = d.getRoot();
		JDFAttributeMap map = root.getXPathValueMap();

		XMLDoc dNew = new XMLDoc();
		dNew.setXPathValues(map);
		assertTrue(dNew.getRoot().isEqual(root));
	}

	/**
	 * 
	 */
	@Test
	public void testSetXPathValuesNSElem()
	{
		XMLDoc d = new XMLDoc("x", null);
		KElement nsElem = d.getRoot().appendElement("ns:y", "foo");
		nsElem.setAttribute("ns:bar", "barbar", "foo");
		KElement root = d.getRoot();
		JDFAttributeMap map = root.getXPathValueMap();

		XMLDoc dNew = new XMLDoc();
		dNew.setXPathValues(map);
		assertTrue(dNew.getRoot().isEqual(root));
	}
}
