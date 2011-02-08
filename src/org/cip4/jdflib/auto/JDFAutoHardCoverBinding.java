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

import java.util.Collection;                        
import java.util.Iterator;                          
import java.util.List;                              
import java.util.Map;                               
import java.util.Vector;                            
import java.util.zip.DataFormatException;           

import org.apache.commons.lang.enums.ValuedEnum;    
import org.w3c.dom.Element;                         
import org.apache.xerces.dom.CoreDocumentImpl;      
import org.cip4.jdflib.*;                           
import org.cip4.jdflib.auto.*;                      
import org.cip4.jdflib.core.*;                      
import org.cip4.jdflib.core.ElementInfo;                      
import org.cip4.jdflib.span.*;                      
import org.cip4.jdflib.node.*;                      
import org.cip4.jdflib.pool.*;                      
import org.cip4.jdflib.jmf.*;                       
import org.cip4.jdflib.datatypes.*;                 
import org.cip4.jdflib.resource.*;                  
import org.cip4.jdflib.resource.devicecapability.*; 
import org.cip4.jdflib.resource.intent.*;           
import org.cip4.jdflib.resource.process.*;          
import org.cip4.jdflib.resource.process.postpress.*;
import org.cip4.jdflib.resource.process.press.*;    
import org.cip4.jdflib.resource.process.prepress.*; 
import org.cip4.jdflib.util.*;           
    /**
    *****************************************************************************
    class JDFAutoHardCoverBinding : public JDFElement

    *****************************************************************************
    */

public abstract class JDFAutoHardCoverBinding extends JDFElement
{

    private static final long serialVersionUID = 1L;

    private static ElemInfoTable[] elemInfoTable = new ElemInfoTable[21];
    static
    {
        elemInfoTable[0] = new ElemInfoTable(ElementName.BLOCKTHREADSEWING, 0x66666661);
        elemInfoTable[1] = new ElemInfoTable(ElementName.COVERSTYLE, 0x66666111);
        elemInfoTable[2] = new ElemInfoTable(ElementName.ENDSHEETS, 0x66666661);
        elemInfoTable[3] = new ElemInfoTable(ElementName.HEADBANDS, 0x66666661);
        elemInfoTable[4] = new ElemInfoTable(ElementName.HEADBANDCOLOR, 0x66666661);
        elemInfoTable[5] = new ElemInfoTable(ElementName.HEADBANDCOLORDETAILS, 0x33331111);
        elemInfoTable[6] = new ElemInfoTable(ElementName.JACKET, 0x66666661);
        elemInfoTable[7] = new ElemInfoTable(ElementName.JACKETFOLDINGWIDTH, 0x66666111);
        elemInfoTable[8] = new ElemInfoTable(ElementName.JAPANBIND, 0x66666661);
        elemInfoTable[9] = new ElemInfoTable(ElementName.SPINEBRUSHING, 0x66666661);
        elemInfoTable[10] = new ElemInfoTable(ElementName.SPINEFIBERROUGHING, 0x66666661);
        elemInfoTable[11] = new ElemInfoTable(ElementName.SPINEGLUE, 0x66666661);
        elemInfoTable[12] = new ElemInfoTable(ElementName.SPINELEVELLING, 0x66666661);
        elemInfoTable[13] = new ElemInfoTable(ElementName.SPINEMILLING, 0x66666661);
        elemInfoTable[14] = new ElemInfoTable(ElementName.SPINENOTCHING, 0x66666661);
        elemInfoTable[15] = new ElemInfoTable(ElementName.SPINESANDING, 0x66666661);
        elemInfoTable[16] = new ElemInfoTable(ElementName.SPINESHREDDING, 0x66666661);
        elemInfoTable[17] = new ElemInfoTable(ElementName.STRIPMATERIAL, 0x66666661);
        elemInfoTable[18] = new ElemInfoTable(ElementName.TIGHTBACKING, 0x66666661);
        elemInfoTable[19] = new ElemInfoTable(ElementName.THICKNESS, 0x66666661);
        elemInfoTable[20] = new ElemInfoTable(ElementName.REGISTERRIBBON, 0x33333331);
    }
    
    protected ElementInfo getTheElementInfo()
    {
        return super.getTheElementInfo().updateReplace(elemInfoTable);
    }



