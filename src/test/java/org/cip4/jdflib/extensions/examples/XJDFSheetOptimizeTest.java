/**
 * The CIP4 Software License, Version 1.0
 *
 * Copyright (c) 2001-2016 The International Cooperation for the Integration of 
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
import org.cip4.jdflib.auto.JDFAutoStrippingParams.EnumWorkStyle;
import org.cip4.jdflib.core.AttributeName;
import org.cip4.jdflib.core.ElementName;
import org.cip4.jdflib.core.JDFResourceLink.EnumUsage;
import org.cip4.jdflib.core.KElement;
import org.cip4.jdflib.datatypes.JDFAttributeMap;
import org.cip4.jdflib.extensions.ResourceHelper;
import org.cip4.jdflib.extensions.SetHelper;
import org.cip4.jdflib.extensions.XJDFHelper;
import org.cip4.jdflib.resource.JDFResource.EnumPartIDKey;
import org.cip4.jdflib.resource.process.JDFBinderySignature;
import org.cip4.jdflib.resource.process.JDFConvertingConfig;
import org.cip4.jdflib.resource.process.JDFLayout;
import org.cip4.jdflib.resource.process.JDFPosition;
import org.junit.Test;

/**
 * 
 * @author rainerprosi
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
			KElement e = addGang();
			e.setAttribute("NPage", "1");
			e.setAttribute("PageDimension", "333 222");
		}
		xjdfHelper.getRoot().setXMLComment("most trivial list of single page documents - e.g. business cards");
		writeTest(xjdfHelper.getRoot(), "SimpleGangIn.xjdf", true);
		for (int i = 0; i < 6; i++)
		{
			JDFAttributeMap partMap = new JDFAttributeMap("BinderySignatureName", "BS" + i);
			String sn = "S" + (i / 3);
			partMap.put("SheetName", sn);
			ResourceHelper phLO = layout.appendPartition(partMap, true);
			phLO.setAmount(1000, partMap, true);
			JDFLayout lo = (JDFLayout) phLO.getCreateResource();
			JDFBinderySignature bs = (JDFBinderySignature) lo.appendElement(ElementName.BINDERYSIGNATURE);
			bs.appendElement(ElementName.POSITION);
		}
		writeTest(xjdfHelper.getRoot(), "SimpleGangOut.xjdf", true);
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
			addGangLayout();
		}
		writeTest(xjdfHelper.getRoot(), "SimpleGang.xjdf", true);
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
				addOutLayout(i, j);
			}
		}
		writeTest(xjdfHelper.getRoot(), "GangLayout.xjdf", true);
	}

	/**
	 *  
	 * @param i
	 * @param j
	 */
	private void addOutLayout(int i, int j)
	{
		JDFAttributeMap partMap = new JDFAttributeMap("SheetName", "Sheet1");
		partMap.put(EnumPartIDKey.BinderySignatureName, "BS_" + i + "_" + j);
		JDFLayout lo = (JDFLayout) layout.getCreatePartition(partMap, true).getResource();
		JDFBinderySignature bs = (JDFBinderySignature) lo.appendElement(ElementName.BINDERYSIGNATURE);
		JDFPosition p = JDFPosition.createPosition(bs, i, j, 2, 2);
		assertNotNull(p);
	}

	/**
	 *  
	 *  
	 */
	private KElement addGang()
	{
		KElement gang = sheetOptimizingParams.appendElement("GangElement");
		gang.setAttribute("ID", "Gang_" + gang.numSiblingElements("GangElement", null));
		gang.setAttribute("OrderedQuantity", 1000, null);
		return gang;
	}

	/**
	 *  
	 *  
	 */
	private KElement addGangLayout()
	{
		KElement gangelem = sheetOptimizingParams.getElement("GangElement", null, -1);
		KElement lo = gangelem.appendElement("Layout");
		lo.setAttribute("GangID", "Gang_" + gangelem.getID());
		lo.setAttribute(AttributeName.WORKSTYLE, EnumWorkStyle.Perfecting.getName());
		JDFBinderySignature bs = (JDFBinderySignature) lo.appendElement(ElementName.BINDERYSIGNATURE);
		bs.setFoldCatalog("F2-1");
		return lo;
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

		prepareSheetOptimizing();
		prepareLayout();
	}

	private void prepareLayout()
	{
		layout = xjdfHelper.getCreateResourceSet(ElementName.LAYOUT, EnumUsage.Output);
		layout.getCreatePartition(0, true);
	}

	private void prepareSheetOptimizing()
	{
		SetHelper hsSheetOptim = xjdfHelper.getCreateResourceSet("SheetOptimizingParams", EnumUsage.Input);
		ResourceHelper hpSheetOptim = hsSheetOptim.getCreatePartition(0, true);
		sheetOptimizingParams = hpSheetOptim.getCreateResource();

		SetHelper hsCC = xjdfHelper.getCreateResourceSet(ElementName.CONVERTINGCONFIG, EnumUsage.Input);
		ResourceHelper hpCC = hsCC.getCreatePartition(0, true);
		convertingConfig = (JDFConvertingConfig) hpCC.getCreateResource();
		convertingConfig.setSheetHeight(700 * 72 / 2.54, 700 * 72 / 2.54);
		convertingConfig.setSheetWidth(1000 * 72 / 2.54, 1000 * 72 / 2.54);

		sheetOptimizingParams.setAttribute(ElementName.CONVERTINGCONFIG + "Ref", hpCC.getID());

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
