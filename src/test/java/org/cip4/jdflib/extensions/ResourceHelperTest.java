/*
 * The CIP4 Software License, Version 1.0
 *
 *
 * Copyright (c) 2001-2022 The International Cooperation for the Integration of Processes in Prepress, Press and Postpress (CIP4). All rights reserved.
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

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.cip4.jdflib.JDFTestCaseBase;
import org.cip4.jdflib.core.AttributeName;
import org.cip4.jdflib.core.ElementName;
import org.cip4.jdflib.core.JDFDoc;
import org.cip4.jdflib.core.JDFResourceLink.EnumUsage;
import org.cip4.jdflib.core.KElement;
import org.cip4.jdflib.datatypes.JDFAttributeMap;
import org.cip4.jdflib.datatypes.VJDFAttributeMap;
import org.cip4.jdflib.resource.JDFResource.EnumPartIDKey;
import org.cip4.jdflib.resource.JDFResource.EnumResStatus;
import org.junit.jupiter.api.Test;

/**
 * @author Rainer Prosi, Heidelberger Druckmaschinen *
 */
public class ResourceHelperTest extends JDFTestCaseBase
{

	/**
	 *
	 */
	@Test
	public void testGetPartMap()
	{
		final XJDFHelper h = new XJDFHelper("j1", null, null);
		final SetHelper sh = h.getCreateSet(ElementName.NODEINFO, null);
		final JDFAttributeMap map = new JDFAttributeMap(AttributeName.SHEETNAME, "s1");
		final ResourceHelper rh = sh.getCreatePartition(map, false);
		assertEquals(map, rh.getPartMap());
	}

	/**
	 *
	 */
	@Test
	public void testGetPartKey()
	{
		final XJDFHelper h = new XJDFHelper("j1", null, null);
		final SetHelper sh = h.getCreateSet(ElementName.NODEINFO, null);
		final JDFAttributeMap map = new JDFAttributeMap(AttributeName.SHEETNAME, "s1");
		final ResourceHelper rh = sh.getCreatePartition(map, false);
		assertEquals("s1", rh.getPartKey(AttributeName.SHEETNAME));
	}

	/**
	 *
	 */
	@Test
	public void testMatchesEmpty()
	{
		final XJDFHelper h = new XJDFHelper("j1", null, null);
		final SetHelper sh = h.getCreateSet(ElementName.NODEINFO, null);
		final ResourceHelper rh = sh.getCreatePartition(null, false);
		final VJDFAttributeMap vmap = new VJDFAttributeMap();
		assertTrue(rh.matches(new JDFAttributeMap()));
		assertTrue(rh.matches(vmap));
		vmap.add(new JDFAttributeMap());
		assertTrue(rh.matches(vmap));
	}

	/**
	 *
	 */
	@Test
	public void testMatches()
	{
		final XJDFHelper h = new XJDFHelper("j1", null, null);
		final SetHelper sh = h.getCreateSet(ElementName.NODEINFO, null);
		final ResourceHelper rh = sh.getCreatePartition(new JDFAttributeMap("Run", "r1"), false);
		final VJDFAttributeMap vmap = new VJDFAttributeMap();
		assertFalse(rh.matches(new JDFAttributeMap()));
		assertFalse(rh.matches(vmap));
		vmap.add(new JDFAttributeMap());
		assertFalse(rh.matches(vmap));

	}

	/**
	 *
	 */
	@Test
	public void testGetResource()
	{
		final JDFDoc d = new JDFDoc(XJDFConstants.XJDF);
		final KElement root = d.getRoot();

		root.getCreateXPathElement("ResourceSet/Resource/Part");
		final KElement m = root.getCreateXPathElement("ResourceSet/Resource/Media");
		final ResourceHelper ph = new ResourceHelper(root.getXPathElement("ResourceSet/Resource"));
		assertEquals(ph.getResource(), m);
	}

