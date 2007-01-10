/**
 * Copyright (c) 2001 Heidelberger Druckmaschinen AG, All Rights Reserved. JDFLayoutElement.java
 * -------------------------------------------------------------------------------------------------
 * The CIP4 Software License, Version 0.1 Copyright (c) 2001 The International Cooperation for the Integration of
 * Processes in  Prepress, Press and Postpress (CIP4).  All rights reserved.
 * Redistribution and use in source and binary forms, with or without
 * modification, are permitted provided that the following conditions are met:
 * 1. Redistributions of source code must retain the above copyright
 * notice, this list of conditions and the following disclaimer.
 * 2. Redistributions in binary form must reproduce the above copyright notice, this list of conditions 
 * and the following disclaimer in the documentation and/or other materials provided with the
 * distribution. 3. The end-user documentation included with the redistribution,
 * if any, must include the following acknowledgment: "This product includes software developed by the
 * The International Cooperation for the Integration of Processes in  Prepress, Press and Postpress (www.cip4.org)"
 * Alternately, this acknowledgment may appear in the software itself,
 * if and wherever such third-party acknowledgments normally appear.
 * 4. The names "CIP4" and "The International Cooperation for the Integration of
 * Processes in  Prepress, Press and Postpress" must not be used to endorse or promote products derived from this
 * software without prior written permission. For written permission, please contact info@cip4.org.
 * 5. Products derived from this software may not be called "CIP4", nor may "CIP4" appear in their name, 
 * without prior written permission of the CIP4 organization 
 * Usage of this software in commercial products is subject to restrictions. 
 * For details please consult info@cip4.org. 
 * THIS SOFTWARE IS PROVIDED ``AS IS'' AND ANY EXPRESSED OR IMPLIED
 * WARRANTIES, INCLUDING, BUT NOT LIMITED TO, THE IMPLIED WARRANTIES
 * OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE ARE DISCLAIMED.  
 * IN NO EVENT SHALL THE INTERNATIONAL COOPERATION FOR
 * THE INTEGRATION OF PROCESSES IN PREPRESS, PRESS AND POSTPRESS OR
 * ITS CONTRIBUTORS BE LIABLE FOR ANY DIRECT, INDIRECT, INCIDENTAL,
 * SPECIAL, EXEMPLARY, OR CONSEQUENTIAL DAMAGES (INCLUDING, BUT NOT
 * LIMITED TO, PROCUREMENT OF SUBSTITUTE GOODS OR SERVICES; LOSS OF
 * USE, DATA, OR PROFITS; OR BUSINESS INTERRUPTION) HOWEVER CAUSED AND
 * ON ANY THEORY OF LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY,
 * OR TORT (INCLUDING NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT
 * OF THE USE OF THIS SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.
 * ====================================================================
 * This software consists of voluntary contributions made by many
 * individuals on behalf of the The International Cooperation for the Integration
 * of Processes in Prepress, Press and Postpress and was originally based on software
 * copyright (c) 1999-2001, Heidelberger Druckmaschinen AG copyright (c) 1999-2001, Agfa-Gevaert N.V.
 * For more information on The International Cooperation for the
 * Integration of Processes in  Prepress, Press and Postpress , please see <http://www.cip4.org/>.
 */

package org.cip4.jdflib.resource.process;

import org.apache.xerces.dom.CoreDocumentImpl;
import org.cip4.jdflib.auto.JDFAutoLayoutElement;
import org.cip4.jdflib.core.AttributeName;
import org.cip4.jdflib.core.ElementName;
import org.cip4.jdflib.core.JDFConstants;
import org.cip4.jdflib.datatypes.JDFRectangle;
import org.cip4.jdflib.resource.JDFPageList;
import org.cip4.jdflib.resource.process.prepress.JDFScreeningParams;
import org.w3c.dom.DOMException;


public class JDFLayoutElement extends JDFAutoLayoutElement
{
    private static final long serialVersionUID = 1L;

    /**
     * Constructor for JDFLayoutElement
     * @param ownerDocument
     * @param qualifiedName
     * @throws DOMException
     */
     public JDFLayoutElement(
        CoreDocumentImpl myOwnerDocument,
        String qualifiedName)
        throws DOMException
    {
        super(myOwnerDocument, qualifiedName);
    }


    /**
     * Constructor for JDFLayoutElement
     * @param ownerDocument
     * @param namespaceURI
     * @param qualifiedName
     * @throws DOMException
     */
    public JDFLayoutElement(
        CoreDocumentImpl myOwnerDocument,
        String myNamespaceURI,
        String qualifiedName)
         throws DOMException
    {
        super(myOwnerDocument, myNamespaceURI, qualifiedName);
    }

