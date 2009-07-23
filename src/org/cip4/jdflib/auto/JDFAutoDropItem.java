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

import java.util.Collection;                        
import java.util.Vector;                            
import java.util.zip.DataFormatException;           

import org.apache.xerces.dom.CoreDocumentImpl;      
import org.cip4.jdflib.core.*;                      
import org.cip4.jdflib.datatypes.*;                 
import org.cip4.jdflib.resource.*;                  
import org.cip4.jdflib.resource.process.*;          
import org.cip4.jdflib.resource.process.prepress.*;

public abstract class JDFAutoDropItem extends JDFElement
{

    private static final long serialVersionUID = 1L;

    private static AtrInfoTable[] atrInfoTable = new AtrInfoTable[9];
    static
    {
        atrInfoTable[0] = new AtrInfoTable(AttributeName.ACTUALAMOUNT, 0x33333111, AttributeInfo.EnumAttributeType.integer, null, null);
        atrInfoTable[1] = new AtrInfoTable(AttributeName.ACTUALTOTALAMOUNT, 0x33333111, AttributeInfo.EnumAttributeType.integer, null, null);
        atrInfoTable[2] = new AtrInfoTable(AttributeName.AMOUNT, 0x33333333, AttributeInfo.EnumAttributeType.integer, null, null);
        atrInfoTable[3] = new AtrInfoTable(AttributeName.TOTALAMOUNT, 0x33333111, AttributeInfo.EnumAttributeType.integer, null, null);
        atrInfoTable[4] = new AtrInfoTable(AttributeName.TOTALDIMENSIONS, 0x33333111, AttributeInfo.EnumAttributeType.shape, null, null);
        atrInfoTable[5] = new AtrInfoTable(AttributeName.TOTALVOLUME, 0x33333111, AttributeInfo.EnumAttributeType.double_, null, null);
        atrInfoTable[6] = new AtrInfoTable(AttributeName.TOTALWEIGHT, 0x33333111, AttributeInfo.EnumAttributeType.double_, null, null);
        atrInfoTable[7] = new AtrInfoTable(AttributeName.TRACKINGID, 0x33333311, AttributeInfo.EnumAttributeType.string, null, null);
        atrInfoTable[8] = new AtrInfoTable(AttributeName.UNIT, 0x33333333, AttributeInfo.EnumAttributeType.string, null, null);
    }
    
    @Override
	protected AttributeInfo getTheAttributeInfo()
    {
        return super.getTheAttributeInfo().updateReplace(atrInfoTable);
    }


    private static ElemInfoTable[] elemInfoTable = new ElemInfoTable[11];
    static
    {
        elemInfoTable[0] = new ElemInfoTable(ElementName.COMPONENT, 0x22222222);
        elemInfoTable[1] = new ElemInfoTable(ElementName.EXPOSEDMEDIA, 0x22222222);
        elemInfoTable[2] = new ElemInfoTable(ElementName.INK, 0x22222222);
        elemInfoTable[3] = new ElemInfoTable(ElementName.MEDIA, 0x22222222);
        elemInfoTable[4] = new ElemInfoTable(ElementName.PALLET, 0x22222222);
        elemInfoTable[5] = new ElemInfoTable(ElementName.REGISTERRIBBON, 0x22222222);
        elemInfoTable[6] = new ElemInfoTable(ElementName.STRAP, 0x22222222);
        elemInfoTable[7] = new ElemInfoTable(ElementName.BUNDLE, 0x22222222);
        elemInfoTable[8] = new ElemInfoTable(ElementName.DIGITALMEDIA, 0x22222222);
        elemInfoTable[9] = new ElemInfoTable(ElementName.ROLLSTAND, 0x22222222);
        elemInfoTable[10] = new ElemInfoTable(ElementName.TOOL, 0x22222222);
    }
    
    @Override
	protected ElementInfo getTheElementInfo()
    {
        return super.getTheElementInfo().updateReplace(elemInfoTable);
    }



    /**
     * Constructor for JDFAutoDropItem
     * @param myOwnerDocument
     * @param qualifiedName
     */
    protected JDFAutoDropItem(
        CoreDocumentImpl myOwnerDocument,
        String qualifiedName)
    {
        super(myOwnerDocument, qualifiedName);
    }

    /**
     * Constructor for JDFAutoDropItem
     * @param myOwnerDocument
     * @param myNamespaceURI
     * @param qualifiedName
     */
    protected JDFAutoDropItem(
        CoreDocumentImpl myOwnerDocument,
        String myNamespaceURI,
        String qualifiedName)
    {
        super(myOwnerDocument, myNamespaceURI, qualifiedName);
    }

