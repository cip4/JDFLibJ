/*
 *
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

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.io.File;
import java.util.List;
import java.util.zip.DataFormatException;

import org.cip4.jdflib.JDFTestCaseBase;
import org.cip4.jdflib.auto.JDFAutoIdentificationField.EnumEncoding;
import org.cip4.jdflib.core.JDFElement.EnumVersion;
import org.cip4.jdflib.core.JDFResourceLink.EnumUsage;
import org.cip4.jdflib.datatypes.JDFIntegerRangeList;
import org.cip4.jdflib.node.JDFNode;
import org.cip4.jdflib.node.JDFNode.EnumType;
import org.cip4.jdflib.resource.JDFResource;
import org.cip4.jdflib.resource.JDFResource.EnumPartUsage;
import org.cip4.jdflib.resource.JDFResource.EnumResourceClass;
import org.cip4.jdflib.resource.process.JDFExposedMedia;
import org.cip4.jdflib.resource.process.JDFIdentificationField;
import org.cip4.jdflib.resource.process.JDFLot;
import org.cip4.jdflib.resource.process.JDFMiscConsumable;
import org.cip4.jdflib.resource.process.JDFPreview;
import org.cip4.jdflib.resource.process.JDFRunList;
import org.cip4.jdflib.util.FileUtil;
import org.cip4.jdflib.util.StringUtil;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

/**
 * @author Rainer Prosi, Heidelberger Druckmaschinen
 *
 */
public class JDFSchemaTest extends JDFTestCaseBase
{
	JDFParser p = null;

	/**
	 * parse a simple JDF against all official schemas this test catches corrupt xml schemas
	 *
	 */
	@Test
	void testSchema()
	{
		final JDFDoc d = p.parseFile(new File(sm_dirTestData + "job.jdf"));
		assertNotNull(d);
	}

	/**
	 * parse a simple JDF against all official schemas this test catches corrupt xml schemas
	 *
	 */
	@Test
	void testDieMaking()
	{
		final JDFDoc d0 = new JDFDoc(ElementName.JDF);
		final JDFNode n = d0.getJDFRoot();
		n.setType(EnumType.DieMaking);
		n.addResource(ElementName.TOOL, EnumUsage.Output);
		final String s = d0.write2String(2);
		final JDFDoc d = p.parseString(s);
		assertNotNull(d);
		assertNull(p.m_lastExcept);
	}

	/**
	 * parse a simple JDF against all official schemas this test catches corrupt xml schemas
	 *
	 */
	@Test
	void testLot()
	{
		final JDFDoc d0 = new JDFDoc(ElementName.JDF);
		final JDFNode n = d0.getJDFRoot();
		n.setType(EnumType.ConventionalPrinting);
		final JDFResource r = n.addResource(ElementName.MEDIA, EnumUsage.Input);
		final JDFResourceLink rl = n.getLink(r, null);
		final JDFLot lot = rl.appendLot();
		lot.setText(" ");
		lot.setLotID("lllll");
		String s = d0.write2String(2);
		s = StringUtil.replaceString(s, "<Lot LotID=\"lllll\"/>", "<Lot LotID=\"lllll\">  \n  </Lot>");
		final JDFDoc d = p.parseString(s);
		assertNotNull(d);
		assertNull(p.m_lastExcept);
	}

	/**
	 *
	 */
	@Test
	void testVariableIntent()
	{
		final JDFDoc d0 = new JDFDoc(ElementName.JDF);
		final JDFNode n = d0.getJDFRoot();
		n.setType(EnumType.Product);
		final JDFResource r = n.addResource(ElementName.VARIABLEINTENT, EnumUsage.Input);
		r.setResourceClass(EnumResourceClass.Intent);
		final KElement vt = r.appendElement("VariableType");
		vt.setAttribute(AttributeName.ACTUAL, "AddressField");
		vt.setAttribute(AttributeName.DATATYPE, "EnumerationSpan");
		final String s = d0.write2String(2);
		final JDFDoc d = p.parseString(s);
		assertNotNull(d);
		assertNull(p.m_lastExcept);
	}

	/**
	 *
	 */
	@Test
	void testCertification()
	{
		final JDFDoc d0 = new JDFDoc(ElementName.JDF);
		final JDFNode n = d0.getJDFRoot();
		n.setType(EnumType.ConventionalPrinting);
		final JDFResource r = n.addResource(ElementName.MEDIA, EnumUsage.Input);
		final KElement c = r.appendElement(ElementName.CERTIFICATION);
		c.setAttribute("Claim", "AddressField");
		final String s = d0.write2String(2);
		final JDFDoc d = p.parseString(s);
		assertNotNull(d);
		assertNull(p.m_lastExcept);
	}

