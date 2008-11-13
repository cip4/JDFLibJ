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
import org.cip4.jdflib.core.AtrInfoTable;
import org.cip4.jdflib.core.AttributeInfo;
import org.cip4.jdflib.core.AttributeName;
import org.cip4.jdflib.core.ElemInfoTable;
import org.cip4.jdflib.core.ElementInfo;
import org.cip4.jdflib.core.ElementName;
import org.cip4.jdflib.core.JDFConstants;
import org.cip4.jdflib.core.JDFElement;
import org.cip4.jdflib.core.JDFException;
import org.cip4.jdflib.resource.JDFPayment;
import org.cip4.jdflib.resource.intent.JDFPricing;

public abstract class JDFAutoPricing extends JDFElement
{

    private static final long serialVersionUID = 1L;

    private static AtrInfoTable[] atrInfoTable = new AtrInfoTable[5];
    static
    {
        atrInfoTable[0] = new AtrInfoTable(AttributeName.ADDITIONALPRICE, 0x44444333, AttributeInfo.EnumAttributeType.double_, null, null);
        atrInfoTable[1] = new AtrInfoTable(AttributeName.CURRENCY, 0x44444333, AttributeInfo.EnumAttributeType.NMTOKEN, null, null);
        atrInfoTable[2] = new AtrInfoTable(AttributeName.HASPRICE, 0x44444333, AttributeInfo.EnumAttributeType.boolean_, null, "true");
        atrInfoTable[3] = new AtrInfoTable(AttributeName.ITEM, 0x44444333, AttributeInfo.EnumAttributeType.string, null, null);
        atrInfoTable[4] = new AtrInfoTable(AttributeName.PRICE, 0x44444333, AttributeInfo.EnumAttributeType.double_, null, null);
    }
    
    protected AttributeInfo getTheAttributeInfo()
    {
        return super.getTheAttributeInfo().updateReplace(atrInfoTable);
    }


    private static ElemInfoTable[] elemInfoTable = new ElemInfoTable[2];
    static
    {
        elemInfoTable[0] = new ElemInfoTable(ElementName.PAYMENT, 0x44444331);
        elemInfoTable[1] = new ElemInfoTable(ElementName.PRICING, 0x44444333);
    }
    
    protected ElementInfo getTheElementInfo()
    {
        return super.getTheElementInfo().updateReplace(elemInfoTable);
    }



    /**
     * Constructor for JDFAutoPricing
     * @param myOwnerDocument
     * @param qualifiedName
     */
    protected JDFAutoPricing(
        CoreDocumentImpl myOwnerDocument,
        String qualifiedName)
    {
        super(myOwnerDocument, qualifiedName);
    }

    /**
     * Constructor for JDFAutoPricing
     * @param myOwnerDocument
     * @param myNamespaceURI
     * @param qualifiedName
     */
    protected JDFAutoPricing(
        CoreDocumentImpl myOwnerDocument,
        String myNamespaceURI,
        String qualifiedName)
    {
        super(myOwnerDocument, myNamespaceURI, qualifiedName);
    }

    /**
     * Constructor for JDFAutoPricing
     * @param myOwnerDocument
     * @param myNamespaceURI
     * @param qualifiedName
     * @param myLocalName
     */
    protected JDFAutoPricing(
        CoreDocumentImpl myOwnerDocument,
        String myNamespaceURI,
        String qualifiedName,
        String myLocalName)
    {
        super(myOwnerDocument, myNamespaceURI, qualifiedName, myLocalName);
    }


    public String toString()
    {
        return " JDFAutoPricing[  --> " + super.toString() + " ]";
    }


/* ************************************************************************
 * Attribute getter / setter
 * ************************************************************************
 */
        
        /* ---------------------------------------------------------------------
        Methods for Attribute AdditionalPrice
        --------------------------------------------------------------------- */
        /**
          * (36) set attribute AdditionalPrice
          * @param value: the value to set the attribute to
          */
        public void setAdditionalPrice(double value)
        {
            setAttribute(AttributeName.ADDITIONALPRICE, value, null);
        }

        /**
          * (17) get double attribute AdditionalPrice
          * @return double the value of the attribute
          */
        public double getAdditionalPrice()
        {
            return getRealAttribute(AttributeName.ADDITIONALPRICE, null, 0.0);
        }

        
        /* ---------------------------------------------------------------------
        Methods for Attribute Currency
        --------------------------------------------------------------------- */
        /**
          * (36) set attribute Currency
          * @param value: the value to set the attribute to
          */
        public void setCurrency(String value)
        {
            setAttribute(AttributeName.CURRENCY, value, null);
        }

