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

package org.cip4.jdflib.resource;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.cip4.jdflib.core.AttributeName;
import org.cip4.jdflib.core.ElementName;
import org.cip4.jdflib.core.JDFDoc;
import org.cip4.jdflib.core.JDFResourceLink.EnumUsage;
import org.cip4.jdflib.core.StringArray;
import org.cip4.jdflib.core.VElement;
import org.cip4.jdflib.core.VString;
import org.cip4.jdflib.datatypes.JDFAttributeMap;
import org.cip4.jdflib.datatypes.JDFAttributeMapArray;
import org.cip4.jdflib.datatypes.VJDFAttributeMap;
import org.cip4.jdflib.node.JDFNode;
import org.cip4.jdflib.resource.JDFResource.EnumPartIDKey;
import org.cip4.jdflib.resource.JDFResource.EnumPartUsage;
import org.cip4.jdflib.resource.JDFResource.EnumResourceClass;
import org.junit.jupiter.api.Test;

public class PartitionGetterTest
{

	/**
	 *
	 */
	@Test
	void testGetExplicit()
	{
		final JDFResource r = (JDFResource) new JDFDoc(ElementName.EMBOSSINGPARAMS).getRoot();
		final JDFResource r2 = r.addPartition(EnumPartIDKey.DeliveryUnit0, "d1");
		final PartitionGetter g = new PartitionGetter(r);
		assertEquals(r, g.getPartition(new JDFAttributeMap(), EnumPartUsage.Explicit));
		assertEquals(r2, g.getPartition(new JDFAttributeMap(EnumPartIDKey.DeliveryUnit0, "d1"), EnumPartUsage.Explicit));
	}

	/**
	 *
	 */
	@Test
	void testRemoveImplicitDup()
	{
		final JDFAttributeMapArray vmap = new JDFAttributeMapArray();
		for (int i = 0; i < 4; i++)
		{
			final JDFAttributeMap m = new JDFAttributeMap("a", "v" + i);
			vmap.add(m);
			final JDFAttributeMap m2 = m.clone();
			m2.put("b", "w" + i);
			vmap.add(m2);
		}

		final JDFResource r = (JDFResource) new JDFDoc(ElementName.EMBOSSINGPARAMS).getRoot();
		final PartitionGetter g = new PartitionGetter(r);
		g.removeImplicitDuplicates(vmap);
		assertEquals(4, vmap.size());
		for (int i = 0; i < 4; i++)
		{
			assertEquals(2, vmap.get(i).size());
		}
	}

	/**
	 *
	 */
	@Test
	void testGet()
	{
		final JDFResource r = (JDFResource) new JDFDoc(ElementName.EMBOSSINGPARAMS).getRoot();
		final JDFResource r1 = r.addPartition(EnumPartIDKey.DeliveryUnit0, "d1");
		final PartitionGetter g = new PartitionGetter(r);
		final JDFResource r2 = r.addPartition(EnumPartIDKey.DeliveryUnit0, "d2");
		assertEquals(r2, g.getPartition(new JDFAttributeMap(EnumPartIDKey.DeliveryUnit0, "d2"), EnumPartUsage.Explicit));
		assertEquals(r1, g.getPartition(new JDFAttributeMap(EnumPartIDKey.DeliveryUnit0, "d1"), EnumPartUsage.Explicit));
	}

	/**
	 *
	 */
	@Test
	void testGetCreate()
	{
		final JDFResource r = (JDFResource) new JDFDoc(ElementName.EMBOSSINGPARAMS).getRoot();
		final JDFResource r1 = r.getCreatePartition(new JDFAttributeMap(EnumPartIDKey.DeliveryUnit0, "d1"), null);
		final PartitionGetter g = new PartitionGetter(r);
		final JDFResource r2 = r.getCreatePartition(new JDFAttributeMap(EnumPartIDKey.DeliveryUnit0, "d2"), null);
		assertEquals(r2, g.getPartition(new JDFAttributeMap(EnumPartIDKey.DeliveryUnit0, "d2"), EnumPartUsage.Explicit));
		assertEquals(r1, g.getPartition(new JDFAttributeMap(EnumPartIDKey.DeliveryUnit0, "d1"), EnumPartUsage.Explicit));
	}

	/**
	 *
	 */
	@Test
	void testGetCreate_PartIDKeys()
	{
		final JDFResource r = (JDFResource) new JDFDoc(ElementName.EMBOSSINGPARAMS).getRoot();
		final JDFAttributeMap partMap = new JDFAttributeMap(EnumPartIDKey.DeliveryUnit0, "d1");
		partMap.put(EnumPartIDKey.DeliveryUnit1, "d2");
		final VString pik = new VString("DeliveryUnit0 DeliveryUnit1 DeliveryUnit2");
		final JDFResource r1 = r.getCreatePartition(partMap, pik);
		assertEquals(partMap, r1.getPartMap());
		assertEquals(new VString("DeliveryUnit0 DeliveryUnit1"), r.getPartIDKeys());
	}

	/**
	 *
	 */
	@Test
	void testGetimplicit()
	{
		final JDFResource r = (JDFResource) new JDFDoc(ElementName.EMBOSSINGPARAMS).getRoot();
		final PartitionGetter g = new PartitionGetter(r);
		assertEquals(r, g.getPartition(new JDFAttributeMap(), EnumPartUsage.Explicit));
		assertEquals(r, g.getPartition(new JDFAttributeMap(EnumPartIDKey.DeliveryUnit0, "d1"), EnumPartUsage.Implicit));
	}

