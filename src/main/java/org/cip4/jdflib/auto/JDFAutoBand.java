/*
 * The CIP4 Software License, Version 1.0
 *
 *
 * Copyright (c) 2001-2020 The International Cooperation for the Integration of
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
import org.cip4.jdflib.core.JDFCoreConstants;
import org.cip4.jdflib.core.JDFElement;

/**
 *****************************************************************************
 * class JDFAutoBand : public JDFElement
 *****************************************************************************
 *
 */

public abstract class JDFAutoBand extends JDFElement
{

	private static final long serialVersionUID = 1L;

	private static AtrInfoTable[] atrInfoTable = new AtrInfoTable[5];
	static
	{
		atrInfoTable[0] = new AtrInfoTable(AttributeName.DATA, 0x33333333, AttributeInfo.EnumAttributeType.URL, null, null);
		atrInfoTable[1] = new AtrInfoTable(AttributeName.HEIGHT, 0x33333333, AttributeInfo.EnumAttributeType.integer, null, null);
		atrInfoTable[2] = new AtrInfoTable(AttributeName.MASK, 0x33333333, AttributeInfo.EnumAttributeType.URL, null, null);
		atrInfoTable[3] = new AtrInfoTable(AttributeName.WASMARKED, 0x33333333, AttributeInfo.EnumAttributeType.boolean_, null, null);
		atrInfoTable[4] = new AtrInfoTable(AttributeName.WIDTH, 0x33333333, AttributeInfo.EnumAttributeType.integer, null, null);
	}

	@Override
	protected AttributeInfo getTheAttributeInfo()
	{
		return super.getTheAttributeInfo().updateReplace(atrInfoTable);
	}

	/**
	 * Constructor for JDFAutoBand
	 *
	 * @param myOwnerDocument
	 * @param qualifiedName
	 */
	protected JDFAutoBand(CoreDocumentImpl myOwnerDocument, String qualifiedName)
	{
		super(myOwnerDocument, qualifiedName);
	}

	/**
	 * Constructor for JDFAutoBand
	 *
	 * @param myOwnerDocument
	 * @param myNamespaceURI
	 * @param qualifiedName
	 */
	protected JDFAutoBand(CoreDocumentImpl myOwnerDocument, String myNamespaceURI, String qualifiedName)
	{
		super(myOwnerDocument, myNamespaceURI, qualifiedName);
	}

	/**
	 * Constructor for JDFAutoBand
	 *
	 * @param myOwnerDocument
	 * @param myNamespaceURI
	 * @param qualifiedName
	 * @param myLocalName
	 */
	protected JDFAutoBand(CoreDocumentImpl myOwnerDocument, String myNamespaceURI, String qualifiedName, String myLocalName)
	{
		super(myOwnerDocument, myNamespaceURI, qualifiedName, myLocalName);
	}

	/* ************************************************************************
	 * Attribute getter / setter
	 * ************************************************************************
	 */

	/* ---------------------------------------------------------------------
	Methods for Attribute Data
	--------------------------------------------------------------------- */
	/**
	 * (36) set attribute Data
	 *
	 * @param value the value to set the attribute to
	 */
	public void setData(String value)
	{
		setAttribute(AttributeName.DATA, value, null);
	}

	/**
	 * (23) get String attribute Data
	 *
	 * @return the value of the attribute
	 */
	public String getData()
	{
		return getAttribute(AttributeName.DATA, null, JDFCoreConstants.EMPTYSTRING);
	}

	/* ---------------------------------------------------------------------
	Methods for Attribute Height
	--------------------------------------------------------------------- */
	/**
	 * (36) set attribute Height
	 *
	 * @param value the value to set the attribute to
	 */
	public void setHeight(int value)
	{
		setAttribute(AttributeName.HEIGHT, value, null);
	}

	/**
	 * (15) get int attribute Height
	 *
	 * @return int the value of the attribute
	 */
	public int getHeight()
	{
		return getIntAttribute(AttributeName.HEIGHT, null, 0);
	}

	/* ---------------------------------------------------------------------
	Methods for Attribute Mask
	--------------------------------------------------------------------- */
	/**
	 * (36) set attribute Mask
	 *
	 * @param value the value to set the attribute to
	 */
	public void setMask(String value)
	{
		setAttribute(AttributeName.MASK, value, null);
	}

	/**
	 * (23) get String attribute Mask
	 *
	 * @return the value of the attribute
	 */
	public String getMask()
	{
		return getAttribute(AttributeName.MASK, null, JDFCoreConstants.EMPTYSTRING);
	}

	/* ---------------------------------------------------------------------
	Methods for Attribute WasMarked
	--------------------------------------------------------------------- */
	/**
	 * (36) set attribute WasMarked
	 *
	 * @param value the value to set the attribute to
	 */
	public void setWasMarked(boolean value)
	{
		setAttribute(AttributeName.WASMARKED, value, null);
	}

	/**
	 * (18) get boolean attribute WasMarked
	 *
	 * @return boolean the value of the attribute
	 */
	public boolean getWasMarked()
	{
		return getBoolAttribute(AttributeName.WASMARKED, null, false);
	}

	/* ---------------------------------------------------------------------
	Methods for Attribute Width
	--------------------------------------------------------------------- */
	/**
	 * (36) set attribute Width
	 *
	 * @param value the value to set the attribute to
	 */
	public void setWidth(int value)
	{
		setAttribute(AttributeName.WIDTH, value, null);
	}

	/**
	 * (15) get int attribute Width
	 *
	 * @return int the value of the attribute
	 */
	public int getWidth()
	{
		return getIntAttribute(AttributeName.WIDTH, null, 0);
	}

}
