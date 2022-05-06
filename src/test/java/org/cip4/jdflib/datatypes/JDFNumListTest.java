/*
 *
 * The CIP4 Software License, Version 1.0
 *
 *
 * Copyright (c) 2001-2021 The International Cooperation for the Integration of Processes in Prepress, Press and Postpress (CIP4). All rights reserved.
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
/**
 * JDFXYPairRangeListTest.java
 *
 * @author Elena Skobchenko
 *
 *         Copyright (c) 2001-2004 The International Cooperation for the Integration of Processes in Prepress, Press and Postpress (CIP4). All rights reserved.
 */
package org.cip4.jdflib.datatypes;

import java.util.Collections;
import java.util.Vector;
import java.util.zip.DataFormatException;

import org.cip4.jdflib.JDFTestCaseBase;
import org.cip4.jdflib.core.JDFDoc;
import org.cip4.jdflib.node.JDFNode;
import org.cip4.jdflib.util.CPUTimer;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

/**
 *
 * @author Rainer Prosi, Heidelberger Druckmaschinen *
 */
public class JDFNumListTest extends JDFTestCaseBase
{
	/**
	 *
	 * @throws Exception
	 */
	@Test
	public final void testSetString() throws Exception
	{
		final JDFDoc d = new JDFDoc("JDF");
		final JDFNode n = d.getJDFRoot();

		JDFIntegerList il = null;
		il = new JDFIntegerList("1 2 INF");
		n.setAttribute("test", il, null);
		Assertions.assertEquals(il.toString(), "1 2 INF", "il");

		JDFNumberList nl = null;
		nl = new JDFNumberList("-INF 1.1 2.2 INF");
		n.setAttribute("test2", nl, null);
		Assertions.assertEquals(nl.toString(), "-INF 1.1 2.2 INF", "nl");
	}

	/**
	 * @throws Exception
	 *
	 */
	@Test
	public final void testGetIntArray() throws Exception
	{
		final JDFIntegerList il = new JDFIntegerList("1 2 INF");
		final int[] ar = il.getIntArray();
		Assertions.assertEquals(3, ar.length);
		Assertions.assertEquals(ar[2], Integer.MAX_VALUE);
	}

	/**
	 * @throws Exception
	 *
	 */
	@Test
	public final void testGetIntArrayDouble() throws Exception
	{
		final JDFIntegerList il = new JDFIntegerList("1.1 2.2 7.7");
		final int[] ar = il.getIntArray();
		Assertions.assertEquals(3, ar.length);
		Assertions.assertEquals(2, ar[1]);
		Assertions.assertEquals(8, ar[2]);
	}

	/**
	 *
	 */
	@Test
	public final void testSetIntArray()
	{
		final int[] iArray = new int[3];
		iArray[0] = 1;
		iArray[1] = 2;
		iArray[2] = 4;
		final JDFIntegerList il = new JDFIntegerList(iArray);
		final int[] ar = il.getIntArray();
		Assertions.assertEquals(iArray.length, ar.length);
		Assertions.assertEquals(iArray[0], ar[0]);
		Assertions.assertEquals(iArray[1], ar[1]);
		Assertions.assertEquals(iArray[2], ar[2]);
	}

	/**
	 *
	 */
	@Test
	public final void testScale()
	{
		final int[] iArray = new int[3];
		iArray[0] = 1;
		iArray[1] = 2;
		iArray[2] = 4;
		final JDFIntegerList il = new JDFIntegerList(iArray);
		il.scale(2);
		final int[] ar = il.getIntArray();
		Assertions.assertEquals(iArray.length, ar.length);
		Assertions.assertEquals(2 * iArray[0], ar[0]);
		Assertions.assertEquals(2 * iArray[1], ar[1]);
		Assertions.assertEquals(2 * iArray[2], ar[2]);
	}

	/**
	 *
	 */
	@Test
	public final void testAbs()
	{
		final int[] iArray = new int[3];
		iArray[0] = 1;
		iArray[1] = -2;
		iArray[2] = 4;
		final JDFIntegerList il = new JDFIntegerList(iArray);
		il.abs();
		final int[] ar = il.getIntArray();
		Assertions.assertEquals(iArray.length, ar.length);
		Assertions.assertEquals(1, ar[0]);
		Assertions.assertEquals(2, ar[1]);
		Assertions.assertEquals(4, ar[2]);
	}

	/**
	 *
	 */
	@Test
	public final void testScaleFromCM()
	{
		final JDFShape s = new JDFShape(10, 20, 5);
		s.scaleFromCM();
		Assertions.assertEquals(s.getX(), 10. * 72. / 2.54, 0);
		Assertions.assertEquals(s.getY(), 20. * 72. / 2.54, 0);
		Assertions.assertEquals(s.getZ(), 5. * 72. / 2.54, 0);
	}