	/**
	 *
	 */
	@Test
	void testGetimplicitGap()
	{
		final JDFResource r = (JDFResource) new JDFDoc(ElementName.EXPOSEDMEDIA).getRoot();
		r.addPartition(EnumPartIDKey.SignatureName, "sig1");
		JDFResource s2 = r.addPartition(EnumPartIDKey.SignatureName, "sig2").addPartition(EnumPartIDKey.SheetName, "s2");
		s2.addPartition(EnumPartIDKey.Side, "Front");
		s2.addPartition(EnumPartIDKey.Side, "Back");
		r.setPartUsage(EnumPartUsage.Implicit);
		final PartitionGetter g = new PartitionGetter(r);
		assertEquals(r, g.getPartition(new JDFAttributeMap(), null));
		assertEquals(s2, g.getPartition(new JDFAttributeMap(EnumPartIDKey.SheetName, "s2"), EnumPartUsage.Implicit));
		assertEquals(s2, g.getPartition(new JDFAttributeMap(EnumPartIDKey.SheetName, "s2"), EnumPartUsage.Sparse));
	}

	/**
	 *
	 */
	@Test
	void testGetimplicitGap2()
	{
		final JDFResource r = (JDFResource) new JDFDoc(ElementName.EXPOSEDMEDIA).getRoot();
		JDFResource s1 = r.addPartition(EnumPartIDKey.SignatureName, "sig1").addPartition(EnumPartIDKey.SheetName, "s1");
		JDFResource s2 = r.addPartition(EnumPartIDKey.SignatureName, "sig2").addPartition(EnumPartIDKey.SheetName, "s2");
		s1.addPartition(EnumPartIDKey.Side, "Front");
		s1.addPartition(EnumPartIDKey.Side, "Back");
		s2.addPartition(EnumPartIDKey.Side, "Front").addPartition(EnumPartIDKey.Separation, "B");
		s2.addPartition(EnumPartIDKey.Side, "Back").addPartition(EnumPartIDKey.Separation, "B");
		r.setPartUsage(EnumPartUsage.Implicit);
		final PartitionGetter g = new PartitionGetter(r);
		assertEquals(r, g.getPartition(new JDFAttributeMap(EnumPartIDKey.Side, "Back"), null));
		assertEquals(r, g.getPartition(new JDFAttributeMap(), null));
		assertEquals(s2, g.getPartition(new JDFAttributeMap(EnumPartIDKey.SheetName, "s2"), EnumPartUsage.Implicit));
		assertEquals(s2, g.getPartition(new JDFAttributeMap(EnumPartIDKey.SheetName, "s2"), EnumPartUsage.Sparse));
		assertEquals(s2, g.getPartition(new JDFAttributeMap(EnumPartIDKey.Separation, "B"), EnumPartUsage.Implicit));
		assertEquals(s2, g.getPartition(new JDFAttributeMap(EnumPartIDKey.Separation, "B"), EnumPartUsage.Sparse));
	}

	/**
	 *
	 */
	@Test
	void testGetimplicitVector()
	{
		final JDFResource r = (JDFResource) new JDFDoc(ElementName.EMBOSSINGPARAMS).getRoot();
		final JDFResource p = r.addPartition(EnumPartIDKey.DeliveryUnit0, "d1");
		final PartitionGetter g = new PartitionGetter(r);
		final VJDFAttributeMap vMap = new VJDFAttributeMap();
		final JDFAttributeMap d1 = new JDFAttributeMap(EnumPartIDKey.DeliveryUnit0, "d1");
		d1.put("Run", "r1");
		final JDFAttributeMap d2 = new JDFAttributeMap(EnumPartIDKey.DeliveryUnit0, "d1");
		d2.put("Run", "r1");

		vMap.add(d1);
		vMap.add(d2);
		final VElement partitionVector = g.getPartitionVector(vMap, EnumPartUsage.Implicit);
		assertEquals(1, partitionVector.size());
		assertEquals(p, partitionVector.get(0));
	}

	/**
	*
	*/
	@Test
	void testGetimplicitVector2()
	{
		final JDFResource r = (JDFResource) new JDFDoc(ElementName.EMBOSSINGPARAMS).getRoot();
		final JDFResource p = r.addPartition(EnumPartIDKey.DeliveryUnit0, "d1");
		final PartitionGetter g = new PartitionGetter(r);
		final VJDFAttributeMap vMap = new VJDFAttributeMap();
		final JDFAttributeMap d1 = new JDFAttributeMap(EnumPartIDKey.DeliveryUnit0, "d1");
		d1.put("Run", "r1");
		final JDFAttributeMap d2 = new JDFAttributeMap(EnumPartIDKey.DeliveryUnit0, "d1");
		d2.put("Run", "r2");
		final JDFAttributeMap d3 = new JDFAttributeMap(EnumPartIDKey.DeliveryUnit0, "d2");
		d3.put("Run", "r1");
		final JDFAttributeMap d4 = new JDFAttributeMap(EnumPartIDKey.DeliveryUnit0, "d2");
		d4.put("Run", "r2");

		vMap.add(d1);
		vMap.add(d2);
		vMap.add(d3);
		vMap.add(d4);
		final VElement partitionVector = g.getPartitionVector(vMap, EnumPartUsage.Sparse);
		assertEquals(1, partitionVector.size());
		assertEquals(p, partitionVector.get(0));
	}

	/**
	 *
	 */
	@Test
	void testPVResMulti()
	{
		final JDFResource r = (JDFResource) new JDFDoc(ElementName.EXPOSEDMEDIA).getRoot();
		final JDFResource ab = r.addPartition(EnumPartIDKey.PartVersion, "a b");
		final JDFResource cd = r.addPartition(EnumPartIDKey.PartVersion, "c d");
		final PartitionGetter pg = new PartitionGetter(r);
		pg.setStrictPartVersion(false);
		assertEquals(ab, pg.getPartition(new JDFAttributeMap(AttributeName.PARTVERSION, "a"), EnumPartUsage.Explicit));
		assertEquals(ab, pg.getPartition(new JDFAttributeMap(AttributeName.PARTVERSION, "a b"), EnumPartUsage.Explicit));
		assertEquals(cd, pg.getPartition(new JDFAttributeMap(AttributeName.PARTVERSION, "c"), EnumPartUsage.Explicit));
		assertNull(pg.getPartition(new JDFAttributeMap(AttributeName.PARTVERSION, "e"), EnumPartUsage.Explicit));
	}

