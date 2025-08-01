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
import org.cip4.jdflib.resource.JDFBindList;
import org.cip4.jdflib.resource.JDFEdgeGluing;
import org.cip4.jdflib.resource.JDFHardCoverBinding;
import org.cip4.jdflib.resource.JDFSoftCoverBinding;
import org.cip4.jdflib.resource.JDFStripBinding;
import org.cip4.jdflib.resource.JDFTabs;
import org.cip4.jdflib.resource.JDFTape;
import org.cip4.jdflib.resource.intent.JDFAdhesiveNote;
import org.cip4.jdflib.resource.intent.JDFBookCase;
import org.cip4.jdflib.resource.intent.JDFIntentResource;
import org.cip4.jdflib.resource.process.postpress.JDFAdhesiveBinding;
import org.cip4.jdflib.resource.process.postpress.JDFChannelBinding;
import org.cip4.jdflib.resource.process.postpress.JDFCoilBinding;
import org.cip4.jdflib.resource.process.postpress.JDFPlasticCombBinding;
import org.cip4.jdflib.resource.process.postpress.JDFRingBinding;
import org.cip4.jdflib.resource.process.postpress.JDFSaddleStitching;
import org.cip4.jdflib.resource.process.postpress.JDFSideSewing;
import org.cip4.jdflib.resource.process.postpress.JDFSideStitching;
import org.cip4.jdflib.resource.process.postpress.JDFThreadSealing;
import org.cip4.jdflib.resource.process.postpress.JDFThreadSewing;
import org.cip4.jdflib.resource.process.postpress.JDFWireCombBinding;
import org.cip4.jdflib.span.JDFSpanBindingLength;
import org.cip4.jdflib.span.JDFSpanBindingSide;
import org.cip4.jdflib.span.JDFSpanBindingType;
import org.cip4.jdflib.span.JDFSpanNamedColor;
import org.cip4.jdflib.span.JDFStringSpan;
import org.cip4.jdflib.util.JavaEnumUtil;

/**
 *****************************************************************************
 * class JDFAutoBindingIntent : public JDFIntentResource
 *****************************************************************************
 * 
 */

public abstract class JDFAutoBindingIntent extends JDFIntentResource
{

	private static final long serialVersionUID = 1L;

	private static AtrInfoTable[] atrInfoTable = new AtrInfoTable[1];
	static
	{
		atrInfoTable[0] = new AtrInfoTable(AttributeName.BINDINGORDER, 0x3333333331l, AttributeInfo.EnumAttributeType.enumeration, EnumBindingOrder.getEnum(0), "Gathering");
	}

	@Override
	protected AttributeInfo getTheAttributeInfo()
	{
		return super.getTheAttributeInfo().updateReplace(atrInfoTable);
	}

	private static ElemInfoTable[] elemInfoTable = new ElemInfoTable[29];
	static
	{
		elemInfoTable[0] = new ElemInfoTable(ElementName.BACKCOVERCOLOR, 0x6666666666l);
		elemInfoTable[1] = new ElemInfoTable(ElementName.BACKCOVERCOLORDETAILS, 0x3333333333l);
		elemInfoTable[2] = new ElemInfoTable(ElementName.BINDINGTYPE, 0x6666666666l);
		elemInfoTable[3] = new ElemInfoTable(ElementName.BINDINGCOLOR, 0x6666666666l);
		elemInfoTable[4] = new ElemInfoTable(ElementName.BINDINGCOLORDETAILS, 0x3333333333l);
		elemInfoTable[5] = new ElemInfoTable(ElementName.BINDINGLENGTH, 0x6666666666l);
		elemInfoTable[6] = new ElemInfoTable(ElementName.BINDINGSIDE, 0x6666666666l);
		elemInfoTable[7] = new ElemInfoTable(ElementName.COVERCOLOR, 0x6666666666l);
		elemInfoTable[8] = new ElemInfoTable(ElementName.COVERCOLORDETAILS, 0x3333333333l);
		elemInfoTable[9] = new ElemInfoTable(ElementName.ADHESIVEBINDING, 0x6666666666l);
		elemInfoTable[10] = new ElemInfoTable(ElementName.ADHESIVENOTE, 0x6666666666l);
		elemInfoTable[11] = new ElemInfoTable(ElementName.BINDLIST, 0x6666666666l);
		elemInfoTable[12] = new ElemInfoTable(ElementName.BOOKCASE, 0x6666666666l);
		elemInfoTable[13] = new ElemInfoTable(ElementName.CHANNELBINDING, 0x6666666666l);
		elemInfoTable[14] = new ElemInfoTable(ElementName.COILBINDING, 0x6666666666l);
		elemInfoTable[15] = new ElemInfoTable(ElementName.EDGEGLUING, 0x6666666666l);
		elemInfoTable[16] = new ElemInfoTable(ElementName.HARDCOVERBINDING, 0x6666666666l);
		elemInfoTable[17] = new ElemInfoTable(ElementName.PLASTICCOMBBINDING, 0x6666666666l);
		elemInfoTable[18] = new ElemInfoTable(ElementName.RINGBINDING, 0x6666666666l);
		elemInfoTable[19] = new ElemInfoTable(ElementName.SADDLESTITCHING, 0x6666666666l);
		elemInfoTable[20] = new ElemInfoTable(ElementName.SIDESEWING, 0x6666666666l);
		elemInfoTable[21] = new ElemInfoTable(ElementName.SIDESTITCHING, 0x6666666666l);
		elemInfoTable[22] = new ElemInfoTable(ElementName.SOFTCOVERBINDING, 0x6666666666l);
		elemInfoTable[23] = new ElemInfoTable(ElementName.TAPE, 0x6666666666l);
		elemInfoTable[24] = new ElemInfoTable(ElementName.TABS, 0x6666666666l);
		elemInfoTable[25] = new ElemInfoTable(ElementName.THREADSEALING, 0x6666666666l);
		elemInfoTable[26] = new ElemInfoTable(ElementName.THREADSEWING, 0x6666666666l);
		elemInfoTable[27] = new ElemInfoTable(ElementName.STRIPBINDING, 0x6666666666l);
		elemInfoTable[28] = new ElemInfoTable(ElementName.WIRECOMBBINDING, 0x6666666666l);
	}

