/*
 *
 * The CIP4 Software License, Version 1.0
 *
 *
 * Copyright (c) 2001-2023 The International Cooperation for the Integration of Processes in Prepress, Press and Postpress (CIP4). All rights reserved.
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
 * JDFRange.java
 *
 */
package org.cip4.jdflib.datatypes;

import org.cip4.jdflib.core.JDFConstants;
import org.cip4.jdflib.util.ContainerUtil;
import org.cip4.jdflib.util.HashUtil;

/**
 * This class must be changed to abstract if possible.
 */
public abstract class JDFRange implements JDFBaseDataTypes
{
	// **************************************** Constructors
	// ****************************************

	/**
	 * isPartOfRange - is range 'ra' within this range?
	 *
	 * @param ra the range to test
	 *
	 * @return boolean - true if range 'r' is within this range, else false
	 */
	public abstract boolean isPartOfRange(JDFRange ra);

	// **************************************** Methods
	// *********************************************
	/**
	 * equals - returns true if both JDFDateTimeRange are equal, otherwise false
	 *
	 * @param other Object to compare
	 * @return boolean - true if equal, otherwise false
	 */
	@Override
	public boolean equals(final Object other)
	{
		if (this == other)
		{
			return true;
		}
		if (other == null)
		{
			return false;
		}
		if (!other.getClass().equals(getClass()))
		{
			return false;
		}

		return ContainerUtil.equals(getLeftObject(), ((JDFRange) other).getLeftObject()) && ContainerUtil.equals(getRightObject(), ((JDFRange) other).getRightObject());
	}

	/**
	 * hashCode: complements equals() to fulfill the equals/hashCode contract
	 *
	 * @return int
	 */
	@Override
	public int hashCode()
	{
		return HashUtil.hashCode(HashUtil.hashCode(0, getLeftObject()), getRightObject());
	}

	// these are used by the superclasses
	protected abstract Object getRightObject();

	protected abstract Object getLeftObject();

	protected boolean inObjectRange(final Object other)
	{
		return equals(other);
	}

	/**
	 *
	 * get a string with precision digits after each decimal
	 *
	 * @param precision
	 * @return
	 */
	public String getString(final int precision)
	{
		if (getLeftObject().equals(getRightObject()))
		{
			return getLeftString(precision);
		}
		return getLeftString(precision) + " ~ " + getRightString(precision);
	}

	/**
	 *
	 * get a string with precision digits after each decimal
	 *
	 * @param precision
	 * @return
	 */
	public String getXJDFString(final int precision)
	{
		return getLeftString(precision) + JDFConstants.BLANK + getRightString(precision);
	}

	public String getRightString(int precision)
	{
		return JDFConstants.EMPTYSTRING + getRightObject();
	}

	public String getLeftString(int precision)
	{
		return JDFConstants.EMPTYSTRING + getLeftObject();
	}

	@Override
	public String toString()
	{
		return getString(42);
	}
}