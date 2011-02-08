/*
 * The CIP4 Software License, Version 1.0
 *
 *
 * Copyright (c) 2001-2010 The International Cooperation for the Integration of
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
import org.cip4.jdflib.span.JDFIntegerSpan;
import org.cip4.jdflib.span.JDFNameSpan;
import org.cip4.jdflib.span.JDFNumberSpan;
import org.cip4.jdflib.span.JDFShapeSpan;
import org.cip4.jdflib.span.JDFXYPairSpan;
    /**
    *****************************************************************************
    class JDFAutoPackingIntent : public JDFIntentResource

    *****************************************************************************
    */

public abstract class JDFAutoPackingIntent extends JDFIntentResource
{

    private static final long serialVersionUID = 1L;

    private static ElemInfoTable[] elemInfoTable = new ElemInfoTable[16];
    static
    {
        elemInfoTable[0] = new ElemInfoTable(ElementName.BOXEDQUANTITY, 0x66666666);
        elemInfoTable[1] = new ElemInfoTable(ElementName.BOXSHAPE, 0x66666666);
        elemInfoTable[2] = new ElemInfoTable(ElementName.CARTONQUANTITY, 0x66666666);
        elemInfoTable[3] = new ElemInfoTable(ElementName.CARTONSHAPE, 0x66666666);
        elemInfoTable[4] = new ElemInfoTable(ElementName.CARTONMAXWEIGHT, 0x66666666);
        elemInfoTable[5] = new ElemInfoTable(ElementName.CARTONSTRENGTH, 0x66666666);
        elemInfoTable[6] = new ElemInfoTable(ElementName.FOLDINGCATALOG, 0x66666666);
        elemInfoTable[7] = new ElemInfoTable(ElementName.PALLETCORNERBOARDS, 0x66666111);
        elemInfoTable[8] = new ElemInfoTable(ElementName.PALLETQUANTITY, 0x66666666);
        elemInfoTable[9] = new ElemInfoTable(ElementName.PALLETSIZE, 0x66666666);
        elemInfoTable[10] = new ElemInfoTable(ElementName.PALLETMAXHEIGHT, 0x66666666);
        elemInfoTable[11] = new ElemInfoTable(ElementName.PALLETMAXWEIGHT, 0x66666666);
        elemInfoTable[12] = new ElemInfoTable(ElementName.PALLETTYPE, 0x66666666);
        elemInfoTable[13] = new ElemInfoTable(ElementName.PALLETWRAPPING, 0x66666666);
        elemInfoTable[14] = new ElemInfoTable(ElementName.WRAPPEDQUANTITY, 0x66666666);
        elemInfoTable[15] = new ElemInfoTable(ElementName.WRAPPINGMATERIAL, 0x66666666);
    }
    
    protected ElementInfo getTheElementInfo()
    {
        return super.getTheElementInfo().updateReplace(elemInfoTable);
    }



    /**
     * Constructor for JDFAutoPackingIntent
     * @param myOwnerDocument
     * @param qualifiedName
     */
    protected JDFAutoPackingIntent(
        CoreDocumentImpl myOwnerDocument,
        String qualifiedName)
    {
        super(myOwnerDocument, qualifiedName);
    }

    /**
     * Constructor for JDFAutoPackingIntent
     * @param myOwnerDocument
     * @param myNamespaceURI
     * @param qualifiedName
     */
    protected JDFAutoPackingIntent(
        CoreDocumentImpl myOwnerDocument,
        String myNamespaceURI,
        String qualifiedName)
    {
        super(myOwnerDocument, myNamespaceURI, qualifiedName);
    }

    /**
     * Constructor for JDFAutoPackingIntent
     * @param myOwnerDocument
     * @param myNamespaceURI
     * @param qualifiedName
     * @param myLocalName
     */
    protected JDFAutoPackingIntent(
        CoreDocumentImpl myOwnerDocument,
        String myNamespaceURI,
        String qualifiedName,
        String myLocalName)
    {
        super(myOwnerDocument, myNamespaceURI, qualifiedName, myLocalName);
    }


    public String toString()
    {
        return " JDFAutoPackingIntent[  --> " + super.toString() + " ]";
    }


/* ***********************************************************************
 * Element getter / setter
 * ***********************************************************************
 */

