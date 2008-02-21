/*
 *
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
 *
 */
/**
 * Created on Jul 5, 2006, 11:45:44 AM
 * org.cip4.jdflib.util.MimeUtil.java
 * Project Name: mimeutil
 */
package org.cip4.jdflib.util;

import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

import org.cip4.jdflib.auto.JDFAutoDeviceInfo.EnumDeviceStatus;
import org.cip4.jdflib.auto.JDFAutoMISDetails.EnumWorkType;
import org.cip4.jdflib.auto.JDFAutoResourceAudit.EnumReason;
import org.cip4.jdflib.core.AttributeName;
import org.cip4.jdflib.core.ElementName;
import org.cip4.jdflib.core.JDFDoc;
import org.cip4.jdflib.core.JDFResourceLink;
import org.cip4.jdflib.core.KElement;
import org.cip4.jdflib.core.VElement;
import org.cip4.jdflib.core.VString;
import org.cip4.jdflib.core.JDFAudit.EnumAuditType;
import org.cip4.jdflib.core.JDFElement.EnumNodeStatus;
import org.cip4.jdflib.datatypes.JDFAttributeMap;
import org.cip4.jdflib.datatypes.VJDFAttributeMap;
import org.cip4.jdflib.jmf.JDFDeviceInfo;
import org.cip4.jdflib.jmf.JDFJMF;
import org.cip4.jdflib.jmf.JDFJobPhase;
import org.cip4.jdflib.jmf.JDFMessage;
import org.cip4.jdflib.jmf.JDFResourceInfo;
import org.cip4.jdflib.jmf.JDFResourceQuParams;
import org.cip4.jdflib.jmf.JDFResponse;
import org.cip4.jdflib.jmf.JDFSignal;
import org.cip4.jdflib.jmf.JDFMessage.EnumType;
import org.cip4.jdflib.node.JDFNode;
import org.cip4.jdflib.pool.JDFAuditPool;
import org.cip4.jdflib.resource.JDFPhaseTime;
import org.cip4.jdflib.resource.JDFProcessRun;
import org.cip4.jdflib.resource.JDFResource;
import org.cip4.jdflib.resource.JDFResourceAudit;
import org.cip4.jdflib.resource.JDFResource.EnumPartIDKey;
import org.cip4.jdflib.resource.JDFResource.EnumResStatus;

//TODO add time related metadata

/**
 * Utility class for status JDF and JMF
 *
 * @author prosirai
 *
 */
public class StatusCounter 
{

    protected JDFNode m_Node;
    private JDFDoc docJMFPhaseTime;
    private JDFDoc docJMFResource;
    protected VJDFAttributeMap m_vPartMap;
    private String m_deviceID=null;
    private VString m_moduleID=null;
    private VString m_moduleType=null;
    private LinkAmount[] vLinkAmount=null;
    private String firstRefID=null;
    private String queueEntryID;
    private String workStepID;
    private EnumWorkType workType=null;
    protected HashSet setTrackWaste=new HashSet();
    protected HashSet setCopyResInfo=new HashSet();
    private EnumDeviceStatus status=null;
    private String statusDetails=null;
    private JDFDate startDate;


    /**
     * construct a StatusUtil for a node n
     * @param node the JDFNode that is being processed
     * @param vPartMap the map of Parts that is being processed excluding the waste partition
     * @param vResLinks the reslinks that are tracked for amount handling
     */
    public StatusCounter(JDFNode node, VJDFAttributeMap vPartMap, VElement vResLinks)
    {
        setActiveNode(node, vPartMap, vResLinks);
    }

    /**
     * set the currently active node
     * @param node the JDFNode that is being processed
     * @param vPartMap the map of Parts that is being processed excluding the waste partition
     * @param vResLinks the reslinks that are tracked for amount handling
     */
    public void setActiveNode(JDFNode node, VJDFAttributeMap vPartMap, VElement vResLinks)
    {
        if(node==null)
            setTrackWaste.clear();
        m_Node=node;
        m_vPartMap=vPartMap;
        vLinkAmount=null;
        workStepID=null;
        firstRefID=null;
        docJMFResource=null;
        docJMFPhaseTime=null;
        startDate=new JDFDate();
        if(node==null)
        {
            setPhase(null, null, EnumDeviceStatus.Idle, null);
        }

        JDFAttributeMap wsMap=null;
        if(m_vPartMap==null && m_Node!=null)
        {
            m_vPartMap = m_Node.getPartMapVector();
        }
        if(m_vPartMap!=null && m_vPartMap.size()>0)
            wsMap=m_vPartMap.elementAt(0);

        workStepID=node!=null ? node.getWorkStepID(wsMap) : null;
        if(vResLinks==null && m_Node!=null)
        {
            vResLinks=m_Node.getResourceLinks(null);            
            int siz=vResLinks==null ? 0 : vResLinks.size();
            for(int i=siz-1;i>=0;i--)
            {
                JDFResourceLink rl=(JDFResourceLink) vResLinks.elementAt(i); 
                if(!rl.isPhysical())
                    vResLinks.remove(i);
            }
        }
        setUpResLinks(vResLinks);
    }

