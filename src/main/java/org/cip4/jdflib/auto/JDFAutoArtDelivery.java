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
import org.cip4.jdflib.core.JDFCoreConstants;
import org.cip4.jdflib.core.JDFElement;
import org.cip4.jdflib.datatypes.JDFIntegerRangeList;
import org.cip4.jdflib.resource.JDFTool;
import org.cip4.jdflib.resource.process.JDFCompany;
import org.cip4.jdflib.resource.process.JDFComponent;
import org.cip4.jdflib.resource.process.JDFContact;
import org.cip4.jdflib.resource.process.JDFDigitalMedia;
import org.cip4.jdflib.resource.process.JDFExposedMedia;
import org.cip4.jdflib.resource.process.JDFRunList;
import org.cip4.jdflib.resource.process.prepress.JDFScanParams;
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
 * class JDFAutoArtDelivery : public JDFElement
 *****************************************************************************
 * 
 */

public abstract class JDFAutoArtDelivery extends JDFElement
{

	private static final long serialVersionUID = 1L;

	private static AtrInfoTable[] atrInfoTable = new AtrInfoTable[7];
	static
	{
		atrInfoTable[0] = new AtrInfoTable(AttributeName.AMOUNT, 0x3333333333l, AttributeInfo.EnumAttributeType.integer, null, null);
		atrInfoTable[1] = new AtrInfoTable(AttributeName.ARTDELIVERYTYPE, 0x2222222221l, AttributeInfo.EnumAttributeType.NMTOKEN, null, null);
		atrInfoTable[2] = new AtrInfoTable(AttributeName.HASBLEEDS, 0x3333333333l, AttributeInfo.EnumAttributeType.boolean_, null, "false");
		atrInfoTable[3] = new AtrInfoTable(AttributeName.ISTRAPPED, 0x3333333333l, AttributeInfo.EnumAttributeType.boolean_, null, "false");
		atrInfoTable[4] = new AtrInfoTable(AttributeName.PAGELIST, 0x3333333333l, AttributeInfo.EnumAttributeType.IntegerRangeList, null, null);
		atrInfoTable[5] = new AtrInfoTable(AttributeName.PREFLIGHTOUTPUT, 0x3333333331l, AttributeInfo.EnumAttributeType.URL, null, null);
		atrInfoTable[6] = new AtrInfoTable(AttributeName.PREFLIGHTSTATUS, 0x3333333331l, AttributeInfo.EnumAttributeType.enumeration, EnumPreflightStatus.getEnum(0), null);
	}

	@Override
	protected AttributeInfo getTheAttributeInfo()
	{
		return super.getTheAttributeInfo().updateReplace(atrInfoTable);
	}

