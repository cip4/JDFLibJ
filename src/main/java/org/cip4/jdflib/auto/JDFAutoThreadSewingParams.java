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
import org.cip4.jdflib.datatypes.JDFIntegerList;
import org.cip4.jdflib.datatypes.JDFNumberList;
import org.cip4.jdflib.resource.JDFResource;
import org.cip4.jdflib.resource.process.postpress.JDFGlueLine;
import org.cip4.jdflib.util.JavaEnumUtil;

/**
 *****************************************************************************
 * class JDFAutoThreadSewingParams : public JDFResource
 *****************************************************************************
 * 
 */

public abstract class JDFAutoThreadSewingParams extends JDFResource
{

	private static final long serialVersionUID = 1L;

	private static AtrInfoTable[] atrInfoTable = new AtrInfoTable[11];
	static
	{
		atrInfoTable[0] = new AtrInfoTable(AttributeName.BLINDSTITCH, 0x3333333333l, AttributeInfo.EnumAttributeType.boolean_, null, "false");
		atrInfoTable[1] = new AtrInfoTable(AttributeName.CASTINGMATERIAL, 0x3333333333l, AttributeInfo.EnumAttributeType.enumeration, EnumCastingMaterial.getEnum(0), null);
		atrInfoTable[2] = new AtrInfoTable(AttributeName.COREMATERIAL, 0x3333333333l, AttributeInfo.EnumAttributeType.enumeration, EnumCoreMaterial.getEnum(0), null);
		atrInfoTable[3] = new AtrInfoTable(AttributeName.GLUELINEREFSHEETS, 0x3333333333l, AttributeInfo.EnumAttributeType.IntegerList, null, null);
		atrInfoTable[4] = new AtrInfoTable(AttributeName.OFFSET, 0x3333333331l, AttributeInfo.EnumAttributeType.double_, null, null);
		atrInfoTable[5] = new AtrInfoTable(AttributeName.NEEDLEPOSITIONS, 0x3333333333l, AttributeInfo.EnumAttributeType.string, null, null);
		atrInfoTable[6] = new AtrInfoTable(AttributeName.NUMBEROFNEEDLES, 0x3333333333l, AttributeInfo.EnumAttributeType.integer, null, null);
		atrInfoTable[7] = new AtrInfoTable(AttributeName.SEALING, 0x4444333333l, AttributeInfo.EnumAttributeType.boolean_, null, null);
		atrInfoTable[8] = new AtrInfoTable(AttributeName.SEWINGPATTERN, 0x3333333333l, AttributeInfo.EnumAttributeType.enumeration, EnumSewingPattern.getEnum(0), null);
		atrInfoTable[9] = new AtrInfoTable(AttributeName.THREADTHICKNESS, 0x3333333333l, AttributeInfo.EnumAttributeType.double_, null, null);
		atrInfoTable[10] = new AtrInfoTable(AttributeName.THREADBRAND, 0x3333333333l, AttributeInfo.EnumAttributeType.string, null, null);
	}

	@Override
	protected AttributeInfo getTheAttributeInfo()
	{
		return super.getTheAttributeInfo().updateReplace(atrInfoTable);
	}

	private static ElemInfoTable[] elemInfoTable = new ElemInfoTable[1];
	static
	{
		elemInfoTable[0] = new ElemInfoTable(ElementName.GLUELINE, 0x3333333333l);
	}

	@Override
	protected ElementInfo getTheElementInfo()
	{
		return super.getTheElementInfo().updateReplace(elemInfoTable);
	}

	/**
	 * Constructor for JDFAutoThreadSewingParams
	 *
	 * @param myOwnerDocument
	 * @param qualifiedName
	 */
	protected JDFAutoThreadSewingParams(CoreDocumentImpl myOwnerDocument, String qualifiedName)
	{
		super(myOwnerDocument, qualifiedName);
	}

