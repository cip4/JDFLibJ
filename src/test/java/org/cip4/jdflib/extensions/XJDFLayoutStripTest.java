/*
 * The CIP4 Software License, Version 1.0
 *
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
 *    Alternately, this acknowledgment mrSubRefay appear in the software itself,
 *    if and wherever such third-party acknowledgments normally appear.
 *
 * 4. The names "CIP4" and "The International Cooperation for the Integration of
 *    Processes in  Prepress, Press and Postpress" must
 *    not be used to endorse or promote products derived from this
 *    software without prior written permission. For written
 *    permission, please contact info@cip4.org.
 *
 * 5. Products derived from this software may not be called "CIP4",
 *    nor may "CIP4" appear in their name, without prior writtenrestartProcesses()
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
 * SPECIAL, EXEMPLARY, OR CONSEQUENTIrSubRefAL DAMAGES (INCLUDING, BUT NOT
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
 * originally based on software restartProcesses()
 * copyright (c) 1999-2001, Heidelberger Druckmaschinen AG
 * copyright (c) 1999-2001, Agfa-Gevaert N.V.
 *
 * For more information on The International Cooperation for the
 * Integration of Processes in  Prepress, Press and Postpress , please see
 * <http://www.cip4.org/>.
 *
 */
package org.cip4.jdflib.extensions;

import static org.junit.Assert.assertEquals;

import org.cip4.jdflib.core.AttributeName;
import org.cip4.jdflib.core.ElementName;
import org.cip4.jdflib.core.JDFCustomerInfo;
import org.cip4.jdflib.core.JDFDoc;
import org.cip4.jdflib.core.JDFResourceLink.EnumUsage;
import org.cip4.jdflib.core.KElement;
import org.cip4.jdflib.datatypes.JDFAttributeMap;
import org.cip4.jdflib.datatypes.JDFRectangle;
import org.cip4.jdflib.extensions.xjdfwalker.XJDFToJDFConverter;
import org.cip4.jdflib.extensions.xjdfwalker.jdftoxjdf.JDFToXJDF;
import org.cip4.jdflib.node.JDFNode;
import org.cip4.jdflib.node.JDFNode.EnumProcessUsage;
import org.cip4.jdflib.node.JDFNode.EnumType;
import org.cip4.jdflib.resource.JDFResource.EnumPartIDKey;
import org.cip4.jdflib.resource.JDFStrippingParams;
import org.cip4.jdflib.resource.process.JDFBinderySignature;
import org.cip4.jdflib.resource.process.JDFContentObject;
import org.cip4.jdflib.resource.process.JDFLayout;
import org.cip4.jdflib.resource.process.JDFPosition;
import org.junit.Test;

/**
 * @author Rainer Prosi, Heidelberger Druckmaschinen
 *
 */
public class XJDFLayoutStripTest extends XJDFCreatorTest
{
	private SetHelper losh;
	private SetHelper bssh;

	/**
	 *
	 */
	@Test
	public void testStripLayout_BSSep()
	{
		bssh = theHelper.getCreateSet(XJDFConstants.Resource, ElementName.BINDERYSIGNATURE, EnumUsage.Input);
		ResourceHelper bsh = bssh.appendPartition(new JDFAttributeMap(), true);
		JDFBinderySignature bs = (JDFBinderySignature) bsh.getCreateResource();
		initBS(bs, 0);

		for (int k = 0; k < 2; k++)
		{
			JDFAttributeMap sheetMap = getSheetMap(1);
			sheetMap.put("Side", k == 0 ? "Front" : "Back");
			ResourceHelper loh = losh.appendPartition(sheetMap, true);
			JDFLayout lo = (JDFLayout) loh.getResource();

			for (int i = 0; i < 8; i++)
			{
				initContentObject(lo, k * 8 + i);
			}
		}
		ResourceHelper loh = losh.appendPartition(getSheetMap(1), true);
		JDFLayout lo = (JDFLayout) loh.getResource();
		for (int i = 0; i < 2; i++)
		{
			JDFPosition pos = (JDFPosition) lo.appendElement(ElementName.POSITION);
			pos.setID("pos" + i);
			if (i == 0)
				pos.setRelativeBox(new JDFRectangle(0, 0, 0.5, 1));
			else
				pos.setRelativeBox(new JDFRectangle(0.5, 0, 1, 1));

			pos.setAttribute("AssemblyIDs", "CutSheet1");
			pos.setAttribute("BinderySignatureRef", bsh.getPartition().getID());
		}

		theHelper.writeToFile(sm_dirTestDataTemp + "loStrip.xjdf");
	}

