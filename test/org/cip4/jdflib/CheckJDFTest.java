/*
 *
 * The CIP4 Software License, Version 1.0
 *
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
/*
 * Created on Jun 13, 2005
 */
package org.cip4.jdflib;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.List;

import org.cip4.jdflib.core.ElementName;
import org.cip4.jdflib.core.JDFDoc;
import org.cip4.jdflib.core.JDFElement.EnumValidationLevel;
import org.cip4.jdflib.core.JDFResourceLink;
import org.cip4.jdflib.core.JDFResourceLink.EnumUsage;
import org.cip4.jdflib.core.KElement;
import org.cip4.jdflib.core.VString;
import org.cip4.jdflib.core.XMLDoc;
import org.cip4.jdflib.node.JDFNode;
import org.cip4.jdflib.node.JDFNode.EnumType;
import org.cip4.jdflib.resource.JDFResource;
import org.cip4.jdflib.resource.JDFResource.EnumPartIDKey;
import org.cip4.jdflib.resource.process.JDFPreview;
import org.cip4.jdflib.resource.process.JDFRunList;
import org.cip4.jdflib.util.FileUtil;
import org.cip4.jdflib.util.StatusCounter;
import org.cip4.jdflib.validate.ICheckValidator;
import org.cip4.jdflib.validate.ICheckValidatorFactory;
import org.cip4.jdflib.validate.JDFValidator;

/**
 * @author Claes Buckwalter (clabu@itn.liu.se)
 */
public class CheckJDFTest extends JDFTestCaseBase
{

	/**
	 * 
	 */
	public void testValidate()
	{
		final File jdfFile = new File(sm_dirTestData + "job.jdf");
		System.out.println("Checking JDF: " + jdfFile.getAbsolutePath());
		// TODO There is a bug in MyArgs that prevents command line arguments
		// containing hyphens from being parsed correctly
		final String[] args = { jdfFile.getAbsolutePath(), "-q", "-c" };

		final CheckJDF checker = new CheckJDF();
		checker.validate(args, null);
	}

	protected class ValidFactory implements ICheckValidatorFactory
	{
		ICheckValidator cv = new MyValid();

		protected class MyValid implements ICheckValidator
		{

			/**
			 * very simple check for the string "snafu"
			 */
			public boolean validate(final KElement toCheck, final KElement report)
			{
				final boolean b = toCheck.toString().toUpperCase().indexOf("SNAFU") >= 0;
				if (b)
				{
					report.setAttribute("IsValid", false, null);
					report.setAttribute("ErrorType", "PrivateValidation", null);
					report.setAttribute("Message", "MyValid: Element contains snafu!!!", null);
					System.out.println("MyValid: Element contains snafu!!! " + toCheck.getNodeName());
				}
				return !b;
			}

		}

		/*
		 * (non-Javadoc)
		 * 
		 * @see org.cip4.jdflib.validate.ICheckValidatorFactory#getValidator(org. cip4.jdflib.core.KElement)
		 */
		public ICheckValidator getValidator(final KElement toCheck)
		{
			// always the snafu checker...
			return cv;
		}
	}

	/**
	 * 
	 */
	public void testPrivateValidate()
	{
		final StatusCounter sc = new StatusCounter(null, null, null);
		sc.setEvent("id1", "good", "blah");
		final JDFValidator c = new JDFValidator();
		c.setValidatorFactory(new ValidFactory());
		JDFDoc d = sc.getDocJMFNotification(true);
		assertTrue(c.isValid(d));
		sc.setEvent("id2", "oops", "Snafu");
		d = sc.getDocJMFNotification(true);
		// XMLDoc report=
		c.processSingleDocument(d);
		assertFalse(c.isValid(d));

	}

	/**
	 * @author Eniac
	 */

	public void testCmdLineChecker()
	{
		final String checkSavePath = sm_dirTestData + "NarrowWeb.jdf";
		final String checkSavePathResult = sm_dirTestDataTemp + "NarrowWeb.out.xml";
		final String schemaLocator = sm_dirTestSchema + "JDF.xsd";// .toURL().
		// toExternalForm();

		final String checkSaveLocator = checkSavePathResult;

		final CheckJDF checker = new CheckJDF();

		final List<String> args = new ArrayList<String>();
		args.add(checkSavePath);
		args.add("-x" + checkSaveLocator);
		args.add("-L" + schemaLocator);
		args.add("-qcv");
		final String[] commandLineArgs = args.toArray(new String[args.size()]);

		String logStr = "";
		for (int i = 0; i < commandLineArgs.length; i++)
		{
			logStr += commandLineArgs[i] + " ";
		}

		System.out.println(logStr);

		checker.validate(commandLineArgs, null);

		System.out.println("end");
	}