	/**
	 *
	 */
	@Test
	void testPVResSkip()
	{
		final JDFResource r = (JDFResource) new JDFDoc(ElementName.EXPOSEDMEDIA).getRoot();
		final JDFResource ab = r.addPartition(EnumPartIDKey.SheetName, "sh1").addPartition(EnumPartIDKey.PartVersion, "a b");
		final JDFResource cd = r.addPartition(EnumPartIDKey.SheetName, "sh2").addPartition(EnumPartIDKey.PartVersion, "c d");
		final JDFResource abs1 = ab.addPartition(EnumPartIDKey.Separation, "s1");
		final JDFResource cds1 = cd.addPartition(EnumPartIDKey.Separation, "s1");
		final JDFResource cds2 = cd.addPartition(EnumPartIDKey.Separation, "s2");

		final PartitionGetter pg = new PartitionGetter(r);
		pg.setStrictPartVersion(false);

		final JDFAttributeMap m1 = new JDFAttributeMap(EnumPartIDKey.SheetName, "sh1");
		m1.put(EnumPartIDKey.Separation, "s1");

		final JDFAttributeMap m2 = new JDFAttributeMap(EnumPartIDKey.SheetName, "sh2");
		m2.put(EnumPartIDKey.Separation, "s1");

		assertEquals(abs1, pg.getPartition(m1, EnumPartUsage.Implicit));
		assertEquals(cds1, pg.getPartition(m2, EnumPartUsage.Implicit));

		m1.put(EnumPartIDKey.Separation, "s2");
		m2.put(EnumPartIDKey.Separation, "s2");
		assertEquals(cds2, pg.getPartition(m2, EnumPartUsage.Implicit));
		assertEquals(cds2, pg.getPartition(m2, EnumPartUsage.Sparse));
		assertEquals(ab, pg.getPartition(m1, EnumPartUsage.Implicit));
		assertEquals(null, pg.getPartition(m1, EnumPartUsage.Sparse));

	}

	/**
	 *
	 */
	@Test
	void testPVAll()
	{
		final JDFResource r = (JDFResource) new JDFDoc(ElementName.EXPOSEDMEDIA).getRoot();
		final JDFResource ab = r.addPartition(EnumPartIDKey.SheetName, "sh1").addPartition(EnumPartIDKey.PartVersion, "a b");
		final JDFResource cd = r.addPartition(EnumPartIDKey.SheetName, "sh2").addPartition(EnumPartIDKey.PartVersion, "c d");
		final JDFResource all = r.addPartition(EnumPartIDKey.SheetName, "sh3").addPartition(EnumPartIDKey.PartVersion, "All");
		final JDFResource abs1 = ab.addPartition(EnumPartIDKey.Separation, "s1");
		final JDFResource cds1 = cd.addPartition(EnumPartIDKey.Separation, "s1");
		final JDFResource cds2 = cd.addPartition(EnumPartIDKey.Separation, "s2");
		final JDFResource cda3 = all.addPartition(EnumPartIDKey.Separation, "s3");

		final PartitionGetter pg = new PartitionGetter(r);
		pg.setStrictPartVersion(false);

		final JDFAttributeMap m1 = new JDFAttributeMap(EnumPartIDKey.PartVersion, "c");
		m1.put(EnumPartIDKey.Separation, "s3");

		assertEquals(cda3, pg.getPartition(m1, EnumPartUsage.Implicit));
		assertEquals(cda3, pg.getPartition(m1, EnumPartUsage.Sparse));

	}

	/**
	 *
	 */
	@Test
	void testReorderPartitions()
	{
		final JDFResource r = (JDFResource) new JDFDoc(ElementName.EXPOSEDMEDIA).getRoot();
		r.appendElement(ElementName.MEDIA);
		r.setID("id");
		final JDFResource ab = r.addPartition(EnumPartIDKey.SheetName, "sh1").addPartition(EnumPartIDKey.PartVersion, "a b");
		final JDFResource cd = r.addPartition(EnumPartIDKey.SheetName, "sh2").addPartition(EnumPartIDKey.PartVersion, "c d");
		final JDFAttributeMap abs1 = ab.addPartition(EnumPartIDKey.Separation, "s1").getPartMap();
		final JDFAttributeMap cds1 = cd.addPartition(EnumPartIDKey.Separation, "s1").getPartMap();
		final JDFAttributeMap cds2 = cd.addPartition(EnumPartIDKey.Separation, "s2").getPartMap();

		final PartitionGetter pg = new PartitionGetter(r);
		pg.reorderPartitions(new StringArray("SheetName Separation PartVersion"));
		assertNotNull(pg.getPartition(null, null).getElement(ElementName.MEDIA));
		assertNotNull(pg.getPartition(abs1, EnumPartUsage.Explicit));
		assertNotNull(r.getPartition(abs1, EnumPartUsage.Explicit));
		assertNotNull(pg.getPartition(cds1, EnumPartUsage.Explicit));
		assertNotNull(r.getPartition(cds2, EnumPartUsage.Explicit));
		assertEquals("id", r.getID());
	}

	/**
	 *
	 */
	@Test
	void testPVResStrict()
	{
		final JDFResource r = (JDFResource) new JDFDoc(ElementName.EXPOSEDMEDIA).getRoot();
		final JDFResource ab = r.addPartition(EnumPartIDKey.PartVersion, "a b");
		final JDFResource cd = r.addPartition(EnumPartIDKey.PartVersion, "c d");
		final PartitionGetter pg = new PartitionGetter(r);
		pg.setStrictPartVersion(true);
		assertNull(pg.getPartition(new JDFAttributeMap(AttributeName.PARTVERSION, "a"), EnumPartUsage.Explicit));
		assertEquals(ab, pg.getPartition(new JDFAttributeMap(AttributeName.PARTVERSION, "a b"), EnumPartUsage.Explicit));
		assertEquals(cd, pg.getPartition(new JDFAttributeMap(AttributeName.PARTVERSION, "c d"), EnumPartUsage.Explicit));
		assertNull(pg.getPartition(new JDFAttributeMap(AttributeName.PARTVERSION, "e"), EnumPartUsage.Explicit));
	}

