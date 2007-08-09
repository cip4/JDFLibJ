/*
 * The CIP4 Software License, Version 1.0
 *
 *
 * Copyright (c) 2001-2007 The International Cooperation for the Integration of
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
import java.net.MalformedURLException;
import java.util.Vector;

import org.cip4.jdflib.JDFTestCaseBase;
import org.cip4.jdflib.core.JDFConstants;
import org.cip4.jdflib.core.VString;
import org.cip4.jdflib.core.JDFElement.EnumOrientation;
import org.cip4.jdflib.core.JDFResourceLink.EnumUsage;
import org.cip4.jdflib.datatypes.JDFBaseDataTypes;


/**
 * @author MatternK
 *
 * To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Generation - Code and Comments
 */
public class StringUtilTest extends JDFTestCaseBase
{
    public void testGetRelativePath()
    {
        File f=new File("./a");
        assertEquals(StringUtil.replaceChar(UrlUtil.getRelativePath(f, null),'\\',"/",0),"./a");
        f=new File("../a.b");
        assertEquals(StringUtil.replaceChar(UrlUtil.getRelativePath(f, null),'\\',"/",0),"../a.b");
        f=new File("./../a b/b");
        assertEquals(StringUtil.replaceChar(UrlUtil.getRelativePath(f, null),'\\',"/",0),"../a b/b");
        f=new File("a/b/c");
        assertEquals(StringUtil.replaceChar(UrlUtil.getRelativePath(f, null),'\\',"/",0),"./a/b/c");
        f=new File("a/b/c");
        assertEquals(StringUtil.replaceChar(UrlUtil.getRelativePath(f, f),'\\',"/",0),".");
    }


    public void testSprintfString()
    {
        assertEquals(StringUtil.sprintf("abc%03idef","5"), "abc005def");
        assertEquals(StringUtil.sprintf("abc%03idef","5.0"), "abc005def");
        assertEquals(StringUtil.sprintf("abc%03i%02idef","5.0,5"), "abc00505def");
        assertEquals(StringUtil.sprintf("abc%03i%02idef%%%s","5.0,5,abcdefghi"), "abc00505def%abcdefghi");
        assertEquals(StringUtil.sprintf("%2x","12"), " c");
        assertEquals(StringUtil.sprintf("%2x","18"), "12");
        assertEquals(StringUtil.sprintf("%s","\\,"), ",");
        assertEquals(StringUtil.sprintf("%s","\\\\,"), "\\,");
        assertEquals(StringUtil.sprintf("%s_%s","\\\\,,a"), "\\,_a");

    }

    public void testSprintf()
    {
        Object[] o=new Object[1];
        o[0]=new Integer(5);
        assertEquals(StringUtil.sprintf("abc%03idef",o), "abc005def");
        o[0]="foobar";
        assertEquals(StringUtil.sprintf("abc%7sdef",o), "abc foobardef");
        assertEquals(StringUtil.sprintf("abc%7s7def",o), "abc foobar7def");
        assertEquals(StringUtil.sprintf("%7sdef",o), " foobardef");
        assertEquals(StringUtil.sprintf("%7s",o), " foobar");
        assertEquals(StringUtil.sprintf("%%_%7s",o), "%_ foobar");
        assertEquals(StringUtil.sprintf("%%",o), "%");
        assertEquals(StringUtil.sprintf("765",o), "765");
        try
        {
            StringUtil.sprintf("abc%7idef",o);
            fail("foobar is an int?");
        }
        catch (Exception x)
        {
            //
        }

        o=new Object[]{new Integer(5),"foobar"};
        assertEquals(StringUtil.sprintf("abc %02i%7sdef",o), "abc 05 foobardef");
        assertEquals(StringUtil.sprintf("%02i%7sdef",o), "05 foobardef");
        o=new Object[]{"5","foobar"};
        assertEquals(StringUtil.sprintf("abc %02i%7sdef",o), "abc 05 foobardef");
        assertEquals(StringUtil.sprintf("%02i%7sdef",o), "05 foobardef");
    }

