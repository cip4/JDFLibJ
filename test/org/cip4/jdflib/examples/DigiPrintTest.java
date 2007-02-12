/*
 * The CIP4 Software License, Version 1.0
 *
 *
 * Copyright (c) 2001-2006 The International Cooperation for the Integration of
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
import org.cip4.jdflib.auto.JDFAutoDeviceInfo.EnumDeviceStatus;
import org.cip4.jdflib.core.ElementName;
import org.cip4.jdflib.core.JDFDoc;
import org.cip4.jdflib.core.JDFElement;
import org.cip4.jdflib.core.JDFResourceLink;
import org.cip4.jdflib.core.VElement;
import org.cip4.jdflib.core.VString;
import org.cip4.jdflib.core.JDFElement.EnumNodeStatus;
import org.cip4.jdflib.core.JDFResourceLink.EnumUsage;
import org.cip4.jdflib.node.JDFNode;
import org.cip4.jdflib.node.JDFNode.EnumProcessUsage;
import org.cip4.jdflib.node.JDFNode.EnumType;
import org.cip4.jdflib.resource.JDFResource.EnumResStatus;
import org.cip4.jdflib.resource.process.JDFComponent;
import org.cip4.jdflib.resource.process.JDFMedia;
import org.cip4.jdflib.resource.process.JDFMiscConsumable;
import org.cip4.jdflib.resource.process.JDFUsageCounter;
import org.cip4.jdflib.util.StatusUtil;
import org.cip4.jdflib.util.StatusUtil.AmountBag;


public class DigiPrintTest extends JDFTestCaseBase
{
    /**
     * test MIS to Finishing ICS
     * @return
     */
    public void testAmount() throws Exception
    {
        JDFElement.setLongID(false);
        JDFDoc d=new JDFDoc("JDF");
        JDFNode n=d.getJDFRoot();
        n.setJobID("JobID");
        n.setType(EnumType.Combined);
        n.setTypes(new VString("Interpreting Rendering DigitalPrinting Stitching"," "));
        JDFComponent comp=(JDFComponent) n.addResource(ElementName.COMPONENT, null, EnumUsage.Output, null, null, null, null);
        JDFResourceLink rlc=n.getLink(comp,null);
        rlc.setAmount(20,null);
        rlc.setDescriptiveName("The link points to 20 planned and 20 good + 2 Waste brochures");
        
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
        
        JDFMedia med=(JDFMedia) n.appendMatchingResource(ElementName.MEDIA, EnumProcessUsage.AnyInput, null);
        med.setResStatus(EnumResStatus.Available, false);
        JDFResourceLink rlm=n.getLink(med, null);
        rlm.setAmount(100,null);
        rlm.setDescriptiveName("The link points to 100 actual sheets");

        Thread.sleep(1000);
        comp.setResStatus(EnumResStatus.Available, true);

        
        VElement vRL=new VElement();
        vRL.add(rlc);
        vRL.add(rlu);
        vRL.add(rlm);
        vRL.add(rlmc);
        StatusUtil stUtil=new StatusUtil(n,null,vRL);
        stUtil.setDeviceID("MyDevice");
        stUtil.setTrackWaste(rlm,true);
        stUtil.setTrackWaste(rlc,true);
        stUtil.setCopyResInResInfo(rlu,true);
        
        d.write2File(sm_dirTestDataTemp+File.separator+"DigiPrintAmount_initial.jdf",2,false);
        
        AmountBag[] bags=new AmountBag[vRL.size()];
        bags[0]=stUtil.new AmountBag(rlc.getrRef());
        bags[1]=stUtil.new AmountBag(rlu.getrRef());
        bags[2]=stUtil.new AmountBag(rlm.getrRef());
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
       
        d.write2File(sm_dirTestDataTemp+File.separator+"DigiPrintAmount_final.jdf",2,false);
     }
    
    /////////////////////////////////////////////////////////////////////////
}