    /**
     * simple sleep wrapper that catches its exception
     * @param millis
     */
    public static void sleep(int millis)
    {
        try
        {
            Thread.sleep(millis);
        }
        catch (InterruptedException x)
        {
//          System.out.print(".");
        }
    }

    /**
     * get the matching LinkAmount out of this
     * @param refID the refID, name or usage of the resource of the bag - this MUST match the refID of a ResourceLink
     * @return the LinkAmount with matching refID, null if none found or bags is null
     */
    protected LinkAmount getLinkAmount(String refID)
    {
        if(vLinkAmount==null || vLinkAmount.length==0) {
            return null;
        }
        if(refID==null)
            refID=getFirstRefID();
        
        for(int i=0;i<vLinkAmount.length;i++)
        {
            if(vLinkAmount[i].linkFitsKey(refID)) 
            {
                return vLinkAmount[i];
            }
        }
        return null;
    }
    /**
     * get the matching LinkAmount out of this
     * @param refID the refID, name or usage of the resource of the bag - this MUST match the refID of a ResourceLink
     * @return the LinkAmount with matching refID, null if none found or bags is null
     */
    protected LinkAmount getLinkAmount(int n)
    {
        if(vLinkAmount==null || vLinkAmount.length<=n) {
            return null;
        }
        return vLinkAmount[n];
    }
    /**
     * get the refID of the first resource, i.e. the Resource that is being tracked in status messages
     * @return the rRef of the prime resource link
     */
    public String getFirstRefID()
    {
        if(firstRefID!=null)
            return firstRefID;
        if(vLinkAmount==null || vLinkAmount.length==0) {
            return null;
        }
        return vLinkAmount[0].rl.getrRef();
    }

    /**
     * set the id of the resource to be tracked in phasetimes, i.e. THE resource that is counted
     * @param resID
     */
    public void setFirstRefID(String resID)
    {
        firstRefID=resID;
    }
    /**
     * @param resLinks
     */
    private void setUpResLinks(VElement resLinks)
    {
        if(resLinks==null || resLinks.size()==0) 
        {
            return;
        }
        vLinkAmount=new LinkAmount[resLinks.size()];
        for(int i=0;i<vLinkAmount.length;i++)
        {
            vLinkAmount[i]= new LinkAmount((JDFResourceLink)resLinks.elementAt(i));
        }
    }

    /**
     * clear all the amounts in the resource with id refID
     * 
     * @param refID id of the resource
     */
    public void clearAmounts(String refID)
    {
        LinkAmount la=getLinkAmount(refID);
        if(la==null)
            return;
        la.lastBag.reset();
    }
    /**
     * add the amount specified by amount and waste to the resource with id refID
     * 
     * @param refID, type or usage of the resource, if null all are updated
     * @param amount
     * @param waste
     */
    public void addPhase(String refID, double amount, double waste)
    { 
        if(refID==null)
            refID=getFirstRefID();
        LinkAmount la=getLinkAmount(refID);
        if(la==null)
            return;
        la.addPhase(amount, waste, false);
    }
    /**
     * get the total the amount of the resource with id refID
     * 
     * @param refID, type or usage of the resource, 
     */
    public double getTotalAmount(String refID)
    { 
        final LinkAmount la=getLinkAmount(refID);
        return la==null ? 0 : la.lastBag.totalAmount;
    }
 
    /**
     * get all total amounts of all tracked resources
     * 
     * @param i 
     */
    public double[] getTotalAmounts()
    { 
        if(vLinkAmount==null || vLinkAmount.length==0) 
            return null;
        double[] d=new double[vLinkAmount.length];
        for(int i=0;i<d.length;i++)
            d[i]=vLinkAmount[i].lastBag.totalAmount;
        return d;
    }
    /**
     * get all total amounts of all tracked resources
     * 
     * @param i 
     */
    public JDFResourceLink[] getAmountLinks()
    { 
        if(vLinkAmount==null || vLinkAmount.length==0) 
            return null;
        JDFResourceLink[] d=new JDFResourceLink[vLinkAmount.length];
        for(int i=0;i<d.length;i++)
            d[i]=vLinkAmount[i].rl;
        return d;
    }

