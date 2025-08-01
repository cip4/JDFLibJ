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
import java.util.Vector;

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
import org.cip4.jdflib.core.VString;
import org.cip4.jdflib.resource.JDFPerformance;
import org.cip4.jdflib.resource.devicecapability.JDFActionPool;
import org.cip4.jdflib.resource.devicecapability.JDFDevCapPool;
import org.cip4.jdflib.resource.devicecapability.JDFDevCaps;
import org.cip4.jdflib.resource.devicecapability.JDFDisplayGroupPool;
import org.cip4.jdflib.resource.devicecapability.JDFFeaturePool;
import org.cip4.jdflib.resource.devicecapability.JDFMacroPool;
import org.cip4.jdflib.resource.devicecapability.JDFModulePool;
import org.cip4.jdflib.resource.devicecapability.JDFTestPool;
import org.cip4.jdflib.util.JavaEnumUtil;

/**
 *****************************************************************************
 * class JDFAutoDeviceCap : public JDFElement
 *****************************************************************************
 * 
 */

public abstract class JDFAutoDeviceCap extends JDFElement
{

	private static final long serialVersionUID = 1L;

	private static AtrInfoTable[] atrInfoTable = new AtrInfoTable[9];
	static
	{
		atrInfoTable[0] = new AtrInfoTable(AttributeName.COMBINEDMETHOD, 0x3333333331l, AttributeInfo.EnumAttributeType.enumerations, EnumCombinedMethod.getEnum(0), "None");
		atrInfoTable[1] = new AtrInfoTable(AttributeName.EXECUTIONPOLICY, 0x3333333311l, AttributeInfo.EnumAttributeType.enumeration, EnumExecutionPolicy.getEnum(0), "AllFound");
		atrInfoTable[2] = new AtrInfoTable(AttributeName.GENERICATTRIBUTES, 0x3333333331l, AttributeInfo.EnumAttributeType.NMTOKENS, null, null);
		atrInfoTable[3] = new AtrInfoTable(AttributeName.LANG, 0x3333333311l, AttributeInfo.EnumAttributeType.languages, null, null);
		atrInfoTable[4] = new AtrInfoTable(AttributeName.OPTIONALCOMBINEDTYPES, 0x4444444431l, AttributeInfo.EnumAttributeType.NMTOKENS, null, null);
		atrInfoTable[5] = new AtrInfoTable(AttributeName.TYPE, 0x4444444431l, AttributeInfo.EnumAttributeType.NMTOKEN, null, null);
		atrInfoTable[6] = new AtrInfoTable(AttributeName.TYPEEXPRESSION, 0x3333333311l, AttributeInfo.EnumAttributeType.Any, null, null);
		atrInfoTable[7] = new AtrInfoTable(AttributeName.TYPEORDER, 0x4444444431l, AttributeInfo.EnumAttributeType.enumeration, EnumTypeOrder.getEnum(0), null);
		atrInfoTable[8] = new AtrInfoTable(AttributeName.TYPES, 0x3333333331l, AttributeInfo.EnumAttributeType.NMTOKENS, null, null);
	}

	@Override
	protected AttributeInfo getTheAttributeInfo()
	{
		return super.getTheAttributeInfo().updateReplace(atrInfoTable);
	}

	private static ElemInfoTable[] elemInfoTable = new ElemInfoTable[9];
	static
	{
		elemInfoTable[0] = new ElemInfoTable(ElementName.ACTIONPOOL, 0x6666666661l);
		elemInfoTable[1] = new ElemInfoTable(ElementName.DEVCAPPOOL, 0x6666666661l);
		elemInfoTable[2] = new ElemInfoTable(ElementName.DEVCAPS, 0x3333333331l);
		elemInfoTable[3] = new ElemInfoTable(ElementName.DISPLAYGROUPPOOL, 0x6666666661l);
		elemInfoTable[4] = new ElemInfoTable(ElementName.FEATUREPOOL, 0x6666666661l);
		elemInfoTable[5] = new ElemInfoTable(ElementName.MACROPOOL, 0x6666666661l);
		elemInfoTable[6] = new ElemInfoTable(ElementName.MODULEPOOL, 0x6666666661l);
		elemInfoTable[7] = new ElemInfoTable(ElementName.PERFORMANCE, 0x3333333331l);
		elemInfoTable[8] = new ElemInfoTable(ElementName.TESTPOOL, 0x6666666661l);
	}