	@Override
	protected ElementInfo getTheElementInfo()
	{
		return super.getTheElementInfo().updateReplace(elemInfoTable);
	}

	/**
	 * Constructor for JDFAutoBindingIntent
	 *
	 * @param myOwnerDocument
	 * @param qualifiedName
	 */
	protected JDFAutoBindingIntent(CoreDocumentImpl myOwnerDocument, String qualifiedName)
	{
		super(myOwnerDocument, qualifiedName);
	}

	/**
	 * Constructor for JDFAutoBindingIntent
	 *
	 * @param myOwnerDocument
	 * @param myNamespaceURI
	 * @param qualifiedName
	 */
	protected JDFAutoBindingIntent(CoreDocumentImpl myOwnerDocument, String myNamespaceURI, String qualifiedName)
	{
		super(myOwnerDocument, myNamespaceURI, qualifiedName);
	}

	/**
	 * Constructor for JDFAutoBindingIntent
	 *
	 * @param myOwnerDocument
	 * @param myNamespaceURI
	 * @param qualifiedName
	 * @param myLocalName
	 */
	protected JDFAutoBindingIntent(CoreDocumentImpl myOwnerDocument, String myNamespaceURI, String qualifiedName, String myLocalName)
	{
		super(myOwnerDocument, myNamespaceURI, qualifiedName, myLocalName);
	}

	/**
	 * Enumeration strings for BindingOrder
	 */

	public enum EBindingOrder
	{
		Collecting, Gathering, List, None;

		public static EBindingOrder getEnum(String val)
		{
			return JavaEnumUtil.getEnumIgnoreCase(EBindingOrder.class, val, EBindingOrder.Gathering);
		}
	}

	/**
	 * Enumeration strings for BindingOrder
	 */

	@SuppressWarnings("rawtypes")
	public static class EnumBindingOrder extends ValuedEnum
	{
		private static final long serialVersionUID = 1L;
		private static int m_startValue = 0;

		protected EnumBindingOrder(String name)
		{
			super(name, m_startValue++);
		}

		/**
		 * @param enumName the string to convert
		 * @return the enum
		 */
		public static EnumBindingOrder getEnum(String enumName)
		{
			return (EnumBindingOrder) getEnum(EnumBindingOrder.class, enumName);
		}

		/**
		 * @param enumValue the integer to convert
		 * @return the enum
		 */
		public static EnumBindingOrder getEnum(int enumValue)
		{
			return (EnumBindingOrder) getEnum(EnumBindingOrder.class, enumValue);
		}

		/**
		 * @return the map of enums
		 */
		public static Map getEnumMap()
		{
			return getEnumMap(EnumBindingOrder.class);
		}

		/**
		 * @return the list of enums
		 */
		public static List getEnumList()
		{
			return getEnumList(EnumBindingOrder.class);
		}

		/**
		 * @return the iterator
		 */
		public static Iterator iterator()
		{
			return iterator(EnumBindingOrder.class);
		}

		/**  */
		public static final EnumBindingOrder Collecting = new EnumBindingOrder("Collecting");
		/**  */
		public static final EnumBindingOrder Gathering = new EnumBindingOrder("Gathering");
		/**  */
		public static final EnumBindingOrder List = new EnumBindingOrder("List");
		/**  */
		public static final EnumBindingOrder None = new EnumBindingOrder("None");
	}

	/*
	 * ************************************************************************ Attribute getter / setter ************************************************************************
	 */

	/*
	 * --------------------------------------------------------------------- Methods for Attribute BindingOrder
	 * ---------------------------------------------------------------------
	 */
	/**
	 * (5) set attribute BindingOrder
	 *
	 * @param enumVar the enumVar to set the attribute to
	 */
	public void setBindingOrder(EBindingOrder enumVar)
	{
		setAttribute(AttributeName.BINDINGORDER, enumVar == null ? null : enumVar.name(), null);
	}

