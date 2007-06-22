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

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.net.URL;

import org.cip4.jdflib.JDFTestCaseBase;



public class UrlUtilTest extends JDFTestCaseBase
{   



    ///////////////////////////////////////////////////////////////////////////
    public void testIsCid() throws Exception
    {
        assertTrue(UrlUtil.isCID("cid:foo"));
        assertTrue(UrlUtil.isCID("<cid:foo>"));
        assertTrue(UrlUtil.isCID("<cid:"));
        assertFalse(UrlUtil.isCID(null));
        assertFalse(UrlUtil.isCID("<"));
    }
    ///////////////////////////////////////////////////////////////////////////
    public void testIsHTTP() throws Exception
    {
        assertTrue(UrlUtil.isHttp("http://foo.bar.com"));
        assertFalse(UrlUtil.isHttp(null));
        assertFalse(UrlUtil.isHttp("foo.bar.com"));
    }
    ///////////////////////////////////////////////////////////////////////////
    public void testIsIRL() throws Exception
    {
        assertTrue(UrlUtil.isIRL("file://blöd€.txt"));
        assertTrue(UrlUtil.isIRL("http://foo.com/blöd€.txt"));
        assertFalse("3 ///",UrlUtil.isIRL("http:///blöd€.txt"));
        assertFalse("blank is bad",UrlUtil.isIRL("file://a blöd€.txt"));
        assertTrue("blank %20 is good",UrlUtil.isIRL("file://a%20blöd€.txt"));
        assertTrue(UrlUtil.isIRL("file:C:/a/b.txt"));
        assertTrue("relative url",UrlUtil.isIRL("./3€.txt"));
        assertFalse("invalid char: @",UrlUtil.isIRL("http://@"));
        assertTrue(UrlUtil.isIRL("HTTP://€/€"));
        assertTrue(UrlUtil.isIRL("file:///C:/Documents%20and%20Settings/Israel/My%20Documents/Vio%20Production/Results/TIME_H8789/TIME_H8789.pdf"));
    }
    public void testIsURL() throws Exception
    {
        assertTrue(UrlUtil.isURL("file://bl.txt"));
        assertTrue(UrlUtil.isURL("http://foo.com/bl.txt"));
        assertFalse("3 ///",UrlUtil.isURL("http:///bl.txt"));
        assertFalse("blank is bad",UrlUtil.isURL("file://a b.txt"));
        assertTrue("blank %20 is good",UrlUtil.isURL("file://a%20bl.txt"));
        assertTrue(UrlUtil.isURL("file:C:/a/b.txt"));
        assertTrue("relative url",UrlUtil.isURL("./3.txt"));
        assertFalse("invalid char: @",UrlUtil.isURL("http://@"));
        assertTrue(UrlUtil.isURL("HTTP://a/b?c"));
    }
    ///////////////////////////////////////////////////////////////////////////

    public void testStringToURL() throws Exception
    {

        // test for an existing directory (a trailing slash is appended by StringToURL)
        assertTrue(UrlUtil.StringToURL("c:\\temp\\").getPath().startsWith(new URL("File:/c:/temp").getPath()));
        assertTrue(UrlUtil.StringToURL("File:/c:/temp/").getPath().startsWith(new URL("File:/c:/temp").getPath()));
        assertTrue(UrlUtil.StringToURL("c:\\temp").getPath().startsWith( new URL("File:/c:/temp").getPath()));
        assertTrue(UrlUtil.StringToURL("File:/c:/temp").getPath().startsWith( new URL("File:/c:/temp").getPath()));


        // test a non existing file
        File file = File.createTempFile("Testfile", "");
        if (file.isFile())
        {
            // test for a file or a non existing object (trailing slash is removed by StringToURL)
            assertEquals(UrlUtil.StringToURL("c:\\xyz\\").getPath(), new URL("File:/c:/xyz").getPath());
            assertEquals(UrlUtil.StringToURL("File:/c:/xyz/").getPath(), new URL("File:/c:/xyz").getPath());
            assertEquals(UrlUtil.StringToURL("c:\\xyz").getPath(), new URL("File:/c:/xyz").getPath());
            assertEquals(UrlUtil.StringToURL("File:/c:/xyz").getPath(), new URL("File:/c:/xyz").getPath());

            file.delete();
        }

        assertEquals(UrlUtil.StringToURL("http://foo"), new URL("http://foo"));
    }
    ///////////////////////////////////////////////////////////////////////////

    public void testFileToURL() throws Exception
    {
        File f=new File("C:\\IO.SYS");
        String s=UrlUtil.fileToUrl(f, false);
        assertEquals(s,"file:///C:/IO.SYS");
        f=new File("\\IO.SYS");
        s=UrlUtil.fileToUrl(f, false);
        assertEquals(s,"file:///C:/IO.SYS");
    }     
    ///////////////////////////////////////////////////////////////////////////

