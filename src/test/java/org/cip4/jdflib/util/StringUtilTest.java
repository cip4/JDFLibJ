/*
 * The CIP4 Software License, Version 1.0
 *
 *
 * Copyright (c) 2001-2020 The International Cooperation for the Integration of Processes in Prepress, Press and Postpress (CIP4). All rights reserved.
 *
 * Redistribution and use in source and binary forms, with or without modification, are permitted provided that the following conditions are met:
 *
 * 1. Redistributions of source code must retain the above copyright notice, this list of conditions and the following disclaimer.
 *
 * 2. Redistributions in binary form must reproduce the above copyright notice, this list of conditions and the following disclaimer in the documentation and/or other materials provided with the
 * distribution.
 *
 * 3. The end-user documentation included with the redistribution, if any, must include the following acknowledgment: "This product includes software developed by the The International Cooperation for
 * the Integration of Processes in Prepress, Press and Postpress (www.cip4.org)" Alternately, this acknowledgment may appear in the software itself, if and wherever such third-party acknowledgments
 * normally appear.
 *
 * 4. The names "CIP4" and "The International Cooperation for the Integration of Processes in Prepress, Press and Postpress" must not be used to endorse or promote products derived from this software
 * without prior written permission. For written permission, please contact info@cip4.org.
 *
 * 5. Products derived from this software may not be called "CIP4", nor may "CIP4" appear in their name, without prior written permission of the CIP4 organization
 *
 * Usage of this software in commercial products is subject to restrictions. For details please consult info@cip4.org.
 *
 * THIS SOFTWARE IS PROVIDED ``AS IS'' AND ANY EXPRESSED OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO, THE IMPLIED WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE ARE
 * DISCLAIMED. IN NO EVENT SHALL THE INTERNATIONAL COOPERATION FOR THE INTEGRATION OF PROCESSES IN PREPRESS, PRESS AND POSTPRESS OR ITS CONTRIBUTORS BE LIABLE FOR ANY DIRECT, INDIRECT, INCIDENTAL,
 * SPECIAL, EXEMPLARY, OR CONSEQUENTIAL DAMAGES (INCLUDING, BUT NOT LIMITED TO, PROCUREMENT OF SUBSTITUTE GOODS OR SERVICES; LOSS OF USE, DATA, OR PROFITS; OR BUSINESS INTERRUPTION) HOWEVER CAUSED AND
 * ON ANY THEORY OF LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY, OR TORT (INCLUDING NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE OF THIS SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY
 * OF SUCH DAMAGE. ====================================================================
 *
 * This software consists of voluntary contributions made by many individuals on behalf of the The International Cooperation for the Integration of Processes in Prepress, Press and Postpress and was
 * originally based on software copyright (c) 1999-2001, Heidelberger Druckmaschinen AG copyright (c) 1999-2001, Agfa-Gevaert N.V.
 *
 * For more information on The International Cooperation for the Integration of Processes in Prepress, Press and Postpress , please see <http://www.cip4.org/>.
 *
 *
 * 04022005 VF initial version
 */
/*
 * Created on Aug 26, 2004
 *
 * To change the template for this generated file go to Window - Preferences - Java - Code Generation - Code and Comments
 */
package org.cip4.jdflib.util;

import java.io.File;
import java.nio.charset.StandardCharsets;
import java.util.Vector;

import org.apache.commons.lang.enums.ValuedEnum;
import org.cip4.jdflib.JDFTestCaseBase;
import org.cip4.jdflib.core.JDFConstants;
import org.cip4.jdflib.core.JDFElement.EnumOrientation;
import org.cip4.jdflib.core.JDFResourceLink.EnumUsage;
import org.cip4.jdflib.core.StringArray;
import org.cip4.jdflib.core.VString;
import org.cip4.jdflib.datatypes.JDFBaseDataTypes;
import org.cip4.jdflib.jmf.JDFMessage.EnumType;
import org.cip4.jdflib.util.StringUtil.EDataType;
import org.cip4.jdflib.util.StringUtil.StringReplacer;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

/**
 * @author MatternK
 *
 *         To change the template for this generated type comment go to Window - Preferences - Java - Code Generation - Code and Comments
 */
public class StringUtilTest extends JDFTestCaseBase
{
	/**
	 *
	 */
	@Test
	public void testSimpleRegexp()
	{
		Assertions.assertEquals("(.)(.+)", StringUtil.simpleRegExptoRegExp("?+", false));
		Assertions.assertEquals(StringUtil.simpleRegExptoRegExp("??", false), "(.)(.)");
		Assertions.assertEquals(StringUtil.simpleRegExptoRegExp("(a?)", false), "(a?)");
		Assertions.assertEquals(StringUtil.simpleRegExptoRegExp("*b??", false), "(.*)b(.)(.)");
		Assertions.assertEquals(StringUtil.simpleRegExptoRegExp("ab", false), "ab");
		Assertions.assertEquals(StringUtil.simpleRegExptoRegExp("a.b", false), "a\\.b");
		Assertions.assertEquals(StringUtil.simpleRegExptoRegExp("a(.+)b", false), "a(.+)b");
		Assertions.assertEquals(StringUtil.simpleRegExptoRegExp("*.b", false), "(.*)\\.b");
		Assertions.assertEquals(StringUtil.simpleRegExptoRegExp("(.*)\\.b", false), "(.*)\\.b", "don't reconvert real regexp");
		Assertions.assertTrue(StringUtil.matches("foo.txt", StringUtil.simpleRegExptoRegExp("*.tx*", false)));
		Assertions.assertTrue(StringUtil.matches(".tx", StringUtil.simpleRegExptoRegExp("*.tx*", false)));
		Assertions.assertTrue(StringUtil.matches("55", StringUtil.simpleRegExptoRegExp("55|56", false)));
		Assertions.assertTrue(StringUtil.matches("56", StringUtil.simpleRegExptoRegExp("55|56", false)));
		Assertions.assertFalse(StringUtil.matches("57", StringUtil.simpleRegExptoRegExp("55|56", false)));
		Assertions.assertFalse(StringUtil.matches("foo_txt", StringUtil.simpleRegExptoRegExp("*.tx*", false)));
		Assertions.assertTrue(StringUtil.matches("aa", StringUtil.simpleRegExptoRegExp("??", false)));
		Assertions.assertTrue(StringUtil.matches("abc", StringUtil.simpleRegExptoRegExp("*b?", false)));
		Assertions.assertTrue(StringUtil.matches("abc", StringUtil.simpleRegExptoRegExp("ab(.)", false)));
		Assertions.assertFalse(StringUtil.matches("ab", StringUtil.simpleRegExptoRegExp("ab(.)", false)));
		Assertions.assertFalse(StringUtil.matches("abcd", StringUtil.simpleRegExptoRegExp("ab(.)", false)));
		Assertions.assertFalse(StringUtil.matches("abc", StringUtil.simpleRegExptoRegExp("*b??", false)));
		Assertions.assertFalse(StringUtil.matches("abcd", StringUtil.simpleRegExptoRegExp("*b?", false)));
		Assertions.assertTrue(StringUtil.matches("axbc", StringUtil.simpleRegExptoRegExp("axb?", false)));

		Assertions.assertEquals(StringUtil.simpleRegExptoRegExp("(a)", true), "\\(a\\)");

	}

	/**
	 *
	 */
	@Test
	public void testWipeInvalidXML10Chars()
	{
		final char[] cs = new char[] { 'a', 0x7, 0x3, 'b', 0x5 };
		Assertions.assertEquals(StringUtil.wipeInvalidXML10Chars(new String(cs), null), "ab");
		Assertions.assertEquals(StringUtil.wipeInvalidXML10Chars(new String(cs), "_"), "a__b_");
		Assertions.assertEquals(StringUtil.wipeInvalidXML10Chars("abc", null), "abc");
	}

	/**
	 *
	 */
	@Test
	public void testGetRelativePath()
	{
		File f = new File("./a");
		Assertions.assertEquals(StringUtil.replaceChar(UrlUtil.getRelativePath(f, null), '\\', "/", 0), "a");
		f = new File("../a.b");
		Assertions.assertEquals(StringUtil.replaceChar(UrlUtil.getRelativePath(f, null), '\\', "/", 0), "../a.b");
		f = new File("./../a b/b");
		Assertions.assertEquals(StringUtil.replaceChar(UrlUtil.getRelativePath(f, null), '\\', "/", 0), "../a b/b");
		f = new File("a/b/c");
		Assertions.assertEquals(StringUtil.replaceChar(UrlUtil.getRelativePath(f, null), '\\', "/", 0), "a/b/c");
		f = new File("a/b/c");
		Assertions.assertEquals(StringUtil.replaceChar(UrlUtil.getRelativePath(f, f), '\\', "/", 0), ".");
	}

	/**
	 * test for getDefaultNull
	 */
	@Test
	public void testGetDefaultNull()
	{
		Assertions.assertNull(StringUtil.getDefaultNull(null, null));
		Assertions.assertNull(StringUtil.getDefaultNull("", ""));
		Assertions.assertNull(StringUtil.getDefaultNull(null, ""));
		Assertions.assertEquals("a", StringUtil.getDefaultNull("a", ""));
		Assertions.assertEquals("a", StringUtil.getDefaultNull("a", null));
	}

