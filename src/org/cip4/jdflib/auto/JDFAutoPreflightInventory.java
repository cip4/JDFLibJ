/*
 * The CIP4 Software License, Version 1.0
 *
 *
 * Copyright (c) 2001-2005 The International Cooperation for the Integration of
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
import org.cip4.jdflib.core.*;                      
import org.cip4.jdflib.resource.*;

public abstract class JDFAutoPreflightInventory extends JDFResource
{

    private static final long serialVersionUID = 1L;

    private static ElemInfoTable[] elemInfoTable = new ElemInfoTable[6];
    static
    {
        elemInfoTable[0] = new ElemInfoTable(ElementName.COLORSRESULTSPOOL, 0x77777766);
        elemInfoTable[1] = new ElemInfoTable(ElementName.DOCUMENTRESULTSPOOL, 0x77777766);
        elemInfoTable[2] = new ElemInfoTable(ElementName.FONTSRESULTSPOOL, 0x77777766);
        elemInfoTable[3] = new ElemInfoTable(ElementName.FILETYPERESULTSPOOL, 0x77777766);
        elemInfoTable[4] = new ElemInfoTable(ElementName.IMAGESRESULTSPOOL, 0x77777766);
        elemInfoTable[5] = new ElemInfoTable(ElementName.PAGESRESULTSPOOL, 0x77777766);
    }
    
    @Override
	protected ElementInfo getTheElementInfo()
    {
        return super.getTheElementInfo().updateReplace(elemInfoTable);
    }



    /**
     * Constructor for JDFAutoPreflightInventory
     * @param myOwnerDocument
     * @param qualifiedName
     */
    protected JDFAutoPreflightInventory(
        CoreDocumentImpl myOwnerDocument,
        String qualifiedName)
    {
        super(myOwnerDocument, qualifiedName);
    }

    /**
     * Constructor for JDFAutoPreflightInventory
     * @param myOwnerDocument
     * @param myNamespaceURI
     * @param qualifiedName
     */
    protected JDFAutoPreflightInventory(
        CoreDocumentImpl myOwnerDocument,
        String myNamespaceURI,
        String qualifiedName)
    {
        super(myOwnerDocument, myNamespaceURI, qualifiedName);
    }

    /**
     * Constructor for JDFAutoPreflightInventory
     * @param myOwnerDocument
     * @param myNamespaceURI
     * @param qualifiedName
     * @param myLocalName
     */
    protected JDFAutoPreflightInventory(
        CoreDocumentImpl myOwnerDocument,
        String myNamespaceURI,
        String qualifiedName,
        String myLocalName)
    {
        super(myOwnerDocument, myNamespaceURI, qualifiedName, myLocalName);
    }


    @Override
	public String toString()
    {
        return " JDFAutoPreflightInventory[  --> " + super.toString() + " ]";
    }


    @Override
	public boolean  init()
    {
        boolean bRet = super.init();
        setResourceClass(JDFResource.EnumResourceClass.Parameter);
        return bRet;
    }


    @Override
	public EnumResourceClass getValidClass()
    {
        return JDFResource.EnumResourceClass.Parameter;
    }


