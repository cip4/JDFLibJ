/*
 *
 * The CIP4 Software License, Version 1.0
 *
 *
 * Copyright (c) 2001-2004 The International Cooperation for the Integration of 
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
 * Copyright (c) 2001 Heidelberger Druckmaschinen AG, All Rights Reserved.
 * 
 * @author Elena Skobchenko
 *
 * JDFPDFPathEvaluation.java
 *
 */

package org.cip4.jdflib.resource.devicecapability;

import java.util.zip.DataFormatException;

import org.apache.xerces.dom.CoreDocumentImpl;
import org.cip4.jdflib.core.AtrInfoTable;
import org.cip4.jdflib.core.AttributeInfo;
import org.cip4.jdflib.core.AttributeName;
import org.cip4.jdflib.core.ElemInfoTable;
import org.cip4.jdflib.core.ElementInfo;
import org.cip4.jdflib.core.ElementName;
import org.cip4.jdflib.core.JDFConstants;
import org.cip4.jdflib.core.JDFException;
import org.cip4.jdflib.core.VElement;
import org.cip4.jdflib.datatypes.JDFIntegerRange;
import org.cip4.jdflib.resource.JDFValue;

public class JDFPDFPathEvaluation extends JDFEvaluation
{
	private static final long serialVersionUID = 1L;

	private static AtrInfoTable[] atrInfoTable = new AtrInfoTable[1];
	static
	{
		atrInfoTable[0] = new AtrInfoTable(AttributeName.LENGTH, 0x33333333, AttributeInfo.EnumAttributeType.IntegerRange, null, null);
	}

	@Override
	protected AttributeInfo getTheAttributeInfo()
	{
		return super.getTheAttributeInfo().updateReplace(atrInfoTable);
	}

	private static ElemInfoTable[] elemInfoTable = new ElemInfoTable[1];
	static
	{
		elemInfoTable[0] = new ElemInfoTable(ElementName.VALUE, 0x33333333);
	}

	@Override
	protected ElementInfo getTheElementInfo()
	{
		return new ElementInfo(super.getTheElementInfo(), elemInfoTable);
	}

	/**
	 * constructor for JDFPDFPathEvaluation
	 * 
	 * @param myOwnerDocument
	 * @param qualifiedName
	 */
	public JDFPDFPathEvaluation(CoreDocumentImpl myOwnerDocument, String qualifiedName)
	{
		super(myOwnerDocument, qualifiedName);
	}

	/**
	 * constructor for JDFPDFPathEvaluation
	 * 
	 * @param myOwnerDocument
	 * @param myNamespaceURI
	 * @param qualifiedName
	 */
	public JDFPDFPathEvaluation(CoreDocumentImpl myOwnerDocument, String myNamespaceURI, String qualifiedName)
	{
		super(myOwnerDocument, myNamespaceURI, qualifiedName);
	}

	/**
	 * constructor for JDFPDFPathEvaluation
	 * 
	 * @param myOwnerDocument
	 * @param myNamespaceURI
	 * @param qualifiedName
	 * @param myLocalName
	 */
	public JDFPDFPathEvaluation(CoreDocumentImpl myOwnerDocument, String myNamespaceURI, String qualifiedName, String myLocalName)
	{
		super(myOwnerDocument, myNamespaceURI, qualifiedName, myLocalName);
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
		return "JDFPDFPathEvaluation[ --> " + super.toString() + " ]";
	}

	/*
	 * // Attribute getter / setter
	 */

	public void setLength(JDFIntegerRange value)
	{
		setAttribute(AttributeName.LENGTH, value.toString());
	}

	public JDFIntegerRange getLengthRange()
	{
		try
		{
			return new JDFIntegerRange(getAttribute(AttributeName.LENGTH));
		}
		catch (DataFormatException dfe)
		{
			throw new JDFException("JDFPDFPathEvaluation.getLengthRange: Attribute LENGTH is not applicable to create JDFIntegerRange");
		}
	}

	/*
	 * // Element getter / setter
	 */

	public JDFValue getValue(int iSkip)
	{
		JDFValue e = (JDFValue) getElement(ElementName.VALUE, JDFConstants.EMPTYSTRING, iSkip);
		return e;
	}

	public JDFValue appendValue()
	{
		return (JDFValue) appendElement(ElementName.VALUE, null);
	}

	/*
	 * // Subelement attribute and element Getter / Setter
	 */

	/**
	 * Set the value of the iSkip'th element <code>Value</code>
	 * 
	 * @param iSkip
	 *            the number of <code>Value</code> elements to skip
	 * @param value
	 *            value to set the element to
	 */
	public void setValueValue(int iSkip, String value)
	{
		JDFValue e = (JDFValue) getElement(ElementName.VALUE, null, iSkip);
		e.setValue(value);
	}

	/**
	 * Get the value of the iSkip'th subelement <code>Value</code>
	 * 
	 * @param iSkip
	 *            the number of <code>Value</code> elements to skip
	 * @return String: the value
	 */
	public final String getValueValue(int iSkip)
	{
		JDFValue e = (JDFValue) getElement(ElementName.VALUE, null, iSkip);
		return e.getValue();
	}

	/*
	 * // FitsValue Methods
	 */

	/**
	 * fitsValue - checks whether <code>value</code> matches the testlists
	 * specified for this Evaluation
	 * 
	 * @param value
	 *            value to test
	 * @return boolean - true, if <code>value</code> matches the testlists or if
	 *         testlists are not specified
	 */
	@Override
	public final boolean fitsValue(String value)
	{
		// TBD __Lena__ Ihhalt! Test of 20 symbols

		return fitsLength(value) && fitsValueElem(value);
	}

	/**
	 * fitsLength - checks whether <code>pfdPath</code> matches the Length
	 * specified for this Evaluation
	 * 
	 * @param pdfPath
	 *            PDFPath to test
	 * @return boolean - true, if <code>pdfPath</code> matches the Length or if
	 *         Length is not specified
	 */
	private final boolean fitsLength(String pdfPath)
	{
		if (!hasAttribute(AttributeName.LENGTH))
		{
			int len = pdfPath.length();
			return getLengthRange().inRange(len);
		}
		return true;
	}

	/**
	 * fitsValueElem - checks whether <code>pdfPath</code> matches the
	 * subelement Value specified for this Evaluation
	 * 
	 * @param pdfPath
	 *            PDFPath to test
	 * 
	 * @return boolean - true, if <code>pdfPath</code> matches subelement Value
	 */
	private final boolean fitsValueElem(String pdfPath)
	{
		VElement v = getChildElementVector(ElementName.VALUE, null, null, true, 0, false);
		int siz = v.size();
		if (siz == 0)
			return true; // Evaluation has no Value elements

		for (int i = 0; i < siz; i++)
		{
			String value = getValueValue(i); // JDFValue elm = (JDFValue)
												// v.elementAt(i);
			if (value.compareTo(pdfPath) == 0)
				return true; // we have found it
		}
		return false;
	}

}