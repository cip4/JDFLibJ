/**
 * The CIP4 Software License, Version 1.0
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
package org.cip4.jdflib.extensions.examples;

import java.util.Vector;

import org.cip4.jdflib.JDFTestCaseBase;
import org.cip4.jdflib.auto.JDFAutoAssembly.EnumOrder;
import org.cip4.jdflib.auto.JDFAutoBinderySignature.EnumBinderySignatureType;
import org.cip4.jdflib.auto.JDFAutoBundleItem.EnumOrientation;
import org.cip4.jdflib.auto.JDFAutoIdentificationField.EnumPosition;
import org.cip4.jdflib.auto.JDFAutoMedia.EnumMediaType;
import org.cip4.jdflib.auto.JDFAutoRefAnchor.EnumAnchor;
import org.cip4.jdflib.auto.JDFAutoRefAnchor.EnumAnchorType;
import org.cip4.jdflib.auto.JDFAutoStrippingParams.EnumWorkStyle;
import org.cip4.jdflib.core.AttributeName;
import org.cip4.jdflib.core.ElementName;
import org.cip4.jdflib.core.JDFElement;
import org.cip4.jdflib.core.JDFElement.EnumValidationLevel;
import org.cip4.jdflib.core.JDFElement.EnumVersion;
import org.cip4.jdflib.core.JDFResourceLink.EnumUsage;
import org.cip4.jdflib.core.KElement;
import org.cip4.jdflib.datatypes.JDFAttributeMap;
import org.cip4.jdflib.datatypes.JDFMatrix;
import org.cip4.jdflib.datatypes.JDFRectangle;
import org.cip4.jdflib.datatypes.JDFXYPair;
import org.cip4.jdflib.extensions.ResourceHelper;
import org.cip4.jdflib.extensions.SetHelper;
import org.cip4.jdflib.extensions.XJDFConstants;
import org.cip4.jdflib.extensions.XJDFHelper;
import org.cip4.jdflib.jmf.JMFBuilderFactory;
import org.cip4.jdflib.resource.JDFJobField;
import org.cip4.jdflib.resource.JDFPageCondition;
import org.cip4.jdflib.resource.JDFPart;
import org.cip4.jdflib.resource.JDFRefAnchor;
import org.cip4.jdflib.resource.JDFResource.EnumPartIDKey;
import org.cip4.jdflib.resource.process.JDFAssembly;
import org.cip4.jdflib.resource.process.JDFBinderySignature;
import org.cip4.jdflib.resource.process.JDFIdentificationField;
import org.cip4.jdflib.resource.process.JDFLayout;
import org.cip4.jdflib.resource.process.JDFMedia;
import org.cip4.jdflib.resource.process.JDFPosition;
import org.cip4.jdflib.resource.process.JDFRegisterMark;
import org.cip4.jdflib.resource.process.JDFStripMark;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**
 *
 * @author rainer prosi
 *
 */
public class XJDFLayoutTest extends JDFTestCaseBase
{
	/**
	 *
	 */
	@Test
	void testIDPSimplex()
	{
		final XJDFHelper xjdfHelper = new XJDFHelper(ElementName.LAYOUT, "Simplex", null);
		xjdfHelper.setTypes("Stripping");
		final SetHelper shLO = xjdfHelper.getCreateSet(XJDFConstants.Resource, ElementName.LAYOUT, EnumUsage.Input);
		final ResourceHelper rh = shLO.appendPartition(null, true);
		final JDFLayout lo = (JDFLayout) rh.getResource();
		xjdfHelper.cleanUp();
		setSnippet(lo, true);
		lo.setAttribute(AttributeName.WORKSTYLE, EnumWorkStyle.Simplex.getName());
		lo.setAutomated(true);
		lo.appendElement(ElementName.POSITION);
		writeTest(xjdfHelper, "processes/LayoutSimplex.xjdf");
	}

