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
import org.cip4.jdflib.core.JDFSeparationList;
import org.cip4.jdflib.resource.JDFResource;
import org.cip4.jdflib.resource.process.JDFColorPool;
import org.cip4.jdflib.resource.process.JDFColorantAlias;
import org.cip4.jdflib.resource.process.JDFDeviceNSpace;
import org.cip4.jdflib.resource.process.prepress.JDFColorSpaceSubstitute;
import org.cip4.jdflib.util.JavaEnumUtil;

/**
 *****************************************************************************
 * class JDFAutoColorantControl : public JDFResource
 *****************************************************************************
 * 
 */

public abstract class JDFAutoColorantControl extends JDFResource
{

	private static final long serialVersionUID = 1L;

	private static AtrInfoTable[] atrInfoTable = new AtrInfoTable[4];
	static
	{
		atrInfoTable[0] = new AtrInfoTable(AttributeName.FORCESEPARATIONS, 0x3333333333l, AttributeInfo.EnumAttributeType.boolean_, null, "false");
		atrInfoTable[1] = new AtrInfoTable(AttributeName.INTERNALCOLORMODEL, 0x3333311111l, AttributeInfo.EnumAttributeType.enumeration, EnumInternalColorModel.getEnum(0), null);
		atrInfoTable[2] = new AtrInfoTable(AttributeName.MAPPINGSELECTION, 0x3333311111l, AttributeInfo.EnumAttributeType.enumeration, EnumMappingSelection.getEnum(0), null);
		atrInfoTable[3] = new AtrInfoTable(AttributeName.PROCESSCOLORMODEL, 0x3333333333l, AttributeInfo.EnumAttributeType.NMTOKEN, null, null);
	}

	@Override
	protected AttributeInfo getTheAttributeInfo()
	{
		return super.getTheAttributeInfo().updateReplace(atrInfoTable);
	}

	private static ElemInfoTable[] elemInfoTable = new ElemInfoTable[8];
	static
	{
		elemInfoTable[0] = new ElemInfoTable(ElementName.COLORANTALIAS, 0x3333333333l);
		elemInfoTable[1] = new ElemInfoTable(ElementName.COLORANTCONVERTPROCESS, 0x6666661111l);
		elemInfoTable[2] = new ElemInfoTable(ElementName.COLORANTORDER, 0x6666666666l);
		elemInfoTable[3] = new ElemInfoTable(ElementName.COLORANTPARAMS, 0x6666666666l);
		elemInfoTable[4] = new ElemInfoTable(ElementName.COLORPOOL, 0x6666666666l);
		elemInfoTable[5] = new ElemInfoTable(ElementName.COLORSPACESUBSTITUTE, 0x3333333333l);
		elemInfoTable[6] = new ElemInfoTable(ElementName.DEVICECOLORANTORDER, 0x6666666666l);
		elemInfoTable[7] = new ElemInfoTable(ElementName.DEVICENSPACE, 0x3333333333l);
	}

	@Override
	protected ElementInfo getTheElementInfo()
	{
		return super.getTheElementInfo().updateReplace(elemInfoTable);
	}

	/**
	 * Constructor for JDFAutoColorantControl
	 *
	 * @param myOwnerDocument
	 * @param qualifiedName
	 */
	protected JDFAutoColorantControl(CoreDocumentImpl myOwnerDocument, String qualifiedName)
	{
		super(myOwnerDocument, qualifiedName);
	}

	/**
	 * Constructor for JDFAutoColorantControl
	 *
	 * @param myOwnerDocument
	 * @param myNamespaceURI
	 * @param qualifiedName
	 */
	protected JDFAutoColorantControl(CoreDocumentImpl myOwnerDocument, String myNamespaceURI, String qualifiedName)
	{
		super(myOwnerDocument, myNamespaceURI, qualifiedName);
	}

	/**
	 * Constructor for JDFAutoColorantControl
	 *
	 * @param myOwnerDocument
	 * @param myNamespaceURI
	 * @param qualifiedName
	 * @param myLocalName
	 */
	protected JDFAutoColorantControl(CoreDocumentImpl myOwnerDocument, String myNamespaceURI, String qualifiedName, String myLocalName)
	{
		super(myOwnerDocument, myNamespaceURI, qualifiedName, myLocalName);
	}

