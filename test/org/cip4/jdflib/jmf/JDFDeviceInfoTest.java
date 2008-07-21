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

package org.cip4.jdflib.jmf;

import org.cip4.jdflib.JDFTestCaseBase;
import org.cip4.jdflib.core.ElementName;
import org.cip4.jdflib.core.JDFDoc;
import org.cip4.jdflib.core.JDFElement;
import org.cip4.jdflib.core.JDFElement.EnumNodeStatus;
import org.cip4.jdflib.jmf.JDFMessage.EnumType;
import org.cip4.jdflib.pool.JDFAuditPool;
import org.cip4.jdflib.resource.JDFPhaseTime;
import org.cip4.jdflib.util.JDFDate;
import org.cip4.jdflib.util.StatusCounter;

/**
 * @author Rainer Prosi
 *
 * Test of the Status JMF
 */
public class JDFDeviceInfoTest extends JDFTestCaseBase
{
    private  JDFDeviceInfo di;

    public void setUp()
    {
        JDFElement.setLongID(false);
        JDFDoc doc = new JDFDoc(ElementName.DEVICEINFO);
        di=(JDFDeviceInfo) doc.getRoot();

    }
    /////////////////////////////////////////////////////////////////////

    public void testGetDeviceID()
    {
        JDFJMF jmf=(JDFJMF) new JDFDoc("JMF").getRoot();
        jmf.setSenderID("S1");
        di=jmf.appendSignal(EnumType.Status).appendDeviceInfo();
        assertEquals(jmf.getSenderID(),di.getDeviceID());
        di.appendDevice().setDeviceID("dd");
        assertEquals(di.getDeviceID(), "dd");
        di.setDeviceID("da");
        assertEquals(di.getDeviceID(), "da");
    }
    /////////////////////////////////////////////////////////////////////


    public void testNullDeviceStatus()
    {
        di.setDeviceStatus(null);
        assertNotNull("got here!",di);
    }
    /////////////////////////////////////////////////////////////////////

    public void testMergeLastPhase()
    {
        JDFDoc d=new JDFDoc("JDF");
        JDFAuditPool ap=d.getJDFRoot().getCreateAuditPool();
        JDFPhaseTime pt=ap.setPhase(EnumNodeStatus.InProgress, "dummy", null,null);
        JDFJobPhase jp=di.createJobPhaseFromPhaseTime(pt);
        jp.setPhaseAmount(200);
        jp.setAmount(200);
        jp.setPhaseWaste(100);
        JDFDate d1=jp.getPhaseStartTime();
        JDFDoc doc = new JDFDoc(ElementName.DEVICEINFO);
        JDFDeviceInfo di2=(JDFDeviceInfo) doc.getRoot();

        JDFJobPhase jp2=(JDFJobPhase) di2.copyElement(jp, null);
        jp2.setPhaseStartTime(new JDFDate());
        StatusCounter.sleep(1000);
        jp2.setPhaseAmount(300);
        jp2.setPhaseWaste(30);
        jp2.setAmount(500);
        assertTrue(di2.mergeLastPhase(di));
        assertEquals(jp2.getPhaseStartTime(), d1);
        assertEquals(jp2.getPhaseWaste(), 130.,0.);
        assertEquals(jp2.getPhaseAmount(), 500.,0.);
        assertEquals(jp2.getAmount(), 500.,0.);                    
    }
    /////////////////////////////////////////////////////////////////////

    public void testIsSamePhase()
    {

        JDFDeviceInfo di1=(JDFDeviceInfo) new JDFDoc(ElementName.DEVICEINFO).getRoot();
        JDFDeviceInfo di2=(JDFDeviceInfo) new JDFDoc(ElementName.DEVICEINFO).getRoot();


        assertTrue(di1.isSamePhase(di2, false));
        di1.appendEmployee().setPersonalID("p1");
        assertFalse(di1.isSamePhase(di2, false));
        di2.appendEmployee().setPersonalID("p1");
        assertTrue(di1.isSamePhase(di2, false));
        di1.appendEmployee().setPersonalID("p2");
        assertFalse(di1.isSamePhase(di2, false));
        di2.appendEmployee().setPersonalID("p3");
        assertFalse(di1.isSamePhase(di2, false));
    }
}