    /**
     * get all phaseamounts of all tracked resources
     * 
     * @param i 
     */
    public double[] getPhaseAmounts()
    { 
        if(vLinkAmount==null || vLinkAmount.length==0) 
            return null;
        double[] d=new double[vLinkAmount.length];
        for(int i=0;i<d.length;i++)
            d[i]=vLinkAmount[i].lastBag.phaseAmount;
        return d;
    }
    /**
     * get the total the amount of the resource with id refID
     * 
     * @param refID, type or usage of the resource, 
     */
    public double getPhaseAmount(String refID)
    { 
        final LinkAmount la=getLinkAmount(refID);
        return la==null ? 0 : la.lastBag.phaseAmount;
    }
    /**
     * get the total the amount of the resource with id refID
     * 
     * @param refID, type or usage of the resource, 
     */
    public double getTotalWaste(String refID)
    { 
        final LinkAmount la=getLinkAmount(refID);
        return la==null ? 0 : la.lastBag.totalWaste;
    }
    /**
     * get all total amounts of all tracked resources
     * 
     * @param i 
     */
    public double[] getTotalWastes()
    { 
        if(vLinkAmount==null || vLinkAmount.length==0) 
            return null;
        double[] d=new double[vLinkAmount.length];
        for(int i=0;i<d.length;i++)
            d[i]=vLinkAmount[i].lastBag.totalWaste;
        return d;
    }
   
    /**
     * get all phase waste amounts of all tracked resources
     * 
     * @param i 
     */
    public double[] getPhaseWastes()
    { 
        if(vLinkAmount==null || vLinkAmount.length==0) 
            return null;
        double[] d=new double[vLinkAmount.length];
        for(int i=0;i<d.length;i++)
            d[i]=vLinkAmount[i].lastBag.phaseWaste;
        return d;
    }

    /**
     * get the total the amount of the resource with id refID
     * 
     * @param refID, type or usage of the resource, 
     */
    public double getPhaseWaste(String refID)
    { 
        final LinkAmount la=getLinkAmount(refID);
        return la==null ? 0 : la.lastBag.phaseWaste;
    }
    /**
     * Set the Status and StatusDetails of this node
     * update the PhaseTime audit or append a new phasetime as appropriate
     * also prepare a status JMF
     *
     * @param nodeStatus the new status of the node
     * @param nodeStatusDetails the new statusDetails of the node
     * @param deviceStatus the new status of the device
     * @param deviceStatusDetails the new statusDetails of the device
     * @param vPartMap the vector of parts to that should be set
     * @param vResLink the resourcelinks that are used to fill the various amount attributes in jobphase and phasetime
     *
     */
    public boolean setPhase(EnumNodeStatus nodeStatus, String nodeStatusDetails, EnumDeviceStatus deviceStatus, String deviceStatusDetails)
    {
        if(m_Node==null)
            return setIdlePhase(deviceStatus, deviceStatusDetails);

        status=deviceStatus;
        statusDetails=deviceStatusDetails;
        JDFJMF jmfStatus = createPhaseTimeJMF();
        JDFJMF jmfRes = createResourceJMF();

        final LinkAmount la=getLinkAmount(getFirstRefID());

        JDFAuditPool ap=m_Node.getCreateAuditPool();
        // TODO rethink when to send 2 phases
        JDFPhaseTime lastPhase= ap.getLastPhase(m_vPartMap,m_moduleID==null ? null : m_moduleID.stringAt(0));
        JDFPhaseTime nextPhase= lastPhase;
        boolean bEnd=EnumNodeStatus.Completed.equals(nodeStatus) || EnumNodeStatus.Aborted.equals(nodeStatus);
        boolean bChanged=bEnd || lastPhase==null; // no previous audit or over and out

        nextPhase=ap.setPhase(nodeStatus,nodeStatusDetails,m_vPartMap);
        if(bEnd )
        {
            appendProcessRun(nodeStatus, ap);
        }

        if(nextPhase!=null)
        {
            generateResourceSignal(jmfRes);
        }

        if(lastPhase!=null && nextPhase!=lastPhase) // we explicitly added a new phasetime audit, thus we need to add a closing JMF for the original jobPhase
        {
            bChanged=true;
            closeJobPhase(jmfStatus, la, lastPhase, nextPhase); // attention - resets la to 0 - all calls after this have the new amounts
            startDate=new JDFDate();
        }

        if(nextPhase!=null)
        {
            if(workType!=null)
                nextPhase.getCreateMISDetails().setWorkType(workType);
            if(m_deviceID!=null) 
            {
                nextPhase.getCreateDevice(0).setDeviceID(m_deviceID);
            }
            nextPhase.setModules(m_moduleID,m_moduleType);

            updateCurrentJobPhase(nodeStatus, deviceStatus, deviceStatusDetails, jmfStatus, la, nextPhase, bEnd);
        }

        jmfStatus.eraseEmptyAttributes(true);
        jmfRes.eraseEmptyAttributes(true);
        return bChanged;
    }

