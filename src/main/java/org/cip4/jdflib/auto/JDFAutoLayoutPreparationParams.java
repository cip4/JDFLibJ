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
import org.cip4.jdflib.core.VString;
import org.cip4.jdflib.datatypes.JDFIntegerList;
import org.cip4.jdflib.datatypes.JDFRectangle;
import org.cip4.jdflib.datatypes.JDFXYPair;
import org.cip4.jdflib.resource.JDFDeviceMark;
import org.cip4.jdflib.resource.JDFFitPolicy;
import org.cip4.jdflib.resource.JDFImageShift;
import org.cip4.jdflib.resource.JDFJobField;
import org.cip4.jdflib.resource.JDFPageCell;
import org.cip4.jdflib.resource.JDFResource;
import org.cip4.jdflib.resource.process.JDFExternalImpositionTemplate;
import org.cip4.jdflib.resource.process.JDFInsertSheet;
import org.cip4.jdflib.resource.process.JDFMedia;
import org.cip4.jdflib.util.JavaEnumUtil;

/**
 *****************************************************************************
 * class JDFAutoLayoutPreparationParams : public JDFResource
 *****************************************************************************
 * 
 */

public abstract class JDFAutoLayoutPreparationParams extends JDFResource
{

	private static final long serialVersionUID = 1L;

	private static AtrInfoTable[] atrInfoTable = new AtrInfoTable[23];
	static
	{
		atrInfoTable[0] = new AtrInfoTable(AttributeName.FINISHINGORDER, 0x3333333331l, AttributeInfo.EnumAttributeType.enumeration, EnumFinishingOrder.getEnum(0), "GatherFold");
		atrInfoTable[1] = new AtrInfoTable(AttributeName.FOLDCATALOGORIENTATION, 0x3333333111l, AttributeInfo.EnumAttributeType.enumeration, EnumFoldCatalogOrientation.getEnum(0),
				"Rotate0");
		atrInfoTable[2] = new AtrInfoTable(AttributeName.PAGEDISTRIBUTIONSCHEME, 0x3333333331l, AttributeInfo.EnumAttributeType.NMTOKEN, null, "Sequential");
		atrInfoTable[3] = new AtrInfoTable(AttributeName.PAGEORDER, 0x3333333331l, AttributeInfo.EnumAttributeType.NMTOKEN, null, "Reader");
		atrInfoTable[4] = new AtrInfoTable(AttributeName.ROTATE, 0x3333333331l, AttributeInfo.EnumAttributeType.enumeration, EnumRotate.getEnum(0), "Rotate0");
		atrInfoTable[5] = new AtrInfoTable(AttributeName.SIDES, 0x3333333331l, AttributeInfo.EnumAttributeType.enumeration, EnumSides.getEnum(0), "OneSidedFront");
		atrInfoTable[6] = new AtrInfoTable(AttributeName.BINDINGEDGE, 0x3333333111l, AttributeInfo.EnumAttributeType.enumeration, EnumBindingEdge.getEnum(0), null);
		atrInfoTable[7] = new AtrInfoTable(AttributeName.BACKMARKLIST, 0x3333333331l, AttributeInfo.EnumAttributeType.NMTOKENS, null, null);
		atrInfoTable[8] = new AtrInfoTable(AttributeName.CREEPVALUE, 0x3333333331l, AttributeInfo.EnumAttributeType.XYPair, null, null);
		atrInfoTable[9] = new AtrInfoTable(AttributeName.FOLDCATALOG, 0x3333333331l, AttributeInfo.EnumAttributeType.string, null, null);
		atrInfoTable[10] = new AtrInfoTable(AttributeName.FRONTMARKLIST, 0x3333333331l, AttributeInfo.EnumAttributeType.NMTOKENS, null, null);
		atrInfoTable[11] = new AtrInfoTable(AttributeName.GUTTER, 0x3333333331l, AttributeInfo.EnumAttributeType.XYPair, null, null);
		atrInfoTable[12] = new AtrInfoTable(AttributeName.GUTTERMINIMUMLIMIT, 0x3333333111l, AttributeInfo.EnumAttributeType.XYPair, null, null);
		atrInfoTable[13] = new AtrInfoTable(AttributeName.HORIZONTALCREEP, 0x3333333331l, AttributeInfo.EnumAttributeType.IntegerList, null, null);
		atrInfoTable[14] = new AtrInfoTable(AttributeName.IMPLICITGUTTER, 0x3333333111l, AttributeInfo.EnumAttributeType.XYPair, null, null);
		atrInfoTable[15] = new AtrInfoTable(AttributeName.IMPLICITGUTTERMINIMUMLIMIT, 0x3333333111l, AttributeInfo.EnumAttributeType.XYPair, null, null);
		atrInfoTable[16] = new AtrInfoTable(AttributeName.NUMBERUP, 0x3333333331l, AttributeInfo.EnumAttributeType.XYPair, null, null);
		atrInfoTable[17] = new AtrInfoTable(AttributeName.PRESENTATIONDIRECTION, 0x3333333331l, AttributeInfo.EnumAttributeType.Any, null, null);
		atrInfoTable[18] = new AtrInfoTable(AttributeName.STACKDEPTH, 0x3333333331l, AttributeInfo.EnumAttributeType.integer, null, null);
		atrInfoTable[19] = new AtrInfoTable(AttributeName.STEPDOCS, 0x3333333331l, AttributeInfo.EnumAttributeType.XYPair, null, null);
		atrInfoTable[20] = new AtrInfoTable(AttributeName.STEPREPEAT, 0x3333333331l, AttributeInfo.EnumAttributeType.string, null, null);
		atrInfoTable[21] = new AtrInfoTable(AttributeName.SURFACECONTENTSBOX, 0x3333333331l, AttributeInfo.EnumAttributeType.rectangle, null, null);
		atrInfoTable[22] = new AtrInfoTable(AttributeName.VERTICALCREEP, 0x3333333331l, AttributeInfo.EnumAttributeType.IntegerList, null, null);
	}

	@Override
	protected AttributeInfo getTheAttributeInfo()
	{
		return super.getTheAttributeInfo().updateReplace(atrInfoTable);
	}