	/**
	 *
	 */
	@Test
	public final void testScaleFromCMPrecision()
	{
		final JDFShape s = new JDFShape(10, 20, 5);
		s.scaleFromCM(0);
		Assertions.assertEquals(s.getX(), 10. * 72. / 2.54, 0.5);
		Assertions.assertEquals(s.getY(), 20. * 72. / 2.54, 0.5);
		Assertions.assertEquals(s.getZ(), 5. * 72. / 2.54, 0.5);
		Assertions.assertEquals((int) s.getX(), (int) (10. * 72. / 2.54 + 0.5));
		Assertions.assertEquals((int) s.getY(), (int) (20. * 72. / 2.54 + 0.5));
		Assertions.assertEquals((int) s.getZ(), (int) (5. * 72. / 2.54 + 0.5));
	}

	/**
	 *
	 */
	@Test
	public final void testScalePrecision()
	{
		JDFShape s = new JDFShape(10, 20, 5);
		s.scaleFromCM();
		s.scale(1, 0);
		Assertions.assertEquals((int) s.getX(), (int) (10. * 72. / 2.54 + 0.5));
		Assertions.assertEquals((int) s.getY(), (int) (20. * 72. / 2.54 + 0.5));
		Assertions.assertEquals((int) s.getZ(), (int) (5. * 72. / 2.54 + 0.5));
		for (int i = 0; i < 5; i++)
		{
			s = new JDFShape(10, 20, 5);
			s.scaleFromCM();
			s.scale(1, i);
			Assertions.assertTrue(s.toString().length() <= 3 * (i + 4) + 2);
		}
	}

	/**
	 *
	 */
	@Test
	public final void testShift()
	{
		final JDFShape s = new JDFShape(10.5, 20.5, 5.5);
		s.shift(4.5);
		Assertions.assertEquals(15, s.getX(), 0);
		Assertions.assertEquals(25, s.getY(), 0);
		Assertions.assertEquals(10, s.getZ(), 0);
	}

	/**
	 *
	 */
	@Test
	public final void testScaleFromMM()
	{
		final JDFShape s = new JDFShape(100, 200, 50);
		s.scaleFromMM();
		Assertions.assertEquals(s.getX(), 10. * 72. / 2.54, 0);
		Assertions.assertEquals(s.getY(), 20. * 72. / 2.54, 0);
		Assertions.assertEquals(s.getZ(), 5. * 72. / 2.54, 0);
	}

	/**
	 * @throws CloneNotSupportedException
	 *
	 */
	@Test
	public final void testClone() throws CloneNotSupportedException
	{
		JDFShape s = new JDFShape(100, 200, 50);
		s = (JDFShape) s.clone();
		Assertions.assertEquals(s.getX(), 100., 0);
		Assertions.assertEquals(s.getY(), 200., 0);
		Assertions.assertEquals(s.getZ(), 50., 0);
	}

	/**
	 * @throws CloneNotSupportedException
	 * @throws DataFormatException
	 *
	 */
	@Test
	public final void testMatches() throws CloneNotSupportedException, DataFormatException
	{
		final JDFShape s = new JDFShape(100, 200, 50);
		final JDFShape s2 = new JDFShape(102, 198, 52);
		Assertions.assertTrue(s.matches(s2, 555));
		Assertions.assertTrue(s.matches(s2, 2));
		Assertions.assertFalse(s.matches(s2, 1));
		final JDFIntegerList il = new JDFIntegerList("100 200 300");
		final JDFIntegerList il2 = new JDFIntegerList("100 197 303");
		Assertions.assertTrue(il.matches(il2, 555));
		Assertions.assertTrue(il.matches(il2, 3));
		Assertions.assertFalse(il.matches(il2, 1));
	}

	// ////////////////////////////////////////////////////////////
	/**
	 * @throws Exception
	 */
	@Test
	public final void testCopy() throws Exception
	{
		final JDFCMYKColor cmy1 = new JDFCMYKColor("1 2 3 4");
		final JDFCMYKColor cmy2 = new JDFCMYKColor(cmy1);
		Assertions.assertEquals(cmy1, cmy2);
		cmy2.setK(0);
		Assertions.assertEquals(cmy2.getK(), 0., 0.);
		Assertions.assertEquals(cmy1.getK(), 4., 0.);

	}

	/**
	 *
	 * @throws Exception
	 */
	@Test
	public final void testContainsDouble() throws Exception
	{
		final JDFNumberList l = new JDFNumberList("1 2.0 3 4 3.0");
		Assertions.assertTrue(l.contains(2.0));
		Assertions.assertTrue(l.contains(4.00));
		Assertions.assertTrue(l.contains(3));
		Assertions.assertFalse(l.contains(0));
	}

