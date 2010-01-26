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

import org.cip4.jdflib.core.JDFConstants;

//
//  Output an XML character in UTF-8 format.
//
//  Type /*XMLCh*/ char represents a 16 bit Unicode character, also known as UTF-16.
//  The W3C DOM API specifies the use of UTF-16 for all DOM character-oriented
//  interfaces.
//
//  UTF-8 is able to represent all of the 16 bit characters as a sequence of
//  one or more 8 bit bytes.  And, all XML parsers are required to recognize
//  and read documents using UTF-8 encoding.
//
//  For additional information on UTF-8, refer to
//      http://www.ietf.org/rfc/rfc2279.txt
//
//  Within this sample, characters that are known in advance to be plain 7
//  bit ASCII can be (and are) written directly using fputs() or similar
//  routines, because their UTF-8 encoding is just the original single byte.
//  But general 16-bit characters all pass through this function, and may be
//  translated to several bytes of UTF-8; how many depends on the original
//  Unicode character value.
//
//  Note: This code does not recognize or handle "surrogate pairs", in which
//  a single character is encoded as two 16 bit UTF-16 values. A more complete
//  implementation is available in the book "The Unicode Standard, Version 2",
//  from the Unicode Consortium.
//

/**
 * @deprecated
 */
@Deprecated
public class XMLstrm
{

	/**
	 * @see java.lang.Object#toString()
	 * @return
	*/
	@Override
	public String toString()
	{
		return "XMLstrm[ ]";
	}

	// static boolean doEscapes;

	/**
	 * the routines defined here serialize dom to xml<br>
	 * mainly copied from xerces
	 */

	// public KString DOM2String(/*DOM*/String name){ return new KString(
	// name.rawBuffer(),name.length());};
	// //////////////////////////////////////////////////////////////////////////
	// /
	// note that the following routine is NOT thread-safe!!!
	// this is private and must be recreated
	// routine to indent xml output, called by the << operators. intitialize
	// with a call of bSet=true.
	private static char sm_buf[];
	private static int sm_ifirst = 0;
	private static int sm_indent = 0;
	private static boolean sm_doNext = true;

	/**
	 * @param iInc
	 * @param bSet
	 * @return default: XMLIndent(iInc, false)
	 */
	public static String xmlIndent(int iInc, boolean bSet)
	{
		if (bSet)
		{
			if (iInc < 0)
			{
				sm_doNext = true;
			}
			else
			{
				sm_indent = iInc;
			}
			return "0";
		}

		sm_ifirst = Math.max(sm_ifirst + sm_indent * iInc, 0);
		if (!sm_doNext)
		{
			return JDFConstants.EMPTYSTRING;
		}

		for (int i = 1; i <= sm_ifirst; i++)
		{
			sm_buf[i - 1] = ' '; // if(ifirst>0) memset(buf,' ',ifirst);
			// //?????????
		}

		sm_buf[sm_ifirst] = 0;
		sm_doNext = false;
		return new String(sm_buf);
	}
}