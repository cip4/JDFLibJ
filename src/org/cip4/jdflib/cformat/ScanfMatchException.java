/*
 * Copyright John E. Lloyd, 2000. All rights reserved. Permission
 * to use, copy, and modify, without fee, is granted for non-commercial
 * and research purposes, provided that this copyright notice appears
 * in all copies.
 *
 * This  software is distributed "as is", without any warranty, including
 * any implied warranty of merchantability or fitness for a particular
 * use. The authors assume no responsibility for, and shall not be liable
 * for, any special, indirect, or consequential damages, or any damages
 * whatsoever, arising out of or in connection with the use of this
 * software.
 */
package org.cip4.jdflib.cformat;

import java.io.IOException;


/**
  * Exception class used by the <tt>scan</tt> methods within
  * ScanfReader when the input does not match the specified format.
  *
  * @.author John E. Lloyd
  * @see ScanfReader
  */
public class ScanfMatchException extends IOException
{
    //~ Constructors ///////////////////////////////////////////////////////////

    /**
      * Creates a new ScanfMatchException with the given message.
      *
      * @param msg Error message
      * @see ScanfReader
      */
    public ScanfMatchException(String msg)
    {
        super(msg);
    }
}

///////////////////////////////////////////////////////////////////////////////
//  END OF FILE.
///////////////////////////////////////////////////////////////////////////////