    private JDFJMF createResourceJMF()
    {
        docJMFResource=new JDFDoc(ElementName.JMF);
        JDFJMF jmfRes=docJMFResource.getJMFRoot();
        jmfRes.setSenderID(m_deviceID);
        return jmfRes;
    }

    private JDFJMF createPhaseTimeJMF()
    {
        docJMFPhaseTime=new JDFDoc(ElementName.JMF);
        JDFJMF jmfStatus=docJMFPhaseTime.getJMFRoot();
        jmfStatus.setSenderID(m_deviceID);
        return jmfStatus;
    }

    /**
     * @param deviceStatus
     * @param deviceStatusDetails
     * @return true if change since last time
     */
    private boolean setIdlePhase(EnumDeviceStatus deviceStatus, String deviceStatusDetails)
    {        
        boolean bChanged = docJMFPhaseTime==null; // first aftersetPhase
        JDFResponse r=bChanged ? null : docJMFPhaseTime.getJMFRoot().getResponse(0);
        JDFDeviceInfo lastDevInfo=r==null ? null : r.getDeviceInfo(-1);
        status=deviceStatus;
        statusDetails=deviceStatusDetails;

        bChanged=bChanged || !ContainerUtil.equals(deviceStatusDetails, lastDevInfo==null ? null : lastDevInfo.getAttribute(AttributeName.STATUSDETAILS,null,null));
        startDate = ( lastDevInfo==null || lastDevInfo.getIdleStartTime()==null || bChanged) ? new JDFDate() : lastDevInfo.getIdleStartTime();

        docJMFPhaseTime=new JDFDoc(ElementName.JMF);
        JDFDeviceInfo newDevInfo=docJMFPhaseTime.getJMFRoot().appendResponse(EnumType.Status).appendDeviceInfo();
        newDevInfo.setDeviceStatus(deviceStatus);
        newDevInfo.setStatusDetails(deviceStatusDetails);
        newDevInfo.setIdleStartTime(startDate);
        return bChanged;
    }

    private void updateCurrentJobPhase(EnumNodeStatus nodeStatus, EnumDeviceStatus deviceStatus, String deviceStatusDetails, JDFJMF jmf, final LinkAmount la, JDFPhaseTime pt2, boolean bEnd)
    {
        JDFResponse respStatus=(JDFResponse)jmf.appendMessageElement(JDFMessage.EnumFamily.Response,JDFMessage.EnumType.Status);
        JDFDeviceInfo deviceInfo = respStatus.getCreateDeviceInfo(0);
        if(!bEnd) // don't write a jobphase for an idle device
        {
            JDFJobPhase jp=deviceInfo.createJobPhaseFromPhaseTime(pt2);
            setJobPhaseAmounts(la, jp);
        }

        deviceInfo.setDeviceStatus(deviceStatus);
        deviceInfo.setStatusDetails(deviceStatusDetails);
        deviceInfo.setDeviceID(m_deviceID);
        m_Node.setPartStatus(m_vPartMap,nodeStatus);
        getVResLink(2);// update the nodes links

        if(bEnd)
        {
            pt2.deleteNode(); // zapp the last phasetime
        }
        else
        {
            pt2.setLinks(getVResLink(1));
            pt2.eraseEmptyAttributes(true);
        }
    }

