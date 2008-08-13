/**
 * NamespaceTest.java
 * 
 * @author Dietrich Mucha
 *
 * Copyright (C) 2002 Heidelberger Druckmaschinen AG. All Rights Reserved.
 */
package org.cip4.jdflib;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

import junit.framework.TestCase;

import org.cip4.jdflib.core.ElementName;
import org.cip4.jdflib.core.JDFDoc;
import org.cip4.jdflib.core.JDFParser;

public class EmptyNamespace extends TestCase
{
	static final String fileSeparator = System.getProperty("file.separator");
	static final String sm_dirTestData = "test" + fileSeparator + "data"
			+ fileSeparator;
	static final String sm_dirTestDataTemp = sm_dirTestData + "temp"
			+ fileSeparator;

	public void testDefaultNamespace()
	{
		JDFDoc jdfDoc = new JDFDoc(ElementName.JDF);

		String defaultFile = "default.jdf";
		jdfDoc.write2File(sm_dirTestDataTemp + defaultFile, 0, true);

		JDFParser p = new JDFParser();
		JDFDoc jdfDocResult = p.parseFile(sm_dirTestDataTemp + defaultFile);

		jdfDocResult.getRoot();

		// assertXMLEqual (jdfDocResult.getMemberDocument (),
		// jdfDoc.getMemberDocument ());
		assertEquals(jdfDocResult.getDocumentElement().getNamespaceURI(),
				jdfDoc.getDocumentElement().getNamespaceURI());

	}

	public void testEmptyNamespace()
	{
		JDFDoc jdfDoc = new JDFDoc(ElementName.JDF);

		jdfDoc.write2File(sm_dirTestDataTemp + "test.jdf", 0, true);

		FileReader reader = null;
		try
		{
			reader = new FileReader(sm_dirTestDataTemp + "test.jdf");

			char[] cbuf = new char[500];
			int readChars = reader.read(cbuf);

			if (readChars >= 0)
			{
				String xml = new String(cbuf);
				int startPoint = xml.indexOf("<AuditPool>");
				boolean namespaceOk = startPoint >= 0;

				// Xerces 2.0.1 test.jdf contains <AuditPool xmlns="">
				// Xerces 2.2.1 test.jdf contains <AuditPool>, which is correct

				if (!namespaceOk)
				{
					String help = xml.substring(xml.indexOf("<AuditPool"));
					assertTrue(
							"This version of Apache Xerces has a namespace problem : "
									+ help, namespaceOk);
				}
			}
		} catch (FileNotFoundException e)
		{
			e.printStackTrace();
		} catch (IOException e)
		{
			e.printStackTrace();
		} finally
		{
			try
			{
				if (reader != null)
				{
					reader.close();
				}
			} catch (IOException e1)
			{
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}
	}
}
