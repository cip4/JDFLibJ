/*
 * JDFExampleDocTest.java
 * 
 * @author muchadie
 */
package org.cip4.jdflib.examples;

import java.io.File;

import org.cip4.jdflib.JDFTestCaseBase;
import org.cip4.jdflib.auto.JDFAutoDeviceInfo.EnumDeviceStatus;
import org.cip4.jdflib.auto.JDFAutoMedia.EnumMediaType;
import org.cip4.jdflib.auto.JDFAutoPart.EnumSide;
import org.cip4.jdflib.core.ElementName;
import org.cip4.jdflib.core.JDFDoc;
import org.cip4.jdflib.core.JDFElement;
import org.cip4.jdflib.core.JDFNodeInfo;
import org.cip4.jdflib.core.JDFResourceLink;
import org.cip4.jdflib.core.VElement;
import org.cip4.jdflib.core.VString;
import org.cip4.jdflib.core.JDFElement.EnumNodeStatus;
import org.cip4.jdflib.core.JDFResourceLink.EnumUsage;
import org.cip4.jdflib.datatypes.JDFAttributeMap;
import org.cip4.jdflib.datatypes.VJDFAttributeMap;
import org.cip4.jdflib.node.JDFNode;
import org.cip4.jdflib.node.JDFNode.EnumType;
import org.cip4.jdflib.resource.JDFResource.EnumPartIDKey;
import org.cip4.jdflib.resource.JDFResource.EnumResStatus;
import org.cip4.jdflib.resource.process.JDFComponent;
import org.cip4.jdflib.resource.process.JDFExposedMedia;
import org.cip4.jdflib.resource.process.JDFMedia;
import org.cip4.jdflib.util.StatusCounter;


public class NColorTest extends JDFTestCaseBase
{
    private JDFNode node;
    private JDFNodeInfo nodeInfo;
    private JDFComponent component;
    private JDFMedia media;
    private JDFExposedMedia exposedMedia;
    private JDFDoc doc;
    private VString sheetsDone;

    ///////////////////////////////////////////////////////////////////

    /**
     * test iteration using Identical in  NodeInfo
     */
    public void testFrontBackIdentical()
    {
        JDFElement.setLongID(false);
        setup(false,2);
        JDFAttributeMap mapS1F=new JDFAttributeMap();
        mapS1F.put("SheetName", "Sheet1");
        mapS1F.put("Side", "Front");

        JDFAttributeMap mapS0F=new JDFAttributeMap();
        mapS0F.put("SheetName", "Sheet0");
        mapS0F.put("Side", "Front");

        VJDFAttributeMap vMap=new VJDFAttributeMap();
        vMap.add(mapS0F);
        vMap.add(mapS1F);
        nodeInfo.setIdentical(vMap);
        exposedMedia.setIdentical(vMap);

        doc.write2File(sm_dirTestDataTemp+File.separator+"FrontBackIdentSetup.jdf",2,false);
        run2Seps("Sheet0", EnumSide.Front, null, null, 550, 100,"press0", EnumNodeStatus.Stopped); 
        doc.write2File(sm_dirTestDataTemp+File.separator+"FrontBackIdent_Front0.jdf",2,false);
        run2Seps("Sheet1", EnumSide.Back, null, null, 550, 100,"press1",EnumNodeStatus.Completed); 
        doc.write2File(sm_dirTestDataTemp+File.separator+"FrontBackIdent_Back1.jdf",2,false);
        run2Seps("Sheet1", EnumSide.Front, null, null, 1000, 150,"press0",EnumNodeStatus.Completed); 
        doc.write2File(sm_dirTestDataTemp+File.separator+"FrontBackIdent_Front1.jdf",2,false);
        run2Seps("Sheet0", EnumSide.Back, null, null, 1000, 150,"press1",EnumNodeStatus.Completed); 
        doc.write2File(sm_dirTestDataTemp+File.separator+"FrontBackIdent_Back0.jdf",2,false);
    }

