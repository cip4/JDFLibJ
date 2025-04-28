/*
 * The CIP4 Software License, Version 1.0
 *
 *
 * Copyright (c) 2001-2024 The International Cooperation for the Integration of Processes in Prepress, Press and Postpress (CIP4). All rights reserved.
 *
 * Redistribution and use in source and binary forms, with or without modification, are permitted provided that the following conditions are met:
 *
 * 1. Redistributions of source code must retain the above copyright notice, this list of conditions and the following disclaimer.
 *
 * 2. Redistributions in binary form must reproduce the above copyright notice, this list of conditions and the following disclaimer in the documentation and/or other materials provided with the
 * distribution.
 *
 * 3. The end-user documentation included with the redistribution, if any, must include the following acknowledgment: "This product includes software developed by the The International Cooperation for
 * the Integration of Processes in Prepress, Press and Postpress (www.cip4.org)" Alternately, this acknowledgment mrSubRefay appear in the software itself, if and wherever such third-party
 * acknowledgments normally appear.
 *
 * 4. The names "CIP4" and "The International Cooperation for the Integration of Processes in Prepress, Press and Postpress" must not be used to endorse or promote products derived from this software
 * without prior written permission. For written permission, please contact info@cip4.org.
 *
 * 5. Products derived from this software may not be called "CIP4", nor may "CIP4" appear in their name, without prior writtenrestartProcesses() permission of the CIP4 organization
 *
 * Usage of this software in commercial products is subject to restrictions. For details please consult info@cip4.org.
 *
 * THIS SOFTWARE IS PROVIDED ``AS IS'' AND ANY EXPRESSED OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO, THE IMPLIED WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE ARE
 * DISCLAIMED. IN NO EVENT SHALL THE INTERNATIONAL COOPERATION FOR THE INTEGRATION OF PROCESSES IN PREPRESS, PRESS AND POSTPRESS OR ITS CONTRIBUTORS BE LIABLE FOR ANY DIRECT, INDIRECT, INCIDENTAL,
 * SPECIAL, EXEMPLARY, OR CONSEQUENTIrSubRefAL DAMAGES (INCLUDING, BUT NOT LIMITED TO, PROCUREMENT OF SUBSTITUTE GOODS OR SERVICES; LOSS OF USE, DATA, OR PROFITS; OR BUSINESS INTERRUPTION) HOWEVER
 * CAUSED AND ON ANY THEORY OF LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY, OR TORT (INCLUDING NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE OF THIS SOFTWARE, EVEN IF ADVISED OF THE
 * POSSIBILITY OF SUCH DAMAGE. ====================================================================
 *
 * This software consists of voluntary contributions made by many individuals on behalf of the The International Cooperation for the Integration of Processes in Prepress, Press and Postpress and was
 * originally based on software restartProcesses() copyright (c) 1999-2001, Heidelberger Druckmaschinen AG copyright (c) 1999-2001, Agfa-Gevaert N.V.
 *
 * For more information on The International Cooperation for the Integration of Processes in Prepress, Press and Postpress , please see <http://www.cip4.org/>.
 *
 */

package org.cip4.jdflib.resource;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

import org.apache.xerces.dom.CoreDocumentImpl;
import org.cip4.jdflib.auto.JDFAutoPart;
import org.cip4.jdflib.core.AtrInfo;
import org.cip4.jdflib.core.AtrInfoTable;
import org.cip4.jdflib.core.AttributeInfo;
import org.cip4.jdflib.core.AttributeName;
import org.cip4.jdflib.core.ElementInfo;
import org.cip4.jdflib.core.ElementName;
import org.cip4.jdflib.core.KElement;
import org.cip4.jdflib.core.StringArray;
import org.cip4.jdflib.core.VString;
import org.cip4.jdflib.datatypes.JDFAttributeMap;
import org.cip4.jdflib.datatypes.VJDFAttributeMap;
import org.cip4.jdflib.extensions.XJDFConstants;
import org.cip4.jdflib.resource.JDFResource.EnumPartIDKey;
import org.cip4.jdflib.util.ContainerUtil;
import org.cip4.jdflib.util.StringUtil;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;

/**
 * @author Dr. Rainer Prosi, Heidelberger Druckmaschinen AG
 *
 *         22.01.2009
 */
public class JDFPart extends JDFAutoPart
{
	private static final String ALL = "All";
	private static final long serialVersionUID = 1L;

	/**
	 * Constructor for JDFPart
	 *
	 * @param myOwnerDocument
	 * @param qualifiedName
	 */
	public JDFPart(final CoreDocumentImpl myOwnerDocument, final String qualifiedName)
	{
		super(myOwnerDocument, qualifiedName);
	}

