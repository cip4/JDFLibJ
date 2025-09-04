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

import org.cip4.jdflib.JDFTestCaseBase;
import org.cip4.jdflib.auto.JDFAutoPart.ESide;
import org.cip4.jdflib.auto.JDFAutoPart.EnumSide;
import org.cip4.jdflib.auto.JDFAutoRefAnchor.EAnchor;
import org.cip4.jdflib.auto.JDFAutoRefAnchor.EnumAnchor;
import org.cip4.jdflib.auto.JDFAutoThreadSewingParams.ECastingMaterial;
import org.cip4.jdflib.extensions.ProductHelper.eProductType;
import org.cip4.jdflib.resource.JDFResource.EnumResStatus;
import org.junit.jupiter.api.Test;

/**
 * general utilities for containers and objects
 *
 * @author prosirai
 */
class EnumUtilTest extends JDFTestCaseBase
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
		assertEquals(EnumUtil.min(EnumResStatus.Incomplete, EnumResStatus.Available), EnumResStatus.Incomplete);
	}

	/**
	 *
	 */
	@Test
	void testMax()
	{
		assertEquals(EnumUtil.max(EnumResStatus.Incomplete, EnumResStatus.Available), EnumResStatus.Available);
	}

	/**
	 *
	 */
	@Test
	void testLessEq()
	{
		assertTrue(EnumUtil.aLessEqualsThanB(EnumResStatus.Incomplete, EnumResStatus.Incomplete));
		assertTrue(EnumUtil.aLessEqualsThanB(null, EnumResStatus.Incomplete));
		assertTrue(EnumUtil.aLessEqualsThanB(EnumResStatus.Incomplete, EnumResStatus.Available));
		assertFalse(EnumUtil.aLessEqualsThanB(EnumResStatus.Available, EnumResStatus.Incomplete));
		assertFalse(EnumUtil.aLessEqualsThanB(EnumResStatus.Available, null));
	}

	/**
	 *
	 */
	@Test
	void testLess()
	{
		assertFalse(EnumUtil.aLessThanB(EnumResStatus.Incomplete, EnumResStatus.Incomplete));
		assertTrue(EnumUtil.aLessThanB(EnumResStatus.Incomplete, EnumResStatus.Available));
		assertTrue(EnumUtil.aLessThanB(null, EnumResStatus.Available));
		assertFalse(EnumUtil.aLessThanB(EnumResStatus.Available, EnumResStatus.Incomplete));
		assertFalse(EnumUtil.aLessThanB(EnumResStatus.Available, null));
	}

	/**
	 *
	 */
	@Test
	void testGetEnumName()
	{
		assertEquals(EnumUtil.getEnumName(EnumAnchor.BottomRight), "EnumAnchor");
	}

	/**
	 *
	 */
	@Test
	void testGetJavaEnumNameIgnoreCase()
	{
		for (final eProductType t : eProductType.values())
		{
			assertEquals(t, EnumUtil.getJavaEnumIgnoreCase(eProductType.class, t.name().toLowerCase()));
			assertEquals(t, EnumUtil.getJavaEnumIgnoreCase(eProductType.class, t.name()));
			assertEquals(t, EnumUtil.getJavaEnumIgnoreCase(eProductType.class, t.name().toUpperCase()));
		}
		assertNull(EnumUtil.getJavaEnumIgnoreCase(eProductType.class, null));
		assertNull(EnumUtil.getJavaEnumIgnoreCase(eProductType.class, ""));
		assertNull(EnumUtil.getJavaEnumIgnoreCase(eProductType.class, "foo"));
	}

	/**
	 *
	 */
	@Test
	void testGetEnumList()
	{
		assertEquals(9, EnumUtil.getNamesList(EnumAnchor.class).size());
		assertNull(EnumUtil.getNamesList(null));
	}

	/**
	 *
	 */
	@Test
	void testGetEnumValue()
	{
		assertEquals(EnumUtil.getName(EnumAnchor.BottomRight), "BottomRight");
		assertEquals(EnumUtil.getName(null), "null");
	}

	/**
	 *
	 */
	@Test
	void testGetJavaEnum()
	{
		assertEquals(ESide.Front, EnumUtil.getJavaEnum(EnumSide.Front));
		assertEquals(null, EnumUtil.getJavaEnum(null));
	}

	/**
	 *
	 */
	@Test
	void testGetOldEnum()
	{
		assertEquals(EnumSide.Front, EnumUtil.getOldEnum(ESide.Front));
		assertEquals(null, EnumUtil.getOldEnum(null));
	}

	/**
	 *
	 */
	@Test
	void testGetJavaEnumClass()
	{
		assertEquals(ESide.class, EnumUtil.getJavaEnumClass(EnumSide.Front));
		assertEquals(null, EnumUtil.getJavaEnumClass(null));
	}

	/**
	 *
	 */
	@Test
	void testGetEnumEnum()
	{
		assertEquals(EAnchor.BottomRight, EnumUtil.getEnum(EAnchor.class, EnumAnchor.BottomRight));
		assertNull(EnumUtil.getEnum(ECastingMaterial.class, EnumAnchor.BottomRight));
		assertNull(EnumUtil.getEnum(ECastingMaterial.class, null));
	}

	/**
	 *
	 */
	@Test
	void testGetEnumIgnorecase()
	{
		assertNull(EnumUtil.getEnumIgnoreCase(EnumAnchor.class, E.a));
		assertNull(EnumUtil.getEnumIgnoreCase(EnumAnchor.class, (String) null));
		assertNull(EnumUtil.getEnumIgnoreCase(EnumAnchor.class, "foo"));
		assertNull(EnumUtil.getEnumIgnoreCase(null, E.a));
		assertEquals(EnumAnchor.BottomLeft, EnumUtil.getEnumIgnoreCase(EnumAnchor.class, E.BOTTOMLEFT));
		assertEquals(EnumAnchor.BottomRight, EnumUtil.getEnumIgnoreCase(EnumAnchor.class, "bottomright"));
	}
}
