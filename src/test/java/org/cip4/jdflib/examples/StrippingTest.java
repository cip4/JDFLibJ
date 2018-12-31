/*
 * The CIP4 Software License, Version 1.0
 *
 *
 * Copyright (c) 2001-2018 The International Cooperation for the Integration of Processes in Prepress, Press and Postpress (CIP4). All rights reserved.
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
/*
 * JDFExampleDocTest.java
 *
 * @author muchadie
 */
package org.cip4.jdflib.examples;

import java.io.File;

import org.cip4.jdflib.JDFTestCaseBase;
import org.cip4.jdflib.auto.JDFAutoBinderySignature.EnumBinderySignatureType;
import org.cip4.jdflib.auto.JDFAutoPosition.EnumOrientation;
import org.cip4.jdflib.auto.JDFAutoStripMark.EnumMarkSide;
import org.cip4.jdflib.core.AttributeName;
import org.cip4.jdflib.core.ElementName;
import org.cip4.jdflib.core.JDFDoc;
import org.cip4.jdflib.core.JDFElement.EnumVersion;
import org.cip4.jdflib.core.JDFResourceLink.EnumUsage;
import org.cip4.jdflib.core.KElement;
import org.cip4.jdflib.core.VString;
import org.cip4.jdflib.datatypes.JDFIntegerList;
import org.cip4.jdflib.datatypes.JDFRectangle;
import org.cip4.jdflib.datatypes.JDFXYPair;
import org.cip4.jdflib.node.JDFNode;
import org.cip4.jdflib.node.JDFNode.EnumType;
import org.cip4.jdflib.resource.JDFResource.EnumPartIDKey;
import org.cip4.jdflib.resource.JDFStrippingParams;
import org.cip4.jdflib.resource.process.JDFAssembly;
import org.cip4.jdflib.resource.process.JDFAssemblySection;
import org.cip4.jdflib.resource.process.JDFBinderySignature;
import org.cip4.jdflib.resource.process.JDFDieLayout;
import org.cip4.jdflib.resource.process.JDFMedia;
import org.cip4.jdflib.resource.process.JDFPosition;
import org.cip4.jdflib.resource.process.JDFPreview;
import org.cip4.jdflib.resource.process.JDFSignatureCell;
import org.cip4.jdflib.resource.process.JDFStation;
import org.cip4.jdflib.resource.process.JDFStripMark;
import org.junit.Test;

public class StrippingTest extends JDFTestCaseBase
{
	private JDFNode n;
	private JDFDoc d;
	private JDFStrippingParams stripParams;

	/**
	 *
	 */
	@Test
	public void testCommonFolds()
	{
		final JDFBinderySignature bs = stripParams.appendBinderySignature();
		bs.setFoldCatalog("F4-1");
		stripParams.addPartition(EnumPartIDKey.BinderySignatureName, "bs1");
		stripParams.addPartition(EnumPartIDKey.BinderySignatureName, "bs2");
		final JDFAssembly a = (JDFAssembly) n.getCreateResource(ElementName.ASSEMBLY, EnumUsage.Input, 0);
		final JDFAssemblySection ass = a.appendAssemblySection();
		ass.setAssemblyIDs(new VString("bs1"));
		ass.setAttribute(AttributeName.COMMONFOLDS, "2");
		final JDFAssemblySection ass2 = a.appendAssemblySection();
		ass2.setAssemblyIDs(new VString("bs2"));
		writeRoundTrip(n, "commonfolds", EnumVersion.Version_2_1);
		writeTest(n, "resources/commonfolds.jdf", true, null);
	}

