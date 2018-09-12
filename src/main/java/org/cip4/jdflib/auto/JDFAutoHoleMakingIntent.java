/*
 * The CIP4 Software License, Version 1.0
 *
 *
 * Copyright (c) 2001-2018 The International Cooperation for the Integration of Processes in Prepress, Press and Postpress (CIP4). All rights reserved.
 *
 * Redistribution and use in source and binary forms, with or without modification, are permitted provided that the following conditions are met:
 *
 * 1. Redistributions of source code must retain the above copyright notice, this list of conditions and the following disclaimer.
 *
 * 2. Redistributions in binary form must reproduce the above copyright notice, this list of conditions and the following disclaimer in the documentation and/or other materials provided with the
 * distribution.
 *
 * 3. The end-user documentation included with the redistribution, if any, must include the following acknowledgment: "This product includes software developed by the The International Cooperation for
 * the Integration of Processes in Prepress, Press and Postpress (www.cip4.org)" Alternately, this acknowledgment may appear in the software itself, if and wherever such third-party acknowledgments
 * normally appear.
 *
 * 4. The names "CIP4" and "The International Cooperation for the Integration of Processes in Prepress, Press and Postpress" must not be used to endorse or promote products derived from this software
 * without prior written permission. For written permission, please contact info@cip4.org.
 *
 * 5. Products derived from this software may not be called "CIP4", nor may "CIP4" appear in their name, without prior written permission of the CIP4 organization
 *
 * Usage of this software in commercial products is subject to restrictions. For details please consult info@cip4.org.
 *
 * THIS SOFTWARE IS PROVIDED ``AS IS'' AND ANY EXPRESSED OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO, THE IMPLIED WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE ARE
 * DISCLAIMED. IN NO EVENT SHALL THE INTERNATIONAL COOPERATION FOR THE INTEGRATION OF PROCESSES IN PREPRESS, PRESS AND POSTPRESS OR ITS CONTRIBUTORS BE LIABLE FOR ANY DIRECT, INDIRECT, INCIDENTAL,
 * SPECIAL, EXEMPLARY, OR CONSEQUENTIAL DAMAGES (INCLUDING, BUT NOT LIMITED TO, PROCUREMENT OF SUBSTITUTE GOODS OR SERVICES; LOSS OF USE, DATA, OR PROFITS; OR BUSINESS INTERRUPTION) HOWEVER CAUSED AND
 * ON ANY THEORY OF LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY, OR TORT (INCLUDING NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE OF THIS SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY
 * OF SUCH DAMAGE. ====================================================================
 *
 * This software consists of voluntary contributions made by many individuals on behalf of the The International Cooperation for the Integration of Processes in Prepress, Press and Postpress and was
 * originally based on software copyright (c) 1999-2001, Heidelberger Druckmaschinen AG copyright (c) 1999-2001, Agfa-Gevaert N.V.
 *
 * For more information on The International Cooperation for the Integration of Processes in Prepress, Press and Postpress , please see <http://www.cip4.org/>.
 *
 *
 */

package org.cip4.jdflib.auto;

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
import org.cip4.jdflib.core.JDFException;
import org.cip4.jdflib.datatypes.JDFXYPair;
import org.cip4.jdflib.resource.intent.JDFIntentResource;
import org.cip4.jdflib.resource.process.postpress.JDFHoleList;
import org.cip4.jdflib.span.JDFStringSpan;

/**
 *****************************************************************************
 * class JDFAutoHoleMakingIntent : public JDFIntentResource
 *****************************************************************************
 * 
 */

public abstract class JDFAutoHoleMakingIntent extends JDFIntentResource
{

	private static final long serialVersionUID = 1L;

