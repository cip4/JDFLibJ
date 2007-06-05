/*
 *
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
 *
 * Copyright (c) 2001 Heidelberger Druckmaschinen AG, All Rights Reserved.
 *
 * JDFDevCap.java
 *
 * @author Elena Skobchenko
 *
 */
package org.cip4.jdflib.resource.devicecapability;

import java.util.Enumeration;
import java.util.HashMap;
import java.util.HashSet;

import org.apache.xerces.dom.CoreDocumentImpl;
import org.cip4.jdflib.auto.JDFAutoDevCap;
import org.cip4.jdflib.auto.JDFAutoBasicPreflightTest.EnumListType;
import org.cip4.jdflib.core.AtrInfoTable;
import org.cip4.jdflib.core.AttributeInfo;
import org.cip4.jdflib.core.AttributeName;
import org.cip4.jdflib.core.ElemInfoTable;
import org.cip4.jdflib.core.ElementInfo;
import org.cip4.jdflib.core.ElementName;
import org.cip4.jdflib.core.JDFComment;
import org.cip4.jdflib.core.JDFConstants;
import org.cip4.jdflib.core.JDFDoc;
import org.cip4.jdflib.core.JDFElement;
import org.cip4.jdflib.core.JDFException;
import org.cip4.jdflib.core.JDFRefElement;
import org.cip4.jdflib.core.KElement;
import org.cip4.jdflib.core.VElement;
import org.cip4.jdflib.core.VString;
import org.cip4.jdflib.core.XMLDoc;
import org.cip4.jdflib.datatypes.JDFAttributeMap;
import org.cip4.jdflib.datatypes.JDFBaseDataTypes.EnumFitsValue;
import org.cip4.jdflib.node.JDFNode;
import org.cip4.jdflib.resource.JDFResource;
import org.cip4.jdflib.resource.JDFResource.EnumPartUsage;
import org.cip4.jdflib.resource.JDFResource.EnumResourceClass;
import org.cip4.jdflib.span.JDFSpanBase;
import org.cip4.jdflib.util.StringUtil;


//----------------------------------
public class JDFDevCap extends JDFAutoDevCap 
{
    private static final long serialVersionUID = 1L;
    
    private static ElemInfoTable[] elemInfoTable = new ElemInfoTable[14];
    static
    {
        elemInfoTable[0] = new ElemInfoTable(ElementName.DEVCAP, 0x33333333);
        elemInfoTable[1] = new ElemInfoTable(ElementName.BOOLEANSTATE, 0x33333333);
        elemInfoTable[2] = new ElemInfoTable(ElementName.DATETIMESTATE, 0x33333333);
        elemInfoTable[3] = new ElemInfoTable(ElementName.DURATIONSTATE, 0x33333333);
        elemInfoTable[4] = new ElemInfoTable(ElementName.ENUMERATIONSTATE, 0x33333333);
        elemInfoTable[5] = new ElemInfoTable(ElementName.INTEGERSTATE, 0x33333333);
        elemInfoTable[6] = new ElemInfoTable(ElementName.MATRIXSTATE, 0x33333333);
        elemInfoTable[7] = new ElemInfoTable(ElementName.NAMESTATE, 0x33333333);
        elemInfoTable[8] = new ElemInfoTable(ElementName.NUMBERSTATE, 0x33333333);
        elemInfoTable[9] = new ElemInfoTable(ElementName.PDFPATHSTATE, 0x33333333);
        elemInfoTable[10] = new ElemInfoTable(ElementName.RECTANGLESTATE, 0x33333333);
        elemInfoTable[11] = new ElemInfoTable(ElementName.SHAPESTATE, 0x33333333);
        elemInfoTable[12] = new ElemInfoTable(ElementName.STRINGSTATE, 0x33333333);
        elemInfoTable[13] = new ElemInfoTable(ElementName.XYPAIRSTATE, 0x33333333);
    }
    
    protected ElementInfo getTheElementInfo()
    {
        return super.getTheElementInfo().updateReplace(elemInfoTable);
    }
    private static AtrInfoTable[] atrInfoTable = new AtrInfoTable[1];
    static
    {
        atrInfoTable[0] = new AtrInfoTable(AttributeName.MAXOCCURS, 0x33333331, AttributeInfo.EnumAttributeType.unbounded, null, "1");
    }
    
    protected AttributeInfo getTheAttributeInfo()
    {
        return super.getTheAttributeInfo().updateReplace(atrInfoTable);
    }

    /**
     * Constructor for JDFDevCap
     * @param myOwnerDocument
     * @param qualifiedName
     */
    public JDFDevCap(
            CoreDocumentImpl myOwnerDocument,
            String qualifiedName)
    {
        super(myOwnerDocument, qualifiedName);
    }

    /**
     * Constructor for JDFDevCap
     * @param myOwnerDocument
     * @param myNamespaceURI
     * @param qualifiedName
     */
    public JDFDevCap(
            CoreDocumentImpl myOwnerDocument,
            String myNamespaceURI,
            String qualifiedName)
    {
        super(myOwnerDocument, myNamespaceURI, qualifiedName);
    }

    /**
     * Constructor for JDFDevCap
     * @param myOwnerDocument
     * @param myNamespaceURI
     * @param qualifiedName
     * @param myLocalName
     */
    public JDFDevCap(
            CoreDocumentImpl myOwnerDocument,
            String myNamespaceURI,
            String qualifiedName,
            String myLocalName)
    {
        super(myOwnerDocument, myNamespaceURI, qualifiedName, myLocalName);
    }
    
    /**
     * toString()
     * @return String
     */
    public String toString()
    {
        return "JDFDevCap[  --> " + super.toString() + " ]";
    }

    /**
     * set attribute <code>DevCapRefs</code>
     * @param value the value to set the attribute to
     */
    public void setDevCapRefs(VString value)
    {
        setAttribute(AttributeName.DEVCAPREFS, value, null);
    }
    
    /**
     * get IDREFS attribute <code>DevCapRefs</code>
     * @return VString: the value of the attribute
     */
    public VString getDevCapRefs()
    {
        return new VString (getAttribute(AttributeName.DEVCAPREFS, null, JDFConstants.EMPTYSTRING), null);
    }

    /**
     * set attribute <code>ID</code>
     * @param value the value to set the attribute to
     */
    public void setID(String value)
    {
        setAttribute(AttributeName.ID, value, null);
    }
    
    /**
     * get String attribute <code>ID</code>
     * @return String: the value of the attribute
     */
    public String getID()
    {
        return getAttribute(AttributeName.ID, null, JDFConstants.EMPTYSTRING);
    }

    /**
     * getAvailability - gets typesafe enumerated attribute <code>Availability</code>
     *
     * @return EnumAvailability: the enumeration value of the attribute
     */
    public JDFDevCap.EnumAvailability getAvailability()
    {
        JDFDevCap.EnumAvailability avail = JDFDevCap.EnumAvailability.getEnum(
                    getAttribute(AttributeName.AVAILABILITY,  null, null));
        
        if (avail.getValue() == JDFDevCaps.EnumAvailability.getEnum(0).getValue())
        {
            String parName = getParentNode().getNodeName();
            if (parName.equals(ElementName.DEVCAP))
            {
                JDFDevCap devCap = (JDFDevCap) getParentNode();
                avail = devCap.getAvailability();
            }
            else if (parName.equals(ElementName.DEVCAPS))
            {
                JDFDevCaps devCaps = (JDFDevCaps) getParentNode();
                String availStr = devCaps.getAvailability().getName();// there are two equal enumerations in DevCap and DevCaps
                avail = JDFDevCap.EnumAvailability.getEnum(availStr); // transfer from DevCaps EnumValue to DevCap EnumValue
            }
        }
        return avail;
    }
    
    //  ///////////////////////////////////////////////////////////////////
    
    /**
     * get iSkip'th element <code>DevCap</code>
     * @param iSkip number of elements to skip (0 -> get first element)
     * @return the value of the attribute
     */
    public JDFDevCap getDevCap(int iSkip) 
    {
        return (JDFDevCap)getElement(ElementName.DEVCAP, JDFConstants.EMPTYSTRING, iSkip);
    }
    
    //  ///////////////////////////////////////////////////////////////////
    
    /**
     * get iSkip'th element <code>DevCap</code>, create if it doesn't exist 
     * @param iSkip number of elements to skip
     * @return the value of the attribute
     */
    public JDFDevCap getCreateDevCap(int iSkip)
    {
        return (JDFDevCap)getCreateElement(ElementName.DEVCAP, JDFConstants.EMPTYSTRING , iSkip);
    }
    
    //  ///////////////////////////////////////////////////////////////////
    
    /**
     * append element <code>DevCap</code>
     * @return the appended element
     */
    public JDFDevCap appendDevCap()
    {
        return (JDFDevCap)appendElement(ElementName.DEVCAP, null);
    }
    
    //  ///////////////////////////////////////////////////////////////////
    
    /**
     * append dc/@ID to the value of devCap/@ID
     * @param dc the devCap to append
     */
    public void appendDevCapRefs(JDFDevCap dc)
    {
        if(!(dc.getParentNode() instanceof JDFDevCapPool))
            throw new JDFException("appendDevCapRefs: referenced devCap must reide in a devCapPool");
        dc.appendAnchor(null); // just in case it is missing
        final String id2 = dc.getID();
        appendDevCapRefs(id2);
    }

    //  ///////////////////////////////////////////////////////////////////
    
    /**
     * append dc/@ID to the value of devCap/@ID
     * @param dcID @ID of the devCap to append
     */
    public void appendDevCapRefs(String dcID)
    {
        appendAttribute(AttributeName.DEVCAPREFS,dcID,null," ",true);
    }
        