	/**
	 * @return true if ok
	 */
	@Override
	public boolean init()
	{
		final boolean bRet = super.init();
		setResourceClass(JDFResource.EnumResourceClass.Parameter);
		return bRet;
	}

	/**
	 * @return the resource Class
	 */
	@Override
	public EnumResourceClass getValidClass()
	{
		return JDFResource.EnumResourceClass.Parameter;
	}

	/**
	 * Enumeration strings for InternalColorModel
	 */

	public enum EInternalColorModel
	{
		Basic, Enhanced, Explicit;

		public static EInternalColorModel getEnum(String val)
		{
			return JavaEnumUtil.getEnumIgnoreCase(EInternalColorModel.class, val, null);
		}
	}

	/**
	 * Enumeration strings for InternalColorModel
	 */

	@SuppressWarnings("rawtypes")
	public static class EnumInternalColorModel extends ValuedEnum
	{
		private static final long serialVersionUID = 1L;
		private static int m_startValue = 0;

		protected EnumInternalColorModel(String name)
		{
			super(name, m_startValue++);
		}

		/**
		 * @param enumName the string to convert
		 * @return the enum
		 */
		public static EnumInternalColorModel getEnum(String enumName)
		{
			return (EnumInternalColorModel) getEnum(EnumInternalColorModel.class, enumName);
		}

		/**
		 * @param enumValue the integer to convert
		 * @return the enum
		 */
		public static EnumInternalColorModel getEnum(int enumValue)
		{
			return (EnumInternalColorModel) getEnum(EnumInternalColorModel.class, enumValue);
		}

		/**
		 * @return the map of enums
		 */
		public static Map getEnumMap()
		{
			return getEnumMap(EnumInternalColorModel.class);
		}

		/**
		 * @return the list of enums
		 */
		public static List getEnumList()
		{
			return getEnumList(EnumInternalColorModel.class);
		}

		/**
		 * @return the iterator
		 */
		public static Iterator iterator()
		{
			return iterator(EnumInternalColorModel.class);
		}

		/**  */
		public static final EnumInternalColorModel Basic = new EnumInternalColorModel("Basic");
		/**  */
		public static final EnumInternalColorModel Enhanced = new EnumInternalColorModel("Enhanced");
		/**  */
		public static final EnumInternalColorModel Explicit = new EnumInternalColorModel("Explicit");
	}

	/**
	 * Enumeration strings for MappingSelection
	 */

	public enum EMappingSelection
	{
		UsePDLValues, UseLocalPrinterValues, UseProcessColorValues;

		public static EMappingSelection getEnum(String val)
		{
			return JavaEnumUtil.getEnumIgnoreCase(EMappingSelection.class, val, null);
		}
	}

	/**
	 * Enumeration strings for MappingSelection
	 */

	@SuppressWarnings("rawtypes")
	public static class EnumMappingSelection extends ValuedEnum
	{
		private static final long serialVersionUID = 1L;
		private static int m_startValue = 0;

		protected EnumMappingSelection(String name)
		{
			super(name, m_startValue++);
		}

		/**
		 * @param enumName the string to convert
		 * @return the enum
		 */
		public static EnumMappingSelection getEnum(String enumName)
		{
			return (EnumMappingSelection) getEnum(EnumMappingSelection.class, enumName);
		}

		/**
		 * @param enumValue the integer to convert
		 * @return the enum
		 */
		public static EnumMappingSelection getEnum(int enumValue)
		{
			return (EnumMappingSelection) getEnum(EnumMappingSelection.class, enumValue);
		}

		/**
		 * @return the map of enums
		 */
		public static Map getEnumMap()
		{
			return getEnumMap(EnumMappingSelection.class);
		}

		/**
		 * @return the list of enums
		 */
		public static List getEnumList()
		{
			return getEnumList(EnumMappingSelection.class);
		}