	private static AtrInfoTable[] atrInfoTable = new AtrInfoTable[2];
	static
	{
		atrInfoTable[0] = new AtrInfoTable(AttributeName.HOLEREFERENCEEDGE, 0x33333331, AttributeInfo.EnumAttributeType.enumeration, EnumHoleReferenceEdge.getEnum(0), "Left");
		atrInfoTable[1] = new AtrInfoTable(AttributeName.EXTENT, 0x33333311, AttributeInfo.EnumAttributeType.XYPair, null, null);
	}

	@Override
	protected AttributeInfo getTheAttributeInfo()
	{
		return super.getTheAttributeInfo().updateReplace(atrInfoTable);
	}

	private static ElemInfoTable[] elemInfoTable = new ElemInfoTable[2];
	static
	{
		elemInfoTable[0] = new ElemInfoTable(ElementName.HOLETYPE, 0x55555555);
		elemInfoTable[1] = new ElemInfoTable(ElementName.HOLELIST, 0x66666666);
	}

	@Override
	protected ElementInfo getTheElementInfo()
	{
		return super.getTheElementInfo().updateReplace(elemInfoTable);
	}

	/**
	 * Constructor for JDFAutoHoleMakingIntent
	 * 
	 * @param myOwnerDocument
	 * @param qualifiedName
	 */
	protected JDFAutoHoleMakingIntent(CoreDocumentImpl myOwnerDocument, String qualifiedName)
	{
		super(myOwnerDocument, qualifiedName);
	}

	/**
	 * Constructor for JDFAutoHoleMakingIntent
	 * 
	 * @param myOwnerDocument
	 * @param myNamespaceURI
	 * @param qualifiedName
	 */
	protected JDFAutoHoleMakingIntent(CoreDocumentImpl myOwnerDocument, String myNamespaceURI, String qualifiedName)
	{
		super(myOwnerDocument, myNamespaceURI, qualifiedName);
	}

	/**
	 * Constructor for JDFAutoHoleMakingIntent
	 * 
	 * @param myOwnerDocument
	 * @param myNamespaceURI
	 * @param qualifiedName
	 * @param myLocalName
	 */
	protected JDFAutoHoleMakingIntent(CoreDocumentImpl myOwnerDocument, String myNamespaceURI, String qualifiedName, String myLocalName)
	{
		super(myOwnerDocument, myNamespaceURI, qualifiedName, myLocalName);
	}

	/**
	 * @return the string representation
	 */
	@Override
	public String toString()
	{
		return " JDFAutoHoleMakingIntent[  --> " + super.toString() + " ]";
	}

	/**
	 * Enumeration strings for HoleReferenceEdge
	 */

	@SuppressWarnings("rawtypes")
	public static class EnumHoleReferenceEdge extends ValuedEnum
	{
		private static final long serialVersionUID = 1L;
		private static int m_startValue = 0;

		private EnumHoleReferenceEdge(String name)
		{
			super(name, m_startValue++);
		}

		/**
		 * @param enumName the string to convert
		 * @return the enum
		 */
		public static EnumHoleReferenceEdge getEnum(String enumName)
		{
			return (EnumHoleReferenceEdge) getEnum(EnumHoleReferenceEdge.class, enumName);
		}

		/**
		 * @param enumValue the integer to convert
		 * @return the enum
		 */
		public static EnumHoleReferenceEdge getEnum(int enumValue)
		{
			return (EnumHoleReferenceEdge) getEnum(EnumHoleReferenceEdge.class, enumValue);
		}

		/**
		 * @return the map of enums
		 */
		public static Map getEnumMap()
		{
			return getEnumMap(EnumHoleReferenceEdge.class);
		}

		/**
		 * @return the list of enums
		 */
		public static List getEnumList()
		{
			return getEnumList(EnumHoleReferenceEdge.class);
		}

		/**
		 * @return the iterator
		 */
		public static Iterator iterator()
		{
			return iterator(EnumHoleReferenceEdge.class);
		}