	private static ElemInfoTable[] elemInfoTable = new ElemInfoTable[8];
	static
	{
		elemInfoTable[0] = new ElemInfoTable(ElementName.IMAGESHIFT, 0x6666666661l);
		elemInfoTable[1] = new ElemInfoTable(ElementName.INSERTSHEET, 0x3333333331l);
		elemInfoTable[2] = new ElemInfoTable(ElementName.DEVICEMARK, 0x6666666661l);
		elemInfoTable[3] = new ElemInfoTable(ElementName.EXTERNALIMPOSITIONTEMPLATE, 0x6666666661l);
		elemInfoTable[4] = new ElemInfoTable(ElementName.FITPOLICY, 0x6666666661l);
		elemInfoTable[5] = new ElemInfoTable(ElementName.JOBFIELD, 0x3333333331l);
		elemInfoTable[6] = new ElemInfoTable(ElementName.MEDIA, 0x6666666661l);
		elemInfoTable[7] = new ElemInfoTable(ElementName.PAGECELL, 0x6666666661l);
	}

	@Override
	protected ElementInfo getTheElementInfo()
	{
		return super.getTheElementInfo().updateReplace(elemInfoTable);
	}

	/**
	 * Constructor for JDFAutoLayoutPreparationParams
	 *
	 * @param myOwnerDocument
	 * @param qualifiedName
	 */
	protected JDFAutoLayoutPreparationParams(CoreDocumentImpl myOwnerDocument, String qualifiedName)
	{
		super(myOwnerDocument, qualifiedName);
	}

	/**
	 * Constructor for JDFAutoLayoutPreparationParams
	 *
	 * @param myOwnerDocument
	 * @param myNamespaceURI
	 * @param qualifiedName
	 */
	protected JDFAutoLayoutPreparationParams(CoreDocumentImpl myOwnerDocument, String myNamespaceURI, String qualifiedName)
	{
		super(myOwnerDocument, myNamespaceURI, qualifiedName);
	}

	/**
	 * Constructor for JDFAutoLayoutPreparationParams
	 *
	 * @param myOwnerDocument
	 * @param myNamespaceURI
	 * @param qualifiedName
	 * @param myLocalName
	 */
	protected JDFAutoLayoutPreparationParams(CoreDocumentImpl myOwnerDocument, String myNamespaceURI, String qualifiedName, String myLocalName)
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
	 * Enumeration strings for FinishingOrder
	 */

	public enum EFinishingOrder
	{
		FoldGather, FoldCollect, Gather, GatherFold;

		public static EFinishingOrder getEnum(String val)
		{
			return JavaEnumUtil.getEnumIgnoreCase(EFinishingOrder.class, val, EFinishingOrder.GatherFold);
		}
	}

	/**
	 * Enumeration strings for FinishingOrder
	 */

	@SuppressWarnings("rawtypes")
	public static class EnumFinishingOrder extends ValuedEnum
	{
		private static final long serialVersionUID = 1L;
		private static int m_startValue = 0;

		protected EnumFinishingOrder(String name)
		{
			super(name, m_startValue++);
		}

		/**
		 * @param enumName the string to convert
		 * @return the enum
		 */
		public static EnumFinishingOrder getEnum(String enumName)
		{
			return (EnumFinishingOrder) getEnum(EnumFinishingOrder.class, enumName);
		}

		/**
		 * @param enumValue the integer to convert
		 * @return the enum
		 */
		public static EnumFinishingOrder getEnum(int enumValue)
		{
			return (EnumFinishingOrder) getEnum(EnumFinishingOrder.class, enumValue);
		}

		/**
		 * @return the map of enums
		 */
		public static Map getEnumMap()
		{
			return getEnumMap(EnumFinishingOrder.class);
		}

		/**
		 * @return the list of enums
		 */
		public static List getEnumList()
		{
			return getEnumList(EnumFinishingOrder.class);
		}

		/**
		 * @return the iterator
		 */
		public static Iterator iterator()
		{
			return iterator(EnumFinishingOrder.class);
		}

		/**  */
		public static final EnumFinishingOrder FoldGather = new EnumFinishingOrder("FoldGather");
		/**  */
		public static final EnumFinishingOrder FoldCollect = new EnumFinishingOrder("FoldCollect");
		/**  */
		public static final EnumFinishingOrder Gather = new EnumFinishingOrder("Gather");
		/**  */
		public static final EnumFinishingOrder GatherFold = new EnumFinishingOrder("GatherFold");
	}

	/**
	 * Enumeration strings for FoldCatalogOrientation
	 */

	public enum EFoldCatalogOrientation
	{
		Rotate0, Rotate90, Rotate180, Rotate270, Flip0, Flip90, Flip180, Flip270;

		public static EFoldCatalogOrientation getEnum(String val)
		{
			return JavaEnumUtil.getEnumIgnoreCase(EFoldCatalogOrientation.class, val, EFoldCatalogOrientation.Rotate0);
		}
	}

	/**
	 * Enumeration strings for FoldCatalogOrientation
	 */

	@SuppressWarnings("rawtypes")
	public static class EnumFoldCatalogOrientation extends ValuedEnum
	{
		private static final long serialVersionUID = 1L;
		private static int m_startValue = 0;

		protected EnumFoldCatalogOrientation(String name)
		{
			super(name, m_startValue++);
		}

		/**
		 * @param enumName the string to convert
		 * @return the enum
		 */
		public static EnumFoldCatalogOrientation getEnum(String enumName)
		{
			return (EnumFoldCatalogOrientation) getEnum(EnumFoldCatalogOrientation.class, enumName);
		}

		/**
		 * @param enumValue the integer to convert
		 * @return the enum
		 */
		public static EnumFoldCatalogOrientation getEnum(int enumValue)
		{
			return (EnumFoldCatalogOrientation) getEnum(EnumFoldCatalogOrientation.class, enumValue);
		}

		/**
		 * @return the map of enums
		 */
		public static Map getEnumMap()
		{
			return getEnumMap(EnumFoldCatalogOrientation.class);
		}

		/**
		 * @return the list of enums
		 */
		public static List getEnumList()
		{
			return getEnumList(EnumFoldCatalogOrientation.class);
		}

		/**
		 * @return the iterator
		 */
		public static Iterator iterator()
		{
			return iterator(EnumFoldCatalogOrientation.class);
		}

