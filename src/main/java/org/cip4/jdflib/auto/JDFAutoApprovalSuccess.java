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
import org.cip4.jdflib.core.ElemInfoTable;
import org.cip4.jdflib.core.ElementInfo;
import org.cip4.jdflib.core.ElementName;
import org.cip4.jdflib.resource.JDFResource;
import org.cip4.jdflib.resource.process.JDFApprovalDetails;
import org.cip4.jdflib.resource.process.JDFContact;
import org.cip4.jdflib.resource.process.JDFFileSpec;

/**
 *****************************************************************************
 * class JDFAutoApprovalSuccess : public JDFResource
 *****************************************************************************
 * 
 */

public abstract class JDFAutoApprovalSuccess extends JDFResource
{

	private static final long serialVersionUID = 1L;

	private static ElemInfoTable[] elemInfoTable = new ElemInfoTable[3];
	static
	{
		elemInfoTable[0] = new ElemInfoTable(ElementName.APPROVALDETAILS, 0x3333333111l);
		elemInfoTable[1] = new ElemInfoTable(ElementName.FILESPEC, 0x7777777666l);
		elemInfoTable[2] = new ElemInfoTable(ElementName.CONTACT, 0x4444444311l);
	}

	@Override
	protected ElementInfo getTheElementInfo()
	{
		return super.getTheElementInfo().updateReplace(elemInfoTable);
	}

	/**
	 * Constructor for JDFAutoApprovalSuccess
	 *
	 * @param myOwnerDocument
	 * @param qualifiedName
	 */
	protected JDFAutoApprovalSuccess(CoreDocumentImpl myOwnerDocument, String qualifiedName)
	{
		super(myOwnerDocument, qualifiedName);
	}

	/**
	 * Constructor for JDFAutoApprovalSuccess
	 *
	 * @param myOwnerDocument
	 * @param myNamespaceURI
	 * @param qualifiedName
	 */
	protected JDFAutoApprovalSuccess(CoreDocumentImpl myOwnerDocument, String myNamespaceURI, String qualifiedName)
	{
		super(myOwnerDocument, myNamespaceURI, qualifiedName);
	}

	/**
	 * Constructor for JDFAutoApprovalSuccess
	 *
	 * @param myOwnerDocument
	 * @param myNamespaceURI
	 * @param qualifiedName
	 * @param myLocalName
	 */
	protected JDFAutoApprovalSuccess(CoreDocumentImpl myOwnerDocument, String myNamespaceURI, String qualifiedName, String myLocalName)
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
	 * *********************************************************************** Element getter / setter ***********************************************************************
	 */

	/**
	 * (24) const get element ApprovalDetails
	 *
	 * @return JDFApprovalDetails the element
	 */
	public JDFApprovalDetails getApprovalDetails()
	{
		return (JDFApprovalDetails) getElement(ElementName.APPROVALDETAILS, null, 0);
	}

	/**
	 * (25) getCreateApprovalDetails
	 * 
	 * @return JDFApprovalDetails the element
	 */
	public JDFApprovalDetails getCreateApprovalDetails()
	{
		return (JDFApprovalDetails) getCreateElement_JDFElement(ElementName.APPROVALDETAILS, null, 0);
	}

	/**
	 * (26) getCreateApprovalDetails
	 * 
	 * @param iSkip number of elements to skip
	 * @return JDFApprovalDetails the element
	 */
	public JDFApprovalDetails getCreateApprovalDetails(int iSkip)
	{
		return (JDFApprovalDetails) getCreateElement_JDFElement(ElementName.APPROVALDETAILS, null, iSkip);
	}

	/**
	 * (27) const get element ApprovalDetails
	 *
	 * @param iSkip number of elements to skip
	 * @return JDFApprovalDetails the element default is getApprovalDetails(0)
	 */
	public JDFApprovalDetails getApprovalDetails(int iSkip)
	{
		return (JDFApprovalDetails) getElement(ElementName.APPROVALDETAILS, null, iSkip);
	}

	/**
	 * Get all ApprovalDetails from the current element
	 * 
	 * @return Collection<JDFApprovalDetails>, null if none are available
	 */
	public Collection<JDFApprovalDetails> getAllApprovalDetails()
	{
		return getChildArrayByClass(JDFApprovalDetails.class, false, 0);
	}

	/**
	 * (30) append element ApprovalDetails
	 *
	 * @return JDFApprovalDetails the element
	 */
	public JDFApprovalDetails appendApprovalDetails()
	{
		return (JDFApprovalDetails) appendElement(ElementName.APPROVALDETAILS, null);
	}

	/**
	 * (24) const get element FileSpec
	 *
	 * @return JDFFileSpec the element
	 */
	public JDFFileSpec getFileSpec()
	{
		return (JDFFileSpec) getElement(ElementName.FILESPEC, null, 0);
	}

	/**
	 * (25) getCreateFileSpec
	 * 
	 * @return JDFFileSpec the element
	 */
	public JDFFileSpec getCreateFileSpec()
	{
		return (JDFFileSpec) getCreateElement_JDFElement(ElementName.FILESPEC, null, 0);
	}

	/**
	 * (29) append element FileSpec
	 *
	 * @return JDFFileSpec the element @ if the element already exists
	 */
	public JDFFileSpec appendFileSpec()
	{
		return (JDFFileSpec) appendElementN(ElementName.FILESPEC, 1, null);
	}

	/**
	 * (31) create inter-resource link to refTarget
	 *
	 * @param refTarget the element that is referenced
	 */
	public void refFileSpec(JDFFileSpec refTarget)
	{
		refElement(refTarget);
	}

	/**
	 * (24) const get element Contact
	 *
	 * @return JDFContact the element
	 */
	@Override
	public JDFContact getContact()
	{
		return (JDFContact) getElement(ElementName.CONTACT, null, 0);
	}

	/**
	 * (25) getCreateContact
	 * 
	 * @return JDFContact the element
	 */
	@Override
	public JDFContact getCreateContact()
	{
		return (JDFContact) getCreateElement_JDFElement(ElementName.CONTACT, null, 0);
	}

	/**
	 * (26) getCreateContact
	 * 
	 * @param iSkip number of elements to skip
	 * @return JDFContact the element
	 */
	public JDFContact getCreateContact(int iSkip)
	{
		return (JDFContact) getCreateElement_JDFElement(ElementName.CONTACT, null, iSkip);
	}

	/**
	 * (27) const get element Contact
	 *
	 * @param iSkip number of elements to skip
	 * @return JDFContact the element default is getContact(0)
	 */
	public JDFContact getContact(int iSkip)
	{
		return (JDFContact) getElement(ElementName.CONTACT, null, iSkip);
	}

	/**
	 * Get all Contact from the current element
	 * 
	 * @return Collection<JDFContact>, null if none are available
	 */
	public Collection<JDFContact> getAllContact()
	{
		return getChildArrayByClass(JDFContact.class, false, 0);
	}

	/**
	 * (30) append element Contact
	 *
	 * @return JDFContact the element
	 */
	@Override
	public JDFContact appendContact()
	{
		return (JDFContact) appendElement(ElementName.CONTACT, null);
	}

	/**
	 * (31) create inter-resource link to refTarget
	 *
	 * @param refTarget the element that is referenced
	 */
	public void refContact(JDFContact refTarget)
	{
		refElement(refTarget);
	}

}