		/**
		 * @return the iterator
		 */
		public static Iterator iterator()
		{
			return iterator(EnumMappingSelection.class);
		}

		/**  */
		public static final EnumMappingSelection UsePDLValues = new EnumMappingSelection("UsePDLValues");
		/**  */
		public static final EnumMappingSelection UseLocalPrinterValues = new EnumMappingSelection("UseLocalPrinterValues");
		/**  */
		public static final EnumMappingSelection UseProcessColorValues = new EnumMappingSelection("UseProcessColorValues");
	}

	/*
	 * ************************************************************************ Attribute getter / setter ************************************************************************
	 */

	/*
	 * --------------------------------------------------------------------- Methods for Attribute ForceSeparations
	 * ---------------------------------------------------------------------
	 */
	/**
	 * (36) set attribute ForceSeparations
	 *
	 * @param value the value to set the attribute to
	 */
	public void setForceSeparations(boolean value)
	{
		setAttribute(AttributeName.FORCESEPARATIONS, value, null);
	}

	/**
	 * (18) get boolean attribute ForceSeparations
	 *
	 * @return boolean the value of the attribute
	 */
	public boolean getForceSeparations()
	{
		return getBoolAttribute(AttributeName.FORCESEPARATIONS, null, false);
	}

	/*
	 * --------------------------------------------------------------------- Methods for Attribute InternalColorModel
	 * ---------------------------------------------------------------------
	 */
	/**
	 * (5) set attribute InternalColorModel
	 *
	 * @param enumVar the enumVar to set the attribute to
	 */
	public void setInternalColorModel(EInternalColorModel enumVar)
	{
		setAttribute(AttributeName.INTERNALCOLORMODEL, enumVar == null ? null : enumVar.name(), null);
	}

	/**
	 * (9) get attribute InternalColorModel
	 *
	 * @return the value of the attribute
	 */
	public EInternalColorModel getEInternalColorModel()
	{
		return EInternalColorModel.getEnum(getAttribute(AttributeName.INTERNALCOLORMODEL, null, null));
	}

	/*
	 * --------------------------------------------------------------------- Methods for Attribute InternalColorModel
	 * ---------------------------------------------------------------------
	 */
	/**
	 * (5) set attribute InternalColorModel
	 *
	 * @param enumVar the enumVar to set the attribute to
	 * @deprecated use java.lang.enum
	 */
	@Deprecated
	public void setInternalColorModel(EnumInternalColorModel enumVar)
	{
		setAttribute(AttributeName.INTERNALCOLORMODEL, enumVar == null ? null : enumVar.getName(), null);
	}

	/**
	 * (9) get attribute InternalColorModel
	 *
	 * @return the value of the attribute
	 */
	public EnumInternalColorModel getInternalColorModel()
	{
		return EnumInternalColorModel.getEnum(getAttribute(AttributeName.INTERNALCOLORMODEL, null, null));
	}

	/*
	 * --------------------------------------------------------------------- Methods for Attribute MappingSelection
	 * ---------------------------------------------------------------------
	 */
	/**
	 * (5) set attribute MappingSelection
	 *
	 * @param enumVar the enumVar to set the attribute to
	 */
	public void setMappingSelection(EMappingSelection enumVar)
	{
		setAttribute(AttributeName.MAPPINGSELECTION, enumVar == null ? null : enumVar.name(), null);
	}

	/**
	 * (9) get attribute MappingSelection
	 *
	 * @return the value of the attribute
	 */
	public EMappingSelection getEMappingSelection()
	{
		return EMappingSelection.getEnum(getAttribute(AttributeName.MAPPINGSELECTION, null, null));
	}

	/*
	 * --------------------------------------------------------------------- Methods for Attribute MappingSelection
	 * ---------------------------------------------------------------------
	 */
	/**
	 * (5) set attribute MappingSelection
	 *
	 * @param enumVar the enumVar to set the attribute to
	 * @deprecated use java.lang.enum
	 */
	@Deprecated
	public void setMappingSelection(EnumMappingSelection enumVar)
	{
		setAttribute(AttributeName.MAPPINGSELECTION, enumVar == null ? null : enumVar.getName(), null);
	}

