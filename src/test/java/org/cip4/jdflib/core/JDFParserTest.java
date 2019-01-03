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
 * JDFDocTest.java
 *
 * @author Kai Mattern
 *
 *         Copyright (C) 2002 Heidelberger Druckmaschinen AG. All Rights Reserved.
 */
package org.cip4.jdflib.core;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

import org.cip4.jdflib.JDFTestCaseBase;
import org.cip4.jdflib.core.JDFResourceLink.EnumUsage;
import org.cip4.jdflib.node.JDFNode;
import org.cip4.jdflib.pool.JDFAuditPool;
import org.cip4.jdflib.resource.JDFCreated;
import org.cip4.jdflib.resource.JDFResource;
import org.cip4.jdflib.resource.process.JDFContact;
import org.cip4.jdflib.util.FileUtil;
import org.junit.Test;

/**
 * @author Rainer Prosi, Heidelberger Druckmaschinen
 *
 */
public class JDFParserTest extends JDFTestCaseBase
{
	String s;
	boolean bSearch;

	/**
	 * @author Rainer Prosi, Heidelberger Druckmaschinen *
	 */
	public static class MyDocImpl extends DocumentJDFImpl
	{

		/**
		 *
		 */
		private static final long serialVersionUID = 1819514227719688245L;

		/**
		 *
		 */
		public MyDocImpl()
		{
			super();
			// for debugging
		}
	}

	/**
	 * @author Rainer Prosi, Heidelberger Druckmaschinen *
	 */
	static class MyParser extends JDFParser
	{

		/**
		 *
		 */
		public MyParser()
		{
			super();
		}

		/**
		 *
		 */

		@Override
		public String getDocumentClass()
		{
			return MyDocImpl.class.getName();

		}

	}

	/**
	 * check speed of the parser
	 *
	 */
	@Test
	public void testSpeed()
	{
		final long l1 = System.nanoTime();
		for (int i = 0; i < 222; i++)
		{
			new JDFParser().parseString(s);
		}
		log.info("new  p: " + (System.nanoTime() - l1) / 1000000);
	}

	/**
	 * check speed of the parser
	 *
	 */
	@Test
	public void testSpeed1()
	{
		final long l1 = System.nanoTime();
		for (int i = 0; i < 222; i++)
		{
			new JDFParser().parseString(s);
		}
		long currentMem = getCurrentMem();
		log.info("mem new:   " + currentMem + " " + mem);
		if (currentMem < mem)
			currentMem = mem;
		assertEquals("parese memory", currentMem, mem, 4200000);
		log.info("new:   " + (System.nanoTime() - l1) / 1000000);
	}

	/**
	 * check speed of the parser
	 *
	 */
	@Test
	public void testParseSpeed()
	{
		final JDFParser parser = new JDFParser();
		System.gc();
		final long l1 = System.currentTimeMillis();
		final JDFDoc d = parser.parseFile(sm_dirTestData + "bigWhite.jdf");
		assertNotNull(d);
		log.info("big parse:   " + (System.currentTimeMillis() - l1) / 1000.000);
	}

	/**
	 * check simple parsestring
	 *
	 */
	@Test
	public void testParseString()
	{
		final JDFParser parser = new JDFParser();
		assertNotNull(parser.parseString(s));
	}

	/**
	 * check simple parsestring
	 *
	 */
	@Test
	public void testParseStringJDF()
	{
		final JDFParser parser = new JDFParser();
		final JDFDoc d = parser.parseString("<JDF/>");
		final JDFNode n = d.getJDFRoot();
		assertEquals(n.getNamespaceURI(), JDFConstants.JDFNAMESPACE);
		JDFAuditPool ap = n.getAuditPool();
		assertNull(ap);
		ap = n.appendAuditPool();
		assertNotNull(ap);
	}

	/**
	 * check simple parsestring
	 *
	 */
	@Test
	public void testParseStringPrintTalk()
	{
		final JDFParser parser = new JDFParser();
		final JDFDoc d = parser.parseString("<PrintTalk/>");
		final KElement pt = d.getRoot();
		assertTrue(pt instanceof JDFElement);
	}

	/**
	 * check simple parsestring
	 *
	 */
	@Test
	public void testParseStringPrintTalkDeep()
	{
		final JDFParser parser = new JDFParser();
		final JDFDoc d = parser.parseString("<PrintTalk><Request><XJDF><Contact/></XJDF></Request></PrintTalk>");
		final KElement pt = d.getRoot();
		assertNotNull(pt.getElementByClass(JDFContact.class, 0, true));
	}

	/**
	 * check simple parsestring for invalid xml
	 *
	 */
	@Test
	public void testParseStringJDFBad()
	{
		final JDFParser parser = new JDFParser();
		JDFDoc d = parser.parseString("<JDF");
		assertNull("not wellformed ", d);
		d = parser.parseString("<Foo:JDF/>");
		assertNull("bad ns ", d);
		d = parser.parseString("<JDF><a b=\"a");
		assertNull("bad attribute ", d);
	}

