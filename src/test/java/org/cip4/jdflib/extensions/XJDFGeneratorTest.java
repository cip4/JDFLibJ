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

import org.cip4.jdflib.auto.JDFAutoConventionalPrintingParams.EnumWorkStyle;
import org.cip4.jdflib.auto.JDFAutoMedia.EnumMediaType;
import org.cip4.jdflib.core.AttributeName;
import org.cip4.jdflib.core.ElementName;
import org.cip4.jdflib.core.JDFConstants;
import org.cip4.jdflib.core.JDFElement.EnumNodeStatus;
import org.cip4.jdflib.core.JDFElement.EnumValidationLevel;
import org.cip4.jdflib.core.JDFElement.EnumVersion;
import org.cip4.jdflib.core.JDFNodeInfo;
import org.cip4.jdflib.core.JDFResourceLink.EnumUsage;
import org.cip4.jdflib.core.KElement;
import org.cip4.jdflib.core.XMLDoc;
import org.cip4.jdflib.datatypes.JDFAttributeMap;
import org.cip4.jdflib.datatypes.JDFXYPair;
import org.cip4.jdflib.goldenticket.MISCPGoldenTicket;
import org.cip4.jdflib.node.JDFNode;
import org.cip4.jdflib.resource.process.JDFBinderySignature;
import org.cip4.jdflib.resource.process.JDFExposedMedia;
import org.cip4.jdflib.resource.process.JDFLayout;
import org.cip4.jdflib.resource.process.JDFRunList;
import org.cip4.jdflib.span.JDFSpanBindingType.EnumSpanBindingType;
import org.junit.Test;

/**
 * @author Rainer Prosi, Heidelberger Druckmaschinen
 *
 */
public class XJDFGeneratorTest extends XJDFCreatorTest
{
	// TODO do we need an explicit incremental flag in the JDF proper and or sets

	/**
	 *
	 */
	@Test
	public void testAddSheet1()
	{
		theHelper.getRoot().setXMLComment("Assume incremental adding of an additional 3rd plate", true);
		theHelper.getRoot().setAttribute("Types", "PlateMaking");
		final SetHelper rlh = theHelper.getCreateSet(XJDFConstants.Resource, "RunList", EnumUsage.Input);
		ResourceHelper p = rlh.getCreatePartition(null, true);
		final JDFRunList rl = (JDFRunList) p.getCreateResource();
		rl.setNPage(48);
		rlh.getSet().setXMLComment("set the updated total number of pages", true);

		final SetHelper loh = theHelper.getCreateSet(XJDFConstants.Resource, "Layout", EnumUsage.Input);
		p = loh.getCreatePartition(new JDFAttributeMap("SheetName", "S3"), true);
		final JDFLayout lo = (JDFLayout) p.getCreateResource();
		final JDFBinderySignature bs = (JDFBinderySignature) lo.appendElement(ElementName.BINDERYSIGNATURE);
		bs.setNumberUp(new JDFXYPair(4, 4));
		loh.getSet().setXMLComment("only specify the 3rd sheet", true);

		final SetHelper mh = theHelper.getCreateSet(XJDFConstants.Resource, "Media", EnumUsage.Input);
		p = mh.getCreatePartition(null, true);
		final KElement mPart = p.getPartition();
		mPart.setAttribute("ProductID", "PlateID");

		final SetHelper xmh = theHelper.getCreateSet(XJDFConstants.Resource, "ExposedMedia", EnumUsage.Output);
		p = xmh.getCreatePartition(new JDFAttributeMap("SheetName", "S3"), true);
		final JDFExposedMedia xm = (JDFExposedMedia) p.getCreateResource();
		xm.setAttribute("MediaRef", mPart.getAttribute("ID"));

		theHelper.getRoot().getOwnerDocument_KElement().write2File(sm_dirTestDataTemp + "changeSheet1.xjdf", 2, false);
	}

