package org.cip4.jdflib.jmf;

import java.io.File;
import java.util.Vector;

import org.cip4.jdflib.JDFTestCaseBase;
import org.cip4.jdflib.auto.JDFAutoUsageCounter.EnumScope;
import org.cip4.jdflib.core.ElementName;
import org.cip4.jdflib.core.JDFDoc;
import org.cip4.jdflib.core.VString;
import org.cip4.jdflib.core.JDFResourceLink.EnumUsage;
import org.cip4.jdflib.core.KElement.EnumValidationLevel;
import org.cip4.jdflib.datatypes.JDFAttributeMap;
import org.cip4.jdflib.datatypes.JDFXYPair;
import org.cip4.jdflib.jmf.JDFMessage.EnumType;
import org.cip4.jdflib.node.JDFNode;
import org.cip4.jdflib.node.JDFNode.EnumProcessUsage;
import org.cip4.jdflib.resource.JDFResource.EnumPartIDKey;
import org.cip4.jdflib.resource.JDFResource.EnumResStatus;
import org.cip4.jdflib.resource.JDFResource.EnumResourceClass;
import org.cip4.jdflib.resource.process.JDFMedia;
import org.cip4.jdflib.resource.process.JDFUsageCounter;

/**
 * @author Rainer Prosi
 *
 * Test of the Resource JMF
 */
public class JMFResourceTest extends JDFTestCaseBase
{
    public void testResourceQuParams()
    {
        JDFDoc doc = new JDFDoc(ElementName.JMF);
        JDFJMF jmf=doc.getJMFRoot();
       
        JDFQuery c=jmf.appendQuery();
        jmf.setSenderID("MISSenderID");
        c.setType(EnumType.Resource);
        JDFResourceQuParams rqp=c.getCreateResourceQuParams(0);
        Vector vClasses=new Vector();
        vClasses.add(EnumResourceClass.Consumable);
        vClasses.add(EnumResourceClass.Handling);
        rqp.setClasses(vClasses);
        assertEquals(rqp.getClasses().toString(), vClasses.toString());

    }       
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
        doc.write2File(sm_dirTestDataTemp+File.separator+"JMFResourceSignal.jmf", 2, false);
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
        ri.getCreateAmountPool();
        //TODO continue
    }

  /////////////////////////////////////////////////////////////////////
    
    public void testApplyResourceCmd()
    {
        JDFDoc doc = new JDFDoc(ElementName.JMF);
        JDFJMF jmf=doc.getJMFRoot();
       
        JDFCommand c=jmf.appendCommand();
        jmf.setSenderID("DeviceSenderID");
        
        
        c.setType(EnumType.Resource);
        JDFResourceCmdParams rqp=c.appendResourceCmdParams();
        rqp.setJobID("JobID");
        rqp.setJobPartID("JobPartID");
        rqp.setResourceName("Media");
        JDFMedia m=(JDFMedia) rqp.appendElement("Media");
        m.setDimension(new JDFXYPair(20,30));
        
        JDFDoc docJDF=new JDFDoc(ElementName.JDF);
        JDFNode jdf=docJDF.getJDFRoot();
        jdf.setType(org.cip4.jdflib.node.JDFNode.EnumType.ConventionalPrinting);
        JDFMedia media=(JDFMedia)jdf.addResource("Media", null, EnumUsage.Input, null, null, null, null);
        media.setDimension(new JDFXYPair(40,60));
        rqp.setJobID(jdf.getJobID(true));
        rqp.setJobPartID(jdf.getJobPartID(true));
        
        rqp.applyResourceCommand(jdf);
        JDFMedia m2=(JDFMedia) jdf.getMatchingResource("Media", EnumProcessUsage.AnyInput, null, 0);
        assertEquals(m2.getDimension(), new JDFXYPair(20,30));
        
        final JDFAttributeMap sheetMap = new JDFAttributeMap("SheetName","S1");
        rqp.setPartMap(sheetMap);
        m.setDimension(new JDFXYPair(200,300));
        
        JDFMedia m2Sheet=(JDFMedia)m2.addPartition(EnumPartIDKey.SheetName, "S1");
        rqp.applyResourceCommand(jdf);
        assertEquals("retained root dimension",m2.getDimension(), new JDFXYPair(20,30));
        assertEquals("overwrote leaf root dimension",m2Sheet.getDimension(), new JDFXYPair(200,300));
               
        JDFMedia mPartRQP=(JDFMedia)m.addPartition(EnumPartIDKey.SheetName, "S1");
        mPartRQP.setDimension(new JDFXYPair(400,600));
        
        rqp.applyResourceCommand(jdf);
        assertEquals("retained root dimension",m2.getDimension(), new JDFXYPair(20,30));
        assertEquals("overwrote leaf root dimension",m2Sheet.getDimension(), new JDFXYPair(400,600));
        
    }
}