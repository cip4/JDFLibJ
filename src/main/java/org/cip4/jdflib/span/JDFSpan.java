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
import org.cip4.jdflib.core.JDFCoreConstants;
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
	@Deprecated
	public JDFSpan(final CoreDocumentImpl myOwnerDocument, final String qualifiedName) throws DOMException
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
	@Deprecated
	public JDFSpan(final CoreDocumentImpl myOwnerDocument, final String myNamespaceURI, final String qualifiedName) throws DOMException
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
	@Deprecated
	public JDFSpan(final CoreDocumentImpl myOwnerDocument, final String myNamespaceURI, final String qualifiedName, final String myLocalName) throws DOMException
	{
		super(myOwnerDocument, myNamespaceURI, qualifiedName, myLocalName);
	}

	// **************************************** Interfaces
	// ******************************************
	@Deprecated
	interface IntegerSpan
	{
		void setRange(JDFIntegerRangeList o);
	}

	@Deprecated
	interface NumberSpan
	{
		void setRange(JDFNumberRangeList o);
	}

	@Deprecated
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
	@Deprecated
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
	@Deprecated
	public void setPreferred(final Object o)
	{
		setAttribute(AttributeName.PREFERRED, o.toString(), JDFCoreConstants.EMPTYSTRING);
	}

	/**
	 * SetPreferred
	 *
	 * @param double o
	 */
	@Deprecated
	public void setPreferred(final double o)
	{
		setAttribute(AttributeName.PREFERRED, JDFCoreConstants.EMPTYSTRING + o, JDFCoreConstants.EMPTYSTRING);
	}

	/**
	 * SetPreferred
	 *
	 * @param boolean o
	 */
	@Deprecated
	public void setPreferred(final boolean o)
	{
		setAttribute(AttributeName.PREFERRED, JDFCoreConstants.EMPTYSTRING + o, JDFCoreConstants.EMPTYSTRING);
	}

	/**
	 * SetPreferred
	 *
	 * @param String o
	 */
	@Deprecated
	public void setPreferred(final String o)
	{
		setAttribute(AttributeName.PREFERRED, o, JDFCoreConstants.EMPTYSTRING);
	}

	/**
	 * SetActual
	 *
	 * @param Object o
	 */
	@Deprecated
	public void setActual(final Object o)
	{
		setAttribute(AttributeName.ACTUAL, o.toString(), JDFCoreConstants.EMPTYSTRING);
	}

	/**
	 * SetActual
	 *
	 * @param int o
	 */
	@Deprecated
	public void setActual(final int o)
	{
		setAttribute(AttributeName.ACTUAL, JDFCoreConstants.EMPTYSTRING + o, JDFCoreConstants.EMPTYSTRING);
	}

	/**
	 * SetActual
	 *
	 * @param double o
	 */
	@Deprecated
	public void setActual(final double o)
	{
		setAttribute(AttributeName.ACTUAL, JDFCoreConstants.EMPTYSTRING + o, JDFCoreConstants.EMPTYSTRING);
	}

	/**
	 * SetActual
	 *
	 * @param boolean o
	 */
	@Deprecated
	public void setActual(final boolean o)
	{
		setAttribute(AttributeName.ACTUAL, JDFCoreConstants.EMPTYSTRING + o, JDFCoreConstants.EMPTYSTRING);
	}

	/**
	 * SetActual
	 *
	 * @param String o
	 */
	@Deprecated
	public void setActual(final String o)
	{
		setAttribute(AttributeName.ACTUAL, o, JDFCoreConstants.EMPTYSTRING);
	}

	/**
	 * SetRange
	 *
	 * @param String rs
	 */
	@Deprecated
	public void setRange(final String rs)
	{
		setAttribute(AttributeName.RANGE, rs, JDFCoreConstants.EMPTYSTRING);
	}

	/**
	 * SetRange
	 *
	 * @param JDFRangeList rl
	 * @deprecated use specialized routines
	 */
	@Deprecated
	public void setRange(final JDFRangeList rl)
	{
		setAttribute("Range", rl.toString(), JDFCoreConstants.EMPTYSTRING);
	}

	/**
	 * GetPreferred
	 *
	 * @return String
	 */
	@Deprecated
	public String getPreferred()
	{
		return getAttribute(AttributeName.PREFERRED, JDFCoreConstants.EMPTYSTRING, JDFCoreConstants.EMPTYSTRING);
	}

	/**
	 * GetActual
	 *
	 * @return String
	 */
	@Deprecated
	public String getActual()
	{
		return getAttribute(AttributeName.ACTUAL, JDFCoreConstants.EMPTYSTRING, JDFCoreConstants.EMPTYSTRING);
	}

	/**
	 * GetRange
	 *
	 * @return String
	 */
	@Deprecated
	public String getRange()
	{
		return getAttribute(AttributeName.RANGE, JDFCoreConstants.EMPTYSTRING, JDFCoreConstants.EMPTYSTRING);
	}

	/**
	 * AddRange
	 *
	 * @param Object xMin
	 * @param Object xMax default is both values are equal
	 */
	@Deprecated
	public void addRange(final String xMin, final String xMax)
	{
		try
		{
			final JDFNameRangeList rl = new JDFNameRangeList(getRange());
			rl.append(new JDFNameRange(xMin, xMax));
			setAttribute(AttributeName.RANGE, rl.toString(), JDFCoreConstants.EMPTYSTRING);
		}
		catch (final DataFormatException e)
		{
			throw new JDFException("JDFSpan.addRange: DataFormatExceptione while creating JDFNameRange");
		}
	}
}
