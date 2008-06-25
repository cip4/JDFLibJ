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

import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.enums.ValuedEnum;
import org.apache.xerces.dom.CoreDocumentImpl;
import org.cip4.jdflib.core.AtrInfoTable;
import org.cip4.jdflib.core.AttributeInfo;
import org.cip4.jdflib.core.AttributeName;
import org.cip4.jdflib.core.ElemInfoTable;
import org.cip4.jdflib.core.ElementInfo;
import org.cip4.jdflib.core.ElementName;
import org.cip4.jdflib.core.JDFConstants;
import org.cip4.jdflib.core.JDFException;
import org.cip4.jdflib.resource.intent.JDFDropIntent;
import org.cip4.jdflib.resource.intent.JDFIntentResource;
import org.cip4.jdflib.resource.intent.JDFPricing;
import org.cip4.jdflib.resource.process.JDFCompany;
import org.cip4.jdflib.resource.process.JDFContact;
import org.cip4.jdflib.span.JDFDurationSpan;
import org.cip4.jdflib.span.JDFNameSpan;
import org.cip4.jdflib.span.JDFNumberSpan;
import org.cip4.jdflib.span.JDFSpanDeliveryCharge;
import org.cip4.jdflib.span.JDFSpanSurplusHandling;
import org.cip4.jdflib.span.JDFSpanTransfer;
import org.cip4.jdflib.span.JDFStringSpan;
import org.cip4.jdflib.span.JDFTimeSpan;
    /*
    *****************************************************************************
    class JDFAutoDeliveryIntent : public JDFIntentResource

    *****************************************************************************
    */

public abstract class JDFAutoDeliveryIntent extends JDFIntentResource
{

    private static final long serialVersionUID = 1L;

    private static AtrInfoTable[] atrInfoTable = new AtrInfoTable[5];
    static
    {
        atrInfoTable[0] = new AtrInfoTable(AttributeName.ACCEPTED, 0x44444333, AttributeInfo.EnumAttributeType.boolean_, null, null);
        atrInfoTable[1] = new AtrInfoTable(AttributeName.ADDITIONALAMOUNT, 0x44444311, AttributeInfo.EnumAttributeType.integer, null, null);
        atrInfoTable[2] = new AtrInfoTable(AttributeName.OWNERSHIP, 0x33333333, AttributeInfo.EnumAttributeType.enumeration, EnumOwnership.getEnum(0), "Origin");
        atrInfoTable[3] = new AtrInfoTable(AttributeName.BUYERACCOUNT, 0x33333333, AttributeInfo.EnumAttributeType.string, null, null);
        atrInfoTable[4] = new AtrInfoTable(AttributeName.PICKUP, 0x44444443, AttributeInfo.EnumAttributeType.boolean_, null, null);
    }
    
    protected AttributeInfo getTheAttributeInfo()
    {
        return super.getTheAttributeInfo().updateReplace(atrInfoTable);
    }


    private static ElemInfoTable[] elemInfoTable = new ElemInfoTable[16];
    static
    {
        elemInfoTable[0] = new ElemInfoTable(ElementName.DELIVERYCHARGE, 0x66666661);
        elemInfoTable[1] = new ElemInfoTable(ElementName.EARLIEST, 0x66666666);
        elemInfoTable[2] = new ElemInfoTable(ElementName.EARLIESTDURATION, 0x66666666);
        elemInfoTable[3] = new ElemInfoTable(ElementName.METHOD, 0x66666666);
        elemInfoTable[4] = new ElemInfoTable(ElementName.OVERAGE, 0x66666666);
        elemInfoTable[5] = new ElemInfoTable(ElementName.REQUIRED, 0x66666666);
        elemInfoTable[6] = new ElemInfoTable(ElementName.REQUIREDDURATION, 0x66666666);
        elemInfoTable[7] = new ElemInfoTable(ElementName.RETURNMETHOD, 0x66666661);
        elemInfoTable[8] = new ElemInfoTable(ElementName.SERVICELEVEL, 0x66666611);
        elemInfoTable[9] = new ElemInfoTable(ElementName.SURPLUSHANDLING, 0x66666661);
        elemInfoTable[10] = new ElemInfoTable(ElementName.TRANSFER, 0x66666661);
        elemInfoTable[11] = new ElemInfoTable(ElementName.UNDERAGE, 0x66666666);
        elemInfoTable[12] = new ElemInfoTable(ElementName.COMPANY, 0x77777776);
        elemInfoTable[13] = new ElemInfoTable(ElementName.CONTACT, 0x33333331);
        elemInfoTable[14] = new ElemInfoTable(ElementName.DROPINTENT, 0x22222222);
        elemInfoTable[15] = new ElemInfoTable(ElementName.PRICING, 0x77777666);
    }
    
