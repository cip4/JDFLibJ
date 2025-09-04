package org.cip4.jdflib.extensions;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class ColorMapperTest
{

	@Test
	void testtestGetColor()
	{
		Assertions.assertEquals("yellow", ColorMapper.getMatchingColor("Yellow"));
	}

}
