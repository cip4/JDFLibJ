/*--------------------------------------------------------------------------------------------------
 * The CIP4 Software License, Version 1.0
 *
 *
 * Copyright (c) 2001-2013 The International Cooperation for the Integration of
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
 * Copyright (c) 2001-2004 Heidelberger Druckmaschinen AG, All Rights Reserved.
 *
 * JDFElement.java
 *
 * Last changes
 *
 * 2001-07-30   Torsten Kaehlert - delete isNull() and throwNull() methods in parent class KNode
 *              TKAE20010730
 * 2002-07-02 JG add SettingsPolicy Support
 * 2002-07-02 JG removed AppendElement - it is now only in KElement
 * 2002-07-02 JG added IsJDFNode()
 * 2002-07-02 JG added RemoveChild() / RemoveChildren
 * 2002-07-02 JG GetEnumerationsAttribute() bug fix in loop counter
 * 2002-07-02 JG removed GetTarget(int id,const KString & attrib) const;
 * 2002-07-02 JG GetJDFRoot, GetJMFRoot now const
 * 2002-07-02 JG GetLinks added rSubRef check to map of checked attributes
 * 2002-07-02 JG added NamedColor support
 * 2002-07-02 JG GetElement - added a check that returns the refElement if a refElement is explicitly required
 */

package org.cip4.jdflib.core;

import java.io.InputStream;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.Vector;

import org.apache.commons.lang.enums.ValuedEnum;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.xerces.dom.CoreDocumentImpl;
import org.cip4.jdflib.auto.JDFAutoQueueEntry.EnumQueueEntryStatus;
import org.cip4.jdflib.core.AttributeInfo.EnumAttributeType;
import org.cip4.jdflib.datatypes.JDFAttributeMap;
import org.cip4.jdflib.datatypes.JDFNameRange;
import org.cip4.jdflib.datatypes.JDFNumList;
import org.cip4.jdflib.datatypes.JDFRange;
import org.cip4.jdflib.datatypes.JDFRangeList;
import org.cip4.jdflib.datatypes.VJDFAttributeMap;
import org.cip4.jdflib.elementwalker.FixVersion;
import org.cip4.jdflib.jmf.JDFJMF;
import org.cip4.jdflib.jmf.JDFMessage;
import org.cip4.jdflib.node.JDFAncestor;
import org.cip4.jdflib.node.JDFNode;
import org.cip4.jdflib.pool.JDFResourcePool;
import org.cip4.jdflib.resource.JDFPart;
import org.cip4.jdflib.resource.JDFResource;
import org.cip4.jdflib.resource.process.JDFGeneralID;
import org.cip4.jdflib.resource.process.JDFPreview;
import org.cip4.jdflib.util.ContainerUtil;
import org.cip4.jdflib.util.EnumUtil;
import org.cip4.jdflib.util.JDFDate;
import org.cip4.jdflib.util.JDFDuration;
import org.cip4.jdflib.util.StringUtil;
import org.cip4.jdflib.util.URLReader;
import org.cip4.jdflib.util.VectorMap;
import org.w3c.dom.Attr;
import org.w3c.dom.DOMException;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

/**
 * @author Dietrich Mucha
 * 
 * JDFElement contains generic JDF element functionality in general only elements in the JDF namespace will inherit from JDFElement
 */

public class JDFElement extends KElement
{
	/**
	 * set the ID generation algorithm to include a date
	 * 
	 * @param bLong if true (default), the date and time is used to generate long IDs
	 */
	static public void setLongID(final boolean bLong)
	{
		KElement.setLongID(bLong);
	}

	private static final long serialVersionUID = 1L;
	private static final Log jLog = LogFactory.getLog(JDFElement.class);
	private static EnumVersion defaultVersion = EnumVersion.Version_1_4;

	private static AtrInfoTable[] atrInfoTable = new AtrInfoTable[7];
	static
	{
		atrInfoTable[0] = new AtrInfoTable(AttributeName.SETTINGSPOLICY, 0x33333311, AttributeInfo.EnumAttributeType.enumeration, EnumSettingsPolicy.getEnum(0), null);
		atrInfoTable[1] = new AtrInfoTable(AttributeName.COMMENTURL, 0x33333333, AttributeInfo.EnumAttributeType.URL, null, null);
		atrInfoTable[2] = new AtrInfoTable(AttributeName.DESCRIPTIVENAME, 0x33333333, AttributeInfo.EnumAttributeType.string, null, null);
		atrInfoTable[3] = new AtrInfoTable(AttributeName.BESTEFFORTEXCEPTIONS, 0x33333331, AttributeInfo.EnumAttributeType.NMTOKENS, null, null);
		atrInfoTable[4] = new AtrInfoTable(AttributeName.MUSTHONOREXCEPTIONS, 0x33333331, AttributeInfo.EnumAttributeType.NMTOKENS, null, null);
		atrInfoTable[5] = new AtrInfoTable(AttributeName.OPERATORINTERVENTIONEXCEPTIONS, 0x33333331, AttributeInfo.EnumAttributeType.NMTOKENS, null, null);
		atrInfoTable[6] = new AtrInfoTable(JDFConstants.XMLNS, 0x33333333, AttributeInfo.EnumAttributeType.URI, null, null);
	}

	/**
	 * @return 
	 */
	protected AttributeInfo getTheAttributeInfo()
	{
		return getTheAttributeInfo_JDFElement();
	}

	/**
	 * public version of getTheAttributeInfo
	 * @return
	 */
	public AttributeInfo getAttributeInfo()
	{
		return getTheAttributeInfo();
	}

	/**
	 * returns the data type of a given attribute
	 * @param attributeName the localname of the attribute to check
	 * @return the data type of attributeName
	 */
	public EnumAttributeType getAtrType(final String attributeName)
	{
		final AttributeInfo ai = getTheAttributeInfo();
		return ai.getAttributeType(attributeName);
	}

	/**
	 * get the first JDF version where attribute name or element name is valid
	 * @param eaName attribute name
	 * @param bElement true - get ElementInfo, false - get AttributeInfo
	 * @return JDF version, Version_1_0 if no Info is found
	 */
	public EnumVersion getFirstVersion(final String eaName, final boolean bElement)
	{
		EnumVersion v = null;
		if (bElement)
		{
			final ElementInfo ei = getTheElementInfo();
			v = ei.getFirstVersion(eaName);
		}
		else
		{
			final AttributeInfo ai = getTheAttributeInfo();
			v = ai.getFirstVersion(eaName);
		}
		if (v == null)
		{
			v = EnumVersion.Version_1_0;
		}
		return v;
	}

	/**
	 * Get the dirty status of this element
	 * @return boolean true if dirty
	 */
	@Override
	public boolean isDirty()
	{
		final XMLDocUserData usrDat = getXMLDocUserData();
		if (usrDat != null)
		{
			return usrDat.isDirty(this);
		}
		return false;
	}

	/**
	 * Set this element as dirty
	 * @param bAttribute if true, only attributes are dirty, else also sub-elements
	 */
	@Override
	public void setDirty(final boolean bAttribute)
	{
		((DocumentJDFImpl) ownerDocument).bGlobalDirtyFlag = true;
		if (!((DocumentXMLImpl) ownerDocument).bGlobalDirtyPolicy)
		{
			final XMLDocUserData usrDat = getXMLDocUserData();
			if (usrDat != null)
			{
				usrDat.setDirty(this, bAttribute);
			}
		}
	}

	/**
	 * get/create the associated XMLDocUserData
	 * @return the XMLDocUserData of this
	 */
	protected XMLDocUserData getXMLDocUserData()
	{
		return (ownerDocument == null) ? null : ((DocumentJDFImpl) ownerDocument).getMyUserData();
	}

	/**
	 * get the last JDF version where attribute name or element name is valid
	 * @param eaName attribute name
	 * @param bElement true - get ElementInfo, false - get AttributeInfo
	 * @return JDF version, Version_1_0 if no Info is found
	 */
	public EnumVersion getLastVersion(final String eaName, final boolean bElement)
	{
		EnumVersion v = null;
		if (bElement)
		{
			final ElementInfo ei = getTheElementInfo();
			v = ei.getLastVersion(eaName);
		}
		else
		{
			final AttributeInfo ai = getTheAttributeInfo();
			v = ai.getLastVersion(eaName);
		}
		return v;
	}

	/**
	 * Comma separated list of all required attributes. KElement is generic, therefore the list is empty
	 * @return String the comma separated list of required attribute keys
	 */
	public VString requiredAttributes()
	{
		return getTheAttributeInfo().requiredAttribs();
	}

	/**
	 * Comma separated list of all optional attributes. KElement is generic, therefore only the XML generic attributes are listed <br>
	 * xmlns: the namespace declaration
	 * @return String the comma separated list of optional attribute keys
	 */
	public VString optionalAttributes()
	{
		return getTheAttributeInfo().optionalAttribs();
	}

	/**
	 * map of all defaults from the schema
	 * @return JDFAttributeMap the comma separated list of deprecated attribute keys
	 */
	public JDFAttributeMap getDefaultAttributeMap()
	{
		return getTheAttributeInfo().getDefaultAttributeMap();
	}

	/**
	 * @return
	 */
	protected AttributeInfo getTheAttributeInfo_JDFElement()
	{
		final AttributeInfo ai = new AttributeInfo(atrInfoTable);
		ai.setVersion(getAIVersion());
		return ai;
	}

	/**
	 * @return
	 */
	private EnumVersion getAIVersion()
	{
		Document d = getOwnerDocument();
		Element e = d.getDocumentElement();
		String s = e.getAttribute(AttributeName.VERSION);
		return EnumVersion.getEnum(s);
	}

	private static ElemInfoTable[] elemInfoTable = new ElemInfoTable[3];
	static
	{
		elemInfoTable[0] = new ElemInfoTable(ElementName.COMMENT, 0x33333333);
		elemInfoTable[1] = new ElemInfoTable(ElementName.PREVIEW, 0x33331111);
		// note the incorrect version which must be retained since this was moved from resource
		elemInfoTable[2] = new ElemInfoTable(ElementName.GENERALID, 0x33333111);

	}

	protected ElementInfo getTheElementInfo_JDFElement()
	{
		final ElementInfo ei = new ElementInfo(null, elemInfoTable);
		ei.setVersion(getAIVersion());
		return ei;
	}

	/**
	 * list of all deprecated attributes. KElement is generic, therefore the list is empty
	 * @return String the comma separated list of deprecated attribute keys
	 */
	public VString deprecatedAttributes()
	{
		return getTheAttributeInfo().deprecatedAttribs();
	}

	/**
	 * Comma separated list of all deprecated attributes. KElement is generic, therefore the list is empty
	 * @return String the comma separated list of deprecated attribute keys
	 */
	public VString prereleaseAttributes()
	{
		return getTheAttributeInfo().prereleaseAttribs();
	}

	/**
	 * KElement is generic, therefore the list is empty
	 * 
	 * @return String the comma separated list of known attribute keys
	 */
	public VString knownAttributes()
	{
		return getTheAttributeInfo().knownAttribs();
	}

