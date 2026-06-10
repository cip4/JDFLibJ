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

import java.util.Collection;

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
import org.cip4.jdflib.util.JavaEnumUtil;

/**
 ***************************************************************************** class JDFAutoShapeElement : public JDFResource
 */

public abstract class JDFAutoShapeElement extends JDFResource
{

	private static final long serialVersionUID = 1L;

	private static AtrInfoTable[] atrInfoTable = new AtrInfoTable[10];
	static
	{
		atrInfoTable[0] = new AtrInfoTable(AttributeName.LOCKORIGINS, 0x3333333333l, AttributeInfo.EnumAttributeType.boolean_, null, "false");
		atrInfoTable[1] = new AtrInfoTable(AttributeName.CUTBOX, 0x3333333333l, AttributeInfo.EnumAttributeType.rectangle, null, null);
		atrInfoTable[2] = new AtrInfoTable(AttributeName.CUTOUT, 0x3333333333l, AttributeInfo.EnumAttributeType.boolean_, null, "false");
		atrInfoTable[3] = new AtrInfoTable(AttributeName.CUTPATH, 0x3333333333l, AttributeInfo.EnumAttributeType.PDFPath, null, null);
		atrInfoTable[4] = new AtrInfoTable(AttributeName.CUTTYPE, 0x3333333333l, AttributeInfo.EnumAttributeType.enumeration,
				JavaEnumUtil.getEnum(EnumCutType.class, 0), "Cut");
		atrInfoTable[5] = new AtrInfoTable(AttributeName.DDESCUTTYPE, 0x3333333333l, AttributeInfo.EnumAttributeType.integer, null, "101");
		atrInfoTable[6] = new AtrInfoTable(AttributeName.MATERIAL, 0x3333333333l, AttributeInfo.EnumAttributeType.string, null, null);
		atrInfoTable[7] = new AtrInfoTable(AttributeName.SHAPEDEPTH, 0x3333333333l, AttributeInfo.EnumAttributeType.double_, null, null);
		atrInfoTable[8] = new AtrInfoTable(AttributeName.SHAPETYPE, 0x2222222222l, AttributeInfo.EnumAttributeType.enumeration,
				JavaEnumUtil.getEnum(EnumShapeType.class, 0), null);
		atrInfoTable[9] = new AtrInfoTable(AttributeName.TEETHPERDIMENSION, 0x3333333333l, AttributeInfo.EnumAttributeType.double_, null, null);
	}

	@Override
	protected AttributeInfo getTheAttributeInfo()
	{
		return super.getTheAttributeInfo().updateReplace(atrInfoTable);
	}