    /**
     * (24) const get element BoxedQuantity
     * @return JDFIntegerSpan the element
     */
    public JDFIntegerSpan getBoxedQuantity()
    {
        return (JDFIntegerSpan) getElement(ElementName.BOXEDQUANTITY, null, 0);
    }

    /** (25) getCreateBoxedQuantity
     * 
     * @return JDFIntegerSpan the element
     */
    public JDFIntegerSpan getCreateBoxedQuantity()
    {
        return (JDFIntegerSpan) getCreateElement_KElement(ElementName.BOXEDQUANTITY, null, 0);
    }

    /**
     * (29) append element BoxedQuantity
     */
    public JDFIntegerSpan appendBoxedQuantity() throws JDFException
    {
        return (JDFIntegerSpan) appendElementN(ElementName.BOXEDQUANTITY, 1, null);
    }

    /**
     * (24) const get element BoxShape
     * @return JDFShapeSpan the element
     */
    public JDFShapeSpan getBoxShape()
    {
        return (JDFShapeSpan) getElement(ElementName.BOXSHAPE, null, 0);
    }

    /** (25) getCreateBoxShape
     * 
     * @return JDFShapeSpan the element
     */
    public JDFShapeSpan getCreateBoxShape()
    {
        return (JDFShapeSpan) getCreateElement_KElement(ElementName.BOXSHAPE, null, 0);
    }

    /**
     * (29) append element BoxShape
     */
    public JDFShapeSpan appendBoxShape() throws JDFException
    {
        return (JDFShapeSpan) appendElementN(ElementName.BOXSHAPE, 1, null);
    }

    /**
     * (24) const get element CartonQuantity
     * @return JDFIntegerSpan the element
     */
    public JDFIntegerSpan getCartonQuantity()
    {
        return (JDFIntegerSpan) getElement(ElementName.CARTONQUANTITY, null, 0);
    }

    /** (25) getCreateCartonQuantity
     * 
     * @return JDFIntegerSpan the element
     */
    public JDFIntegerSpan getCreateCartonQuantity()
    {
        return (JDFIntegerSpan) getCreateElement_KElement(ElementName.CARTONQUANTITY, null, 0);
    }

    /**
     * (29) append element CartonQuantity
     */
    public JDFIntegerSpan appendCartonQuantity() throws JDFException
    {
        return (JDFIntegerSpan) appendElementN(ElementName.CARTONQUANTITY, 1, null);
    }

    /**
     * (24) const get element CartonShape
     * @return JDFShapeSpan the element
     */
    public JDFShapeSpan getCartonShape()
    {
        return (JDFShapeSpan) getElement(ElementName.CARTONSHAPE, null, 0);
    }

    /** (25) getCreateCartonShape
     * 
     * @return JDFShapeSpan the element
     */
    public JDFShapeSpan getCreateCartonShape()
    {
        return (JDFShapeSpan) getCreateElement_KElement(ElementName.CARTONSHAPE, null, 0);
    }

    /**
     * (29) append element CartonShape
     */
    public JDFShapeSpan appendCartonShape() throws JDFException
    {
        return (JDFShapeSpan) appendElementN(ElementName.CARTONSHAPE, 1, null);
    }

    /**
     * (24) const get element CartonMaxWeight
     * @return JDFNumberSpan the element
     */
    public JDFNumberSpan getCartonMaxWeight()
    {
        return (JDFNumberSpan) getElement(ElementName.CARTONMAXWEIGHT, null, 0);
    }

    /** (25) getCreateCartonMaxWeight
     * 
     * @return JDFNumberSpan the element
     */
    public JDFNumberSpan getCreateCartonMaxWeight()
    {
        return (JDFNumberSpan) getCreateElement_KElement(ElementName.CARTONMAXWEIGHT, null, 0);
    }

    /**
     * (29) append element CartonMaxWeight
     */
    public JDFNumberSpan appendCartonMaxWeight() throws JDFException
    {
        return (JDFNumberSpan) appendElementN(ElementName.CARTONMAXWEIGHT, 1, null);
    }

    /**
     * (24) const get element CartonStrength
     * @return JDFNumberSpan the element
     */
    public JDFNumberSpan getCartonStrength()
    {
        return (JDFNumberSpan) getElement(ElementName.CARTONSTRENGTH, null, 0);
    }