	/**
	 *
	 */
	@Test
	void testIDPBooklet()
	{
		final XJDFHelper xjdfHelper = new XJDFHelper(ElementName.LAYOUT, "Booklet", null);
		xjdfHelper.setTypes("Stripping");
		final SetHelper shLO = xjdfHelper.getCreateSet(XJDFConstants.Resource, ElementName.LAYOUT, EnumUsage.Input);
		final ResourceHelper rh = shLO.appendPartition(null, true);
		final JDFLayout lo = (JDFLayout) rh.getResource();

		lo.setAttribute(AttributeName.WORKSTYLE, EnumWorkStyle.Perfecting.getName());
		lo.setAutomated(true);
		lo.appendElement(ElementName.POSITION);

		final SetHelper shBS = xjdfHelper.getCreateSet(XJDFConstants.Resource, ElementName.BINDERYSIGNATURE, EnumUsage.Input);
		final ResourceHelper rhBS = shBS.appendPartition(null, true);
		final JDFBinderySignature bs = (JDFBinderySignature) rhBS.getResource();
		bs.setFoldCatalog("F4-1");
		bs.setBinderySignatureType(EnumBinderySignatureType.Fold);

		final SetHelper shAss = xjdfHelper.getCreateSet(XJDFConstants.Resource, ElementName.ASSEMBLY, EnumUsage.Input);
		final ResourceHelper rhAss = shAss.appendPartition(null, true);
		final JDFAssembly ass = (JDFAssembly) rhAss.getResource();
		ass.setOrder(EnumOrder.Collecting);
		xjdfHelper.cleanUp();

		cleanSnippets(xjdfHelper);
		writeTest(xjdfHelper, "processes/LayoutBooklet.xjdf");
	}

	/**
	 *
	 */
	@Test
	void testSheetActivation()
	{
		final XJDFHelper xjdfHelper = new XJDFHelper(ElementName.LAYOUT, "SheetActivation", null);
		xjdfHelper.setTypes("Stripping");
		final SetHelper shLO = xjdfHelper.getCreateSet(XJDFConstants.Resource, ElementName.LAYOUT, EnumUsage.Input);
		final ResourceHelper rh1 = shLO.appendPartition(new JDFAttributeMap(AttributeName.SHEETNAME, "FrontInsert"), true);
		final ResourceHelper rh2 = shLO.appendPartition(new JDFAttributeMap(AttributeName.SHEETNAME, "Cover"), true);
		final ResourceHelper rh3 = shLO.appendPartition(new JDFAttributeMap(AttributeName.SHEETNAME, "Body"), true);
		final ResourceHelper rh4 = shLO.appendPartition(new JDFAttributeMap(AttributeName.SHEETNAME, "JobSheet"), true);
		final ResourceHelper rh5 = shLO.appendPartition(new JDFAttributeMap(AttributeName.SHEETNAME, "SetSheet"), true);
		final Vector<JDFLayout> vLO = new Vector<>();

		final JDFLayout lo1 = (JDFLayout) rh1.getResource();
		final KElement sc1 = lo1.appendElement("SheetActivation");
		final JDFElement c1 = (JDFElement) sc1.appendElement(AttributeName.CONDITION);
		final JDFPart p1 = (JDFPart) c1.appendElement(ElementName.PART);
		p1.setPartMap(new JDFAttributeMap(EnumPartIDKey.RunIndex, "0 0"));
		rh1.setAttribute(AttributeName.DESCRIPTIVENAME, "Start Job Sheet");
		vLO.add(lo1);

		final JDFLayout lo2 = (JDFLayout) rh2.getResource();
		vLO.add(lo2);
		final KElement po2 = lo2.appendElement(XJDFConstants.PlacedObject);
		po2.appendElement(ElementName.CONTENTOBJECT);
		po2.setAttribute(AttributeName.ORD, "0");
		po2.setAttribute(AttributeName.CTM, "1 0 0 1 0 0");
		final JDFPart p = (JDFPart) po2.getCreateXPathElement("PageActivation/Condition/Part");
		p.setPartMap(new JDFAttributeMap("RunIndex", "0 0"));
		p.getParentNode_KElement().setAttribute("PartContext", "DocIndex");
		final JDFLayout lo3 = (JDFLayout) rh3.getResource();
		vLO.add(lo3);
		final KElement po3 = lo3.appendElement(XJDFConstants.PlacedObject);
		po3.appendElement(ElementName.CONTENTOBJECT);
		po3.setAttribute(AttributeName.ORD, "0");
		po3.setAttribute(AttributeName.CTM, "1 0 0 1 0 0");
		final JDFPart p3 = (JDFPart) po3.getCreateXPathElement("PageCondition/Condition/Part");
		p3.setPartMap(new JDFAttributeMap("RunIndex", "0 0"));
		p3.getParentNode_KElement().setAttribute("PartContext", "DocIndex");

		final JDFLayout lo4 = (JDFLayout) rh4.getResource();
		vLO.add(lo4);
		final JDFPart p4 = (JDFPart) lo4.getCreateXPathElement("SheetActivation/Condition/Part");
		final JDFAttributeMap mPart4 = new JDFAttributeMap("RunIndex", "-1 -1");
		p4.setPartMap(mPart4);
		p4.getParentNode_KElement().setAttribute("PartContext", "DocIndex");

		final JDFLayout lo5 = (JDFLayout) rh5.getResource();
		vLO.add(lo5);
		final JDFPart p5 = (JDFPart) lo5.getCreateXPathElement("SheetActivation/Condition/Part");
		final JDFAttributeMap mPart5 = new JDFAttributeMap("RunIndex", "-1 -1");
		mPart5.put("DocIndex", "-1 -1");
		p5.setPartMap(mPart5);

		for (final JDFLayout lo : vLO)
		{
			lo.setAttribute(AttributeName.WORKSTYLE, EnumWorkStyle.Simplex.getName());
			lo.setAutomated(true);
			final ResourceHelper helper = ResourceHelper.getHelper(lo);
			final JDFAttributeMap partMap = helper.getPartMap();
			partMap.put(EnumPartIDKey.Side, "Front");
			helper.setPartMap(partMap);
		}

		cleanSnippets(xjdfHelper);
		writeTest(xjdfHelper, "../insertsheet.xjdf");
	}

