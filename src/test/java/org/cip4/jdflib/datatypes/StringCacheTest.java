package org.cip4.jdflib.datatypes;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

import org.cip4.jdflib.JDFTestCaseBase;
import org.junit.Test;

public class StringCacheTest extends JDFTestCaseBase
{

	/**
	 *
	 */
	@Test
	public void testAddMany()
	{
		for (int i = 0; i < 10000; i++)
		{
			StringCache.getCreateString("" + (i % 100));
		}
		assertEquals(100, StringCache.size(), 10);
		StringCache.getCreateString(null);
		assertEquals(101, StringCache.size(), 10);
		StringCache.enable(false);
		assertEquals(0, StringCache.size());
	}

	/**
	 *
	 */
	@Test
	public void testGetMany()
	{
		StringCache.enable(false);
		StringCache.enable(true);
		for (int i = 0; i < 10000; i++)
		{
			StringCache.getString("_" + (i % 100));
		}
		assertEquals(10, StringCache.size(), 10);
		StringCache.getCreateString(null);
		assertEquals(11, StringCache.size(), 10);
		StringCache.enable(false);
		assertEquals(0, StringCache.size());
	}

	/**
	 *
	 */
	@Test
	public void testCreateNull()
	{
		StringCache.enable(true);
		assertNull(StringCache.getCreateString(null));
		StringCache.enable(false);
	}

	/**
	 *
	 */
	@Test
	public void testNull()
	{
		StringCache.enable(true);
		assertNull(StringCache.getString(null));
		StringCache.enable(false);
	}

	/**
	 * @see org.cip4.jdflib.JDFTestCaseBase#setUp()
	 */
	@Override
	protected void setUp() throws Exception
	{
		StringCache.enable(false);
		StringCache.enable(true);
		super.setUp();
	}

	/**
	 * @see org.cip4.jdflib.JDFTestCaseBase#tearDown()
	 */
	@Override
	protected void tearDown() throws Exception
	{
		StringCache.enable(false);
		super.tearDown();
	}

}
