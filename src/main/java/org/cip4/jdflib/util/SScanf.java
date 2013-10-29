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
package org.cip4.jdflib.util;

import java.io.IOException;
import java.io.StringReader;
import java.util.Iterator;
import java.util.Vector;

import org.cip4.jdflib.cformat.ScanfFormat;
import org.cip4.jdflib.cformat.ScanfMatchException;
import org.cip4.jdflib.cformat.ScanfReader;
import org.cip4.jdflib.core.VString;

/**
 * @author prosirai
 * 
 */
public class SScanf extends ScanfReader implements Iterator<Object>
{
	private final VString vFmt;
	private int pos = 0;

	/**
	 * creates a scanf reader for a given string and format and returns the approriate object
	 * 
	 * valid format identifiers %f - returns Double %i - returns Integer %d - returns Integer %x - returns Integer %o -
	 * returns Integer %c - returns String %s - returns String
	 * 
	 * @param theString the String to scan
	 * @param format the formatting String to apply according to c++ sscanf rools
	 */
	public SScanf(String theString, String format)
	{
		super(new StringReader(theString));
		String newFMT = StringUtil.replaceString(format, "%%", "__comma__��-eher selten");
		vFmt = StringUtil.tokenize(newFMT, "%", false);
		int siz = vFmt.size();
		// make sure we have exactly 1 element per "real" %
		String firstChar = "%";
		if (siz > 1 && !format.startsWith("%"))
		{
			vFmt.set(1, vFmt.stringAt(0) + "%" + vFmt.stringAt(1));
			vFmt.remove(0);
			siz--;
			firstChar = "";
		}
		for (int i = 0; i < siz; i++)
		{
			String fmtString = vFmt.stringAt(i);
			fmtString = StringUtil.replaceString(fmtString, "__comma__��-eher selten", "%%");
			vFmt.set(i, firstChar + fmtString);
			firstChar = "%";
		}

	}

	/**
	 * scan a string using C++ sscanf functionality
	 * @return 
	 * 
	 */
	public Vector<Object> sscanf()
	{
		Vector<Object> v = new Vector<Object>();
		while (hasNext())
			v.add(next());
		return v;
	}

	/**
	 * 
	 * @see org.cip4.jdflib.cformat.ScanfReader#scanDouble(org.cip4.jdflib.cformat .ScanfFormat)
	 */
	@Override
	public double scanDouble(ScanfFormat fmt) throws IOException, ScanfMatchException, IllegalArgumentException
	{
		if ("dxoi".indexOf(fmt.type) >= 0) // also gracefully handle int as
			// double
			return scanLong(fmt);
		return super.scanDouble(fmt);
	}

	/**
	 * 
	 * 
	 * @see org.cip4.jdflib.cformat.ScanfReader#scanString(org.cip4.jdflib.cformat .ScanfFormat)
	 */
	@Override
	public String scanString(ScanfFormat fmt) throws IOException, IllegalArgumentException
	{
		if ("di".indexOf(fmt.type) >= 0) // also gracefully handle int as double
			return Long.toString(scanLong(fmt), 10);
		if ("o".indexOf(fmt.type) >= 0) // also gracefully handle int as double
			return Long.toString(scanLong(fmt), 8);
		if ("x".indexOf(fmt.type) >= 0) // also gracefully handle int as double
			return Long.toString(scanLong(fmt), 16);
		if ("f".indexOf(fmt.type) >= 0) // also gracefully handle int as double
			return Double.toString(scanDouble(fmt));
		if ("c".indexOf(fmt.type) >= 0) // also gracefully handle int as double
			return new String(scanChars(fmt));
		return super.scanString(fmt);
	}

	/**
	 * 
	 * @see java.util.Iterator#hasNext()
	 */
	@Override
	public boolean hasNext()
	{
		return pos < vFmt.size();
	}

	/**
	 * returns the next Object (@see the constructor), null if the string has been completely parsed or an invalid
	 * format is scanned
	 */
	@Override
	public Object next()
	{
		if (!hasNext())
			return null;
		String fmtString = vFmt.stringAt(pos++);
		ScanfFormat fmt = new ScanfFormat(fmtString);
		try
		{
			if ("dxoi".indexOf(fmt.type) >= 0)
				return new Integer(scanInt(fmt));
			if ("f".indexOf(fmt.type) >= 0)
				return Double.valueOf(scanDouble(fmt));
			return scanString(fmt);
		}
		catch (IllegalArgumentException x)
		{
			pos = 999999;
			return null;
		}
		catch (IOException x)
		{
			pos = 999999;
			return null;
		}

	}

	/**
	 * NOT IMPLEMENTED - the iterator is only forward
	 * 
	 * @see java.util.Iterator#remove()
	 */
	@Override
	public void remove()
	{
		throw new IllegalArgumentException("remove not implemented!");

	}

	/**
	 * convenience static function - see the constructor for details
	 * 
	 * @param theString
	 * @param format
	 * @return Vector of scanned objects - see constructor for details
	 */
	public static Vector<Object> sscanf(String theString, String format)
	{
		return new SScanf(theString, format).sscanf();
	}
}
