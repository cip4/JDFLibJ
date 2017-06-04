/**
 * The CIP4 Software License, Version 1.0
 *
 * Copyright (c) 2001-2017 The International Cooperation for the Integration of
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
package org.cip4.jdflib.extensions.examples;

import org.cip4.jdflib.JDFTestCaseBase;
import org.cip4.jdflib.auto.JDFAutoAssembly.EnumOrder;
import org.cip4.jdflib.auto.JDFAutoBinderySignature.EnumBinderySignatureType;
import org.cip4.jdflib.auto.JDFAutoRefAnchor.EnumAnchor;
import org.cip4.jdflib.auto.JDFAutoRefAnchor.EnumAnchorType;
import org.cip4.jdflib.auto.JDFAutoStrippingParams.EnumWorkStyle;
import org.cip4.jdflib.core.AttributeName;
import org.cip4.jdflib.core.ElementName;
import org.cip4.jdflib.core.JDFResourceLink.EnumUsage;
import org.cip4.jdflib.core.KElement;
import org.cip4.jdflib.datatypes.JDFAttributeMap;
import org.cip4.jdflib.datatypes.JDFMatrix;
import org.cip4.jdflib.datatypes.JDFRectangle;
import org.cip4.jdflib.datatypes.JDFXYPair;
import org.cip4.jdflib.datatypes.VJDFAttributeMap;
import org.cip4.jdflib.extensions.ResourceHelper;
import org.cip4.jdflib.extensions.SetHelper;
import org.cip4.jdflib.extensions.XJDFConstants;
import org.cip4.jdflib.extensions.XJDFHelper;
import org.cip4.jdflib.resource.JDFJobField;
import org.cip4.jdflib.resource.JDFPageCondition;
import org.cip4.jdflib.resource.JDFRefAnchor;
import org.cip4.jdflib.resource.process.JDFAssembly;
import org.cip4.jdflib.resource.process.JDFBinderySignature;
import org.cip4.jdflib.resource.process.JDFLayout;
import org.cip4.jdflib.resource.process.JDFPosition;
import org.cip4.jdflib.resource.process.JDFStripMark;
import org.junit.Test;

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
	public void testIDPSimplex()
	{
		XJDFHelper xjdfHelper = new XJDFHelper(ElementName.LAYOUT, "Simplex", null);
		xjdfHelper.setTypes("Stripping");
		SetHelper shLO = xjdfHelper.getCreateResourceSet(ElementName.LAYOUT, EnumUsage.Input);
		ResourceHelper rh = shLO.appendPartition(null, true);
		JDFLayout lo = (JDFLayout) rh.getResource();
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
	public void testIDPBooklet()
	{
		XJDFHelper xjdfHelper = new XJDFHelper(ElementName.LAYOUT, "Simplex", null);
		xjdfHelper.setTypes("Stripping");
		SetHelper shLO = xjdfHelper.getCreateResourceSet(ElementName.LAYOUT, EnumUsage.Input);
		ResourceHelper rh = shLO.appendPartition(null, true);
		JDFLayout lo = (JDFLayout) rh.getResource();

		lo.setAttribute(AttributeName.WORKSTYLE, EnumWorkStyle.WorkAndTurn.getName());
		lo.setAutomated(true);
		lo.appendElement(ElementName.POSITION);

		SetHelper shBS = xjdfHelper.getCreateResourceSet(ElementName.BINDERYSIGNATURE, null);
		ResourceHelper rhBS = shBS.appendPartition(null, true);
		JDFBinderySignature bs = (JDFBinderySignature) rhBS.getResource();
		bs.setFoldCatalog("F4-1");

		SetHelper shAss = xjdfHelper.getCreateResourceSet(ElementName.ASSEMBLY, null);
		ResourceHelper rhAss = shAss.appendPartition(null, true);
		JDFAssembly ass = (JDFAssembly) rhAss.getResource();
		ass.setOrder(EnumOrder.Collecting);
		xjdfHelper.cleanUp();

		cleanSnippets(xjdfHelper);
		writeTest(xjdfHelper, "processes/IDPBooklet.xjdf");
	}

	/**
	 *
	 */
	@Test
	public void testIDPCalendar()
	{
		XJDFHelper xjdfHelper = new XJDFHelper(ElementName.LAYOUT, "Simplex", null);
		xjdfHelper.setTypes("Stripping");
		SetHelper shLO = xjdfHelper.getCreateResourceSet(ElementName.LAYOUT, EnumUsage.Input);
		ResourceHelper rh = shLO.appendPartition(null, true);
		JDFLayout lo = (JDFLayout) rh.getResource();
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
	public void testTiling()
	{
		XJDFHelper xjdfHelper = new XJDFHelper(ElementName.LAYOUT, "Tiling", null);
		xjdfHelper.setTypes("Imposition");
		SetHelper shLO = xjdfHelper.getCreateResourceSet(ElementName.LAYOUT, EnumUsage.Input);
		ResourceHelper rh = shLO.appendPartition(AttributeName.TILEID, "0 0", true);
		rh.ensurePart(AttributeName.SIDE, "Front");
		JDFLayout lo = (JDFLayout) rh.getResource();
		lo.setSurfaceContentsBox(new JDFRectangle(0, 0, 600, 420));
		KElement po = lo.appendElement(XJDFConstants.PlacedObject);
		po.setAttribute("Ord", "0");
		po.setAttribute(AttributeName.CLIPBOX, new JDFRectangle(0, 0, 600, 420).toString());
		po.setAttribute("CTM", JDFMatrix.getUnitMatrix().toString());
		po.appendElement(ElementName.CONTENTOBJECT);
		ResourceHelper rh2 = shLO.appendPartition(AttributeName.TILEID, "1 1", true);
		rh2.ensurePart(AttributeName.SIDE, "Front");
		JDFLayout lo2 = (JDFLayout) rh2.getResource();
		lo2.setSurfaceContentsBox(new JDFRectangle(0, 0, 600, 420));
		po = lo2.copyElement(po, null);
		po.setAttribute("CTM", JDFMatrix.getUnitMatrix().shift(-600, -420).toString());
		xjdfHelper.cleanUp();
		shLO.getRoot().appendXMLComment("More tiles here", rh2.getRoot());
		setSnippet(shLO, true);
		writeTest(xjdfHelper, "processes/impositionForTiling.xjdf");
	}

	/**
	 *
	 */
	@Test
	public void testPageCondition()
	{
		XJDFHelper xjdfHelper = new XJDFHelper(ElementName.LAYOUT, "PageCondition", null);
		xjdfHelper.setTypes("Imposition");
		SetHelper shLO = xjdfHelper.getCreateResourceSet(ElementName.LAYOUT, EnumUsage.Input);
		ResourceHelper rh = shLO.appendPartition(AttributeName.SIDE, "Front", true);

		JDFLayout lo = (JDFLayout) rh.getResource();
		lo.setAutomated(true);
		KElement po = lo.appendElement(XJDFConstants.PlacedObject);
		po.setAttribute("Ord", "0");
		po.setAttribute(AttributeName.CTM, JDFMatrix.getUnitMatrix().toString());
		po.appendElement(ElementName.CONTENTOBJECT);
		po = lo.appendElement(XJDFConstants.PlacedObject);
		po.setAttribute("Ord", "1");
		po.setAttribute(AttributeName.CTM, JDFMatrix.getUnitMatrix().shift(new JDFXYPair(500, 0)).toString());
		po.appendElement(ElementName.CONTENTOBJECT);
		JDFPageCondition pc = (JDFPageCondition) po.appendElement(ElementName.PAGECONDITION);
		JDFAttributeMap mPart = new JDFAttributeMap(AttributeName.RUNINDEX, "0 0");
		VJDFAttributeMap vMap = new VJDFAttributeMap();
		vMap.add(mPart);

		pc.setPartMapVector(vMap);
		KElement cond = pc.appendElement(AttributeName.CONDITION);
		cond.moveElements(pc.getChildElementVector(ElementName.PART, null), null);
		cond.setAttribute(XJDFConstants.PartContext, AttributeName.DOCINDEX);
		xjdfHelper.cleanUp();
		setSnippet(shLO, true);
		writeTest(xjdfHelper, "resources/pageCondition.xjdf");
	}

	/**
	 *
	 */
	@Test
	public void testDynamicField()
	{
		XJDFHelper xjdfHelper = new XJDFHelper(ElementName.LAYOUT, "DynamicField", null);
		xjdfHelper.setTypes("Imposition");
		SetHelper shLO = xjdfHelper.getCreateResourceSet(ElementName.LAYOUT, EnumUsage.Input);
		ResourceHelper rh = shLO.appendPartition(AttributeName.SIDE, "Front", true);
		rh.setID("ID_Lo");
		JDFLayout lo = (JDFLayout) rh.getResource();
		lo.setAutomated(true);
		JDFStripMark sm = (JDFStripMark) lo.appendElement(ElementName.STRIPMARK);
		sm.setAttribute(AttributeName.ABSOLUTEHEIGHT, "99");
		sm.setAttribute(AttributeName.ABSOLUTEWIDTH, "999");
		JDFRefAnchor ra = (JDFRefAnchor) sm.appendElement(ElementName.REFANCHOR);
		ra.setrRef(rh.getID());
		ra.setAnchorType(EnumAnchorType.Parent);
		ra.setAnchor(EnumAnchor.TopLeft);
		JDFJobField df = (JDFJobField) sm.appendElement(ElementName.JOBFIELD);
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
	public void testCutStack()
	{
		XJDFHelper xjdfHelper = new XJDFHelper(ElementName.LAYOUT, "CutStack", null);
		xjdfHelper.setTypes("Stripping");
		SetHelper shLO = xjdfHelper.getCreateResourceSet(ElementName.LAYOUT, EnumUsage.Input);
		ResourceHelper rh = shLO.appendPartition(null, true);
		JDFLayout lo = (JDFLayout) rh.getResource();

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
	public void testStrippingF16()
	{
		XJDFHelper xjdfHelper = new XJDFHelper(ElementName.LAYOUT, "3F-16", null);
		xjdfHelper.setTypes("Stripping");
		SetHelper shBS = xjdfHelper.getCreateResourceSet(ElementName.BINDERYSIGNATURE, EnumUsage.Input);
		ResourceHelper rhBS = shBS.appendPartition(null, true);
		JDFBinderySignature bs = (JDFBinderySignature) rhBS.getResource();
		bs.setFoldCatalog("F16-6");
		bs.setBinderySignatureType(EnumBinderySignatureType.Fold);

		SetHelper shLO = xjdfHelper.getCreateResourceSet(ElementName.LAYOUT, EnumUsage.Input);
		for (int i = 1; i <= 3; i++)
		{
			rhBS.appendPartMap(new JDFAttributeMap(XJDFConstants.BinderySignatureID, "bs" + i));
			ResourceHelper rh = shLO.appendPartition(AttributeName.SHEETNAME, "sheet" + i, true);
			JDFLayout lo = (JDFLayout) rh.getResource();
			lo.setAttribute(AttributeName.WORKSTYLE, EnumWorkStyle.WorkAndBack.getName());
			KElement pos = lo.appendElement(ElementName.POSITION);
			pos.setAttribute(XJDFConstants.BinderySignatureID, "bs" + i);
		}
		SetHelper shAss = xjdfHelper.getCreateResourceSet(ElementName.ASSEMBLY, EnumUsage.Input);
		ResourceHelper rhAss = shAss.appendPartition(null, true);
		rhAss.getResource().setAttribute(XJDFConstants.BinderySignatureIDs, "bs1 bs2 bs3");
		((JDFAssembly) rhAss.getResource()).setOrder(EnumOrder.Collecting);
		cleanSnippets(xjdfHelper);
		writeTest(xjdfHelper, "processes/StrippingF16-6.xjdf");
	}

}
