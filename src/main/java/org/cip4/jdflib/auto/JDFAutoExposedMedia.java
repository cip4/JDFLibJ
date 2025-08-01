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
import org.cip4.jdflib.datatypes.JDFIntegerRangeList;
import org.cip4.jdflib.datatypes.JDFXYPair;
import org.cip4.jdflib.resource.JDFPageList;
import org.cip4.jdflib.resource.JDFResource;
import org.cip4.jdflib.resource.process.JDFContact;
import org.cip4.jdflib.resource.process.JDFFileSpec;
import org.cip4.jdflib.resource.process.JDFIdentificationField;
import org.cip4.jdflib.resource.process.JDFMedia;
import org.cip4.jdflib.resource.process.prepress.JDFScreeningParams;
import org.cip4.jdflib.util.JavaEnumUtil;

/**
 *****************************************************************************
 * class JDFAutoExposedMedia : public JDFResource
 *****************************************************************************
 * 
 */

public abstract class JDFAutoExposedMedia extends JDFResource
{

	private static final long serialVersionUID = 1L;

	private static AtrInfoTable[] atrInfoTable = new AtrInfoTable[9];
	static
	{
		atrInfoTable[0] = new AtrInfoTable(AttributeName.POLARITY, 0x3333333333l, AttributeInfo.EnumAttributeType.boolean_, null, "true");
		atrInfoTable[1] = new AtrInfoTable(AttributeName.COLORTYPE, 0x3333333333l, AttributeInfo.EnumAttributeType.enumeration, EnumColorType.getEnum(0), null);
		atrInfoTable[2] = new AtrInfoTable(AttributeName.PAGELISTINDEX, 0x3333333111l, AttributeInfo.EnumAttributeType.IntegerRangeList, null, null);
		atrInfoTable[3] = new AtrInfoTable(AttributeName.PLATETYPE, 0x3333333111l, AttributeInfo.EnumAttributeType.enumeration, EnumPlateType.getEnum(0), null);
		atrInfoTable[4] = new AtrInfoTable(AttributeName.PROOFNAME, 0x3333333311l, AttributeInfo.EnumAttributeType.string, null, null);
		atrInfoTable[5] = new AtrInfoTable(AttributeName.PROOFQUALITY, 0x3333333333l, AttributeInfo.EnumAttributeType.enumeration, EnumProofQuality.getEnum(0), null);
		atrInfoTable[6] = new AtrInfoTable(AttributeName.PROOFTYPE, 0x3333333333l, AttributeInfo.EnumAttributeType.enumeration, EnumProofType.getEnum(0), null);
		atrInfoTable[7] = new AtrInfoTable(AttributeName.PUNCHTYPE, 0x3333333333l, AttributeInfo.EnumAttributeType.string, null, null);
		atrInfoTable[8] = new AtrInfoTable(AttributeName.RESOLUTION, 0x3333333333l, AttributeInfo.EnumAttributeType.XYPair, null, null);
	}

	@Override
	protected AttributeInfo getTheAttributeInfo()
	{
		return super.getTheAttributeInfo().updateReplace(atrInfoTable);
	}

	private static ElemInfoTable[] elemInfoTable = new ElemInfoTable[6];
	static
	{
		elemInfoTable[0] = new ElemInfoTable(ElementName.FILESPEC, 0x3333333333l);
		elemInfoTable[1] = new ElemInfoTable(ElementName.MEDIA, 0x5555555555l);
		elemInfoTable[2] = new ElemInfoTable(ElementName.PAGELIST, 0x6666666111l);
		elemInfoTable[3] = new ElemInfoTable(ElementName.SCREENINGPARAMS, 0x6666666666l);
		elemInfoTable[4] = new ElemInfoTable(ElementName.CONTACT, 0x3333333333l);
		elemInfoTable[5] = new ElemInfoTable(ElementName.IDENTIFICATIONFIELD, 0x3333333333l);
	}

