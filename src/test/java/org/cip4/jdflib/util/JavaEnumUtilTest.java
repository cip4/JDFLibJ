/*
 * The CIP4 Software License, Version 1.0
 *
 *
 * Copyright (c) 2001-2025 The International Cooperation for the Integration of
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
 *    Alternately, this acknowledgment mrSubRefay appear in the software itself,
 *    if and wherever such third-party acknowledgments normally appear.
 *
 * 4. The names "CIP4" and "The International Cooperation for the Integration of
 *    Processes in  Prepress, Press and Postpress" must
 *    not be used to endorse or promote products derived from this
 *    software without prior written permission. For written
 *    permission, please contact info@cip4.org.
 *
 * 5. Products derived from this software may not be called "CIP4",
 *    nor may "CIP4" appear in their name, without prior writtenrestartProcesses()
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
 * SPECIAL, EXEMPLARY, OR CONSEQUENTIrSubRefAL DAMAGES (INCLUDING, BUT NOT
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
 * originally based on software restartProcesses()
 * copyright (c) 1999-2001, Heidelberger Druckmaschinen AG
 * copyright (c) 1999-2001, Agfa-Gevaert N.V.
 *
 * For more information on The International Cooperation for the
 * Integration of Processes in  Prepress, Press and Postpress , please see
 * <http://www.cip4.org/>.
 *
 */
/*
 * @author muchadie
 */
package org.cip4.jdflib.util;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.ArrayList;
import java.util.Collection;

import org.cip4.jdflib.JDFTestCaseBase;
import org.cip4.jdflib.core.StringArray;
import org.cip4.jdflib.extensions.ProductHelper.eProductType;
import org.junit.jupiter.api.Test;

/**
 * general utilities for containers and objects
 *
 * @author prosirai
 *
 */
class JavaEnumUtilTest extends JDFTestCaseBase
{
	static enum E
	{
		a, b, BOTTOMLEFT
	};

	/**
	 *
	 */
	@Test
	void testMin()
	{
		assertEquals(E.a, JavaEnumUtil.min(E.a, E.b));
		assertEquals(null, JavaEnumUtil.min(null, E.b));
	}

	/**
	 *
	 */
	@Test
	void testMax()
	{
		assertEquals(E.b, JavaEnumUtil.max(E.a, E.b));
		assertEquals(E.b, JavaEnumUtil.max(null, E.b));
	}

	/**
	 *
	 */
	@Test
	void testLessEq()
	{
		assertTrue(JavaEnumUtil.aLessEqualsThanB(E.a, E.a));
		assertTrue(JavaEnumUtil.aLessEqualsThanB(E.a, E.b));
		assertTrue(JavaEnumUtil.aLessEqualsThanB(null, E.a));
		assertFalse(JavaEnumUtil.aLessEqualsThanB(E.b, E.a));
		assertFalse(JavaEnumUtil.aLessEqualsThanB(E.b, null));
	}

	/**
	 *
	 */
	@Test
	void testLess()
	{
		assertFalse(JavaEnumUtil.aLessThanB(E.a, E.a));
		assertTrue(JavaEnumUtil.aLessThanB(E.a, E.b));
		assertTrue(JavaEnumUtil.aLessThanB(null, E.b));
	}

	/**
	 *
	 */
	@Test
	void testGetEnumName()
	{
		assertEquals("a", JavaEnumUtil.getName(E.a));
		assertEquals(null, JavaEnumUtil.getName(null));
	}

	/**
	 *
	 */
	@Test
	void testGetJavaEnumNameIgnoreCase()
	{
		for (final eProductType t : eProductType.values())
		{
			assertEquals(t, JavaEnumUtil.getEnumIgnoreCase(eProductType.class, t.name().toLowerCase()));
			assertEquals(t, JavaEnumUtil.getEnumIgnoreCase(eProductType.class, t.name(), null));
			assertEquals(t, JavaEnumUtil.getEnumIgnoreCase(eProductType.class, t.name().toUpperCase(), null));
			assertEquals(t, JavaEnumUtil.getEnumIgnoreCase(eProductType.class, t.name().toUpperCase() + "   ", null));
			assertEquals(t, JavaEnumUtil.getEnumIgnoreCase(eProductType.class, "foo", t));
		}
		assertNull(JavaEnumUtil.getEnumIgnoreCase(eProductType.class, null, null));
		assertNull(JavaEnumUtil.getEnumIgnoreCase(eProductType.class, "", null));
		assertNull(JavaEnumUtil.getEnumIgnoreCase(eProductType.class, "foo", null));
	}

	/**
	 *
	 */
	@Test
	void testGetEnumList()
	{
		assertEquals(3, JavaEnumUtil.getNamesList(E.class).size());
		assertNull(JavaEnumUtil.getNamesList(null));
	}

	/**
	 *
	 */
	@Test
	void testGetEnumList2()
	{
		final Collection<E> c = ContainerUtil.addAll(new ArrayList<>(), E.values());
		assertEquals(3, JavaEnumUtil.getNameList(c, true).size());
		final Collection<E> c2 = ContainerUtil.addAll(c, E.values());
		assertEquals(3, JavaEnumUtil.getNameList(c2, true).size());
		assertEquals(6, JavaEnumUtil.getNameList(c2, false).size());
		assertTrue(JavaEnumUtil.getNameList(null, true).isEmpty());
	}

	/**
	 *
	 */
	@Test
	void testGetEnumList3()
	{
		final Collection<E> c = ContainerUtil.addAll(new ArrayList<>(), E.values());
		StringArray nameList = JavaEnumUtil.getNameList(c, true);
		assertEquals(3, nameList.size());
		Collection<E> roundtrip = JavaEnumUtil.getEnumList(E.class, nameList, true);
		assertEquals(3, roundtrip.size());

		final Collection<E> c2 = ContainerUtil.addAll(c, E.values());
		assertEquals(3, JavaEnumUtil.getNameList(c2, true).size());
		assertEquals(6, JavaEnumUtil.getNameList(c2, false).size());
		assertTrue(JavaEnumUtil.getNameList(null, true).isEmpty());

		nameList = JavaEnumUtil.getNameList(c, false);
		roundtrip = JavaEnumUtil.getEnumList(E.class, nameList, true);
		assertEquals(3, roundtrip.size());
		roundtrip = JavaEnumUtil.getEnumList(E.class, nameList, false);
		assertEquals(6, roundtrip.size());
	}

}