	/**
	 * parse a simple JDF against all official schemas this test catches corrupt xml schemas
	 *
	 */
	@Test
	void testPlateType()
	{
		final JDFDoc d = p.parseFile(sm_dirTestData + "Example8-15.jdf");
		assertNotNull(d, sm_dirTestData + "Example8-15.jdf");
		assertNull(p.m_lastExcept);
	}

	/**
	 * parse a simple JDF against all official schemas this test catches corrupt xml schemas
	 *
	 */
	// @Test
	// void testSetIndex()
	// {
	// final JDFDoc d = p.parseFile(sm_dirTestData + "ExampleO-5.3.1.jdf");
	// assertNotNull(d);
	// assertNull(p.m_lastExcept);
	// }

	/**
	 * parse a simple JDF against all official schemas this test catches corrupt xml schemas
	 *
	 */
	@Test
	void testInlineClass()
	{
		final JDFDoc d0 = new JDFDoc("JDF");
		final JDFNode n = d0.getJDFRoot();
		n.setType(EnumType.ImageSetting);
		final JDFExposedMedia xm = (JDFExposedMedia) n.addResource(ElementName.EXPOSEDMEDIA, EnumUsage.Output);
		xm.appendMedia().setResourceClass(EnumResourceClass.Consumable);
		final JDFRunList rl = (JDFRunList) n.addResource(ElementName.RUNLIST, EnumUsage.Input);
		rl.addRun("file://foo.pdf", 0, 33);
		final String s = d0.write2String(2);
		final JDFDoc d = p.parseString(s);
		assertNotNull(d);
		assertNull(p.m_lastExcept);
	}

	/**
	 * parse a simple JDF against all official schemas this test catches corrupt xml schemas
	 *
	 */
	@Test
	void testCasingIn()
	{
		final JDFDoc d0 = new JDFDoc("JDF");
		final JDFNode n = d0.getJDFRoot();
		n.setType(EnumType.CasingIn);
		n.addResource(ElementName.CASINGINPARAMS, EnumUsage.Input);
		final String s = d0.write2String(2);
		final JDFDoc d = p.parseString(s);
		assertNotNull(d);
		assertNull(p.m_lastExcept);
	}

	/**
	 * parse a simple JDF against all official schemas this test catches corrupt xml schemas
	 *
	 */
	@Test
	void testPreviewResource()
	{
		final JDFDoc d0 = new JDFDoc("JDF");
		final JDFNode n = d0.getJDFRoot();
		n.setType(EnumType.PreviewGeneration);
		final JDFPreview pv = (JDFPreview) n.addResource(ElementName.PREVIEW, EnumUsage.Output);
		pv.setPartUsage(EnumPartUsage.Explicit);
		final String s = d0.write2String(2);
		final JDFDoc d = p.parseString(s);
		assertNotNull(d);
		assertNull(p.m_lastExcept);
	}

	/**
	 * parse a simple JDF against all official schemas this test catches corrupt xml schemas
	 *
	 */
	@Test
	void testSchemafolder()
	{
		final File[] jdfs = FileUtil.listFilesWithExtension(new File(sm_dirTestData + "schema"), "jdf");

		for (final File jdf : jdfs)
		{
			final JDFDoc d = p.parseFile(jdf);
			assertNotNull(d);
			log.info("Parsing: " + jdf.getName());
			assertNull(p.m_lastExcept, "schema error in: " + jdf.getName());
		}
	}

	/**
	 * parse a simple JDF against all official schemas this test catches corrupt xml schemas
	 *
	 */
	@Test
	void testSchemafolderJMF()
	{
		final File[] jdfs = FileUtil.listFilesWithExtension(new File(sm_dirTestData + "schema"), "jmf");

		for (final File jdf : jdfs)
		{
			final JDFDoc d = p.parseFile(jdf);
			assertNotNull(d);
			log.info("Parsing: " + jdf.getName());
			assertNull(p.m_lastExcept, "schema error in: " + jdf.getName());
		}
	}

	/**
	 * parse a simple JDF against all official schemas this test catches corrupt xml schemas
	 *
	 */
	@Test
	void testIdentificationField()
	{
		final JDFDoc d0 = new JDFDoc(ElementName.JDF);
		final JDFNode n = d0.getJDFRoot();
		n.setType(EnumType.Verification);
		final JDFIdentificationField idf = (JDFIdentificationField) n.addResource(ElementName.IDENTIFICATIONFIELD, EnumUsage.Input);
		idf.setEncoding(EnumEncoding.Barcode);
		idf.setEncodingDetails("D1");
		idf.setPartUsage(EnumPartUsage.Explicit);
		final String s = d0.write2String(2);
		final JDFDoc d = p.parseString(s);
		assertNotNull(d);
		assertNull(p.m_lastExcept);
	}

