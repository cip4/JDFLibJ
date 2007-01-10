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
 * Copyright (c) 2001 Heidelberger Druckmaschinen AG, All Rights Reserved.
 * 
 * @author Elena Skobchenko
 *
 * JDFAbstractState.java
 *
 */

package org.cip4.jdflib.resource.devicecapability;

import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Vector;
import java.util.zip.DataFormatException;

import org.apache.commons.lang.enums.ValuedEnum;
import org.apache.xerces.dom.CoreDocumentImpl;
import org.cip4.jdflib.auto.JDFAutoBasicPreflightTest.EnumListType;
import org.cip4.jdflib.auto.JDFAutoDevCap.EnumAvailability;
import org.cip4.jdflib.auto.JDFAutoDevCaps.EnumContext;
import org.cip4.jdflib.core.AtrInfoTable;
import org.cip4.jdflib.core.AttributeInfo;
import org.cip4.jdflib.core.AttributeName;
import org.cip4.jdflib.core.ElemInfoTable;
import org.cip4.jdflib.core.ElementInfo;
import org.cip4.jdflib.core.ElementName;
import org.cip4.jdflib.core.JDFConstants;
import org.cip4.jdflib.core.JDFElement;
import org.cip4.jdflib.core.JDFException;
import org.cip4.jdflib.core.KElement;
import org.cip4.jdflib.core.VString;
import org.cip4.jdflib.datatypes.JDFBaseDataTypes;
import org.cip4.jdflib.datatypes.JDFIntegerRange;
import org.cip4.jdflib.datatypes.JDFNameRangeList;
import org.cip4.jdflib.datatypes.JDFRangeList;
import org.cip4.jdflib.resource.intent.JDFIntentResource;
import org.cip4.jdflib.span.JDFSpanBase;
import org.cip4.jdflib.util.StringUtil;


public abstract class JDFAbstractState extends JDFElement implements JDFBaseDataTypes
{
    private static final long serialVersionUID = 1L;
    
    private static AtrInfoTable[] atrInfoTable = new AtrInfoTable[16];
    static 
    {
        atrInfoTable[0]  = new AtrInfoTable(AttributeName.AVAILABILITY,      0x33333311, AttributeInfo.EnumAttributeType.enumeration, EnumAvailability.getEnum(0),null);
        atrInfoTable[1]  = new AtrInfoTable(AttributeName.ACTIONREFS,        0x33333311, AttributeInfo.EnumAttributeType.IDREFS, null,null);
        atrInfoTable[2]  = new AtrInfoTable(AttributeName.DEPENDENTMACROREF, 0x33333311, AttributeInfo.EnumAttributeType.IDREF, null,null);
        atrInfoTable[3]  = new AtrInfoTable(AttributeName.DEVNS,             0x33333331, AttributeInfo.EnumAttributeType.URI, null, JDFConstants.JDFNAMESPACE);
        atrInfoTable[4]  = new AtrInfoTable(AttributeName.EDITABLE,          0x33333311, AttributeInfo.EnumAttributeType.boolean_, null, JDFConstants.TRUE);
        atrInfoTable[5]  = new AtrInfoTable(AttributeName.HASDEFAULT,        0x33333331, AttributeInfo.EnumAttributeType.boolean_, null, JDFConstants.TRUE);
        atrInfoTable[6]  = new AtrInfoTable(AttributeName.ID,                0x33333311, AttributeInfo.EnumAttributeType.ID, null, null);
        atrInfoTable[7]  = new AtrInfoTable(AttributeName.LISTTYPE,          0x33333311, AttributeInfo.EnumAttributeType.enumeration, EnumListType.getEnum(0), EnumListType.SingleValue.getName());
        atrInfoTable[8]  = new AtrInfoTable(AttributeName.MACROREFS,         0x33333311, AttributeInfo.EnumAttributeType.IDREFS, null, null);
        atrInfoTable[9]  = new AtrInfoTable(AttributeName.MAXOCCURS,         0x33333311, AttributeInfo.EnumAttributeType.unbounded, null, "1");
        atrInfoTable[10] = new AtrInfoTable(AttributeName.MINOCCURS,         0x33333311, AttributeInfo.EnumAttributeType.integer, null, "1");
        atrInfoTable[11] = new AtrInfoTable(AttributeName.MODULEREFS,        0x33333111, AttributeInfo.EnumAttributeType.IDREFS, null, null);
        atrInfoTable[12] = new AtrInfoTable(AttributeName.NAME,              0x33333331, AttributeInfo.EnumAttributeType.NMTOKEN, null, null);
        atrInfoTable[13] = new AtrInfoTable(AttributeName.REQUIRED,          0x33333311, AttributeInfo.EnumAttributeType.boolean_, null, null);
        atrInfoTable[14] = new AtrInfoTable(AttributeName.SPAN,              0x44444431, AttributeInfo.EnumAttributeType.boolean_, null, null);
        atrInfoTable[15] = new AtrInfoTable(AttributeName.USERDISPLAY,       0x33333311, AttributeInfo.EnumAttributeType.enumeration, EnumUserDisplay.getEnum(0),EnumUserDisplay.Display.getName());
    }
    
