/**
 * The CIP4 Software License, Version 1.0
 *
 * Copyright (c) 2001-2021 The International Cooperation for the Integration of Processes in Prepress, Press and Postpress (CIP4). All rights reserved.
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

import java.io.File;

import org.cip4.jdflib.JDFTestCaseBase;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;

/**
 *
 * @author rainer prosi
 * @date Sep 30, 2010
 */
public class XMLParserTest extends JDFTestCaseBase
{
	/**
	 *
	 *
	 */
	@Test
	void testStar()
	{
		final XMLDoc d = new XMLDoc("a*", null);
		final String xml = d.toXML();
		final XMLParser p = new XMLParser();
		Assertions.assertNull(p.parseString(xml));
	}

	/**
	 *
	 *
	 */
	@Test
	void testParseFile()
	{
		for (int i = 0; i < 444; i++)
		{
			final XMLParser p = XMLParserFactory.getFactory().get();
			Assertions.assertNotNull(p.parseFile(new File(sm_dirTestData + "ResourceInfo.jmf")));
		}
	}

	/**
	 *
	 *
	 */
	@Test
	void testFatalError()
	{
		final String xml = "<a>aaa</b>";
		final XMLParser p = new XMLParser();
		Assertions.assertNull(p.parseString(xml));
	}

	/**
	 *
	 *
	 */
	@Test
	void testFatalErrorFile()
	{
		final XMLParser p = new XMLParser();
		Assertions.assertNull(p.parseFile(sm_dirTestData + "corrupt.jdf"));
	}

	/**
	 *
	 *
	 */
	@Test
	void testSetSchemaLocation()
	{
		final XMLParser p = new XMLParser();
		p.setSchemaLocation("foo", "./foo.com");
		Assertions.assertEquals("foo ./foo.com", p.m_SchemaLocation);
	}

	/**
	 *
	 *
	 */
	@Test
	void testAddSchemaLocation()
	{
		final XMLParser p = new XMLParser();
		p.addSchemaLocation("foo", "./foo.com");
		Assertions.assertEquals("foo ./foo.com", p.m_SchemaLocation);
		p.addSchemaLocation("bar", "./bar.com");
		Assertions.assertEquals("foo ./foo.com bar ./bar.com", p.m_SchemaLocation);
	}

	/**
	 *
	 *
	 */
	@Test
	void testMeanChars()
	{
		final XMLParser p = new XMLParser();
		final XMLDoc d = p.parseString("<foo a=\"SchuÌˆtz_Teil5_bel\"/>");
		Assertions.assertNotNull(d);
		final String s = d.write2String(2);
		final XMLDoc d2 = p.parseString(s);
		Assertions.assertNotNull(d2);
	}

	/**
	 *
	 *
	 */
	@Test
	void testDirty()
	{
		final XMLParser p = new XMLParser();
		final XMLDoc d = p.parseString("<foo/>");
		Assertions.assertNotNull(d);
		Assertions.assertFalse(d.getRoot().isDirty());
	}

	/**
	 *
	 *
	 */
	@Test
	void testWantLog()
	{
		final XMLParser p = new XMLParser();
		XMLErrorHandler.isWantLog();
		XMLDoc d = p.parseString("<foo a=\"SchuÌˆtz_Teil5_bel\">");
		XMLErrorHandler.setWantLog(false);
		log.info("empty after");
		d = p.parseString("<foo a=\"SchuÌˆtz_Teil5_bel\">");
		log.info("empty before");
		Assertions.assertNull(d);
	}

	/**
	 *
	 *
	 */
	@Test
	void testSkip()
	{
		final XMLParser p = new XMLParser();
		final boolean b = XMLParser.m_searchStream;
		XMLParser.m_searchStream = true;
		final XMLDoc d = p.parseString("aaaabbb  ss<?xml version='1.0' encoding='utf-8' ?>\n<foo />");
		XMLParser.m_searchStream = b;

		Assertions.assertNotNull(d);
		final String s = d.write2String(2);
		final XMLDoc d2 = p.parseString(s);
		Assertions.assertNotNull(d2);
	}

	/**
	 * @see org.cip4.jdflib.JDFTestCaseBase#tearDown()
	 */
	@Override
	@AfterEach
	public void tearDown() throws Exception
	{
		XMLErrorHandler.setWantLog(true);
		super.tearDown();
	}
}
