/*
 *
 * The CIP4 Software License, Version 1.0
 *
 *
 * Copyright (c) 2001-2025 The International Cooperation for the Integration of
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
import java.util.List;
import java.util.Map.Entry;
import java.util.Set;

import org.cip4.jdflib.core.AttributeInfo.EnumAttributeType;
import org.cip4.jdflib.core.AttributeName;
import org.cip4.jdflib.core.ElementName;
import org.cip4.jdflib.core.JDFConstants;
import org.cip4.jdflib.core.JDFElement;
import org.cip4.jdflib.core.KElement;
import org.cip4.jdflib.core.StringArray;
import org.cip4.jdflib.datatypes.JDFAttributeMap;
import org.cip4.jdflib.extensions.XJDFConstants;
import org.cip4.jdflib.span.JDFSpanBase;

/**
 * class to parse units from strings
 *
 * @author Rainer Prosi, Heidelberger Druckmaschinen *
 */
public class UnitParser
{
	final static Set<String> unitKeys = getUnitKeys();
	private int precision;

	public enum eParserUnit
	{
		in, cm, mm, pt;

		public static eParserUnit getEnum(final String val)
		{
			return JavaEnumUtil.getEnumIgnoreCase(eParserUnit.class, val);
		}

		public static double getFactor(final String val)
		{
			final eParserUnit pu = getEnum(val);
			return pu == null ? 1.0 : pu.getFactor();
		}

		/**
		 * get the factor for one of the units to points
		 *
		 * @param unit
		 * @return
		 */
		public double getFactor()
		{
			switch (this)
			{
			case mm:
				return 72. / 25.4;
			case cm:
				return 72. / 2.54;
			case in:
				return 72.0;
			default:
				return 1.0;
			}
		}
	}

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
	public void convertUnits(final KElement element, final boolean recurse)
	{
		convertUnits(element);
		if (recurse)
		{
			final List<KElement> v = element.getChildList();
			for (final KElement e : v)
			{
				convertUnits(e, recurse);
			}
		}
	}