	/**
	 *
	 */
	@Test
	public void testHasPartition()
	{
		final JDFDoc d = new JDFDoc(XJDFConstants.XJDF);
		final KElement root = d.getRoot();

		root.getCreateXPathElement("ResourceSet/Resource/Part");
		final ResourceHelper ph = new ResourceHelper(root.getXPathElement("ResourceSet/Resource"));
		assertTrue(ph.hasPartition(null));
		assertTrue(ph.hasPartition(new JDFAttributeMap()));
		assertFalse(ph.hasPartition(new JDFAttributeMap("a", "b")));

		ph.setPartMap(new JDFAttributeMap("a", "b"));
		assertFalse(ph.hasPartition(null));
		assertFalse(ph.hasPartition(new JDFAttributeMap()));
		assertTrue(ph.hasPartition(new JDFAttributeMap("a", "b")));

		ph.appendPartMap(new JDFAttributeMap("a", "c"));
		assertFalse(ph.hasPartition(null));
		assertFalse(ph.hasPartition(new JDFAttributeMap()));
		assertTrue(ph.hasPartition(new JDFAttributeMap("a", "b")));
		assertTrue(ph.hasPartition(new JDFAttributeMap("a", "c")));
	}

	/**
	 *
	 */
	@Test
	public void testSetResourceAttribute()
	{
		final ResourceHelper rh = new XJDFHelper("j1", null).getCreateSet(ElementName.MEDIA, EnumUsage.Input).getCreatePartition(0, false);
		assertNull(rh.getResourceAttribute("foo"));
		rh.setResourceAttribute("foo", "bar");
		assertEquals("bar", rh.getResourceAttribute("foo"));
	}

	/**
	 *
	 */
	@Test
	public void testGetStatus()
	{
		final JDFDoc d = new JDFDoc(XJDFConstants.XJDF);
		final KElement root = d.getRoot();

		root.getCreateXPathElement("ResourceSet/Resource/Part");
		final ResourceHelper ph = new ResourceHelper(root.getXPathElement("ResourceSet/Resource"));
		assertNull(ph.getStatus());
	}

	/**
	 *
	 */
	@Test
	public void testSetStatus()
	{
		final JDFDoc d = new JDFDoc(XJDFConstants.XJDF);
		final KElement root = d.getRoot();

		root.getCreateXPathElement("ResourceSet/Resource/Part");
		final ResourceHelper ph = new ResourceHelper(root.getXPathElement("ResourceSet/Resource"));
		ph.setStatus(EnumResStatus.Available);
		assertEquals(EnumResStatus.Available, ph.getStatus());
		ph.setStatus(EnumResStatus.Unavailable);
		assertEquals(EnumResStatus.Unavailable, ph.getStatus());
		ph.setStatus(EnumResStatus.Draft);
		assertNull(ph.getStatus());
	}

	/**
	 *
	 */
	@Test
	public void testGetHelper()
	{
		final JDFDoc d = new JDFDoc(XJDFConstants.XJDF);
		final KElement root = d.getRoot();

		root.getCreateXPathElement("ResourceSet[@Name=\"Media\"]/Resource/Part");
		final KElement m = root.getCreateXPathElement("ResourceSet/Resource/Media");
		final ResourceHelper ph = new ResourceHelper(root.getXPathElement("ResourceSet/Resource"));
		assertEquals(ph.getResource(), m);
		assertEquals(ResourceHelper.getHelper(m), ph);
		assertEquals(ResourceHelper.getHelper(m.getParentNode_KElement()), ph);
	}

	/**
	 *
	 */
	@Test
	public void testGetSet()
	{
		final JDFDoc d = new JDFDoc("XJDF");
		final KElement root = d.getRoot();

		final KElement set = root.getCreateXPathElement("ResourceSet");
		set.getCreateXPathElement("Resource/Part");
		final ResourceHelper ph = new ResourceHelper(root.getXPathElement("ResourceSet/Resource"));
		assertEquals(ph.getSet(), new SetHelper(set));
	}

	/**
	 *
	 */
	@Test
	public void testIsAsset()
	{
		final JDFDoc d = new JDFDoc(XJDFConstants.XJDF);
		final KElement root = d.getRoot();

		final KElement set = root.getCreateXPathElement("ResourceSet");
		set.setAttribute("Name", "Media");
		final KElement p = set.getCreateXPathElement("Resource/Part");
		final KElement m = set.getCreateXPathElement("Resource/Media");
		final KElement res = root.getXPathElement("ResourceSet/Resource");
		assertTrue(ResourceHelper.isAsset(res));
		assertFalse(ResourceHelper.isAsset(m));
		assertFalse(ResourceHelper.isAsset(p));
	}