    /**
     * Constructor for JDFAutoHardCoverBinding
     * @param myOwnerDocument
     * @param qualifiedName
     */
    protected JDFAutoHardCoverBinding(
        CoreDocumentImpl myOwnerDocument,
        String qualifiedName)
    {
        super(myOwnerDocument, qualifiedName);
    }

    /**
     * Constructor for JDFAutoHardCoverBinding
     * @param myOwnerDocument
     * @param myNamespaceURI
     * @param qualifiedName
     */
    protected JDFAutoHardCoverBinding(
        CoreDocumentImpl myOwnerDocument,
        String myNamespaceURI,
        String qualifiedName)
    {
        super(myOwnerDocument, myNamespaceURI, qualifiedName);
    }

    /**
     * Constructor for JDFAutoHardCoverBinding
     * @param myOwnerDocument
     * @param myNamespaceURI
     * @param qualifiedName
     * @param myLocalName
     */
    protected JDFAutoHardCoverBinding(
        CoreDocumentImpl myOwnerDocument,
        String myNamespaceURI,
        String qualifiedName,
        String myLocalName)
    {
        super(myOwnerDocument, myNamespaceURI, qualifiedName, myLocalName);
    }


    public String toString()
    {
        return " JDFAutoHardCoverBinding[  --> " + super.toString() + " ]";
    }


/* ***********************************************************************
 * Element getter / setter
 * ***********************************************************************
 */

    /**
     * (24) const get element BlockThreadSewing
     * @return JDFOptionSpan the element
     */
    public JDFOptionSpan getBlockThreadSewing()
    {
        return (JDFOptionSpan) getElement(ElementName.BLOCKTHREADSEWING, null, 0);
    }

    /** (25) getCreateBlockThreadSewing
     * 
     * @return JDFOptionSpan the element
     */
    public JDFOptionSpan getCreateBlockThreadSewing()
    {
        return (JDFOptionSpan) getCreateElement_KElement(ElementName.BLOCKTHREADSEWING, null, 0);
    }

    /**
     * (29) append element BlockThreadSewing
     */
    public JDFOptionSpan appendBlockThreadSewing() throws JDFException
    {
        return (JDFOptionSpan) appendElementN(ElementName.BLOCKTHREADSEWING, 1, null);
    }

    /**
     * (24) const get element CoverStyle
     * @return JDFNameSpan the element
     */
    public JDFNameSpan getCoverStyle()
    {
        return (JDFNameSpan) getElement(ElementName.COVERSTYLE, null, 0);
    }

    /** (25) getCreateCoverStyle
     * 
     * @return JDFNameSpan the element
     */
    public JDFNameSpan getCreateCoverStyle()
    {
        return (JDFNameSpan) getCreateElement_KElement(ElementName.COVERSTYLE, null, 0);
    }

    /**
     * (29) append element CoverStyle
     */
    public JDFNameSpan appendCoverStyle() throws JDFException
    {
        return (JDFNameSpan) appendElementN(ElementName.COVERSTYLE, 1, null);
    }

    /**
     * (24) const get element EndSheets
     * @return JDFOptionSpan the element
     */
    public JDFOptionSpan getEndSheets()
    {
        return (JDFOptionSpan) getElement(ElementName.ENDSHEETS, null, 0);
    }

    /** (25) getCreateEndSheets
     * 
     * @return JDFOptionSpan the element
     */
    public JDFOptionSpan getCreateEndSheets()
    {
        return (JDFOptionSpan) getCreateElement_KElement(ElementName.ENDSHEETS, null, 0);
    }

    /**
     * (29) append element EndSheets
     */
    public JDFOptionSpan appendEndSheets() throws JDFException
    {
        return (JDFOptionSpan) appendElementN(ElementName.ENDSHEETS, 1, null);
    }

    /**
     * (24) const get element HeadBands
     * @return JDFOptionSpan the element
     */
    public JDFOptionSpan getHeadBands()
    {
        return (JDFOptionSpan) getElement(ElementName.HEADBANDS, null, 0);
    }

    /** (25) getCreateHeadBands
     * 
     * @return JDFOptionSpan the element
     */
    public JDFOptionSpan getCreateHeadBands()
    {
        return (JDFOptionSpan) getCreateElement_KElement(ElementName.HEADBANDS, null, 0);
    }

