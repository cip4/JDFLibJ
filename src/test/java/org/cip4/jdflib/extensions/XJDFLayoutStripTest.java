/*
 * The CIP4 Software License, Version 1.0
 *
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
 * the Integration of Processes in Prepress, Press and Postpress (www.cip4.org)" Alternately, this acknowledgment mrSubRefay appear in the software itself, if and wherever such third-party
 * acknowledgments normally appear.
 *
 * 4. The names "CIP4" and "The International Cooperation for the Integration of Processes in Prepress, Press and Postpress" must not be used to endorse or promote products derived from this software
 * without prior written permission. For written permission, please contact info@cip4.org.
 *
 * 5. Products derived from this software may not be called "CIP4", nor may "CIP4" appear in their name, without prior writtenrestartProcesses() permission of the CIP4 organization
 *
 * Usage of this software in commercial products is subject to restrictions. For details please consult info@cip4.org.
 *
 * THIS SOFTWARE IS PROVIDED ``AS IS'' AND ANY EXPRESSED OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO, THE IMPLIED WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE ARE
 * DISCLAIMED. IN NO EVENT SHALL THE INTERNATIONAL COOPERATION FOR THE INTEGRATION OF PROCESSES IN PREPRESS, PRESS AND POSTPRESS OR ITS CONTRIBUTORS BE LIABLE FOR ANY DIRECT, INDIRECT, INCIDENTAL,
 * SPECIAL, EXEMPLARY, OR CONSEQUENTIrSubRefAL DAMAGES (INCLUDING, BUT NOT LIMITED TO, PROCUREMENT OF SUBSTITUTE GOODS OR SERVICES; LOSS OF USE, DATA, OR PROFITS; OR BUSINESS INTERRUPTION) HOWEVER
 * CAUSED AND ON ANY THEORY OF LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY, OR TORT (INCLUDING NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE OF THIS SOFTWARE, EVEN IF ADVISED OF THE
 * POSSIBILITY OF SUCH DAMAGE. ====================================================================
 *
 * This software consists of voluntary contributions made by many individuals on behalf of the The International Cooperation for the Integration of Processes in Prepress, Press and Postpress and was
 * originally based on software restartProcesses() copyright (c) 1999-2001, Heidelberger Druckmaschinen AG copyright (c) 1999-2001, Agfa-Gevaert N.V.
 *
 * For more information on The International Cooperation for the Integration of Processes in Prepress, Press and Postpress , please see <http://www.cip4.org/>.
 *
 */
package org.cip4.jdflib.extensions;

