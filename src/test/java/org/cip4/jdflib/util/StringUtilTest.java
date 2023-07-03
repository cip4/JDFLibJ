/*
 * The CIP4 Software License, Version 1.0
 *
 *
 * Copyright (c) 2001-2023 The International Cooperation for the Integration of Processes in Prepress, Press and Postpress (CIP4). All rights reserved.
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

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNotSame;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;
import static org.mockito.ArgumentMatchers.any;

import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.Vector;

import org.apache.commons.lang.enums.ValuedEnum;
import org.cip4.jdflib.JDFTestCaseBase;
import org.cip4.jdflib.core.JDFConstants;
import org.cip4.jdflib.core.JDFElement.EnumOrientation;
import org.cip4.jdflib.core.JDFResourceLink.EnumUsage;
import org.cip4.jdflib.core.KElement;
import org.cip4.jdflib.core.StringArray;
import org.cip4.jdflib.core.VString;
import org.cip4.jdflib.datatypes.JDFBaseDataTypes;
import org.cip4.jdflib.ifaces.IStreamWriter;
import org.cip4.jdflib.jmf.JDFMessage.EnumType;
import org.cip4.jdflib.util.StringUtil.EDataType;
import org.cip4.jdflib.util.StringUtil.StringReplacer;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

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
		assertEquals("(.)(.+)", StringUtil.simpleRegExptoRegExp("?+", false));
		assertEquals(StringUtil.simpleRegExptoRegExp("??", false), "(.)(.)");
		assertEquals(StringUtil.simpleRegExptoRegExp("(a?)", false), "(a?)");
		assertEquals(StringUtil.simpleRegExptoRegExp("*b??", false), "(.*)b(.)(.)");
		assertEquals(StringUtil.simpleRegExptoRegExp("ab", false), "ab");
		assertEquals(StringUtil.simpleRegExptoRegExp("a.b", false), "a\\.b");
		assertEquals(StringUtil.simpleRegExptoRegExp("a(.+)b", false), "a(.+)b");
		assertEquals(StringUtil.simpleRegExptoRegExp("*.b", false), "(.*)\\.b");
		assertEquals(StringUtil.simpleRegExptoRegExp("(.*)\\.b", false), "(.*)\\.b", "don't reconvert real regexp");
		assertTrue(StringUtil.matches("foo.txt", StringUtil.simpleRegExptoRegExp("*.tx*", false)));
		assertTrue(StringUtil.matches(".tx", StringUtil.simpleRegExptoRegExp("*.tx*", false)));
		assertTrue(StringUtil.matches("55", StringUtil.simpleRegExptoRegExp("55|56", false)));
		assertTrue(StringUtil.matches("56", StringUtil.simpleRegExptoRegExp("55|56", false)));
		assertFalse(StringUtil.matches("57", StringUtil.simpleRegExptoRegExp("55|56", false)));
		assertFalse(StringUtil.matches("foo_txt", StringUtil.simpleRegExptoRegExp("*.tx*", false)));
		assertTrue(StringUtil.matches("aa", StringUtil.simpleRegExptoRegExp("??", false)));
		assertTrue(StringUtil.matches("abc", StringUtil.simpleRegExptoRegExp("*b?", false)));
		assertTrue(StringUtil.matches("abc", StringUtil.simpleRegExptoRegExp("ab(.)", false)));
		assertFalse(StringUtil.matches("ab", StringUtil.simpleRegExptoRegExp("ab(.)", false)));
		assertFalse(StringUtil.matches("abcd", StringUtil.simpleRegExptoRegExp("ab(.)", false)));
		assertFalse(StringUtil.matches("abc", StringUtil.simpleRegExptoRegExp("*b??", false)));
		assertFalse(StringUtil.matches("abcd", StringUtil.simpleRegExptoRegExp("*b?", false)));
		assertTrue(StringUtil.matches("axbc", StringUtil.simpleRegExptoRegExp("axb?", false)));

		assertEquals(StringUtil.simpleRegExptoRegExp("(a)", true), "\\(a\\)");

	}

	/**
	 *
	 */
	@Test
	public void testWipeInvalidXML10Chars()
	{
		final char[] cs = new char[] { 'a', 0x7, 0x3, 'b', 0x5 };
		assertEquals(StringUtil.wipeInvalidXML10Chars(new String(cs), null), "ab");
		assertEquals(StringUtil.wipeInvalidXML10Chars(new String(cs), "_"), "a__b_");
		assertEquals(StringUtil.wipeInvalidXML10Chars("abc", null), "abc");
	}

	/**
	 *
	 */
	@Test
	public void testGetRelativePath()
	{
		File f = new File("./a");
		assertEquals(StringUtil.replaceChar(UrlUtil.getRelativePath(f, null), '\\', "/", 0), "a");
		f = new File("../a.b");
		assertEquals(StringUtil.replaceChar(UrlUtil.getRelativePath(f, null), '\\', "/", 0), "../a.b");
		f = new File("./../a b/b");
		assertEquals(StringUtil.replaceChar(UrlUtil.getRelativePath(f, null), '\\', "/", 0), "../a b/b");
		f = new File("a/b/c");
		assertEquals(StringUtil.replaceChar(UrlUtil.getRelativePath(f, null), '\\', "/", 0), "a/b/c");
		f = new File("a/b/c");
		assertEquals(StringUtil.replaceChar(UrlUtil.getRelativePath(f, f), '\\', "/", 0), ".");
	}

	/**
	 * test for getDefaultNull
	 */
	@Test
	public void testGetDefaultNull()
	{
		assertNull(StringUtil.getDefaultNull(null, null));
		assertNull(StringUtil.getDefaultNull("", ""));
		assertNull(StringUtil.getDefaultNull(null, ""));
		assertEquals("a", StringUtil.getDefaultNull("a", ""));
		assertEquals("a", StringUtil.getDefaultNull("a", null));
	}

	/**
	 * test for getDefaultNull
	 */
	@Test
	public void testGetDistance()
	{
		assertEquals(StringUtil.getDistance("", null, false, false, false), 0);
		assertEquals(StringUtil.getDistance("", "A", false, true, true), 0);
		assertEquals(StringUtil.getDistance("B", "A", false, true, true), 8);
		assertEquals(StringUtil.getDistance("AA", "A", false, true, true), 8);
		assertEquals(StringUtil.getDistance("BB", "BA", false, true, true), 4);
		assertEquals(StringUtil.getDistance("BBB", "BBA", false, true, true), 2);
		assertEquals(StringUtil.getDistance("BBBB", "BBBA", false, true, true), 1);
		assertEquals(StringUtil.getDistance(null, "A", false, true, true), 0);
		assertEquals(StringUtil.getDistance("a", null, false, true, true), 0);
		assertEquals(StringUtil.getDistance("a", "", false, true, true), 0);
		assertEquals(StringUtil.getDistance("a", "A", false, true, false), 0);
		assertEquals(StringUtil.getDistance("a", "AA", false, true, false), 8);
		assertEquals(StringUtil.getDistance("a  b", "a B", true, true, false), 0);
		assertEquals(StringUtil.getDistance("hallo", "hllo", true, true, false), 1);
		assertEquals(StringUtil.getDistance("hasso", "haßo", true, true, false), 2);
	}

	/**
	 * test for getDefaultNull
	 */
	@Test
	public void testGetDistanceSlide()
	{
		assertEquals(0, StringUtil.getDistance("", null, false, false, false, true));
		assertEquals(0, StringUtil.getDistance("AA", "A", false, true, true, true));
		assertEquals(4, StringUtil.getDistance("AAABCS", "AC", false, true, true, true));
		assertEquals(0, StringUtil.getDistance("AAABCS99999", "ABC", false, true, true, true));
		assertEquals(0, StringUtil.getDistance("abc", "AAABCS99999", false, true, true, true));
	}

	/**
	 * test for getNonEmpty
	 */
	@Test
	public void testGetNonEmpty()
	{
		assertNull(StringUtil.getNonEmpty(""));
		assertNull(StringUtil.getNonEmpty(null));
		assertEquals("a", StringUtil.getNonEmpty("a"));
	}

	/**
	 * test for getNonEmpty
	 */
	@Test
	public void testIsEmpty()
	{
		assertTrue(StringUtil.isEmpty(""));
		assertTrue(StringUtil.isEmpty((String) null));
		assertFalse(StringUtil.isEmpty("a"));
	}

	/**
	 *
	 */
	@Test
	public void testIsEmptyList()
	{
		assertTrue(StringUtil.isEmpty((StringArray) null));
		final StringArray v = new StringArray();
		assertTrue(StringUtil.isEmpty(v));
		v.appendUnique("");
		assertTrue(StringUtil.isEmpty(v));
		v.set(0, "b");
		assertFalse(StringUtil.isEmpty(v));
	}

	/**
	 * test for getNonEmpty
	 */
	@Test
	public void testHasContent()
	{
		assertFalse(StringUtil.hasContent(" "));
		assertFalse(StringUtil.hasContent(null));
		assertFalse(StringUtil.hasContent("\t\n "));
		assertTrue(StringUtil.hasContent(" a"));
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
			assertTrue(s.length() > 1);
		}
		assertTrue(v.size() < 900, "should be many different");
		assertTrue(v.size() > 5);
	}

	/**
	 *
	 */
	@Test
	public void testSprintfString()
	{
		assertEquals(StringUtil.sprintf("part_%04i.txt", "" + 6), "part_0006.txt");
		assertEquals(StringUtil.sprintf("abc%03idef", "5"), "abc005def");
		assertEquals(StringUtil.sprintf("abc%03idef", "5.0"), "abc005def");
		assertEquals(StringUtil.sprintf("abc%03i%02idef", "5.0,5"), "abc00505def");
		assertEquals(StringUtil.sprintf("abc%03i%02idef%%%s", "5.0,5,abcdefghi"), "abc00505def%abcdefghi");
		assertEquals(StringUtil.sprintf("%2x", "12"), " c");
		assertEquals(StringUtil.sprintf("%2x", "18"), "12");
		assertEquals(StringUtil.sprintf("%s", "\\,"), ",");
		assertEquals(StringUtil.sprintf("%s", "\\\\,"), "\\,");
		assertEquals(StringUtil.sprintf("%s_%s", "\\\\,,a"), "\\,_a");

	}

	/**
	 *
	 */
	@Test
	public void testSprintf()
	{
		Object[] o = new Object[1];
		o[0] = new Integer(5);
		assertEquals(StringUtil.sprintf("abc%03idef", o), "abc005def");
		o[0] = "foobar";
		assertEquals(StringUtil.sprintf("abc%7sdef", o), "abc foobardef");
		assertEquals(StringUtil.sprintf("abc%7s7def", o), "abc foobar7def");
		assertEquals(StringUtil.sprintf("%7sdef", o), " foobardef");
		assertEquals(StringUtil.sprintf("%7s", o), " foobar");
		assertEquals(StringUtil.sprintf("%%_%7s", o), "%_ foobar");
		assertEquals(StringUtil.sprintf("%%", o), "%");
		assertEquals(StringUtil.sprintf("765", o), "765");
		try
		{
			StringUtil.sprintf("abc%7idef", o);
			fail("foobar is an int?");
		}
		catch (final Exception x)
		{
			//
		}

		o = new Object[] { new Integer(5), "foobar" };
		assertEquals(StringUtil.sprintf("abc %02i%7sdef", o), "abc 05 foobardef");
		assertEquals(StringUtil.sprintf("%02i%7sdef", o), "05 foobardef");
		o = new Object[] { new Long(5), "foobar" };
		assertEquals(StringUtil.sprintf("abc %02i%7sdef", o), "abc 05 foobardef");
		assertEquals(StringUtil.sprintf("%02i%7sdef", o), "05 foobardef");
		o = new Object[] { "5", "foobar" };
		assertEquals(StringUtil.sprintf("abc %02i%7sdef", o), "abc 05 foobardef");
		assertEquals(StringUtil.sprintf("%02i%7sdef", o), "05 foobardef");
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
		assertEquals(strTestString, strResultString, "Input and Outputstring are not equal");
	}

	/**
	 *
	 */
	@Test
	public void testSetHexBinaryBytesNull()
	{
		final String strTemp = StringUtil.setHexBinaryBytes(null, -1);
		assertEquals("", strTemp);
	}

	/**
	 *
	 */
	@Test
	public void testGetHexBinaryBytesNull()
	{
		final byte[] tempBuffer = StringUtil.getHexBinaryBytes(null);
		assertEquals(0, tempBuffer.length);
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
		assertEquals(newString, newString2);
		final byte[] green = new byte[] { 'g', 'r', (byte) 0xfc, 'n' };
		final String greenString = StringUtil.getUTF8String(green);

		assertEquals(greenString, "grün", "incorrectly encoded grün is also heuristically fixed");
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
		assertEquals(utf8Buf.length, utf8Buf2.length, "Input and Output bytes are not equal");
		assertEquals(strTestString, newString, "Input and Output string are not equal");
		assertEquals(strTestString, strResultString, "Input and Output string are not equal");
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
		assertEquals(StringUtil.setvString(v, " ", null, null), "Input Output");
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

		assertEquals("1 22 333 4444", s, "s");
		s = StringUtil.setvString(v, "", "", "");
		assertEquals("1223334444", s, "s");
		s = StringUtil.setvString(v, null, null, null);
		assertEquals("1223334444", s, "s");

		String[] a = new String[v.size()];
		a = v.toArray(a);
		s = StringUtil.setvString(a, " ", null, null);
		assertEquals("1 22 333 4444", s, "s");
	}

	/**
	 *
	 */
	@Test
	public void testEscape()
	{
		final String iri = "file://myHost/a/c%20aöü%25&?.txtß€";
		assertEquals(iri, StringUtil.unEscape(StringUtil.escape(iri, ":&?%", "%", 16, 2, 0x21, 127), "%", 16, 2), "escape round trip");
		assertEquals("%25_%c3%bc", StringUtil.escape("%_ü", ":&?%", "%", 16, 2, 0x21, 127), "escape ");
		assertEquals("%e2%82%ac", StringUtil.escape(new String(StringUtil.getUTF8Bytes("€")), ":&?%", "%", 16, 2, 0x21, 127), "escape ");
		assertEquals("€", StringUtil.escape("€", null, "%", 16, 2, 0x21, -1));
		assertEquals("ä", StringUtil.escape("ä", null, "%", 16, 2, 0x21, -1));
		assertEquals("a__a", StringUtil.escape("aäa", null, "_", -1, 0, 0x21, 127), "ä is 2 bytes --> __");
		assertEquals("a____a", StringUtil.escape("a€_a", null, "_", -1, 0, 0x21, 127));
		assertEquals("a%20b", StringUtil.escape("a b", " \t", "%", 16, 2, 0, -19));

		// assertEquals("a_€a", StringUtil.escape("a€a", null, "_", 0, 0, 0x21, 127));
	}

	/**
	 *
	 */
	@Test
	public void testUnEscape()
	{
		assertEquals("\\", StringUtil.unEscape("\\\\", "\\", -1, 1));
	}

	/**
	 *
	 */
	@Test
	public void testWrite2String()
	{
		assertNotNull(KElement.parseString(StringUtil.write2String(KElement.createRoot("a", null))));
	}

	/**
	 * @throws IOException
	 *
	 */
	@Test
	public void testWrite2StringBad() throws IOException
	{
		IStreamWriter w = Mockito.mock(IStreamWriter.class);
		Mockito.doThrow(IOException.class).when(w).writeStream(any());
		assertNull(StringUtil.write2String(w));
	}

	/**
	 *
	 */
	@Test
	public void testExtension()
	{
		final String iri = "file://my.Host/a.n/c%20ï¿½ï¿½ï¿½%25&?.txtï¿½";
		assertEquals(UrlUtil.extension(iri), "txtï¿½");
		assertNull(UrlUtil.extension("abc"));
	}

	/**
	 * test regexp matching utility
	 *
	 */
	@Test
	public void testMatchesIgnoreCase()
	{
		assertFalse(StringUtil.matchesIgnoreCase(null, "(.+ )*(BB)( .+)*", false));
		assertTrue(StringUtil.matchesIgnoreCase("a bb c", "(.+ )*(BB)( .+)*", false));
		assertTrue(StringUtil.matchesIgnoreCase("ff a bb c", "*B*", false));
		assertTrue(StringUtil.matchesIgnoreCase("mailTo:a@b.c", JDFConstants.REGEXP_EMAIL, false));
		assertFalse(StringUtil.matchesIgnoreCase("mailT:a@b.c", JDFConstants.REGEXP_EMAIL, false));
	}

	/**
	 * test prefix stripper
	 *
	 */
	@Test
	public void testStripPrefix()
	{
		assertEquals(StringUtil.stripPrefix("a:b", "a:", true), "b");
		assertEquals(StringUtil.stripPrefix("a:b", "A:", true), "b");
		assertEquals(StringUtil.stripPrefix("a:B", "A:", true), "B");
		assertEquals(StringUtil.stripPrefix("a:b", "A:", false), "a:b");
		assertEquals(StringUtil.stripPrefix("abcdef", "ABC", true), "def");
		assertNull(StringUtil.stripPrefix(null, "A:", false));
		assertEquals(StringUtil.stripPrefix("a:b", null, false), "a:b");
	}

	/**
	 *
	 */
	@Test
	public void testStripNot()
	{
		assertEquals(StringUtil.stripNot("a1b321", "12"), "121");
		assertNull(StringUtil.stripNot("a1b321", null));
		assertNull(StringUtil.stripNot("a1b321", "7"));
	}

	/**
	 *
	 */
	@Test
	public void testStripQuote()
	{
		assertEquals(StringUtil.stripQuote(",123,", ",", true), "123");
		assertEquals(StringUtil.stripQuote(",123,", ",", false), "123");
		assertEquals(StringUtil.stripQuote(",123, ", ",", false), ",123, ");
		assertEquals(StringUtil.stripQuote(" 	,123, ", ",", true), "123");
		assertEquals(StringUtil.stripQuote(",", ",", true), ",");
	}

	/**
	 *
	 */
	@Test
	public void testTrim()
	{
		assertEquals(StringUtil.trim(",123,", ","), "123");
		assertEquals(StringUtil.trim(", ,123,", ", &"), "123");
		assertEquals(StringUtil.trim("123", ", &"), "123");
		assertEquals(StringUtil.trim("123&", ", &"), "123");
		assertEquals(StringUtil.trim(" 123", ", &"), "123");
		assertEquals(StringUtil.trim("", ", &"), null);
		assertEquals(StringUtil.trim("   ", " "), null);
		assertEquals(StringUtil.trim("   ", null), null);
		assertEquals(StringUtil.trim(null, null), null);
	}

	/**
	 * test regexp matching utility
	 *
	 */
	@Test
	public void testMatchesSimple()
	{
		assertFalse(StringUtil.matchesSimple(null, "(.+ )*(BB)( .+)*", false));
		assertTrue(StringUtil.matchesSimple("a bb c", "(.+ )*(bb)( .+)*", false));
		assertTrue(StringUtil.matchesSimple("b bb c", "(.* )*(bb)( .+)*", false));
		assertTrue(StringUtil.matchesSimple("a bb", "(.+ )*(bb)( .+)*", false));
		assertTrue(StringUtil.matchesSimple("bb", "(.+ )*(bb)( .+)*", false));
		assertFalse(StringUtil.matchesSimple(" bb", "(.+ )*(bb)( .+)*", false));
		assertFalse(StringUtil.matchesSimple("bb ", "(.+ )*(bb)( .+)*", false));
		assertFalse(StringUtil.matchesSimple("a", "(.+ )*(bb)( .+)*", false));
		assertFalse(StringUtil.matchesSimple("a c", "(.+ )*(bb)( .+)*", false));
		assertFalse(StringUtil.matchesSimple("a b c", "(.+ )*(bb)( .+)*", false));
		assertFalse(StringUtil.matchesSimple("123456", "\\d{5,5}", false));
		assertFalse(StringUtil.matchesSimple("1234", "\\d{5,5}", false));
		assertTrue(StringUtil.matchesSimple("12345", "\\d{5,5}"));
		assertTrue(StringUtil.matchesSimple("abc", "*", false));
		assertTrue(StringUtil.matchesSimple("abc", "?*", false));
		assertTrue(StringUtil.matchesSimple("abc", "?+", false));
		assertTrue(StringUtil.matchesSimple("abc", "", false));
		assertTrue(StringUtil.matchesSimple("äbc", "???", false));
		assertFalse(StringUtil.matchesSimple("abc", "????", false));
		assertFalse(StringUtil.matchesSimple("abc", "??", false));
		assertTrue(StringUtil.matchesSimple("€bc", null, false));
		assertTrue(StringUtil.matchesSimple("€", "(€)?", false));
		assertTrue(StringUtil.matchesSimple("€€", "€{0,2}", false));
		assertFalse(StringUtil.matchesSimple("€€€", "€{0,2}", false));
		assertTrue(StringUtil.matchesSimple("", "(€)?", false));
		assertTrue(StringUtil.matchesSimple("12de", JDFConstants.REGEXP_HEXBINARY, false));
		assertFalse(StringUtil.matchesSimple("12d", JDFConstants.REGEXP_HEXBINARY, false));
		assertFalse(StringUtil.matchesSimple("12dk", JDFConstants.REGEXP_HEXBINARY, false));

		assertTrue(StringUtil.matchesSimple("€", "(€)?", false));

		assertFalse(StringUtil.matchesSimple("abc", "??", false));
		assertTrue(StringUtil.matchesSimple(null, null, false));
		assertFalse(StringUtil.matchesSimple("abc", "?", false));
		assertTrue(StringUtil.matchesSimple("a b", "(a)?( b)?( c)?", false));
		assertTrue(StringUtil.matchesSimple("a b", "(a)? (b)?", false));
		assertTrue(StringUtil.matchesSimple("a b", "a?(( )*b)?", false));
		assertTrue(StringUtil.matchesSimple("a", "a?(( )*b)?", false));
		assertTrue(StringUtil.matchesSimple("b", "a?(( )*b)?", false));
		assertTrue(StringUtil.matchesSimple("b a c", "((.+ )*((a)|(b))( .+)*)+", false));
		assertTrue(StringUtil.matchesSimple("b a c", "((.+ )*((a)|(b))( .+)*){1,1}", false));
		assertFalse(StringUtil.matchesSimple("d e c", "((.+ )*((a)|(b))( .+)*)+", false));
		assertFalse(StringUtil.matchesSimple("b b", "a?(( )*b)?", false));
		assertTrue(StringUtil.matchesSimple("MIS_L2-1.3", "((.+ )*((MIS_L2-1.3)|(MISCPS_L1-1.3))( .+)*)+", false));

		assertTrue(StringUtil.matchesSimple("a-aB.3@b.c", JDFConstants.REGEXP_EMAIL, false));
		assertTrue(StringUtil.matchesSimple("a@b.c", JDFConstants.REGEXP_EMAIL, false));
		assertTrue(StringUtil.matchesSimple("mailto:a@b.c", JDFConstants.REGEXP_EMAIL, false));
		assertFalse(StringUtil.matchesSimple("mailt:a@b.c", JDFConstants.REGEXP_EMAIL, false));
		assertTrue(StringUtil.matchesSimple("aa@b.c", JDFConstants.REGEXP_EMAIL, false));
		assertFalse(StringUtil.matchesSimple("a@b", JDFConstants.REGEXP_EMAIL, false));
		assertTrue(StringUtil.matchesSimple("+(1).2/344", JDFConstants.REGEXP_PHONE, false));
		assertFalse(StringUtil.matchesSimple("+(1).2 344", JDFConstants.REGEXP_PHONE, false));

		assertTrue(StringUtil.matchesSimple("ab", "a*", false));
		assertTrue(StringUtil.matchesSimple("ab", "a(.*)", false));
		assertTrue(StringUtil.matchesSimple("a", "a*", false));
		assertFalse(StringUtil.matchesSimple("a", "a(.(.*))", false));
		assertTrue(StringUtil.matchesSimple("a", "a(b)?", false));
		assertTrue(StringUtil.matchesSimple("ab", "a(b)?", false));
		assertFalse(StringUtil.matchesSimple("ac", "a(b)?", false));

		assertTrue(StringUtil.matchesSimple("a b", "a b", false));
		assertTrue(StringUtil.matchesSimple("abc123ä", "abc123ä", false));
		assertTrue(StringUtil.matchesSimple("GangBrochureA4", "(Gang)?Bro(.)*", false));

	}

	/**
	 * test regexp matching utility
	 *
	 */
	@Test
	public void testMatchesRegExp()
	{
		assertFalse(StringUtil.matches(null, "(.+ )*(BB)( .+)*"));
		assertTrue(StringUtil.matches("a bb c", "(.+ )*(bb)( .+)*"));
		assertTrue(StringUtil.matches("b bb c", "(.* )*(bb)( .+)*"));
		assertTrue(StringUtil.matches("a bb", "(.+ )*(bb)( .+)*"));
		assertTrue(StringUtil.matches("bb", "(.+ )*(bb)( .+)*"));
		assertFalse(StringUtil.matches(" bb", "(.+ )*(bb)( .+)*"));
		assertFalse(StringUtil.matches("bb ", "(.+ )*(bb)( .+)*"));
		assertFalse(StringUtil.matches("a", "(.+ )*(bb)( .+)*"));
		assertFalse(StringUtil.matches("a c", "(.+ )*(bb)( .+)*"));
		assertFalse(StringUtil.matches("a b c", "(.+ )*(bb)( .+)*"));
		assertFalse(StringUtil.matches("123456", "\\d{5,5}"));
		assertFalse(StringUtil.matches("1234", "\\d{5,5}"));
		assertTrue(StringUtil.matches("12345", "\\d{5,5}"));
		assertTrue(StringUtil.matches("abc", ".*"));
		assertTrue(StringUtil.matches("abc", ".?.*"));
		assertTrue(StringUtil.matches("abc", ".+"));
		assertTrue(StringUtil.matches("abc", ""));
		assertTrue(StringUtil.matches("€bc", null));
		assertTrue(StringUtil.matches("€", "(€)?"));
		assertTrue(StringUtil.matches("€€", "€{0,2}"));
		assertFalse(StringUtil.matches("€€€", "€{0,2}"));
		assertTrue(StringUtil.matches("", "(€)?"));
		assertTrue(StringUtil.matches("12de", JDFConstants.REGEXP_HEXBINARY));
		assertFalse(StringUtil.matches("12d", JDFConstants.REGEXP_HEXBINARY));
		assertFalse(StringUtil.matches("12dk", JDFConstants.REGEXP_HEXBINARY));

		assertTrue(StringUtil.matches("€", "(€)?"));

		assertTrue(StringUtil.matches(null, null));
		assertFalse(StringUtil.matches("abc", "?"));
		assertTrue(StringUtil.matches("a b", "(a)?( b)?( c)?"));
		assertTrue(StringUtil.matches("a b", "(a)? (b)?"));
		assertTrue(StringUtil.matches("a b", "a?(( )*b)?"));
		assertTrue(StringUtil.matches("a", "a?(( )*b)?"));
		assertTrue(StringUtil.matches("b", "a?(( )*b)?"));
		assertTrue(StringUtil.matches("b a c", "((.+ )*((a)|(b))( .+)*)+"));
		assertTrue(StringUtil.matches("b a c", "((.+ )*((a)|(b))( .+)*){1,1}"));
		assertFalse(StringUtil.matches("d e c", "((.+ )*((a)|(b))( .+)*)+"));
		assertFalse(StringUtil.matches("b b", "a?(( )*b)?"));
		assertTrue(StringUtil.matches("MIS_L2-1.3", "((.+ )*((MIS_L2-1.3)|(MISCPS_L1-1.3))( .+)*)+"));

		assertTrue(StringUtil.matches("a-aB.3@b.c", JDFConstants.REGEXP_EMAIL));
		assertTrue(StringUtil.matches("a@b.c", JDFConstants.REGEXP_EMAIL));
		assertTrue(StringUtil.matches("mailto:a@b.c", JDFConstants.REGEXP_EMAIL));
		assertFalse(StringUtil.matches("mailt:a@b.c", JDFConstants.REGEXP_EMAIL));
		assertTrue(StringUtil.matches("aa@b.c", JDFConstants.REGEXP_EMAIL));
		assertFalse(StringUtil.matches("a@b", JDFConstants.REGEXP_EMAIL));
		assertTrue(StringUtil.matches("+(1).2/344", JDFConstants.REGEXP_PHONE));
		assertFalse(StringUtil.matches("+(1).2 344", JDFConstants.REGEXP_PHONE));

		assertTrue(StringUtil.matches("ab", "a.*"));
		assertTrue(StringUtil.matches("ab", "a(.*)"));
		assertTrue(StringUtil.matches("a", "a*"));
		assertFalse(StringUtil.matches("a", "a(.(.*))"));
		assertTrue(StringUtil.matches("a", "a(b)?"));
		assertTrue(StringUtil.matches("ab", "a(b)?"));
		assertFalse(StringUtil.matches("ac", "a(b)?"));

		assertTrue(StringUtil.matches("a b", "a b"));
		assertTrue(StringUtil.matches("abc123ä", "abc123ä"));
		assertTrue(StringUtil.matches("GangBrochureA4", "(Gang)?Bro(.)*"));
		assertFalse(StringUtil.matches("GangBrochureA4", "(Gang)?Bro(.)*5"));

		assertTrue(StringUtil.matches("GangBrochureA4 (1 3~6)", "(.)*\\(([\\d\\s~])+\\)"));
		assertFalse(StringUtil.matches("GangBrochureA4 (1 3~6", "(.)*\\(([\\d\\s~])+\\)"));
		assertFalse(StringUtil.matches("GangBrochureA4", "(.)*\\(([\\d\\s~])+\\)"));

	}

	/**
	 * test normalize utility
	 *
	 */
	@Test
	public void testNormalize()
	{
		assertEquals("a b", StringUtil.normalize("\n  a   \t  b ", false));
		assertEquals("a bbb", StringUtil.normalize("\n  a   \t  BBb ", true));
		assertEquals("a BBb", StringUtil.normalize("\n  a   \t  BBb ", false));
		assertNull(StringUtil.normalize("\n      \t   ", true));
		assertNull(StringUtil.normalize(null, true));
	}

	/**
	 * test normalize utility
	 *
	 */
	@Test
	public void testNormalize3()
	{
		assertEquals("a11b", StringUtil.normalize("\n  a   \t  b ", false, "11"));
		assertEquals("abbb", StringUtil.normalize("\n  a   \t  BBb ", true, null));
		assertEquals("aBBb", StringUtil.normalize("\n  a   \t  BBb ", false, null));
		assertNull(StringUtil.normalize("\n      \t   ", true, null));
		assertNull(StringUtil.normalize(null, true, null));
	}

	/**
	 *
	 */
	@Test
	public void testNumOccurrences()
	{
		assertEquals(StringUtil.numSubstrings("a", "aa"), 0);
		assertEquals(StringUtil.numSubstrings("aa", "aa"), 1);
		assertEquals(StringUtil.numSubstrings("aaa", "aa"), 2);
		assertEquals(StringUtil.numSubstrings("aa a", "aa"), 1);
		assertEquals(StringUtil.numSubstrings("ab/>", "/>"), 1);
		assertEquals(StringUtil.numSubstrings("aa a", ""), 0);
		assertEquals(StringUtil.numSubstrings("aa a", null), 0);
		assertEquals(StringUtil.numSubstrings(null, "a"), 0);
		assertEquals(StringUtil.numSubstrings(null, null), 0);
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
		assertTrue(b > 2);
	}

	/**
	 *
	 */
	@Test
	public void testReplaceString()
	{
		assertEquals(StringUtil.replaceString("abbcc", "a", "_"), "_bbcc");
		assertEquals(StringUtil.replaceString("abbcc", "aa", "_"), "abbcc");
		assertEquals(StringUtil.replaceString("abbcc", "b", "_"), "a__cc");
		assertEquals(StringUtil.replaceString("abbcc", "bb", "_"), "a_cc");
		assertEquals(StringUtil.replaceString("abbcc", "c", "_"), "abb__");
		assertEquals(StringUtil.replaceString("abbcc", "cc", "_"), "abb_");
		assertEquals(StringUtil.replaceString("abbcc", "bb", null), "acc");
		assertEquals(StringUtil.replaceString("000000", "00", "0"), "0");
		assertEquals(StringUtil.replaceString("000", "0", "00"), "000000");
	}

	/**
	 *
	 */
	@Test
	public void testStringReplacer()
	{
		StringReplacer sr = new StringReplacer("000000");
		assertEquals(sr.replaceString("00", "0"), "0");
		sr = new StringReplacer("000000");
		sr.setReRead(false);
		assertEquals(sr.replaceString("00", "0"), "000");
	}

	/**
	 *
	 */
	@Test
	public void testReplaceChar()
	{
		assertEquals(StringUtil.replaceChar("abbcc", 'a', null, 0), "bbcc");
		assertEquals(StringUtil.replaceChar("abbcc", 'b', null, 0), "acc");
		assertEquals(StringUtil.replaceChar("abbcc", 'b', null, 2), "abcc");
		assertEquals(StringUtil.replaceChar("abbcc", 'b', "_2", 0), "a_2_2cc");
		assertEquals(StringUtil.replaceChar("abbcc", 'b', "_2", 2), "ab_2cc");
		assertEquals(StringUtil.replaceChar("abbcc", 'b', "_2", 4), "abbcc");
		assertEquals(StringUtil.replaceChar("abbcc", 'b', "_2", 5), "abbcc");
		assertEquals(StringUtil.replaceChar("abbcc", 'b', "_2", 6), "abbcc");
	}

	/**
	 *
	 */
	@Test
	public void testReplaceCharSet()
	{
		assertEquals(StringUtil.replaceCharSet("abbcc", "ab", null, 0), "cc");
		assertEquals(StringUtil.replaceCharSet("abbcc", "ab", "_", 0), "___cc");
		assertEquals(StringUtil.replaceCharSet("abbcc", "ab", "_", 2), "ab_cc");
	}

	/**
	 *
	 */
	@Test
	public void testRightString()
	{
		assertEquals(StringUtil.rightStr("abbcc", 2), "cc");
		assertEquals(StringUtil.rightStr("abbcc", -3), "cc");
		assertNull(StringUtil.rightStr("abbcc", -5));
		assertNull(StringUtil.rightStr(null, -5));
		assertNull(StringUtil.rightStr("abc", -55));
	}

	/**
	 *
	 */
	@Test
	public void testLeftString()
	{
		assertEquals(StringUtil.leftStr("abbcc", 2), "ab");
		assertEquals(StringUtil.leftStr("abbcc", -2), "abb");
		assertNull(StringUtil.leftStr("abbcc", -5));
		assertNull(StringUtil.leftStr(null, -5));
		assertNull(StringUtil.leftStr("abc", -55));
	}

	/**
	 *
	 */
	@Test
	public void testSubstring()
	{
		// final String s = "a".substring(0, 0);
		assertEquals(StringUtil.substring("abbcc", 0, 2), "ab");
		assertEquals(StringUtil.substring("abbcc", 0, -2), "abb");
		assertEquals(StringUtil.substring("abbcc", -2, -2), "");
		assertEquals(StringUtil.substring("abbcc", -3, 0), "bcc");
		assertEquals(StringUtil.substring("abbcc", 2, -1), "bc");
		assertNull(StringUtil.substring("abbcc", 0, -6));
		assertNull(StringUtil.substring(null, 0, -5));
		assertNull(StringUtil.substring("abc", 0, -55));
	}

	/**
	 *
	 */
	@Test
	public void testParseLong()
	{
		assertEquals(StringUtil.parseLong("", 0L), 0L);
		assertEquals(StringUtil.parseLong("1234567890123456", 0L), 1234567890123456L);
		assertEquals(StringUtil.parseLong("INF", 0L), Long.MAX_VALUE);
		assertEquals(StringUtil.parseLong("unbounded", 0L), Long.MAX_VALUE);
		assertEquals(StringUtil.parseLong("-inf", 0L), Long.MIN_VALUE);
		assertEquals(StringUtil.parseLong("12341234561234567834556", 0), Long.MAX_VALUE);
		assertEquals(StringUtil.parseLong("-12341234561234567834556", 0), Long.MIN_VALUE);
		assertEquals(StringUtil.parseLong("-1.0e44", 0), Long.MIN_VALUE);
		assertEquals(StringUtil.parseLong("-0xa", 0), -10);
		assertEquals(StringUtil.parseLong("0xff", 0), 255);
	}

	/**
	 *
	 */
	@Test
	public void testParseInt()
	{
		assertEquals(StringUtil.parseInt("", 0), 0);
		assertEquals(StringUtil.parseInt("1234123456", 0), 1234123456);
		assertEquals(StringUtil.parseInt("+1234123456", 0), 1234123456);
		assertEquals(StringUtil.parseInt("1234123456.0", 0), 1234123456);
		assertEquals(StringUtil.parseInt("abc", 99), 99);
		assertEquals(StringUtil.parseInt("12341234561234567834556", 0), Integer.MAX_VALUE);
		assertEquals(StringUtil.parseInt("-12341234561234567834556", 0), Integer.MIN_VALUE);
		assertEquals(StringUtil.parseInt("INF", 0), Integer.MAX_VALUE);
		assertEquals(StringUtil.parseInt("-inf", 0), Integer.MIN_VALUE);
		assertEquals(StringUtil.parseInt("0xf", 0), 15);
		assertEquals(StringUtil.parseInt("-0xf", 0), -15);
	}

	/**
	 *
	 */
	@Test
	public void testParseBoolean()
	{
		assertEquals(StringUtil.parseBoolean("", false), false);
		assertEquals(StringUtil.parseBoolean("", true), true);
		assertEquals(StringUtil.parseBoolean("TRUE ", false), true);
		assertEquals(StringUtil.parseBoolean(" FalSe ", true), false);
		assertEquals(StringUtil.parseBoolean("1 ", false), true);
		assertEquals(StringUtil.parseBoolean(" 0 ", true), false);
	}

	/**
	 *
	 */
	@Test
	public void testParseDouble()
	{
		String s = "INF";
		assertEquals(StringUtil.parseDouble(s, 0), Double.MAX_VALUE, 0.0);
		assertTrue(StringUtil.isNumber(s));
		s = "-INF";
		assertEquals(StringUtil.parseDouble(s, 0), -Double.MAX_VALUE, 0.0);
		assertTrue(StringUtil.isNumber(s));
		s = "123.45e3 ";
		assertEquals(StringUtil.parseDouble(s, 0), 123450., 0.);
		assertTrue(StringUtil.isNumber(s));
		assertEquals(StringUtil.parseDouble(s, 0), 123450., 0.);
		assertTrue(StringUtil.isNumber(s));
		s = "123.45E3";
		assertEquals(StringUtil.parseDouble(s, 0), 123450., 0.);
		assertTrue(StringUtil.isNumber(s));
		s = "123.45";
		assertEquals(StringUtil.parseDouble(s, 0), 123.450, 0.);
		assertTrue(StringUtil.isNumber(s));
		s = "-123.45";
		assertEquals(StringUtil.parseDouble(s, 0), -123.450, 0.);
		assertTrue(StringUtil.isNumber(s));
		s = ".45";
		assertEquals(StringUtil.parseDouble(s, 0), 0.450, 0., "missing leading zero ok");
		assertTrue(StringUtil.isNumber(s));
		s = "-123.45a";
		assertEquals(StringUtil.parseDouble(s, 0.), 0., 0.);
		assertFalse(StringUtil.isNumber(s));
		s = "";
		assertEquals(StringUtil.parseDouble(s, 0.), 0., 0.);
		assertFalse(StringUtil.isNumber(s));
		s = "1,2";
		assertEquals(StringUtil.parseDouble(s, 0.), 1.2, 0., "gracefully handle ',' as a separator char");
		assertTrue(StringUtil.isNumber(s), "gracefully handle ',' as a separator char");
		s = null;
		assertEquals(StringUtil.parseDouble(s, 0.), 0., 0.);
		assertFalse(StringUtil.isNumber(s));
	}

	/**
	 *
	 */
	@Test
	public void testFind_last_not_of()
	{
		assertEquals(StringUtil.find_last_not_of("abc", "bcd"), 0);
		assertEquals(StringUtil.find_last_not_of("abc", "abc"), -1);
		assertEquals(StringUtil.find_last_not_of("abc", "ac"), 1);
		assertEquals(StringUtil.find_last_not_of("grün", "ï¿½ï¿½ï¿½"), 3);
		assertEquals(StringUtil.find_last_not_of("abc", "_"), 2);
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
			assertEquals(StringUtil.parseLong(StringUtil.formatLong(l), -1), l);
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
		assertEquals("0.12345679", s, "s=6");
		d = 0.12345678;
		s = StringUtil.formatDouble(d);
		assertEquals("0.12345678", s, "s=6");
		d = 0.123456789;
		s = StringUtil.formatDouble(d);
		assertEquals("0.12345679", s, "s=6");
		d = 0.12345673;
		s = StringUtil.formatDouble(d);
		assertEquals("0.12345673", s, "s=5");
		d = 234.0;
		s = StringUtil.formatDouble(d);
		assertEquals("234", s, "s=int");
		d = 123.456e4;
		s = StringUtil.formatDouble(d);
		assertEquals("1234560", s, "s=int");
		assertEquals("0", StringUtil.formatDouble(0.1e-20), "s=real small");
		assertEquals("0", StringUtil.formatDouble(-0.1e-20), "s=real small -");
		assertEquals("1", StringUtil.formatDouble(1.000000001), "s=1+epsilon");
		assertEquals("1", StringUtil.formatDouble(0.99999999987), "s=1-epsilon");
		assertNotSame("1", StringUtil.formatDouble(0.99949999987), "s=1-epsilon");
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

			assertEquals(StringUtil.uncToUrl(unc, true), uri, "uri ok");
			assertEquals(StringUtil.uncToUrl(unc, false), iri, "iri ok");
		}
	}

	// /////////////////////////////////////////////////////////////////////////

	/**
	 *
	 */
	@Test
	public void testZappTokenWS()
	{
		assertEquals("1 2 3~4", StringUtil.zappTokenWS(" 1 2 3~    4 ", JDFConstants.TILDE), "new string");
		assertEquals("1 2 3~4", StringUtil.zappTokenWS(" 1 2 3~    4", JDFConstants.TILDE), "new string");
		assertEquals("1 2 3~4", StringUtil.zappTokenWS(" 1 2 3~    4~", JDFConstants.TILDE), "new string");
		assertEquals(StringUtil.zappTokenWS(" n2 ", null), "n2");
	}

	/**
	 *
	 */
	@Test
	public void testHasTokenNeg()
	{
		assertTrue(StringUtil.hasToken("1", "1", null, -1));
		assertTrue(StringUtil.hasToken("1 2 1", "1", null, -1));
		assertTrue(StringUtil.hasToken("1 2 1", "2", null, -1));
		assertFalse(StringUtil.hasToken("1 2 1", "2", null, -2));
		assertFalse(StringUtil.hasToken("1 2 1", "1", null, -3));
	}

	/**
	 *
	 */
	@Test
	public void testHasToken()
	{
		assertFalse(StringUtil.hasToken("1", "1", null, 1));
		final String s = "1 2 3 3 \n15\n4";
		assertFalse(StringUtil.hasToken(s, "0", " \n", 0));
		assertTrue(StringUtil.hasToken(s, "1", " ", 0));
		assertFalse(StringUtil.hasToken("", "1", null, 0));
		assertTrue(StringUtil.hasToken(s, "1", null, 0));
		assertFalse(StringUtil.hasToken(s, "5", " ", 0));
		assertFalse(StringUtil.hasToken(s, "15", " ", 0));
		assertTrue(StringUtil.hasToken(s, "2", " ", 0));
		assertTrue(StringUtil.hasToken(s, "4", "\n ", 0));
		assertTrue(StringUtil.hasToken(s, "3", " ", 0));
		assertTrue(StringUtil.hasToken(s, "3", " ", 1));
		assertFalse(StringUtil.hasToken(s, "3", " ", 2));
		assertFalse(StringUtil.hasToken(s, "3", " ", 99));
		assertFalse(StringUtil.hasToken(null, "0", " ", 0));
		assertFalse(StringUtil.hasToken("ab", "a", " ", 0));
		assertFalse(StringUtil.hasToken("ab", "b", " ", 0));
		assertFalse(StringUtil.hasToken("abab", "ab", " ", 0));
		assertFalse(StringUtil.hasToken("ababa", "ab", " ", 0));
		assertTrue(StringUtil.hasToken("abc ab", "ab", " ", 0));
		assertTrue(StringUtil.hasToken("abc ab ", "ab", " ", 0));
		assertTrue(StringUtil.hasToken("abc ab abd", "ab", " ", 0));
		assertTrue(StringUtil.hasToken("a", "a", " ", 0), "string matches are always true for 0");
		assertTrue(StringUtil.hasToken("a", "a", ", ", -1), "string matches are always true for -1");

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
		assertTrue(StringUtil.hasToken(s, "33", " ", 0));
		assertFalse(StringUtil.hasToken(s, "34", " ", 0));
		assertFalse(StringUtil.hasToken(s2, "ab12345679", " ", 0));
		assertTrue(StringUtil.hasToken(s2, "ab1234567", " ", 0));
		assertTrue(StringUtil.hasToken(s2, "ab", " ", 0));
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
		assertEquals(StringUtil.token(s, 0, " "), "1");
		assertEquals(StringUtil.token(s, 1, " "), "2");
		assertEquals(StringUtil.token(s, -3, " "), "2");
		assertEquals(StringUtil.token(s, -1, " "), "4");
		assertNull(StringUtil.token(s, 4, " "));
		assertNull(StringUtil.token(s, -5, " "));
		assertNull(StringUtil.token(null, 2, " "));
		s = "a/b?c";
		assertEquals(StringUtil.token(s, 0, "?/"), "a");
		assertEquals(StringUtil.token(s, 1, "?/"), "b");
		assertEquals(StringUtil.token(s, 2, "?/"), "c");
		assertNull(StringUtil.token("", 0, null));
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
		assertEquals(StringUtil.tokenize(s, " \n", false), v);
		final String descName = "foobar( 1,2,3  ,4,5 )";
		final int pos2 = descName.lastIndexOf(')');
		final int pos1 = descName.lastIndexOf('(') + 1;
		assertEquals(v, StringUtil.tokenize(descName.substring(pos1, pos2), ", ", false));
	}

	/**
	 *
	 */
	@Test
	public void testReplaceTokenString()
	{
		final String s = "a/b/c";
		assertEquals(StringUtil.replaceToken(s, "a", "/", "A"), "A/b/c");
		assertEquals(StringUtil.replaceToken(s, 0, "/", null), "b/c");
		assertEquals(StringUtil.replaceToken(s, "c", "/", null), "a/b");
		assertEquals(StringUtil.replaceToken(s, "d", "/", null), s);
	}

	/**
	 *
	 */
	@Test
	public void testRemoveTokenString()
	{
		final String s = "a/b/c";
		assertEquals(StringUtil.removeToken(s, "a", "/"), "b/c");
		assertEquals(StringUtil.removeToken(s, "b", "/"), "a/c");
		assertEquals(StringUtil.removeToken(s, "c", "/"), "a/b");
		assertEquals(StringUtil.removeToken("aa/bb/cc", "c", "/"), "aa/bb/cc");
	}

	/**
	 *
	 */
	@Test
	public void testReplaceToken()
	{
		final String s = "a/b/c";
		assertEquals(StringUtil.replaceToken(s, 0, "/", "A"), "A/b/c");
		assertEquals(StringUtil.replaceToken(s, 0, "/", null), "b/c");
		assertEquals(StringUtil.replaceToken(s, -1, "/", null), "a/b");
		assertEquals(StringUtil.replaceToken(s, -1, "/", ""), "a/b/");
		assertEquals(StringUtil.replaceToken(s, -1, "/", "A"), "a/b/A");
		assertEquals(StringUtil.replaceToken(s, 1, "/", "A"), "a/A/c");
		assertEquals(StringUtil.replaceToken(s, 1, "/", null), "a/c");
		assertEquals(StringUtil.replaceToken(s, -5, "/", "A"), "a/b/c");
		final String s2 = "//a/b/c";
		assertEquals(StringUtil.replaceToken(s2, 0, "/", "A"), "//A/b/c");
		assertEquals(StringUtil.replaceToken(s2, 2, "/", null), "//a/b");
		final String s3 = "a";
		assertEquals(StringUtil.replaceToken(s3, 0, "/", "A"), "A");
		assertNull(StringUtil.replaceToken(s3, 0, "/", null));
		final String s4 = "a_b";
		assertEquals(StringUtil.replaceToken(s4, 0, "_", "c"), "c_b");
		assertEquals(StringUtil.replaceToken(s4, 0, "_", null), "b");
	}

	/**
	 *
	 */
	@Test
	public void testRemoveToken()
	{
		assertEquals(StringUtil.removeToken("a/b/c", 0, "/"), "b/c");
		assertEquals(StringUtil.removeToken("a/b/c", -1, "/"), "a/b");
		assertEquals(StringUtil.removeToken("a/b/c", -2, "/"), "a/c");
		assertNull(StringUtil.removeToken("a/", -1, "/"));
		assertEquals(StringUtil.removeToken("/a/", -1, "/"), "/");
		assertNull(StringUtil.removeToken("a", -1, "/"));
		assertNull(StringUtil.removeToken("/a", -1, "/"));
	}

	/**
	 *
	 */
	@Test
	public void testAddToken()
	{
		assertNull(StringUtil.addToken(null, "/", null));
		assertEquals(StringUtil.addToken("a", "/", "b"), "a/b");
		assertEquals(StringUtil.addToken("a/", "/", "b"), "a/b");
		assertEquals(StringUtil.addToken("a", "/", "/b"), "a/b");
		assertEquals(StringUtil.addToken("a/", "/", "/b"), "a/b");
		assertEquals(StringUtil.addToken("a/", "/", "//b"), "a/b");
		assertEquals(StringUtil.addToken("//a/", "/", "//b"), "//a/b");
		assertEquals(StringUtil.addToken("/", "/", "b"), "/b");
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
		assertEquals(StringUtil.tokenize(s, "/?", true), v);
	}

	/**
	 *
	 */
	@Test
	public void testTokenizeBrackets()
	{
		assertEquals(StringUtil.tokenizeBrackets("?", '?', ':'), new VString(new String[] { "", "" }));
		assertEquals(StringUtil.tokenizeBrackets("a", '?', ':'), new VString(new String[] { "a" }));
		assertEquals(StringUtil.tokenizeBrackets("a:", '?', ':'), new VString(new String[] { "a", "", "" }));
		assertEquals(StringUtil.tokenizeBrackets("a:b", '?', ':'), new VString(new String[] { "a", "", "b" }));
		assertEquals(StringUtil.tokenizeBrackets("a:b?c:d", '?', ':'), new VString(new String[] { "a", "", "b?c:d" }));
		assertEquals(StringUtil.tokenizeBrackets("?:", '?', ':'), new VString(new String[] { "", "", "" }));
		assertEquals(StringUtil.tokenizeBrackets("a?:", '?', ':'), new VString(new String[] { "a", "", "" }));
		assertEquals(StringUtil.tokenizeBrackets("a?b:", '?', ':'), new VString(new String[] { "a", "b", "" }));
		assertEquals(StringUtil.tokenizeBrackets("a?b:c", '?', ':'), new VString(new String[] { "a", "b", "c" }));
		assertEquals(StringUtil.tokenizeBrackets("a?b?c:d:e", '?', ':'), new VString(new String[] { "a", "b?c:d", "e" }));
		assertEquals(StringUtil.tokenizeBrackets("a?b:c?d:e", '?', ':'), new VString(new String[] { "a", "b", "c?d:e" }));
		assertEquals(StringUtil.tokenizeBrackets("a?b:c?d:e?f:g", '?', ':'), new VString(new String[] { "a", "b", "c?d:e?f:g" }));
	}

	/**
	 *
	 */
	@Test
	public void testConcatStrings()
	{
		final VString v = StringUtil.tokenize("a b c", " ", false);
		StringUtil.concatStrings(v, "_foo");
		assertEquals("a_foo b_foo c_foo", StringUtil.setvString(v, " ", null, null));
	}

	/**
	 *
	 */
	@Test
	public void testConcatString()
	{
		assertEquals("acb", StringUtil.concat("a", "b", "c"));
		assertEquals("a", StringUtil.concat("a", null, "c"));
		assertEquals("a", StringUtil.concat(null, "a", null));
		assertNull(StringUtil.concat(null, "", null));
		assertNull(StringUtil.concat("", "", null));
	}

	/**
	 *
	 */
	@Test
	public void testCreateString()
	{
		final ByteArrayIOStream ios = new ByteArrayIOStream("abc".getBytes());
		assertEquals(StringUtil.createString(ios.getInputStream()), "abc");
		ios.close();
	}

	/**
	 *
	 */
	@Test
	public void testEndsWithIgnoreCase()
	{
		final String s = "a.ZIP";
		assertTrue(s.toLowerCase().endsWith(".zip"));
		assertEquals(s, "a.ZIP");
	}

	// /////////////////////////////////////////////////////////////////////////

	/**
	 *
	 */
	@Test
	public void testEquals()
	{
		assertTrue(StringUtil.equals("", null));
		assertTrue(StringUtil.equals(null, null));
		assertTrue(StringUtil.equals("", ""));
		assertFalse(StringUtil.equals("a", ""));
		assertFalse(StringUtil.equals("a", null));
	}

	/**
	 *
	 */
	@Test
	public void testEqualsPrecision()
	{
		assertTrue(StringUtil.equals("", null, 0.001));
		assertTrue(StringUtil.equals(null, null, 0.001));
		assertTrue(StringUtil.equals("1", "1.0", 0));
		assertTrue(StringUtil.equals("1", "1.0001", 0.001));
		assertTrue(StringUtil.equals("1 2", "1.0001 2.0002", 0.001));
		assertTrue(StringUtil.equals("true", "TRUE", 0));
		JDFDate jdfDate = new JDFDate();
		assertTrue(StringUtil.equals(jdfDate.getDateTimeISO(), jdfDate.addOffset(1, 0, 0, 0).getDateTimeISO(), 1234));
	}

	/**
	 *
	 */
	@Test
	public void testisInteger()
	{
		assertFalse(StringUtil.isInteger(""));
		assertFalse(StringUtil.isInteger("a"));
		assertFalse(StringUtil.isInteger("1.0"));
		assertTrue(StringUtil.isInteger("123"));
		assertTrue(StringUtil.isInteger("-123"));
		assertTrue(StringUtil.isInteger("+123"));
	}

	/**
	 *
	 */
	@Test
	public void testisNumber()
	{
		assertFalse(StringUtil.isNumber(""));
		assertFalse(StringUtil.isNumber(" a"));
		assertFalse(StringUtil.isNumber("a"));
		assertTrue(StringUtil.isNumber("1.0"));
		assertTrue(StringUtil.isNumber("1.0"));
		assertTrue(StringUtil.isNumber("123"));
		assertTrue(StringUtil.isNumber("-123"));
		assertTrue(StringUtil.isNumber("+123"));
		assertTrue(StringUtil.isNumber(".123"));
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
			assertFalse(StringUtil.isNumber(""));
			assertFalse(StringUtil.isNumber(" a"));
			assertFalse(StringUtil.isNumber("a"));
			assertTrue(StringUtil.isNumber("1.0"));
			assertTrue(StringUtil.isNumber("1.0"));
			assertTrue(StringUtil.isNumber("123"));
			assertTrue(StringUtil.isNumber("-123"));
			assertTrue(StringUtil.isNumber("+123"));
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
			assertTrue(StringUtil.isEqual(d2, StringUtil.parseDouble(StringUtil.formatDouble(d2), 0.)), "" + d2);
			assertTrue(StringUtil.isEqual(d3, StringUtil.parseDouble(StringUtil.formatDouble(d3), 0.)), "" + d3);
			assertFalse(StringUtil.isEqual(d2, d2 * (1 + 1.1 * JDFBaseDataTypes.EPSILON) + JDFBaseDataTypes.EPSILON), "" + d2);
			assertFalse(StringUtil.isEqual(d3, d3 * (1 + 1.1 * JDFBaseDataTypes.EPSILON) - JDFBaseDataTypes.EPSILON), "" + d3);
		}
		assertTrue(StringUtil.isEqual(0.000000001, -0.000000001), "0.000001");
		assertTrue(StringUtil.isEqual(4, 4), "int");
	}

	// /////////////////////////////////////////////////////////////////////////

	/**
	 *
	 */
	@Test
	public void testCompareTo()
	{
		assertEquals(-1, StringUtil.compareTo(-3, -2));
		assertEquals(1, StringUtil.compareTo(3, 2));
		assertEquals(1, StringUtil.compareTo(3, 2));
		assertEquals(1, StringUtil.compareTo(3, 2));
		assertEquals(0, StringUtil.compareTo(2 + 0.5 * JDFBaseDataTypes.EPSILON, 2));
	}

	/**
	 *
	 */
	@Test
	public void testIsNMTOKEN()
	{
		assertTrue(StringUtil.isNMTOKEN("abc"));
		assertTrue(StringUtil.isNMTOKEN("ä"));
		assertFalse(StringUtil.isNMTOKEN("$"));
		assertTrue(StringUtil.isNMTOKEN("öpü"));
		assertTrue(StringUtil.isNMTOKEN("1öpü2.:-_"));
		assertFalse(StringUtil.isNMTOKEN(" abc"));
		assertFalse(StringUtil.isNMTOKEN("a bc"));
		assertFalse(StringUtil.isNMTOKEN("a\nbc"));
		assertFalse(StringUtil.isNMTOKEN("\tabc"));
		assertFalse(StringUtil.isNMTOKEN("abc "));
	}

	/**
	 *
	 */
	@Test
	public void testIsBoolean()
	{
		assertTrue(StringUtil.isBoolean("1 "));
		assertFalse(StringUtil.isBoolean("abc "));
	}

	/**
	 *
	 */
	@Test
	public void testIsID()
	{
		assertTrue(StringUtil.isID("abc"));
		assertFalse(StringUtil.isID("1abc"));
	}

	/**
	 *
	 */
	@Test
	public void testIsDate()
	{
		assertTrue(StringUtil.isDate(new JDFDate().getDateTimeISO()));
		assertTrue(StringUtil.isDate(new JDFDate().getDateISO()));
		assertFalse(StringUtil.isDate("1abc"));
	}

	/**
	 *
	 */
	@Test
	public void testList()
	{
		assertTrue(StringUtil.isNumberList("1"));
		assertTrue(StringUtil.isNumberList("1.04 6.777"));
		assertFalse(StringUtil.isNumberList("a"));
	}

	/**
	 *
	 */
	@Test
	public void testgetDatatype()
	{
		assertEquals(EDataType.integer, StringUtil.getDataType("-1"));
		assertEquals(EDataType.integer, StringUtil.getDataType("1234567890"));
		assertEquals(EDataType.bool, StringUtil.getDataType(" false "));
		assertEquals(EDataType.date, StringUtil.getDataType(new JDFDate().getDateTimeISO()));
		assertEquals(EDataType.number, StringUtil.getDataType(" 1e15 "));
		assertEquals(EDataType.number, StringUtil.getDataType(" 1.234 "));
		assertEquals(EDataType.numberlist, StringUtil.getDataType(" 1 1.234 "));
		assertEquals(EDataType.string, StringUtil.getDataType(" 1a1.234 "));
		assertNull(StringUtil.getDataType(null));
	}

	/**
	 *
	 */
	@Test
	public void testgetCharset()
	{
		assertEquals(StandardCharsets.UTF_8, StringUtil.getCharset(StringUtil.UTF8));
		assertEquals(StandardCharsets.ISO_8859_1, StringUtil.getCharset("8859_1"));
		assertNull(StringUtil.getCharset("snafu"));
	}

	/**
	 *
	 */
	@Test
	public void testisWindowsLocalPath()
	{
		assertTrue(UrlUtil.isWindowsLocalPath("c:\\foo"));
		assertTrue(UrlUtil.isWindowsLocalPath("c:\\foo\\bar.abc"));
		assertTrue(UrlUtil.isWindowsLocalPath("d:foo"));
		assertFalse(UrlUtil.isWindowsLocalPath("\\\\foo\\bar"));
		assertFalse(UrlUtil.isWindowsLocalPath("c/d/e.f"));
		assertFalse(UrlUtil.isWindowsLocalPath("/c/d/e.f"));
	}

	// /////////////////////////////////////////////////////////////////////////

	/**
	 *
	 */
	@Test
	public void testPathToName()
	{
		assertEquals(StringUtil.pathToName("\\\\foo\\bar"), "bar");
		assertEquals(StringUtil.pathToName("c:\\foo\\bar.txt"), "bar.txt");
		assertEquals(StringUtil.pathToName("c/foo/bar.txt"), "bar.txt");
		assertEquals(StringUtil.pathToName("bar.txt"), "bar.txt");
	}

	// /////////////////////////////////////////////////////////////////////////

	/**
	 *
	 */
	@Test
	public void testGetNamesVector()
	{
		VString v = EnumUtil.getNamesVector(EnumType.AbortQueueEntry.getClass());
		assertTrue(v.contains("Resource"));
		v = EnumUtil.getNamesVector(EnumOrientation.Flip0.getClass());
		assertTrue(v.contains("Rotate90"));
	}

	// /////////////////////////////////////////////////////////////////////////

	/**
	 *
	 */
	@Test
	public void testGetEnumsVector()
	{
		final Vector<ValuedEnum> v = EnumUtil.getEnumsVector(EnumOrientation.Flip180.getClass());
		assertTrue(v.contains(EnumOrientation.Rotate180));
	}

	/**
	 *
	 */
	@Test
	public void testhasContent()
	{
		assertTrue(StringUtil.hasContent(" a b"));
		assertFalse(StringUtil.hasContent(""));
		assertFalse(StringUtil.hasContent("  "));
		assertFalse(StringUtil.hasContent("\t\n"));
		assertFalse(StringUtil.hasContent(null));

	}

}
