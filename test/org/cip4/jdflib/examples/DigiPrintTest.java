/*
 * The CIP4 Software License, Version 1.0
 *
 *
 * Copyright (c) 2001-2007 The International Cooperation for the Integration of
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
package org.cip4.jdflib.examples;

import java.io.File;

import org.cip4.jdflib.JDFTestCaseBase;
import org.cip4.jdflib.auto.JDFAutoBasicPreflightTest.EnumListType;
import org.cip4.jdflib.auto.JDFAutoDeviceInfo.EnumDeviceStatus;
import org.cip4.jdflib.auto.JDFAutoResourceAudit.EnumReason;
import org.cip4.jdflib.core.AttributeName;
import org.cip4.jdflib.core.ElementName;
import org.cip4.jdflib.core.JDFAudit;
import org.cip4.jdflib.core.JDFDoc;
import org.cip4.jdflib.core.JDFElement;
import org.cip4.jdflib.core.JDFResourceLink;
import org.cip4.jdflib.core.VElement;
import org.cip4.jdflib.core.VString;
import org.cip4.jdflib.core.JDFElement.EnumNodeStatus;
import org.cip4.jdflib.core.JDFResourceLink.EnumUsage;
import org.cip4.jdflib.datatypes.JDFIntegerList;
import org.cip4.jdflib.jmf.JDFDeviceInfo;
import org.cip4.jdflib.jmf.JDFJMF;
import org.cip4.jdflib.jmf.JDFJobPhase;
import org.cip4.jdflib.jmf.JDFMessage;
import org.cip4.jdflib.jmf.JDFSignal;
import org.cip4.jdflib.node.JDFNode;
import org.cip4.jdflib.node.JDFNode.EnumProcessUsage;
import org.cip4.jdflib.node.JDFNode.EnumType;
import org.cip4.jdflib.pool.JDFAuditPool;
import org.cip4.jdflib.resource.JDFModulePhase;
import org.cip4.jdflib.resource.JDFModuleStatus;
import org.cip4.jdflib.resource.JDFPhaseTime;
import org.cip4.jdflib.resource.JDFStrippingParams;
import org.cip4.jdflib.resource.JDFResource.EnumResStatus;
import org.cip4.jdflib.resource.devicecapability.JDFDevCap;
import org.cip4.jdflib.resource.devicecapability.JDFDevCaps;
import org.cip4.jdflib.resource.devicecapability.JDFDeviceCap;
import org.cip4.jdflib.resource.devicecapability.JDFNameState;
import org.cip4.jdflib.resource.process.JDFComponent;
import org.cip4.jdflib.resource.process.JDFDigitalPrintingParams;
import org.cip4.jdflib.resource.process.JDFMedia;
import org.cip4.jdflib.resource.process.JDFMiscConsumable;
import org.cip4.jdflib.resource.process.JDFUsageCounter;
import org.cip4.jdflib.util.JDFDate;
import org.cip4.jdflib.util.StatusUtil;
import org.cip4.jdflib.util.StatusUtil.AmountBag;


public class DigiPrintTest extends JDFTestCaseBase
{
    private JDFDoc doc;
    private JDFNode n;
    private JDFComponent comp;
    private JDFStrippingParams stripParams;
    private JDFResourceLink rlComp;
    private JDFDigitalPrintingParams digiParams;
    private JDFMedia med;
    private JDFResourceLink rlMedia;

    /**
     * test amount handling
     * @return
     */
    public void testModules() throws Exception
    {
        JDFAuditPool ap=n.getCreateAuditPool();
        ap.appendXMLComment("JDF 1.3 compatible auditing of module phases - note that modulephase start and end times are set outside of the phasetime start and end times", null);
        JDFPhaseTime pt=ap.addPhaseTime(EnumNodeStatus.Setup, null, null);
        JDFPhaseTime pt2=ap.addPhaseTime(EnumNodeStatus.InProgress, null, null);        
        final JDFDate date = new JDFDate();
        JDFModulePhase mpRIP=pt.appendModulePhase();
        JDFModulePhase mpRIP2=pt2.appendModulePhase();
        mpRIP.setStatus(EnumNodeStatus.InProgress);
        mpRIP2.setStatus(EnumNodeStatus.InProgress);
        mpRIP2.setDescriptiveName("This ModulePhase is actually the same as the initial ModulePhase in the setup PhaseTime");
        mpRIP.setModuleType("Imaging");
        mpRIP2.setModuleType("Imaging");
        pt.setStart(date);
        mpRIP.setStart(date);
        mpRIP2.setStart(date);
        date.addOffset(0,5,0,0);
        pt.setEnd(date);

        JDFModulePhase mpPrint=pt2.appendModulePhase();
        mpPrint.setStatus(EnumNodeStatus.InProgress);
        pt2.setStart(date);
        mpPrint.setStart(date);
        date.addOffset(0,30,0,0);
        mpRIP.setEnd(date);
        mpRIP2.setEnd(date);

        date.addOffset(0,70,0,0);
        pt2.setEnd(date);
        mpPrint.setEnd(date);
        mpPrint.setModuleType("Printer");
        doc.write2File(sm_dirTestDataTemp+"DigiPrintModule1.jdf", 2, false);
    }

    public void testModulesUpdate() throws Exception
    {
        JDFAuditPool ap=n.getCreateAuditPool();
        ap.appendXMLComment("JDF 1.3 compatible auditing of module phases with updates", null);
        JDFPhaseTime pt=ap.addPhaseTime(EnumNodeStatus.Setup, null, null);
        JDFPhaseTime pt2=ap.addPhaseTime(EnumNodeStatus.InProgress, null, null);        
        JDFPhaseTime pt3=ap.addPhaseTime(EnumNodeStatus.InProgress, null, null);        
        final JDFDate date = new JDFDate();
        JDFModulePhase mpRIP=pt.appendModulePhase();
        mpRIP.setModuleType("Imaging");
        JDFModulePhase mpJob=pt.appendModulePhase();
        mpJob.setModuleType("Manual");
        mpJob.setStatus(EnumNodeStatus.InProgress);
        JDFModulePhase mpPrint=pt.appendModulePhase();
        mpPrint.setModuleType("Printing");
        
        mpRIP.setStatus(EnumNodeStatus.InProgress);
        pt.setStart(date);
        mpRIP.setStart(date);
        date.addOffset(0,5,0,0);
        pt.setEnd(date);

        pt2.copyElement(mpRIP, null);
        pt2.copyElement(mpJob, null);
        pt2.copyElement(mpPrint, null);
        mpPrint.setStatus(EnumNodeStatus.InProgress);
        pt2.setStart(date);
        mpPrint.setStart(date);
        date.addOffset(0,30,0,0);
        mpRIP.setEnd(date);

        date.addOffset(0,70,0,0);
        pt2.setEnd(date);
        mpPrint.setEnd(date);
        pt3.copyElement(mpRIP, null);
        pt3.copyElement(mpJob, null);
        pt3.copyElement(mpPrint, null);

        doc.write2File(sm_dirTestDataTemp+"DigiPrintModuleUpdate.jdf", 2, false);
    }
    /**
     * test amount handling
     * @return
     */
    public void testModules14() throws Exception
    {
        VString v=new VString("orig fullList end",null);
        for(int i=0;i<v.size();i++)
        {
            String testType=v.stringAt(i);
            JDFAuditPool ap=n.getCreateAuditPool();
            ap.appendXMLComment("JDF 1.3 incompatible auditing of module phases the REQUIRED time attributes are not set in the ModulePhase elements\n"+
                    "- note that phases may now arbitrarily overlap\n"+
                    "The modulePhase elements now only specify which modules are involved, times are all defined by the phasetime proper", null);
            ap.appendXMLComment("The following phaseTime is executed by one module - the RIP,which executes two process steps (Interpreting and Rendering)", null);
            JDFPhaseTime ptRIP=ap.addPhaseTime(EnumNodeStatus.Setup, null, null);
            final JDFDate date = new JDFDate();
            ptRIP.setStart(date);

            JDFDoc jmfDoc = new JDFDoc("JMF");
            JDFJMF jmf=jmfDoc.getJMFRoot();
            jmf.setDescriptiveName("Initial phase when the RIP starts up");
            JDFSignal signal=jmf.appendSignal(JDFMessage.EnumType.Status);
            JDFDeviceInfo di=signal.appendDeviceInfo();


            JDFJobPhase jpRIP=di.appendJobPhase();
            di.setDeviceStatus(EnumDeviceStatus.Setup);
            jpRIP.setStartTime(date);
            jpRIP.setStatus(EnumNodeStatus.Setup);
            jpRIP.setJobID(n.getJobID(true));
            jpRIP.setJobPartID(n.getJobPartID(true));

            JDFModuleStatus msRIP=jpRIP.appendModuleStatus();
            msRIP.setCombinedProcessIndex(new JDFIntegerList("0 1"));
            msRIP.setModuleType("Imaging");
            msRIP.setModuleID("ID_Imaging");

            JDFModulePhase mpRIP=ptRIP.appendModulePhase();
            mpRIP.setCombinedProcessIndex(new JDFIntegerList("0 1"));
            mpRIP.setModuleType("Imaging");
            mpRIP.setModuleID("ID_Imaging");

            JDFModuleStatus msPrint=di.appendModuleStatus();
            msPrint.setCombinedProcessIndex(new JDFIntegerList("2"));
            msPrint.setModuleType("Printer");
            msPrint.setModuleID("ID_Printer");

            JDFModuleStatus msStitch=di.appendModuleStatus();
            msStitch.setCombinedProcessIndex(new JDFIntegerList("3"));
            msStitch.setModuleType("Stitcher");
            msStitch.setModuleID("ID_Stitcher");

            jmfDoc.write2File(sm_dirTestDataTemp+"moduleStatus"+testType+"0.jmf", 2, false);
            date.addOffset(0,5,0,0);
            jmf.setTimeStamp(date);

            JDFJobPhase jpPrint=di.appendJobPhase();
            di.setDeviceStatus(EnumDeviceStatus.Running);
            jpPrint.setStatus(EnumNodeStatus.InProgress);
            jpPrint.setStartTime(date);
            jpPrint.setJobID(n.getJobID(true));
            jpPrint.setJobPartID(n.getJobPartID(true));

            msPrint=jpPrint.appendModuleStatus();
            msPrint.setCombinedProcessIndex(new JDFIntegerList("2"));
            msPrint.setModuleType("Printer");
            msPrint.setModuleID("ID_Printer");

            msStitch=jpPrint.appendModuleStatus();
            msStitch.setCombinedProcessIndex(new JDFIntegerList("3"));
            msStitch.setModuleType("Stitcher");
            msStitch.setModuleID("ID_Stitcher");

            di.removeChildren(ElementName.MODULESTATUS, null, null);
            jmf.setDescriptiveName("Phase when the Printer and Finisher start up; RIP is still RIPping");
            jmfDoc.write2File(sm_dirTestDataTemp+"moduleStatus"+testType+"1.jmf", 2, false);


            ap.appendXMLComment("The following phaseTime is executed by two modules - sticher and printer", null);
            JDFPhaseTime ptPrint=ap.addPhaseTime(EnumNodeStatus.InProgress, null, null);        
            JDFModulePhase mpPrint=ptPrint.appendModulePhase();
            mpPrint.setCombinedProcessIndex(new JDFIntegerList("2"));
            mpPrint.setModuleType("Printer");
            mpPrint.setModuleID("ID_Printer");
            ptPrint.setStart(date);

            JDFModulePhase mpStitch=ptPrint.appendModulePhase();
            mpStitch.setCombinedProcessIndex(new JDFIntegerList("3"));
            mpStitch.setModuleType("Stitcher");
            mpStitch.setModuleID("ID_Stitcher");
            date.addOffset(0,30,0,0);
            ptRIP.setEnd(date);

            JDFDeviceInfo di2=null;
            if(i<2)
            {
                JDFSignal signal2=jmf.appendSignal(JDFMessage.EnumType.Status);
                di2=(JDFDeviceInfo) signal2.copyElement(di, null);
                di2.removeChild(ElementName.JOBPHASE, null, 0);
                if(i==1)
                {
                    JDFModuleStatus directMSRip=(JDFModuleStatus) di2.copyElement(msRIP, null);
                    directMSRip.setDeviceStatus(EnumDeviceStatus.Idle);
                }
            }
            else
            {
                jpRIP.setAttribute("EndTime", date.getDateTimeISO());
                jpRIP.setDescriptiveName("Added EndTime to explicitly close phase");
            }
            jmf.setTimeStamp(date);
            jmf.setDescriptiveName("Phase when the RIP has completed, Printer and Finisher are still RIPping");
            jmfDoc.write2File(sm_dirTestDataTemp+"moduleStatus"+testType+"2.jmf", 2, false);

            date.addOffset(0,70,0,0);
            ptPrint.setEnd(date);
            jmf.setTimeStamp(date);

            if(i<2)
            {
                signal.deleteNode();
                signal=jmf.appendSignal(JDFMessage.EnumType.Status);
                di=(JDFDeviceInfo) signal.copyElement(di2, null);
                di.removeChild(ElementName.JOBPHASE, null, 0);
                di.removeChild(ElementName.MODULESTATUS, null, 0);
                di.setDeviceStatus(EnumDeviceStatus.Idle);
                signal.appendXMLComment("Or should the complete list of modules also be specified here?", di);
            }
            else
            {
                jpRIP.deleteNode();
                jpPrint.setAttribute("EndTime", date.getDateTimeISO());
                jpPrint.setDescriptiveName("Added ne EndTime to explicitly close phase");
            }
            jmf.setDescriptiveName("Phase when the Printer and Finisher have completed");
            jmfDoc.write2File(sm_dirTestDataTemp+"moduleStatus"+testType+"3.jmf", 2, false);
            doc.write2File(sm_dirTestDataTemp+"DigiPrintModule.1.4.jdf", 2, false);
        }
    }

    /**
     * test devcaps for usagecounters
     * @return
     */
    public void testUsageCounterDevCaps() throws Exception
    {
        JDFDoc duc=new JDFDoc("DeviceCap");
        JDFDeviceCap devicecap=(JDFDeviceCap)duc.getRoot();
        JDFDevCaps dcs=devicecap.appendDevCaps();
        dcs.setName(ElementName.USAGECOUNTER);
        JDFDevCap dc=dcs.appendDevCapInPool();
        dc.setMinOccurs(3);
        dc.setMaxOccurs(3);
        JDFNameState counterID=dc.appendNameState(AttributeName.COUNTERID);
        counterID.setAllowedValueList(new VString("ID_Black ID_Color ID_Total",null));
        counterID.setListType(EnumListType.SingleValue);
        duc.write2File(sm_dirTestDataTemp+"DevCapUsageCounter.jdf", 2, false);
    }

    /**
     * test amount handling
     * @return
     */
    public void testDirectProof() throws Exception
    {
        n.setXMLComment("Example workflow with initioal warmup phase, one direct proof and 100 copies of 10 sheets each.\n"+
                "The direct proof is acceptable and included in the good output");
        digiParams.setDirectProofAmount(1);
        digiParams.setXMLComment("1 initial proof is requested");
        rlComp.setAmount(100, null);
        JDFAuditPool ap=n.getAuditPool();
        
        VElement vRL=new VElement();
        vRL.add(rlComp);
        vRL.add(rlMedia);
        
        StatusUtil stUtil=new StatusUtil(n,null,vRL);
        stUtil.setDeviceID("MyDevice");
        stUtil.setTrackWaste(rlMedia,true);
        stUtil.setTrackWaste(rlComp,false);       
        
        doc.write2File(sm_dirTestDataTemp+File.separator+"DigiPrintAmount_initial.jdf",2,false);

        AmountBag[] bags=new AmountBag[vRL.size()];
        bags[0]=stUtil.new AmountBag(rlComp.getrRef());
        bags[1]=stUtil.new AmountBag(rlMedia.getrRef());

        stUtil.setPhase(EnumNodeStatus.InProgress, "Waste", EnumDeviceStatus.Running, null,bags);
        ap.getLastPhase(null).setXMLComment("Phase where warm up waste is produced");
        bags[0].addPhase(0,2,true);
        bags[1].addPhase(0,20,true);

        stUtil.setPhase(EnumNodeStatus.InProgress, "Waste", EnumDeviceStatus.Running, null,bags);

        bags[0].addPhase(1,0,true);
        bags[1].addPhase(10,0,true);
        stUtil.setPhase(EnumNodeStatus.InProgress, "Good", EnumDeviceStatus.Running, null,bags);
        ap.getLastPhase(null).setXMLComment("Phase where 1 proof is produced");
        
        bags[0].addPhase(0,0,true);
        bags[1].addPhase(0,0,true);
        stUtil.setPhase(EnumNodeStatus.Stopped, "WaitForApproval", EnumDeviceStatus.Stopped, null,bags);
        ap.getLastPhase(null).setXMLComment("Phase where the proof is evaluated while the device is stopped");
        stUtil.setPhase(EnumNodeStatus.InProgress, "Good", EnumDeviceStatus.Running, null,bags);

        bags[0].addPhase(99,0,false);
        bags[1].addPhase(990,0,false);
        stUtil.setPhase(EnumNodeStatus.InProgress, "Good", EnumDeviceStatus.Running, null,bags);
        ap.getLastPhase(null).setXMLComment("Phase where the 100 copies are produced");
        
        bags[0].addPhase(0,0,true);
        bags[1].addPhase(0,0,true);
        stUtil.setPhase(EnumNodeStatus.Completed, "Idle", EnumDeviceStatus.Idle, null,bags);
        stUtil.setResourceAudit(bags[1], EnumReason.ProcessResult);
        doc.write2File(sm_dirTestDataTemp+File.separator+"DigiPrintProof_final.jdf",2,false);
        
    }
    /**
     * test amount handling
     * @return
     */
    public void testAmount() throws Exception
    {
        rlComp.setAmount(20,null);
        rlComp.setDescriptiveName("The link points to 20 planned and 20 good + 2 Waste brochures");

        JDFMiscConsumable mc=(JDFMiscConsumable) n.appendMatchingResource(ElementName.MISCCONSUMABLE, EnumProcessUsage.AnyInput, null);
        mc.setResStatus(EnumResStatus.Available, false);
        mc.setConsumableType("FooBar");
        mc.setUnit("Fnarfs");
        mc.setDescriptiveName("FooBars are always measured in Fnarfs");
        JDFResourceLink rlmc=n.getLink(mc, null);
        rlmc.setAmount(42,null);
        rlmc.setDescriptiveName("The link points to 42 actual FooBars");

        JDFUsageCounter uc=(JDFUsageCounter) n.appendMatchingResource(ElementName.USAGECOUNTER, EnumProcessUsage.AnyInput, null);
        uc.setResStatus(EnumResStatus.Available, false);
        uc.setCounterTypes(new VString("Click"," "));
        JDFResourceLink rlu=n.getLink(uc, null);
        rlu.setAmount(200,null);
        rlu.setDescriptiveName("The link points to 200 actual clicks");

        rlMedia.setAmount(100,null);
        rlMedia.setDescriptiveName("The link points to 100 actual sheets");

        Thread.sleep(1000);
        comp.setResStatus(EnumResStatus.Available, true);


        VElement vRL=new VElement();
        vRL.add(rlComp);
        vRL.add(rlu);
        vRL.add(rlMedia);
        vRL.add(rlmc);
        StatusUtil stUtil=new StatusUtil(n,null,vRL);
        stUtil.setDeviceID("MyDevice");
        stUtil.setTrackWaste(rlMedia,true);
        stUtil.setTrackWaste(rlComp,true);
        stUtil.setCopyResInResInfo(rlu,true);

        doc.write2File(sm_dirTestDataTemp+File.separator+"DigiPrintAmount_initial.jdf",2,false);

        AmountBag[] bags=new AmountBag[vRL.size()];
        bags[0]=stUtil.new AmountBag(rlComp.getrRef());
        bags[1]=stUtil.new AmountBag(rlu.getrRef());
        bags[2]=stUtil.new AmountBag(rlMedia.getrRef());
        bags[3]=stUtil.new AmountBag(rlmc.getrRef());
        stUtil.setPhase(EnumNodeStatus.InProgress, "Waste", EnumDeviceStatus.Running, null,bags);
        JDFDoc docStatusJMF=stUtil.getDocJMFPhaseTime();
        docStatusJMF.write2File(sm_dirTestDataTemp+File.separator+"DigiPrintAmountStatus0.jmf",2,false);
        JDFDoc docResJMF=stUtil.getDocJMFResource();
        docResJMF.write2File(sm_dirTestDataTemp+File.separator+"DigiPrintAmountResource0.jmf",2,false);

        bags[0].addPhase(0,2,true);
        bags[1].addPhase(0,20,true);
        bags[2].addPhase(0,10,true);
        bags[3].addPhase(0,0,true);
        stUtil.setPhase(EnumNodeStatus.InProgress, "Waste", EnumDeviceStatus.Running, null,bags);
        docStatusJMF=stUtil.getDocJMFPhaseTime();
        docStatusJMF.write2File(sm_dirTestDataTemp+File.separator+"DigiPrintAmountStatus1.jmf",2,false);
        docResJMF=stUtil.getDocJMFResource();
        docResJMF.write2File(sm_dirTestDataTemp+File.separator+"DigiPrintAmountResource1.jmf",2,false);

        bags[0].addPhase(15,0,true);
        bags[1].addPhase(150,0,true);
        bags[2].addPhase(75,0,true);
        bags[3].addPhase(32,0,true);        
        stUtil.setPhase(EnumNodeStatus.InProgress, "Good", EnumDeviceStatus.Running, null,bags);
        docStatusJMF=stUtil.getDocJMFPhaseTime();
        docStatusJMF.write2File(sm_dirTestDataTemp+File.separator+"DigiPrintAmountStatus2.jmf",2,false);
        docResJMF=stUtil.getDocJMFResource();
        docResJMF.write2File(sm_dirTestDataTemp+File.separator+"DigiPrintAmountResource2.jmf",2,false);

        bags[0].addPhase(5,0,false);
        bags[1].addPhase(50,0,false);
        bags[2].addPhase(25,0,false);
        bags[3].addPhase(11,0,false);        
        stUtil.setPhase(EnumNodeStatus.InProgress, "Good", EnumDeviceStatus.Running, null,bags);
        docStatusJMF=stUtil.getDocJMFPhaseTime();
        docStatusJMF.write2File(sm_dirTestDataTemp+File.separator+"DigiPrintAmountStatus3.jmf",2,false);
        docResJMF=stUtil.getDocJMFResource();
        docResJMF.write2File(sm_dirTestDataTemp+File.separator+"DigiPrintAmountResource3.jmf",2,false);

        bags[0].addPhase(0,0,true);
        bags[1].addPhase(0,0,true);
        bags[2].addPhase(0,0,true);
        bags[3].addPhase(0,0,true);
        stUtil.setPhase(EnumNodeStatus.Completed, "Idle", EnumDeviceStatus.Idle, null,bags);
        docStatusJMF=stUtil.getDocJMFPhaseTime();
        docStatusJMF.write2File(sm_dirTestDataTemp+File.separator+"DigiPrintAmountStatus4.jmf",2,false);
        docResJMF=stUtil.getDocJMFResource();
        docResJMF.write2File(sm_dirTestDataTemp+File.separator+"DigiPrintAmountResource4.jmf",2,false);

        doc.write2File(sm_dirTestDataTemp+File.separator+"DigiPrintAmount_final.jdf",2,false);
    }


    /**
     * 
     */
    protected void setUp()
    {
        JDFElement.setLongID(false);
        JDFAudit.setStaticAgentName(null);
        JDFAudit.setStaticAgentVersion(null);
        JDFAudit.setStaticAuthor(null);

        doc = new JDFDoc("JDF");
        n = doc.getJDFRoot();
        n.setJobID("JobID");
        n.setType(EnumType.Combined);
        n.setTypes(new VString("Interpreting Rendering DigitalPrinting Stitching"," "));
        comp = (JDFComponent) n.addResource(ElementName.COMPONENT, null, EnumUsage.Output, null, null, null, null);
        rlComp=n.getLink(comp,null);
        digiParams=(JDFDigitalPrintingParams) n.addResource(ElementName.DIGITALPRINTINGPARAMS, null, EnumUsage.Input, null, null, null, null);
        med=(JDFMedia) n.appendMatchingResource(ElementName.MEDIA, EnumProcessUsage.AnyInput, null);
        med.setResStatus(EnumResStatus.Available, false);
        rlMedia=n.getLink(med, null);
   
    }

    /////////////////////////////////////////////////////////////////////////
}
