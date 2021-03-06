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

package org.cip4.jdflib.resource;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import java.io.File;
import java.util.Vector;

import org.cip4.jdflib.JDFTestCaseBase;
import org.cip4.jdflib.auto.JDFAutoLayout;
import org.cip4.jdflib.auto.JDFAutoMedia.EnumMediaType;
import org.cip4.jdflib.auto.JDFAutoPart.EnumSide;
import org.cip4.jdflib.auto.JDFAutoRegisterMark.EnumMarkUsage;
import org.cip4.jdflib.core.AttributeName;
import org.cip4.jdflib.core.ElementName;
import org.cip4.jdflib.core.JDFDoc;
import org.cip4.jdflib.core.JDFElement;
import org.cip4.jdflib.core.JDFElement.EnumValidationLevel;
import org.cip4.jdflib.core.JDFElement.EnumVersion;
import org.cip4.jdflib.core.JDFException;
import org.cip4.jdflib.core.JDFParser;
import org.cip4.jdflib.core.JDFResourceLink.EnumUsage;
import org.cip4.jdflib.core.KElement;
import org.cip4.jdflib.core.VElement;
import org.cip4.jdflib.core.VString;
import org.cip4.jdflib.core.XMLDoc;
import org.cip4.jdflib.datatypes.JDFMatrix;
import org.cip4.jdflib.datatypes.JDFRectangle;
import org.cip4.jdflib.datatypes.JDFXYPair;
import org.cip4.jdflib.node.JDFNode;
import org.cip4.jdflib.node.JDFNode.EnumProcessUsage;
import org.cip4.jdflib.node.JDFNode.EnumType;
import org.cip4.jdflib.resource.JDFResource.EnumPartIDKey;
import org.cip4.jdflib.resource.process.JDFColorControlStrip;
import org.cip4.jdflib.resource.process.JDFContentObject;
import org.cip4.jdflib.resource.process.JDFLayout;
import org.cip4.jdflib.resource.process.JDFMedia;
import org.cip4.jdflib.resource.process.JDFRegisterMark;
import org.cip4.jdflib.resource.process.JDFRunList;
import org.cip4.jdflib.resource.process.JDFSurface;
import org.cip4.jdflib.resource.process.postpress.JDFSheet;
import org.junit.Before;
import org.junit.Test;

/**
 * all kinds of fun tests around JDF 1.2 vs JDF 1.3 Layouts also some tests for automated layout
 *
 */
public class JDFLayoutTest extends JDFTestCaseBase
{

	private JDFDoc doc = null;
	private JDFNode n = null;
	private JDFRunList rl = null;

	/**
	 * @see org.cip4.jdflib.JDFTestCaseBase#setUp()
	 * @throws Exception
	 */
	@Override
	@Before
	public void setUp() throws Exception
	{
		super.setUp();
		KElement.setLongID(false);
		reset();
	}

	/**
	 *
	 */
	private void reset()
	{
		doc = new JDFDoc("JDF");
		n = doc.getJDFRoot();
		n.setType(EnumType.Imposition);
		rl = (JDFRunList) n.appendMatchingResource(ElementName.RUNLIST, EnumProcessUsage.AnyInput, null);
	}

	// ////////////////////////////////////////////////////////////////////////

	/**
	 *
	 */
	@Test
	public void testIsNewLayout()
	{
		assertEquals("version ok", n.getVersion(false), defaultVersion);
		final JDFLayout lo = (JDFLayout) n.appendMatchingResource(ElementName.LAYOUT, EnumProcessUsage.AnyInput, null);
		assertTrue("lo 1.3", JDFLayout.isNewLayout(lo));
		n.setVersion(EnumVersion.Version_1_2);
		assertFalse("lo 1.3", JDFLayout.isNewLayout(lo));
		lo.addPartition(EnumPartIDKey.SheetName, "Sheet1");
		assertTrue("lo 1.3", JDFLayout.isNewLayout(lo));
		assertFalse("l no layout", JDFLayout.isNewLayout(rl));
	}

	/**
	 *
	 */
	@Test
	public void testAutoRegister()
	{

		assertEquals("version ok", n.getVersion(false), defaultVersion);
		final JDFLayout lo = (JDFLayout) n.appendMatchingResource(ElementName.LAYOUT, EnumProcessUsage.AnyInput, null);
		final JDFColorControlStrip autoReg = lo.appendMarkObject().appendColorControlStrip();
		autoReg.setStripType("AutoRegister");
		autoReg.appendElement("SeparationSpec").setAttribute("Name", "Black");
		autoReg.appendElement("SeparationSpec").setAttribute("Name", "Cyan");
		autoReg.appendElement("SeparationSpec").setAttribute("Name", "Yellow");
		autoReg.appendElement("SeparationSpec").setAttribute("Name", "Magenta");
		autoReg.appendElement("SeparationSpec").setAttribute("Name", "Spot1");
		autoReg.appendElement("SeparationSpec").setAttribute("Name", "Spot2");
		JDFColorControlStrip fms = lo.getMarkObject(0).appendColorControlStrip();
		fms.setStripType("FMS");
		fms.appendElement("SeparationSpec").setAttribute("Name", "Black");
		fms.appendElement("SeparationSpec").setAttribute("Name", "Yellow");
		fms.appendElement("SeparationSpec").setAttribute("Name", "Magenta");
		fms.appendElement("SeparationSpec").setAttribute("Name", "Cyan");

		fms = lo.getMarkObject(0).appendColorControlStrip();
		fms.setStripType("FMS");
		fms.appendElement("SeparationSpec").setAttribute("Name", "Spot1");
		fms.appendElement("SeparationSpec").setAttribute("Name", "Spot2");
		doc.write2File(sm_dirTestDataTemp + "autoregister.jdf", 2, false);
	}

