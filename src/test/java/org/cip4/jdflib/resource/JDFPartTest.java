/*
 * The CIP4 Software License, Version 1.0
 *
 *
 * Copyright (c) 2001-2020 The International Cooperation for the Integration of Processes in Prepress, Press and Postpress (CIP4). All rights reserved.
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
/*
 * MediaColorTest.java
 *
 * @author Dietrich Mucha
 *
 * Copyright (C) 2004 Heidelberger Druckmaschinen AG. All Rights Reserved.
 */
package org.cip4.jdflib.resource;

import java.util.Iterator;

import org.cip4.jdflib.JDFTestCaseBase;
import org.cip4.jdflib.auto.JDFAutoPart.EnumSide;
import org.cip4.jdflib.core.AttributeName;
import org.cip4.jdflib.core.JDFDoc;
import org.cip4.jdflib.core.VString;
import org.cip4.jdflib.datatypes.JDFAttributeMap;
import org.cip4.jdflib.resource.JDFResource.EnumPartIDKey;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

////////////////////////////////////////////////////////////////
/**
 * @author Dr. Rainer Prosi, Heidelberger Druckmaschinen AG 13.11.2008
 */
public class JDFPartTest extends JDFTestCaseBase
{
	JDFPart part = null;

	/**
	 *
	 */
	@Test
	public void testSetPartMap()
	{
		final JDFAttributeMap map = new JDFAttributeMap("Side", "Front");
		part.setPartMap(map);
		Assertions.assertEquals(part.getPartMap(), map);
		map.put("Side", "Back");
		map.put("Run", "1");
		part.setPartMap(map);
		Assertions.assertEquals(part.getPartMap(), map);
		part.setPartMap(null);
		Assertions.assertEquals(part.getPartMap(), new JDFAttributeMap());
	}

	/**
	 *
	 */
	@Test
	public void testGetPartMap()
	{
		final JDFAttributeMap map = new JDFAttributeMap("Side", "Front");
		map.put("ID", "i");
		part.setPartMap(map);
		Assertions.assertEquals(1, part.getPartMap().size());
	}

	/**
	 *
	 */
	@Override
	@BeforeEach
	public void setUp()
	{
		final JDFDoc doc = new JDFDoc("Part");
		part = (JDFPart) doc.getRoot();
	}

	/**
	 *
	 */
	@Test
	public void testPartIDConsistency()
	{
		final VString knownAtts = part.knownAttributes();
		final Iterator<EnumPartIDKey> it = EnumPartIDKey.iterator();
		while (it.hasNext())
		{
			final String name = (it.next()).getName();
			Assertions.assertTrue(knownAtts.contains(name), "name missing in Part: " + name);
		}
	}

	/**
	 *
	 */
	@Test
	public void testEnumSide()
	{
		Assertions.assertEquals(EnumSide.Front, EnumSide.getEnum("Front"));
		Assertions.assertEquals(EnumSide.Back, EnumSide.getEnum("Back"));
		Assertions.assertNull(EnumSide.getEnum("foo"));
	}

	/**
	 *
	 */
	@Test
	public void testMatchesPartVersion()
	{
		Assertions.assertTrue(JDFPart.matchesPart(AttributeName.PARTVERSION, "eng", "eng eng", false));
		Assertions.assertTrue(JDFPart.matchesPart(AttributeName.PARTVERSION, "eng", "eng fra", false));
		Assertions.assertTrue(JDFPart.matchesPart(AttributeName.PARTVERSION, "eng", "eng", false));
		Assertions.assertTrue(JDFPart.matchesPart(AttributeName.PARTVERSION, "eng fra", "eng", false));
		Assertions.assertTrue(JDFPart.matchesPart(AttributeName.PARTVERSION, "eng eng", "eng", false));
		Assertions.assertFalse(JDFPart.matchesPart(AttributeName.PARTVERSION, "eng fra", "fra eng", false));
		Assertions.assertFalse(JDFPart.matchesPart(AttributeName.PARTVERSION, "eng fra eng", "fra eng", false));

		Assertions.assertFalse(JDFPart.matchesPart(AttributeName.PARTVERSION, "eng", "eng eng", true));
		Assertions.assertFalse(JDFPart.matchesPart(AttributeName.PARTVERSION, "eng", "eng fra", true));
		Assertions.assertTrue(JDFPart.matchesPart(AttributeName.PARTVERSION, "eng", "eng", true));
		Assertions.assertFalse(JDFPart.matchesPart(AttributeName.PARTVERSION, "eng fra", "eng", true));
		Assertions.assertFalse(JDFPart.matchesPart(AttributeName.PARTVERSION, "eng eng", "eng", true));
		Assertions.assertFalse(JDFPart.matchesPart(AttributeName.PARTVERSION, "eng fra", "fra eng", true));
		Assertions.assertFalse(JDFPart.matchesPart(AttributeName.PARTVERSION, "eng fra eng", "fra eng", true));
	}

