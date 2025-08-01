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
import org.cip4.jdflib.core.JDFCoreConstants;
import org.cip4.jdflib.core.VString;
import org.cip4.jdflib.resource.JDFResource;
import org.cip4.jdflib.util.JavaEnumUtil;

/**
 *****************************************************************************
 * class JDFAutoComChannel : public JDFResource
 *****************************************************************************
 * 
 */

public abstract class JDFAutoComChannel extends JDFResource
{

	private static final long serialVersionUID = 1L;

	private static AtrInfoTable[] atrInfoTable = new AtrInfoTable[4];
	static
	{
		atrInfoTable[0] = new AtrInfoTable(AttributeName.CHANNELTYPE, 0x2222222222l, AttributeInfo.EnumAttributeType.enumeration, EnumChannelType.getEnum(0), null);
		atrInfoTable[1] = new AtrInfoTable(AttributeName.LOCATOR, 0x2222222222l, AttributeInfo.EnumAttributeType.string, null, null);
		atrInfoTable[2] = new AtrInfoTable(AttributeName.CHANNELTYPEDETAILS, 0x3333333311l, AttributeInfo.EnumAttributeType.NMTOKEN, null, null);
		atrInfoTable[3] = new AtrInfoTable(AttributeName.CHANNELUSAGE, 0x3333333311l, AttributeInfo.EnumAttributeType.NMTOKENS, null, null);
	}

	@Override
	protected AttributeInfo getTheAttributeInfo()
	{
		return super.getTheAttributeInfo().updateReplace(atrInfoTable);
	}

	/**
	 * Constructor for JDFAutoComChannel
	 *
	 * @param myOwnerDocument
	 * @param qualifiedName
	 */
	protected JDFAutoComChannel(CoreDocumentImpl myOwnerDocument, String qualifiedName)
	{
		super(myOwnerDocument, qualifiedName);
	}

	/**
	 * Constructor for JDFAutoComChannel
	 *
	 * @param myOwnerDocument
	 * @param myNamespaceURI
	 * @param qualifiedName
	 */
	protected JDFAutoComChannel(CoreDocumentImpl myOwnerDocument, String myNamespaceURI, String qualifiedName)
	{
		super(myOwnerDocument, myNamespaceURI, qualifiedName);
	}

	/**
	 * Constructor for JDFAutoComChannel
	 *
	 * @param myOwnerDocument
	 * @param myNamespaceURI
	 * @param qualifiedName
	 * @param myLocalName
	 */
	protected JDFAutoComChannel(CoreDocumentImpl myOwnerDocument, String myNamespaceURI, String qualifiedName, String myLocalName)
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
	 * Enumeration strings for ChannelType
	 */

	public enum EChannelType
	{
		ComputerName, Email, Fax, InstantMessaging, JMF, Mobile, Phone, PrivateDirectory, WWW;

		public static EChannelType getEnum(String val)
		{
			return JavaEnumUtil.getEnumIgnoreCase(EChannelType.class, val, null);
		}
	}

	/**
	 * Enumeration strings for ChannelType
	 */

	@SuppressWarnings("rawtypes")
	public static class EnumChannelType extends ValuedEnum
	{
		private static final long serialVersionUID = 1L;
		private static int m_startValue = 0;

		protected EnumChannelType(String name)
		{
			super(name, m_startValue++);
		}

		/**
		 * @param enumName the string to convert
		 * @return the enum
		 */
		public static EnumChannelType getEnum(String enumName)
		{
			return (EnumChannelType) getEnum(EnumChannelType.class, enumName);
		}

		/**
		 * @param enumValue the integer to convert
		 * @return the enum
		 */
		public static EnumChannelType getEnum(int enumValue)
		{
			return (EnumChannelType) getEnum(EnumChannelType.class, enumValue);
		}

		/**
		 * @return the map of enums
		 */
		public static Map getEnumMap()
		{
			return getEnumMap(EnumChannelType.class);
		}

		/**
		 * @return the list of enums
		 */
		public static List getEnumList()
		{
			return getEnumList(EnumChannelType.class);
		}

		/**
		 * @return the iterator
		 */
		public static Iterator iterator()
		{
			return iterator(EnumChannelType.class);
		}