    protected ElementInfo getTheElementInfo()
    {
        return super.getTheElementInfo().updateReplace(elemInfoTable);
    }



    /**
     * Constructor for JDFAutoDeliveryIntent
     * @param myOwnerDocument
     * @param qualifiedName
     */
    protected JDFAutoDeliveryIntent(
        CoreDocumentImpl myOwnerDocument,
        String qualifiedName)
    {
        super(myOwnerDocument, qualifiedName);
    }

    /**
     * Constructor for JDFAutoDeliveryIntent
     * @param myOwnerDocument
     * @param myNamespaceURI
     * @param qualifiedName
     */
    protected JDFAutoDeliveryIntent(
        CoreDocumentImpl myOwnerDocument,
        String myNamespaceURI,
        String qualifiedName)
    {
        super(myOwnerDocument, myNamespaceURI, qualifiedName);
    }

    /**
     * Constructor for JDFAutoDeliveryIntent
     * @param myOwnerDocument
     * @param myNamespaceURI
     * @param qualifiedName
     * @param myLocalName
     */
    protected JDFAutoDeliveryIntent(
        CoreDocumentImpl myOwnerDocument,
        String myNamespaceURI,
        String qualifiedName,
        String myLocalName)
    {
        super(myOwnerDocument, myNamespaceURI, qualifiedName, myLocalName);
    }


    public String toString()
    {
        return " JDFAutoDeliveryIntent[  --> " + super.toString() + " ]";
    }


        /**
        * Enumeration strings for Ownership
        */

        public static class EnumOwnership extends ValuedEnum
        {
            private static final long serialVersionUID = 1L;
            private static int m_startValue = 0;

            private EnumOwnership(String name)
            {
                super(name, m_startValue++);
            }

            public static EnumOwnership getEnum(String enumName)
            {
                return (EnumOwnership) getEnum(EnumOwnership.class, enumName);
            }

            public static EnumOwnership getEnum(int enumValue)
            {
                return (EnumOwnership) getEnum(EnumOwnership.class, enumValue);
            }

            public static Map getEnumMap()
            {
                return getEnumMap(EnumOwnership.class);
            }

            public static List getEnumList()
            {
                return getEnumList(EnumOwnership.class);
            }

            public static Iterator iterator()
            {
                return iterator(EnumOwnership.class);
            }

            public static final EnumOwnership Origin = new EnumOwnership("Origin");
            public static final EnumOwnership Destination = new EnumOwnership("Destination");
        }      



/* ************************************************************************
 * Attribute getter / setter
 * ************************************************************************
 */
        
        /* ---------------------------------------------------------------------
        Methods for Attribute Accepted
        --------------------------------------------------------------------- */
        /**
          * (36) set attribute Accepted
          * @param value: the value to set the attribute to
          */
        public void setAccepted(boolean value)
        {
            setAttribute(AttributeName.ACCEPTED, value, null);
        }

        /**
          * (18) get boolean attribute Accepted
          * @return boolean the value of the attribute
          */
        public boolean getAccepted()
        {
            return getBoolAttribute(AttributeName.ACCEPTED, null, false);
        }

        
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
        Methods for Attribute Ownership
        --------------------------------------------------------------------- */
        /**
          * (5) set attribute Ownership
          * @param enumVar: the enumVar to set the attribute to
          */
        public void setOwnership(EnumOwnership enumVar)
        {
            setAttribute(AttributeName.OWNERSHIP, enumVar==null ? null : enumVar.getName(), null);
        }

        /**
          * (9) get attribute Ownership
          * @return the value of the attribute
          */
        public EnumOwnership getOwnership()
        {
            return EnumOwnership.getEnum(getAttribute(AttributeName.OWNERSHIP, null, "Origin"));
        }

        
        /* ---------------------------------------------------------------------
        Methods for Attribute BuyerAccount
        --------------------------------------------------------------------- */
        /**
          * (36) set attribute BuyerAccount
          * @param value: the value to set the attribute to
          */
        public void setBuyerAccount(String value)
        {
            setAttribute(AttributeName.BUYERACCOUNT, value, null);
        }