	/**
	 *
	 */
	@Test
	public void testAddProcess()
	{
		theHelper.getRoot().setXMLComment("Added Varnishing - how do we differentiate varnishing only from add varnishing\n", true);
		theHelper.getRoot().setAttribute("Types", "Varnishing");
		final SetHelper rlh = theHelper.getCreateSet(XJDFConstants.Resource, ElementName.VARNISHINGPARAMS, EnumUsage.Input);
		final ResourceHelper p = rlh.getCreatePartition(new JDFAttributeMap("SheetName", "S3"), true);
		// JDFVarnishingParams vp = (JDFRunList) p.getCreateResource();
		// rl.setNPage(48);
	}

	/**
	 *
	 */
	@Test
	public void testProductOnly()
	{
		theHelper.getRoot().setXMLComment("Product description only\n", true);
		theHelper.setJobID("j");
		theHelper.setTypes(JDFConstants.PRODUCT);
		final ProductHelper ph = theHelper.appendProduct();
		ph.setRoot();
		ph.setAmount(4200);

		final ProductHelper phCover = theHelper.appendProduct();
		ph.setChild(phCover);
		phCover.getProduct().setAttribute("DescriptiveName", "Cover");
		final IntentHelper coverMedia = phCover.getCreateIntent("MediaIntent");
		coverMedia.setSpan("Weight", "150", "NumberSpan");
		coverMedia.setSpan(AttributeName.MEDIATYPE, "Paper", null);

		final ProductHelper phBody = theHelper.appendProduct();
		ph.setChild(phBody);
		phBody.getProduct().setAttribute("DescriptiveName", "Body");
		final IntentHelper bodyMedia = phBody.getCreateIntent("MediaIntent");
		bodyMedia.setSpan("Weight", "100", "NumberSpan");
		bodyMedia.setSpan(AttributeName.MEDIATYPE, "Paper", null);

		final IntentHelper binding = ph.getCreateIntent("BindingIntent");
		binding.setSpan("BindingType", EnumSpanBindingType.SaddleStitch.getName(), "EnumerationSpan");
		binding.setSpan("ChildRefs", phCover.getProduct().getID(), null);
		final IntentHelper layout = ph.getCreateIntent("LayoutIntent");
		layout.setSpan("Dimensions", "700 1000", "XYPairSpan");
		theHelper.cleanUp();
		writeRoundTripX(theHelper, "product1", EnumValidationLevel.Incomplete);
	}

	/**
	 *
	 */
	@Test
	public void testRemoveSheet()
	{
		theHelper.getRoot().setXMLComment("Assume incremental removal of an existing 3rd plate", true);
		theHelper.getRoot().setAttribute("Types", "PlateMaking");
		final SetHelper rlh = theHelper.getCreateSet(XJDFConstants.Resource, "RunList", EnumUsage.Input);
		ResourceHelper p = rlh.getCreatePartition(null, true);
		final JDFRunList rl = (JDFRunList) p.getCreateResource();
		rl.setNPage(32);
		rlh.getSet().setXMLComment("set the updated reduced total number of pages", true);

		final SetHelper nih = theHelper.getCreateSet(XJDFConstants.Resource, "NodeInfo", EnumUsage.Input);
		p = nih.getCreatePartition(new JDFAttributeMap("SheetName", "S3"), true);
		final JDFNodeInfo ni = (JDFNodeInfo) p.getCreateResource();
		ni.setNodeStatus(EnumNodeStatus.Aborted);
		ni.setNodeStatusDetails("Removed");
		nih.getSet().setXMLComment("All other dependent resources must be appropriately modified by the device", true);

		theHelper.writeToFile(sm_dirTestDataTemp + "removeSheet1.xjdf");
	}

