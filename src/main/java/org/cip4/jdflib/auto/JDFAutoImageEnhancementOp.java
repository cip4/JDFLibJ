/*
 * The CIP4 Software License, Version 1.0
 *
 *
 * Copyright (c) 2001-2020 The International Cooperation for the Integration of
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

import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Vector;

import org.apache.commons.lang.enums.ValuedEnum;
import org.apache.xerces.dom.CoreDocumentImpl;
import org.cip4.jdflib.core.AtrInfoTable;
import org.cip4.jdflib.core.AttributeInfo;
import org.cip4.jdflib.core.AttributeName;
import org.cip4.jdflib.core.JDFCoreConstants;
import org.cip4.jdflib.core.JDFElement;
import org.cip4.jdflib.core.VString;

/**
 *****************************************************************************
 * class JDFAutoImageEnhancementOp : public JDFElement
 *****************************************************************************
 *
 */

public abstract class JDFAutoImageEnhancementOp extends JDFElement
{

	private static final long serialVersionUID = 1L;

	private static AtrInfoTable[] atrInfoTable = new AtrInfoTable[4];
	static
	{
		atrInfoTable[0] = new AtrInfoTable(AttributeName.OBJECTTAGS, 0x33333333, AttributeInfo.EnumAttributeType.NMTOKENS, null, null);
		atrInfoTable[1] = new AtrInfoTable(AttributeName.OPERATION, 0x22222222, AttributeInfo.EnumAttributeType.NMTOKEN, null, null);
		atrInfoTable[2] = new AtrInfoTable(AttributeName.OPERATIONDETAILS, 0x33333333, AttributeInfo.EnumAttributeType.string, null, null);
		atrInfoTable[3] = new AtrInfoTable(AttributeName.SOURCEOBJECTS, 0x33333333, AttributeInfo.EnumAttributeType.enumerations, EnumSourceObjects.getEnum(0), null);
	}

	@Override
	protected AttributeInfo getTheAttributeInfo()
	{
		return super.getTheAttributeInfo().updateReplace(atrInfoTable);
	}

	/**
	 * Constructor for JDFAutoImageEnhancementOp
	 *
	 * @param myOwnerDocument
	 * @param qualifiedName
	 */
	protected JDFAutoImageEnhancementOp(CoreDocumentImpl myOwnerDocument, String qualifiedName)
	{
		super(myOwnerDocument, qualifiedName);
	}

	/**
	 * Constructor for JDFAutoImageEnhancementOp
	 *
	 * @param myOwnerDocument
	 * @param myNamespaceURI
	 * @param qualifiedName
	 */
	protected JDFAutoImageEnhancementOp(CoreDocumentImpl myOwnerDocument, String myNamespaceURI, String qualifiedName)
	{
		super(myOwnerDocument, myNamespaceURI, qualifiedName);
	}

	/**
	 * Constructor for JDFAutoImageEnhancementOp
	 *
	 * @param myOwnerDocument
	 * @param myNamespaceURI
	 * @param qualifiedName
	 * @param myLocalName
	 */
	protected JDFAutoImageEnhancementOp(CoreDocumentImpl myOwnerDocument, String myNamespaceURI, String qualifiedName, String myLocalName)
	{
		super(myOwnerDocument, myNamespaceURI, qualifiedName, myLocalName);
	}

	/**
	 * Enumeration strings for SourceObjects
	 */

	@SuppressWarnings("rawtypes")
	public static class EnumSourceObjects extends ValuedEnum
	{
		private static final long serialVersionUID = 1L;
		private static int m_startValue = 0;

		private EnumSourceObjects(String name)
		{
			super(name, m_startValue++);
		}

		/**
		 * @param enumName the string to convert
		 * @return the enum
		 */
		public static EnumSourceObjects getEnum(String enumName)
		{
			return (EnumSourceObjects) getEnum(EnumSourceObjects.class, enumName);
		}

		/**
		 * @param enumValue the integer to convert
		 * @return the enum
		 */
		public static EnumSourceObjects getEnum(int enumValue)
		{
			return (EnumSourceObjects) getEnum(EnumSourceObjects.class, enumValue);
		}

