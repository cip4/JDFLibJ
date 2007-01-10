/*
 * JDFExampleDocTest.java
 * 
 * @author muchadie
 */
package org.cip4.jdflib.core;

import java.io.File;

import org.cip4.jdflib.auto.JDFAutoDeviceInfo.EnumDeviceStatus;
import org.cip4.jdflib.core.JDFElement.EnumNodeStatus;
import org.cip4.jdflib.node.JDFNode.EnumProcessUsage;
import org.cip4.jdflib.node.JDFNode.EnumType;
import org.cip4.jdflib.resource.devicecapability.JDFActionPool;
import org.cip4.jdflib.resource.process.JDFLayoutElementProductionParams;
import org.cip4.jdflib.resource.process.JDFRunList;


public class ContentCreationTest extends PreflightTest
{
    /**
     * test iteration
     * @return
     */
    public void testLayoutPreflight() throws Exception
    {
        JDFElement.setLongID(false);
        JDFDoc d=new JDFDoc("JDF");
        n=d.getJDFRoot();
        n.setType(EnumType.LayoutElementProduction);
        
        JDFRunList outRun=(JDFRunList)n.appendMatchingResource(ElementName.RUNLIST,EnumProcessUsage.AnyOutput,null);
        outRun.setFileURL("output.pdf");
        
        JDFLayoutElementProductionParams lep=(JDFLayoutElementProductionParams) n.appendMatchingResource(ElementName.LAYOUTELEMENTPRODUCTIONPARAMS,EnumProcessUsage.AnyInput,null);
        JDFComment com=lep.appendComment();
        com.setName("Instruction");
        com.setText("Add any human readable instructions here");
        
        // new
        aPool=(JDFActionPool) lep.appendElement(ElementName.ACTIONPOOL);
        
        // now some simple tests...
        appendNumPagesAction();
        appendSeparationAction();
        appendBWSeparationAction();
        appendTrimBoxAction();
        appendResolutionAction();
        
        n.setPhase(EnumNodeStatus.InProgress, "Creative Work",  EnumDeviceStatus.Running, null, null);
        Thread.sleep(1000);
        n.setPhase(EnumNodeStatus.InProgress, "Creative Work",  EnumDeviceStatus.Running, null, null);
        Thread.sleep(1000);
        n.setPhase(EnumNodeStatus.Completed, "done",  EnumDeviceStatus.Idle, null, null);
        d.write2File(sm_dirTestDataTemp+File.separator+"LayoutPreflight.jdf",2,false);
        
        
    }
}
