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
import org.cip4.jdflib.datatypes.JDFXYPair;
import org.cip4.jdflib.resource.JDFResource;
import org.cip4.jdflib.resource.process.postpress.JDFGlueApplication;
import org.cip4.jdflib.resource.process.postpress.JDFScore;

/**
 *****************************************************************************
 * class JDFAutoCoverApplicationParams : public JDFResource
 *****************************************************************************
 * 
 */

public abstract class JDFAutoCoverApplicationParams extends JDFResource
{

	private static final long serialVersionUID = 1L;

	private static AtrInfoTable[] atrInfoTable = new AtrInfoTable[1];
	static
	{
		atrInfoTable[0] = new AtrInfoTable(AttributeName.COVEROFFSET, 0x4444444431l, AttributeInfo.EnumAttributeType.XYPair, null, null);
	}

	@Override
	protected AttributeInfo getTheAttributeInfo()
	{
		return super.getTheAttributeInfo().updateReplace(atrInfoTable);
	}

	private static ElemInfoTable[] elemInfoTable = new ElemInfoTable[2];
	static
	{
		elemInfoTable[0] = new ElemInfoTable(ElementName.GLUEAPPLICATION, 0x3333333331l);
		elemInfoTable[1] = new ElemInfoTable(ElementName.SCORE, 0x3333333331l);
	}

	@Override
	protected ElementInfo getTheElementInfo()
	{
		return super.getTheElementInfo().updateReplace(elemInfoTable);
	}

	/**
	 * Constructor for JDFAutoCoverApplicationParams
	 *
	 * @param myOwnerDocument
	 * @param qualifiedName
	 */
	protected JDFAutoCoverApplicationParams(CoreDocumentImpl myOwnerDocument, String qualifiedName)
	{
		super(myOwnerDocument, qualifiedName);
	}

	/**
	 * Constructor for JDFAutoCoverApplicationParams
	 *
	 * @param myOwnerDocument
	 * @param myNamespaceURI
	 * @param qualifiedName
	 */
	protected JDFAutoCoverApplicationParams(CoreDocumentImpl myOwnerDocument, String myNamespaceURI, String qualifiedName)
	{
		super(myOwnerDocument, myNamespaceURI, qualifiedName);
	}

	/**
	 * Constructor for JDFAutoCoverApplicationParams
	 *
	 * @param myOwnerDocument
	 * @param myNamespaceURI
	 * @param qualifiedName
	 * @param myLocalName
	 */
	protected JDFAutoCoverApplicationParams(CoreDocumentImpl myOwnerDocument, String myNamespaceURI, String qualifiedName, String myLocalName)
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

	/*
	 * ************************************************************************ Attribute getter / setter ************************************************************************
	 */

	/*
	 * --------------------------------------------------------------------- Methods for Attribute CoverOffset ---------------------------------------------------------------------
	 */
	/**
	 * (36) set attribute CoverOffset
	 *
	 * @param value the value to set the attribute to
	 */
	public void setCoverOffset(JDFXYPair value)
	{
		setAttribute(AttributeName.COVEROFFSET, value, null);
	}

	/**
	 * (20) get JDFXYPair attribute CoverOffset
	 *
	 * @return JDFXYPair the value of the attribute, null if a the attribute value is not a valid to create a JDFXYPair
	 */
	public JDFXYPair getCoverOffset()
	{
		final String strAttrName = getAttribute(AttributeName.COVEROFFSET, null, null);
		final JDFXYPair nPlaceHolder = JDFXYPair.createXYPair(strAttrName);
		return nPlaceHolder;
	}

	/*
	 * *********************************************************************** Element getter / setter ***********************************************************************
	 */

	/**
	 * (24) const get element GlueApplication
	 *
	 * @return JDFGlueApplication the element
	 */
	public JDFGlueApplication getGlueApplication()
	{
		return (JDFGlueApplication) getElement(ElementName.GLUEAPPLICATION, null, 0);
	}

	/**
	 * (25) getCreateGlueApplication
	 * 
	 * @return JDFGlueApplication the element
	 */
	public JDFGlueApplication getCreateGlueApplication()
	{
		return (JDFGlueApplication) getCreateElement_JDFElement(ElementName.GLUEAPPLICATION, null, 0);
	}

	/**
	 * (26) getCreateGlueApplication
	 * 
	 * @param iSkip number of elements to skip
	 * @return JDFGlueApplication the element
	 */
	public JDFGlueApplication getCreateGlueApplication(int iSkip)
	{
		return (JDFGlueApplication) getCreateElement_JDFElement(ElementName.GLUEAPPLICATION, null, iSkip);
	}

	/**
	 * (27) const get element GlueApplication
	 *
	 * @param iSkip number of elements to skip
	 * @return JDFGlueApplication the element default is getGlueApplication(0)
	 */
	public JDFGlueApplication getGlueApplication(int iSkip)
	{
		return (JDFGlueApplication) getElement(ElementName.GLUEAPPLICATION, null, iSkip);
	}

	/**
	 * Get all GlueApplication from the current element
	 * 
	 * @return Collection<JDFGlueApplication>, null if none are available
	 */
	public Collection<JDFGlueApplication> getAllGlueApplication()
	{
		return getChildArrayByClass(JDFGlueApplication.class, false, 0);
	}

	/**
	 * (30) append element GlueApplication
	 *
	 * @return JDFGlueApplication the element
	 */
	public JDFGlueApplication appendGlueApplication()
	{
		return (JDFGlueApplication) appendElement(ElementName.GLUEAPPLICATION, null);
	}

	/**
	 * (31) create inter-resource link to refTarget
	 *
	 * @param refTarget the element that is referenced
	 */
	public void refGlueApplication(JDFGlueApplication refTarget)
	{
		refElement(refTarget);
	}

	/**
	 * (24) const get element Score
	 *
	 * @return JDFScore the element
	 */
	public JDFScore getScore()
	{
		return (JDFScore) getElement(ElementName.SCORE, null, 0);
	}

	/**
	 * (25) getCreateScore
	 * 
	 * @return JDFScore the element
	 */
	public JDFScore getCreateScore()
	{
		return (JDFScore) getCreateElement_JDFElement(ElementName.SCORE, null, 0);
	}

	/**
	 * (26) getCreateScore
	 * 
	 * @param iSkip number of elements to skip
	 * @return JDFScore the element
	 */
	public JDFScore getCreateScore(int iSkip)
	{
		return (JDFScore) getCreateElement_JDFElement(ElementName.SCORE, null, iSkip);
	}

	/**
	 * (27) const get element Score
	 *
	 * @param iSkip number of elements to skip
	 * @return JDFScore the element default is getScore(0)
	 */
	public JDFScore getScore(int iSkip)
	{
		return (JDFScore) getElement(ElementName.SCORE, null, iSkip);
	}

	/**
	 * Get all Score from the current element
	 * 
	 * @return Collection<JDFScore>, null if none are available
	 */
	public Collection<JDFScore> getAllScore()
	{
		return getChildArrayByClass(JDFScore.class, false, 0);
	}

	/**
	 * (30) append element Score
	 *
	 * @return JDFScore the element
	 */
	public JDFScore appendScore()
	{
		return (JDFScore) appendElement(ElementName.SCORE, null);
	}

}
