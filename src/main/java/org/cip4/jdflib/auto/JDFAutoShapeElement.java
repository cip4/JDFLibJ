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
import org.cip4.jdflib.datatypes.JDFRectangle;
import org.cip4.jdflib.resource.JDFResource;
import org.cip4.jdflib.resource.JDFShapeElement;

/**
 *****************************************************************************
 * class JDFAutoShapeElement : public JDFResource
 *****************************************************************************
 * 
 */

public abstract class JDFAutoShapeElement extends JDFResource
{

	private static final long serialVersionUID = 1L;

	private static AtrInfoTable[] atrInfoTable = new AtrInfoTable[10];
	static
	{
		atrInfoTable[0] = new AtrInfoTable(AttributeName.LOCKORIGINS, 0x33333333, AttributeInfo.EnumAttributeType.boolean_, null, "false");
		atrInfoTable[1] = new AtrInfoTable(AttributeName.CUTBOX, 0x33333333, AttributeInfo.EnumAttributeType.rectangle, null, null);
		atrInfoTable[2] = new AtrInfoTable(AttributeName.CUTOUT, 0x33333333, AttributeInfo.EnumAttributeType.boolean_, null, "false");
		atrInfoTable[3] = new AtrInfoTable(AttributeName.CUTPATH, 0x33333333, AttributeInfo.EnumAttributeType.PDFPath, null, null);
		atrInfoTable[4] = new AtrInfoTable(AttributeName.CUTTYPE, 0x33333333, AttributeInfo.EnumAttributeType.enumeration, EnumCutType.getEnum(0), "Cut");
		atrInfoTable[5] = new AtrInfoTable(AttributeName.DDESCUTTYPE, 0x33333333, AttributeInfo.EnumAttributeType.integer, null, "101");
		atrInfoTable[6] = new AtrInfoTable(AttributeName.MATERIAL, 0x33333333, AttributeInfo.EnumAttributeType.string, null, null);
		atrInfoTable[7] = new AtrInfoTable(AttributeName.SHAPEDEPTH, 0x33333333, AttributeInfo.EnumAttributeType.double_, null, null);
		atrInfoTable[8] = new AtrInfoTable(AttributeName.SHAPETYPE, 0x22222222, AttributeInfo.EnumAttributeType.enumeration, EnumShapeType.getEnum(0), null);
		atrInfoTable[9] = new AtrInfoTable(AttributeName.TEETHPERDIMENSION, 0x33333333, AttributeInfo.EnumAttributeType.double_, null, null);
	}

	@Override
	protected AttributeInfo getTheAttributeInfo()
	{
		return super.getTheAttributeInfo().updateReplace(atrInfoTable);
	}

	private static ElemInfoTable[] elemInfoTable = new ElemInfoTable[1];
	static
	{
		elemInfoTable[0] = new ElemInfoTable(ElementName.SHAPE, 0x33333333);
	}

	@Override
	protected ElementInfo getTheElementInfo()
	{
		return super.getTheElementInfo().updateReplace(elemInfoTable);
	}

	/**
	 * Constructor for JDFAutoShapeElement
	 * 
	 * @param myOwnerDocument
	 * @param qualifiedName
	 */
	protected JDFAutoShapeElement(CoreDocumentImpl myOwnerDocument, String qualifiedName)
	{
		super(myOwnerDocument, qualifiedName);
	}

	/**
	 * Constructor for JDFAutoShapeElement
	 * 
	 * @param myOwnerDocument
	 * @param myNamespaceURI
	 * @param qualifiedName
	 */
	protected JDFAutoShapeElement(CoreDocumentImpl myOwnerDocument, String myNamespaceURI, String qualifiedName)
	{
		super(myOwnerDocument, myNamespaceURI, qualifiedName);
	}

	/**
	 * Constructor for JDFAutoShapeElement
	 * 
	 * @param myOwnerDocument
	 * @param myNamespaceURI
	 * @param qualifiedName
	 * @param myLocalName
	 */
	protected JDFAutoShapeElement(CoreDocumentImpl myOwnerDocument, String myNamespaceURI, String qualifiedName, String myLocalName)
	{
		super(myOwnerDocument, myNamespaceURI, qualifiedName, myLocalName);
	}

