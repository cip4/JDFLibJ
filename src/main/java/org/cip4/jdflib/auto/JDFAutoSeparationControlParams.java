/*
 * The CIP4 Software License, Version 1.0
 *
 *
 * Copyright (c) 2001-2020 The International Cooperation for the Integration of
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
import org.cip4.jdflib.resource.JDFTransferFunctionControl;
import org.cip4.jdflib.resource.process.JDFAutomatedOverPrintParams;

/**
 *****************************************************************************
 * class JDFAutoSeparationControlParams : public JDFResource
 *****************************************************************************
 *
 */

public abstract class JDFAutoSeparationControlParams extends JDFResource
{

	private static final long serialVersionUID = 1L;

	private static ElemInfoTable[] elemInfoTable = new ElemInfoTable[2];
	static
	{
		elemInfoTable[0] = new ElemInfoTable(ElementName.AUTOMATEDOVERPRINTPARAMS, 0x33333333);
		elemInfoTable[1] = new ElemInfoTable(ElementName.TRANSFERFUNCTIONCONTROL, 0x33333333);
	}

	@Override
	protected ElementInfo getTheElementInfo()
	{
		return super.getTheElementInfo().updateReplace(elemInfoTable);
	}

	/**
	 * Constructor for JDFAutoSeparationControlParams
	 *
	 * @param myOwnerDocument
	 * @param qualifiedName
	 */
	protected JDFAutoSeparationControlParams(CoreDocumentImpl myOwnerDocument, String qualifiedName)
	{
		super(myOwnerDocument, qualifiedName);
	}

	/**
	 * Constructor for JDFAutoSeparationControlParams
	 *
	 * @param myOwnerDocument
	 * @param myNamespaceURI
	 * @param qualifiedName
	 */
	protected JDFAutoSeparationControlParams(CoreDocumentImpl myOwnerDocument, String myNamespaceURI, String qualifiedName)
	{
		super(myOwnerDocument, myNamespaceURI, qualifiedName);
	}

	/**
	 * Constructor for JDFAutoSeparationControlParams
	 *
	 * @param myOwnerDocument
	 * @param myNamespaceURI
	 * @param qualifiedName
	 * @param myLocalName
	 */
	protected JDFAutoSeparationControlParams(CoreDocumentImpl myOwnerDocument, String myNamespaceURI, String qualifiedName, String myLocalName)
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

	/* ***********************************************************************
	 * Element getter / setter
	 * ***********************************************************************
	 */

	/**
	 * (26) getCreateAutomatedOverPrintParams
	 *
	 * @param iSkip number of elements to skip
	 * @return JDFAutomatedOverPrintParams the element
	 */
	public JDFAutomatedOverPrintParams getCreateAutomatedOverPrintParams(int iSkip)
	{
		return (JDFAutomatedOverPrintParams) getCreateElement_JDFElement(ElementName.AUTOMATEDOVERPRINTPARAMS, null, iSkip);
	}

	/**
	 * (27) const get element AutomatedOverPrintParams
	 *
	 * @param iSkip number of elements to skip
	 * @return JDFAutomatedOverPrintParams the element default is getAutomatedOverPrintParams(0)
	 */
	public JDFAutomatedOverPrintParams getAutomatedOverPrintParams(int iSkip)
	{
		return (JDFAutomatedOverPrintParams) getElement(ElementName.AUTOMATEDOVERPRINTPARAMS, null, iSkip);
	}

	/**
	 * Get all AutomatedOverPrintParams from the current element
	 *
	 * @return Collection<JDFAutomatedOverPrintParams>, null if none are available
	 */
	public Collection<JDFAutomatedOverPrintParams> getAllAutomatedOverPrintParams()
	{
		return getChildArrayByClass(JDFAutomatedOverPrintParams.class, false, 0);
	}

	/**
	 * (30) append element AutomatedOverPrintParams
	 *
	 * @return JDFAutomatedOverPrintParams the element
	 */
	public JDFAutomatedOverPrintParams appendAutomatedOverPrintParams()
	{
		return (JDFAutomatedOverPrintParams) appendElement(ElementName.AUTOMATEDOVERPRINTPARAMS, null);
	}

	/**
	 * (31) create inter-resource link to refTarget
	 *
	 * @param refTarget the element that is referenced
	 */
	public void refAutomatedOverPrintParams(JDFAutomatedOverPrintParams refTarget)
	{
		refElement(refTarget);
	}

	/**
	 * (26) getCreateTransferFunctionControl
	 *
	 * @param iSkip number of elements to skip
	 * @return JDFTransferFunctionControl the element
	 */
	public JDFTransferFunctionControl getCreateTransferFunctionControl(int iSkip)
	{
		return (JDFTransferFunctionControl) getCreateElement_JDFElement(ElementName.TRANSFERFUNCTIONCONTROL, null, iSkip);
	}

	/**
	 * (27) const get element TransferFunctionControl
	 *
	 * @param iSkip number of elements to skip
	 * @return JDFTransferFunctionControl the element default is getTransferFunctionControl(0)
	 */
	public JDFTransferFunctionControl getTransferFunctionControl(int iSkip)
	{
		return (JDFTransferFunctionControl) getElement(ElementName.TRANSFERFUNCTIONCONTROL, null, iSkip);
	}

	/**
	 * Get all TransferFunctionControl from the current element
	 *
	 * @return Collection<JDFTransferFunctionControl>, null if none are available
	 */
	public Collection<JDFTransferFunctionControl> getAllTransferFunctionControl()
	{
		return getChildArrayByClass(JDFTransferFunctionControl.class, false, 0);
	}

	/**
	 * (30) append element TransferFunctionControl
	 *
	 * @return JDFTransferFunctionControl the element
	 */
	public JDFTransferFunctionControl appendTransferFunctionControl()
	{
		return (JDFTransferFunctionControl) appendElement(ElementName.TRANSFERFUNCTIONCONTROL, null);
	}

	/**
	 * (31) create inter-resource link to refTarget
	 *
	 * @param refTarget the element that is referenced
	 */
	public void refTransferFunctionControl(JDFTransferFunctionControl refTarget)
	{
		refElement(refTarget);
	}

}