    //  ///////////////////////////////////////////////////////////////////
    
    /**
     * gets the iSkip'th existing BooleanState 
     * @param iSkip number of elements to skip (0 -> get first element)
     * @return JDFBooleanState: the existing BooleanState
     */
    public JDFBooleanState getBooleanState(int iSkip) 
    {
        return (JDFBooleanState)getElement(ElementName.BOOLEANSTATE, null, iSkip);
     }
    
    //  ///////////////////////////////////////////////////////////////////
    
    /**
     * gets an existing BooleanState with @Name="name"
     * @param nam the Name attribute of the newly appended BooleanState
     * @return JDFBooleanState: the existing BooleanState
     */
    public JDFBooleanState getBooleanState(String nam)
    {
        return (JDFBooleanState)getChildWithAttribute(ElementName.BOOLEANSTATE, AttributeName.NAME, null, nam, 0, true);
    }
     
    //  ///////////////////////////////////////////////////////////////////
    
    /**
     * @deprecated use method with parameter (string) instead
     * @return
     */
    public JDFBooleanState getCreateBooleanState(int iSkip)
    {
        return (JDFBooleanState)getCreateElement(ElementName.BOOLEANSTATE, null, iSkip);
    }
    
    //  ///////////////////////////////////////////////////////////////////
    
    /**
     * gets a NumberState with @Name="name", appends it if it does not exist
     * @param name the name attribute of the newly appended NumberState
     * @return JDFNumberState: the existing or newly appended NumberState
     */
    public JDFBooleanState getCreateBooleanState(String nam)
    {
        JDFBooleanState s=getBooleanState(nam);
        if(s==null)
            s=appendBooleanState(nam);
        return s;
    }
    
    //  ///////////////////////////////////////////////////////////////////
    
    /**
     * @deprecated use method with parameter (string) instead
     * @return
     */
    public JDFBooleanState appendBooleanState()
    {
        return (JDFBooleanState)appendElement(ElementName.BOOLEANSTATE, null);
    }
    
    /**
     * appends a BooleanState with @Name="name"
     * @param nam the name attribute of the newly appended BooleanState
     * @return JDFBooleanState: the newly appended BooleanState
     */
    public JDFBooleanState appendBooleanState(String nam)
    {
        JDFBooleanState s= (JDFBooleanState)appendElement(ElementName.BOOLEANSTATE, null);
        s.setName(nam);
        return s;
    }
    
    //  ///////////////////////////////////////////////////////////////////
    //  ///////////////////////////////////////////////////////////////////
    
    /**
     * gets the iSkip'th existing IntegerState 
     * @param iSkip number of elements to skip (0 -> get first element)
     * @return JDFIntegerState: the existing IntegerState
     */
    public JDFIntegerState getIntegerState(int iSkip)
    {
        JDFIntegerState e = (JDFIntegerState)getElement(ElementName.INTEGERSTATE, null, iSkip);
        return e;
    }
    
    //  ///////////////////////////////////////////////////////////////////
    
    /**
     * gets an existing IntegerState with @Name="name"
     * @param nam the name attribute of the newly appended IntegerState
     * @return JDFIntegerState: the existing IntegerState
     */
    public JDFIntegerState getIntegerState(String nam)
    {
        return (JDFIntegerState)getChildWithAttribute(ElementName.INTEGERSTATE, AttributeName.NAME, null, nam, 0, true);
    }
    
    //  ///////////////////////////////////////////////////////////////////
    
    /**
     * @deprecated use method with parameter (string) instead
     * @return
     */
    public JDFIntegerState getCreateIntegerState(int iSkip)
    {
        return (JDFIntegerState)getCreateElement(ElementName.INTEGERSTATE, null, iSkip);
    }
    
    /**
     * gets an IntegerState with @Name="name", appends it if it does not yet exist
     * @param nam the name attribute of the newly appended IntegerState
     * @return JDFIntegerState: the existing or newly appended IntegerState
     */
    public JDFIntegerState getCreateIntegerState(String nam)
    {
        JDFIntegerState s=getIntegerState(nam);
        if(s==null)
            s=appendIntegerState(nam);
        return s;
    }
    
    //  ///////////////////////////////////////////////////////////////////
    
    /**
     * append an IntegerState with no name set
     * @return JDFIntegerState: the newly appended IntegerState
     */
    public JDFIntegerState appendIntegerState()
    {
        return (JDFIntegerState)appendElement(ElementName.INTEGERSTATE, null);
    }
    
    //  ///////////////////////////////////////////////////////////////////
    
    /**
     * appends an IntegerState with @Name="name"
     * @param name the Name attribute of the newly appended IntegerState
     * @return JDFIntegerState: the newly appended IntegerState
     */
    public JDFIntegerState appendIntegerState(String nam)
    {
        JDFIntegerState s= (JDFIntegerState)appendElement(ElementName.INTEGERSTATE, null);
        s.setName(nam);
        return s;
    }
    
    //  ///////////////////////////////////////////////////////////////////
    //  ///////////////////////////////////////////////////////////////////
    
    /**
     * gets the iSkip'th existing NumberState 
     * @param iSkip number of elements to skip
     * @return JDFNumberState: the existing NumberState
     */
    public JDFNumberState getNumberState(int iSkip)
    {
        return (JDFNumberState)getElement(ElementName.NUMBERSTATE, null, iSkip);
    }
    
    //  ///////////////////////////////////////////////////////////////////
    /**
     * gets an existing  NumberState with @Name="name"
     * @param nam the Name attribute of the newly appended NumberState
     * @return JDFNumberState: the existing NumberState
     */
    public JDFNumberState getNumberState(String nam)
    {
        return (JDFNumberState)getChildWithAttribute(ElementName.NUMBERSTATE, AttributeName.NAME, null, nam, 0, true);
    }
    
    //  ///////////////////////////////////////////////////////////////////
    
    /**
     * @deprecated use method with parameter (string) instead
     * @return
     */
    public JDFNumberState getCreateNumberState(int iSkip)
    {
        return (JDFNumberState)getCreateElement(ElementName.NUMBERSTATE, null, iSkip);       
    }
    //  ///////////////////////////////////////////////////////////////////
    
    /**
     * gets a NumberState with @Name="name", appends it if it does not yet exist
     * @param nam the Name attribute of the newly appended NumberState
     * @return JDFNumberState: the existing or newly appended NumberState
     */
    public JDFNumberState getCreateNumberState(String nam)
    {
        JDFNumberState s=getNumberState(nam);
        if(s==null)
            s=appendNumberState(nam);
        return s;
    }
    //  ///////////////////////////////////////////////////////////////////
    /**
     * appends a NumberState with @Name="name"
     * @param nam the Name attribute of the newly appended NumberState
     * @return JDFNumberState: the newly appended NumberState
     */
    public JDFNumberState appendNumberState(String nam)
    {
        JDFNumberState s= (JDFNumberState)appendElement(ElementName.NUMBERSTATE, null);
        s.setName(nam);
        return s;
    }
    
    /**
     * @deprecated use method with parameter (string) instead
     * @return
     */
    public JDFNumberState appendNumberState()
    {
        return appendNumberState(null);
    }
    //  ///////////////////////////////////////////////////////////////////
    //  ///////////////////////////////////////////////////////////////////
    
    /**
     * gets the iSkip'th existing EnumerationState
     * @param iSkip number of elements to skip (0 -> get first element)
     * @return JDFEnumerationState: the existing EnumerationState
     */
    public JDFEnumerationState getEnumerationState(int iSkip)
    {
        return (JDFEnumerationState)getElement(ElementName.ENUMERATIONSTATE, null, iSkip);
    }
    
    //  ///////////////////////////////////////////////////////////////////
    
    /**
     * gets an existing  EnumerationState with @Name="name"
     * @param name the Name attribute of the newly appended EnumerationState
     * @return JDFEnumerationState: the existing EnumerationState
     */
    public JDFEnumerationState getEnumerationState(String nam)
    {
        return (JDFEnumerationState)getChildWithAttribute(ElementName.ENUMERATIONSTATE, AttributeName.NAME, null, nam, 0, true);
    }
    
    //  ///////////////////////////////////////////////////////////////////
    
    /**
     * gets the iSkip'th existing EnumerationState, creates it if it doesn't exist
     * @param iSkip number of elements to skip (0 -> get first element)
     * @return JDFEnumerationState: the existing EnumerationState
     */
    public JDFEnumerationState getCreateEnumerationState(int iSkip)
    {
        return (JDFEnumerationState)getCreateElement(ElementName.ENUMERATIONSTATE, null, iSkip);
    }
    
    //  ///////////////////////////////////////////////////////////////////
    
    /**
     * gets a EnumerationState with @Name="name", appends it if it does not exist
     * @param nam the name attribute of the newly appended EnumerationState
     * @return JDFEnumerationState the existing or newly appended EnumerationState
     */
    public JDFEnumerationState getCreateEnumerationState(String nam)
    {
        JDFEnumerationState s=getEnumerationState(nam);
        if(s==null)
            s=appendEnumerationState(nam);
        return s;
    }
    
    //  ///////////////////////////////////////////////////////////////////
    
    /**
     * @deprecated use method with parameter (string) instead
     * @return
     */
    public JDFEnumerationState appendEnumerationState()
    {
        return  (JDFEnumerationState)appendElement(ElementName.ENUMERATIONSTATE, null);
    }
    
    //  ///////////////////////////////////////////////////////////////////
    