	/**
	 *
	 */
	@Test
	public void testGetAllOrds()
	{
		final JDFLayout lo = prepare44();
		final Vector<Integer> v = lo.getAllOrds();
		assertEquals(v.size(), 32);
		for (int i = 0; i < 16; i++)
			assertTrue("pos: " + i, v.contains(new Integer(i)));
	}

	/**
	 *
	 */
	@Test
	public void testCalcMaxOrd()
	{
		final JDFLayout lo = prepare44();
		assertEquals(lo.calcMaxOrd(), 16);
		((JDFAutoLayout) lo.getLeaves(false).elementAt(0)).appendContentObject().setOrd(20);
		assertEquals(lo.calcMaxOrd(), 21);
	}

	/**
	 *
	 */
	@Test
	public void testCalcNumSame()
	{
		final JDFLayout lo = prepare44();
		assertEquals(lo.calcNumSame(), 2);
		((JDFAutoLayout) lo.getLeaves(false).elementAt(0)).appendContentObject().setOrd(20);
		assertEquals(lo.calcNumSame(), -1);
	}

	/**
	 * @return
	 */
	private JDFLayout prepare44()
	{
		final JDFLayout lo = (JDFLayout) n.appendMatchingResource(ElementName.LAYOUT, EnumProcessUsage.AnyInput, null);
		lo.appendMedia();
		for (int i = 0; i < 4; i++)
		{
			final JDFResource sheet = lo.addPartition(EnumPartIDKey.SheetName, "Sheet" + i);
			final VString fb = new VString("Front Back", null);
			for (final String side : fb)
			{
				final JDFLayout surf = (JDFLayout) sheet.addPartition(EnumPartIDKey.Side, side);
				for (int j = 0; j < 4; j++)
				{
					final JDFContentObject co = surf.appendContentObject();
					co.setOrd(4 * i + j);
				}
			}
		}
		return lo;
	}

	/**
	 *
	 */
	@Test
	public void testMedia()
	{
		final JDFLayout lo = (JDFLayout) n.appendMatchingResource(ElementName.LAYOUT, EnumProcessUsage.AnyInput, null);
		lo.appendMedia();
		final JDFMedia m2 = lo.appendMedia();
		assertNotNull("2. Media ok", m2);
		assertEquals(m2, lo.getMedia(1));
		assertEquals(m2, lo.getCreateMedia(1));
	}

	/**
	 *
	 */
	@Test
	public void testSheetCondition()
	{
		final JDFLayout lo = (JDFLayout) n.appendMatchingResource(ElementName.LAYOUT, EnumProcessUsage.AnyInput, null);
		lo.appendSheetCondition();
		final JDFSheetCondition sc2 = lo.appendSheetCondition();
		assertNotNull("2. SC ok", sc2);
		assertEquals(sc2, lo.getSheetCondition(1));
		assertEquals(sc2, lo.getSheetCondition(1));
	}

	/**
	 *
	 */
	@Test
	public void testLogicalStackSchema()
	{
		final JDFLayout lo = (JDFLayout) n.appendMatchingResource(ElementName.LAYOUT, EnumProcessUsage.AnyInput, null);
		lo.appendLogicalStackParams().appendStack().setLogicalStackOrd(1);
		final String s = lo.getOwnerDocument_JDFElement().write2String(2);
		final JDFParser p = getSchemaParser();
		final JDFDoc dNew = p.parseString(s);
		final XMLDoc dVal = dNew.getValidationResult();
		assertEquals(dVal.getRoot().getAttribute("ValidationResult"), "Valid");
	}