	/**
	 * (9) get attribute MappingSelection
	 *
	 * @return the value of the attribute
	 */
	public EnumMappingSelection getMappingSelection()
	{
		return EnumMappingSelection.getEnum(getAttribute(AttributeName.MAPPINGSELECTION, null, null));
	}

	/*
	 * --------------------------------------------------------------------- Methods for Attribute ProcessColorModel
	 * ---------------------------------------------------------------------
	 */
	/**
	 * (36) set attribute ProcessColorModel
	 *
	 * @param value the value to set the attribute to
	 */
	public void setProcessColorModel(String value)
	{
		setAttribute(AttributeName.PROCESSCOLORMODEL, value, null);
	}

	/**
	 * (23) get String attribute ProcessColorModel
	 *
	 * @return the value of the attribute
	 */
	public String getProcessColorModel()
	{
		return getAttribute(AttributeName.PROCESSCOLORMODEL, null, JDFCoreConstants.EMPTYSTRING);
	}

	/*
	 * *********************************************************************** Element getter / setter ***********************************************************************
	 */

	/**
	 * (24) const get element ColorantAlias
	 *
	 * @return JDFColorantAlias the element
	 */
	public JDFColorantAlias getColorantAlias()
	{
		return (JDFColorantAlias) getElement(ElementName.COLORANTALIAS, null, 0);
	}

	/**
	 * (25) getCreateColorantAlias
	 * 
	 * @return JDFColorantAlias the element
	 */
	public JDFColorantAlias getCreateColorantAlias()
	{
		return (JDFColorantAlias) getCreateElement_JDFElement(ElementName.COLORANTALIAS, null, 0);
	}

	/**
	 * (26) getCreateColorantAlias
	 * 
	 * @param iSkip number of elements to skip
	 * @return JDFColorantAlias the element
	 */
	public JDFColorantAlias getCreateColorantAlias(int iSkip)
	{
		return (JDFColorantAlias) getCreateElement_JDFElement(ElementName.COLORANTALIAS, null, iSkip);
	}

	/**
	 * (27) const get element ColorantAlias
	 *
	 * @param iSkip number of elements to skip
	 * @return JDFColorantAlias the element default is getColorantAlias(0)
	 */
	public JDFColorantAlias getColorantAlias(int iSkip)
	{
		return (JDFColorantAlias) getElement(ElementName.COLORANTALIAS, null, iSkip);
	}

	/**
	 * Get all ColorantAlias from the current element
	 * 
	 * @return Collection<JDFColorantAlias>, null if none are available
	 */
	public Collection<JDFColorantAlias> getAllColorantAlias()
	{
		return getChildArrayByClass(JDFColorantAlias.class, false, 0);
	}

	/**
	 * (30) append element ColorantAlias
	 *
	 * @return JDFColorantAlias the element
	 */
	public JDFColorantAlias appendColorantAlias()
	{
		return (JDFColorantAlias) appendElement(ElementName.COLORANTALIAS, null);
	}

	/**
	 * (31) create inter-resource link to refTarget
	 *
	 * @param refTarget the element that is referenced
	 */
	public void refColorantAlias(JDFColorantAlias refTarget)
	{
		refElement(refTarget);
	}

	/**
	 * (24) const get element ColorantConvertProcess
	 *
	 * @return JDFSeparationList the element
	 */
	public JDFSeparationList getColorantConvertProcess()
	{
		return (JDFSeparationList) getElement(ElementName.COLORANTCONVERTPROCESS, null, 0);
	}

	/**
	 * (25) getCreateColorantConvertProcess
	 * 
	 * @return JDFSeparationList the element
	 */
	public JDFSeparationList getCreateColorantConvertProcess()
	{
		return (JDFSeparationList) getCreateElement_JDFElement(ElementName.COLORANTCONVERTPROCESS, null, 0);
	}

	/**
	 * (29) append element ColorantConvertProcess
	 *
	 * @return JDFSeparationList the element @ if the element already exists
	 */
	public JDFSeparationList appendColorantConvertProcess()
	{
		return (JDFSeparationList) appendElementN(ElementName.COLORANTCONVERTPROCESS, 1, null);
	}

