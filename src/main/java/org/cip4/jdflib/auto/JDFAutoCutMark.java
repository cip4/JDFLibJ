/*
 * The CIP4 Software License, Version 1.0
 *
 *
 * Copyright (c) 2001-2018 The International Cooperation for the Integration of Processes in Prepress, Press and Postpress (CIP4). All rights reserved.
 *
 * Redistribution and use in source and binary forms, with or without modification, are permitted provided that the following conditions are met:
 *
 * 1. Redistributions of source code must retain the above copyright notice, this list of conditions and the following disclaimer.
 *
 * 2. Redistributions in binary form must reproduce the above copyright notice, this list of conditions and the following disclaimer in the documentation and/or other materials provided with the
 * distribution.
 *
 * 3. The end-user documentation included with the redistribution, if any, must include the following acknowledgment: "This product includes software developed by the The International Cooperation for
 * the Integration of Processes in Prepress, Press and Postpress (www.cip4.org)" Alternately, this acknowledgment may appear in the software itself, if and wherever such third-party acknowledgments
 * normally appear.
 *
 * 4. The names "CIP4" and "The International Cooperation for the Integration of Processes in Prepress, Press and Postpress" must not be used to endorse or promote products derived from this software
 * without prior written permission. For written permission, please contact info@cip4.org.
 *
 * 5. Products derived from this software may not be called "CIP4", nor may "CIP4" appear in their name, without prior written permission of the CIP4 organization
 *
 * Usage of this software in commercial products is subject to restrictions. For details please consult info@cip4.org.
 *
 * THIS SOFTWARE IS PROVIDED ``AS IS'' AND ANY EXPRESSED OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO, THE IMPLIED WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE ARE
 * DISCLAIMED. IN NO EVENT SHALL THE INTERNATIONAL COOPERATION FOR THE INTEGRATION OF PROCESSES IN PREPRESS, PRESS AND POSTPRESS OR ITS CONTRIBUTORS BE LIABLE FOR ANY DIRECT, INDIRECT, INCIDENTAL,
 * SPECIAL, EXEMPLARY, OR CONSEQUENTIAL DAMAGES (INCLUDING, BUT NOT LIMITED TO, PROCUREMENT OF SUBSTITUTE GOODS OR SERVICES; LOSS OF USE, DATA, OR PROFITS; OR BUSINESS INTERRUPTION) HOWEVER CAUSED AND
 * ON ANY THEORY OF LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY, OR TORT (INCLUDING NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE OF THIS SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY
 * OF SUCH DAMAGE. ====================================================================
 *
 * This software consists of voluntary contributions made by many individuals on behalf of the The International Cooperation for the Integration of Processes in Prepress, Press and Postpress and was
 * originally based on software copyright (c) 1999-2001, Heidelberger Druckmaschinen AG copyright (c) 1999-2001, Agfa-Gevaert N.V.
 *
 * For more information on The International Cooperation for the Integration of Processes in Prepress, Press and Postpress , please see <http://www.cip4.org/>.
 *
 *
 */

package org.cip4.jdflib.auto;

import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.enums.ValuedEnum;
import org.apache.xerces.dom.CoreDocumentImpl;
import org.cip4.jdflib.core.AtrInfoTable;
import org.cip4.jdflib.core.AttributeInfo;
import org.cip4.jdflib.core.AttributeName;
import org.cip4.jdflib.core.ElemInfoTable;
import org.cip4.jdflib.core.ElementInfo;
import org.cip4.jdflib.core.ElementName;
import org.cip4.jdflib.core.JDFCoreConstants;
import org.cip4.jdflib.core.VString;
import org.cip4.jdflib.datatypes.JDFXYPair;
import org.cip4.jdflib.resource.JDFResource;
import org.cip4.jdflib.resource.process.JDFAssembly;

/**
 *****************************************************************************
 * class JDFAutoCutMark : public JDFResource
 *****************************************************************************
 * 
 */

public abstract class JDFAutoCutMark extends JDFResource
{

	private static final long serialVersionUID = 1L;

