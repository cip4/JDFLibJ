/*
 * The CIP4 Software License, Version 1.0
 *
 *
 * Copyright (c) 2001-2014 The International Cooperation for the Integration of
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
import org.cip4.jdflib.resource.JDFResource;
import org.cip4.jdflib.util.JDFDuration;

/**
*****************************************************************************
class JDFAutoShrinkingParams : public JDFResource

*****************************************************************************
*/

public abstract class JDFAutoShrinkingParams extends JDFResource
{

	private static final long serialVersionUID = 1L;

	private static AtrInfoTable[] atrInfoTable = new AtrInfoTable[3];
	static
	{
		atrInfoTable[0] = new AtrInfoTable(AttributeName.SHRINKINGMETHOD, 0x33333331, AttributeInfo.EnumAttributeType.enumeration, EnumShrinkingMethod.getEnum(0), "ShrinkHot");
		atrInfoTable[1] = new AtrInfoTable(AttributeName.DURATION, 0x33333331, AttributeInfo.EnumAttributeType.duration, null, null);
		atrInfoTable[2] = new AtrInfoTable(AttributeName.TEMPERATURE, 0x33333331, AttributeInfo.EnumAttributeType.double_, null, null);
	}

	@Override
	protected AttributeInfo getTheAttributeInfo()
	{
		return super.getTheAttributeInfo().updateReplace(atrInfoTable);
	}

	/**
	 * Constructor for JDFAutoShrinkingParams
	 * @param myOwnerDocument
	 * @param qualifiedName
	 */
	protected JDFAutoShrinkingParams(CoreDocumentImpl myOwnerDocument, String qualifiedName)
	{
		super(myOwnerDocument, qualifiedName);
	}

	/**
	 * Constructor for JDFAutoShrinkingParams
	 * @param myOwnerDocument
	 * @param myNamespaceURI
	 * @param qualifiedName
	 */
	protected JDFAutoShrinkingParams(CoreDocumentImpl myOwnerDocument, String myNamespaceURI, String qualifiedName)
	{
		super(myOwnerDocument, myNamespaceURI, qualifiedName);
	}

	/**
	 * Constructor for JDFAutoShrinkingParams
	 * @param myOwnerDocument
	 * @param myNamespaceURI
	 * @param qualifiedName
	 * @param myLocalName
	 */
	protected JDFAutoShrinkingParams(CoreDocumentImpl myOwnerDocument, String myNamespaceURI, String qualifiedName, String myLocalName)
	{
		super(myOwnerDocument, myNamespaceURI, qualifiedName, myLocalName);
	}

	/**
	 * @return  the string representation
	 */
	@Override
	public String toString()
	{
		return " JDFAutoShrinkingParams[  --> " + super.toString() + " ]";
	}

	/**
	 * @return  true if ok
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
	* Enumeration strings for ShrinkingMethod
	*/

	public static class EnumShrinkingMethod extends ValuedEnum
	{
		private static final long serialVersionUID = 1L;
		private static int m_startValue = 0;

		private EnumShrinkingMethod(String name)
		{
			super(name, m_startValue++);
		}

		/**
		 * @param enumName the string to convert
		 * @return the enum
		 */
		public static EnumShrinkingMethod getEnum(String enumName)
		{
			return (EnumShrinkingMethod) getEnum(EnumShrinkingMethod.class, enumName);
		}

		/**
		 * @param enumValue the integer to convert
		 * @return the enum
		 */
		public static EnumShrinkingMethod getEnum(int enumValue)
		{
			return (EnumShrinkingMethod) getEnum(EnumShrinkingMethod.class, enumValue);
		}

		/**
		 * @return the map of enums
		 */
		public static Map getEnumMap()
		{
			return getEnumMap(EnumShrinkingMethod.class);
		}

		/**
		 * @return the list of enums
		 */
		public static List getEnumList()
		{
			return getEnumList(EnumShrinkingMethod.class);
		}

		/**
		 * @return the iterator
		 */
		public static Iterator iterator()
		{
			return iterator(EnumShrinkingMethod.class);
		}

		public static final EnumShrinkingMethod ShrinkCool = new EnumShrinkingMethod("ShrinkCool");
		public static final EnumShrinkingMethod ShrinkHot = new EnumShrinkingMethod("ShrinkHot");
	}

	/* ************************************************************************
	 * Attribute getter / setter
	 * ************************************************************************
	 */

	/* ---------------------------------------------------------------------
	Methods for Attribute ShrinkingMethod
	--------------------------------------------------------------------- */
	/**
	  * (5) set attribute ShrinkingMethod
	  * @param enumVar the enumVar to set the attribute to
	  */
	public void setShrinkingMethod(EnumShrinkingMethod enumVar)
	{
		setAttribute(AttributeName.SHRINKINGMETHOD, enumVar == null ? null : enumVar.getName(), null);
	}

	/**
	  * (9) get attribute ShrinkingMethod
	  * @return the value of the attribute
	  */
	public EnumShrinkingMethod getShrinkingMethod()
	{
		return EnumShrinkingMethod.getEnum(getAttribute(AttributeName.SHRINKINGMETHOD, null, "ShrinkHot"));
	}

	/* ---------------------------------------------------------------------
	Methods for Attribute Duration
	--------------------------------------------------------------------- */
	/**
	  * (36) set attribute Duration
	  * @param value the value to set the attribute to
	  */
	public void setDuration(JDFDuration value)
	{
		setAttribute(AttributeName.DURATION, value, null);
	}

	/**
	  * (20) get JDFDuration attribute Duration
	  * @return JDFDuration the value of the attribute, null if a the
	  *         attribute value is not a valid to create a JDFDuration
	  */
	public JDFDuration getDuration()
	{
		final String strAttrName = getAttribute(AttributeName.DURATION, null, null);
		final JDFDuration nPlaceHolder = JDFDuration.createDuration(strAttrName);
		return nPlaceHolder;
	}

	/* ---------------------------------------------------------------------
	Methods for Attribute Temperature
	--------------------------------------------------------------------- */
	/**
	  * (36) set attribute Temperature
	  * @param value the value to set the attribute to
	  */
	public void setTemperature(double value)
	{
		setAttribute(AttributeName.TEMPERATURE, value, null);
	}

	/**
	  * (17) get double attribute Temperature
	  * @return double the value of the attribute
	  */
	public double getTemperature()
	{
		return getRealAttribute(AttributeName.TEMPERATURE, null, 0.0);
	}

}// end namespace JDF