    public void testSetHexBinaryBytes()
    {
        String strTestString = "ABCDEFGHIJKLMNOPQESTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789ÖÄÜöäü€";
        byte[] buffer           = strTestString.getBytes();
        String strTemp          = StringUtil.setHexBinaryBytes(buffer, -1);
        byte[] tempBuffer       = StringUtil.getHexBinaryBytes(strTemp.getBytes());
        String strResultString  = new String(tempBuffer); 
        assertEquals("Input and Outputstring are not equal", strTestString,strResultString);
    }

    public void testSetUTF8Bytes()
    {
        //String strTestString = "€";
        String strTestString = "ABCDEFGHIJKLMNOPQESTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789ÖÄÜöäü€";
        byte[] utf8Buf          = StringUtil.setUTF8String(strTestString);
        String newString       = StringUtil.getUTF8String(utf8Buf);
        byte[] utf8Buf2          = StringUtil.setUTF8String(newString);
        String strResultString  = StringUtil.getUTF8String(utf8Buf2); 
        assertEquals("Input and Output bytes are not equal", utf8Buf.length,utf8Buf2.length);
        assertEquals("Input and Output string are not equal", strTestString,newString);
        assertEquals("Input and Output string are not equal", strTestString,strResultString);
    }

    public void testSetVStringEnum()
    {
        Vector v=new Vector();
        v.add(EnumUsage.Input);
        v.add(EnumUsage.Output);
        assertEquals(StringUtil.setvString(v, " ", null, null), "Input Output");
    }

    public void testSetVString()
    {
        VString v=new VString();
        v.add("1");
        v.add("22");
        v.add("333");
        v.add("4444");
        String s=StringUtil.setvString(v);

        assertEquals("s","1 22 333 4444",s);
        s=StringUtil.setvString(v,"","","");
        assertEquals("s","1223334444",s);
        s=StringUtil.setvString(v,null,null,null);
        assertEquals("s","1223334444",s);

        String[] a=new String[v.size()];
        a=(String[]) v.toArray(a);
        s=StringUtil.setvString(a," ",null,null);
        assertEquals("s","1 22 333 4444",s);
    }

    public void testEscape()
    {
        String iri="file://myHost/a/c%20äöü%25&?.txtß€";
        assertEquals("escape round trip",iri,StringUtil.unEscape(StringUtil.escape(iri,":&?%","%",16,2,0x21,127),"%",16,2));
        assertEquals("escape ","%25_%e4",StringUtil.escape("%_ä",":&?%","%",16,2,0x21,127));
        assertEquals("escape ","%e2%82%ac",StringUtil.escape(new String(StringUtil.setUTF8String("€")),":&?%","%",16,2,0x21,127));
        assertEquals("ß",StringUtil.escape("ß",null,"%",16,2,0x21,-1));
        assertEquals("€",StringUtil.escape("€",null,"%",16,2,0x21,-1));
        assertEquals("a_a",StringUtil.escape("aäa",null,"_",-1,0,0x21,127));
        assertEquals("a__a",StringUtil.escape("aä_a",null,"_",-1,0,0x21,127));
        assertEquals("a_äa",StringUtil.escape("aäa",null,"_",0,0,0x21,127));
    }

