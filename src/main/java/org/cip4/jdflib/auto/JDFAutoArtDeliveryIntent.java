/*
 * The CIP4 Software License, Version 1.0
 *
 *
 * Copyright (c) 2001-2025 The International Cooperation for the Integration of
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

import org.apache.commons.lang.enums.ValuedEnum;
import org.apache.xerces.dom.CoreDocumentImpl;
import org.cip4.jdflib.core.AtrInfoTable;
import org.cip4.jdflib.core.AttributeInfo;
import org.cip4.jdflib.core.AttributeName;
import org.cip4.jdflib.core.ElemInfoTable;
import org.cip4.jdflib.core.ElementInfo;
import org.cip4.jdflib.core.ElementName;
import org.cip4.jdflib.core.VString;
import org.cip4.jdflib.resource.intent.JDFArtDelivery;
import org.cip4.jdflib.resource.intent.JDFIntentResource;
import org.cip4.jdflib.resource.process.JDFCompany;
import org.cip4.jdflib.resource.process.JDFContact;
import org.cip4.jdflib.span.JDFDurationSpan;
import org.cip4.jdflib.span.JDFNameSpan;
import org.cip4.jdflib.span.JDFSpanArtHandling;
import org.cip4.jdflib.span.JDFSpanDeliveryCharge;
import org.cip4.jdflib.span.JDFSpanTransfer;
import org.cip4.jdflib.span.JDFStringSpan;
import org.cip4.jdflib.span.JDFTimeSpan;
import org.cip4.jdflib.util.JavaEnumUtil;

/**
 *****************************************************************************
 * class JDFAutoArtDeliveryIntent : public JDFIntentResource
 *****************************************************************************
 * 
 */

public abstract class JDFAutoArtDeliveryIntent extends JDFIntentResource
{

	private static final long serialVersionUID = 1L;

	private static AtrInfoTable[] atrInfoTable = new AtrInfoTable[2];
	static
	{
		atrInfoTable[0] = new AtrInfoTable(AttributeName.PREFLIGHTSTATUS, 0x3333333331l, AttributeInfo.EnumAttributeType.enumeration, EnumPreflightStatus.getEnum(0),
				"NotPerformed");
		atrInfoTable[1] = new AtrInfoTable(AttributeName.RETURNLIST, 0x3333333331l, AttributeInfo.EnumAttributeType.NMTOKENS, null, "None");
	}

	@Override
	protected AttributeInfo getTheAttributeInfo()
	{
		return super.getTheAttributeInfo().updateReplace(atrInfoTable);
	}

	private static ElemInfoTable[] elemInfoTable = new ElemInfoTable[11];
	static
	{
		elemInfoTable[0] = new ElemInfoTable(ElementName.ARTDELIVERYDATE, 0x6666666661l);
		elemInfoTable[1] = new ElemInfoTable(ElementName.ARTDELIVERYDURATION, 0x6666666661l);
		elemInfoTable[2] = new ElemInfoTable(ElementName.ARTHANDLING, 0x6666666661l);
		elemInfoTable[3] = new ElemInfoTable(ElementName.DELIVERYCHARGE, 0x6666666661l);
		elemInfoTable[4] = new ElemInfoTable(ElementName.METHOD, 0x6666666666l);
		elemInfoTable[5] = new ElemInfoTable(ElementName.RETURNMETHOD, 0x6666666661l);
		elemInfoTable[6] = new ElemInfoTable(ElementName.SERVICELEVEL, 0x6666666611l);
		elemInfoTable[7] = new ElemInfoTable(ElementName.TRANSFER, 0x6666666661l);
		elemInfoTable[8] = new ElemInfoTable(ElementName.ARTDELIVERY, 0x2222222222l);
		elemInfoTable[9] = new ElemInfoTable(ElementName.COMPANY, 0x7777777776l);
		elemInfoTable[10] = new ElemInfoTable(ElementName.CONTACT, 0x3333333331l);
	}

	@Override
	protected ElementInfo getTheElementInfo()
	{
		return super.getTheElementInfo().updateReplace(elemInfoTable);
	}

	/**
	 * Constructor for JDFAutoArtDeliveryIntent
	 *
	 * @param myOwnerDocument
	 * @param qualifiedName
	 */
	protected JDFAutoArtDeliveryIntent(CoreDocumentImpl myOwnerDocument, String qualifiedName)
	{
		super(myOwnerDocument, qualifiedName);
	}