	/**
	 *
	 */
	@Test
	void testIDPCalendar()
	{
		final XJDFHelper xjdfHelper = new XJDFHelper(ElementName.LAYOUT, "Simplex", null);
		xjdfHelper.setTypes("Stripping");
		final SetHelper shLO = xjdfHelper.getCreateSet(XJDFConstants.Resource, ElementName.LAYOUT, EnumUsage.Input);
		final ResourceHelper rh = shLO.appendPartition(null, true);
		final JDFLayout lo = (JDFLayout) rh.getResource();
		xjdfHelper.cleanUp();
		setSnippet(lo, true);
		lo.setAttribute(AttributeName.WORKSTYLE, EnumWorkStyle.WorkAndTumble.getName());
		lo.setAutomated(true);
		lo.appendElement(ElementName.POSITION);
		writeTest(xjdfHelper, "processes/LayoutDuplexTopBind.xjdf");
	}

	/**
	 *
	 */
	@Test
	void testTiling()
	{
		final XJDFHelper xjdfHelper = new XJDFHelper(ElementName.LAYOUT, "Tiling", null);
		xjdfHelper.setTypes("Imposition");
		final SetHelper shLO = xjdfHelper.getCreateSet(XJDFConstants.Resource, ElementName.LAYOUT, EnumUsage.Input);
		final ResourceHelper rh = shLO.appendPartition(AttributeName.TILEID, "0 0", true);
		rh.ensurePart(AttributeName.SIDE, "Front");
		final JDFLayout lo = (JDFLayout) rh.getResource();
		lo.setSurfaceContentsBox(new JDFRectangle(0, 0, 1820, 1220));
		final KElement po = lo.appendElement(XJDFConstants.PlacedObject);
		po.setAttribute("Ord", "0");
		po.setAttribute(AttributeName.CLIPBOX, new JDFRectangle(0, 0, 620, 420).toString());
		po.setAttribute("CTM", JDFMatrix.getUnitMatrix().toString());
		po.setAttribute(AttributeName.TRIMSIZE, new JDFXYPair(600, 400).toString());
		po.setAttribute(AttributeName.TRIMCTM, JDFMatrix.getUnitMatrix().shift(-10, -10).toString());
		po.appendElement(ElementName.CONTENTOBJECT);
		final ResourceHelper rh2 = shLO.appendPartition(AttributeName.TILEID, "2 2", true);
		rh2.ensurePart(AttributeName.SIDE, "Front");
		final JDFLayout lo2 = (JDFLayout) rh2.getResource();
		lo2.setAttributes(lo);
		final KElement po2 = lo2.copyElement(po, null);
		po2.setAttribute(AttributeName.TRIMCTM, JDFMatrix.getUnitMatrix().shift(-10, -10).shift(-1200, -800).toString());
		po2.setAttribute("CTM", JDFMatrix.getUnitMatrix().shift(-1200, -800).toString());
		xjdfHelper.cleanUp();
		shLO.getRoot().appendXMLComment("7 further tiles '0 1' to '2 1' omitted for brevity", rh2.getRoot());
		setSnippet(shLO, true);
		writeTest(xjdfHelper, "processes/impositionForTiling.xjdf");
	}

