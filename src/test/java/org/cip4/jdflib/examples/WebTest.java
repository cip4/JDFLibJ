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

import org.cip4.jdflib.JDFTestCaseBase;
import org.cip4.jdflib.auto.JDFAutoPart.EnumSide;
import org.cip4.jdflib.core.ElementName;
import org.cip4.jdflib.core.JDFDoc;
import org.cip4.jdflib.core.JDFNodeInfo;
import org.cip4.jdflib.core.JDFResourceLink.EnumUsage;
import org.cip4.jdflib.core.KElement;
import org.cip4.jdflib.core.VString;
import org.cip4.jdflib.core.XMLDoc;
import org.cip4.jdflib.datatypes.JDFMatrix;
import org.cip4.jdflib.datatypes.JDFXYPair;
import org.cip4.jdflib.extensions.SetHelper;
import org.cip4.jdflib.extensions.XJDFHelper;
import org.cip4.jdflib.node.JDFNode;
import org.cip4.jdflib.node.JDFNode.EnumProcessUsage;
import org.cip4.jdflib.node.JDFNode.EnumType;
import org.cip4.jdflib.pool.JDFResourcePool;
import org.cip4.jdflib.resource.JDFMarkObject;
import org.cip4.jdflib.resource.JDFResource;
import org.cip4.jdflib.resource.JDFResource.EnumPartIDKey;
import org.cip4.jdflib.resource.JDFResource.EnumResStatus;
import org.cip4.jdflib.resource.JDFResource.EnumResourceClass;
import org.cip4.jdflib.resource.process.JDFByteMap;
import org.cip4.jdflib.resource.process.JDFContentObject;
import org.cip4.jdflib.resource.process.JDFExposedMedia;
import org.cip4.jdflib.resource.process.JDFLayout;
import org.cip4.jdflib.resource.process.JDFRunList;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class WebTest extends JDFTestCaseBase
{
	private JDFNode node;
	private JDFNodeInfo nodeInfo;
	private JDFDoc doc;

	/**
	 * test WebGrowth Compensation
	 */
	@Test
	void testWebGrowthCompensation()
	{

		KElement.setLongID(false);
		doc = new JDFDoc("JDF");
		final JDFNode n = doc.getJDFRoot();
		final JDFResourcePool rp = n.getCreateResourcePool();
		final JDFResource lo = n.addResource("Layout", EnumResourceClass.Parameter, EnumUsage.Input, null, null, null, null);
		final JDFLayout losh = (JDFLayout) lo.addPartition(EnumPartIDKey.SheetName, "Sheet1");
		final JDFLayout lofr = (JDFLayout) losh.addPartition(EnumPartIDKey.Side, EnumSide.Front.getName());

		rp.appendXMLComment("LayoutShift SHOULD be partitioned: at least Side and Separation will make sense", null);

		JDFResource los = n.addResource("LayoutShift", EnumResourceClass.Parameter, EnumUsage.Input, null, null, null, null);
		los.appendXMLComment("Note that the interpolation algorithm between positions is implementation dependent", null);
		los = los.addPartition(EnumPartIDKey.Side, "Front");
		final VString vSep = new VString("Cyan Magenta Yellow Black", " ");

		for (int i = 0; i < 4; i++)
		{
			final int x = 720 * (i % 2);
			final int y = 1000 * (i / 2);
			final int ord = i % 8;
			final JDFContentObject co = lofr.appendContentObject();
			co.setOrd(ord);
			co.setOrdID(i);
			co.setCTM(new JDFMatrix(1, 0, 0, 1, x, y));
			if ((i % 4) == 0)
			{
				final JDFMarkObject mo = lofr.appendMarkObject();
				mo.setOrd(ord);
				mo.setOrdID(i + 100);
				mo.setCTM(new JDFMatrix(1, 0, 0, 1, x + 700, y + 900));
			}
		}
		for (int j = 0; j < vSep.size(); j++)
		{
			final KElement sepShift = los.addPartition(EnumPartIDKey.Separation, vSep.get(j));
			for (int i = 0; i < 16; i += 2)
			{

				final int x = 720 * (i % 4);
				final int y = 1000 * (i / 4);
				final KElement shiftObject = sepShift.appendElement(ElementName.SHIFTPOINT);

				shiftObject.setAttribute("Position", new JDFXYPair(x + 360, y + 500).toString());
				shiftObject.setAttribute("CTM", new JDFMatrix(1, 0, 0, 1, j + i / 4, j + i % 4).toString());
			}
		}
		XMLDoc xjdfDoc = writeTest(doc.getRoot(), "resources/WebgrowthPartition.jdf", true, null);
		XJDFHelper h = XJDFHelper.getHelper(xjdfDoc);
		SetHelper sh = h.getSet(ElementName.LAYOUTSHIFT, 0);
		//		setSnippet(sh, true);
		setSnippet(sh.getPartition(0), false);
		setSnippet(sh.getPartition(1), false);
		setSnippet(sh.getPartition(2), false);
		writeTest(h, "resources/WebgrowthPartition.xjdf");
	}

	/**
	 * test direct imaging
	 */
	@Test
	void testDirectImage()
	{

		KElement.setLongID(false);
		doc = new JDFDoc("JDF");
		node = doc.getJDFRoot();
		node.setType(EnumType.Combined);
		final VString vTypes = new VString("ImageSetting ConventionalPrinting", " ");
		node.setTypes(vTypes);
		nodeInfo = node.appendNodeInfo();
		nodeInfo.setResStatus(EnumResStatus.Available, true);
		final JDFRunList rl = (JDFRunList) node.appendMatchingResource(ElementName.RUNLIST, EnumProcessUsage.AnyInput, null);
		final JDFByteMap bm = rl.appendByteMap();
		bm.appendFileSpec().setURL("File:///foo.tif");
		final JDFExposedMedia xm = (JDFExposedMedia) node.appendMatchingResource(ElementName.EXPOSEDMEDIA, EnumProcessUsage.Plate, null);
		xm.setDescriptiveName("Real Plate");
		xm.appendMedia();
		final JDFExposedMedia xmCyl = (JDFExposedMedia) node.appendMatchingResource(ElementName.EXPOSEDMEDIA, EnumProcessUsage.Cylinder, null);
		xmCyl.setDescriptiveName("Optional cylinder");
		Assertions.assertNotNull(node.linkMatchingResource(xmCyl, EnumProcessUsage.AnyOutput, null));
		Assertions.assertEquals(node.getResourceLinkPool().numChildElements("ExposedMediaLink", null), 3, "2 for cylinder one for plate");
		doc.write2File(sm_dirTestDataTemp + File.separator + "webDirect.jdf", 2, false);

	}

	@Test
	void testSplitDuct()
	{
		// TODO
	}

}
