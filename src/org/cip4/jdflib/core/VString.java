/*
 *
 * The CIP4 Software License, Version 1.0
 *
 *
 * Copyright (c) 2001-2010 The International Cooperation for the Integration of 
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
 * Copyright (c) 2001 Heidelberger Druckmaschinen AG, All Rights Reserved.
 *
 * vString.java
 *
 * Last changes
 *
 */
package org.cip4.jdflib.core;

import java.util.Arrays;
import java.util.Enumeration;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.Set;
import java.util.StringTokenizer;
import java.util.Vector;

import org.apache.commons.lang.enums.ValuedEnum;
import org.cip4.jdflib.util.ContainerUtil;
import org.cip4.jdflib.util.StringUtil;

/**
 *
 */
public class VString extends Vector<String>
{
	private static final long serialVersionUID = 1L;
	/**
	 * the empty VString
	 */
	final public static VString emptyVector = new VString();

	// **************************************** Constructors ****************************************
	/**
	 * constructor
	 */
	public VString()
	{
		super();
	}

	/**
	 * constructor
	 * 
	 * @param m
	 */
	public VString(final Vector<String> m)
	{
		super();
		if (m != null)
		{
			addAll(m);
		}
	}

	/**
	 * constructs a VString by tokenizing a string
	 * @param strIn the string to tokenize by blank
	 * @deprecated use VString (String strIn, null)
	 */
	@Deprecated
	public VString(final String strIn)
	{
		this(strIn, null);
	}

	/**
	 * 
	 * constructs a VString by tokenizing a string
	 * @param strIn the string to tokenize
	 * @param strSep the separator character
	 */
	public VString(final String strIn, String strSep)
	{
		super();
		if (strIn != null)
		{
			if (strSep == null)
			{
				strSep = JDFCoreConstants.BLANK;
			}

			final StringTokenizer sToken = new StringTokenizer(strIn, strSep);
			while (sToken.hasMoreTokens())
			{
				this.addElement(sToken.nextToken());
			}
		}
	}

	/**
	 * creates a VString from an array of Strings
	 * @param a the array
	 */
	public VString(final String[] a)
	{
		super(a.length);
		for (int i = 0; i < a.length; i++)
		{
			add(a[i]);
		}
	}

	/**
	 * creates a VString from an Enumeration of Strings
	 * @param a the Enumeration
	 */
	public VString(Enumeration<String> a)
	{
		super();
		while (a.hasMoreElements())
			add(a.nextElement());
	}

	// **************************************** Methods *********************************************

	/**
	 * @param index the index which may be negative to count backwards
	 * @return the string at index
	 */
	public String stringAt(int index)
	{
		if (index < 0)
		{
			index += size();
		}
		return super.elementAt(index);
	}

	/**
	 * @param index the index which may be negative to count backwards
	 * @return the string at index
	 */
	@Override
	public String elementAt(int index)
	{
		if (index < 0)
		{
			index += size();
		}
		return super.elementAt(index);
	}

	/**
	 * @param index the index which may be negative to count backwards
	 * @return the string at index
	 */
	@Override
	public String get(int index)
	{
		if (index < 0)
		{
			index += size();
		}
		return super.get(index);
	}

	/**
	 * toString
	 * 
	 * @return String
	 */
	@Override
	public String toString()
	{
		return "vString[ --> " + super.toString() + " ]";
	}

	/**
	 * Method getAllStrings - returns all strings concatenated together
	 * @param strSep separation between the strings
	 * @return String
	 * @deprecated use getString(strSep,null,null)
	 */
	@Deprecated
	public String getAllStrings(final String strSep)
	{
		return StringUtil.setvString(this, strSep, null, null);
	}

	/**
	 * @return all strings separated by a blank
	 * @deprecated use getString(JDFConstants.BLANK,null,null)
	 */
	@Deprecated
	public String getAllStrings()
	{
		return StringUtil.setvString(this, JDFCoreConstants.BLANK, null, null);
	}

	/**
	 * Method setAllStrings - put a separated string into the vString<br>
	 * e.g. "asdf asdf asdf asdf"
	 * @param strIn separated string
	 * @param strSep string separator
	 */
	public void setAllStrings(final String strIn, final String strSep)
	{
		if ((strIn != null) && (strSep != null))
		{
			this.clear();
			final StringTokenizer sToken = new StringTokenizer(strIn, strSep);

			while (sToken.hasMoreTokens())
			{
				this.addElement(sToken.nextToken());
			}
		}
	}

	/**
	 * index - get the index of s in the vector
	 * 
	 * @param s
	 * 
	 * @return int the index of a string
	 */
	public int index(final String s)
	{
		if (s == null)
		{
			return -1;
		}
		final int siz = size();
		for (int i = 0; i < siz; i++)
		{
			if (s.equals(super.elementAt(i)))
			{
				return i;
			}
		}
		return -1;
	}

	/**
	 * hasString - is 's' a member of <code>this</code>?
	 * 
	 * @param s string to find
	 * 
	 * @return boolean - true, if 's' is included in <code>this</code>
	 * @deprecated 2005-02-14 use contains ...
	 */
	@Deprecated
	public boolean hasString(final String s)
	{
		return index(s) >= 0;
	}

	/**
	 * AppendUnique - append a string but ignore multiple entries
	 * 
	 * @param v the string to append, if null nothing is added
	 */
	public void appendUnique(final String v)
	{
		if (v == null)
		{
			return;
		}

		if (!contains(v))
		{
			addElement(v);
		}
	}

