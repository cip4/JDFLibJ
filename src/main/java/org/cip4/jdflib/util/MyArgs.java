/*--------------------------------------------------------------------------------------------------
 * The CIP4 Software License, Version 1.0
 *
 *
 * Copyright (c) 2001-2014 The International Cooperation for the Integration of 
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
//Titel:        org.cip4.jdflib.util
//Version:
//Copyright:    Copyright (c) 1999
//Autor:       sabine Jonas
//Firma:      BUGH-Wuppertal
//Beschreibung:  Utility library for the JDF library
package org.cip4.jdflib.util;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;

import org.cip4.jdflib.core.JDFConstants;
import org.cip4.jdflib.core.VString;

/**
  * @author Rainer Prosi, Heidelberger Druckmaschinen *
 */
public class MyArgs
{

	// //////////////////////////////////////////////////////////////////////////
	// ////
	// Variables
	// //////////////////////////////////////////////////////////////////////////
	// ////
	private VString m_onlyArgs;
	private String m_switchParameterString;
	private String m_argumentParameterString;
	private String m_requiredParameterString;
	private final HashSet<String> m_flags = new HashSet<String>();
	private final HashMap<String, String> m_Parameters = new HashMap<String, String>();
	private VString m_argV;

	// cmd-line has or has not args and not only options( starting with "-" )

	// //////////////////////////////////////////////////////////////////////////
	// ////
	// Construction
	// //////////////////////////////////////////////////////////////////////////
	// ////

	/**
	 * @param argv 
	 * @param switchParameterString 
	 * @param argumentParameterString 
	 * @deprecated
	 */
	@Deprecated
	public MyArgs(final String argv[], final String switchParameterString, final String argumentParameterString)
	{
		initMyArgs(argv, switchParameterString, argumentParameterString, null);
	}

	/**
	 * @param argv
	 * @param switchParameterString
	 * @param argumentParameterString
	 * @param requiredParameterString
	 */
	public MyArgs(final String argv[], final String switchParameterString, final String argumentParameterString, final String requiredParameterString)
	{
		initMyArgs(argv, switchParameterString, argumentParameterString, requiredParameterString);
	}

	private void initMyArgs(final String[] argv, final String strSwitchParameter, final String strArgumentParameter, final String strRequiredParameter)
	{
		String[] argvLocal = argv;

		if (argvLocal == null)
		{
			argvLocal = new String[0];
		}

		m_switchParameterString = strSwitchParameter;
		m_argumentParameterString = strArgumentParameter;
		m_requiredParameterString = strRequiredParameter;

		m_argV = new VString(argvLocal);
		m_onlyArgs = new VString();

		// cut of the "-" from the options, but don't remove it from file names
		for (int i = 0; i < argvLocal.length; i++)
		{
			final String tempString = argvLocal[i];
			if (tempString.startsWith("-"))
			{
				String whazzLeft = tempString.substring(1);
				while (whazzLeft.length() > 0)
				{
					final String flag = whazzLeft.substring(0, 1);
					if (m_switchParameterString != null && m_switchParameterString.indexOf(flag) >= 0)
					{
						m_flags.add(flag);
						whazzLeft = whazzLeft.substring(1);
					}
					else if (m_argumentParameterString != null && m_argumentParameterString.indexOf(flag) >= 0)
					{
						String wl2 = whazzLeft.substring(1);
						if (wl2.length() == 0 && argvLocal.length > i + 1)
						{
							wl2 = argvLocal[i + 1];
							i++;
						}
						m_Parameters.put(flag, wl2);
						whazzLeft = "";
					}
					else
					{
						// oops... don't know it; skip it
						whazzLeft = whazzLeft.substring(1);
						if (!flag.equals("-"))
						{
							System.out.println("unknown flag:" + flag);
						}
					}
				}
			}
			else
			{
				m_onlyArgs.add(tempString);
			}

		}
	}

	/**
	 * @see java.lang.Object#toString()
	 * @return
	*/
	@Override
	public String toString()
	{
		String s = "\n\tMyArgs: \n";
		s += "\t\t switchParameterString=" + m_switchParameterString + "\n";
		s += "\t\t argumentParameterString=" + m_argumentParameterString + "\n";
		s += "\t\t requiredParameterString=" + m_requiredParameterString + "\n";
		s += "\t\t argC    =" + m_argV.size() + "\n";
		s += "\t\t argV    =" + m_argV + "\n";
		s += "\t\t Nargs   =" + nargs() + "\n";
		s += "\t\t Flags:  =";
		Iterator<String> it = m_flags.iterator();
		while (it.hasNext())
		{
			s += it.next() + ", ";
		}
		s += "\n";
		it = m_Parameters.keySet().iterator();
		s += "\t\t Parameters: \n";
		while (it.hasNext())
		{
			final String key = it.next();
			s += "\t\t\t " + key + " = " + m_Parameters.get(key) + "\n";
		}
		s += "\t\t onlyArgs=" + m_onlyArgs + "\n";

		return s += "\n";
	}

	// //////////////////////////////////////////////////////////////////////////
	// ////
	// Methods
	// //////////////////////////////////////////////////////////////////////////
	// ///

	// --------------------------------------------------------------------------
	// ----
	/**
	 * @param c
	 * @return
	 */
	public String parameter(final String c)
	{
		return parameter(c.charAt(0));
	}

	// .........................................................

	/**
	 * @param c
	 * @return
	 */
	public String parameter(final char c)
	{
		return m_Parameters.get("" + c);
	}

	// .........................................................

	// ......................................................................
	/**
	 * @param s
	 * @return
	 */
	public String parameterString(final String s)
	{
		return parameterString(s.charAt(0));
	}

