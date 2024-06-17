/*
 * The CIP4 Software License, Version 1.0
 *
 *
 * Copyright (c) 2001-2024 The International Cooperation for the Integration of
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
import org.cip4.jdflib.resource.process.postpress.JDFStitchingParams;

/**
 *****************************************************************************
 * class JDFAutoIDPStitching : public JDFElement
 *****************************************************************************
 * 
 */

public abstract class JDFAutoIDPStitching extends JDFElement
{

	private static final long serialVersionUID = 1L;

	private static AtrInfoTable[] atrInfoTable = new AtrInfoTable[2];
	static
	{
		atrInfoTable[0] = new AtrInfoTable(AttributeName.STITCHINGPOSITION, 0x3333333333l, AttributeInfo.EnumAttributeType.enumeration, EnumStitchingPosition.getEnum(0), null);
		atrInfoTable[1] = new AtrInfoTable(AttributeName.STITCHINGREFERENCEEDGE, 0x3333333333l, AttributeInfo.EnumAttributeType.enumeration, EnumStitchingReferenceEdge.getEnum(0),
				null);
	}

	@Override
	protected AttributeInfo getTheAttributeInfo()
	{
		return super.getTheAttributeInfo().updateReplace(atrInfoTable);
	}

	private static ElemInfoTable[] elemInfoTable = new ElemInfoTable[1];
	static
	{
		elemInfoTable[0] = new ElemInfoTable(ElementName.STITCHINGPARAMS, 0x3333333333l);
	}

	@Override
	protected ElementInfo getTheElementInfo()
	{
		return super.getTheElementInfo().updateReplace(elemInfoTable);
	}

	/**
	 * Constructor for JDFAutoIDPStitching
	 *
	 * @param myOwnerDocument
	 * @param qualifiedName
	 */
	protected JDFAutoIDPStitching(CoreDocumentImpl myOwnerDocument, String qualifiedName)
	{
		super(myOwnerDocument, qualifiedName);
	}

	/**
	 * Constructor for JDFAutoIDPStitching
	 *
	 * @param myOwnerDocument
	 * @param myNamespaceURI
	 * @param qualifiedName
	 */
	protected JDFAutoIDPStitching(CoreDocumentImpl myOwnerDocument, String myNamespaceURI, String qualifiedName)
	{
		super(myOwnerDocument, myNamespaceURI, qualifiedName);
	}

	/**
	 * Constructor for JDFAutoIDPStitching
	 *
	 * @param myOwnerDocument
	 * @param myNamespaceURI
	 * @param qualifiedName
	 * @param myLocalName
	 */
	protected JDFAutoIDPStitching(CoreDocumentImpl myOwnerDocument, String myNamespaceURI, String qualifiedName, String myLocalName)
	{
		super(myOwnerDocument, myNamespaceURI, qualifiedName, myLocalName);
	}

	/**
	 * Enumeration strings for StitchingPosition
	 */

	@SuppressWarnings("rawtypes")
	public static class EnumStitchingPosition extends ValuedEnum
	{
		private static final long serialVersionUID = 1L;
		private static int m_startValue = 0;

		protected EnumStitchingPosition(String name)
		{
			super(name, m_startValue++);
		}

		/**
		 * @param enumName the string to convert
		 * @return the enum
		 */
		public static EnumStitchingPosition getEnum(String enumName)
		{
			return (EnumStitchingPosition) getEnum(EnumStitchingPosition.class, enumName);
		}

		/**
		 * @param enumValue the integer to convert
		 * @return the enum
		 */
		public static EnumStitchingPosition getEnum(int enumValue)
		{
			return (EnumStitchingPosition) getEnum(EnumStitchingPosition.class, enumValue);
		}

		/**
		 * @return the map of enums
		 */
		public static Map getEnumMap()
		{
			return getEnumMap(EnumStitchingPosition.class);
		}

		/**
		 * @return the list of enums
		 */
		public static List getEnumList()
		{
			return getEnumList(EnumStitchingPosition.class);
		}

		/**
		 * @return the iterator
		 */
		public static Iterator iterator()
		{
			return iterator(EnumStitchingPosition.class);
		}

		/**  */
		public static final EnumStitchingPosition None = new EnumStitchingPosition("None");
		/**  */
		public static final EnumStitchingPosition TopLeft = new EnumStitchingPosition("TopLeft");
		/**  */
		public static final EnumStitchingPosition BottomLeft = new EnumStitchingPosition("BottomLeft");
		/**  */
		public static final EnumStitchingPosition TopRight = new EnumStitchingPosition("TopRight");
		/**  */
		public static final EnumStitchingPosition BottomRight = new EnumStitchingPosition("BottomRight");
		/**  */
		public static final EnumStitchingPosition LeftEdge = new EnumStitchingPosition("LeftEdge");
		/**  */
		public static final EnumStitchingPosition TopEdge = new EnumStitchingPosition("TopEdge");
		/**  */
		public static final EnumStitchingPosition RightEdge = new EnumStitchingPosition("RightEdge");
		/**  */
		public static final EnumStitchingPosition BottomEdge = new EnumStitchingPosition("BottomEdge");
		/**  */
		public static final EnumStitchingPosition DualLeftEdge = new EnumStitchingPosition("DualLeftEdge");
		/**  */
		public static final EnumStitchingPosition DualTopEdge = new EnumStitchingPosition("DualTopEdge");
		/**  */
		public static final EnumStitchingPosition DualRightEdge = new EnumStitchingPosition("DualRightEdge");
		/**  */
		public static final EnumStitchingPosition DualBottomEdge = new EnumStitchingPosition("DualBottomEdge");
	}

