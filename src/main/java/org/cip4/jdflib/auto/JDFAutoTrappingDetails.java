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
import org.cip4.jdflib.resource.JDFResource;
import org.cip4.jdflib.resource.process.JDFObjectResolution;
import org.cip4.jdflib.resource.process.JDFTrapRegion;
import org.cip4.jdflib.resource.process.prepress.JDFTrappingOrder;
import org.cip4.jdflib.resource.process.prepress.JDFTrappingParams;

/**
 *****************************************************************************
 * class JDFAutoTrappingDetails : public JDFResource
 *****************************************************************************
 * 
 */

public abstract class JDFAutoTrappingDetails extends JDFResource
{

	private static final long serialVersionUID = 1L;

	private static AtrInfoTable[] atrInfoTable = new AtrInfoTable[4];
	static
	{
		atrInfoTable[0] = new AtrInfoTable(AttributeName.DEFAULTTRAPPING, 0x3333333333l, AttributeInfo.EnumAttributeType.boolean_, null, "false");
		atrInfoTable[1] = new AtrInfoTable(AttributeName.IGNOREFILEPARAMS, 0x4444443333l, AttributeInfo.EnumAttributeType.boolean_, null, null);
		atrInfoTable[2] = new AtrInfoTable(AttributeName.TRAPPING, 0x4444444433l, AttributeInfo.EnumAttributeType.boolean_, null, null);
		atrInfoTable[3] = new AtrInfoTable(AttributeName.TRAPPINGTYPE, 0x4444444433l, AttributeInfo.EnumAttributeType.integer, null, null);
	}

	@Override
	protected AttributeInfo getTheAttributeInfo()
	{
		return super.getTheAttributeInfo().updateReplace(atrInfoTable);
	}

	private static ElemInfoTable[] elemInfoTable = new ElemInfoTable[4];
	static
	{
		elemInfoTable[0] = new ElemInfoTable(ElementName.TRAPPINGORDER, 0x6666666666l);
		elemInfoTable[1] = new ElemInfoTable(ElementName.TRAPPINGPARAMS, 0x6666666666l);
		elemInfoTable[2] = new ElemInfoTable(ElementName.OBJECTRESOLUTION, 0x3333333331l);
		elemInfoTable[3] = new ElemInfoTable(ElementName.TRAPREGION, 0x3333333333l);
	}

	@Override
	protected ElementInfo getTheElementInfo()
	{
		return super.getTheElementInfo().updateReplace(elemInfoTable);
	}

	/**
	 * Constructor for JDFAutoTrappingDetails
	 *
	 * @param myOwnerDocument
	 * @param qualifiedName
	 */
	protected JDFAutoTrappingDetails(CoreDocumentImpl myOwnerDocument, String qualifiedName)
	{
		super(myOwnerDocument, qualifiedName);
	}

	/**
	 * Constructor for JDFAutoTrappingDetails
	 *
	 * @param myOwnerDocument
	 * @param myNamespaceURI
	 * @param qualifiedName
	 */
	protected JDFAutoTrappingDetails(CoreDocumentImpl myOwnerDocument, String myNamespaceURI, String qualifiedName)
	{
		super(myOwnerDocument, myNamespaceURI, qualifiedName);
	}

	/**
	 * Constructor for JDFAutoTrappingDetails
	 *
	 * @param myOwnerDocument
	 * @param myNamespaceURI
	 * @param qualifiedName
	 * @param myLocalName
	 */
	protected JDFAutoTrappingDetails(CoreDocumentImpl myOwnerDocument, String myNamespaceURI, String qualifiedName, String myLocalName)
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
	 * --------------------------------------------------------------------- Methods for Attribute DefaultTrapping
	 * ---------------------------------------------------------------------
	 */
	/**
	 * (36) set attribute DefaultTrapping
	 *
	 * @param value the value to set the attribute to
	 */
	public void setDefaultTrapping(boolean value)
	{
		setAttribute(AttributeName.DEFAULTTRAPPING, value, null);
	}

