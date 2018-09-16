/*
 * The CIP4 Software License, Version 1.0
 *
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

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNotSame;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import org.cip4.jdflib.JDFTestCaseBase;
import org.cip4.jdflib.core.AttributeName;
import org.cip4.jdflib.core.ElementName;
import org.cip4.jdflib.core.JDFDoc;
import org.cip4.jdflib.core.JDFResourceLink.EnumUsage;
import org.cip4.jdflib.core.KElement;
import org.cip4.jdflib.datatypes.JDFAttributeMap;
import org.cip4.jdflib.datatypes.JDFIntegerList;
import org.cip4.jdflib.datatypes.VJDFAttributeMap;
import org.junit.Test;

/**
 * @author Rainer Prosi, Heidelberger Druckmaschinen *
 */
public class SetHelperTest extends JDFTestCaseBase
{
	KElement root = null;

	/**
	 *
	 */
	@Test
	public void testGetSet()
	{
		assertNull(SetHelper.getHelper(null));
		final SetHelper sh = SetHelper.getHelper(root.getElement(SetHelper.RESOURCE_SET));
		assertEquals(sh.getName(), "Media");
	}

	/**
	 *
	 */
	@Test
	public void testGetName()
	{
		final SetHelper sh = new SetHelper(root.getElement(SetHelper.RESOURCE_SET));
		assertEquals(sh.getName(), "Media");
	}

	/**
	 *
	 */
	@Test
	public void testIsSet()
	{
		assertTrue(SetHelper.isSet(root.getElement(SetHelper.RESOURCE_SET)));
		assertTrue(SetHelper.isSet("ResourceSet"));
		assertFalse(SetHelper.isSet("ResourceSet_"));
		assertFalse(SetHelper.isSet("Media"));
	}

	/**
	 *
	 */
	@Test
	public void testGetPartition()
	{
		final SetHelper sh = new SetHelper(root.getElement(SetHelper.RESOURCE_SET));
		assertEquals(sh.getName(), ElementName.MEDIA);
		assertNull(sh.getPartition(1));
		assertNotNull(sh.getCreatePartition(1, true));
		assertNotNull(sh.getPartition(-1));
		assertNull(sh.getPartition(-3));
	}

	/**
	 *
	 */
	@Test
	public void testGetCPI()
	{
		final SetHelper sh = new SetHelper(root.getElement(SetHelper.RESOURCE_SET));
		sh.setCombinedProcessIndex(new JDFIntegerList(3));
		assertEquals(3, sh.getCombinedProcessIndex().getInt(0));

	}

	/**
	 *
	 */
	@Test
	public void testGetXJDF()
	{
		final SetHelper sh = new SetHelper(root.getElement(SetHelper.RESOURCE_SET));
		assertEquals(sh.getXJDF(), new XJDFHelper(root));
	}

	/**
	 *
	 */
	@Test
	public void testSetName()
	{
		final KElement set = KElement.createRoot(XJDFConstants.ResourceSet, null);
		final SetHelper sh = new SetHelper(set);
		sh.setName("foo");
		assertEquals("foo", sh.getName());
	}

	/**
	 *
	 */
	@Test
	public void testGetDependentJobPart()
	{
		final SetHelper sh = new SetHelper(root.getElement(SetHelper.RESOURCE_SET));
		assertNull(sh.getDependentJobParts());
		sh.setXPathValue("Dependent[1]/@JobPartID", "p1");
		assertEquals(sh.getDependentJobParts().get(0), "p1");
		sh.setXPathValue("Dependent[3]/@JobPartID", "p2");
		assertEquals(sh.getDependentJobParts().get(1), "p2");
		assertEquals(sh.getDependentJobParts().size(), 2);
	}

	/**
	 *
	 */
	@Test
	public void testGetPartitionMap()
	{
		final SetHelper sh = new SetHelper(root.getElement(SetHelper.RESOURCE_SET));
		assertEquals(sh.getName(), "Media");
		final ResourceHelper ph1 = sh.getPartition(new JDFAttributeMap("SheetName", "S1"));
		final ResourceHelper ph2 = sh.getCreatePartition(new JDFAttributeMap("SheetName", "S1"), true);
		assertNotSame(ph2, ph1);
		final ResourceHelper ph3 = sh.getPartition(new JDFAttributeMap("SheetName", "S1"));
		assertNotNull(ph3);
		assertEquals(ph2, ph3);
	}

	/**
	 *
	 */
	@Test
	public void testGetPartitionsMap()
	{
		final SetHelper sh = new SetHelper(root.getElement(SetHelper.RESOURCE_SET));
		assertEquals(sh.getName(), "Media");
		sh.getCreatePartition(new JDFAttributeMap("SheetName", "S1"), true);
		sh.getCreatePartition(new JDFAttributeMap("SheetName", "S2"), true);
		assertEquals(sh.getPartitions(new JDFAttributeMap("SheetName", "S2")).size(), 2);
	}

	/**
	 *
	 */
	@Test
	public void testGetSuperPartitionsMap()
	{
		final SetHelper sh = new SetHelper(root.getElement(SetHelper.RESOURCE_SET));
		assertEquals(sh.getName(), "Media");
		sh.getCreatePartition(new JDFAttributeMap("SheetName", "S1"), true);
		sh.getCreatePartition(new JDFAttributeMap("SheetName", "S2"), true);
		assertEquals(sh.getSuperPartitions(new JDFAttributeMap("SheetName", "S2")).size(), 1);
	}

