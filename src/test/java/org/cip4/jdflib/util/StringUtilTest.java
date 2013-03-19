/*
 * The CIP4 Software License, Version 1.0
 *
 *
 * Copyright (c) 2001-2013 The International Cooperation for the Integration of
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
 * 04022005 VF initial version
 */
/*
 * Created on Aug 26, 2004
 *
 * To change the template for this generated file go to
 * Window - Preferences - Java - Code Generation - Code and Comments
 */
package org.cip4.jdflib.util;

import java.io.File;
import java.util.Vector;

import org.apache.commons.lang.enums.ValuedEnum;
import org.cip4.jdflib.JDFTestCaseBase;
import org.cip4.jdflib.core.JDFConstants;
import org.cip4.jdflib.core.JDFElement.EnumOrientation;
import org.cip4.jdflib.core.JDFResourceLink.EnumUsage;
import org.cip4.jdflib.core.VString;
import org.cip4.jdflib.datatypes.JDFBaseDataTypes;
import org.cip4.jdflib.jmf.JDFMessage.EnumType;
import org.cip4.jdflib.util.StringUtil.StringReplacer;
import org.junit.Assert;
import org.junit.Test;

/**
 * @author MatternK
 * 
 * To change the template for this generated type comment go to Window - Preferences - Java - Code Generation - Code and Comments
 */
public class StringUtilTest extends JDFTestCaseBase {
	/**
	 * 
	 */
	@Test
	public void testSimpleRegexp() {
		Assert.assertEquals(StringUtil.simpleRegExptoRegExp("??"), "(.)(.)");
		Assert.assertEquals(StringUtil.simpleRegExptoRegExp("(a?)"), "(a?)");
		Assert.assertEquals(StringUtil.simpleRegExptoRegExp("*b??"), "(.*)b(.)(.)");
		Assert.assertEquals(StringUtil.simpleRegExptoRegExp("ab"), "ab");
		Assert.assertEquals(StringUtil.simpleRegExptoRegExp("a.b"), "a\\.b");
		Assert.assertEquals(StringUtil.simpleRegExptoRegExp("a(.+)b"), "a(.+)b");
		Assert.assertEquals(StringUtil.simpleRegExptoRegExp("*.b"), "(.*)\\.b");
		Assert.assertEquals("don't reconvert real regexp", StringUtil.simpleRegExptoRegExp("(.*)\\.b"), "(.*)\\.b");
		Assert.assertTrue(StringUtil.matches("foo.txt", StringUtil.simpleRegExptoRegExp("*.tx*")));
		Assert.assertTrue(StringUtil.matches(".tx", StringUtil.simpleRegExptoRegExp("*.tx*")));
		Assert.assertTrue(StringUtil.matches("55", StringUtil.simpleRegExptoRegExp("55|56")));
		Assert.assertTrue(StringUtil.matches("56", StringUtil.simpleRegExptoRegExp("55|56")));
		Assert.assertFalse(StringUtil.matches("57", StringUtil.simpleRegExptoRegExp("55|56")));
		Assert.assertFalse(StringUtil.matches("foo_txt", StringUtil.simpleRegExptoRegExp("*.tx*")));
		Assert.assertTrue(StringUtil.matches("aa", StringUtil.simpleRegExptoRegExp("??")));
		Assert.assertTrue(StringUtil.matches("abc", StringUtil.simpleRegExptoRegExp("*b?")));
		Assert.assertTrue(StringUtil.matches("abc", StringUtil.simpleRegExptoRegExp("ab(.)")));
		Assert.assertFalse(StringUtil.matches("ab", StringUtil.simpleRegExptoRegExp("ab(.)")));
		Assert.assertFalse(StringUtil.matches("abcd", StringUtil.simpleRegExptoRegExp("ab(.)")));
		Assert.assertFalse(StringUtil.matches("abc", StringUtil.simpleRegExptoRegExp("*b??")));
		Assert.assertFalse(StringUtil.matches("abcd", StringUtil.simpleRegExptoRegExp("*b?")));
	}

	/**
	 * 
	 */
	@Test
	public void testWipeInvalidXML10Chars() {
		final char[] cs = new char[] { 'a', 0x7, 0x3, 'b', 0x5 };
		Assert.assertEquals(StringUtil.wipeInvalidXML10Chars(new String(cs), null), "ab");
		Assert.assertEquals(StringUtil.wipeInvalidXML10Chars(new String(cs), "_"), "a__b_");
		Assert.assertEquals(StringUtil.wipeInvalidXML10Chars("abc", null), "abc");
	}

	/**
	 * 
	 */
	// TODO @Stefan @Test
	public void testGetRelativePath() {
		File f = new File("./a");
		Assert.assertEquals(StringUtil.replaceChar(UrlUtil.getRelativePath(f, null), '\\', "/", 0), "a");
		f = new File("../a.b");
		Assert.assertEquals(StringUtil.replaceChar(UrlUtil.getRelativePath(f, null), '\\', "/", 0), "../a.b");
		f = new File("./../a b/b");
		Assert.assertEquals(StringUtil.replaceChar(UrlUtil.getRelativePath(f, null), '\\', "/", 0), "../a b/b");
		f = new File("a/b/c");
		Assert.assertEquals(StringUtil.replaceChar(UrlUtil.getRelativePath(f, null), '\\', "/", 0), "a/b/c");
		f = new File("a/b/c");
		Assert.assertEquals(StringUtil.replaceChar(UrlUtil.getRelativePath(f, f), '\\', "/", 0), ".");
	}

	/**
	 * test for getDefaultNull
	 */
	@Test
	public void testGetDefaultNull() {
		Assert.assertNull(StringUtil.getDefaultNull("", ""));
		Assert.assertNull(StringUtil.getDefaultNull(null, ""));
		Assert.assertEquals("a", StringUtil.getDefaultNull("a", ""));
	}

	/**
	 * test for getNonEmpty
	 */
	@Test
	public void testGetNonEmpty() {
		Assert.assertNull(StringUtil.getNonEmpty(""));
		Assert.assertNull(StringUtil.getNonEmpty(null));
		Assert.assertEquals("a", StringUtil.getNonEmpty("a"));
	}

	/**
	 * test for gerRandomString
	 */
	@Test
	public void testGetRandomString() {
		final VString v = new VString();
		for (int i = 0; i < 1000; i++) {
			final String s = StringUtil.getRandomString();
			if (i % 100 == 0) {
				System.out.println(s);
			}
			v.appendUnique(s);
			Assert.assertTrue(s.length() > 1);
		}
		Assert.assertTrue("should be many different", v.size() < 900);
		Assert.assertTrue(v.size() > 5);
	}

