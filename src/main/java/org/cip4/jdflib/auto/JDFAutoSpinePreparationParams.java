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

import org.apache.xerces.dom.CoreDocumentImpl;
import org.cip4.jdflib.core.AtrInfoTable;
import org.cip4.jdflib.core.AttributeInfo;
import org.cip4.jdflib.core.AttributeName;
import org.cip4.jdflib.core.JDFCoreConstants;
import org.cip4.jdflib.core.VString;
import org.cip4.jdflib.resource.JDFResource;

/**
 *****************************************************************************
 * class JDFAutoSpinePreparationParams : public JDFResource
 *****************************************************************************
 * 
 */

public abstract class JDFAutoSpinePreparationParams extends JDFResource
{

	private static final long serialVersionUID = 1L;

	private static AtrInfoTable[] atrInfoTable = new AtrInfoTable[9];
	static
	{
		atrInfoTable[0] = new AtrInfoTable(AttributeName.STARTPOSITION, 0x3333333331l, AttributeInfo.EnumAttributeType.double_, null, "0");
		atrInfoTable[1] = new AtrInfoTable(AttributeName.FLEXVALUE, 0x4444444431l, AttributeInfo.EnumAttributeType.double_, null, null);
		atrInfoTable[2] = new AtrInfoTable(AttributeName.MILLINGDEPTH, 0x3333333331l, AttributeInfo.EnumAttributeType.double_, null, null);
		atrInfoTable[3] = new AtrInfoTable(AttributeName.NOTCHINGDEPTH, 0x3333333331l, AttributeInfo.EnumAttributeType.double_, null, null);
		atrInfoTable[4] = new AtrInfoTable(AttributeName.NOTCHINGDISTANCE, 0x3333333331l, AttributeInfo.EnumAttributeType.double_, null, null);
		atrInfoTable[5] = new AtrInfoTable(AttributeName.OPERATIONS, 0x3333333331l, AttributeInfo.EnumAttributeType.NMTOKENS, null, null);
		atrInfoTable[6] = new AtrInfoTable(AttributeName.PULLOUTVALUE, 0x4444444431l, AttributeInfo.EnumAttributeType.double_, null, null);
		atrInfoTable[7] = new AtrInfoTable(AttributeName.SEALINGTEMPERATURE, 0x3333111111l, AttributeInfo.EnumAttributeType.integer, null, null);
		atrInfoTable[8] = new AtrInfoTable(AttributeName.WORKINGLENGTH, 0x3333333331l, AttributeInfo.EnumAttributeType.double_, null, null);
	}

	@Override
	protected AttributeInfo getTheAttributeInfo()
	{
		return super.getTheAttributeInfo().updateReplace(atrInfoTable);
	}

	/**
	 * Constructor for JDFAutoSpinePreparationParams
	 *
	 * @param myOwnerDocument
	 * @param qualifiedName
	 */
	protected JDFAutoSpinePreparationParams(CoreDocumentImpl myOwnerDocument, String qualifiedName)
	{
		super(myOwnerDocument, qualifiedName);
	}

	/**
	 * Constructor for JDFAutoSpinePreparationParams
	 *
	 * @param myOwnerDocument
	 * @param myNamespaceURI
	 * @param qualifiedName
	 */
	protected JDFAutoSpinePreparationParams(CoreDocumentImpl myOwnerDocument, String myNamespaceURI, String qualifiedName)
	{
		super(myOwnerDocument, myNamespaceURI, qualifiedName);
	}

	/**
	 * Constructor for JDFAutoSpinePreparationParams
	 *
	 * @param myOwnerDocument
	 * @param myNamespaceURI
	 * @param qualifiedName
	 * @param myLocalName
	 */
	protected JDFAutoSpinePreparationParams(CoreDocumentImpl myOwnerDocument, String myNamespaceURI, String qualifiedName, String myLocalName)
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

	/*
	 * ************************************************************************ Attribute getter / setter ************************************************************************
	 */

	/*
	 * --------------------------------------------------------------------- Methods for Attribute StartPosition
	 * ---------------------------------------------------------------------
	 */
	/**
	 * (36) set attribute StartPosition
	 *
	 * @param value the value to set the attribute to
	 */
	public void setStartPosition(double value)
	{
		setAttribute(AttributeName.STARTPOSITION, value, null);
	}

	/**
	 * (17) get double attribute StartPosition
	 *
	 * @return double the value of the attribute
	 */
	public double getStartPosition()
	{
		return getRealAttribute(AttributeName.STARTPOSITION, null, 0.0);
	}

	/*
	 * --------------------------------------------------------------------- Methods for Attribute FlexValue ---------------------------------------------------------------------
	 */
	/**
	 * (36) set attribute FlexValue
	 *
	 * @param value the value to set the attribute to
	 */
	public void setFlexValue(double value)
	{
		setAttribute(AttributeName.FLEXVALUE, value, null);
	}

