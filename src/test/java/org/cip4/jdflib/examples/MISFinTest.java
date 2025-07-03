/*
 *
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
/*
 * JDFExampleDocTest.java
 *
 * @author muchadie
 */
package org.cip4.jdflib.examples;

import java.io.File;
import java.util.zip.DataFormatException;

import org.cip4.jdflib.auto.JDFAutoBoxFoldAction.EnumAction;
import org.cip4.jdflib.auto.JDFAutoBoxFoldingParams.EnumBoxFoldingType;
import org.cip4.jdflib.auto.JDFAutoBundle.EnumBundleType;
import org.cip4.jdflib.auto.JDFAutoSurfaceMark.EnumFace;
import org.cip4.jdflib.core.ElementName;
import org.cip4.jdflib.core.JDFDoc;
import org.cip4.jdflib.core.JDFElement.EnumVersion;
import org.cip4.jdflib.core.JDFResourceLink;
import org.cip4.jdflib.core.JDFResourceLink.EnumUsage;
import org.cip4.jdflib.core.KElement;
import org.cip4.jdflib.core.StringArray;
import org.cip4.jdflib.core.VString;
import org.cip4.jdflib.datatypes.JDFNumberList;
import org.cip4.jdflib.datatypes.JDFXYPair;
import org.cip4.jdflib.goldenticket.BaseGoldenTicketTest;
import org.cip4.jdflib.goldenticket.MISFinGoldenTicket;
import org.cip4.jdflib.node.JDFNode;
import org.cip4.jdflib.node.JDFNode.EnumType;
import org.cip4.jdflib.resource.JDFBundle;
import org.cip4.jdflib.resource.JDFBundleItem;
import org.cip4.jdflib.resource.JDFCuttingParams;
import org.cip4.jdflib.resource.JDFResource.EnumPartIDKey;
import org.cip4.jdflib.resource.JDFResource.EnumResStatus;
import org.cip4.jdflib.resource.JDFSurfaceMark;
import org.cip4.jdflib.resource.process.JDFBoxFoldAction;
import org.cip4.jdflib.resource.process.JDFBoxFoldingParams;
import org.cip4.jdflib.resource.process.JDFComponent;
import org.cip4.jdflib.resource.process.JDFCutBlock;
import org.junit.jupiter.api.Test;

/**
 * @author Dr. Rainer Prosi, Heidelberger Druckmaschinen AG
 *
 *         Apr 1, 2009
 */
class MISFinTest extends BaseGoldenTicketTest
{
	/**
	 * test MIS to Finishing ICS
	 *
	 *
	 */
	@Test
	void testAmount()
	{
		KElement.setLongID(false);
		final JDFDoc d = new JDFDoc("JDF");
		final JDFNode n = d.getJDFRoot();
		n.setType(EnumType.ProcessGroup);
		n.setTypes(new VString("Binding Stacking BoxPacking Palletizing", " "));
		final JDFComponent comp = (JDFComponent) n.addResource(ElementName.COMPONENT, null, EnumUsage.Output, null, null, null, null);
		final JDFResourceLink rl = n.getLink(comp, null);
		rl.setAmount(2, null);
		rl.setDescriptiveName("The link point to 2 pallets with a total comtent of 10000 brochures in 200 boxes");

		// create a pallet partition - may also use the root
		final JDFComponent compPallet = (JDFComponent) comp.addPartition(EnumPartIDKey.DeliveryUnit0, "Pallet");

		final JDFComponent compBox = (JDFComponent) compPallet.addPartition(EnumPartIDKey.DeliveryUnit1, "Box");
		final JDFBundle bundlePallet = compPallet.appendBundle();
		bundlePallet.setDescriptiveName("Bundle describing 100 boxes with 5000 Brochures Contents total");
		bundlePallet.setTotalAmount(5000);
		bundlePallet.setBundleType(EnumBundleType.Pallet);
		final JDFBundleItem biBoxes = bundlePallet.appendBundleItem();
		biBoxes.refElement(compBox);
		biBoxes.setAmount(100);

		final JDFComponent compBrochure = (JDFComponent) compBox.addPartition(EnumPartIDKey.DeliveryUnit2, "Brochure");
		final JDFBundle bundleBox = compBox.appendBundle();
		bundleBox.setDescriptiveName("Bundle describing 1 boxes with 50 Brochures Contents per box");
		bundleBox.setTotalAmount(50);
		bundleBox.setBundleType(EnumBundleType.Box);

		final JDFBundleItem biBrochures = bundleBox.appendBundleItem();
		biBrochures.refElement(compBrochure);
		biBrochures.setAmount(50);

		final JDFBundle bundleBrochure = compBrochure.appendBundle();
		bundleBrochure.setDescriptiveName("Dummy Bundle to inhibit inheritence of the box Bundle");
		d.write2File(sm_dirTestDataTemp + File.separator + "MISFinAmount.jdf", 2, false);
	}

