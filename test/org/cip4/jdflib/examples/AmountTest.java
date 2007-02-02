/*
 * JDFExampleDocTest.java
 * 
 * @author muchadie
 */
package org.cip4.jdflib.examples;

import java.io.File;

import org.cip4.jdflib.JDFTestCaseBase;
import org.cip4.jdflib.auto.JDFAutoDeviceInfo.EnumDeviceStatus;
import org.cip4.jdflib.auto.JDFAutoResourceAudit.EnumReason;
import org.cip4.jdflib.core.ElementName;
import org.cip4.jdflib.core.JDFDoc;
import org.cip4.jdflib.core.JDFElement;
import org.cip4.jdflib.core.JDFResourceLink;
import org.cip4.jdflib.core.VElement;
import org.cip4.jdflib.core.VString;
import org.cip4.jdflib.core.JDFElement.EnumNodeStatus;
import org.cip4.jdflib.core.JDFResourceLink.EnumUsage;
import org.cip4.jdflib.datatypes.JDFAttributeMap;
import org.cip4.jdflib.datatypes.VJDFAttributeMap;
import org.cip4.jdflib.node.JDFNode;
import org.cip4.jdflib.node.JDFNode.EnumType;
import org.cip4.jdflib.pool.JDFAmountPool;
import org.cip4.jdflib.resource.JDFProcessRun;
import org.cip4.jdflib.resource.JDFResourceAudit;
import org.cip4.jdflib.resource.JDFResource.EnumPartIDKey;
import org.cip4.jdflib.resource.process.JDFComponent;
import org.cip4.jdflib.resource.process.JDFMedia;
import org.cip4.jdflib.util.StatusUtil;
import org.cip4.jdflib.util.StatusUtil.AmountBag;


public class AmountTest extends JDFTestCaseBase
{
    /**
     * test specification of planned waste in AmountPool
     * @return
     */
    public void testPlannedWaste() throws Exception
    {
        JDFElement.setLongID(false);
        JDFDoc d=new JDFDoc("JDF");
        JDFNode n=d.getJDFRoot();
        n.setType(EnumType.ConventionalPrinting);
        JDFComponent out=(JDFComponent) n.addResource(ElementName.COMPONENT, null, EnumUsage.Output, null, null, null, null);
        JDFResourceLink rl=n.getLink(out, null);
        JDFAmountPool ap=rl.getCreateAmountPool();
        
        JDFComponent cover=(JDFComponent) out.addPartition(EnumPartIDKey.SheetName, "Cover");
        JDFAttributeMap map=cover.getPartMap();
        ap.appendXMLComment("Want 10000-10500 good cover sheets and allow for 500 waste cover sheets");
        map.put(EnumPartIDKey.Condition, "Good");
        rl.setAmount(10000, map);
        rl.setMaxAmount(10500, map);
        map.put(EnumPartIDKey.Condition, "Waste");
        rl.setMaxAmount(500, map);
        
        ap.appendXMLComment("Want 20000 good first insert sheets and allow for 200 waste insert sheets");
        JDFComponent sheet1=(JDFComponent) out.addPartition(EnumPartIDKey.SheetName, "Sheet1");
        map=sheet1.getPartMap();
        map.put(EnumPartIDKey.Condition, "Good");
        rl.setAmount(20000, map);
        map.put(EnumPartIDKey.Condition, "Waste");
        rl.setMaxAmount(200, map);
        
        ap.appendXMLComment("Want 20000 good second insert sheets and allow for 200 waste insert sheets");
        JDFComponent sheet2=(JDFComponent) out.addPartition(EnumPartIDKey.SheetName, "Sheet2");
        map=sheet2.getPartMap();
        map.put(EnumPartIDKey.Condition, "Good");
        rl.setAmount(20000, map);
        map.put(EnumPartIDKey.Condition, "Waste");
        rl.setMaxAmount(100, map);
       
        
        d.write2File(sm_dirTestDataTemp+"plannedWaste.jdf", 2, true);
    }
    
    //////////////////////////////////////////////////////////////////////////////////
    
