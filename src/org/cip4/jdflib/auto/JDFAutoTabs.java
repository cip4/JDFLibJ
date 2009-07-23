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
import org.apache.xerces.dom.CoreDocumentImpl;      
import org.cip4.jdflib.core.*;                      
import org.cip4.jdflib.span.*;

public abstract class JDFAutoTabs extends JDFElement
{

    private static final long serialVersionUID = 1L;

    private static AtrInfoTable[] atrInfoTable = new AtrInfoTable[3];
    static
    {
        atrInfoTable[0] = new AtrInfoTable(AttributeName.TABBANKS, 0x33333333, AttributeInfo.EnumAttributeType.integer, null, "1");
        atrInfoTable[1] = new AtrInfoTable(AttributeName.TABCOUNT, 0x33331111, AttributeInfo.EnumAttributeType.integer, null, null);
        atrInfoTable[2] = new AtrInfoTable(AttributeName.TABSPERBANK, 0x33333333, AttributeInfo.EnumAttributeType.integer, null, null);
    }
    
    @Override
	protected AttributeInfo getTheAttributeInfo()
    {
        return super.getTheAttributeInfo().updateReplace(atrInfoTable);
    }


    private static ElemInfoTable[] elemInfoTable = new ElemInfoTable[7];
    static
    {
        elemInfoTable[0] = new ElemInfoTable(ElementName.TABBRAND, 0x66666111);
        elemInfoTable[1] = new ElemInfoTable(ElementName.TABEXTENSIONDISTANCE, 0x66666666);
        elemInfoTable[2] = new ElemInfoTable(ElementName.TABEXTENSIONMYLAR, 0x66666666);
        elemInfoTable[3] = new ElemInfoTable(ElementName.TABBINDMYLAR, 0x66666666);
        elemInfoTable[4] = new ElemInfoTable(ElementName.TABBODYCOPY, 0x66666666);
        elemInfoTable[5] = new ElemInfoTable(ElementName.TABMYLARCOLOR, 0x66666666);
        elemInfoTable[6] = new ElemInfoTable(ElementName.TABMYLARCOLORDETAILS, 0x33331111);
    }
    
    @Override
	protected ElementInfo getTheElementInfo()
    {
        return super.getTheElementInfo().updateReplace(elemInfoTable);
    }



    /**
     * Constructor for JDFAutoTabs
     * @param myOwnerDocument
     * @param qualifiedName
     */
    protected JDFAutoTabs(
        CoreDocumentImpl myOwnerDocument,
        String qualifiedName)
    {
        super(myOwnerDocument, qualifiedName);
    }

    /**
     * Constructor for JDFAutoTabs
     * @param myOwnerDocument
     * @param myNamespaceURI
     * @param qualifiedName
     */
    protected JDFAutoTabs(
        CoreDocumentImpl myOwnerDocument,
        String myNamespaceURI,
        String qualifiedName)
    {
        super(myOwnerDocument, myNamespaceURI, qualifiedName);
    }

    /**
     * Constructor for JDFAutoTabs
     * @param myOwnerDocument
     * @param myNamespaceURI
     * @param qualifiedName
     * @param myLocalName
     */
    protected JDFAutoTabs(
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
        return " JDFAutoTabs[  --> " + super.toString() + " ]";
    }


/* ************************************************************************
 * Attribute getter / setter
 * ************************************************************************
 */
        
        /* ---------------------------------------------------------------------
        Methods for Attribute TabBanks
        --------------------------------------------------------------------- */
        /**
          * (36) set attribute TabBanks
          * @param value: the value to set the attribute to
          */
        public void setTabBanks(int value)
        {
            setAttribute(AttributeName.TABBANKS, value, null);
        }

        /**
          * (15) get int attribute TabBanks
          * @return int the value of the attribute
          */
        public int getTabBanks()
        {
            return getIntAttribute(AttributeName.TABBANKS, null, 1);
        }

        
        /* ---------------------------------------------------------------------
        Methods for Attribute TabCount
        --------------------------------------------------------------------- */
        /**
          * (36) set attribute TabCount
          * @param value: the value to set the attribute to
          */
        public void setTabCount(int value)
        {
            setAttribute(AttributeName.TABCOUNT, value, null);
        }

        /**
          * (15) get int attribute TabCount
          * @return int the value of the attribute
          */
        public int getTabCount()
        {
            return getIntAttribute(AttributeName.TABCOUNT, null, 0);
        }

        
        /* ---------------------------------------------------------------------
        Methods for Attribute TabsPerBank
        --------------------------------------------------------------------- */
        /**
          * (36) set attribute TabsPerBank
          * @param value: the value to set the attribute to
          */
        public void setTabsPerBank(int value)
        {
            setAttribute(AttributeName.TABSPERBANK, value, null);
        }

        /**
          * (15) get int attribute TabsPerBank
          * @return int the value of the attribute
          */
        public int getTabsPerBank()
        {
            return getIntAttribute(AttributeName.TABSPERBANK, null, 0);
        }

/* ***********************************************************************
 * Element getter / setter
 * ***********************************************************************
 */

    /**
     * (24) const get element TabBrand
     * @return JDFStringSpan the element
     */
    public JDFStringSpan getTabBrand()
    {
        return (JDFStringSpan) getElement(ElementName.TABBRAND, null, 0);
    }

    /** (25) getCreateTabBrand
     * 
     * @return JDFStringSpan the element
     */
    public JDFStringSpan getCreateTabBrand()
    {
        return (JDFStringSpan) getCreateElement_KElement(ElementName.TABBRAND, null, 0);
    }

