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
import org.cip4.jdflib.core.ElemInfoTable;
import org.cip4.jdflib.core.ElementInfo;
import org.cip4.jdflib.core.ElementName;
import org.cip4.jdflib.core.JDFElement;
import org.cip4.jdflib.core.JDFException;
import org.cip4.jdflib.resource.JDFResource;

public abstract class JDFAutoPreflightProfile extends JDFResource
{

    private static final long serialVersionUID = 1L;

    private static ElemInfoTable[] elemInfoTable = new ElemInfoTable[6];
    static
    {
        elemInfoTable[0] = new ElemInfoTable(ElementName.COLORSCONSTRAINTSPOOL, 0x77777766);
        elemInfoTable[1] = new ElemInfoTable(ElementName.DOCUMENTCONSTRAINTSPOOL, 0x77777766);
        elemInfoTable[2] = new ElemInfoTable(ElementName.FONTSCONSTRAINTSPOOL, 0x77777766);
        elemInfoTable[3] = new ElemInfoTable(ElementName.FILETYPECONSTRAINTSPOOL, 0x77777766);
        elemInfoTable[4] = new ElemInfoTable(ElementName.IMAGESCONSTRAINTSPOOL, 0x77777766);
        elemInfoTable[5] = new ElemInfoTable(ElementName.PAGESCONSTRAINTSPOOL, 0x77777766);
    }
    
    @Override
	protected ElementInfo getTheElementInfo()
    {
        return super.getTheElementInfo().updateReplace(elemInfoTable);
    }



    /**
     * Constructor for JDFAutoPreflightProfile
     * @param myOwnerDocument
     * @param qualifiedName
     */
    protected JDFAutoPreflightProfile(
        CoreDocumentImpl myOwnerDocument,
        String qualifiedName)
    {
        super(myOwnerDocument, qualifiedName);
    }

    /**
     * Constructor for JDFAutoPreflightProfile
     * @param myOwnerDocument
     * @param myNamespaceURI
     * @param qualifiedName
     */
    protected JDFAutoPreflightProfile(
        CoreDocumentImpl myOwnerDocument,
        String myNamespaceURI,
        String qualifiedName)
    {
        super(myOwnerDocument, myNamespaceURI, qualifiedName);
    }

    /**
     * Constructor for JDFAutoPreflightProfile
     * @param myOwnerDocument
     * @param myNamespaceURI
     * @param qualifiedName
     * @param myLocalName
     */
    protected JDFAutoPreflightProfile(
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
        return " JDFAutoPreflightProfile[  --> " + super.toString() + " ]";
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
     * (24) const get element ColorsConstraintsPool
     * @return JDFElement the element
     */
    public JDFElement getColorsConstraintsPool()
    {
        return (JDFElement) getElement(ElementName.COLORSCONSTRAINTSPOOL, null, 0);
    }

    /** (25) getCreateColorsConstraintsPool
     * 
     * @return JDFElement the element
     */
    public JDFElement getCreateColorsConstraintsPool()
    {
        return (JDFElement) getCreateElement_KElement(ElementName.COLORSCONSTRAINTSPOOL, null, 0);
    }

    /**
     * (29) append element ColorsConstraintsPool
     */
    public JDFElement appendColorsConstraintsPool() throws JDFException
    {
        return (JDFElement) appendElementN(ElementName.COLORSCONSTRAINTSPOOL, 1, null);
    }

    /**
     * (24) const get element DocumentConstraintsPool
     * @return JDFElement the element
     */
    public JDFElement getDocumentConstraintsPool()
    {
        return (JDFElement) getElement(ElementName.DOCUMENTCONSTRAINTSPOOL, null, 0);
    }

    /** (25) getCreateDocumentConstraintsPool
     * 
     * @return JDFElement the element
     */
    public JDFElement getCreateDocumentConstraintsPool()
    {
        return (JDFElement) getCreateElement_KElement(ElementName.DOCUMENTCONSTRAINTSPOOL, null, 0);
    }

    /**
     * (29) append element DocumentConstraintsPool
     */
    public JDFElement appendDocumentConstraintsPool() throws JDFException
    {
        return (JDFElement) appendElementN(ElementName.DOCUMENTCONSTRAINTSPOOL, 1, null);
    }

    /**
     * (24) const get element FontsConstraintsPool
     * @return JDFElement the element
     */
    public JDFElement getFontsConstraintsPool()
    {
        return (JDFElement) getElement(ElementName.FONTSCONSTRAINTSPOOL, null, 0);
    }

    /** (25) getCreateFontsConstraintsPool
     * 
     * @return JDFElement the element
     */
    public JDFElement getCreateFontsConstraintsPool()
    {
        return (JDFElement) getCreateElement_KElement(ElementName.FONTSCONSTRAINTSPOOL, null, 0);
    }

    /**
     * (29) append element FontsConstraintsPool
     */
    public JDFElement appendFontsConstraintsPool() throws JDFException
    {
        return (JDFElement) appendElementN(ElementName.FONTSCONSTRAINTSPOOL, 1, null);
    }

    /**
     * (24) const get element FileTypeConstraintsPool
     * @return JDFElement the element
     */
    public JDFElement getFileTypeConstraintsPool()
    {
        return (JDFElement) getElement(ElementName.FILETYPECONSTRAINTSPOOL, null, 0);
    }

    /** (25) getCreateFileTypeConstraintsPool
     * 
     * @return JDFElement the element
     */
    public JDFElement getCreateFileTypeConstraintsPool()
    {
        return (JDFElement) getCreateElement_KElement(ElementName.FILETYPECONSTRAINTSPOOL, null, 0);
    }

    /**
     * (29) append element FileTypeConstraintsPool
     */
    public JDFElement appendFileTypeConstraintsPool() throws JDFException
    {
        return (JDFElement) appendElementN(ElementName.FILETYPECONSTRAINTSPOOL, 1, null);
    }

    /**
     * (24) const get element ImagesConstraintsPool
     * @return JDFElement the element
     */
    public JDFElement getImagesConstraintsPool()
    {
        return (JDFElement) getElement(ElementName.IMAGESCONSTRAINTSPOOL, null, 0);
    }

    /** (25) getCreateImagesConstraintsPool
     * 
     * @return JDFElement the element
     */
    public JDFElement getCreateImagesConstraintsPool()
    {
        return (JDFElement) getCreateElement_KElement(ElementName.IMAGESCONSTRAINTSPOOL, null, 0);
    }

    /**
     * (29) append element ImagesConstraintsPool
     */
    public JDFElement appendImagesConstraintsPool() throws JDFException
    {
        return (JDFElement) appendElementN(ElementName.IMAGESCONSTRAINTSPOOL, 1, null);
    }

    /**
     * (24) const get element PagesConstraintsPool
     * @return JDFElement the element
     */
    public JDFElement getPagesConstraintsPool()
    {
        return (JDFElement) getElement(ElementName.PAGESCONSTRAINTSPOOL, null, 0);
    }

    /** (25) getCreatePagesConstraintsPool
     * 
     * @return JDFElement the element
     */
    public JDFElement getCreatePagesConstraintsPool()
    {
        return (JDFElement) getCreateElement_KElement(ElementName.PAGESCONSTRAINTSPOOL, null, 0);
    }

    /**
     * (29) append element PagesConstraintsPool
     */
    public JDFElement appendPagesConstraintsPool() throws JDFException
    {
        return (JDFElement) appendElementN(ElementName.PAGESCONSTRAINTSPOOL, 1, null);
    }

}// end namespace JDF
