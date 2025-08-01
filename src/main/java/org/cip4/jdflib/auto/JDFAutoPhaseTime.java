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
import org.cip4.jdflib.core.JDFAudit;
import org.cip4.jdflib.core.JDFCoreConstants;
import org.cip4.jdflib.node.JDFActivity;
import org.cip4.jdflib.resource.JDFDevice;
import org.cip4.jdflib.resource.JDFModulePhase;
import org.cip4.jdflib.resource.JDFPart;
import org.cip4.jdflib.resource.process.JDFEmployee;
import org.cip4.jdflib.resource.process.JDFMISDetails;
import org.cip4.jdflib.util.JDFDate;

/**
 *****************************************************************************
 * class JDFAutoPhaseTime : public JDFAudit
 *****************************************************************************
 * 
 */

public abstract class JDFAutoPhaseTime extends JDFAudit
{

	private static final long serialVersionUID = 1L;

	private static AtrInfoTable[] atrInfoTable = new AtrInfoTable[4];
	static
	{
		atrInfoTable[0] = new AtrInfoTable(AttributeName.END, 0x3333333333l, AttributeInfo.EnumAttributeType.dateTime, null, null);
		atrInfoTable[1] = new AtrInfoTable(AttributeName.START, 0x2222222222l, AttributeInfo.EnumAttributeType.dateTime, null, null);
		atrInfoTable[2] = new AtrInfoTable(AttributeName.STATUS, 0x2222222222l, AttributeInfo.EnumAttributeType.enumeration, EnumNodeStatus.getEnum(0), null);
		atrInfoTable[3] = new AtrInfoTable(AttributeName.STATUSDETAILS, 0x3333333333l, AttributeInfo.EnumAttributeType.shortString, null, null);
	}

	@Override
	protected AttributeInfo getTheAttributeInfo()
	{
		return super.getTheAttributeInfo().updateReplace(atrInfoTable);
	}

	private static ElemInfoTable[] elemInfoTable = new ElemInfoTable[6];
	static
	{
		elemInfoTable[0] = new ElemInfoTable(ElementName.DEVICE, 0x3333333333l);
		elemInfoTable[1] = new ElemInfoTable(ElementName.EMPLOYEE, 0x3333333333l);
		elemInfoTable[2] = new ElemInfoTable(ElementName.ACTIVITY, 0x3333311111l);
		elemInfoTable[3] = new ElemInfoTable(ElementName.MISDETAILS, 0x6666666611l);
		elemInfoTable[4] = new ElemInfoTable(ElementName.MODULEPHASE, 0x3333333333l);
		elemInfoTable[5] = new ElemInfoTable(ElementName.PART, 0x3333333333l);
	}

	@Override
	protected ElementInfo getTheElementInfo()
	{
		return super.getTheElementInfo().updateReplace(elemInfoTable);
	}

	/**
	 * Constructor for JDFAutoPhaseTime
	 *
	 * @param myOwnerDocument
	 * @param qualifiedName
	 */
	protected JDFAutoPhaseTime(CoreDocumentImpl myOwnerDocument, String qualifiedName)
	{
		super(myOwnerDocument, qualifiedName);
	}

	/**
	 * Constructor for JDFAutoPhaseTime
	 *
	 * @param myOwnerDocument
	 * @param myNamespaceURI
	 * @param qualifiedName
	 */
	protected JDFAutoPhaseTime(CoreDocumentImpl myOwnerDocument, String myNamespaceURI, String qualifiedName)
	{
		super(myOwnerDocument, myNamespaceURI, qualifiedName);
	}

	/**
	 * Constructor for JDFAutoPhaseTime
	 *
	 * @param myOwnerDocument
	 * @param myNamespaceURI
	 * @param qualifiedName
	 * @param myLocalName
	 */
	protected JDFAutoPhaseTime(CoreDocumentImpl myOwnerDocument, String myNamespaceURI, String qualifiedName, String myLocalName)
	{
		super(myOwnerDocument, myNamespaceURI, qualifiedName, myLocalName);
	}

