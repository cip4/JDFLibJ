/**
 * The CIP4 Software License, Version 1.0
 *
 *
 * Copyright (c) 2001-2015 The International Cooperation for the Integration of 
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
 * copyright (c) 1999-2006, Heidelberger Druckmaschinen AG 
 * copyright (c) 1999-2001, Agfa-Gevaert N.V. 
 *  
 * For more information on The International Cooperation for the 
 * Integration of Processes in  Prepress, Press and Postpress , please see
 * <http://www.cip4.org/>.
 *  
 * 
 */
/**
 *========================================================================== class JDFPhaseTime extends JDFAutoPhaseTime
 * created 2001-09-06T10:02:57GMT+02:00 ==========================================================================
 *          @COPYRIGHT Heidelberger Druckmaschinen AG, 1999-2001 ALL RIGHTS RESERVED
 *              @Author  sabjon@topmail.de   using a code generator
 * Warning! very preliminary test version. Interface subject to change without prior notice! Revision history:   ...
 */

package org.cip4.jdflib.resource;

import java.util.Vector;

import org.apache.xerces.dom.CoreDocumentImpl;
import org.cip4.jdflib.auto.JDFAutoPhaseTime;
import org.cip4.jdflib.core.AttributeName;
import org.cip4.jdflib.core.ElementName;
import org.cip4.jdflib.core.JDFAudit;
import org.cip4.jdflib.core.JDFElement;
import org.cip4.jdflib.core.JDFResourceLink;
import org.cip4.jdflib.core.KElement;
import org.cip4.jdflib.core.VElement;
import org.cip4.jdflib.core.VString;
import org.cip4.jdflib.datatypes.JDFAttributeMap;
import org.cip4.jdflib.datatypes.VJDFAttributeMap;
import org.cip4.jdflib.ifaces.ISignalAudit;
import org.cip4.jdflib.jmf.JDFDeviceInfo;
import org.cip4.jdflib.jmf.JDFJMF;
import org.cip4.jdflib.jmf.JDFJobPhase;
import org.cip4.jdflib.jmf.JDFMessage.EnumFamily;
import org.cip4.jdflib.jmf.JDFMessage.EnumType;
import org.cip4.jdflib.jmf.JDFSignal;
import org.cip4.jdflib.jmf.JDFStatusQuParams;
import org.cip4.jdflib.node.JDFNode;
import org.cip4.jdflib.resource.process.JDFMISDetails;
import org.cip4.jdflib.util.ContainerUtil;
import org.cip4.jdflib.util.JDFDate;
import org.cip4.jdflib.util.JDFDuration;
import org.cip4.jdflib.util.StringUtil;

/**
 * @author Dr. Rainer Prosi, Heidelberger Druckmaschinen AG
 * 
 * 14.11.2008
 */
public class JDFPhaseTime extends JDFAutoPhaseTime implements ISignalAudit
{
	private static final long serialVersionUID = 1L;

	/**
	 * Constructor for JDFPhaseTime
	 * 
	 * @param myOwnerDocument
	 * @param qualifiedName
	 */
	public JDFPhaseTime(final CoreDocumentImpl myOwnerDocument, final String qualifiedName)
	{
		super(myOwnerDocument, qualifiedName);
	}

	/**
	 * Constructor for JDFPhaseTime
	 * 
	 * @param myOwnerDocument
	 * @param myNamespaceURI
	 * @param qualifiedName
	 */
	public JDFPhaseTime(final CoreDocumentImpl myOwnerDocument, final String myNamespaceURI, final String qualifiedName)
	{
		super(myOwnerDocument, myNamespaceURI, qualifiedName);
	}

	/**
	 * Constructor for JDFPhaseTime
	 * 
	 * @param myOwnerDocument
	 * @param myNamespaceURI
	 * @param qualifiedName
	 * @param myLocalName
	 */
	public JDFPhaseTime(final CoreDocumentImpl myOwnerDocument, final String myNamespaceURI, final String qualifiedName, final String myLocalName)
	{
		super(myOwnerDocument, myNamespaceURI, qualifiedName, myLocalName);
	}

	/**
	 * toString()
	 * 
	 * @return String
	 */
	@Override
	public String toString()
	{
		return "JDFPhaseTime[  --> " + super.toString() + " ]";
	}

	/**
	 * set all parts to those defined in vParts
	 * 
	 * @param vParts vector of attribute maps for the parts
	 */
	@Override
	public void setPartMapVector(final VJDFAttributeMap vParts)
	{
		super.setPartMapVector(vParts);
	}

