/*
 * The CIP4 Software License, Version 1.0
 *
 *
 * Copyright (c) 2001-2006 The International Cooperation for the Integration of
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
package org.cip4.jdflib.examples;

import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStream;
import java.util.zip.DataFormatException;

import org.cip4.jdflib.JDFTestCaseBase;
import org.cip4.jdflib.auto.JDFAutoPart.EnumSide;
import org.cip4.jdflib.cformat.PrintfFormat;
import org.cip4.jdflib.core.AttributeName;
import org.cip4.jdflib.core.ElementName;
import org.cip4.jdflib.core.JDFAudit;
import org.cip4.jdflib.core.JDFDoc;
import org.cip4.jdflib.core.JDFElement;
import org.cip4.jdflib.core.VString;
import org.cip4.jdflib.core.JDFResourceLink.EnumUsage;
import org.cip4.jdflib.datatypes.JDFIntegerRangeList;
import org.cip4.jdflib.datatypes.JDFMatrix;
import org.cip4.jdflib.datatypes.JDFRectangle;
import org.cip4.jdflib.datatypes.JDFXYPair;
import org.cip4.jdflib.node.JDFNode;
import org.cip4.jdflib.node.JDFNode.EnumProcessUsage;
import org.cip4.jdflib.node.JDFNode.EnumType;
import org.cip4.jdflib.resource.JDFStrippingParams;
import org.cip4.jdflib.resource.JDFResource.EnumPartIDKey;
import org.cip4.jdflib.resource.JDFResource.EnumResStatus;
import org.cip4.jdflib.resource.process.JDFBinderySignature;
import org.cip4.jdflib.resource.process.JDFComponent;
import org.cip4.jdflib.resource.process.JDFContentObject;
import org.cip4.jdflib.resource.process.JDFFileSpec;
import org.cip4.jdflib.resource.process.JDFLayout;
import org.cip4.jdflib.resource.process.JDFMedia;
import org.cip4.jdflib.resource.process.JDFRunList;
import org.cip4.jdflib.util.StringUtil;

public class AutomatedLayoutTest extends JDFTestCaseBase
{
	private JDFDoc doc;
	private JDFNode n;
	private JDFStrippingParams stripParams;
	private JDFRunList rl = null;
	private JDFLayout lo = null;

	/**
	 * test automated stripping
	 * 
	 * @return
	 */
	public void testAutomatedStripping() throws Exception
	{
		n.setType(EnumType.Stripping);
		stripParams = (JDFStrippingParams) n.addResource(
				ElementName.STRIPPINGPARAMS, null, EnumUsage.Input, null, null,
				null, null);
		stripParams
				.getParentNode_KElement()
				.appendXMLComment(
						"Simple automated StrippingParams with exactly one sheet class\n",
						stripParams);
		JDFStrippingParams stripSheet = (JDFStrippingParams) stripParams
				.addPartition(EnumPartIDKey.SheetName, "Sheets");
		stripSheet.setAttribute(AttributeName.AUTOMATED, true, null);
		final String format = "Sheet%02i";
		stripSheet.setAttribute("NameFormat", format);
		stripSheet.setAttribute("NameTemplate", "SheetNum");
		stripSheet.appendBinderySignature().setNumberUp(new JDFXYPair(2, 2));
		doc.write2File(sm_dirTestDataTemp + "AutomatedStripping.jdf", 2, false);
	}

	/**
     * 
     */
	@Override
	protected void setUp()
	{
		JDFElement.setLongID(false);
		JDFAudit.setStaticAgentName(null);
		JDFAudit.setStaticAgentVersion(null);
		JDFAudit.setStaticAuthor(null);

		doc = new JDFDoc("JDF");
		n = doc.getJDFRoot();
		n.setJobID("JobID");
		rl = (JDFRunList) n.addResource(ElementName.RUNLIST, null,
				EnumUsage.Input, null, null, null, null);

	}

	// ///////////////////////////////////////////////////

	public void testAutomateLayout_PlateSet() throws Exception
	{
		for (int loop = 0; loop < 3; loop++)
		{

			n
					.getAuditPool()
					.appendXMLComment(
							"This is a simple example of an automated layout used for conventional prepress\n"
									+ "The structure is aligned as closely as possible with a static Layout",
							null);

			JDFRunList run = rl.addRun("file://host/data/test.pdf", 0, -1);
			run.setNPage(128);
			rl.setResStatus(EnumResStatus.Available, true);
			rl
					.setDescriptiveName("This is a RunList specifiying 128 pages each in a pdf file.");

			lo = (JDFLayout) n.appendMatchingResource(ElementName.LAYOUT,
					EnumProcessUsage.AnyInput, null);
			lo.setResStatus(EnumResStatus.Available, true);

			lo.setMaxOrd(4);
			lo.setAutomated(true);
			final String format = "Sheet%02i";
			lo.setAttribute("NameFormat", format);
			lo.setAttribute("NameTemplate", "SheetNum");
			lo.appendXMLComment(
					"Simple automated Layout with exactly one sheet\n", null);
			JDFLayout sheet = (JDFLayout) lo.addPartition(
					EnumPartIDKey.SheetName, "Sheet");
			sheet.setDescriptiveName("two sided 2 up sheet");
			JDFLayout sheetFront = (JDFLayout) sheet.addPartition(
					EnumPartIDKey.Side, EnumSide.Front);
			JDFContentObject co = sheetFront.appendContentObject();
			co.setCTM(new JDFMatrix(1, 0, 0, 1, 0, 0));
			co.setOrd(0);
			co.setDescriptiveName("Front 1st, 5th, 9th... Page");

			co = sheetFront.appendContentObject();
			co.setCTM(new JDFMatrix(1, 0, 0, 1, 8.5 * 72, 0));
			co.setOrd(1);
			co.setDescriptiveName("Front 2nd, 6th, 10th... page");

			JDFLayout sheetBack = (JDFLayout) sheet.addPartition(
					EnumPartIDKey.Side, EnumSide.Back);
			co = sheetBack.appendContentObject();
			co.setCTM(new JDFMatrix(1, 0, 0, 1, 0, 0));
			co.setOrd(2);
			co.setDescriptiveName("Back 3rd, 7th, 11th... Page");

			co = sheetBack.appendContentObject();
			co.setCTM(new JDFMatrix(1, 0, 0, 1, 8.5 * 72, 0));
			co.setOrd(3);
			co.setDescriptiveName("Back 4th, 8th, 12th... page");

			JDFRunList rlSheet = (JDFRunList) n.appendMatchingResource(
					ElementName.RUNLIST, EnumProcessUsage.AnyOutput, null);
			rlSheet.setDirectory("file://host/out/");
			if (loop == 0) // instantiate individually
			{
				PrintfFormat fmt = new PrintfFormat(format);
				for (int i = 0; i < 128; i += 4)
				{
					final String sheetName = fmt.tostr(1 + i / 4);
					JDFRunList rlp = (JDFRunList) rlSheet.addPartition(
							EnumPartIDKey.SheetName, sheetName);
					JDFRunList rlF = (JDFRunList) rlp.addPartition(
							EnumPartIDKey.Side, EnumSide.Front);
					rlF.appendLayoutElement().setMimeURL(
							sheetName + "Front.pdf");
					JDFRunList rlB = (JDFRunList) rlp.addPartition(
							EnumPartIDKey.Side, EnumSide.Back);
					rlB.appendLayoutElement()
							.setMimeURL(sheetName + "Back.pdf");
				}
			}
			if (loop == 1) // instantiate individually
			{
				rlSheet.appendLayoutElement().setMimeURL("AllSheets.pdf");
				PrintfFormat fmt = new PrintfFormat(format);
				final JDFIntegerRangeList integerRangeList = new JDFIntegerRangeList();
				for (int i = 0; i < 128; i += 4)
				{
					final String sheetName = fmt.tostr(1 + i / 4);
					JDFRunList rlp = (JDFRunList) rlSheet.addPartition(
							EnumPartIDKey.SheetName, sheetName);
					JDFRunList rlF = (JDFRunList) rlp.addPartition(
							EnumPartIDKey.Side, EnumSide.Front);
					integerRangeList.clear();
					integerRangeList.append(i / 2);
					rlF.setPages(integerRangeList);
					JDFRunList rlB = (JDFRunList) rlp.addPartition(
							EnumPartIDKey.Side, EnumSide.Back);
					integerRangeList.clear();
					integerRangeList.append(1 + i / 2);
					rlB.setPages(integerRangeList);
				}
			} else
			// instantiate by template
			{
				JDFFileSpec fs = rlSheet.appendLayoutElement().appendFileSpec();
				fs.setMimeType("application/pdf");
				fs.setFileFormat(format + "%s_%s.pdf");
				fs.setFileTemplate("SheetNum,Surface,Separation");
			}

			doc.write2File(sm_dirTestDataTemp + "AutomatedLayout_Plateset"
					+ loop + ".jdf", 2, false);
			n.getResourceLinkPool().deleteNode();
			n.getResourcePool().deleteNode();
		}
	}

	// ///////////////////////////////////////////////////

	public void testAutomateLayout1() throws Exception
	{
		n
				.setXMLComment("This is the simplest example of an automated layout\n"
						+ "The structure is aligned as closely as possible with a static Layout\n"
						+ "note that the actual processes and outputs have been omitted for brevity");

		setUpAutomatedInputRunList();
		rl
				.setDescriptiveName("This is a RunList specifiying 100 instance documents of 14 pages each in a ppml file");

		lo = (JDFLayout) n.appendMatchingResource(ElementName.LAYOUT,
				EnumProcessUsage.AnyInput, null);
		lo.setResStatus(EnumResStatus.Available, true);

		lo.setMaxOrd(14);
		lo.setMaxDocOrd(1);
		lo.setAutomated(true);
		lo
				.appendXMLComment(
						"Layout for 2 Cover pages and 12 2 up two sided body pages\n The number of pages per instance document is fixed\n"
								+ "This Layout is an example of an 'almost conventional' automated layout\n"
								+ "MaxDocOrd is set to 1. This is redundant since 1 is the default.\n"
								+ "A value of 1 explicitly resets all counters at a Document break.",
						null);
		JDFLayout cover = (JDFLayout) lo.addPartition(EnumPartIDKey.SheetName,
				"Cover");
		cover
				.setDescriptiveName("one sided cover - the inner = back side is empty");
		JDFLayout coverFront = (JDFLayout) cover.addPartition(
				EnumPartIDKey.Side, EnumSide.Front);
		JDFContentObject co = coverFront.appendContentObject();
		co.setCTM(new JDFMatrix(1, 0, 0, 1, 0, 0));
		co.setOrd(13);
		co.setDescriptiveName("Front Cover Page");
		co = coverFront.appendContentObject();
		co.setCTM(new JDFMatrix(1, 0, 0, 1, 8.5 * 72, 0));
		co.setOrd(0);
		co
				.setDescriptiveName("Back Cover Page - (back of brochure but front of sheet)");

		for (int i = 0; i < 3; i++)
		{
			JDFLayout body = (JDFLayout) lo.addPartition(
					EnumPartIDKey.SheetName, "Body" + (i + 1));
			body.setDescriptiveName("sheet " + (i + 1) + " of 3 of the insert");
			JDFLayout bodySide = (JDFLayout) body.addPartition(
					EnumPartIDKey.Side, EnumSide.Front);

			co = bodySide.appendContentObject();
			co.setCTM(new JDFMatrix(1, 0, 0, 1, 0, 0));
			co.setOrd(8 + 2 * (2 - i));
			co.setDescriptiveName("Left Front Sheet Body Page");
			co = bodySide.appendContentObject();
			co.setCTM(new JDFMatrix(1, 0, 0, 1, 8.5 * 72, 0));
			co.setOrd(1 + (2 * i));
			co.setDescriptiveName("Right Front Sheet Body Page");

			bodySide = (JDFLayout) body.addPartition(EnumPartIDKey.Side,
					EnumSide.Back);

			co = bodySide.appendContentObject();
			co.setCTM(new JDFMatrix(1, 0, 0, 1, 0, 0));
			co.setOrd(2 + (2 * i));
			co.setDescriptiveName("Left Back Sheet Body Page");
			co = bodySide.appendContentObject();
			co.setCTM(new JDFMatrix(1, 0, 0, 1, 8.5 * 72, 0));
			co.setOrd(7 + 2 * (2 - i));
			co.setDescriptiveName("Right Back Sheet Body Page");
		}
		doc.write2File(sm_dirTestDataTemp + "AutomatedLayout1.jdf", 2, false);
	}

	// ///////////////////////////////////////////////////

	public void testAutomateLayout2() throws Exception
	{
		n
				.setXMLComment("This another example of an automated layout\n"
						+ "The structure is aligned close to a static Layout but additionally uses OrdExpression and allows for varying numbers of pages in the runlist\n"
						+ "note that the actual processes and outputs have been omitted for brevity");

		setUpAutomatedInputRunList();
		rl
				.setDescriptiveName("This is a RunList specifiying 100 instance documents of varying pages each in a ppml file");

		lo = (JDFLayout) n.appendMatchingResource(ElementName.LAYOUT,
				EnumProcessUsage.AnyInput, null);
		lo.setResStatus(EnumResStatus.Available, true);

		lo.setMaxDocOrd(1);
		lo.setAutomated(true);
		lo
				.appendXMLComment(
						"Layout for 2 Cover pages and varying numbers of 2 up two sided body pages\n"
								+ "The number of pages per instance document varies\n"
								+ "MaxDocOrd is set to 1. This is redundant since 1 is the default.\n"
								+ "A value of 1 explicitly resets all counters at a Document break.",
						null);
		JDFLayout cover = (JDFLayout) lo.addPartition(EnumPartIDKey.SheetName,
				"Cover");
		cover
				.appendXMLComment(
						"In this example, the cover is assumed to be the first two pages of each runlist\n"
								+ "\n!!! We unfortunately have an issue here:\n"
								+ "we cannot differentiate whether the cover should be repeated of not, i.e. whether the cover is executed once (the correct choice) or repeated between each body sheet.\n"
								+ "Note that no MaxOrd is not set, as it varies between documents",
						null);
		cover
				.setDescriptiveName("one sided cover - the inner = back side is empty");
		JDFLayout coverFront = (JDFLayout) cover.addPartition(
				EnumPartIDKey.Side, EnumSide.Front);
		JDFContentObject co = coverFront.appendContentObject();
		co.setCTM(new JDFMatrix(1, 0, 0, 1, 0, 0));
		co.setOrdExpression("1");
		co.setDescriptiveName("Front Cover Page");
		co = coverFront.appendContentObject();
		co.setCTM(new JDFMatrix(1, 0, 0, 1, 8.5 * 72, 0));
		co.setOrdExpression("0");
		co
				.setDescriptiveName("Back Cover Page - (back of brochure but front of sheet)");

		JDFLayout body = (JDFLayout) lo.addPartition(EnumPartIDKey.SheetName,
				"Body");
		body.setDescriptiveName("abstract description of multiple body sheets");
		JDFLayout bodySide = (JDFLayout) body.addPartition(EnumPartIDKey.Side,
				EnumSide.Front);

		co = bodySide.appendContentObject();
		co.setCTM(new JDFMatrix(1, 0, 0, 1, 0, 0));
		co.setOrdExpression("4 * (n+3)/4 - s*2 +1");
		co.setDescriptiveName("Left Front Sheet Body Page");
		co = bodySide.appendContentObject();
		co.setCTM(new JDFMatrix(1, 0, 0, 1, 8.5 * 72, 0));
		co.setOrdExpression("2*s +2");
		co.setDescriptiveName("Right Front Sheet Body Page");

		bodySide = (JDFLayout) body.addPartition(EnumPartIDKey.Side,
				EnumSide.Back);

		co = bodySide.appendContentObject();
		co.setCTM(new JDFMatrix(1, 0, 0, 1, 0, 0));
		co.setOrdExpression("2*s +3");
		co.setDescriptiveName("Left Back Sheet Body Page");
		co = bodySide.appendContentObject();
		co.setCTM(new JDFMatrix(1, 0, 0, 1, 8.5 * 72, 0));
		co.setOrdExpression("4 * (n+3)/4 - s*2 +0");
		co.setDescriptiveName("Right Back Sheet Body Page");

		doc.write2File(sm_dirTestDataTemp + "AutomatedLayout2.jdf", 2, false);
	}

	// ///////////////////////////////////////////////////
	// ///////////////////////////////////////////////////

	public void testAutomateLayout3() throws Exception
	{
		n
				.setXMLComment("This is a simple example of an automated layout that positions multiple instance documents onto one sheet\n"
						+ "The structure is aligned as closely as possible with a static Layout\n"
						+ "note that the actual processes and outputs have been omitted for brevity");

		setUpAutomatedInputRunList();
		rl
				.setDescriptiveName("This is a RunList specifiying 100 instance documents of 14 pages each in a ppml file");

		lo = (JDFLayout) n.appendMatchingResource(ElementName.LAYOUT,
				EnumProcessUsage.AnyInput, null);
		lo.setResStatus(EnumResStatus.Available, true);

		lo.setMaxOrd(7);
		lo.setMaxDocOrd(2);
		lo.setAutomated(true);
		lo
				.appendXMLComment(
						"Layout for 2*1 Cover page and 2*6 2 up two sided body pages\n The number of pages per instance document is fixed\n"
								+ "This Layout is an example of an 'almost conventional' automated layout\n"
								+ "MaxDocOrd is set to 2. Thus 2 documents are positioned on each sheet.\n",
						null);
		JDFLayout cover = (JDFLayout) lo.addPartition(EnumPartIDKey.SheetName,
				"Cover");
		cover
				.setDescriptiveName("one sided cover - the inner = back side is empty");
		JDFLayout coverFront = (JDFLayout) cover.addPartition(
				EnumPartIDKey.Side, EnumSide.Front);
		JDFContentObject co = coverFront.appendContentObject();
		co.setCTM(new JDFMatrix(1, 0, 0, 1, 0, 0));
		co.setOrd(0);
		co.setDocOrd(0);
		co.setDescriptiveName("Front Cover Page, document 0,2,4,...");
		co = coverFront.appendContentObject();
		co.setCTM(new JDFMatrix(1, 0, 0, 1, 8.5 * 72, 0));
		co.setOrd(0);
		co.setDocOrd(1);
		co.setDescriptiveName("Front Cover Page, document 1,3,5,...");

		for (int i = 0; i < 3; i++)
		{
			JDFLayout body = (JDFLayout) lo.addPartition(
					EnumPartIDKey.SheetName, "Body" + (i + 1));
			body.setDescriptiveName("sheet " + (i + 1) + " of 3 of the insert");
			JDFLayout bodySide = (JDFLayout) body.addPartition(
					EnumPartIDKey.Side, EnumSide.Front);

			co = bodySide.appendContentObject();
			co.setCTM(new JDFMatrix(1, 0, 0, 1, 0, 0));
			co.setOrd(1 + i);
			co.setDocOrd(0);
			co.setDescriptiveName("Front Sheet Body Page, document 0,2,4,...");
			co = bodySide.appendContentObject();
			co.setCTM(new JDFMatrix(1, 0, 0, 1, 8.5 * 72, 0));
			co.setOrd(1 + (2 * i));
			co.setDocOrd(1);
			co.setDescriptiveName("Front Sheet Body Page, document 1,3,5,...");

			bodySide = (JDFLayout) body.addPartition(EnumPartIDKey.Side,
					EnumSide.Back);

			co = bodySide.appendContentObject();
			co.setCTM(new JDFMatrix(1, 0, 0, 1, 0, 0));
			co.setOrd(2 + (2 * i));
			co.setDocOrd(0);
			co.setDescriptiveName("Back Sheet Body Page, document 0,2,4,...");
			co = bodySide.appendContentObject();
			co.setCTM(new JDFMatrix(1, 0, 0, 1, 8.5 * 72, 0));
			co.setOrd(2 + (2 * i));
			co.setDocOrd(1);

			co.setDescriptiveName("Back Sheet Body Page, document 1,3,5,...");
		}
		doc.write2File(sm_dirTestDataTemp + "AutomatedLayout3.jdf", 2, false);
	}

	// ///////////////////////////////////////////////////
	// ///////////////////////////////////////////////////

	public void testAutomateLayout4() throws Exception
	{
		n
				.setXMLComment("This is a simple example of an automated layout that positions multiple instance documents onto one sheet\n"
						+ "The structure is aligned as closely as possible with a static Layout\n"
						+ "note that the actual processes and outputs have been omitted for brevity");

		setUpAutomatedInputRunList();
		rl
				.setDescriptiveName("This is a RunList specifiying 100 instance documents of 14 pages each in a ppml file.\n"
						+ "DocCopies requests a repeat of 50 copies per document");
		rl.setAttribute("DocCopies", 50, null);
		lo = (JDFLayout) n.appendMatchingResource(ElementName.LAYOUT,
				EnumProcessUsage.AnyInput, null);
		lo.setResStatus(EnumResStatus.Available, true);

		lo.setMaxOrd(1);
		lo.setMaxDocOrd(4);
		lo.setAutomated(true);
		lo
				.appendXMLComment(
						"Layout for 4stacks on a sheet\n The number of pages per instance document is fixed\n"
								+ "\n", null);
		JDFLayout cover = (JDFLayout) lo.addPartition(EnumPartIDKey.SheetName,
				"Stack");
		cover.setDescriptiveName("one sided 4 up stack back side is empty");
		JDFLayout coverFront = (JDFLayout) cover.addPartition(
				EnumPartIDKey.Side, EnumSide.Front);
		JDFContentObject co = coverFront.appendContentObject();
		co.setCTM(new JDFMatrix(1, 0, 0, 1, 0, 0));
		co.setOrd(0);
		co.setDocOrd(0);
		co.setDescriptiveName("Front Cover Page, document 0,4,...");

		co = coverFront.appendContentObject();
		co.setCTM(new JDFMatrix(1, 0, 0, 1, 8.5 * 72, 0));
		co.setOrd(0);
		co.setDocOrd(1);
		co.setDescriptiveName("Front Cover Page, document 1,5,...");

		co = coverFront.appendContentObject();
		co.setCTM(new JDFMatrix(1, 0, 0, 1, 0, 11 * 72));
		co.setOrd(0);
		co.setDocOrd(2);
		co.setDescriptiveName("Front Cover Page, document 2,6,...");

		co = coverFront.appendContentObject();
		co.setCTM(new JDFMatrix(1, 0, 0, 1, 8.5 * 72, 11 * 72));
		co.setOrd(0);
		co.setDocOrd(3);
		co.setDescriptiveName("Front Cover Page, document 3,7,...");

		doc.write2File(sm_dirTestDataTemp + "AutomatedLayout4.jdf", 2, false);
	}

	public void testAutomatedStrippingCutAndStack() throws Exception
	{
		n.setType(EnumType.Stripping);
		stripParams = (JDFStrippingParams) n.addResource(
				ElementName.STRIPPINGPARAMS, null, EnumUsage.Input, null, null,
				null, null);
		stripParams
				.getParentNode_KElement()
				.appendXMLComment(
						"Simple automated StrippingParams for the cut&stack example layout\n",
						stripParams);
		JDFStrippingParams stripSheet = (JDFStrippingParams) stripParams
				.addPartition(EnumPartIDKey.SheetName, "Sheets");
		stripSheet.setAttribute(AttributeName.AUTOMATED, true, null);
		final String format = "Sheet%02i";
		stripSheet.setAttribute("NameFormat", format);
		stripSheet.setAttribute("NameTemplate", "SheetNum");
		JDFStrippingParams stripStack0 = (JDFStrippingParams) stripSheet
				.addPartition(EnumPartIDKey.BinderySignatureName, "Stack0");
		stripParams.setAttribute("StackDepth", "100");

		JDFBinderySignature binderySignature = stripStack0
				.appendBinderySignature();
		stripStack0.appendPosition().setRelativeBox(
				new JDFRectangle(0, 0, 0.5, 1));
		binderySignature = (JDFBinderySignature) binderySignature
				.makeRootResource(null, null, true);
		binderySignature.setNumberUp(new JDFXYPair(1, 1));
		JDFStrippingParams stripStack1 = (JDFStrippingParams) stripSheet
				.addPartition(EnumPartIDKey.BinderySignatureName, "Stack1");
		stripStack1.refElement(binderySignature);
		stripStack1.appendPosition().setRelativeBox(
				new JDFRectangle(0.5, 0, 1, 1));
		doc.write2File(sm_dirTestDataTemp + "AutomatedStrippingCutStack.jdf",
				2, false);
	}

	/**
	 * tests jdf 1.4 negative ords
	 * 
	 * @throws Exception
	 */
	public void testAutomatedBooklet1() throws Exception
	{
		n
				.setXMLComment("This is a simple Automated Booklet using negative ords\n"
						+ "New Attribute @OrdsConsumed limits the number of ords consumed by an automated Layout\n"
						+ "Negative Ord values are assumed to flow backwards\n"
						+ "MaxOrd is not specified and must be calculated by counting the number of different ord values\n"
						+ "If we want to keep maxord, it would have to be replaced by an xypair that specifies hom many are consumed from back and from front\n"
						+ "If the number of pages is not mod 4, blank pages are retained at the back of the layout");

		setUpAutomatedInputRunList();
		rl.setDescriptiveName("This is any RunList...");
		lo = (JDFLayout) n.appendMatchingResource(ElementName.LAYOUT,
				EnumProcessUsage.AnyInput, null);
		lo.setResStatus(EnumResStatus.Available, true);

		JDFLayout sheet = (JDFLayout) lo.addPartition(EnumPartIDKey.SheetName,
				"TheSheet");
		sheet.setAutomated(true);
		sheet.setAttribute("OrdsConsumed", "0 -1");
		JDFLayout sheetFront = (JDFLayout) sheet.addPartition(
				EnumPartIDKey.Side, EnumSide.Front);
		JDFContentObject co = sheetFront.appendContentObject();
		co.setCTM(new JDFMatrix(1, 0, 0, 1, 0, 0));
		co.setOrd(0);
		co.setDescriptiveName("Front left Page 0,2,4...");

		co = sheetFront.appendContentObject();
		co.setCTM(new JDFMatrix(1, 0, 0, 1, 8.5 * 72, 0));
		co.setOrd(-1);
		co
				.setDescriptiveName("Back right page after folding -1 -3 -5 ... (Front sheet)");

		JDFLayout sheetBack = (JDFLayout) sheet.addPartition(
				EnumPartIDKey.Side, EnumSide.Back);
		co = sheetBack.appendContentObject();
		co.setCTM(new JDFMatrix(1, 0, 0, 1, 8.5 * 72, 0));
		co.setOrd(1);
		co.setDescriptiveName("Back left Page 1,3,5");

		co = sheetBack.appendContentObject();
		co.setCTM(new JDFMatrix(1, 0, 0, 1, 0, 0));
		co.setOrd(-2);
		co.setDescriptiveName("Front Right Page Page -2 -4 -6");

		doc.write2File(sm_dirTestDataTemp + "SimpleAutomatedBooklet.jdf", 2,
				false);

	}

	/**
	 * tests jdf 1.4 negative ords
	 * 
	 * @throws Exception
	 */
	public void testTaggedAutomatedBooklet() throws Exception
	{
		n
				.setXMLComment("This is a simple Automated Booklet using negative ords and meta data tags\n"
						+ "New Attribute @OrdsConsumed limits the number of ords consumed by an automated Layout\n"
						+ "Negative Ord values are assumed to flow backwards\n"
						+ "New Attribute @OrdReset defines the scope of ords\n"
						+ "MaxOrd is not specified and must be calculated by counting the number of different ord values\n"
						+ "If we want to keep maxord, it would have to be replaced by an xypair that specifies hom many are consumed from back and from front\n"
						+ "If the number of pages is not mod 4, blank pages are retained at the back of the layout");

		n.setType(EnumType.Combined);
		n.setTypes(new VString(
				"Interpreting Rendering DigitalPrinting Inserting Stitching",
				null));
		setUpAutomatedInputRunList();
		rl.setDescriptiveName("This is any RunList...");
		lo = (JDFLayout) n.appendMatchingResource(ElementName.LAYOUT,
				EnumProcessUsage.AnyInput, null);
		lo.setResStatus(EnumResStatus.Available, true);

		JDFMedia media = (JDFMedia) n.addResource("Media", EnumUsage.Input);
		JDFMedia mediaC = (JDFMedia) media.addPartition(
				EnumPartIDKey.SheetName, "TheCover");
		JDFMedia mediaCMale = (JDFMedia) mediaC.addPartition(
				EnumPartIDKey.SetTags, "Male");
		JDFMedia mediaCFemale = (JDFMedia) mediaC.addPartition(
				EnumPartIDKey.SetTags, "Female");

		JDFComponent insert = (JDFComponent) n.addResource("Component", null,
				EnumUsage.Input, EnumProcessUsage.Child, null, null, null);
		// JDFComponent insertExist=(JDFComponent)
		insert.addPartition(EnumPartIDKey.DocTags, "Exist");
		// JDFComponent insertProspect=(JDFComponent)
		insert.addPartition(EnumPartIDKey.DocTags, "Prospect");

		JDFLayout cover = (JDFLayout) lo.addPartition(EnumPartIDKey.SheetName,
				"TheCover");
		cover.setAutomated(false);
		JDFLayout coverMale = (JDFLayout) cover.addPartition(
				EnumPartIDKey.SetTags, "Male");
		coverMale.refMedia(mediaCMale);
		JDFLayout coverMaleHi = (JDFLayout) coverMale.addPartition(
				EnumPartIDKey.PageTags, "Hi");
		JDFLayout coverMaleLo = (JDFLayout) coverMale.addPartition(
				EnumPartIDKey.PageTags, "Low");

		JDFLayout coverFemale = (JDFLayout) cover.addPartition(
				EnumPartIDKey.SetTags, "Female");
		coverFemale.refMedia(mediaCFemale);
		JDFLayout coverFemaleHi = (JDFLayout) coverFemale.addPartition(
				EnumPartIDKey.PageTags, "Hi");
		JDFLayout coverFemaleLo = (JDFLayout) coverFemale.addPartition(
				EnumPartIDKey.PageTags, "Low");

		JDFLayout[] lo4 = new JDFLayout[4];
		lo4[0] = coverMaleHi;
		lo4[1] = coverFemaleHi;
		lo4[2] = coverMaleLo;
		lo4[3] = coverFemaleLo;
		JDFContentObject co;
		for (int i = 0; i < 4; i++)
		{
			JDFLayout lolo = lo4[i];

			JDFLayout coverFront = (JDFLayout) lolo.addPartition(
					EnumPartIDKey.Side, "Front");

			co = coverFront.appendContentObject();
			co.setCTM(new JDFMatrix(1, 0, 0, 1, 0, 0));
			co.setOrd(0);
			co.setDescriptiveName("Front Cover Page Outside");
			co = coverFront.appendContentObject();
			co.setCTM(new JDFMatrix(1, 0, 0, 8.5 * 72, 0, 0));
			co.setOrd(-1);
			co.setDescriptiveName("Back Cover Page Outside");
			if (i < 2)
			{
				JDFLayout coverBack = (JDFLayout) lolo.addPartition(
						EnumPartIDKey.Side, "Back");
				co = coverBack.appendContentObject();
				co.setCTM(new JDFMatrix(1, 0, 0, 1, 0, 0));
				co.setOrd(0);
				co.setDescriptiveName("Front Cover Page Inside");
				co = coverBack.appendContentObject();
				co.setCTM(new JDFMatrix(1, 0, 0, 8.5 * 72, 0, 0));
				co.setOrd(-1);
				co.setDescriptiveName("Back Cover Page Inside");
			}
		}
		JDFLayout sheet = (JDFLayout) lo.addPartition(EnumPartIDKey.SheetName,
				"TheSheet");
		sheet.setAutomated(true);
		sheet.setAttribute("OrdReset", "Set Doc");
		sheet.setAttribute("OrdsConsumed", "0 -1");
		sheet = (JDFLayout) sheet.addPartition(EnumPartIDKey.SetTags,
				"Male Female").addPartition(EnumPartIDKey.PageTags, "Hi Lo");
		JDFLayout sheetFront = (JDFLayout) sheet.addPartition(
				EnumPartIDKey.Side, EnumSide.Front);
		co = sheetFront.appendContentObject();
		co.setCTM(new JDFMatrix(1, 0, 0, 1, 0, 0));
		co.setOrd(0);
		co.setDescriptiveName("Front left Page 0,2,4...");

		co = sheetFront.appendContentObject();
		co.setCTM(new JDFMatrix(1, 0, 0, 1, 8.5 * 72, 0));
		co.setOrd(-1);
		co
				.setDescriptiveName("Back right page after folding -1 -3 -5 ... (Front sheet)");

		JDFLayout sheetBack = (JDFLayout) sheet.addPartition(
				EnumPartIDKey.Side, EnumSide.Back);
		co = sheetBack.appendContentObject();
		co.setCTM(new JDFMatrix(1, 0, 0, 1, 8.5 * 72, 0));
		co.setOrd(1);
		co.setDescriptiveName("Back left Page 1,3,5");

		co = sheetBack.appendContentObject();
		co.setCTM(new JDFMatrix(1, 0, 0, 1, 0, 0));
		co.setOrd(-2);
		co.setDescriptiveName("Front Right Page Page -2 -4 -6");
		String s = doc.write2String(2);
		s = StringUtil.replaceString(s, "SetTags", "Meta0");
		s = StringUtil.replaceString(s, "DocTags", "Meta1");
		s = StringUtil.replaceString(s, "PageTags", "Meta2");

		File f = new File(sm_dirTestDataTemp + "TaggedAutomatedBooklet.jdf");
		OutputStream os = new FileOutputStream(f);
		os.write(s.getBytes());

	}

	/**
	 * tests jdf 1.4 negative ords
	 * 
	 * @throws Exception
	 */
	public void testAutomatedBookletWithCover() throws Exception
	{
		for (int j = 0; j < 2; j++)
		{

			for (int i = 0; i < 3; i++)
			{
				if (i == 2 && j == 1)
					continue; // no cut&stack centerfold
				setUp();
				n
						.setXMLComment("This is a simple Automated Booklet using negative ords and special handling oft the cover\n"
								+ "New Attribute @OrdsConsumed limits the number of ords consumed by an automated Layout\n"
								+ "Negative Ord values are assumed to flow backwards\n"
								+ "MaxOrd is not specified and must be calculated by counting the number of different ord values\n"
								+ "If we want to keep maxord, it would have to be replaced by an xypair that specifies hom many are consumed from back and from front\n"
								+ "If the number of pages is not mod 4, blank pages are retained at the back of the layout");

				setUpAutomatedInputRunList();
				rl.setDescriptiveName("This is any RunList...");
				lo = (JDFLayout) n.appendMatchingResource(ElementName.LAYOUT,
						EnumProcessUsage.AnyInput, null);
				lo.setResStatus(EnumResStatus.Available, true);
				if (j > 0)
					lo.setAttribute("StackDepth", 100, null);

				JDFLayout cover = (JDFLayout) lo.addPartition(
						EnumPartIDKey.SheetName, "TheCover");
				cover.setAutomated(false);
				JDFLayout sheet = (JDFLayout) lo.addPartition(
						EnumPartIDKey.SheetName, "TheSheet");
				sheet.setAutomated(true);
				JDFLayout coverFront = (JDFLayout) cover.addPartition(
						EnumPartIDKey.Side, EnumSide.Front);
				JDFContentObject co;
				if (i == 0)
				{
					cover
							.setXMLComment("the cover consumes pages 0 and -1 and is not printed on the inside=back");
					for (int k = 0; k < 1 + j; k++)
					{

						co = coverFront.appendContentObject();
						co.setCTM(new JDFMatrix(1, 0, 0, 1, 0, k * 1000));
						co.setOrd(0);
						co.setDescriptiveName("Front left cover Page 0...");
						if (j > 0)
							co.setAttribute("StackOrd", k, null);
						co = coverFront.appendContentObject();
						co
								.setCTM(new JDFMatrix(1, 0, 0, 1, 8.5 * 72,
										k * 1000));
						co.setOrd(-1);
						co
								.setDescriptiveName("Back right cover page after folding -1 ");
						if (j > 0)
							co.setAttribute("StackOrd", k, null);
					}
					sheet.setAttribute("OrdsConsumed", "1 -2");
				} else if (i == 1)
				{
					cover
							.setXMLComment("the cover consumes page 0 as a wraparound and is not printed on the inside=back");
					for (int k = 0; k < j; k++)
					{
						co = coverFront.appendContentObject();
						co.setDescriptiveName("wraparound cover Page 0");
						co.setCTM(new JDFMatrix(1, 0, 0, 1, 0, k * 1000));
						co.setOrd(0);
						if (j > 0)
							co.setAttribute("StackOrd", k, null);
					}
					sheet.setAttribute("OrdsConsumed", "1 -1");
				} else if (i == 2)
				{
					cover
							.setXMLComment("the cover consumes page 0 as a wraparound and is not printed on the inside=back");

					co = coverFront.appendContentObject();
					co.setDescriptiveName("wraparound cover Page 0");
					co.setCTM(new JDFMatrix(1, 0, 0, 1, 0, 0));
					co.setOrd(0);
					sheet.setAttribute("OrdsConsumed", "1 -5");
					JDFLayout centerfold = (JDFLayout) lo.addPartition(
							EnumPartIDKey.SheetName, "Centerfold");
					centerfold.setAutomated(true);
					centerfold
							.setXMLComment("the cetertfold is an asymmetric z-fold that consumes 1 large page on the front and 3 pages on the back");
					centerfold.setAttribute("OrdsConsumed", "-4 -1");
					co = ((JDFLayout) centerfold.addPartition(
							EnumPartIDKey.Side, "Front")).appendContentObject();
					co.setDescriptiveName("centerfold front spread");
					co.setCTM(new JDFMatrix(1, 0, 0, 1, 0, 0));
					co.setOrd(0);
					centerfold = (JDFLayout) centerfold.addPartition(
							EnumPartIDKey.Side, "Back");
					for (int k = 0; k < 3; k++)
					{
						co = centerfold.appendContentObject();
						co
								.setDescriptiveName("centerfold back page "
										+ (k + 1));
						co.setCTM(new JDFMatrix(1, 0, 0, 1, 0, k * 1000));
						co.setOrd(k + 1);

					}

				}

				sheet
						.setXMLComment("this sheet consumes the second through "
								+ (-2 + i)
								+ " pages\nAutomated ords are based on the REDUCED list defined by OrdsConsumed\n"
								+ "In this case ord=0 actually starts at ord(RunList)=1 and ord -1 actually starts at ord(RunList -2)\n"
								+ "The advantage is that the offsets for the loop are specified in OrdsConsumed while the loop increments are specified in the ords:\n"
								+ "Ord RL (++) = OrdsConsumed_x + Ord + n*maxOrd_x\n"
								+ "Ord RL (--) = 1+ OrdsConsumed_y + Ord - n*maxOrd_y (the first 1 in the equation is from the fact that -1+-1 is actually still -1)\n");
				JDFLayout sheetFront = (JDFLayout) sheet.addPartition(
						EnumPartIDKey.Side, EnumSide.Front);
				JDFLayout sheetBack = (JDFLayout) sheet.addPartition(
						EnumPartIDKey.Side, EnumSide.Back);
				for (int k = 0; k <= j; k++)
				{
					co = sheetFront.appendContentObject();
					co.setCTM(new JDFMatrix(1, 0, 0, 1, 0, k * 1000));
					co.setOrd(0);
					if (j > 0)
						co.setAttribute("StackOrd", k, null);
					co.setDescriptiveName("Front left Pages ");

					co = sheetFront.appendContentObject();
					co.setCTM(new JDFMatrix(1, 0, 0, 1, 8.5 * 72, k * 1000));
					co.setOrd(-1);
					co
							.setDescriptiveName("Back right pages after folding ... (Front sheet)");
					if (j > 0)
						co.setAttribute("StackOrd", k, null);

					co = sheetBack.appendContentObject();
					co.setCTM(new JDFMatrix(1, 0, 0, 1, 8.5 * 72, k * 1000));
					co.setOrd(1);
					co.setDescriptiveName("Back left Pages");
					if (j > 0)
						co.setAttribute("StackOrd", k, null);

					co = sheetBack.appendContentObject();
					co.setCTM(new JDFMatrix(1, 0, 0, 1, 0, k * 1000));
					co.setOrd(-2);
					co.setDescriptiveName("Front Right Page Pages");
					if (j > 0)
						co.setAttribute("StackOrd", k, null);
				}
				doc.write2File(sm_dirTestDataTemp
						+ "SimpleAutomatedBookletWithCover" + i + "_" + j
						+ ".jdf", 2, false);
			}
		}
	}

	/**
	 * tests jdf 1.4 c&s layout
	 * 
	 * @throws Exception
	 */
	public void testCutAndStack() throws Exception
	{
		n
				.setXMLComment("This is a simple cut and stack layout witrh 2 stacks of one page each (two sided)\n");

		setUpAutomatedInputRunList();
		rl.setDescriptiveName("This is any RunList...");
		lo = (JDFLayout) n.appendMatchingResource(ElementName.LAYOUT,
				EnumProcessUsage.AnyInput, null);
		lo.setResStatus(EnumResStatus.Available, true);
		lo.setMaxOrd(2);
		lo.setAutomated(true);
		lo
				.setXMLComment("2 stacks with 2 pages\n"
						+ "The algorithm for calculating which pages go where is:\n"
						+ "Ord + MaxOrd*SheetLoop%(MaxOrd*MaxStack*StackDepth) + StackOrd*StackDepth\n"
						+ "Each set of stacks consumes 2 * 2 * 100 = 400 Pages (4 ContentObjects = 2 front, 2 Back / Sheet * 100 StackDepth");
		lo.setAttribute("StackDepth", "100");
		lo.setAttribute("MaxStack", "2");
		JDFLayout cover = (JDFLayout) lo.addPartition(EnumPartIDKey.SheetName,
				"TheSheet");
		JDFLayout coverFront = (JDFLayout) cover.addPartition(
				EnumPartIDKey.Side, EnumSide.Front);
		JDFContentObject co = coverFront.appendContentObject();
		co.setCTM(new JDFMatrix(1, 0, 0, 1, 0, 0));
		co.setOrd(0);
		co.setAttribute("StackOrd", "0");
		co.setDescriptiveName("Front Page 0,2,4..., Stack 0");
		co
				.setXMLComment("this co consumes all pages 0,2,4...198, 400,402,404...598, 800....");

		co = coverFront.appendContentObject();
		co.setCTM(new JDFMatrix(1, 0, 0, 1, 8.5 * 72, 0));
		co.setOrd(0);
		co.setAttribute("StackOrd", "1");
		co.setDescriptiveName("Front Page 0,2,4,..., Stack 1");
		co
				.setXMLComment("this co consumes all pages 200,202,204...398, 600,602,604...798, 1000....");

		JDFLayout coverBack = (JDFLayout) cover.addPartition(
				EnumPartIDKey.Side, EnumSide.Back);
		co = coverBack.appendContentObject();
		co.setCTM(new JDFMatrix(1, 0, 0, 1, 8.5 * 72, 0));
		co.setOrd(1);
		co.setAttribute("StackOrd", "0");
		co.setDescriptiveName("Back Page 1,3,5, Stack 0");
		co
				.setXMLComment("this co consumes all pages 1,3,5...199, 401,403,405...499, 801....");

		co = coverBack.appendContentObject();
		co.setCTM(new JDFMatrix(1, 0, 0, 1, 0, 0));
		co.setOrd(1);
		co.setAttribute("StackOrd", "1");
		co.setDescriptiveName("Back Page 1,3,5, Stack 1");
		co
				.setXMLComment("this co consumes all pages 201,203,205...299, 601,603,605...799, 1001....");

		doc.write2File(sm_dirTestDataTemp + "CutStack.jdf", 2, false);
	}

	// ///////////////////////////////////////////////////

	// ///////////////////////////////////////////////////
	// ///////////////////////////////////////////////////
	/**
	 * @throws DataFormatException
	 */
	private void setUpAutomatedInputRunList() throws DataFormatException
	{
		JDFRunList run = rl.addRun("file://host/data/test.ppml", 0, -1);
		assertEquals(run.getLayoutElement().getFileSpec().getMimeType(),
				JDFFileSpec.getMimeTypeFromURL(".ppml"));
		run.setDocs(new JDFIntegerRangeList("0~99"));
		rl.setResStatus(EnumResStatus.Available, true);
	}

	// ///////////////////////////////////////////////////////////////////////
}
