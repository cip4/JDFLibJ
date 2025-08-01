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
import org.cip4.jdflib.resource.JDFResource;
import org.cip4.jdflib.resource.process.JDFFileSpec;
import org.cip4.jdflib.resource.process.prepress.JDFColorSpaceConversionOp;
import org.cip4.jdflib.util.JavaEnumUtil;

/**
 *****************************************************************************
 * class JDFAutoColorSpaceConversionParams : public JDFResource
 *****************************************************************************
 * 
 */

public abstract class JDFAutoColorSpaceConversionParams extends JDFResource
{

	private static final long serialVersionUID = 1L;

	private static AtrInfoTable[] atrInfoTable = new AtrInfoTable[3];
	static
	{
		atrInfoTable[0] = new AtrInfoTable(AttributeName.ICCPROFILEUSAGE, 0x3333333311l, AttributeInfo.EnumAttributeType.enumeration, EnumICCProfileUsage.getEnum(0), "UsePDL");
		atrInfoTable[1] = new AtrInfoTable(AttributeName.COLORMANAGEMENTSYSTEM, 0x3333333333l, AttributeInfo.EnumAttributeType.string, null, null);
		atrInfoTable[2] = new AtrInfoTable(AttributeName.CONVERTDEVINDEPCOLORS, 0x4444444443l, AttributeInfo.EnumAttributeType.boolean_, null, null);
	}

	@Override
	protected AttributeInfo getTheAttributeInfo()
	{
		return super.getTheAttributeInfo().updateReplace(atrInfoTable);
	}

	private static ElemInfoTable[] elemInfoTable = new ElemInfoTable[2];
	static
	{
		elemInfoTable[0] = new ElemInfoTable(ElementName.FILESPEC, 0x3333333333l);
		elemInfoTable[1] = new ElemInfoTable(ElementName.COLORSPACECONVERSIONOP, 0x3333333333l);
	}

	@Override
	protected ElementInfo getTheElementInfo()
	{
		return super.getTheElementInfo().updateReplace(elemInfoTable);
	}

	/**
	 * Constructor for JDFAutoColorSpaceConversionParams
	 *
	 * @param myOwnerDocument
	 * @param qualifiedName
	 */
	protected JDFAutoColorSpaceConversionParams(CoreDocumentImpl myOwnerDocument, String qualifiedName)
	{
		super(myOwnerDocument, qualifiedName);
	}

	/**
	 * Constructor for JDFAutoColorSpaceConversionParams
	 *
	 * @param myOwnerDocument
	 * @param myNamespaceURI
	 * @param qualifiedName
	 */
	protected JDFAutoColorSpaceConversionParams(CoreDocumentImpl myOwnerDocument, String myNamespaceURI, String qualifiedName)
	{
		super(myOwnerDocument, myNamespaceURI, qualifiedName);
	}

	/**
	 * Constructor for JDFAutoColorSpaceConversionParams
	 *
	 * @param myOwnerDocument
	 * @param myNamespaceURI
	 * @param qualifiedName
	 * @param myLocalName
	 */
	protected JDFAutoColorSpaceConversionParams(CoreDocumentImpl myOwnerDocument, String myNamespaceURI, String qualifiedName, String myLocalName)
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
	 * Enumeration strings for ICCProfileUsage
	 */

	public enum EICCProfileUsage
	{
		UsePDL, UseSupplied;

		public static EICCProfileUsage getEnum(String val)
		{
			return JavaEnumUtil.getEnumIgnoreCase(EICCProfileUsage.class, val, EICCProfileUsage.UsePDL);
		}
	}

	/**
	 * Enumeration strings for ICCProfileUsage
	 */

	@SuppressWarnings("rawtypes")
	public static class EnumICCProfileUsage extends ValuedEnum
	{
		private static final long serialVersionUID = 1L;
		private static int m_startValue = 0;

		protected EnumICCProfileUsage(String name)
		{
			super(name, m_startValue++);
		}

		/**
		 * @param enumName the string to convert
		 * @return the enum
		 */
		public static EnumICCProfileUsage getEnum(String enumName)
		{
			return (EnumICCProfileUsage) getEnum(EnumICCProfileUsage.class, enumName);
		}

