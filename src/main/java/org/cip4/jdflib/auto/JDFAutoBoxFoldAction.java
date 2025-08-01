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
import org.cip4.jdflib.core.JDFElement;
import org.cip4.jdflib.datatypes.JDFXYPair;
import org.cip4.jdflib.resource.process.postpress.JDFGlueLine;
import org.cip4.jdflib.util.JavaEnumUtil;

/**
 *****************************************************************************
 * class JDFAutoBoxFoldAction : public JDFElement
 *****************************************************************************
 * 
 */

public abstract class JDFAutoBoxFoldAction extends JDFElement
{

	private static final long serialVersionUID = 1L;

	private static AtrInfoTable[] atrInfoTable = new AtrInfoTable[2];
	static
	{
		atrInfoTable[0] = new AtrInfoTable(AttributeName.FOLDINDEX, 0x2222222111l, AttributeInfo.EnumAttributeType.XYPair, null, null);
		atrInfoTable[1] = new AtrInfoTable(AttributeName.ACTION, 0x3333333111l, AttributeInfo.EnumAttributeType.enumeration, EnumAction.getEnum(0), null);
	}

	@Override
	protected AttributeInfo getTheAttributeInfo()
	{
		return super.getTheAttributeInfo().updateReplace(atrInfoTable);
	}

	private static ElemInfoTable[] elemInfoTable = new ElemInfoTable[1];
	static
	{
		elemInfoTable[0] = new ElemInfoTable(ElementName.GLUELINE, 0x3333333111l);
	}

	@Override
	protected ElementInfo getTheElementInfo()
	{
		return super.getTheElementInfo().updateReplace(elemInfoTable);
	}

	/**
	 * Constructor for JDFAutoBoxFoldAction
	 *
	 * @param myOwnerDocument
	 * @param qualifiedName
	 */
	protected JDFAutoBoxFoldAction(CoreDocumentImpl myOwnerDocument, String qualifiedName)
	{
		super(myOwnerDocument, qualifiedName);
	}

	/**
	 * Constructor for JDFAutoBoxFoldAction
	 *
	 * @param myOwnerDocument
	 * @param myNamespaceURI
	 * @param qualifiedName
	 */
	protected JDFAutoBoxFoldAction(CoreDocumentImpl myOwnerDocument, String myNamespaceURI, String qualifiedName)
	{
		super(myOwnerDocument, myNamespaceURI, qualifiedName);
	}

	/**
	 * Constructor for JDFAutoBoxFoldAction
	 *
	 * @param myOwnerDocument
	 * @param myNamespaceURI
	 * @param qualifiedName
	 * @param myLocalName
	 */
	protected JDFAutoBoxFoldAction(CoreDocumentImpl myOwnerDocument, String myNamespaceURI, String qualifiedName, String myLocalName)
	{
		super(myOwnerDocument, myNamespaceURI, qualifiedName, myLocalName);
	}

	/**
	 * Enumeration strings for Action
	 */

	public enum EAction
	{
		LongFoldLeftToRight, LongFoldRightToLeft, LongPreFoldLeftToRight, LongPreFoldRightToLeft, FrontFoldComplete, FrontFoldDiagonal, FrontFoldCompleteDiagonal, BackFoldComplete, BackFoldDiagonal, BackFoldCompleteDiagonal, ReverseFold, Milling, Rotate90, Rotate180, Rotate270;

		public static EAction getEnum(String val)
		{
			return JavaEnumUtil.getEnumIgnoreCase(EAction.class, val, null);
		}
	}

	/**
	 * Enumeration strings for Action
	 */

	@SuppressWarnings("rawtypes")
	public static class EnumAction extends ValuedEnum
	{
		private static final long serialVersionUID = 1L;
		private static int m_startValue = 0;

		protected EnumAction(String name)
		{
			super(name, m_startValue++);
		}

		/**
		 * @param enumName the string to convert
		 * @return the enum
		 */
		public static EnumAction getEnum(String enumName)
		{
			return (EnumAction) getEnum(EnumAction.class, enumName);
		}

		/**
		 * @param enumValue the integer to convert
		 * @return the enum
		 */
		public static EnumAction getEnum(int enumValue)
		{
			return (EnumAction) getEnum(EnumAction.class, enumValue);
		}

