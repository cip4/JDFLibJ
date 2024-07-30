/*--------------------------------------------------------------------------------------------------
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
 */
/**
 * ========================================================================== class JDFDeviceInfo extends JDFResource ==========================================================================
 *
 * @COPYRIGHT Heidelberger Druckmaschinen AG, 1999-2001 ALL RIGHTS RESERVED Author: sabjon@topmail.de using a code generator Warning! very preliminary test version. Interface subject to change without
 *            prior notice! Revision history: ...
 **/

package org.cip4.jdflib.jmf;

import java.util.Collection;

import org.apache.xerces.dom.CoreDocumentImpl;
import org.cip4.jdflib.auto.JDFAutoDeviceInfo;
import org.cip4.jdflib.core.AtrInfoTable;
import org.cip4.jdflib.core.AttributeInfo;
import org.cip4.jdflib.core.AttributeName;
import org.cip4.jdflib.core.ElemInfoTable;
import org.cip4.jdflib.core.ElementInfo;
import org.cip4.jdflib.core.ElementName;
import org.cip4.jdflib.core.JDFResourceLink;
import org.cip4.jdflib.core.KElement;
import org.cip4.jdflib.core.VElement;
import org.cip4.jdflib.core.VString;
import org.cip4.jdflib.datatypes.VJDFAttributeMap;
import org.cip4.jdflib.extensions.XJDFEnums.eDeviceStatus;
import org.cip4.jdflib.node.JDFNode;
import org.cip4.jdflib.resource.JDFDevice;
import org.cip4.jdflib.resource.JDFEvent;
import org.cip4.jdflib.resource.JDFModulePhase;
import org.cip4.jdflib.resource.JDFPhaseTime;
import org.cip4.jdflib.resource.process.JDFEmployee;
import org.cip4.jdflib.resource.process.JDFMISDetails;
import org.cip4.jdflib.util.ContainerUtil;
import org.cip4.jdflib.util.StringUtil;

// ----------------------------------
/**
 * @author Dr. Rainer Prosi, Heidelberger Druckmaschinen AG
 *
 *         much prior to May 17, 2009
 */
public class JDFDeviceInfo extends JDFAutoDeviceInfo
{
	private static AtrInfoTable[] atrInfoTable = new AtrInfoTable[1];
	static
	{
		atrInfoTable[0] = new AtrInfoTable(AttributeName.IDLESTARTTIME, 0x33333000, AttributeInfo.EnumAttributeType.dateTime, null, null);
	}

	/**
	 * @see org.cip4.jdflib.auto.JDFAutoDeviceInfo#getTheAttributeInfo()
	 */
	@Override
	protected AttributeInfo getTheAttributeInfo()
	{
		return super.getTheAttributeInfo().updateReplace(atrInfoTable);
	}

	private static ElemInfoTable[] elemInfoTable = new ElemInfoTable[2];
	static
	{
		elemInfoTable[0] = new ElemInfoTable(ElementName.EMPLOYEE, 0x33333333);
		elemInfoTable[1] = new ElemInfoTable(ElementName.EVENT, 0x33333333);
	}

	/**
	 *
	 *
	 * @return
	 */
	public void setCounterUnit(final eUnit unit)
	{
		setCounterUnit(unit == null ? null : unit.name());
	}

	/**
	 *
	 *
	 * @return
	 */
	public eUnit getCountUnitEnum()
	{
		return eUnit.getEnum(getCounterUnit());
	}

	@Override
	protected ElementInfo getTheElementInfo()
	{
		return super.getTheElementInfo().updateReplace(elemInfoTable);
	}

	private static final long serialVersionUID = 1L;

	/**
	 * Constructor for JDFDeviceInfo
	 *
	 * @param myOwnerDocument
	 * @param qualifiedName
	 */
	public JDFDeviceInfo(final CoreDocumentImpl myOwnerDocument, final String qualifiedName)
	{
		super(myOwnerDocument, qualifiedName);
	}

	/**
	 * Constructor for JDFDeviceInfo
	 *
	 * @param myOwnerDocument
	 * @param myNamespaceURI
	 * @param qualifiedName
	 */
	public JDFDeviceInfo(final CoreDocumentImpl myOwnerDocument, final String myNamespaceURI, final String qualifiedName)
	{
		super(myOwnerDocument, myNamespaceURI, qualifiedName);
	}

	/**
	 * Constructor for JDFDeviceInfo
	 *
	 * @param myOwnerDocument
	 * @param myNamespaceURI
	 * @param qualifiedName
	 * @param myLocalName
	 */
	public JDFDeviceInfo(final CoreDocumentImpl myOwnerDocument, final String myNamespaceURI, final String qualifiedName, final String myLocalName)
	{
		super(myOwnerDocument, myNamespaceURI, qualifiedName, myLocalName);
	}

