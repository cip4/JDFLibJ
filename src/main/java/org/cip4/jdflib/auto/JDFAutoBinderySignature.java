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
import org.cip4.jdflib.datatypes.JDFNumberList;
import org.cip4.jdflib.datatypes.JDFXYPair;
import org.cip4.jdflib.resource.JDFResource;
import org.cip4.jdflib.resource.process.JDFDieLayout;
import org.cip4.jdflib.resource.process.JDFSignatureCell;
import org.cip4.jdflib.resource.process.postpress.JDFFold;
import org.cip4.jdflib.util.JavaEnumUtil;

/**
 *****************************************************************************
 * class JDFAutoBinderySignature : public JDFResource
 *****************************************************************************
 * 
 */

public abstract class JDFAutoBinderySignature extends JDFResource
{

	private static final long serialVersionUID = 1L;

	private static AtrInfoTable[] atrInfoTable = new AtrInfoTable[23];
	static
	{
		atrInfoTable[0] = new AtrInfoTable(AttributeName.BINDERYSIGNATURETYPE, 0x3333333111l, AttributeInfo.EnumAttributeType.enumeration, EnumBinderySignatureType.getEnum(0),
				"Fold");
		atrInfoTable[1] = new AtrInfoTable(AttributeName.BINDINGEDGE, 0x3333333311l, AttributeInfo.EnumAttributeType.enumeration, EnumBindingEdge.getEnum(0), "Left");
		atrInfoTable[2] = new AtrInfoTable(AttributeName.JOGEDGE, 0x3333333111l, AttributeInfo.EnumAttributeType.enumeration, EnumJogEdge.getEnum(0), "Top");
		atrInfoTable[3] = new AtrInfoTable(AttributeName.NUMBERUP, 0x3333333311l, AttributeInfo.EnumAttributeType.XYPair, null, "1 1");
		atrInfoTable[4] = new AtrInfoTable(AttributeName.ALIGNMENTREFERENCEWEB, 0x3333331111l, AttributeInfo.EnumAttributeType.NMTOKEN, null, null);
		atrInfoTable[5] = new AtrInfoTable(AttributeName.BINDINGORIENTATION, 0x3333333111l, AttributeInfo.EnumAttributeType.enumeration, EnumBindingOrientation.getEnum(0), null);
		atrInfoTable[6] = new AtrInfoTable(AttributeName.BLEEDBOTTOM, 0x3333311111l, AttributeInfo.EnumAttributeType.double_, null, null);
		atrInfoTable[7] = new AtrInfoTable(AttributeName.BLEEDLEFT, 0x3333311111l, AttributeInfo.EnumAttributeType.double_, null, null);
		atrInfoTable[8] = new AtrInfoTable(AttributeName.BLEEDRIGHT, 0x3333311111l, AttributeInfo.EnumAttributeType.double_, null, null);
		atrInfoTable[9] = new AtrInfoTable(AttributeName.BLEEDTOP, 0x3333311111l, AttributeInfo.EnumAttributeType.double_, null, null);
		atrInfoTable[10] = new AtrInfoTable(AttributeName.BOTTLING, 0x3333111111l, AttributeInfo.EnumAttributeType.enumeration, EnumBottling.getEnum(0), null);
		atrInfoTable[11] = new AtrInfoTable(AttributeName.FOLDCATALOG, 0x3333333311l, AttributeInfo.EnumAttributeType.string, null, null);
		atrInfoTable[12] = new AtrInfoTable(AttributeName.FOLDLAY, 0x3333331111l, AttributeInfo.EnumAttributeType.enumeration, EnumFoldLay.getEnum(0), null);
		atrInfoTable[13] = new AtrInfoTable(AttributeName.OUTSIDEGUTTER, 0x3333333111l, AttributeInfo.EnumAttributeType.boolean_, null, null);
		atrInfoTable[14] = new AtrInfoTable(AttributeName.SPREADTYPE, 0x3331111111l, AttributeInfo.EnumAttributeType.enumeration, EnumSpreadType.getEnum(0), null);
		atrInfoTable[15] = new AtrInfoTable(AttributeName.STAGGERCOLUMNS, 0x3333333111l, AttributeInfo.EnumAttributeType.string, null, null);
		atrInfoTable[16] = new AtrInfoTable(AttributeName.STAGGERCONTINUOUS, 0x3333333111l, AttributeInfo.EnumAttributeType.boolean_, null, null);
		atrInfoTable[17] = new AtrInfoTable(AttributeName.STAGGERROWS, 0x3333333111l, AttributeInfo.EnumAttributeType.string, null, null);
		atrInfoTable[18] = new AtrInfoTable(AttributeName.TRIMBOTTOM, 0x3333311111l, AttributeInfo.EnumAttributeType.double_, null, null);
		atrInfoTable[19] = new AtrInfoTable(AttributeName.TRIMLEFT, 0x3333311111l, AttributeInfo.EnumAttributeType.double_, null, null);
		atrInfoTable[20] = new AtrInfoTable(AttributeName.TRIMRIGHT, 0x3333311111l, AttributeInfo.EnumAttributeType.double_, null, null);
		atrInfoTable[21] = new AtrInfoTable(AttributeName.TRIMTOP, 0x3333311111l, AttributeInfo.EnumAttributeType.double_, null, null);
		atrInfoTable[22] = new AtrInfoTable(AttributeName.WEBCELLALIGNMENT, 0x3333331111l, AttributeInfo.EnumAttributeType.XYPair, null, null);
	}