    /** (25) getCreateCartonStrength
     * 
     * @return JDFNumberSpan the element
     */
    public JDFNumberSpan getCreateCartonStrength()
    {
        return (JDFNumberSpan) getCreateElement_KElement(ElementName.CARTONSTRENGTH, null, 0);
    }

    /**
     * (29) append element CartonStrength
     */
    public JDFNumberSpan appendCartonStrength() throws JDFException
    {
        return (JDFNumberSpan) appendElementN(ElementName.CARTONSTRENGTH, 1, null);
    }

    /**
     * (24) const get element FoldingCatalog
     * @return JDFNameSpan the element
     */
    public JDFNameSpan getFoldingCatalog()
    {
        return (JDFNameSpan) getElement(ElementName.FOLDINGCATALOG, null, 0);
    }

    /** (25) getCreateFoldingCatalog
     * 
     * @return JDFNameSpan the element
     */
    public JDFNameSpan getCreateFoldingCatalog()
    {
        return (JDFNameSpan) getCreateElement_KElement(ElementName.FOLDINGCATALOG, null, 0);
    }

    /**
     * (29) append element FoldingCatalog
     */
    public JDFNameSpan appendFoldingCatalog() throws JDFException
    {
        return (JDFNameSpan) appendElementN(ElementName.FOLDINGCATALOG, 1, null);
    }

    /**
     * (24) const get element PalletCornerBoards
     * @return JDFNameSpan the element
     */
    public JDFNameSpan getPalletCornerBoards()
    {
        return (JDFNameSpan) getElement(ElementName.PALLETCORNERBOARDS, null, 0);
    }

    /** (25) getCreatePalletCornerBoards
     * 
     * @return JDFNameSpan the element
     */
    public JDFNameSpan getCreatePalletCornerBoards()
    {
        return (JDFNameSpan) getCreateElement_KElement(ElementName.PALLETCORNERBOARDS, null, 0);
    }

    /**
     * (29) append element PalletCornerBoards
     */
    public JDFNameSpan appendPalletCornerBoards() throws JDFException
    {
        return (JDFNameSpan) appendElementN(ElementName.PALLETCORNERBOARDS, 1, null);
    }

    /**
     * (24) const get element PalletQuantity
     * @return JDFIntegerSpan the element
     */
    public JDFIntegerSpan getPalletQuantity()
    {
        return (JDFIntegerSpan) getElement(ElementName.PALLETQUANTITY, null, 0);
    }

    /** (25) getCreatePalletQuantity
     * 
     * @return JDFIntegerSpan the element
     */
    public JDFIntegerSpan getCreatePalletQuantity()
    {
        return (JDFIntegerSpan) getCreateElement_KElement(ElementName.PALLETQUANTITY, null, 0);
    }

    /**
     * (29) append element PalletQuantity
     */
    public JDFIntegerSpan appendPalletQuantity() throws JDFException
    {
        return (JDFIntegerSpan) appendElementN(ElementName.PALLETQUANTITY, 1, null);
    }

    /**
     * (24) const get element PalletSize
     * @return JDFXYPairSpan the element
     */
    public JDFXYPairSpan getPalletSize()
    {
        return (JDFXYPairSpan) getElement(ElementName.PALLETSIZE, null, 0);
    }

    /** (25) getCreatePalletSize
     * 
     * @return JDFXYPairSpan the element
     */
    public JDFXYPairSpan getCreatePalletSize()
    {
        return (JDFXYPairSpan) getCreateElement_KElement(ElementName.PALLETSIZE, null, 0);
    }

    /**
     * (29) append element PalletSize
     */
    public JDFXYPairSpan appendPalletSize() throws JDFException
    {
        return (JDFXYPairSpan) appendElementN(ElementName.PALLETSIZE, 1, null);
    }

    /**
     * (24) const get element PalletMaxHeight
     * @return JDFNumberSpan the element
     */
    public JDFNumberSpan getPalletMaxHeight()
    {
        return (JDFNumberSpan) getElement(ElementName.PALLETMAXHEIGHT, null, 0);
    }

    /** (25) getCreatePalletMaxHeight
     * 
     * @return JDFNumberSpan the element
     */
    public JDFNumberSpan getCreatePalletMaxHeight()
    {
        return (JDFNumberSpan) getCreateElement_KElement(ElementName.PALLETMAXHEIGHT, null, 0);
    }