	/**
	 * Constructor for JDFAutoThreadSewingParams
	 *
	 * @param myOwnerDocument
	 * @param myNamespaceURI
	 * @param qualifiedName
	 */
	protected JDFAutoThreadSewingParams(CoreDocumentImpl myOwnerDocument, String myNamespaceURI, String qualifiedName)
	{
		super(myOwnerDocument, myNamespaceURI, qualifiedName);
	}

	/**
	 * Constructor for JDFAutoThreadSewingParams
	 *
	 * @param myOwnerDocument
	 * @param myNamespaceURI
	 * @param qualifiedName
	 * @param myLocalName
	 */
	protected JDFAutoThreadSewingParams(CoreDocumentImpl myOwnerDocument, String myNamespaceURI, String qualifiedName, String myLocalName)
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
	 * Enumeration strings for CastingMaterial
	 */

	public enum ECastingMaterial
	{
		Cotton, Nylon, Polyester;

		public static ECastingMaterial getEnum(String val)
		{
			return JavaEnumUtil.getEnumIgnoreCase(ECastingMaterial.class, val, null);
		}
	}

	/**
	 * Enumeration strings for CastingMaterial
	 */

	@SuppressWarnings("rawtypes")
	public static class EnumCastingMaterial extends ValuedEnum
	{
		private static final long serialVersionUID = 1L;
		private static int m_startValue = 0;

		protected EnumCastingMaterial(String name)
		{
			super(name, m_startValue++);
		}

		/**
		 * @param enumName the string to convert
		 * @return the enum
		 */
		public static EnumCastingMaterial getEnum(String enumName)
		{
			return (EnumCastingMaterial) getEnum(EnumCastingMaterial.class, enumName);
		}

		/**
		 * @param enumValue the integer to convert
		 * @return the enum
		 */
		public static EnumCastingMaterial getEnum(int enumValue)
		{
			return (EnumCastingMaterial) getEnum(EnumCastingMaterial.class, enumValue);
		}

		/**
		 * @return the map of enums
		 */
		public static Map getEnumMap()
		{
			return getEnumMap(EnumCastingMaterial.class);
		}

		/**
		 * @return the list of enums
		 */
		public static List getEnumList()
		{
			return getEnumList(EnumCastingMaterial.class);
		}

		/**
		 * @return the iterator
		 */
		public static Iterator iterator()
		{
			return iterator(EnumCastingMaterial.class);
		}

		/**  */
		public static final EnumCastingMaterial Cotton = new EnumCastingMaterial("Cotton");
		/**  */
		public static final EnumCastingMaterial Nylon = new EnumCastingMaterial("Nylon");
		/**  */
		public static final EnumCastingMaterial Polyester = new EnumCastingMaterial("Polyester");
	}

	/**
	 * Enumeration strings for CoreMaterial
	 */

	public enum ECoreMaterial
	{
		Cotton, Nylon, Polyester;

		public static ECoreMaterial getEnum(String val)
		{
			return JavaEnumUtil.getEnumIgnoreCase(ECoreMaterial.class, val, null);
		}
	}

	/**
	 * Enumeration strings for CoreMaterial
	 */

	@SuppressWarnings("rawtypes")
	public static class EnumCoreMaterial extends ValuedEnum
	{
		private static final long serialVersionUID = 1L;
		private static int m_startValue = 0;

		protected EnumCoreMaterial(String name)
		{
			super(name, m_startValue++);
		}

		/**
		 * @param enumName the string to convert
		 * @return the enum
		 */
		public static EnumCoreMaterial getEnum(String enumName)
		{
			return (EnumCoreMaterial) getEnum(EnumCoreMaterial.class, enumName);
		}

		/**
		 * @param enumValue the integer to convert
		 * @return the enum
		 */
		public static EnumCoreMaterial getEnum(int enumValue)
		{
			return (EnumCoreMaterial) getEnum(EnumCoreMaterial.class, enumValue);
		}

