/*
 * JDFExampleDocTest.java
 * 
 * @author muchadie
 */
package org.cip4.jdflib.core;

import java.io.File;

import org.cip4.jdflib.JDFTestCaseBase;
import org.cip4.jdflib.node.JDFNode;
import org.cip4.jdflib.node.JDFNode.EnumProcessUsage;
import org.cip4.jdflib.node.JDFNode.EnumType;
import org.cip4.jdflib.resource.JDFResource.EnumResStatus;
import org.cip4.jdflib.resource.process.JDFByteMap;
import org.cip4.jdflib.resource.process.JDFExposedMedia;
import org.cip4.jdflib.resource.process.JDFRunList;


public class WebTest extends JDFTestCaseBase
{
    private JDFNode node;
    private JDFNodeInfo nodeInfo;
    private JDFDoc doc;

    /**
     * test iteration using Identical in  NodeInfo
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
