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
import java.util.Vector;

import org.apache.commons.lang.enums.ValuedEnum;
import org.apache.xerces.dom.CoreDocumentImpl;
import org.cip4.jdflib.core.AtrInfoTable;
import org.cip4.jdflib.core.AttributeInfo;
import org.cip4.jdflib.core.AttributeName;
import org.cip4.jdflib.core.ElemInfoTable;
import org.cip4.jdflib.core.ElementInfo;
import org.cip4.jdflib.core.ElementName;
import org.cip4.jdflib.core.JDFElement;
import org.cip4.jdflib.datatypes.JDFXYPair;
import org.cip4.jdflib.resource.process.JDFFileSpec;
import org.cip4.jdflib.util.JavaEnumUtil;

/**
 *****************************************************************************
 * class JDFAutoIcon : public JDFElement
 *****************************************************************************
 * 
 */

public abstract class JDFAutoIcon extends JDFElement
{

	private static final long serialVersionUID = 1L;

	private static AtrInfoTable[] atrInfoTable = new AtrInfoTable[3];
	static
	{
		atrInfoTable[0] = new AtrInfoTable(AttributeName.SIZE, 0x2222222221l, AttributeInfo.EnumAttributeType.XYPair, null, null);
		atrInfoTable[1] = new AtrInfoTable(AttributeName.BITDEPTH, 0x2222222221l, AttributeInfo.EnumAttributeType.integer, null, null);
		atrInfoTable[2] = new AtrInfoTable(AttributeName.ICONUSAGE, 0x3333333331l, AttributeInfo.EnumAttributeType.enumerations, EnumIconUsage.getEnum(0), null);
	}

	@Override
	protected AttributeInfo getTheAttributeInfo()
	{
		return super.getTheAttributeInfo().updateReplace(atrInfoTable);
	}

	private static ElemInfoTable[] elemInfoTable = new ElemInfoTable[1];
	static
	{
		elemInfoTable[0] = new ElemInfoTable(ElementName.FILESPEC, 0x5555555551l);
	}

	@Override
	protected ElementInfo getTheElementInfo()
	{
		return super.getTheElementInfo().updateReplace(elemInfoTable);
	}

	/**
	 * Constructor for JDFAutoIcon
	 *
	 * @param myOwnerDocument
	 * @param qualifiedName
	 */
	protected JDFAutoIcon(CoreDocumentImpl myOwnerDocument, String qualifiedName)
	{
		super(myOwnerDocument, qualifiedName);
	}

	/**
	 * Constructor for JDFAutoIcon
	 *
	 * @param myOwnerDocument
	 * @param myNamespaceURI
	 * @param qualifiedName
	 */
	protected JDFAutoIcon(CoreDocumentImpl myOwnerDocument, String myNamespaceURI, String qualifiedName)
	{
		super(myOwnerDocument, myNamespaceURI, qualifiedName);
	}

	/**
	 * Constructor for JDFAutoIcon
	 *
	 * @param myOwnerDocument
	 * @param myNamespaceURI
	 * @param qualifiedName
	 * @param myLocalName
	 */
	protected JDFAutoIcon(CoreDocumentImpl myOwnerDocument, String myNamespaceURI, String qualifiedName, String myLocalName)
	{
		super(myOwnerDocument, myNamespaceURI, qualifiedName, myLocalName);
	}

	/**
	 * Enumeration strings for IconUsage
	 */

	public enum EIconUsage
	{
		Unknown, Idle, Down, Setup, Running, Cleanup, Stopped;

		public static EIconUsage getEnum(String val)
		{
			return JavaEnumUtil.getEnumIgnoreCase(EIconUsage.class, val, null);
		}
	}

	/**
	 * Enumeration strings for IconUsage
	 */

	@SuppressWarnings("rawtypes")
	public static class EnumIconUsage extends ValuedEnum
	{
		private static final long serialVersionUID = 1L;
		private static int m_startValue = 0;

		protected EnumIconUsage(String name)
		{
			super(name, m_startValue++);
		}

		/**
		 * @param enumName the string to convert
		 * @return the enum
		 */
		public static EnumIconUsage getEnum(String enumName)
		{
			return (EnumIconUsage) getEnum(EnumIconUsage.class, enumName);
		}

		/**
		 * @param enumValue the integer to convert
		 * @return the enum
		 */
		public static EnumIconUsage getEnum(int enumValue)
		{
			return (EnumIconUsage) getEnum(EnumIconUsage.class, enumValue);
		}

		/**
		 * @return the map of enums
		 */
		public static Map getEnumMap()
		{
			return getEnumMap(EnumIconUsage.class);
		}

		/**
		 * @return the list of enums
		 */
		public static List getEnumList()
		{
			return getEnumList(EnumIconUsage.class);
		}