	/**
	 * (17) get double attribute FlexValue
	 *
	 * @return double the value of the attribute
	 */
	public double getFlexValue()
	{
		return getRealAttribute(AttributeName.FLEXVALUE, null, 0.0);
	}

	/*
	 * --------------------------------------------------------------------- Methods for Attribute MillingDepth
	 * ---------------------------------------------------------------------
	 */
	/**
	 * (36) set attribute MillingDepth
	 *
	 * @param value the value to set the attribute to
	 */
	public void setMillingDepth(double value)
	{
		setAttribute(AttributeName.MILLINGDEPTH, value, null);
	}

	/**
	 * (17) get double attribute MillingDepth
	 *
	 * @return double the value of the attribute
	 */
	public double getMillingDepth()
	{
		return getRealAttribute(AttributeName.MILLINGDEPTH, null, 0.0);
	}

	/*
	 * --------------------------------------------------------------------- Methods for Attribute NotchingDepth
	 * ---------------------------------------------------------------------
	 */
	/**
	 * (36) set attribute NotchingDepth
	 *
	 * @param value the value to set the attribute to
	 */
	public void setNotchingDepth(double value)
	{
		setAttribute(AttributeName.NOTCHINGDEPTH, value, null);
	}

	/**
	 * (17) get double attribute NotchingDepth
	 *
	 * @return double the value of the attribute
	 */
	public double getNotchingDepth()
	{
		return getRealAttribute(AttributeName.NOTCHINGDEPTH, null, 0.0);
	}

	/*
	 * --------------------------------------------------------------------- Methods for Attribute NotchingDistance
	 * ---------------------------------------------------------------------
	 */
	/**
	 * (36) set attribute NotchingDistance
	 *
	 * @param value the value to set the attribute to
	 */
	public void setNotchingDistance(double value)
	{
		setAttribute(AttributeName.NOTCHINGDISTANCE, value, null);
	}

	/**
	 * (17) get double attribute NotchingDistance
	 *
	 * @return double the value of the attribute
	 */
	public double getNotchingDistance()
	{
		return getRealAttribute(AttributeName.NOTCHINGDISTANCE, null, 0.0);
	}

	/*
	 * --------------------------------------------------------------------- Methods for Attribute Operations ---------------------------------------------------------------------
	 */
	/**
	 * (36) set attribute Operations
	 *
	 * @param value the value to set the attribute to
	 */
	public void setOperations(VString value)
	{
		setAttribute(AttributeName.OPERATIONS, value, null);
	}

	/**
	 * (21) get VString attribute Operations
	 *
	 * @return VString the value of the attribute
	 */
	public VString getOperations()
	{
		final VString vStrAttrib = new VString();
		final String s = getAttribute(AttributeName.OPERATIONS, null, JDFCoreConstants.EMPTYSTRING);
		vStrAttrib.setAllStrings(s, " ");
		return vStrAttrib;
	}

	/*
	 * --------------------------------------------------------------------- Methods for Attribute PullOutValue
	 * ---------------------------------------------------------------------
	 */
	/**
	 * (36) set attribute PullOutValue
	 *
	 * @param value the value to set the attribute to
	 */
	public void setPullOutValue(double value)
	{
		setAttribute(AttributeName.PULLOUTVALUE, value, null);
	}

	/**
	 * (17) get double attribute PullOutValue
	 *
	 * @return double the value of the attribute
	 */
	public double getPullOutValue()
	{
		return getRealAttribute(AttributeName.PULLOUTVALUE, null, 0.0);
	}

	/*
	 * --------------------------------------------------------------------- Methods for Attribute SealingTemperature
	 * ---------------------------------------------------------------------
	 */
	/**
	 * (36) set attribute SealingTemperature
	 *
	 * @param value the value to set the attribute to
	 */
	public void setSealingTemperature(int value)
	{
		setAttribute(AttributeName.SEALINGTEMPERATURE, value, null);
	}

	/**
	 * (15) get int attribute SealingTemperature
	 *
	 * @return int the value of the attribute
	 */
	public int getSealingTemperature()
	{
		return getIntAttribute(AttributeName.SEALINGTEMPERATURE, null, 0);
	}

	/*
	 * --------------------------------------------------------------------- Methods for Attribute WorkingLength
	 * ---------------------------------------------------------------------
	 */
	/**
	 * (36) set attribute WorkingLength
	 *
	 * @param value the value to set the attribute to
	 */
	public void setWorkingLength(double value)
	{
		setAttribute(AttributeName.WORKINGLENGTH, value, null);
	}

	/**
	 * (17) get double attribute WorkingLength
	 *
	 * @return double the value of the attribute
	 */
	public double getWorkingLength()
	{
		return getRealAttribute(AttributeName.WORKINGLENGTH, null, 0.0);
	}

}
