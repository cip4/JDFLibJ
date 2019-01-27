package org.cip4.jdflib.extensions;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class ColorMapperTest
{

	@Test
	public void testtestGetColor()
	{
		assertEquals("yellow", ColorMapper.getMatchingColor("Yellow"));
	}

}
