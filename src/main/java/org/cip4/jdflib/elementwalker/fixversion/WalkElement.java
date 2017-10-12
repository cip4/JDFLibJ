/**
 * The CIP4 Software License, Version 1.0
 *
 * Copyright (c) 2001-2017 The International Cooperation for the Integration of
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
 *
 */
package org.cip4.jdflib.elementwalker.fixversion;

import java.util.Vector;
import java.util.zip.DataFormatException;

import org.apache.commons.lang.StringUtils;
import org.cip4.jdflib.core.AttributeInfo;
import org.cip4.jdflib.core.AttributeInfo.EnumAttributeType;
import org.cip4.jdflib.core.AttributeName;
import org.cip4.jdflib.core.JDFElement;
import org.cip4.jdflib.core.JDFException;
import org.cip4.jdflib.core.KElement;
import org.cip4.jdflib.core.VString;
import org.cip4.jdflib.datatypes.JDFAttributeMap;
import org.cip4.jdflib.datatypes.JDFNameRangeList;
import org.cip4.jdflib.datatypes.JDFShape;
import org.cip4.jdflib.datatypes.JDFXYPair;
import org.cip4.jdflib.elementwalker.BaseWalker;
import org.cip4.jdflib.span.JDFTimeSpan;
import org.cip4.jdflib.util.ContainerUtil;
import org.cip4.jdflib.util.JDFDate;
import org.cip4.jdflib.util.JDFDuration;
import org.cip4.jdflib.util.StringUtil;

/**
 * the resource walker note the naming convention Walkxxx so that it is automagically instantiated by the super classes
 *
 * @author prosirai
 *
 */
public class WalkElement extends BaseWalker
{

	/**
	 *
	 */
	FixVersionImpl fixVersion;

	/**
	 * fills this into the factory
	 */
	public WalkElement()
	{
		super();
		fixVersion = null;
	}

	/**
	 * fills this into the factory
	 * @param fixVersion
	 */
	public void setParent(final FixVersionImpl fixVersion)
	{
		this.fixVersion = fixVersion;
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
			el.deleteNode();
			return null;
		}

		// replace all "~" with " ~ "
		final JDFAttributeMap m = el.getAttributeMap_KElement();
		if (updateAttributes(m))
		{
			el.removeAttributes(null);
			el.setAttributes(m);
		}
		final Vector<String> keys = ContainerUtil.getKeyVector(m);
		final AttributeInfo ai = el.getAttributeInfo();
		if (keys != null)
		{
			for (final String key : keys)
			{
				final String value = m.get(key);
				walkSingleAttribute(el, ai, key, value);
			}
		}
		return el;
	}

	/**
	 * hook to update the attributemap
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
		if (fixVersion.bZappDeprecated)
		{
			final String prefix = KElement.xmlnsPrefix(key);
			final String uri = prefix == null ? null : el.getNamespaceURIFromPrefix(prefix);
			if ((uri == null || JDFElement.isInJDFNameSpaceStatic(uri)) && fixVersion.version.isGreater(ai.getLastVersion(key)))
			{
				el.removeAttribute_KElement(key, null);
				return;
			}
		}

		final EnumAttributeType attType = ai.getAttributeType(key);
		if (EnumAttributeType.isRange(attType))
		{
			fixRange(el, key, value);
		}
		else if (EnumAttributeType.duration.equals(attType))
		{
			fixDuration(el, key, value);
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
			fixDateTime(el, key, value);
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
		if (fixVersion.bFixIDs && value.length() > 0 && StringUtils.isNumeric(value.substring(0, 1)))
		{
			fixIDs(el, ai, key, value);
		}
		if (AttributeName.ICSVERSIONS.equals(key))
		{
			fixICSVersions(el, value);
		}
		if (fixVersion.bZappInvalid && !AttributeInfo.validStringForType(value, attType, null))
		{
			el.removeAttribute_KElement(key, null);
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
	private void fixRange(final JDFElement el, final String key, final String value)
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
	 * @param key
	 * @param value
	 */
	void fixDateTime(final JDFElement el, final String key, final String value)
	{
		int hour = -1;
		String check = key;
		if (el instanceof JDFTimeSpan)
		{
			check = el.getLocalName();
		}
		if (check != null)
		{
			if (check.endsWith(AttributeName.END) || AttributeName.REQUIRED.equals(check))
				hour = fixVersion.lasthour;
			else if (check.endsWith(AttributeName.START) || AttributeName.EARLIEST.equals(check))
				hour = fixVersion.firsthour;
		}
		try
		{
			el.setAttribute(key, new JDFDate(value, hour, 0).getDateTimeISO());
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
				final VString vvalues = new VString(value, " ");
				for (int i = 0; i < vvalues.size(); i++)
				{
					String s = vvalues.get(i);
					if (s.length() > 0 && StringUtils.isNumeric(s.substring(0, 1)))
					{
						s = "_" + s;
						vvalues.setElementAt(s, i);
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
		return (toCheck instanceof JDFElement);
	}
}