	/**
	 * @param c
	 * @return
	 */
	public String parameterString(final char c)
	{
		return parameter(c);
	}

	// --------------------------------------------------------------------------
	// ----

	/**
	 * @return
	 */
	public int nargs()
	{
		return m_onlyArgs.size();
	}

	// ......................................................................
	/**
	 * @param m
	 * @return
	 */
	public String argument(final int m)
	{

		if (m >= nargs())
		{
			return null;
		}
		return m_onlyArgs.get(m);
	}

	// ......................................................................
	/**
	 * @param m
	 * @return default: ArgumentString(0)
	 */
	public String argumentString(final int m)
	{
		return argument(m);
	}

	// --------------------------------------------------------------------------
	// ----
	/**
	 * convert character to interger
	 * 
	 * @param c
	 * @param defaultValue
	 * @param radix
	 * @return default: IntParameter(c + JDFConstants.EMPTYSTRING, 0, 10)
	 */
	public int intParameter(final char c, final int defaultValue, final int radix)
	{
		return intParameter(c + JDFConstants.EMPTYSTRING, defaultValue, radix);
	}

	/**
	 * @param s
	 * @param defaultValue
	 * @param radix
	 * @return default: IntParameter(s + JDFConstants.EMPTYSTRING, 0, 10)
	 */
	public int intParameter(final String s, final int defaultValue, final int radix)
	{

		final String paramString = parameter(s);
		try
		{
			return Integer.parseInt(paramString, radix);
		}
		catch (final NumberFormatException e)
		{
			System.out.println("WARNING: Int-Parameter[" + s + "] has no Int-Argument" + " or does not exist (= [" + paramString + "] ) ==> use default value: " + defaultValue);
			return defaultValue;
		}
	}

	// ......................................................................
	/**
	 * convert character to double
	 * 
	 * @param c
	 * @param defaultValue
	 * @return default: FloatParameter(JDFConstants.EMPTYSTRING + c, 0)
	 */
	public double floatParameter(final char c, final double defaultValue)
	{
		return floatParameter(JDFConstants.EMPTYSTRING + c, defaultValue);
	}

	/**
	 * @param s
	 * @param defaultValue
	 * @return default: FloatParameter(JDFConstants.EMPTYSTRING + s, 0)
	 */
	public double floatParameter(final String s, final double defaultValue)
	{

		final String paramString = parameter(s);
		try
		{
			return Double.parseDouble(paramString);
		}
		catch (final NumberFormatException e)
		{
			System.out.println("WARNING: Float-Parameter[" + s + "] has no Double-Argument" + " or does not exist (= [" + paramString + "] ) ==> use default value: "
					+ defaultValue);
			return defaultValue;
		}
	}

	// ......................................................................
	/**
	 * convert character to boolean
	 * 
	 * @param c
	 * 
	 * @return default: BoolParameter(c + JDFConstants.EMPTYSTRING, false)
	 */
	public boolean boolParameter(final char c)
	{
		return boolParameter(c + JDFConstants.EMPTYSTRING);
	}

	/**
	 * convert character to boolean
	 * 
	 * @param c
	 * @param defaultValue 
	 * 
	 * @return default: BoolParameter(c + JDFConstants.EMPTYSTRING, false)
	 * @deprecated defaultValue==true is kind of stupid...
	 */
	@Deprecated
	public boolean boolParameter(final char c, boolean defaultValue)
	{
		return boolParameter(c + JDFConstants.EMPTYSTRING, defaultValue);
	}

	/** 
	 * set or remove a boolean flag
	 *  
	 * @param c the flag key
	 * @param val if true, set else remove
	 */
	public void setFlag(char c, boolean val)
	{
		if (val)
			m_flags.add("" + c);
		else
			m_flags.remove("" + c);
	}

	/** 
	 * set or remove a boolean flag
	 *  
	 * @param c the flag key
	 * @param val the parameter value, if null remove
	 */
	public void setParam(char c, String val)
	{
		if (val != null)
			m_Parameters.put("" + c, val);
		else
			m_Parameters.remove("" + c);
	}

	/**
	 * @param s
	 * @param defaultValue
	 * @return default: BoolParameter(s + JDFConstants.EMPTYSTRING, false)
	 * @deprecated defaultValue==true is kind of stupid...
	 */
	@Deprecated
	public boolean boolParameter(final String s, final boolean defaultValue)
	{
		return m_flags.contains(s) ? true : defaultValue;
	}

	/**
	 * @param s
	 * 
	 * @return 
	 */
	public boolean boolParameter(final String s)
	{
		return m_flags.contains(s);
	}

	/**
	 * @param paramString
	 * @return default: null
	 */
	public String usage(final String paramString)
	{
		String usageTable = paramString == null ? "" : paramString;

		usageTable += "\n.\n.\n.usage: ";
		if (m_argV.size() > 0)
		{
			usageTable += m_argV.get(0);
		}

		if (m_switchParameterString != null)
		{
			usageTable += "\n\t switches:   -" + m_switchParameterString;
		}
		if (m_argumentParameterString != null)
		{
			usageTable += "\n\t Parameters: -" + m_argumentParameterString;
		}
		if (m_requiredParameterString != null)
		{
			usageTable += "\n\t Required:   -" + m_requiredParameterString;
		}

		usageTable += "\n\t Argument(s)\n";

		return usageTable;
	}

	/**
	 * return true if either a flag or parameter for c is set
	 * 
	 * @param c
	 *            the char to test for
	 * @return
	 */
	public boolean hasParameter(final char c)
	{
		return (m_switchParameterString != null && m_switchParameterString.indexOf(c) > -1 && m_flags.contains("" + c)) || (parameter(c) != null);
	}

}