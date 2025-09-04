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
import org.cip4.jdflib.auto.JDFAutoAssembly.EOrder;
import org.cip4.jdflib.auto.JDFAutoAssembly.EnumOrder;
import org.cip4.jdflib.core.AtrInfoTable;
import org.cip4.jdflib.core.AttributeInfo;
import org.cip4.jdflib.core.AttributeName;
import org.cip4.jdflib.core.ElemInfoTable;
import org.cip4.jdflib.core.ElementInfo;
import org.cip4.jdflib.core.ElementName;
import org.cip4.jdflib.core.JDFCoreConstants;
import org.cip4.jdflib.core.JDFElement;
import org.cip4.jdflib.core.VString;
import org.cip4.jdflib.resource.process.JDFPageAssignedList;

/**
 ***************************************************************************** class JDFAutoAssemblySection : public JDFElement
 */

public abstract class JDFAutoAssemblySection extends JDFElement
{

	private static final long serialVersionUID = 1L;

	private static AtrInfoTable[] atrInfoTable = new AtrInfoTable[5];
	static
	{
		atrInfoTable[0] = new AtrInfoTable(AttributeName.ASSEMBLYID, 0x4444444311l, AttributeInfo.EnumAttributeType.string, null, null);
		atrInfoTable[1] = new AtrInfoTable(AttributeName.ASSEMBLYIDS, 0x3333333111l, AttributeInfo.EnumAttributeType.NMTOKENS, null, null);
		atrInfoTable[2] = new AtrInfoTable(AttributeName.COMMONFOLDS, 0x3331111111l, AttributeInfo.EnumAttributeType.integer, null, null);
		atrInfoTable[3] = new AtrInfoTable(AttributeName.JOBID, 0x3333333311l, AttributeInfo.EnumAttributeType.shortString, null, null);
		atrInfoTable[4] = new AtrInfoTable(AttributeName.ORDER, 0x4444433311l, AttributeInfo.EnumAttributeType.enumeration, EnumOrder.getEnum(0), null);
	}

	@Override
	protected AttributeInfo getTheAttributeInfo()
	{
		return super.getTheAttributeInfo().updateReplace(atrInfoTable);
	}

	private static ElemInfoTable[] elemInfoTable = new ElemInfoTable[1];
	static
	{
		elemInfoTable[0] = new ElemInfoTable(ElementName.PAGEASSIGNEDLIST, 0x3333333311l);
	}

	@Override
	protected ElementInfo getTheElementInfo()
	{
		return super.getTheElementInfo().updateReplace(elemInfoTable);
	}

	/**
	 * Constructor for JDFAutoAssemblySection
	 *
	 * @param myOwnerDocument
	 * @param qualifiedName
	 */
	protected JDFAutoAssemblySection(CoreDocumentImpl myOwnerDocument, String qualifiedName)
	{
		super(myOwnerDocument, qualifiedName);
	}

	/**
	 * Constructor for JDFAutoAssemblySection
	 *
	 * @param myOwnerDocument
	 * @param myNamespaceURI
	 * @param qualifiedName
	 */
	protected JDFAutoAssemblySection(CoreDocumentImpl myOwnerDocument, String myNamespaceURI, String qualifiedName)
	{
		super(myOwnerDocument, myNamespaceURI, qualifiedName);
	}

	/**
	 * Constructor for JDFAutoAssemblySection
	 *
	 * @param myOwnerDocument
	 * @param myNamespaceURI
	 * @param qualifiedName
	 * @param myLocalName
	 */
	protected JDFAutoAssemblySection(CoreDocumentImpl myOwnerDocument, String myNamespaceURI, String qualifiedName, String myLocalName)
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
	 * Methods for Attribute AssemblyID
	 * ---------------------------------------------------------------------
	 */
	/**
	 * (36) set attribute AssemblyID
	 *
	 * @param value the value to set the attribute to
	 */
	public void setAssemblyID(String value)
	{
		setAttribute(AttributeName.ASSEMBLYID, value, null);
	}

	/**
	 * (23) get String attribute AssemblyID
	 *
	 * @return the value of the attribute
	 */
	public String getAssemblyID()
	{
		return getAttribute(AttributeName.ASSEMBLYID, null, JDFCoreConstants.EMPTYSTRING);
	}

	/*
	 * ---------------------------------------------------------------------
	 * Methods for Attribute AssemblyIDs
	 * ---------------------------------------------------------------------
	 */
	/**
	 * (36) set attribute AssemblyIDs
	 *
	 * @param value the value to set the attribute to
	 */
	public void setAssemblyIDs(VString value)
	{
		setAttribute(AttributeName.ASSEMBLYIDS, value, null);
	}

	/**
	 * (21) get VString attribute AssemblyIDs
	 *
	 * @return VString the value of the attribute
	 */
	public VString getAssemblyIDs()
	{
		VString vStrAttrib = new VString();
		String s = getAttribute(AttributeName.ASSEMBLYIDS, null, JDFCoreConstants.EMPTYSTRING);
		vStrAttrib.setAllStrings(s, " ");
		return vStrAttrib;
	}

