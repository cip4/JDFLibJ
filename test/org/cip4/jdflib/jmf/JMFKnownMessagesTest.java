package org.cip4.jdflib.jmf;

import org.cip4.jdflib.JDFTestCaseBase;
import org.cip4.jdflib.auto.JDFAutoDeviceInfo.EnumDeviceStatus;
import org.cip4.jdflib.auto.JDFAutoMessageService.EnumJMFRole;
import org.cip4.jdflib.core.AttributeName;
import org.cip4.jdflib.core.ElementName;
import org.cip4.jdflib.core.JDFDoc;
import org.cip4.jdflib.core.XMLDoc;
import org.cip4.jdflib.core.KElement.EnumValidationLevel;
import org.cip4.jdflib.datatypes.JDFBaseDataTypes.EnumFitsValue;
import org.cip4.jdflib.jmf.JDFMessage.EnumType;
import org.cip4.jdflib.resource.JDFDeviceList;
import org.cip4.jdflib.resource.devicecapability.JDFAction;
import org.cip4.jdflib.resource.devicecapability.JDFActionPool;
import org.cip4.jdflib.resource.devicecapability.JDFDevCap;
import org.cip4.jdflib.resource.devicecapability.JDFDevCaps;
import org.cip4.jdflib.resource.devicecapability.JDFDeviceCap;
import org.cip4.jdflib.resource.devicecapability.JDFStringState;
import org.cip4.jdflib.resource.devicecapability.JDFTest;
import org.cip4.jdflib.resource.devicecapability.JDFTerm.EnumTerm;

/**
 * @author Rainer Prosi
 *
 * Test of the Resource JMF
 */
public class JMFKnownMessagesTest extends JDFTestCaseBase
{
    public void testJMFDevCaps()
    {
        JDFDoc doc = new JDFDoc(ElementName.JMF);
        JDFJMF jmfDC=doc.getJMFRoot();
       
        JDFResponse r=jmfDC.appendResponse(EnumType.KnownMessages);
        jmfDC.setSenderID("DeviceSenderID");
        
        JDFMessageService ms=r.appendMessageService();
        ms.setJMFRole(EnumJMFRole.Sender);
        ms.setQuery(true);
        ms.setType("KnownDevices");
        JDFDevCaps dcs=ms.appendDevCaps();
        dcs.setName("DeviceList");
        JDFDevCap dc=dcs.appendDevCapInPool();
        JDFDevCap dcDI=dc.appendDevCap();
        dcDI.setName(ElementName.DEVICEINFO);
        dcDI.setMinOccurs(1);
        dcDI.setMaxOccurs(1);
        JDFStringState state=dcDI.appendStringState(AttributeName.DEVICEID);
        state.setRequired(true);
        
        state=dcDI.appendStringState(AttributeName.DEVICESTATUS);
        state.setRequired(true);
        state.appendValueAllowedValue("Running");
        
        ms=r.appendMessageService();
        ms.setJMFRole(EnumJMFRole.Sender);
        ms.setQuery(true);
        ms.setType("KnownMessages");
        dcs=ms.appendDevCaps();
        dcs.setName("MessageService");
        dc=dcs.appendDevCapInPool();
        state=dc.appendStringState(AttributeName.TYPE);
        state.setRequired(true);
        state=dc.appendStringState("Foo");
        state.setRequired(false);
        
        
        JDFActionPool ap=ms.appendActionPool();
        JDFAction a=ap.appendActionTest(EnumTerm.IsPresentEvaluation, true);
        JDFTest t=a.getTest();
//        JDFTerm term=
        	t.getTerm();
        //TODO
        JDFDoc docJMF=new JDFDoc("JMF");
        JDFJMF jmf=docJMF.getJMFRoot();
        for(int i=0;i<3;i++)
        {
            JDFResponse resp=jmf.appendResponse(EnumType.KnownDevices);
            JDFDeviceList dl=resp.appendDeviceList();
            JDFDeviceInfo di=dl.appendDeviceInfo();
            di.setDeviceID("d123");
            di.setDeviceStatus(EnumDeviceStatus.Running);
            XMLDoc report=JDFDeviceCap.getJMFInfo(jmf, r, EnumFitsValue.Allowed, EnumValidationLevel.Complete, true);
            assertEquals(report.getRoot().getAttribute("IsValid"),"true");
        }
        {
            JDFResponse resp=jmf.appendResponse(EnumType.KnownMessages);
            JDFMessageService mi=resp.appendMessageService();
            mi.setType("FooBar");
            doc.write2File(sm_dirTestDataTemp+"JMFDevCap.xml", 2, false);
            docJMF.write2File(sm_dirTestDataTemp+"JMFDevCapTest.jmf", 2, false);
            
        }
        XMLDoc report=JDFDeviceCap.getJMFInfo(jmf, r, EnumFitsValue.Allowed, EnumValidationLevel.Complete, true);
        assertEquals(report.getRoot().getAttribute("IsValid"),"true");
        
        doc.write2File(sm_dirTestDataTemp+"JMFDevCap.xml", 2, false);
        docJMF.write2File(sm_dirTestDataTemp+"JMFDevCapTest.jmf", 2, false);
        {
            jmf.appendResponse(EnumType.AbortQueueEntry);             
        }
        report=JDFDeviceCap.getJMFInfo(jmf, r, EnumFitsValue.Allowed, EnumValidationLevel.Complete, true);
        assertEquals(report.getRoot().getAttribute("IsValid"),"false");
 
    }       
        /////////////////////////////////////////////////////////////////////
}