		/**  */
		public static final EnumFoldCatalogOrientation Rotate0 = new EnumFoldCatalogOrientation("Rotate0");
		/**  */
		public static final EnumFoldCatalogOrientation Rotate90 = new EnumFoldCatalogOrientation("Rotate90");
		/**  */
		public static final EnumFoldCatalogOrientation Rotate180 = new EnumFoldCatalogOrientation("Rotate180");
		/**  */
		public static final EnumFoldCatalogOrientation Rotate270 = new EnumFoldCatalogOrientation("Rotate270");
		/**  */
		public static final EnumFoldCatalogOrientation Flip0 = new EnumFoldCatalogOrientation("Flip0");
		/**  */
		public static final EnumFoldCatalogOrientation Flip90 = new EnumFoldCatalogOrientation("Flip90");
		/**  */
		public static final EnumFoldCatalogOrientation Flip180 = new EnumFoldCatalogOrientation("Flip180");
		/**  */
		public static final EnumFoldCatalogOrientation Flip270 = new EnumFoldCatalogOrientation("Flip270");
	}

	/**
	 * Enumeration strings for Rotate
	 */

	public enum ERotate
	{
		Rotate0, Rotate90, Rotate180, Rotate270;

		public static ERotate getEnum(String val)
		{
			return JavaEnumUtil.getEnumIgnoreCase(ERotate.class, val, ERotate.Rotate0);
		}
	}

	/**
	 * Enumeration strings for Rotate
	 */

	@SuppressWarnings("rawtypes")
	public static class EnumRotate extends ValuedEnum
	{
		private static final long serialVersionUID = 1L;
		private static int m_startValue = 0;

		protected EnumRotate(String name)
		{
			super(name, m_startValue++);
		}

		/**
		 * @param enumName the string to convert
		 * @return the enum
		 */
		public static EnumRotate getEnum(String enumName)
		{
			return (EnumRotate) getEnum(EnumRotate.class, enumName);
		}

		/**
		 * @param enumValue the integer to convert
		 * @return the enum
		 */
		public static EnumRotate getEnum(int enumValue)
		{
			return (EnumRotate) getEnum(EnumRotate.class, enumValue);
		}

		/**
		 * @return the map of enums
		 */
		public static Map getEnumMap()
		{
			return getEnumMap(EnumRotate.class);
		}

		/**
		 * @return the list of enums
		 */
		public static List getEnumList()
		{
			return getEnumList(EnumRotate.class);
		}

		/**
		 * @return the iterator
		 */
		public static Iterator iterator()
		{
			return iterator(EnumRotate.class);
		}

		/**  */
		public static final EnumRotate Rotate0 = new EnumRotate("Rotate0");
		/**  */
		public static final EnumRotate Rotate90 = new EnumRotate("Rotate90");
		/**  */
		public static final EnumRotate Rotate180 = new EnumRotate("Rotate180");
		/**  */
		public static final EnumRotate Rotate270 = new EnumRotate("Rotate270");
	}

	/**
	 * Enumeration strings for Sides
	 */

	public enum ESides
	{
		OneSidedBackFlipX, OneSidedBackFlipY, OneSidedFront, TwoSidedFlipX, TwoSidedFlipY;

		public static ESides getEnum(String val)
		{
			return JavaEnumUtil.getEnumIgnoreCase(ESides.class, val, ESides.OneSidedFront);
		}
	}

	/**
	 * Enumeration strings for Sides
	 */

	@SuppressWarnings("rawtypes")
	public static class EnumSides extends ValuedEnum
	{
		private static final long serialVersionUID = 1L;
		private static int m_startValue = 0;

		protected EnumSides(String name)
		{
			super(name, m_startValue++);
		}

		/**
		 * @param enumName the string to convert
		 * @return the enum
		 */
		public static EnumSides getEnum(String enumName)
		{
			return (EnumSides) getEnum(EnumSides.class, enumName);
		}

		/**
		 * @param enumValue the integer to convert
		 * @return the enum
		 */
		public static EnumSides getEnum(int enumValue)
		{
			return (EnumSides) getEnum(EnumSides.class, enumValue);
		}

		/**
		 * @return the map of enums
		 */
		public static Map getEnumMap()
		{
			return getEnumMap(EnumSides.class);
		}

		/**
		 * @return the list of enums
		 */
		public static List getEnumList()
		{
			return getEnumList(EnumSides.class);
		}

		/**
		 * @return the iterator
		 */
		public static Iterator iterator()
		{
			return iterator(EnumSides.class);
		}

		/**  */
		public static final EnumSides OneSidedBackFlipX = new EnumSides("OneSidedBackFlipX");
		/**  */
		public static final EnumSides OneSidedBackFlipY = new EnumSides("OneSidedBackFlipY");
		/**  */
		public static final EnumSides OneSidedFront = new EnumSides("OneSidedFront");
		/**  */
		public static final EnumSides TwoSidedFlipX = new EnumSides("TwoSidedFlipX");
		/**  */
		public static final EnumSides TwoSidedFlipY = new EnumSides("TwoSidedFlipY");
	}

	/**
	 * Enumeration strings for BindingEdge
	 */

	public enum EBindingEdge
	{
		Left, Right, Top, Bottom, None;

		public static EBindingEdge getEnum(String val)
		{
			return JavaEnumUtil.getEnumIgnoreCase(EBindingEdge.class, val, null);
		}
	}

	/**
	 * Enumeration strings for BindingEdge
	 */

	@SuppressWarnings("rawtypes")
	public static class EnumBindingEdge extends ValuedEnum
	{
		private static final long serialVersionUID = 1L;
		private static int m_startValue = 0;

		protected EnumBindingEdge(String name)
		{
			super(name, m_startValue++);
		}

		/**
		 * @param enumName the string to convert
		 * @return the enum
		 */
		public static EnumBindingEdge getEnum(String enumName)
		{
			return (EnumBindingEdge) getEnum(EnumBindingEdge.class, enumName);
		}

		/**
		 * @param enumValue the integer to convert
		 * @return the enum
		 */
		public static EnumBindingEdge getEnum(int enumValue)
		{
			return (EnumBindingEdge) getEnum(EnumBindingEdge.class, enumValue);
		}

		/**
		 * @return the map of enums
		 */
		public static Map getEnumMap()
		{
			return getEnumMap(EnumBindingEdge.class);
		}

		/**
		 * @return the list of enums
		 */
		public static List getEnumList()
		{
			return getEnumList(EnumBindingEdge.class);
		}

