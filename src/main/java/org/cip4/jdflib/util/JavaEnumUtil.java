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

import java.util.List;

import org.cip4.jdflib.core.StringArray;

/**
 * class with utilities for enums
 *
 * @author prosirai
 *
 */
public class JavaEnumUtil
{

	private JavaEnumUtil()
	{
		super();
	}

	/**
	 * get the lower of two enum values, null is lowest
	 *
	 * @param e1
	 * @param e2
	 * @return the lower of the two values
	 */
	public static <T extends Enum<T>> T min(final T e1, final T e2)
	{
		if (e1 == null || e2 == null)
		{
			return null;
		}
		return e1.ordinal() < e2.ordinal() ? e1 : e2;
	}

	/**
	 * get the higher of two enum values, null is lowest
	 *
	 * @param e1
	 * @param e2
	 * @return the higher of the two values
	 */
	public static <T extends Enum<T>> T max(final T e1, final T e2)
	{
		if (e1 == null)
		{
			return e2;
		}
		if (e2 == null)
		{
			return e1;
		}
		return e1.ordinal() > e2.ordinal() ? e1 : e2;
	}

	/**
	 * null safe convenience name getter
	 *
	 * @param en the enum to get the name of the class
	 * @return
	 */
	public static String getName(final Enum<?> en)
	{
		return en == null ? null : en.name();
	}

	/**
	 * checks whether the value of an enum is less than another <br/>
	 * null is always smaller
	 *
	 * @param a the first enum; if this is smaller we return true
	 * @param b the second enum
	 * @return boolean a is < b
	 */
	public static <T extends Enum<T>> boolean aLessThanB(final T a, final T b)
	{
		final int aa = a == null ? -1 : a.ordinal();
		final int bb = b == null ? -1 : b.ordinal();
		return aa < bb;
	}

	/**
	 * checks whether the value of an enum is less or equal to another <br/>
	 * null is always smaller
	 *
	 * @param a the first enum; if this is smaller we return true
	 * @param b the second enum
	 * @return boolean a is <= b
	 */
	public static <T extends Enum<T>> boolean aLessEqualsThanB(final T a, final T b)
	{
		final int aa = a == null ? -1 : a.ordinal();
		final int bb = b == null ? -1 : b.ordinal();
		return aa <= bb;
	}

	/**
	 * @param <T>
	 * @param val
	 * @param c
	 * @return
	 */
	public static <T extends Enum<T>> T getEnumIgnoreCase(final Class<T> c, final String val)
	{
		return getEnumIgnoreCase(c, val, null);
	}

	/**
	 * @param c
	 * @param val
	 * @param def the default
	 * @param <T>
	 * @return
	 */
	public static <T extends Enum<T>> T getEnumIgnoreCase(final Class<T> c, String val, final T def)
	{
		val = StringUtil.trim(val, null);
		if (!StringUtil.isEmpty(val))
		{
			try
			{
				return Enum.valueOf(c, val);
			}
			catch (final Exception x)
			{
				final T[] vals = c.getEnumConstants();
				if (vals != null)
				{
					for (final T e : vals)
					{
						if (e.name().equalsIgnoreCase(val))
						{
							return e;
						}
					}
				}
			}
		}
		return def;
	}

	/**
	 * 
	 * @param <T>
	 * @param c
	 * @return
	 */
	public static <T extends Enum<T>> List<String> getNamesList(final Class<T> c)
	{
		final T[] vals = c == null ? null : c.getEnumConstants();
		if (vals != null)
		{
			final StringArray ret = new StringArray();
			for (final T e : vals)
			{
				ret.add(e.name());
			}
			return ret;
		}
		return null;
	}
}
