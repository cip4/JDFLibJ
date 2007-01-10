
/**
==========================================================================
class XMLstrm.java
    created 2001-03-14
==========================================================================

            COPYRIGHT Heidelberger Druckmaschinen AG, 1999-2001
                ALL RIGHTS RESERVED

                Author: sabjon@topmail.de

            Warning! very preliminary test version. Interface subject to change without prior notice!

        Revision history:

**/

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
public class XMLstrm
{

    public String toString()
    {
        return "XMLstrm[ ]";
    }

    //static boolean doEscapes;

    /**
        the routines defined here serialize dom to xml
        mainly copied from xerces
        */

    //  public KString DOM2String(/*DOM*/String name){ return new KString( name.rawBuffer(),name.length());};

    /////////////////////////////////////////////////////////////////////////////
    // note that the following routine is NOT thread-safe!!!

    // this is private and must be recreated
    // routine to indent xml output, called by the << operators. intitialize with a call of bSet=true.

    private static char sm_buf[];
    private static int sm_ifirst = 0;
    private static int sm_indent = 0;
    private static boolean sm_doNext = true;

    /**
     * @param iInc
     * @param bSet
     * @return
     * 
     * default: XMLIndent(iInc, false)
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
            sm_buf[i - 1] = ' '; //if(ifirst>0) memset(buf,' ',ifirst);    //?????????
        }
        
        sm_buf[sm_ifirst] = 0;
        sm_doNext = false;
        return new String(sm_buf);
    }
}