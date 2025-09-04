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
import org.cip4.jdflib.core.ElemInfoTable;
import org.cip4.jdflib.core.ElementInfo;
import org.cip4.jdflib.core.ElementName;
import org.cip4.jdflib.core.JDFCoreConstants;
import org.cip4.jdflib.resource.JDFResource;
import org.cip4.jdflib.resource.process.postpress.JDFGlueLine;

/**
 ***************************************************************************** class JDFAutoCaseMakingParams : public JDFResource
 */

public abstract class JDFAutoCaseMakingParams extends JDFResource
{

	private static final long serialVersionUID = 1L;

	private static AtrInfoTable[] atrInfoTable = new AtrInfoTable[8];
	static
	{
		atrInfoTable[0] = new AtrInfoTable(AttributeName.BOTTOMFOLDIN, 0x3333333331l, AttributeInfo.EnumAttributeType.double_, null, null);
		atrInfoTable[1] = new AtrInfoTable(AttributeName.COVERWIDTH, 0x3333333331l, AttributeInfo.EnumAttributeType.double_, null, null);
		atrInfoTable[2] = new AtrInfoTable(AttributeName.CORNERTYPE, 0x3333333331l, AttributeInfo.EnumAttributeType.NMTOKEN, null, null);
		atrInfoTable[3] = new AtrInfoTable(AttributeName.FRONTFOLDIN, 0x3333333331l, AttributeInfo.EnumAttributeType.double_, null, null);
		atrInfoTable[4] = new AtrInfoTable(AttributeName.TOPFOLDIN, 0x3333333331l, AttributeInfo.EnumAttributeType.double_, null, null);
		atrInfoTable[5] = new AtrInfoTable(AttributeName.HEIGHT, 0x3333333331l, AttributeInfo.EnumAttributeType.double_, null, null);
		atrInfoTable[6] = new AtrInfoTable(AttributeName.JOINTWIDTH, 0x3333333331l, AttributeInfo.EnumAttributeType.double_, null, null);
		atrInfoTable[7] = new AtrInfoTable(AttributeName.SPINEWIDTH, 0x3333333331l, AttributeInfo.EnumAttributeType.double_, null, null);
	}

	@Override
	protected AttributeInfo getTheAttributeInfo()
	{
		return super.getTheAttributeInfo().updateReplace(atrInfoTable);
	}

	private static ElemInfoTable[] elemInfoTable = new ElemInfoTable[1];
	static
	{
		elemInfoTable[0] = new ElemInfoTable(ElementName.GLUELINE, 0x6666666661l);
	}

	@Override
	protected ElementInfo getTheElementInfo()
	{
		return super.getTheElementInfo().updateReplace(elemInfoTable);
	}

	/**
	 * Constructor for JDFAutoCaseMakingParams
	 *
	 * @param myOwnerDocument
	 * @param qualifiedName
	 */
	protected JDFAutoCaseMakingParams(CoreDocumentImpl myOwnerDocument, String qualifiedName)
	{
		super(myOwnerDocument, qualifiedName);
	}

	/**
	 * Constructor for JDFAutoCaseMakingParams
	 *
	 * @param myOwnerDocument
	 * @param myNamespaceURI
	 * @param qualifiedName
	 */
	protected JDFAutoCaseMakingParams(CoreDocumentImpl myOwnerDocument, String myNamespaceURI, String qualifiedName)
	{
		super(myOwnerDocument, myNamespaceURI, qualifiedName);
	}

	/**
	 * Constructor for JDFAutoCaseMakingParams
	 *
	 * @param myOwnerDocument
	 * @param myNamespaceURI
	 * @param qualifiedName
	 * @param myLocalName
	 */
	protected JDFAutoCaseMakingParams(CoreDocumentImpl myOwnerDocument, String myNamespaceURI, String qualifiedName, String myLocalName)
	{
		super(myOwnerDocument, myNamespaceURI, qualifiedName, myLocalName);
	}