		/**
		 * @param enumValue the integer to convert
		 * @return the enum
		 */
		public static EnumICCProfileUsage getEnum(int enumValue)
		{
			return (EnumICCProfileUsage) getEnum(EnumICCProfileUsage.class, enumValue);
		}

		/**
		 * @return the map of enums
		 */
		public static Map getEnumMap()
		{
			return getEnumMap(EnumICCProfileUsage.class);
		}

		/**
		 * @return the list of enums
		 */
		public static List getEnumList()
		{
			return getEnumList(EnumICCProfileUsage.class);
		}

		/**
		 * @return the iterator
		 */
		public static Iterator iterator()
		{
			return iterator(EnumICCProfileUsage.class);
		}

		/**  */
		public static final EnumICCProfileUsage UsePDL = new EnumICCProfileUsage("UsePDL");
		/**  */
		public static final EnumICCProfileUsage UseSupplied = new EnumICCProfileUsage("UseSupplied");
	}

	/*
	 * ************************************************************************ Attribute getter / setter ************************************************************************
	 */

	/*
	 * --------------------------------------------------------------------- Methods for Attribute ICCProfileUsage
	 * ---------------------------------------------------------------------
	 */
	/**
	 * (5) set attribute ICCProfileUsage
	 *
	 * @param enumVar the enumVar to set the attribute to
	 */
	public void setICCProfileUsage(EICCProfileUsage enumVar)
	{
		setAttribute(AttributeName.ICCPROFILEUSAGE, enumVar == null ? null : enumVar.name(), null);
	}

	/**
	 * (9) get attribute ICCProfileUsage
	 *
	 * @return the value of the attribute
	 */
	public EICCProfileUsage getEICCProfileUsage()
	{
		return EICCProfileUsage.getEnum(getAttribute(AttributeName.ICCPROFILEUSAGE, null, "UsePDL"));
	}

	/*
	 * --------------------------------------------------------------------- Methods for Attribute ICCProfileUsage
	 * ---------------------------------------------------------------------
	 */
	/**
	 * (5) set attribute ICCProfileUsage
	 *
	 * @param enumVar the enumVar to set the attribute to
	 * @deprecated use java.lang.enum
	 */
	@Deprecated
	public void setICCProfileUsage(EnumICCProfileUsage enumVar)
	{
		setAttribute(AttributeName.ICCPROFILEUSAGE, enumVar == null ? null : enumVar.getName(), null);
	}

	/**
	 * (9) get attribute ICCProfileUsage
	 *
	 * @return the value of the attribute
	 */
	public EnumICCProfileUsage getICCProfileUsage()
	{
		return EnumICCProfileUsage.getEnum(getAttribute(AttributeName.ICCPROFILEUSAGE, null, "UsePDL"));
	}

	/*
	 * --------------------------------------------------------------------- Methods for Attribute ColorManagementSystem
	 * ---------------------------------------------------------------------
	 */
	/**
	 * (36) set attribute ColorManagementSystem
	 *
	 * @param value the value to set the attribute to
	 */
	public void setColorManagementSystem(String value)
	{
		setAttribute(AttributeName.COLORMANAGEMENTSYSTEM, value, null);
	}

	/**
	 * (23) get String attribute ColorManagementSystem
	 *
	 * @return the value of the attribute
	 */
	public String getColorManagementSystem()
	{
		return getAttribute(AttributeName.COLORMANAGEMENTSYSTEM, null, JDFCoreConstants.EMPTYSTRING);
	}

	/*
	 * --------------------------------------------------------------------- Methods for Attribute ConvertDevIndepColors
	 * ---------------------------------------------------------------------
	 */
	/**
	 * (36) set attribute ConvertDevIndepColors
	 *
	 * @param value the value to set the attribute to
	 */
	public void setConvertDevIndepColors(boolean value)
	{
		setAttribute(AttributeName.CONVERTDEVINDEPCOLORS, value, null);
	}

	/**
	 * (18) get boolean attribute ConvertDevIndepColors
	 *
	 * @return boolean the value of the attribute
	 */
	public boolean getConvertDevIndepColors()
	{
		return getBoolAttribute(AttributeName.CONVERTDEVINDEPCOLORS, null, false);
	}

	/*
	 * *********************************************************************** Element getter / setter ***********************************************************************
	 */

