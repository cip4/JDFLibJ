/**
 * The CIP4 Software License, Version 1.0
 *
 * Copyright (c) 2001-2023 The International Cooperation for the Integration of Processes in Prepress, Press and Postpress (CIP4). All rights reserved.
 *
 * Redistribution and use in source and binary forms, with or without modification, are permitted provided that the following conditions are met:
 *
 * 1. Redistributions of source code must retain the above copyright notice, this list of conditions and the following disclaimer.
 *
 * 2. Redistributions in binary form must reproduce the above copyright notice, this list of conditions and the following disclaimer in the documentation and/or other materials provided with the
 * distribution.
 *
 * 3. The end-user documentation included with the redistribution, if any, must include the following acknowledgment: "This product includes software developed by the The International Cooperation for
 * the Integration of Processes in Prepress, Press and Postpress (www.cip4.org)" Alternately, this acknowledgment may appear in the software itself, if and wherever such third-party acknowledgments
 * normally appear.
 *
 * 4. The names "CIP4" and "The International Cooperation for the Integration of Processes in Prepress, Press and Postpress" must not be used to endorse or promote products derived from this software
 * without prior written permission. For written permission, please contact info@cip4.org.
 *
 * 5. Products derived from this software may not be called "CIP4", nor may "CIP4" appear in their name, without prior written permission of the CIP4 organization
 *
 * Usage of this software in commercial products is subject to restrictions. For details please consult info@cip4.org.
 *
 * THIS SOFTWARE IS PROVIDED ``AS IS'' AND ANY EXPRESSED OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO, THE IMPLIED WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE ARE
 * DISCLAIMED. IN NO EVENT SHALL THE INTERNATIONAL COOPERATION FOR THE INTEGRATION OF PROCESSES IN PREPRESS, PRESS AND POSTPRESS OR ITS CONTRIBUTORS BE LIABLE FOR ANY DIRECT, INDIRECT, INCIDENTAL,
 * SPECIAL, EXEMPLARY, OR CONSEQUENTIAL DAMAGES (INCLUDING, BUT NOT LIMITED TO, PROCUREMENT OF SUBSTITUTE GOODS OR SERVICES; LOSS OF USE, DATA, OR PROFITS; OR BUSINESS INTERRUPTION) HOWEVER CAUSED AND
 * ON ANY THEORY OF LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY, OR TORT (INCLUDING NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE OF THIS SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY
 * OF SUCH DAMAGE. ====================================================================
 *
 * This software consists of voluntary contributions made by many individuals on behalf of the The International Cooperation for the Integration of Processes in Prepress, Press and Postpress and was
 * originally based on software copyright (c) 1999-2001, Heidelberger Druckmaschinen AG copyright (c) 1999-2001, Agfa-Gevaert N.V.
 *
 * For more information on The International Cooperation for the Integration of Processes in Prepress, Press and Postpress , please see <http://www.cip4.org/>.
 *
 *
 */
package org.cip4.jdflib.elementwalker.fixversion;

import java.util.List;
import java.util.zip.DataFormatException;

import org.apache.commons.lang3.StringUtils;
import org.cip4.jdflib.core.AttributeInfo;
import org.cip4.jdflib.core.AttributeInfo.EnumAttributeType;
import org.cip4.jdflib.core.AttributeName;
import org.cip4.jdflib.core.JDFElement;
import org.cip4.jdflib.core.JDFElement.EnumVersion;
import org.cip4.jdflib.core.JDFException;
import org.cip4.jdflib.core.KElement;
import org.cip4.jdflib.core.StringArray;
import org.cip4.jdflib.core.VString;
import org.cip4.jdflib.datatypes.JDFAttributeMap;
import org.cip4.jdflib.datatypes.JDFNameRangeList;
import org.cip4.jdflib.datatypes.JDFShape;
import org.cip4.jdflib.datatypes.JDFXYPair;
import org.cip4.jdflib.util.ContainerUtil;
import org.cip4.jdflib.util.EnumUtil;
import org.cip4.jdflib.util.JDFDuration;
import org.cip4.jdflib.util.StringUtil;

/**
 * the resource walker note the naming convention Walkxxx so that it is automagically instantiated by the super classes
 *
 * @author prosirai
 *
 */
public class WalkElement extends WalkAnyElement
{

	/**
	 * fills this into the factory
	 */
	public WalkElement()
	{
		super();
	}