		/**
		 * @return the map of enums
		 */
		public static Map getEnumMap()
		{
			return getEnumMap(EnumCoreMaterial.class);
		}

		/**
		 * @return the list of enums
		 */
		public static List getEnumList()
		{
			return getEnumList(EnumCoreMaterial.class);
		}

		/**
		 * @return the iterator
		 */
		public static Iterator iterator()
		{
			return iterator(EnumCoreMaterial.class);
		}

		/**  */
		public static final EnumCoreMaterial Cotton = new EnumCoreMaterial("Cotton");
		/**  */
		public static final EnumCoreMaterial Nylon = new EnumCoreMaterial("Nylon");
		/**  */
		public static final EnumCoreMaterial Polyester = new EnumCoreMaterial("Polyester");
	}

	/**
	 * Enumeration strings for SewingPattern
	 */

	public enum ESewingPattern
	{
		Normal, Staggered, CombinedStaggered, Side;

		public static ESewingPattern getEnum(String val)
		{
			return JavaEnumUtil.getEnumIgnoreCase(ESewingPattern.class, val, null);
		}
	}

	/**
	 * Enumeration strings for SewingPattern
	 */

	@SuppressWarnings("rawtypes")
	public static class EnumSewingPattern extends ValuedEnum
	{
		private static final long serialVersionUID = 1L;
		private static int m_startValue = 0;

		protected EnumSewingPattern(String name)
		{
			super(name, m_startValue++);
		}

		/**
		 * @param enumName the string to convert
		 * @return the enum
		 */
		public static EnumSewingPattern getEnum(String enumName)
		{
			return (EnumSewingPattern) getEnum(EnumSewingPattern.class, enumName);
		}

		/**
		 * @param enumValue the integer to convert
		 * @return the enum
		 */
		public static EnumSewingPattern getEnum(int enumValue)
		{
			return (EnumSewingPattern) getEnum(EnumSewingPattern.class, enumValue);
		}

		/**
		 * @return the map of enums
		 */
		public static Map getEnumMap()
		{
			return getEnumMap(EnumSewingPattern.class);
		}

		/**
		 * @return the list of enums
		 */
		public static List getEnumList()
		{
			return getEnumList(EnumSewingPattern.class);
		}

		/**
		 * @return the iterator
		 */
		public static Iterator iterator()
		{
			return iterator(EnumSewingPattern.class);
		}

		/**  */
		public static final EnumSewingPattern Normal = new EnumSewingPattern("Normal");
		/**  */
		public static final EnumSewingPattern Staggered = new EnumSewingPattern("Staggered");
		/**  */
		public static final EnumSewingPattern CombinedStaggered = new EnumSewingPattern("CombinedStaggered");
		/**  */
		public static final EnumSewingPattern Side = new EnumSewingPattern("Side");
	}

	/*
	 * ************************************************************************ Attribute getter / setter ************************************************************************
	 */

	/*
	 * --------------------------------------------------------------------- Methods for Attribute BlindStitch ---------------------------------------------------------------------
	 */
	/**
	 * (36) set attribute BlindStitch
	 *
	 * @param value the value to set the attribute to
	 */
	public void setBlindStitch(boolean value)
	{
		setAttribute(AttributeName.BLINDSTITCH, value, null);
	}

	/**
	 * (18) get boolean attribute BlindStitch
	 *
	 * @return boolean the value of the attribute
	 */
	public boolean getBlindStitch()
	{
		return getBoolAttribute(AttributeName.BLINDSTITCH, null, false);
	}

	/*
	 * --------------------------------------------------------------------- Methods for Attribute CastingMaterial
	 * ---------------------------------------------------------------------
	 */
	/**
	 * (5) set attribute CastingMaterial
	 *
	 * @param enumVar the enumVar to set the attribute to
	 */
	public void setCastingMaterial(ECastingMaterial enumVar)
	{
		setAttribute(AttributeName.CASTINGMATERIAL, enumVar == null ? null : enumVar.name(), null);
	}

