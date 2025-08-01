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
import org.cip4.jdflib.core.ElemInfoTable;
import org.cip4.jdflib.core.ElementInfo;
import org.cip4.jdflib.core.ElementName;
import org.cip4.jdflib.core.JDFElement;
import org.cip4.jdflib.resource.JDFResource;

/**
 *****************************************************************************
 * class JDFAutoPreflightAnalysis : public JDFResource
 *****************************************************************************
 * 
 */

public abstract class JDFAutoPreflightAnalysis extends JDFResource
{

	private static final long serialVersionUID = 1L;

	private static ElemInfoTable[] elemInfoTable = new ElemInfoTable[6];
	static
	{
		elemInfoTable[0] = new ElemInfoTable(ElementName.COLORSRESULTSPOOL, 0x7777777766l);
		elemInfoTable[1] = new ElemInfoTable(ElementName.DOCUMENTRESULTSPOOL, 0x7777777766l);
		elemInfoTable[2] = new ElemInfoTable(ElementName.FONTSRESULTSPOOL, 0x7777777766l);
		elemInfoTable[3] = new ElemInfoTable(ElementName.FILETYPERESULTSPOOL, 0x7777777766l);
		elemInfoTable[4] = new ElemInfoTable(ElementName.IMAGESRESULTSPOOL, 0x7777777766l);
		elemInfoTable[5] = new ElemInfoTable(ElementName.PAGESRESULTSPOOL, 0x7777777766l);
	}

	@Override
	protected ElementInfo getTheElementInfo()
	{
		return super.getTheElementInfo().updateReplace(elemInfoTable);
	}

	/**
	 * Constructor for JDFAutoPreflightAnalysis
	 *
	 * @param myOwnerDocument
	 * @param qualifiedName
	 */
	protected JDFAutoPreflightAnalysis(CoreDocumentImpl myOwnerDocument, String qualifiedName)
	{
		super(myOwnerDocument, qualifiedName);
	}

	/**
	 * Constructor for JDFAutoPreflightAnalysis
	 *
	 * @param myOwnerDocument
	 * @param myNamespaceURI
	 * @param qualifiedName
	 */
	protected JDFAutoPreflightAnalysis(CoreDocumentImpl myOwnerDocument, String myNamespaceURI, String qualifiedName)
	{
		super(myOwnerDocument, myNamespaceURI, qualifiedName);
	}

	/**
	 * Constructor for JDFAutoPreflightAnalysis
	 *
	 * @param myOwnerDocument
	 * @param myNamespaceURI
	 * @param qualifiedName
	 * @param myLocalName
	 */
	protected JDFAutoPreflightAnalysis(CoreDocumentImpl myOwnerDocument, String myNamespaceURI, String qualifiedName, String myLocalName)
	{
		super(myOwnerDocument, myNamespaceURI, qualifiedName, myLocalName);
	}

	/**
	 * @return true if ok
	 */
	@Override
	public boolean init()
	{
		final boolean bRet = super.init();
		setResourceClass(JDFResource.EnumResourceClass.Parameter);
		return bRet;
	}

	/**
	 * @return the resource Class
	 */
	@Override
	public EnumResourceClass getValidClass()
	{
		return JDFResource.EnumResourceClass.Parameter;
	}

	/*
	 * *********************************************************************** Element getter / setter ***********************************************************************
	 */

	/**
	 * (24) const get element ColorsResultsPool
	 *
	 * @return JDFElement the element
	 */
	public JDFElement getColorsResultsPool()
	{
		return (JDFElement) getElement(ElementName.COLORSRESULTSPOOL, null, 0);
	}

	/**
	 * (25) getCreateColorsResultsPool
	 * 
	 * @return JDFElement the element
	 */
	public JDFElement getCreateColorsResultsPool()
	{
		return (JDFElement) getCreateElement_JDFElement(ElementName.COLORSRESULTSPOOL, null, 0);
	}

	/**
	 * (29) append element ColorsResultsPool
	 *
	 * @return JDFElement the element @ if the element already exists
	 */
	public JDFElement appendColorsResultsPool()
	{
		return (JDFElement) appendElementN(ElementName.COLORSRESULTSPOOL, 1, null);
	}

