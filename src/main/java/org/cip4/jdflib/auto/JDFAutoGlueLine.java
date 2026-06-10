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
import org.cip4.jdflib.datatypes.JDFXYPair;
import org.cip4.jdflib.util.JavaEnumUtil;

/**
 ***************************************************************************** class JDFAutoGlueLine : public JDFElement
 */

public abstract class JDFAutoGlueLine extends JDFElement
{

	private static final long serialVersionUID = 1L;

	private static AtrInfoTable[] atrInfoTable = new AtrInfoTable[10];
	static
	{
		atrInfoTable[0] = new AtrInfoTable(AttributeName.AREAGLUE, 0x3333333333l, AttributeInfo.EnumAttributeType.boolean_, null, "false");
		atrInfoTable[1] = new AtrInfoTable(AttributeName.GLUEBRAND, 0x3333333333l, AttributeInfo.EnumAttributeType.string, null, null);
		atrInfoTable[2] = new AtrInfoTable(AttributeName.GLUELINEWIDTH, 0x3333333333l, AttributeInfo.EnumAttributeType.double_, null, null);
		atrInfoTable[3] = new AtrInfoTable(AttributeName.GLUETYPE, 0x3333333333l, AttributeInfo.EnumAttributeType.enumeration,
				JavaEnumUtil.getEnum(EnumGlueType.class, 0), null);
		atrInfoTable[4] = new AtrInfoTable(AttributeName.GLUINGPATTERN, 0x3333333333l, AttributeInfo.EnumAttributeType.XYPair, null, null);
		atrInfoTable[5] = new AtrInfoTable(AttributeName.MELTINGTEMPERATURE, 0x3333333333l, AttributeInfo.EnumAttributeType.integer, null, null);
		atrInfoTable[6] = new AtrInfoTable(AttributeName.RELATIVESTARTPOSITION, 0x3333333333l, AttributeInfo.EnumAttributeType.XYPair, null, null);
		atrInfoTable[7] = new AtrInfoTable(AttributeName.RELATIVEWORKINGPATH, 0x3333333333l, AttributeInfo.EnumAttributeType.XYPair, null, null);
		atrInfoTable[8] = new AtrInfoTable(AttributeName.STARTPOSITION, 0x3333333333l, AttributeInfo.EnumAttributeType.XYPair, null, null);
		atrInfoTable[9] = new AtrInfoTable(AttributeName.WORKINGPATH, 0x3333333333l, AttributeInfo.EnumAttributeType.XYPair, null, null);
	}

	@Override
	protected AttributeInfo getTheAttributeInfo()
	{
		return super.getTheAttributeInfo().updateReplace(atrInfoTable);
	}

	/**
	 * Constructor for JDFAutoGlueLine
	 *
	 * @param myOwnerDocument
	 * @param qualifiedName
	 */
	protected JDFAutoGlueLine(final CoreDocumentImpl myOwnerDocument, final String qualifiedName)
	{
		super(myOwnerDocument, qualifiedName);
	}

	/**
	 * Constructor for JDFAutoGlueLine
	 *
	 * @param myOwnerDocument
	 * @param myNamespaceURI
	 * @param qualifiedName
	 */
	protected JDFAutoGlueLine(final CoreDocumentImpl myOwnerDocument, final String myNamespaceURI, final String qualifiedName)
	{
		super(myOwnerDocument, myNamespaceURI, qualifiedName);
	}

	/**
	 * Constructor for JDFAutoGlueLine
	 *
	 * @param myOwnerDocument
	 * @param myNamespaceURI
	 * @param qualifiedName
	 * @param myLocalName
	 */
	protected JDFAutoGlueLine(final CoreDocumentImpl myOwnerDocument, final String myNamespaceURI, final String qualifiedName, final String myLocalName)
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
	 * Methods for Attribute AreaGlue
	 * ---------------------------------------------------------------------
	 */
	/**
	 * (36) set attribute AreaGlue
	 *
	 * @param value the value to set the attribute to
	 */
	public void setAreaGlue(final boolean value)
	{
		setAttribute(AttributeName.AREAGLUE, value, null);
	}

	/**
	 * (18) get boolean attribute AreaGlue
	 *
	 * @return boolean the value of the attribute
	 */
	public boolean getAreaGlue()
	{
		return getBoolAttribute(AttributeName.AREAGLUE, null, false);
	}

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
	 * Methods for Attribute GlueLineWidth
	 * ---------------------------------------------------------------------
	 */
	/**
	 * (36) set attribute GlueLineWidth
	 *
	 * @param value the value to set the attribute to
	 */
	public void setGlueLineWidth(final double value)
	{
		setAttribute(AttributeName.GLUELINEWIDTH, value, null);
	}

