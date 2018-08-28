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

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import org.cip4.jdflib.core.AttributeName;
import org.cip4.jdflib.core.ElementName;
import org.cip4.jdflib.core.JDFDoc;
import org.cip4.jdflib.core.VString;
import org.cip4.jdflib.datatypes.JDFAttributeMap;
import org.cip4.jdflib.datatypes.VJDFAttributeMap;
import org.cip4.jdflib.resource.JDFResource.EnumPartIDKey;
import org.cip4.jdflib.resource.JDFResource.EnumPartUsage;
import org.junit.Test;

public class PartitionGetterTest
{

	/**
	 *
	 */
	@Test
	public void testGetExplicit()
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
	public void testRemoveExplicitDup()
	{
		final VJDFAttributeMap vmap = new VJDFAttributeMap();
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
		g.removeExplicitDuplicates(vmap);
		assertEquals(4, vmap.size());
		for (int i = 0; i < 4; i++)
		{
			assertEquals(1, vmap.get(i).size());
		}
	}

	/**
	 *
	 */
	@Test
	public void testRemoveImplicitDup()
	{
		final VJDFAttributeMap vmap = new VJDFAttributeMap();
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
	public void testGet()
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
	public void testGetCreate()
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
	public void testGetimplicit()
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
	public void testPVResMulti()
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
	public void testPVResStrict()
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
	public void testPVLinkMulti()
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
	public void testGetimplicitFromMap()
	{
		final JDFResource r = (JDFResource) new JDFDoc(ElementName.EMBOSSINGPARAMS).getRoot();
		final PartitionGetter g = new PartitionGetter(r);
		assertEquals(new JDFAttributeMap(), g.getImplicitPartitionFromMap(new JDFAttributeMap(EnumPartIDKey.DeliveryUnit0, "d1")));
	}

	/**
	 *
	 */
	@Test
	public void testHasGap()
	{
		final VString v = new VString("a b c");
		final JDFResource r = (JDFResource) new JDFDoc(ElementName.EMBOSSINGPARAMS).getRoot();
		final PartitionGetter g = new PartitionGetter(r);
		assertTrue(g.hasGap(new JDFAttributeMap("b", "b1"), v));
		assertTrue(g.hasGap(new JDFAttributeMap("d", "b1"), v));
		final JDFAttributeMap next = new JDFAttributeMap("a", "b1");
		assertFalse(g.hasGap(next, v));
		next.put("b", "ccc");
		assertFalse(g.hasGap(next, v));
		next.put("c", "ccc");
		next.remove("b");
		assertTrue(g.hasGap(next, v));
	}

	/**
	*
	*/
	@Test
	public void testLastPos()
	{
		final VString v = new VString("a b c");
		final JDFResource r = (JDFResource) new JDFDoc(ElementName.EMBOSSINGPARAMS).getRoot();
		final PartitionGetter g = new PartitionGetter(r);
		assertEquals(1, g.lastPos(new JDFAttributeMap("b", "b1"), v));
		assertEquals(3, g.lastPos(new JDFAttributeMap("d", "b1"), v));
		final JDFAttributeMap next = new JDFAttributeMap("a", "b1");
		assertEquals(0, g.lastPos(new JDFAttributeMap("a", "b1"), v));
		next.put("b", "ccc");
		assertEquals(1, g.lastPos(next, v));
		next.put("c", "ccc");
		assertEquals(2, g.lastPos(next, v));
		next.remove("b");
		assertEquals(2, g.lastPos(next, v));
	}

	/**
	 *
	 */
	@Test
	public void testSpecialFromMap()
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
	public void testGetimplicitFromMap2()
	{
		final JDFResource r = (JDFResource) new JDFDoc(ElementName.EMBOSSINGPARAMS).getRoot();
		final JDFResource r2 = r.addPartition(EnumPartIDKey.SignatureName, "S1");
		final PartitionGetter g = new PartitionGetter(r);
		assertEquals(new JDFAttributeMap(), g.getImplicitPartitionFromMap(new JDFAttributeMap(EnumPartIDKey.DeliveryUnit0, "d1")));
		final JDFAttributeMap p1 = new JDFAttributeMap(EnumPartIDKey.SignatureName, "S1");
		final JDFAttributeMap p2 = p1.clone();
		p2.put(EnumPartIDKey.SheetName, "SH1");
		assertEquals(p1, g.getImplicitPartitionFromMap(p2));
	}

	/**
	 *
	 */
	@Test
	public void testGetMissing()
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
	public void testGetimplicitFromMapnix()
	{
		final JDFResource r = (JDFResource) new JDFDoc(ElementName.EMBOSSINGPARAMS).getRoot();
		final JDFResource r2 = r.addPartition(EnumPartIDKey.SignatureName, "S1");
		final PartitionGetter g = new PartitionGetter(r);
		assertEquals(new JDFAttributeMap(), g.getImplicitPartitionFromMap(new JDFAttributeMap(EnumPartIDKey.DeliveryUnit0, "d1")));
		final JDFAttributeMap p1 = new JDFAttributeMap(EnumPartIDKey.SignatureName, "S1");
		final JDFAttributeMap p2 = p1.clone();
		p2.put(EnumPartIDKey.SheetName, "SH1");
		assertEquals(p1, g.getImplicitPartitionFromMap(p2));
	}

	/**
	 *
	 */
	@Test
	public void testIdentical()
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
	public void testIdenticalFB()
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
	public void testIdenticalPV()
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
	public void testIdenticalNot()
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