    /**
     * (29) append element HeadBands
     */
    public JDFOptionSpan appendHeadBands() throws JDFException
    {
        return (JDFOptionSpan) appendElementN(ElementName.HEADBANDS, 1, null);
    }

    /**
     * (24) const get element HeadBandColor
     * @return JDFSpanNamedColor the element
     */
    public JDFSpanNamedColor getHeadBandColor()
    {
        return (JDFSpanNamedColor) getElement(ElementName.HEADBANDCOLOR, null, 0);
    }

    /** (25) getCreateHeadBandColor
     * 
     * @return JDFSpanNamedColor the element
     */
    public JDFSpanNamedColor getCreateHeadBandColor()
    {
        return (JDFSpanNamedColor) getCreateElement_KElement(ElementName.HEADBANDCOLOR, null, 0);
    }

    /**
     * (29) append element HeadBandColor
     */
    public JDFSpanNamedColor appendHeadBandColor() throws JDFException
    {
        return (JDFSpanNamedColor) appendElementN(ElementName.HEADBANDCOLOR, 1, null);
    }

    /** (26) getCreateHeadBandColorDetails
     * 
     * @param iSkip number of elements to skip
     * @return JDFStringSpan the element
     */
    public JDFStringSpan getCreateHeadBandColorDetails(int iSkip)
    {
        return (JDFStringSpan)getCreateElement_KElement(ElementName.HEADBANDCOLORDETAILS, null, iSkip);
    }

    /**
     * (27) const get element HeadBandColorDetails
     * @param iSkip number of elements to skip
     * @return JDFStringSpan the element
     * default is getHeadBandColorDetails(0)     */
    public JDFStringSpan getHeadBandColorDetails(int iSkip)
    {
        return (JDFStringSpan) getElement(ElementName.HEADBANDCOLORDETAILS, null, iSkip);
    }

    /**
     * Get all HeadBandColorDetails from the current element
     * 
     * @return Collection<JDFStringSpan>, null if none are available
     */
    public Collection<JDFStringSpan> getAllHeadBandColorDetails()
    {
        final VElement vc = getChildElementVector(ElementName.HEADBANDCOLORDETAILS, null);
        if (vc == null || vc.size() == 0)
        {
            return null;
        }

        final Vector<JDFStringSpan> v = new Vector<JDFStringSpan>();
        for (int i = 0; i < vc.size(); i++)
        {
            v.add((JDFStringSpan) vc.get(i));
        }

        return v;
    }

    /**
     * (30) append element HeadBandColorDetails
     */
    public JDFStringSpan appendHeadBandColorDetails() throws JDFException
    {
        return (JDFStringSpan) appendElement(ElementName.HEADBANDCOLORDETAILS, null);
    }

    /**
     * (24) const get element Jacket
     * @return JDFSpanJacket the element
     */
    public JDFSpanJacket getJacket()
    {
        return (JDFSpanJacket) getElement(ElementName.JACKET, null, 0);
    }

    /** (25) getCreateJacket
     * 
     * @return JDFSpanJacket the element
     */
    public JDFSpanJacket getCreateJacket()
    {
        return (JDFSpanJacket) getCreateElement_KElement(ElementName.JACKET, null, 0);
    }

    /**
     * (29) append element Jacket
     */
    public JDFSpanJacket appendJacket() throws JDFException
    {
        return (JDFSpanJacket) appendElementN(ElementName.JACKET, 1, null);
    }

    /**
     * (24) const get element JacketFoldingWidth
     * @return JDFNumberSpan the element
     */
    public JDFNumberSpan getJacketFoldingWidth()
    {
        return (JDFNumberSpan) getElement(ElementName.JACKETFOLDINGWIDTH, null, 0);
    }

    /** (25) getCreateJacketFoldingWidth
     * 
     * @return JDFNumberSpan the element
     */
    public JDFNumberSpan getCreateJacketFoldingWidth()
    {
        return (JDFNumberSpan) getCreateElement_KElement(ElementName.JACKETFOLDINGWIDTH, null, 0);
    }

    /**
     * (29) append element JacketFoldingWidth
     */
    public JDFNumberSpan appendJacketFoldingWidth() throws JDFException
    {
        return (JDFNumberSpan) appendElementN(ElementName.JACKETFOLDINGWIDTH, 1, null);
    }