	/**
	 * checks if the curent element has other attributes then also present in vReqKeys. If the attribute is not present in vReqKeys, the attribut is added to a
	 * new vector. The new vector is returned if there is no missing element left or the new vector has reached the given size nMax.
	 * @param vReqKeys the vector with the attributes you already have
	 * @param nMax vector with the missing attributes
	 * @return Vector the vector with the missing attributes
	 * @default getMissingAttributeVector(vReqKeys, 9999999)
	 */
	public VString getMissingAttributeVector(final VString vReqKeys, final int nMax)
	{
		final VString vMissing = new VString(); // is a StringVector like
		if (vReqKeys == null || vReqKeys.isEmpty())
		{
			return vMissing; // none required - just return null
		}

		final VString vAtts = getAttributeVector();
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
			if (!vAtts.contains(prefix + req) && !vAtts.contains(req) && (!req.equals(JDFConstants.XMLNS) || super.getNamespaceURI() == null))
			{
				vMissing.addElement(prefix + req);
			}
		}
		return vMissing;
	}

	/**
	 * checks if the curent element has other attributes that are present in vReqKeys. If the attribute is present in vReqKeys, the attribut is added to a new
	 * vector. The new vector is returned if there is no missing element left or the new vector has reached the given size nMax.
	 * @param vReqKeys the vector with the attributes you already have
	 * @param nMax vector with the missing attributes
	 * @return Vector the vector with the missing attributes
	 * @default getMissingAttributeVector(vReqKeys, 9999999)
	 */
	private VString getMatchingAttributeVector(final VString vReqKeys, final int nMax)
	{
		final VString vAtts = getAttributeVector();
		final VString vMatching = new VString(); // is a StringVector like
		// vReqKeys

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
			final String req = vReqKeys.elementAt(i);
			if (vAtts.contains(prefix + req) || vAtts.contains(req))
			{
				vMatching.addElement(prefix + req);
			}
		}
		return vMatching;
	}

	/**
	 * @return the elementinfo
	 */
	protected ElementInfo getTheElementInfo()
	{
		return getTheElementInfo_JDFElement();
	}

	/**
	 * get the missing elements as a vector
	 * <p>
	 * default: getMissingElements(99999999)
	 * @param nMax maximum value of missing elements to return
	 * @return VString vector with nMax missing elements
	 */
	public VString getMissingElements(final int nMax)
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
	 * Comma separated list of all optional element names; KElement is generic, therefore the list is empty
	 * @return VString the comma separated list of optional element names
	 */
	public VString optionalElements()
	{
		return getTheElementInfo().optionalElements();
	}

	/**
	 * comma separated list of all unique Elements that may occur at most once; KElement is generic, therefore the list is empty
	 * @return String the comma separated list of required element names
	 */
	public VString uniqueElements()
	{
		return getTheElementInfo().uniqueElements();
	}

	/**
	 * comma separated list of all prerelease Elements that may occur in a future version
	 * @return String the comma separated list of required element names
	 */
	public String prereleaseElements()
	{
		final VString v = getTheElementInfo().prereleaseElements();
		return StringUtil.setvString(v, JDFConstants.COMMA, null, null);
	}

	/**
	 * Comma separated list of all prerelease elements.
	 * <p>
	 * default: getPrereleaseElements(99999999)
	 * @param nMax
	 * @return VString vector with nMax missing elements
	 */
	public VString getPrereleaseElements(final int nMax)
	{
		final VString v = getTheElementInfo().prereleaseElements();
		return getMatchingElementVector(v, nMax);
	}

	/**
	 * Vector of deprecated elements below the current element.
	 * <p>
	 * default: getDeprecatedElements(99999999)
	 * @param nMax
	 * @return VString vector with nMax missing elements
	 */
	public VString getDeprecatedElements(final int nMax)
	{
		final VString v = getTheElementInfo().deprecatedElements();
		return getMatchingElementVector(v, nMax);
	}

	/**
	 * Comma separated list of all known element names;
	 * @return String the comma separated list of known element names
	 */
	public VString knownElements()
	{
		final VString s = requiredElements();
		if (s.size() == 0)
			return optionalElements();
		s.appendUnique(optionalElements());
		return s;
	}

	/**
	 * Returns a vector with matching elements
	 * <p>
	 * default: getMissingElementVector(vReqKeys, 9999999)
	 * @param vReqKeys vector with all element wich are required
	 * @param nMax maximum amount of missing element inside the returned vector
	 * @return Vector the vector with the missing elements
	 */
	private VString getMatchingElementVector(final VString vReqKeys, final int nMax)
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
	 * This function first, gets all required attributes and then compare them with the attributes present and returns a Vector with the missing attributes
	 * @param nMax maximum size of the returned Vector
	 * @return VString vector with the missing attribute names
	 * @default getMissingAttributes(9999999)
	 */
	public VString getMissingAttributes(final int nMax)
	{
		final VString v = getTheAttributeInfo().requiredAttribs();
		return getMissingAttributeVector(v, nMax);
	}

	/**
	 * This function first, gets all deprecated attributes
	 * @param nMax maximum size of the returned Vector
	 * @return Vector vector with the deprecated attributes
	 * @default getMissingAttributes(9999999)
	 */
	public VString getDeprecatedAttributes(final int nMax)
	{
		final VString v = deprecatedAttributes();
		return getMatchingAttributeVector(v, nMax);
	}

	/**
	 * Get the unknown attributes
	 * <p>
	 * default: getUnknownAttributes(bIgnorePrivate, 9999999)
	 * @param bIgnorePrivate if true the private attributes will be ignored
	 * @param nMax mamimum amount of unknown attributes to return
	 * @return Vector a vector with all unknown atttributes the Element have
	 */
	public VString getUnknownAttributes(final boolean bIgnorePrivate, final int nMax)
	{
		final VString vKnownAttribs = knownAttributes();
		final VString v = bIgnorePrivate ? new VString(StringUtil.tokenize(" :JDF", JDFConstants.COLON, false)) : new VString();
		return getUnknownAttributeVector(vKnownAttribs, v, nMax);
	}

	/**
	 * Gets the unknown attributes
	 * <p>
	 * default: getUnknownAttributeVector(vKnownKeys, new Vector(), 99999999)
	 * @param vKnownKeys vector with all known keys
	 * @param vInNameSpace vector with all namespaces to search in
	 * @param nMax maximum amount of unknown attributes to return
	 * @return vector with maximum nMax unknown Attributes
	 */
	public VString getUnknownAttributeVector(final VString vKnownKeys, final VString vInNameSpace, int nMax)
	{
		if (nMax < 0)
		{
			nMax = Integer.MAX_VALUE;
		}

		final VString vAtts = getAttributeVector_KElement();
		final VString vUnknown = new VString();
		if (vKnownKeys.contains("*"))
		{
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
			final String strAtts = vAtts.elementAt(i);
			final String ns = KElement.xmlnsPrefix(strAtts);
			if ((JDFConstants.XSI.equals(ns)) || JDFConstants.XMLNS.equals(ns))
			{
				continue;
			}

			if (bAllNS || ns == null || vInNameSpace.contains(ns))
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
	 * @param bIgnorePrivate true, to ignore the private elements
	 * @param nMax maximum number of elements in the vector returned
	 * @return Vector a vector with nMax unknown elements in the actual element.
	 */
	public VString getUnknownElements(final boolean bIgnorePrivate, final int nMax)
	{
		final VString v1 = knownElements();
		final VString v2 = StringUtil.tokenize(" :JDF", JDFConstants.COLON, false);

		return getUnknownElementVector(v1, bIgnorePrivate ? v2 : new VString(), nMax);
	}

	/**
	 * Get a vector with the unknown elements
	 * <p>
	 * default: getUnknownElementVector(vKnownKeys, vInNameSpace, 9999999)
	 * @param vKnownKeys vector of all known elements
	 * @param vInNameSpace vector of all namespaces to search in
	 * @param nMax maximum amount of elements to return
	 * @return Vector a vector containing the unknown elements
	 */
	public VString getUnknownElementVector(final VString vKnownKeys, final VString vInNameSpace, final int nMax)
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
				if (ns == null)
				{
					ns = JDFConstants.EMPTYSTRING;
				}

				if (bAllNS || (vInNameSpace.contains(ns)))
				{
					if (!vKnownKeys.contains(attr))
					{
						vUnknown.addElement(attr);
					}
				}
			}
			while (vUnknown.size() < nMax && ++i < vAtts.size());
		}

		return vUnknown;
	}

	/**
	 * Get the EnumAttributeType for an attribute
	 * @param key the local name of the attribute
	 * @return EnumAttributeType of the attribute
	 */
	public ValuedEnum getTypeForAttribute(final String key)
	{
		return getTheAttributeInfo().getAttributeType(key);
	}

	/**
	 * is the attribute valid and of type iType. iType is of type EnumAttributeType but may be expanded in child classes
	 * <p>
	 * default: validAttribute(key, null)
	 * @param key the attribute name
	 * @param nameSpaceURI attribute namespace uri
	 * @param level the validation level
	 * @return boolean: true if the attribute is valid
	 */
	public boolean validAttribute(final String key, final String nameSpaceURI, final EnumValidationLevel level)
	{
		return getTheAttributeInfo().validAttribute(key, getAttribute(key, nameSpaceURI, null), level);
	}

	/**
	 * Get the ValuedEnum for an enumerated attribute
	 * @param key the local name of the attribute
	 * @return ValuedEnum.Unknown of the requested attribute or null if the attribute is not an enum
	 */
	public ValuedEnum getEnumforAttribute(final String key)
	{
		return getTheAttributeInfo().getAttributeEnum(key);
	}

	/**
	 * remove all default attributes from this i.e. all attributes whose value matches the schema default
	 * @param bRecurse if true, also recurse subelements, else only local
	 */
	public void eraseDefaultAttributes(final boolean bRecurse)
	{
		final JDFAttributeMap aMap = getDefaultAttributeMap();
		if (aMap != null)
		{
			final NamedNodeMap nm = getAttributes();
			if (nm != null)
			{
				final int siz = nm.getLength();
				for (int i = siz - 1; i >= 0; i--)
				{
					final Node item = nm.item(i);
					final String attVal = item.getNodeName();
					if (aMap.containsKey(attVal) && item.getNodeValue().equals(aMap.get(attVal)))
					{
						removeAttribute(attVal);
					}
				}
			}
		}

		if (bRecurse)
		{
			KElement e = getFirstChildElement();
			while (e != null)
			{
				if (e instanceof JDFElement)
					((JDFElement) e).eraseDefaultAttributes(true);
				e = e.getNextSiblingElement();
			}
		}
	}

	/**
	 * Checks, if this has any missing attributes
	 * @return boolean true, if one or more attributes are missing
	 */
	public boolean hasMissingAttributes()
	{
		return getMissingAttributes(1).size() > 0;
	}

	/**
	 * Checks, if this has are any unknown attributes
	 * @param bIgnorePrivate if true, looks only in default and JDF namespaces
	 * @return boolean true, if one or more attributes are unknown
	 */
	public boolean hasUnknownAttributes(final boolean bIgnorePrivate)
	{
		return getUnknownAttributes(bIgnorePrivate, 1).size() > 0;
	}

	/**
	 * Tests, whether 'this' has any missing direct child elements
	 * @return boolean true, if one or more direct child elements are missing
	 */
	public boolean hasMissingElements()
	{
		return getMissingElements(1).size() > 0;
	}

	/**
	 * Tests, whether 'this' has any unknown direct child elements
	 * @param bIgnorePrivate if true, only looks in default and JDF namespaces
	 * @return boolean true if there are any unknown elements (in respect to the parameter)
	 */
	public boolean hasUnknownElements(final boolean bIgnorePrivate)
	{
		return getUnknownElements(bIgnorePrivate, 1).size() > 0;
	}

	/**
	 * This function first, gets all prerelease attributes It ignores any atrributes that have been added by a schema parser
	 * @param nMax maximum size of the returned Vector
	 * @return Vector vector with the prerelease attributes
	 * @default getMissingAttributes(9999999)
	 */
	public VString getPrereleaseAttributes(final int nMax)
	{
		final VString v = getMatchingAttributeVector(prereleaseAttributes(), nMax);
		AttributeInfo ai = null;
		if (!v.isEmpty())
		{
			ai = getTheAttributeInfo();
		}

		// ideally we would find a better method to recognize schema placed
		// attributes
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
	 * Get a vector of direct child element names that exist but are unknown in this element.
	 * @return a <code>vString</code> that contains missing element keys
	 */
	public VString getInsertElements()
	{
		final VString vKnownElements = knownElements();
		final VString vUniqueElements = uniqueElements();
		final VString vStrRet = getInsertElementVector(vKnownElements, vUniqueElements);
		return vStrRet;
	}

	/**
	 * get a <code>vString</code> vector of direct child element names that may be inserted in this element. This means that a element which is already present
	 * as a child and has a max occurs of 1 will not be part of the returned <code>vString</code>
	 * @param vKnownKeys a <code>vString</code> list of known element tag names. If you want a complete list of all known Elements use
	 * <code>KnownElements()</code> from KElement to get a list. Or call <code>GetInsertElements(int nMax)</code>
	 * @param vUnique <code>vString</code> a list of elements that may occur only once. Use UniqueElements() to get a <code>String</code> which contains all
	 * valid unique Elements from this.
	 * @return VString a vector of strings that contains insertable element keys
	 * @see #knownElements()
	 * @see #getInsertElements()
	 * @see #uniqueElements()
	 */
	public VString getInsertElementVector(final VString vKnownKeys, final VString vUnique)
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
	 * Returns the type of the given attribute for the latest JDF version. Attribute types of previous versions have to be provided by attribute-specific
	 * functions (if necessary).
	 * @param attributeName name of the attribute
	 * @return EnumAttributeType the attribute's type
	 */
	public AttributeInfo.EnumAttributeType attributeType(final String attributeName)
	{
		return getTheAttributeInfo().getAttributeType(attributeName);
	}

	/**
	 * Get the vector of valid attribute values for an enumerated attribute
	 * @param key the local name of the attribute
	 * @return vector of valid names, null if key is not an enumeration
	 */
	public VString getNamesVector(final String key)
	{
		final ValuedEnum enu = getEnumforAttribute(key);
		if (enu != null)
		{
			return EnumUtil.getNamesVector(enu.getClass());
		}
		return null;
	}

	private static final DocumentJDFImpl m_dummyDocumentJDFImpl = new DocumentJDFImpl();

	private boolean checkInstance(final VString vElements, final String requiredKey)
	{
		final Class<?> requiredClass = m_dummyDocumentJDFImpl.getFactoryClass(requiredKey);
		Class<?> elementClass = null;
		final Iterator<String> elemIter = vElements.iterator();
		while (elemIter.hasNext() && !requiredClass.equals(elementClass))
		{
			final String elemName = elemIter.next();
			elementClass = m_dummyDocumentJDFImpl.getFactoryClass(elemName);
		}

		return requiredClass.equals(elementClass);
	}

	/**
	 * Returns a vector with missing elements
	 * <p>
	 * default: getMissingElementVector(vRequiredKeys, 9999999)
	 * @param vRequiredKeys vector with all element which are required
	 * @param nMax maximum amount of missing element inside the returned vector
	 * @return Vector the vector with the missing elements
	 */
	public VString getMissingElementVector(final VString vRequiredKeys, final int nMax)
	{
		final VString vMissing = new VString();
		if (vRequiredKeys == null || vRequiredKeys.isEmpty())
		{
			return vMissing; // none required - just return null
		}
		final VString vElements = getElementNameVector();

		for (int i = 0; i < vRequiredKeys.size() && vMissing.size() < nMax; i++)
		{
			final String requiredKey = vRequiredKeys.elementAt(i);
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

	/**
	 * Constructor for JDFElement
	 * 
	 * @param myOwnerDocument the DOM document where this elements will be inserted
	 * @param qualifiedName the qualified name of the element (see www.w3.org/XML/)
	 */
	public JDFElement(final CoreDocumentImpl myOwnerDocument, final String qualifiedName)
	{
		super(myOwnerDocument, null, qualifiedName);
	}

	/**
	 * Constructor for JDFElement
	 * 
	 * @param myOwnerDocument the DOM document where this elements will be inserted
	 * @param myNamespaceURI the namespace of the elements to (see www.w3.org/XML/)
	 * @param qualifiedName the qualified name of the element
	 */
	public JDFElement(final CoreDocumentImpl myOwnerDocument, final String myNamespaceURI, final String qualifiedName)
	{
		super(myOwnerDocument, myNamespaceURI, qualifiedName);
	}

	/**
	 * Constructor for JDFElement
	 * 
	 * @param myOwnerDocument the DOM document where this elements will be inserted
	 * @param myNamespaceURI the namespace of the elements to create
	 * @param qualifiedName the qualified name of the element (see www.w3.org/XML/)
	 * @param myLocalName the local name of the element (see www.w3.org/XML/)
	 */
	public JDFElement(final CoreDocumentImpl myOwnerDocument, final String myNamespaceURI, final String qualifiedName, final String myLocalName)
	{
		super(myOwnerDocument, myNamespaceURI, qualifiedName, myLocalName);
	}

	/**
	 * Boolean Enumeration from JDF Spec Orientation of a physical resource.
	 */
	@SuppressWarnings("unchecked")
	public static final class EnumBoolean extends ValuedEnum
	{
		private static final long serialVersionUID = 1L;
		private static int m_startValue = 0;

		private EnumBoolean(final String name)
		{
			super(name.toLowerCase(), m_startValue++);
		}

		/**
		 * @param enumName the name of the enum object to return
		 * @return the enum object if enumName is valid. Otherwise null
		 */
		public static EnumBoolean getEnum(final String enumName)
		{
			return (EnumBoolean) getEnum(EnumBoolean.class, enumName.toLowerCase());
		}

		/**
		 * @param enumValue the value of the enum object to return
		 * @return the enum object if enumName is valid. Otherwise null
		 */
		public static EnumBoolean getEnum(final int enumValue)
		{
			return (EnumBoolean) getEnum(EnumBoolean.class, enumValue);
		}

		/**
		 * @return a map of all orientation enums
		 */
		public static Map getEnumMap()
		{
			return getEnumMap(EnumBoolean.class);
		}

		/**
		 * @return a list of all orientation enums
		 */
		public static List getEnumList()
		{
			return getEnumList(EnumBoolean.class);
		}

		/**
		 * @return an iterator over the enum objects
		 */
		public static Iterator iterator()
		{
			return iterator(EnumBoolean.class);
		}

		// enums for validation of booleans
		/**
		 * 
		 */
		public static final EnumBoolean True = new EnumBoolean(JDFConstants.BOOLEAN_TRUE);
		/**
		 * 
		 */
		public static final EnumBoolean False = new EnumBoolean(JDFConstants.BOOLEAN_FALSE);
	}

	/**
	 * Orientation Enumeration <br>
	 * Orientation of a physical resource.
	 */
	@SuppressWarnings("unchecked")
	public static final class EnumOrientation extends ValuedEnum
	{
		private static final long serialVersionUID = 1L;
		private static int m_startValue = 0;

		private EnumOrientation(final String name)
		{
			super(name, m_startValue++);
		}

		/**
		 * @param enumName the name of the enum object to return
		 * @return the enum object if enumName is valid. Otherwise null
		 */
		public static EnumOrientation getEnum(final String enumName)
		{
			return (EnumOrientation) getEnum(EnumOrientation.class, enumName);
		}

		/**
		 * @param enumValue the value of the enum object to return
		 * @return the enum object if enumName is valid. Otherwise null
		 */
		public static EnumOrientation getEnum(final int enumValue)
		{
			return (EnumOrientation) getEnum(EnumOrientation.class, enumValue);
		}

		/**
		 * @return a map of all orientation enums
		 */
		public static Map<String, EnumOrientation> getEnumMap()
		{
			return getEnumMap(EnumOrientation.class);
		}

		/**
		 * @return a list of all orientation enums
		 */
		public static List<EnumOrientation> getEnumList()
		{
			return getEnumList(EnumOrientation.class);
		}

		/**
		 * @return an iterator over the enum objects
		 */
		public static Iterator<EnumOrientation> iterator()
		{
			return iterator(EnumOrientation.class);
		}

		// enums accordng to JDF spec A.3.3.3, Table 3-3 Orientation
		/**
		 * 
		 */
		public static final EnumOrientation Flip0 = new EnumOrientation(JDFConstants.ORIENTATION_FLIP0);
		/**
		 * 
		 */
		public static final EnumOrientation Flip90 = new EnumOrientation(JDFConstants.ORIENTATION_FLIP90);
		/**
		 * 
		 */
		public static final EnumOrientation Flip180 = new EnumOrientation(JDFConstants.ORIENTATION_FLIP180);
		/**
		 * 
		 */
		public static final EnumOrientation Flip270 = new EnumOrientation(JDFConstants.ORIENTATION_FLIP270);
		/**
		 * 
		 */
		public static final EnumOrientation Rotate0 = new EnumOrientation(JDFConstants.ORIENTATION_ROTATE0);
		/**
		 * 
		 */
		public static final EnumOrientation Rotate90 = new EnumOrientation(JDFConstants.ORIENTATION_ROTATE90);
		/**
		 * 
		 */
		public static final EnumOrientation Rotate180 = new EnumOrientation(JDFConstants.ORIENTATION_ROTATE180);
		/**
		 * 
		 */
		public static final EnumOrientation Rotate270 = new EnumOrientation(JDFConstants.ORIENTATION_ROTATE270);
	}

	/**
	 * XYRelation Enumeration <br>
	 * XML attributes of type XYRelation define the relationship between two ordered numbers. <br>
	 * gt X>Y <br>
	 * ge X>=Y <br>
	 * eq X==Y <br>
	 * le X<=Y <br>
	 * lt X<Y <br>
	 * ne X!=Y <br>
	 * see JDF Spec (Appendix A.3.4) for latest changes
	 */
	@SuppressWarnings("unchecked")
	public static final class EnumXYRelation extends ValuedEnum
	{
		private static final long serialVersionUID = 1L;
		private static int m_startValue = 0;

		private EnumXYRelation(final String name)
		{
			super(name, m_startValue++);
		}

		/**
		 * @param enumName the name of the enum object to return
		 * @return the enum object if enumName is valid. Otherwise null
		 */
		public static EnumXYRelation getEnum(final String enumName)
		{
			return (EnumXYRelation) getEnum(EnumXYRelation.class, enumName);
		}

		/**
		 * @param enumValue the value of the enum object to return
		 * @return the enum object if enumName is valid. Otherwise null
		 */
		public static EnumXYRelation getEnum(final int enumValue)
		{
			return (EnumXYRelation) getEnum(EnumXYRelation.class, enumValue);
		}

		/**
		 * @return a map of all XYRelation enums
		 */
		public static Map getEnumMap()
		{
			return getEnumMap(EnumXYRelation.class);
		}

		/**
		 * @return a list of all XYRelation enums
		 */
		public static List getEnumList()
		{
			return getEnumList(EnumXYRelation.class);
		}

		/**
		 * @return an iterator over the enum objects
		 */
		public static Iterator iterator()
		{
			return iterator(EnumXYRelation.class);
		}

		// enums accordng to JDF spec 3.1.2, Table 3-3 Status

		/**
		 * 
		 */
		public static final EnumXYRelation gt = new EnumXYRelation(JDFConstants.XYRELATION_GT);
		/**
		 * 
		 */
		public static final EnumXYRelation ge = new EnumXYRelation(JDFConstants.XYRELATION_GE);
		/**
		 * 
		 */
		public static final EnumXYRelation eq = new EnumXYRelation(JDFConstants.XYRELATION_EQ);
		/**
		 * 
		 */
		public static final EnumXYRelation le = new EnumXYRelation(JDFConstants.XYRELATION_LE);
		/**
		 * 
		 */
		public static final EnumXYRelation lt = new EnumXYRelation(JDFConstants.XYRELATION_LT);
		/**
		 * 
		 */
		public static final EnumXYRelation ne = new EnumXYRelation(JDFConstants.XYRELATION_NE);

		/**
		 * xyRelation - tests if relation x/y matches XYRelation enumeration value
		 * 
		 * @param x x in XYRelation ( x/y )
		 * @param y y in XYRelation ( x/y )
		 * @param nt negative tolerance
		 * @param pt positive tolerance
		 * @return true, if relation x/y matches specified enumerated value
		 */
		public boolean evaluateXY(final double x, final double y, final double nt, final double pt)
		{
			if (this == EnumXYRelation.gt)
			{
				return x + pt > y - nt;
			}
			else if (this == EnumXYRelation.ge)
			{
				return x + pt >= y - nt;
			}
			else if (this == EnumXYRelation.eq)
			{
				return x >= y - nt && x <= y + pt;
			}
			else if (this == EnumXYRelation.le)
			{
				return x - nt <= y + pt;
			}
			else if (this == EnumXYRelation.lt)
			{
				return x - nt < y + pt;
			}
			else if (this == EnumXYRelation.ne)
			{
				return x < y - nt || x > y + pt;
			}
			else
			{
				return true;
			}
		}

	}

	/**
	 * Separation Enumeration identifies the separation name.
	 * <p>
	 * Possible values include:
	 * <p>
	 * <ul>
	 * <li><b>Composite</b> - Non-separated resource.
	 * <li><b>Separated</b> - The resource is separated, but the separation definition is handled internally by the resource, such as a PDF file that contains
	 * SeparationInfo dictionaries.
	 * <li><b>Cyan</b> - Process color.
	 * <li><b>Magenta</b> - Process color.
	 * <li><b>Yellow</b> - Process color.
	 * <li><b>Black</b> - Process color.
	 * <li><b>Red</b> - Additional process color.
	 * <li><b>Green</b> - Additional process color.
	 * <li><b>Blue</b> - Additional process color.
	 * <li><b>Orange</b> - Additional process color.
	 * <li><b>Spot</b> - Generic spot color. Used when the exact nature of the spot color is unknown.
	 * <li><b>Varnish</b> - Varnish.
	 * </ul>
	 * Other values may be any separation name defined in the Name attribute of a {@link org.cip4.jdflib.resource.process.JDFColor} element in the
	 * {@link org.cip4.jdflib.resource.process.JDFColorPool}. When Separation is applied to a ColorantControlLink, it defines an implicit partition that selects
	 * a subset of separations for the process that is described by the ColorantControl.
	 */
	@SuppressWarnings("unchecked")
	public static final class EnumSeparation extends ValuedEnum
	{
		private static final long serialVersionUID = 1L;
		private static int m_startValue = 0;

		private EnumSeparation(final String name)
		{
			super(name, m_startValue++);
		}

		/**
		 * @param enumName the name of the enum object to return
		 * @return the enum object if enumName is valid. Otherwise null
		 */
		public static EnumSeparation getEnum(final String enumName)
		{
			return (EnumSeparation) getEnum(EnumSeparation.class, enumName);
		}

		/**
		 * @param enumValue the value of the enum object to return
		 * @return the enum object if enumName is valid. Otherwise null
		 */
		public static EnumSeparation getEnum(final int enumValue)
		{
			return (EnumSeparation) getEnum(EnumSeparation.class, enumValue);
		}

		/**
		 * @return a map of all orientation enums
		 */
		public static Map getEnumMap()
		{
			return getEnumMap(EnumSeparation.class);
		}

		/**
		 * @return a list of all orientation enums
		 */
		public static List getEnumList()
		{
			return getEnumList(EnumSeparation.class);
		}

		/**
		 * @return an iterator over the enum objects
		 */
		public static Iterator iterator()
		{
			return iterator(EnumSeparation.class);
		}

		// enums accordng to JDF spec Table 3-28: Contents of the Part element
		/** * */
		public static final EnumSeparation Cyan = new EnumSeparation(JDFConstants.SEPARATION_CYAN);
		/** * */
		public static final EnumSeparation Magenta = new EnumSeparation(JDFConstants.SEPARATION_MAGENTA);
		/** * */
		public static final EnumSeparation Yellow = new EnumSeparation(JDFConstants.SEPARATION_YELLOW);
		/** * */
		public static final EnumSeparation Black = new EnumSeparation(JDFConstants.SEPARATION_BLACK);
		/** * */
		public static final EnumSeparation Red = new EnumSeparation(JDFConstants.SEPARATION_RED);
		/** * */
		public static final EnumSeparation Green = new EnumSeparation(JDFConstants.SEPARATION_GREEN);
		/** * */
		public static final EnumSeparation Blue = new EnumSeparation(JDFConstants.SEPARATION_BLUE);
		/** * */
		public static final EnumSeparation Orange = new EnumSeparation(JDFConstants.SEPARATION_ORANGE);
		/** * */
		public static final EnumSeparation Spot = new EnumSeparation(JDFConstants.SEPARATION_SPOT);
		/** * */
		public static final EnumSeparation Varnish = new EnumSeparation(JDFConstants.SEPARATION_VARNISH);
	}

	/**
	 * Enumeration of various pool types
	 */
	@SuppressWarnings("unchecked")
	public static final class EnumPoolType extends ValuedEnum
	{
		private static final long serialVersionUID = 1L;
		private static int m_startValue = 0;

		private EnumPoolType(final String name)
		{
			super(name, m_startValue++);
		}

		/**
		 * @param enumName the name of the enum object to return
		 * @return the enum object if enumName is valid. Otherwise null
		 */
		public static EnumPoolType getEnum(final String enumName)
		{
			return (EnumPoolType) getEnum(EnumPoolType.class, enumName);
		}

		/**
		 * @param enumValue the value of the enum object to return
		 * @return the enum object if enumName is valid. Otherwise null
		 */
		public static EnumPoolType getEnum(final int enumValue)
		{
			return (EnumPoolType) getEnum(EnumPoolType.class, enumValue);
		}

		/**
		 * @return a map of all orientation enums
		 */
		public static Map getEnumMap()
		{
			return getEnumMap(EnumPoolType.class);
		}

		/**
		 * @return a list of all orientation enums
		 */
		public static List getEnumList()
		{
			return getEnumList(EnumPoolType.class);
		}

		/**
		 * @return an iterator over the enum objects
		 */
		public static Iterator iterator()
		{
			return iterator(EnumPoolType.class);
		}

		/** * */
		public static final EnumPoolType RefElement = new EnumPoolType(ElementName.REFELEMENT);
		/** * */
		public static final EnumPoolType ResourcePool = new EnumPoolType(ElementName.RESOURCEPOOL);
		/** * */
		public static final EnumPoolType PipeParams = new EnumPoolType(ElementName.PIPEPARAMS);
		/** * */
		public static final EnumPoolType ResourceLinkPool = new EnumPoolType(ElementName.RESOURCELINKPOOL);
		/** * */
		public static final EnumPoolType AncestorPool = new EnumPoolType(ElementName.ANCESTORPOOL);
		/** * */
		public static final EnumPoolType AuditPool = new EnumPoolType(ElementName.AUDITPOOL);
		/** * */
		public static final EnumPoolType ProductionIntent = new EnumPoolType(ElementName.PRODUCTIONINTENT);
	}

	/**
	 * Enumeration of valid nodestatus types Identifies the status of the node. Possible values are:
	 * <p>
	 * <ul>
	 * <li><b>Waiting</b> - The node may be executed, but it has not completed a test run.
	 * <li><b>TestRunInProgress</b> - The node is currently executing a test run.
	 * <li><b>Ready</b> - As indicated by the successful completion of a test run, all ResourceLinks are correct, required resources are available, and the
	 * parameters of resources are valid. The node is ready to start.
	 * <li><b>FailedTestRun</b> - An error occurred during the test run. Error information is logged in the Notification element, which is an optional
	 * subelement of the AuditPool element described in Section 3.9, AuditPool.
	 * <li><b>Setup</b> - The process represented by this node is currently being set up.
	 * <li><b>InProgress</b> - The node is currently executing.
	 * <li><b>Cleanup</b> - The process represented by this node is currently being cleaned up.
	 * <li><b>Spawned</b> - The node is spawned in the form of a separate spawned JDF. The status Spawned can only be assigned to the original instance of the
	 * spawned job. For details, see Section 4.4, Spawning and Merging.
	 * <li><b>Stopped</b> - Execution has been stopped. If a job is Stopped, running may be resumed later. This status may indicate a break, a pause,
	 * maintenance, or a breakdown - in short, any pause that does not lead the job to be aborted.
	 * <li><b>Completed</b> - Indicates that the node has been executed correctly, and is finished.
	 * <li><b>Aborted</b> - Indicates that the process executing the node has been aborted, which means that execution will not be resumed again.
	 * <li><b>Pool</b> - Indicates that the node processes partitioned resources and that the Status varies depending on the partition keys. Details are
	 * provided in the StatusPool element of the node.
	 * <p>
	 * Derivation of the Status of a parent node from the Status of child nodes is non-trivial and implementation-dependent
	 * </ul>
	 */
	@SuppressWarnings("unchecked")
	public static final class EnumNodeStatus extends ValuedEnum
	{
		private static final long serialVersionUID = 1L;
		private static int m_startValue = 0;

		private EnumNodeStatus(final String name)
		{
			super(name, m_startValue++);
		}

		/**
		 * @param enumName the name of the enum object to return
		 * @return the enum object if enumName is valid. Otherwise null
		 */
		public static EnumNodeStatus getEnum(final String enumName)
		{
			return (EnumNodeStatus) getEnum(EnumNodeStatus.class, enumName);
		}

		/**
		 * @param enumValue the value of the enum object to return
		 * @return the enum object if enumName is valid. Otherwise null
		 */
		public static EnumNodeStatus getEnum(final int enumValue)
		{
			return (EnumNodeStatus) getEnum(EnumNodeStatus.class, enumValue);
		}

		/**
		 * @return a map of all orientation enums
		 */
		public static Map getEnumMap()
		{
			return getEnumMap(EnumNodeStatus.class);
		}

		/**
		 * @return a list of all orientation enums
		 */
		public static List getEnumList()
		{
			return getEnumList(EnumNodeStatus.class);
		}

		/**
		 * returns the queuentrystatus corresponding to a node status
		 * 
		 * @param ns the node status to test agains
		 * @return the queentrystatus that corresponds to ns; may be null in case of pool or part
		 */
		public static EnumQueueEntryStatus getQueueEntryStatus(final EnumNodeStatus ns)
		{
			if (EnumNodeStatus.Waiting.equals(ns))
			{
				return EnumQueueEntryStatus.Waiting;
			}
			if (EnumNodeStatus.TestRunInProgress.equals(ns))
			{
				return EnumQueueEntryStatus.Waiting;
			}
			if (EnumNodeStatus.Ready.equals(ns))
			{
				return EnumQueueEntryStatus.Waiting;
			}
			if (EnumNodeStatus.FailedTestRun.equals(ns))
			{
				return EnumQueueEntryStatus.Aborted;
			}
			if (EnumNodeStatus.Setup.equals(ns))
			{
				return EnumQueueEntryStatus.Running;
			}
			if (EnumNodeStatus.InProgress.equals(ns))
			{
				return EnumQueueEntryStatus.Running;
			}
			if (EnumNodeStatus.Cleanup.equals(ns))
			{
				return EnumQueueEntryStatus.Running;
			}
			if (EnumNodeStatus.Spawned.equals(ns))
			{
				return EnumQueueEntryStatus.Running;
			}
			if (EnumNodeStatus.Suspended.equals(ns))
			{
				return EnumQueueEntryStatus.Suspended;
			}
			if (EnumNodeStatus.Stopped.equals(ns))
			{
				return EnumQueueEntryStatus.Suspended;
			}
			if (EnumNodeStatus.Completed.equals(ns))
			{
				return EnumQueueEntryStatus.Completed;
			}
			if (EnumNodeStatus.Aborted.equals(ns))
			{
				return EnumQueueEntryStatus.Aborted;
			}

			return null; // punt
		}

		/**
		 * @return an iterator over the enum objects
		 */
		public static Iterator iterator()
		{
			return iterator(EnumNodeStatus.class);
		}

		// enums accordng to JDF spec Table 3-4 Contents of a node
		/** * */
		public static final EnumNodeStatus Waiting = new EnumNodeStatus(JDFConstants.WAITING);
		/** * */
		public static final EnumNodeStatus TestRunInProgress = new EnumNodeStatus(JDFConstants.TESTRUNINPROGRESS);
		/** * */
		public static final EnumNodeStatus Ready = new EnumNodeStatus(JDFConstants.READY);
		/** * */
		public static final EnumNodeStatus FailedTestRun = new EnumNodeStatus(JDFConstants.FAILEDTESTRUN);
		/** * */
		public static final EnumNodeStatus Setup = new EnumNodeStatus(JDFConstants.SETUP);
		/** * */
		public static final EnumNodeStatus InProgress = new EnumNodeStatus(JDFConstants.INPROGRESS);
		/** * */
		public static final EnumNodeStatus Cleanup = new EnumNodeStatus(JDFConstants.CLEANUP);
		/** * */
		public static final EnumNodeStatus Spawned = new EnumNodeStatus(JDFConstants.SPAWNED);
		/** * */
		public static final EnumNodeStatus Suspended = new EnumNodeStatus(JDFConstants.SUSPENDED);
		/** * */
		public static final EnumNodeStatus Stopped = new EnumNodeStatus(JDFConstants.STOPPED);
		/** * */
		public static final EnumNodeStatus Completed = new EnumNodeStatus(JDFConstants.COMPLETED);
		/** * */
		public static final EnumNodeStatus Aborted = new EnumNodeStatus(JDFConstants.ABORTED);
		/** * */
		public static final EnumNodeStatus Part = new EnumNodeStatus(JDFConstants.PART);
		/** * */
		public static final EnumNodeStatus Pool = new EnumNodeStatus(JDFConstants.POOL);

		/** 
		 * 
		 * returns true if we are in an end phase (Completed or Aborted)
		 * @param nodeStatus the status to compare
		 * @return true if we are in an end phase (Completed or Aborted)
		 */
		public static boolean isCompleted(EnumNodeStatus nodeStatus)
		{
			return EnumNodeStatus.Completed.equals(nodeStatus) || EnumNodeStatus.Aborted.equals(nodeStatus);
		}
	}

	/**
	 * 
	 * The policy for this element indicates what happens when unsupported settings, (i.e., subelements, attributes or attribute values), are present in the
	 * element. Possible values are:
	 * <p>
	 * <ul>
	 * <li><b>BestEffort</b> - Substitute or ignore unsupported attributes, attribute values, default attribute values, or elements and continue processing the
	 * job.
	 * <li><b>MustHonor</b> - Reject the job when any unsupported attributes, attribute values, or elements are present.
	 * <li><b>OperatorIntervention</b> - Pause job and query the operator when any unsupported attributes, attribute values, or elements are present. If a
	 * device has no operator intervention capabilities, OperatorIntervention is treated as MustHonor.
	 * <p>
	 * If not specified, SettingsPolicy is inherited from the parent element, and if not specified in the parent element or further superior element, the
	 * default value defaults to "BestEffort". In JDF 1.1 SettingsPolicy was specified in "Contents of a JDF node" and
	 * "Contents of the abstract Resource element". It has been removed from JDF node and Resource and been promoted to all JDF elements.
	 */
	@SuppressWarnings("unchecked")
	public static final class EnumSettingsPolicy extends ValuedEnum
	{
		private static final long serialVersionUID = 1L;
		private static int m_startValue = 0;

		private EnumSettingsPolicy(final String name)
		{
			super(name, m_startValue++);
		}

		/**
		 * @param enumName the name of the enum object to return
		 * @return the enum object if enumName is valid. Otherwise null
		 */
		public static EnumSettingsPolicy getEnum(final String enumName)
		{
			return (EnumSettingsPolicy) getEnum(EnumSettingsPolicy.class, enumName);
		}

		/**
		 * @param enumValue the value of the enum object to return
		 * @return the enum object if enumName is valid. Otherwise null
		 */
		public static EnumSettingsPolicy getEnum(final int enumValue)
		{
			return (EnumSettingsPolicy) getEnum(EnumSettingsPolicy.class, enumValue);
		}

		/**
		 * @return a map of all orientation enums
		 */
		public static Map getEnumMap()
		{
			return getEnumMap(EnumSettingsPolicy.class);
		}

		/**
		 * @return a list of all orientation enums
		 */
		public static List getEnumList()
		{
			return getEnumList(EnumSettingsPolicy.class);
		}

		/**
		 * @return an iterator over the enum objects
		 */
		public static Iterator iterator()
		{
			return iterator(EnumSettingsPolicy.class);
		}

		/** * */
		public static final EnumSettingsPolicy BestEffort = new EnumSettingsPolicy(JDFConstants.SETTINGSPOLICY_BESTEFFORT);
		/** * */
		public static final EnumSettingsPolicy MustHonor = new EnumSettingsPolicy(JDFConstants.SETTINGSPOLICY_MUSTHONOR);
		/** * */
		public static final EnumSettingsPolicy OperatorIntervention = new EnumSettingsPolicy(JDFConstants.SETTINGSPOLICY_OPERATORINTERVENTION);
	}

	/**
	 * Colors of preprocessed products such as Wire-O binders and cover leaflets. The entries in the following table may be prefixed by either "Dark" or
	 * "Light". The result may additionally be prefixed by "Clear" to indicate translucent material. For example, "ClearDarkBlue" indicates a translucent dark
	 * blue, "ClearBlue" a translucent blue and "Blue" indicates an opaque blue.
	 * <ul>
	 * <li>Black
	 * <li>Blue
	 * <li>Brown
	 * <li>Buff
	 * <li>Cyan <i>New in JDF 1.2</i>
	 * <li>Gold
	 * <li>Goldenrod
	 * <li>Gray
	 * <li>Green
	 * <li>Ivory
	 * <li>Magenta <i>New in JDF 1.2</i>
	 * <li>MultiColor <i>New in JDF 1.1</i>
	 * <li>Mustard <i>New in JDF 1.1</i>
	 * <li>NoColor
	 * <li>Orange
	 * <li>Pink
	 * <li>Red
	 * <li>Silver
	 * <li>Turquoise
	 * <li>Violet
	 * <li>White
	 * <li>Yellow
	 * </ul>
	 */
	@SuppressWarnings("unchecked")
	public static final class EnumNamedColor extends ValuedEnum
	{
		private static final long serialVersionUID = 1L;
		private static int m_startValue = 0;

		private EnumNamedColor(final String name)
		{
			super(name, m_startValue++);
		}

		/**
		 * @param enumName the name of the enum object to return
		 * @return the enum object if enumName is valid. Otherwise null
		 */
		public static EnumNamedColor getEnum(final String enumName)
		{
			return (EnumNamedColor) getEnum(EnumNamedColor.class, enumName);
		}

		/**
		 * @param enumValue the value of the enum object to return
		 * @return the enum object if enumName is valid. Otherwise null
		 */
		public static EnumNamedColor getEnum(final int enumValue)
		{
			return (EnumNamedColor) getEnum(EnumNamedColor.class, enumValue);
		}

		/**
		 * @return a map of all orientation enums
		 */
		public static Map getEnumMap()
		{
			return getEnumMap(EnumNamedColor.class);
		}

		/**
		 * @return a list of all orientation enums
		 */
		public static List getEnumList()
		{
			return getEnumList(EnumNamedColor.class);
		}

		/**
		 * @return an iterator over the enum objects
		 */
		public static Iterator iterator()
		{
			return iterator(EnumNamedColor.class);
		}

		/** * */
		public static final EnumNamedColor White = new EnumNamedColor(JDFConstants.NAMEDCOLOR_WHITE);
		/** * */
		public static final EnumNamedColor Black = new EnumNamedColor(JDFConstants.NAMEDCOLOR_BLACK);
		/** * */
		public static final EnumNamedColor Gray = new EnumNamedColor(JDFConstants.NAMEDCOLOR_GRAY);
		/** * */
		public static final EnumNamedColor Red = new EnumNamedColor(JDFConstants.NAMEDCOLOR_RED);
		/** * */
		public static final EnumNamedColor Yellow = new EnumNamedColor(JDFConstants.NAMEDCOLOR_YELLOW);
		/** * */
		public static final EnumNamedColor Green = new EnumNamedColor(JDFConstants.NAMEDCOLOR_GREEN);
		/** * */
		public static final EnumNamedColor Blue = new EnumNamedColor(JDFConstants.NAMEDCOLOR_BLUE);
		/** * */
		public static final EnumNamedColor Turquoise = new EnumNamedColor(JDFConstants.NAMEDCOLOR_TURQUOISE);
		/** * */
		public static final EnumNamedColor Violet = new EnumNamedColor(JDFConstants.NAMEDCOLOR_VIOLET);
		/** * */
		public static final EnumNamedColor Orange = new EnumNamedColor(JDFConstants.NAMEDCOLOR_ORANGE);
		/** * */
		public static final EnumNamedColor Brown = new EnumNamedColor(JDFConstants.NAMEDCOLOR_BROWN);
		/** * */
		public static final EnumNamedColor Gold = new EnumNamedColor(JDFConstants.NAMEDCOLOR_GOLD);
		/** * */
		public static final EnumNamedColor Silver = new EnumNamedColor(JDFConstants.NAMEDCOLOR_SILVER);
		/** * */
		public static final EnumNamedColor Pink = new EnumNamedColor(JDFConstants.NAMEDCOLOR_PINK);
		/** * */
		public static final EnumNamedColor Buff = new EnumNamedColor(JDFConstants.NAMEDCOLOR_BUFF);
		/** * */
		public static final EnumNamedColor Ivory = new EnumNamedColor(JDFConstants.NAMEDCOLOR_IVORY);
		/** * */
		public static final EnumNamedColor Goldenrod = new EnumNamedColor(JDFConstants.NAMEDCOLOR_GOLDENROD);
		/** * */
		public static final EnumNamedColor DarkWhite = new EnumNamedColor(JDFConstants.NAMEDCOLOR_DARKWHITE);
		/** * */
		public static final EnumNamedColor DarkBlack = new EnumNamedColor(JDFConstants.NAMEDCOLOR_DARKBLACK);
		/** * */
		public static final EnumNamedColor DarkGray = new EnumNamedColor(JDFConstants.NAMEDCOLOR_DARKGRAY);
		/** * */
		public static final EnumNamedColor DarkRed = new EnumNamedColor(JDFConstants.NAMEDCOLOR_DARKRED);
		/** * */
		public static final EnumNamedColor DarkYellow = new EnumNamedColor(JDFConstants.NAMEDCOLOR_DARKYELLOW);
		/** * */
		public static final EnumNamedColor DarkGreen = new EnumNamedColor(JDFConstants.NAMEDCOLOR_DARKGREEN);
		/** * */
		public static final EnumNamedColor DarkBlue = new EnumNamedColor(JDFConstants.NAMEDCOLOR_DARKBLUE);
		/** * */
		public static final EnumNamedColor DarkTurquoise = new EnumNamedColor(JDFConstants.NAMEDCOLOR_DARKTURQUOISE);
		/** * */
		public static final EnumNamedColor DarkViolet = new EnumNamedColor(JDFConstants.NAMEDCOLOR_DARKVIOLET);
		/** * */
		public static final EnumNamedColor DarkOrange = new EnumNamedColor(JDFConstants.NAMEDCOLOR_DARKORANGE);
		/** * */
		public static final EnumNamedColor DarkBrown = new EnumNamedColor(JDFConstants.NAMEDCOLOR_DARKBROWN);
		/** * */
		public static final EnumNamedColor DarkGold = new EnumNamedColor(JDFConstants.NAMEDCOLOR_DARKGOLD);
		/** * */
		public static final EnumNamedColor DarkSilver = new EnumNamedColor(JDFConstants.NAMEDCOLOR_DARKSILVER);
		/** * */
		public static final EnumNamedColor DarkPink = new EnumNamedColor(JDFConstants.NAMEDCOLOR_DARKPINK);
		/** * */
		public static final EnumNamedColor DarkBuff = new EnumNamedColor(JDFConstants.NAMEDCOLOR_DARKBUFF);
		/** * */
		public static final EnumNamedColor DarkIvory = new EnumNamedColor(JDFConstants.NAMEDCOLOR_DARKIVORY);
		/** * */
		public static final EnumNamedColor DarkGoldenrod = new EnumNamedColor(JDFConstants.NAMEDCOLOR_DARKGOLDENROD);
		/** * */
		public static final EnumNamedColor DarkMustard = new EnumNamedColor(JDFConstants.NAMEDCOLOR_DARKMUSTARD);
		/** * */
		public static final EnumNamedColor LightWhite = new EnumNamedColor(JDFConstants.NAMEDCOLOR_LIGHTWHITE);
		/** * */
		public static final EnumNamedColor LightBlack = new EnumNamedColor(JDFConstants.NAMEDCOLOR_LIGHTBLACK);
		/** * */
		public static final EnumNamedColor LightGray = new EnumNamedColor(JDFConstants.NAMEDCOLOR_LIGHTGRAY);
		/** * */
		public static final EnumNamedColor LightRed = new EnumNamedColor(JDFConstants.NAMEDCOLOR_LIGHTRED);
		/** * */
		public static final EnumNamedColor LightYellow = new EnumNamedColor(JDFConstants.NAMEDCOLOR_LIGHTYELLOW);
		/** * */
		public static final EnumNamedColor LightGreen = new EnumNamedColor(JDFConstants.NAMEDCOLOR_LIGHTGREEN);
		/** * */
		public static final EnumNamedColor LightBlue = new EnumNamedColor(JDFConstants.NAMEDCOLOR_LIGHTBLUE);
		/** * */
		public static final EnumNamedColor LightTurquoise = new EnumNamedColor(JDFConstants.NAMEDCOLOR_LIGHTTURQUOISE);
		/** * */
		public static final EnumNamedColor LightViolet = new EnumNamedColor(JDFConstants.NAMEDCOLOR_LIGHTVIOLET);
		/** * */
		public static final EnumNamedColor LightOrange = new EnumNamedColor(JDFConstants.NAMEDCOLOR_LIGHTORANGE);
		/** * */
		public static final EnumNamedColor LightBrown = new EnumNamedColor(JDFConstants.NAMEDCOLOR_LIGHTBROWN);
		/** * */
		public static final EnumNamedColor LightGold = new EnumNamedColor(JDFConstants.NAMEDCOLOR_LIGHTGOLD);
		/** * */
		public static final EnumNamedColor LightSilver = new EnumNamedColor(JDFConstants.NAMEDCOLOR_LIGHTSILVER);
		/** * */
		public static final EnumNamedColor LightPink = new EnumNamedColor(JDFConstants.NAMEDCOLOR_LIGHTPINK);
		/** * */
		public static final EnumNamedColor LightBuff = new EnumNamedColor(JDFConstants.NAMEDCOLOR_LIGHTBUFF);
		/** * */
		public static final EnumNamedColor LightIvory = new EnumNamedColor(JDFConstants.NAMEDCOLOR_LIGHTIVORY);
		/** * */
		public static final EnumNamedColor LightGoldenrod = new EnumNamedColor(JDFConstants.NAMEDCOLOR_LIGHTGOLDENROD);
		/** * */
		public static final EnumNamedColor LightMustard = new EnumNamedColor(JDFConstants.NAMEDCOLOR_LIGHTMUSTARD);
		/** * */
		public static final EnumNamedColor ClearWhite = new EnumNamedColor(JDFConstants.NAMEDCOLOR_CLEARWHITE);
		/** * */
		public static final EnumNamedColor ClearBlack = new EnumNamedColor(JDFConstants.NAMEDCOLOR_CLEARBLACK);
		/** * */
		public static final EnumNamedColor ClearGray = new EnumNamedColor(JDFConstants.NAMEDCOLOR_CLEARGRAY);
		/** * */
		public static final EnumNamedColor ClearRed = new EnumNamedColor(JDFConstants.NAMEDCOLOR_CLEARRED);
		/** * */
		public static final EnumNamedColor ClearGreen = new EnumNamedColor(JDFConstants.NAMEDCOLOR_CLEARGREEN);
		/** * */
		public static final EnumNamedColor ClearBlue = new EnumNamedColor(JDFConstants.NAMEDCOLOR_CLEARBLUE);
		/** * */
		public static final EnumNamedColor ClearTurquoise = new EnumNamedColor(JDFConstants.NAMEDCOLOR_CLEARTURQUOISE);
		/** * */
		public static final EnumNamedColor ClearViolet = new EnumNamedColor(JDFConstants.NAMEDCOLOR_CLEARVIOLET);
		/** * */
		public static final EnumNamedColor ClearOrange = new EnumNamedColor(JDFConstants.NAMEDCOLOR_CLEARORANGE);
		/** * */
		public static final EnumNamedColor ClearBrown = new EnumNamedColor(JDFConstants.NAMEDCOLOR_CLEARBROWN);
		/** * */
		public static final EnumNamedColor ClearGold = new EnumNamedColor(JDFConstants.NAMEDCOLOR_CLEARGOLD);
		/** * */
		public static final EnumNamedColor ClearSilver = new EnumNamedColor(JDFConstants.NAMEDCOLOR_CLEARSILVER);
		/** * */
		public static final EnumNamedColor ClearPink = new EnumNamedColor(JDFConstants.NAMEDCOLOR_CLEARPINK);
		/** * */
		public static final EnumNamedColor ClearBuff = new EnumNamedColor(JDFConstants.NAMEDCOLOR_CLEARBUFF);
		/** * */
		public static final EnumNamedColor ClearIvory = new EnumNamedColor(JDFConstants.NAMEDCOLOR_CLEARIVORY);
		/** * */
		public static final EnumNamedColor ClearGoldenrod = new EnumNamedColor(JDFConstants.NAMEDCOLOR_CLEARGOLDENROD);
		/** * */
		public static final EnumNamedColor ClearMustard = new EnumNamedColor(JDFConstants.NAMEDCOLOR_CLEARMUSTARD);
		/** * */
		public static final EnumNamedColor ClearDarkWhite = new EnumNamedColor(JDFConstants.NAMEDCOLOR_CLEARDARKWHITE);
		/** * */
		public static final EnumNamedColor ClearDarkBlack = new EnumNamedColor(JDFConstants.NAMEDCOLOR_CLEARDARKBLACK);
		/** * */
		public static final EnumNamedColor ClearDarkGray = new EnumNamedColor(JDFConstants.NAMEDCOLOR_CLEARDARKGRAY);
		/** * */
		public static final EnumNamedColor ClearDarkRed = new EnumNamedColor(JDFConstants.NAMEDCOLOR_CLEARDARKRED);
		/** * */
		public static final EnumNamedColor ClearDarkYellow = new EnumNamedColor(JDFConstants.NAMEDCOLOR_CLEARDARKYELLOW);
		/** * */
		public static final EnumNamedColor ClearDarkGreen = new EnumNamedColor(JDFConstants.NAMEDCOLOR_CLEARDARKGREEN);
		/** * */
		public static final EnumNamedColor ClearDarkBlue = new EnumNamedColor(JDFConstants.NAMEDCOLOR_CLEARDARKBLUE);
		/** * */
		public static final EnumNamedColor ClearDarkTurquoise = new EnumNamedColor(JDFConstants.NAMEDCOLOR_CLEARDARKTURQUOISE);
		/** * */
		public static final EnumNamedColor ClearDarkViolet = new EnumNamedColor(JDFConstants.NAMEDCOLOR_CLEARDARKVIOLET);
		/** * */
		public static final EnumNamedColor ClearDarkOrange = new EnumNamedColor(JDFConstants.NAMEDCOLOR_CLEARDARKORANGE);
		/** * */
		public static final EnumNamedColor ClearDarkBrown = new EnumNamedColor(JDFConstants.NAMEDCOLOR_CLEARDARKBROWN);
		/** * */
		public static final EnumNamedColor ClearDarkGold = new EnumNamedColor(JDFConstants.NAMEDCOLOR_CLEARDARKGOLD);
		/** * */
		public static final EnumNamedColor ClearDarkSilver = new EnumNamedColor(JDFConstants.NAMEDCOLOR_CLEARDARKSILVER);
		/** * */
		public static final EnumNamedColor ClearDarkPink = new EnumNamedColor(JDFConstants.NAMEDCOLOR_CLEARDARKPINK);
		/** * */
		public static final EnumNamedColor ClearDarkBuff = new EnumNamedColor(JDFConstants.NAMEDCOLOR_CLEARDARKBUFF);
		/** * */
		public static final EnumNamedColor ClearDarkIvory = new EnumNamedColor(JDFConstants.NAMEDCOLOR_CLEARDARKIVORY);
		/** * */
		public static final EnumNamedColor ClearDarkGoldenrod = new EnumNamedColor(JDFConstants.NAMEDCOLOR_CLEARDARKGOLDENROD);
		/** * */
		public static final EnumNamedColor ClearLightWhite = new EnumNamedColor(JDFConstants.NAMEDCOLOR_CLEARLIGHTWHITE);
		/** * */
		public static final EnumNamedColor ClearLightBlack = new EnumNamedColor(JDFConstants.NAMEDCOLOR_CLEARLIGHTBLACK);
		/** * */
		public static final EnumNamedColor ClearLightGray = new EnumNamedColor(JDFConstants.NAMEDCOLOR_CLEARLIGHTGRAY);
		/** * */
		public static final EnumNamedColor ClearLightRed = new EnumNamedColor(JDFConstants.NAMEDCOLOR_CLEARLIGHTRED);
		/** * */
		public static final EnumNamedColor ClearLightYellow = new EnumNamedColor(JDFConstants.NAMEDCOLOR_CLEARLIGHTYELLOW);
		/** * */
		public static final EnumNamedColor ClearLightGreen = new EnumNamedColor(JDFConstants.NAMEDCOLOR_CLEARLIGHTGREEN);
		/** * */
		public static final EnumNamedColor ClearLightBlue = new EnumNamedColor(JDFConstants.NAMEDCOLOR_CLEARLIGHTBLUE);
		/** * */
		public static final EnumNamedColor ClearLightTurquoise = new EnumNamedColor(JDFConstants.NAMEDCOLOR_CLEARLIGHTTURQUOISE);
		/** * */
		public static final EnumNamedColor ClearLightViolet = new EnumNamedColor(JDFConstants.NAMEDCOLOR_CLEARLIGHTVIOLET);
		/** * */
		public static final EnumNamedColor ClearLightOrange = new EnumNamedColor(JDFConstants.NAMEDCOLOR_CLEARLIGHTORANGE);
		/** * */
		public static final EnumNamedColor ClearLightBrown = new EnumNamedColor(JDFConstants.NAMEDCOLOR_CLEARLIGHTBROWN);
		/** * */
		public static final EnumNamedColor ClearLightGold = new EnumNamedColor(JDFConstants.NAMEDCOLOR_CLEARLIGHTGOLD);
		/** * */
		public static final EnumNamedColor ClearLightSilver = new EnumNamedColor(JDFConstants.NAMEDCOLOR_CLEARLIGHTSILVER);
		/** * */
		public static final EnumNamedColor ClearLightPink = new EnumNamedColor(JDFConstants.NAMEDCOLOR_CLEARLIGHTPINK);
		/** * */
		public static final EnumNamedColor ClearLightBuff = new EnumNamedColor(JDFConstants.NAMEDCOLOR_CLEARLIGHTBUFF);
		/** * */
		public static final EnumNamedColor ClearLightIvory = new EnumNamedColor(JDFConstants.NAMEDCOLOR_CLEARLIGHTIVORY);
		/** * */
		public static final EnumNamedColor ClearLightGoldenrod = new EnumNamedColor(JDFConstants.NAMEDCOLOR_CLEARLIGHTGOLDENROD);
		/** * */
		public static final EnumNamedColor ClearLightMustard = new EnumNamedColor(JDFConstants.NAMEDCOLOR_CLEARLIGHTMUSTARD);
		/** * */
		public static final EnumNamedColor MultiColor = new EnumNamedColor(JDFConstants.NAMEDCOLOR_MULTICOLOR);
		/** * */
		public static final EnumNamedColor NoColor = new EnumNamedColor(JDFConstants.NAMEDCOLOR_NOCOLOR);
	}

	// **************************************** Methods
	// *********************************************
	/**
	 * toString
	 * 
	 * @return String
	 */
	@Override
	public String toString()
	{
		return "JDFElement[  --> " + super.toString() + " ]";
	}

	/**
	 * Mother of all validators
	 * 
	 * @param level validation level
	 * <ul>
	 * <li>level EnumValidationLevel.None: always return true;
	 * <li>level EnumValidationLevel.Construct: incomplete and null elements are valid.
	 * <li>level EnumValidationLevel.Incomplete: incomplete elements are valid
	 * <li>level EnumValidationLevel.Complete: full validation
	 * <li>level EnumValidationLevel.RecursiveIncomplete: incomplete validation but follow links
	 * <li>level EnumValidationLevel.RecursiveComplete: full validation and follow links downward
	 * </ul>
	 * 
	 * @return boolean the validity of the node
	 */
	public boolean isValid(final EnumValidationLevel level)
	{
		return isValid_JDFElement(level);
	}

	/**
	 * Mother of all validators; this method is for direct access to the JDFElement valid method and short-circuit all overriding virtual methods
	 * 
	 * @param level validation level
	 * <ul>
	 * <li>level EnumValidationLevel.None: always return true;
	 * <li>level EnumValidationLevel.Construct: incomplete and null elements are valid.
	 * <li>level EnumValidationLevel.Incomplete: incomplete elements are valid
	 * <li>level EnumValidationLevel.Complete: full validation
	 * <li>level EnumValidationLevel.RecursiveIncomplete: incomplete validation but follow links
	 * <li>level EnumValidationLevel.RecursiveComplete: full validation and follow links downward
	 * </ul>
	 * 
	 * @return boolean the validity of the node
	 */
	public boolean isValid_JDFElement(final EnumValidationLevel level)
	{
		// there is no explicit extension type --> these are always assumed
		// valid
		final Class<? extends JDFElement> class1 = this.getClass();
		if (class1 == JDFElement.class || class1 == JDFResource.class)
		{
			return true;
		}

		try
		{
			if (getInvalidAttributes(level, true, 1).size() > 0 || getInvalidElements(level, true, 1).size() > 0)
			{
				return false;
			}

			if (EnumValidationLevel.isRecursive(level))
			{
				final EnumValidationLevel valDown = (level == EnumValidationLevel.RecursiveIncomplete) ? EnumValidationLevel.Incomplete : EnumValidationLevel.Complete;

				final VElement v = getChildElementVector(null, null, null, true, 0, false);
				final int size = v.size();
				for (int i = 0; i < size; i++)
				{
					if (v.elementAt(i) instanceof JDFRefElement)
					{
						final JDFResource res = ((JDFRefElement) v.elementAt(i)).getTarget();
						if (!res.isValid(valDown))
						{
							return false;
						}
					}
				}
			}
			return true;
		}
		catch (final JDFException e)
		{
			// snafu --> it probably isn't valid...
			return false;
		}
	}

	/**
	 * Mother of all version fixing routines
	 * 
	 * uses heuristics to modify this element and its children to be compatible with a given version in general, it will be able to move from low to high
	 * versions but potentially fail when attempting to move from higher to lower versions
	 * 
	 * @param version version the resulting element should correspond to
	 * @return true if successful
	 */
	public final boolean fixVersion(final EnumVersion version)
	{
		final FixVersion fixVersion = new FixVersion(version);
		fixVersion.walkTree(this, null);
		return fixVersion.isOK();
	}

	/**
	 * Check Existance of attribute SettingsPolicy
	 * 
	 * @param bInherit recurse through ancestors when searching
	 * @return true if attribute exists
	 * @deprecated use hasAttribute
	 */
	@Deprecated
	public boolean hasSettingsPolicy(final boolean bInherit)
	{
		return hasAttribute(AttributeName.SETTINGSPOLICY, null, bInherit);
	}

	/**
	 * Remove attribute SettingsPolicy
	 * 
	 * @deprecated use removeAttribute
	 */
	@Deprecated
	public void removeSettingsPolicy()
	{
		removeAttribute(AttributeName.SETTINGSPOLICY, null);
	}

	/**
	 * Remove attribute BestEffortExceptions
	 * 
	 * @deprecated use removeAttribute
	 */
	@Deprecated
	public void removeBestEffortExceptions()
	{
		removeAttribute(AttributeName.BESTEFFORTEXCEPTIONS, null);
	}

	/**
	 * Gets the one and reference to an ID
	 * 
	 * @return String the rRef attribute
	 * @deprecated use getrRef of the coresponding sub classes instead
	 */
	@Deprecated
	public String getHRef()
	{
		return getAttribute(JDFConstants.RREF, null, JDFConstants.EMPTYSTRING);
	}

	/**
	 * append a reference to a target node automatically generate a unique ID attribute for target, if it does not exist
	 * 
	 * @param target the element to reference
	 * @param refAttribute name of the refering attribute, e.g. hRef, rRef
	 * @param preSet preset value of the ID attribute - defaults to autogenerated
	 * 
	 * @return JDFElement
	 * 
	 * @default AppendHRef(target, null, JDFConstants.EMPTYSTRING)
	 */
	public JDFElement appendHRef(final JDFResource target, final String refAttribute, final String preSet)
	{
		if (target == null)
		{
			return null;
		}

		String idRef = target.getID();

		if (idRef == null || idRef.equals(JDFConstants.EMPTYSTRING))
		{
			target.appendAnchor(preSet);
			idRef = target.getID();
		}

		return appendHRef(idRef, refAttribute, null);
	}

	/**
	 * append a reference with a specific id
	 * 
	 * @param idRef value of the ID-IDREF pair
	 * @param refAttribute value of the IDREF tag
	 * @param nameSpaceURI nameSpaceURI of the IDREF tag
	 * 
	 * @return JDFElement
	 * 
	 * @default AppendHRef(idRef, JDFConstants.EMPTYSTRING, null)
	 */
	public JDFElement appendHRef(final String idRef, final String refAttribute, final String nameSpaceURI)
	{
		String refAttributeLocal = refAttribute;

		if (idRef == null || idRef.length() < 1)
		{
			return null;
		}

		if (refAttributeLocal == null || refAttributeLocal.equals(JDFConstants.EMPTYSTRING))
		{
			refAttributeLocal = JDFConstants.RREF;
		}

		setAttribute(refAttributeLocal, idRef, nameSpaceURI);

		return this;
	}

	/**
	 * Sets an element attribute
	 * 
	 * @param key the name of the attribute to set
	 * @param value the JDFDuration to set
	 * @param nameSpaceURI the nameSpace the attribute is in
	 * 
	 * default: setAttribute(key, value, null)
	 */
	public void setAttribute(final String key, final JDFDuration value, final String nameSpaceURI)
	{
		if (value == null)
		{
			removeAttribute(key, nameSpaceURI);
		}
		else
		{
			super.setAttribute(key, value.getDurationISO(), nameSpaceURI);
		}
	}

	/**
	 * Sets an element attribute
	 * 
	 * @param key the name of the attribute to set
	 * @param value the JDFNumList to set
	 * @param nameSpaceURI the nameSpace the attribute is in
	 * 
	 * @default setAttribute(key, value, null)
	 */
	public void setAttribute(final String key, final JDFNumList value, final String nameSpaceURI)
	{
		if (value == null)
		{
			removeAttribute(key, nameSpaceURI);
		}
		else
		{
			super.setAttribute(key, value.toString(), nameSpaceURI);
		}
	}

	/**
	* Sets an element attribute
	* 
	* @param key the name of the attribute to set
	* @param value the JDFNumList to set
	* @param nameSpaceURI the nameSpace the attribute is in
	* @param precision number of digits
	* 
	* @default setAttribute(key, value, null)
	*/
	public void setAttribute(final String key, final JDFNumList value, final String nameSpaceURI, int precision)
	{
		if (value == null)
		{
			removeAttribute(key, nameSpaceURI);
		}
		else
		{
			super.setAttribute(key, value.getString(precision), nameSpaceURI);
		}
	}

	/**
	 * Sets an element attribute
	 * 
	 * @param key the name of the attribute to set
	 * @param value the JDFNumberRangeList to set
	 * @param nameSpaceURI the nameSpace the attribute is in
	 * 
	 * @default setAttribute(key, value, null)
	 */
	public void setAttribute(final String key, final JDFRangeList value, final String nameSpaceURI)
	{
		if (value == null)
		{
			removeAttribute(key, nameSpaceURI);
		}
		else
		{
			super.setAttribute(key, value.toString(), nameSpaceURI);
		}
	}

	/**
	 * Sets an element attribute
	 * 
	 * @param key the name of the attribute to set
	 * @param value the JDFNumberRangeList to set
	 * @param nameSpaceURI the nameSpace the attribute is in
	 * @param precision 
	 * 
	 * @default setAttribute(key, value, null)
	 */
	public void setAttribute(final String key, final JDFRangeList value, final String nameSpaceURI, int precision)
	{
		if (value == null)
		{
			removeAttribute(key, nameSpaceURI);
		}
		else
		{
			super.setAttribute(key, value.getString(precision), nameSpaceURI);
		}
	}

	/**
	 * Sets an element attribute
	 * 
	 * @param key the name of the attribute to set
	 * @param value the JDFXYRange to set
	 * @param nameSpaceURI the nameSpace the attribute is in
	 * 
	 * @default setAttribute(key, value, null)
	 */
	public void setAttribute(final String key, final JDFRange value, final String nameSpaceURI)
	{
		if (value == null)
		{
			removeAttribute(key, nameSpaceURI);
		}
		else
		{
			super.setAttribute(key, value.toString(), nameSpaceURI);
		}
	}

	/**
	 * Sets an element attribute
	 * 
	 * @param key the name of the attribute to set
	 * @param value the JDFXYRange to set
	 * @param nameSpaceURI the nameSpace the attribute is in
	 * @param precision 
	 * 
	 * @default setAttribute(key, value, null)
	 */
	public void setAttribute(final String key, final JDFRange value, final String nameSpaceURI, int precision)
	{
		if (value == null)
		{
			removeAttribute(key, nameSpaceURI);
		}
		else
		{
			super.setAttribute(key, value.getString(precision), nameSpaceURI);
		}
	}

	/**
	 * Sets an element attribute
	 * 
	 * @param key the name of the attribute to set
	 * @param value the JDFNameRange to set
	 * @param nameSpaceURI the nameSpace the attribute is in
	 * @deprecated
	 * @default setvStringAttribute(key, value, null)
	 */
	@Deprecated
	public void setvStringAttribute(final String key, final JDFNameRange value, final String nameSpaceURI)
	{
		setAttribute(key, value.toString(), nameSpaceURI);
	}

	/**
	 * return true if no more than one of att1 or att2 exists or exactly one of att1 or att2 exists depending on level
	 * 
	 * @param level validation level, if level allows optional, no attribute also returns true
	 * @param att1 name of first attribute
	 * @param att2 name of second attribute
	 * @param att3 name of third optional attribute
	 * @param att4 name of fourth optional attribute
	 * @return boolean true combination is valid
	 * @default exclusiveOneOfAttribute(EnumValidationLevel level, String att1, String att2, JDFConstants.EMPTYSTRING, JDFConstants.EMPTYSTRING)
	 */
	public boolean exclusiveOneOfAttribute(final EnumValidationLevel level, final String att1, final String att2, final String att3, final String att4)
	{
		int n = 0;
		final boolean hasAtt1 = hasAttribute(att1);
		n += hasAtt1 ? 1 : 0;
		n += hasAttribute(att2) ? 1 : 0;
		if (!att3.equals(JDFConstants.EMPTYSTRING))
		{
			n += hasAttribute(att3) ? 1 : 0;
		}
		if (!att4.equals(JDFConstants.EMPTYSTRING))
		{
			n += hasAttribute(att4) ? 1 : 0;
		}

		if (EnumValidationLevel.isRequired(level))
		{
			// exactly one or more than one, but not this one
			return n == 1 || ((!hasAtt1) && (n >= 1));
		}
		// at most one is good or more than one, but not this one
		return n <= 1 || !hasAtt1;
	}

	/**
	 * return true if no more than one of att1 or att2 exists or exactly one of att1 or att2 exists depending on level
	 * 
	 * @param level validation level, if level allows optional, no attribute also returns true
	 * @param elm1 name of first attribute
	 * @param ns1 namespace URI of first attribute
	 * @param elm2 name of first attribute
	 * @param ns2 namespace URI of first attribute
	 * @return boolean true combination is valid
	 */
	public boolean exclusiveOneOfElement(final EnumValidationLevel level, final String elm1, final String ns1, final String elm2, final String ns2)
	{
		int n = 0;
		n += hasChildElement(elm1, ns1) ? 1 : 0;
		n += hasChildElement(elm2, ns2) ? 1 : 0;
		if (EnumValidationLevel.isRequired(level))
		{
			// exactly one
			return n == 1;
		}
		// at most one is good
		return n <= 1;
	}

	/**
	 * GetChildIds - get a set of all known id's in child nodes
	 * 
	 * @param attrib attribute name of the ID attribute, defaults to "ID"
	 * @param element name of the elements to be searched
	 * @param nameSpaceURI attribute namespace uri of the elements to include in the list
	 * 
	 * @return Vector - a vector of all known ID elements
	 * 
	 * @default GetChildIds(AttributeName.ID, null, null)
	 */
	public VString getChildIds(final String attrib, final String element, final String nameSpaceURI)
	{
		final VString setID = new VString();

		final VElement nl = getElementsByTagName_KElement(element, nameSpaceURI);
		final int l = nl.size();

		if (l != 0)
		{
			nl.elementAt(l - 1);
		}

		for (int i = 0; i < l; i++)
		{
			final KElement kElem = nl.elementAt(i);
			final String s = kElem.getAttribute(attrib, nameSpaceURI, JDFConstants.EMPTYSTRING);

			if (s.equals(JDFConstants.EMPTYSTRING))
			{
				continue;
			}
			setID.addElement(s);
		}

		return setID;
	}

	/**
	 * Get the target element of a link (string id)
	 * 
	 * @return JDFElement - the element that this reference refers to
	 * @deprecated use the respective subclasses getTarget functions
	 */
	@Deprecated
	public JDFResource getTarget()
	{
		return (JDFResource) getTarget_JDFElement(getAttribute(AttributeName.RREF), AttributeName.ID);
	}

	/**
	 * looking for a specified target with an id, e.g. resource.<br>
	 * Offers access to exactly KElements implementation of GetTarget even if called for an instance of one of it's subclasses.
	 * <p>
	 * default: getTarget(id, JDFCoreConstants.ID)
	 * @param id value of the ID tag to search
	 * @param attrib name of the ID tag, defaults to "ID"
	 * @return KElement - the element if existing, otherwise <code>null</code>
	 */
	@Override
	public KElement getTarget(final String id, final String attrib)
	{
		return getTarget_JDFElement(id, attrib);
	}

	/**
	 * Get the JDF root
	 * 
	 * @return JDFNode - The root of the JDF File
	 */
	public JDFNode getJDFRoot()
	{
		return (JDFNode) getDeepParent(ElementName.JDF, Integer.MAX_VALUE);
	}

	/**
	 * Get the JMF root
	 * 
	 * @return JDFJMF - the root of the JMF file
	 */
	public JDFJMF getJMFRoot()
	{
		return (JDFJMF) getDeepParent(ElementName.JMF, Integer.MAX_VALUE);
	}

	/**
	 * IsRefElement - is this thing a RefElement?
	 * 
	 * @return true, if this is a refElement
	 * @deprecated use instanceof JDFRefElement
	 */
	@Deprecated
	public boolean isRefElement()
	{
		return this instanceof JDFRefElement;
	}

	/**
	 * IsRefElement - is this thing a RefElement?
	 * @param kElem
	 * 
	 * @return true, if this is a refElement
	 * @deprecated use instanceof JDFRefElement
	 */
	@Deprecated
	public static boolean isRefElementStatic(final KElement kElem)
	{
		return kElem instanceof JDFRefElement;
	}

	/**
	 * tests whether this Element is a Resource.
	 * 
	 * @return boolean - true, if it is a Resource.
	 * @deprecated use instanceof JDFResource instead
	 */
	@Deprecated
	public boolean isResource()
	{
		return this instanceof JDFResource;
	}

	/**
	 * tests whether this Element is a Resource.
	 * @param e
	 * 
	 * @return boolean - true, if it is a Resource.
	 * @deprecated use instanceof JDFResource instead
	 */
	@Deprecated
	static public boolean isResourceStatic(final KElement e)
	{
		return e instanceof JDFResource;
	}

	/**
	 * is this thing a ResourceUpdate?
	 * 
	 * @return true, if this is a ResourceUpdate
	 */
	public boolean isResourceUpdate()
	{
		return this.getLocalName().endsWith(ElementName.UPDATE);
	}

	/**
	 * Method IsResourceLink.
	 * 
	 * @return boolean
	 * @deprecated use instanceof JDFResourceLink instead
	 */
	@Deprecated
	public boolean isResourceLink()
	{
		return this instanceof JDFResourceLink;
	}

	/**
	 * Method IsResourceLinkStatic.
	 * 
	 * @param kElem
	 * @return boolean
	 * @deprecated use instanceof JDFResourceLink instead
	 * 
	 */
	@Deprecated
	public static boolean isResourceLinkStatic(final KElement kElem)
	{
		return kElem instanceof JDFResourceLink;
	}

	/**
	 * Check, if this is an Comment element
	 * 
	 * @return boolean - true, if this is an Comment-Element, otherwise false
	 * @deprecated use instanceof JDFResourceLink instead
	 */
	@Deprecated
	public boolean isComment()
	{
		return this instanceof JDFComment;
	}

	/**
	 * Method isCommentStatic.
	 * 
	 * @param kElem
	 * @return boolean - true, if this is an Comment-Element, otherwise false
	 * @deprecated use instanceof JDFComment instead
	 */
	@Deprecated
	public static boolean isCommentStatic(final KElement kElem)
	{
		return kElem instanceof JDFComment;
	}

	/**
	 * is this thing a JDF Node?
	 * 
	 * @return true if this is a JDF Node
	 * @deprecated use instanceof JDFNode instead
	 */
	@Deprecated
	public boolean isJDFNode()
	{
		return this instanceof JDFNode;
	}

	/**
	 * Method IsInJDFNameSpace.
	 * 
	 * @return boolean
	 * @deprecated - use isInJDFNameSpaceStatic(kElem)
	 */
	@Deprecated
	public boolean isInJDFNameSpace()
	{
		return isInJDFNameSpaceStatic(this);
	}

	/**
	 *remove all private extensions form a jdf element and its children
	 * 
	 */
	public void removeExtensions()
	{
		Node n = getFirstChild();
		while (n != null)
		{
			final Node next = n.getNextSibling(); // get next prior to zapping
			final String nsuri = n.getNamespaceURI();
			if (!isInJDFNameSpaceStatic(nsuri))
			{
				removeChild(n);
			}
			else if (n instanceof JDFElement)
			{
				((JDFElement) n).removeExtensions();
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
				if (!isInJDFNameSpaceStatic(nsuri))
				{
					removeAttributeNode((Attr) na);
				}
			}
		}
	}

	/**
	 * checks whether kElem is in the JDF namespace
	 * 
	 * @param ns the KElement to check
	 * @return boolean - true, if kElem is in the JDF namespace
	 */
	public static boolean isInJDFNameSpaceStatic(final String ns)
	{
		return ns == null || ns.equals(JDFConstants.EMPTYSTRING) || (ns.compareToIgnoreCase("http://www.CIP4.org/JDFSchema_1_1") == 0)
				|| (ns.compareToIgnoreCase("http://www.CIP4.org/JDFSchema_1") == 0);
	}

	/**
	 * checks whether kElem is in the JDF namespace
	 * 
	 * @param kElem the KElement to check
	 * @return boolean - true, if kElem is in the JDF namespace
	 */
	public static boolean isInJDFNameSpaceStatic(final KElement kElem)
	{
		if (kElem == null)
		{
			return false; // null ain't no jdf
		}

		final String ns = kElem.getNamespaceURI();
		return isInJDFNameSpaceStatic(ns);
	}

	/**
	 * gets an inter resource link to a target resource. if target is a partition, the refElement MUST point exactly to that partition
	 * 
	 * @param target - Target resource to link to
	 * @return the existing refElement
	 */
	public JDFRefElement getRefElement(final JDFResource target)
	{
		if (target == null)
		{
			return null;
		}

		JDFAttributeMap map = target.getPartMap();
		if (map != null && map.size() == 0)
		{
			map = null;
		}
		final String id = target.getID();
		if (isWildCard(id))
		{
			return null;
		}

		final VElement v = getChildrenByTagName(target.getLocalName() + JDFConstants.REF, target.getNamespaceURI(), new JDFAttributeMap(AttributeName.RREF, id), false, true, 0);
		if (v != null)
		{
			final int siz = v.size();
			for (int i = 0; i < siz; i++)
			{
				final JDFRefElement re = (JDFRefElement) v.elementAt(i);
				JDFAttributeMap partMap = re.getPartMap();
				if (partMap != null && partMap.size() == 0)
				{
					partMap = null;
				}

				if (ContainerUtil.equals(partMap, map))
				{
					return re;
				}
			}
		}

		return null;
	}

	/**
	 * gets an inter resource link to a target resource., creates it if it does not exist
	 * 
	 * @param target - Target resource to link to
	 * @return
	 */
	public JDFRefElement getCreateRefElement(final JDFResource target)
	{
		if (target == null)
		{
			return null;
		}
		target.makeRootResource(null, null, true);
		final JDFRefElement re = getRefElement(target);
		return re == null ? refElement(target) : re;
	}

	/**
	 * Creates an inter resource link to a target resource.<br/>
	 * note that if target is a subelement rather than a root resource, it WILL be promoted to root level (rSubRef is deprecated)
	 * 
	 * @param target - Target resource to link to
	 * @return
	 */
	public JDFRefElement refElement(JDFResource target)
	{
		final JDFRefElement newRef = (JDFRefElement) appendElement(target.getNodeName() + JDFConstants.REF, target.getNamespaceURI());

		JDFResource root = target.getResourceRoot();

		// check whether it is a resource element
		if (target.isResourceElement())
		{
			target = target.makeRootResource(null, null, true);
		}

		// check whether it is a resource leaf or root
		if (!target.isResourceRoot())
		{
			final JDFAttributeMap partMap = target.getPartMap();
			newRef.setPartMap(partMap);
		}
		// ID is appended to the resource root of target
		newRef.appendHRef(root, AttributeName.RREF, null);

		// move the resource to the closest common ancestor if it is not already an ancestor of this
		JDFNode parent = root.getParentJDF();

		// move the resource to the closest common ancestor if it is not already an ancestor of this
		while (parent != null && !parent.isAncestor(this))
		{
			parent = root.getParentJDF();
			if (parent == null)
			{
				break;
			}
			parent = parent.getParentJDF();
			if (parent == null)
			{
				newRef.deleteNode(); // cleanup
				throw new JDFException("appendRefElement resource is not in the same document");
			}

			root = (JDFResource) parent.getCreateResourcePool().moveElement(root, null);
		}

		return newRef;
	}

	/**
	 * Removes an inter resource link from this Element.
	 * 
	 * @param target Target to remove
	 */
	public void removeRefElement(final JDFResource target)
	{
		final String id = target.getID();

		if (id.equals(JDFConstants.EMPTYSTRING))
		{
			throw new JDFException("RemoveRefElement: target has no id");
		}

		final VElement v = getChildElementVector(target.getRefString(), null, null, true, 0, false);
		for (int i = 0; i < v.size(); i++)
		{
			final JDFRefElement re = (JDFRefElement) v.elementAt(i);

			if (re.getrRef().equals(id) || re.getrSubRef().equals(id))
			{
				re.deleteNode();
				break;
			}
		}
	}

	/**
	 * gets the vector of all RefElements
	 * 
	 * @return VElement - vector of JDFRefElements
	 */
	public VElement getRefElements()
	{
		final VElement v = getChildElementVector(null, null, null, true, 0, false);
		// loop backwords so that I don't invalidate the vector by deleting
		for (int i = v.size() - 1; i >= 0; i--)
		{
			final KElement e = v.item(i);
			// cant be null
			if (!(e instanceof JDFRefElement))
			{
				v.remove(i);
			}
		}
		return v;
	}

	/**
	 * UpDates rRefs attribute of this Element, corresponding to the child reference Elements of this Element.
	 * @return
	 * 
	 * @deprecated use KElement.fillHashSet(ElementName.RREF,null,hashSet)
	 */
	@Deprecated
	public VString upDaterRefs()
	{
		final VString vrRefs = new VString();
		final VElement v = getChildrenByTagName(null, null, new JDFAttributeMap(JDFConstants.RREF, ""), false, true, 0);
		// grabemall

		final int size = v.size();
		for (int i = 0; i < size; i++)
		{
			final KElement el_i = (v.elementAt(i));
			vrRefs.add(el_i.getAttribute(JDFConstants.RREF, null, JDFConstants.EMPTYSTRING));
		}
		vrRefs.unify();
		return vrRefs;
	}

	/**
	 * inline refelements that match nodename and nameSpaceURI
	 * 
	 * @param nodeName name of the refelement (without the "Ref")
	 * @param nameSpaceURI
	 * @param bDirect if true, get direct children only, else recurse into all sub-elements
	 */
	public void inlineRefElements(final String nodeName, final String nameSpaceURI, final boolean bDirect)
	{
		VElement v = getRefElements();
		int i;
		int vSize = v.size();
		for (i = 0; i < vSize; i++)
		{
			final JDFRefElement re = (JDFRefElement) v.elementAt(i);
			// it fits - inline it
			if (re.fitsName(nodeName, nameSpaceURI))
			{
				re.inlineRef();
			}
		}

		// now loop over all (!) children, to see if any descendants match
		if (!bDirect)
		{
			v = this.getChildElementVector_KElement(null, null, null, true, 0);
			vSize = v.size();
			for (i = 0; i < vSize; i++)
			{
				if (v.elementAt(i) instanceof JDFElement)
				{
					final JDFElement e = (JDFElement) v.elementAt(i);
					e.inlineRefElements(nodeName, nameSpaceURI, bDirect);
				}
			}
		}
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
	 * @param bResolveTarget if true, returns the targets of the refElements<br>
	 * else the refElements are returned (if mAttrib != null), additionally the attributes of the target are checked)
	 * 
	 * @return VElement - vector with all elements found
	 * 
	 * @see org.cip4.jdflib.core.KElement#getChildElementVector(java.lang.String, java.lang.String, org.cip4.jdflib.datatypes.JDFAttributeMap, boolean, int,
	 * boolean) default: getChildElementVector(null, null,null, true, 0, false)
	 */
	@Override
	public VElement getChildElementVector(final String nodeName, final String nameSpaceURI, final JDFAttributeMap mAttrib, final boolean bAnd, final int maxSize, final boolean bResolveTarget)
	{
		return getChildElementVector_JDFElement(nodeName, nameSpaceURI, mAttrib, bAnd, maxSize, bResolveTarget);
	}

	/**
	 * Gets children of 'this' by tag name, nameSpaceURI and attribute map, if the attribute map is not empty.<br>
	 * Searches the entire tree including hidden nodes that are children of non-matching nodes
	 * 
	 * @param elementName elementname you are searching for
	 * @param nameSpaceURI nameSpace you are searching for
	 * @param mAttrib map of attributes you are looking for <br>
	 * Wildcards in the attribute map are supported
	 * @param bDirect if true, return value is a vector only of all direct child elements. <br>
	 * Otherwise the returned vector contains nodes of arbitrary depth
	 * @param bAnd if true, a child is only added, if it includes all attributes, specified in mAttrib
	 * @param maxSize maximum size of the element vector. maxSize is ignored if bDirect is false
	 * 
	 * @return VElement: vector with all found elements
	 * 
	 * @see org.cip4.jdflib.core.KElement#getChildElementVector(java.lang.String, java.lang.String, org.cip4.jdflib.datatypes.JDFAttributeMap, boolean, int,
	 * boolean)
	 * 
	 * @default getChildrenByTagName(s,null,null, false, true, 0)
	 */
	@Override
	public VElement getChildrenByTagName(final String elementName, final String nameSpaceURI, final JDFAttributeMap mAttrib, final boolean bDirect, final boolean bAnd, final int maxSize)
	{

		return getChildrenByTagName(elementName, nameSpaceURI, mAttrib, bDirect, bAnd, maxSize, !isWildCard(elementName) && !elementName.endsWith("Ref"));

	}

	/**
	 * Gets children of 'this' by tag name, nameSpaceURI and attribute map, if the attribute map is not empty.<br>
	 * Searches the entire tree including hidden nodes that are children of non-matching nodes
	 * 
	 * @param elementName elementname you are searching for
	 * @param nameSpaceURI nameSpace you are searching for
	 * @param mAttrib map of attributes you are looking for <br>
	 * Wildcards in the attribute map are supported
	 * @param bDirect if true, return value is a vector only of all direct child elements. <br>
	 * Otherwise the returned vector contains nodes of arbitrary depth
	 * @param bAnd if true, a child is only added, if it includes all attributes, specified in mAttrib
	 * @param maxSize maximum size of the element vector. maxSize is ignored if bDirect is false
	 * @param bFollowRefs if true follow references of refElements, else return the refElement
	 * 
	 * @return VElement: vector with all found elements
	 * 
	 * @see org.cip4.jdflib.core.KElement#getChildElementVector(java.lang.String, java.lang.String, org.cip4.jdflib.datatypes.JDFAttributeMap, boolean, int,
	 * boolean)
	 * 
	 * @default getChildrenByTagName(s,null,null, false, true, 0)
	 */
	public VElement getChildrenByTagName(final String elementName, final String nameSpaceURI, final JDFAttributeMap mAttrib, final boolean bDirect, final boolean bAnd, final int maxSize, final boolean bFollowRefs)
	{
		final VElement v = super.getChildrenByTagName_KElement(bFollowRefs ? null : elementName, nameSpaceURI, mAttrib, bDirect, bAnd, bFollowRefs ? -1 : maxSize);

		if (bFollowRefs == false)
		{
			return v; // do not folow refs if explicit refs are requested
		}

		if (v != null)
		{
			final int size = v.size();
			for (int i = size - 1; i >= 0; i--)
			{
				KElement refElem = v.elementAt(i);
				if (!refElem.fitsName(elementName, nameSpaceURI))
				{
					v.remove(i);
				}
				else if (refElem instanceof JDFRefElement)
				{
					KElement target = ((JDFRefElement) refElem).getTarget();
					if (target == null)
					{
						jLog.warn("Ignoring missing refelemnt target: " + refElem.getNodeName() + " rRef=" + refElem.getAttribute(AttributeName.RREF));
						v.remove(i);
					}
					else
					{
						if (!(target instanceof JDFResource))
						{
							jLog.warn("target is not a resource: " + refElem.getNodeName() + " rRef=" + refElem.getAttribute(AttributeName.RREF));
						}
						v.set(i, target);
					}
				}
			}
			if (maxSize > 0)
			{
				for (int i = v.size() - 1; i >= maxSize; i--)
				{
					v.remove(i);
				}
			}
		}
		return v;
	}

	/**
	 * @see org.cip4.jdflib.core.KElement#getChildElementVector(java.lang.String, java.lang.String, org.cip4.jdflib.datatypes.JDFAttributeMap, boolean, int,
	 * boolean)
	 * 
	 * @param nodeName
	 * @param nameSpaceURI
	 * @param mAttrib
	 * @param bAnd
	 * @param maxSize
	 * @param bResolveTarget - additional control how refelements are followed
	 * @return
	 */
	public VElement getChildElementVector_JDFElement(String nodeName, String nameSpaceURI, JDFAttributeMap mAttrib, final boolean bAnd, int maxSize, final boolean bResolveTarget)
	{

		final VElement v = new VElement();
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
		if (maxSize == 0)
		{
			maxSize = -1;
		}

		final boolean bAlwaysFit = nodeName == null && nameSpaceURI == null;
		final boolean bMapEmpty = mAttrib == null;

		int iSize = 0;
		KElement kElem = getFirstChildElement();

		while (kElem != null)
		{
			if (bAlwaysFit || kElem.fitsName(nodeName, nameSpaceURI))
			{
				if (bResolveTarget && (kElem instanceof JDFRefElement))
				{
					try
					{
						final JDFRefElement ref = (JDFRefElement) kElem;
						final KElement target = ref.getTarget();

						// in case there is no element for the REF, target will
						// be null and will be skipped
						if (target != null)
						{ // we want the jdfElem version of IncludesAttributes,
							// so we must upcast
							if (bMapEmpty || target.includesAttributes(mAttrib, bAnd))
							{
								v.addElement(target);
								iSize++;
							}
						}
					}
					catch (final JDFException ex)
					{
						// simply skip invalid refelements
					}
				}
				else if (bMapEmpty || kElem.includesAttributes(mAttrib, bAnd))
				{
					v.addElement(kElem);
					iSize++;
				}
				if (iSize == maxSize)
				{
					break;
				}
			}
			kElem = kElem.getNextSiblingElement();
		}
		return v;
	}

	/**
	 * @see org.cip4.jdflib.core.KElement#getElementNameVector()
	 */
	@Override
	public VString getElementNameVector()
	{
		final VElement e = getChildElementVector(null, null, null, true, 0, false);

		final VString v = new VString();
		for (int i = 0; i < e.size(); i++)
		{
			final KElement el = e.elementAt(i);
			String s = el.getNodeName();
			if (el instanceof JDFRefElement)
			{
				s = s.substring(0, s.length() - AttributeName.REF.length());
			}
			v.appendUnique(s);
		}
		return v;
	}

	/**
	 * @see org.cip4.jdflib.core.KElement#getElement(java.lang.String, java.lang.String, int)
	 * @default getElement(nodeName, null, 0)
	 */
	@Override
	public KElement getElement(final String nodeName, final String nameSpaceURI, final int iSkip)
	{
		return getElement_JDFElement(nodeName, nameSpaceURI, iSkip);
	}

	/**
	 * same as KElement.GetElement, but follows references as well.<br>
	 * Thus the returned value is the target of the refElement unless the requested element is explicitely a refElement, which is specified by requesting an
	 * element with nodeName="XXXRef".<br>
	 * Invalid refelements are simply skipped and are not filled into the vector
	 * 
	 * @param nodeName name of the child node to get
	 * @param nameSpaceURI namespace to search for
	 * @param iSkip get the iSkipth element that fits
	 * 
	 * @return KElement the matching element
	 * 
	 * @default getElement_JDFElement(nodeName, null, 0)
	 */
	public KElement getElement_JDFElement(final String nodeName, final String nameSpaceURI, int iSkip)
	{
		// loop over the list
		int i = 0;
		if (iSkip < 0)
		{
			iSkip = numChildElements_JDFElement(nodeName, nameSpaceURI) + iSkip;
		}
		if (iSkip < 0)
		{
			return null;
		}
		KElement jdfElem = getFirstChildElement();
		final boolean bExplicitRefElement = (nodeName != null) && (nodeName.endsWith(JDFConstants.REF));

		while (jdfElem != null)
		{
			if (jdfElem.fitsName(nodeName, nameSpaceURI))
			{
				// this guy is the one
				if (i++ == iSkip)
				{
					// follow valid (!) refElements, invalid refelements are
					// ignored
					// 300502 RP added check for explicit refelements
					if (jdfElem instanceof JDFRefElement && !bExplicitRefElement)
					{
						try
						{
							final JDFRefElement re = (JDFRefElement) jdfElem;
							final KElement target = re.getTarget();
							if (target != null)
							{
								return target;
							}
						}
						catch (final JDFException ex)
						{
							// simply ignore invalid refelements
						}
						i--; // this one was screwed up -> don't count it
					}
					else
					{ // not a refElement or explicitly want the refElement
						return jdfElem;
					}
				}
			}

			jdfElem = jdfElem.getNextSiblingElement();
		}

		// found no match
		return null;
	}

	/**
	 * same as {@link KElement#numChildElements(String, String)}, but also follows references.<br>
	 * Invalid refelements are simply skipped.
	 * 
	 * @param nodeName the nodes to count
	 * @param nameSpaceURI the nameSpace to look in
	 * @return int - the number of child elements
	 * 
	 * @default numChildElements(String nodeName, null)
	 */
	@Override
	public int numChildElements(final String nodeName, final String nameSpaceURI)
	{
		return getChildElementVector(nodeName, nameSpaceURI, null, true, 0, false).size();
	}

	/**
	 * @param nodeName
	 * @param nameSpaceURI
	 * @return
	 */
	public int numChildElements_JDFElement(final String nodeName, final String nameSpaceURI)
	{
		return getChildElementVector_JDFElement(nodeName, nameSpaceURI, null, true, 0, false).size();
	}

	/**
	 * Remove children that match nodeName and nameSpaceURI
	 * 
	 * @param nodeName name of the child node to get, if empty or null remove all
	 * @param nameSpaceURI namespace to search for
	 * @param mAttrib attribute map to search for
	 */
	@Override
	public void removeChildren(final String nodeName, final String nameSpaceURI, final JDFAttributeMap mAttrib)
	{
		// the loop allows jdf resources to recursively remove all children
		while (true)
		{
			final VElement v = getChildElementVector_JDFElement(nodeName, nameSpaceURI, mAttrib, true, 0, false);
			if (v.size() > 0)
			{
				for (int i = 0; i < v.size(); i++)
				{
					// may NOT call removeChild since JDFResource calls to this
					// routine may actually change the parent
					final KElement kelem = v.elementAt(i);
					kelem.deleteNode();
				}
			}
			else
			{
				return;
			}
		}
	}

	/**
	 * is the ressource r linkable by this? used by ResorceLink and refElement
	 * 
	 * @param r the resource to link to
	 * @return boolean - true if r is at a valid position
	 */
	protected boolean validResourcePosition(final JDFResource r)
	{
		if (r == null)
		{
			return false;
		}
		final JDFNode nodeResource = r.getParentJDF();
		final JDFNode nodeLink = getParentJDF();
		if ((nodeResource != null) && (nodeLink != null))
		{
			if (nodeResource.equals(nodeLink))
			{
				return true;
			}
			if (nodeResource.isAncestor(nodeLink))
			{
				return true;
			}
		}
		else if (getDeepParent(ElementName.JMF, 0) != null)
		{
			// they are in the same signal, command etc..
			return getDeepParentChild(ElementName.JMF) == r.getDeepParentChild(ElementName.JMF);
		}

		return false;
	}

	/**
	 * remove child node
	 * 
	 * @param node name of the child node to remove, if empty or "*" remove all
	 * @param nameSpaceURI namespace to search for
	 * @param n number of nodes to skip before deleting
	 * @return KElement - the removed node
	 */
	@Override
	public KElement removeChild(final String node, final String nameSpaceURI, final int n)
	{
		// use KElement because I do not want to remove the target
		final VElement v = getChildElementVector_JDFElement(node, nameSpaceURI, null, true, 0, false);

		if (n >= v.size())
		{
			return null;
		}
		// may NOT call removeChild since JDFResource calls to this routine may
		// actually change the parent
		final KElement kelem = v.elementAt(n);
		return kelem.deleteNode();
	}

	/**
	 * create and append a unique id, keep the existing one if it already exists
	 * @param strName
	 * 
	 * @return String - the value of the ID attribute after setting
	 * 
	 * @default appendAnchor(null)
	 */
	@Override
	public String appendAnchor(String strName)
	{
		if (hasAttribute(AttributeName.ID))
		{
			return this.getAttribute(AttributeName.ID, null, null);
		}
		else if ((strName == null) || strName.equals(JDFConstants.EMPTYSTRING))
		{
			String local = "";
			final JDFNode n = getJDFRoot();
			if (n != null)
			{
				local = n.getSpawnID(true);

				if (!isWildCard(local))
				{
					local = "." + StringUtil.rightStr(local, 6) + ".";
				}
			}
			final JDFJMF m = getJMFRoot();
			if (m != null)
			{
				local = m.getSenderID();
				if (!isWildCard(local))
				{
					local = StringUtil.replaceCharSet(local, " \t\n\f", null, 0);
					local = "." + Math.abs(local.hashCode()) + ".";
				}
			}

			strName = getIDPrefix() + local + uniqueID(0);
		}
		setAttribute(AttributeName.ID, strName, null);

		return strName;
	}

	/**
	 * put a timestamp in an attribute of the current node
	 * 
	 * @param attributeName the attribute name to timestamp
	 * @param timestamp the timestamp
	 */
	public void setAttributeNameTimeStamp(final String attributeName, final JDFDate timestamp)
	{
		JDFDate timestampLocal = timestamp;

		if (timestampLocal == null)
		{
			timestampLocal = new JDFDate();
		}
		setAttribute(attributeName, timestampLocal.getDateTimeISO(), null);
	}

	/**
	 * Sets the attributes from the curent element to the attributes from kElem. If the Attributes map from kElem is empty (kElem has no attributes), zero is
	 * returned. Otherwhise the size of the map (number of attributes from kElem) is returned.
	 * @param kElem the attribute source
	 * @param ignoreList 
	 * @return int number of elements from kElem
	 */
	@Override
	public int setAttributes(final KElement kElem, final VString ignoreList)
	{
		if (kElem == null)
		{
			return 0;
		}

		KElement parent = null;
		if (kElem instanceof JDFResource)
		{
			parent = kElem.getParentNode_KElement();
			if (parent != null && kElem.getNodeName().equals(parent.getNodeName()))
			{
				final JDFResource r = (JDFResource) parent;
				VString il2 = ignoreList;
				if (il2 == null)
				{
					il2 = new VString();
					il2.add(AttributeName.ID);
					il2.add(AttributeName.PARTUSAGE);
					il2.add(AttributeName.PARTIDKEYS);
					il2.add(AttributeName.CLASS);
					il2.appendUnique(r.getPartIDKeys());
				}

				setAttributes(parent, il2);
			}
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
					if (ignoreList == null || !ignoreList.contains(a.getLocalName()))
					{
						try
						{
							setAttribute(a.getNodeName(), a.getNodeValue(), a.getNamespaceURI());
						}
						catch (JDFException x)
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
	 * create a string link from an integer
	 * 
	 * @param id the integer value to convert to an ID
	 * @deprecated 060307 internal legacy method
	 * @return String - a unique ID string
	 */
	@Deprecated
	public String idString(final int id)
	{
		String s = JDFConstants.LINK;
		final String strID = Integer.toString(id);

		for (int i = JDFConstants.LINK.length() - strID.length(); i > 0; i--)
		{
			s += "0";
		}

		s += strID;
		return s;
	}

	/**
	 * static version of GetParentJDF - get the local JDF node that this element resides in. <br>
	 * if e is a JDF node, return it's parent<br>
	 * if e is the root JDF node, return a null reference
	 * 
	 * @param e the KElement (may be in foreign namespace)
	 * 
	 * @return JDFNode - the local parent JDF of this element
	 */
	public static JDFNode getParentJDF(final KElement e)
	{
		KElement e2 = e;
		if (e2 instanceof JDFNode)
		{
			e2 = e2.getParentNode_KElement();
		}
		if (e2 == null)
		{
			return null;
		}
		e2 = e2.getDeepParent(ElementName.JDF, 0);
		return e2 == e ? null : (JDFNode) e2;
	}

	/**
	 * get the local JDF node that this element resides in<br>
	 * if this is a JDF node, return it's parent<br>
	 * if this is the root JDF node, return a null reference
	 * 
	 * @return JDFNode - the local parent JDF of this element
	 */
	public JDFNode getParentJDF()
	{
		return getParentJDF(this);
	}

	/**
	 * get invalid attributes
	 * 
	 * @param level {@linkplain JDFElement.EnumValidationLevel validation level}
	 * @param bIgnorePrivate if true, do not worry about attributes in other namespaces
	 * @param nMax maximum size of the returned vector
	 * @return VString - a vector of invalid attribute names
	 * 
	 * @default getInvalidAttributes(level, true, 9999999)
	 */
	public VString getInvalidAttributes(final EnumValidationLevel level, final boolean bIgnorePrivate, final int nMax)
	{

		final AttributeInfo ai = getTheAttributeInfo();
		VString v = getInvalidAttributes_JDFElement(level, bIgnorePrivate, nMax, ai);
		String s = getNamespaceURI();
		if (s != null && s.toLowerCase().indexOf(JDFConstants.CIP4ORG) >= 0 && !s.equals(JDFConstants.JDFNAMESPACE))
		{
			if (v == null)
			{
				v = new VString(AttributeName.XMLNS, null);
			}
			else
			{
				v.appendUnique(AttributeName.XMLNS);
			}
		}
		s = getAttribute(AttributeName.XMLNSXSI, null, null);
		{
			if ((s != null) && (!s.equals(AttributeName.XSIURI)))
			{
				v.appendUnique(AttributeName.XMLNSXSI);
			}
		}
		return v;
	}

	/**
	 * get invalid attributes
	 * 
	 * @param level validation level
	 * @param bIgnorePrivate if true, do not worry about attributes in other namespaces
	 * @param nMax maximum size of the returned vector
	 * @param ai 
	 * @return VString - a vector of invalid attribute names
	 * 
	 * @default getInvalidAttributes_JDFElement(level, true, 9999999)
	 */
	private VString getInvalidAttributes_JDFElement(final EnumValidationLevel level, final boolean bIgnorePrivate, final int nMax, final AttributeInfo ai)
	{
		final VString vAttsReturn = new VString();
		if (!isInJDFNameSpaceStatic(this))
			return vAttsReturn;
		int numAtts = 0;
		final NamedNodeMap nm = getAttributes();
		Set<String> vReq;

		if (EnumValidationLevel.isRequired(level) && !isIncomplete())
		{
			vReq = getTheAttributeInfo().requiredAttribs().getSet();
		}
		else
		{
			vReq = null;
		}

		if (nm != null)
		{
			final int siz = nm.getLength();
			for (int i = 0; i < siz; i++)
			{
				final Node a = nm.item(i);
				final String key = a.getLocalName();
				final String ns = a.getNamespaceURI();
				if ((JDFConstants.XSI.equals(ns)) || JDFConstants.XMLNS.equals(ns))
				{
					continue;
				}
				if (bIgnorePrivate && !isWildCard(ns) && !JDFConstants.JDFNAMESPACE.equals(ns))
					continue;

				if (ai.getAttributeType(key) == null || !ai.validAttribute(key, a.getNodeValue(), level))
				{
					vAttsReturn.add(key);
					if (++numAtts >= nMax)
					{
						return vAttsReturn;
					}
				}
				if (vReq != null)
				{
					boolean rem = vReq.remove(key);
					if (rem && vReq.isEmpty())
						vReq = null;
				}
			}
		}
		if (vReq != null)
		{
			VString v = new VString();
			Iterator<String> it = vReq.iterator();
			while (it.hasNext())
			{
				String next = it.next();
				if (getAttribute(next, null, null) == null)
					v.add(next);
			}
			if (v.size() > 0)
				vAttsReturn.appendUnique(v);
		}
		return vAttsReturn;
	}

	/**
	 * check whether this is a subelem of an incomplete resource and thus need not contain all traits
	 * 
	 * @return
	 */
	private boolean isIncomplete()
	{
		final JDFResource r = JDFResource.getResourceRoot(this);
		return r == null ? false : "Incomplete".equals(r.getAttribute(AttributeName.STATUS, null, null));
	}

	/**
	 * get invalid elements
	 * 
	 * @param level validation level
	 * @param bIgnorePrivate if true, do not worry about elements in other namespaces
	 * @param nMax maximum size of the returned vector
	 * @return VString - a vector of invalid element names
	 * 
	 * @default GetInvalidElements(level, true, 999999)
	 */
	public VString getInvalidElements(final EnumValidationLevel level, final boolean bIgnorePrivate, final int nMax)
	{
		return getInvalidElements_JDFElement(level, bIgnorePrivate, nMax);
	}

	/**
	 * Method GetInvalidElements_JDFElement.
	 * 
	 * @param level validation level
	 * @param bIgnorePrivate if true, do not worry about elements in other namespaces
	 * @param nMax maximum size of the returned vector
	 * @return VString - a vector of invalid element names
	 */
	public VString getInvalidElements_JDFElement(final EnumValidationLevel level, final boolean bIgnorePrivate, final int nMax)
	{
		final VString vBad = new VString();
		if (bIgnorePrivate && !isInJDFNameSpaceStatic(this))
			return vBad;

		int i = 0;
		int num = 0;
		final VElement v = getChildElementVector(null, null, null, true, 0, false);

		for (i = 0; i < v.size(); i++)
		{
			final KElement e = v.elementAt(i);
			if ((e instanceof JDFElement) && !((JDFElement) e).isValid(level))
			{
				vBad.add(e.getLocalName());
				if (++num >= nMax)
				{
					return vBad;
				}
			}
		}

		if (EnumValidationLevel.isRequired(level) && !isIncomplete())
		{
			vBad.appendUnique(new VString(getMissingElements(nMax)));
		}

		vBad.appendUnique(new VString(getUnknownElements(bIgnorePrivate, nMax)));

		return vBad;
	}

	/**
	 * Set attribute CommentURL
	 * 
	 * @param value the CommentURL value
	 */
	public void setCommentURL(final String value)
	{
		setAttribute(AttributeName.COMMENTURL, value, null);
	}

	/**
	 * Get string attribute CommentURL
	 * 
	 * @return the value of the attribute commentURL
	 */
	public String getCommentURL()
	{
		return getAttribute(AttributeName.COMMENTURL, null, JDFConstants.EMPTYSTRING);
	}

	/**
	 * Get the string of a refelement that points to this, i.e.NodeName+"Ref"
	 * 
	 * @return the nodename of a refelement
	 */
	protected String getRefString()
	{
		return getNodeName() + JDFConstants.REF;
	}

	/**
	 * get the LDFLib version
	 * 
	 * @return the JDFLib version
	 */
	public String jdfVersion()
	{
		return "1.4";
	}

	/**
	 * returns the official JDF schema URI for a particular version
	 * fool proof schema url as of November 5th, 2003
	 * 
	 * @return the URL that fits to majorVersion and minorVersion - null if not supported
	 */
	public static String getSchemaURL()
	{
		return getSchemaURL(1, 1); // "http://www.CIP4.org/JDFSchema_1_1"
	}

	/**
	 * @param majorVersion the major version - only 1 is supported
	 * @param minorVersion the minor version - only 0 or 1 are supported
	 * @return the URL that fits to majorVersion and minorVersion - null if not supported
	 */
	public static String getSchemaURL(final int majorVersion, final int minorVersion)
	{
		if (majorVersion == 1)
		{
			if (minorVersion == 0)
			{
				return "http://www.CIP4.org/JDFSchema_1";
			}
			else if (minorVersion >= 1)
			{
				return "http://www.CIP4.org/JDFSchema_1_1";
			}
		}
		return "http://www.CIP4.org/JDFSchema_" + majorVersion + "_" + minorVersion;
	}

	/**
	 * returns the JDFElement::EnumVersion, where new elements will be generated in by default
	 * 
	 * @return the default version
	 */
	public static EnumVersion getDefaultJDFVersion()
	{
		return defaultVersion;
	}

	// ////////////////////////////////////////////////////////////////////
	/**
	 * sets the JDFElement::EnumVersion, where new elements will be generated in by default<br>
	 * Attention this is static and global. Therefore it should generally be be called on initialization
	 * 
	 * @param vers the new default version
	 */
	public static synchronized void setDefaultJDFVersion(final EnumVersion vers)
	{
		defaultVersion = vers;
	}

	/**
	 * get Element ID prefix
	 * 
	 * @return the ID prefix of JDFElement
	 */
	public String getElementIDPrefix()
	{
		return "l";
	}

	/**
	 * gets a new ID for the element
	 * 
	 * @param lastID the highest ID that has been created before
	 * 
	 * @return the unique ID string
	 */
	public String newID(final String lastID)
	{
		final String idPrefix = getIDPrefix();
		if (lastID == null || lastID.equals(JDFConstants.EMPTYSTRING))
		{
			return idPrefix + uniqueID(0);
		}

		final JDFElement p = (JDFElement) getParentNode_KElement();
		if (p == null)
		{
			return idPrefix + uniqueID(0);
		}

		return generateDotID(AttributeName.ID, null);
	}

	/**
	 * defines an enumerated list of attributes ; used by the automated code generator
	 * 
	 * @param key the attribute name
	 * @param v comma separated string of allowed values
	 * @param nameSpaceURI attribute namespace uri
	 * @param def the default enum if it does not exist
	 * @param bInherit if true: recurse into parent elements when searching the attribute as well
	 * 
	 * @since 300402
	 * 
	 * @return int - the enumeration equivalent of the attribute of the attribute def if
	 * @deprecated use EnumXYZ.getEnum(getAttribute(key, namespaceURI, def)
	 * @default getEnumAttribute(key, v, null, -1, false)
	 */
	@SuppressWarnings("unchecked")
	@Deprecated
	public int getEnumAttribute(final String key, final Vector v, final String nameSpaceURI, final int def, final boolean bInherit)
	{
		String s = null;
		int i = def;

		if (bInherit)
		{
			s = getInheritedAttribute(key, nameSpaceURI, JDFConstants.EMPTYSTRING);
		}
		else
		{
			s = getAttribute(key, nameSpaceURI, JDFConstants.EMPTYSTRING);
		}

		if (!s.equals(JDFConstants.EMPTYSTRING) && v.contains(s))
		{
			i = v.indexOf(s);
		}

		return i;
	}

	/**
	 * defines an enumerated list of attributes ; used by the automated code generator
	 * 
	 * @param key the attribute name
	 * @param v comma separated string of allowed values
	 * @param nameSpaceURI attribute namespace uri
	 * @param def the default enum if it does not exist
	 * @param bInherit if true: recurse into parent elements when searching the attribute as well
	 * 
	 * @return the Vector of enumeration equivalents of the attribute
	 * @since 300402
	 * @deprecated use getEnumerationsAttribute(key, nameSpaceURI, EnumXYZ.getEnum(0), bInherit)
	 * @default getEnumerationsAttribute(key, allowedValues, null, -1, false)
	 */
	@SuppressWarnings("unchecked")
	@Deprecated
	public Vector getEnumerationsAttribute(final String key, final Vector v, final String nameSpaceURI, final int def, final boolean bInherit)
	{
		final VString vAllowed = new VString();
		vAllowed.addAll(v);

		String strAtt = JDFConstants.EMPTYSTRING;
		final VString vs = new VString();
		Vector vAtts = null;

		if (bInherit)
		{
			strAtt = getInheritedAttribute(key, nameSpaceURI, JDFConstants.EMPTYSTRING);
		}
		else
		{
			strAtt = getAttribute(key, nameSpaceURI, JDFConstants.EMPTYSTRING);
		}
		if (strAtt.equals(JDFConstants.EMPTYSTRING))
		{
			vs.add(strAtt);
		}
		vAtts = StringUtil.tokenize(strAtt, JDFConstants.BLANK, false);
		vs.addAll(vAtts);

		final Vector ret = new Vector();
		if (vs.isEmpty())
		{
			if (def >= 0)
			{
				ret.addElement(new Integer(def));
			}
			return ret;
		}

		// 080502 RP was vAllowed,size - oops
		for (int i = 0; i < vs.size(); i++)
		{
			final int enumIndex = vAllowed.index(vs.stringAt(i));
			if (enumIndex >= 0)
			{
				ret.addElement(new Integer(enumIndex));
			}
			else
			{
				// illegal value
				ret.addElement(new Integer(-1));
			}
		}

		return ret;
	}

	/**
	 * defines an enumerated list of attributes; used by the automated code generator
	 * 
	 * @param key the attribute local name
	 * @param nameSpaceURI the namespace URI
	 * @param enu a dummy enumeration of the correct type, typically EnumXYZ.getEnum(0)
	 * @param bInherit if true, also recurse into parent elements when searching the attribute
	 * @return Vector of ValuedEnum, null if no enum was set
	 */
	public Vector<? extends ValuedEnum> getEnumerationsAttribute(final String key, final String nameSpaceURI, final ValuedEnum enu, final boolean bInherit)
	{
		String strAtt = null;
		final Vector<ValuedEnum> vEnum = new Vector<ValuedEnum>();

		if (bInherit)
		{
			strAtt = getInheritedAttribute(key, nameSpaceURI, null);
		}
		else
		{
			strAtt = getAttribute(key, nameSpaceURI, null);
		}
		if (strAtt == null)
		{
			return null;
		}

		final VString vAtts = StringUtil.tokenize(strAtt, JDFConstants.BLANK, false);

		try
		{
			final Class<?> methodArgs[] = { String.class };
			final Method m = enu.getClass().getMethod("getEnum", methodArgs);
			for (int i = 0; i < vAtts.size(); i++)
			{
				final Object args[] = { vAtts.elementAt(i) };
				final ValuedEnum ve = (ValuedEnum) m.invoke(null, args);
				// there was an invalid token
				if (ve != null)
				{
					vEnum.add(ve);

				}
			}
		}
		catch (final SecurityException e1)
		{
			// in case of exceptions, simply apply NMTOKENS rule
		}
		catch (final NoSuchMethodException e1)
		{
			// in case of exceptions, simply apply NMTOKENS rule
		}
		catch (final IllegalArgumentException e)
		{
			// in case of exceptions, simply apply NMTOKENS rule
		}
		catch (final IllegalAccessException e)
		{
			// in case of exceptions, simply apply NMTOKENS rule
		}
		catch (final InvocationTargetException e)
		{
			// in case of exceptions, simply apply NMTOKENS rule
		}
		// all were ok
		return vEnum.size() == 0 ? null : vEnum;
	}

	/**
	 * set an enumerated list of attributes; used by the automated code generator
	 * 
	 * @param key the attribute name
	 * @param value the enumeration vector
	 * @param nameSpaceURI attribute namespace uri
	 * @throws JDFException wrong data type in vector
	 */
	protected void setEnumerationsAttribute(final String key, final Vector<? extends ValuedEnum> value, final String nameSpaceURI)
	{
		String s = null;
		if (value != null)
		{
			int n = 0;
			final Iterator<? extends ValuedEnum> valueIterator = value.iterator();
			while (valueIterator.hasNext())
			{
				final ValuedEnum o = valueIterator.next();
				if (n++ > 0)
				{
					s += JDFConstants.BLANK;
				}
				else
				{
					s = "";
				}

				s += o.getName();
			}
		}
		setAttribute(key, s, nameSpaceURI);
	}

	/**
	 * is the attribute valid and of type iType? <br>
	 * iType is of type EnumAttributeType, but may be expanded in child classes
	 * 
	 * @param key the attribute name
	 * @param iType the attribute type
	 * @param bRequired true if this attribute is required
	 * @param nameSpaceURI attribute namespace uri
	 * 
	 * @return boolean: true if the attribute is valid
	 * 
	 * @tbd implement URL validation
	 * @deprecated clean up attributeInfo tables instead use KElement public boolean validAttribute(String key,String nameSpaceURI, EnumValidationLevel level)
	 * 
	 * @default ValidAttribute(key, iType, bRequired, null)
	 */
	@Deprecated
	public boolean validAttribute(final String key, final AttributeInfo.EnumAttributeType iType, final boolean bRequired, final String nameSpaceURI)
	{

		if (!hasAttribute(key, null, false))
		{
			return !bRequired;
		}
		final String val = getAttribute(key, nameSpaceURI, null);
		return AttributeInfo.validStringForType(val, iType, null);
	}

	/**
	 * ValidEnumAttribute - is the attribute valid and does it fit allowedValues, iType is of type EnumAttributeType but may be expanded in child classes
	 * 
	 * @param key the attribute name
	 * @param v Vector with all valid enums
	 * @param bRequired true if this attribute is required
	 * @param nameSpaceURI attribute namespace uri
	 * 
	 * @return booelan - true if the attribute is valid
	 * @deprecated use getTheAttributeInfo instead
	 * @default ValidEnumAttribute(key, v, bRequired, null)
	 */
	@SuppressWarnings("unchecked")
	@Deprecated
	public boolean validEnumAttribute(final String key, final Vector v, final boolean bRequired, final String nameSpaceURI)
	{
		if (!hasAttribute(key, nameSpaceURI, false))
		{
			return !bRequired;
		}
		return getEnumAttribute(key, v, nameSpaceURI, -1, false) >= 0;
	}

	/**
	 * is the attribute valid and does it fit allowedValues. iType is of type EnumAttributeType but may be expanded in child classes
	 * 
	 * @param key the attribute name
	 * @param vs comma separated string of allowed values
	 * @param bRequired true if this attribute is required
	 * @param nameSpaceURI attribute namespace uri
	 * @return true, if the attribute is valid
	 * @deprecated use getTheAttributeInfo instead
	 */
	@SuppressWarnings("unchecked")
	@Deprecated
	public boolean validEnumerationsAttribute(final String key, final Vector vs, final boolean bRequired, final String nameSpaceURI)
	{
		if (!hasAttribute(key, nameSpaceURI, false))
		{
			return !bRequired;
		}

		final Vector v = getEnumerationsAttribute(key, vs, nameSpaceURI, -1, false);

		for (int i = 0; i < v.size(); i++)
		{
			if (((Integer) v.elementAt(i)).intValue() == -1)
			{
				return false;
			}
		}

		return true;
	}

	/**
	 * @see org.apache.xerces.dom.ParentNode#replaceChild(org.w3c.dom.Node, org.w3c.dom.Node)
	 */
	@Override
	public synchronized Node replaceChild(final Node arg0, final Node arg1) throws DOMException
	{
		setDirty(false);
		if (arg1 instanceof JDFElement)
		{
			((JDFElement) arg1).clearTargets();
		}
		return super.replaceChild(arg0, arg1);
	}

	/**
	 * @see org.apache.xerces.dom.ParentNode#removeChild(org.w3c.dom.Node)
	 */
	@Override
	public synchronized Node removeChild(final Node arg0) throws DOMException
	{
		setDirty(false);
		if (arg0 instanceof JDFElement)
		{
			((JDFElement) arg0).clearTargets();
		}

		return super.removeChild(arg0);
	}

	/**
	 * Gets the target of link. Follows an ID-IDREF pair by recursively searching for an attrib with the value id
	 * @param id value of the ID tag to search
	 * @param attrib name of the ID tag, defaults to "ID"
	 * @return KElement the target of link - the element node.
	 */
	public KElement getTarget_JDFElement(final String id, String attrib)
	{
		if (id == null || id.equals(JDFCoreConstants.EMPTYSTRING))
		{
			return null;
		}
		boolean bID = false;
		KElement kRet = null;
		if (attrib == null)
		{
			attrib = JDFCoreConstants.ID;
		}

		// try to find the target ID in the cached list
		XMLDocUserData userData = getXMLDocUserData();
		if (attrib.equals(JDFCoreConstants.ID) && userData != null)
		{
			kRet = userData.getTarget(id);
			bID = true;
		}

		// if it wasn't in the cached list, search for it
		if (kRet == null)
		{
			// loop upwards from here
			// links are most likely quite local
			KElement excludeElement = null;
			KElement root = this;
			final KElement docRoot = getDocRoot();
			boolean bFound = false;
			if (!bID)
			{
				userData = null;
			}

			while (root != null && !bFound)
			{
				final KElement deepElement = getDeepElementByID(root, attrib, id, excludeElement, userData);

				// search tree one level higher
				if (deepElement == null)
				{
					if (root == docRoot)
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
	 * Inserts the Element elementName before the existing Element node beforeChild. If beforeChild is <code>null</code> , insert elementName at the end of the
	 * list of children. If elementName is a DocumentFragment object, all of its children are inserted, in the same order, before beforeChild. If the
	 * elementName is already in the tree, it is removed first.
	 * <p>
	 * default: insertBefore(elementName, beforeChild, null)
	 * @param elementName The elementName to insert the element itself will be created
	 * @param beforeChild The reference element, i.e., the elemente before which the new element must be inserted
	 * @param nameSpaceURI The namespace to create elementName in
	 * @return KElement the element being inserted
	 */
	@Override
	public KElement insertBefore(final String elementName, final Node beforeChild, final String nameSpaceURI)
	{
		final KElement newChild = super.insertBefore(elementName, beforeChild, nameSpaceURI);
		if (newChild != null)
		{
			Document od = getOwnerDocument();
			if (od instanceof DocumentJDFImpl && ((DocumentJDFImpl) od).bInitOnCreate)
				newChild.init();
		}
		return newChild;
	}

	@Override
	protected void clearTargets()
	{
		final XMLDocUserData ud = getXMLDocUserData();
		if (ud != null)
		{
			if (hasChildElements()) // who knows what is down there -- clear
			// cache
			{
				ud.clearTargets();
			}
			else
			// only need to remove this element
			{
				final String id = super.getAttribute(JDFCoreConstants.ID);
				if (id != null)
				{
					ud.removeTarget(id);
				}
			}
		}
	}

	/**
	 * same as @see clone but the clone is in a new document
	 * @see java.lang.Object#clone()
	 * @return
	*/
	@Override
	public KElement cloneNewDoc()
	{
		JDFDoc d = new JDFDoc();
		d.copyMeta(getOwnerDocument_KElement());
		d.setInitOnCreate(false);
		d.setRoot(getNodeName(), getNamespaceURI());
		KElement e = d.getRoot();
		e.copyInto(this, false);
		d.setInitOnCreate(true);
		return e;
	}

	/** 
	 * copies a node into this, ignoring identical node names i.e. duplicating elements
	 *  
	 * @param src
	 * @param bRemove if true, remove existing information, else retain and overwrite / merge
	 * @return this
	 * 
	 */
	@Override
	public KElement copyInto(KElement src, boolean bRemove)
	{
		if (src == null || src == this)
			return this;

		JDFDoc ownerDocument_JDF = getOwnerDocument_JDFElement();
		boolean b = ownerDocument_JDF.getInitOnCreate();
		try
		{
			ownerDocument_JDF.setInitOnCreate(false);
			super.copyInto(src, bRemove);
		}
		finally
		{
			// rewind
			ownerDocument_JDF.setInitOnCreate(b);
		}
		return this;
	}

	/**
	 * get the string value from an enumerated set of values
	 * 
	 * @param value - the enum that is to be translated to a string
	 * @param allowedValues - comma separated string of allowed values
	 * @deprecated use EnumXYZ.getEnum(int)
	 * @return String - the string for the enum
	 */
	@SuppressWarnings("unchecked")
	@Deprecated
	public String getEnumString(final int value, final String allowedValues)
	{
		final Vector vs = new Vector();
		vs.addAll(StringUtil.tokenize(allowedValues, JDFConstants.COMMA, false));

		if (value >= vs.size())
		{
			return JDFConstants.EMPTYSTRING;
		}

		return (String) vs.elementAt(value);
	}

	/**
	 * Get the document object that owns this
	 * 
	 * @return JDFDoc the owner document of this
	 */
	public JDFDoc getOwnerDocument_JDFElement()
	{
		final Document doc = getOwnerDocument();
		return new JDFDoc(doc);
	}

	/**
	 * map a node's localname to an enumeration defined by allowedValues
	 * 
	 * @param allowedValues comma separated string of allowed values
	 * 
	 * @return int - the enum that corresponds to the nodename
	 * @deprecated
	 */
	@SuppressWarnings("unchecked")
	@Deprecated
	public int getEnumNodeName(final Vector allowedValues)
	{
		// 050629 RP made localname. TBD actually check whether the namespace is
		// ok
		final String s = getLocalName();

		for (int i = 0; i < allowedValues.size(); i++)
		{
			if (/* ! */
			s.equalsIgnoreCase((String) allowedValues.elementAt(i)))
			{
				return i;
			} // von Thomas nachgezogen
		}

		return 0; // unknown or illegal
	}

	/**
	 * GetPartMapVector returns a vector of partmaps, null if no parts are present
	 * 
	 * @return Vector
	 */
	protected VJDFAttributeMap getPartMapVector()
	{
		final VElement vE = getChildElementVector(ElementName.PART, null, null, true, 0, false);

		final int size = vE.size();
		final VJDFAttributeMap v = size == 0 ? null : new VJDFAttributeMap();
		for (int i = 0; i < size; i++)
		{
			final JDFPart part = (JDFPart) vE.elementAt(i);
			v.add(part.getPartMap());
		}
		return v;
	}

	/**
	 * gets the part map
	 * 
	 * @return JDFAttributeMap, of the part element
	 */
	protected JDFAttributeMap getPartMap()
	{
		final JDFPart p = (JDFPart) getElement(ElementName.PART, null, 0);
		if (p == null)
		{
			return null;
		}
		return p.getPartMap();
	}

	/**
	 * sets all parts to those defined in vParts
	 * 
	 * @param vPart vector of attribute maps for the parts
	 */
	protected void setPartMapVector(final VJDFAttributeMap vPart)
	{
		removeChildren(ElementName.PART, null, null);
		if (vPart == null)
		{
			return;
		}

		for (JDFAttributeMap part : vPart)
		{
			final KElement p = appendElement(ElementName.PART, null);
			p.setAttributes(part);
		}
	}

	/**
	 * sets part to mPart
	 * 
	 * @param mPart attribute map for the part to set
	 */
	protected void setPartMap(final JDFAttributeMap mPart)
	{
		removeChildren(ElementName.PART, null, null);
		if ((mPart != null) && !mPart.isEmpty())
		{
			final KElement p = appendElement(ElementName.PART, null);
			p.setAttributes(mPart);
		}
	}

	/**
	 * removes the part defined in mPart
	 * 
	 * @param mPart attribute map for the part to remove
	 */
	protected void removePartMap(final JDFAttributeMap mPart)
	{
		final VElement vE = getChildElementVector(ElementName.PART, null, null, true, 0, false);

		final int size = vE.size();
		for (int i = 0; i < size; i++)
		{
			final KElement e_i = (vE.elementAt(i));
			final JDFAttributeMap a_Map = e_i.getAttributeMap();

			if (a_Map.subMap(mPart))
			{
				vE.remove(i);
			}
		}
	}

	/**
	 * checks whether the part defined in mPart is included in <i>this</i>
	 * 
	 * @param mPart Attribute map to check
	 * @return true if <i>this</i> has a part containing mPart
	 */
	protected boolean hasPartMap(final JDFAttributeMap mPart)
	{
		final VElement vE = getChildElementVector(ElementName.PART, null, null, true, 0, false);

		final int size = vE.size();
		for (int i = 0; i < size; i++)
		{
			final KElement e_i = (vE.elementAt(i));
			final JDFAttributeMap a_Map = e_i.getAttributeMap();

			if (a_Map.subMap(mPart))
			{
				return true;
			}
		}
		return false;
	}

	/**
	 * returns true if the enumeration level is either Complete or RecursiveComplete, i.e. if the parameter is required
	 * 
	 * @param level the level to check
	 * @return true if required
	 * @deprecated use EnumValidationLevel.isRequired()
	 */
	@Deprecated
	public static boolean requiredLevel(final EnumValidationLevel level)
	{
		return EnumValidationLevel.isRequired(level);
	}

	/**
	 * GetHRefs - get inter-resource linked resource IDs
	 * 
	 * @param vDoneRefs
	 * @param bRecurse if true, recurse followed refs
	 * 
	 * @return VString
	 * @deprecated use getHRefs(VString vDoneRefs, boolean bRecurse, boolean bExpand)
	 * @default GetHRefs(null, false);
	 */
	@Deprecated
	public VString getHRefs(final VString vDoneRefs, final boolean bRecurse)
	{
		return getHRefs(vDoneRefs, bRecurse, false);
	}

	/**
	 * GetHRefs - get inter-resource linked resource IDs
	 * 
	 * @param vDoneRefs (use null by default)
	 * @param bRecurse if true recurse followed refs
	 * @param bExpand if true expand partitioned resources
	 * 
	 * @return VString - the vector of referenced resource IDs
	 * 
	 * @default GetHRefs(null, false);
	 */
	public VString getHRefs(VString vDoneRefs, final boolean bRecurse, final boolean bExpand)
	{

		if (vDoneRefs == null)
		{
			vDoneRefs = new VString();
		}

		HashSet<String> h = new HashSet<String>();
		if (bExpand && (this instanceof JDFResource))
		{
			final VElement vLeaves = ((JDFResource) this).getLeaves(true);
			final int siz = vLeaves.size();
			for (int i = 0; i < siz; i++)
			{
				final HashSet<String> h2 = vLeaves.item(i).fillHashSet(AttributeName.RREF, null);
				if (h2 != null)
				{
					h.addAll(h2);
				}
			}
		}
		else
		{
			h = fillHashSet(AttributeName.RREF, null);
		}
		final int iFirstPos = vDoneRefs.size(); // get the previous size
		final VString v2 = new VString();
		if (h != null)
		{
			v2.addAll(h);
		}

		vDoneRefs.appendUnique(v2); // get the new size
		if (bRecurse)
		{
			final int iLastPos = vDoneRefs.size();

			// recurse only the new rrefs
			for (int i = iFirstPos; i < iLastPos; i++)
			{
				final String s = vDoneRefs.elementAt(i);
				final KElement e = getTarget(s, AttributeName.ID);
				if (e instanceof JDFElement)
				{
					vDoneRefs = ((JDFElement) e).getHRefs(vDoneRefs, true, bExpand);
				}
			}
		}

		return vDoneRefs;
	}

	/**
	 * get inter-resource linked resource vector
	 * 
	 * @param bRecurse
	 * @deprecated use getvHRefRes(true,false);
	 * @return VElement
	 */
	@Deprecated
	public VElement getvHRefRes(final boolean bRecurse)
	{
		return getvHRefRes(bRecurse, false);
	}

	/**
	 * get inter-resource linked resource vector
	 * 
	 * @param bRecurse if true, recurse followed links
	 * @param bExpand if true, expand partitioned resources and follow the refs from the leaves
	 * 
	 * @return VElement - the inter-resource linked resource vector
	 */
	public VElement getvHRefRes(final boolean bRecurse, final boolean bExpand)
	{
		final VString sRefs = getHRefs(null, bRecurse, bExpand);
		final VElement v = new VElement();

		for (int i = 0; i < sRefs.size(); i++)
		{
			final KElement kEl = getTarget(sRefs.elementAt(i), AttributeName.ID);
			if (kEl instanceof JDFResource)
			{
				v.appendUnique(kEl);
			}
		}

		return v;
	}

	/**
	 * Append to attribute rRefs if it is not yet in the list
	 * 
	 * @param value the rRef token to append
	 * @deprecated rRefs was deprecated in JDF 1.2
	 */
	@Deprecated
	public void appendrRefs(final String value)
	{
		appendAttribute(AttributeName.RREFS, value, null, JDFConstants.BLANK, true);
	}

	/**
	 * Remove value from attribute rRefs if it is in the list
	 * 
	 * @param value the rRef token to remove from the NMTOKENS list
	 * @return
	 * @deprecated rRefs was deprecated in JDF 1.2
	 */
	@Deprecated
	public int removeFromrRefs(final String value)
	{
		return removeFromAttribute(AttributeName.RREFS, value, null, JDFConstants.BLANK, -1);
	}

	/**
	 * Get string attribute rRefs, i.e. split the blank separed attribute list
	 * @return
	 * 
	 * @deprecated rRefs was deprecated in JDF 1.2
	 */
	@Deprecated
	public VString getrRefs()
	{
		final VString vStr = new VString();
		vStr.setAllStrings(getAttribute_KElement(AttributeName.RREFS, null, JDFConstants.EMPTYSTRING), JDFConstants.BLANK);

		return vStr;
	}

	/**
	 * Gets the root resource of the target returns a null JDFElement if it does not exist or the name mangling is illegal
	 * 
	 * @param id the id of the linked root. If null, the id of <i>this</i> is used.
	 * 
	 * @return JDFResource the resource root of the resource referenced by this resource link
	 */
	public JDFResource getLinkRoot(final String id)
	{
		JDFResource ret = null;

		final String myid = (id == null) ? getAttribute(AttributeName.RREF, null, null) : id;
		if (myid == null)
		{
			return null;
		}

		boolean bSearching = true;
		final XMLDocUserData ud = getXMLDocUserData();
		if (ud != null) // grab target from the cache
		{
			final KElement kOwner = ud.getTarget(myid);
			if (kOwner != null)
			{
				if (kOwner instanceof JDFResource)
				{
					ret = (JDFResource) kOwner;
					if (!ret.isResourceRootRoot())
					{
						ret = null;
					}
				}
			}

			if (ret != null) // we found something in the cache
			{
				bSearching = false;
				if (!validResourcePosition(ret))
				{
					bSearching = true;
					ret = null;
				}
			}
		}

		if (ret == null)
		{

			// start with the first jdf node parent of this so that nod is
			// initialized
			JDFNode nod = (JDFNode) getDeepParent(ElementName.JDF, 0);
			if (nod != null) // we are in a JDF, not in a JMF
			{
				while (bSearching)
				{
					if (nod != null)
					{
						final JDFResourcePool rp = nod.getResourcePool();
						if (rp != null)
						{
							ret = rp.getResourceByID(myid);
							bSearching = ret == null;
						}

						if (bSearching)
						{
							nod = nod.getParentJDF();
							bSearching = nod != null;
						}
					}
				}
			}
			else
			// we may be in a JMF and have nothing in the cache
			{
				if (bSearching)
				{
					final KElement jmf = getDeepParent(ElementName.JMF, 0);
					if (jmf != null)
					{
						KElement rootElem = jmf.getChildWithAttribute(null, AttributeName.ID, null, myid, 0, false);
						if (rootElem instanceof JDFResource)
						{
							ret = (JDFResource) rootElem;
						}
					}
				}
			}
		}
		return ret;
	}

	/**
	 * returns the official JDF version string
	 * 
	 * @return String: the inherited version information or "1.3" if no valid version info was found
	 * @deprecated use getDefaultJDFVersion()
	 */
	@Deprecated
	public final String version()
	{
		final String ver = getInheritedAttribute(AttributeName.VERSION, null, JDFConstants.EMPTYSTRING);
		if (JDFConstants.EMPTYSTRING.equals(ver))
		{
			return JDFConstants.VERSION_1_3;
		}
		return JDFElement.EnumVersion.getEnum(ver).getName();
	}

	/**
	 * set Version to enumVer
	 * 
	 * @param enumVer the EnumVersion to set
	 */
	public void setVersion(final EnumVersion enumVer)
	{
		setAttribute(AttributeName.VERSION, enumVer == null ? null : enumVer.getName(), null);
		AttributeInfo.fixedMap.clear();
		ElementInfo.fixedMap.clear();
	}

	/**
	 * get EnumVersion attribute Version
	 * 
	 * @return EnumVersion - attribute value
	 * @deprecated 060505 use getVersion(boolean);
	 */
	@Deprecated
	public EnumVersion getVersion()
	{
		return getVersion(true);
	}

	/**
	 * get the version of this element
	 * 
	 * @param bInherit if true, check ancestor nodes
	 * @return the version corresponding to this element
	 */
	public EnumVersion getVersion(final boolean bInherit)
	{
		return EnumVersion.getEnum(bInherit ? getInheritedAttribute(AttributeName.VERSION, null, null) : getAttribute(AttributeName.VERSION, null, null));
	}

	/**
	 * get attribute MaxVersion, defaults to version if not set
	 * 
	 * @param bInherit if true recurse through ancestors when searching
	 * @return EnumVersion - attribute value
	 * 
	 * default - getMaxVersion(false)
	 */
	public EnumVersion getMaxVersion(final boolean bInherit)
	{
		final String version = (bInherit) ? getInheritedAttribute(AttributeName.MAXVERSION, null, null) : getAttribute(AttributeName.MAXVERSION, null, null);

		if (version == null)
		{
			return getVersion(bInherit);
		}

		return EnumVersion.getEnum(version);
	}

	/**
	 * Enumeration strings for Version
	 * 
	 * NOTE: If not specified the version defaults to Version 1.3
	 */
	@SuppressWarnings("unchecked")
	public static final class EnumVersion extends ValuedEnum
	{
		private static final long serialVersionUID = 1L;
		private static int m_startValue = 0;

		/**
		 * @see java.lang.Object#toString()
		 * @deprecated just for compiling PrintReady, to be removed afterwards
		 */
		@Deprecated
		@Override
		public String toString()
		{
			return getName();
		}

		private EnumVersion(final String name)
		{
			super(name, m_startValue++);
		}

		/**
		 * casts a String into a corresponding EnumVersion hand coded for speed!
		 * @param enumName the name of the EnumVersion
		 * @return the corresponding EnumVersion
		 */
		public static EnumVersion getEnum(final String enumName)
		{
			if ("1.4".equals(enumName))
			{
				return EnumVersion.Version_1_4;
			}
			else if ("1.3".equals(enumName))
			{
				return EnumVersion.Version_1_3;
			}
			else if ("1.2".equals(enumName))
			{
				return EnumVersion.Version_1_2;
			}
			else if ("1.1".equals(enumName))
			{
				return EnumVersion.Version_1_1;
			}
			else if ("1.0".equals(enumName))
			{
				return EnumVersion.Version_1_0;
			}
			else if ("2.0".equals(enumName))
			{
				return EnumVersion.Version_2_0;
			}
			return EnumVersion.Version_1_4; // the default
		}

		/**
		 * casts a EnumVersion into its corresponding String
		 * 
		 * @param enumValue the EnumVersion to cast
		 * @return the corresponding String
		 */
		public static EnumVersion getEnum(final int enumValue)
		{
			return (EnumVersion) getEnum(EnumVersion.class, enumValue);
		}

		/**
		 * @return Map
		 */
		public static Map getEnumMap()
		{
			return getEnumMap(EnumVersion.class);
		}

		/**
		 * @return List
		 */
		public static List getEnumList()
		{
			return getEnumList(EnumVersion.class);
		}

		/**
		 * gets the EnumVersion iterator
		 * 
		 * @return Iterator
		 */
		public static Iterator iterator()
		{
			return iterator(EnumVersion.class);
		}

		/**
		 * returns true if this is greater than other
		 * @param other
		 * 
		 * @return boolean true if this>other
		 */
		public boolean isGreater(final EnumVersion other)
		{
			if (other == null)
			{
				return true;
			}
			return getValue() > other.getValue();
		}

		/**
		 * @ deprecated EnumVersion.Unknown - don't use EnumVersion.Unknown, it can't be deprecated because of bit operations in eg. AtrInfo.getFirstVersion()
		 */
		/** * */
		public static final EnumVersion Unknown = new EnumVersion(JDFConstants.UNKNOWN);

		/** * */
		public static final EnumVersion Version_1_0 = new EnumVersion(JDFConstants.VERSION_1_0);
		/** * */
		public static final EnumVersion Version_1_1 = new EnumVersion(JDFConstants.VERSION_1_1);
		/** * */
		public static final EnumVersion Version_1_2 = new EnumVersion(JDFConstants.VERSION_1_2);
		/** * */
		public static final EnumVersion Version_1_3 = new EnumVersion(JDFConstants.VERSION_1_3);
		/** * */
		public static final EnumVersion Version_1_4 = new EnumVersion(JDFConstants.VERSION_1_4);
		/** * */
		public static final EnumVersion Version_1_5 = new EnumVersion(JDFConstants.VERSION_1_5);
		/** * */
		public static final EnumVersion Version_1_6 = new EnumVersion(JDFConstants.VERSION_1_6);
		/** * */
		public static final EnumVersion Version_1_7 = new EnumVersion(JDFConstants.VERSION_1_7);
		/** * */
		public static final EnumVersion Version_1_8 = new EnumVersion(JDFConstants.VERSION_1_8);
		/** * */
		public static final EnumVersion Version_1_9 = new EnumVersion(JDFConstants.VERSION_1_9);
		/** * */
		public static final EnumVersion Version_2_0 = new EnumVersion(JDFConstants.VERSION_2_0);

		/**
		 * gets the integer value of the minor version, e.g 2 for 1.3 etc
		 * @return
		 */
		public int getMinorVersion()
		{
			return (getValue() - 1) % 10;
		}

		/**
		 * gets the integer value of the major version, e.g 2 for 1.3 etc
		 * @return
		 */
		public int getMajorVersion()
		{
			return 1 + (getValue() - 1) / 10;
		}

	}

	/**
	 * Enumeration for validation level <br>
	 * <blockquote>
	 * <ul>
	 * <li>level ValidationLevel_NoWarnIncomplete: Ignore warnings and don't require all required parameters <br>
	 * <li>level ValidationLevel_NoWarnComplete: Ignore warnings and require all required parameters <br>
	 * <li>level ValidationLevel_Incomplete: incomplete elements are valid <br>
	 * <li>level ValidationLevel_Complete: full validation of an individual resource <br>
	 * <li>level ValidationLevel_RecursiveIncomplete: incomplete validation of an individual resource and all of its child elements - e.g. for pools <br>
	 * <li>level ValidationLevel_RecursiveComplete: full validation of an individual resource and all of its child elements - e.g. for pools <br>
	 * </ul>
	 * </blockquote>
	 */
	@SuppressWarnings("unchecked")
	public static final class EnumValidationLevel extends ValuedEnum
	{
		private static final long serialVersionUID = 1L;
		private static int m_startValue = 0;

		protected EnumValidationLevel(final String name)
		{
			super(name, m_startValue++);
		}

		/**
		 * @param enumName
		 * @return
		 */
		public static EnumValidationLevel getEnum(final String enumName)
		{
			return (EnumValidationLevel) getEnum(EnumValidationLevel.class, enumName);
		}

		/**
		 * @param enumValue
		 * @return
		 */
		public static EnumValidationLevel getEnum(final int enumValue)
		{
			return (EnumValidationLevel) getEnum(EnumValidationLevel.class, enumValue);
		}

		/**
		 * @return
		 */
		public static Map getEnumMap()
		{
			return getEnumMap(EnumValidationLevel.class);
		}

		/**
		 * @return
		 */
		public static List getEnumList()
		{
			return getEnumList(EnumValidationLevel.class);
		}

		/**
		 * @return
		 */
		public static Iterator iterator()
		{
			return iterator(EnumValidationLevel.class);
		}

		/**
		 * return true if vl is a recursvive EnumValidationLevel
		 * @param vl the EnumValidationLevel to check
		 * @return true if vl is recursive
		 */
		public static boolean isRecursive(final EnumValidationLevel vl)
		{
			return EnumValidationLevel.RecursiveIncomplete.equals(vl) || EnumValidationLevel.RecursiveComplete.equals(vl);
		}

		/**
		 * return true if vl is a recursvive EnumValidationLevel
		 * @param vl the EnumValidationLevel to check
		 * @return true if vl is recursive
		 */
		public static boolean isNoWarn(final EnumValidationLevel vl)
		{
			return EnumValidationLevel.NoWarnComplete.equals(vl) || EnumValidationLevel.NoWarnIncomplete.equals(vl);
		}

		/**
		 * returns true if the enumeration level is either Complete, NoWarnComplete or RecursiveComplete, i.e. if the parameter is required
		 * @param level the level to check
		 * @return true if required
		 */
		public static boolean isRequired(final EnumValidationLevel level)
		{
			return EnumValidationLevel.Complete.equals(level) || EnumValidationLevel.RecursiveComplete.equals(level) || EnumValidationLevel.NoWarnComplete.equals(level);
		}

		/**
		 * ignore warnings and allow missing traits
		 */
		public static final EnumValidationLevel NoWarnIncomplete = new EnumValidationLevel("NoWarnIncomplete");

		/**
		 * ignore warnings and require all traits
		 */
		public static final EnumValidationLevel NoWarnComplete = new EnumValidationLevel("NoWarnComplete");

		/**
		 * show warnings and allow missing traits
		 */
		public static final EnumValidationLevel Incomplete = new EnumValidationLevel(JDFConstants.VALIDATIONLEVEL_INCOMPLETE);

		/**
		 * show warnings and require all traits
		 */
		public static final EnumValidationLevel Complete = new EnumValidationLevel(JDFConstants.VALIDATIONLEVEL_COMPLETE);

		/**
		 * show warnings and allow missing traits- also recurse referenced elements
		 */
		public static final EnumValidationLevel RecursiveIncomplete = new EnumValidationLevel(JDFConstants.VALIDATIONLEVEL_RECURSIVEINCOMPLETE);

		/**
		 * show warnings and require all traits - also recurse referenced elements
		 */
		public static final EnumValidationLevel RecursiveComplete = new EnumValidationLevel(JDFConstants.VALIDATIONLEVEL_RECURSIVECOMPLETE);

		/**
		 * calculate the corresponding nowarn level based on level
		 * @param level the level to strip warnings from
		 * @param noWarning if true, set to nowarne, else set to standard
		 * @return the validationlevel withot warnings
		 */
		public static EnumValidationLevel setNoWarning(EnumValidationLevel level, final boolean noWarning)
		{
			if (noWarning && !isNoWarn(level))
			{
				level = isRequired(level) ? EnumValidationLevel.NoWarnComplete : EnumValidationLevel.NoWarnIncomplete;
			}
			if (!noWarning && isNoWarn(level))
			{
				level = isRequired(level) ? EnumValidationLevel.Complete : EnumValidationLevel.Incomplete;
			}
			return level;
		}

		/**
		 * calculate the corresponding incomplete level based on level
		 * @param level the level to test
		 * @return EnumValidationLevel - the modified level
		 */
		public static EnumValidationLevel incompleteLevel(EnumValidationLevel level)
		{
			if (EnumValidationLevel.Complete.equals(level))
			{
				level = EnumValidationLevel.Incomplete;
			}
			else if (EnumValidationLevel.RecursiveComplete.equals(level))
			{
				level = EnumValidationLevel.RecursiveIncomplete;
			}
			else if (EnumValidationLevel.NoWarnComplete.equals(level))
			{
				level = EnumValidationLevel.NoWarnIncomplete;
			}
			return level;
		}
	}

	/**
	 * @param enumName
	 * @return EnumVersion
	 * @deprecated use EnumVersion.getEnum
	 */
	@Deprecated
	public static EnumVersion stringToVersion(final String enumName)
	{
		return EnumVersion.getEnum(enumName);
	}

	/**
	 * Method getChildElements.
	 * 
	 * @return JDFElement[]
	 * @deprecated use {@link KElement#getChildElementArray KElement.getChildElementArray()}<br>
	 * not typesafe in case of elements in foreign namespaces note that this method previously returned JDFElement[]
	 */
	@Deprecated
	public KElement[] getChildElements()
	{
		return getChildElementArray();
	}

	/**
	 * Method getChildElement
	 * 
	 * @param n Element index n (1 based)
	 * @return JDFElement
	 * 
	 * @deprecated use getElement(null, null ,n)
	 */
	@Deprecated
	public JDFElement getChildElement(final int n)
	{
		int nLocal = n;

		JDFElement eReturn = null;

		final NodeList children = getChildNodes();

		final int cnt = children.getLength();

		for (int i = 0; i < cnt && eReturn == null; i++)
		{
			final Node node = children.item(i);

			if (node.getNodeType() == Node.ELEMENT_NODE)
			{
				nLocal--;

				if (nLocal == 0)
				{
					eReturn = (JDFElement) node;
				}
			}
		}

		return eReturn;
	}

	/**
	 * get unknown elements for a specific pool
	 * 
	 * @param poolType the PoolType to look for
	 * @param nMax max. number of elements to retrieve
	 * @return VString of unknown pool elements
	 * @default getUnknownPoolElements(EnumPoolType poolType, 99999, null)
	 */
	protected VString getUnknownPoolElements(final EnumPoolType poolType, final int nMax)
	{
		final VElement v = getChildElementVector(null, null, null, true, 0, false);
		final VString vElem = new VString();
		int n = 0;
		final VString knownElements = knownElements();

		for (KElement e : v)
		{
			if (e instanceof JDFComment)
			{
				continue;
			}

			if (((EnumPoolType.ResourcePool.equals(poolType)) || (EnumPoolType.ProductionIntent.equals(poolType)) || (EnumPoolType.PipeParams.equals(poolType)))
					&& e instanceof JDFResource)
			{
				continue;
			}

			if (((EnumPoolType.ProductionIntent.equals(poolType)) || (EnumPoolType.RefElement.equals(poolType)) || (EnumPoolType.PipeParams.equals(poolType)))
					&& e instanceof JDFRefElement)
			{
				continue;
			}

			if (((EnumPoolType.ResourceLinkPool.equals(poolType)) || (EnumPoolType.PipeParams.equals(poolType))) && e instanceof JDFResourceLink)
			{
				continue;
			}

			if (EnumPoolType.AuditPool.equals(poolType) && e instanceof JDFAudit)
			{
				continue;
			}

			if (EnumPoolType.AncestorPool.equals(poolType) && e instanceof JDFAncestor)
			{
				continue;
			}

			if (knownElements.contains(e.getLocalName()))
			{
				continue;
			}
			if (e instanceof JDFRefElement)
			{
				final String refName = ((JDFRefElement) e).getRefLocalName();
				if (knownElements.contains(refName))
				{
					continue;
				}
			}

			vElem.add(e.getLocalName());
			if (++n > nMax)
			{
				return vElem;
			}
		}
		return vElem;
	}

	/**
	 * Set attribute SettingsPolicy
	 * 
	 * @param value the SettingsPolicy to set
	 */
	public void setSettingsPolicy(final EnumSettingsPolicy value)
	{
		setAttribute(AttributeName.SETTINGSPOLICY, value == null ? null : value.getName(), null);
	}

	/**
	 * Typesafe enumerated attribute SettingsPolicy
	 * 
	 * @param bInherit recurse through ancestors when searching
	 * @return
	 */
	public EnumSettingsPolicy getSettingsPolicy(final boolean bInherit)
	{
		String s = null;
		if (bInherit)
		{
			s = getInheritedAttribute(AttributeName.SETTINGSPOLICY, null, null);
		}
		else
		{
			s = getAttribute(AttributeName.SETTINGSPOLICY, null, null);
		}
		return EnumSettingsPolicy.getEnum(s);
	}

	/**
	 * Sets attribute BestEffortExceptions with the vector of values
	 * 
	 * @param value vector of BestEffortExceptions tokens
	 */
	public void setBestEffortExceptions(final VString value)
	{
		setAttribute(AttributeName.BESTEFFORTEXCEPTIONS, value, null);
	}

	/**
	 * Append a token to attribute BestEffortExceptions
	 * 
	 * @param value the new BestEffortExceptions token
	 */
	public void appendBestEffortExceptions(final String value)
	{
		appendAttribute(AttributeName.BESTEFFORTEXCEPTIONS, value, null, JDFConstants.BLANK, true);
	}

	/**
	 * Remove a token from attribute BestEffortExceptions
	 * 
	 * @param value the BestEffortExceptions token to remove
	 */
	public void removeFromBestEffortExceptions(final String value)
	{
		removeFromAttribute(AttributeName.BESTEFFORTEXCEPTIONS, value, null, JDFConstants.BLANK, -1);
	}

	/**
	 * Gets the value of attribute BestEffortExceptions
	 * 
	 * @return the attribute value
	 */
	public String getBestEffortExceptions()
	{
		return getAttribute(AttributeName.BESTEFFORTEXCEPTIONS, null, JDFConstants.EMPTYSTRING);
	}

	/**
	 * Sets attribute MustHonorExceptions with the vector of values
	 * 
	 * @param value vector of MustHonorExceptions tokens
	 */
	public void setMustHonorExceptions(final VString value)
	{
		setAttribute(AttributeName.MUSTHONOREXCEPTIONS, value, null);
	}

	/**
	 * Append a token to attribute MustHonorExceptions
	 * 
	 * @param value the new of MustHonorExceptions token
	 */
	public void appendMustHonorExceptions(final String value)
	{
		appendAttribute(AttributeName.MUSTHONOREXCEPTIONS, value, null, JDFConstants.BLANK, true);
	}

	/**
	 * Remove a token from attribute MustHonorExceptions
	 * 
	 * @param value the new of MustHonorExceptions token
	 */
	public void removeFromMustHonorExceptions(final String value)
	{
		removeFromAttribute(AttributeName.MUSTHONOREXCEPTIONS, value, null, JDFConstants.BLANK, -1);
	}

	/**
	 * gets the value of attribute MustHonorExceptions
	 * 
	 * @return the attribute value
	 */
	public String getMustHonorExceptions()
	{
		return getAttribute(AttributeName.MUSTHONOREXCEPTIONS, null, JDFConstants.EMPTYSTRING);
	}

	/**
	 * Sets attribute OperatorInterventionExceptions with the vector of values
	 * 
	 * @param value the vector of OperatorInterventionExceptions tokens
	 */
	public void setOperatorInterventionExceptions(final VString value)
	{
		setAttribute(AttributeName.OPERATORINTERVENTIONEXCEPTIONS, value, null);
	}

	/**
	 * Append a token to attribute OperatorInterventionExceptions
	 * 
	 * @param value the new of OperatorInterventionExceptions token
	 */
	public void appendOperatorInterventionExceptions(final String value)
	{
		appendAttribute(AttributeName.OPERATORINTERVENTIONEXCEPTIONS, value, null, JDFConstants.BLANK, true);
	}

	/**
	 * Remove a token from attribute OperatorInterventionExceptions
	 * 
	 * @param value the new of OperatorInterventionExceptions token
	 */
	public void removeFromOperatorInterventionExceptions(final String value)
	{
		removeFromAttribute(AttributeName.OPERATORINTERVENTIONEXCEPTIONS, value, null, JDFConstants.BLANK, -1);
	}

	/**
	 * gets the value of attribute OperatorInterventionExceptions
	 * 
	 * @return the attribute value
	 */
	public String getOperatorInterventionExceptions()
	{
		return getAttribute(AttributeName.OPERATORINTERVENTIONEXCEPTIONS, null, JDFConstants.EMPTYSTRING);
	}

	/**
	 * Set attribute DescriptiveName
	 * 
	 * @param value value to set the attribute to
	 */
	public void setDescriptiveName(final String value)
	{
		setAttribute(AttributeName.DESCRIPTIVENAME, value, null);
	}

	/**
	 * Get string attribute DescriptiveName
	 * 
	 * @return the attribute value
	 */
	public String getDescriptiveName()
	{
		return getAttribute(AttributeName.DESCRIPTIVENAME, null, JDFConstants.EMPTYSTRING);
	}

	// ////////////////////////////////////////////////////////////////////
	// ////////////////////////////////////////////////////////////////////

	/*
	 * // Element Getter / Setter
	 */

	// ////////////////////////////////////////////////////////////////////
	/**
	 * append an empty GeneralID
	 * 
	 * @return the newly created GeneralID
	 */
	public JDFGeneralID appendGeneralID()
	{
		KElement appendElement = appendElement(ElementName.GENERALID, null);
		KElement next = getFirstChildElement();
		while (next instanceof JDFGeneralID)
			next = next.getNextSiblingElement();
		moveElement(appendElement, next);
		return (JDFGeneralID) appendElement;
	}

	/**
	 * append a GeneralID with idValue, duplicate entries are retained
	 * generalID elements are always placed first and new GeneralID elements are appended at the end of the list
	 * 
	 * @param idUsage the IDUsage attribute of the generalID
	 * @param idValue the IDValue attribute of the generalID
	 * @return the newly created GeneralID
	 */
	public JDFGeneralID appendGeneralID(final String idUsage, final String idValue)
	{
		final JDFGeneralID gid = appendGeneralID();
		gid.setIDValue(idValue);
		gid.setIDUsage(idUsage);
		return gid;
	}

	/**
	 * gets attribute GeneralID
	 * 
	 * @param i get the i'th element that fits
	 * @return the attribute value
	 */
	public JDFGeneralID getGeneralID(final int i)
	{
		return (JDFGeneralID) getElement(ElementName.GENERALID, null, i);
	}

	/**
	 * Creates or Updates a GeneralID with the IDUsage idUsage and IDValue=idValue all entries with a duplicate idUsage are removed
	 * 
	 * @param idUsage usage to set the attribute to
	 * @param idValue value to set the attribute to
	 * @return the newly set GeneralID
	 */
	public JDFGeneralID setGeneralID(final String idUsage, final String idValue)
	{
		final JDFGeneralID gid;
		final VElement v = getChildElementVector_JDFElement(ElementName.GENERALID, null, new JDFAttributeMap(AttributeName.IDUSAGE, idUsage), true, 0, true);
		if (v.size() >= 1)
		{
			gid = (JDFGeneralID) v.elementAt(0);

			// remove any duplicates
			for (int i = 1; i < v.size(); i++)
			{
				v.elementAt(i).deleteNode();
			}
			gid.setIDValue(idValue);
			gid.setIDUsage(idUsage);
		}
		else
		{
			gid = appendGeneralID(idUsage, idValue);
		}
		return gid;
	}

	/**
	 * removes GeneralID with the IDUsage idUsage
	 * 
	 * @param idUsage value to set the attribute to
	 */
	public void removeGeneralID(final String idUsage)
	{
		final VElement v = getChildElementVector_JDFElement(ElementName.GENERALID, null, new JDFAttributeMap(AttributeName.IDUSAGE, idUsage), true, 0, true);
		for (int i = 0; i < v.size(); i++)
		{
			(v.elementAt(i)).deleteNode();
		}
	}

	/**
	 * Gets IDValue of the GeneralID with IDUsage=idUsage null, if none exists
	 * @param idUsage
	 * 
	 * @return String the attribute value
	 * @deprecated use getGeneralID(idUsage, 0)
	 */
	@Deprecated
	public String getGeneralID(final String idUsage)
	{
		return getGeneralID(idUsage, 0);
	}

	/**
	 * Gets IDValue of the GeneralID with IDUsage=idUsage null, if none exists
	 * @param idUsage
	 * @param iPos 
	 * 
	 * @return String the attribute value
	 */
	public String getGeneralID(final String idUsage, int iPos)
	{
		final VElement v = getChildElementVector(ElementName.GENERALID, null, new JDFAttributeMap(AttributeName.IDUSAGE, idUsage), true, 0, true);
		if (iPos < 0)
			iPos += v.size();
		if (iPos < 0 || iPos >= v.size())
		{
			return null;
		}
		final JDFGeneralID gid = (JDFGeneralID) v.elementAt(iPos);
		return gid.getIDValue();
	}

	/**
	 * Gets a map of all GeneralID key-value Pair lists
	 * if multiple generalIDs with the same IDUsage are specified, each GeneralID is added to the VectorMap
	 * 
	 * 
	 * @return VectorMap<String, JDFGeneralID> the map of lists of attribute values
	 */
	public VectorMap<String, JDFGeneralID> getGeneralIDVectorMap()
	{
		final VElement v = getChildElementVector(ElementName.GENERALID, null);
		if (v.size() == 0)
		{
			return null;
		}
		VectorMap<String, JDFGeneralID> vm = new VectorMap<String, JDFGeneralID>();
		for (KElement e : v)
		{
			JDFGeneralID gid = (JDFGeneralID) e;
			vm.putOne(gid.getIDUsage(), gid);
		}
		return vm;
	}

	/**
	 * Gets a map of all GeneralID key-value Pairs
	 * if multiple generalIDs with the same IDUsage are specified, the last one is added to the map
	 * 
	 * 
	 * @return JDFAttributeMap the map of attribute values
	 */
	public JDFAttributeMap getGeneralIDMap()
	{
		final VElement v = getChildElementVector(ElementName.GENERALID, null);
		if (v.size() == 0)
		{
			return null;
		}
		JDFAttributeMap m = new JDFAttributeMap();
		for (KElement e : v)
		{
			JDFGeneralID gid = (JDFGeneralID) e;
			m.put(gid.getIDUsage(), gid.getIDValue());
		}
		return m;
	}

	/**
	 * @param elementName the elementname with namespace prefix "xyz:abc"
	 * @param nameSpaceURI the namespace of the element "null" is valid if the namespace was specified already above. The method will lookup the namespace for
	 * you. Performance wise its better to add it nevertheless.
	 * @return KElement the appended element or null
	 * @throws JDFException if you tried to append an element into an unspecified namespace
	 */
	@Override
	public synchronized KElement appendElement(final String elementName, final String nameSpaceURI)
	{
		final KElement newChild = appendElementRaw(elementName, nameSpaceURI);
		setDirty(false);
		Document od = getOwnerDocument();
		if (od instanceof DocumentJDFImpl && ((DocumentJDFImpl) od).bInitOnCreate)
			newChild.init();
		return newChild;
	}

	// ////////////////////////////////////////////////////////////////////
	/**
	 * Appends element Comment to the end of 'this'
	 * 
	 * @return the newly created JDFComment
	 */
	public JDFComment appendComment()
	{
		return (JDFComment) appendElement(ElementName.COMMENT, null);
	}

	/**
	 * Gets the iSkip-th element Comment. If doesn't exist, creates it
	 * 
	 * @param iSkip number of elements to skip
	 * @return the newly created JDFComment
	 */
	public JDFComment getCreateComment(final int iSkip)
	{
		return (JDFComment) getCreateElement_KElement(ElementName.COMMENT, null, iSkip);
	}

	/**
	 * Gets the iSkip-th element Comment
	 * 
	 * @param iSkip number of elements to skip
	 * @return JDFComment - the matching element
	 * 
	 * @default getComment(0)
	 */
	public JDFComment getComment(final int iSkip)
	{
		return (JDFComment) getElement(ElementName.COMMENT, null, iSkip);
	}

	/**
	 * Gets the Comment with a give @Name
	 * 
	 * @param _name Comment/@Name
	 * @param index number of elements to skip
	 * @return JDFComment - the matching element
	 * 
	 */
	public JDFComment getComment(final String _name, final int index)
	{
		return (JDFComment) getChildWithAttribute(ElementName.COMMENT, AttributeName.NAME, null, _name, index, true);
	}

	// ////////////////////////////////////////////////////////////////////
	// ////////////////////////////////////////////////////////////////////
	// ////////////////////////////////////////////////////////////////////
	/**
	 * Appends element JDFPreview to the end of 'this'
	 * 
	 * @return the newly created JDFPreview
	 */
	public JDFPreview appendPreview()
	{
		return (JDFPreview) appendElement(ElementName.PREVIEW, null);
	}

	/**
	 * Gets the iSkip-th element JDFPreview. If doesn't exist, creates it
	 * 
	 * @param iSkip number of elements to skip
	 * @return the newly created JDFPreview
	 */
	public JDFPreview getCreatePreview(final int iSkip)
	{
		return (JDFPreview) getCreateElement_KElement(ElementName.PREVIEW, null, iSkip);
	}

	/**
	 * Gets the iSkip-th element Comment
	 * 
	 * @param iSkip number of elements to skip
	 * @return JDFPreview - the matching element
	 * 
	 * @default getPreview(0)
	 */
	public JDFPreview getPreview(final int iSkip)
	{
		return (JDFPreview) getElement(ElementName.PREVIEW, null, iSkip);
	}

	/**
	 * Gets a child with matching attributes, also follows refelements
	 * 
	 * @param nodeName name of the child node to search for
	 * @param attName attribute name to search for
	 * @param nameSpaceURI namespace to search for
	 * @param attVal a special value to search for
	 * @param index if more then one child meets the condition, you can specify the one to return via an index
	 * @param bDirect if true, look only in direct children, else search through all children, grandchildren etc.
	 * @param dataType dataType to be matched
	 * 
	 * @return JDFElement: the element which matches the above conditions
	 * 
	 * @default getChildWithMatchingAttribute(nodeName, attName, null, null, 0, true, EnumAttributeType.Any);
	 */
	public JDFElement getChildWithMatchingAttribute(final String nodeName, final String attName, final String nameSpaceURI, final String attVal, final int index, final boolean bDirect, final AttributeInfo.EnumAttributeType dataType)
	{
		final VElement v = getChildrenByTagName(nodeName, nameSpaceURI, null, bDirect, true, 0);
		final int siz = v.size();
		int n = 0;
		for (int i = 0; i < siz; i++)
		{
			final JDFElement e = (JDFElement) v.elementAt(i);
			if (e.includesMatchingAttribute(attName, attVal, dataType))
			{
				if (n++ == index)
				{
					return e;
				}
			}
		}
		return null;
	}

	/**
	 * GetChildWithAttribute - Get a child with matching attributes
	 * <p>
	 * default: getChildWithAttribute(nodeName, attName, null,attValue, 0, true)
	 * @param nodeName name of the child node to search for
	 * @param attName attribute name to search for
	 * @param nameSpaceURI namespace to search for
	 * @param attVal the attribute value to search for, Wildcard supported ( <code>null</code>)
	 * @param index if more then one child meets the condition, you can specify the one to return via an index
	 * @param bDirect if true, looks only in direct children, else search through all children, grandchildren etc.
	 * @return KElement the element which matches the above conditions
	 */
	@Override
	public KElement getChildWithAttribute(String nodeName, final String attName, String nameSpaceURI, String attVal, final int index, final boolean bDirect)
	{
		KElement kRet = null;
		XMLDocUserData userData = null;

		final boolean bID = AttributeName.ID.equals(attName);
		if (bID && !isWildCard(attVal))
		{
			userData = getXMLDocUserData();
			if (userData != null)
			{
				kRet = userData.getTarget(attVal);
				if (kRet != null && ((bDirect && kRet.getParentNode_KElement() != this) || kRet.getOwnerDocument() != getOwnerDocument()))
				{
					kRet = null; // it is somewhere else, not a child of this!
				}
				if (kRet != null)
				{
					return kRet;
				}
			}
		}

		if (isWildCard(nodeName))
		{
			nodeName = null;
		}
		if (isWildCard(nameSpaceURI))
		{
			nameSpaceURI = null;
		}
		if (isWildCard(attVal))
		{
			attVal = null;
		}

		if (bDirect)
		{ // inlined for performance
			KElement e0 = getFirstChildElement();
			if (e0 != null)
			{
				final boolean bAlwaysFit = nodeName == null && nameSpaceURI == null;
				do
				{
					KElement e = e0;
					if (e instanceof JDFRefElement)
					{
						if (!(this instanceof JDFResourcePool))
						{
							// loops!
							e = ((JDFRefElement) e0).getTarget();
						}
					}

					if (e != null)
					{
						if ((bAlwaysFit || e.fitsName(nodeName, nameSpaceURI)) && (e.includesAttribute(attName, attVal)))
						{
							kRet = e;
						}

						// update ID cache while searching IDs
						if (bID && userData != null)
						{
							final String idVal = e.getAttribute_KElement(AttributeName.ID, null, null);
							if (idVal != null)
							{
								userData.setTarget(e, idVal);
							}
						}
					}

					e0 = e0.getNextSiblingElement();

				}
				while (e0 != null && (kRet == null)); // loop to end if we are
				// filling the cache
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
	 * test whether the attributes described by attName and attVal exist<br< also checking ranges and NMTOKENS etc.
	 * 
	 * @param attName the name of the attribute to look for
	 * @param attVal the value of the attribute to look for; a values of "*" is treated as true if the attribute exists, regardless of its value
	 * @param dataType the dataType to be matched
	 * @tbd allow for regular expressions in attVal
	 * @return true, if such attribute exists
	 * 
	 * @default includesMatchingAttribute(String attName, null, JDFElement.EnumAttributeType.Any)
	 */
	public boolean includesMatchingAttribute(final String attName, final String attVal, final AttributeInfo.EnumAttributeType dataType)
	{
		final String thisVal = getAttribute(attName, null, null);
		if (thisVal == null)
		{
			return false;
		}
		if (isWildCard(attVal))
		{
			return true;
		}
		if (attVal.equals(thisVal))
		{
			return true;
		}
		return AtrInfo.matchesAttribute(attVal, thisVal, dataType);
	}

	/**
	 * Sets attribute Status
	 * 
	 * @param s the status to set the attribute to
	 */
	public void setStatus(final EnumNodeStatus s)
	{
		setAttribute(AttributeName.STATUS, s == null ? null : s.getName(), null);
	}

	/**
	 * gets the value of attribute Status
	 * 
	 * @return EnumNodeStatus
	 */
	public EnumNodeStatus getStatus()
	{
		return EnumNodeStatus.getEnum(getAttribute(AttributeName.STATUS, null, null));
	}

	/**
	 * this is an optimized version of GetDeepElement() which returns a complete list of elements. Here we abort, when we found the first element that fits.
	 * (There is only one element, because the id must be unique)
	 * @param base 
	 * @param attName attribute name
	 * @param id attribute ID value
	 * @param childToExclude here can be specified, if this method should exclude a child-element when searching This is useful, when searching a tree up
	 * @param ud userdata with reference to id cache, if null, no caching
	 * @return KElement the element specified by id and name
	 */
	protected static KElement getDeepElementByID(final KElement base, final String attName, final String id, final KElement childToExclude, final XMLDocUserData ud)
	{
		Attr attr = base.getAttributeNode(attName);
		String attVal = attr != null ? attr.getValue() : null;
		if (attVal != null)
		{
			if (ud != null)
			{
				ud.setTarget(base, attVal);
			}
			if (attVal.equals(id))
			{
				return base; // just found ourselves
			}
		}
		// tree walk children
		KElement childElement = base.getFirstChildElement();

		while (childElement != null)
		{
			if (!childElement.equals(childToExclude))
			{
				final KElement kDeepElement = JDFElement.getDeepElementByID(childElement, attName, id, childToExclude, ud);
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
	* Gets inter-resource linked resource IDs
	* 
	* @param vDoneRefs (use null as default value)
	* @param bRecurse if true, return recursively linked IDs as well
	* @return the HashSet of all refElements
	* 
	* @default getAllRefs(null, false)
	*/
	public HashSet<JDFElement> getAllRefs(HashSet<JDFElement> vDoneRefs, final boolean bRecurse)
	{

		if (vDoneRefs.contains(this))
		{
			return vDoneRefs;
		}

		final VElement v = getChildElementVector_KElement(null, null, null, true, 0); // grabemall

		final int size = v.size();
		for (int i = 0; i < size; i++)
		{
			final KElement e = v.elementAt(i);
			if (e instanceof JDFRefElement)
			{
				final JDFRefElement ref = (JDFRefElement) e;
				if (!vDoneRefs.contains(ref))
				{
					vDoneRefs.add(ref);
					if (bRecurse)
					{
						final JDFResource r = ref.getTarget();
						if (r != null)
						{
							vDoneRefs = r.getAllRefs(vDoneRefs, bRecurse);
						}
					}
				}
			}
			else if (e instanceof JDFElement)
			{ // recurse tree
				vDoneRefs = ((JDFElement) e).getAllRefs(vDoneRefs, bRecurse);
			}
		}
		return vDoneRefs;
	}

	/**
	 * check whether this matches a simple xpath - note that references are NOT followed in case a node name is replaced with a "*"
	 * 
	 * @param path xpath to match
	 * @return boolean true, if this matches the given xpath
	 */
	@Override
	public boolean matchesPath(final String path, final boolean bFollowRefs)
	{
		if (path == null)
		{
			return true;
		}
		final VString v = StringUtil.tokenize(path, "/", false);
		KElement e = this;
		KElement eLast = null;
		for (int i = v.size() - 1; i >= 0; i--)
		{
			if (e == null)
			{
				return false;
			}
			final String locName = e.getLocalName();
			if (!e.matchesPathName(v.stringAt(i)))
			{
				if (bFollowRefs && eLast != null && locName.equals(ElementName.RESOURCEPOOL))
				{ // now look for a refelement that points at this
					if (eLast instanceof JDFResource)
					{
						final JDFResource r = (JDFResource) eLast;
						final VElement vRefs = r.getLinks(r.getRefString(), null);
						if (vRefs != null)
						{
							String subPath = v.stringAt(0);
							for (int k = 1; k <= i + 1; k++)
							{
								subPath += "/" + v.stringAt(k);
							}
							subPath += "Ref";
							for (int j = 0; j < vRefs.size(); j++)
							{
								final KElement eRef = vRefs.item(j);
								final boolean b = eRef.matchesPath(subPath, bFollowRefs);
								if (b)
								{
									return true;
								}
							}
						}

					}
				}
				// xpath is xpath -lets try not to be too smart implicitly
				// if(eLast!=null)
				// {
				// if(eLast instanceof JDFResource)
				// {
				// if(locName.equals(eLast.getLocalName())){
				// e=e.getParentNode_KElement();
				// i++; // undo i--
				// continue;
				// }
				// }
				// }
				return false;
			}
			eLast = e;
			e = e.getParentNode_KElement();
		}
		if (path.startsWith("/"))
		{
			return e == null || path.startsWith("//"); // must be root
		}
		return true; // any location
	}

	/**
	 * gets a reasonable value for an attribute
	 * @param ke
	 * @param attName
	 * @return
	 */
	static public String getValueForNewAttribute(final KElement ke, final String attName)
	{

		if (!(ke instanceof JDFElement))
			return "New Value";
		JDFElement e = (JDFElement) ke;
		// return the default if it exists
		final JDFAttributeMap map = (e != null) ? null : e.getDefaultAttributeMap();
		if (map != null && map.containsKey(attName))
		{
			return map.get(attName);
		}

		if (attName.equals("ID"))
		{
			return "ID" + KElement.uniqueID(0);
		}

		if (attName.equals("JobID"))
		{
			return "J" + KElement.uniqueID(0);
		}

		if (attName.equals("JobPartID") && (e instanceof JDFElement))
		{
			return e.generateDotID("JobPartID", null);
		}

		if (attName.equals("Status") && (e instanceof JDFNode))
		{
			return "Waiting";
		}

		if (attName.equals("Status") && (e instanceof JDFResource))
		{
			return "Unavailable";
		}

		if (attName.equals("Type") && (e instanceof JDFNode))
		{
			return "Product";
		}

		if (attName.equals("Type") && (e instanceof JDFMessage))
		{
			return "Status";
		}

		if (attName.equals("TimeStamp"))
		{
			return new JDFDate().getDateTimeISO();
		}

		if (attName.equals("ComponentType"))
		{
			return "PartialProduct";
		}

		if (attName.equals("PreviewType"))
		{
			return "Separation";
		}

		if (e != null)
		{
			final EnumAttributeType attyp = e.getAtrType(attName);
			if (attyp != null)
			{
				if (EnumAttributeType.boolean_.equals(attyp))
				{
					return "true";
				}

				if (EnumAttributeType.CMYKColor.equals(attyp))
				{
					return "0 0 0 1";
				}

				if (EnumAttributeType.RGBColor.equals(attyp))
				{
					return "1 1 1";
				}

				if (EnumAttributeType.dateTime.equals(attyp) || EnumAttributeType.DateTimeRange.equals(attyp) || EnumAttributeType.DateTimeRangeList.equals(attyp))
				{
					return new JDFDate().getDateTimeISO();
				}

				if (EnumAttributeType.double_.equals(attyp))
				{
					return "0.0";
				}

				if (EnumAttributeType.duration.equals(attyp) || EnumAttributeType.DurationRange.equals(attyp) || EnumAttributeType.DurationRangeList.equals(attyp))
				{
					return "PT1H";
				}

				// TODO evaluate durations
				// if(EnumAttributeType.enumeration.equals(attyp) ||
				// EnumAttributeType.enumerations.equals(attyp))
				// return "";
				if (EnumAttributeType.integer.equals(attyp) || EnumAttributeType.IntegerRange.equals(attyp) || EnumAttributeType.IntegerRangeList.equals(attyp)
						|| EnumAttributeType.IntegerList.equals(attyp))
				{
					return "0";
				}

				if (EnumAttributeType.JDFJMFVersion.equals(attyp))
				{
					return "1.4";
				}

				if (EnumAttributeType.matrix.equals(attyp))
				{
					return "1 0 0 1 0 0";
				}

				if (EnumAttributeType.XYPair.equals(attyp) || EnumAttributeType.XYPairRange.equals(attyp) || EnumAttributeType.XYPairRangeList.equals(attyp))
				{
					return "0 0";
				}
			}
		}

		return "New Value";
	}

	/**
	 * returns the jdf doc referenced by url
	 * @param url 
	 *  
	 * 
	 * @return the document
	 */
	protected JDFDoc getURLDoc(String url)
	{
		URLReader r = new URLReader(url, getOwnerDocument_JDFElement());
		return r.getJDFDoc();
	}

	/**
	 * get the input stream that reads from URL
	 * @param url 
	 * 
	 * @return InputStream the input stream that the url points to, null if the url is inaccessible
	 */
	protected InputStream getURLInputStream(String url)
	{
		URLReader r = new URLReader(url, getOwnerDocument_JDFElement());
		return r.getURLInputStream();
	}
}
