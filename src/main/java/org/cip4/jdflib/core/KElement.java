/*--------------------------------------------------------------------------------------------------
 * The CIP4 Software License, Version 1.0
 *
 *
 * Copyright (c) 2001-2019 The International Cooperation for the Integration of
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
 * KElement.java KElement wraps DOM_Element objects
 */
package org.cip4.jdflib.core;

import java.io.File;
import java.io.IOException;
import java.io.OutputStream;
import java.io.StringWriter;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import java.util.Vector;
import java.util.concurrent.atomic.AtomicInteger;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.xerces.dom.AttrNSImpl;
import org.apache.xerces.dom.CoreDocumentImpl;
import org.apache.xerces.dom.ElementNSImpl;
import org.apache.xerces.dom.NodeImpl;
import org.apache.xml.serialize.OutputFormat;
import org.apache.xml.serialize.XMLSerializer;
import org.cip4.jdflib.datatypes.JDFAttributeMap;
import org.cip4.jdflib.datatypes.JDFNumList;
import org.cip4.jdflib.util.ContainerUtil;
import org.cip4.jdflib.util.FileUtil;
import org.cip4.jdflib.util.JDFDate;
import org.cip4.jdflib.util.MyPair;
import org.cip4.jdflib.util.StreamUtil;
import org.cip4.jdflib.util.StringUtil;
import org.cip4.jdflib.util.UrlUtil;
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
 * KElement wraps Element and contains some generic utilities.<br>
 * Every access to a Element should be wrapped using KElement.<br>
 * KElement is completely agnostic to JDF.<br>
 * Typically elements in non-JDF namespaces will be KElements.<br>
 * <br>
 * note that it is discouraged to mix direct calls to Dom Element and KElement routines a future version will most likely contain a private ElementNSImpl rather than inherit from it. The current
 * extension solution is a work around around a xerces bug
 *
 * @author CIP4
 * @see JDFElement for the first element class that is aware of JDF
 */
public class KElement extends ElementNSImpl implements Element
{
	private static final long serialVersionUID = 1L;
	private static final Log kLog = LogFactory.getLog(JDFElement.class);

	private static AtomicInteger m_lStoreID = new AtomicInteger();
	private static boolean bIDDate = true;
	private static final SimpleDateFormat dateFormatter = new SimpleDateFormat("yyMMdd_kkmmssSSS");

	/**
	 * Constructor for KElement
	 *
	 * @param myOwnerDocument the owner document of the new element
	 * @param qualifiedName the qualified name of the element
	 */
	public KElement(final CoreDocumentImpl myOwnerDocument, final String qualifiedName)
	{
		super(myOwnerDocument, qualifiedName);
	}

	/**
	 * Constructor for KElement
	 *
	 * @param myOwnerDocument the owner document of the new element
	 * @param myNamespaceURI the namespace of the new element
	 * @param qualifiedName the qualified name of the element
	 */
	public KElement(final CoreDocumentImpl myOwnerDocument, final String myNamespaceURI, final String qualifiedName)
	{
		super(myOwnerDocument, myNamespaceURI, qualifiedName);
	}

	/**
	 * Constructor for KElement
	 *
	 * @param myOwnerDocument the owner document of the new element
	 * @param myNamespaceURI the namespace of the new element
	 * @param qualifiedName the qualified name of the element
	 * @param myLocalName the localname of the element
	 */
	public KElement(final CoreDocumentImpl myOwnerDocument, final String myNamespaceURI, final String qualifiedName, final String myLocalName)
	{
		super(myOwnerDocument, myNamespaceURI, qualifiedName, myLocalName);
	}

	/**
	 * Get the dirty status of this element
	 *
	 * @return boolean true if dirty
	 */
	public boolean isDirty()
	{
		return ((DocumentXMLImpl) ownerDocument).bGlobalDirtyFlag;
	}

	/**
	 * Set this element as dirty
	 *
	 * @deprecated use setDirty (bAttribute)
	 */
	@Deprecated
	public void setDirty()
	{
		setDirty(false);
	}