	/**
	 * 
	 */
	@Test
	public void testSprintfString() {
		Assert.assertEquals(StringUtil.sprintf("part_%04i.txt", "" + 6), "part_0006.txt");
		Assert.assertEquals(StringUtil.sprintf("abc%03idef", "5"), "abc005def");
		Assert.assertEquals(StringUtil.sprintf("abc%03idef", "5.0"), "abc005def");
		Assert.assertEquals(StringUtil.sprintf("abc%03i%02idef", "5.0,5"), "abc00505def");
		Assert.assertEquals(StringUtil.sprintf("abc%03i%02idef%%%s", "5.0,5,abcdefghi"), "abc00505def%abcdefghi");
		Assert.assertEquals(StringUtil.sprintf("%2x", "12"), " c");
		Assert.assertEquals(StringUtil.sprintf("%2x", "18"), "12");
		Assert.assertEquals(StringUtil.sprintf("%s", "\\,"), ",");
		Assert.assertEquals(StringUtil.sprintf("%s", "\\\\,"), "\\,");
		Assert.assertEquals(StringUtil.sprintf("%s_%s", "\\\\,,a"), "\\,_a");

	}

	/**
	 * 
	 */
	@Test
	public void testSprintf() {
		Object[] o = new Object[1];
		o[0] = new Integer(5);
		Assert.assertEquals(StringUtil.sprintf("abc%03idef", o), "abc005def");
		o[0] = "foobar";
		Assert.assertEquals(StringUtil.sprintf("abc%7sdef", o), "abc foobardef");
		Assert.assertEquals(StringUtil.sprintf("abc%7s7def", o), "abc foobar7def");
		Assert.assertEquals(StringUtil.sprintf("%7sdef", o), " foobardef");
		Assert.assertEquals(StringUtil.sprintf("%7s", o), " foobar");
		Assert.assertEquals(StringUtil.sprintf("%%_%7s", o), "%_ foobar");
		Assert.assertEquals(StringUtil.sprintf("%%", o), "%");
		Assert.assertEquals(StringUtil.sprintf("765", o), "765");
		try {
			StringUtil.sprintf("abc%7idef", o);
			Assert.fail("foobar is an int?");
		} catch (final Exception x) {
			//
		}

		o = new Object[] { new Integer(5), "foobar" };
		Assert.assertEquals(StringUtil.sprintf("abc %02i%7sdef", o), "abc 05 foobardef");
		Assert.assertEquals(StringUtil.sprintf("%02i%7sdef", o), "05 foobardef");
		o = new Object[] { "5", "foobar" };
		Assert.assertEquals(StringUtil.sprintf("abc %02i%7sdef", o), "abc 05 foobardef");
		Assert.assertEquals(StringUtil.sprintf("%02i%7sdef", o), "05 foobardef");
	}

	/**
	 * 
	 */
	@Test
	public void testSetHexBinaryBytes() {
		final String strTestString = "ABCDEFGHIJKLMNOPQESTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789ï¿½ï¿½ï¿½ï¿½ï¿½ï¿½";
		final byte[] buffer = strTestString.getBytes();
		final String strTemp = StringUtil.setHexBinaryBytes(buffer, -1);
		final byte[] tempBuffer = StringUtil.getHexBinaryBytes(strTemp.getBytes());
		final String strResultString = new String(tempBuffer);
		Assert.assertEquals("Input and Outputstring are not equal", strTestString, strResultString);
	}

	/**
	 * 
	 */
	@Test
	public void testGetUTF8Bytes() {
		// String strTestString = "ï¿½";
		final String strTestString = "ABCDEFGHIJKLMNOPQESTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789aöü€";
		final byte[] utf8Buf = StringUtil.setUTF8String(strTestString);
		final String newString = StringUtil.getUTF8String(utf8Buf);
		final byte[] charBuf = strTestString.getBytes();
		final String newString2 = StringUtil.getUTF8String(charBuf);
		Assert.assertEquals(newString, newString2);
		final byte[] green = new byte[] { 'g', 'r', (byte) 0xfc, 'n' };
		final String greenString = StringUtil.getUTF8String(green);

		Assert.assertEquals("incorrectly encoded grün is also heuristically fixed", greenString, "grün");
	}

	/**
	 * 
	 */
	@Test
	public void testSetUTF8Bytes() {
		// String strTestString = "ï¿½";
		final String strTestString = "ABCDEFGHIJKLMNOPQESTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789ï¿½ï¿½ï¿½ï¿½ï¿½ï¿½";
		final byte[] utf8Buf = StringUtil.setUTF8String(strTestString);
		final String newString = StringUtil.getUTF8String(utf8Buf);
		final byte[] utf8Buf2 = StringUtil.setUTF8String(newString);
		final String strResultString = StringUtil.getUTF8String(utf8Buf2);
		Assert.assertEquals("Input and Output bytes are not equal", utf8Buf.length, utf8Buf2.length);
		Assert.assertEquals("Input and Output string are not equal", strTestString, newString);
		Assert.assertEquals("Input and Output string are not equal", strTestString, strResultString);
	}

	/**
	 * 
	 */
	@Test
	public void testSetVStringEnum() {
		final Vector<Object> v = new Vector<Object>();
		v.add(EnumUsage.Input);
		v.add(EnumUsage.Output);
		Assert.assertEquals(StringUtil.setvString(v, " ", null, null), "Input Output");
	}

	/**
	 * 
	 */
	@Test
	public void testSetVString() {
		final VString v = new VString();
		v.add("1");
		v.add("22");
		v.add("333");
		v.add("4444");
		String s = StringUtil.setvString(v);

		Assert.assertEquals("s", "1 22 333 4444", s);
		s = StringUtil.setvString(v, "", "", "");
		Assert.assertEquals("s", "1223334444", s);
		s = StringUtil.setvString(v, null, null, null);
		Assert.assertEquals("s", "1223334444", s);

		String[] a = new String[v.size()];
		a = v.toArray(a);
		s = StringUtil.setvString(a, " ", null, null);
		Assert.assertEquals("s", "1 22 333 4444", s);
	}

	/**
	 * 
	 */
	@Test
	public void testEscape() {
		final String iri = "file://myHost/a/c%20aöü%25&?.txtß€";
		Assert.assertEquals("escape round trip", iri, StringUtil.unEscape(StringUtil.escape(iri, ":&?%", "%", 16, 2, 0x21, 127), "%", 16, 2));
		Assert.assertEquals("escape ", "%25_%c3%bc", StringUtil.escape("%_ü", ":&?%", "%", 16, 2, 0x21, 127));
		Assert.assertEquals("escape ", "%e2%82%ac", StringUtil.escape(new String(StringUtil.setUTF8String("€")), ":&?%", "%", 16, 2, 0x21, 127));
		Assert.assertEquals("€", StringUtil.escape("€", null, "%", 16, 2, 0x21, -1));
		Assert.assertEquals("ä", StringUtil.escape("ä", null, "%", 16, 2, 0x21, -1));
		Assert.assertEquals("ä is 2 bytes --> __", "a__a", StringUtil.escape("aäa", null, "_", -1, 0, 0x21, 127));
		Assert.assertEquals("a____a", StringUtil.escape("a€_a", null, "_", -1, 0, 0x21, 127));
		Assert.assertEquals("a%20b", StringUtil.escape("a b", " \t", "%", 16, 2, 0, -19));

		// Assert.assertEquals("a_€a", StringUtil.escape("a€a", null, "_", 0, 0, 0x21, 127));
	}

