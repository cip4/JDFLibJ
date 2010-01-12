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

import org.cip4.jdflib.JDFTestCaseBase;
import org.cip4.jdflib.core.ElementName;
import org.cip4.jdflib.core.JDFNodeInfo;
import org.cip4.jdflib.core.KElement;
import org.cip4.jdflib.core.JDFElement.EnumNodeStatus;
import org.cip4.jdflib.core.JDFResourceLink.EnumUsage;
import org.cip4.jdflib.datatypes.JDFAttributeMap;
import org.cip4.jdflib.datatypes.JDFXYPair;
import org.cip4.jdflib.resource.process.JDFBinderySignature;
import org.cip4.jdflib.resource.process.JDFExposedMedia;
import org.cip4.jdflib.resource.process.JDFLayout;
import org.cip4.jdflib.resource.process.JDFRunList;
import org.cip4.jdflib.span.JDFSpanBindingType.EnumSpanBindingType;

/**
 * @author Rainer Prosi, Heidelberger Druckmaschinen
 * 
 */
public class XJDFGeneratorTest extends JDFTestCaseBase
{
	//TODO do we need an explicit incremental flag in the JDF proper and or sets
	XJDFHelper h;

	/**
	* 
	*/
	@Override
	public void setUp() throws Exception
	{
		KElement.setLongID(false);
		super.setUp();
		h = new XJDFHelper("jobID", "jobPartID", null);
	}

	/**
	 * 
	 */
	public void testAddSheet1()
	{
		h.getRoot().setXMLComment("Assume incremental adding of an additional 3rd plate");
		h.getRoot().setAttribute("Types", "PlateMaking");
		SetHelper rlh = h.getCreateSet("Parameter", "RunList", EnumUsage.Input);
		PartitionHelper p = rlh.getCreatePartition(null, true);
		JDFRunList rl = (JDFRunList) p.getCreateResource();
		rl.setNPage(48);
		rlh.getSet().setXMLComment("set the updated total number of pages");

		SetHelper loh = h.getCreateSet("Parameter", "Layout", EnumUsage.Input);
		p = loh.getCreatePartition(new JDFAttributeMap("SheetName", "S3"), true);
		JDFLayout lo = (JDFLayout) p.getCreateResource();
		JDFBinderySignature bs = (JDFBinderySignature) lo.appendElement(ElementName.BINDERYSIGNATURE);
		bs.setNumberUp(new JDFXYPair(4, 4));
		loh.getSet().setXMLComment("only specify the 3rd sheet");

		SetHelper mh = h.getCreateSet("Resource", "Media", EnumUsage.Input);
		p = mh.getCreatePartition(null, true);
		KElement mPart = p.getPartition();
		mPart.setAttribute("ProductID", "PlateID");

		SetHelper xmh = h.getCreateSet("Resource", "ExposedMedia", EnumUsage.Output);
		p = xmh.getCreatePartition(new JDFAttributeMap("SheetName", "S3"), true);
		JDFExposedMedia xm = (JDFExposedMedia) p.getCreateResource();
		xm.setAttribute("MediaRef", mPart.getAttribute("ID"));

		h.getRoot().getOwnerDocument_KElement().write2File(sm_dirTestDataTemp + "changeSheet1.xjdf", 2, false);
	}

	/**
	 * 
	 */
	public void testAddProcess()
	{
		h.getRoot().setXMLComment("Added Varnishing - how do we differentiate varnishing only from add varnishing\n");
		h.getRoot().setAttribute("Types", "Varnishing");
		SetHelper rlh = h.getCreateSet("Parameter", "VarnishingParams", EnumUsage.Input);
		PartitionHelper p = rlh.getCreatePartition(new JDFAttributeMap("SheetName", "S3"), true);
		//		JDFVarnishingParams vp = (JDFRunList) p.getCreateResource();
		//		rl.setNPage(48);
	}

	/**
	 * 
	 */
	public void testProductOnly()
	{
		h.getRoot().setXMLComment("Product description only\n");
		//h.getRoot().setAttribute("Types", "Varnishing");
		ProductHelper ph = h.appendProduct();
		ph.setRoot();
		ph.setAmount(4200);

		ProductHelper phCover = h.appendProduct();
		ph.setChild(phCover, 1);
		phCover.getProduct().setAttribute("DescriptiveName", "Cover");
		IntentHelper coverMedia = phCover.getCreateIntent("MediaIntent");
		coverMedia.setSpan("Weight", "150", "NumberSpan");

		ProductHelper phBody = h.appendProduct();
		ph.setChild(phBody, 1);
		phBody.getProduct().setAttribute("DescriptiveName", "Body");
		IntentHelper bodyMedia = phBody.getCreateIntent("MediaIntent");
		bodyMedia.setSpan("Weight", "100", "NumberSpan");

		IntentHelper binding = ph.getCreateIntent("BindingIntent");
		binding.setSpan("BindingType", EnumSpanBindingType.SaddleStitch.getName(), "EnumerationSpan");
		binding.setSpan("CoverRef", phCover.getProduct().getID(), null);
		IntentHelper layout = ph.getCreateIntent("LayoutIntent");
		layout.setSpan("Dimensions", "700 1000", "XYPairSpan");

		h.writeToFile(sm_dirTestDataTemp + "product1.xjdf");
	}

	/**
	 * 
	 */
	public void testRemoveSheet()
	{
		h.getRoot().setXMLComment("Assume incremental removal of an existing 3rd plate");
		h.getRoot().setAttribute("Types", "PlateMaking");
		SetHelper rlh = h.getCreateSet("Parameter", "RunList", EnumUsage.Input);
		PartitionHelper p = rlh.getCreatePartition(null, true);
		JDFRunList rl = (JDFRunList) p.getCreateResource();
		rl.setNPage(32);
		rlh.getSet().setXMLComment("set the updated reduced total number of pages");

		SetHelper nih = h.getCreateSet("Parameter", "NodeInfo", EnumUsage.Input);
		p = nih.getCreatePartition(new JDFAttributeMap("SheetName", "S3"), true);
		JDFNodeInfo ni = (JDFNodeInfo) p.getCreateResource();
		ni.setNodeStatus(EnumNodeStatus.Aborted);
		ni.setNodeStatusDetails("Removed");
		nih.getSet().setXMLComment("All other dependent resources must be appropriately modified by the device");

		h.writeToFile(sm_dirTestDataTemp + "removeSheet1.xjdf");
	}

	/**
	 * 
	 */
	public void testUpdateAmount()
	{
		h.getRoot().setXMLComment("update the required amount for sheet=s3");
		h.getRoot().setAttribute("Types", "ConventionalPrinting");

		h.removeSet(ElementName.NODEINFO);
		SetHelper nih = h.getCreateSet("Parameter", "NodeInfo", EnumUsage.Input);
		PartitionHelper p = nih.getCreatePartition(new JDFAttributeMap("SheetName", "S3"), true);
		JDFNodeInfo ni = (JDFNodeInfo) p.getCreateResource();
		ni.setAttribute("AmountGood", 10000, null);

		h.getRoot().getOwnerDocument_KElement().write2File(sm_dirTestDataTemp + "updateAmount.xjdf", 2, false);
	}

	/**
	 * @see junit.framework.TestCase#toString()
	 * @return
	*/
	@Override
	public String toString()
	{
		return h == null ? "empty test" : "test " + h.toString();
	}
}
