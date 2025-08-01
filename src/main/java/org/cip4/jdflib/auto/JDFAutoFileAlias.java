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
import org.cip4.jdflib.core.JDFElement;
import org.cip4.jdflib.resource.process.JDFFileSpec;
import org.cip4.jdflib.util.JavaEnumUtil;

/**
 *****************************************************************************
 * class JDFAutoFileAlias : public JDFElement
 *****************************************************************************
 * 
 */

public abstract class JDFAutoFileAlias extends JDFElement
{

	private static final long serialVersionUID = 1L;

	private static AtrInfoTable[] atrInfoTable = new AtrInfoTable[5];
	static
	{
		atrInfoTable[0] = new AtrInfoTable(AttributeName.ALIAS, 0x2222222222l, AttributeInfo.EnumAttributeType.string, null, null);
		atrInfoTable[1] = new AtrInfoTable(AttributeName.DISPOSITION, 0x4444444433l, AttributeInfo.EnumAttributeType.enumeration, EnumDisposition.getEnum(0), null);
		atrInfoTable[2] = new AtrInfoTable(AttributeName.MIMETYPE, 0x4444444433l, AttributeInfo.EnumAttributeType.string, null, null);
		atrInfoTable[3] = new AtrInfoTable(AttributeName.RAWALIAS, 0x3333333311l, AttributeInfo.EnumAttributeType.hexBinary, null, null);
		atrInfoTable[4] = new AtrInfoTable(AttributeName.URL, 0x4444444433l, AttributeInfo.EnumAttributeType.URL, null, null);
	}

	@Override
	protected AttributeInfo getTheAttributeInfo()
	{
		return super.getTheAttributeInfo().updateReplace(atrInfoTable);
	}

	private static ElemInfoTable[] elemInfoTable = new ElemInfoTable[1];
	static
	{
		elemInfoTable[0] = new ElemInfoTable(ElementName.FILESPEC, 0x6666666611l);
	}

	@Override
	protected ElementInfo getTheElementInfo()
	{
		return super.getTheElementInfo().updateReplace(elemInfoTable);
	}

	/**
	 * Constructor for JDFAutoFileAlias
	 *
	 * @param myOwnerDocument
	 * @param qualifiedName
	 */
	protected JDFAutoFileAlias(CoreDocumentImpl myOwnerDocument, String qualifiedName)
	{
		super(myOwnerDocument, qualifiedName);
	}

	/**
	 * Constructor for JDFAutoFileAlias
	 *
	 * @param myOwnerDocument
	 * @param myNamespaceURI
	 * @param qualifiedName
	 */
	protected JDFAutoFileAlias(CoreDocumentImpl myOwnerDocument, String myNamespaceURI, String qualifiedName)
	{
		super(myOwnerDocument, myNamespaceURI, qualifiedName);
	}

	/**
	 * Constructor for JDFAutoFileAlias
	 *
	 * @param myOwnerDocument
	 * @param myNamespaceURI
	 * @param qualifiedName
	 * @param myLocalName
	 */
	protected JDFAutoFileAlias(CoreDocumentImpl myOwnerDocument, String myNamespaceURI, String qualifiedName, String myLocalName)
	{
		super(myOwnerDocument, myNamespaceURI, qualifiedName, myLocalName);
	}

	/**
	 * Enumeration strings for Disposition
	 */

	public enum EDisposition
	{
		Unlink, Delete, Retain;

		public static EDisposition getEnum(String val)
		{
			return JavaEnumUtil.getEnumIgnoreCase(EDisposition.class, val, null);
		}
	}

	/**
	 * Enumeration strings for Disposition
	 */

	@SuppressWarnings("rawtypes")
	public static class EnumDisposition extends ValuedEnum
	{
		private static final long serialVersionUID = 1L;
		private static int m_startValue = 0;

		protected EnumDisposition(String name)
		{
			super(name, m_startValue++);
		}

		/**
		 * @param enumName the string to convert
		 * @return the enum
		 */
		public static EnumDisposition getEnum(String enumName)
		{
			return (EnumDisposition) getEnum(EnumDisposition.class, enumName);
		}

		/**
		 * @param enumValue the integer to convert
		 * @return the enum
		 */
		public static EnumDisposition getEnum(int enumValue)
		{
			return (EnumDisposition) getEnum(EnumDisposition.class, enumValue);
		}

		/**
		 * @return the map of enums
		 */
		public static Map getEnumMap()
		{
			return getEnumMap(EnumDisposition.class);
		}

		/**
		 * @return the list of enums
		 */
		public static List getEnumList()
		{
			return getEnumList(EnumDisposition.class);
		}

