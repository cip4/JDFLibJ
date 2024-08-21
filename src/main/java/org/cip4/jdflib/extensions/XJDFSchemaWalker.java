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
import java.util.Map;

import org.cip4.jdflib.core.KElement;
import org.cip4.jdflib.datatypes.JDFAttributeMap;
import org.cip4.jdflib.elementwalker.BaseElementWalker;
import org.cip4.jdflib.elementwalker.BaseWalker;
import org.cip4.jdflib.elementwalker.BaseWalkerFactory;

/**
 * 
 * the old implementattion was obsolete this implementation extracts more data from the xml schema that can be injected into the json schema
 * 
 */
public class XJDFSchemaWalker extends BaseElementWalker
{
	private final JDFAttributeMap typeMap;

	/**
	 * 
	 */
	public XJDFSchemaWalker()
	{
		super(new BaseWalkerFactory());
		typeMap = new JDFAttributeMap();
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
			final String typ = a.getNonEmpty("type");
			final String parent = a.getParentNode_KElement().getInheritedAttribute("name", null, null);
			final String name = a.getNonEmpty("name");
			typeMap.putNotNull(parent + "/" + name, typ);
			return null;
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
			case "XYPair":
				return 2;
			case "shape":
			case "LabColor":
			case "sRGBColor":
				return 3;
			case "rectangle":
			case "CMYKColor":
				return 4;
			case "matrix":
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
			case "shape":
			case "sRGBColor":
			case "CMYKColor":
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
			case "sRGBColor":
			case "CMYKColor":
				return 1;
			default:
			}
		return null;

	}

}
