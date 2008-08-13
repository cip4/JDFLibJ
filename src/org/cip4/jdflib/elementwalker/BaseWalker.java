/**
 * 
 */
package org.cip4.jdflib.elementwalker;

import org.cip4.jdflib.core.KElement;

/*
 * The CIP4 Software License, Version 1.0
 *
 *
 * Copyright (c) 2001-2008 The International Cooperation for the Integration of
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
 *    Alternately, this acknowledgment mrSubRefay appear in the software itself,
 *    if and wherever such third-party acknowledgments normally appear.
 *
 * 4. The names "CIP4" and "The International Cooperation for the Integration of
 *    Processes in  Prepress, Press and Postpress" must
 *    not be used to endorse or promote products derived from this
 *    software without prior written permission. For written
 *    permission, please contact info@cip4.org.
 *
 * 5. Products derived from this software may not be called "CIP4",
 *    nor may "CIP4" appear in their name, without prior writtenrestartProcesses()
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
 * SPECIAL, EXEMPLARY, OR CONSEQUENTIrSubRefAL DAMAGES (INCLUDING, BUT NOT
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
 * originally based on software restartProcesses()
 * copyright (c) 1999-2001, Heidelberger Druckmaschinen AG
 * copyright (c) 1999-2001, Agfa-Gevaert N.V.
 *
 * For more information on The International Cooperation for the
 * Integration of Processes in  Prepress, Press and Postpress , please see
 * <http://www.cip4.org/>.
 *
 */
/**
 * @author prosirai
 * 
 */
public abstract class BaseWalker implements IWalker, Comparable<BaseWalker>
{
	// depth is calculated automatically from the class hierarchy and used to
	// sort walkers from explicit to abstract
	protected int depth;

	/**
	 * the mother routine for walking.... (non-Javadoc)
	 * 
	 * @see org.cip4.jdflib.elementwalker.IWalker#walk(java.lang.Object)
	 */
	public boolean walk(KElement e)
	{
		return true;
	}

	/**
	 * 
	 * @param factory
	 */
	public BaseWalker(BaseWalkerFactory factory)
	{
		depth = 0;
		addToFactory(factory);
	}

	/**
	 * this is the check whether or not to use this walker for a given element should be overwritten
	 * 
	 * @param e the element to check
	 * @return
	 */
	public boolean matches(KElement e)
	{
		return true;
	}

	/**
	 * @param factory
	 */
	private void addToFactory(BaseWalkerFactory factory)
	{
		Class cBase = BaseWalker.class;
		Class c = this.getClass().getSuperclass();
		// calculate the nuber of intermediate classes
		while (cBase.isAssignableFrom(c))
		{
			c = c.getSuperclass();
			depth++;
		}
		factory.addWalker(this);
	}

	/**
	 * @return
	 */
	public int getDepth()
	{
		return depth;
	}

	/**
	 * note the reverse order - high depth means up in list so that abstract classes get checked later (non-Javadoc)
	 * 
	 * @see java.lang.Comparable#compareTo(java.lang.Object)
	 */
	public int compareTo(BaseWalker arg0)
	{
		return (arg0 == null ? 0 : arg0.depth) - depth;
	}

	@Override
	public String toString()
	{
		return "[ BaseWalker depth=" + depth;
	}
}