    /**
     * test iteration using Identical in  NodeInfo
     */
    public void testFrontBackSimple()
    {
        JDFElement.setLongID(false);
        setup(false,1);

        doc.write2File(sm_dirTestDataTemp+File.separator+"FrontBackSetup.jdf",2,false);
        run2Seps("Sheet0", EnumSide.Front, null, null, 550, 100,"press0",EnumNodeStatus.Stopped); 
        doc.write2File(sm_dirTestDataTemp+File.separator+"FrontBack_Front0.jdf",2,false);
        run2Seps("Sheet0", EnumSide.Back, null, null, 550, 100,"press1",EnumNodeStatus.Stopped); 
        doc.write2File(sm_dirTestDataTemp+File.separator+"FrontBack_Back0.jdf",2,false);

        run2Seps("Sheet0", EnumSide.Front, null, null, 1000, 150,"press0",EnumNodeStatus.Completed); 
        final JDFAttributeMap attributeMapS0 = new JDFAttributeMap("SheetName","Sheet0");
        node.getLink(component, null).setAmountPoolAttribute("ActualAmount", String.valueOf(500), null, attributeMapS0);
        doc.write2File(sm_dirTestDataTemp+File.separator+"FrontBack_Front1.jdf",2,false);
        run2Seps("Sheet0", EnumSide.Back, null, null, 1000, 150,"press1",EnumNodeStatus.Completed); 
        node.getLink(component, null).setAmountPoolAttribute("ActualAmount", String.valueOf(1000), null, attributeMapS0);
        node.setPartStatus(attributeMapS0, EnumNodeStatus.Completed);
        doc.write2File(sm_dirTestDataTemp+File.separator+"FrontBack_Back1.jdf",2,false);
    }
    /**
     * test iteration using Identical in  NodeInfo
     */
    public void testNColor()
    {

        JDFElement.setLongID(false);
        setup(true,2);
        doc.write2File(sm_dirTestDataTemp+File.separator+"NColorSetup.jdf",2,false);

        run2Seps("Sheet0", EnumSide.Front, "Cyan", "Magenta",510,55,"press",EnumNodeStatus.Completed);
        JDFResourceLink rlMedia=node.getLink(media,null);
        final JDFAttributeMap sheetNameMap0 = new JDFAttributeMap(EnumPartIDKey.SheetName.getName(),"Sheet0");
        final JDFAttributeMap sheetNameMap1 = new JDFAttributeMap(EnumPartIDKey.SheetName.getName(),"Sheet1");
        rlMedia.setActualAmount(560,sheetNameMap0);
        doc.write2File(sm_dirTestDataTemp+File.separator+"NColor_S0_F_CM.jdf",2,false);

        run2Seps("Sheet0", EnumSide.Back, "Cyan", "Magenta",450,60,"press",EnumNodeStatus.Completed);
        doc.write2File(sm_dirTestDataTemp+File.separator+"NColor_S0_B_CM.jdf",2,false);

        run2Seps("Sheet1", EnumSide.Front, "Cyan", "Magenta",500,55,"press",EnumNodeStatus.Completed);
        rlMedia.setActualAmount(560,sheetNameMap1);
        doc.write2File(sm_dirTestDataTemp+File.separator+"NColor_S1_F_CM.jdf",2,false);

        run2Seps("Sheet1", EnumSide.Back, "Cyan", "Magenta",450,50,"press",EnumNodeStatus.Completed);
        doc.write2File(sm_dirTestDataTemp+File.separator+"NColor_S1_B_CM.jdf",2,false);

        run2Seps("Sheet0", EnumSide.Front, "Black", "Yellow",400,50,"press",EnumNodeStatus.Completed);
        doc.write2File(sm_dirTestDataTemp+File.separator+"NColor_S0_F_KY.jdf",2,false);

        run2Seps("Sheet0", EnumSide.Back, "Black", "Yellow",350,50,"press",EnumNodeStatus.Completed);
        // now the sheet is actually available
        component.getPartition(sheetNameMap0,null).setResStatus(EnumResStatus.Available,true);
        rlMedia=node.getLink(component,null);
        rlMedia.setActualAmount(350,sheetNameMap0);
        doc.write2File(sm_dirTestDataTemp+File.separator+"NColor_S0_B_KY.jdf",2,false);

        run2Seps("Sheet1", EnumSide.Front, "Black", "Yellow",400,50,"press",EnumNodeStatus.Completed);
        doc.write2File(sm_dirTestDataTemp+File.separator+"NColor_S1_F_KY.jdf",2,false);

        run2Seps("Sheet1", EnumSide.Back, "Black", "Yellow",360,40,"press",EnumNodeStatus.Completed);
        component.getPartition(sheetNameMap1,null).setResStatus(EnumResStatus.Available,true);
        rlMedia=node.getLink(component,null);
        rlMedia.setActualAmount(360,sheetNameMap1);
        doc.write2File(sm_dirTestDataTemp+File.separator+"NColor_S1_B_KY.jdf",2,false);
    }

    ///////////////////////////////////////////////////////////////////

