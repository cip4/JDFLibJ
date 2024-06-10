/*
 *
 * The CIP4 Software License, Version 1.0
 *
 *
 * Copyright (c) 2001-2016 The International Cooperation for the Integration of
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
import org.cip4.jdflib.core.ElementName;
import org.cip4.jdflib.core.JDFDoc;
import org.cip4.jdflib.jmf.JDFEmployeeDef;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

/**
 * tests the JDFEmployee class
 * @author Dr. Rainer Prosi, Heidelberger Druckmaschinen AG
 *
 * before June 4, 2009
 */
class JDFEmployeeTest extends JDFTestCaseBase
{
	/**
	 *
	 */
	@Test
	public final void testMatchesEmployee()
	{
		final JDFEmployee emp = (JDFEmployee) new JDFDoc(ElementName.EMPLOYEE).getRoot();
		final JDFEmployee emp2 = (JDFEmployee) new JDFDoc(ElementName.EMPLOYEE).getRoot();
		Assertions.assertTrue(emp.matches(null));
		emp.setPersonalID("p1");
		Assertions.assertFalse(emp.matches(emp2));
		emp2.setPersonalID("p1");
		Assertions.assertTrue(emp.matches(emp2));
		emp2.setPersonalID("p2");
		Assertions.assertFalse(emp.matches(emp2));
	}

	/**
	 *
	 */
	@Test
	public final void testMatchesPersonalID()
	{
		final JDFEmployee emp = (JDFEmployee) new JDFDoc(ElementName.EMPLOYEE).getRoot();
		Assertions.assertTrue(emp.matches(null));
		Assertions.assertFalse(emp.matches("p2"));
		emp.setPersonalID("p1");
		Assertions.assertFalse(emp.matches("p2"));
		Assertions.assertTrue(emp.matches("p1"));
	}

	/**
	 * tests the matches case
	 *
	 */
	@Test
	public final void testMatchesEmployeeDef()
	{
		final JDFEmployee emp = (JDFEmployee) new JDFDoc(ElementName.EMPLOYEE).getRoot();
		final JDFEmployeeDef emp2 = (JDFEmployeeDef) new JDFDoc(ElementName.EMPLOYEEDEF).getRoot();
		Assertions.assertTrue(emp.matches(null));
		emp.setPersonalID("p1");
		Assertions.assertFalse(emp.matches(emp2));
		emp2.setPersonalID("p1");
		Assertions.assertTrue(emp.matches(emp2));
		emp2.setPersonalID("p2");
		Assertions.assertFalse(emp.matches(emp2));
	}

	/**
	 *
	 */
	void testGetDescriptiveName()
	{
		final JDFEmployee emp = (JDFEmployee) new JDFDoc(ElementName.EMPLOYEE).getRoot();
		JDFPerson p = emp.appendPerson();
		p.setFirstName("a");
		p.setFamilyName("b");
		Assertions.assertEquals(emp.getDescriptiveName(), "a b");
		emp.setDescriptiveName("aaa");
		Assertions.assertEquals(emp.getDescriptiveName(), "aaa");
	}

	/**
	 *
	 */
	@Test
	void testGetDescriptiveNamePID()
	{
		final JDFEmployee emp = (JDFEmployee) new JDFDoc(ElementName.EMPLOYEE).getRoot();
		emp.setProductID("aaa");
		Assertions.assertEquals(emp.getDescriptiveName(), "aaa");
		emp.setPersonalID("aaaa");
		Assertions.assertEquals(emp.getDescriptiveName(), "aaaa");
	}

	/**
	 *
	 */
	@Test
	void testGetPersonalID()
	{
		final JDFEmployee emp = (JDFEmployee) new JDFDoc(ElementName.EMPLOYEE).getRoot();
		emp.setProductID("P1");
		Assertions.assertEquals(emp.getPersonalID(), "P1");
		emp.setPersonalID("P2");
		Assertions.assertEquals(emp.getPersonalID(), "P2");
	}

	/**
	 *
	 */
	@Test
	void testFixVerion()
	{
		final JDFEmployee emp = (JDFEmployee) new JDFDoc(ElementName.EMPLOYEE).getRoot();
		emp.setPersonalID("p1");
		emp.fixVersion(null);
		Assertions.assertEquals(emp.getProductID(), "p1");
		emp.setPersonalID("p2");
		emp.fixVersion(null);
		Assertions.assertEquals(emp.getProductID(), "p1");

	}
}
