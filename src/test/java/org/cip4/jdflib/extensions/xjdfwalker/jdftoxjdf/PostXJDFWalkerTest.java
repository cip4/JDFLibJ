/*
 * The CIP4 Software License, Version 1.0
 *
 *
 * Copyright (c) 2001-2023 The International Cooperation for the Integration of Processes in Prepress, Press and Postpress (CIP4). All rights reserved.
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
package org.cip4.jdflib.extensions.xjdfwalker.jdftoxjdf;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.cip4.jdflib.JDFTestCaseBase;
import org.cip4.jdflib.core.AttributeName;
import org.cip4.jdflib.core.ElementName;
import org.cip4.jdflib.core.JDFDoc;
import org.cip4.jdflib.core.JDFElement;
import org.cip4.jdflib.core.JDFElement.EnumOrientation;
import org.cip4.jdflib.core.JDFElement.EnumVersion;
import org.cip4.jdflib.core.JDFResourceLink.EnumUsage;
import org.cip4.jdflib.core.KElement;
import org.cip4.jdflib.datatypes.JDFAttributeMap;
import org.cip4.jdflib.extensions.MessageHelper;
import org.cip4.jdflib.extensions.ResourceHelper;
import org.cip4.jdflib.extensions.SetHelper;
import org.cip4.jdflib.extensions.XJDF20;
import org.cip4.jdflib.extensions.XJDFConstants;
import org.cip4.jdflib.extensions.XJDFHelper;
import org.cip4.jdflib.extensions.XJMFHelper;
import org.cip4.jdflib.node.JDFNode;
import org.cip4.jdflib.node.JDFNode.EnumType;
import org.cip4.jdflib.resource.JDFResource;
import org.junit.jupiter.api.Test;

class PostXJDFWalkerTest extends JDFTestCaseBase
{

	/**
	 *
	 */
	@Test
	void testAmountsNull()
	{
		final XJDFHelper h = new XJDFHelper("a", "p", null);
		final SetHelper sni = h.getCreateSet(XJDFConstants.Resource, ElementName.NODEINFO, EnumUsage.Input);
		final ResourceHelper pi = sni.getCreatePartition(null, true);
		pi.setAmount(42, null, true);
		pi.setAmount(2, null, false);

		final PostXJDFWalker w = new PostXJDFWalker((JDFElement) h.getRoot());
		w.walkTree(h.getRoot(), null);
		assertNull(h.getRoot().getXPathAttribute("AuditPool/AuditResource/ResourceInfo/ResourceSet/Resource/AmountPool/PartAmount/@Amount", null));
	}

	/**
	 *
	 */
	@Test
	void testAuditPool()
	{
		final XJDFHelper h = new XJDFHelper("a", "p", null);
		h.setXPathValue("AuditPool/AuditCreated/@ID", "42");
		final PostXJDFWalker w = new PostXJDFWalker((JDFElement) h.getRoot());
		w.walkTree(h.getRoot(), null);
		assertNotNull(h.getRoot().getXPathElement("AuditPool/AuditCreated"));
		assertNotNull(h.getRoot().getXPathElement("AuditPool/AuditCreated/Header"));
		assertNull(h.getRoot().getXPathElement("AuditPool/Header"));
	}

	/**
	 *
	 */
	@Test
	void testSameSets()
	{
		final XJDFHelper h = new XJDFHelper("a", "p", null);
		h.appendResourceSet("a", EnumUsage.Input).appendPartition("Run", "r1", true);
		h.appendResourceSet("a", EnumUsage.Input).appendPartition("Run", "r2", true);
		final PostXJDFWalker w = new PostXJDFWalker((JDFElement) h.getRoot());
		w.combineSameSets();
		assertNull(h.getSet("a", 1));
		assertEquals(2, h.getSet("a", 0).getPartMapVector().size());
	}

	/**
	 *
	 */
	@Test
	void testSameResInfo()
	{
		final XJMFHelper h = new XJMFHelper();
		final MessageHelper mh = h.appendMessage("ResponseResource");
		int j = 0;
		for (int i = 0; i < 10; i++)
		{
			final KElement ri = mh.getRoot().appendElement(ElementName.RESOURCEINFO);
			j++;
			final KElement rs0 = ri.appendElement(XJDFConstants.ResourceSet);
			rs0.setAttribute(AttributeName.NAME, "Media");
			new SetHelper(rs0).appendResource((JDFAttributeMap) null, true).setDescriptiveName("foo " + j);
		}
		final PostXJDFWalker w = new PostXJDFWalker((JDFElement) h.getRoot());
		w.walkTree(mh.getRoot(), null);
		assertEquals(1, mh.getRoot().getChildList(ElementName.RESOURCEINFO, null).size());
		final SetHelper s2 = new SetHelper(mh.getRoot().getElement("ResourceInfo").getElement(XJDFConstants.ResourceSet));
		assertEquals(10, s2.getPartitions().size());
	}

	/**
	 *
	 */
	@Test
	void testSameSetsRetain()
	{
		final XJDFHelper h = new XJDFHelper("a", "p", null);
		h.appendResourceSet("a", EnumUsage.Input).appendPartition("Run", "r1", true);
		h.appendResourceSet("a", EnumUsage.Input).appendPartition("Run", "r2", true);
		final PostXJDFWalker w = new PostXJDFWalker((JDFElement) h.getRoot());
		w.setRetainAll(true);
		w.combineSameSets();
		assertNotNull(h.getSet("a", 1));
		assertEquals(1, h.getSet("a", 0).getPartMapVector().size());
	}

	/**
	 *
	 */
	@Test
	void testHeadbandStrip()
	{
		final XJDFHelper h = new XJDFHelper("a", "p", null);
		h.appendResourceSet(ElementName.HEADBANDAPPLICATIONPARAMS, EnumUsage.Input).appendPartition(null, true).getResource()
				.setAttribute(AttributeName.STRIPMATERIAL, "b1");
		final PostXJDFWalker w = new PostXJDFWalker((JDFElement) h.getRoot());
		w.walkTree(h.getRoot(), null);
		assertEquals("b1",
				h.getSet(ElementName.MISCCONSUMABLE, EnumUsage.Input, "BackStrip").getPartition(0).getResource().getAttribute(XJDFConstants.TypeDetails));
	}

	/**
	 *
	 */
	@Test
	void testHeadbandColor()
	{
		final XJDFHelper h = new XJDFHelper("a", "p", null);
		final KElement band = h.appendResourceSet(ElementName.HEADBANDAPPLICATIONPARAMS, EnumUsage.Input).appendPartition(null, true).getResource();
		band.setAttribute(AttributeName.TOPCOLOR, "Black");
		band.setAttribute(AttributeName.TOPBRAND, "b1");
		final PostXJDFWalker w = new PostXJDFWalker((JDFElement) h.getRoot());
		w.walkTree(h.getRoot(), null);
		assertEquals("Black",
				h.getSet(ElementName.MISCCONSUMABLE, EnumUsage.Input, "HeadBand").getPartition(0).getResource().getAttribute(AttributeName.COLOR));
		assertEquals("b1", h.getSet(ElementName.MISCCONSUMABLE, EnumUsage.Input, "HeadBand").getPartition(0).getBrand());
	}

	/**
	 *
	 */
	@Test
	void testSignatureName()
	{
		final XJDFHelper h = new XJDFHelper("a", "p", null);
		final ResourceHelper r = h.appendResourceSet(ElementName.COMPONENT, EnumUsage.Input).appendPartition(null, true);
		final JDFAttributeMap map = new JDFAttributeMap(AttributeName.SIGNATURENAME, "s1");
		r.setPartMap(map);
		final PostXJDFWalker w = new PostXJDFWalker((JDFElement) h.getRoot());
		w.walkTree(h.getRoot(), null);
		assertEquals(new JDFAttributeMap(), r.getPartMap());
	}

	/**
	 *
	 */
	@Test
	void testDocRunIndex()
	{
		final XJDFHelper h = new XJDFHelper("a", "p", null);
		final ResourceHelper r = h.appendResourceSet(ElementName.COMPONENT, EnumUsage.Input).appendPartition(null, true);
		final JDFAttributeMap map = new JDFAttributeMap(AttributeName.DOCRUNINDEX, "3");
		r.setPartMap(map);
		final PostXJDFWalker w = new PostXJDFWalker((JDFElement) h.getRoot());
		w.walkTree(h.getRoot(), null);
		assertEquals(new JDFAttributeMap(AttributeName.RUNINDEX, "3"), r.getPartMap());
	}

	/**
	 *
	 */
	@Test
	void testRunPage()
	{
		final XJDFHelper h = new XJDFHelper("a", "p", null);
		final SetHelper set = h.appendResourceSet(ElementName.RUNLIST, EnumUsage.Input);
		final JDFAttributeMap map = new JDFAttributeMap(AttributeName.RUN, "R");
		map.put(AttributeName.RUNPAGE, "1");
		set.appendPartition(map, true);
		map.put(AttributeName.RUNPAGE, "2");
		final ResourceHelper r2 = set.appendPartition(map, true);
		final PostXJDFWalker w = new PostXJDFWalker((JDFElement) h.getRoot());
		w.walkTree(h.getRoot(), null);
		assertEquals(2, set.getPartitions().size());
		assertTrue(set.getPartMapVector().getKeys().contains(AttributeName.PAGENUMBER));
		assertEquals("2 2", r2.getPartMap().get(AttributeName.PAGENUMBER));
	}

	/**
	 *
	 */
	@Test
	void testThreadsewMaterial()
	{
		final XJDFHelper h = new XJDFHelper("a", "p", null);
		final KElement tsp = h.appendResourceSet(ElementName.THREADSEWINGPARAMS, EnumUsage.Input).appendPartition(null, true).getResource();
		tsp.setAttribute(AttributeName.CASTINGMATERIAL, "cm");
		final PostXJDFWalker w = new PostXJDFWalker((JDFElement) h.getRoot());
		w.walkTree(h.getRoot(), null);
		assertEquals("cm",
				h.getSet(ElementName.MISCCONSUMABLE, EnumUsage.Input, "Thread").getPartition(0).getResource().getAttribute(XJDFConstants.TypeDetails));
	}

	/**
	 *
	 */
	@Test
	void testLaminateHardener()
	{
		final XJDFHelper h = new XJDFHelper("a", "p", null);
		final KElement tsp = h.appendResourceSet(ElementName.LAMINATINGPARAMS, EnumUsage.Input).appendPartition(null, true).getResource();
		tsp.setAttribute(AttributeName.HARDENERTYPE, "toughstough");
		final PostXJDFWalker w = new PostXJDFWalker((JDFElement) h.getRoot());
		w.walkTree(h.getRoot(), null);
		assertEquals("toughstough",
				h.getSet(ElementName.MISCCONSUMABLE, EnumUsage.Input, "Hardener").getPartition(0).getResource().getAttribute(XJDFConstants.TypeDetails));
	}

	/**
	 *
	 */
	@Test
	void testLaminateGlue()
	{
		final XJDFHelper h = new XJDFHelper("a", "p", null);
		final KElement tsp = h.appendResourceSet(ElementName.LAMINATINGPARAMS, EnumUsage.Input).appendPartition(null, true).getResource();
		tsp.setAttribute(AttributeName.ADHESIVETYPE, "toughstough");
		final PostXJDFWalker w = new PostXJDFWalker((JDFElement) h.getRoot());
		w.walkTree(h.getRoot(), null);
		assertEquals("toughstough",
				h.getSet(ElementName.MISCCONSUMABLE, EnumUsage.Input, "Glue").getPartition(0).getResource().getAttribute(XJDFConstants.TypeDetails));
	}

	/**
	 *
	 */
	@Test
	void testStitchGauge()
	{
		final XJDFHelper h = new XJDFHelper("a", "p", null);
		final KElement tsp = h.appendResourceSet(ElementName.STITCHINGPARAMS, EnumUsage.Input).appendPartition(null, true).getResource();
		tsp.setAttribute(AttributeName.WIREGAUGE, "42");
		tsp.setAttribute(AttributeName.WIREBRAND, "wb");
		final PostXJDFWalker w = new PostXJDFWalker((JDFElement) h.getRoot());
		w.walkTree(h.getRoot(), null);
		assertEquals("42", h.getSet(ElementName.MISCCONSUMABLE, EnumUsage.Input, "Wire").getPartition(0).getResource().getAttribute(XJDFConstants.TypeDetails));
	}

	/**
	 *
	 */
	@Test
	void testStationAmount()
	{
		final XJDFHelper h = new XJDFHelper("a", "p", null);
		final KElement dl = h.appendResourceSet(ElementName.DIELAYOUT, EnumUsage.Input).appendPartition(null, true).getResource();
		dl.appendElement(ElementName.STATION).setAttribute(AttributeName.STATIONAMOUNT, "3");
		final PostXJDFWalker w = new PostXJDFWalker((JDFElement) h.getRoot());
		w.walkTree(h.getRoot(), null);
		assertEquals(3, dl.numChildElements(ElementName.STATION, null));
		assertFalse(dl.getElement(ElementName.STATION, null, 0).hasAttribute(AttributeName.STATIONAMOUNT));
	}

	/**
	 *
	 */
	@Test
	void testAuditOrder()
	{
		final XJDFHelper h = new XJDFHelper("a", "p", null);
		h.setXPathValue("AuditPool/AuditCreated/@ID", "42");
		h.setXPathValue("AuditPool/AuditCreated/Foo/@Bar", "foo");
		final PostXJDFWalker w = new PostXJDFWalker((JDFElement) h.getRoot());
		w.walkTree(h.getRoot(), null);
		assertNotNull(h.getRoot().getXPathElement("AuditPool/AuditCreated"));
		final KElement head = h.getRoot().getXPathElement("AuditPool/AuditCreated/Header");
		final KElement foo = h.getRoot().getXPathElement("AuditPool/AuditCreated/Foo");
		assertEquals(head.getNextSibling(), foo);
	}

	/**
	 *
	 */
	@Test
	void testHeaderMessageOrder()
	{
		final KElement x = new JDFDoc(XJDFConstants.XJMF, EnumVersion.Version_2_0).getRoot();
		final KElement c = x.appendElement("CommandSubmitQueueEntry");
		final KElement h = c.appendElement(XJDFConstants.Header);
		final KElement h2 = c.appendElement(XJDFConstants.AssemblingIntent);
		final PostXJDFWalker w = new PostXJDFWalker((JDFElement) x);
		w.walkTree(x, null);
		assertEquals(c.getElement(null), h);
		assertEquals(h.getNextSibling(), h2);
	}

	/**
	 *
	 */
	@Test
	void testHeaderMessageNameSpace()
	{
		final KElement x = new JDFDoc(XJDFConstants.XJMF, EnumVersion.Version_1_6).getRoot();
		final KElement c = x.appendElement("SignalNotification");
		x.setAttribute(AttributeName.DEVICEID, "d1");
		c.setAttribute(AttributeName.DEVICEID, "d1");
		final PostXJDFWalker w = new PostXJDFWalker((JDFElement) x);
		w.walkTreeKidsFirst(x);
		assertEquals(XJDF20.getSchemaURL(), x.getElement(XJDFConstants.Header).getNamespaceURI());
		assertEquals(XJDF20.getSchemaURL(), c.getElement(XJDFConstants.Header).getNamespaceURI());
	}

	/**
	 *
	 */
	@Test
	void testSetNewVersion()
	{
		final KElement x = new JDFDoc(XJDFConstants.XJMF, EnumVersion.Version_1_6).getRoot();
		final KElement c = x.appendElement("SignalNotification");
		x.setAttribute(AttributeName.DEVICEID, "d1");
		c.setAttribute(AttributeName.DEVICEID, "d1");
		final PostXJDFWalker w = new PostXJDFWalker((JDFElement) x);
		w.setNewVersion(EnumVersion.Version_2_1);
		w.walkTreeKidsFirst(x);
		assertEquals(JDFElement.getSchemaURL(EnumVersion.Version_2_1), x.getElement(XJDFConstants.Header).getNamespaceURI());
		assertEquals(JDFElement.getSchemaURL(EnumVersion.Version_2_1), c.getElement(XJDFConstants.Header).getNamespaceURI());
		assertEquals(JDFElement.getSchemaURL(EnumVersion.Version_2_1), x.getNamespaceURI());
	}

	/**
	 *
	 */
	@Test
	void testPV()
	{
		final XJDFHelper h = new XJDFHelper("a", null);
		h.appendResourceSet(ElementName.EXPOSEDMEDIA, EnumUsage.Input).getCreatePartition(AttributeName.PARTVERSION, "P1 P2", true);
		final PostXJDFWalker w = new PostXJDFWalker((JDFElement) h.getRoot());
		w.walkTree(h.getRoot(), null);
		assertEquals("P1", h.getSet(ElementName.EXPOSEDMEDIA, EnumUsage.Input).getPartMapVector().get(0).get(AttributeName.PARTVERSION));
		assertEquals("P2", h.getSet(ElementName.EXPOSEDMEDIA, EnumUsage.Input).getPartMapVector().get(1).get(AttributeName.PARTVERSION));
	}

	/**
	 *
	 */
	@Test
	void testOrientation()
	{
		final XJDFHelper h = new XJDFHelper("a", null);
		final SetHelper set = h.appendResourceSet(ElementName.EXPOSEDMEDIA, EnumUsage.Input);
		set.getRoot().setAttribute(AttributeName.ORIENTATION, EnumOrientation.Flip0.getName());
		set.getCreatePartition(AttributeName.PARTVERSION, "P1", true);
		final PostXJDFWalker w = new PostXJDFWalker((JDFElement) h.getRoot());
		w.walkTree(h.getRoot(), null);
		assertEquals(EnumOrientation.Flip0.getName(),
				h.getSet(ElementName.EXPOSEDMEDIA, EnumUsage.Input).getPartition(0).getAttribute(AttributeName.ORIENTATION));
		assertNull(h.getSet(ElementName.EXPOSEDMEDIA, EnumUsage.Input).getAttribute(AttributeName.ORIENTATION));
	}

	/**
	 *
	 */
	@Test
	void testMoveToSet()
	{
		final XJDFHelper h = new XJDFHelper("a", null);
		final SetHelper set = h.appendResourceSet(ElementName.EXPOSEDMEDIA, EnumUsage.Input);
		set.getRoot().setAttribute(AttributeName.DESCRIPTIVENAME, "D1");
		final ResourceHelper p = set.getCreatePartition(AttributeName.PARTVERSION, "P1", true);
		p.setDescriptiveName("D1");
		final PostXJDFWalker w = new PostXJDFWalker((JDFElement) h.getRoot());
		final org.cip4.jdflib.extensions.xjdfwalker.jdftoxjdf.PostXJDFWalker.WalkResource walkResource = w.new WalkResource();
		walkResource.moveToSet(p.getRoot());
		walkResource.moveToSet(p.getRoot());
		assertEquals("D1", h.getSet(ElementName.EXPOSEDMEDIA, EnumUsage.Input).getPartition(0).getDescriptiveName());
		assertNull(h.getSet(ElementName.EXPOSEDMEDIA, EnumUsage.Input).getDescriptiveName());
		walkResource.moveToSet(KElement.createRoot(XJDFConstants.Resource, null));
		walkResource.moveToSet(null);
	}

	/**
	 *
	 */
	@Test
	void testPVRetain()
	{
		final XJDFHelper h = new XJDFHelper("a", null);
		h.appendResourceSet(ElementName.EXPOSEDMEDIA, EnumUsage.Input).getCreatePartition(AttributeName.PARTVERSION, "P1 P2", true);
		final PostXJDFWalker w = new PostXJDFWalker((JDFElement) h.getRoot());
		w.setRetainAll(true);
		w.walkTree(h.getRoot(), null);
		assertEquals("P1 P2", h.getSet(ElementName.EXPOSEDMEDIA, EnumUsage.Input).getPartMapVector().get(0).get(AttributeName.PARTVERSION));
	}

	/**
	 *
	 */
	@Test
	void testProcessListSingleDeletedAndMultipleKept()
	{
		final XJDFHelper oneProcessHelper = new XJDFHelper("a", "p", null);
		final KElement oneProcessList = oneProcessHelper.getRoot().appendElement(XJDFConstants.ProcessList);
		oneProcessList.appendElement(XJDFConstants.Process).setAttribute(AttributeName.JOBPARTID, "p1");

		final PostXJDFWalker walker = new PostXJDFWalker((JDFElement) oneProcessHelper.getRoot());
		walker.walkTree(oneProcessHelper.getRoot(), null);
		assertNull(oneProcessHelper.getRoot().getElement(XJDFConstants.ProcessList));

		final XJDFHelper twoProcessHelper = new XJDFHelper("a", "p", null);
		final KElement twoProcessList = twoProcessHelper.getRoot().appendElement(XJDFConstants.ProcessList);
		twoProcessList.appendElement(XJDFConstants.Process).setAttribute(AttributeName.JOBPARTID, "p1");
		twoProcessList.appendElement(XJDFConstants.Process).setAttribute(AttributeName.JOBPARTID, "p2");

		new PostXJDFWalker((JDFElement) twoProcessHelper.getRoot()).walkTree(twoProcessHelper.getRoot(), null);
		assertNotNull(twoProcessHelper.getRoot().getElement(XJDFConstants.ProcessList));
		assertEquals(2, twoProcessHelper.getRoot().getXPathElement("ProcessList").numChildElements(XJDFConstants.Process, null));
	}

	/**
	 *
	 */
	@Test
	void testPartAmountMovesResourceAttributesAndRemovesRedundantParts()
	{
		final XJDFHelper h = new XJDFHelper("a", "p", null);
		final SetHelper componentSet = h.appendResourceSet(ElementName.COMPONENT, EnumUsage.Output);
		final ResourceHelper componentPartition = componentSet.appendPartition(AttributeName.SHEETNAME, "S1", true);
		final KElement component = componentPartition.getResource();
		component.appendElement(ElementName.PART).setAttribute(AttributeName.SHEETNAME, "S1");

		final KElement amountPool = component.getCreateElement(ElementName.AMOUNTPOOL, null, 0);
		final KElement partAmount = amountPool.appendElement(ElementName.PARTAMOUNT);
		partAmount.setAttribute(AttributeName.TRANSFORMATION, "1 0 0 1 5 6");
		partAmount.setAttribute(AttributeName.ORIENTATION, "Rotate0");
		final KElement part = partAmount.appendElement(ElementName.PART);
		part.setAttribute(AttributeName.SHEETNAME, "S1");
		part.setAttribute(AttributeName.SEPARATION, "Cyan");

		final PostXJDFWalker w = new PostXJDFWalker((JDFElement) h.getRoot());
		w.walkTree(h.getRoot(), null);

		assertEquals("1 0 0 1 5 6", h.getRoot().getXPathAttribute("ResourceSet[@Name=\"Component\"]/Resource/@Transformation", null));
		assertEquals("Rotate0", h.getRoot().getXPathAttribute("ResourceSet[@Name=\"Component\"]/Resource/@Orientation", null));
		assertNull(h.getRoot().getXPathAttribute("ResourceSet[@Name=\"Component\"]/Resource/AmountPool/PartAmount/@Transformation", null));
		assertNull(h.getRoot().getXPathAttribute("ResourceSet[@Name=\"Component\"]/Resource/AmountPool/PartAmount/@Orientation", null));
		assertNull(h.getRoot().getXPathAttribute("ResourceSet[@Name=\"Component\"]/Resource/AmountPool/PartAmount/Part/@SheetName", null));
		assertNull(h.getRoot().getXPathAttribute("ResourceSet[@Name=\"Component\"]/Resource/AmountPool/PartAmount/Part/@Condition", null));
	}

	/**
	 *
	 */
	@Test
	void testPlacedObjectCopiesCommonAttributesAndMovesStripMarkChildren()
	{
		final XJDFHelper h = new XJDFHelper("a", "p", null);
		final SetHelper layoutSet = h.appendResourceSet(ElementName.LAYOUT, EnumUsage.Input);
		final KElement layout = layoutSet.appendPartition(AttributeName.SHEETNAME, "S1", true).getResource();
		final KElement markObject = layout.appendElement(ElementName.MARKOBJECT);
		markObject.setAttribute(AttributeName.ORD, "2");
		markObject.appendElement(ElementName.JOBFIELD).setAttribute(AttributeName.VALUE, "JobFieldValue");

		final PostXJDFWalker w = new PostXJDFWalker((JDFElement) h.getRoot());
		w.walkTree(h.getRoot(), null);

		assertEquals("2", h.getRoot().getXPathAttribute("ResourceSet[@Name=\"Layout\"]/Resource/Layout/PlacedObject/@Ord", null));
		assertNull(h.getRoot().getXPathAttribute("ResourceSet[@Name=\"Layout\"]/Resource/Layout/PlacedObject/MarkObject/@Ord", null));
		assertNotNull(h.getRoot().getXPathElement("ResourceSet[@Name=\"Layout\"]/Resource/Layout/PlacedObject/MarkObject"));
		assertEquals("JobFieldValue",
				h.getRoot().getXPathAttribute("ResourceSet[@Name=\"Layout\"]/Resource/Layout/StripMark/JobField/@Value", null));
	}

	/**
	 *
	 */
	@Test
	void testWalkResLinkCreatesProcessListWhenRequested()
	{
		final JDFNode root = new JDFDoc(ElementName.JDF).getJDFRoot();
		root.setType(EnumType.ProcessGroup);
		root.setJobPartID("P0");
		final JDFNode child = root.addJDFNode(EnumType.ConventionalPrinting);
		child.setJobPartID("P0.1");
		final JDFResource media = child.addResource(ElementName.MEDIA, EnumUsage.Input);
		child.ensureLink(media, EnumUsage.Input, null);

		final JDFNode child2 = root.addJDFNode(EnumType.Cutting);
		child2.setJobPartID("P0.2");
		final JDFResource component = child2.addResource(ElementName.COMPONENT, EnumUsage.Input);
		child2.ensureLink(component, EnumUsage.Input, null);

		final JDFToXJDF converter = new JDFToXJDF();
		converter.setProcessPart(JDFToXJDF.EnumProcessPartition.processList);
		final KElement xjdf = converter.convert(root);

		assertEquals(2, xjdf.getXPathElement("ProcessList").numChildElements(XJDFConstants.Process, null));
		assertNotNull(xjdf.getXPathElement("ProcessList/Process"));
		assertEquals("P0.1", xjdf.getXPathAttribute("ProcessList/Process/@JobPartID", null));
		assertNotNull(xjdf.getXPathAttribute("ProcessList/Process/@Types", null));
	}

	/**
	 *
	 */
	@Test
	void testWalkResLinkAddsProductPartWhenPartitioningByJobPartID()
	{
		final JDFNode root = new JDFDoc(ElementName.JDF).getJDFRoot();
		root.setType(EnumType.ProcessGroup);
		root.setJobPartID("P0");
		final JDFNode child = root.addJDFNode(EnumType.ConventionalPrinting);
		child.setJobPartID("P0.1");
		final JDFResource media = child.addResource(ElementName.MEDIA, EnumUsage.Input);
		child.ensureLink(media, EnumUsage.Input, null);

		final JDFToXJDF converter = new JDFToXJDF();
		converter.setProcessPart(JDFToXJDF.EnumProcessPartition.jobPartID);
		final KElement xjdf = converter.convert(root);

		assertEquals("P0.1", xjdf.getXPathAttribute("ResourceSet[@Name=\"Media\"]/Resource/Part/@ProductPart", null));
	}
}