	/**
	 * @throws Exception
	 */
	@Test
	public void testDynamicMarks() throws Exception
	{
		JDFElement.setLongID(false);
		final JDFLayout lo = (JDFLayout) n.appendMatchingResource(ElementName.LAYOUT, EnumProcessUsage.AnyInput, null);
		lo.setXMLComment("Layout that illustrates dynamic mark placement - all margins are 25 points (gutter=2*25)", true);
		lo.setSurfaceContentsBox(new JDFRectangle(0, 0, 500, 350));
		final JDFSheet s = lo.appendSheet();
		final JDFSurface su = s.appendFrontSurface();

		final JDFContentObject co0 = su.appendContentObject();
		co0.setOrd(0);
		JDFMatrix m1 = JDFMatrix.getUnitMatrix();
		m1.shift(25, 25);
		co0.setCTM(JDFMatrix.getUnitMatrix());
		co0.setTrimSize(new JDFXYPair(200, 300));
		final String[] id = new String[2];
		id[0] = co0.appendAnchor(null);
		final JDFContentObject co1 = su.appendContentObject();
		m1 = JDFMatrix.getUnitMatrix();
		m1.shift(275, 25);
		co1.setCTM(m1);
		co1.setTrimSize(200, 300);
		id[1] = co1.appendAnchor(null);

		{
			final JDFMarkObject mark0 = su.appendMarkObject();
			mark0.setXMLComment("Register Mark on the top right of the sheet - assumed size is 20*30, assumed sheet size is 500*350", true);
			mark0.setTrimSize(20, 30);
			mark0.setCTM(new JDFMatrix(1, 0, 0, 1, 500 - 20, 350 - 30));
			mark0.appendDeviceMark().setAttribute("Anchor", "TopRight");
			mark0.appendRegisterMark().setXMLComment("mark metadata goes here", true);
			appendRefAnchor(mark0, "TopRight", "Parent", null);
		}

		{
			final JDFMarkObject mark0 = su.appendMarkObject();
			mark0.setXMLComment("Vertical Slug Line beginning at the top of the bottom margin of of the sheet between the 2 pages"
					+ "\nnote that no TrimSize need be specified and therefore TrimCTM / CTM place the point defined by @Anchor"
					+ "\nnote also that the anchor points to centerleft which is in the unrotated (horizontal) cs of the slug line", true);
			final JDFMatrix m0 = new JDFMatrix(1, 0, 0, 1, 0, 0);
			m0.rotate(90);
			m0.shift(250, 25);
			mark0.setCTM(m0);
			final JDFDeviceMark dm = mark0.appendDeviceMark();
			dm.setAttribute("Anchor", "CenterLeft");
			dm.setFontSize(10);
			dm.setFont("GhostCrypt");
			final JDFJobField jf = mark0.appendJobField();
			jf.setXMLComment("Result: Sheet Printed by Dracula at the moonphase FullMoon", true);
			jf.setAttribute("JobFormat", "Sheet Printed by %s at the moonphase %s");
			jf.setAttribute("JobTemplate", "Operator,MoonPhase");
			appendRefAnchor(mark0, "BottomCenter", "Parent", null);
		}

		for (int i = 0; i < 2; i++)
		{
			final JDFMarkObject mark0 = su.appendMarkObject();
			mark0.setXMLComment("Horizonzal Slug Line, centered 5 points over the top of page " + i + "\nnote that page is not yet a predefined token\n", true);
			final JDFMatrix m0 = new JDFMatrix(1, 0, 0, 1, 0, 0);
			m0.rotate(90);
			m0.shift(25 + 100, 300 + 25 + 5);
			if (i == 1)
			{
				m0.shift(250, 0);
			}
			mark0.setCTM(m0);
			final JDFDeviceMark dm = mark0.appendDeviceMark();
			dm.setAttribute("Anchor", "BottomCenter");
			dm.setFontSize(8);
			final JDFJobField jf = mark0.appendJobField();
			jf.setXMLComment("Result: Page # " + i + " for Customer, Polanski - Job: J11", true);
			jf.setAttribute("JobFormat", "Page # %i for Customer, %s - Job: %s");
			jf.setAttribute("JobTemplate", "Page,JobRecipientName,JobID");
			appendRefAnchor(mark0, "BottomCenter", "Sibling", id[i]);
		}
		doc.write2File(sm_dirTestDataTemp + "LayoutDynamicMarks.jdf", 2, false);

	}

	/**
	 * @param mark0
	 * @param anchor
	 * @param anchorType
	 * @param rRef
	 * @return
	 */
	public static KElement appendRefAnchor(final JDFMarkObject mark0, final String anchor, final String anchorType, final String rRef)
	{
		final KElement refAnchor = mark0.getCreateElement("RefAnchor", null, 0);
		refAnchor.setAttribute("Anchor", anchor);
		refAnchor.setAttribute("AnchorType", anchorType);
		refAnchor.setAttribute("rRef", rRef);
		return refAnchor;
	}