	/**
	 *
	 */
	@Test
	public void testGetSuperPartitionsMap2()
	{
		final SetHelper sh = new SetHelper(root.getElement(SetHelper.RESOURCE_SET));
		assertEquals(sh.getName(), "Media");
		final JDFAttributeMap map = new JDFAttributeMap("SheetName", "S1");
		map.put(AttributeName.SIDE, "Front");
		sh.getCreatePartition(map, true);
		final JDFAttributeMap map2 = new JDFAttributeMap("SheetName", "S2");
		map2.put(AttributeName.SIDE, "Front");
		sh.getCreatePartition(map2, true);
		assertEquals(2, sh.getSuperPartitions(new JDFAttributeMap("Side", "Front")).size());
	}

	/**
	 *
	 */
	@Test
	public void testGetPartMapVector()
	{
		final SetHelper sh = new SetHelper(root.getElement(SetHelper.RESOURCE_SET));
		assertEquals(sh.getName(), "Media");
		final VJDFAttributeMap v = new VJDFAttributeMap();
		final JDFAttributeMap map1 = new JDFAttributeMap("SheetName", "S1");
		v.add(map1);
		sh.getCreatePartition(map1, true);
		final JDFAttributeMap map2 = new JDFAttributeMap("SheetName", "S2");
		v.add(map2);
		v.add(new JDFAttributeMap());
		sh.getCreatePartition(map2, true);
		final VJDFAttributeMap vp = sh.getPartMapVector();
		assertEquals(vp, v);
	}

	/**
	 *
	 */
	@Test
	public void testGetPartitionsVMap()
	{
		final SetHelper sh = new SetHelper(root.getElement(SetHelper.RESOURCE_SET));
		assertEquals(sh.getName(), "Media");
		sh.getCreatePartition(new JDFAttributeMap("SheetName", "S1"), true);
		sh.getCreatePartition(new JDFAttributeMap("SheetName", "S2"), true);
		assertEquals(sh.getPartitions(new VJDFAttributeMap(new JDFAttributeMap("SheetName", "S2"))).size(), 2);
	}

	/**
	 *
	 */
	@Test
	public void testGetPartitionByID()
	{
		final SetHelper sh = new SetHelper(root.getElement(SetHelper.RESOURCE_SET));
		assertNull(sh.getPartition("fooo"));
		assertNotNull(sh.getCreatePartition(1, true));
		sh.setID("id");
		final String id = sh.getPartition(0).getID();
		assertEquals(sh.getPartition(0), sh.getPartition(id));
	}

	/**
	 *
	 */
	@Test
	public void testGetCreatePartition()
	{
		final SetHelper sh = new XJDFHelper(root).getCreateSet(XJDFConstants.Resource, "FoldingParams", EnumUsage.Input);
		assertEquals(sh.getName(), "FoldingParams");
		assertNotNull(sh.getCreatePartition(0, false));
		assertNotNull(sh.getPartition(-1));
		assertNull(sh.getCreatePartition(0, false).getResource());
		assertNotNull(sh.getCreatePartition(0, true).getResource());
	}

	/**
	 *
	 */
	@Test
	public void testIndexOf()
	{
		final SetHelper sh = new XJDFHelper(root).getCreateSet(XJDFConstants.Resource, "FoldingParams", EnumUsage.Input);
		assertEquals(sh.indexOf(null), -1);
		final ResourceHelper rh = sh.getCreatePartition(0, false);
		assertEquals(sh.indexOf(rh), 0);
	}

	/**
	 *
	 */
	@Test
	public void testAddTypeToCPI()
	{
		final SetHelper sh = new XJDFHelper(root).getCreateSet(XJDFConstants.Resource, "FoldingParams", EnumUsage.Input);
		sh.addTypeToCPI(0);
		assertNull(sh.getCombinedProcessIndex());
		sh.setCombinedProcessIndex(JDFIntegerList.createIntegerList("1 3"));
		sh.addTypeToCPI(2);
		assertEquals(1, sh.getCombinedProcessIndex().getInt(0));
		assertEquals(4, sh.getCombinedProcessIndex().getInt(1));
	}

	/**
	 *
	 */
	@Test
	public void testRemoveTypeFromCPI()
	{
		final SetHelper sh = new XJDFHelper(root).getCreateSet(XJDFConstants.Resource, "FoldingParams", EnumUsage.Input);
		sh.addTypeToCPI(0);
		assertNull(sh.getCombinedProcessIndex());
		sh.setCombinedProcessIndex(JDFIntegerList.createIntegerList("1 4"));
		sh.removeTypeFromCPI(2);
		assertEquals(1, sh.getCombinedProcessIndex().getInt(0));
		assertEquals(3, sh.getCombinedProcessIndex().getInt(1));
		sh.removeTypeFromCPI(1);
		assertEquals(2, sh.getCombinedProcessIndex().getInt(0));
	}

	/**
	 *
	 */
	@Test
	public void testCleanup()
	{
		final KElement element = root.getElement("ResourceSet");
		final SetHelper sh = new SetHelper(element);
		sh.cleanUp();
		assertEquals(element.getAttribute("Name"), "Media");
	}

	/**
	 *
	 */
	@Override
	public void setUp()
	{
		final JDFDoc d = new JDFDoc(XJDFConstants.XJDF);
		root = d.getRoot();
		root.getCreateXPathElement("ResourceSet/Resource/Part");
		root.getCreateXPathElement("ResourceSet/Resource/Media");
		new XJDFHelper(root).cleanUp();
	}
}