    private JDFResponse closeJobPhase(JDFJMF jmf, final LinkAmount la, JDFPhaseTime pt1, JDFPhaseTime pt2)
    {
        JDFResponse respStatus=(JDFResponse)jmf.appendMessageElement(JDFMessage.EnumFamily.Response,JDFMessage.EnumType.Status);
        JDFDeviceInfo deviceInfo = respStatus.appendDeviceInfo();
        JDFJobPhase jp=deviceInfo.createJobPhaseFromPhaseTime(pt1);
        jp.setJobID(m_Node.getJobID(true));
        jp.setJobPartID(m_Node.getJobPartID(false));
        setJobPhaseAmounts(la, jp);
        pt1.setLinks(getVResLink(1));

        // cleanup!
        if(vLinkAmount!=null)
        {
            for(int i=0;i<vLinkAmount.length;i++)
            {
                vLinkAmount[i].addPhase(0, 0, true);
            }
        }
        return respStatus;
    }

    private void appendProcessRun(EnumNodeStatus nodeStatus, JDFAuditPool ap)
    {
        JDFProcessRun pr=(JDFProcessRun) ap.addAudit(EnumAuditType.ProcessRun, null);
        pr.setPartMapVector(m_vPartMap);
        VElement audits=ap.getAudits(EnumAuditType.PhaseTime, null, m_vPartMap);
        for(int i=0;i<audits.size();i++)
        {
            pr.addPhase((JDFPhaseTime)audits.elementAt(i));
        }
        pr.setEndStatus(nodeStatus);
    }

    /**
     * @param amounts
     * @param jmfRes
     */
    private void generateResourceSignal(JDFJMF jmfRes)
    {
        VElement vResResourceInfo=getVResLink(3);
        JDFSignal sig=jmfRes.appendSignal(EnumType.Resource);
        JDFResourceQuParams rqp=sig.appendResourceQuParams();
        rqp.setJDF(m_Node);
        rqp.setExact(false);
        boolean bAllExact=true;

        if (vResResourceInfo != null) {
            Iterator<KElement> vResResourceInfoIterator = vResResourceInfo.iterator();
            while (vResResourceInfoIterator.hasNext())
            {
                JDFResourceInfo ri=sig.appendResourceInfo();
                if(workType!=null)
                {
                    ri.appendMISDetails().setWorkType(workType);
                }
                final JDFResourceLink rl = (JDFResourceLink) vResResourceInfoIterator.next();
                LinkAmount la=getLinkAmount(rl.getrRef());
                boolean bExact=la!=null && la.isCopyResInfo();
                bAllExact=bAllExact && bExact;
                rqp.setExact(bExact);
                ri.setLink(rl,rqp);
            }
        }
        rqp.setExact(bAllExact);
    }

    /**
     * @param lastAb
     * @param jp
     */
    private void setJobPhaseAmounts(final LinkAmount la, JDFJobPhase jp)
    {
        if(la==null) 
            return;

        LinkAmount.AmountBag lastAb=la.lastBag;
        if(la.isTrackWaste())
        {
            if(lastAb.totalAmount!=0) {
                jp.setPhaseAmount(lastAb.phaseAmount);
                jp.setAmount(lastAb.totalAmount);
            }
            if(lastAb.totalWaste!=0) {
                jp.setPhaseWaste(lastAb.phaseWaste);
                jp.setWaste(lastAb.totalWaste);
            }
        }
        else
        {
            if((lastAb.totalAmount+lastAb.totalWaste)!=0) {
                jp.setPhaseAmount(lastAb.phaseAmount+lastAb.phaseWaste);
                jp.setAmount(lastAb.totalAmount+lastAb.totalWaste);
            }

        }
        double total=0;

        total=la.startAmount;
        if(total!=0)
        {
            jp.setTotalAmount(total);
            jp.setPercentCompleted(lastAb.totalAmount/total*100.0);
        }
    }


    /**
     * @param resLink
     * @param n : 1=phaseTime, 2=node, 3=resourceinfo
     * @return
     */
    private VElement getVResLink(int n)
    {
        if(vLinkAmount==null)
            return null;
        VElement vRet=new VElement();
        for(int i=0;i<vLinkAmount.length;i++)
        {
            LinkAmount la=vLinkAmount[i];
            if(n==1) {
                vRet.add(la.getPhaseTimeLink());
            }
            if(n==2) {
                vRet.add(la.updateNodeLink());
            }
            if(n==3) {
                vRet.add(la.getResourceInfoLink());
            }
        }
        return vRet;
    }

    /**
     * @return the docJMFPhaseTime
     */
    public JDFDoc getDocJMFPhaseTime()
    {
        return docJMFPhaseTime;
    }

