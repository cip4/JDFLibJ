/*
 * The CIP4 Software License, Version 1.0
 *
 *
 * Copyright (c) 2001-2013 The International Cooperation for the Integration of
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
/**
 *
 * Copyright (c) 2005 Heidelberger Druckmaschinen AG, All Rights Reserved.
 *
 * AtrInfo.java
 * 04022005 VF initial version
 */
package org.cip4.jdflib.core;

import java.util.zip.DataFormatException;

import org.apache.commons.lang.enums.ValuedEnum;
import org.cip4.jdflib.core.JDFElement.EnumVersion;
import org.cip4.jdflib.datatypes.JDFIntegerList;
import org.cip4.jdflib.datatypes.JDFIntegerRange;
import org.cip4.jdflib.datatypes.JDFIntegerRangeList;
import org.cip4.jdflib.datatypes.JDFNumberRange;
import org.cip4.jdflib.datatypes.JDFNumberRangeList;
import org.cip4.jdflib.datatypes.JDFXYPair;
import org.cip4.jdflib.datatypes.JDFXYPairRange;
import org.cip4.jdflib.datatypes.JDFXYPairRangeList;
import org.cip4.jdflib.util.StringUtil;

/**
 * @author Dr. Rainer Prosi, Heidelberger Druckmaschinen AG
 * 
 * Aug 10, 2009
 */
public class AtrInfo
{
	private final long atrValidityStatus;
	private final AttributeInfo.EnumAttributeType atrType;
	private ValuedEnum enumEnum = null;
	private String atrDefault = null;

	/**
	 * @param s
	 * @param t
	 * @param e
	 * @param _atrDefault
	 */
	public AtrInfo(final long s, final AttributeInfo.EnumAttributeType t, final ValuedEnum e, final String _atrDefault)
	{
		atrValidityStatus = s;
		atrType = t;
		enumEnum = e;
		atrDefault = _atrDefault;
	}

	/**
	 * @param s
	 * @param t
	 * @param e
	 */
	public AtrInfo(final long s, final AttributeInfo.EnumAttributeType t, final ValuedEnum e)
	{
		atrValidityStatus = s;
		atrType = t;
		enumEnum = e;
		atrDefault = null;
	}

