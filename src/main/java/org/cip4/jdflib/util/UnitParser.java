/*
 *
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
package org.cip4.jdflib.util;

import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

import org.cip4.jdflib.core.AttributeInfo.EnumAttributeType;
import org.cip4.jdflib.core.AttributeName;
import org.cip4.jdflib.core.ElementName;
import org.cip4.jdflib.core.JDFConstants;
import org.cip4.jdflib.core.JDFElement;
import org.cip4.jdflib.core.KElement;
import org.cip4.jdflib.core.VString;
import org.cip4.jdflib.datatypes.JDFAttributeMap;
import org.cip4.jdflib.span.JDFSpanBase;

/**
 * class to parse units from strings
 *
 * @author Rainer Prosi, Heidelberger Druckmaschinen *
 */
public class UnitParser
{
	public static final String UNIT_IN = "in";
	public static final String UNIT_CM = "cm";
	public static final String UNIT_MM = "mm";
	public static final String UNIT_PT = "pt";
	final static Set<String> unitKeys = getUnitKeys();
	private int precision;

	/**
	 *
	 */
	public UnitParser()
	{
		super();
		setPrecision(4);
	}

	/**
	 * @param element
	 */
	public void convertUnits(final KElement element)
	{
		final JDFAttributeMap map = element.getAttributeMap_KElement();
		final Iterator<String> keyIt = map.getKeyIterator();
		final boolean elemSpan = (element instanceof JDFSpanBase) && isUnit(element.getLocalName());
		while (keyIt.hasNext())
		{
			final String key = keyIt.next();

			if (!elemSpan && !isUnit(key))
			{
				continue;
			}

			final String val = map.get(key);
			final String newVal = extractUnits(val);
			if (!val.equals(newVal))
			{
				element.setAttribute(key, newVal);
			}
			// update dates in case they were specified in milliseconds
			if ((element instanceof JDFElement) && EnumAttributeType.dateTime.equals(((JDFElement) element).getAttributeInfo().getAttributeType(key)))
			{
				final JDFDate d = JDFDate.createDate(val);
				if (d != null && !val.equals(d.getDateTimeISO()))
				{
					element.setAttribute(key, d.getDateTimeISO());
				}
			}
		}
	}

	/**
	 *
	 * @param key
	 * @return
	 */
	public boolean isUnit(final String key)
	{
		return unitKeys.contains(key);
	}

	/**
	 *
	 * @param key
	 * @return
	 */
	public void addUnitKey(final String key)
	{
		unitKeys.add(key);
	}

	private static Set<String> getUnitKeys()
	{
		final Set<String> keys = new HashSet<>();
		if (keys.isEmpty())
		{
			keys.add(AttributeName.BACKOVERFOLD);
			keys.add(AttributeName.BLEEDBOTTOM);
			keys.add(AttributeName.BLEEDFACE);
			keys.add(AttributeName.BLEEDFOOT);
			keys.add(AttributeName.BLEEDHEAD);
			keys.add(AttributeName.BLEEDLEFT);
			keys.add(AttributeName.BLEEDRIGHT);
			keys.add(AttributeName.BLEEDSPINE);
			keys.add(AttributeName.BOUNDINGBOX);
			keys.add(AttributeName.CENTER);
			keys.add(AttributeName.CUTBOX);
			keys.add(AttributeName.DIAMETER);
			keys.add(AttributeName.DIMENSION);
			keys.add(ElementName.DIMENSIONS);
			keys.add(AttributeName.EXTENT);
			keys.add(ElementName.FINISHEDDIMENSIONS);
			keys.add(AttributeName.FOLDINGWIDTH);
			keys.add(AttributeName.FOLDINGWIDTH + "Back");
			keys.add(AttributeName.FRONTOVERFOLD);
			keys.add(AttributeName.HEIGHT);
			keys.add(AttributeName.LENGTH);
			keys.add(AttributeName.MILLINGDEPTH);
			keys.add(AttributeName.PITCH);
			keys.add(ElementName.POSITION);
			keys.add(AttributeName.SPINE);
			keys.add(AttributeName.TABEXTENSIONDISTANCE);
			keys.add(AttributeName.THICKNESS);
			keys.add(AttributeName.TRIMBOTTOM);
			keys.add(AttributeName.TRIMBOX);
			keys.add(AttributeName.TRIMFACE);
			keys.add(AttributeName.TRIMFOOT);
			keys.add(AttributeName.TRIMHEAD);
			keys.add(AttributeName.TRIMLEFT);
			keys.add(AttributeName.TRIMRIGHT);
			keys.add(AttributeName.TRIMSIZE);
			keys.add(AttributeName.TRIMTOP);
			keys.add(AttributeName.WIDTH);
		}
		return keys;
	}