	/**
	 * parse a simple JDF against all official schemas this test catches corrupt xml schemas
	 *
	 * @throws DataFormatException
	 *
	 */
	@Test
	void testIntegerrange() throws DataFormatException
	{
		final JDFDoc d0 = new JDFDoc(ElementName.JDF);
		final JDFNode n = d0.getJDFRoot();
		n.setType(EnumType.ColorSpaceConversion);
		final JDFRunList rl = (JDFRunList) n.addResource(ElementName.RUNLIST, EnumUsage.Input);
		rl.setPages(new JDFIntegerRangeList("1 ~ 3 5 7 9~-1"));
		final String s = d0.write2String(2);
		final JDFDoc d = p.parseString(s);
		assertNotNull(d);
		assertNull(p.m_lastExcept);
	}

	/**
	 * parse a simple JDF against all official schemas this test catches corrupt xml schemas
	 *
	 */
	@Test
	void testMiscConsumableIdentificationField()
	{
		final JDFDoc d0 = new JDFDoc(ElementName.JDF);
		final JDFNode n = d0.getJDFRoot();
		n.setType(EnumType.Verification);
		final JDFMiscConsumable misc = (JDFMiscConsumable) n.addResource(ElementName.MISCCONSUMABLE, EnumUsage.Input);
		misc.setConsumableType("typ");
		final JDFIdentificationField idf = misc.appendIdentificationField();
		idf.setEncoding(EnumEncoding.Barcode);
		idf.setEncodingDetails("D1");
		final String s = d0.write2String(2);
		final JDFDoc d = p.parseString(s);
		assertNotNull(d);
		assertNull(p.m_lastExcept);
	}

	/**
	 * parse a simple JDF against all official schemas this test catches corrupt xml schemas
	 *
	 */
	@Test
	void testTemplate()
	{
		final JDFDoc d0 = new JDFDoc("JDF");
		final JDFNode n = d0.getJDFRoot();
		n.setType(EnumType.ProcessGroup);
		n.setTemplateID("T1");
		n.setTemplateVersion("1.0");
		final String s = d0.write2String(2);
		final JDFDoc d = p.parseString(s);
		assertNotNull(d);
		assertNull(p.m_lastExcept);
	}

	/**
	 *
	 */
	@Test
	void testVersionBad()
	{
		final JDFDoc d0 = new JDFDoc(ElementName.JDF);
		final JDFNode n = d0.getJDFRoot();
		n.setVersion(EnumVersion.Version_1_9);
		n.setType(EnumType.ProcessGroup);
		final String s = d0.write2String(2);
		final JDFDoc d = p.parseString(s);
		assertNotNull(d);
		assertNotNull(p.m_lastExcept);
	}

	/**
	 *
	 */
	@Test
	void testVersion()
	{
		final JDFDoc d0 = new JDFDoc(ElementName.JDF);
		final JDFNode n = d0.getJDFRoot();
		n.setVersion(EnumVersion.Version_1_6);
		n.setType(EnumType.ProcessGroup);
		final String s = d0.write2String(2);
		final JDFDoc d = p.parseString(s);
		assertNotNull(d);
		assertNull(p.m_lastExcept);
	}

	/**
	 *
	 * @see JDFTestCaseBase#setUp()
	 */
	@Override
	@BeforeEach
	public void setUp() throws Exception
	{
		super.setUp();
		KElement.setLongID(false);
		final File foo = new File(sm_dirTestSchema).getParentFile();
		assertTrue(foo.isDirectory(), "please mount the svn schema parallel to jdflibJ");
		p = getSchemaParser(defaultVersion);
	}

	/**
	 *
	 */
	@Test
	@Disabled
	void testSamples()
	{
		final List<File> l = FileUtil.listFilesInTree(new File("/gitreps/jdfschema/samples"), "*.j*");
		for (final File f : l)
		{
			if (!"further".equalsIgnoreCase(f.getParentFile().getName()))
			{
				if ("actionpool.jdf".equalsIgnoreCase(f.getName()))
					continue;
				if ("featurepool.jdf".equalsIgnoreCase(f.getName()))
					continue;
				if ("updateJDFCommand.jmf".equalsIgnoreCase(f.getName()))
					continue;
				if ("automatedImpositionMarkObject.jdf".equalsIgnoreCase(f.getName()))
					continue;
				if (f.getName().toLowerCase().indexOf("invalid") >= 0)
					continue;
				if (StringUtil.matchesIgnoreCase(f.getName(), "custom*.jmf"))
					continue;
				final JDFDoc doc = p.parseFile(f);
				assertNotNull(doc, f.getName());
				assertEquals("Valid", doc.getValidationResult().getRoot().getAttribute("ValidationResult"), f.getAbsolutePath());
			}
		}
	}

}