	/**
	*
	*/
	@Test
	void testPVResDouble()
	{
		final JDFResource r0 = (JDFResource) new JDFDoc(ElementName.EXPOSEDMEDIA).getRoot();
		final JDFResource r = r0.addPartition(EnumPartIDKey.SheetName, "s1");

		final JDFResource a = r.addPartition(EnumPartIDKey.PartVersion, "a");
		final JDFResource b = r.addPartition(EnumPartIDKey.PartVersion, "b");
		final JDFResource c = r.addPartition(EnumPartIDKey.PartVersion, "c");
		final PartitionGetter pg = new PartitionGetter(r0);

		final JDFAttributeMap m = new JDFAttributeMap(AttributeName.PARTVERSION, "a b");
		m.put(AttributeName.SHEETNAME, "s1");
		assertEquals(r, pg.getPartition(m, EnumPartUsage.Sparse));
	}

	/**
	 *
	 */
	@Test
	void testPVLinkMulti()
	{
		final JDFResource r = (JDFResource) new JDFDoc(ElementName.EXPOSEDMEDIA).getRoot();
		final JDFResource ab = r.addPartition(EnumPartIDKey.PartVersion, "a");
		final JDFResource cd = r.addPartition(EnumPartIDKey.PartVersion, "c");
		final PartitionGetter pg = new PartitionGetter(r);
		pg.setStrictPartVersion(false);
		assertEquals(ab, pg.getPartition(new JDFAttributeMap(AttributeName.PARTVERSION, "a"), EnumPartUsage.Explicit));
		assertEquals(ab, pg.getPartition(new JDFAttributeMap(AttributeName.PARTVERSION, "a b"), EnumPartUsage.Explicit));
		assertEquals(cd, pg.getPartition(new JDFAttributeMap(AttributeName.PARTVERSION, "c d e"), EnumPartUsage.Explicit));
		assertNull(pg.getPartition(new JDFAttributeMap(AttributeName.PARTVERSION, "e"), EnumPartUsage.Explicit));
	}

	/**
	 *
	 */
	@Test
	void testGetimplicitFromMap()
	{
		final JDFResource r = (JDFResource) new JDFDoc(ElementName.EMBOSSINGPARAMS).getRoot();
		final PartitionGetter g = new PartitionGetter(r);
		assertEquals(new JDFAttributeMap(), g.getImplicitPartitionFromMap(new JDFAttributeMap(EnumPartIDKey.DeliveryUnit0, "d1"), EnumPartUsage.Implicit));
	}

	/**
	 *
	 */
	@Test
	void testHasGap()
	{
		final VString v = new VString("a b c");
		final JDFResource r = (JDFResource) new JDFDoc(ElementName.EMBOSSINGPARAMS).getRoot();
		final PartitionGetter g = new PartitionGetter(r);
		assertTrue(g.hasGap(new JDFAttributeMap("b", "b1"), v));
		assertFalse(g.hasGap(new JDFAttributeMap("d", "b1"), v));
		final JDFAttributeMap next = new JDFAttributeMap("a", "b1");
		assertFalse(g.hasGap(next, v));
		next.put("b", "ccc");
		assertFalse(g.hasGap(next, v));
		next.put("c", "ccc");
		next.remove("b");
		assertTrue(g.hasGap(next, v));
		assertFalse(g.hasGap(null, v));
	}

	/**
	 *
	 */
	@Test
	void testFillSparse()
	{
		final JDFResource r = (JDFResource) new JDFDoc(ElementName.EMBOSSINGPARAMS).getRoot();
		final JDFResource s1 = r.addPartition(EnumPartIDKey.SheetName, "a1");
		final JDFResource f = s1.addPartition(EnumPartIDKey.Side, "Front");
		final JDFResource b = s1.addPartition(EnumPartIDKey.Side, "Back");
		final JDFResource sepb = f.addPartition(EnumPartIDKey.Separation, "b");
		final JDFResource sep = f.addPartition(EnumPartIDKey.Separation, "c");
		final JDFResource sepm = f.addPartition(EnumPartIDKey.Separation, "m");
		final JDFResource v1 = sepb.addPartition(EnumPartIDKey.PartVersion, "v1");
		final JDFResource v2 = sepb.addPartition(EnumPartIDKey.PartVersion, "v2");
		final PartitionGetter g = new PartitionGetter(r);
		g.fillSparse();
		assertEquals(12, r.getLeafArray(false).size());
	}