	/**
	 * (9) get attribute BindingOrder
	 *
	 * @return the value of the attribute
	 */
	public EBindingOrder getEBindingOrder()
	{
		return EBindingOrder.getEnum(getAttribute(AttributeName.BINDINGORDER, null, "Gathering"));
	}

	/*
	 * --------------------------------------------------------------------- Methods for Attribute BindingOrder
	 * ---------------------------------------------------------------------
	 */
	/**
	 * (5) set attribute BindingOrder
	 *
	 * @param enumVar the enumVar to set the attribute to
	 * @deprecated use java.lang.enum
	 */
	@Deprecated
	public void setBindingOrder(EnumBindingOrder enumVar)
	{
		setAttribute(AttributeName.BINDINGORDER, enumVar == null ? null : enumVar.getName(), null);
	}

	/**
	 * (9) get attribute BindingOrder
	 *
	 * @return the value of the attribute
	 */
	public EnumBindingOrder getBindingOrder()
	{
		return EnumBindingOrder.getEnum(getAttribute(AttributeName.BINDINGORDER, null, "Gathering"));
	}

	/*
	 * *********************************************************************** Element getter / setter ***********************************************************************
	 */

	/**
	 * (24) const get element BackCoverColor
	 *
	 * @return JDFSpanNamedColor the element
	 */
	public JDFSpanNamedColor getBackCoverColor()
	{
		return (JDFSpanNamedColor) getElement(ElementName.BACKCOVERCOLOR, null, 0);
	}

	/**
	 * (25) getCreateBackCoverColor
	 * 
	 * @return JDFSpanNamedColor the element
	 */
	public JDFSpanNamedColor getCreateBackCoverColor()
	{
		return (JDFSpanNamedColor) getCreateElement_JDFElement(ElementName.BACKCOVERCOLOR, null, 0);
	}

	/**
	 * (29) append element BackCoverColor
	 *
	 * @return JDFSpanNamedColor the element @ if the element already exists
	 */
	public JDFSpanNamedColor appendBackCoverColor()
	{
		return (JDFSpanNamedColor) appendElementN(ElementName.BACKCOVERCOLOR, 1, null);
	}

	/**
	 * (24) const get element BackCoverColorDetails
	 *
	 * @return JDFStringSpan the element
	 */
	public JDFStringSpan getBackCoverColorDetails()
	{
		return (JDFStringSpan) getElement(ElementName.BACKCOVERCOLORDETAILS, null, 0);
	}

	/**
	 * (25) getCreateBackCoverColorDetails
	 * 
	 * @return JDFStringSpan the element
	 */
	public JDFStringSpan getCreateBackCoverColorDetails()
	{
		return (JDFStringSpan) getCreateElement_JDFElement(ElementName.BACKCOVERCOLORDETAILS, null, 0);
	}

	/**
	 * (26) getCreateBackCoverColorDetails
	 * 
	 * @param iSkip number of elements to skip
	 * @return JDFStringSpan the element
	 */
	public JDFStringSpan getCreateBackCoverColorDetails(int iSkip)
	{
		return (JDFStringSpan) getCreateElement_JDFElement(ElementName.BACKCOVERCOLORDETAILS, null, iSkip);
	}

	/**
	 * (27) const get element BackCoverColorDetails
	 *
	 * @param iSkip number of elements to skip
	 * @return JDFStringSpan the element default is getBackCoverColorDetails(0)
	 */
	public JDFStringSpan getBackCoverColorDetails(int iSkip)
	{
		return (JDFStringSpan) getElement(ElementName.BACKCOVERCOLORDETAILS, null, iSkip);
	}

	/**
	 * Get all BackCoverColorDetails from the current element
	 * 
	 * @return Collection<JDFStringSpan>, null if none are available
	 */
	public Collection<JDFStringSpan> getAllBackCoverColorDetails()
	{
		return getChildArrayByClass(JDFStringSpan.class, false, 0);
	}

	/**
	 * (30) append element BackCoverColorDetails
	 *
	 * @return JDFStringSpan the element
	 */
	public JDFStringSpan appendBackCoverColorDetails()
	{
		return (JDFStringSpan) appendElement(ElementName.BACKCOVERCOLORDETAILS, null);
	}

	/**
	 * (24) const get element BindingType
	 *
	 * @return JDFSpanBindingType the element
	 */
	public JDFSpanBindingType getBindingType()
	{
		return (JDFSpanBindingType) getElement(ElementName.BINDINGTYPE, null, 0);
	}

	/**
	 * (25) getCreateBindingType
	 * 
	 * @return JDFSpanBindingType the element
	 */
	public JDFSpanBindingType getCreateBindingType()
	{
		return (JDFSpanBindingType) getCreateElement_JDFElement(ElementName.BINDINGTYPE, null, 0);
	}

	/**
	 * (29) append element BindingType
	 *
	 * @return JDFSpanBindingType the element @ if the element already exists
	 */
	public JDFSpanBindingType appendBindingType()
	{
		return (JDFSpanBindingType) appendElementN(ElementName.BINDINGTYPE, 1, null);
	}