    /**
     * (24) const get element JapanBind
     * @return JDFOptionSpan the element
     */
    public JDFOptionSpan getJapanBind()
    {
        return (JDFOptionSpan) getElement(ElementName.JAPANBIND, null, 0);
    }

    /** (25) getCreateJapanBind
     * 
     * @return JDFOptionSpan the element
     */
    public JDFOptionSpan getCreateJapanBind()
    {
        return (JDFOptionSpan) getCreateElement_KElement(ElementName.JAPANBIND, null, 0);
    }

    /**
     * (29) append element JapanBind
     */
    public JDFOptionSpan appendJapanBind() throws JDFException
    {
        return (JDFOptionSpan) appendElementN(ElementName.JAPANBIND, 1, null);
    }

    /**
     * (24) const get element SpineBrushing
     * @return JDFOptionSpan the element
     */
    public JDFOptionSpan getSpineBrushing()
    {
        return (JDFOptionSpan) getElement(ElementName.SPINEBRUSHING, null, 0);
    }

    /** (25) getCreateSpineBrushing
     * 
     * @return JDFOptionSpan the element
     */
    public JDFOptionSpan getCreateSpineBrushing()
    {
        return (JDFOptionSpan) getCreateElement_KElement(ElementName.SPINEBRUSHING, null, 0);
    }

    /**
     * (29) append element SpineBrushing
     */
    public JDFOptionSpan appendSpineBrushing() throws JDFException
    {
        return (JDFOptionSpan) appendElementN(ElementName.SPINEBRUSHING, 1, null);
    }

    /**
     * (24) const get element SpineFiberRoughing
     * @return JDFOptionSpan the element
     */
    public JDFOptionSpan getSpineFiberRoughing()
    {
        return (JDFOptionSpan) getElement(ElementName.SPINEFIBERROUGHING, null, 0);
    }

    /** (25) getCreateSpineFiberRoughing
     * 
     * @return JDFOptionSpan the element
     */
    public JDFOptionSpan getCreateSpineFiberRoughing()
    {
        return (JDFOptionSpan) getCreateElement_KElement(ElementName.SPINEFIBERROUGHING, null, 0);
    }

    /**
     * (29) append element SpineFiberRoughing
     */
    public JDFOptionSpan appendSpineFiberRoughing() throws JDFException
    {
        return (JDFOptionSpan) appendElementN(ElementName.SPINEFIBERROUGHING, 1, null);
    }

    /**
     * (24) const get element SpineGlue
     * @return JDFSpanGlue the element
     */
    public JDFSpanGlue getSpineGlue()
    {
        return (JDFSpanGlue) getElement(ElementName.SPINEGLUE, null, 0);
    }

    /** (25) getCreateSpineGlue
     * 
     * @return JDFSpanGlue the element
     */
    public JDFSpanGlue getCreateSpineGlue()
    {
        return (JDFSpanGlue) getCreateElement_KElement(ElementName.SPINEGLUE, null, 0);
    }

    /**
     * (29) append element SpineGlue
     */
    public JDFSpanGlue appendSpineGlue() throws JDFException
    {
        return (JDFSpanGlue) appendElementN(ElementName.SPINEGLUE, 1, null);
    }

    /**
     * (24) const get element SpineLevelling
     * @return JDFOptionSpan the element
     */
    public JDFOptionSpan getSpineLevelling()
    {
        return (JDFOptionSpan) getElement(ElementName.SPINELEVELLING, null, 0);
    }

    /** (25) getCreateSpineLevelling
     * 
     * @return JDFOptionSpan the element
     */
    public JDFOptionSpan getCreateSpineLevelling()
    {
        return (JDFOptionSpan) getCreateElement_KElement(ElementName.SPINELEVELLING, null, 0);
    }

    /**
     * (29) append element SpineLevelling
     */
    public JDFOptionSpan appendSpineLevelling() throws JDFException
    {
        return (JDFOptionSpan) appendElementN(ElementName.SPINELEVELLING, 1, null);
    }

    /**
     * (24) const get element SpineMilling
     * @return JDFOptionSpan the element
     */
    public JDFOptionSpan getSpineMilling()
    {
        return (JDFOptionSpan) getElement(ElementName.SPINEMILLING, null, 0);
    }

    /** (25) getCreateSpineMilling
     * 
     * @return JDFOptionSpan the element
     */
    public JDFOptionSpan getCreateSpineMilling()
    {
        return (JDFOptionSpan) getCreateElement_KElement(ElementName.SPINEMILLING, null, 0);
    }