	/**
	 * (24) const get element DocumentResultsPool
	 *
	 * @return JDFElement the element
	 */
	public JDFElement getDocumentResultsPool()
	{
		return (JDFElement) getElement(ElementName.DOCUMENTRESULTSPOOL, null, 0);
	}

	/**
	 * (25) getCreateDocumentResultsPool
	 * 
	 * @return JDFElement the element
	 */
	public JDFElement getCreateDocumentResultsPool()
	{
		return (JDFElement) getCreateElement_JDFElement(ElementName.DOCUMENTRESULTSPOOL, null, 0);
	}

	/**
	 * (29) append element DocumentResultsPool
	 *
	 * @return JDFElement the element @ if the element already exists
	 */
	public JDFElement appendDocumentResultsPool()
	{
		return (JDFElement) appendElementN(ElementName.DOCUMENTRESULTSPOOL, 1, null);
	}

	/**
	 * (24) const get element FontsResultsPool
	 *
	 * @return JDFElement the element
	 */
	public JDFElement getFontsResultsPool()
	{
		return (JDFElement) getElement(ElementName.FONTSRESULTSPOOL, null, 0);
	}

	/**
	 * (25) getCreateFontsResultsPool
	 * 
	 * @return JDFElement the element
	 */
	public JDFElement getCreateFontsResultsPool()
	{
		return (JDFElement) getCreateElement_JDFElement(ElementName.FONTSRESULTSPOOL, null, 0);
	}

	/**
	 * (29) append element FontsResultsPool
	 *
	 * @return JDFElement the element @ if the element already exists
	 */
	public JDFElement appendFontsResultsPool()
	{
		return (JDFElement) appendElementN(ElementName.FONTSRESULTSPOOL, 1, null);
	}

	/**
	 * (24) const get element FileTypeResultsPool
	 *
	 * @return JDFElement the element
	 */
	public JDFElement getFileTypeResultsPool()
	{
		return (JDFElement) getElement(ElementName.FILETYPERESULTSPOOL, null, 0);
	}

	/**
	 * (25) getCreateFileTypeResultsPool
	 * 
	 * @return JDFElement the element
	 */
	public JDFElement getCreateFileTypeResultsPool()
	{
		return (JDFElement) getCreateElement_JDFElement(ElementName.FILETYPERESULTSPOOL, null, 0);
	}

	/**
	 * (29) append element FileTypeResultsPool
	 *
	 * @return JDFElement the element @ if the element already exists
	 */
	public JDFElement appendFileTypeResultsPool()
	{
		return (JDFElement) appendElementN(ElementName.FILETYPERESULTSPOOL, 1, null);
	}

	/**
	 * (24) const get element ImagesResultsPool
	 *
	 * @return JDFElement the element
	 */
	public JDFElement getImagesResultsPool()
	{
		return (JDFElement) getElement(ElementName.IMAGESRESULTSPOOL, null, 0);
	}

	/**
	 * (25) getCreateImagesResultsPool
	 * 
	 * @return JDFElement the element
	 */
	public JDFElement getCreateImagesResultsPool()
	{
		return (JDFElement) getCreateElement_JDFElement(ElementName.IMAGESRESULTSPOOL, null, 0);
	}

	/**
	 * (29) append element ImagesResultsPool
	 *
	 * @return JDFElement the element @ if the element already exists
	 */
	public JDFElement appendImagesResultsPool()
	{
		return (JDFElement) appendElementN(ElementName.IMAGESRESULTSPOOL, 1, null);
	}

	/**
	 * (24) const get element PagesResultsPool
	 *
	 * @return JDFElement the element
	 */
	public JDFElement getPagesResultsPool()
	{
		return (JDFElement) getElement(ElementName.PAGESRESULTSPOOL, null, 0);
	}

	/**
	 * (25) getCreatePagesResultsPool
	 * 
	 * @return JDFElement the element
	 */
	public JDFElement getCreatePagesResultsPool()
	{
		return (JDFElement) getCreateElement_JDFElement(ElementName.PAGESRESULTSPOOL, null, 0);
	}

	/**
	 * (29) append element PagesResultsPool
	 *
	 * @return JDFElement the element @ if the element already exists
	 */
	public JDFElement appendPagesResultsPool()
	{
		return (JDFElement) appendElementN(ElementName.PAGESRESULTSPOOL, 1, null);
	}

}
