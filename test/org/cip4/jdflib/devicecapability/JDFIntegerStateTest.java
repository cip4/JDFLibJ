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
/**
 * JDFStateBaseTest.java
 *
 * @author Elena Skobchenko
 * 
 * Copyright (c) 2001-2004 The International Cooperation for the Integration 
 * of Processes in  Prepress, Press and Postpress (CIP4).  All rights reserved.
 */
package org.cip4.jdflib.devicecapability;

import org.cip4.jdflib.JDFTestCaseBase;
import org.cip4.jdflib.auto.JDFAutoBasicPreflightTest.EnumListType;
import org.cip4.jdflib.core.JDFDoc;
import org.cip4.jdflib.core.JDFParser;
import org.cip4.jdflib.core.KElement.EnumValidationLevel;
import org.cip4.jdflib.datatypes.JDFBaseDataTypes;
import org.cip4.jdflib.datatypes.JDFIntegerList;
import org.cip4.jdflib.datatypes.JDFIntegerRange;
import org.cip4.jdflib.datatypes.JDFIntegerRangeList;
import org.cip4.jdflib.datatypes.JDFXYPair;
import org.cip4.jdflib.resource.devicecapability.JDFIntegerState;


public class JDFIntegerStateTest extends JDFTestCaseBase
{

    JDFIntegerState iState=null;

    public void setUp() throws Exception
    {
        super.setUp();
        JDFDoc doc=new JDFDoc("IntegerState");
        iState=(JDFIntegerState)doc.getRoot();
        
    }
    ////////////////////////////////////////////////////
    
    public final void testFitsValue()
    {
        JDFParser p = new JDFParser();
        String strNode = 
            "<IntegerState Name=\"BitDepth\" DefaultValue=\"1\" AllowedValueList=\"1 8 12\"/>";
            
        JDFDoc jdfDoc = p.parseString(strNode);
        JDFIntegerState state = (JDFIntegerState) jdfDoc.getRoot();

        JDFIntegerRangeList list = new JDFIntegerRangeList();
        list.append(new JDFIntegerRange(1,12)); // 1~12
        //list.append(12);
        
        state.setListType(EnumListType.RangeList);
        assertFalse("ListType=RangeList",state.fitsValue(list.toString(),JDFBaseDataTypes.EnumFitsValue.Allowed));
        
        JDFIntegerRangeList list2 = new JDFIntegerRangeList();
        list2.append(new JDFIntegerRange(1,-2)); // 1~-2
        
        JDFIntegerRangeList allowedVL= new JDFIntegerRangeList();
        allowedVL.append(new JDFIntegerRange(1,32)); // 1~32
        
        state.setAllowedValueList(allowedVL); // new AllowedVlaueList
        
        assertTrue("xDef is wrong", state.fitsValue(list2.toString(),JDFBaseDataTypes.EnumFitsValue.Allowed));
        
        
        list.erase(list.size()-1); // erase "1~12"
        list.append(2);
        list.append(12);
        list.append(22);
        state.setListType(EnumListType.List);
        state.setAllowedValueMod(new JDFXYPair(10,2));
        assertTrue("ListType=List, ValueMod="+state.getAllowedValueMod(),
                   state.fitsValue(list.toString(),JDFBaseDataTypes.EnumFitsValue.Allowed));
     }

    ////////////////////////////////////////////////////////////
    
    public final void testSetCurrentValue() throws Exception
    {
       final JDFIntegerList integerList = new JDFIntegerList("1 2 3");
       iState.setCurrentValue(integerList);
       assertEquals(iState.getCurrentValue(), integerList);
       iState.setCurrentValue(1);
       assertEquals(iState.getCurrentValue(), new JDFIntegerList("1"));

    }
    ////////////////////////////////////////////////////////////
    public final void testSetDefaultValue() throws Exception
    {
       final JDFIntegerList integerList = new JDFIntegerList("1 2 3");
       iState.setDefaultValue(integerList);
       assertEquals(iState.getDefaultValue(), integerList);
       iState.setDefaultValue(1);
       assertEquals(iState.getDefaultValue(), new JDFIntegerList("1"));

    }
    ////////////////////////////////////////////////////////////
    ////////////////////////////////////////////////////////////
    public final void testSetAllowedValueList() throws Exception
    {
       final JDFIntegerRangeList integerList = new JDFIntegerRangeList("1 2 3 4 ~ 44");
       iState.setAllowedValueList(integerList);
       assertEquals(iState.getPresentValueList(), integerList);
       assertEquals(iState.getAllowedValueList(), integerList);
       final JDFIntegerRangeList integerList2 = new JDFIntegerRangeList("1 2 3 7~77");
       iState.setPresentValueList(integerList2);
       assertEquals(iState.getPresentValueList(), integerList2);
       assertEquals(iState.getAllowedValueList(), integerList);
    }
    ////////////////////////////////////////////////////////////
    public final void testIsValid() throws Exception
    {
        final JDFIntegerList integerList = new JDFIntegerList("1 2 3");
        iState.setDefaultValue(integerList);
        iState.setCurrentValue(integerList);
        assertFalse(iState.isValid(EnumValidationLevel.Complete));
        iState.setListType(EnumListType.List);
        assertTrue(iState.isValid(EnumValidationLevel.Complete));
        final JDFIntegerRangeList integerRList = new JDFIntegerRangeList("1 2 3 4 ~ 44");
        iState.setAllowedValueList(integerRList);
        assertTrue(iState.isValid(EnumValidationLevel.Complete));
        final JDFIntegerRangeList integerList2 = new JDFIntegerRangeList("1 2 3 7~77");
        iState.setPresentValueList(integerList2);
        assertTrue(iState.isValid(EnumValidationLevel.Complete));
    }
   ////////////////////////////////////////////////////////////

}