        /**
          * (23) get String attribute Currency
          * @return the value of the attribute
          */
        public String getCurrency()
        {
            return getAttribute(AttributeName.CURRENCY, null, JDFConstants.EMPTYSTRING);
        }

        
        /* ---------------------------------------------------------------------
        Methods for Attribute HasPrice
        --------------------------------------------------------------------- */
        /**
          * (36) set attribute HasPrice
          * @param value: the value to set the attribute to
          */
        public void setHasPrice(boolean value)
        {
            setAttribute(AttributeName.HASPRICE, value, null);
        }

        /**
          * (18) get boolean attribute HasPrice
          * @return boolean the value of the attribute
          */
        public boolean getHasPrice()
        {
            return getBoolAttribute(AttributeName.HASPRICE, null, true);
        }

        
        /* ---------------------------------------------------------------------
        Methods for Attribute Item
        --------------------------------------------------------------------- */
        /**
          * (36) set attribute Item
          * @param value: the value to set the attribute to
          */
        public void setItem(String value)
        {
            setAttribute(AttributeName.ITEM, value, null);
        }

        /**
          * (23) get String attribute Item
          * @return the value of the attribute
          */
        public String getItem()
        {
            return getAttribute(AttributeName.ITEM, null, JDFConstants.EMPTYSTRING);
        }

        
        /* ---------------------------------------------------------------------
        Methods for Attribute Price
        --------------------------------------------------------------------- */
        /**
          * (36) set attribute Price
          * @param value: the value to set the attribute to
          */
        public void setPrice(double value)
        {
            setAttribute(AttributeName.PRICE, value, null);
        }

        /**
          * (17) get double attribute Price
          * @return double the value of the attribute
          */
        public double getPrice()
        {
            return getRealAttribute(AttributeName.PRICE, null, 0.0);
        }

/* ***********************************************************************
 * Element getter / setter
 * ***********************************************************************
 */

    /** (26) getCreatePayment
     * 
     * @param iSkip number of elements to skip
     * @return JDFPayment the element
     */
    public JDFPayment getCreatePayment(int iSkip)
    {
        return (JDFPayment)getCreateElement_KElement(ElementName.PAYMENT, null, iSkip);
    }

    /**
     * (27) const get element Payment
     * @param iSkip number of elements to skip
     * @return JDFPayment the element
     * default is getPayment(0)     */
    public JDFPayment getPayment(int iSkip)
    {
        return (JDFPayment) getElement(ElementName.PAYMENT, null, iSkip);
    }

    /**
     * Get all Payment from the current element
     * 
     * @return Collection<JDFPayment>
     */
    public Collection<JDFPayment> getAllPayment()
    {
        Vector<JDFPayment> v = new Vector<JDFPayment>();

        JDFPayment kElem = (JDFPayment) getFirstChildElement(ElementName.PAYMENT, null);

        while (kElem != null)
        {
            v.add(kElem);

            kElem = (JDFPayment) kElem.getNextSiblingElement(ElementName.PAYMENT, null);
        }

        return v;
    }

    /**
     * (30) append element Payment
     */
    public JDFPayment appendPayment() throws JDFException
    {
        return (JDFPayment) appendElement(ElementName.PAYMENT, null);
    }

    /** (26) getCreatePricing
     * 
     * @param iSkip number of elements to skip
     * @return JDFPricing the element
     */
    public JDFPricing getCreatePricing(int iSkip)
    {
        return (JDFPricing)getCreateElement_KElement(ElementName.PRICING, null, iSkip);
    }

    /**
     * (27) const get element Pricing
     * @param iSkip number of elements to skip
     * @return JDFPricing the element
     * default is getPricing(0)     */
    public JDFPricing getPricing(int iSkip)
    {
        return (JDFPricing) getElement(ElementName.PRICING, null, iSkip);
    }

    /**
     * Get all Pricing from the current element
     * 
     * @return Collection<JDFPricing>
     */
    public Collection<JDFPricing> getAllPricing()
    {
        Vector<JDFPricing> v = new Vector<JDFPricing>();

        JDFPricing kElem = (JDFPricing) getFirstChildElement(ElementName.PRICING, null);

        while (kElem != null)
        {
            v.add(kElem);

            kElem = (JDFPricing) kElem.getNextSiblingElement(ElementName.PRICING, null);
        }

        return v;
    }

    /**
     * (30) append element Pricing
     */
    public JDFPricing appendPricing() throws JDFException
    {
        return (JDFPricing) appendElement(ElementName.PRICING, null);
    }

}// end namespace JDF
