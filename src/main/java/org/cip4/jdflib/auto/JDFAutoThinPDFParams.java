/*
 * The CIP4 Software License, Version 1.0
 *
 *
 * Copyright (c) 2001-2025 The International Cooperation for the Integration of
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
 * class JDFAutoThinPDFParams : public JDFElement
 *****************************************************************************
 * 
 */

public abstract class JDFAutoThinPDFParams extends JDFElement
{

	private static final long serialVersionUID = 1L;

	private static AtrInfoTable[] atrInfoTable = new AtrInfoTable[4];
	static
	{
		atrInfoTable[0] = new AtrInfoTable(AttributeName.FILEPERPAGE, 0x3333333333l, AttributeInfo.EnumAttributeType.boolean_, null, "false");
		atrInfoTable[1] = new AtrInfoTable(AttributeName.SIDELINEEPS, 0x3333333311l, AttributeInfo.EnumAttributeType.boolean_, null, "false");
		atrInfoTable[2] = new AtrInfoTable(AttributeName.SIDELINEFONTS, 0x3333333333l, AttributeInfo.EnumAttributeType.boolean_, null, "false");
		atrInfoTable[3] = new AtrInfoTable(AttributeName.SIDELINEIMAGES, 0x3333333333l, AttributeInfo.EnumAttributeType.boolean_, null, "false");
	}

	@Override
	protected AttributeInfo getTheAttributeInfo()
	{
		return super.getTheAttributeInfo().updateReplace(atrInfoTable);
	}

	/**
	 * Constructor for JDFAutoThinPDFParams
	 *
	 * @param myOwnerDocument
	 * @param qualifiedName
	 */
	protected JDFAutoThinPDFParams(CoreDocumentImpl myOwnerDocument, String qualifiedName)
	{
		super(myOwnerDocument, qualifiedName);
	}

	/**
	 * Constructor for JDFAutoThinPDFParams
	 *
	 * @param myOwnerDocument
	 * @param myNamespaceURI
	 * @param qualifiedName
	 */
	protected JDFAutoThinPDFParams(CoreDocumentImpl myOwnerDocument, String myNamespaceURI, String qualifiedName)
	{
		super(myOwnerDocument, myNamespaceURI, qualifiedName);
	}

	/**
	 * Constructor for JDFAutoThinPDFParams
	 *
	 * @param myOwnerDocument
	 * @param myNamespaceURI
	 * @param qualifiedName
	 * @param myLocalName
	 */
	protected JDFAutoThinPDFParams(CoreDocumentImpl myOwnerDocument, String myNamespaceURI, String qualifiedName, String myLocalName)
	{
		super(myOwnerDocument, myNamespaceURI, qualifiedName, myLocalName);
	}

	/*
	 * ************************************************************************ Attribute getter / setter ************************************************************************
	 */

	/*
	 * --------------------------------------------------------------------- Methods for Attribute FilePerPage ---------------------------------------------------------------------
	 */
	/**
	 * (36) set attribute FilePerPage
	 *
	 * @param value the value to set the attribute to
	 */
	public void setFilePerPage(boolean value)
	{
		setAttribute(AttributeName.FILEPERPAGE, value, null);
	}

	/**
	 * (18) get boolean attribute FilePerPage
	 *
	 * @return boolean the value of the attribute
	 */
	public boolean getFilePerPage()
	{
		return getBoolAttribute(AttributeName.FILEPERPAGE, null, false);
	}

	/*
	 * --------------------------------------------------------------------- Methods for Attribute SidelineEPS ---------------------------------------------------------------------
	 */
	/**
	 * (36) set attribute SidelineEPS
	 *
	 * @param value the value to set the attribute to
	 */
	public void setSidelineEPS(boolean value)
	{
		setAttribute(AttributeName.SIDELINEEPS, value, null);
	}

	/**
	 * (18) get boolean attribute SidelineEPS
	 *
	 * @return boolean the value of the attribute
	 */
	public boolean getSidelineEPS()
	{
		return getBoolAttribute(AttributeName.SIDELINEEPS, null, false);
	}

	/*
	 * --------------------------------------------------------------------- Methods for Attribute SidelineFonts
	 * ---------------------------------------------------------------------
	 */
	/**
	 * (36) set attribute SidelineFonts
	 *
	 * @param value the value to set the attribute to
	 */
	public void setSidelineFonts(boolean value)
	{
		setAttribute(AttributeName.SIDELINEFONTS, value, null);
	}

	/**
	 * (18) get boolean attribute SidelineFonts
	 *
	 * @return boolean the value of the attribute
	 */
	public boolean getSidelineFonts()
	{
		return getBoolAttribute(AttributeName.SIDELINEFONTS, null, false);
	}

	/*
	 * --------------------------------------------------------------------- Methods for Attribute SidelineImages
	 * ---------------------------------------------------------------------
	 */
	/**
	 * (36) set attribute SidelineImages
	 *
	 * @param value the value to set the attribute to
	 */
	public void setSidelineImages(boolean value)
	{
		setAttribute(AttributeName.SIDELINEIMAGES, value, null);
	}

	/**
	 * (18) get boolean attribute SidelineImages
	 *
	 * @return boolean the value of the attribute
	 */
	public boolean getSidelineImages()
	{
		return getBoolAttribute(AttributeName.SIDELINEIMAGES, null, false);
	}

}