	// ///////////////////////////////////////////////////////////////////////

	/**
	 *
	 */
	@Test
	void testAmountPalletteManifest()
	{
		KElement.setLongID(false);
		final JDFDoc d = new JDFDoc("JDF");
		final JDFNode n = d.getJDFRoot();
		n.setType(EnumType.ProcessGroup);
		n.setTypes(new VString("Binding Stacking BoxPacking Palletizing", " "));
		final JDFComponent comp = (JDFComponent) n.addResource(ElementName.COMPONENT, null, EnumUsage.Output, null, null, null, null);
		final JDFResourceLink rl = n.getLink(comp, null);
		rl.setAmount(2, null);
		rl.setDescriptiveName("The link point to 2 pallets with a total comtent of 10000 brochures in 200 boxes");
		final JDFComponent compBrochure = (JDFComponent) n.addResource(ElementName.COMPONENT, null, EnumUsage.Input, null, null, null, null);
		compBrochure.setResStatus(EnumResStatus.Available, true);
		compBrochure.setDescriptiveName("The individual Brochures");
		final JDFResourceLink rlB = n.getLink(compBrochure, null);
		rlB.setAmount(10000, null);
		rlB.setActualAmount(9700, null);

		for (int i = 0; i < 2; i++)
		{
			// create a pallet partition - may also use the root
			final JDFComponent compPallet = (JDFComponent) comp.addPartition(EnumPartIDKey.DeliveryUnit0, "Pallet" + i);
			compPallet.setProductID("Pallet_" + i);

			final JDFBundle bundlePallet = compPallet.getCreateBundle();
			final int nBox = i == 0 ? 100 : 94;
			bundlePallet.setDescriptiveName("Pallet Bundle describing " + nBox + " boxes with " + nBox * 50 + " Brochures Contents total");
			bundlePallet.setTotalAmount(nBox * 50);
			bundlePallet.setBundleType(EnumBundleType.Pallet);
			final JDFBundleItem biBoxes = bundlePallet.appendBundleItem();

			final JDFComponent compBox = (JDFComponent) compPallet.addPartition(EnumPartIDKey.DeliveryUnit1, "Box");
			biBoxes.refElement(compBox);
			biBoxes.setAmount(nBox);

			final JDFBundle bundleBox = compBox.appendBundle();
			bundleBox.setDescriptiveName("Bundle describing 1 boxes with 50 Brochures Contents per box");
			bundleBox.setTotalAmount(50);
			bundleBox.setBundleType(EnumBundleType.Box);

			final JDFBundleItem biBrochures = bundleBox.appendBundleItem();
			biBrochures.refElement(compBrochure);
			biBrochures.setAmount(50);
		}

		d.write2File(sm_dirTestDataTemp + File.separator + "MISFinAmountManifest.jdf", 2, false);
	}

	/**
	 *
	 */
	@Test
	void testStitchGB()
	{
		final MISFinGoldenTicket fgt = new MISFinGoldenTicket(2, EnumVersion.Version_1_3, 2, 2, null);
		fgt.setCategory(MISFinGoldenTicket.MISFIN_STITCHFIN);
		fgt.assign(null);
		fgt.bExpandGrayBox = false;
		write9GTFiles(fgt, "GBStitching", null);
	}

	/**
	 * tests the creation of the initial shapedefproduction (one up) process
	 * 
	 * @throws DataFormatException
	 * @throws Exception
	 */
	@Test
	void testBoxfold() throws DataFormatException
	{
		final JDFNode n = JDFNode.createRoot();
		n.setType("BoxFolding", false);
		final JDFBoxFoldingParams bfp = (JDFBoxFoldingParams) n.addResource(ElementName.BOXFOLDINGPARAMS, EnumUsage.Input);
		bfp.setBoxFoldingType(EnumBoxFoldingType.Type01);
		bfp.setBlankDimensionsX((JDFNumberList) new JDFNumberList("1 5 7 11 13").scaleFromCM(0));
		bfp.setBlankDimensionsY((JDFNumberList) new JDFNumberList("2 3 5 15 17 18 20").scaleFromCM(0));
		setSnippet(bfp, true);
		JDFBoxFoldAction bfa = bfp.appendBoxFoldAction();
		bfa.setFoldIndex(new JDFXYPair(0, -1));
		bfa.setAction(EnumAction.LongPreFoldLeftToRight);

		bfa = bfp.appendBoxFoldAction();
		bfa.setFoldIndex(new JDFXYPair(2, -1));
		bfa.setAction(EnumAction.LongPreFoldRightToLeft);

		bfa = bfp.appendBoxFoldAction();
		bfa.setFoldIndex(new JDFXYPair(1, -1));
		bfa.setAction(EnumAction.LongFoldLeftToRight);

		bfa = bfp.appendBoxFoldAction();
		bfa.setFoldIndex(new JDFXYPair(3, -1));
		bfa.setAction(EnumAction.LongFoldRightToLeft);

		writeTest(n, "resources/boxFoldingParams_boxFoldAction.jdf", true, "ResourceSet[@Name=\"BoxFoldingParams\"]");
	}