    public void testExtension()
    {
        String iri="file://my.Host/a.n/c%20äöü%25&?.txtß";
        assertEquals(UrlUtil.extension(iri),"txtß");
        assertNull(UrlUtil.extension("abc"));
    }
    /**
     * test regexp matching utility
     *
     */
    public void testMatchesIgnoreCase()
    {
        assertFalse(StringUtil.matchesIgnoreCase(null,"(.+ )*(BB)( .+)*"));
        assertTrue(StringUtil.matchesIgnoreCase("a bb c","(.+ )*(BB)( .+)*"));
        assertTrue(StringUtil.matchesIgnoreCase("mailTo:a@b.c",JDFConstants.REGEXP_EMAIL));
        assertFalse(StringUtil.matchesIgnoreCase("mailT:a@b.c",JDFConstants.REGEXP_EMAIL ));
    }
    /**
     * test regexp matching utility
     *
     */
    public void testStripPrefix()
    {
        assertEquals(StringUtil.stripPrefix("a:b", "a:", true),"b");
        assertEquals(StringUtil.stripPrefix("a:b", "A:", true),"b");
        assertEquals(StringUtil.stripPrefix("a:b", "A:", false),"a:b");
        assertEquals(StringUtil.stripPrefix("abcdef", "ABC", true),"def");
        assertNull(StringUtil.stripPrefix(null, "A:", false));
        assertEquals(StringUtil.stripPrefix("a:b", null, false),"a:b");
    }
    /**
     * test regexp matching utility
     *
     */
    public void testMatches()
    {
        assertFalse(StringUtil.matches(null,"(.+ )*(BB)( .+)*"));
        assertTrue(StringUtil.matches("a bb c","(.+ )*(bb)( .+)*"));
        assertTrue(StringUtil.matches("b bb c","(.* )*(bb)( .+)*"));
        assertTrue(StringUtil.matches("a bb","(.+ )*(bb)( .+)*"));
        assertTrue(StringUtil.matches("bb","(.+ )*(bb)( .+)*"));
        assertFalse(StringUtil.matches(" bb","(.+ )*(bb)( .+)*"));
        assertFalse(StringUtil.matches("bb ","(.+ )*(bb)( .+)*"));
        assertFalse(StringUtil.matches("a","(.+ )*(bb)( .+)*"));
        assertFalse(StringUtil.matches("a c","(.+ )*(bb)( .+)*"));
        assertFalse(StringUtil.matches("a b c","(.+ )*(bb)( .+)*"));
        assertTrue(StringUtil.matches("abc","*"));
        assertTrue(StringUtil.matches("abc",".*"));
        assertTrue(StringUtil.matches("abc",".+"));
        assertTrue(StringUtil.matches("abc",""));
        assertTrue(StringUtil.matches("äbc","..."));
        assertTrue(StringUtil.matches("äbc",null));
        assertTrue(StringUtil.matches("ä","ä?"));
        assertTrue(StringUtil.matches("","ä?"));
        assertTrue(StringUtil.matches("12de",JDFConstants.REGEXP_HEXBINARY));
        assertFalse(StringUtil.matches("12d",JDFConstants.REGEXP_HEXBINARY));
        assertFalse(StringUtil.matches("12dk",JDFConstants.REGEXP_HEXBINARY));
        assertFalse(StringUtil.matches("ö","ä?"));
        assertFalse(StringUtil.matches("abc",".."));
        assertFalse(StringUtil.matches(null,null));
        assertFalse(StringUtil.matches("abc","?"));
        assertTrue(StringUtil.matches("a b","(a)?( b)?( c)?"));
        assertTrue(StringUtil.matches("a b","a? b?"));
        assertTrue(StringUtil.matches("a b","a?(( )*b)?"));
        assertTrue(StringUtil.matches("a","a?(( )*b)?"));
        assertTrue(StringUtil.matches("b","a?(( )*b)?"));
        assertFalse(StringUtil.matches("b b","a?(( )*b)?"));

        assertTrue(StringUtil.matches("a-aB.3@b.c",JDFConstants.REGEXP_EMAIL));
        assertTrue(StringUtil.matches("a@b.c",JDFConstants.REGEXP_EMAIL ));
        assertTrue(StringUtil.matches("mailto:a@b.c",JDFConstants.REGEXP_EMAIL));
        assertFalse(StringUtil.matches("mailt:a@b.c",JDFConstants.REGEXP_EMAIL ));
        assertTrue(StringUtil.matches("aa@b.c",JDFConstants.REGEXP_EMAIL ));
        assertFalse(StringUtil.matches("a@b",JDFConstants.REGEXP_EMAIL ));
        assertTrue(StringUtil.matches("+(1).2/344",JDFConstants.REGEXP_PHONE));
        assertFalse(StringUtil.matches("+(1).2 344",JDFConstants.REGEXP_PHONE));        
    }