	/**
	 * checks whether smallAtt is a matching subset of bigAtt depending on datatype
	 * 
	 * @param smallAtt the small att to test
	 * @param bigAtt the big att, e.g. list to test against
	 * @param dataType the datatype of the big att
	 * 
	 * @return true if smallAtt is a matching subset of bigAtt
	 */
	public static boolean matchesAttribute(final String smallAtt, final String bigAtt, final AttributeInfo.EnumAttributeType dataType)
	{
		if (dataType == null || dataType.equals(AttributeInfo.EnumAttributeType.Any))
		{
			return bigAtt.equals(smallAtt);
		}

		if ((dataType.equals(AttributeInfo.EnumAttributeType.NMTOKENS)) || (dataType.equals(AttributeInfo.EnumAttributeType.enumerations))
				|| (dataType.equals(AttributeInfo.EnumAttributeType.IDREFS)))
		{
			// check for matching individual NMTOKEN
			if (smallAtt.indexOf(" ") > 0)
			{
				final VString vSmall = StringUtil.tokenize(smallAtt, JDFConstants.BLANK, false);
				for (int i = 0; i < vSmall.size(); i++)
				{
					if (!StringUtil.hasToken(bigAtt, vSmall.get(i), JDFConstants.BLANK, 0))
					{
						return false;
					}
				}
			}
			else if (!StringUtil.hasToken(bigAtt, smallAtt, JDFConstants.BLANK, 0))
			{
				return false;
			}

			return true;
		}

		if (dataType.equals(AttributeInfo.EnumAttributeType.NumberRange))
		{
			try
			{
				final JDFNumberRange r = new JDFNumberRange(bigAtt);
				if (r.inRange(Double.parseDouble(smallAtt)))
				{
					return true;
				}
			}
			catch (final DataFormatException nfe)
			{
				// do nothing
			}
			catch (final JDFException jdfe)
			{
				// do nothing
			}
			catch (final IllegalArgumentException iae)
			{
				// do nothing
			}
		}

		if (dataType.equals(AttributeInfo.EnumAttributeType.NumberRangeList))
		{
			try
			{
				final JDFNumberRangeList r = new JDFNumberRangeList(bigAtt);
				if (r.inRange(Double.parseDouble(smallAtt)))
				{
					return true;
				}
			}
			catch (final DataFormatException nfe)
			{
				// do nothing
			}
			catch (final JDFException jdfe)
			{
				// do nothing
			}
			catch (final IllegalArgumentException iae)
			{
				// do nothing
			}
		}

		if (dataType.equals(AttributeInfo.EnumAttributeType.IntegerList))
		{
			try
			{
				if (StringUtil.isInteger(smallAtt))
				{
					final JDFIntegerList rBig = new JDFIntegerList(bigAtt);
					if (rBig.contains(StringUtil.parseInt(smallAtt, 0)))
					{
						return true;
					}
				}
			}
			catch (final DataFormatException nfe)
			{
				// do nothing
			}
			catch (final JDFException jdfe)
			{
				// do nothing
			}
			catch (final IllegalArgumentException iae)
			{
				// do nothing
			}
		}
		if (dataType.equals(AttributeInfo.EnumAttributeType.IntegerRange))
		{
			try
			{
				final JDFIntegerRange rBig = new JDFIntegerRange(bigAtt, Integer.MAX_VALUE);
				final JDFIntegerRange rSmal = new JDFIntegerRange(smallAtt, Integer.MAX_VALUE);
				if (rBig.isPartOfRange(rSmal))
				{
					return true;
				}
			}
			catch (final DataFormatException nfe)
			{
				// do nothing
			}
			catch (final JDFException jdfe)
			{
				// do nothing
			}
			catch (final IllegalArgumentException iae)
			{
				// do nothing
			}
		}

		if (dataType.equals(AttributeInfo.EnumAttributeType.IntegerRangeList))
		{
			try
			{
				final JDFIntegerRangeList rBig = new JDFIntegerRangeList(bigAtt, Integer.MAX_VALUE);
				final JDFIntegerRangeList rSmal = new JDFIntegerRangeList(smallAtt, Integer.MAX_VALUE);
				if (rBig.isPartOfRange(rSmal))
				{
					return true;
				}
			}
			catch (final DataFormatException nfe)
			{
				// do nothing
			}
			catch (final JDFException jdfe)
			{
				// do nothing
			}
			catch (final IllegalArgumentException iae)
			{
				// do nothing
			}
		}

		if (dataType.equals(AttributeInfo.EnumAttributeType.XYPairRange))
		{
			try
			{
				final JDFXYPair xypair = new JDFXYPair(smallAtt);
				final JDFXYPairRange r = new JDFXYPairRange(bigAtt);
				if (r.inRange(xypair))
				{
					return true;
				}
			}
			catch (final DataFormatException x)
			{
				// do nothing
			}
			catch (final IllegalArgumentException x1)
			{
				// do nothing
			}
		}

		if (dataType.equals(AttributeInfo.EnumAttributeType.XYPairRangeList))
		{
			try
			{
				final JDFXYPair xypair = new JDFXYPair(smallAtt);
				final JDFXYPairRangeList r = new JDFXYPairRangeList(bigAtt);
				if (r.inRange(xypair))
				{
					return true;
				}
			}
			catch (final DataFormatException x)
			{
				// do nothing
			}
			catch (final IllegalArgumentException x1)
			{
				// do nothing
			}
		}

		return false;
	}

	/**
	 * @return Returns the atrType.
	 */
	public AttributeInfo.EnumAttributeType getAtrType()
	{
		return atrType;
	}

	/**
	 * @return Returns the atrValidityStatus.
	 */
	public long getAtrValidityStatus()
	{
		return atrValidityStatus;
	}

	/**
	 * @return the enum used for type declaration
	 */
	public ValuedEnum getEnumEnum()
	{
		return enumEnum;
	}

	/**
	 * @return the default value, null if none set
	 */
	public String getAtrDefault()
	{
		return atrDefault;
	}

	/**
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString()
	{
		String s = "Type: " + atrType.toString();
		s += "; Validity: " + Long.toHexString(atrValidityStatus);
		if (enumEnum != null)
		{
			s += "; Enum: " + enumEnum.toString();
		}
		if (atrDefault != null)
		{
			s += "; default: " + atrDefault;
		}

		return s;
	}

	/**
	 * get the first jdf version where an attribute of this type is valid
	 * 
	 * @return the first valid version
	 */
	public EnumVersion getFirstVersion()
	{
		for (int i = 0; i < 8; i++)
		{
			long masked = atrValidityStatus & (0xFl << (4 * i));
			masked = masked >> (4 * i);
			if (masked == 2 || masked == 3)
			{
				return EnumVersion.getEnum(i + 1);
			}
		}
		return null;
	}

	/**
	 * get the last jdf version where an attribute of this type is valid
	 * 
	 * @return the last valid version
	 */
	public EnumVersion getLastVersion()
	{
		for (int i = 7; i >= 0; i--)
		{
			long masked = atrValidityStatus & 0xFl << (4 * i);
			masked = masked >> (4 * i);
			if (masked == 2 || masked == 3)
			{
				// dirty hack to allow FixVersion with 2.0, if all bits, we also assume jdf 2.0 (xjdf)
				if (i == 7)
					i += 3;

				return EnumVersion.getEnum(i + 1);
			}
		}
		return null;
	}
}
