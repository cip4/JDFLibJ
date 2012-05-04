/**
 * The CIP4 Software License, Version 1.0
 *
 * Copyright (c) 2001-2012 The International Cooperation for the Integration of 
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
package org.cip4.jdflib.util.zip;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.Vector;
import java.util.zip.ZipEntry;

import org.cip4.jdflib.JDFTestCaseBase;
import org.cip4.jdflib.core.XMLDoc;
import org.cip4.jdflib.util.ByteArrayIOStream;
import org.cip4.jdflib.util.FileUtil;

/**
 *  
 * @author rainer prosi
 * @date Feb 1, 2012
 */
public class ZipReaderTest extends JDFTestCaseBase
{
	/**
	 * 
	 * 
	 */
	public void testunpack()
	{
		ZipReader r = new ZipReader(sm_dirTestData + "schema.zip");
		File dir = new File(sm_dirTestDataTemp + "schema.zip.dir");
		for (int i = 0; i < 3; i++)
		{
			r.buffer();
			FileUtil.deleteAll(dir);
			r.unPack(dir);
			assertTrue(dir.isDirectory());
			File dir2 = dir = FileUtil.getFileInDirectory(dir, new File("schema"));
			assertEquals(dir2.listFiles().length, 3);
		}
	}

	/**
	 * 
	 *  
	 */
	public void testGetEntryStream()
	{
		ZipReader r = new ZipReader(sm_dirTestData + "schema.zip");
		ZipEntry e = r.getEntry("schema/Conditions.jdf");
		assertNotNull(r.getInputStream());
		e = r.getEntry("schema/BarcodeDetails.jdf");
		assertNotNull(r.getInputStream());
		e = r.getEntry("schema/Conditions.jdf");
		assertNotNull(r.getInputStream());
		assertNotNull(e);
	}

	/**
	 * 
	 *  
	 */
	public void testGetXMLDoc()
	{
		ZipReader r = new ZipReader(sm_dirTestData + "schema.zip");
		ZipEntry e = r.getEntry("schema/Conditions.jdf");
		XMLDoc xmlDoc = r.getXMLDoc();
		assertNotNull(xmlDoc);
		assertEquals(r, xmlDoc.getZipReader());
		assertNotNull(e);
	}

	/**
	 * 
	 *  
	 */
	public void testGetJDFDoc()
	{
		ZipReader r = new ZipReader(sm_dirTestData + "schema.zip");
		ZipEntry e = r.getMatchingEntry("*Conditions.jdf", 0);
		XMLDoc xmlDoc = r.getJDFDoc();
		assertNotNull(xmlDoc);
		assertEquals(r, xmlDoc.getZipReader());
		assertNotNull(e);
	}

	/**
	 * 
	 *  
	 */
	public void testGetEntry()
	{
		ZipReader r = new ZipReader(sm_dirTestData + "schema.zip");
		ZipEntry e = r.getEntry("schema/Conditions.jdf");
		assertNotNull(e);
		e = r.getEntry("schema/BarcodeDetails.jdf");
		assertNotNull(e);
		e = r.getEntry("schema/Conditions.jdf");
		assertNotNull(e);
	}

	/**
	 * 
	 *  
	 */
	public void testGetEntryAutoFileRoot()
	{
		ZipReader r = new ZipReader(sm_dirTestData + "schema.zip");
		ZipEntry e = r.getEntry("Conditions.jdf");
		assertNotNull(e);
		e = r.getEntry("BarcodeDetails.jdf");
		assertNotNull(e);
		e = r.getEntry("Conditions.jdf");
		assertNotNull(e);
	}

	/**
	 * 
	 *  
	 */
	public void testSetRoot()
	{
		ZipReader r = new ZipReader(sm_dirTestData + "schema.zip");
		r.setRootEntry("schema/");
		ZipEntry e = r.getEntry("Conditions.jdf");
		assertNotNull(e);
		e = r.getEntry("BarcodeDetails.jdf");
		assertNotNull(e);
		e = r.getEntry("Conditions.jdf");
		assertNotNull(e);
		e = r.getMatchingEntry("Condition?.jdf", 0);
		assertNotNull(e);
	}

	/**
	 * 
	 *  
	 */
	public void testGetBigEntry()
	{
		ZipReader r = new ZipReader(sm_dirTestData + "dir1.zip");
		ZipEntry e = r.getEntry("dir1/bigzip.pdf");
		assertNotNull(e);
	}

	/**
	 * @throws IOException 
	 * 
	 *  
	 */
	public void testGetBigEntryStream() throws IOException
	{
		ByteArrayIOStream bos = new ByteArrayIOStream(new File(sm_dirTestData + "dir1.zip"));
		ZipReader r = new ZipReader(bos.getInputStream());
		ZipEntry e = r.getEntry("dir1/bigzip.pdf");
		assertNotNull(e);
		InputStream inputStream = r.getInputStream();
		assertNotNull(inputStream);
		int n = 0;
		byte[] b = new byte[1000];
		while (true)
		{
			int i = inputStream.read(b);
			n += i;
			if (i < 0)
				break;
		}
		assertTrue(n > 190000000);
	}

	/**
	 * 
	 *  
	 */
	public void testGetBigEntries()
	{
		ZipReader r = new ZipReader(sm_dirTestData + "dir1.zip");
		Vector<ZipEntry> v = r.getEntries();
		assertNotNull(v);
	}

	/**
	 * 
	 *  
	 */
	public void testGetMatchingEntry()
	{
		ZipReader r = new ZipReader(sm_dirTestData + "schema.zip");
		ZipEntry e = r.getMatchingEntry("*.jdf", 0);
		assertTrue(e.getName().endsWith(".jdf"));
		ZipEntry e2 = r.getMatchingEntry("*.jdf", 1);
		assertNotNull(e2);
		assertTrue(e2.getName().endsWith(".jdf"));
		ZipEntry e3 = r.getMatchingEntry("*.jdf", 2);
		assertNull(e3);
	}

	/**
	 * 
	 *  
	 */
	public void testGetIgnoreCase()
	{
		ZipReader r = new ZipReader(sm_dirTestData + "schema.zip");
		ZipEntry e = r.getMatchingEntry("*.jdf", 0);
		assertTrue(e.getName().endsWith(".jdf"));
		ZipEntry e3 = r.getMatchingEntry("*.JDF", 0);
		assertNull(e3);
		e = r.getEntry("schema/barcodedetails.jdf");
		assertNull(e);
		r.setCaseSensitive(false);
		ZipEntry e2 = r.getMatchingEntry("*.JDF", 0);
		assertTrue(e2.getName().endsWith(".jdf"));
		e = r.getEntry("schema/barcodedetails.jdf");
		assertNotNull(e);
	}

	/**
	 * 
	 *  
	 */
	public void testGetEntries()
	{
		ZipReader r = new ZipReader(sm_dirTestData + "schema.zip");
		Vector<ZipEntry> entries = r.getEntries();
		assertEquals(entries.size(), 15);
	}
}
