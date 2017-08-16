/*
 * The CIP4 Software License, Version 1.0
 *
 *
 * Copyright (c) 2001-2017 The International Cooperation for the Integration of
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
 * JDFSpan.java
 *
 * Last changes
 *
 */
package org.cip4.jdflib.span;

import java.util.zip.DataFormatException;

import org.apache.xerces.dom.CoreDocumentImpl;
import org.cip4.jdflib.core.AttributeName;
import org.cip4.jdflib.core.JDFConstants;
import org.cip4.jdflib.core.JDFException;
import org.cip4.jdflib.datatypes.JDFIntegerRangeList;
import org.cip4.jdflib.datatypes.JDFNameRange;
import org.cip4.jdflib.datatypes.JDFNameRangeList;
import org.cip4.jdflib.datatypes.JDFNumberRangeList;
import org.cip4.jdflib.datatypes.JDFRangeList;
import org.w3c.dom.DOMException;

/**
 * @deprecated defines the data type dependent parts of a ranged Span resource
 */
@Deprecated
public abstract class JDFSpan extends JDFSpanBase
{
	private static final long serialVersionUID = 1L;

	/**
	 * Constructor for JDFSpan
	 *
	 * @param ownerDocument
	 * @param qualifiedName
	 * @throws DOMException
	 */
	public JDFSpan(CoreDocumentImpl myOwnerDocument, String qualifiedName) throws DOMException
	{
		super(myOwnerDocument, qualifiedName);
	}

	/**
	 * Constructor for JDFSpan
	 *
	 * @param ownerDocument
	 * @param namespaceURI
	 * @param qualifiedName
	 * @throws DOMException
	 */
	public JDFSpan(CoreDocumentImpl myOwnerDocument, String myNamespaceURI, String qualifiedName) throws DOMException
	{
		super(myOwnerDocument, myNamespaceURI, qualifiedName);
	}

	/**
	 * Constructor for JDFSpan
	 *
	 * @param ownerDocument
	 * @param namespaceURI
	 * @param qualifiedName
	 * @param localName
	 * @throws DOMException
	 */
	public JDFSpan(CoreDocumentImpl myOwnerDocument, String myNamespaceURI, String qualifiedName, String myLocalName) throws DOMException
	{
		super(myOwnerDocument, myNamespaceURI, qualifiedName, myLocalName);
	}

	// **************************************** Interfaces
	// ******************************************
	interface IntegerSpan
	{
		void setRange(JDFIntegerRangeList o);
	}

	interface NumberSpan
	{
		void setRange(JDFNumberRangeList o);
	}

	interface NameSpan
	{
		void setRange(JDFNameRangeList o);
	}

	// **************************************** Methods
	// *********************************************
	/**
	 * toString
	 *
	 * @return String
	 */
	@Override
	public String toString()
	{
		return "JDFSpan[ --> " + super.toString() + " ]";
	}

	/**
	 * SetPreferred
	 *
	 * @param Object o
	 */
	public void setPreferred(Object o)
	{
		setAttribute(AttributeName.PREFERRED, o.toString(), JDFConstants.EMPTYSTRING);
	}

	/**
	 * SetPreferred
	 *
	 * @param double o
	 */
	public void setPreferred(double o)
	{
		setAttribute(AttributeName.PREFERRED, JDFConstants.EMPTYSTRING + o, JDFConstants.EMPTYSTRING);
	}

	/**
	 * SetPreferred
	 *
	 * @param boolean o
	 */
	public void setPreferred(boolean o)
	{
		setAttribute(AttributeName.PREFERRED, JDFConstants.EMPTYSTRING + o, JDFConstants.EMPTYSTRING);
	}

	/**
	 * SetPreferred
	 *
	 * @param String o
	 */
	public void setPreferred(String o)
	{
		setAttribute(AttributeName.PREFERRED, o, JDFConstants.EMPTYSTRING);
	}

	/**
	 * SetActual
	 *
	 * @param Object o
	 */
	public void setActual(Object o)
	{
		setAttribute(AttributeName.ACTUAL, o.toString(), JDFConstants.EMPTYSTRING);
	}

	/**
	 * SetActual
	 *
	 * @param int o
	 */
	public void setActual(int o)
	{
		setAttribute(AttributeName.ACTUAL, JDFConstants.EMPTYSTRING + o, JDFConstants.EMPTYSTRING);
	}

	/**
	 * SetActual
	 *
	 * @param double o
	 */
	public void setActual(double o)
	{
		setAttribute(AttributeName.ACTUAL, JDFConstants.EMPTYSTRING + o, JDFConstants.EMPTYSTRING);
	}

	/**
	 * SetActual
	 *
	 * @param boolean o
	 */
	public void setActual(boolean o)
	{
		setAttribute(AttributeName.ACTUAL, JDFConstants.EMPTYSTRING + o, JDFConstants.EMPTYSTRING);
	}

	/**
	 * SetActual
	 *
	 * @param String o
	 */
	public void setActual(String o)
	{
		setAttribute(AttributeName.ACTUAL, o, JDFConstants.EMPTYSTRING);
	}

	/**
	 * SetRange
	 *
	 * @param String rs
	 */
	public void setRange(String rs)
	{
		setAttribute(AttributeName.RANGE, rs, JDFConstants.EMPTYSTRING);
	}

	/**
	 * SetRange
	 *
	 * @param JDFRangeList rl
	 * @deprecated use specialized routines
	 */
	@Deprecated
	public void setRange(JDFRangeList rl)
	{
		setAttribute("Range", rl.toString(), JDFConstants.EMPTYSTRING);
	}

	/**
	 * GetPreferred
	 *
	 * @return String
	 */
	public String getPreferred()
	{
		return getAttribute(AttributeName.PREFERRED, JDFConstants.EMPTYSTRING, JDFConstants.EMPTYSTRING);
	}

	/**
	 * GetActual
	 *
	 * @return String
	 */
	public String getActual()
	{
		return getAttribute(AttributeName.ACTUAL, JDFConstants.EMPTYSTRING, JDFConstants.EMPTYSTRING);
	}

	/**
	 * GetRange
	 *
	 * @return String
	 */
	public String getRange()
	{
		return getAttribute(AttributeName.RANGE, JDFConstants.EMPTYSTRING, JDFConstants.EMPTYSTRING);
	}

	/**
	 * AddRange
	 *
	 * @param Object xMin
	 * @param Object xMax default is both values are equal
	 */
	public void addRange(String xMin, String xMax)
	{
		try
		{
			JDFNameRangeList rl = new JDFNameRangeList(getRange());
			rl.append(new JDFNameRange(xMin, xMax));
			setAttribute(AttributeName.RANGE, rl.toString(), JDFConstants.EMPTYSTRING);
		}
		catch (DataFormatException e)
		{
			throw new JDFException("JDFSpan.addRange: DataFormatExceptione while creating JDFNameRange");
		}
	}
}