    protected AttributeInfo getTheAttributeInfo() 
    {
        return super.getTheAttributeInfo().updateReplace(atrInfoTable);
    }
    
    
    private static ElemInfoTable[] elemInfoTable = new ElemInfoTable[1];
    static
    {
        elemInfoTable[0] = new ElemInfoTable(ElementName.LOC, 0x33333311);
    }
    
    protected ElementInfo getTheElementInfo()
    {
        return super.getTheElementInfo().updateReplace(elemInfoTable);
    }
    
    
    /**
     * constructor for JDFAbstractState
     * @param ownerDocument
     * @param qualifiedName
     */
    public JDFAbstractState(CoreDocumentImpl myOwnerDocument, String qualifiedName)
    {
        super(myOwnerDocument, qualifiedName);
    }
    
    /**
     * constructor for JDFAbstractState
     * @param ownerDocument
     * @param namespaceURI
     * @param qualifiedName
     */
    public JDFAbstractState(CoreDocumentImpl myOwnerDocument, String myNamespaceURI, String qualifiedName)
    {
        super(myOwnerDocument, myNamespaceURI, qualifiedName);
    }
    
    /**
     * constructor for JDFAbstractState
     * @param ownerDocument
     * @param namespaceURI
     * @param qualifiedName
     * @param localName
     */
    public JDFAbstractState(CoreDocumentImpl myOwnerDocument, String myNamespaceURI, String qualifiedName,
            String myLocalName)
    {
        super(myOwnerDocument, myNamespaceURI, qualifiedName, myLocalName);
    }
    
    //**************************************** Methods *********************************************
    
    /**
     * toString
     * @return String
     */
    public String toString()
    {
        return "JDFAbstractState[ --> " + super.toString() + " ]";
    }
    
    public static class EnumUserDisplay extends ValuedEnum
    {
        private static final long serialVersionUID = 1L;
        private static int m_startValue = 0;
        
        private EnumUserDisplay(String name)
        {
            super(name, m_startValue++);
        }
        
        public static EnumUserDisplay getEnum(String enumName)
        {
            return (EnumUserDisplay) getEnum(EnumUserDisplay.class, enumName);
        }
        
        public static EnumUserDisplay getEnum(int enumValue)
        {
            return (EnumUserDisplay) getEnum(EnumUserDisplay.class, enumValue);
        }
        
        public static Map getEnumMap()
        {
            return getEnumMap(EnumUserDisplay.class);
        }
        
        public static List getEnumList()
        {
            return getEnumList(EnumUserDisplay.class);
        }
        
        public static Iterator iterator()
        {
            return iterator(EnumUserDisplay.class);
        }
        