	/**
	 * toString()
	 *
	 * @see org.cip4.jdflib.auto.JDFAutoDeviceInfo#toString()
	 */
	@Override
	public String toString()
	{
		return "JDFDeviceInfo[  --> " + super.toString() + " ]";
	}

	/**
	 * Method getJobCount.
	 *
	 * @return int
	 * @deprecated use numChildElements(ElementName.JOBPHASE,null)
	 */
	@Deprecated
	public int getJobCount()
	{
		return getChildrenByTagName(ElementName.JOBPHASE, null, null, false, true, 0).size();
	}

	/**
	 * create a JobPhase message from a phaseTime Audit
	 *
	 * @param pt the phasetime audit
	 * @return JDFJobPhase: the jobphase element that has been filled by the phaseTime
	 */
	public JDFJobPhase createJobPhaseFromPhaseTime(final JDFPhaseTime pt)
	{
		final JDFJobPhase jp = appendJobPhase();
		final JDFNode node = pt.getParentJDF();

		jp.setJobID(node.getJobID(true));
		jp.setJobPartID(StringUtil.getNonEmpty(node.getJobPartID(true)));
		final VJDFAttributeMap partMapVector = pt.getPartMapVector();
		jp.setPartMapVector(partMapVector);
		jp.copyAttribute(AttributeName.STATUS, pt);
		jp.copyAttribute(AttributeName.STATUSDETAILS, pt);
		jp.setPhaseStartTime(pt.getStart());
		final JDFResourceLink rl = pt.getLink(0);
		if (rl != null)
		{
			if (rl.getAmountPoolAttribute(AttributeName.ACTUALAMOUNT, null, null, 0) != null)
			{
				jp.setPhaseAmount(rl.getActualAmount(null));
			}
		}
		final JDFMISDetails md = pt.getMISDetails();
		jp.copyElement(md, null);
		final VElement modules = pt.getChildElementVector(ElementName.MODULEPHASE, null);
		if (modules != null)
		{
			final int mLen = modules.size();
			for (int i = 0; i < mLen; i++)
			{
				jp.createModuleStatusFromModulePhase((JDFModulePhase) modules.elementAt(i));
			}
		}

		// TODO set more
		jp.eraseEmptyAttributes(true);
		return jp;
	}

	/**
	 * gets the deviceID from @DeviceID if it exists, otherwise searches Device/@DeviceID
	 *
	 * @return the appropriate deviceID for this deviceInfo
	 */
	@Override
	public String getDeviceID()
	{
		if (hasAttribute(AttributeName.DEVICEID))
		{
			return super.getDeviceID();
		}
		final JDFDevice d = getDevice();
		String ret = d == null ? null : d.getDeviceID();
		if (ret == null)
		{
			final KElement km = getParentNode_KElement();
			if (km instanceof JDFMessage)
			{
				ret = ((JDFMessage) km).getSenderID();
			}
		}
		return ret;
	}

	/**
	 * gets the DescriptiveName from @DescriptiveName if it exists, otherwise searches Device/@DescriptiveName
	 *
	 * @return the appropriate deviceID for this deviceInfo
	 */
	@Override
	public String getDescriptiveName()
	{
		if (hasAttribute(AttributeName.DESCRIPTIVENAME))
		{
			return super.getDescriptiveName();
		}
		final JDFDevice d = getDevice();
		return d == null ? "" : d.getDescriptiveName();
	}