	// /////////////////////////////////////////////////////////////////////////

	/**
	 * 
	 */
	public void testProcessSingleFile()
	{
		processSingleFile("test/data/matsch.jdf", false, EnumValidationLevel.Complete);
		processSingleFile("test/data/matsch.jdf", false, EnumValidationLevel.RecursiveComplete);
	}

	// /////////////////////////////////////////////////////////////

	private void processSingleFile(final String fileName, final boolean bShouldValidate, final EnumValidationLevel level)
	{
		JDFValidator checkJDF = new JDFValidator();
		checkJDF.setPrint(false);
		checkJDF.bQuiet = true;
		checkJDF.level = level != null ? level : EnumValidationLevel.Complete;
		final XMLDoc schemaValidationResult = checkJDF.processSingleFile(fileName);
		final KElement root = schemaValidationResult.getRoot();
		assertNotNull(root.getXPathElement("TestFile/SchemaValidationOutput"));
		assertNotNull(root.getXPathElement("TestFile/CheckJDFOutput"));

		checkJDF = new JDFValidator();
		checkJDF.setPrint(false);
		checkJDF.bQuiet = true;
		checkJDF.level = EnumValidationLevel.Complete;
		checkJDF.processSingleFile(fileName);
		assertNotNull(root);

		if (bShouldValidate)
		{
			assertEquals(fileName + " should validate", root.getXPathAttribute("TestFile/CheckJDFOutput/@IsValid", ""), "true");
		}
		else
		{
			assertEquals(fileName + " should not validate", root.getXPathAttribute("TestFile/CheckJDFOutput/@IsValid", ""), "false");
		}

		// now repeat including schema
		checkJDF.setJDFSchemaLocation(new File(sm_dirTestSchema + "JDF.xsd"));
		checkJDF.processSingleDocument(null);
		assertNotNull(root);

		if (bShouldValidate)
		{
			assertEquals(root.getXPathAttribute("TestFile/CheckJDFOutput/@IsValid", ""), "true");
		}
		else
		{
			assertEquals(root.getXPathAttribute("TestFile/CheckJDFOutput/@IsValid", ""), "false");
		}
	}

	/**
	 * 
	 */
	public void testProcessAllFiles()
	{
		final JDFValidator checkJDF = new JDFValidator();
		checkJDF.setPrint(false);
		checkJDF.bQuiet = true;
		checkJDF.level = EnumValidationLevel.Complete;
		final VString files = new VString();
		files.add("test/data/job.jdf");
		checkJDF.allFiles = files;
		final XMLDoc schemaValidationResult = checkJDF.processAllFiles();
		assertNotNull(schemaValidationResult.getRoot().getXPathElement("TestFile/SchemaValidationOutput"));
		assertNotNull(schemaValidationResult.getRoot().getXPathElement("TestFile/CheckJDFOutput"));

	}

	/**
	 * @throws Exception
	 */
	public void testValidateStream() throws Exception
	{
		final File jdfFile = new File(sm_dirTestData + "job.jdf");
		final FileInputStream ins = new FileInputStream(jdfFile);
		System.out.println("Checking JDF: " + jdfFile.getAbsolutePath());
		// TODO There is a bug in MyArgs that prevents command line arguments
		// containing hyphens from being parsed correctly
		final String[] args = { jdfFile.getAbsolutePath(), "-q", "-c" };

		final CheckJDF checker = new CheckJDF();
		checker.validate(args, ins);
	}