	private static AtrInfoTable[] atrInfoTable = new AtrInfoTable[3];
	static
	{
		atrInfoTable[0] = new AtrInfoTable(AttributeName.MARKTYPE, 0x22222222, AttributeInfo.EnumAttributeType.enumeration, EnumMarkType.getEnum(0), null);
		atrInfoTable[1] = new AtrInfoTable(AttributeName.POSITION, 0x22222222, AttributeInfo.EnumAttributeType.XYPair, null, null);
		atrInfoTable[2] = new AtrInfoTable(AttributeName.BLOCKS, 0x33333333, AttributeInfo.EnumAttributeType.NMTOKENS, null, null);
	}

	@Override
	protected AttributeInfo getTheAttributeInfo()
	{
		return super.getTheAttributeInfo().updateReplace(atrInfoTable);
	}

	private static ElemInfoTable[] elemInfoTable = new ElemInfoTable[1];
	static
	{
		elemInfoTable[0] = new ElemInfoTable(ElementName.ASSEMBLY, 0x33333333);
	}

	@Override
	protected ElementInfo getTheElementInfo()
	{
		return super.getTheElementInfo().updateReplace(elemInfoTable);
	}

	/**
	 * Constructor for JDFAutoCutMark
	 * 
	 * @param myOwnerDocument
	 * @param qualifiedName
	 */
	protected JDFAutoCutMark(CoreDocumentImpl myOwnerDocument, String qualifiedName)
	{
		super(myOwnerDocument, qualifiedName);
	}

	/**
	 * Constructor for JDFAutoCutMark
	 * 
	 * @param myOwnerDocument
	 * @param myNamespaceURI
	 * @param qualifiedName
	 */
	protected JDFAutoCutMark(CoreDocumentImpl myOwnerDocument, String myNamespaceURI, String qualifiedName)
	{
		super(myOwnerDocument, myNamespaceURI, qualifiedName);
	}

	/**
	 * Constructor for JDFAutoCutMark
	 * 
	 * @param myOwnerDocument
	 * @param myNamespaceURI
	 * @param qualifiedName
	 * @param myLocalName
	 */
	protected JDFAutoCutMark(CoreDocumentImpl myOwnerDocument, String myNamespaceURI, String qualifiedName, String myLocalName)
	{
		super(myOwnerDocument, myNamespaceURI, qualifiedName, myLocalName);
	}