        /**
          * (23) get String attribute BuyerAccount
          * @return the value of the attribute
          */
        public String getBuyerAccount()
        {
            return getAttribute(AttributeName.BUYERACCOUNT, null, JDFConstants.EMPTYSTRING);
        }

        
        /* ---------------------------------------------------------------------
        Methods for Attribute Pickup
        --------------------------------------------------------------------- */
        /**
          * (36) set attribute Pickup
          * @param value: the value to set the attribute to
          */
        public void setPickup(boolean value)
        {
            setAttribute(AttributeName.PICKUP, value, null);
        }

        /**
          * (18) get boolean attribute Pickup
          * @return boolean the value of the attribute
          */
        public boolean getPickup()
        {
            return getBoolAttribute(AttributeName.PICKUP, null, false);
        }

/* ***********************************************************************
 * Element getter / setter
 * ***********************************************************************
 */

    /**
     * (24) const get element DeliveryCharge
     * @return JDFSpanDeliveryCharge the element
     */
    public JDFSpanDeliveryCharge getDeliveryCharge()
    {
        return (JDFSpanDeliveryCharge) getElement(ElementName.DELIVERYCHARGE, null, 0);
    }

    /** (25) getCreateDeliveryCharge
     * 
     * @return JDFSpanDeliveryCharge the element
     */
    public JDFSpanDeliveryCharge getCreateDeliveryCharge()
    {
        return (JDFSpanDeliveryCharge) getCreateElement_KElement(ElementName.DELIVERYCHARGE, null, 0);
    }

    /**
     * (29) append element DeliveryCharge
     */
    public JDFSpanDeliveryCharge appendDeliveryCharge() throws JDFException
    {
        return (JDFSpanDeliveryCharge) appendElementN(ElementName.DELIVERYCHARGE, 1, null);
    }

    /**
     * (24) const get element Earliest
     * @return JDFTimeSpan the element
     */
    public JDFTimeSpan getEarliest()
    {
        return (JDFTimeSpan) getElement(ElementName.EARLIEST, null, 0);
    }

    /** (25) getCreateEarliest
     * 
     * @return JDFTimeSpan the element
     */
    public JDFTimeSpan getCreateEarliest()
    {
        return (JDFTimeSpan) getCreateElement_KElement(ElementName.EARLIEST, null, 0);
    }

    /**
     * (29) append element Earliest
     */
    public JDFTimeSpan appendEarliest() throws JDFException
    {
        return (JDFTimeSpan) appendElementN(ElementName.EARLIEST, 1, null);
    }

    /**
     * (24) const get element EarliestDuration
     * @return JDFDurationSpan the element
     */
    public JDFDurationSpan getEarliestDuration()
    {
        return (JDFDurationSpan) getElement(ElementName.EARLIESTDURATION, null, 0);
    }

    /** (25) getCreateEarliestDuration
     * 
     * @return JDFDurationSpan the element
     */
    public JDFDurationSpan getCreateEarliestDuration()
    {
        return (JDFDurationSpan) getCreateElement_KElement(ElementName.EARLIESTDURATION, null, 0);
    }

    /**
     * (29) append element EarliestDuration
     */
    public JDFDurationSpan appendEarliestDuration() throws JDFException
    {
        return (JDFDurationSpan) appendElementN(ElementName.EARLIESTDURATION, 1, null);
    }

    /**
     * (24) const get element Method
     * @return JDFNameSpan the element
     */
    public JDFNameSpan getMethod()
    {
        return (JDFNameSpan) getElement(ElementName.METHOD, null, 0);
    }

    /** (25) getCreateMethod
     * 
     * @return JDFNameSpan the element
     */
    public JDFNameSpan getCreateMethod()
    {
        return (JDFNameSpan) getCreateElement_KElement(ElementName.METHOD, null, 0);
    }

    /**
     * (29) append element Method
     */
    public JDFNameSpan appendMethod() throws JDFException
    {
        return (JDFNameSpan) appendElementN(ElementName.METHOD, 1, null);
    }

    /**
     * (24) const get element Overage
     * @return JDFNumberSpan the element
     */
    public JDFNumberSpan getOverage()
    {
        return (JDFNumberSpan) getElement(ElementName.OVERAGE, null, 0);
    }