	/**
	 * @see org.cip4.jdflib.elementwalker.BaseWalker#walk(org.cip4.jdflib.core.KElement, org.cip4.jdflib.core.KElement)
	 * @param e1 - the element to track
	 * @param trackElem - always null
	 * @return the element to continue walking
	 */
	@Override
	public KElement walk(final KElement e1, final KElement trackElem)
	{
		final JDFElement el = (JDFElement) e1;
		if (fixVersion.isZappDeprecated() && el.isDeprecated())
		{
			processDeprecated(el);
			return null;
		}

		// replace all "~" with " ~ "
		final JDFAttributeMap m = el.getAttributeMap_KElement();
		if (updateAttributes(m))
		{
			el.removeAttributes(null);
			el.setAttributes(m);
		}
		final List<String> keys = ContainerUtil.getKeyList(m);
		final AttributeInfo ai = el.getAttributeInfo();
		if (keys != null)
		{
			final String localName = el.getLocalName();
			final List<String> ignore = fixVersion.ignoreMap.get(localName);
			for (final String key : keys)
			{
				final String value = m.get(key);
				if (ignore == null || !ignore.contains(key))
				{
					walkSingleAttribute(el, ai, key, value);
				}
			}
		}
		return el;
	}

	protected void processDeprecated(final JDFElement el)
	{
		el.deleteNode();
	}

	/**
	 * hook to update the attributemap
	 *
	 * @param m
	 * @param el
	 */
	boolean updateAttributes(final JDFAttributeMap m)
	{
		return false;
	}

	/**
	 * @param el
	 * @param ai
	 * @param key
	 * @param value
	 */
	void walkSingleAttribute(final JDFElement el, final AttributeInfo ai, final String key, final String value)
	{
		final EnumAttributeType attType = ai.getAttributeType(key);
		if (fixVersion.bZappInvalid && attType == null)
		{
			final String prefix = KElement.xmlnsPrefix(key);
			final String uri = prefix == null ? null : el.getNamespaceURIFromPrefix(prefix);
			if ((uri == null || JDFElement.isInJDFNameSpaceStatic(uri)) && ai.getLastVersion(key) == null)
			{
				el.removeAttribute_KElement(key, null);
				return;
			}
		}
		else if (fixVersion.bZappDeprecated && fixVersion.version != null)
		{
			final String prefix = KElement.xmlnsPrefix(key);
			final String uri = prefix == null ? null : el.getNamespaceURIFromPrefix(prefix);
			if ((uri == null || JDFElement.isInJDFNameSpaceStatic(uri)) && fixVersion.version.isGreater(ai.getLastVersion(key)))
			{
				el.removeAttribute_KElement(key, null);
				return;
			}
		}

		if (EnumAttributeType.isRange(attType))
		{
			fixRange(el, key, value);
		}
		else if (EnumAttributeType.duration.equals(attType))
		{
			fixDuration(el, key, value);
		}
		else if (EnumAttributeType.boolean_.equals(attType))
		{
			fixBoolean(el, key, value);
		}
		else if (EnumAttributeType.integer.equals(attType))
		{
			final int i = StringUtil.parseInt(value, Integer.MIN_VALUE + 42);
			if (i == Integer.MIN_VALUE + 42)
			{
				el.removeAttribute(key);
			}
			else
			{
				el.setAttribute(key, i, null);
			}
		}
		else if (EnumAttributeType.double_.equals(attType))
		{
			final double d = StringUtil.parseDouble(value, Double.POSITIVE_INFINITY);
			if (d == Double.POSITIVE_INFINITY)
			{
				el.removeAttribute(key);
			}
			else
			{
				el.setAttribute(key, d, null);
			}
		}
		else if (EnumAttributeType.dateTime.equals(attType))
		{
			fixDateTime(el, key, value, true);
		}
		else if (EnumAttributeType.NMTOKEN.equals(attType))
		{
			fixNMTOKEN(el, key, value);
		}
		else if (EnumAttributeType.NMTOKENS.equals(attType))
		{
			fixNMTOKENS(el, key, value);
		}
		else if (EnumAttributeType.XYPair.equals(attType))
		{
			final JDFXYPair xyPair = JDFXYPair.createXYPair(value);
			el.setAttribute(key, xyPair, null);
		}
		else if (EnumAttributeType.shape.equals(attType))
		{
			final JDFShape shape = JDFShape.createShape(value);
			el.setAttribute(key, shape, null);
		}
		else if (EnumAttributeType.enumerations.equals(attType))
		{
			fixSourceObjects(el, key, value);
		}
		if (fixVersion.bFixIDs && value.length() > 0 && StringUtils.isNumeric(value.substring(0, 1)))
		{
			fixIDs(el, ai, key, value);
		}
		if (AttributeName.ICSVERSIONS.equals(key))
		{
			fixICSVersions(el, value);
		}
		else if (fixVersion.version != null && AttributeName.VERSION.equals(key))
		{
			el.setAttribute(key, fixVersion.version, null);
		}
		else if (fixVersion.version != null && AttributeName.MAXVERSION.equals(key) && EnumUtil.aLessEqualsThanB(EnumVersion.getEnum(value), fixVersion.version))
		{
			el.setAttribute(key, fixVersion.version, null);
		}
		if (fixVersion.bZappInvalid && !AttributeInfo.validStringForType(value, attType, null))
		{
			// we may have fixed it earlier - recheck
			final String newValue = el.getNonEmpty_KElement(key);
			if (el == null || !AttributeInfo.validStringForType(newValue, attType, null))
			{
				el.removeAttribute_KElement(key, null);
			}
		}
	}