	/**
	 * (9) get attribute CastingMaterial
	 *
	 * @return the value of the attribute
	 */
	public ECastingMaterial getECastingMaterial()
	{
		return ECastingMaterial.getEnum(getAttribute(AttributeName.CASTINGMATERIAL, null, null));
	}

	/*
	 * --------------------------------------------------------------------- Methods for Attribute CastingMaterial
	 * ---------------------------------------------------------------------
	 */
	/**
	 * (5) set attribute CastingMaterial
	 *
	 * @param enumVar the enumVar to set the attribute to
	 * @deprecated use setCastingMaterial(ECastingMaterial) based on java.lang.enum instead
	 */
	@Deprecated
	public void setCastingMaterial(EnumCastingMaterial enumVar)
	{
		setAttribute(AttributeName.CASTINGMATERIAL, enumVar == null ? null : enumVar.getName(), null);
	}

	/**
	 * (9) get attribute CastingMaterial
	 *
	 * @return the value of the attribute
	 * @deprecated use ECastingMaterial getECastingMaterial() based on java.lang.enum instead
	 */
	@Deprecated
	public EnumCastingMaterial getCastingMaterial()
	{
		return EnumCastingMaterial.getEnum(getAttribute(AttributeName.CASTINGMATERIAL, null, null));
	}

	/*
	 * --------------------------------------------------------------------- Methods for Attribute CoreMaterial
	 * ---------------------------------------------------------------------
	 */
	/**
	 * (5) set attribute CoreMaterial
	 *
	 * @param enumVar the enumVar to set the attribute to
	 */
	public void setCoreMaterial(ECoreMaterial enumVar)
	{
		setAttribute(AttributeName.COREMATERIAL, enumVar == null ? null : enumVar.name(), null);
	}

	/**
	 * (9) get attribute CoreMaterial
	 *
	 * @return the value of the attribute
	 */
	public ECoreMaterial getECoreMaterial()
	{
		return ECoreMaterial.getEnum(getAttribute(AttributeName.COREMATERIAL, null, null));
	}

	/*
	 * --------------------------------------------------------------------- Methods for Attribute CoreMaterial
	 * ---------------------------------------------------------------------
	 */
	/**
	 * (5) set attribute CoreMaterial
	 *
	 * @param enumVar the enumVar to set the attribute to
	 * @deprecated use setCoreMaterial(ECoreMaterial) based on java.lang.enum instead
	 */
	@Deprecated
	public void setCoreMaterial(EnumCoreMaterial enumVar)
	{
		setAttribute(AttributeName.COREMATERIAL, enumVar == null ? null : enumVar.getName(), null);
	}

	/**
	 * (9) get attribute CoreMaterial
	 *
	 * @return the value of the attribute
	 * @deprecated use ECoreMaterial getECoreMaterial() based on java.lang.enum instead
	 */
	@Deprecated
	public EnumCoreMaterial getCoreMaterial()
	{
		return EnumCoreMaterial.getEnum(getAttribute(AttributeName.COREMATERIAL, null, null));
	}

	/*
	 * --------------------------------------------------------------------- Methods for Attribute GlueLineRefSheets
	 * ---------------------------------------------------------------------
	 */
	/**
	 * (36) set attribute GlueLineRefSheets
	 *
	 * @param value the value to set the attribute to
	 */
	public void setGlueLineRefSheets(JDFIntegerList value)
	{
		setAttribute(AttributeName.GLUELINEREFSHEETS, value, null);
	}

	/**
	 * (20) get JDFIntegerList attribute GlueLineRefSheets
	 *
	 * @return JDFIntegerList the value of the attribute, null if a the attribute value is not a valid to create a JDFIntegerList
	 */
	public JDFIntegerList getGlueLineRefSheets()
	{
		final String strAttrName = getAttribute(AttributeName.GLUELINEREFSHEETS, null, null);
		final JDFIntegerList nPlaceHolder = JDFIntegerList.createIntegerList(strAttrName);
		return nPlaceHolder;
	}

