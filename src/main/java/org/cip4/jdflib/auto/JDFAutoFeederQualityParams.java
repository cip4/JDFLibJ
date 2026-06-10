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

import org.apache.xerces.dom.CoreDocumentImpl;
import org.cip4.jdflib.core.AtrInfoTable;
import org.cip4.jdflib.core.AttributeInfo;
import org.cip4.jdflib.core.AttributeName;
import org.cip4.jdflib.core.JDFElement;
import org.cip4.jdflib.util.JavaEnumUtil;

/**
 ***************************************************************************** class JDFAutoFeederQualityParams : public JDFElement
 */

public abstract class JDFAutoFeederQualityParams extends JDFElement
{

	private static final long serialVersionUID = 1L;

	private static AtrInfoTable[] atrInfoTable = new AtrInfoTable[6];
	static
	{
		atrInfoTable[0] = new AtrInfoTable(AttributeName.INCORRECTCOMPONENTQUALITY, 0x3333333311l, AttributeInfo.EnumAttributeType.enumeration,
				JavaEnumUtil.getEnum(EnumIncorrectComponentQuality.class, 0), null);
		atrInfoTable[1] = new AtrInfoTable(AttributeName.INCORRECTCOMPONENTS, 0x3333333311l, AttributeInfo.EnumAttributeType.integer, null, null);
		atrInfoTable[2] = new AtrInfoTable(AttributeName.DOUBLEFEEDQUALITY, 0x3333333311l, AttributeInfo.EnumAttributeType.enumeration,
				JavaEnumUtil.getEnum(EnumDoubleFeedQuality.class, 0), null);
		atrInfoTable[3] = new AtrInfoTable(AttributeName.DOUBLEFEEDS, 0x3333333311l, AttributeInfo.EnumAttributeType.integer, null, null);
		atrInfoTable[4] = new AtrInfoTable(AttributeName.BADFEEDQUALITY, 0x3333333311l, AttributeInfo.EnumAttributeType.enumeration,
				JavaEnumUtil.getEnum(EnumBadFeedQuality.class, 0), null);
		atrInfoTable[5] = new AtrInfoTable(AttributeName.BADFEEDS, 0x3333333311l, AttributeInfo.EnumAttributeType.integer, null, null);
	}

	@Override
	protected AttributeInfo getTheAttributeInfo()
	{
		return super.getTheAttributeInfo().updateReplace(atrInfoTable);
	}

	/**
	 * Constructor for JDFAutoFeederQualityParams
	 *
	 * @param myOwnerDocument
	 * @param qualifiedName
	 */
	protected JDFAutoFeederQualityParams(final CoreDocumentImpl myOwnerDocument, final String qualifiedName)
	{
		super(myOwnerDocument, qualifiedName);
	}

	/**
	 * Constructor for JDFAutoFeederQualityParams
	 *
	 * @param myOwnerDocument
	 * @param myNamespaceURI
	 * @param qualifiedName
	 */
	protected JDFAutoFeederQualityParams(final CoreDocumentImpl myOwnerDocument, final String myNamespaceURI, final String qualifiedName)
	{
		super(myOwnerDocument, myNamespaceURI, qualifiedName);
	}

	/**
	 * Constructor for JDFAutoFeederQualityParams
	 *
	 * @param myOwnerDocument
	 * @param myNamespaceURI
	 * @param qualifiedName
	 * @param myLocalName
	 */
	protected JDFAutoFeederQualityParams(final CoreDocumentImpl myOwnerDocument, final String myNamespaceURI, final String qualifiedName, final String myLocalName)
	{
		super(myOwnerDocument, myNamespaceURI, qualifiedName, myLocalName);
	}

	/**
	 * Enumeration strings for numIncorrectComponentQuality
	 */

	public enum EnumIncorrectComponentQuality
	{
		NotActive, Check, Waste, StopNoWaste, StopWaste;

		public static EnumIncorrectComponentQuality getEnum(final String val)
		{
			return JavaEnumUtil.getEnumIgnoreCase(EnumIncorrectComponentQuality.class, val, null);
		}
	}

	/**
	 * Enumeration strings for numDoubleFeedQuality
	 */

	public enum EnumDoubleFeedQuality
	{
		NotActive, Check, Waste, StopNoWaste, StopWaste;

		public static EnumDoubleFeedQuality getEnum(final String val)
		{
			return JavaEnumUtil.getEnumIgnoreCase(EnumDoubleFeedQuality.class, val, null);
		}
	}

	/**
	 * Enumeration strings for numBadFeedQuality
	 */

	public enum EnumBadFeedQuality
	{
		NotActive, Check, Waste, StopNoWaste, StopWaste;