    /**
     * Constructor for JDFAutoDropItem
     * @param myOwnerDocument
     * @param myNamespaceURI
     * @param qualifiedName
     * @param myLocalName
     */
    protected JDFAutoDropItem(
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
        return " JDFAutoDropItem[  --> " + super.toString() + " ]";
    }


/* ************************************************************************
 * Attribute getter / setter
 * ************************************************************************
 */
        
        /* ---------------------------------------------------------------------
        Methods for Attribute ActualAmount
        --------------------------------------------------------------------- */
        /**
          * (36) set attribute ActualAmount
          * @param value: the value to set the attribute to
          */
        public void setActualAmount(int value)
        {
            setAttribute(AttributeName.ACTUALAMOUNT, value, null);
        }

        /**
          * (15) get int attribute ActualAmount
          * @return int the value of the attribute
          */
        public int getActualAmount()
        {
            return getIntAttribute(AttributeName.ACTUALAMOUNT, null, 0);
        }

        
        /* ---------------------------------------------------------------------
        Methods for Attribute ActualTotalAmount
        --------------------------------------------------------------------- */
        /**
          * (36) set attribute ActualTotalAmount
          * @param value: the value to set the attribute to
          */
        public void setActualTotalAmount(int value)
        {
            setAttribute(AttributeName.ACTUALTOTALAMOUNT, value, null);
        }