	/*
	 * ---------------------------------------------------------------------
	 * Methods for Attribute CommonFolds
	 * ---------------------------------------------------------------------
	 */
	/**
	 * (36) set attribute CommonFolds
	 *
	 * @param value the value to set the attribute to
	 */
	public void setCommonFolds(int value)
	{
		setAttribute(AttributeName.COMMONFOLDS, value, null);
	}

	/**
	 * (15) get int attribute CommonFolds
	 *
	 * @return int the value of the attribute
	 */
	public int getCommonFolds()
	{
		return getIntAttribute(AttributeName.COMMONFOLDS, null, 0);
	}

	/*
	 * ---------------------------------------------------------------------
	 * Methods for Attribute JobID
	 * ---------------------------------------------------------------------
	 */
	/**
	 * (36) set attribute JobID
	 *
	 * @param value the value to set the attribute to
	 */
	public void setJobID(String value)
	{
		setAttribute(AttributeName.JOBID, value, null);
	}

	/**
	 * (23) get String attribute JobID
	 *
	 * @return the value of the attribute
	 */
	public String getJobID()
	{
		return getAttribute(AttributeName.JOBID, null, JDFCoreConstants.EMPTYSTRING);
	}

	/*
	 * ---------------------------------------------------------------------
	 * Methods for Attribute Order
	 * ---------------------------------------------------------------------
	 */
	/**
	 * (5) set attribute Order
	 *
	 * @param enumVar the enumVar to set the attribute to
	 */
	public void setOrder(EOrder enumVar)
	{
		setAttribute(AttributeName.ORDER, enumVar == null ? null : enumVar.name(), null);
	}

	/**
	 * (9) get attribute Order
	 *
	 * @return the value of the attribute
	 */
	public EOrder getEOrder()
	{
		return EOrder.getEnum(getAttribute(AttributeName.ORDER, null, null));
	}

	/*
	 * ---------------------------------------------------------------------
	 * Methods for Attribute Order
	 * ---------------------------------------------------------------------
	 */
	/**
	 * (5) set attribute Order
	 *
	 * @param enumVar the enumVar to set the attribute to
	 * @deprecated use SetOrder(EOrder) based on java.lang.enum instead
	 */
	@Deprecated
	public void setOrder(EnumOrder enumVar)
	{
		setAttribute(AttributeName.ORDER, enumVar == null ? null : enumVar.getName(), null);
	}

	/**
	 * (9) get attribute Order
	 *
	 * @return the value of the attribute
	 * @deprecated use EOrder GetEOrder() based on java.lang.enum instead
	 */
	@Deprecated
	public EnumOrder getOrder()
	{
		return EnumOrder.getEnum(getAttribute(AttributeName.ORDER, null, null));
	}

	/*
	 * ***********************************************************************
	 * Element getter / setter
	 * ***********************************************************************
	 */

	/**
	 * (24) const get element PageAssignedList
	 *
	 * @return JDFPageAssignedList the element
	 */
	public JDFPageAssignedList getPageAssignedList()
	{
		return (JDFPageAssignedList) getElement(ElementName.PAGEASSIGNEDLIST, null, 0);
	}

	/**
	 * (25) getCreatePageAssignedList
	 * 
	 * @return JDFPageAssignedList the element
	 */
	public JDFPageAssignedList getCreatePageAssignedList()
	{
		return (JDFPageAssignedList) getCreateElement_JDFElement(ElementName.PAGEASSIGNEDLIST, null, 0);
	}

	/**
	 * (26) getCreatePageAssignedList
	 * 
	 * @param iSkip number of elements to skip
	 * @return JDFPageAssignedList the element
	 */
	public JDFPageAssignedList getCreatePageAssignedList(int iSkip)
	{
		return (JDFPageAssignedList) getCreateElement_JDFElement(ElementName.PAGEASSIGNEDLIST, null, iSkip);
	}

	/**
	 * (27) const get element PageAssignedList
	 *
	 * @param iSkip number of elements to skip
	 * @return JDFPageAssignedList the element
	 *         default is getPageAssignedList(0)
	 */
	public JDFPageAssignedList getPageAssignedList(int iSkip)
	{
		return (JDFPageAssignedList) getElement(ElementName.PAGEASSIGNEDLIST, null, iSkip);
	}

	/**
	 * Get all PageAssignedList from the current element
	 * 
	 * @return Collection<JDFPageAssignedList>, null if none are available
	 */
	public Collection<JDFPageAssignedList> getAllPageAssignedList()
	{
		return getChildArrayByClass(JDFPageAssignedList.class, false, 0);
	}

	/**
	 * (30) append element PageAssignedList
	 *
	 * @return JDFPageAssignedList the element
	 */
	public JDFPageAssignedList appendPageAssignedList()
	{
		return (JDFPageAssignedList) appendElement(ElementName.PAGEASSIGNEDLIST, null);
	}

}
