/*
 * The CIP4 Software License, Version 1.0
 *
 *
 * Copyright (c) 2001-2006 The International Cooperation for the Integration of
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
    
    public void testStringToURL() throws Exception
    {
        assertEquals(UrlUtil.StringToURL("c:\\temp\\").getPath(), new URL("File:/c:/temp/").getPath());
//        assertEquals(UrlUtil.StringToURL("\\\\c\\temp\\").getPath(), new URL("File:/c/temp/").getPath());
        assertEquals(UrlUtil.StringToURL("File:/c:/temp/").getPath(), new URL("File:/c:/temp/").getPath());
//        assertEquals(UrlUtil.StringToURL("File:/c/temp/").getPath(), new URL("File:/c/temp/").getPath());
//        assertEquals(UrlUtil.StringToURL("cid:foo"), new URL("cid:foo"));
        assertEquals(UrlUtil.StringToURL("http://foo"), new URL("http://foo"));
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

}   
