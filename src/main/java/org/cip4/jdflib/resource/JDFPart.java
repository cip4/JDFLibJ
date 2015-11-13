/*
 * The CIP4 Software License, Version 1.0
 *
 *
 * Copyright (c) 2001-2015 The International Cooperation for the Integration of
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
 *    Alternately, this acknowledgment mrSubRefay appear in the software itself,
 *    if and wherever such third-party acknowledgments normally appear.
 *
 * 4. The names "CIP4" and "The International Cooperation for the Integration of
 *    Processes in  Prepress, Press and Postpress" must
 *    not be used to endorse or promote products derived from this
 *    software without prior written permission. For written
 *    permission, please contact info@cip4.org.
 *
 * 5. Products derived from this software may not be called "CIP4",
 *    nor may "CIP4" appear in their name, without prior writtenrestartProcesses()
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
 * SPECIAL, EXEMPLARY, OR CONSEQUENTIrSubRefAL DAMAGES (INCLUDING, BUT NOT
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
 * originally based on software restartProcesses()
 * copyright (c) 1999-2001, Heidelberger Druckmaschinen AG
 * copyright (c) 1999-2001, Agfa-Gevaert N.V.
 *
 * For more information on The International Cooperation for the
 * Integration of Processes in  Prepress, Press and Postpress , please see
 * <http://www.cip4.org/>.
 *
 */
/**
 *========================================================================== class JDFPart extends JDFAutoPart
 * created 2001-09-06T10:02:57GMT+02:00 ==========================================================================
 *          @COPYRIGHT Heidelberger Druckmaschinen AG, 1999-2001 ALL RIGHTS RESERVED
 * Warning! very preliminary test version. Interface subject to change without prior notice! Revision history:   ...
 */

package org.cip4.jdflib.resource;

import java.util.Iterator;
import java.util.Set;

import org.apache.xerces.dom.CoreDocumentImpl;
import org.cip4.jdflib.auto.JDFAutoPart;
import org.cip4.jdflib.core.AtrInfo;
import org.cip4.jdflib.core.AttributeInfo;
import org.cip4.jdflib.core.AttributeName;
import org.cip4.jdflib.core.ElementInfo;
import org.cip4.jdflib.core.VString;
import org.cip4.jdflib.datatypes.JDFAttributeMap;
import org.cip4.jdflib.datatypes.VJDFAttributeMap;
import org.cip4.jdflib.resource.JDFResource.EnumPartIDKey;

/**
 * @author Dr. Rainer Prosi, Heidelberger Druckmaschinen AG
 * 
 * 22.01.2009
 */
public class JDFPart extends JDFAutoPart
{
	private static final long serialVersionUID = 1L;

	/**
	 * Constructor for JDFPart
	 * @param myOwnerDocument
	 * @param qualifiedName
	 */
	public JDFPart(final CoreDocumentImpl myOwnerDocument, final String qualifiedName)
	{
		super(myOwnerDocument, qualifiedName);
	}

	/**
	 * Constructor for JDFPart
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
	 * @return String
	 */
	@Override
	public String toString()
	{
		return "JDFPart[  --> " + super.toString() + " ]";
	}

	/**
	 * gets a map of all Partition key value pairs, empty if no partition keys exist
	 * @return JDFAttributeMap
	 */
	@Override
	public JDFAttributeMap getPartMap()
	{
		final JDFAttributeMap am = getAttributeMap();
		final Iterator<String> it = am.getKeyIterator();
		final JDFAttributeMap retMap = new JDFAttributeMap();
		while (it.hasNext())
		{
			final String key = it.next();
			if (EnumPartIDKey.getEnum(key) != null)
			{
				retMap.put(key, am.get(key));
			}
		}
		return retMap;
	}

	/**
	 * heuristically guess the partidkey order
	 * @return
	 */
	public VString guessPartIDKeys()
	{
		final JDFAttributeMap map = getPartMap();
		return guessPartIDKeys(map);
	}

	public static VString guessPartIDKeys(final JDFAttributeMap map)
	{
		if (map == null || map.size() == 0)
		{
			return null;
		}
		if (map.size() == 1)
		{
			return map.getKeys();
		}
		final VString v = new VString();
		final VString keys = map.getKeys();
		if (keys.contains(AttributeName.SIGNATURENAME))
		{
			v.add(AttributeName.SIGNATURENAME);
			keys.remove(AttributeName.SIGNATURENAME);
		}
		if (keys.contains(AttributeName.SHEETNAME))
		{
			v.add(AttributeName.SHEETNAME);
			keys.remove(AttributeName.SHEETNAME);
		}
		if (keys.contains(AttributeName.SIDE))
		{
			v.add(AttributeName.SIDE);
			keys.remove(AttributeName.SIDE);
		}
		if (keys.contains(AttributeName.SEPARATION))
		{
			v.add(AttributeName.SEPARATION);
			keys.remove(AttributeName.SEPARATION);
		}
		v.addAll(keys);
		return v;
	}

