/**
 * 
 */
package org.cip4.jdflib.util;

import java.util.ArrayList;

import org.cip4.jdflib.JDFTestCaseBase;

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
	public void testPush()
	{
		final FastFiFo<MyInteger> ff = new FastFiFo<MyInteger>(10);
		for (int i = 0; i < 100; i++)
		{
			final MyInteger r = ff.push(new MyInteger(i));
			if (i < 10)
			{
				assertNull("loop " + i, r);
			}
			else
			{
				assertEquals("loop " + i, r, new MyInteger(i - 10));
			}
		}
	}

	/**
	 * 
	 */
	public void testPeek()
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
					assertNull("loop " + i, r2);
				}
				else
				{
					assertEquals("loop " + i + "/" + j, r2, new MyInteger(i - Math.min(9, i) + j));
				}
			}
		}
	}

	/**
	 * 
	 */
	public void testPeekArray()
	{
		final FastFiFo<MyInteger> ff = new FastFiFo<MyInteger>(10);
		for (int i = 0; i < 100; i++)
		{
//			final MyInteger r = 
				ff.push(new MyInteger(i));
			final MyInteger[] a = ff.peekArray();
			assertEquals("loop " + i, a[0], new MyInteger(Math.max(0, i - 9)));
			if (i > 5)
			{
				assertEquals("loop " + i, a[5], new MyInteger(Math.max(5, i - 4)));
				assertTrue("loop " + i, a[5].i > a[4].i);
			}
		}
	}

	/**
	 * 
	 */
	public void testSpeed()
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
