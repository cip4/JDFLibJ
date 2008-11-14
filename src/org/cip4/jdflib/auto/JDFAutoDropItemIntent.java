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
import org.cip4.jdflib.resource.intent.*;           
import org.cip4.jdflib.resource.process.*;          
import org.cip4.jdflib.resource.process.prepress.*;

public abstract class JDFAutoDropItemIntent extends JDFElement
{

    private static final long serialVersionUID = 1L;

    private static AtrInfoTable[] atrInfoTable = new AtrInfoTable[5];
    static
    {
        atrInfoTable[0] = new AtrInfoTable(AttributeName.ADDITIONALAMOUNT, 0x44444333, AttributeInfo.EnumAttributeType.integer, null, null);
        atrInfoTable[1] = new AtrInfoTable(AttributeName.AMOUNT, 0x33333333, AttributeInfo.EnumAttributeType.integer, null, null);
        atrInfoTable[2] = new AtrInfoTable(AttributeName.ORDEREDAMOUNT, 0x33333333, AttributeInfo.EnumAttributeType.integer, null, null);
        atrInfoTable[3] = new AtrInfoTable(AttributeName.PROOF, 0x33333331, AttributeInfo.EnumAttributeType.string, null, null);
        atrInfoTable[4] = new AtrInfoTable(AttributeName.UNIT, 0x33333333, AttributeInfo.EnumAttributeType.string, null, null);
    }
    
    @Override
	protected AttributeInfo getTheAttributeInfo()
    {
        return super.getTheAttributeInfo().updateReplace(atrInfoTable);
    }


    private static ElemInfoTable[] elemInfoTable = new ElemInfoTable[12];
    static
    {
        elemInfoTable[0] = new ElemInfoTable(ElementName.PRICING, 0x77777666);
        elemInfoTable[1] = new ElemInfoTable(ElementName.COMPONENT, 0x66666666);
        elemInfoTable[2] = new ElemInfoTable(ElementName.EXPOSEDMEDIA, 0x66666666);
        elemInfoTable[3] = new ElemInfoTable(ElementName.INK, 0x66666666);
        elemInfoTable[4] = new ElemInfoTable(ElementName.MEDIA, 0x66666666);
        elemInfoTable[5] = new ElemInfoTable(ElementName.PALLET, 0x66666666);
        elemInfoTable[6] = new ElemInfoTable(ElementName.REGISTERRIBBON, 0x66666666);
        elemInfoTable[7] = new ElemInfoTable(ElementName.STRAP, 0x66666666);
        elemInfoTable[8] = new ElemInfoTable(ElementName.BUNDLE, 0x66666666);
        elemInfoTable[9] = new ElemInfoTable(ElementName.DIGITALMEDIA, 0x66666666);
        elemInfoTable[10] = new ElemInfoTable(ElementName.ROLLSTAND, 0x66666666);
        elemInfoTable[11] = new ElemInfoTable(ElementName.TOOL, 0x66666666);
    }
    
    @Override
	protected ElementInfo getTheElementInfo()
    {
        return super.getTheElementInfo().updateReplace(elemInfoTable);
    }



    /**
     * Constructor for JDFAutoDropItemIntent
     * @param myOwnerDocument
     * @param qualifiedName
     */
    protected JDFAutoDropItemIntent(
        CoreDocumentImpl myOwnerDocument,
        String qualifiedName)
    {
        super(myOwnerDocument, qualifiedName);
    }

    /**
     * Constructor for JDFAutoDropItemIntent
     * @param myOwnerDocument
     * @param myNamespaceURI
     * @param qualifiedName
     */
    protected JDFAutoDropItemIntent(
        CoreDocumentImpl myOwnerDocument,
        String myNamespaceURI,
        String qualifiedName)
    {
        super(myOwnerDocument, myNamespaceURI, qualifiedName);
    }

    /**
     * Constructor for JDFAutoDropItemIntent
     * @param myOwnerDocument
     * @param myNamespaceURI
     * @param qualifiedName
     * @param myLocalName
     */
    protected JDFAutoDropItemIntent(
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
        return " JDFAutoDropItemIntent[  --> " + super.toString() + " ]";
    }


/* ************************************************************************
 * Attribute getter / setter
 * ************************************************************************
 */
        