    public void testAudits() throws Exception
    {
        JDFElement.setLongID(false);
        JDFDoc d=new JDFDoc("JDF");
        JDFNode n=d.getJDFRoot();
        n.setType(EnumType.ConventionalPrinting);
        JDFComponent out=(JDFComponent) n.addResource(ElementName.COMPONENT, null, EnumUsage.Output, null, null, null, null);
        JDFResourceLink rlOut=n.getLink(out, null);
        
        JDFMedia media=(JDFMedia) n.addResource(ElementName.MEDIA, null, EnumUsage.Input, null, null, null, null);
        JDFResourceLink rlMedia=n.getLink(media, null);
        VString vs=new VString("Sheet1 Sheet2"," ");
       
        VElement vRL=new VElement();
        vRL.add(rlOut);
        vRL.add(rlMedia);
        
 
        for(int i=0;i<vs.size();i++)
        {
            String sheet=vs.stringAt(i);
            VJDFAttributeMap vmP=new VJDFAttributeMap();
            vmP.add(new JDFAttributeMap(EnumPartIDKey.SheetName,sheet));
            StatusUtil stUtil=new StatusUtil(n,vmP,vRL);
           
            AmountBag[] bags=new AmountBag[2];
            bags[0]=stUtil.new AmountBag(rlMedia);
            bags[1]=stUtil.new AmountBag(rlOut);

            stUtil.setTrackWaste(rlOut,true);

            if(i==0)
                stUtil.setPhase(EnumNodeStatus.Stopped, "PowerOn", EnumDeviceStatus.Stopped, "PowerOn" ,bags);
            bags[0].addPhase(0, 200, true);
            bags[1].addPhase(0, 200, true);
            stUtil.setPhase(EnumNodeStatus.Setup, "FormChange", EnumDeviceStatus.Setup, "FormChange" ,bags);
 
            if(i==1)
            {
                JDFResourceAudit ra=stUtil.setResourceAudit(bags[0],EnumReason.ProcessResult);
                
                stUtil.setResourceAudit(bags[1],EnumReason.ProcessResult);

                bags[0].reset();
                bags[0].totalAmount=50;
                JDFResourceAudit ra2=stUtil.setResourceAudit(bags[0],EnumReason.OperatorInput);
                ra2.setRef(ra);
                ra2.setDescriptiveName("manual reset to using only 50 sheets because 100 initially were  wates");

            }
            bags[0].addPhase(2000, 0, true);
            bags[1].addPhase(2000, 0, true);
            stUtil.setPhase(EnumNodeStatus.InProgress, "Good", EnumDeviceStatus.Running, null ,bags);

            bags[0].addPhase(0, 0, true);
            bags[1].addPhase(0, 0, true);
            stUtil.setPhase(EnumNodeStatus.Cleanup, "Washup during processing", EnumDeviceStatus.Cleanup, "Washup" ,bags);

            bags[0].addPhase(0, 40, true);
            bags[1].addPhase(0, 40, true);
            stUtil.setPhase(EnumNodeStatus.InProgress, "Waste", EnumDeviceStatus.Running, null ,bags);

            bags[0].addPhase(4000, 0, true);
            bags[1].addPhase(4000, 0, true);
            stUtil.setPhase(EnumNodeStatus.InProgress, "Good", EnumDeviceStatus.Running, null ,bags);
            
            JDFResourceAudit ra=stUtil.setResourceAudit(bags[0],EnumReason.ProcessResult);
            
            stUtil.setResourceAudit(bags[1],EnumReason.ProcessResult);

            bags[0].reset();
            bags[0].totalAmount=i==0 ? 5100 : 5050;
            JDFResourceAudit ra2=stUtil.setResourceAudit(bags[0],EnumReason.OperatorInput);
            ra2.setRef(ra);
            ra2.setDescriptiveName("manual reset to using only 2100 sheets because 100 initially were  wates");
            
            JDFProcessRun pr=stUtil.setProcessResult(EnumNodeStatus.Completed);
            pr.setDescriptiveName("we even have the utterly useless ProcessRun");
         }
        d.write2File(sm_dirTestDataTemp+File.separator+"ConvPrintAmount_final.jdf",2,false);

    }
}
