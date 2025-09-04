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
import org.cip4.jdflib.core.JDFElement;
import org.cip4.jdflib.datatypes.JDFIntegerRangeList;
import org.cip4.jdflib.resource.process.JDFPRError;
import org.cip4.jdflib.resource.process.JDFPRGroup;

/**
 ***************************************************************************** class JDFAutoPRItem : public JDFElement
 */

public abstract class JDFAutoPRItem extends JDFElement
{

	private static final long serialVersionUID = 1L;

	private static AtrInfoTable[] atrInfoTable = new AtrInfoTable[3];
	static
	{
		atrInfoTable[0] = new AtrInfoTable(AttributeName.ACTIONREF, 0x2222222211l, AttributeInfo.EnumAttributeType.IDREF, null, null);
		atrInfoTable[1] = new AtrInfoTable(AttributeName.OCCURRENCES, 0x2222222211l, AttributeInfo.EnumAttributeType.integer, null, null);
		atrInfoTable[2] = new AtrInfoTable(AttributeName.PAGESET, 0x3333333311l, AttributeInfo.EnumAttributeType.IntegerRangeList, null, null);
	}

	@Override
	protected AttributeInfo getTheAttributeInfo()
	{
		return super.getTheAttributeInfo().updateReplace(atrInfoTable);
	}

	private static ElemInfoTable[] elemInfoTable = new ElemInfoTable[2];
	static
	{
		elemInfoTable[0] = new ElemInfoTable(ElementName.PRERROR, 0x3333333311l);
		elemInfoTable[1] = new ElemInfoTable(ElementName.PRGROUP, 0x3333333311l);
	}

	@Override
	protected ElementInfo getTheElementInfo()
	{
		return super.getTheElementInfo().updateReplace(elemInfoTable);
	}

	/**
	 * Constructor for JDFAutoPRItem
	 *
	 * @param myOwnerDocument
	 * @param qualifiedName
	 */
	protected JDFAutoPRItem(CoreDocumentImpl myOwnerDocument, String qualifiedName)
	{
		super(myOwnerDocument, qualifiedName);
	}

	/**
	 * Constructor for JDFAutoPRItem
	 *
	 * @param myOwnerDocument
	 * @param myNamespaceURI
	 * @param qualifiedName
	 */
	protected JDFAutoPRItem(CoreDocumentImpl myOwnerDocument, String myNamespaceURI, String qualifiedName)
	{
		super(myOwnerDocument, myNamespaceURI, qualifiedName);
	}

	/**
	 * Constructor for JDFAutoPRItem
	 *
	 * @param myOwnerDocument
	 * @param myNamespaceURI
	 * @param qualifiedName
	 * @param myLocalName
	 */
	protected JDFAutoPRItem(CoreDocumentImpl myOwnerDocument, String myNamespaceURI, String qualifiedName, String myLocalName)
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
	 * Methods for Attribute ActionRef
	 * ---------------------------------------------------------------------
	 */
	/**
	 * (36) set attribute ActionRef
	 *
	 * @param value the value to set the attribute to
	 */
	public void setActionRef(String value)
	{
		setAttribute(AttributeName.ACTIONREF, value, null);
	}

	/**
	 * (23) get String attribute ActionRef
	 *
	 * @return the value of the attribute
	 */
	public String getActionRef()
	{
		return getAttribute(AttributeName.ACTIONREF, null, JDFCoreConstants.EMPTYSTRING);
	}

	/*
	 * ---------------------------------------------------------------------
	 * Methods for Attribute Occurrences
	 * ---------------------------------------------------------------------
	 */
	/**
	 * (36) set attribute Occurrences
	 *
	 * @param value the value to set the attribute to
	 */
	public void setOccurrences(int value)
	{
		setAttribute(AttributeName.OCCURRENCES, value, null);
	}

	/**
	 * (15) get int attribute Occurrences
	 *
	 * @return int the value of the attribute
	 */
	public int getOccurrences()
	{
		return getIntAttribute(AttributeName.OCCURRENCES, null, 0);
	}

	/*
	 * ---------------------------------------------------------------------
	 * Methods for Attribute PageSet
	 * ---------------------------------------------------------------------
	 */
	/**
	 * (36) set attribute PageSet
	 *
	 * @param value the value to set the attribute to
	 */
	public void setPageSet(JDFIntegerRangeList value)
	{
		setAttribute(AttributeName.PAGESET, value, null);
	}

