/*
 *
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

package org.cip4.jdflib.examples;

import java.util.zip.DataFormatException;

import org.cip4.jdflib.auto.JDFAutoDeviceInfo.EnumDeviceStatus;
import org.cip4.jdflib.auto.JDFAutoIdentificationField.EnumEncoding;
import org.cip4.jdflib.auto.JDFAutoLayoutElement.EnumElementType;
import org.cip4.jdflib.auto.JDFAutoPositionObj.EnumAnchor;
import org.cip4.jdflib.core.ElementName;
import org.cip4.jdflib.core.JDFComment;
import org.cip4.jdflib.core.JDFDoc;
import org.cip4.jdflib.core.JDFElement.EnumNodeStatus;
import org.cip4.jdflib.core.KElement;
import org.cip4.jdflib.datatypes.JDFIntegerRange;
import org.cip4.jdflib.datatypes.JDFIntegerRangeList;
import org.cip4.jdflib.datatypes.JDFMatrix;
import org.cip4.jdflib.datatypes.JDFXYPair;
import org.cip4.jdflib.node.JDFNode.EnumProcessUsage;
import org.cip4.jdflib.node.JDFNode.EnumType;
import org.cip4.jdflib.resource.devicecapability.JDFActionPool;
import org.cip4.jdflib.resource.process.JDFBarcodeProductionParams;
import org.cip4.jdflib.resource.process.JDFContentData;
import org.cip4.jdflib.resource.process.JDFContentList;
import org.cip4.jdflib.resource.process.JDFIdentificationField;
import org.cip4.jdflib.resource.process.JDFLayoutElement;
import org.cip4.jdflib.resource.process.JDFLayoutElementPart;
import org.cip4.jdflib.resource.process.JDFLayoutElementProductionParams;
import org.cip4.jdflib.resource.process.JDFPositionObj;
import org.cip4.jdflib.resource.process.JDFRunList;
import org.cip4.jdflib.util.StatusCounter;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**
 * @author Rainer Prosi, Heidelberger Druckmaschinen *
 */
public class ContentCreationTest extends PreflightTest
{
	/**
	 * test iteration
	 *
	 * @throws Exception
	 */
	@Test
	public void testLayoutElementPositioningExact() throws Exception
	{
		// TBD: Fuzzy, Sizes, literal text via comments
		KElement.setLongID(false);
		final JDFDoc d = new JDFDoc("JDF");
		n = d.getJDFRoot();
		n.setType(EnumType.LayoutElementProduction);

		final JDFRunList outRun = (JDFRunList) n.appendMatchingResource(ElementName.RUNLIST, EnumProcessUsage.AnyOutput, null);
		outRun.setFileURL("output.pdf");

		final JDFLayoutElementProductionParams lep = (JDFLayoutElementProductionParams) n.appendMatchingResource(ElementName.LAYOUTELEMENTPRODUCTIONPARAMS, EnumProcessUsage.AnyInput, null);
		lep.appendXMLComment("This is a \"well placed\" CTM defined mark\nThe anchor defines the 0,0 point to be transformed\nThe element to be placed is referenced by LayoutElement/FileSpec/URL",
				null);

		final JDFContentList cl = null;
		final JDFLayoutElementPart lePartPage = addLayoutElementPart(lep, cl);
		lePartPage.setID("IDPage");
		lePartPage.appendPositionObj().setSize(new JDFXYPair(21 * 44, 29 * 44));
		final JDFLayoutElementPart lePart = addLayoutElementPart(lep, cl);
		final JDFPositionObj positionObj = lePart.appendPositionObj();
		positionObj.setPageRange(new JDFIntegerRangeList(new JDFIntegerRange(0)));
		setNextAnchor(positionObj, "IDPage", "BottomLeft", "0 0", null, "Parent", 0);
		positionObj.setAttribute("Anchor", "BottomLeft");
		positionObj.setAttribute("PositionPolicy", "Exact");
		final JDFLayoutElement bkg = (JDFLayoutElement) lePart.appendElement("LayoutElement");
		bkg.setMimeURL("bkg.pdf");

		writeRoundTrip(n, "resources/layoutElementProductionParamsPositionObjExact");
	}