	/**
	 * 
	 */
	@Test
	public void testExtension() {
		final String iri = "file://my.Host/a.n/c%20ï¿½ï¿½ï¿½%25&?.txtï¿½";
		Assert.assertEquals(UrlUtil.extension(iri), "txtï¿½");
		Assert.assertNull(UrlUtil.extension("abc"));
	}

	/**
	 * test regexp matching utility
	 * 
	 */
	@Test
	public void testMatchesIgnoreCase() {
		Assert.assertFalse(StringUtil.matchesIgnoreCase(null, "(.+ )*(BB)( .+)*"));
		Assert.assertTrue(StringUtil.matchesIgnoreCase("a bb c", "(.+ )*(BB)( .+)*"));
		Assert.assertTrue(StringUtil.matchesIgnoreCase("mailTo:a@b.c", JDFConstants.REGEXP_EMAIL));
		Assert.assertFalse(StringUtil.matchesIgnoreCase("mailT:a@b.c", JDFConstants.REGEXP_EMAIL));
	}

	/**
	 * test prefix stripper
	 * 
	 */
	@Test
	public void testStripPrefix() {
		Assert.assertEquals(StringUtil.stripPrefix("a:b", "a:", true), "b");
		Assert.assertEquals(StringUtil.stripPrefix("a:b", "A:", true), "b");
		Assert.assertEquals(StringUtil.stripPrefix("a:B", "A:", true), "B");
		Assert.assertEquals(StringUtil.stripPrefix("a:b", "A:", false), "a:b");
		Assert.assertEquals(StringUtil.stripPrefix("abcdef", "ABC", true), "def");
		Assert.assertNull(StringUtil.stripPrefix(null, "A:", false));
		Assert.assertEquals(StringUtil.stripPrefix("a:b", null, false), "a:b");
	}

	/**
	 * 
	 */
	@Test
	public void testStripNot() {
		Assert.assertEquals(StringUtil.stripNot("a1b321", "12"), "121");
		Assert.assertNull(StringUtil.stripNot("a1b321", null));
		Assert.assertNull(StringUtil.stripNot("a1b321", "7"));
	}

	/**
	 * 
	 */
	@Test
	public void testStripQuote() {
		Assert.assertEquals(StringUtil.stripQuote(",123,", ",", true), "123");
		Assert.assertEquals(StringUtil.stripQuote(",123,", ",", false), "123");
		Assert.assertEquals(StringUtil.stripQuote(",123, ", ",", false), ",123, ");
		Assert.assertEquals(StringUtil.stripQuote(" 	,123, ", ",", true), "123");
		Assert.assertEquals(StringUtil.stripQuote(",", ",", true), ",");
	}

	/**
	 * 
	 */
	@Test
	public void testTrim() {
		Assert.assertEquals(StringUtil.trim(",123,", ","), "123");
		Assert.assertEquals(StringUtil.trim(", ,123,", ", &"), "123");
		Assert.assertEquals(StringUtil.trim("123", ", &"), "123");
		Assert.assertEquals(StringUtil.trim("123&", ", &"), "123");
		Assert.assertEquals(StringUtil.trim(" 123", ", &"), "123");
		Assert.assertEquals(StringUtil.trim("", ", &"), null);
	}

	/**
	 * test regexp matching utility
	 * 
	 */
	@Test
	public void testMatches() {
		Assert.assertFalse(StringUtil.matchesSimple(null, "(.+ )*(BB)( .+)*"));
		Assert.assertTrue(StringUtil.matchesSimple("a bb c", "(.+ )*(bb)( .+)*"));
		Assert.assertTrue(StringUtil.matchesSimple("b bb c", "(.* )*(bb)( .+)*"));
		Assert.assertTrue(StringUtil.matchesSimple("a bb", "(.+ )*(bb)( .+)*"));
		Assert.assertTrue(StringUtil.matchesSimple("bb", "(.+ )*(bb)( .+)*"));
		Assert.assertFalse(StringUtil.matchesSimple(" bb", "(.+ )*(bb)( .+)*"));
		Assert.assertFalse(StringUtil.matchesSimple("bb ", "(.+ )*(bb)( .+)*"));
		Assert.assertFalse(StringUtil.matchesSimple("a", "(.+ )*(bb)( .+)*"));
		Assert.assertFalse(StringUtil.matchesSimple("a c", "(.+ )*(bb)( .+)*"));
		Assert.assertFalse(StringUtil.matchesSimple("a b c", "(.+ )*(bb)( .+)*"));
		Assert.assertFalse(StringUtil.matchesSimple("123456", "\\d{5,5}"));
		Assert.assertFalse(StringUtil.matchesSimple("1234", "\\d{5,5}"));
		Assert.assertTrue(StringUtil.matchesSimple("12345", "\\d{5,5}"));
		Assert.assertTrue(StringUtil.matchesSimple("abc", "*"));
		Assert.assertTrue(StringUtil.matchesSimple("abc", "?*"));
		Assert.assertTrue(StringUtil.matchesSimple("abc", "?+"));
		Assert.assertTrue(StringUtil.matchesSimple("abc", ""));
		Assert.assertTrue(StringUtil.matchesSimple("äbc", "???"));
		Assert.assertFalse(StringUtil.matchesSimple("abc", "????"));
		Assert.assertFalse(StringUtil.matchesSimple("abc", "??"));
		Assert.assertTrue(StringUtil.matchesSimple("€bc", null));
		Assert.assertTrue(StringUtil.matchesSimple("€", "(€)?"));
		Assert.assertTrue(StringUtil.matchesSimple("€€", "€{0,2}"));
		Assert.assertFalse(StringUtil.matchesSimple("€€€", "€{0,2}"));
		Assert.assertTrue(StringUtil.matchesSimple("", "(€)?"));
		Assert.assertTrue(StringUtil.matchesSimple("12de", JDFConstants.REGEXP_HEXBINARY));
		Assert.assertFalse(StringUtil.matchesSimple("12d", JDFConstants.REGEXP_HEXBINARY));
		Assert.assertFalse(StringUtil.matchesSimple("12dk", JDFConstants.REGEXP_HEXBINARY));

		Assert.assertTrue(StringUtil.matchesSimple("€", "(€)?"));

		Assert.assertFalse(StringUtil.matchesSimple("abc", "??"));
		Assert.assertFalse(StringUtil.matchesSimple(null, null));
		Assert.assertFalse(StringUtil.matchesSimple("abc", "?"));
		Assert.assertTrue(StringUtil.matchesSimple("a b", "(a)?( b)?( c)?"));
		Assert.assertTrue(StringUtil.matchesSimple("a b", "(a)? (b)?"));
		Assert.assertTrue(StringUtil.matchesSimple("a b", "a?(( )*b)?"));
		Assert.assertTrue(StringUtil.matchesSimple("a", "a?(( )*b)?"));
		Assert.assertTrue(StringUtil.matchesSimple("b", "a?(( )*b)?"));
		Assert.assertTrue(StringUtil.matchesSimple("b a c", "((.+ )*((a)|(b))( .+)*)+"));
		Assert.assertTrue(StringUtil.matchesSimple("b a c", "((.+ )*((a)|(b))( .+)*){1,1}"));
		Assert.assertFalse(StringUtil.matchesSimple("d e c", "((.+ )*((a)|(b))( .+)*)+"));
		Assert.assertFalse(StringUtil.matchesSimple("b b", "a?(( )*b)?"));
		Assert.assertTrue(StringUtil.matchesSimple("MIS_L2-1.3", "((.+ )*((MIS_L2-1.3)|(MISCPS_L1-1.3))( .+)*)+"));

		Assert.assertTrue(StringUtil.matchesSimple("a-aB.3@b.c", JDFConstants.REGEXP_EMAIL));
		Assert.assertTrue(StringUtil.matchesSimple("a@b.c", JDFConstants.REGEXP_EMAIL));
		Assert.assertTrue(StringUtil.matchesSimple("mailto:a@b.c", JDFConstants.REGEXP_EMAIL));
		Assert.assertFalse(StringUtil.matchesSimple("mailt:a@b.c", JDFConstants.REGEXP_EMAIL));
		Assert.assertTrue(StringUtil.matchesSimple("aa@b.c", JDFConstants.REGEXP_EMAIL));
		Assert.assertFalse(StringUtil.matchesSimple("a@b", JDFConstants.REGEXP_EMAIL));
		Assert.assertTrue(StringUtil.matchesSimple("+(1).2/344", JDFConstants.REGEXP_PHONE));
		Assert.assertFalse(StringUtil.matchesSimple("+(1).2 344", JDFConstants.REGEXP_PHONE));

		Assert.assertTrue(StringUtil.matchesSimple("ab", "a*"));
		Assert.assertTrue(StringUtil.matchesSimple("ab", "a(.*)"));
		Assert.assertTrue(StringUtil.matchesSimple("a", "a*"));
		Assert.assertFalse(StringUtil.matchesSimple("a", "a(.(.*))"));
		Assert.assertTrue(StringUtil.matchesSimple("a", "a(b)?"));
		Assert.assertTrue(StringUtil.matchesSimple("ab", "a(b)?"));
		Assert.assertFalse(StringUtil.matchesSimple("ac", "a(b)?"));

		Assert.assertTrue(StringUtil.matchesSimple("a b", "a b"));
		Assert.assertTrue(StringUtil.matchesSimple("abc123ä", "abc123ä"));
		Assert.assertTrue(StringUtil.matchesSimple("GangBrochureA4", "(Gang)?Bro(.)*"));
	}