        /**
          * (15) get int attribute ActualTotalAmount
          * @return int the value of the attribute
          */
        public int getActualTotalAmount()
        {
            return getIntAttribute(AttributeName.ACTUALTOTALAMOUNT, null, 0);
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
        Methods for Attribute TotalAmount
        --------------------------------------------------------------------- */
        /**
          * (36) set attribute TotalAmount
          * @param value: the value to set the attribute to
          */
        public void setTotalAmount(int value)
        {
            setAttribute(AttributeName.TOTALAMOUNT, value, null);
        }

        /**
          * (15) get int attribute TotalAmount
          * @return int the value of the attribute
          */
        public int getTotalAmount()
        {
            return getIntAttribute(AttributeName.TOTALAMOUNT, null, 0);
        }

        
        /* ---------------------------------------------------------------------
        Methods for Attribute TotalDimensions
        --------------------------------------------------------------------- */
        /**
          * (36) set attribute TotalDimensions
          * @param value: the value to set the attribute to
          */
        public void setTotalDimensions(JDFShape value)
        {
            setAttribute(AttributeName.TOTALDIMENSIONS, value, null);
        }

        /**
          * (20) get JDFShape attribute TotalDimensions
          * @return JDFShape the value of the attribute, null if a the
          *         attribute value is not a valid to create a JDFShape
          */
        public JDFShape getTotalDimensions()
        {
            String strAttrName = "";
            JDFShape nPlaceHolder = null;
            strAttrName = getAttribute(AttributeName.TOTALDIMENSIONS, null, JDFConstants.EMPTYSTRING);
            try
            {
                nPlaceHolder = new JDFShape(strAttrName);
            }
            catch(DataFormatException e)
            {
                return null;
            }
            return nPlaceHolder;
        }

        
        /* ---------------------------------------------------------------------
        Methods for Attribute TotalVolume
        --------------------------------------------------------------------- */
        /**
          * (36) set attribute TotalVolume
          * @param value: the value to set the attribute to
          */
        public void setTotalVolume(double value)
        {
            setAttribute(AttributeName.TOTALVOLUME, value, null);
        }

        /**
          * (17) get double attribute TotalVolume
          * @return double the value of the attribute
          */
        public double getTotalVolume()
        {
            return getRealAttribute(AttributeName.TOTALVOLUME, null, 0.0);
        }

        
        /* ---------------------------------------------------------------------
        Methods for Attribute TotalWeight
        --------------------------------------------------------------------- */
        /**
          * (36) set attribute TotalWeight
          * @param value: the value to set the attribute to
          */
        public void setTotalWeight(double value)
        {
            setAttribute(AttributeName.TOTALWEIGHT, value, null);
        }

        /**
          * (17) get double attribute TotalWeight
          * @return double the value of the attribute
          */
        public double getTotalWeight()
        {
            return getRealAttribute(AttributeName.TOTALWEIGHT, null, 0.0);
        }

        
        /* ---------------------------------------------------------------------
        Methods for Attribute TrackingID
        --------------------------------------------------------------------- */
        /**
          * (36) set attribute TrackingID
          * @param value: the value to set the attribute to
          */
        public void setTrackingID(String value)
        {
            setAttribute(AttributeName.TRACKINGID, value, null);
        }

        /**
          * (23) get String attribute TrackingID
          * @return the value of the attribute
          */
        public String getTrackingID()
        {
            return getAttribute(AttributeName.TRACKINGID, null, JDFConstants.EMPTYSTRING);
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

    /** (26) getCreateComponent
     * 
     * @param iSkip number of elements to skip
     * @return JDFComponent the element
     */
    public JDFComponent getCreateComponent(int iSkip)
    {
        return (JDFComponent)getCreateElement_KElement(ElementName.COMPONENT, null, iSkip);
    }

    /**
     * (27) const get element Component
     * @param iSkip number of elements to skip
     * @return JDFComponent the element
     * default is getComponent(0)     */
    public JDFComponent getComponent(int iSkip)
    {
        return (JDFComponent) getElement(ElementName.COMPONENT, null, iSkip);
    }

    /**
     * Get all Component from the current element
     * 
     * @return Collection<JDFComponent>, null if none are available
     */
    public Collection<JDFComponent> getAllComponent()
    {
        final VElement vc = getChildElementVector(ElementName.COMPONENT, null);
        if (vc == null || vc.size() == 0)
        {
            return null;
        }

        final Vector<JDFComponent> v = new Vector<JDFComponent>();
        for (int i = 0; i < vc.size(); i++)
        {
            v.add((JDFComponent) vc.get(i));
        }

        return v;
    }

    /**
     * (30) append element Component
     */
    public JDFComponent appendComponent() throws JDFException
    {
        return (JDFComponent) appendElement(ElementName.COMPONENT, null);
    }

    /**
      * (31) create inter-resource link to refTarget
      * @param refTarget the element that is referenced
      */
    public void refComponent(JDFComponent refTarget)
    {
        refElement(refTarget);
    }

    /** (26) getCreateExposedMedia
     * 
     * @param iSkip number of elements to skip
     * @return JDFExposedMedia the element
     */
    public JDFExposedMedia getCreateExposedMedia(int iSkip)
    {
        return (JDFExposedMedia)getCreateElement_KElement(ElementName.EXPOSEDMEDIA, null, iSkip);
    }

    /**
     * (27) const get element ExposedMedia
     * @param iSkip number of elements to skip
     * @return JDFExposedMedia the element
     * default is getExposedMedia(0)     */
    public JDFExposedMedia getExposedMedia(int iSkip)
    {
        return (JDFExposedMedia) getElement(ElementName.EXPOSEDMEDIA, null, iSkip);
    }

    /**
     * Get all ExposedMedia from the current element
     * 
     * @return Collection<JDFExposedMedia>, null if none are available
     */
    public Collection<JDFExposedMedia> getAllExposedMedia()
    {
        final VElement vc = getChildElementVector(ElementName.EXPOSEDMEDIA, null);
        if (vc == null || vc.size() == 0)
        {
            return null;
        }

        final Vector<JDFExposedMedia> v = new Vector<JDFExposedMedia>();
        for (int i = 0; i < vc.size(); i++)
        {
            v.add((JDFExposedMedia) vc.get(i));
        }

        return v;
    }

    /**
     * (30) append element ExposedMedia
     */
    public JDFExposedMedia appendExposedMedia() throws JDFException
    {
        return (JDFExposedMedia) appendElement(ElementName.EXPOSEDMEDIA, null);
    }

    /**
      * (31) create inter-resource link to refTarget
      * @param refTarget the element that is referenced
      */
    public void refExposedMedia(JDFExposedMedia refTarget)
    {
        refElement(refTarget);
    }

    /** (26) getCreateInk
     * 
     * @param iSkip number of elements to skip
     * @return JDFInk the element
     */
    public JDFInk getCreateInk(int iSkip)
    {
        return (JDFInk)getCreateElement_KElement(ElementName.INK, null, iSkip);
    }

    /**
     * (27) const get element Ink
     * @param iSkip number of elements to skip
     * @return JDFInk the element
     * default is getInk(0)     */
    public JDFInk getInk(int iSkip)
    {
        return (JDFInk) getElement(ElementName.INK, null, iSkip);
    }

    /**
     * Get all Ink from the current element
     * 
     * @return Collection<JDFInk>, null if none are available
     */
    public Collection<JDFInk> getAllInk()
    {
        final VElement vc = getChildElementVector(ElementName.INK, null);
        if (vc == null || vc.size() == 0)
        {
            return null;
        }

        final Vector<JDFInk> v = new Vector<JDFInk>();
        for (int i = 0; i < vc.size(); i++)
        {
            v.add((JDFInk) vc.get(i));
        }

        return v;
    }

    /**
     * (30) append element Ink
     */
    public JDFInk appendInk() throws JDFException
    {
        return (JDFInk) appendElement(ElementName.INK, null);
    }

    /**
      * (31) create inter-resource link to refTarget
      * @param refTarget the element that is referenced
      */
    public void refInk(JDFInk refTarget)
    {
        refElement(refTarget);
    }

    /** (26) getCreateMedia
     * 
     * @param iSkip number of elements to skip
     * @return JDFMedia the element
     */
    public JDFMedia getCreateMedia(int iSkip)
    {
        return (JDFMedia)getCreateElement_KElement(ElementName.MEDIA, null, iSkip);
    }

    /**
     * (27) const get element Media
     * @param iSkip number of elements to skip
     * @return JDFMedia the element
     * default is getMedia(0)     */
    public JDFMedia getMedia(int iSkip)
    {
        return (JDFMedia) getElement(ElementName.MEDIA, null, iSkip);
    }

    /**
     * Get all Media from the current element
     * 
     * @return Collection<JDFMedia>, null if none are available
     */
    public Collection<JDFMedia> getAllMedia()
    {
        final VElement vc = getChildElementVector(ElementName.MEDIA, null);
        if (vc == null || vc.size() == 0)
        {
            return null;
        }

        final Vector<JDFMedia> v = new Vector<JDFMedia>();
        for (int i = 0; i < vc.size(); i++)
        {
            v.add((JDFMedia) vc.get(i));
        }

        return v;
    }

    /**
     * (30) append element Media
     */
    public JDFMedia appendMedia() throws JDFException
    {
        return (JDFMedia) appendElement(ElementName.MEDIA, null);
    }

    /**
      * (31) create inter-resource link to refTarget
      * @param refTarget the element that is referenced
      */
    public void refMedia(JDFMedia refTarget)
    {
        refElement(refTarget);
    }

    /** (26) getCreatePallet
     * 
     * @param iSkip number of elements to skip
     * @return JDFPallet the element
     */
    public JDFPallet getCreatePallet(int iSkip)
    {
        return (JDFPallet)getCreateElement_KElement(ElementName.PALLET, null, iSkip);
    }

    /**
     * (27) const get element Pallet
     * @param iSkip number of elements to skip
     * @return JDFPallet the element
     * default is getPallet(0)     */
    public JDFPallet getPallet(int iSkip)
    {
        return (JDFPallet) getElement(ElementName.PALLET, null, iSkip);
    }

    /**
     * Get all Pallet from the current element
     * 
     * @return Collection<JDFPallet>, null if none are available
     */
    public Collection<JDFPallet> getAllPallet()
    {
        final VElement vc = getChildElementVector(ElementName.PALLET, null);
        if (vc == null || vc.size() == 0)
        {
            return null;
        }

        final Vector<JDFPallet> v = new Vector<JDFPallet>();
        for (int i = 0; i < vc.size(); i++)
        {
            v.add((JDFPallet) vc.get(i));
        }

        return v;
    }

    /**
     * (30) append element Pallet
     */
    public JDFPallet appendPallet() throws JDFException
    {
        return (JDFPallet) appendElement(ElementName.PALLET, null);
    }

    /**
      * (31) create inter-resource link to refTarget
      * @param refTarget the element that is referenced
      */
    public void refPallet(JDFPallet refTarget)
    {
        refElement(refTarget);
    }

    /** (26) getCreateRegisterRibbon
     * 
     * @param iSkip number of elements to skip
     * @return JDFRegisterRibbon the element
     */
    public JDFRegisterRibbon getCreateRegisterRibbon(int iSkip)
    {
        return (JDFRegisterRibbon)getCreateElement_KElement(ElementName.REGISTERRIBBON, null, iSkip);
    }

    /**
     * (27) const get element RegisterRibbon
     * @param iSkip number of elements to skip
     * @return JDFRegisterRibbon the element
     * default is getRegisterRibbon(0)     */
    public JDFRegisterRibbon getRegisterRibbon(int iSkip)
    {
        return (JDFRegisterRibbon) getElement(ElementName.REGISTERRIBBON, null, iSkip);
    }

    /**
     * Get all RegisterRibbon from the current element
     * 
     * @return Collection<JDFRegisterRibbon>, null if none are available
     */
    public Collection<JDFRegisterRibbon> getAllRegisterRibbon()
    {
        final VElement vc = getChildElementVector(ElementName.REGISTERRIBBON, null);
        if (vc == null || vc.size() == 0)
        {
            return null;
        }

        final Vector<JDFRegisterRibbon> v = new Vector<JDFRegisterRibbon>();
        for (int i = 0; i < vc.size(); i++)
        {
            v.add((JDFRegisterRibbon) vc.get(i));
        }

        return v;
    }

    /**
     * (30) append element RegisterRibbon
     */
    public JDFRegisterRibbon appendRegisterRibbon() throws JDFException
    {
        return (JDFRegisterRibbon) appendElement(ElementName.REGISTERRIBBON, null);
    }

    /**
      * (31) create inter-resource link to refTarget
      * @param refTarget the element that is referenced
      */
    public void refRegisterRibbon(JDFRegisterRibbon refTarget)
    {
        refElement(refTarget);
    }

    /** (26) getCreateStrap
     * 
     * @param iSkip number of elements to skip
     * @return JDFStrap the element
     */
    public JDFStrap getCreateStrap(int iSkip)
    {
        return (JDFStrap)getCreateElement_KElement(ElementName.STRAP, null, iSkip);
    }

    /**
     * (27) const get element Strap
     * @param iSkip number of elements to skip
     * @return JDFStrap the element
     * default is getStrap(0)     */
    public JDFStrap getStrap(int iSkip)
    {
        return (JDFStrap) getElement(ElementName.STRAP, null, iSkip);
    }

    /**
     * Get all Strap from the current element
     * 
     * @return Collection<JDFStrap>, null if none are available
     */
    public Collection<JDFStrap> getAllStrap()
    {
        final VElement vc = getChildElementVector(ElementName.STRAP, null);
        if (vc == null || vc.size() == 0)
        {
            return null;
        }

        final Vector<JDFStrap> v = new Vector<JDFStrap>();
        for (int i = 0; i < vc.size(); i++)
        {
            v.add((JDFStrap) vc.get(i));
        }

        return v;
    }

    /**
     * (30) append element Strap
     */
    public JDFStrap appendStrap() throws JDFException
    {
        return (JDFStrap) appendElement(ElementName.STRAP, null);
    }

    /**
      * (31) create inter-resource link to refTarget
      * @param refTarget the element that is referenced
      */
    public void refStrap(JDFStrap refTarget)
    {
        refElement(refTarget);
    }

    /** (26) getCreateBundle
     * 
     * @param iSkip number of elements to skip
     * @return JDFBundle the element
     */
    public JDFBundle getCreateBundle(int iSkip)
    {
        return (JDFBundle)getCreateElement_KElement(ElementName.BUNDLE, null, iSkip);
    }

    /**
     * (27) const get element Bundle
     * @param iSkip number of elements to skip
     * @return JDFBundle the element
     * default is getBundle(0)     */
    public JDFBundle getBundle(int iSkip)
    {
        return (JDFBundle) getElement(ElementName.BUNDLE, null, iSkip);
    }

    /**
     * Get all Bundle from the current element
     * 
     * @return Collection<JDFBundle>, null if none are available
     */
    public Collection<JDFBundle> getAllBundle()
    {
        final VElement vc = getChildElementVector(ElementName.BUNDLE, null);
        if (vc == null || vc.size() == 0)
        {
            return null;
        }

        final Vector<JDFBundle> v = new Vector<JDFBundle>();
        for (int i = 0; i < vc.size(); i++)
        {
            v.add((JDFBundle) vc.get(i));
        }

        return v;
    }

    /**
     * (30) append element Bundle
     */
    public JDFBundle appendBundle() throws JDFException
    {
        return (JDFBundle) appendElement(ElementName.BUNDLE, null);
    }

    /**
      * (31) create inter-resource link to refTarget
      * @param refTarget the element that is referenced
      */
    public void refBundle(JDFBundle refTarget)
    {
        refElement(refTarget);
    }

    /** (26) getCreateDigitalMedia
     * 
     * @param iSkip number of elements to skip
     * @return JDFDigitalMedia the element
     */
    public JDFDigitalMedia getCreateDigitalMedia(int iSkip)
    {
        return (JDFDigitalMedia)getCreateElement_KElement(ElementName.DIGITALMEDIA, null, iSkip);
    }

    /**
     * (27) const get element DigitalMedia
     * @param iSkip number of elements to skip
     * @return JDFDigitalMedia the element
     * default is getDigitalMedia(0)     */
    public JDFDigitalMedia getDigitalMedia(int iSkip)
    {
        return (JDFDigitalMedia) getElement(ElementName.DIGITALMEDIA, null, iSkip);
    }

    /**
     * Get all DigitalMedia from the current element
     * 
     * @return Collection<JDFDigitalMedia>, null if none are available
     */
    public Collection<JDFDigitalMedia> getAllDigitalMedia()
    {
        final VElement vc = getChildElementVector(ElementName.DIGITALMEDIA, null);
        if (vc == null || vc.size() == 0)
        {
            return null;
        }

        final Vector<JDFDigitalMedia> v = new Vector<JDFDigitalMedia>();
        for (int i = 0; i < vc.size(); i++)
        {
            v.add((JDFDigitalMedia) vc.get(i));
        }

        return v;
    }

    /**
     * (30) append element DigitalMedia
     */
    public JDFDigitalMedia appendDigitalMedia() throws JDFException
    {
        return (JDFDigitalMedia) appendElement(ElementName.DIGITALMEDIA, null);
    }

    /**
      * (31) create inter-resource link to refTarget
      * @param refTarget the element that is referenced
      */
    public void refDigitalMedia(JDFDigitalMedia refTarget)
    {
        refElement(refTarget);
    }

    /** (26) getCreateRollStand
     * 
     * @param iSkip number of elements to skip
     * @return JDFRollStand the element
     */
    public JDFRollStand getCreateRollStand(int iSkip)
    {
        return (JDFRollStand)getCreateElement_KElement(ElementName.ROLLSTAND, null, iSkip);
    }

    /**
     * (27) const get element RollStand
     * @param iSkip number of elements to skip
     * @return JDFRollStand the element
     * default is getRollStand(0)     */
    public JDFRollStand getRollStand(int iSkip)
    {
        return (JDFRollStand) getElement(ElementName.ROLLSTAND, null, iSkip);
    }

    /**
     * Get all RollStand from the current element
     * 
     * @return Collection<JDFRollStand>, null if none are available
     */
    public Collection<JDFRollStand> getAllRollStand()
    {
        final VElement vc = getChildElementVector(ElementName.ROLLSTAND, null);
        if (vc == null || vc.size() == 0)
        {
            return null;
        }

        final Vector<JDFRollStand> v = new Vector<JDFRollStand>();
        for (int i = 0; i < vc.size(); i++)
        {
            v.add((JDFRollStand) vc.get(i));
        }

        return v;
    }

    /**
     * (30) append element RollStand
     */
    public JDFRollStand appendRollStand() throws JDFException
    {
        return (JDFRollStand) appendElement(ElementName.ROLLSTAND, null);
    }

    /**
      * (31) create inter-resource link to refTarget
      * @param refTarget the element that is referenced
      */
    public void refRollStand(JDFRollStand refTarget)
    {
        refElement(refTarget);
    }

    /** (26) getCreateTool
     * 
     * @param iSkip number of elements to skip
     * @return JDFTool the element
     */
    public JDFTool getCreateTool(int iSkip)
    {
        return (JDFTool)getCreateElement_KElement(ElementName.TOOL, null, iSkip);
    }

    /**
     * (27) const get element Tool
     * @param iSkip number of elements to skip
     * @return JDFTool the element
     * default is getTool(0)     */
    public JDFTool getTool(int iSkip)
    {
        return (JDFTool) getElement(ElementName.TOOL, null, iSkip);
    }

    /**
     * Get all Tool from the current element
     * 
     * @return Collection<JDFTool>, null if none are available
     */
    public Collection<JDFTool> getAllTool()
    {
        final VElement vc = getChildElementVector(ElementName.TOOL, null);
        if (vc == null || vc.size() == 0)
        {
            return null;
        }

        final Vector<JDFTool> v = new Vector<JDFTool>();
        for (int i = 0; i < vc.size(); i++)
        {
            v.add((JDFTool) vc.get(i));
        }

        return v;
    }

    /**
     * (30) append element Tool
     */
    public JDFTool appendTool() throws JDFException
    {
        return (JDFTool) appendElement(ElementName.TOOL, null);
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
