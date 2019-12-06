/**
 * The CIP4 Software License, Version 1.0
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
package org.cip4.jdflib.extensions.examples;

import org.cip4.jdflib.JDFTestCaseBase;
import org.cip4.jdflib.auto.JDFAutoMedia.EnumMediaType;
import org.cip4.jdflib.core.AttributeName;
import org.cip4.jdflib.core.ElementName;
import org.cip4.jdflib.core.JDFElement.EnumValidationLevel;
import org.cip4.jdflib.core.JDFElement.EnumVersion;
import org.cip4.jdflib.core.JDFResourceLink.EnumUsage;
import org.cip4.jdflib.core.KElement;
import org.cip4.jdflib.datatypes.JDFAttributeMap;
import org.cip4.jdflib.extensions.ResourceHelper;
import org.cip4.jdflib.extensions.SetHelper;
import org.cip4.jdflib.extensions.XJDFConstants;
import org.cip4.jdflib.extensions.XJDFHelper;
import org.cip4.jdflib.node.JDFNode.EnumType;
import org.cip4.jdflib.resource.process.JDFExpr;
import org.cip4.jdflib.resource.process.JDFFileSpec;
import org.cip4.jdflib.resource.process.JDFMedia;
import org.cip4.jdflib.resource.process.JDFMetadataMap;
import org.cip4.jdflib.resource.process.JDFRunList;
import org.cip4.jdflib.util.UrlUtil;
import org.junit.Test;

/**
 *
 * @author rainer prosi
 *
 */
public class XJDFRunListTest extends JDFTestCaseBase
{
	/**
	 *
	 */
	@Test
	public void testFilterRunIndex()
	{
		final XJDFHelper xjdfHelper = new XJDFHelper(ElementName.RUNLIST, "Impo", null);
		xjdfHelper.setTypes(EnumType.Product.getName());
		final SetHelper shRL = xjdfHelper.getCreateSet(XJDFConstants.Resource, ElementName.RUNLIST, EnumUsage.Input);
		final JDFRunList rl1 = (JDFRunList) shRL.appendPartition(new JDFAttributeMap(AttributeName.RUN, "r1"), true).getResource();
		rl1.setNPage(2);
		rl1.setXPathAttribute("FileSpec/@URL", "file:///indir/file1.pdf");
		final JDFRunList rl2 = (JDFRunList) shRL.appendPartition(new JDFAttributeMap(AttributeName.RUN, "r2"), true).getResource();
		rl2.setNPage(8);
		rl2.setXPathAttribute("FileSpec/@URL", "file:///indir/file2.pdf");

		final SetHelper shOut = xjdfHelper.getCreateSet(XJDFConstants.Resource, ElementName.RUNLIST, EnumUsage.Output);
		shOut.removePartitions();
		final ResourceHelper rh = shOut.appendPartition(new JDFAttributeMap(AttributeName.RUNINDEX, "0 0"), true);
		rh.appendPartMap(new JDFAttributeMap(AttributeName.RUNINDEX, "3 6"));
		final JDFRunList rlo = (JDFRunList) rh.getResource();
		rl2.setXPathAttribute("FileSpec/@URL", "file:///outdir/output.pdf");
		xjdfHelper.cleanUp();
		setSnippet(shOut, true);
		setSnippet(shRL, true);
		writeTest(xjdfHelper, "resources/filteringPartsofaRunList.xjdf");
	}