		/**
		 * @return the iterator
		 */
		public static Iterator iterator()
		{
			return iterator(EnumBindingEdge.class);
		}

		/**  */
		public static final EnumBindingEdge Left = new EnumBindingEdge("Left");
		/**  */
		public static final EnumBindingEdge Right = new EnumBindingEdge("Right");
		/**  */
		public static final EnumBindingEdge Top = new EnumBindingEdge("Top");
		/**  */
		public static final EnumBindingEdge Bottom = new EnumBindingEdge("Bottom");
		/**  */
		public static final EnumBindingEdge None = new EnumBindingEdge("None");
	}

	/*
	 * ************************************************************************ Attribute getter / setter ************************************************************************
	 */

	/*
	 * --------------------------------------------------------------------- Methods for Attribute FinishingOrder
	 * ---------------------------------------------------------------------
	 */
	/**
	 * (5) set attribute FinishingOrder
	 *
	 * @param enumVar the enumVar to set the attribute to
	 */
	public void setFinishingOrder(EFinishingOrder enumVar)
	{
		setAttribute(AttributeName.FINISHINGORDER, enumVar == null ? null : enumVar.name(), null);
	}

	/**
	 * (9) get attribute FinishingOrder
	 *
	 * @return the value of the attribute
	 */
	public EFinishingOrder getEFinishingOrder()
	{
		return EFinishingOrder.getEnum(getAttribute(AttributeName.FINISHINGORDER, null, "GatherFold"));
	}

	/*
	 * --------------------------------------------------------------------- Methods for Attribute FinishingOrder
	 * ---------------------------------------------------------------------
	 */
	/**
	 * (5) set attribute FinishingOrder
	 *
	 * @param enumVar the enumVar to set the attribute to
	 * @deprecated use java.lang.enum
	 */
	@Deprecated
	public void setFinishingOrder(EnumFinishingOrder enumVar)
	{
		setAttribute(AttributeName.FINISHINGORDER, enumVar == null ? null : enumVar.getName(), null);
	}

	/**
	 * (9) get attribute FinishingOrder
	 *
	 * @return the value of the attribute
	 */
	public EnumFinishingOrder getFinishingOrder()
	{
		return EnumFinishingOrder.getEnum(getAttribute(AttributeName.FINISHINGORDER, null, "GatherFold"));
	}

	/*
	 * --------------------------------------------------------------------- Methods for Attribute FoldCatalogOrientation
	 * ---------------------------------------------------------------------
	 */
	/**
	 * (5) set attribute FoldCatalogOrientation
	 *
	 * @param enumVar the enumVar to set the attribute to
	 */
	public void setFoldCatalogOrientation(EFoldCatalogOrientation enumVar)
	{
		setAttribute(AttributeName.FOLDCATALOGORIENTATION, enumVar == null ? null : enumVar.name(), null);
	}

	/**
	 * (9) get attribute FoldCatalogOrientation
	 *
	 * @return the value of the attribute
	 */
	public EFoldCatalogOrientation getEFoldCatalogOrientation()
	{
		return EFoldCatalogOrientation.getEnum(getAttribute(AttributeName.FOLDCATALOGORIENTATION, null, "Rotate0"));
	}

	/*
	 * --------------------------------------------------------------------- Methods for Attribute FoldCatalogOrientation
	 * ---------------------------------------------------------------------
	 */
	/**
	 * (5) set attribute FoldCatalogOrientation
	 *
	 * @param enumVar the enumVar to set the attribute to
	 * @deprecated use java.lang.enum
	 */
	@Deprecated
	public void setFoldCatalogOrientation(EnumFoldCatalogOrientation enumVar)
	{
		setAttribute(AttributeName.FOLDCATALOGORIENTATION, enumVar == null ? null : enumVar.getName(), null);
	}

	/**
	 * (9) get attribute FoldCatalogOrientation
	 *
	 * @return the value of the attribute
	 */
	public EnumFoldCatalogOrientation getFoldCatalogOrientation()
	{
		return EnumFoldCatalogOrientation.getEnum(getAttribute(AttributeName.FOLDCATALOGORIENTATION, null, "Rotate0"));
	}

	/*
	 * --------------------------------------------------------------------- Methods for Attribute PageDistributionScheme
	 * ---------------------------------------------------------------------
	 */
	/**
	 * (36) set attribute PageDistributionScheme
	 *
	 * @param value the value to set the attribute to
	 */
	public void setPageDistributionScheme(String value)
	{
		setAttribute(AttributeName.PAGEDISTRIBUTIONSCHEME, value, null);
	}

	/**
	 * (23) get String attribute PageDistributionScheme
	 *
	 * @return the value of the attribute
	 */
	public String getPageDistributionScheme()
	{
		return getAttribute(AttributeName.PAGEDISTRIBUTIONSCHEME, null, "Sequential");
	}

	/*
	 * --------------------------------------------------------------------- Methods for Attribute PageOrder ---------------------------------------------------------------------
	 */
	/**
	 * (36) set attribute PageOrder
	 *
	 * @param value the value to set the attribute to
	 */
	public void setPageOrder(String value)
	{
		setAttribute(AttributeName.PAGEORDER, value, null);
	}

	/**
	 * (23) get String attribute PageOrder
	 *
	 * @return the value of the attribute
	 */
	public String getPageOrder()
	{
		return getAttribute(AttributeName.PAGEORDER, null, "Reader");
	}

	/*
	 * --------------------------------------------------------------------- Methods for Attribute Rotate ---------------------------------------------------------------------
	 */
	/**
	 * (5) set attribute Rotate
	 *
	 * @param enumVar the enumVar to set the attribute to
	 */
	public void setRotate(ERotate enumVar)
	{
		setAttribute(AttributeName.ROTATE, enumVar == null ? null : enumVar.name(), null);
	}

	/**
	 * (9) get attribute Rotate
	 *
	 * @return the value of the attribute
	 */
	public ERotate getERotate()
	{
		return ERotate.getEnum(getAttribute(AttributeName.ROTATE, null, "Rotate0"));
	}

