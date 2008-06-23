/*--------------------------------------------------------------------------------------------------
 * The CIP4 Software License, Version 1.0
 *
 *
 * Copyright (c) 2001-2008 The International Cooperation for the Integration of
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
 */
/**
 *
 * Copyright (c) 2001-2005 Heidelberger Druckmaschinen AG, All Rights Reserved.
 *
 * KElement.java
 * KElement wraps DOM_Element objects
 */
package org.cip4.jdflib.core;

import java.io.IOException;
import java.io.StringWriter;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.Vector;

import org.apache.commons.lang.enums.ValuedEnum;
import org.apache.xerces.dom.AttrNSImpl;
import org.apache.xerces.dom.CoreDocumentImpl;
import org.apache.xerces.dom.ElementNSImpl;
import org.apache.xml.serialize.OutputFormat;
import org.apache.xml.serialize.XMLSerializer;
import org.cip4.jdflib.core.AttributeInfo.EnumAttributeType;
import org.cip4.jdflib.core.JDFElement.EnumVersion;
import org.cip4.jdflib.datatypes.JDFAttributeMap;
import org.cip4.jdflib.pool.JDFResourcePool;
import org.cip4.jdflib.resource.JDFResource;
import org.cip4.jdflib.util.StringUtil;
import org.w3c.dom.Attr;
import org.w3c.dom.CDATASection;
import org.w3c.dom.Comment;
import org.w3c.dom.DOMException;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.EntityReference;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.w3c.dom.Text;


/**
 *
 * KElement wraps Element and contains some generic utilities.<br>
 * Every access to a Element should be wrapped using KElement.<br>
 * KElement is completely agnostic to JDF.<br>
 * Typically elements in non-JDF namespaces will be KElements.<br>
 * <br>
 * note that it is discouraged to mix direct calls to Dom Element and KElement routines
 * a future version will most likely contain a private  ElementNSImpl rather than inherit from it.
 * The current extension solution is a work around around a xerces bug
 *
 * @author CIP4
 * @see JDFElement for the first element class that is aware of JDF
 */
public class KElement extends ElementNSImpl
{
    private static final long serialVersionUID = 1L;
    private static AtrInfoTable[] atrInfoTable = new AtrInfoTable[1];

    static
    {
        atrInfoTable[0] = new AtrInfoTable(JDFConstants.XMLNS,   0x33333333, AttributeInfo.EnumAttributeType.URI, null,null);
    }

    protected AttributeInfo getTheAttributeInfo()
    {
        AttributeInfo ai = new AttributeInfo(atrInfoTable);
        ai.setVersion(EnumVersion.getEnum(this.getInheritedAttribute(AttributeName.VERSION,null,null)));
        return ai;
    }

    /**
     * returns the data type of a given attribute
     * @param attributeName the localname of the attribute to check
     * @return the data type of attributeName
     */
    public EnumAttributeType getAtrType(String attributeName)
    {
        AttributeInfo ai=getTheAttributeInfo();
        return ai.getAttributeType(attributeName);
    }

    /**
     * get the first JDF version where attribute name or element name is valid
     * @param eaName attribute name
     * @param bElement true - get ElementInfo, false - get AttributeInfo
     * @return JDF version, Version_1_0 if no Info is found
     */
    public EnumVersion getFirstVersion(String eaName, boolean bElement)
    {
        EnumVersion v=null;
        if(bElement)
        {
            ElementInfo ei=getTheElementInfo();
            v= ei.getFirstVersion(eaName);
        }
        else
        {
            AttributeInfo ai=getTheAttributeInfo();
            v= ai.getFirstVersion(eaName);
        }
        if(v==null) {
            v=EnumVersion.Version_1_0;
        }
        return v;
    }

    /**
     * get the last JDF version where attribute name or element name is valid
     * @param eaName attribute name
     * @param bElement true - get ElementInfo, false - get AttributeInfo
     * @return JDF version, Version_1_0 if no Info is found
     */
    public EnumVersion getLastVersion(String eaName, boolean bElement)
    {
        EnumVersion v=null;
        if(bElement)
        {
            ElementInfo ei=getTheElementInfo();
            v= ei.getLastVersion(eaName);
        }
        else
        {
            AttributeInfo ai=getTheAttributeInfo();
            v= ai.getLastVersion(eaName);
        }
        return v;
    }

    protected ElementInfo getTheElementInfo()
    {
        ElementInfo ei= new ElementInfo(null, null);
        ei.setVersion(EnumVersion.getEnum(this.getInheritedAttribute(AttributeName.VERSION,null,null)));
        return ei;
    }

    /**
     * Constructor for KElement
     * @param myOwnerDocument     the owner document of the new element
     * @param qualifiedName     the qualified name of the element
     */
    public KElement(CoreDocumentImpl myOwnerDocument, String qualifiedName)
    {
        super(myOwnerDocument, qualifiedName);
    }

    /**
     * Constructor for KElement
     * @param myOwnerDocument     the owner document of the new element
     * @param myNamespaceURI      the namespace of the new element
     * @param qualifiedName     the qualified name of the element
     */
    public KElement(CoreDocumentImpl myOwnerDocument, String myNamespaceURI, String qualifiedName)
    {
        super(myOwnerDocument, myNamespaceURI, qualifiedName);
    }

    /**
     * Constructor for KElement
     *
     * @param myOwnerDocument     the owner document of the new element
     * @param myNamespaceURI      the namespace of the new element
     * @param qualifiedName     the qualified name of the element
     * @param myLocalName         the localname of the element
     */
    public KElement(CoreDocumentImpl myOwnerDocument, String myNamespaceURI, String qualifiedName, String myLocalName)
    {
        super(myOwnerDocument, myNamespaceURI, qualifiedName, myLocalName);
    }

    /** Encoding. */
    private static final String sm_strENCODING = "UTF-8"; // faster: "US-ASCII"

    //************************** start of directly dependend methods ***********

    /**
     * Get the dirty status of this element
     * @return boolean true if dirty
     */
    public boolean isDirty()
    {
        final XMLDocUserData usrDat = getXMLDocUserData();
        if (usrDat != null )
        {
            return usrDat.isDirty(this);
        }
        return false;
    }

    /**
     * Set this element as dirty
     * @deprecated use setDirty (bAttribute)
     */
    @Deprecated
    public void setDirty()
    {
        setDirty(false);
    }
    /**
     * Set this element as dirty
     */
    public void setDirty(boolean bAttribute)
    {
        final XMLDocUserData usrDat = getXMLDocUserData();
        if (usrDat != null )
        {
            usrDat.setDirty(this,bAttribute);
        }
    }

    /**
     * Get the document object that owns this
     *
     * @return XMLDoc the owner document of this
     */
    public XMLDoc getOwnerDocument_KElement()
    {
        final Document doc = getOwnerDocument();
        return new XMLDoc(doc);
    }

    /**
     * searches for the first attribute occurence
     * in this element or any ancestors
     *
     * @param attrib        the attribute name
     * @param nameSpaceURI  the XML-namespace
     * @param def           the default if it does not exist
     *
     * @return String       value of attribute found,
     *                      value of def if not available
     *
     * @default getInheritedAttribute(attrib,
     *                                null,
     *                                JDFConstants.EMPTYSTRING)
     */
    public String getInheritedAttribute(String attrib, String nameSpaceURI, String def)
    {
        return getInheritedAttribute_KElement(attrib, nameSpaceURI, def);
    }

    /**
     * searches for the first attribute occurence
     * in this element or any ancestors
     *
     * @param attrib        the attribute name
     * @param nameSpaceURI  the XML-namespace
     * @param def           the default if it does not exist
     *
     * @return String       value of attribute found,
     *                      value of def if not available
     *
     * @default getInheritedAttribute_KElement(attrib,
     *                                         null,
     *                                         JDFConstants.EMPTYSTRING)
     */
    private String getInheritedAttribute_KElement(String attrib, String nameSpaceURI, String def)
    {
        String strRet = getAttribute_KElement(attrib, nameSpaceURI, null);

        if (strRet==null)
        {
            final KElement parentNode = getParentNode_KElement();
            if (parentNode != null)
            {
                strRet = parentNode.getInheritedAttribute(attrib, nameSpaceURI, def);
            }
        }
        return strRet==null ? def : strRet;
    }

    /**
     * mother of all attribute getters.
     * Get a attribute out of this element
     *
     * @param attrib        the name of the attribute you want to have
     * @param nameSpaceURI  namespace of key
     * @param def           the value that is returned if attrib does not exist
     *                      in this or this is null
     *
     * @return String       the attribute value as a string, or def if that
     *                      attribute does not have a specified or default value
     *
     * @default GetAttribute(attrib,
     *                       null,
     *                       JDFConstants.EMPTYSTRING)
     */
    public String getAttribute(String attrib, String nameSpaceURI, String def)
    {
        return getAttribute_KElement(attrib, nameSpaceURI, def);
    }

    /**
     * Because getAttribute is overwritten in various classes
     * this method can be called directly to get only KElement Attribute.
     *
     * @param attrib         the name of the attribute you want to have
     * @param nameSpaceURI   namespace of key
     * @param def            the value that is returned if attrib does not
     *                       exist in this  - may be null
     *
     * @return String        the attribute value as a string,
     *                       or def if attribute was not found<br><br>
     *
     * @default getAttribute_KElement(attrib, null,null)
     */
    public String getAttribute_KElement(String attrib, String nameSpaceURI, String def)
    {
        final Attr attribute=getDOMAttr(attrib,nameSpaceURI,false);
        return (attribute == null) ? def : attribute.getValue();
        //      switch for null defaults        return JDFConstants.EMPTYSTRING.equals(def) ? null : def;
    }

    /**
     * Mother of all attribute getters <br>
     * Gets an attribute value out of an element
     *
     * @param strLocalName   the name of the attribute you want to have
     *
     * @return String        the value of the Attribute or emptystring
     */
    @Override
    public String getAttribute(String strLocalName)
    {
        return getAttribute(strLocalName, null, JDFConstants.EMPTYSTRING);
    }

    /**
     * Mother of all attribute getters <br>
     * Gets an attribute value out of an element
     *
     * @param strLocalName   the name of the attribute you want to have
     *
     * @return String        the value of the Attribute or emptystring
     */
    public String getAttribute_KElement(String strLocalName)
    {
        return getAttribute_KElement(strLocalName, null, JDFConstants.EMPTYSTRING);
    }

    /**
     * get the parent node of this
     *
     * @return KElement the parent node of the actual element
     */
    public KElement getParentNode_KElement()
    {
        final Node parentNode = getParentNode();
        if (parentNode instanceof KElement)
        {
            return (KElement) parentNode;
        }
        return null;
    }

    //************************** end of directly dependend methods *************
    ////////////////////////////////////////////////////////////////////////////
    ////////////////////////////////////////////////////////////////////////////
    //************************** start of methods needed in core ***************
    //************************** methods needed in JDFElement, JDFNodeInfo, XMLD

    /**
     * Enumeration for validation level <br>
     * <blockquote><ul>
     * <li>level ValidationLevel_NoWarnIncomplete:
     *     Ignore warnings and don't require all required parameters <br>
     * <li>level ValidationLevel_NoWarnComplete:
     *     Ignore warnings and require all required parameters <br>
     * <li>level ValidationLevel_Incomplete:
     *     incomplete elements are valid <br>
     * <li>level ValidationLevel_Complete:
     *     full validation of an individual resource <br>
     * <li>level ValidationLevel_RecursiveIncomplete:
     *     incomplete validation of an individual resource and all of its
     *     child elements - e.g. for pools <br>
     * <li>level ValidationLevel_RecursiveComplete:
     *     full validation of an individual resource and all of its child
     *     elements - e.g. for pools <br>
     * </ul></blockquote>
     */
    public static final class EnumValidationLevel extends ValuedEnum
    {
        private static final long serialVersionUID = 1L;
        private static int m_startValue = 0;

        protected EnumValidationLevel(String name)
        {
            super(name, m_startValue++);
        }

        public static EnumValidationLevel getEnum(String enumName)
        {
            return (EnumValidationLevel) getEnum(EnumValidationLevel.class, enumName);
        }

        public static EnumValidationLevel getEnum(int enumValue)
        {
            return (EnumValidationLevel) getEnum(EnumValidationLevel.class, enumValue);
        }

        public static Map getEnumMap()
        {
            return getEnumMap(EnumValidationLevel.class);
        }

        public static List getEnumList()
        {
            return getEnumList(EnumValidationLevel.class);
        }

        public static Iterator iterator()
        {
            return iterator(EnumValidationLevel.class);
        }

        /**
         * return true if vl is a recursvive EnumValidationLevel
         * @param vl the EnumValidationLevel to check
         * @return true if vl is recursive
         */
        public static boolean isRecursive(EnumValidationLevel vl)
        {
            return EnumValidationLevel.RecursiveIncomplete.equals(vl)
            || EnumValidationLevel.RecursiveComplete.equals(vl);
        }

        /**
         * return true if vl is a recursvive EnumValidationLevel
         * @param vl the EnumValidationLevel to check
         * @return true if vl is recursive
         */
        public static boolean isNoWarn(EnumValidationLevel vl)
        {
            return EnumValidationLevel.NoWarnComplete.equals(vl)
            || EnumValidationLevel.NoWarnIncomplete.equals(vl);
        }

        /**
         * returns true if the enumeration level is either Complete, NoWarnComplete
         * or RecursiveComplete, i.e. if the parameter is required
         * @param level the level to check
         * @return true if required
         */
        public static boolean isRequired(EnumValidationLevel vl)
        {
            return EnumValidationLevel.Complete.equals(vl)
            || EnumValidationLevel.RecursiveComplete.equals(vl)
            || EnumValidationLevel.NoWarnComplete.equals(vl);
        }

        public static final EnumValidationLevel NoWarnIncomplete = new EnumValidationLevel("NoWarnIncomplete");

        public static final EnumValidationLevel NoWarnComplete = new EnumValidationLevel("NoWarnComplete");

        public static final EnumValidationLevel Incomplete = new EnumValidationLevel(
                JDFConstants.VALIDATIONLEVEL_INCOMPLETE);

        public static final EnumValidationLevel Complete = new EnumValidationLevel(
                JDFConstants.VALIDATIONLEVEL_COMPLETE);

        public static final EnumValidationLevel RecursiveIncomplete = new EnumValidationLevel(
                JDFConstants.VALIDATIONLEVEL_RECURSIVEINCOMPLETE);

        public static final EnumValidationLevel RecursiveComplete = new EnumValidationLevel(
                JDFConstants.VALIDATIONLEVEL_RECURSIVECOMPLETE);

        /**
         * 
         * calculate the corresponding nowarn level based on level
         * @param noWarning if true, set to nowarne, else set to standard
         * @return
         */
        public static EnumValidationLevel setNoWarning(EnumValidationLevel level, boolean noWarning)
        {
            if(noWarning &&!isNoWarn(level))
                level=isRequired(level) ? EnumValidationLevel.NoWarnComplete : EnumValidationLevel.NoWarnIncomplete;
            if(!noWarning &&isNoWarn(level))
                level=isRequired(level) ? EnumValidationLevel.Complete : EnumValidationLevel.Incomplete;
            return level;
        }

        /**
         * calculate the corresponding incomplete level based on level
         *
         * @param level the level to test
         * @return EnumValidationLevel - the modified level
         */
        public static EnumValidationLevel incompleteLevel(EnumValidationLevel level)
        {
            if(EnumValidationLevel.Complete.equals(level))
            {
                level = EnumValidationLevel.Incomplete;
            }
            else if(EnumValidationLevel.RecursiveComplete.equals(level))
            {
                level = EnumValidationLevel.RecursiveIncomplete;
            }
            else if(EnumValidationLevel.NoWarnComplete.equals(level))
            {
                level = EnumValidationLevel.NoWarnIncomplete;
            }
            return level;
        }
    }


    /**
     * @deprecated use local EnumValidationLevel enums
     */
    @Deprecated
    public static final EnumValidationLevel ValidationLevel_Incomplete = EnumValidationLevel.Incomplete;

    /**
     * @deprecated use local EnumValidationLevel enums
     */
    @Deprecated
    public static final EnumValidationLevel ValidationLevel_Complete =  EnumValidationLevel.Complete;

    /**
     * @deprecated use local EnumValidationLevel enums
     */
    @Deprecated
    public static final EnumValidationLevel ValidationLevel_RecursiveIncomplete =  EnumValidationLevel.RecursiveIncomplete;

    /**
     * @deprecated use local EnumValidationLevel enums
     */
    @Deprecated
    public static final EnumValidationLevel ValidationLevel_RecursiveComplete = EnumValidationLevel.RecursiveComplete;

    /**
     * Sets an NMTOKENS attribute to all elements from parameter value will
     * be concatenate with blanks to the resulting NMTOKEN
     *
     * @param key    the name of the attribute to set
     * @param value  the values for the attribute key
     * @param nameSpaceURI the namespace of the key
     * @deprecated use setAttribute instead
     *
     * @default setvStringAttribute(key, vStr, null)
     */
    @Deprecated
    public void setvStringAttribute(String key, VString value, String nameSpaceURI)
    {
        setAttribute(key, value, nameSpaceURI);
    }
    /**
     * Sets an NMTOKENS attribute to all elements from parameter value will
     * be concatenate with blanks to the resulting NMTOKEN
     *
     * @param key    the name of the attribute to set
     * @param value  the values for the attribute key
     * @param nameSpaceURI the namespace of the key
     *
     * @default setvStringAttribute(key, vStr, null)
     */
    public void setAttribute(String key, VString value, String nameSpaceURI)
    {
        final String s=StringUtil.setvString(value);
        setAttribute(key, s, nameSpaceURI);
    }

    /**
     * Get The DOM Attribute node of a given attribute
     * if attrib has no namespace prefix and nameSpaceURI is a wildcard
     * the attribute with the element prefix will be returned if no empty attribute exists
     * e.g. getDOMAttr("a") will return the node x:a in <x:e x:a="b"/>
     *
     * @param attrib the attribute Name
     * @param nameSpaceURI then namespaceURI, defaults to the local namespace
     * @param bInherit search in parent elements as well
     * @return Node  the DOMAttr node of the matching attribute
     */
    public Attr getDOMAttr(String attrib, String nameSpaceURI, boolean bInherit)
    {
        Attr a = null;
        if ((nameSpaceURI==null) || nameSpaceURI.equals(JDFConstants.EMPTYSTRING))
        {
            a = getAttributeNode(attrib);
            if (a != null) {
                return a;
            }

            nameSpaceURI=null;

            final String attribPrefix = xmlnsPrefix(attrib);
            final String elementPrefix = getPrefix();
            if (elementPrefix !=null && attribPrefix!=null && attribPrefix.equals(elementPrefix))
            { // has attribute prefix
                a = getAttributeNode(attrib.substring(elementPrefix.length() + 1));
            }
            else if (elementPrefix != null && attribPrefix==null)
            {
                a = getAttributeNode(elementPrefix + JDFConstants.COLON + attrib);
            }

            if((a==null) && !attrib.startsWith(JDFConstants.XMLNS))
            {
                nameSpaceURI=getNamespaceURIFromPrefix(attribPrefix);
            }
        }
        // either we have an explicit ns or we haven't found anything in dom level 1 - assume the namespace of this
        if((a==null) && (nameSpaceURI!=null))
        {
            // the xmlNSLocalName here is just in case - actually you shouldn't be calling both ns and prefix
            a = getAttributeNodeNS(nameSpaceURI, KElement.xmlnsLocalName(attrib));
            if((a==null) && nameSpaceURI.equals(getNamespaceURI())) {
                a = getAttributeNodeNS(null, KElement.xmlnsLocalName(attrib));
            }

        }
        if(a==null && bInherit)
        {
            KElement parent=getParentNode_KElement();
            if(parent!=null) {
                return parent.getDOMAttr(attrib,nameSpaceURI,bInherit);
            }
        }
        return a;
    }

    //////////////////////////////////////////////////////////////////////
    /**
     * Mother of all Attribute setters <br>
     * Sets a new attribute. If an attribute with that name is already present in
     * the element, its value is changed to be that of the value parameter. This
     * value is a simple string; it is not parsed as it is being set. So any
     * markup (such as syntax to be recognized as an entity reference) is treated
     * as literal text, and needs to be appropriately escaped by the
     * implementation when it is written out. In order to assign an attribute
     * value that contains entity references, the user must create an Attr node
     * plus any Text and EntityReference nodes, build the appropriate subtree,
     * and use setAttributeNode to assign it as the value of an attribute.
     * To set an attribute with a qualified name and namespace URI, use the
     * setAttributeNS method.
     *
     * @param key the qualified name of the attribute to create or alter.
     * @param value the value to set in string form. If null, the attribute is removed
     * @param nameSpaceURI the namespace the element is in
     * @throws JDFException if no settings of its attributes are possible
     */
    public void setAttribute(String key, String value, String nameSpaceURI)
    {
        boolean bDirty = false;
        if(value==null)
        {
            removeAttribute(key,nameSpaceURI);
            return;
        }

        if ((nameSpaceURI==null) || (nameSpaceURI.equals(JDFConstants.EMPTYSTRING)))
        { ////////////// DOM Level 1 ///////////////////
            // must explicitely set xmlns as DOM level 2 because the xerces serializer checks for DOM level 2
            // xmlns attributes and avoids duplicate serialization of the attribute and namespace nodes
            if (key.startsWith(JDFConstants.XMLNS) && (key.length() == 5 || key.charAt(5) == ':'))
            {   // set an attribute which is a namespace
                if (value.equals(JDFConstants.EMPTYSTRING))
                {
                    Node a = getAttributeNode(key);
                    // never ever set "xmlns:foo="" !
                    if (a != null)
                    {
                        bDirty = true;
                        removeAttribute(key);
                    }
                }
                else if (!value.equals(getInheritedAttribute(key, null, null)))
                {
                    bDirty = true;
                    super.setAttributeNS(AttributeName.XMLNSURI, key, value);
                    ((DocumentJDFImpl)getOwnerDocument()).setIgnoreNSDefault(false); 
                }
            }
            else
            { // set a normal attribute
                String attributePrefix = xmlnsPrefix(key);
                if (attributePrefix==null)
                { // no attribute prefix, put the attribute in the default namespace
                    bDirty = true;
                    super.setAttributeNS(null, key, value);
                }
                else
                {   // try to find a namespace
                    String namespaceURI2 = getNamespaceURIFromPrefix(attributePrefix);
                    if (namespaceURI2!=null)
                    {
                        // now we have a namespace --> recurse
                        setAttribute(key, value, namespaceURI2);
                    }
                    else
                    {
                        // attribute with prefix, no namespace found
                        Node a = getDOMAttr(key, null,false);
                        if (a == null || !value.equals(a.getNodeValue()))
                        {
                            bDirty = true;
                            if (a != null)
                            {
                                String nodeName = a.getNodeName();

                                // don't search the attribute node if it is already there
                                if (key.equals(nodeName))
                                { // overwrite default namespace with qualified namespace or vice versa
                                    removeAttribute(nodeName);
                                    super.setAttribute(key, value);
                                }
                                else
                                { // same qualified name, simply overwrite the value
                                    a.setNodeValue(value);
                                }
                            }
                            else
                            {
                                String nsURI2 = getNamespaceURIFromPrefix(xmlnsPrefix(key));
                                if((nsURI2!=null)&&!nsURI2.equals(nameSpaceURI))
                                {
                                    throw new JDFException("KElement.setAttribute: inconsistent namespace URI for prefix: " + xmlnsPrefix(key) + "; existing URI: " + nsURI2 + "; attempting to set URI: " + nameSpaceURI);
                                }
                                try{
                                    super.setAttributeNS(nsURI2, key, value);
                                }
                                catch(DOMException de)
                                {
                                    // we punt here because it wil hopefully only be an ordering problem
                                    super.setAttribute(key,value);
                                }
                            }
                        }
                    }
                }
            }
        }
        else
        { ////////////// DOM Level 2 ///////////////////
            if (AttributeName.XMLNSURI.equals(nameSpaceURI))
            {
                // never ever set "xmlns:foo="" !
                if (value.equals(JDFConstants.EMPTYSTRING))
                {
                    bDirty = true;
                    removeAttributeNS(nameSpaceURI, key);
                }
                else if (!value.equals(getInheritedAttribute(xmlnsLocalName(key), nameSpaceURI, null)))
                {
                    bDirty = true;
                    super.setAttributeNS(AttributeName.XMLNSURI, key, value);
                }
            }
            else // standard attribute (not xmlns)
            {
                Node a = getAttributeNodeNS(nameSpaceURI, xmlnsLocalName(key));
                if (a == null || !value.equals(a.getNodeValue()))
                {
                    bDirty = true;
                    if (a != null)
                    { // don't search the attribute node if it is already there
                        String nodeName = a.getNodeName();

                        if (!key.equals(nodeName))
                        { // overwrite default namespace with qualified namespace or vice versa
                            super.setAttributeNS(nameSpaceURI, key, value);
                        }
                        else
                        { // same qualified name, simply overwrite the value
                            a.setNodeValue(value);
                        }
                    }
                    else
                    {
                        String namespaceURI2 = getNamespaceURIFromPrefix(xmlnsPrefix(key));

                        if ( namespaceURI2!=null && !JDFConstants.EMPTYSTRING.equals(namespaceURI2) &&
                                !namespaceURI2.equals(nameSpaceURI))
                        {   // in case multiple namespace uris are defined for the same prefix,
                            // all we can do is to bail out loudly
                            throw new JDFException(
                                    "KElement.setAttribute: inconsistent namespace URI for prefix: "
                                    + xmlnsPrefix(key) + "; existing URI: "+namespaceURI2 +
                                    "; attempting to set URI: " + nameSpaceURI);
                        }

                        // remove any twin dom lvl 1 attributes - just in case
                        removeAttribute(key);
                        if(nameSpaceURI.equals(getNamespaceURI()))
                        {
                            // clean up any attribute that may be in the same ns but with a different prefix
                            removeAttributeNS(nameSpaceURI,xmlnsLocalName(key));
                            if(xmlnsPrefix(key)==null) {
                                nameSpaceURI=null; // avoid spurios NS1 prefix
                            }
                        }

                        super.setAttributeNS(nameSpaceURI, key, value);
                    }
                }
            }
        }

        if (bDirty) {
            setDirty(true);
        }
    }

