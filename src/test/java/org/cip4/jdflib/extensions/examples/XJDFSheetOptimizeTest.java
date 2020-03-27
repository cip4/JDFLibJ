/**
 * The CIP4 Software License, Version 1.0
 *
 * Copyright (c) 2001-2020 The International Cooperation for the Integration of Processes in Prepress, Press and Postpress (CIP4). All rights reserved.
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

import static org.junit.Assert.assertNotNull;

import org.cip4.jdflib.auto.JDFAutoBinderySignature.EnumBinderySignatureType;
import org.cip4.jdflib.auto.JDFAutoMedia.EnumMediaType;
import org.cip4.jdflib.core.AttributeName;
import org.cip4.jdflib.core.ElementName;
import org.cip4.jdflib.core.JDFElement;
import org.cip4.jdflib.core.JDFElement.EnumVersion;
import org.cip4.jdflib.core.JDFResourceLink.EnumUsage;
import org.cip4.jdflib.core.KElement;
import org.cip4.jdflib.datatypes.JDFAttributeMap;
import org.cip4.jdflib.datatypes.JDFRectangle;
import org.cip4.jdflib.datatypes.JDFXYPair;
import org.cip4.jdflib.extensions.ResourceHelper;
import org.cip4.jdflib.extensions.SetHelper;
import org.cip4.jdflib.extensions.XJDFConstants;
import org.cip4.jdflib.extensions.XJDFHelper;
import org.cip4.jdflib.resource.process.JDFBinderySignature;
import org.cip4.jdflib.resource.process.JDFConvertingConfig;
import org.cip4.jdflib.resource.process.JDFCutBlock;
import org.cip4.jdflib.resource.process.JDFGangElement;
import org.cip4.jdflib.resource.process.JDFLayout;
import org.cip4.jdflib.resource.process.JDFMedia;
import org.cip4.jdflib.resource.process.JDFPosition;
import org.junit.Test;

/**
 *
 * @author rainer prosi
 * @date Dec 23, 2012
 */
public class XJDFSheetOptimizeTest extends ExampleTest
{
	private static final String GANG = "Gang_";
	private XJDFHelper xjdfHelper;
	private KElement sheetOptimizingParams;
	private SetHelper layout;
	private JDFConvertingConfig convertingConfig;

	/**
	 *
	 *
	 */
	@Test
	public void testSimple()
	{
		for (int i = 0; i < 6; i++)
		{
			final KElement e = addGang();
			e.setAttribute("NPage", "1");
			e.setAttribute("PageDimension", "333 222");
		}
		xjdfHelper.getRoot().setXMLComment("most trivial list of single page documents - e.g. business cards", true);
		writeTest(xjdfHelper, "SimpleGangIn.xjdf");
		for (int i = 0; i < 6; i++)
		{
			final JDFAttributeMap partMap = new JDFAttributeMap("BinderySignatureID", "BS" + i);
			final String sn = "S" + (i / 3);
			partMap.put("SheetName", sn);
			final ResourceHelper phLO = layout.appendPartition(partMap, true);
			phLO.setAmount(1000, partMap, true);
			final JDFLayout lo = (JDFLayout) phLO.getCreateResource();
			final SetHelper sh = xjdfHelper.getCreateSet(XJDFConstants.Resource, ElementName.BINDERYSIGNATURE, EnumUsage.Input);
			final JDFBinderySignature bs = (JDFBinderySignature) sh.getCreatePartition(new JDFAttributeMap(XJDFConstants.BinderySignatureID, "BS" + i), true).getResource();
			bs.setBinderySignatureType(EnumBinderySignatureType.Grid);
			lo.appendElement(ElementName.POSITION).setAttribute(XJDFConstants.BinderySignatureID, "BS" + i);
		}
		cleanSnippets(xjdfHelper);
		writeTest(xjdfHelper, "processes/SimpleGangOut.xjdf");
	}

