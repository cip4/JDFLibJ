/*
 * The CIP4 Software License, Version 1.0
 *
 *
 * Copyright (c) 2001-2024 The International Cooperation for the Integration of
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
package org.cip4.jdflib.resource.process;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.cip4.jdflib.JDFTestCaseBase;
import org.cip4.jdflib.auto.JDFAutoComChannel.EnumChannelType;
import org.cip4.jdflib.core.ElementName;
import org.cip4.jdflib.core.JDFDoc;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**
 * @author prosirai
 *
 */
class JDFPersonTest extends JDFTestCaseBase
{
	private JDFPerson person;

	/**
	 *
	 *
	 */
	@Test
	void testGetComChannelPerson()
	{
		final JDFComChannel a = person.appendComChannel(EnumChannelType.Phone, "1234");
		assertEquals(a, person.getComChannel(EnumChannelType.Phone));
		assertEquals(null, person.getComChannel(EnumChannelType.Mobile));
	}

	/**
	 *
	 */
	@Test
	void testFamilyName()
	{
		person.setFamilyName("Müller");
		assertEquals(person.getFamilyName(), "Müller");
		assertEquals(person.getDescriptiveName(), "Müller");
		person.setFamilyName("Meyer");
		assertEquals(person.getFamilyName(), "Meyer");
		assertEquals(person.getDescriptiveName(), "Meyer");
		person.setFamilyName("Müller");
		assertEquals(person.getFamilyName(), "Müller");
		assertEquals(person.getDescriptiveName(), "Müller");
	}

	/**
	 *
	 */
	@Test
	void testFirstName()
	{
		person.setFirstName("Joe");
		assertEquals(person.getFirstName(), "Joe");
		assertEquals(person.getDescriptiveName(), "Joe");
		person.setFirstName("Mary");
		assertEquals(person.getFirstName(), "Mary");
		assertEquals(person.getDescriptiveName(), "Mary");
		person.setFirstName("Joe");
		assertEquals(person.getFirstName(), "Joe");
		assertEquals(person.getDescriptiveName(), "Joe");
	}

	/**
	 *
	 */
	@Test
	void testFirstLastName()
	{
		testFirstName();
		person.setFamilyName("M�ller");
		assertEquals(person.getFamilyName(), "M�ller");
		assertEquals(person.getDescriptiveName(), "Joe M�ller");
		person.setFirstName("Mary");
		assertEquals(person.getFirstName(), "Mary");
		assertEquals(person.getDescriptiveName(), "Mary M�ller");
		person.setFamilyName("Meyer");
		assertEquals(person.getFamilyName(), "Meyer");
		assertEquals(person.getDescriptiveName(), "Mary Meyer");
		person.setFamilyName("Meyer");
		assertEquals(person.getFamilyName(), "Meyer");
		assertEquals(person.getDescriptiveName(), "Mary Meyer");
		person.setFamilyName("Schmidt");
		assertEquals(person.getFamilyName(), "Schmidt");
		assertEquals(person.getDescriptiveName(), "Mary Schmidt");
		person.setFamilyName(null);
		assertFalse(person.hasAttribute("FamilyName"));
		assertEquals(person.getDescriptiveName(), "Mary Schmidt");
	}

	/**
	 *
	 */
	@Test
	void testLastFirstName()
	{
		testFamilyName();
		person.setFirstName("Joe");
		assertEquals(person.getFirstName(), "Joe");
		assertEquals(person.getDescriptiveName(), "Joe Müller");
	}

	/**
	*
	*/
	@Test
	void testPhoneticFirstName()
	{
		testFamilyName();
		person.setPhoneticFirstName("Joe");
		assertEquals(person.getPhoneticFirstName(), "Joe");
	}

	/**
	*
	*/
	@Test
	void testPhoneticLastName()
	{
		testFamilyName();
		person.setPhoneticLastName("Joe");
		assertEquals(person.getPhoneticLastName(), "Joe");
	}

	/**
	 *
	 */
	@Test
	void testKeepDescName()
	{
		person.setDescriptiveName("foo");
		person.setFirstName("Joe");
		assertEquals(person.getFirstName(), "Joe");
		assertEquals(person.getDescriptiveName(), "foo", "no overwrite of non-matching name");
	}

	/**
	 *
	 */
	@Test
	void testGetDescName()
	{
		person.setFirstName("Joe");
		assertEquals(person.getDescriptiveName(), "Joe");
		person.setFamilyName("Cool");
		assertEquals(person.getDescriptiveName(), "Joe Cool", "create correct descname");
		person.setFirstName(null);
		assertEquals(person.getDescriptiveName(), "Cool", "create correct descname");
		person.setNamePrefix("Prof.");
		person.setFirstName("Joe");
		assertEquals(person.getDescriptiveName(), "Prof. Joe Cool", "create correct descname");
		person.setNameSuffix("IV");
		assertEquals(person.getDescriptiveName(), "Prof. Joe Cool IV", "create correct descname");
	}

	/**
	 *
	 */
	@Test
	void testGetProductID()
	{
		person.setProductID("p1");
		assertEquals(person.getProductID(), "p1");
	}

	/**
	 *
	 * @see JDFTestCaseBase#setUp()
	 */
	@Override
	@BeforeEach
	public void setUp() throws Exception
	{
		super.setUp();
		person = (JDFPerson) new JDFDoc(ElementName.PERSON).getRoot();
	}

	/**
	 *
	 *
	 */
	@Test
	final void testMatches()
	{
		final JDFDoc doc = new JDFDoc("Person");
		final JDFPerson c = (JDFPerson) doc.getRoot();
		final JDFPerson c2 = (JDFPerson) new JDFDoc("Person").getRoot();
		assertTrue(c.matches(c2));
		c.setFirstName("foo");
		assertTrue(c.matches(c2));
		c2.setFirstName("Foo");
		assertTrue(c.matches(c));
		assertTrue(c.matches("foo"));
		c2.setFamilyName("bar");
		assertFalse(c.matches(c2));
		c.setFamilyName("bar");
		assertTrue(c.matches("foo bar"));
	}
}