	/**
	 * set all parts to those defined by mPart
	 * 
	 * @param mPart attribute map for the part to set
	 */
	@Override
	public void setPartMap(final JDFAttributeMap mPart)
	{
		super.setPartMap(mPart);
	}

	/**
	 * remove the part defined by mPart
	 * 
	 * @param mPart attribute map for the part to remove
	 */
	@Override
	public void removePartMap(final JDFAttributeMap mPart)
	{
		super.removePartMap(mPart);
	}

	/**
	 * check whether the part defined in mPart is included
	 * 
	 * @param mPart attribute map to look for
	 * @return boolean - returns true if the part exists
	 */
	@Override
	public boolean hasPartMap(final JDFAttributeMap mPart)
	{
		return super.hasPartMap(mPart);
	}

	/**
	 * return a vector of unknown element nodenames
	 * <p>
	 * default: GetInvalidElements(true, 999999)
	 * 
	 * @param bIgnorePrivate used by JDFElement during the validation
	 * @param nMax maximum size of the returned vector
	 * @return Vector - vector of unknown element nodenames
	 * 
	 * !!! Do not change the signature of this method
	 */
	@Override
	public VString getUnknownElements(final boolean bIgnorePrivate, final int nMax)
	{
		return getUnknownPoolElements(JDFElement.EnumPoolType.ResourceLinkPool, nMax);
	}

	/**
	 * copy a Vector of resourceLinks into this PhaseTime
	 * 
	 * @param vRL the Vector of resourceLinks to copy - the order is significant, because the first rl will be used to fill the Amount in
	 * Signal/DeviceInfo/JobPhase
	 */
	public void setLinks(final VElement vRL)
	{
		if (vRL == null)
		{
			return;
		}
		final int size = vRL.size();
		if (size == 0)
		{
			return;
		}

		for (int i = 0; i < size; i++)
		{
			final JDFResourceLink rl = (JDFResourceLink) vRL.elementAt(i);
			removeChildren(rl.getLocalName(), rl.getNamespaceURI(), null);
		}
		for (int i = 0; i < size; i++)
		{
			final JDFResourceLink rl = (JDFResourceLink) vRL.elementAt(i);
			copyElement(rl, null);
		}
	}

	/**
	 * return the ResourceLink in <code>this</code>, null if none exists
	 * 
	 * @param iSkip the nTh resourceLink to retrieve
	 * @return JDFResourceLink - <code>this</code> phaseTimes ResourceLink
	 */
	public JDFResourceLink getLink(final int iSkip)
	{
		KElement e = getFirstChildElement();
		int n = 0;
		while (e != null)
		{
			if (e instanceof JDFResourceLink)
			{
				if (n++ >= iSkip)
				{
					return (JDFResourceLink) e;
				}
			}
			e = e.getNextSiblingElement();
		}
		return null;
	}

	/**
	 * return the ResourceLink in <code>this</code>, null if none exists
	 * 
	 * @return JDFResourceLink - this phaseTimes ResourceLink
	 */
	public VElement getLinkVector()
	{
		KElement e = getFirstChildElement();
		final VElement vRet = new VElement();
		while (e != null)
		{
			if (e instanceof JDFResourceLink)
			{
				vRet.add(e);
			}
			e = e.getNextSiblingElement();
		}
		return vRet.size() == 0 ? null : vRet;
	}

	/**
	 * gets the DeviceID from the first child device, null if none was set
	 * @return the deviceID
	 */
	public String getDeviceID()
	{
		JDFDevice d = getDevice(0);
		if (d == null)
			return null;
		return StringUtil.getNonEmpty(d.getDeviceID());
	}

	/**
	 * gets the DeviceID from all child devices, null if none was set
	 * @return the deviceID
	 */
	public VString getDeviceIDs()
	{
		Vector<JDFDevice> vd = getChildrenByClass(JDFDevice.class, false, 0);
		if (vd == null)
			return null;
		VString ret = new VString();
		for (JDFDevice d : vd)
			ret.add(d.getDeviceID());
		return ret;
	}

	/**
	 * set Device/@DeviceID
	 * @param deviceID
	 */
	public void setDeviceID(String deviceID)
	{
		getCreateDevice(0).setDeviceID(deviceID);
	}

