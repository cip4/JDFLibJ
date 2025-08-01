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
import org.cip4.jdflib.jmf.JDFCreateLink;
import org.cip4.jdflib.jmf.JDFCreateResource;
import org.cip4.jdflib.jmf.JDFMoveResource;
import org.cip4.jdflib.jmf.JDFRemoveLink;

/**
 *****************************************************************************
 * class JDFAutoUpdateJDFCmdParams : public JDFElement
 *****************************************************************************
 * 
 */

public abstract class JDFAutoUpdateJDFCmdParams extends JDFElement
{

	private static final long serialVersionUID = 1L;

	private static AtrInfoTable[] atrInfoTable = new AtrInfoTable[2];
	static
	{
		atrInfoTable[0] = new AtrInfoTable(AttributeName.PARENTJOBID, 0x2222222222l, AttributeInfo.EnumAttributeType.string, null, null);
		atrInfoTable[1] = new AtrInfoTable(AttributeName.PARENTJOBPARTID, 0x2222222222l, AttributeInfo.EnumAttributeType.string, null, null);
	}

	@Override
	protected AttributeInfo getTheAttributeInfo()
	{
		return super.getTheAttributeInfo().updateReplace(atrInfoTable);
	}

	private static ElemInfoTable[] elemInfoTable = new ElemInfoTable[4];
	static
	{
		elemInfoTable[0] = new ElemInfoTable(ElementName.CREATELINK, 0x3333333333l);
		elemInfoTable[1] = new ElemInfoTable(ElementName.CREATERESOURCE, 0x3333333333l);
		elemInfoTable[2] = new ElemInfoTable(ElementName.MOVERESOURCE, 0x3333333333l);
		elemInfoTable[3] = new ElemInfoTable(ElementName.REMOVELINK, 0x3333333333l);
	}

	@Override
	protected ElementInfo getTheElementInfo()
	{
		return super.getTheElementInfo().updateReplace(elemInfoTable);
	}

	/**
	 * Constructor for JDFAutoUpdateJDFCmdParams
	 *
	 * @param myOwnerDocument
	 * @param qualifiedName
	 */
	protected JDFAutoUpdateJDFCmdParams(CoreDocumentImpl myOwnerDocument, String qualifiedName)
	{
		super(myOwnerDocument, qualifiedName);
	}

	/**
	 * Constructor for JDFAutoUpdateJDFCmdParams
	 *
	 * @param myOwnerDocument
	 * @param myNamespaceURI
	 * @param qualifiedName
	 */
	protected JDFAutoUpdateJDFCmdParams(CoreDocumentImpl myOwnerDocument, String myNamespaceURI, String qualifiedName)
	{
		super(myOwnerDocument, myNamespaceURI, qualifiedName);
	}

	/**
	 * Constructor for JDFAutoUpdateJDFCmdParams
	 *
	 * @param myOwnerDocument
	 * @param myNamespaceURI
	 * @param qualifiedName
	 * @param myLocalName
	 */
	protected JDFAutoUpdateJDFCmdParams(CoreDocumentImpl myOwnerDocument, String myNamespaceURI, String qualifiedName, String myLocalName)
	{
		super(myOwnerDocument, myNamespaceURI, qualifiedName, myLocalName);
	}

	/*
	 * ************************************************************************ Attribute getter / setter ************************************************************************
	 */

	/*
	 * --------------------------------------------------------------------- Methods for Attribute ParentJobID ---------------------------------------------------------------------
	 */
	/**
	 * (36) set attribute ParentJobID
	 *
	 * @param value the value to set the attribute to
	 */
	public void setParentJobID(String value)
	{
		setAttribute(AttributeName.PARENTJOBID, value, null);
	}

	/**
	 * (23) get String attribute ParentJobID
	 *
	 * @return the value of the attribute
	 */
	public String getParentJobID()
	{
		return getAttribute(AttributeName.PARENTJOBID, null, JDFCoreConstants.EMPTYSTRING);
	}