	/**
	 * @throws Exception
	 *
	 */
	@Test
	public final void testContainsInt() throws Exception
	{
		final JDFIntegerList l = new JDFIntegerList("1 2 3 4 3");
		Assertions.assertTrue(l.contains(2));
		Assertions.assertTrue(l.contains(4));
		Assertions.assertTrue(l.contains(3));
		Assertions.assertFalse(l.contains(0));
	}

	/**
	 * @throws Exception
	 *
	 */
	@Test
	public final void testContainsAll() throws Exception
	{
		final JDFIntegerList l = new JDFIntegerList("1 2 3 4 3");
		Assertions.assertTrue(l.containsAll(null));
		Assertions.assertTrue(l.containsAll(new JDFIntegerList("1 2 4 3")));
		Assertions.assertFalse(l.containsAll(new JDFIntegerList("1 2 4 3 5")));
	}

	// ////////////////////////////////////////////////////////////
	/**
	 * @throws Exception
	 *
	 */
	@Test
	public final void testContainsList() throws Exception
	{
		final JDFIntegerList l = new JDFIntegerList("1 2 3 4 3");
		Assertions.assertTrue(l.contains(new JDFIntegerList("1")));
		Assertions.assertTrue(l.contains(new JDFIntegerList("2 5")));

		Assertions.assertFalse(l.contains(new JDFIntegerList("5")));
	}

	// ////////////////////////////////////////////////////////////
	/**
	 *
	 */
	@Test
	public final void testRemoveElementAt()
	{
		final int[] iArray = new int[3];
		iArray[0] = 1;
		iArray[1] = 2;
		iArray[2] = 4;
		final JDFIntegerList il = new JDFIntegerList(iArray);
		Assertions.assertEquals(il.getInt(-1), 4);
		il.removeElementAt(2);
		Assertions.assertEquals(il.getInt(-1), 2);
		il.removeElementAt(-1);
		Assertions.assertEquals(il.getInt(-1), 1);
	}

	/**
	 * @throws Exception
	 *
	 */
	@Test
	public void testGetDouble() throws Exception
	{
		final JDFNumberList nl = new JDFNumberList("1.1 2.2 3.3");
		Assertions.assertEquals(nl.doubleAt(0), 1.1, 0.0);
		Assertions.assertEquals(nl.doubleAt(1), 2.2, 0.0);
		Assertions.assertEquals(nl.doubleAt(2), 3.3, 0.0);
		Assertions.assertEquals(nl.doubleAt(-1), 3.3, 0.0);
		Assertions.assertEquals(nl.doubleAt(3), 0.0, 0.0);
	}

	/**
	 * @throws Exception
	 *
	 */
	@Test
	public void testNorm() throws Exception
	{
		final JDFNumberList nl = new JDFNumberList("4 3");
		final JDFNumberList nl2 = new JDFNumberList("-3 4");
		Assertions.assertEquals(nl.norm(), nl2.norm(), 0.0001);
		Assertions.assertEquals(nl.norm(), 5, 0.0);
	}

	/**
	 * @throws Exception
	 *
	 */
	@Test
	public void testMin() throws Exception
	{
		final JDFNumberList nl = new JDFNumberList("4 3 8 7 ");
		Assertions.assertEquals(3, nl.min(), 0);
	}

	/**
	 * @throws Exception
	 *
	 */
	@Test
	public void testMinNeg() throws Exception
	{
		final JDFNumberList nl = new JDFNumberList("-4 -3 -8 -7 ");
		Assertions.assertEquals(-8, nl.min(), 0);
	}

	/**
	 * @throws Exception
	 *
	 */
	@Test
	public void testMax() throws Exception
	{
		final JDFNumberList nl = new JDFNumberList("4 3 8 7 -15");
		Assertions.assertEquals(8, nl.max(), 0);
	}

	/**
	 * @throws Exception
	 *
	 */
	@Test
	public void testMaxNeg() throws Exception
	{
		final JDFNumberList nl = new JDFNumberList("-1 -1");
		Assertions.assertEquals(-1, nl.max(), 0);
	}

	/**
	 * @throws Exception
	 *
	 */
	@Test
	public void testVolume() throws Exception
	{
		final JDFNumberList nl = new JDFNumberList("4 3");
		final JDFNumberList nl2 = new JDFNumberList("-3 4");
		Assertions.assertEquals(nl.volume(), -nl2.volume(), 0.0001);
		Assertions.assertEquals(nl.volume(), 12, 0.0);
	}

