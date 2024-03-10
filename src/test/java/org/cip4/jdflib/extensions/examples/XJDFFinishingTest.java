/**
 * The CIP4 Software License, Version 1.0
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
package org.cip4.jdflib.extensions.examples;

import org.cip4.jdflib.JDFTestCaseBase;
import org.cip4.jdflib.core.AttributeName;
import org.cip4.jdflib.core.ElementName;
import org.cip4.jdflib.core.JDFElement.EnumVersion;
import org.cip4.jdflib.core.JDFResourceLink.EnumUsage;
import org.cip4.jdflib.core.KElement;
import org.cip4.jdflib.datatypes.JDFAttributeMap;
import org.cip4.jdflib.datatypes.JDFIntegerList;
import org.cip4.jdflib.datatypes.JDFRectangle;
import org.cip4.jdflib.datatypes.JDFXYPair;
import org.cip4.jdflib.extensions.ProductHelper;
import org.cip4.jdflib.extensions.ResourceHelper;
import org.cip4.jdflib.extensions.SetHelper;
import org.cip4.jdflib.extensions.XJDFConstants;
import org.cip4.jdflib.extensions.XJDFHelper;
import org.cip4.jdflib.node.JDFNode.EnumType;
import org.cip4.jdflib.resource.JDFBundle;
import org.cip4.jdflib.resource.JDFBundleItem;
import org.cip4.jdflib.resource.JDFCuttingParams;
import org.cip4.jdflib.resource.process.JDFComponent;
import org.cip4.jdflib.resource.process.JDFCutBlock;
import org.cip4.jdflib.resource.process.JDFIdentificationField;
import org.cip4.jdflib.resource.process.JDFMetadataMap;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**
 *
 * @author rainer prosi
 *
 */
public class XJDFFinishingTest extends JDFTestCaseBase
{
	/**
	 *
	 */
	@Test
	void testBundlePallet()
	{
		final XJDFHelper xjdfHelper = new XJDFHelper("Bundle", null, null);
		xjdfHelper.setTypes(EnumType.Palletizing.getName());
		xjdfHelper.setVersion(EnumVersion.Version_2_1);
		final ProductHelper book = xjdfHelper.getCreateRootProduct(0);
		book.setID("BookProductID");
		book.setExternalID("BookExternalID");
		book.setAmount(4200);

		final SetHelper shBook = xjdfHelper.getCreateSet(XJDFConstants.Resource, ElementName.COMPONENT, EnumUsage.Input);
		final ResourceHelper bookHelper = shBook.getCreatePartition(XJDFConstants.Product, "BookExternalID", true);
		bookHelper.setID("BookComponentID");
		bookHelper.setAttribute(AttributeName.GROSSWEIGHT, "" + 650);
		bookHelper.setAmount(4200, null, true);

		final SetHelper shPallet = xjdfHelper.getCreateSet(XJDFConstants.Resource, ElementName.COMPONENT, EnumUsage.Output);
		final ResourceHelper palletHelper = shPallet.getCreatePartition(null, true);
		palletHelper.setAmount(10, null, true);
		palletHelper.setAttribute(AttributeName.GROSSWEIGHT, "" + (20 * 1000 + 10 * 300 + 42 * 650));

		final SetHelper shBundle = xjdfHelper.getCreateSet(XJDFConstants.Resource, ElementName.BUNDLE, EnumUsage.Input);
		final ResourceHelper rh = shBundle.appendPartition(null, true);
		final JDFBundle b = (JDFBundle) rh.getResource();
		final JDFBundleItem pallet = b.appendBundleItem();
		pallet.setAttribute(AttributeName.BUNDLETYPE, "Pallet");
		pallet.setAmount(10);
		pallet.setAttribute(AttributeName.TOTALAMOUNT, "4200");
		final JDFBundleItem box = (JDFBundleItem) pallet.appendElement(ElementName.BUNDLEITEM);
		box.setAttribute(AttributeName.BUNDLETYPE, "Carton");
		box.setAmount(10);
		box.setAttribute(XJDFConstants.ItemRef, "BookProductID");
		box.setAttribute(AttributeName.TOTALAMOUNT, "420");

		cleanSnippets(xjdfHelper);
		writeTest(xjdfHelper, "resources/PalletBundle.xjdf");
	}