	/**
	 *
	 */
	@Test
	void testHasGapRange()
	{
		final JDFResource r = (JDFResource) new JDFDoc(ElementName.EMBOSSINGPARAMS).getRoot();
		final JDFAttributeMap partMap = new JDFAttributeMap();
		partMap.put(AttributeName.SIGNATURENAME, "sig1");
		partMap.put(AttributeName.SHEETNAME, "s1");
		final JDFAttributeMap pSheet = new JDFAttributeMap(partMap);
		partMap.put(AttributeName.BINDERYSIGNATURENAME, "bs1");
		partMap.put(AttributeName.CELLINDEX, "0");
		r.getCreatePartition(partMap, new VString("SignatureName SheetName BinderySignatureName CellIndex"));
		partMap.put(AttributeName.CELLINDEX, "2 ~ 4");
		final JDFResource leaf = r.getCreatePartition(partMap, new VString("SignatureName SheetName BinderySignatureName CellIndex"));
		final PartitionGetter g = new PartitionGetter(r);
		final JDFAttributeMap m2 = new JDFAttributeMap(partMap);
		m2.put(AttributeName.CELLINDEX, "3");
		final JDFResource sheet = g.getPartition(pSheet, null);
		assertEquals(leaf, g.getPartition(m2, null));
		assertEquals(leaf, g.getPartition(partMap, null));
		assertEquals(leaf, r.getPartition(m2, null));
		assertEquals(leaf, r.getPartition(partMap, null));
		assertEquals(leaf, sheet.getPartition(m2, null));
		assertEquals(leaf, sheet.getPartition(partMap, null));
		assertEquals(leaf, leaf.getPartition(m2, null));
		assertEquals(leaf, leaf.getPartition(partMap, null));

		m2.remove(AttributeName.BINDERYSIGNATURENAME);
		assertEquals(leaf, g.getPartition(m2, null));
		assertEquals(leaf, r.getPartition(m2, null));
		assertEquals(leaf, sheet.getPartition(m2, null));
		assertEquals(leaf, leaf.getPartition(m2, null));
		m2.remove(AttributeName.SIGNATURENAME);
		assertEquals(leaf, g.getPartition(m2, null));
		assertEquals(leaf, r.getPartition(m2, null));
		assertEquals(leaf, sheet.getPartition(m2, null));

		assertEquals(leaf, leaf.getPartition(m2, null));

	}

	/**
	 *
	 */
	@Test
	void testMultiGapRange()
	{
		final JDFResource r = (JDFResource) new JDFDoc(ElementName.EMBOSSINGPARAMS).getRoot();
		r.setPartUsage(EnumPartUsage.Implicit);
		final JDFAttributeMap partMap = new JDFAttributeMap();
		partMap.put(AttributeName.SIGNATURENAME, "sig1");
		partMap.put(AttributeName.SHEETNAME, "s1");
		final JDFAttributeMap pSheet = new JDFAttributeMap(partMap);
		partMap.put(AttributeName.BINDERYSIGNATURENAME, "bs1");
		partMap.put(AttributeName.CELLINDEX, "0");
		r.getCreatePartition(partMap, new VString("SignatureName SheetName BinderySignatureName CellIndex"));
		partMap.put(AttributeName.BINDERYSIGNATURENAME, "bs2");
		r.getCreatePartition(partMap, new VString("SignatureName SheetName BinderySignatureName CellIndex"));
		final JDFResource leaf = r.getCreatePartition(partMap, new VString("SignatureName SheetName BinderySignatureName CellIndex"));
		final PartitionGetter g = new PartitionGetter(r);
		final JDFAttributeMap m2 = new JDFAttributeMap(partMap);
		final JDFResource sheet = g.getPartition(pSheet, null);

		m2.remove(AttributeName.BINDERYSIGNATURENAME);
		assertEquals(sheet, g.getPartition(m2, null));
		assertEquals(sheet, r.getPartition(m2, null));
		assertEquals(sheet, sheet.getPartition(m2, null));
		assertEquals(leaf, leaf.getPartition(m2, null));
		m2.remove(AttributeName.SIGNATURENAME);
		assertEquals(sheet, g.getPartition(m2, null));
		assertEquals(sheet, r.getPartition(m2, null));
		assertEquals(sheet, sheet.getPartition(m2, null));
		assertEquals(leaf, leaf.getPartition(m2, null));
	}

	/**
	 *
	 */
	@Test
	void testGetCompleteMap()
	{
		final JDFResource r = (JDFResource) new JDFDoc(ElementName.EMBOSSINGPARAMS).getRoot();
		final JDFAttributeMap partMap = new JDFAttributeMap();
		partMap.put(AttributeName.SIGNATURENAME, "sig1");
		partMap.put(AttributeName.SHEETNAME, "s1");
		partMap.put(AttributeName.BINDERYSIGNATURENAME, "bs1");
		partMap.put(AttributeName.CELLINDEX, "0");
		r.getCreatePartition(partMap, new VString("SignatureName SheetName BinderySignatureName CellIndex"));
		partMap.put(AttributeName.CELLINDEX, "2 ~ 4");
		final JDFResource leaf = r.getCreatePartition(partMap, new VString("SignatureName SheetName BinderySignatureName CellIndex"));
		final PartitionGetter g = new PartitionGetter(r);
		final JDFAttributeMap m2 = new JDFAttributeMap(partMap);
		m2.put(AttributeName.CELLINDEX, "3");
		final PartitionGetter g2 = new PartitionGetter(leaf);
		assertEquals(g2.getCompletePartMap(m2, false), partMap);

		m2.remove(AttributeName.BINDERYSIGNATURENAME);
		assertEquals(g2.getCompletePartMap(m2, false), partMap);
		m2.remove(AttributeName.SIGNATURENAME);
		assertEquals(g2.getCompletePartMap(m2, false), partMap);
	}

	/**
	*
	*/
	@Test
	void testLastPos()
	{
		final VString v = new VString("a b c");
		final JDFResource r = (JDFResource) new JDFDoc(ElementName.EMBOSSINGPARAMS).getRoot();
		final PartitionGetter g = new PartitionGetter(r);
		assertEquals(1, g.lastPos(new JDFAttributeMap("b", "b1"), v, true));
		assertEquals(3, g.lastPos(new JDFAttributeMap("d", "b1"), v, true));
		final JDFAttributeMap next = new JDFAttributeMap("a", "b1");
		assertEquals(0, g.lastPos(new JDFAttributeMap("a", "b1"), v, true));
		next.put("b", "ccc");
		assertEquals(1, g.lastPos(next, v, true));
		next.put("c", "ccc");
		assertEquals(2, g.lastPos(next, v, true));
		next.remove("b");
		assertEquals(2, g.lastPos(next, v, true));
	}