	/**
	 *
	 */
	@Test
	void testPageCondition()
	{
		final XJDFHelper xjdfHelper = new XJDFHelper(ElementName.LAYOUT, "PageCondition", null);
		xjdfHelper.setTypes("Imposition");
		final SetHelper shLO = xjdfHelper.getCreateSet(XJDFConstants.Resource, ElementName.LAYOUT, EnumUsage.Input);
		final ResourceHelper rh = shLO.appendPartition(AttributeName.SIDE, "Front", true);

		final JDFLayout lo = (JDFLayout) rh.getResource();
		lo.setAutomated(true);
		KElement po = lo.appendElement(XJDFConstants.PlacedObject);
		po.setAttribute("Ord", "0");
		po.setAttribute(AttributeName.CTM, JDFMatrix.getUnitMatrix().toString());
		po.appendElement(ElementName.CONTENTOBJECT);
		po.setID("po_0");

		po = lo.appendElement(XJDFConstants.PlacedObject);
		po.setAttribute("Ord", "1");
		po.setAttribute(AttributeName.CTM, JDFMatrix.getUnitMatrix().shift(new JDFXYPair(500, 0)).toString());
		po.setID("po_1");
		po.appendElement(ElementName.CONTENTOBJECT);

		final JDFPageCondition pc = (JDFPageCondition) po.appendElement(ElementName.PAGECONDITION);
		final JDFAttributeMap mPart = new JDFAttributeMap(AttributeName.RUNINDEX, "0 0");

		pc.setPartMap(mPart);
		final KElement cond = pc.appendElement(AttributeName.CONDITION);
		cond.moveArray(pc.getChildElementVector(ElementName.PART, null), null);
		cond.setAttribute(XJDFConstants.PartContext, AttributeName.DOCINDEX);
		xjdfHelper.cleanUp();
		setSnippet(shLO, true);
		writeTest(xjdfHelper, "resources/pageCondition.xjdf");
	}

	/**
	 *
	 */
	@Test
	void testRegisterMark()
	{
		final XJDFHelper xjdfHelper = new XJDFHelper(ElementName.LAYOUT, "PageCondition", null);
		xjdfHelper.setTypes("Imposition");
		final SetHelper shLO = xjdfHelper.getCreateSet(XJDFConstants.Resource, ElementName.LAYOUT, EnumUsage.Input);
		final ResourceHelper rh = shLO.appendPartition(AttributeName.SIDE, "Front", true);

		final JDFLayout lo = (JDFLayout) rh.getResource();
		final KElement po = lo.appendElement(XJDFConstants.PlacedObject);
		po.setAttribute("Ord", "0");
		po.setAttribute(AttributeName.CTM, JDFMatrix.getUnitMatrix().toString());
		final JDFRegisterMark rm = (JDFRegisterMark) po.appendElement(ElementName.MARKOBJECT).appendElement(ElementName.REGISTERMARK);
		rm.setCenter((JDFXYPair) new JDFXYPair(10, 250).scaleFromMM());
		rm.setAttribute(AttributeName.SIZE, new JDFXYPair(10, 10).scaleFromMM(), 2);

		xjdfHelper.cleanUp();
		setSnippet(shLO, true);
		writeRoundTripX(xjdfHelper, "registerMark", EnumValidationLevel.Incomplete);
	}

	/**
	 *
	 */
	// @Test
	void testIdentificationField()
	{
		final XJDFHelper xjdfHelper = new XJDFHelper(ElementName.LAYOUT, "PageCondition", null);
		xjdfHelper.setTypes("Imposition");
		final SetHelper shLO = xjdfHelper.getCreateSet(XJDFConstants.Resource, ElementName.LAYOUT, EnumUsage.Input);
		final ResourceHelper rh = shLO.appendPartition(AttributeName.SIDE, "Front", true);

		final JDFLayout lo = (JDFLayout) rh.getResource();
		final KElement po = lo.appendElement(XJDFConstants.PlacedObject);
		po.setAttribute("Ord", "0");
		po.setAttribute(AttributeName.CTM, JDFMatrix.getUnitMatrix().toString());
		final JDFIdentificationField idf = (JDFIdentificationField) po.appendElement(ElementName.MARKOBJECT).appendElement(ElementName.IDENTIFICATIONFIELD);
		idf.setPosition(EnumPosition.Back);

		xjdfHelper.cleanUp();
		setSnippet(shLO, true);
		writeRoundTripX(xjdfHelper, "identificationfield", EnumValidationLevel.Incomplete);
	}