    /**
     * appends a NumberState with @Name="name"
     * @param nam   the name attribute of the newly appended NumberState
     * @return JDFNumberState: the newly appended NumberState
     */
    public JDFEnumerationState appendEnumerationState(String nam)
    {
        JDFEnumerationState s= (JDFEnumerationState)appendElement(ElementName.ENUMERATIONSTATE, null);
        s.setName(nam);
        return s;
    }
    
    //  ///////////////////////////////////////////////////////////////////
    //  ///////////////////////////////////////////////////////////////////
    
    /**
     * gets the iSkip'th existing NameState 
     * @param iSkip number of elements to skip
     * @return JDFNameState: the existing NameState
     */
    public JDFNameState getNameState(int iSkip)
    {
        return (JDFNameState)getElement(ElementName.NAMESTATE, null, iSkip);
    }
    
    //  ///////////////////////////////////////////////////////////////////
    
    /**
     * gets an existing NameState with @Name="name"
     * @param nam the Name attribute of the newly appended NameState
     * @return JDFNameState: the existing NameState
     */
    public JDFNameState getNameState(String nam)
    {
        return (JDFNameState)getChildWithAttribute(ElementName.NAMESTATE, AttributeName.NAME, null, nam, 0, true);
    }
    
    //  ///////////////////////////////////////////////////////////////////
    
    /**
     * @deprecated use method with parameter (string) instead
     * @return
     */
    public JDFNameState getCreateNameState(int iSkip)
    {
        return (JDFNameState)getCreateElement(ElementName.NAMESTATE, null, iSkip);
    }
    
    //  ///////////////////////////////////////////////////////////////////
    
    /**
     * gets a NameState with @Name="name", appends it if it does not exist
     * @param nam the name attribute of the newly appended NameState
     * @return JDFNameState: the existing or newly appended NameState
     */
    public JDFNameState getCreateNameState(String nam)
    {
        JDFNameState s=getNameState(nam);
        if(s==null)
            s=appendNameState(nam);
        return s;
    }
    
    //  ///////////////////////////////////////////////////////////////////
    
    /**
     * @deprecated use method with parameter (string) instead
     * @return
     */
    public JDFNameState appendNameState()
    {
        return (JDFNameState)appendElement(ElementName.NAMESTATE, null);
    }
    
    //  ///////////////////////////////////////////////////////////////////
    
    /**
     * appends a NameState with @Name="name"
     * @param nam the name attribute of the newly appended NameState
     * @return JDFNameState: the newly appended NameState
     */
    public JDFNameState appendNameState(String nam)
    {
        JDFNameState s= (JDFNameState)appendElement(ElementName.NAMESTATE, null);
        s.setName(nam);
        return s;
    }
    
    //  ///////////////////////////////////////////////////////////////////
    //  ///////////////////////////////////////////////////////////////////
    
    /**
     * gets the iSkip'th existing StringState 
     * @param iSkip number of elements to skip
     * @return JDFStringState: the existing StringState
     */
    public JDFStringState getStringState(int iSkip)
    {
        return (JDFStringState)getElement(ElementName.STRINGSTATE, null, iSkip);
    }
    
    //  ///////////////////////////////////////////////////////////////////
    
    /**
     * gets an existing  StringState with @Name="name"
     * @param nam the Name attribute of the newly appended StringState
     * @return JDFStringState: the existing StringState
     */
    public JDFStringState getStringState(String nam)
    {
        return (JDFStringState)getChildWithAttribute(ElementName.STRINGSTATE, AttributeName.NAME, null, nam, 0, true);
    }
    
    //  ///////////////////////////////////////////////////////////////////
    
    /**
     * @deprecated use method with parameter (string) instead
     * @return
     */
    public JDFStringState getCreateStringState(int iSkip)
    {
        return (JDFStringState)getCreateElement(ElementName.STRINGSTATE, null, iSkip);
    }
    
    //  ///////////////////////////////////////////////////////////////////

    /**
     * gets a StringState with @Name="name", appends it if it does not yet exist
     * @param nam the Name attribute of the newly appended StringState
     * @return JDFStringState: the existing or newly appended StringState
     */
    public JDFStringState getCreateStringState(String nam)
    {
        JDFStringState s=getStringState(nam);
        if(s==null)
            s=appendStringState(nam);
        return s;
    }
    
    //  ///////////////////////////////////////////////////////////////////       
    
    /**
     * @deprecated use method with parameter (string) instead
     * @return
     */
    public JDFStringState appendStringState()
    {
        return (JDFStringState)appendElement(ElementName.STRINGSTATE, null);
    }
    
    //  ///////////////////////////////////////////////////////////////////

    /**
     * appends a StringState with @Name="name"
     * @param nam the Name attribute of the newly appended StringState
     * @return JDFStringState: the newly appended StringState
     */
    public JDFStringState appendStringState(String nam)
    {
        JDFStringState s= (JDFStringState)appendElement(ElementName.STRINGSTATE, null);
        s.setName(nam);
        return s;
    }
    
    //  ///////////////////////////////////////////////////////////////////
    //  ///////////////////////////////////////////////////////////////////
    
    /**
     * gets the iSkip'th existing XYPairState 
     * @param iSkip number of elements to skip
     * @return JDFXYPairState: the existing XYPairState
     */
    public JDFXYPairState getXYPairState(int iSkip)
    {
        return (JDFXYPairState) getElement(ElementName.XYPAIRSTATE, null, iSkip);
    }
    
    //  ///////////////////////////////////////////////////////////////////

    /**
     * gets an existing XYPairState with @Name="name"
     * @param nam the Name attribute of the newly appended XYPairState
     * @return JDFXYPairState: the existing XYPairState
     */
    public JDFXYPairState getXYPairState(String nam)
    {
        return (JDFXYPairState)getChildWithAttribute(ElementName.XYPAIRSTATE, AttributeName.NAME, null, nam, 0, true);
    }
    
    //  ///////////////////////////////////////////////////////////////////
    
    /**
     * @deprecated use method with parameter (string) instead
     * @return
     */
    public JDFXYPairState getCreateXYPairState(int iSkip)
    {
        return (JDFXYPairState)getCreateElement(ElementName.XYPAIRSTATE, null, iSkip);
    }
    
    //  ///////////////////////////////////////////////////////////////////

    /**
     * gets a XYPairState with @Name="name", appends it if it does not yet exist
     * @param nam the Name attribute of the newly appended XYPairState
     * @return JDFXYPairState: the existing or newly appended XYPairState
     */
    public JDFXYPairState getCreateXYPairState(String nam)
    {
        JDFXYPairState s=getXYPairState(nam);
        if(s==null)
            s=appendXYPairState(nam);
        return s;
    }
    
    //  ///////////////////////////////////////////////////////////////////
    
    /**
     * @deprecated use method with parameter (string) instead
     * @return
     */
    public JDFXYPairState appendXYPairState()
    {
        return (JDFXYPairState)appendElement(ElementName.XYPAIRSTATE, null);
    }
    
    //  ///////////////////////////////////////////////////////////////////

    /**
     * appends a XYPairState with @Name="name"
     * @param nam the Name attribute of the newly appended XYPairState
     * @return JDFXYPairState: the newly appended XYPairState
     */
    public JDFXYPairState appendXYPairState(String nam)
    {
        JDFXYPairState s= (JDFXYPairState)appendElement(ElementName.XYPAIRSTATE, null);
        s.setName(nam);
        return s;
    }
    
    //  ///////////////////////////////////////////////////////////////////
    //  ///////////////////////////////////////////////////////////////////
    
    /**
     * gets the iSkip'th existing ShapeState 
     * @param iSkip number of elements to skip (0 -> get first element)
     * @return JDFShapeState the existing ShapeState
     */
    public JDFShapeState getShapeState(int iSkip)
    {
        return (JDFShapeState)getElement(ElementName.SHAPESTATE, null, iSkip);
    }
    
    //  ///////////////////////////////////////////////////////////////////

    /**
     * gets an existing ShapeState with @Name="name"
     * @param nam the Name attribute of the newly appended ShapeState
     * @return JDFShapeState: the existing ShapeState
     */
    public JDFShapeState getShapeState(String nam)
    {
        return (JDFShapeState)getChildWithAttribute(ElementName.SHAPESTATE, AttributeName.NAME, null, nam, 0, true);
    }
    
    //  ///////////////////////////////////////////////////////////////////
    
    /**
     * @deprecated use method with parameter (string) instead
     * @return
     */
    public JDFShapeState getCreateShapeState(int iSkip)
    {
        return(JDFShapeState)getCreateElement(ElementName.SHAPESTATE, null, iSkip);
    }
    
    //  ///////////////////////////////////////////////////////////////////

    /**
     * gets a ShapeState with @Name="name", appends it if it does not yet exist
     * @param nam the Name attribute of the newly appended ShapeState
     * @return JDFShapeState: the existing or newly appended ShapeState
     */
    public JDFShapeState getCreateShapeState(String nam)
    {
        JDFShapeState s=getShapeState(nam);
        if(s==null)
            s=appendShapeState(nam);
        return s;
    }
    
    //  ///////////////////////////////////////////////////////////////////
        
    /**
     * @deprecated use method with parameter (string) instead
     * @return
     */
    public JDFShapeState appendShapeState()
    {
        return (JDFShapeState)appendElement(ElementName.SHAPESTATE, null);
    }
    
    //  ///////////////////////////////////////////////////////////////////

    /**
     * appends a ShapeState with @Name="name"
     * @param nam the Name attribute of the newly appended ShapeState
     * @return JDFShapeState: the newly appended ShapeState
     */
    public JDFShapeState appendShapeState(String nam)
    {
        JDFShapeState s= (JDFShapeState)appendElement(ElementName.SHAPESTATE, null);
        s.setName(nam);
        return s;
    }
    