	/**
	 * (17) get double attribute GlueLineWidth
	 *
	 * @return double the value of the attribute
	 */
	public double getGlueLineWidth()
	{
		return getRealAttribute(AttributeName.GLUELINEWIDTH, null, 0.0);
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
	 * Methods for Attribute GluingPattern
	 * ---------------------------------------------------------------------
	 */
	/**
	 * (36) set attribute GluingPattern
	 *
	 * @param value the value to set the attribute to
	 */
	public void setGluingPattern(final JDFXYPair value)
	{
		setAttribute(AttributeName.GLUINGPATTERN, value, null);
	}

	/**
	 * (20) get JDFXYPair attribute GluingPattern
	 *
	 * @return JDFXYPair the value of the attribute, null if a the
	 *         attribute value is not a valid to create a JDFXYPair
	 */
	public JDFXYPair getGluingPattern()
	{
		final String strAttrName = getAttribute(AttributeName.GLUINGPATTERN, null, null);
		final JDFXYPair nPlaceHolder = JDFXYPair.createXYPair(strAttrName);
		return nPlaceHolder;
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
	 * Methods for Attribute RelativeStartPosition
	 * ---------------------------------------------------------------------
	 */
	/**
	 * (36) set attribute RelativeStartPosition
	 *
	 * @param value the value to set the attribute to
	 */
	public void setRelativeStartPosition(final JDFXYPair value)
	{
		setAttribute(AttributeName.RELATIVESTARTPOSITION, value, null);
	}

	/**
	 * (20) get JDFXYPair attribute RelativeStartPosition
	 *
	 * @return JDFXYPair the value of the attribute, null if a the
	 *         attribute value is not a valid to create a JDFXYPair
	 */
	public JDFXYPair getRelativeStartPosition()
	{
		final String strAttrName = getAttribute(AttributeName.RELATIVESTARTPOSITION, null, null);
		final JDFXYPair nPlaceHolder = JDFXYPair.createXYPair(strAttrName);
		return nPlaceHolder;
	}

	/*
	 * ---------------------------------------------------------------------
	 * Methods for Attribute RelativeWorkingPath
	 * ---------------------------------------------------------------------
	 */
	/**
	 * (36) set attribute RelativeWorkingPath
	 *
	 * @param value the value to set the attribute to
	 */
	public void setRelativeWorkingPath(final JDFXYPair value)
	{
		setAttribute(AttributeName.RELATIVEWORKINGPATH, value, null);
	}

	/**
	 * (20) get JDFXYPair attribute RelativeWorkingPath
	 *
	 * @return JDFXYPair the value of the attribute, null if a the
	 *         attribute value is not a valid to create a JDFXYPair
	 */
	public JDFXYPair getRelativeWorkingPath()
	{
		final String strAttrName = getAttribute(AttributeName.RELATIVEWORKINGPATH, null, null);
		final JDFXYPair nPlaceHolder = JDFXYPair.createXYPair(strAttrName);
		return nPlaceHolder;
	}

	/*
	 * ---------------------------------------------------------------------
	 * Methods for Attribute StartPosition
	 * ---------------------------------------------------------------------
	 */
	/**
	 * (36) set attribute StartPosition
	 *
	 * @param value the value to set the attribute to
	 */
	public void setStartPosition(final JDFXYPair value)
	{
		setAttribute(AttributeName.STARTPOSITION, value, null);
	}

	/**
	 * (20) get JDFXYPair attribute StartPosition
	 *
	 * @return JDFXYPair the value of the attribute, null if a the
	 *         attribute value is not a valid to create a JDFXYPair
	 */
	public JDFXYPair getStartPosition()
	{
		final String strAttrName = getAttribute(AttributeName.STARTPOSITION, null, null);
		final JDFXYPair nPlaceHolder = JDFXYPair.createXYPair(strAttrName);
		return nPlaceHolder;
	}

	/*
	 * ---------------------------------------------------------------------
	 * Methods for Attribute WorkingPath
	 * ---------------------------------------------------------------------
	 */
	/**
	 * (36) set attribute WorkingPath
	 *
	 * @param value the value to set the attribute to
	 */
	public void setWorkingPath(final JDFXYPair value)
	{
		setAttribute(AttributeName.WORKINGPATH, value, null);
	}

	/**
	 * (20) get JDFXYPair attribute WorkingPath
	 *
	 * @return JDFXYPair the value of the attribute, null if a the
	 *         attribute value is not a valid to create a JDFXYPair
	 */
	public JDFXYPair getWorkingPath()
	{
		final String strAttrName = getAttribute(AttributeName.WORKINGPATH, null, null);
		final JDFXYPair nPlaceHolder = JDFXYPair.createXYPair(strAttrName);
		return nPlaceHolder;
	}

}