    /**
     * @return the docJMFResource
     */
    public JDFDoc getDocJMFResource()
    {
        return docJMFResource;
    }

    ///////////////////////////////////////////////////////////////////////


    ///////////////////////////////////////////////////////////////////////

    /**
     * container class to set amounts and waste in phaseTime
     */
    private class LinkAmount
    {
        private class AmountBag
        {
            /**
             * refID of the resourceLink to set
             */
            protected double totalAmount;
            protected double phaseAmount;
            protected double totalWaste;
            protected double phaseWaste;


            @Override
            public String toString()
            {
                return "[AmountBag totalAmount="+totalAmount+" phaseAmount="+phaseAmount+" totalWaste="+totalWaste+" phaseWaste="+phaseWaste+" ]";
            }

            /**
             *
             * @param _refID refID of the resource that is being counted
             */
            protected AmountBag()
            {
                reset();
            }

            /**
             *
             */
            protected void reset()
            {
                totalAmount=0;
                phaseAmount=0;
                totalWaste=0;
                phaseWaste=0;
            }

            /**
             * copy ctor
             * @param bag
             */
            protected AmountBag(AmountBag bag)
            {
                totalAmount=bag.totalAmount;
                phaseAmount=bag.phaseAmount;
                totalWaste=bag.totalWaste;
                phaseWaste=bag.phaseWaste;
            }

            /**
             * @param amount
             * @param waste
             * @param bNewPhase
             */
            protected void addPhase(double amount, double waste, boolean bNewPhase)
            {
                totalAmount+=amount;
                totalWaste+=waste;
                if(bNewPhase)
                {
                    phaseAmount=amount;
                    phaseWaste=waste;
                }
                else
                {
                    phaseAmount+=amount;
                    phaseWaste+=waste;
                }
            }
        } // end AmountBag

        /////////////////////////////////////////////////////////////////////

        protected double startAmount=0;
        protected double startWaste=0;
        protected JDFResourceLink rl;
        protected String refID;
        private final AmountBag lastBag;
        protected VJDFAttributeMap vResPartMap;

        protected LinkAmount(JDFResourceLink _rl)
        {
            JDFNode dump=new JDFDoc("JDF").getJDFRoot();
            dump.appendResourceLinkPool().copyElement(_rl, null);
            final JDFResource target = _rl.getTarget();
            dump.appendResourcePool().copyElement(target, null);
            rl=(JDFResourceLink)dump.getResourceLinkPool().getElement(_rl.getNodeName(), null, 0);
            lastBag=new AmountBag();
            refID=rl.getrRef();
            vResPartMap=new VJDFAttributeMap(m_vPartMap);
            JDFAttributeMap map=null;
            if(vResPartMap!=null)
            {
//              final VString partIDKeys = target.getPartIDKeys();
//              Set keyset=partIDKeys==null ? null : partIDKeys.getSet();
//              vResPartMap.reduceMap(keyset);
                if(vResPartMap.size()==0)
                    vResPartMap=null;
                map=(vResPartMap==null) ? null : vResPartMap.elementAt(0);
            }
            startAmount=rl.getAmount(map);


            if(startAmount==-1)
            {
                map=new JDFAttributeMap(map);
                map.put(EnumPartIDKey.Condition, "Good");
                startAmount=rl.getAmount(map);
                if(startAmount==-1) {
                    startAmount=0;
                }
                map.put(EnumPartIDKey.Condition, "Waste");
                startWaste=rl.getAmount(map);
                if(startWaste==-1) {
                    startWaste=0;
                }
            }
            if(isTrackWaste())
            {
                map=new JDFAttributeMap(map);
                map.put(EnumPartIDKey.Condition, "Good");
                lastBag.totalAmount+=rl.getActualAmount(map);
                map.put(EnumPartIDKey.Condition, "Waste");
                lastBag.totalWaste+=rl.getActualAmount(map);
            }
            else
            {
                lastBag.totalAmount+=rl.getActualAmount(map);
            }
        }