	/**
	 *
	 */
	@Test
	public void testFileTemplate()
	{
		final XJDFHelper xjdfHelper = new XJDFHelper(ElementName.RUNLIST, "Format", null);
		xjdfHelper.setTypes(EnumType.Product.getName());
		final SetHelper shRL = xjdfHelper.getCreateSet(XJDFConstants.Resource, ElementName.RUNLIST, EnumUsage.Input);
		final KElement ruli = shRL.appendPartition(null, true).getResource();
		final JDFFileSpec fs = (JDFFileSpec) ruli.appendElement(ElementName.FILESPEC);
		fs.setFileFormat("file://myserver/next/%s/m%4.i.pdf");
		fs.setFileTemplate("JobID DocIndex");
		fs.setMimeType(UrlUtil.APPLICATION_PDF);
		xjdfHelper.cleanUp();
		setSnippet(ruli, true);
		writeTest(xjdfHelper, "ap_generatingstrings/FileFormat.xjdf");
	}

	/**
	 *
	 */
	@Test
	public void testMetaDataMap()
	{
		final XJDFHelper xjdfHelper = new XJDFHelper(ElementName.RUNLIST, "Metadata", null);
		xjdfHelper.setTypes(EnumType.DigitalPrinting.getName());
		final SetHelper shRL = xjdfHelper.getCreateSet(XJDFConstants.Resource, ElementName.RUNLIST, EnumUsage.Input);
		final JDFRunList runList = (JDFRunList) shRL.appendPartition(null, true).getResource();
		final JDFFileSpec fs = (JDFFileSpec) runList.appendElement(ElementName.FILESPEC);
		fs.setURL("file://host/file/data.pdf");
		final JDFMetadataMap md = (JDFMetadataMap) runList.appendElement(ElementName.METADATAMAP);
		md.setName(AttributeName.METADATA);
		md.setValueFormat("%s_%s");
		md.setValueTemplate("gender status");
		JDFExpr x = md.appendExpr();
		x.setName("gender");
		x.setPath("/doc/record/Geschlecht");
		x = md.appendExpr();
		x.setName("status");
		x.setPath("/doc/record/Status");

		final SetHelper shComp = xjdfHelper.getCreateSet(XJDFConstants.Resource, ElementName.COMPONENT, EnumUsage.Input);
		ResourceHelper rh = shComp.appendPartition(AttributeName.METADATA, "Mann_Platin", true);
		rh.setExternalID("BlueGoodPaper");
		rh = shComp.appendPartition(AttributeName.METADATA, "Mann(.)*", true);
		rh.setExternalID("BlueCheapPaper");
		rh = shComp.appendPartition(AttributeName.METADATA, "Frau_Platin", true);
		rh.setExternalID("PinkGoodPaper");
		rh = shComp.appendPartition(AttributeName.METADATA, "Frau_(.)*", true);
		rh.setExternalID("PinkCheapPaper");
		xjdfHelper.cleanUp();
		setSnippet(shRL, true);
		setSnippet(shComp, true);
		writeTest(xjdfHelper, "subelements/runListMetaDataMap.xjdf");
	}

	/**
	*
	*/
	@Test
	public final void testSingleRunList()
	{
		final XJDFHelper xjdfHelper = new XJDFHelper("RunList", null, null);
		xjdfHelper.setTypes(EnumType.Imposition.getName());
		final SetHelper rlh = xjdfHelper.getCreateSet(ElementName.RUNLIST, EnumUsage.Input, null);
		final ResourceHelper runh = rlh.appendPartition(null, null, true);
		final JDFRunList rl = (JDFRunList) runh.getResource();
		rl.setAttribute(AttributeName.PAGES, "0 -1");
		rl.appendElement(ElementName.FILESPEC).setAttribute(AttributeName.URL, "File:///in/colortest.pdf");
		cleanSnippets(xjdfHelper);
		writeTest(xjdfHelper, "resources/RunListSimple.xjdf");

	}

