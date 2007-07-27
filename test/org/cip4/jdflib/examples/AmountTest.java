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
import org.cip4.jdflib.core.JDFAudit.EnumAuditType;
import org.cip4.jdflib.core.JDFElement.EnumNodeStatus;
import org.cip4.jdflib.core.JDFResourceLink.EnumUsage;
import org.cip4.jdflib.datatypes.JDFAttributeMap;
import org.cip4.jdflib.datatypes.VJDFAttributeMap;
import org.cip4.jdflib.jmf.JDFJMF;
import org.cip4.jdflib.jmf.JDFSignal;
import org.cip4.jdflib.node.JDFNode;
import org.cip4.jdflib.node.JDFNode.EnumType;
import org.cip4.jdflib.pool.JDFAmountPool;
import org.cip4.jdflib.pool.JDFAuditPool;
import org.cip4.jdflib.resource.JDFNotification;
import org.cip4.jdflib.resource.JDFProcessRun;
import org.cip4.jdflib.resource.JDFResourceAudit;
import org.cip4.jdflib.resource.JDFResource.EnumPartIDKey;
import org.cip4.jdflib.resource.process.JDFComponent;
import org.cip4.jdflib.resource.process.JDFExposedMedia;
import org.cip4.jdflib.resource.process.JDFMedia;
import org.cip4.jdflib.util.StatusCounter;


public class AmountTest extends JDFTestCaseBase
{
    private JDFNode n;
    private JDFComponent outComp;
    private JDFMedia inMedia;
    private JDFResourceLink rlOut;
    private JDFResourceLink rlMediaIn;
    private JDFDoc d;