		/**
		 * @return the map of enums
		 */
		public static Map getEnumMap()
		{
			return getEnumMap(EnumAction.class);
		}

		/**
		 * @return the list of enums
		 */
		public static List getEnumList()
		{
			return getEnumList(EnumAction.class);
		}

		/**
		 * @return the iterator
		 */
		public static Iterator iterator()
		{
			return iterator(EnumAction.class);
		}

		/**  */
		public static final EnumAction LongFoldLeftToRight = new EnumAction("LongFoldLeftToRight");
		/**  */
		public static final EnumAction LongFoldRightToLeft = new EnumAction("LongFoldRightToLeft");
		/**  */
		public static final EnumAction LongPreFoldLeftToRight = new EnumAction("LongPreFoldLeftToRight");
		/**  */
		public static final EnumAction LongPreFoldRightToLeft = new EnumAction("LongPreFoldRightToLeft");
		/**  */
		public static final EnumAction FrontFoldComplete = new EnumAction("FrontFoldComplete");
		/**  */
		public static final EnumAction FrontFoldDiagonal = new EnumAction("FrontFoldDiagonal");
		/**  */
		public static final EnumAction FrontFoldCompleteDiagonal = new EnumAction("FrontFoldCompleteDiagonal");
		/**  */
		public static final EnumAction BackFoldComplete = new EnumAction("BackFoldComplete");
		/**  */
		public static final EnumAction BackFoldDiagonal = new EnumAction("BackFoldDiagonal");
		/**  */
		public static final EnumAction BackFoldCompleteDiagonal = new EnumAction("BackFoldCompleteDiagonal");
		/**  */
		public static final EnumAction ReverseFold = new EnumAction("ReverseFold");
		/**  */
		public static final EnumAction Milling = new EnumAction("Milling");
		/**  */
		public static final EnumAction Rotate90 = new EnumAction("Rotate90");
		/**  */
		public static final EnumAction Rotate180 = new EnumAction("Rotate180");
		/**  */
		public static final EnumAction Rotate270 = new EnumAction("Rotate270");
	}

	/*
	 * ************************************************************************ Attribute getter / setter ************************************************************************
	 */

	/*
	 * --------------------------------------------------------------------- Methods for Attribute FoldIndex ---------------------------------------------------------------------
	 */
	/**
	 * (36) set attribute FoldIndex
	 *
	 * @param value the value to set the attribute to
	 */
	public void setFoldIndex(JDFXYPair value)
	{
		setAttribute(AttributeName.FOLDINDEX, value, null);
	}

	/**
	 * (20) get JDFXYPair attribute FoldIndex
	 *
	 * @return JDFXYPair the value of the attribute, null if a the attribute value is not a valid to create a JDFXYPair
	 */
	public JDFXYPair getFoldIndex()
	{
		final String strAttrName = getAttribute(AttributeName.FOLDINDEX, null, null);
		final JDFXYPair nPlaceHolder = JDFXYPair.createXYPair(strAttrName);
		return nPlaceHolder;
	}

	/*
	 * --------------------------------------------------------------------- Methods for Attribute Action ---------------------------------------------------------------------
	 */
	/**
	 * (5) set attribute Action
	 *
	 * @param enumVar the enumVar to set the attribute to
	 */
	public void setAction(EAction enumVar)
	{
		setAttribute(AttributeName.ACTION, enumVar == null ? null : enumVar.name(), null);
	}

	/**
	 * (9) get attribute Action
	 *
	 * @return the value of the attribute
	 */
	public EAction getEAction()
	{
		return EAction.getEnum(getAttribute(AttributeName.ACTION, null, null));
	}

	/*
	 * --------------------------------------------------------------------- Methods for Attribute Action ---------------------------------------------------------------------
	 */
	/**
	 * (5) set attribute Action
	 *
	 * @param enumVar the enumVar to set the attribute to
	 * @deprecated use java.lang.enum
	 */
	@Deprecated
	public void setAction(EnumAction enumVar)
	{
		setAttribute(AttributeName.ACTION, enumVar == null ? null : enumVar.getName(), null);
	}

	/**
	 * (9) get attribute Action
	 *
	 * @return the value of the attribute
	 */
	public EnumAction getAction()
	{
		return EnumAction.getEnum(getAttribute(AttributeName.ACTION, null, null));
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