import org.cip4.jdflib.auto.JDFAutoAssembly.EnumOrder;
import org.cip4.jdflib.auto.JDFAutoBinderySignature.EnumBinderySignatureType;
import org.cip4.jdflib.auto.JDFAutoSignatureCell.EnumOrientation;
import org.cip4.jdflib.auto.JDFAutoStripCellParams.EnumSides;
import org.cip4.jdflib.auto.JDFAutoStrippingParams.EnumWorkStyle;
import org.cip4.jdflib.core.*;
import org.cip4.jdflib.core.JDFElement.EnumValidationLevel;
import org.cip4.jdflib.core.JDFResourceLink.EnumUsage;
import org.cip4.jdflib.datatypes.JDFAttributeMap;
import org.cip4.jdflib.datatypes.JDFIntegerList;
import org.cip4.jdflib.datatypes.JDFMatrix;
import org.cip4.jdflib.datatypes.JDFRectangle;
import org.cip4.jdflib.extensions.xjdfwalker.XJDFToJDFConverter;
import org.cip4.jdflib.extensions.xjdfwalker.jdftoxjdf.JDFToXJDF;
import org.cip4.jdflib.node.JDFNode;
import org.cip4.jdflib.node.JDFNode.EnumProcessUsage;
import org.cip4.jdflib.node.JDFNode.EnumType;
import org.cip4.jdflib.resource.JDFResource.EnumPartIDKey;
import org.cip4.jdflib.resource.JDFStrippingParams;
import org.cip4.jdflib.resource.process.JDFAssembly;
import org.cip4.jdflib.resource.process.JDFBinderySignature;
import org.cip4.jdflib.resource.process.JDFContentObject;
import org.cip4.jdflib.resource.process.JDFLayout;
import org.cip4.jdflib.resource.process.JDFPosition;
import org.cip4.jdflib.resource.process.JDFSignatureCell;
import org.cip4.jdflib.resource.process.JDFStripCellParams;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

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
	void testStripLayout_BSSep()
	{
		bssh = theHelper.getCreateSet(XJDFConstants.Resource, ElementName.BINDERYSIGNATURE, EnumUsage.Input);
		final ResourceHelper bsh = bssh.appendPartition(new JDFAttributeMap(), true);
		final JDFBinderySignature bs = (JDFBinderySignature) bsh.getCreateResource();
		initBS(bs, 0);

		for (int k = 0; k < 2; k++)
		{
			final JDFAttributeMap sheetMap = getSheetMap(1);
			sheetMap.put("Side", k == 0 ? "Front" : "Back");
			final ResourceHelper loh = losh.appendPartition(sheetMap, true);
			final JDFLayout lo = (JDFLayout) loh.getResource();

			for (int i = 0; i < 8; i++)
			{
				initContentObject(lo, k * 8 + i);
			}
		}
		final ResourceHelper loh = losh.appendPartition(getSheetMap(1), true);
		final JDFLayout lo = (JDFLayout) loh.getResource();
		for (int i = 0; i < 2; i++)
		{
			final JDFPosition pos = (JDFPosition) lo.appendElement(ElementName.POSITION);
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
	private JDFContentObject initContentObject(final JDFLayout lo, final int i)
	{
		final KElement po = lo.appendElement(XJDFConstants.PlacedObject);
		final JDFContentObject co = (JDFContentObject) po.appendElement(ElementName.CONTENTOBJECT);
		po.setAttribute("PositionRef", "pos" + (i % 8) / 4);
		po.setAttribute(AttributeName.ORD, i, null);
		po.setAttribute(AttributeName.CTM, JDFMatrix.getUnitMatrix(), 2);
		return co;
	}

	/**
	 * @param bs
	 */
	private void initBS(final JDFBinderySignature bs, final int i)
	{
		bs.setFoldCatalog("F8-4");
		bs.setXPathAttribute("../Part/@BinderySignatureID", "Ass_ID" + i);
		bs.appendElement("SignatureCell");
	}

	/**
	 *
	 */
	@Test
	void testStripLayout_AllinOne()
	{
		theHelper.setTypes("Stripping");
		theHelper.setJobID("j");
		for (int k = 0; k < 2; k++)
		{
			final JDFAttributeMap sheetMap = getSheetMap(1);
			sheetMap.put("Side", k == 0 ? "Front" : "Back");
			final ResourceHelper loh = losh.appendPartition(sheetMap, true);
			final JDFLayout lo = (JDFLayout) loh.getResource();

			for (int i = 0; i < 8; i++)
			{
				initContentObject(lo, k * 8 + i);
			}
		}
		losh.setUsage(EnumUsage.Output);
		final ResourceHelper loh = losh.appendPartition(getSheetMap(1), true);
		final JDFLayout lo = (JDFLayout) loh.getResource();
		bssh = theHelper.getCreateSet(XJDFConstants.Resource, ElementName.BINDERYSIGNATURE, EnumUsage.Input);
		final ResourceHelper bsh = bssh.getCreatePartition(0, true);
		final JDFBinderySignature bs = (JDFBinderySignature) bsh.getResource();
		initBS(bs, 0);

		for (int i = 0; i < 2; i++)
		{
			final JDFPosition pos = (JDFPosition) lo.appendElement(ElementName.POSITION);
			pos.setID("pos" + i);
			if (i == 0)
				pos.setRelativeBox(new JDFRectangle(0, 0, 0.5, 1));
			else
				pos.setRelativeBox(new JDFRectangle(0.5, 0, 1, 1));

			pos.setAttribute(XJDFConstants.BinderySignatureID, "Ass_ID0");
		}
		writeRoundTripX(theHelper, "loStrip1", EnumValidationLevel.Incomplete);
	}

	/**
	 *
	 */
	@Test
	void testDescribeFinishedGang()
	{
		for (int k = 0; k < 2; k++)
		{
			final JDFAttributeMap sheetMap = getSheetMap(k);
			final ResourceHelper loh = losh.appendPartition(sheetMap, true);
			final JDFLayout lo = (JDFLayout) loh.getResource();
			final SetHelper nish = theHelper.getCreateSet(XJDFConstants.Resource, ElementName.NODEINFO, EnumUsage.Input);
			final ResourceHelper niph = nish.appendPartition(sheetMap, true);
			niph.getRoot().setAttribute("AmountGood", 1234, null);
			final SetHelper cush = theHelper.getCreateSet(XJDFConstants.Resource, ElementName.CUSTOMERINFO, EnumUsage.Input);
			final SetHelper cosh = theHelper.getCreateSet(XJDFConstants.Resource, ElementName.CONTACT, EnumUsage.Input);
			for (int i = 0; i < 4; i++)
			{
				final int ii = k * 4 + i;
				final ProductHelper ph = theHelper.appendProduct();
				ph.getRoot().setID("P" + ii);
				ph.setRoot();
				ph.setAmount(1000);
				if (ii >= 6)
					ph.getRoot().deleteNode();
				final JDFBinderySignature bs = (JDFBinderySignature) lo.appendElement(ElementName.BINDERYSIGNATURE);
				bs.setAttribute("ProductRef", "P" + (ii % 6));
				initBS(bs, ii);
				final JDFPosition pos = (JDFPosition) bs.appendElement(ElementName.POSITION);
				final double d = i;
				final double d2 = i + 1;
				pos.setRelativeBox(new JDFRectangle(d * 0.25, d * 0.25, d2 * 0.25, d2 * 0.25));

				if (ii < 6)
				{
					final ResourceHelper cuph = cush.appendPartition(new JDFAttributeMap("Product", "P" + ii), true);
					final ResourceHelper coph = cosh.appendPartition(new JDFAttributeMap("Product", "P" + ii), true);
					coph.getResource().setXMLComment("Contact, Address etc go here", true);
					coph.getResource().setAttribute("ContactTypes", "Customer");
					final JDFCustomerInfo ci = (JDFCustomerInfo) cuph.getResource();
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
	void testStripLayout_verbose()
	{
		for (int k = 0; k < 2; k++)
		{
			final JDFAttributeMap sheetMap = getSheetMap(1);
			sheetMap.put("Side", k == 0 ? "Front" : "Back");
			final ResourceHelper loh = losh.appendPartition(sheetMap, true);
			final JDFLayout lo = (JDFLayout) loh.getResource();

			for (int i = 0; i < 8; i++)
			{
				final JDFContentObject co = initContentObject(lo, k * 8 + i);
				co.appendElement("StripCellParams");
				co.appendElement("SignatureCell");
				co.setXMLComment("we would probably simply merge signaturecell and stripcellparams into each co", true);

			}
		}
		final ResourceHelper loh = losh.appendPartition(getSheetMap(1), true);
		final JDFLayout lo = (JDFLayout) loh.getResource();
		final JDFBinderySignature bs = (JDFBinderySignature) lo.appendElement(ElementName.BINDERYSIGNATURE);
		initBS(bs, 0);
		bs.removeChild("StripCellParams", null, 0);

		for (int i = 0; i < 2; i++)
		{
			final JDFPosition pos = (JDFPosition) bs.appendElement(ElementName.POSITION);
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
	private JDFAttributeMap getSheetMap(final int i)
	{
		final JDFAttributeMap map = new JDFAttributeMap();
		map.put("SheetName", "Sheet" + i);
		return map;
	}

	/**
	 * @see XJDFCreatorTest#setUp()
	 * @throws Exception
	 */
	@Override
	@BeforeEach
	void setUp() throws Exception
	{
		super.setUp();
		theHelper.getRoot().setXMLComment("create a stripping in a layout\n Stripping now consumes a layout", true);
		theHelper.setTypes("Imposition");
		theHelper.setJobID("j");

		losh = theHelper.getCreateSet(XJDFConstants.Resource, ElementName.LAYOUT, EnumUsage.Input);
		bssh = null;
	}

	/**
	 *
	 */
	@Test
	void testStripCell()
	{
		final JDFNode n = new JDFDoc(ElementName.JDF).getJDFRoot();
		n.setType(EnumType.Stripping);
		JDFStrippingParams sp = (JDFStrippingParams) n.appendMatchingResource(ElementName.STRIPPINGPARAMS, EnumProcessUsage.AnyInput, null);
		sp = (JDFStrippingParams) sp.addPartition(EnumPartIDKey.BinderySignatureName, "BS1");
		final JDFBinderySignature bs = (JDFBinderySignature) n.addResource(ElementName.BINDERYSIGNATURE, null);
		sp.refBinderySignature(bs);
		sp.appendPosition();
		final JDFStripCellParams scp = sp.appendStripCellParams();
		scp.setSpine(42);
		scp.setSides(EnumSides.TwoSidedHeadToFoot);

		final JDFToXJDF xjdf20 = new JDFToXJDF();

		final KElement xjdf = xjdf20.makeNewJDF(n, null);
		final JDFBinderySignature bsNew = (JDFBinderySignature) xjdf.getChildByTagName(ElementName.BINDERYSIGNATURE, null, 0, null, false, true);
		Assertions.assertEquals("TwoSidedHeadToFoot", bsNew.getSignatureCell(0).getAttribute(AttributeName.SIDES));
		Assertions.assertEquals(42, bsNew.getSignatureCell(0).getIntAttribute(XJDFConstants.TrimSpine, null, 0));

		final XJDFToJDFConverter xc = new XJDFToJDFConverter(null);
		final JDFDoc dJDF = xc.convert(xjdf);
		final JDFNode n2 = dJDF.getJDFRoot();
		n2.getResource(ElementName.STRIPPINGPARAMS, null, 0);

	}

	/**
	 *
	 */
	@Test
	void testSides()
	{
		final JDFNode n = new JDFDoc(ElementName.JDF).getJDFRoot();
		n.setType(EnumType.Stripping);
		JDFStrippingParams sp = (JDFStrippingParams) n.appendMatchingResource(ElementName.STRIPPINGPARAMS, EnumProcessUsage.AnyInput, null);
		sp = (JDFStrippingParams) sp.addPartition(EnumPartIDKey.BinderySignatureName, "BS1");
		final JDFBinderySignature bs = (JDFBinderySignature) n.addResource(ElementName.BINDERYSIGNATURE, null);
		sp.refBinderySignature(bs);
		sp.appendPosition();
		sp.setWorkStyle(EnumWorkStyle.Simplex);

		final JDFToXJDF xjdf20 = new JDFToXJDF();

		final KElement xjdf = xjdf20.makeNewJDF(n, null);
		final JDFBinderySignature bsNew = (JDFBinderySignature) xjdf.getChildByTagName(ElementName.BINDERYSIGNATURE, null, 0, null, false, true);
		Assertions.assertEquals("OneSided", bsNew.getSignatureCell(0).getAttribute(AttributeName.SIDES));

	}

	/**
	 *
	 */
	@Test
	void testAssemblyOnly()
	{
		theHelper.setTypes("Stripping");
		bssh = theHelper.getCreateSet(ElementName.BINDERYSIGNATURE, EnumUsage.Input);
		final SetHelper assh = theHelper.getCreateSet(ElementName.ASSEMBLY, EnumUsage.Input);
		final JDFAssembly assembly = (JDFAssembly) assh.getCreatePartition(0, true).getResource();
		assembly.setOrder(EnumOrder.Collecting);
		assembly.setAttribute(XJDFConstants.BinderySignatureIDs, "bs1 bs2 bs3");

		writeRoundTripX(theHelper, "onlyAssembly", EnumValidationLevel.Incomplete);
	}

	/**
	 *
	 */
	@Test
	void testBSOnly()
	{
		theHelper.setTypes("Stripping");
		bssh = theHelper.getCreateSet(ElementName.BINDERYSIGNATURE, EnumUsage.Input);

		final JDFBinderySignature bs1 = (JDFBinderySignature) bssh.getCreatePartition(new JDFAttributeMap(XJDFConstants.BinderySignatureID, "BS1"), true).getResource();
		bs1.setBinderySignatureType(EnumBinderySignatureType.Fold);
		losh.getCreatePartition(0, true).getResource().appendElement(ElementName.POSITION).setAttribute(XJDFConstants.BinderySignatureID, "BS1");

		writeRoundTripX(theHelper, "onlyBS", EnumValidationLevel.Incomplete);
	}

	/**
	 * @throws Exception
	 *
	 */
	@Test
	void testSigCell1() throws Exception
	{
		theHelper.setTypes("Stripping");
		bssh = theHelper.getCreateSet(ElementName.BINDERYSIGNATURE, EnumUsage.Input);

		final JDFBinderySignature bs1 = (JDFBinderySignature) bssh.getCreatePartition(new JDFAttributeMap(XJDFConstants.BinderySignatureID, "BS1"), true).getResource();
		bs1.setBinderySignatureType(EnumBinderySignatureType.Fold);
		final JDFSignatureCell sc = bs1.appendSignatureCell();
		sc.setFrontPages(new JDFIntegerList("0 1 2"));
		sc.setAttribute(AttributeName.BLEEDFACE, "42");
		sc.setOrientation(EnumOrientation.Down);
		sc.setAttribute(AttributeName.SIDES, "TwoSidedHeadToFoot");

		losh.getCreatePartition(0, true).getResource().appendElement(ElementName.POSITION).setAttribute(XJDFConstants.BinderySignatureID, "BS1");

		writeRoundTripX(theHelper, "sc1", EnumValidationLevel.Incomplete);
	}

	/**
	 *
	 */
	@Test
	void testBSRetain()
	{
		theHelper.setTypes("Stripping");
		bssh = theHelper.getCreateSet(ElementName.BINDERYSIGNATURE, EnumUsage.Input);

		final JDFBinderySignature bs1 = (JDFBinderySignature) bssh.getCreatePartition(new JDFAttributeMap(XJDFConstants.BinderySignatureID, "BS1"), true).getResource();
		bs1.setBinderySignatureType(EnumBinderySignatureType.Fold);
		losh.getCreatePartition(0, true).getResource().appendElement(ElementName.POSITION).setAttribute(XJDFConstants.BinderySignatureID, "BS1");
		final XJDFToJDFConverter jdfConverter = new XJDFToJDFConverter(null);
		final JDFDoc converted = jdfConverter.convert(theHelper);
		Assertions.assertNotNull(converted.getJDFRoot().getResource(ElementName.STRIPPINGPARAMS, EnumUsage.Input, 0).getLeaf(0).getElement(ElementName.BINDERYSIGNATURE));

	}

	/**
	 *
	 */
	@Test
	void testMultiPage1()
	{
		theHelper.setTypes("Stripping");
		theHelper.setJobID("2up");

		bssh = theHelper.getCreateSet(ElementName.BINDERYSIGNATURE, EnumUsage.Input);

		final ResourceHelper resBS = bssh.getCreatePartition(new JDFAttributeMap(XJDFConstants.BinderySignatureID, "BS1"), true);
		final JDFBinderySignature bs1 = (JDFBinderySignature) resBS.getResource();
		bs1.setBinderySignatureType(EnumBinderySignatureType.Fold);
		bs1.setFoldCatalog("F16-14");
		for (int i = 0; i < 2; i++)
		{
			final KElement mpf = bs1.appendElement(XJDFConstants.MultiPageFold);
			mpf.setAttribute(XJDFConstants.BinderySignatureID, "BS1");
			mpf.setAttribute(AttributeName.ORIENTATION, "Rotate0");
		}
		final JDFLayout lo = (JDFLayout) losh.getCreatePartition(0, true).getResource();
		lo.setSurfaceContentsBox((JDFRectangle) new JDFRectangle(0, 0, 100, 70).scaleFromCM());
		final JDFPosition position = (JDFPosition) lo.appendElement(ElementName.POSITION);
		position.setAttribute(XJDFConstants.BinderySignatureID, "BS1");
		theHelper.cleanUp();
		setSnippet(bssh, true);
		setSnippet(losh, true);
		writeTest(theHelper, "2upfold.xjdf");

	}

	/**
	 *
	 */
	@Test
	void testMultiPageComeGo()
	{
		theHelper.setTypes("Stripping");
		theHelper.setJobID("comeandgo");
		bssh = theHelper.getCreateSet(ElementName.BINDERYSIGNATURE, EnumUsage.Input);

		final ResourceHelper resBS = bssh.getCreatePartition(new JDFAttributeMap(XJDFConstants.BinderySignatureID, "BS1"), true);
		resBS.appendPartMap(new JDFAttributeMap(XJDFConstants.BinderySignatureID, "BS2"));
		final JDFBinderySignature bs1 = (JDFBinderySignature) resBS.getResource();
		bs1.setBinderySignatureType(EnumBinderySignatureType.Fold);
		bs1.setFoldCatalog("F16-14");
		final KElement mpf = bs1.appendElement(XJDFConstants.MultiPageFold);
		mpf.setAttribute(XJDFConstants.BinderySignatureID, "BS1");
		mpf.setAttribute(AttributeName.ORIENTATION, "Rotate0");
		final KElement mpf2 = bs1.appendElement(XJDFConstants.MultiPageFold);
		mpf2.setAttribute(XJDFConstants.BinderySignatureID, "BS2");
		mpf2.setAttribute(AttributeName.ORIENTATION, "Flip0");
		final JDFLayout lo = (JDFLayout) losh.getCreatePartition(0, true).getResource();
		lo.setSurfaceContentsBox((JDFRectangle) new JDFRectangle(0, 0, 100, 70).scaleFromCM());
		final JDFPosition position = (JDFPosition) lo.appendElement(ElementName.POSITION);
		position.setAttribute(XJDFConstants.BinderySignatureID, "BS1");
		theHelper.cleanUp();
		setSnippet(bssh, true);
		setSnippet(losh, true);
		writeTest(theHelper, "comego.xjdf");

	}

	/**
	 *
	 */
	@Test
	void testBSMulti()
	{
		theHelper.setTypes("Stripping");
		bssh = theHelper.getCreateSet(ElementName.BINDERYSIGNATURE, EnumUsage.Input);

		final ResourceHelper bsRes = bssh.getCreatePartition(new JDFAttributeMap(XJDFConstants.BinderySignatureID, "BS1"), true);
		bsRes.appendPartMap(new JDFAttributeMap(XJDFConstants.BinderySignatureID, "BS2"));
		final JDFBinderySignature bs1 = (JDFBinderySignature) bsRes.getResource();
		bs1.setBinderySignatureType(EnumBinderySignatureType.Fold);
		final KElement loRes = losh.getCreatePartition(0, true).getResource();
		loRes.appendElement(ElementName.POSITION).setAttribute(XJDFConstants.BinderySignatureID, "BS1");
		loRes.appendElement(ElementName.POSITION).setAttribute(XJDFConstants.BinderySignatureID, "BS2");
		final XJDFToJDFConverter jdfConverter = new XJDFToJDFConverter(null);
		final JDFDoc converted = jdfConverter.convert(theHelper);
		Assertions.assertNotNull(converted.getJDFRoot().getResource(ElementName.STRIPPINGPARAMS, EnumUsage.Input, 0).getLeaf(0).getElement(ElementName.BINDERYSIGNATURE));
		writeRoundTripX(theHelper, "multiBS", EnumValidationLevel.Incomplete);

	}

	/**
	 *
	 */
	@Test
	void testBSMultiAssembly()
	{
		theHelper.setTypes("Stripping");
		bssh = theHelper.getCreateSet(ElementName.BINDERYSIGNATURE, EnumUsage.Input);

		final ResourceHelper bsRes = bssh.getCreatePartition(new JDFAttributeMap(XJDFConstants.BinderySignatureID, "BS1"), true);
		bsRes.appendPartMap(new JDFAttributeMap(XJDFConstants.BinderySignatureID, "BS2"));
		final JDFBinderySignature bs1 = (JDFBinderySignature) bsRes.getResource();
		bs1.setBinderySignatureType(EnumBinderySignatureType.Fold);
		final KElement loRes = losh.getCreatePartition(0, true).getResource();
		loRes.appendElement(ElementName.POSITION).setAttribute(XJDFConstants.BinderySignatureID, "BS1");
		loRes.appendElement(ElementName.POSITION).setAttribute(XJDFConstants.BinderySignatureID, "BS2");

		final SetHelper assh = theHelper.getCreateSet(ElementName.ASSEMBLY, EnumUsage.Input);
		final JDFAssembly assembly = (JDFAssembly) assh.getCreatePartition(0, true).getResource();
		assembly.setOrder(EnumOrder.Collecting);
		assembly.setAttribute(XJDFConstants.BinderySignatureIDs, "BS1 BS2");

		final XJDFToJDFConverter jdfConverter = new XJDFToJDFConverter(null);
		final JDFDoc converted = jdfConverter.convert(theHelper);
		Assertions.assertNotNull(converted.getJDFRoot().getResource(ElementName.STRIPPINGPARAMS, EnumUsage.Input, 0).getLeaf(0).getElement(ElementName.BINDERYSIGNATURE));
		writeRoundTripX(theHelper, "multiBsAss", EnumValidationLevel.Incomplete);

	}

}
