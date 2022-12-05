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
/*
 * @author muchadie
 */
package org.cip4.jdflib.util;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.Vector;

import org.cip4.jdflib.JDFTestCaseBase;
import org.cip4.jdflib.core.ElementName;
import org.cip4.jdflib.core.JDFDoc;
import org.cip4.jdflib.core.VString;
import org.cip4.jdflib.datatypes.JDFAttributeMap;
import org.cip4.jdflib.ifaces.IMatches;
import org.cip4.jdflib.resource.process.JDFCostCenter;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

/**
 * general utilities for containers and objects
 *
 * @author Rainer Prosi
 *
 */
public class ContainerUtilTest extends JDFTestCaseBase
{
	private class SimpleMatch implements IMatches
	{
		private final int i;

		public SimpleMatch(final int pi)
		{
			super();
			this.i = pi;
		}

		/**
		 *
		 * @see org.cip4.jdflib.ifaces.IMatches#matches(java.lang.Object)
		 */
		@Override
		public boolean matches(final Object subset)
		{
			if (subset instanceof Integer)
				return ((Integer) subset).intValue() == i;
			return ((SimpleMatch) subset).i == i;
		}

		/**
		 * @see java.lang.Object#hashCode()
		 */
		@Override
		public int hashCode()
		{
			final int prime = 31;
			int result = 1;
			result = prime * result + i;
			return result;
		}

		/**
		 *
		 * @see java.lang.Object#equals(java.lang.Object)
		 */
		@Override
		public boolean equals(final Object obj)
		{
			return matches(obj);
		}

	}

	private class FilterMatch implements IMatches
	{
		/**
		 * @see java.lang.Object#toString()
		 */
		@Override
		public String toString()
		{
			return "FilterMatch [s=" + s + "]";
		}

		private final String s;

		public FilterMatch(final String pi)
		{
			super();
			this.s = pi;
		}

		/**
		 *
		 * @see org.cip4.jdflib.ifaces.IMatches#matches(java.lang.Object)
		 */
		@Override
		public boolean matches(final Object subset)
		{
			final FilterMatch f = (FilterMatch) subset;
			return f.s.startsWith(s);
		}

	}

	/**
	 *
	 */
	@Test
	public void testEquals()
	{
		Assertions.assertTrue(ContainerUtil.equals(null, null));
		Assertions.assertFalse(ContainerUtil.equals(null, ""));
		Assertions.assertFalse(ContainerUtil.equals("", null));
		Assertions.assertFalse(ContainerUtil.equals("", " "));
		Assertions.assertTrue(ContainerUtil.equals("a", "a"));
	}

	/**
	 *
	 */
	@Test
	public void testMatches()
	{
		final JDFCostCenter cs = (JDFCostCenter) new JDFDoc(ElementName.COSTCENTER).getRoot();
		cs.setCostCenterID("CS");
		final JDFCostCenter cs2 = (JDFCostCenter) new JDFDoc(ElementName.COSTCENTER).getRoot();
		cs2.setCostCenterID("CS");
		Assertions.assertTrue(ContainerUtil.matches(cs, cs2));
		Assertions.assertTrue(ContainerUtil.matches(cs, "CS"));
		Assertions.assertTrue(ContainerUtil.matches(null, null));
		cs2.setCostCenterID("CS2");
		Assertions.assertFalse(ContainerUtil.matches(cs, cs2));
		Assertions.assertFalse(ContainerUtil.matches(cs, null));
		Assertions.assertFalse(ContainerUtil.matches(cs, "CS2"));
		Assertions.assertFalse(ContainerUtil.matches(null, "CS2"));
	}