	/**
	 *
	 *
	 */
	@Test
	public void testSimpleUpload()
	{
		final KElement e = addGang();
		e.setAttribute("NPage", "1");
		e.setAttribute("PageDimension", "333 222");
		final JDFMedia media = (JDFMedia) e.appendElement(ElementName.MEDIA);
		media.setMediaType(EnumMediaType.Paper);
		media.setMediaQuality("MyQuality");

		xjdfHelper.getRoot().setXMLComment("most trivial list of single page documents - e.g. business cards", true);
		layout.deleteNode();
		convertingConfig.deleteNode();

		cleanSnippets(xjdfHelper);
		writeTest(xjdfHelper, "processes/SimpleGangUpload.xjdf", false, null);
	}

	/**
	 *
	 *
	 */
	@Test
	public void testOptimizeCutBlock()
	{
		for (int i = 0; i < 6; i++)
		{
			final JDFElement e = addGang();
			e.setAttribute(AttributeName.NPAGE, "1");
			e.setAttribute(AttributeName.PAGEDIMENSION, new JDFXYPair(500, 350).scaleFromMM(), null);
			if (i % 2 == 0)
				e.setAttribute(AttributeName.OPERATIONS, "Laminate");

		}
		xjdfHelper.setVersion(EnumVersion.Version_2_1);
		final JDFCutBlock cb = (JDFCutBlock) convertingConfig.getCreateElement(ElementName.CUTBLOCK);
		cb.setAttribute(AttributeName.BOX, new JDFRectangle(0, 0, 500, 700).scaleFromMM(), null);
		cb.setBlockName("B1");
		cb.setAttribute(AttributeName.OPERATIONS, "Laminate");
		final JDFCutBlock cb2 = (JDFCutBlock) convertingConfig.getCreateElement(ElementName.CUTBLOCK, null, 1);
		cb2.setAttribute(AttributeName.BOX, new JDFRectangle(500, 0, 1000, 700).scaleFromMM(), null);
		cb2.setBlockName("B2");

		writeTest(xjdfHelper, "CutGangIn.xjdf");
		for (int i = 0; i < 6; i++)
		{
			final String sn = "S" + (i / 2);
			final JDFAttributeMap partMap = new JDFAttributeMap(AttributeName.SHEETNAME, sn);
			final ResourceHelper phLO = layout.getCreatePartition(partMap, true);
			phLO.setAmount(1000, partMap, true);
			final JDFLayout lo = (JDFLayout) phLO.getCreateResource();
			final JDFPosition pos = (JDFPosition) lo.appendElement(ElementName.POSITION);
			final JDFRectangle relBox = new JDFRectangle(0, 0, 0.5, 1);
			if (i % 2 == 1)
				relBox.shift(0.5, 0);
			pos.setRelativeBox(relBox);
			pos.setAttribute(XJDFConstants.BinderySignatureID, "BS" + i);

			final SetHelper sh = xjdfHelper.getCreateSet(XJDFConstants.Resource, ElementName.BINDERYSIGNATURE, EnumUsage.Input);
			final JDFBinderySignature bs = (JDFBinderySignature) sh.getCreatePartition(new JDFAttributeMap(XJDFConstants.BinderySignatureID, "BS" + i), true).getResource();
			bs.setBinderySignatureType(EnumBinderySignatureType.Grid);
			bs.setNumberUp(1, 1);
			final KElement gang = sheetOptimizingParams.getElement(ElementName.GANGELEMENT, null, i);

			gang.setAttribute(XJDFConstants.BinderySignatureIDs, "BS" + i);

		}
		cleanSnippets(xjdfHelper);
		writeTest(xjdfHelper, "processes/CutGangOut.xjdf");
	}

