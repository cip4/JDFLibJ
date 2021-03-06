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

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

import java.io.File;

import org.cip4.jdflib.JDFTestCaseBase;
import org.junit.After;
import org.junit.Test;

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
	public void testStar()
	{
		final XMLDoc d = new XMLDoc("a*", null);
		final String xml = d.toXML();
		final XMLParser p = new XMLParser();
		assertNull(p.parseString(xml));
	}

	/**
	 *
	 *
	 */
	@Test
	public void testParseFile()
	{
		for (int i = 0; i < 444; i++)
		{
			final XMLParser p = XMLParserFactory.getFactory().get();
			assertNotNull(p.parseFile(new File(sm_dirTestData + "ResourceInfo.jmf")));
		}
	}

	/**
	 *
	 *
	 */
	@Test
	public void testFatalError()
	{
		final String xml = "<a>aaa</b>";
		final XMLParser p = new XMLParser();
		assertNull(p.parseString(xml));
	}

	/**
	 *
	 *
	 */
	@Test
	public void testFatalErrorFile()
	{
		final XMLParser p = new XMLParser();
		assertNull(p.parseFile(sm_dirTestData + "corrupt.jdf"));
	}

	/**
	 *
	 *
	 */
	@Test
	public void testSetSchemaLocation()
	{
		final XMLParser p = new XMLParser();
		p.setSchemaLocation("foo", "./foo.com");
		assertEquals("foo ./foo.com", p.m_SchemaLocation);
	}

	/**
	 *
	 *
	 */
	@Test
	public void testAddSchemaLocation()
	{
		final XMLParser p = new XMLParser();
		p.addSchemaLocation("foo", "./foo.com");
		assertEquals("foo ./foo.com", p.m_SchemaLocation);
		p.addSchemaLocation("bar", "./bar.com");
		assertEquals("foo ./foo.com bar ./bar.com", p.m_SchemaLocation);
	}

	/**
	 *
	 *
	 */
	@Test
	public void testMeanChars()
	{
		final XMLParser p = new XMLParser();
		final XMLDoc d = p.parseString("<foo a=\"SchuÌˆtz_Teil5_bel\"/>");
		assertNotNull(d);
		final String s = d.write2String(2);
		final XMLDoc d2 = p.parseString(s);
		assertNotNull(d2);
	}

	/**
	 *
	 *
	 */
	@Test
	public void testDirty()
	{
		final XMLParser p = new XMLParser();
		final XMLDoc d = p.parseString("<foo/>");
		assertNotNull(d);
		assertFalse(d.getRoot().isDirty());
	}

	/**
	 *
	 *
	 */
	@Test
	public void testWantLog()
	{
		final XMLParser p = new XMLParser();
		XMLErrorHandler.isWantLog();
		XMLDoc d = p.parseString("<foo a=\"SchuÌˆtz_Teil5_bel\">");
		XMLErrorHandler.setWantLog(false);
		log.info("empty after");
		d = p.parseString("<foo a=\"SchuÌˆtz_Teil5_bel\">");
		log.info("empty before");
		assertNull(d);
	}

	/**
	 *
	 *
	 */
	@Test
	public void testSkip()
	{
		final XMLParser p = new XMLParser();
		final boolean b = XMLParser.m_searchStream;
		XMLParser.m_searchStream = true;
		final XMLDoc d = p.parseString("aaaabbb  ss<?xml version='1.0' encoding='utf-8' ?>\n<foo />");
		XMLParser.m_searchStream = b;

		assertNotNull(d);
		final String s = d.write2String(2);
		final XMLDoc d2 = p.parseString(s);
		assertNotNull(d2);
	}

	/**
	 * @see org.cip4.jdflib.JDFTestCaseBase#tearDown()
	 */
	@Override
	@After
	public void tearDown() throws Exception
	{
		XMLErrorHandler.setWantLog(true);
		super.tearDown();
	}
}