	/**
	 *
	 */
	@Test
	public void testIsAssetTwo()
	{
		final JDFDoc d = new JDFDoc(XJDFConstants.XJDF);
		final KElement root = d.getRoot();

		final KElement set = root.getCreateXPathElement("ResourceSet");
		set.setAttribute("Name", "Media");
		final KElement p = set.getCreateXPathElement("Resource/Part");
		final KElement m = set.getCreateXPathElement("Resource/Media");
		final KElement res = root.getXPathElement("ResourceSet/Resource");
		assertTrue(ResourceHelper.isAsset(res, null));
		assertTrue(ResourceHelper.isAsset(res, "Media"));
		assertFalse(ResourceHelper.isAsset(res, "ExposedMedia"));
		assertFalse(ResourceHelper.isAsset(m, "Media"));
		assertFalse(ResourceHelper.isAsset(p, null));
	}

	/**
	 *
	 */
	@Test
	public void testGetXJDF()
	{
		final JDFDoc d = new JDFDoc(XJDFConstants.XJDF);
		final KElement root = d.getRoot();

		final KElement set = root.getCreateXPathElement("ResourceSet");
		set.getCreateXPathElement("Resource/Part");
		final ResourceHelper ph = new ResourceHelper(root.getXPathElement("ResourceSet/Resource"));
		assertEquals(ph.getXJDF(), new XJDFHelper(root));
	}

	/**
	 *
	 */
	@Test
	public void testisResourceElement()
	{
		final JDFDoc d = new JDFDoc(XJDFConstants.XJDF);
		final KElement root = d.getRoot();

		final KElement set = root.getCreateXPathElement("ResourceSet");
		set.setAttribute("Name", "Media");
		final KElement p = set.getCreateXPathElement("Resource/Part");
		final KElement m = set.getCreateXPathElement("Resource/Media");
		final KElement res = root.getXPathElement("ResourceSet/Resource");
		assertFalse(ResourceHelper.isResourceElement(res));
		assertTrue(ResourceHelper.isResourceElement(m));
		assertFalse(ResourceHelper.isResourceElement(p));
	}

	/**
	 *
	 */
	@Test
	public void testCleanup()
	{
		final JDFDoc d = new JDFDoc(XJDFConstants.XJDF);
		final KElement root = d.getRoot();

		root.getCreateXPathElement("ResourceSet/Resource/Part");
		final KElement m = root.getCreateXPathElement("ResourceSet/Resource/Media");
		final ResourceHelper ph = new ResourceHelper(root.getXPathElement("ResourceSet/Resource"));
		m.getParentNode_KElement().setID("iii");
		ph.cleanUp();
		assertEquals("iii", m.getParentNode_KElement().getAttribute(AttributeName.ID, null, null));
	}

	/**
	 *
	 */
	@Test
	public void testIgnoreOrder()
	{
		final JDFDoc d = new JDFDoc(XJDFConstants.XJDF);
		final KElement root = d.getRoot();

		final KElement r1 = root.getCreateXPathElement("ResourceSet/Resource/Part");
		final KElement m = root.getCreateXPathElement("ResourceSet/Resource/Media");
		final KElement ml = m.appendElement(ElementName.MEDIALAYERS);
		final KElement m1 = ml.appendElement(ElementName.MEDIA);
		final KElement m2 = ml.appendElement(ElementName.GLUE);
		final KElement m3 = ml.appendElement(ElementName.MEDIA);
		final ResourceHelper ph = new ResourceHelper(r1);
		ph.cleanUp();
		assertEquals(m1.getNextSiblingElement(), m2);
		assertEquals(m2.getNextSiblingElement(), m3);
	}

	/**
	 *
	 */
	@Test
	public void testSetPartMap()
	{
		final JDFDoc d = new JDFDoc(XJDFConstants.XJDF);
		final KElement root = d.getRoot();
		root.getCreateXPathElement("ResourceSet/Resource/Media");
		final ResourceHelper ph = new ResourceHelper(root.getXPathElement("ResourceSet/Resource"));
		final JDFAttributeMap map = new JDFAttributeMap("Drop", "D1");
		ph.setPartMap(map);
		assertEquals(ph.getPartMap(), map);
	}