	/*
	 * --------------------------------------------------------------------- Methods for Attribute Rotate ---------------------------------------------------------------------
	 */
	/**
	 * (5) set attribute Rotate
	 *
	 * @param enumVar the enumVar to set the attribute to
	 * @deprecated use java.lang.enum
	 */
	@Deprecated
	public void setRotate(EnumRotate enumVar)
	{
		setAttribute(AttributeName.ROTATE, enumVar == null ? null : enumVar.getName(), null);
	}

	/**
	 * (9) get attribute Rotate
	 *
	 * @return the value of the attribute
	 */
	public EnumRotate getRotate()
	{
		return EnumRotate.getEnum(getAttribute(AttributeName.ROTATE, null, "Rotate0"));
	}

	/*
	 * --------------------------------------------------------------------- Methods for Attribute Sides ---------------------------------------------------------------------
	 */
	/**
	 * (5) set attribute Sides
	 *
	 * @param enumVar the enumVar to set the attribute to
	 */
	public void setSides(ESides enumVar)
	{
		setAttribute(AttributeName.SIDES, enumVar == null ? null : enumVar.name(), null);
	}

	/**
	 * (9) get attribute Sides
	 *
	 * @return the value of the attribute
	 */
	public ESides getESides()
	{
		return ESides.getEnum(getAttribute(AttributeName.SIDES, null, "OneSidedFront"));
	}

	/*
	 * --------------------------------------------------------------------- Methods for Attribute Sides ---------------------------------------------------------------------
	 */
	/**
	 * (5) set attribute Sides
	 *
	 * @param enumVar the enumVar to set the attribute to
	 * @deprecated use java.lang.enum
	 */
	@Deprecated
	public void setSides(EnumSides enumVar)
	{
		setAttribute(AttributeName.SIDES, enumVar == null ? null : enumVar.getName(), null);
	}

	/**
	 * (9) get attribute Sides
	 *
	 * @return the value of the attribute
	 */
	public EnumSides getSides()
	{
		return EnumSides.getEnum(getAttribute(AttributeName.SIDES, null, "OneSidedFront"));
	}

	/*
	 * --------------------------------------------------------------------- Methods for Attribute BindingEdge ---------------------------------------------------------------------
	 */
	/**
	 * (5) set attribute BindingEdge
	 *
	 * @param enumVar the enumVar to set the attribute to
	 */
	public void setBindingEdge(EBindingEdge enumVar)
	{
		setAttribute(AttributeName.BINDINGEDGE, enumVar == null ? null : enumVar.name(), null);
	}

	/**
	 * (9) get attribute BindingEdge
	 *
	 * @return the value of the attribute
	 */
	public EBindingEdge getEBindingEdge()
	{
		return EBindingEdge.getEnum(getAttribute(AttributeName.BINDINGEDGE, null, null));
	}

	/*
	 * --------------------------------------------------------------------- Methods for Attribute BindingEdge ---------------------------------------------------------------------
	 */
	/**
	 * (5) set attribute BindingEdge
	 *
	 * @param enumVar the enumVar to set the attribute to
	 * @deprecated use java.lang.enum
	 */
	@Deprecated
	public void setBindingEdge(EnumBindingEdge enumVar)
	{
		setAttribute(AttributeName.BINDINGEDGE, enumVar == null ? null : enumVar.getName(), null);
	}

	/**
	 * (9) get attribute BindingEdge
	 *
	 * @return the value of the attribute
	 */
	public EnumBindingEdge getBindingEdge()
	{
		return EnumBindingEdge.getEnum(getAttribute(AttributeName.BINDINGEDGE, null, null));
	}

	/*
	 * --------------------------------------------------------------------- Methods for Attribute BackMarkList
	 * ---------------------------------------------------------------------
	 */
	/**
	 * (36) set attribute BackMarkList
	 *
	 * @param value the value to set the attribute to
	 */
	public void setBackMarkList(VString value)
	{
		setAttribute(AttributeName.BACKMARKLIST, value, null);
	}

	/**
	 * (21) get VString attribute BackMarkList
	 *
	 * @return VString the value of the attribute
	 */
	public VString getBackMarkList()
	{
		final VString vStrAttrib = new VString();
		final String s = getAttribute(AttributeName.BACKMARKLIST, null, JDFCoreConstants.EMPTYSTRING);
		vStrAttrib.setAllStrings(s, " ");
		return vStrAttrib;
	}

	/*
	 * --------------------------------------------------------------------- Methods for Attribute CreepValue ---------------------------------------------------------------------
	 */
	/**
	 * (36) set attribute CreepValue
	 *
	 * @param value the value to set the attribute to
	 */
	public void setCreepValue(JDFXYPair value)
	{
		setAttribute(AttributeName.CREEPVALUE, value, null);
	}

	/**
	 * (20) get JDFXYPair attribute CreepValue
	 *
	 * @return JDFXYPair the value of the attribute, null if a the attribute value is not a valid to create a JDFXYPair
	 */
	public JDFXYPair getCreepValue()
	{
		final String strAttrName = getAttribute(AttributeName.CREEPVALUE, null, null);
		final JDFXYPair nPlaceHolder = JDFXYPair.createXYPair(strAttrName);
		return nPlaceHolder;
	}

	/*
	 * --------------------------------------------------------------------- Methods for Attribute FoldCatalog ---------------------------------------------------------------------
	 */
	/**
	 * (36) set attribute FoldCatalog
	 *
	 * @param value the value to set the attribute to
	 */
	public void setFoldCatalog(String value)
	{
		setAttribute(AttributeName.FOLDCATALOG, value, null);
	}

	/**
	 * (23) get String attribute FoldCatalog
	 *
	 * @return the value of the attribute
	 */
	public String getFoldCatalog()
	{
		return getAttribute(AttributeName.FOLDCATALOG, null, JDFCoreConstants.EMPTYSTRING);
	}

	/*
	 * --------------------------------------------------------------------- Methods for Attribute FrontMarkList
	 * ---------------------------------------------------------------------
	 */
	/**
	 * (36) set attribute FrontMarkList
	 *
	 * @param value the value to set the attribute to
	 */
	public void setFrontMarkList(VString value)
	{
		setAttribute(AttributeName.FRONTMARKLIST, value, null);
	}

	/**
	 * (21) get VString attribute FrontMarkList
	 *
	 * @return VString the value of the attribute
	 */
	public VString getFrontMarkList()
	{
		final VString vStrAttrib = new VString();
		final String s = getAttribute(AttributeName.FRONTMARKLIST, null, JDFCoreConstants.EMPTYSTRING);
		vStrAttrib.setAllStrings(s, " ");
		return vStrAttrib;
	}

