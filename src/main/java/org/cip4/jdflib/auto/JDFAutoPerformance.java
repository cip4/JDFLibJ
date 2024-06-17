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
import org.cip4.jdflib.core.JDFElement;
import org.cip4.jdflib.util.JDFDuration;

/**
 *****************************************************************************
 * class JDFAutoPerformance : public JDFElement
 *****************************************************************************
 * 
 */

public abstract class JDFAutoPerformance extends JDFElement
{

	private static final long serialVersionUID = 1L;

	private static AtrInfoTable[] atrInfoTable = new AtrInfoTable[12];
	static
	{
		atrInfoTable[0] = new AtrInfoTable(AttributeName.AVERAGEAMOUNT, 0x3333333331l, AttributeInfo.EnumAttributeType.double_, null, null);
		atrInfoTable[1] = new AtrInfoTable(AttributeName.AVERAGECLEANUP, 0x3333333331l, AttributeInfo.EnumAttributeType.duration, null, null);
		atrInfoTable[2] = new AtrInfoTable(AttributeName.AVERAGESETUP, 0x3333333331l, AttributeInfo.EnumAttributeType.duration, null, null);
		atrInfoTable[3] = new AtrInfoTable(AttributeName.MAXAMOUNT, 0x3333333331l, AttributeInfo.EnumAttributeType.double_, null, null);
		atrInfoTable[4] = new AtrInfoTable(AttributeName.MAXCLEANUP, 0x3333333331l, AttributeInfo.EnumAttributeType.duration, null, null);
		atrInfoTable[5] = new AtrInfoTable(AttributeName.MAXSETUP, 0x3333333331l, AttributeInfo.EnumAttributeType.duration, null, null);
		atrInfoTable[6] = new AtrInfoTable(AttributeName.MINAMOUNT, 0x3333333331l, AttributeInfo.EnumAttributeType.double_, null, null);
		atrInfoTable[7] = new AtrInfoTable(AttributeName.MINCLEANUP, 0x3333333331l, AttributeInfo.EnumAttributeType.duration, null, null);
		atrInfoTable[8] = new AtrInfoTable(AttributeName.MINSETUP, 0x3333333331l, AttributeInfo.EnumAttributeType.duration, null, null);
		atrInfoTable[9] = new AtrInfoTable(AttributeName.NAME, 0x4444444431l, AttributeInfo.EnumAttributeType.NMTOKEN, null, null);
		atrInfoTable[10] = new AtrInfoTable(AttributeName.DEVCAPSREF, 0x3333333311l, AttributeInfo.EnumAttributeType.IDREF, null, null);
		atrInfoTable[11] = new AtrInfoTable(AttributeName.UNIT, 0x3333333331l, AttributeInfo.EnumAttributeType.NMTOKEN, null, null);
	}

	@Override
	protected AttributeInfo getTheAttributeInfo()
	{
		return super.getTheAttributeInfo().updateReplace(atrInfoTable);
	}

	/**
	 * Constructor for JDFAutoPerformance
	 *
	 * @param myOwnerDocument
	 * @param qualifiedName
	 */
	protected JDFAutoPerformance(CoreDocumentImpl myOwnerDocument, String qualifiedName)
	{
		super(myOwnerDocument, qualifiedName);
	}

	/**
	 * Constructor for JDFAutoPerformance
	 *
	 * @param myOwnerDocument
	 * @param myNamespaceURI
	 * @param qualifiedName
	 */
	protected JDFAutoPerformance(CoreDocumentImpl myOwnerDocument, String myNamespaceURI, String qualifiedName)
	{
		super(myOwnerDocument, myNamespaceURI, qualifiedName);
	}

	/**
	 * Constructor for JDFAutoPerformance
	 *
	 * @param myOwnerDocument
	 * @param myNamespaceURI
	 * @param qualifiedName
	 * @param myLocalName
	 */
	protected JDFAutoPerformance(CoreDocumentImpl myOwnerDocument, String myNamespaceURI, String qualifiedName, String myLocalName)
	{
		super(myOwnerDocument, myNamespaceURI, qualifiedName, myLocalName);
	}

	/*
	 * ************************************************************************ Attribute getter / setter ************************************************************************
	 */