		/**
		 * @return the map of enums
		 */
		public static Map getEnumMap()
		{
			return getEnumMap(EnumSourceObjects.class);
		}

		/**
		 * @return the list of enums
		 */
		public static List getEnumList()
		{
			return getEnumList(EnumSourceObjects.class);
		}

		/**
		 * @return the iterator
		 */
		public static Iterator iterator()
		{
			return iterator(EnumSourceObjects.class);
		}

		/**  */
		public static final EnumSourceObjects All = new EnumSourceObjects("All");
		/**  */
		public static final EnumSourceObjects ImagePhotographic = new EnumSourceObjects("ImagePhotographic");
		/**  */
		public static final EnumSourceObjects ImageScreenShot = new EnumSourceObjects("ImageScreenShot");
		/**  */
		public static final EnumSourceObjects LineArt = new EnumSourceObjects("LineArt");
		/**  */
		public static final EnumSourceObjects SmoothShades = new EnumSourceObjects("SmoothShades");
		/**  */
		public static final EnumSourceObjects Text = new EnumSourceObjects("Text");
	}

	/* ************************************************************************
	 * Attribute getter / setter
	 * ************************************************************************
	 */

	/* ---------------------------------------------------------------------
	Methods for Attribute ObjectTags
	--------------------------------------------------------------------- */
	/**
	 * (36) set attribute ObjectTags
	 *
	 * @param value the value to set the attribute to
	 */
	public void setObjectTags(VString value)
	{
		setAttribute(AttributeName.OBJECTTAGS, value, null);
	}

	/**
	 * (21) get VString attribute ObjectTags
	 *
	 * @return VString the value of the attribute
	 */
	public VString getObjectTags()
	{
		final VString vStrAttrib = new VString();
		final String s = getAttribute(AttributeName.OBJECTTAGS, null, JDFCoreConstants.EMPTYSTRING);
		vStrAttrib.setAllStrings(s, " ");
		return vStrAttrib;
	}

	/* ---------------------------------------------------------------------
	Methods for Attribute Operation
	--------------------------------------------------------------------- */
	/**
	 * (36) set attribute Operation
	 *
	 * @param value the value to set the attribute to
	 */
	public void setOperation(String value)
	{
		setAttribute(AttributeName.OPERATION, value, null);
	}

	/**
	 * (23) get String attribute Operation
	 *
	 * @return the value of the attribute
	 */
	public String getOperation()
	{
		return getAttribute(AttributeName.OPERATION, null, JDFCoreConstants.EMPTYSTRING);
	}

	/* ---------------------------------------------------------------------
	Methods for Attribute OperationDetails
	--------------------------------------------------------------------- */
	/**
	 * (36) set attribute OperationDetails
	 *
	 * @param value the value to set the attribute to
	 */
	public void setOperationDetails(String value)
	{
		setAttribute(AttributeName.OPERATIONDETAILS, value, null);
	}

	/**
	 * (23) get String attribute OperationDetails
	 *
	 * @return the value of the attribute
	 */
	public String getOperationDetails()
	{
		return getAttribute(AttributeName.OPERATIONDETAILS, null, JDFCoreConstants.EMPTYSTRING);
	}

	/* ---------------------------------------------------------------------
	Methods for Attribute SourceObjects
	--------------------------------------------------------------------- */
	/**
	 * (5.2) set attribute SourceObjects
	 *
	 * @param v vector of the enumeration values
	 */
	public void setSourceObjects(Vector<? extends ValuedEnum> v)
	{
		setEnumerationsAttribute(AttributeName.SOURCEOBJECTS, v, null);
	}

	/**
	 * (9.2) get SourceObjects attribute SourceObjects
	 *
	 * @return Vector of the enumerations
	 */
	public Vector<? extends ValuedEnum> getSourceObjects()
	{
		return getEnumerationsAttribute(AttributeName.SOURCEOBJECTS, null, EnumSourceObjects.getEnum(0), false);
	}

}
