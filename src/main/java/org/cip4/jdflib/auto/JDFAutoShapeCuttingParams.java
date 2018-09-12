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
import org.cip4.jdflib.core.JDFException;
import org.cip4.jdflib.resource.JDFResource;
import org.cip4.jdflib.resource.JDFShapeElement;
import org.cip4.jdflib.resource.process.JDFDieLayout;

/**
 *****************************************************************************
 * class JDFAutoShapeCuttingParams : public JDFResource
 *****************************************************************************
 * 
 */

public abstract class JDFAutoShapeCuttingParams extends JDFResource
{

	private static final long serialVersionUID = 1L;

	private static AtrInfoTable[] atrInfoTable = new AtrInfoTable[3];
	static
	{
		atrInfoTable[0] = new AtrInfoTable(AttributeName.DELIVERYMODE, 0x33333111, AttributeInfo.EnumAttributeType.enumeration, EnumDeliveryMode.getEnum(0), null);
		atrInfoTable[1] = new AtrInfoTable(AttributeName.MODULEINDEX, 0x33331111, AttributeInfo.EnumAttributeType.integer, null, null);
		atrInfoTable[2] = new AtrInfoTable(AttributeName.SHEETLAY, 0x33333111, AttributeInfo.EnumAttributeType.enumeration, EnumSheetLay.getEnum(0), null);
	}

	@Override
	protected AttributeInfo getTheAttributeInfo()
	{
		return super.getTheAttributeInfo().updateReplace(atrInfoTable);
	}

	private static ElemInfoTable[] elemInfoTable = new ElemInfoTable[2];
	static
	{
		elemInfoTable[0] = new ElemInfoTable(ElementName.DIELAYOUT, 0x66666661);
		elemInfoTable[1] = new ElemInfoTable(ElementName.SHAPE, 0x33333331);
	}

	@Override
	protected ElementInfo getTheElementInfo()
	{
		return super.getTheElementInfo().updateReplace(elemInfoTable);
	}

	/**
	 * Constructor for JDFAutoShapeCuttingParams
	 * 
	 * @param myOwnerDocument
	 * @param qualifiedName
	 */
	protected JDFAutoShapeCuttingParams(CoreDocumentImpl myOwnerDocument, String qualifiedName)
	{
		super(myOwnerDocument, qualifiedName);
	}

	/**
	 * Constructor for JDFAutoShapeCuttingParams
	 * 
	 * @param myOwnerDocument
	 * @param myNamespaceURI
	 * @param qualifiedName
	 */
	protected JDFAutoShapeCuttingParams(CoreDocumentImpl myOwnerDocument, String myNamespaceURI, String qualifiedName)
	{
		super(myOwnerDocument, myNamespaceURI, qualifiedName);
	}

	/**
	 * Constructor for JDFAutoShapeCuttingParams
	 * 
	 * @param myOwnerDocument
	 * @param myNamespaceURI
	 * @param qualifiedName
	 * @param myLocalName
	 */
	protected JDFAutoShapeCuttingParams(CoreDocumentImpl myOwnerDocument, String myNamespaceURI, String qualifiedName, String myLocalName)
	{
		super(myOwnerDocument, myNamespaceURI, qualifiedName, myLocalName);
	}