    public void testMime()
    {
        assertEquals(StringUtil.mime(null), JDFConstants.MIME_TEXTUNKNOWN);
        assertEquals(StringUtil.mime("foo.PDF"), JDFConstants.MIME_PDF);
    }


    public void testNewExtension()
    {
        assertEquals(StringUtil.newExtension("a.b","c"), "a.c");
        assertEquals(StringUtil.newExtension("a.b.c","c"), "a.b.c");
        assertEquals(StringUtil.newExtension("a.b",null), "a");
        assertEquals(StringUtil.newExtension("a.b",".c"), "a.c");
        assertEquals(StringUtil.newExtension("a.b",".c.d"), "a.c.d");
        assertEquals(StringUtil.newExtension("a.b","c.d"), "a.c.d");
        assertEquals(StringUtil.newExtension(".b",".c"), ".c");
    }

 


    public void testReplaceString()
    {
        assertEquals(StringUtil.replaceString("abbcc", "a", "_"), "_bbcc");
        assertEquals(StringUtil.replaceString("abbcc", "aa", "_"), "abbcc");
        assertEquals(StringUtil.replaceString("abbcc", "b", "_"), "a__cc");
        assertEquals(StringUtil.replaceString("abbcc", "bb", "_"), "a_cc");
        assertEquals(StringUtil.replaceString("abbcc", "c", "_"), "abb__");
        assertEquals(StringUtil.replaceString("abbcc", "cc", "_"), "abb_");
        assertEquals(StringUtil.replaceString("abbcc", "bb", null), "acc");
    }
    public void testReplaceChar()
    {
        assertEquals(StringUtil.replaceChar("abbcc", 'a',null, 0), "bbcc");
        assertEquals(StringUtil.replaceChar("abbcc", 'b',null, 0), "acc");
        assertEquals(StringUtil.replaceChar("abbcc", 'b',null, 2), "abcc");
        assertEquals(StringUtil.replaceChar("abbcc", 'b',"_2", 2), "ab_2cc");
    }
    public void testReplaceCharSet()
    {
        assertEquals(StringUtil.replaceCharSet("abbcc", "ab",null, 0), "cc");
        assertEquals(StringUtil.replaceCharSet("abbcc", "ab","_", 0), "___cc");
        assertEquals(StringUtil.replaceCharSet("abbcc", "ab","_", 2), "ab_cc");
    }


    public void testRightString()
    {
        assertEquals(StringUtil.rightStr("abbcc",2), "cc");
        assertEquals(StringUtil.rightStr("abbcc",-3), "cc");
        assertNull(StringUtil.rightStr("abbcc",-5));
        assertNull(StringUtil.rightStr(null,-5));
        assertNull(StringUtil.rightStr("abc",-55));
    }



    public void testLeftString()
    {
        assertEquals(StringUtil.leftStr("abbcc",2), "ab");
        assertEquals(StringUtil.leftStr("abbcc",-2), "abb");
        assertNull(StringUtil.leftStr("abbcc",-5));
        assertNull(StringUtil.leftStr(null,-5));
        assertNull(StringUtil.leftStr("abc",-55));
    }


    public void testParseDouble()
    {
        String s="123.45e3";
        assertEquals(StringUtil.parseDouble(s,0),123450.,0.);
        assertTrue(StringUtil.isNumber(s));
        s="123.45";
        assertEquals(StringUtil.parseDouble(s,0),123.450,0.);
        assertTrue(StringUtil.isNumber(s));
        s="-123.45";
        assertEquals(StringUtil.parseDouble(s,0),-123.450,0.);
        assertTrue(StringUtil.isNumber(s));
        s="-123.45a";
        assertEquals(StringUtil.parseDouble(s,0.),0.,0.);
        assertFalse(StringUtil.isNumber(s));
    }


