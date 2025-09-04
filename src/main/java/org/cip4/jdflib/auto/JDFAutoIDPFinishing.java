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
import org.cip4.jdflib.core.JDFElement;
import org.cip4.jdflib.datatypes.JDFIntegerList;
import org.cip4.jdflib.resource.process.JDFIDPFolding;
import org.cip4.jdflib.resource.process.JDFIDPHoleMaking;
import org.cip4.jdflib.resource.process.JDFIDPStitching;
import org.cip4.jdflib.resource.process.JDFIDPTrimming;

/**
 ***************************************************************************** class JDFAutoIDPFinishing : public JDFElement
 */

public abstract class JDFAutoIDPFinishing extends JDFElement
{

	private static final long serialVersionUID = 1L;

	private static AtrInfoTable[] atrInfoTable = new AtrInfoTable[1];
	static
	{
		atrInfoTable[0] = new AtrInfoTable(AttributeName.FINISHINGS, 0x3333333333l, AttributeInfo.EnumAttributeType.IntegerList, null, null);
	}

	@Override
	protected AttributeInfo getTheAttributeInfo()
	{
		return super.getTheAttributeInfo().updateReplace(atrInfoTable);
	}

	private static ElemInfoTable[] elemInfoTable = new ElemInfoTable[4];
	static
	{
		elemInfoTable[0] = new ElemInfoTable(ElementName.IDPFOLDING, 0x3333333333l);
		elemInfoTable[1] = new ElemInfoTable(ElementName.IDPHOLEMAKING, 0x3333333333l);
		elemInfoTable[2] = new ElemInfoTable(ElementName.IDPSTITCHING, 0x3333333333l);
		elemInfoTable[3] = new ElemInfoTable(ElementName.IDPTRIMMING, 0x3333333333l);
	}

	@Override
	protected ElementInfo getTheElementInfo()
	{
		return super.getTheElementInfo().updateReplace(elemInfoTable);
	}

	/**
	 * Constructor for JDFAutoIDPFinishing
	 *
	 * @param myOwnerDocument
	 * @param qualifiedName
	 */
	protected JDFAutoIDPFinishing(CoreDocumentImpl myOwnerDocument, String qualifiedName)
	{
		super(myOwnerDocument, qualifiedName);
	}

	/**
	 * Constructor for JDFAutoIDPFinishing
	 *
	 * @param myOwnerDocument
	 * @param myNamespaceURI
	 * @param qualifiedName
	 */
	protected JDFAutoIDPFinishing(CoreDocumentImpl myOwnerDocument, String myNamespaceURI, String qualifiedName)
	{
		super(myOwnerDocument, myNamespaceURI, qualifiedName);
	}

	/**
	 * Constructor for JDFAutoIDPFinishing
	 *
	 * @param myOwnerDocument
	 * @param myNamespaceURI
	 * @param qualifiedName
	 * @param myLocalName
	 */
	protected JDFAutoIDPFinishing(CoreDocumentImpl myOwnerDocument, String myNamespaceURI, String qualifiedName, String myLocalName)
	{
		super(myOwnerDocument, myNamespaceURI, qualifiedName, myLocalName);
	}

	/*
	 * ************************************************************************
	 * Attribute getter / setter
	 * ************************************************************************
	 */

	/*
	 * ---------------------------------------------------------------------
	 * Methods for Attribute Finishings
	 * ---------------------------------------------------------------------
	 */
	/**
	 * (36) set attribute Finishings
	 *
	 * @param value the value to set the attribute to
	 */
	public void setFinishings(JDFIntegerList value)
	{
		setAttribute(AttributeName.FINISHINGS, value, null);
	}

	/**
	 * (20) get JDFIntegerList attribute Finishings
	 *
	 * @return JDFIntegerList the value of the attribute, null if a the
	 *         attribute value is not a valid to create a JDFIntegerList
	 */
	public JDFIntegerList getFinishings()
	{
		String strAttrName = getAttribute(AttributeName.FINISHINGS, null, null);
		JDFIntegerList nPlaceHolder = JDFIntegerList.createIntegerList(strAttrName);
		return nPlaceHolder;
	}