    /**
     * (29) append element PalletMaxHeight
     */
    public JDFNumberSpan appendPalletMaxHeight() throws JDFException
    {
        return (JDFNumberSpan) appendElementN(ElementName.PALLETMAXHEIGHT, 1, null);
    }

    /**
     * (24) const get element PalletMaxWeight
     * @return JDFNumberSpan the element
     */
    public JDFNumberSpan getPalletMaxWeight()
    {
        return (JDFNumberSpan) getElement(ElementName.PALLETMAXWEIGHT, null, 0);
    }

    /** (25) getCreatePalletMaxWeight
     * 
     * @return JDFNumberSpan the element
     */
    public JDFNumberSpan getCreatePalletMaxWeight()
    {
        return (JDFNumberSpan) getCreateElement_KElement(ElementName.PALLETMAXWEIGHT, null, 0);
    }

    /**
     * (29) append element PalletMaxWeight
     */
    public JDFNumberSpan appendPalletMaxWeight() throws JDFException
    {
        return (JDFNumberSpan) appendElementN(ElementName.PALLETMAXWEIGHT, 1, null);
    }

    /**
     * (24) const get element PalletType
     * @return JDFNameSpan the element
     */
    public JDFNameSpan getPalletType()
    {
        return (JDFNameSpan) getElement(ElementName.PALLETTYPE, null, 0);
    }

    /** (25) getCreatePalletType
     * 
     * @return JDFNameSpan the element
     */
    public JDFNameSpan getCreatePalletType()
    {
        return (JDFNameSpan) getCreateElement_KElement(ElementName.PALLETTYPE, null, 0);
    }

    /**
     * (29) append element PalletType
     */
    public JDFNameSpan appendPalletType() throws JDFException
    {
        return (JDFNameSpan) appendElementN(ElementName.PALLETTYPE, 1, null);
    }

    /**
     * (24) const get element PalletWrapping
     * @return JDFNameSpan the element
     */
    public JDFNameSpan getPalletWrapping()
    {
        return (JDFNameSpan) getElement(ElementName.PALLETWRAPPING, null, 0);
    }

    /** (25) getCreatePalletWrapping
     * 
     * @return JDFNameSpan the element
     */
    public JDFNameSpan getCreatePalletWrapping()
    {
        return (JDFNameSpan) getCreateElement_KElement(ElementName.PALLETWRAPPING, null, 0);
    }

    /**
     * (29) append element PalletWrapping
     */
    public JDFNameSpan appendPalletWrapping() throws JDFException
    {
        return (JDFNameSpan) appendElementN(ElementName.PALLETWRAPPING, 1, null);
    }

    /**
     * (24) const get element WrappedQuantity
     * @return JDFIntegerSpan the element
     */
    public JDFIntegerSpan getWrappedQuantity()
    {
        return (JDFIntegerSpan) getElement(ElementName.WRAPPEDQUANTITY, null, 0);
    }

    /** (25) getCreateWrappedQuantity
     * 
     * @return JDFIntegerSpan the element
     */
    public JDFIntegerSpan getCreateWrappedQuantity()
    {
        return (JDFIntegerSpan) getCreateElement_KElement(ElementName.WRAPPEDQUANTITY, null, 0);
    }

    /**
     * (29) append element WrappedQuantity
     */
    public JDFIntegerSpan appendWrappedQuantity() throws JDFException
    {
        return (JDFIntegerSpan) appendElementN(ElementName.WRAPPEDQUANTITY, 1, null);
    }

    /**
     * (24) const get element WrappingMaterial
     * @return JDFNameSpan the element
     */
    public JDFNameSpan getWrappingMaterial()
    {
        return (JDFNameSpan) getElement(ElementName.WRAPPINGMATERIAL, null, 0);
    }

    /** (25) getCreateWrappingMaterial
     * 
     * @return JDFNameSpan the element
     */
    public JDFNameSpan getCreateWrappingMaterial()
    {
        return (JDFNameSpan) getCreateElement_KElement(ElementName.WRAPPINGMATERIAL, null, 0);
    }

    /**
     * (29) append element WrappingMaterial
     */
    public JDFNameSpan appendWrappingMaterial() throws JDFException
    {
        return (JDFNameSpan) appendElementN(ElementName.WRAPPINGMATERIAL, 1, null);
    }

}// end namespace JDF