    /**
     * test specification of planned waste in AmountPool
     * @return
     */
    public void testPlannedWaste() throws Exception
    {
        JDFElement.setLongID(false);
        d = new JDFDoc("JDF");
        n=d.getJDFRoot();
        n.setType(EnumType.ConventionalPrinting);
        outComp=(JDFComponent) n.addResource(ElementName.COMPONENT, null, EnumUsage.Output, null, null, null, null);
        rlOut=n.getLink(outComp, null);
        JDFAmountPool ap=rlOut.getCreateAmountPool();

        JDFComponent cover=(JDFComponent) outComp.addPartition(EnumPartIDKey.SheetName, "Cover");
        JDFAttributeMap map=cover.getPartMap();
        ap.appendXMLComment("Want 10000-10500 good cover sheets and allow for 500 waste cover sheets", null);
        map.put(EnumPartIDKey.Condition, "Good");
        rlOut.setAmount(10000, map);
        rlOut.setMaxAmount(10500, map);
        map.put(EnumPartIDKey.Condition, "Waste");
        rlOut.setMaxAmount(500, map);

        ap.appendXMLComment("Want 20000 good first insert sheets and allow for 200 waste insert sheets", null);
        JDFComponent sheet1=(JDFComponent) outComp.addPartition(EnumPartIDKey.SheetName, "Sheet1");
        map=sheet1.getPartMap();
        map.put(EnumPartIDKey.Condition, "Good");
        rlOut.setAmount(20000, map);
        map.put(EnumPartIDKey.Condition, "Waste");
        rlOut.setMaxAmount(200, map);

        ap.appendXMLComment("Want 20000 good second insert sheets and allow for 200 waste insert sheets", null);
        JDFComponent sheet2=(JDFComponent) outComp.addPartition(EnumPartIDKey.SheetName, "Sheet2");
        map=sheet2.getPartMap();
        map.put(EnumPartIDKey.Condition, "Good");
        rlOut.setAmount(20000, map);
        map.put(EnumPartIDKey.Condition, "Waste");
        rlOut.setMaxAmount(100, map);


        d.write2File(sm_dirTestDataTemp+"plannedWaste.jdf", 2, true);
    }
    /**
     * test specification of planned waste in AmountPool
     * @return
     */
    public void testPlannedWasteICS() throws Exception
    {
        JDFAmountPool ap=rlOut.getCreateAmountPool();
        JDFAmountPool apIn=rlMediaIn.getCreateAmountPool();

        JDFComponent cover=(JDFComponent) outComp.addPartition(EnumPartIDKey.SheetName, "Cover");
        JDFAttributeMap map=cover.getPartMap();
        ap.appendXMLComment("Want 10000-10400 good cover sheets and allow for 500 waste cover sheets", null);
        map.put(EnumPartIDKey.Condition, "Good");
        rlOut.setAmount(10000, map);
        rlOut.setMaxAmount(10400, map);
        apIn.appendXMLComment("Amount[Good]: planned consumption for good production\n"+
                "MaxAmount[Good]: planned maximum consumption for good production\n"+
        "MaxAmount[Waste]: planned Media for waste", null);
        rlMediaIn.setAmount(10500, map);
        map.put(EnumPartIDKey.Condition, "Waste");
        rlMediaIn.setMaxAmount(500, map);

        ap.appendXMLComment("Want 20000 good first insert sheets and allow for 200 waste insert sheets", null);
        JDFComponent sheet1=(JDFComponent) outComp.addPartition(EnumPartIDKey.SheetName, "Sheet1");
        map=sheet1.getPartMap();
        map.put(EnumPartIDKey.Condition, "Good");
        rlOut.setAmount(20000, map);
        rlOut.setMaxAmount(20800, map);
        rlMediaIn.setAmount(21000, map);
        map.put(EnumPartIDKey.Condition, "Waste");
        rlMediaIn.setMaxAmount(200, map);

        ap.appendXMLComment("Want 20000 good second insert sheets and allow for 100 waste insert sheets", null);
        JDFComponent sheet2=(JDFComponent) outComp.addPartition(EnumPartIDKey.SheetName, "Sheet2");
        map=sheet2.getPartMap();
        map.put(EnumPartIDKey.Condition, "Good");
        rlOut.setAmount(20000, map);
        rlOut.setMaxAmount(20800, map);
        rlMediaIn.setAmount(20900, map);
        map.put(EnumPartIDKey.Condition, "Waste");
        rlMediaIn.setMaxAmount(100, map);

        d.write2File(sm_dirTestDataTemp+"plannedWasteICS.jdf", 2, true);
        
   /*     
        map=cover.getPartMap();
        map.put(EnumPartIDKey.Condition, "Good");
        rl.getAmountPool().getPartAmount(map, 0).appendXMLComment("Actually produced covers: 10200\nWaste put on output stack:100");
        rlIn.getAmountPool().getPartAmount(map, 0).appendXMLComment("Total consumed sheets: 10400\nOf that: sheets wasted: 200");
        rl.setActualAmount(10200, map);
        rlIn.setActualAmount(10200, map);
        map.put(EnumPartIDKey.Condition, "Waste");
        rl.setActualAmount(100, map);
        rlIn.setActualAmount(200, map);
        
        d.write2File(sm_dirTestDataTemp+"actualWasteICS.jdf", 2, true);
     */   
    }
    /**
     * @return
     */
    protected void setUp() throws Exception
    {
        super.setUp();
        JDFElement.setLongID(false);
        d=new JDFDoc("JDF");

        n = d.getJDFRoot();
        n.appendXMLComment("Example to illustrate JDF 1.3 Base and MIS compatible amount handling", null);
        n.setType(EnumType.ConventionalPrinting);
        outComp = (JDFComponent) n.addResource(ElementName.COMPONENT, null, EnumUsage.Output, null, null, null, null);
        inMedia = (JDFMedia) n.addResource(ElementName.MEDIA, null, EnumUsage.Input, null, null, null, null);
        rlOut = n.getLink(outComp, null);
        rlMediaIn = n.getLink(inMedia, null);
    }

    //////////////////////////////////////////////////////////////////////////////////