	/**
	 *
	 */
	@Test
	public void testMatchesPartVersionWildcard()
	{
		Assertions.assertTrue(JDFPart.matchesPart(AttributeName.PARTVERSION, "*", "eng eng", false));
		Assertions.assertTrue(JDFPart.matchesPart(AttributeName.PARTVERSION, "All", "eng fra", false));
		Assertions.assertTrue(JDFPart.matchesPart(AttributeName.PARTVERSION, "*", "eng", false));
		Assertions.assertTrue(JDFPart.matchesPart(AttributeName.PARTVERSION, "All", "eng", false));
		Assertions.assertFalse(JDFPart.matchesPart(AttributeName.PARTVERSION, "* fra eng", "fra ALL", false));
	}

	/**
	 *
	 */
	@Test
	public void testMatchesPart()
	{
		Assertions.assertTrue(JDFPart.matchesPart(AttributeName.SHEETNAME, "eng", "eng", true));
		Assertions.assertTrue(JDFPart.matchesPart(AttributeName.SHEETNAME, "eng", "eng", false));
		Assertions.assertFalse(JDFPart.matchesPart(AttributeName.SHEETNAME, "eng", "eng2", true));
		Assertions.assertFalse(JDFPart.matchesPart(AttributeName.SHEETNAME, "eng", "eng2", false));

		Assertions.assertTrue(JDFPart.matchesPart("PartVersion", "DE EN FR", "DE EN FR", false));
		Assertions.assertTrue(JDFPart.matchesPart("RunIndex", "1 ~ 4", "2 3", false));
		Assertions.assertTrue(JDFPart.matchesPart("RunIndex", "1 ~ 3 5 ~ 6", "3 5", false));
		Assertions.assertFalse(JDFPart.matchesPart("RunIndex", "1 ~ 3 6 ~ 8", "3 ~ 6", false));
		Assertions.assertTrue(JDFPart.matchesPart("PartVersion", "DE EN", "DE", false));
		Assertions.assertTrue(JDFPart.matchesPart("PartVersion", "DE EN", "DE EN", false));
		Assertions.assertFalse(JDFPart.matchesPart("PartVersion", "DE EN", "DE", true));
		Assertions.assertTrue(JDFPart.matchesPart("PartVersion", "DE EN", "DE EN", true));
		Assertions.assertFalse(JDFPart.matchesPart("PartVersion", "DE EN", "DEU", false));
		Assertions.assertTrue(JDFPart.matchesPart("Run", "R1", "R1", false));
		Assertions.assertFalse(JDFPart.matchesPart("Run", "R1 R2", "R1", false));
		Assertions.assertFalse(JDFPart.matchesPart("Run", "R2", "R1", false));
		Assertions.assertFalse(JDFPart.matchesPart("RunIndex", "1 ~ 4", "5", false));
	}

	/**
	 *
	 */
	@Test
	public void testMatchesPartTags()
	{
		Assertions.assertFalse(JDFPart.matchesPart("RunTags", "aaa bb", "aa", false));
		Assertions.assertTrue(JDFPart.matchesPart("RunTags", "aa bb", "aa", false));
		Assertions.assertTrue(JDFPart.matchesPart("ItemNames", "aa bb", "aa", false));
	}

	/**
	 *
	 */
	@Test
	public void testFillTags()
	{
		Assertions.assertFalse(JDFPart.fillFastParts().contains("RunTags"));
		Assertions.assertTrue(JDFPart.fillFastParts().contains("Run"));
		Assertions.assertTrue(JDFPart.fillFastParts().contains(EnumPartIDKey.SheetName.getName()));
		Assertions.assertFalse(JDFPart.fillFastParts().contains(EnumPartIDKey.RunIndex.getName()));
	}
}