	/**
	 * (24) const get element BindingColor
	 *
	 * @return JDFSpanNamedColor the element
	 */
	public JDFSpanNamedColor getBindingColor()
	{
		return (JDFSpanNamedColor) getElement(ElementName.BINDINGCOLOR, null, 0);
	}

	/**
	 * (25) getCreateBindingColor
	 * 
	 * @return JDFSpanNamedColor the element
	 */
	public JDFSpanNamedColor getCreateBindingColor()
	{
		return (JDFSpanNamedColor) getCreateElement_JDFElement(ElementName.BINDINGCOLOR, null, 0);
	}

	/**
	 * (29) append element BindingColor
	 *
	 * @return JDFSpanNamedColor the element @ if the element already exists
	 */
	public JDFSpanNamedColor appendBindingColor()
	{
		return (JDFSpanNamedColor) appendElementN(ElementName.BINDINGCOLOR, 1, null);
	}

	/**
	 * (24) const get element BindingColorDetails
	 *
	 * @return JDFStringSpan the element
	 */
	public JDFStringSpan getBindingColorDetails()
	{
		return (JDFStringSpan) getElement(ElementName.BINDINGCOLORDETAILS, null, 0);
	}

	/**
	 * (25) getCreateBindingColorDetails
	 * 
	 * @return JDFStringSpan the element
	 */
	public JDFStringSpan getCreateBindingColorDetails()
	{
		return (JDFStringSpan) getCreateElement_JDFElement(ElementName.BINDINGCOLORDETAILS, null, 0);
	}

	/**
	 * (26) getCreateBindingColorDetails
	 * 
	 * @param iSkip number of elements to skip
	 * @return JDFStringSpan the element
	 */
	public JDFStringSpan getCreateBindingColorDetails(int iSkip)
	{
		return (JDFStringSpan) getCreateElement_JDFElement(ElementName.BINDINGCOLORDETAILS, null, iSkip);
	}

	/**
	 * (27) const get element BindingColorDetails
	 *
	 * @param iSkip number of elements to skip
	 * @return JDFStringSpan the element default is getBindingColorDetails(0)
	 */
	public JDFStringSpan getBindingColorDetails(int iSkip)
	{
		return (JDFStringSpan) getElement(ElementName.BINDINGCOLORDETAILS, null, iSkip);
	}

	/**
	 * Get all BindingColorDetails from the current element
	 * 
	 * @return Collection<JDFStringSpan>, null if none are available
	 */
	public Collection<JDFStringSpan> getAllBindingColorDetails()
	{
		return getChildArrayByClass(JDFStringSpan.class, false, 0);
	}

	/**
	 * (30) append element BindingColorDetails
	 *
	 * @return JDFStringSpan the element
	 */
	public JDFStringSpan appendBindingColorDetails()
	{
		return (JDFStringSpan) appendElement(ElementName.BINDINGCOLORDETAILS, null);
	}

	/**
	 * (24) const get element BindingLength
	 *
	 * @return JDFSpanBindingLength the element
	 */
	public JDFSpanBindingLength getBindingLength()
	{
		return (JDFSpanBindingLength) getElement(ElementName.BINDINGLENGTH, null, 0);
	}

	/**
	 * (25) getCreateBindingLength
	 * 
	 * @return JDFSpanBindingLength the element
	 */
	public JDFSpanBindingLength getCreateBindingLength()
	{
		return (JDFSpanBindingLength) getCreateElement_JDFElement(ElementName.BINDINGLENGTH, null, 0);
	}

	/**
	 * (29) append element BindingLength
	 *
	 * @return JDFSpanBindingLength the element @ if the element already exists
	 */
	public JDFSpanBindingLength appendBindingLength()
	{
		return (JDFSpanBindingLength) appendElementN(ElementName.BINDINGLENGTH, 1, null);
	}

	/**
	 * (24) const get element BindingSide
	 *
	 * @return JDFSpanBindingSide the element
	 */
	public JDFSpanBindingSide getBindingSide()
	{
		return (JDFSpanBindingSide) getElement(ElementName.BINDINGSIDE, null, 0);
	}

	/**
	 * (25) getCreateBindingSide
	 * 
	 * @return JDFSpanBindingSide the element
	 */
	public JDFSpanBindingSide getCreateBindingSide()
	{
		return (JDFSpanBindingSide) getCreateElement_JDFElement(ElementName.BINDINGSIDE, null, 0);
	}

	/**
	 * (29) append element BindingSide
	 *
	 * @return JDFSpanBindingSide the element @ if the element already exists
	 */
	public JDFSpanBindingSide appendBindingSide()
	{
		return (JDFSpanBindingSide) appendElementN(ElementName.BINDINGSIDE, 1, null);
	}

	/**
	 * (24) const get element CoverColor
	 *
	 * @return JDFSpanNamedColor the element
	 */
	public JDFSpanNamedColor getCoverColor()
	{
		return (JDFSpanNamedColor) getElement(ElementName.COVERCOLOR, null, 0);
	}