    public void testAudits() throws Exception
    {
        testPlannedWasteICS();
        VString vs=new VString("Cover Sheet1 Sheet2"," ");

        VElement vRL=new VElement();
        vRL.add(rlOut);
        vRL.add(rlMediaIn);


        for(int j=0;j<2;j++) 
        {
            boolean bMinimal=j==0;

            for(int i=0;i<vs.size();i++)
            {
                String sheet=vs.stringAt(i);
                VJDFAttributeMap vmP=new VJDFAttributeMap();
                vmP.add(new JDFAttributeMap(EnumPartIDKey.SheetName,sheet));
                StatusCounter stUtil=new StatusCounter(n,vmP,vRL);

                String refComp=rlOut.getrRef();
                String refMedia=rlMediaIn.getrRef();
                
                stUtil.setTrackWaste(refComp,true);
                stUtil.setTrackWaste(refMedia,true);

                if(i==0)
                    stUtil.setPhase(EnumNodeStatus.Stopped, "PowerOn", EnumDeviceStatus.Stopped, "PowerOn" );
                stUtil.setPhase(EnumNodeStatus.Setup, "FormChange", EnumDeviceStatus.Setup, "FormChange");
                stUtil.addPhase(refMedia,0, 200);
                stUtil.addPhase(refComp,0, 200);
                stUtil.setPhase(EnumNodeStatus.Setup, "FormChange", EnumDeviceStatus.Setup, "FormChange");

                if(i>=1 &&!bMinimal)
                {
                    JDFResourceAudit ra=stUtil.setResourceAudit(refMedia,EnumReason.ProcessResult);

                    stUtil.setResourceAudit(refComp,EnumReason.ProcessResult);

                    stUtil.clearAmounts(refMedia);
                    stUtil.addPhase(refMedia, 50, 0);
                    JDFResourceAudit ra2=stUtil.setResourceAudit(refMedia,EnumReason.OperatorInput);
                    ra2.setRef(ra);
                    ra2.setDescriptiveName("manual reset to using only 50 sheets because 100 initially were wastes");

                }
                stUtil.setPhase(EnumNodeStatus.InProgress, "Good", EnumDeviceStatus.Running, null);
                stUtil.addPhase(refMedia,4000, 0);
                stUtil.addPhase(refComp,4000, 0);
                stUtil.setPhase(EnumNodeStatus.Cleanup, "Washup during processing", EnumDeviceStatus.Cleanup, "Washup" );
                stUtil.setPhase(EnumNodeStatus.InProgress, "Waste", EnumDeviceStatus.Running, null);

                stUtil.addPhase(refMedia,0, i==0 ? 40 : 30);
                stUtil.addPhase(refComp, 0, i==0 ? 40 : 30);
                stUtil.setPhase(EnumNodeStatus.InProgress, "Good", EnumDeviceStatus.Running, null);

                stUtil.addPhase(refMedia,1000, 0);
                stUtil.addPhase(refComp,1000, 0);
                stUtil.setPhase(EnumNodeStatus.InProgress, "Good", EnumDeviceStatus.Running, null);
                stUtil.addPhase(refMedia,i==0 ? 5200 : 5400, 0);
                stUtil.addPhase(refComp,i==0 ? 5200 : 5400, 0);
                stUtil.setPhase(EnumNodeStatus.InProgress, "Good", EnumDeviceStatus.Running, null);

                JDFResourceAudit ra=stUtil.setResourceAudit(refMedia,EnumReason.ProcessResult);

                if(!bMinimal)
                {
                    stUtil.setResourceAudit(refComp,EnumReason.ProcessResult);

                    stUtil.clearAmounts(refMedia);
                    stUtil.addPhase(refMedia, 1==0 ? 10100 : 10200, 0);
                    JDFResourceAudit ra2=stUtil.setResourceAudit(refMedia,EnumReason.OperatorInput);
                    ra2.setRef(ra);
                    ra2.setDescriptiveName("manual reset to using only 10200 sheets because 100 initially were  wates");
                }
                JDFProcessRun pr=stUtil.setProcessResult(EnumNodeStatus.Completed);
                pr.setDescriptiveName("we even have the utterly useless ProcessRun");
            }
            if(bMinimal)
            {
                JDFAuditPool ap=n.getAuditPool();
                VElement audits=ap.getAudits(EnumAuditType.PhaseTime, null, null);
                for(int i=0;i<audits.size();i++)
                {
                    audits.item(i).deleteNode();
                }
            }
            d.write2File(sm_dirTestDataTemp+File.separator+"ConvPrintAmount_"+ (bMinimal ? "min":"full") +".jdf",2,false);
        }
    }
    