	/**
	 * test regexp matching utility
	 * 
	 */
	@Test
	public void testMatchesRegExp() {
		Assert.assertFalse(StringUtil.matches(null, "(.+ )*(BB)( .+)*"));
		Assert.assertTrue(StringUtil.matches("a bb c", "(.+ )*(bb)( .+)*"));
		Assert.assertTrue(StringUtil.matches("b bb c", "(.* )*(bb)( .+)*"));
		Assert.assertTrue(StringUtil.matches("a bb", "(.+ )*(bb)( .+)*"));
		Assert.assertTrue(StringUtil.matches("bb", "(.+ )*(bb)( .+)*"));
		Assert.assertFalse(StringUtil.matches(" bb", "(.+ )*(bb)( .+)*"));
		Assert.assertFalse(StringUtil.matches("bb ", "(.+ )*(bb)( .+)*"));
		Assert.assertFalse(StringUtil.matches("a", "(.+ )*(bb)( .+)*"));
		Assert.assertFalse(StringUtil.matches("a c", "(.+ )*(bb)( .+)*"));
		Assert.assertFalse(StringUtil.matches("a b c", "(.+ )*(bb)( .+)*"));
		Assert.assertFalse(StringUtil.matches("123456", "\\d{5,5}"));
		Assert.assertFalse(StringUtil.matches("1234", "\\d{5,5}"));
		Assert.assertTrue(StringUtil.matches("12345", "\\d{5,5}"));
		Assert.assertTrue(StringUtil.matches("abc", ".*"));
		Assert.assertTrue(StringUtil.matches("abc", ".?.*"));
		Assert.assertTrue(StringUtil.matches("abc", ".+"));
		Assert.assertTrue(StringUtil.matches("abc", ""));
		Assert.assertTrue(StringUtil.matches("€bc", null));
		Assert.assertTrue(StringUtil.matches("€", "(€)?"));
		Assert.assertTrue(StringUtil.matches("€€", "€{0,2}"));
		Assert.assertFalse(StringUtil.matches("€€€", "€{0,2}"));
		Assert.assertTrue(StringUtil.matches("", "(€)?"));
		Assert.assertTrue(StringUtil.matches("12de", JDFConstants.REGEXP_HEXBINARY));
		Assert.assertFalse(StringUtil.matches("12d", JDFConstants.REGEXP_HEXBINARY));
		Assert.assertFalse(StringUtil.matches("12dk", JDFConstants.REGEXP_HEXBINARY));

		Assert.assertTrue(StringUtil.matches("€", "(€)?"));

		Assert.assertFalse(StringUtil.matches(null, null));
		Assert.assertFalse(StringUtil.matches("abc", "?"));
		Assert.assertTrue(StringUtil.matches("a b", "(a)?( b)?( c)?"));
		Assert.assertTrue(StringUtil.matches("a b", "(a)? (b)?"));
		Assert.assertTrue(StringUtil.matches("a b", "a?(( )*b)?"));
		Assert.assertTrue(StringUtil.matches("a", "a?(( )*b)?"));
		Assert.assertTrue(StringUtil.matches("b", "a?(( )*b)?"));
		Assert.assertTrue(StringUtil.matches("b a c", "((.+ )*((a)|(b))( .+)*)+"));
		Assert.assertTrue(StringUtil.matches("b a c", "((.+ )*((a)|(b))( .+)*){1,1}"));
		Assert.assertFalse(StringUtil.matches("d e c", "((.+ )*((a)|(b))( .+)*)+"));
		Assert.assertFalse(StringUtil.matches("b b", "a?(( )*b)?"));
		Assert.assertTrue(StringUtil.matches("MIS_L2-1.3", "((.+ )*((MIS_L2-1.3)|(MISCPS_L1-1.3))( .+)*)+"));

		Assert.assertTrue(StringUtil.matches("a-aB.3@b.c", JDFConstants.REGEXP_EMAIL));
		Assert.assertTrue(StringUtil.matches("a@b.c", JDFConstants.REGEXP_EMAIL));
		Assert.assertTrue(StringUtil.matches("mailto:a@b.c", JDFConstants.REGEXP_EMAIL));
		Assert.assertFalse(StringUtil.matches("mailt:a@b.c", JDFConstants.REGEXP_EMAIL));
		Assert.assertTrue(StringUtil.matches("aa@b.c", JDFConstants.REGEXP_EMAIL));
		Assert.assertFalse(StringUtil.matches("a@b", JDFConstants.REGEXP_EMAIL));
		Assert.assertTrue(StringUtil.matches("+(1).2/344", JDFConstants.REGEXP_PHONE));
		Assert.assertFalse(StringUtil.matches("+(1).2 344", JDFConstants.REGEXP_PHONE));

		Assert.assertTrue(StringUtil.matches("ab", "a.*"));
		Assert.assertTrue(StringUtil.matches("ab", "a(.*)"));
		Assert.assertTrue(StringUtil.matches("a", "a*"));
		Assert.assertFalse(StringUtil.matches("a", "a(.(.*))"));
		Assert.assertTrue(StringUtil.matches("a", "a(b)?"));
		Assert.assertTrue(StringUtil.matches("ab", "a(b)?"));
		Assert.assertFalse(StringUtil.matches("ac", "a(b)?"));

		Assert.assertTrue(StringUtil.matches("a b", "a b"));
		Assert.assertTrue(StringUtil.matches("abc123ä", "abc123ä"));
		Assert.assertTrue(StringUtil.matches("GangBrochureA4", "(Gang)?Bro(.)*"));
	}

