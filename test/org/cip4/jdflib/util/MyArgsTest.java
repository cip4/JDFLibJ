/*
 * Created on Aug 12, 2005
 */
package org.cip4.jdflib.util;

import junit.framework.TestCase;

public class MyArgsTest extends TestCase
{

	final String[] _testArray = {
			"/Users/clabu/Documents/workarea/Elk/testarea/jakarta-tomcat-5.0.30/temp/VAHNSC6a7ag5ecAn9379.jdf",
			"-qc",
			"-v",
			"-d /Users/clabu/Documents/workarea/Elk/testarea/jakarta-tomcat-5.0.30/temp/tENgU4Gh3huO2iVH9380.xml",
			"-x /Users/clabu/Documents/workarea/Elk/testarea/jakarta-tomcat-5.0.30/temp/SimpleJDFPreprocessor_report9378.xml" };

	final String[] _testArrayWithQuotes = {
			"\"/Users/clabu/Documents/workarea/Elk/testarea/jakarta-tomcat-5.0.30/temp/VAHNSC6a7ag5ecAn9379.jdf\"",
			"-qcv",
			"-d \"/Users/clabu/Documents/workarea/Elk/testarea/jakarta-tomcat-5.0.30/temp/tENgU4Gh3huO2iVH9380.xml\"",
			"-x \"/Users/clabu/Documents/workarea/Elk/testarea/jakarta-tomcat-5.0.30/temp/SimpleJDFPreprocessor_report9378.xml\"" };

	MyArgs _myArgs;

	@Override
	protected void setUp() throws Exception
	{
		super.setUp();
		_myArgs = new MyArgs(_testArray, "?cqvVntP", "dlLuhpx", null);

	}

	/*
	 * Test method for 'org.cip4.jdflib.util.MyArgs.MyArgs(String[], String,
	 * String)'
	 */
	public void testMyArgsStringArrayStringString()
	{
		MyArgs args = new MyArgs(_testArray, "?cqvVntP", "dlLuhpx", null);
		System.out.println("Without quotes:");
		System.out.println(args);

		MyArgs args2 = new MyArgs(_testArrayWithQuotes, "?cqvVntP", "dlLuhpx",
				null);
		System.out.println("With quotes:");
		System.out.println(args2);
	}

	/*
	 * Test method for 'org.cip4.jdflib.util.MyArgs.MyArgs(String[], String,
	 * String, String)'
	 */
	public void testMyArgsStringArrayStringStringString()
	{
		// dummy
	}

	/*
	 * Test method for 'org.cip4.jdflib.util.MyArgs.MyArgs(Vector, String,
	 * String, String)'
	 */
	public void testMyArgsVectorStringStringString()
	{
		// dummy
	}

	/*
	 * Test method for 'org.cip4.jdflib.util.MyArgs.initMyArgs(Vector, String,
	 * String, String)'
	 */
	public void testInitMyArgs()
	{
		// dummy
	}

	/*
	 * Test method for 'org.cip4.jdflib.util.MyArgs.toString()'
	 */
	public void testToString()
	{
		// dummy
	}

	/*
	 * Test method for 'org.cip4.jdflib.util.MyArgs.parameter(String)'
	 */
	public void testParameterString()
	{
		String d = _myArgs.parameter("d");
		assertEquals(
				d,
				"/Users/clabu/Documents/workarea/Elk/testarea/jakarta-tomcat-5.0.30/temp/tENgU4Gh3huO2iVH9380.xml");
	}

	/*
	 * Test method for 'org.cip4.jdflib.util.MyArgs.parameter(char)'
	 */
	public void testParameterChar()
	{
		// dummy
	}

	/*
	 * Test method for 'org.cip4.jdflib.util.MyArgs.parameterString(String)'
	 */
	public void testParameterStringString()
	{
		// dummy
	}

	/*
	 * Test method for 'org.cip4.jdflib.util.MyArgs.parameterString(char)'
	 */
	public void testParameterStringChar()
	{
		// dummy
	}

	/*
	 * Test method for 'org.cip4.jdflib.util.MyArgs.nargs()'
	 */
	public void testNargs()
	{
		String[] s = { "-abc", "foo" };
		MyArgs args = new MyArgs(s, "ab", "c", null);
		assertEquals(args.nargs(), 0);

	}

	/*
	 * Test method for 'org.cip4.jdflib.util.MyArgs.hasParameter()'
	 */
	public void hasParam()
	{
		String[] s = { "-abc", "foo" };
		MyArgs args = new MyArgs(s, "ab", "c", null);
		assertTrue(args.hasParameter('a'));
		assertTrue(args.hasParameter('b'));
		assertTrue(args.hasParameter('c'));
		assertFalse(args.hasParameter('d'));

	}

	/*
	 * Test method for 'org.cip4.jdflib.util.MyArgs.argument(int)'
	 */
	public void testArgument()
	{
		String[] s = { "-abc", "foo", "bar" };
		MyArgs args = new MyArgs(s, "ab", "c", null);
		assertEquals(args.nargs(), 1);
		assertEquals(args.argument(0), "bar");
	}

	/*
	 * Test method for 'org.cip4.jdflib.util.MyArgs.argumentString(int)'
	 */
	public void testArgumentString()
	{
		// dummy
	}

	/*
	 * Test method for 'org.cip4.jdflib.util.MyArgs.intParameter(char, int,
	 * int)'
	 */
	public void testIntParameterCharIntInt()
	{
		// dummy
	}

	/*
	 * Test method for 'org.cip4.jdflib.util.MyArgs.intParameter(String, int,
	 * int)'
	 */
	public void testIntParameterStringIntInt()
	{
		// dummy
	}

	/*
	 * Test method for 'org.cip4.jdflib.util.MyArgs.floatParameter(char,
	 * double)'
	 */
	public void testFloatParameterCharDouble()
	{
		// dummy
	}

	/*
	 * Test method for 'org.cip4.jdflib.util.MyArgs.floatParameter(String,
	 * double)'
	 */
	public void testFloatParameterStringDouble()
	{
		// dummy
	}

	/*
	 * Test method for 'org.cip4.jdflib.util.MyArgs.boolParameter(char,
	 * boolean)'
	 */
	public void testBoolParameterCharBoolean()
	{
		// dummy
	}

	/*
	 * Test method for 'org.cip4.jdflib.util.MyArgs.boolParameter(String,
	 * boolean)'
	 */
	public void testBoolParameterStringBoolean()
	{
		// dummy
	}

	/*
	 * Test method for 'org.cip4.jdflib.util.MyArgs.usage(String)'
	 */
	public void testUsage()
	{
		// dummy
	}

}
