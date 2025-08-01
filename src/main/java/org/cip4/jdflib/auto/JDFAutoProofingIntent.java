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
import org.cip4.jdflib.resource.JDFProofItem;
import org.cip4.jdflib.resource.intent.JDFIntentResource;
import org.cip4.jdflib.resource.intent.JDFPreflightItem;

/**
 *****************************************************************************
 * class JDFAutoProofingIntent : public JDFIntentResource
 *****************************************************************************
 * 
 */

public abstract class JDFAutoProofingIntent extends JDFIntentResource
{

	private static final long serialVersionUID = 1L;

	private static ElemInfoTable[] elemInfoTable = new ElemInfoTable[2];
	static
	{
		elemInfoTable[0] = new ElemInfoTable(ElementName.PROOFITEM, 0x3333333331l);
		elemInfoTable[1] = new ElemInfoTable(ElementName.PREFLIGHTITEM, 0x3333111111l);
	}

	@Override
	protected ElementInfo getTheElementInfo()
	{
		return super.getTheElementInfo().updateReplace(elemInfoTable);
	}

	/**
	 * Constructor for JDFAutoProofingIntent
	 *
	 * @param myOwnerDocument
	 * @param qualifiedName
	 */
	protected JDFAutoProofingIntent(CoreDocumentImpl myOwnerDocument, String qualifiedName)
	{
		super(myOwnerDocument, qualifiedName);
	}

	/**
	 * Constructor for JDFAutoProofingIntent
	 *
	 * @param myOwnerDocument
	 * @param myNamespaceURI
	 * @param qualifiedName
	 */
	protected JDFAutoProofingIntent(CoreDocumentImpl myOwnerDocument, String myNamespaceURI, String qualifiedName)
	{
		super(myOwnerDocument, myNamespaceURI, qualifiedName);
	}

	/**
	 * Constructor for JDFAutoProofingIntent
	 *
	 * @param myOwnerDocument
	 * @param myNamespaceURI
	 * @param qualifiedName
	 * @param myLocalName
	 */
	protected JDFAutoProofingIntent(CoreDocumentImpl myOwnerDocument, String myNamespaceURI, String qualifiedName, String myLocalName)
	{
		super(myOwnerDocument, myNamespaceURI, qualifiedName, myLocalName);
	}

	/*
	 * *********************************************************************** Element getter / setter ***********************************************************************
	 */

	/**
	 * (24) const get element ProofItem
	 *
	 * @return JDFProofItem the element
	 */
	public JDFProofItem getProofItem()
	{
		return (JDFProofItem) getElement(ElementName.PROOFITEM, null, 0);
	}

	/**
	 * (25) getCreateProofItem
	 * 
	 * @return JDFProofItem the element
	 */
	public JDFProofItem getCreateProofItem()
	{
		return (JDFProofItem) getCreateElement_JDFElement(ElementName.PROOFITEM, null, 0);
	}

	/**
	 * (26) getCreateProofItem
	 * 
	 * @param iSkip number of elements to skip
	 * @return JDFProofItem the element
	 */
	public JDFProofItem getCreateProofItem(int iSkip)
	{
		return (JDFProofItem) getCreateElement_JDFElement(ElementName.PROOFITEM, null, iSkip);
	}

	/**
	 * (27) const get element ProofItem
	 *
	 * @param iSkip number of elements to skip
	 * @return JDFProofItem the element default is getProofItem(0)
	 */
	public JDFProofItem getProofItem(int iSkip)
	{
		return (JDFProofItem) getElement(ElementName.PROOFITEM, null, iSkip);
	}

	/**
	 * Get all ProofItem from the current element
	 * 
	 * @return Collection<JDFProofItem>, null if none are available
	 */
	public Collection<JDFProofItem> getAllProofItem()
	{
		return getChildArrayByClass(JDFProofItem.class, false, 0);
	}

	/**
	 * (30) append element ProofItem
	 *
	 * @return JDFProofItem the element
	 */
	public JDFProofItem appendProofItem()
	{
		return (JDFProofItem) appendElement(ElementName.PROOFITEM, null);
	}

	/**
	 * (24) const get element PreflightItem
	 *
	 * @return JDFPreflightItem the element
	 */
	public JDFPreflightItem getPreflightItem()
	{
		return (JDFPreflightItem) getElement(ElementName.PREFLIGHTITEM, null, 0);
	}

	/**
	 * (25) getCreatePreflightItem
	 * 
	 * @return JDFPreflightItem the element
	 */
	public JDFPreflightItem getCreatePreflightItem()
	{
		return (JDFPreflightItem) getCreateElement_JDFElement(ElementName.PREFLIGHTITEM, null, 0);
	}

	/**
	 * (26) getCreatePreflightItem
	 * 
	 * @param iSkip number of elements to skip
	 * @return JDFPreflightItem the element
	 */
	public JDFPreflightItem getCreatePreflightItem(int iSkip)
	{
		return (JDFPreflightItem) getCreateElement_JDFElement(ElementName.PREFLIGHTITEM, null, iSkip);
	}

	/**
	 * (27) const get element PreflightItem
	 *
	 * @param iSkip number of elements to skip
	 * @return JDFPreflightItem the element default is getPreflightItem(0)
	 */
	public JDFPreflightItem getPreflightItem(int iSkip)
	{
		return (JDFPreflightItem) getElement(ElementName.PREFLIGHTITEM, null, iSkip);
	}

	/**
	 * Get all PreflightItem from the current element
	 * 
	 * @return Collection<JDFPreflightItem>, null if none are available
	 */
	public Collection<JDFPreflightItem> getAllPreflightItem()
	{
		return getChildArrayByClass(JDFPreflightItem.class, false, 0);
	}

	/**
	 * (30) append element PreflightItem
	 *
	 * @return JDFPreflightItem the element
	 */
	public JDFPreflightItem appendPreflightItem()
	{
		return (JDFPreflightItem) appendElement(ElementName.PREFLIGHTITEM, null);
	}

}