    /**
     * no namespace variant
     *
     * @param key    name of the attribute to set
     * @param value  value of the attribute
     */
    @Override
    public void setAttribute(String key, String value)
    {
        setAttribute(key, value, null);
    }
    /**
     * fastest setAttribute - use only if you know exactly what you are doing
     * @param key  name of the attribute to set
     * @param value value of the attribute
     */
    public void setAttributeRaw(String key, String value)
    {
        super.setAttribute(key, value);
    }

    /**
     * Sets an element attribute
     *
     * @param key          the name of the attribute to set
     * @param value        the value for the attribute
     * @param nameSpaceURI the namespace the element is in
     * @deprecated	use setAttribute instead
     *
     * @default SetAttribute(key, value, null)
     */
    @Deprecated
    public void setIntAttribute(String key, int value, String nameSpaceURI)
    {
        setAttribute(key, value, nameSpaceURI);
    }

    /**
     * Sets an element attribute
     *
     * @param key          the name of the attribute to set
     * @param value        the value for the attribute
     * @param nameSpaceURI the namespace the element is in
     *
     * @default SetAttribute(key, value, null)
     */
    public void setAttribute(String key, int value, String nameSpaceURI)
    {
        setAttribute(key, StringUtil.formatInteger(value), nameSpaceURI);
    }

    /**
     * Sets an element attribute
     *
     * @param key           the name of the attribute to set
     * @param value         the value for the attribute
     * @param nameSpaceURI  the namespace the element is in
     * @deprecated	use setAttribute instead
     *
     * @default setAttribute(key, value, null)
     */
    @Deprecated
    public void setRealAttribute(String key, double value, String nameSpaceURI)
    {
        setAttribute(key, StringUtil.formatDouble(value), nameSpaceURI);
    }
    /**
     * Sets an element attribute
     *
     * @param key           the name of the attribute to set
     * @param value         the value for the attribute
     * @param nameSpaceURI  the namespace the element is in
     *
     * @default setAttribute(key, value, null)
     */
    public void setAttribute(String key, double value, String nameSpaceURI)
    {
        setAttribute(key, StringUtil.formatDouble(value), nameSpaceURI);
    }

    /**
     * SetAttribute - Sets an element attribute
     *
     * @param key           the name of the attribute to set
     * @param b             value of the boolean attribute to be set
     *                      (true or false)
     * @param nameSpaceURI  the nameSpace the attribute is in
     * @deprecated use setAttribute instead
     *
     * @default setAttribute(key, b, null)
     */
    @Deprecated
    public void setBoolAttribute(String key, boolean b, String nameSpaceURI)
    {
        setAttribute(key, b,  nameSpaceURI);
    }

    /**
     * SetAttribute - Sets an element attribute
     *
     * @param key           the name of the attribute to set
     * @param b             value of the boolean attribute to be set
     *                      (true or false)
     * @param nameSpaceURI  the nameSpace the attribute is in
     *
     * @default setAttribute(key, b, null)
     */
    public void setAttribute(String key, boolean b, String nameSpaceURI)
    {
        setAttribute(key, b ? JDFConstants.TRUE : JDFConstants.FALSE, nameSpaceURI);
    }

    /**
     * increments or decrements a numeric attribute by inc
     * @param key           the name of the attribute to set
     * @param inc           the value to increment or decrement by
     * @param nameSpaceURI  the nameSpace the attribute is in
     * @return double       the attribute value after modification
     */
    public double addAttribute(String key, double inc, String nameSpaceURI)
    {
        double d=getRealAttribute(key, nameSpaceURI, 0);
        d+=inc;
        setAttribute(key, d, namespaceURI);
        return d;
    }
    /**
     * increments or decrements a numeric attribute by inc
     * @param key           the name of the attribute to set
     * @param inc           the value to increment or decrement by
     * @param nameSpaceURI  the nameSpace the attribute is in
     * @return double       the attribute value after modification
     */
    public int addAttribute(String key, int inc, String nameSpaceURI)
    {
        int i=getIntAttribute(key, nameSpaceURI, 0);
        i+=inc;
        setAttribute(key, i, namespaceURI);
        return i;
    }

    /**
     * Remove a attribute from the current element
     *
     * @param attrib        attribute name to remove
     * @param nameSpaceURI  the nameSpace of the attribut
     *
     * @default removeAttribute(attrib, null)
     */
    public void removeAttribute(String attrib, String nameSpaceURI)
    {
        removeAttribute_KElement(attrib, nameSpaceURI);
    }

    /**
     * Remove a attribute from the current element
     *
     * @param attrib        attribute name to remove
     * @param nameSpaceURI  the nameSpace of the attribut
     *
     * @default removeAttribute(attrib, null)
     */
    public void removeAttribute_KElement(String attrib, String nameSpaceURI)
    {
        if (hasAttribute(attrib, nameSpaceURI, false))
        {
            if ((nameSpaceURI==null)||nameSpaceURI.equals(JDFConstants.EMPTYSTRING))
            {
                super.removeAttribute(attrib);
            }
            else
            {
                super.removeAttributeNS(nameSpaceURI, xmlnsLocalName(attrib));
                if(nameSpaceURI.equals(getNamespaceURI())) {
                    super.removeAttributeNS(null, attrib);
                }
            }
            setDirty(true);
        }
    }

    /**
     * Checks if the actual element has a specific attribute<br>
     * this version checks within the resource and its partitioned parent xml elements
     *
     * @param attrib         the name of the attribute to look for
     *
     * @return boolean true, if the attribute is present
     */
    @Override
    public boolean hasAttribute(String attrib)
    {
        return hasAttribute_KElement(attrib, null, false);
    }

    /**
     * Checks if the actual element has a specific attribute <br>
     * this version checks within the exact xml element
     *
     * @param attrib         the name of the attribute to look for
     * @param nameSpaceURI   the nameSpace to look in
     * @param bInherit       if true also check recursively in parent elements
     *
     * @return boolean       true if the attribute is present
     *
     * @default hasAttribute(attrib, null, false)
     */
    public boolean hasAttribute(String attrib, String nameSpaceURI, boolean bInherit)
    {
        return hasAttribute_KElement(attrib, nameSpaceURI, bInherit);
    }

    /**
     * Checks if the actual element has a specific attribute <br>
     * this version checks within the exact xml element
     *
     * @param attrib       the name of the attribute to look for
     * @param nameSpaceURI the nameSpace to look in
     * @param bInherit     if true also check recursively in parent elements
     *
     * @return boolean   true if the attribute is present
     *
     * @default hasAttribute_KElement(attrib, null, false)
     */
    public boolean hasAttribute_KElement(String attrib, String nameSpaceURI, boolean bInherit)
    {
        return getDOMAttr(attrib,nameSpaceURI,bInherit)!=null;
    }

    /**
     * Append the contents of value to the existing attribute key.
     * Create Key, if it does not exist
     *
     * @param key            attribute key
     * @param value          string to be appended
     * @param nameSpaceURI   namespace of key
     * @param sep            separator between the original attribute
     *                       value and value, defaults to " " if null
     * @param bUnique        if true, the attribute will only be appended
     *                       if it is not yet within the current attribute value
     *
     * appendAttribute("key","next",JDFConstants.EMPTYSTRING,JDFConstants.COMMA)
     * applied to <xml key="first"/> results in <xml key="first,next"/>
     *
     * @default appendAttribute(key,
     *                          value,
     *                          null,
     *                          null,
     *                          false)
     */
    public void appendAttribute(String key, String value, String nameSpaceURI, String sep, boolean bUnique)
    {
        if(value==null) {
            return;
        }

        final String oldVal = getAttribute_KElement(key, nameSpaceURI, null);
        if (oldVal==null)
        {
            setAttribute(key, value, nameSpaceURI);
        }
        else // something is there
        {
            if(sep==null) {
                sep=JDFConstants.BLANK;
            }

            if (!bUnique || !StringUtil.hasToken(oldVal,value,sep,0)) // it is either not unique or not there yet --> append
            {
                setAttribute(key, oldVal + sep + value, nameSpaceURI);
            }
        }
    }

    /**
     * Tests whether an argument is a wildcard i.e null, empty or *
     * note that Wildcard ("*") is deprecated
     * ideally null should be used to denote a wildcard
     *
     * @param nodeName   the argument to test
     *
     * @return boolean   true if the name is wildcard
     */
    public static boolean isWildCard(String nodeName)
    {
        return (nodeName==null) || (nodeName.length()<2) && (nodeName.equals(JDFConstants.EMPTYSTRING) || nodeName.equals(JDFConstants.STAR));
    }

    /**
     * Tests whether the specified nodename and namespace fits the
     * nodename and namespace of 'this'
     *
     * @param nodeName       the name of the node to test.
     *                       May be either local or qualified
     * @param nameSpaceURI   the namespace of the node to test.
     *
     * @return boolean       true if fits
     */
    public boolean fitsName(String nodeName, String nameSpaceURI)
    {
        return fitsName_KElement(nodeName,nameSpaceURI);
    }
    protected boolean fitsName_KElement(String nodeName, String nameSpaceURI)
    {
        boolean bNameOK = nodeName==null || isWildCard(nodeName);

        // first check name, since it is faster
        if (!bNameOK)
        {
            String s=getNodeName();
            bNameOK=s.endsWith(nodeName);
            if(bNameOK && !s.equals(nodeName)) {
                bNameOK=nodeName.equals(xmlnsLocalName(s));
            }
        }

        // only check ns, if the name is ok
        if (bNameOK && nameSpaceURI!=null && !isWildCard(nameSpaceURI))
        {
            if (!nameSpaceURI.equals(getNamespaceURI()))
            {
                bNameOK = false;
            }
        }

        return bNameOK;
    }

    /**
     * Gets the NameSpaceURI corresponding to a given prefix,
     * returns null if no namespace is defined
     *
     * @param prefix the prefix to check for
     * @return String The nameSpaceURI that maps to prefix
     */
    public String getNamespaceURIFromPrefix(String prefix)
    {
        String strNamespaceURI = null;
        if (prefix == null || prefix.equals(JDFConstants.EMPTYSTRING))
        {
            String elementPrefix = getPrefix();
            if (elementPrefix == null)
            {
                strNamespaceURI = getNamespaceURI();
                if (strNamespaceURI != null) {
                    return strNamespaceURI;
                }
            }

            strNamespaceURI = getAttribute(AttributeName.XMLNS,null,null);
            if (strNamespaceURI != null) {
                return strNamespaceURI;
            }
        }
        else
        {
            // some well known hardcoded stuff
            if (prefix.equals(AttributeName.XSI)) {
                return AttributeName.XSIURI;
            }
            if (prefix.equals(AttributeName.XMLNS)) {
                return AttributeName.XMLNSURI;
            }

            String elementPrefix = getPrefix();
            if (prefix.equals(elementPrefix))
            {
                // we are checking an element or attribute with the same prefix as this.
                // therefore we assume that the same NamespaceURI also applies, if it is set
                strNamespaceURI = getNamespaceURI();
                if (strNamespaceURI != null)
                {
                    return strNamespaceURI;
                }
            }

            strNamespaceURI = getAttribute(prefix, AttributeName.XMLNSURI, null);

            // found a decent URI
            if (strNamespaceURI != null)
            {
                return strNamespaceURI;
            }

            NamedNodeMap nl = getAttributes();
            final int length = nl.getLength();
            for (int i = 0; i < length; i++)
            {
                final Node at = nl.item(i);
                if (at instanceof AttrNSImpl)
                {
                    AttrNSImpl ati = (AttrNSImpl) at;
                    if (prefix.equals(ati.getPrefix()))
                    {
                        strNamespaceURI=ati.getNamespaceURI();
                        if (strNamespaceURI != null)
                        {
                            return strNamespaceURI;
                        }
                    }
                }
            }

            // nothing found, recurse into parent element and try again
            KElement e = getParentNode_KElement();
            if (e !=null)
            {
                return e.getNamespaceURIFromPrefix(prefix);
            }
        }

        // we reached the document root and didn't find anything --> punt and return an empty string
        return null;
    }

    /**
     * Get the NameSpaceURI
     *
     * @return String The nameSpaceURI
     */
    @Override
    public String getNamespaceURI()
    {
        String s = super.getNamespaceURI();
        if ((s != null && s!=JDFConstants.EMPTYSTRING) || ((DocumentJDFImpl)getOwnerDocument()).isIgnoreNSDefault())
        {
            return s;
        }

        s = getPrefix();

        KElement parent=getParentNode_KElement();
        while(parent!=null)
        {
            String prefix=KElement.xmlnsPrefix(parent.getNodeName());
            if(prefix==null && s==null || prefix!=null && prefix.equals(s))
            {
                final String nsuri=parent.getNamespaceURI();
                if(nsuri!=null) // we found a valid nsuri so we might as well set it for this
                {
                    namespaceURI=nsuri;
                    return nsuri;
                }
            }
            parent=parent.getParentNode_KElement();
        }

        final String nsuri;

        if (s != null)
        {
            nsuri= getInheritedAttribute(JDFConstants.XMLNS + JDFConstants.COLON + s, null,null);
        }
        else
        {
            nsuri= getInheritedAttribute(JDFConstants.XMLNS, null, null);
        }
        if(nsuri!=null) // we found a valid nsuri so we might as well set it for this
        {
            namespaceURI=nsuri;
        }
        else if(s==null)
        {
            // ran into root and no default ns found - ciao from now on
            ((DocumentJDFImpl)getOwnerDocument()).setIgnoreNSDefault(true); 

        }
        return nsuri;
    }

    /**
     * Parses pc for it's namespace prefix
     *
     * @param nodeName string to parse
     * @return String namespace prefix of element - null if no ":" is in nodeName or nodeName starts with ":"
     */

    public static String xmlnsPrefix(final String nodeName)
    {
        if (nodeName != null)
        {
            final int posColon = nodeName.indexOf(':');
            if (posColon > 0) {
                return nodeName.substring(0, posColon);
            }
        }

        return null;
    }


    /**
     * Gets the local name of kElem
     * @deprecated use getLocalName
     * @param kElem      the element to get the LocalName from
     *
     * @return String   the local name of 'this'
     */
    @Deprecated
    public static String getLocalNameStatic(KElement kElem)
    {
        return kElem.getLocalName();
    }

    /**
     * Sets the attributes from the curent element.
     * If Attributes map is null or empty, zero is returned.
     * otherwhise the size of the map is returned
     *
     * @param map   map of attributes to set
     *
     * @return int  size of the map or zero if empty
     */
    public int setAttributes(JDFAttributeMap map)
    {
        int iRet = 0;
        if (map!=null && !map.isEmpty())
        {
            final Iterator<String> it = map.getKeyIterator();
            while(it.hasNext())
            {
                final String key = it.next();
                final String value = map.get(key);
                setAttribute(key, value, null);
            }
            iRet = map.size();
        }
        return iRet;
    }

    /**
     * Sets the attributes from the curent element to the attributes from kElem.
     * If the Attributes map from kElem is empty (kElem has no attributes),
     * zero is returned. Otherwhise the size of the map (number of attributes
     * from kElem) is returned.
     *
     * @param  kElem     the attribute source
     *
     * @return int       number of elements from kElem
     */
    public int setAttributes(KElement kElem)
    {
        return setAttributes(kElem,null);
    }
    /**
     * Sets the attributes from the curent element to the attributes from kElem.
     * If the Attributes map from kElem is empty (kElem has no attributes),
     * zero is returned. Otherwhise the size of the map (number of attributes
     * from kElem) is returned.
     *
     * @param  kElem     the attribute source
     *
     * @return int       number of elements from kElem
     */
    protected int setAttributes(KElement kElem, VString ignoreList)
    {
        if(kElem==null)
            return 0;

        final NamedNodeMap nm = kElem.getAttributes();

        final int siz = (nm == null) ? 0 : nm.getLength();
        KElement parent=null;
        if(kElem instanceof JDFResource)
        {
            parent=kElem.getParentNode_KElement();
            if(parent!=null && kElem.getNodeName().equals(parent.getNodeName()))
            {
                JDFResource r=(JDFResource)parent;
                VString il2=ignoreList;
                if(il2==null)
                {
                    il2=new VString();
                    il2.add(AttributeName.ID);
                    il2.add(AttributeName.PARTUSAGE);
                    il2.add(AttributeName.PARTIDKEYS);
                    il2.add(AttributeName.CLASS);
                    il2.appendUnique(r.getPartIDKeys());
                }
                setAttributes(parent,il2);
            }
        }
        for (int i = 0; i < siz; i++)
        {
            final Node a = nm.item(i);
            if(ignoreList==null || !ignoreList.contains(a.getLocalName()) )
            {
                setAttribute(a.getNodeName(), a.getNodeValue(), a.getNamespaceURI());
            }
        }
        return siz;
    }

    /**
     * Get the Attribute Map of the actual element
     *
     * @return JDFAttributeMap the attribute map of the actual element
     */
    public JDFAttributeMap getAttributeMap()
    {
        final JDFAttributeMap m = new JDFAttributeMap();
        final NamedNodeMap nm = getAttributes();

        final int siz = (nm == null) ? 0 : nm.getLength();

        for (int i = 0; i < siz; i++)
        {
            final Node a = nm.item(i);
            final String nodeName = a.getNodeName();
            m.put(nodeName, a.getNodeValue());
        }

        return m;
    }

    /**
     * Method init. Superclass of all inits
     * @return boolean true always
     */
    public boolean init()
    {
        return true;
    }

    /**
     *
     * @param elementName   the elementname with namespace prefix "xyz:abc"
     * @param nameSpaceURI  the namespace of the element "null" is valid
     *                      if the namespace was specified already above. The method will lookup
     *                      the namespace for you. Performance wise its better to add it nevertheless.
     *
     * @return KElement     the appended element or null
     *
     * @throws JDFException if you tried to append an element into an unspecified namespace
     */
    public synchronized KElement appendElement(String elementName, String nameSpaceURI)
    {
        KElement newChild = createChildFromName(elementName, nameSpaceURI);
        appendChild(newChild);
        setDirty(false);
        newChild.init();
        return newChild;
    }

    /**
     * creates a new child from name and nameSpaceURI and does some heuristics
     * to find a lvl 2 namespace in case of dom level 1 calls
     * @param elementName
     * @param nameSpaceURI
     * @return KElement
     */
    private KElement createChildFromName(String elementName, String nameSpaceURI)
    {
        KElement newChild = null;
        final DocumentJDFImpl ownerDoc = (DocumentJDFImpl) getOwnerDocument();

        if (nameSpaceURI==null || JDFConstants.EMPTYSTRING.equals(nameSpaceURI))
        {   ///////////////// DOM Level 1 ////////////////
            final String xmlnsPrefix = xmlnsPrefix(elementName);

            final String namespaceURI2 = ownerDoc.isIgnoreNSDefault() && xmlnsPrefix==null ? nameSpaceURI : getNamespaceURIFromPrefix(xmlnsPrefix);
            if (xmlnsPrefix!=null && (namespaceURI2==null || JDFConstants.EMPTYSTRING.equals(namespaceURI2)))
            {
                throw new JDFException("You tried to add an element \"" + elementName + "\" in an unspecified Namespace");
            }
            else if (namespaceURI2!=null && !JDFConstants.EMPTYSTRING.equals(namespaceURI2))
            {   ///////////////// we found an URI ////////////////
                newChild = DocumentJDFImpl.factoryCreate(ownerDoc, this, namespaceURI2, elementName);
            }
            else
            {   ///////////////// no URI, create DOM Level 1 ////////////////
                newChild = DocumentJDFImpl.factoryCreate(ownerDoc, this, elementName);
            }
        }
        else
        {   ///////////////// DOM Level 2 ////////////////
            newChild = DocumentJDFImpl.factoryCreate(ownerDoc, this, nameSpaceURI, elementName);
        }

        return newChild;
    }

    /**
     * append a DOM element. This function creates a new element in the
     * given namespace and appends it there.
     *
     * @param elementName  the name of the element to append
     *
     * @return KElement    the appended element
     */
    public KElement appendElement(String elementName)
    {
        return appendElement(elementName, null);
    }

    /**
     * Deletes itself from its parent
     *
     * @return KElement - the deleted element, null if this has no parent node
     */
    public KElement deleteNode()
    {
        final KElement parentElement = getParentNode_KElement();
        if (parentElement != null)
        {
            return (KElement) parentElement.removeChild(this);
        }
        return null;
    }

    /**
     * gets an array of the direct children of the current element
     * @return KElement[] the array of direct children
     */
    public KElement[] getChildElementArray()
    {
        VElement v=new VElement();
        v.ensureCapacity(10); // good guess to avoid resizing too often
        Node n=getFirstChild();
        while(n!=null){
            if(n.getNodeType()==Node.ELEMENT_NODE) {
                v.add((KElement) n);
            }
            n=n.getNextSibling();
        }
        final int size = v.size();
        KElement[] a=new KElement[size];
        return v.toArray(a);
    }

