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
import org.cip4.jdflib.pool.JDFAmountPool;
import org.cip4.jdflib.resource.JDFResource;

/**
 *****************************************************************************
 * class JDFAutoPipeParams : public JDFElement
 *****************************************************************************
 * 
 */

public abstract class JDFAutoPipeParams extends JDFElement
{

	private static final long serialVersionUID = 1L;

	private static AtrInfoTable[] atrInfoTable = new AtrInfoTable[6];
	static
	{
		atrInfoTable[0] = new AtrInfoTable(AttributeName.JOBID, 0x3333333311l, AttributeInfo.EnumAttributeType.shortString, null, null);
		atrInfoTable[1] = new AtrInfoTable(AttributeName.JOBPARTID, 0x3333333311l, AttributeInfo.EnumAttributeType.shortString, null, null);
		atrInfoTable[2] = new AtrInfoTable(AttributeName.PIPEID, 0x2222222222l, AttributeInfo.EnumAttributeType.shortString, null, null);
		atrInfoTable[3] = new AtrInfoTable(AttributeName.PROJECTID, 0x3333311111l, AttributeInfo.EnumAttributeType.shortString, null, null);
		atrInfoTable[4] = new AtrInfoTable(AttributeName.STATUS, 0x3333333333l, AttributeInfo.EnumAttributeType.enumeration, EnumNodeStatus.getEnum(0), "InProgress");
		atrInfoTable[5] = new AtrInfoTable(AttributeName.UPDATEDSTATUS, 0x3333333333l, AttributeInfo.EnumAttributeType.enumeration, JDFResource.EnumResStatus.getEnum(0), null);
	}

	@Override
	protected AttributeInfo getTheAttributeInfo()
	{
		return super.getTheAttributeInfo().updateReplace(atrInfoTable);
	}

	private static ElemInfoTable[] elemInfoTable = new ElemInfoTable[2];
	static
	{
		elemInfoTable[0] = new ElemInfoTable(ElementName.AMOUNTPOOL, 0x6666611111l);
		elemInfoTable[1] = new ElemInfoTable(ElementName.RESOURCE, 0x3333333333l);
	}

	@Override
	protected ElementInfo getTheElementInfo()
	{
		return super.getTheElementInfo().updateReplace(elemInfoTable);
	}

	/**
	 * Constructor for JDFAutoPipeParams
	 *
	 * @param myOwnerDocument
	 * @param qualifiedName
	 */
	protected JDFAutoPipeParams(CoreDocumentImpl myOwnerDocument, String qualifiedName)
	{
		super(myOwnerDocument, qualifiedName);
	}

	/**
	 * Constructor for JDFAutoPipeParams
	 *
	 * @param myOwnerDocument
	 * @param myNamespaceURI
	 * @param qualifiedName
	 */
	protected JDFAutoPipeParams(CoreDocumentImpl myOwnerDocument, String myNamespaceURI, String qualifiedName)
	{
		super(myOwnerDocument, myNamespaceURI, qualifiedName);
	}

	/**
	 * Constructor for JDFAutoPipeParams
	 *
	 * @param myOwnerDocument
	 * @param myNamespaceURI
	 * @param qualifiedName
	 * @param myLocalName
	 */
	protected JDFAutoPipeParams(CoreDocumentImpl myOwnerDocument, String myNamespaceURI, String qualifiedName, String myLocalName)
	{
		super(myOwnerDocument, myNamespaceURI, qualifiedName, myLocalName);
	}

	/*
	 * ************************************************************************ Attribute getter / setter ************************************************************************
	 */

	/*
	 * --------------------------------------------------------------------- Methods for Attribute JobID ---------------------------------------------------------------------
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
	 * --------------------------------------------------------------------- Methods for Attribute JobPartID ---------------------------------------------------------------------
	 */
	/**
	 * (36) set attribute JobPartID
	 *
	 * @param value the value to set the attribute to
	 */
	public void setJobPartID(String value)
	{
		setAttribute(AttributeName.JOBPARTID, value, null);
	}

	/**
	 * (23) get String attribute JobPartID
	 *
	 * @return the value of the attribute
	 */
	public String getJobPartID()
	{
		return getAttribute(AttributeName.JOBPARTID, null, JDFCoreConstants.EMPTYSTRING);
	}

	/*
	 * --------------------------------------------------------------------- Methods for Attribute PipeID ---------------------------------------------------------------------
	 */
	/**
	 * (36) set attribute PipeID
	 *
	 * @param value the value to set the attribute to
	 */
	public void setPipeID(String value)
	{
		setAttribute(AttributeName.PIPEID, value, null);
	}

	/**
	 * (23) get String attribute PipeID
	 *
	 * @return the value of the attribute
	 */
	public String getPipeID()
	{
		return getAttribute(AttributeName.PIPEID, null, JDFCoreConstants.EMPTYSTRING);
	}