	/**
	 * @return true if ok
	 */
	@Override
	public boolean init()
	{
		boolean bRet = super.init();
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

	/*
	 * ************************************************************************
	 * Attribute getter / setter
	 * ************************************************************************
	 */

	/*
	 * ---------------------------------------------------------------------
	 * Methods for Attribute BottomFoldIn
	 * ---------------------------------------------------------------------
	 */
	/**
	 * (36) set attribute BottomFoldIn
	 *
	 * @param value the value to set the attribute to
	 */
	public void setBottomFoldIn(double value)
	{
		setAttribute(AttributeName.BOTTOMFOLDIN, value, null);
	}

	/**
	 * (17) get double attribute BottomFoldIn
	 *
	 * @return double the value of the attribute
	 */
	public double getBottomFoldIn()
	{
		return getRealAttribute(AttributeName.BOTTOMFOLDIN, null, 0.0);
	}

	/*
	 * ---------------------------------------------------------------------
	 * Methods for Attribute CoverWidth
	 * ---------------------------------------------------------------------
	 */
	/**
	 * (36) set attribute CoverWidth
	 *
	 * @param value the value to set the attribute to
	 */
	public void setCoverWidth(double value)
	{
		setAttribute(AttributeName.COVERWIDTH, value, null);
	}

	/**
	 * (17) get double attribute CoverWidth
	 *
	 * @return double the value of the attribute
	 */
	public double getCoverWidth()
	{
		return getRealAttribute(AttributeName.COVERWIDTH, null, 0.0);
	}

	/*
	 * ---------------------------------------------------------------------
	 * Methods for Attribute CornerType
	 * ---------------------------------------------------------------------
	 */
	/**
	 * (36) set attribute CornerType
	 *
	 * @param value the value to set the attribute to
	 */
	public void setCornerType(String value)
	{
		setAttribute(AttributeName.CORNERTYPE, value, null);
	}

	/**
	 * (23) get String attribute CornerType
	 *
	 * @return the value of the attribute
	 */
	public String getCornerType()
	{
		return getAttribute(AttributeName.CORNERTYPE, null, JDFCoreConstants.EMPTYSTRING);
	}

	/*
	 * ---------------------------------------------------------------------
	 * Methods for Attribute FrontFoldIn
	 * ---------------------------------------------------------------------
	 */
	/**
	 * (36) set attribute FrontFoldIn
	 *
	 * @param value the value to set the attribute to
	 */
	public void setFrontFoldIn(double value)
	{
		setAttribute(AttributeName.FRONTFOLDIN, value, null);
	}

	/**
	 * (17) get double attribute FrontFoldIn
	 *
	 * @return double the value of the attribute
	 */
	public double getFrontFoldIn()
	{
		return getRealAttribute(AttributeName.FRONTFOLDIN, null, 0.0);
	}

	/*
	 * ---------------------------------------------------------------------
	 * Methods for Attribute TopFoldIn
	 * ---------------------------------------------------------------------
	 */
	/**
	 * (36) set attribute TopFoldIn
	 *
	 * @param value the value to set the attribute to
	 */
	public void setTopFoldIn(double value)
	{
		setAttribute(AttributeName.TOPFOLDIN, value, null);
	}

	/**
	 * (17) get double attribute TopFoldIn
	 *
	 * @return double the value of the attribute
	 */
	public double getTopFoldIn()
	{
		return getRealAttribute(AttributeName.TOPFOLDIN, null, 0.0);
	}

	/*
	 * ---------------------------------------------------------------------
	 * Methods for Attribute Height
	 * ---------------------------------------------------------------------
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
	 * ---------------------------------------------------------------------
	 * Methods for Attribute JointWidth
	 * ---------------------------------------------------------------------
	 */
	/**
	 * (36) set attribute JointWidth
	 *
	 * @param value the value to set the attribute to
	 */
	public void setJointWidth(double value)
	{
		setAttribute(AttributeName.JOINTWIDTH, value, null);
	}

	/**
	 * (17) get double attribute JointWidth
	 *
	 * @return double the value of the attribute
	 */
	public double getJointWidth()
	{
		return getRealAttribute(AttributeName.JOINTWIDTH, null, 0.0);
	}

	/*
	 * ---------------------------------------------------------------------
	 * Methods for Attribute SpineWidth
	 * ---------------------------------------------------------------------
	 */
	/**
	 * (36) set attribute SpineWidth
	 *
	 * @param value the value to set the attribute to
	 */
	public void setSpineWidth(double value)
	{
		setAttribute(AttributeName.SPINEWIDTH, value, null);
	}

	/**
	 * (17) get double attribute SpineWidth
	 *
	 * @return double the value of the attribute
	 */
	public double getSpineWidth()
	{
		return getRealAttribute(AttributeName.SPINEWIDTH, null, 0.0);
	}

	/*
	 * ***********************************************************************
	 * Element getter / setter
	 * ***********************************************************************
	 */

	/**
	 * (24) const get element GlueLine
	 *
	 * @return JDFGlueLine the element
	 */
	public JDFGlueLine getGlueLine()
	{
		return (JDFGlueLine) getElement(ElementName.GLUELINE, null, 0);
	}

	/**
	 * (25) getCreateGlueLine
	 * 
	 * @return JDFGlueLine the element
	 */
	public JDFGlueLine getCreateGlueLine()
	{
		return (JDFGlueLine) getCreateElement_JDFElement(ElementName.GLUELINE, null, 0);
	}

	/**
	 * (29) append element GlueLine
	 *
	 * @return JDFGlueLine the element
	 * @ if the element already exists
	 */
	public JDFGlueLine appendGlueLine()
	{
		return (JDFGlueLine) appendElementN(ElementName.GLUELINE, 1, null);
	}

}