	/**
	 * 
	 */
	@Test
	public void testNumOccurrences() {
		Assert.assertEquals(StringUtil.numSubstrings("a", "aa"), 0);
		Assert.assertEquals(StringUtil.numSubstrings("aa", "aa"), 1);
		Assert.assertEquals(StringUtil.numSubstrings("aaa", "aa"), 2);
		Assert.assertEquals(StringUtil.numSubstrings("aa a", "aa"), 1);
		Assert.assertEquals(StringUtil.numSubstrings("ab/>", "/>"), 1);
		Assert.assertEquals(StringUtil.numSubstrings("aa a", ""), 0);
		Assert.assertEquals(StringUtil.numSubstrings("aa a", null), 0);
		Assert.assertEquals(StringUtil.numSubstrings(null, "a"), 0);
		Assert.assertEquals(StringUtil.numSubstrings(null, null), 0);
	}

	/**
	 * 
	 */
	@Test
	public void testRegExpPerformance() {
		CPUTimer ct = new CPUTimer(false);
		int b = 0;
		for (int i = 0; i < 100000; i++) {
			ct.start();
			if (StringUtil.matches("abc" + i, "(.)?bc(1|2)?00(1)?"))
				b++;
			ct.stop();
		}
		System.out.print(ct.toString());
		Assert.assertTrue(b > 2);
	}

	/**
	 * 
	 */
	@Test
	public void testReplaceString() {
		Assert.assertEquals(StringUtil.replaceString("abbcc", "a", "_"), "_bbcc");
		Assert.assertEquals(StringUtil.replaceString("abbcc", "aa", "_"), "abbcc");
		Assert.assertEquals(StringUtil.replaceString("abbcc", "b", "_"), "a__cc");
		Assert.assertEquals(StringUtil.replaceString("abbcc", "bb", "_"), "a_cc");
		Assert.assertEquals(StringUtil.replaceString("abbcc", "c", "_"), "abb__");
		Assert.assertEquals(StringUtil.replaceString("abbcc", "cc", "_"), "abb_");
		Assert.assertEquals(StringUtil.replaceString("abbcc", "bb", null), "acc");
		Assert.assertEquals(StringUtil.replaceString("000000", "00", "0"), "0");
		Assert.assertEquals(StringUtil.replaceString("000", "0", "00"), "000000");
	}

	/**
	 * 
	 */
	@Test
	public void testStringReplacer() {
		StringReplacer sr = new StringReplacer("000000");
		Assert.assertEquals(sr.replaceString("00", "0"), "0");
		sr = new StringReplacer("000000");
		sr.setReRead(false);
		Assert.assertEquals(sr.replaceString("00", "0"), "000");
	}

	/**
	 * 
	 */
	@Test
	public void testReplaceChar() {
		Assert.assertEquals(StringUtil.replaceChar("abbcc", 'a', null, 0), "bbcc");
		Assert.assertEquals(StringUtil.replaceChar("abbcc", 'b', null, 0), "acc");
		Assert.assertEquals(StringUtil.replaceChar("abbcc", 'b', null, 2), "abcc");
		Assert.assertEquals(StringUtil.replaceChar("abbcc", 'b', "_2", 2), "ab_2cc");
	}

	/**
	 * 
	 */
	@Test
	public void testReplaceCharSet() {
		Assert.assertEquals(StringUtil.replaceCharSet("abbcc", "ab", null, 0), "cc");
		Assert.assertEquals(StringUtil.replaceCharSet("abbcc", "ab", "_", 0), "___cc");
		Assert.assertEquals(StringUtil.replaceCharSet("abbcc", "ab", "_", 2), "ab_cc");
	}

	/**
	 * 
	 */
	@Test
	public void testRightString() {
		Assert.assertEquals(StringUtil.rightStr("abbcc", 2), "cc");
		Assert.assertEquals(StringUtil.rightStr("abbcc", -3), "cc");
		Assert.assertNull(StringUtil.rightStr("abbcc", -5));
		Assert.assertNull(StringUtil.rightStr(null, -5));
		Assert.assertNull(StringUtil.rightStr("abc", -55));
	}

	/**
	 * 
	 */
	@Test
	public void testLeftString() {
		Assert.assertEquals(StringUtil.leftStr("abbcc", 2), "ab");
		Assert.assertEquals(StringUtil.leftStr("abbcc", -2), "abb");
		Assert.assertNull(StringUtil.leftStr("abbcc", -5));
		Assert.assertNull(StringUtil.leftStr(null, -5));
		Assert.assertNull(StringUtil.leftStr("abc", -55));
	}

	/**
	 * 
	 */
	@Test
	public void testSubstring() {
		// final String s = "a".substring(0, 0);
		Assert.assertEquals(StringUtil.substring("abbcc", 0, 2), "ab");
		Assert.assertEquals(StringUtil.substring("abbcc", 0, -2), "abb");
		Assert.assertEquals(StringUtil.substring("abbcc", -2, -2), "");
		Assert.assertEquals(StringUtil.substring("abbcc", -3, 0), "bcc");
		Assert.assertNull(StringUtil.substring("abbcc", 0, -6));
		Assert.assertNull(StringUtil.substring(null, 0, -5));
		Assert.assertNull(StringUtil.substring("abc", 0, -55));
	}

	/**
	 * 
	 */
	@Test
	public void testParseLong() {
		Assert.assertEquals(StringUtil.parseLong("", 0L), 0L);
		Assert.assertEquals(StringUtil.parseLong("1234567890123456", 0L), 1234567890123456L);
		Assert.assertEquals(StringUtil.parseLong("INF", 0L), Long.MAX_VALUE);
		Assert.assertEquals(StringUtil.parseLong("-inf", 0L), Long.MIN_VALUE);
		Assert.assertEquals(StringUtil.parseLong("12341234561234567834556", 0), Long.MAX_VALUE);
		Assert.assertEquals(StringUtil.parseLong("-12341234561234567834556", 0), Long.MIN_VALUE);
		Assert.assertEquals(StringUtil.parseLong("-1.0e44", 0), Long.MIN_VALUE);
		Assert.assertEquals(StringUtil.parseLong("-0xa", 0), -10);
		Assert.assertEquals(StringUtil.parseLong("0xff", 0), 255);
	}