	/**
	 *
	 *
	 */
	@Test
	public void testOptimizeCutBlockBS()
	{
		for (int i = 0; i < 6; i++)
		{
			final JDFElement e = addGang();
			e.setAttribute(AttributeName.NPAGE, "1");
			e.setAttribute(AttributeName.PAGEDIMENSION, new JDFXYPair(500, 350).scaleFromMM(), null);
			e.setAttribute(XJDFConstants.BinderySignatureIDs, "BS" + i);
			if (i % 2 == 0)
				e.setAttribute(AttributeName.OPERATIONS, "Laminate");

			final SetHelper sh = xjdfHelper.getCreateSet(XJDFConstants.Resource, ElementName.BINDERYSIGNATURE, EnumUsage.Input);
			final JDFBinderySignature bs = (JDFBinderySignature) sh.getCreatePartition(new JDFAttributeMap(XJDFConstants.BinderySignatureID, "BS" + i), true).getResource();
			bs.setBinderySignatureType(EnumBinderySignatureType.Grid);
			bs.setNumberUp(1, 1);
		}
		xjdfHelper.setVersion(EnumVersion.Version_2_1);
		final JDFCutBlock cb = (JDFCutBlock) convertingConfig.getCreateElement(ElementName.CUTBLOCK);
		cb.setAttribute(AttributeName.BOX, new JDFRectangle(0, 0, 500, 700).scaleFromMM(), null);
		cb.setBlockName("B1");
		cb.setAttribute(AttributeName.OPERATIONS, "Laminate");
		final JDFCutBlock cb2 = (JDFCutBlock) convertingConfig.getCreateElement(ElementName.CUTBLOCK, null, 1);
		cb2.setAttribute(AttributeName.BOX, new JDFRectangle(500, 0, 1000, 700).scaleFromMM(), null);
		cb2.setBlockName("B2");

		writeTest(xjdfHelper, "CutGangInBS.xjdf");
		for (int i = 0; i < 6; i++)
		{
			final String sn = "S" + (i / 2);
			final JDFAttributeMap partMap = new JDFAttributeMap(AttributeName.SHEETNAME, sn);
			final ResourceHelper phLO = layout.getCreatePartition(partMap, true);
			phLO.setAmount(1000, partMap, true);
			final JDFLayout lo = (JDFLayout) phLO.getCreateResource();
			final JDFPosition pos = (JDFPosition) lo.appendElement(ElementName.POSITION);
			final JDFRectangle relBox = new JDFRectangle(0, 0, 0.5, 1);
			if (i % 2 == 1)
				relBox.shift(0.5, 0);
			pos.setRelativeBox(relBox);
			pos.setAttribute(XJDFConstants.BinderySignatureID, "BS" + i);
		}
		cleanSnippets(xjdfHelper);
		writeTest(xjdfHelper, "processes/CutGangOutBS.xjdf");
	}

	/**
	 *
	 *
	 */
	@Test
	public void testLayout()
	{
		for (int i = 0; i < 6; i++)
		{
			addGang();
		}
		xjdfHelper.cleanUp();
		setSnippet(xjdfHelper, true);
		writeTest(xjdfHelper, "processes/SimpleGang.xjdf");
	}

	/**
	 *
	 *
	 */
	@Test
	public void testOutputLayout()
	{
		for (int i = 0; i < 2; i++)
		{
			for (int j = 0; j < 2; j++)
			{
				addGang();
				addOutLayout(i, j, 0, 4, false);
			}
		}
		layout.getPartition(0).setAmount(513, null, true);

		xjdfHelper.setVersion(EnumVersion.Version_2_1);
		xjdfHelper.cleanUp();
		setSnippet(xjdfHelper, true);
		setSnippet(xjdfHelper.getAuditPool(), false);
		setSnippet(xjdfHelper.getNodeInfo(), false);
		writeTest(xjdfHelper, "processes/GangLayout.xjdf");
	}

	/**
	 *
	 *
	 */
	@Test
	public void testOutputLayoutPositionOrd()
	{
		for (int k = 0; k < 2; k++)
		{
			for (int i = 0; i < 2; i++)
			{
				for (int j = 0; j < 1; j++)
				{
					addGang();
					addOutLayout(i, j, k, 2, true);
				}
			}
		}

		xjdfHelper.cleanUp();
		xjdfHelper.setVersion(EnumVersion.Version_2_1);
		setSnippet(layout, true);
		writeTest(xjdfHelper, "processes/GangLayoutPosOrd.xjdf");
	}