	/**
	 * test for getDefaultNull
	 */
	@Test
	public void testGetDistance()
	{
		Assertions.assertEquals(StringUtil.getDistance("", null, false, false, false), 0);
		Assertions.assertEquals(StringUtil.getDistance("", "A", false, true, true), 0);
		Assertions.assertEquals(StringUtil.getDistance("B", "A", false, true, true), 8);
		Assertions.assertEquals(StringUtil.getDistance("AA", "A", false, true, true), 8);
		Assertions.assertEquals(StringUtil.getDistance("BB", "BA", false, true, true), 4);
		Assertions.assertEquals(StringUtil.getDistance("BBB", "BBA", false, true, true), 2);
		Assertions.assertEquals(StringUtil.getDistance("BBBB", "BBBA", false, true, true), 1);
		Assertions.assertEquals(StringUtil.getDistance(null, "A", false, true, true), 0);
		Assertions.assertEquals(StringUtil.getDistance("a", null, false, true, true), 0);
		Assertions.assertEquals(StringUtil.getDistance("a", "", false, true, true), 0);
		Assertions.assertEquals(StringUtil.getDistance("a", "A", false, true, false), 0);
		Assertions.assertEquals(StringUtil.getDistance("a", "AA", false, true, false), 8);
		Assertions.assertEquals(StringUtil.getDistance("a  b", "a B", true, true, false), 0);
		Assertions.assertEquals(StringUtil.getDistance("hallo", "hllo", true, true, false), 1);
		Assertions.assertEquals(StringUtil.getDistance("hasso", "haßo", true, true, false), 2);
	}

	/**
	 * test for getDefaultNull
	 */
	@Test
	public void testGetDistanceSlide()
	{
		Assertions.assertEquals(0, StringUtil.getDistance("", null, false, false, false, true));
		Assertions.assertEquals(0, StringUtil.getDistance("AA", "A", false, true, true, true));
		Assertions.assertEquals(4, StringUtil.getDistance("AAABCS", "AC", false, true, true, true));
		Assertions.assertEquals(0, StringUtil.getDistance("AAABCS99999", "ABC", false, true, true, true));
		Assertions.assertEquals(0, StringUtil.getDistance("abc", "AAABCS99999", false, true, true, true));
	}

	/**
	 * test for getNonEmpty
	 */
	@Test
	public void testGetNonEmpty()
	{
		Assertions.assertNull(StringUtil.getNonEmpty(""));
		Assertions.assertNull(StringUtil.getNonEmpty(null));
		Assertions.assertEquals("a", StringUtil.getNonEmpty("a"));
	}

	/**
	 * test for getNonEmpty
	 */
	@Test
	public void testIsEmpty()
	{
		Assertions.assertTrue(StringUtil.isEmpty(""));
		Assertions.assertTrue(StringUtil.isEmpty((String) null));
		Assertions.assertFalse(StringUtil.isEmpty("a"));
	}

	/**
	 *
	 */
	@Test
	public void testIsEmptyList()
	{
		Assertions.assertTrue(StringUtil.isEmpty((StringArray) null));
		final StringArray v = new StringArray();
		Assertions.assertTrue(StringUtil.isEmpty(v));
		v.appendUnique("");
		Assertions.assertTrue(StringUtil.isEmpty(v));
		v.set(0, "b");
		Assertions.assertFalse(StringUtil.isEmpty(v));
	}

	/**
	 * test for getNonEmpty
	 */
	@Test
	public void testHasContent()
	{
		Assertions.assertFalse(StringUtil.hasContent(" "));
		Assertions.assertFalse(StringUtil.hasContent(null));
		Assertions.assertFalse(StringUtil.hasContent("\t\n "));
		Assertions.assertTrue(StringUtil.hasContent(" a"));
	}

	/**
	 * test for gerRandomString
	 */
	@Test
	public void testGetRandomString()
	{
		final VString v = new VString();
		for (int i = 0; i < 1000; i++)
		{
			final String s = StringUtil.getRandomString();
			if (i % 100 == 0)
			{
				System.out.println(s);
			}
			v.appendUnique(s);
			Assertions.assertTrue(s.length() > 1);
		}
		Assertions.assertTrue(v.size() < 900, "should be many different");
		Assertions.assertTrue(v.size() > 5);
	}

	/**
	 *
	 */
	@Test
	public void testSprintfString()
	{
		Assertions.assertEquals(StringUtil.sprintf("part_%04i.txt", "" + 6), "part_0006.txt");
		Assertions.assertEquals(StringUtil.sprintf("abc%03idef", "5"), "abc005def");
		Assertions.assertEquals(StringUtil.sprintf("abc%03idef", "5.0"), "abc005def");
		Assertions.assertEquals(StringUtil.sprintf("abc%03i%02idef", "5.0,5"), "abc00505def");
		Assertions.assertEquals(StringUtil.sprintf("abc%03i%02idef%%%s", "5.0,5,abcdefghi"), "abc00505def%abcdefghi");
		Assertions.assertEquals(StringUtil.sprintf("%2x", "12"), " c");
		Assertions.assertEquals(StringUtil.sprintf("%2x", "18"), "12");
		Assertions.assertEquals(StringUtil.sprintf("%s", "\\,"), ",");
		Assertions.assertEquals(StringUtil.sprintf("%s", "\\\\,"), "\\,");
		Assertions.assertEquals(StringUtil.sprintf("%s_%s", "\\\\,,a"), "\\,_a");

	}

	/**
	 *
	 */
	@Test
	public void testSprintf()
	{
		Object[] o = new Object[1];
		o[0] = new Integer(5);
		Assertions.assertEquals(StringUtil.sprintf("abc%03idef", o), "abc005def");
		o[0] = "foobar";
		Assertions.assertEquals(StringUtil.sprintf("abc%7sdef", o), "abc foobardef");
		Assertions.assertEquals(StringUtil.sprintf("abc%7s7def", o), "abc foobar7def");
		Assertions.assertEquals(StringUtil.sprintf("%7sdef", o), " foobardef");
		Assertions.assertEquals(StringUtil.sprintf("%7s", o), " foobar");
		Assertions.assertEquals(StringUtil.sprintf("%%_%7s", o), "%_ foobar");
		Assertions.assertEquals(StringUtil.sprintf("%%", o), "%");
		Assertions.assertEquals(StringUtil.sprintf("765", o), "765");
		try
		{
			StringUtil.sprintf("abc%7idef", o);
			Assertions.fail("foobar is an int?");
		}
		catch (final Exception x)
		{
			//
		}

		o = new Object[] { new Integer(5), "foobar" };
		Assertions.assertEquals(StringUtil.sprintf("abc %02i%7sdef", o), "abc 05 foobardef");
		Assertions.assertEquals(StringUtil.sprintf("%02i%7sdef", o), "05 foobardef");
		o = new Object[] { new Long(5), "foobar" };
		Assertions.assertEquals(StringUtil.sprintf("abc %02i%7sdef", o), "abc 05 foobardef");
		Assertions.assertEquals(StringUtil.sprintf("%02i%7sdef", o), "05 foobardef");
		o = new Object[] { "5", "foobar" };
		Assertions.assertEquals(StringUtil.sprintf("abc %02i%7sdef", o), "abc 05 foobardef");
		Assertions.assertEquals(StringUtil.sprintf("%02i%7sdef", o), "05 foobardef");
	}

	/**
	 *
	 */
	@Test
	public void testSetHexBinaryBytes()
	{
		final String strTestString = "ABCDEFGHIJKLMNOPQESTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789ï¿½ï¿½ï¿½ï¿½ï¿½ï¿½";
		final byte[] buffer = strTestString.getBytes();
		final String strTemp = StringUtil.setHexBinaryBytes(buffer, -1);
		final byte[] tempBuffer = StringUtil.getHexBinaryBytes(strTemp.getBytes());
		final String strResultString = new String(tempBuffer);
		Assertions.assertEquals(strTestString, strResultString, "Input and Outputstring are not equal");
	}

	/**
	 *
	 */
	@Test
	public void testSetHexBinaryBytesNull()
	{
		final String strTemp = StringUtil.setHexBinaryBytes(null, -1);
		Assertions.assertEquals("", strTemp);
	}

	/**
	 *
	 */
	@Test
	public void testGetHexBinaryBytesNull()
	{
		final byte[] tempBuffer = StringUtil.getHexBinaryBytes(null);
		Assertions.assertEquals(0, tempBuffer.length);
	}

	/**
	 *
	 */
	@Test
	public void testGetUTF8Bytes()
	{
		// String strTestString = "ï¿½";
		final String strTestString = "ABCDEFGHIJKLMNOPQESTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789aöü€";
		final byte[] utf8Buf = StringUtil.getUTF8Bytes(strTestString);
		final String newString = StringUtil.getUTF8String(utf8Buf);
		final byte[] charBuf = strTestString.getBytes();
		final String newString2 = StringUtil.getUTF8String(charBuf);
		Assertions.assertEquals(newString, newString2);
		final byte[] green = new byte[] { 'g', 'r', (byte) 0xfc, 'n' };
		final String greenString = StringUtil.getUTF8String(green);

		Assertions.assertEquals(greenString, "grün", "incorrectly encoded grün is also heuristically fixed");
	}

	/**
	 *
	 */
	@Test
	public void testSetUTF8Bytes()
	{
		// String strTestString = "ï¿½";
		final String strTestString = "ABCDEFGHIJKLMNOPQESTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789ï¿½ï¿½ï¿½ï¿½ï¿½ï¿½";
		final byte[] utf8Buf = StringUtil.getUTF8Bytes(strTestString);
		final String newString = StringUtil.getUTF8String(utf8Buf);
		final byte[] utf8Buf2 = StringUtil.getUTF8Bytes(newString);
		final String strResultString = StringUtil.getUTF8String(utf8Buf2);
		Assertions.assertEquals(utf8Buf.length, utf8Buf2.length, "Input and Output bytes are not equal");
		Assertions.assertEquals(strTestString, newString, "Input and Output string are not equal");
		Assertions.assertEquals(strTestString, strResultString, "Input and Output string are not equal");
	}

	/**
	 *
	 */
	@Test
	public void testSetVStringEnum()
	{
		final Vector<Object> v = new Vector<>();
		v.add(EnumUsage.Input);
		v.add(EnumUsage.Output);
		Assertions.assertEquals(StringUtil.setvString(v, " ", null, null), "Input Output");
	}

