/*
 *
 * The CIP4 Software License, Version 1.0
 *
 *
 * Copyright (c) 2001-2009 The International Cooperation for the Integration of 
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

import java.lang.reflect.Array;

/**
 * @author Dr. Rainer Prosi, Heidelberger Druckmaschinen AG <br/>
 * quick and dirty fast fifo built on a circular array May 26, 2009
 * @param <x> the elements to dump into the fifo
 */
public class FastFiFo<x>
{
	private final Object[] theArray;
	private int first;
	private int fill;

	/**
	 * @param n size of the fifo
	 * 
	 */
	public FastFiFo(final int n)
	{
		if (n <= 0)
		{
			throw new IllegalArgumentException("fifo must have positive # elements");
		}
		theArray = new Object[n];
		for (int i = 0; i < n; i++)
		{
			theArray[i] = null;
		}
		first = 0;
		fill = 0;
	}

	/**
	 * pushes an element into the back and returns the previous content of the cell
	 * @param back
	 * @return
	 */
	synchronized public x push(final x back)
	{
		final int s = theArray.length;
		x ret = null;
		if (fill == s)
		{
			ret = pop();
		}
		theArray[(first + fill++) % s] = back;
		return ret;
	}

	/**
	 * pops the first to go
	 * @return the first element, null if empty
	 */
	synchronized public x pop()
	{
		if (fill == 0)
		{
			return null;
		}
		final int s = theArray.length;
		final x ret = (x) theArray[first];
		first = ++first % s;
		fill--;
		return ret;
	}

	/**
	 * peeks into the fifo from the beginning
	 * @param i the index of the element to peek
	 * @return
	 */
	public x peek(final int i)
	{
		final int s = theArray.length;
		if (i >= fill)
		{
			return null;
		}
		return (x) theArray[(first + i) % s];
	}

	/**
	 * peeks into the fifo from the beginning
	 * @param i the index of the element to peek
	 * @return a snapshot of the current fifo, null if empty
	 */
	synchronized public x[] peekArray()
	{
		if (fill == 0)
		{
			return null;
		}
		final x[] ret = (x[]) Array.newInstance(peek(0).getClass(), fill);
		for (int i = 0; i < fill; i++)
		{
			ret[i] = peek(i);
		}
		return ret;

	}

	/**
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString()
	{
		return "FastFiFo : fill=" + fill + " first=" + first + "\n" + theArray.toString();
	}

	/**
	 * @return the current number of elements
	 */
	public int getFill()
	{
		return fill;
	}
}