	/**
	 * test dielayout
	 *
	 * @throws Exception
	 */
	@Test
	public void testDieLayoutStrip() throws Exception
	{
		final JDFBinderySignature bs = stripParams.appendBinderySignature();
		bs.setBinderySignatureType(EnumBinderySignatureType.Die);
		final JDFDieLayout dl = bs.appendDieLayout();
		final JDFStation s = dl.appendStation();
		s.setStationAmount(12);
		s.setStationName("MyStation");
		final JDFSignatureCell scs[] = new JDFSignatureCell[3];
		final JDFPreview pv0 = (JDFPreview) n.addResource(ElementName.PREVIEW, null, null, null, null, null, null);
		final String index[] = { "0 ~ 2", "3 ~ 8", "9 ~ 11" };
		for (int i = 0; i < 3; i++)
		{
			final JDFSignatureCell sc = scs[i] = bs.appendSignatureCell();
			sc.setStationName("MyStation");
			final JDFPreview pv = (JDFPreview) pv0.addPartition(EnumPartIDKey.CellIndex, index[i]);
			pv.setURL("file://foo" + i + ".vrml");
			pv.setAttribute("PreviewFileType", "VRML");
			pv.setAttribute("PreviewUsage", "Animation");
			sc.refElement(pv);
		}

		scs[0].setXMLComment("This represents 3 times pageList 0");
		scs[0].setDescriptiveName("Strawberry lid");
		scs[0].setFrontPages(new JDFIntegerList("0 0 0"));

		scs[1].setFrontPages(new JDFIntegerList("1 1 1 1 1 1"));
		scs[1].setXMLComment("This represents 6 times pageList 1");
		scs[1].setDescriptiveName("Raspberry lid");

		scs[2].setFrontPages(new JDFIntegerList("2 2 2"));
		scs[2].setXMLComment("This represents 3 times pageList 2");
		scs[2].setDescriptiveName("Blueberry lid");

		writeTest(n, "../DieStationStrip.jdf", false, null);
	}