    /**
     * (29) append element SpineMilling
     */
    public JDFOptionSpan appendSpineMilling() throws JDFException
    {
        return (JDFOptionSpan) appendElementN(ElementName.SPINEMILLING, 1, null);
    }

    /**
     * (24) const get element SpineNotching
     * @return JDFOptionSpan the element
     */
    public JDFOptionSpan getSpineNotching()
    {
        return (JDFOptionSpan) getElement(ElementName.SPINENOTCHING, null, 0);
    }

    /** (25) getCreateSpineNotching
     * 
     * @return JDFOptionSpan the element
     */
    public JDFOptionSpan getCreateSpineNotching()
    {
        return (JDFOptionSpan) getCreateElement_KElement(ElementName.SPINENOTCHING, null, 0);
    }

    /**
     * (29) append element SpineNotching
     */
    public JDFOptionSpan appendSpineNotching() throws JDFException
    {
        return (JDFOptionSpan) appendElementN(ElementName.SPINENOTCHING, 1, null);
    }

    /**
     * (24) const get element SpineSanding
     * @return JDFOptionSpan the element
     */
    public JDFOptionSpan getSpineSanding()
    {
        return (JDFOptionSpan) getElement(ElementName.SPINESANDING, null, 0);
    }

    /** (25) getCreateSpineSanding
     * 
     * @return JDFOptionSpan the element
     */
    public JDFOptionSpan getCreateSpineSanding()
    {
        return (JDFOptionSpan) getCreateElement_KElement(ElementName.SPINESANDING, null, 0);
    }

    /**
     * (29) append element SpineSanding
     */
    public JDFOptionSpan appendSpineSanding() throws JDFException
    {
        return (JDFOptionSpan) appendElementN(ElementName.SPINESANDING, 1, null);
    }

    /**
     * (24) const get element SpineShredding
     * @return JDFOptionSpan the element
     */
    public JDFOptionSpan getSpineShredding()
    {
        return (JDFOptionSpan) getElement(ElementName.SPINESHREDDING, null, 0);
    }

    /** (25) getCreateSpineShredding
     * 
     * @return JDFOptionSpan the element
     */
    public JDFOptionSpan getCreateSpineShredding()
    {
        return (JDFOptionSpan) getCreateElement_KElement(ElementName.SPINESHREDDING, null, 0);
    }

    /**
     * (29) append element SpineShredding
     */
    public JDFOptionSpan appendSpineShredding() throws JDFException
    {
        return (JDFOptionSpan) appendElementN(ElementName.SPINESHREDDING, 1, null);
    }

    /**
     * (24) const get element StripMaterial
     * @return JDFSpanStripMaterial the element
     */
    public JDFSpanStripMaterial getStripMaterial()
    {
        return (JDFSpanStripMaterial) getElement(ElementName.STRIPMATERIAL, null, 0);
    }

    /** (25) getCreateStripMaterial
     * 
     * @return JDFSpanStripMaterial the element
     */
    public JDFSpanStripMaterial getCreateStripMaterial()
    {
        return (JDFSpanStripMaterial) getCreateElement_KElement(ElementName.STRIPMATERIAL, null, 0);
    }

    /**
     * (29) append element StripMaterial
     */
    public JDFSpanStripMaterial appendStripMaterial() throws JDFException
    {
        return (JDFSpanStripMaterial) appendElementN(ElementName.STRIPMATERIAL, 1, null);
    }

    /**
     * (24) const get element TightBacking
     * @return JDFSpanTightBacking the element
     */
    public JDFSpanTightBacking getTightBacking()
    {
        return (JDFSpanTightBacking) getElement(ElementName.TIGHTBACKING, null, 0);
    }

    /** (25) getCreateTightBacking
     * 
     * @return JDFSpanTightBacking the element
     */
    public JDFSpanTightBacking getCreateTightBacking()
    {
        return (JDFSpanTightBacking) getCreateElement_KElement(ElementName.TIGHTBACKING, null, 0);
    }

    /**
     * (29) append element TightBacking
     */
    public JDFSpanTightBacking appendTightBacking() throws JDFException
    {
        return (JDFSpanTightBacking) appendElementN(ElementName.TIGHTBACKING, 1, null);
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

}// end namespace JDF