	/**
	 * build a 1.2 layout using appendsignature etc
	 *
	 */
	@Test
	public void testBuildOldLayout()
	{
		reset();

		n.setVersion(EnumVersion.Version_1_2);
		final JDFLayout lo = (JDFLayout) n.appendMatchingResource(ElementName.LAYOUT, EnumProcessUsage.AnyInput, null);
		assertFalse("lo 1.3", JDFLayout.isNewLayout(lo));
		JDFSignature si = lo.appendSignature();
		si.setName("Sig1");
		assertEquals("signature name", si.getLocalName(), ElementName.SIGNATURE);
		assertFalse(si.hasAttribute(AttributeName.CLASS));
		si = lo.appendSignature();
		si.setName("Sig2");
		assertEquals("num sigs", 2, lo.numSignatures());
		assertEquals("signature name", si.getLocalName(), ElementName.SIGNATURE);

		JDFSheet sh = si.appendSheet();
		sh.setName("Sheet2_1");
		sh.makeRootResource(null, null, true); // see what happens with
		// refelements
		sh = si.appendSheet();
		sh.setName("Sheet2_2");
		assertEquals("num sheets", 2, si.numSheets());
		assertEquals("sheet name", sh.getLocalName(), ElementName.SHEET);
		sh = si.getCreateSheet(4);
		assertEquals("num sheets", 3, si.numSheets());
		assertEquals("sheet name", sh.getLocalName(), ElementName.SHEET);
		sh = si.getSheet(2);
		assertEquals("num sheets", 3, si.numSheets());
		assertEquals("sheet name", sh.getLocalName(), ElementName.SHEET);

		JDFSurface su = sh.appendFrontSurface();
		assertEquals("num surfaces", 1, sh.numSurfaces());
		assertEquals("sheet name", su.getLocalName(), ElementName.SURFACE);
		assertTrue("hasfrontSurface", sh.hasFrontSurface());
		su.appendContentObject();
		su.appendMarkObject();
		su.appendContentObject();
		su.appendContentObject();

		su = sh.appendBackSurface();
		su.makeRootResource(null, null, true);
		su.appendContentObject();
		su.appendMarkObject();
		su.appendContentObject();
		su.appendContentObject();
		su.appendMarkObject();
		assertEquals("num surfaces", 2, sh.numSurfaces());
		assertEquals("sheet name", su.getLocalName(), ElementName.SURFACE);
		assertTrue("hasBackSurface", sh.hasBackSurface());

		try
		{
			sh.appendBackSurface();
			fail("append second surface");
		}
		catch (final JDFException e)
		{/* nop */
		}

		si = lo.getCreateSignature(4);
		assertEquals("num sigs", 3, lo.numSignatures());
		assertEquals("signature name", si.getLocalName(), ElementName.SIGNATURE);
		si = lo.getSignature(2);
		assertEquals("num sigs", 3, lo.numSignatures());
		assertEquals("signature name", si.getLocalName(), ElementName.SIGNATURE);
		si = lo.getSignature(5);
		assertNull("si null", si);
		assertEquals(lo.numSignatures(), lo.getSignatureVector().size());

	}

	/**
	 * build a 1.3 layout using appendsignature etc
	 *
	 */
	@Test
	public void testBuildNewLayout()
	{
		reset();

		final JDFLayout lo = (JDFLayout) n.appendMatchingResource(ElementName.LAYOUT, EnumProcessUsage.AnyInput, null);
		assertTrue("lo 1.3", JDFLayout.isNewLayout(lo));
		JDFSignature si = lo.appendSignature();
		assertEquals("signature name", si.getLocalName(), ElementName.LAYOUT);
		si = lo.appendSignature();
		assertEquals("num sigs", 2, lo.numSignatures());
		assertEquals("signature name", si.getLocalName(), ElementName.LAYOUT);

		JDFSheet sh = si.appendSheet();
		sh = si.appendSheet();
		assertEquals("num sheets", 2, si.numSheets());
		assertEquals("sheet name", sh.getLocalName(), ElementName.LAYOUT);
		sh = si.getCreateSheet(4);
		assertEquals("num sheets", 3, si.numSheets());
		assertEquals("sheet name", sh.getLocalName(), ElementName.LAYOUT);
		sh = si.getSheet(2);
		assertEquals("num sheets", 3, si.numSheets());
		assertEquals("sheet name", sh.getLocalName(), ElementName.LAYOUT);

		JDFSurface su = sh.appendFrontSurface();
		assertEquals("num surfaces", 1, sh.numSurfaces());
		assertEquals("sheet name", su.getLocalName(), ElementName.LAYOUT);
		assertTrue("hasfrontSurface", sh.hasFrontSurface());

		su = sh.appendBackSurface();
		assertEquals("num surfaces", 2, sh.numSurfaces());
		assertEquals("sheet name", su.getLocalName(), ElementName.LAYOUT);

		try
		{
			sh.appendBackSurface();
			fail("no two back surfaces");
		}
		catch (final JDFException e)
		{/* nop */
		}

		si = lo.getCreateSignature(4);
		assertEquals("num sigs", 3, lo.numSignatures());
		assertEquals("signature name", si.getLocalName(), ElementName.LAYOUT);
		si = lo.getSignature(2);
		assertEquals("num sigs", 3, lo.numSignatures());
		assertEquals("signature name", si.getLocalName(), ElementName.LAYOUT);
		si = lo.getSignature(5);
		assertNull("si null", si);
		assertTrue("layout valid", lo.isValid(EnumValidationLevel.Complete));
		assertEquals(lo.numSignatures(), lo.getSignatureVector().size());
	}

	// ///////////////////////////////////////////////////

	/**
	 *
	 */
	@Test
	public void testFixToNewLayout()
	{
		testBuildOldLayout();
		n.fixVersion(EnumVersion.Version_1_3);
		final JDFLayout lo = (JDFLayout) n.getMatchingResource(ElementName.LAYOUT, EnumProcessUsage.AnyInput, null, 0);
		assertTrue(JDFLayout.isNewLayout(lo));
		final JDFSignature si = lo.getSignature(0);
		assertEquals(si.getSignatureName(), "Sig1");
		assertFalse(si.hasAttribute(AttributeName.CLASS));
	}