	/**
	 * Constructor for JDFAutoArtDeliveryIntent
	 *
	 * @param myOwnerDocument
	 * @param myNamespaceURI
	 * @param qualifiedName
	 */
	protected JDFAutoArtDeliveryIntent(CoreDocumentImpl myOwnerDocument, String myNamespaceURI, String qualifiedName)
	{
		super(myOwnerDocument, myNamespaceURI, qualifiedName);
	}

	/**
	 * Constructor for JDFAutoArtDeliveryIntent
	 *
	 * @param myOwnerDocument
	 * @param myNamespaceURI
	 * @param qualifiedName
	 * @param myLocalName
	 */
	protected JDFAutoArtDeliveryIntent(CoreDocumentImpl myOwnerDocument, String myNamespaceURI, String qualifiedName, String myLocalName)
	{
		super(myOwnerDocument, myNamespaceURI, qualifiedName, myLocalName);
	}

	/**
	 * Enumeration strings for PreflightStatus
	 */

	public enum EPreflightStatus
	{
		NotPerformed, WithErrors, WithWarnings, WithoutErrors;

		public static EPreflightStatus getEnum(String val)
		{
			return JavaEnumUtil.getEnumIgnoreCase(EPreflightStatus.class, val, EPreflightStatus.NotPerformed);
		}
	}

	/**
	 * Enumeration strings for PreflightStatus
	 */

	@SuppressWarnings("rawtypes")
	public static class EnumPreflightStatus extends ValuedEnum
	{
		private static final long serialVersionUID = 1L;
		private static int m_startValue = 0;

		protected EnumPreflightStatus(String name)
		{
			super(name, m_startValue++);
		}

		/**
		 * @param enumName the string to convert
		 * @return the enum
		 */
		public static EnumPreflightStatus getEnum(String enumName)
		{
			return (EnumPreflightStatus) getEnum(EnumPreflightStatus.class, enumName);
		}

		/**
		 * @param enumValue the integer to convert
		 * @return the enum
		 */
		public static EnumPreflightStatus getEnum(int enumValue)
		{
			return (EnumPreflightStatus) getEnum(EnumPreflightStatus.class, enumValue);
		}

		/**
		 * @return the map of enums
		 */
		public static Map getEnumMap()
		{
			return getEnumMap(EnumPreflightStatus.class);
		}

		/**
		 * @return the list of enums
		 */
		public static List getEnumList()
		{
			return getEnumList(EnumPreflightStatus.class);
		}

		/**
		 * @return the iterator
		 */
		public static Iterator iterator()
		{
			return iterator(EnumPreflightStatus.class);
		}

		/**  */
		public static final EnumPreflightStatus NotPerformed = new EnumPreflightStatus("NotPerformed");
		/**  */
		public static final EnumPreflightStatus WithErrors = new EnumPreflightStatus("WithErrors");
		/**  */
		public static final EnumPreflightStatus WithWarnings = new EnumPreflightStatus("WithWarnings");
		/**  */
		public static final EnumPreflightStatus WithoutErrors = new EnumPreflightStatus("WithoutErrors");
	}

	/*
	 * ************************************************************************ Attribute getter / setter ************************************************************************
	 */

	/*
	 * --------------------------------------------------------------------- Methods for Attribute PreflightStatus
	 * ---------------------------------------------------------------------
	 */
	/**
	 * (5) set attribute PreflightStatus
	 *
	 * @param enumVar the enumVar to set the attribute to
	 */
	public void setPreflightStatus(EPreflightStatus enumVar)
	{
		setAttribute(AttributeName.PREFLIGHTSTATUS, enumVar == null ? null : enumVar.name(), null);
	}

	/**
	 * (9) get attribute PreflightStatus
	 *
	 * @return the value of the attribute
	 */
	public EPreflightStatus getEPreflightStatus()
	{
		return EPreflightStatus.getEnum(getAttribute(AttributeName.PREFLIGHTSTATUS, null, "NotPerformed"));
	}

	/*
	 * --------------------------------------------------------------------- Methods for Attribute PreflightStatus
	 * ---------------------------------------------------------------------
	 */
	/**
	 * (5) set attribute PreflightStatus
	 *
	 * @param enumVar the enumVar to set the attribute to
	 * @deprecated use java.lang.enum
	 */
	@Deprecated
	public void setPreflightStatus(EnumPreflightStatus enumVar)
	{
		setAttribute(AttributeName.PREFLIGHTSTATUS, enumVar == null ? null : enumVar.getName(), null);
	}

