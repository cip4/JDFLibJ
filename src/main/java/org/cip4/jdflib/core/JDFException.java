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
 *
 * Copyright (c) 2001 Heidelberger Druckmaschinen AG, All Rights Reserved.
 *
 * JDFComment.java
 *
 * Last changes
 *
 */
package org.cip4.jdflib.core;

/**
 * @author Rainer Prosi, Heidelberger Druckmaschinen
 * Standard JDF runtime exception class
 */
public class JDFException extends RuntimeException
{
	/**
	 * equals that includes the value of id
	 * @see java.lang.Object#equals(java.lang.Object)
	 * @param obj
	 * @return true if obj is the same type of exception
	 */
	@Override
	public boolean equals(Object obj)
	{
		if (!(obj instanceof JDFException))
			return false;
		return id != 0 ? id == ((JDFException) obj).id : super.equals(obj);
	}

	/**
	 * @see java.lang.Object#hashCode()
	 * @return the hashcode that takes id into account
	 */
	@Override
	public int hashCode()
	{
		return getID();
	}

	private static final long serialVersionUID = 1L;
	private int id = 0;

	// **************************************** Constructors
	// ****************************************
	/**
	 * constructor the id is calculated as a hash code
	 * 
	 * @param message the message to print out
	 */
	public JDFException(String message)
	{
		super(message);
	}

	/**
	 * constructor if a stack trace is needed use JDFException (String message, boolean bPrintStack)
	 * 
	 * @param message
	 * @param _id id of the exception - never use id=0, since this is the flag to use the has code as id
	 */
	public JDFException(String message, int _id)
	{
		super(message);
		id = _id;
	}

	/**
	 * constructor
	 * 
	 * @param message
	 * @param bPrintStack print Stacktrace if true
	 * @deprecated print the stack trace in the application
	 */
	@Deprecated
	public JDFException(String message, boolean bPrintStack)
	{
		super(message);
		id = hashCode();
		if (bPrintStack == true)
		{
			printStackTrace();
		}
	}

	// **************************************** Methods
	// *********************************************
	/**
	 * toString - StringRepresentation of JDFNode
	 * 
	 * @return String
	 */
	@Override
	public String toString()
	{
		return "JDFException[  --> " + super.toString() + " ]";
	}

	/**
	 * return a unique identifier of this exception if id was not explicitley set, a hash code is returned
	 * 
	 * @return int the id
	 */
	public int getID()
	{
		return id == 0 ? super.hashCode() : id;
	}
}
