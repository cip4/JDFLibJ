package org.cip4.jdflib.datatypes;

import org.cip4.jdflib.JDFTestCaseBase;
import org.junit.Test;

public class StringSetTest extends JDFTestCaseBase
{

	/**
	 *
	 */
	@Test
	public void testAddMany()
	{
		StringSet.enable(true);
		for (int i = 0; i < 10000; i++)
		{
			StringSet.getString("" + (i % 100));
		}
		assertEquals(100, StringSet.size());
		StringSet.getString(null);
		assertEquals(101, StringSet.size());
		StringSet.enable(false);
		assertEquals(0, StringSet.size());
	}

	/**
	 *
	 */
	@Test
	public void testNull()
	{
		StringSet.enable(true);
		assertNull(StringSet.getString(null));
		StringSet.enable(false);
	}

}