	/**
	 *
	 */
	@Test
	void testContentRef()
	{
		final XJDFHelper xjdfHelper = new XJDFHelper(ElementName.LAYOUT, "ContentRef", null);
		xjdfHelper.setTypes("Imposition");
		final SetHelper shLO = xjdfHelper.getCreateSet(XJDFConstants.Resource, ElementName.LAYOUT, EnumUsage.Input);
		final ResourceHelper rh = shLO.appendPartition(AttributeName.SIDE, "Front", true);

		final JDFLayout lo = (JDFLayout) rh.getResource();
		lo.setAutomated(true);
		KElement po = lo.appendElement(XJDFConstants.PlacedObject);
		po.setAttribute("Ord", "0");
		po.setAttribute(AttributeName.CTM, JDFMatrix.getUnitMatrix().toString());
		po.appendElement(ElementName.CONTENTOBJECT);
		po.setID("po_0");

		po = lo.appendElement(XJDFConstants.PlacedObject);
		po.setAttribute("Ord", "1");
		po.setAttribute(AttributeName.CTM, JDFMatrix.getUnitMatrix().shift(new JDFXYPair(500, 0)).toString());
		po.setID("po_1");
		po.appendElement(ElementName.CONTENTOBJECT);

		final JDFPageCondition pc = (JDFPageCondition) po.appendElement(ElementName.PAGECONDITION);
		final JDFAttributeMap mPart = new JDFAttributeMap(AttributeName.RUNINDEX, "0 0");

		pc.setPartMap(mPart);
		final KElement cond = pc.appendElement(AttributeName.CONDITION);
		cond.moveArray(pc.getChildElementVector(ElementName.PART, null), null);
		cond.setAttribute(XJDFConstants.PartContext, AttributeName.DOCINDEX);

		po = lo.appendElement(XJDFConstants.PlacedObject);
		po.setAttribute("Ord", "0");
		po.setAttribute(AttributeName.CTM, JDFMatrix.getUnitMatrix().shift(new JDFXYPair(0, 500)).toString());
		final KElement mo1 = po.appendElement(ElementName.MARKOBJECT);
		mo1.appendElement(ElementName.REGISTERMARK);

		po = lo.appendElement(XJDFConstants.PlacedObject);
		po.setAttribute("Ord", "1");
		po.setAttribute(AttributeName.CTM, JDFMatrix.getUnitMatrix().shift(new JDFXYPair(500, 500)).toString());
		final KElement mo2 = po.appendElement(ElementName.MARKOBJECT);
		mo2.setAttribute(XJDFConstants.ContentRef, "po_1");
		mo2.appendElement(ElementName.REGISTERMARK);

		xjdfHelper.cleanUp();
		setSnippet(shLO, true);
		writeTest(xjdfHelper, "resources/contentRef.xjdf");
	}

	/**
	 *
	 */
	@Test
	void testDynamicField()
	{
		final XJDFHelper xjdfHelper = new XJDFHelper(ElementName.LAYOUT, "DynamicField", null);
		xjdfHelper.setTypes("Imposition");
		final SetHelper shLO = xjdfHelper.getCreateSet(XJDFConstants.Resource, ElementName.LAYOUT, EnumUsage.Input);
		final ResourceHelper rh = shLO.appendPartition(AttributeName.SIDE, "Front", true);
		rh.setID("ID_Lo");
		final JDFLayout lo = (JDFLayout) rh.getResource();
		lo.setAutomated(true);
		final JDFStripMark sm = (JDFStripMark) lo.appendElement(ElementName.STRIPMARK);
		sm.setAttribute(AttributeName.ABSOLUTEHEIGHT, "99");
		sm.setAttribute(AttributeName.ABSOLUTEWIDTH, "999");
		final JDFRefAnchor ra = (JDFRefAnchor) sm.appendElement(ElementName.REFANCHOR);
		ra.setrRef(rh.ensureID());
		ra.setAnchorType(EnumAnchorType.Parent);
		ra.setAnchor(EnumAnchor.TopLeft);
		final JDFJobField df = (JDFJobField) sm.appendElement(ElementName.JOBFIELD);
		sm.copyAttribute(AttributeName.ANCHOR, ra);
		df.setJobFormat("Moon phase while imaging: %s sheet # %i");
		df.setJobTemplate("MoonPhase SheetIndex");
		xjdfHelper.cleanUp();
		setSnippet(shLO, true);
		writeTest(xjdfHelper, "resources/dynamicField.xjdf");
	}