	/**
	 * test stripmarks
	 *
	 * @throws Exception
	 */
	@Test
	public void testStripMarks() throws Exception
	{
		// TODO relativeSize AbsoluteSize NextPosition
		KElement.setLongID(false);
		n.getCreateResourcePool().appendXMLComment("StrippingParams with one sheet for simplicity", null);

		final JDFMedia media = stripParams.appendMedia();
		media.setDimensionCM(new JDFXYPair(100, 70));

		final JDFStrippingParams spS1 = (JDFStrippingParams) stripParams.addPartition(EnumPartIDKey.SheetName, "Sheet_1");
		{
			final JDFStripMark sm1 = spS1.appendStripMark();
			sm1.setXMLComment(
					"The following mark is on the press sheet (see new Attribute: MarkContext)\n@Anchor defines the cs origin of the mark, @NextAnchor defines the cs origin of the container, in this case the sheet\nThus the top center of the mark is rotated by 90� and placed exactly on (Position=0 0) the center right of the sheet\nNote that position is defined in absolute coordinates so that it alligns with Margin\nI propose deprecating StripMark/Position because the box paradigm does not fit well with resizeable marks.");
			sm1.setAttribute("MarkContext", "Sheet");
			sm1.setMarkName("ColorControlStrip");
			sm1.setMarkSide(EnumMarkSide.TwoSidedIndependent);
			sm1.setAttribute("Orientation", "Rotate90");
			setNextAnchor(sm1, null, "CenterRight", "0 0", null, "Parent");
			sm1.setAttribute("Anchor", "TopCenter");
			sm1.appendElement(ElementName.COLORCONTROLSTRIP).setXMLComment("The various explicit mark elements should be allowed here for their associated metatdata");
		}

		final JDFStrippingParams spBS1 = (JDFStrippingParams) spS1.addPartition(EnumPartIDKey.BinderySignatureName, "BS_1");
		final JDFBinderySignature bs1 = spBS1.appendBinderySignature();
		bs1.setNumberUp(new JDFXYPair(2, 4));
		final JDFPosition posBS1_1 = spBS1.appendPosition();
		posBS1_1.setRelativeBox(new JDFRectangle(0, 0, 0.5, 1));
		{
			final JDFStripMark sm1_1 = spBS1.appendStripMark();
			sm1_1.setXMLComment(
					"The following describes a back to back mark on the folding signature (see new Attribute: MarkContext)\n@Anchor defines the cs origin of the mark, @NextAnchor defines the cs origin of the container, in this case the bindery signature.\nThus the center of the cutmark is positioned 5 pts left(-5) and 5 pts up(+5) from the bottom right of the bindery Signature");
			sm1_1.setAttribute("MarkContext", "BinderySignature");
			sm1_1.setMarkName("CutMark");
			sm1_1.setMarkSide(EnumMarkSide.TwoSidedBackToBack);
			sm1_1.setAttribute("Orientation", "Rotate0");
			sm1_1.setAttribute("Anchor", "Center");
			setNextAnchor(sm1_1, null, "BottomRight", "-5 5", null, "Parent");
			sm1_1.appendElement(ElementName.CUTMARK).setXMLComment("The various explicit mark elements should be allowed here for their associated metatdata");
		}
		// TODO page cs vs. cell cs
		{
			final JDFStripMark sm1_2 = spBS1.appendStripMark();
			sm1_2.setXMLComment(
					"The following describes a 4 back marks, one on each pair of Strip Cells (page) (see new Attribute: MarkContext)\n@Anchor defines the cs origin of the mark, @NextAnchor defines the cs origin of the container, in this case the spine of a pair of Page cells.\nThus the center of the bar code is positioned 0 pts right and 5 point up from the bottom spine of the cell page.\n Position is applied prior to rotating the mark.");
			sm1_2.setAttribute("MarkContext", "CellPair");
			sm1_2.setMarkName("IdentificationField");
			sm1_2.setMarkSide(EnumMarkSide.Back);
			sm1_2.setAttribute("Orientation", "Rotate90");
			sm1_2.setAttribute("Anchor", "CenterLeft");
			setNextAnchor(sm1_2, null, "BottomCenter", "5 0", null, "Parent");
			sm1_2.appendElement(ElementName.IDENTIFICATIONFIELD).setXMLComment("The various explicit mark elements should be allowed here for their associated metatdata");
		}

		{
			final JDFStripMark sm1_3 = spBS1.appendStripMark();
			sm1_3.setXMLComment(
					"The following describes a back mark on each of the 8 Strip Cells (page) (see new Attribute: MarkContext)\n@Anchor defines the cs origin of the mark, @NextAnchor defines the cs origin of the container, in this case the bottom center of a Page cell.\nThus the center of the bar code is positioned 0 pts right and 5 point up from the bottom cell page.");
			sm1_3.setAttribute("MarkContext", "Cell");
			sm1_3.setMarkName("IdentificationField");
			sm1_3.setMarkSide(EnumMarkSide.Back);
			sm1_3.setAttribute("Orientation", "Rotate0");
			sm1_3.setAttribute("Anchor", "BottomCenter");
			setNextAnchor(sm1_3, null, "BottomCenter", "0 5", null, "Parent");
			sm1_3.setAttribute("AbsoluteWidth", "20");
			sm1_3.setAttribute("AbsoluteHeight", "10");
			sm1_3.appendElement(ElementName.IDENTIFICATIONFIELD).setXMLComment("The various explicit mark elements should be allowed here for their associated metatdata");
		}

		final JDFStrippingParams spBS2 = (JDFStrippingParams) spS1.addPartition(EnumPartIDKey.BinderySignatureName, "BS_2");
		spBS2.appendBinderySignature();
		final JDFPosition posBS2_1 = spBS2.appendPosition();
		posBS2_1.setRelativeBox(new JDFRectangle(0.5, 0, 1, 0.5));
		posBS2_1.setOrientation(EnumOrientation.Rotate270);

		final JDFPosition posBS2_2 = spBS2.appendPosition();
		posBS2_2.setRelativeBox(new JDFRectangle(0.5, 0.5, 1, 1));
		posBS2_2.setOrientation(EnumOrientation.Flip90);

		{
			final JDFStripMark sm2_1 = spBS2.appendStripMark();
			sm2_1.setXMLComment(
					"The following describes single sided barcode on the two folding signatures (see new Attribute: MarkContext)\n@Anchor defines the cs origin of the mark, @NextAnchor defines the cs origin of the container, in this case the 2 bindery signatures.\nThus the top left of the barcode is positioned 3 pts right(+3) and 3 pts below(-3) from the top left of the front side of the bindery Signature.\nSince ther are two position elements, this results in two marks:\nPosition one is rotated by 90 degrees so that the barcode remains on the front of the sheet. Due to the Position/@Rotation, the mark is also rotated by 90� on the press sheet\n Position 2 is also flipped so that the barcode eventually ends up on the back of the press sheet.");
			sm2_1.setAttribute("MarkContext", "BinderySignature");
			sm2_1.setMarkName("IdentificationField");
			sm2_1.setMarkSide(EnumMarkSide.Front);
			sm2_1.setAttribute("Orientation", "Rotate0");
			sm2_1.setAttribute("Anchor", "TopLeft");
			setNextAnchor(sm2_1, null, "TopLeft", "2 -3", null, "Parent");
			final String idAnchor = sm2_1.appendAnchor(null);
			sm2_1.appendElement(ElementName.IDENTIFICATIONFIELD).setXMLComment("The various explicit mark elements should be allowed here for their associated metatdata");

			final JDFStripMark sm2_2 = spBS2.appendStripMark();
			sm2_2.setXMLComment("The following is a relatively positioned stripmark.");
			sm2_2.setAttribute("MarkContext", "BinderySignature");
			sm2_1.setAttribute("Anchor", "BottomLeft");
			sm2_2.setMarkName("RegisterMark");
			sm2_2.setMarkSide(EnumMarkSide.Front);
			sm2_2.setAttribute("Orientation", "Rotate0");
			sm2_2.appendElement(ElementName.REGISTERMARK).setXMLComment("The various explicit mark elements should be allowed here for their associated metatdata");
			setNextAnchor(sm2_2, idAnchor, "BottomRight", "3 0",
					"This NextAnchor element refers to the previous barcode. the lower left of this is 3 points tothe right of the lower right of the referenced barcode.", "Sibling");

		}

		d.write2File(sm_dirTestDataTemp + File.separator + "StripMarks.jdf", 2, false);

	}