	/**
	 *
	 */
	@Test
	void testNestedCutBlock()
	{
		final XJDFHelper xjdfHelper = new XJDFHelper("Bundle", null, null);
		xjdfHelper.addType(EnumType.Cutting);
		xjdfHelper.addType(EnumType.Cutting);

		final SetHelper shCut1 = xjdfHelper.getCreateSet(XJDFConstants.Resource, ElementName.CUTTINGPARAMS, EnumUsage.Input);
		shCut1.setCombinedProcessIndex(new JDFIntegerList(0));
		final ResourceHelper rhcut1 = shCut1.getCreatePartition(0, true);
		final JDFCuttingParams cp1 = (JDFCuttingParams) rhcut1.getResource();
		final JDFCutBlock cb1 = cp1.appendCutBlock();
		cb1.setBlockName("B1");
		cb1.setAttribute(AttributeName.BOX, new JDFRectangle(0, 0, 400, 600), null);
		final JDFCutBlock cb2 = cp1.appendCutBlock();
		cb2.setBlockName("B2");
		cb2.setAttribute(AttributeName.BOX, new JDFRectangle(400, 0, 1200, 600), null);

		final SetHelper shCut2 = xjdfHelper.appendSet(XJDFConstants.Resource, ElementName.CUTTINGPARAMS, EnumUsage.Input);
		shCut2.setCombinedProcessIndex(new JDFIntegerList(1));

		final ResourceHelper rhcut21 = shCut2.getCreatePartition(0, true);
		rhcut21.setPartMap(new JDFAttributeMap(AttributeName.BLOCKNAME, "B1"));
		final JDFCuttingParams cp21 = (JDFCuttingParams) rhcut21.getResource();
		final JDFCutBlock cb211 = cp21.appendCutBlock();
		cb211.setBlockName("B1.1");
		cb211.setAttribute(AttributeName.BOX, new JDFRectangle(0, 0, 400, 300), null);
		final JDFCutBlock cb212 = cp21.appendCutBlock();
		cb212.setBlockName("B1.2");
		cb212.setAttribute(AttributeName.BOX, new JDFRectangle(0, 300, 400, 600), null);

		final ResourceHelper rhcut22 = shCut2.getCreatePartition(1, true);
		rhcut22.setPartMap(new JDFAttributeMap(AttributeName.BLOCKNAME, "B2"));
		final JDFCuttingParams cp22 = (JDFCuttingParams) rhcut22.getResource();
		final JDFCutBlock cb221 = cp22.appendCutBlock();
		cb221.setBlockName("B2.1");
		cb221.setAttribute(AttributeName.BOX, new JDFRectangle(0, 0, 800, 300), null);
		final JDFCutBlock cb222 = cp22.appendCutBlock();
		cb222.setBlockName("B2.2");
		cb222.setAttribute(AttributeName.BOX, new JDFRectangle(0, 300, 800, 600), null);

		final SetHelper shCompIn = xjdfHelper.getCreateSet(XJDFConstants.Resource, ElementName.COMPONENT, EnumUsage.Input);
		final ResourceHelper rhcompIn = shCompIn.getCreatePartition(0, true);
		JDFComponent c = (JDFComponent) rhcompIn.getCreateResource();
		c.setDimensions(new JDFXYPair(1200, 600));

		final SetHelper shCompOut = xjdfHelper.getCreateSet(XJDFConstants.Resource, ElementName.COMPONENT, EnumUsage.Output);
		ResourceHelper rhcompOut = shCompOut.getCreatePartition(new JDFAttributeMap(AttributeName.BLOCKNAME, "B1.1"), true);
		c = (JDFComponent) rhcompOut.getCreateResource();
		c.setDimensions(new JDFXYPair(400, 300));

		rhcompOut = shCompOut.getCreatePartition(new JDFAttributeMap(AttributeName.BLOCKNAME, "B1.2"), true);
		c = (JDFComponent) rhcompOut.getCreateResource();
		c.setDimensions(new JDFXYPair(400, 300));

		rhcompOut = shCompOut.getCreatePartition(new JDFAttributeMap(AttributeName.BLOCKNAME, "B2.1"), true);
		c = (JDFComponent) rhcompOut.getCreateResource();
		c.setDimensions(new JDFXYPair(800, 300));

		rhcompOut = shCompOut.getCreatePartition(new JDFAttributeMap(AttributeName.BLOCKNAME, "B2.2"), true);
		c = (JDFComponent) rhcompOut.getCreateResource();
		c.setDimensions(new JDFXYPair(800, 300));

		cleanSnippets(xjdfHelper);
		writeTest(xjdfHelper, "resources/NestedCutBlock.xjdf");
	}

	/**
	 *
	 */
	@Test
	void testMetaDataMapBarcode()
	{
		final XJDFHelper xjdfHelper = new XJDFHelper("Barcode", "Metadata", null);
		xjdfHelper.setTypes(EnumType.Verification.getName());

		final SetHelper shComp = xjdfHelper.getCreateSet(XJDFConstants.Resource, ElementName.COMPONENT, EnumUsage.Input);
		final ResourceHelper rh = shComp.getCreatePartition(0, true);
		final JDFIdentificationField idf = (JDFIdentificationField) rh.getCreateResource().appendElement(ElementName.IDENTIFICATIONFIELD);
		idf.setValueFormat("%6s%3i%2i");
		idf.setValueTemplate("job doc sheet");
		final JDFMetadataMap md0 = (JDFMetadataMap) idf.appendElement(ElementName.METADATAMAP);
		md0.setName("JobID");
		md0.setValueFormat("Job_%s");
		md0.setValueTemplate("job");
		final JDFMetadataMap md2 = (JDFMetadataMap) idf.appendElement(ElementName.METADATAMAP);
		md2.setName("DocIndex");
		md2.setValueFormat("%i %i");
		md2.setValueTemplate("doc doc");
		final JDFMetadataMap md1 = (JDFMetadataMap) idf.appendElement(ElementName.METADATAMAP);
		md1.setName("SheetIndex");
		md1.setValueFormat("%i %i");
		md1.setValueTemplate("sheet sheet");
		cleanSnippets(xjdfHelper);
		writeTest(xjdfHelper, "subelements/barcodeMetaDataMap.xjdf");
	}

	/**
	 * @see JDFTestCaseBase#setUp()
	 */
	@Override
	@BeforeEach
	public void setUp() throws Exception
	{
		super.setUp();
		KElement.setLongID(false);
	}
}