    public void testURLToFile() throws Exception
    {
        String cwd=System.getProperty("user.dir");
        System.setProperty("user.dir", sm_dirTestDataTemp);
        File f=UrlUtil.urlToFile(".");
        assertTrue(f.isDirectory());
        File f2=UrlUtil.urlToFile(UrlUtil.fileToUrl(f, true));
        assertTrue(f2.isDirectory());
        assertEquals(f2.getCanonicalPath(), f.getCanonicalPath());

        f=new File(".\\simple.pdf");
        f2=UrlUtil.urlToFile(UrlUtil.fileToUrl(f, true));   
        assertEquals("asccii",f.getCanonicalPath(), f2.getCanonicalPath());

        f=new File("blöd .pdf");
        f2=UrlUtil.urlToFile(UrlUtil.fileToUrl(f, true));   
        assertEquals("non asccii",f.getCanonicalPath(), f2.getCanonicalPath());

        f=new File("blöd ist es 10@€.pdf");
        final String fileToUrl = UrlUtil.fileToUrl(f, true);
        f2=UrlUtil.urlToFile(fileToUrl);   
        assertEquals("escape %20",f.getCanonicalPath(), f2.getCanonicalPath());

        System.setProperty("user.dir", cwd);
    }
    ///////////////////////////////////////////////////////////////////////////

    public  void testisUNC()
    {
        assertTrue(UrlUtil.isUNC("\\\\foo\\bar"));
        assertFalse(UrlUtil.isUNC("c/d/e.f"));
        assertFalse(UrlUtil.isUNC("/c/d/e.f"));
    }   

    public void testGetRelativeURI()
    {
        File f=new File("./a b");
        assertEquals(StringUtil.replaceChar(UrlUtil.getRelativeURL(f, null, true),'\\',"/",0),"./a%20b");
        f=new File("../a.ä");
        assertEquals("escaped utf8", StringUtil.replaceChar(UrlUtil.getRelativeURL(f, null, true),'\\',"/",0),"../a.%c3%a4");
        assertEquals("unescaped but utf8",StringUtil.replaceChar(UrlUtil.getRelativeURL(f, null, false),'\\',"/",0),new String(StringUtil.setUTF8String("../a.ä")));
    }

    ///////////////////////////////////////////////////////////////////////////
    public void testGetRelativeURL()
    {
        File file=new File("c:\\a\\b\\c.txt");
        File cwd=new File("c:\\a\\b1");
        assertEquals(UrlUtil.getRelativeURL(file, cwd, true),"../b/c.txt");   
        file=new File("c:\\a\\b1\\c.txt");
        assertEquals(UrlUtil.getRelativeURL(file, cwd, true),"./c.txt");   
        file=new File("a\\..\\b\\c.txt");
        assertEquals(UrlUtil.getRelativeURL(file, null, true),"./b/c.txt");   
        file=cwd;
        assertEquals(UrlUtil.getRelativeURL(file, cwd, true),".");   
    }

    ///////////////////////////////////////////////////////////////////////////

    public void testGetURLWithDirectory()
    {
        String url="File://a.b";
        assertEquals("test nulls",url, UrlUtil.getURLWithDirectory(null, url));
        assertEquals("test nulls",url, UrlUtil.getURLWithDirectory("", url));
        assertEquals("test nulls",url, UrlUtil.getURLWithDirectory("File:/dir", url));

        url="a.b";
        assertEquals("relative url","File://dir/a.b", UrlUtil.getURLWithDirectory("File://dir/", url));
        assertEquals("relative url","File://dir/a.b", UrlUtil.getURLWithDirectory("File://dir", url));

        url="/a.b";
        assertEquals("absolute url no host","File://a.b", UrlUtil.getURLWithDirectory("File://dir/", url));
        assertEquals("absolute url no host","File://a.b", UrlUtil.getURLWithDirectory("File://dir", url));

        url="//a.b";
        assertEquals("absolute url with default host","File://a.b", UrlUtil.getURLWithDirectory("File://dir/", url));
        assertEquals("absolute url with default host","File://a.b", UrlUtil.getURLWithDirectory("File://dir", url));

        url="//boo/a.b";
        assertEquals("absolute url with new host","File://boo/a.b", UrlUtil.getURLWithDirectory("File://dir/", url));
        assertEquals("absolute url with new host","File://boo/a.b", UrlUtil.getURLWithDirectory("File://dir", url));

        url="//boo/./gg/../a.b";
        assertEquals("absolute url with new host and cleandots","File://boo/a.b", UrlUtil.getURLWithDirectory("File://dir/", url));
        assertEquals("absolute url with new host and cleandots","File://boo/a.b", UrlUtil.getURLWithDirectory("File://dir", url));
    }
    ///////////////////////////////////////////////////////////////////////////
    ///////////////////////////////////////////////////////////////////////////

    public void testCleanDots()
    {
        assertEquals(UrlUtil.cleanDots("."), ".");
        assertEquals(UrlUtil.cleanDots(".."), "..");
        assertEquals(UrlUtil.cleanDots("a/.."), ".");
        assertEquals(UrlUtil.cleanDots("../../c.pdf"), "../../c.pdf");
        assertEquals(UrlUtil.cleanDots(".././../c.pdf"), "../../c.pdf");
        assertEquals(UrlUtil.cleanDots("File://a/../b"), "File://b");
        assertEquals(UrlUtil.cleanDots("File://a/.././c/../b.pdf"), "File://b.pdf");
        assertEquals(UrlUtil.cleanDots("/."), "/.");
    }

}   
