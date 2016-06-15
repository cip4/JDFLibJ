/*
 *
 * The CIP4 Software License, Version 1.0
 *
 *
 * Copyright (c) 2001-2016 The International Cooperation for the Integration of 
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
/**
 *
 * Copyright (c) 2001 Heidelberger Druckmaschinen AG, All Rights Reserved.
 *
 * JDFComChannel.java
 *
 * Last changes
 *
 */
package org.cip4.jdflib.resource.process;

import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.enums.ValuedEnum;
import org.apache.xerces.dom.CoreDocumentImpl;
import org.cip4.jdflib.auto.JDFAutoComChannel;
import org.cip4.jdflib.core.JDFConstants;
import org.cip4.jdflib.ifaces.IMatches;
import org.cip4.jdflib.util.ContainerUtil;
import org.cip4.jdflib.util.StringUtil;

/**
 * @author Dr. Rainer Prosi, Heidelberger Druckmaschinen AG
 * 
 * 13.02.2009
 */
public class JDFComChannel extends JDFAutoComChannel implements IMatches
{
	private static final String PHONE_CHARS = "+0123456789";
	private static final long serialVersionUID = 1L;
	/**
	 * 
	 */
	public static final String MAILTO = "mailto:";
	/**
	 * 
	 */
	public static final String TEL = "tel:";

	/**
	 * some additionally specified details from the spec
	 * @author Dr. Rainer Prosi, Heidelberger Druckmaschinen AG
	 * 
	 * 13.02.2009
	 */
	public static class EnumChannelTypeDetails extends ValuedEnum
	{
		private static final long serialVersionUID = 1L;
		private static int m_startValue = 0;

		private EnumChannelTypeDetails(final String name)
		{
			super(name, m_startValue++);
		}

		/**
		 * 
		 * 
		 * @param enumName
		 * @return
		 */
		public static EnumChannelTypeDetails getEnum(final String enumName)
		{
			return (EnumChannelTypeDetails) getEnum(EnumChannelTypeDetails.class, enumName);
		}

		/**
		 * 
		 *  
		 * @param enumValue
		 * @return
		 */
		public static EnumChannelTypeDetails getEnum(final int enumValue)
		{
			return (EnumChannelTypeDetails) getEnum(EnumChannelTypeDetails.class, enumValue);
		}

		public static Map getEnumMap()
		{
			return getEnumMap(EnumChannelTypeDetails.class);
		}

		/**
		 * 
		 *  
		 * @return
		 */
		public static List getEnumList()
		{
			return getEnumList(EnumChannelTypeDetails.class);
		}

		/**
		 * 
		 *  
		 * @return
		 */
		public static Iterator iterator()
		{
			return iterator(EnumChannelTypeDetails.class);
		}

		/**
		 * @deprectated - use null
		 */
		public static final EnumChannelTypeDetails Unknown = new EnumChannelTypeDetails("Unknown");
		/** */
		public static final EnumChannelTypeDetails LandLine = new EnumChannelTypeDetails("LandLine");
		/** */
		public static final EnumChannelTypeDetails Mobile = new EnumChannelTypeDetails("Mobile");
		/** */
		public static final EnumChannelTypeDetails Secure = new EnumChannelTypeDetails("Secure");
		/** */
		public static final EnumChannelTypeDetails ISDN = new EnumChannelTypeDetails("ISDN");
		/** */
		public static final EnumChannelTypeDetails Form = new EnumChannelTypeDetails("Form");
		/** */
		public static final EnumChannelTypeDetails Target = new EnumChannelTypeDetails("Target");
	}

	/**
	 * Constructor for JDFComChannel
	 * 
	 * @param myOwnerDocument
	 * @param qualifiedName
	 */
	public JDFComChannel(final CoreDocumentImpl myOwnerDocument, final String qualifiedName)
	{
		super(myOwnerDocument, qualifiedName);
	}

	/**
	 * Constructor for JDFComChannel
	 * 
	 * @param myOwnerDocument
	 * @param myNamespaceURI
	 * @param qualifiedName
	 */
	public JDFComChannel(final CoreDocumentImpl myOwnerDocument, final String myNamespaceURI, final String qualifiedName)
	{
		super(myOwnerDocument, myNamespaceURI, qualifiedName);
	}

	/**
	 * 
	 * Constructor for JDFComChannel
	 * @param myOwnerDocument
	 * @param myNamespaceURI
	 * @param qualifiedName
	 * @param myLocalName
	 */
	public JDFComChannel(final CoreDocumentImpl myOwnerDocument, final String myNamespaceURI, final String qualifiedName, final String myLocalName)
	{
		super(myOwnerDocument, myNamespaceURI, qualifiedName, myLocalName);
	}

	// **************************************** Methods
	// *********************************************
	/**
	 * toString
	 * 
	 * @return String
	 */
	@Override
	public String toString()
	{
		return "JDFComChannel[  --> " + super.toString() + " ]";
	}

	/**
	 * sets locator to the string specified in eMail, checking valid email syntax "mailto:" is prepended, if it is not yet there
	 * 
	 * @param eMail the email address
	 * @throws IllegalArgumentException if eMail is not a valid emai as defined by JDFConstants.REGEXP_EMAIL
	 */
	public void setEMailLocator(final String eMail)
	{
		String eMailLocal = eMail;

		if (eMailLocal != null)
		{
			eMailLocal = eMailLocal.trim();
		}

		if (eMailLocal == null || !StringUtil.matchesIgnoreCase(eMailLocal, JDFConstants.REGEXP_EMAIL))
		{
			throw new IllegalArgumentException("illegal email address:" + eMailLocal);
		}

		setChannelType(EnumChannelType.Email);
		if (!eMailLocal.toLowerCase().startsWith(MAILTO))
		{
			eMailLocal = MAILTO + eMailLocal;
		}

		setLocator(eMailLocal);
	}

