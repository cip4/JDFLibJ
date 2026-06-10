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
import org.cip4.jdflib.core.JDFCoreConstants;
import org.cip4.jdflib.core.JDFElement;
import org.cip4.jdflib.datatypes.JDFNumberList;
import org.cip4.jdflib.util.JavaEnumUtil;

/**
 ***************************************************************************** class JDFAutoLongGlue : public JDFElement
 */

public abstract class JDFAutoLongGlue extends JDFElement
{

	private static final long serialVersionUID = 1L;

	private static AtrInfoTable[] atrInfoTable = new AtrInfoTable[6];
	static
	{
		atrInfoTable[0] = new AtrInfoTable(AttributeName.GLUEBRAND, 0x4444444443l, AttributeInfo.EnumAttributeType.string, null, null);
		atrInfoTable[1] = new AtrInfoTable(AttributeName.GLUETYPE, 0x4444444443l, AttributeInfo.EnumAttributeType.enumeration,
				JavaEnumUtil.getEnum(EnumGlueType.class, 0), null);
		atrInfoTable[2] = new AtrInfoTable(AttributeName.LINEWIDTH, 0x4444444443l, AttributeInfo.EnumAttributeType.double_, null, null);
		atrInfoTable[3] = new AtrInfoTable(AttributeName.MELTINGTEMPERATURE, 0x4444444443l, AttributeInfo.EnumAttributeType.integer, null, null);
		atrInfoTable[4] = new AtrInfoTable(AttributeName.WORKINGLIST, 0x4444444443l, AttributeInfo.EnumAttributeType.string, null, "0 1000000000");
		atrInfoTable[5] = new AtrInfoTable(AttributeName.XOFFSET, 0x4444444442l, AttributeInfo.EnumAttributeType.double_, null, null);
	}

	@Override
	protected AttributeInfo getTheAttributeInfo()
	{
		return super.getTheAttributeInfo().updateReplace(atrInfoTable);
	}

	/**
	 * Constructor for JDFAutoLongGlue
	 *
	 * @param myOwnerDocument
	 * @param qualifiedName
	 */
	protected JDFAutoLongGlue(final CoreDocumentImpl myOwnerDocument, final String qualifiedName)
	{
		super(myOwnerDocument, qualifiedName);
	}

	/**
	 * Constructor for JDFAutoLongGlue
	 *
	 * @param myOwnerDocument
	 * @param myNamespaceURI
	 * @param qualifiedName
	 */
	protected JDFAutoLongGlue(final CoreDocumentImpl myOwnerDocument, final String myNamespaceURI, final String qualifiedName)
	{
		super(myOwnerDocument, myNamespaceURI, qualifiedName);
	}

	/**
	 * Constructor for JDFAutoLongGlue
	 *
	 * @param myOwnerDocument
	 * @param myNamespaceURI
	 * @param qualifiedName
	 * @param myLocalName
	 */
	protected JDFAutoLongGlue(final CoreDocumentImpl myOwnerDocument, final String myNamespaceURI, final String qualifiedName, final String myLocalName)
	{
		super(myOwnerDocument, myNamespaceURI, qualifiedName, myLocalName);
	}

	/**
	 * Enumeration strings for numGlueType
	 */

	public enum EnumGlueType
	{
		ColdGlue, Hotmelt, PUR;

		public static EnumGlueType getEnum(final String val)
		{
			return JavaEnumUtil.getEnumIgnoreCase(EnumGlueType.class, val, null);
		}
	}/*
		 * ************************************************************************
		 * Attribute getter / setter
		 * ************************************************************************
		 */

	/*
	 * ---------------------------------------------------------------------
	 * Methods for Attribute GlueBrand
	 * ---------------------------------------------------------------------
	 */
	/**
	 * (36) set attribute GlueBrand
	 *
	 * @param value the value to set the attribute to
	 */
	public void setGlueBrand(final String value)
	{
		setAttribute(AttributeName.GLUEBRAND, value, null);
	}

	/**
	 * (23) get String attribute GlueBrand
	 *
	 * @return the value of the attribute
	 */
	public String getGlueBrand()
	{
		return getAttribute(AttributeName.GLUEBRAND, null, JDFCoreConstants.EMPTYSTRING);
	}

	/*
	 * ---------------------------------------------------------------------
	 * Methods for Attribute GlueType
	 * ---------------------------------------------------------------------
	 */
	/**
	 * (5) set attribute GlueType
	 *
	 * @param enumVar the enumVar to set the attribute to
	 */
	public void setGlueType(final EnumGlueType enumVar)
	{
		setAttribute(AttributeName.GLUETYPE, JavaEnumUtil.getName(enumVar), null);
	}

	/**
	 * (9) get attribute GlueType
	 *
	 * @return the value of the attribute
	 */
	public EnumGlueType getGlueType()
	{
		return EnumGlueType.getEnum(getAttribute(AttributeName.GLUETYPE, null, null));
	}

	/*
	 * ---------------------------------------------------------------------
	 * Methods for Attribute LineWidth
	 * ---------------------------------------------------------------------
	 */
	/**
	 * (36) set attribute LineWidth
	 *
	 * @param value the value to set the attribute to
	 */
	public void setLineWidth(final double value)
	{
		setAttribute(AttributeName.LINEWIDTH, value, null);
	}

	/**
	 * (17) get double attribute LineWidth
	 *
	 * @return double the value of the attribute
	 */
	public double getLineWidth()
	{
		return getRealAttribute(AttributeName.LINEWIDTH, null, 0.0);
	}

	/*
	 * ---------------------------------------------------------------------
	 * Methods for Attribute MeltingTemperature
	 * ---------------------------------------------------------------------
	 */
	/**
	 * (36) set attribute MeltingTemperature
	 *
	 * @param value the value to set the attribute to
	 */
	public void setMeltingTemperature(final int value)
	{
		setAttribute(AttributeName.MELTINGTEMPERATURE, value, null);
	}

	/**
	 * (15) get int attribute MeltingTemperature
	 *
	 * @return int the value of the attribute
	 */
	public int getMeltingTemperature()
	{
		return getIntAttribute(AttributeName.MELTINGTEMPERATURE, null, 0);
	}

	/*
	 * ---------------------------------------------------------------------
	 * Methods for Attribute WorkingList
	 * ---------------------------------------------------------------------
	 */
	/**
	 * (36) set attribute WorkingList
	 *
	 * @param value the value to set the attribute to
	 */
	public void setWorkingList(final JDFNumberList value)
	{
		setAttribute(AttributeName.WORKINGLIST, value, null);
	}

	/**
	 * (20) get JDFNumberList attribute WorkingList
	 *
	 * @return JDFNumberList the value of the attribute, null if a the
	 *         attribute value is not a valid to create a JDFNumberList
	 */
	public JDFNumberList getWorkingList()
	{
		final String strAttrName = getAttribute(AttributeName.WORKINGLIST, null, null);
		final JDFNumberList nPlaceHolder = JDFNumberList.createNumberList(strAttrName);
		return nPlaceHolder;
	}

	/*
	 * ---------------------------------------------------------------------
	 * Methods for Attribute XOffset
	 * ---------------------------------------------------------------------
	 */
	/**
	 * (36) set attribute XOffset
	 *
	 * @param value the value to set the attribute to
	 */
	public void setXOffset(final double value)
	{
		setAttribute(AttributeName.XOFFSET, value, null);
	}

	/**
	 * (17) get double attribute XOffset
	 *
	 * @return double the value of the attribute
	 */
	public double getXOffset()
	{
		return getRealAttribute(AttributeName.XOFFSET, null, 0.0);
	}

}