    /**
     * Get all children from the actual element matching the given conditions<br>
     * does NOT get refElement targets although the attributes are checked
     * in the target elements in case of refElements
     *
     * @param nodeName       element name you are searching for
     * @param nameSpaceURI   nameSpace you are searching for
     * @param mAttrib        attributes you are lokking for
     * @param bAnd           if true, a child is only added if it has all
     *                       attributes specified in Attributes mAttrib
     * @param maxSize        maximum size of the element vector
     *
     * @return VElement      vector with all found elements
     * @deprecated 060302 - use 6 parameter version
     * @default getChildElementVector(null, null, null, true, 0, false)
     */
    @Deprecated
    public VElement getChildElementVector(String nodeName, String nameSpaceURI, JDFAttributeMap mAttrib, boolean bAnd,
            int maxSize)
    {
        return getChildElementVector(nodeName, nameSpaceURI, mAttrib, bAnd, maxSize,false);
    }
    /**
     * Get all children from the actual element matching the given conditions<br>
     * does NOT get refElement targets although the attributes are checked
     * in the target elements in case of refElements
     *
     * @param nodeName       element name you are searching for
     * @param nameSpaceURI   nameSpace you are searching for
     * @param mAttrib        attributes you are lokking for
     * @param bAnd           if true, a child is only added if it has all
     *                       attributes specified in Attributes mAttrib
     * @param maxSize        maximum size of the element vector (0=any)
     * @param bResolveTarget if true, IDRef elements are followed, dummy at this level but needed in JDFElement
     *
     * @return VElement      vector with all found elements
     *
     * @default getChildElementVector(null, null, null, true, 0, true)
     */
    public VElement getChildElementVector(String nodeName, String nameSpaceURI, JDFAttributeMap mAttrib, boolean bAnd,
            int maxSize, boolean bResolveTarget)
    {
        bResolveTarget=bResolveTarget&&true; // fool compiler
        return getChildElementVector_KElement(nodeName, nameSpaceURI, mAttrib, bAnd, maxSize);
    }

    /**
     * Get all children from the actual element matching the given conditions<br>
     * convenience for  getChildElementVector(nodeName, nameSpaceURI, null, true, 0, true)
     * @param nodeName       element name you are searching for
     * @param nameSpaceURI   nameSpace you are searching for
     *
     * @return VElement      vector with all found elements
     *
     * @default getChildElementVector(null, null)
     * 
     */
    public VElement getChildElementVector(String nodeName, String nameSpaceURI)
    {
        return getChildElementVector(nodeName, nameSpaceURI, null, true, 0, true);
    }

    /**
     * Get all children from the actual element matching the
     * given conditions<br> does NOT get refElement targets although the
     * attributes are checked in the target elements in case of refElements
     *
     * @param nodeName          element name you are searching for
     * @param nameSpaceURI      nameSpace you are searching for
     * @param mAttrib           attributes you are lokking for
     * @param bAnd              if true, a child is only added if it has all
     *                          attributes specified in Attributes mAttrib
     * @param maxSize           maximum size of the element vector
     *
     * @return VElement          vector with all found elements
     *
     * @see org.cip4.jdflib.core.KElement#getChildElementVector(
     *          java.lang.String, java.lang.String,
     *          org.cip4.jdflib.datatypes.JDFAttributeMap, boolean, int)
     *
     * @default getChildElementVector(null, null, null, true, 0)
     */
    public VElement getChildElementVector_KElement(String nodeName,String nameSpaceURI,
            JDFAttributeMap mAttrib, boolean bAnd, int maxSize)
    {
        final VElement v = new VElement();
        if(isWildCard(nodeName)) {
            nodeName=null;
        }
        if(isWildCard(nameSpaceURI)) {
            nameSpaceURI=null;
        }
        if(mAttrib!=null && mAttrib.isEmpty()) {
            mAttrib=null;
        }

        final boolean bAlwaysFit = nodeName==null && nameSpaceURI==null;
        final boolean bMapEmpty = mAttrib == null;

        int iSize = 0;
        KElement kElem = getFirstChildElement();

        while (kElem != null)
        {
            if (bAlwaysFit || kElem.fitsName_KElement(nodeName, nameSpaceURI))
            {
                if (bMapEmpty || kElem.includesAttributes(mAttrib, bAnd))
                {
                    v.addElement(kElem);

                    if (++iSize == maxSize)
                    {
                        break;
                    }
                }
            }
            kElem = kElem.getNextSiblingElement();
        }
        return v;
    }

    /**
     * get the first child element
     *
     * @return KElement the first child element of type ELEMENT_NODE if existing otherwise null
     */
    public KElement getFirstChildElement()
    {
        Node firstChildElement = getFirstChild();
        while (firstChildElement != null)
        {
            if (firstChildElement.getNodeType() == Node.ELEMENT_NODE)
            {
                return (KElement) firstChildElement;
            }
            firstChildElement = firstChildElement.getNextSibling();
        }
        return null;
    }

    /**
     *  get the next sibling element
     *
     * @return KElement the next sibling element is existing otherwise null
     */
    public KElement getNextSiblingElement()
    {
        Node nextSiblingElement = getNextSibling();
        while (nextSiblingElement != null)
        {
            if (nextSiblingElement.getNodeType() == Node.ELEMENT_NODE)
            {
                return (KElement) nextSiblingElement;
            }
            nextSiblingElement = nextSiblingElement.getNextSibling();
        }
        return null;
    }

    /**
     *  get the previous sibling element
     *
     * @return KElement the previous sibling element is existing otherwise null
     */
    public KElement getPreviousSiblingElement()
    {
        Node previousSiblingElement = getPreviousSibling();
        while (previousSiblingElement != null)
        {
            if (previousSiblingElement.getNodeType() == Node.ELEMENT_NODE)
            {
                return (KElement) previousSiblingElement;
            }
            previousSiblingElement = previousSiblingElement.getPreviousSibling();
        }
        return null;
    }

    /**
     * Gets the previous sibling named nodename from
     * the namespace nameSpaceURI of 'this'.
     *
     * @param nodeName       the name of the sibling
     * @param nameSpaceURI   the namespace of the sibling
     *
     * @return KElement      the next sibling element of 'this',
     *                       null if none is found
     */
    public KElement getNextSiblingElement(String nodeName, String nameSpaceURI)
    {
        KElement e = getNextSiblingElement();
        while (e != null)
        {
            if (e.fitsName(nodeName, nameSpaceURI))
            {
                return e;
            }
            e = e.getNextSiblingElement();
        }
        return null;
    }

    /**
     * Gets the previous sibling named nodename from
     * the namespace nameSpaceURI of 'this'.
     *
     * @param nodeName       the name of the sibling
     * @param nameSpaceURI   the namespace of the sibling
     *
     * @return KElement      the previous sibling element of 'this',
     *                       null if none is found
     */
    public KElement getPreviousSiblingElement(String nodeName, String nameSpaceURI)
    {
        KElement e = getPreviousSiblingElement();
        while (e != null)
        {
            if (e.fitsName(nodeName, nameSpaceURI))
            {
                return e;
            }
            e = e.getPreviousSiblingElement();
        }
        return null;
    }

    /**
     * Checks if the actual element contains the attributes given in aMap
     *
     * @param aMap  the attribute names to check
     * @param bAnd  true if you want to make sure all given attributes in
     *              aMap are present.
     *              False if it is enough if only one attribute in
     *              aMap is present
     *
     * @return boolean: true if present
     *
     * @default IncludesAttributes(aMap, true)
     */
    public boolean includesAttributes(JDFAttributeMap aMap, boolean bAnd)
    {
        if (aMap==null || aMap.isEmpty())
        {
            return true;
        }
        String key;
        String value;
        final Iterator<String> it = aMap.getKeyIterator();

        if (bAnd)
        {
            while(it.hasNext())
            {
                key = it.next();
                value = aMap.get(key);
                if (!includesAttribute(key, value))
                {
                    return false;
                }
            }
            return true;
        }
        // bAnd=false
        while(it.hasNext())
        {
            key = it.next();
            value = aMap.get(key);
            if (includesAttribute(key, value))
            {
                return true;
            }
        }
        return false;
    }

    /**
     * Check if an attribute is present.
     * If attValue is '*', "" or null it is checked if the element attName is present.
     *
     * Only for simple attributes where an exact .equals match is appropriate,
     * for ranges and rangelists use JDFElement.includesMatchingAttributes()
     *
     * @param attName  the name of the attribute
     * @param attValue the value of the attribute
     *
     * @return boolean true if present
     *
     * @default includesAttribute(attName, null)
     */
    public boolean includesAttribute(String attName, String attValue)
    {
        Attr attr=getDOMAttr(attName,null,false);
        if(attr==null) {
            return false;
        }

        if (isWildCard(attValue))
        {
            return true;
        }
        return attValue.equals(attr.getValue());
    }

    /**
     * Gets children of 'this' by tag name, nameSpaceURI
     * and attribute map, if the attribute map is not empty.<br>
     * Searches the entire tree including hidden nodes that are children
     * of non-matching nodes
     *
     * @param elementName    elementname you are searching for
     * @param nameSpaceURI   nameSpace you are searching for
     * @param  mAttrib       map of attributes you are looking for <br>
     *                       Wildcards in the attribute map are supported
     * @param bDirect        if true, return value is a vector only of all direct
     *                       child elements. <br> Otherwise the returned vector
     *                       contains nodes of arbitrary depth
     * @param bAnd           if true, a child is only added, if it includes all
     *                       attributes, specified in mAttrib
     * @param maxSize        maximum size of the element vector.
     *                       maxSize is ignored if bDirect is false
     *
     * @return VElement: vector with all found elements
     *
     * @see org.cip4.jdflib.core.KElement#getChildElementVector(
     *          java.lang.String, java.lang.String,
     *          org.cip4.jdflib.datatypes.JDFAttributeMap,
     *          boolean, int, boolean)
     *
     * @default getChildrenByTagName(s,null,null, false, true, 0)
     */
    public VElement getChildrenByTagName(String elementName, String nameSpaceURI, JDFAttributeMap mAttrib, boolean bDirect,
            boolean bAnd, int maxSize)
    {
        if (bDirect)
        {
            return getChildElementVector(elementName, nameSpaceURI, mAttrib, bAnd, maxSize,true);
        }

        // maxSize is ignored in the tree walk!
        final boolean bHasNoMap = mAttrib==null || mAttrib.isEmpty();

        final VElement v = new VElement();
        KElement e = getFirstChildElement();
        final boolean bAlwaysFit = isWildCard(elementName) && isWildCard(nameSpaceURI);
        while (e != null)
        {
            if ((bAlwaysFit || e.fitsName(elementName, nameSpaceURI))
                    && (bHasNoMap || e.includesAttributes(mAttrib, bAnd)))
            {
                // this guy is the one
                v.add(e);
                if (maxSize>0 && v.size() == maxSize)
                {
                    return v;
                }
            }
            final int maxSizeRecurse = maxSize>0 ? maxSize - v.size() : maxSize;
            final VElement v2 = e.getChildrenByTagName(elementName, nameSpaceURI, mAttrib, bDirect, bAnd, maxSizeRecurse);
            v.addAll(v2);

            if ( maxSize > 0 && v.size() >= maxSize)
            {
                return v;
            }
            e = e.getNextSiblingElement();
        }
        return v;
    }

    /**
     * wrappers of DOM routines that dont bang on null nodes
     *
     * @param s             the local name of the elements to match on
     * @param nameSpaceURI  the namespace URI of the elements to match on
     *
     * @return VElement     a new NodeList object containing all
     *                      the matched Elements
     *
     * @default getElementsByTagName_KElement(s, null)
     */
    public VElement getElementsByTagName_KElement(String s, String nameSpaceURI)
    {
        VElement vEle = null;
        if(s==null)s=JDFConstants.STAR;

        if ((nameSpaceURI==null)||nameSpaceURI.equals(JDFConstants.EMPTYSTRING))
        {
            vEle = new VElement(getElementsByTagName(s));
        }
        else
        {
            vEle = new VElement(getElementsByTagNameNS(nameSpaceURI, s));
        }

        return vEle;
    }

    /**
     * Get a vector of all children with a matching attribte
     *
     * @param nodeName      elementname you are searching for
     * @param attName       attributes you are looking for
     * @param nameSpaceURI  nameSpace you are searching for
     * @param attVal        value of the attribute you are searching for
     * @param bDirect       if true  : return only direct children
     *                      if false : search recursively
     *
     * @return VElement - vector with all found elements
     *
     * @default GetChildrenWithAttribute(nodeName,
     *                                   attName,
     *                                   null,
     *                                   null,
     *                                   true)
     *
     * @deprecated use getChildrenByTagName(nodeName, nameSpaceURI,
     *                                      new JDFAttributeMap(attName, attVal), bDirect, true, 0);
     */
    @Deprecated
    public VElement getChildrenWithAttribute(String nodeName, String attName, String nameSpaceURI, String attVal,
            boolean bDirect)
    {
        return getChildrenByTagName(nodeName, nameSpaceURI, new JDFAttributeMap(attName, attVal),
                bDirect, true, 0);
    }

    /**
     * Get the actual element, create if not there
     *
     * @param nodeName      name of the node from the element
     *
     * @return KElement     the requested element
     */
    public KElement getCreateElement(String nodeName)
    {
        return getCreateElement(nodeName, null, 0);
    }

    /**
     * Get a vector of all IDs that occur multiple times
     *
     * @param attributeName      name of the attribute to test for
     * @return VString     the list of multiply occurring ID values, null if all is well
     */
    public VString getMultipleIDs(String attributeName)
    {
        VString vRet=new VString();
        getMultipleIDs(attributeName, vRet, new HashSet());
        return vRet.isEmpty() ? null : vRet;
    }

    /**
     * Get a vector of all IDs that occur multiple times
     *
     * @param attributeName      name of the attribute to test for
     * @param vRet used for recursion; should be null
     * @param setID  used for recursion; should be null
     * @return VString     the list of multiply occurring ID values, null if all is well
     */
    private void getMultipleIDs(String attributeName, VString vRet, Set setID)
    {
        String id=getAttribute_KElement(attributeName, null, null);
        if(id!=null)
        {
            if(setID.contains(id)) {
                vRet.appendUnique(id);
            } else {
                setID.add(id);
            }
        }
        KElement child=getFirstChildElement();
        while(child!=null)
        {
            child.getMultipleIDs(attributeName,vRet,setID);
            child=child.getNextSiblingElement();
        }
    }

    /**
     * Get the actual element, create if not there
     *
     * @param nodeName     name of the node from the element
     * @param nameSpaceURI the name of the namespaceURI
     * @param iSkip        which one you want
     *
     * @return KElement   the requested element
     *
     * @default getCreateElement(nodeName, null, 0)
     */
    public KElement getCreateElement(String nodeName, String nameSpaceURI, int iSkip)
    {
        final KElement e=getElement(nodeName, nameSpaceURI, iSkip);
        return e==null ? appendElement(nodeName, nameSpaceURI) : e;
    }

    /**
     * Gets the iSkip-th child node with matching nodeName and nameSpaceURI,
     * optionally creates it if it doesn't exist. <br>
     * If iSkip is more than one larger that the number of elements only
     * one is appended
     *
     * @param nodeName       name of the child node to get
     * @param nameSpaceURI   namespace to search for
     * @param iSkip          number of matching child nodes to skip
     *
     * @return KElement the matching child element
     */
    public KElement getCreateElement_KElement(String nodeName, String nameSpaceURI, int iSkip)
    {
        KElement kElem = getElement_KElement(nodeName, nameSpaceURI, iSkip);

        if (kElem == null)
        {
            kElem = appendElement(nodeName, nameSpaceURI);
        }

        return kElem;
    }

    /**
     * Get the actual element - utility routine.
     *
     * @param nodeName      Name of the node
     * @return KElement - the child node
     *
     *
     * @default getElement_KElement(nodeName, null, 0)
     */
    public KElement getElement(String nodeName)
    {
        return getElement(nodeName, null, 0);
    }

    /**
     * Gets an existing iSkip-th child node with matching
     * nodeName and nameSpaceURI
     *
     * @param nodeName       name of the child node to get
     * @param nameSpaceURI   namespace to search for
     * @param iSkip          number of matching child nodes to skip
     *
     * @return KElement      the matching child element
     *
     * @default getElement_KElement(nodeName, null, 0)
     */
    public KElement getElement(String nodeName, String nameSpaceURI, int iSkip)
    {
        return getElement_KElement(nodeName, nameSpaceURI, iSkip);
    }

    /**
     * performance enhanced function to access multiple elements e.g. by ID
     * get a HashMap of key= attribute value, object=element
     *
     * @param elementName the names of the elements, wildcard if null
     * @param elementNS the namespace URI of the elements, any if null
     * @param attributeName the attribute name - MUST  not be null
     * @return a hashmap of the matching elements
     */
    public HashMap getElementHashMap(String elementName, String elementNS, String attributeName)
    {
        HashMap m=new HashMap();
        VElement v=getChildElementVector_KElement(elementName,elementNS,new JDFAttributeMap(attributeName,(String)null),true,0);
        final int siz=v.size();
        for(int i=0;i<siz;i++)
        {
            KElement e=v.elementAt(i);
            m.put(e.getAttribute(attributeName),e);
        }
        return m;
    }

    /**
     * getElement - Get the actual element
     *
     * @param nodeName     Name of the node
     * @param nameSpaceURI Name of the namespaceURI to search in
     * @param iSkip        number of element to get, if negative count backwards (-1 is the last)
     *
     * @return KElement   the child node
     *
     * @default getElement_KElement(nodeName, null, 0)
     */
    public KElement getElement_KElement(String nodeName, String nameSpaceURI, int iSkip)
    {
        KElement kElem = getFirstChildElement();
        int i = 0;
        if(iSkip<0) {
            iSkip=numChildElements_KElement(nodeName, nameSpaceURI)+iSkip;
        }

        if(iSkip<0) {
            return null;
        }

        while (kElem != null)
        {
            if (kElem.fitsName_KElement(nodeName, nameSpaceURI))
            {
                // this guy is the one
                if (i++ == iSkip)
                {
                    return kElem;
                }
            }
            kElem = kElem.getNextSiblingElement();
        }
        return null;
    }

    /**
     * Get the n'th Ancestor node with name parentNode
     *
     * @param parentNode    name of the parent node to search for
     * @param depth         which one you want to have in order of appearance
     *
     * @return KElement     the n'th ancestor node with name nodeName
     *
     * @default getDeepParent(parentNode, 0)
     */
    public KElement getDeepParent(String parentNode, int depth)
    {
        if (!getLocalName().equals(parentNode))
        {
            final KElement parentElement = getParentNode_KElement();
            return  (parentElement == null) ? null : parentElement.getDeepParent(parentNode, depth);
        }
        else if (depth > 0)
        {
            final KElement parentElement = getParentNode_KElement();
            // last in chain or
            // leaving structure
            if ((parentElement != null) && (parentNode.equals(parentElement.getLocalName())))
            {
                return parentElement.getDeepParent(parentNode, depth - 1);
            }
        }
        return this;
    }

    /**
     * Get the ancestor which may have one of the node names
     * defined in parentNode
     *
     * @param vParentElement  vector with node names to search for
     * @param depth           which one you want to have (in order of appearance)
     *
     * @return KElement     the first ancestor node with name nodeName
     * @deprecated - loop over the single node method
     */
    @Deprecated
    public KElement getDeepParent(Vector vParentElement, int depth)
    {
        KElement kRet = this;

        if (!(vParentElement.contains(getNodeName())))
        {
            kRet = getParentNode_KElement().getDeepParent(vParentElement, depth);
        }
        else if (depth > 0)
        {
            final KElement par = getParentNode_KElement();

            if (par != null && vParentElement.contains(par.getNodeName()))
            {
                kRet = par.getDeepParent(vParentElement, depth - 1);
            }
        }

        return kRet;
    }

    /**
     * Gets the root element of the current document
     *
     * @return KElement the root element of the current document
     */
    public KElement getDocRoot()
    {
        KElement kDocRoot = null;
        final Element rootElem = getOwnerDocument().getDocumentElement();

        if (rootElem != null)
        {
            kDocRoot = (KElement) rootElem;
        }

        return kDocRoot;
    }

    /**
     * Gets all attribute keys of 'this' as a vector of strings
     *
     * @return vWString: a vector of all attribute keys in 'this'
     */
    public VString getAttributeVector()
    {
        return getAttributeVector_KElement();
    }


    /**
     * Gets all attribute keys of 'this' as a vector of strings
     *
     * @return vWString: a vector of all attribute keys in 'this'
     */
    public VString getAttributeVector_KElement()
    {
        final VString v = new VString();
        final NamedNodeMap nm = getAttributes();

        final int siz = (nm == null) ? 0 : nm.getLength();

        for (int i = 0; i < siz; i++)
        {
            final Node a = nm.item(i);
            v.addElement(a.getNodeName());
        }

        return v;
    }

    /**
     * This function first, gets all required attributes and then compare
     * them with the attributes present and returns a Vector with the missing
     * attributes
     *
     * @param nMax       maximum size of the returned Vector
     *
     * @return VString vector with the missing attribute names
     *
     * @default getMissingAttributes(9999999)
     */
    public VString getMissingAttributes(int nMax)
    {
        final VString v = getTheAttributeInfo().requiredAttribs();
        return getMissingAttributeVector(v, nMax);
    }

    /**
     * This function first, gets all deprecated attributes
     *
     * @param nMax       maximum size of the returned Vector
     *
     * @return Vector    vector with the deprecated attributes
     *
     * @default getMissingAttributes(9999999)
     */
    public VString getDeprecatedAttributes(int nMax)
    {
        final VString v = deprecatedAttributes();
        return getMatchingAttributeVector(v, nMax);
    }

    /**
     * This function first, gets all prerelease attributes
     * It ignores any atrributes that have been added by a schema parser
     *
     * @param nMax       maximum size of the returned Vector
     *
     * @return Vector    vector with the prerelease attributes
     *
     * @default getMissingAttributes(9999999)
     */
    public VString getPrereleaseAttributes(int nMax)
    {
        VString v        = getMatchingAttributeVector(prereleaseAttributes(), nMax);
        AttributeInfo ai = null;
        if (!v.isEmpty()) {
            ai = getTheAttributeInfo();
        }

        // ideally we would find a better method to recognize schema placed attributes
        for (int i = v.size() - 1; i >= 0; i--)
        {
            final String key = v.stringAt(i);
            if (ai != null && getAttribute(key).equals(ai.getAttributeDefault(key)))
            {
                v.remove(i);
            }
        }
        return v;
    }

    /**
     * Comma separated list of all required attributes.
     * KElement is generic, therefore the list is empty
     * @return String the comma separated list of required attribute keys
     */
    public VString requiredAttributes()
    {
        return getTheAttributeInfo().requiredAttribs();
    }

    /**
     * Comma separated list of all optional attributes.
     * KElement is generic, therefore only the XML generic
     * attributes are listed <br>
     * xmlns: the namespace declaration
     *
     * @return String the comma separated list of optional attribute keys
     */
    public VString optionalAttributes()
    {
        return getTheAttributeInfo().optionalAttribs();
    }

    /**
     * map of all defaults from the schema
     *
     * @return JDFAttributeMap the comma separated list of deprecated attribute keys
     */
    public JDFAttributeMap getDefaultAttributeMap ()
    {
        return getTheAttributeInfo().getDefaultAttributeMap();
    }

    /**
     * list of all deprecated attributes.
     * KElement is generic, therefore the list is empty
     *
     * @return String the comma separated list of deprecated attribute keys
     */
    public VString deprecatedAttributes()
    {
        return getTheAttributeInfo().deprecatedAttribs();
    }

