/**
 *
 *
 * The CIP4 Software License, Version 1.0
 *
 *
 * Copyright (c) 2001-2010 The International Cooperation for the Integration of
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
 * 09022005 VF initial version
 */
package org.cip4.jdflib.core;

import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.enums.ValuedEnum;
import org.cip4.jdflib.core.AttributeInfo.EnumAttributeValidity;
import org.cip4.jdflib.core.JDFElement.EnumVersion;

/**
 * @author MatternK
 * @See EnumElementValidity below for current list of validity value
 * 
 *      0 Unknown UNKNOWN); 1 None NONE); 2 Required REQUIRED); 3 Optional OPTIONAL); 4 Deprecated DEPRECATED); 5
 *      SingleRequired SINGLEREQUIRED); 6 SingleOptional SINGLEOPTIONAL); 7 SingleDeprecated SINGLEDEPRECATED); 8 Dummy
 *      DUMMY);
 */
public class ElementInfo
{

	/**
	 * 
	 */
	public static HashMap<String, ElementInfo> fixedMap = new HashMap<String, ElementInfo>();

	HashMap<String, ElemInfo> elementInfoTable = new HashMap<String, ElemInfo>();
	private EnumVersion version = null;

	/**
	 * Constructor
	 * 
	 * @param elemInfo_super elemInfo_super: corresponding element info of super; if null: start from scratch, otherwise
	 *            initialize from other ElementInfo
	 * @param elemInfo_own [] elemInfo_own: table with element-specific element info
	 */
	public ElementInfo(ElementInfo elemInfo_super, ElemInfoTable[] elemInfo_own)
	{
		// use ElementInfo of super as a starting point
		if (elemInfo_super != null)
		{
			elementInfoTable = new HashMap<String, ElemInfo>(elemInfo_super.elementInfoTable);
			version = elemInfo_super.version;
		}

		// fill table with the element info specific to this element type (if
		// any)
		updateAdd(elemInfo_own);

		// now all schema-based knowledge should be in the element info table
	}

	/**
	 * Updater
	 * 
	 * @param elemInfo_update [] elemInfo_update: table with element-specific attribute info
	 * @return 
	 */
	public ElementInfo updateAdd(ElemInfoTable elemInfo_update)
	{
		if (elemInfo_update != null)
		{
			if (!elementInfoTable.containsKey(elemInfo_update.getElementName()))
			{
				elementInfoTable.put(elemInfo_update.getElementName(), new ElemInfo(elemInfo_update.getValidityStatus()));
			}
			else
			{
				// complain about duplicate element name
			}
		}
		return this;
	}

	/**
	 * Updater
	 * 
	 * @param elemInfo_update [] elemInfo_update: table with element-specific attribute info
	 * @return 
	 */
	public ElementInfo updateAdd(ElemInfoTable[] elemInfo_update)
	{
		if (elemInfo_update != null)
		{
			for (int i = 0; i < elemInfo_update.length; i++)
			{
				if (elemInfo_update[i] != null)
				{
					if (!elementInfoTable.containsKey(elemInfo_update[i].getElementName()))
					{
						elementInfoTable.put(elemInfo_update[i].getElementName(), new ElemInfo(elemInfo_update[i].getValidityStatus()));
					}
					else
					{
						// complain about duplicate element name
					}
				}
			}
		}
		return this;
	}

	/**
	 * Updater
	 * 
	 * @param elemInfo_update : table with element-specific attribute info
	 * @return 
	 */
	public ElementInfo updateRemove(ElemInfoTable elemInfo_update)
	{
		if (elemInfo_update != null)
		{
			if (elementInfoTable.containsKey(elemInfo_update.getElementName()))
			{
				elementInfoTable.remove(elemInfo_update.getElementName());
			}
		}
		return this;
	}

	/**
	 * Updater
	 * 
	 * @param elemInfo_update  table with element-specific attribute info to remove from attribInfoTable
	 * @return 
	 */
	public ElementInfo updateRemove(ElemInfoTable[] elemInfo_update)
	{
		if (elemInfo_update != null)
		{
			for (int i = 0; i < elemInfo_update.length; i++)
			{
				if (elementInfoTable.containsKey(elemInfo_update[i].getElementName()))
				{
					elementInfoTable.remove(elemInfo_update[i].getElementName());
				}
			}
		}
		return this;
	}

