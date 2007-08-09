/*
 *
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

import java.util.Iterator;

import junit.framework.TestCase;

import org.cip4.jdflib.auto.JDFAutoDeviceInfo.EnumDeviceStatus;
import org.cip4.jdflib.auto.JDFAutoStatusQuParams.EnumDeviceDetails;
import org.cip4.jdflib.core.AttributeName;
import org.cip4.jdflib.core.ElementName;
import org.cip4.jdflib.core.JDFDoc;
import org.cip4.jdflib.core.JDFElement;
import org.cip4.jdflib.core.VElement;
import org.cip4.jdflib.core.KElement.EnumValidationLevel;
import org.cip4.jdflib.jmf.JDFMessage.EnumFamily;
import org.cip4.jdflib.jmf.JDFMessage.EnumType;
import org.cip4.jdflib.resource.JDFNotification;


public class JDFJMFTest extends TestCase
{

    public void testGetMessageVector()
    {
        JDFDoc doc = new JDFDoc(ElementName.JMF);
        JDFJMF jmf=doc.getJMFRoot();
        JDFCommand command=(JDFCommand) jmf.appendMessageElement(EnumFamily.Command,EnumType.Status);
        assertEquals(jmf.getMessageVector(null, EnumType.Status).elementAt(0), command);
        assertEquals(jmf.getMessageVector(null, EnumType.Status).size(), 1);
        JDFSignal signal=(JDFSignal) jmf.appendMessageElement(EnumFamily.Signal,EnumType.Status);
        assertEquals(jmf.getMessageVector(null, EnumType.Status).elementAt(0), command);
        assertEquals(jmf.getMessageVector(null, EnumType.Status).elementAt(1), signal);
        assertEquals(jmf.getMessageVector(null, EnumType.Status).size(), 2);
    }
/////////////////////////////////////////////////////////////////////////////
    public void testInit()
    {
        JDFDoc doc = new JDFDoc(ElementName.JMF);
        JDFJMF jmf=doc.getJMFRoot();
        jmf.setSenderID("sid");
        JDFCommand c=jmf.appendCommand();
        assertTrue(c.getID().indexOf(".sid.") != -1);
        assertTrue(jmf.toString().indexOf("xsi:type=")>0);
        assertEquals(jmf.getMaxVersion(), jmf.getVersion(false));
        assertEquals(jmf.getMaxVersion(), c.getMaxVersion(true));
    }
    
/////////////////////////////////////////////////////////////////////////////
    public void testTheSenderID()
    {
        JDFJMF.setTheSenderID("sid");
        JDFDoc doc = new JDFDoc(ElementName.JMF);
        JDFJMF jmf=doc.getJMFRoot();
        JDFCommand c=jmf.appendCommand();
        assertTrue(c.getID().indexOf(".sid.") != -1);
        JDFJMF.setTheSenderID(null);
    }
/////////////////////////////////////////////////////////////////////////////
    public void testgetSubmissionParams()
    {
        JDFDoc doc = new JDFDoc(ElementName.JMF);
        JDFJMF jmf=doc.getJMFRoot();
        assertNull(jmf.getSubmissionURL());
        JDFCommand c=jmf.appendCommand(EnumType.ResubmitQueueEntry);
        assertNull(jmf.getSubmissionURL());
        JDFResubmissionParams rsp=c.appendResubmissionParams();
        assertNull(jmf.getSubmissionURL());
        rsp.setURL("url");
        assertEquals("url", jmf.getSubmissionURL());
    }
/////////////////////////////////////////////////////////////////////////////
    public void testCreateResponse()
    {
        JDFJMF queries=JDFJMF.createJMF(EnumFamily.Query, EnumType.Status);
        queries.appendCommand(EnumType.Resource);
        queries.appendCommand(EnumType.Resource);
        queries.appendRegistration(EnumType.Resource);

        JDFJMF responses=queries.createResponse();
        final VElement messageVector = queries.getMessageVector(null, null);
        final VElement responseVector = responses.getMessageVector(null, null);
        assertEquals(responseVector.size(), 4);
        for(int i=0;i<responseVector.size();i++)
        {
            JDFResponse r=(JDFResponse)responseVector.elementAt(i);
            JDFMessage m=(JDFMessage)messageVector.elementAt(i);
            assertEquals(r.getrefID(), m.getID());
            assertEquals(r.getType(), m.getType());
        }
    }
/////////////////////////////////////////////////////////////////////////////
/////////////////////////////////////////////////////////////////////////////
    /////////////////////////////////////////////////////////////////////
    
    public void testConvertResponse()
    {
        JDFDoc doc = new JDFDoc(ElementName.JMF);
        JDFJMF jmf=doc.getJMFRoot();
        JDFDoc doc2 = new JDFDoc(ElementName.JMF);
        JDFJMF jmf2=doc2.getJMFRoot();
        JDFSignal s=jmf.appendSignal();
        JDFResponse r=jmf2.appendResponse();
        JDFQuery q=jmf.appendQuery();
        q.setType("KnownMessages");
        r.setQuery(q);
        assertEquals("refID",q.getID(),r.getrefID());
        
        JDFMessageService ms=r.appendMessageService();
        ms.setType("KnownMessages");
        s.convertResponse(r,q);
        assertEquals("type",r.getType(),s.getType());
        assertTrue("ms equal",ms.isEqual(s.getMessageService(0)));
        s=jmf.appendSignal();
        s.convertResponse(r,null);
        assertEquals("type",r.getType(),s.getType());
        assertTrue("ms equal",ms.isEqual(s.getMessageService(0)));
    }
    
    
    public void testConvertResponses()
    {
        JDFDoc doc = new JDFDoc(ElementName.JMF);
        JDFJMF jmf=doc.getJMFRoot();
        JDFDoc doc2 = new JDFDoc(ElementName.JMF);
        JDFJMF jmf2=doc2.getJMFRoot();
        JDFResponse r=jmf2.appendResponse();
        JDFQuery q=jmf.appendQuery();
        q.setType("KnownMessages");
        r.setQuery(q);
        assertEquals("refID",q.getID(),r.getrefID());
        
        jmf2.convertResponses(q);
        assertNull(jmf2.getResponse(0));
        assertEquals(jmf2.getSignal(0).getrefID(), q.getID());
    }
    public void testCreateJMF()
    {
        JDFJMF jmf=JDFJMF.createJMF(EnumFamily.Response, EnumType.AbortQueueEntry);
        assertEquals(jmf.getResponse(0).getEnumType(), EnumType.AbortQueueEntry);
        assertEquals(jmf.getResponse(0).getLocalName(), "Response");
    }
    /////////////////////////////////////////////////////////////////////
    
    public void testDeviceInfo()
    {
        JDFDoc doc = new JDFDoc(ElementName.JMF);
        JDFJMF jmf=doc.getJMFRoot();
        JDFSignal s=(JDFSignal) jmf.appendMessageElement (EnumFamily.Signal, null);
        s.setType("Status");
        JDFStatusQuParams sqp=s.appendStatusQuParams();
        sqp.setDeviceDetails(EnumDeviceDetails.Brief);
        JDFDeviceInfo di=s.appendDeviceInfo();
        di.setDeviceStatus(EnumDeviceStatus.Unknown);
        JDFJobPhase jp=di.appendJobPhase();
        assertEquals("",jp,di.getJobPhase(0));
        jp=(JDFJobPhase)di.appendElement("jdf:JobPhase",JDFElement.getSchemaURL());
        assertEquals("",jp,di.getJobPhase(1));
        assertNull("",di.getJobPhase(2));
        jp.appendNode();
        assertTrue(jp.isValid(EnumValidationLevel.Incomplete));
        jp.setAttribute("Status","fnarf");
        assertFalse(jp.isValid(EnumValidationLevel.Incomplete));
    }
    public void testError()
    {
        JDFDoc doc = new JDFDoc(ElementName.JMF);
        JDFJMF jmf=doc.getJMFRoot();
        JDFResponse r=(JDFResponse) jmf.appendMessageElement (EnumFamily.Response, null);
        JDFNotification n=r.setErrorText("blub");
        assertEquals("get comment text",n.getComment(0).getText(),"blub");
        assertEquals("type",n.getType(),"Error");
    }
    public void testGetMessageElement()
    {
        JDFDoc d=new JDFDoc("JMF");
        JDFJMF jmf=d.getJMFRoot();
        JDFCommand c=(JDFCommand) jmf.appendMessageElement(EnumFamily.Command,EnumType.Events);
        assertEquals(c,jmf.getMessageElement(EnumFamily.Command,EnumType.Events,0));
        jmf.appendComment();
        
        JDFSignal s=(JDFSignal) jmf.appendMessageElement(EnumFamily.Signal,EnumType.Events);
        assertEquals(s,jmf.getMessageElement(EnumFamily.Signal,EnumType.Events,0));
        assertEquals(s,jmf.getMessageElement(null,EnumType.Events,1));
        assertEquals(s,jmf.getMessageElement(null,null,1));
        
        JDFSignal s2=(JDFSignal) jmf.appendMessageElement(EnumFamily.Signal,EnumType.Status);
        assertEquals(s2,jmf.getMessageElement(EnumFamily.Signal,EnumType.Status,0));
        assertEquals(s2,jmf.getMessageElement(EnumFamily.Signal,null,1));
        assertEquals(s2,jmf.getMessageElement(null,null,2));
    }
    /////////////////////////////////////////////////////////////////////
    
    public void testJobPhase()
    {
        JDFDoc doc = new JDFDoc(ElementName.JMF);
        JDFJMF jmf=doc.getJMFRoot();
        JDFSignal s=(JDFSignal) jmf.appendMessageElement (EnumFamily.Signal, null);
        s.setType("Status");
        JDFStatusQuParams sqp=s.appendStatusQuParams();
        sqp.setDeviceDetails(EnumDeviceDetails.Brief);
        JDFDeviceInfo di=s.appendDeviceInfo();
        JDFJobPhase jp=di.appendJobPhase();
        assertEquals("",jp,di.getJobPhase(0));
        jp=(JDFJobPhase)di.appendElement("jdf:JobPhase",JDFElement.getSchemaURL());
        assertEquals("",jp,di.getJobPhase(1));
        assertNull("",di.getJobPhase(2));
        jp.appendNode();
        assertTrue(jp.isValid(EnumValidationLevel.Incomplete));
    }
    public void testMessage()
    {
        JDFDoc doc = new JDFDoc(ElementName.JMF);
        JDFJMF jmf=doc.getJMFRoot();
        jmf.setSenderID("Pippi Langstrumpf");
        
        Iterator it= JDFMessage.EnumFamily.iterator();
        while(it.hasNext())
        {
            JDFMessage.EnumFamily f=(JDFMessage.EnumFamily) it.next();
            if(f==null)
                continue;
            JDFMessage m=jmf.appendMessageElement (f, null);
            m.setType("KnownMessages");
            
            if(f.equals(JDFMessage.EnumFamily.Acknowledge))
            {
                JDFAcknowledge a=(JDFAcknowledge) m;
                a.setrefID("refID");
            }
            
            if(f.equals(JDFMessage.EnumFamily.Registration))
            {
                JDFRegistration r=(JDFRegistration) m;
                r.appendSubscription();
            }
            
            assertTrue(" added messages",jmf.getMessageVector (f, null).size()==1);
            assertTrue("xsi type",jmf.getMessageElement(f,null,0).hasAttribute(AttributeName.XSITYPE));
            assertEquals("xsi type",jmf.getMessageElement(f,null,0).getAttribute(AttributeName.XSITYPE),f.getName()+"KnownMessages");
            
        }
        
        assertTrue(" added messages",jmf.getMessageVector (null, null).size()==6);
        assertTrue("valid", jmf.isValid(EnumValidationLevel.Complete));
    }
    public void testPrivateMessage()
    {
        JDFDoc doc = new JDFDoc(ElementName.JMF);
        JDFJMF jmf=doc.getJMFRoot();
        JDFSignal s=(JDFSignal) jmf.appendMessageElement (EnumFamily.Signal, null);
        s.setType("foo:test");
        s.appendDevice();
        assertNull(s.getXSIType());
        assertTrue("get device",s.getDevice(0)!=null);
    }
    public void testReturnQueueEntry()
    {
        JDFDoc doc = new JDFDoc(ElementName.JMF);
        JDFJMF jmf=doc.getJMFRoot();
        JDFCommand c=(JDFCommand) jmf.appendMessageElement (EnumFamily.Command, null);
        c.setType("ReturnQueueEntry");
        JDFReturnQueueEntryParams rqe=c.appendReturnQueueEntryParams();
        rqe.setURL("http://foo.jdf");
        rqe.setQueueEntryID("dummyID");
        assertTrue("JDFReturnQueueEntryParams",rqe.isValid(EnumValidationLevel.Complete));
    }

}