	/**
	 * get the email address of this, if this is an email address, else null any "mailto" is stripped
	 * 
	 * @return
	 */
	public String getEMailAddress()
	{
		if (!EnumChannelType.Email.equals(getChannelType()))
		{
			return null;
		}
		final String locator = getLocator();
		if (!StringUtil.matchesIgnoreCase(locator, JDFConstants.REGEXP_EMAIL))
		{
			return null;
		}
		return StringUtil.stripPrefix(locator, MAILTO, true);
	}

	/**
	 * get the phone number of this, if this is a valid phone address, else null any "tel:" or "fax:" is stripped
	 * 
	 * @param stripNonNumerical if true, remove any valid brackets, . / etc. so that a purely numerical code (except for an optional "+" for international) is
	 * returned
	 * @return the phone number
	 */
	public String getPhoneNumber(final boolean stripNonNumerical)
	{
		final EnumChannelType channelType = getChannelType();
		if (!EnumChannelType.Fax.equals(channelType) && !EnumChannelType.Phone.equals(channelType) && !EnumChannelType.Mobile.equals(channelType))
		{
			return null;
		}
		String locator = getLocator();
		if (!StringUtil.matchesIgnoreCase(locator, JDFConstants.REGEXP_PHONE))
		{
			return null;
		}
		locator = StringUtil.stripPrefix(locator, TEL, true);
		if (stripNonNumerical)
		{
			locator = StringUtil.stripNot(locator, PHONE_CHARS);
		}
		return locator;
	}

	/**
	 * set the phone number of this, if this is a valid phone url, "tel:" or "fax:" is prepended, if it is not yet there
	 * 
	 * @param phone the phone number string
	 * @throws IllegalArgumentException if phone is not a valid phone number
	 * 
	 */
	public void setPhoneNumber(String phone)
	{
		if (phone == null)
		{
			setLocator(null);
			return;
		}
		EnumChannelType channelType = getChannelType();
		setPhoneNumber(phone, ".", channelType);
	}

	/**
	 * set the phone number of this, if this is a valid phone url, "tel:" or "fax:" is prepended, if it is not yet there
	 * 
	 * @param phone the phone number string
	 * @param replaceForBlank the replacement char for non-leading blanks , typically "." or null are a good idea
	 * @param channelType the channelType - must be either Fax, Phone or Mobile
	 * 
	 * @throws IllegalArgumentException if phone is not a valid phone number
	 * 
	 */
	public void setPhoneNumber(String phone, final String replaceForBlank, EnumChannelType channelType)
	{
		if (channelType == null)
			channelType = getChannelType();
		if (!EnumChannelType.Fax.equals(channelType) && !EnumChannelType.Phone.equals(channelType) && !EnumChannelType.Mobile.equals(channelType))
		{
			throw new IllegalArgumentException("illegal channelType: " + channelType);
		}

		if (phone != null)
		{
			phone = phone.trim();
		}

		phone = StringUtil.replaceCharSet(phone, " ", replaceForBlank, 0);
		if (phone == null || !StringUtil.matches(phone, JDFConstants.REGEXP_PHONE))
		{
			throw new IllegalArgumentException("illegal phone number:" + phone);
		}

		setChannelType(channelType);
		if (!phone.toLowerCase().startsWith(TEL))
		{
			phone = TEL + phone;
		}

		setLocator(phone);
	}

	/**
	 * update to mobile
	 * 
	 * @see org.cip4.jdflib.auto.JDFAutoComChannel#getChannelType()
	 */
	@Override
	public EnumChannelType getChannelType()
	{
		EnumChannelType channelType = super.getChannelType();
		if (EnumChannelType.Phone.equals(channelType))
		{
			String ctd = getChannelTypeDetails();
			ctd = StringUtil.normalize(ctd, true);
			if ("mobile".equals(ctd))
			{
				channelType = EnumChannelType.Mobile;
			}
		}
		return channelType;
	}

	@Override
	public boolean matches(Object subset)
	{
		if (subset instanceof String)
		{
			return stringMatch((String) subset);
		}
		else if (subset instanceof JDFComChannel)
		{
			return matchesComChannel((JDFComChannel) subset);
		}
		return false;
	}

	/**
	 * 
	 * @param other
	 * @return
	 */
	boolean matchesComChannel(JDFComChannel other)
	{
		if (!ContainerUtil.equals(getChannelType(), other.getChannelType()))
			return false;
		if (!ContainerUtil.equals(getChannelTypeDetails(), other.getChannelTypeDetails()))
			return false;
		if (!ContainerUtil.equals(getChannelUsage(), other.getChannelUsage()))
			return false;
		return matches(other.getLocator());
	}

	/**
	 * 
	 * @param s
	 * @return
	 */
	boolean stringMatch(String s)
	{
		boolean b = StringUtil.equals(StringUtil.normalize(s, true, null), StringUtil.normalize(getLocator(), true, null));
		if (!b)
		{
			EnumChannelType channelType = getChannelType();
			if (EnumChannelType.Fax.equals(channelType) || EnumChannelType.Phone.equals(channelType) || EnumChannelType.Mobile.equals(channelType))
			{
				b = StringUtil.equals(StringUtil.stripNot(s, PHONE_CHARS), StringUtil.stripNot(getLocator(), PHONE_CHARS));
			}
		}
		return b;
	}
}