	/*
	 * (non-Javadoc)
	 *
	 * @see org.cip4.jdflib.JDFTestCaseBase#setUp()
	 */
	@Override
	public void setUp() throws Exception
	{
		super.setUp();
		KElement.setLongID(false);
		d = new JDFDoc(ElementName.JDF);
		n = d.getJDFRoot();
		n.setVersion(EnumVersion.Version_1_7);
		n.setType(EnumType.Stripping);
		stripParams = (JDFStrippingParams) n.addResource(ElementName.STRIPPINGPARAMS, null, EnumUsage.Input, null, null, null, null);
		n.addResource(ElementName.LAYOUT, EnumUsage.Output).setDescriptiveName("dummy");
	}

	/**
	 * @param sm2_2
	 * @param idAnchor
	 */
	private static void setNextAnchor(final KElement sm2_2, final String idAnchor, final String anchor, final String absolutePosition, final String xmlComment, final String anchorType)
	{
		final KElement nextAnchor = sm2_2.appendElement("RefAnchor");
		nextAnchor.setAttribute("Anchor", anchor);
		sm2_2.setAttribute("Offset", absolutePosition);
		nextAnchor.setAttribute("rRef", idAnchor);
		nextAnchor.setAttribute("AnchorType", anchorType);
		nextAnchor.setXMLComment(xmlComment);
	}

}