	/*
	 * --------------------------------------------------------------------- Methods for Attribute ParentJobPartID
	 * ---------------------------------------------------------------------
	 */
	/**
	 * (36) set attribute ParentJobPartID
	 *
	 * @param value the value to set the attribute to
	 */
	public void setParentJobPartID(String value)
	{
		setAttribute(AttributeName.PARENTJOBPARTID, value, null);
	}

	/**
	 * (23) get String attribute ParentJobPartID
	 *
	 * @return the value of the attribute
	 */
	public String getParentJobPartID()
	{
		return getAttribute(AttributeName.PARENTJOBPARTID, null, JDFCoreConstants.EMPTYSTRING);
	}

	/*
	 * *********************************************************************** Element getter / setter ***********************************************************************
	 */

	/**
	 * (24) const get element CreateLink
	 *
	 * @return JDFCreateLink the element
	 */
	public JDFCreateLink getCreateLink()
	{
		return (JDFCreateLink) getElement(ElementName.CREATELINK, null, 0);
	}

	/**
	 * (25) getCreateCreateLink
	 * 
	 * @return JDFCreateLink the element
	 */
	public JDFCreateLink getCreateCreateLink()
	{
		return (JDFCreateLink) getCreateElement_JDFElement(ElementName.CREATELINK, null, 0);
	}

	/**
	 * (26) getCreateCreateLink
	 * 
	 * @param iSkip number of elements to skip
	 * @return JDFCreateLink the element
	 */
	public JDFCreateLink getCreateCreateLink(int iSkip)
	{
		return (JDFCreateLink) getCreateElement_JDFElement(ElementName.CREATELINK, null, iSkip);
	}

	/**
	 * (27) const get element CreateLink
	 *
	 * @param iSkip number of elements to skip
	 * @return JDFCreateLink the element default is getCreateLink(0)
	 */
	public JDFCreateLink getCreateLink(int iSkip)
	{
		return (JDFCreateLink) getElement(ElementName.CREATELINK, null, iSkip);
	}

	/**
	 * Get all CreateLink from the current element
	 * 
	 * @return Collection<JDFCreateLink>, null if none are available
	 */
	public Collection<JDFCreateLink> getAllCreateLink()
	{
		return getChildArrayByClass(JDFCreateLink.class, false, 0);
	}

	/**
	 * (30) append element CreateLink
	 *
	 * @return JDFCreateLink the element
	 */
	public JDFCreateLink appendCreateLink()
	{
		return (JDFCreateLink) appendElement(ElementName.CREATELINK, null);
	}

	/**
	 * (24) const get element CreateResource
	 *
	 * @return JDFCreateResource the element
	 */
	public JDFCreateResource getCreateResource()
	{
		return (JDFCreateResource) getElement(ElementName.CREATERESOURCE, null, 0);
	}

	/**
	 * (25) getCreateCreateResource
	 * 
	 * @return JDFCreateResource the element
	 */
	public JDFCreateResource getCreateCreateResource()
	{
		return (JDFCreateResource) getCreateElement_JDFElement(ElementName.CREATERESOURCE, null, 0);
	}

	/**
	 * (26) getCreateCreateResource
	 * 
	 * @param iSkip number of elements to skip
	 * @return JDFCreateResource the element
	 */
	public JDFCreateResource getCreateCreateResource(int iSkip)
	{
		return (JDFCreateResource) getCreateElement_JDFElement(ElementName.CREATERESOURCE, null, iSkip);
	}

	/**
	 * (27) const get element CreateResource
	 *
	 * @param iSkip number of elements to skip
	 * @return JDFCreateResource the element default is getCreateResource(0)
	 */
	public JDFCreateResource getCreateResource(int iSkip)
	{
		return (JDFCreateResource) getElement(ElementName.CREATERESOURCE, null, iSkip);
	}

	/**
	 * Get all CreateResource from the current element
	 * 
	 * @return Collection<JDFCreateResource>, null if none are available
	 */
	public Collection<JDFCreateResource> getAllCreateResource()
	{
		return getChildArrayByClass(JDFCreateResource.class, false, 0);
	}

