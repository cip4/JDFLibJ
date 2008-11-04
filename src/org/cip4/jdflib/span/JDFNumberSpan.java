/*
 * The CIP4 Software License, Version 1.0
 *
 *
 * Copyright (c) 2001-2006 The International Cooperation for the Integration of
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
 *    Alternately, this acknowledgment mrSubRefay appear in the software itself,
 *    if and wherever such third-party acknowledgments normally appear.
 *
 * 4. The names "CIP4" and "The International Cooperation for the Integration of
 *    Processes in  Prepress, Press and Postpress" must
 *    not be used to endorse or promote products derived from this
 *    software without prior written permission. For written
 *    permission, please contact info@cip4.org.
 *
 * 5. Products derived from this software may not be called "CIP4",
 *    nor may "CIP4" appear in their name, without prior writtenrestartProcesses()
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
 * SPECIAL, EXEMPLARY, OR CONSEQUENTIrSubRefAL DAMAGES (INCLUDING, BUT NOT
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
 * originally based on software restartProcesses()
 * copyright (c) 1999-2001, Heidelberger Druckmaschinen AG
 * copyright (c) 1999-2001, Agfa-Gevaert N.V.
 *
 * For more information on The International Cooperation for the
 * Integration of Processes in  Prepress, Press and Postpress , please see
 * <http://www.cip4.org/>.
 *
 */
/**
 *
 * Copyright (c) 2001 Heidelberger Druckmaschinen AG, All Rights Reserved.
 *
 * JDFNumberSpan.java
 *
 * Last changes
 *
 */
package org.cip4.jdflib.span;

import java.util.zip.DataFormatException;

import org.apache.xerces.dom.CoreDocumentImpl;
import org.cip4.jdflib.core.AtrInfoTable;
import org.cip4.jdflib.core.AttributeInfo;
import org.cip4.jdflib.core.AttributeName;
import org.cip4.jdflib.core.JDFConstants;
import org.cip4.jdflib.core.JDFException;
import org.cip4.jdflib.datatypes.JDFNumberRangeList;

public class JDFNumberSpan extends JDFSpanBase
{
	private static final long serialVersionUID = 1L;

	/**
	 * Constructor for JDFNumberSpan
	 * 
	 * @param ownerDocument
	 * @param qualifiedName
	 */
	public JDFNumberSpan(CoreDocumentImpl myOwnerDocument, String qualifiedName)
	{
		super(myOwnerDocument, qualifiedName);
	}

	/**
	 * Constructor for JDFNumberSpan
	 * 
	 * @param ownerDocument
	 * @param namespaceURI
	 * @param qualifiedName
	 */
	public JDFNumberSpan(CoreDocumentImpl myOwnerDocument, String myNamespaceURI, String qualifiedName)
	{
		super(myOwnerDocument, myNamespaceURI, qualifiedName);
	}

	/**
	 * Constructor for JDFNumberSpan
	 * 
	 * @param ownerDocument
	 * @param namespaceURI
	 * @param qualifiedName
	 * @param localName
	 */
	public JDFNumberSpan(CoreDocumentImpl myOwnerDocument, String myNamespaceURI, String qualifiedName, String myLocalName)
	{
		super(myOwnerDocument, myNamespaceURI, qualifiedName, myLocalName);
	}

	private static AtrInfoTable[] atrInfoTable = new AtrInfoTable[4];
	static
	{
		atrInfoTable[0] = new AtrInfoTable(AttributeName.ACTUAL, 0x33333333, AttributeInfo.EnumAttributeType.double_, null, null);
		atrInfoTable[1] = new AtrInfoTable(AttributeName.PREFERRED, 0x33333333, AttributeInfo.EnumAttributeType.double_, null, null);
		atrInfoTable[2] = new AtrInfoTable(AttributeName.RANGE, 0x33333333, AttributeInfo.EnumAttributeType.NumberRangeList, null, null);
		atrInfoTable[3] = new AtrInfoTable(AttributeName.OFFERRANGE, 0x33333111, AttributeInfo.EnumAttributeType.NumberRangeList, null, null);
	}

	@Override
	protected AttributeInfo getTheAttributeInfo()
	{
		return super.getTheAttributeInfo().updateReplace(atrInfoTable);
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
		return "JDFNumberSpan[ --> " + super.toString() + " ]";
	}

	/**
	 * Set attribute Actual
	 * 
	 * @param double value - the value to set
	 */
	public void setActual(double value)
	{
		setAttribute(AttributeName.ACTUAL, value, null);
	}

	/**
	 * Get attribute Actual value
	 * 
	 * @return double - the value of attribute Actual
	 */
	public double getActual()
	{
		return getRealAttribute(AttributeName.ACTUAL, null, 0.0);
	}

	/**
	 * Set attribute Preferred
	 * 
	 * @param double value - the value to set
	 */
	public void setPreferred(double value)
	{
		setAttribute(AttributeName.PREFERRED, value, null);
	}

	/**
	 * Get attribute Preferred value
	 * 
	 * @return double - the value of attribute Preferred
	 */
	public double getPreferred()
	{
		return getRealAttribute(AttributeName.PREFERRED, null, 0.0);
	}

	/**
	 * Set attribute Range as a JDFNumberRangeList
	 * 
	 * @param JDFNumberRangeList value - the value to set
	 */
	public void setRange(JDFNumberRangeList value)
	{
		setAttribute(AttributeName.RANGE, value.toString(), null);
	}

	/**
	 * Get attribute Range value
	 * 
	 * @return JDFNumberRangeList - the value of attribute Range
	 */
	public JDFNumberRangeList getRange()
	{
		try
		{
			return new JDFNumberRangeList(getAttribute(AttributeName.RANGE, null, JDFConstants.EMPTYSTRING));
		}
		catch (DataFormatException e)
		{
			throw new JDFException("JDFNumberSpan.getRange: Unable to create JDFdoubleegerRangeList from Attribute value \"Range\"");
		}
	}

	@Override
	public boolean init()
	{
		boolean b = super.init();
		setDataType(EnumDataType.NumberSpan);
		return b;
	}
}