	/**
	 *
	 */
	@Test
	public void testParseStringJDFWrongNS()
	{
		final JDFParser parser = new JDFParser();
		final JDFDoc d = parser.parseString("<JDF xmlns=\"www.cip4.org\"/>");
		final JDFNode n = d.getJDFRoot();
		assertEquals(n.getNamespaceURI(), JDFConstants.JDFNAMESPACE);
		JDFAuditPool ap = n.getAuditPool();
		assertNull(ap);
		ap = n.appendAuditPool();
		assertNotNull(ap);
	}

	/**
	 * check simple parseStream
	 *
	 * @throws IOException
	 *
	 */
	@Test
	public void testParseFileStream() throws IOException
	{
		final JDFParser parser = new JDFParser();
		final File f = new File(sm_dirTestData + "ApprovalSubJDF.jdf");
		assertTrue(f.canRead());
		final File f2 = new File(sm_dirTestDataTemp + "ApprovalSubJDF.jdf");
		assertTrue(FileUtil.copyFile(f, f2));
		final InputStream is = new FileInputStream(f2);
		final JDFDoc d = parser.parseStream(is);
		assertNotNull(d);
		is.close();
		final File f3 = new File("movedTo.jdf");
		f3.delete();
		assertTrue(FileUtil.moveFile(f2, f3));
		assertFalse(f2.canRead());
		f3.delete();

	}

	/**
	 * check speed of the parser
	 *
	 */
	@Test
	public void testMyDocClass()
	{
		final MyParser p = new MyParser();
		final JDFDoc doc = p.parseString("<JMF/>");
		assertEquals(doc.getRoot().getLocalName(), "JMF");
		new JDFDoc(doc.getMemberDocument());
	}

	/**
	 * check speed of the parser
	 *
	 */
	@Test
	public void testBadNS()
	{
		final String s2 = "<JMF/>";
		assertEquals(new JDFParser().parseString(s2).getRoot().getLocalName(), "JMF");
	}

	/**
	 * check speed of the parser
	 *
	 */
	@Test
	public void testSpeed2()
	{
		final long l1 = System.nanoTime();
		final JDFParser p = new JDFParser();
		for (int i = 0; i < 222; i++)
		{
			p.parseString(s);
		}
		log.info("mem reuse:   " + getCurrentMem() + " " + mem);
		assertTrue(getCurrentMem() - mem < 4200000);
		log.info("reuse: " + (System.nanoTime() - l1) / 1000000);
	}

	/**
	 * parse a string with guck up front
	 *
	 */
	@Test
	public void testSkipParse()
	{
		JDFParser.m_searchStream = true;
		final String s2 = "        ------ end of header ----!\n<?xml version=\"1.0\" encoding=\"UTF-8\"?>\n <JMF ID=\"abc\"/>";
		for (int i = 0; i < 2222; i++)
		{
			assertNotNull(new JDFParser().parseString(s2));
		}
		log.info("mem new:   " + getCurrentMem() + " " + mem);
		assertTrue(getCurrentMem() - mem < 4200000);
		JDFParser.m_searchStream = false;
		assertNull(new JDFParser().parseString(s2));
	}

	/**
	 * parse a string with guck up front
	 *
	 */
	@Test
	public void testInit()
	{
		final JDFDoc d = new JDFParser().parseString(new JDFDoc("JDF").write2String(2));
		final JDFCreated c = d.getJDFRoot().getCreateAuditPool().addCreated(null, null);
		assertTrue(c.getID().length() > 2);
	}

	/**
	 * parse a simple JDF against all official schemas this test catches corrupt xml schemas
	 */
	@Test
	public void testSchema()
	{
		final File foo = new File(sm_dirTestSchema).getParentFile();
		assertTrue("please mount the svn schema parallel to jdflibJ", foo.isDirectory());
		final File[] dirs = FileUtil.listFilesWithExpression(foo, "*Version_1*");
		assertTrue(dirs.length > 0);
		int nCheck = 0;
		for (final File dir2 : dirs)
		{
			final File dir = dir2;
			if (!dir.isDirectory())
			{
				continue;
			}
			final File jdfxsd = new File(dir + File.separator + "JDF.xsd");
			assertTrue(jdfxsd.canRead());
			final JDFParser p = new JDFParser();
			p.setJDFSchemaLocation(jdfxsd);
			assertNotNull("oops in" + jdfxsd, p.parseString(s));
			nCheck++;
		}
		assertTrue(nCheck >= 1);
	}

	/**
	 *
	 * @see org.cip4.jdflib.JDFTestCaseBase#setUp()
	 */
	@Override
	public void setUp() throws Exception
	{
		super.setUp();
		final JDFDoc d = new JDFDoc("JDF");
		final JDFNode n = d.getJDFRoot();
		final JDFResource rl = n.addResource("RunList", EnumUsage.Input);
		rl.setDescriptiveName("Runlist für 10 € &&&"); // sum special characters
		s = d.write2String(2);
		bSearch = JDFParser.m_searchStream;
	}

	@Override
	public void tearDown() throws Exception
	{
		super.tearDown();
		JDFParser.m_searchStream = bSearch;
	}

}