		/**
		 * @return the iterator
		 */
		public static Iterator iterator()
		{
			return iterator(EnumDisposition.class);
		}

		/**  */
		public static final EnumDisposition Unlink = new EnumDisposition("Unlink");
		/**  */
		public static final EnumDisposition Delete = new EnumDisposition("Delete");
		/**  */
		public static final EnumDisposition Retain = new EnumDisposition("Retain");
	}

	/*
	 * ************************************************************************ Attribute getter / setter ************************************************************************
	 */

	/*
	 * --------------------------------------------------------------------- Methods for Attribute Alias ---------------------------------------------------------------------
	 */
	/**
	 * (36) set attribute Alias
	 *
	 * @param value the value to set the attribute to
	 */
	public void setAlias(String value)
	{
		setAttribute(AttributeName.ALIAS, value, null);
	}

	/**
	 * (23) get String attribute Alias
	 *
	 * @return the value of the attribute
	 */
	public String getAlias()
	{
		return getAttribute(AttributeName.ALIAS, null, JDFCoreConstants.EMPTYSTRING);
	}

	/*
	 * --------------------------------------------------------------------- Methods for Attribute Disposition ---------------------------------------------------------------------
	 */
	/**
	 * (5) set attribute Disposition
	 *
	 * @param enumVar the enumVar to set the attribute to
	 */
	public void setDisposition(EDisposition enumVar)
	{
		setAttribute(AttributeName.DISPOSITION, enumVar == null ? null : enumVar.name(), null);
	}

	/**
	 * (9) get attribute Disposition
	 *
	 * @return the value of the attribute
	 */
	public EDisposition getEDisposition()
	{
		return EDisposition.getEnum(getAttribute(AttributeName.DISPOSITION, null, null));
	}

	/*
	 * --------------------------------------------------------------------- Methods for Attribute Disposition ---------------------------------------------------------------------
	 */
	/**
	 * (5) set attribute Disposition
	 *
	 * @param enumVar the enumVar to set the attribute to
	 * @deprecated use java.lang.enum
	 */
	@Deprecated
	public void setDisposition(EnumDisposition enumVar)
	{
		setAttribute(AttributeName.DISPOSITION, enumVar == null ? null : enumVar.getName(), null);
	}

	/**
	 * (9) get attribute Disposition
	 *
	 * @return the value of the attribute
	 */
	public EnumDisposition getDisposition()
	{
		return EnumDisposition.getEnum(getAttribute(AttributeName.DISPOSITION, null, null));
	}

	/*
	 * --------------------------------------------------------------------- Methods for Attribute MimeType ---------------------------------------------------------------------
	 */
	/**
	 * (36) set attribute MimeType
	 *
	 * @param value the value to set the attribute to
	 */
	public void setMimeType(String value)
	{
		setAttribute(AttributeName.MIMETYPE, value, null);
	}

	/**
	 * (23) get String attribute MimeType
	 *
	 * @return the value of the attribute
	 */
	public String getMimeType()
	{
		return getAttribute(AttributeName.MIMETYPE, null, JDFCoreConstants.EMPTYSTRING);
	}

	/*
	 * --------------------------------------------------------------------- Methods for Attribute RawAlias ---------------------------------------------------------------------
	 */
	/**
	 * (36) set attribute RawAlias
	 *
	 * @param value the value to set the attribute to
	 */
	public void setRawAlias(String value)
	{
		setAttribute(AttributeName.RAWALIAS, value, null);
	}

	/**
	 * (23) get String attribute RawAlias
	 *
	 * @return the value of the attribute
	 */
	public String getRawAlias()
	{
		return getAttribute(AttributeName.RAWALIAS, null, JDFCoreConstants.EMPTYSTRING);
	}

	/*
	 * --------------------------------------------------------------------- Methods for Attribute URL ---------------------------------------------------------------------
	 */
	/**
	 * (36) set attribute URL
	 *
	 * @param value the value to set the attribute to
	 */
	public void setURL(String value)
	{
		setAttribute(AttributeName.URL, value, null);
	}

	/**
	 * (23) get String attribute URL
	 *
	 * @return the value of the attribute
	 */
	public String getURL()
	{
		return getAttribute(AttributeName.URL, null, JDFCoreConstants.EMPTYSTRING);
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
	 * (29) append element FileSpec
	 *
	 * @return JDFFileSpec the element @ if the element already exists
	 */
	public JDFFileSpec appendFileSpec()
	{
		return (JDFFileSpec) appendElementN(ElementName.FILESPEC, 1, null);
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

}
