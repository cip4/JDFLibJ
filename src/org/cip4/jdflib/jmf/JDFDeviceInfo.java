/*--------------------------------------------------------------------------------------------------
 * The CIP4 Software License, Version 1.0
 *
 *
 * Copyright (c) 2001-2008 The International Cooperation for the Integration of
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
==========================================================================
class JDFDeviceInfo extends JDFResource
==========================================================================
@COPYRIGHT Heidelberger Druckmaschinen AG, 1999-2001
ALL RIGHTS RESERVED
@Author: sabjon@topmail.de   using a code generator
Warning! very preliminary test version. Interface subject to change without prior notice!
Revision history:    ...
 **/





package org.cip4.jdflib.jmf;

import java.util.zip.DataFormatException;

import org.apache.xerces.dom.CoreDocumentImpl;
import org.cip4.jdflib.auto.JDFAutoDeviceInfo;
import org.cip4.jdflib.core.AttributeName;
import org.cip4.jdflib.core.ElementName;
import org.cip4.jdflib.core.JDFResourceLink;
import org.cip4.jdflib.core.VElement;
import org.cip4.jdflib.datatypes.VJDFAttributeMap;
import org.cip4.jdflib.node.JDFNode;
import org.cip4.jdflib.resource.JDFDevice;
import org.cip4.jdflib.resource.JDFModulePhase;
import org.cip4.jdflib.resource.JDFPhaseTime;
import org.cip4.jdflib.resource.process.JDFMISDetails;
import org.cip4.jdflib.util.ContainerUtil;
import org.cip4.jdflib.util.JDFDate;



//----------------------------------
public class JDFDeviceInfo extends JDFAutoDeviceInfo
{
    private static final long serialVersionUID = 1L;

    /**
     * Constructor for JDFDeviceInfo
     * @param myOwnerDocument
     * @param qualifiedName
     */
    public JDFDeviceInfo(
            CoreDocumentImpl myOwnerDocument,
            String qualifiedName)
    {
        super(myOwnerDocument, qualifiedName);
    }

    /**
     * Constructor for JDFDeviceInfo
     * @param myOwnerDocument
     * @param myNamespaceURI
     * @param qualifiedName
     */
    public JDFDeviceInfo(
            CoreDocumentImpl myOwnerDocument,
            String myNamespaceURI,
            String qualifiedName)
    {
        super(myOwnerDocument, myNamespaceURI, qualifiedName);
    }

    /**
     * Constructor for JDFDeviceInfo
     * @param myOwnerDocument
     * @param myNamespaceURI
     * @param qualifiedName
     * @param myLocalName
     */
    public JDFDeviceInfo(
            CoreDocumentImpl myOwnerDocument,
            String myNamespaceURI,
            String qualifiedName,
            String myLocalName)
    {
        super(myOwnerDocument, myNamespaceURI, qualifiedName, myLocalName);
    }

    /**
     * toString()
     * @see org.cip4.jdflib.auto.JDFAutoDeviceInfo#toString()
     */
    public String toString()
    {
        return "JDFDeviceInfo[  --> " + super.toString() + " ]";
    }

    /**
     * Method getJobCount.
     * @return int
     * @deprecated use numChildElements(ElementName.JOBPHASE,null)
     */
    public int getJobCount()
    {
        return getChildrenByTagName(ElementName.JOBPHASE,null,null, false, true,0).size();
    }   

    /**
     * (11) set attribute IdleStartTime
     * @param value: the value to set the attribute to or null
     */
    public void setIdleStartTime(JDFDate value)
    {
        if (value == null) value = new JDFDate();
        setAttribute(AttributeName.IDLESTARTTIME, value.getDateTimeISO(), null);
    }

    /**
     * (12) get JDFDate attribute IdleStartTime
     * @return JDFDate the value of the attribute
     */
    public JDFDate getIdleStartTime()
    {
        final String str = getAttribute(AttributeName.IDLESTARTTIME, null, null);
        if (str!=null)
        {
            try
            {
                return new JDFDate(str);
            }
            catch(DataFormatException dfe)
            {
                // nop
            }
        }
        return null;
    }