	/*
	 * ************************************************************************ Attribute getter / setter ************************************************************************
	 */

	/*
	 * --------------------------------------------------------------------- Methods for Attribute End ---------------------------------------------------------------------
	 */
	/**
	 * (11) set attribute End
	 *
	 * @param value the value to set the attribute to or null
	 */
	public void setEnd(JDFDate value)
	{
		JDFDate date = value;
		if (date == null)
		{
			date = new JDFDate();
		}
		setAttribute(AttributeName.END, date.getDateTimeISO(), null);
	}

	/**
	 * (12) get JDFDate attribute End
	 *
	 * @return JDFDate the value of the attribute
	 */
	public JDFDate getEnd()
	{
		final String str = getAttribute(AttributeName.END, null, null);
		final JDFDate ret = JDFDate.createDate(str);
		return ret;
	}

	/*
	 * --------------------------------------------------------------------- Methods for Attribute Start ---------------------------------------------------------------------
	 */
	/**
	 * (11) set attribute Start
	 *
	 * @param value the value to set the attribute to or null
	 */
	public void setStart(JDFDate value)
	{
		JDFDate date = value;
		if (date == null)
		{
			date = new JDFDate();
		}
		setAttribute(AttributeName.START, date.getDateTimeISO(), null);
	}

	/**
	 * (12) get JDFDate attribute Start
	 *
	 * @return JDFDate the value of the attribute
	 */
	public JDFDate getStart()
	{
		final String str = getAttribute(AttributeName.START, null, null);
		final JDFDate ret = JDFDate.createDate(str);
		return ret;
	}

	/*
	 * --------------------------------------------------------------------- Methods for Attribute StatusDetails
	 * ---------------------------------------------------------------------
	 */
	/**
	 * (36) set attribute StatusDetails
	 *
	 * @param value the value to set the attribute to
	 */
	public void setStatusDetails(String value)
	{
		setAttribute(AttributeName.STATUSDETAILS, value, null);
	}

	/**
	 * (23) get String attribute StatusDetails
	 *
	 * @return the value of the attribute
	 */
	public String getStatusDetails()
	{
		return getAttribute(AttributeName.STATUSDETAILS, null, JDFCoreConstants.EMPTYSTRING);
	}

	/*
	 * *********************************************************************** Element getter / setter ***********************************************************************
	 */

	/**
	 * (24) const get element Device
	 *
	 * @return JDFDevice the element
	 */
	public JDFDevice getDevice()
	{
		return (JDFDevice) getElement(ElementName.DEVICE, null, 0);
	}

	/**
	 * (25) getCreateDevice
	 * 
	 * @return JDFDevice the element
	 */
	public JDFDevice getCreateDevice()
	{
		return (JDFDevice) getCreateElement_JDFElement(ElementName.DEVICE, null, 0);
	}

	/**
	 * (26) getCreateDevice
	 * 
	 * @param iSkip number of elements to skip
	 * @return JDFDevice the element
	 */
	public JDFDevice getCreateDevice(int iSkip)
	{
		return (JDFDevice) getCreateElement_JDFElement(ElementName.DEVICE, null, iSkip);
	}

	/**
	 * (27) const get element Device
	 *
	 * @param iSkip number of elements to skip
	 * @return JDFDevice the element default is getDevice(0)
	 */
	public JDFDevice getDevice(int iSkip)
	{
		return (JDFDevice) getElement(ElementName.DEVICE, null, iSkip);
	}

	/**
	 * Get all Device from the current element
	 * 
	 * @return Collection<JDFDevice>, null if none are available
	 */
	public Collection<JDFDevice> getAllDevice()
	{
		return getChildArrayByClass(JDFDevice.class, false, 0);
	}

	/**
	 * (30) append element Device
	 *
	 * @return JDFDevice the element
	 */
	public JDFDevice appendDevice()
	{
		return (JDFDevice) appendElement(ElementName.DEVICE, null);
	}