        public static final EnumUserDisplay Display = new EnumUserDisplay("Display");
        public static final EnumUserDisplay Hide = new EnumUserDisplay("Hide");
        public static final EnumUserDisplay Dependent = new EnumUserDisplay("Dependent");
        
    }
    
    
    /**
     * Tests, if the defined value matches Allowed test lists or Present test lists,
     * specified for this State
     *
     * @param String value - value to test
     * @param EnumFitsValue valuelist - Switches between Allowed test lists and Present test lists.
     * Has two values: Allowed and Present.
     * @return boolean - true, if the value matches test lists or if Allowed testlists are not specified
     */
    public abstract boolean fitsValue(String value, EnumFitsValue testlists);
    
    /**
     * Gets the NamePath of this State in form 
     * "DevCapsName[Context=aaa, LinkUsage=ccc]/DevCapName1/DevCapName2../@StateName"
     * 
     * @param boolean onlyNames - if true returns "DevCapsName/SubelemName1/SubelemName2/../@StateName". 
     * default getNamePath(false)
     * 
     * @return String - NamePath of this State
     */
    public final String getNamePath()
    {
        JDFDevCap devCap = (JDFDevCap) getParentNode();
        String namePath = devCap.getNamePath(true);
        JDFDevCaps dcs=getParentDevCaps();
        if(dcs!=null)
        {
            EnumContext context=dcs.getContext();
            if(EnumContext.Link.equals(context))
                namePath+="Link";
        }
        if (getListType().equals(EnumListType.Span))
            return (namePath + "/" + getName()); // Span is an element
        return (namePath + "/@" + getName());
    }
    
    /**
     * Gets the NamePath of this State in form 
     * "DevCapsName[Context=aaa, LinkUsage=ccc]/DevCapName1/DevCapName2../@StateName"
     * 
     * @param boolean onlyNames - if true returns "DevCapsName/SubelemName1/SubelemName2/../@StateName". 
     * default getNamePath(false)
     * 
     * @return String - NamePath of this State
     */
    public final VString getNamePathVector(boolean bRecurse)
    {
        JDFDevCap devCap = (JDFDevCap) getParentNode();
        VString vNamePath = devCap.getNamePathVector(bRecurse);
        JDFDevCaps dcs=getParentDevCaps();
        if(dcs!=null)
        {
            EnumContext context=dcs.getContext();
            if(EnumContext.Link.equals(context))
            {
                StringUtil.concatStrings(vNamePath,"Link");
            }
        }
        if (getListType().equals(EnumListType.Span))
        {
            StringUtil.concatStrings(vNamePath, "/" + getName()); // Span is an element
        }
        else
        {
            StringUtil.concatStrings(vNamePath, "/@" + getName());
        }
        return vNamePath;
    }
    
    
    /**
     * get the ancestor devCaps, null if this resides in a devcappool
     * @return
     */
    public JDFDevCaps getParentDevCaps(){
        return (JDFDevCaps) getDeepParent(ElementName.DEVCAPS,0);
    }
    
    /* ******************************************************
     // Attribute Getter / Setter
      **************************************************************** */
    /**
     * Sets attribute Availability
     *
     * @param EnumAvailability value: the value to set the attribute to
     */
    public void setAvailability(JDFDevCaps.EnumAvailability value)
    {
        setAttribute(AttributeName.AVAILABILITY, value.getName(), null);
    }
    
    /**
     * Gets typesafe enumerated attribute Availability
     *
     * @return EnumAvailability: the enumeration value of the attribute
     */
    public JDFDevCap.EnumAvailability getAvailability()
    {
        JDFDevCap.EnumAvailability avail = JDFDevCap.EnumAvailability.getEnum(getAttribute(
                AttributeName.AVAILABILITY, null, null));
        
        if (avail.getValue() == JDFDevCaps.EnumAvailability.getEnum(0).getValue())
        {
            JDFDevCap par = (JDFDevCap) getParentNode();
            avail = par.getAvailability();
        }
        return avail;
    }
    
    /**
     * Sets attribute DevNS
     *
     * @param String value: the value to set the attribute to
     */
    public void setDevNS(String value)
    {
        setAttribute(AttributeName.DEVNS, value);
    }
    