	private static ElemInfoTable[] elemInfoTable = new ElemInfoTable[16];
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
		elemInfoTable[8] = new ElemInfoTable(ElementName.COMPANY, 0x7777777776l);
		elemInfoTable[9] = new ElemInfoTable(ElementName.COMPONENT, 0x7777777776l);
		elemInfoTable[10] = new ElemInfoTable(ElementName.CONTACT, 0x3333333331l);
		elemInfoTable[11] = new ElemInfoTable(ElementName.DIGITALMEDIA, 0x6666666611l);
		elemInfoTable[12] = new ElemInfoTable(ElementName.EXPOSEDMEDIA, 0x6666666666l);
		elemInfoTable[13] = new ElemInfoTable(ElementName.RUNLIST, 0x6666666666l);
		elemInfoTable[14] = new ElemInfoTable(ElementName.SCANPARAMS, 0x6666666666l);
		elemInfoTable[15] = new ElemInfoTable(ElementName.TOOL, 0x6666666661l);
	}

	@Override
	protected ElementInfo getTheElementInfo()
	{
		return super.getTheElementInfo().updateReplace(elemInfoTable);
	}

	/**
	 * Constructor for JDFAutoArtDelivery
	 *
	 * @param myOwnerDocument
	 * @param qualifiedName
	 */
	protected JDFAutoArtDelivery(CoreDocumentImpl myOwnerDocument, String qualifiedName)
	{
		super(myOwnerDocument, qualifiedName);
	}

	/**
	 * Constructor for JDFAutoArtDelivery
	 *
	 * @param myOwnerDocument
	 * @param myNamespaceURI
	 * @param qualifiedName
	 */
	protected JDFAutoArtDelivery(CoreDocumentImpl myOwnerDocument, String myNamespaceURI, String qualifiedName)
	{
		super(myOwnerDocument, myNamespaceURI, qualifiedName);
	}

	/**
	 * Constructor for JDFAutoArtDelivery
	 *
	 * @param myOwnerDocument
	 * @param myNamespaceURI
	 * @param qualifiedName
	 * @param myLocalName
	 */
	protected JDFAutoArtDelivery(CoreDocumentImpl myOwnerDocument, String myNamespaceURI, String qualifiedName, String myLocalName)
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
			return JavaEnumUtil.getEnumIgnoreCase(EPreflightStatus.class, val, null);
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
	 * --------------------------------------------------------------------- Methods for Attribute Amount ---------------------------------------------------------------------
	 */
	/**
	 * (36) set attribute Amount
	 *
	 * @param value the value to set the attribute to
	 */
	public void setAmount(int value)
	{
		setAttribute(AttributeName.AMOUNT, value, null);
	}

	/**
	 * (15) get int attribute Amount
	 *
	 * @return int the value of the attribute
	 */
	public int getAmount()
	{
		return getIntAttribute(AttributeName.AMOUNT, null, 0);
	}

	/*
	 * --------------------------------------------------------------------- Methods for Attribute ArtDeliveryType
	 * ---------------------------------------------------------------------
	 */
	/**
	 * (36) set attribute ArtDeliveryType
	 *
	 * @param value the value to set the attribute to
	 */
	public void setArtDeliveryType(String value)
	{
		setAttribute(AttributeName.ARTDELIVERYTYPE, value, null);
	}

	/**
	 * (23) get String attribute ArtDeliveryType
	 *
	 * @return the value of the attribute
	 */
	public String getArtDeliveryType()
	{
		return getAttribute(AttributeName.ARTDELIVERYTYPE, null, JDFCoreConstants.EMPTYSTRING);
	}

	/*
	 * --------------------------------------------------------------------- Methods for Attribute HasBleeds ---------------------------------------------------------------------
	 */
	/**
	 * (36) set attribute HasBleeds
	 *
	 * @param value the value to set the attribute to
	 */
	public void setHasBleeds(boolean value)
	{
		setAttribute(AttributeName.HASBLEEDS, value, null);
	}

	/**
	 * (18) get boolean attribute HasBleeds
	 *
	 * @return boolean the value of the attribute
	 */
	public boolean getHasBleeds()
	{
		return getBoolAttribute(AttributeName.HASBLEEDS, null, false);
	}

	/*
	 * --------------------------------------------------------------------- Methods for Attribute IsTrapped ---------------------------------------------------------------------
	 */
	/**
	 * (36) set attribute IsTrapped
	 *
	 * @param value the value to set the attribute to
	 */
	public void setIsTrapped(boolean value)
	{
		setAttribute(AttributeName.ISTRAPPED, value, null);
	}

	/**
	 * (18) get boolean attribute IsTrapped
	 *
	 * @return boolean the value of the attribute
	 */
	public boolean getIsTrapped()
	{
		return getBoolAttribute(AttributeName.ISTRAPPED, null, false);
	}

	/*
	 * --------------------------------------------------------------------- Methods for Attribute PageList ---------------------------------------------------------------------
	 */
	/**
	 * (36) set attribute PageList
	 *
	 * @param value the value to set the attribute to
	 */
	public void setPageList(JDFIntegerRangeList value)
	{
		setAttribute(AttributeName.PAGELIST, value, null);
	}

	/**
	 * (20) get JDFIntegerRangeList attribute PageList
	 *
	 * @return JDFIntegerRangeList the value of the attribute, null if a the attribute value is not a valid to create a JDFIntegerRangeList
	 */
	public JDFIntegerRangeList getPageList()
	{
		final String strAttrName = getAttribute(AttributeName.PAGELIST, null, null);
		final JDFIntegerRangeList nPlaceHolder = JDFIntegerRangeList.createIntegerRangeList(strAttrName);
		return nPlaceHolder;
	}

	/*
	 * --------------------------------------------------------------------- Methods for Attribute PreflightOutput
	 * ---------------------------------------------------------------------
	 */
	/**
	 * (36) set attribute PreflightOutput
	 *
	 * @param value the value to set the attribute to
	 */
	public void setPreflightOutput(String value)
	{
		setAttribute(AttributeName.PREFLIGHTOUTPUT, value, null);
	}

	/**
	 * (23) get String attribute PreflightOutput
	 *
	 * @return the value of the attribute
	 */
	public String getPreflightOutput()
	{
		return getAttribute(AttributeName.PREFLIGHTOUTPUT, null, JDFCoreConstants.EMPTYSTRING);
	}

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
		return EPreflightStatus.getEnum(getAttribute(AttributeName.PREFLIGHTSTATUS, null, null));
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
		return EnumPreflightStatus.getEnum(getAttribute(AttributeName.PREFLIGHTSTATUS, null, null));
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
	 * (24) const get element Component
	 *
	 * @return JDFComponent the element
	 */
	public JDFComponent getComponent()
	{
		return (JDFComponent) getElement(ElementName.COMPONENT, null, 0);
	}

	/**
	 * (25) getCreateComponent
	 * 
	 * @return JDFComponent the element
	 */
	public JDFComponent getCreateComponent()
	{
		return (JDFComponent) getCreateElement_JDFElement(ElementName.COMPONENT, null, 0);
	}

	/**
	 * (29) append element Component
	 *
	 * @return JDFComponent the element @ if the element already exists
	 */
	public JDFComponent appendComponent()
	{
		return (JDFComponent) appendElementN(ElementName.COMPONENT, 1, null);
	}

	/**
	 * (31) create inter-resource link to refTarget
	 *
	 * @param refTarget the element that is referenced
	 */
	public void refComponent(JDFComponent refTarget)
	{
		refElement(refTarget);
	}

	/**
	 * (24) const get element Contact
	 *
	 * @return JDFContact the element
	 */
	public JDFContact getContact()
	{
		return (JDFContact) getElement(ElementName.CONTACT, null, 0);
	}

	/**
	 * (25) getCreateContact
	 * 
	 * @return JDFContact the element
	 */
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

	/**
	 * (24) const get element DigitalMedia
	 *
	 * @return JDFDigitalMedia the element
	 */
	public JDFDigitalMedia getDigitalMedia()
	{
		return (JDFDigitalMedia) getElement(ElementName.DIGITALMEDIA, null, 0);
	}

	/**
	 * (25) getCreateDigitalMedia
	 * 
	 * @return JDFDigitalMedia the element
	 */
	public JDFDigitalMedia getCreateDigitalMedia()
	{
		return (JDFDigitalMedia) getCreateElement_JDFElement(ElementName.DIGITALMEDIA, null, 0);
	}

	/**
	 * (29) append element DigitalMedia
	 *
	 * @return JDFDigitalMedia the element @ if the element already exists
	 */
	public JDFDigitalMedia appendDigitalMedia()
	{
		return (JDFDigitalMedia) appendElementN(ElementName.DIGITALMEDIA, 1, null);
	}

	/**
	 * (31) create inter-resource link to refTarget
	 *
	 * @param refTarget the element that is referenced
	 */
	public void refDigitalMedia(JDFDigitalMedia refTarget)
	{
		refElement(refTarget);
	}

	/**
	 * (24) const get element ExposedMedia
	 *
	 * @return JDFExposedMedia the element
	 */
	public JDFExposedMedia getExposedMedia()
	{
		return (JDFExposedMedia) getElement(ElementName.EXPOSEDMEDIA, null, 0);
	}

	/**
	 * (25) getCreateExposedMedia
	 * 
	 * @return JDFExposedMedia the element
	 */
	public JDFExposedMedia getCreateExposedMedia()
	{
		return (JDFExposedMedia) getCreateElement_JDFElement(ElementName.EXPOSEDMEDIA, null, 0);
	}

	/**
	 * (29) append element ExposedMedia
	 *
	 * @return JDFExposedMedia the element @ if the element already exists
	 */
	public JDFExposedMedia appendExposedMedia()
	{
		return (JDFExposedMedia) appendElementN(ElementName.EXPOSEDMEDIA, 1, null);
	}

	/**
	 * (31) create inter-resource link to refTarget
	 *
	 * @param refTarget the element that is referenced
	 */
	public void refExposedMedia(JDFExposedMedia refTarget)
	{
		refElement(refTarget);
	}

	/**
	 * (24) const get element RunList
	 *
	 * @return JDFRunList the element
	 */
	public JDFRunList getRunList()
	{
		return (JDFRunList) getElement(ElementName.RUNLIST, null, 0);
	}

	/**
	 * (25) getCreateRunList
	 * 
	 * @return JDFRunList the element
	 */
	public JDFRunList getCreateRunList()
	{
		return (JDFRunList) getCreateElement_JDFElement(ElementName.RUNLIST, null, 0);
	}

	/**
	 * (29) append element RunList
	 *
	 * @return JDFRunList the element @ if the element already exists
	 */
	public JDFRunList appendRunList()
	{
		return (JDFRunList) appendElementN(ElementName.RUNLIST, 1, null);
	}

	/**
	 * (31) create inter-resource link to refTarget
	 *
	 * @param refTarget the element that is referenced
	 */
	public void refRunList(JDFRunList refTarget)
	{
		refElement(refTarget);
	}

	/**
	 * (24) const get element ScanParams
	 *
	 * @return JDFScanParams the element
	 */
	public JDFScanParams getScanParams()
	{
		return (JDFScanParams) getElement(ElementName.SCANPARAMS, null, 0);
	}

	/**
	 * (25) getCreateScanParams
	 * 
	 * @return JDFScanParams the element
	 */
	public JDFScanParams getCreateScanParams()
	{
		return (JDFScanParams) getCreateElement_JDFElement(ElementName.SCANPARAMS, null, 0);
	}

	/**
	 * (29) append element ScanParams
	 *
	 * @return JDFScanParams the element @ if the element already exists
	 */
	public JDFScanParams appendScanParams()
	{
		return (JDFScanParams) appendElementN(ElementName.SCANPARAMS, 1, null);
	}

	/**
	 * (31) create inter-resource link to refTarget
	 *
	 * @param refTarget the element that is referenced
	 */
	public void refScanParams(JDFScanParams refTarget)
	{
		refElement(refTarget);
	}

	/**
	 * (24) const get element Tool
	 *
	 * @return JDFTool the element
	 */
	public JDFTool getTool()
	{
		return (JDFTool) getElement(ElementName.TOOL, null, 0);
	}

	/**
	 * (25) getCreateTool
	 * 
	 * @return JDFTool the element
	 */
	public JDFTool getCreateTool()
	{
		return (JDFTool) getCreateElement_JDFElement(ElementName.TOOL, null, 0);
	}

	/**
	 * (29) append element Tool
	 *
	 * @return JDFTool the element @ if the element already exists
	 */
	public JDFTool appendTool()
	{
		return (JDFTool) appendElementN(ElementName.TOOL, 1, null);
	}

	/**
	 * (31) create inter-resource link to refTarget
	 *
	 * @param refTarget the element that is referenced
	 */
	public void refTool(JDFTool refTarget)
	{
		refElement(refTarget);
	}

}