	/**
	 * get the implied duration from Start and End
	 * 
	 * @return JDFDuration the duration
	 */
	public JDFDuration getDuration()
	{
		final JDFDate dStart = getStart();
		final JDFDate dEnd = getEnd();
		if (dStart == null || dEnd == null)
		{
			return null;
		}
		int dur = (int) ((dEnd.getTimeInMillis() - dStart.getTimeInMillis()) / 1000);
		if (dur < 0)
		{
			dur = 0;
		}
		return new JDFDuration(dur);
	}

	/**
	 * @param moduleIDs the list of module ids to add, if null: nop
	 * @param moduleTypes
	 * @return the list of ModulePhase element
	 * @throws IllegalArgumentException if the vectors have different lengths
	 */
	public VElement setModules(final VString moduleIDs, final VString moduleTypes)
	{
		if (moduleIDs == null || moduleIDs.size() == 0)
		{
			return null;
		}
		if (moduleTypes == null || moduleTypes.size() == 0 || moduleTypes.size() != moduleIDs.size())
		{
			throw new IllegalArgumentException("Inconsistent vector lengths");
		}
		final VElement v = new VElement();
		for (int i = 0; i < moduleIDs.size(); i++)
		{
			final JDFModulePhase modulePhase = getCreateModulePhase(i);
			v.add(modulePhase);
			modulePhase.setModuleID(moduleIDs.get(i));
			modulePhase.setModuleType(moduleTypes.get(i));
		}
		return v;
	}

	/**
	 * @see org.cip4.jdflib.ifaces.ISignalAudit#toSignalJMF()
	 */
	@Override
	public JDFJMF toSignalJMF()
	{
		final JDFJMF newJMF = JDFJMF.createJMF(EnumFamily.Signal, EnumType.Status);
		final JDFSignal s = newJMF.getSignal(0);
		s.copyElement(getEmployee(0), null);
		final JDFStatusQuParams sqp = s.appendStatusQuParams();
		final JDFDeviceInfo di = s.appendDeviceInfo();
		final JDFJobPhase jp = di.appendJobPhase();
		final JDFNode parentJDF = getParentJDF();
		if (parentJDF != null)
		{
			sqp.setJobID(parentJDF.getJobID(true));
			sqp.setJobPartID(StringUtil.getNonEmpty(parentJDF.getJobPartID(false)));
			jp.setJobID(parentJDF.getJobID(true));
			jp.setJobPartID(StringUtil.getNonEmpty(parentJDF.getJobPartID(false)));

		}
		final JDFDevice dev = getDevice(0);
		if (dev != null)
		{
			di.setDevice(dev, true);
		}

		di.setDeviceStatus(EnumNodeStatus.getDeviceStatus(getStatus()));
		jp.setStatus(getStatus());
		jp.copyAttribute(AttributeName.STATUSDETAILS, this);
		final JDFMISDetails md = getMISDetails();
		if (md != null)
		{
			jp.copyElement(md, null);
		}
		final VJDFAttributeMap vP = getPartMapVector();
		jp.setPartMapVector(vP);
		s.setTime(getEnd());
		jp.setPhaseStartTime(getStart());
		return newJMF;
	}

	/**
	 * sort by timestamp using the end time
	 * @param a1 an audit
	 * @param a2 another audit
	 * @return @see java.util.Comparator#compare(java.lang.Object, java.lang.Object)
	 * @see java.util.Comparator#compare(java.lang.Object, java.lang.Object)
	 */
	@Override
	public int compare(final JDFAudit a1, final JDFAudit a2)
	{
		final JDFDate d1 = (a1 instanceof JDFPhaseTime) ? ((JDFPhaseTime) a1).getEnd() : a1.getTimeStamp();
		final JDFDate d2 = (a2 instanceof JDFPhaseTime) ? ((JDFPhaseTime) a2).getEnd() : a2.getTimeStamp();
		return ContainerUtil.compare(d1, d2);
	}

	/**
	 * 
	 * @param phase
	 */
	public void setPhase(JDFJobPhase phase)
	{
		JDFJMF jmf = (JDFJMF) phase.getDeepParent(ElementName.JMF, 0);
		JDFDeviceInfo devInfo = (JDFDeviceInfo) phase.getParentNode_KElement();
		setStatusDetails(phase.getStatusDetails());
		setStatus(phase.getStatus());
		setPartMapVector(phase.getPartMapVector());
		copyElements(devInfo.getChildElementVector(ElementName.EMPLOYEE, null), null);
		copyElements(phase.getChildElementVector(ElementName.ACTIVITY, null), null);
		copyElement(phase.getMISDetails(), null);
		setEnd(jmf.getTimeStamp());
		setStart(phase.getPhaseStartTime());
	}

}