	/**
	 * (24) const get element ColorantOrder
	 *
	 * @return JDFSeparationList the element
	 */
	public JDFSeparationList getColorantOrder()
	{
		return (JDFSeparationList) getElement(ElementName.COLORANTORDER, null, 0);
	}

	/**
	 * (25) getCreateColorantOrder
	 * 
	 * @return JDFSeparationList the element
	 */
	public JDFSeparationList getCreateColorantOrder()
	{
		return (JDFSeparationList) getCreateElement_JDFElement(ElementName.COLORANTORDER, null, 0);
	}

	/**
	 * (29) append element ColorantOrder
	 *
	 * @return JDFSeparationList the element @ if the element already exists
	 */
	public JDFSeparationList appendColorantOrder()
	{
		return (JDFSeparationList) appendElementN(ElementName.COLORANTORDER, 1, null);
	}

	/**
	 * (24) const get element ColorantParams
	 *
	 * @return JDFSeparationList the element
	 */
	public JDFSeparationList getColorantParams()
	{
		return (JDFSeparationList) getElement(ElementName.COLORANTPARAMS, null, 0);
	}

	/**
	 * (25) getCreateColorantParams
	 * 
	 * @return JDFSeparationList the element
	 */
	public JDFSeparationList getCreateColorantParams()
	{
		return (JDFSeparationList) getCreateElement_JDFElement(ElementName.COLORANTPARAMS, null, 0);
	}

	/**
	 * (29) append element ColorantParams
	 *
	 * @return JDFSeparationList the element @ if the element already exists
	 */
	public JDFSeparationList appendColorantParams()
	{
		return (JDFSeparationList) appendElementN(ElementName.COLORANTPARAMS, 1, null);
	}

	/**
	 * (24) const get element ColorPool
	 *
	 * @return JDFColorPool the element
	 */
	public JDFColorPool getColorPool()
	{
		return (JDFColorPool) getElement(ElementName.COLORPOOL, null, 0);
	}

	/**
	 * (25) getCreateColorPool
	 * 
	 * @return JDFColorPool the element
	 */
	public JDFColorPool getCreateColorPool()
	{
		return (JDFColorPool) getCreateElement_JDFElement(ElementName.COLORPOOL, null, 0);
	}

	/**
	 * (29) append element ColorPool
	 *
	 * @return JDFColorPool the element @ if the element already exists
	 */
	public JDFColorPool appendColorPool()
	{
		return (JDFColorPool) appendElementN(ElementName.COLORPOOL, 1, null);
	}

	/**
	 * (31) create inter-resource link to refTarget
	 *
	 * @param refTarget the element that is referenced
	 */
	public void refColorPool(JDFColorPool refTarget)
	{
		refElement(refTarget);
	}

	/**
	 * (24) const get element ColorSpaceSubstitute
	 *
	 * @return JDFColorSpaceSubstitute the element
	 */
	public JDFColorSpaceSubstitute getColorSpaceSubstitute()
	{
		return (JDFColorSpaceSubstitute) getElement(ElementName.COLORSPACESUBSTITUTE, null, 0);
	}

	/**
	 * (25) getCreateColorSpaceSubstitute
	 * 
	 * @return JDFColorSpaceSubstitute the element
	 */
	public JDFColorSpaceSubstitute getCreateColorSpaceSubstitute()
	{
		return (JDFColorSpaceSubstitute) getCreateElement_JDFElement(ElementName.COLORSPACESUBSTITUTE, null, 0);
	}

	/**
	 * (26) getCreateColorSpaceSubstitute
	 * 
	 * @param iSkip number of elements to skip
	 * @return JDFColorSpaceSubstitute the element
	 */
	public JDFColorSpaceSubstitute getCreateColorSpaceSubstitute(int iSkip)
	{
		return (JDFColorSpaceSubstitute) getCreateElement_JDFElement(ElementName.COLORSPACESUBSTITUTE, null, iSkip);
	}

