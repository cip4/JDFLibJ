/*
 *
 * The CIP4 Software License, Version 1.0
 *
 *
 * Copyright (c) 2001-2009 The International Cooperation for the Integration of 
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
/**
 * JDFDocTest.java
 * 
 * @author Kai Mattern
 *
 * Copyright (C) 2002 Heidelberger Druckmaschinen AG. All Rights Reserved.
 */
package org.cip4.jdflib.core;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

import org.cip4.jdflib.JDFTestCaseBase;
import org.cip4.jdflib.core.JDFResourceLink.EnumUsage;
import org.cip4.jdflib.node.JDFNode;
import org.cip4.jdflib.resource.JDFResource;
import org.cip4.jdflib.util.FileUtil;

/**
 * @author Rainer Prosi, Heidelberger Druckmaschinen
 * 
 */
public class JDFParserTest extends JDFTestCaseBase
{
	String s;
	boolean bSearch;

	/**
	 * check speed of the parser
	 * 
	 */
	public void testSpeed()
	{
		final long l1 = System.nanoTime();
		for (int i = 0; i < 1000; i++)
		{
			new JDFParser().parseString(s);
		}
		System.out.println("new  p: " + (System.nanoTime() - l1) / 1000000);
	}

	/**
	 * check speed of the parser
	 * 
	 */
	public void testSpeed1()
	{
		final long l1 = System.nanoTime();
		for (int i = 0; i < 10000; i++)
		{
			new JDFParser().parseString(s);
		}
		System.out.println("mem new:   " + getCurrentMem() + " " + mem);
		assertTrue(getCurrentMem() - mem < 1000000);
		System.out.println("new:   " + (System.nanoTime() - l1) / 1000000);
	}

	/**
	 * check speed of the parser
	 * 
	 */
	public void testParseSpeed()
	{
		final JDFParser parser = new JDFParser();
		System.gc();
		final long l1 = System.nanoTime();
		final JDFDoc d = parser.parseFile(sm_dirTestData + "bigWhite.jdf");
		assertNotNull(d);
		System.out.println("big parse:   " + (System.nanoTime() - l1) / 1000000);
	}

	/**
	 * check simple parsestring
	 * 
	 */
	public void testParseString()
	{
		final JDFParser parser = new JDFParser();
		assertNotNull(parser.parseString(s));
	}

	/**
	 * check simple parseStream
	 * @throws IOException
	 * 
	 */
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
	public void testBadNS()
	{
		final String s2 = "<JMF/>";
		assertEquals(new JDFParser().parseString(s2).getRoot().getLocalName(), "JMF");
	}

	/**
	 * check speed of the parser
	 * 
	 */
	public void testSpeed2()
	{
		final long l1 = System.nanoTime();
		final JDFParser p = new JDFParser();
		for (int i = 0; i < 10000; i++)
		{
			p.parseString(s);
		}
		System.out.println("mem reuse:   " + getCurrentMem() + " " + mem);
		assertTrue(getCurrentMem() - mem < 1000000);
		System.out.println("reuse: " + (System.nanoTime() - l1) / 1000000);
	}

	/**
	 * parse a string with guck up front
	 * 
	 * @throws Exception
	 */
	public void testSkipParse()
	{
		JDFParser.m_searchStream = true;
		final String s2 = "        ------ end of header ----!\n<?xml version=\"1.0\" encoding=\"UTF-8\"?>\n <JMF ID=\"abc\"/>";
		for (int i = 0; i < 100000; i++)
		{
			assertNotNull(new JDFParser().parseString(s2));
		}
		System.out.println("mem new:   " + getCurrentMem() + " " + mem);
		assertTrue(getCurrentMem() - mem < 1000000);
		JDFParser.m_searchStream = false;
		assertNull(new JDFParser().parseString(s2));
	}

	/**
	 * parse a simple JDF against all official schemas this test catches corrupt xml schemas
	 * @throws Exception
	 */
	public void testSchema()
	{
		final File foo = new File(sm_dirTestSchema).getParentFile();

		assertTrue("please mount the svn schema parallel to jdflibJ", foo.isDirectory());
		final File[] dirs = FileUtil.listFilesWithExpression(foo, ".*Version_.*");
		assertTrue(dirs.length > 3);
		int nCheck = 0;
		for (int i = 0; i < dirs.length; i++)
		{
			final File dir = dirs[i];
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
		assertTrue(nCheck >= 3);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.cip4.jdflib.JDFTestCaseBase#setUp()
	 */
	@Override
	protected void setUp() throws Exception
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
	protected void tearDown() throws Exception
	{
		// TODO Auto-generated method stub
		super.tearDown();
		JDFParser.m_searchStream = bSearch;
	}

}