	/**
	 *
	 */
	@Test
	public void testSetVString()
	{
		final VString v = new VString();
		v.add("1");
		v.add("22");
		v.add("333");
		v.add("4444");
		String s = StringUtil.setvString(v);

		Assertions.assertEquals("1 22 333 4444", s, "s");
		s = StringUtil.setvString(v, "", "", "");
		Assertions.assertEquals("1223334444", s, "s");
		s = StringUtil.setvString(v, null, null, null);
		Assertions.assertEquals("1223334444", s, "s");

		String[] a = new String[v.size()];
		a = v.toArray(a);
		s = StringUtil.setvString(a, " ", null, null);
		Assertions.assertEquals("1 22 333 4444", s, "s");
	}

	/**
	 *
	 */
	@Test
	public void testEscape()
	{
		final String iri = "file://myHost/a/c%20aöü%25&?.txtß€";
		Assertions.assertEquals(iri, StringUtil.unEscape(StringUtil.escape(iri, ":&?%", "%", 16, 2, 0x21, 127), "%", 16, 2), "escape round trip");
		Assertions.assertEquals("%25_%c3%bc", StringUtil.escape("%_ü", ":&?%", "%", 16, 2, 0x21, 127), "escape ");
		Assertions.assertEquals("%e2%82%ac", StringUtil.escape(new String(StringUtil.getUTF8Bytes("€")), ":&?%", "%", 16, 2, 0x21, 127), "escape ");
		Assertions.assertEquals("€", StringUtil.escape("€", null, "%", 16, 2, 0x21, -1));
		Assertions.assertEquals("ä", StringUtil.escape("ä", null, "%", 16, 2, 0x21, -1));
		Assertions.assertEquals("a__a", StringUtil.escape("aäa", null, "_", -1, 0, 0x21, 127), "ä is 2 bytes --> __");
		Assertions.assertEquals("a____a", StringUtil.escape("a€_a", null, "_", -1, 0, 0x21, 127));
		Assertions.assertEquals("a%20b", StringUtil.escape("a b", " \t", "%", 16, 2, 0, -19));

		// assertEquals("a_€a", StringUtil.escape("a€a", null, "_", 0, 0, 0x21, 127));
	}

	/**
	 *
	 */
	@Test
	public void testUnEscape()
	{
		Assertions.assertEquals("\\", StringUtil.unEscape("\\\\", "\\", -1, 1));
	}

	/**
	 *
	 */
	@Test
	public void testExtension()
	{
		final String iri = "file://my.Host/a.n/c%20ï¿½ï¿½ï¿½%25&?.txtï¿½";
		Assertions.assertEquals(UrlUtil.extension(iri), "txtï¿½");
		Assertions.assertNull(UrlUtil.extension("abc"));
	}

	/**
	 * test regexp matching utility
	 *
	 */
	@Test
	public void testMatchesIgnoreCase()
	{
		Assertions.assertFalse(StringUtil.matchesIgnoreCase(null, "(.+ )*(BB)( .+)*", false));
		Assertions.assertTrue(StringUtil.matchesIgnoreCase("a bb c", "(.+ )*(BB)( .+)*", false));
		Assertions.assertTrue(StringUtil.matchesIgnoreCase("ff a bb c", "*B*", false));
		Assertions.assertTrue(StringUtil.matchesIgnoreCase("mailTo:a@b.c", JDFConstants.REGEXP_EMAIL, false));
		Assertions.assertFalse(StringUtil.matchesIgnoreCase("mailT:a@b.c", JDFConstants.REGEXP_EMAIL, false));
	}

	/**
	 * test prefix stripper
	 *
	 */
	@Test
	public void testStripPrefix()
	{
		Assertions.assertEquals(StringUtil.stripPrefix("a:b", "a:", true), "b");
		Assertions.assertEquals(StringUtil.stripPrefix("a:b", "A:", true), "b");
		Assertions.assertEquals(StringUtil.stripPrefix("a:B", "A:", true), "B");
		Assertions.assertEquals(StringUtil.stripPrefix("a:b", "A:", false), "a:b");
		Assertions.assertEquals(StringUtil.stripPrefix("abcdef", "ABC", true), "def");
		Assertions.assertNull(StringUtil.stripPrefix(null, "A:", false));
		Assertions.assertEquals(StringUtil.stripPrefix("a:b", null, false), "a:b");
	}

	/**
	 *
	 */
	@Test
	public void testStripNot()
	{
		Assertions.assertEquals(StringUtil.stripNot("a1b321", "12"), "121");
		Assertions.assertNull(StringUtil.stripNot("a1b321", null));
		Assertions.assertNull(StringUtil.stripNot("a1b321", "7"));
	}

	/**
	 *
	 */
	@Test
	public void testStripQuote()
	{
		Assertions.assertEquals(StringUtil.stripQuote(",123,", ",", true), "123");
		Assertions.assertEquals(StringUtil.stripQuote(",123,", ",", false), "123");
		Assertions.assertEquals(StringUtil.stripQuote(",123, ", ",", false), ",123, ");
		Assertions.assertEquals(StringUtil.stripQuote(" 	,123, ", ",", true), "123");
		Assertions.assertEquals(StringUtil.stripQuote(",", ",", true), ",");
	}

	/**
	 *
	 */
	@Test
	public void testTrim()
	{
		Assertions.assertEquals(StringUtil.trim(",123,", ","), "123");
		Assertions.assertEquals(StringUtil.trim(", ,123,", ", &"), "123");
		Assertions.assertEquals(StringUtil.trim("123", ", &"), "123");
		Assertions.assertEquals(StringUtil.trim("123&", ", &"), "123");
		Assertions.assertEquals(StringUtil.trim(" 123", ", &"), "123");
		Assertions.assertEquals(StringUtil.trim("", ", &"), null);
		Assertions.assertEquals(StringUtil.trim("   ", " "), null);
		Assertions.assertEquals(StringUtil.trim("   ", null), null);
		Assertions.assertEquals(StringUtil.trim(null, null), null);
	}

	/**
	 * test regexp matching utility
	 *
	 */
	@Test
	public void testMatchesSimple()
	{
		Assertions.assertFalse(StringUtil.matchesSimple(null, "(.+ )*(BB)( .+)*", false));
		Assertions.assertTrue(StringUtil.matchesSimple("a bb c", "(.+ )*(bb)( .+)*", false));
		Assertions.assertTrue(StringUtil.matchesSimple("b bb c", "(.* )*(bb)( .+)*", false));
		Assertions.assertTrue(StringUtil.matchesSimple("a bb", "(.+ )*(bb)( .+)*", false));
		Assertions.assertTrue(StringUtil.matchesSimple("bb", "(.+ )*(bb)( .+)*", false));
		Assertions.assertFalse(StringUtil.matchesSimple(" bb", "(.+ )*(bb)( .+)*", false));
		Assertions.assertFalse(StringUtil.matchesSimple("bb ", "(.+ )*(bb)( .+)*", false));
		Assertions.assertFalse(StringUtil.matchesSimple("a", "(.+ )*(bb)( .+)*", false));
		Assertions.assertFalse(StringUtil.matchesSimple("a c", "(.+ )*(bb)( .+)*", false));
		Assertions.assertFalse(StringUtil.matchesSimple("a b c", "(.+ )*(bb)( .+)*", false));
		Assertions.assertFalse(StringUtil.matchesSimple("123456", "\\d{5,5}", false));
		Assertions.assertFalse(StringUtil.matchesSimple("1234", "\\d{5,5}", false));
		Assertions.assertTrue(StringUtil.matchesSimple("12345", "\\d{5,5}"));
		Assertions.assertTrue(StringUtil.matchesSimple("abc", "*", false));
		Assertions.assertTrue(StringUtil.matchesSimple("abc", "?*", false));
		Assertions.assertTrue(StringUtil.matchesSimple("abc", "?+", false));
		Assertions.assertTrue(StringUtil.matchesSimple("abc", "", false));
		Assertions.assertTrue(StringUtil.matchesSimple("äbc", "???", false));
		Assertions.assertFalse(StringUtil.matchesSimple("abc", "????", false));
		Assertions.assertFalse(StringUtil.matchesSimple("abc", "??", false));
		Assertions.assertTrue(StringUtil.matchesSimple("€bc", null, false));
		Assertions.assertTrue(StringUtil.matchesSimple("€", "(€)?", false));
		Assertions.assertTrue(StringUtil.matchesSimple("€€", "€{0,2}", false));
		Assertions.assertFalse(StringUtil.matchesSimple("€€€", "€{0,2}", false));
		Assertions.assertTrue(StringUtil.matchesSimple("", "(€)?", false));
		Assertions.assertTrue(StringUtil.matchesSimple("12de", JDFConstants.REGEXP_HEXBINARY, false));
		Assertions.assertFalse(StringUtil.matchesSimple("12d", JDFConstants.REGEXP_HEXBINARY, false));
		Assertions.assertFalse(StringUtil.matchesSimple("12dk", JDFConstants.REGEXP_HEXBINARY, false));

		Assertions.assertTrue(StringUtil.matchesSimple("€", "(€)?", false));

		Assertions.assertFalse(StringUtil.matchesSimple("abc", "??", false));
		Assertions.assertTrue(StringUtil.matchesSimple(null, null, false));
		Assertions.assertFalse(StringUtil.matchesSimple("abc", "?", false));
		Assertions.assertTrue(StringUtil.matchesSimple("a b", "(a)?( b)?( c)?", false));
		Assertions.assertTrue(StringUtil.matchesSimple("a b", "(a)? (b)?", false));
		Assertions.assertTrue(StringUtil.matchesSimple("a b", "a?(( )*b)?", false));
		Assertions.assertTrue(StringUtil.matchesSimple("a", "a?(( )*b)?", false));
		Assertions.assertTrue(StringUtil.matchesSimple("b", "a?(( )*b)?", false));
		Assertions.assertTrue(StringUtil.matchesSimple("b a c", "((.+ )*((a)|(b))( .+)*)+", false));
		Assertions.assertTrue(StringUtil.matchesSimple("b a c", "((.+ )*((a)|(b))( .+)*){1,1}", false));
		Assertions.assertFalse(StringUtil.matchesSimple("d e c", "((.+ )*((a)|(b))( .+)*)+", false));
		Assertions.assertFalse(StringUtil.matchesSimple("b b", "a?(( )*b)?", false));
		Assertions.assertTrue(StringUtil.matchesSimple("MIS_L2-1.3", "((.+ )*((MIS_L2-1.3)|(MISCPS_L1-1.3))( .+)*)+", false));

		Assertions.assertTrue(StringUtil.matchesSimple("a-aB.3@b.c", JDFConstants.REGEXP_EMAIL, false));
		Assertions.assertTrue(StringUtil.matchesSimple("a@b.c", JDFConstants.REGEXP_EMAIL, false));
		Assertions.assertTrue(StringUtil.matchesSimple("mailto:a@b.c", JDFConstants.REGEXP_EMAIL, false));
		Assertions.assertFalse(StringUtil.matchesSimple("mailt:a@b.c", JDFConstants.REGEXP_EMAIL, false));
		Assertions.assertTrue(StringUtil.matchesSimple("aa@b.c", JDFConstants.REGEXP_EMAIL, false));
		Assertions.assertFalse(StringUtil.matchesSimple("a@b", JDFConstants.REGEXP_EMAIL, false));
		Assertions.assertTrue(StringUtil.matchesSimple("+(1).2/344", JDFConstants.REGEXP_PHONE, false));
		Assertions.assertFalse(StringUtil.matchesSimple("+(1).2 344", JDFConstants.REGEXP_PHONE, false));

		Assertions.assertTrue(StringUtil.matchesSimple("ab", "a*", false));
		Assertions.assertTrue(StringUtil.matchesSimple("ab", "a(.*)", false));
		Assertions.assertTrue(StringUtil.matchesSimple("a", "a*", false));
		Assertions.assertFalse(StringUtil.matchesSimple("a", "a(.(.*))", false));
		Assertions.assertTrue(StringUtil.matchesSimple("a", "a(b)?", false));
		Assertions.assertTrue(StringUtil.matchesSimple("ab", "a(b)?", false));
		Assertions.assertFalse(StringUtil.matchesSimple("ac", "a(b)?", false));

		Assertions.assertTrue(StringUtil.matchesSimple("a b", "a b", false));
		Assertions.assertTrue(StringUtil.matchesSimple("abc123ä", "abc123ä", false));
		Assertions.assertTrue(StringUtil.matchesSimple("GangBrochureA4", "(Gang)?Bro(.)*", false));

	}