    /** (25) getCreateOverage
     * 
     * @return JDFNumberSpan the element
     */
    public JDFNumberSpan getCreateOverage()
    {
        return (JDFNumberSpan) getCreateElement_KElement(ElementName.OVERAGE, null, 0);
    }

    /**
     * (29) append element Overage
     */
    public JDFNumberSpan appendOverage() throws JDFException
    {
        return (JDFNumberSpan) appendElementN(ElementName.OVERAGE, 1, null);
    }

    /**
     * (24) const get element Required
     * @return JDFTimeSpan the element
     */
    public JDFTimeSpan getRequired()
    {
        return (JDFTimeSpan) getElement(ElementName.REQUIRED, null, 0);
    }

    /** (25) getCreateRequired
     * 
     * @return JDFTimeSpan the element
     */
    public JDFTimeSpan getCreateRequired()
    {
        return (JDFTimeSpan) getCreateElement_KElement(ElementName.REQUIRED, null, 0);
    }

    /**
     * (29) append element Required
     */
    public JDFTimeSpan appendRequired() throws JDFException
    {
        return (JDFTimeSpan) appendElementN(ElementName.REQUIRED, 1, null);
    }

    /**
     * (24) const get element RequiredDuration
     * @return JDFDurationSpan the element
     */
    public JDFDurationSpan getRequiredDuration()
    {
        return (JDFDurationSpan) getElement(ElementName.REQUIREDDURATION, null, 0);
    }

    /** (25) getCreateRequiredDuration
     * 
     * @return JDFDurationSpan the element
     */
    public JDFDurationSpan getCreateRequiredDuration()
    {
        return (JDFDurationSpan) getCreateElement_KElement(ElementName.REQUIREDDURATION, null, 0);
    }

    /**
     * (29) append element RequiredDuration
     */
    public JDFDurationSpan appendRequiredDuration() throws JDFException
    {
        return (JDFDurationSpan) appendElementN(ElementName.REQUIREDDURATION, 1, null);
    }

    /**
     * (24) const get element ReturnMethod
     * @return JDFNameSpan the element
     */
    public JDFNameSpan getReturnMethod()
    {
        return (JDFNameSpan) getElement(ElementName.RETURNMETHOD, null, 0);
    }

    /** (25) getCreateReturnMethod
     * 
     * @return JDFNameSpan the element
     */
    public JDFNameSpan getCreateReturnMethod()
    {
        return (JDFNameSpan) getCreateElement_KElement(ElementName.RETURNMETHOD, null, 0);
    }

    /**
     * (29) append element ReturnMethod
     */
    public JDFNameSpan appendReturnMethod() throws JDFException
    {
        return (JDFNameSpan) appendElementN(ElementName.RETURNMETHOD, 1, null);
    }

    /**
     * (24) const get element ServiceLevel
     * @return JDFStringSpan the element
     */
    public JDFStringSpan getServiceLevel()
    {
        return (JDFStringSpan) getElement(ElementName.SERVICELEVEL, null, 0);
    }

    /** (25) getCreateServiceLevel
     * 
     * @return JDFStringSpan the element
     */
    public JDFStringSpan getCreateServiceLevel()
    {
        return (JDFStringSpan) getCreateElement_KElement(ElementName.SERVICELEVEL, null, 0);
    }

    /**
     * (29) append element ServiceLevel
     */
    public JDFStringSpan appendServiceLevel() throws JDFException
    {
        return (JDFStringSpan) appendElementN(ElementName.SERVICELEVEL, 1, null);
    }

    /**
     * (24) const get element SurplusHandling
     * @return JDFSpanSurplusHandling the element
     */
    public JDFSpanSurplusHandling getSurplusHandling()
    {
        return (JDFSpanSurplusHandling) getElement(ElementName.SURPLUSHANDLING, null, 0);
    }

    /** (25) getCreateSurplusHandling
     * 
     * @return JDFSpanSurplusHandling the element
     */
    public JDFSpanSurplusHandling getCreateSurplusHandling()
    {
        return (JDFSpanSurplusHandling) getCreateElement_KElement(ElementName.SURPLUSHANDLING, null, 0);
    }