    /**
     * Gets URI attribute DevNS
     *
     * @return String: the attribute value
     */
    public String getDevNS()
    {
        return getAttribute(AttributeName.DEVNS, JDFConstants.EMPTYSTRING, "http://www.cip4.org/JDFSchema_1_1");
    }
    
    /**
     * Sets attribute HasDefault, default=true
     *
     * @param boolean value: the value to set the attribute to
     */
    public void setHasDefault(boolean value)
    {
        setAttribute(AttributeName.HASDEFAULT, value, null);
    }
    
    /**
     * Gets boolean attribute HasDefault
     *
     * @return bool: the attribute value
     */
    public boolean getHasDefault()
    {
        return getBoolAttribute(AttributeName.HASDEFAULT, null, true);
    }
    
    /**
     * Sets attribute MaxOccurs, 
     *
     * @param int value: the value to set the attribute to
     */
    public void setMaxOccurs(int value)
    {
        setAttribute(AttributeName.MAXOCCURS, value, null);
    }

    /**
     * Gets integer attribute MaxOccurs
     *
     * @return int: the attribute value 
     */
    public int getMaxOccurs()
    {
        final String s = getAttribute(AttributeName.MAXOCCURS, null, null);
        if(JDFConstants.UNBOUNDED.equals(s)) // legacy support
            return Integer.MAX_VALUE;
        return StringUtil.parseInt(s,1);
    }
    
    /**
     * Sets attribute MinOccurs, default=1
     *
     * @param int value: the value to set the attribute to
     */
    public void setMinOccurs(int value)
    {
        setAttribute(AttributeName.MINOCCURS, value, null);
    }
    
    /**
     * Get integer attribute MinOccurs
     *
     * @return bool: the attribute value 
     */
    public int getMinOccurs()
    {
        return getIntAttribute(AttributeName.MINOCCURS, JDFConstants.EMPTYSTRING, 1);
    }
    
    /**
     * Sets String attribute Name
     * Since name is independent of the data type of the State element, the setter is defined here
     *
     * @param String value: the value to set the attribute to
     */
    public void setName(String value)
    {
        setAttribute(AttributeName.NAME, value);
    }
    
    /**
     * Gets String attribute Name
     * Since name is independent of the data type of the State element,the getter is defined here
     *
     * @return bool: the attribute value
     */
    public String getName()
    {
        return getAttribute(AttributeName.NAME, null, JDFConstants.EMPTYSTRING);
    }
    
    /**
     * Sets attribute Required
     *
     * @param boolean value: the value to set the attribute to
     */
    public void setRequired(boolean value)
    {
        setAttribute(AttributeName.REQUIRED, value, null);
    }
    
    /**
     * Gets boolean attribute Required
     *
     * @return bool: the attribute value 
     */
    public boolean getRequired()
    {
        return getBoolAttribute(AttributeName.REQUIRED, JDFConstants.EMPTYSTRING, false);
    }
    
    /**
     * Sets attribute ListType, default=SingleValue
     *
     * @param EnumListType value: the value to set the attribute to
     */
    public void setListType(EnumListType value)
    {
        setAttribute(AttributeName.LISTTYPE, value.getName(), null);
    }
    
    /**
     * Gets typesafe enumerated attribute ListType
     *
     * @return EnumListType: the enumeration value of the attribute
     */
    public EnumListType getListType()
    {
        return EnumListType.getEnum(getAttribute(AttributeName.LISTTYPE, null, EnumListType.SingleValue.getName()));
    }
    
    /**
     * Sets attribute ActionRefs
     *
     * @param VString value: vector of ActionRefs
     */
    public void setActionRefs(VString value)
    {
        StringBuffer strActionRefs = new StringBuffer(100);
        for (int i = 0; i < value.size(); i++)
        {
            strActionRefs.append(value.elementAt(i));
        }
        setAttribute(AttributeName.ACTIONREFS, strActionRefs.toString());
    }
    