    /**
     * (29) append element TabBrand
     */
    public JDFStringSpan appendTabBrand() throws JDFException
    {
        return (JDFStringSpan) appendElementN(ElementName.TABBRAND, 1, null);
    }

    /**
     * (24) const get element TabExtensionDistance
     * @return JDFNumberSpan the element
     */
    public JDFNumberSpan getTabExtensionDistance()
    {
        return (JDFNumberSpan) getElement(ElementName.TABEXTENSIONDISTANCE, null, 0);
    }

    /** (25) getCreateTabExtensionDistance
     * 
     * @return JDFNumberSpan the element
     */
    public JDFNumberSpan getCreateTabExtensionDistance()
    {
        return (JDFNumberSpan) getCreateElement_KElement(ElementName.TABEXTENSIONDISTANCE, null, 0);
    }

    /**
     * (29) append element TabExtensionDistance
     */
    public JDFNumberSpan appendTabExtensionDistance() throws JDFException
    {
        return (JDFNumberSpan) appendElementN(ElementName.TABEXTENSIONDISTANCE, 1, null);
    }

    /**
     * (24) const get element TabExtensionMylar
     * @return JDFOptionSpan the element
     */
    public JDFOptionSpan getTabExtensionMylar()
    {
        return (JDFOptionSpan) getElement(ElementName.TABEXTENSIONMYLAR, null, 0);
    }

    /** (25) getCreateTabExtensionMylar
     * 
     * @return JDFOptionSpan the element
     */
    public JDFOptionSpan getCreateTabExtensionMylar()
    {
        return (JDFOptionSpan) getCreateElement_KElement(ElementName.TABEXTENSIONMYLAR, null, 0);
    }

    /**
     * (29) append element TabExtensionMylar
     */
    public JDFOptionSpan appendTabExtensionMylar() throws JDFException
    {
        return (JDFOptionSpan) appendElementN(ElementName.TABEXTENSIONMYLAR, 1, null);
    }

    /**
     * (24) const get element TabBindMylar
     * @return JDFOptionSpan the element
     */
    public JDFOptionSpan getTabBindMylar()
    {
        return (JDFOptionSpan) getElement(ElementName.TABBINDMYLAR, null, 0);
    }

    /** (25) getCreateTabBindMylar
     * 
     * @return JDFOptionSpan the element
     */
    public JDFOptionSpan getCreateTabBindMylar()
    {
        return (JDFOptionSpan) getCreateElement_KElement(ElementName.TABBINDMYLAR, null, 0);
    }

    /**
     * (29) append element TabBindMylar
     */
    public JDFOptionSpan appendTabBindMylar() throws JDFException
    {
        return (JDFOptionSpan) appendElementN(ElementName.TABBINDMYLAR, 1, null);
    }

    /**
     * (24) const get element TabBodyCopy
     * @return JDFOptionSpan the element
     */
    public JDFOptionSpan getTabBodyCopy()
    {
        return (JDFOptionSpan) getElement(ElementName.TABBODYCOPY, null, 0);
    }

    /** (25) getCreateTabBodyCopy
     * 
     * @return JDFOptionSpan the element
     */
    public JDFOptionSpan getCreateTabBodyCopy()
    {
        return (JDFOptionSpan) getCreateElement_KElement(ElementName.TABBODYCOPY, null, 0);
    }

    /**
     * (29) append element TabBodyCopy
     */
    public JDFOptionSpan appendTabBodyCopy() throws JDFException
    {
        return (JDFOptionSpan) appendElementN(ElementName.TABBODYCOPY, 1, null);
    }

    /**
     * (24) const get element TabMylarColor
     * @return JDFSpanNamedColor the element
     */
    public JDFSpanNamedColor getTabMylarColor()
    {
        return (JDFSpanNamedColor) getElement(ElementName.TABMYLARCOLOR, null, 0);
    }

    /** (25) getCreateTabMylarColor
     * 
     * @return JDFSpanNamedColor the element
     */
    public JDFSpanNamedColor getCreateTabMylarColor()
    {
        return (JDFSpanNamedColor) getCreateElement_KElement(ElementName.TABMYLARCOLOR, null, 0);
    }

    /**
     * (29) append element TabMylarColor
     */
    public JDFSpanNamedColor appendTabMylarColor() throws JDFException
    {
        return (JDFSpanNamedColor) appendElementN(ElementName.TABMYLARCOLOR, 1, null);
    }

    /** (26) getCreateTabMylarColorDetails
     * 
     * @param iSkip number of elements to skip
     * @return JDFStringSpan the element
     */
    public JDFStringSpan getCreateTabMylarColorDetails(int iSkip)
    {
        return (JDFStringSpan)getCreateElement_KElement(ElementName.TABMYLARCOLORDETAILS, null, iSkip);
    }

    /**
     * (27) const get element TabMylarColorDetails
     * @param iSkip number of elements to skip
     * @return JDFStringSpan the element
     * default is getTabMylarColorDetails(0)     */
    public JDFStringSpan getTabMylarColorDetails(int iSkip)
    {
        return (JDFStringSpan) getElement(ElementName.TABMYLARCOLORDETAILS, null, iSkip);
    }

    /**
     * Get all TabMylarColorDetails from the current element
     * 
     * @return Collection<JDFStringSpan>, null if none are available
     */
    public Collection<JDFStringSpan> getAllTabMylarColorDetails()
    {
        final VElement vc = getChildElementVector(ElementName.TABMYLARCOLORDETAILS, null);
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
     * (30) append element TabMylarColorDetails
     */
    public JDFStringSpan appendTabMylarColorDetails() throws JDFException
    {
        return (JDFStringSpan) appendElement(ElementName.TABMYLARCOLORDETAILS, null);
    }

}// end namespace JDF
