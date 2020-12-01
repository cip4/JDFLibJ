/*
 *
 * The CIP4 Software License, Version 1.0
 *
 *
 * Copyright (c) 2001-2019 The International Cooperation for the Integration of Processes in Prepress, Press and Postpress (CIP4). All rights reserved.
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
/**
 *
 * Copyright (c) 2001 Heidelberger Druckmaschinen AG, All Rights Reserved.
 *
 * vString.java
 *
 * Last changes
 *
 */
package org.cip4.jdflib.core;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.Set;
import java.util.StringTokenizer;

import org.cip4.jdflib.datatypes.StringCache;
import org.cip4.jdflib.util.ContainerUtil;
import org.cip4.jdflib.util.StringUtil;

/**
 *
 */
public class StringArray extends ArrayList<String>
{
	private static final long serialVersionUID = 1L;

	/**
	 * are we null or empty or contain only an empty JDFAttributeMap
	 *
	 * @param v
	 * @return
	 */
	public static boolean isEmpty(final Collection<String> v)
	{
		return StringUtil.isEmpty(v);
	}

	/**
	 * constructor
	 */
	public StringArray()
	{
		super();
	}

	/**
	 * constructor
	 *
	 * @param m
	 */
	public StringArray(final Collection<String> m)
	{
		super();
		if (m != null)
		{
			addAll(m);
		}
	}

	/**
	 * convenience - constructs a VString by tokenizing a string
	 *
	 * @param strIn the string to tokenize by blank
	 *
	 */
	public StringArray(final String strIn)
	{
		this(strIn, null);
	}

	/**
	 * simple static factory - null if strIn is null or empty
	 *
	 * @param strIn
	 * @param strSep
	 * @return
	 */
	public static StringArray getVString(final String strIn, final String strSep)
	{
		return StringUtil.getNonEmpty(strIn) == null ? null : new StringArray(strIn, strSep);
	}

	/**
	 *
	 * constructs a VString by tokenizing a string
	 *
	 * @param strIn the string to tokenize
	 * @param strSep the separator character
	 */
	public StringArray(final String strIn, String strSep)
	{
		super();
		if (!StringUtil.isEmpty(strIn))
		{
			if (strSep == null)
			{
				strSep = JDFCoreConstants.BLANK;
			}
			if (strSep.length() == 1 && strIn.indexOf(strSep) < 0)
			{
				add(strIn);
				return;
			}

			final StringTokenizer sToken = new StringTokenizer(strIn, strSep);
			while (sToken.hasMoreTokens())
			{
				add(StringCache.getString(sToken.nextToken()));
			}
		}
	}

	/**
	 * creates a VString from an array of Strings
	 *
	 * @param a the array
	 */
	public StringArray(final String[] a)
	{
		super(a == null ? 0 : a.length);
		final int l = a == null ? 0 : a.length;
		for (int i = 0; i < l; i++)
		{
			add(a[i]);
		}
	}

	/**
	 * @param index the index which may be negative to count backwards
	 * @return the string at index
	 */
	@Override
	public String get(final int index)
	{
		final int i = ContainerUtil.index(this, index);
		return i >= 0 ? super.get(i) : null;
	}

	/**
	 * AppendUnique - append a string but ignore multiple entries
	 *
	 * @param string the string to append, if null nothing is added
	 */
	public void appendUnique(final String string)
	{
		ContainerUtil.appendUnique(this, string);
	}

	/**
	 * AppendUnique - append a vector but ignore multiple entries
	 *
	 * @param v the vector to append, if null nothing is added
	 */
	public void appendUnique(final Collection<String> v)
	{
		if (!StringUtil.isEmpty(v))
		{
			final int size = v.size();
			if (size == 1) // speedup for single append
			{
				final String theOther = v.iterator().next();
				if (!contains(theOther))
				{
					add(theOther);
				}
			}
			else
			{
				addAll(v);
				unify();
			}
		}
	}

	/**
	 * serialize to a string
	 *
	 * @param sep separator between strings
	 * @param front string before the first entry
	 * @param back string after the last entry
	 *
	 * @return a tokenized string
	 */
	public String getString(final String sep, final String front, final String back)
	{
		return StringUtil.setvString(this, sep, front, back);
	}

	/**
	 * serialize to a string
	 *
	 * @param sep separator between strings
	 * @param front string before the first entry
	 * @param back string after the last entry
	 *
	 * @return a tokenized string
	 */
	public String getString()
	{
		return StringUtil.setvString(this, JDFConstants.BLANK, null, null);
	}

	/**
	 * unify - make VString unique, retaining initial order
	 */
	public void unify()
	{
		ContainerUtil.unify(this);
	}

	/**
	 * gets a set with all entries of the VString<br/>
	 * note that the set retains ordering (LinkedHashSet)
	 *
	 * @return the set corresponding to this
	 */
	public Set<String> getSet()
	{
		final HashSet<String> set = new LinkedHashSet<>();
		final Iterator<String> it = iterator();
		while (it.hasNext())
		{
			set.add(it.next());
		}

		return set;
	}

	/**
	 * appends all strings of an array to <code>this</code>
	 *
	 * @param strings the array of strings to append to <code>this</code>
	 */
	public void addAll(final String[] strings)
	{
		ensureCapacity(size() + strings.length);
		ContainerUtil.addAll(this, strings);
	}

	/**
	 * checks whether at least one of a given vector of strings is contained in <code>this</code>
	 *
	 * @param others the VSTring of values to test
	 * @return true if at least one String in other is in <code>this</code>
	 */
	public boolean containsAny(final Collection<String> others)
	{
		return ContainerUtil.containsAny(this, others);
	}

	/**
	 * vector of strings that is contained in <code>this</code>
	 *
	 * @param others the VString of values to test
	 * @return true if at least one String in other is in <code>this</code>
	 */
	public StringArray getOverlapping(final Collection<String> others)
	{
		if (others == null)
		{
			return null;
		}
		final StringArray ret = new StringArray();
		for (final String s : this)
		{
			if (others.contains(s))
			{
				ret.add(s);
			}
		}
		return ret.size() > 0 ? ret : null;
	}

	/**
	 * remove but also implementing the usual neg number syntax
	 *
	 * @param index if 0, or positive count from front, else if negative from back
	 * @see java.util.Vector#remove(int)
	 */
	@Override
	public String remove(final int index)
	{
		final int i = ContainerUtil.index(this, index);
		return i < 0 ? null : super.remove(i);
	}

	/**
	 * add a string if it is not empty
	 *
	 * @param text
	 */
	public void addNonEmpty(final String text)
	{
		final String t2 = StringUtil.getNonEmpty(text);
		if (t2 != null)
			add(t2);

	}

}