    public void testFind_last_not_of()
    {
        assertEquals(StringUtil.find_last_not_of("abc", "bcd"), 0);
        assertEquals(StringUtil.find_last_not_of("abc", "abc"), -1);
        assertEquals(StringUtil.find_last_not_of("abc", "ac"), 1);
        assertEquals(StringUtil.find_last_not_of("grün", "äöü"), 3);
        assertEquals(StringUtil.find_last_not_of("abc", "_"), 2);
    }


    public void testFormatDouble()
    {
        double d=0.12345678901234;
        String s=StringUtil.formatDouble(d);
        assertEquals("s=6","0.12345678",s);
        d=0.12345678;
        s=StringUtil.formatDouble(d);
        assertEquals("s=6","0.12345678",s);
        d=0.123456789;
        s=StringUtil.formatDouble(d);
        assertEquals("s=6","0.12345678",s);
        d=0.1234567;
        s=StringUtil.formatDouble(d);
        assertEquals("s=5","0.1234567",s);
        d=234.0;
        s=StringUtil.formatDouble(d);
        assertEquals("s=int","234",s);
        d=123.456e4;
        s=StringUtil.formatDouble(d);
        assertEquals("s=int","1234560",s);       
        assertEquals("s=real small","0",StringUtil.formatDouble(0.1e-20));       
        assertEquals("s=real small -","0",StringUtil.formatDouble(-0.1e-20));       
    }

    ///////////////////////////////////////////////////////////////////////////

    public void testuncToUrl(){
        String unc="\\\\myHost\\a\\.\\b\\..\\c äöü%.txt";
        String iri="file://myHost/a/c%20äöü%25.txt";
        String uri="file://myHost/a/c%20%c3%a4%c3%b6%c3%bc%25.txt";

        try
        {
            assertEquals("uri ok",StringUtil.uncToUrl(unc,true),uri);
            assertEquals("iri ok",StringUtil.uncToUrl(unc,false),iri);
        }
        catch (MalformedURLException e)
        {
            assertFalse("malformed URL",true);
        }
    }

    ///////////////////////////////////////////////////////////////////////////

    public void testZappTokenWS()
    {
        String s=" 1 2 3~    4";
        s=StringUtil.zappTokenWS(s,JDFConstants.TILDE);
        assertEquals("new string","1 2 3~4",s);        
        assertEquals(StringUtil.zappTokenWS(" n2 ",null),"n2");        
    }

    ///////////////////////////////////////////////////////////////////////////

    public void testHasToken()
    {
        String s="1 2 3 3 15 4";
        assertFalse(StringUtil.hasToken(s,"0"," ",0));      
        assertTrue(StringUtil.hasToken(s,"1"," ",0));      
        assertFalse(StringUtil.hasToken(s,"5"," ",0));      
        assertTrue(StringUtil.hasToken(s,"2"," ",0));      
        assertTrue(StringUtil.hasToken(s,"4"," ",0));      
        assertTrue(StringUtil.hasToken(s,"3"," ",0));      
        assertTrue(StringUtil.hasToken(s,"3"," ",1));      
        assertFalse(StringUtil.hasToken(s,"3"," ",2));      
        assertFalse(StringUtil.hasToken(s,"3"," ",99));      
        assertFalse(StringUtil.hasToken(null,"0"," ",0));   
        assertFalse(StringUtil.hasToken("ab","a"," ",0));   
        assertFalse(StringUtil.hasToken("ab","b"," ",0));   

    }
    ///////////////////////////////////////////////////////////////////////////

    public void testToken()
    {
        String s="1 2 3 4";
        assertEquals(StringUtil.token(s,0," "),"1");      
        assertEquals(StringUtil.token(s,1," "),"2");      
        assertEquals(StringUtil.token(s,-3," "),"2");      
        assertEquals(StringUtil.token(s,-1," "),"4");      
        assertNull(StringUtil.token(s,4," "));      
        assertNull(StringUtil.token(s,-5," "));      
    }
    ///////////////////////////////////////////////////////////////////////////

    public void testTokenize()
    {
        String s="1 2 3";
        VString v=new VString();
        v.add("1");
        v.add("2");
        v.add("3");
        assertEquals(StringUtil.tokenize(s," ",false),v);      
    }