	/*
	 * --------------------------------------------------------------------- Methods for Attribute Offset ---------------------------------------------------------------------
	 */
	/**
	 * (36) set attribute Offset
	 *
	 * @param value the value to set the attribute to
	 */
	public void setOffset(double value)
	{
		setAttribute(AttributeName.OFFSET, value, null);
	}

	/**
	 * (17) get double attribute Offset
	 *
	 * @return double the value of the attribute
	 */
	public double getOffset()
	{
		return getRealAttribute(AttributeName.OFFSET, null, 0.0);
	}

	/*
	 * --------------------------------------------------------------------- Methods for Attribute NeedlePositions
	 * ---------------------------------------------------------------------
	 */
	/**
	 * (36) set attribute NeedlePositions
	 *
	 * @param value the value to set the attribute to
	 */
	public void setNeedlePositions(JDFNumberList value)
	{
		setAttribute(AttributeName.NEEDLEPOSITIONS, value, null);
	}

	/**
	 * (20) get JDFNumberList attribute NeedlePositions
	 *
	 * @return JDFNumberList the value of the attribute, null if a the attribute value is not a valid to create a JDFNumberList
	 */
	public JDFNumberList getNeedlePositions()
	{
		final String strAttrName = getAttribute(AttributeName.NEEDLEPOSITIONS, null, null);
		final JDFNumberList nPlaceHolder = JDFNumberList.createNumberList(strAttrName);
		return nPlaceHolder;
	}

	/*
	 * --------------------------------------------------------------------- Methods for Attribute NumberOfNeedles
	 * ---------------------------------------------------------------------
	 */
	/**
	 * (36) set attribute NumberOfNeedles
	 *
	 * @param value the value to set the attribute to
	 */
	public void setNumberOfNeedles(int value)
	{
		setAttribute(AttributeName.NUMBEROFNEEDLES, value, null);
	}

	/**
	 * (15) get int attribute NumberOfNeedles
	 *
	 * @return int the value of the attribute
	 */
	public int getNumberOfNeedles()
	{
		return getIntAttribute(AttributeName.NUMBEROFNEEDLES, null, 0);
	}

	/*
	 * --------------------------------------------------------------------- Methods for Attribute Sealing ---------------------------------------------------------------------
	 */
	/**
	 * (36) set attribute Sealing
	 *
	 * @param value the value to set the attribute to
	 */
	public void setSealing(boolean value)
	{
		setAttribute(AttributeName.SEALING, value, null);
	}

	/**
	 * (18) get boolean attribute Sealing
	 *
	 * @return boolean the value of the attribute
	 */
	public boolean getSealing()
	{
		return getBoolAttribute(AttributeName.SEALING, null, false);
	}

	/*
	 * --------------------------------------------------------------------- Methods for Attribute SewingPattern
	 * ---------------------------------------------------------------------
	 */
	/**
	 * (5) set attribute SewingPattern
	 *
	 * @param enumVar the enumVar to set the attribute to
	 */
	public void setSewingPattern(ESewingPattern enumVar)
	{
		setAttribute(AttributeName.SEWINGPATTERN, enumVar == null ? null : enumVar.name(), null);
	}

	/**
	 * (9) get attribute SewingPattern
	 *
	 * @return the value of the attribute
	 */
	public ESewingPattern getESewingPattern()
	{
		return ESewingPattern.getEnum(getAttribute(AttributeName.SEWINGPATTERN, null, null));
	}