	/**
	 *
	 */
	@Test
	void testCutStack()
	{
		final XJDFHelper xjdfHelper = new XJDFHelper(ElementName.LAYOUT, "CutStack", null);
		xjdfHelper.setTypes("Stripping");
		final SetHelper shLO = xjdfHelper.getCreateSet(XJDFConstants.Resource, ElementName.LAYOUT, EnumUsage.Input);
		final ResourceHelper rh = shLO.appendPartition(null, true);
		final JDFLayout lo = (JDFLayout) rh.getResource();

		xjdfHelper.cleanUp();
		setSnippet(lo, true);

		lo.setAttribute(AttributeName.WORKSTYLE, EnumWorkStyle.WorkAndTurn.getName());
		lo.setAutomated(true);
		JDFPosition pos = (JDFPosition) lo.appendElement(ElementName.POSITION);
		pos.setAttribute("StackDepth", "10");
		pos.setAttribute("StackOrd", "0");
		pos.setRelativeBox(new JDFRectangle(0, 0, 50, 100));

		pos = (JDFPosition) lo.appendElement(ElementName.POSITION);
		pos.setAttribute("StackDepth", "10");
		pos.setAttribute("StackOrd", "1");
		pos.setRelativeBox(new JDFRectangle(50, 0, 100, 100));
		writeTest(xjdfHelper, "processes/layoutCutStack.xjdf");
	}

	/**
	 *
	 */
	@Test
	void testStrippingF16()
	{
		final XJDFHelper xjdfHelper = new XJDFHelper(ElementName.LAYOUT, "3F-16", null);
		xjdfHelper.setTypes("Stripping");
		final SetHelper shBS = xjdfHelper.getCreateSet(XJDFConstants.Resource, ElementName.BINDERYSIGNATURE, EnumUsage.Input);
		final ResourceHelper rhBS = shBS.appendPartition(null, true);
		final JDFBinderySignature bs = (JDFBinderySignature) rhBS.getResource();
		bs.setFoldCatalog("F16-6");
		bs.setBinderySignatureType(EnumBinderySignatureType.Fold);

		final SetHelper shLO = xjdfHelper.getCreateSet(XJDFConstants.Resource, ElementName.LAYOUT, EnumUsage.Input);
		for (int i = 1; i <= 3; i++)
		{
			rhBS.appendPartMap(new JDFAttributeMap(XJDFConstants.BinderySignatureID, "bs" + i));
			final ResourceHelper rh = shLO.appendPartition(AttributeName.SHEETNAME, "sheet" + i, true);
			final JDFLayout lo = (JDFLayout) rh.getResource();
			lo.setAttribute(AttributeName.WORKSTYLE, EnumWorkStyle.WorkAndBack.getName());
			final KElement pos = lo.appendElement(ElementName.POSITION);
			pos.setAttribute(XJDFConstants.BinderySignatureID, "bs" + i);
		}
		final SetHelper shAss = xjdfHelper.getCreateSet(XJDFConstants.Resource, ElementName.ASSEMBLY, EnumUsage.Input);
		final ResourceHelper rhAss = shAss.appendPartition(null, true);
		rhAss.getResource().setAttribute(XJDFConstants.BinderySignatureIDs, "bs1 bs2 bs3");
		((JDFAssembly) rhAss.getResource()).setOrder(EnumOrder.Collecting);
		cleanSnippets(xjdfHelper);
		writeTest(xjdfHelper, "processes/StrippingF16-6.xjdf");
	}

