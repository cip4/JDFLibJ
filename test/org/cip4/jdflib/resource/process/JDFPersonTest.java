/*
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
package org.cip4.jdflib.resource.process;

import org.cip4.jdflib.JDFTestCaseBase;
import org.cip4.jdflib.core.JDFDoc;

/**
 * @author prosirai
 * 
 */
public class JDFPersonTest extends JDFTestCaseBase
{
	JDFPerson person;

	public void testFamilyName() throws Exception
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

	public void testFirstName() throws Exception
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

	public void testFirstLastName() throws Exception
	{
		testFirstName();
		person.setFamilyName("Müller");
		assertEquals(person.getFamilyName(), "Müller");
		assertEquals(person.getDescriptiveName(), "Joe Müller");
		person.setFirstName("Mary");
		assertEquals(person.getFirstName(), "Mary");
		assertEquals(person.getDescriptiveName(), "Mary Müller");
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

	public void testLastFirstName() throws Exception
	{
		testFamilyName();
		person.setFirstName("Joe");
		assertEquals(person.getFirstName(), "Joe");
		assertEquals(person.getDescriptiveName(), "Joe Müller");
	}

	public void testKeepDescName() throws Exception
	{
		person.setDescriptiveName("foo");
		person.setFirstName("Joe");
		assertEquals(person.getFirstName(), "Joe");
		assertEquals("no overwrite of non-matching name", person
				.getDescriptiveName(), "foo");

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.cip4.jdflib.JDFTestCaseBase#setUp()
	 */
	protected void setUp() throws Exception
	{
		// TODO Auto-generated method stub
		super.setUp();
		JDFDoc d = new JDFDoc("Person");
		person = (JDFPerson) d.getRoot();
	}
}