	/**
	 *
	 */
	@Test
	public void testFixToNewLayoutWithPartIDKeys()
	{
		testBuildOldLayout();
		final JDFLayout loOld = (JDFLayout) n.getMatchingResource(ElementName.LAYOUT, EnumProcessUsage.AnyInput, null, 0);
		loOld.setPartIDKeys(new VString("SignatureName SheetName Side", null));
		assertFalse(JDFLayout.isNewLayout(loOld));
		n.fixVersion(EnumVersion.Version_1_3);
		final JDFLayout lo = (JDFLayout) n.getMatchingResource(ElementName.LAYOUT, EnumProcessUsage.AnyInput, null, 0);
		assertTrue(JDFLayout.isNewLayout(lo));
		final JDFSignature si = lo.getSignature(0);
		assertEquals(si.getSignatureName(), "Sig1");
		assertFalse(si.hasAttribute(AttributeName.CLASS));
	}

	// ///////////////////////////////////////////////////

	/**
	 *
	 */
	@Test
	public void testFixFromNewLayout()
	{
		testBuildNewLayout();
		n.fixVersion(EnumVersion.Version_1_2);
		final JDFLayout lo = (JDFLayout) n.getMatchingResource(ElementName.LAYOUT, EnumProcessUsage.AnyInput, null, 0);
		assertFalse(JDFLayout.isNewLayout(lo));
		final JDFSignature si = lo.getSignature(0);
		assertEquals(si.getLocalName(), ElementName.SIGNATURE);
	}

	// ///////////////////////////////////////////////////
	/**
	 *
	 */
	@Test
	public void testFixFromFlatNewLayout()
	{
		n.setVersion(EnumVersion.Version_1_3);
		final JDFLayout loNew = (JDFLayout) n.appendMatchingResource(ElementName.LAYOUT, EnumProcessUsage.AnyInput, null);
		final JDFContentObject co1 = loNew.appendContentObject();

		n.fixVersion(EnumVersion.Version_1_2);
		final JDFLayout lo = (JDFLayout) n.getMatchingResource(ElementName.LAYOUT, EnumProcessUsage.AnyInput, null, 0);
		assertFalse(JDFLayout.isNewLayout(lo));
		final JDFSignature si = lo.getSignature(0);
		assertEquals(si.getLocalName(), ElementName.SIGNATURE);
		final JDFSheet sh = si.getSheet(0);
		final JDFSurface su = sh.getSurface(EnumSide.Front);
		assertEquals(co1, su.getContentObject(0));
	}

	// ///////////////////////////////////////////////////
	/**
	 *
	 */
	@Test
	public void testFixFromSheetNewLayout()
	{
		n.setVersion(EnumVersion.Version_1_3);
		JDFLayout loNew = (JDFLayout) n.appendMatchingResource(ElementName.LAYOUT, EnumProcessUsage.AnyInput, null);
		loNew = (JDFLayout) loNew.addPartition(EnumPartIDKey.SheetName, "s1");
		final JDFContentObject co1 = loNew.appendContentObject();

		n.fixVersion(EnumVersion.Version_1_2);
		final JDFLayout lo = (JDFLayout) n.getMatchingResource(ElementName.LAYOUT, EnumProcessUsage.AnyInput, null, 0);
		assertFalse(JDFLayout.isNewLayout(lo));
		final JDFSignature si = lo.getSignature(0);
		assertEquals(si.getLocalName(), ElementName.SIGNATURE);
		final JDFSheet sh = si.getSheet(0);
		final JDFSurface su = sh.getSurface(EnumSide.Front);
		assertEquals(co1, su.getContentObject(0));
	}

	// ///////////////////////////////////////////////////
	/**
	 *
	 */
	@Test
	public void testFixFromSurfaceNewLayout()
	{
		n.setVersion(EnumVersion.Version_1_3);
		JDFLayout loNew = (JDFLayout) n.appendMatchingResource(ElementName.LAYOUT, EnumProcessUsage.AnyInput, null);
		loNew = (JDFLayout) loNew.addPartition(EnumPartIDKey.Side, "Back");
		final JDFContentObject co1 = loNew.appendContentObject();

		n.fixVersion(EnumVersion.Version_1_2);
		final JDFLayout lo = (JDFLayout) n.getMatchingResource(ElementName.LAYOUT, EnumProcessUsage.AnyInput, null, 0);
		assertFalse(JDFLayout.isNewLayout(lo));
		final JDFSignature si = lo.getSignature(0);
		assertEquals(si.getLocalName(), ElementName.SIGNATURE);
		final JDFSheet sh = si.getSheet(0);
		JDFSurface su = sh.getSurface(EnumSide.Front);
		assertNull(su);
		su = sh.getSurface(EnumSide.Back);
		assertEquals(co1, su.getContentObject(0));
	}

	// ///////////////////////////////////////////////////

