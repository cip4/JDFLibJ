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
/**
 *
 */
package org.cip4.jdflib.elementwalker;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.PrintWriter;

import org.cip4.jdflib.JDFTestCaseBase;
import org.cip4.jdflib.core.ElementName;
import org.cip4.jdflib.core.JDFDoc;
import org.cip4.jdflib.core.JDFParser;
import org.cip4.jdflib.core.KElement;
import org.cip4.jdflib.core.VString;
import org.cip4.jdflib.core.XMLDoc;
import org.cip4.jdflib.resource.JDFResource;
import org.cip4.jdflib.util.ByteArrayIOStream;
import org.cip4.jdflib.util.CPUTimer;
import org.cip4.jdflib.util.StringUtil;
import org.cip4.jdflib.util.UrlUtil;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**
 * @author Rainer Prosi, Heidelberger Druckmaschinen *
 */
public class XPathWalkerTest extends JDFTestCaseBase
{

	private final String testFile = "matsch.jdf";

	/**
	 * @throws Exception
	 */
	@Test
	public synchronized void testFile() throws Exception
	{
		final String s = sm_dirTestData + testFile;
		final JDFDoc d = new JDFParser().parseFile(s);
		final XPathWalker w = new XPathWalker(new File(sm_dirTestDataTemp + UrlUtil.newExtension(testFile, "txt")));
		w.setAttribute(true);
		w.setAttributeValue(true);
		w.walkAll(d.getRoot());
	}

	/**
	 * @throws Exception
	 */
	@Test
	void testPerformanceFile() throws Exception
	{
		final CPUTimer ct = new CPUTimer(false);
		final JDFDoc d = new JDFParser().parseFile(sm_dirTestData + "bigWhite.jdf");
		for (int i = 0; i < 3; i++)
		{
			final XPathWalker w = new XPathWalker(new File(sm_dirTestDataTemp + "bigWhite" + i + ".txt"));
			w.setAttribute(true);
			w.setAttributeValue(true);
			ct.start();
			w.walkAll(d.getRoot());
			log.info(i + " " + ct.getSingleSummary());
			ct.stop();
		}
	}

	/**
	 * @throws Exception
	 */
	@Test
	void testDataType() throws Exception
	{
		final JDFDoc d = new JDFDoc("JDF");
		final JDFResource intent = d.getJDFRoot().addResource(ElementName.LAYOUTINTENT, null);
		intent.setAttribute("SizePolicy", "Tile");
		final ByteArrayIOStream ios = new ByteArrayIOStream();
		final PrintWriter writer = new PrintWriter(ios);
		final XPathWalker w = new XPathWalker(writer);
		w.setSeparator(",");
		w.setAttribute(true);
		w.setAttributeValue(true);
		w.setDatatype(true);
		w.walkAll(intent);
		final String s = new String(ios.getBuf());
		Assertions.assertTrue(s.indexOf("@SizePolicy,Tile,enumeration") > 0);
	}

	/**
	 * @throws Exception
	 */
	@Test
	void testCSV() throws Exception
	{
		final JDFDoc d = new JDFDoc("JDF");
		final ByteArrayIOStream ios = new ByteArrayIOStream();
		final PrintWriter writer = new PrintWriter(ios);
		final XPathWalker w = new XPathWalker(writer);
		w.setSeparator(",");
		w.setAttribute(true);
		w.setAttributeValue(true);
		w.setDatatype(true);
		w.walkAll(d.getRoot());
		final String s = new String(ios.getBuf());
		Assertions.assertTrue(s.indexOf("JDF/AuditPool") > 0);
	}

	/**
	 * @throws Exception
	 */
	@Test
	void testCSVUnique() throws Exception
	{
		final JDFDoc d = new JDFDoc("JDF");
		for (int i = 0; i < 100; i++)
			d.getJDFRoot().getCreateAuditPool().addModified(null, null);
		final ByteArrayIOStream ios = new ByteArrayIOStream();
		final PrintWriter writer = new PrintWriter(ios);
		final XPathWalker w = new XPathWalker(writer);
		w.setSeparator(",");
		w.setAttribute(true);
		w.setAttributeValue(true);
		w.setDatatype(true);
		w.setUnique(true);
		w.setMethod(0);
		w.walkAll(d.getRoot());
		final String s = ios.toString();
		Assertions.assertTrue(StringUtil.tokenize(s, "\n", false).size() < 50);
	}

	/**
	 * @throws Exception
	 */
	@Test
	public synchronized void testRead() throws Exception
	{
		final long n0 = System.currentTimeMillis();
		testFile();
		long n1 = System.currentTimeMillis();
		System.out.println("create time: " + (n1 - n0));
		for (int i = 0; i < 3; i++)
		{
			n1 = System.currentTimeMillis();
			final XMLDoc d = (i % 2 == 0) ? new XMLDoc("JDF", null) : new JDFDoc("JDF");

			final KElement n = d.getRoot();
			final File f = new File(sm_dirTestDataTemp + UrlUtil.newExtension(testFile, "txt"));
			final BufferedReader r = new BufferedReader(new FileReader(f));
			String s = r.readLine();
			while (s != null)
			{
				final int neq = s.indexOf("=");
				final VString v = StringUtil.tokenize(s, "= ", false);
				if (neq > 0 && v.size() == 1)
					v.add("");
				if (v.size() == 1)
				{
					n.getCreateXPathElement(v.get(0));
				}
				else
				{
					n.setXPathValue(v.get(0), v.get(1));
				}
				s = r.readLine();
			}
			final long n2 = System.currentTimeMillis();
			System.out.println(i + " build time: " + (n2 - n1));
			d.write2File(sm_dirTestDataTemp + UrlUtil.newExtension(testFile, (i + ".new.jdf")), 0, true);
			final long n3 = System.currentTimeMillis();
			System.out.println("write time: " + (n3 - n2));
			r.close();
		}
	}

	/**
	 * @see JDFTestCaseBase#setUp()
	 */
	@Override
	@BeforeEach
	void setUp() throws Exception
	{

		super.setUp();
		new File(sm_dirTestDataTemp).mkdirs();
	}
}