    /**
     * Comma separated list of all deprecated attributes.
     * KElement is generic, therefore the list is empty
     *
     * @return String the comma separated list of deprecated attribute keys
     */
    public VString prereleaseAttributes()
    {
        return getTheAttributeInfo().prereleaseAttribs();
    }


    /* KElement is generic, therefore the list is empty
     *
     * @return String the comma separated list of known attribute keys
     */
    public VString knownAttributes()
    {
        return getTheAttributeInfo().knownAttribs();
    }

    /**
     * checks if the curent element has other
     * attributes then also present in vReqKeys. If the attribute is not
     * present in vReqKeys, the attribut is added to a new vector. The new
     * vector is returned if there is no missing element left or the new
     * vector has reached the given size nMax.
     *
     * @param vReqKeys  the vector with the attributes you already have
     * @param nMax      vector with the missing attributes
     *
     * @return Vector   the vector with the missing attributes
     *
     * @default getMissingAttributeVector(vReqKeys, 9999999)
     */
    public VString getMissingAttributeVector(VString vReqKeys, int nMax)
    {
        final VString vAtts = getAttributeVector();
        final VString vMissing = new VString(); // is a StringVector like vReqKeys

        String prefix = getPrefix();
        if (prefix != null && !prefix.equals(JDFConstants.EMPTYSTRING))
        {
            prefix += JDFConstants.COLON;
        }
        else
        {
            prefix = JDFConstants.EMPTYSTRING;
        }
        for (int i = 0; i < vReqKeys.size() && vMissing.size() < nMax; i++)
        {
            final String req = vReqKeys.stringAt(i);
            if (!vAtts.contains(prefix + req) && !vAtts.contains(req)&&
                    (!req.equals(JDFConstants.XMLNS)||super.getNamespaceURI()==null))
            {
                vMissing.addElement(prefix + req);
            }
        }
        return vMissing;
    }
    /**
     * checks if the curent element has other
     * attributes that are present in vReqKeys. If the attribute is
     * present in vReqKeys, the attribut is added to a new vector. The new
     * vector is returned if there is no missing element left or the new
     * vector has reached the given size nMax.
     *
     * @param vReqKeys  the vector with the attributes you already have
     * @param nMax      vector with the missing attributes
     *
     * @return Vector   the vector with the missing attributes
     *
     * @default getMissingAttributeVector(vReqKeys, 9999999)
     */
    private VString getMatchingAttributeVector(Vector vReqKeys, int nMax)
    {
        final VString vAtts = getAttributeVector();
        final VString vMatching = new VString(); // is a StringVector like vReqKeys

        String prefix = getPrefix();
        if (prefix != null && !prefix.equals(JDFConstants.EMPTYSTRING))
        {
            prefix += JDFConstants.COLON;
        }
        else
        {
            prefix = JDFConstants.EMPTYSTRING;
        }
        for (int i = 0; i < vReqKeys.size() && vMatching.size() < nMax; i++)
        {
            final String req = (String) vReqKeys.elementAt(i);
            if (vAtts.contains(prefix + req) || vAtts.contains(req))
            {
                vMatching.addElement(prefix + req);
            }
        }
        return vMatching;
    }

    /**
     * Returns a vector which contains the childs of
     * the actual element. But every child only once.
     *
     * @return Vector   vector with the childs of the actual element.
     *                  Ever child typ is only added once.
     */
    public VString getElementNameVector()
    {
        final VElement vChildElem = getChildElementVector(null, null, null, true, 0,false);
        final VString v = new VString();

        for (int i = 0; i < vChildElem.size(); i++)
        {
            final String strName = (vChildElem.item(i)).getNodeName();
            v.appendUnique(strName);
        }
        return v;
    }

    /**
     * get the missing elements as a vector
     * <p>
     * default: getMissingElements(99999999)
     *
     * @param nMax  maximum value of missing elements to return
     *
     * @return VString  vector with nMax missing elements
     */
    public VString getMissingElements(int nMax)
    {
        final VString v = getTheElementInfo().requiredElements();
        return getMissingElementVector(v, nMax);
    }

    /**
     * @since 060517 changed signature to VString
     * @return required elements
     */
    public VString requiredElements()
    {
        return getTheElementInfo().requiredElements();
    }

    /**
     * Comma separated list of all optional element names;
     * KElement is generic, therefore the list is empty
     *
     * @return VString the comma separated list of optional element names
     */
    public VString optionalElements()
    {
        return getTheElementInfo().optionalElements();
    }
    /**
     * comma separated list of all unique Elements that may occur at most once;
     * KElement is generic, therefore the list is empty
     *
     * @return String  the comma separated list of required element names
     */
    public VString uniqueElements()
    {
        return getTheElementInfo().uniqueElements();
    }


    /**
     * comma separated list of all prerelease Elements that may occur in a future version
     *
     * @return String  the comma separated list of required element names
     */
    public String prereleaseElements()
    {
        VString v=getTheElementInfo().prereleaseElements();
        return StringUtil.setvString(v,JDFConstants.COMMA,null,null);
    }

    /**
     * Comma separated list of all prerelease elements.
     * <p>
     * default: getPrereleaseElements(99999999)
     *
     * @return VString   vector with nMax missing elements
     */
    public VString getPrereleaseElements(int nMax)
    {
        VString v=getTheElementInfo().prereleaseElements();
        return getMatchingElementVector(v, nMax);
    }


    /**
     * Vector of deprecated elements below the current element.
     * <p>
     * default: getDeprecatedElements(99999999)
     *
     * @return VString   vector with nMax missing elements
     */
    public VString getDeprecatedElements(int nMax)
    {
        VString v = getTheElementInfo().deprecatedElements();
        return getMatchingElementVector(v, nMax);
    }



    /**
     * Comma separated list of all known element names;
     *
     * @return String the comma separated list of known element names
     */
    public VString knownElements()
    {
        VString s= requiredElements();
        s.appendUnique(optionalElements());
        return s;
    }

    /**
     * Returns a vector with missing elements
     * <p>
     * default: getMissingElementVector(vRequiredKeys, 9999999)
     *
     * @param vRequiredKeys vector with all element wich are required
     * @param nMax     maximum amount of missing element inside
     *                 the returned vector
     *
     * @return Vector  the vector with the missing elements
     */
    public VString getMissingElementVector(Vector vRequiredKeys, int nMax)
    {
        final VString vElements = getElementNameVector();
        final VString vMissing  = new VString();

        for (int i = 0; i < vRequiredKeys.size() && vMissing.size() < nMax; i++)
        {
            final String requiredKey = (String) vRequiredKeys.elementAt(i);
            if (!vElements.contains(requiredKey))
            {
                if (!checkInstance(vElements, requiredKey))
                {
                    vMissing.add(requiredKey);
                }
            }
        }

        return vMissing;
    }
///////////////////////////////////////////////////////////////////////////////

    private static final DocumentJDFImpl m_dummyDocumentJDFImpl = new DocumentJDFImpl();
    private boolean checkInstance(VString vElements, String requiredKey)
    {
        Class requiredClass 	  = m_dummyDocumentJDFImpl.getFactoryClass(requiredKey);
        Class elementClass		  = null;
        Iterator<String> elemIter = vElements.iterator();
        while (elemIter.hasNext() && !requiredClass.equals(elementClass))
        {
            String elemName = elemIter.next();
            elementClass    = m_dummyDocumentJDFImpl.getFactoryClass(elemName);
        }

        return requiredClass.equals(elementClass);
    }

    /**
     * Returns a vector with matching elements
     * <p>
     * default: getMissingElementVector(vReqKeys, 9999999)
     *
     * @param vReqKeys vector with all element wich are required
     * @param nMax     maximum amount of missing element inside
     *                 the returned vector
     *
     * @return Vector the vector with the missing elements
     */
    private VString getMatchingElementVector(VString vReqKeys, int nMax)
    {
        final VString vAtts = getElementNameVector();
        final VString vReturn = new VString();

        for (int i = 0; i < vReqKeys.size() && vReturn.size() < nMax; i++)
        {
            if (vAtts.contains(vReqKeys.elementAt(i)))
            {
                vReturn.addElement(vReqKeys.elementAt(i));
            }
        }

        return vReturn;
    }

    /**
     * looking for a specified target with an id, e.g. resource.<br>
     * Offers access to exactly KElements implementation of GetTarget even
     * if called for an instance of one of it's subclasses.
     * <p>
     * default: getTarget(id, AttributeName.ID)
     *
     * @param id      value of the ID tag to search
     * @param attrib  name of the ID tag, defaults to "ID"
     *
     * @return KElement - the element if existing, otherwise <code>null</code>
     */
    public final KElement getTarget(String id, String attrib)
    {
        return getTarget_KElement(id, attrib);
    }

    /**
     * Gets the target of link. Follows an ID-IDREF pair by recursively
     * searching for an attrib with the value id
     *
     * @param id         value of the ID tag to search
     * @param attrib     name of the ID tag, defaults to "ID"
     *
     * @return KElement  the target of link - the element node.
     *
     */
    public KElement getTarget_KElement(String id, String attrib)
    {
        if (id==null || id.equals(JDFConstants.EMPTYSTRING))
        {
            return null;
        }
        boolean bID=false;
        KElement kRet = null;
        if(attrib==null) {
            attrib=AttributeName.ID;
        }

        // try to find the target ID in the cached list
        XMLDocUserData userData = getXMLDocUserData();
        if (attrib.equals(AttributeName.ID)&& userData!=null)
        {
            kRet = userData.getTarget(id);
            bID=true;
        }

        //if it  wasn't in the cached list, search for it
        if (kRet == null)
        {
            // loop upwards from here
            // links are most likely quite local
            KElement excludeElement = null;
            KElement root = this;
            final KElement docRoot = getDocRoot();
            boolean bFound = false;
            if(!bID) {
                userData=null;
            }

            while (root != null && !bFound)
            {
                KElement deepElement = root.getDeepElementByID(attrib, id, excludeElement,userData);

                // search tree one level higher
                if (deepElement == null)
                {
                    if (root==docRoot)
                    {
                        kRet = null;
                        bFound = true;
                    }
                    else
                    {
                        excludeElement = root; // was already looked at
                        root = root.getParentNode_KElement();
                    }
                }
                else
                {
                    kRet = deepElement; // found it; return it
                    bFound = true;
                }
            }
        }
        return kRet;
    }

    /**
     * this is an optimized version of GetDeepElement()
     * which returns a complete list of elements.
     * Here we abort, when we found the first element that fits.
     * (There is only one element, because the id must be unique)
     *
     * @param attName           attribute name
     * @param id                attribute ID value
     * @param childToExclude    here can be specified, if this method should
     *                          exclude a child-element when searching
     *                          This is useful, when searching a tree up
     * @param ud                userdata with reference to id cache, if null, no caching
     *
     * @return KElement         the element specified by id and name
     */
    protected KElement getDeepElementByID(String attName, String id, KElement childToExclude, XMLDocUserData ud)
    {
        String attVal = getAttribute_KElement(attName,null,null);
        if (attVal!=null)
        {
            if (ud!=null)
            {
                ud.setTarget(this,attVal);
            }
            if(attVal.equals(id))
            {
                return this; // just found ourselves
            }
        }
        //tree walk children
        KElement childElement = getFirstChildElement();

        while (childElement != null)
        {
            if (!childElement.equals(childToExclude))
            {
                KElement kDeepElement = childElement.getDeepElementByID(attName, id, childToExclude,ud);
                if (kDeepElement != null)
                {
                    return kDeepElement; // just got it
                }
            }
            // not yet found, try next sibling
            childElement = childElement.getNextSiblingElement();
        }// end while
        return null;
    }



    /**
     * get the first child element
     *
     * @param parent  the node to get the first element node from
     *
     * @return Element - the first child element if existing otherwise null
     * @deprecated use elem.getFirstChildElement
     */
    @Deprecated
    public static Element getFirstElementNode(Element parent)
    {
        Node firstChildElement = parent.getFirstChild();

        while (firstChildElement != null && firstChildElement.getNodeType() != Node.ELEMENT_NODE)
        {
            firstChildElement = firstChildElement.getNextSibling();
        }

        return (Element) firstChildElement;
    }

    /**
     * get the next sibling element
     *
     * @param elem      the Element to get the next element node from
     *
     * @return Element  the first sibling element if existing otherwise null
     * @deprecated - use elem.getNextSiblingElement();
     */
    @Deprecated
    public static Element getNextElementNode(Element elem)
    {
        Node nextSiblingElement = elem.getNextSibling();

        while (nextSiblingElement != null && nextSiblingElement.getNodeType() != Node.ELEMENT_NODE)
        {
            nextSiblingElement = nextSiblingElement.getNextSibling();
        }

        return (Element) nextSiblingElement;
    }

    /**
     * Checks if this element is equal to element kElem
     *
     * @param kElem     the element to compare
     *
     * @return boolean  true if equal
     */
    public boolean isEqual(KElement kElem)
    {
        if(kElem==null) {
            return false;
        }
        if(this.equals(kElem)) {
            return true;
        }
        if(numChildNodes(0)!=kElem.numChildNodes(0)){
            return false;
        }
        if (!getNodeName().equals(kElem.getNodeName()))
        {
            return false;
        }

        if (getNodeType() != kElem.getNodeType())
        {
            return false;
        }

        if (!includesAttributes(kElem.getAttributeMap(), true))
        {
            return false;
        }

        if (!kElem.includesAttributes(getAttributeMap(), true))
        {
            return false;
        }

        final String txt=getText();
        final String txt2=kElem.getText();
        if(txt==null && txt2!=null) {
            return false;
        }
        if(txt2==null && txt!=null) {
            return false;
        }
        if(txt!=null && !txt.equals(txt2)) {
            return false;
        }


        final VElement l1 = getChildElementVector(null, null, null, true, 0,false);
        final VElement l2 = kElem.getChildElementVector(null, null, null, true, 0,false);

        if (l1.size() != l2.size())
        {
            return false;
        }
        for (int i = 0; i < l1.size(); i++)
        {
            final KElement kNode1 = l1.item(i);
            final KElement kNode2 = l2.item(i);

            if (!kNode1.isEqual(kNode2))
            {
                return false;
            }
        }

        return true;
    }

    /**
     * Get all child nodes from the actual element
     *
     * @return VElement list of all childs
     * @deprecated use getChildElementVector(null, null, null, true, 0)
     */
    @Deprecated
    public VElement getChildNodes_KElement()
    {
        return new VElement(getChildNodes());
    }

    /**
     * Get the unknown attributes
     * <p>
     * default: getUnknownAttributes(bIgnorePrivate, 9999999)
     *
     * @param bIgnorePrivate    if true the private attributes will be ignored
     * @param nMax              mamimum amount of unknown attributes to return
     *
     * @return Vector           a vector with all unknown atttributes
     *                          the Element have
     */
    public VString getUnknownAttributes(boolean bIgnorePrivate, int nMax)
    {
        final VString vKnownAttribs = knownAttributes();
        final VString v = bIgnorePrivate 
        ? new VString(StringUtil.tokenize(" :JDF", JDFConstants.COLON, false))
        : new VString();
        return getUnknownAttributeVector(vKnownAttribs, v, nMax);
    }

    /**
     * Gets the unknown attributes
     * <p>
     * default: getUnknownAttributeVector(vKnownKeys, new Vector(), 99999999)
     *
     * @param vKnownKeys    vector with all known keys
     * @param vInNameSpace  vector with all namespaces to search in
     * @param nMax          maximum amount of unknown attributes to return
     *
     * @return        vector with maximum nMax unknown Attributes
     */
    public VString getUnknownAttributeVector(Vector vKnownKeys, Vector vInNameSpace, int nMax)
    {
        if(nMax<0)
            nMax=Integer.MAX_VALUE;
        final VString vAtts = getAttributeVector_KElement();
        final VString vUnknown = new VString();
        if(vKnownKeys.contains("*")) {
            return vUnknown;
        }

        final boolean bAllNS = vInNameSpace.isEmpty();

        for (int j = 0; j < vInNameSpace.size(); j++)
        {
            // tokenize needs a blank
            if (vInNameSpace.elementAt(j).equals(JDFConstants.BLANK))
            {
                vInNameSpace.setElementAt(JDFConstants.EMPTYSTRING, j);
            }
        }

        for (int i = 0; i < vAtts.size() && vUnknown.size() < nMax; i++)
        {
            String strAtts = vAtts.elementAt(i);
            String ns = KElement.xmlnsPrefix(strAtts);
            if( (JDFConstants.XSI.equals(ns)) || JDFConstants.XMLNS.equals(ns)) {
                continue;
            }

            if (bAllNS || ns==null || vInNameSpace.contains(ns))
            {
                if (!vKnownKeys.contains(strAtts))
                {
                    vUnknown.addElement(strAtts);
                }
            }
        }

        return vUnknown;
    }

    /**
     * Get a vector with the unknown elements
     * <p>
     * default: getUnknownElements(bIgnorePrivate, 99999999)
     *
     * @param bIgnorePrivate    true, to ignore the private elements
     * @param nMax              maximum number of elements in the vector returned
     *
     * @return Vector           a vector with nMax unknown elements
     *                          in the actual element.
     *
     */
    public Vector getUnknownElements(boolean bIgnorePrivate, int nMax)
    {
        final VString v1  = knownElements();
        final VString v2  = StringUtil.tokenize(" :JDF", JDFConstants.COLON,false);
        return  getUnknownElementVector(v1, bIgnorePrivate ? v2 : new Vector(), nMax);
    }

    /**
     * Get a vector with the unknown elements
     * <p>
     * default: getUnknownElementVector(vKnownKeys, vInNameSpace, 9999999)
     *
     * @param vKnownKeys     vector of all known elements
     * @param vInNameSpace   vector of all namespaces to search in
     * @param nMax           maximum amount of elements to return
     *
     * @return Vector        a vector containing the unknown elements
     */
    public Vector getUnknownElementVector(Vector vKnownKeys, Vector vInNameSpace, int nMax)
    {
        for (int j = 0; j < vInNameSpace.size(); j++)
        {
            // tokenize needs a blank
            if (vInNameSpace.elementAt(j).equals(JDFConstants.BLANK))
            {
                vInNameSpace.setElementAt(JDFConstants.EMPTYSTRING, j);
            }
        }

        final VString vAtts = getElementNameVector();
        final VString vUnknown = new VString();

        if (vAtts.size() > 0)
        {
            int i = 0;
            final boolean bAllNS = vInNameSpace.isEmpty();

            do
            {
                final String attr = vAtts.elementAt(i);
                String ns = KElement.xmlnsPrefix(attr);
                if(ns==null) {
                    ns=JDFConstants.EMPTYSTRING;
                }

                if (bAllNS || (vInNameSpace.contains(ns)))
                {
                    if (!vKnownKeys.contains(attr))
                    {
                        vUnknown.addElement(attr);
                    }
                }
            } while (vUnknown.size() < nMax && ++i < vAtts.size());
        }

        return vUnknown;
    }

    /**
     * checks if KElement child is ancestor or not
     *
     * @param child     child to check
     *
     * @return boolean  true if anchestor
     */
    public boolean isAncestor(KElement child)
    {
        if (child != null)
        {
            if (child==this)
            {
                return true;
            }
            return isAncestor(child.getParentNode_KElement());
        }
        return false;
    }

    /**
     * Not full implemented right now right now it checks
     * if a the current object is null (return false) or if there is a owner
     * document (if not, return false)
     *
     * @deprecated use isValid(EnumValidationLevel.Complete)
     * @return boolean - true if valid (see above)
     */
    @Deprecated
    public boolean isValid()
    {
        boolean result = true;

        if (getOwnerDocument() == null)
        {
            result = false;
        }

        return result;
    }

    /**
     * Mother of all validators
     *
     * @param level  validation level, defaults to complete if null
     *
     * <blockquote><ul><li>level ValidationLevel_None: always return true
     * <li>level ValidationLevel_Construct: incomplete and null
     *     elements are valid
     *
     * <li>level ValidationLevel_Incomplete: incomplete elements are valid
     *
     * <li>level ValidationLevel_Complete: full validation of an
     *     individual resource
     *
     * <li>level ValidationLevel_RecursiveIncomplete:
     *     incomplete validation of an individual resource and all of its
     *     child elements - e.g. for pools
     *
     * <li>level ValidationLevel_RecursiveComplete: full validation of an
     *     individual resource and all of its child elements
     *     - e.g. for pools</ul></blockquote>
     *
     * @return boolean   true, if the node is valid.
     */
    public boolean isValid(EnumValidationLevel level)
    {
        if(level==null) {
            level=EnumValidationLevel.Complete;// makes compiler happy
        }
        return getOwnerDocument() != null;
    }

    /**
     * is the attribute valid and of type iType. iType is of type EnumAttributeType but may be expanded in child classes
     * <p>
     * default: validAttribute(key, null)
     *
     * @param key the attribute name
     * @param nameSpaceURI attribute namespace uri
     * @param level the validation level
     *
     * @return boolean: true if the attribute is valid
     */
    public boolean validAttribute(String key,String nameSpaceURI, EnumValidationLevel level)
    {
        return getTheAttributeInfo().validAttribute(key,getAttribute(key,nameSpaceURI,null),level);
    }

    /**
     * Get the ValuedEnum for an enumerated attribute
     * @param key the local name of the attribute
     * @return ValuedEnum.Unknown of the requested attribute or null if the attribute is not an enum
     */
    public ValuedEnum getEnumforAttribute(String key)
    {
        return getTheAttributeInfo().getAttributeEnum(key);
    }

    /**
     * Get the EnumAttributeType for an  attribute
     * @param key the local name of the attribute
     * @return EnumAttributeType of the attribute
     */
    public ValuedEnum getTypeForAttribute(String key)
    {
        return getTheAttributeInfo().getAttributeType(key);
    }

    /**
     * Get the number of child elements. If String 'node' is an empty string
     * or '*', all nodes are counted.
     * <p>
     * default: numChildElements(null, null)
     *
     * @param node          the nodes with name 'node' to count
     * @param nameSpaceURI  the nameSpace to look in
     *
     * @return int          the number of matching child elements
     */
    public int numChildElements(String node, String nameSpaceURI)
    {
        return numChildElements_KElement(node, nameSpaceURI);
    }

    /**
     * Get the number of child elements. If String 'node' is null, an empty string
     * or '*', all nodes are counted. This method is the same as numChildElements
     * but prevents before the maybe unwanted virtuality of numChildElements.
     * <p>
     * default: numChildElements_KElement(null, null)
     *
     * @param node          the nodes with name 'node' to count
     * @param nameSpaceURI  the nameSpace to look in
     *
     * @return int          the number of matching child elements
     */
    public int numChildElements_KElement(String node, String nameSpaceURI)
    {
        KElement kElem = getFirstChildElement();
        int n = 0;

        while (kElem != null)
        {
            if (kElem.fitsName_KElement(node, nameSpaceURI))
            {
                n++;
            }
            kElem = kElem.getNextSiblingElement();
        }

        return n;
    }

    /**
     * Removes the n'th child node that matches 'nodeName' and 'nameSpaceURI'
     *
     * @param node           name of the child node to remove, if empty;
     *                       or "*" removes the n'th element
     * @param nameSpaceURI   namespace to search in
     * @param  n             number of nodes to skip before deleting
     *
     * @return KElement      the removed element
     */
    public KElement removeChild(String node, String nameSpaceURI, int n)
    {
        KElement kRet = null;
        final KElement kElem = getChildByTagName(node, nameSpaceURI, n, null, true, true);
        if (kElem != null)
        {
            kRet = (KElement) removeChild(kElem);
        }

        return kRet;
    }