	/**
	 * (25) getCreateCoverColor
	 * 
	 * @return JDFSpanNamedColor the element
	 */
	public JDFSpanNamedColor getCreateCoverColor()
	{
		return (JDFSpanNamedColor) getCreateElement_JDFElement(ElementName.COVERCOLOR, null, 0);
	}

	/**
	 * (29) append element CoverColor
	 *
	 * @return JDFSpanNamedColor the element @ if the element already exists
	 */
	public JDFSpanNamedColor appendCoverColor()
	{
		return (JDFSpanNamedColor) appendElementN(ElementName.COVERCOLOR, 1, null);
	}

	/**
	 * (24) const get element CoverColorDetails
	 *
	 * @return JDFStringSpan the element
	 */
	public JDFStringSpan getCoverColorDetails()
	{
		return (JDFStringSpan) getElement(ElementName.COVERCOLORDETAILS, null, 0);
	}

	/**
	 * (25) getCreateCoverColorDetails
	 * 
	 * @return JDFStringSpan the element
	 */
	public JDFStringSpan getCreateCoverColorDetails()
	{
		return (JDFStringSpan) getCreateElement_JDFElement(ElementName.COVERCOLORDETAILS, null, 0);
	}

	/**
	 * (26) getCreateCoverColorDetails
	 * 
	 * @param iSkip number of elements to skip
	 * @return JDFStringSpan the element
	 */
	public JDFStringSpan getCreateCoverColorDetails(int iSkip)
	{
		return (JDFStringSpan) getCreateElement_JDFElement(ElementName.COVERCOLORDETAILS, null, iSkip);
	}

	/**
	 * (27) const get element CoverColorDetails
	 *
	 * @param iSkip number of elements to skip
	 * @return JDFStringSpan the element default is getCoverColorDetails(0)
	 */
	public JDFStringSpan getCoverColorDetails(int iSkip)
	{
		return (JDFStringSpan) getElement(ElementName.COVERCOLORDETAILS, null, iSkip);
	}

	/**
	 * Get all CoverColorDetails from the current element
	 * 
	 * @return Collection<JDFStringSpan>, null if none are available
	 */
	public Collection<JDFStringSpan> getAllCoverColorDetails()
	{
		return getChildArrayByClass(JDFStringSpan.class, false, 0);
	}

	/**
	 * (30) append element CoverColorDetails
	 *
	 * @return JDFStringSpan the element
	 */
	public JDFStringSpan appendCoverColorDetails()
	{
		return (JDFStringSpan) appendElement(ElementName.COVERCOLORDETAILS, null);
	}

	/**
	 * (24) const get element AdhesiveBinding
	 *
	 * @return JDFAdhesiveBinding the element
	 */
	public JDFAdhesiveBinding getAdhesiveBinding()
	{
		return (JDFAdhesiveBinding) getElement(ElementName.ADHESIVEBINDING, null, 0);
	}

	/**
	 * (25) getCreateAdhesiveBinding
	 * 
	 * @return JDFAdhesiveBinding the element
	 */
	public JDFAdhesiveBinding getCreateAdhesiveBinding()
	{
		return (JDFAdhesiveBinding) getCreateElement_JDFElement(ElementName.ADHESIVEBINDING, null, 0);
	}

	/**
	 * (29) append element AdhesiveBinding
	 *
	 * @return JDFAdhesiveBinding the element @ if the element already exists
	 */
	public JDFAdhesiveBinding appendAdhesiveBinding()
	{
		return (JDFAdhesiveBinding) appendElementN(ElementName.ADHESIVEBINDING, 1, null);
	}

	/**
	 * (24) const get element AdhesiveNote
	 *
	 * @return JDFAdhesiveNote the element
	 */
	public JDFAdhesiveNote getAdhesiveNote()
	{
		return (JDFAdhesiveNote) getElement(ElementName.ADHESIVENOTE, null, 0);
	}

	/**
	 * (25) getCreateAdhesiveNote
	 * 
	 * @return JDFAdhesiveNote the element
	 */
	public JDFAdhesiveNote getCreateAdhesiveNote()
	{
		return (JDFAdhesiveNote) getCreateElement_JDFElement(ElementName.ADHESIVENOTE, null, 0);
	}

	/**
	 * (29) append element AdhesiveNote
	 *
	 * @return JDFAdhesiveNote the element @ if the element already exists
	 */
	public JDFAdhesiveNote appendAdhesiveNote()
	{
		return (JDFAdhesiveNote) appendElementN(ElementName.ADHESIVENOTE, 1, null);
	}

	/**
	 * (24) const get element BindList
	 *
	 * @return JDFBindList the element
	 */
	public JDFBindList getBindList()
	{
		return (JDFBindList) getElement(ElementName.BINDLIST, null, 0);
	}

	/**
	 * (25) getCreateBindList
	 * 
	 * @return JDFBindList the element
	 */
	public JDFBindList getCreateBindList()
	{
		return (JDFBindList) getCreateElement_JDFElement(ElementName.BINDLIST, null, 0);
	}