	/**
	 * (24) const get element FileSpec
	 *
	 * @return JDFFileSpec the element
	 */
	public JDFFileSpec getFileSpec()
	{
		return (JDFFileSpec) getElement(ElementName.FILESPEC, null, 0);
	}

	/**
	 * (25) getCreateFileSpec
	 * 
	 * @return JDFFileSpec the element
	 */
	public JDFFileSpec getCreateFileSpec()
	{
		return (JDFFileSpec) getCreateElement_JDFElement(ElementName.FILESPEC, null, 0);
	}

	/**
	 * (26) getCreateFileSpec
	 * 
	 * @param iSkip number of elements to skip
	 * @return JDFFileSpec the element
	 */
	public JDFFileSpec getCreateFileSpec(int iSkip)
	{
		return (JDFFileSpec) getCreateElement_JDFElement(ElementName.FILESPEC, null, iSkip);
	}

	/**
	 * (27) const get element FileSpec
	 *
	 * @param iSkip number of elements to skip
	 * @return JDFFileSpec the element default is getFileSpec(0)
	 */
	public JDFFileSpec getFileSpec(int iSkip)
	{
		return (JDFFileSpec) getElement(ElementName.FILESPEC, null, iSkip);
	}

	/**
	 * Get all FileSpec from the current element
	 * 
	 * @return Collection<JDFFileSpec>, null if none are available
	 */
	public Collection<JDFFileSpec> getAllFileSpec()
	{
		return getChildArrayByClass(JDFFileSpec.class, false, 0);
	}

	/**
	 * (30) append element FileSpec
	 *
	 * @return JDFFileSpec the element
	 */
	public JDFFileSpec appendFileSpec()
	{
		return (JDFFileSpec) appendElement(ElementName.FILESPEC, null);
	}

	/**
	 * (31) create inter-resource link to refTarget
	 *
	 * @param refTarget the element that is referenced
	 */
	public void refFileSpec(JDFFileSpec refTarget)
	{
		refElement(refTarget);
	}

	/**
	 * (24) const get element ColorSpaceConversionOp
	 *
	 * @return JDFColorSpaceConversionOp the element
	 */
	public JDFColorSpaceConversionOp getColorSpaceConversionOp()
	{
		return (JDFColorSpaceConversionOp) getElement(ElementName.COLORSPACECONVERSIONOP, null, 0);
	}

	/**
	 * (25) getCreateColorSpaceConversionOp
	 * 
	 * @return JDFColorSpaceConversionOp the element
	 */
	public JDFColorSpaceConversionOp getCreateColorSpaceConversionOp()
	{
		return (JDFColorSpaceConversionOp) getCreateElement_JDFElement(ElementName.COLORSPACECONVERSIONOP, null, 0);
	}

	/**
	 * (26) getCreateColorSpaceConversionOp
	 * 
	 * @param iSkip number of elements to skip
	 * @return JDFColorSpaceConversionOp the element
	 */
	public JDFColorSpaceConversionOp getCreateColorSpaceConversionOp(int iSkip)
	{
		return (JDFColorSpaceConversionOp) getCreateElement_JDFElement(ElementName.COLORSPACECONVERSIONOP, null, iSkip);
	}

	/**
	 * (27) const get element ColorSpaceConversionOp
	 *
	 * @param iSkip number of elements to skip
	 * @return JDFColorSpaceConversionOp the element default is getColorSpaceConversionOp(0)
	 */
	public JDFColorSpaceConversionOp getColorSpaceConversionOp(int iSkip)
	{
		return (JDFColorSpaceConversionOp) getElement(ElementName.COLORSPACECONVERSIONOP, null, iSkip);
	}

	/**
	 * Get all ColorSpaceConversionOp from the current element
	 * 
	 * @return Collection<JDFColorSpaceConversionOp>, null if none are available
	 */
	public Collection<JDFColorSpaceConversionOp> getAllColorSpaceConversionOp()
	{
		return getChildArrayByClass(JDFColorSpaceConversionOp.class, false, 0);
	}

	/**
	 * (30) append element ColorSpaceConversionOp
	 *
	 * @return JDFColorSpaceConversionOp the element
	 */
	public JDFColorSpaceConversionOp appendColorSpaceConversionOp()
	{
		return (JDFColorSpaceConversionOp) appendElement(ElementName.COLORSPACECONVERSIONOP, null);
	}

}