	/*
	 * --------------------------------------------------------------------- Methods for Attribute SewingPattern
	 * ---------------------------------------------------------------------
	 */
	/**
	 * (5) set attribute SewingPattern
	 *
	 * @param enumVar the enumVar to set the attribute to
	 * @deprecated use setSewingPattern(ESewingPattern) based on java.lang.enum instead
	 */
	@Deprecated
	public void setSewingPattern(EnumSewingPattern enumVar)
	{
		setAttribute(AttributeName.SEWINGPATTERN, enumVar == null ? null : enumVar.getName(), null);
	}

	/**
	 * (9) get attribute SewingPattern
	 *
	 * @return the value of the attribute
	 * @deprecated use ESewingPattern getESewingPattern() based on java.lang.enum instead
	 */
	@Deprecated
	public EnumSewingPattern getSewingPattern()
	{
		return EnumSewingPattern.getEnum(getAttribute(AttributeName.SEWINGPATTERN, null, null));
	}

	/*
	 * --------------------------------------------------------------------- Methods for Attribute ThreadThickness
	 * ---------------------------------------------------------------------
	 */
	/**
	 * (36) set attribute ThreadThickness
	 *
	 * @param value the value to set the attribute to
	 */
	public void setThreadThickness(double value)
	{
		setAttribute(AttributeName.THREADTHICKNESS, value, null);
	}

	/**
	 * (17) get double attribute ThreadThickness
	 *
	 * @return double the value of the attribute
	 */
	public double getThreadThickness()
	{
		return getRealAttribute(AttributeName.THREADTHICKNESS, null, 0.0);
	}

	/*
	 * --------------------------------------------------------------------- Methods for Attribute ThreadBrand ---------------------------------------------------------------------
	 */
	/**
	 * (36) set attribute ThreadBrand
	 *
	 * @param value the value to set the attribute to
	 */
	public void setThreadBrand(String value)
	{
		setAttribute(AttributeName.THREADBRAND, value, null);
	}

	/**
	 * (23) get String attribute ThreadBrand
	 *
	 * @return the value of the attribute
	 */
	public String getThreadBrand()
	{
		return getAttribute(AttributeName.THREADBRAND, null, JDFCoreConstants.EMPTYSTRING);
	}

	/*
	 * *********************************************************************** Element getter / setter ***********************************************************************
	 */

	/**
	 * (24) const get element GlueLine
	 *
	 * @return JDFGlueLine the element
	 */
	public JDFGlueLine getGlueLine()
	{
		return (JDFGlueLine) getElement(ElementName.GLUELINE, null, 0);
	}

	/**
	 * (25) getCreateGlueLine
	 * 
	 * @return JDFGlueLine the element
	 */
	public JDFGlueLine getCreateGlueLine()
	{
		return (JDFGlueLine) getCreateElement_JDFElement(ElementName.GLUELINE, null, 0);
	}

	/**
	 * (26) getCreateGlueLine
	 * 
	 * @param iSkip number of elements to skip
	 * @return JDFGlueLine the element
	 */
	public JDFGlueLine getCreateGlueLine(int iSkip)
	{
		return (JDFGlueLine) getCreateElement_JDFElement(ElementName.GLUELINE, null, iSkip);
	}

	/**
	 * (27) const get element GlueLine
	 *
	 * @param iSkip number of elements to skip
	 * @return JDFGlueLine the element default is getGlueLine(0)
	 */
	public JDFGlueLine getGlueLine(int iSkip)
	{
		return (JDFGlueLine) getElement(ElementName.GLUELINE, null, iSkip);
	}

	/**
	 * Get all GlueLine from the current element
	 * 
	 * @return Collection<JDFGlueLine>, null if none are available
	 */
	public Collection<JDFGlueLine> getAllGlueLine()
	{
		return getChildArrayByClass(JDFGlueLine.class, false, 0);
	}

	/**
	 * (30) append element GlueLine
	 *
	 * @return JDFGlueLine the element
	 */
	public JDFGlueLine appendGlueLine()
	{
		return (JDFGlueLine) appendElement(ElementName.GLUELINE, null);
	}

}