	/**
	 * test getPlacedObjectVector
	 */
	@Test
	public void testGetPlacedObjectVector()
	{
		testBuildOldLayout();
		final JDFLayout lo = (JDFLayout) n.getMatchingResource(ElementName.LAYOUT, EnumProcessUsage.AnyInput, null, 0);
		final JDFSurface su = lo.getSignature(1).getSheet(2).getSurface(EnumSide.Front);
		final VElement v = su.getPlacedObjectVector();
		assertEquals(v.size(), 4);
		assertTrue(v.elementAt(0) instanceof JDFContentObject);
		assertTrue(v.elementAt(1) instanceof JDFMarkObject);
		assertTrue(v.elementAt(2) instanceof JDFContentObject);
		assertTrue(v.elementAt(3) instanceof JDFContentObject);

	}

	/**
	 * tests the typed media getter
	 */
	@Test
	public void testGetMedia()
	{
		final JDFLayout lo = (JDFLayout) new JDFDoc("JDF").getRoot().appendElement(ElementName.RESOURCEPOOL).appendElement(ElementName.LAYOUT);
		final JDFLayout s1 = (JDFLayout) lo.addPartition(EnumPartIDKey.SheetName, "s1");
		final JDFMedia media = lo.appendMedia();
		media.setMediaType(EnumMediaType.Plate);
		assertNull(lo.getMedia(EnumMediaType.Paper));
		assertNull(s1.getMedia(EnumMediaType.Paper));
		assertEquals(s1.getMedia(EnumMediaType.Plate), media);
		assertEquals(lo.getMedia(EnumMediaType.Plate), media);

		final JDFMedia media2 = s1.appendMedia();
		media2.setMediaType(EnumMediaType.Paper);
		assertNull(s1.getMedia(EnumMediaType.Plate));
		assertEquals(s1.getMedia(EnumMediaType.Paper), media2);
		final JDFMedia media3 = s1.appendMedia();
		media3.setMediaType(EnumMediaType.Plate);
		assertEquals(s1.getMedia(EnumMediaType.Paper), media2);
		assertEquals(s1.getMedia(EnumMediaType.Plate), media3);
		media3.makeRootResource(null, null, true);
		assertEquals(s1.getMedia(EnumMediaType.Paper), media2);
		assertEquals(s1.getMedia(EnumMediaType.Plate), media3);
	}

	/**
	 *
	 */
	// ///////////////////////////////////////////////////
	@Test
	public void testGetLayoutLeavesOld()
	{
		testBuildOldLayout();

		final JDFLayout lo = (JDFLayout) n.getMatchingResource(ElementName.LAYOUT, EnumProcessUsage.AnyInput, null, 0);
		VElement leaves = lo.getLayoutLeaves(false);
		assertEquals(leaves.size(), 2);
		final JDFSignature si = lo.getSignature(1);
		leaves = si.getLayoutLeaves(false);
		assertEquals(leaves.size(), 2);
		final JDFSheet sh = si.getSheet(2);
		leaves = sh.getLayoutLeaves(false);
		assertEquals(leaves.size(), 2);

	}

	// ///////////////////////////////////////////////////
	/**
	 *
	 */
	@Test
	public void testGetLayoutLeavesNew()
	{
		testBuildNewLayout();

		final JDFLayout lo = (JDFLayout) n.getMatchingResource(ElementName.LAYOUT, EnumProcessUsage.AnyInput, null, 0);
		VElement leaves = lo.getLayoutLeaves(false);
		assertEquals("2 Sigs, 2 sheets, 2 surfaces", leaves.size(), 6);
		final JDFSignature si = lo.getSignature(1);
		leaves = si.getLayoutLeaves(false);
		assertEquals("2 sheets, 2 surfaces", leaves.size(), 4);
		final JDFSheet sh = si.getSheet(2);
		leaves = sh.getLayoutLeaves(false);
		assertEquals("2 surfaces", leaves.size(), 2);

	}

	// ///////////////////////////////////////////////////
	/**
	 *
	 */
	@Test
	public void testGetSignatureVector_Old()
	{
		testBuildOldLayout();
		final JDFLayout lo = (JDFLayout) n.getMatchingResource(ElementName.LAYOUT, EnumProcessUsage.AnyInput, null, 0);
		final VElement v = lo.getSignatureVector();
		final JDFSignature sig = (JDFSignature) v.elementAt(0);
		assertEquals(sig.getSignatureName(), sig.getName());
		assertEquals(sig.getSignatureName(), "Sig1");
		final JDFSignature sig2 = (JDFSignature) v.elementAt(1);
		assertEquals(sig2.getSignatureName(), sig2.getName());
		assertEquals(sig2.getSignatureName(), "Sig2");
		final VElement vSheet = sig2.getSheetVector();
		final JDFSheet s1 = (JDFSheet) vSheet.elementAt(1); // don't try 0 it will
		// fail because it is
		// referenced...
		assertEquals(s1.getSignatureName(), "Sig2");
		assertEquals(s1.getSheetName(), "Sheet2_2");
		final JDFSurface su = s1.getCreateBackSurface();
		assertEquals(su.getSignatureName(), "Sig2");
		assertEquals(su.getSheetName(), "Sheet2_2");
		assertEquals(s1.getSurfaceVector().elementAt(0), su);

	}