	/**
	 * (30) append element CreateResource
	 *
	 * @return JDFCreateResource the element
	 */
	public JDFCreateResource appendCreateResource()
	{
		return (JDFCreateResource) appendElement(ElementName.CREATERESOURCE, null);
	}

	/**
	 * (24) const get element MoveResource
	 *
	 * @return JDFMoveResource the element
	 */
	public JDFMoveResource getMoveResource()
	{
		return (JDFMoveResource) getElement(ElementName.MOVERESOURCE, null, 0);
	}

	/**
	 * (25) getCreateMoveResource
	 * 
	 * @return JDFMoveResource the element
	 */
	public JDFMoveResource getCreateMoveResource()
	{
		return (JDFMoveResource) getCreateElement_JDFElement(ElementName.MOVERESOURCE, null, 0);
	}

	/**
	 * (26) getCreateMoveResource
	 * 
	 * @param iSkip number of elements to skip
	 * @return JDFMoveResource the element
	 */
	public JDFMoveResource getCreateMoveResource(int iSkip)
	{
		return (JDFMoveResource) getCreateElement_JDFElement(ElementName.MOVERESOURCE, null, iSkip);
	}

	/**
	 * (27) const get element MoveResource
	 *
	 * @param iSkip number of elements to skip
	 * @return JDFMoveResource the element default is getMoveResource(0)
	 */
	public JDFMoveResource getMoveResource(int iSkip)
	{
		return (JDFMoveResource) getElement(ElementName.MOVERESOURCE, null, iSkip);
	}

	/**
	 * Get all MoveResource from the current element
	 * 
	 * @return Collection<JDFMoveResource>, null if none are available
	 */
	public Collection<JDFMoveResource> getAllMoveResource()
	{
		return getChildArrayByClass(JDFMoveResource.class, false, 0);
	}

	/**
	 * (30) append element MoveResource
	 *
	 * @return JDFMoveResource the element
	 */
	public JDFMoveResource appendMoveResource()
	{
		return (JDFMoveResource) appendElement(ElementName.MOVERESOURCE, null);
	}

	/**
	 * (24) const get element RemoveLink
	 *
	 * @return JDFRemoveLink the element
	 */
	public JDFRemoveLink getRemoveLink()
	{
		return (JDFRemoveLink) getElement(ElementName.REMOVELINK, null, 0);
	}

	/**
	 * (25) getCreateRemoveLink
	 * 
	 * @return JDFRemoveLink the element
	 */
	public JDFRemoveLink getCreateRemoveLink()
	{
		return (JDFRemoveLink) getCreateElement_JDFElement(ElementName.REMOVELINK, null, 0);
	}

	/**
	 * (26) getCreateRemoveLink
	 * 
	 * @param iSkip number of elements to skip
	 * @return JDFRemoveLink the element
	 */
	public JDFRemoveLink getCreateRemoveLink(int iSkip)
	{
		return (JDFRemoveLink) getCreateElement_JDFElement(ElementName.REMOVELINK, null, iSkip);
	}

	/**
	 * (27) const get element RemoveLink
	 *
	 * @param iSkip number of elements to skip
	 * @return JDFRemoveLink the element default is getRemoveLink(0)
	 */
	public JDFRemoveLink getRemoveLink(int iSkip)
	{
		return (JDFRemoveLink) getElement(ElementName.REMOVELINK, null, iSkip);
	}

	/**
	 * Get all RemoveLink from the current element
	 * 
	 * @return Collection<JDFRemoveLink>, null if none are available
	 */
	public Collection<JDFRemoveLink> getAllRemoveLink()
	{
		return getChildArrayByClass(JDFRemoveLink.class, false, 0);
	}

	/**
	 * (30) append element RemoveLink
	 *
	 * @return JDFRemoveLink the element
	 */
	public JDFRemoveLink appendRemoveLink()
	{
		return (JDFRemoveLink) appendElement(ElementName.REMOVELINK, null);
	}

}
