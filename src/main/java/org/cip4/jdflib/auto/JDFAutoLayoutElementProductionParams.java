/*
 * The CIP4 Software License, Version 1.0
 *
 *
 * Copyright (c) 2001-2024 The International Cooperation for the Integration of
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
import org.cip4.jdflib.resource.devicecapability.JDFActionPool;
import org.cip4.jdflib.resource.devicecapability.JDFTestPool;
import org.cip4.jdflib.resource.process.JDFFileSpec;
import org.cip4.jdflib.resource.process.JDFLayoutElementPart;
import org.cip4.jdflib.resource.process.JDFShapeDef;

/**
 *****************************************************************************
 * class JDFAutoLayoutElementProductionParams : public JDFResource
 *****************************************************************************
 * 
 */

public abstract class JDFAutoLayoutElementProductionParams extends JDFResource
{

	private static final long serialVersionUID = 1L;

	private static ElemInfoTable[] elemInfoTable = new ElemInfoTable[5];
	static
	{
		elemInfoTable[0] = new ElemInfoTable(ElementName.ACTIONPOOL, 0x6666666111l);
		elemInfoTable[1] = new ElemInfoTable(ElementName.FILESPEC, 0x6666666111l);
		elemInfoTable[2] = new ElemInfoTable(ElementName.LAYOUTELEMENTPART, 0x3333333111l);
		elemInfoTable[3] = new ElemInfoTable(ElementName.SHAPEDEF, 0x6666666111l);
		elemInfoTable[4] = new ElemInfoTable(ElementName.TESTPOOL, 0x6666666111l);
	}

	@Override
	protected ElementInfo getTheElementInfo()
	{
		return super.getTheElementInfo().updateReplace(elemInfoTable);
	}

	/**
	 * Constructor for JDFAutoLayoutElementProductionParams
	 *
	 * @param myOwnerDocument
	 * @param qualifiedName
	 */
	protected JDFAutoLayoutElementProductionParams(CoreDocumentImpl myOwnerDocument, String qualifiedName)
	{
		super(myOwnerDocument, qualifiedName);
	}

	/**
	 * Constructor for JDFAutoLayoutElementProductionParams
	 *
	 * @param myOwnerDocument
	 * @param myNamespaceURI
	 * @param qualifiedName
	 */
	protected JDFAutoLayoutElementProductionParams(CoreDocumentImpl myOwnerDocument, String myNamespaceURI, String qualifiedName)
	{
		super(myOwnerDocument, myNamespaceURI, qualifiedName);
	}

	/**
	 * Constructor for JDFAutoLayoutElementProductionParams
	 *
	 * @param myOwnerDocument
	 * @param myNamespaceURI
	 * @param qualifiedName
	 * @param myLocalName
	 */
	protected JDFAutoLayoutElementProductionParams(CoreDocumentImpl myOwnerDocument, String myNamespaceURI, String qualifiedName, String myLocalName)
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
	 * (24) const get element ActionPool
	 *
	 * @return JDFActionPool the element
	 */
	public JDFActionPool getActionPool()
	{
		return (JDFActionPool) getElement(ElementName.ACTIONPOOL, null, 0);
	}

	/**
	 * (25) getCreateActionPool
	 * 
	 * @return JDFActionPool the element
	 */
	public JDFActionPool getCreateActionPool()
	{
		return (JDFActionPool) getCreateElement_JDFElement(ElementName.ACTIONPOOL, null, 0);
	}

	/**
	 * (29) append element ActionPool
	 *
	 * @return JDFActionPool the element @ if the element already exists
	 */
	public JDFActionPool appendActionPool()
	{
		return (JDFActionPool) appendElementN(ElementName.ACTIONPOOL, 1, null);
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
	 * (26) getCreateLayoutElementPart
	 * 
	 * @param iSkip number of elements to skip
	 * @return JDFLayoutElementPart the element
	 */
	public JDFLayoutElementPart getCreateLayoutElementPart(int iSkip)
	{
		return (JDFLayoutElementPart) getCreateElement_JDFElement(ElementName.LAYOUTELEMENTPART, null, iSkip);
	}

	/**
	 * (27) const get element LayoutElementPart
	 *
	 * @param iSkip number of elements to skip
	 * @return JDFLayoutElementPart the element default is getLayoutElementPart(0)
	 */
	public JDFLayoutElementPart getLayoutElementPart(int iSkip)
	{
		return (JDFLayoutElementPart) getElement(ElementName.LAYOUTELEMENTPART, null, iSkip);
	}

	/**
	 * Get all LayoutElementPart from the current element
	 * 
	 * @return Collection<JDFLayoutElementPart>, null if none are available
	 */
	public Collection<JDFLayoutElementPart> getAllLayoutElementPart()
	{
		return getChildArrayByClass(JDFLayoutElementPart.class, false, 0);
	}

	/**
	 * (30) append element LayoutElementPart
	 *
	 * @return JDFLayoutElementPart the element
	 */
	public JDFLayoutElementPart appendLayoutElementPart()
	{
		return (JDFLayoutElementPart) appendElement(ElementName.LAYOUTELEMENTPART, null);
	}

	/**
	 * (24) const get element ShapeDef
	 *
	 * @return JDFShapeDef the element
	 */
	public JDFShapeDef getShapeDef()
	{
		return (JDFShapeDef) getElement(ElementName.SHAPEDEF, null, 0);
	}

	/**
	 * (25) getCreateShapeDef
	 * 
	 * @return JDFShapeDef the element
	 */
	public JDFShapeDef getCreateShapeDef()
	{
		return (JDFShapeDef) getCreateElement_JDFElement(ElementName.SHAPEDEF, null, 0);
	}

	/**
	 * (29) append element ShapeDef
	 *
	 * @return JDFShapeDef the element @ if the element already exists
	 */
	public JDFShapeDef appendShapeDef()
	{
		return (JDFShapeDef) appendElementN(ElementName.SHAPEDEF, 1, null);
	}

	/**
	 * (31) create inter-resource link to refTarget
	 *
	 * @param refTarget the element that is referenced
	 */
	public void refShapeDef(JDFShapeDef refTarget)
	{
		refElement(refTarget);
	}

	/**
	 * (24) const get element TestPool
	 *
	 * @return JDFTestPool the element
	 */
	public JDFTestPool getTestPool()
	{
		return (JDFTestPool) getElement(ElementName.TESTPOOL, null, 0);
	}

	/**
	 * (25) getCreateTestPool
	 * 
	 * @return JDFTestPool the element
	 */
	public JDFTestPool getCreateTestPool()
	{
		return (JDFTestPool) getCreateElement_JDFElement(ElementName.TESTPOOL, null, 0);
	}

	/**
	 * (29) append element TestPool
	 *
	 * @return JDFTestPool the element @ if the element already exists
	 */
	public JDFTestPool appendTestPool()
	{
		return (JDFTestPool) appendElementN(ElementName.TESTPOOL, 1, null);
	}

}