	/**
	 * @param lo
	 * @param i
	 * @return
	 */
	private JDFContentObject initContentObject(JDFLayout lo, int i)
	{
		JDFContentObject co = lo.appendContentObject();
		co.setAttribute("PositionRef", "pos" + (i % 8) / 4);
		co.setAttribute("PageIndex", i, null);
		return co;
	}

	/**
	 * @param bs
	 */
	private void initBS(JDFBinderySignature bs, int i)
	{
		bs.setFoldCatalog("F8-4");
		bs.setAttribute(AttributeName.ASSEMBLYID, "Ass_ID" + i);
		bs.appendElement("StripCellParams");
	}

	/**
	 *
	 */
	@Test
	public void testStripLayout_AllinOne()
	{

		for (int k = 0; k < 2; k++)
		{
			JDFAttributeMap sheetMap = getSheetMap(1);
			sheetMap.put("Side", k == 0 ? "Front" : "Back");
			ResourceHelper loh = losh.appendPartition(sheetMap, true);
			JDFLayout lo = (JDFLayout) loh.getResource();

			for (int i = 0; i < 8; i++)
			{
				initContentObject(lo, k * 8 + i);
			}
		}
		ResourceHelper loh = losh.appendPartition(getSheetMap(1), true);
		JDFLayout lo = (JDFLayout) loh.getResource();
		JDFBinderySignature bs = (JDFBinderySignature) lo.appendElement(ElementName.BINDERYSIGNATURE);
		initBS(bs, 0);

		for (int i = 0; i < 2; i++)
		{
			JDFPosition pos = (JDFPosition) bs.appendElement(ElementName.POSITION);
			pos.setID("pos" + i);
			if (i == 0)
				pos.setRelativeBox(new JDFRectangle(0, 0, 0.5, 1));
			else
				pos.setRelativeBox(new JDFRectangle(0.5, 0, 1, 1));

			pos.setAttribute("AssemblyIDs", "CutSheet1");
		}

		theHelper.writeToFile(sm_dirTestDataTemp + "loStrip1.xjdf");
	}

	/**
	 *
	 */
	@Test
	public void testDescribeFinishedGang()
	{
		for (int k = 0; k < 2; k++)
		{
			JDFAttributeMap sheetMap = getSheetMap(k);
			ResourceHelper loh = losh.appendPartition(sheetMap, true);
			JDFLayout lo = (JDFLayout) loh.getResource();
			SetHelper nish = theHelper.getCreateSet(XJDFConstants.Resource, ElementName.NODEINFO, EnumUsage.Input);
			ResourceHelper niph = nish.appendPartition(sheetMap, true);
			niph.getRoot().setAttribute("AmountGood", 1234, null);
			SetHelper cush = theHelper.getCreateSet(XJDFConstants.Resource, ElementName.CUSTOMERINFO, EnumUsage.Input);
			SetHelper cosh = theHelper.getCreateSet(XJDFConstants.Resource, ElementName.CONTACT, EnumUsage.Input);
			for (int i = 0; i < 4; i++)
			{
				int ii = k * 4 + i;
				ProductHelper ph = theHelper.appendProduct();
				ph.getRoot().setID("P" + ii);
				ph.setRoot();
				ph.setAmount(1000);
				if (ii >= 6)
					ph.getRoot().deleteNode();
				JDFBinderySignature bs = (JDFBinderySignature) lo.appendElement(ElementName.BINDERYSIGNATURE);
				bs.setAttribute("ProductRef", "P" + (ii % 6));
				initBS(bs, ii);
				JDFPosition pos = (JDFPosition) bs.appendElement(ElementName.POSITION);
				double d = i;
				double d2 = i + 1;
				pos.setRelativeBox(new JDFRectangle(d * 0.25, d * 0.25, d2 * 0.25, d2 * 0.25));

				if (ii < 6)
				{
					ResourceHelper cuph = cush.appendPartition(new JDFAttributeMap("Product", "P" + ii), true);
					ResourceHelper coph = cosh.appendPartition(new JDFAttributeMap("Product", "P" + ii), true);
					coph.getResource().setXMLComment("Contact, Address etc go here");
					coph.getResource().setAttribute("ContactTypes", "Customer");
					JDFCustomerInfo ci = (JDFCustomerInfo) cuph.getResource();
					ci.setAttribute("ContactRefs", coph.getID());
					ci.setCustomerOrderID("CustomerJob" + ii);
					ph.setCustomerInfo(cuph);
				}
			}
		}

		theHelper.writeToFile(sm_dirTestDataTemp + "loGang.xjdf");
	}

