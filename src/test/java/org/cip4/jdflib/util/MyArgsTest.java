/*
 *
 * The CIP4 Software License, Version 1.0
 *
 *
 * Copyright (c) 2001-2011 The International Cooperation for the Integration of 
 * Processes in  Prepress, Press and Postpress (CIP4).  All rights 
 * reserved.
 *
 * Redistribution and use in source and binary forms, with or without
 * modification, are permitted provided that the following conditions
 * are met:
 *
 * 1. Redistributions of source code must retain the above copyright
 *    notice, this list of conditions and the following disclaimer. 
 *
 * 2. Redistributions in binary form must reproduce the above copyright
 *    notice, this list of conditions and the following disclaimer in
 *    the documentation and/or other materials provided with the
 *    distribution.
 *
 * 3. The end-user documentation included with the redistribution,
 *    if any, must include the following acknowledgment:  
 *       "This product includes software developed by the
 *        The International Cooperation for the Integration of 
 *        Processes in  Prepress, Press and Postpress (www.cip4.org)"
 *    Alternately, this acknowledgment may appear in the software itself,
 *    if and wherever such third-party acknowledgments normally appear.
 *
 * 4. The names "CIP4" and "The International Cooperation for the Integration of 
 *    Processes in  Prepress, Press and Postpress" must
 *    not be used to endorse or promote products derived from this
 *    software without prior written permission. For written 
 *    permission, please contact info@cip4.org.
 *
 * 5. Products derived from this software may not be called "CIP4",
 *    nor may "CIP4" appear in their name, without prior written
 *    permission of the CIP4 organization
 *
 * Usage of this software in commercial products is subject to restrictions. For
 * details please consult info@cip4.org.
 *
 * THIS SOFTWARE IS PROVIDED ``AS IS'' AND ANY EXPRESSED OR IMPLIED
 * WARRANTIES, INCLUDING, BUT NOT LIMITED TO, THE IMPLIED WARRANTIES
 * OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE ARE
 * DISCLAIMED.  IN NO EVENT SHALL THE INTERNATIONAL COOPERATION FOR
 * THE INTEGRATION OF PROCESSES IN PREPRESS, PRESS AND POSTPRESS OR
 * ITS CONTRIBUTORS BE LIABLE FOR ANY DIRECT, INDIRECT, INCIDENTAL,
 * SPECIAL, EXEMPLARY, OR CONSEQUENTIAL DAMAGES (INCLUDING, BUT NOT
 * LIMITED TO, PROCUREMENT OF SUBSTITUTE GOODS OR SERVICES; LOSS OF
 * USE, DATA, OR PROFITS; OR BUSINESS INTERRUPTION) HOWEVER CAUSED AND
 * ON ANY THEORY OF LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY,
 * OR TORT (INCLUDING NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT
 * OF THE USE OF THIS SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY OF
 * SUCH DAMAGE.
 * ====================================================================
 *
 * This software consists of voluntary contributions made by many
 * individuals on behalf of the The International Cooperation for the Integration 
 * of Processes in Prepress, Press and Postpress and was
 * originally based on software 
 * copyright (c) 1999-2001, Heidelberger Druckmaschinen AG 
 * copyright (c) 1999-2001, Agfa-Gevaert N.V. 
 *  
 * For more information on The International Cooperation for the 
 * Integration of Processes in  Prepress, Press and Postpress , please see
 * <http://www.cip4.org/>.
 *  
 * 
 */
/*
 * Created on Aug 12, 2005
 */
package org.cip4.jdflib.util;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**
 * 
 * @author Rainer Prosi, Heidelberger Druckmaschinen
 *
 */
public class MyArgsTest {

