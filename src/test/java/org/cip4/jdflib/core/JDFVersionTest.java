/*
 *
 * The CIP4 Software License, Version 1.0
 *
 *
 * Copyright (c) 2001-2017 The International Cooperation for the Integration of
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

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;

import java.lang.reflect.Method;

import org.apache.commons.lang.StringUtils;
import org.cip4.jdflib.JDFTestCaseBase;
import org.junit.Test;

/**
 * JUnit test case for JDFVersion.
 */
public class JDFVersionTest extends JDFTestCaseBase
{
    boolean canTest;
    
    @Test
    public void testLibArtifactId()
    {
        if (!canTest)
            return;
        
        // arrange
        String expected = "JDFLibJ";
        
        // act
        String actual = JDFVersion.LIB_ARTIFACT_ID;
        
        // assert
        assertEquals("ArtifactID number is wrong.", expected, actual);
        System.out.println("JDFLibJ ArtifactID: " + actual);
    }
    
    @Test
    public void testLibName()
    {
        if (!canTest)
            return;
        
        // arrange
        String expected = "CIP4 JDF Writer Java";
        
        // act
        String actual = JDFVersion.LIB_NAME;
        
        // assert
        assertEquals("Name number is wrong.", expected, actual);
        System.out.println("JDFLibJ Name: " + actual);
    }
    
    @Test
    public void testLibReleaseDate()
    {
        if (!canTest)
            return;
        
        // arrange
        
        // act
        String actual = JDFVersion.LIB_RELEASE_DATE;
        
        // assert
        //assertTrue("ReleaseDate is wrong.", actual.matches("(\\d{4})-(\\d{2})-(\\d{2}) (\\d{2}):(\\d{2})"));
        System.out.println("JDFLibJ Release Date: " + actual);
    }
    
    @Test
    public void testLibVersion()
    {
        if (!canTest)
            return;
        
        // arrange
        
        // act
        String actual = JDFVersion.LIB_VERSION;
        
        // assert
        assertFalse("Version is wrong.", StringUtils.isEmpty(actual));
        System.out.println("JDFLibJ Version: " + actual);
    }
    
    @Test
    public void testLibMajorVersion()
    {
        if (!canTest)
            return;
        
        // arrange
        
        // act
        String actual = JDFVersion.LIB_MAJOR_VERSION;
        
        // assert
        assertFalse("Version is wrong.", StringUtils.isEmpty(actual));
        assertEquals("Major Version is wrong.", 3, StringUtils.split(actual, ".").length);
        assertEquals("Major Version is wrong.", 1, StringUtils.split(actual, "-").length);
        System.out.println("JDFLibJ Major Version: " + actual);
    }
    
    @Test
    public void testLibMinorVersion()
    {
        
        if (!canTest)
            return;
        // arrange
        
        // act
        String actual = JDFVersion.LIB_MINOR_VERSION;
        
        // assert
        assertFalse("Version is wrong.", StringUtils.isEmpty(actual));
        assertEquals("Major Version is wrong.", 1, StringUtils.split(actual, ".").length);
        assertEquals("Major Version is wrong.", 1, StringUtils.split(actual, "-").length);
        System.out.println("JDFLibJ Major Version: " + actual);
    }
    
    @Test
    public void testJdfVersion()
    {
        if (!canTest)
            return;
        // arrange
        String expected = "1.5";
        
        // act
        String actual = JDFVersion.JDF_VERSION;
        
        // assert
        assertEquals("JDF Version number is wrong.", expected, actual);
        System.out.println("JDF Version: " + actual);
    }
    
    @Test
    public void testJdfVersion_LibVersion()
    {
        if (!canTest)
            return;
        
        // arrange
        String jdfVersion = JDFVersion.JDF_VERSION;
        String libVersion = JDFVersion.LIB_VERSION;
        
        // act
        int i = libVersion.indexOf(".") + 1;
        int n = libVersion.lastIndexOf(".");
        
        String extractedVersion = libVersion.substring(i, n);
        
        // assert
        assertEquals("JDF Version doesn't match the Lib Version.", jdfVersion, extractedVersion);
        System.out.println(String.format("JDF Version: %s - Lib Version: %s (OK)", jdfVersion, libVersion));
    }
    
    @Test
    public void testMinorVersion_1() throws Exception
    {
        if (!canTest)
            return;
        
        // arrange
        Method method = JDFVersion.class.getDeclaredMethod("getMinorVersion", String.class);
        method.setAccessible(true);
        
        String version = "2.1.4a.69";
        String expected = "69";
        
        // act
        String actual = (String) method.invoke(null, version);
        
        // assert
        assertEquals("Minor Version is wrong.", expected, actual);
    }
    
    @Test
    public void testMinorVersion_2() throws Exception
    {
        if (!canTest)
            return;
        
        // arrange
        Method method = JDFVersion.class.getDeclaredMethod("getMinorVersion", String.class);
        method.setAccessible(true);
        
        String version = "2.1.5.80-SNAPSHOT";
        String expected = "80";
        
        // act
        String actual = (String) method.invoke(null, version);
        
        // assert
        assertEquals("Minor Version is wrong.", expected, actual);
    }
    
    @Test
    public void testMajorVersion_1() throws Exception
    {
        if (!canTest)
            return;
        
        // arrange
        Method method = JDFVersion.class.getDeclaredMethod("getMajorVersion", String.class);
        method.setAccessible(true);
        
        String version = "2.1.4a.69";
        String expected = "2.1.4a";
        
        // act
        String actual = (String) method.invoke(null, version);
        
        // assert
        assertEquals("Major Version is wrong.", expected, actual);
    }
    
    @Test
    public void testMajorVersion_2() throws Exception
    {
        if (!canTest)
            return;
        
        // arrange
        Method method = JDFVersion.class.getDeclaredMethod("getMajorVersion", String.class);
        method.setAccessible(true);
        
        String version = "2.1.5.80-SNAPSHOT";
        String expected = "2.1.5";
        
        // act
        String actual = (String) method.invoke(null, version);
        
        // assert
        assertEquals("Major Version is wrong.", expected, actual);
    }
    
    /**
     * @see org.cip4.jdflib.JDFTestCaseBase#setUp()
     */
    @Override
    public void setUp() throws Exception
    {
        try
        {
            canTest = JDFVersion.JDF_VERSION != null;
        }
        catch (Throwable t)
        {
            canTest = false;
        }
        super.setUp();
    }
}