	/**
	 *
	 */
	@Test
	public void testStripLayout_verbose()
	{
		for (int k = 0; k < 2; k++)
		{
			JDFAttributeMap sheetMap = getSheetMap(1);
			sheetMap.put("Side", k == 0 ? "Front" : "Back");
			ResourceHelper loh = losh.appendPartition(sheetMap, true);
			JDFLayout lo = (JDFLayout) loh.getResource();

			for (int i = 0; i < 8; i++)
			{
				JDFContentObject co = initContentObject(lo, k * 8 + i);
				co.appendElement("StripCellParams");
				co.appendElement("SignatureCell");
				co.setXMLComment("we would probably simply merge signaturecell and stripcellparams into each co");

			}
		}
		ResourceHelper loh = losh.appendPartition(getSheetMap(1), true);
		JDFLayout lo = (JDFLayout) loh.getResource();
		JDFBinderySignature bs = (JDFBinderySignature) lo.appendElement(ElementName.BINDERYSIGNATURE);
		initBS(bs, 0);
		bs.removeChild("StripCellParams", null, 0);

		for (int i = 0; i < 2; i++)
		{
			JDFPosition pos = (JDFPosition) bs.appendElement(ElementName.POSITION);
			pos.setID("pos" + i);
			if (i == 0)
				pos.setRelativeBox(new JDFRectangle(0, 0, 0.5, 1));
			else
				pos.setRelativeBox(new JDFRectangle(0.5, 0, 1, 1));

			pos.setAttribute("AssemblyIDs", "CutSheet1");
		}

		theHelper.writeToFile(sm_dirTestDataTemp + "loStripVerbose.xjdf");
	}

	/**
	 * @param i
	 * @return
	 */
	private JDFAttributeMap getSheetMap(int i)
	{
		JDFAttributeMap map = new JDFAttributeMap();
		map.put("SheetName", "Sheet" + i);
		return map;
	}

	/**
	 * @see org.cip4.jdflib.extensions.XJDFCreatorTest#setUp()
	 * @throws Exception
	 */
	@Override
	public void setUp() throws Exception
	{
		super.setUp();
		theHelper.getRoot().setXMLComment("create a stripping in a layout\n Stripping now consumes a layout");
		theHelper.getRoot().setAttribute("Types", "Imposition");

		losh = theHelper.getCreateSet(XJDFConstants.Resource, ElementName.LAYOUT, EnumUsage.Input);
		bssh = null;
	}

	/**
	 *
	 */
	@Test
	public void testStripCell()
	{
		JDFNode n = new JDFDoc(ElementName.JDF).getJDFRoot();
		n.setType(EnumType.Stripping);
		JDFStrippingParams sp = (JDFStrippingParams) n.appendMatchingResource(ElementName.STRIPPINGPARAMS, EnumProcessUsage.AnyInput, null);
		sp = (JDFStrippingParams) sp.addPartition(EnumPartIDKey.BinderySignatureName, "BS1");
		JDFBinderySignature bs = (JDFBinderySignature) n.addResource(ElementName.BINDERYSIGNATURE, null);
		sp.refBinderySignature(bs);
		sp.appendPosition();
		sp.appendStripCellParams().setSpine(42);

		JDFToXJDF xjdf20 = new JDFToXJDF();

		KElement xjdf = xjdf20.makeNewJDF(n, null);
		JDFBinderySignature bsNew = (JDFBinderySignature) xjdf.getChildByTagName(ElementName.BINDERYSIGNATURE, null, 0, null, false, true);
		assertEquals(42, bsNew.getSignatureCell(0).getIntAttribute(XJDFConstants.TrimSpine, null, 0));

		XJDFToJDFConverter xc = new XJDFToJDFConverter(null);
		JDFDoc dJDF = xc.convert(xjdf);
		JDFNode n2 = dJDF.getJDFRoot();
		n2.getResource(ElementName.STRIPPINGPARAMS, null, 0);
	}
}