	/**
	 *
	 */
	@Test
	public void testMatchesExisting()
	{
		final JDFCostCenter cs = (JDFCostCenter) new JDFDoc(ElementName.COSTCENTER).getRoot();
		cs.setCostCenterID("CS");
		final JDFCostCenter cs2 = (JDFCostCenter) new JDFDoc(ElementName.COSTCENTER).getRoot();
		cs2.setCostCenterID("CS");
		Assertions.assertTrue(ContainerUtil.matchesExisting(cs, cs2));
		Assertions.assertTrue(ContainerUtil.matchesExisting(cs, "CS"));
		Assertions.assertTrue(ContainerUtil.matchesExisting(null, null));
		Assertions.assertTrue(ContainerUtil.matchesExisting(cs, null));
		Assertions.assertTrue(ContainerUtil.matchesExisting(null, "CS2"));
		cs2.setCostCenterID("CS2");
		Assertions.assertFalse(ContainerUtil.matchesExisting(cs, cs2));
		Assertions.assertFalse(ContainerUtil.matchesExisting(cs, "CS2"));
	}

	/**
	 *
	 */
	@Test
	public void testUnifyMatches()
	{
		final JDFCostCenter cs = (JDFCostCenter) new JDFDoc(ElementName.COSTCENTER).getRoot();
		cs.setCostCenterID("CS");
		final JDFCostCenter cs2 = (JDFCostCenter) new JDFDoc(ElementName.COSTCENTER).getRoot();
		cs2.setCostCenterID("CS2");
		final JDFCostCenter cs3 = (JDFCostCenter) new JDFDoc(ElementName.COSTCENTER).getRoot();
		cs2.setCostCenterID("CS");
		final Vector<JDFCostCenter> vcs = new Vector<>();
		vcs.add(cs);
		vcs.add(cs2);
		vcs.add(cs3);
		Assertions.assertEquals(ContainerUtil.unifyMatches(vcs).size(), 2);
	}

	/**
	*
	*/
	@Test
	public void testUnifyFilterMatches()
	{
		final FilterMatch ma = new FilterMatch("a");
		final FilterMatch mb = new FilterMatch("b");
		final Vector<FilterMatch> vcs = new Vector<>();
		vcs.add(mb);
		for (int i = 0; i < 20; i++)
		{
			vcs.add(new FilterMatch("a" + i));
		}
		vcs.add(ma);
		for (int i = 0; i < 20; i++)
		{
			vcs.add(new FilterMatch("b" + i));
		}
		ContainerUtil.unifyMatches(vcs);
		Assertions.assertEquals(2, vcs.size());
	}

	/**
	 *
	 */
	@Test
	public void testAddAll()
	{
		final VString v1 = new VString("a b c", null);
		final VString v2 = new VString("e f g", null);
		final VString v3 = new VString("a b c e f g", null);
		Assertions.assertEquals(ContainerUtil.addAll(null, (List<String>) null), null);
		Assertions.assertEquals(ContainerUtil.addAll(v1, (List<String>) null), v1);
		Assertions.assertEquals(ContainerUtil.addAll(null, v1), v1);
		Assertions.assertEquals(ContainerUtil.addAll(v1, v2), v3);
		Assertions.assertEquals(v1, v3);
	}

	/**
	 *
	 */
	@Test
	public void testPutAll()
	{
		JDFAttributeMap m0 = new JDFAttributeMap("a", "b");
		JDFAttributeMap m1 = new JDFAttributeMap("a1", "b1");
		assertNull(ContainerUtil.putAll(null, null));
		assertEquals(m0, ContainerUtil.putAll(null, m0));
		assertEquals(m0, ContainerUtil.putAll(m0, null));
		assertEquals(m0, ContainerUtil.putAll(m0, m1));
		assertEquals(2, m0.size());
	}

	/**
	 *
	 */
	@Test
	public void testPut()
	{
		JDFAttributeMap m0 = new JDFAttributeMap("a", "b");
		JDFAttributeMap m1 = new JDFAttributeMap("a1", "b1");
		assertNull(ContainerUtil.put(null, null, null));
		assertNull(ContainerUtil.put(null, "a", null));
		assertNull(ContainerUtil.put(null, null, "b"));
		assertNull(ContainerUtil.put(null, "a", "b"));
		assertEquals("b", ContainerUtil.put(m0, "a", "b1"));
	}

