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

import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.enums.ValuedEnum;
import org.apache.xerces.dom.CoreDocumentImpl;
import org.cip4.jdflib.core.AtrInfoTable;
import org.cip4.jdflib.core.AttributeInfo;
import org.cip4.jdflib.core.AttributeName;
import org.cip4.jdflib.resource.JDFResource;
import org.cip4.jdflib.util.JavaEnumUtil;

/**
 *****************************************************************************
 * class JDFAutoTrimmingParams : public JDFResource
 *****************************************************************************
 * 
 */

public abstract class JDFAutoTrimmingParams extends JDFResource
{

	private static final long serialVersionUID = 1L;

	private static AtrInfoTable[] atrInfoTable = new AtrInfoTable[5];
	static
	{
		atrInfoTable[0] = new AtrInfoTable(AttributeName.TRIMCOVER, 0x3333333111l, AttributeInfo.EnumAttributeType.enumeration, EnumTrimCover.getEnum(0), "Both");
		atrInfoTable[1] = new AtrInfoTable(AttributeName.WIDTH, 0x3333333333l, AttributeInfo.EnumAttributeType.double_, null, null);
		atrInfoTable[2] = new AtrInfoTable(AttributeName.HEIGHT, 0x3333333333l, AttributeInfo.EnumAttributeType.double_, null, null);
		atrInfoTable[3] = new AtrInfoTable(AttributeName.TRIMMINGOFFSET, 0x3333333333l, AttributeInfo.EnumAttributeType.double_, null, null);
		atrInfoTable[4] = new AtrInfoTable(AttributeName.TRIMMINGTYPE, 0x4444444431l, AttributeInfo.EnumAttributeType.enumeration, EnumTrimmingType.getEnum(0), null);
	}

	@Override
	protected AttributeInfo getTheAttributeInfo()
	{
		return super.getTheAttributeInfo().updateReplace(atrInfoTable);
	}

	/**
	 * Constructor for JDFAutoTrimmingParams
	 *
	 * @param myOwnerDocument
	 * @param qualifiedName
	 */
	protected JDFAutoTrimmingParams(CoreDocumentImpl myOwnerDocument, String qualifiedName)
	{
		super(myOwnerDocument, qualifiedName);
	}

	/**
	 * Constructor for JDFAutoTrimmingParams
	 *
	 * @param myOwnerDocument
	 * @param myNamespaceURI
	 * @param qualifiedName
	 */
	protected JDFAutoTrimmingParams(CoreDocumentImpl myOwnerDocument, String myNamespaceURI, String qualifiedName)
	{
		super(myOwnerDocument, myNamespaceURI, qualifiedName);
	}

	/**
	 * Constructor for JDFAutoTrimmingParams
	 *
	 * @param myOwnerDocument
	 * @param myNamespaceURI
	 * @param qualifiedName
	 * @param myLocalName
	 */
	protected JDFAutoTrimmingParams(CoreDocumentImpl myOwnerDocument, String myNamespaceURI, String qualifiedName, String myLocalName)
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

	/**
	 * Enumeration strings for TrimCover
	 */

	public enum ETrimCover
	{
		Front, Back, Both, Neither;

		public static ETrimCover getEnum(String val)
		{
			return JavaEnumUtil.getEnumIgnoreCase(ETrimCover.class, val, ETrimCover.Both);
		}
	}

	/**
	 * Enumeration strings for TrimCover
	 */

	@SuppressWarnings("rawtypes")
	public static class EnumTrimCover extends ValuedEnum
	{
		private static final long serialVersionUID = 1L;
		private static int m_startValue = 0;

		protected EnumTrimCover(String name)
		{
			super(name, m_startValue++);
		}

		/**
		 * @param enumName the string to convert
		 * @return the enum
		 */
		public static EnumTrimCover getEnum(String enumName)
		{
			return (EnumTrimCover) getEnum(EnumTrimCover.class, enumName);
		}

