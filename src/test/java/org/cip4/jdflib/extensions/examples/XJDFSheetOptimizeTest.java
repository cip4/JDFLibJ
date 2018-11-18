/**
 * The CIP4 Software License, Version 1.0
 *
 * Copyright (c) 2001-2017 The International Cooperation for the Integration of Processes in Prepress, Press and Postpress (CIP4). All rights reserved.
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

import org.cip4.jdflib.JDFTestCaseBase;
import org.cip4.jdflib.auto.JDFAutoBinderySignature.EnumBinderySignatureType;
import org.cip4.jdflib.core.AttributeName;
import org.cip4.jdflib.core.ElementName;
import org.cip4.jdflib.core.JDFElement;
import org.cip4.jdflib.core.JDFResourceLink.EnumUsage;
import org.cip4.jdflib.core.KElement;
import org.cip4.jdflib.datatypes.JDFAttributeMap;
import org.cip4.jdflib.extensions.ResourceHelper;
import org.cip4.jdflib.extensions.SetHelper;
import org.cip4.jdflib.extensions.XJDFConstants;
import org.cip4.jdflib.extensions.XJDFHelper;
import org.cip4.jdflib.resource.process.JDFBinderySignature;
import org.cip4.jdflib.resource.process.JDFConvertingConfig;
import org.cip4.jdflib.resource.process.JDFLayout;
import org.cip4.jdflib.resource.process.JDFPosition;
import org.junit.Test;

/**
 *
 * @author rainer prosi
 * @date Dec 23, 2012
 */
public class XJDFSheetOptimizeTest extends JDFTestCaseBase
{
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
		xjdfHelper.getRoot().setXMLComment("most trivial list of single page documents - e.g. business cards");
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
			bs.setBinderySignatureType(EnumBinderySignatureType.Fold);
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
				addOutLayout(i, j);
			}
		}
		xjdfHelper.cleanUp();
		writeTest(xjdfHelper, "processes/GangLayout.xjdf");
	}

	/**
	 *
	 * @param i
	 * @param j
	 */
	private void addOutLayout(final int i, final int j)
	{
		final JDFAttributeMap partMap = new JDFAttributeMap("SheetName", "Sheet1");
		final String bsIJ = "BS_" + i + "_" + j;
		partMap.put(XJDFConstants.BinderySignatureID, bsIJ);
		final JDFLayout lo = (JDFLayout) layout.getCreatePartition(partMap, true).getResource();
		final SetHelper sh = xjdfHelper.getCreateSet(ElementName.BINDERYSIGNATURE, EnumUsage.Input);
		final JDFBinderySignature bs = (JDFBinderySignature) sh.getCreatePartition(new JDFAttributeMap(XJDFConstants.BinderySignatureID, bsIJ), true).getResource();
		bs.setBinderySignatureType(EnumBinderySignatureType.Grid);
		final JDFPosition p = JDFPosition.createPosition(lo, i, j, 2, 2);
		p.setAttribute(XJDFConstants.BinderySignatureID, bsIJ);
		assertNotNull(p);
	}

	/**
	 *
	 *
	 */
	private KElement addGang()
	{
		final KElement gang = sheetOptimizingParams.appendElement(ElementName.GANGELEMENT);
		gang.setAttribute(AttributeName.GANGELEMENTID, "Gang_" + gang.numSiblingElements(ElementName.GANGELEMENT, null));
		gang.setAttribute("MinQuantity", 1000, null);
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
		convertingConfig.setAttribute(AttributeName.SHEETHEIGHT + "Max", 700 * 72 / 2.54, null);
		convertingConfig.setAttribute(AttributeName.SHEETHEIGHT + "Min", 700 * 72 / 2.54, null);
		convertingConfig.setAttribute(AttributeName.SHEETWIDTH + "Max", 1000 * 72 / 2.54, null);
		convertingConfig.setAttribute(AttributeName.SHEETWIDTH + "Min", 1000 * 72 / 2.54, null);

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