	/*
	 * --------------------------------------------------------------------- Methods for Attribute Gutter ---------------------------------------------------------------------
	 */
	/**
	 * (36) set attribute Gutter
	 *
	 * @param value the value to set the attribute to
	 */
	public void setGutter(JDFXYPair value)
	{
		setAttribute(AttributeName.GUTTER, value, null);
	}

	/**
	 * (20) get JDFXYPair attribute Gutter
	 *
	 * @return JDFXYPair the value of the attribute, null if a the attribute value is not a valid to create a JDFXYPair
	 */
	public JDFXYPair getGutter()
	{
		final String strAttrName = getAttribute(AttributeName.GUTTER, null, null);
		final JDFXYPair nPlaceHolder = JDFXYPair.createXYPair(strAttrName);
		return nPlaceHolder;
	}

	/*
	 * --------------------------------------------------------------------- Methods for Attribute GutterMinimumLimit
	 * ---------------------------------------------------------------------
	 */
	/**
	 * (36) set attribute GutterMinimumLimit
	 *
	 * @param value the value to set the attribute to
	 */
	public void setGutterMinimumLimit(JDFXYPair value)
	{
		setAttribute(AttributeName.GUTTERMINIMUMLIMIT, value, null);
	}

	/**
	 * (20) get JDFXYPair attribute GutterMinimumLimit
	 *
	 * @return JDFXYPair the value of the attribute, null if a the attribute value is not a valid to create a JDFXYPair
	 */
	public JDFXYPair getGutterMinimumLimit()
	{
		final String strAttrName = getAttribute(AttributeName.GUTTERMINIMUMLIMIT, null, null);
		final JDFXYPair nPlaceHolder = JDFXYPair.createXYPair(strAttrName);
		return nPlaceHolder;
	}

	/*
	 * --------------------------------------------------------------------- Methods for Attribute HorizontalCreep
	 * ---------------------------------------------------------------------
	 */
	/**
	 * (36) set attribute HorizontalCreep
	 *
	 * @param value the value to set the attribute to
	 */
	public void setHorizontalCreep(JDFIntegerList value)
	{
		setAttribute(AttributeName.HORIZONTALCREEP, value, null);
	}

	/**
	 * (20) get JDFIntegerList attribute HorizontalCreep
	 *
	 * @return JDFIntegerList the value of the attribute, null if a the attribute value is not a valid to create a JDFIntegerList
	 */
	public JDFIntegerList getHorizontalCreep()
	{
		final String strAttrName = getAttribute(AttributeName.HORIZONTALCREEP, null, null);
		final JDFIntegerList nPlaceHolder = JDFIntegerList.createIntegerList(strAttrName);
		return nPlaceHolder;
	}

	/*
	 * --------------------------------------------------------------------- Methods for Attribute ImplicitGutter
	 * ---------------------------------------------------------------------
	 */
	/**
	 * (36) set attribute ImplicitGutter
	 *
	 * @param value the value to set the attribute to
	 */
	public void setImplicitGutter(JDFXYPair value)
	{
		setAttribute(AttributeName.IMPLICITGUTTER, value, null);
	}

	/**
	 * (20) get JDFXYPair attribute ImplicitGutter
	 *
	 * @return JDFXYPair the value of the attribute, null if a the attribute value is not a valid to create a JDFXYPair
	 */
	public JDFXYPair getImplicitGutter()
	{
		final String strAttrName = getAttribute(AttributeName.IMPLICITGUTTER, null, null);
		final JDFXYPair nPlaceHolder = JDFXYPair.createXYPair(strAttrName);
		return nPlaceHolder;
	}

	/*
	 * --------------------------------------------------------------------- Methods for Attribute ImplicitGutterMinimumLimit
	 * ---------------------------------------------------------------------
	 */
	/**
	 * (36) set attribute ImplicitGutterMinimumLimit
	 *
	 * @param value the value to set the attribute to
	 */
	public void setImplicitGutterMinimumLimit(JDFXYPair value)
	{
		setAttribute(AttributeName.IMPLICITGUTTERMINIMUMLIMIT, value, null);
	}

	/**
	 * (20) get JDFXYPair attribute ImplicitGutterMinimumLimit
	 *
	 * @return JDFXYPair the value of the attribute, null if a the attribute value is not a valid to create a JDFXYPair
	 */
	public JDFXYPair getImplicitGutterMinimumLimit()
	{
		final String strAttrName = getAttribute(AttributeName.IMPLICITGUTTERMINIMUMLIMIT, null, null);
		final JDFXYPair nPlaceHolder = JDFXYPair.createXYPair(strAttrName);
		return nPlaceHolder;
	}

	/*
	 * --------------------------------------------------------------------- Methods for Attribute NumberUp ---------------------------------------------------------------------
	 */
	/**
	 * (36) set attribute NumberUp
	 *
	 * @param value the value to set the attribute to
	 */
	public void setNumberUp(JDFXYPair value)
	{
		setAttribute(AttributeName.NUMBERUP, value, null);
	}

	/**
	 * (20) get JDFXYPair attribute NumberUp
	 *
	 * @return JDFXYPair the value of the attribute, null if a the attribute value is not a valid to create a JDFXYPair
	 */
	public JDFXYPair getNumberUp()
	{
		final String strAttrName = getAttribute(AttributeName.NUMBERUP, null, null);
		final JDFXYPair nPlaceHolder = JDFXYPair.createXYPair(strAttrName);
		return nPlaceHolder;
	}

	/*
	 * --------------------------------------------------------------------- Methods for Attribute PresentationDirection
	 * ---------------------------------------------------------------------
	 */
	/**
	 * (36) set attribute PresentationDirection
	 *
	 * @param value the value to set the attribute to
	 */
	public void setPresentationDirection(String value)
	{
		setAttribute(AttributeName.PRESENTATIONDIRECTION, value, null);
	}

	/**
	 * (23) get String attribute PresentationDirection
	 *
	 * @return the value of the attribute
	 */
	public String getPresentationDirection()
	{
		return getAttribute(AttributeName.PRESENTATIONDIRECTION, null, JDFCoreConstants.EMPTYSTRING);
	}

