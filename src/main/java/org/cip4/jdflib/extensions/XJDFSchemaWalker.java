/*
 * The CIP4 Software License, Version 1.0
 *
 *
 * Copyright (c) 2001-2024 The International Cooperation for the Integration of
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
package org.cip4.jdflib.extensions;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.cip4.jdflib.core.KElement;
import org.cip4.jdflib.datatypes.JDFAttributeMap;
import org.cip4.jdflib.elementwalker.BaseElementWalker;
import org.cip4.jdflib.elementwalker.BaseWalker;
import org.cip4.jdflib.elementwalker.BaseWalkerFactory;
import org.cip4.jdflib.util.ListMap;
import org.cip4.jdflib.util.StringUtil;

/**
 * 
 * the old implementattion was obsolete this implementation extracts more data from the xml schema that can be injected into the json schema
 * 
 */
public class XJDFSchemaWalker extends BaseElementWalker
{
	static final String ENUM = "Enum";
	static final String MATRIX = "matrix";
	static final String CMYK_COLOR = "CMYKColor";
	static final String RECTANGLE = "rectangle";
	static final String SRGB_COLOR = "sRGBColor";
	static final String LAB_COLOR = "LabColor";
	static final String SHAPE = "shape";
	static final String XY_PAIR = "XYPair";
	private final JDFAttributeMap typeMap;
	private final ListMap<String, String> enumMap;

	/**
	 * 
	 */
	public XJDFSchemaWalker()
	{
		super(new BaseWalkerFactory());
		typeMap = new JDFAttributeMap();
		enumMap = new ListMap<>();
	}

	/**
	 * 
	 * @author Rainer Prosi, Heidelberger Druckmaschinen
	 * 
	 */
	protected class WalkElement extends BaseWalker
	{
		public WalkElement()
		{
			super(getFactory());
		}

		/**
		 * @param in
		 * @param out
		 * @return not null if must continue
		 */
		@Override
		public KElement walk(final KElement in, final KElement out)
		{
			return in;
		}
	}

	/**
	 * any matching class will be removed with extreme prejudice...
	 * 
	 * @author Rainer Prosi, Heidelberger Druckmaschinen
	 * 
	 */
	protected class WalkAttribute extends WalkElement
	{

		public WalkAttribute()
		{
			super();
		}

		/**
		 * @param xjdf
		 * @return true if must continue
		 */
		@Override
		public KElement walk(final KElement a, final KElement xjdf)
		{
			final String typ = a.getNonEmpty(XSDConstants.TYPE);
			final String parent = a.getParentNode_KElement().getInheritedAttribute(XSDConstants.NAME, null, null);
			final String name = a.getNonEmpty(XSDConstants.NAME);
			final String key = parent + "/" + name;
			typeMap.putNotNull(key, typ);
			return super.walk(a, xjdf);
		}

		/**
		 * @see org.cip4.jdflib.elementwalker.BaseWalker#matches(org.cip4.jdflib.core.KElement)
		 * @param toCheck
		 * @return true if it matches
		 */
		@Override
		public boolean matches(final KElement toCheck)
		{
			return "xs:attribute".equals(toCheck.getNodeName());
		}
	}

	/**
	 * any matching class will be removed with extreme prejudice...
	 * 
	 * @author Rainer Prosi, Heidelberger Druckmaschinen
	 * 
	 */
	protected class WalkEnum extends WalkElement
	{

		private static final String VALUE = "value";

		public WalkEnum()
		{
			super();
		}

		/**
		 * @param xjdf
		 * @return true if must continue
		 */
		@Override
		public KElement walk(final KElement a, final KElement xjdf)
		{
			final String val = a.getNonEmpty(VALUE);
			if (!StringUtil.isEmpty(val))
			{
				final String name = getKey(a);
				enumMap.putOne(name, val);
			}
			return null;
		}

		/**
		 * searches for the first attribute occurrence in this element or any ancestors
		 *
		 * @param attrib the attribute name
		 * @param nameSpaceURI the XML-namespace
		 * @param def the default if it does not exist
		 * @return String value of attribute found, value of def if not available
		 * @default getInheritedAttribute_KElement(attrib, null, JDFCoreConstants.EMPTYSTRING)
		 */
		String getKey(KElement a)
		{
			int i = 0;
			String strRet = "";
			while (i < 2 && a != null)
			{
				final String name = a.getNonEmpty(XSDConstants.NAME);
				if (name != null)
				{
					strRet = i == 0 ? name : name + "/" + strRet;
					i++;
				}
				a = a.getParentNode_KElement();
			}

			return strRet;
		}

		/**
		 * @see org.cip4.jdflib.elementwalker.BaseWalker#matches(org.cip4.jdflib.core.KElement)
		 * @param toCheck
		 * @return true if it matches
		 */
		@Override
		public boolean matches(final KElement toCheck)
		{
			return "xs:enumeration".equals(toCheck.getNodeName());
		}
	}

	public JDFAttributeMap getTypeMap()
	{
		return typeMap;
	}

	public Map<String, Integer> getLengthMap()
	{
		final Map<String, Integer> ret = new HashMap<>();
		for (final String key : typeMap.getKeyList())
		{
			final int l = getLength(key);
			if (l > 0)
			{
				ret.put(key, Integer.valueOf(l));
			}
		}
		return ret;
	}

	public int getLength(final String path)
	{
		final String typ = typeMap.get(path);
		if (typ != null)
			switch (typ)
			{
			case XY_PAIR:
				return 2;
			case SHAPE:
			case LAB_COLOR:
			case SRGB_COLOR:
				return 3;
			case RECTANGLE:
			case CMYK_COLOR:
				return 4;
			case MATRIX:
				return 6;
			default:
			}
		return 0;

	}

	public Integer getMin(final String path)
	{
		final String typ = typeMap.get(path);
		if (typ != null)
			switch (typ)
			{
			case SHAPE:
			case SRGB_COLOR:
			case CMYK_COLOR:
				return 0;
			default:
			}
		return null;

	}

	public Integer getMax(final String path)
	{
		final String typ = typeMap.get(path);
		if (typ != null)
			switch (typ)
			{
			case SRGB_COLOR:
			case CMYK_COLOR:
				return 1;
			default:
			}
		return null;

	}

	public List<String> getEnums(final String name)
	{
		final String newName = getKey(name);
		return newName == null ? null : enumMap.get(newName);

	}

	public String getKey(final String name)
	{
		if (enumMap.containsKey(name))
			return name;
		else if (name.startsWith(ENUM) && enumMap.containsKey(name.substring(4)))
			return name.substring(4);
		else if (enumMap.containsKey(ENUM + name))
			return ENUM + name;
		else if (name.indexOf('/') > 0)
			return getKey(StringUtil.removeToken(name, 0, "/"));
		else
			return null;

	}

	public ListMap<String, String> getEnumMap()
	{
		return enumMap;
	}

}