	/**
	 * @return the string representation
	 */
	@Override
	public String toString()
	{
		return " JDFAutoShapeElement[  --> " + super.toString() + " ]";
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
	 * Enumeration strings for CutType
	 */

	@SuppressWarnings("rawtypes")
	public static class EnumCutType extends ValuedEnum
	{
		private static final long serialVersionUID = 1L;
		private static int m_startValue = 0;

		private EnumCutType(String name)
		{
			super(name, m_startValue++);
		}

		/**
		 * @param enumName the string to convert
		 * @return the enum
		 */
		public static EnumCutType getEnum(String enumName)
		{
			return (EnumCutType) getEnum(EnumCutType.class, enumName);
		}

		/**
		 * @param enumValue the integer to convert
		 * @return the enum
		 */
		public static EnumCutType getEnum(int enumValue)
		{
			return (EnumCutType) getEnum(EnumCutType.class, enumValue);
		}

		/**
		 * @return the map of enums
		 */
		public static Map getEnumMap()
		{
			return getEnumMap(EnumCutType.class);
		}

		/**
		 * @return the list of enums
		 */
		public static List getEnumList()
		{
			return getEnumList(EnumCutType.class);
		}

		/**
		 * @return the iterator
		 */
		public static Iterator iterator()
		{
			return iterator(EnumCutType.class);
		}

		/**  */
		public static final EnumCutType Cut = new EnumCutType("Cut");
		/**  */
		public static final EnumCutType Perforate = new EnumCutType("Perforate");
	}

	/**
	 * Enumeration strings for ShapeType
	 */

	@SuppressWarnings("rawtypes")
	public static class EnumShapeType extends ValuedEnum
	{
		private static final long serialVersionUID = 1L;
		private static int m_startValue = 0;

		private EnumShapeType(String name)
		{
			super(name, m_startValue++);
		}

		/**
		 * @param enumName the string to convert
		 * @return the enum
		 */
		public static EnumShapeType getEnum(String enumName)
		{
			return (EnumShapeType) getEnum(EnumShapeType.class, enumName);
		}

		/**
		 * @param enumValue the integer to convert
		 * @return the enum
		 */
		public static EnumShapeType getEnum(int enumValue)
		{
			return (EnumShapeType) getEnum(EnumShapeType.class, enumValue);
		}

		/**
		 * @return the map of enums
		 */
		public static Map getEnumMap()
		{
			return getEnumMap(EnumShapeType.class);
		}

		/**
		 * @return the list of enums
		 */
		public static List getEnumList()
		{
			return getEnumList(EnumShapeType.class);
		}

		/**
		 * @return the iterator
		 */
		public static Iterator iterator()
		{
			return iterator(EnumShapeType.class);
		}

		/**  */
		public static final EnumShapeType Rectangular = new EnumShapeType("Rectangular");
		/**  */
		public static final EnumShapeType Round = new EnumShapeType("Round");
		/**  */
		public static final EnumShapeType Path = new EnumShapeType("Path");
		/**  */
		public static final EnumShapeType RoundedRectangle = new EnumShapeType("RoundedRectangle");
	}

	/*
	 * ************************************************************************ Attribute getter / setter ************************************************************************
	 */

	/*
	 * --------------------------------------------------------------------- Methods for Attribute LockOrigins ---------------------------------------------------------------------
	 */
	/**
	 * (36) set attribute LockOrigins
	 * 
	 * @param value the value to set the attribute to
	 */
	public void setLockOrigins(boolean value)
	{
		setAttribute(AttributeName.LOCKORIGINS, value, null);
	}

	/**
	 * (18) get boolean attribute LockOrigins
	 * 
	 * @return boolean the value of the attribute
	 */
	public boolean getLockOrigins()
	{
		return getBoolAttribute(AttributeName.LOCKORIGINS, null, false);
	}

	/*
	 * --------------------------------------------------------------------- Methods for Attribute CutBox ---------------------------------------------------------------------
	 */
	/**
	 * (36) set attribute CutBox
	 * 
	 * @param value the value to set the attribute to
	 */
	public void setCutBox(JDFRectangle value)
	{
		setAttribute(AttributeName.CUTBOX, value, null);
	}

	/**
	 * (20) get JDFRectangle attribute CutBox
	 * 
	 * @return JDFRectangle the value of the attribute, null if a the attribute value is not a valid to create a JDFRectangle
	 */
	public JDFRectangle getCutBox()
	{
		final String strAttrName = getAttribute(AttributeName.CUTBOX, null, null);
		final JDFRectangle nPlaceHolder = JDFRectangle.createRectangle(strAttrName);
		return nPlaceHolder;
	}

	/*
	 * --------------------------------------------------------------------- Methods for Attribute CutOut ---------------------------------------------------------------------
	 */
	/**
	 * (36) set attribute CutOut
	 * 
	 * @param value the value to set the attribute to
	 */
	public void setCutOut(boolean value)
	{
		setAttribute(AttributeName.CUTOUT, value, null);
	}

	/**
	 * (18) get boolean attribute CutOut
	 * 
	 * @return boolean the value of the attribute
	 */
	public boolean getCutOut()
	{
		return getBoolAttribute(AttributeName.CUTOUT, null, false);
	}

	/*
	 * --------------------------------------------------------------------- Methods for Attribute CutPath ---------------------------------------------------------------------
	 */
	/**
	 * (36) set attribute CutPath
	 * 
	 * @param value the value to set the attribute to
	 */
	public void setCutPath(String value)
	{
		setAttribute(AttributeName.CUTPATH, value, null);
	}

	/**
	 * (23) get String attribute CutPath
	 * 
	 * @return the value of the attribute
	 */
	public String getCutPath()
	{
		return getAttribute(AttributeName.CUTPATH, null, JDFCoreConstants.EMPTYSTRING);
	}

	/*
	 * --------------------------------------------------------------------- Methods for Attribute CutType ---------------------------------------------------------------------
	 */
	/**
	 * (5) set attribute CutType
	 * 
	 * @param enumVar the enumVar to set the attribute to
	 */
	public void setCutType(EnumCutType enumVar)
	{
		setAttribute(AttributeName.CUTTYPE, enumVar == null ? null : enumVar.getName(), null);
	}

	/**
	 * (9) get attribute CutType
	 * 
	 * @return the value of the attribute
	 */
	public EnumCutType getCutType()
	{
		return EnumCutType.getEnum(getAttribute(AttributeName.CUTTYPE, null, "Cut"));
	}

	/*
	 * --------------------------------------------------------------------- Methods for Attribute DDESCutType ---------------------------------------------------------------------
	 */
	/**
	 * (36) set attribute DDESCutType
	 * 
	 * @param value the value to set the attribute to
	 */
	public void setDDESCutType(int value)
	{
		setAttribute(AttributeName.DDESCUTTYPE, value, null);
	}

	/**
	 * (15) get int attribute DDESCutType
	 * 
	 * @return int the value of the attribute
	 */
	public int getDDESCutType()
	{
		return getIntAttribute(AttributeName.DDESCUTTYPE, null, 101);
	}

	/*
	 * --------------------------------------------------------------------- Methods for Attribute Material ---------------------------------------------------------------------
	 */
	/**
	 * (36) set attribute Material
	 * 
	 * @param value the value to set the attribute to
	 */
	public void setMaterial(String value)
	{
		setAttribute(AttributeName.MATERIAL, value, null);
	}

	/**
	 * (23) get String attribute Material
	 * 
	 * @return the value of the attribute
	 */
	public String getMaterial()
	{
		return getAttribute(AttributeName.MATERIAL, null, JDFCoreConstants.EMPTYSTRING);
	}

	/*
	 * --------------------------------------------------------------------- Methods for Attribute ShapeDepth ---------------------------------------------------------------------
	 */
	/**
	 * (36) set attribute ShapeDepth
	 * 
	 * @param value the value to set the attribute to
	 */
	public void setShapeDepth(double value)
	{
		setAttribute(AttributeName.SHAPEDEPTH, value, null);
	}

	/**
	 * (17) get double attribute ShapeDepth
	 * 
	 * @return double the value of the attribute
	 */
	public double getShapeDepth()
	{
		return getRealAttribute(AttributeName.SHAPEDEPTH, null, 0.0);
	}

	/*
	 * --------------------------------------------------------------------- Methods for Attribute ShapeType ---------------------------------------------------------------------
	 */
	/**
	 * (5) set attribute ShapeType
	 * 
	 * @param enumVar the enumVar to set the attribute to
	 */
	public void setShapeType(EnumShapeType enumVar)
	{
		setAttribute(AttributeName.SHAPETYPE, enumVar == null ? null : enumVar.getName(), null);
	}

	/**
	 * (9) get attribute ShapeType
	 * 
	 * @return the value of the attribute
	 */
	public EnumShapeType getShapeType()
	{
		return EnumShapeType.getEnum(getAttribute(AttributeName.SHAPETYPE, null, null));
	}

	/*
	 * --------------------------------------------------------------------- Methods for Attribute TeethPerDimension ---------------------------------------------------------------------
	 */
	/**
	 * (36) set attribute TeethPerDimension
	 * 
	 * @param value the value to set the attribute to
	 */
	public void setTeethPerDimension(double value)
	{
		setAttribute(AttributeName.TEETHPERDIMENSION, value, null);
	}

	/**
	 * (17) get double attribute TeethPerDimension
	 * 
	 * @return double the value of the attribute
	 */
	public double getTeethPerDimension()
	{
		return getRealAttribute(AttributeName.TEETHPERDIMENSION, null, 0.0);
	}

	/*
	 * *********************************************************************** Element getter / setter ***********************************************************************
	 */

	/**
	 * (26) getCreateShape
	 * 
	 * @param iSkip number of elements to skip
	 * @return JDFShapeElement the element
	 */
	public JDFShapeElement getCreateShape(int iSkip)
	{
		return (JDFShapeElement) getCreateElement_KElement(ElementName.SHAPE, null, iSkip);
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