	/**
	*
	*/
	@Test
	public final void testMultiRunList()
	{
		final XJDFHelper xjdfHelper = new XJDFHelper("RunList", null, null);
		xjdfHelper.setTypes(EnumType.Imposition.getName());
		final SetHelper rlh = xjdfHelper.getCreateSet(ElementName.RUNLIST, EnumUsage.Input, null);
		ResourceHelper runh = rlh.appendPartition(AttributeName.RUN, "R1", true);
		JDFRunList rl = (JDFRunList) runh.getResource();
		rl.setNPage(6);
		rl.setAttribute(AttributeName.PAGES, "0 5");
		rl.appendElement(ElementName.FILESPEC).setAttribute(AttributeName.URL, "File:///File1.pdf");
		runh = rlh.appendPartition(AttributeName.RUN, "R2", true);
		rl = (JDFRunList) runh.getResource();
		rl.setAttribute(AttributeName.PAGES, "0 -1");
		rl.appendElement(ElementName.FILESPEC).setAttribute(AttributeName.URL, "File:///File2.pdf");

		cleanSnippets(xjdfHelper);
		writeTest(xjdfHelper, "resources/RunList2.xjdf");

	}

	/**
	*
	*/
	@Test
	public final void testMultiSet()
	{
		final XJDFHelper xjdfHelper = new XJDFHelper(ElementName.RUNLIST, null, null);
		xjdfHelper.setTypes(EnumType.DigitalPrinting.getName());
		xjdfHelper.setVersion(EnumVersion.Version_2_1);
		final SetHelper rlh = xjdfHelper.getCreateSet(ElementName.RUNLIST, EnumUsage.Input, null);
		final ResourceHelper runh = rlh.appendPartition(null, true);
		final JDFRunList rl = (JDFRunList) runh.getResource();
		rl.setNPage(480);
		rl.setAttribute("DocPages", "2 8 2");
		rl.appendElement(ElementName.FILESPEC).setAttribute(AttributeName.URL, "File:///ManyBrochures.pdf");

		final SetHelper setMedia = xjdfHelper.getCreateSet(ElementName.MEDIA, null);
		final ResourceHelper rhm0 = setMedia.getCreatePartition(new JDFAttributeMap(AttributeName.DOCINDEX, "0 0"), true);
		rhm0.appendPartMap(new JDFAttributeMap(AttributeName.DOCINDEX, "2 2"));
		final JDFMedia m0 = (JDFMedia) rhm0.getResource();
		m0.setMediaType(EnumMediaType.Paper);
		m0.setWeight(150);
		rhm0.setDescriptiveName("Media for front and back covers");
		final ResourceHelper rhm1 = setMedia.getCreatePartition(new JDFAttributeMap(AttributeName.DOCINDEX, "1 1"), true);
		rhm1.setDescriptiveName("Media for inner pages");
		final String idm0 = rhm0.getRoot().appendAnchor("M02");

		final JDFMedia m1 = (JDFMedia) rhm1.getResource();
		m1.setWeight(100);
		m1.setMediaType(EnumMediaType.Paper);
		final String idm1 = rhm1.getRoot().appendAnchor("M1");

		final SetHelper setCompIn = xjdfHelper.getCreateSet(ElementName.COMPONENT, EnumUsage.Input);
		final ResourceHelper rhc0 = setCompIn.getCreatePartition(new JDFAttributeMap(AttributeName.DOCINDEX, "0 0"), true);
		rhc0.appendPartMap(new JDFAttributeMap(AttributeName.DOCINDEX, "2 2"));
		rhc0.setDescriptiveName("Physical media for front and back covers");
		rhc0.getResource().setAttribute("MediaRef", idm0);
		final ResourceHelper rhc1 = setCompIn.getCreatePartition(new JDFAttributeMap(AttributeName.DOCINDEX, "1 1"), true);
		rhc1.getResource().setAttribute("MediaRef", idm1);

		cleanSnippets(xjdfHelper);
		writeRoundTripX(xjdfHelper, "resources/DocPages", EnumValidationLevel.Complete);

	}

	/**
	 * @see org.cip4.jdflib.JDFTestCaseBase#setUp()
	 */
	@Override
	public void setUp() throws Exception
	{
		super.setUp();
		KElement.setLongID(false);
	}
}