	@Override
	protected AttributeInfo getTheAttributeInfo()
	{
		return super.getTheAttributeInfo().updateReplace(atrInfoTable);
	}

	private static ElemInfoTable[] elemInfoTable = new ElemInfoTable[3];
	static
	{
		elemInfoTable[0] = new ElemInfoTable(ElementName.DIELAYOUT, 0x6666666611l);
		elemInfoTable[1] = new ElemInfoTable(ElementName.FOLD, 0x3333333311l);
		elemInfoTable[2] = new ElemInfoTable(ElementName.SIGNATURECELL, 0x3333333311l);
	}

	@Override
	protected ElementInfo getTheElementInfo()
	{
		return super.getTheElementInfo().updateReplace(elemInfoTable);
	}

	/**
	 * Constructor for JDFAutoBinderySignature
	 *
	 * @param myOwnerDocument
	 * @param qualifiedName
	 */
	protected JDFAutoBinderySignature(CoreDocumentImpl myOwnerDocument, String qualifiedName)
	{
		super(myOwnerDocument, qualifiedName);
	}

	/**
	 * Constructor for JDFAutoBinderySignature
	 *
	 * @param myOwnerDocument
	 * @param myNamespaceURI
	 * @param qualifiedName
	 */
	protected JDFAutoBinderySignature(CoreDocumentImpl myOwnerDocument, String myNamespaceURI, String qualifiedName)
	{
		super(myOwnerDocument, myNamespaceURI, qualifiedName);
	}

	/**
	 * Constructor for JDFAutoBinderySignature
	 *
	 * @param myOwnerDocument
	 * @param myNamespaceURI
	 * @param qualifiedName
	 * @param myLocalName
	 */
	protected JDFAutoBinderySignature(CoreDocumentImpl myOwnerDocument, String myNamespaceURI, String qualifiedName, String myLocalName)
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
	 * Enumeration strings for BinderySignatureType
	 */

	public enum EBinderySignatureType
	{
		Die, Fold, Grid;

		public static EBinderySignatureType getEnum(String val)
		{
			return JavaEnumUtil.getEnumIgnoreCase(EBinderySignatureType.class, val, EBinderySignatureType.Fold);
		}
	}

	/**
	 * Enumeration strings for BinderySignatureType
	 */

	@SuppressWarnings("rawtypes")
	public static class EnumBinderySignatureType extends ValuedEnum
	{
		private static final long serialVersionUID = 1L;
		private static int m_startValue = 0;

		protected EnumBinderySignatureType(String name)
		{
			super(name, m_startValue++);
		}

		/**
		 * @param enumName the string to convert
		 * @return the enum
		 */
		public static EnumBinderySignatureType getEnum(String enumName)
		{
			return (EnumBinderySignatureType) getEnum(EnumBinderySignatureType.class, enumName);
		}

		/**
		 * @param enumValue the integer to convert
		 * @return the enum
		 */
		public static EnumBinderySignatureType getEnum(int enumValue)
		{
			return (EnumBinderySignatureType) getEnum(EnumBinderySignatureType.class, enumValue);
		}

		/**
		 * @return the map of enums
		 */
		public static Map getEnumMap()
		{
			return getEnumMap(EnumBinderySignatureType.class);
		}

		/**
		 * @return the list of enums
		 */
		public static List getEnumList()
		{
			return getEnumList(EnumBinderySignatureType.class);
		}

		/**
		 * @return the iterator
		 */
		public static Iterator iterator()
		{
			return iterator(EnumBinderySignatureType.class);
		}

		/**  */
		public static final EnumBinderySignatureType Die = new EnumBinderySignatureType("Die");
		/**  */
		public static final EnumBinderySignatureType Fold = new EnumBinderySignatureType("Fold");
		/**  */
		public static final EnumBinderySignatureType Grid = new EnumBinderySignatureType("Grid");
	}

	/**
	 * Enumeration strings for BindingEdge
	 */

	public enum EBindingEdge
	{
		Left, Right, Top, Bottom, None;