	/**
	 * test regexp matching utility
	 *
	 */
	@Test
	public void testMatchesRegExp()
	{
		Assertions.assertFalse(StringUtil.matches(null, "(.+ )*(BB)( .+)*"));
		Assertions.assertTrue(StringUtil.matches("a bb c", "(.+ )*(bb)( .+)*"));
		Assertions.assertTrue(StringUtil.matches("b bb c", "(.* )*(bb)( .+)*"));
		Assertions.assertTrue(StringUtil.matches("a bb", "(.+ )*(bb)( .+)*"));
		Assertions.assertTrue(StringUtil.matches("bb", "(.+ )*(bb)( .+)*"));
		Assertions.assertFalse(StringUtil.matches(" bb", "(.+ )*(bb)( .+)*"));
		Assertions.assertFalse(StringUtil.matches("bb ", "(.+ )*(bb)( .+)*"));
		Assertions.assertFalse(StringUtil.matches("a", "(.+ )*(bb)( .+)*"));
		Assertions.assertFalse(StringUtil.matches("a c", "(.+ )*(bb)( .+)*"));
		Assertions.assertFalse(StringUtil.matches("a b c", "(.+ )*(bb)( .+)*"));
		Assertions.assertFalse(StringUtil.matches("123456", "\\d{5,5}"));
		Assertions.assertFalse(StringUtil.matches("1234", "\\d{5,5}"));
		Assertions.assertTrue(StringUtil.matches("12345", "\\d{5,5}"));
		Assertions.assertTrue(StringUtil.matches("abc", ".*"));
		Assertions.assertTrue(StringUtil.matches("abc", ".?.*"));
		Assertions.assertTrue(StringUtil.matches("abc", ".+"));
		Assertions.assertTrue(StringUtil.matches("abc", ""));
		Assertions.assertTrue(StringUtil.matches("€bc", null));
		Assertions.assertTrue(StringUtil.matches("€", "(€)?"));
		Assertions.assertTrue(StringUtil.matches("€€", "€{0,2}"));
		Assertions.assertFalse(StringUtil.matches("€€€", "€{0,2}"));
		Assertions.assertTrue(StringUtil.matches("", "(€)?"));
		Assertions.assertTrue(StringUtil.matches("12de", JDFConstants.REGEXP_HEXBINARY));
		Assertions.assertFalse(StringUtil.matches("12d", JDFConstants.REGEXP_HEXBINARY));
		Assertions.assertFalse(StringUtil.matches("12dk", JDFConstants.REGEXP_HEXBINARY));

		Assertions.assertTrue(StringUtil.matches("€", "(€)?"));

		Assertions.assertTrue(StringUtil.matches(null, null));
		Assertions.assertFalse(StringUtil.matches("abc", "?"));
		Assertions.assertTrue(StringUtil.matches("a b", "(a)?( b)?( c)?"));
		Assertions.assertTrue(StringUtil.matches("a b", "(a)? (b)?"));
		Assertions.assertTrue(StringUtil.matches("a b", "a?(( )*b)?"));
		Assertions.assertTrue(StringUtil.matches("a", "a?(( )*b)?"));
		Assertions.assertTrue(StringUtil.matches("b", "a?(( )*b)?"));
		Assertions.assertTrue(StringUtil.matches("b a c", "((.+ )*((a)|(b))( .+)*)+"));
		Assertions.assertTrue(StringUtil.matches("b a c", "((.+ )*((a)|(b))( .+)*){1,1}"));
		Assertions.assertFalse(StringUtil.matches("d e c", "((.+ )*((a)|(b))( .+)*)+"));
		Assertions.assertFalse(StringUtil.matches("b b", "a?(( )*b)?"));
		Assertions.assertTrue(StringUtil.matches("MIS_L2-1.3", "((.+ )*((MIS_L2-1.3)|(MISCPS_L1-1.3))( .+)*)+"));

		Assertions.assertTrue(StringUtil.matches("a-aB.3@b.c", JDFConstants.REGEXP_EMAIL));
		Assertions.assertTrue(StringUtil.matches("a@b.c", JDFConstants.REGEXP_EMAIL));
		Assertions.assertTrue(StringUtil.matches("mailto:a@b.c", JDFConstants.REGEXP_EMAIL));
		Assertions.assertFalse(StringUtil.matches("mailt:a@b.c", JDFConstants.REGEXP_EMAIL));
		Assertions.assertTrue(StringUtil.matches("aa@b.c", JDFConstants.REGEXP_EMAIL));
		Assertions.assertFalse(StringUtil.matches("a@b", JDFConstants.REGEXP_EMAIL));
		Assertions.assertTrue(StringUtil.matches("+(1).2/344", JDFConstants.REGEXP_PHONE));
		Assertions.assertFalse(StringUtil.matches("+(1).2 344", JDFConstants.REGEXP_PHONE));

		Assertions.assertTrue(StringUtil.matches("ab", "a.*"));
		Assertions.assertTrue(StringUtil.matches("ab", "a(.*)"));
		Assertions.assertTrue(StringUtil.matches("a", "a*"));
		Assertions.assertFalse(StringUtil.matches("a", "a(.(.*))"));
		Assertions.assertTrue(StringUtil.matches("a", "a(b)?"));
		Assertions.assertTrue(StringUtil.matches("ab", "a(b)?"));
		Assertions.assertFalse(StringUtil.matches("ac", "a(b)?"));

		Assertions.assertTrue(StringUtil.matches("a b", "a b"));
		Assertions.assertTrue(StringUtil.matches("abc123ä", "abc123ä"));
		Assertions.assertTrue(StringUtil.matches("GangBrochureA4", "(Gang)?Bro(.)*"));
		Assertions.assertFalse(StringUtil.matches("GangBrochureA4", "(Gang)?Bro(.)*5"));

		Assertions.assertTrue(StringUtil.matches("GangBrochureA4 (1 3~6)", "(.)*\\(([\\d\\s~])+\\)"));
		Assertions.assertFalse(StringUtil.matches("GangBrochureA4 (1 3~6", "(.)*\\(([\\d\\s~])+\\)"));
		Assertions.assertFalse(StringUtil.matches("GangBrochureA4", "(.)*\\(([\\d\\s~])+\\)"));

	}

	/**
	 * test normalize utility
	 *
	 */
	@Test
	public void testNormalize()
	{
		Assertions.assertEquals("a b", StringUtil.normalize("\n  a   \t  b ", false));
		Assertions.assertEquals("a bbb", StringUtil.normalize("\n  a   \t  BBb ", true));
		Assertions.assertEquals("a BBb", StringUtil.normalize("\n  a   \t  BBb ", false));
		Assertions.assertNull(StringUtil.normalize("\n      \t   ", true));
		Assertions.assertNull(StringUtil.normalize(null, true));
	}