	/**
	 * 
	 */
	@Test
	public void testParseInt() {
		Assert.assertEquals(StringUtil.parseInt("", 0), 0);
		Assert.assertEquals(StringUtil.parseInt("1234123456", 0), 1234123456);
		Assert.assertEquals(StringUtil.parseInt("1234123456.0", 0), 1234123456);
		Assert.assertEquals(StringUtil.parseInt("12341234561234567834556", 0), Integer.MAX_VALUE);
		Assert.assertEquals(StringUtil.parseInt("-12341234561234567834556", 0), Integer.MIN_VALUE);
		Assert.assertEquals(StringUtil.parseInt("INF", 0), Integer.MAX_VALUE);
		Assert.assertEquals(StringUtil.parseInt("-inf", 0), Integer.MIN_VALUE);
		Assert.assertEquals(StringUtil.parseInt("0xf", 0), 15);
		Assert.assertEquals(StringUtil.parseInt("-0xf", 0), -15);
	}

	/**
	 * 
	 */
	@Test
	public void testParseBoolean() {
		Assert.assertEquals(StringUtil.parseBoolean("", false), false);
		Assert.assertEquals(StringUtil.parseBoolean("", true), true);
		Assert.assertEquals(StringUtil.parseBoolean("TRUE ", false), true);
		Assert.assertEquals(StringUtil.parseBoolean(" FalSe ", true), false);
	}

	/**
	 * 
	 */
	@Test
	public void testParseDouble() {
		String s = "INF";
		Assert.assertEquals(StringUtil.parseDouble(s, 0), Double.MAX_VALUE, 0.0);
		Assert.assertTrue(StringUtil.isNumber(s));
		s = "-INF";
		Assert.assertEquals(StringUtil.parseDouble(s, 0), -Double.MAX_VALUE, 0.0);
		Assert.assertTrue(StringUtil.isNumber(s));
		s = "123.45e3 ";
		Assert.assertEquals(StringUtil.parseDouble(s, 0), 123450., 0.);
		Assert.assertTrue(StringUtil.isNumber(s));
		Assert.assertEquals(StringUtil.parseDouble(s, 0), 123450., 0.);
		Assert.assertTrue(StringUtil.isNumber(s));
		s = "123.45E3";
		Assert.assertEquals(StringUtil.parseDouble(s, 0), 123450., 0.);
		Assert.assertTrue(StringUtil.isNumber(s));
		s = "123.45";
		Assert.assertEquals(StringUtil.parseDouble(s, 0), 123.450, 0.);
		Assert.assertTrue(StringUtil.isNumber(s));
		s = "-123.45";
		Assert.assertEquals(StringUtil.parseDouble(s, 0), -123.450, 0.);
		Assert.assertTrue(StringUtil.isNumber(s));
		s = ".45";
		Assert.assertEquals("missing leading zero ok", StringUtil.parseDouble(s, 0), 0.450, 0.);
		Assert.assertTrue(StringUtil.isNumber(s));
		s = "-123.45a";
		Assert.assertEquals(StringUtil.parseDouble(s, 0.), 0., 0.);
		Assert.assertFalse(StringUtil.isNumber(s));
		s = "";
		Assert.assertEquals(StringUtil.parseDouble(s, 0.), 0., 0.);
		Assert.assertFalse(StringUtil.isNumber(s));
		s = "1,2";
		Assert.assertEquals("gracefully handle ',' as a separator char", StringUtil.parseDouble(s, 0.), 1.2, 0.);
		Assert.assertTrue("gracefully handle ',' as a separator char", StringUtil.isNumber(s));
		s = null;
		Assert.assertEquals(StringUtil.parseDouble(s, 0.), 0., 0.);
		Assert.assertFalse(StringUtil.isNumber(s));
	}

	/**
	 * 
	 */
	@Test
	public void testFind_last_not_of() {
		Assert.assertEquals(StringUtil.find_last_not_of("abc", "bcd"), 0);
		Assert.assertEquals(StringUtil.find_last_not_of("abc", "abc"), -1);
		Assert.assertEquals(StringUtil.find_last_not_of("abc", "ac"), 1);
		Assert.assertEquals(StringUtil.find_last_not_of("grün", "ï¿½ï¿½ï¿½"), 3);
		Assert.assertEquals(StringUtil.find_last_not_of("abc", "_"), 2);
	}

	/**
	 * 
	 */
	@Test
	public void testFormatLong() {
		long l = 13;
		while (l > 0) // breaks over top
		{
			l *= 7;
			Assert.assertEquals(StringUtil.parseLong(StringUtil.formatLong(l), -1), l);
		}
	}

	/**
	* 
	*/
	@Test
	public void testFormatDouble() {
		double d = 0.12345678901234;
		String s = StringUtil.formatDouble(d);
		Assert.assertEquals("s=6", "0.12345679", s);
		d = 0.12345678;
		s = StringUtil.formatDouble(d);
		Assert.assertEquals("s=6", "0.12345678", s);
		d = 0.123456789;
		s = StringUtil.formatDouble(d);
		Assert.assertEquals("s=6", "0.12345679", s);
		d = 0.12345673;
		s = StringUtil.formatDouble(d);
		Assert.assertEquals("s=5", "0.12345673", s);
		d = 234.0;
		s = StringUtil.formatDouble(d);
		Assert.assertEquals("s=int", "234", s);
		d = 123.456e4;
		s = StringUtil.formatDouble(d);
		Assert.assertEquals("s=int", "1234560", s);
		Assert.assertEquals("s=real small", "0", StringUtil.formatDouble(0.1e-20));
		Assert.assertEquals("s=real small -", "0", StringUtil.formatDouble(-0.1e-20));
		Assert.assertEquals("s=1+epsilon", "1", StringUtil.formatDouble(1.000000001));
		Assert.assertEquals("s=1-epsilon", "1", StringUtil.formatDouble(0.99999999987));
		Assert.assertNotSame("s=1-epsilon", "1", StringUtil.formatDouble(0.99949999987));
	}

	// /////////////////////////////////////////////////////////////////////////

	/**
	 * 
	 */
	@Test
	public void testuncToUrl() {
		if (FileUtil.isWindows()) {
			final String unc = "\\\\myHost\\a\\.\\b\\..\\c äöü%.txt";
			final String iri = "file://myHost/a/c%20äöü%25.txt";
			final String uri = "file://myHost/a/c%20%c3%a4%c3%b6%c3%bc%25.txt";

			Assert.assertEquals("uri ok", StringUtil.uncToUrl(unc, true), uri);
			Assert.assertEquals("iri ok", StringUtil.uncToUrl(unc, false), iri);
		}
	}

	// /////////////////////////////////////////////////////////////////////////

