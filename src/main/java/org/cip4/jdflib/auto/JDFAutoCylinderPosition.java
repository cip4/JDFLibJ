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
import org.cip4.jdflib.datatypes.JDFXYPairRangeList;
import org.cip4.jdflib.util.JavaEnumUtil;

/**
 ***************************************************************************** class JDFAutoCylinderPosition : public JDFElement
 */

public abstract class JDFAutoCylinderPosition extends JDFElement
{

	private static final long serialVersionUID = 1L;

	private static AtrInfoTable[] atrInfoTable = new AtrInfoTable[4];
	static
	{
		atrInfoTable[0] = new AtrInfoTable(AttributeName.PLATEPOSITION, 0x2222222111l, AttributeInfo.EnumAttributeType.XYPairRangeList, null, null);
		atrInfoTable[1] = new AtrInfoTable(AttributeName.PLATETYPE, 0x3333333111l, AttributeInfo.EnumAttributeType.enumeration,
				JavaEnumUtil.getEnum(EnumPlateType.class, 0), "Exposed");
		atrInfoTable[2] = new AtrInfoTable(AttributeName.PLATEUSAGE, 0x3333333111l, AttributeInfo.EnumAttributeType.enumeration,
				JavaEnumUtil.getEnum(EnumPlateUsage.class, 0), "Original");
		atrInfoTable[3] = new AtrInfoTable(AttributeName.DEVICEMODULEINDEX, 0x2222222111l, AttributeInfo.EnumAttributeType.integer, null, null);
	}

	@Override
	protected AttributeInfo getTheAttributeInfo()
	{
		return super.getTheAttributeInfo().updateReplace(atrInfoTable);
	}

	/**
	 * Constructor for JDFAutoCylinderPosition
	 *
	 * @param myOwnerDocument
	 * @param qualifiedName
	 */
	protected JDFAutoCylinderPosition(final CoreDocumentImpl myOwnerDocument, final String qualifiedName)
	{
		super(myOwnerDocument, qualifiedName);
	}

	/**
	 * Constructor for JDFAutoCylinderPosition
	 *
	 * @param myOwnerDocument
	 * @param myNamespaceURI
	 * @param qualifiedName
	 */
	protected JDFAutoCylinderPosition(final CoreDocumentImpl myOwnerDocument, final String myNamespaceURI, final String qualifiedName)
	{
		super(myOwnerDocument, myNamespaceURI, qualifiedName);
	}

	/**
	 * Constructor for JDFAutoCylinderPosition
	 *
	 * @param myOwnerDocument
	 * @param myNamespaceURI
	 * @param qualifiedName
	 * @param myLocalName
	 */
	protected JDFAutoCylinderPosition(final CoreDocumentImpl myOwnerDocument, final String myNamespaceURI, final String qualifiedName, final String myLocalName)
	{
		super(myOwnerDocument, myNamespaceURI, qualifiedName, myLocalName);
	}

	/**
	 * Enumeration strings for numPlateType
	 */

	public enum EnumPlateType
	{
		Exposed, Dummy;

		public static EnumPlateType getEnum(final String val)
		{
			return JavaEnumUtil.getEnumIgnoreCase(EnumPlateType.class, val, EnumPlateType.Exposed);
		}
	}

	/**
	 * Enumeration strings for numPlateUsage
	 */

	public enum EnumPlateUsage
	{
		Original, Reuse;

		public static EnumPlateUsage getEnum(final String val)
		{
			return JavaEnumUtil.getEnumIgnoreCase(EnumPlateUsage.class, val, EnumPlateUsage.Original);
		}
	}/*
		 * ************************************************************************
		 * Attribute getter / setter
		 * ************************************************************************
		 */

	/*
	 * ---------------------------------------------------------------------
	 * Methods for Attribute PlatePosition
	 * ---------------------------------------------------------------------
	 */
	/**
	 * (36) set attribute PlatePosition
	 *
	 * @param value the value to set the attribute to
	 */
	public void setPlatePosition(final JDFXYPairRangeList value)
	{
		setAttribute(AttributeName.PLATEPOSITION, value == null ? null : value.toString(), null);
	}

	/**
	 * (20) get JDFXYPairRangeList attribute PlatePosition
	 *
	 * @return JDFXYPairRangeList the value of the attribute, null if a the
	 *         attribute value is not a valid to create a JDFXYPairRangeList
	 */
	public JDFXYPairRangeList getPlatePosition()
	{
		final String strAttrName = getAttribute(AttributeName.PLATEPOSITION, null, null);
		final JDFXYPairRangeList nPlaceHolder = JDFXYPairRangeList.createXYPairRangeList(strAttrName);
		return nPlaceHolder;
	}

	/*
	 * ---------------------------------------------------------------------
	 * Methods for Attribute PlateType
	 * ---------------------------------------------------------------------
	 */
	/**
	 * (5) set attribute PlateType
	 *
	 * @param enumVar the enumVar to set the attribute to
	 */
	public void setPlateType(final EnumPlateType enumVar)
	{
		setAttribute(AttributeName.PLATETYPE, JavaEnumUtil.getName(enumVar), null);
	}

	/**
	 * (9) get attribute PlateType
	 *
	 * @return the value of the attribute
	 */
	public EnumPlateType getPlateType()
	{
		return EnumPlateType.getEnum(getAttribute(AttributeName.PLATETYPE, null, "Exposed"));
	}

	/*
	 * ---------------------------------------------------------------------
	 * Methods for Attribute PlateUsage
	 * ---------------------------------------------------------------------
	 */
	/**
	 * (5) set attribute PlateUsage
	 *
	 * @param enumVar the enumVar to set the attribute to
	 */
	public void setPlateUsage(final EnumPlateUsage enumVar)
	{
		setAttribute(AttributeName.PLATEUSAGE, JavaEnumUtil.getName(enumVar), null);
	}

	/**
	 * (9) get attribute PlateUsage
	 *
	 * @return the value of the attribute
	 */
	public EnumPlateUsage getPlateUsage()
	{
		return EnumPlateUsage.getEnum(getAttribute(AttributeName.PLATEUSAGE, null, "Original"));
	}

	/*
	 * ---------------------------------------------------------------------
	 * Methods for Attribute DeviceModuleIndex
	 * ---------------------------------------------------------------------
	 */
	/**
	 * (36) set attribute DeviceModuleIndex
	 *
	 * @param value the value to set the attribute to
	 */
	public void setDeviceModuleIndex(final int value)
	{
		setAttribute(AttributeName.DEVICEMODULEINDEX, value, null);
	}

	/**
	 * (15) get int attribute DeviceModuleIndex
	 *
	 * @return int the value of the attribute
	 */
	public int getDeviceModuleIndex()
	{
		return getIntAttribute(AttributeName.DEVICEMODULEINDEX, null, 0);
	}

}