	/**
	 * 
	 */
	public void testBadResLink()
	{
		final JDFDoc d = new JDFDoc("JDF");
		final JDFNode n = d.getJDFRoot();
		n.setType(EnumType.Stitching);
		final JDFResource r = n.addResource(ElementName.STITCHINGPARAMS, EnumUsage.Input);
		final JDFResourceLink rl = n.getLink(r, null);
		assertTrue(rl.isValid(EnumValidationLevel.Complete));
		assertTrue(n.isValid(EnumValidationLevel.Incomplete));
		rl.setrRef("badLink");
		assertFalse(rl.isValid(EnumValidationLevel.Complete));
		assertFalse(n.isValid(EnumValidationLevel.Complete));
		final JDFValidator cj = new JDFValidator();
		final XMLDoc res = cj.processSingleDocument(d);
		final KElement root = res.getRoot();
		final String s = root.toString();
		assertTrue(s.indexOf("Dangling") > 0);

	}

	/**
	 * 
	 */
	public void testValidateZip()
	{
		final File zip = new File(sm_dirTestData + "checkJDF.zip");
		final JDFValidator checker = new JDFValidator();
		final XMLDoc d = checker.processZipFile(zip);
		final KElement root = d.getRoot();
		System.out.println("mem new:   " + getCurrentMem() + " " + mem);
		// TODO fix mem leak in zip assertEquals(getCurrentMem(), mem, 1000000);

		assertEquals("checkJDF.zip has 17 files", root.numChildElements("TestFile", null), 17);
	}

	/**
	 * @throws Exception
	 */
	public void testValidateMime() throws Exception
	{

		final File mim = new File(sm_dirTestData + "checkjdf.mjm");
		final JDFValidator checker = new JDFValidator();
		final FileInputStream is = new FileInputStream(mim);
		final XMLDoc d = checker.processMimeStream(is);
		final KElement root = d.getRoot();
		assertEquals("checkJDF.mjm has 2 files", root.numChildElements("TestFile", null), 2);
	}

	/**
	 * 
	 */
	public void testMainQuietComplete()
	{
		final File jdfFile = new File(sm_dirTestData + "job.jdf");
		System.out.println("Checking JDF: " + jdfFile.getAbsolutePath());
		// TODO There is a bug in MyArgs that prevents command line arguments
		// containing hyphens from being parsed correctly
		final String[] args = { jdfFile.getAbsolutePath(), "-q", "-c" };
		CheckJDF.main(args);

	}

	/**
	 * 
	 */
	public void testMainQuietCompleteXMLReport()
	{
		final File jdfFile = new File(sm_dirTestData + "job.jdf");
		System.out.println("Checking JDF: " + jdfFile.getAbsolutePath());
		// TODO There is a bug in MyArgs that prevents command line arguments
		// containing hyphens from being parsed correctly

		// Run test once
		final String report1 = sm_dirTestDataTemp + "checkjdf_report_1.xml";
		final String[] args1 = { jdfFile.getAbsolutePath(), "-q", "-c", "-x", report1 };
		CheckJDF.main(args1);
		assertTrue(new File(report1).exists());
		// TODO Run test twice and compare XML files

	}

	/**
	 * @throws IOException
	 */
	public void testValidateJMF() throws IOException
	{
		// Write temp JMF
		final String jmf = "<?xml version='1.0' encoding='UTF-8'?><JMF xmlns='http://www.CIP4.org/JDFSchema_1_1' xmlns:xsi='http://www.w3.org/2001/XMLSchema-instance'  SenderID='Alces' TimeStamp='2004-08-30T17:23:00+01:00' Version='1.2'><Query ID='M001' Type='KnownDevices' xsi:type='QueryKnownDevices'><DeviceFilter DeviceDetails='None'/></Query></JMF>";
		final File jmfFile = File.createTempFile("Query-KnownDevices", ".jmf");
		// jmfFile.deleteOnExit();
		final PrintStream out = new PrintStream(new FileOutputStream(jmfFile));
		out.print(jmf);
		out.close();
		assertTrue(jmfFile.exists());

		final File reportFile = File.createTempFile("Queue-KnownDevices-report", ".xml");
		// reportFile.deleteOnExit();

		// Run JDFValidator
		final String[] args = { jmfFile.getAbsolutePath(), "-cq", "-x " + reportFile.getAbsolutePath() };
		final CheckJDF checker = new CheckJDF();
		final XMLDoc d = checker.validate(args, null);
		final KElement dRoot = d.getRoot();
		assertEquals(dRoot.getXPathAttribute("/CheckOutput/TestFile/CheckJDFOutput/@IsValid", null), "true");

		// Check that report exists
		assertTrue(reportFile.exists());
		jmfFile.delete();
		reportFile.delete();
	}