    //  ///////////////////////////////////////////////////////////////////
    //  ///////////////////////////////////////////////////////////////////
    
    /**
     * gets the iSkip'th existing MatrixState 
     * @param iSkip number of elements to skip (0 -> get first element)
     * @return JDFMatrixState the existing MatrixState
     */
    public JDFMatrixState getMatrixState(int iSkip)
    {
        return(JDFMatrixState)getElement(ElementName.MATRIXSTATE, null, iSkip);
    }
    
    //  ///////////////////////////////////////////////////////////////////

    /**
     * gets an existing MatrixState with @Name="name"
     * @param nam the Name attribute of the newly appended MatrixState
     * @return JDFMatrixState: the existing MatrixState
     */
    public JDFMatrixState getMatrixState(String nam)
    {
        return (JDFMatrixState)getChildWithAttribute(ElementName.MATRIXSTATE, AttributeName.NAME, null, nam, 0, true);
    }
    
    //  ///////////////////////////////////////////////////////////////////
    
    /**
     * @deprecated use method with parameter (string) instead
     * @return
     */
    public JDFMatrixState getCreateMatrixState(int iSkip)
    {
        return (JDFMatrixState)getCreateElement(ElementName.MATRIXSTATE, null, iSkip);
    }
    
    /**
     * gets a MatrixState with @Name="name", appends it if it does not yet exist
     * @param nam the Name attribute of the newly appended MatrixState
     * @return JDFMatrixState: the existing or newly appended MatrixState
     */
    public JDFMatrixState getCreateMatrixState(String nam)
    {
        JDFMatrixState s=getMatrixState(nam);
        if(s==null)
            s=appendMatrixState(nam);
        return s;
    }
    
    //  ///////////////////////////////////////////////////////////////////
    
    /**
     * @deprecated use method with parameter (string) instead
     * @return
     */
    public JDFMatrixState appendMatrixState()
    {
        return (JDFMatrixState)appendElement(ElementName.MATRIXSTATE, null);
    }
    
    //  ///////////////////////////////////////////////////////////////////

    /**
     * appends a MatrixState with @Name="name"
     * @param nam the Name attribute of the newly appended MatrixState
     * @return JDFMatrixState: the newly appended MatrixState
     */
    public JDFMatrixState appendMatrixState(String nam)
    {
        JDFMatrixState s= (JDFMatrixState)appendElement(ElementName.MATRIXSTATE, null);
        s.setName(nam);
        return s;
    }
    
    //  ///////////////////////////////////////////////////////////////////
    //  ///////////////////////////////////////////////////////////////////
    
    /**
     * gets the iSkip'th existing DateTimeState 
     * @param iSkip number of elements to skip (0 -> get first element)
     * @return JDFDateTimeState the existing DateTimeState
     */    
    public JDFDateTimeState getDateTimeState(int iSkip)
    {
        return (JDFDateTimeState)getElement(ElementName.DATETIMESTATE, null, iSkip);
    }
    
    //  ///////////////////////////////////////////////////////////////////

    /**
     * gets an existing DateTimeState with @Name="name"
     * @param nam the Name attribute of the newly appended DateTimeState
     * @return JDFDateTimeState: the existing DateTimeState
     */
    public JDFDateTimeState getDateTimeState(String nam)
    {
        return (JDFDateTimeState)getChildWithAttribute(ElementName.DATETIMESTATE, AttributeName.NAME, null, nam, 0, true);
    }
    
    //  ///////////////////////////////////////////////////////////////////
    
    /**
     * @deprecated use method with parameter (string) instead
     * @return
     */
    public JDFDateTimeState getCreateDateTimeState(int iSkip)
    {
        return(JDFDateTimeState)getCreateElement(ElementName.DATETIMESTATE, null, iSkip);
    }
    
    //  ///////////////////////////////////////////////////////////////////

    /**
     * gets a DateTimeState with @Name="name", appends it if it does not yet exist
     * @param nam the Name attribute of the newly appended DateTimeState
     * @return JDFDateTimeState: the existing or newly appended DateTimeState
     */
    public JDFDateTimeState getCreateDateTimeState(String nam)
    {
        JDFDateTimeState s=getDateTimeState(nam);
        if(s==null)
            s=appendDateTimeState(nam);
        return s;
    }
    
    //  ///////////////////////////////////////////////////////////////////
    
    /**
     * @deprecated use method with parameter (string) instead
     * @return
     */
    public JDFDateTimeState appendDateTimeState()
    {
        return (JDFDateTimeState)appendElement(ElementName.DATETIMESTATE, null);
    }
 
    //  ///////////////////////////////////////////////////////////////////

    /**
     * appends a DateTimeState with @Name="name"
     * @param nam the Name attribute of the newly appended DateTimeState
     * @return JDFDateTimeState: the newly appended DateTimeState
     */
    public JDFDateTimeState appendDateTimeState(String nam)
    {
        JDFDateTimeState s= (JDFDateTimeState)appendElement(ElementName.DATETIMESTATE, null);
        s.setName(nam);
        return s;
    }
    
    //  ///////////////////////////////////////////////////////////////////
    //  ///////////////////////////////////////////////////////////////////
    
    /**
     * gets the iSkip'th existing DurationState 
     * @param iSkip number of elements to skip (0 -> get first element)
     * @return JDFDurationState the existing DurationState
     */
    public JDFDurationState getDurationState(int iSkip)
    {
        return (JDFDurationState) getElement(ElementName.DURATIONSTATE, null, iSkip);
    }
    
    //  ///////////////////////////////////////////////////////////////////

    /**
     * gets an existing DurationState with @Name="name"
     * @param nam the Name attribute of the newly appended DurationState
     * @return JDFDurationState: the existing DurationState
     */
    public JDFDurationState getDurationState(String nam)
    {
        return (JDFDurationState)getChildWithAttribute(ElementName.DURATIONSTATE, AttributeName.NAME, null, nam, 0, true);
    }
    
    //  ///////////////////////////////////////////////////////////////////
    
    /**
     * @deprecated use method with parameter (string) instead
     * @return
     */
    public JDFDurationState getCreateDurationState(int iSkip)
    {
        return (JDFDurationState)getCreateElement(ElementName.DURATIONSTATE, null, iSkip);
    }
    
    //  ///////////////////////////////////////////////////////////////////

    /**
     * gets a DurationState with @Name="name", appends it if it does not yet exist
     * @param nam the Name attribute of the newly appended DurationState
     * @return JDFDurationState: the existing or newly appended DurationState
     */
    public JDFDurationState getCreateDurationState(String nam)
    {
        JDFDurationState s=getDurationState(nam);
        if(s==null)
            s=appendDurationState(nam);
        return s;
    }
    
    //  ///////////////////////////////////////////////////////////////////
    
    /**
     * @deprecated use method with parameter (string) instead
     * @return
     */
    public JDFDurationState appendDurationState()
    {
        return (JDFDurationState)appendElement(ElementName.DURATIONSTATE, null);
    }
    
    //  ///////////////////////////////////////////////////////////////////

    /**
     * appends a DurationState with @Name="name"
     * @param nam the Name attribute of the newly appended DurationState
     * @return JDFDurationState: the newly appended DurationState
     */
    public JDFDurationState appendDurationState(String nam)
    {
        JDFDurationState s= (JDFDurationState)appendElement(ElementName.DURATIONSTATE, null);
        s.setName(nam);
        return s;
    }
    
    //  ///////////////////////////////////////////////////////////////////
    
    /**
     * gets the iSkip'th existing PDFPathState 
     * @param iSkip number of elements to skip (0 -> get first element)
     * @return JDFPDFPathState: the existing PDFPathState
     */
    public JDFPDFPathState getPDFPathState(int iSkip)
    {
        return(JDFPDFPathState)getElement(ElementName.PDFPATHSTATE, null, iSkip);
    }
    
    //  ///////////////////////////////////////////////////////////////////

    /**
     * gets an existing PDFPathState with @Name="name"
     * @param nam the Name attribute of the newly appended PDFPathState
     * @return JDFPDFPathState: the existing PDFPathState
     */
    public JDFPDFPathState getPDFPathState(String nam)
    {
        return (JDFPDFPathState)getChildWithAttribute(ElementName.PDFPATHSTATE, AttributeName.NAME, null, nam, 0, true);
    }
    
    //  ///////////////////////////////////////////////////////////////////
    
    /**
     * @deprecated use method with parameter (string) instead
     * @return
     */
    public JDFPDFPathState getCreatePDFPathState(int iSkip)
    {
        return  (JDFPDFPathState)getCreateElement(ElementName.PDFPATHSTATE, null, iSkip);
    }
    
    //  ///////////////////////////////////////////////////////////////////

    /**
     * gets a PDFPathState with @Name="name", appends it if it does not yet exist
     * @param nam the Name attribute of the newly appended PDFPathState
     * @return JDFPDFPathState: the existing or newly appended PDFPathState
     */
    public JDFPDFPathState getCreatePDFPathState(String nam)
    {
        JDFPDFPathState s=getPDFPathState(nam);
        if(s==null)
            s=appendPDFPathState(nam);
        return s;
    }
    
    //  ///////////////////////////////////////////////////////////////////
    
    /**
     * @deprecated use method with parameter (string) instead
     * @return
     */
    public JDFPDFPathState appendPDFPathState()
    {
        return (JDFPDFPathState)appendElement(ElementName.PDFPATHSTATE, null);
    }
    
    //  ///////////////////////////////////////////////////////////////////

