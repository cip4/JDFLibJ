/*
 * The CIP4 Software License, Version 1.0
 *
 *
 * Copyright (c) 2001-2024 The International Cooperation for the Integration of
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

package org.cip4.jdflib.auto;

import org.apache.xerces.dom.CoreDocumentImpl;
import org.cip4.jdflib.core.AtrInfoTable;
import org.cip4.jdflib.core.AttributeInfo;
import org.cip4.jdflib.core.AttributeName;
import org.cip4.jdflib.core.JDFElement;

/**
 *****************************************************************************
 * class JDFAutoCCITTFaxParams : public JDFElement
 *****************************************************************************
 * 
 */

public abstract class JDFAutoCCITTFaxParams extends JDFElement
{

	private static final long serialVersionUID = 1L;

	private static AtrInfoTable[] atrInfoTable = new AtrInfoTable[5];
	static
	{
		atrInfoTable[0] = new AtrInfoTable(AttributeName.UNCOMPRESSED, 0x3333333311l, AttributeInfo.EnumAttributeType.boolean_, null, "false");
		atrInfoTable[1] = new AtrInfoTable(AttributeName.K, 0x3333333311l, AttributeInfo.EnumAttributeType.integer, null, "0");
		atrInfoTable[2] = new AtrInfoTable(AttributeName.ENDOFLINE, 0x3333333311l, AttributeInfo.EnumAttributeType.boolean_, null, null);
		atrInfoTable[3] = new AtrInfoTable(AttributeName.ENCODEDBYTEALIGN, 0x3333333311l, AttributeInfo.EnumAttributeType.boolean_, null, null);
		atrInfoTable[4] = new AtrInfoTable(AttributeName.ENDOFBLOCK, 0x3333333311l, AttributeInfo.EnumAttributeType.boolean_, null, null);
	}

	@Override
	protected AttributeInfo getTheAttributeInfo()
	{
		return super.getTheAttributeInfo().updateReplace(atrInfoTable);
	}

	/**
	 * Constructor for JDFAutoCCITTFaxParams
	 *
	 * @param myOwnerDocument
	 * @param qualifiedName
	 */
	protected JDFAutoCCITTFaxParams(CoreDocumentImpl myOwnerDocument, String qualifiedName)
	{
		super(myOwnerDocument, qualifiedName);
	}

	/**
	 * Constructor for JDFAutoCCITTFaxParams
	 *
	 * @param myOwnerDocument
	 * @param myNamespaceURI
	 * @param qualifiedName
	 */
	protected JDFAutoCCITTFaxParams(CoreDocumentImpl myOwnerDocument, String myNamespaceURI, String qualifiedName)
	{
		super(myOwnerDocument, myNamespaceURI, qualifiedName);
	}

	/**
	 * Constructor for JDFAutoCCITTFaxParams
	 *
	 * @param myOwnerDocument
	 * @param myNamespaceURI
	 * @param qualifiedName
	 * @param myLocalName
	 */
	protected JDFAutoCCITTFaxParams(CoreDocumentImpl myOwnerDocument, String myNamespaceURI, String qualifiedName, String myLocalName)
	{
		super(myOwnerDocument, myNamespaceURI, qualifiedName, myLocalName);
	}

	/*
	 * ************************************************************************ Attribute getter / setter ************************************************************************
	 */

	/*
	 * --------------------------------------------------------------------- Methods for Attribute Uncompressed
	 * ---------------------------------------------------------------------
	 */
	/**
	 * (36) set attribute Uncompressed
	 *
	 * @param value the value to set the attribute to
	 */
	public void setUncompressed(boolean value)
	{
		setAttribute(AttributeName.UNCOMPRESSED, value, null);
	}

	/**
	 * (18) get boolean attribute Uncompressed
	 *
	 * @return boolean the value of the attribute
	 */
	public boolean getUncompressed()
	{
		return getBoolAttribute(AttributeName.UNCOMPRESSED, null, false);
	}

	/*
	 * --------------------------------------------------------------------- Methods for Attribute K ---------------------------------------------------------------------
	 */
	/**
	 * (36) set attribute K
	 *
	 * @param value the value to set the attribute to
	 */
	public void setK(int value)
	{
		setAttribute(AttributeName.K, value, null);
	}

	/**
	 * (15) get int attribute K
	 *
	 * @return int the value of the attribute
	 */
	public int getK()
	{
		return getIntAttribute(AttributeName.K, null, 0);
	}

	/*
	 * --------------------------------------------------------------------- Methods for Attribute EndOfLine ---------------------------------------------------------------------
	 */
	/**
	 * (36) set attribute EndOfLine
	 *
	 * @param value the value to set the attribute to
	 */
	public void setEndOfLine(boolean value)
	{
		setAttribute(AttributeName.ENDOFLINE, value, null);
	}

	/**
	 * (18) get boolean attribute EndOfLine
	 *
	 * @return boolean the value of the attribute
	 */
	public boolean getEndOfLine()
	{
		return getBoolAttribute(AttributeName.ENDOFLINE, null, false);
	}

	/*
	 * --------------------------------------------------------------------- Methods for Attribute EncodedByteAlign
	 * ---------------------------------------------------------------------
	 */
	/**
	 * (36) set attribute EncodedByteAlign
	 *
	 * @param value the value to set the attribute to
	 */
	public void setEncodedByteAlign(boolean value)
	{
		setAttribute(AttributeName.ENCODEDBYTEALIGN, value, null);
	}

	/**
	 * (18) get boolean attribute EncodedByteAlign
	 *
	 * @return boolean the value of the attribute
	 */
	public boolean getEncodedByteAlign()
	{
		return getBoolAttribute(AttributeName.ENCODEDBYTEALIGN, null, false);
	}

	/*
	 * --------------------------------------------------------------------- Methods for Attribute EndOfBlock ---------------------------------------------------------------------
	 */
	/**
	 * (36) set attribute EndOfBlock
	 *
	 * @param value the value to set the attribute to
	 */
	public void setEndOfBlock(boolean value)
	{
		setAttribute(AttributeName.ENDOFBLOCK, value, null);
	}

	/**
	 * (18) get boolean attribute EndOfBlock
	 *
	 * @return boolean the value of the attribute
	 */
	public boolean getEndOfBlock()
	{
		return getBoolAttribute(AttributeName.ENDOFBLOCK, null, false);
	}

}