	/**
	 *
	 */
	@Test
	public void testGetMatch()
	{
		final Vector<SimpleMatch> v = new Vector<>();
		for (int i = 0; i < 10; i++)
		{
			v.add(new SimpleMatch(i % 2));
		}
		final SimpleMatch simpleMatch1 = new SimpleMatch(1);
		Assertions.assertEquals(ContainerUtil.getMatches(v, simpleMatch1).size(), 5);
		Assertions.assertEquals(ContainerUtil.getMatch(v, simpleMatch1, 0), simpleMatch1);
	}

	/**
	 *
	 */
	@Test
	public void testGetMatchList()
	{
		final Vector<SimpleMatch> v = new Vector<>();
		for (int i = 0; i < 10; i++)
		{
			v.add(new SimpleMatch(i % 2));
		}
		final SimpleMatch simpleMatch1 = new SimpleMatch(1);
		Assertions.assertEquals(ContainerUtil.getMatchesList(v, simpleMatch1).size(), 5);
		Assertions.assertEquals(ContainerUtil.getMatch(v, simpleMatch1, 0), simpleMatch1);
	}

	/**
	 *
	 */
	@Test
	public void testGetMatchInverted()
	{
		final Vector<Integer> v = new Vector<>();
		for (int i = 0; i < 10; i++)
		{
			v.add(Integer.valueOf(i % 2));
		}
		final SimpleMatch simpleMatch1 = new SimpleMatch(1);
		Assertions.assertEquals(ContainerUtil.getMatches(simpleMatch1, v).size(), 5);
		Assertions.assertEquals(ContainerUtil.getMatch(simpleMatch1, v, 0), Integer.valueOf(1));
	}

	/**
	 *
	 */
	@Test
	public void testGetMatchListInverted()
	{
		final List<Integer> v = new ArrayList<>();
		for (int i = 0; i < 10; i++)
		{
			v.add(Integer.valueOf(i % 2));
		}
		final SimpleMatch simpleMatch1 = new SimpleMatch(1);
		Assertions.assertEquals(ContainerUtil.getMatchesList(simpleMatch1, v).size(), 5);
		Assertions.assertEquals(ContainerUtil.getMatch(simpleMatch1, v, 0), Integer.valueOf(1));
	}

	/**
	 *
	 */
	@Test
	public void testToHashSetArray()
	{
		final String[] a = { "a", "b" };
		final Set<String> s = ContainerUtil.toHashSet(a);
		Assertions.assertTrue(s.contains("a"));
		Assertions.assertTrue(s.contains("b"));
		Assertions.assertFalse(s.contains("c"));
		Assertions.assertEquals(s.size(), a.length);
	}

	/**
	 *
	 */
	@Test
	public void testToArray()
	{
		final String[] a = { "a", "b" };
		final List<String> s = ContainerUtil.toArrayList(a);
		Assertions.assertTrue(s.contains("a"));
		Assertions.assertTrue(s.contains("b"));
		Assertions.assertFalse(s.contains("c"));
		Assertions.assertEquals(s.size(), a.length);
	}

	/**
	 *
	 */
	@Test
	public void testToValueVector()
	{
		final HashMap<String, String> hm = new HashMap<>();
		for (int i = 0; i < 10; i++)
		{
			hm.put("" + i, "a" + i);
		}
		final Vector<String> v = ContainerUtil.toValueVector(hm, false);
		Assertions.assertEquals(v.size(), 10);
		final Vector<String> vs = ContainerUtil.toValueVector(hm, true);
		Assertions.assertTrue(vs.containsAll(v));
		Assertions.assertTrue(v.containsAll(vs));
		for (int i = 1; i < 10; i++)
		{
			Assertions.assertTrue(vs.get(i - 1).compareTo(vs.get(i)) < 0);
		}
	}