    /**
     * Gets NMTOKENS attribute ActionRefs
     *
     * @return vString: the attribute value
     */
    public VString getActionRefs()
    {
        String strActionRefs = getAttribute(AttributeName.ACTIONREFS, null,
                JDFConstants.EMPTYSTRING);
        Vector v = StringUtil.tokenize(strActionRefs, JDFConstants.COMMA, false);
        return new VString(v);
    }
    
    /**
     * Sets attribute Editable
     *
     * @param boolean value: the value to set the attribute to
     */
    public void setEditable(boolean value)
    {
        setAttribute(AttributeName.EDITABLE, value, null);
    }
    
    /**
     * Gets boolean attribute Editable
     *
     * @return bool: the attribute value 
     */
    public boolean getEditable()
    {
        return getBoolAttribute(AttributeName.EDITABLE, JDFConstants.EMPTYSTRING, true);
    }
    
    /**
     * Sets attribute MacroRefs
     *
     * @param VString value: vector of MacroRefs
     */
    public void setMacroRefs(VString value)
    {
        StringBuffer strMacroRefs = new StringBuffer(100);
        for (int i = 0; i < value.size(); i++)
        {
            strMacroRefs.append(value.elementAt(i));
        }
        setAttribute(AttributeName.MACROREFS, strMacroRefs.toString());
    }
    
    /**
     * Get NMTOKENS attribute MacroRefs
     *
     * @return vString: the attribute value
     */
    public VString getMacroRefs()
    {
        String strMacroRef = getAttribute(AttributeName.MACROREFS, null, JDFConstants.EMPTYSTRING);
        Vector v = StringUtil.tokenize(strMacroRef, JDFConstants.COMMA, false);
        return new VString(v);
    }
    
    /**
     * Sets attribute DependentMacroRef
     *
     * @param String& value: vector of DependentMacroRef
     */
    public void setDependentMacroRef(String value)
    {
        setAttribute(AttributeName.DEPENDENTMACROREF, value);
    }
    
    /**
     * Get string attribute DependentMacroRef
     *
     * @return String: the attribute value
     */
    public String getDependentMacroRef()
    {
        return getAttribute(AttributeName.DEPENDENTMACROREF, null, JDFConstants.EMPTYSTRING);
    }
    
    /**
     * Sets attribute UserDisplay, default=Display
     *
     * @param EnumUserDisplay value: the value to set the attribute to
     */
    public void setUserDisplay(EnumUserDisplay value)
    {
        setAttribute(AttributeName.USERDISPLAY, value.getName(),null);
    }
    
    /**
     * Gets typesafe enumerated attribute UserDisplay
     *
     * @return EnumUserDisplay: the enumeration value of the attribute
     */
    public EnumUserDisplay getUserDisplay()
    {
        return EnumUserDisplay.getEnum(getAttribute(AttributeName.USERDISPLAY, null, EnumUserDisplay.Display.getName()));
    }
    
    /* ******************************************************
     // Element Getter / Setter
      **************************************************************** */
    //@{
    /**
     * Gets the iSkip-th element Loc. If doesn't exist, creates it
     *
     * @param int iSkip: number of elements to skip
     * @return JDFLoc: the matching element
     * @default getCreateLoc(0);
     */
    public JDFLoc getCreateLoc(int iSkip)
    {
        return (JDFLoc) getCreateElement(ElementName.LOC, JDFConstants.EMPTYSTRING, iSkip);
    }
    
    /**
     * Gets the iSkip-th element Loc
     *
     * @param int iSkip: number of elements to skip
     * @return JDFLoc: the matching element
     * @default getLoc(0)
     */
    public JDFLoc getLoc(int iSkip)
    {
        JDFLoc e = (JDFLoc) getElement(ElementName.LOC, JDFConstants.EMPTYSTRING, iSkip);
        return e;
    }
    
    /**
     * Appends element Loc to the end of 'this'
     *
     * @return JDFLoc: newly created Loc element
     */
    public JDFLoc appendLoc()
    {
        return (JDFLoc) appendElement(ElementName.LOC, null);
    }
    