	/**
	 * @param elemInfo_update
	 * @return
	 */
	public ElementInfo updateReplace(ElemInfoTable elemInfo_update)
	{
		if (elemInfo_update != null)
		{
			elementInfoTable.put(elemInfo_update.getElementName(), new ElemInfo(elemInfo_update.getValidityStatus()));
		}
		return this;
	}

	/**
	 * @param elemInfo_update
	 * @return
	 */
	public ElementInfo updateReplace(ElemInfoTable[] elemInfo_update)
	{
		if (elemInfo_update != null)
		{
			for (int i = 0; i < elemInfo_update.length; i++)
			{
				elementInfoTable.put(elemInfo_update[i].getElementName(), new ElemInfo(elemInfo_update[i].getValidityStatus()));
			}
		}
		return this;
	}

	/**
	 * Returns a list of element names matching the requested validity for the specified JDF version.
	 * 
	 * @param elemValidity1 elemValidity: requested validity
	 * @param elemValidity2 
	 * @param elemValidity3 
	 * @param elemValidity4 
	 * @return VString: list of strings containing the names of the matching elements
	 */
	private VString conformingElements(EnumElementValidity elemValidity1, EnumElementValidity elemValidity2, EnumElementValidity elemValidity3, EnumElementValidity elemValidity4)
	{
		VString matchingElements = new VString();
		Iterator<String> iter = elementInfoTable.keySet().iterator();

		long l2 = JDFVersions.getTheMask(version);
		long v2 = JDFVersions.getTheOffset(version);
		while (iter.hasNext())
		{
			final String theKey = iter.next();
			final ElemInfo ei = elementInfoTable.get(theKey);
			long eiValStatus = ei.getElemValidityStatus() & l2;
			if (eiValStatus == ((long) elemValidity1.getValue() << v2))
			{
				matchingElements.add(theKey);
			}
			else if (eiValStatus == ((long) elemValidity2.getValue() << v2))
			{
				matchingElements.add(theKey);
			}
			else if (eiValStatus == ((long) elemValidity3.getValue() << v2))
			{
				matchingElements.add(theKey);
			}
			else if (eiValStatus == ((long) elemValidity4.getValue() << v2))
			{
				matchingElements.add(theKey);
			}
		}

		return matchingElements;
	}

	/**
	 * Returns true if there is at least one sub-element matching the requested validity for the specified JDF version.
	 * 
	 * @param elemValidity1 elemValidity: requested validity
	 * @param elemValidity2 
	 * @param elemValidity3 
	 * @param elemValidity4 
	 * @return boolean: true if at least one sub-element matches the requested validity
	 */
	public boolean hasConformingElements(EnumElementValidity elemValidity1, EnumElementValidity elemValidity2, EnumElementValidity elemValidity3, EnumElementValidity elemValidity4)
	{
		return !conformingElements(elemValidity1, elemValidity2, elemValidity3, elemValidity4).isEmpty();
	}

	/**
	 * Returns the list of required sub-elements for the specified JDF version.
	 * 
	 * @return VString: list of strings containing the names of the required sub-elements
	 */
	public VString requiredElements()
	{
		return conformingElements(EnumElementValidity.Required, EnumElementValidity.SingleRequired, EnumElementValidity.Dummy, EnumElementValidity.Dummy);
	}

	/**
	 * Returns the list of optional sub-elements for the specified JDF version. Note: This includes elements marked as
	 * optional as well as elements marked as deprecated (since, for backward compatibility, these are also optional).
	 * 
	 * @return VString: list of strings containing the names of the optional attributes
	 */
	public VString optionalElements()
	{
		return conformingElements(EnumElementValidity.Optional, EnumElementValidity.Deprecated, EnumElementValidity.SingleDeprecated, EnumElementValidity.SingleOptional);
	}

	/**
	 * Returns the list of deprecated elements for the specified JDF version.
	 * 
	 * @return VString: list of strings containing the names of the deprecated attributes
	 */
	public VString deprecatedElements()
	{
		return conformingElements(EnumElementValidity.Deprecated, EnumElementValidity.SingleDeprecated, EnumElementValidity.Dummy, EnumElementValidity.Dummy);
	}

	/**
	 * Returns the list of unique elements for the specified JDF version.
	 * 
	 * @return VString: list of strings containing the names of the deprecated attributes
	 */
	public VString uniqueElements()
	{
		return conformingElements(EnumElementValidity.SingleRequired, EnumElementValidity.SingleOptional, EnumElementValidity.SingleDeprecated, EnumElementValidity.Dummy);
	}