	/**
	 * test iteration
	 *
	 * @throws Exception
	 */
	@Test
	public void testLayoutElementPositioningFuzzy() throws Exception
	{
		// TBD: Fuzzy, Sizes, literal text via comments
		KElement.setLongID(false);
		final JDFDoc d = new JDFDoc("JDF");
		n = d.getJDFRoot();
		n.setType(EnumType.LayoutElementProduction);

		final JDFRunList outRun = (JDFRunList) n.appendMatchingResource(ElementName.RUNLIST, EnumProcessUsage.AnyOutput, null);
		outRun.setFileURL("output.pdf");

		final JDFLayoutElementProductionParams lep = (JDFLayoutElementProductionParams) n.appendMatchingResource(ElementName.LAYOUTELEMENTPRODUCTIONPARAMS, EnumProcessUsage.AnyInput, null);
		final JDFContentList cl = null;
		final JDFLayoutElementPart lePartPage = addLayoutElementPart(lep, cl);
		lePartPage.setID("IDPage");
		lePartPage.appendPositionObj().setSize(new JDFXYPair(21 * 44, 29 * 44));

		lep.appendXMLComment("This is a \"roughly placed\" reservation in the middle of the page", null);
		JDFLayoutElementPart lePart = addLayoutElementPart(lep, cl);
		JDFPositionObj positionObj = lePart.appendPositionObj();
		positionObj.setAttribute("PageRange", "0");
		// TODO discuss individual positions
		setNextAnchor(positionObj, "IDPage", "Center", null, null, "Parent", 0);
		positionObj.setAttribute("Anchor", "Center");
		positionObj.setAttribute("PositionPolicy", "Free");
		final String id = lePart.appendAnchor(null);

		JDFLayoutElement image = (JDFLayoutElement) lePart.appendElement("LayoutElement");
		image.setElementType(EnumElementType.Image);
		image.appendComment().setText("Please add an image of a palm tree on a beach here!");
		lep.appendXMLComment(
				"This is a \"roughly placed\" reservation 36 points below the previous image;\n NextPosition points from Anchor on this to NextAnchor on next,\n i.e. a positive vector specifies that next is shifted in the positive direction in the parent (in this case page) coordinate system",
				null);
		lePart = addLayoutElementPart(lep, cl);
		positionObj = lePart.appendPositionObj();
		positionObj.setAttribute("PageRange", "0");
		positionObj.setAttribute("Anchor", "TopCenter");
		positionObj.setAttribute("PositionPolicy", "Free");
		setNextAnchor(positionObj, id, "BottomCenter", "0 36", null, "Sibling", 0);

		image = (JDFLayoutElement) lePart.appendElement("LayoutElement");
		image.setElementType(EnumElementType.Image);
		image.appendComment().setText("Please add an image of a beach ball below the palm tree!");

		writeRoundTrip(n, "resources/layoutElementProductionParamsPositionObjFuzzy");
		// d.write2File(sm_dirTestDataTemp + File.separator + "LayoutPositionObj.jdf", 2, false);
		writeTest(n, "resources/layoutElementProductionParamsPositionObj.jdf", false, null);
	}