	private static ElemInfoTable[] elemInfoTable = new ElemInfoTable[1];
	static
	{
		elemInfoTable[0] = new ElemInfoTable(ElementName.SHAPE, 0x3333333333l);
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
	protected JDFAutoShapeElement(final CoreDocumentImpl myOwnerDocument, final String qualifiedName)
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
	protected JDFAutoShapeElement(final CoreDocumentImpl myOwnerDocument, final String myNamespaceURI, final String qualifiedName)
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
	protected JDFAutoShapeElement(final CoreDocumentImpl myOwnerDocument, final String myNamespaceURI, final String qualifiedName, final String myLocalName)
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
	 * Enumeration strings for numCutType
	 */

	public enum EnumCutType
	{
		Cut, Perforate;

		public static EnumCutType getEnum(final String val)
		{
			return JavaEnumUtil.getEnumIgnoreCase(EnumCutType.class, val, EnumCutType.Cut);
		}
	}

	/**
	 * Enumeration strings for numShapeType
	 */

	public enum EnumShapeType
	{
		Rectangular, Round, Path, RoundedRectangle;

		public static EnumShapeType getEnum(final String val)
		{
			return JavaEnumUtil.getEnumIgnoreCase(EnumShapeType.class, val, null);
		}
	}/*
		 * ************************************************************************
		 * Attribute getter / setter
		 * ************************************************************************
		 */

	/*
	 * ---------------------------------------------------------------------
	 * Methods for Attribute LockOrigins
	 * ---------------------------------------------------------------------
	 */
	/**
	 * (36) set attribute LockOrigins
	 *
	 * @param value the value to set the attribute to
	 */
	public void setLockOrigins(final boolean value)
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
	 * ---------------------------------------------------------------------
	 * Methods for Attribute CutBox
	 * ---------------------------------------------------------------------
	 */
	/**
	 * (36) set attribute CutBox
	 *
	 * @param value the value to set the attribute to
	 */
	public void setCutBox(final JDFRectangle value)
	{
		setAttribute(AttributeName.CUTBOX, value, null);
	}

	/**
	 * (20) get JDFRectangle attribute CutBox
	 *
	 * @return JDFRectangle the value of the attribute, null if a the
	 *         attribute value is not a valid to create a JDFRectangle
	 */
	public JDFRectangle getCutBox()
	{
		final String strAttrName = getAttribute(AttributeName.CUTBOX, null, null);
		final JDFRectangle nPlaceHolder = JDFRectangle.createRectangle(strAttrName);
		return nPlaceHolder;
	}

	/*
	 * ---------------------------------------------------------------------
	 * Methods for Attribute CutOut
	 * ---------------------------------------------------------------------
	 */
	/**
	 * (36) set attribute CutOut
	 *
	 * @param value the value to set the attribute to
	 */
	public void setCutOut(final boolean value)
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
	 * ---------------------------------------------------------------------
	 * Methods for Attribute CutPath
	 * ---------------------------------------------------------------------
	 */
	/**
	 * (36) set attribute CutPath
	 *
	 * @param value the value to set the attribute to
	 */
	public void setCutPath(final String value)
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
	 * ---------------------------------------------------------------------
	 * Methods for Attribute CutType
	 * ---------------------------------------------------------------------
	 */
	/**
	 * (5) set attribute CutType
	 *
	 * @param enumVar the enumVar to set the attribute to
	 */
	public void setCutType(final EnumCutType enumVar)
	{
		setAttribute(AttributeName.CUTTYPE, JavaEnumUtil.getName(enumVar), null);
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
	 * ---------------------------------------------------------------------
	 * Methods for Attribute DDESCutType
	 * ---------------------------------------------------------------------
	 */
	/**
	 * (36) set attribute DDESCutType
	 *
	 * @param value the value to set the attribute to
	 */
	public void setDDESCutType(final int value)
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
	 * ---------------------------------------------------------------------
	 * Methods for Attribute Material
	 * ---------------------------------------------------------------------
	 */
	/**
	 * (36) set attribute Material
	 *
	 * @param value the value to set the attribute to
	 */
	public void setMaterial(final String value)
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
	 * ---------------------------------------------------------------------
	 * Methods for Attribute ShapeDepth
	 * ---------------------------------------------------------------------
	 */
	/**
	 * (36) set attribute ShapeDepth
	 *
	 * @param value the value to set the attribute to
	 */
	public void setShapeDepth(final double value)
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
	 * ---------------------------------------------------------------------
	 * Methods for Attribute ShapeType
	 * ---------------------------------------------------------------------
	 */
	/**
	 * (5) set attribute ShapeType
	 *
	 * @param enumVar the enumVar to set the attribute to
	 */
	public void setShapeType(final EnumShapeType enumVar)
	{
		setAttribute(AttributeName.SHAPETYPE, JavaEnumUtil.getName(enumVar), null);
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
	 * ---------------------------------------------------------------------
	 * Methods for Attribute TeethPerDimension
	 * ---------------------------------------------------------------------
	 */
	/**
	 * (36) set attribute TeethPerDimension
	 *
	 * @param value the value to set the attribute to
	 */
	public void setTeethPerDimension(final double value)
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
	 * ***********************************************************************
	 * Element getter / setter
	 * ***********************************************************************
	 */

	/**
	 * (24) const get element Shape
	 *
	 * @return JDFShapeElement the element
	 */
	public JDFShapeElement getShape()
	{
		return (JDFShapeElement) getElement(ElementName.SHAPE, null, 0);
	}

	/**
	 * (25) getCreateShape
	 * 
	 * @return JDFShapeElement the element
	 */
	public JDFShapeElement getCreateShape()
	{
		return (JDFShapeElement) getCreateElement_JDFElement(ElementName.SHAPE, null, 0);
	}

	/**
	 * (26) getCreateShape
	 * 
	 * @param iSkip number of elements to skip
	 * @return JDFShapeElement the element
	 */
	public JDFShapeElement getCreateShape(final int iSkip)
	{
		return (JDFShapeElement) getCreateElement_JDFElement(ElementName.SHAPE, null, iSkip);
	}

	/**
	 * (27) const get element Shape
	 *
	 * @param iSkip number of elements to skip
	 * @return JDFShapeElement the element
	 *         default is getShape(0)
	 */
	public JDFShapeElement getShape(final int iSkip)
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
		return getChildArrayByClass(JDFShapeElement.class, false, 0);
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

}