	/*
	 * --------------------------------------------------------------------- Methods for Attribute ProjectID ---------------------------------------------------------------------
	 */
	/**
	 * (36) set attribute ProjectID
	 *
	 * @param value the value to set the attribute to
	 */
	public void setProjectID(String value)
	{
		setAttribute(AttributeName.PROJECTID, value, null);
	}

	/**
	 * (23) get String attribute ProjectID
	 *
	 * @return the value of the attribute
	 */
	public String getProjectID()
	{
		return getAttribute(AttributeName.PROJECTID, null, JDFCoreConstants.EMPTYSTRING);
	}

	/*
	 * --------------------------------------------------------------------- Methods for Attribute UpdatedStatus
	 * ---------------------------------------------------------------------
	 */
	/**
	 * (5) set attribute UpdatedStatus
	 *
	 * @param enumVar the enumVar to set the attribute to
	 */
	public void setUpdatedStatus(JDFResource.EResourceClass enumVar)
	{
		setAttribute(AttributeName.UPDATEDSTATUS, enumVar == null ? null : enumVar.name(), null);
	}

	/**
	 * (9) get attribute UpdatedStatus
	 *
	 * @return the value of the attribute
	 */
	public JDFResource.EResourceClass getEUpdatedStatus()
	{
		return JDFResource.EResourceClass.getEnum(getAttribute(AttributeName.UPDATEDSTATUS, null, null));
	}

	/*
	 * --------------------------------------------------------------------- Methods for Attribute UpdatedStatus
	 * ---------------------------------------------------------------------
	 */
	/**
	 * (5) set attribute UpdatedStatus
	 *
	 * @param enumVar the enumVar to set the attribute to
	 * @deprecated use java.lang.enum
	 */
	@Deprecated
	public void setUpdatedStatus(JDFResource.EnumResStatus enumVar)
	{
		setAttribute(AttributeName.UPDATEDSTATUS, enumVar == null ? null : enumVar.getName(), null);
	}

	/**
	 * (9) get attribute UpdatedStatus
	 *
	 * @return the value of the attribute
	 */
	public JDFResource.EnumResStatus getUpdatedStatus()
	{
		return JDFResource.EnumResStatus.getEnum(getAttribute(AttributeName.UPDATEDSTATUS, null, null));
	}

	/*
	 * *********************************************************************** Element getter / setter ***********************************************************************
	 */

	/**
	 * (24) const get element AmountPool
	 *
	 * @return JDFAmountPool the element
	 */
	public JDFAmountPool getAmountPool()
	{
		return (JDFAmountPool) getElement(ElementName.AMOUNTPOOL, null, 0);
	}

	/**
	 * (25) getCreateAmountPool
	 * 
	 * @return JDFAmountPool the element
	 */
	public JDFAmountPool getCreateAmountPool()
	{
		return (JDFAmountPool) getCreateElement_JDFElement(ElementName.AMOUNTPOOL, null, 0);
	}

	/**
	 * (29) append element AmountPool
	 *
	 * @return JDFAmountPool the element @ if the element already exists
	 */
	public JDFAmountPool appendAmountPool()
	{
		return (JDFAmountPool) appendElementN(ElementName.AMOUNTPOOL, 1, null);
	}

	/**
	 * (24) const get element Resource
	 *
	 * @return JDFResource the element
	 */
	public JDFResource getResource()
	{
		return (JDFResource) getElement(ElementName.RESOURCE, null, 0);
	}

	/**
	 * (25) getCreateResource
	 * 
	 * @return JDFResource the element
	 */
	public JDFResource getCreateResource()
	{
		return (JDFResource) getCreateElement_JDFElement(ElementName.RESOURCE, null, 0);
	}

	/**
	 * (26) getCreateResource
	 * 
	 * @param iSkip number of elements to skip
	 * @return JDFResource the element
	 */
	public JDFResource getCreateResource(int iSkip)
	{
		return (JDFResource) getCreateElement_JDFElement(ElementName.RESOURCE, null, iSkip);
	}

	/**
	 * (27) const get element Resource
	 *
	 * @param iSkip number of elements to skip
	 * @return JDFResource the element default is getResource(0)
	 */
	public JDFResource getResource(int iSkip)
	{
		return (JDFResource) getElement(ElementName.RESOURCE, null, iSkip);
	}

	/**
	 * Get all Resource from the current element
	 * 
	 * @return Collection<JDFResource>, null if none are available
	 */
	public Collection<JDFResource> getAllResource()
	{
		return getChildArrayByClass(JDFResource.class, false, 0);
	}

	/**
	 * (30) append element Resource
	 *
	 * @return JDFResource the element
	 */
	public JDFResource appendResource()
	{
		return (JDFResource) appendElement(ElementName.RESOURCE, null);
	}

}