	/*
	 * --------------------------------------------------------------------- Methods for Attribute AverageAmount
	 * ---------------------------------------------------------------------
	 */
	/**
	 * (36) set attribute AverageAmount
	 *
	 * @param value the value to set the attribute to
	 */
	public void setAverageAmount(double value)
	{
		setAttribute(AttributeName.AVERAGEAMOUNT, value, null);
	}

	/**
	 * (17) get double attribute AverageAmount
	 *
	 * @return double the value of the attribute
	 */
	public double getAverageAmount()
	{
		return getRealAttribute(AttributeName.AVERAGEAMOUNT, null, 0.0);
	}

	/*
	 * --------------------------------------------------------------------- Methods for Attribute AverageCleanup
	 * ---------------------------------------------------------------------
	 */
	/**
	 * (36) set attribute AverageCleanup
	 *
	 * @param value the value to set the attribute to
	 */
	public void setAverageCleanup(JDFDuration value)
	{
		setAttribute(AttributeName.AVERAGECLEANUP, value, null);
	}

	/**
	 * (20) get JDFDuration attribute AverageCleanup
	 *
	 * @return JDFDuration the value of the attribute, null if a the attribute value is not a valid to create a JDFDuration
	 */
	public JDFDuration getAverageCleanup()
	{
		final String strAttrName = getAttribute(AttributeName.AVERAGECLEANUP, null, null);
		final JDFDuration nPlaceHolder = JDFDuration.createDuration(strAttrName);
		return nPlaceHolder;
	}

	/*
	 * --------------------------------------------------------------------- Methods for Attribute AverageSetup
	 * ---------------------------------------------------------------------
	 */
	/**
	 * (36) set attribute AverageSetup
	 *
	 * @param value the value to set the attribute to
	 */
	public void setAverageSetup(JDFDuration value)
	{
		setAttribute(AttributeName.AVERAGESETUP, value, null);
	}

	/**
	 * (20) get JDFDuration attribute AverageSetup
	 *
	 * @return JDFDuration the value of the attribute, null if a the attribute value is not a valid to create a JDFDuration
	 */
	public JDFDuration getAverageSetup()
	{
		final String strAttrName = getAttribute(AttributeName.AVERAGESETUP, null, null);
		final JDFDuration nPlaceHolder = JDFDuration.createDuration(strAttrName);
		return nPlaceHolder;
	}

	/*
	 * --------------------------------------------------------------------- Methods for Attribute MaxAmount ---------------------------------------------------------------------
	 */
	/**
	 * (36) set attribute MaxAmount
	 *
	 * @param value the value to set the attribute to
	 */
	public void setMaxAmount(double value)
	{
		setAttribute(AttributeName.MAXAMOUNT, value, null);
	}

	/**
	 * (17) get double attribute MaxAmount
	 *
	 * @return double the value of the attribute
	 */
	public double getMaxAmount()
	{
		return getRealAttribute(AttributeName.MAXAMOUNT, null, 0.0);
	}

	/*
	 * --------------------------------------------------------------------- Methods for Attribute MaxCleanup ---------------------------------------------------------------------
	 */
	/**
	 * (36) set attribute MaxCleanup
	 *
	 * @param value the value to set the attribute to
	 */
	public void setMaxCleanup(JDFDuration value)
	{
		setAttribute(AttributeName.MAXCLEANUP, value, null);
	}

	/**
	 * (20) get JDFDuration attribute MaxCleanup
	 *
	 * @return JDFDuration the value of the attribute, null if a the attribute value is not a valid to create a JDFDuration
	 */
	public JDFDuration getMaxCleanup()
	{
		final String strAttrName = getAttribute(AttributeName.MAXCLEANUP, null, null);
		final JDFDuration nPlaceHolder = JDFDuration.createDuration(strAttrName);
		return nPlaceHolder;
	}

	/*
	 * --------------------------------------------------------------------- Methods for Attribute MaxSetup ---------------------------------------------------------------------
	 */
	/**
	 * (36) set attribute MaxSetup
	 *
	 * @param value the value to set the attribute to
	 */
	public void setMaxSetup(JDFDuration value)
	{
		setAttribute(AttributeName.MAXSETUP, value, null);
	}