	/**
	 * (29) append element BindList
	 *
	 * @return JDFBindList the element @ if the element already exists
	 */
	public JDFBindList appendBindList()
	{
		return (JDFBindList) appendElementN(ElementName.BINDLIST, 1, null);
	}

	/**
	 * (24) const get element BookCase
	 *
	 * @return JDFBookCase the element
	 */
	public JDFBookCase getBookCase()
	{
		return (JDFBookCase) getElement(ElementName.BOOKCASE, null, 0);
	}

	/**
	 * (25) getCreateBookCase
	 * 
	 * @return JDFBookCase the element
	 */
	public JDFBookCase getCreateBookCase()
	{
		return (JDFBookCase) getCreateElement_JDFElement(ElementName.BOOKCASE, null, 0);
	}

	/**
	 * (29) append element BookCase
	 *
	 * @return JDFBookCase the element @ if the element already exists
	 */
	public JDFBookCase appendBookCase()
	{
		return (JDFBookCase) appendElementN(ElementName.BOOKCASE, 1, null);
	}

	/**
	 * (24) const get element ChannelBinding
	 *
	 * @return JDFChannelBinding the element
	 */
	public JDFChannelBinding getChannelBinding()
	{
		return (JDFChannelBinding) getElement(ElementName.CHANNELBINDING, null, 0);
	}

	/**
	 * (25) getCreateChannelBinding
	 * 
	 * @return JDFChannelBinding the element
	 */
	public JDFChannelBinding getCreateChannelBinding()
	{
		return (JDFChannelBinding) getCreateElement_JDFElement(ElementName.CHANNELBINDING, null, 0);
	}

	/**
	 * (29) append element ChannelBinding
	 *
	 * @return JDFChannelBinding the element @ if the element already exists
	 */
	public JDFChannelBinding appendChannelBinding()
	{
		return (JDFChannelBinding) appendElementN(ElementName.CHANNELBINDING, 1, null);
	}

	/**
	 * (24) const get element CoilBinding
	 *
	 * @return JDFCoilBinding the element
	 */
	public JDFCoilBinding getCoilBinding()
	{
		return (JDFCoilBinding) getElement(ElementName.COILBINDING, null, 0);
	}

	/**
	 * (25) getCreateCoilBinding
	 * 
	 * @return JDFCoilBinding the element
	 */
	public JDFCoilBinding getCreateCoilBinding()
	{
		return (JDFCoilBinding) getCreateElement_JDFElement(ElementName.COILBINDING, null, 0);
	}

	/**
	 * (29) append element CoilBinding
	 *
	 * @return JDFCoilBinding the element @ if the element already exists
	 */
	public JDFCoilBinding appendCoilBinding()
	{
		return (JDFCoilBinding) appendElementN(ElementName.COILBINDING, 1, null);
	}

	/**
	 * (24) const get element EdgeGluing
	 *
	 * @return JDFEdgeGluing the element
	 */
	public JDFEdgeGluing getEdgeGluing()
	{
		return (JDFEdgeGluing) getElement(ElementName.EDGEGLUING, null, 0);
	}

	/**
	 * (25) getCreateEdgeGluing
	 * 
	 * @return JDFEdgeGluing the element
	 */
	public JDFEdgeGluing getCreateEdgeGluing()
	{
		return (JDFEdgeGluing) getCreateElement_JDFElement(ElementName.EDGEGLUING, null, 0);
	}

	/**
	 * (29) append element EdgeGluing
	 *
	 * @return JDFEdgeGluing the element @ if the element already exists
	 */
	public JDFEdgeGluing appendEdgeGluing()
	{
		return (JDFEdgeGluing) appendElementN(ElementName.EDGEGLUING, 1, null);
	}

	/**
	 * (24) const get element HardCoverBinding
	 *
	 * @return JDFHardCoverBinding the element
	 */
	public JDFHardCoverBinding getHardCoverBinding()
	{
		return (JDFHardCoverBinding) getElement(ElementName.HARDCOVERBINDING, null, 0);
	}

	/**
	 * (25) getCreateHardCoverBinding
	 * 
	 * @return JDFHardCoverBinding the element
	 */
	public JDFHardCoverBinding getCreateHardCoverBinding()
	{
		return (JDFHardCoverBinding) getCreateElement_JDFElement(ElementName.HARDCOVERBINDING, null, 0);
	}

	/**
	 * (29) append element HardCoverBinding
	 *
	 * @return JDFHardCoverBinding the element @ if the element already exists
	 */
	public JDFHardCoverBinding appendHardCoverBinding()
	{
		return (JDFHardCoverBinding) appendElementN(ElementName.HARDCOVERBINDING, 1, null);
	}

	/**
	 * (24) const get element PlasticCombBinding
	 *
	 * @return JDFPlasticCombBinding the element
	 */
	public JDFPlasticCombBinding getPlasticCombBinding()
	{
		return (JDFPlasticCombBinding) getElement(ElementName.PLASTICCOMBBINDING, null, 0);
	}