    /**
     * (29) append element SurplusHandling
     */
    public JDFSpanSurplusHandling appendSurplusHandling() throws JDFException
    {
        return (JDFSpanSurplusHandling) appendElementN(ElementName.SURPLUSHANDLING, 1, null);
    }

    /**
     * (24) const get element Transfer
     * @return JDFSpanTransfer the element
     */
    public JDFSpanTransfer getTransfer()
    {
        return (JDFSpanTransfer) getElement(ElementName.TRANSFER, null, 0);
    }

    /** (25) getCreateTransfer
     * 
     * @return JDFSpanTransfer the element
     */
    public JDFSpanTransfer getCreateTransfer()
    {
        return (JDFSpanTransfer) getCreateElement_KElement(ElementName.TRANSFER, null, 0);
    }

    /**
     * (29) append element Transfer
     */
    public JDFSpanTransfer appendTransfer() throws JDFException
    {
        return (JDFSpanTransfer) appendElementN(ElementName.TRANSFER, 1, null);
    }

    /**
     * (24) const get element Underage
     * @return JDFNumberSpan the element
     */
    public JDFNumberSpan getUnderage()
    {
        return (JDFNumberSpan) getElement(ElementName.UNDERAGE, null, 0);
    }

    /** (25) getCreateUnderage
     * 
     * @return JDFNumberSpan the element
     */
    public JDFNumberSpan getCreateUnderage()
    {
        return (JDFNumberSpan) getCreateElement_KElement(ElementName.UNDERAGE, null, 0);
    }

    /**
     * (29) append element Underage
     */
    public JDFNumberSpan appendUnderage() throws JDFException
    {
        return (JDFNumberSpan) appendElementN(ElementName.UNDERAGE, 1, null);
    }

    /**
     * (24) const get element Company
     * @return JDFCompany the element
     */
    public JDFCompany getCompany()
    {
        return (JDFCompany) getElement(ElementName.COMPANY, null, 0);
    }

    /** (25) getCreateCompany
     * 
     * @return JDFCompany the element
     */
    public JDFCompany getCreateCompany()
    {
        return (JDFCompany) getCreateElement_KElement(ElementName.COMPANY, null, 0);
    }

    /**
     * (29) append element Company
     */
    public JDFCompany appendCompany() throws JDFException
    {
        return (JDFCompany) appendElementN(ElementName.COMPANY, 1, null);
    }

    /**
      * (31) create inter-resource link to refTarget
      * @param refTarget the element that is referenced
      */
    public void refCompany(JDFCompany refTarget)
    {
        refElement(refTarget);
    }

    /** (26) getCreateContact
     * 
     * @param iSkip number of elements to skip
     * @return JDFContact the element
     */
    public JDFContact getCreateContact(int iSkip)
    {
        return (JDFContact)getCreateElement_KElement(ElementName.CONTACT, null, iSkip);
    }

    /**
     * (27) const get element Contact
     * @param iSkip number of elements to skip
     * @return JDFContact the element
     * default is getContact(0)     */
    public JDFContact getContact(int iSkip)
    {
        return (JDFContact) getElement(ElementName.CONTACT, null, iSkip);
    }

    /**
     * (30) append element Contact
     */
    public JDFContact appendContact() throws JDFException
    {
        return (JDFContact) appendElement(ElementName.CONTACT, null);
    }

    /**
      * (31) create inter-resource link to refTarget
      * @param refTarget the element that is referenced
      */
    public void refContact(JDFContact refTarget)
    {
        refElement(refTarget);
    }

    /** (26) getCreateDropIntent
     * 
     * @param iSkip number of elements to skip
     * @return JDFDropIntent the element
     */
    public JDFDropIntent getCreateDropIntent(int iSkip)
    {
        return (JDFDropIntent)getCreateElement_KElement(ElementName.DROPINTENT, null, iSkip);
    }

    /**
     * (27) const get element DropIntent
     * @param iSkip number of elements to skip
     * @return JDFDropIntent the element
     * default is getDropIntent(0)     */
    public JDFDropIntent getDropIntent(int iSkip)
    {
        return (JDFDropIntent) getElement(ElementName.DROPINTENT, null, iSkip);
    }

    /**
     * (30) append element DropIntent
     */
    public JDFDropIntent appendDropIntent() throws JDFException
    {
        return (JDFDropIntent) appendElement(ElementName.DROPINTENT, null);
    }

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

}// end namespace JDF