	/**
	 *
	 */
	@Test
	void testSpecialFromMap()
	{
		final JDFResource r = (JDFResource) new JDFDoc(ElementName.EMBOSSINGPARAMS).getRoot();
		final PartitionGetter g = new PartitionGetter(r);
		final JDFResource r2 = r.addPartition(EnumPartIDKey.SignatureName, "Sig1");
		final JDFResource r23 = r2.addPartition(EnumPartIDKey.SheetName, "S1");

		final JDFAttributeMap pm = new JDFAttributeMap();
		pm.put(EnumPartIDKey.SignatureName, "Sig1");
		pm.put(EnumPartIDKey.SheetName, "S1");
		assertEquals(pm, g.specialSearch(new JDFAttributeMap(EnumPartIDKey.SheetName, "S1"), EnumPartUsage.Explicit).get(0));

		for (int i = 0; i < 4; i++)
		{
			r23.addPartition(EnumPartIDKey.RibbonName, "R" + i);
		}
		assertEquals(pm, g.specialSearch(new JDFAttributeMap(EnumPartIDKey.SheetName, "S1"), EnumPartUsage.Explicit).get(0));

		final JDFResource r3 = r.addPartition(EnumPartIDKey.SignatureName, "Sig2");
		final JDFResource r33 = r3.addPartition(EnumPartIDKey.SheetName, "S1");
		assertNotNull(g.specialSearch(new JDFAttributeMap(EnumPartIDKey.SheetName, "S1"), EnumPartUsage.Explicit).get(1));

	}

	/**
	 *
	 */
	@Test
	void testGetimplicitFromMap2()
	{
		final JDFResource r = (JDFResource) new JDFDoc(ElementName.EMBOSSINGPARAMS).getRoot();
		final JDFResource r2 = r.addPartition(EnumPartIDKey.SignatureName, "S1");
		final PartitionGetter g = new PartitionGetter(r);
		assertEquals(new JDFAttributeMap(), g.getImplicitPartitionFromMap(new JDFAttributeMap(EnumPartIDKey.DeliveryUnit0, "d1"), EnumPartUsage.Implicit));
		final JDFAttributeMap p1 = new JDFAttributeMap(EnumPartIDKey.SignatureName, "S1");
		final JDFAttributeMap p2 = p1.clone();
		p2.put(EnumPartIDKey.SheetName, "SH1");
		assertEquals(p1, g.getImplicitPartitionFromMap(p2, EnumPartUsage.Implicit));
	}

	/**
	 *
	 */
	@Test
	void testGetMissing()
	{
		final JDFResource r = (JDFResource) new JDFDoc(ElementName.EMBOSSINGPARAMS).getRoot();
		final JDFResource r2 = r.addPartition(EnumPartIDKey.SignatureName, "S1");
		final JDFResource r3 = r2.addPartition(EnumPartIDKey.SheetName, "SH1");
		final PartitionGetter g = new PartitionGetter(r);
		final JDFAttributeMap p1 = new JDFAttributeMap(EnumPartIDKey.SheetName, "SH1");
		assertEquals(r3, g.getPartition(p1, null));
	}

	/**
	 *
	 */
	@Test
	void testGetMissingWierd()
	{
		final JDFResource r = (JDFResource) new JDFDoc(ElementName.EMBOSSINGPARAMS).getRoot();
		final JDFResource r2 = r.addPartition(EnumPartIDKey.SignatureName, "S1");
		final JDFResource r3 = r2.addPartition(EnumPartIDKey.SheetName, "SH1");
		final JDFResource r41 = r3.addPartition(EnumPartIDKey.PartVersion, "PV1");
		final JDFResource r411 = r41.addPartition(EnumPartIDKey.BlockName, "B1.1");
		final JDFResource r42 = r3.addPartition(EnumPartIDKey.PartVersion, "PV2");
		final JDFResource r421 = r42.addPartition(EnumPartIDKey.BlockName, "B1.2");
		final PartitionGetter g = new PartitionGetter(r);
		final JDFAttributeMap p1 = r3.getPartMap();
		p1.put(EnumPartIDKey.BlockName, "B1");
		assertEquals(r3, g.getPartition(p1, EnumPartUsage.Implicit));
	}

	/**
	 *
	 */
	@Test
	void testGetimplicitFromMapnix()
	{
		final JDFResource r = (JDFResource) new JDFDoc(ElementName.EMBOSSINGPARAMS).getRoot();
		final JDFResource r2 = r.addPartition(EnumPartIDKey.SignatureName, "S1");
		final PartitionGetter g = new PartitionGetter(r);
		assertEquals(new JDFAttributeMap(), g.getImplicitPartitionFromMap(new JDFAttributeMap(EnumPartIDKey.DeliveryUnit0, "d1"), EnumPartUsage.Implicit));
		final JDFAttributeMap p1 = new JDFAttributeMap(EnumPartIDKey.SignatureName, "S1");
		final JDFAttributeMap p2 = p1.clone();
		p2.put(EnumPartIDKey.SheetName, "SH1");
		assertEquals(p1, g.getImplicitPartitionFromMap(p2, EnumPartUsage.Implicit));
	}

	/**
	 *
	 */
	@Test
	void testIdentical()
	{
		final JDFResource r = (JDFResource) new JDFDoc(ElementName.EXPOSEDMEDIA).getRoot();
		final PartitionGetter pg = new PartitionGetter(r);
		final JDFResource rs = r.addPartition(EnumPartIDKey.SheetName, "s1");
		final JDFResource a = rs.addPartition(EnumPartIDKey.Separation, "a");
		final JDFResource b = rs.addPartition(EnumPartIDKey.Separation, "b");
		final JDFResource c = rs.addPartition(EnumPartIDKey.Separation, "c");
		b.setIdentical(a);
		c.setIdentical(a);
		pg.setFollowIdentical(true);
		final JDFAttributeMap m = new JDFAttributeMap(AttributeName.SHEETNAME, "s1");
		m.put(AttributeName.SEPARATION, "a");
		assertEquals(a, pg.getPartition(m, EnumPartUsage.Explicit));
		m.put(AttributeName.SEPARATION, "b");
		assertEquals(a, pg.getPartition(m, EnumPartUsage.Explicit));
		m.put(AttributeName.SEPARATION, "c");
		assertEquals(a, pg.getPartition(m, EnumPartUsage.Explicit));
		m.put(AttributeName.SEPARATION, "d");
		assertNull(pg.getPartition(m, EnumPartUsage.Explicit));
	}