	/*
	 * ***********************************************************************
	 * Element getter / setter
	 * ***********************************************************************
	 */

	/**
	 * (24) const get element IDPFolding
	 *
	 * @return JDFIDPFolding the element
	 */
	public JDFIDPFolding getIDPFolding()
	{
		return (JDFIDPFolding) getElement(ElementName.IDPFOLDING, null, 0);
	}

	/**
	 * (25) getCreateIDPFolding
	 * 
	 * @return JDFIDPFolding the element
	 */
	public JDFIDPFolding getCreateIDPFolding()
	{
		return (JDFIDPFolding) getCreateElement_JDFElement(ElementName.IDPFOLDING, null, 0);
	}

	/**
	 * (26) getCreateIDPFolding
	 * 
	 * @param iSkip number of elements to skip
	 * @return JDFIDPFolding the element
	 */
	public JDFIDPFolding getCreateIDPFolding(int iSkip)
	{
		return (JDFIDPFolding) getCreateElement_JDFElement(ElementName.IDPFOLDING, null, iSkip);
	}

	/**
	 * (27) const get element IDPFolding
	 *
	 * @param iSkip number of elements to skip
	 * @return JDFIDPFolding the element
	 *         default is getIDPFolding(0)
	 */
	public JDFIDPFolding getIDPFolding(int iSkip)
	{
		return (JDFIDPFolding) getElement(ElementName.IDPFOLDING, null, iSkip);
	}

	/**
	 * Get all IDPFolding from the current element
	 * 
	 * @return Collection<JDFIDPFolding>, null if none are available
	 */
	public Collection<JDFIDPFolding> getAllIDPFolding()
	{
		return getChildArrayByClass(JDFIDPFolding.class, false, 0);
	}

	/**
	 * (30) append element IDPFolding
	 *
	 * @return JDFIDPFolding the element
	 */
	public JDFIDPFolding appendIDPFolding()
	{
		return (JDFIDPFolding) appendElement(ElementName.IDPFOLDING, null);
	}

	/**
	 * (24) const get element IDPHoleMaking
	 *
	 * @return JDFIDPHoleMaking the element
	 */
	public JDFIDPHoleMaking getIDPHoleMaking()
	{
		return (JDFIDPHoleMaking) getElement(ElementName.IDPHOLEMAKING, null, 0);
	}

	/**
	 * (25) getCreateIDPHoleMaking
	 * 
	 * @return JDFIDPHoleMaking the element
	 */
	public JDFIDPHoleMaking getCreateIDPHoleMaking()
	{
		return (JDFIDPHoleMaking) getCreateElement_JDFElement(ElementName.IDPHOLEMAKING, null, 0);
	}

	/**
	 * (26) getCreateIDPHoleMaking
	 * 
	 * @param iSkip number of elements to skip
	 * @return JDFIDPHoleMaking the element
	 */
	public JDFIDPHoleMaking getCreateIDPHoleMaking(int iSkip)
	{
		return (JDFIDPHoleMaking) getCreateElement_JDFElement(ElementName.IDPHOLEMAKING, null, iSkip);
	}

	/**
	 * (27) const get element IDPHoleMaking
	 *
	 * @param iSkip number of elements to skip
	 * @return JDFIDPHoleMaking the element
	 *         default is getIDPHoleMaking(0)
	 */
	public JDFIDPHoleMaking getIDPHoleMaking(int iSkip)
	{
		return (JDFIDPHoleMaking) getElement(ElementName.IDPHOLEMAKING, null, iSkip);
	}

	/**
	 * Get all IDPHoleMaking from the current element
	 * 
	 * @return Collection<JDFIDPHoleMaking>, null if none are available
	 */
	public Collection<JDFIDPHoleMaking> getAllIDPHoleMaking()
	{
		return getChildArrayByClass(JDFIDPHoleMaking.class, false, 0);
	}

