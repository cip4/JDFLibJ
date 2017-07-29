/*
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

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import org.cip4.jdflib.JDFTestCaseBase;
import org.cip4.jdflib.core.AttributeName;
import org.cip4.jdflib.core.ElementName;
import org.cip4.jdflib.core.JDFDoc;
import org.cip4.jdflib.core.KElement;
import org.cip4.jdflib.datatypes.JDFAttributeMap;
import org.cip4.jdflib.datatypes.VJDFAttributeMap;
import org.cip4.jdflib.resource.JDFResource.EnumResStatus;
import org.junit.Test;

/**
  * @author Rainer Prosi, Heidelberger Druckmaschinen *
 */
public class ResourceHelperTest extends JDFTestCaseBase
{
	/**
	 *
	 */
	@Test
	public void testGetResource()
	{
		JDFDoc d = new JDFDoc(XJDFConstants.XJDF);
		KElement root = d.getRoot();

		root.getCreateXPathElement("ResourceSet/Resource/Part");
		KElement m = root.getCreateXPathElement("ResourceSet/Resource/Media");
		ResourceHelper ph = new ResourceHelper(root.getXPathElement("ResourceSet/Resource"));
		assertEquals(ph.getResource(), m);
	}

	/**
	 *
	 */
	@Test
	public void testGetStatus()
	{
		JDFDoc d = new JDFDoc(XJDFConstants.XJDF);
		KElement root = d.getRoot();

		root.getCreateXPathElement("ResourceSet/Resource/Part");
		ResourceHelper ph = new ResourceHelper(root.getXPathElement("ResourceSet/Resource"));
		assertNull(ph.getStatus());
	}

	/**
	 *
	 */
	@Test
	public void testSetStatus()
	{
		JDFDoc d = new JDFDoc(XJDFConstants.XJDF);
		KElement root = d.getRoot();

		root.getCreateXPathElement("ResourceSet/Resource/Part");
		ResourceHelper ph = new ResourceHelper(root.getXPathElement("ResourceSet/Resource"));
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
		JDFDoc d = new JDFDoc(XJDFConstants.XJDF);
		KElement root = d.getRoot();

		root.getCreateXPathElement("ResourceSet[@Name=\"Media\"]/Resource/Part");
		KElement m = root.getCreateXPathElement("ResourceSet/Resource/Media");
		ResourceHelper ph = new ResourceHelper(root.getXPathElement("ResourceSet/Resource"));
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
		JDFDoc d = new JDFDoc("XJDF");
		KElement root = d.getRoot();

		KElement set = root.getCreateXPathElement("ResourceSet");
		set.getCreateXPathElement("Resource/Part");
		ResourceHelper ph = new ResourceHelper(root.getXPathElement("ResourceSet/Resource"));
		assertEquals(ph.getSet(), new SetHelper(set));
	}

	/**
	 *
	 */
	@Test
	public void testIsAsset()
	{
		JDFDoc d = new JDFDoc(XJDFConstants.XJDF);
		KElement root = d.getRoot();

		KElement set = root.getCreateXPathElement("ResourceSet");
		set.setAttribute("Name", "Media");
		KElement p = set.getCreateXPathElement("Resource/Part");
		KElement m = set.getCreateXPathElement("Resource/Media");
		KElement res = root.getXPathElement("ResourceSet/Resource");
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
		JDFDoc d = new JDFDoc(XJDFConstants.XJDF);
		KElement root = d.getRoot();

		KElement set = root.getCreateXPathElement("ResourceSet");
		set.setAttribute("Name", "Media");
		KElement p = set.getCreateXPathElement("Resource/Part");
		KElement m = set.getCreateXPathElement("Resource/Media");
		KElement res = root.getXPathElement("ResourceSet/Resource");
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
		JDFDoc d = new JDFDoc(XJDFConstants.XJDF);
		KElement root = d.getRoot();

		KElement set = root.getCreateXPathElement("ResourceSet");
		set.getCreateXPathElement("Resource/Part");
		ResourceHelper ph = new ResourceHelper(root.getXPathElement("ResourceSet/Resource"));
		assertEquals(ph.getXJDF(), new XJDFHelper(root));
	}

	/**
	 *
	 */
	@Test
	public void testisResourceElement()
	{
		JDFDoc d = new JDFDoc(XJDFConstants.XJDF);
		KElement root = d.getRoot();

		KElement set = root.getCreateXPathElement("ResourceSet");
		set.setAttribute("Name", "Media");
		KElement p = set.getCreateXPathElement("Resource/Part");
		KElement m = set.getCreateXPathElement("Resource/Media");
		KElement res = root.getXPathElement("ResourceSet/Resource");
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
		JDFDoc d = new JDFDoc(XJDFConstants.XJDF);
		KElement root = d.getRoot();

		root.getCreateXPathElement("ResourceSet/Resource/Part");
		KElement m = root.getCreateXPathElement("ResourceSet/Resource/Media");
		ResourceHelper ph = new ResourceHelper(root.getXPathElement("ResourceSet/Resource"));
		assertNull(m.getParentNode_KElement().getAttribute("ID", null, null));
		ph.cleanUp();
		assertEquals(ph.getPartition().getID(), m.getParentNode_KElement().getAttribute(AttributeName.ID, null, null));
	}

	/**
	 *
	 */
	@Test
	public void testIgnoreOrder()
	{
		JDFDoc d = new JDFDoc(XJDFConstants.XJDF);
		KElement root = d.getRoot();

		KElement r1 = root.getCreateXPathElement("ResourceSet/Resource/Part");
		KElement m = root.getCreateXPathElement("ResourceSet/Resource/Media");
		KElement ml = m.appendElement(ElementName.MEDIALAYERS);
		KElement m1 = ml.appendElement(ElementName.MEDIA);
		KElement m2 = ml.appendElement(ElementName.GLUE);
		KElement m3 = ml.appendElement(ElementName.MEDIA);
		ResourceHelper ph = new ResourceHelper(r1);
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
		JDFDoc d = new JDFDoc(XJDFConstants.XJDF);
		KElement root = d.getRoot();
		root.getCreateXPathElement("ResourceSet/Resource/Media");
		ResourceHelper ph = new ResourceHelper(root.getXPathElement("ResourceSet/Resource"));
		JDFAttributeMap map = new JDFAttributeMap("Drop", "D1");
		ph.setPartMap(map);
		assertEquals(ph.getPartMap(), map);
	}

	/**
	 *
	 */
	@Test
	public void testAppendPartMap()
	{
		JDFDoc d = new JDFDoc(XJDFConstants.XJDF);
		KElement root = d.getRoot();
		root.getCreateXPathElement("ResourceSet/Resource/Media");
		ResourceHelper ph = new ResourceHelper(root.getXPathElement("ResourceSet/Resource"));
		JDFAttributeMap map = new JDFAttributeMap("Drop", "D1");
		VJDFAttributeMap vMap = new VJDFAttributeMap(map);
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
	public void testEnsureParts()
	{
		JDFDoc d = new JDFDoc(XJDFConstants.XJDF);
		KElement root = d.getRoot();
		root.getCreateXPathElement("ResourceSet/Resource/Media");
		ResourceHelper ph = new ResourceHelper(root.getXPathElement("ResourceSet/Resource"));
		JDFAttributeMap map = new JDFAttributeMap("SheetName", "S1");
		ph.ensurePart("SheetName", "S1");
		assertEquals(ph.getPartMap(), map);
		ph.ensurePart("Side", "Front");
		map.put("Side", "Front");

		assertEquals(ph.getPartMap(), map);
	}

}