	/**
	 * @return the string representation
	 */
	@Override
	public String toString()
	{
		return " JDFAutoCutMark[  --> " + super.toString() + " ]";
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
	 * Enumeration strings for MarkType
	 */

	@SuppressWarnings("rawtypes")
	public static class EnumMarkType extends ValuedEnum
	{
		private static final long serialVersionUID = 1L;
		private static int m_startValue = 0;

		private EnumMarkType(String name)
		{
			super(name, m_startValue++);
		}

		/**
		 * @param enumName the string to convert
		 * @return the enum
		 */
		public static EnumMarkType getEnum(String enumName)
		{
			return (EnumMarkType) getEnum(EnumMarkType.class, enumName);
		}

		/**
		 * @param enumValue the integer to convert
		 * @return the enum
		 */
		public static EnumMarkType getEnum(int enumValue)
		{
			return (EnumMarkType) getEnum(EnumMarkType.class, enumValue);
		}

		/**
		 * @return the map of enums
		 */
		public static Map getEnumMap()
		{
			return getEnumMap(EnumMarkType.class);
		}

		/**
		 * @return the list of enums
		 */
		public static List getEnumList()
		{
			return getEnumList(EnumMarkType.class);
		}

		/**
		 * @return the iterator
		 */
		public static Iterator iterator()
		{
			return iterator(EnumMarkType.class);
		}

		/**  */
		public static final EnumMarkType CrossCutMark = new EnumMarkType("CrossCutMark");
		/**  */
		public static final EnumMarkType TopVerticalCutMark = new EnumMarkType("TopVerticalCutMark");
		/**  */
		public static final EnumMarkType BottomVerticalCutMark = new EnumMarkType("BottomVerticalCutMark");
		/**  */
		public static final EnumMarkType LeftHorizontalCutMark = new EnumMarkType("LeftHorizontalCutMark");
		/**  */
		public static final EnumMarkType RightHorizontalCutMark = new EnumMarkType("RightHorizontalCutMark");
		/**  */
		public static final EnumMarkType LowerLeftCutMark = new EnumMarkType("LowerLeftCutMark");
		/**  */
		public static final EnumMarkType UpperLeftCutMark = new EnumMarkType("UpperLeftCutMark");
		/**  */
		public static final EnumMarkType LowerRightCutMark = new EnumMarkType("LowerRightCutMark");
		/**  */
		public static final EnumMarkType UpperRightCutMark = new EnumMarkType("UpperRightCutMark");
	}

	/*
	 * ************************************************************************ Attribute getter / setter ************************************************************************
	 */

	/*
	 * --------------------------------------------------------------------- Methods for Attribute MarkType ---------------------------------------------------------------------
	 */
	/**
	 * (5) set attribute MarkType
	 * 
	 * @param enumVar the enumVar to set the attribute to
	 */
	public void setMarkType(EnumMarkType enumVar)
	{
		setAttribute(AttributeName.MARKTYPE, enumVar == null ? null : enumVar.getName(), null);
	}

	/**
	 * (9) get attribute MarkType
	 * 
	 * @return the value of the attribute
	 */
	public EnumMarkType getMarkType()
	{
		return EnumMarkType.getEnum(getAttribute(AttributeName.MARKTYPE, null, null));
	}

	/*
	 * --------------------------------------------------------------------- Methods for Attribute Position ---------------------------------------------------------------------
	 */
	/**
	 * (36) set attribute Position
	 * 
	 * @param value the value to set the attribute to
	 */
	public void setPosition(JDFXYPair value)
	{
		setAttribute(AttributeName.POSITION, value, null);
	}

	/**
	 * (20) get JDFXYPair attribute Position
	 * 
	 * @return JDFXYPair the value of the attribute, null if a the attribute value is not a valid to create a JDFXYPair
	 */
	public JDFXYPair getPosition()
	{
		final String strAttrName = getAttribute(AttributeName.POSITION, null, null);
		final JDFXYPair nPlaceHolder = JDFXYPair.createXYPair(strAttrName);
		return nPlaceHolder;
	}

	/*
	 * --------------------------------------------------------------------- Methods for Attribute Blocks ---------------------------------------------------------------------
	 */
	/**
	 * (36) set attribute Blocks
	 * 
	 * @param value the value to set the attribute to
	 */
	public void setBlocks(VString value)
	{
		setAttribute(AttributeName.BLOCKS, value, null);
	}

	/**
	 * (21) get VString attribute Blocks
	 * 
	 * @return VString the value of the attribute
	 */
	public VString getBlocks()
	{
		final VString vStrAttrib = new VString();
		final String s = getAttribute(AttributeName.BLOCKS, null, JDFCoreConstants.EMPTYSTRING);
		vStrAttrib.setAllStrings(s, " ");
		return vStrAttrib;
	}

	/*
	 * *********************************************************************** Element getter / setter ***********************************************************************
	 */

	/**
	 * (26) getCreateAssembly
	 * 
	 * @param iSkip number of elements to skip
	 * @return JDFAssembly the element
	 */
	public JDFAssembly getCreateAssembly(int iSkip)
	{
		return (JDFAssembly) getCreateElement_JDFElement(ElementName.ASSEMBLY, null, iSkip);
	}

	/**
	 * (27) const get element Assembly
	 * 
	 * @param iSkip number of elements to skip
	 * @return JDFAssembly the element default is getAssembly(0)
	 */
	public JDFAssembly getAssembly(int iSkip)
	{
		return (JDFAssembly) getElement(ElementName.ASSEMBLY, null, iSkip);
	}

	/**
	 * Get all Assembly from the current element
	 * 
	 * @return Collection<JDFAssembly>, null if none are available
	 */
	public Collection<JDFAssembly> getAllAssembly()
	{
		return getChildrenByClass(JDFAssembly.class, false, 0);
	}

	/**
	 * (30) append element Assembly
	 * 
	 * @return JDFAssembly the element
	 */
	public JDFAssembly appendAssembly()
	{
		return (JDFAssembly) appendElement(ElementName.ASSEMBLY, null);
	}

	/**
	 * (31) create inter-resource link to refTarget
	 * 
	 * @param refTarget the element that is referenced
	 */
	public void refAssembly(JDFAssembly refTarget)
	{
		refElement(refTarget);
	}

}// end namespace JDF