	// ///////////////////////////////////////////////////
	/**
	 *
	 */
	@Test
	public void testGetSignatureName_Old()
	{
		testBuildOldLayout();
		final JDFLayout lo = (JDFLayout) n.getMatchingResource(ElementName.LAYOUT, EnumProcessUsage.AnyInput, null, 0);
		final JDFSignature sig = lo.getSignature(0);
		assertEquals(sig.getSignatureName(), sig.getName());
		assertEquals(sig.getSignatureName(), "Sig1");
		final JDFSignature sig2 = lo.getSignature(1);
		assertEquals(sig2.getSignatureName(), sig2.getName());
		assertEquals(sig2.getSignatureName(), "Sig2");
		final JDFSheet s1 = sig2.getSheet(1); // don't try 0 it will fail because it
		// is referenced...
		assertEquals(s1.getSignatureName(), "Sig2");
		assertEquals(s1.getSheetName(), "Sheet2_2");
		final JDFSurface su = s1.getCreateBackSurface();
		assertEquals(su.getSignatureName(), "Sig2");
		assertEquals(su.getSheetName(), "Sheet2_2");
		su.makeRootResource(null, null, true);
		assertEquals(su.getSignatureName(), "Sig2");
		assertEquals(su.getSheetName(), "Sheet2_2");
		s1.makeRootResource(null, null, true);
		assertEquals(su.getSignatureName(), "Sig2");
		assertEquals(su.getSheetName(), "Sheet2_2");

	}

	// ///////////////////////////////////////////////////
	/**
	 *
	 */
	@Test
	public void testGetSignatureByName()
	{
		for (int i = 0; i < 2; i++)
		{
			if (i == 0)
			{
				testBuildNewLayout();
			}
			else
			{
				testBuildOldLayout();
				final JDFLayout lo = (JDFLayout) n.getMatchingResource(ElementName.LAYOUT, EnumProcessUsage.AnyInput, null, 0);
				lo.getSignature(0).setName("SignatureName1");
				lo.getSignature(1).setName("SignatureName2");
				lo.getSignature(1).getSheet(0).setName("SheetName1");
			}
			final JDFLayout lo = (JDFLayout) n.getMatchingResource(ElementName.LAYOUT, EnumProcessUsage.AnyInput, null, 0);
			assertNull(lo.getSignature("fooBar"));
			assertEquals(lo.getSignature("SignatureName1"), lo.getSignature(0));
			assertEquals("loop " + i, lo.getSheet("SheetName1"), lo.getSheet(0));
			final JDFSignature signature2 = lo.getSignature("SignatureName2");
			assertEquals(signature2.getSheet("SheetName1"), lo.getSignature(1).getSheet(0));
			assertEquals(lo.getCreateSignature("fooBar"), lo.getSignature(-1));
		}

	}

	// ///////////////////////////////////////////////////
	/**
	 *
	 */
	@Test
	public void testGetSignatureName_New()
	{
		testBuildNewLayout();
		final JDFLayout lo = (JDFLayout) n.getMatchingResource(ElementName.LAYOUT, EnumProcessUsage.AnyInput, null, 0);
		final JDFSignature sig = lo.getSignature(0);
		assertEquals(sig.getSignatureName(), "SignatureName1");
		final JDFSignature sig2 = lo.getSignature(1);
		assertEquals(sig2.getSignatureName(), "SignatureName2");
		final JDFSheet s1 = sig2.getSheet(1); // don't try 0 it will fail because it
		// is referenced...
		assertEquals(s1.getSignatureName(), "SignatureName2");
		assertEquals(s1.getSheetName(), "SheetName2");
		final JDFSurface su = s1.getCreateBackSurface();
		assertEquals(su.getSignatureName(), "SignatureName2");
		assertEquals(su.getSheetName(), "SheetName2");
	}

	// ///////////////////////////////////////////////////
	/**
	 *
	 */
	@Test
	public void testGetSignatureVector_New()
	{
		testBuildNewLayout();
		final JDFLayout lo = (JDFLayout) n.getMatchingResource(ElementName.LAYOUT, EnumProcessUsage.AnyInput, null, 0);
		final VElement v = lo.getSignatureVector();
		final JDFSignature sig = (JDFSignature) v.elementAt(0);
		assertEquals(sig.getSignatureName(), "SignatureName1");
		final JDFSignature sig2 = (JDFSignature) v.elementAt(1);
		assertEquals(sig2.getSignatureName(), "SignatureName2");
		final VElement vSheet = sig2.getSheetVector();
		final JDFSheet s1 = (JDFSheet) vSheet.elementAt(1); // don't try 0 it will
		// fail because it is
		// referenced...
		assertEquals(s1.getSignatureName(), "SignatureName2");
		assertEquals(s1.getSheetName(), "SheetName2");
		final JDFSurface su = s1.getCreateBackSurface();
		assertEquals(su.getSignatureName(), "SignatureName2");
		assertEquals(su.getSheetName(), "SheetName2");
		assertEquals(s1.getSurfaceVector().elementAt(0), su);
	}

	// ///////////////////////////////////////////////////

