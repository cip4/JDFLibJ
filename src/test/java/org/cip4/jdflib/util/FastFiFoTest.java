/**
 * 
 */
package org.cip4.jdflib.util;

import java.util.ArrayList;

import org.cip4.jdflib.JDFTestCaseBase;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
/**
 * @author Dr. Rainer Prosi, Heidelberger Druckmaschinen AG
 * 
 * May 26, 2009
 */
public class FastFiFoTest extends JDFTestCaseBase
{
	/**
	 * 
	 */
	@Test
	void testPush()
	{
		final FastFiFo<MyInteger> ff = new FastFiFo<MyInteger>(10);
		for (int i = 0; i < 100; i++)
		{
			final MyInteger r = ff.push(new MyInteger(i));
			if (i < 10)
			{
				Assertions.assertNull(r, "loop " + i);
			}
			else
			{
				Assertions.assertEquals(r, new MyInteger(i - 10), "loop " + i);
			}
		}
	}

	/**
	 * 
	 */
	@Test
	void testPeek()
	{
		final FastFiFo<MyInteger> ff = new FastFiFo<MyInteger>(10);
		for (int i = 0; i < 100; i++)
		{
//			final MyInteger r = 
				ff.push(new MyInteger(i));
			for (int j = 0; j < 10; j++)
			{
				final MyInteger r2 = ff.peek(j);
				if (j > i)
				{
					Assertions.assertNull(r2, "loop " + i);
				}
				else
				{
					Assertions.assertEquals(r2, new MyInteger(i - Math.min(9, i) + j), "loop " + i + "/" + j);
				}
			}
		}
	}

	/**
	 * 
	 */
	@Test
	void testPeekArray()
	{
		final FastFiFo<MyInteger> ff = new FastFiFo<MyInteger>(10);
		for (int i = 0; i < 100; i++)
		{
//			final MyInteger r = 
				ff.push(new MyInteger(i));
			final MyInteger[] a = ff.peekArray();
			Assertions.assertEquals(a[0], new MyInteger(Math.max(0, i - 9)), "loop " + i);
			if (i > 5)
			{
				Assertions.assertEquals(a[5], new MyInteger(Math.max(5, i - 4)), "loop " + i);
				Assertions.assertTrue(a[5].i > a[4].i, "loop " + i);
			}
		}
	}

	/**
	 * 
	 */
	@Test
	void testSpeed()
	{
		final FastFiFo<MyInteger> fifo = new FastFiFo<MyInteger>(10000);
		long l0 = System.currentTimeMillis();
		for (int i = 0; i < 1000000; i++)
		{
			fifo.push(new MyInteger(i));
		}
		System.out.println("T=" + (System.currentTimeMillis() - l0));

		l0 = System.currentTimeMillis();
		final ArrayList<MyInteger> v = new ArrayList<MyInteger>(10000);
		for (int i = 0; i < 100000; i++) // note the factor 10 less for the arraylist...
		{
			v.add(new MyInteger(i));
			if (i > 10000)
			{
				v.remove(0);
			}
		}
		System.out.println("T=" + (System.currentTimeMillis() - l0));

	}
}