	/**
	 * AppendUnique - append a vector but ignore multiple entries
	 * 
	 * @param v the vector to append, if null nothing is added
	 */
	public void appendUnique(final VString v)
	{
		if (v == null || v.size() == 0)
		{
			return;
		}
		final int size = v.size();
		if (size == 1) // speedup for single append
		{
			final String theOther = v.elementAt(0);
			if (!this.contains(theOther))
			{
				add(theOther);
			}
			return;
		}
		for (int i = 0; i < size; i++)
		{
			add(v.elementAt(i));
		}

		unify();
	}

	/**
	 * removeStrings - remove all occurrences of a string
	 * 
	 * @param v
	 * @deprecated use removeStrings(v, Integer.MAX_VALUE);
	 */
	@Deprecated
	public void removeStrings(final VString v)
	{
		removeStrings(v, Integer.MAX_VALUE);
	}

	/**
	 * removeStrings - remove all occurrences of a string
	 * 
	 * @param v the vector of strings to remove from <code>this</code>
	 * @param nMax the max number of strings to remove
	 */
	public void removeStrings(final VString v, int nMax)
	{
		if (v == null || v.size() == 0)
		{
			return;
		}

		for (int i = this.size() - 1; i >= 0; i--)
		{
			if (v.contains(this.elementAt(i)))
			{
				this.removeElementAt(i);
				nMax--;
				if (nMax == 0)
					break;
			}
		}
	}

	/**
	 * removeStrings - remove all occurrences of a string
	 * 
	 * @param s
	 * @deprecated use removeStrings(s, Integer.MAX_VALUE);
	 */
	@Deprecated
	public void removeStrings(final String s)
	{
		removeStrings(s, Integer.MAX_VALUE);
	}

	/**
	 * removeStrings - remove nMax occurrences of a string
	 * 
	 * @param s the string to remove
	 * @param nMax remove s max. nMax times , 0 or negative = infinite
	 */
	public void removeStrings(final String s, int nMax)
	{
		if (s == null)
			return;
		for (int i = this.size() - 1; i >= 0; i--)
		{
			if (s.equals(get(i)))
			{
				this.removeElementAt(i);
				nMax--;
				if (nMax == 0)
					break;
			}
		}
	}

	/**
	 * serialize to a string
	 * @param sep separator between strings
	 * @param front string before the first entry
	 * @param back string after the last entry
	 * 
	 * @return a tokenized string
	 * @deprecated use StringUtil setVString default: GetString(sep, JDFConstants.EMPTYSTRING, JDFConstants.EMPTYSTRING)
	 */
	@Deprecated
	public String getString(final String sep, final String front, final String back)
	{
		return StringUtil.setvString(this, sep, front, back);
	}

	/**
	 * create a string from a vector of tokens
	 * @param v vector of tokens
	 * @param sep separator between tokens
	 * @param front prefix to string (before the first token)
	 * @param end suffix to string (after the last token)
	 * @return condensed string of tokens separated by sep
	 * @deprecated use getString
	 */
	@Deprecated
	public String setvString(final VString v, final String sep, final String front, final String end)
	{
		String s = front == null ? JDFCoreConstants.EMPTYSTRING : front;
		final int siz = v.size();
		for (int i = 0; i < siz; i++)
		{
			if (i != 0)// add seperator after every add
			{
				s += sep;
			}
			s += v.elementAt(i);
		}
		if (end != null)
		{
			s += end;
		}
		return s;
	}

	/**
	 * unify - make VString unique, retaining initial order
	 */
	public void unify()
	{
		ContainerUtil.unify(this);
	}

	/**
	 * get a string from <code>this</code>
	 * @param s the String you are looking for
	 * @return the String if found or null if <code>this</code> does not contain s
	 */
	public String get(final String s)
	{
		if (contains(s))
		{
			final int i = indexOf(s);
			return get(i);
		}

		return null;
	}

	/**
	 * gets a set with all entries of the VString<br/>
	 * note that the set retains ordering (LinkedHashSet)
	 * @return the set corresponding to this
	 */
	public Set<String> getSet()
	{
		final HashSet<String> set = new LinkedHashSet<String>();
		final Iterator<String> it = iterator();
		while (it.hasNext())
		{
			set.add(it.next());
		}

		return set;
	}

	/**
	 * sort <code>this</code> lexically
	 */
	public void sort()
	{
		final Object[] array = this.elementData;
		Arrays.sort(array, 0, size());
	}

	/**
	 * appends all strings of an array to <code>this</code>
	 * @param strings the array of strings to append to <code>this</code>
	 */
	public void addAll(final String[] strings)
	{
		ensureCapacity(size() + strings.length);
		for (int i = 0; i < strings.length; i++)
		{
			add(strings[i]);
		}
	}

	/**
	 * checks whether at least one of a given vector of strings is contained in <code>this</code>
	 * 
	 * @param other the VSTring of values to test
	 * @return true if at least one String in other is in <code>this</code>
	 */
	public boolean containsAny(final VString other)
	{
		if (other == null)
		{
			return false;
		}
		final int size = other.size();
		for (int i = 0; i < size; i++)
		{
			if (contains(other.elementAt(i)))
			{
				return true;
			}
		}
		return false;
	}

	/**
	 * appends enumType to <code>this</code><br>
	 * if enumType is a ValuedEnum, the name is appended
	 * 
	 * @param enumType the object to append
	 * @return true if successfully added
	 */
	public boolean add(final ValuedEnum enumType)
	{
		return super.add(enumType.getName());
	}

}