	/**
	*
	*/
	@Test
	void testIdenticalSibling()
	{
		final JDFResource r = (JDFResource) new JDFDoc(ElementName.EXPOSEDMEDIA).getRoot();
		final PartitionGetter pg = new PartitionGetter(r);
		final JDFResource rs = r.addPartition(EnumPartIDKey.SheetName, "s1");
		final JDFResource rf = r.addPartition(EnumPartIDKey.SheetName, "s2");
		final JDFResource af = rf.addPartition(EnumPartIDKey.Separation, "a");
		final JDFResource as = rs.addPartition(EnumPartIDKey.Separation, "a");
		af.setIdentical(as);
		pg.setFollowIdentical(true);
		final JDFAttributeMap m = new JDFAttributeMap(AttributeName.SHEETNAME, "s2");
		m.put(AttributeName.SEPARATION, "a");
		assertEquals(as, af.getPartition(m, EnumPartUsage.Explicit));
		assertEquals(as, rf.getPartition(m, EnumPartUsage.Explicit));
		assertEquals(as, r.getPartition(m, EnumPartUsage.Explicit));
		assertEquals(null, rs.getPartition(m, EnumPartUsage.Explicit));
		assertEquals(null, as.getPartition(m, EnumPartUsage.Explicit));
	}

	/**
	 *
	 */
	@Test
	void testIdenticalForeign()
	{
		final JDFNode n = JDFNode.createRoot();
		final JDFResource r = n.addResource("foo.Bar", EnumResourceClass.Parameter, EnumUsage.Input, null, null, "www.foo.com", null);
		final PartitionGetter pg = new PartitionGetter(r);
		final JDFResource rs = r.addPartition(EnumPartIDKey.SheetName, "s1");
		final JDFResource a = rs.addPartition(EnumPartIDKey.Separation, "a");
		final JDFResource b = rs.addPartition(EnumPartIDKey.Separation, "b");
		final JDFResource c = rs.addPartition(EnumPartIDKey.Separation, "c");
		b.setIdentical(a);
		c.setIdentical(a);
		pg.setFollowIdentical(true);
		final JDFAttributeMap m = new JDFAttributeMap(AttributeName.SHEETNAME, "s1");
		m.put(AttributeName.SEPARATION, "a");
		assertEquals(a, pg.getPartition(m, EnumPartUsage.Explicit));
		assertEquals(a, r.getPartition(m, EnumPartUsage.Explicit));
		m.put(AttributeName.SEPARATION, "b");
		assertEquals(a, pg.getPartition(m, EnumPartUsage.Explicit));
		assertEquals(a, r.getPartition(m, EnumPartUsage.Explicit));
		m.put(AttributeName.SEPARATION, "c");
		assertEquals(a, pg.getPartition(m, EnumPartUsage.Explicit));
		assertEquals(a, r.getPartition(m, EnumPartUsage.Explicit));
		m.put(AttributeName.SEPARATION, "d");
		assertNull(pg.getPartition(m, EnumPartUsage.Explicit));
		assertNull(r.getPartition(m, EnumPartUsage.Explicit));
	}

	/**
	 *
	 */
	@Test
	void testIdenticalFB()
	{
		final JDFResource r = (JDFResource) new JDFDoc(ElementName.EXPOSEDMEDIA).getRoot();
		final JDFResource rs = r.addPartition(EnumPartIDKey.SheetName, "s1");
		final JDFResource rsf = rs.addPartition(EnumPartIDKey.Side, "Front");
		final JDFResource a = rsf.addPartition(EnumPartIDKey.Separation, "a");
		final JDFResource b = rsf.addPartition(EnumPartIDKey.Separation, "b");
		final JDFResource c = rsf.addPartition(EnumPartIDKey.Separation, "c");
		final JDFResource rsb = rs.addPartition(EnumPartIDKey.Side, "Back");
		final JDFResource ab = rsb.addPartition(EnumPartIDKey.Separation, "a");
		final JDFResource bb = rsb.addPartition(EnumPartIDKey.Separation, "b");
		final JDFResource cb = rsb.addPartition(EnumPartIDKey.Separation, "c");
		b.setIdentical(a);
		c.setIdentical(a);
		ab.setIdentical(a);
		bb.setIdentical(a);
		cb.setIdentical(a);
		final PartitionGetter pg = new PartitionGetter(r);
		pg.setFollowIdentical(true);
		final JDFAttributeMap m = new JDFAttributeMap(AttributeName.SHEETNAME, "s1");
		m.put(AttributeName.SIDE, "Back");
		m.put(AttributeName.SEPARATION, "a");
		assertEquals(a, pg.getPartition(m, EnumPartUsage.Explicit));
		m.put(AttributeName.SEPARATION, "b");
		assertEquals(a, pg.getPartition(m, EnumPartUsage.Explicit));
		m.put(AttributeName.SEPARATION, "c");
		assertEquals(a, pg.getPartition(m, EnumPartUsage.Explicit));
		m.put(AttributeName.SEPARATION, "d");
		assertNull(pg.getPartition(m, EnumPartUsage.Explicit));
		m.put(AttributeName.SIDE, "Front");
		m.put(AttributeName.SEPARATION, "b");
		assertEquals(a, pg.getPartition(m, EnumPartUsage.Explicit));
	}

