/**
 * 
 */
package org.cip4.jdflib.util;

import org.cip4.jdflib.JDFTestCaseBase;

/**
  * @author Rainer Prosi, Heidelberger Druckmaschinen *
 */
public class UnitParserTest extends JDFTestCaseBase
{

	private UnitParser unitParser;

	/**
	 * @see org.cip4.jdflib.JDFTestCaseBase#setUp()
	 * @throws Exception
	*/
	@Override
	protected void setUp() throws Exception
	{
		super.setUp();
		this.unitParser = new UnitParser();
	}

	/**
	 * 
	 */
	public void testUnits()
	{
		assertEquals(unitParser.extractUnits("123"), "123");
		assertEquals(unitParser.extractUnits("abc def"), "abc def");
		assertEquals(unitParser.extractUnits("abc cm mm"), "abc cm mm");
		assertEquals(unitParser.extractUnits("10cm 10mm"), "283.46456692 28.34645669");
	}
}
