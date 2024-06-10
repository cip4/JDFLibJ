package org.cip4.jdflib.extensions;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Assertions;

class ColorMapperTest
{

	@Test
	void testtestGetColor()
	{
		Assertions.assertEquals("yellow", ColorMapper.getMatchingColor("Yellow"));
	}

}