	/**
	 * (25) getCreatePlasticCombBinding
	 * 
	 * @return JDFPlasticCombBinding the element
	 */
	public JDFPlasticCombBinding getCreatePlasticCombBinding()
	{
		return (JDFPlasticCombBinding) getCreateElement_JDFElement(ElementName.PLASTICCOMBBINDING, null, 0);
	}

	/**
	 * (29) append element PlasticCombBinding
	 *
	 * @return JDFPlasticCombBinding the element @ if the element already exists
	 */
	public JDFPlasticCombBinding appendPlasticCombBinding()
	{
		return (JDFPlasticCombBinding) appendElementN(ElementName.PLASTICCOMBBINDING, 1, null);
	}

	/**
	 * (24) const get element RingBinding
	 *
	 * @return JDFRingBinding the element
	 */
	public JDFRingBinding getRingBinding()
	{
		return (JDFRingBinding) getElement(ElementName.RINGBINDING, null, 0);
	}

	/**
	 * (25) getCreateRingBinding
	 * 
	 * @return JDFRingBinding the element
	 */
	public JDFRingBinding getCreateRingBinding()
	{
		return (JDFRingBinding) getCreateElement_JDFElement(ElementName.RINGBINDING, null, 0);
	}

	/**
	 * (29) append element RingBinding
	 *
	 * @return JDFRingBinding the element @ if the element already exists
	 */
	public JDFRingBinding appendRingBinding()
	{
		return (JDFRingBinding) appendElementN(ElementName.RINGBINDING, 1, null);
	}

	/**
	 * (24) const get element SaddleStitching
	 *
	 * @return JDFSaddleStitching the element
	 */
	public JDFSaddleStitching getSaddleStitching()
	{
		return (JDFSaddleStitching) getElement(ElementName.SADDLESTITCHING, null, 0);
	}

	/**
	 * (25) getCreateSaddleStitching
	 * 
	 * @return JDFSaddleStitching the element
	 */
	public JDFSaddleStitching getCreateSaddleStitching()
	{
		return (JDFSaddleStitching) getCreateElement_JDFElement(ElementName.SADDLESTITCHING, null, 0);
	}

	/**
	 * (29) append element SaddleStitching
	 *
	 * @return JDFSaddleStitching the element @ if the element already exists
	 */
	public JDFSaddleStitching appendSaddleStitching()
	{
		return (JDFSaddleStitching) appendElementN(ElementName.SADDLESTITCHING, 1, null);
	}

	/**
	 * (24) const get element SideSewing
	 *
	 * @return JDFSideSewing the element
	 */
	public JDFSideSewing getSideSewing()
	{
		return (JDFSideSewing) getElement(ElementName.SIDESEWING, null, 0);
	}

	/**
	 * (25) getCreateSideSewing
	 * 
	 * @return JDFSideSewing the element
	 */
	public JDFSideSewing getCreateSideSewing()
	{
		return (JDFSideSewing) getCreateElement_JDFElement(ElementName.SIDESEWING, null, 0);
	}

	/**
	 * (29) append element SideSewing
	 *
	 * @return JDFSideSewing the element @ if the element already exists
	 */
	public JDFSideSewing appendSideSewing()
	{
		return (JDFSideSewing) appendElementN(ElementName.SIDESEWING, 1, null);
	}

	/**
	 * (24) const get element SideStitching
	 *
	 * @return JDFSideStitching the element
	 */
	public JDFSideStitching getSideStitching()
	{
		return (JDFSideStitching) getElement(ElementName.SIDESTITCHING, null, 0);
	}

	/**
	 * (25) getCreateSideStitching
	 * 
	 * @return JDFSideStitching the element
	 */
	public JDFSideStitching getCreateSideStitching()
	{
		return (JDFSideStitching) getCreateElement_JDFElement(ElementName.SIDESTITCHING, null, 0);
	}

	/**
	 * (29) append element SideStitching
	 *
	 * @return JDFSideStitching the element @ if the element already exists
	 */
	public JDFSideStitching appendSideStitching()
	{
		return (JDFSideStitching) appendElementN(ElementName.SIDESTITCHING, 1, null);
	}

	/**
	 * (24) const get element SoftCoverBinding
	 *
	 * @return JDFSoftCoverBinding the element
	 */
	public JDFSoftCoverBinding getSoftCoverBinding()
	{
		return (JDFSoftCoverBinding) getElement(ElementName.SOFTCOVERBINDING, null, 0);
	}

	/**
	 * (25) getCreateSoftCoverBinding
	 * 
	 * @return JDFSoftCoverBinding the element
	 */
	public JDFSoftCoverBinding getCreateSoftCoverBinding()
	{
		return (JDFSoftCoverBinding) getCreateElement_JDFElement(ElementName.SOFTCOVERBINDING, null, 0);
	}

	/**
	 * (29) append element SoftCoverBinding
	 *
	 * @return JDFSoftCoverBinding the element @ if the element already exists
	 */
	public JDFSoftCoverBinding appendSoftCoverBinding()
	{
		return (JDFSoftCoverBinding) appendElementN(ElementName.SOFTCOVERBINDING, 1, null);
	}