	/**
	 * (9) get attribute PreflightStatus
	 *
	 * @return the value of the attribute
	 */
	public EnumPreflightStatus getPreflightStatus()
	{
		return EnumPreflightStatus.getEnum(getAttribute(AttributeName.PREFLIGHTSTATUS, null, "NotPerformed"));
	}

	/*
	 * --------------------------------------------------------------------- Methods for Attribute ReturnList ---------------------------------------------------------------------
	 */
	/**
	 * (36) set attribute ReturnList
	 *
	 * @param value the value to set the attribute to
	 */
	public void setReturnList(VString value)
	{
		setAttribute(AttributeName.RETURNLIST, value, null);
	}

	/**
	 * (21) get VString attribute ReturnList
	 *
	 * @return VString the value of the attribute
	 */
	public VString getReturnList()
	{
		final VString vStrAttrib = new VString();
		final String s = getAttribute(AttributeName.RETURNLIST, null, "None");
		vStrAttrib.setAllStrings(s, " ");
		return vStrAttrib;
	}

	/*
	 * *********************************************************************** Element getter / setter ***********************************************************************
	 */

	/**
	 * (24) const get element ArtDeliveryDate
	 *
	 * @return JDFTimeSpan the element
	 */
	public JDFTimeSpan getArtDeliveryDate()
	{
		return (JDFTimeSpan) getElement(ElementName.ARTDELIVERYDATE, null, 0);
	}

	/**
	 * (25) getCreateArtDeliveryDate
	 * 
	 * @return JDFTimeSpan the element
	 */
	public JDFTimeSpan getCreateArtDeliveryDate()
	{
		return (JDFTimeSpan) getCreateElement_JDFElement(ElementName.ARTDELIVERYDATE, null, 0);
	}

	/**
	 * (29) append element ArtDeliveryDate
	 *
	 * @return JDFTimeSpan the element @ if the element already exists
	 */
	public JDFTimeSpan appendArtDeliveryDate()
	{
		return (JDFTimeSpan) appendElementN(ElementName.ARTDELIVERYDATE, 1, null);
	}

	/**
	 * (24) const get element ArtDeliveryDuration
	 *
	 * @return JDFDurationSpan the element
	 */
	public JDFDurationSpan getArtDeliveryDuration()
	{
		return (JDFDurationSpan) getElement(ElementName.ARTDELIVERYDURATION, null, 0);
	}

	/**
	 * (25) getCreateArtDeliveryDuration
	 * 
	 * @return JDFDurationSpan the element
	 */
	public JDFDurationSpan getCreateArtDeliveryDuration()
	{
		return (JDFDurationSpan) getCreateElement_JDFElement(ElementName.ARTDELIVERYDURATION, null, 0);
	}

	/**
	 * (29) append element ArtDeliveryDuration
	 *
	 * @return JDFDurationSpan the element @ if the element already exists
	 */
	public JDFDurationSpan appendArtDeliveryDuration()
	{
		return (JDFDurationSpan) appendElementN(ElementName.ARTDELIVERYDURATION, 1, null);
	}

	/**
	 * (24) const get element ArtHandling
	 *
	 * @return JDFSpanArtHandling the element
	 */
	public JDFSpanArtHandling getArtHandling()
	{
		return (JDFSpanArtHandling) getElement(ElementName.ARTHANDLING, null, 0);
	}

	/**
	 * (25) getCreateArtHandling
	 * 
	 * @return JDFSpanArtHandling the element
	 */
	public JDFSpanArtHandling getCreateArtHandling()
	{
		return (JDFSpanArtHandling) getCreateElement_JDFElement(ElementName.ARTHANDLING, null, 0);
	}

	/**
	 * (29) append element ArtHandling
	 *
	 * @return JDFSpanArtHandling the element @ if the element already exists
	 */
	public JDFSpanArtHandling appendArtHandling()
	{
		return (JDFSpanArtHandling) appendElementN(ElementName.ARTHANDLING, 1, null);
	}

	/**
	 * (24) const get element DeliveryCharge
	 *
	 * @return JDFSpanDeliveryCharge the element
	 */
	public JDFSpanDeliveryCharge getDeliveryCharge()
	{
		return (JDFSpanDeliveryCharge) getElement(ElementName.DELIVERYCHARGE, null, 0);
	}