    /**
     * appends a PDFPathState with @Name="name"
     * @param nam the Name attribute of the newly appended PDFPathState
     * @return JDFPDFPathState: the newly appended PDFPathState
     */
    public JDFPDFPathState appendPDFPathState(String nam)
    {
        JDFPDFPathState s= (JDFPDFPathState)appendElement(ElementName.PDFPATHSTATE, null);
        s.setName(nam);
        return s;
    }
    
    //  ///////////////////////////////////////////////////////////////////
    //  ///////////////////////////////////////////////////////////////////
    
    /**
     * gets the iSkip'th existing RectangleState 
     * @param iSkip number of elements to skip (0 -> get first element)
     * @return JDFRectangleState: the existing RectangleState
     */
    public JDFRectangleState getRectangleState(int iSkip)
    {
        return (JDFRectangleState)getElement(ElementName.RECTANGLESTATE, null, iSkip);
    }
    
    //  ///////////////////////////////////////////////////////////////////

    /**
     * gets an existing RectangleState with @Name="name"
     * @param nam the Name attribute of the newly appended RectangleState
     * @return JDFRectangleState: the existing RectangleState
     */
    public JDFRectangleState getRectangleState(String nam)
    {
        return (JDFRectangleState)getChildWithAttribute(ElementName.RECTANGLESTATE, AttributeName.NAME, null, nam, 0, true);
    }
    
    //  ///////////////////////////////////////////////////////////////////
    
    /**
     * @deprecated use method with parameter (string) instead
     * @return
     */
    public JDFRectangleState getCreateRectangleState(int iSkip)
    {
        return (JDFRectangleState)getCreateElement(ElementName.RECTANGLESTATE, null, iSkip);
    }
    
    //  ///////////////////////////////////////////////////////////////////

    /**
     * gets a RectangleState with @Name="name", appends it if it does not yet exist
     * @param nam the Name attribute of the newly appended RectangleState
     * @return JDFRectangleState: the existing or newly appended RectangleState
     */
    public JDFRectangleState getCreateRectangleState(String nam)
    {
        JDFRectangleState s=getRectangleState(nam);
        if(s==null)
            s=appendRectangleState(nam);
        return s;
    }
    
    //  ///////////////////////////////////////////////////////////////////
    
    /**
     * @deprecated use method with parameter (string) instead
     * @return
     */
    public JDFRectangleState appendRectangleState()
    {
        return (JDFRectangleState)appendElement(ElementName.RECTANGLESTATE, null);
    }
    
    //  ///////////////////////////////////////////////////////////////////

    /**
     * appends a RectangleState with @Name="name"
     * @param nam the Name attribute of the newly appended RectangleState
     * @return JDFRectangleState: the newly appended RectangleState
     */
    public JDFRectangleState appendRectangleState(String nam)
    {
        JDFRectangleState s= (JDFRectangleState)appendElement(ElementName.RECTANGLESTATE, null);
        s.setName(nam);
        return s;
    }
    
    
    /* ******************************************************
     // FitsValue Methods
      **************************************************************** */
    
    /**
     * Gets of this the Vector of all direct child DevCap elements plus 
     * the referenced (by attribute DevCapRefs) reusable DevCap elements, 
     * that are located in DevCapPool
     * 
     * @param devCaps
     * @param bDirect
     * 
     * @return VElement - vector of all direct child DevCap elements plus 
     * the referenced reusable DevCap elements, that are located in DevCapPool. 
     */ 
    public final VElement getDevCapVector(VElement devCaps, boolean bDirect)
    {
        int preFill=0;
        VElement vDevCap = getChildElementVector(ElementName.DEVCAP, null, null, true, 0, false);
        if(devCaps!=null){
            preFill=devCaps.size();
            devCaps.appendUnique(vDevCap);
            vDevCap=devCaps;
        }
        else // first call
        {
            if(bDirect==false)
                vDevCap.appendUnique(this);
        }
        
        
        if (hasAttribute(AttributeName.DEVCAPREFS)) 
        {         
            JDFDeviceCap deviceCap = (JDFDeviceCap) getDeepParent(ElementName.DEVICECAP, 0);
            JDFDevCapPool devCapPool = deviceCap == null ? null : deviceCap.getDevCapPool();
            if (devCapPool != null) 
            {
                VString idRefs = getDevCapRefs();
                for (int i=0; i < idRefs.size(); i++) 
                {
                    JDFDevCap devCap = devCapPool.getDevCap(idRefs.stringAt(i));
                    if (devCap!=null)
                    {
                        vDevCap.appendUnique(devCap);
                    }
                    else
                    {
                        throw new JDFException("JDFDevCap.getDevCapVector: Attribute DevCapRefs refers to the non-existent DevCap: "+idRefs.stringAt(i));
                    }
                }
            }
            else
            {
                throw new JDFException("JDFDevCap.getDevCapVector: Attribute DevCapRefs refers to the non-existent DevCapPool");
            }
        }
        if(bDirect==false){
            for(int i=preFill;i<vDevCap.size();i++)
            {
                JDFDevCap dcChild=(JDFDevCap) vDevCap.elementAt(i);
                vDevCap=dcChild.getDevCapVector(vDevCap,bDirect);
            }
        }
        return vDevCap;
    }
    
    /**
     * Tests if the attributes and subelements of the given element
     * match the corresponding States and DevCap subelements of this DevCap.<br>
     * Composes a detailed report of the found errors in XML form. 
     * If XMLDoc equals null - there are no errors
     * 
     * @param e         element to test
     * @param testlists FitsValue_Allowed or FitsValue_Present testlists
     * that are specified for the State elements. (Will be used in fitsValue method of the State element)
     * @param level     validation level
     * @return XMLDoc - XMLDoc output of the error messages. If XMLDoc is <code>null</code> 
     * there are no errors.<br>
     * Element <code>e</code> fits the corresponding States and DevCap subelements of this DevCap.
     */
    public final KElement stateReport(KElement e, EnumFitsValue testlists, EnumValidationLevel level, boolean ignoreExtensions, boolean bRecurse, KElement parentReport)
    {        
        // 'e' in DeviceCapabilities is described by this DevCap
        
        // first test if there are any subelements of 'e' that are not described by DevCap
        if(!(e instanceof JDFNode) && !ignoreExtensions)
        {
            missingDevCap(e,parentReport);
        }
        
        // DevCap contains: (1) description of parts; 
        // (2) description of subelements; 
        // (3) description of attributes
        
        // (1) Test Partition Leaves: 'e' - is partitioned, its leaves must be described by 'this' DevCap
        if (e instanceof JDFResource && bRecurse) 
        {
            JDFResource res = (JDFResource) e;
            final JDFResource resourceRoot = res.getResourceRoot();
            if(resourceRoot.hasAttribute_KElement(AttributeName.PARTIDKEYS, null, false)) 
            {
                final VElement vLeaves = res.getLeaves(!EnumPartUsage.Explicit.equals(resourceRoot.getPartUsage()));

                final int size = vLeaves.size();
                for (int i=0; i < size; i++)
                {
                    JDFResource leaf = (JDFResource) vLeaves.elementAt(i);
                    KElement p = parentReport.appendElement("InvalidPartitionLeaf");
                    KElement partTestResult = stateReport(leaf,testlists,level,ignoreExtensions,false,p);
                    if(partTestResult != null) 
                    {
                        String path = leaf.buildXPath(null,1);
                        p.setAttribute("XPath",path);
                        String leafPath = path.substring(res.buildXPath(null,1).length());
                        p.setAttribute("LeafXPath",res.getNodeName()+leafPath);
                    }
                    else
                    {
                        p.deleteNode();
                    }
                }
            }
            else
            {
                bRecurse=false; // not partitioned - just pass through e
            }
        }
        else
        {
            bRecurse=false;
        }

        if(!bRecurse)            
        {
            subelementsTest(e,testlists,level,ignoreExtensions,parentReport);
 
            // (3) Test Attributes, Spans and Comments - described by States
            spanAndAttributesTest(e,testlists,level, ignoreExtensions,parentReport);
        }
        if (!parentReport.hasChildElements())
        {
            parentReport = null;
        }
         return parentReport;
    }
    
    
    
