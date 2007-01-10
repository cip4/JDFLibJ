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
 */
/*
 * MediaColorTest.java
 * @author Dietrich Mucha
 * 
 * Copyright (C) 2004 Heidelberger Druckmaschinen AG. All Rights Reserved.
 */
package org.cip4.jdflib.resource;

import java.util.Vector;

import junit.framework.TestCase;

import org.cip4.jdflib.auto.JDFAutoBasicPreflightTest.EnumListType;
import org.cip4.jdflib.core.JDFDoc;
import org.cip4.jdflib.core.VString;
import org.cip4.jdflib.core.JDFElement.EnumBoolean;
import org.cip4.jdflib.datatypes.JDFBaseDataTypes.EnumFitsValue;
import org.cip4.jdflib.resource.devicecapability.JDFBooleanState;
import org.cip4.jdflib.resource.devicecapability.JDFEnumerationState;


public class JDFDevCapTest extends TestCase
{
    public void testBooleanState()
    {
        JDFDoc d=new JDFDoc("BooleanState");
        JDFBooleanState bs=(JDFBooleanState) d.getRoot();
        Vector v=new Vector();
        v.add(EnumBoolean.False);
        bs.setAllowedValueList(v);
        assertTrue(bs.fitsValue("false", EnumFitsValue.Allowed));
        assertFalse(bs.fitsValue("fnarf", EnumFitsValue.Allowed));
        assertFalse(bs.fitsValue("true", EnumFitsValue.Allowed));     
        v.add(EnumBoolean.True);
        bs.setAllowedValueList(v);
        assertTrue(bs.fitsValue("true", EnumFitsValue.Allowed));     

    }
    public void testEnumerationState()
    {
        JDFDoc d=new JDFDoc("EnumerationState");
        JDFEnumerationState es=(JDFEnumerationState) d.getRoot();
        VString v=new VString();
        v.add("foo");
        v.add("bar");
        
        es.setAllowedValueList(v);
        assertTrue(es.fitsValue("foo", EnumFitsValue.Allowed));
        assertTrue(es.fitsValue("bar", EnumFitsValue.Allowed));
        assertFalse(es.fitsValue("fnarf", EnumFitsValue.Allowed));  
        
        es.setListType(EnumListType.List);
        assertTrue(es.fitsValue("foo", EnumFitsValue.Allowed));
        assertTrue(es.fitsValue("foo bar", EnumFitsValue.Allowed));
        assertTrue(es.fitsValue("foo bar foo", EnumFitsValue.Allowed));
        assertFalse(es.fitsValue("foo bar fnarf", EnumFitsValue.Allowed));
        
        es.setListType(EnumListType.CompleteList);
        assertFalse(es.fitsValue("foo", EnumFitsValue.Allowed));
        assertTrue(es.fitsValue("foo bar", EnumFitsValue.Allowed));
        assertTrue(es.fitsValue("bar foo", EnumFitsValue.Allowed));
        assertFalse(es.fitsValue("foo bar foo", EnumFitsValue.Allowed));
        assertFalse(es.fitsValue("foo bar fnarf", EnumFitsValue.Allowed));
//TODO implement more list types        
//        es.setListType(EnumListType.OrderedList);
//        assertFalse(es.fitsValue("foo", EnumFitsValue.Allowed));
//        assertTrue(es.fitsValue("foo bar", EnumFitsValue.Allowed));
//        assertFalse(es.fitsValue("bar foo", EnumFitsValue.Allowed));
//        assertFalse(es.fitsValue("foo bar foo", EnumFitsValue.Allowed));
//        assertFalse(es.fitsValue("foo bar fnarf", EnumFitsValue.Allowed));
    }
}
