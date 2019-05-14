/*
 *
 * The CIP4 Software License, Version 1.0
 *
 *
 * Copyright (c) 2001-2019 The International Cooperation for the Integration of Processes in Prepress, Press and Postpress (CIP4). All rights reserved.
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
/**
 * JDFIntegerRangeListTest.java
 *
 * @author Elena Skobchenko
 *
 *         Copyright (c) 2001-2004 The International Cooperation for the Integration of Processes in Prepress, Press and Postpress (CIP4). All rights reserved.
 */
package org.cip4.jdflib.datatypes;

import static org.junit.Assert.assertEquals;

import java.util.zip.DataFormatException;

import org.cip4.jdflib.JDFTestCaseBase;
import org.junit.Test;

/**
 * @author Dr. Rainer Prosi, Heidelberger Druckmaschinen AG
 *
 *         prior to May 7, 2009
 */
public class JDFIntegerListTest extends JDFTestCaseBase
{

	/**
	 *
	 */
	@Test
	public void testConstruct()
	{
		final JDFIntegerList l = new JDFIntegerList(3);
		assertEquals(l.size(), 1);
		assertEquals(l.getInt(0), 3);
	}

	/**
	 *
	 */
	@Test
	public void testSetInt()
	{
		final JDFIntegerList l = new JDFIntegerList(3);
		assertEquals(l.size(), 1);
		assertEquals(l.getInt(0), 3);
		l.setInt(0, 2);
		assertEquals(l.getInt(0), 2);
		assertEquals(l.size(), 1);
		l.setInt(1, 4);
		assertEquals(l.getInt(1), 4);
		l.setInt(-1, 3);
		assertEquals(l.getInt(1), 3);
	}

	/**
	 *
	 */
	@Test
	public void testSort()
	{
		final JDFIntegerList l = new JDFIntegerList(3);
		l.setInt(0, 2);
		l.setInt(1, 4);
		l.setInt(2, 3);
		l.sort();
		assertEquals(l.get(0), 2);
		assertEquals(l.get(1), 3);
		assertEquals(l.get(2), 4);
	}

	/**
	*
	*/
	@Test
	public void testUnify()
	{
		final JDFIntegerList l = new JDFIntegerList(3);
		l.setIntX(0, 2).setIntX(1, 2).setInt(2, 2);
		l.unify();
		assertEquals(l.get(0), 2);
		assertEquals(1, l.size());
	}

	/**
	*
	*/
	@Test
	public void testAdd()
	{
		final JDFIntegerList l = new JDFIntegerList(3);
		l.addX(4).add(5);
		assertEquals(4, l.get(1));
		assertEquals(3, l.size());
	}

	/**
	*
	*/
	@Test
	public void testShift()
	{
		final JDFIntegerList l = new JDFIntegerList(1);
		l.addX(4).shift(10);
		assertEquals(11, l.get(0));
		assertEquals(14, l.get(1));
		assertEquals(2, l.size());
	}

	/**
	 * @throws DataFormatException
	 *
	 */
	@Test
	public void testSubtract() throws DataFormatException
	{
		final JDFIntegerList l = new JDFIntegerList(3);
		l.add(4);
		final JDFIntegerList l2 = new JDFIntegerList(l);
		l.subtract(l2);
		assertEquals(0, l.get(0));
		assertEquals(0, l.get(1));
		assertEquals(2, l.size());

	}
}