/* ***********************************************************************
 * Element getter / setter
 * ***********************************************************************
 */

    /**
     * (24) const get element ColorsResultsPool
     * @return JDFElement the element
     */
    public JDFElement getColorsResultsPool()
    {
        return (JDFElement) getElement(ElementName.COLORSRESULTSPOOL, null, 0);
    }

    /** (25) getCreateColorsResultsPool
     * 
     * @return JDFElement the element
     */
    public JDFElement getCreateColorsResultsPool()
    {
        return (JDFElement) getCreateElement_KElement(ElementName.COLORSRESULTSPOOL, null, 0);
    }

    /**
     * (29) append element ColorsResultsPool
     */
    public JDFElement appendColorsResultsPool() throws JDFException
    {
        return (JDFElement) appendElementN(ElementName.COLORSRESULTSPOOL, 1, null);
    }

    /**
     * (24) const get element DocumentResultsPool
     * @return JDFElement the element
     */
    public JDFElement getDocumentResultsPool()
    {
        return (JDFElement) getElement(ElementName.DOCUMENTRESULTSPOOL, null, 0);
    }

    /** (25) getCreateDocumentResultsPool
     * 
     * @return JDFElement the element
     */
    public JDFElement getCreateDocumentResultsPool()
    {
        return (JDFElement) getCreateElement_KElement(ElementName.DOCUMENTRESULTSPOOL, null, 0);
    }

    /**
     * (29) append element DocumentResultsPool
     */
    public JDFElement appendDocumentResultsPool() throws JDFException
    {
        return (JDFElement) appendElementN(ElementName.DOCUMENTRESULTSPOOL, 1, null);
    }

    /**
     * (24) const get element FontsResultsPool
     * @return JDFElement the element
     */
    public JDFElement getFontsResultsPool()
    {
        return (JDFElement) getElement(ElementName.FONTSRESULTSPOOL, null, 0);
    }

    /** (25) getCreateFontsResultsPool
     * 
     * @return JDFElement the element
     */
    public JDFElement getCreateFontsResultsPool()
    {
        return (JDFElement) getCreateElement_KElement(ElementName.FONTSRESULTSPOOL, null, 0);
    }

    /**
     * (29) append element FontsResultsPool
     */
    public JDFElement appendFontsResultsPool() throws JDFException
    {
        return (JDFElement) appendElementN(ElementName.FONTSRESULTSPOOL, 1, null);
    }

    /**
     * (24) const get element FileTypeResultsPool
     * @return JDFElement the element
     */
    public JDFElement getFileTypeResultsPool()
    {
        return (JDFElement) getElement(ElementName.FILETYPERESULTSPOOL, null, 0);
    }

    /** (25) getCreateFileTypeResultsPool
     * 
     * @return JDFElement the element
     */
    public JDFElement getCreateFileTypeResultsPool()
    {
        return (JDFElement) getCreateElement_KElement(ElementName.FILETYPERESULTSPOOL, null, 0);
    }

    /**
     * (29) append element FileTypeResultsPool
     */
    public JDFElement appendFileTypeResultsPool() throws JDFException
    {
        return (JDFElement) appendElementN(ElementName.FILETYPERESULTSPOOL, 1, null);
    }

    /**
     * (24) const get element ImagesResultsPool
     * @return JDFElement the element
     */
    public JDFElement getImagesResultsPool()
    {
        return (JDFElement) getElement(ElementName.IMAGESRESULTSPOOL, null, 0);
    }

    /** (25) getCreateImagesResultsPool
     * 
     * @return JDFElement the element
     */
    public JDFElement getCreateImagesResultsPool()
    {
        return (JDFElement) getCreateElement_KElement(ElementName.IMAGESRESULTSPOOL, null, 0);
    }

    /**
     * (29) append element ImagesResultsPool
     */
    public JDFElement appendImagesResultsPool() throws JDFException
    {
        return (JDFElement) appendElementN(ElementName.IMAGESRESULTSPOOL, 1, null);
    }

    /**
     * (24) const get element PagesResultsPool
     * @return JDFElement the element
     */
    public JDFElement getPagesResultsPool()
    {
        return (JDFElement) getElement(ElementName.PAGESRESULTSPOOL, null, 0);
    }

    /** (25) getCreatePagesResultsPool
     * 
     * @return JDFElement the element
     */
    public JDFElement getCreatePagesResultsPool()
    {
        return (JDFElement) getCreateElement_KElement(ElementName.PAGESRESULTSPOOL, null, 0);
    }

    /**
     * (29) append element PagesResultsPool
     */
    public JDFElement appendPagesResultsPool() throws JDFException
    {
        return (JDFElement) appendElementN(ElementName.PAGESRESULTSPOOL, 1, null);
    }

}// end namespace JDF