	/**
	 * (18) get boolean attribute DefaultTrapping
	 *
	 * @return boolean the value of the attribute
	 */
	public boolean getDefaultTrapping()
	{
		return getBoolAttribute(AttributeName.DEFAULTTRAPPING, null, false);
	}

	/*
	 * --------------------------------------------------------------------- Methods for Attribute IgnoreFileParams
	 * ---------------------------------------------------------------------
	 */
	/**
	 * (36) set attribute IgnoreFileParams
	 *
	 * @param value the value to set the attribute to
	 */
	public void setIgnoreFileParams(boolean value)
	{
		setAttribute(AttributeName.IGNOREFILEPARAMS, value, null);
	}

	/**
	 * (18) get boolean attribute IgnoreFileParams
	 *
	 * @return boolean the value of the attribute
	 */
	public boolean getIgnoreFileParams()
	{
		return getBoolAttribute(AttributeName.IGNOREFILEPARAMS, null, false);
	}

	/*
	 * --------------------------------------------------------------------- Methods for Attribute Trapping ---------------------------------------------------------------------
	 */
	/**
	 * (36) set attribute Trapping
	 *
	 * @param value the value to set the attribute to
	 */
	public void setTrapping(boolean value)
	{
		setAttribute(AttributeName.TRAPPING, value, null);
	}

	/**
	 * (18) get boolean attribute Trapping
	 *
	 * @return boolean the value of the attribute
	 */
	public boolean getTrapping()
	{
		return getBoolAttribute(AttributeName.TRAPPING, null, false);
	}

	/*
	 * --------------------------------------------------------------------- Methods for Attribute TrappingType
	 * ---------------------------------------------------------------------
	 */
	/**
	 * (36) set attribute TrappingType
	 *
	 * @param value the value to set the attribute to
	 */
	public void setTrappingType(int value)
	{
		setAttribute(AttributeName.TRAPPINGTYPE, value, null);
	}

	/**
	 * (15) get int attribute TrappingType
	 *
	 * @return int the value of the attribute
	 */
	public int getTrappingType()
	{
		return getIntAttribute(AttributeName.TRAPPINGTYPE, null, 0);
	}

	/*
	 * *********************************************************************** Element getter / setter ***********************************************************************
	 */

	/**
	 * (24) const get element TrappingOrder
	 *
	 * @return JDFTrappingOrder the element
	 */
	public JDFTrappingOrder getTrappingOrder()
	{
		return (JDFTrappingOrder) getElement(ElementName.TRAPPINGORDER, null, 0);
	}

	/**
	 * (25) getCreateTrappingOrder
	 * 
	 * @return JDFTrappingOrder the element
	 */
	public JDFTrappingOrder getCreateTrappingOrder()
	{
		return (JDFTrappingOrder) getCreateElement_JDFElement(ElementName.TRAPPINGORDER, null, 0);
	}

	/**
	 * (29) append element TrappingOrder
	 *
	 * @return JDFTrappingOrder the element @ if the element already exists
	 */
	public JDFTrappingOrder appendTrappingOrder()
	{
		return (JDFTrappingOrder) appendElementN(ElementName.TRAPPINGORDER, 1, null);
	}

	/**
	 * (24) const get element TrappingParams
	 *
	 * @return JDFTrappingParams the element
	 */
	public JDFTrappingParams getTrappingParams()
	{
		return (JDFTrappingParams) getElement(ElementName.TRAPPINGPARAMS, null, 0);
	}

	/**
	 * (25) getCreateTrappingParams
	 * 
	 * @return JDFTrappingParams the element
	 */
	public JDFTrappingParams getCreateTrappingParams()
	{
		return (JDFTrappingParams) getCreateElement_JDFElement(ElementName.TRAPPINGPARAMS, null, 0);
	}

	/**
	 * (29) append element TrappingParams
	 *
	 * @return JDFTrappingParams the element @ if the element already exists
	 */
	public JDFTrappingParams appendTrappingParams()
	{
		return (JDFTrappingParams) appendElementN(ElementName.TRAPPINGPARAMS, 1, null);
	}

	/**
	 * (31) create inter-resource link to refTarget
	 *
	 * @param refTarget the element that is referenced
	 */
	public void refTrappingParams(JDFTrappingParams refTarget)
	{
		refElement(refTarget);
	}

