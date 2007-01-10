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

import java.util.Vector;

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
import org.cip4.jdflib.core.JDFElement;
import org.cip4.jdflib.core.JDFException;
import org.cip4.jdflib.core.KElement;
import org.cip4.jdflib.core.VElement;
import org.cip4.jdflib.core.VString;
import org.cip4.jdflib.core.XMLDoc;
import org.cip4.jdflib.datatypes.JDFAttributeMap;
import org.cip4.jdflib.datatypes.JDFBaseDataTypes.EnumFitsValue;
import org.cip4.jdflib.node.JDFNode;
import org.cip4.jdflib.resource.JDFResource;
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
     * @param ownerDocument
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
     * @param ownerDocument
     * @param namespaceURI
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
     * @param ownerDocument
     * @param namespaceURI
     * @param qualifiedName
     * @param localName
     */
    public JDFDevCap(
            CoreDocumentImpl myOwnerDocument,
            String myNamespaceURI,
            String qualifiedName,
            String myLocalName)
    {
        super(myOwnerDocument, myNamespaceURI, qualifiedName, myLocalName);
    }
    
    public String toString()
    {
        return "JDFDevCap[  --> " + super.toString() + " ]";
    }
    
    
    
    
    /**
     * set attribute DevCapRefs
     * @param VString value: the value to set the attribute to
     */
    public void setDevCapRefs(VString value)
    {
        setAttribute(AttributeName.DEVCAPREFS, value, null);
    }
    
    /**
     * get IDREFS attribute DevCapRefs
     * @return VString the value of the attribute
     */
    public VString getDevCapRefs()
    {
        return new VString (getAttribute(AttributeName.DEVCAPREFS, null, JDFConstants.EMPTYSTRING), null);
    }
    
    
    
    /**
     * set attribute ID
     * @param String value: the value to set the attribute to
     */
    public void setID(String value)
    {
        setAttribute(AttributeName.ID, value, null);
    }
    
    /**
     * get String attribute ID
     * @return String the value of the attribute
     */
    public String getID()
    {
        return getAttribute(AttributeName.ID, null, JDFConstants.EMPTYSTRING);
    }
    
    
    
    /**
     * getAvailability - gets typesafe enumerated attribute Availability
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
    
    public JDFDevCap getDevCap(int iSkip) 
    {
        return (JDFDevCap)getElement(ElementName.DEVCAP, JDFConstants.EMPTYSTRING, iSkip);
    }
    
    //  ///////////////////////////////////////////////////////////////////
    
    public JDFDevCap getCreateDevCap(int iSkip)
    {
        return (JDFDevCap)getCreateElement(ElementName.DEVCAP, JDFConstants.EMPTYSTRING , iSkip);
    }
    
    //  ///////////////////////////////////////////////////////////////////
    
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
     * gets the i'th existing BooleanState 
     * @param iSkip 
     * @return JDFBooleanState the existing BooleanState
     */
    public JDFBooleanState getBooleanState(int iSkip) 
    {
        return (JDFBooleanState)getElement(ElementName.BOOLEANSTATE, null, iSkip);
     }
    
    //  ///////////////////////////////////////////////////////////////////
    
    /**
     * gets an existing  BooleanState with @Name="name"
     * @param name the Name attribute of the newly appended BooleanState
     * @return JDFBooleanState the existing BooleanState
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
     * gets a NumberState with @Name="name", appends it if it does not yet exist
     * @param name the Name attribute of the newly appended NumberState
     * @return JDFNumberState the existing or newly appended NumberState
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
     * @param name the Name attribute of the newly appended BooleanState
     * @return JDFBooleanState the newly appended BooleanState
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
     * gets the i'th existing IntegerState 
     * @param iSkip 
     * @return JDFIntegerState the existing IntegerState
     */
    public JDFIntegerState getIntegerState(int iSkip)
    {
        JDFIntegerState e = (JDFIntegerState)getElement(ElementName.INTEGERSTATE, null, iSkip);
        return e;
    }
    
    //  ///////////////////////////////////////////////////////////////////
    
    /**
     * gets an existing IntegerState with @Name="name"
     * @param name the Name attribute of the newly appended IntegerState
     * @return JDFIntegerState the existing IntegerState
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
     * gets a IntegerState with @Name="name", appends it if it does not yet exist
     * @param name the Name attribute of the newly appended IntegerState
     * @return JDFIntegerState the existing or newly appended IntegerState
     */
    public JDFIntegerState getCreateIntegerState(String nam)
    {
        JDFIntegerState s=getIntegerState(nam);
        if(s==null)
            s=appendIntegerState(nam);
        return s;
    }
    
    //  ///////////////////////////////////////////////////////////////////
    
    public JDFIntegerState appendIntegerState()
    {
        return (JDFIntegerState)appendElement(ElementName.INTEGERSTATE, null);
    }
    
    //  ///////////////////////////////////////////////////////////////////
    
    /**
     * appends a IntegerState with @Name="name"
     * @param name the Name attribute of the newly appended IntegerState
     * @return JDFIntegerState the newly appended IntegerState
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
     * gets the i'th existing  NumberState 
     * @param iSkip 
     * @return JDFNumberState the existing NumberState
     */
    public JDFNumberState getNumberState(int iSkip)
    {
        return (JDFNumberState)getElement(ElementName.NUMBERSTATE, null, iSkip);
    }
    
    //  ///////////////////////////////////////////////////////////////////
    /**
     * gets an existing  NumberState with @Name="name"
     * @param name the Name attribute of the newly appended NumberState
     * @return JDFNumberState the existing NumberState
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
     * @param name the Name attribute of the newly appended NumberState
     * @return JDFNumberState the existing or newly appended NumberState
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
     * @param name the Name attribute of the newly appended NumberState
     * @return JDFNumberState the newly appended NumberState
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
    
    public JDFEnumerationState getEnumerationState(int iSkip)
    {
        return (JDFEnumerationState)getElement(ElementName.ENUMERATIONSTATE, null, iSkip);
    }
    
    //  ///////////////////////////////////////////////////////////////////
    
    /**
     * gets an existing  EnumerationState with @Name="name"
     * @param name the Name attribute of the newly appended EnumerationState
     * @return JDFEnumerationState the existing EnumerationState
     */
    public JDFEnumerationState getEnumerationState(String nam)
    {
        return (JDFEnumerationState)getChildWithAttribute(ElementName.ENUMERATIONSTATE, AttributeName.NAME, null, nam, 0, true);
    }
    
    //  ///////////////////////////////////////////////////////////////////
    
    public JDFEnumerationState getCreateEnumerationState(int iSkip)
    {
        return (JDFEnumerationState)getCreateElement(ElementName.ENUMERATIONSTATE, null, iSkip);
    }
    
    //  ///////////////////////////////////////////////////////////////////
    
    /**
     * gets a EnumerationState with @Name="name", appends it if it does not yet exist
     * @param name the Name attribute of the newly appended EnumerationState
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
     * @param name the Name attribute of the newly appended NumberState
     * @return JDFNumberState the newly appended NumberState
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
     * gets the i'th existing NameState 
     * @param iSkip 
     * @return JDFNameState the existing NameState
     */
    public JDFNameState getNameState(int iSkip)
    {
        return (JDFNameState)getElement(ElementName.NAMESTATE, null, iSkip);
    }
    
    //  ///////////////////////////////////////////////////////////////////
    
    /**
     * gets an existing  NameState with @Name="name"
     * @param name the Name attribute of the newly appended NameState
     * @return JDFNameState the existing NameState
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
     * gets a NameState with @Name="name", appends it if it does not yet exist
     * @param name the Name attribute of the newly appended NameState
     * @return JDFNameState the existing or newly appended NameState
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
     * @param name the Name attribute of the newly appended NameState
     * @return JDFNameState the newly appended NameState
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
     * gets the i'th existing StringState 
     * @param iSkip 
     * @return JDFStringState the existing StringState
     */
    public JDFStringState getStringState(int iSkip)
    {
        return (JDFStringState)getElement(ElementName.STRINGSTATE, null, iSkip);
    }
    
    //  ///////////////////////////////////////////////////////////////////
    
    /**
     * gets an existing  StringState with @Name="name"
     * @param name the Name attribute of the newly appended StringState
     * @return JDFStringState the existing StringState
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
     * @param name the Name attribute of the newly appended StringState
     * @return JDFStringState the existing or newly appended StringState
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
     * @param name the Name attribute of the newly appended StringState
     * @return JDFStringState the newly appended StringState
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
     * gets the i'th existing XYPairState 
     * @param iSkip 
     * @return JDFXYPairState the existing XYPairState
     */
    public JDFXYPairState getXYPairState(int iSkip)
    {
        return (JDFXYPairState) getElement(ElementName.XYPAIRSTATE, null, iSkip);
    }
    
    //  ///////////////////////////////////////////////////////////////////

    /**
     * gets an existing  XYPairState with @Name="name"
     * @param name the Name attribute of the newly appended XYPairState
     * @return JDFXYPairState the existing XYPairState
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
     * @param name the Name attribute of the newly appended XYPairState
     * @return JDFXYPairState the existing or newly appended XYPairState
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
     * @param name the Name attribute of the newly appended XYPairState
     * @return JDFXYPairState the newly appended XYPairState
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
     * gets the i'th existing ShapeState 
     * @param iSkip 
     * @return JDFShapeState the existing ShapeState
     */
    public JDFShapeState getShapeState(int iSkip)
    {
        return (JDFShapeState)getElement(ElementName.SHAPESTATE, null, iSkip);
    }
    
    //  ///////////////////////////////////////////////////////////////////

    /**
     * gets an existing  ShapeState with @Name="name"
     * @param name the Name attribute of the newly appended ShapeState
     * @return JDFShapeState the existing ShapeState
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
     * @param name the Name attribute of the newly appended ShapeState
     * @return JDFShapeState the existing or newly appended ShapeState
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
     * @param name the Name attribute of the newly appended ShapeState
     * @return JDFShapeState the newly appended ShapeState
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
     * gets the i'th existing MatrixState 
     * @param iSkip 
     * @return JDFMatrixState the existing MatrixState
     */
    public JDFMatrixState getMatrixState(int iSkip)
    {
        return(JDFMatrixState)getElement(ElementName.MATRIXSTATE, null, iSkip);
    }
    
    //  ///////////////////////////////////////////////////////////////////

    /**
     * gets an existing  MatrixState with @Name="name"
     * @param name the Name attribute of the newly appended MatrixState
     * @return JDFMatrixState the existing MatrixState
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
     * @param name the Name attribute of the newly appended MatrixState
     * @return JDFMatrixState the existing or newly appended MatrixState
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
     * @param name the Name attribute of the newly appended MatrixState
     * @return JDFMatrixState the newly appended MatrixState
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
     * gets the i'th existing DateTimeState 
     * @param iSkip 
     * @return JDFDateTimeState the existing DateTimeState
     */    
    public JDFDateTimeState getDateTimeState(int iSkip)
    {
        return (JDFDateTimeState)getElement(ElementName.DATETIMESTATE, null, iSkip);
    }
    
    //  ///////////////////////////////////////////////////////////////////

    /**
     * gets an existing  DateTimeState with @Name="name"
     * @param name the Name attribute of the newly appended DateTimeState
     * @return JDFDateTimeState the existing DateTimeState
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
     * @param name the Name attribute of the newly appended DateTimeState
     * @return JDFDateTimeState the existing or newly appended DateTimeState
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
     * @param name the Name attribute of the newly appended DateTimeState
     * @return JDFDateTimeState the newly appended DateTimeState
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
     * gets the i'th existing DurationState 
     * @param iSkip 
     * @return JDFDurationState the existing DurationState
     */
    public JDFDurationState getDurationState(int iSkip)
    {
        return (JDFDurationState) getElement(ElementName.DURATIONSTATE, null, iSkip);
    }
    
    //  ///////////////////////////////////////////////////////////////////

    /**
     * gets an existing  DurationState with @Name="name"
     * @param name the Name attribute of the newly appended DurationState
     * @return JDFDurationState the existing DurationState
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
     * @param name the Name attribute of the newly appended DurationState
     * @return JDFDurationState the existing or newly appended DurationState
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
     * @param name the Name attribute of the newly appended DurationState
     * @return JDFDurationState the newly appended DurationState
     */
    public JDFDurationState appendDurationState(String nam)
    {
        JDFDurationState s= (JDFDurationState)appendElement(ElementName.DURATIONSTATE, null);
        s.setName(nam);
        return s;
    }
    
    //  ///////////////////////////////////////////////////////////////////
    
    /**
     * gets the i'th existing PDFPathState 
     * @param iSkip 
     * @return JDFPDFPathState the existing PDFPathState
     */
    public JDFPDFPathState getPDFPathState(int iSkip)
    {
        return(JDFPDFPathState)getElement(ElementName.PDFPATHSTATE, null, iSkip);
    }
    
    //  ///////////////////////////////////////////////////////////////////

    /**
     * gets an existing  PDFPathState with @Name="name"
     * @param name the Name attribute of the newly appended PDFPathState
     * @return JDFPDFPathState the existing PDFPathState
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
     * @param name the Name attribute of the newly appended PDFPathState
     * @return JDFPDFPathState the existing or newly appended PDFPathState
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
     * @param name the Name attribute of the newly appended PDFPathState
     * @return JDFPDFPathState the newly appended PDFPathState
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
     * gets the i'th existing RectangleState 
     * @param iSkip 
     * @return JDFRectangleState the existing RectangleState
     */
    public JDFRectangleState getRectangleState(int iSkip)
    {
        return (JDFRectangleState)getElement(ElementName.RECTANGLESTATE, null, iSkip);
    }
    
    //  ///////////////////////////////////////////////////////////////////

    /**
     * gets an existing  RectangleState with @Name="name"
     * @param name the Name attribute of the newly appended RectangleState
     * @return JDFRectangleState the existing RectangleState
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
     * @param name the Name attribute of the newly appended RectangleState
     * @return JDFRectangleState the existing or newly appended RectangleState
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
     * @param name the Name attribute of the newly appended RectangleState
     * @return JDFRectangleState the newly appended RectangleState
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
            JDFDevCapPool devCapPool = deviceCap.getDevCapPool();
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
     * match the corresponding States and DevCap subelements of this DevCap
     * Composes a detailed report of the found errors in XML form. 
     * If XMLDoc equals null - there are no errors
     * 
     * @param KElement e - element to test
     * @param EnumFitsValue testlists - FitsValue_Allowed or FitsValue_Present testlists
     * that are specified for the State elements. (Will be used in fitsValue method of the State element)
     * @param EnumValidationLevel level - validation level
     * @return XMLDoc - XMLDoc output of the error messages. If XMLDoc equals null - there are no errors,
     * element 'e' fits the corresponding States and DevCap subelements of this DevCap
     */
    public final XMLDoc stateReport(KElement e, EnumFitsValue testlists, EnumValidationLevel level)
    {
        XMLDoc testResult = new XMLDoc("Temp", null);
        KElement root = testResult.getRoot();
        
        // 'e' in DeviceCapabilities is described by this DevCap
        
        // first test if there are any subelements of 'e' that are not described by DevCap
        XMLDoc temp=null;
        if(!(e instanceof JDFNode))
        {
            temp = missingDevCap(e);
        }
        if (temp != null) 
        {
            root.copyElement(temp.getRoot(), null);
        }
        
        // DevCap contains: (1) description of parts; 
        // (2) description of subelements; 
        // (3) description of attributes
        
        // (1) Test Partition Leaves: 'e' - is a root of partition, its leaves must be described by 'this' DevCap
        if ((e instanceof JDFResource) && 
                e.hasAttribute_KElement(AttributeName.PARTIDKEYS, null, false)) 
        {
            JDFResource res = (JDFResource) e;
            final VElement vLeaves = res.getLeaves(false);
            
            final int size = vLeaves.size();
            for (int i=0; i < size; i++)
            {
                JDFResource leaf = (JDFResource) vLeaves.elementAt(i);
                XMLDoc partTestResult = stateReport(leaf,testlists,level);
                if(partTestResult != null) 
                {
                    KElement p = root.appendElement("InvalidPartitionLeaf");
                    String path = leaf.buildXPath();
                    p.setAttribute("XPath",path);
                    String leafPath = path.substring(res.buildXPath().length());
                    p.setAttribute("LeafXPath",res.getNodeName()+leafPath);
                    moveChildElementVector_Static(p,partTestResult.getRoot());
                }
            }
        }
        
        // (2) Test Subelements - described by DevCap that 'this' DevCap consists of
        XMLDoc subelemTestResult = subelementsTest(e,testlists,level);
        if(subelemTestResult!=null) 
        {
            root.copyElement(subelemTestResult.getRoot(),null);
        }   
        
        // (3) Test Attributes, Spans and Comments - described by States
        XMLDoc attrTestResult = spanAndAttributesTest(e,testlists,level);
        if(attrTestResult!=null) 
        {
            moveChildElementVector_Static(root,attrTestResult.getRoot());
        }
 
        if (!root.hasChildElements())
            testResult = null;
        
        return testResult;
    }
    
    
    
    /**
     * Tests subelements of the element 'e'
     * whether they fit the corresponding DevCap elements of 'this' DevCap.
     * ! Recursive returns to stateReport to test the attributes of the subelements.
     * Composes a detailed report of the found errors in XML form. 
     * If XMLDoc equals null - there are no errors
     * 
     * @param e - element to test
     * @param EnumFitsValue testlists - FitsValue_Allowed or FitsValue_Present testlists
     * that are specified for the State elements. (Will be used in fitsValue method of the State element)
     * @param EnumValidationLevel level - validation level
     * @return XMLDoc - XMLDoc output of the error messages. If XMLDoc equals null - there are no errors,
     * 'e' fits the corresponding DevCap elements of 'this' DevCap
     */
    private final XMLDoc subelementsTest(KElement e, EnumFitsValue testlists, EnumValidationLevel level)
    {
        XMLDoc testResult = new XMLDoc("InvalidSubelements", null);
        KElement root = testResult.getRoot();
        
        VElement vDevCap = getDevCapVector(null,true); // vDevCap - contains DevCap elements of this DevCap
        
        for (int k=0; k < vDevCap.size(); k++) 
        { // if there are any DevCap subelements in this DavCap
            // then element e must have subelements, and we are going to check them first
            JDFDevCap dc = (JDFDevCap) vDevCap.elementAt(k);
            String dcName = dc.getName();
            
            // vElem - direct children of the Node. 
            // If 'dcName' is a partition Leaf - gets only children of the Leaf.
            VElement vElem = dc.getMatchingElementsFromParent(e);
            final int occurs = vElem==null ? 0 : vElem.size();
            KElement r;
            final int minOccurs = dc.getMinOccurs();
            final int maxOccurs = dc.getMaxOccurs();
            if( occurs > maxOccurs) 
            {
                for (int j = 0; j < occurs; j++)
                {
                    KElement subEl = vElem.item(j);
                    r = root.appendElement("InvalidSubelement");
                    r.setAttribute("CapXPath", dc.getNamePath(true));
                    r.setAttribute("XPath", subEl.buildXPath());
                    r.setAttribute("Name", dcName);
                    r.setAttribute("Message", "Element occurrence exceeds value of MaxOccurs");
                    r.setAttribute("MaxOccurs", maxOccurs, null); // MaxOccurs
                    r.setAttribute("FoundElements", occurs, null);
                    XMLDoc recursionResult = dc.stateReport(subEl,testlists,level); 
                    if(recursionResult != null) 
                    {
                        moveChildElementVector_Static(r,recursionResult.getRoot());
                    }
                }
            }
            else if(occurs < minOccurs && requiredLevel(level)) 
            {
                for (int j = 0; j < occurs; j++)
                {
                    KElement subEl = vElem.item(j);
                    r = root.appendElement("InvalidSubelement");
                    r.setAttribute("CapXPath", dc.getNamePath(true));
                    r.setAttribute("XPath", subEl.buildXPath());
                    r.setAttribute("Name", dcName);
                    r.setAttribute("Message", "Element occurrence is less than value of MinOccurs");
                    r.setAttribute("MinOccurs", minOccurs, null); // MinOccurs
                    r.setAttribute("FoundElements", occurs, null);
                    XMLDoc recursionResult = dc.stateReport(subEl,testlists,level); 
                    if(recursionResult != null) 
                    {
                        moveChildElementVector_Static(r,recursionResult.getRoot());
                    }
                }
                for (int m = occurs; m < minOccurs; m++) // the rest are missing elements
                {
                    r = root.appendElement("MissingSubelement");
                    r.setAttribute("CapXPath", dc.getNamePath(true));
                    r.setAttribute("XPath", e.buildXPath() + "/" + dcName);
                    r.setAttribute("Name", dcName);
                    r.setAttribute("Message", "Element occurrence is less than value of MinOccurs");
                    r.setAttribute("MinOccurs", minOccurs, null);
                    r.setAttribute("FoundElements", occurs, null);
                }
            }
            else
            {
                for (int j = 0; j < occurs; j++)
                {
                    JDFElement subEl = (JDFElement)vElem.elementAt(j);
                    XMLDoc recursionResult = dc.stateReport(subEl,testlists,level); 
                    if(recursionResult != null) 
                    {
                        r = root.appendElement("InvalidSubelement");
                        r.setAttribute("CapXPath", dc.getNamePath(true));
                        r.setAttribute("XPath", subEl.buildXPath());
                        r.setAttribute("Name", dcName);
                        moveChildElementVector_Static(r,recursionResult.getRoot());
                    }
                }
            }
        }
        
        if (!root.hasChildElements())
            testResult = null;
        
        return testResult;
    }
    
    /**
     * Tests attributes and span elements (if 'e' is a intent resource) of the element 'e'.
     * Checks, whether they fit the corresponding State elements of 'this' DevCap.
     * Composes a detailed report of the found errors in XML form. If XMLDoc equals null - there are no errors.
     * 
     * @param e - element to test
     * @param testlists - FitsValue_Allowed or FitsValue_Present testlists
     * that are specified for the State elements. (Will be used in fitsValue method of the State element)
     * @param level - validation level
     * @return XMLDoc - XMLDoc output of the error messages. If XMLDoc equals null - there are no errors, 
     * 'e' fits the corresponding State elements of 'this' DevCap
     */
    private final XMLDoc spanAndAttributesTest(KElement e, EnumFitsValue testlists, EnumValidationLevel level)
    {
        XMLDoc testResult = new XMLDoc("Temp", null);
        KElement root = testResult.getRoot();
        KElement msp = root.appendElement("UnknownAttributes");
        KElement map = root.appendElement("MissingAttributes");
        KElement iap = root.appendElement("InvalidAttributes");
        
        //  vSubElem - contains all subelements of this DevCap
        VElement vStates = getStates(true,null); 
        
        JDFAttributeMap am = e.getAttributeMap(); // only attribute map of 'e'   
        JDFAttributeMap m = getSpanAndAttributesMap(e); // get of 'e' as a map of attributes: (1) all attributes, 
        //(2) span key-values (in case of intent resource), (3) comments 
        VString vKeys = m.getKeys(); //we'll use "keys" to find the appropriate State elements in DevCap
       
        final int sizeStates = vStates.size();
        for (int j=0; j<sizeStates; j++) // SubElem can be DevCap, can be Loc, can be any State element
        {                                     // here we need only States
            JDFAbstractState state =(JDFAbstractState) vStates.item(j);
            String nam = state.getName();
            
            int size=vKeys.size();
            boolean bExit = false;
            for (int i=size-1; i>=0 && !bExit; i--) 
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
                            r.setAttribute("XPath", e.buildXPath()+ "/"+ key);
                            r.setAttribute("Message", "Span value doesn't fit this State description");
                        }
                        else if (key.equals("CommentText"))
                        {
                            r = iap.appendElement("InvalidComment");
                            r.setAttribute("XPath", e.buildXPath()+ "/"+ key);
                            r.setAttribute("Message", "Comment doesn't fit this State description");
                        }
                        else 
                        {
                            r = iap.appendElement("InvalidAttribute");
                            r.setAttribute("XPath", e.buildXPath()+ "/@"+ key);
                            r.setAttribute("Message", "Attribute value doesn't fit this State description");
                        }
                        r.setAttribute("Name", key);
                        r.setAttribute("CapXPath", state.getNamePath());
                        r.setAttribute("Value", value);
                        r.copyElement(state,null);
                        
                    }
                    vKeys.removeElementAt(i); // The attribute/span was found, checked, so we don't need it any more in vKeys
                    bExit = true; // go to another State
                }
            }
            
            if ((size==vKeys.size()) && (state.getRequired()) && requiredLevel(level)) 
            { // No attribute/span found but state is required
                KElement ms;
                if (state.getListType().equals(EnumListType.Span)) 
                {
                    ms = map.appendElement("MissingSpan");
                    ms.setAttribute("Message", "Missing Span");
                }
                else 
                {
                    ms = map.appendElement("MissingAttribute");
                    ms.setAttribute("Message", "Missing Attribute");
                }
                ms.setAttribute("XPath", e.buildXPath()+ "/@"+ nam);
                ms.setAttribute("CapXPath", state.getNamePath());
                ms.setAttribute("Name", nam);
            }
        }
        for (int x=0; x<vKeys.size(); x++) 
        { // if there are in vKeys still some attibutes, they must be generic attributes
            String key = (String) vKeys.elementAt(x);
            
            if (!isGenericAttribute(key)) 
            { // if the rest attributes are not generic -> Node is rejected
                KElement us;
                if(!am.containsKey(key) && !key.equals("CommentText")) 
                {
                    us = msp.appendElement("UnknownSpan");
                    us.setAttribute("XPath", e.buildXPath()+"/"+key);
                    us.setAttribute("CapXPath", getNamePath(true)+"/"+key);
                }
                else 
                {
                    us = msp.appendElement("UnknownAttribute");
                    us.setAttribute("XPath", e.buildXPath()+ "/@"+ key);
                    us.setAttribute("CapXPath", getNamePath(true)+"/@"+key);
                }
                us.setAttribute("Name", key);
                us.setAttribute("Message", "No State description for "+key);
            }
        }
        
        if (!map.hasChildElements())
            root.removeChild(map);
        
        if (!iap.hasChildElements())
            root.removeChild(iap);
        
        if (!msp.hasChildElements())
            root.removeChild(msp);
        
        if (!root.hasChildElements())
            testResult = null;
        
        return testResult;
    }
    
    
    
    /**
     * Gets of the element 'e' a map of Attributes, Comments and Span key-value pairs.
     * All of them must be described as State elements
     * 
     * @param e - element to get the attribute map of
     * @return JDFAttributeMap - a map of Attributes, Comments and Span key-value pairs
     */
    private final JDFAttributeMap getSpanAndAttributesMap(KElement e)
    {
        JDFAttributeMap m = e.getAttributeMap(); // get all attributes of 'e' as a map of attributes
        
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
     * Gets of the intent resource 'r' a map of key-value pairs, where key is a Span NodeName
     * and value is a combiantion of actual, preferred and range values
     * 
     * @param r - intent resource to get the SpanValueMap of
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
     * Tests if there are any subelements of the element elem that are not described by DevCap
     * Composes a detailed report of the found errors in XML form. If XMLDoc equals null - there are no errors.
     *  
     * @param elem - element we test
     * @return XMLDoc - XMLDoc output of the error messages. If XMLDoc equals null - there are no errors
     */ 
    private final XMLDoc missingDevCap(KElement elem)
    {
        XMLDoc testResult = new XMLDoc("UnknownSubelements", null); // temporary root
        KElement root = testResult.getRoot();
        
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
                    KElement us = root.appendElement("UnknownSubelement");
                    us.setAttribute("XPath", e.buildXPath());
                    us.setAttribute("CapXPath", getNamePath(false)+ JDFConstants.SLASH + nam);
                    us.setAttribute("Name", nam);
                    us.setAttribute("Message", "Found no DevCap description for this element");
                }
            }
        }
        
        if (!root.hasChildElements())
            testResult = null;
        
        return testResult;
    }
    
    
    /**
     * Checks if the attributes key of the tested element is 
     * a generic attribute. (Gets this attribute of DeviceCap element)
     * 
     * @param String key - attribute key to test
     * @return boolean - true if the key is a generic attribute
     */        
    private final boolean isGenericAttribute(String key)
    {
        JDFDeviceCap deviceCap = (JDFDeviceCap)getDeepParent(ElementName.DEVICECAP,0);
        VString s = deviceCap.getGenericAttributes();
        return s.contains(key) || s.contains("*");
    }
    
    
    /**
     * Gets the NamePath of this DevCap in form 
     * "DevCapsName/SubelemName1/SubelemName2/..."
     * If this DevCap is located in DevCapPool and not in a DevCaps - it describes the reusable resource 
     * and DevCap root will have the attribute "Name" = value of DevCaps/@Name 
     * but will have no info about DevCaps/@Context or DevCaps/@LinkUsage 
     *
     * @param bRecurse - if true, returns "DevCapsName/SubelemName1/SubelemName2/..."
     * @return String - NamePath of this DevCap
     * 
     * default: getNamePath(false)  
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
     * "DevCapsName/SubelemName1/SubelemName2/..."
     * If this DevCap is located in DevCapPool and not in a DevCaps - it describes the reusable resource 
     * and DevCap root will have the attribute "Name" = value of DevCaps/@Name 
     * but will have no info about DevCaps/@Context or DevCaps/@LinkUsage 
     *
     * @param bRecurse - if true, returns "DevCapsName/SubelemName1/SubelemName2/..."
     * @return String - NamePath of this DevCap
     * 
     * default: getNamePath(false)  
     */
    public final VString getNamePathVector(boolean bRecurse)
    {
        String result = getName();    
        VString vResult=null;
        
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
                    JDFDeviceCap deviceCap=(JDFDeviceCap) parentNode.getParentNode_KElement();
 
                    for(int i=0;i<v.size();i++)
                    {
                        JDFDevCap dc=(JDFDevCap)v.elementAt(i);
                        VString refs=dc.getDevCapRefs();
                        if(refs.contains(id))
                        {
                            if(vResult==null)
                                vResult=new VString();
                            String dcID=dc.getID();
                            JDFDevCaps dcs=(JDFDevCaps) deviceCap.getChildWithAttribute(ElementName.DEVCAPS,AttributeName.DEVCAPREF,null,dcID,0,true);
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
                    if(vResult==null)
                    {
                        JDFDevCaps dcs=(JDFDevCaps) deviceCap.getChildWithAttribute(ElementName.DEVCAPS,AttributeName.DEVCAPREF,null,id,0,true);                        
                        if(dcs!=null)
                        {
                            vResult=dcs.getNamePathVector();    
                            return vResult;
                        }
  // TODO mixed mode devcaps subelements + devcappool
                    }
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
     * gets String attribute Name inherits from devcap or devcaps if necessary
     * 
     * @return String the value of the attribute
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
     * Moves ChildElementVector of the second element into the first
     * 
     * @param moveToElement - the first element - new parent for the children of the second element
     * @param moveFromElement -  the second element - element whose children will be removed
     */
    private final static void moveChildElementVector_Static(KElement moveToElement, KElement moveFromElement) 
    {
        if (moveToElement != null && moveFromElement != null)
        {
            Vector v = moveFromElement.getChildElementVector(null, null, null, true, 0,false);
            for (int i = 0; i < v.size(); i++) 
            {
                moveToElement.moveElement((KElement)v.elementAt(i), null);
            }
        }
        return;
    }
    
    /**
     * return the vector of all states
     * @param bDirect if false, recurse into child elements, else return only direct child states
     * @param id ID attribute of the requested string
     * @return
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
     * Sets attribute MaxOccurs, 
     * also handles unbounded
     *
     * @param int value: the value to set the attribute to
     */
     public void setMaxOccurs(int value)
     {
         if(value==Integer.MAX_VALUE)
         {
             setAttribute(AttributeName.MAXOCCURS, JDFConstants.UNBOUNDED, null);
         }
         else
         {
             setAttribute(AttributeName.MAXOCCURS, value, null);
         }
     }

     /**
     * Gets integer attribute MaxOccurs
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
      * gets the matching elements in node that match this devcap
      * 
      * @param node the node to search in
      * @return VElement the element vector of matching elements, 
      * null if none were found
      */
     public VElement getMatchingElementsFromParent(KElement parent)
     {
         String nam=getName();
         VElement subElems=parent.getChildElementVector(nam,null,null,true,999999,true);
         return subElems.size()==0 ? null : subElems;
     }
  /**
      * sets the element and attribute defaults
      * @param element
      * @return
      */
    public boolean setDefaultsFromCaps(KElement element)
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
                    success=setDefaultsFromCaps(vR.item(i)) || success;
                }
                return success;
            }
        }
        // default leaf or element case -
        VElement vSubDevCap=getDevCapVector(null,true);
        int i;
        for(i=0;i<vSubDevCap.size();i++)
        {
            JDFDevCap subDevCap=(JDFDevCap) vSubDevCap.elementAt(i);
            final int minOccurs = subDevCap.getMinOccurs();
            VElement subElms=subDevCap.getMatchingElementsFromParent(element);
            if(minOccurs>0)
            {
                int occurs=subElms==null ? 0 : subElms.size();
                if(occurs<minOccurs && subElms==null)
                    subElms=new VElement();
                
                for(int ii=occurs;ii<minOccurs;ii++)
                {
                    
                    KElement newSub=element.appendElement(subDevCap.getName(),subDevCap.getDevNS());
                    subElms.add(newSub);
                    success=true;
                }
            }
            for(int ii=0;ii<subElms.size();ii++)
            {
                success = subDevCap.setDefaultsFromCaps(subElms.item(ii)) || success;
            }            
        }
        // states
        VElement vStates=getStates(true,null);
        for(i=0;i<vStates.size();i++)
        {
            JDFAbstractState state=(JDFAbstractState)vStates.elementAt(i);
            success = state.setDefaultsFromCaps(element) || success;
        }

        return false;
    }   
     
///////////////////////////////////////////////////////     
}