	/**
	 * Enumeration strings for StitchingReferenceEdge
	 */

	@SuppressWarnings("rawtypes")
	public static class EnumStitchingReferenceEdge extends ValuedEnum
	{
		private static final long serialVersionUID = 1L;
		private static int m_startValue = 0;

		protected EnumStitchingReferenceEdge(String name)
		{
			super(name, m_startValue++);
		}

		/**
		 * @param enumName the string to convert
		 * @return the enum
		 */
		public static EnumStitchingReferenceEdge getEnum(String enumName)
		{
			return (EnumStitchingReferenceEdge) getEnum(EnumStitchingReferenceEdge.class, enumName);
		}

		/**
		 * @param enumValue the integer to convert
		 * @return the enum
		 */
		public static EnumStitchingReferenceEdge getEnum(int enumValue)
		{
			return (EnumStitchingReferenceEdge) getEnum(EnumStitchingReferenceEdge.class, enumValue);
		}

		/**
		 * @return the map of enums
		 */
		public static Map getEnumMap()
		{
			return getEnumMap(EnumStitchingReferenceEdge.class);
		}

		/**
		 * @return the list of enums
		 */
		public static List getEnumList()
		{
			return getEnumList(EnumStitchingReferenceEdge.class);
		}

		/**
		 * @return the iterator
		 */
		public static Iterator iterator()
		{
			return iterator(EnumStitchingReferenceEdge.class);
		}

		/**  */
		public static final EnumStitchingReferenceEdge Bottom = new EnumStitchingReferenceEdge("Bottom");
		/**  */
		public static final EnumStitchingReferenceEdge Top = new EnumStitchingReferenceEdge("Top");
		/**  */
		public static final EnumStitchingReferenceEdge Left = new EnumStitchingReferenceEdge("Left");
		/**  */
		public static final EnumStitchingReferenceEdge Right = new EnumStitchingReferenceEdge("Right");
	}

	/*
	 * ************************************************************************ Attribute getter / setter ************************************************************************
	 */

	/*
	 * --------------------------------------------------------------------- Methods for Attribute StitchingPosition
	 * ---------------------------------------------------------------------
	 */
	/**
	 * (5) set attribute StitchingPosition
	 *
	 * @param enumVar the enumVar to set the attribute to
	 */
	public void setStitchingPosition(EnumStitchingPosition enumVar)
	{
		setAttribute(AttributeName.STITCHINGPOSITION, enumVar == null ? null : enumVar.getName(), null);
	}

	/**
	 * (9) get attribute StitchingPosition
	 *
	 * @return the value of the attribute
	 */
	public EnumStitchingPosition getStitchingPosition()
	{
		return EnumStitchingPosition.getEnum(getAttribute(AttributeName.STITCHINGPOSITION, null, null));
	}

	/*
	 * --------------------------------------------------------------------- Methods for Attribute StitchingReferenceEdge
	 * ---------------------------------------------------------------------
	 */
	/**
	 * (5) set attribute StitchingReferenceEdge
	 *
	 * @param enumVar the enumVar to set the attribute to
	 */
	public void setStitchingReferenceEdge(EnumStitchingReferenceEdge enumVar)
	{
		setAttribute(AttributeName.STITCHINGREFERENCEEDGE, enumVar == null ? null : enumVar.getName(), null);
	}

	/**
	 * (9) get attribute StitchingReferenceEdge
	 *
	 * @return the value of the attribute
	 */
	public EnumStitchingReferenceEdge getStitchingReferenceEdge()
	{
		return EnumStitchingReferenceEdge.getEnum(getAttribute(AttributeName.STITCHINGREFERENCEEDGE, null, null));
	}

	/*
	 * *********************************************************************** Element getter / setter ***********************************************************************
	 */

	/**
	 * (26) getCreateStitchingParams
	 * 
	 * @param iSkip number of elements to skip
	 * @return JDFStitchingParams the element
	 */
	public JDFStitchingParams getCreateStitchingParams(int iSkip)
	{
		return (JDFStitchingParams) getCreateElement_JDFElement(ElementName.STITCHINGPARAMS, null, iSkip);
	}

	/**
	 * (27) const get element StitchingParams
	 *
	 * @param iSkip number of elements to skip
	 * @return JDFStitchingParams the element default is getStitchingParams(0)
	 */
	public JDFStitchingParams getStitchingParams(int iSkip)
	{
		return (JDFStitchingParams) getElement(ElementName.STITCHINGPARAMS, null, iSkip);
	}

	/**
	 * Get all StitchingParams from the current element
	 * 
	 * @return Collection<JDFStitchingParams>, null if none are available
	 */
	public Collection<JDFStitchingParams> getAllStitchingParams()
	{
		return getChildArrayByClass(JDFStitchingParams.class, false, 0);
	}

	/**
	 * (30) append element StitchingParams
	 *
	 * @return JDFStitchingParams the element
	 */
	public JDFStitchingParams appendStitchingParams()
	{
		return (JDFStitchingParams) appendElement(ElementName.STITCHINGPARAMS, null);
	}

	/**
	 * (31) create inter-resource link to refTarget
	 *
	 * @param refTarget the element that is referenced
	 */
	public void refStitchingParams(JDFStitchingParams refTarget)
	{
		refElement(refTarget);
	}

}