	/**
	 * (31) create inter-resource link to refTarget
	 *
	 * @param refTarget the element that is referenced
	 */
	public void refDevice(JDFDevice refTarget)
	{
		refElement(refTarget);
	}

	/**
	 * (24) const get element Employee
	 *
	 * @return JDFEmployee the element
	 */
	public JDFEmployee getEmployee()
	{
		return (JDFEmployee) getElement(ElementName.EMPLOYEE, null, 0);
	}

	/**
	 * (25) getCreateEmployee
	 * 
	 * @return JDFEmployee the element
	 */
	public JDFEmployee getCreateEmployee()
	{
		return (JDFEmployee) getCreateElement_JDFElement(ElementName.EMPLOYEE, null, 0);
	}

	/**
	 * (26) getCreateEmployee
	 * 
	 * @param iSkip number of elements to skip
	 * @return JDFEmployee the element
	 */
	@Override
	public JDFEmployee getCreateEmployee(int iSkip)
	{
		return (JDFEmployee) getCreateElement_JDFElement(ElementName.EMPLOYEE, null, iSkip);
	}

	/**
	 * (27) const get element Employee
	 *
	 * @param iSkip number of elements to skip
	 * @return JDFEmployee the element default is getEmployee(0)
	 */
	@Override
	public JDFEmployee getEmployee(int iSkip)
	{
		return (JDFEmployee) getElement(ElementName.EMPLOYEE, null, iSkip);
	}

	/**
	 * Get all Employee from the current element
	 * 
	 * @return Collection<JDFEmployee>, null if none are available
	 */
	@Override
	public Collection<JDFEmployee> getAllEmployee()
	{
		return getChildArrayByClass(JDFEmployee.class, false, 0);
	}

	/**
	 * (30) append element Employee
	 *
	 * @return JDFEmployee the element
	 */
	@Override
	public JDFEmployee appendEmployee()
	{
		return (JDFEmployee) appendElement(ElementName.EMPLOYEE, null);
	}

	/**
	 * (31) create inter-resource link to refTarget
	 *
	 * @param refTarget the element that is referenced
	 */
	public void refEmployee(JDFEmployee refTarget)
	{
		refElement(refTarget);
	}

	/**
	 * (24) const get element Activity
	 *
	 * @return JDFActivity the element
	 */
	public JDFActivity getActivity()
	{
		return (JDFActivity) getElement(ElementName.ACTIVITY, null, 0);
	}

	/**
	 * (25) getCreateActivity
	 * 
	 * @return JDFActivity the element
	 */
	public JDFActivity getCreateActivity()
	{
		return (JDFActivity) getCreateElement_JDFElement(ElementName.ACTIVITY, null, 0);
	}

	/**
	 * (26) getCreateActivity
	 * 
	 * @param iSkip number of elements to skip
	 * @return JDFActivity the element
	 */
	public JDFActivity getCreateActivity(int iSkip)
	{
		return (JDFActivity) getCreateElement_JDFElement(ElementName.ACTIVITY, null, iSkip);
	}

	/**
	 * (27) const get element Activity
	 *
	 * @param iSkip number of elements to skip
	 * @return JDFActivity the element default is getActivity(0)
	 */
	public JDFActivity getActivity(int iSkip)
	{
		return (JDFActivity) getElement(ElementName.ACTIVITY, null, iSkip);
	}

	/**
	 * Get all Activity from the current element
	 * 
	 * @return Collection<JDFActivity>, null if none are available
	 */
	public Collection<JDFActivity> getAllActivity()
	{
		return getChildArrayByClass(JDFActivity.class, false, 0);
	}

	/**
	 * (30) append element Activity
	 *
	 * @return JDFActivity the element
	 */
	public JDFActivity appendActivity()
	{
		return (JDFActivity) appendElement(ElementName.ACTIVITY, null);
	}

	/**
	 * (24) const get element MISDetails
	 *
	 * @return JDFMISDetails the element
	 */
	public JDFMISDetails getMISDetails()
	{
		return (JDFMISDetails) getElement(ElementName.MISDETAILS, null, 0);
	}