	/**
	 *
	 */
	@Test
	public void testToValueArray()
	{
		final HashMap<String, String> hm = new HashMap<>();
		for (int i = 0; i < 10; i++)
		{
			hm.put("" + i, "a" + i);
		}
		final List<String> v = ContainerUtil.toArrayList(hm, false);
		Assertions.assertEquals(v.size(), 10);
		final List<String> vs = ContainerUtil.toArrayList(hm, true);
		Assertions.assertTrue(vs.containsAll(v));
		Assertions.assertTrue(v.containsAll(vs));
		for (int i = 1; i < 10; i++)
		{
			Assertions.assertTrue(vs.get(i - 1).compareTo(vs.get(i)) < 0);
		}
	}

	/**
	 *
	 */
	@Test
	public void testGetKeyVector()
	{
		final HashMap<String, String> hm = new HashMap<>();
		for (int i = 0; i < 10; i++)
		{
			hm.put("" + i, "a" + i);
		}
		final Vector<String> v = ContainerUtil.getKeyVector(hm);
		Assertions.assertEquals(v.size(), 10);
		final Vector<String> vs = ContainerUtil.getKeyVector(hm);
		Assertions.assertTrue(vs.containsAll(v));
		Assertions.assertTrue(v.containsAll(vs));
		for (int i = 0; i < 10; i++)
		{
			Assertions.assertTrue(v.contains("" + i));
		}
	}

	/**
	 *
	 */
	@Test
	public void testGetKeyArray()
	{
		final HashMap<String, String> hm = new HashMap<>();
		for (int i = 0; i < 10; i++)
		{
			hm.put("" + i, "a" + i);
		}
		final Collection<String> v = ContainerUtil.getKeyArray(hm);
		Assertions.assertEquals(v.size(), 10);
		final ArrayList<String> vs = (ArrayList<String>) ContainerUtil.getKeyArray(hm);
		Assertions.assertTrue(vs.containsAll(v));
		Assertions.assertTrue(v.containsAll(vs));
		for (int i = 0; i < 10; i++)
		{
			Assertions.assertTrue(v.contains("" + i));
		}
	}

	/**
	 *
	 */
	@Test
	public void testGetInvertedVector()
	{
		final HashMap<String, String> hm = new HashMap<>();
		for (int i = 0; i < 10; i++)
		{
			hm.put("" + i, "a" + i);
		}
		VectorMap<String, String> inv = ContainerUtil.getInvertedMap(hm);
		Assertions.assertEquals(inv.size(), 10);
		for (int i = 0; i < 10; i++)
		{
			Assertions.assertEquals(inv.getOne("a" + i, 0), "" + i);
		}
		for (int i = 0; i < 10; i++)
		{
			hm.put("b" + i, "a" + i);
		}
		inv = ContainerUtil.getInvertedMap(hm);
		Assertions.assertEquals(inv.size(), 10);
		for (int i = 0; i < 10; i++)
		{
			Assertions.assertEquals(inv.get("a" + i).size(), 2);
			Assertions.assertTrue(inv.get("a" + i).contains("b" + i));
			Assertions.assertTrue(inv.get("a" + i).contains("" + i));
		}
	}

	/**
	 *
	 */
	@Test
	public void testGetNonEmpty()
	{
		final Vector<String> v = new Vector<>();
		Assertions.assertNull(ContainerUtil.getNonEmpty(v));
		Assertions.assertNull(ContainerUtil.getNonEmpty((Map) null));

		v.add("a");
		Assertions.assertEquals(v, ContainerUtil.getNonEmpty(v));
	}

	/**
	 *
	 */
	@Test
	public void testIsEmpty()
	{
		final Vector<String> v = new Vector<>();
		Assertions.assertTrue(ContainerUtil.isEmpty(v));
		Assertions.assertTrue(ContainerUtil.isEmpty((Map) null));

		v.add("a");
		Assertions.assertFalse(ContainerUtil.isEmpty(v));
	}