	/**
	 * test normalize utility
	 *
	 */
	@Test
	public void testNormalize3()
	{
		Assertions.assertEquals("a11b", StringUtil.normalize("\n  a   \t  b ", false, "11"));
		Assertions.assertEquals("abbb", StringUtil.normalize("\n  a   \t  BBb ", true, null));
		Assertions.assertEquals("aBBb", StringUtil.normalize("\n  a   \t  BBb ", false, null));
		Assertions.assertNull(StringUtil.normalize("\n      \t   ", true, null));
		Assertions.assertNull(StringUtil.normalize(null, true, null));
	}

	/**
	 *
	 */
	@Test
	public void testNumOccurrences()
	{
		Assertions.assertEquals(StringUtil.numSubstrings("a", "aa"), 0);
		Assertions.assertEquals(StringUtil.numSubstrings("aa", "aa"), 1);
		Assertions.assertEquals(StringUtil.numSubstrings("aaa", "aa"), 2);
		Assertions.assertEquals(StringUtil.numSubstrings("aa a", "aa"), 1);
		Assertions.assertEquals(StringUtil.numSubstrings("ab/>", "/>"), 1);
		Assertions.assertEquals(StringUtil.numSubstrings("aa a", ""), 0);
		Assertions.assertEquals(StringUtil.numSubstrings("aa a", null), 0);
		Assertions.assertEquals(StringUtil.numSubstrings(null, "a"), 0);
		Assertions.assertEquals(StringUtil.numSubstrings(null, null), 0);
	}

	/**
	 *
	 */
	@Test
	public void testRegExpPerformance()
	{
		final CPUTimer ct = new CPUTimer(false);
		int b = 0;
		for (int i = 0; i < 100000; i++)
		{
			ct.start();
			if (StringUtil.matches("abc" + i, "(.)?bc(1|2)?00(1)?"))
				b++;
			ct.stop();
		}
		System.out.print(ct.toString());
		Assertions.assertTrue(b > 2);
	}

	/**
	 *
	 */
	@Test
	public void testReplaceString()
	{
		Assertions.assertEquals(StringUtil.replaceString("abbcc", "a", "_"), "_bbcc");
		Assertions.assertEquals(StringUtil.replaceString("abbcc", "aa", "_"), "abbcc");
		Assertions.assertEquals(StringUtil.replaceString("abbcc", "b", "_"), "a__cc");
		Assertions.assertEquals(StringUtil.replaceString("abbcc", "bb", "_"), "a_cc");
		Assertions.assertEquals(StringUtil.replaceString("abbcc", "c", "_"), "abb__");
		Assertions.assertEquals(StringUtil.replaceString("abbcc", "cc", "_"), "abb_");
		Assertions.assertEquals(StringUtil.replaceString("abbcc", "bb", null), "acc");
		Assertions.assertEquals(StringUtil.replaceString("000000", "00", "0"), "0");
		Assertions.assertEquals(StringUtil.replaceString("000", "0", "00"), "000000");
	}

	/**
	 *
	 */
	@Test
	public void testStringReplacer()
	{
		StringReplacer sr = new StringReplacer("000000");
		Assertions.assertEquals(sr.replaceString("00", "0"), "0");
		sr = new StringReplacer("000000");
		sr.setReRead(false);
		Assertions.assertEquals(sr.replaceString("00", "0"), "000");
	}

	/**
	 *
	 */
	@Test
	public void testReplaceChar()
	{
		Assertions.assertEquals(StringUtil.replaceChar("abbcc", 'a', null, 0), "bbcc");
		Assertions.assertEquals(StringUtil.replaceChar("abbcc", 'b', null, 0), "acc");
		Assertions.assertEquals(StringUtil.replaceChar("abbcc", 'b', null, 2), "abcc");
		Assertions.assertEquals(StringUtil.replaceChar("abbcc", 'b', "_2", 0), "a_2_2cc");
		Assertions.assertEquals(StringUtil.replaceChar("abbcc", 'b', "_2", 2), "ab_2cc");
		Assertions.assertEquals(StringUtil.replaceChar("abbcc", 'b', "_2", 4), "abbcc");
		Assertions.assertEquals(StringUtil.replaceChar("abbcc", 'b', "_2", 5), "abbcc");
		Assertions.assertEquals(StringUtil.replaceChar("abbcc", 'b', "_2", 6), "abbcc");
	}

	/**
	 *
	 */
	@Test
	public void testReplaceCharSet()
	{
		Assertions.assertEquals(StringUtil.replaceCharSet("abbcc", "ab", null, 0), "cc");
		Assertions.assertEquals(StringUtil.replaceCharSet("abbcc", "ab", "_", 0), "___cc");
		Assertions.assertEquals(StringUtil.replaceCharSet("abbcc", "ab", "_", 2), "ab_cc");
	}

	/**
	 *
	 */
	@Test
	public void testRightString()
	{
		Assertions.assertEquals(StringUtil.rightStr("abbcc", 2), "cc");
		Assertions.assertEquals(StringUtil.rightStr("abbcc", -3), "cc");
		Assertions.assertNull(StringUtil.rightStr("abbcc", -5));
		Assertions.assertNull(StringUtil.rightStr(null, -5));
		Assertions.assertNull(StringUtil.rightStr("abc", -55));
	}

	/**
	 *
	 */
	@Test
	public void testLeftString()
	{
		Assertions.assertEquals(StringUtil.leftStr("abbcc", 2), "ab");
		Assertions.assertEquals(StringUtil.leftStr("abbcc", -2), "abb");
		Assertions.assertNull(StringUtil.leftStr("abbcc", -5));
		Assertions.assertNull(StringUtil.leftStr(null, -5));
		Assertions.assertNull(StringUtil.leftStr("abc", -55));
	}

	/**
	 *
	 */
	@Test
	public void testSubstring()
	{
		// final String s = "a".substring(0, 0);
		Assertions.assertEquals(StringUtil.substring("abbcc", 0, 2), "ab");
		Assertions.assertEquals(StringUtil.substring("abbcc", 0, -2), "abb");
		Assertions.assertEquals(StringUtil.substring("abbcc", -2, -2), "");
		Assertions.assertEquals(StringUtil.substring("abbcc", -3, 0), "bcc");
		Assertions.assertEquals(StringUtil.substring("abbcc", 2, -1), "bc");
		Assertions.assertNull(StringUtil.substring("abbcc", 0, -6));
		Assertions.assertNull(StringUtil.substring(null, 0, -5));
		Assertions.assertNull(StringUtil.substring("abc", 0, -55));
	}

	/**
	 *
	 */
	@Test
	public void testParseLong()
	{
		Assertions.assertEquals(StringUtil.parseLong("", 0L), 0L);
		Assertions.assertEquals(StringUtil.parseLong("1234567890123456", 0L), 1234567890123456L);
		Assertions.assertEquals(StringUtil.parseLong("INF", 0L), Long.MAX_VALUE);
		Assertions.assertEquals(StringUtil.parseLong("unbounded", 0L), Long.MAX_VALUE);
		Assertions.assertEquals(StringUtil.parseLong("-inf", 0L), Long.MIN_VALUE);
		Assertions.assertEquals(StringUtil.parseLong("12341234561234567834556", 0), Long.MAX_VALUE);
		Assertions.assertEquals(StringUtil.parseLong("-12341234561234567834556", 0), Long.MIN_VALUE);
		Assertions.assertEquals(StringUtil.parseLong("-1.0e44", 0), Long.MIN_VALUE);
		Assertions.assertEquals(StringUtil.parseLong("-0xa", 0), -10);
		Assertions.assertEquals(StringUtil.parseLong("0xff", 0), 255);
	}

	/**
	 *
	 */
	@Test
	public void testParseInt()
	{
		Assertions.assertEquals(StringUtil.parseInt("", 0), 0);
		Assertions.assertEquals(StringUtil.parseInt("1234123456", 0), 1234123456);
		Assertions.assertEquals(StringUtil.parseInt("+1234123456", 0), 1234123456);
		Assertions.assertEquals(StringUtil.parseInt("1234123456.0", 0), 1234123456);
		Assertions.assertEquals(StringUtil.parseInt("abc", 99), 99);
		Assertions.assertEquals(StringUtil.parseInt("12341234561234567834556", 0), Integer.MAX_VALUE);
		Assertions.assertEquals(StringUtil.parseInt("-12341234561234567834556", 0), Integer.MIN_VALUE);
		Assertions.assertEquals(StringUtil.parseInt("INF", 0), Integer.MAX_VALUE);
		Assertions.assertEquals(StringUtil.parseInt("-inf", 0), Integer.MIN_VALUE);
		Assertions.assertEquals(StringUtil.parseInt("0xf", 0), 15);
		Assertions.assertEquals(StringUtil.parseInt("-0xf", 0), -15);
	}

	/**
	 *
	 */
	@Test
	public void testParseBoolean()
	{
		Assertions.assertEquals(StringUtil.parseBoolean("", false), false);
		Assertions.assertEquals(StringUtil.parseBoolean("", true), true);
		Assertions.assertEquals(StringUtil.parseBoolean("TRUE ", false), true);
		Assertions.assertEquals(StringUtil.parseBoolean(" FalSe ", true), false);
		Assertions.assertEquals(StringUtil.parseBoolean("1 ", false), true);
		Assertions.assertEquals(StringUtil.parseBoolean(" 0 ", true), false);
	}