    /**
     * Get a child from the actual element by the tag name.
     * <p>
     * default: getChildByTagName(s, null, 0, null, true, true)
     *
     * @param s            elementname you are searching for
     * @param nameSpaceURI nameSpace you are searching for
     * @param index        if more then one child match the condition you can
     *                     specify which one you want to have via the index
     * @param mAttrib      attributes you are lokking for
     * @param bDirect      if true return value is directly the elemement.
     *                     Otherwise the return value is the node of the found
     *                     element. Only direct child, not grandchild etc.
     * @param bAnd         if true, a child is only returned if it has all
     *                     attributes specified in mAttrib
     *
     * @return KElement    the found child (element or node), null if index
     *                     < 0 or index < number of matching children
     */
    public KElement getChildByTagName(String s, String nameSpaceURI, int index, JDFAttributeMap mAttrib,
            boolean bDirect, boolean bAnd)
    {
        final VElement v = getChildrenByTagName(s, nameSpaceURI, mAttrib, bDirect, bAnd, index + 1);
        if ((index >= 0) && (v.size() > index))
        {
            return v.item(index);
        }
        return null;
    }

    /**
     * Remove children that match <code>nodeName</code> and <code>nameSpaceURI</code>
     * <p>
     * default: removeChildren(nodeName, nameSpaceURI, null)
     *
     * @param nodeName      name of the child node to get, if empty or null remove all
     * @param nameSpaceURI  namespace to search in
     *
     * @deprecated use three parameter version removeChildren(nodeName, nameSpaceURI, null);
     */
    @Deprecated
    public void removeChildren(String nodeName, String nameSpaceURI)
    {
        removeChildren(nodeName, nameSpaceURI, null);
    }

    /**
     * Removes the children named <code>nodeName</code> in the namespace
     * <code>nameSpaceURI</code> from the parent element
     * <p>
     * default: removeChildren(null,null,null)
     *
     * @param nodeName      name of the element typ to remove
     * @param nameSpaceURI  namespace in which the elements are to be removed
     */
    public void removeChildren(String nodeName, String nameSpaceURI, JDFAttributeMap mAttrib)
    {
        final VElement v = getChildElementVector(nodeName, nameSpaceURI, mAttrib, true, 0,false);
        final int size = v.size();
        for (int i = 0; i < size; i++)
        {
            removeChild(v.elementAt(i));
        }
    }

    /**
     * Removes the attribute value from its value list <br>
     * Removes the contents of value from the existing attribute key.
     * Deletes the attribute Key, if it has no value.<br>
     * <code>removeFromAttribute("key","next","",",", -1)</code> applied to
     * <code><xml key="first,next"/></code> results in <code><xml key="first"/></code>
     *
     * @param key            attribute key
     * @param value          string to remove
     * @param nameSpaceURI   namespace of attribute key
     * @param sep            separator between the values
     * @param nMax           maximum number of value instances
     *                       to remove (-1 = all)
     *
     * @return int           number of removed instances
     */
    public int removeFromAttribute(String key, String value, String nameSpaceURI, String sep, int nMax)
    {
        final String strAttrValue = getAttribute_KElement(key, nameSpaceURI, null);
        if(strAttrValue==null || strAttrValue.indexOf(value)<0)
            return 0;
        final VString v = StringUtil.tokenize(strAttrValue, sep, false);
        int siz = v.size();

        for(int i=siz-1;i>=0;i--)
        {
            if (v.elementAt(i).equals(value))
            {
                v.removeElementAt(i);
                if(--nMax==0) {
                    break;
                }
            }
        }

        if (v.size() == 0)
        {
            removeAttribute_KElement(key, nameSpaceURI);
            return 0;
        }
        setAttribute(key, StringUtil.setvString(v,sep,null,null), nameSpaceURI);
        return v.size();
    }

    /**
     * Flush - remove all attributes, elements and text, leaving only a stub tag
     *
     * @return boolean  true always
     */
    public boolean flush()
    {
        final VElement list = getChildElementVector(null, null, null, true, 0,false);

        for (int i = list.size() - 1; i >= 0; i--)
        {
            final Node node = list.elementAt(i);
            removeChild(node);
        }

        removeAttributes(null);
        return true;
    }

    /**
     * Get all or the spezified number of childs nodes from the actual element
     * a maxSize of 0 spezifies all children.
     * <p>
     * default: getChildNodeVector(0)
     *
     * @return Vector vector with all found nodes
     * @deprecated
     */
    @Deprecated
    public Vector getChildNodeVector(int maxSize)
    {
        final Vector v = new Vector();
        int i = 0;
        Node node = getFirstChild();

        if (node != null)
        {
            do
            {
                v.add(node); // this guy is ok
                node = node.getNextSibling();
            } while (++i != maxSize && node != null);
        }

        return v;
    }

    /**
     * Removes all attributes spezified in attribs. If attribs is empty,
     * all attributes are removed
     *
     * @param attribs  list of attributes to remove, if empty, remove all
     */
    public void removeAttributes(VString attribs)
    {
        if (attribs==null)
        {
            final NamedNodeMap nm = getAttributes();
            final int siz = (nm == null) ? 0 : nm.getLength();

            for (int i = siz - 1; i >= 0; i--)
            {
                removeAttribute(nm.item(i).getNodeName());
            }
        }
        else
        {
            for (int i = 0; i < attribs.size(); i++)
            {
                removeAttribute(attribs.elementAt(i));
            }
        }

    }
    /**
     * remove all empty attributes from this (e.g. att="")
     * @param bRecurse if true, alse recurse subelements, else only local
     */
    public void eraseEmptyAttributes(boolean bRecurse)
    {
        final NamedNodeMap nm = getAttributes();
        final int siz = (nm == null) ? 0 : nm.getLength();

        for (int i = siz - 1; i >= 0; i--)
        {
            final Node item = nm.item(i);
            if(item.getNodeValue().equals(JDFConstants.EMPTYSTRING)) {
                removeAttribute(item.getNodeName());
            }
        }
        if(bRecurse)
        {
            KElement e=getFirstChildElement();
            while(e!=null)
            {
                e.eraseEmptyAttributes(true);
                e=e.getNextSiblingElement();
            }
        }
    }

    /**
     * remove all default attributes from this i.e. all attributes whose value matches the schema default
     * @param bRecurse if true, alse recurse subelements, else only local
     */
    public void eraseDefaultAttributes(boolean bRecurse)
    {
        final JDFAttributeMap aMap=getDefaultAttributeMap();
        if(aMap!=null)
        {
            final NamedNodeMap nm = getAttributes();
            final int siz = (nm == null) ? 0 : nm.getLength();
            for (int i = siz - 1; i >= 0; i--)
            {
                final Node item = nm.item(i);
                final String attVal = item.getNodeName();
                if(aMap.containsKey(attVal) && item.getNodeValue().equals(aMap.get(attVal))) {
                    removeAttribute(attVal);
                }
            }
        }
        if(bRecurse)
        {
            KElement e=getFirstChildElement();
            while(e!=null)
            {
                e.eraseDefaultAttributes(true);
                e=e.getNextSiblingElement();
            }
        }
    }

    /**
     * You can get the iSkip element named by nodeName. If there is no element
     * present, a new empty element is returned.
     * If iSkip is out of range, a new element is returned.
     * <p>
     * default: getDeepElement(nodeName, null, 0)
     *
     * @param nodeName      the type of element you want to get
     * @param nameSpaceURI  the namespace to search in
     *                      !!! NOT USED IN FUCTION !!!
     * @param iSkip         which element you want to have (order of appearance)
     *
     * @return KElement     the iSkip element or a new element
     *
     * @deprecated use getChildByTagName(nodeName, nameSpaceURI, iSkip, null, false, true);
     */
    @Deprecated
    public KElement getDeepElement(String nodeName, String nameSpaceURI, int iSkip)
    {
        return getChildByTagName(nodeName, nameSpaceURI, iSkip, null, false, true);
    }

    /**
     * @deprecated use getChildFromList(Vector nodeNames, int iSkip, JDFAttributeMap map)
     * @param nodeNames
     * @param iSkip
     * @return KElement
     */
    @Deprecated
    public KElement getChildFromList(VString nodeNames, int iSkip)
    {
        return getChildFromList(nodeNames, iSkip, null, true);
    }
    /**
     * Get any Child that matches a string defined in nodeNames. The method
     * compares the strings with the element name
     * <p>
     * default: getChildFromList(nodeNames, 0, null)
     *
     * @param nodeNames  container for the node name string
     * @param iSkip      how many of the found child should be skiped
     *
     * @return KElement  a child matching the condition
     */
    public KElement getChildFromList(VString nodeNames, int iSkip, JDFAttributeMap map, boolean bDirect)
    {
        int i = 0;
        KElement kElem = getFirstChildElement();
        while (kElem != null)
        {
            if (nodeNames.contains(kElem.getLocalName()))
            {
                if(map==null || kElem.includesAttributes(map,true))
                {
                    if (i++ >= iSkip)
                    {
                        return kElem; // this guy is the one
                    }
                }
            }
            if(!bDirect)
            {
                int j=0;
                KElement e2=null;
                do
                {
                    e2=kElem.getChildFromList(nodeNames,j,map,bDirect);
                    if(e2!=null)
                    {
                        if (i++ >= iSkip) {
                            return e2;
                        }
                        j++;
                    }
                }
                while(e2!=null);
            }
            kElem = kElem.getNextSiblingElement();
        }
        return null;
    }

    /**
     *  Rename the element with the String newName.
     *  Attention. the Java class of the element does not get modified.
     *  It is up to the caller to ensure that no inconsistent types get created with rename
     *  <p>
     *  default: renameElement(newName, null)
     *
     * @param newName       the new name of the actual element
     * @param nameSpaceURI  the new nameSpace, ignored if null
     *
     * @return KElement     the renamed child, i.e. this
     */
    public KElement renameElement(String newName, String nameSpaceURI)
    {
        this.name =newName;
        this.localName=xmlnsLocalName(newName);
        if(nameSpaceURI!=null) {
            this.namespaceURI=nameSpaceURI;
        }
        return this;
    }
    /**
     *remove all elements and attributes of a given namespace
     *@param nsURI the ns uri of the extensions to remove
     *
     */
    public void removeExtensions(String nsURI)
    {
        if(nsURI==null)
            return;
        KElement n=getFirstChildElement();
        while (n!=null)
        {
            KElement next=n.getNextSiblingElement(); // get next prior to zapping
            final String nsuri=n.getNamespaceURI();
            if(nsURI.equals(nsuri))
            {
                removeChild(n);
            }
            else 
            {
                n.removeExtensions(nsURI);
            }

            n=next;
        }
        NamedNodeMap nm=getAttributes();
        int siz=nm==null ? 0 : nm.getLength();
        for(int i=siz-1;i>=0;i--)
        {
            Node na=nm.item(i);
            final String nsuri=na.getNamespaceURI();
            if(nsURI.equals(nsuri))
            {
                removeAttributeNode((Attr)na);
            }
        }
    }

    /**
     * moves this to a position before another child, 
     * fails if either this or beforechild are document roots 
     * 
     * @param beforeChild the child to move before, if beforechild is a the document root, do nothing
     * if null, move me to the end of my parent
     * 
     * @return this after moving, null if failure
     */
    public KElement moveMe(KElement beforeChild)
    {
        KElement parent=beforeChild==null ? getParentNode_KElement() : beforeChild.getParentNode_KElement();
        if(beforeChild==this)
            return this;
        if(parent==null || getParentNode_KElement()==null)
            return null;
        return parent.moveElement(this, beforeChild);
    }
    /**
     * Moves src node (including all attributes and subelements)
     * from its parent node into 'this' and
     * inserts it in front of beforeChild, if it exists.
     * Otherwise appends src to 'this'.<br>
     * If src is <code>null</code>, an empty KElement is returned.<br>
     *
     * src is removed from its parent node.
     * if the actual document owner is the same as the document owner of src,
     * src is appended to 'this'
     * If the documents are different, then src is appended to
     * 'this' in the actual document.
     * <p>
     * default: moveElement(src, null)
     *
     * @param src           node to move.
     * @param beforeChild   child of 'this' to insert src before.
     *                      If beforeChild is null, src is appended to 'this'
     * @return KElement     src element after moving, null if src is null
     *
     * @throws JDFException if beforeChild is not a child of 'this'
     */
    public KElement moveElement(KElement src, KElement beforeChild)
    {
        KElement kRet = null;

        if (src != null)
        {
            if(src.isAncestor(this))
                return null; // snafu when moving a to b in a/b
            final Node parentNode = src.getParentNode();
            Node srcElement = parentNode==null ? src : parentNode.removeChild(src);

            if (beforeChild != null && beforeChild.getParentNode() != this)
            {
                throw new JDFException("KElement.moveElement" + " beforeChild is not child of this");
            }

            if (src.getOwnerDocument() != getOwnerDocument())
            {
                src.clearTargets();
                KElement dest = (KElement)getOwnerDocument().importNode(srcElement, true);
                dest.fixParent(this, dest);
                srcElement = dest;
            }

            if (beforeChild == null)
            {
                kRet = (KElement) appendChild(srcElement);
            }
            else
            {
                kRet = (KElement) insertBefore(srcElement, beforeChild);
            }
        }

        return kRet;
    }

    /**
     * Erases all empty text nodes in 'this' and its subelements
     * If there any empty text nodes removes them.
     * If bTrimWhite is true, then trims white spaces from both
     * ends of a text node and only then, if it is empty, removes it
     *
     * @param bTrimWhite trims whitespace of text, default = true
     * @return int       the number of removed nodes
     */
    public int eraseEmptyNodes(boolean bTrimWhite)
    {
        int nRemove = 0;

        Node n = getFirstChild();
        while (n != null)
        {
            Node nNext=n.getNextSibling(); // must set nNew prior to potentially deleting n...
            final short nodeType = n.getNodeType();

            if (nodeType == Node.TEXT_NODE)
            {
                String s = n.getNodeValue();
                if (bTrimWhite)
                {
                    s = s.trim();
                }
                if (s.equals(JDFConstants.EMPTYSTRING))
                {
                    removeChild(n);
                    nRemove++;
                }
                else if (bTrimWhite)
                { // replace value with cleaned string
                    n.setNodeValue(s);
                }
            }
            else if (nodeType == Node.ELEMENT_NODE)
            {
                final KElement kElem = (KElement) n;
                nRemove += kElem.eraseEmptyNodes(true);
                // 040302 RP do not erase empty elements
                //they may have a sementic meaning
            }
            n = nNext;

        }

        return nRemove;
    }

    /**
     * Copies src node (including all attributes and subelements)
     * and inserts the copy into 'this' in front of beforeChild, if it exists.
     * Otherwise appends src node to 'this'.
     * <p>
     * default: copyElement(src, null)
     *
     * @param src           node to copy.
     * @param beforeChild   child of 'this' to insert src before.
     *                      If null, src is appended
     *
     * @return KElement     the copied element, <code>null</code> if src is <code>null</code>.
     */
    public KElement copyElement(KElement src, KElement beforeChild)
    {
        return (KElement)copyNode(this, src, beforeChild);
    }

    private static Node copyNode(Node parent, Node src, Node beforeChild)
    {
        if (src == null)
            return null;

        Node childNode = null;
        if (src.getOwnerDocument() == parent.getOwnerDocument())
        {
            childNode = src.cloneNode(true);
        }
        else
        {
            childNode = parent.getOwnerDocument().importNode(src, true);
        }

        if (beforeChild != null && beforeChild.getParentNode() != parent)
        {
            throw new JDFException("KElement.copyElement" + " beforeChild is not child of this");
        }
        return parent.insertBefore(childNode, beforeChild);
    }

    /**
     * Replaces 'this' with src. <br>
     * If the actual document is the same as the src document,
     * 'this' is replaced by src.<br>
     * If the actual document and the src document are different,
     * src is positioned at the position of 'this' in the current document
     * and removed from the old parent document.<br>
     *
     * @since 130103 ReplaceElement works on all elements including
     *               the document root
     * @param src    node, that 'this' will be replaced with
     *
     * @return KElement  the replaced element. If src is null or equal
     *                   'this', src is returned.
     */
    public KElement replaceElement(KElement src)
    {
        if(src == this) {
            return this; // nop
        }

        KElement kRet = src;

        if (src != null)
        {
            // workaround for the document element
            //TBD: there must be a better way
            if (getParentNode_KElement() == null)
            {
                kRet = replaceElement_isDocRoot(src);
            }
            else
            {
                final KElement srcParentNode = src.getParentNode_KElement();
                if (srcParentNode != null)
                {
                    //removes the resource from the to merge document
                    src = (KElement)srcParentNode.removeChild(src);
                }

                // this and src are in the same document
                if (src.getOwnerDocument() == getOwnerDocument())
                {
                    getParentNode_KElement().replaceChild(src, this);
                    fixParent(src, null);
                    kRet = src;
                }
                else
                { // import from other document
                    KElement dn = (KElement) getOwnerDocument().importNode(src, true);
                    getParentNode_KElement().replaceChild(dn, this);
                    fixParent(dn, null);
                    kRet = dn;
                }
            }
        }
        this.ownerNode=null; // I've replace this so that it remains an orphan in the document, but has no owner node
        return kRet;
    }

    /*
     *  used in replaceElement
     */
    private KElement replaceElement_isDocRoot(KElement src)
    {
        if (!getNodeName().equals(src.getNodeName()) || !getNamespaceURI().equals(src.getNamespaceURI()))
        {
            renameElement(src.getNodeName(),src.getNamespaceURI());
        }
        flush();
        setAttributes(src.getAttributeMap());
        final VElement children = src.getChildElementVector(null, null, null, true, 0,false);

        for (int iv = 0; iv < children.size(); iv++)
        {
            copyElement(children.elementAt(iv), null);
        }

        return this; // return the original
    }

    //************************** end of methods needed in JDFRefElement ********
    ////////////////////////////////////////////////////////////////////////////
    //************************** start of methods needed in JDFResourceLink ****

    /**
     * Get a long attribute if present
     * <p>
     * default: getLongAttribute(attrib, null, 0)
     *
     * @param attrib        attribute to parse for an integer attribute
     * @param nameSpaceURI  nameSpaceURI to search in
     * @param def           the default to return if the value is empty or
     *                      the attribute is not set
     *
     * @return              the parsed int. If the attribute was not found,
     *                      <code>long def</code> is returned.
     */
    public long getLongAttribute(String attrib, String nameSpaceURI, long def)
    {
        long lRet = def;
        final String s = getAttribute(attrib, nameSpaceURI, null);

        if (s!=null)
        {
            lRet = Long.parseLong(s);
        }
        return lRet;
    }

    /**
     * Get a integer attribute if present
     * <p>
     * default: getIntAttribute(attrib, null, 0)
     *
     * @param attrib       attribute to parse for an integer attribute
     * @param nameSpaceURI nameSpaceURI to search in
     * @param def          the default to return if the value is not set or the
     *                     attribute does not exists
     *
     * @return int          the parsed int. If the attribute was not found
     *                      int def is returned
     */
    public int getIntAttribute(String attrib, String nameSpaceURI, int def)
    {
        final String s = getAttribute(attrib, nameSpaceURI, null);
        return StringUtil.parseInt(s,def);
    }

    /**
     * get a boolean attribute
     * <p>
     * default: getBoolAttribute(attrib, null, false)
     *
     * @param attrib         attribute to parse for a boolean value
     * @param nameSpaceURI   nameSapceURI to search in
     * @param def            the default to return if value is not set or
     *                       the attribute does not exists
     *
     * @return boolean       the boolean value or the def parameter
     */
    public boolean getBoolAttribute(String attrib, String nameSpaceURI, boolean def)
    {
        final String s = getAttribute(attrib, nameSpaceURI, null);
        return StringUtil.parseBoolean(s, def);
    }

    /**
     * get a double attribute
     * <p>
     * default: getRealAttribute(attrib, null, 0.0)
     *
     * @param attrib         attribute to parse for a boolean value
     * @param nameSpaceURI   nameSapceURI to search in
     * @param def            default to return if none value is set or
     *                       attribute does not exist
     *
     * @return double        the double value or <code>def</code>
     */
    public double getRealAttribute(String attrib, String nameSpaceURI, double def)
    {
        final String s = getAttribute(attrib, nameSpaceURI, null);
        return StringUtil.parseDouble(s,def);
    }

    /**
     * Get the Ancestor node with name other than thisNode
     *
     * @param thisNode      serch string
     *
     * @return KElement     parent node element
     */
    public KElement getDeepParentNotName(String thisNode)
    {
        KElement kRet = null;

        if (getLocalName().equals(thisNode))
        {
            final KElement parent = getParentNode_KElement();
            kRet = (parent == null) ? null : parent.getDeepParentNotName(thisNode);
        }
        else
        {
            kRet = this;
        }

        return kRet;
    }

    //************************** end of methods needed in JDFResourceLink ******
    ////////////////////////////////////////////////////////////////////////////
    //************************** start of methods needed in JDFPartAmount ******

    /**
     * Get the first child of parentNode with name parentNode
     *
     * @param  parentNode   node name to search for
     * @return KElement     the matching child of the parent element
     */
    public KElement getDeepParentChild(String parentNode)
    {
        final KElement kElem = getParentNode_KElement();

        if (kElem != null && parentNode!=null)
        {
            if (!kElem.getNodeName().equals(parentNode))
            {
                return kElem.getDeepParentChild(parentNode);
            }
            return this;
        }
        return null;
    }

    //************************** end of methods needed in JDFPartAmount ********
    ////////////////////////////////////////////////////////////////////////////
    //************************** start of methods needed in JDFResponse ********

    /**
     * append a DOM comment <code>&lt;!-- XMLComment --&gt;</code>
     * The double minus sign '--' is escaped with an underscore '_' in order to ensure valid xml
     *
     * @param commentText  the comment to append
     * @deprecated use appendXMLComment(commentText, null);
     */
    @Deprecated
    public void appendXMLComment(String commentText)
    {
        appendXMLComment(commentText, null);
    }

    /**
     * append a DOM comment <code>&lt;!-- XMLComment --&gt;</code>
     * The double minus sign '--' is escaped with an underscore '_' in order to ensure valid xml
     *
     * @param commentText  the comment to append
     * @param beforeChild the child of this that the Comment should be appended before. if null, append the Comment
     */
    public void appendXMLComment(String commentText, KElement beforeChild)
    {
        commentText=StringUtil.replaceString(commentText, "--", "__");
        final Comment newChild = getOwnerDocument().createComment(commentText);
        insertBefore(newChild,beforeChild);
    }
    /**
     * set a DOM comment <code>&lt;!-- XMLComment --&gt;</code> in front of <code>this</code>
     * if an xml Comment node already exists directly in front of <code>this</code>, the previous comment is removed
     *
     * The double minus sign '--' is escaped with an underscore '_' in order to ensure valid xml
     *
     * @param commentText  the comment text to set
     */
    public void setXMLComment(String commentText)
    {
        KElement e=getParentNode_KElement();
        if(e==null)
        {
            commentText=StringUtil.replaceString(commentText, "--", "__");
            final Comment newChild = getOwnerDocument().createComment(commentText);
            getOwnerDocument().insertBefore(newChild,this);
            Node last=newChild.getPreviousSibling();
            if(last!=null && last.getNodeType()==Node.COMMENT_NODE) {
                getOwnerDocument().removeChild(last);
            }
        }
        else
        {
            Node last=getPreviousSibling();
            e.appendXMLComment(commentText, this);
            if(last!=null && last.getNodeType()==Node.COMMENT_NODE) {
                e.removeChild(last);
            }

        }
    }

