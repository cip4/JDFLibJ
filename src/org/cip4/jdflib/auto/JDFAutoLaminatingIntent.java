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
import org.cip4.jdflib.core.JDFException;
import org.cip4.jdflib.resource.intent.JDFIntentResource;
import org.cip4.jdflib.span.JDFNameSpan;
import org.cip4.jdflib.span.JDFNumberSpan;
import org.cip4.jdflib.span.JDFOptionSpan;
import org.cip4.jdflib.span.JDFSpanSurface;
import org.cip4.jdflib.span.JDFSpanTemperature;

public abstract class JDFAutoLaminatingIntent extends JDFIntentResource
{

    private static final long serialVersionUID = 1L;

    private static ElemInfoTable[] elemInfoTable = new ElemInfoTable[5];
    static
    {
        elemInfoTable[0] = new ElemInfoTable(ElementName.LAMINATED, 0x77777776);
        elemInfoTable[1] = new ElemInfoTable(ElementName.TEMPERATURE, 0x55555555);
        elemInfoTable[2] = new ElemInfoTable(ElementName.SURFACE, 0x66666666);
        elemInfoTable[3] = new ElemInfoTable(ElementName.TEXTURE, 0x66666111);
        elemInfoTable[4] = new ElemInfoTable(ElementName.THICKNESS, 0x66666666);
    }
    
    protected ElementInfo getTheElementInfo()
    {
        return super.getTheElementInfo().updateReplace(elemInfoTable);
    }



    /**
     * Constructor for JDFAutoLaminatingIntent
     * @param myOwnerDocument
     * @param qualifiedName
     */
    protected JDFAutoLaminatingIntent(
        CoreDocumentImpl myOwnerDocument,
        String qualifiedName)
    {
        super(myOwnerDocument, qualifiedName);
    }

    /**
     * Constructor for JDFAutoLaminatingIntent
     * @param myOwnerDocument
     * @param myNamespaceURI
     * @param qualifiedName
     */
    protected JDFAutoLaminatingIntent(
        CoreDocumentImpl myOwnerDocument,
        String myNamespaceURI,
        String qualifiedName)
    {
        super(myOwnerDocument, myNamespaceURI, qualifiedName);
    }

    /**
     * Constructor for JDFAutoLaminatingIntent
     * @param myOwnerDocument
     * @param myNamespaceURI
     * @param qualifiedName
     * @param myLocalName
     */
    protected JDFAutoLaminatingIntent(
        CoreDocumentImpl myOwnerDocument,
        String myNamespaceURI,
        String qualifiedName,
        String myLocalName)
    {
        super(myOwnerDocument, myNamespaceURI, qualifiedName, myLocalName);
    }


    public String toString()
    {
        return " JDFAutoLaminatingIntent[  --> " + super.toString() + " ]";
    }


/* ***********************************************************************
 * Element getter / setter
 * ***********************************************************************
 */

    /**
     * (24) const get element Laminated
     * @return JDFOptionSpan the element
     */
    public JDFOptionSpan getLaminated()
    {
        return (JDFOptionSpan) getElement(ElementName.LAMINATED, null, 0);
    }

    /** (25) getCreateLaminated
     * 
     * @return JDFOptionSpan the element
     */
    public JDFOptionSpan getCreateLaminated()
    {
        return (JDFOptionSpan) getCreateElement_KElement(ElementName.LAMINATED, null, 0);
    }

    /**
     * (29) append element Laminated
     */
    public JDFOptionSpan appendLaminated() throws JDFException
    {
        return (JDFOptionSpan) appendElementN(ElementName.LAMINATED, 1, null);
    }

    /**
     * (24) const get element Temperature
     * @return JDFSpanTemperature the element
     */
    public JDFSpanTemperature getTemperature()
    {
        return (JDFSpanTemperature) getElement(ElementName.TEMPERATURE, null, 0);
    }

    /** (25) getCreateTemperature
     * 
     * @return JDFSpanTemperature the element
     */
    public JDFSpanTemperature getCreateTemperature()
    {
        return (JDFSpanTemperature) getCreateElement_KElement(ElementName.TEMPERATURE, null, 0);
    }

    /**
     * (29) append element Temperature
     */
    public JDFSpanTemperature appendTemperature() throws JDFException
    {
        return (JDFSpanTemperature) appendElementN(ElementName.TEMPERATURE, 1, null);
    }

    /**
     * (24) const get element Surface
     * @return JDFSpanSurface the element
     */
    public JDFSpanSurface getSurface()
    {
        return (JDFSpanSurface) getElement(ElementName.SURFACE, null, 0);
    }

    /** (25) getCreateSurface
     * 
     * @return JDFSpanSurface the element
     */
    public JDFSpanSurface getCreateSurface()
    {
        return (JDFSpanSurface) getCreateElement_KElement(ElementName.SURFACE, null, 0);
    }

    /**
     * (29) append element Surface
     */
    public JDFSpanSurface appendSurface() throws JDFException
    {
        return (JDFSpanSurface) appendElementN(ElementName.SURFACE, 1, null);
    }

    /**
     * (24) const get element Texture
     * @return JDFNameSpan the element
     */
    public JDFNameSpan getTexture()
    {
        return (JDFNameSpan) getElement(ElementName.TEXTURE, null, 0);
    }

    /** (25) getCreateTexture
     * 
     * @return JDFNameSpan the element
     */
    public JDFNameSpan getCreateTexture()
    {
        return (JDFNameSpan) getCreateElement_KElement(ElementName.TEXTURE, null, 0);
    }

    /**
     * (29) append element Texture
     */
    public JDFNameSpan appendTexture() throws JDFException
    {
        return (JDFNameSpan) appendElementN(ElementName.TEXTURE, 1, null);
    }

    /**
     * (24) const get element Thickness
     * @return JDFNumberSpan the element
     */
    public JDFNumberSpan getThickness()
    {
        return (JDFNumberSpan) getElement(ElementName.THICKNESS, null, 0);
    }

    /** (25) getCreateThickness
     * 
     * @return JDFNumberSpan the element
     */
    public JDFNumberSpan getCreateThickness()
    {
        return (JDFNumberSpan) getCreateElement_KElement(ElementName.THICKNESS, null, 0);
    }

    /**
     * (29) append element Thickness
     */
    public JDFNumberSpan appendThickness() throws JDFException
    {
        return (JDFNumberSpan) appendElementN(ElementName.THICKNESS, 1, null);
    }

}// end namespace JDF