	/**
	 *
	 */
	@Test
	public void testFixVersionProblem()
	{
		final JDFParser p = new JDFParser();
		final JDFDoc d = p.parseFile(sm_dirTestData + File.separator + "FixVersionProblem.jdf");
		assertNotNull("FixVersionProblem exists", d);
		n = d.getJDFRoot();
		n.fixVersion(EnumVersion.Version_1_2);
		final JDFLayout lo = (JDFLayout) n.getResourcePool().getElement(ElementName.LAYOUT, null, 0);
		assertEquals(lo.numChildElements("Signature", null), 1);
	}

	// ///////////////////////////////////////////////////

	/**
	 * GeneratedObject
	 *
	 * CTM or Position Position: See ImageShift PositionX and PositionY, Shift (Margins) --> See ShiftFront RelativeShift?
	 *
	 * Anchor Point (same as position ll, ul, cc, spine�) (if CTM is given) Orientation (rotation, matrix or ll, ul, ...) Contents Format/Template JobField (Replace, DynamicField?) SeparationList Mark
	 * References (FoldMark, CIE, ...)
	 * 
	 * @throws Exception
	 */
	@Test
	public void testGeneratedObject() throws Exception
	{
		n = doc.getJDFRoot();
		final JDFLayout lo = (JDFLayout) n.addResource("Layout", null, EnumUsage.Input, null, null, null, null);
		final JDFRunList rlo = (JDFRunList) n.addResource("RunList", null, EnumUsage.Output, null, null, null, null);
		rlo.setFileURL("output.pdf");

		lo.appendXMLComment(
				"This is a simple horizontal slug line\nAnchor specifies which of the 9 coordinates is the 0 point for the coordinate system specified in the CTM\nThis slugline describes error and endtime in the lower left corner of the scb",
				null);
		JDFMarkObject mark = lo.appendMarkObject();
		mark.setCTM(new JDFMatrix("1 0 0 1 0 0"));
		JDFJobField jf = mark.appendJobField();
		jf.setShowList(new VString("Error EndTime", " "));

		lo.appendXMLComment(
				"This is a simple vertical slug line\nAnchor specifies which of the 9 coordinates is the 0 point for the coordinate system specified in the CTM\nThis slugline describes the operator name along the right side of the sheet text from top to bottom\nthe slug line (top right of the slug cs) is anchored in the bottom right of the sheet.\nNote that the coordinates in the ctm are guess work. the real coordinates are left as an exercise for the reader;-)",
				null);
		mark = lo.appendMarkObject();
		mark.setCTM(new JDFMatrix("0 1 -1 0 555 444"));
		jf = mark.appendJobField();
		jf.setShowList(new VString("Operator", " "));
		JDFDeviceMark dm = jf.appendDeviceMark();
		dm.setAttribute("Anchor", "TopRight");
		dm.setFont("Arial");
		dm.setFontSize(10);

		lo.appendXMLComment(
				"This is a formatted vertical slug line\nAnchor specifies which of the 9 coordinates is the 0 point for the coordinate system specified in the CTM\nThis slugline describes a formatted line along the left side of the sheet text from top to bottom\nthe slug line (top left) is anchored in the bottom left of the sheet.\nThe text is defined in @Format with the sequence in ShowList defining the 5 placeholders marked by %s or %i\nAn example instance would be: \"This Cyan plate of Sheet1 was proudly produced by Joe Cool! Resolution: 600 x 600\"\nNote that the coordinates in the ctm are guess work. the real coordinates are left as an exercise for the reader;-)",
				null);
		mark = lo.appendMarkObject();
		mark.setCTM(new JDFMatrix("0 1 -1 0 0 0"));
		jf = mark.appendJobField();
		jf.setShowList(new VString("Separation SheetName Operator ResolutionX ResolutionY", " "));
		jf.setAttribute("Format", "This %s plate of %s was proudly produced by %s!\nResolution: %i x %i");
		dm = jf.appendDeviceMark();
		dm.setAttribute("Anchor", "TopLeft");
		dm.setFont("Arial");
		dm.setFontSize(10);

		lo.appendXMLComment("This is a positioned registermark\nthe center of the mark is translated by 666 999\n the JobField is empty and serves aa a Marker that no external Content is requested",
				null);
		mark = lo.appendMarkObject();
		mark.setCTM(new JDFMatrix("1 0 0 1 666 999"));
		jf = mark.appendJobField();
		mark.appendXMLComment("The coordinate system origin is defined by the anchor in the center, i.e. 0 0\n the separartions are excluding black", null);
		final JDFRegisterMark rm = mark.appendRegisterMark();
		rm.setMarkType(new VString("Arc Cross", null));
		rm.setMarkUsage(EnumMarkUsage.Color);
		rm.setCenter(new JDFXYPair("0 0"));
		rm.setSeparations(new VString("Cyan Magent Yellow", " "));
		dm = jf.appendDeviceMark();
		dm.setAttribute("Anchor", "Center");

		doc.write2File(sm_dirTestDataTemp + "generatedObject.jdf", 2, false);

	}
	// ///////////////////////////////////////////////////

}