    /**
     * Appends XML CData section <code>&lt;![CDATA[ CData Section ]]&gt;</code>
     * some character data <br>
     * Appends a new CData section child node to the end of 'this '
     *
     * @param cDataText the text of the XML CData section to append
     */
    public void appendCData(String cDataText)
    {
        final CDATASection newChild = getOwnerDocument().createCDATASection(cDataText);
        appendChild(newChild);
    }

    /**
     * Appends XML CData section <code>&lt;![CDATA[ CData Section ]]&gt;</code>
     * some character data <br>
     * Appends a new CData section child node to the end of 'this '
     *
     * @param cDataElem the element of the XML CData section to append
     */
    public void appendCData(KElement cDataElem)
    {
        final String s=cDataElem.toString();
        final CDATASection newChild = getOwnerDocument().createCDATASection(s);
        appendChild(newChild);
    }
    /**
     * append some generic text
     *
     * @param textName the text to append
     */
    public void appendText(String textName)
    {
        final Text newChild = getOwnerDocument().createTextNode(textName);
        appendChild(newChild);
    }

    /**
     * appends a entity reference to the actual element
     *
     * @param refName the name of the entity reference
     */
    public void appendEntityReference(String refName)
    {
        final EntityReference newChild = getOwnerDocument().createEntityReference(refName);
        appendChild(newChild);
    }

    /**
     * append a text element with text included
     *
     * @param nodeName   node name of the text element
     * @param text       the text to apend
     *
     * @return KElement  the appended text element
     */
    public KElement appendTextElement(String nodeName, String text)
    {
        final KElement kElem = appendElement(nodeName, null);
        kElem.appendText(text);

        return kElem;
    }

    //************************** end of methods needed in JDFResponse **********
    ////////////////////////////////////////////////////////////////////////////
    //************************** start of methods needed in JDFNode ************

    /**
     * merge nodes in a way that no duplicate elements are created<br>
     * <b>attention !! this kills pools !!</b> since elements in kElem
     * overwrite those in *this
     *
     * @param kElem     the node element to merge with the current node
     * @param bDelete   if true KElement kElem will be deleted
     *
     * @return KElement the merged node element
     */
    public KElement mergeElement(KElement kElem, boolean bDelete)
    {
        if(kElem==null)
            return this;
        
        final VElement v = kElem.getChildElementVector(null, null, null, true, 0,false);

        for (int i = 0; i < v.size(); i++)
        {
            final KElement m = v.elementAt(i);
            int nThis        = numChildElements(m.getNodeName(), null);

            if(nThis == 1)
            {
                int nE = kElem.numChildElements(m.getNodeName(), null);
                if(nE==1)
                {
                    getElement(m.getNodeName(), null, 0).mergeElement(m,bDelete);
                    continue; // we've merged the only element and can continue with the next
                }
            }
            // it was impossible to merge, therefore simply copy over
            if (bDelete)
            {
                moveElement(m, null);
            }
            else
            {
                copyElement(m, null);
            }
        }
        setAttributes(kElem);

        return this;
    }

    /**
     * GetChildWithAttribute - Get a child with matching attributes
     * <p>
     * default: getChildWithAttribute(nodeName, attName, null,attValue, 0, true)
     *
     * @param nodeName      name of the child node to search for
     * @param attName       attribute name to search for
     * @param nameSpaceURI  namespace to search for
     * @param attVal        the attribute value to search for,
     *                      Wildcard supported (<code>null</code>)
     * @param index         if more then one child meets the condition, you can
     *                      specify the one to return via an index
     * @param bDirect       if true, looks only in direct children, else search
     *                      through all children, grandchildren etc.
     *
     * @return KElement     the element which matches the above conditions
     */
    public KElement getChildWithAttribute(String nodeName, String attName, String nameSpaceURI, String attVal, int index, boolean bDirect)
    {
        KElement kRet = null;
        XMLDocUserData userData = null;


        final boolean bID = attName.equals(AttributeName.ID);
        if(bID&&!isWildCard(attVal))
        {
            userData = getXMLDocUserData();
            if(userData!=null)
            {
                kRet=userData.getTarget(attVal);
                if(kRet!=null && ((bDirect && kRet.getParentNode_KElement()!=this) || kRet.getOwnerDocument() != getOwnerDocument()))
                {
                    kRet=null; // it is somewhere else, not a child of this!
                }
                if(kRet!=null) {
                    return kRet;
                }
            }
        }

        if(isWildCard(nodeName)) {
            nodeName=null;
        }
        if(isWildCard(nameSpaceURI)) {
            nameSpaceURI=null;
        }
        if(isWildCard(attVal)) {
            attVal=null;
        }

        if (bDirect)
        { // inlined for performance
            KElement e0 = getFirstChildElement();
            if (e0 != null)
            {
                final boolean bAlwaysFit = nodeName==null && nameSpaceURI==null;
                do
                {
                    KElement e=e0;
                    if(e instanceof JDFRefElement)
                    {
                        if(!(this instanceof JDFResourcePool)) // zapp dead loops!
                            e=((JDFRefElement)e0).getTarget();
                    }

                    if(e!=null)
                    {
                        if ((bAlwaysFit || e.fitsName(nodeName, nameSpaceURI))
                                && (e.includesAttribute(attName, attVal)))
                        {
                            kRet = e;
                        }

                        // update ID cache while searching IDs
                        if(bID && userData!=null)
                        {
                            final String idVal=e.getAttribute_KElement(AttributeName.ID,null,null);
                            if(idVal!=null) {
                                userData.setTarget(e,idVal);
                            }
                        }
                    }

                    e0 = e0.getNextSiblingElement();

                } while (e0 != null && (kRet == null)); // loop to end if we are filling the cache
            }
        }
        else
        {
            final JDFAttributeMap m = new JDFAttributeMap(attName, attVal);
            kRet = getChildByTagName(nodeName, nameSpaceURI, index, m, bDirect, true);
        }

        return kRet;
    }

    /**
     * Checks whether the current element has a child element NodeName
     * <p>
     * default: hasChildElement(String nodeName, null)
     *
     * @param nodeName       name of the node to check for
     * @param nameSpaceURI   nameSpaceURI to search in
     *
     * @return boolean       true if there is a child with nodeName,
     *                       otherwise false
     */
    public boolean hasChildElement(String nodeName, String nameSpaceURI)
    {
        return !(getElement(nodeName, nameSpaceURI, 0) == null);
    }

    /**
     * searches for the first child element occurence in this
     * element or any ancestors
     * <p>
     * default: getInheritedElement(elementName, null, 0)
     *
     * @param elementName    name of the element to be searched
     * @param nameSpaceURI   XML-namespace
     * @param iSkip          leading siblings to be skipped
     *
     * @return JDFElement    the element found
     */
    public KElement getInheritedElement(String elementName, String nameSpaceURI, int iSkip)
    {
        KElement kElem = getElement(elementName, nameSpaceURI, iSkip);

        if (kElem == null)
        {
            kElem = getParentNode_KElement();

            if (kElem != null)
            {
                kElem = kElem.getInheritedElement(elementName, nameSpaceURI, iSkip);
            }
        }

        return kElem;
    }

    //************************** end of methods needed in JDFNode **************
    ////////////////////////////////////////////////////////////////////////////
    //************************** start of methods needed in JDFRoot ************

    /**
     * Adds a NameSpace and maps the prefix to a URI. <br>
     * Checks all parents, whether such namespace is already
     * defined in an ancestor
     *
     * @param strPrefix      the namespace prefix to set
     * @param strNameSpaceURI   the namespace URI to set
     * @return boolean       true if newly set, false if already
     *                       there and matching
     */
    public boolean addNameSpace(String strPrefix, String strNameSpaceURI)
    {
        boolean fSuccess = false;

        String strNameSpace = (strPrefix==null || strPrefix.length() <= 0)
        ? JDFConstants.XMLNS
                : JDFConstants.XMLNS + JDFConstants.COLON + strPrefix;

        final String strOldNameSpaceURI = getInheritedAttribute(strNameSpace, null, JDFConstants.EMPTYSTRING);
        final String myNameSpaceURI     = getNamespaceURI();

        if (!strNameSpaceURI.equals(strOldNameSpaceURI) && !strNameSpaceURI.equals(myNameSpaceURI))
        {
            fSuccess = true;
            setAttribute(strNameSpace, strNameSpaceURI, null);
        }

        return fSuccess;
    }

    /**
     * sorts all child elements by alphabet
     *
     */
    public void sortChildren()
    {
        VElement v=getChildElementVector_KElement(null, null, null, true, -1);
        if(v==null || v.size()==0) {
            return;
        }

        String[] vs=new String[v.size()];
        for(int i=0;i<v.size();i++)
        {
            KElement e=v.item(i);
            String s=e.getNodeName();
            String id=e.getAttribute("ID", null, null);
            if(id!=null) {
                s+="<"+id;
            }
            vs[i]=s;
        }
        Arrays.sort(vs);
        for(int i=0;i<vs.length;i++)
        {
            String s=vs[i];
            String id=StringUtil.token(s, 1, "<");
            KElement e= id==null ? getElement(s, null, 0) : getChildWithAttribute(StringUtil.token(s, 0, "<"), "ID", null, id, 0, true);
            moveElement(e, null);
        }
    }
    /**
     * this to string, used for debug purpose mostly
     *
     * @return string representativ of this
     *
     * @see Object#toString()
     */
    @Override
    public String toString()
    {
        String strJdf = JDFConstants.EMPTYSTRING;
        try
        {
            final StringWriter osw = new StringWriter();
            final OutputFormat format = new OutputFormat(getOwnerDocument());

            format.setIndenting(true);
            format.setIndent(2);
            format.setEncoding(sm_strENCODING);

            final XMLSerializer serial = new XMLSerializer(osw, format);
            serial.setNamespaces(true);
            serial.asDOMSerializer();
            serial.serialize(this);

            strJdf = osw.toString();
        }
        catch (IOException e)
        {
            strJdf = "### ERROR while serializing " + getClass().getName() + "(" + e.toString() + ": " + e.getMessage()
            + ")";
        }

        return strJdf;
    }

    /**
     * serialize this to a string
     *
     * @return String the dom element serialized as a string
     *
     * @throws JDFExcepion if an error occurs while serializing
     */
    public String toXML()
    {
        String strXML = JDFConstants.EMPTYSTRING;

        try
        {
            final StringWriter osw = new StringWriter();
            final OutputFormat format = new OutputFormat(getOwnerDocument());

            format.setIndenting(true);
            format.setIndent(2);
            format.setEncoding(sm_strENCODING);

            final XMLSerializer serial = new XMLSerializer(osw, format);
            serial.setNamespaces(true);
            serial.asDOMSerializer();
            serial.serialize(this);

            strXML = osw.toString();
        }
        catch (IOException e)
        {
            throw new JDFException("ERROR while serializing " + getClass().getName() + " element");
        }

        return strXML;
    }

    //************************** end of methods needed in JDFRoot **************
    ////////////////////////////////////////////////////////////////////////////
    //************************** start of methods needed in JDFAncestorPool ****

    /**
     * Rename an attribute in this namespace
     * <p>
     * default: renameAttribute(oldName, newName, null, null)
     * @param oldName           attribute name to move from
     * @param newName           attribute name to move to
     * @param nameSpaceURI      attribute nameSpaceURI to move from
     * @param newNameSpaceURI   attribute nameSpaceURI to move the name to
     */
    public void renameAttribute(String oldName, String newName, String nameSpaceURI, String newNameSpaceURI)
    {
        if (hasAttribute(oldName, nameSpaceURI, false))
        {
            final String strAttValue = getAttribute_KElement(oldName, nameSpaceURI, JDFConstants.EMPTYSTRING);

            setAttribute(newName, strAttValue, newNameSpaceURI);
            removeAttribute(oldName, nameSpaceURI);
        }
    }

    //************************** end of methods needed in JDFAncestorPool ******
    ////////////////////////////////////////////////////////////////////////////
    //************************** start of methods needed in JDFResource ********

    /**
     * Get a vector of all value of the attribute attName in the children
     * of this node
     * <p>
     * default: getChildAttributeList(nodeName, attName, null,
     *                                JDFConstants.WILDCARD, true, true)
     *
     * @param nodeName     element name you are searching for
     * @param attName      attributes you are looking for
     * @param nameSpaceURI nameSpace you are searching for
     * @param bDirect      if true return value is a vector of all found
     *                     elements. Otherwise the returned vector contains
     *                     only the nodes
     * @param bUnique      if you want to make sure, the attribute is unique, set
     *                     this boolean to true. Otherwise attribute attName is
     *                     added to the returned vector
     *
     * @return Vector - vector with attributes
     */
    public Vector getChildAttributeList(String nodeName, String attName, String nameSpaceURI, String attValue,
            boolean bDirect, boolean bUnique)
    {
        final VString v = new VString();
        final VElement vChildren = getChildrenByTagName(nodeName, nameSpaceURI, new JDFAttributeMap(attName, JDFConstants.EMPTYSTRING),
                bDirect, true, 0);

        final boolean bAttWildCard = isWildCard(attValue);

        for (int i = 0; i < vChildren.size(); i++)
        {
            boolean bAddElement = true;
            final KElement kElem = vChildren.elementAt(i);
            final String s = kElem.getAttribute_KElement(attName, nameSpaceURI, JDFConstants.EMPTYSTRING);
            //fill only matching attributes
            if (bAttWildCard || s.equals(attValue))
            {
                if (bUnique && v.contains(s))
                {
                    bAddElement = false;
                }

                if (bAddElement)
                {
                    v.addElement(s);
                }
            }
        }

        return v;
    }

    /**
     * Inserts the Element elementName before the existing Element
     * node beforeChild. If beforeChild is <code>null</code>, insert elementName at the end
     * of the list of children. If elementName is a DocumentFragment object,
     * all of its children are inserted, in the same order, before beforeChild.
     * If the elementName is already in the tree, it is removed first.
     * <p>
     * default: insertBefore(elementName, beforeChild, null)
     *
     * @param elementName   The elementName to insert the element itself
     *                      will be created
     * @param beforeChild   The reference element, i.e., the elemente before
     *                      which the new
     *                      element must be inserted
     * @param nameSpaceURI  The namespace to create elementName in
     *
     * @return KElement     the element being inserted
     */
    public KElement insertBefore(String elementName, Node beforeChild, String nameSpaceURI)
    {
        KElement newChild=createChildFromName(elementName,nameSpaceURI);
        if (newChild != null)
        {
            insertBefore(newChild, beforeChild);
            newChild.init();
        }
        return newChild;
    }

    /**
     * get a vector of all Children that match the strings defined in nodeNames
     *
     * @param  nodeNames    list of node names that fit, both local and qualified node names are checked
     *
     * @return VElement     the found child elements
     */
    public VElement getChildrenFromList(VString nodeNames, JDFAttributeMap map, boolean bDirect, VElement v)
    {
        if(v==null) {
            v = new VElement();
        }
        KElement kElem = getFirstChildElement();

        while (kElem != null)
        {
            if (nodeNames.contains(kElem.getLocalName())|| nodeNames.contains(kElem.getNodeName()))
            {
                if(map==null || kElem.includesAttributes(map,true))
                {
                    v.addElement(kElem);
                }
            }
            if(!bDirect) {
                kElem.getChildrenFromList(nodeNames,map,bDirect,v);
            }
            kElem = kElem.getNextSiblingElement();
        }
        return v;
    }

    //************************** end of methods needed in JDFSurface ***********
    ////////////////////////////////////////////////////////////////////////////
    //************************** start of methods needed in JDFAutoxxx *********

    /**
     * Appends a new child element to the end of 'this',
     * if it's maximum number of the children with defined name and nameSpace
     * doesn't exceed maxAllowed
     * <p>
     * default: AppendElementN(elementName, maxAllowed, null)
     *
     * @param elementName    name of the new child element
     * @param maxAllowed     the maximum number of children with defined name
     *                       and nameSpace, that are allowed for 'this'
     * @param nameSpaceURI   nameSpace of the new child element
     *
     * @return KElement      newly created child element
     *
     * @throws JDFException if more elements with name and namespace
     *         then maxAllowed already exist
     */
    public KElement appendElementN(String elementName, int maxAllowed, String nameSpaceURI)
    {
        if (numChildElements_KElement(elementName, nameSpaceURI) >= maxAllowed)
        {
            throw new JDFException("KElement:appendElementN:" + " too many elements (>" + maxAllowed + ") of type"
                    + nameSpaceURI + JDFConstants.COLON + elementName);
        }

        return appendElement(elementName, nameSpaceURI);
    }

    //************************** end of methods needed in JDFAutoxxx ***********
    ////////////////////////////////////////////////////////////////////////////
    //************************** start of methods needed in misc/testNew *******


    /**
     * Gets the XPath full tree representation of 'this'
     *
     * @return String    the XPath representation of 'this' e.g.
     *                   <code>/root/parent/element</code><br>
     *                   <code>null</code> if parent of this is null
     *                   (e.g. called on rootnode)
     *                   @deprecated use buildXPath(null,true);
     */
    @Deprecated
    public String buildXPath()
    {
        return buildXPath(null,1);
    }
    /**
     * Gets the XPath full tree representation of 'this'
     * @param relativeTo  relative path to which to create an xpath
     * @return String    the XPath representation of 'this' e.g.
     *                   <code>/root/parent/element</code><br>
     *                   <code>null</code> if parent of this is null
     *                   (e.g. called on rootnode)
     *                   @deprecated use buildXPath(relativeTo,true);
     */
    @Deprecated
    public String buildXPath(String relativeTo)
    {
        return buildXPath(relativeTo,1);
    }

    /**
     * Gets the XPath full tree representation of 'this'
     * @param relativeTo  relative path to which to create an xpath
     * @param methCountSiblings, if 1 count siblings, i.e. add '[n]'
     *                           if 0, only specify the path of parents
     *                           if 2 or 3, add [@ID="id"]
     *                           
     * @return String    the XPath representation of 'this' e.g.
     *                   <code>/root/parent/element</code><br>
     *                   <code>null</code> if parent of this is null
     *                   (e.g. called on rootnode)
     */
    public String buildXPath(String relativeTo, int methCountSiblings)
    {
        String path = getNodeName();
        KElement p           = getParentNode_KElement();

        if(methCountSiblings>0)
        {
            if(methCountSiblings==3 && hasAttribute_KElement(AttributeName.ID,null,false))
            {
                path+="[@ID=\""+getAttribute(AttributeName.ID)+"\"]";
            }
            else
            {
                KElement e = (p != null) ? p.getElement(path, null, 0) : null;
                int i = 1;
                while (e != null)
                {
                    if (e.equals(this))
                    {
                        path+="["+Integer.toString(i)+"]";
                        break;
                    }
                    do
                    {
                        e = e.getNextSiblingElement();
                    } while (e != null && !e.fitsName_KElement(path, null));
                    i++;
                }
            }
        }
        path="/"+path;
        if(p!=null) {
            path=p.buildXPath(relativeTo, methCountSiblings)+path;
        }

        if(relativeTo!=null)
        {
            if(path.startsWith(relativeTo))
            {
                path="."+path.substring(relativeTo.length());
                if(path.startsWith(".["))
                {
                    int iB=path.indexOf("]");
                    if(iB>0) {
                        path="."+path.substring(iB+1);
                    }
                }
            }
        }
        return path;
    }

    /**
     * Creates a new child element with defined Name and NameSpace and inserts it
     * in front of the node with a name bForeNode and namespace
     * beforeNameSpaceURI, with index beforePos
     * <p>
     * default: InsertAt(nodeName, beforePos, null, null, null)
     *
     * @param nodeName           name of the new Element
     * @param beforePos          index of beforeNode, i.e if beforePos = 0, put
     *                           it before the first occurrence
     * @param beforeNode         name of the node to put it before, default
     *                           - any name, Wildcard supported
     * @param nameSpaceURI       nameSpace of the new node
     * @param beforeNameSpaceURI nameSpace of the node to put it before,
     *                           default - value of nameSpaceURI
     *
     * @return KElement the newly created element
     *
     * @throws JDFException if 'this' is a null element and thus nothing
     *         can be inserted in it
     */
    public KElement insertAt(String nodeName, int beforePos, String beforeNode, String nameSpaceURI,
            String beforeNameSpaceURI)
    {
        KElement kRet = null;
        final String strBeforeNS = ((beforeNameSpaceURI==null)||beforeNameSpaceURI.equals(JDFConstants.EMPTYSTRING))
        ? nameSpaceURI : beforeNameSpaceURI;
        final KElement kElem = getElement_KElement(beforeNode, strBeforeNS, beforePos);

        if (kElem == null)
        {
            kRet = appendElement(nodeName, nameSpaceURI);
        }
        else
        {
            kRet = insertBefore(nodeName, kElem, nameSpaceURI);
        }

        return kRet;
    }


    //************************** end of methods needed in misc/testNew *********
    ////////////////////////////////////////////////////////////////////////////
    //************************** start of additional methods *******************

    /**
     * Sets an attribute as defined by XPath to value <br>
     *
     * @tbd enhance the subsets of allowed XPaths,
     * now only .,..,/,@ are supported
     *
     * @param path   XPath abbreviated syntax representation of the attribute, e.g.:
     *               <code>parentElement/thisElement@thisAtt</code>
     *               <code>parentElement/thisElement[2]/@thisAtt</code>
     *               <code>parentElement/thisElement[@foo=\"bar\"]/@thisAtt</code>
     * @param value  string to be set as attribute value
     *
     * @throws       JDFException if the defined path is a bad attribute path
     */
    public void setXPathAttribute(String path, String value)
    {
        final int pos = path.lastIndexOf(JDFConstants.AET);
        if (pos == -1)
        {
            throw new JDFException("SetXPathAttribute - bad attribute path: " + path);
        }

        final String att = path.substring(pos + 1);
        final String strAttrPath = path.substring(0, pos);
        KElement kEle = getXPathElement(strAttrPath);
        if(kEle==null) {
            kEle = getCreateXPathElement(strAttrPath);
        }
        kEle.setAttribute(att, value, null);
    }

    /**
     * returns true if the element or attribute described by this XPath exists, else false
     *
     * @param path the XPath to test for
     * @return true if the element described by path exists
     */
    public boolean hasXPathNode(String path)
    {
        String path2=StringUtil.replaceString(path,"[@", "");
        final int pos = path2.indexOf(JDFConstants.AET);
        if (pos >= 0)
        {
            return getXPathAttribute(path,null)!=null;
        }
        return getXPathElement(path)!=null;
    }

    /**
     * Gets an attribute value as defined by XPath
     * namespace prefixes are resolved <br>
     *
     * @tbd enhance the subsets of allowed XPaths,
     *      now only .,..,/,@ are supported
     * TODO fix bug for attribute searches where the att value contains xpath syntax      
     *
     * @param path XPath abbreviated syntax representation of the attribute,
     *              <code>parentElement/thisElement/@thisAtt</code>
     *              <code>parentElement/thisElement[2]/@thisAtt</code>
     *              <code>parentElement[@a=\"b\"]/thisElement[@foo=\"bar\"]/@thisAtt</code>
     * @param def default value if it doesn't exist
     *
     * @return String the String value of the attribute
     *               or null if the xpath element does not exist
     *
     * @throws JDFException if the defined path is a bad attribute path
     * @default getXPathAttribute(path, null);
     */
    public String getXPathAttribute(String path, String def)
    {
        final int pos = path.lastIndexOf(JDFConstants.AET);
        if (pos == -1 || pos>0&&path.charAt(pos-1)=='[' )
        {
            throw new JDFException("GetXPathAttribute - bad attribute path: " + path);
        }
        final KElement kEle = getXPathElement(path.substring(0, pos));
        return kEle == null ? def // xpath element does not exist
                : kEle.getAttribute_KElement(path.substring(pos + 1), null, def);
    }   