	/**
	 * (24) const get element ObjectResolution
	 *
	 * @return JDFObjectResolution the element
	 */
	public JDFObjectResolution getObjectResolution()
	{
		return (JDFObjectResolution) getElement(ElementName.OBJECTRESOLUTION, null, 0);
	}

	/**
	 * (25) getCreateObjectResolution
	 * 
	 * @return JDFObjectResolution the element
	 */
	public JDFObjectResolution getCreateObjectResolution()
	{
		return (JDFObjectResolution) getCreateElement_JDFElement(ElementName.OBJECTRESOLUTION, null, 0);
	}

	/**
	 * (26) getCreateObjectResolution
	 * 
	 * @param iSkip number of elements to skip
	 * @return JDFObjectResolution the element
	 */
	public JDFObjectResolution getCreateObjectResolution(int iSkip)
	{
		return (JDFObjectResolution) getCreateElement_JDFElement(ElementName.OBJECTRESOLUTION, null, iSkip);
	}

	/**
	 * (27) const get element ObjectResolution
	 *
	 * @param iSkip number of elements to skip
	 * @return JDFObjectResolution the element default is getObjectResolution(0)
	 */
	public JDFObjectResolution getObjectResolution(int iSkip)
	{
		return (JDFObjectResolution) getElement(ElementName.OBJECTRESOLUTION, null, iSkip);
	}

	/**
	 * Get all ObjectResolution from the current element
	 * 
	 * @return Collection<JDFObjectResolution>, null if none are available
	 */
	public Collection<JDFObjectResolution> getAllObjectResolution()
	{
		return getChildArrayByClass(JDFObjectResolution.class, false, 0);
	}

	/**
	 * (30) append element ObjectResolution
	 *
	 * @return JDFObjectResolution the element
	 */
	public JDFObjectResolution appendObjectResolution()
	{
		return (JDFObjectResolution) appendElement(ElementName.OBJECTRESOLUTION, null);
	}

	/**
	 * (24) const get element TrapRegion
	 *
	 * @return JDFTrapRegion the element
	 */
	public JDFTrapRegion getTrapRegion()
	{
		return (JDFTrapRegion) getElement(ElementName.TRAPREGION, null, 0);
	}

	/**
	 * (25) getCreateTrapRegion
	 * 
	 * @return JDFTrapRegion the element
	 */
	public JDFTrapRegion getCreateTrapRegion()
	{
		return (JDFTrapRegion) getCreateElement_JDFElement(ElementName.TRAPREGION, null, 0);
	}

	/**
	 * (26) getCreateTrapRegion
	 * 
	 * @param iSkip number of elements to skip
	 * @return JDFTrapRegion the element
	 */
	public JDFTrapRegion getCreateTrapRegion(int iSkip)
	{
		return (JDFTrapRegion) getCreateElement_JDFElement(ElementName.TRAPREGION, null, iSkip);
	}

	/**
	 * (27) const get element TrapRegion
	 *
	 * @param iSkip number of elements to skip
	 * @return JDFTrapRegion the element default is getTrapRegion(0)
	 */
	public JDFTrapRegion getTrapRegion(int iSkip)
	{
		return (JDFTrapRegion) getElement(ElementName.TRAPREGION, null, iSkip);
	}

	/**
	 * Get all TrapRegion from the current element
	 * 
	 * @return Collection<JDFTrapRegion>, null if none are available
	 */
	public Collection<JDFTrapRegion> getAllTrapRegion()
	{
		return getChildArrayByClass(JDFTrapRegion.class, false, 0);
	}

	/**
	 * (30) append element TrapRegion
	 *
	 * @return JDFTrapRegion the element
	 */
	public JDFTrapRegion appendTrapRegion()
	{
		return (JDFTrapRegion) appendElement(ElementName.TRAPREGION, null);
	}

	/**
	 * (31) create inter-resource link to refTarget
	 *
	 * @param refTarget the element that is referenced
	 */
	public void refTrapRegion(JDFTrapRegion refTarget)
	{
		refElement(refTarget);
	}

}