        /**
         * @param lastBag
         * @return
         */
        protected JDFResourceLink updateNodeLink()
        {
            final JDFResourceLink nodeLink=m_Node.getLink(0,null,new JDFAttributeMap(AttributeName.RREF,rl.getrRef()),null);
            VJDFAttributeMap vMap=new VJDFAttributeMap(vResPartMap);
            if(vMap.size()==0) {
                vMap.add(new JDFAttributeMap());
            }
            if(nodeLink!=null)
            {
                if(isTrackWaste())
                {
                    vMap.put(EnumPartIDKey.Condition, "Good");
                    nodeLink.setAmountPoolAttribute(AttributeName.ACTUALAMOUNT, StringUtil.formatDouble(lastBag.totalAmount), null, vMap);
                    vMap.put(EnumPartIDKey.Condition, "Waste");
                    nodeLink.setAmountPoolAttribute(AttributeName.ACTUALAMOUNT, StringUtil.formatDouble(lastBag.totalWaste), null, vMap);
                }
                else
                {
                    nodeLink.setAmountPoolAttribute(AttributeName.ACTUALAMOUNT, StringUtil.formatDouble(lastBag.totalAmount+lastBag.totalWaste), null, vMap);
                }
                // update output status
                if(lastBag.totalAmount>0)
                {
                    JDFResource r=nodeLink.getLinkRoot();
                    if(vResPartMap!=null)
                    {
                        for(int i=0;i<vResPartMap.size();i++)
                        {
                            JDFAttributeMap m=vResPartMap.elementAt(i);
                            JDFResource rp=r.getPartition(m, null);
                            if(rp!=null)
                                rp.setResStatus(EnumResStatus.Available, false);
                        }
                    }
                    else
                    {
                        r.setResStatus(EnumResStatus.Available, false);
                    }
                }
            }
            return nodeLink;

        }

        /**
         * @return
         */
        protected JDFResourceLink getPhaseTimeLink()
        {
            cleanAmounts();
            setPhaseAmounts();
            return rl;
        }

        /**
         * @return
         */
        protected JDFResourceLink getResourceAuditLink()
        {
            cleanAmounts();
            setPhaseAmounts();
            return rl;
        }
        /**
         * @return
         */
        protected JDFResourceLink getResourceInfoLink()
        {
            cleanAmounts();
            return setPhaseAmounts();       
        }


        private JDFResourceLink setPhaseAmounts()
        {
            VJDFAttributeMap vMap=new VJDFAttributeMap(vResPartMap);
            if(vMap.size()==0) {
                vMap.add(new JDFAttributeMap());
            }
            if(isTrackWaste())
            {
                vMap.put(EnumPartIDKey.Condition, "Good");
                if(lastBag.totalAmount!=0) {
                    rl.setAmountPoolAttribute(AttributeName.ACTUALAMOUNT, StringUtil.formatDouble(lastBag.phaseAmount), null, vMap);
                }
                if(startAmount!=0) {
                    rl.setAmountPoolAttribute(AttributeName.AMOUNT, StringUtil.formatDouble(startAmount), null, vMap);
                }
                vMap.put(EnumPartIDKey.Condition, "Waste");
                if(lastBag.totalWaste!=0) {
                    rl.setAmountPoolAttribute(AttributeName.ACTUALAMOUNT, StringUtil.formatDouble(lastBag.phaseWaste), null, vMap);
                }
                if(startWaste!=0) {
                    rl.setAmountPoolAttribute(AttributeName.AMOUNT, StringUtil.formatDouble(startWaste), null, vMap);
                }
            }
            else
            {
                if(lastBag.totalAmount + lastBag.totalWaste !=0) {
                    rl.setAmountPoolAttribute(AttributeName.ACTUALAMOUNT, StringUtil.formatDouble(lastBag.phaseAmount+lastBag.phaseWaste), null, vMap);
                }
                if(startAmount+startWaste!=0) {
                    rl.setAmountPoolAttribute(AttributeName.AMOUNT, StringUtil.formatDouble(startAmount+startWaste), null, vMap);
                }
            }
            return rl;
        }

        /**
         * @param rl2
         */
        private void cleanAmounts()
        {

            rl.removeAttribute(AttributeName.AMOUNT);
            rl.removeAttribute(AttributeName.ACTUALAMOUNT);
            rl.removeChild(ElementName.AMOUNTPOOL, null,0);
        }
        /**
         * @param amount
         * @param waste
         * @param bNewPhase
         */
        protected void addPhase(double amount, double waste, boolean bNewPhase)
        {
            lastBag.addPhase(amount, waste, bNewPhase);
        }

        /* (non-Javadoc)
         * @see java.lang.Object#toString()
         */
        @Override
        public String toString()
        {
            StringBuffer sb=new StringBuffer();
            sb.append("LinkAmount: refID=");
            sb.append(refID);
            sb.append("\n");
            sb.append(vResPartMap);
            sb.append("\n");
            sb.append(lastBag);

            return sb.toString();

        }


