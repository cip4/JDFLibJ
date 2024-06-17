/*
 *
 * The CIP4 Software License, Version 1.0
 *
 *
 * Copyright (c) 2001-2024 The International Cooperation for the Integration of
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
package org.cip4.jdflib.core;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;

import java.lang.reflect.Method;

import org.apache.commons.lang.StringUtils;
import org.cip4.jdflib.JDFTestCaseBase;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**
 * JUnit test case for JDFVersion.
 */
class JDFVersionTest extends JDFTestCaseBase
{
	boolean canTest;

	@Test
	void testLibArtifactId()
	{
		if (!canTest)
			return;

		// arrange
		final String expected = "JDFLibJ";

		// act
		final String actual = JDFVersion.LIB_ARTIFACT_ID;

		// assert
		assertEquals(expected, actual, "ArtifactID number is wrong.");
		System.out.println("JDFLibJ ArtifactID: " + actual);
	}

	@Test
	void testLibName()
	{
		if (!canTest)
			return;

		// arrange
		final String expected = "CIP4 JDF Writer Java";

		// act
		final String actual = JDFVersion.LIB_NAME;

		// assert
		assertEquals(expected, actual, "Name number is wrong.");
		System.out.println("JDFLibJ Name: " + actual);
	}

	@Test
	void testLibReleaseDate()
	{
		if (!canTest)
			return;

		// arrange

		// act
		final String actual = JDFVersion.LIB_RELEASE_DATE;

		// assert
		// assertTrue("ReleaseDate is wrong.", actual.matches("(\\d{4})-(\\d{2})-(\\d{2}) (\\d{2}):(\\d{2})"));
		System.out.println("JDFLibJ Release Date: " + actual);
	}

	@Test
	void testLibVersion()
	{
		if (!canTest)
			return;

		// arrange

		// act
		final String actual = JDFVersion.LIB_VERSION;

		// assert
		assertFalse(StringUtils.isEmpty(actual), "Version is wrong.");
		System.out.println("JDFLibJ Version: " + actual);
	}

	@Test
	void testLibMajorVersion()
	{
		if (!canTest)
			return;

		// arrange

		// act
		final String actual = JDFVersion.LIB_MAJOR_VERSION;

		// assert
		assertFalse(StringUtils.isEmpty(actual), "Version is wrong.");
		assertEquals(3, StringUtils.split(actual, ".").length, "Major Version is wrong.");
		assertEquals(1, StringUtils.split(actual, "-").length, "Major Version is wrong.");
		System.out.println("JDFLibJ Major Version: " + actual);
	}

	@Test
	void testLibMinorVersion()
	{

		if (!canTest)
			return;
		// arrange

		// act
		final String actual = JDFVersion.LIB_MINOR_VERSION;

		// assert
		assertFalse(StringUtils.isEmpty(actual), "Version is wrong.");
		assertEquals(1, StringUtils.split(actual, ".").length, "Major Version is wrong.");
		assertEquals(1, StringUtils.split(actual, "-").length, "Major Version is wrong.");
		System.out.println("JDFLibJ Major Version: " + actual);
	}

	@Test
	void testJdfVersion()
	{
		if (!canTest)
			return;
		// arrange
		final String expected = "1.8";

		// act
		final String actual = JDFVersion.JDF_VERSION;

		// assert
		assertEquals(expected, actual, "JDF Version number is wrong.");
		System.out.println("JDF Version: " + actual);
	}

	@Test
	void testJdfVersion_LibVersion()
	{
		if (!canTest)
			return;

		// arrange
		final String jdfVersion = JDFVersion.JDF_VERSION;
		final String libVersion = JDFVersion.LIB_VERSION;

		// act
		final int i = libVersion.indexOf(".") + 1;
		final int n = libVersion.lastIndexOf(".");

		final String extractedVersion = libVersion.substring(i, n);

		// assert
		assertEquals(jdfVersion, extractedVersion, "JDF Version doesn't match the Lib Version.");
		System.out.println(String.format("JDF Version: %s - Lib Version: %s (OK)", jdfVersion, libVersion));
	}

	@Test
	void testMinorVersion_1() throws Exception
	{
		if (!canTest)
			return;

		// arrange
		final Method method = JDFVersion.class.getDeclaredMethod("getMinorVersion", String.class);
		method.setAccessible(true);

		final String version = "2.1.4a.69";
		final String expected = "69";

		// act
		final String actual = (String) method.invoke(null, version);

		// assert
		assertEquals(expected, actual, "Minor Version is wrong.");
	}

	@Test
	void testMinorVersion_2() throws Exception
	{
		if (!canTest)
			return;

		// arrange
		final Method method = JDFVersion.class.getDeclaredMethod("getMinorVersion", String.class);
		method.setAccessible(true);

		final String version = "2.1.5.80-SNAPSHOT";
		final String expected = "80";

		// act
		final String actual = (String) method.invoke(null, version);

		// assert
		assertEquals(expected, actual, "Minor Version is wrong.");
	}

	@Test
	void testMajorVersion_1() throws Exception
	{
		if (!canTest)
			return;

		// arrange
		final Method method = JDFVersion.class.getDeclaredMethod("getMajorVersion", String.class);
		method.setAccessible(true);

		final String version = "2.1.4a.69";
		final String expected = "2.1.4a";

		// act
		final String actual = (String) method.invoke(null, version);

		// assert
		assertEquals(expected, actual, "Major Version is wrong.");
	}

	@Test
	void testMajorVersion_2() throws Exception
	{
		if (!canTest)
			return;

		// arrange
		final Method method = JDFVersion.class.getDeclaredMethod("getMajorVersion", String.class);
		method.setAccessible(true);

		final String version = "2.1.5.80-SNAPSHOT";
		final String expected = "2.1.5";

		// act
		final String actual = (String) method.invoke(null, version);

		// assert
		assertEquals(expected, actual, "Major Version is wrong.");
	}

	/**
	 * @see JDFTestCaseBase#setUp()
	 */
	@Override
	@BeforeEach
	public void setUp() throws Exception
	{
		try
		{
			canTest = JDFVersion.JDF_VERSION != null;
		}
		catch (final Throwable t)
		{
			canTest = false;
		}
		super.setUp();
	}
}