    ///////////////////////////////////////////////////////////////////////////

    public  void testConcatStrings()
    {
        VString v=StringUtil.tokenize("a b c"," ",false);
        StringUtil.concatStrings(v,"_foo");
        assertEquals("a_foo b_foo c_foo",StringUtil.setvString(v," ",null,null));
    }   
    ///////////////////////////////////////////////////////////////////////////

    public  void testEndsWithIgnoreCase()
    {
        String s="a.ZIP";
        assertTrue(s.toLowerCase().endsWith(".zip"));
        assertEquals(s,"a.ZIP");
    }   
    ///////////////////////////////////////////////////////////////////////////

    public  void testisEqual()
    {
        final double d=1.3141516171819;
        double d2=0.00000000000001;
        double d3=-0.000000000000011;
        
        while (d2<9999999999.9999)
        {
            d2*=d;
            d3*=d;
            assertTrue(""+d2,StringUtil.isEqual(d2, StringUtil.parseDouble(StringUtil.formatDouble(d2), 0.)));
            assertTrue(""+d3,StringUtil.isEqual(d3, StringUtil.parseDouble(StringUtil.formatDouble(d3), 0.)));
            assertFalse(""+d2,StringUtil.isEqual(d2, d2*(1+1.1*JDFBaseDataTypes.EPSILON)+JDFBaseDataTypes.EPSILON));
            assertFalse(""+d3,StringUtil.isEqual(d3, d3*(1+1.1*JDFBaseDataTypes.EPSILON)-JDFBaseDataTypes.EPSILON));
        }
        assertTrue("0.000001",StringUtil.isEqual(0.000000001,-0.000000001 ));
        assertTrue("int",StringUtil.isEqual(4,4 ));
    }
    ///////////////////////////////////////////////////////////////////////////

    public  void testCompareTo()
    {
        assertEquals(-1, StringUtil.compareTo(-3, -2));    
        assertEquals(1, StringUtil.compareTo(3, 2));    
        assertEquals(1, StringUtil.compareTo(3, 2));    
        assertEquals(1, StringUtil.compareTo(3, 2));    
        assertEquals(0, StringUtil.compareTo(2+0.5*JDFBaseDataTypes.EPSILON, 2));    
    }
    ///////////////////////////////////////////////////////////////////////////

    public  void testisWindowsLocalPath()
    {
        assertTrue(UrlUtil.isWindowsLocalPath("c:\\foo"));
        assertTrue(UrlUtil.isWindowsLocalPath("c:\\foo\\bar.abc"));
        assertTrue(UrlUtil.isWindowsLocalPath("d:foo"));
        assertFalse(UrlUtil.isWindowsLocalPath("\\\\foo\\bar"));
        assertFalse(UrlUtil.isWindowsLocalPath("c/d/e.f"));
        assertFalse(UrlUtil.isWindowsLocalPath("/c/d/e.f"));
    }   


    ///////////////////////////////////////////////////////////////////////////

    public  void testPathToName()
    {
        assertEquals(StringUtil.pathToName("\\\\foo\\bar"),"bar");
        assertEquals(StringUtil.pathToName("c:\\foo\\bar.txt"),"bar.txt");
        assertEquals(StringUtil.pathToName("c/foo/bar.txt"),"bar.txt");
        assertEquals(StringUtil.pathToName("bar.txt"),"bar.txt");
    }   

    ///////////////////////////////////////////////////////////////////////////

    public  void testGetNamesVector()
    {
        VString v=StringUtil.getNamesVector(EnumOrientation.class);        
        assertTrue(v.contains("Rotate90"));
    }   
    ///////////////////////////////////////////////////////////////////////////

    public  void testGetEnumsVector()
    {
        Vector v=StringUtil.getEnumsVector(EnumOrientation.class);        
        assertTrue(v.contains(EnumOrientation.Rotate180));
    }   

    ///////////////////////////////////////////////////////////////////////////

    ///////////////////////////////////////////////////////////////////////////

    ///////////////////////////////////////////////////////////////////////////

}   