        /* ---------------------------------------------------------------------
        Methods for Attribute AdditionalAmount
        --------------------------------------------------------------------- */
        /**
          * (36) set attribute AdditionalAmount
          * @param value: the value to set the attribute to
          */
        public void setAdditionalAmount(int value)
        {
            setAttribute(AttributeName.ADDITIONALAMOUNT, value, null);
        }

        /**
          * (15) get int attribute AdditionalAmount
          * @return int the value of the attribute
          */
        public int getAdditionalAmount()
        {
            return getIntAttribute(AttributeName.ADDITIONALAMOUNT, null, 0);
        }

        
        /* ---------------------------------------------------------------------
        Methods for Attribute Amount
        --------------------------------------------------------------------- */
        /**
          * (36) set attribute Amount
          * @param value: the value to set the attribute to
          */
        public void setAmount(int value)
        {
            setAttribute(AttributeName.AMOUNT, value, null);
        }

        /**
          * (15) get int attribute Amount
          * @return int the value of the attribute
          */
        public int getAmount()
        {
            return getIntAttribute(AttributeName.AMOUNT, null, 0);
        }

        
        /* ---------------------------------------------------------------------
        Methods for Attribute OrderedAmount
        --------------------------------------------------------------------- */
        /**
          * (36) set attribute OrderedAmount
          * @param value: the value to set the attribute to
          */
        public void setOrderedAmount(int value)
        {
            setAttribute(AttributeName.ORDEREDAMOUNT, value, null);
        }

        /**
          * (15) get int attribute OrderedAmount
          * @return int the value of the attribute
          */
        public int getOrderedAmount()
        {
            return getIntAttribute(AttributeName.ORDEREDAMOUNT, null, 0);
        }

        
        /* ---------------------------------------------------------------------
        Methods for Attribute Proof
        --------------------------------------------------------------------- */
        /**
          * (36) set attribute Proof
          * @param value: the value to set the attribute to
          */
        public void setProof(String value)
        {
            setAttribute(AttributeName.PROOF, value, null);
        }

        /**
          * (23) get String attribute Proof
          * @return the value of the attribute
          */
        public String getProof()
        {
            return getAttribute(AttributeName.PROOF, null, JDFConstants.EMPTYSTRING);
        }

        
        /* ---------------------------------------------------------------------
        Methods for Attribute Unit
        --------------------------------------------------------------------- */
        /**
          * (36) set attribute Unit
          * @param value: the value to set the attribute to
          */
        public void setUnit(String value)
        {
            setAttribute(AttributeName.UNIT, value, null);
        }

        /**
          * (23) get String attribute Unit
          * @return the value of the attribute
          */
        public String getUnit()
        {
            return getAttribute(AttributeName.UNIT, null, JDFConstants.EMPTYSTRING);
        }

/* ***********************************************************************
 * Element getter / setter
 * ***********************************************************************
 */

    /**
     * (24) const get element Pricing
     * @return JDFPricing the element
     */
    public JDFPricing getPricing()
    {
        return (JDFPricing) getElement(ElementName.PRICING, null, 0);
    }

    /** (25) getCreatePricing
     * 
     * @return JDFPricing the element
     */
    public JDFPricing getCreatePricing()
    {
        return (JDFPricing) getCreateElement_KElement(ElementName.PRICING, null, 0);
    }

    /**
     * (29) append element Pricing
     */
    public JDFPricing appendPricing() throws JDFException
    {
        return (JDFPricing) appendElementN(ElementName.PRICING, 1, null);
    }

    /**
     * (24) const get element Component
     * @return JDFComponent the element
     */
    public JDFComponent getComponent()
    {
        return (JDFComponent) getElement(ElementName.COMPONENT, null, 0);
    }

    /** (25) getCreateComponent
     * 
     * @return JDFComponent the element
     */
    public JDFComponent getCreateComponent()
    {
        return (JDFComponent) getCreateElement_KElement(ElementName.COMPONENT, null, 0);
    }

    /**
     * (29) append element Component
     */
    public JDFComponent appendComponent() throws JDFException
    {
        return (JDFComponent) appendElementN(ElementName.COMPONENT, 1, null);
    }

    /**
      * (31) create inter-resource link to refTarget
      * @param refTarget the element that is referenced
      */
    public void refComponent(JDFComponent refTarget)
    {
        refElement(refTarget);
    }

    /**
     * (24) const get element ExposedMedia
     * @return JDFExposedMedia the element
     */
    public JDFExposedMedia getExposedMedia()
    {
        return (JDFExposedMedia) getElement(ElementName.EXPOSEDMEDIA, null, 0);
    }