	/**
	 *
	 */
	@Test
	public void testSize()
	{
		final JDFAttributeMap m = new JDFAttributeMap();
		Assertions.assertEquals(0, ContainerUtil.size(m));
		Assertions.assertEquals(0, ContainerUtil.size((List) null));

		m.put("a", "b");
		Assertions.assertEquals(1, ContainerUtil.size(m));
	}

	/**
	 *
	 */
	@Test
	public void testGetNonEmptyCollection()
	{
		final Vector<VString> v = new Vector<>();
		Assertions.assertNull(ContainerUtil.getNonEmptyCollection(v));
		Assertions.assertNull(ContainerUtil.getNonEmptyCollection(null));

		v.add(new VString());
		Assertions.assertNull(ContainerUtil.getNonEmptyCollection(v));
		v.add(new VString());
		Assertions.assertEquals(v, ContainerUtil.getNonEmptyCollection(v));
	}

	/**
	 *
	 */
	@Test
	public void testUnify()
	{
		final Vector<String> v = new Vector<>();
		final Vector<String> v2 = new Vector<>();
		for (int i = 0; i < 10; i++)
		{
			v.add("" + i);
			v.add("" + i);
			v2.add("" + i);
		}
		for (int i = 0; i < 10; i++)
		{
			v.add("" + i);
			v.add("" + i);
		}

		ContainerUtil.unify(v);
		Assertions.assertEquals(v, v2);
	}

	/**
	 *
	 */
	@Test
	public void testEnsureSize()
	{
		final VString v = new VString();
		ContainerUtil.ensureSize(4, v);
		Assertions.assertEquals(v.size(), 4);
		v.set(2, "foo");
		Assertions.assertEquals(v.get(2), "foo");
		Assertions.assertNull(v.get(0));
	}

	/**
	 *
	 */
	@Test
	public void testCompare()
	{
		Assertions.assertEquals(ContainerUtil.compare("1", "0"), 1);
		Assertions.assertEquals(ContainerUtil.compare("1", "1"), 0);
		Assertions.assertEquals(ContainerUtil.compare("1", "2"), -1);
		Assertions.assertEquals(ContainerUtil.compare("1", null), 1);
		Assertions.assertEquals(ContainerUtil.compare(null, "2"), -1);
		Assertions.assertEquals(ContainerUtil.compare(null, null), 0);
	}

	/**
	*
	*/
	@Test
	public void testContains()
	{
		Assertions.assertFalse(ContainerUtil.contains(null, null));
		Assertions.assertFalse(ContainerUtil.contains(null, "a"));
		Assertions.assertFalse(ContainerUtil.contains(new VString("a"), null));
		Assertions.assertFalse(ContainerUtil.contains(new VString("a b"), "f"));
		Assertions.assertTrue(ContainerUtil.contains(new VString("a b c d"), "c"));
	}

	/**
	*
	*/
	@Test
	public void testContainsAll()
	{
		Assertions.assertTrue(ContainerUtil.containsAll(null, null));
		Assertions.assertFalse(ContainerUtil.containsAll(null, new VString("a")));
		Assertions.assertTrue(ContainerUtil.containsAll(new VString("a"), null));
		Assertions.assertFalse(ContainerUtil.containsAll(new VString("a b"), new VString("a b c")));
		Assertions.assertTrue(ContainerUtil.containsAll(new VString("a b c d"), new VString("a b c")));
	}

	/**
	*
	*/
	@Test
	public void testContainsAny()
	{
		Assertions.assertTrue(ContainerUtil.containsAny(null, null));
		Assertions.assertFalse(ContainerUtil.containsAny(null, new VString("a")));
		Assertions.assertTrue(ContainerUtil.containsAny(new VString("a"), null));
		Assertions.assertTrue(ContainerUtil.containsAny(new VString("a b"), new VString("a")));
		Assertions.assertTrue(ContainerUtil.containsAny(new VString("a b"), new VString("a")));
	}
}