	/**
	 *
	 */
	@Test
	public void testAppendPartMap()
	{
		final JDFDoc d = new JDFDoc(XJDFConstants.XJDF);
		final KElement root = d.getRoot();
		root.getCreateXPathElement("ResourceSet/Resource/Media");
		final ResourceHelper ph = new ResourceHelper(root.getXPathElement("ResourceSet/Resource"));
		final JDFAttributeMap map = new JDFAttributeMap("Drop", "D1");
		final VJDFAttributeMap vMap = new VJDFAttributeMap(map);
		ph.appendPartMapVector(vMap);
		assertEquals(ph.getPartMapVector(), vMap);
		vMap.add(new JDFAttributeMap("Drop", "D2"));
		ph.appendPartMapVector(vMap);
		assertEquals(ph.getPartMapVector(), vMap);
	}

	/**
	 *
	 */
	@Test
	public void testSetAmount()
	{
		final JDFDoc d = new JDFDoc(XJDFConstants.XJDF);
		final KElement root = d.getRoot();
		root.getCreateXPathElement("ResourceSet/Resource/Media");
		final ResourceHelper ph = new ResourceHelper(root.getXPathElement("ResourceSet/Resource"));
		final JDFAttributeMap map = new JDFAttributeMap(EnumPartIDKey.RibbonName, "D1");
		ph.setAmount(22, null, true);
		assertEquals(22, ph.getAmount(null, true), 0.001);
		ph.setAmount(33, null, true);
		assertEquals(33, ph.getAmount(null, true), 0.001);
		ph.setAmount(333, map, true);
		assertEquals(333, ph.getAmount(map, true), 0.001);
		assertEquals(33, ph.getAmount(null, true), 0.001);
	}

	/**
	 *
	 */
	@Test
	public void testGetAmountSum()
	{
		final JDFDoc d = new JDFDoc(XJDFConstants.XJDF);
		final KElement root = d.getRoot();
		root.getCreateXPathElement("ResourceSet/Resource/Media");
		final ResourceHelper ph = new ResourceHelper(root.getXPathElement("ResourceSet/Resource"));
		final JDFAttributeMap map = new JDFAttributeMap(EnumPartIDKey.RibbonName, "D1");
		ph.setAmount(22, null, true);
		ph.setAmount(33, null, false);
		assertEquals(22, ph.getAmountSum(true), 0.001);
		assertEquals(33, ph.getAmountSum(false), 0.001);
		ph.setAmount(333, map, true);
		assertEquals(355, ph.getAmountSum(true), 0.001);
		assertEquals(33, ph.getAmountSum(false), 0.001);
	}

	/**
	 *
	 */
	@Test
	public void testEnsureParts()
	{
		final JDFDoc d = new JDFDoc(XJDFConstants.XJDF);
		final KElement root = d.getRoot();
		root.getCreateXPathElement("ResourceSet/Resource/Media");
		final ResourceHelper ph = new ResourceHelper(root.getXPathElement("ResourceSet/Resource"));
		final JDFAttributeMap map = new JDFAttributeMap("SheetName", "S1");
		ph.ensurePart("SheetName", "S1");
		assertEquals(ph.getPartMap(), map);
		ph.ensurePart("Side", "Front");
		map.put("Side", "Front");

		assertEquals(ph.getPartMap(), map);
	}

	/**
	 *
	 */
	@Test
	public void testEnsureReference()
	{
		final XJDFHelper h = new XJDFHelper("j1", null);
		final ResourceHelper rhm = h.getCreateSet(ElementName.MEDIA, null).getCreatePartition(null, false);
		final KElement comp = h.getCreateSet(ElementName.COMPONENT, EnumUsage.Input).getCreatePartition(null, true).getResource();
		rhm.ensureReference(comp, null);
		assertEquals(rhm.getID(), comp.getAttribute("MediaRef"));
	}

	/**
	 *
	 */
	@Test
	public void testEnsureReference2()
	{
		final XJDFHelper h = new XJDFHelper("j1", null);
		final ResourceHelper rhm = h.getCreateSet(ElementName.MEDIA, null).getCreatePartition(null, false);
		final ResourceHelper comp = h.getCreateSet(ElementName.COMPONENT, EnumUsage.Input).getCreatePartition(null, true);
		rhm.ensureReference(comp, null);
		assertEquals(rhm.getID(), comp.getResourceAttribute("MediaRef"));
	}

	/**
	 *
	 */
	@Test
	public void testGetName()
	{
		final XJDFHelper h = new XJDFHelper("j1", null);
		final ResourceHelper rhm = h.getCreateSet(ElementName.MEDIA, null).getCreatePartition(null, false);
		assertEquals(ElementName.MEDIA, rhm.getName());
	}

}