	@Override
	protected ElementInfo getTheElementInfo()
	{
		return super.getTheElementInfo().updateReplace(elemInfoTable);
	}

	/**
	 * Constructor for JDFAutoExposedMedia
	 *
	 * @param myOwnerDocument
	 * @param qualifiedName
	 */
	protected JDFAutoExposedMedia(CoreDocumentImpl myOwnerDocument, String qualifiedName)
	{
		super(myOwnerDocument, qualifiedName);
	}

	/**
	 * Constructor for JDFAutoExposedMedia
	 *
	 * @param myOwnerDocument
	 * @param myNamespaceURI
	 * @param qualifiedName
	 */
	protected JDFAutoExposedMedia(CoreDocumentImpl myOwnerDocument, String myNamespaceURI, String qualifiedName)
	{
		super(myOwnerDocument, myNamespaceURI, qualifiedName);
	}

	/**
	 * Constructor for JDFAutoExposedMedia
	 *
	 * @param myOwnerDocument
	 * @param myNamespaceURI
	 * @param qualifiedName
	 * @param myLocalName
	 */
	protected JDFAutoExposedMedia(CoreDocumentImpl myOwnerDocument, String myNamespaceURI, String qualifiedName, String myLocalName)
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
		setResourceClass(JDFResource.EnumResourceClass.Handling);
		return bRet;
	}

	/**
	 * @return the resource Class
	 */
	@Override
	public EnumResourceClass getValidClass()
	{
		return JDFResource.EnumResourceClass.Handling;
	}

	/**
	 * Enumeration strings for ColorType
	 */

	public enum EColorType
	{
		Color, GrayScale, Monochrome;

		public static EColorType getEnum(String val)
		{
			return JavaEnumUtil.getEnumIgnoreCase(EColorType.class, val, null);
		}
	}

	/**
	 * Enumeration strings for ColorType
	 */

	@SuppressWarnings("rawtypes")
	public static class EnumColorType extends ValuedEnum
	{
		private static final long serialVersionUID = 1L;
		private static int m_startValue = 0;

		protected EnumColorType(String name)
		{
			super(name, m_startValue++);
		}

		/**
		 * @param enumName the string to convert
		 * @return the enum
		 */
		public static EnumColorType getEnum(String enumName)
		{
			return (EnumColorType) getEnum(EnumColorType.class, enumName);
		}

		/**
		 * @param enumValue the integer to convert
		 * @return the enum
		 */
		public static EnumColorType getEnum(int enumValue)
		{
			return (EnumColorType) getEnum(EnumColorType.class, enumValue);
		}

		/**
		 * @return the map of enums
		 */
		public static Map getEnumMap()
		{
			return getEnumMap(EnumColorType.class);
		}

		/**
		 * @return the list of enums
		 */
		public static List getEnumList()
		{
			return getEnumList(EnumColorType.class);
		}

		/**
		 * @return the iterator
		 */
		public static Iterator iterator()
		{
			return iterator(EnumColorType.class);
		}

		/**  */
		public static final EnumColorType Color = new EnumColorType("Color");
		/**  */
		public static final EnumColorType GrayScale = new EnumColorType("GrayScale");
		/**  */
		public static final EnumColorType Monochrome = new EnumColorType("Monochrome");
	}

	/**
	 * Enumeration strings for PlateType
	 */

	public enum EPlateType
	{
		Exposed, Dummy;

		public static EPlateType getEnum(String val)
		{
			return JavaEnumUtil.getEnumIgnoreCase(EPlateType.class, val, null);
		}
	}

	/**
	 * Enumeration strings for PlateType
	 */

	@SuppressWarnings("rawtypes")
	public static class EnumPlateType extends ValuedEnum
	{
		private static final long serialVersionUID = 1L;
		private static int m_startValue = 0;

		protected EnumPlateType(String name)
		{
			super(name, m_startValue++);
		}

		/**
		 * @param enumName the string to convert
		 * @return the enum
		 */
		public static EnumPlateType getEnum(String enumName)
		{
			return (EnumPlateType) getEnum(EnumPlateType.class, enumName);
		}

		/**
		 * @param enumValue the integer to convert
		 * @return the enum
		 */
		public static EnumPlateType getEnum(int enumValue)
		{
			return (EnumPlateType) getEnum(EnumPlateType.class, enumValue);
		}

		/**
		 * @return the map of enums
		 */
		public static Map getEnumMap()
		{
			return getEnumMap(EnumPlateType.class);
		}

		/**
		 * @return the list of enums
		 */
		public static List getEnumList()
		{
			return getEnumList(EnumPlateType.class);
		}

		/**
		 * @return the iterator
		 */
		public static Iterator iterator()
		{
			return iterator(EnumPlateType.class);
		}

		/**  */
		public static final EnumPlateType Exposed = new EnumPlateType("Exposed");
		/**  */
		public static final EnumPlateType Dummy = new EnumPlateType("Dummy");
	}

	/**
	 * Enumeration strings for ProofQuality
	 */

	public enum EProofQuality
	{
		None, Halftone, Contone, Conceptual;

		public static EProofQuality getEnum(String val)
		{
			return JavaEnumUtil.getEnumIgnoreCase(EProofQuality.class, val, null);
		}
	}

	/**
	 * Enumeration strings for ProofQuality
	 */

	@SuppressWarnings("rawtypes")
	public static class EnumProofQuality extends ValuedEnum
	{
		private static final long serialVersionUID = 1L;
		private static int m_startValue = 0;

		protected EnumProofQuality(String name)
		{
			super(name, m_startValue++);
		}

		/**
		 * @param enumName the string to convert
		 * @return the enum
		 */
		public static EnumProofQuality getEnum(String enumName)
		{
			return (EnumProofQuality) getEnum(EnumProofQuality.class, enumName);
		}

		/**
		 * @param enumValue the integer to convert
		 * @return the enum
		 */
		public static EnumProofQuality getEnum(int enumValue)
		{
			return (EnumProofQuality) getEnum(EnumProofQuality.class, enumValue);
		}

		/**
		 * @return the map of enums
		 */
		public static Map getEnumMap()
		{
			return getEnumMap(EnumProofQuality.class);
		}

		/**
		 * @return the list of enums
		 */
		public static List getEnumList()
		{
			return getEnumList(EnumProofQuality.class);
		}

		/**
		 * @return the iterator
		 */
		public static Iterator iterator()
		{
			return iterator(EnumProofQuality.class);
		}

		/**  */
		public static final EnumProofQuality None = new EnumProofQuality("None");
		/**  */
		public static final EnumProofQuality Halftone = new EnumProofQuality("Halftone");
		/**  */
		public static final EnumProofQuality Contone = new EnumProofQuality("Contone");
		/**  */
		public static final EnumProofQuality Conceptual = new EnumProofQuality("Conceptual");
	}

	/**
	 * Enumeration strings for ProofType
	 */

	public enum EProofType
	{
		None, Imposition, Page;

		public static EProofType getEnum(String val)
		{
			return JavaEnumUtil.getEnumIgnoreCase(EProofType.class, val, null);
		}
	}

	/**
	 * Enumeration strings for ProofType
	 */

	@SuppressWarnings("rawtypes")
	public static class EnumProofType extends ValuedEnum
	{
		private static final long serialVersionUID = 1L;
		private static int m_startValue = 0;

		protected EnumProofType(String name)
		{
			super(name, m_startValue++);
		}

		/**
		 * @param enumName the string to convert
		 * @return the enum
		 */
		public static EnumProofType getEnum(String enumName)
		{
			return (EnumProofType) getEnum(EnumProofType.class, enumName);
		}

		/**
		 * @param enumValue the integer to convert
		 * @return the enum
		 */
		public static EnumProofType getEnum(int enumValue)
		{
			return (EnumProofType) getEnum(EnumProofType.class, enumValue);
		}

		/**
		 * @return the map of enums
		 */
		public static Map getEnumMap()
		{
			return getEnumMap(EnumProofType.class);
		}

		/**
		 * @return the list of enums
		 */
		public static List getEnumList()
		{
			return getEnumList(EnumProofType.class);
		}

		/**
		 * @return the iterator
		 */
		public static Iterator iterator()
		{
			return iterator(EnumProofType.class);
		}

		/**  */
		public static final EnumProofType None = new EnumProofType("None");
		/**  */
		public static final EnumProofType Imposition = new EnumProofType("Imposition");
		/**  */
		public static final EnumProofType Page = new EnumProofType("Page");
	}

	/*
	 * ************************************************************************ Attribute getter / setter ************************************************************************
	 */

	/*
	 * --------------------------------------------------------------------- Methods for Attribute Polarity ---------------------------------------------------------------------
	 */
	/**
	 * (36) set attribute Polarity
	 *
	 * @param value the value to set the attribute to
	 */
	public void setPolarity(boolean value)
	{
		setAttribute(AttributeName.POLARITY, value, null);
	}

	/**
	 * (18) get boolean attribute Polarity
	 *
	 * @return boolean the value of the attribute
	 */
	public boolean getPolarity()
	{
		return getBoolAttribute(AttributeName.POLARITY, null, true);
	}

	/*
	 * --------------------------------------------------------------------- Methods for Attribute ColorType ---------------------------------------------------------------------
	 */
	/**
	 * (5) set attribute ColorType
	 *
	 * @param enumVar the enumVar to set the attribute to
	 */
	public void setColorType(EColorType enumVar)
	{
		setAttribute(AttributeName.COLORTYPE, enumVar == null ? null : enumVar.name(), null);
	}

	/**
	 * (9) get attribute ColorType
	 *
	 * @return the value of the attribute
	 */
	public EColorType getEColorType()
	{
		return EColorType.getEnum(getAttribute(AttributeName.COLORTYPE, null, null));
	}

	/*
	 * --------------------------------------------------------------------- Methods for Attribute ColorType ---------------------------------------------------------------------
	 */
	/**
	 * (5) set attribute ColorType
	 *
	 * @param enumVar the enumVar to set the attribute to
	 * @deprecated use java.lang.enum
	 */
	@Deprecated
	public void setColorType(EnumColorType enumVar)
	{
		setAttribute(AttributeName.COLORTYPE, enumVar == null ? null : enumVar.getName(), null);
	}

	/**
	 * (9) get attribute ColorType
	 *
	 * @return the value of the attribute
	 */
	public EnumColorType getColorType()
	{
		return EnumColorType.getEnum(getAttribute(AttributeName.COLORTYPE, null, null));
	}

	/*
	 * --------------------------------------------------------------------- Methods for Attribute PageListIndex
	 * ---------------------------------------------------------------------
	 */
	/**
	 * (36) set attribute PageListIndex
	 *
	 * @param value the value to set the attribute to
	 */
	public void setPageListIndex(JDFIntegerRangeList value)
	{
		setAttribute(AttributeName.PAGELISTINDEX, value, null);
	}

	/**
	 * (20) get JDFIntegerRangeList attribute PageListIndex
	 *
	 * @return JDFIntegerRangeList the value of the attribute, null if a the attribute value is not a valid to create a JDFIntegerRangeList
	 */
	public JDFIntegerRangeList getPageListIndex()
	{
		final String strAttrName = getAttribute(AttributeName.PAGELISTINDEX, null, null);
		final JDFIntegerRangeList nPlaceHolder = JDFIntegerRangeList.createIntegerRangeList(strAttrName);
		return nPlaceHolder;
	}

	/*
	 * --------------------------------------------------------------------- Methods for Attribute PlateType ---------------------------------------------------------------------
	 */
	/**
	 * (5) set attribute PlateType
	 *
	 * @param enumVar the enumVar to set the attribute to
	 */
	public void setPlateType(EPlateType enumVar)
	{
		setAttribute(AttributeName.PLATETYPE, enumVar == null ? null : enumVar.name(), null);
	}

	/**
	 * (9) get attribute PlateType
	 *
	 * @return the value of the attribute
	 */
	public EPlateType getEPlateType()
	{
		return EPlateType.getEnum(getAttribute(AttributeName.PLATETYPE, null, null));
	}

	/*
	 * --------------------------------------------------------------------- Methods for Attribute PlateType ---------------------------------------------------------------------
	 */
	/**
	 * (5) set attribute PlateType
	 *
	 * @param enumVar the enumVar to set the attribute to
	 * @deprecated use java.lang.enum
	 */
	@Deprecated
	public void setPlateType(EnumPlateType enumVar)
	{
		setAttribute(AttributeName.PLATETYPE, enumVar == null ? null : enumVar.getName(), null);
	}

	/**
	 * (9) get attribute PlateType
	 *
	 * @return the value of the attribute
	 */
	public EnumPlateType getPlateType()
	{
		return EnumPlateType.getEnum(getAttribute(AttributeName.PLATETYPE, null, null));
	}

	/*
	 * --------------------------------------------------------------------- Methods for Attribute ProofName ---------------------------------------------------------------------
	 */
	/**
	 * (36) set attribute ProofName
	 *
	 * @param value the value to set the attribute to
	 */
	public void setProofName(String value)
	{
		setAttribute(AttributeName.PROOFNAME, value, null);
	}

	/**
	 * (23) get String attribute ProofName
	 *
	 * @return the value of the attribute
	 */
	public String getProofName()
	{
		return getAttribute(AttributeName.PROOFNAME, null, JDFCoreConstants.EMPTYSTRING);
	}

	/*
	 * --------------------------------------------------------------------- Methods for Attribute ProofQuality
	 * ---------------------------------------------------------------------
	 */
	/**
	 * (5) set attribute ProofQuality
	 *
	 * @param enumVar the enumVar to set the attribute to
	 */
	public void setProofQuality(EProofQuality enumVar)
	{
		setAttribute(AttributeName.PROOFQUALITY, enumVar == null ? null : enumVar.name(), null);
	}

	/**
	 * (9) get attribute ProofQuality
	 *
	 * @return the value of the attribute
	 */
	public EProofQuality getEProofQuality()
	{
		return EProofQuality.getEnum(getAttribute(AttributeName.PROOFQUALITY, null, null));
	}

	/*
	 * --------------------------------------------------------------------- Methods for Attribute ProofQuality
	 * ---------------------------------------------------------------------
	 */
	/**
	 * (5) set attribute ProofQuality
	 *
	 * @param enumVar the enumVar to set the attribute to
	 * @deprecated use java.lang.enum
	 */
	@Deprecated
	public void setProofQuality(EnumProofQuality enumVar)
	{
		setAttribute(AttributeName.PROOFQUALITY, enumVar == null ? null : enumVar.getName(), null);
	}

	/**
	 * (9) get attribute ProofQuality
	 *
	 * @return the value of the attribute
	 */
	public EnumProofQuality getProofQuality()
	{
		return EnumProofQuality.getEnum(getAttribute(AttributeName.PROOFQUALITY, null, null));
	}

	/*
	 * --------------------------------------------------------------------- Methods for Attribute ProofType ---------------------------------------------------------------------
	 */
	/**
	 * (5) set attribute ProofType
	 *
	 * @param enumVar the enumVar to set the attribute to
	 */
	public void setProofType(EProofType enumVar)
	{
		setAttribute(AttributeName.PROOFTYPE, enumVar == null ? null : enumVar.name(), null);
	}

	/**
	 * (9) get attribute ProofType
	 *
	 * @return the value of the attribute
	 */
	public EProofType getEProofType()
	{
		return EProofType.getEnum(getAttribute(AttributeName.PROOFTYPE, null, null));
	}

	/*
	 * --------------------------------------------------------------------- Methods for Attribute ProofType ---------------------------------------------------------------------
	 */
	/**
	 * (5) set attribute ProofType
	 *
	 * @param enumVar the enumVar to set the attribute to
	 * @deprecated use java.lang.enum
	 */
	@Deprecated
	public void setProofType(EnumProofType enumVar)
	{
		setAttribute(AttributeName.PROOFTYPE, enumVar == null ? null : enumVar.getName(), null);
	}

	/**
	 * (9) get attribute ProofType
	 *
	 * @return the value of the attribute
	 */
	public EnumProofType getProofType()
	{
		return EnumProofType.getEnum(getAttribute(AttributeName.PROOFTYPE, null, null));
	}

	/*
	 * --------------------------------------------------------------------- Methods for Attribute PunchType ---------------------------------------------------------------------
	 */
	/**
	 * (36) set attribute PunchType
	 *
	 * @param value the value to set the attribute to
	 */
	public void setPunchType(String value)
	{
		setAttribute(AttributeName.PUNCHTYPE, value, null);
	}

	/**
	 * (23) get String attribute PunchType
	 *
	 * @return the value of the attribute
	 */
	public String getPunchType()
	{
		return getAttribute(AttributeName.PUNCHTYPE, null, JDFCoreConstants.EMPTYSTRING);
	}

	/*
	 * --------------------------------------------------------------------- Methods for Attribute Resolution ---------------------------------------------------------------------
	 */
	/**
	 * (36) set attribute Resolution
	 *
	 * @param value the value to set the attribute to
	 */
	public void setResolution(JDFXYPair value)
	{
		setAttribute(AttributeName.RESOLUTION, value, null);
	}

	/**
	 * (20) get JDFXYPair attribute Resolution
	 *
	 * @return JDFXYPair the value of the attribute, null if a the attribute value is not a valid to create a JDFXYPair
	 */
	public JDFXYPair getResolution()
	{
		final String strAttrName = getAttribute(AttributeName.RESOLUTION, null, null);
		final JDFXYPair nPlaceHolder = JDFXYPair.createXYPair(strAttrName);
		return nPlaceHolder;
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
	 * (24) const get element Media
	 *
	 * @return JDFMedia the element
	 */
	public JDFMedia getMedia()
	{
		return (JDFMedia) getElement(ElementName.MEDIA, null, 0);
	}

	/**
	 * (25) getCreateMedia
	 * 
	 * @return JDFMedia the element
	 */
	public JDFMedia getCreateMedia()
	{
		return (JDFMedia) getCreateElement_JDFElement(ElementName.MEDIA, null, 0);
	}

	/**
	 * (29) append element Media
	 *
	 * @return JDFMedia the element @ if the element already exists
	 */
	public JDFMedia appendMedia()
	{
		return (JDFMedia) appendElementN(ElementName.MEDIA, 1, null);
	}

	/**
	 * (31) create inter-resource link to refTarget
	 *
	 * @param refTarget the element that is referenced
	 */
	public void refMedia(JDFMedia refTarget)
	{
		refElement(refTarget);
	}

	/**
	 * (24) const get element PageList
	 *
	 * @return JDFPageList the element
	 */
	public JDFPageList getPageList()
	{
		return (JDFPageList) getElement(ElementName.PAGELIST, null, 0);
	}

	/**
	 * (25) getCreatePageList
	 * 
	 * @return JDFPageList the element
	 */
	public JDFPageList getCreatePageList()
	{
		return (JDFPageList) getCreateElement_JDFElement(ElementName.PAGELIST, null, 0);
	}

	/**
	 * (29) append element PageList
	 *
	 * @return JDFPageList the element @ if the element already exists
	 */
	public JDFPageList appendPageList()
	{
		return (JDFPageList) appendElementN(ElementName.PAGELIST, 1, null);
	}

	/**
	 * (31) create inter-resource link to refTarget
	 *
	 * @param refTarget the element that is referenced
	 */
	public void refPageList(JDFPageList refTarget)
	{
		refElement(refTarget);
	}

	/**
	 * (24) const get element ScreeningParams
	 *
	 * @return JDFScreeningParams the element
	 */
	public JDFScreeningParams getScreeningParams()
	{
		return (JDFScreeningParams) getElement(ElementName.SCREENINGPARAMS, null, 0);
	}

	/**
	 * (25) getCreateScreeningParams
	 * 
	 * @return JDFScreeningParams the element
	 */
	public JDFScreeningParams getCreateScreeningParams()
	{
		return (JDFScreeningParams) getCreateElement_JDFElement(ElementName.SCREENINGPARAMS, null, 0);
	}

	/**
	 * (29) append element ScreeningParams
	 *
	 * @return JDFScreeningParams the element @ if the element already exists
	 */
	public JDFScreeningParams appendScreeningParams()
	{
		return (JDFScreeningParams) appendElementN(ElementName.SCREENINGPARAMS, 1, null);
	}

	/**
	 * (31) create inter-resource link to refTarget
	 *
	 * @param refTarget the element that is referenced
	 */
	public void refScreeningParams(JDFScreeningParams refTarget)
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

	/**
	 * (24) const get element IdentificationField
	 *
	 * @return JDFIdentificationField the element
	 */
	public JDFIdentificationField getIdentificationField()
	{
		return (JDFIdentificationField) getElement(ElementName.IDENTIFICATIONFIELD, null, 0);
	}

	/**
	 * (25) getCreateIdentificationField
	 * 
	 * @return JDFIdentificationField the element
	 */
	public JDFIdentificationField getCreateIdentificationField()
	{
		return (JDFIdentificationField) getCreateElement_JDFElement(ElementName.IDENTIFICATIONFIELD, null, 0);
	}

	/**
	 * (26) getCreateIdentificationField
	 * 
	 * @param iSkip number of elements to skip
	 * @return JDFIdentificationField the element
	 */
	@Override
	public JDFIdentificationField getCreateIdentificationField(int iSkip)
	{
		return (JDFIdentificationField) getCreateElement_JDFElement(ElementName.IDENTIFICATIONFIELD, null, iSkip);
	}

	/**
	 * (27) const get element IdentificationField
	 *
	 * @param iSkip number of elements to skip
	 * @return JDFIdentificationField the element default is getIdentificationField(0)
	 */
	@Override
	public JDFIdentificationField getIdentificationField(int iSkip)
	{
		return (JDFIdentificationField) getElement(ElementName.IDENTIFICATIONFIELD, null, iSkip);
	}

	/**
	 * Get all IdentificationField from the current element
	 * 
	 * @return Collection<JDFIdentificationField>, null if none are available
	 */
	public Collection<JDFIdentificationField> getAllIdentificationField()
	{
		return getChildArrayByClass(JDFIdentificationField.class, false, 0);
	}

	/**
	 * (30) append element IdentificationField
	 *
	 * @return JDFIdentificationField the element
	 */
	@Override
	public JDFIdentificationField appendIdentificationField()
	{
		return (JDFIdentificationField) appendElement(ElementName.IDENTIFICATIONFIELD, null);
	}

	/**
	 * (31) create inter-resource link to refTarget
	 *
	 * @param refTarget the element that is referenced
	 */
	public void refIdentificationField(JDFIdentificationField refTarget)
	{
		refElement(refTarget);
	}

}