	/**
	 * get the factor for one of the units to points
	 *
	 * @param unit
	 * @return
	 */
	public double getFactor(String unit)
	{
		final double factor;
		unit = StringUtil.normalize(unit, true);
		if (UNIT_MM.equals(unit))
		{
			factor = 72. / 25.4;
		}
		else if (UNIT_CM.equals(unit))
		{
			factor = 72. / 2.54;
		}
		else if (UNIT_IN.equals(unit))
		{
			factor = 72.;
		}
		else
		{
			factor = 1.0;
		}
		return factor;
	}

	/**
	 *
	 * @param key the attribute name to check
	 * @param val
	 * @return
	 */
	public String extractUnits(final String key, final String val)
	{
		if (key != null && unitKeys.contains(key))
		{
			return extractUnits(val);
		}
		else
		{
			return val;
		}
	}

	/**
	 * extract units if and only if the string has a pattern of "<##>mm" or "<##>cm"or "<##>in" whitespace characters may be placed between the numbers and the units the unit case
	 * is ignored
	 *
	 * @param val the string to convert
	 * @return the converted unit string
	 */
	public String extractUnits(String val)
	{
		if (val == null)
		{
			return val;
		}

		final VString v = StringUtil.tokenize(val, JDFConstants.BLANK, false);
		final VString keep = new VString(v);
		boolean oneGood = false;
		final int size = v.size();
		for (int i = 0; i < size; i++)
		{
			String tmp = v.get(i).toLowerCase();
			double factor = 1.0;
			if (tmp.endsWith(UNIT_MM))
			{
				factor = 72. / 25.4;
				tmp = StringUtil.leftStr(tmp, -2);
			}
			else if (tmp.endsWith(UNIT_CM))
			{
				factor = 72. / 2.54;
				tmp = StringUtil.leftStr(tmp, -2);
			}
			else if (tmp.endsWith(UNIT_IN))
			{
				factor = 72.;
				tmp = StringUtil.leftStr(tmp, -2);
			}

			if (factor > 0 && i > 0 && tmp == null && StringUtil.isNumber(keep.get(i - 1)))
			{
				if (i < 2 || !JDFConstants.QUOTE.equals(v.get(i - 2)))
				{
					tmp = v.get(i - 1);
					v.setElementAt(JDFConstants.QUOTE, i - 1);
				}
				else
				{
					// we have something like "10 mm mm"" - bail out
					oneGood = false;
					break;
				}
			}
			if (!StringUtil.isNumber(tmp))
			{
				factor = 0;
			}

			if (factor != 0)
			{
				if (factor == 1.0 && (tmp.startsWith("0") || tmp.startsWith("-0")) && StringUtil.isInteger(tmp)) // we want to retain explicit starts with 0
				{
					v.setElementAt(tmp, i);
				}
				else
				{
					oneGood = true;
					final double dbl = StringUtil.parseDouble(tmp, -1) * factor;
					v.setElementAt(new NumberFormatter().formatDouble(dbl, precision), i);
				}
			}
			else
			{
				oneGood = false;
				break;
			}
		}
		if (oneGood)
		{
			val = StringUtil.setvString(v, JDFConstants.BLANK, null, null);
			val = StringUtil.replaceString(val, "\" ", "");
		}
		return val;
	}

	/**
	 * Setter for precision attribute.
	 *
	 * @param precision the precision to set
	 */
	public void setPrecision(final int precision)
	{
		this.precision = precision;
	}

	/**
	 * Getter for precision attribute.
	 *
	 * @return the precision
	 */
	public int getPrecision()
	{
		return precision;
	}

	/**
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString()
	{
		return "UnitParser [precision=" + precision + "]";
	}

}