	/**
	 * (25) getCreateDeliveryCharge
	 * 
	 * @return JDFSpanDeliveryCharge the element
	 */
	public JDFSpanDeliveryCharge getCreateDeliveryCharge()
	{
		return (JDFSpanDeliveryCharge) getCreateElement_JDFElement(ElementName.DELIVERYCHARGE, null, 0);
	}

	/**
	 * (29) append element DeliveryCharge
	 *
	 * @return JDFSpanDeliveryCharge the element @ if the element already exists
	 */
	public JDFSpanDeliveryCharge appendDeliveryCharge()
	{
		return (JDFSpanDeliveryCharge) appendElementN(ElementName.DELIVERYCHARGE, 1, null);
	}

	/**
	 * (24) const get element Method
	 *
	 * @return JDFNameSpan the element
	 */
	public JDFNameSpan getMethod()
	{
		return (JDFNameSpan) getElement(ElementName.METHOD, null, 0);
	}

	/**
	 * (25) getCreateMethod
	 * 
	 * @return JDFNameSpan the element
	 */
	public JDFNameSpan getCreateMethod()
	{
		return (JDFNameSpan) getCreateElement_JDFElement(ElementName.METHOD, null, 0);
	}

	/**
	 * (29) append element Method
	 *
	 * @return JDFNameSpan the element @ if the element already exists
	 */
	public JDFNameSpan appendMethod()
	{
		return (JDFNameSpan) appendElementN(ElementName.METHOD, 1, null);
	}

	/**
	 * (24) const get element ReturnMethod
	 *
	 * @return JDFNameSpan the element
	 */
	public JDFNameSpan getReturnMethod()
	{
		return (JDFNameSpan) getElement(ElementName.RETURNMETHOD, null, 0);
	}

	/**
	 * (25) getCreateReturnMethod
	 * 
	 * @return JDFNameSpan the element
	 */
	public JDFNameSpan getCreateReturnMethod()
	{
		return (JDFNameSpan) getCreateElement_JDFElement(ElementName.RETURNMETHOD, null, 0);
	}

	/**
	 * (29) append element ReturnMethod
	 *
	 * @return JDFNameSpan the element @ if the element already exists
	 */
	public JDFNameSpan appendReturnMethod()
	{
		return (JDFNameSpan) appendElementN(ElementName.RETURNMETHOD, 1, null);
	}

	/**
	 * (24) const get element ServiceLevel
	 *
	 * @return JDFStringSpan the element
	 */
	public JDFStringSpan getServiceLevel()
	{
		return (JDFStringSpan) getElement(ElementName.SERVICELEVEL, null, 0);
	}

	/**
	 * (25) getCreateServiceLevel
	 * 
	 * @return JDFStringSpan the element
	 */
	public JDFStringSpan getCreateServiceLevel()
	{
		return (JDFStringSpan) getCreateElement_JDFElement(ElementName.SERVICELEVEL, null, 0);
	}

	/**
	 * (29) append element ServiceLevel
	 *
	 * @return JDFStringSpan the element @ if the element already exists
	 */
	public JDFStringSpan appendServiceLevel()
	{
		return (JDFStringSpan) appendElementN(ElementName.SERVICELEVEL, 1, null);
	}

	/**
	 * (24) const get element Transfer
	 *
	 * @return JDFSpanTransfer the element
	 */
	public JDFSpanTransfer getTransfer()
	{
		return (JDFSpanTransfer) getElement(ElementName.TRANSFER, null, 0);
	}

	/**
	 * (25) getCreateTransfer
	 * 
	 * @return JDFSpanTransfer the element
	 */
	public JDFSpanTransfer getCreateTransfer()
	{
		return (JDFSpanTransfer) getCreateElement_JDFElement(ElementName.TRANSFER, null, 0);
	}

	/**
	 * (29) append element Transfer
	 *
	 * @return JDFSpanTransfer the element @ if the element already exists
	 */
	public JDFSpanTransfer appendTransfer()
	{
		return (JDFSpanTransfer) appendElementN(ElementName.TRANSFER, 1, null);
	}

	/**
	 * (24) const get element ArtDelivery
	 *
	 * @return JDFArtDelivery the element
	 */
	public JDFArtDelivery getArtDelivery()
	{
		return (JDFArtDelivery) getElement(ElementName.ARTDELIVERY, null, 0);
	}