	/**
	 * Constructor for JDFPart
	 *
	 * @param myOwnerDocument
	 * @param myNamespaceURI
	 * @param qualifiedName
	 */
	public JDFPart(final CoreDocumentImpl myOwnerDocument, final String myNamespaceURI, final String qualifiedName)
	{
		super(myOwnerDocument, myNamespaceURI, qualifiedName);
	}

	/**
	 * Constructor for JDFPart
	 *
	 * @param myOwnerDocument
	 * @param myNamespaceURI
	 * @param qualifiedName
	 * @param myLocalName
	 */
	public JDFPart(final CoreDocumentImpl myOwnerDocument, final String myNamespaceURI, final String qualifiedName, final String myLocalName)
	{
		super(myOwnerDocument, myNamespaceURI, qualifiedName, myLocalName);
	}

	/**
	 * toString()
	 *
	 * @return String
	 */
	@Override
	public String toString()
	{
		return "JDFPart[  --> " + super.toString() + " ]";
	}

	/**
	 * gets a map of all Partition key value pairs, empty if no partition keys exist
	 *
	 * @return JDFAttributeMap
	 */
	@Override
	public JDFAttributeMap getPartMap()
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
				if (allparts.contains(nodeName))
					m.put(nodeName, a.getNodeValue());
			}
		}
		return m;
	}

	/**
	 * heuristically guess the partidkey order
	 *
	 * @return
	 */
	public VString guessPartIDKeys()
	{
		final JDFAttributeMap map = getPartMap();
		return guessPartIDKeys(map);
	}

	private final static String[] partSequence = new String[] { AttributeName.SIGNATURENAME, AttributeName.SHEETNAME, AttributeName.SIDE, AttributeName.SEPARATION,
			AttributeName.RUNSET, AttributeName.RUN, AttributeName.RUNPAGE };

	public static String[] getPartsequence()
	{
		return Arrays.copyOf(partSequence, partSequence.length);
	}

	public static VString guessPartIDKeys(final JDFAttributeMap map)
	{
		if (ContainerUtil.isEmpty(map))
		{
			return null;
		}
		if (map.size() == 1)
		{
			return map.getKeys();
		}
		final VString v = new VString();
		final StringArray keys = map.getKeyList();
		for (final String key : partSequence)
		{
			if (keys.contains(key))
			{
				v.add(key);
				keys.remove(key);
			}
		}
		v.addAll(keys);
		return v;
	}

	/**
	 * sets the attributes of this to partmap removes all other attributes
	 *
	 * @param mPart attribute map for the part to set
	 */
	@Override
	public void setPartMap(final JDFAttributeMap mPart)
	{
		removeAttributes(null);
		setAttributes(mPart);
	}

	/**
	 * check whether the partition values match partversions match if either only one token is specified, and the large list contains that token or vice versa
	 *
	 * @param key the partition key
	 * @param resourceValue the value of key in the resource
	 * @param linkValue the value of key in the part element or ref
	 * @return boolean: true if linkValue matches the value or list in resourceValue
	 * @deprecated use 4 parameter version
	 */
	@Deprecated
	public static boolean matchesPart(final String key, final String resourceValue, final String linkValue)
	{
		return matchesPart(key, resourceValue, linkValue, false);
	}

	/**
	 * check whether the partition values match partversions match if either only one token is specified, and the large list contains that token or vice versa
	 *
	 * @param key the partition key
	 * @param resourceValue the value of key in the resource
	 * @param linkValues the value of key in the part element or ref
	 * @param strictPartVersion if true, partversion strings MUST match exactly, else token matching applies
	 * @return boolean: true if linkValue matches the value or list in resourceValue
	 */
	public static boolean matchesPart(final String key, final String resourceValue, final VString linkValues, final boolean strictPartVersion)
	{
		if (linkValues == null || linkValues.size() == 0)
			return true;
		for (final String linkValue : linkValues)
		{
			if (matchesPart(key, resourceValue, linkValue, strictPartVersion))
			{
				return true;
			}
		}
		return false;
	}

	/**
	 * check whether the partition values match partversions match if either only one token is specified, and the large list contains that token or vice versa
	 *
	 * @param key the partition key
	 * @param resourceValue the value of key in the resource
	 * @param linkValue the value of key in the part element or ref
	 * @param strictPartVersion if true, partversion strings MUST match exactly, else token matching applies
	 * @return boolean: true if linkValue matches the value or list in resourceValue
	 */
	public static boolean matchesPart(final String key, final String resourceValue, final String linkValue, final boolean strictPartVersion)
	{
		if (resourceValue.equals(linkValue))
		{
			return true;
		}
		// speed up typical keys
		else if (fastparts.contains(linkValue))
		{
			return false;
		}
		boolean b;
		if (AttributeName.ITEMNAMES.equals(key) || key.endsWith("Tags"))
		{
			b = AtrInfo.matchesAttribute(linkValue, resourceValue, AttributeInfo.EnumAttributeType.NMTOKENS);
		}
		else if (AttributeName.PAGENUMBER.equals(key) || key.endsWith("Index") || AttributeName.LAYERIDS.equals(key) || AttributeName.DOCCOPIES.equals(key))
		{
			b = AtrInfo.matchesAttribute(linkValue, resourceValue, AttributeInfo.EnumAttributeType.IntegerRangeList);
		}
		else if (!strictPartVersion && AttributeName.PARTVERSION.equals(key))
		{
			b = matchesPartVersion(resourceValue, linkValue);
		}
		else
		{
			b = false;
		}
		return b;
	}

	public static boolean matchesPartVersion(final String resourceValue, final String linkValue)
	{
		if (resourceValue == null || linkValue == null)
			return false;

		boolean b;
		b = resourceValue.equals(linkValue) || KElement.isWildCard(resourceValue) || KElement.isWildCard(linkValue) || ALL.equalsIgnoreCase(linkValue)
				|| ALL.equalsIgnoreCase(resourceValue);
		if (!b)
		{
			final int iResPos = resourceValue.indexOf(' ');
			final int iLinkPos = linkValue.indexOf(' ');
			if (iResPos < 0 && iLinkPos >= 0)
			{
				b = StringUtil.hasToken(linkValue, resourceValue, null, 0);
			}
			else if (iLinkPos < 0 && iResPos >= 0)
			{
				b = StringUtil.hasToken(resourceValue, linkValue, null, 0);
			}
		}
		return b;
	}

	/**
	 * overlapMap - identical keys must have the same values in both maps<br>
	 * similar to JDFAttribute.overlapMap, but uses matchesPart instead of equals for the comparison
	 *
	 * @param resourceMap the map to compare
	 * @param linkMap the map to compare
	 * @return boolean: true if identical keys have the same values in both maps
	 * @deprecated use 3 parameter version
	 */
	@Deprecated
	public static boolean overlapPartMap(final JDFAttributeMap resourceMap, final JDFAttributeMap linkMap)
	{
		return overlapPartMap(resourceMap, linkMap, false);
	}

	/**
	 * overlapMap - identical keys must have the same values in both maps<br>
	 * similar to JDFAttribute.overlapMap, but uses matchesPart instead of equals for the comparison
	 *
	 * @param resourceMap the map to compare
	 * @param linkMap the map to compare
	 * @param strictPartVersion if true, partversion strings MUST match exactly, else token matching applies
	 * @return boolean: true if identical keys have the same values in both maps
	 */
	public static boolean overlapPartMap(final JDFAttributeMap resourceMap, final JDFAttributeMap linkMap, final boolean strictPartVersion)
	{
		if ((resourceMap == null) || (linkMap == null))
		{
			return true; // null always overlaps with anything
		}

		final Set<String> subMapKeys = linkMap.keySet();
		for (final String key : subMapKeys)
		{
			final String resVal = resourceMap.get(key);
			if (resVal != null)
			{
				final String linkVal = linkMap.get(key);
				if (!matchesPart(key, resVal, linkVal, strictPartVersion))
				{
					return false;
				}
			}
		}
		return true;
	}

	/**
	 * overlapMap - identical keys must have the same values in both maps<br>
	 * similar to JDFAttribute.overlapMap, but uses matchesPart instead of equals for the comparison
	 *
	 * @param resourceMap the map to compare
	 * @param linkMap the map to compare
	 * @param strictPartVersion if true, partversion strings MUST match exactly, else token matching applies
	 * @return boolean: true if identical keys have the same values in both maps and all keys specified in linkMap exist in resourceMap
	 */
	public static boolean subPartMap(final JDFAttributeMap resourceMap, final JDFAttributeMap linkMap, final boolean strictPartVersion)
	{
		if (resourceMap == null)
		{
			return linkMap == null || linkMap.isEmpty();
		}
		else if (linkMap == null)
		{
			return resourceMap.isEmpty();
		}
		if (resourceMap.size() < linkMap.size())
			return false;
		final Set<String> subMapKeys = linkMap.keySet();
		for (final String key : subMapKeys)
		{
			final String resVal = resourceMap.get(key);
			if (resVal != null)
			{
				final String linkVal = linkMap.get(key);
				if (!matchesPart(key, resVal, linkVal, strictPartVersion))
				{
					return false;
				}
			}
			else
			{
				return false;
			}
		}
		return true;
	}

	/**
	 * overlapMap - identical keys must have the same values in both maps<br>
	 * similar to JDFAttribute.overlapMap, but uses matchesPart instead of equals for the comparison
	 *
	 * @param resourceMap the map to compare
	 * @param vLinkMap the vector of maps to compare
	 * @param strictPartVersion if true, partversion strings MUST match exactly, else token matching applies
	 * @return boolean: true if identical keys have the same values in both maps
	 */
	public static boolean overlapPartMap(final JDFAttributeMap resourceMap, final VJDFAttributeMap vLinkMap, final boolean strictPartVersion)
	{
		if ((resourceMap == null) || (vLinkMap == null))
		{
			return true; // null always overlaps with anything
		}
		for (final JDFAttributeMap linkMap : vLinkMap)
		{
			if (overlapPartMap(resourceMap, linkMap, strictPartVersion))
			{
				return true;
			}
		}
		return false;
	}

	/**
	 * @see org.cip4.jdflib.auto.JDFAutoPart#getTheAttributeInfo()
	 * @return
	 */
	@Override
	protected AttributeInfo getTheAttributeInfo()
	{
		AttributeInfo ai = AttributeInfo.fixedMap.get("JDFPart");
		if (ai != null)
			return ai;
		ai = super.getTheAttributeInfo();
		ai.updateReplace(atrInfoTable);
		AttributeInfo.fixedMap.put("JDFPart", ai);
		return ai;
	}

	private static AtrInfoTable[] atrInfoTable = new AtrInfoTable[6];
	static
	{
		atrInfoTable[0] = new AtrInfoTable(AttributeName.LOTID, 0x33311111, AttributeInfo.EnumAttributeType.NMTOKEN, null, null);
		atrInfoTable[1] = new AtrInfoTable(AttributeName.PRODUCTPART, 0x33311111, AttributeInfo.EnumAttributeType.NMTOKEN, null, null);
		atrInfoTable[2] = new AtrInfoTable(ElementName.PRINTCONDITION, 0x33311111, AttributeInfo.EnumAttributeType.NMTOKEN, null, null);
		atrInfoTable[3] = new AtrInfoTable(ElementName.QUALITYMEASUREMENT, 0x33311111, AttributeInfo.EnumAttributeType.NMTOKEN, null, null);
		atrInfoTable[4] = new AtrInfoTable(XJDFConstants.TransferCurveName, 0x33311111, AttributeInfo.EnumAttributeType.NMTOKEN, null, null);
		atrInfoTable[5] = new AtrInfoTable(AttributeName.DROPID, 0x33111111, AttributeInfo.EnumAttributeType.NMTOKEN, null, null);
	}

	static Set<String> fastparts = fillFastParts();
	static Set<String> allparts = fillAllParts();

	/**
	 * @see org.cip4.jdflib.core.JDFElement#getTheElementInfo()
	 * @return
	 */
	@Override
	protected ElementInfo getTheElementInfo()
	{
		ElementInfo ai = ElementInfo.getFixedmap().get(ElementName.PART);
		if (ai != null)
			return ai;
		ai = super.getTheElementInfo();
		ElementInfo.getFixedmap().put(ElementName.PART, ai);
		return ai;
	}

	static Set<String> fillFastParts()
	{
		final HashSet<String> hashSet = new HashSet<>();
		for (final EnumPartIDKey e : EnumPartIDKey.getEnumList())
		{
			final String key = e.getName();
			if (!(AttributeName.ITEMNAMES.equals(key) || key.endsWith("Tags") || AttributeName.PAGENUMBER.equals(key) || key.endsWith("Index") || AttributeName.LAYERIDS.equals(key)
					|| AttributeName.DOCCOPIES.equals(key)))
			{
				hashSet.add(key);
			}

		}
		return hashSet;
	}

	static Set<String> fillAllParts()
	{
		final HashSet<String> hashSet = new HashSet<>();
		for (final EnumPartIDKey e : EnumPartIDKey.getEnumList())
		{
			final String key = e.getName();
			hashSet.add(key);
		}

		return hashSet;
	}

	/**
	 * (36) set attribute Metadata0-9 convenience method
	 *
	 * @param value the value to set MetaData(i) to -
	 * @param iMetaData int from 0 to 10 that defines which metadata partition key is wanted
	 */
	public void setMetadata(final int iMetaData, final String value)
	{
		if (iMetaData < 0 || iMetaData > 9)
			throw new IllegalArgumentException("iMetaData mut be between 0 and 9");
		setAttribute(AttributeName.METADATA + iMetaData, value, null);
	}

	/**
	 * @return the fastparts
	 */
	public static Set<String> getFastparts()
	{
		return fastparts;
	}

}