    /**
     * Tests subelements of the element <code>e</code>
     * whether they fit the corresponding DevCap elements of <code>this</code>.<br>
     * ! Recursively returns to stateReport to test the attributes of the subelements.<br>
     * Composes a detailed report of the found errors in XML form. <br>
     * If XMLDoc is <code>null</code> there are no errors.
     * 
     * @param e         element to test
     * @param testlists FitsValue_Allowed or FitsValue_Present testlists
     *                  that are specified for the State elements. 
     *                  (Will be used in fitsValue method of the State element.)
     * @param level     validation level
     * @return XMLDoc - XMLDoc output of the error messages. If XMLDoc equals null - there are no errors,
     * 'e' fits the corresponding DevCap elements of 'this' DevCap
     */
    private final KElement subelementsTest(KElement e, EnumFitsValue testlists, EnumValidationLevel level, boolean ignoreExtensions, KElement parentReport)
    {        
        VElement vDevCap = getDevCapVector(null,true); // vDevCap - contains DevCap elements of this DevCap
        
        HashSet goodElems=new HashSet();
        HashMap badElems=new HashMap();

        for (int k=0; k < vDevCap.size(); k++) 
        { // if there are any DevCap subelements in this DavCap
            // then element e must have subelements, and we are going to check them first
            JDFDevCap dc = (JDFDevCap) vDevCap.elementAt(k);
            String dcName = dc.getName();
            
            // vElem - direct children of the Node. 
            // If 'dcName' is a partition Leaf - gets only children of the Leaf.
            VElement vElem = dc.getMatchingElementsFromParent(e,vDevCap);
            int occurs = vElem==null ? 0 : vElem.size();
            KElement r;
            final int minOccurs = dc.getMinOccurs();
            final int maxOccurs = dc.getMaxOccurs();
            if( occurs > maxOccurs) 
            {
                for (int j = 0; j < occurs; j++)
                {
                    KElement subEl = vElem.item(j);
                    r = parentReport.appendElement("InvalidElement");
                    r.setAttribute("CapXPath", dc.getNamePath(true));
                    r.setAttribute("XPath", subEl.buildXPath(null,1));
                    r.setAttribute("Name", dcName);
                    r.setAttribute("Message", "Element occurrence exceeds value of MaxOccurs");
                    r.setAttribute("MaxOccurs", maxOccurs, null); // MaxOccurs
                    r.setAttribute("FoundElements", occurs, null);
                }
            }
            else if(occurs < minOccurs && EnumValidationLevel.isRequired(level)) 
            {
                r = parentReport.appendElement("MissingElement");
                r.setAttribute("CapXPath", dc.getNamePath(true));
                r.setAttribute("XPath", e.buildXPath(null,1) + "/" + dcName);
                r.setAttribute("Name", dcName);
                r.setAttribute("Message", "Element occurrence is less than value of MinOccurs");
                r.setAttribute("MinOccurs", minOccurs, null);
                r.setAttribute("FoundElements", occurs, null);
            }
            // there were elements that didn't pass some of the tests - assume that these are actually invalid and report on them
            for (int j = 0; j < occurs; j++)
            {
                KElement subEl = (KElement)vElem.elementAt(j);
                if(goodElems.contains(subEl))
                    continue;
                r = parentReport.appendElement("InvalidElement");
                KElement recursionResult = dc.stateReport(subEl,testlists,level,ignoreExtensions,true,r); 

                if(recursionResult==null)
                {
                    goodElems.add(subEl);
                    KElement badReport=(KElement)badElems.get(subEl);
                    if(badReport!=null)
                        badReport.deleteNode();
                }
                else
                {
                    badElems.put(subEl,recursionResult);
                }
                 
                if(recursionResult != null) 
                {
                    r.setAttribute("CapXPath", dc.getNamePath(true));
                    r.setAttribute("XPath", subEl.buildXPath(null,1));
                    r.setAttribute("Name", dcName);
                }
                else
                {
                    r.deleteNode();
                }
            }
        }
        
        if (!parentReport.hasChildElements())
        {
             parentReport = null;
        }
        
        return parentReport;
    }
    
    /**
     * Tests attributes and span elements (if <code>e</code> is a intent resource) 
     * of the element <code>e</code>.<br>
     * Checks whether they fit the corresponding State elements of <code>this</code>.<br>
     * Composes a detailed report of the errors found in XML form. 
     * If XMLDoc is <code>null</code> there are no errors.
     * 
     * @param e         element to test
     * @param testlists FitsValue_Allowed or FitsValue_Present testlists
     *                  that are specified for the State elements. 
     *                  (Will be used in fitsValue method of the State element.)
     * @param level     validation level
     * @return XMLDoc - XMLDoc output of the error messages. If XMLDoc is <code>null</code> 
     *                  there are no errors, <code>e</code> fits the corresponding State 
     *                  elements of <code>this</code>
     */
    private final KElement spanAndAttributesTest(KElement e, EnumFitsValue testlists, EnumValidationLevel level, boolean ignoreExtensions, KElement parentReport)
    {
        KElement msp = parentReport.appendElement("UnknownAttributes");
        KElement map = parentReport.appendElement("MissingAttributes");
        KElement iap = parentReport.appendElement("InvalidAttributes");
        
        //  vSubElem - contains all subelements of this DevCap
        VElement vStates = getStates(true,null); 
        JDFAttributeMap missMap=new JDFAttributeMap();
        JDFAttributeMap capMap=new JDFAttributeMap();
        
        final JDFAttributeMap defMap = e.getDefaultAttributeMap();
        JDFAttributeMap am = new JDFAttributeMap(defMap);
        am.putAll(e.getAttributeMap()); // only attribute map of 'e'   
        JDFAttributeMap m = getSpanAndAttributesMap(e); // get of 'e' as a map of attributes: (1) all attributes, 
        //(2) span key-values (in case of intent resource), (3) comments 
        VString vKeys = m.getKeys(); //we'll use "keys" to find the appropriate State elements in DevCap
       
        final int sizeStates = vStates.size();
        for (int j=0; j<sizeStates; j++) // SubElem can be DevCap, can be Loc, can be any State element
        {                                     // here we need only States
            JDFAbstractState state =(JDFAbstractState) vStates.item(j);
            String nam = state.getName();
            
            int size=vKeys.size();
            for (int i=size-1; i>=0; i--) 
            {  
                String key = (String) vKeys.elementAt(i);
                if (nam.equals(key) ||(key.equals("CommentText")&& nam.length()==0)) // if name is absent - state describes a Comment
                {
                    String value = nam.length()!=0 ? m.get(key) : m.get("CommentText");
                    if (!state.fitsValue(value,testlists)) 
                    { // The attribute/span was found but it has the wrong value
                        
                        KElement r;
                        if (!am.containsKey(key) && !key.equals("CommentText")) // it is Span but not Attribute
                        {
                            r = iap.appendElement("InvalidSpan");
                            r.setAttribute("XPath", e.buildXPath(null,1)+ "/"+ key);
                            r.setAttribute("Message", "Span value doesn't fit this State description");
                        }
                        else if (key.equals("CommentText"))
                        {
                            r = iap.appendElement("InvalidComment");
                            r.setAttribute("XPath", e.buildXPath(null,1)+ "/"+ key);
                            r.setAttribute("Message", "Comment doesn't fit this State description");
                        }
                        else 
                        {
                            r = iap.appendElement("InvalidAttribute");
                            r.setAttribute("XPath", e.buildXPath(null,1)+ "/@"+ key);
                            r.setAttribute("Message", "Attribute value doesn't fit this State description");
                        }
                        r.setAttribute("Name", key);
                        r.setAttribute("CapXPath", state.getNamePath());
                        r.setAttribute("Value", value);
                        r.copyElement(state,null);
                        
                    }
                    vKeys.removeElementAt(i); // The attribute/span was found, checked, so we don't need it any more in vKeys
                    break; // go to next State
                }
            }
            
            if ((size==vKeys.size()) && state.getRequired() && EnumValidationLevel.isRequired(level)) 
            { // No attribute/span found but state is required
                
                if (state.getListType().equals(EnumListType.Span)) 
                {
                    missMap.put(nam, "Span");
                }
                else 
                {
                    missMap.put(nam, "Attribute");
                }
                capMap.put(nam, state.getNamePath());
            }
        }
        if(EnumValidationLevel.isRequired(level))
        {
            VString missAts=e.getMissingAttributes(9999999);
            for(int i=0;i<missAts.size();i++)
                missMap.put(missAts.stringAt(i), "Attribute");
            missAts=e.getMissingElements(99999);
            for(int i=0;i<missAts.size();i++)
            {
                final String stringAt = missAts.stringAt(i);
                if(stringAt.endsWith("Span"))
                    missMap.put(stringAt, "Span");
            }
        }
        
        Enumeration missIt=missMap.keys();
        while (missIt.hasMoreElements())
        { // No attribute/span found but state is required
            String nam=(String)missIt.nextElement();
            String typ=missMap.get(nam);

            KElement ms = map.appendElement("Missing"+typ);
            ms.setAttribute("Message", "Missing "+typ);
            ms.setAttribute("XPath", e.buildXPath(null,1)+ "/@"+ nam);
            String capNamePath=capMap.get(nam);
            if(capNamePath!=null)
            ms.setAttribute("CapXPath", capNamePath);
            ms.setAttribute("Name", nam);
        }

        
        
        if(!ignoreExtensions)
        {
            for (int x=0; x<vKeys.size(); x++) 
            { // if there are in vKeys still some attibutes, they must be generic attributes
                String key = (String) vKeys.elementAt(x);

                if (!isGenericAttribute(key)&&(defMap==null || !defMap.containsKey(key))) 
                { // if the rest attributes are not generic -> Node is rejected
                    KElement us;
                    if(!am.containsKey(key) && !key.equals("CommentText")) 
                    {
                        us = msp.appendElement("UnknownSpan");
                        us.setAttribute("XPath", e.buildXPath(null,1)+"/"+key);
                        us.setAttribute("CapXPath", getNamePath(true)+"/"+key);
                    }
                    else 
                    {
                        us = msp.appendElement("UnknownAttribute");
                        us.setAttribute("XPath", e.buildXPath(null,1)+ "/@"+ key);
                        us.setAttribute("CapXPath", getNamePath(true)+"/@"+key);
                    }
                    us.setAttribute("Name", key);
                    us.setAttribute("Message", "No State description for "+key);
                }
            }
        }
        
        if (!map.hasChildElements())
            parentReport.removeChild(map);
        
        if (!iap.hasChildElements())
            parentReport.removeChild(iap);
        
        if (!msp.hasChildElements())
            parentReport.removeChild(msp);
        
        if (!parentReport.hasChildElements())
        {
            parentReport = null;
        }
        
        return parentReport;
    }
    
    
    