	/**
	 *
	 * @param i
	 * @param j
	 */
	private void addOutLayout(final int i, final int j, final int k, final int n, final boolean wantBS)
	{
		final JDFAttributeMap partMap = new JDFAttributeMap("SheetName", "Sheet" + (k + 1));
		final ResourceHelper lor = layout.getCreatePartition(k, true);
		lor.setPartMap(partMap);
		final JDFLayout lo = (JDFLayout) lor.getResource();
		final JDFPosition p = JDFPosition.createPosition(lo, i, j * 2, 2, n);
		final int pOrd = n * k + i * n / 2 + j;
		p.setAttribute(AttributeName.GANGELEMENTID, GANG + pOrd);
		p.setAttribute(AttributeName.POSITIONORD, "" + pOrd);

		final JDFPosition p2 = JDFPosition.createPosition(lo, i, j * 2 + 1, 2, n);
		p2.setAttribute(AttributeName.GANGELEMENTID, GANG + pOrd);
		p2.setAttribute(AttributeName.POSITIONORD, "" + pOrd);

		if (wantBS)
		{
			final SetHelper sh = xjdfHelper.getCreateSet(ElementName.BINDERYSIGNATURE, EnumUsage.Input);
			final String bsIJ = "BS_" + (k * n + i) + "_" + j;
			partMap.put(XJDFConstants.BinderySignatureID, bsIJ);
			final JDFBinderySignature bs = (JDFBinderySignature) sh.getCreatePartition(new JDFAttributeMap(XJDFConstants.BinderySignatureID, bsIJ), true).getResource();
			bs.setBinderySignatureType(EnumBinderySignatureType.Grid);
			p.setAttribute(XJDFConstants.BinderySignatureID, bsIJ);
			p2.setAttribute(XJDFConstants.BinderySignatureID, bsIJ);
		}
		assertNotNull(p);
	}

	/**
	 *
	 *
	 */
	private JDFGangElement addGang()
	{
		final JDFGangElement gang = (JDFGangElement) sheetOptimizingParams.appendElement(ElementName.GANGELEMENT);
		final int nGang = gang.numSiblingElements(ElementName.GANGELEMENT, null);
		gang.setGangElementID(GANG + nGang);
		gang.setJobID(GANG + nGang * 10);
		gang.setOrderQuantity(1000 - nGang * 50 + 25);
		return gang;
	}

	/**
	 * @see org.cip4.jdflib.JDFTestCaseBase#setUp()
	 */
	@Override
	public void setUp() throws Exception
	{
		super.setUp();
		xjdfHelper = new XJDFHelper("job", "root", null);
		xjdfHelper.setTypes("SheetOptimizing");
		JDFElement.setLongID(false);
		prepareSheetOptimizing();
		prepareLayout();
	}

	private void prepareLayout()
	{
		layout = xjdfHelper.getCreateSet(XJDFConstants.Resource, ElementName.LAYOUT, EnumUsage.Output);
		layout.getCreatePartition(0, true);
	}

	private void prepareSheetOptimizing()
	{
		final SetHelper hsSheetOptim = xjdfHelper.getCreateSet(XJDFConstants.Resource, "SheetOptimizingParams", EnumUsage.Input);
		final ResourceHelper hpSheetOptim = hsSheetOptim.getCreatePartition(0, true);
		sheetOptimizingParams = hpSheetOptim.getCreateResource();

		convertingConfig = (JDFConvertingConfig) sheetOptimizingParams.appendElement(ElementName.CONVERTINGCONFIG);
		convertingConfig.setAttribute(AttributeName.SHEETHEIGHT + "Max", 75 * 72 / 2.54, null);
		convertingConfig.setAttribute(AttributeName.SHEETHEIGHT + "Min", 70 * 72 / 2.54, null);
		convertingConfig.setAttribute(AttributeName.SHEETWIDTH + "Max", 105 * 72 / 2.54, null);
		convertingConfig.setAttribute(AttributeName.SHEETWIDTH + "Min", 100 * 72 / 2.54, null);
	}

	/**
	 * @see org.cip4.jdflib.JDFTestCaseBase#toString()
	 */
	@Override
	public String toString()
	{
		return super.toString() + "\n" + xjdfHelper;
	}
}
