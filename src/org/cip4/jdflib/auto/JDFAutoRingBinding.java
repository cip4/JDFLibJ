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
import org.cip4.jdflib.span.JDFNameSpan;
import org.cip4.jdflib.span.JDFNumberSpan;
import org.cip4.jdflib.span.JDFOptionSpan;
import org.cip4.jdflib.span.JDFSpanHoleType;
import org.cip4.jdflib.span.JDFStringSpan;

public abstract class JDFAutoRingBinding extends JDFElement
{

    private static final long serialVersionUID = 1L;

    private static ElemInfoTable[] elemInfoTable = new ElemInfoTable[9];
    static
    {
        elemInfoTable[0] = new ElemInfoTable(ElementName.BINDERBRAND, 0x66666111);
        elemInfoTable[1] = new ElemInfoTable(ElementName.BINDERMATERIAL, 0x66666666);
        elemInfoTable[2] = new ElemInfoTable(ElementName.HOLETYPE, 0x66666661);
        elemInfoTable[3] = new ElemInfoTable(ElementName.RINGDIAMETER, 0x66666666);
        elemInfoTable[4] = new ElemInfoTable(ElementName.RINGMECHANIC, 0x66666666);
        elemInfoTable[5] = new ElemInfoTable(ElementName.RINGSHAPE, 0x66666666);
        elemInfoTable[6] = new ElemInfoTable(ElementName.RINGSYSTEM, 0x77777776);
        elemInfoTable[7] = new ElemInfoTable(ElementName.RIVETSEXPOSED, 0x66666666);
        elemInfoTable[8] = new ElemInfoTable(ElementName.VIEWBINDER, 0x66666666);
    }
    
    @Override
	protected ElementInfo getTheElementInfo()
    {
        return super.getTheElementInfo().updateReplace(elemInfoTable);
    }



    /**
     * Constructor for JDFAutoRingBinding
     * @param myOwnerDocument
     * @param qualifiedName
     */
    protected JDFAutoRingBinding(
        CoreDocumentImpl myOwnerDocument,
        String qualifiedName)
    {
        super(myOwnerDocument, qualifiedName);
    }

    /**
     * Constructor for JDFAutoRingBinding
     * @param myOwnerDocument
     * @param myNamespaceURI
     * @param qualifiedName
     */
    protected JDFAutoRingBinding(
        CoreDocumentImpl myOwnerDocument,
        String myNamespaceURI,
        String qualifiedName)
    {
        super(myOwnerDocument, myNamespaceURI, qualifiedName);
    }

    /**
     * Constructor for JDFAutoRingBinding
     * @param myOwnerDocument
     * @param myNamespaceURI
     * @param qualifiedName
     * @param myLocalName
     */
    protected JDFAutoRingBinding(
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
        return " JDFAutoRingBinding[  --> " + super.toString() + " ]";
    }


/* ***********************************************************************
 * Element getter / setter
 * ***********************************************************************
 */

    /**
     * (24) const get element BinderBrand
     * @return JDFStringSpan the element
     */
    public JDFStringSpan getBinderBrand()
    {
        return (JDFStringSpan) getElement(ElementName.BINDERBRAND, null, 0);
    }

    /** (25) getCreateBinderBrand
     * 
     * @return JDFStringSpan the element
     */
    public JDFStringSpan getCreateBinderBrand()
    {
        return (JDFStringSpan) getCreateElement_KElement(ElementName.BINDERBRAND, null, 0);
    }

    /**
     * (29) append element BinderBrand
     */
    public JDFStringSpan appendBinderBrand() throws JDFException
    {
        return (JDFStringSpan) appendElementN(ElementName.BINDERBRAND, 1, null);
    }

    /**
     * (24) const get element BinderMaterial
     * @return JDFNameSpan the element
     */
    public JDFNameSpan getBinderMaterial()
    {
        return (JDFNameSpan) getElement(ElementName.BINDERMATERIAL, null, 0);
    }

    /** (25) getCreateBinderMaterial
     * 
     * @return JDFNameSpan the element
     */
    public JDFNameSpan getCreateBinderMaterial()
    {
        return (JDFNameSpan) getCreateElement_KElement(ElementName.BINDERMATERIAL, null, 0);
    }

    /**
     * (29) append element BinderMaterial
     */
    public JDFNameSpan appendBinderMaterial() throws JDFException
    {
        return (JDFNameSpan) appendElementN(ElementName.BINDERMATERIAL, 1, null);
    }

    /**
     * (24) const get element HoleType
     * @return JDFSpanHoleType the element
     */
    public JDFSpanHoleType getHoleType()
    {
        return (JDFSpanHoleType) getElement(ElementName.HOLETYPE, null, 0);
    }

    /** (25) getCreateHoleType
     * 
     * @return JDFSpanHoleType the element
     */
    public JDFSpanHoleType getCreateHoleType()
    {
        return (JDFSpanHoleType) getCreateElement_KElement(ElementName.HOLETYPE, null, 0);
    }