	/**
	 *
	 */
	@Test
	public void testUpdateAmount()
	{
		theHelper.getRoot().setXMLComment("update the required amount for sheet=s3", true);
		theHelper.getRoot().setAttribute("Types", "ConventionalPrinting");

		theHelper.removeSet(ElementName.NODEINFO);
		final SetHelper nih = theHelper.getCreateSet(XJDFConstants.Resource, ElementName.NODEINFO, EnumUsage.Input);
		final ResourceHelper p = nih.getCreatePartition(new JDFAttributeMap("SheetName", "S3"), true);
		final JDFNodeInfo ni = (JDFNodeInfo) p.getCreateResource();
		ni.setAttribute("AmountGood", 10000, null);

		theHelper.getRoot().getOwnerDocument_KElement().write2File(sm_dirTestDataTemp + "updateAmount.xjdf", 2, false);
	}

	/**
	 *
	 *
	 */
	@Test
	public void testCPFromScratch()
	{
		theHelper = new XJDFHelper(null);
		theXJDF = theHelper.getRoot();
		theXJDF.setAttribute("Types", "InkZoneCalculation ConventionalPrinting");
		final SetHelper nih = theHelper.appendResourceSet("NodeInfo", null);
		nih.setUsage(EnumUsage.Input);
		final JDFAttributeMap sheetMap = new JDFAttributeMap("SheetName", "S1");
		final ResourceHelper niS1 = nih.getCreatePartition(sheetMap, true);
		final KElement ni = niS1.getResource();
		ni.setAttribute("Amount", "5000");

		final SetHelper cpSetHelper = theHelper.appendResourceSet(ElementName.CONVENTIONALPRINTINGPARAMS, null);
		cpSetHelper.setUsage(EnumUsage.Input);
		cpSetHelper.getCreatePartition(sheetMap, true).getResource().setAttribute(AttributeName.WORKSTYLE, EnumWorkStyle.Perfecting.getName());

		final SetHelper mediaSetHelper = theHelper.appendResourceSet(ElementName.MEDIA, null);
		mediaSetHelper.setUsage(EnumUsage.Input);
		final ResourceHelper mediaHelper = mediaSetHelper.getCreatePartition(sheetMap, true);
		final KElement mediaPart = mediaHelper.getPartition();
		mediaPart.setAttribute("Brand", "TestBrand");
		mediaPart.setAttribute("ProductID", "ID");
		final KElement media = mediaHelper.getResource();
		media.setAttribute("Dimension", new JDFXYPair(72, 49).scaleFromCM(1).toString(), null);
		media.setAttribute(AttributeName.MEDIATYPE, EnumMediaType.Paper.getName());

		final SetHelper compSetHelper = theHelper.appendResourceSet(ElementName.COMPONENT, null);
		compSetHelper.setUsage(EnumUsage.Output);
		final ResourceHelper compHelper = compSetHelper.getCreatePartition(sheetMap, true);
		final KElement compPart = compHelper.getPartition();
		compPart.setAttribute("ProductID", "ComponentID");
		compHelper.getResource().setAttribute("MediaRef", mediaPart.getAttribute("ID"));

		theXJDF.getOwnerDocument_KElement().write2File(sm_dirTestDataTemp + "cpFromScratch.jdf", 2, false);
	}

	/**
	 *
	 *
	 */
	@Test
	public void testFromCPGoldenTicket()
	{
		final MISCPGoldenTicket gt = new MISCPGoldenTicket(2, EnumVersion.Version_1_4, 2, 2, true, null);
		gt.assign(null);
		final JDFNode n = gt.getNode();
		n.getOwnerDocument_JDFElement().write2File(sm_dirTestDataTemp + "cpGoldenTicket.jdf", 2, false);
		final XJDF20 jdfToXJD = new XJDF20();
		jdfToXJD.setMergeLayout(true);
		final KElement xjdf = jdfToXJD.makeNewJDF(n, null);
		final XMLDoc d = xjdf.getOwnerDocument_KElement();
		d.write2File(sm_dirTestDataTemp + "cpGoldenTicket.xjdf", 2, false);
	}
}