	/**
	 * (25) getCreateArtDelivery
	 * 
	 * @return JDFArtDelivery the element
	 */
	public JDFArtDelivery getCreateArtDelivery()
	{
		return (JDFArtDelivery) getCreateElement_JDFElement(ElementName.ARTDELIVERY, null, 0);
	}

	/**
	 * (26) getCreateArtDelivery
	 * 
	 * @param iSkip number of elements to skip
	 * @return JDFArtDelivery the element
	 */
	public JDFArtDelivery getCreateArtDelivery(int iSkip)
	{
		return (JDFArtDelivery) getCreateElement_JDFElement(ElementName.ARTDELIVERY, null, iSkip);
	}

	/**
	 * (27) const get element ArtDelivery
	 *
	 * @param iSkip number of elements to skip
	 * @return JDFArtDelivery the element default is getArtDelivery(0)
	 */
	public JDFArtDelivery getArtDelivery(int iSkip)
	{
		return (JDFArtDelivery) getElement(ElementName.ARTDELIVERY, null, iSkip);
	}

	/**
	 * Get all ArtDelivery from the current element
	 * 
	 * @return Collection<JDFArtDelivery>, null if none are available
	 */
	public Collection<JDFArtDelivery> getAllArtDelivery()
	{
		return getChildArrayByClass(JDFArtDelivery.class, false, 0);
	}

	/**
	 * (30) append element ArtDelivery
	 *
	 * @return JDFArtDelivery the element
	 */
	public JDFArtDelivery appendArtDelivery()
	{
		return (JDFArtDelivery) appendElement(ElementName.ARTDELIVERY, null);
	}

	/**
	 * (24) const get element Company
	 *
	 * @return JDFCompany the element
	 */
	public JDFCompany getCompany()
	{
		return (JDFCompany) getElement(ElementName.COMPANY, null, 0);
	}

	/**
	 * (25) getCreateCompany
	 * 
	 * @return JDFCompany the element
	 */
	public JDFCompany getCreateCompany()
	{
		return (JDFCompany) getCreateElement_JDFElement(ElementName.COMPANY, null, 0);
	}

	/**
	 * (29) append element Company
	 *
	 * @return JDFCompany the element @ if the element already exists
	 */
	public JDFCompany appendCompany()
	{
		return (JDFCompany) appendElementN(ElementName.COMPANY, 1, null);
	}

	/**
	 * (31) create inter-resource link to refTarget
	 *
	 * @param refTarget the element that is referenced
	 */
	public void refCompany(JDFCompany refTarget)
	{
		refElement(refTarget);
	}

	/**
	 * (24) const get element Contact
	 *
	 * @return JDFContact the element
	 */
	@Override
	public JDFContact getContact()
	{
		return (JDFContact) getElement(ElementName.CONTACT, null, 0);
	}

	/**
	 * (25) getCreateContact
	 * 
	 * @return JDFContact the element
	 */
	@Override
	public JDFContact getCreateContact()
	{
		return (JDFContact) getCreateElement_JDFElement(ElementName.CONTACT, null, 0);
	}

	/**
	 * (26) getCreateContact
	 * 
	 * @param iSkip number of elements to skip
	 * @return JDFContact the element
	 */
	public JDFContact getCreateContact(int iSkip)
	{
		return (JDFContact) getCreateElement_JDFElement(ElementName.CONTACT, null, iSkip);
	}

	/**
	 * (27) const get element Contact
	 *
	 * @param iSkip number of elements to skip
	 * @return JDFContact the element default is getContact(0)
	 */
	public JDFContact getContact(int iSkip)
	{
		return (JDFContact) getElement(ElementName.CONTACT, null, iSkip);
	}

	/**
	 * Get all Contact from the current element
	 * 
	 * @return Collection<JDFContact>, null if none are available
	 */
	public Collection<JDFContact> getAllContact()
	{
		return getChildArrayByClass(JDFContact.class, false, 0);
	}

	/**
	 * (30) append element Contact
	 *
	 * @return JDFContact the element
	 */
	@Override
	public JDFContact appendContact()
	{
		return (JDFContact) appendElement(ElementName.CONTACT, null);
	}

	/**
	 * (31) create inter-resource link to refTarget
	 *
	 * @param refTarget the element that is referenced
	 */
	public void refContact(JDFContact refTarget)
	{
		refElement(refTarget);
	}

}