	/**
	 * test iteration
	 *
	 * @throws Exception
	 */
	@Test
	public void testLayoutElementPositioning() throws Exception
	{
		// TBD: Fuzzy, Sizes, literal text via comments
		KElement.setLongID(false);
		final JDFDoc d = new JDFDoc(ElementName.JDF);
		n = d.getJDFRoot();
		n.setType(EnumType.LayoutElementProduction);

		final JDFRunList outRun = (JDFRunList) n.appendMatchingResource(ElementName.RUNLIST, EnumProcessUsage.AnyOutput, null);
		outRun.setFileURL("output.pdf");

		final JDFLayoutElementProductionParams lep = (JDFLayoutElementProductionParams) n.appendMatchingResource(ElementName.LAYOUTELEMENTPRODUCTIONPARAMS, EnumProcessUsage.AnyInput, null);
		lep.appendXMLComment("This is a \"well placed\" CTM defined mark\nThe anchor defines the 0,0 point to be transformed\nThe element to be placed is referenced by LayoutElement/FileSpec/URL",
				null);

		/*
		 * JDFContentList cl = (JDFContentList) lep.appendElement(ElementName.CONTENTLIST); cl = (JDFContentList) cl.makeRootResource(null, null, true);
		 * cl.setXMLComment("this is an optional metadatapool for the content");
		 */
		final JDFContentList cl = null;
		final JDFLayoutElementPart lePartPage = addLayoutElementPart(lep, cl);
		lePartPage.setID("IDPage");
		lePartPage.appendPositionObj().setSize(new JDFXYPair(21 * 44, 29 * 44));
		JDFLayoutElementPart lePart = addLayoutElementPart(lep, cl);
		JDFPositionObj positionObj = lePart.appendPositionObj();
		positionObj.setPageRange(new JDFIntegerRangeList(new JDFIntegerRange(0)));
		setNextAnchor(positionObj, "IDPage", "BottomLeft", "0 0", null, "Parent", 0);
		positionObj.setAttribute("Anchor", "BottomLeft");
		positionObj.setAttribute("PositionPolicy", "Exact");
		final JDFLayoutElement bkg = (JDFLayoutElement) lePart.appendElement("LayoutElement");
		bkg.setMimeURL("bkg.pdf");

		lep.appendXMLComment("This is a \"roughly placed\" reservation in the middle of the page", null);
		lePart = addLayoutElementPart(lep, cl);
		positionObj = lePart.appendPositionObj();
		positionObj.setAttribute("PageRange", "0");
		// TODO discuss individual positions
		setNextAnchor(positionObj, "IDPage", "Center", null, null, "Parent", 0);
		positionObj.setAttribute("Anchor", "Center");
		positionObj.setAttribute("PositionPolicy", "Free");
		String id = lePart.appendAnchor(null);

		JDFLayoutElement image = (JDFLayoutElement) lePart.appendElement("LayoutElement");
		image.setElementType(EnumElementType.Image);
		image.appendComment().setText("Please add an image of a palm tree on a beach here!");

		lep.appendXMLComment(
				"This is a \"roughly placed\" reservation 36 points below the previous image;\n NextPosition points from Anchor on this to NextAnchor on next,\n i.e. a positive vector specifies that next is shifted in the positive direction in the parent (in this case page) coordinate system",
				null);
		lePart = addLayoutElementPart(lep, cl);
		positionObj = lePart.appendPositionObj();
		positionObj.setAttribute("PageRange", "0");
		positionObj.setAttribute("Anchor", "TopCenter");
		positionObj.setAttribute("PositionPolicy", "Free");
		setNextAnchor(positionObj, id, "BottomCenter", "0 36", null, "Sibling", 0);

		image = (JDFLayoutElement) lePart.appendElement("LayoutElement");
		image.setElementType(EnumElementType.Image);
		image.appendComment().setText("Please add an image of a beach ball below the palm tree!");

		lep.appendXMLComment("This is a \"well placed\" CTM defined mark\nThe anchor defines the 0,0 point to be transformed", null);
		lePart = addLayoutElementPart(lep, cl);
		positionObj = lePart.appendPositionObj();
		positionObj.setAttribute("PageRange", "0");
		setNextAnchor(positionObj, "IDPage", "BottomLeft", "2 3", null, "Parent", 0);
		positionObj.setAnchor(EnumAnchor.BottomLeft);
		positionObj.setAttribute("PositionPolicy", "Exact");
		addBarcode(lePart);

		lePart = addLayoutElementPart(lep, cl);
		positionObj = lePart.appendPositionObj();
		positionObj.setAttribute("PageRange", "0");
		setNextAnchor(positionObj, "IDPage", "TopRight", null, null, "Parent", 0);
		positionObj.setAttribute("Anchor", "TopRight");
		positionObj.appendXMLComment("This is a \"roughly placed\"  mark\nThe anchor at top right is placed at the right (=1.0) top(=1.0) position of the page.\nNo rotation is specified", null);
		positionObj.setAttribute("PositionPolicy", "Exact");
		addBarcode(lePart);
		lep.appendXMLComment(
				"This is a \"roughly placed\"  container for marks\nThe anchor at top left is defined in the !Unrotated! orientation.\n It is placed at the left (=0.0) bottom(=0.0) position of the page.\nThe text flows bottom to top (=Rotate 90 = counterclockwise)\n do we need margins?",
				null);

		lePart = addLayoutElementPart(lep, cl);
		final String idParent = lePart.appendAnchor(null);
		positionObj = lePart.appendPositionObj();
		positionObj.setAttribute("PageRange", "1");
		positionObj.setAttribute("Anchor", "TopLeft");
		positionObj.setAttribute("PositionPolicy", "Free");
		setNextAnchor(positionObj, "IDPage", "BottomCenter", "0 0", null, "Parent", 90);
		lep.appendXMLComment(
				"This is a  barcode inside the previous container\nThe anchor at bottom left is defined in the !Unrotated! orientation.\n It is placed at the left (=0.0) bottom(=0.0) position of the container.",
				null);

		lePart = addLayoutElementPart(lep, cl);
		id = lePart.appendAnchor(null);
		positionObj = lePart.appendPositionObj();
		positionObj.setAttribute("Anchor", "BottomLeft");
		setNextAnchor(positionObj, idParent, "BottomLeft", "0 0", null, "Parent", 0);
		addBarcode(lePart);
		lep.appendXMLComment(
				"This is a disclaimer text inside the previous container\nThe anchor at top left is defined in the !Unrotated! orientation.\n The barcode and text are justified with their top margins and spaced by 72 points\n which corresponds to the left of the page because the container is rotated 90�\n"
						+ "AbsoluteSize specifies the size of the object in points",
				null);

		lePart = addLayoutElementPart(lep, cl);
		positionObj = lePart.appendPositionObj();
		setNextAnchor(positionObj, id, "TopRight", "-72 0", null, "Sibling", 0);

		positionObj.setAttribute("Anchor", "TopLeft");
		// positionObj.setAttribute("ParentRef", idParent);
		positionObj.setSize(new JDFXYPair(200, 300));
		JDFLayoutElement text = (JDFLayoutElement) lePart.appendElement("LayoutElement");
		text.setElementType(EnumElementType.Text);
		text.setMimeURL("file://myServer/disclaimers/de/aspirin.txt");
		lep.appendXMLComment("This is a \"VERY roughly placed\" piece of text somewhere on pages 2-3\n" + "RelativeSize specifies the size of the object as a ratio of the size of the container",
				null);

		lePart = addLayoutElementPart(lep, cl);
		positionObj = lePart.appendPositionObj();
		positionObj.setAttribute("PageRange", "1 ~ 2");
		positionObj.setAttribute("RelativeSize", "0.8 0.5");
		text = (JDFLayoutElement) lePart.appendElement("LayoutElement");
		text.setElementType(EnumElementType.Text);
		final JDFComment instructionComment = text.appendComment();
		instructionComment.setName("Instructions");
		instructionComment.setText("Please add some text about the image of a palm tree on a beach here!");
		lep.appendXMLComment("This is another \"VERY roughly placed\" piece of text somewhere on pages 2-3; the text source is the JDF", null);

		lePart = addLayoutElementPart(lep, cl);
		positionObj = lePart.appendPositionObj();
		positionObj.setAttribute("PageRange", "1 ~ 2");
		text = (JDFLayoutElement) lePart.appendElement("LayoutElement");
		text.setElementType(EnumElementType.Text);

		final JDFComment textSrc = text.appendComment();
		textSrc.setName("TextInput");
		textSrc.setText("Laurum Ipsum Blah blah blah!\n btw. this is unformatted plain text and nothing else!");

		writeRoundTrip(n, "LayoutPositionObj");
	}