    public void setID(String sid)
    {
        setAttribute(AttributeName.ID,sid,null);        
    }
    
    
    protected void setAllowedLength(JDFIntegerRange value)
    {
        setAttribute(AttributeName.ALLOWEDLENGTH, value.toString());
    }
    
    
    protected JDFIntegerRange getAllowedLength()
    {
        try
        {
            final String len = getAttribute(AttributeName.ALLOWEDLENGTH,null,null);
            if(len==null)
                return null;
            JDFIntegerRange ir = new JDFIntegerRange(len);
            return ir;
        }
        catch(DataFormatException dfe)
        {
            throw new JDFException("JDFAbstractState.getAllowedLength: Attribute ALLOWEDLENGTH is not capable to create JDFIntegerRange");
        }
        
    }
    
    
    protected void setPresentLength(JDFIntegerRange value)
    {
        setAttribute(AttributeName.PRESENTLENGTH, value.toString());
    }
    
    
    protected JDFIntegerRange getPresentLength()
    {
        if (hasAttribute(AttributeName.PRESENTLENGTH))
        {
            try
            {
                JDFIntegerRange ir = new JDFIntegerRange(getAttribute(AttributeName.PRESENTLENGTH));
                return ir;
            }
            catch(DataFormatException dfe)
            {
                throw new JDFException("JDFAbstractState.getPresentLength: Attribute PRESENTLENGTH is not capable to create JDFIntegerRange");
            }
        }
        return getAllowedLength();
    }
    
    /**
     * Mother of all version fixing routines
     *
     * uses heuristics to modify this element and its children to be compatible with a given version
     * in general, it will be able to move from low to high versions but potentially fail 
     * when attempting to move from higher to lower versions
     *
     * @param version: version that the resulting element should correspond to
     * @return true if successful
     */
    public boolean fixVersion(EnumVersion version){
        if(JDFConstants.UNBOUNDED.equals(getAttribute(AttributeName.MAXOCCURS,null,null)))
            setAttribute(AttributeName.MAXOCCURS, JDFConstants.POSINF);
        return super.fixVersion(version);
    }

