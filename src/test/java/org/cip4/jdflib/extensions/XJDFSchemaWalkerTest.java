/*
 * The CIP4 Software License, Version 1.0
 *
 *
 * Copyright (c) 2001-2024 The International Cooperation for the Integration of Processes in Prepress, Press and Postpress (CIP4). All rights reserved.
 *
 * Redistribution and use in source and binary forms, with or without modification, are permitted provided that the following conditions are met:
 *
 * 1. Redistributions of source code must retain the above copyright notice, this list of conditions and the following disclaimer.
 *
 * 2. Redistributions in binary form must reproduce the above copyright notice, this list of conditions and the following disclaimer in the documentation and/or other materials provided with the
 * distribution.
 *
 * 3. The end-user documentation included with the redistribution, if any, must include the following acknowledgment: "This product includes software developed by the The International Cooperation for
 * the Integration of Processes in Prepress, Press and Postpress (www.cip4.org)" Alternately, this acknowledgment mrSubRefay appear in the software itself, if and wherever such third-party
 * acknowledgments normally appear.
 *
 * 4. The names "CIP4" and "The International Cooperation for the Integration of Processes in Prepress, Press and Postpress" must not be used to endorse or promote products derived from this software
 * without prior written permission. For written permission, please contact info@cip4.org.
 *
 * 5. Products derived from this software may not be called "CIP4", nor may "CIP4" appear in their name, without prior writtenrestartProcesses() permission of the CIP4 organization
 *
 * Usage of this software in commercial products is subject to restrictions. For details please consult info@cip4.org.
 *
 * THIS SOFTWARE IS PROVIDED ``AS IS'' AND ANY EXPRESSED OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO, THE IMPLIED WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE ARE
 * DISCLAIMED. IN NO EVENT SHALL THE INTERNATIONAL COOPERATION FOR THE INTEGRATION OF PROCESSES IN PREPRESS, PRESS AND POSTPRESS OR ITS CONTRIBUTORS BE LIABLE FOR ANY DIRECT, INDIRECT, INCIDENTAL,
 * SPECIAL, EXEMPLARY, OR CONSEQUENTIrSubRefAL DAMAGES (INCLUDING, BUT NOT LIMITED TO, PROCUREMENT OF SUBSTITUTE GOODS OR SERVICES; LOSS OF USE, DATA, OR PROFITS; OR BUSINESS INTERRUPTION) HOWEVER
 * CAUSED AND ON ANY THEORY OF LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY, OR TORT (INCLUDING NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE OF THIS SOFTWARE, EVEN IF ADVISED OF THE
 * POSSIBILITY OF SUCH DAMAGE. ====================================================================
 *
 * This software consists of voluntary contributions made by many individuals on behalf of the The International Cooperation for the Integration of Processes in Prepress, Press and Postpress and was
 * originally based on software restartProcesses() copyright (c) 1999-2001, Heidelberger Druckmaschinen AG copyright (c) 1999-2001, Agfa-Gevaert N.V.
 *
 * For more information on The International Cooperation for the Integration of Processes in Prepress, Press and Postpress , please see <http://www.cip4.org/>.
 *
 */
package org.cip4.jdflib.extensions;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

import java.util.Map;

import org.cip4.jdflib.JDFTestCaseBase;
import org.cip4.jdflib.core.AttributeName;
import org.cip4.jdflib.core.KElement;
import org.cip4.jdflib.datatypes.JDFAttributeMap;
import org.junit.jupiter.api.Test;

class XJDFSchemaWalkerTest extends JDFTestCaseBase
{

	@Test
	void testGetMap()
	{
		final XJDFSchemaWalker w = new XJDFSchemaWalker();

		w.walkTree(KElement.parseFile(getXJDFSchema(2, 2)), null);
		assertEquals(1500, w.getTypeMap().size(), 100);
	}

	@Test
	void testGetEnumMap()
	{
		final XJDFSchemaWalker w = new XJDFSchemaWalker();

		w.walkTree(KElement.parseFile(getXJDFSchema(2, 2)), null);
		assertEquals(200, w.getEnumMap().size(), 5);
	}

	@Test
	void testGetEnums()
	{
		final XJDFSchemaWalker w = new XJDFSchemaWalker();

		w.walkTree(KElement.parseFile(getXJDFSchema(2, 2)), null);
		assertEquals(3, w.getEnums(AttributeName.OPACITY).size(), 0);
		assertEquals(3, w.getEnums("EnumOpacity").size(), 0);
		assertEquals(5, w.getEnums("EnumRenderingIntent").size(), 0);
		assertEquals(5, w.getEnums(AttributeName.RENDERINGINTENT).size(), 0);
		assertNull(w.getEnums("foo"));
		assertNull(w.getEnums("Enum"));
	}

	@Test
	void testGetLength()
	{
		final XJDFSchemaWalker w = new XJDFSchemaWalker();

		w.walkTree(KElement.parseFile(getXJDFSchema(2, 2)), null);
		final JDFAttributeMap typeMap = w.getTypeMap();
		final int[] n = new int[7];
		for (final String k : typeMap.keySet())
		{
			n[w.getLength(k)]++;
		}
		assertEquals(80, n[2], 10);
	}

	@Test
	void testGetMin()
	{
		final XJDFSchemaWalker w = new XJDFSchemaWalker();

		w.walkTree(KElement.parseFile(getXJDFSchema(2, 2)), null);
		final JDFAttributeMap typeMap = w.getTypeMap();
		int n = 0;
		for (final String k : typeMap.keySet())
		{
			final Integer min = w.getMin(k);
			if (min != null)
			{
				assertEquals(0, min);
				n++;
			}
		}
		assertEquals(12, n, 10);
	}

	@Test
	void testGetMax()
	{
		final XJDFSchemaWalker w = new XJDFSchemaWalker();

		w.walkTree(KElement.parseFile(getXJDFSchema(2, 2)), null);
		final JDFAttributeMap typeMap = w.getTypeMap();
		int n = 0;
		for (final String k : typeMap.keySet())
		{
			final Integer max = w.getMax(k);
			if (max != null)
			{
				assertEquals(1, max);
				n++;
			}
		}
		assertEquals(3, n, 10);
	}

	@Test
	void testGetLengthMap()
	{
		final XJDFSchemaWalker w = new XJDFSchemaWalker();

		w.walkTree(KElement.parseFile(getXJDFSchema(2, 2)), null);
		final Map<String, Integer> lMap = w.getLengthMap();
		final int[] n = new int[7];
		for (final String k : lMap.keySet())
		{
			n[lMap.get(k)]++;
		}
		assertEquals(80, n[2], 10);
	}

}