	/*
	 * --------------------------------------------------------------------- Methods for Attribute StackDepth ---------------------------------------------------------------------
	 */
	/**
	 * (36) set attribute StackDepth
	 *
	 * @param value the value to set the attribute to
	 */
	public void setStackDepth(int value)
	{
		setAttribute(AttributeName.STACKDEPTH, value, null);
	}

	/**
	 * (15) get int attribute StackDepth
	 *
	 * @return int the value of the attribute
	 */
	public int getStackDepth()
	{
		return getIntAttribute(AttributeName.STACKDEPTH, null, 0);
	}

	/*
	 * --------------------------------------------------------------------- Methods for Attribute StepDocs ---------------------------------------------------------------------
	 */
	/**
	 * (36) set attribute StepDocs
	 *
	 * @param value the value to set the attribute to
	 */
	public void setStepDocs(JDFXYPair value)
	{
		setAttribute(AttributeName.STEPDOCS, value, null);
	}

	/**
	 * (20) get JDFXYPair attribute StepDocs
	 *
	 * @return JDFXYPair the value of the attribute, null if a the attribute value is not a valid to create a JDFXYPair
	 */
	public JDFXYPair getStepDocs()
	{
		final String strAttrName = getAttribute(AttributeName.STEPDOCS, null, null);
		final JDFXYPair nPlaceHolder = JDFXYPair.createXYPair(strAttrName);
		return nPlaceHolder;
	}

	/*
	 * --------------------------------------------------------------------- Methods for Attribute StepRepeat ---------------------------------------------------------------------
	 */
	/**
	 * (36) set attribute StepRepeat
	 *
	 * @param value the value to set the attribute to
	 */
	public void setStepRepeat(JDFIntegerList value)
	{
		setAttribute(AttributeName.STEPREPEAT, value, null);
	}

	/**
	 * (20) get JDFIntegerList attribute StepRepeat
	 *
	 * @return JDFIntegerList the value of the attribute, null if a the attribute value is not a valid to create a JDFIntegerList
	 */
	public JDFIntegerList getStepRepeat()
	{
		final String strAttrName = getAttribute(AttributeName.STEPREPEAT, null, null);
		final JDFIntegerList nPlaceHolder = JDFIntegerList.createIntegerList(strAttrName);
		return nPlaceHolder;
	}

	/*
	 * --------------------------------------------------------------------- Methods for Attribute SurfaceContentsBox
	 * ---------------------------------------------------------------------
	 */
	/**
	 * (36) set attribute SurfaceContentsBox
	 *
	 * @param value the value to set the attribute to
	 */
	public void setSurfaceContentsBox(JDFRectangle value)
	{
		setAttribute(AttributeName.SURFACECONTENTSBOX, value, null);
	}

	/**
	 * (20) get JDFRectangle attribute SurfaceContentsBox
	 *
	 * @return JDFRectangle the value of the attribute, null if a the attribute value is not a valid to create a JDFRectangle
	 */
	public JDFRectangle getSurfaceContentsBox()
	{
		final String strAttrName = getAttribute(AttributeName.SURFACECONTENTSBOX, null, null);
		final JDFRectangle nPlaceHolder = JDFRectangle.createRectangle(strAttrName);
		return nPlaceHolder;
	}

	/*
	 * --------------------------------------------------------------------- Methods for Attribute VerticalCreep
	 * ---------------------------------------------------------------------
	 */
	/**
	 * (36) set attribute VerticalCreep
	 *
	 * @param value the value to set the attribute to
	 */
	public void setVerticalCreep(JDFIntegerList value)
	{
		setAttribute(AttributeName.VERTICALCREEP, value, null);
	}

	/**
	 * (20) get JDFIntegerList attribute VerticalCreep
	 *
	 * @return JDFIntegerList the value of the attribute, null if a the attribute value is not a valid to create a JDFIntegerList
	 */
	public JDFIntegerList getVerticalCreep()
	{
		final String strAttrName = getAttribute(AttributeName.VERTICALCREEP, null, null);
		final JDFIntegerList nPlaceHolder = JDFIntegerList.createIntegerList(strAttrName);
		return nPlaceHolder;
	}

	/*
	 * *********************************************************************** Element getter / setter ***********************************************************************
	 */

	/**
	 * (24) const get element ImageShift
	 *
	 * @return JDFImageShift the element
	 */
	public JDFImageShift getImageShift()
	{
		return (JDFImageShift) getElement(ElementName.IMAGESHIFT, null, 0);
	}

	/**
	 * (25) getCreateImageShift
	 * 
	 * @return JDFImageShift the element
	 */
	public JDFImageShift getCreateImageShift()
	{
		return (JDFImageShift) getCreateElement_JDFElement(ElementName.IMAGESHIFT, null, 0);
	}

	/**
	 * (29) append element ImageShift
	 *
	 * @return JDFImageShift the element @ if the element already exists
	 */
	public JDFImageShift appendImageShift()
	{
		return (JDFImageShift) appendElementN(ElementName.IMAGESHIFT, 1, null);
	}

	/**
	 * (24) const get element InsertSheet
	 *
	 * @return JDFInsertSheet the element
	 */
	public JDFInsertSheet getInsertSheet()
	{
		return (JDFInsertSheet) getElement(ElementName.INSERTSHEET, null, 0);
	}

	/**
	 * (25) getCreateInsertSheet
	 * 
	 * @return JDFInsertSheet the element
	 */
	public JDFInsertSheet getCreateInsertSheet()
	{
		return (JDFInsertSheet) getCreateElement_JDFElement(ElementName.INSERTSHEET, null, 0);
	}

	/**
	 * (26) getCreateInsertSheet
	 * 
	 * @param iSkip number of elements to skip
	 * @return JDFInsertSheet the element
	 */
	public JDFInsertSheet getCreateInsertSheet(int iSkip)
	{
		return (JDFInsertSheet) getCreateElement_JDFElement(ElementName.INSERTSHEET, null, iSkip);
	}

	/**
	 * (27) const get element InsertSheet
	 *
	 * @param iSkip number of elements to skip
	 * @return JDFInsertSheet the element default is getInsertSheet(0)
	 */
	public JDFInsertSheet getInsertSheet(int iSkip)
	{
		return (JDFInsertSheet) getElement(ElementName.INSERTSHEET, null, iSkip);
	}