    /**
     * Gets an attribute value as defined by XPath
     * namespace prefixes are resolved <br>
     * Attributes are searched for in partitioned resources if appropriate
     *
     * @tbd enhance the subsets of allowed XPaths,
     *      now only .,..,/,@ are supported
     * TODO fix bug for attribute searches where the att value contains xpath syntax      
     *
     * @param path XPath abbreviated syntax representation of the attribute,
     *              <code>parentElement/thisElement/@thisAtt</code>
     *              <code>parentElement/thisElement[2]/@thisAtt</code>
     *              <code>parentElement[@a=\"b\"]/thisElement[@foo=\"bar\"]/@thisAtt</code>
     * @param def default value if it doesn't exist
     *
     * @return String the String value of the attribute
     *               or null if the xpath element does not exist
     *
     * @throws JDFException if the defined path is a bad attribute path
     * @default getXPathAttribute(path, null);
     */
    public String getInheritedXPathAttribute(String path, String def)
    {
        final int pos = path.lastIndexOf(JDFConstants.AET);
        if (pos == -1 || pos>0&&path.charAt(pos-1)=='[' )
        {
            throw new JDFException("GetXPathAttribute - bad attribute path: " + path);
        }
        final KElement kEle = getXPathElement(path.substring(0, pos));
        return kEle == null ? def // xpath element does not exist
                : kEle.getAttribute(path.substring(pos + 1), null, def);
    }

    /**
     * Gets a map of attribute values as defined by XPath
     * namespace prefixes are resolved <br>
     *
     * @tbd enhance the subsets of allowed XPaths,
     *      now only .,..,/,@ are supported
     *
     * @param path XPath abbreviated syntax representation of the attribute,
     *              <code>parentElement/thisElement/@thisAtt</code>
     *              <code>parentElement/thisElement[2]/@thisAtt</code>
     *              <code>parentElement[@a=\"b\"]/thisElement[@foo=\"bar\"]/@thisAtt</code>
     * @param def default value if it doesn't exist
     *
     * @return String the String value of the attribute
     *               or null if the xpath element does not exist
     *
     * @throws JDFException if the defined path is a bad attribute path
     * @default getXPathAttribute(path, null);
     */
    public Map getXPathAttributeMap(String path)
    {
        final int pos = path.lastIndexOf(JDFConstants.AET);
        if (pos == -1)
        {
            throw new JDFException("GetXPathAttribute - bad attribute path: " + path);
        }
        final String attName=path.substring(pos + 1);
        final VElement vEle = getXPathElementVector(path.substring(0, pos),0);
        if(vEle==null) {
            return null;
        }
        LinkedHashMap map=new LinkedHashMap();
        for(int i=0;i<vEle.size();i++)
        {
            KElement e=vEle.item(i);
            String s=e.getAttribute_KElement(attName,null,null);
            if(s!=null)
            {
                map.put(e.buildXPath(null, 1)+"/@"+attName,s);
            }
        }
        return map.size()>0 ? map : null;
    }
//  public Node getXPathNode(String path)
//  {
//  Document doc=getOwnerDocument();

//  XPathEvaluator ev=(XPathEvaluator)doc.getFeature("XPath", null);

//  XPathExpression ex=ev.createExpression(path, ev.createNSResolver(this));
//  return (Node) ex.evaluate(this, XPathResult.ANY_UNORDERED_NODE_TYPE, null);
//  }
    /**
     * gets an element as defined by XPath to value <br>
     *
     *
     * @tbd enhance the subsets of allowed XPaths,
     *      now only .,..,/,@ are supported
     *
     * @param path XPath abbreviated syntax representation of the
     *             attribute, e.g
     *              <code>parentElement/thisElement</code>
     *              <code>parentElement/thisElement[2]</code>
     *              <code>parentElement[@a=\"b\"]/thisElement[@foo=\"bar\"]</code>
     *
     * @return KElement the specified element
     * @throws IllegalArgumentException if path is not supported
     */
    public KElement getXPathElement(String path)
    {
        VElement v= getXPathElementVector(path, 1);
        if(v==null || v.size()<1) {
            return null;
        }
        return v.item(0);
    }

    /**
     * gets an vector of elements element as defined by XPath to value <br>
     *
     *
     * @tbd enhance the subsets of allowed XPaths,
     *      now only .,..,/,@,// are supported
     *
     * @param path XPath abbreviated syntax representation of the
     *             attribute, e.g
     *              <code>parentElement/thisElement</code>
     *              <code>parentElement/thisElement[2]</code>
     *              <code>parentElement[@a=\"b\"]/thisElement[@foo=\"bar\"]</code>
     *
     * @return VElement the vector of matching elements
     *
     * @throws IllegalArgumentException if path is not supported
     */
    public VElement getXPathElementVector(String path, int maxSize)
    {
        return getXPathElementVectorInternal(path, maxSize, true);
    }

    /**
     * gets an vector of elements element as defined by XPath to value <br>
     *
     *
     * @tbd enhance the subsets of allowed XPaths,
     *      now only .,..,/,@,// are supported
     *
     * @param path XPath abbreviated syntax representation of the
     *             attribute, e.g
     *              <code>parentElement/thisElement</code>
     *              <code>parentElement/thisElement[2]</code>
     *              <code>parentElement[@a=\"b\"]/thisElement[@foo=\"bar\"]</code>
     *
     * @return VElement the vector of matching elements
     *
     * @throws IllegalArgumentException if path is not supported
     */
    private VElement getXPathElementVectorInternal(String path, int maxSize, boolean bLocal)
    {
        if (path == null) {
            return null;
        }

        VElement vRet=new VElement();
        if (JDFConstants.EMPTYSTRING.equals(path))
        {
            if(bLocal)
                vRet.add(this);
            else
                vRet=getChildrenByTagName(null, null, null, false, true, 0);
            return vRet;
        }
        if (path.startsWith(JDFConstants.SLASH))
        {
            if(path.startsWith("//"))
            {
                return getDocRoot().getXPathElementVectorInternal(path.substring(2), maxSize, false);
            }
            KElement r=getDocRoot();
            final String rootNodeName = r.getNodeName();
            int nextPos = path.indexOf("/", 2);
            final String rootPath = nextPos>0 ? path.substring( 1,nextPos) : path.substring( 1);
            final String nextPath = nextPos>0 ? path.substring( nextPos+1) : "";
            if (rootPath.equals(rootNodeName) || isWildCard(rootPath)) 
            {
                return r.getXPathElementVectorInternal(nextPath,maxSize,true);
            }
            throw new IllegalArgumentException("Invalid root node name");

        }
        else if (path.startsWith(JDFConstants.DOT))
        {
            if (path.startsWith(JDFConstants.DOTSLASH)) {
                return getXPathElementVectorInternal(path.substring(JDFConstants.DOTSLASH.length()),maxSize,true);
            }
            if (path.startsWith(JDFConstants.DOTDOTSLASH))
            {
                final KElement parent = getParentNode_KElement();
                if(parent==null) {
                    return null;
                }
                return parent.getXPathElementVectorInternal(path.substring(JDFConstants.DOTDOTSLASH.length()),maxSize,true);
            }
            else if (path.equals(JDFConstants.DOT))
            {
                vRet.add(this);
                return vRet;
            }
            else if (path.equals(".."))
            {
                KElement parent=getParentNode_KElement();
                if(parent==null) {
                    return null;
                }
                vRet.add(parent);
                return vRet;
            }
        }

        path=StringUtil.replaceString(path, "[@", "|||");
        int posB0 = path.indexOf("[");
        int posBAt=path.indexOf("|||");
        int iSkip = 0;
        String newPath = path;
        int pos = newPath.indexOf(JDFConstants.SLASH);
        JDFAttributeMap map=null;
        boolean bExplicitSkip=false;

        if (posB0 != -1 && (posB0<pos || pos==-1)) // parse for [n]
        {
            int posB1 = path.indexOf("]");

            //TODO fix escape attribute values

            String n = path.substring(posB0 + 1, posB1);
            iSkip = StringUtil.parseInt(n, 0);
            if(iSkip<=0)
                throw new IllegalArgumentException("getXPathVector: bad index:"+iSkip);
            iSkip--;
            bExplicitSkip=true;
            newPath = path.substring(0, posB0) + path.substring(posB1 + 1);
            pos = newPath.indexOf(JDFConstants.SLASH);
        }
        else  if (posBAt != -1 && (posBAt<pos || pos==-1)) // parse for [@a="b"]
        {
            int posB1 = path.indexOf("]");
            map = getXPathAtMap(path, posBAt, posB1);
            newPath = path.substring(0, posBAt) + path.substring(posB1 + 1);
            pos = newPath.indexOf(JDFConstants.SLASH);
        }

        if (pos != -1) // have another element
        {
            final String elmName = newPath.substring(0, pos);
            VElement ve;
            if(bLocal)
            {
                ve = getChildElementVector_KElement(elmName, null, map, true,0);
            }
            else
            {
                ve = getElementsByTagName_KElement(elmName, null);
                if(getLocalName().equals(elmName) || isWildCard(newPath))
                    ve.add(this);
            }
            if(ve==null || ve.size()<=iSkip) {
                return null;
            }
            int iFirst=bExplicitSkip ? iSkip : 0;
            int iLast=bExplicitSkip ? iSkip+1 : ve.size();
            for(int i=iFirst;i<iLast;i++) // loop in case multiple elements contain the same attribute
            {
                VElement eRet=null;
                KElement ee=ve.item(i);
                if (ee != null) {
                    eRet= ee.getXPathElementVectorInternal(newPath.substring(pos + 1),maxSize,true);
                }
                if(eRet!=null) {
                    vRet.addAll(eRet);
                }
            }
            return vRet.size()>0 ? vRet : null;
        }
        // last element
        if(bExplicitSkip)
        {
            KElement e=getChildByTagName(newPath, null, iSkip, map, true, true);
            if(e==null) {
                return null;
            }
            vRet.add(e);
            return vRet;
        }
        if(bLocal)
        {
            vRet =  getChildElementVector_KElement(newPath, null, map, true,0);
        }
        else
        {
            vRet = getElementsByTagName_KElement(newPath, null);
            if(getLocalName().equals(newPath) || isWildCard(newPath))
                vRet.add(this);
        }
        return vRet;
    }

    private JDFAttributeMap getXPathAtMap(String path, int posBAt, int posB1)
    {
        JDFAttributeMap map = new JDFAttributeMap();
        String attEqVal = path.substring(posBAt + 3, posB1);
        //TODO multiple attributes, maybe tokenize by ","
        String attName=StringUtil.token(attEqVal, 0, "=");
        String attVal=attEqVal.substring(attName.length()+2,attEqVal.length()-1);
        map.put(attName,attVal);
        return map;
    }

    /**
     * gets an element as defined by XPath to value and creates it if it does
     * not exist <br>
     * @tbd enhance the subsets of allowed XPaths,
     * now only .,..,/,@ are supported
     *
     * @param path XPath abbreviated syntax representation of the attribute,
     *              <code>parentElement/thisElement</code>
     *              <code>parentElement/thisElement[2]</code>
     *              <code>parentElement[@a=\"b\"]/thisElement[@foo=\"bar\"]</code>
     *
     * @return KElement the specified element
     */
    public KElement getCreateXPathElement(String path)
    {
        if(path==null || path.length()==0) {
            return this;
        }
        KElement e=getXPathElement(path);
        if(e!=null) {
            return e;
        }
        int slash=path.indexOf("/");
        if(slash>0)
        {
            String next=path.substring(0,slash);
            e=getXPathElement(next);
            if(e!=null)
            {
                next=path.substring(slash+1);
                return e.getCreateXPathElement(next);
            }
        }

        if (path.startsWith(JDFConstants.SLASH))
        {
            KElement r=getDocRoot();
            int nextPos=path.indexOf(JDFConstants.SLASH,2);
            if(!path.substring(1,nextPos).equals(r.getNodeName())) {
                throw new JDFException("GetCreateXPathElement:: invalid path: "+path);
            }

            if(nextPos==-1) {
                return this;
            }

            return r.getCreateXPathElement(path.substring(nextPos+1));
        }
        if(path.startsWith(JDFConstants.DOT)){
            if(path.startsWith(JDFConstants.DOTSLASH)) {
                return getCreateXPathElement(path.substring(2));
            }
            if(path.startsWith(JDFConstants.DOTDOTSLASH)) {
                return getParentNode_KElement().getCreateXPathElement(path.substring(3));
            }
            if (path.equals(JDFConstants.DOT)) {
                return this;
            }
            if (path.equals("..")) {
                return getParentNode_KElement();
            }
        }
        int posB0=path.indexOf("[");
        int iSkip=0;
        String newPath=path;
        int pos=newPath.indexOf(JDFConstants.SLASH);
        if(posB0!=-1 && (posB0<pos || pos==-1)){
            int posB1=path.indexOf("]");
            final String siSkip=path.substring(posB0+1,posB1);
            if(!StringUtil.isInteger(siSkip)) {
                throw new IllegalArgumentException("GetCreateXPath: illegal path:"+path);
            }
            iSkip=StringUtil.parseInt(siSkip, 1);
            iSkip--;
            newPath=path.substring(0,posB0)+path.substring(posB1+1);
            pos=newPath.indexOf(JDFConstants.SLASH);
        }
        if(pos!=-1){
            int n=numChildElements(newPath.substring(0,pos),null);
            for(int i=n;i<iSkip;i++) {
                appendElement(newPath.substring(0,pos),null);
            }
            e=getCreateElement(newPath.substring(0,pos),null,iSkip);
            return e.getCreateXPathElement(newPath.substring(pos+1));
        }
        int n=numChildElements(newPath,null);
        for(int i=n;i<iSkip;i++) {
            appendElement(newPath,null);
        }
        return getCreateElement(newPath,null,iSkip);
    }



    /**
     * checks if the current element has attributes
     *
     * @return boolean true if at least one attribute is present
     */
    @Override
    public boolean hasAttributes()
    {
        final NamedNodeMap nm = getAttributes();
        final int l = (nm == null) ? 0 : nm.getLength();

        return l != 0;
    }

    /**
     * checks wether this has node childs of the stated node type
     *
     * @param int nodeType
     * <blockquote><ul>
     * <li>ELEMENT_NODE                = 1
     * <li>ATTRIBUTE_NODE              = 2
     * <li>TEXT_NODE                   = 3
     * <li>CDATA_SECTION_NODE          = 4
     * <li>ENTITY_REFERENCE_NODE       = 5
     * <li>ENTITY_NODE                 = 6
     * <li>PROCESSING_INSTRUCTION_NODE = 7
     * <li>COMMENT_NODE                = 8
     * <li>DOCUMENT_NODE               = 9
     * <li>DOCUMENT_TYPE_NODE          = 10
     * <li>DOCUMENT_FRAGMENT_NODE      = 11
     * <li>NOTATION_NODE               = 12
     * <li>XML_DECL_NODE               = 13
     * </blockquote></ul>
     *
     * @return boolean true if there is at least one child of the stated node
     * type, false otherwise
     */
    protected boolean hasChildNodes(int nodeType)
    {
        boolean bRet = false;
        Node node = getFirstChild();

        while (node != null && bRet == false)
        {
            if (node.getNodeType() == nodeType)
            {
                bRet = true;
            }
            else
            {
                node = node.getNextSibling();
            }
        }

        return bRet;
    }

    /**
     * checks if the current element has a child element
     *
     * @return boolean - true if there is one or more child elements present
     */
    public boolean hasChildElements()
    {
        return hasChildNodes(Node.ELEMENT_NODE);
    }

    /**
     * Get children from the actual element by the tag name, nameSpaceURI
     * or attribute map.
     * GetTree only follows direct links, e.g. as in a JDF tree.
     * Hidden nodes that are children of non-matching nodes are ignored
     *
     * @param nodeName elementname you are searching for
     * @param nameSpaceURI nameSpace you are searching for
     * @param mAttrib attributes you are looking for. Wildcards in the attribute
     *        map are supported
     * @param bDirect if true return value is a vector of all direct elements.
     *        Otherwise the returned vector contains nodes of arbitrary depth
     * @param bAnd if true, a child is only added if it has all attributes
     *        specified in Attributes mAttrib
     *
     * @return VElement vector with all found elements
     */
    public VElement getTree(String nodeName, String nameSpaceURI, JDFAttributeMap mAttrib, boolean bDirect, boolean bAnd)
    {
        final VElement v = new VElement();
        KElement e = getFirstChildElement();
        final boolean bHasNoMap = mAttrib==null || mAttrib.isEmpty();
        final boolean bAlwaysFit = KElement.isWildCard(nodeName) && KElement.isWildCard(nameSpaceURI);

        while (e != null)
        {
            if (bAlwaysFit || e.fitsName_KElement(nodeName, nameSpaceURI))
            {
                if (bHasNoMap || e.includesAttributes(mAttrib, bAnd))
                {
                    // this guy is the one
                    v.add(e);
                    if (!bDirect)
                    { // if not direct, recurse
                        final VElement vv = e.getTree(nodeName, nameSpaceURI, mAttrib, bDirect, bAnd);
                        v.addAll(vv);
                    }
                }
            }
            e = e.getNextSiblingElement();
        }

        return v;
    }

    /**
     * Get a vector of direct child element names that exist but are
     * unknown in this element.
     *
     * @return a <code>vString</code> that contains missing element keys
     */
    public VString getInsertElements()
    {
        VString vKnownElements = knownElements();
        VString vUniqueElements = uniqueElements();
        VString vStrRet = getInsertElementVector(vKnownElements, vUniqueElements);
        return vStrRet;
    }

    /**
     * get a <code>vString</code> vector of direct child element names that may
     * be inserted in this element. This means that a element which is already
     * present as a child and has a max occurs of 1 will not be part of the
     * returned <code>vString</code>
     *
     * @param vKnownKeys a <code>vString</code> list of known element tag names.
     * If you want a complete list of all known Elements use
     * <code>KnownElements()</code> from KElement to get a list.
     * Or call <code>GetInsertElements(int nMax)</code>
     *
     * @param vUnique <code>vString</code> a list of elements that may occur
     * only once. Use UniqueElements() to get a <code>String</code> which
     * contains all valid unique Elements from this.
     *
     * @return VString a vector of strings that contains insertable element keys
     *
     * @see #knownElements()
     * @see #getInsertElements()
     * @see #uniqueElements()
     */
    public VString getInsertElementVector(VString vKnownKeys, VString vUnique)
    {
        final VString vAtts = new VString(getElementNameVector());
        final VString vInsert = vKnownKeys;

        for (int i = 0; i < vAtts.size(); i++)
        {
            if (vUnique.contains(vAtts.elementAt(i)))
            {
                vInsert.removeElement(vAtts.elementAt(i));
            }
        }

        return vInsert;
    }

    /**
     * Get child from the actual element by the tag name, nameSpaceURI
     * or attribute map. GetTree only follows direct links, e.g. as in
     * a JDF tree. Hidden nodes that are children of non-matching
     * nodes are ignored
     *
     * @param nodeName      elementname you are searching for.<br>
     *        Required, no default.
     *
     * @param nameSpaceURI  nameSpace you are searching for.<br>
     *        Default is <code>null</code>
     *
     * @param mAttrib       attributes you are looking for <br>
     *        Wildcards in the attribute map are supported.
     *        Default is an empty Map
     *
     * @param bDirect      if true, return value is a vector of all direct elements.<br>
     *        Otherwise the returned vector contains nodes of arbitrary depth. <br>
     *        Default is false.
     *
     * @param bAnd if true, a child is only added if it has all attributes
     *        specified in Attributes mAttrib.<br>
     *        Default is true.
     *
     * @return KElement the first found element
     */
    public KElement getTreeElement(String nodeName, String nameSpaceURI, JDFAttributeMap mAttrib, boolean bDirect, boolean bAnd)
    {
        if(!bDirect && (mAttrib==null || includesAttributes(mAttrib, bAnd)))
        {
            return this;
        }
        KElement e = getFirstChildElement();
        final boolean bAlwaysFit = isWildCard(nodeName) && isWildCard(nameSpaceURI);

        while (e != null)
        {
            if (bAlwaysFit || e.fitsName_KElement(nodeName, nameSpaceURI))
            {
                if (bDirect && (mAttrib==null || e.includesAttributes(mAttrib, bAnd)))
                {
                    // this guy is the one
                    return e;
                }
                else if (!bDirect)
                { // if not direct, recurse
                    final KElement ee = e.getTreeElement(nodeName, nameSpaceURI, mAttrib, bDirect, bAnd);
                    if (ee != null)
                    {
                        return ee;
                    }
                }
            }
            e = e.getNextSiblingElement();
        }
        // nothing found
        return null;
    }

    /**
     * gets the i'th text child node of 'this'
     *
     * @param i index of the child text node, you are searching for
     *
     * @return String the text if the i'th text node.
     *         <code>null</code> if the i'th text node does not exists
     */
    public String getText(int i)
    {
        final Node n = getChildNode(Node.TEXT_NODE, i);
        return (n == null) ? null : n.getNodeValue();
    }

    /**
     * Gets of 'this' the text of the i'th XML CData section.
     * This section may also contain &lt; and &gt;.
     *
     * @param i index of the CData section child node, you are searching for
     *
     * @return String content of the i'th XML CData section
     */
    public String getCData(int i)
    {
        final Node n = getChildNode(Node.CDATA_SECTION_NODE, i);
        return (n == null) ? null : n.getNodeValue();
    }

    /**
     * gets of 'this' the text of the i-th child XMLComment.
     * <code><!-- this is a XMLComment --></code> would return
     * <code>this is a XMLComment</code>
     *
     * @param i index of the XMLComment child node, you are searching for
     *
     * @return String text of the i-th XMLComment, null if i is higher
     *         then the number of child nodes
     */
    public String getXMLComment(int i)
    {
        final Node n = getChildNode(Node.COMMENT_NODE, i);
        return (n == null) ? null : n.getNodeValue();
    }

    /**
     * Checks, if this has any missing attributes
     *
     * @return boolean true, if one or more attributes are missing
     */
    public boolean hasMissingAttributes()
    {
        return getMissingAttributes(1).size() > 0;
    }

    /**
     * Checks, if this has are any unknown attributes
     *
     * @param bIgnorePrivate if true, looks only in default and JDF namespaces
     *
     * @return boolean true, if one or more attributes are unknown
     */
    public boolean hasUnknownAttributes(boolean bIgnorePrivate)
    {
        return getUnknownAttributes(bIgnorePrivate, 1).size() > 0;
    }

    /**
     * Tests, whether 'this' has any missing direct child elements
     *
     * @return boolean true, if one or more direct child elements are missing
     */
    public boolean hasMissingElements()
    {
        return getMissingElements(1).size() > 0;
    }

    /**
     * Tests, whether 'this' has any unknown direct child elements
     *
     * @param  bIgnorePrivate if true, only looks in default and JDF namespaces
     * @return boolean true if there are any unknown elements
     *         (in respect to the parameter)
     */
    public boolean hasUnknownElements(boolean bIgnorePrivate)
    {
        return getUnknownElements(bIgnorePrivate, 1).size() > 0;
    }