	@Override
	protected ElementInfo getTheElementInfo()
	{
		return super.getTheElementInfo().updateReplace(elemInfoTable);
	}

	/**
	 * Constructor for JDFAutoDeviceCap
	 *
	 * @param myOwnerDocument
	 * @param qualifiedName
	 */
	protected JDFAutoDeviceCap(CoreDocumentImpl myOwnerDocument, String qualifiedName)
	{
		super(myOwnerDocument, qualifiedName);
	}

	/**
	 * Constructor for JDFAutoDeviceCap
	 *
	 * @param myOwnerDocument
	 * @param myNamespaceURI
	 * @param qualifiedName
	 */
	protected JDFAutoDeviceCap(CoreDocumentImpl myOwnerDocument, String myNamespaceURI, String qualifiedName)
	{
		super(myOwnerDocument, myNamespaceURI, qualifiedName);
	}

	/**
	 * Constructor for JDFAutoDeviceCap
	 *
	 * @param myOwnerDocument
	 * @param myNamespaceURI
	 * @param qualifiedName
	 * @param myLocalName
	 */
	protected JDFAutoDeviceCap(CoreDocumentImpl myOwnerDocument, String myNamespaceURI, String qualifiedName, String myLocalName)
	{
		super(myOwnerDocument, myNamespaceURI, qualifiedName, myLocalName);
	}

	/**
	 * Enumeration strings for CombinedMethod
	 */

	public enum ECombinedMethod
	{
		Combined, CombinedProcessGroup, GrayBox, ProcessGroup, None;

		public static ECombinedMethod getEnum(String val)
		{
			return JavaEnumUtil.getEnumIgnoreCase(ECombinedMethod.class, val, ECombinedMethod.None);
		}
	}

	/**
	 * Enumeration strings for CombinedMethod
	 */

	@SuppressWarnings("rawtypes")
	public static class EnumCombinedMethod extends ValuedEnum
	{
		private static final long serialVersionUID = 1L;
		private static int m_startValue = 0;

		protected EnumCombinedMethod(String name)
		{
			super(name, m_startValue++);
		}

		/**
		 * @param enumName the string to convert
		 * @return the enum
		 */
		public static EnumCombinedMethod getEnum(String enumName)
		{
			return (EnumCombinedMethod) getEnum(EnumCombinedMethod.class, enumName);
		}

		/**
		 * @param enumValue the integer to convert
		 * @return the enum
		 */
		public static EnumCombinedMethod getEnum(int enumValue)
		{
			return (EnumCombinedMethod) getEnum(EnumCombinedMethod.class, enumValue);
		}

		/**
		 * @return the map of enums
		 */
		public static Map getEnumMap()
		{
			return getEnumMap(EnumCombinedMethod.class);
		}

		/**
		 * @return the list of enums
		 */
		public static List getEnumList()
		{
			return getEnumList(EnumCombinedMethod.class);
		}

		/**
		 * @return the iterator
		 */
		public static Iterator iterator()
		{
			return iterator(EnumCombinedMethod.class);
		}

		/**  */
		public static final EnumCombinedMethod Combined = new EnumCombinedMethod("Combined");
		/**  */
		public static final EnumCombinedMethod CombinedProcessGroup = new EnumCombinedMethod("CombinedProcessGroup");
		/**  */
		public static final EnumCombinedMethod GrayBox = new EnumCombinedMethod("GrayBox");
		/**  */
		public static final EnumCombinedMethod ProcessGroup = new EnumCombinedMethod("ProcessGroup");
		/**  */
		public static final EnumCombinedMethod None = new EnumCombinedMethod("None");
	}

	/**
	 * Enumeration strings for ExecutionPolicy
	 */

	public enum EExecutionPolicy
	{
		RootNode, FirstFound, AllFound;

		public static EExecutionPolicy getEnum(String val)
		{
			return JavaEnumUtil.getEnumIgnoreCase(EExecutionPolicy.class, val, EExecutionPolicy.AllFound);
		}
	}

	/**
	 * Enumeration strings for ExecutionPolicy
	 */

	@SuppressWarnings("rawtypes")
	public static class EnumExecutionPolicy extends ValuedEnum
	{
		private static final long serialVersionUID = 1L;
		private static int m_startValue = 0;

		protected EnumExecutionPolicy(String name)
		{
			super(name, m_startValue++);
		}

