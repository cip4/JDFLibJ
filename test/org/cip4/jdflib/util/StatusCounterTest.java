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
 *    Alternately, this acknowledgment mrSubRefay appear in the software itself,
 *    if and wherever such third-party acknowledgments normally appear.
 *
 * 4. The names "CIP4" and "The International Cooperation for the Integration of
 *    Processes in  Prepress, Press and Postpress" must
 *    not be used to endorse or promote products derived from this
 *    software without prior written permission. For written
 *    permission, please contact info@cip4.org.
 *
 * 5. Products derived from this software may not be called "CIP4",
 *    nor may "CIP4" appear in their name, without prior writtenrestartProcesses()
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
 * SPECIAL, EXEMPLARY, OR CONSEQUENTIrSubRefAL DAMAGES (INCLUDING, BUT NOT
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
 * originally based on software restartProcesses()
 * copyright (c) 1999-2001, Heidelberger Druckmaschinen AG
 * copyright (c) 1999-2001, Agfa-Gevaert N.V.
 *
 * For more information on The International Cooperation for the
 * Integration of Processes in  Prepress, Press and Postpress , please see
 * <http://www.cip4.org/>.
 *
 */
/*
 * @author muchadie
 */
package org.cip4.jdflib.util;

import org.cip4.jdflib.JDFTestCaseBase;
import org.cip4.jdflib.auto.JDFAutoDeviceInfo.EnumDeviceStatus;
import org.cip4.jdflib.core.AttributeName;
import org.cip4.jdflib.core.JDFDoc;
import org.cip4.jdflib.core.JDFElement.EnumNodeStatus;
import org.cip4.jdflib.jmf.JDFDeviceInfo;
import org.cip4.jdflib.jmf.JDFJobPhase;
import org.cip4.jdflib.jmf.JDFResponse;
import org.cip4.jdflib.jmf.JDFMessage.EnumFamily;
import org.cip4.jdflib.jmf.JDFMessage.EnumType;
import org.cip4.jdflib.node.JDFNode;
import org.cip4.jdflib.resource.process.JDFExposedMedia;


public class StatusCounterTest extends JDFTestCaseBase
{
    public void testAddPhase()
    {
        JDFDoc d=creatXMDoc();
        JDFNode n=d.getJDFRoot();
        StatusCounter sc=new StatusCounter(n,null,null);
        JDFExposedMedia m=(JDFExposedMedia) n.getMatchingResource("ExposedMedia", null, null, 0);
        String resID=m.getID();
        sc.setFirstRefID(resID);
        sc.addPhase(resID, 200, 0);
        boolean bChanged=sc.setPhase(EnumNodeStatus.InProgress, "i", EnumDeviceStatus.Running, "r");
        assertTrue(bChanged);
        JDFDoc docJMF=sc.getDocJMFPhaseTime();
        JDFResponse sig=(JDFResponse) docJMF.getJMFRoot().getMessageElement(EnumFamily.Response, EnumType.Status, 0);
        JDFJobPhase jp=sig.getDeviceInfo(0).getJobPhase(0);
        assertEquals(jp.getAmount(), 200,0);
        sc.addPhase(resID, 0, 100);
        sc.setTrackWaste(m.getID(), true);
        bChanged= sc.setPhase(EnumNodeStatus.InProgress, "i", EnumDeviceStatus.Running, "r");
        assertFalse(bChanged);
        docJMF=sc.getDocJMFPhaseTime();
        sig=(JDFResponse) docJMF.getJMFRoot().getMessageElement(EnumFamily.Response, EnumType.Status, 0);
        jp=sig.getDeviceInfo(0).getJobPhase(0);
        assertEquals(jp.getAmount(), 200,0);
        assertEquals(jp.getWaste(), 100,0);
        bChanged=sc.setPhase(EnumNodeStatus.InProgress, "ii", EnumDeviceStatus.Running, "r");
        assertTrue(bChanged);
        docJMF=sc.getDocJMFPhaseTime();
        sig=(JDFResponse) docJMF.getJMFRoot().getMessageElement(EnumFamily.Response, EnumType.Status, 0);
        jp=sig.getDeviceInfo(0).getJobPhase(0);
        assertEquals(jp.getAmount(), 200,0);
        assertEquals(jp.getWaste(), 100,0);
        assertTrue(jp.hasAttribute(AttributeName.PHASEAMOUNT));
        sig=(JDFResponse) docJMF.getJMFRoot().getMessageElement(EnumFamily.Response, EnumType.Status, 0);
        jp=sig.getDeviceInfo(1).getJobPhase(0);
        assertFalse(jp.hasAttribute(AttributeName.PHASEAMOUNT));
       
        sc.setFirstRefID("dummy");
        sc.addPhase(resID, 0, 100);
        sc.setTrackWaste(m.getID(), true);
        sc.setPhase(EnumNodeStatus.InProgress, "i", EnumDeviceStatus.Running, "r");
        docJMF=sc.getDocJMFPhaseTime();
        sig=(JDFResponse) docJMF.getJMFRoot().getMessageElement(EnumFamily.Response, EnumType.Status, 0);
        jp=sig.getDeviceInfo(0).getJobPhase(0);
        assertFalse(jp.hasAttribute(AttributeName.AMOUNT));
    }
    
    
    public void testIdle()
    {
        JDFDoc d=creatXMDoc();
        JDFNode n=d.getJDFRoot();
        StatusCounter sc=new StatusCounter(n,null,null);
        JDFExposedMedia m=(JDFExposedMedia) n.getMatchingResource("ExposedMedia", null, null, 0);
        String resID=m.getID();
        sc.setFirstRefID(resID);
        sc.addPhase(resID, 200, 0);
        boolean bChanged=sc.setPhase(EnumNodeStatus.InProgress, "i", EnumDeviceStatus.Running, "r");
        assertTrue(bChanged);
        JDFDoc docJMF=sc.getDocJMFPhaseTime();
        JDFResponse sig=(JDFResponse) docJMF.getJMFRoot().getMessageElement(EnumFamily.Response, EnumType.Status, 0);
        JDFDeviceInfo deviceInfo = sig.getDeviceInfo(0);
        JDFJobPhase jp=deviceInfo.getJobPhase(0);
        assertEquals(jp.getAmount(), 200,0);
        sc.addPhase(resID, 0, 100);
        sc.setTrackWaste(m.getID(), true);
        bChanged= sc.setPhase(EnumNodeStatus.InProgress, "i", EnumDeviceStatus.Running, "r");
        assertFalse(bChanged);
        docJMF=sc.getDocJMFPhaseTime();
        sig=(JDFResponse) docJMF.getJMFRoot().getMessageElement(EnumFamily.Response, EnumType.Status, 0);
        bChanged= sc.setPhase(EnumNodeStatus.Completed, null, EnumDeviceStatus.Idle, null);
        assertTrue(bChanged);
        
        sc.setActiveNode(null, null, null);
        bChanged= sc.setPhase(null, null, EnumDeviceStatus.Idle, null);
        assertFalse(bChanged);
        bChanged=sc.setPhase(null, null, EnumDeviceStatus.Idle, "very idle");
        assertTrue(bChanged);

       

        docJMF=sc.getDocJMFPhaseTime();
        sig=(JDFResponse) docJMF.getJMFRoot().getMessageElement(EnumFamily.Response, EnumType.Status, 0);
        deviceInfo=sig.getDeviceInfo(0);
        jp=deviceInfo.getJobPhase(0);
        assertNull(jp);
    }    
}