	final String[] _testArray = { "/Users/clabu/Documents/workarea/Elk/testarea/jakarta-tomcat-5.0.30/temp/VAHNSC6a7ag5ecAn9379.jdf", "-qc", "-v",
			"-d /Users/clabu/Documents/workarea/Elk/testarea/jakarta-tomcat-5.0.30/temp/tENgU4Gh3huO2iVH9380.xml",
			"-x /Users/clabu/Documents/workarea/Elk/testarea/jakarta-tomcat-5.0.30/temp/SimpleJDFPreprocessor_report9378.xml" };

	final String[] _testArrayWithQuotes = { "\"/Users/clabu/Documents/workarea/Elk/testarea/jakarta-tomcat-5.0.30/temp/VAHNSC6a7ag5ecAn9379.jdf\"", "-qcv",
			"-d \"/Users/clabu/Documents/workarea/Elk/testarea/jakarta-tomcat-5.0.30/temp/tENgU4Gh3huO2iVH9380.xml\"",
			"-x \"/Users/clabu/Documents/workarea/Elk/testarea/jakarta-tomcat-5.0.30/temp/SimpleJDFPreprocessor_report9378.xml\"" };

	MyArgs _myArgs;

	/**
	 * 
	 * @see junit.framework.TestCase#setUp()
	 * @throws Exception
	 */
	@BeforeEach
    public void setUp() throws Exception
	{
        _myArgs = new MyArgs(_testArray, "?cqvVntP", "dlLuhpx", null);

	}

	/**
	 * Test method for 'org.cip4.jdflib.util.MyArgs.MyArgs(String[], String, String)'
	 */
	@Test
	public void testMyArgsStringArrayStringString()
	{
		MyArgs args = new MyArgs(_testArray, "?cqvVntP", "dlLuhpx", null);
		System.out.println("Without quotes:");
		System.out.println(args);

		MyArgs args2 = new MyArgs(_testArrayWithQuotes, "?cqvVntP", "dlLuhpx", null);
		System.out.println("With quotes:");
		System.out.println(args2);
	}

	/**
	 * Test method for 'org.cip4.jdflib.util.MyArgs.MyArgs(String[], String, String, String)'
	 */
	@Test
	public void testMyArgsStringArrayStringStringString()
	{
		// dummy
	}

	/**
	 * Test method for 'org.cip4.jdflib.util.MyArgs.MyArgs(Vector, String, String, String)'
	 */
	@Test
	public void testMyArgsVectorStringStringString()
	{
		// dummy
	}

	/**
	 * Test method for 'org.cip4.jdflib.util.MyArgs.initMyArgs(Vector, String, String, String)'
	 */
	@Test
	public void testInitMyArgs()
	{
		// dummy
	}

	/**
	 * Test method for 'org.cip4.jdflib.util.MyArgs.toString()'
	 */
	@Test
	public void testToString()
	{
		// dummy
	}

	/**
	 * Test method for 'org.cip4.jdflib.util.MyArgs.parameter(String)'
	 */
	@Test
	public void testParameterString()
	{
		String d = _myArgs.parameter("d");
		Assertions.assertEquals(d, " /Users/clabu/Documents/workarea/Elk/testarea/jakarta-tomcat-5.0.30/temp/tENgU4Gh3huO2iVH9380.xml");
	}

	/**
	 * Test method for 'org.cip4.jdflib.util.MyArgs.parameter(char)'
	 */
	@Test
	public void testParameterChar()
	{
		// dummy
	}

	/**
	 * Test method for 'org.cip4.jdflib.util.MyArgs.parameterString(String)'
	 */
	@Test
	public void testParameterStringString()
	{
		// dummy
	}

	/**
	 * Test method for 'org.cip4.jdflib.util.MyArgs.parameterString(char)'
	 */
	@Test
	public void testParameterStringChar()
	{
		// dummy
	}

	/**
	 * Test method for 'org.cip4.jdflib.util.MyArgs.nargs()'
	 */
	@Test
	public void testNargs()
	{
		String[] s = { "-abc", "foo" };
		MyArgs args = new MyArgs(s, "ab", "c", null);
		Assertions.assertEquals(args.nargs(), 0);

	}