		public static EnumBadFeedQuality getEnum(final String val)
		{
			return JavaEnumUtil.getEnumIgnoreCase(EnumBadFeedQuality.class, val, null);
		}
	}/*
		 * ************************************************************************
		 * Attribute getter / setter
		 * ************************************************************************
		 */

	/*
	 * ---------------------------------------------------------------------
	 * Methods for Attribute IncorrectComponentQuality
	 * ---------------------------------------------------------------------
	 */
	/**
	 * (5) set attribute IncorrectComponentQuality
	 *
	 * @param enumVar the enumVar to set the attribute to
	 */
	public void setIncorrectComponentQuality(final EnumIncorrectComponentQuality enumVar)
	{
		setAttribute(AttributeName.INCORRECTCOMPONENTQUALITY, JavaEnumUtil.getName(enumVar), null);
	}

	/**
	 * (9) get attribute IncorrectComponentQuality
	 *
	 * @return the value of the attribute
	 */
	public EnumIncorrectComponentQuality getIncorrectComponentQuality()
	{
		return EnumIncorrectComponentQuality.getEnum(getAttribute(AttributeName.INCORRECTCOMPONENTQUALITY, null, null));
	}

	/*
	 * ---------------------------------------------------------------------
	 * Methods for Attribute IncorrectComponents
	 * ---------------------------------------------------------------------
	 */
	/**
	 * (36) set attribute IncorrectComponents
	 *
	 * @param value the value to set the attribute to
	 */
	public void setIncorrectComponents(final int value)
	{
		setAttribute(AttributeName.INCORRECTCOMPONENTS, value, null);
	}

	/**
	 * (15) get int attribute IncorrectComponents
	 *
	 * @return int the value of the attribute
	 */
	public int getIncorrectComponents()
	{
		return getIntAttribute(AttributeName.INCORRECTCOMPONENTS, null, 0);
	}

	/*
	 * ---------------------------------------------------------------------
	 * Methods for Attribute DoubleFeedQuality
	 * ---------------------------------------------------------------------
	 */
	/**
	 * (5) set attribute DoubleFeedQuality
	 *
	 * @param enumVar the enumVar to set the attribute to
	 */
	public void setDoubleFeedQuality(final EnumDoubleFeedQuality enumVar)
	{
		setAttribute(AttributeName.DOUBLEFEEDQUALITY, JavaEnumUtil.getName(enumVar), null);
	}

	/**
	 * (9) get attribute DoubleFeedQuality
	 *
	 * @return the value of the attribute
	 */
	public EnumDoubleFeedQuality getDoubleFeedQuality()
	{
		return EnumDoubleFeedQuality.getEnum(getAttribute(AttributeName.DOUBLEFEEDQUALITY, null, null));
	}

	/*
	 * ---------------------------------------------------------------------
	 * Methods for Attribute DoubleFeeds
	 * ---------------------------------------------------------------------
	 */
	/**
	 * (36) set attribute DoubleFeeds
	 *
	 * @param value the value to set the attribute to
	 */
	public void setDoubleFeeds(final int value)
	{
		setAttribute(AttributeName.DOUBLEFEEDS, value, null);
	}

	/**
	 * (15) get int attribute DoubleFeeds
	 *
	 * @return int the value of the attribute
	 */
	public int getDoubleFeeds()
	{
		return getIntAttribute(AttributeName.DOUBLEFEEDS, null, 0);
	}

	/*
	 * ---------------------------------------------------------------------
	 * Methods for Attribute BadFeedQuality
	 * ---------------------------------------------------------------------
	 */
	/**
	 * (5) set attribute BadFeedQuality
	 *
	 * @param enumVar the enumVar to set the attribute to
	 */
	public void setBadFeedQuality(final EnumBadFeedQuality enumVar)
	{
		setAttribute(AttributeName.BADFEEDQUALITY, JavaEnumUtil.getName(enumVar), null);
	}

	/**
	 * (9) get attribute BadFeedQuality
	 *
	 * @return the value of the attribute
	 */
	public EnumBadFeedQuality getBadFeedQuality()
	{
		return EnumBadFeedQuality.getEnum(getAttribute(AttributeName.BADFEEDQUALITY, null, null));
	}

	/*
	 * ---------------------------------------------------------------------
	 * Methods for Attribute BadFeeds
	 * ---------------------------------------------------------------------
	 */
	/**
	 * (36) set attribute BadFeeds
	 *
	 * @param value the value to set the attribute to
	 */
	public void setBadFeeds(final int value)
	{
		setAttribute(AttributeName.BADFEEDS, value, null);
	}

	/**
	 * (15) get int attribute BadFeeds
	 *
	 * @return int the value of the attribute
	 */
	public int getBadFeeds()
	{
		return getIntAttribute(AttributeName.BADFEEDS, null, 0);
	}

}