	/**
	 * (20) get JDFDuration attribute MaxSetup
	 *
	 * @return JDFDuration the value of the attribute, null if a the attribute value is not a valid to create a JDFDuration
	 */
	public JDFDuration getMaxSetup()
	{
		final String strAttrName = getAttribute(AttributeName.MAXSETUP, null, null);
		final JDFDuration nPlaceHolder = JDFDuration.createDuration(strAttrName);
		return nPlaceHolder;
	}

	/*
	 * --------------------------------------------------------------------- Methods for Attribute MinAmount ---------------------------------------------------------------------
	 */
	/**
	 * (36) set attribute MinAmount
	 *
	 * @param value the value to set the attribute to
	 */
	public void setMinAmount(double value)
	{
		setAttribute(AttributeName.MINAMOUNT, value, null);
	}

	/**
	 * (17) get double attribute MinAmount
	 *
	 * @return double the value of the attribute
	 */
	public double getMinAmount()
	{
		return getRealAttribute(AttributeName.MINAMOUNT, null, 0.0);
	}

	/*
	 * --------------------------------------------------------------------- Methods for Attribute MinCleanup ---------------------------------------------------------------------
	 */
	/**
	 * (36) set attribute MinCleanup
	 *
	 * @param value the value to set the attribute to
	 */
	public void setMinCleanup(JDFDuration value)
	{
		setAttribute(AttributeName.MINCLEANUP, value, null);
	}

	/**
	 * (20) get JDFDuration attribute MinCleanup
	 *
	 * @return JDFDuration the value of the attribute, null if a the attribute value is not a valid to create a JDFDuration
	 */
	public JDFDuration getMinCleanup()
	{
		final String strAttrName = getAttribute(AttributeName.MINCLEANUP, null, null);
		final JDFDuration nPlaceHolder = JDFDuration.createDuration(strAttrName);
		return nPlaceHolder;
	}

	/*
	 * --------------------------------------------------------------------- Methods for Attribute MinSetup ---------------------------------------------------------------------
	 */
	/**
	 * (36) set attribute MinSetup
	 *
	 * @param value the value to set the attribute to
	 */
	public void setMinSetup(JDFDuration value)
	{
		setAttribute(AttributeName.MINSETUP, value, null);
	}

	/**
	 * (20) get JDFDuration attribute MinSetup
	 *
	 * @return JDFDuration the value of the attribute, null if a the attribute value is not a valid to create a JDFDuration
	 */
	public JDFDuration getMinSetup()
	{
		final String strAttrName = getAttribute(AttributeName.MINSETUP, null, null);
		final JDFDuration nPlaceHolder = JDFDuration.createDuration(strAttrName);
		return nPlaceHolder;
	}

	/*
	 * --------------------------------------------------------------------- Methods for Attribute Name ---------------------------------------------------------------------
	 */
	/**
	 * (36) set attribute Name
	 *
	 * @param value the value to set the attribute to
	 */
	public void setName(String value)
	{
		setAttribute(AttributeName.NAME, value, null);
	}

	/**
	 * (23) get String attribute Name
	 *
	 * @return the value of the attribute
	 */
	public String getName()
	{
		return getAttribute(AttributeName.NAME, null, JDFCoreConstants.EMPTYSTRING);
	}

	/*
	 * --------------------------------------------------------------------- Methods for Attribute DevCapsRef ---------------------------------------------------------------------
	 */
	/**
	 * (36) set attribute DevCapsRef
	 *
	 * @param value the value to set the attribute to
	 */
	public void setDevCapsRef(String value)
	{
		setAttribute(AttributeName.DEVCAPSREF, value, null);
	}

	/**
	 * (23) get String attribute DevCapsRef
	 *
	 * @return the value of the attribute
	 */
	public String getDevCapsRef()
	{
		return getAttribute(AttributeName.DEVCAPSREF, null, JDFCoreConstants.EMPTYSTRING);
	}

	/*
	 * --------------------------------------------------------------------- Methods for Attribute Unit ---------------------------------------------------------------------
	 */
	/**
	 * (36) set attribute Unit
	 *
	 * @param value the value to set the attribute to
	 */
	public void setUnit(String value)
	{
		setAttribute(AttributeName.UNIT, value, null);
	}

	/**
	 * (23) get String attribute Unit
	 *
	 * @return the value of the attribute
	 */
	public String getUnit()
	{
		return getAttribute(AttributeName.UNIT, null, JDFCoreConstants.EMPTYSTRING);
	}

}