		/**
		 * @param enumName the string to convert
		 * @return the enum
		 */
		public static EnumExecutionPolicy getEnum(String enumName)
		{
			return (EnumExecutionPolicy) getEnum(EnumExecutionPolicy.class, enumName);
		}

		/**
		 * @param enumValue the integer to convert
		 * @return the enum
		 */
		public static EnumExecutionPolicy getEnum(int enumValue)
		{
			return (EnumExecutionPolicy) getEnum(EnumExecutionPolicy.class, enumValue);
		}

		/**
		 * @return the map of enums
		 */
		public static Map getEnumMap()
		{
			return getEnumMap(EnumExecutionPolicy.class);
		}

		/**
		 * @return the list of enums
		 */
		public static List getEnumList()
		{
			return getEnumList(EnumExecutionPolicy.class);
		}

		/**
		 * @return the iterator
		 */
		public static Iterator iterator()
		{
			return iterator(EnumExecutionPolicy.class);
		}

		/**  */
		public static final EnumExecutionPolicy RootNode = new EnumExecutionPolicy("RootNode");
		/**  */
		public static final EnumExecutionPolicy FirstFound = new EnumExecutionPolicy("FirstFound");
		/**  */
		public static final EnumExecutionPolicy AllFound = new EnumExecutionPolicy("AllFound");
	}

	/**
	 * Enumeration strings for TypeOrder
	 */

	public enum ETypeOrder
	{
		Fixed, Unordered, Unrestricted;

		public static ETypeOrder getEnum(String val)
		{
			return JavaEnumUtil.getEnumIgnoreCase(ETypeOrder.class, val, null);
		}
	}

	/**
	 * Enumeration strings for TypeOrder
	 */

	@SuppressWarnings("rawtypes")
	public static class EnumTypeOrder extends ValuedEnum
	{
		private static final long serialVersionUID = 1L;
		private static int m_startValue = 0;

		protected EnumTypeOrder(String name)
		{
			super(name, m_startValue++);
		}

		/**
		 * @param enumName the string to convert
		 * @return the enum
		 */
		public static EnumTypeOrder getEnum(String enumName)
		{
			return (EnumTypeOrder) getEnum(EnumTypeOrder.class, enumName);
		}

		/**
		 * @param enumValue the integer to convert
		 * @return the enum
		 */
		public static EnumTypeOrder getEnum(int enumValue)
		{
			return (EnumTypeOrder) getEnum(EnumTypeOrder.class, enumValue);
		}

		/**
		 * @return the map of enums
		 */
		public static Map getEnumMap()
		{
			return getEnumMap(EnumTypeOrder.class);
		}

		/**
		 * @return the list of enums
		 */
		public static List getEnumList()
		{
			return getEnumList(EnumTypeOrder.class);
		}

		/**
		 * @return the iterator
		 */
		public static Iterator iterator()
		{
			return iterator(EnumTypeOrder.class);
		}

		/**  */
		public static final EnumTypeOrder Fixed = new EnumTypeOrder("Fixed");
		/**  */
		public static final EnumTypeOrder Unordered = new EnumTypeOrder("Unordered");
		/**  */
		public static final EnumTypeOrder Unrestricted = new EnumTypeOrder("Unrestricted");
	}

	/*
	 * ************************************************************************ Attribute getter / setter ************************************************************************
	 */

	/*
	 * --------------------------------------------------------------------- Methods for Attribute CombinedMethod
	 * ---------------------------------------------------------------------
	 */
	/**
	 * (5.2) set attribute CombinedMethod
	 *
	 * @param v List of the enumeration values
	 */
	public void setECombinedMethod(List<ECombinedMethod> v)
	{
		setEnumsAttribute(AttributeName.COMBINEDMETHOD, v, null);
	}

	/**
	 * (9.2) get CombinedMethod attribute CombinedMethod
	 *
	 * @return Vector of the enumerations
	 */
	public List<ECombinedMethod> getEnumsCombinedMethod()
	{
		return getEnumerationsAttribute(AttributeName.COMBINEDMETHOD, null, ECombinedMethod.class);
	}

	/*
	 * --------------------------------------------------------------------- Methods for Attribute CombinedMethod
	 * ---------------------------------------------------------------------
	 */
	/**
	 * (5.2) set attribute CombinedMethod
	 *
	 * @param v List of the enumeration values
	 * @deprecated use java.lang.enum
	 */
	@Deprecated
	public void setCombinedMethod(List<EnumCombinedMethod> v)
	{
		setEnumerationsAttribute(AttributeName.COMBINEDMETHOD, v, null);
	}