	/**
	 * sets the attributes of this to partmap removes all other attributes
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
	 * @param key the partition key
	 * @param resourceValue the value of key in the resource
	 * @param linkValues the value of key in the part element or ref
	 * @param strictPartVersion if true, partversion strings MUST match exactly, else token matching applies
	 * @return boolean: true if linkValue matches the value or list in resourceValue
	 */
	public static boolean matchesPart(final String key, final String resourceValue, final VString linkValues, boolean strictPartVersion)
	{
		if (linkValues == null || linkValues.size() == 0)
			return true;
		for (String linkValue : linkValues)
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
	 * @param key the partition key
	 * @param resourceValue the value of key in the resource
	 * @param linkValue the value of key in the part element or ref
	 * @param strictPartVersion if true, partversion strings MUST match exactly, else token matching applies
	 * @return boolean: true if linkValue matches the value or list in resourceValue
	 */
	public static boolean matchesPart(final String key, final String resourceValue, final String linkValue, boolean strictPartVersion)
	{
		if (resourceValue.equals(linkValue))
		{
			return true;
		}
		// speed up typical keys
		else if (AttributeName.SIGNATURENAME.equals(key) || AttributeName.SHEETNAME.equals(key) || AttributeName.SIDE.equals(key))
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
			b = resourceValue.equals(linkValue);
			if (!b)
			{
				int iResPos = resourceValue.indexOf(' ');
				int iLinkPos = linkValue.indexOf(' ');
				if (iResPos < 0 && iLinkPos >= 0)
				{
					b = AtrInfo.matchesAttribute(resourceValue, linkValue, AttributeInfo.EnumAttributeType.NMTOKENS);
				}
				else if (iLinkPos < 0 && iResPos >= 0)
				{
					b = AtrInfo.matchesAttribute(linkValue, resourceValue, AttributeInfo.EnumAttributeType.NMTOKENS);
				}
			}
		}
		else
		{
			b = false;
		}
		return b;
	}

	/**
	 * overlapMap - identical keys must have the same values in both maps<br>
	 * similar to JDFAttribute.overlapMap, but uses matchesPart instead of equals for the comparison
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
	 * @param resourceMap the map to compare
	 * @param linkMap the map to compare
	 * @param strictPartVersion if true, partversion strings MUST match exactly, else token matching applies
	 * @return boolean: true if identical keys have the same values in both maps
	 */
	public static boolean overlapPartMap(final JDFAttributeMap resourceMap, final JDFAttributeMap linkMap, boolean strictPartVersion)
	{
		if ((resourceMap == null) || (linkMap == null))
		{
			return true; // null always overlaps with anything
		}

		final Set<String> subMapKeys = linkMap.keySet();
		for (String key : subMapKeys)
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
	* @param resourceMap the map to compare
	* @param vLinkMap the vector of maps to compare
	* @param strictPartVersion if true, partversion strings MUST match exactly, else token matching applies
	* @return boolean: true if identical keys have the same values in both maps
	*/
	public static boolean overlapPartMap(final JDFAttributeMap resourceMap, final VJDFAttributeMap vLinkMap, boolean strictPartVersion)
	{
		if ((resourceMap == null) || (vLinkMap == null))
		{
			return true; // null always overlaps with anything
		}
		for (JDFAttributeMap linkMap : vLinkMap)
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
		AttributeInfo.fixedMap.put("JDFPart", ai);
		return ai;
	}

	/**
	 * @see org.cip4.jdflib.core.JDFElement#getTheElementInfo()
	 * @return
	*/
	@Override
	protected ElementInfo getTheElementInfo()
	{
		ElementInfo ai = ElementInfo.fixedMap.get("JDFPart");
		if (ai != null)
			return ai;
		ai = super.getTheElementInfo();
		ElementInfo.fixedMap.put("JDFPart", ai);
		return ai;
	}

	/**
	 * (36) set attribute Metadata0-9 convenience method
	 * @param value the value to set MetaData(i) to - 
	 * @param iMetaData int from 0 to 10 that defines which metadata partition key is wanted
	 */
	public void setMetadata(int iMetaData, String value)
	{
		if (iMetaData < 0 || iMetaData > 9)
			throw new IllegalArgumentException("iMetaData mut be between 0 and 9");
		setAttribute(AttributeName.METADATA + iMetaData, value, null);
	}

} // class JDFPart
