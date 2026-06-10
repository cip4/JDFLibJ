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
import org.cip4.jdflib.util.JavaEnumUtil;

/**
 ***************************************************************************** class JDFAutoMISDetails : public JDFElement
 */

public abstract class JDFAutoMISDetails extends JDFElement
{

	private static final long serialVersionUID = 1L;

	private static AtrInfoTable[] atrInfoTable = new AtrInfoTable[5];
	static
	{
		atrInfoTable[0] = new AtrInfoTable(AttributeName.COMPLEXITY, 0x3333333311l, AttributeInfo.EnumAttributeType.double_, null, null);
		atrInfoTable[1] = new AtrInfoTable(AttributeName.COSTTYPE, 0x3333333311l, AttributeInfo.EnumAttributeType.enumeration,
				JavaEnumUtil.getEnum(EnumCostType.class, 0), null);
		atrInfoTable[2] = new AtrInfoTable(AttributeName.DEVICEOPERATIONMODE, 0x3333333311l, AttributeInfo.EnumAttributeType.enumeration,
				JavaEnumUtil.getEnum(EnumDeviceOperationMode.class, 0), null);
		atrInfoTable[3] = new AtrInfoTable(AttributeName.WORKTYPE, 0x3333333311l, AttributeInfo.EnumAttributeType.enumeration,
				JavaEnumUtil.getEnum(EnumWorkType.class, 0), null);
		atrInfoTable[4] = new AtrInfoTable(AttributeName.WORKTYPEDETAILS, 0x3333333311l, AttributeInfo.EnumAttributeType.string, null, null);
	}

	@Override
	protected AttributeInfo getTheAttributeInfo()
	{
		return super.getTheAttributeInfo().updateReplace(atrInfoTable);
	}

	/**
	 * Constructor for JDFAutoMISDetails
	 *
	 * @param myOwnerDocument
	 * @param qualifiedName
	 */
	protected JDFAutoMISDetails(final CoreDocumentImpl myOwnerDocument, final String qualifiedName)
	{
		super(myOwnerDocument, qualifiedName);
	}

	/**
	 * Constructor for JDFAutoMISDetails
	 *
	 * @param myOwnerDocument
	 * @param myNamespaceURI
	 * @param qualifiedName
	 */
	protected JDFAutoMISDetails(final CoreDocumentImpl myOwnerDocument, final String myNamespaceURI, final String qualifiedName)
	{
		super(myOwnerDocument, myNamespaceURI, qualifiedName);
	}

	/**
	 * Constructor for JDFAutoMISDetails
	 *
	 * @param myOwnerDocument
	 * @param myNamespaceURI
	 * @param qualifiedName
	 * @param myLocalName
	 */
	protected JDFAutoMISDetails(final CoreDocumentImpl myOwnerDocument, final String myNamespaceURI, final String qualifiedName, final String myLocalName)
	{
		super(myOwnerDocument, myNamespaceURI, qualifiedName, myLocalName);
	}

	/**
	 * Enumeration strings for numCostType
	 */

	public enum EnumCostType
	{
		Chargeable, NonChargeable;

		public static EnumCostType getEnum(final String val)
		{
			return JavaEnumUtil.getEnumIgnoreCase(EnumCostType.class, val, null);
		}
	}

	/**
	 * Enumeration strings for numDeviceOperationMode
	 */

	public enum EnumDeviceOperationMode
	{
		Productive, NonProductive, Maintenance;

		public static EnumDeviceOperationMode getEnum(final String val)
		{
			return JavaEnumUtil.getEnumIgnoreCase(EnumDeviceOperationMode.class, val, null);
		}
	}

	/**
	 * Enumeration strings for numWorkType
	 */

	public enum EnumWorkType
	{
		Original, Alteration, Rework;

		public static EnumWorkType getEnum(final String val)
		{
			return JavaEnumUtil.getEnumIgnoreCase(EnumWorkType.class, val, null);
		}
	}/*
		 * ************************************************************************
		 * Attribute getter / setter
		 * ************************************************************************
		 */

	/*
	 * ---------------------------------------------------------------------
	 * Methods for Attribute Complexity
	 * ---------------------------------------------------------------------
	 */
	/**
	 * (36) set attribute Complexity
	 *
	 * @param value the value to set the attribute to
	 */
	public void setComplexity(final double value)
	{
		setAttribute(AttributeName.COMPLEXITY, value, null);
	}

	/**
	 * (17) get double attribute Complexity
	 *
	 * @return double the value of the attribute
	 */
	public double getComplexity()
	{
		return getRealAttribute(AttributeName.COMPLEXITY, null, 0.0);
	}

	/*
	 * ---------------------------------------------------------------------
	 * Methods for Attribute CostType
	 * ---------------------------------------------------------------------
	 */
	/**
	 * (5) set attribute CostType
	 *
	 * @param enumVar the enumVar to set the attribute to
	 */
	public void setCostType(final EnumCostType enumVar)
	{
		setAttribute(AttributeName.COSTTYPE, JavaEnumUtil.getName(enumVar), null);
	}

	/**
	 * (9) get attribute CostType
	 *
	 * @return the value of the attribute
	 */
	public EnumCostType getCostType()
	{
		return EnumCostType.getEnum(getAttribute(AttributeName.COSTTYPE, null, null));
	}

	/*
	 * ---------------------------------------------------------------------
	 * Methods for Attribute DeviceOperationMode
	 * ---------------------------------------------------------------------
	 */
	/**
	 * (5) set attribute DeviceOperationMode
	 *
	 * @param enumVar the enumVar to set the attribute to
	 */
	public void setDeviceOperationMode(final EnumDeviceOperationMode enumVar)
	{
		setAttribute(AttributeName.DEVICEOPERATIONMODE, JavaEnumUtil.getName(enumVar), null);
	}

	/**
	 * (9) get attribute DeviceOperationMode
	 *
	 * @return the value of the attribute
	 */
	public EnumDeviceOperationMode getDeviceOperationMode()
	{
		return EnumDeviceOperationMode.getEnum(getAttribute(AttributeName.DEVICEOPERATIONMODE, null, null));
	}

	/*
	 * ---------------------------------------------------------------------
	 * Methods for Attribute WorkType
	 * ---------------------------------------------------------------------
	 */
	/**
	 * (5) set attribute WorkType
	 *
	 * @param enumVar the enumVar to set the attribute to
	 */
	public void setWorkType(final EnumWorkType enumVar)
	{
		setAttribute(AttributeName.WORKTYPE, JavaEnumUtil.getName(enumVar), null);
	}

	/**
	 * (9) get attribute WorkType
	 *
	 * @return the value of the attribute
	 */
	public EnumWorkType getWorkType()
	{
		return EnumWorkType.getEnum(getAttribute(AttributeName.WORKTYPE, null, null));
	}

	/*
	 * ---------------------------------------------------------------------
	 * Methods for Attribute WorkTypeDetails
	 * ---------------------------------------------------------------------
	 */
	/**
	 * (36) set attribute WorkTypeDetails
	 *
	 * @param value the value to set the attribute to
	 */
	public void setWorkTypeDetails(final String value)
	{
		setAttribute(AttributeName.WORKTYPEDETAILS, value, null);
	}

	/**
	 * (23) get String attribute WorkTypeDetails
	 *
	 * @return the value of the attribute
	 */
	public String getWorkTypeDetails()
	{
		return getAttribute(AttributeName.WORKTYPEDETAILS, null, JDFCoreConstants.EMPTYSTRING);
	}

}