	/**
	 * (9.2) get CombinedMethod attribute CombinedMethod
	 *
	 * @return Vector of the enumerations
	 */
	public Vector<EnumCombinedMethod> getCombinedMethod()
	{
		return getEnumerationsAttribute(AttributeName.COMBINEDMETHOD, null, EnumCombinedMethod.None, false);
	}

	/*
	 * --------------------------------------------------------------------- Methods for Attribute ExecutionPolicy
	 * ---------------------------------------------------------------------
	 */
	/**
	 * (5) set attribute ExecutionPolicy
	 *
	 * @param enumVar the enumVar to set the attribute to
	 */
	public void setExecutionPolicy(EExecutionPolicy enumVar)
	{
		setAttribute(AttributeName.EXECUTIONPOLICY, enumVar == null ? null : enumVar.name(), null);
	}

	/**
	 * (9) get attribute ExecutionPolicy
	 *
	 * @return the value of the attribute
	 */
	public EExecutionPolicy getEExecutionPolicy()
	{
		return EExecutionPolicy.getEnum(getAttribute(AttributeName.EXECUTIONPOLICY, null, "AllFound"));
	}

	/*
	 * --------------------------------------------------------------------- Methods for Attribute ExecutionPolicy
	 * ---------------------------------------------------------------------
	 */
	/**
	 * (5) set attribute ExecutionPolicy
	 *
	 * @param enumVar the enumVar to set the attribute to
	 * @deprecated use java.lang.enum
	 */
	@Deprecated
	public void setExecutionPolicy(EnumExecutionPolicy enumVar)
	{
		setAttribute(AttributeName.EXECUTIONPOLICY, enumVar == null ? null : enumVar.getName(), null);
	}

	/**
	 * (9) get attribute ExecutionPolicy
	 *
	 * @return the value of the attribute
	 */
	public EnumExecutionPolicy getExecutionPolicy()
	{
		return EnumExecutionPolicy.getEnum(getAttribute(AttributeName.EXECUTIONPOLICY, null, "AllFound"));
	}

	/*
	 * --------------------------------------------------------------------- Methods for Attribute GenericAttributes
	 * ---------------------------------------------------------------------
	 */
	/**
	 * (36) set attribute GenericAttributes
	 *
	 * @param value the value to set the attribute to
	 */
	public void setGenericAttributes(VString value)
	{
		setAttribute(AttributeName.GENERICATTRIBUTES, value, null);
	}

	/**
	 * (21) get VString attribute GenericAttributes
	 *
	 * @return VString the value of the attribute
	 */
	public VString getGenericAttributes()
	{
		final VString vStrAttrib = new VString();
		final String s = getAttribute(AttributeName.GENERICATTRIBUTES, null, JDFCoreConstants.EMPTYSTRING);
		vStrAttrib.setAllStrings(s, " ");
		return vStrAttrib;
	}

	/*
	 * --------------------------------------------------------------------- Methods for Attribute Lang ---------------------------------------------------------------------
	 */
	/**
	 * (36) set attribute Lang
	 *
	 * @param value the value to set the attribute to
	 */
	public void setLang(VString value)
	{
		setAttribute(AttributeName.LANG, value, null);
	}

	/**
	 * (21) get VString attribute Lang
	 *
	 * @return VString the value of the attribute
	 */
	public VString getLang()
	{
		final VString vStrAttrib = new VString();
		final String s = getAttribute(AttributeName.LANG, null, JDFCoreConstants.EMPTYSTRING);
		vStrAttrib.setAllStrings(s, " ");
		return vStrAttrib;
	}

	/*
	 * --------------------------------------------------------------------- Methods for Attribute OptionalCombinedTypes
	 * ---------------------------------------------------------------------
	 */
	/**
	 * (36) set attribute OptionalCombinedTypes
	 *
	 * @param value the value to set the attribute to
	 */
	public void setOptionalCombinedTypes(VString value)
	{
		setAttribute(AttributeName.OPTIONALCOMBINEDTYPES, value, null);
	}

	/**
	 * (21) get VString attribute OptionalCombinedTypes
	 *
	 * @return VString the value of the attribute
	 */
	public VString getOptionalCombinedTypes()
	{
		final VString vStrAttrib = new VString();
		final String s = getAttribute(AttributeName.OPTIONALCOMBINEDTYPES, null, JDFCoreConstants.EMPTYSTRING);
		vStrAttrib.setAllStrings(s, " ");
		return vStrAttrib;
	}

