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
package org.cip4.jdflib.extensions;

import java.util.List;

import org.apache.commons.lang.enums.ValuedEnum;
import org.cip4.jdflib.core.KElement;
import org.cip4.jdflib.core.StringArray;
import org.cip4.jdflib.extensions.XSDConstants.eAttributeUse;
import org.cip4.jdflib.util.EnumUtil;
import org.cip4.jdflib.util.StringUtil;

public class XSDUtil
{
	private XSDUtil()
	{
		super();
	}

	/**
	 * @param root
	 * @param attName
	 * @param typ
	 * @param required
	 */
	public static KElement setXSAttribute(KElement root, final String attName, String typ, boolean required)
	{
		final KElement root2 = root.getElement(XSDConstants.XS_COMPLEX_CONTENT);
		if (root2 != null)
		{
			final KElement root3 = root2.getElement(XSDConstants.XS_EXTENSION);
			if (root3 != null)
			{
				root = root3;
			}
		}
		KElement att = root.getChildWithAttribute(XSDConstants.XS_ATTRIBUTE, XSDConstants.NAME, null, attName, 0, true);
		if (att == null)
		{
			att = root.appendElement(XSDConstants.XS_ATTRIBUTE);
		}
		required = required || eAttributeUse.required.equals(eAttributeUse.getEnum(att.getAttribute(XSDConstants.USE)));
		att.setAttribute(XSDConstants.USE, required ? eAttributeUse.required : eAttributeUse.optional, null);
		att.setAttribute(XSDConstants.NAME, attName);
		att.setAttribute(XSDConstants.TYPE, typ);
		return att;
	}

	/**
	 * @param root
	 * @param typeName
	 * @param typ
	 * @param required
	 */
	public static KElement createRestriction(final KElement root, final String typeName)
	{
		KElement simp = root.getChildWithAttribute(XSDConstants.XS_SIMPLE_TYPE, XSDConstants.NAME, null, typeName, 0, true);
		if (simp == null)
		{
			simp = root.appendElement(XSDConstants.XS_SIMPLE_TYPE);
			simp.setAttribute(XSDConstants.NAME, typeName);
		}
		return simp.getCreateElement(XSDConstants.XS_RESTRICTION);
	}

	/**
	 * @param root
	 * @param simpleType
	 * @param typ
	 * @param required
	 */
	public static KElement createPatternType(final KElement root, final String simpleType, String pattern)
	{
		final KElement restrict = createRestriction(root, simpleType);
		restrict.setAttribute(XSDConstants.BASE, XSDConstants.XS_STRING);
		final KElement pat = restrict.getCreateElement(XSDConstants.XS_PATTERN);
		pat.setAttribute(XSDConstants.VALUE, pattern);
		return restrict;
	}

	/**
	 * @param root
	 * @param simpleType
	 * @param typ
	 * @param required
	 */
	public static KElement createEnumType(final KElement root, final String simpleType, final Class<? extends ValuedEnum> c)
	{
		final KElement restrict = createRestriction(root, simpleType);
		updateRestrictions(restrict, c);
		return restrict;
	}

	public static void removeAttribute(KElement parent, String name)
	{
		removeXSElement(parent, XSDConstants.XS_ATTRIBUTE, XSDConstants.NAME, name);

	}

	static void removeXSElement(KElement parent, String typ, String attName, String name)
	{
		final KElement e = parent.getChildWithAttribute(typ, attName, null, name, 0, true);
		if (e != null)
		{
			e.deleteNode();
		}

	}

	static String shortString(KElement schema)
	{
		String s = schema.getNodeName();
		for (final String key : new StringArray("name ref type base"))
		{
			s = StringUtil.addToken(s, " " + key + ": ", schema.getInheritedAttribute(key));
		}
		return s;
	}

	public static void updateRestrictions(KElement copy, List<String> values)
	{
		final KElement res = XSDConstants.XS_RESTRICTION.equals(copy.getNodeName()) ? copy : copy.getCreateElement(XSDConstants.XS_RESTRICTION);
		res.setAttribute(XSDConstants.BASE, "xs:NMTOKEN");
		for (final String val : values)
		{
			res.getCreateChildWithAttribute(XSDConstants.XS_ENUMERATION, XSDConstants.VALUE, null, val, 0);
		}
	}

	public static void updateRestrictions(KElement copy, final Class<? extends ValuedEnum> c)
	{
		updateRestrictions(copy, EnumUtil.getNamesVector(c));
	}

}
