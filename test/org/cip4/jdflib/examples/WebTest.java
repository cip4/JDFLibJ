/*
 * JDFExampleDocTest.java
 * 
 * @author muchadie
 */
package org.cip4.jdflib.examples;

import java.io.File;

import org.cip4.jdflib.JDFTestCaseBase;
import org.cip4.jdflib.auto.JDFAutoPart.EnumSide;
import org.cip4.jdflib.core.ElementName;
import org.cip4.jdflib.core.JDFDoc;
import org.cip4.jdflib.core.JDFElement;
import org.cip4.jdflib.core.JDFNodeInfo;
import org.cip4.jdflib.core.JDFResourceLink;
import org.cip4.jdflib.core.KElement;
import org.cip4.jdflib.core.VString;
import org.cip4.jdflib.core.JDFResourceLink.EnumUsage;
import org.cip4.jdflib.datatypes.JDFMatrix;
import org.cip4.jdflib.datatypes.JDFXYPair;
import org.cip4.jdflib.node.JDFNode;
import org.cip4.jdflib.node.JDFNode.EnumProcessUsage;
import org.cip4.jdflib.node.JDFNode.EnumType;
import org.cip4.jdflib.pool.JDFResourcePool;
import org.cip4.jdflib.resource.JDFMarkObject;
import org.cip4.jdflib.resource.JDFResource;
import org.cip4.jdflib.resource.JDFResource.EnumPartIDKey;
import org.cip4.jdflib.resource.JDFResource.EnumResStatus;
import org.cip4.jdflib.resource.JDFResource.EnumResourceClass;
import org.cip4.jdflib.resource.process.JDFByteMap;
import org.cip4.jdflib.resource.process.JDFContentObject;
import org.cip4.jdflib.resource.process.JDFExposedMedia;
import org.cip4.jdflib.resource.process.JDFLayout;
import org.cip4.jdflib.resource.process.JDFRunList;


public class WebTest extends JDFTestCaseBase
{
    private JDFNode node;
    private JDFNodeInfo nodeInfo;
    private JDFDoc doc;

    /**
     * test WebGrowth Compensation
     */
    public void testWebGrowthCompensation()
    {

        JDFElement.setLongID(false);
        for(int k=1;k<3;k++)
        {
        doc = new JDFDoc("JDF");
        JDFNode n=doc.getJDFRoot();
        final JDFResourcePool rp = n.getCreateResourcePool();
        rp.appendXMLComment(
                "We actually have a problem if we separate LayoutShift and Layout.\n"+
                "Ord is not a unique Reference, because you MAY have multiple placed objects at completely different locations on the spread, e.g. in a multi-up binderysignature environment.\n"+
                "Therefore I suggest referencing PlacedObject/@OrdID instead.");
        JDFResource lo=n.addResource("Layout", EnumResourceClass.Parameter, EnumUsage.Input, null, null, null, null);
        JDFLayout losh=(JDFLayout) lo.addPartition(EnumPartIDKey.SheetName, "Sheet1");
        JDFLayout lofr=(JDFLayout) losh.addPartition(EnumPartIDKey.Side, EnumSide.Front.getName());
        
        rp.appendXMLComment("LayoutShift MAY but NEED Not be partitioned, although at least Side will make sense\n");
        
        JDFResource los=n.addResource("LayoutShift", EnumResourceClass.Parameter, EnumUsage.Input, null, null, null, null);
        if(k==1)
            los=los.addPartition(EnumPartIDKey.SheetName, "Sheet1");
        
        if(k==1)
            los.appendXMLComment("Note that the shiftObject MAY reference multiple OrdIDs for brevity");
        else
            los.appendXMLComment("Note that the interpolation algorithm between positions is implementation dependent\n"
                    +"Another option would be to specify boxes of validity, but that allows inconsistent overlap");
        
        los=los.addPartition(EnumPartIDKey.Side, "Front");
        
        VString vSep=new VString("Cyan Magenta Yellow Black"," ");
        
        for(int i=0;i<16;i++)
        {
            int x=720*(i%4);
            int y=1000*(i/4);
            int ord=i%8;
            JDFContentObject co=lofr.appendContentObject();
            co.setOrd(ord);
            co.setOrdID(i);
            co.setCTM(new JDFMatrix(1,0,0,1,x,y));
            JDFMarkObject mo=lofr.appendMarkObject();
            mo.setOrd(ord);
            mo.setOrdID(i+100);
            mo.setCTM(new JDFMatrix(1,0,0,1,x+700,y+900));

            KElement shiftObject=los.appendElement("ShiftObject");
            if(k==1)
            {
                if(i%2==0)
                    shiftObject.setAttribute("OrdIDRefs",""+i+" "+(i+100)+" "+(i+1)+" "+(i+101),null);
                else
                    shiftObject.deleteNode();
            }
            else
            {
                if(i%2==0)
                    shiftObject.setAttribute("Position", new JDFXYPair(x+360,y+500).toString());
                else
                    shiftObject.deleteNode();
            }
            if(i%2==0)
            for(int j=0;j<vSep.size();j++)
            {
                shiftObject.appendElement("SeparationShift").setAttribute("CTM", new JDFMatrix(1,0,0,1,j+i/4,j+i%4).toString());
            }
       }
        doc.write2File(sm_dirTestDataTemp+"Webgrowth"+k+".jdf", 2, false);
        }
    }    
    
    /**
     * test direct imaging
     */
    public void testDirectImage()
    {

        JDFElement.setLongID(false);
        doc = new JDFDoc("JDF");
        node = doc.getJDFRoot();
        node.setType(EnumType.Combined);
        VString vTypes=new VString("ImageSetting ConventionalPrinting"," ");
        node.setTypes(vTypes);
        nodeInfo = node.appendNodeInfo();
        nodeInfo.setResStatus(EnumResStatus.Available,true);
        JDFRunList rl=(JDFRunList) node.appendMatchingResource(ElementName.RUNLIST, EnumProcessUsage.AnyInput, null);
        JDFByteMap bm= rl.appendByteMap();
        bm.appendFileSpec().setURL("File:///foo.tif");
        JDFExposedMedia xm=(JDFExposedMedia) node.appendMatchingResource(ElementName.EXPOSEDMEDIA, EnumProcessUsage.Plate, null);
        xm.setDescriptiveName("Real Plate");
        xm.appendMedia();
        JDFExposedMedia xmCyl=(JDFExposedMedia) node.appendMatchingResource(ElementName.EXPOSEDMEDIA, EnumProcessUsage.Cylinder, null);
        xmCyl.setDescriptiveName("Optional cylinder");
        JDFResourceLink rlCylOut=node.linkMatchingResource(xmCyl, EnumProcessUsage.AnyOutput, null);
        assertEquals("2 for cylinder one for plate",node.getResourceLinkPool().numChildElements("ExposedMediaLink", null), 3);
        doc.write2File(sm_dirTestDataTemp+File.separator+"webDirect.jdf", 2,false);
        

    }


    ///////////////////////////////////////////////////////////////////

    ///////////////////////////////////////////////////////////////////

}