	/**
	 * (25) getCreateMISDetails
	 * 
	 * @return JDFMISDetails the element
	 */
	public JDFMISDetails getCreateMISDetails()
	{
		return (JDFMISDetails) getCreateElement_JDFElement(ElementName.MISDETAILS, null, 0);
	}

	/**
	 * (29) append element MISDetails
	 *
	 * @return JDFMISDetails the element @ if the element already exists
	 */
	public JDFMISDetails appendMISDetails()
	{
		return (JDFMISDetails) appendElementN(ElementName.MISDETAILS, 1, null);
	}

	/**
	 * (24) const get element ModulePhase
	 *
	 * @return JDFModulePhase the element
	 */
	public JDFModulePhase getModulePhase()
	{
		return (JDFModulePhase) getElement(ElementName.MODULEPHASE, null, 0);
	}

	/**
	 * (25) getCreateModulePhase
	 * 
	 * @return JDFModulePhase the element
	 */
	public JDFModulePhase getCreateModulePhase()
	{
		return (JDFModulePhase) getCreateElement_JDFElement(ElementName.MODULEPHASE, null, 0);
	}

	/**
	 * (26) getCreateModulePhase
	 * 
	 * @param iSkip number of elements to skip
	 * @return JDFModulePhase the element
	 */
	public JDFModulePhase getCreateModulePhase(int iSkip)
	{
		return (JDFModulePhase) getCreateElement_JDFElement(ElementName.MODULEPHASE, null, iSkip);
	}

	/**
	 * (27) const get element ModulePhase
	 *
	 * @param iSkip number of elements to skip
	 * @return JDFModulePhase the element default is getModulePhase(0)
	 */
	public JDFModulePhase getModulePhase(int iSkip)
	{
		return (JDFModulePhase) getElement(ElementName.MODULEPHASE, null, iSkip);
	}

	/**
	 * Get all ModulePhase from the current element
	 * 
	 * @return Collection<JDFModulePhase>, null if none are available
	 */
	public Collection<JDFModulePhase> getAllModulePhase()
	{
		return getChildArrayByClass(JDFModulePhase.class, false, 0);
	}

	/**
	 * (30) append element ModulePhase
	 *
	 * @return JDFModulePhase the element
	 */
	public JDFModulePhase appendModulePhase()
	{
		return (JDFModulePhase) appendElement(ElementName.MODULEPHASE, null);
	}

	/**
	 * (24) const get element Part
	 *
	 * @return JDFPart the element
	 */
	public JDFPart getPart()
	{
		return (JDFPart) getElement(ElementName.PART, null, 0);
	}

	/**
	 * (25) getCreatePart
	 * 
	 * @return JDFPart the element
	 */
	public JDFPart getCreatePart()
	{
		return (JDFPart) getCreateElement_JDFElement(ElementName.PART, null, 0);
	}

	/**
	 * (26) getCreatePart
	 * 
	 * @param iSkip number of elements to skip
	 * @return JDFPart the element
	 */
	public JDFPart getCreatePart(int iSkip)
	{
		return (JDFPart) getCreateElement_JDFElement(ElementName.PART, null, iSkip);
	}

	/**
	 * (27) const get element Part
	 *
	 * @param iSkip number of elements to skip
	 * @return JDFPart the element default is getPart(0)
	 */
	public JDFPart getPart(int iSkip)
	{
		return (JDFPart) getElement(ElementName.PART, null, iSkip);
	}

	/**
	 * Get all Part from the current element
	 * 
	 * @return Collection<JDFPart>, null if none are available
	 */
	public Collection<JDFPart> getAllPart()
	{
		return getChildArrayByClass(JDFPart.class, false, 0);
	}

	/**
	 * (30) append element Part
	 *
	 * @return JDFPart the element
	 */
	public JDFPart appendPart()
	{
		return (JDFPart) appendElement(ElementName.PART, null);
	}

}