	/*
	 * --------------------------------------------------------------------- Methods for Attribute Type ---------------------------------------------------------------------
	 */
	/**
	 * (36) set attribute Type
	 *
	 * @param value the value to set the attribute to
	 */
	public void setType(String value)
	{
		setAttribute(AttributeName.TYPE, value, null);
	}

	/**
	 * (23) get String attribute Type
	 *
	 * @return the value of the attribute
	 */
	public String getType()
	{
		return getAttribute(AttributeName.TYPE, null, JDFCoreConstants.EMPTYSTRING);
	}

	/*
	 * --------------------------------------------------------------------- Methods for Attribute TypeExpression
	 * ---------------------------------------------------------------------
	 */
	/**
	 * (36) set attribute TypeExpression
	 *
	 * @param value the value to set the attribute to
	 */
	public void setTypeExpression(String value)
	{
		setAttribute(AttributeName.TYPEEXPRESSION, value, null);
	}

	/**
	 * (23) get String attribute TypeExpression
	 *
	 * @return the value of the attribute
	 */
	public String getTypeExpression()
	{
		return getAttribute(AttributeName.TYPEEXPRESSION, null, JDFCoreConstants.EMPTYSTRING);
	}

	/*
	 * --------------------------------------------------------------------- Methods for Attribute TypeOrder ---------------------------------------------------------------------
	 */
	/**
	 * (5) set attribute TypeOrder
	 *
	 * @param enumVar the enumVar to set the attribute to
	 */
	public void setTypeOrder(ETypeOrder enumVar)
	{
		setAttribute(AttributeName.TYPEORDER, enumVar == null ? null : enumVar.name(), null);
	}

	/**
	 * (9) get attribute TypeOrder
	 *
	 * @return the value of the attribute
	 */
	public ETypeOrder getETypeOrder()
	{
		return ETypeOrder.getEnum(getAttribute(AttributeName.TYPEORDER, null, null));
	}

	/*
	 * --------------------------------------------------------------------- Methods for Attribute TypeOrder ---------------------------------------------------------------------
	 */
	/**
	 * (5) set attribute TypeOrder
	 *
	 * @param enumVar the enumVar to set the attribute to
	 * @deprecated use java.lang.enum
	 */
	@Deprecated
	public void setTypeOrder(EnumTypeOrder enumVar)
	{
		setAttribute(AttributeName.TYPEORDER, enumVar == null ? null : enumVar.getName(), null);
	}

	/**
	 * (9) get attribute TypeOrder
	 *
	 * @return the value of the attribute
	 */
	public EnumTypeOrder getTypeOrder()
	{
		return EnumTypeOrder.getEnum(getAttribute(AttributeName.TYPEORDER, null, null));
	}

	/*
	 * --------------------------------------------------------------------- Methods for Attribute Types ---------------------------------------------------------------------
	 */
	/**
	 * (36) set attribute Types
	 *
	 * @param value the value to set the attribute to
	 */
	public void setTypes(VString value)
	{
		setAttribute(AttributeName.TYPES, value, null);
	}

	/**
	 * (21) get VString attribute Types
	 *
	 * @return VString the value of the attribute
	 */
	public VString getTypes()
	{
		final VString vStrAttrib = new VString();
		final String s = getAttribute(AttributeName.TYPES, null, JDFCoreConstants.EMPTYSTRING);
		vStrAttrib.setAllStrings(s, " ");
		return vStrAttrib;
	}

	/*
	 * *********************************************************************** Element getter / setter ***********************************************************************
	 */

	/**
	 * (24) const get element ActionPool
	 *
	 * @return JDFActionPool the element
	 */
	public JDFActionPool getActionPool()
	{
		return (JDFActionPool) getElement(ElementName.ACTIONPOOL, null, 0);
	}

	/**
	 * (25) getCreateActionPool
	 * 
	 * @return JDFActionPool the element
	 */
	public JDFActionPool getCreateActionPool()
	{
		return (JDFActionPool) getCreateElement_JDFElement(ElementName.ACTIONPOOL, null, 0);
	}

	/**
	 * (29) append element ActionPool
	 *
	 * @return JDFActionPool the element @ if the element already exists
	 */
	public JDFActionPool appendActionPool()
	{
		return (JDFActionPool) appendElementN(ElementName.ACTIONPOOL, 1, null);
	}