	/**
	 * 
	 */
	@Test
	public void testZappTokenWS() {
		Assert.assertEquals("new string", "1 2 3~4", StringUtil.zappTokenWS(" 1 2 3~    4 ", JDFConstants.TILDE));
		Assert.assertEquals("new string", "1 2 3~4", StringUtil.zappTokenWS(" 1 2 3~    4", JDFConstants.TILDE));
		Assert.assertEquals("new string", "1 2 3~4", StringUtil.zappTokenWS(" 1 2 3~    4~", JDFConstants.TILDE));
		Assert.assertEquals(StringUtil.zappTokenWS(" n2 ", null), "n2");
	}

	// /////////////////////////////////////////////////////////////////////////

	/**
	 * 
	 */
	@Test
	public void testHasToken() {
		Assert.assertFalse(StringUtil.hasToken("1", "1", null, 1));
		final String s = "1 2 3 3 \n15\n4";
		Assert.assertFalse(StringUtil.hasToken(s, "0", " \n", 0));
		Assert.assertTrue(StringUtil.hasToken(s, "1", " ", 0));
		Assert.assertFalse(StringUtil.hasToken("", "1", null, 0));
		Assert.assertTrue(StringUtil.hasToken(s, "1", null, 0));
		Assert.assertFalse(StringUtil.hasToken(s, "5", " ", 0));
		Assert.assertFalse(StringUtil.hasToken(s, "15", " ", 0));
		Assert.assertTrue(StringUtil.hasToken(s, "2", " ", 0));
		Assert.assertTrue(StringUtil.hasToken(s, "4", "\n ", 0));
		Assert.assertTrue(StringUtil.hasToken(s, "3", " ", 0));
		Assert.assertTrue(StringUtil.hasToken(s, "3", " ", 1));
		Assert.assertFalse(StringUtil.hasToken(s, "3", " ", 2));
		Assert.assertFalse(StringUtil.hasToken(s, "3", " ", 99));
		Assert.assertFalse(StringUtil.hasToken(null, "0", " ", 0));
		Assert.assertFalse(StringUtil.hasToken("ab", "a", " ", 0));
		Assert.assertFalse(StringUtil.hasToken("ab", "b", " ", 0));
		Assert.assertFalse(StringUtil.hasToken("abab", "ab", " ", 0));
		Assert.assertFalse(StringUtil.hasToken("ababa", "ab", " ", 0));
		Assert.assertTrue(StringUtil.hasToken("abc ab", "ab", " ", 0));
		Assert.assertTrue(StringUtil.hasToken("abc ab ", "ab", " ", 0));
		Assert.assertTrue(StringUtil.hasToken("abc ab abd", "ab", " ", 0));
		Assert.assertTrue("string matches are always true for 0", StringUtil.hasToken("a", "a", " ", 0));
		Assert.assertTrue("string matches are always true for -1", StringUtil.hasToken("a", "a", ", ", -1));

	}

	/**
	 * 
	 */
	@Test
	public void testHasTokenPerformance() {
		final String s = "1 2 33 3 \n15\n4";
		final String s2 = "ab123456 ab1234567 ab 1234568";
		long t0 = System.currentTimeMillis();
		Assert.assertTrue(StringUtil.hasToken(s, "33", " ", 0));
		Assert.assertFalse(StringUtil.hasToken(s, "34", " ", 0));
		Assert.assertFalse(StringUtil.hasToken(s2, "ab12345679", " ", 0));
		Assert.assertTrue(StringUtil.hasToken(s2, "ab1234567", " ", 0));
		Assert.assertTrue(StringUtil.hasToken(s2, "ab", " ", 0));
		for (int i = 0; i < 1000000; i++) {
			StringUtil.hasToken(s, "33", " ", 0);
			StringUtil.hasToken(s, "34", " ", 0);
			StringUtil.hasToken(s2, "ab12345679", " ", 0);
			StringUtil.hasToken(s2, "ab1234567", " ", 0);
			StringUtil.hasToken(s2, "ab", " ", 0);
		}
		long t2 = System.currentTimeMillis();
		System.out.println(t2 - t0);
	}

	// /////////////////////////////////////////////////////////////////////////

	/**
	 * 
	 */
	@Test
	public void testToken() {
		String s = "1 2 3 4";
		Assert.assertEquals(StringUtil.token(s, 0, " "), "1");
		Assert.assertEquals(StringUtil.token(s, 1, " "), "2");
		Assert.assertEquals(StringUtil.token(s, -3, " "), "2");
		Assert.assertEquals(StringUtil.token(s, -1, " "), "4");
		Assert.assertNull(StringUtil.token(s, 4, " "));
		Assert.assertNull(StringUtil.token(s, -5, " "));
		Assert.assertNull(StringUtil.token(null, 2, " "));
		s = "a/b?c";
		Assert.assertEquals(StringUtil.token(s, 0, "?/"), "a");
		Assert.assertEquals(StringUtil.token(s, 1, "?/"), "b");
		Assert.assertEquals(StringUtil.token(s, 2, "?/"), "c");
	}

	// /////////////////////////////////////////////////////////////////////////

	/**
	 * 
	 */
	@Test
	public void testTokenize() {
		final String s = " 1 2\n3 \n4   5";
		final VString v = new VString();
		v.add("1");
		v.add("2");
		v.add("3");
		v.add("4");
		v.add("5");
		Assert.assertEquals(StringUtil.tokenize(s, " \n", false), v);
		final String descName = "foobar( 1,2,3  ,4,5 )";
		final int pos2 = descName.lastIndexOf(')');
		final int pos1 = descName.lastIndexOf('(') + 1;
		Assert.assertEquals(v, StringUtil.tokenize(descName.substring(pos1, pos2), ", ", false));
	}

	/**
	 * 
	 */
	@Test
	public void testReplaceToken() {
		final String s = "a/b/c";
		Assert.assertEquals(StringUtil.replaceToken(s, 0, "/", "A"), "A/b/c");
		Assert.assertEquals(StringUtil.replaceToken(s, 0, "/", null), "b/c");
		Assert.assertEquals(StringUtil.replaceToken(s, -1, "/", null), "a/b");
		Assert.assertEquals(StringUtil.replaceToken(s, -1, "/", ""), "a/b/");
		Assert.assertEquals(StringUtil.replaceToken(s, -1, "/", "A"), "a/b/A");
		Assert.assertEquals(StringUtil.replaceToken(s, 1, "/", "A"), "a/A/c");
		Assert.assertEquals(StringUtil.replaceToken(s, 1, "/", null), "a/c");
		Assert.assertEquals(StringUtil.replaceToken(s, -5, "/", "A"), "a/b/c");
		final String s2 = "//a/b/c";
		Assert.assertEquals(StringUtil.replaceToken(s2, 0, "/", "A"), "//A/b/c");
		Assert.assertEquals(StringUtil.replaceToken(s2, 2, "/", null), "//a/b");
		final String s3 = "a";
		Assert.assertEquals(StringUtil.replaceToken(s3, 0, "/", "A"), "A");
		Assert.assertEquals(StringUtil.replaceToken(s3, 0, "/", null), "");
		final String s4 = "a_b";
		Assert.assertEquals(StringUtil.replaceToken(s4, 0, "_", "c"), "c_b");
		Assert.assertEquals(StringUtil.replaceToken(s4, 0, "_", null), "b");
	}

