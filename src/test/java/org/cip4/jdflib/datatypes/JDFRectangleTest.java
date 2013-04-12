/**
 * JDFRectangleRangeTest.java
 *
 * @author Elena Skobchenko
 * 
 * Copyright (c) 2001-2004 The International Cooperation for the Integration 
 * of Processes in  Prepress, Press and Postpress (CIP4).  All rights reserved.
 */
package org.cip4.jdflib.datatypes;

import junit.framework.TestCase;

import org.junit.Assert;
import org.junit.Test;
public class JDFRectangleTest extends TestCase
{

	@Test
	public final void testRectangle() throws Exception
	{
		JDFRectangle r1 = new JDFRectangle("0 0 1 1");
		JDFRectangle r2 = new JDFRectangle("0 0 1 1");
		Assert.assertTrue(r1.equals(r2));
		Assert.assertTrue(r1.isGreaterOrEqual(r2));
		Assert.assertTrue(r1.isLessOrEqual(r2));
		Assert.assertFalse(r1.isGreater(r2));
		Assert.assertFalse(r1.isLess(r2));
		r2 = new JDFRectangle("0 0 1 2");
		Assert.assertTrue(r1.isLessOrEqual(r2));
		Assert.assertTrue(r1.isLess(r2));
		r2 = new JDFRectangle("0 0 1.000000001 1");
		Assert.assertTrue(r1.isLessOrEqual(r2));
		Assert.assertTrue(r1.equals(r2));

		r2 = new JDFRectangle("2 2 3 3");
		Assert.assertFalse(r1.equals(r2));
		Assert.assertFalse(r1.isGreaterOrEqual(r2));
		Assert.assertFalse(r1.isLessOrEqual(r2));
		Assert.assertFalse(r1.isGreater(r2));
		Assert.assertFalse(r1.isLess(r2));

	}

	@Test
	public final void testRectangleMm() throws Exception
	{
		JDFRectangle r1 = new JDFRectangle();
		r1.setLlxMm(0);
		r1.setLlyMm(0);
		r1.setUrxMm(1);
		r1.setUryMm(1);
		
		JDFRectangle r2 = new JDFRectangle();
		r2.setLlxMm(0);
		r2.setLlyMm(0);
		r2.setUrxMm(1);
		r2.setUryMm(1);
		
		Assert.assertTrue(r1.equals(r2));
		Assert.assertTrue(r1.isGreaterOrEqual(r2));
		Assert.assertTrue(r1.isLessOrEqual(r2));
		Assert.assertFalse(r1.isGreater(r2));
		Assert.assertFalse(r1.isLess(r2));
		
		r2 = new JDFRectangle();
		r2.setLlxMm(0);
		r2.setLlyMm(0);
		r2.setUrxMm(1);
		r2.setUryMm(2);
		Assert.assertTrue(r1.isLessOrEqual(r2));
		Assert.assertTrue(r1.isLess(r2));
		
		
		r2 = new JDFRectangle();
		r2.setLlxMm(0);
		r2.setLlyMm(0);
		r2.setUrxMm(1.000000001);
		r2.setUryMm(1);
		Assert.assertTrue(r1.isLessOrEqual(r2));
		Assert.assertTrue(r1.equals(r2));

		r2 = new JDFRectangle();
		r2.setLlxMm(2);
		r2.setLlyMm(2);
		r2.setUrxMm(3);
		r2.setUryMm(3);
		Assert.assertFalse(r1.equals(r2));
		Assert.assertFalse(r1.isGreaterOrEqual(r2));
		Assert.assertFalse(r1.isLessOrEqual(r2));
		Assert.assertFalse(r1.isGreater(r2));
		Assert.assertFalse(r1.isLess(r2));

	}
}