    /**
     * Constructor for JDFLayoutElement
     * @param ownerDocument
     * @param namespaceURI
     * @param qualifiedName
     * @param localName
     * @throws DOMException
     */
    public JDFLayoutElement(
        CoreDocumentImpl myOwnerDocument,
        String myNamespaceURI,
        String qualifiedName,
        String myLocalName)
        throws DOMException
    {
        super(myOwnerDocument, myNamespaceURI, qualifiedName, myLocalName);
    }

    //**************************************** Methods *********************************************

    /**
     * toString
     * @return String
     */
    public String toString()
    {
        return "JDFLayoutElement[ --> " + super.toString() + " ]";
    }

    /**
     * SetFileName
     * @param String fileName
     */
    public void setFileName(String fileName)
    {
        JDFFileSpec fileSpec = 
            (JDFFileSpec) getCreateElement_KElement (ElementName.FILESPEC, JDFConstants.EMPTYSTRING, 0);
        if (fileSpec != null)
        {
            fileSpec.setURL(fileName);
        }
    }
    
   
    public JDFPageData getPageListPageData()
    {
        //TODO lena - make vector
        return getPageList().getPageData(0);
    }

    public boolean getHasBleeds()
    {
        if (hasAttribute(AttributeName.HASBLEEDS))
        {    
            return super.getHasBleeds();
        }
        return getPageListPageData().getHasBleeds();
    }
    
    public boolean getIsBlank()
    {
        if (hasAttribute(AttributeName.ISBLANK))
        {    
            return super.getIsBlank();
        }
        return getPageListPageData().getIsBlank();
    }
    
    
    public boolean getIsPrintable() 
    {
        if (hasAttribute(AttributeName.ISPRINTABLE))
        {    
            return super.getIsPrintable();
        }
        return getPageListPageData().getIsPrintable();
    }
    
    
    public boolean getIsTrapped() 
    {
        if (hasAttribute(AttributeName.ISTRAPPED))
        {    
            return super.getIsTrapped();
        }
        return getPageListPageData().getIsTrapped();
    }
    
    
    public JDFRectangle getSourceBleedBox()
    {
        if (hasAttribute(AttributeName.SOURCEBLEEDBOX))
        {    
            return super.getSourceBleedBox();
        }
        return getPageListPageData().getSourceBleedBox();
    }
    
    
    public JDFRectangle getSourceClipBox() 
    {
        if (hasAttribute(AttributeName.SOURCECLIPBOX))
        {    
            return super.getSourceClipBox();
        }
        return getPageListPageData().getSourceClipBox();
    }
    
    
    public JDFRectangle getSourceTrimBox() 
    {
        if (hasAttribute(AttributeName.SOURCETRIMBOX))
        {    
            return super.getSourceTrimBox();
        }
        return getPageListPageData().getSourceTrimBox();
    }
    
    
    public boolean getTemplate() 
    {
        if (hasAttribute(AttributeName.TEMPLATE))
        {    
            return super.getTemplate();
        }
        return getPageListPageData().getTemplate();
    }
    
    
    public JDFImageCompressionParams getImageCompressionParams() 
    {
        if (hasChildElement(ElementName.IMAGECOMPRESSIONPARAMS, JDFConstants.EMPTYSTRING))
        {    
            return super.getImageCompressionParams();
        }
        return getPageListPageData().getImageCompressionParams();
    }
    
    public JDFElementColorParams getElementColorParams() 
    {
        if (hasChildElement(ElementName.ELEMENTCOLORPARAMS, JDFConstants.EMPTYSTRING))
        {    
            return super.getElementColorParams();
        }
        return getPageListPageData().getElementColorParams();
    }
    
    public JDFScreeningParams getScreeningParams() 
    {
        if (hasChildElement(ElementName.SCREENINGPARAMS, JDFConstants.EMPTYSTRING))
        {    
            return super.getScreeningParams();
        }
        return getPageListPageData().getScreeningParams();
    }
    
    
    public JDFSeparationSpec getSeparationSpec(int iSkip) 
    {
        if (hasChildElement(ElementName.SEPARATIONSPEC, JDFConstants.EMPTYSTRING))
        {    
            return super.getSeparationSpec(iSkip);
        }
        return (JDFSeparationSpec)getPageListPageData().getElement(ElementName.SEPARATIONSPEC, JDFConstants.EMPTYSTRING, 0);
    }
    
    public JDFPageList getPageList()
    {
        JDFPageList e = (JDFPageList)getElement(ElementName.PAGELIST, JDFConstants.EMPTYSTRING, 0);
        return e;
    }

}