	/**
	 * @param element
	 */
	public void convertUnits(final KElement element)
	{
		final JDFAttributeMap map = element.getAttributeMap_KElement();
		final boolean elemSpan = (element instanceof JDFSpanBase) && isUnit(element.getLocalName());
		for (final Entry<String, String> e : map.entrySet())
		{

			final String key = e.getKey();
			if (!elemSpan && !isUnit(key))
			{
				continue;
			}

			final String val = e.getValue();
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
			keys.add(AttributeName.ABSOLUTEBOX);
			keys.add(AttributeName.ABSOLUTEHEIGHT);
			keys.add(AttributeName.ABSOLUTEWIDTH);
			keys.add(AttributeName.ADVANCEDISTANCE);
			keys.add(AttributeName.BACKING);
			keys.add(AttributeName.BACKOVERFOLD);
			keys.add(AttributeName.BLANKDIMENSIONSX);
			keys.add(AttributeName.BLANKDIMENSIONSY);
			keys.add(AttributeName.BLEEDBOTTOM);
			keys.add(AttributeName.BLEEDFACE);
			keys.add(AttributeName.BLEEDFOOT);
			keys.add(AttributeName.BLEEDHEAD);
			keys.add(AttributeName.BLEEDLEFT);
			keys.add(AttributeName.BLEEDRIGHT);
			keys.add(AttributeName.BLEEDSPINE);
			keys.add(AttributeName.BOTTOMFOLDIN);
			keys.add(AttributeName.BOX);
			keys.add(AttributeName.BOUNDINGBOX);
			keys.add(AttributeName.BURNOUTAREA);
			keys.add(AttributeName.CARTONTOPFLAPS);
			keys.add(AttributeName.CASERADIUS);
			keys.add(AttributeName.CENTER);
			keys.add(AttributeName.CLAMPSIZE);
			keys.add(AttributeName.CLIPBOX);
			keys.add(AttributeName.COVERWIDTH);
			keys.add(AttributeName.CUTBOX);
			keys.add(AttributeName.CUTWIDTH);
			keys.add(AttributeName.DIAMETER);
			keys.add(AttributeName.DIMENSION);
			keys.add(ElementName.DIMENSIONS);
			keys.add(XJDFConstants.ExpansionBox);
			keys.add(AttributeName.EXTENT);
			keys.add(ElementName.FINISHEDDIMENSIONS);
			keys.add(AttributeName.FLATDIMENSIONS);
			keys.add(AttributeName.FOLDINGDISTANCE);
			keys.add(AttributeName.FOLDINGWIDTH);
			keys.add(AttributeName.FOLDINGWIDTH + "Back");
			keys.add(AttributeName.FRONTFOLDIN);
			keys.add(AttributeName.FRONTOVERFOLD);
			keys.add(AttributeName.GLUELINEWIDTH);
			keys.add(AttributeName.GLUINGPATTERN);
			keys.add(AttributeName.GUTTERX);
			keys.add(AttributeName.GUTTERX2);
			keys.add(AttributeName.GUTTERY);
			keys.add(AttributeName.GUTTERY2);
			keys.add(AttributeName.HEIGHT);
			keys.add(AttributeName.HORIZONTALEXCESS);
			keys.add(AttributeName.HORIZONTALEXCESSBACK);
			keys.add(AttributeName.INNERCOREDIAMETER);
			keys.add(AttributeName.INNERDIMENSIONS);
			keys.add(AttributeName.JOINTWIDTH);
			keys.add(AttributeName.KNOCKOUTBLEED);
			keys.add(AttributeName.LAMINATINGBOX);
			keys.add(AttributeName.LENGTH);
			keys.add(AttributeName.LENGTHOVERALL);
			keys.add(AttributeName.MARGINBOTTOM);
			keys.add(AttributeName.MARGINLEFT);
			keys.add(AttributeName.MARGINRIGHT);
			keys.add(AttributeName.MARGINTOP);
			keys.add(AttributeName.MAXHEIGHT);
			keys.add(AttributeName.MILLINGDEPTH);
			keys.add(AttributeName.MINGUTTER);
			keys.add(AttributeName.NEEDLEPOSITIONS);
			keys.add(AttributeName.NIPWIDTH);
			keys.add(AttributeName.NOTCHINGDEPTH);
			keys.add(AttributeName.NOTCHINGDISTANCE);
			keys.add(AttributeName.OFFSET);
			keys.add(AttributeName.OUTERCOREDIAMETER);
			keys.add(AttributeName.OVERFOLD);
			keys.add(AttributeName.OVERHANG);
			keys.add(AttributeName.OVERHANGOFFSET);
			keys.add(AttributeName.PITCH);
			keys.add(ElementName.POSITION);
			keys.add(AttributeName.ROLLCUT);
			keys.add(AttributeName.ROLLDIAMETER);
			keys.add(AttributeName.ROUNDING);
			keys.add(AttributeName.SIZE);
			keys.add(AttributeName.SPINE);
			keys.add(AttributeName.SPINEWIDTH);
			keys.add(AttributeName.STARTPOSITION);
			keys.add(AttributeName.STITCHPOSITIONS);
			keys.add(AttributeName.STITCHWIDTH);
			keys.add(AttributeName.STRAPPOSITIONS);
			keys.add(AttributeName.STRIPLENGTH);
			keys.add(AttributeName.SURFACECONTENTSBOX);
			keys.add(AttributeName.TABEXTENSIONDISTANCE);
			keys.add(AttributeName.TABOFFSET);
			keys.add(AttributeName.TABWIDTH);
			keys.add(AttributeName.THICKNESS);
			keys.add(AttributeName.THREADLENGTH);
			keys.add(AttributeName.THREADPOSITIONS);
			keys.add(AttributeName.THREADSTITCHWIDTH);
			keys.add(AttributeName.THREADTHICKNESS);
			keys.add(AttributeName.TOPEXCESS);
			keys.add(AttributeName.TOPFOLDIN);
			keys.add(AttributeName.TOTALDIMENSIONS);
			keys.add(AttributeName.TRAVEL);
			keys.add(AttributeName.TRIMBOTTOM);
			keys.add(AttributeName.TRIMBOX);
			keys.add(AttributeName.TRIMFACE);
			keys.add(AttributeName.TRIMFOOT);
			keys.add(AttributeName.TRIMHEAD);
			keys.add(AttributeName.TRIMLEFT);
			keys.add(AttributeName.TRIMMINGOFFSET);
			keys.add(AttributeName.TRIMRIGHT);
			keys.add(AttributeName.TRIMSIZE);
			keys.add(AttributeName.TRIMTOP);
			// keys.add(AttributeName.VALUE);
			keys.add(AttributeName.VISIBLELENGTH);
			keys.add(AttributeName.WIDTH);
			keys.add(AttributeName.WIREGAUGE);
			keys.add(AttributeName.WORKINGDIRECTION);
			keys.add(AttributeName.WORKINGLENGTH);
			keys.add(AttributeName.WORKINGPATH);
			keys.add(AttributeName.ZONEHEIGHT);
			keys.add(AttributeName.ZONEWIDTH);
		}
		return keys;
	}