	/**
	 * tests the creation of the initial shapedefproduction (one up) process
	 * 
	 * @throws DataFormatException
	 * @throws Exception
	 */
	@Test
	void testCutMarks() throws DataFormatException
	{
		final JDFNode n = JDFNode.createRoot();
		n.setType("Cutting", false);
		n.setVersion(EnumVersion.Version_1_9);
		final JDFCuttingParams cp = (JDFCuttingParams) n.addResource(ElementName.CUTTINGPARAMS, EnumUsage.Input);
		final JDFCutBlock cb = cp.appendCutBlock();

		final StringArray snippets = new StringArray();

		final JDFComponent comp = (JDFComponent) n.addResource(ElementName.COMPONENT, EnumUsage.Input);
		comp.setDimensions((JDFXYPair) new JDFXYPair(50, 40).scaleFromCM(1));

		final JDFSurfaceMark sm = comp.getCreateSurfaceMark(EnumFace.Front);

		snippets.add("ResourceSet[@Name=\"CuttingParams\"]");
		snippets.add("ResourceSet[@Name=\"Component\"]");
		writeTestSnippets(n, "resources/cuttingParams_surfacemark.jdf", true, snippets);
	}

	/**
	 *
	 */
	@Test
	void testAmountPalletteCompleteManifest()
	{
		KElement.setLongID(false);
		final JDFDoc d = new JDFDoc("JDF");
		final JDFNode n = d.getJDFRoot();
		n.setType(EnumType.ProcessGroup);
		n.setTypes(new VString("Binding Stacking BoxPacking Palletizing", " "));
		final JDFComponent comp = (JDFComponent) n.addResource(ElementName.COMPONENT, null, EnumUsage.Output, null, null, null, null);
		final JDFResourceLink rl = n.getLink(comp, null);
		rl.setAmount(2, null);
		rl.setDescriptiveName("The link point to 2 pallets with a total comtent of 10000 brochures in 200 boxes");
		final JDFComponent compBrochure = (JDFComponent) n.addResource(ElementName.COMPONENT, null, EnumUsage.Input, null, null, null, null);
		compBrochure.setResStatus(EnumResStatus.Available, true);
		compBrochure.setDescriptiveName("The individual Brochures");
		final JDFResourceLink rlB = n.getLink(compBrochure, null);
		rlB.setAmount(10000, null);
		rlB.setActualAmount(9700, null);

		for (int i = 0; i < 2; i++)
		{
			// create a pallet partition - may also use the root
			final JDFComponent compPallet = (JDFComponent) comp.addPartition(EnumPartIDKey.DeliveryUnit0, "Pallet" + i);
			compPallet.setProductID("Pallet_" + i);

			final JDFBundle bundlePallet = compPallet.getCreateBundle();
			final int nBox = i == 0 ? 100 : 94;
			bundlePallet.setDescriptiveName("Pallet Bundle describing " + nBox + " boxes with " + nBox * 50 + " Brochures Contents total");
			bundlePallet.setTotalAmount(nBox * 50);
			bundlePallet.setBundleType(EnumBundleType.Pallet);

			for (int j = 0; j < nBox; j++)
			{
				final JDFBundleItem biBoxes = bundlePallet.appendBundleItem();
				final JDFComponent compBox = (JDFComponent) compPallet.addPartition(EnumPartIDKey.DeliveryUnit1, "Box_" + i + "_" + j);
				compBox.setProductID("Box_" + i + "_" + j);
				biBoxes.refElement(compBox);
				biBoxes.setAmount(1);

				final JDFBundle bundleBox = compBox.appendBundle();
				bundleBox.setDescriptiveName("Bundle describing box #" + j + " with 50 Brochures Contents per box");
				bundleBox.setTotalAmount(50);
				bundleBox.setBundleType(EnumBundleType.Box);

				final JDFBundleItem biBrochures = bundleBox.appendBundleItem();
				biBrochures.refElement(compBrochure);
				biBrochures.setAmount(50);
			}
		}

		d.write2File(sm_dirTestDataTemp + File.separator + "MISFinAmountCompleteManifest.jdf", 2, false);
	}

	// ///////////////////////////////////////////////////////////////////////
}