	void fixNMTOKENS(final JDFElement el, final String key, final String value)
	{
		final StringArray a = StringArray.getVString(value, null);
		final String v2 = a == null ? null : a.getString();
		if (!StringUtil.equals(v2, value))
		{
			el.setAttribute(key, v2);
		}

	}

	void fixNMTOKEN(final JDFElement el, final String key, final String value)
	{
		if (!StringUtil.isNMTOKEN(value))
		{
			final String newVal = StringUtil.replaceCharSet(value, " ", "_", 0);
			if (!StringUtil.equals(newVal, value) && StringUtil.isNMTOKEN(newVal))
			{
				el.setAttribute(key, newVal);
			}
		}
	}

	private void fixBoolean(final JDFElement el, final String key, final String value)
	{
		if (!"false".equals(value) && !"true".equals(value) && StringUtil.isBoolean(value))
		{
			final boolean newVal = StringUtil.parseBoolean(value, true);
			el.setAttribute(key, "" + newVal);
		}

	}

	/**
	 *
	 * @param el
	 * @param key
	 * @param value
	 */
	protected void fixSourceObjects(final JDFElement el, final String key, final String value)
	{
		if (AttributeName.SOURCEOBJECTS.equals(key) && EnumUtil.aLessEqualsThanB(EnumVersion.Version_1_5, fixVersion.version))
		{
			if (StringUtil.hasToken(value, "All", null, 0))
			{
				el.removeAttribute_KElement(key, null);
			}
		}
	}

	/**
	 * @param el
	 * @param value
	 */
	private void fixICSVersions(final JDFElement el, final String value)
	{
		if (!this.fixVersion.fixICSVersions)
		{
			return;
		}
		final VString v = StringUtil.tokenize(value, null, false);
		if (v == null)
		{
			return;
		}
		final int minor = this.fixVersion.version.getMinorVersion();
		for (int i = 0; i < v.size(); i++)
		{
			String icsToken = v.get(i);
			if (".".equals(StringUtil.substring(icsToken, -2, -1)) && StringUtil.isInteger(StringUtil.rightStr(icsToken, 1)))
			{
				icsToken = StringUtil.leftStr(icsToken, -1) + minor;
				v.set(i, icsToken);
			}
		}
		el.setAttribute(AttributeName.ICSVERSIONS, v, null);
	}

	/**
	 * @param el
	 * @param key
	 * @param value
	 */
	void fixRange(final JDFElement el, final String key, final String value)
	{
		try
		{
			final JDFNameRangeList nrl = new JDFNameRangeList(value);
			el.setAttribute(key, nrl, null);
		}
		catch (final JDFException e)
		{
			// do nothing
		}
		catch (final DataFormatException e)
		{
			// do nothing
		}
	}

	/**
	 * @param el
	 * @param key
	 * @param value
	 */
	private void fixDuration(final JDFElement el, final String key, final String value)
	{
		try
		{
			el.setAttribute(key, new JDFDuration(value).getDurationISO());
		}
		catch (final DataFormatException ex)
		{
			// nop - continue
		}
	}

	/**
	 * @param el
	 * @param ai
	 * @param key
	 * @param value
	 */
	private void fixIDs(final JDFElement el, final AttributeInfo ai, final String key, String value)
	{
		final EnumAttributeType atType = ai.getAttributeType(key);
		if (atType != null)
		{
			if (atType.equals(EnumAttributeType.ID) || atType.equals(EnumAttributeType.IDREF))
			{
				value = "_" + value;
				el.setAttribute(key, value);
			}
			else if (atType.equals(EnumAttributeType.IDREFS))
			{
				final StringArray vvalues = new StringArray(value, " ");
				for (int i = 0; i < vvalues.size(); i++)
				{
					String s = vvalues.get(i);
					if (s.length() > 0 && StringUtils.isNumeric(s.substring(0, 1)))
					{
						s = "_" + s;
						vvalues.set(i, s);
					}
				}
				el.setAttribute(key, vvalues, null);
			}
		}
	}

	/**
	 * @see org.cip4.jdflib.elementwalker.BaseWalker#matches(org.cip4.jdflib.core.KElement)
	 * @param toCheck
	 * @return true if matches
	 */
	@Override
	public boolean matches(final KElement toCheck)
	{
		return (toCheck instanceof JDFElement && JDFElement.isInAnyCIP4NameSpaceStatic(toCheck));
	}
}