		/**  */
		public static final EnumChannelType ComputerName = new EnumChannelType("ComputerName");
		/**  */
		public static final EnumChannelType Email = new EnumChannelType("Email");
		/**  */
		public static final EnumChannelType Fax = new EnumChannelType("Fax");
		/**  */
		public static final EnumChannelType InstantMessaging = new EnumChannelType("InstantMessaging");
		/**  */
		public static final EnumChannelType JMF = new EnumChannelType("JMF");
		/**  */
		public static final EnumChannelType Mobile = new EnumChannelType("Mobile");
		/**  */
		public static final EnumChannelType Phone = new EnumChannelType("Phone");
		/**  */
		public static final EnumChannelType PrivateDirectory = new EnumChannelType("PrivateDirectory");
		/**  */
		public static final EnumChannelType WWW = new EnumChannelType("WWW");
	}

	/*
	 * ************************************************************************ Attribute getter / setter ************************************************************************
	 */

	/*
	 * --------------------------------------------------------------------- Methods for Attribute ChannelType ---------------------------------------------------------------------
	 */
	/**
	 * (5) set attribute ChannelType
	 *
	 * @param enumVar the enumVar to set the attribute to
	 */
	public void setChannelType(EChannelType enumVar)
	{
		setAttribute(AttributeName.CHANNELTYPE, enumVar == null ? null : enumVar.name(), null);
	}

	/**
	 * (9) get attribute ChannelType
	 *
	 * @return the value of the attribute
	 */
	public EChannelType getEChannelType()
	{
		return EChannelType.getEnum(getAttribute(AttributeName.CHANNELTYPE, null, null));
	}

	/*
	 * --------------------------------------------------------------------- Methods for Attribute ChannelType ---------------------------------------------------------------------
	 */
	/**
	 * (5) set attribute ChannelType
	 *
	 * @param enumVar the enumVar to set the attribute to
	 * @deprecated use java.lang.enum
	 */
	@Deprecated
	public void setChannelType(EnumChannelType enumVar)
	{
		setAttribute(AttributeName.CHANNELTYPE, enumVar == null ? null : enumVar.getName(), null);
	}

	/**
	 * (9) get attribute ChannelType
	 *
	 * @return the value of the attribute
	 */
	public EnumChannelType getChannelType()
	{
		return EnumChannelType.getEnum(getAttribute(AttributeName.CHANNELTYPE, null, null));
	}

	/*
	 * --------------------------------------------------------------------- Methods for Attribute Locator ---------------------------------------------------------------------
	 */
	/**
	 * (36) set attribute Locator
	 *
	 * @param value the value to set the attribute to
	 */
	public void setLocator(String value)
	{
		setAttribute(AttributeName.LOCATOR, value, null);
	}

	/**
	 * (23) get String attribute Locator
	 *
	 * @return the value of the attribute
	 */
	public String getLocator()
	{
		return getAttribute(AttributeName.LOCATOR, null, JDFCoreConstants.EMPTYSTRING);
	}

	/*
	 * --------------------------------------------------------------------- Methods for Attribute ChannelTypeDetails
	 * ---------------------------------------------------------------------
	 */
	/**
	 * (36) set attribute ChannelTypeDetails
	 *
	 * @param value the value to set the attribute to
	 */
	public void setChannelTypeDetails(String value)
	{
		setAttribute(AttributeName.CHANNELTYPEDETAILS, value, null);
	}

	/**
	 * (23) get String attribute ChannelTypeDetails
	 *
	 * @return the value of the attribute
	 */
	public String getChannelTypeDetails()
	{
		return getAttribute(AttributeName.CHANNELTYPEDETAILS, null, JDFCoreConstants.EMPTYSTRING);
	}

	/*
	 * --------------------------------------------------------------------- Methods for Attribute ChannelUsage
	 * ---------------------------------------------------------------------
	 */
	/**
	 * (36) set attribute ChannelUsage
	 *
	 * @param value the value to set the attribute to
	 */
	public void setChannelUsage(VString value)
	{
		setAttribute(AttributeName.CHANNELUSAGE, value, null);
	}

	/**
	 * (21) get VString attribute ChannelUsage
	 *
	 * @return VString the value of the attribute
	 */
	public VString getChannelUsage()
	{
		final VString vStrAttrib = new VString();
		final String s = getAttribute(AttributeName.CHANNELUSAGE, null, JDFCoreConstants.EMPTYSTRING);
		vStrAttrib.setAllStrings(s, " ");
		return vStrAttrib;
	}

}