	// //////////////////////////////////////////////////////////////////////////
	/**
	 * 
	 */
	public void testValidateSchemaPreview()
	{
		final JDFDoc doc = new JDFDoc("JDF");
		final JDFValidator checkJDF = new JDFValidator();
		checkJDF.setPrint(false);
		checkJDF.bQuiet = true;
		checkJDF.level = EnumValidationLevel.Incomplete;
		final File foo = FileUtil.getCreateDirectory(sm_dirTestSchema);

		// final File foo = new File(sm_dirTestSchema);
		assertTrue("please mount the svn schema parallel to jdflibJ", foo.isDirectory());
		final File jdfxsd = new File(sm_dirTestSchema + "JDF.xsd");
		checkJDF.setJDFSchemaLocation(jdfxsd);
		final JDFNode n = doc.getJDFRoot();
		n.setType(EnumType.PreviewGeneration);
		final JDFPreview prev = (JDFPreview) n.addResource("Preview", null, EnumUsage.Output, null, null, null, null);
		prev.addPartition(EnumPartIDKey.SheetName, "S1");
		prev.addPartition(EnumPartIDKey.SheetName, "S2").setAttribute("URL", "File://url2");
		prev.setURL("File://foo.bar");
		checkJDF.setIgnorePrivate(false);
		final JDFRunList rl = (JDFRunList) n.addResource("RunList", null, EnumUsage.Input, null, null, null, null);
		//		final JDFPreview pv2 = 
		rl.appendPreview();

		final JDFPreview pv3 = rl.appendPreview();
		pv3.makeRootResource(null, null, true);
		doc.write2File(sm_dirTestDataTemp + "previewSchema.jdf", 0, true);
		final XMLDoc out = checkJDF.processSingleFile(sm_dirTestDataTemp + "previewSchema.jdf");
		assertEquals(out.getRoot().getXPathAttribute("TestFile/SchemaValidationOutput/@ValidationResult", null), "Valid");

	}

	// //////////////////////////////////////////////////////////////////////////
	/**
	 * tests validation of a document that is passed by reference to a document
	 */
	public void testValidatePrivateDoc()
	{
		final JDFDoc doc = new JDFDoc("JDF");
		final JDFValidator checkJDF = new JDFValidator();
		checkJDF.setPrint(false);
		checkJDF.bQuiet = true;
		checkJDF.level = EnumValidationLevel.Incomplete;
		final JDFNode n = doc.getJDFRoot();
		checkJDF.setIgnorePrivate(true);

		n.setAttribute("foo:bar", "foobar", "www.foo.cpm");
		XMLDoc schemaValidationResult = checkJDF.processSingleDocument(doc);
		KElement root = schemaValidationResult.getRoot();
		assertEquals(root.getXPathAttribute("TestFile/CheckJDFOutput/@IsValid", "booboo"), "true");

		n.removeAttribute("bar", "www.foo.cpm");
		n.appendElement("foo:bar", "www.foo.cpm");
		schemaValidationResult = checkJDF.processSingleDocument(doc);
		root = schemaValidationResult.getRoot();
		assertEquals(root.getXPathAttribute("TestFile[2]/CheckJDFOutput/@IsValid", "booboo"), "true");

		n.setAttribute("jdfbar", "thisbebad");
		schemaValidationResult = checkJDF.processSingleDocument(doc);
		root = schemaValidationResult.getRoot();
		assertEquals(root.getXPathAttribute("TestFile[3]/CheckJDFOutput/@IsValid", "booboo"), "false");

		n.removeAttribute("jdfbar", null);
		n.appendElement("jdfbar", null);
		schemaValidationResult = checkJDF.processSingleDocument(doc);
		root = schemaValidationResult.getRoot();
		assertEquals(root.getXPathAttribute("TestFile[4]/CheckJDFOutput/@IsValid", "booboo"), "false");
	}

	// //////////////////////////////////////////////////////////////////////////

