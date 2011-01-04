/*--------------------------------------------------------------------------------------------------
 * The CIP4 Software License, Version 1.0
 *
 *
 * Copyright (c) 2001-2011 The International Cooperation for the Integration of 
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
 */
package org.cip4.jdflib.util;

import org.cip4.jdflib.core.JDFConstants;

/**
 * class to format integers, longs, doubles etc.
 * 
  * @author Rainer Prosi, Heidelberger Druckmaschinen *
 */
public class NumberFormatter
{
	/**
	 * set up the defaults
	 */
	public NumberFormatter()
	{
		super();
		zapp0 = true;
	}

	/**
	 * if set, remove trailing 0
	 *  
	 * @param zapp0
	 */
	public void setZapp0(boolean zapp0)
	{
		this.zapp0 = zapp0;
	}

	private boolean zapp0;

	/**
	 * returns a formatted double. Truncates to 8exactly precision digits after the "." <br>
	 * If precision=0, the . is stripped
	 * 
	 * @param d the double to format
	 * @param precision maximum precision, depending on value of zapp0, trailing 0s are discarded or kept
	 * @return the formatted string that represents d TBD handle exp format
	 */
	public String formatDouble(final double d, int precision)
	{
		final Double[] ad = { new Double(d) };
		if (precision > 0)
		{
			String s = StringUtil.sprintf("%." + precision + "f", ad);
			s = zappTrailing(s);
			return s;
		}
		else
		{
			return StringUtil.sprintf("%" + precision + "i", ad);
		}

	}

	private String zappTrailing(String s)
	{
		int posDot = s.indexOf('.');
		if (zapp0 && posDot >= 0)
		{
			int n;

			int length = s.length();
			for (n = length; n > posDot; n--)
			{
				if (s.charAt(n - 1) != '0')
				{
					break;
				}
			}
			s = s.substring(0, n);
		}
		return s;
	}

	/**
	 * returns a formatted double. Truncates to 8 digits after the "." <br>
	 * If the double is representable as an integer, any ".0" is stripped.
	 * 
	 * @param d the double to format
	 * @return the formatted string that represents d TBD handle exp format
	 */
	public String formatDouble(final double d)
	{
		String s;
		if (d == Double.MAX_VALUE)
		{
			s = JDFConstants.POSINF;
		}
		else if (d == -Double.MAX_VALUE)
		{
			s = JDFConstants.NEGINF;
		}
		else
		{
			s = String.valueOf(d);
			if (s.endsWith(".0"))
			{
				s = s.substring(0, s.length() - 2);
			}
			if (s.indexOf("E") >= 0)
			{
				s = formatDouble(d, 10);
			}

			if (s.length() > 10)
			{
				final int posDot = s.indexOf(JDFConstants.DOT);
				if (posDot >= 0)
				{
					int l = s.length();
					if (l - posDot > 8)
					{
						l = posDot + 9;
						s = s.substring(0, l);
						if (s.endsWith("999"))
						{
							return formatDouble(d + 0.000000004);
						}
						zappTrailing(s);
					}
				}
			}
			if (s.endsWith("."))
			{
				s = StringUtil.leftStr(s, -1);
			}
			if ("-0".equals(s))
			{
				s = "0";
			}
		}
		return s;
	}
}