	/**
	 * (24) const get element DevCapPool
	 *
	 * @return JDFDevCapPool the element
	 */
	public JDFDevCapPool getDevCapPool()
	{
		return (JDFDevCapPool) getElement(ElementName.DEVCAPPOOL, null, 0);
	}

	/**
	 * (25) getCreateDevCapPool
	 * 
	 * @return JDFDevCapPool the element
	 */
	public JDFDevCapPool getCreateDevCapPool()
	{
		return (JDFDevCapPool) getCreateElement_JDFElement(ElementName.DEVCAPPOOL, null, 0);
	}

	/**
	 * (29) append element DevCapPool
	 *
	 * @return JDFDevCapPool the element @ if the element already exists
	 */
	public JDFDevCapPool appendDevCapPool()
	{
		return (JDFDevCapPool) appendElementN(ElementName.DEVCAPPOOL, 1, null);
	}

	/**
	 * (24) const get element DevCaps
	 *
	 * @return JDFDevCaps the element
	 */
	public JDFDevCaps getDevCaps()
	{
		return (JDFDevCaps) getElement(ElementName.DEVCAPS, null, 0);
	}

	/**
	 * (25) getCreateDevCaps
	 * 
	 * @return JDFDevCaps the element
	 */
	public JDFDevCaps getCreateDevCaps()
	{
		return (JDFDevCaps) getCreateElement_JDFElement(ElementName.DEVCAPS, null, 0);
	}

	/**
	 * (26) getCreateDevCaps
	 * 
	 * @param iSkip number of elements to skip
	 * @return JDFDevCaps the element
	 */
	public JDFDevCaps getCreateDevCaps(int iSkip)
	{
		return (JDFDevCaps) getCreateElement_JDFElement(ElementName.DEVCAPS, null, iSkip);
	}

	/**
	 * (27) const get element DevCaps
	 *
	 * @param iSkip number of elements to skip
	 * @return JDFDevCaps the element default is getDevCaps(0)
	 */
	public JDFDevCaps getDevCaps(int iSkip)
	{
		return (JDFDevCaps) getElement(ElementName.DEVCAPS, null, iSkip);
	}

	/**
	 * Get all DevCaps from the current element
	 * 
	 * @return Collection<JDFDevCaps>, null if none are available
	 */
	public Collection<JDFDevCaps> getAllDevCaps()
	{
		return getChildArrayByClass(JDFDevCaps.class, false, 0);
	}

	/**
	 * (30) append element DevCaps
	 *
	 * @return JDFDevCaps the element
	 */
	public JDFDevCaps appendDevCaps()
	{
		return (JDFDevCaps) appendElement(ElementName.DEVCAPS, null);
	}

	/**
	 * (24) const get element DisplayGroupPool
	 *
	 * @return JDFDisplayGroupPool the element
	 */
	public JDFDisplayGroupPool getDisplayGroupPool()
	{
		return (JDFDisplayGroupPool) getElement(ElementName.DISPLAYGROUPPOOL, null, 0);
	}

	/**
	 * (25) getCreateDisplayGroupPool
	 * 
	 * @return JDFDisplayGroupPool the element
	 */
	public JDFDisplayGroupPool getCreateDisplayGroupPool()
	{
		return (JDFDisplayGroupPool) getCreateElement_JDFElement(ElementName.DISPLAYGROUPPOOL, null, 0);
	}

	/**
	 * (29) append element DisplayGroupPool
	 *
	 * @return JDFDisplayGroupPool the element @ if the element already exists
	 */
	public JDFDisplayGroupPool appendDisplayGroupPool()
	{
		return (JDFDisplayGroupPool) appendElementN(ElementName.DISPLAYGROUPPOOL, 1, null);
	}

	/**
	 * (24) const get element FeaturePool
	 *
	 * @return JDFFeaturePool the element
	 */
	public JDFFeaturePool getFeaturePool()
	{
		return (JDFFeaturePool) getElement(ElementName.FEATUREPOOL, null, 0);
	}

	/**
	 * (25) getCreateFeaturePool
	 * 
	 * @return JDFFeaturePool the element
	 */
	public JDFFeaturePool getCreateFeaturePool()
	{
		return (JDFFeaturePool) getCreateElement_JDFElement(ElementName.FEATUREPOOL, null, 0);
	}