	/**
	 *
	 */
	@Test
	public void testParseDouble()
	{
		String s = "INF";
		Assertions.assertEquals(StringUtil.parseDouble(s, 0), Double.MAX_VALUE, 0.0);
		Assertions.assertTrue(StringUtil.isNumber(s));
		s = "-INF";
		Assertions.assertEquals(StringUtil.parseDouble(s, 0), -Double.MAX_VALUE, 0.0);
		Assertions.assertTrue(StringUtil.isNumber(s));
		s = "123.45e3 ";
		Assertions.assertEquals(StringUtil.parseDouble(s, 0), 123450., 0.);
		Assertions.assertTrue(StringUtil.isNumber(s));
		Assertions.assertEquals(StringUtil.parseDouble(s, 0), 123450., 0.);
		Assertions.assertTrue(StringUtil.isNumber(s));
		s = "123.45E3";
		Assertions.assertEquals(StringUtil.parseDouble(s, 0), 123450., 0.);
		Assertions.assertTrue(StringUtil.isNumber(s));
		s = "123.45";
		Assertions.assertEquals(StringUtil.parseDouble(s, 0), 123.450, 0.);
		Assertions.assertTrue(StringUtil.isNumber(s));
		s = "-123.45";
		Assertions.assertEquals(StringUtil.parseDouble(s, 0), -123.450, 0.);
		Assertions.assertTrue(StringUtil.isNumber(s));
		s = ".45";
		Assertions.assertEquals(StringUtil.parseDouble(s, 0), 0.450, 0., "missing leading zero ok");
		Assertions.assertTrue(StringUtil.isNumber(s));
		s = "-123.45a";
		Assertions.assertEquals(StringUtil.parseDouble(s, 0.), 0., 0.);
		Assertions.assertFalse(StringUtil.isNumber(s));
		s = "";
		Assertions.assertEquals(StringUtil.parseDouble(s, 0.), 0., 0.);
		Assertions.assertFalse(StringUtil.isNumber(s));
		s = "1,2";
		Assertions.assertEquals(StringUtil.parseDouble(s, 0.), 1.2, 0., "gracefully handle ',' as a separator char");
		Assertions.assertTrue(StringUtil.isNumber(s), "gracefully handle ',' as a separator char");
		s = null;
		Assertions.assertEquals(StringUtil.parseDouble(s, 0.), 0., 0.);
		Assertions.assertFalse(StringUtil.isNumber(s));
	}

	/**
	 *
	 */
	@Test
	public void testFind_last_not_of()
	{
		Assertions.assertEquals(StringUtil.find_last_not_of("abc", "bcd"), 0);
		Assertions.assertEquals(StringUtil.find_last_not_of("abc", "abc"), -1);
		Assertions.assertEquals(StringUtil.find_last_not_of("abc", "ac"), 1);
		Assertions.assertEquals(StringUtil.find_last_not_of("grün", "ï¿½ï¿½ï¿½"), 3);
		Assertions.assertEquals(StringUtil.find_last_not_of("abc", "_"), 2);
	}

	/**
	 *
	 */
	@Test
	public void testFormatLong()
	{
		long l = 13;
		while (l > 0) // breaks over top
		{
			l *= 7;
			Assertions.assertEquals(StringUtil.parseLong(StringUtil.formatLong(l), -1), l);
		}
	}

	/**
	*
	*/
	@Test
	public void testFormatDouble()
	{
		double d = 0.12345678901234;
		String s = StringUtil.formatDouble(d);
		Assertions.assertEquals("0.12345679", s, "s=6");
		d = 0.12345678;
		s = StringUtil.formatDouble(d);
		Assertions.assertEquals("0.12345678", s, "s=6");
		d = 0.123456789;
		s = StringUtil.formatDouble(d);
		Assertions.assertEquals("0.12345679", s, "s=6");
		d = 0.12345673;
		s = StringUtil.formatDouble(d);
		Assertions.assertEquals("0.12345673", s, "s=5");
		d = 234.0;
		s = StringUtil.formatDouble(d);
		Assertions.assertEquals("234", s, "s=int");
		d = 123.456e4;
		s = StringUtil.formatDouble(d);
		Assertions.assertEquals("1234560", s, "s=int");
		Assertions.assertEquals("0", StringUtil.formatDouble(0.1e-20), "s=real small");
		Assertions.assertEquals("0", StringUtil.formatDouble(-0.1e-20), "s=real small -");
		Assertions.assertEquals("1", StringUtil.formatDouble(1.000000001), "s=1+epsilon");
		Assertions.assertEquals("1", StringUtil.formatDouble(0.99999999987), "s=1-epsilon");
		Assertions.assertNotSame("1", StringUtil.formatDouble(0.99949999987), "s=1-epsilon");
	}

	// /////////////////////////////////////////////////////////////////////////

	/**
	 *
	 */
	@Test
	public void testuncToUrl()
	{
		if (PlatformUtil.isWindows())
		{
			final String unc = "\\\\myHost\\a\\.\\b\\..\\c äöü%.txt";
			final String iri = "file://myHost/a/c%20äöü%25.txt";
			final String uri = "file://myHost/a/c%20%c3%a4%c3%b6%c3%bc%25.txt";

			Assertions.assertEquals(StringUtil.uncToUrl(unc, true), uri, "uri ok");
			Assertions.assertEquals(StringUtil.uncToUrl(unc, false), iri, "iri ok");
		}
	}

	// /////////////////////////////////////////////////////////////////////////

	/**
	 *
	 */
	@Test
	public void testZappTokenWS()
	{
		Assertions.assertEquals("1 2 3~4", StringUtil.zappTokenWS(" 1 2 3~    4 ", JDFConstants.TILDE), "new string");
		Assertions.assertEquals("1 2 3~4", StringUtil.zappTokenWS(" 1 2 3~    4", JDFConstants.TILDE), "new string");
		Assertions.assertEquals("1 2 3~4", StringUtil.zappTokenWS(" 1 2 3~    4~", JDFConstants.TILDE), "new string");
		Assertions.assertEquals(StringUtil.zappTokenWS(" n2 ", null), "n2");
	}

	/**
	 *
	 */
	@Test
	public void testHasTokenNeg()
	{
		Assertions.assertTrue(StringUtil.hasToken("1", "1", null, -1));
		Assertions.assertTrue(StringUtil.hasToken("1 2 1", "1", null, -1));
		Assertions.assertTrue(StringUtil.hasToken("1 2 1", "2", null, -1));
		Assertions.assertFalse(StringUtil.hasToken("1 2 1", "2", null, -2));
		Assertions.assertFalse(StringUtil.hasToken("1 2 1", "1", null, -3));
	}

	/**
	 *
	 */
	@Test
	public void testHasToken()
	{
		Assertions.assertFalse(StringUtil.hasToken("1", "1", null, 1));
		final String s = "1 2 3 3 \n15\n4";
		Assertions.assertFalse(StringUtil.hasToken(s, "0", " \n", 0));
		Assertions.assertTrue(StringUtil.hasToken(s, "1", " ", 0));
		Assertions.assertFalse(StringUtil.hasToken("", "1", null, 0));
		Assertions.assertTrue(StringUtil.hasToken(s, "1", null, 0));
		Assertions.assertFalse(StringUtil.hasToken(s, "5", " ", 0));
		Assertions.assertFalse(StringUtil.hasToken(s, "15", " ", 0));
		Assertions.assertTrue(StringUtil.hasToken(s, "2", " ", 0));
		Assertions.assertTrue(StringUtil.hasToken(s, "4", "\n ", 0));
		Assertions.assertTrue(StringUtil.hasToken(s, "3", " ", 0));
		Assertions.assertTrue(StringUtil.hasToken(s, "3", " ", 1));
		Assertions.assertFalse(StringUtil.hasToken(s, "3", " ", 2));
		Assertions.assertFalse(StringUtil.hasToken(s, "3", " ", 99));
		Assertions.assertFalse(StringUtil.hasToken(null, "0", " ", 0));
		Assertions.assertFalse(StringUtil.hasToken("ab", "a", " ", 0));
		Assertions.assertFalse(StringUtil.hasToken("ab", "b", " ", 0));
		Assertions.assertFalse(StringUtil.hasToken("abab", "ab", " ", 0));
		Assertions.assertFalse(StringUtil.hasToken("ababa", "ab", " ", 0));
		Assertions.assertTrue(StringUtil.hasToken("abc ab", "ab", " ", 0));
		Assertions.assertTrue(StringUtil.hasToken("abc ab ", "ab", " ", 0));
		Assertions.assertTrue(StringUtil.hasToken("abc ab abd", "ab", " ", 0));
		Assertions.assertTrue(StringUtil.hasToken("a", "a", " ", 0), "string matches are always true for 0");
		Assertions.assertTrue(StringUtil.hasToken("a", "a", ", ", -1), "string matches are always true for -1");

	}

	/**
	 *
	 */
	@Test
	public void testHasTokenPerformance()
	{
		final String s = "1 2 33 3 \n15\n4";
		final String s2 = "ab123456 ab1234567 ab 1234568";
		final long t0 = System.currentTimeMillis();
		Assertions.assertTrue(StringUtil.hasToken(s, "33", " ", 0));
		Assertions.assertFalse(StringUtil.hasToken(s, "34", " ", 0));
		Assertions.assertFalse(StringUtil.hasToken(s2, "ab12345679", " ", 0));
		Assertions.assertTrue(StringUtil.hasToken(s2, "ab1234567", " ", 0));
		Assertions.assertTrue(StringUtil.hasToken(s2, "ab", " ", 0));
		for (int i = 0; i < 1000000; i++)
		{
			StringUtil.hasToken(s, "33", " ", 0);
			StringUtil.hasToken(s, "34", " ", 0);
			StringUtil.hasToken(s2, "ab12345679", " ", 0);
			StringUtil.hasToken(s2, "ab1234567", " ", 0);
			StringUtil.hasToken(s2, "ab", " ", 0);
		}
		final long t2 = System.currentTimeMillis();
		log.info("" + (t2 - t0));
	}