	/**
	 * 
	 */
	@Test
	public void testAddToken() {
		Assert.assertNull(StringUtil.addToken(null, "/", null));
		Assert.assertEquals(StringUtil.addToken("a", "/", "b"), "a/b");
		Assert.assertEquals(StringUtil.addToken("a/", "/", "b"), "a/b");
		Assert.assertEquals(StringUtil.addToken("a", "/", "/b"), "a/b");
		Assert.assertEquals(StringUtil.addToken("a/", "/", "/b"), "a/b");
		Assert.assertEquals(StringUtil.addToken("a/", "/", "//b"), "a/b");
		Assert.assertEquals(StringUtil.addToken("//a/", "/", "//b"), "//a/b");
	}

	/**
	 * 
	 */
	@Test
	public void testTokenizeDelim() {
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
		Assert.assertEquals(StringUtil.tokenize(s, "/?", true), v);
	}

	// /////////////////////////////////////////////////////////////////////////

	/**
	 * 
	 */
	@Test
	public void testConcatStrings() {
		final VString v = StringUtil.tokenize("a b c", " ", false);
		StringUtil.concatStrings(v, "_foo");
		Assert.assertEquals("a_foo b_foo c_foo", StringUtil.setvString(v, " ", null, null));
	}

	// /////////////////////////////////////////////////////////////////////////

	/**
	 * 
	 */
	@Test
	public void testCreateString() {
		ByteArrayIOStream ios = new ByteArrayIOStream("abc".getBytes());
		Assert.assertEquals(StringUtil.createString(ios.getInputStream()), "abc");
	}

	// /////////////////////////////////////////////////////////////////////////

	/**
	 * 
	 */
	@Test
	public void testEndsWithIgnoreCase() {
		final String s = "a.ZIP";
		Assert.assertTrue(s.toLowerCase().endsWith(".zip"));
		Assert.assertEquals(s, "a.ZIP");
	}

	// /////////////////////////////////////////////////////////////////////////

	/**
	 * 
	 */
	@Test
	public void testisEqual() {
		final double d = 1.3141516171819;
		double d2 = 0.00000000000001;
		double d3 = -0.000000000000011;

		while (d2 < 9999999999.9999) {
			d2 *= d;
			d3 *= d;
			Assert.assertTrue("" + d2, StringUtil.isEqual(d2, StringUtil.parseDouble(StringUtil.formatDouble(d2), 0.)));
			Assert.assertTrue("" + d3, StringUtil.isEqual(d3, StringUtil.parseDouble(StringUtil.formatDouble(d3), 0.)));
			Assert.assertFalse("" + d2, StringUtil.isEqual(d2, d2 * (1 + 1.1 * JDFBaseDataTypes.EPSILON) + JDFBaseDataTypes.EPSILON));
			Assert.assertFalse("" + d3, StringUtil.isEqual(d3, d3 * (1 + 1.1 * JDFBaseDataTypes.EPSILON) - JDFBaseDataTypes.EPSILON));
		}
		Assert.assertTrue("0.000001", StringUtil.isEqual(0.000000001, -0.000000001));
		Assert.assertTrue("int", StringUtil.isEqual(4, 4));
	}

	// /////////////////////////////////////////////////////////////////////////

	/**
	 * 
	 */
	@Test
	public void testCompareTo() {
		Assert.assertEquals(-1, StringUtil.compareTo(-3, -2));
		Assert.assertEquals(1, StringUtil.compareTo(3, 2));
		Assert.assertEquals(1, StringUtil.compareTo(3, 2));
		Assert.assertEquals(1, StringUtil.compareTo(3, 2));
		Assert.assertEquals(0, StringUtil.compareTo(2 + 0.5 * JDFBaseDataTypes.EPSILON, 2));
	}

	// /////////////////////////////////////////////////////////////////////////

	/**
	 * 
	 */
	@Test
	public void testIsNMTOKEN() {
		Assert.assertTrue(StringUtil.isNMTOKEN("abc"));
		Assert.assertTrue(StringUtil.isNMTOKEN("ä"));
		Assert.assertFalse(StringUtil.isNMTOKEN("$"));
		Assert.assertTrue(StringUtil.isNMTOKEN("öpü"));
		Assert.assertTrue(StringUtil.isNMTOKEN("1öpü2.:-_"));
		Assert.assertFalse(StringUtil.isNMTOKEN(" abc"));
		Assert.assertFalse(StringUtil.isNMTOKEN("a bc"));
		Assert.assertFalse(StringUtil.isNMTOKEN("a\nbc"));
		Assert.assertFalse(StringUtil.isNMTOKEN("\tabc"));
		Assert.assertFalse(StringUtil.isNMTOKEN("abc "));
	}

	// /////////////////////////////////////////////////////////////////////////
	/**
	 * 
	 */
	@Test
	public void testIsID() {
		Assert.assertTrue(StringUtil.isID("abc"));
		Assert.assertFalse(StringUtil.isID("1abc"));
	}

	// /////////////////////////////////////////////////////////////////////////

	/**
	 * 
	 */
	@Test
	public void testisWindowsLocalPath() {
		Assert.assertTrue(UrlUtil.isWindowsLocalPath("c:\\foo"));
		Assert.assertTrue(UrlUtil.isWindowsLocalPath("c:\\foo\\bar.abc"));
		Assert.assertTrue(UrlUtil.isWindowsLocalPath("d:foo"));
		Assert.assertFalse(UrlUtil.isWindowsLocalPath("\\\\foo\\bar"));
		Assert.assertFalse(UrlUtil.isWindowsLocalPath("c/d/e.f"));
		Assert.assertFalse(UrlUtil.isWindowsLocalPath("/c/d/e.f"));
	}

	// /////////////////////////////////////////////////////////////////////////

	/**
	 * 
	 */
	@Test
	public void testPathToName() {
		Assert.assertEquals(StringUtil.pathToName("\\\\foo\\bar"), "bar");
		Assert.assertEquals(StringUtil.pathToName("c:\\foo\\bar.txt"), "bar.txt");
		Assert.assertEquals(StringUtil.pathToName("c/foo/bar.txt"), "bar.txt");
		Assert.assertEquals(StringUtil.pathToName("bar.txt"), "bar.txt");
	}

	// /////////////////////////////////////////////////////////////////////////

	/**
	 * 
	 */
	@Test
	public void testGetNamesVector() {
		VString v = EnumUtil.getNamesVector(EnumType.AbortQueueEntry.getClass());
		Assert.assertTrue(v.contains("Resource"));
		v = EnumUtil.getNamesVector(EnumOrientation.Flip0.getClass());
		Assert.assertTrue(v.contains("Rotate90"));
	}

	// /////////////////////////////////////////////////////////////////////////

	/**
	 * 
	 */
	@Test
	public void testGetEnumsVector() {
		final Vector<ValuedEnum> v = EnumUtil.getEnumsVector(EnumOrientation.Flip180.getClass());
		Assert.assertTrue(v.contains(EnumOrientation.Rotate180));
	}

	// /////////////////////////////////////////////////////////////////////////
}