		/**
		 * @return the iterator
		 */
		public static Iterator iterator()
		{
			return iterator(EnumIconUsage.class);
		}

		/**  */
		public static final EnumIconUsage Unknown = new EnumIconUsage("Unknown");
		/**  */
		public static final EnumIconUsage Idle = new EnumIconUsage("Idle");
		/**  */
		public static final EnumIconUsage Down = new EnumIconUsage("Down");
		/**  */
		public static final EnumIconUsage Setup = new EnumIconUsage("Setup");
		/**  */
		public static final EnumIconUsage Running = new EnumIconUsage("Running");
		/**  */
		public static final EnumIconUsage Cleanup = new EnumIconUsage("Cleanup");
		/**  */
		public static final EnumIconUsage Stopped = new EnumIconUsage("Stopped");
	}

	/*
	 * ************************************************************************ Attribute getter / setter ************************************************************************
	 */

	/*
	 * --------------------------------------------------------------------- Methods for Attribute Size ---------------------------------------------------------------------
	 */
	/**
	 * (36) set attribute Size
	 *
	 * @param value the value to set the attribute to
	 */
	public void setSize(JDFXYPair value)
	{
		setAttribute(AttributeName.SIZE, value, null);
	}

	/**
	 * (20) get JDFXYPair attribute Size
	 *
	 * @return JDFXYPair the value of the attribute, null if a the attribute value is not a valid to create a JDFXYPair
	 */
	public JDFXYPair getSize()
	{
		final String strAttrName = getAttribute(AttributeName.SIZE, null, null);
		final JDFXYPair nPlaceHolder = JDFXYPair.createXYPair(strAttrName);
		return nPlaceHolder;
	}

	/*
	 * --------------------------------------------------------------------- Methods for Attribute BitDepth ---------------------------------------------------------------------
	 */
	/**
	 * (36) set attribute BitDepth
	 *
	 * @param value the value to set the attribute to
	 */
	public void setBitDepth(int value)
	{
		setAttribute(AttributeName.BITDEPTH, value, null);
	}

	/**
	 * (15) get int attribute BitDepth
	 *
	 * @return int the value of the attribute
	 */
	public int getBitDepth()
	{
		return getIntAttribute(AttributeName.BITDEPTH, null, 0);
	}

	/*
	 * --------------------------------------------------------------------- Methods for Attribute IconUsage ---------------------------------------------------------------------
	 */
	/**
	 * (5.2) set attribute IconUsage
	 *
	 * @param v List of the enumeration values
	 */
	public void setEIconUsage(List<EIconUsage> v)
	{
		setEnumsAttribute(AttributeName.ICONUSAGE, v, null);
	}

	/**
	 * (9.2) get IconUsage attribute IconUsage
	 *
	 * @return Vector of the enumerations
	 */
	public List<EIconUsage> getEnumsIconUsage()
	{
		return getEnumerationsAttribute(AttributeName.ICONUSAGE, null, EIconUsage.class);
	}

	/*
	 * --------------------------------------------------------------------- Methods for Attribute IconUsage ---------------------------------------------------------------------
	 */
	/**
	 * (5.2) set attribute IconUsage
	 *
	 * @param v List of the enumeration values
	 * @deprecated use java.lang.enum
	 */
	@Deprecated
	public void setIconUsage(List<EnumIconUsage> v)
	{
		setEnumerationsAttribute(AttributeName.ICONUSAGE, v, null);
	}

	/**
	 * (9.2) get IconUsage attribute IconUsage
	 *
	 * @return Vector of the enumerations
	 */
	public Vector<EnumIconUsage> getIconUsage()
	{
		return getEnumerationsAttribute(AttributeName.ICONUSAGE, null, EnumIconUsage.getEnum(0), false);
	}

	/*
	 * *********************************************************************** Element getter / setter ***********************************************************************
	 */

	/**
	 * (24) const get element FileSpec
	 *
	 * @return JDFFileSpec the element
	 */
	public JDFFileSpec getFileSpec()
	{
		return (JDFFileSpec) getElement(ElementName.FILESPEC, null, 0);
	}

	/**
	 * (25) getCreateFileSpec
	 * 
	 * @return JDFFileSpec the element
	 */
	public JDFFileSpec getCreateFileSpec()
	{
		return (JDFFileSpec) getCreateElement_JDFElement(ElementName.FILESPEC, null, 0);
	}

	/**
	 * (29) append element FileSpec
	 *
	 * @return JDFFileSpec the element @ if the element already exists
	 */
	public JDFFileSpec appendFileSpec()
	{
		return (JDFFileSpec) appendElementN(ElementName.FILESPEC, 1, null);
	}

}