	/**
	 * @return the string representation
	 */
	@Override
	public String toString()
	{
		return " JDFAutoShapeCuttingParams[  --> " + super.toString() + " ]";
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
	 * Enumeration strings for DeliveryMode
	 */

	@SuppressWarnings("rawtypes")
	public static class EnumDeliveryMode extends ValuedEnum
	{
		private static final long serialVersionUID = 1L;
		private static int m_startValue = 0;

		private EnumDeliveryMode(String name)
		{
			super(name, m_startValue++);
		}

		/**
		 * @param enumName the string to convert
		 * @return the enum
		 */
		public static EnumDeliveryMode getEnum(String enumName)
		{
			return (EnumDeliveryMode) getEnum(EnumDeliveryMode.class, enumName);
		}

		/**
		 * @param enumValue the integer to convert
		 * @return the enum
		 */
		public static EnumDeliveryMode getEnum(int enumValue)
		{
			return (EnumDeliveryMode) getEnum(EnumDeliveryMode.class, enumValue);
		}

		/**
		 * @return the map of enums
		 */
		public static Map getEnumMap()
		{
			return getEnumMap(EnumDeliveryMode.class);
		}

		/**
		 * @return the list of enums
		 */
		public static List getEnumList()
		{
			return getEnumList(EnumDeliveryMode.class);
		}

		/**
		 * @return the iterator
		 */
		public static Iterator iterator()
		{
			return iterator(EnumDeliveryMode.class);
		}

		/**  */
		public static final EnumDeliveryMode FullSheet = new EnumDeliveryMode("FullSheet");
		/**  */
		public static final EnumDeliveryMode RemoveGripperMargin = new EnumDeliveryMode("RemoveGripperMargin");
		/**  */
		public static final EnumDeliveryMode SeparateBlanks = new EnumDeliveryMode("SeparateBlanks");
	}

	/**
	 * Enumeration strings for SheetLay
	 */

	@SuppressWarnings("rawtypes")
	public static class EnumSheetLay extends ValuedEnum
	{
		private static final long serialVersionUID = 1L;
		private static int m_startValue = 0;

		private EnumSheetLay(String name)
		{
			super(name, m_startValue++);
		}

		/**
		 * @param enumName the string to convert
		 * @return the enum
		 */
		public static EnumSheetLay getEnum(String enumName)
		{
			return (EnumSheetLay) getEnum(EnumSheetLay.class, enumName);
		}

		/**
		 * @param enumValue the integer to convert
		 * @return the enum
		 */
		public static EnumSheetLay getEnum(int enumValue)
		{
			return (EnumSheetLay) getEnum(EnumSheetLay.class, enumValue);
		}

		/**
		 * @return the map of enums
		 */
		public static Map getEnumMap()
		{
			return getEnumMap(EnumSheetLay.class);
		}

		/**
		 * @return the list of enums
		 */
		public static List getEnumList()
		{
			return getEnumList(EnumSheetLay.class);
		}

		/**
		 * @return the iterator
		 */
		public static Iterator iterator()
		{
			return iterator(EnumSheetLay.class);
		}

		/**  */
		public static final EnumSheetLay Left = new EnumSheetLay("Left");
		/**  */
		public static final EnumSheetLay Right = new EnumSheetLay("Right");
		/**  */
		public static final EnumSheetLay Center = new EnumSheetLay("Center");
	}

	/*
	 * ************************************************************************ Attribute getter / setter ************************************************************************
	 */

	/*
	 * --------------------------------------------------------------------- Methods for Attribute DeliveryMode ---------------------------------------------------------------------
	 */
	/**
	 * (5) set attribute DeliveryMode
	 * 
	 * @param enumVar the enumVar to set the attribute to
	 */
	public void setDeliveryMode(EnumDeliveryMode enumVar)
	{
		setAttribute(AttributeName.DELIVERYMODE, enumVar == null ? null : enumVar.getName(), null);
	}

	/**
	 * (9) get attribute DeliveryMode
	 * 
	 * @return the value of the attribute
	 */
	public EnumDeliveryMode getDeliveryMode()
	{
		return EnumDeliveryMode.getEnum(getAttribute(AttributeName.DELIVERYMODE, null, null));
	}

	/*
	 * --------------------------------------------------------------------- Methods for Attribute ModuleIndex ---------------------------------------------------------------------
	 */
	/**
	 * (36) set attribute ModuleIndex
	 * 
	 * @param value the value to set the attribute to
	 */
	public void setModuleIndex(int value)
	{
		setAttribute(AttributeName.MODULEINDEX, value, null);
	}

	/**
	 * (15) get int attribute ModuleIndex
	 * 
	 * @return int the value of the attribute
	 */
	public int getModuleIndex()
	{
		return getIntAttribute(AttributeName.MODULEINDEX, null, 0);
	}

	/*
	 * --------------------------------------------------------------------- Methods for Attribute SheetLay ---------------------------------------------------------------------
	 */
	/**
	 * (5) set attribute SheetLay
	 * 
	 * @param enumVar the enumVar to set the attribute to
	 */
	public void setSheetLay(EnumSheetLay enumVar)
	{
		setAttribute(AttributeName.SHEETLAY, enumVar == null ? null : enumVar.getName(), null);
	}

	/**
	 * (9) get attribute SheetLay
	 * 
	 * @return the value of the attribute
	 */
	public EnumSheetLay getSheetLay()
	{
		return EnumSheetLay.getEnum(getAttribute(AttributeName.SHEETLAY, null, null));
	}

	/*
	 * *********************************************************************** Element getter / setter ***********************************************************************
	 */

	/**
	 * (24) const get element DieLayout
	 * 
	 * @return JDFDieLayout the element
	 */
	public JDFDieLayout getDieLayout()
	{
		return (JDFDieLayout) getElement(ElementName.DIELAYOUT, null, 0);
	}

	/**
	 * (25) getCreateDieLayout
	 * 
	 * @return JDFDieLayout the element
	 */
	public JDFDieLayout getCreateDieLayout()
	{
		return (JDFDieLayout) getCreateElement_JDFElement(ElementName.DIELAYOUT, null, 0);
	}

	/**
	 * (29) append element DieLayout
	 * 
	 * @return JDFDieLayout the element
	 * @throws JDFException if the element already exists
	 */
	public JDFDieLayout appendDieLayout() throws JDFException
	{
		return (JDFDieLayout) appendElementN(ElementName.DIELAYOUT, 1, null);
	}

	/**
	 * (31) create inter-resource link to refTarget
	 * 
	 * @param refTarget the element that is referenced
	 */
	public void refDieLayout(JDFDieLayout refTarget)
	{
		refElement(refTarget);
	}

	/**
	 * (26) getCreateShape
	 * 
	 * @param iSkip number of elements to skip
	 * @return JDFShapeElement the element
	 */
	public JDFShapeElement getCreateShape(int iSkip)
	{
		return (JDFShapeElement) getCreateElement_JDFElement(ElementName.SHAPE, null, iSkip);
	}

	/**
	 * (27) const get element Shape
	 * 
	 * @param iSkip number of elements to skip
	 * @return JDFShapeElement the element default is getShape(0)
	 */
	public JDFShapeElement getShape(int iSkip)
	{
		return (JDFShapeElement) getElement(ElementName.SHAPE, null, iSkip);
	}

	/**
	 * Get all Shape from the current element
	 * 
	 * @return Collection<JDFShapeElement>, null if none are available
	 */
	public Collection<JDFShapeElement> getAllShape()
	{
		return getChildrenByClass(JDFShapeElement.class, false, 0);
	}

	/**
	 * (30) append element Shape
	 * 
	 * @return JDFShapeElement the element
	 */
	public JDFShapeElement appendShape()
	{
		return (JDFShapeElement) appendElement(ElementName.SHAPE, null);
	}

}// end namespace JDF