	/**
	 * (24) const get element Tape
	 *
	 * @return JDFTape the element
	 */
	public JDFTape getTape()
	{
		return (JDFTape) getElement(ElementName.TAPE, null, 0);
	}

	/**
	 * (25) getCreateTape
	 * 
	 * @return JDFTape the element
	 */
	public JDFTape getCreateTape()
	{
		return (JDFTape) getCreateElement_JDFElement(ElementName.TAPE, null, 0);
	}

	/**
	 * (29) append element Tape
	 *
	 * @return JDFTape the element @ if the element already exists
	 */
	public JDFTape appendTape()
	{
		return (JDFTape) appendElementN(ElementName.TAPE, 1, null);
	}

	/**
	 * (24) const get element Tabs
	 *
	 * @return JDFTabs the element
	 */
	public JDFTabs getTabs()
	{
		return (JDFTabs) getElement(ElementName.TABS, null, 0);
	}

	/**
	 * (25) getCreateTabs
	 * 
	 * @return JDFTabs the element
	 */
	public JDFTabs getCreateTabs()
	{
		return (JDFTabs) getCreateElement_JDFElement(ElementName.TABS, null, 0);
	}

	/**
	 * (29) append element Tabs
	 *
	 * @return JDFTabs the element @ if the element already exists
	 */
	public JDFTabs appendTabs()
	{
		return (JDFTabs) appendElementN(ElementName.TABS, 1, null);
	}

	/**
	 * (24) const get element ThreadSealing
	 *
	 * @return JDFThreadSealing the element
	 */
	public JDFThreadSealing getThreadSealing()
	{
		return (JDFThreadSealing) getElement(ElementName.THREADSEALING, null, 0);
	}

	/**
	 * (25) getCreateThreadSealing
	 * 
	 * @return JDFThreadSealing the element
	 */
	public JDFThreadSealing getCreateThreadSealing()
	{
		return (JDFThreadSealing) getCreateElement_JDFElement(ElementName.THREADSEALING, null, 0);
	}

	/**
	 * (29) append element ThreadSealing
	 *
	 * @return JDFThreadSealing the element @ if the element already exists
	 */
	public JDFThreadSealing appendThreadSealing()
	{
		return (JDFThreadSealing) appendElementN(ElementName.THREADSEALING, 1, null);
	}

	/**
	 * (24) const get element ThreadSewing
	 *
	 * @return JDFThreadSewing the element
	 */
	public JDFThreadSewing getThreadSewing()
	{
		return (JDFThreadSewing) getElement(ElementName.THREADSEWING, null, 0);
	}

	/**
	 * (25) getCreateThreadSewing
	 * 
	 * @return JDFThreadSewing the element
	 */
	public JDFThreadSewing getCreateThreadSewing()
	{
		return (JDFThreadSewing) getCreateElement_JDFElement(ElementName.THREADSEWING, null, 0);
	}

	/**
	 * (29) append element ThreadSewing
	 *
	 * @return JDFThreadSewing the element @ if the element already exists
	 */
	public JDFThreadSewing appendThreadSewing()
	{
		return (JDFThreadSewing) appendElementN(ElementName.THREADSEWING, 1, null);
	}

	/**
	 * (24) const get element StripBinding
	 *
	 * @return JDFStripBinding the element
	 */
	public JDFStripBinding getStripBinding()
	{
		return (JDFStripBinding) getElement(ElementName.STRIPBINDING, null, 0);
	}

	/**
	 * (25) getCreateStripBinding
	 * 
	 * @return JDFStripBinding the element
	 */
	public JDFStripBinding getCreateStripBinding()
	{
		return (JDFStripBinding) getCreateElement_JDFElement(ElementName.STRIPBINDING, null, 0);
	}

	/**
	 * (29) append element StripBinding
	 *
	 * @return JDFStripBinding the element @ if the element already exists
	 */
	public JDFStripBinding appendStripBinding()
	{
		return (JDFStripBinding) appendElementN(ElementName.STRIPBINDING, 1, null);
	}

	/**
	 * (24) const get element WireCombBinding
	 *
	 * @return JDFWireCombBinding the element
	 */
	public JDFWireCombBinding getWireCombBinding()
	{
		return (JDFWireCombBinding) getElement(ElementName.WIRECOMBBINDING, null, 0);
	}

	/**
	 * (25) getCreateWireCombBinding
	 * 
	 * @return JDFWireCombBinding the element
	 */
	public JDFWireCombBinding getCreateWireCombBinding()
	{
		return (JDFWireCombBinding) getCreateElement_JDFElement(ElementName.WIRECOMBBINDING, null, 0);
	}

	/**
	 * (29) append element WireCombBinding
	 *
	 * @return JDFWireCombBinding the element @ if the element already exists
	 */
	public JDFWireCombBinding appendWireCombBinding()
	{
		return (JDFWireCombBinding) appendElementN(ElementName.WIRECOMBBINDING, 1, null);
	}

}