		/**
		 * @param enumValue the integer to convert
		 * @return the enum
		 */
		public static EnumTrimCover getEnum(int enumValue)
		{
			return (EnumTrimCover) getEnum(EnumTrimCover.class, enumValue);
		}

		/**
		 * @return the map of enums
		 */
		public static Map getEnumMap()
		{
			return getEnumMap(EnumTrimCover.class);
		}

		/**
		 * @return the list of enums
		 */
		public static List getEnumList()
		{
			return getEnumList(EnumTrimCover.class);
		}

		/**
		 * @return the iterator
		 */
		public static Iterator iterator()
		{
			return iterator(EnumTrimCover.class);
		}

		/**  */
		public static final EnumTrimCover Front = new EnumTrimCover("Front");
		/**  */
		public static final EnumTrimCover Back = new EnumTrimCover("Back");
		/**  */
		public static final EnumTrimCover Both = new EnumTrimCover("Both");
		/**  */
		public static final EnumTrimCover Neither = new EnumTrimCover("Neither");
	}

	/**
	 * Enumeration strings for TrimmingType
	 */

	public enum ETrimmingType
	{
		Detailed, SystemSpecified;

		public static ETrimmingType getEnum(String val)
		{
			return JavaEnumUtil.getEnumIgnoreCase(ETrimmingType.class, val, null);
		}
	}

	/**
	 * Enumeration strings for TrimmingType
	 */

	@SuppressWarnings("rawtypes")
	public static class EnumTrimmingType extends ValuedEnum
	{
		private static final long serialVersionUID = 1L;
		private static int m_startValue = 0;

		protected EnumTrimmingType(String name)
		{
			super(name, m_startValue++);
		}

		/**
		 * @param enumName the string to convert
		 * @return the enum
		 */
		public static EnumTrimmingType getEnum(String enumName)
		{
			return (EnumTrimmingType) getEnum(EnumTrimmingType.class, enumName);
		}

		/**
		 * @param enumValue the integer to convert
		 * @return the enum
		 */
		public static EnumTrimmingType getEnum(int enumValue)
		{
			return (EnumTrimmingType) getEnum(EnumTrimmingType.class, enumValue);
		}

		/**
		 * @return the map of enums
		 */
		public static Map getEnumMap()
		{
			return getEnumMap(EnumTrimmingType.class);
		}

		/**
		 * @return the list of enums
		 */
		public static List getEnumList()
		{
			return getEnumList(EnumTrimmingType.class);
		}

		/**
		 * @return the iterator
		 */
		public static Iterator iterator()
		{
			return iterator(EnumTrimmingType.class);
		}

		/**  */
		public static final EnumTrimmingType Detailed = new EnumTrimmingType("Detailed");
		/**  */
		public static final EnumTrimmingType SystemSpecified = new EnumTrimmingType("SystemSpecified");
	}

	/*
	 * ************************************************************************ Attribute getter / setter ************************************************************************
	 */

	/*
	 * --------------------------------------------------------------------- Methods for Attribute TrimCover ---------------------------------------------------------------------
	 */
	/**
	 * (5) set attribute TrimCover
	 *
	 * @param enumVar the enumVar to set the attribute to
	 */
	public void setTrimCover(ETrimCover enumVar)
	{
		setAttribute(AttributeName.TRIMCOVER, enumVar == null ? null : enumVar.name(), null);
	}

	/**
	 * (9) get attribute TrimCover
	 *
	 * @return the value of the attribute
	 */
	public ETrimCover getETrimCover()
	{
		return ETrimCover.getEnum(getAttribute(AttributeName.TRIMCOVER, null, "Both"));
	}

	/*
	 * --------------------------------------------------------------------- Methods for Attribute TrimCover ---------------------------------------------------------------------
	 */
	/**
	 * (5) set attribute TrimCover
	 *
	 * @param enumVar the enumVar to set the attribute to
	 * @deprecated use setTrimCover(ETrimCover) based on java.lang.enum instead
	 */
	@Deprecated
	public void setTrimCover(EnumTrimCover enumVar)
	{
		setAttribute(AttributeName.TRIMCOVER, enumVar == null ? null : enumVar.getName(), null);
	}