	/**
	 * returns true if this is the same phase, i.e. the
	 *
	 * @param lastInfo the deviceInfo to compare with
	 * @param bExact if true, use startTime as hook, else compare stati
	 * @return true if same
	 */
	public boolean isSamePhase(final JDFDeviceInfo lastInfo, final boolean bExact)
	{
		if (lastInfo == null)
		{
			return false;
		}
		if (!ContainerUtil.equals(getDeviceID(), lastInfo.getDeviceID()))
		{
			return false;
		}
		if (!ContainerUtil.equals(getDeviceOperationMode(), lastInfo.getDeviceOperationMode()))
		{
			return false;
		}
		if (!ContainerUtil.equals(getDeviceStatus(), lastInfo.getDeviceStatus()))
		{
			return false;
		}
		if (!ContainerUtil.equals(getStatusDetails(), lastInfo.getStatusDetails()))
		{
			return false;
		}
		final int numEmployees = numChildElements(ElementName.EMPLOYEE, null);
		if (numEmployees != lastInfo.numChildElements(ElementName.EMPLOYEE, null))
		{
			return false;
		}
		boolean bSame = true;
		for (int i = 0; i < numEmployees && bSame; i++)
		{
			final JDFEmployee employee = lastInfo.getEmployee(i);
			if (employee != null)
			{
				bSame = bSame && getEmployee(i).matches(employee);
			}
		}
		if (!bSame)
		{
			return false;
		}
		final int numJobPhases = numChildElements(ElementName.JOBPHASE, null);
		if (numJobPhases != lastInfo.numChildElements(ElementName.JOBPHASE, null))
		{
			return false;
		}
		bSame = numJobPhases == 0;
		for (int i = 0; i < numJobPhases; i++)
		{
			bSame = bSame || getJobPhase(i).isSamePhase(lastInfo.getJobPhase(i), bExact);
		}
		if (bSame)
		{
			final VString ignore = new VString(new String[] { ElementName.PART, ElementName.EMPLOYEE, ElementName.JOBPHASE });
			final VElement childrenIgnoreList = getChildrenIgnoreList(ignore, true, null);
			final VElement lastchildrenIgnoreList = lastInfo.getChildrenIgnoreList(ignore, true, null);
			if (childrenIgnoreList.size() > 0 || lastchildrenIgnoreList.size() > 0)
			{
				if (childrenIgnoreList.size() != lastchildrenIgnoreList.size())
				{
					return false;
				}
				else
				{
					for (int i = 0; i < childrenIgnoreList.size(); i++)
					{
						if (!childrenIgnoreList.get(i).isEqual(lastchildrenIgnoreList.get(i)))
						{
							return false;
						}
					}
				}
			}
		}

		return bSame;
	}

	/**
	 * creates a new deviceInfo that spans lastphase and this phase note that phase amounts are not merged when the phase start times are identical. <br/>
	 * In this case, we assume that the more recent phase already contains the sum of both
	 *
	 * @param lastInfo the deviceInfo to merge
	 * @return true if successful
	 */
	public boolean mergeLastPhase(final JDFDeviceInfo lastInfo)
	{
		if (!isSamePhase(lastInfo, false))
		{
			return false;
		}

		final int numJobPhases = numChildElements(ElementName.JOBPHASE, null);
		boolean bGood = true;
		for (int i = 0; i < numJobPhases; i++)
		{
			bGood = getJobPhase(i).mergeLastPhase(lastInfo.getJobPhase(i)) || bGood;
		}
		return bGood;
	}

	/**
	 * set the parameters of this to the values from device
	 *
	 * @param device the device to copy here
	 * @param bCopy if true, also copy the device element
	 */
	public void setDevice(final JDFDevice device, final boolean bCopy)
	{
		if (device == null)
		{
			return;
		}
		copyAttribute(AttributeName.DEVICEID, device);
		if (bCopy && getDevice() == null)
		{
			copyElement(device, null);
		}
	}

	/**
	 * (26) getCreateEvent
	 *
	 * @param iSkip number of elements to skip
	 * @return JDFEvent the element
	 */
	@Override
	public JDFEvent getCreateEvent(final int iSkip)
	{
		return (JDFEvent) getCreateElement_JDFElement(ElementName.EVENT, null, iSkip);
	}

	/**
	 * (27) const get element Event
	 *
	 * @param iSkip number of elements to skip
	 * @return JDFEvent the element default is getEvent(0)
	 */
	@Override
	public JDFEvent getEvent(final int iSkip)
	{
		return (JDFEvent) getElement(ElementName.EVENT, null, iSkip);
	}

	/**
	 * Get all Event from the current element
	 *
	 * @return Collection<JDFEvent>, null if none are available
	 */
	@Override
	public Collection<JDFEvent> getAllEvent()
	{
		return getChildrenByClass(JDFEvent.class, false, 0);
	}

	/**
	 * (30) append element Event
	 *
	 * @return JDFEvent the element
	 */
	@Override
	public JDFEvent appendEvent()
	{
		return (JDFEvent) appendElement(ElementName.EVENT, null);
	}

	/**
	 * (30) append element Event
	 *
	 * @return JDFEvent the element
	 */
	public JDFEvent appendEvent(final String eventID)
	{
		final JDFEvent e = appendEvent();
		e.setEventID(StringUtil.getNonEmpty(eventID));
		return e;
	}

	public eDeviceStatus getXJMFStatus()
	{
		return eDeviceStatus.getEnum(getAttribute(AttributeName.STATUS));
	}

	public void setXJMFStatus(final eDeviceStatus s)
	{
		setAttribute(AttributeName.STATUS, s == null ? null : s.name());
	}
}
