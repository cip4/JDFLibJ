/*
 *
 * The CIP4 Software License, Version 1.0
 *
 *
 * Copyright (c) 2001-2012 The International Cooperation for the Integration of 
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
 * HashUtil.java
 * 
 * Copyright (c) 2004 Heidelberger Druckmaschinen AG, All Rights Reserved.
 */
package org.cip4.jdflib.util;

import java.util.Collection;

/**
 * This class provides some hashCode calculation utilities. Use the static methods of this class to generate
 * 
 * <pre>
 * hashCode()
 * </pre>
 * 
 * values in data objects. For example, to calculate the hashCode of a data object, use the methods of this class as follows:
 * 
 * <pre>
 * int myIntField;
 * Object myObject;
 * 
 * public int hashCode()
 * {
 * 	//        int hash = super.hashCode();    // use when not extending Object
 * 	int hash = 0; // use when extending Object
 * 	hash = HashUtil.hashCode(hash, myIntField);
 * 	hash = HashUtil.hashCode(hash, myObject);
 * 	return hash;
 * }
 * </pre>
 * 
 * <br>
 * Hint: Start your hashCode calculation depending on the object your data object extends. If you extend Object initialize your hash value to 0. Otherwise
 * initialize hash to super.hashCode(). See the example code.
 * 
 * @author Manfred Steinbach
 */
public class HashUtil extends Object
{
	/**
	 * 
	 */
	public static final int PRIME = 1000003;

	private HashUtil()
	{ // Prevent an instantiation
		super();
	}

	/**
	 * @param source
	 * @param x
	 * @return
	 */
	public static final int hashCode(final int source, final boolean x)
	{
		return PRIME * source + (x ? 1 : 0);
	}

	/**
	 * @param source
	 * @param x
	 * @return
	 */
	public static final int hashCode(final int source, final int x)
	{
		return PRIME * source + x;
	}

	/**
	 * @param source
	 * @param x
	 * @return
	 */
	public static final int hashCode(final int source, final long x)
	{
		return PRIME * source + (int) (PRIME * (x >>> 32) + (x & 0xFFFFFFFF));
	}

	/**
	 * @param source
	 * @param x
	 * @return
	 */
	public static final int hashCode(final int source, final float x)
	{
		return hashCode(source, ((new Float(x).equals(new Float(0.0))) ? 0 : Float.floatToIntBits(x)));
	}

	/**
	 * @param source
	 * @param x
	 * @return
	 */
	public static final int hashCode(final int source, final double x)
	{
		return hashCode(source, ((Double.valueOf(x).equals(Double.valueOf(0.0))) ? 0L : Double.doubleToLongBits(x)));
	}

	/**
	 * @param source
	 * @param x
	 * @return
	 */
	public static final int hashCode(final int source, final Object x)
	{
		return (null == x) ? 0 : PRIME * source + x.hashCode();
	}

	/**
	 * @param source
	 * @param c
	 * @return
	 */
	public static final int hashCode(int source, final Collection<?> c)
	{
		if (null != c)
		{
			for (Object o : c)
			{
				source = hashCode(source, o);
			}
		}

		return source;
	}

	/**
	 * @param source
	 * @param x
	 * @return
	 */
	public static final int hashCode(int source, final Object[] x)
	{
		if (null != x)
		{
			final int len = x.length;
			for (int i = 0; i < len; i++)
			{
				source = hashCode(source, x[i]);
			}
		}

		return source;
	}
}