	/**
	 * (20) get JDFIntegerRangeList attribute PageSet
	 *
	 * @return JDFIntegerRangeList the value of the attribute, null if a the
	 *         attribute value is not a valid to create a JDFIntegerRangeList
	 */
	public JDFIntegerRangeList getPageSet()
	{
		String strAttrName = getAttribute(AttributeName.PAGESET, null, null);
		JDFIntegerRangeList nPlaceHolder = JDFIntegerRangeList.createIntegerRangeList(strAttrName);
		return nPlaceHolder;
	}

	/*
	 * ***********************************************************************
	 * Element getter / setter
	 * ***********************************************************************
	 */

	/**
	 * (24) const get element PRError
	 *
	 * @return JDFPRError the element
	 */
	public JDFPRError getPRError()
	{
		return (JDFPRError) getElement(ElementName.PRERROR, null, 0);
	}

	/**
	 * (25) getCreatePRError
	 * 
	 * @return JDFPRError the element
	 */
	public JDFPRError getCreatePRError()
	{
		return (JDFPRError) getCreateElement_JDFElement(ElementName.PRERROR, null, 0);
	}

	/**
	 * (26) getCreatePRError
	 * 
	 * @param iSkip number of elements to skip
	 * @return JDFPRError the element
	 */
	public JDFPRError getCreatePRError(int iSkip)
	{
		return (JDFPRError) getCreateElement_JDFElement(ElementName.PRERROR, null, iSkip);
	}

	/**
	 * (27) const get element PRError
	 *
	 * @param iSkip number of elements to skip
	 * @return JDFPRError the element
	 *         default is getPRError(0)
	 */
	public JDFPRError getPRError(int iSkip)
	{
		return (JDFPRError) getElement(ElementName.PRERROR, null, iSkip);
	}

	/**
	 * Get all PRError from the current element
	 * 
	 * @return Collection<JDFPRError>, null if none are available
	 */
	public Collection<JDFPRError> getAllPRError()
	{
		return getChildArrayByClass(JDFPRError.class, false, 0);
	}

	/**
	 * (30) append element PRError
	 *
	 * @return JDFPRError the element
	 */
	public JDFPRError appendPRError()
	{
		return (JDFPRError) appendElement(ElementName.PRERROR, null);
	}

	/**
	 * (24) const get element PRGroup
	 *
	 * @return JDFPRGroup the element
	 */
	public JDFPRGroup getPRGroup()
	{
		return (JDFPRGroup) getElement(ElementName.PRGROUP, null, 0);
	}

	/**
	 * (25) getCreatePRGroup
	 * 
	 * @return JDFPRGroup the element
	 */
	public JDFPRGroup getCreatePRGroup()
	{
		return (JDFPRGroup) getCreateElement_JDFElement(ElementName.PRGROUP, null, 0);
	}

	/**
	 * (26) getCreatePRGroup
	 * 
	 * @param iSkip number of elements to skip
	 * @return JDFPRGroup the element
	 */
	public JDFPRGroup getCreatePRGroup(int iSkip)
	{
		return (JDFPRGroup) getCreateElement_JDFElement(ElementName.PRGROUP, null, iSkip);
	}

	/**
	 * (27) const get element PRGroup
	 *
	 * @param iSkip number of elements to skip
	 * @return JDFPRGroup the element
	 *         default is getPRGroup(0)
	 */
	public JDFPRGroup getPRGroup(int iSkip)
	{
		return (JDFPRGroup) getElement(ElementName.PRGROUP, null, iSkip);
	}

	/**
	 * Get all PRGroup from the current element
	 * 
	 * @return Collection<JDFPRGroup>, null if none are available
	 */
	public Collection<JDFPRGroup> getAllPRGroup()
	{
		return getChildArrayByClass(JDFPRGroup.class, false, 0);
	}

	/**
	 * (30) append element PRGroup
	 *
	 * @return JDFPRGroup the element
	 */
	public JDFPRGroup appendPRGroup()
	{
		return (JDFPRGroup) appendElement(ElementName.PRGROUP, null);
	}

}