    /**
     * copy an attribute from src to this
     * <p>
     * default: copyAttribute(attrib, src, null, null, null)
     *
     * @param attrib          the name of the attribute to copy
     *                        (if source attribute is different only the
     *                        value will be copied)
     * @param src             source element where the attribute to be
     *                        copied resides
     * @param srcAttrib       attribute to copy, defaults to the value of attrib
     * @param nameSpaceURI    of the attribute in the destination
     * @param srcNameSpaceURI of the attribute in the source, defaults to the
     *                        value of nameSpaceURI
     * @default copyAttribute(attrib,src,null,null,null);
     */
    public void copyAttribute(String attrib, KElement src, String srcAttrib, String nameSpaceURI, String srcNameSpaceURI)
    {
        final String strSrcAttrib = (srcAttrib==null)       ||srcAttrib.equals(JDFConstants.EMPTYSTRING) ? attrib : srcAttrib;
        final String strNameSpace = (srcNameSpaceURI==null) ||srcNameSpaceURI.equals(JDFConstants.EMPTYSTRING) ? nameSpaceURI : srcNameSpaceURI;
        if(strNameSpace!=null && KElement.xmlnsPrefix(attrib)==null)
        {
            Attr an=src.getDOMAttr(strSrcAttrib, srcNameSpaceURI,false);
            if(an!=null)
            {
                String pre=an.getPrefix();
                if(!isWildCard(pre))
                    attrib=pre+":"+attrib;

            }
        }
        setAttribute(attrib, src.getAttribute_KElement(strSrcAttrib, srcNameSpaceURI, null), strNameSpace);
    }
    /**
     * copy an attribute from src to this - shorthand if no renaming or namespace handling is necessary
     * <p>
     * default: copyAttribute(attrib, src, null, null, null)
     *
     * @param attrib          the name of the attribute to copy
     *                        (if source attribute is different only the
     *                        value will be copied)
     * @param src             source element where the attribute to be
     *                        copied resides
     */
    public void copyAttribute(String attrib, KElement src)
    {

        copyAttribute(attrib,src,null,null,null);
    }
    /**
     *  moves an attribute from src to this, the attribute will be removed
     *  from src and moved to this.
     *  <p>
     *  default: moveAttribute(attrib, src, null, null, null)
     *
     * @param attrib       where to move the attribute
     * @param src          element to move from
     * @param srcAttrib    the attribute to move. If empty string, the string
     *                     attrib is used as source and target
     * @param nameSpaceURI the namespaceURI to search in
     */
    public void moveAttribute(String attrib, KElement src, String srcAttrib, String nameSpaceURI, String srcNameSpaceURI)
    {
        final String strSrcAttrib = (srcAttrib==null)|| srcAttrib.equals(JDFConstants.EMPTYSTRING)  ? attrib : srcAttrib;
        final String strNameSpace = (srcNameSpaceURI==null)|| srcNameSpaceURI.equals(JDFConstants.EMPTYSTRING) ? nameSpaceURI : srcNameSpaceURI;
        if(xmlnsPrefix(attrib)!=null && nameSpaceURI==null)
        {
            boolean b=src.hasAttribute(strSrcAttrib, strNameSpace,false);
            if(b)
            {
                final Attr attr=src.getDOMAttr(strSrcAttrib, strNameSpace, true);
                if(attr!=null)
                {
                    nameSpaceURI=attr.getNamespaceURI();
                }
            }
        }
        final String attribute = src.getAttribute(strSrcAttrib, strNameSpace, null);
        setAttribute(attrib, attribute);
        if(attribute!=null) 
        {
            src.removeAttribute(strSrcAttrib, strNameSpace);
        }
    }

    /**
     * Tests, whether 'this' contains any text child nodes
     *
     * @return boolean true, if there are one or more text child nodes
     */
    public boolean hasChildText()
    {
        return hasChildNodes(Node.TEXT_NODE);
    }

    /**
     * gets a concatenated string representing of all direct text child nodes
     *
     * @return String the concatenated values of all direct text child nodes
     *         empty string if no child nodes exist
     */
    public String getText()
    {
        int iBufferSize = 100;
        final StringBuffer strBuff = new StringBuffer(iBufferSize);

        final NodeList nodeList = getChildNodes();
        for (int i = 0; i < nodeList.getLength(); i++)
        {
            Node node = nodeList.item(i);
            if (node.getNodeType() == TEXT_NODE)
            {
                strBuff.append(node.getNodeValue());
            }
        }

        return strBuff.length() <= 0 ? null : strBuff.toString();
    }

    /**
     * removes all text children of the current node
     */
    public void removeAllText()
    {
        final NodeList allNodes = getChildNodes();

        for (int i = 0; i < allNodes.getLength(); i++)
        {
            final Node node = allNodes.item(i);

            if (node.getNodeType() == Node.TEXT_NODE)
            {
                removeChild(node);
            }
        }
    }

    /**
     * Moves 'this' from parent to grandparent or to the closest ancestor
     * with name newParentName
     *
     * @param newParentName name of the parent to recursively search, defaults
     *        to any name
     *
     * @return this, null if failed (e.g. no parentNode found)
     */
    public KElement pushUp(String newParentName)
    {
        KElement kEle = null;
        Node parent = getParentNode();

        if (parent != null)
        {
            do
            {
                parent = parent.getParentNode();
            } while (parent != null && newParentName!=null && !newParentName.equals(JDFConstants.EMPTYSTRING)
                    && !parent.getNodeName().equals(newParentName));

            if (parent != null)
            {
                kEle = ((KElement) parent).moveElement(this, null);
            }
        }

        return kEle;
    }
    /**
     * count the number of child nodes of DOM nodeType nodeType (0=count all)
     * @param nodeType DOM nodeType
     * <blockquote><ul>
     * <li>count all                   = 0
     * <li>ELEMENT_NODE                = 1
     * <li>ATTRIBUTE_NODE              = 2
     * <li>TEXT_NODE                   = 3
     * <li>CDATA_SECTION_NODE          = 4
     * <li>ENTITY_REFERENCE_NODE       = 5
     * <li>ENTITY_NODE                 = 6
     * <li>PROCESSING_INSTRUCTION_NODE = 7
     * <li>COMMENT_NODE                = 8
     * <li>DOCUMENT_NODE               = 9
     * <li>DOCUMENT_TYPE_NODE          = 10
     * <li>DOCUMENT_FRAGMENT_NODE      = 11
     * <li>NOTATION_NODE               = 12
     * <li>XML_DECL_NODE               = 13
     * </blockquote></ul>
     * @return number of child nodes with "nodeType"
     */
    public int numChildNodes(int nodeType)
    {
        int i=0;
        Node n=getFirstChild();
        while(n!=null){
            if(nodeType==0 || n.getNodeType()==nodeType) {
                i++;
            }
            n=n.getNextSibling();
        }
        return i;
    }

    /**
     * removes the i'th child node, that match NodeType
     *
     * @param nodeType the DOM NodeType,if 0 count all nodes
     * <blockquote><ul>
     * <li>ELEMENT_NODE                = 1
     * <li>ATTRIBUTE_NODE              = 2
     * <li>TEXT_NODE                   = 3
     * <li>CDATA_SECTION_NODE          = 4
     * <li>ENTITY_REFERENCE_NODE       = 5
     * <li>ENTITY_NODE                 = 6
     * <li>PROCESSING_INSTRUCTION_NODE = 7
     * <li>COMMENT_NODE                = 8
     * <li>DOCUMENT_NODE               = 9
     * <li>DOCUMENT_TYPE_NODE          = 10
     * <li>DOCUMENT_FRAGMENT_NODE      = 11
     * <li>NOTATION_NODE               = 12
     * <li>XML_DECL_NODE               = 13
     * </blockquote></ul>
     * @param i index of the child nodes to remove
     *
     * @return true if success, false if failed (no i'th node of nodeType found)
     */
    public boolean removeChildNode(int nodeType, int i)
    {
        boolean bRemoved = false;
        final Node n = getChildNode(nodeType, i);

        if (n != null)
        {
            removeChild(n);
            bRemoved = true;
        }

        return bRemoved;
    }

    /**
     * Removes the i'th text node of 'this'
     *
     * @param i  index of the text node to remove. First node has index 0,
     *           second 1, etc.
     */
    public void removeChildText(int i)
    {
        removeChildNode(Node.TEXT_NODE, i);
    }

    /**
     * Removes the i'th XML CData section
     *
     * @param i index of the CData section child node to remove
     */
    public void removeCData(int i)
    {
        removeChildNode(Node.CDATA_SECTION_NODE, i);
    }

    /**
     * removes the i'th XMLComment node
     *
     * @param i index of the XMLComment node to remove
     */
    public void removeXMLComment(int i)
    {
        removeChildNode(Node.COMMENT_NODE, i);
    }

    /**
     * Gets the number of direct child nodes of 'this', that match NodeType
     *
     * @param nodeType the DOM NodeType, if 0 count all nodes
     * <blockquote><ul>
     *    <li>ELEMENT_NODE                = 1
     *    <li>ATTRIBUTE_NODE              = 2
     *    <li>TEXT_NODE                   = 3
     *    <li>CDATA_SECTION_NODE          = 4
     *    <li>ENTITY_REFERENCE_NODE       = 5
     *    <li>ENTITY_NODE                 = 6
     *    <li>PROCESSING_INSTRUCTION_NODE = 7
     *    <li>COMMENT_NODE                = 8
     *    <li>DOCUMENT_NODE               = 9
     *    <li>DOCUMENT_TYPE_NODE          = 10
     *    <li>DOCUMENT_FRAGMENT_NODE      = 11
     *    <li>NOTATION_NODE               = 12
     *    <li>XML_DECL_NODE               = 13
     * </blockquote></ul>
     * @return int: the counted number of direct child nodes, that match NodeType
     */
    public int getNumChildNodes(int nodeType)
    {
        int n = 0;

        final NodeList nodeList = getChildNodes();
        for (int i = 0; i < nodeList.getLength(); i++)
        {
            if (nodeList.item(i).getNodeType() == nodeType)
            {
                n++;
            }
        }

        return n;
    }

    /**
     * Gets the number of direct text child nodes
     *
     * @return int: the number of direct text child nodes in 'this'
     */
    public int getNumChildText()
    {
        return getNumChildNodes(Node.TEXT_NODE);
    }

    /**
     * gets the number of direct child CData sections
     *
     * @return int: the number of direct child CData sections
     */
    public int getNumCDatas()
    {
        return getNumChildNodes(Node.CDATA_SECTION_NODE);
    }

    /**
     * gets the number of direct XMLComment child nodes of 'this'
     *
     * @return int: the number of direct XMLComment child  nodes
     */
    public int getNumXMLComments()
    {
        return getNumChildNodes(Node.COMMENT_NODE);
    }

    /**
     * gets the n'th child node of nodetype <code>nodeType</code> with n = iPos
     *
     * @param int nodeType: the DOM node type to get
     * <blockquote><ul>
     *    <li>ELEMENT_NODE                = 1
     *    <li>ATTRIBUTE_NODE              = 2
     *    <li>TEXT_NODE                   = 3
     *    <li>CDATA_SECTION_NODE          = 4
     *    <li>ENTITY_REFERENCE_NODE       = 5
     *    <li>ENTITY_NODE                 = 6
     *    <li>PROCESSING_INSTRUCTION_NODE = 7
     *    <li>COMMENT_NODE                = 8
     *    <li>DOCUMENT_NODE               = 9
     *    <li>DOCUMENT_TYPE_NODE          = 10
     *    <li>DOCUMENT_FRAGMENT_NODE      = 11
     *    <li>NOTATION_NODE               = 12
     *    <li>XML_DECL_NODE               = 13
     * </blockquote></ul>
     *
     * @param int iPos:  the index of the node with default 0
     *                   for the first occurance
     * @return KElement: a node that matches the filter, null if iPos is
     *                   higher then the number of child nodes
     */
    protected Node getChildNode(int nodeType, int iPos)
    {
        Node retNode = null;
        Node node = getFirstChild();
        int i = 0;

        //iPos + 1 because we need to stop after the iPos
        //turn and i will then be iPos + 1
        while ((node != null) && (i != iPos + 1))
        {
            if (node.getNodeType() == nodeType)
            {
                if (i++ == iPos)
                {
                    retNode = node;
                }
            }
            node = node.getNextSibling();
        }

        return retNode;
    }

    /**
     * get the namespace prefix from a qualified name.
     * For example, nodename <code>exp:myexampley</code>
     * would return <code>exp</code>
     * @deprecated use xmlnsPrefix()
     *
     * @param s the qualified name
     *
     * @return namespace of the qualified name
     */
    @Deprecated
    public static String xmlNameSpace(String s)
    {

        return xmlnsPrefix(s);
    }

    /**
     * sets multiple attributes at once both arrays need to be of equal length.
     *
     * @param myAttributes array of attributes
     * @param strValues  array of values
     * @throws ArrayIndexOutOfBoundsException if the arrays are
     *         not of equal length
     * @deprecated use setAttributes(JDFAttributeMap)
     */
    @Deprecated
    public void setAttributes(String[] myAttributes, String[] strValues)
    {
        if (myAttributes.length != strValues.length)
        {
            throw new ArrayIndexOutOfBoundsException();
        }

        final JDFAttributeMap attributeValueMap = new JDFAttributeMap();

        for (int nPara = 0; nPara < myAttributes.length; nPara++)
        {
            attributeValueMap.put(myAttributes[nPara], strValues[nPara]);
        }

        setAttributes(attributeValueMap);
    }

    /**
     * used by get localname
     *
     * @param pc the qualifiedname
     * @return the local part of the qualified name, null if no local name exists
     */
    public static String xmlnsLocalName(final String pc)
    {
        if (pc != null)
        {
            final int posColon = pc.indexOf(':');
            if (posColon == -1)
            {
                return pc;
            }
            else if(posColon==pc.length()-1)
            {
                return null;
            }
            return pc.substring(posColon + 1);
        }
        return null;
    }

    /**
     * get the namespace of the qualified name
     * * <blockquote><b>namespace</b>:localname</blockquote>
     *
     * @param   pc the qualified name.
     * @deprecated
     * @return  the namespace of the qualified name.
     *          An Emptystring if pc is null or no namespace found.
     */
    @Deprecated
    public static String getXMLNSNameSpace(String pc)
    {
        return xmlnsPrefix(pc);
    }


    public void setXSIType(String typ)
    {
        setAttribute(AttributeName.XSITYPE,typ,AttributeName.XSIURI);
    }

    /**
     * returns the xsi:type of this element, null if not present
     * @return String the xsi:type of this element, null if not present
     */
    public String getXSIType()
    {
        return getAttribute("type",AttributeName.XSIURI,null);
    }
    /**
     * Parses pc for it's namespace prefix<p>
     * <blockquote><code>ns:foo</code> will return <code>ns</code></blockquote>
     *@deprecated use xmlnsPrefix
     * @param pc string to parse
     *
     * @return String namespace prefix of pc, emptyspace if no ":" is in pc
     *
     */
    @Deprecated
    public static String getXMLNSPrefix(String pc)
    {
        return xmlnsPrefix(pc);
    }

    /**
     * Fix the parentNode from this. If flagSrc == null the flags of parentNode are used.
     *
     * @param parentSrc where we get the parent from
     * @param flagSrc	where er get the flags from
     */
    private void fixParent(KElement parentSrc, KElement flagSrc)
    {
        //tell him where he belongs to and...
        this.ownerNode  = parentSrc.ownerNode;
        //that he is owned (the flags are a bitmask)
        if(flagSrc == null) {
            this.flags = parentSrc.flags;
        } else {
            this.flags = flagSrc.flags;
        }
    }

    /**
     * Returns the type of the given attribute for the latest JDF version.
     * Attribute types of previous versions have to be provided by attribute-specific
     * functions (if necessary).
     *
     * @param attributeName name of the attribute
     * @return EnumAttributeType the attribute's type
     */
    public AttributeInfo.EnumAttributeType attributeType(String attributeName)
    {
        return getTheAttributeInfo().getAttributeType(attributeName);
    }

    /**
     * Sets an XML Text <br>
     * the text contents of this to the value of text
     *
     * @param text XML Text to set
     *
     * @throws JDFException if 'this' is a null element and thus nothing can be inserted in it
     */
    public void setText(String text)
    {
        removeAllText();
        appendText(text);
    }

    @Override
    public void removeAttribute(String attrib) throws DOMException
    {
        removeAttribute_KElement(attrib, null);
    }

    @Override
    public void removeAttributeNS(String nameSpaceURI, String attrib) throws DOMException
    {
        removeAttribute_KElement(attrib, nameSpaceURI);
    }



    @Override
    public Attr removeAttributeNode(Attr arg0) throws DOMException
    {
        setDirty(true);
        return super.removeAttributeNode(arg0);
    }

    @Override
    public Attr setAttributeNode(Attr arg0) throws DOMException
    {
        setDirty(true);
        return super.setAttributeNode(arg0);
    }

    @Override
    public Attr setAttributeNodeNS(Attr arg0) throws DOMException
    {
        setDirty(true);
        return super.setAttributeNodeNS(arg0);
    }


    @Override
    public void setAttributeNS(String nsURI, String arg1, String arg2) throws DOMException
    {
        setDirty(true);
        setAttribute(arg1,arg2,nsURI);
    }


    @Override
    public void normalize()
    {
        setDirty(false);
        super.normalize();
    }


    @Override
    public void setNodeValue(String arg0) throws DOMException
    {
        setDirty(false);
        super.setNodeValue(arg0);
    }

    @Override
    public void setPrefix(String arg0) throws DOMException
    {
        super.setPrefix(arg0);
        setDirty(false);
    }


    @Override
    public Node appendChild(Node arg0) throws DOMException
    {
        return insertBefore(arg0, null);
    }

    @Override
    public synchronized Node removeChild(Node arg0) throws DOMException
    {
        setDirty(false);
        if(arg0 instanceof KElement) {
            ((KElement)arg0).clearTargets();
        }

        return super.removeChild(arg0);
    }

    @Override
    public synchronized Node insertBefore(Node arg0, Node arg1) throws DOMException
    {
        setDirty(false);
        return super.insertBefore(arg0,arg1);
    }

    @Override
    public synchronized Node replaceChild(Node arg0, Node arg1) throws DOMException
    {
        setDirty(false);
        if(arg1 instanceof KElement) {
            ((KElement)arg1).clearTargets();
        }
        return super.replaceChild(arg0,arg1);
    }

    /**
     * get/create the associated XMLDocUserData
     * @return the XMLDocUserData of this
     */
    protected XMLDocUserData getXMLDocUserData()
    {
        return (ownerDocument==null) ? null : (XMLDocUserData) ownerDocument.getUserData();
    }

    private void clearTargets ()
    {
        final XMLDocUserData ud=getXMLDocUserData();
        if(ud!=null)
        {
            if(hasChildElements()) // who knows what is down there -- clear cache
            {
                ud.clearTargets();
            }
            else // only need to remove this element
            {
                final String id=getAttribute(AttributeName.ID,null,null);
                if(id!=null) {
                    ud.removeTarget(id);
                }
            }
        }
    }

    /**
     * remove an attribute that is described by the xpath path
     * quietly returns if the attribute does not exist
     *
     * @param path the XPath to the attribute that is to be removed
     * @throws JDFException if the XPath is corrupt
     */
    public void removeXPathAttribute(String path)
    {
        final int pos = path.lastIndexOf(JDFConstants.AET);
        if (pos == -1)
        {
            throw new JDFException("RemoveXPathAttribute - bad attribute path: " + path);
        }
        final KElement kEle = getXPathElement(path.substring(0, pos));
        if(kEle!=null) {
            kEle.removeAttribute(path.substring(pos + 1), null);
        }
    }

    /**
     * check whether this matches a simple xpath
     * @param path
     * @return boolean
     * @deprecated use matchesPath(String path, boolean bFollowRefs)
     */
    @Deprecated
    public boolean matchesPath(String path)
    {
        return matchesPath(path,false);
    }

    /**
     * check whether this element matches a simple xpath
     * @param path xpath to match may include syntax <code>e[i]</code> or <code>e[@a="b"]</code>
     * 
     * @return boolean true, if this matches the given xpath
     */
    public boolean matchesPath(String path, boolean bFollowRefs)
    {
//      bFollowRefs only needed in the JDFElement version
        if (path == null)
        {
            return true;
        }
        if(bFollowRefs) {
            this.getClass(); // dummy to fool compiler
        }

        VString v = StringUtil.tokenize(path, "/", false);
        KElement e = this;
        for (int i = v.size() - 1; i >= 0; i--)
        {
            if(e==null) {
                return false;
            }
            final String pathAt = v.stringAt(i);
            if (!e.matchesPathName(pathAt)) 
            {
                return false;
            }

            e = e.getParentNode_KElement();
        }

        if (path.startsWith("/") && !path.startsWith("//")) {
            return e == null; // must be root
        }

        return true; // any location
    }

    protected boolean matchesPathName(final String pathAt)
    {
        if(pathAt==null || pathAt.equals(JDFConstants.STAR))
            return true;
        if(localName.equals(pathAt))
            return true;
        final String nodeName = getNodeName();
        if(nodeName.equals(pathAt))
            return true;
        String startPath=pathAt.startsWith(localName)? localName : pathAt.startsWith(nodeName) ? nodeName : null;
        if(startPath!=null) // process for attributes
        {
            String token=StringUtil.token(pathAt,1,"[");
            if(token==null || ! token.endsWith("]")) // want e[@a="b"] or e[n];
                return false;
            token=StringUtil.leftStr(token, -1); // remove "]"
            int n=StringUtil.parseInt(token, 0);
            if(n!=0)
            {
                KElement p=getParentNode_KElement();
                if(p==null)
                    return n==1;
                return p.getElement(localName,getNamespaceURI(),n-1)==this;
            }
            if(token.startsWith("@"))
            {
                token=token.substring(1);
                String nam=StringUtil.token(token, 0, "=");
                String value=StringUtil.token(token, 1, "=");
                if(value==null)
                    return false;
                if(value.length()<2 || ! value.startsWith("\"") || !value.endsWith("\""))
                    return false;
                value=value.substring(1, value.length()-1);
                return value.equals("*")&&hasAttribute_KElement(nam,null,false) || value.equals(getAttribute_KElement(nam));                       
            }
        }
        return false;
    }

    public void fillHashSet(String attName,String attNS, HashSet preFill)
    {
        fillHashSet(attName,attNS,preFill,true);
    }
    /**
     * fills a HashSet with all values of the attribute in all child elements
     * @param attName attribute name
     * @param attNS attrib ute namespaceuri
     * @param preFill the HashSet to fill
     */
    private void fillHashSet(String attName,String attNS, HashSet preFill, boolean bFirst)
    {

        String attVal=getAttribute(attName,attNS,null);
        if(attVal!=null)
        {
            if(preFill.contains(attVal)) {
                return; // been here already: break
            }
            preFill.add(attVal);
        }

        // get all subnodes, INCLUDING partition leaves
        VElement v=getChildElementVector_KElement(null,null,null,true,0);
        int siz=v.size();
        for(int i=0;i<siz;i++) // do not recurse down again for the leaves, we've already done that
        {
            v.item(i).fillHashSet(attName,attNS,preFill,false);
        }

        if(bFirst)
        {
            // also get all lower level parent partition refs
            v=getChildElementVector(null,null,null,true,0,false);
            siz=v.size();
            for(int i=0;i<siz;i++)
            {
                v.item(i).fillHashSet(attName,attNS,preFill,true);
            }
        }
    }

    /**
     * Get the vector of valid attribute values for an enumerated attribute
     * @param key the local name of the attribute
     * @return vector of valid names, null if key is not an enumeration
     */
    public VString getNamesVector(String key)
    {
        ValuedEnum enu=getEnumforAttribute(key);
        if(enu!=null)
        {
            return StringUtil.getNamesVector(enu.getClass());
        }
        return null;
    }

/////////////////////////////////////////////////////////////////////////////

}
