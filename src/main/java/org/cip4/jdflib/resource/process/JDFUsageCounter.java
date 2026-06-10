/*
 * The CIP4 Software License, Version 1.0
 *
 *
 * Copyright (c) 2001-2012 The International Cooperation for the Integration of
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
 *//**
	*
	* Copyright (c) 2001 Heidelberger Druckmaschinen AG, All Rights Reserved.
	*
	* JDFUsageCounter.java
	*
	* Last changes
	*
	*/
package org.cip4.jdflib.resource.process;

import java.util.List;
import java.util.Vector;

import org.apache.xerces.dom.CoreDocumentImpl;
import org.cip4.jdflib.auto.JDFAutoUsageCounter;
import org.cip4.jdflib.core.AttributeName;
import org.cip4.jdflib.util.JavaEnumUtil;
import org.cip4.jdflib.util.StringUtil;
import org.w3c.dom.DOMException;

/**
 * @author rainer prosi
 * @date Jan 10, 2012
 */
public class JDFUsageCounter extends JDFAutoUsageCounter
{
	private static final long serialVersionUID = 1L;

	/**
	 * Constructor for JDFUsageCounter
	 *
	 * @param myOwnerDocument
	 * @param qualifiedName
	 * @throws DOMException
	 */
	public JDFUsageCounter(final CoreDocumentImpl myOwnerDocument, final String qualifiedName) throws DOMException
	{
		super(myOwnerDocument, qualifiedName);
	}

	/**
	 * Constructor for JDFUsageCounter
	 *
	 * @param myOwnerDocument
	 * @param myNamespaceURI
	 * @param qualifiedName
	 * @throws DOMException
	 */
	public JDFUsageCounter(final CoreDocumentImpl myOwnerDocument, final String myNamespaceURI, final String qualifiedName) throws DOMException
	{
		super(myOwnerDocument, myNamespaceURI, qualifiedName);
	}

	/**
	 * Constructor for JDFUsageCounter
	 *
	 * @param myOwnerDocument
	 * @param myNamespaceURI
	 * @param qualifiedName
	 * @param myLocalName
	 * @throws DOMException
	 */
	public JDFUsageCounter(final CoreDocumentImpl myOwnerDocument, final String myNamespaceURI, final String qualifiedName, final String myLocalName) throws DOMException
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
		return "JDFUsageCounter[  --> " + super.toString() + " ]";
	}

	/**
	 * @author rainerprosi
	 * @date Jan 10, 2012
	 */
	public enum EnumCounterType
	{
		Blank, Insert, InsertPrefuser, OneSided, TwoSided, NormalSize, LargeSize, Black, HighlightColor, Color, Separation, Varnish, User, Auxiliary, Impressions, Clicks, pt;

		public static EnumCounterType getEnum(final String enumName)
		{
			return JavaEnumUtil.getEnumIgnoreCase(EnumCounterType.class, enumName, null);
		}

		public static EnumCounterType getEnum(final int enumValue)
		{
			final EnumCounterType[] values = values();
			return enumValue >= 0 && enumValue < values.length ? values[enumValue] : null;
		}
	}

	/*
	 * --------------------------------------------------------------------- Methods for Attribute CounterTypes
	 * ---------------------------------------------------------------------
	 */
	/**
	 * (5) set attribute CounterTypes
	 *
	 * @param enumVar : the enumVar to set the attribute to
	 */
	public void setCounterTypes(final EnumCounterType enumVar)
	{
		setAttribute(AttributeName.COUNTERTYPES, JavaEnumUtil.getName(enumVar), null);
	}

	/**
	 * (5) set attribute CounterTypes
	 *
	 * @param vVar vector of EnumCounterType : the enumVar to set the attribute to
	 */
	public void setCounterTypes(final Vector<EnumCounterType> vVar)
	{
		final String s = StringUtil.setvString(vVar);
		setAttribute(AttributeName.COUNTERTYPES, s, null);
	}

	/**
	 * append a countertype to attribute CounterTypes
	 *
	 * @param cType EnumCounterType : the enumVar to set the attribute to
	 */
	public void appendCounterType(final EnumCounterType cType)
	{
		if (cType != null)
		{
			appendAttribute(AttributeName.COUNTERTYPES, cType.name(), null, null, true);
		}
	}

	/**
	 * (9) get attribute Scope
	 *
	 * @return the value of the attribute
	 */
	public Vector<EnumCounterType> getEnumCounterTypes()
	{
		final List<EnumCounterType> counterTypes = getEnumerationsAttribute(AttributeName.COUNTERTYPES, null, EnumCounterType.class);
		return counterTypes == null ? null : new Vector<>(counterTypes);
	}

	/**
	 * also checks for matching counterID or CounteType mangled with "_", e.g. UsageCounter:ID_CounterA UsageCounter:Black_SingleSided etc.
	 */
	@Override
	public boolean matchesString(final String namedResLink)
	{
		if (namedResLink == null)
		{
			return false;
		}
		final String counterID = getCounterID();
		if (!isWildCard(counterID))
		{
			final String idString = getLocalName() + ":" + counterID;
			if (idString.equals(namedResLink))
			{
				return true;
			}
		}
		final String ct = StringUtil.setvString(getCounterTypes(), "_", null, null);
		if (!isWildCard(ct) && namedResLink.equals(getLocalName() + ":" + ct))
		{
			return true;
		}
		return super.matchesString(namedResLink);
	}

}