	/**
	 * Test method for 'org.cip4.jdflib.util.MyArgs.nargs()'
	 */
	@Test
	public void testSetFlag()
	{
		String[] s = { "-abc", "foo" };
		MyArgs args = new MyArgs(s, "ab", "c", null);
		Assertions.assertTrue(args.boolParameter('a'));
		args.setFlag('a', false);
		Assertions.assertFalse(args.boolParameter('a'));
		Assertions.assertFalse(args.boolParameter('d'));
		args.setFlag('d', true);
		Assertions.assertTrue(args.boolParameter('d'));
	}

	/**
	 * Test method for 'org.cip4.jdflib.util.MyArgs.nargs()'
	 */
	@Test
	public void testSetParameter()
	{
		String[] s = { "-abc", "foo" };
		MyArgs args = new MyArgs(s, "ab", "c", null);
		Assertions.assertEquals(args.parameter('c'), "foo");
		args.setParam('c', null);
		Assertions.assertNull(args.parameter('c'));
		args.setParam('c', "bar");
		Assertions.assertEquals(args.parameter('c'), "bar");
		Assertions.assertNull(args.parameter('d'));
		args.setParam('d', "bar");
		Assertions.assertEquals(args.parameter('d'), "bar");
	}

	/**
	 * Test method for 'org.cip4.jdflib.util.MyArgs.hasParameter()'
	 */
	public void hasParam()
	{
		String[] s = { "-abc", "foo" };
		MyArgs args = new MyArgs(s, "ab", "c", null);
		Assertions.assertTrue(args.hasParameter('a'));
		Assertions.assertTrue(args.hasParameter('b'));
		Assertions.assertTrue(args.hasParameter('c'));
		Assertions.assertFalse(args.hasParameter('d'));

	}

	/**
	 * Test method for 'org.cip4.jdflib.util.MyArgs.argument(int)'
	 */
	@Test
	public void testArgument()
	{
		String[] s = { "-abc", "foo", "bar" };
		MyArgs args = new MyArgs(s, "ab", "c", null);
		Assertions.assertEquals(args.nargs(), 1);
		Assertions.assertEquals(args.argument(0), "bar");
	}

	/**
	 * Test method for 'org.cip4.jdflib.util.MyArgs.argumentString(int)'
	 */
	@Test
	public void testArgumentString()
	{
		// dummy
	}

	/**
	 * Test method for 'org.cip4.jdflib.util.MyArgs.intParameter(char, int, int)'
	 */
	@Test
	public void testIntParameterCharIntInt()
	{
		// dummy
	}

	/**
	 * Test method for 'org.cip4.jdflib.util.MyArgs.intParameter(String, int, int)'
	 */
	@Test
	public void testIntParameterStringIntInt()
	{
		// dummy
	}

	/**
	 * Test method for 'org.cip4.jdflib.util.MyArgs.floatParameter(char, double)'
	 */
	@Test
	public void testFloatParameterCharDouble()
	{
		// dummy
	}

	/**
	 * Test method for 'org.cip4.jdflib.util.MyArgs.floatParameter(String, double)'
	 */
	@Test
	public void testFloatParameterStringDouble()
	{
		// dummy
	}

	/**
	 * Test method for 'org.cip4.jdflib.util.MyArgs.boolParameter(char, boolean)'
	 */
	@Test
	public void testBoolParameterCharBoolean()
	{
		// dummy
	}

	/**
	 * Test method for 'org.cip4.jdflib.util.MyArgs.boolParameter(String, boolean)'
	 */
	@Test
	public void testBoolParameterStringBoolean()
	{
		// dummy
	}

	/**
	 * Test method for 'org.cip4.jdflib.util.MyArgs.usage(String)'
	 */
	@Test
	public void testUsage()
	{
		// dummy
	}

}