    /**
     * simulate running 2 separations on one press
     */
    private void run2Seps(String sheet, EnumSide side, String sep1, String sep2, int good, int waste, String deviceID, EnumNodeStatus endStatus)
    {
        String jmfFile=sm_dirTestDataTemp+File.separator+"NColorStatus";
        JDFAttributeMap[] map=new JDFAttributeMap[sep1==null ? 1 : 2];
        map[0]=new JDFAttributeMap(EnumPartIDKey.SheetName,sheet);
        map[0].put(EnumPartIDKey.Side,side);
       jmfFile+=sheet+"_"+side.getName();
       boolean bFirst=!sheetsDone.contains(sheet);
       if(bFirst)
           sheetsDone.add(sheet);
        if(sep1!=null)
        {

            map[1]=new JDFAttributeMap(map[0]);
            map[0].put(EnumPartIDKey.Separation,sep1);
            map[1].put(EnumPartIDKey.Separation,sep2);
            jmfFile+="_"+sep1+"_"+sep2;
        }
        VJDFAttributeMap vMap=new VJDFAttributeMap(map);  

        if(sep1!=null)
            nodeInfo.setIdentical(vMap);

        JDFResourceLink rlComp=node.getLink(component,null);
        VElement vRL=new VElement();
        vRL.add(rlComp);
        JDFResourceLink rlMedia=null;
        if(bFirst)
        {
            rlMedia=node.getLink(media, null);
            vRL.add(rlMedia);
        }
        StatusCounter su=new StatusCounter(node,vMap,vRL);
        su.setTrackWaste(rlComp.getrRef(), true);
        
        su.setDeviceID(deviceID);
        if(bFirst)
        {
            su.setTrackWaste(rlMedia.getrRef(), true);
        }
        su.setPhase(EnumNodeStatus.InProgress,"Good",EnumDeviceStatus.Running,null);
        su.addPhase(rlComp.getrRef(),good, waste);
        if(rlMedia!=null)
        {
            su.addPhase(rlMedia.getrRef(),good, waste);                   
        }
        su.setPhase(endStatus,"dummy",EnumDeviceStatus.Idle,null);
        JDFDoc jmfStatus=su.getDocJMFPhaseTime();
        jmfStatus.write2File(jmfFile+"_status.jmf", 2,false);
        JDFDoc jmfRes=su.getDocJMFResource();
        jmfRes.write2File(jmfFile+"_resource.jmf", 2,false);
    }

    ///////////////////////////////////////////////////////////////////

    /**
     * create 2 sheets with 2 surfaces of 4 seps each
     */
    private void setup(boolean bnColorNI, int nSheet) 
    {
        sheetsDone=new VString();
        doc = new JDFDoc("JDF");
        node = doc.getJDFRoot();
        node.setType(EnumType.ConventionalPrinting);
        nodeInfo = node.appendNodeInfo();
        nodeInfo.setResStatus(EnumResStatus.Available,true);

        component = (JDFComponent) node.addResource(ElementName.COMPONENT, null, EnumUsage.Output, null, null, null, null);
        component.setResStatus(EnumResStatus.Unavailable,true);
        JDFResourceLink rl=node.getLink(component,null);
        final JDFAttributeMap sheetNameMap = new JDFAttributeMap(EnumPartIDKey.SheetName.getName(),"Sheet0");
        for(int i=0;i<nSheet;i++)
        {
            sheetNameMap.put(EnumPartIDKey.SheetName.getName(),"Sheet"+i);
            rl.setAmount(500,sheetNameMap);
        }


        media = (JDFMedia) node.addResource(ElementName.MEDIA, null, EnumUsage.Input, null, null, null, null);
        media.setMediaType(EnumMediaType.Paper);
        media.setResStatus(EnumResStatus.Available,true);
        rl=node.getLink(media,null);
        for(int i=0;i<nSheet;i++)
        {
            sheetNameMap.put(EnumPartIDKey.SheetName.getName(),"Sheet"+i);
            rl.setMaxAmount(600,sheetNameMap);
        }

        JDFMedia mediaPlate=(JDFMedia) node.addResource(ElementName.MEDIA, null, null, null, null, null, null);
        mediaPlate.setMediaType(EnumMediaType.Plate);
        exposedMedia = (JDFExposedMedia) node.addResource(ElementName.EXPOSEDMEDIA, null, EnumUsage.Input, null, null, null, null);
        exposedMedia.refElement(mediaPlate);
        exposedMedia.setResStatus(EnumResStatus.Available,true);

        for(int sheet = 0;sheet<nSheet;sheet ++)
        {
            String sheetName="Sheet"+sheet;
            JDFComponent c1=(JDFComponent) component.addPartition(EnumPartIDKey.SheetName,sheetName);
            c1.setDescriptiveName("Component for sheet "+sheet);
            JDFNodeInfo n1=(JDFNodeInfo) nodeInfo.addPartition(EnumPartIDKey.SheetName,sheetName);
            JDFExposedMedia x1=(JDFExposedMedia) exposedMedia.addPartition(EnumPartIDKey.SheetName,sheetName);

            for(int surface = 0;surface<2;surface ++)
            {
                EnumSide side=EnumSide.getEnum(surface);
                JDFNodeInfo n2=(JDFNodeInfo) n1.addPartition(EnumPartIDKey.Side,side.getName());
                JDFExposedMedia x2=(JDFExposedMedia) x1.addPartition(EnumPartIDKey.Side,side.getName());

                VString seps=new VString("Cyan Magenta Yellow Black"," ");
                for(int sep = 0;sep<seps.size();sep ++)
                {
                    String separation=seps.stringAt(sep);
                    if(bnColorNI)
                    {
                        JDFNodeInfo n3=(JDFNodeInfo) n2.addPartition(EnumPartIDKey.Separation,separation);
                        n3.setNodeStatus(EnumNodeStatus.Waiting);
                    }
                    else
                    {
                        n2.setNodeStatus(EnumNodeStatus.Waiting);
                    }
                    x2.addPartition(EnumPartIDKey.Separation,separation);

                }
            }           
        }
    }


    ///////////////////////////////////////////////////////////////////

    ///////////////////////////////////////////////////////////////////

}
