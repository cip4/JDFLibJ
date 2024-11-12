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

import java.util.Iterator;
import java.util.Vector;

import org.apache.commons.lang.enums.EnumUtils;
import org.apache.commons.lang.enums.ValuedEnum;
import org.cip4.jdflib.core.StringArray;
import org.cip4.jdflib.core.VString;

/**
 * class with utilities for enums
 *
 * @author prosirai
 *
 */
public class EnumUtil
{
	private EnumUtil()
	{
		super();
	}

	private static final String NULL = "null";

	/**
	 * get a vector of names in an iteration
	 *
	 * @param e any member of the enum to iterate over
	 * @return VString - the vector of enum names
	 */
	@SuppressWarnings("unchecked")
	public static VString getNamesVector(final Class<? extends ValuedEnum> e)
	{
		if (e == null)
			return null;

		final VString namesVector = new VString();
		final Iterator<ValuedEnum> it = EnumUtils.iterator(e);
		while (it.hasNext())
		{
			namesVector.addElement(it.next().getName());
		}

		return namesVector;
	}

	/**
	 * get a vector of names in an iteration
	 *
	 * @param e any member of the enum to iterate over
	 * @return VString - the vector of enum names
	 */
	@SuppressWarnings("unchecked")
	public static StringArray getNamesList(final Class<? extends ValuedEnum> e)
	{
		if (e == null)
			return null;

		final StringArray list = new StringArray();
		final Iterator<ValuedEnum> it = EnumUtils.iterator(e);
		while (it.hasNext())
		{
			list.add(it.next().getName());
		}

		return list;
	}

	/**
	 * get a vector of elements in an iteration
	 *
	 * @param e any member of the enum to iterate over
	 * @return Vector - the vector of enum instances
	 */
	@SuppressWarnings("unchecked")
	public static Vector<ValuedEnum> getEnumsVector(final Class<? extends ValuedEnum> e)
	{
		final Vector<ValuedEnum> v = new Vector<>();
		final Iterator<ValuedEnum> it = EnumUtils.iterator(e);
		while (it.hasNext())
		{
			v.addElement(it.next());
		}
		return v;
	}

	/**
	 * get the lower of two enum values, null is lowest
	 *
	 * @param e1
	 * @param e2
	 * @return the lower of the two values
	 */
	public static ValuedEnum min(final ValuedEnum e1, final ValuedEnum e2)
	{
		if (e1 == null || e2 == null)
		{
			return null;
		}
		return e1.getValue() < e2.getValue() ? e1 : e2;
	}

	/**
	 * get the higher of two enum values, null is lowest
	 *
	 * @param e1
	 * @param e2
	 * @return the higher of the two values
	 */
	public static ValuedEnum max(final ValuedEnum e1, final ValuedEnum e2)
	{
		if (e1 == null)
		{
			return e2;
		}
		if (e2 == null)
		{
			return e1;
		}
		return e1.getValue() > e2.getValue() ? e1 : e2;
	}

	/**
	 * null save convenience name getter
	 *
	 * @param en the enum to get the name of the class
	 * @return
	 */
	public static String getName(final ValuedEnum en)
	{
		return en == null ? NULL : en.getName();
	}

	/**
	 * null save convenience name getter
	 *
	 * @param en the enum to get the name
	 * @return
	 */
	public static String getEnumName(final ValuedEnum en)
	{
		return en == null ? null : StringUtil.token(en.getClass().getName(), -1, "$");
	}

	/**
	 * checks whether the value of an enum is less than another <br/>
	 * null is always smaller
	 *
	 * @param a the first enum; if this is smaller we return true
	 * @param b the second enum
	 * @return boolean a is < b
	 */
	public static boolean aLessThanB(final ValuedEnum a, final ValuedEnum b)
	{
		final int aa = getValue(a);
		final int bb = getValue(b);
		return aa < bb;
	}

	public static int getValue(final ValuedEnum a)
	{
		return a == null ? -1 : a.getValue();
	}

	/**
	 * checks whether the value of an enum is less or equal to another <br/>
	 * null is always smaller
	 *
	 * @param a the first enum; if this is smaller we return true
	 * @param b the second enum
	 * @return boolean a is <= b
	 */
	public static boolean aLessEqualsThanB(final ValuedEnum a, final ValuedEnum b)
	{
		final int aa = getValue(a);
		final int bb = getValue(b);
		return aa <= bb;
	}

	/**
	 * get enum ignoring case
	 *
	 * @param clazz the jdflib valued enum class
	 * @param e the enum
	 * @return
	 */
	public static ValuedEnum getEnumIgnoreCase(final Class<? extends ValuedEnum> clazz, final Enum<?> e)
	{
		final String s = e == null ? null : e.name();
		return getEnumIgnoreCase(clazz, s);
	}

	/**
	 * @param clazz
	 * @param s
	 * @return
	 */
	public static ValuedEnum getEnumIgnoreCase(final Class<? extends ValuedEnum> clazz, final String s)
	{
		if (!StringUtil.isEmpty(s) && clazz != null)
		{
			@SuppressWarnings("unchecked")
			final Iterator<ValuedEnum> it = EnumUtils.iterator(clazz);
			while (it.hasNext())
			{
				final ValuedEnum next = it.next();
				if (s.equalsIgnoreCase(next.getName()))
				{
					return (ValuedEnum) EnumUtils.getEnum(clazz, next.getName());
				}
			}
		}
		return null;
	}

	/**
	 * @param <T>
	 * @param val
	 * @param c
	 * @return
	 */
	public static <T extends Enum<T>> T getJavaEnumIgnoreCase(final Class<T> c, final String val)
	{
		return JavaEnumUtil.getEnumIgnoreCase(c, val, null);
	}

}
