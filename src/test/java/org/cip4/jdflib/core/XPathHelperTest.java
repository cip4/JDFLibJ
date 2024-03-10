/**
 * The CIP4 Software License, Version 1.0
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
package org.cip4.jdflib.core;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Map;

import org.cip4.jdflib.JDFTestCaseBase;
import org.cip4.jdflib.auto.JDFAutoComChannel.EnumChannelType;
import org.cip4.jdflib.datatypes.JDFAttributeMap;
import org.cip4.jdflib.resource.process.JDFAddress;
import org.cip4.jdflib.resource.process.JDFContact;
import org.cip4.jdflib.resource.process.JDFContact.EnumContactType;
import org.cip4.jdflib.resource.process.JDFPerson;
import org.cip4.jdflib.util.CPUTimer;
import org.cip4.jdflib.util.StatusCounterTest;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

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
	void testSetNull()
	{
		theHelper.setXPathAttribute("@bar", null);
		assertFalse(theHelper.hasXPathNode("@bar"));
	}

	/**
	 *
	 *
	 */
	void testSetNamespaces()
	{
		theHelper.setXPathAttribute("@foo:bar", "b1");
		theHelper.setXPathAttribute("@xmlns:foo", "www.foo.com");
		theHelper.setXPathAttribute("@foo:bar2", "b2");
		assertFalse(theHelper.hasXPathNode("@bar"));
		assertFalse(theHelper.hasXPathNode("@bar2"));
	}

	/**
	 * @see JDFTestCaseBase#setUp()
	 */
	@Override
	@BeforeEach
	public void setUp() throws Exception
	{
		super.setUp();
		this.theHelper = new XPathHelper(KElement.createRoot("foo", null));
	}

	/**
	 *
	 */
	@Test
	void testSetXPathValuesPerformance()
	{
		for (int j = 0; j < 1; j++)
		{
			final CPUTimer ct1 = new CPUTimer(true);
			final CPUTimer ct = new CPUTimer(false);
			final JDFDoc d = new JDFParser().parseFile(sm_dirTestData + "bigWhite.jdf");
			final JDFAttributeMap map = d.getRoot().getXPathValueMap();
			log.info("Read " + ct1.getSingleSummary());

			for (int i = 0; i < 1; i++)
			{
				ct.start();
				final JDFDoc dNew = new JDFDoc(ElementName.JDF);
				dNew.setXPathValues(map);
				log.info(i + " " + ct.getSingleSummary());
				ct.stop();
			}
		}
	}

	/**
	 *
	 */
	@Test
	void testSetXPathValuesCustomerPerformance()
	{
		final CPUTimer ct = new CPUTimer(false);
		final JDFCustomerInfo ci = (JDFCustomerInfo) new JDFDoc(ElementName.CUSTOMERINFO).getRoot();
		for (int i = 0; i < 200; i++)
		{
			final JDFContact c = ci.appendContact();
			c.setContactTypes(EnumContactType.Approver);

			final JDFAddress address = c.appendAddress();
			address.setStreet("street" + i);
			address.setCity("city" + i);
			address.setPostalCode("p" + i);
			address.setCountry("country");

			for (int j = 0; j < 3; j++)
			{
				c.appendComChannel(EnumChannelType.getEnum(j)).setLocator("loc" + i + " " + j);
			}
			final JDFPerson p = c.appendPerson();
			p.setFirstName("Name" + i);
			p.setFamilyName("Fam" + i);
		}
		final CPUTimer ct1 = new CPUTimer(true);
		final JDFAttributeMap map = ci.getXPathValueMap();
		log.info("Read " + ct1.getSingleSummary());

		ct.start();
		final JDFDoc dNew = new JDFDoc(ElementName.CUSTOMERINFO);
		dNew.getRoot().setXPathValues(map);
		log.info(" set " + ct.getSingleSummary());
		ct.stop();
		assertTrue(ct.getTotalRealTime() < 12345);
	}

	/**
	*
	*/
	@Test
	void testSetXPathValuesJMFPerformance()
	{
		final CPUTimer ct = new CPUTimer(false);
		final JDFDoc d = StatusCounterTest.getJMF();

		for (int i = 0; i < 10000; i++)
		{
			ct.start();
			final JDFAttributeMap map = d.getRoot().getXPathValueMap();
			final JDFDoc dNew = new JDFDoc();
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
	void testSetXPathValues()
	{
		final JDFDoc d = creatXMDoc();
		final KElement root = d.getRoot();
		final JDFAttributeMap map = root.getXPathValueMap();

		final JDFDoc dNew = new JDFDoc("JDF");
		dNew.setXPathValues(map);
		assertTrue(dNew.getRoot().getLocalName().equals("JDF"));
	}

	/**
	 *
	 */
	@Test
	void testSetXPathValuesEmptyElem()
	{
		final XMLDoc d = new XMLDoc("x", null);
		d.getRoot().appendElement("y");
		final KElement root = d.getRoot();
		final JDFAttributeMap map = root.getXPathValueMap();

		final XMLDoc dNew = new XMLDoc();
		dNew.setXPathValues(map);
		assertTrue(dNew.getRoot().isEqual(root));
	}

	/**
	 *
	 */
	@Test
	void testRemoveXPathElem()
	{
		final XMLDoc d = new XMLDoc("x", null);
		final KElement root = d.getRoot();
		final KElement y = root.appendElement("y");
		y.setAttribute("a", "b");
		assertEquals(y, root.getXPathElement("y[@a=\"b\"]"));
		root.removeXPathElement("y[@a=\"b\"]");
		assertNull(root.getXPathElement("y[@a=\"b\"]"));
	}

	/**
	 *
	 */
	@Test
	void testSetXPathValuesNSElem()
	{
		final XMLDoc d = new XMLDoc("x", null);
		final KElement nsElem = d.getRoot().appendElement("ns:y", "foo");
		nsElem.setAttribute("ns:bar", "barbar", "foo");
		final KElement root = d.getRoot();
		final JDFAttributeMap map = root.getXPathValueMap();

		final XMLDoc dNew = new XMLDoc();
		dNew.setXPathValues(map);
		assertEquals(dNew.getRoot().toXML(), root.toXML());
		final String s = dNew.toXML();
		final XMLDoc dParsed = new XMLParser().parseString(s);
		final KElement root2 = dParsed.getRoot();
		final JDFAttributeMap map2 = root2.getXPathValueMap();
		final XMLDoc dNew2 = new XMLDoc();
		dNew2.setXPathValues(map2);
		assertEquals(dNew2.getRoot().toXML(), root.toXML());
	}

	/**
	 *
	 */
	@Test
	void testSetXPathValuesNSAttrib()
	{
		final XMLDoc d = new XMLDoc("x", null);
		final KElement nsElem = d.getRoot().appendElement("nons");
		nsElem.setAttribute("ns:bar", "barbar", "foo");
		final KElement root = d.getRoot();
		final JDFAttributeMap map = root.getXPathValueMap();

		final XMLDoc dNew = new XMLDoc();
		dNew.setXPathValues(map);
		assertEquals(dNew.getRoot().toXML(), root.toXML());

		final String s = dNew.toXML();

		final XMLDoc dParsed = new XMLParser().parseString(s);
		final KElement root2 = dParsed.getRoot();
		final JDFAttributeMap map2 = root2.getXPathValueMap();
		final XMLDoc dNew2 = new XMLDoc();
		dNew2.setXPathValues(map2);
		assertEquals(dNew2.getRoot().toXML(), root.toXML());
	}

	/**
	 *
	 */
	@Test
	void testGetXPathValueMapNotRoot()
	{
		final XMLDoc jdfDoc = new XMLDoc("a", null);
		final KElement root = jdfDoc.getRoot();
		final KElement b = root.appendElement("bbb");
		b.setXPathAttribute("b/c[3]/d/@foo", "bar3");
		b.setXPathAttribute("b/c[5]/d/@foo", "bar5");
		b.setAttribute("test", "it");
		final Map<String, String> m = b.getXPathValueMap(true);
		assertEquals(m.get("./b[1]/c[3]/d[1]/@foo"), "bar3");
	}

	/**
	 *
	 */
	@Test
	void testGetXPathValueMapNotRootset()
	{
		final XMLDoc jdfDoc = new XMLDoc("a", null);
		final KElement root = jdfDoc.getRoot();
		final KElement b = root.appendElement("bbb");
		b.setXPathAttribute("b/c[3]/d/@foo", "bar3");
		b.setXPathAttribute("b/c[5]/d/@foo", "bar5");
		b.setAttribute("test", "it");
		final JDFAttributeMap m = b.getXPathValueMap(true);
		assertEquals(m.get("./b[1]/c[3]/d[1]/@foo"), "bar3");
		KElement b2 = root.appendElement("bbb");
		b2.setXPathValues(m);
		assertTrue(b.isEqual(b2));
	}

}