		public static EBindingEdge getEnum(String val)
		{
			return JavaEnumUtil.getEnumIgnoreCase(EBindingEdge.class, val, EBindingEdge.Left);
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

	/**
	 * Enumeration strings for JogEdge
	 */

	public enum EJogEdge
	{
		Left, Right, Top, Bottom, None;

		public static EJogEdge getEnum(String val)
		{
			return JavaEnumUtil.getEnumIgnoreCase(EJogEdge.class, val, EJogEdge.Top);
		}
	}

	/**
	 * Enumeration strings for JogEdge
	 */

	@SuppressWarnings("rawtypes")
	public static class EnumJogEdge extends ValuedEnum
	{
		private static final long serialVersionUID = 1L;
		private static int m_startValue = 0;

		protected EnumJogEdge(String name)
		{
			super(name, m_startValue++);
		}

		/**
		 * @param enumName the string to convert
		 * @return the enum
		 */
		public static EnumJogEdge getEnum(String enumName)
		{
			return (EnumJogEdge) getEnum(EnumJogEdge.class, enumName);
		}

		/**
		 * @param enumValue the integer to convert
		 * @return the enum
		 */
		public static EnumJogEdge getEnum(int enumValue)
		{
			return (EnumJogEdge) getEnum(EnumJogEdge.class, enumValue);
		}

		/**
		 * @return the map of enums
		 */
		public static Map getEnumMap()
		{
			return getEnumMap(EnumJogEdge.class);
		}

		/**
		 * @return the list of enums
		 */
		public static List getEnumList()
		{
			return getEnumList(EnumJogEdge.class);
		}

		/**
		 * @return the iterator
		 */
		public static Iterator iterator()
		{
			return iterator(EnumJogEdge.class);
		}

		/**  */
		public static final EnumJogEdge Left = new EnumJogEdge("Left");
		/**  */
		public static final EnumJogEdge Right = new EnumJogEdge("Right");
		/**  */
		public static final EnumJogEdge Top = new EnumJogEdge("Top");
		/**  */
		public static final EnumJogEdge Bottom = new EnumJogEdge("Bottom");
		/**  */
		public static final EnumJogEdge None = new EnumJogEdge("None");
	}

	/**
	 * Enumeration strings for BindingOrientation
	 */

	public enum EBindingOrientation
	{
		Rotate0, Rotate90, Rotate180, Rotate270, Flip0, Flip90, Flip180, Flip270;

		public static EBindingOrientation getEnum(String val)
		{
			return JavaEnumUtil.getEnumIgnoreCase(EBindingOrientation.class, val, null);
		}
	}

	/**
	 * Enumeration strings for BindingOrientation
	 */

	@SuppressWarnings("rawtypes")
	public static class EnumBindingOrientation extends ValuedEnum
	{
		private static final long serialVersionUID = 1L;
		private static int m_startValue = 0;

		protected EnumBindingOrientation(String name)
		{
			super(name, m_startValue++);
		}

		/**
		 * @param enumName the string to convert
		 * @return the enum
		 */
		public static EnumBindingOrientation getEnum(String enumName)
		{
			return (EnumBindingOrientation) getEnum(EnumBindingOrientation.class, enumName);
		}

		/**
		 * @param enumValue the integer to convert
		 * @return the enum
		 */
		public static EnumBindingOrientation getEnum(int enumValue)
		{
			return (EnumBindingOrientation) getEnum(EnumBindingOrientation.class, enumValue);
		}

		/**
		 * @return the map of enums
		 */
		public static Map getEnumMap()
		{
			return getEnumMap(EnumBindingOrientation.class);
		}

		/**
		 * @return the list of enums
		 */
		public static List getEnumList()
		{
			return getEnumList(EnumBindingOrientation.class);
		}

		/**
		 * @return the iterator
		 */
		public static Iterator iterator()
		{
			return iterator(EnumBindingOrientation.class);
		}

		/**  */
		public static final EnumBindingOrientation Rotate0 = new EnumBindingOrientation("Rotate0");
		/**  */
		public static final EnumBindingOrientation Rotate90 = new EnumBindingOrientation("Rotate90");
		/**  */
		public static final EnumBindingOrientation Rotate180 = new EnumBindingOrientation("Rotate180");
		/**  */
		public static final EnumBindingOrientation Rotate270 = new EnumBindingOrientation("Rotate270");
		/**  */
		public static final EnumBindingOrientation Flip0 = new EnumBindingOrientation("Flip0");
		/**  */
		public static final EnumBindingOrientation Flip90 = new EnumBindingOrientation("Flip90");
		/**  */
		public static final EnumBindingOrientation Flip180 = new EnumBindingOrientation("Flip180");
		/**  */
		public static final EnumBindingOrientation Flip270 = new EnumBindingOrientation("Flip270");
	}

	/**
	 * Enumeration strings for Bottling
	 */

	public enum EBottling
	{
		All, Last, None;

		public static EBottling getEnum(String val)
		{
			return JavaEnumUtil.getEnumIgnoreCase(EBottling.class, val, null);
		}
	}

	/**
	 * Enumeration strings for Bottling
	 */

	@SuppressWarnings("rawtypes")
	public static class EnumBottling extends ValuedEnum
	{
		private static final long serialVersionUID = 1L;
		private static int m_startValue = 0;

		protected EnumBottling(String name)
		{
			super(name, m_startValue++);
		}

		/**
		 * @param enumName the string to convert
		 * @return the enum
		 */
		public static EnumBottling getEnum(String enumName)
		{
			return (EnumBottling) getEnum(EnumBottling.class, enumName);
		}

		/**
		 * @param enumValue the integer to convert
		 * @return the enum
		 */
		public static EnumBottling getEnum(int enumValue)
		{
			return (EnumBottling) getEnum(EnumBottling.class, enumValue);
		}

		/**
		 * @return the map of enums
		 */
		public static Map getEnumMap()
		{
			return getEnumMap(EnumBottling.class);
		}

		/**
		 * @return the list of enums
		 */
		public static List getEnumList()
		{
			return getEnumList(EnumBottling.class);
		}

		/**
		 * @return the iterator
		 */
		public static Iterator iterator()
		{
			return iterator(EnumBottling.class);
		}

		/**  */
		public static final EnumBottling All = new EnumBottling("All");
		/**  */
		public static final EnumBottling Last = new EnumBottling("Last");
		/**  */
		public static final EnumBottling None = new EnumBottling("None");
	}

	/**
	 * Enumeration strings for FoldLay
	 */

	public enum EFoldLay
	{
		Rotate0, Rotate90, Rotate180, Rotate270, Flip0, Flip90, Flip180, Flip270;

		public static EFoldLay getEnum(String val)
		{
			return JavaEnumUtil.getEnumIgnoreCase(EFoldLay.class, val, null);
		}
	}

	/**
	 * Enumeration strings for FoldLay
	 */

	@SuppressWarnings("rawtypes")
	public static class EnumFoldLay extends ValuedEnum
	{
		private static final long serialVersionUID = 1L;
		private static int m_startValue = 0;

		protected EnumFoldLay(String name)
		{
			super(name, m_startValue++);
		}

		/**
		 * @param enumName the string to convert
		 * @return the enum
		 */
		public static EnumFoldLay getEnum(String enumName)
		{
			return (EnumFoldLay) getEnum(EnumFoldLay.class, enumName);
		}

		/**
		 * @param enumValue the integer to convert
		 * @return the enum
		 */
		public static EnumFoldLay getEnum(int enumValue)
		{
			return (EnumFoldLay) getEnum(EnumFoldLay.class, enumValue);
		}

		/**
		 * @return the map of enums
		 */
		public static Map getEnumMap()
		{
			return getEnumMap(EnumFoldLay.class);
		}

		/**
		 * @return the list of enums
		 */
		public static List getEnumList()
		{
			return getEnumList(EnumFoldLay.class);
		}

		/**
		 * @return the iterator
		 */
		public static Iterator iterator()
		{
			return iterator(EnumFoldLay.class);
		}

		/**  */
		public static final EnumFoldLay Rotate0 = new EnumFoldLay("Rotate0");
		/**  */
		public static final EnumFoldLay Rotate90 = new EnumFoldLay("Rotate90");
		/**  */
		public static final EnumFoldLay Rotate180 = new EnumFoldLay("Rotate180");
		/**  */
		public static final EnumFoldLay Rotate270 = new EnumFoldLay("Rotate270");
		/**  */
		public static final EnumFoldLay Flip0 = new EnumFoldLay("Flip0");
		/**  */
		public static final EnumFoldLay Flip90 = new EnumFoldLay("Flip90");
		/**  */
		public static final EnumFoldLay Flip180 = new EnumFoldLay("Flip180");
		/**  */
		public static final EnumFoldLay Flip270 = new EnumFoldLay("Flip270");
	}

	/**
	 * Enumeration strings for SpreadType
	 */

	public enum ESpreadType
	{
		SinglePage, Spread;

		public static ESpreadType getEnum(String val)
		{
			return JavaEnumUtil.getEnumIgnoreCase(ESpreadType.class, val, null);
		}
	}

	/**
	 * Enumeration strings for SpreadType
	 */

	@SuppressWarnings("rawtypes")
	public static class EnumSpreadType extends ValuedEnum
	{
		private static final long serialVersionUID = 1L;
		private static int m_startValue = 0;

		protected EnumSpreadType(String name)
		{
			super(name, m_startValue++);
		}

		/**
		 * @param enumName the string to convert
		 * @return the enum
		 */
		public static EnumSpreadType getEnum(String enumName)
		{
			return (EnumSpreadType) getEnum(EnumSpreadType.class, enumName);
		}

		/**
		 * @param enumValue the integer to convert
		 * @return the enum
		 */
		public static EnumSpreadType getEnum(int enumValue)
		{
			return (EnumSpreadType) getEnum(EnumSpreadType.class, enumValue);
		}

		/**
		 * @return the map of enums
		 */
		public static Map getEnumMap()
		{
			return getEnumMap(EnumSpreadType.class);
		}

		/**
		 * @return the list of enums
		 */
		public static List getEnumList()
		{
			return getEnumList(EnumSpreadType.class);
		}

		/**
		 * @return the iterator
		 */
		public static Iterator iterator()
		{
			return iterator(EnumSpreadType.class);
		}

		/**  */
		public static final EnumSpreadType SinglePage = new EnumSpreadType("SinglePage");
		/**  */
		public static final EnumSpreadType Spread = new EnumSpreadType("Spread");
	}

	/*
	 * ************************************************************************ Attribute getter / setter ************************************************************************
	 */

	/*
	 * --------------------------------------------------------------------- Methods for Attribute BinderySignatureType
	 * ---------------------------------------------------------------------
	 */
	/**
	 * (5) set attribute BinderySignatureType
	 *
	 * @param enumVar the enumVar to set the attribute to
	 */
	public void setBinderySignatureType(EBinderySignatureType enumVar)
	{
		setAttribute(AttributeName.BINDERYSIGNATURETYPE, enumVar == null ? null : enumVar.name(), null);
	}

	/**
	 * (9) get attribute BinderySignatureType
	 *
	 * @return the value of the attribute
	 */
	public EBinderySignatureType getEBinderySignatureType()
	{
		return EBinderySignatureType.getEnum(getAttribute(AttributeName.BINDERYSIGNATURETYPE, null, "Fold"));
	}

	/*
	 * --------------------------------------------------------------------- Methods for Attribute BinderySignatureType
	 * ---------------------------------------------------------------------
	 */
	/**
	 * (5) set attribute BinderySignatureType
	 *
	 * @param enumVar the enumVar to set the attribute to
	 * @deprecated use java.lang.enum
	 */
	@Deprecated
	public void setBinderySignatureType(EnumBinderySignatureType enumVar)
	{
		setAttribute(AttributeName.BINDERYSIGNATURETYPE, enumVar == null ? null : enumVar.getName(), null);
	}

	/**
	 * (9) get attribute BinderySignatureType
	 *
	 * @return the value of the attribute
	 */
	public EnumBinderySignatureType getBinderySignatureType()
	{
		return EnumBinderySignatureType.getEnum(getAttribute(AttributeName.BINDERYSIGNATURETYPE, null, "Fold"));
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
		return EBindingEdge.getEnum(getAttribute(AttributeName.BINDINGEDGE, null, "Left"));
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
		return EnumBindingEdge.getEnum(getAttribute(AttributeName.BINDINGEDGE, null, "Left"));
	}

	/*
	 * --------------------------------------------------------------------- Methods for Attribute JogEdge ---------------------------------------------------------------------
	 */
	/**
	 * (5) set attribute JogEdge
	 *
	 * @param enumVar the enumVar to set the attribute to
	 */
	public void setJogEdge(EJogEdge enumVar)
	{
		setAttribute(AttributeName.JOGEDGE, enumVar == null ? null : enumVar.name(), null);
	}

	/**
	 * (9) get attribute JogEdge
	 *
	 * @return the value of the attribute
	 */
	public EJogEdge getEJogEdge()
	{
		return EJogEdge.getEnum(getAttribute(AttributeName.JOGEDGE, null, "Top"));
	}

	/*
	 * --------------------------------------------------------------------- Methods for Attribute JogEdge ---------------------------------------------------------------------
	 */
	/**
	 * (5) set attribute JogEdge
	 *
	 * @param enumVar the enumVar to set the attribute to
	 * @deprecated use java.lang.enum
	 */
	@Deprecated
	public void setJogEdge(EnumJogEdge enumVar)
	{
		setAttribute(AttributeName.JOGEDGE, enumVar == null ? null : enumVar.getName(), null);
	}

	/**
	 * (9) get attribute JogEdge
	 *
	 * @return the value of the attribute
	 */
	public EnumJogEdge getJogEdge()
	{
		return EnumJogEdge.getEnum(getAttribute(AttributeName.JOGEDGE, null, "Top"));
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
	 * --------------------------------------------------------------------- Methods for Attribute AlignmentReferenceWeb
	 * ---------------------------------------------------------------------
	 */
	/**
	 * (36) set attribute AlignmentReferenceWeb
	 *
	 * @param value the value to set the attribute to
	 */
	public void setAlignmentReferenceWeb(String value)
	{
		setAttribute(AttributeName.ALIGNMENTREFERENCEWEB, value, null);
	}

	/**
	 * (23) get String attribute AlignmentReferenceWeb
	 *
	 * @return the value of the attribute
	 */
	public String getAlignmentReferenceWeb()
	{
		return getAttribute(AttributeName.ALIGNMENTREFERENCEWEB, null, JDFCoreConstants.EMPTYSTRING);
	}

	/*
	 * --------------------------------------------------------------------- Methods for Attribute BindingOrientation
	 * ---------------------------------------------------------------------
	 */
	/**
	 * (5) set attribute BindingOrientation
	 *
	 * @param enumVar the enumVar to set the attribute to
	 */
	public void setBindingOrientation(EBindingOrientation enumVar)
	{
		setAttribute(AttributeName.BINDINGORIENTATION, enumVar == null ? null : enumVar.name(), null);
	}

	/**
	 * (9) get attribute BindingOrientation
	 *
	 * @return the value of the attribute
	 */
	public EBindingOrientation getEBindingOrientation()
	{
		return EBindingOrientation.getEnum(getAttribute(AttributeName.BINDINGORIENTATION, null, null));
	}

	/*
	 * --------------------------------------------------------------------- Methods for Attribute BindingOrientation
	 * ---------------------------------------------------------------------
	 */
	/**
	 * (5) set attribute BindingOrientation
	 *
	 * @param enumVar the enumVar to set the attribute to
	 * @deprecated use java.lang.enum
	 */
	@Deprecated
	public void setBindingOrientation(EnumBindingOrientation enumVar)
	{
		setAttribute(AttributeName.BINDINGORIENTATION, enumVar == null ? null : enumVar.getName(), null);
	}

	/**
	 * (9) get attribute BindingOrientation
	 *
	 * @return the value of the attribute
	 */
	public EnumBindingOrientation getBindingOrientation()
	{
		return EnumBindingOrientation.getEnum(getAttribute(AttributeName.BINDINGORIENTATION, null, null));
	}

	/*
	 * --------------------------------------------------------------------- Methods for Attribute BleedBottom ---------------------------------------------------------------------
	 */
	/**
	 * (36) set attribute BleedBottom
	 *
	 * @param value the value to set the attribute to
	 */
	public void setBleedBottom(double value)
	{
		setAttribute(AttributeName.BLEEDBOTTOM, value, null);
	}

	/**
	 * (17) get double attribute BleedBottom
	 *
	 * @return double the value of the attribute
	 */
	public double getBleedBottom()
	{
		return getRealAttribute(AttributeName.BLEEDBOTTOM, null, 0.0);
	}

	/*
	 * --------------------------------------------------------------------- Methods for Attribute BleedLeft ---------------------------------------------------------------------
	 */
	/**
	 * (36) set attribute BleedLeft
	 *
	 * @param value the value to set the attribute to
	 */
	public void setBleedLeft(double value)
	{
		setAttribute(AttributeName.BLEEDLEFT, value, null);
	}

	/**
	 * (17) get double attribute BleedLeft
	 *
	 * @return double the value of the attribute
	 */
	public double getBleedLeft()
	{
		return getRealAttribute(AttributeName.BLEEDLEFT, null, 0.0);
	}

	/*
	 * --------------------------------------------------------------------- Methods for Attribute BleedRight ---------------------------------------------------------------------
	 */
	/**
	 * (36) set attribute BleedRight
	 *
	 * @param value the value to set the attribute to
	 */
	public void setBleedRight(double value)
	{
		setAttribute(AttributeName.BLEEDRIGHT, value, null);
	}

	/**
	 * (17) get double attribute BleedRight
	 *
	 * @return double the value of the attribute
	 */
	public double getBleedRight()
	{
		return getRealAttribute(AttributeName.BLEEDRIGHT, null, 0.0);
	}

	/*
	 * --------------------------------------------------------------------- Methods for Attribute BleedTop ---------------------------------------------------------------------
	 */
	/**
	 * (36) set attribute BleedTop
	 *
	 * @param value the value to set the attribute to
	 */
	public void setBleedTop(double value)
	{
		setAttribute(AttributeName.BLEEDTOP, value, null);
	}

	/**
	 * (17) get double attribute BleedTop
	 *
	 * @return double the value of the attribute
	 */
	public double getBleedTop()
	{
		return getRealAttribute(AttributeName.BLEEDTOP, null, 0.0);
	}

	/*
	 * --------------------------------------------------------------------- Methods for Attribute Bottling ---------------------------------------------------------------------
	 */
	/**
	 * (5) set attribute Bottling
	 *
	 * @param enumVar the enumVar to set the attribute to
	 */
	public void setBottling(EBottling enumVar)
	{
		setAttribute(AttributeName.BOTTLING, enumVar == null ? null : enumVar.name(), null);
	}

	/**
	 * (9) get attribute Bottling
	 *
	 * @return the value of the attribute
	 */
	public EBottling getEBottling()
	{
		return EBottling.getEnum(getAttribute(AttributeName.BOTTLING, null, null));
	}

	/*
	 * --------------------------------------------------------------------- Methods for Attribute Bottling ---------------------------------------------------------------------
	 */
	/**
	 * (5) set attribute Bottling
	 *
	 * @param enumVar the enumVar to set the attribute to
	 * @deprecated use java.lang.enum
	 */
	@Deprecated
	public void setBottling(EnumBottling enumVar)
	{
		setAttribute(AttributeName.BOTTLING, enumVar == null ? null : enumVar.getName(), null);
	}

	/**
	 * (9) get attribute Bottling
	 *
	 * @return the value of the attribute
	 */
	public EnumBottling getBottling()
	{
		return EnumBottling.getEnum(getAttribute(AttributeName.BOTTLING, null, null));
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
	 * --------------------------------------------------------------------- Methods for Attribute FoldLay ---------------------------------------------------------------------
	 */
	/**
	 * (5) set attribute FoldLay
	 *
	 * @param enumVar the enumVar to set the attribute to
	 */
	public void setFoldLay(EFoldLay enumVar)
	{
		setAttribute(AttributeName.FOLDLAY, enumVar == null ? null : enumVar.name(), null);
	}

	/**
	 * (9) get attribute FoldLay
	 *
	 * @return the value of the attribute
	 */
	public EFoldLay getEFoldLay()
	{
		return EFoldLay.getEnum(getAttribute(AttributeName.FOLDLAY, null, null));
	}

	/*
	 * --------------------------------------------------------------------- Methods for Attribute FoldLay ---------------------------------------------------------------------
	 */
	/**
	 * (5) set attribute FoldLay
	 *
	 * @param enumVar the enumVar to set the attribute to
	 * @deprecated use java.lang.enum
	 */
	@Deprecated
	public void setFoldLay(EnumFoldLay enumVar)
	{
		setAttribute(AttributeName.FOLDLAY, enumVar == null ? null : enumVar.getName(), null);
	}

	/**
	 * (9) get attribute FoldLay
	 *
	 * @return the value of the attribute
	 */
	public EnumFoldLay getFoldLay()
	{
		return EnumFoldLay.getEnum(getAttribute(AttributeName.FOLDLAY, null, null));
	}

	/*
	 * --------------------------------------------------------------------- Methods for Attribute OutsideGutter
	 * ---------------------------------------------------------------------
	 */
	/**
	 * (36) set attribute OutsideGutter
	 *
	 * @param value the value to set the attribute to
	 */
	public void setOutsideGutter(boolean value)
	{
		setAttribute(AttributeName.OUTSIDEGUTTER, value, null);
	}

	/**
	 * (18) get boolean attribute OutsideGutter
	 *
	 * @return boolean the value of the attribute
	 */
	public boolean getOutsideGutter()
	{
		return getBoolAttribute(AttributeName.OUTSIDEGUTTER, null, false);
	}

	/*
	 * --------------------------------------------------------------------- Methods for Attribute SpreadType ---------------------------------------------------------------------
	 */
	/**
	 * (5) set attribute SpreadType
	 *
	 * @param enumVar the enumVar to set the attribute to
	 */
	public void setSpreadType(ESpreadType enumVar)
	{
		setAttribute(AttributeName.SPREADTYPE, enumVar == null ? null : enumVar.name(), null);
	}

	/**
	 * (9) get attribute SpreadType
	 *
	 * @return the value of the attribute
	 */
	public ESpreadType getESpreadType()
	{
		return ESpreadType.getEnum(getAttribute(AttributeName.SPREADTYPE, null, null));
	}

	/*
	 * --------------------------------------------------------------------- Methods for Attribute SpreadType ---------------------------------------------------------------------
	 */
	/**
	 * (5) set attribute SpreadType
	 *
	 * @param enumVar the enumVar to set the attribute to
	 * @deprecated use java.lang.enum
	 */
	@Deprecated
	public void setSpreadType(EnumSpreadType enumVar)
	{
		setAttribute(AttributeName.SPREADTYPE, enumVar == null ? null : enumVar.getName(), null);
	}

	/**
	 * (9) get attribute SpreadType
	 *
	 * @return the value of the attribute
	 */
	public EnumSpreadType getSpreadType()
	{
		return EnumSpreadType.getEnum(getAttribute(AttributeName.SPREADTYPE, null, null));
	}

	/*
	 * --------------------------------------------------------------------- Methods for Attribute StaggerColumns
	 * ---------------------------------------------------------------------
	 */
	/**
	 * (36) set attribute StaggerColumns
	 *
	 * @param value the value to set the attribute to
	 */
	public void setStaggerColumns(JDFNumberList value)
	{
		setAttribute(AttributeName.STAGGERCOLUMNS, value, null);
	}

	/**
	 * (20) get JDFNumberList attribute StaggerColumns
	 *
	 * @return JDFNumberList the value of the attribute, null if a the attribute value is not a valid to create a JDFNumberList
	 */
	public JDFNumberList getStaggerColumns()
	{
		final String strAttrName = getAttribute(AttributeName.STAGGERCOLUMNS, null, null);
		final JDFNumberList nPlaceHolder = JDFNumberList.createNumberList(strAttrName);
		return nPlaceHolder;
	}

	/*
	 * --------------------------------------------------------------------- Methods for Attribute StaggerContinuous
	 * ---------------------------------------------------------------------
	 */
	/**
	 * (36) set attribute StaggerContinuous
	 *
	 * @param value the value to set the attribute to
	 */
	public void setStaggerContinuous(boolean value)
	{
		setAttribute(AttributeName.STAGGERCONTINUOUS, value, null);
	}

	/**
	 * (18) get boolean attribute StaggerContinuous
	 *
	 * @return boolean the value of the attribute
	 */
	public boolean getStaggerContinuous()
	{
		return getBoolAttribute(AttributeName.STAGGERCONTINUOUS, null, false);
	}

	/*
	 * --------------------------------------------------------------------- Methods for Attribute StaggerRows ---------------------------------------------------------------------
	 */
	/**
	 * (36) set attribute StaggerRows
	 *
	 * @param value the value to set the attribute to
	 */
	public void setStaggerRows(JDFNumberList value)
	{
		setAttribute(AttributeName.STAGGERROWS, value, null);
	}

	/**
	 * (20) get JDFNumberList attribute StaggerRows
	 *
	 * @return JDFNumberList the value of the attribute, null if a the attribute value is not a valid to create a JDFNumberList
	 */
	public JDFNumberList getStaggerRows()
	{
		final String strAttrName = getAttribute(AttributeName.STAGGERROWS, null, null);
		final JDFNumberList nPlaceHolder = JDFNumberList.createNumberList(strAttrName);
		return nPlaceHolder;
	}

	/*
	 * --------------------------------------------------------------------- Methods for Attribute TrimBottom ---------------------------------------------------------------------
	 */
	/**
	 * (36) set attribute TrimBottom
	 *
	 * @param value the value to set the attribute to
	 */
	public void setTrimBottom(double value)
	{
		setAttribute(AttributeName.TRIMBOTTOM, value, null);
	}

	/**
	 * (17) get double attribute TrimBottom
	 *
	 * @return double the value of the attribute
	 */
	public double getTrimBottom()
	{
		return getRealAttribute(AttributeName.TRIMBOTTOM, null, 0.0);
	}

	/*
	 * --------------------------------------------------------------------- Methods for Attribute TrimLeft ---------------------------------------------------------------------
	 */
	/**
	 * (36) set attribute TrimLeft
	 *
	 * @param value the value to set the attribute to
	 */
	public void setTrimLeft(double value)
	{
		setAttribute(AttributeName.TRIMLEFT, value, null);
	}

	/**
	 * (17) get double attribute TrimLeft
	 *
	 * @return double the value of the attribute
	 */
	public double getTrimLeft()
	{
		return getRealAttribute(AttributeName.TRIMLEFT, null, 0.0);
	}

	/*
	 * --------------------------------------------------------------------- Methods for Attribute TrimRight ---------------------------------------------------------------------
	 */
	/**
	 * (36) set attribute TrimRight
	 *
	 * @param value the value to set the attribute to
	 */
	public void setTrimRight(double value)
	{
		setAttribute(AttributeName.TRIMRIGHT, value, null);
	}

	/**
	 * (17) get double attribute TrimRight
	 *
	 * @return double the value of the attribute
	 */
	public double getTrimRight()
	{
		return getRealAttribute(AttributeName.TRIMRIGHT, null, 0.0);
	}

	/*
	 * --------------------------------------------------------------------- Methods for Attribute TrimTop ---------------------------------------------------------------------
	 */
	/**
	 * (36) set attribute TrimTop
	 *
	 * @param value the value to set the attribute to
	 */
	public void setTrimTop(double value)
	{
		setAttribute(AttributeName.TRIMTOP, value, null);
	}

	/**
	 * (17) get double attribute TrimTop
	 *
	 * @return double the value of the attribute
	 */
	public double getTrimTop()
	{
		return getRealAttribute(AttributeName.TRIMTOP, null, 0.0);
	}

	/*
	 * --------------------------------------------------------------------- Methods for Attribute WebCellAlignment
	 * ---------------------------------------------------------------------
	 */
	/**
	 * (36) set attribute WebCellAlignment
	 *
	 * @param value the value to set the attribute to
	 */
	public void setWebCellAlignment(JDFXYPair value)
	{
		setAttribute(AttributeName.WEBCELLALIGNMENT, value, null);
	}

	/**
	 * (20) get JDFXYPair attribute WebCellAlignment
	 *
	 * @return JDFXYPair the value of the attribute, null if a the attribute value is not a valid to create a JDFXYPair
	 */
	public JDFXYPair getWebCellAlignment()
	{
		final String strAttrName = getAttribute(AttributeName.WEBCELLALIGNMENT, null, null);
		final JDFXYPair nPlaceHolder = JDFXYPair.createXYPair(strAttrName);
		return nPlaceHolder;
	}

	/*
	 * *********************************************************************** Element getter / setter ***********************************************************************
	 */

	/**
	 * (24) const get element DieLayout
	 *
	 * @return JDFDieLayout the element
	 */
	public JDFDieLayout getDieLayout()
	{
		return (JDFDieLayout) getElement(ElementName.DIELAYOUT, null, 0);
	}

	/**
	 * (25) getCreateDieLayout
	 * 
	 * @return JDFDieLayout the element
	 */
	public JDFDieLayout getCreateDieLayout()
	{
		return (JDFDieLayout) getCreateElement_JDFElement(ElementName.DIELAYOUT, null, 0);
	}

	/**
	 * (29) append element DieLayout
	 *
	 * @return JDFDieLayout the element @ if the element already exists
	 */
	public JDFDieLayout appendDieLayout()
	{
		return (JDFDieLayout) appendElementN(ElementName.DIELAYOUT, 1, null);
	}

	/**
	 * (31) create inter-resource link to refTarget
	 *
	 * @param refTarget the element that is referenced
	 */
	public void refDieLayout(JDFDieLayout refTarget)
	{
		refElement(refTarget);
	}

	/**
	 * (24) const get element Fold
	 *
	 * @return JDFFold the element
	 */
	public JDFFold getFold()
	{
		return (JDFFold) getElement(ElementName.FOLD, null, 0);
	}

	/**
	 * (25) getCreateFold
	 * 
	 * @return JDFFold the element
	 */
	public JDFFold getCreateFold()
	{
		return (JDFFold) getCreateElement_JDFElement(ElementName.FOLD, null, 0);
	}

	/**
	 * (26) getCreateFold
	 * 
	 * @param iSkip number of elements to skip
	 * @return JDFFold the element
	 */
	public JDFFold getCreateFold(int iSkip)
	{
		return (JDFFold) getCreateElement_JDFElement(ElementName.FOLD, null, iSkip);
	}

	/**
	 * (27) const get element Fold
	 *
	 * @param iSkip number of elements to skip
	 * @return JDFFold the element default is getFold(0)
	 */
	public JDFFold getFold(int iSkip)
	{
		return (JDFFold) getElement(ElementName.FOLD, null, iSkip);
	}

	/**
	 * Get all Fold from the current element
	 * 
	 * @return Collection<JDFFold>, null if none are available
	 */
	public Collection<JDFFold> getAllFold()
	{
		return getChildArrayByClass(JDFFold.class, false, 0);
	}

	/**
	 * (30) append element Fold
	 *
	 * @return JDFFold the element
	 */
	public JDFFold appendFold()
	{
		return (JDFFold) appendElement(ElementName.FOLD, null);
	}

	/**
	 * (24) const get element SignatureCell
	 *
	 * @return JDFSignatureCell the element
	 */
	public JDFSignatureCell getSignatureCell()
	{
		return (JDFSignatureCell) getElement(ElementName.SIGNATURECELL, null, 0);
	}

	/**
	 * (25) getCreateSignatureCell
	 * 
	 * @return JDFSignatureCell the element
	 */
	public JDFSignatureCell getCreateSignatureCell()
	{
		return (JDFSignatureCell) getCreateElement_JDFElement(ElementName.SIGNATURECELL, null, 0);
	}

	/**
	 * (26) getCreateSignatureCell
	 * 
	 * @param iSkip number of elements to skip
	 * @return JDFSignatureCell the element
	 */
	public JDFSignatureCell getCreateSignatureCell(int iSkip)
	{
		return (JDFSignatureCell) getCreateElement_JDFElement(ElementName.SIGNATURECELL, null, iSkip);
	}

	/**
	 * (27) const get element SignatureCell
	 *
	 * @param iSkip number of elements to skip
	 * @return JDFSignatureCell the element default is getSignatureCell(0)
	 */
	public JDFSignatureCell getSignatureCell(int iSkip)
	{
		return (JDFSignatureCell) getElement(ElementName.SIGNATURECELL, null, iSkip);
	}

	/**
	 * Get all SignatureCell from the current element
	 * 
	 * @return Collection<JDFSignatureCell>, null if none are available
	 */
	public Collection<JDFSignatureCell> getAllSignatureCell()
	{
		return getChildArrayByClass(JDFSignatureCell.class, false, 0);
	}

	/**
	 * (30) append element SignatureCell
	 *
	 * @return JDFSignatureCell the element
	 */
	public JDFSignatureCell appendSignatureCell()
	{
		return (JDFSignatureCell) appendElement(ElementName.SIGNATURECELL, null);
	}

}