    /**
     * Gets a map of Attributes, Comments and Span key-value pairs for <code>e</code>.
     * All of them must be described as State elements.
     * 
     * @param e element to get the attribute map for
     * @return JDFAttributeMap - a map of Attributes, Comments and Span key-value pairs
     */
    private final JDFAttributeMap getSpanAndAttributesMap(KElement e)
    {
        JDFAttributeMap m = e.getDefaultAttributeMap();
        if(m!=null)
            m.putAll(e.getAttributeMap()); // get all attributes of 'e' as a map of attributes
        else
            m=e.getAttributeMap();
       
        if (e instanceof JDFResource)
        {
            JDFResource r = (JDFResource) e; // if 'e' is an Intent Resource
            // add to 'm' a 'spanMap'
            if (r.getResourceClass()==EnumResourceClass.Intent)
            { 
                JDFAttributeMap spanMap = getSpanValueMap(r);
                VString vSpanMapKeys = spanMap.getKeys();
                for (int k=0; k < vSpanMapKeys.size(); k++)
                {
                    String spanKey = (String) vSpanMapKeys.elementAt(k);
                    m.put(spanKey, spanMap.get(spanKey));
                }
            }
        }
        else if (e instanceof JDFComment) 
        {
            JDFComment c = (JDFComment) e;
            m.put("CommentText", c.getText(0)) ;
        }
        
        return m;
    }
    
    
    /**
     * Gets a map of key-value pairs for the intent resource <code>r</code>. 
     * The key of the map is a Span NodeName, 
     * the value is a combination of actual, preferred and range values
     * 
     * @param r intent resource to get the SpanValueMap of
     * @return JDFAttributeMap - SpanValueMap
     */
    private final JDFAttributeMap getSpanValueMap(JDFResource r)
    {
        JDFAttributeMap map = new JDFAttributeMap();
        VElement v = r.getChildElementVector(null, null, null, true, 0, false);
        for (int i=0; i < v.size(); i++)
        {
            KElement el =  v.item(i);
            if (el instanceof JDFSpanBase)
            {
//              JDFSpanBase span = (JDFSpanBase) el;
//              String value = span.getValue();
                
                String value = el.hasAttribute(AttributeName.ACTUAL) 
                ? el.getAttribute(AttributeName.ACTUAL) 
                        : el.getAttribute(AttributeName.PREFERRED);
                
                if (value.length()==0)
                    value = el.getAttribute(AttributeName.RANGE);
                
                map.put(el.getNodeName(), value);
            }
        }    
        return map;
    }
    
    
    /**
     * Tests if there are any subelements of the element elem that are not described by DevCap.<br>
     * Composes a detailed report of the found errors in XML form. 
     * If XMLDoc is <code>null</code> there are no errors.
     *  
     * @param elem element to test
     * @return KElement - output of the error messages. 
     *                  If XMLDoc is <code>null</code> there are no errors.
     */ 
    private final KElement missingDevCap(KElement elem, KElement parentReport)
    {
        KElement root = parentReport.appendElement("UnknownElements");        
        VElement vDevCap = getDevCapVector(null,true);
        
        VElement vSubElem = elem.getChildElementVector(null, null, null, true, 0, true); // follows the refelements
        // for every one child of the 'elem' we look for the corresponding DevCap description
        for (int i=0; i < vSubElem.size(); i++) 
        {
            KElement e = vSubElem.item(i);
            if (e instanceof JDFSpanBase) {
                continue; // Spans are described as State elements not as DevCap 
            }
            else if (e instanceof JDFNode) {
                continue; // nodes are described as high level DevCaps 
            }
            else
            {    
                String nam = e.getNodeName(); // we are looking for DevCap whose atr_Name is equal 'nam'
                boolean bFound = false;
                
                for (int k=0; k < vDevCap.size() && !bFound; k++) 
                {
                    JDFDevCap devCap = (JDFDevCap) vDevCap.elementAt(k);
                    if (devCap.getName().equals(nam)) // getName() as default takes the name of a parent DevCaps
                    { 
                        bFound = true;
                    }    
                }       
                if (!bFound) 
                {    
                    KElement us = root.appendElement("UnknownElement");
                    us.setAttribute("XPath", e.buildXPath(null,1));
                    us.setAttribute("CapXPath", getNamePath(false)+ JDFConstants.SLASH + nam);
                    us.setAttribute("Name", nam);
                    us.setAttribute("Message", "Found no DevCap description for this element");
                }
            }
        }
        
        if (!root.hasChildElements())
        {
            root.deleteNode();
            root = null;
        }        
        return root;
    }
    
    
    /**
     * Checks if the attributes key of the tested element is 
     * a generic attribute. (Gets this attribute of DeviceCap element)
     * 
     * @param key attribute key to test
     * @return boolean - true if the key is a generic attribute
     */        
    private final boolean isGenericAttribute(String key)
    {
        if(key==null)
            return true;
        if(key.startsWith(AttributeName.XMLNS))
            return true;
        
        KElement deviceCap = getDeepParent(ElementName.DEVICECAP,0);
        if(deviceCap==null)
            deviceCap = getDeepParent(ElementName.MESSAGESERVICE,0);
        if(deviceCap==null)
            return false;
        
        VString s = StringUtil.tokenize(deviceCap.getAttribute(AttributeName.GENERICATTRIBUTES)," ",false);
        return s!=null && (s.contains(key) || s.contains("*"));
    }
    
    
    /**
     * Gets the NamePath of this DevCap in form 
     * "DevCapsName/SubelemName1/SubelemName2/..."<br>
     * If this DevCap is located in DevCapPool and not in a DevCaps - it describes the reusable resource 
     * and DevCap root will have the attribute "Name" = value of DevCaps/@Name, 
     * but will have no info about DevCaps/@Context or DevCaps/@LinkUsage 
     * <p>
     * default: getNamePath(false)
     *
     * @param bRecurse if true, returns "DevCapsName/SubelemName1/SubelemName2/..."
     * @return String - NamePath of this DevCap   
     */
    public final String getNamePath(boolean bRecurse)
    {
        VString paths=getNamePathVector(bRecurse);
        if(paths==null || paths.size()<1)
            return null;
        return paths.stringAt(0);
            
    }
    
    /**
     * Gets the NamePath of this DevCap in form 
     * "DevCapsName/SubelemName1/SubelemName2/..."<br>
     * If this DevCap is located in DevCapPool and not in a DevCaps - it describes the reusable resource 
     * and DevCap root will have the attribute "Name" = value of DevCaps/@Name, 
     * but will have no info about DevCaps/@Context or DevCaps/@LinkUsage 
     *
     * @param bRecurse if true, returns "DevCapsName/SubelemName1/SubelemName2/..."
     * @return String - NamePath of this DevCap, null if no name is specified
     * 
     * default: getNamePath(false)  
     */
    public final VString getNamePathVector(boolean bRecurse)
    {
        String result = getName();    
        VString vResult=null;
        if(result==null)
            return null;
        
        final KElement parentNode = getParentNode_KElement();
        
        if (parentNode instanceof JDFDevCap)
        { //subsub elem - always recurse
            JDFDevCap devCap = (JDFDevCap) parentNode;
            vResult = devCap.getNamePathVector(bRecurse); 
        }
        else if (parentNode instanceof JDFDevCapPool) // if DevCap is located in DevCapPool it is reusable and there are no info from DevCaps(Context,LinkUsage)
        {
            if(bRecurse)
            {
                String id=getID();
                if(!id.equals(""))
                {
                    VElement v=parentNode.getChildrenByTagName("DevCap",null,null,false,true,0);
                    KElement deviceCap= parentNode.getParentNode_KElement();
 
                    for(int i=0;i<v.size();i++)
                    {
                        JDFDevCap dc=(JDFDevCap)v.elementAt(i);
                        VString refs=dc.getDevCapRefs();
                        if(refs.contains(id))
                        {
                            if(vResult==null)
                                vResult=new VString();
                            final String dcID=dc.getAttribute(AttributeName.ID,null,null);
                            JDFDevCaps dcs=(JDFDevCaps) (dcID==null ? null : deviceCap.getChildWithAttribute(ElementName.DEVCAPS,AttributeName.DEVCAPREF,null,dcID,0,true));
                            if(dcs!=null)
                            {
                                vResult.appendUnique(dcs.getNamePathVector());                                
                            }
                            else
                            {
                                vResult.appendUnique(dc.getNamePathVector(bRecurse));
                            }
                        }                        
                    }
                    JDFDevCaps dcs=(JDFDevCaps) deviceCap.getChildWithAttribute(ElementName.DEVCAPS,AttributeName.DEVCAPREF,null,id,0,true);                        
                    if(dcs!=null)
                    {
                        VString vResult2=dcs.getNamePathVector(); 
                        if(vResult==null)
                        {
                            vResult=vResult2;
                        }
                        else
                        {
                            StringUtil.concatStrings(vResult, "/"+result);
                            vResult.appendUnique(vResult2);
                        }
                        return vResult;
                    }
                    // TODO mixed mode devcaps subelements + devcappool
                }
            }
        }
        else if (parentNode instanceof JDFDevCaps) // need to add jdf in case of Context="Element" or Context="JDF"
        {
            JDFDevCaps devCaps = (JDFDevCaps) parentNode;
            vResult = devCaps.getNamePathVector();
            return vResult; // return here directly because the first devCap in the tree repeats the name of the devCaps
        }
        if(vResult==null)
        {
            vResult=new VString();
            vResult.add(result);
        }
        else
        {
            StringUtil.concatStrings(vResult, "/"+result);
        }
        return vResult;
    }

////////////////////////////////////////////////////////////////////////////////////////
    