    /** (25) getCreateExposedMedia
     * 
     * @return JDFExposedMedia the element
     */
    public JDFExposedMedia getCreateExposedMedia()
    {
        return (JDFExposedMedia) getCreateElement_KElement(ElementName.EXPOSEDMEDIA, null, 0);
    }

    /**
     * (29) append element ExposedMedia
     */
    public JDFExposedMedia appendExposedMedia() throws JDFException
    {
        return (JDFExposedMedia) appendElementN(ElementName.EXPOSEDMEDIA, 1, null);
    }

    /**
      * (31) create inter-resource link to refTarget
      * @param refTarget the element that is referenced
      */
    public void refExposedMedia(JDFExposedMedia refTarget)
    {
        refElement(refTarget);
    }

    /**
     * (24) const get element Ink
     * @return JDFInk the element
     */
    public JDFInk getInk()
    {
        return (JDFInk) getElement(ElementName.INK, null, 0);
    }

    /** (25) getCreateInk
     * 
     * @return JDFInk the element
     */
    public JDFInk getCreateInk()
    {
        return (JDFInk) getCreateElement_KElement(ElementName.INK, null, 0);
    }

    /**
     * (29) append element Ink
     */
    public JDFInk appendInk() throws JDFException
    {
        return (JDFInk) appendElementN(ElementName.INK, 1, null);
    }

    /**
      * (31) create inter-resource link to refTarget
      * @param refTarget the element that is referenced
      */
    public void refInk(JDFInk refTarget)
    {
        refElement(refTarget);
    }

    /**
     * (24) const get element Media
     * @return JDFMedia the element
     */
    public JDFMedia getMedia()
    {
        return (JDFMedia) getElement(ElementName.MEDIA, null, 0);
    }

    /** (25) getCreateMedia
     * 
     * @return JDFMedia the element
     */
    public JDFMedia getCreateMedia()
    {
        return (JDFMedia) getCreateElement_KElement(ElementName.MEDIA, null, 0);
    }

    /**
     * (29) append element Media
     */
    public JDFMedia appendMedia() throws JDFException
    {
        return (JDFMedia) appendElementN(ElementName.MEDIA, 1, null);
    }

    /**
      * (31) create inter-resource link to refTarget
      * @param refTarget the element that is referenced
      */
    public void refMedia(JDFMedia refTarget)
    {
        refElement(refTarget);
    }

    /**
     * (24) const get element Pallet
     * @return JDFPallet the element
     */
    public JDFPallet getPallet()
    {
        return (JDFPallet) getElement(ElementName.PALLET, null, 0);
    }

    /** (25) getCreatePallet
     * 
     * @return JDFPallet the element
     */
    public JDFPallet getCreatePallet()
    {
        return (JDFPallet) getCreateElement_KElement(ElementName.PALLET, null, 0);
    }

    /**
     * (29) append element Pallet
     */
    public JDFPallet appendPallet() throws JDFException
    {
        return (JDFPallet) appendElementN(ElementName.PALLET, 1, null);
    }

    /**
      * (31) create inter-resource link to refTarget
      * @param refTarget the element that is referenced
      */
    public void refPallet(JDFPallet refTarget)
    {
        refElement(refTarget);
    }

    /**
     * (24) const get element RegisterRibbon
     * @return JDFRegisterRibbon the element
     */
    public JDFRegisterRibbon getRegisterRibbon()
    {
        return (JDFRegisterRibbon) getElement(ElementName.REGISTERRIBBON, null, 0);
    }

    /** (25) getCreateRegisterRibbon
     * 
     * @return JDFRegisterRibbon the element
     */
    public JDFRegisterRibbon getCreateRegisterRibbon()
    {
        return (JDFRegisterRibbon) getCreateElement_KElement(ElementName.REGISTERRIBBON, null, 0);
    }

    /**
     * (29) append element RegisterRibbon
     */
    public JDFRegisterRibbon appendRegisterRibbon() throws JDFException
    {
        return (JDFRegisterRibbon) appendElementN(ElementName.REGISTERRIBBON, 1, null);
    }

    /**
      * (31) create inter-resource link to refTarget
      * @param refTarget the element that is referenced
      */
    public void refRegisterRibbon(JDFRegisterRibbon refTarget)
    {
        refElement(refTarget);
    }