    /**
     * fitsLength - tests, if the defined string 'str' matches 
     * AllowedLength or the PresentLength, specified for this State
     *
     * @param String str - string to test
     * @param EnumFitsValue length - Switches between AllowedLength and PresentLength.
     * @return boolean - true, if 'str' matches Length or if AllowedLength is not specified
     */
    protected final boolean fitsLength(String str, EnumFitsValue length)
    {        
        
        JDFIntegerRange lengthlist;
        if (length.equals(EnumFitsValue.Allowed))
        {
            lengthlist = getAllowedLength();
        } 
        else 
        {
            lengthlist = getPresentLength();
        }
        
        if(lengthlist!=null){
            int len=str.length();
            return lengthlist.inRange(len);
        }
        return true;
    }
    
    
    /**
     * gets the matching Attribute value String or AbstractSpan object from the parent, 
     * depending on the type of the state
     * 
     * @param element the parent in which to search
     * @return Object either a String or AbstractSpan
     */
    public Object getMatchingObjectInNode(KElement element)
    {
        String nam = getName();
        if(getListType().equals(EnumListType.Span))
        {            
            return element.getElement(nam,getDevNS(),0);            
        }
        
        return element.getAttribute(nam,getDevNS(),null);
    }
    
    
    /**
     * set the default values specified in this in element
     * @param element the element to set the defaults on
     * @return true if successful
     */
    public boolean setDefaultsFromCaps(KElement element)
    {
        if(!getHasDefault())
            return false;
        String def=getAttribute(AttributeName.DEFAULTVALUE,null,null);
        if(def==null)
            return false;
        Object theValue=getMatchingObjectInNode(element);
        if(theValue!= null)
            return false;
        
        final String nam = getName();
        if(getListType().equals(EnumListType.Span))
        {            
            JDFIntentResource ir=(JDFIntentResource)element;
            JDFSpanBase span=ir.appendSpan(nam,null);
            span.setAttribute(AttributeName.PREFERRED,def);
        }
        else // some attribute...
        {
            element.setAttribute(nam,def,null);
        } 
        return true;
    }
    
    
    /**
     * fitsListType - tests, if the defined 'value' matches value of ListType attribute,
     * specified for this State
     *
     * @param String value - value to test
     * 
     * @return boolean - true, if 'value' matches specified ListType
     */
    protected final boolean fitsListType(String value)
    {
        EnumListType listType=getListType();
        
        JDFRangeList rangelist; // lists of strings are most generic
        try 
        {
            rangelist = new JDFNameRangeList(value);                       
        }
        catch (DataFormatException e)
        {
            return false;
        }
        catch (JDFException e)
        {
            return false;
        }
        
        if (listType==null || listType.equals(EnumListType.SingleValue)) 
        {// default ListType = SingleValue
            return value.indexOf(" ")==-1;
        }    
        else if (listType.equals(EnumListType.RangeList) ||
                listType.equals(EnumListType.Span))
        {
            return true;
        }
        else if ( listType.equals(EnumListType.List) ||
                listType.equals(EnumListType.CompleteList)  || // complete or not - tested in fitsValueList
                listType.equals(EnumListType.CompleteOrderedList) || // tested in fitsValueList
                listType.equals(EnumListType.ContainedList))  // tested in fitsValueList
        {
            return rangelist.isList(); 
        }
        else if (listType.equals(EnumListType.OrderedList))
        {
            return (rangelist.isList()&&rangelist.isOrdered());
        }
        else if (listType.equals(EnumListType.UniqueList))
        {
            return (rangelist.isList()&&rangelist.isUnique());
        }
        else if (listType.equals(EnumListType.UniqueOrderedList))
        {
            return (rangelist.isList()&&rangelist.isUniqueOrdered());
        }
        else if (listType.equals(EnumListType.OrderedRangeList))
        {
            return rangelist.isOrdered();
        }
        else if (listType.equals(EnumListType.UniqueRangeList))
        {
            return rangelist.isUnique();
        }
        else if (listType.equals(EnumListType.UniqueOrderedRangeList))
        {
            return (rangelist.isUniqueOrdered());
        }
        else
        {
            throw new JDFException ("JDFDateTimeState.fitsListType illegal ListType attribute"); 
        }
    }


    /**
    * Gets the j-th element Loc of the i-th element Value
    *
    * @param int iSkip: number of Value elements to skip 
    * ( iSkip=0 - first Value element)
    * @param int jSkip: number of Loc subelements of i-th Value element to skip,
    * ( jSkip=0 - first Loc element)
    * @return JDFLoc: the matching Loc element
    */
    public JDFLoc getValueLocLoc(int iSkip, int jSkip)
    {
        JDFValueLoc val = getValueLoc(iSkip);
        if(val==null)
            return null;
        return val.getLoc(jSkip);
    }

    /**
     * Appends element Loc to the end of the i-th subelement Value
     *
     * @param int iSkip: number of Value elements to skip 
     * ( iSkip=0 - first Value element)
     * @return JDFLoc: newly created Loc element
     */
     final public JDFValueLoc getValueLoc(int iSkip)
     {
         return (JDFValueLoc) getElement(ElementName.VALUELOC,null,iSkip); 
     }
    /**
     * Appends element Loc to the end of the i-th subelement Value
     *
     * @param int iSkip: number of Value elements to skip 
     * ( iSkip=0 - first Value element)
     * @return JDFLoc: newly created Loc element
     */
     public JDFLoc appendValueLocLoc(int iSkip)
     {
         JDFValueLoc val =  getValueLoc(iSkip);
         if(val==null)
             return null;
         return val.appendLoc();
     }
     /**
      * Appends element ValueLoc 
      *
      * @param int iSkip: number of Value elements to skip 
        * @return JDFLoc: newly created Loc element
      */
      final public JDFValueLoc appendValueLoc()
      {
          return (JDFValueLoc) appendElement(ElementName.VALUELOC,null);
      }
    
    /////////////////////////////////////////////
}