    /**
     * gets String attribute Name, inherits from devcap or devcaps if necessary
     * 
     * @return String - the value of the attribute
     */
    public String getName()
    {
        String s = getAttribute(AttributeName.NAME, null, null);
        if (s==null) 
        {
            KElement parentNode=getParentNode_KElement();
            if (parentNode instanceof JDFDevCaps)
            {
                JDFDevCaps devCaps = (JDFDevCaps) getParentNode();
                s = devCaps.getName();
            }
            else if (parentNode instanceof JDFDevCap)
            {
                JDFDevCap devCap = (JDFDevCap) getParentNode();
                s = devCap.getName();
            }
            else if (parentNode instanceof JDFDevCapPool)
            {
                String id=getID();
                if(!id.equals(""))
                {
                    JDFDeviceCap dec=(JDFDeviceCap)parentNode.getParentNode();
                    JDFDevCaps dcs=(JDFDevCaps) dec.getChildWithAttribute(ElementName.DEVCAPS,AttributeName.DEVCAPREF,null,id,0,true);
                    if(dcs!=null)
                        s=dcs.getName();
                    
                }
             }
        }
         return s;
    }
        
    /**
     * return the vector of all states
     * @param bDirect if false, recurse into child elements, else return only direct child states
     * @param id      ID attribute of the requested string
     * @return VElement
     */
    public VElement getStates(boolean bDirect, String id)
    {
        JDFAttributeMap m=null;
        if(id!=null)
        {
            m=new JDFAttributeMap(AttributeName.ID,id);
        }
        VElement v=null;
        if(bDirect==true)
        {
            v=getChildrenByTagName(null,null,m,bDirect,true,0);
            for(int i=v.size()-1;i>=0;i--)
            {
                if(!(v.elementAt(i) instanceof JDFAbstractState))
                    v.remove(i);
            }
        }
        else
        {
            v=new VElement();
            VElement vdevCap=getDevCapVector(null,false); 
            for(int i=0;i<vdevCap.size();i++)
            {
                JDFDevCap child=(JDFDevCap) vdevCap.elementAt(i);
                v.appendUnique(child.getStates(true,id));
            }
        } 
        return v;
    }
    
    /**
     * Sets attribute <code>MaxOccurs</code>, 
     * also handles unbounded
     *
     * @param value the value to set the attribute to
     */
     public void setMaxOccurs(int value)
     {
         if(value==Integer.MAX_VALUE)
         {
             setAttribute(AttributeName.MAXOCCURS, JDFConstants.POSINF, null);
         }
         else
         {
             setAttribute(AttributeName.MAXOCCURS, value, null);
         }
     }

     /**
     * Gets integer attribute <code>MaxOccurs</code>,
     * also handles unbounded
     *
     * @return int: the attribute value 
     */
     public int getMaxOccurs()
     {
         final String s = getAttribute(AttributeName.MAXOCCURS, null, null);
         if(JDFConstants.UNBOUNDED.equals(s))
             return Integer.MAX_VALUE;
         return StringUtil.parseInt(s,1);
     }


     /**
      * gets the matching elements in the node that match <code>this</code>
      * 
      * @param node the node to search in
      * @param testValidity if true, recusively check for validity of the elements, 
      * else only get children by name
      * 
      * @return VElement - the element vector of matching elements, 
      * <code>null</code> if none were found
      */
     public VElement getMatchingElementsFromParent(KElement parent, VElement vDevCap)
     {
         VElement v= getAllMatchingElementsFromParent(parent);
         if(v==null)
             return null;

         final String _name=getName();
         VElement vOther=new VElement();
         if(vDevCap!=null)
         {
             for(int i=0;i<vDevCap.size();i++)
             {
                 JDFDevCap dcOther=(JDFDevCap) vDevCap.elementAt(i);
                 if(dcOther==this)
                     continue;
                 if(!_name.equals(dcOther.getName()))
                     continue;
                 vOther.add(dcOther);
              }
         }
         if(vOther.size()==0) // no other elements that we have to worry about
             return v;
         
         
         XMLDoc doc=new XMLDoc("dummy",null);
         KElement repRootDummy=doc.getRoot();
         for(int i=v.size()-1;i>=0;i--)
         {
             KElement e=v.item(i);
             repRootDummy.flush();
             if(spanAndAttributesTest(e, EnumFitsValue.Allowed, EnumValidationLevel.Incomplete, true, repRootDummy)!=null || 
                     subelementsTest(e, EnumFitsValue.Allowed, EnumValidationLevel.Incomplete, true, repRootDummy) !=null)
             {
                 // check if an element fits to a different devcap in this, if so remove it from the check
                 for(int j=0;j<vOther.size();j++)
                 {
                     repRootDummy.flush();
                     JDFDevCap dcOther=(JDFDevCap) vOther.elementAt(j);
                     if(dcOther.spanAndAttributesTest(e, EnumFitsValue.Allowed, EnumValidationLevel.Incomplete, true, repRootDummy)==null && 
                             dcOther.subelementsTest(e, EnumFitsValue.Allowed, EnumValidationLevel.Incomplete, true, repRootDummy) ==null)
                     {
                         v.remove(i);
                         break;   // j                      
                     }
                 }                 
             }
         }
         return v.size()!=0 ? v : null;
     }

     /**
      * gets the matching elements in the node that match the nodename of this
      * 
      * @param node the node to search in
      * @return VElement - the element vector of matching elements, 
      * <code>null</code> if none were found
      */
     public VElement getAllMatchingElementsFromParent(KElement parent)
     {
         String nam=getName();
         VElement subElems=parent.getChildElementVector(nam,null,null,true,999999,true);
         return subElems.size()==0 ? null : subElems;
     }

     /**
      * sets the element and attribute defaults
      * 
	 * @param element the element that is defaulted
     * @param bAll if false, only add if minOccurs>=1 and required=true or a default exists
	 * @return ignored
	 */
    public boolean setDefaultsFromCaps(KElement element, boolean bAll)
    {
        boolean success=false;
        if(element instanceof JDFResource)
        {
            JDFResource r=(JDFResource)element;
            if(!r.isLeaf())
            {
                VElement vR=r.getLeaves(false);
                for(int i=0;i<vR.size();i++)
                {
                    success=setDefaultsFromCaps(vR.item(i),bAll) || success;
                }
                return success;
            }
        }
        // default leaf or element case -
        VElement vSubDevCap=null;
        try
        {
            vSubDevCap = getDevCapVector(null,true);
        }
        catch(JDFException x)
        {
            return false;
        }
        for(int i=0;i<vSubDevCap.size();i++)
        {
            JDFDevCap subDevCap=(JDFDevCap) vSubDevCap.elementAt(i);
            int minOccurs = subDevCap.getMinOccurs();
            if(minOccurs==0 && bAll)
                minOccurs=1;
            VElement subElms=subDevCap.getMatchingElementsFromParent(element,vSubDevCap);
            if(minOccurs>0)
            {
                int occurs=subElms==null ? 0 : subElms.size();
                if(occurs<minOccurs && subElms==null)
                    subElms=new VElement();
                
                for(int ii=occurs;ii<minOccurs;ii++)
                {
                    final String id=subDevCap.getID();                   
                    KElement isThere=id==null ? null : element.getOwnerDocument_KElement().getRoot().getTarget(id, AttributeName.ID);
                    if(!(isThere instanceof JDFResource) || !(element instanceof JDFElement))
                    {
                        KElement newSub=element.appendElement(subDevCap.getName(),subDevCap.getDevNS());
                        subElms.add(newSub);
                    }
                    else
                    {
                        JDFRefElement re=((JDFElement)element).refElement((JDFResource)isThere);
                        subElms.add(re.getTarget());
                    }
                    success=true;
                }
            }
            final int subSize= subElms==null ? 0 : subElms.size();
            for(int ii=0;ii<subSize;ii++)
            {
                success = subDevCap.setDefaultsFromCaps(subElms.item(ii), bAll) || success;
            }            
        }
        // states
        VElement vStates=getStates(true,null);
        for(int i=0;i<vStates.size();i++)
        {
            JDFAbstractState state=(JDFAbstractState)vStates.elementAt(i);
            success = state.setDefaultsFromCaps(element, bAll) || success;
        }

        return false;
    }

    /* (non-Javadoc)
     * @see org.cip4.jdflib.core.JDFElement#getInvalidAttributes(org.cip4.jdflib.core.KElement.EnumValidationLevel, boolean, int)
     */
    public VString getInvalidAttributes(EnumValidationLevel level, boolean bIgnorePrivate, int nMax)
    {
        VString vs= super.getInvalidAttributes(level, bIgnorePrivate, nMax);
        if(nMax>0 && vs.size()>nMax)
            return vs;

        if(EnumValidationLevel.isRecursive(level)&& !vs.contains(AttributeName.DEVCAPREFS) && hasAttribute(AttributeName.DEVCAPREFS))
        {
            JDFDeviceCap deviceCap = (JDFDeviceCap) getDeepParent(ElementName.DEVICECAP, 0);
            JDFDevCapPool devCapPool = deviceCap == null ? null : deviceCap.getDevCapPool();
            if (devCapPool == null)
            {
                vs.add(AttributeName.DEVCAPREFS);
                return vs;
            }
            VString idRefs = getDevCapRefs();
            for (int i=0; i < idRefs.size(); i++) 
            {
                JDFDevCap devCap = devCapPool.getDevCap(idRefs.stringAt(i));
                if (devCap==null)
                {
                    vs.add(AttributeName.DEVCAPREFS);
                    return vs;
                }
            }
        }  
        String nam=getName();
        if(!vs.contains(AttributeName.NAME) && nam!=null && getDevNS().equals(JDFElement.getSchemaURL()))
        {
            nam=KElement.xmlnsLocalName(nam);
            JDFDoc tmpDoc=new JDFDoc(nam);
            KElement tmpRoot=tmpDoc.getRoot();
            if(JDFElement.class.equals(tmpRoot.getClass()))
            {
                vs.add(AttributeName.NAME);
            }           
        }
        return vs;
    }


///////////////////////////////////////////////////////     
}