        protected boolean linkFitsKey(String key)
        {
            if(key==null)
                return true;

            return rl.matchesString(key);
        }

        protected boolean linkFitsKey(Set keys)
        {
            if(keys==null)
                return false;

            return  keys.contains(rl.getNamedProcessUsage())
            || keys.contains(rl.getLinkedResourceName())
            || keys.contains(refID)
            || keys.contains(rl.getAttribute(AttributeName.USAGE));
        }


        /**
         * @return the bCopyResInfo
         */
        public boolean isCopyResInfo()
        {
            return linkFitsKey(setCopyResInfo);
        }


        /**
         * @return the bTrackWaste
         */
        public boolean isTrackWaste()
        {
            return linkFitsKey(setTrackWaste);
        }

    }
    /*
     * @return the m_deviceID
     */
    public String getDeviceID()
    {
        return m_deviceID;
    }
    /*
     * @return the m_moduleID
     */
    public VString getModuleeID()
    {
        return m_moduleID;
    }

    /**
     * @param m_deviceid the m_deviceID to set
     */
    public void setDeviceID(String deviceid)
    {
        m_deviceID = deviceid;
    }

    /**
     * @param m_deviceid the m_deviceID to set
     */
    public void addModule(String moduleID, String moduleType)
    {
        if(m_moduleID==null)
            m_moduleID=new VString();
        if(m_moduleType==null)
            m_moduleType=new VString();
        m_moduleID.add(moduleID);
        m_moduleType.add(moduleType);
    }

    /**
     * set waste tracking on or off for the resourcelink rl
     * @param rl the resourcelink to the resource to track
     * @param b tracking on or off
     */
    public void setTrackWaste(String resID, boolean b)
    {
        if(b)
            setTrackWaste.add(resID);
        else
            setTrackWaste.remove(resID);
    }

    /**
     * set copying the resource into resourceInfo on or off for the resourcelink rl
     * @param rl the resourcelink to the resource to copy
     * @param b tracking on or off
     */
    public void setCopyResInResInfo(String _refID, boolean b)
    {
        if(b)
            setCopyResInfo.add(_refID);
        else
            setCopyResInfo.remove(_refID);

    }

    /**
     * 
     * @param resID the resource ID to set/track
     * reason for the audit
     * @return JDFResourceAudit the generated audit
     */
    public JDFResourceAudit setResourceAudit(String resID, EnumReason reason)
    {
        LinkAmount la=getLinkAmount(resID);
        if(la==null)
            return null;
        JDFAuditPool ap=m_Node.getCreateAuditPool();

        JDFResourceAudit resourceAudit=ap.addResourceAudit(null);
        resourceAudit.setContentsModified(false);
        resourceAudit.setReason(reason);

        resourceAudit.copyElement(la.getResourceAuditLink(), null);
        resourceAudit.setPartMapVector(m_vPartMap);

        return resourceAudit;
    }

    /**
     *
     */
    public JDFProcessRun setProcessResult(EnumNodeStatus endStatus)
    {
        setPhase(EnumNodeStatus.Completed, null, EnumDeviceStatus.Idle, null);
        JDFAuditPool ap=m_Node.getCreateAuditPool();
        JDFProcessRun pr=(JDFProcessRun) ap.getAudit(-1,EnumAuditType.ProcessRun, null, m_vPartMap);
        return pr;
    }

    /**
     * @param queueEntryID
     */
    public void setQueueEntryID(String _queueEntryID)
    {
        queueEntryID=_queueEntryID;        
    }
    /**
     * @param _workstepID
     */
    public void setWorkStepID(String _workstepID)
    {
        workStepID=_workstepID;        
    }
    /**
     * @param queueEntryID
     */
    public String getQueueEntryID()
    {
        return queueEntryID;        
    }
    /**
     * @param queueEntryID
     */
    public String getWorkStepID()
    {
        return workStepID;        
    }

    /**
     * sets the MISDetails/@WorkType for default audis, resource audits and phaseTime elements
     * @param _workType the worktype to set, if null no MISDetails and no Worktype are added.
     * closes all ongoing phases and starts a new phase
     */
    public void setWorkType(EnumWorkType _workType)
    {
        if(ContainerUtil.equals(_workType,workType))
            return; // nop

        workType=_workType;
    }

    public EnumDeviceStatus getStatus()
    {
        return status;
    }

    public String getStatusDetails()
    {
        return statusDetails;
    }

    public JDFDate getStartDate()
    {
        return startDate;
    }

}