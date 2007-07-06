package org.cip4.jdflib.jmf;

import java.io.File;
import java.util.Vector;

import org.cip4.jdflib.JDFTestCaseBase;
import org.cip4.jdflib.auto.JDFAutoResourceCmdParams.EnumUpdateMethod;
import org.cip4.jdflib.auto.JDFAutoUsageCounter.EnumScope;
import org.cip4.jdflib.core.ElementName;
import org.cip4.jdflib.core.JDFDoc;
import org.cip4.jdflib.core.JDFNodeInfo;
import org.cip4.jdflib.core.JDFRefElement;
import org.cip4.jdflib.core.VString;
import org.cip4.jdflib.core.JDFResourceLink.EnumUsage;
import org.cip4.jdflib.core.KElement.EnumValidationLevel;
import org.cip4.jdflib.datatypes.JDFAttributeMap;
import org.cip4.jdflib.datatypes.JDFXYPair;
import org.cip4.jdflib.jmf.JDFMessage.EnumFamily;
import org.cip4.jdflib.jmf.JDFMessage.EnumType;
import org.cip4.jdflib.node.JDFNode;
import org.cip4.jdflib.node.JDFNode.EnumProcessUsage;
import org.cip4.jdflib.resource.JDFResource;
import org.cip4.jdflib.resource.JDFResource.EnumPartIDKey;
import org.cip4.jdflib.resource.JDFResource.EnumResStatus;
import org.cip4.jdflib.resource.JDFResource.EnumResourceClass;
import org.cip4.jdflib.resource.process.JDFExposedMedia;
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
    /////////////////////////////////////////////////////////////////////

    public void testMediaRef()
    {

        JDFDoc doc = new JDFDoc(ElementName.JMF);
        JDFJMF jmf=doc.getJMFRoot();

        JDFSignal s=jmf.appendSignal();
        jmf.setSenderID("DeviceSenderID");


        s.setType(EnumType.Resource);
        JDFResourceQuParams rqp=s.appendResourceQuParams();
        rqp.setJobID("JobID");
        rqp.setJobPartID("JobPartID");
        rqp.setResourceName(ElementName.EXPOSEDMEDIA);

        JDFResourceInfo ri=s.appendResourceInfo();
        JDFExposedMedia xm=(JDFExposedMedia) ri.appendElement("ExposedMedia");

        JDFResourceInfo ri2=s.appendResourceInfo();
        JDFMedia m=(JDFMedia) ri2.appendElement("Media");
        JDFRefElement rm=xm.refElement(m);

        assertEquals("works initially ",m, rm.getTarget());
        assertEquals("also works with cache", m, rm.getTarget());
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
        JDFMedia mediaRQP=(JDFMedia) rqp.appendElement("Media");
        mediaRQP.setDimension(new JDFXYPair(20,30));

        JDFDoc docJDF=new JDFDoc(ElementName.JDF);
        JDFNode jdf=docJDF.getJDFRoot();
        jdf.setType(org.cip4.jdflib.node.JDFNode.EnumType.ConventionalPrinting);
        JDFMedia mediaJDF=(JDFMedia)jdf.addResource("Media", null, EnumUsage.Input, null, null, null, null);
        mediaJDF.setDimension(new JDFXYPair(40,60));
        rqp.setJobID(jdf.getJobID(true));
        rqp.setJobPartID(jdf.getJobPartID(true));

        rqp.applyResourceCommand(jdf);
        JDFMedia m2=(JDFMedia) jdf.getMatchingResource("Media", EnumProcessUsage.AnyInput, null, 0);
        assertEquals(m2.getDimension(), new JDFXYPair(20,30));

        final JDFAttributeMap sheetMap = new JDFAttributeMap("SheetName","S1");
        rqp.setPartMap(sheetMap);
        mediaRQP.setDimension(new JDFXYPair(200,300));

        JDFMedia m2Sheet=(JDFMedia)m2.addPartition(EnumPartIDKey.SheetName, "S1");
        rqp.applyResourceCommand(jdf);
        assertEquals("retained root dimension",m2.getDimension(), new JDFXYPair(20,30));
        assertEquals("overwrote leaf root dimension",m2Sheet.getDimension(), new JDFXYPair(200,300));

        JDFMedia mPartRQP=(JDFMedia)mediaRQP.addPartition(EnumPartIDKey.SheetName, "S1");
        mPartRQP.setDimension(new JDFXYPair(400,600));

        rqp.applyResourceCommand(jdf);
        assertEquals("retained root dimension",m2.getDimension(), new JDFXYPair(20,30));
        assertEquals("overwrote leaf root dimension",m2Sheet.getDimension(), new JDFXYPair(400,600));
        assertFalse(m2Sheet.hasAttribute_KElement("ID", null, false));

    }

    /**
     * Method testResourceCommand
     * 
     * @throws Exception
     */

    public void testResourceCommand ()
    throws Exception
    {
        JDFDoc jdfDoc = JDFDoc.parseFile(sm_dirTestData+"ResourceCommandTest.jdf");
        JDFNode root=jdfDoc.getJDFRoot();
        JDFResourceCmdParams params;

        JDFAttributeMap amAttr = new JDFAttributeMap ();

        amAttr.put ("Start", "2006-11-02T14:13:18+01:00");
        amAttr.put ("End",   "2006-11-02T15:13:18+01:00");
        String partID, resID;
        for(int i=0;i<2;i++)
        {

            JDFAttributeMap amParts = new JDFAttributeMap ();
            if(i==0)
            {

                amParts.put ("SignatureName", "Sig001");
                amParts.put ("SheetName",     "FB 001");
                amParts.put ("Side",          "Front");
                partID="SFP0.C";
                resID="Link49087948_000508";
            }
            else
            {
                resID="Link49165276_000704";
                amParts.put ("SignatureName", "Sig002");
                amParts.put ("SheetName",     "FB 002");
                amParts.put ("Side",          "Back");
                partID="SFP1.C";                
            }
            params = createResourceParams (partID, resID, amParts, amAttr);
            JDFNode n=root.getJobPart(partID, null);
            params.applyResourceCommand(n);
            assertNotNull(n);
            JDFNodeInfo ni=(JDFNodeInfo)n.getChildWithAttribute(ElementName.NODEINFO, "ID", null, resID, 0, false);
            assertNotNull(ni);
            JDFNodeInfo nip=(JDFNodeInfo) ni.getPartition(amParts, null);
            assertNotNull(nip);
            assertFalse(nip.hasAttribute_KElement("ID", null, false));
            assertFalse(nip.hasAttribute_KElement("SheetName", null, false));
         }
    }

    /**
     * Method createResourceParams
     * 
     * @param strJobPartID
     * @param strResourceID
     * @param amParts
     * @param amAttr
     * 
     * @return
     */

    private JDFResourceCmdParams createResourceParams (String strJobPartID, String strResourceID, JDFAttributeMap amParts, JDFAttributeMap amAttr)
    {
        JDFJMF jmf = JDFJMF.createJMF(EnumFamily.Command, JDFMessage.EnumType.Resource);

        JDFCommand cmd = jmf.getCommand (0);

        JDFResourceCmdParams params = cmd.appendResourceCmdParams ();

        final String strJobID      = "RefJob-1";
        final String strPartIDKeys = "SignatureName SheetName Side";

        params.setJobID        (strJobID);
        params.setJobPartID    (strJobPartID);
        params.setResourceID   (strResourceID);
        params.setResourceName ("NodeInfo");
        params.setUpdateMethod (EnumUpdateMethod.Incremental);

        params.setPartMap (amParts);

        JDFResource nodeInfo = params.appendResource ("NodeInfo");

        JDFResource resLeaf = nodeInfo.getCreatePartition (amParts, new VString (strPartIDKeys, null));

        resLeaf.setAttributes (amAttr);

        return (params);
    }


}