    /**
     * (29) append element HoleType
     */
    public JDFSpanHoleType appendHoleType() throws JDFException
    {
        return (JDFSpanHoleType) appendElementN(ElementName.HOLETYPE, 1, null);
    }

    /**
     * (24) const get element RingDiameter
     * @return JDFNumberSpan the element
     */
    public JDFNumberSpan getRingDiameter()
    {
        return (JDFNumberSpan) getElement(ElementName.RINGDIAMETER, null, 0);
    }

    /** (25) getCreateRingDiameter
     * 
     * @return JDFNumberSpan the element
     */
    public JDFNumberSpan getCreateRingDiameter()
    {
        return (JDFNumberSpan) getCreateElement_KElement(ElementName.RINGDIAMETER, null, 0);
    }

    /**
     * (29) append element RingDiameter
     */
    public JDFNumberSpan appendRingDiameter() throws JDFException
    {
        return (JDFNumberSpan) appendElementN(ElementName.RINGDIAMETER, 1, null);
    }

    /**
     * (24) const get element RingMechanic
     * @return JDFOptionSpan the element
     */
    public JDFOptionSpan getRingMechanic()
    {
        return (JDFOptionSpan) getElement(ElementName.RINGMECHANIC, null, 0);
    }

    /** (25) getCreateRingMechanic
     * 
     * @return JDFOptionSpan the element
     */
    public JDFOptionSpan getCreateRingMechanic()
    {
        return (JDFOptionSpan) getCreateElement_KElement(ElementName.RINGMECHANIC, null, 0);
    }

    /**
     * (29) append element RingMechanic
     */
    public JDFOptionSpan appendRingMechanic() throws JDFException
    {
        return (JDFOptionSpan) appendElementN(ElementName.RINGMECHANIC, 1, null);
    }

    /**
     * (24) const get element RingShape
     * @return JDFNameSpan the element
     */
    public JDFNameSpan getRingShape()
    {
        return (JDFNameSpan) getElement(ElementName.RINGSHAPE, null, 0);
    }

    /** (25) getCreateRingShape
     * 
     * @return JDFNameSpan the element
     */
    public JDFNameSpan getCreateRingShape()
    {
        return (JDFNameSpan) getCreateElement_KElement(ElementName.RINGSHAPE, null, 0);
    }

    /**
     * (29) append element RingShape
     */
    public JDFNameSpan appendRingShape() throws JDFException
    {
        return (JDFNameSpan) appendElementN(ElementName.RINGSHAPE, 1, null);
    }

    /**
     * (24) const get element RingSystem
     * @return JDFNameSpan the element
     */
    public JDFNameSpan getRingSystem()
    {
        return (JDFNameSpan) getElement(ElementName.RINGSYSTEM, null, 0);
    }

    /** (25) getCreateRingSystem
     * 
     * @return JDFNameSpan the element
     */
    public JDFNameSpan getCreateRingSystem()
    {
        return (JDFNameSpan) getCreateElement_KElement(ElementName.RINGSYSTEM, null, 0);
    }

    /**
     * (29) append element RingSystem
     */
    public JDFNameSpan appendRingSystem() throws JDFException
    {
        return (JDFNameSpan) appendElementN(ElementName.RINGSYSTEM, 1, null);
    }

    /**
     * (24) const get element RivetsExposed
     * @return JDFOptionSpan the element
     */
    public JDFOptionSpan getRivetsExposed()
    {
        return (JDFOptionSpan) getElement(ElementName.RIVETSEXPOSED, null, 0);
    }

    /** (25) getCreateRivetsExposed
     * 
     * @return JDFOptionSpan the element
     */
    public JDFOptionSpan getCreateRivetsExposed()
    {
        return (JDFOptionSpan) getCreateElement_KElement(ElementName.RIVETSEXPOSED, null, 0);
    }

    /**
     * (29) append element RivetsExposed
     */
    public JDFOptionSpan appendRivetsExposed() throws JDFException
    {
        return (JDFOptionSpan) appendElementN(ElementName.RIVETSEXPOSED, 1, null);
    }

    /**
     * (24) const get element ViewBinder
     * @return JDFNameSpan the element
     */
    public JDFNameSpan getViewBinder()
    {
        return (JDFNameSpan) getElement(ElementName.VIEWBINDER, null, 0);
    }

    /** (25) getCreateViewBinder
     * 
     * @return JDFNameSpan the element
     */
    public JDFNameSpan getCreateViewBinder()
    {
        return (JDFNameSpan) getCreateElement_KElement(ElementName.VIEWBINDER, null, 0);
    }

    /**
     * (29) append element ViewBinder
     */
    public JDFNameSpan appendViewBinder() throws JDFException
    {
        return (JDFNameSpan) appendElementN(ElementName.VIEWBINDER, 1, null);
    }

}// end namespace JDF