    /**
     * (24) const get element Strap
     * @return JDFStrap the element
     */
    public JDFStrap getStrap()
    {
        return (JDFStrap) getElement(ElementName.STRAP, null, 0);
    }

    /** (25) getCreateStrap
     * 
     * @return JDFStrap the element
     */
    public JDFStrap getCreateStrap()
    {
        return (JDFStrap) getCreateElement_KElement(ElementName.STRAP, null, 0);
    }

    /**
     * (29) append element Strap
     */
    public JDFStrap appendStrap() throws JDFException
    {
        return (JDFStrap) appendElementN(ElementName.STRAP, 1, null);
    }

    /**
      * (31) create inter-resource link to refTarget
      * @param refTarget the element that is referenced
      */
    public void refStrap(JDFStrap refTarget)
    {
        refElement(refTarget);
    }

    /**
     * (24) const get element Bundle
     * @return JDFBundle the element
     */
    public JDFBundle getBundle()
    {
        return (JDFBundle) getElement(ElementName.BUNDLE, null, 0);
    }

    /** (25) getCreateBundle
     * 
     * @return JDFBundle the element
     */
    public JDFBundle getCreateBundle()
    {
        return (JDFBundle) getCreateElement_KElement(ElementName.BUNDLE, null, 0);
    }

    /**
     * (29) append element Bundle
     */
    public JDFBundle appendBundle() throws JDFException
    {
        return (JDFBundle) appendElementN(ElementName.BUNDLE, 1, null);
    }

    /**
      * (31) create inter-resource link to refTarget
      * @param refTarget the element that is referenced
      */
    public void refBundle(JDFBundle refTarget)
    {
        refElement(refTarget);
    }

    /**
     * (24) const get element DigitalMedia
     * @return JDFDigitalMedia the element
     */
    public JDFDigitalMedia getDigitalMedia()
    {
        return (JDFDigitalMedia) getElement(ElementName.DIGITALMEDIA, null, 0);
    }

    /** (25) getCreateDigitalMedia
     * 
     * @return JDFDigitalMedia the element
     */
    public JDFDigitalMedia getCreateDigitalMedia()
    {
        return (JDFDigitalMedia) getCreateElement_KElement(ElementName.DIGITALMEDIA, null, 0);
    }

    /**
     * (29) append element DigitalMedia
     */
    public JDFDigitalMedia appendDigitalMedia() throws JDFException
    {
        return (JDFDigitalMedia) appendElementN(ElementName.DIGITALMEDIA, 1, null);
    }

    /**
      * (31) create inter-resource link to refTarget
      * @param refTarget the element that is referenced
      */
    public void refDigitalMedia(JDFDigitalMedia refTarget)
    {
        refElement(refTarget);
    }

    /**
     * (24) const get element RollStand
     * @return JDFRollStand the element
     */
    public JDFRollStand getRollStand()
    {
        return (JDFRollStand) getElement(ElementName.ROLLSTAND, null, 0);
    }

    /** (25) getCreateRollStand
     * 
     * @return JDFRollStand the element
     */
    public JDFRollStand getCreateRollStand()
    {
        return (JDFRollStand) getCreateElement_KElement(ElementName.ROLLSTAND, null, 0);
    }

    /**
     * (29) append element RollStand
     */
    public JDFRollStand appendRollStand() throws JDFException
    {
        return (JDFRollStand) appendElementN(ElementName.ROLLSTAND, 1, null);
    }

    /**
      * (31) create inter-resource link to refTarget
      * @param refTarget the element that is referenced
      */
    public void refRollStand(JDFRollStand refTarget)
    {
        refElement(refTarget);
    }

    /**
     * (24) const get element Tool
     * @return JDFTool the element
     */
    public JDFTool getTool()
    {
        return (JDFTool) getElement(ElementName.TOOL, null, 0);
    }

    /** (25) getCreateTool
     * 
     * @return JDFTool the element
     */
    public JDFTool getCreateTool()
    {
        return (JDFTool) getCreateElement_KElement(ElementName.TOOL, null, 0);
    }

    /**
     * (29) append element Tool
     */
    public JDFTool appendTool() throws JDFException
    {
        return (JDFTool) appendElementN(ElementName.TOOL, 1, null);
    }

    /**
      * (31) create inter-resource link to refTarget
      * @param refTarget the element that is referenced
      */
    public void refTool(JDFTool refTarget)
    {
        refElement(refTarget);
    }

}// end namespace JDF