	/**
	 * Set this element as dirty
	 *
	 * @param bAttribute if true, only attributes are dirty, else also sub-elements
	 */
	public void setDirty(final boolean bAttribute)
	{
		((DocumentXMLImpl) ownerDocument).bGlobalDirtyFlag = true;
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
	 * searches for the first attribute occurence in this element or any ancestors
	 *
	 * @param attrib the attribute name
	 * @param nameSpaceURI the XML-namespace
	 * @param def the default if it does not exist
	 * @return String value of attribute found, value of def if not available
	 * @default getInheritedAttribute(attrib, null, JDFCoreConstants.EMPTYSTRING)
	 */
	public String getInheritedAttribute(final String attrib, final String nameSpaceURI, final String def)
	{
		return getInheritedAttribute_KElement(attrib, nameSpaceURI, def);
	}

	/**
	 * searches for the first attribute occurence in this element or any ancestors
	 *
	 * @param attrib the attribute name
	 * @param nameSpaceURI the XML-namespace
	 * @param def the default if it does not exist
	 * @return String value of attribute found, value of def if not available
	 * @default getInheritedAttribute_KElement(attrib, null, JDFCoreConstants.EMPTYSTRING)
	 */
	private String getInheritedAttribute_KElement(final String attrib, final String nameSpaceURI, final String def)
	{
		String strRet = getAttribute_KElement(attrib, nameSpaceURI, null);

		if (strRet == null)
		{
			final KElement parentNode = getParentNode_KElement();
			if (parentNode != null)
			{
				strRet = parentNode.getInheritedAttribute(attrib, nameSpaceURI, def);
			}
		}
		return strRet == null ? def : strRet;
	}

	/**
	 * mother of all attribute getters. Get a attribute out of this element
	 *
	 * @param attrib the name of the attribute you want to have
	 * @param nameSpaceURI namespace of key
	 * @param def the value that is returned if attrib does not exist in this or this is null
	 * @return String the attribute value as a string, or def if that attribute does not have a specified or default value
	 * @default GetAttribute(attrib, null, JDFCoreConstants.EMPTYSTRING)
	 */
	public String getAttribute(final String attrib, final String nameSpaceURI, final String def)
	{
		return getAttribute_KElement(attrib, nameSpaceURI, def);
	}

	/**
	 * Because getAttribute is overwritten in various classes this method can be called directly to get only KElement Attribute.
	 *
	 * @param attrib the name of the attribute you want to have
	 * @param nameSpaceURI namespace of key
	 * @param def the value that is returned if attrib does not exist in this - may be null
	 * @return String the attribute value as a string, or def if attribute was not found<br>
	 *         <br>
	 * @default getAttribute_KElement(attrib, null,null)
	 */
	public String getAttribute_KElement(final String attrib, final String nameSpaceURI, final String def)
	{
		final Attr attribute = getDOMAttr(attrib, nameSpaceURI, false);
		return (attribute == null) ? def : attribute.getValue();
		// switch for null defaults return JDFCoreConstants.EMPTYSTRING.equals(def)
		// ? null : def;
	}

	/**
	 * Mother of all attribute getters <br>
	 * Gets an attribute value out of an element
	 *
	 * @param strLocalName the name of the attribute you want to have
	 * @return String the value of the Attribute or emptystring
	 */
	@Override
	public String getAttribute(final String strLocalName)
	{
		return getAttribute(strLocalName, null, JDFCoreConstants.EMPTYSTRING);
	}

	/**
	 * ensure the correct case of n attribute, if present
	 *
	 * @param key
	 * @return
	 */
	public String ensureCase(final String key)
	{
		String val = getNonEmpty_KElement(key);
		if (val == null)
		{
			val = getIgnoreCase_KElement(key);
			if (val != null)
			{
				removeIgnoreCase(key);
				setAttribute(key, val);
			}
		}
		return val;
	}

	public void removeIgnoreCase(final String key)
	{
		if (key != null)
		{
			final JDFAttributeMap m = getAttributeMap();
			for (final String keys : m.keySet())
			{
				if (key.equalsIgnoreCase(keys))
					removeAttribute(keys);
			}
		}
	}

	public void removeIgnoreCase_KElement(final String key)
	{
		if (key != null)
		{
			final JDFAttributeMap m = getAttributeMap_KElement();
			for (final String keys : m.keySet())
			{
				if (key.equalsIgnoreCase(keys))
					removeAttribute_KElement(keys, null);
			}
		}
	}

	/**
	 * similar to getAttribute but returns null for all empty strings
	 *
	 * Gets an attribute value out of an element
	 *
	 * @param strLocalName the name of the attribute you want to have
	 * @return String the value of the Attribute or null
	 */
	public String getIgnoreCase(final String strLocalName)
	{
		final JDFAttributeMap map = getAttributeMap();
		return map.getIgnoreCase(strLocalName);
	}

	/**
	 * similar to getAttribute but returns null for all empty strings
	 *
	 * Gets an attribute value out of an element
	 *
	 * @param strLocalName the name of the attribute you want to have
	 * @return String the value of the Attribute or null
	 */
	public String getIgnoreCase_KElement(final String strLocalName)
	{
		final JDFAttributeMap map = getAttributeMap_KElement();
		return map.getIgnoreCase(strLocalName);
	}

	/**
	 * similar to getAttribute but returns null for all empty strings
	 *
	 * Gets an attribute value out of an element
	 *
	 * @param strLocalName the name of the attribute you want to have
	 * @return String the value of the Attribute or null
	 */
	public String getNonEmpty(final String strLocalName)
	{
		final String val = getAttribute(strLocalName, null, null);
		return val == null || JDFConstants.EMPTYSTRING.equals(val) ? null : val;
	}

	/**
	 * similar to getAttribute but returns null for all empty strings
	 *
	 * Gets an attribute value out of an element
	 *
	 * @param strLocalName the name of the attribute you want to have
	 * @return String the value of the Attribute or null
	 */
	public String getNonEmpty_KElement(final String strLocalName)
	{
		final String val = getAttribute_KElement(strLocalName, null, null);
		return val == null || JDFConstants.EMPTYSTRING.equals(val) ? null : val;
	}

	/**
	 * similar to hasAttribute but returns false for all empty strings
	 *
	 * Gets an attribute value out of an element
	 *
	 * @param strLocalName the name of the attribute you want to have
	 * @return String the value of the Attribute or emptystring
	 */
	public boolean hasNonEmpty(final String strLocalName)
	{
		final String val = getNonEmpty(strLocalName);
		return val != null;
	}

	/**
	 * similar to hasAttribute but returns false for all empty strings
	 *
	 * Gets an attribute value out of an element
	 *
	 * @param strLocalName the name of the attribute you want to have
	 * @return String the value of the Attribute or emptystring
	 */
	public boolean hasNonEmpty_KElement(final String strLocalName)
	{
		final String val = getNonEmpty_KElement(strLocalName);
		return val != null;
	}

	/**
	 * getAttribute with no pardon for namespaces or anything
	 *
	 * @param qualifiedName the name of the attribute you want to have
	 * @return String the value of the Attribute or emptystring
	 */
	public String getAttributeRaw(final String qualifiedName)
	{
		final Attr a = getAttributeNode(qualifiedName);
		return a == null ? null : a.getValue();
	}

	/**
	 * Mother of all attribute getters <br>
	 * Gets an attribute value out of an element
	 *
	 * @param strLocalName the name of the attribute you want to have
	 * @return String the value of the Attribute or emptystring
	 */
	public String getAttribute_KElement(final String strLocalName)
	{
		return getAttribute_KElement(strLocalName, null, JDFCoreConstants.EMPTYSTRING);
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

	/**
	 * Sets an NMTOKENS attribute to all elements from parameter value will be concatenate with blanks to the resulting NMTOKEN
	 *
	 * @param key the name of the attribute to set
	 * @param value the values for the attribute key
	 * @param nameSpaceURI the namespace of the key
	 * @deprecated use setAttribute instead
	 * @default setvStringAttribute(key, vStr, null)
	 */
	@Deprecated
	public void setvStringAttribute(final String key, final VString value, final String nameSpaceURI)
	{
		setAttribute(key, value, nameSpaceURI);
	}

	/**
	 * Sets an NMTOKENS attribute to all elements from parameter value will be concatenate with blanks to the resulting NMTOKEN
	 *
	 * @param key the name of the attribute to set
	 * @param value the values for the attribute key
	 * @param nameSpaceURI the namespace of the key
	 * @default setvStringAttribute(key, vStr, null)
	 */
	public void setAttribute(final String key, final VString value, final String nameSpaceURI)
	{
		final String s = StringUtil.setvString(value);
		setAttribute(key, s, nameSpaceURI);
	}

	/**
	 * Sets an NMTOKENS attribute to all elements from parameter value will be concatenate with blanks to the resulting NMTOKEN
	 *
	 * @param key the name of the attribute to set
	 * @param value the values for the attribute key
	 * @param nameSpaceURI the namespace of the key
	 * @default setvStringAttribute(key, vStr, null)
	 */
	public void setAttribute(final String key, final List<String> value, final String nameSpaceURI)
	{
		final String s = StringUtil.setvString(value);
		setAttribute(key, s, nameSpaceURI);
	}

	/**
	 *
	 * @param key
	 * @param value
	 * @param nameSpaceURI
	 */
	public void setAttribute(final String key, JDFDate value, final String nameSpaceURI)
	{
		if (value == null)
			value = new JDFDate();
		setAttribute(key, value.getDateTimeISO(), nameSpaceURI);
	}

	/**
	 *
	 * @param key
	 * @param value
	 * @param nameSpaceURI
	 */
	public void setAttribute(final String key, final JDFNumList value, final int precision)
	{
		setAttribute(key, value == null ? null : value.getString(precision));
	}

	/**
	 * Get The DOM Attribute node of a given attribute if attrib has no namespace prefix and nameSpaceURI is a wildcard the attribute with the element prefix will be returned if no empty attribute
	 * exists e.g. getDOMAttr("a") will return the node x:a in <x:e x:a="b"/>
	 *
	 * @param attrib the attribute Name
	 * @param nameSpaceURI then namespaceURI, defaults to the local namespace
	 * @param bInherit search in parent elements as well
	 * @return Node the DOMAttr node of the matching attribute
	 */
	public Attr getDOMAttr(final String attrib, String nameSpaceURI, final boolean bInherit)
	{
		Attr a = null;
		if ((nameSpaceURI == null) || nameSpaceURI.equals(JDFCoreConstants.EMPTYSTRING))
		{
			a = getAttributeNode(attrib);
			if (a != null)
			{
				return a;
			}

			nameSpaceURI = null;

			final String attribPrefix = xmlnsPrefix(attrib);
			final String elementPrefix = getPrefix();
			if (elementPrefix != null && attribPrefix != null && attribPrefix.equals(elementPrefix))
			{ // has attribute prefix
				a = getAttributeNode(attrib.substring(elementPrefix.length() + 1));
			}
			else if (elementPrefix != null && attribPrefix == null)
			{
				a = getAttributeNode(elementPrefix + JDFCoreConstants.COLON + attrib);
			}

			if ((a == null) && !attrib.startsWith(JDFCoreConstants.XMLNS))
			{
				nameSpaceURI = getNamespaceURIFromPrefix(attribPrefix);
			}
		}

		// either we have an explicit ns or we haven't found anything in dom
		// level 1 - assume the namespace of this
		if ((a == null) && (nameSpaceURI != null))
		{
			// the xmlNSLocalName here is just in case - actually you shouldn't be calling both ns and prefix
			final String xmlnsLocalName = KElement.xmlnsLocalName(attrib);
			a = getAttributeNodeNS(nameSpaceURI, xmlnsLocalName);
			if ((a == null) && nameSpaceURI.equals(getNamespaceURI()))
			{
				a = getAttributeNodeNS(null, xmlnsLocalName);
			}
		}

		if (a == null && bInherit)
		{
			final KElement parent = getParentNode_KElement();
			if (parent != null)
			{
				return parent.getDOMAttr(attrib, nameSpaceURI, bInherit);
			}
		}

		return a;
	}

	// ////////////////////////////////////////////////////////////////////
	/**
	 * Mother of all Attribute setters <br>
	 * Sets a new attribute. If an attribute with that name is already present in the element, its value is changed to be that of the value parameter. This value is a simple string; it is not parsed
	 * as it is being set. So any markup (such as syntax to be recognized as an entity reference) is treated as literal text, and needs to be appropriately escaped by the implementation when it is
	 * written out. In order to assign an attribute value that contains entity references, the user must create an Attr node plus any Text and EntityReference nodes, build the appropriate subtree, and
	 * use setAttributeNode to assign it as the value of an attribute. To set an attribute with a qualified name and namespace URI, use the setAttributeNS method.
	 *
	 * @param key the qualified name of the attribute to create or alter.
	 * @param value the value to set in string form. If null, the attribute is removed
	 * @param nameSpaceURI the namespace the element is in
	 * @throws JDFException if no settings of its attributes are possible
	 */
	public void setAttribute(final String key, final String value, final String nameSpaceURI)
	{
		final Document d = getOwnerDocument();
		if ((d instanceof DocumentXMLImpl) && !((DocumentXMLImpl) d).isStrictNSCheck())
		{
			super.setAttributeNS(nameSpaceURI, key, value);
			return;
		}
		boolean bDirty = false;
		if (value == null)
		{
			removeAttribute(key, nameSpaceURI);
			return;
		}

		if ((nameSpaceURI == null) || (nameSpaceURI.equals(JDFCoreConstants.EMPTYSTRING)))
		{ // //////////// DOM Level 1 ///////////////////
			bDirty = setDomAttribute2FromDom1(key, value);
		}
		else
		{ // //////////// DOM Level 2 ///////////////////
			bDirty = setDomAttribute2FromDom2(key, value, nameSpaceURI);
		}

		if (bDirty)
		{
			setDirty(true);
		}
	}

	/**
	 * @param key
	 * @param value
	 * @param nameSpaceURI
	 * @return
	 */
	private boolean setDomAttribute2FromDom2(final String key, final String value, final String nameSpaceURI)
	{
		final boolean bDirty;
		if (JDFCoreConstants.XMLNSURI.equals(nameSpaceURI))
		{
			// never ever set "xmlns:foo="" !
			setDOMNSValue(key, value, nameSpaceURI);
			bDirty = true;
		}
		else
		// standard attribute (not xmlns)
		{
			bDirty = setRegularDomVal(key, value, nameSpaceURI);
		}
		return bDirty;
	}

	boolean setRegularDomVal(final String key, final String value, String nameSpaceURI)
	{
		boolean bDirty = false;
		final Node a = getAttributeNodeNS(nameSpaceURI, xmlnsLocalName(key));
		if (a == null || !value.equals(a.getNodeValue()))
		{
			bDirty = true;
			if (a != null)
			{ // don't search the attribute node if it is already there
				final String nodeName = a.getNodeName();

				if (nodeName.equals(key))
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
				String namespaceURI2 = getNamespaceURIFromPrefix(xmlnsPrefix(key), true);

				if (namespaceURI2 != null && !JDFCoreConstants.EMPTYSTRING.equals(namespaceURI2) && !namespaceURI2.equals(nameSpaceURI))
				{ // in case multiple namespace uris are defined for the same prefix, all we can do is to bail out loudly
					namespaceURI2 = getNamespaceURIFromPrefix(xmlnsPrefix(key), false);
					if (!ContainerUtil.equals(namespaceURI2, nameSpaceURI))
					{
						final String message = key + ": inconsistent namespace URI for prefix: " + xmlnsPrefix(key) + "; existing URI: " + namespaceURI2
								+ "; attempting to set URI: " + nameSpaceURI;
						kLog.error(message);
						throw new JDFException(message);
					}
				}

				// remove any twin dom lvl 1 attributes - just in case
				removeAttribute(key);
				if (nameSpaceURI.equals(getNamespaceURI()))
				{
					// clean up any attribute that may be in the same ns but with a different prefix
					removeAttributeNS(nameSpaceURI, xmlnsLocalName(key));
					if (xmlnsPrefix(key) == null)
					{
						nameSpaceURI = null; // avoid spurious NS1 prefix
					}
				}
				super.setAttributeNS(nameSpaceURI, key, value);
			}
		}
		return bDirty;
	}

	void setDOMNSValue(final String key, final String value, final String nameSpaceURI)
	{
		if (value.equals(JDFCoreConstants.EMPTYSTRING))
		{
			removeAttributeNS(nameSpaceURI, key);
		}
		else if (!value.equals(getInheritedAttribute(xmlnsLocalName(key), nameSpaceURI, null)))
		{
			removeAttribute(key);
			super.setAttributeNS(JDFCoreConstants.XMLNSURI, key, value);
			getNamespaceURIFromPrefix(StringUtil.rightStr(key, -6), true);
		}
	}

	/**
	 * @param key
	 * @param value
	 * @return
	 */
	private boolean setDomAttribute2FromDom1(final String key, final String value)
	{
		final String nameSpaceURI = null;
		boolean bDirty = false;
		// must explicitly set xmlns as DOM level 2 because the xerces serializer checks for DOM level 2
		// xmlns attributes and avoids duplicate serialization of the attribute and namespace nodes
		if (key.startsWith(JDFCoreConstants.XMLNS) && (key.length() == 5 || key.charAt(5) == ':'))
		{ // set an attribute which is a namespace
			if (value.equals(JDFCoreConstants.EMPTYSTRING))
			{
				final Node a = getAttributeNode(key);
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
				String myPrefix = xmlnsLocalName(key);
				if ("xmlns".equals(key))
				{
					myPrefix = null;
				}
				else
				{
					super.setAttributeNS(JDFCoreConstants.XMLNSURI, key, value);
				}
				final DocumentXMLImpl doc = (DocumentXMLImpl) getOwnerDocument();
				doc.setNamespaceURIFromPrefix(myPrefix, value);
			}
			final String prefixElem = getPrefix();
			final String prefixValue = StringUtil.token(key, 1, ":");
			if (ContainerUtil.equals(prefixElem, prefixValue) && !value.equals(super.getNamespaceURI()))
			{
				setNamespaceURI(value);
			}
		}
		else
		{ // set a normal attribute
			final String attributePrefix = xmlnsPrefix(key);
			if (attributePrefix == null)
			{ // no attribute prefix, put the attribute in the default namespace
				bDirty = true;
				super.setAttributeNS(null, key, value);
			}
			else
			{ // try to find a namespace
				final String namespaceURI2 = getNamespaceURIFromPrefix(attributePrefix);
				if (namespaceURI2 != null)
				{
					// now we have a namespace --> recurse
					setAttribute(key, value, namespaceURI2);
				}
				else
				{
					// attribute with prefix, no namespace found
					final Node a = getDOMAttr(key, null, false);
					if (a == null || !value.equals(a.getNodeValue()))
					{
						bDirty = true;
						if (a != null)
						{
							final String nodeName = a.getNodeName();

							// don't search the attribute node if it is
							// already there
							if (key.equals(nodeName))
							{ // overwrite default namespace with qualified
									// namespace or vice versa
								removeAttribute(nodeName);
								super.setAttribute(key, value);
							}
							else
							{ // same qualified name, simply overwrite the
									// value
								a.setNodeValue(value);
							}
						}
						else
						{
							final String nsURI2 = getNamespaceURIFromPrefix(xmlnsPrefix(key));
							if ((nsURI2 != null) && !nsURI2.equals(nameSpaceURI))
							{
								throw new JDFException("KElement.setAttribute: inconsistent namespace URI for prefix: " + xmlnsPrefix(key) + "; existing URI: " + nsURI2
										+ "; attempting to set URI: " + nameSpaceURI);
							}
							try
							{
								super.setAttributeNS(nsURI2, key, value);
							}
							catch (final DOMException de)
							{
								// we punt here because it will hopefully
								// only be an ordering problem
								super.setAttribute(key, value);
							}
						}
					}
				}
			}
		}
		return bDirty;
	}

	/**
	 * no namespace variant
	 *
	 * @param key name of the attribute to set
	 * @param value value of the attribute
	 */
	@Override
	public void setAttribute(final String key, final String value)
	{
		setAttribute(key, value, null);
	}

	/**
	 * fastest setAttribute - use only if you know exactly what you are doing
	 *
	 * @param key name of the attribute to set
	 * @param value value of the attribute
	 */
	public void setAttributeRaw(final String key, final String value)
	{
		super.setAttribute(key, value);
	}

	/**
	 * fastest setAttribute with namespace- use only if you know exactly what you are doing
	 *
	 * @param ns the namespace uri
	 * @param key name of the attribute to set
	 * @param value value of the attribute
	 */
	public void setAttributeNSRaw(final String ns, final String key, final String value)
	{
		super.setAttributeNS(ns, key, value);
	}

	/**
	 * Sets an element attribute
	 *
	 * @param key the name of the attribute to set
	 * @param value the value for the attribute
	 * @param nameSpaceURI the namespace the element is in
	 * @deprecated use setAttribute instead
	 * @default SetAttribute(key, value, null)
	 */
	@Deprecated
	public void setIntAttribute(final String key, final int value, final String nameSpaceURI)
	{
		setAttribute(key, value, nameSpaceURI);
	}

	/**
	 * Sets an element attribute
	 *
	 * @param key the name of the attribute to set
	 * @param value the value for the attribute
	 * @param nameSpaceURI the namespace the element is in
	 * @default SetAttribute(key, value, null)
	 */
	public void setAttribute(final String key, final int value, final String nameSpaceURI)
	{
		setAttribute(key, StringUtil.formatInteger(value), nameSpaceURI);
	}

	/**
	 * Sets an element attribute
	 *
	 * @param key the name of the attribute to set
	 * @param value the long value for the attribute
	 * @param nameSpaceURI the namespace the element is in
	 * @default SetAttribute(key, value, null)
	 */
	public void setAttribute(final String key, final long value, final String nameSpaceURI)
	{
		setAttribute(key, StringUtil.formatLong(value), nameSpaceURI);
	}

	/**
	 * Sets an element attribute
	 *
	 * @param key the name of the attribute to set
	 * @param value the value for the attribute
	 * @param nameSpaceURI the namespace the element is in
	 * @deprecated use setAttribute instead
	 * @default setAttribute(key, value, null)
	 */
	@Deprecated
	public void setRealAttribute(final String key, final double value, final String nameSpaceURI)
	{
		setAttribute(key, StringUtil.formatDouble(value), nameSpaceURI);
	}

	/**
	 * Sets an element attribute
	 *
	 * @param key the name of the attribute to set
	 * @param value the value for the attribute
	 * @param nameSpaceURI the namespace the element is in
	 * @default setAttribute(key, value, null)
	 */
	public void setAttribute(final String key, final double value, final String nameSpaceURI)
	{
		setAttribute(key, StringUtil.formatDouble(value), nameSpaceURI);
	}

	/**
	 * Sets an element attribute
	 *
	 * @param key the name of the attribute to set
	 * @param value the value for the attribute
	 * @param nameSpaceURI the namespace the element is in
	 * @param precision
	 *
	 * @default setAttribute(key, value, null)
	 */
	public void setAttribute(final String key, final double value, final String nameSpaceURI, final int precision)
	{
		setAttribute(key, StringUtil.formatDouble(value, precision), nameSpaceURI);
	}

	/**
	 * SetAttribute - Sets an element attribute
	 *
	 * @param key the name of the attribute to set
	 * @param b value of the boolean attribute to be set (true or false)
	 * @param nameSpaceURI the nameSpace the attribute is in
	 * @deprecated use setAttribute instead
	 * @default setAttribute(key, b, null)
	 */
	@Deprecated
	public void setBoolAttribute(final String key, final boolean b, final String nameSpaceURI)
	{
		setAttribute(key, b, nameSpaceURI);
	}

	/**
	 * SetAttribute - Sets an element attribute
	 *
	 * @param key the name of the attribute to set
	 * @param b value of the boolean attribute to be set (true or false)
	 * @param nameSpaceURI the nameSpace the attribute is in
	 * @default setAttribute(key, b, null)
	 */
	public void setAttribute(final String key, final boolean b, final String nameSpaceURI)
	{
		setAttribute(key, b ? JDFCoreConstants.TRUE : JDFCoreConstants.FALSE, nameSpaceURI);
	}

	/**
	 * increments or decrements a numeric attribute by inc
	 *
	 * @param key the name of the attribute to set
	 * @param inc the value to increment or decrement by
	 * @param nameSpaceURI the nameSpace the attribute is in
	 * @return double the attribute value after modification
	 */
	public double addAttribute(final String key, final double inc, final String nameSpaceURI)
	{
		double d = getRealAttribute(key, nameSpaceURI, 0);
		d += inc;
		setAttribute(key, d, nameSpaceURI);
		return d;
	}

	/**
	 * increments or decrements a numeric attribute by inc
	 *
	 * @param key the name of the attribute to set
	 * @param inc the value to increment or decrement by
	 * @param nameSpaceURI the nameSpace the attribute is in
	 * @return double the attribute value after modification
	 */
	public int addAttribute(final String key, final int inc, final String nameSpaceURI)
	{
		int i = getIntAttribute(key, nameSpaceURI, 0);
		i += inc;
		setAttribute(key, i, nameSpaceURI);
		return i;
	}

	/**
	 * Remove an attribute from the current element
	 *
	 * @param attrib attribute name to remove
	 * @param nameSpaceURI the nameSpace of the attribut
	 * @default removeAttribute(attrib, null)
	 */
	public void removeAttribute(final String attrib, final String nameSpaceURI)
	{
		removeAttribute_KElement(attrib, nameSpaceURI);
	}

	/**
	 * Remove a attribute from the current element
	 *
	 * @param attrib attribute name to remove
	 * @param nameSpaceURI the nameSpace of the attribut
	 * @default removeAttribute(attrib, null)
	 */
	public void removeAttribute_KElement(final String attrib, final String nameSpaceURI)
	{
		if (hasAttribute(attrib, nameSpaceURI, false))
		{
			if ((nameSpaceURI == null) || nameSpaceURI.equals(JDFCoreConstants.EMPTYSTRING))
			{
				super.removeAttribute(attrib);
			}
			else
			{
				super.removeAttributeNS(nameSpaceURI, xmlnsLocalName(attrib));
				if (nameSpaceURI.equals(getNamespaceURI()))
				{
					super.removeAttributeNS(null, attrib);
				}
			}
			setDirty(true);
		}
	}

	/**
	 * Checks if the actual element has a specific attribute<br>
	 * this version checks only the explicit element and NOT any inherited resource partiotions
	 *
	 * Attention! this behavior differs from that of @see getAttribute()
	 *
	 * @param attrib the name of the attribute to look for
	 * @return boolean true, if the attribute is present
	 */
	@Override
	public boolean hasAttribute(final String attrib)
	{
		return hasAttribute_KElement(attrib, null, false);
	}

	/**
	 * Checks if the actual element has a specific attribute <br>
	 * this version checks within the exact xml element
	 *
	 * @param attrib the name of the attribute to look for
	 * @param nameSpaceURI the nameSpace to look in
	 * @param bInherit if true also check recursively in parent elements
	 * @return boolean true if the attribute is present
	 * @default hasAttribute(attrib, null, false)
	 */
	public boolean hasAttribute(final String attrib, final String nameSpaceURI, final boolean bInherit)
	{
		return hasAttribute_KElement(attrib, nameSpaceURI, bInherit);
	}

	/**
	 * Checks if the actual element has a specific attribute <br>
	 * this version checks within the exact xml element
	 *
	 * @param attrib the name of the attribute to look for
	 * @param nameSpaceURI the nameSpace to look in
	 * @param bInherit if true also check recursively in parent elements
	 * @return boolean true if the attribute is present
	 * @default hasAttribute_KElement(attrib, null, false)
	 */
	public boolean hasAttribute_KElement(final String attrib, final String nameSpaceURI, final boolean bInherit)
	{
		return getDOMAttr(attrib, nameSpaceURI, bInherit) != null;
	}

	/**
	 * Append the contents of value to the existing attribute key. Create Key, if it does not exist
	 *
	 * @param key attribute key
	 * @param value string to be appended
	 * @param nameSpaceURI namespace of key
	 * @param sep separator between the original attribute value and value, defaults to " " if null
	 * @param bUnique if true, the attribute will only be appended if it is not yet within the current attribute value appendAttribute("key","next",JDFCoreConstants.EMPTYSTRING,JDFCoreConstants
	 *            .COMMA) applied to <xml key="first"/> results in <xml key="first,next"/>
	 * @default appendAttribute(key, value, null, null, false)
	 * @return the updated value; null if none exists
	 */
	public String appendAttribute(final String key, final String value, final String nameSpaceURI, String sep, final boolean bUnique)
	{
		if (StringUtil.isEmpty(value))
		{
			return getAttribute(key, nameSpaceURI, null);
		}

		final String oldVal = getAttribute_KElement(key, nameSpaceURI, null);
		if (oldVal == null)
		{
			setAttribute(key, value, nameSpaceURI);
			return value;
		}
		else
		// something is there
		{
			if (sep == null)
			{
				sep = JDFCoreConstants.BLANK;
			}

			if (!bUnique || !StringUtil.hasToken(oldVal, value, sep, 0))
			// it is either not unique/ or not there yet --> append
			{
				final String newValue = oldVal + sep + value;
				setAttribute(key, newValue, nameSpaceURI);
				return newValue;
			}
			else
			{
				return oldVal;
			}
		}
	}

	/**
	 * Tests whether an argument is a wildcard i.e null, empty or * note that Wildcard ("*") is deprecated ideally null should be used to denote a wildcard
	 *
	 * @param nodeName the argument to test
	 * @return boolean true if the name is wildcard
	 */
	public static boolean isWildCard(final String nodeName)
	{
		final int length = (nodeName == null) ? 0 : nodeName.length();
		return length == 0 || length == 1 && nodeName.charAt(0) == '*';
	}

	/**
	 * Tests whether the specified nodename and namespace fits the nodename and namespace of 'this'
	 *
	 * @param nodeName the name of the node to test. May be either local or qualified
	 * @param nameSpaceURI the namespace of the node to test.
	 * @return boolean true if fits
	 */
	public boolean fitsName(final String nodeName, final String nameSpaceURI)
	{
		return fitsName_KElement(nodeName, nameSpaceURI);
	}

	protected boolean fitsName_KElement(final String nodeName, final String nameSpaceURI)
	{
		boolean bNameOK = isWildCard(nodeName);
		if (!bNameOK)
		{
			// first check name, since it is faster
			final String s = getNodeName();
			final int l1 = nodeName.length();
			final int l2 = s.length();
			if (l1 > l2 || l2 + 1 == l1) // ":name" is illegal
			{
				return false;
			}
			else if (l1 == l2)
			{
				bNameOK = s.equals(nodeName);
			}
			else
			{
				bNameOK = (s.indexOf(':') == (l2 - l1 - 1)) && s.endsWith(nodeName);
			}
		}
		// only check ns, if the name is ok
		final boolean bNSNotWild = bNameOK && !isWildCard(nameSpaceURI);
		if (bNSNotWild && !nameSpaceURI.equals(getNamespaceURI()))
		{
			bNameOK = false;
		}

		return bNameOK;
	}

	/**
	 * Gets the NameSpaceURI corresponding to a given prefix, returns null if no namespace is defined
	 *
	 * @param prefix the prefix to check for
	 * @return String The nameSpaceURI that maps to prefix
	 */
	public String getNamespaceURIFromPrefix(final String prefix)
	{
		return getNamespaceURIFromPrefix(prefix, true);
	}

	/**
	 *
	 * @param prefix
	 * @param bcache
	 * @return
	 */
	private String getNamespaceURIFromPrefix(final String prefix, final boolean bcache)
	{
		String strNamespaceURI = null;
		if (prefix == null || prefix.equals(JDFCoreConstants.EMPTYSTRING))
		{
			final String elementPrefix = getPrefix();
			if (elementPrefix == null)
			{
				strNamespaceURI = super.getNamespaceURI();
				if (strNamespaceURI != null)
				{
					return strNamespaceURI;
				}
			}

			strNamespaceURI = getAttribute(JDFCoreConstants.XMLNS, null, null);
			if (strNamespaceURI != null)
			{
				return strNamespaceURI;
			}
		}
		else
		{
			// some well known hardcoded stuff
			if (prefix.equals(JDFCoreConstants.XSI))
			{
				return JDFCoreConstants.XSIURI;
			}
			if (prefix.equals(JDFCoreConstants.XMLNS))
			{
				return JDFCoreConstants.XMLNSURI;
			}
			final DocumentXMLImpl documentXMLImpl = (DocumentXMLImpl) getOwnerDocument();
			if (bcache)
			{
				strNamespaceURI = documentXMLImpl.getNamespaceURIFromPrefix(prefix);
				if (strNamespaceURI != null)
				{
					return StringUtil.getNonEmpty(strNamespaceURI);
				}
			}
			final String elementPrefix = getPrefix();
			if (prefix.equals(elementPrefix))
			{
				// we are checking an element or attribute with the same prefix as this.
				// therefore we assume that the same NamespaceURI also applies, if it is set
				strNamespaceURI = getNamespaceURI();
				if (strNamespaceURI != null)
				{
					documentXMLImpl.setNamespaceURIFromPrefix(prefix, strNamespaceURI);
					return strNamespaceURI;
				}
			}

			strNamespaceURI = getAttribute(prefix, JDFCoreConstants.XMLNSURI, null);
			if (strNamespaceURI == null)
				strNamespaceURI = StringUtil.getNonEmpty(super.getAttribute("xmlns:" + prefix));

			// found a decent URI
			if (strNamespaceURI != null)
			{
				documentXMLImpl.setNamespaceURIFromPrefix(prefix, strNamespaceURI);
				return strNamespaceURI;
			}

			final NamedNodeMap nl = getAttributes();
			final int length = nl.getLength();
			for (int i = 0; i < length; i++)
			{
				final Node at = nl.item(i);
				if (at instanceof AttrNSImpl)
				{
					final AttrNSImpl ati = (AttrNSImpl) at;
					if (prefix.equals(ati.getPrefix()))
					{
						strNamespaceURI = ati.getNamespaceURI();
						if (strNamespaceURI != null)
						{
							documentXMLImpl.setNamespaceURIFromPrefix(prefix, strNamespaceURI);
							return strNamespaceURI;
						}
					}
				}
			}
		}

		// nothing found, recurse into parent element and try again
		final KElement e = getParentNode_KElement();
		if (e != null)
		{
			return e.getNamespaceURIFromPrefix(prefix, bcache);
		}

		// we reached the document root and didn't find anything --> punt and return an empty string
		final DocumentXMLImpl documentXMLImpl = (DocumentXMLImpl) getOwnerDocument();
		if (prefix == null)
			documentXMLImpl.setNamespaceURIFromPrefix(JDFConstants.COLON, "");
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
		if (s != null && !JDFConstants.EMPTYSTRING.equals(s) || ((DocumentXMLImpl) getOwnerDocument()).isIgnoreNSDefault())
		{
			return s;
		}

		s = getPrefix();

		KElement parent = getParentNode_KElement();
		while (parent != null)
		{
			final String prefix = KElement.xmlnsPrefix(parent.getNodeName());
			if (prefix == null && s == null || prefix != null && prefix.equals(s))
			{
				final String nsuri = parent.getNamespaceURI();
				if (nsuri != null) // we found a valid nsuri so we might as well set it for this
				{
					namespaceURI = nsuri;
					return nsuri;
				}
			}
			parent = parent.getParentNode_KElement();
		}

		final String nsuri;

		if (s != null)
		{
			nsuri = getInheritedAttribute(JDFCoreConstants.XMLNS + JDFCoreConstants.COLON + s, null, null);
		}
		else
		{
			nsuri = getInheritedAttribute(JDFCoreConstants.XMLNS, null, null);
		}
		if (nsuri != null) // we found a valid nsuri so we might as well set it for this
		{
			namespaceURI = nsuri;
		}
		else if (s == null)
		{
			// nop

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
			if (posColon > 0)
			{
				return nodeName.substring(0, posColon);
			}
		}

		return null;
	}

	/**
	 * Gets the local name of kElem
	 *
	 * @deprecated use getLocalName
	 * @param kElem the element to get the LocalName from
	 * @return String the local name of 'this'
	 */
	@Deprecated
	public static String getLocalNameStatic(final KElement kElem)
	{
		return kElem.getLocalName();
	}

	/**
	 * Sets the attributes from the curent element. If Attributes map is null or empty, zero is returned. otherwhise the size of the map is returned
	 *
	 * @param map map of attributes to set
	 * @return int size of the map or zero if empty
	 */
	public int setAttributes(final JDFAttributeMap map)
	{
		int iRet = 0;
		if (map != null && !map.isEmpty())
		{
			final Iterator<String> it = map.getKeyIterator();
			while (it.hasNext())
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
	 * Sets the attributes from the curent element to the attributes from kElem. If the Attributes map from kElem is empty (kElem has no attributes), zero is returned. Otherwhise the size of the map
	 * (number of attributes from kElem) is returned.
	 *
	 * @param kElem the attribute source
	 * @return int number of elements from kElem
	 */
	public int setAttributes(final KElement kElem)
	{
		return setAttributes(kElem, null);
	}

	/**
	 * Sets the attributes from the current element to the attributes from kElem. If the Attributes map from kElem is empty (kElem has no attributes), zero is returned. Otherwise the size of the map
	 * (number of attributes from kElem) is returned.
	 *
	 * @param kElem the attribute source
	 */
	public void setAttributesRaw(final KElement kElem)
	{
		if (kElem == null)
			return;
		final NamedNodeMap nm = kElem.getAttributes();
		if (nm != null)
		{
			final int siz = nm.getLength();
			for (int i = 0; i < siz; i++)
			{
				final Node a = nm.item(i);
				super.setAttributeNS(a.getNamespaceURI(), a.getNodeName(), a.getNodeValue());
			}
		}
	}

	/**
	 * Sets the attributes from the curent element to the attributes from kElem. If the Attributes map from kElem is empty (kElem has no attributes), zero is returned. Otherwhise the size of the map
	 * (number of attributes from kElem) is returned.
	 *
	 * @param kElem the attribute source
	 * @param ignoreList
	 * @return int number of elements from kElem
	 */
	public int setAttributes(final KElement kElem, final VString ignoreList)
	{
		if (kElem == null)
		{
			return 0;
		}

		int siz = 0;
		final NamedNodeMap nm = kElem.getAttributes();
		if (nm != null)
		{
			siz = nm.getLength();
			boolean bCatch = false;
			// this loop allows us to catch namespace exceptions, then set the namespace and subsequently reset the attributes
			for (int j = 0; j < 2; j++)
			{
				for (int i = 0; i < siz; i++)
				{
					final Node a = nm.item(i);
					if (ignoreList == null || (!ignoreList.contains(a.getLocalName()) && !ignoreList.contains(a.getNodeName())))
					{
						try
						{
							setAttribute(a.getNodeName(), a.getNodeValue(), a.getNamespaceURI());
						}
						catch (final JDFException x)
						{
							bCatch = true;
							if (j == 1)
								throw x;
						}
					}
				}
				if (!bCatch)
					break; // one loop is enough in case all sets went through
			}
		}

		return siz;
	}

	/**
	 * Get the Attribute Map of the actual element
	 *
	 * @return JDFAttributeMap the attribute map of the actual element - never null
	 */
	public JDFAttributeMap getAttributeMap()
	{
		return getAttributeMap_KElement();
	}

	/**
	 * Get the Attribute Map of the actual element
	 *
	 * @return JDFAttributeMap the attribute map of the actual element
	 */
	public JDFAttributeMap getAttributeMap_KElement()
	{
		final JDFAttributeMap m = new JDFAttributeMap();
		final NamedNodeMap nm = getAttributes();
		if (nm != null)
		{
			final int siz = nm.getLength();

			for (int i = 0; i < siz; i++)
			{
				final Node a = nm.item(i);
				final String nodeName = a.getNodeName();
				m.put(nodeName, a.getNodeValue());
			}
		}

		return m;
	}

	/**
	 * Method init. Superclass of all inits
	 *
	 * @return boolean true always
	 */
	public boolean init()
	{
		return true;
	}

	/**
	 * @param elementName the elementname with namespace prefix "xyz:abc"
	 * @param nameSpaceURI the namespace of the element "null" is valid if the namespace was specified already above. The method will lookup the namespace for you. Performance wise its better to add
	 *            it nevertheless.
	 * @return KElement the appended element or null
	 * @throws JDFException if you tried to append an element into an unspecified namespace
	 */
	public synchronized KElement appendElement(final String elementName, final String nameSpaceURI)
	{
		final KElement newChild = appendElementRaw(elementName, nameSpaceURI);
		setDirty(false);
		return newChild;
	}

	/**
	 * appends an element without any namespace validity checks or initialization Faster but not sa safe...
	 *
	 * @param elementName element name
	 * @param nameSpaceURI element namespace
	 * @return the newly created element
	 * @since 090216
	 */
	public synchronized KElement appendElementRaw(final String elementName, final String nameSpaceURI)
	{
		final KElement newChild = createChildFromName(elementName, nameSpaceURI);
		appendChild(newChild);
		return newChild;
	}

	/**
	 * creates a new child from name and nameSpaceURI and does some heuristics to find a lvl 2 namespace in case of dom level 1 calls
	 *
	 * @param elementName
	 * @param nameSpaceURI
	 * @return KElement
	 */
	private KElement createChildFromName(final String elementName, final String nameSpaceURI)
	{
		KElement newChild = null;
		final DocumentXMLImpl ownerDoc = (DocumentXMLImpl) getOwnerDocument();

		if (nameSpaceURI == null || JDFCoreConstants.EMPTYSTRING.equals(nameSpaceURI))
		{ // /////////////// DOM Level 1 ////////////////
			if (ownerDoc.isStrictNSCheck())
			{
				final String xmlnsPrefix = xmlnsPrefix(elementName);

				final boolean ignoreNSDefault = ownerDoc.isIgnoreNSDefault();
				String namespaceURI2 = ignoreNSDefault && xmlnsPrefix == null ? nameSpaceURI : getNamespaceURIFromPrefix(xmlnsPrefix);
				namespaceURI2 = StringUtil.getNonEmpty(namespaceURI2);
				if (xmlnsPrefix != null && namespaceURI2 == null)
				{
					throw new JDFException("You tried to add an element \"" + elementName + "\" in an unspecified Namespace");
				}
				else if (namespaceURI2 != null)
				{ // /////////////// we found an URI ////////////////
					newChild = ownerDoc.factoryCreate(this, namespaceURI2, elementName);
				}
				else
				{ // /////////////// no URI, create DOM Level 1 ////////////////
					newChild = ownerDoc.factoryCreate(this, elementName);
				}
			}
			else
			{
				newChild = ownerDoc.factoryCreate(this, elementName);
			}
		}
		else
		{ // /////////////// DOM Level 2 ////////////////
			newChild = ownerDoc.factoryCreate(this, nameSpaceURI, elementName);
		}

		return newChild;
	}

	/**
	 * append a DOM element. This function creates a new element in the given namespace and appends it there.
	 *
	 * @param elementName the name of the element to append
	 * @return KElement the appended element
	 */
	public KElement appendElement(final String elementName)
	{
		return appendElement(elementName, null);
	}

	/**
	 * append all children in a vector of elements in the order of the vector
	 *
	 * @param v the vector of elements to append, if null nothing happens
	 * @param beforeChild the child before which to append the elements of the vector
	 */
	public void copyElements(final VElement v, final KElement beforeChild)
	{
		if (v == null)
		{
			return;
		}
		for (final KElement e : v)
		{
			copyElement(e, beforeChild);
		}
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
	 *
	 * @return KElement[] the array of direct children
	 */
	public synchronized KElement[] getChildElementArray()
	{
		final ArrayList<KElement> v = new ArrayList<>();
		Node n = getFirstChild();
		while (n != null)
		{
			if (n.getNodeType() == Node.ELEMENT_NODE)
			{
				v.add((KElement) n);
			}
			n = n.getNextSibling();
		}
		final int size = v.size();
		final KElement[] a = new KElement[size];
		return v.toArray(a);
	}

	/**
	 * Get all children from the actual element matching the given conditions<br>
	 * does NOT get refElement targets although the attributes are checked in the target elements in case of refElements
	 *
	 * @param nodeName element name you are searching for
	 * @param nameSpaceURI nameSpace you are searching for
	 * @param mAttrib attributes you are lokking for
	 * @param bAnd if true, a child is only added if it has all attributes specified in Attributes mAttrib
	 * @param maxSize maximum size of the element vector
	 * @return VElement vector with all found elements
	 * @deprecated 060302 - use 6 parameter version
	 * @default getChildElementVector(null, null, null, true, 0, false)
	 */
	@Deprecated
	public VElement getChildElementVector(final String nodeName, final String nameSpaceURI, final JDFAttributeMap mAttrib, final boolean bAnd, final int maxSize)
	{
		return getChildElementVector(nodeName, nameSpaceURI, mAttrib, bAnd, maxSize, false);
	}

	/**
	 * Get all children from the actual element matching the given conditions<br>
	 * does NOT get refElement targets although the attributes are checked in the target elements in case of refElements
	 *
	 * @param nodeName element name you are searching for
	 * @param nameSpaceURI nameSpace you are searching for
	 * @param mAttrib attributes you are lokking for
	 * @param bAnd if true, a child is only added if it has all attributes specified in Attributes mAttrib
	 * @param maxSize maximum size of the element vector (0=any)
	 * @param bResolveTarget if true, IDRef elements are followed, dummy at this level but needed in JDFElement
	 * @return VElement vector with all found elements, an empty vector if no elements match
	 * @default getChildElementVector(null, null, null, true, 0, true)
	 */
	public VElement getChildElementVector(final String nodeName, final String nameSpaceURI, final JDFAttributeMap mAttrib, final boolean bAnd, final int maxSize, final boolean bResolveTarget)
	{
		return getChildElementVector_KElement(nodeName, nameSpaceURI, mAttrib, bAnd, maxSize);
	}

	/**
	 * Get all children from the actual element matching the given conditions<br>
	 * convenience for getChildElementVector(nodeName, nameSpaceURI, null, true, 0, true)
	 *
	 * @param nodeName element name you are searching for
	 * @param nameSpaceURI nameSpace you are searching for
	 * @return VElement vector with all found elements, an empty vector if no elements match
	 * @default getChildElementVector(null, null)
	 */
	public VElement getChildElementVector(final String nodeName, final String nameSpaceURI)
	{
		return getChildElementVector(nodeName, nameSpaceURI, null, true, 0, true);
	}

	/**
	 * Get all children from the actual element matching the given conditions<br>
	 * convenience for getChildElementVector(nodeName, nameSpaceURI, null, true, 0, true)
	 *
	 * @param nodeName element name you are searching for
	 * @param nameSpaceURI nameSpace you are searching for
	 * @return VElement vector with all found elements, an empty vector if no elements match
	 * @default getChildElementVector(null, null)
	 */
	public Collection<KElement> getChildArray(final String nodeName, final String nameSpaceURI)
	{
		return getChildArray_KElement(nodeName, nameSpaceURI, null, true, 0);
	}

	/**
	 * Get all children from the actual element matching the given conditions<br>
	 * does NOT get refElement targets although the attributes are checked in the target elements in case of refElements
	 *
	 * @param nodeName element name you are searching for
	 * @param nameSpaceURI nameSpace you are searching for
	 * @param mAttrib attributes you are lokking for
	 * @param bAnd if true, a child is only added if it has all attributes specified in Attributes mAttrib
	 * @param maxSize maximum size of the element vector
	 * @return VElement vector with all found elements, an empty vector if no elements match
	 * @see org.cip4.jdflib.core.KElement#getChildElementVector(java.lang.String, java.lang.String, org.cip4.jdflib.datatypes.JDFAttributeMap, boolean, int)
	 * @default getChildElementVector(null, null, null, true, 0)
	 */
	public synchronized VElement getChildElementVector_KElement(final String nodeName, final String nameSpaceURI, final JDFAttributeMap mAttrib, final boolean bAnd, final int maxSize)
	{
		final VElement v = new VElement();
		v.addAll(getChildArray_KElement(nodeName, nameSpaceURI, mAttrib, bAnd, maxSize));
		return v;
	}

	/**
	 * Get all children from the actual element matching the given conditions<br>
	 * does NOT get refElement targets although the attributes are checked in the target elements in case of refElements
	 *
	 * @param nodeName element name you are searching for
	 * @param nameSpaceURI nameSpace you are searching for
	 * @param mAttrib attributes you are lokking for
	 * @param bAnd if true, a child is only added if it has all attributes specified in Attributes mAttrib
	 * @param maxSize maximum size of the element vector
	 * @return VElement vector with all found elements, an empty vector if no elements match
	 * @see org.cip4.jdflib.core.KElement#getChildElementVector(java.lang.String, java.lang.String, org.cip4.jdflib.datatypes.JDFAttributeMap, boolean, int)
	 * @default getChildElementVector(null, null, null, true, 0)
	 */
	public List<KElement> getChildArray_KElement(String nodeName, String nameSpaceURI, JDFAttributeMap mAttrib, final boolean bAnd, final int maxSize)
	{
		final ArrayList<KElement> v = new ArrayList<>();
		if (isWildCard(nodeName))
		{
			nodeName = null;
		}

		if (isWildCard(nameSpaceURI))
		{
			nameSpaceURI = null;
		}

		if (mAttrib != null && mAttrib.isEmpty())
		{
			mAttrib = null;
		}

		final boolean bAlwaysFit = nodeName == null && nameSpaceURI == null;
		final boolean bMapEmpty = mAttrib == null;

		int iSize = 0;
		KElement kElem = getFirstChildElement();

		while (kElem != null)
		{
			if ((bAlwaysFit || kElem.fitsName_KElement(nodeName, nameSpaceURI)) && (bMapEmpty || kElem.includesAttributes(mAttrib, bAnd)))
			{
				v.add(kElem);
				if (++iSize == maxSize)
				{
					break;
				}
			}

			kElem = kElem.getNextSiblingElement();
		}

		return v;
	}

	/**
	 *
	 * @param clazz the class of the children to zapp
	 */
	public void removeChildrenByClass(final Class<? extends Node> clazz)
	{
		Node n = getFirstChild();
		final Node parent = n == null ? null : n.getParentNode();
		while (n != null)
		{
			final Node nTmp = n.getNextSibling();
			if (clazz.isInstance(n))
			{
				parent.removeChild(n);
			}
			n = nTmp;
		}

	}

	/**
	 *
	 * @param clazz
	 * @param bRecurse
	 * @param nMax
	 * @deprecated use getChildArrayByClass
	 * @return
	 */
	@Deprecated
	public <A extends KElement> Vector<A> getChildrenByClass(final Class<A> clazz, final boolean bRecurse, final int nMax)
	{
		final Vector<A> v = new Vector<>();
		v.addAll(getChildArrayByClass(clazz, bRecurse, nMax));
		return v;
	}

	/**
	 * Get all children from the actual element matching the given conditions<br>
	 * does NOT get refElement targets although the attributes are checked in the target elements in case of refElements never null
	 *
	 * @param clazz
	 * @param <A>
	 * @param bRecurse if true recurse through all children, grandchildren etc.
	 * @param nMax maximum number to search - if 0 or negative, search all
	 * @return Vector<a> vector with all found elements, never null
	 * @see org.cip4.jdflib.core.KElement#getChildElementVector(java.lang.String, java.lang.String, org.cip4.jdflib.datatypes.JDFAttributeMap, boolean, int)
	 * @default getChildElementVector(null, null, null, true, 0)
	 */
	public <A extends KElement> List<A> getChildArrayByClass(final Class<A> clazz, final boolean bRecurse, final int nMax)
	{
		final List<A> v = new ArrayList<>();
		getChildArrayByClass(clazz, bRecurse, nMax, v);
		return v;
	}

	/**
	 * Get all children from the actual element matching the given conditions<br>
	 * does NOT get refElement targets although the attributes are checked in the target elements in case of refElements never null
	 *
	 * @param clazz
	 * @param <A>
	 * @param bRecurse if true recurse through all children, grandchildren etc.
	 * @param nMax maximum number to search - if 0 or negative, search all
	 * @return Vector<a> vector with all found elements, never null
	 * @see org.cip4.jdflib.core.KElement#getChildElementVector(java.lang.String, java.lang.String, org.cip4.jdflib.datatypes.JDFAttributeMap, boolean, int)
	 * @default getChildElementVector(null, null, null, true, 0)
	 */
	@SuppressWarnings("unchecked")
	<A extends KElement> int getChildArrayByClass(final Class<A> clazz, final boolean bRecurse, final int nMax, final List<A> v)
	{
		Node node = getFirstChild();
		int n = 0;
		while (node != null)
		{
			if (clazz.isInstance(node))
			{
				v.add((A) node);
				if (++n == nMax)
					return n;
			}
			if (bRecurse && (node instanceof KElement))
			{
				n += ((KElement) node).getChildArrayByClass(clazz, bRecurse, nMax - n, v);
				if (nMax > 0 && n == nMax)
					return n;
			}
			node = node.getNextSibling();
		}
		return n;
	}

	@SuppressWarnings("unchecked")
	public <A extends KElement> A getChildWithAttribute(final Class<A> clazz, final String attName, final String attVal)
	{
		Node n = getFirstChild();
		while (n != null)
		{
			if (clazz.isInstance(n) && ((KElement) n).includesAttribute(attName, attVal))
			{
				return (A) n;
			}
			n = n.getNextSibling();
		}
		return null;
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
			if (firstChildElement instanceof KElement)
			{
				return (KElement) firstChildElement;
			}
			firstChildElement = firstChildElement.getNextSibling();
		}
		return null;
	}

	/**
	 * get the next sibling element
	 *
	 * @return KElement the next sibling element is existing otherwise null
	 */
	public KElement getNextSiblingElement()
	{
		Node nextSiblingElement = getNextSibling();
		while (nextSiblingElement != null)
		{
			if (nextSiblingElement instanceof KElement)
			{
				return (KElement) nextSiblingElement;
			}
			nextSiblingElement = nextSiblingElement.getNextSibling();
		}
		return null;
	}

	/**
	 * get the previous sibling element
	 *
	 * @return KElement the previous sibling element is existing otherwise null
	 */
	public KElement getPreviousSiblingElement()
	{
		Node previousSiblingElement = getPreviousSibling();
		while (previousSiblingElement != null)
		{
			if (previousSiblingElement instanceof KElement)
			{
				return (KElement) previousSiblingElement;
			}
			previousSiblingElement = previousSiblingElement.getPreviousSibling();
		}
		return null;
	}

	/**
	 * Gets the previous sibling named nodename from the namespace nameSpaceURI of 'this'.
	 *
	 * @param <a>
	 * @param clazz the class of the sibling
	 * @return KElement the next sibling element of 'this', null if none is found
	 */
	@SuppressWarnings("unchecked")
	public <a extends KElement> a getNextSiblingElement(final Class<a> clazz)
	{
		Node e = getNextSibling();
		while (e != null)
		{
			if (clazz.isInstance(e))
			{
				return (a) e;
			}
			e = e.getNextSibling();
		}
		return null;
	}

	/**
	 * Gets the previous sibling named nodename from the namespace nameSpaceURI of 'this'.
	 *
	 * @param <a>
	 * @param clazz the class of the sibling
	 * @return KElement the next sibling element of 'this', null if none is found
	 */
	@SuppressWarnings("unchecked")
	public <a extends KElement> a getFirstChildElement(final Class<a> clazz)
	{
		Node e = getFirstChild();
		while (e != null)
		{
			if (clazz.isInstance(e))
			{
				return (a) e;
			}
			e = e.getNextSibling();
		}
		return null;
	}

	/**
	 * Gets the previous sibling named nodename from the namespace nameSpaceURI of 'this'.
	 *
	 * @param nodeName the name of the sibling
	 * @param nameSpaceURI the namespace of the sibling
	 * @return KElement the next sibling element of 'this', null if none is found
	 */
	public KElement getNextSiblingElement(final String nodeName, final String nameSpaceURI)
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
	 * Gets the previous sibling named nodename from the namespace nameSpaceURI of 'this'.
	 *
	 * @param nodeName the name of the sibling
	 * @param nameSpaceURI the namespace of the sibling
	 * @return KElement the next sibling element of 'this', null if none is found
	 */
	public KElement getFirstChildElement(final String nodeName, final String nameSpaceURI)
	{
		KElement e = getFirstChildElement();
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
	 * Gets the previous sibling named nodename from the namespace nameSpaceURI of 'this'.
	 *
	 * @param nodeName the name of the sibling
	 * @param nameSpaceURI the namespace of the sibling
	 * @return KElement the previous sibling element of 'this', null if none is found
	 */
	public KElement getPreviousSiblingElement(final String nodeName, final String nameSpaceURI)
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
	 * @param aMap the attribute names to check
	 * @param bAnd true if you want to make sure all given attributes in aMap are present. False if it is enough if only one attribute in aMap is present
	 * @return boolean: true if present
	 * @default IncludesAttributes(aMap, true)
	 */
	public boolean includesAttributes(final JDFAttributeMap aMap, final boolean bAnd)
	{
		if (aMap == null || aMap.isEmpty())
		{
			return true;
		}
		String key;
		String value;
		final Iterator<String> it = aMap.getKeyIterator();

		if (bAnd)
		{
			while (it.hasNext())
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
		while (it.hasNext())
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
	 * Check if an attribute is present. If attValue is '*', "" or null it is checked if the element attName is present. Only for simple attributes where an exact .equals match is appropriate, for
	 * ranges and rangelists use JDFElement.includesMatchingAttributes()
	 *
	 * @param attName the name of the attribute
	 * @param attValue the value of the attribute
	 * @return boolean true if present
	 * @default includesAttribute(attName, null)
	 */
	public boolean includesAttribute(final String attName, final String attValue)
	{
		final Attr attr = getDOMAttr(attName, null, false);
		if (attr == null)
		{
			return false;
		}

		if (isWildCard(attValue))
		{
			return true;
		}
		return attValue.equals(attr.getValue());
	}

	/**
	 * Gets children of 'this' by tag name, nameSpaceURI and attribute map, if the attribute map is not empty.<br>
	 * Searches the entire tree including hidden nodes that are children of non-matching nodes
	 *
	 * @param elementName elementname you are searching for
	 * @param nameSpaceURI nameSpace you are searching for
	 * @param mAttrib map of attributes you are looking for <br>
	 *            Wildcards in the attribute map are supported
	 * @param bDirect if true, return value is a vector only of all direct child elements. <br>
	 *            Otherwise the returned vector contains nodes of arbitrary depth
	 * @param bAnd if true, a child is only added, if it includes all attributes, specified in mAttrib
	 * @param maxSize maximum size of the element vector. maxSize is ignored if bDirect is false
	 * @return VElement: vector with all found elements
	 * @see org.cip4.jdflib.core.KElement#getChildElementVector(java.lang.String, java.lang.String, org.cip4.jdflib.datatypes.JDFAttributeMap, boolean, int, boolean)
	 * @default getChildrenByTagName(s,null,null, false, true, 0)
	 */
	public VElement getChildrenByTagName(final String elementName, final String nameSpaceURI, final JDFAttributeMap mAttrib, final boolean bDirect, final boolean bAnd, final int maxSize)
	{
		if (bDirect)
		{
			return getChildElementVector(elementName, nameSpaceURI, mAttrib, bAnd, maxSize, true);
		}

		// maxSize is ignored in the tree walk!
		final boolean bHasNoMap = mAttrib == null || mAttrib.isEmpty();

		final VElement v = new VElement();
		KElement e = getFirstChildElement();
		final boolean bAlwaysFit = isWildCard(elementName) && isWildCard(nameSpaceURI);
		while (e != null)
		{
			if ((bAlwaysFit || e.fitsName(elementName, nameSpaceURI)) && (bHasNoMap || e.includesAttributes(mAttrib, bAnd)))
			{
				// this guy is the one
				v.add(e);
				if (maxSize > 0 && v.size() == maxSize)
				{
					return v;
				}
			}
			final int maxSizeRecurse = maxSize > 0 ? maxSize - v.size() : maxSize;
			final VElement v2 = e.getChildrenByTagName(elementName, nameSpaceURI, mAttrib, bDirect, bAnd, maxSizeRecurse);
			v.addAll(v2);

			if (maxSize > 0 && v.size() >= maxSize)
			{
				return v;
			}
			e = e.getNextSiblingElement();
		}
		return v;
	}

	/**
	 * Gets children of 'this' by tag name, nameSpaceURI and attribute map, if the attribute map is not empty.<br>
	 * Searches the entire tree including hidden nodes that are children of non-matching nodes
	 *
	 * @param elementName elementname you are searching for
	 * @param nameSpaceURI nameSpace you are searching for
	 * @param mAttrib map of attributes you are looking for <br>
	 *            Wildcards in the attribute map are supported
	 * @param bDirect if true, return value is a vector only of all direct child elements. <br>
	 *            Otherwise the returned vector contains nodes of arbitrary depth
	 * @param bAnd if true, a child is only added, if it includes all attributes, specified in mAttrib
	 * @param maxSize maximum size of the element vector. maxSize is ignored if bDirect is false
	 * @return VElement: vector with all found elements
	 * @see org.cip4.jdflib.core.KElement#getChildElementVector(java.lang.String, java.lang.String, org.cip4.jdflib.datatypes.JDFAttributeMap, boolean, int, boolean)
	 * @default getChildrenByTagName(s,null,null, false, true, 0)
	 */
	public VElement getChildrenByTagName_KElement(final String elementName, final String nameSpaceURI, final JDFAttributeMap mAttrib, final boolean bDirect, final boolean bAnd, final int maxSize)
	{
		if (bDirect)
		{
			return getChildElementVector_KElement(elementName, nameSpaceURI, mAttrib, bAnd, maxSize);
		}

		// maxSize is ignored in the tree walk!
		final boolean bHasNoMap = mAttrib == null || mAttrib.isEmpty();

		final VElement v = new VElement();
		KElement e = getFirstChildElement();
		final boolean bAlwaysFit = isWildCard(elementName) && isWildCard(nameSpaceURI);
		while (e != null)
		{
			if ((bAlwaysFit || e.fitsName_KElement(elementName, nameSpaceURI)) && (bHasNoMap || e.includesAttributes(mAttrib, bAnd)))
			{
				// this guy is the one
				v.add(e);
				if (maxSize > 0 && v.size() == maxSize)
				{
					return v;
				}
			}
			final int maxSizeRecurse = maxSize > 0 ? maxSize - v.size() : maxSize;
			final VElement v2 = e.getChildrenByTagName_KElement(elementName, nameSpaceURI, mAttrib, bDirect, bAnd, maxSizeRecurse);
			v.addAll(v2);

			if (maxSize > 0 && v.size() >= maxSize)
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
	 * @param s the local name of the elements to match on
	 * @param nameSpaceURI the namespace URI of the elements to match on
	 * @return VElement a new NodeList object containing all the matched Elements
	 * @default getElementsByTagName_KElement(s, null)
	 */
	public VElement getElementsByTagName_KElement(String s, final String nameSpaceURI)
	{
		VElement vEle = null;
		if (s == null)
		{
			s = JDFCoreConstants.STAR;
		}

		if ((nameSpaceURI == null) || nameSpaceURI.equals(JDFCoreConstants.EMPTYSTRING))
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
	 * @param nodeName elementname you are searching for
	 * @param attName attributes you are looking for
	 * @param nameSpaceURI nameSpace you are searching for
	 * @param attVal value of the attribute you are searching for
	 * @param bDirect if true : return only direct children if false : search recursively
	 * @return VElement - vector with all found elements
	 * @default GetChildrenWithAttribute(nodeName, attName, null, null, true)
	 * @deprecated use getChildrenByTagName(nodeName, nameSpaceURI, new JDFAttributeMap(attName, attVal), bDirect, true, 0);
	 */
	@Deprecated
	public VElement getChildrenWithAttribute(final String nodeName, final String attName, final String nameSpaceURI, final String attVal, final boolean bDirect)
	{
		return getChildrenByTagName(nodeName, nameSpaceURI, new JDFAttributeMap(attName, attVal), bDirect, true, 0);
	}

	/**
	 * Get the actual element, create if not there
	 *
	 * @param nodeName name of the node from the element
	 * @return KElement the requested element
	 */
	public KElement getCreateElement(final String nodeName)
	{
		return getCreateElement(nodeName, null, 0);
	}

	/**
	 * Get a vector of all IDs that occur multiple times
	 *
	 * @param JDFCoreConstants name of the attribute to test for
	 * @return VString the list of multiply occurring ID values, null if all is well
	 */
	public VString getMultipleIDs(final String JDFCoreConstants)
	{
		final VString vRet = new VString();
		getMultipleIDs(JDFCoreConstants, vRet, new HashSet<String>());
		return vRet.isEmpty() ? null : vRet;
	}

	/**
	 * Get a vector of all IDs that occur multiple times
	 *
	 * @param JDFCoreConstants name of the attribute to test for
	 * @param vRet used for recursion; should be null
	 * @param setID used for recursion; should be null
	 */
	private void getMultipleIDs(final String JDFCoreConstants, final VString vRet, final Set<String> setID)
	{
		final String id = getAttribute_KElement(JDFCoreConstants, null, null);
		if (id != null)
		{
			if (setID.contains(id))
			{
				vRet.appendUnique(id);
			}
			else
			{
				setID.add(id);
			}
		}
		KElement child = getFirstChildElement();
		while (child != null)
		{
			child.getMultipleIDs(JDFCoreConstants, vRet, setID);
			child = child.getNextSiblingElement();
		}
	}

	/**
	 * Get the actual element, create if not there
	 *
	 * @param nodeName name of the node from the element
	 * @param nameSpaceURI the name of the namespaceURI
	 * @param iSkip which one you want
	 * @return KElement the requested element
	 * @default getCreateElement(nodeName, null, 0)
	 */
	public KElement getCreateElement(final String nodeName, final String nameSpaceURI, final int iSkip)
	{
		final KElement e = getElement(nodeName, nameSpaceURI, iSkip);
		return e == null ? appendElement(nodeName, nameSpaceURI) : e;
	}

	/**
	 * Gets the iSkip-th child node with matching nodeName and nameSpaceURI, optionally creates it if it doesn't exist. <br>
	 * If iSkip is more than one larger that the number of elements only one is appended
	 *
	 * @param nodeName name of the child node to get
	 * @param nameSpaceURI namespace to search for
	 * @param iSkip number of matching child nodes to skip
	 * @return KElement the matching child element
	 */
	public KElement getCreateElement_KElement(final String nodeName, final String nameSpaceURI, final int iSkip)
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
	 * @param nodeName Name of the node
	 * @return KElement - the child node
	 * @default getElement_KElement(nodeName, null, 0)
	 */
	public KElement getElement(final String nodeName)
	{
		return getElement(nodeName, null, 0);
	}

	/**
	 * Gets an existing iSkip-th child node with matching nodeName and nameSpaceURI
	 *
	 * @param nodeName name of the child node to get
	 * @param nameSpaceURI namespace to search for
	 * @param iSkip number of matching child nodes to skip
	 * @return KElement the matching child element
	 * @default getElement_KElement(nodeName, null, 0)
	 */
	public KElement getElement(final String nodeName, final String nameSpaceURI, final int iSkip)
	{
		return getElement_KElement(nodeName, nameSpaceURI, iSkip);
	}

	/**
	 * performance enhanced function to access multiple elements e.g. by ID get a HashMap of key= attribute value, object=element
	 *
	 * @param elementName the names of the elements, wildcard if null
	 * @param elementNS the namespace URI of the elements, any if null
	 * @param attName the attribute name - MUST not be null
	 * @return a hashmap of the matching elements
	 */
	public HashMap<String, KElement> getElementHashMap(final String elementName, final String elementNS, final String attName)
	{
		final HashMap<String, KElement> m = new HashMap<>();
		final List<KElement> v = getChildArray_KElement(elementName, elementNS, new JDFAttributeMap(attName, (String) null), true, 0);
		for (final KElement e : v)
		{
			m.put(e.getAttribute(attName), e);
		}
		return m;
	}

	/**
	 * getElement - Get the actual element
	 *
	 * @param nodeName Name of the node
	 * @param nameSpaceURI Name of the namespaceURI to search in
	 * @param iSkip number of element to get, if negative count backwards (-1 is the last)
	 * @return KElement the child node
	 * @default getElement_KElement(nodeName, null, 0)
	 */
	public KElement getElement_KElement(final String nodeName, final String nameSpaceURI, int iSkip)
	{
		KElement kElem = getFirstChildElement();
		int i = 0;
		if (iSkip < 0)
		{
			iSkip = numChildElements_KElement(nodeName, nameSpaceURI) + iSkip;
		}

		if (iSkip < 0)
		{
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
	 * getElement - Get the actual element by java class
	 *
	 * @param <A> the data type to return
	 * @param clazz java class of the requested element
	 * @param iSkip number of element to get, if negative count backwards (-1 is the last)
	 * @param bRecurse if true recurse sub elements
	 * @return KElement the child node
	 *
	 */
	@SuppressWarnings("unchecked")
	public <A extends KElement> A getElementByClass(final Class<A> clazz, int iSkip, final boolean bRecurse)
	{
		if (iSkip < 0)
		{
			final List<A> v = getChildArrayByClass(clazz, bRecurse, 0);
			iSkip = v.size() + iSkip;
			return iSkip >= 0 ? v.get(iSkip) : null;
		}
		else if (bRecurse)
		{
			final MyPair<A, Integer> mp = getElementByClassImpl(clazz, iSkip);
			return mp == null ? null : mp.a;
		}
		else
		{
			KElement n = getFirstChildElement();
			int i = 0;
			while (n != null)
			{
				if (clazz.isInstance(n))
				{
					if (iSkip == i++)
					{
						return (A) n;
					}
				}
				n = n.getNextSiblingElement();
			}
		}
		return null;
	}

	/**
	 * recursion implementation
	 *
	 * @param clazz
	 * @param iSkip
	 * @return a pair of either the return value or the number of elements skipped in case we want later
	 */
	@SuppressWarnings("unchecked")
	private <A extends KElement> MyPair<A, Integer> getElementByClassImpl(final Class<A> clazz, final int iSkip)
	{
		KElement n = getFirstChildElement();
		int i = 0;
		while (n != null)
		{
			if (clazz.isInstance(n) && (iSkip == i++))
			{
				return new MyPair<>((A) n, Integer.valueOf(i));
			}

			final MyPair<A, Integer> ret = n.getElementByClassImpl(clazz, iSkip - i);
			if (ret != null)
			{
				if (ret.a != null)
				{
					return ret;
				}
				i += ret.b;
			}
			n = n.getNextSiblingElement();
		}

		return i == 0 ? null : new MyPair<A, Integer>(null, Integer.valueOf(i));
	}

	/**
	 * Get the n'th Ancestor node with name parentNode
	 *
	 * @param parentNode name of the parent node to search for
	 * @param depth which one you want to have in order of appearance
	 * @return KElement the n'th ancestor node with name nodeName
	 * @default getDeepParent(parentNode, 0)
	 */
	public KElement getDeepParent(final String parentNode, final int depth)
	{
		final KElement parentElement = getParentNode_KElement();
		if (!getLocalName().equals(parentNode) && !isWildCard(parentNode))
		{
			return (parentElement == null) ? null : parentElement.getDeepParent(parentNode, depth);
		}
		else if (depth > 0)
		{
			if (parentElement == null)
			{
				return getDeepParent(parentNode, 0);
			}
			// last in chain or leaving structure
			if (isWildCard(parentNode) || parentNode.equals(parentElement.getLocalName()))
			{
				return parentElement.getDeepParent(parentNode, depth - 1);
			}
		}
		return this;
	}

	/**
	 * Get the ancestor which may have one of the node names defined in parentNode
	 *
	 * @param vParentElement vector with node names to search for
	 * @param depth which one you want to have (in order of appearance)
	 * @return KElement the first ancestor node with name nodeName
	 * @deprecated - loop over the single node method
	 */
	@SuppressWarnings("rawtypes")
	@Deprecated
	public KElement getDeepParent(final Vector vParentElement, final int depth)
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
		return (KElement) getOwnerDocument().getDocumentElement();
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
	 * @return VString: a vector of all attribute keys in 'this'
	 */
	public VString getAttributeVector_KElement()
	{
		final VString v = new VString();
		final Collection<String> c = getAttributeArray_KElement();
		v.addAll(c);
		return v;
	}

	/**
	 * Gets all attribute keys of 'this' as a vector of strings
	 *
	 * @return VString: a vector of all attribute keys in 'this'
	 */
	public List<String> getAttributeArray_KElement()
	{
		final List<String> v = new ArrayList<>();
		final NamedNodeMap nm = getAttributes();
		if (nm != null)
		{
			final int siz = nm.getLength();

			for (int i = 0; i < siz; i++)
			{
				final Node a = nm.item(i);
				v.add(a.getNodeName());
			}
		}

		return v;
	}

	/**
	 * looking for a specified target with an id, e.g. resource.<br>
	 * Offers access to exactly KElements implementation of GetTarget even if called for an instance of one of it's subclasses.
	 * <p>
	 * default: getTarget(id, JDFCoreConstants.ID)
	 *
	 * @param id value of the ID tag to search
	 * @param attrib name of the ID tag, defaults to "ID"
	 * @return KElement - the element if existing, otherwise <code>null</code>
	 */
	public KElement getTarget(final String id, final String attrib)
	{
		return getTarget_KElement(id, attrib);
	}

	/**
	 * Gets the target of link. Follows an ID-IDREF pair by recursively searching for an attrib with the value id
	 *
	 * @param id value of the ID tag to search
	 * @param attrib name of the ID tag, defaults to "ID"
	 * @return KElement the target of link - the element node.
	 */
	public KElement getTarget_KElement(final String id, String attrib)
	{
		if (id == null || id.equals(JDFCoreConstants.EMPTYSTRING))
		{
			return null;
		}
		if (StringUtil.getNonEmpty(attrib) == null)
			attrib = JDFCoreConstants.ID;
		final KElement docRoot = getDocRoot();
		if (docRoot.includesAttribute(attrib, id))
			return docRoot;
		return docRoot.getChildWithAttribute(null, attrib, null, id, 0, false);
	}

	/**
	 * Returns a vector which contains the childs of the actual element. But every child only once.
	 *
	 * @return Vector vector with the childs of the actual element. Ever child typ is only added once.
	 */
	public VString getElementNameVector()
	{
		final VElement vChildElem = getChildElementVector(null, null, null, true, 0, false);
		final VString v = new VString();

		for (final KElement e : vChildElem)
		{
			v.add(e.getNodeName());
		}
		v.unify();
		return v;
	}

	/**
	 * get the first child element
	 *
	 * @param parent the node to get the first element node from
	 * @return Element - the first child element if existing otherwise null
	 * @deprecated use elem.getFirstChildElement
	 */
	@Deprecated
	public static Element getFirstElementNode(final Element parent)
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
	 * @param elem the Element to get the next element node from
	 * @return Element the first sibling element if existing otherwise null
	 * @deprecated - use elem.getNextSiblingElement();
	 */
	@Deprecated
	public static Element getNextElementNode(final Element elem)
	{
		Node nextSiblingElement = elem.getNextSibling();

		while (nextSiblingElement != null && nextSiblingElement.getNodeType() != Node.ELEMENT_NODE)
		{
			nextSiblingElement = nextSiblingElement.getNextSibling();
		}

		return (Element) nextSiblingElement;
	}

	/**
	 * Checks if the contents of this element are equal to element kElem<br/>
	 * differs from @see equals because nodes that are in different locations or documents but have the same name, attributes and elements are considered equal
	 *
	 * @param kElem the element to compare
	 * @return boolean true if equal
	 */
	public boolean isEqual(final KElement kElem)
	{
		if (kElem == null)
		{
			return false;
		}
		if (this.equals(kElem))
		{
			return true;
		}
		if (numChildNodes(0, false) != kElem.numChildNodes(0, false))
		{
			return false;
		}
		// performance: count attributes and compare
		if (numChildNodes(ATTRIBUTE_NODE, false) != kElem.numChildNodes(ATTRIBUTE_NODE, false))
		{
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

		final NamedNodeMap atts = getAttributes();
		final NamedNodeMap katts = kElem.getAttributes();
		final int length = atts.getLength();
		if (length != katts.getLength())
		{
			return false;
		}
		for (int i = 0; i < length; i++)
		{
			final Node node = atts.item(i);
			final String key = node.getNodeName();
			final Node kNode = katts.getNamedItem(key);
			if (kNode == null)
				return false;
			if (!key.equals(kNode.getNodeName()))
				return false;
			if (!node.getNodeValue().equals(kNode.getNodeValue()))
				return false;
		}

		if (!ContainerUtil.equals(getText(), kElem.getText()))
			return false;

		final List<KElement> l1 = getChildArray_KElement(null, null, null, true, 0);
		final List<KElement> l2 = kElem.getChildArray_KElement(null, null, null, true, 0);

		final int l1Size = l1.size();
		if (l1Size != l2.size())
		{
			return false;
		}
		for (int i = 0; i < l1Size; i++)
		{
			final KElement kNode1 = l1.get(i);
			final KElement kNode2 = l2.get(i);

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
	 * checks if KElement child is ancestor or not
	 *
	 * @param child child to check
	 * @return boolean true if anchestor
	 */
	public boolean isAncestor(final KElement child)
	{
		return ancestorDistance(child) >= 0;
	}

	/**
	 * distance to ancestor (0=this)
	 *
	 * @param child child to check
	 * @return int distance to ancestor, -1 if no direct descendant
	 */
	public int ancestorDistance(final KElement child)
	{
		if (child != null)
		{
			if (child == this)
			{
				return 0;
			}
			final int ancestorDistance = ancestorDistance(child.getParentNode_KElement());
			return ancestorDistance < 0 ? ancestorDistance : 1 + ancestorDistance;
		}
		return -1;
	}

	/**
	 * Get the number of child elements. If String 'node' is an empty string or '*', all nodes are counted.
	 * <p>
	 * default: numChildElements(null, null)
	 *
	 * @param node the nodes with name 'node' to count
	 * @param nameSpaceURI the nameSpace to look in
	 * @return int the number of matching child elements
	 */
	public int numChildElements(final String node, final String nameSpaceURI)
	{
		return numChildElements_KElement(node, nameSpaceURI);
	}

	/**
	 * Get the number of child elements of a certain class
	 * <p>
	 *
	 * @param clazz the child class
	 * @param bRecurse if true search the tree
	 *
	 * @return int the number of matching child elements
	 */
	public int numChildrenByClass(final Class<?> clazz, final boolean bRecurse)
	{
		int i = 0;
		Node n = getFirstChild();
		while (n != null)
		{
			if (clazz.isInstance(n))
			{
				i++;
			}
			if (bRecurse && (n instanceof KElement))
			{
				i += ((KElement) n).numChildrenByClass(clazz, true);
			}
			n = n.getNextSibling();
		}
		return i;
	}

	/**
	 * Get the number of child elements. If String 'node' is null, an empty string or '*', all nodes are counted. This method is the same as numChildElements but prevents before the maybe unwanted
	 * virtuality of numChildElements.
	 * <p>
	 * default: numChildElements_KElement(null, null)
	 *
	 * @param node the nodes with name 'node' to count
	 * @param nameSpaceURI the nameSpace to look in
	 * @return int the number of matching child elements
	 */
	public int numChildElements_KElement(final String node, final String nameSpaceURI)
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
	 * @param node name of the child node to remove, if empty; or "*" removes the n'th element
	 * @param nameSpaceURI namespace to search in
	 * @param n number of nodes to skip before deleting
	 * @return KElement the removed element
	 */
	public KElement removeChild(final String node, final String nameSpaceURI, final int n)
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
	 * @param s elementname you are searching for
	 * @param nameSpaceURI nameSpace you are searching for
	 * @param index if more then one child match the condition you can specify which one you want to have via the index
	 * @param mAttrib attributes you are lokking for
	 * @param bDirect if true return value is directly the elemement. Otherwise the return value is the node of the found element. Only direct child, not grandchild etc.
	 * @param bAnd if true, a child is only returned if it has all attributes specified in mAttrib
	 * @return KElement the found child (element or node), null if index < 0 or index < number of matching children
	 */
	public KElement getChildByTagName(final String s, final String nameSpaceURI, int index, final JDFAttributeMap mAttrib, final boolean bDirect, final boolean bAnd)
	{
		final VElement v = getChildrenByTagName(s, nameSpaceURI, mAttrib, bDirect, bAnd, index + 1);
		if (index < 0)
			index += v.size();
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
	 * @param nodeName name of the child node to get, if empty or null remove all
	 * @param nameSpaceURI namespace to search in
	 * @deprecated use three parameter version removeChildren(nodeName, nameSpaceURI, null);
	 */
	@Deprecated
	public void removeChildren(final String nodeName, final String nameSpaceURI)
	{
		removeChildren(nodeName, nameSpaceURI, null);
	}

	/**
	 * Removes the children named <code>nodeName</code> in the namespace <code>nameSpaceURI</code> from the parent element
	 * <p>
	 * default: removeChildren(null,null,null)
	 *
	 * @param nodeName name of the element typ to remove
	 * @param nameSpaceURI namespace in which the elements are to be removed
	 * @param mAttrib map of attributes to match
	 */
	public void removeChildren(final String nodeName, final String nameSpaceURI, final JDFAttributeMap mAttrib)
	{
		final VElement v = getChildElementVector(nodeName, nameSpaceURI, mAttrib, true, 0, false);
		for (final KElement e : v)
		{
			removeChild(e);
		}
	}

	/**
	 * Removes the attribute value from its value list <br>
	 * Removes the contents of value from the existing attribute key. Deletes the attribute Key, if it has no value.<br>
	 * <code>removeFromAttribute("key","next","",",", -1)</code> applied to <code><xml key="first,next"/></code> results in <code><xml key="first"/></code>
	 *
	 * @param key attribute key
	 * @param token string to remove
	 * @param nameSpaceURI namespace of attribute key
	 * @param sep separator between the values
	 * @param nMax maximum number of value instances to remove (-1 = all)
	 * @return int number of removed instances
	 */
	public int removeFromAttribute(final String key, final String token, final String nameSpaceURI, final String sep, final int nMax)
	{
		String strAttrValue = getAttribute_KElement(key, nameSpaceURI, null);
		if (strAttrValue == null || token == null)
			return 0;

		int n = 0;
		int posOfToken = StringUtil.posOfToken(strAttrValue, token, sep, 0);
		while (posOfToken >= 0)
		{
			strAttrValue = StringUtil.replaceToken(strAttrValue, posOfToken, sep, null);
			n++;
			posOfToken = StringUtil.posOfToken(strAttrValue, token, sep, 0);
		}

		// we had a change - update
		if (n > 0)
		{
			strAttrValue = strAttrValue == null ? null : strAttrValue.trim();
			setAttribute(key, StringUtil.getNonEmpty(strAttrValue), nameSpaceURI);
		}

		return n;
	}

	/**
	 * Flush - remove all attributes, elements and text, leaving only a stub tag
	 *
	 * @return boolean true always
	 */
	public boolean flush()
	{
		Node node = getFirstChild();
		while (node != null)
		{
			final Node next = node.getNextSibling();
			removeChild(node);
			node = next;
		}
		removeAttributes(null);
		return true;
	}

	/**
	 * Get all or the spezified number of childs nodes from the actual element a maxSize of 0 spezifies all children.
	 * <p>
	 * default: getChildNodeVector(0)
	 *
	 * @param maxSize
	 * @return Vector vector with all found nodes
	 * @deprecated
	 */
	@SuppressWarnings({ "rawtypes", "unchecked" })
	@Deprecated
	public Vector getChildNodeVector(final int maxSize)
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
			}
			while (++i != maxSize && node != null);
		}

		return v;
	}

	/**
	 * Removes all attributes spezified in attribs. If attribs is empty, all attributes are removed
	 *
	 * @param attribs list of attributes to remove, if null, remove all
	 */
	public void removeAttributes(final Collection<String> attribs)
	{
		if (attribs == null)
		{
			final NamedNodeMap nm = getAttributes();
			if (nm != null)
			{
				final int siz = nm.getLength();
				for (int i = siz - 1; i >= 0; i--)
				{
					removeAttribute(nm.item(i).getNodeName());
				}
			}
		}
		else
		{
			for (final String attrib : attribs)
			{
				removeAttribute(attrib);
			}
		}
	}

	/**
	 * remove all empty attributes from this (e.g. att="")
	 *
	 * @param bRecurse if true, alse recurse subelements, else only local
	 */
	public void eraseEmptyAttributes(final boolean bRecurse)
	{
		final NamedNodeMap nm = getAttributes();
		if (nm != null)
		{
			final int siz = nm.getLength();

			for (int i = siz - 1; i >= 0; i--)
			{
				final Node item = nm.item(i);
				if (item.getNodeValue().equals(JDFCoreConstants.EMPTYSTRING))
				{
					removeAttribute(item.getNodeName());
				}
			}
		}

		if (bRecurse)
		{
			KElement e = getFirstChildElement();
			while (e != null)
			{
				e.eraseEmptyAttributes(true);
				e = e.getNextSiblingElement();
			}
		}
	}

	/**
	 * You can get the iSkip element named by nodeName. If there is no element present, a new empty element is returned. If iSkip is out of range, a new element is returned.
	 * <p>
	 * default: getDeepElement(nodeName, null, 0)
	 *
	 * @param nodeName the type of element you want to get
	 * @param nameSpaceURI the namespace to search in !!! NOT USED IN FUCTION !!!
	 * @param iSkip which element you want to have (order of appearance)
	 * @return KElement the iSkip element or a new element
	 * @deprecated use getChildByTagName(nodeName, nameSpaceURI, iSkip, null, false, true);
	 */
	@Deprecated
	public KElement getDeepElement(final String nodeName, final String nameSpaceURI, final int iSkip)
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
	public KElement getChildFromList(final VString nodeNames, final int iSkip)
	{
		return getChildFromList(nodeNames, iSkip, null, true);
	}

	/**
	 * Get any Child that matches a string defined in nodeNames. The method compares the strings with the element name
	 * <p>
	 * default: getChildFromList(nodeNames, 0, null)
	 *
	 * @param nodeNames container for the node name string
	 * @param iSkip how many of the found child should be skiped
	 * @param map map of attributes to match
	 * @param bDirect
	 * @return KElement a child matching the condition
	 */
	public KElement getChildFromList(final VString nodeNames, final int iSkip, final JDFAttributeMap map, final boolean bDirect)
	{
		int i = 0;
		KElement kElem = getFirstChildElement();
		while (kElem != null)
		{
			if (nodeNames.contains(kElem.getLocalName()))
			{
				if (map == null || kElem.includesAttributes(map, true))
				{
					if (i++ >= iSkip)
					{
						return kElem; // this guy is the one
					}
				}
			}
			if (!bDirect)
			{
				int j = 0;
				KElement e2 = null;
				do
				{
					e2 = kElem.getChildFromList(nodeNames, j, map, bDirect);
					if (e2 != null)
					{
						if (i++ >= iSkip)
						{
							return e2;
						}
						j++;
					}
				}
				while (e2 != null);
			}
			kElem = kElem.getNextSiblingElement();
		}
		return null;
	}

	/**
	 * Rename the element with the String newName. Attention. the Java class of the element does not get modified. It is up to the caller to ensure that no inconsistent types get created with rename
	 * <p>
	 * default: renameElement(newName, null)
	 *
	 * @param newName the new name of the actual element
	 * @param nameSpaceURI the new nameSpace, ignored if null
	 * @return KElement the renamed child, i.e. this
	 */
	public KElement renameElement(final String newName, final String nameSpaceURI)
	{
		this.name = newName;
		this.localName = xmlnsLocalName(newName);
		if (nameSpaceURI != null)
		{
			this.namespaceURI = nameSpaceURI;
		}
		return this;
	}

	/**
	 * remove all elements and attributes of a given namespace
	 *
	 * @param nsURI the ns uri of the extensions to remove
	 */
	public void removeExtensions(final String nsURI)
	{
		if (nsURI == null)
		{
			return;
		}

		KElement n = getFirstChildElement();
		while (n != null)
		{
			final KElement next = n.getNextSiblingElement(); // get next prior to
			// zapping
			final String nsuri = n.getNamespaceURI();
			if (nsURI.equals(nsuri))
			{
				removeChild(n);
			}
			else
			{
				n.removeExtensions(nsURI);
			}

			n = next;
		}

		final NamedNodeMap nm = getAttributes();
		if (nm != null)
		{
			final int siz = nm.getLength();
			for (int i = siz - 1; i >= 0; i--)
			{
				final Node na = nm.item(i);
				final String nsuri = na.getNamespaceURI();
				if (nsURI.equals(nsuri))
				{
					removeAttributeNode((Attr) na);
				}
			}
		}
	}

	/**
	 * moves this to a position before another child, fails if either this or beforechild are document roots
	 *
	 * @param beforeChild the child to move before, if beforechild is a the document root, do nothing if null, move me to the end of my parent
	 * @return this after moving, null if failure
	 */
	public KElement moveMe(final KElement beforeChild)
	{
		final KElement parent = beforeChild == null ? getParentNode_KElement() : beforeChild.getParentNode_KElement();
		if (beforeChild == this)
		{
			return this;
		}
		if (parent == null || getParentNode_KElement() == null)
		{
			return null;
		}
		return parent.moveElement(this, beforeChild);
	}

	/**
	 * Moves src node (including all attributes and subelements) from its parent node into 'this' and inserts it in front of beforeChild, if it exists. Otherwise appends src to 'this'.<br>
	 * If src is <code>null</code>, an empty KElement is returned.<br>
	 * src is removed from its parent node. if the actual document owner is the same as the document owner of src, src is appended to 'this' If the documents are different, then src is appended to
	 * 'this' in the actual document.
	 * <p>
	 * default: moveElement(src, null)
	 *
	 * @param src node to move.
	 * @param beforeChild child of 'this' to insert src before. If beforeChild is null, src is appended to 'this'
	 * @return KElement src element after moving, null if src is null
	 * @throws JDFException if beforeChild is not a child of 'this'
	 */
	public KElement moveElement(final KElement src, final KElement beforeChild)
	{
		if (src != null)
		{
			if (src == beforeChild)
			{
				return src; // nop
			}
			if (src.isAncestor(this))
			{
				return null; // snafu when moving a to b in a/b
			}
			final Node parentNode = src.getParentNode();
			Node srcElement = parentNode == null ? src : parentNode.removeChild(src);

			if (beforeChild != null && beforeChild.getParentNode() != this)
			{
				throw new JDFException("KElement.moveElement" + " beforeChild is not child of this");
			}

			if (src.getOwnerDocument() != getOwnerDocument())
			{
				src.clearTargets();

				final KElement dest = (KElement) getOwnerDocument().importNode(srcElement, true);
				dest.fixParent(this, dest);
				srcElement = dest;
			}

			if (beforeChild == null)
			{
				return (KElement) appendChild(srcElement);
			}
			else
			{
				return (KElement) insertBefore(srcElement, beforeChild);
			}
		}

		return null;
	}

	protected void clearTargets()
	{
		// nop
	}

	/**
	 *
	 * @param v
	 * @param beforeChild
	 * @deprecated use moveArray
	 */
	@Deprecated
	public void moveElements(final VElement v, final KElement beforeChild)
	{
		moveArray(v, beforeChild);
	}

	/**
	 * move all children in a vector of elements in the order of the vector
	 *
	 * @param v the vector of elements to append, if null nothing happens
	 * @param beforeChild the child before which to append the elements of the vector
	 */
	public void moveArray(final Collection<KElement> v, final KElement beforeChild)
	{
		if (v == null)
		{
			return;
		}
		for (final KElement e : v)
		{
			moveElement(e, beforeChild);
		}
	}

	/**
	 * Erases all empty text nodes in 'this' and its subelements If there any empty text nodes removes them. If bTrimWhite is true, then trims white spaces from both ends of a text node and only then,
	 * if it is empty, removes it
	 *
	 * @param bTrimWhite trims whitespace of text, default = true
	 * @return int the number of removed nodes
	 */
	public int eraseEmptyNodes(final boolean bTrimWhite)
	{
		int nRemove = 0;

		Node n = getFirstChild();
		while (n != null)
		{
			final Node nNext = n.getNextSibling(); // must set nNew prior to potentially deleting n...
			final short nodeType = n.getNodeType();

			if (nodeType == Node.TEXT_NODE)
			{
				String s = n.getNodeValue();
				if (bTrimWhite)
				{
					s = s.trim();
				}
				if (s.equals(JDFCoreConstants.EMPTYSTRING))
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
				// 040302 RP do not erase empty elements - they may have a sementic meaning
			}
			n = nNext;
		}

		return nRemove;
	}

	/**
	 * Copies src node (including all attributes and subelements) and inserts the copy into 'this' in front of beforeChild, if it exists. Otherwise appends src node to 'this'.
	 * <p>
	 * default: copyElement(src, null)
	 *
	 * @param src node to copy.
	 * @param beforeChild child of 'this' to insert src before. If null, src is appended
	 * @return KElement the copied element, <code>null</code> if src is <code>null</code>.
	 */
	public KElement copyElement(final KElement src, final KElement beforeChild)
	{
		if (src == null)
		{
			return null;
		}
		synchronized (this)
		{
			return (KElement) copyNode(src, beforeChild);
		}
	}

	/**
	 * same as copyElement but over any node type
	 *
	 *
	 * @param src
	 * @param beforeChild
	 * @return
	 */
	private Node copyNode(final Node src, final KElement beforeChild)
	{
		Node childNode = null;
		final Document od = getOwnerDocument();
		if (src.getOwnerDocument() == od)
		{
			childNode = src.cloneNode(true);
		}
		else
		{
			if (od instanceof DocumentJDFImpl)
				((DocumentJDFImpl) od).setParentNode(this);
			childNode = od.importNode(src, true);
		}

		if (beforeChild != null && beforeChild.getParentNode() != this)
		{
			throw new JDFException("KElement.copyElement: beforeChild " + beforeChild + " is not child of this: " + toString());
		}
		return insertBefore(childNode, beforeChild);
	}

	/**
	 * copies a node into this, ignoring identical node names i.e. duplicating elements
	 *
	 * @param src
	 * @param bRemove if true, remove existing information, else retain and overwrite / merge
	 * @return this
	 *
	 */
	public KElement copyInto(final KElement src, final boolean bRemove)
	{
		if (src == null || src == this)
			return this;

		if (bRemove)
		{
			removeChildren(null, null, null);
			removeAllText();
			removeAttributes(null);
		}
		setAttributes(src);
		final String srcNS = StringUtil.getNonEmpty(src.getNamespaceURI());
		if (srcNS != null)
			setNamespaceURI(srcNS);
		Node e = src.getFirstChild();
		while (e != null)
		{
			copyNode(e, null);
			e = e.getNextSibling();
		}

		return this;
	}

	/**
	 * Replaces 'this' with src. <br>
	 * If the actual document is the same as the src document, 'this' is replaced by src.<br>
	 * If the actual document and the src document are different, src is positioned at the position of 'this' in the current document and removed from the old parent document.<br>
	 *
	 * @since 130103 ReplaceElement works on all elements including the document root
	 * @param src node, that 'this' will be replaced with
	 * @return KElement the replaced element. If src is null or equal 'this', src is returned.
	 */
	public KElement replaceElement(KElement src)
	{

		if (src == this)
		{
			return this; // nop
		}

		KElement kRet = src;

		if (src != null)
		{
			// workaround for the document element
			// TBD: there must be a better way
			if (getParentNode_KElement() == null)
			{
				kRet = replaceElement_isDocRoot(src);
			}
			else
			{
				final KElement srcParentNode = src.getParentNode_KElement();
				if (srcParentNode != null)
				{
					// removes the resource from the to merge document
					src = (KElement) srcParentNode.removeChild(src);
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
					final KElement dn = (KElement) getOwnerDocument().importNode(src, true);
					getParentNode_KElement().replaceChild(dn, this);
					fixParent(dn, null);
					kRet = dn;
				}
			}
		}
		this.ownerNode = null; // I've replace this so that it remains an orphan
		// in the document, but has no owner node
		return kRet;
	}

	/*
	 * used in replaceElement
	 */
	private KElement replaceElement_isDocRoot(final KElement src)
	{
		if (!ContainerUtil.equals(getNodeName(), src.getNodeName()) || !ContainerUtil.equals(getNamespaceURI(), src.getNamespaceURI()))
		{
			renameElement(src.getNodeName(), src.getNamespaceURI());
		}
		flush();
		setAttributes(src.getAttributeMap());
		final VElement children = src.getChildElementVector(null, null, null, true, 0, false);

		for (int iv = 0; iv < children.size(); iv++)
		{
			copyElement(children.elementAt(iv), null);
		}

		return this; // return the original
	}

	// ************************** end of methods needed in JDFRefElement
	// ********
	// //////////////////////////////////////////////////////////////////////////
	// ************************** start of methods needed in JDFResourceLink
	// ****

	/**
	 * Get a long attribute if present
	 * <p>
	 * default: getLongAttribute(attrib, null, 0)
	 *
	 * @param attrib attribute to parse for an integer attribute
	 * @param nameSpaceURI nameSpaceURI to search in
	 * @param def the default to return if the value is empty or the attribute is not set
	 * @return the parsed int. If the attribute was not found, <code>long def</code> is returned.
	 */
	public long getLongAttribute(final String attrib, final String nameSpaceURI, final long def)
	{
		final String s = getAttribute(attrib, nameSpaceURI, null);
		return StringUtil.parseLong(s, def);
	}

	/**
	 * Get a integer attribute if present
	 * <p>
	 * default: getIntAttribute(attrib, null, 0)
	 *
	 * @param attrib attribute to parse for an integer attribute
	 * @param nameSpaceURI nameSpaceURI to search in
	 * @param def the default to return if the value is not set or the attribute does not exists
	 * @return int the parsed int. If the attribute was not found int def is returned
	 */
	public int getIntAttribute(final String attrib, final String nameSpaceURI, final int def)
	{
		final String s = getAttribute(attrib, nameSpaceURI, null);
		return StringUtil.parseInt(s, def);
	}

	/**
	 * get a boolean attribute
	 * <p>
	 * default: getBoolAttribute(attrib, null, false)
	 *
	 * @param attrib attribute to parse for a boolean value
	 * @param nameSpaceURI nameSapceURI to search in
	 * @param def the default to return if value is not set or the attribute does not exists
	 * @return boolean the boolean value or the def parameter
	 */
	public boolean getBoolAttribute(final String attrib, final String nameSpaceURI, final boolean def)
	{
		final String s = getAttribute(attrib, nameSpaceURI, null);
		return StringUtil.parseBoolean(s, def);
	}

	/**
	 * get a double attribute
	 * <p>
	 * default: getRealAttribute(attrib, null, 0.0)
	 *
	 * @param attrib attribute to parse for a boolean value
	 * @param nameSpaceURI nameSapceURI to search in
	 * @param def default to return if none value is set or attribute does not exist
	 * @return double the double value or <code>def</code>
	 */
	public double getRealAttribute(final String attrib, final String nameSpaceURI, final double def)
	{
		final String s = getAttribute(attrib, nameSpaceURI, null);
		return StringUtil.parseDouble(s, def);
	}

	/**
	 * Get the Ancestor node with name other than thisNode
	 *
	 * @param thisNode serch string
	 * @return KElement parent node element
	 */
	public KElement getDeepParentNotName(final String thisNode)
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

	// ************************** end of methods needed in JDFResourceLink
	// ******
	// //////////////////////////////////////////////////////////////////////////
	// ************************** start of methods needed in JDFPartAmount
	// ******

	/**
	 * Get the first child of parentNode with name parentNode
	 *
	 * @param parentNode node name to search for
	 * @return KElement the matching child of the parent element
	 */
	public KElement getDeepParentChild(final String parentNode)
	{
		final KElement kElem = getParentNode_KElement();

		if (kElem != null && parentNode != null)
		{
			if (!kElem.getNodeName().equals(parentNode))
			{
				return kElem.getDeepParentChild(parentNode);
			}
			return this;
		}
		return null;
	}

	// ************************** end of methods needed in JDFPartAmount
	// ********
	// //////////////////////////////////////////////////////////////////////////
	// ************************** start of methods needed in JDFResponse
	// ********

	/**
	 * append a DOM comment <code>&lt;!-- XMLComment --&gt;</code> The double minus sign '--' is escaped with an underscore '_' in order to ensure valid xml
	 *
	 * @param commentText the comment to append
	 * @deprecated use appendXMLComment(commentText, null);
	 */
	@Deprecated
	public void appendXMLComment(final String commentText)
	{
		appendXMLComment(commentText, null);
	}

	/**
	 * append a DOM comment <code>&lt;!-- XMLComment --&gt;</code> The double minus sign '--' is escaped with an underscore '_' in order to ensure valid xml
	 *
	 * @param commentText the comment to append
	 * @param beforeChild the child of this that the Comment should be appended before. if null, append the Comment
	 * @return the newly created xml comment
	 */
	public Node appendXMLComment(String commentText, final KElement beforeChild)
	{
		commentText = StringUtil.replaceString(commentText, "--", "__");
		final Comment newChild = getOwnerDocument().createComment(commentText);
		insertBefore(newChild, beforeChild);
		return newChild;
	}

	/**
	 * set a DOM comment <code>&lt;!-- XMLComment --&gt;</code> in front of <code>this</code> if an xml Comment node already exists directly in front of <code>this</code>, the previous comment is
	 * removed The double minus sign '--' is escaped with an underscore '_' in order to ensure valid xml
	 *
	 * @param commentText the comment text to set
	 */
	public void setXMLComment(String commentText)
	{
		if (commentText == null)
			return;
		final KElement e = getParentNode_KElement();
		if (e == null)
		{
			commentText = StringUtil.replaceString(commentText, "--", "__");
			final Comment newChild = getOwnerDocument().createComment(commentText);
			getOwnerDocument().insertBefore(newChild, this);
			final Node last = newChild.getPreviousSibling();
			if (last != null && last.getNodeType() == Node.COMMENT_NODE)
			{
				getOwnerDocument().removeChild(last);
			}
		}
		else
		{
			final Node last = getPreviousSibling();
			e.appendXMLComment(commentText, this);
			if (last != null && last.getNodeType() == Node.COMMENT_NODE)
			{
				e.removeChild(last);
			}

		}
	}

	/**
	 * Appends XML CData section <code>&lt;![CDATA[ CData Section ]]&gt;</code> some character data <br>
	 * Appends a new CData section child node to the end of 'this '
	 *
	 * @param cDataText the text of the XML CData section to append
	 */
	public void appendCData(final String cDataText)
	{
		final CDATASection newChild = getOwnerDocument().createCDATASection(cDataText);
		appendChild(newChild);
	}

	/**
	 * Appends XML CData section <code>&lt;![CDATA[ CData Section ]]&gt;</code> some character data <br>
	 * Appends a new CData section child node to the end of 'this '
	 *
	 * @param cDataElem the element of the XML CData section to append
	 */
	public void appendCData(final KElement cDataElem)
	{
		final String s = cDataElem.toString();
		final CDATASection newChild = getOwnerDocument().createCDATASection(s);
		appendChild(newChild);
	}

	/**
	 * append some generic text
	 *
	 * @param textName the text to append
	 */
	public void appendText(final String textName)
	{
		if (StringUtil.isEmpty(textName))
			return;
		final Text newChild = getOwnerDocument().createTextNode(textName);
		appendChild(newChild);
	}

	/**
	 * appends a entity reference to the actual element
	 *
	 * @param refName the name of the entity reference
	 */
	public void appendEntityReference(final String refName)
	{
		final EntityReference newChild = getOwnerDocument().createEntityReference(refName);
		appendChild(newChild);
	}

	/**
	 * append a text element with text included
	 *
	 * @param nodeName node name of the text element
	 * @param text the text to apend
	 * @return KElement the appended text element
	 */
	public KElement appendTextElement(final String nodeName, final String text)
	{
		final KElement kElem = appendElement(nodeName, null);
		kElem.appendText(text);

		return kElem;
	}

	// ************************** end of methods needed in JDFResponse
	// **********
	// //////////////////////////////////////////////////////////////////////////
	// ************************** start of methods needed in JDFNode
	// ************

	/**
	 * merge nodes in a way that no duplicate elements are created<br>
	 * <b>attention !! this kills pools !!</b> since elements in kElem overwrite those in *this
	 *
	 * @param kElem the node element to merge with the current node
	 * @param bDelete if true KElement kElem will be deleted
	 * @return KElement the merged node element
	 */
	public KElement mergeElement(final KElement kElem, final boolean bDelete)
	{
		if (kElem == null)
		{
			return this;
		}

		final VElement v = kElem.getChildElementVector(null, null, null, true, 0, false);
		final VElement vThis = getChildElementVector(null, null, null, true, 0, false);
		for (final KElement m : v)
		{

			final int index = vThis.nameIndex(m.getNodeName(), 0);
			if (index >= 0)
			{
				final KElement em = vThis.remove(index);
				em.mergeElement(m, bDelete);
			}
			else
			{
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
		}

		setAttributes(kElem);

		return this;
	}

	/**
	 * GetChildWithAttribute - Get a child with matching attributes
	 * <p>
	 * default: getChildWithAttribute(nodeName, attName, null,attValue, 0, true)
	 *
	 * @param nodeName name of the child node to search for
	 * @param attName attribute name to search for
	 * @param nameSpaceURI namespace to search for
	 * @param attVal the attribute value to search for, Wildcard supported ( <code>null</code>)
	 * @param index if more then one child meets the condition, you can specify the one to return via an index
	 * @param bDirect if true, looks only in direct children, else search through all children, grandchildren etc.
	 * @return KElement the element which matches the above conditions
	 */
	public KElement getChildWithAttribute(final String nodeName, final String attName, final String nameSpaceURI, final String attVal, final int index, final boolean bDirect)
	{
		return getChildByTagName(nodeName, nameSpaceURI, index, new JDFAttributeMap(attName, attVal), bDirect, true);
	}

	/**
	 * GetChildWithAttribute - Get a child with matching attributes; craete it if it does not exist
	 * <p>
	 * default: getChildWithAttribute(nodeName, attName, null,attValue, 0, true)
	 *
	 * @param nodeName name of the child node to search for
	 * @param attName attribute name to search for
	 * @param nameSpaceURI namespace to search for
	 * @param attVal the attribute value to search for, Wildcard supported ( <code>null</code>)
	 * @param index if more then one child meets the condition, you can specify the one to return via an index
	 * @return KElement the element which matches the above conditions
	 */
	public KElement getCreateChildWithAttribute(final String nodeName, final String attName, final String nameSpaceURI, final String attVal, final int index)
	{
		KElement e = getChildWithAttribute(nodeName, attName, nameSpaceURI, attVal, index, true);
		if (e == null)
		{
			e = appendElement(nodeName, nameSpaceURI);
			e.setAttribute(attName, attVal);
		}
		return e;
	}

	/**
	 * Checks whether the current element has a child element NodeName
	 * <p>
	 * default: hasChildElement(String nodeName, null)
	 *
	 * @param nodeName name of the node to check for
	 * @param nameSpaceURI nameSpaceURI to search in
	 * @return boolean true if there is a child with nodeName, otherwise false
	 */
	public boolean hasChildElement(final String nodeName, final String nameSpaceURI)
	{
		return !(getElement(nodeName, nameSpaceURI, 0) == null);
	}

	/**
	 * searches for the first child element occurence in this element or any ancestors
	 * <p>
	 * default: getInheritedElement(elementName, null, 0)
	 *
	 * @param elementName name of the element to be searched
	 * @param nameSpaceURI XML-namespace
	 * @param iSkip leading siblings to be skipped
	 * @return JDFElement the element found
	 */
	public KElement getInheritedElement(final String elementName, final String nameSpaceURI, final int iSkip)
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

	// ************************** end of methods needed in JDFNode
	// **************
	// //////////////////////////////////////////////////////////////////////////
	// ************************** start of methods needed in JDFRoot
	// ************

	/**
	 * Adds a NameSpace and maps the prefix to a URI. <br>
	 * Checks all parents, whether such namespace is already defined in an ancestor
	 *
	 * @param strPrefix the namespace prefix to set
	 * @param strNameSpaceURI the namespace URI to set
	 * @return boolean true if newly set, false if already there and matching
	 */
	public boolean addNameSpace(final String strPrefix, final String strNameSpaceURI)
	{
		boolean fSuccess = false;

		final String strNameSpace = (strPrefix == null || strPrefix.length() <= 0) ? JDFCoreConstants.XMLNS : JDFCoreConstants.XMLNS + JDFCoreConstants.COLON + strPrefix;

		final String strOldNameSpaceURI = getInheritedAttribute(strNameSpace, null, JDFCoreConstants.EMPTYSTRING);
		final String myNameSpaceURI = getNamespaceURI();

		if (!strNameSpaceURI.equals(strOldNameSpaceURI) && !strNameSpaceURI.equals(myNameSpaceURI))
		{
			fSuccess = true;
			setAttribute(strNameSpace, strNameSpaceURI, null);
		}
		final DocumentXMLImpl doc = (DocumentXMLImpl) getOwnerDocument();
		doc.setNamespaceURIFromPrefix(strPrefix, strNameSpaceURI);

		return fSuccess;
	}

	/**
	 * sorts according to the lexical string representation of the xml objects
	 *
	 * @author prosirai
	 */
	public static class SimpleNodeComparator implements Comparator<KElement>
	{

		/**
		 *
		 * @param o1
		 * @param o2
		 * @return
		 * @see java.util.Comparator#compare(java.lang.Object, java.lang.Object)
		 */
		@Override
		public int compare(final KElement o1, final KElement o2)
		{
			String nodeName = o1.getNodeName();
			String nodeName2 = o2.getNodeName();
			int i = nodeName.compareTo(nodeName2);
			if (i != 0)
			{
				return i;
			}
			nodeName += o1.getAttribute(JDFCoreConstants.ID);
			nodeName2 += o2.getAttribute(JDFCoreConstants.ID);
			i = nodeName.compareTo(nodeName2);
			if (i != 0)
			{
				return i;
			}
			nodeName += o1.getAttributeMap_KElement();
			nodeName2 += o2.getAttributeMap_KElement();
			return nodeName.compareTo(nodeName2);
		}
	}

	/**
	 * sorts according to the lexical string representation of the xml objects
	 *
	 * @author prosirai
	 */
	public static class SimpleElementNameComparator implements Comparator<KElement>
	{

		/**
		 *
		 * @param o1
		 * @param o2
		 * @return
		 * @see java.util.Comparator#compare(java.lang.Object, java.lang.Object)
		 */
		@Override
		public int compare(final KElement o1, final KElement o2)
		{
			final String nodeName = o1.getNodeName();
			final String nodeName2 = o2.getNodeName();
			return nodeName.compareTo(nodeName2);

		}
	}

	/**
	 * sorts according to the value of one attribute<br/>
	 * if the attribute is numeric, compare numerically, else lexical comparison is done
	 *
	 * @author prosirai
	 */
	public static class SingleXPathComparator implements Comparator<KElement>
	{
		/**
		 * if the attribute is numeric, compare numerically, else lexical comparison is done
		 *
		 * @param xPath the xpath in the context of this element to use for comparing<br/>
		 * @param pInvert if true, sort backwards
		 */
		public SingleXPathComparator(final String xPath, final boolean pInvert)
		{
			super();
			this.xPath = xPath;
			this.invert = pInvert ? -1 : 1;
		}

		final String xPath;
		final int invert;

		/**
		 * @param o1
		 * @param o2
		 * @return
		 * @see java.util.Comparator#compare(java.lang.Object, java.lang.Object)
		 */
		@Override
		public int compare(final KElement o1, final KElement o2)
		{
			final String attribute1 = o1.getXPathAttribute(xPath, null);
			final String attribute2 = o2.getXPathAttribute(xPath, null);
			if (StringUtil.isNumber(attribute1) && StringUtil.isNumber(attribute2))
			{
				final double d1 = StringUtil.parseDouble(attribute1, 0);
				final double d2 = StringUtil.parseDouble(attribute2, 0);
				if (d1 < d2)
				{
					return -invert;
				}
				else if (d1 == d2)
				{
					return 0;
				}
				else
				{
					return invert;
				}
			}
			return invert * ContainerUtil.compare(attribute1, attribute2);
		}
	}

	/**
	 * sorts according to the value of one attribute<br/>
	 * if the attribute is numeric, compare numerically, else lexical comparison is done
	 *
	 * @author prosirai
	 */
	public static class SingleAttributeComparator extends SingleXPathComparator
	{
		/**
		 * if the attribute is numeric, compare numerically, else lexical comparison is done
		 *
		 * @param pAttName the attribute to use for comparing<br/>
		 * @param pInvert if true, sort backwards
		 */
		public SingleAttributeComparator(final String pAttName, final boolean pInvert)
		{
			super("@" + pAttName, pInvert);
		}
	}

	/**
	 * sorts all child elements by alphabet
	 */
	public void sortChildren()
	{
		sortChildren(false);
	}

	/**
	 * sorts all child elements by alphabet
	 */
	public void sortChildren(final boolean bRecursive)
	{
		sortChildren(new SimpleNodeComparator(), bRecursive);
	}

	/**
	 * sorts/inserts e by alphabet
	 *
	 * @param e
	 */
	public void sortChild(final KElement e)
	{
		sortChild(e, new SimpleNodeComparator());
	}

	/**
	 * sorts all child elements by alphabet
	 *
	 * @param comparator the comparator object to sort by
	 */
	public void sortChildren(final Comparator<KElement> comparator)
	{
		sortChildren(comparator, false);
	}

	/**
	 * sorts all child elements by alphabet
	 *
	 * @param comparator the comparator object to sort by
	 */
	public synchronized void sortChildren(final Comparator<KElement> comparator, final boolean bRecursive)
	{
		final List<KElement> v = getChildArray_KElement(null, null, null, true, -1);
		Collections.sort(v, comparator);
		for (final KElement e : v)
		{
			if (bRecursive)
			{
				e.sortChildren(comparator, bRecursive);
			}
			moveElement(e, null);
		}
	}

	/**
	 * insert e into this, assuming that this is already sorted
	 *
	 * @param e the existing element to sort
	 * @param comparator the comparator object to sort by
	 */
	public synchronized void sortChild(final KElement e, final Comparator<KElement> comparator)
	{
		if (e == null)
			return;
		if (e.getParentNode_KElement() == this)
		{
			final KElement prev = e.getPreviousSiblingElement();
			boolean bSorted = prev == null || comparator.compare(prev, e) <= 0;
			if (bSorted)
			{
				final KElement next = e.getNextSiblingElement();
				bSorted = next == null || comparator.compare(next, e) >= 0;
			}
			if (!bSorted)
				e.deleteNode();
			else
				return; // heureka - nothing to do!

		}
		final VElement v = getChildElementVector_KElement(null, null, null, true, -1);
		KElement before = null;
		if (v != null && v.size() > 0)
		{
			int posBefore = v.size();
			int posAfter = 0;
			int lastPos = -1;
			while (posBefore != posAfter)
			{
				final int pos = (posBefore + posAfter) / 2;

				before = v.get(pos);
				final int d = comparator.compare(before, e);
				if (d >= 0)
				{
					posBefore = (lastPos != pos) ? pos : pos - 1;
				}
				else
				{
					posAfter = (lastPos != pos) ? pos : pos + 1;
				}
				lastPos = pos;
			}
			before = v.get(posBefore);
		}
		insertBefore(e, before);
	}

	/**
	 * this to string, used for debug purpose mostly
	 *
	 * @return string representativ of this
	 * @see Object#toString()
	 */
	@Override
	public String toString()
	{
		return toXML();
	}

	/**
	 * serialize this to a string
	 *
	 * @return String the dom element serialized as a string
	 * @throws JDFException if an error occurs while serializing
	 */
	public String toXML()
	{
		return toXML(2);
	}

	/**
	 * serialize this to a string
	 *
	 * @param indent
	 * @return String the dom element serialized as a string
	 * @throws JDFException if an io exception occurs while serializing
	 */
	@SuppressWarnings("deprecation")
	public String toXML(final int indent)
	{
		try
		{
			final StringWriter osw = new StringWriter();
			final OutputFormat format = new OutputFormat(getOwnerDocument());

			format.setIndenting(indent != 0);
			format.setIndent(indent);
			format.setEncoding(StringUtil.UTF8);

			final XMLSerializer serial = new XMLSerializer(osw, format);
			serial.setNamespaces(true);
			serial.asDOMSerializer();
			serial.serialize(this);

			final String s = osw.toString();
			return s;
		}
		catch (final IOException e)
		{
			throw new JDFException("IOException while serializing " + getClass().getName() + " element " + e.getMessage());
		}
	}

	/**
	 * serialize this to a string
	 *
	 * @param indent
	 * @return String the dom element serialized as a string
	 * @throws JDFException if an error occurs while serializing
	 */
	public String toDisplayXML(final int indent)
	{
		final String s = toXML(indent);
		int pos = s.indexOf("?>");
		if (pos > 0)
		{
			pos = s.indexOf("<", pos);
		}
		return (pos > 0) ? s.substring(pos) : s;
	}

	/**
	 * Rename an attribute in this namespace<br/>
	 * if oldName does not exist, newName is NOT modified
	 * <p>
	 * default: renameAttribute(oldName, newName, null, null)
	 *
	 * @param oldName attribute name to move from
	 * @param newName attribute name to move to
	 * @param nameSpaceURI attribute nameSpaceURI to move from
	 * @param newNameSpaceURI attribute nameSpaceURI to move the name to
	 */
	public void renameAttribute(final String oldName, final String newName, final String nameSpaceURI, final String newNameSpaceURI)
	{
		if (hasAttribute(oldName, nameSpaceURI, false))
		{
			final String strAttValue = getAttribute_KElement(oldName, nameSpaceURI, null);
			setAttribute(newName, strAttValue, newNameSpaceURI);
			removeAttribute(oldName, nameSpaceURI);
		}
	}

	/**
	 * Rename an attribute in this namespace<br/>
	 * if oldName does not exist, newName is NOT modified
	 * <p>
	 * default: renameAttribute(oldName, newName, null, null)
	 *
	 * @param oldName attribute name to move from
	 * @param newName attribute name to move to
	 */
	public void renameAttribute(final String oldName, final String newName)
	{
		renameAttribute(oldName, newName, null, null);
	}

	/**
	 * Get a vector of all value of the attribute attName in the children of this node
	 * <p>
	 * default: getChildAttributeList(nodeName, attName, null, JDFCoreConstants.WILDCARD, true, true)
	 *
	 * @param nodeName element name you are searching for
	 * @param attName attributes you are looking for
	 * @param nameSpaceURI nameSpace you are searching for
	 * @param attValue
	 * @param bDirect if true return value is a vector of all found elements. Otherwise the returned vector contains only the nodes
	 * @param bUnique if you want to make sure, the attribute is unique, set this boolean to true. Otherwise attribute attName is added to the returned vector
	 * @return Vector - vector with attributes
	 */
	public VString getChildAttributeList(final String nodeName, final String attName, final String nameSpaceURI, final String attValue, final boolean bDirect, final boolean bUnique)
	{
		final VString v = new VString();
		final VElement vChildren = getChildrenByTagName(nodeName, nameSpaceURI, new JDFAttributeMap(attName, JDFCoreConstants.EMPTYSTRING), bDirect, true, 0);

		final boolean bAttWildCard = isWildCard(attValue);

		for (int i = 0; i < vChildren.size(); i++)
		{
			boolean bAddElement = true;
			final KElement kElem = vChildren.elementAt(i);
			final String s = kElem.getAttribute_KElement(attName, nameSpaceURI, JDFCoreConstants.EMPTYSTRING);
			// fill only matching attributes
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
	 * Inserts the Element elementName before the existing Element node beforeChild. If beforeChild is <code>null</code> , insert elementName at the end of the list of children. If elementName is a
	 * DocumentFragment object, all of its children are inserted, in the same order, before beforeChild. If the elementName is already in the tree, it is removed first.
	 * <p>
	 * default: insertBefore(elementName, beforeChild, null)
	 *
	 * @param elementName The elementName to insert the element itself will be created
	 * @param beforeChild The reference element, i.e., the elemente before which the new element must be inserted
	 * @param nameSpaceURI The namespace to create elementName in
	 * @return KElement the element being inserted
	 */
	public KElement insertBefore(final String elementName, final Node beforeChild, final String nameSpaceURI)
	{
		final KElement newChild = createChildFromName(elementName, nameSpaceURI);
		if (newChild != null)
		{
			insertBefore(newChild, beforeChild);
		}
		return newChild;
	}

	/**
	 * get a vector of all Children that match the strings defined in nodeNames
	 *
	 * @param nodeNames list of node names that fit, both local and qualified node names are checked
	 * @param map
	 * @param bDirect
	 * @param v the vector to be filled. if null, a new empty vector will be created
	 * @return VElement the found child elements
	 */
	public VElement getChildrenFromList(final VString nodeNames, final JDFAttributeMap map, final boolean bDirect, VElement v)
	{
		if (v == null)
		{
			v = new VElement();
		}
		KElement kElem = getFirstChildElement();

		while (kElem != null)
		{
			if (nodeNames.contains(kElem.getLocalName()) || nodeNames.contains(kElem.getNodeName()))
			{
				if (map == null || kElem.includesAttributes(map, true))
				{
					v.addElement(kElem);
				}
			}
			if (!bDirect)
			{
				kElem.getChildrenFromList(nodeNames, map, bDirect, v);
			}
			kElem = kElem.getNextSiblingElement();
		}
		return v;
	}

	/**
	 * get a vector of all Children that do not match the strings defined in nodeNames
	 *
	 * @param nodeNames list of node names that fit, both local and qualified node names are checked
	 * @param bDirect
	 * @param v the vector to be filled. if null, a new empty vector will be created
	 * @return VElement the found child elements
	 */
	public VElement getChildrenIgnoreList(final VString nodeNames, final boolean bDirect, VElement v)
	{
		if (v == null)
		{
			v = new VElement();
		}
		KElement kElem = getFirstChildElement();
		while (kElem != null)
		{
			if (!nodeNames.contains(kElem.getLocalName()) && !nodeNames.contains(kElem.getNodeName()))
			{
				v.addElement(kElem);
			}
			if (!bDirect)
			{
				kElem.getChildrenIgnoreList(nodeNames, bDirect, v);
			}
			kElem = kElem.getNextSiblingElement();
		}
		return v;
	}

	/**
	 * Appends a new child element to the end of 'this', if it's maximum number of the children with defined name and nameSpace doesn't exceed maxAllowed
	 * <p>
	 * default: AppendElementN(elementName, maxAllowed, null)
	 *
	 * @param elementName name of the new child element
	 * @param maxAllowed the maximum number of children with defined name and nameSpace, that are allowed for 'this'
	 * @param nameSpaceURI nameSpace of the new child element
	 * @return KElement newly created child element
	 * @throws JDFException if more elements with name and namespace then maxAllowed already exist
	 */
	public KElement appendElementN(final String elementName, final int maxAllowed, final String nameSpaceURI)
	{
		if (numChildElements_KElement(elementName, nameSpaceURI) >= maxAllowed)
		{
			throw new JDFException("KElement:appendElementN:" + " too many elements (>" + maxAllowed + ") of type" + nameSpaceURI + JDFCoreConstants.COLON + elementName);
		}

		return appendElement(elementName, nameSpaceURI);
	}

	/**
	 * Gets the XPath full tree representation of 'this'
	 *
	 * @return String the XPath representation of 'this' e.g. <code>/root/parent/element</code><br>
	 *         <code>null</code> if parent of this is null (e.g. called on rootnode)
	 * @deprecated use buildXPath(null,true);
	 */
	@Deprecated
	public String buildXPath()
	{
		return buildXPath(null, 1);
	}

	/**
	 * Gets the XPath full tree representation of 'this'
	 *
	 * @param relativeTo relative path to which to create an xpath
	 * @return String the XPath representation of 'this' e.g. <code>/root/parent/element</code><br>
	 *         <code>null</code> if parent of this is null (e.g. called on rootnode)
	 * @deprecated use buildXPath(relativeTo,true);
	 */
	@Deprecated
	public String buildXPath(final String relativeTo)
	{
		return buildXPath(relativeTo, 1);
	}

	/**
	 * Gets the XPath full tree representation of 'this'
	 *
	 * @param relativeTo relative path to which to create an xpath
	 * @param methCountSiblings , if 1 count siblings, i.e. add '[n]' if 0, only specify the path of parents if 2 or 3, add [@ID="id"]
	 * @return String the XPath representation of 'this' e.g. <code>/root/parent/element</code><br>
	 *         <code>null</code> if parent of this is null (e.g. called on rootnode)
	 */
	public String buildXPath(final String relativeTo, final int methCountSiblings)
	{
		return new XPathHelper(this).buildXPath(relativeTo, methCountSiblings);
	}

	/**
	 * Gets the XPath full tree representation of 'this'
	 *
	 * @param relativeTo parent element to which to create an xpath
	 * @param methCountSiblings , if 1 count siblings, i.e. add '[n]' if 0, only specify the path of parents if 2 or 3, add [@ID="id"]
	 * @return String the XPath representation of 'this' e.g. <code>/root/parent/element</code><br>
	 *         <code>null</code> if parent of this is null (e.g. called on rootnode)
	 */
	public String buildRelativeXPath(final KElement relativeTo, final int methCountSiblings)
	{
		return new XPathHelper(this).buildRelativeXPath(relativeTo, methCountSiblings);
	}

	/**
	 * Creates a new child element with defined Name and NameSpace and inserts it in front of the node with a name bForeNode and namespace beforeNameSpaceURI, with index beforePos
	 * <p>
	 * default: InsertAt(nodeName, beforePos, null, null, null)
	 *
	 * @param nodeName name of the new Element
	 * @param beforePos index of beforeNode, i.e if beforePos = 0, put it before the first occurrence
	 * @param beforeNode name of the node to put it before, default - any name, Wildcard supported
	 * @param nameSpaceURI nameSpace of the new node
	 * @param beforeNameSpaceURI nameSpace of the node to put it before, default - value of nameSpaceURI
	 * @return KElement the newly created element
	 * @throws JDFException if 'this' is a null element and thus nothing can be inserted in it
	 */
	public KElement insertAt(final String nodeName, final int beforePos, final String beforeNode, final String nameSpaceURI, final String beforeNameSpaceURI)
	{
		KElement kRet = null;
		final String strBeforeNS = ((beforeNameSpaceURI == null) || beforeNameSpaceURI.equals(JDFCoreConstants.EMPTYSTRING)) ? nameSpaceURI : beforeNameSpaceURI;
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

	/**
	 * Sets an attribute as defined by XPath to value <br>
	 *
	 * @tbd enhance the subsets of allowed XPaths, now only .,..,/,@ are supported
	 * @param path XPath abbreviated syntax representation of the attribute, e.g.: <code>parentElement/thisElement@thisAtt</code> <code>parentElement/thisElement[2]/@thisAtt</code>
	 *            <code>parentElement/thisElement[@foo=\"bar\"]/@thisAtt</code>
	 * @param value string to be set as attribute value
	 */
	public void setXPathValue(final String path, final String value)
	{
		new XPathHelper(this).setXPathValue(path, value);
	}

	/**
	 *
	 * sets all xpaths to the values provided in map
	 *
	 * @param map map of XPath / values to set
	 */
	public void setXPathValues(final JDFAttributeMap map)
	{
		new XPathHelper(this).setXPathValues(map);
	}

	/**
	 * Sets an attribute as defined by XPath to value <br>
	 *
	 * @tbd enhance the subsets of allowed XPaths, now only .,..,/,@ are supported
	 * @param path XPath abbreviated syntax representation of the attribute, e.g.: <code>parentElement/thisElement@thisAtt</code> <code>parentElement/thisElement[2]/@thisAtt</code>
	 *            <code>parentElement/thisElement[@foo=\"bar\"]/@thisAtt</code>
	 * @param value string to be set as attribute value
	 * @throws JDFException if the defined path is a bad attribute path
	 */
	public void setXPathAttribute(final String path, final String value)
	{
		new XPathHelper(this).setXPathAttribute(path, value);
	}

	/**
	 * returns true if the element or attribute described by this XPath exists, else false
	 *
	 * @param path the XPath to test for
	 * @return true if the element described by path exists
	 */
	public boolean hasXPathNode(final String path)
	{
		return new XPathHelper(this).hasXPathNode(path);
	}

	/**
	 * Gets an attribute value as defined by XPath namespace prefixes are resolved or the node text if an element is specified <br>
	 *
	 * @tbd enhance the subsets of allowed XPaths, now only .,..,/,@ are supported TODO fix bug for attribute searches where the att value contains xpath syntax
	 * @param path XPath abbreviated syntax representation of the attribute, <code>parentElement/thisElement/@thisAtt</code> <code>parentElement/thisElement[2]/@thisAtt</code>
	 *            <code>parentElement[@a=\"b\"]/thisElement[@foo=\"bar\"]/@thisAtt</code>
	 * @param def default value if it doesn't exist
	 * @return String the String value of the attribute or null if the xpath element does not exist
	 * @throws JDFException if the defined path is a bad attribute path
	 * @default getXPathAttribute(path, null);
	 */
	public String getXPathAttribute(final String path, final String def)
	{
		final XPathHelper xPathHelper = new XPathHelper(this);
		return xPathHelper.getXPathAttribute(path, def);
	}

	/**
	 * Gets an attribute value as defined by XPath namespace prefixes are resolved <br>
	 * Attributes are searched for in partitioned resources if appropriate
	 *
	 * @tbd enhance the subsets of allowed XPaths, now only .,..,/,@ are supported TODO fix bug for attribute searches where the att value contains xpath syntax
	 * @param path XPath abbreviated syntax representation of the attribute, <code>parentElement/thisElement/@thisAtt</code> <code>parentElement/thisElement[2]/@thisAtt</code>
	 *            <code>parentElement[@a=\"b\"]/thisElement[@foo=\"bar\"]/@thisAtt</code>
	 * @param def default value if it doesn't exist
	 * @return String the String value of the attribute or null if the xpath element does not exist
	 * @throws JDFException if the defined path is a bad attribute path
	 * @default getXPathAttribute(path, null);
	 */
	public String getInheritedXPathAttribute(final String path, final String def)
	{
		return new XPathHelper(this).getInheritedXPathAttribute(path, def);
	}

	/**
	 * Gets a map of attribute values as defined by XPath namespace prefixes are resolved <br>
	 *
	 * @tbd enhance the subsets of allowed XPaths, now only .,..,/,@ are supported
	 * @param path XPath abbreviated syntax representation of the attribute, <code>parentElement/thisElement/@thisAtt</code> <code>parentElement/thisElement[2]/@thisAtt</code>
	 *            <code>parentElement[@a=\"b\"]/thisElement[@foo=\"bar\"]/@thisAtt</code> if null, assume .//@*, i.e. all of this
	 *
	 * @return String the String value of the attribute or null if the xpath element does not exist
	 * @throws JDFException if the defined path is a bad attribute path
	 */
	public JDFAttributeMap getXPathAttributeMap(final String path)
	{
		final XPathHelper xPathHelper = new XPathHelper(this);
		return xPathHelper.getXPathAttributeMap(path, false);
	}

	/**
	 * Gets a map of attribute values as defined by XPath namespace prefixes are resolved <br>
	 *
	 * @tbd enhance the subsets of allowed XPaths, now only .,..,/,@ are supported
	 *
	 *      <code>parentElement/thisElement[2]/@thisAtt</code> <code>parentElement[@a=\"b\"]/thisElement[@foo=\"bar\"]/@thisAtt</code> if null, assume .//@*, i.e. all of this
	 *
	 * @return String the String value of the xpath
	 *
	 */
	public JDFAttributeMap getXPathValueMap()
	{
		final XPathHelper xPathHelper = new XPathHelper(this);
		return xPathHelper.getXPathAttributeMap(null, true);
	}

	/**
	 * gets an element as defined by XPath to value <br>
	 *
	 * @tbd enhance the subsets of allowed XPaths, now only .,..,/,@ are supported
	 * @param path XPath abbreviated syntax representation of the attribute, e.g <code>parentElement/thisElement</code> <code>parentElement/thisElement[2]</code>
	 *            <code>parentElement[@a=\"b\"]/thisElement[./foo/@foo=\"bar\"]</code>
	 * @return KElement the specified element
	 * @throws IllegalArgumentException if path is not supported
	 */
	public KElement getXPathElement(final String path)
	{
		final XPathHelper xPathHelper = new XPathHelper(this);
		return xPathHelper.getXPathElement(path);
	}

	/**
	 * gets an vector of elements element as defined by XPath to value <br>
	 *
	 * @tbd enhance the subsets of allowed XPaths, now only .,..,/,@,// are supported
	 * @param path XPath abbreviated syntax representation of the attribute, e.g <code>parentElement/thisElement</code> <code>parentElement/thisElement[2]</code>
	 *            <code>parentElement[@a=\"b\"]/thisElement[@foo=\"bar\"]</code>
	 * @param maxSize
	 * @return VElement the vector of matching elements
	 * @throws IllegalArgumentException if path is not supported
	 */
	public VElement getXPathElementVector(final String path, final int maxSize)
	{
		return new XPathHelper(this).getXPathElementVectorInternal(path, maxSize, true);
	}

	/**
	 * gets an element as defined by XPath to value and creates it if it does not exist <br>
	 *
	 * @tbd enhance the subsets of allowed XPaths, now only .,..,/,@ are supported
	 * @param path XPath abbreviated syntax representation of the attribute, <code>parentElement/thisElement</code> <code>parentElement/thisElement[2]</code>
	 *            <code>parentElement[@a=\"b\"]/thisElement[@foo=\"bar\"]</code>
	 * @return KElement the specified element
	 */
	public KElement getCreateXPathElement(final String path)
	{
		return new XPathHelper(this).getCreateXPathElement(path);
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
	 * @param nodeType <blockquote>
	 *            <ul>
	 *            <li>ELEMENT_NODE = 1
	 *            <li>ATTRIBUTE_NODE = 2
	 *            <li>TEXT_NODE = 3
	 *            <li>CDATA_SECTION_NODE = 4
	 *            <li>ENTITY_REFERENCE_NODE = 5
	 *            <li>ENTITY_NODE = 6
	 *            <li>PROCESSING_INSTRUCTION_NODE = 7
	 *            <li>COMMENT_NODE = 8
	 *            <li>DOCUMENT_NODE = 9
	 *            <li>DOCUMENT_TYPE_NODE = 10
	 *            <li>DOCUMENT_FRAGMENT_NODE = 11
	 *            <li>NOTATION_NODE = 12
	 *            <li>XML_DECL_NODE = 13 </blockquote>
	 *            </ul>
	 * @return boolean true if there is at least one child of the stated node type, false otherwise
	 */
	protected boolean hasChildNodes(final int nodeType)
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
	 * Get children from the actual element by the tag name, nameSpaceURI or attribute map. <br/>
	 * GetTree only follows direct links, e.g. as in a JDF tree. Hidden nodes that are children of nodes with non-matching names are ignored
	 *
	 * @param nodeName elementname you are searching for
	 * @param nameSpaceURI nameSpace you are searching for
	 * @param mAttrib attributes you are looking for. Wildcards in the attribute map are supported
	 * @param bDirect if true return value is a vector of all direct elements. Otherwise the returned vector contains nodes of arbitrary depth
	 * @param bAnd if true, a child is only added if it has all attributes specified in Attributes mAttrib
	 * @return VElement vector with all found elements
	 */
	public VElement getTree(final String nodeName, final String nameSpaceURI, final JDFAttributeMap mAttrib, final boolean bDirect, final boolean bAnd)
	{
		final VElement v = new VElement();
		KElement e = getFirstChildElement();
		final boolean bHasNoMap = mAttrib == null || mAttrib.isEmpty();
		final boolean bAlwaysFit = KElement.isWildCard(nodeName) && KElement.isWildCard(nameSpaceURI);

		while (e != null)
		{
			if (bAlwaysFit || e.fitsName_KElement(nodeName, nameSpaceURI))
			{
				if (bHasNoMap || e.includesAttributes(mAttrib, bAnd))
				{
					// this guy is the one
					v.add(e);
				}
				if (!bDirect)
				{ // if not direct, recurse
					final VElement vv = e.getTree(nodeName, nameSpaceURI, mAttrib, bDirect, bAnd);
					v.addAll(vv);
				}
			}
			e = e.getNextSiblingElement();
		}

		return v;
	}

	/**
	 * Get children from the actual element by the tag name <br/>
	 * GetTree only follows direct links, e.g. as in a JDF tree. Hidden nodes that are children of nodes with non-matching names are ignored
	 *
	 * @param nodeName elementname you are searching for
	 * @return VElement vector with all found elements
	 */
	public List<KElement> getTree(final String nodeName)
	{
		final ArrayList<KElement> v = new ArrayList<>();
		KElement e = getFirstChildElement();
		final boolean bAlwaysFit = KElement.isWildCard(nodeName);

		while (e != null)
		{
			if (bAlwaysFit || e.fitsName_KElement(nodeName, null))
			{
				// this guy is the one
				v.add(e);
				final List<KElement> vv = e.getTree(nodeName);
				v.addAll(vv);
			}
			e = e.getNextSiblingElement();
		}

		return v;

	}

	/**
	 * Get child from the actual element by the tag name, nameSpaceURI or attribute map. GetTree only follows direct links, e.g. as in a JDF tree. Hidden nodes that are children of non-matching nodes
	 * are ignored
	 *
	 * @param nodeName elementname you are searching for.<br>
	 *            Required, no default.
	 * @param nameSpaceURI nameSpace you are searching for.<br>
	 *            Default is <code>null</code>
	 * @param mAttrib attributes you are looking for <br>
	 *            Wildcards in the attribute map are supported. Default is an empty Map
	 * @param bDirect if true, return value is a vector of all direct elements.<br>
	 *            Otherwise the returned vector contains nodes of arbitrary depth. <br>
	 *            Default is false.
	 * @param bAnd if true, a child is only added if it has all attributes specified in Attributes mAttrib.<br>
	 *            Default is true.
	 * @return KElement the first found element
	 */
	public KElement getTreeElement(final String nodeName, final String nameSpaceURI, final JDFAttributeMap mAttrib, final boolean bDirect, final boolean bAnd)
	{
		if (!bDirect && (mAttrib == null || includesAttributes(mAttrib, bAnd)))
		{
			return this;
		}
		KElement e = getFirstChildElement();
		final boolean bAlwaysFit = isWildCard(nodeName) && isWildCard(nameSpaceURI);

		while (e != null)
		{
			if (bAlwaysFit || e.fitsName_KElement(nodeName, nameSpaceURI))
			{
				if (mAttrib == null || e.includesAttributes(mAttrib, bAnd))
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
	 * @return String the text if the i'th text node. <code>null</code> if the i'th text node does not exists
	 */
	public String getText(final int i)
	{
		final Node n = getChildNode(Node.TEXT_NODE, i);
		return (n == null) ? null : n.getNodeValue();
	}

	/**
	 * Gets of 'this' the text of the i'th XML CData section. This section may also contain &lt; and &gt;.
	 *
	 * @param i index of the CData section child node, you are searching for
	 * @return String content of the i'th XML CData section
	 */
	public String getCData(final int i)
	{
		final Node n = getChildNode(Node.CDATA_SECTION_NODE, i);
		return (n == null) ? null : n.getNodeValue();
	}

	/**
	 * gets of 'this' the text of the i-th child XMLComment. <code><!-- this is a XMLComment --></code> would return <code>this is a XMLComment</code>
	 *
	 * @param i index of the XMLComment child node, you are searching for
	 * @return String text of the i-th XMLComment, null if i is higher then the number of child nodes
	 */
	public String getXMLComment(final int i)
	{
		final Node n = getChildNode(Node.COMMENT_NODE, i);
		return (n == null) ? null : n.getNodeValue();
	}

	/**
	 * copy attribute values or text from an xpath in src to this
	 *
	 * @param dstXPath the destination xpath in this element
	 * @param src the source element, if null; use this
	 * @param srcXPath the source xpath, if null same as dstXPath
	 * @return the copied value; may be null if no value was found in srcXPath
	 */
	public String copyXPathValue(final String dstXPath, final KElement src, final String srcXPath)
	{
		return new XPathHelper(this).copyXPathValue(dstXPath, src, srcXPath);
	}

	/**
	 * copy an attribute from src to this, if null - remove the attribute
	 * <p>
	 * default: copyAttribute(attrib, src, null, null, null)
	 *
	 * @param attrib the name of the attribute to copy (if source attribute is different only the value will be copied)
	 * @param src source element where the attribute to be copied resides
	 * @param srcAttrib attribute to copy, defaults to the value of attrib
	 * @param nameSpaceURI of the attribute in the destination
	 * @param srcNameSpaceURI of the attribute in the source, defaults to the value of nameSpaceURI
	 * @default copyAttribute(attrib,src,null,null,null);
	 * @return the value of the copied attribute
	 */
	public String copyAttribute(String attrib, final KElement src, final String srcAttrib, final String nameSpaceURI, final String srcNameSpaceURI)
	{
		final String strSrcAttrib = (srcAttrib == null) || srcAttrib.equals(JDFCoreConstants.EMPTYSTRING) ? attrib : srcAttrib;
		final String strNameSpace = (srcNameSpaceURI == null) || srcNameSpaceURI.equals(JDFCoreConstants.EMPTYSTRING) ? nameSpaceURI : srcNameSpaceURI;
		if (strNameSpace != null && KElement.xmlnsPrefix(attrib) == null)
		{
			final Attr an = src.getDOMAttr(strSrcAttrib, srcNameSpaceURI, false);
			if (an != null)
			{
				final String pre = an.getPrefix();
				if (!isWildCard(pre))
				{
					attrib = pre + ":" + attrib;
				}
			}
		}

		final String srcAtt = src == null ? null : src.getAttribute_KElement(strSrcAttrib, srcNameSpaceURI, null);
		setAttribute(attrib, srcAtt, strNameSpace);
		return srcAtt;
	}

	/**
	 * copy an attribute from src to this - shorthand if no renaming or namespace handling is necessary
	 * <p>
	 * default: copyAttribute(attrib, src, null, null, null)
	 *
	 * @param attrib the name of the attribute to copy (if source attribute is different only the value will be copied)
	 * @param src source element where the attribute to be copied resides
	 * @return the value of the copied attribute
	 */
	public String copyAttribute(final String attrib, final KElement src)
	{
		return copyAttribute(attrib, src, null, null, null);
	}

	/**
	 * moves an attribute from src to this, the attribute will be removed from src and moved to this.
	 * <p>
	 * default: moveAttribute(attrib, src, null, null, null)
	 *
	 * @param attrib where to move the attribute
	 * @param src element to move from
	 * @param srcAttrib the attribute to move. If empty string, the string attrib is used as source and target
	 * @param nameSpaceURI the namespaceURI to set
	 * @param srcNameSpaceURI
	 */
	public void moveAttribute(final String attrib, KElement src, final String srcAttrib, String nameSpaceURI, final String srcNameSpaceURI)
	{
		if (src == null)
		{
			src = this;
		}

		final String strSrcAttrib = StringUtil.isEmpty(srcAttrib) ? attrib : srcAttrib;
		final String strNameSpace = StringUtil.isEmpty(srcNameSpaceURI) ? nameSpaceURI : srcNameSpaceURI;
		if (xmlnsPrefix(attrib) != null && nameSpaceURI == null)
		{
			final boolean b = src.hasAttribute(strSrcAttrib, strNameSpace, false);
			if (b)
			{
				final Attr attr = src.getDOMAttr(strSrcAttrib, strNameSpace, true);
				if (attr != null)
				{
					nameSpaceURI = attr.getNamespaceURI();
				}
			}
		}

		final String attribute = src.getAttribute(strSrcAttrib, strNameSpace, null);
		if (!StringUtil.isEmpty(attribute))
		{
			src.removeAttribute(strSrcAttrib, strNameSpace);
		}

		setAttribute(attrib, attribute, nameSpaceURI);
	}

	/**
	 * moves an attribute from src to this, the attribute will be removed from src and moved to this.
	 * <p>
	 *
	 * @param attrib where to move the attribute
	 * @param src element to move from
	 */
	public void moveAttribute(final String attrib, final KElement src)
	{
		if (src != null)
		{
			moveAttribute(attrib, src, null, null, null);
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
	 * @return String the concatenated values of all direct text child nodes empty string if no child nodes exist
	 */
	public String getText()
	{
		final int iBufferSize = 100;
		final StringBuilder strBuff = new StringBuilder(iBufferSize);

		final NodeList nodeList = getChildNodes();
		final int length = nodeList.getLength();
		for (int i = 0; i < length; i++)
		{
			final Node node = nodeList.item(i);
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
	 * Moves 'this' from parent to grandparent or to the closest ancestor with name newParentName
	 *
	 * @param newParentName name of the parent to recursively search, defaults to any name
	 * @return this, null if failed (e.g. no parentNode found)
	 */
	public KElement pushUp(final String newParentName)
	{
		KElement kEle = null;
		Node parent = getParentNode();

		if (parent != null)
		{
			do
			{
				parent = parent.getParentNode();
			}
			while (parent != null && newParentName != null && !newParentName.equals(JDFCoreConstants.EMPTYSTRING) && !parent.getNodeName().equals(newParentName));

			if (parent != null)
			{
				kEle = ((KElement) parent).moveElement(this, null);
			}
		}

		return kEle;
	}

	/**
	 * count the number of child nodes of DOM nodeType nodeType (0=count all)
	 *
	 * @param nodeType DOM nodeType <blockquote>
	 *            <ul>
	 *            <li>count all = 0
	 *            <li>ELEMENT_NODE = 1
	 *            <li>ATTRIBUTE_NODE = 2
	 *            <li>TEXT_NODE = 3
	 *            <li>CDATA_SECTION_NODE = 4
	 *            <li>ENTITY_REFERENCE_NODE = 5
	 *            <li>ENTITY_NODE = 6
	 *            <li>PROCESSING_INSTRUCTION_NODE = 7
	 *            <li>COMMENT_NODE = 8
	 *            <li>DOCUMENT_NODE = 9
	 *            <li>DOCUMENT_TYPE_NODE = 10
	 *            <li>DOCUMENT_FRAGMENT_NODE = 11
	 *            <li>NOTATION_NODE = 12
	 *            <li>XML_DECL_NODE = 13 </blockquote>
	 *            </ul>
	 * @return number of child nodes with "nodeType"
	 * @deprecated use 2-parameter version numChildNodes(nodeType, false);
	 */
	@Deprecated
	public int numChildNodes(final int nodeType)
	{
		return numChildNodes(nodeType, false);
	}

	/**
	 * count the number of child nodes of DOM nodeType nodeType (0=count all)
	 *
	 * @param nodeType DOM nodeType <blockquote>
	 *            <ul>
	 *            <li>count all = 0
	 *            <li>ELEMENT_NODE = 1
	 *            <li>ATTRIBUTE_NODE = 2
	 *            <li>TEXT_NODE = 3
	 *            <li>CDATA_SECTION_NODE = 4
	 *            <li>ENTITY_REFERENCE_NODE = 5
	 *            <li>ENTITY_NODE = 6
	 *            <li>PROCESSING_INSTRUCTION_NODE = 7
	 *            <li>COMMENT_NODE = 8
	 *            <li>DOCUMENT_NODE = 9
	 *            <li>DOCUMENT_TYPE_NODE = 10
	 *            <li>DOCUMENT_FRAGMENT_NODE = 11
	 *            <li>NOTATION_NODE = 12
	 *            <li>XML_DECL_NODE = 13 </blockquote>
	 *            </ul>
	 * @param bRecurse
	 * @return number of child nodes with "nodeType"
	 */
	public int numChildNodes(final int nodeType, final boolean bRecurse)
	{
		int i = 0;
		Node n = getFirstChild();
		while (n != null)
		{
			if (nodeType == 0 || n.getNodeType() == nodeType)
			{
				i++;
			}
			if (bRecurse && nodeType == ELEMENT_NODE)
			{
				i += ((KElement) n).numChildNodes(nodeType, bRecurse);
			}
			n = n.getNextSibling();
		}
		return i;
	}

	/**
	 * removes the i'th child node, that match NodeType
	 *
	 * @param nodeType the DOM NodeType,if 0 count all nodes <blockquote>
	 *            <ul>
	 *            <li>ELEMENT_NODE = 1
	 *            <li>ATTRIBUTE_NODE = 2
	 *            <li>TEXT_NODE = 3
	 *            <li>CDATA_SECTION_NODE = 4
	 *            <li>ENTITY_REFERENCE_NODE = 5
	 *            <li>ENTITY_NODE = 6
	 *            <li>PROCESSING_INSTRUCTION_NODE = 7
	 *            <li>COMMENT_NODE = 8
	 *            <li>DOCUMENT_NODE = 9
	 *            <li>DOCUMENT_TYPE_NODE = 10
	 *            <li>DOCUMENT_FRAGMENT_NODE = 11
	 *            <li>NOTATION_NODE = 12
	 *            <li>XML_DECL_NODE = 13 </blockquote>
	 *            </ul>
	 * @param i index of the child nodes to remove
	 * @return true if success, false if failed (no i'th node of nodeType found)
	 */
	public boolean removeChildNode(final int nodeType, final int i)
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
	 * @param i index of the text node to remove. First node has index 0, second 1, etc.
	 */
	public void removeChildText(final int i)
	{
		removeChildNode(Node.TEXT_NODE, i);
	}

	/**
	 * Removes the i'th XML CData section
	 *
	 * @param i index of the CData section child node to remove
	 */
	public void removeCData(final int i)
	{
		removeChildNode(Node.CDATA_SECTION_NODE, i);
	}

	/**
	 * removes the i'th XMLComment node
	 *
	 * @param i index of the XMLComment node to remove
	 */
	public void removeXMLComment(final int i)
	{
		removeChildNode(Node.COMMENT_NODE, i);
	}

	/**
	 * Gets the number of direct child nodes of 'this', that match NodeType
	 *
	 * @param nodeType the DOM NodeType, if 0 count all nodes <blockquote>
	 *            <ul>
	 *            <li>ELEMENT_NODE = 1
	 *            <li>ATTRIBUTE_NODE = 2
	 *            <li>TEXT_NODE = 3
	 *            <li>CDATA_SECTION_NODE = 4
	 *            <li>ENTITY_REFERENCE_NODE = 5
	 *            <li>ENTITY_NODE = 6
	 *            <li>PROCESSING_INSTRUCTION_NODE = 7
	 *            <li>COMMENT_NODE = 8
	 *            <li>DOCUMENT_NODE = 9
	 *            <li>DOCUMENT_TYPE_NODE = 10
	 *            <li>DOCUMENT_FRAGMENT_NODE = 11
	 *            <li>NOTATION_NODE = 12
	 *            <li>XML_DECL_NODE = 13 </blockquote>
	 *            </ul>
	 * @return int: the counted number of direct child nodes, that match NodeType
	 */
	public int getNumChildNodes(final int nodeType)
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
	 * @return int: the number of direct XMLComment child nodes
	 */
	public int getNumXMLComments()
	{
		return getNumChildNodes(Node.COMMENT_NODE);
	}

	/**
	 * gets the n'th child node of nodetype <code>nodeType</code> with n = iPos
	 *
	 * @param nodeType the DOM node type to get <blockquote>
	 *            <ul>
	 *            <li>ELEMENT_NODE = 1
	 *            <li>ATTRIBUTE_NODE = 2
	 *            <li>TEXT_NODE = 3
	 *            <li>CDATA_SECTION_NODE = 4
	 *            <li>ENTITY_REFERENCE_NODE = 5
	 *            <li>ENTITY_NODE = 6
	 *            <li>PROCESSING_INSTRUCTION_NODE = 7
	 *            <li>COMMENT_NODE = 8
	 *            <li>DOCUMENT_NODE = 9
	 *            <li>DOCUMENT_TYPE_NODE = 10
	 *            <li>DOCUMENT_FRAGMENT_NODE = 11
	 *            <li>NOTATION_NODE = 12
	 *            <li>XML_DECL_NODE = 13 </blockquote>
	 *            </ul>
	 * @param iPos the index of the node with default 0 for the first occurance
	 * @return KElement: a node that matches the filter, null if iPos is higher then the number of child nodes
	 */
	protected Node getChildNode(final int nodeType, final int iPos)
	{
		Node retNode = null;
		Node node = getFirstChild();
		int i = 0;

		// iPos + 1 because we need to stop after the iPos
		// turn and i will then be iPos + 1
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
	 * get the namespace prefix from a qualified name. For example, nodename <code>exp:myexampley</code> would return <code>exp</code>
	 *
	 * @deprecated use xmlnsPrefix()
	 * @param s the qualified name
	 * @return namespace of the qualified name
	 */
	@Deprecated
	public static String xmlNameSpace(final String s)
	{

		return xmlnsPrefix(s);
	}

	/**
	 * sets multiple attributes at once both arrays need to be of equal length.
	 *
	 * @param myAttributes array of attributes
	 * @param strValues array of values
	 * @throws ArrayIndexOutOfBoundsException if the arrays are not of equal length
	 * @deprecated use setAttributes(JDFAttributeMap)
	 */
	@Deprecated
	public void setAttributes(final String[] myAttributes, final String[] strValues)
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
			else if (posColon == pc.length() - 1)
			{
				return null;
			}
			return pc.substring(posColon + 1);
		}
		return null;
	}

	/**
	 * get the namespace of the qualified name * <blockquote><b>namespace</b>:localname</blockquote>
	 *
	 * @param pc the qualified name.
	 * @deprecated
	 * @return the namespace of the qualified name. An Emptystring if pc is null or no namespace found.
	 */
	@Deprecated
	public static String getXMLNSNameSpace(final String pc)
	{
		return xmlnsPrefix(pc);
	}

	/**
	 * @param typ
	 */
	public void setXSIType(final String typ)
	{
		setAttribute(JDFCoreConstants.XSITYPE, typ, JDFCoreConstants.XSIURI);
	}

	/**
	 * returns the xsi:type of this element, null if not present
	 *
	 * @return String the xsi:type of this element, null if not present
	 */
	public String getXSIType()
	{
		return getAttribute("type", JDFCoreConstants.XSIURI, null);
	}

	/**
	 * Parses pc for it's namespace prefix
	 * <p>
	 * <blockquote><code>ns:foo</code> will return <code>ns</code></blockquote>
	 *
	 * @deprecated use xmlnsPrefix
	 * @param pc string to parse
	 * @return String namespace prefix of pc, emptyspace if no ":" is in pc
	 */
	@Deprecated
	public static String getXMLNSPrefix(final String pc)
	{
		return xmlnsPrefix(pc);
	}

	/**
	 * Fix the parentNode from this. If flagSrc == null the flags of parentNode are used.
	 *
	 * @param parentSrc where we get the parent from
	 * @param flagSrc where er get the flags from
	 */
	private void fixParent(final KElement parentSrc, final KElement flagSrc)
	{
		// tell him where he belongs to and...
		this.ownerNode = parentSrc.ownerNode;
		// that he is owned (the flags are a bitmask)
		if (flagSrc == null)
		{
			this.flags = parentSrc.flags;
		}
		else
		{
			this.flags = flagSrc.flags;
		}
	}

	/**
	 * Sets an XML Text <br>
	 * the text contents of this to the value of text
	 *
	 * @param text XML Text to set
	 * @throws JDFException if 'this' is a null element and thus nothing can be inserted in it
	 */
	public void setText(final String text)
	{
		removeAllText();
		appendText(text);
	}

	/**
	 * @see org.apache.xerces.dom.ElementImpl#removeAttribute(java.lang.String)
	 */
	@Override
	public void removeAttribute(final String attrib) throws DOMException
	{
		removeAttribute_KElement(attrib, null);
	}

	/**
	 * @see org.apache.xerces.dom.ElementImpl#removeAttributeNS(java.lang.String, java.lang.String)
	 */
	@Override
	public void removeAttributeNS(final String nameSpaceURI, final String attrib) throws DOMException
	{
		removeAttribute_KElement(attrib, nameSpaceURI);
	}

	/**
	 * @see org.apache.xerces.dom.ElementImpl#removeAttributeNode(org.w3c.dom.Attr)
	 */
	@Override
	public Attr removeAttributeNode(final Attr arg0) throws DOMException
	{
		setDirty(true);
		return super.removeAttributeNode(arg0);
	}

	/**
	 * @see org.apache.xerces.dom.ElementImpl#setAttributeNode(org.w3c.dom.Attr)
	 */
	@Override
	public Attr setAttributeNode(final Attr arg0) throws DOMException
	{
		setDirty(true);
		return super.setAttributeNode(arg0);
	}

	/**
	 * @see org.apache.xerces.dom.ElementImpl#setAttributeNodeNS(org.w3c.dom.Attr)
	 */
	@Override
	public Attr setAttributeNodeNS(final Attr arg0) throws DOMException
	{
		setDirty(true);
		return super.setAttributeNodeNS(arg0);
	}

	/**
	 * @see org.apache.xerces.dom.ElementImpl#setAttributeNS(java.lang.String, java.lang.String, java.lang.String)
	 */
	@Override
	public void setAttributeNS(final String nsURI, final String arg1, final String arg2) throws DOMException
	{
		setDirty(true);
		setAttribute(arg1, arg2, nsURI);
	}

	/**
	 * @see org.apache.xerces.dom.ElementImpl#normalize()
	 */
	@Override
	public void normalize()
	{
		setDirty(false);
		super.normalize();
	}

	/**
	 * @see org.apache.xerces.dom.NodeImpl#setNodeValue(java.lang.String)
	 */
	@Override
	public void setNodeValue(final String arg0) throws DOMException
	{
		setDirty(false);
		super.setNodeValue(arg0);
	}

	/**
	 * @see org.apache.xerces.dom.ElementNSImpl#setPrefix(java.lang.String)
	 */
	@Override
	public void setPrefix(final String arg0) throws DOMException
	{
		super.setPrefix(arg0);
		setDirty(false);
	}

	/**
	 * @param arg0 the ns uri to set
	 * @throws DOMException
	 */
	public void setNamespaceURI(final String arg0) throws DOMException
	{
		namespaceURI = arg0;
		setDirty(false);
	}

	/**
	 * @see org.apache.xerces.dom.NodeImpl#appendChild(org.w3c.dom.Node)
	 */
	@Override
	public Node appendChild(final Node arg0) throws DOMException
	{
		return insertBefore(arg0, null);
	}

	/**
	 * @see org.apache.xerces.dom.ParentNode#removeChild(org.w3c.dom.Node)
	 */
	@Override
	public synchronized Node removeChild(final Node arg0) throws DOMException
	{
		setDirty(false);
		return super.removeChild(arg0);
	}

	/**
	 * @see org.apache.xerces.dom.ParentNode#insertBefore(org.w3c.dom.Node, org.w3c.dom.Node)
	 */
	@Override
	public synchronized Node insertBefore(final Node arg0, final Node arg1) throws DOMException
	{
		setDirty(false);
		return super.insertBefore(arg0, arg1);
	}

	/**
	 * @see org.apache.xerces.dom.ParentNode#replaceChild(org.w3c.dom.Node, org.w3c.dom.Node)
	 */
	@Override
	public synchronized Node replaceChild(final Node arg0, final Node arg1) throws DOMException
	{
		setDirty(false);
		return super.replaceChild(arg0, arg1);
	}

	/**
	 * clones the root and also correctly sets the owner document
	 *
	 * @param d the document to clone the root into
	 * @return the cloned root element
	 */
	KElement cloneRoot(final XMLDoc d)
	{
		final KElement e = (KElement) d.importNode(this, true);
		e.flags = (short) (e.flags | NodeImpl.OWNED); // otherwise some crap might not work...
		return e;
	}

	/**
	 * remove an attribute or text that is described by the xpath path quietly returns if the attribute or text does not exist
	 *
	 * @param path the XPath to the attribute that is to be removed
	 * @throws JDFException if the XPath is corrupt
	 */
	public void removeXPathAttribute(final String path)
	{
		new XPathHelper(this).removeXPathAttribute(path);
	}

	/**
	 * remove an attribute or element that is described by the xpath path quietly returns if the element does not exist
	 *
	 * @param path the XPath to the attribute that is to be removed
	 * @throws JDFException if the XPath is corrupt
	 */
	public void removeXPathElement(final String path)
	{
		new XPathHelper(this).removeXPathElement(path);
	}

	/**
	 * check whether this matches a simple xpath
	 *
	 * @param path
	 * @return boolean
	 * @deprecated use matchesPath(String path, boolean bFollowRefs)
	 */
	@Deprecated
	public boolean matchesPath(final String path)
	{
		return matchesPath(path, false);
	}

	/**
	 * check whether this element matches a simple xpath
	 *
	 * @param path xpath to match may include syntax <code>e[i]</code> or <code>e[@a="b"]</code>
	 * @param bFollowRefs
	 * @return boolean true, if this matches the given xpath
	 */
	public boolean matchesPath(final String path, final boolean bFollowRefs)
	{
		// bFollowRefs only needed in the JDFElement version
		if (path == null)
		{
			return true;
		}
		if (bFollowRefs)
		{
			this.getClass(); // dummy to fool compiler
		}

		final VString v = StringUtil.tokenize(path, "/", false);
		KElement e = this;
		for (int i = v.size() - 1; i >= 0; i--)
		{
			if (e == null)
			{
				return false;
			}
			final String pathAt = v.get(i);
			if (!e.matchesPathName(pathAt))
			{
				return false;
			}

			e = e.getParentNode_KElement();
		}

		if (path.startsWith("/") && !path.startsWith("//"))
		{
			return e == null; // must be root
		}

		return true; // any location
	}

	protected boolean matchesPathName(final String pathAt)
	{
		if (pathAt == null || pathAt.equals(JDFCoreConstants.STAR))
		{
			return true;
		}
		if (localName.equals(pathAt))
		{
			return true;
		}
		final String nodeName = getNodeName();
		if (nodeName.equals(pathAt))
		{
			return true;
		}
		final String startPath = pathAt.startsWith(localName) ? localName : pathAt.startsWith(nodeName) ? nodeName : null;
		if (startPath != null) // process for attributes
		{
			String token = StringUtil.token(pathAt, 1, "[");
			if (token == null || !token.endsWith("]"))
			{
				// e[n];
				return false;
			}
			token = StringUtil.leftStr(token, -1); // remove "]"
			final int n = StringUtil.parseInt(token, 0);
			if (n != 0)
			{
				final KElement p = getParentNode_KElement();
				if (p == null)
				{
					return n == 1;
				}
				return p.getElement(localName, getNamespaceURI(), n - 1) == this;
			}
			if (token.startsWith("@"))
			{
				token = token.substring(1);
				final String nam = StringUtil.token(token, 0, "=");
				String value = StringUtil.token(token, 1, "=");
				if (value == null)
				{
					return false;
				}
				if (value.length() < 2 || !value.startsWith("\"") || !value.endsWith("\""))
				{
					return false;
				}
				value = value.substring(1, value.length() - 1);
				return value.equals("*") && hasAttribute_KElement(nam, null, false) || value.equals(getAttribute_KElement(nam));
			}
		}
		return false;
	}

	/**
	 * fills a HashSet with all values of the attribute in all child elements
	 *
	 * @param attName the attribute to search
	 * @param attNS the namespace of the attribute
	 * @return
	 */
	public final HashSet<String> fillHashSet(final String attName, final String attNS)
	{
		final HashSet<String> preFill = new HashSet<>();
		fillHashSet(attName, attNS, preFill, true);
		return preFill;
	}

	/**
	 * fills a HashSet with all values of the attribute in all child elements
	 *
	 * @param attName attribute name
	 * @param attNS attrib ute namespaceuri
	 * @param preFill the HashSet to fill
	 * @param bFirst
	 */
	private void fillHashSet(final String attName, final String attNS, final HashSet<String> preFill, final boolean bFirst)
	{
		final Attr attr = attNS == null ? super.getAttributeNodeNS(attNS, attName) : super.getAttributeNode(attName);
		final String attVal = attr == null ? null : attr.getValue();
		if (attVal != null)
		{
			if (preFill.contains(attVal))
			{
				if (JDFCoreConstants.ID.equals(attName))
				{
					return; // been here already: break
				}
			}
			else
			{
				preFill.add(attVal);
			}
		}

		// get all subnodes, INCLUDING partition leaves
		Collection<KElement> v = getChildArray_KElement(null, null, null, true, 0);
		for (final KElement e : v) // do not recurse down again for the leaves, we've already done that
		{
			e.fillHashSet(attName, attNS, preFill, false);
		}

		if (bFirst)
		{
			// also get all lower level parent partition refs
			v = getChildElementVector(null, null, null, true, 0, false);
			for (final KElement e : v)
			{
				e.fillHashSet(attName, attNS, preFill, true);
			}
		}
	}

	/**
	 * the clone is the same document
	 *
	 * @see java.lang.Object#clone()
	 * @return
	 */
	@Override
	public KElement clone()
	{
		final KElement e = createChildFromName(getNodeName(), getNamespaceURI());
		e.copyInto(this, false);
		return e;

	}

	/**
	 * same as @see clone but the clone is in a new document <br/>
	 * the document gets copies of context sensitive stuff like zip, mime, filename...
	 *
	 * @see java.lang.Object#clone()
	 * @return
	 */
	public KElement cloneNewDoc() // throws CloneNotSupportedException
	{
		final XMLDoc d = new XMLDoc(getNodeName(), getNamespaceURI());
		d.copyMeta(getOwnerDocument_KElement());
		final KElement e = d.getRoot();
		e.copyInto(this, false);
		return e;
	}

	/**
	 * create and append a unique id, keep the existing one if it already exists
	 *
	 * @param newID the new id. if null, then a reasonably unique id is generated
	 *
	 * @return String - the value of the ID attribute after setting
	 *
	 * @default appendAnchor(null)
	 */
	public String appendAnchor(String newID)
	{
		final String id = getNonEmpty(JDFCoreConstants.ID);
		if (id != null)
		{
			return id;
		}
		else if ((newID == null) || newID.equals(JDFCoreConstants.EMPTYSTRING))
		{
			newID = "id_" + uniqueID(0);
		}
		setAttribute(JDFCoreConstants.ID, newID, null);
		return newID;
	}

	/**
	 * gets attribute ID
	 *
	 * @return the attribute value
	 */
	public String getID()
	{
		return getAttribute(JDFCoreConstants.ID, null, JDFCoreConstants.EMPTYSTRING);
	}

	/**
	 * generate a unique id in the syntax newID=oldID.nn <br>
	 * nn is a unique number, that is generated as the first integer higher than the number of sibling elements with the same name. <br>
	 * Note that it is the responsibilty of the caller not to provide multiple siblings that use the same base IDs.
	 *
	 * @param key the attribute that is to be set to this ID, e.g. jobpartid
	 * @param nameSpaceURI the attribute namespace that is to be set to this ID, e.g. jobpartid
	 * @return String - the newly generated ID in the syntax parentID.nn
	 */
	public String generateDotID(final String key, final String nameSpaceURI)
	{
		final String nodeName = getLocalName();
		final KElement p = getParentNode_KElement();
		final String idPrefix = getIDPrefix();
		if (p == null)
		{
			return idPrefix + uniqueID(0);
		}
		String parentID = p.getAttribute(key, nameSpaceURI, null);
		if (parentID == null)
		{
			return idPrefix + uniqueID(0);
		}

		final List<KElement> vn = p.getChildArray_KElement(nodeName, nameSpaceURI, null, true, 0);
		final int siz = vn.size();
		parentID += JDFCoreConstants.DOT;

		for (int i = siz; i < 2 * siz + 2; i++)
		{
			final String nn = parentID + i;
			boolean bFound = false;
			for (int j = 0; j < siz; j++)
			{
				if (nn.equals(vn.get(j).getAttribute(key, nameSpaceURI, null)))
				{
					bFound = true;
					break;
				}
			}
			// got an unused id matching out x.y algorithm - return it
			if (!bFound)
			{
				return nn;
			}
		}
		// panic exit!
		return idPrefix + uniqueID(0);
	}

	/**
	 * getIDPrefix
	 *
	 * @return the default ID prefix of non-overwritten JDF elements
	 */
	protected String getIDPrefix()
	{
		return "l";
	}

	/**
	 * gets attribute ID
	 *
	 * @param id
	 *
	 */
	public void setID(final String id)
	{
		setAttribute(JDFCoreConstants.ID, id, null);
	}

	/**
	 * set the ID generation algorithm to include a date
	 *
	 * @param bLong if true (default), the date and time is used to generate long IDs
	 */
	static public void setLongID(final boolean bLong)
	{
		bIDDate = bLong;
	}

	/**
	 * UniqueID - create a unique id based on date and time + a counter - 6 digits are taken from id Normally this should only be used internally, @see JDFElement.appendAnchor() for details.
	 *
	 * @param id the starting id of the ID - should normally be 0 in order to increment
	 *
	 * @return the ID string value
	 *
	 * @default uniqueID(0)
	 */
	public static String uniqueID(final int id)
	{
		return uniqueID(id, bIDDate);
	}

	/**
	 *
	 * @param id
	 * @param bDate
	 * @return
	 */
	public static String uniqueID(int id, final boolean bDate)
	{
		if (id != 0)
		{
			if (id < 0)
			{
				id = m_lStoreID.get() - id; // just in case someone accidentally uses too large random numbers
			}
			m_lStoreID.set(id % 1000000);
		}
		final String s = StringUtil.rightStr("000000" + m_lStoreID.getAndIncrement(), 6);
		// time + 6 digits (ID)
		if (bDate)
		{
			final String date = dateFormatter.format(new Date());
			return "_" + date + "_" + s;
		}
		return "_" + s;
	}

	/**
	 *
	 * create a new root document
	 *
	 * @param nodename
	 * @param namespaceURI
	 * @return
	 */
	public static KElement createRoot(final String nodename, final String namespaceURI)
	{
		return new XMLDoc(nodename, namespaceURI).getRoot();
	}

	/**
	 * get the number of sibling elements
	 *
	 * @param elementName , if null any and all at the same level
	 * @param nameSpaceURI
	 * @return
	 */
	public int numSiblingElements(final String elementName, final String nameSpaceURI)
	{
		final KElement parent = getParentNode_KElement();
		if (parent == null)
			return 0;
		final VElement ve = parent.getChildElementVector(elementName, nameSpaceURI);
		if (ve == null)
			return 0;
		int n = ve.size();
		if (ve.contains(this))
			n--;
		return n;
	}

	/**
	 *
	 * write myself to an output stream
	 *
	 * @param stream
	 * @return
	 */
	public boolean write2Stream(final OutputStream stream)
	{
		if (stream == null)
		{
			return false;
		}
		try
		{
			stream.write(toXML().getBytes(StringUtil.UTF8));
			return true;
		}
		catch (final IOException e)
		{
			kLog.error("cannot write " + getLocalName());
			return false;
		}
	}

	/**
	 *
	 * write myself to a file
	 *
	 * @param file
	 * @return
	 */
	public boolean write2File(final File file)
	{
		final OutputStream stream = FileUtil.getBufferedOutputStream(file);
		final boolean written = write2Stream(stream);
		StreamUtil.close(stream);
		return written;
	}

	/**
	 *
	 * write myself to a file
	 *
	 * @param filename
	 * @return
	 */
	public boolean write2File(final String filename)
	{
		return write2File(UrlUtil.urlToFile(filename));
	}

	/**
	 * convenience setter
	 *
	 * same as setAttribute but never sets ""
	 *
	 * @param key
	 * @param val
	 */
	public void setNonEmpty(final String key, final String val)
	{
		setAttribute(key, StringUtil.getNonEmpty(val));
	}

	/**
	 *
	 * @param filename
	 * @return
	 */
	public static KElement parseFile(final String filename)
	{
		final XMLDoc f = XMLDoc.parseFile(filename);
		return f == null ? null : f.getRoot();
	}
}