	/**
	 * (27) const get element ColorSpaceSubstitute
	 *
	 * @param iSkip number of elements to skip
	 * @return JDFColorSpaceSubstitute the element default is getColorSpaceSubstitute(0)
	 */
	public JDFColorSpaceSubstitute getColorSpaceSubstitute(int iSkip)
	{
		return (JDFColorSpaceSubstitute) getElement(ElementName.COLORSPACESUBSTITUTE, null, iSkip);
	}

	/**
	 * Get all ColorSpaceSubstitute from the current element
	 * 
	 * @return Collection<JDFColorSpaceSubstitute>, null if none are available
	 */
	public Collection<JDFColorSpaceSubstitute> getAllColorSpaceSubstitute()
	{
		return getChildArrayByClass(JDFColorSpaceSubstitute.class, false, 0);
	}

	/**
	 * (30) append element ColorSpaceSubstitute
	 *
	 * @return JDFColorSpaceSubstitute the element
	 */
	public JDFColorSpaceSubstitute appendColorSpaceSubstitute()
	{
		return (JDFColorSpaceSubstitute) appendElement(ElementName.COLORSPACESUBSTITUTE, null);
	}

	/**
	 * (24) const get element DeviceColorantOrder
	 *
	 * @return JDFSeparationList the element
	 */
	public JDFSeparationList getDeviceColorantOrder()
	{
		return (JDFSeparationList) getElement(ElementName.DEVICECOLORANTORDER, null, 0);
	}

	/**
	 * (25) getCreateDeviceColorantOrder
	 * 
	 * @return JDFSeparationList the element
	 */
	public JDFSeparationList getCreateDeviceColorantOrder()
	{
		return (JDFSeparationList) getCreateElement_JDFElement(ElementName.DEVICECOLORANTORDER, null, 0);
	}

	/**
	 * (29) append element DeviceColorantOrder
	 *
	 * @return JDFSeparationList the element @ if the element already exists
	 */
	public JDFSeparationList appendDeviceColorantOrder()
	{
		return (JDFSeparationList) appendElementN(ElementName.DEVICECOLORANTORDER, 1, null);
	}

	/**
	 * (24) const get element DeviceNSpace
	 *
	 * @return JDFDeviceNSpace the element
	 */
	public JDFDeviceNSpace getDeviceNSpace()
	{
		return (JDFDeviceNSpace) getElement(ElementName.DEVICENSPACE, null, 0);
	}

	/**
	 * (25) getCreateDeviceNSpace
	 * 
	 * @return JDFDeviceNSpace the element
	 */
	public JDFDeviceNSpace getCreateDeviceNSpace()
	{
		return (JDFDeviceNSpace) getCreateElement_JDFElement(ElementName.DEVICENSPACE, null, 0);
	}

	/**
	 * (26) getCreateDeviceNSpace
	 * 
	 * @param iSkip number of elements to skip
	 * @return JDFDeviceNSpace the element
	 */
	public JDFDeviceNSpace getCreateDeviceNSpace(int iSkip)
	{
		return (JDFDeviceNSpace) getCreateElement_JDFElement(ElementName.DEVICENSPACE, null, iSkip);
	}

	/**
	 * (27) const get element DeviceNSpace
	 *
	 * @param iSkip number of elements to skip
	 * @return JDFDeviceNSpace the element default is getDeviceNSpace(0)
	 */
	public JDFDeviceNSpace getDeviceNSpace(int iSkip)
	{
		return (JDFDeviceNSpace) getElement(ElementName.DEVICENSPACE, null, iSkip);
	}

	/**
	 * Get all DeviceNSpace from the current element
	 * 
	 * @return Collection<JDFDeviceNSpace>, null if none are available
	 */
	public Collection<JDFDeviceNSpace> getAllDeviceNSpace()
	{
		return getChildArrayByClass(JDFDeviceNSpace.class, false, 0);
	}

	/**
	 * (30) append element DeviceNSpace
	 *
	 * @return JDFDeviceNSpace the element
	 */
	public JDFDeviceNSpace appendDeviceNSpace()
	{
		return (JDFDeviceNSpace) appendElement(ElementName.DEVICENSPACE, null);
	}

}