	/**
	 *
	 */
	@Test
	public void testToken()
	{
		String s = "1 2 3 4";
		Assertions.assertEquals(StringUtil.token(s, 0, " "), "1");
		Assertions.assertEquals(StringUtil.token(s, 1, " "), "2");
		Assertions.assertEquals(StringUtil.token(s, -3, " "), "2");
		Assertions.assertEquals(StringUtil.token(s, -1, " "), "4");
		Assertions.assertNull(StringUtil.token(s, 4, " "));
		Assertions.assertNull(StringUtil.token(s, -5, " "));
		Assertions.assertNull(StringUtil.token(null, 2, " "));
		s = "a/b?c";
		Assertions.assertEquals(StringUtil.token(s, 0, "?/"), "a");
		Assertions.assertEquals(StringUtil.token(s, 1, "?/"), "b");
		Assertions.assertEquals(StringUtil.token(s, 2, "?/"), "c");
		Assertions.assertNull(StringUtil.token("", 0, null));
	}

	// /////////////////////////////////////////////////////////////////////////

	/**
	 *
	 */
	@Test
	public void testTokenize()
	{
		final String s = " 1 2\n3 \n4   5";
		final VString v = new VString();
		v.add("1");
		v.add("2");
		v.add("3");
		v.add("4");
		v.add("5");
		Assertions.assertEquals(StringUtil.tokenize(s, " \n", false), v);
		final String descName = "foobar( 1,2,3  ,4,5 )";
		final int pos2 = descName.lastIndexOf(')');
		final int pos1 = descName.lastIndexOf('(') + 1;
		Assertions.assertEquals(v, StringUtil.tokenize(descName.substring(pos1, pos2), ", ", false));
	}

	/**
	 *
	 */
	@Test
	public void testReplaceTokenString()
	{
		final String s = "a/b/c";
		Assertions.assertEquals(StringUtil.replaceToken(s, "a", "/", "A"), "A/b/c");
		Assertions.assertEquals(StringUtil.replaceToken(s, 0, "/", null), "b/c");
		Assertions.assertEquals(StringUtil.replaceToken(s, "c", "/", null), "a/b");
		Assertions.assertEquals(StringUtil.replaceToken(s, "d", "/", null), s);
	}

	/**
	 *
	 */
	@Test
	public void testRemoveTokenString()
	{
		final String s = "a/b/c";
		Assertions.assertEquals(StringUtil.removeToken(s, "a", "/"), "b/c");
		Assertions.assertEquals(StringUtil.removeToken(s, "b", "/"), "a/c");
		Assertions.assertEquals(StringUtil.removeToken(s, "c", "/"), "a/b");
		Assertions.assertEquals(StringUtil.removeToken("aa/bb/cc", "c", "/"), "aa/bb/cc");
	}

	/**
	 *
	 */
	@Test
	public void testReplaceToken()
	{
		final String s = "a/b/c";
		Assertions.assertEquals(StringUtil.replaceToken(s, 0, "/", "A"), "A/b/c");
		Assertions.assertEquals(StringUtil.replaceToken(s, 0, "/", null), "b/c");
		Assertions.assertEquals(StringUtil.replaceToken(s, -1, "/", null), "a/b");
		Assertions.assertEquals(StringUtil.replaceToken(s, -1, "/", ""), "a/b/");
		Assertions.assertEquals(StringUtil.replaceToken(s, -1, "/", "A"), "a/b/A");
		Assertions.assertEquals(StringUtil.replaceToken(s, 1, "/", "A"), "a/A/c");
		Assertions.assertEquals(StringUtil.replaceToken(s, 1, "/", null), "a/c");
		Assertions.assertEquals(StringUtil.replaceToken(s, -5, "/", "A"), "a/b/c");
		final String s2 = "//a/b/c";
		Assertions.assertEquals(StringUtil.replaceToken(s2, 0, "/", "A"), "//A/b/c");
		Assertions.assertEquals(StringUtil.replaceToken(s2, 2, "/", null), "//a/b");
		final String s3 = "a";
		Assertions.assertEquals(StringUtil.replaceToken(s3, 0, "/", "A"), "A");
		Assertions.assertNull(StringUtil.replaceToken(s3, 0, "/", null));
		final String s4 = "a_b";
		Assertions.assertEquals(StringUtil.replaceToken(s4, 0, "_", "c"), "c_b");
		Assertions.assertEquals(StringUtil.replaceToken(s4, 0, "_", null), "b");
	}

	/**
	 *
	 */
	@Test
	public void testRemoveToken()
	{
		Assertions.assertEquals(StringUtil.removeToken("a/b/c", 0, "/"), "b/c");
		Assertions.assertEquals(StringUtil.removeToken("a/b/c", -1, "/"), "a/b");
		Assertions.assertEquals(StringUtil.removeToken("a/b/c", -2, "/"), "a/c");
		Assertions.assertNull(StringUtil.removeToken("a/", -1, "/"));
		Assertions.assertEquals(StringUtil.removeToken("/a/", -1, "/"), "/");
		Assertions.assertNull(StringUtil.removeToken("a", -1, "/"));
		Assertions.assertNull(StringUtil.removeToken("/a", -1, "/"));
	}

	/**
	 *
	 */
	@Test
	public void testAddToken()
	{
		Assertions.assertNull(StringUtil.addToken(null, "/", null));
		Assertions.assertEquals(StringUtil.addToken("a", "/", "b"), "a/b");
		Assertions.assertEquals(StringUtil.addToken("a/", "/", "b"), "a/b");
		Assertions.assertEquals(StringUtil.addToken("a", "/", "/b"), "a/b");
		Assertions.assertEquals(StringUtil.addToken("a/", "/", "/b"), "a/b");
		Assertions.assertEquals(StringUtil.addToken("a/", "/", "//b"), "a/b");
		Assertions.assertEquals(StringUtil.addToken("//a/", "/", "//b"), "//a/b");
		Assertions.assertEquals(StringUtil.addToken("/", "/", "b"), "/b");
	}

	/**
	 *
	 */
	@Test
	public void testTokenizeDelim()
	{
		final String s = "http://aa/b?c";
		final VString v = new VString();
		v.add("http:");
		v.add("/");
		v.add("/");
		v.add("aa");
		v.add("/");
		v.add("b");
		v.add("?");
		v.add("c");
		Assertions.assertEquals(StringUtil.tokenize(s, "/?", true), v);
	}

	/**
	 *
	 */
	@Test
	public void testTokenizeBrackets()
	{
		Assertions.assertEquals(StringUtil.tokenizeBrackets("?", '?', ':'), new VString(new String[] { "", "" }));
		Assertions.assertEquals(StringUtil.tokenizeBrackets("a", '?', ':'), new VString(new String[] { "a" }));
		Assertions.assertEquals(StringUtil.tokenizeBrackets("a:", '?', ':'), new VString(new String[] { "a", "", "" }));
		Assertions.assertEquals(StringUtil.tokenizeBrackets("a:b", '?', ':'), new VString(new String[] { "a", "", "b" }));
		Assertions.assertEquals(StringUtil.tokenizeBrackets("a:b?c:d", '?', ':'), new VString(new String[] { "a", "", "b?c:d" }));
		Assertions.assertEquals(StringUtil.tokenizeBrackets("?:", '?', ':'), new VString(new String[] { "", "", "" }));
		Assertions.assertEquals(StringUtil.tokenizeBrackets("a?:", '?', ':'), new VString(new String[] { "a", "", "" }));
		Assertions.assertEquals(StringUtil.tokenizeBrackets("a?b:", '?', ':'), new VString(new String[] { "a", "b", "" }));
		Assertions.assertEquals(StringUtil.tokenizeBrackets("a?b:c", '?', ':'), new VString(new String[] { "a", "b", "c" }));
		Assertions.assertEquals(StringUtil.tokenizeBrackets("a?b?c:d:e", '?', ':'), new VString(new String[] { "a", "b?c:d", "e" }));
		Assertions.assertEquals(StringUtil.tokenizeBrackets("a?b:c?d:e", '?', ':'), new VString(new String[] { "a", "b", "c?d:e" }));
		Assertions.assertEquals(StringUtil.tokenizeBrackets("a?b:c?d:e?f:g", '?', ':'), new VString(new String[] { "a", "b", "c?d:e?f:g" }));
	}

	/**
	 *
	 */
	@Test
	public void testConcatStrings()
	{
		final VString v = StringUtil.tokenize("a b c", " ", false);
		StringUtil.concatStrings(v, "_foo");
		Assertions.assertEquals("a_foo b_foo c_foo", StringUtil.setvString(v, " ", null, null));
	}

	/**
	 *
	 */
	@Test
	public void testConcatString()
	{
		Assertions.assertEquals("acb", StringUtil.concat("a", "b", "c"));
		Assertions.assertEquals("a", StringUtil.concat("a", null, "c"));
		Assertions.assertEquals("a", StringUtil.concat(null, "a", null));
		Assertions.assertNull(StringUtil.concat(null, "", null));
		Assertions.assertNull(StringUtil.concat("", "", null));
	}

	/**
	 *
	 */
	@Test
	public void testCreateString()
	{
		final ByteArrayIOStream ios = new ByteArrayIOStream("abc".getBytes());
		Assertions.assertEquals(StringUtil.createString(ios.getInputStream()), "abc");
		ios.close();
	}

	/**
	 *
	 */
	@Test
	public void testEndsWithIgnoreCase()
	{
		final String s = "a.ZIP";
		Assertions.assertTrue(s.toLowerCase().endsWith(".zip"));
		Assertions.assertEquals(s, "a.ZIP");
	}

	// /////////////////////////////////////////////////////////////////////////

	/**
	 *
	 */
	@Test
	public void testEquals()
	{
		Assertions.assertTrue(StringUtil.equals("", null));
		Assertions.assertTrue(StringUtil.equals(null, null));
		Assertions.assertTrue(StringUtil.equals("", ""));
		Assertions.assertFalse(StringUtil.equals("a", ""));
		Assertions.assertFalse(StringUtil.equals("a", null));
	}