		/**  */
		public static final EnumHoleReferenceEdge Left = new EnumHoleReferenceEdge("Left");
		/**  */
		public static final EnumHoleReferenceEdge Right = new EnumHoleReferenceEdge("Right");
		/**  */
		public static final EnumHoleReferenceEdge Top = new EnumHoleReferenceEdge("Top");
		/**  */
		public static final EnumHoleReferenceEdge Bottom = new EnumHoleReferenceEdge("Bottom");
		/**  */
		public static final EnumHoleReferenceEdge Pattern = new EnumHoleReferenceEdge("Pattern");
	}

	/*
	 * ************************************************************************ Attribute getter / setter ************************************************************************
	 */

	/*
	 * --------------------------------------------------------------------- Methods for Attribute HoleReferenceEdge ---------------------------------------------------------------------
	 */
	/**
	 * (5) set attribute HoleReferenceEdge
	 * 
	 * @param enumVar the enumVar to set the attribute to
	 */
	public void setHoleReferenceEdge(EnumHoleReferenceEdge enumVar)
	{
		setAttribute(AttributeName.HOLEREFERENCEEDGE, enumVar == null ? null : enumVar.getName(), null);
	}

	/**
	 * (9) get attribute HoleReferenceEdge
	 * 
	 * @return the value of the attribute
	 */
	public EnumHoleReferenceEdge getHoleReferenceEdge()
	{
		return EnumHoleReferenceEdge.getEnum(getAttribute(AttributeName.HOLEREFERENCEEDGE, null, "Left"));
	}

	/*
	 * --------------------------------------------------------------------- Methods for Attribute Extent ---------------------------------------------------------------------
	 */
	/**
	 * (36) set attribute Extent
	 * 
	 * @param value the value to set the attribute to
	 */
	public void setExtent(JDFXYPair value)
	{
		setAttribute(AttributeName.EXTENT, value, null);
	}

	/**
	 * (20) get JDFXYPair attribute Extent
	 * 
	 * @return JDFXYPair the value of the attribute, null if a the attribute value is not a valid to create a JDFXYPair
	 */
	public JDFXYPair getExtent()
	{
		final String strAttrName = getAttribute(AttributeName.EXTENT, null, null);
		final JDFXYPair nPlaceHolder = JDFXYPair.createXYPair(strAttrName);
		return nPlaceHolder;
	}

	/*
	 * *********************************************************************** Element getter / setter ***********************************************************************
	 */

	/**
	 * (24) const get element HoleType
	 * 
	 * @return JDFStringSpan the element
	 */
	public JDFStringSpan getHoleType()
	{
		return (JDFStringSpan) getElement(ElementName.HOLETYPE, null, 0);
	}

	/**
	 * (25) getCreateHoleType
	 * 
	 * @return JDFStringSpan the element
	 */
	public JDFStringSpan getCreateHoleType()
	{
		return (JDFStringSpan) getCreateElement(ElementName.HOLETYPE, null, 0);
	}

	/**
	 * (29) append element HoleType
	 * 
	 * @return JDFStringSpan the element
	 * @throws JDFException if the element already exists
	 */
	public JDFStringSpan appendHoleType() throws JDFException
	{
		return (JDFStringSpan) appendElementN(ElementName.HOLETYPE, 1, null);
	}

	/**
	 * (24) const get element HoleList
	 * 
	 * @return JDFHoleList the element
	 */
	public JDFHoleList getHoleList()
	{
		return (JDFHoleList) getElement(ElementName.HOLELIST, null, 0);
	}

	/**
	 * (25) getCreateHoleList
	 * 
	 * @return JDFHoleList the element
	 */
	public JDFHoleList getCreateHoleList()
	{
		return (JDFHoleList) getCreateElement(ElementName.HOLELIST, null, 0);
	}

	/**
	 * (29) append element HoleList
	 * 
	 * @return JDFHoleList the element
	 * @throws JDFException if the element already exists
	 */
	public JDFHoleList appendHoleList() throws JDFException
	{
		return (JDFHoleList) appendElementN(ElementName.HOLELIST, 1, null);
	}

}// end namespace JDF
