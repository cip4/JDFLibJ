package org.cip4.jdflib.jmf;

import java.io.File;

import org.cip4.jdflib.JDFTestCaseBase;
import org.cip4.jdflib.auto.JDFAutoDeviceInfo.EnumDeviceCondition;
import org.cip4.jdflib.auto.JDFAutoDeviceInfo.EnumDeviceStatus;
import org.cip4.jdflib.core.ElementName;
import org.cip4.jdflib.core.JDFDoc;
import org.cip4.jdflib.core.KElement.EnumValidationLevel;
import org.cip4.jdflib.jmf.JDFMessage.EnumType;

/**
 * @author Rainer Prosi
 *
 * Test of the Resource JMF
 */
public class JMFStatusTest extends JDFTestCaseBase
{
    /////////////////////////////////////////////////////////////////////
    
    public void testStatusDetails()
    {
        JDFDoc doc = new JDFDoc(ElementName.JMF);
        JDFJMF jmf=doc.getJMFRoot();
       
        JDFSignal s=jmf.appendSignal();
        jmf.setSenderID("DeviceSenderID");
        
        
        s.setType(EnumType.Status);
        JDFStatusQuParams sqp=s.appendStatusQuParams();
        sqp.setJobID("JobID");
        sqp.setJobPartID("JobPartID");
       
        JDFDeviceInfo di=s.appendDeviceInfo();
        di.setDeviceCondition(EnumDeviceCondition.NeedsAttention);
        di.setDeviceStatus(EnumDeviceStatus.Stopped);
        di.setStatusDetails("OutputAreaFull PaperJam Repair");
        
        
        
        doc.write2File(sm_dirTestDataTemp+File.separator+"JMFStatusSignal.jmf", 2, false);
        assertTrue(jmf.isValid(EnumValidationLevel.Complete));
    }

}