	/**
	 * @throws Exception
	 *
	 */
	@Test
	public void testCompare() throws Exception
	{
		final JDFNumberList nl = new JDFNumberList("1 2");
		final JDFNumberList nl2 = new JDFNumberList("3 4");
		final Vector<JDFNumberList> v = new Vector<>();
		v.add(nl2);
		v.add(nl);
		Collections.sort(v, new JDFNumList.NormComparator());
		Assertions.assertEquals(v.get(0), nl);
		Collections.sort(v, new JDFNumList.VolumeComparator());
		Assertions.assertEquals(v.get(0), nl);
	}

	/**
	 *
	 */
	@Test
	public void testSort()
	{
		final JDFNumberList l = new JDFNumberList();
		l.add(2);
		l.add(4);
		l.add(3);
		l.sort();
		Assertions.assertEquals(l.get(0), 2.0);
		Assertions.assertEquals(l.get(1), 3.0);
		Assertions.assertEquals(l.get(2), 4.0);
	}

	/**
	 * @throws Exception
	 *
	 */
	@Test
	public void testGetString() throws Exception
	{
		final JDFNumberList nl = new JDFNumberList("1.11 2.22 3.33");
		Assertions.assertEquals(nl.getString(10), "1.11 2.22 3.33");
		Assertions.assertEquals(nl.getString(2), "1.11 2.22 3.33");
		Assertions.assertEquals(nl.getString(1), "1.1 2.2 3.3");
		Assertions.assertEquals(nl.getString(0), "1 2 3");
	}

	/**
	 * @throws Exception
	 *
	 */
	@Test
	public void testGet() throws Exception
	{
		final JDFNumberList nl = new JDFNumberList("1.11 2.22 3.33");
		Assertions.assertEquals(Double.valueOf(1.11), nl.get(0));
	}

	/**
	 * @throws Exception
	 *
	 */
	@Test
	public void testGetDoubleList() throws Exception
	{
		final JDFNumberList nl = new JDFNumberList("1.1 2.2 3.3");
		Assertions.assertEquals(nl.getDoubleList().length, 3);
		Assertions.assertEquals(nl.getDoubleList()[1], 2.2, 0.0);
		Assertions.assertEquals(nl.getDoubleList()[2], 3.3, 0.0);
	}

	/**
	 * @throws Exception
	 *
	 */
	@Test
	public void testGetDoubleVector() throws Exception
	{
		final JDFNumberList nl = new JDFNumberList("1.1 2.2 3.3");
		final Vector<Double> dv = nl.getDoubleVector();
		Assertions.assertEquals(dv.size(), 3);
		Assertions.assertEquals(dv.get(1).doubleValue(), 2.2, 0.0);
		Assertions.assertEquals(dv.get(2).doubleValue(), 3.3, 0.0);
	}

	/**
	 * @throws Exception
	 *
	 */
	@Test
	public void testPerformance() throws Exception
	{
		final CPUTimer ct = new CPUTimer(true);
		for (int i = 0; i < 100000; i++)
		{
			new JDFNumberList("1.104534098756 2.2098256107389 3.3098563 4.4234190123874 5.5555555 6.098634198634109875 7.129875 8.9123846");
		}
		System.out.println(ct);
	}

	// ////////////////////////////////////////////////////////////
	/**
	 * @throws Exception
	 *
	 */
	@Test
	public void testShape() throws Exception
	{
		final JDFShape nl = new JDFShape("1.1 2.2 3.3");
		Assertions.assertEquals(nl.doubleAt(0), 1.1, 0.0);
		Assertions.assertEquals(nl.doubleAt(1), 2.2, 0.0);
		Assertions.assertEquals(nl.doubleAt(2), 3.3, 0.0);
		Assertions.assertEquals(nl.getX(), 1.1, 0.0);
		Assertions.assertEquals(nl.getY(), 2.2, 0.0);
		Assertions.assertEquals(nl.getZ(), 3.3, 0.0);

		nl.setY(5);
		Assertions.assertEquals(nl.getY(), 5, 0.0);

	}

	// ////////////////////////////////////////////////////////////

	/**
	 *
	 */
	@Test
	public void testShape2()
	{
		final JDFShape nl = new JDFShape(1, 2);
		Assertions.assertEquals(nl.doubleAt(0), 1, 0.0);
		Assertions.assertEquals(nl.doubleAt(1), 2, 0.0);
		Assertions.assertEquals(nl.doubleAt(2), 0, 0.0);
		Assertions.assertEquals(nl.getX(), 1, 0.0);
		Assertions.assertEquals(nl.getY(), 2, 0.0);
		Assertions.assertEquals(nl.getZ(), 0, 0.0);
	}
	// ////////////////////////////////////////////////////////////

}