	/**
	 *
	 */
	@Test
	public void testEqualsPrecision()
	{
		Assertions.assertTrue(StringUtil.equals("", null, 0.001));
		Assertions.assertTrue(StringUtil.equals(null, null, 0.001));
		Assertions.assertTrue(StringUtil.equals("1", "1.0", 0));
		Assertions.assertTrue(StringUtil.equals("1", "1.0001", 0.001));
		Assertions.assertTrue(StringUtil.equals("1 2", "1.0001 2.0002", 0.001));
		Assertions.assertTrue(StringUtil.equals("true", "TRUE", 0));
		JDFDate jdfDate = new JDFDate();
		Assertions.assertTrue(StringUtil.equals(jdfDate.getDateTimeISO(), jdfDate.addOffset(1, 0, 0, 0).getDateTimeISO(), 1234));
	}

	/**
	 *
	 */
	@Test
	public void testisInteger()
	{
		Assertions.assertFalse(StringUtil.isInteger(""));
		Assertions.assertFalse(StringUtil.isInteger("a"));
		Assertions.assertFalse(StringUtil.isInteger("1.0"));
		Assertions.assertTrue(StringUtil.isInteger("123"));
		Assertions.assertTrue(StringUtil.isInteger("-123"));
		Assertions.assertTrue(StringUtil.isInteger("+123"));
	}

	/**
	 *
	 */
	@Test
	public void testisNumber()
	{
		Assertions.assertFalse(StringUtil.isNumber(""));
		Assertions.assertFalse(StringUtil.isNumber(" a"));
		Assertions.assertFalse(StringUtil.isNumber("a"));
		Assertions.assertTrue(StringUtil.isNumber("1.0"));
		Assertions.assertTrue(StringUtil.isNumber("1.0"));
		Assertions.assertTrue(StringUtil.isNumber("123"));
		Assertions.assertTrue(StringUtil.isNumber("-123"));
		Assertions.assertTrue(StringUtil.isNumber("+123"));
		Assertions.assertTrue(StringUtil.isNumber(".123"));
	}

	/**
	 *
	 */
	@Test
	public void testisNumberSpeed()
	{
		final long t0 = System.currentTimeMillis();
		for (int i = 0; i < 1000000; i++)
		{
			Assertions.assertFalse(StringUtil.isNumber(""));
			Assertions.assertFalse(StringUtil.isNumber(" a"));
			Assertions.assertFalse(StringUtil.isNumber("a"));
			Assertions.assertTrue(StringUtil.isNumber("1.0"));
			Assertions.assertTrue(StringUtil.isNumber("1.0"));
			Assertions.assertTrue(StringUtil.isNumber("123"));
			Assertions.assertTrue(StringUtil.isNumber("-123"));
			Assertions.assertTrue(StringUtil.isNumber("+123"));
		}
		log.info(System.currentTimeMillis() - t0);
	}

	/**
	 *
	 */
	@Test
	public void testisEqual()
	{
		final double d = 1.3141516171819;
		double d2 = 0.00000000000001;
		double d3 = -0.000000000000011;

		while (d2 < 9999999999.9999)
		{
			d2 *= d;
			d3 *= d;
			Assertions.assertTrue(StringUtil.isEqual(d2, StringUtil.parseDouble(StringUtil.formatDouble(d2), 0.)), "" + d2);
			Assertions.assertTrue(StringUtil.isEqual(d3, StringUtil.parseDouble(StringUtil.formatDouble(d3), 0.)), "" + d3);
			Assertions.assertFalse(StringUtil.isEqual(d2, d2 * (1 + 1.1 * JDFBaseDataTypes.EPSILON) + JDFBaseDataTypes.EPSILON), "" + d2);
			Assertions.assertFalse(StringUtil.isEqual(d3, d3 * (1 + 1.1 * JDFBaseDataTypes.EPSILON) - JDFBaseDataTypes.EPSILON), "" + d3);
		}
		Assertions.assertTrue(StringUtil.isEqual(0.000000001, -0.000000001), "0.000001");
		Assertions.assertTrue(StringUtil.isEqual(4, 4), "int");
	}

	// /////////////////////////////////////////////////////////////////////////

	/**
	 *
	 */
	@Test
	public void testCompareTo()
	{
		Assertions.assertEquals(-1, StringUtil.compareTo(-3, -2));
		Assertions.assertEquals(1, StringUtil.compareTo(3, 2));
		Assertions.assertEquals(1, StringUtil.compareTo(3, 2));
		Assertions.assertEquals(1, StringUtil.compareTo(3, 2));
		Assertions.assertEquals(0, StringUtil.compareTo(2 + 0.5 * JDFBaseDataTypes.EPSILON, 2));
	}

	/**
	 *
	 */
	@Test
	public void testIsNMTOKEN()
	{
		Assertions.assertTrue(StringUtil.isNMTOKEN("abc"));
		Assertions.assertTrue(StringUtil.isNMTOKEN("ä"));
		Assertions.assertFalse(StringUtil.isNMTOKEN("$"));
		Assertions.assertTrue(StringUtil.isNMTOKEN("öpü"));
		Assertions.assertTrue(StringUtil.isNMTOKEN("1öpü2.:-_"));
		Assertions.assertFalse(StringUtil.isNMTOKEN(" abc"));
		Assertions.assertFalse(StringUtil.isNMTOKEN("a bc"));
		Assertions.assertFalse(StringUtil.isNMTOKEN("a\nbc"));
		Assertions.assertFalse(StringUtil.isNMTOKEN("\tabc"));
		Assertions.assertFalse(StringUtil.isNMTOKEN("abc "));
	}

	/**
	 *
	 */
	@Test
	public void testIsBoolean()
	{
		Assertions.assertTrue(StringUtil.isBoolean("1 "));
		Assertions.assertFalse(StringUtil.isBoolean("abc "));
	}

	/**
	 *
	 */
	@Test
	public void testIsID()
	{
		Assertions.assertTrue(StringUtil.isID("abc"));
		Assertions.assertFalse(StringUtil.isID("1abc"));
	}

	/**
	 *
	 */
	@Test
	public void testIsDate()
	{
		Assertions.assertTrue(StringUtil.isDate(new JDFDate().getDateTimeISO()));
		Assertions.assertTrue(StringUtil.isDate(new JDFDate().getDateISO()));
		Assertions.assertFalse(StringUtil.isDate("1abc"));
	}

	/**
	 *
	 */
	@Test
	public void testList()
	{
		Assertions.assertTrue(StringUtil.isNumberList("1"));
		Assertions.assertTrue(StringUtil.isNumberList("1.04 6.777"));
		Assertions.assertFalse(StringUtil.isNumberList("a"));
	}

	/**
	 *
	 */
	@Test
	public void testgetDatatype()
	{
		Assertions.assertEquals(EDataType.integer, StringUtil.getDataType("-1"));
		Assertions.assertEquals(EDataType.integer, StringUtil.getDataType("1234567890"));
		Assertions.assertEquals(EDataType.bool, StringUtil.getDataType(" false "));
		Assertions.assertEquals(EDataType.date, StringUtil.getDataType(new JDFDate().getDateTimeISO()));
		Assertions.assertEquals(EDataType.number, StringUtil.getDataType(" 1e15 "));
		Assertions.assertEquals(EDataType.number, StringUtil.getDataType(" 1.234 "));
		Assertions.assertEquals(EDataType.numberlist, StringUtil.getDataType(" 1 1.234 "));
		Assertions.assertEquals(EDataType.string, StringUtil.getDataType(" 1a1.234 "));
		Assertions.assertNull(StringUtil.getDataType(null));
	}

	/**
	 *
	 */
	@Test
	public void testgetCharset()
	{
		Assertions.assertEquals(StandardCharsets.UTF_8, StringUtil.getCharset(StringUtil.UTF8));
		Assertions.assertEquals(StandardCharsets.ISO_8859_1, StringUtil.getCharset("8859_1"));
		Assertions.assertNull(StringUtil.getCharset("snafu"));
	}

	/**
	 *
	 */
	@Test
	public void testisWindowsLocalPath()
	{
		Assertions.assertTrue(UrlUtil.isWindowsLocalPath("c:\\foo"));
		Assertions.assertTrue(UrlUtil.isWindowsLocalPath("c:\\foo\\bar.abc"));
		Assertions.assertTrue(UrlUtil.isWindowsLocalPath("d:foo"));
		Assertions.assertFalse(UrlUtil.isWindowsLocalPath("\\\\foo\\bar"));
		Assertions.assertFalse(UrlUtil.isWindowsLocalPath("c/d/e.f"));
		Assertions.assertFalse(UrlUtil.isWindowsLocalPath("/c/d/e.f"));
	}

	// /////////////////////////////////////////////////////////////////////////

	/**
	 *
	 */
	@Test
	public void testPathToName()
	{
		Assertions.assertEquals(StringUtil.pathToName("\\\\foo\\bar"), "bar");
		Assertions.assertEquals(StringUtil.pathToName("c:\\foo\\bar.txt"), "bar.txt");
		Assertions.assertEquals(StringUtil.pathToName("c/foo/bar.txt"), "bar.txt");
		Assertions.assertEquals(StringUtil.pathToName("bar.txt"), "bar.txt");
	}

	// /////////////////////////////////////////////////////////////////////////

	/**
	 *
	 */
	@Test
	public void testGetNamesVector()
	{
		VString v = EnumUtil.getNamesVector(EnumType.AbortQueueEntry.getClass());
		Assertions.assertTrue(v.contains("Resource"));
		v = EnumUtil.getNamesVector(EnumOrientation.Flip0.getClass());
		Assertions.assertTrue(v.contains("Rotate90"));
	}

	// /////////////////////////////////////////////////////////////////////////

	/**
	 *
	 */
	@Test
	public void testGetEnumsVector()
	{
		final Vector<ValuedEnum> v = EnumUtil.getEnumsVector(EnumOrientation.Flip180.getClass());
		Assertions.assertTrue(v.contains(EnumOrientation.Rotate180));
	}

	// /////////////////////////////////////////////////////////////////////////
}
