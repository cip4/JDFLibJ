/*
 * The CIP4 Software License, Version 1.0
 *
 *
 * Copyright (c) 2001-2010 The International Cooperation for the Integration of
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

import org.cip4.jdflib.auto.JDFAutoConventionalPrintingParams.EnumWorkStyle;
import org.cip4.jdflib.auto.JDFAutoMedia.EnumMediaType;
import org.cip4.jdflib.core.AttributeName;
import org.cip4.jdflib.core.ElementName;
import org.cip4.jdflib.core.JDFElement.EnumNodeStatus;
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
	//TODO do we need an explicit incremental flag in the JDF proper and or sets

	/**
	 * 
	 */
	@Test
	public void testAddSheet1()
	{
		theHelper.getRoot().setXMLComment("Assume incremental adding of an additional 3rd plate");
		theHelper.getRoot().setAttribute("Types", "PlateMaking");
		SetHelper rlh = theHelper.getCreateResourceSet("RunList", EnumUsage.Input);
		ResourceHelper p = rlh.getCreatePartition(null, true);
		JDFRunList rl = (JDFRunList) p.getCreateResource();
		rl.setNPage(48);
		rlh.getSet().setXMLComment("set the updated total number of pages");

		SetHelper loh = theHelper.getCreateResourceSet("Layout", EnumUsage.Input);
		p = loh.getCreatePartition(new JDFAttributeMap("SheetName", "S3"), true);
		JDFLayout lo = (JDFLayout) p.getCreateResource();
		JDFBinderySignature bs = (JDFBinderySignature) lo.appendElement(ElementName.BINDERYSIGNATURE);
		bs.setNumberUp(new JDFXYPair(4, 4));
		loh.getSet().setXMLComment("only specify the 3rd sheet");

		SetHelper mh = theHelper.getCreateResourceSet("Media", EnumUsage.Input);
		p = mh.getCreatePartition(null, true);
		KElement mPart = p.getPartition();
		mPart.setAttribute("ProductID", "PlateID");

		SetHelper xmh = theHelper.getCreateResourceSet("ExposedMedia", EnumUsage.Output);
		p = xmh.getCreatePartition(new JDFAttributeMap("SheetName", "S3"), true);
		JDFExposedMedia xm = (JDFExposedMedia) p.getCreateResource();
		xm.setAttribute("MediaRef", mPart.getAttribute("ID"));

		theHelper.getRoot().getOwnerDocument_KElement().write2File(sm_dirTestDataTemp + "changeSheet1.xjdf", 2, false);
	}

	/**
	 * 
	 */
	@Test
	public void testAddProcess()
	{
		theHelper.getRoot().setXMLComment("Added Varnishing - how do we differentiate varnishing only from add varnishing\n");
		theHelper.getRoot().setAttribute("Types", "Varnishing");
		SetHelper rlh = theHelper.getCreateResourceSet(ElementName.VARNISHINGPARAMS, EnumUsage.Input);
		ResourceHelper p = rlh.getCreatePartition(new JDFAttributeMap("SheetName", "S3"), true);
		//		JDFVarnishingParams vp = (JDFRunList) p.getCreateResource();
		//		rl.setNPage(48);
	}

	/**
	 * 
	 */
	@Test
	public void testProductOnly()
	{
		theHelper.getRoot().setXMLComment("Product description only\n");
		//h.getRoot().setAttribute("Types", "Varnishing");
		ProductHelper ph = theHelper.appendProduct();
		ph.setRoot();
		ph.setAmount(4200);

		ProductHelper phCover = theHelper.appendProduct();
		ph.setChild(phCover, 1);
		phCover.getProduct().setAttribute("DescriptiveName", "Cover");
		IntentHelper coverMedia = phCover.getCreateIntent("MediaIntent");
		coverMedia.setSpan("Weight", "150", "NumberSpan");

		ProductHelper phBody = theHelper.appendProduct();
		ph.setChild(phBody, 1);
		phBody.getProduct().setAttribute("DescriptiveName", "Body");
		IntentHelper bodyMedia = phBody.getCreateIntent("MediaIntent");
		bodyMedia.setSpan("Weight", "100", "NumberSpan");

		IntentHelper binding = ph.getCreateIntent("BindingIntent");
		binding.setSpan("BindingType", EnumSpanBindingType.SaddleStitch.getName(), "EnumerationSpan");
		binding.setSpan("CoverRef", phCover.getProduct().getID(), null);
		IntentHelper layout = ph.getCreateIntent("LayoutIntent");
		layout.setSpan("Dimensions", "700 1000", "XYPairSpan");

		theHelper.writeToFile(sm_dirTestDataTemp + "product1.xjdf");
	}

	/**
	 * 
	 */
	@Test
	public void testRemoveSheet()
	{
		theHelper.getRoot().setXMLComment("Assume incremental removal of an existing 3rd plate");
		theHelper.getRoot().setAttribute("Types", "PlateMaking");
		SetHelper rlh = theHelper.getCreateResourceSet("RunList", EnumUsage.Input);
		ResourceHelper p = rlh.getCreatePartition(null, true);
		JDFRunList rl = (JDFRunList) p.getCreateResource();
		rl.setNPage(32);
		rlh.getSet().setXMLComment("set the updated reduced total number of pages");

		SetHelper nih = theHelper.getCreateResourceSet("NodeInfo", EnumUsage.Input);
		p = nih.getCreatePartition(new JDFAttributeMap("SheetName", "S3"), true);
		JDFNodeInfo ni = (JDFNodeInfo) p.getCreateResource();
		ni.setNodeStatus(EnumNodeStatus.Aborted);
		ni.setNodeStatusDetails("Removed");
		nih.getSet().setXMLComment("All other dependent resources must be appropriately modified by the device");

		theHelper.writeToFile(sm_dirTestDataTemp + "removeSheet1.xjdf");
	}

	/**
	 * 
	 */
	@Test
	public void testUpdateAmount()
	{
		theHelper.getRoot().setXMLComment("update the required amount for sheet=s3");
		theHelper.getRoot().setAttribute("Types", "ConventionalPrinting");

		theHelper.removeSet(ElementName.NODEINFO);
		SetHelper nih = theHelper.getCreateResourceSet(ElementName.NODEINFO, EnumUsage.Input);
		ResourceHelper p = nih.getCreatePartition(new JDFAttributeMap("SheetName", "S3"), true);
		JDFNodeInfo ni = (JDFNodeInfo) p.getCreateResource();
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
		SetHelper nih = theHelper.appendResourceSet("NodeInfo", null);
		nih.setUsage(EnumUsage.Input);
		JDFAttributeMap sheetMap = new JDFAttributeMap("SheetName", "S1");
		ResourceHelper niS1 = nih.getCreatePartition(sheetMap, true);
		KElement ni = niS1.getResource();
		ni.setAttribute("Amount", "5000");

		SetHelper cpSetHelper = theHelper.appendResourceSet(ElementName.CONVENTIONALPRINTINGPARAMS, null);
		cpSetHelper.setUsage(EnumUsage.Input);
		cpSetHelper.getCreatePartition(sheetMap, true).getResource().setAttribute(AttributeName.WORKSTYLE, EnumWorkStyle.Perfecting.getName());

		SetHelper mediaSetHelper = theHelper.appendResourceSet(ElementName.MEDIA, null);
		mediaSetHelper.setUsage(EnumUsage.Input);
		ResourceHelper mediaHelper = mediaSetHelper.getCreatePartition(sheetMap, true);
		KElement mediaPart = mediaHelper.getPartition();
		mediaPart.setAttribute("Brand", "TestBrand");
		mediaPart.setAttribute("ProductID", "ID");
		KElement media = mediaHelper.getResource();
		media.setAttribute("Dimension", new JDFXYPair(72, 49).scaleFromCM().toString(), null);
		media.setAttribute(AttributeName.MEDIATYPE, EnumMediaType.Paper.getName());

		SetHelper compSetHelper = theHelper.appendResourceSet(ElementName.COMPONENT, null);
		compSetHelper.setUsage(EnumUsage.Output);
		ResourceHelper compHelper = compSetHelper.getCreatePartition(sheetMap, true);
		KElement compPart = compHelper.getPartition();
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
		MISCPGoldenTicket gt = new MISCPGoldenTicket(2, EnumVersion.Version_1_4, 2, 2, true, null);
		gt.assign(null);
		JDFNode n = gt.getNode();
		n.getOwnerDocument_JDFElement().write2File(sm_dirTestDataTemp + "cpGoldenTicket.jdf", 2, false);
		XJDF20 jdfToXJD = new XJDF20();
		jdfToXJD.setMergeLayout(true);
		KElement xjdf = jdfToXJD.makeNewJDF(n, null);
		XMLDoc d = xjdf.getOwnerDocument_KElement();
		d.write2File(sm_dirTestDataTemp + "cpGoldenTicket.xjdf", 2, false);
	}
}
