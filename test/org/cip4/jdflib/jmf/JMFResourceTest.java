package org.cip4.jdflib.jmf;

import org.cip4.jdflib.JDFTestCaseBase;
import org.cip4.jdflib.auto.JDFAutoUsageCounter.EnumScope;
import org.cip4.jdflib.core.ElementName;
import org.cip4.jdflib.core.JDFDoc;
import org.cip4.jdflib.core.VString;
import org.cip4.jdflib.core.KElement.EnumValidationLevel;
import org.cip4.jdflib.jmf.JDFMessage.EnumType;
import org.cip4.jdflib.pool.JDFAmountPool;
import org.cip4.jdflib.resource.JDFResource.EnumResStatus;
import org.cip4.jdflib.resource.process.JDFUsageCounter;

/**
 * @author Rainer Prosi
 *
 * Test of the Resource JMF
 */
public class JMFResourceTest extends JDFTestCaseBase
{
    /////////////////////////////////////////////////////////////////////
    
    public void testUsageCounter()
    {
        JDFDoc doc = new JDFDoc(ElementName.JMF);
        JDFJMF jmf=doc.getJMFRoot();
       
        JDFSignal s=jmf.appendSignal();
        jmf.setSenderID("DeviceSenderID");
        
        
        s.setType(EnumType.Resource);
        JDFResourceQuParams rqp=s.appendResourceQuParams();
        rqp.setJobID("JobID");
        rqp.setJobPartID("JobPartID");
        rqp.setResourceName(ElementName.USAGECOUNTER);
        
        JDFResourceInfo ri=s.appendResourceInfo();
        ri.setActualAmount(42);
        JDFUsageCounter uc=(JDFUsageCounter) ri.appendElement(ElementName.USAGECOUNTER);
        uc.setID("UsageCounterID");
        uc.setScope(EnumScope.Job);
        uc.setCounterID("DeviceCounterID");
        uc.setResStatus(EnumResStatus.Available,true);
        uc.setCounterTypes(new VString("NormalSize"," "));
        doc.write2File(sm_dirTestDataTemp+fileSeparator+"JMFResourceSignal.jmf", 2, false);
        assertTrue(jmf.isValid(EnumValidationLevel.Complete));
    }

   /////////////////////////////////////////////////////////////////////
    
    public void testMedia()
    {
        JDFDoc doc = new JDFDoc(ElementName.JMF);
        JDFJMF jmf=doc.getJMFRoot();
       
        JDFSignal s=jmf.appendSignal();
        jmf.setSenderID("DeviceSenderID");
        
        
        s.setType(EnumType.Resource);
        JDFResourceQuParams rqp=s.appendResourceQuParams();
        rqp.setJobID("JobID");
        rqp.setJobPartID("JobPartID");
        rqp.setResourceName(ElementName.MEDIA);
        
        JDFResourceInfo ri=s.appendResourceInfo();
        JDFAmountPool ap=ri.getCreateAmountPool(0);
        //TODO continue
    }
}