	/**
	 * get the factor for one of the units to points
	 *
	 * @param unit
	 * @return
	 */
	public double getFactor(final String unit)
	{
		return eParserUnit.getFactor(unit);
	}

	/**
	 *
	 * @param key the attribute name to check
	 * @param val
	 * @return
	 */
	public String extractUnits(final String key, final String val)
	{
		if (ContainerUtil.contains(unitKeys, key))
		{
			return extractUnits(val);
		}
		else
		{
			return val;
		}
	}

	public String getUnitString(final String key, final eParserUnit pu, final String val0, final String sep)
	{
		if (isUnit(key))
			return getUnitString(pu, val0, sep);
		else
			return val0;
	}

	private static final UnitParser roundTrip = getRoundTrip();

	/**
	 * extract units if and only if the string has a pattern of "<##>mm" or "<##>cm"or "<##>in" whitespace characters may be placed between the numbers and the units the unit case
	 * is ignored
	 *
	 * @param val the string to convert
	 * @return the converted unit string
	 */
	public String getUnitString(final eParserUnit pu, final String val0, final String sep)
	{

		final String val = roundTrip.extractUnits(val0);
		if (val == null)
		{
			return val;
		}

		final StringArray v = StringArray.getVString(val, null);
		final StringBuilder ret = new StringBuilder();
		boolean first = true;
		for (final String v0 : v)
		{
			if (StringUtil.isNumber(v0))
			{
				if (!first)
				{
					ret.append(' ');
				}
				final double d = StringUtil.parseDouble(v0, 0);
				ret.append(getNoUnitString(pu, d, sep));
				first = false;
			}
			else
			{
				return val0;
			}
		}

		return ret.toString();
	}

	private static UnitParser getRoundTrip()
	{
		final UnitParser p = new UnitParser();
		p.setPrecision(42);
		return p;
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

		final StringArray v = StringArray.getVString(val, null);
		final StringArray keep = new StringArray(v);
		boolean oneGood = false;
		final int size = v.size();
		for (int i = 0; i < size; i++)
		{
			String tmp = v.get(i).toLowerCase();
			double factor = 1.0;
			final eParserUnit pu = eParserUnit.getEnum(StringUtil.rightStr(tmp, 2));
			if (pu != null)
			{
				factor = pu.getFactor();
				tmp = StringUtil.leftStr(tmp, -2);
			}

			if (factor > 0 && i > 0 && tmp == null && StringUtil.isNumber(keep.get(i - 1)))
			{
				if (i < 2 || !JDFConstants.QUOTE.equals(v.get(i - 2)))
				{
					tmp = v.get(i - 1);
					v.set(i - 1, JDFConstants.QUOTE);
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
					v.set(i, tmp);
				}
				else
				{
					oneGood = true;
					final double dbl = StringUtil.parseDouble(tmp, -1) * factor;
					v.set(i, new NumberFormatter().formatDouble(dbl, precision));
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
	 * getter for unit attribute
	 *
	 * @param precision the precision to set
	 */
	public String getNoUnitString(final eParserUnit unit, final double points, final String separator)
	{
		return StringUtil.formatDouble(points / unit.getFactor(), precision) + separator + unit.name();
	}

	public String getNoUnitString(final eParserUnit unit, final double points)
	{
		return getNoUnitString(unit, points, " ");
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