	/**
	 *
	 * @param lePart
	 */
	private void addBarcode(final JDFLayoutElementPart lePart)
	{
		final JDFBarcodeProductionParams barcodeProductionParams = lePart.appendBarcodeProductionParams();
		final JDFIdentificationField identificationField = barcodeProductionParams.appendIdentificationField();
		identificationField.setEncoding(EnumEncoding.Barcode);
		identificationField.setEncodingDetails("EAN");
		barcodeProductionParams.setXMLComment("barcode details here", true);
	}

	/**
	 *
	 * @param lep
	 * @param cl
	 * @return
	 */
	private JDFLayoutElementPart addLayoutElementPart(final JDFLayoutElementProductionParams lep, final JDFContentList cl)
	{
		final JDFLayoutElementPart lePart = lep.appendLayoutElementPart();
		addMetaData(cl, lePart);
		return lePart;
	}

	/**
	 * @param cl
	 * @param lePart
	 * @return
	 */
	private JDFContentData addMetaData(final JDFContentList cl, final JDFLayoutElementPart lePart)
	{
		if (cl == null)
			return null;

		final JDFContentData cd = cl.appendContentData();
		// lePart.setAttribute("ContentDataIndex", cd.getIndex(), null);
		return cd;
	}

	/**
	 * @param sm2_2
	 * @param idAnchor
	 * @param anchor
	 * @param absolutePosition
	 * @param xmlComment
	 * @param anchorType
	 * @param rotation
	 * @throws DataFormatException
	 */
	private static void setNextAnchor(final KElement sm2_2, final String idAnchor, final String anchor, final String absolutePosition, final String xmlComment, final String anchorType,
			final double rotation) throws DataFormatException
	{
		final KElement nextAnchor = sm2_2.appendElement(ElementName.REFANCHOR);
		nextAnchor.setAttribute("Anchor", anchor);
		final JDFMatrix m = new JDFMatrix("1 0 0 1 0 0");
		final JDFXYPair xy = absolutePosition == null ? null : new JDFXYPair(absolutePosition);
		m.shift(xy);
		m.rotate(rotation);
		if (xy != null || rotation != 0)
			sm2_2.setAttribute("CTM", m.toString());
		nextAnchor.setAttribute("rRef", idAnchor);
		nextAnchor.setAttribute("AnchorType", anchorType);
		nextAnchor.setXMLComment(xmlComment, true);
	}