	/**
	 *
	 */
	@Test
	void testStrippingMedia()
	{
		final XJDFHelper xjdfHelper = new XJDFHelper(ElementName.LAYOUT, "3F-16", null);
		xjdfHelper.setTypes("Stripping");
		final SetHelper shBS = xjdfHelper.getCreateSet(XJDFConstants.Resource, ElementName.BINDERYSIGNATURE, EnumUsage.Input);
		final ResourceHelper rhBS = shBS.appendPartition(null, true);
		final JDFBinderySignature bs = (JDFBinderySignature) rhBS.getResource();
		bs.setFoldCatalog("F16-6");
		bs.setBinderySignatureType(EnumBinderySignatureType.Fold);

		final SetHelper shPap = xjdfHelper.getCreateSet(ElementName.MEDIA, null);
		final ResourceHelper rhPap = shPap.getCreatePartition(AttributeName.SHEETNAME, "sheet1", true);
		rhPap.setID("idPaper");
		final JDFMedia pap = (JDFMedia) rhPap.getResource();
		pap.setMediaType(EnumMediaType.Paper);

		final SetHelper shPlate = xjdfHelper.appendResourceSet(ElementName.MEDIA, null);
		final ResourceHelper rhPlate = shPlate.getCreatePartition(AttributeName.SHEETNAME, "sheet1", true);
		rhPlate.setID("idPlate");
		final JDFMedia plate = (JDFMedia) rhPlate.getResource();
		plate.setMediaType(EnumMediaType.Plate);

		final SetHelper shLO = xjdfHelper.getCreateSet(XJDFConstants.Resource, ElementName.LAYOUT, EnumUsage.Input);
		rhBS.appendPartMap(new JDFAttributeMap(XJDFConstants.BinderySignatureID, "bs1"));
		final ResourceHelper rh = shLO.appendPartition(AttributeName.SHEETNAME, "sheet1", true);
		final JDFLayout lo = (JDFLayout) rh.getResource();
		lo.setAttribute(AttributeName.WORKSTYLE, EnumWorkStyle.WorkAndBack.getName());
		final KElement pos = lo.appendElement(ElementName.POSITION);
		pos.setAttribute(XJDFConstants.BinderySignatureID, "bs1");

		lo.setAttribute(XJDFConstants.PaperRef, "idPaper");
		lo.setAttribute(XJDFConstants.PlateRef, "idPlate");

		final SetHelper shAss = xjdfHelper.getCreateSet(XJDFConstants.Resource, ElementName.ASSEMBLY, EnumUsage.Input);
		final ResourceHelper rhAss = shAss.appendPartition(null, true);
		rhAss.getResource().setAttribute(XJDFConstants.BinderySignatureIDs, "bs1");
		((JDFAssembly) rhAss.getResource()).setOrder(EnumOrder.Collecting);
		cleanSnippets(xjdfHelper);
		writeTest(xjdfHelper, "processes/StrippingMedia.xjdf");

	}

	/**
	 *
	 */
	@Test
	void testStrippingMultiPageFold()
	{
		final XJDFHelper xjdfHelper = new XJDFHelper(ElementName.LAYOUT, "MultiPage", null);
		xjdfHelper.setTypes("Stripping");
		xjdfHelper.setVersion(EnumVersion.Version_2_1);

		final SetHelper shBS = xjdfHelper.getCreateSet(XJDFConstants.Resource, ElementName.BINDERYSIGNATURE, EnumUsage.Input);
		final ResourceHelper rhBS1 = shBS.appendPartition(XJDFConstants.BinderySignatureID, "BS1", true);
		final JDFBinderySignature bs1 = (JDFBinderySignature) rhBS1.getResource();
		bs1.setFoldCatalog("F8-7");
		bs1.setBinderySignatureType(EnumBinderySignatureType.Fold);

		final KElement mp1 = bs1.appendElement(XJDFConstants.MultiPageFold);
		mp1.setAttribute(XJDFConstants.BinderySignatureID, "BS1");
		mp1.setAttribute(AttributeName.ORIENTATION, EnumOrientation.Rotate0.getName());
		final KElement mp2 = bs1.appendElement(XJDFConstants.MultiPageFold);
		mp2.setAttribute(XJDFConstants.BinderySignatureID, "BS2");
		mp2.setAttribute(AttributeName.ORIENTATION, EnumOrientation.Rotate0.getName());

		final ResourceHelper rhBS2 = shBS.appendPartition(XJDFConstants.BinderySignatureID, "BS2", true);
		final JDFBinderySignature bs2 = (JDFBinderySignature) rhBS2.getResource();
		bs2.setFoldCatalog("F8-7");
		bs2.setBinderySignatureType(EnumBinderySignatureType.Fold);
		bs2.copyElement(mp1, null);
		bs2.copyElement(mp2, null);

		final SetHelper shLO = xjdfHelper.getCreateSet(ElementName.LAYOUT, EnumUsage.Input);
		final ResourceHelper rhLO = shLO.appendPartition(AttributeName.SHEETNAME, "Sheet1", true);
		final JDFLayout lo = (JDFLayout) rhLO.getResource();
		final JDFPosition pos = (JDFPosition) lo.appendElement(ElementName.POSITION);
		pos.setAttribute(XJDFConstants.BinderySignatureID, "BS1");
		cleanSnippets(xjdfHelper);
		writeTest(xjdfHelper, "processes/MultiPageFold.xjdf");
	}