	/**
	 * (29) append element FeaturePool
	 *
	 * @return JDFFeaturePool the element @ if the element already exists
	 */
	public JDFFeaturePool appendFeaturePool()
	{
		return (JDFFeaturePool) appendElementN(ElementName.FEATUREPOOL, 1, null);
	}

	/**
	 * (24) const get element MacroPool
	 *
	 * @return JDFMacroPool the element
	 */
	public JDFMacroPool getMacroPool()
	{
		return (JDFMacroPool) getElement(ElementName.MACROPOOL, null, 0);
	}

	/**
	 * (25) getCreateMacroPool
	 * 
	 * @return JDFMacroPool the element
	 */
	public JDFMacroPool getCreateMacroPool()
	{
		return (JDFMacroPool) getCreateElement_JDFElement(ElementName.MACROPOOL, null, 0);
	}

	/**
	 * (29) append element MacroPool
	 *
	 * @return JDFMacroPool the element @ if the element already exists
	 */
	public JDFMacroPool appendMacroPool()
	{
		return (JDFMacroPool) appendElementN(ElementName.MACROPOOL, 1, null);
	}

	/**
	 * (24) const get element ModulePool
	 *
	 * @return JDFModulePool the element
	 */
	public JDFModulePool getModulePool()
	{
		return (JDFModulePool) getElement(ElementName.MODULEPOOL, null, 0);
	}

	/**
	 * (25) getCreateModulePool
	 * 
	 * @return JDFModulePool the element
	 */
	public JDFModulePool getCreateModulePool()
	{
		return (JDFModulePool) getCreateElement_JDFElement(ElementName.MODULEPOOL, null, 0);
	}

	/**
	 * (29) append element ModulePool
	 *
	 * @return JDFModulePool the element @ if the element already exists
	 */
	public JDFModulePool appendModulePool()
	{
		return (JDFModulePool) appendElementN(ElementName.MODULEPOOL, 1, null);
	}

	/**
	 * (24) const get element Performance
	 *
	 * @return JDFPerformance the element
	 */
	public JDFPerformance getPerformance()
	{
		return (JDFPerformance) getElement(ElementName.PERFORMANCE, null, 0);
	}

	/**
	 * (25) getCreatePerformance
	 * 
	 * @return JDFPerformance the element
	 */
	public JDFPerformance getCreatePerformance()
	{
		return (JDFPerformance) getCreateElement_JDFElement(ElementName.PERFORMANCE, null, 0);
	}

	/**
	 * (26) getCreatePerformance
	 * 
	 * @param iSkip number of elements to skip
	 * @return JDFPerformance the element
	 */
	public JDFPerformance getCreatePerformance(int iSkip)
	{
		return (JDFPerformance) getCreateElement_JDFElement(ElementName.PERFORMANCE, null, iSkip);
	}

	/**
	 * (27) const get element Performance
	 *
	 * @param iSkip number of elements to skip
	 * @return JDFPerformance the element default is getPerformance(0)
	 */
	public JDFPerformance getPerformance(int iSkip)
	{
		return (JDFPerformance) getElement(ElementName.PERFORMANCE, null, iSkip);
	}

	/**
	 * Get all Performance from the current element
	 * 
	 * @return Collection<JDFPerformance>, null if none are available
	 */
	public Collection<JDFPerformance> getAllPerformance()
	{
		return getChildArrayByClass(JDFPerformance.class, false, 0);
	}

	/**
	 * (30) append element Performance
	 *
	 * @return JDFPerformance the element
	 */
	public JDFPerformance appendPerformance()
	{
		return (JDFPerformance) appendElement(ElementName.PERFORMANCE, null);
	}

	/**
	 * (24) const get element TestPool
	 *
	 * @return JDFTestPool the element
	 */
	public JDFTestPool getTestPool()
	{
		return (JDFTestPool) getElement(ElementName.TESTPOOL, null, 0);
	}

	/**
	 * (25) getCreateTestPool
	 * 
	 * @return JDFTestPool the element
	 */
	public JDFTestPool getCreateTestPool()
	{
		return (JDFTestPool) getCreateElement_JDFElement(ElementName.TESTPOOL, null, 0);
	}

	/**
	 * (29) append element TestPool
	 *
	 * @return JDFTestPool the element @ if the element already exists
	 */
	public JDFTestPool appendTestPool()
	{
		return (JDFTestPool) appendElementN(ElementName.TESTPOOL, 1, null);
	}

}