	/**
	 *
	 */
	@Test
	void testIdenticalPV()
	{
		final JDFResource r = (JDFResource) new JDFDoc(ElementName.EXPOSEDMEDIA).getRoot();
		final PartitionGetter pg = new PartitionGetter(r);

		final JDFResource rs = r.addPartition(EnumPartIDKey.SheetName, "s1");
		final JDFResource rsf = rs.addPartition(EnumPartIDKey.PartVersion, "pa pb");
		final JDFResource a = rsf.addPartition(EnumPartIDKey.Separation, "a");
		final JDFResource b = rsf.addPartition(EnumPartIDKey.Separation, "b");
		final JDFResource c = rsf.addPartition(EnumPartIDKey.Separation, "c");
		final JDFResource rsb = rs.addPartition(EnumPartIDKey.PartVersion, "pc pd");
		final JDFResource ab = rsb.addPartition(EnumPartIDKey.Separation, "a");
		final JDFResource bb = rsb.addPartition(EnumPartIDKey.Separation, "b");
		final JDFResource cb = rsb.addPartition(EnumPartIDKey.Separation, "c");
		b.setIdentical(a);
		c.setIdentical(a);
		ab.setIdentical(a);
		bb.setIdentical(a);
		cb.setIdentical(a);
		pg.setFollowIdentical(true);
		final JDFAttributeMap m = new JDFAttributeMap(AttributeName.SHEETNAME, "s1");
		m.put(AttributeName.PARTVERSION, "pa");
		m.put(AttributeName.SEPARATION, "a");
		assertEquals(a, pg.getPartition(m, EnumPartUsage.Explicit));
		m.put(AttributeName.SEPARATION, "b");
		assertEquals(a, pg.getPartition(m, EnumPartUsage.Explicit));
		m.put(AttributeName.SEPARATION, "c");
		assertEquals(a, pg.getPartition(m, EnumPartUsage.Explicit));
		m.put(AttributeName.SEPARATION, "d");
		assertNull(pg.getPartition(m, EnumPartUsage.Explicit));
		m.put(AttributeName.PARTVERSION, "pc");
		m.put(AttributeName.SEPARATION, "b");
		assertEquals(a, pg.getPartition(m, EnumPartUsage.Explicit));
	}

	/**
	 *
	 */
	@Test
	void testImplicitIdenticalPV()
	{
		final JDFResource r = (JDFResource) new JDFDoc(ElementName.EXPOSEDMEDIA).getRoot();
		r.setPartUsage(EnumPartUsage.Implicit);
		final PartitionGetter pg = new PartitionGetter(r);

		final JDFResource rs = r.addPartition(EnumPartIDKey.SheetName, "s1");
		final JDFResource rsf = rs.addPartition(EnumPartIDKey.PartVersion, "pa pb");
		final JDFResource a = rsf.addPartition(EnumPartIDKey.Separation, "a");
		final JDFResource rsb = rs.addPartition(EnumPartIDKey.PartVersion, "pc pd");
		final JDFResource ab = rsb.addPartition(EnumPartIDKey.Separation, "a");
		ab.setIdentical(a);
		a.deleteNode();
		pg.setFollowIdentical(true);
		final JDFAttributeMap m = new JDFAttributeMap(AttributeName.SHEETNAME, "s1");
		m.put(AttributeName.PARTVERSION, "pa");
		m.put(AttributeName.SEPARATION, "a");
		assertEquals(rsf, pg.getPartition(m, EnumPartUsage.Implicit));
		m.put(AttributeName.PARTVERSION, "pc");
		m.put(AttributeName.SEPARATION, "a");
		assertEquals(rsf, pg.getPartition(m, EnumPartUsage.Implicit));
	}

	/**
	 *
	 */
	@Test
	void testIdenticalPVLeaf()
	{
		final JDFResource r = (JDFResource) new JDFDoc(ElementName.EXPOSEDMEDIA).getRoot();
		final PartitionGetter pg = new PartitionGetter(r);

		final JDFResource rs = r.addPartition(EnumPartIDKey.SheetName, "s1");
		final JDFResource rsf = rs.addPartition(EnumPartIDKey.PartVersion, "pa pb");
		final JDFResource rsb = rs.addPartition(EnumPartIDKey.PartVersion, "pc pd");
		rsf.setIdentical(rsb);
		pg.setFollowIdentical(true);
		final JDFAttributeMap m = new JDFAttributeMap(AttributeName.SHEETNAME, "s1");
		m.put(AttributeName.PARTVERSION, "pa");
		assertEquals(rsb, pg.getPartition(m, EnumPartUsage.Explicit));
		m.put(AttributeName.PARTVERSION, "pa pb");
		assertEquals(rsb, pg.getPartition(m, EnumPartUsage.Explicit));
		m.put(AttributeName.PARTVERSION, "pc");
		assertEquals(rsb, pg.getPartition(m, EnumPartUsage.Explicit));
	}

	/**
	 *
	 */
	@Test
	void testIdenticalNot()
	{
		final JDFResource r = (JDFResource) new JDFDoc(ElementName.EXPOSEDMEDIA).getRoot();
		final JDFResource rs = r.addPartition(EnumPartIDKey.SheetName, "s1");
		final JDFResource a = rs.addPartition(EnumPartIDKey.Separation, "a");
		final JDFResource b = rs.addPartition(EnumPartIDKey.Separation, "b");
		final JDFResource c = rs.addPartition(EnumPartIDKey.Separation, "c");
		b.setIdentical(a);
		c.setIdentical(a);
		final PartitionGetter pg = new PartitionGetter(r);
		pg.setFollowIdentical(false);
		final JDFAttributeMap m = new JDFAttributeMap(AttributeName.SHEETNAME, "s1");
		m.put(AttributeName.SEPARATION, "a");
		assertEquals(a, pg.getPartition(m, EnumPartUsage.Explicit));
		m.put(AttributeName.SEPARATION, "b");
		assertEquals(b, pg.getPartition(m, EnumPartUsage.Explicit));
		m.put(AttributeName.SEPARATION, "c");
		assertEquals(c, pg.getPartition(m, EnumPartUsage.Explicit));
		m.put(AttributeName.SEPARATION, "d");
		assertNull(pg.getPartition(m, EnumPartUsage.Explicit));
	}

}