	/**
	 * Get all InsertSheet from the current element
	 * 
	 * @return Collection<JDFInsertSheet>, null if none are available
	 */
	public Collection<JDFInsertSheet> getAllInsertSheet()
	{
		return getChildArrayByClass(JDFInsertSheet.class, false, 0);
	}

	/**
	 * (30) append element InsertSheet
	 *
	 * @return JDFInsertSheet the element
	 */
	public JDFInsertSheet appendInsertSheet()
	{
		return (JDFInsertSheet) appendElement(ElementName.INSERTSHEET, null);
	}

	/**
	 * (31) create inter-resource link to refTarget
	 *
	 * @param refTarget the element that is referenced
	 */
	public void refInsertSheet(JDFInsertSheet refTarget)
	{
		refElement(refTarget);
	}

	/**
	 * (24) const get element DeviceMark
	 *
	 * @return JDFDeviceMark the element
	 */
	public JDFDeviceMark getDeviceMark()
	{
		return (JDFDeviceMark) getElement(ElementName.DEVICEMARK, null, 0);
	}

	/**
	 * (25) getCreateDeviceMark
	 * 
	 * @return JDFDeviceMark the element
	 */
	public JDFDeviceMark getCreateDeviceMark()
	{
		return (JDFDeviceMark) getCreateElement_JDFElement(ElementName.DEVICEMARK, null, 0);
	}

	/**
	 * (29) append element DeviceMark
	 *
	 * @return JDFDeviceMark the element @ if the element already exists
	 */
	public JDFDeviceMark appendDeviceMark()
	{
		return (JDFDeviceMark) appendElementN(ElementName.DEVICEMARK, 1, null);
	}

	/**
	 * (24) const get element ExternalImpositionTemplate
	 *
	 * @return JDFExternalImpositionTemplate the element
	 */
	public JDFExternalImpositionTemplate getExternalImpositionTemplate()
	{
		return (JDFExternalImpositionTemplate) getElement(ElementName.EXTERNALIMPOSITIONTEMPLATE, null, 0);
	}

	/**
	 * (25) getCreateExternalImpositionTemplate
	 * 
	 * @return JDFExternalImpositionTemplate the element
	 */
	public JDFExternalImpositionTemplate getCreateExternalImpositionTemplate()
	{
		return (JDFExternalImpositionTemplate) getCreateElement_JDFElement(ElementName.EXTERNALIMPOSITIONTEMPLATE, null, 0);
	}

	/**
	 * (29) append element ExternalImpositionTemplate
	 *
	 * @return JDFExternalImpositionTemplate the element @ if the element already exists
	 */
	public JDFExternalImpositionTemplate appendExternalImpositionTemplate()
	{
		return (JDFExternalImpositionTemplate) appendElementN(ElementName.EXTERNALIMPOSITIONTEMPLATE, 1, null);
	}

	/**
	 * (31) create inter-resource link to refTarget
	 *
	 * @param refTarget the element that is referenced
	 */
	public void refExternalImpositionTemplate(JDFExternalImpositionTemplate refTarget)
	{
		refElement(refTarget);
	}

	/**
	 * (24) const get element FitPolicy
	 *
	 * @return JDFFitPolicy the element
	 */
	public JDFFitPolicy getFitPolicy()
	{
		return (JDFFitPolicy) getElement(ElementName.FITPOLICY, null, 0);
	}

	/**
	 * (25) getCreateFitPolicy
	 * 
	 * @return JDFFitPolicy the element
	 */
	public JDFFitPolicy getCreateFitPolicy()
	{
		return (JDFFitPolicy) getCreateElement_JDFElement(ElementName.FITPOLICY, null, 0);
	}

	/**
	 * (29) append element FitPolicy
	 *
	 * @return JDFFitPolicy the element @ if the element already exists
	 */
	public JDFFitPolicy appendFitPolicy()
	{
		return (JDFFitPolicy) appendElementN(ElementName.FITPOLICY, 1, null);
	}

	/**
	 * (24) const get element JobField
	 *
	 * @return JDFJobField the element
	 */
	public JDFJobField getJobField()
	{
		return (JDFJobField) getElement(ElementName.JOBFIELD, null, 0);
	}

	/**
	 * (25) getCreateJobField
	 * 
	 * @return JDFJobField the element
	 */
	public JDFJobField getCreateJobField()
	{
		return (JDFJobField) getCreateElement_JDFElement(ElementName.JOBFIELD, null, 0);
	}

	/**
	 * (26) getCreateJobField
	 * 
	 * @param iSkip number of elements to skip
	 * @return JDFJobField the element
	 */
	public JDFJobField getCreateJobField(int iSkip)
	{
		return (JDFJobField) getCreateElement_JDFElement(ElementName.JOBFIELD, null, iSkip);
	}

	/**
	 * (27) const get element JobField
	 *
	 * @param iSkip number of elements to skip
	 * @return JDFJobField the element default is getJobField(0)
	 */
	public JDFJobField getJobField(int iSkip)
	{
		return (JDFJobField) getElement(ElementName.JOBFIELD, null, iSkip);
	}

	/**
	 * Get all JobField from the current element
	 * 
	 * @return Collection<JDFJobField>, null if none are available
	 */
	public Collection<JDFJobField> getAllJobField()
	{
		return getChildArrayByClass(JDFJobField.class, false, 0);
	}

	/**
	 * (30) append element JobField
	 *
	 * @return JDFJobField the element
	 */
	public JDFJobField appendJobField()
	{
		return (JDFJobField) appendElement(ElementName.JOBFIELD, null);
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
	 * (24) const get element PageCell
	 *
	 * @return JDFPageCell the element
	 */
	public JDFPageCell getPageCell()
	{
		return (JDFPageCell) getElement(ElementName.PAGECELL, null, 0);
	}

	/**
	 * (25) getCreatePageCell
	 * 
	 * @return JDFPageCell the element
	 */
	public JDFPageCell getCreatePageCell()
	{
		return (JDFPageCell) getCreateElement_JDFElement(ElementName.PAGECELL, null, 0);
	}

	/**
	 * (29) append element PageCell
	 *
	 * @return JDFPageCell the element @ if the element already exists
	 */
	public JDFPageCell appendPageCell()
	{
		return (JDFPageCell) appendElementN(ElementName.PAGECELL, 1, null);
	}

}