    public void testAuditsImageSetting() throws Exception
    {

        n.removeChildren(null, null, null);
        n.setType(EnumType.ImageSetting);
        JDFExposedMedia outXM = (JDFExposedMedia) n.addResource(ElementName.EXPOSEDMEDIA, EnumUsage.Output);
        inMedia = (JDFMedia) n.addResource(ElementName.MEDIA, null, EnumUsage.Input, null, null, null, null);
        
        rlOut = n.getLink(outXM, null);
        rlMediaIn = n.getLink(inMedia, null);
        VElement vRL=new VElement();
//        vRL.add(rlOut);
        vRL.add(rlMediaIn);

        outXM=(JDFExposedMedia) outXM.addPartition(EnumPartIDKey.SignatureName, "Sig1");
        VString vsSheet=new VString("Cover Sheet1 Sheet2"," ");
        VString vsCMYK=new VString("Cyan Magenta Yellow Black Spot1"," ");
        StatusCounter stUtil=new StatusCounter(n,null,vRL);
           
        for(int i=0;i<vsSheet.size();i++)
        {
            String sheet=vsSheet.stringAt(i);
            VJDFAttributeMap vmP=new VJDFAttributeMap();
            final JDFAttributeMap attributeMap = new JDFAttributeMap(EnumPartIDKey.SheetName,sheet);
            attributeMap.put("SignatureName","Sig1");
            
            vmP.add(attributeMap);
            stUtil.setActiveNode(n, vmP, vRL);
            String refXM=rlOut.getrRef();
            String refMedia=rlMediaIn.getrRef();

            stUtil.setTrackWaste(refXM,true);
            stUtil.setTrackWaste(refMedia,false);

            stUtil.setPhase(EnumNodeStatus.Stopped, "PowerOn", EnumDeviceStatus.Stopped, "PowerOn" );

            stUtil.setPhase(EnumNodeStatus.InProgress, "Imaging", EnumDeviceStatus.Running, null);
            stUtil.addPhase(refMedia,5, 0);
            stUtil.addPhase(refXM,5, 0);
            stUtil.setPhase(EnumNodeStatus.InProgress, "Imaging", EnumDeviceStatus.Running, null);
            
            JDFResourceAudit ra=stUtil.setResourceAudit(refMedia,EnumReason.ProcessResult);

            JDFProcessRun pr=stUtil.setProcessResult(EnumNodeStatus.Completed);
            pr.setDescriptiveName("we even have the utterly useless ProcessRun");
        }
        d.write2File(sm_dirTestDataTemp+File.separator+"ImageSetAmount_.jdf",2,false);
        JDFDoc d2=stUtil.getDocJMFResource();
        JDFJMF jmf=d2.getJMFRoot();
        JDFSignal sig=jmf.appendSignal(org.cip4.jdflib.jmf.JDFMessage.EnumType.Notification);
        JDFNotification not=sig.appendNotification();
        not.setXPathAttribute("MileStone/@MileStoneType", "PrepressCompleted");
        not.setXPathAttribute("MileStone/@Amount", "5");
        d2.write2File(sm_dirTestDataTemp+File.separator+"ImageSetAmount_.jmf",2,false);
    }
}
