package org.cip4.jdflib.jmf;

import java.util.Iterator;

import org.cip4.jdflib.JDFTestCaseBase;
import org.cip4.jdflib.auto.JDFAutoDeviceInfo.EnumDeviceStatus;
import org.cip4.jdflib.auto.JDFAutoStatusQuParams.EnumDeviceDetails;
import org.cip4.jdflib.core.AttributeName;
import org.cip4.jdflib.core.ElementName;
import org.cip4.jdflib.core.JDFDoc;
import org.cip4.jdflib.core.JDFElement;
import org.cip4.jdflib.core.KElement.EnumValidationLevel;
import org.cip4.jdflib.jmf.JDFMessage.EnumFamily;
import org.cip4.jdflib.jmf.JDFMessage.EnumType;
import org.cip4.jdflib.resource.JDFNotification;

/**
 * @author MuchaD
 *
 * This implements the first fixture with unit tests for class JDFElement.
 */
public class JMFTest extends JDFTestCaseBase
{
    // member variables for the fixture
 
    
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

    public void testError()
    {
        JDFDoc doc = new JDFDoc(ElementName.JMF);
        JDFJMF jmf=doc.getJMFRoot();
        JDFResponse r=(JDFResponse) jmf.appendMessageElement (EnumFamily.Response, null);
        JDFNotification n=r.setErrorText("blub");
        assertEquals("get comment text",n.getComment(0).getText(),"blub");
        assertEquals("type",n.getType(),"Error");
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
        s.convertResponse(r);
        assertEquals("type",r.getType(),s.getType());
        assertTrue("ms equal",ms.isEqual(s.getMessageService(0)));
    }

}