	/**
	 * test preflight concepts in layoutelementproduction
	 *
	 * @throws Exception
	 */
	@Test
	public void testLayoutPreflight() throws Exception
	{
		KElement.setLongID(false);
		final JDFDoc d = new JDFDoc("JDF");
		n = d.getJDFRoot();
		n.setType(EnumType.LayoutElementProduction);

		final JDFRunList outRun = (JDFRunList) n.appendMatchingResource(ElementName.RUNLIST, EnumProcessUsage.AnyOutput, null);
		outRun.setFileURL("output.pdf");

		final JDFLayoutElementProductionParams lep = (JDFLayoutElementProductionParams) n.appendMatchingResource(ElementName.LAYOUTELEMENTPRODUCTIONPARAMS, EnumProcessUsage.AnyInput, null);
		final JDFComment com = lep.appendComment();
		com.setName("Instruction");
		com.setText("Add any human readable instructions here");

		// new
		aPool = (JDFActionPool) lep.appendElement(ElementName.ACTIONPOOL);

		// now some simple tests...
		appendNumPagesAction();
		// appendSeparationAction();
		// appendBWSeparationAction();
		appendTrimBoxAction();
		appendResolutionAction();

		StatusCounter su = new StatusCounter(n, null, null);
		su.setPhase(EnumNodeStatus.InProgress, "Creative Work", EnumDeviceStatus.Running, null);

		su.getDocJMFPhaseTime();
		Thread.sleep(1000);
		su = new StatusCounter(n, null, null);
		su.setPhase(EnumNodeStatus.InProgress, "Creative Work", EnumDeviceStatus.Running, null);
		su.getDocJMFPhaseTime();
		Thread.sleep(1000);
		su = new StatusCounter(n, null, null);
		su.setPhase(EnumNodeStatus.Completed, "done", EnumDeviceStatus.Idle, null);
		su.getDocJMFPhaseTime();

		writeTest(n, "resources/layoutElementProductionParamsPreflight.jdf", false, null);

	}

	/**
	 * @see PreflightTest#setUp()
	 */
	@Override
	@BeforeEach
	public void setUp() throws Exception
	{
		super.setUp();
		KElement.setLongID(false);
	}
}