	/**
	 * (9) get attribute TrimCover
	 *
	 * @return the value of the attribute
	 * @deprecated use ETrimCover getETrimCover() based on java.lang.enum instead
	 */
	@Deprecated
	public EnumTrimCover getTrimCover()
	{
		return EnumTrimCover.getEnum(getAttribute(AttributeName.TRIMCOVER, null, "Both"));
	}

	/*
	 * --------------------------------------------------------------------- Methods for Attribute Width ---------------------------------------------------------------------
	 */
	/**
	 * (36) set attribute Width
	 *
	 * @param value the value to set the attribute to
	 */
	public void setWidth(double value)
	{
		setAttribute(AttributeName.WIDTH, value, null);
	}

	/**
	 * (17) get double attribute Width
	 *
	 * @return double the value of the attribute
	 */
	public double getWidth()
	{
		return getRealAttribute(AttributeName.WIDTH, null, 0.0);
	}

	/*
	 * --------------------------------------------------------------------- Methods for Attribute Height ---------------------------------------------------------------------
	 */
	/**
	 * (36) set attribute Height
	 *
	 * @param value the value to set the attribute to
	 */
	public void setHeight(double value)
	{
		setAttribute(AttributeName.HEIGHT, value, null);
	}

	/**
	 * (17) get double attribute Height
	 *
	 * @return double the value of the attribute
	 */
	public double getHeight()
	{
		return getRealAttribute(AttributeName.HEIGHT, null, 0.0);
	}

	/*
	 * --------------------------------------------------------------------- Methods for Attribute TrimmingOffset
	 * ---------------------------------------------------------------------
	 */
	/**
	 * (36) set attribute TrimmingOffset
	 *
	 * @param value the value to set the attribute to
	 */
	public void setTrimmingOffset(double value)
	{
		setAttribute(AttributeName.TRIMMINGOFFSET, value, null);
	}

	/**
	 * (17) get double attribute TrimmingOffset
	 *
	 * @return double the value of the attribute
	 */
	public double getTrimmingOffset()
	{
		return getRealAttribute(AttributeName.TRIMMINGOFFSET, null, 0.0);
	}

	/*
	 * --------------------------------------------------------------------- Methods for Attribute TrimmingType
	 * ---------------------------------------------------------------------
	 */
	/**
	 * (5) set attribute TrimmingType
	 *
	 * @param enumVar the enumVar to set the attribute to
	 */
	public void setTrimmingType(ETrimmingType enumVar)
	{
		setAttribute(AttributeName.TRIMMINGTYPE, enumVar == null ? null : enumVar.name(), null);
	}

	/**
	 * (9) get attribute TrimmingType
	 *
	 * @return the value of the attribute
	 */
	public ETrimmingType getETrimmingType()
	{
		return ETrimmingType.getEnum(getAttribute(AttributeName.TRIMMINGTYPE, null, null));
	}

	/*
	 * --------------------------------------------------------------------- Methods for Attribute TrimmingType
	 * ---------------------------------------------------------------------
	 */
	/**
	 * (5) set attribute TrimmingType
	 *
	 * @param enumVar the enumVar to set the attribute to
	 * @deprecated use setTrimmingType(ETrimmingType) based on java.lang.enum instead
	 */
	@Deprecated
	public void setTrimmingType(EnumTrimmingType enumVar)
	{
		setAttribute(AttributeName.TRIMMINGTYPE, enumVar == null ? null : enumVar.getName(), null);
	}

	/**
	 * (9) get attribute TrimmingType
	 *
	 * @return the value of the attribute
	 * @deprecated use ETrimmingType getETrimmingType() based on java.lang.enum instead
	 */
	@Deprecated
	public EnumTrimmingType getTrimmingType()
	{
		return EnumTrimmingType.getEnum(getAttribute(AttributeName.TRIMMINGTYPE, null, null));
	}

}