	/**
	 * tests validation of a document that is passed by reference to a document
	 */
	public void testValidateDoc()
	{
		JDFDoc doc = new JDFDoc("JDF");
		final JDFValidator checkJDF = new JDFValidator();
		checkJDF.setPrint(false);
		checkJDF.bQuiet = true;
		int v = 0;
		while (true)
		{
			checkJDF.level = EnumValidationLevel.getEnum(v);
			if (checkJDF.level == null)
			{
				break;
			}
			for (int i = 0; i < 3; i++)
			{
				if (i >= 1)
				{
					doc = null;
				}
				final XMLDoc schemaValidationResult = checkJDF.processSingleDocument(doc);
				final KElement root = schemaValidationResult.getRoot();
				assertNotNull(root.getXPathElement("TestFile/SchemaValidationOutput"));
				assertNotNull(root.getXPathElement("TestFile/CheckJDFOutput"));
				assertEquals(root.getXPathAttribute("TestFile/CheckJDFOutput/@IsValid", "booboo"), "true");
			}
			v++;
		}
	}

	/**
	 * tests validation of a document that is passed by reference to a document
	 */
	public void testIsValid()
	{
		JDFDoc doc = new JDFDoc("JDF");
		final JDFNode n = doc.getJDFRoot();
		n.setType(EnumType.ProcessGroup);
		final JDFValidator checkJDF = new JDFValidator();
		checkJDF.setPrint(false);
		checkJDF.bQuiet = true;
		int v = 0;
		while (true)
		{
			checkJDF.level = EnumValidationLevel.getEnum(v);
			if (checkJDF.level == null)
			{
				break;
			}
			for (int i = 0; i < 3; i++)
			{
				if (i >= 1)
				{
					doc = null;
				}
				final boolean bValid = checkJDF.isValid(doc);
				assertTrue(bValid);
			}
			v++;
		}
	}

	/**
	 * tests validation of a document that is passed by reference to a document
	 */
	public void testValidateCombined()
	{
		JDFDoc doc = new JDFDoc("JDF");
		final JDFValidator checkJDF = new JDFValidator();
		checkJDF.setPrint(false);
		checkJDF.bQuiet = true;
		final JDFNode n = doc.getJDFRoot();
		n.setType(EnumType.Combined);
		int v = 0;
		while (true)
		{
			checkJDF.level = EnumValidationLevel.getEnum(v);
			if (checkJDF.level == null)
			{
				break;
			}
			for (int i = 0; i < 3; i++)
			{
				if (i >= 1)
				{
					doc = null;
				}
				final XMLDoc schemaValidationResult = checkJDF.processSingleDocument(doc);
				final KElement root = schemaValidationResult.getRoot();
				assertNotNull(root.getXPathElement("TestFile/SchemaValidationOutput"));
				assertNotNull(root.getXPathElement("TestFile/CheckJDFOutput"));
				assertEquals(root.getXPathAttribute("TestFile/CheckJDFOutput/@IsValid", "booboo"), "true");
			}
			v++;
		}
	}

	// //////////////////////////////////////////////////////////////////////////

	/**
	 * 
	 */
	public void testSamples()
	{
		final File testData = new File(sm_dirTestData + "SampleFiles");
		assertTrue("testData dir", testData.isDirectory());
		final File[] fList = testData.listFiles();

		for (int i = 0; i < fList.length; i++)
		{
			final File file = fList[i];
			// skip directories in CVS environments
			if (file.isDirectory())
			{
				continue;
			}
			// skip non jdf
			if (!file.getPath().endsWith(".jdf") && !file.getPath().endsWith(".jmf") && !file.getPath().endsWith(".xml"))
			{
				continue;
			}
			System.out.println(i + " Parsing: " + file.getPath());
			processSingleFile(file.getPath(), true, null);
			System.out.println(i + " Parsing: " + file.getPath());
			processSingleFile(file.getPath(), true, EnumValidationLevel.RecursiveComplete);
		}
	}

	// //////////////////////////////////////////////////////////////////////////

	/**
	 * 
	 */
	public void testBadSamples()
	{
		final File testData = new File(sm_dirTestData + "BadSampleFiles");
		assertTrue("testData dir", testData.isDirectory());
		final File[] fList = testData.listFiles();

		for (int i = 0; i < fList.length; i++)
		{
			final File file = fList[i];
			// skip directories in CVS environments
			if (file.isDirectory())
			{
				continue;
			}
			// skip schema files
			if (file.getPath().endsWith(".xsd"))
			{
				continue;
			}

			System.out.println("Parsing: " + file.getPath());
			processSingleFile(file.getPath(), false, null);
		}
	}
}