	/**
	 * (30) append element IDPHoleMaking
	 *
	 * @return JDFIDPHoleMaking the element
	 */
	public JDFIDPHoleMaking appendIDPHoleMaking()
	{
		return (JDFIDPHoleMaking) appendElement(ElementName.IDPHOLEMAKING, null);
	}

	/**
	 * (24) const get element IDPStitching
	 *
	 * @return JDFIDPStitching the element
	 */
	public JDFIDPStitching getIDPStitching()
	{
		return (JDFIDPStitching) getElement(ElementName.IDPSTITCHING, null, 0);
	}

	/**
	 * (25) getCreateIDPStitching
	 * 
	 * @return JDFIDPStitching the element
	 */
	public JDFIDPStitching getCreateIDPStitching()
	{
		return (JDFIDPStitching) getCreateElement_JDFElement(ElementName.IDPSTITCHING, null, 0);
	}

	/**
	 * (26) getCreateIDPStitching
	 * 
	 * @param iSkip number of elements to skip
	 * @return JDFIDPStitching the element
	 */
	public JDFIDPStitching getCreateIDPStitching(int iSkip)
	{
		return (JDFIDPStitching) getCreateElement_JDFElement(ElementName.IDPSTITCHING, null, iSkip);
	}

	/**
	 * (27) const get element IDPStitching
	 *
	 * @param iSkip number of elements to skip
	 * @return JDFIDPStitching the element
	 *         default is getIDPStitching(0)
	 */
	public JDFIDPStitching getIDPStitching(int iSkip)
	{
		return (JDFIDPStitching) getElement(ElementName.IDPSTITCHING, null, iSkip);
	}

	/**
	 * Get all IDPStitching from the current element
	 * 
	 * @return Collection<JDFIDPStitching>, null if none are available
	 */
	public Collection<JDFIDPStitching> getAllIDPStitching()
	{
		return getChildArrayByClass(JDFIDPStitching.class, false, 0);
	}

	/**
	 * (30) append element IDPStitching
	 *
	 * @return JDFIDPStitching the element
	 */
	public JDFIDPStitching appendIDPStitching()
	{
		return (JDFIDPStitching) appendElement(ElementName.IDPSTITCHING, null);
	}

	/**
	 * (24) const get element IDPTrimming
	 *
	 * @return JDFIDPTrimming the element
	 */
	public JDFIDPTrimming getIDPTrimming()
	{
		return (JDFIDPTrimming) getElement(ElementName.IDPTRIMMING, null, 0);
	}

	/**
	 * (25) getCreateIDPTrimming
	 * 
	 * @return JDFIDPTrimming the element
	 */
	public JDFIDPTrimming getCreateIDPTrimming()
	{
		return (JDFIDPTrimming) getCreateElement_JDFElement(ElementName.IDPTRIMMING, null, 0);
	}

	/**
	 * (26) getCreateIDPTrimming
	 * 
	 * @param iSkip number of elements to skip
	 * @return JDFIDPTrimming the element
	 */
	public JDFIDPTrimming getCreateIDPTrimming(int iSkip)
	{
		return (JDFIDPTrimming) getCreateElement_JDFElement(ElementName.IDPTRIMMING, null, iSkip);
	}

	/**
	 * (27) const get element IDPTrimming
	 *
	 * @param iSkip number of elements to skip
	 * @return JDFIDPTrimming the element
	 *         default is getIDPTrimming(0)
	 */
	public JDFIDPTrimming getIDPTrimming(int iSkip)
	{
		return (JDFIDPTrimming) getElement(ElementName.IDPTRIMMING, null, iSkip);
	}

	/**
	 * Get all IDPTrimming from the current element
	 * 
	 * @return Collection<JDFIDPTrimming>, null if none are available
	 */
	public Collection<JDFIDPTrimming> getAllIDPTrimming()
	{
		return getChildArrayByClass(JDFIDPTrimming.class, false, 0);
	}

	/**
	 * (30) append element IDPTrimming
	 *
	 * @return JDFIDPTrimming the element
	 */
	public JDFIDPTrimming appendIDPTrimming()
	{
		return (JDFIDPTrimming) appendElement(ElementName.IDPTRIMMING, null);
	}

}