    /**
     * create a JobPhase message from a phaseTime Audit
     * @param pt the phasetime audit
     * @return JDFJobPhase: the jobphase element that has been filled by the phaseTime
     */
    public JDFJobPhase createJobPhaseFromPhaseTime(final JDFPhaseTime pt)
    {
        JDFJobPhase jp=appendJobPhase();
        JDFNode node=pt.getParentJDF();

        jp.setJobID(node.getJobID(true));
        jp.setJobPartID(node.getJobPartID(true));
        final VJDFAttributeMap partMapVector = pt.getPartMapVector();
        jp.setPartMapVector(partMapVector);
        jp.setStatus(pt.getStatus());
        final String statusDetails = pt.getStatusDetails();
        jp.setStatusDetails(statusDetails);
        jp.setPhaseStartTime(pt.getStart());
        JDFResourceLink rl=pt.getLink(0);
        if(rl!=null)
        {
            if(rl.getAmountPoolAttribute(AttributeName.ACTUALAMOUNT, null, null, 0)!=null)
                jp.setPhaseAmount(rl.getActualAmount(null));
        }
        JDFMISDetails md=pt.getMISDetails();
        jp.copyElement(md,null);
        VElement modules=pt.getChildElementVector(ElementName.MODULEPHASE, null);
        int mLen=modules==null ? 0 : modules.size();
        for(int i=0;i<mLen;i++)
        {
            jp.createModuleStatusFromModulePhase((JDFModulePhase)modules.elementAt(i));
        }
        //TODO set more
        jp.eraseEmptyAttributes(true);
        return jp;
    }


    /**
     * gets the deviceID from @DeviceID if it exists, otherwise searches Device/@DeviceID
     * @return the appropriate deviceID for this deviceInfo
     */
    @Override
    public String getDeviceID()
    {
        if(hasAttribute(AttributeName.DEVICEID))
            return super.getDeviceID();
        JDFDevice d=getDevice();
        if(d==null)
        {
            JDFMessage m=(JDFMessage) getParentNode_KElement();
            if(m!=null)
                return m.getSenderID();

        }
        return d==null ? null : d.getDeviceID();
    }

    /**
     * returns true if this is the same phase, i.e. the 
     * @param lastphase the phase to compare with
     * @param bExact if true, use startTime as hook, else compare stati
     * @return
     */
    public boolean isSamePhase(JDFDeviceInfo lastInfo, boolean bExact)
    {
        if(lastInfo==null)
            return false;
        if(!ContainerUtil.equals(getDeviceID(), lastInfo.getDeviceID()))
            return false;
        if(!ContainerUtil.equals(getDeviceOperationMode(), lastInfo.getDeviceOperationMode()))
            return false;
        if(!ContainerUtil.equals(getDeviceStatus(), lastInfo.getDeviceStatus()))
            return false;
        if(!ContainerUtil.equals(getStatusDetails(), lastInfo.getStatusDetails()))
            return false;
        int numEmployees = numChildElements(ElementName.EMPLOYEE, null);
        if(numEmployees!=lastInfo.numChildElements(ElementName.EMPLOYEE, null))
            return false;
        boolean bGood=true;
        for(int i=0;i<numEmployees;i++)
            bGood=bGood || getEmployee(i).isSameEmployee(lastInfo.getEmployee(i));

        int numJobPhases = numChildElements(ElementName.JOBPHASE, null);
        if(numJobPhases!=lastInfo.numChildElements(ElementName.JOBPHASE, null))
            return false;
        bGood=true;

        for(int i=0;i<numJobPhases;i++)
            bGood=bGood || getJobPhase(i).isSamePhase(lastInfo.getJobPhase(i), bExact);
        return bGood;
    }

    /**
     * creates a new deviceInfo that spans lastphase and this phase
     * @param lastphase the phase to merge 
     * @return true if successful
     */
    public boolean mergeLastPhase(JDFDeviceInfo lastInfo)
    {
        if(!isSamePhase(lastInfo, false))
            return false;

        int numJobPhases = numChildElements(ElementName.JOBPHASE, null);
        boolean bGood=true;
        for(int i=0;i<numJobPhases;i++)
            bGood= getJobPhase(i).mergeLastPhase(lastInfo.getJobPhase(i))||bGood;
        return bGood;
    }

}