	/**
	 *
	 */
	@Test
	void testStrippingComeGo()
	{
		final XJDFHelper xjdfHelper = new XJDFHelper(ElementName.LAYOUT, "ComeGo", null);
		xjdfHelper.setTypes("Stripping");
		xjdfHelper.setVersion(EnumVersion.Version_2_1);

		final SetHelper shBS = xjdfHelper.getCreateSet(XJDFConstants.Resource, ElementName.BINDERYSIGNATURE, EnumUsage.Input);
		final ResourceHelper rhBS1 = shBS.appendPartition(XJDFConstants.BinderySignatureID, "BS1", true);
		final JDFBinderySignature bs1 = (JDFBinderySignature) rhBS1.getResource();
		bs1.setFoldCatalog("F8-7");
		bs1.setBinderySignatureType(EnumBinderySignatureType.Fold);

		final KElement mp1 = bs1.appendElement(XJDFConstants.MultiPageFold);
		mp1.setAttribute(XJDFConstants.BinderySignatureID, "BS1");
		mp1.setAttribute(AttributeName.ORIENTATION, EnumOrientation.Rotate0.getName());
		final KElement mp2 = bs1.appendElement(XJDFConstants.MultiPageFold);
		mp2.setAttribute(XJDFConstants.BinderySignatureID, "BS3");
		mp2.setAttribute(AttributeName.ORIENTATION, EnumOrientation.Flip0.getName());

		final ResourceHelper rhBS2 = shBS.appendPartition(XJDFConstants.BinderySignatureID, "BS3", true);
		final JDFBinderySignature bs2 = (JDFBinderySignature) rhBS2.getResource();
		bs2.setFoldCatalog("F8-7");
		bs2.setBinderySignatureType(EnumBinderySignatureType.Fold);
		bs2.copyElement(mp1, null);
		bs2.copyElement(mp2, null);

		final ResourceHelper rhBS3 = shBS.appendPartition(XJDFConstants.BinderySignatureID, "BS2", true);
		final JDFBinderySignature bs3 = (JDFBinderySignature) rhBS3.getResource();
		bs3.setFoldCatalog("F8-7");
		bs3.setBinderySignatureType(EnumBinderySignatureType.Fold);

		final KElement mp3 = bs3.appendElement(XJDFConstants.MultiPageFold);
		mp3.setAttribute(XJDFConstants.BinderySignatureID, "BS2");
		mp3.setAttribute(AttributeName.ORIENTATION, EnumOrientation.Rotate0.getName());
		final KElement mp3b = bs3.appendElement(XJDFConstants.MultiPageFold);
		mp3b.setAttribute(XJDFConstants.BinderySignatureID, "BS2");
		mp3b.setAttribute(AttributeName.ORIENTATION, EnumOrientation.Flip0.getName());

		final SetHelper shLO = xjdfHelper.getCreateSet(ElementName.LAYOUT, EnumUsage.Input);
		final ResourceHelper rhLO = shLO.appendPartition(AttributeName.SHEETNAME, "Sheet1", true);
		final JDFLayout lo = (JDFLayout) rhLO.getResource();
		final JDFPosition pos = (JDFPosition) lo.appendElement(ElementName.POSITION);
		pos.setAttribute(XJDFConstants.BinderySignatureID, "BS1");

		final ResourceHelper rhL2 = shLO.appendPartition(AttributeName.SHEETNAME, "Sheet2", true);
		final JDFLayout lo2 = (JDFLayout) rhL2.getResource();
		final JDFPosition pos2 = (JDFPosition) lo2.appendElement(ElementName.POSITION);
		pos2.setAttribute(XJDFConstants.BinderySignatureID, "BS2");

		cleanSnippets(xjdfHelper);
		writeTest(xjdfHelper, "processes/ComeGo.xjdf");
	}

	/**
	 * @see JDFTestCaseBase#setUp()
	 */
	@Override
	@BeforeEach
	void setUp() throws Exception
	{
		JMFBuilderFactory.getJMFBuilder(XJDFConstants.XJMF).setAgentName(null);
		JMFBuilderFactory.getJMFBuilder(XJDFConstants.XJMF).setAgentVersion(null);
		JMFBuilderFactory.getJMFBuilder(XJDFConstants.XJMF).setSenderID(null);
		KElement.setLongID(false);

		super.setUp();
	}

}