	/**
	 * Returns the list of prerelease attributes (those that are only valid in a later version) for the specified JDF
	 * version.
	 * 
	 * @return VString: list of strings containing the names of the prerelease attributes
	 */
	public VString prereleaseElements()
	{
		return conformingElements(EnumElementValidity.None, EnumElementValidity.Dummy, EnumElementValidity.Dummy, EnumElementValidity.Dummy);
	}

	/*
	 * ----------------------------------------------------------------------- Enumeration of element validity values
	 * -----------------------------------------------------------------------
	 */

	/**
	 * Enumeration of element validity values
	 */
	@SuppressWarnings("unchecked")
	public static final class EnumElementValidity extends ValuedEnum
	{
		private static final long serialVersionUID = 1L;
		private static int m_startValue = 0;

		/**
		 * @param name
		 */
		private EnumElementValidity(String name)
		{
			super(name, m_startValue++);
		}

		/**
		 * @param enumName the name of the enum object to return
		 * @return the enum object if enumName is valid. Otherwise null
		 */
		public static EnumElementValidity getEnum(String enumName)
		{
			return (EnumElementValidity) getEnum(EnumElementValidity.class, enumName);
		}

		/**
		 * @param enumValue the value of the enum object to return
		 * @return the enum object if enumName is valid. Otherwise null
		 */
		public static EnumAttributeValidity getEnum(int enumValue)
		{
			return (EnumAttributeValidity) getEnum(EnumAttributeValidity.class, enumValue);
		}

		/**
		 * @return a map of all orientation enums
		 */
		public static Map getEnumMap()
		{
			return getEnumMap(EnumElementValidity.class);
		}

		/**
		 * @return a list of all orientation enums
		 */
		public static List getEnumList()
		{
			return getEnumList(EnumElementValidity.class);
		}

		/**
		 * @return an iterator over the enum objects
		 */
		public static Iterator iterator()
		{
			return iterator(EnumElementValidity.class);
		}

		/** */
		public static final EnumElementValidity Unknown = new EnumElementValidity(JDFConstants.ELEMENTVALIDITY_UNKNOWN);
		/** */
		public static final EnumElementValidity None = new EnumElementValidity(JDFConstants.ELEMENTVALIDITY_NONE);
		/** */
		public static final EnumElementValidity Required = new EnumElementValidity(JDFConstants.ELEMENTVALIDITY_REQUIRED);
		/** */
		public static final EnumElementValidity Optional = new EnumElementValidity(JDFConstants.ELEMENTVALIDITY_OPTIONAL);
		/** */
		public static final EnumElementValidity Deprecated = new EnumElementValidity(JDFConstants.ELEMENTVALIDITY_DEPRECATED);
		/** */
		public static final EnumElementValidity SingleRequired = new EnumElementValidity(JDFConstants.ELEMENTVALIDITY_SINGLEREQUIRED);
		/** */
		public static final EnumElementValidity SingleOptional = new EnumElementValidity(JDFConstants.ELEMENTVALIDITY_SINGLEOPTIONAL);
		/** */
		public static final EnumElementValidity SingleDeprecated = new EnumElementValidity(JDFConstants.ELEMENTVALIDITY_SINGLEDEPRECATED);
		/** */
		public static final EnumElementValidity Dummy = new EnumElementValidity(JDFConstants.ELEMENTVALIDITY_DUMMY);
	}

	/**
	 * @param v
	 */
	public void setVersion(EnumVersion v)
	{
		version = v;
	}

	/**
	 * @see java.lang.Object#toString()
	 * @return
	*/
	@Override
	public String toString()
	{
		String s = "ElementInfoTable version=" + version;
		s += elementInfoTable.toString();
		return s;
	}

	/**
	 * get the first jdf version where an attrinute of this type is valid
	 * 
	 * @param elementName the name of the queried attribute
	 * @return
	 */
	public EnumVersion getFirstVersion(String elementName)
	{
		if (elementInfoTable.containsKey(elementName))
		{
			return (elementInfoTable.get(elementName)).getFirstVersion();
		}
		return null;
	}

	/**
	 * get the last jdf version where an attrinute of this type is valid
	 * 
	 * @param elementName the name of the queried attribute
	 * @return
	 */
	public EnumVersion getLastVersion(String elementName)
	{
		if (elementInfoTable.containsKey(elementName))
		{
			return (elementInfoTable.get(elementName)).getLastVersion();
		}
		return null;
	}

}
