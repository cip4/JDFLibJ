/*
 * JDFExampleDocTest.java
 * 
 * @author muchadie
 */
package org.cip4.jdflib.core;

import java.io.File;

import org.cip4.jdflib.auto.JDFAutoDeviceInfo.EnumDeviceStatus;
import org.cip4.jdflib.auto.JDFAutoLayoutElement.EnumElementType;
import org.cip4.jdflib.core.JDFElement.EnumNodeStatus;
import org.cip4.jdflib.node.JDFNode.EnumProcessUsage;
import org.cip4.jdflib.node.JDFNode.EnumType;
import org.cip4.jdflib.resource.devicecapability.JDFActionPool;
import org.cip4.jdflib.resource.process.JDFLayoutElement;
import org.cip4.jdflib.resource.process.JDFLayoutElementPart;
import org.cip4.jdflib.resource.process.JDFLayoutElementProductionParams;
import org.cip4.jdflib.resource.process.JDFRunList;


public class ContentCreationTest extends PreflightTest
{
    /**
     * test iteration
     * @return
     */
    public void testLayoutElementPositioning() throws Exception
    {
        JDFElement.setLongID(false);
        JDFDoc d=new JDFDoc("JDF");
        n=d.getJDFRoot();
        n.setType(EnumType.LayoutElementProduction);

        JDFRunList outRun=(JDFRunList)n.appendMatchingResource(ElementName.RUNLIST,EnumProcessUsage.AnyOutput,null);
        outRun.setFileURL("output.pdf");

        JDFLayoutElementProductionParams lep=(JDFLayoutElementProductionParams) n.appendMatchingResource(ElementName.LAYOUTELEMENTPRODUCTIONPARAMS,EnumProcessUsage.AnyInput,null);
        JDFLayoutElementPart lePart=lep.appendLayoutElementPart();
        lePart.setAttribute("PageRange", "0");
        KElement dynamicObj=lePart.appendElement("DynamicObject");
        dynamicObj.setAttribute("CTM", "1 0 0 1 0 0");
        dynamicObj.setAttribute("Anchor", "LowLeft");
        dynamicObj.appendXMLComment("This is a \"well placed\" CTM defined mark\nThe anchor defines the 0,0 point to be transformed\nThe element to be placed is referenced by LayoutElement/FileSpec/URL");
        final JDFLayoutElement bkg = (JDFLayoutElement)lePart.appendElement("LayoutElement");
        bkg.setMimeURL("bkg.pdf");
        
        lePart=lep.appendLayoutElementPart();
        lePart.setAttribute("PageRange", "0");
        dynamicObj=lePart.appendElement("DynamicObject");
        dynamicObj.setAttribute("Position", "0.5 0.5");
        dynamicObj.setAttribute("Anchor", "CenterCenter");
        dynamicObj.appendXMLComment("This is a \"roughly placed\" reservation in the middle of the page");
        final JDFLayoutElement image = (JDFLayoutElement)lePart.appendElement("LayoutElement");
        image.setElementType(EnumElementType.Image);
        image.appendComment().setText("Please add an image of a palm tree on a beach here!");
        
        lePart=lep.appendLayoutElementPart();
        lePart.setAttribute("PageRange", "0");
        dynamicObj=lePart.appendElement("DynamicObject");
        dynamicObj.setAttribute("CTM", "1 0 0 1 2 3");
        dynamicObj.setAttribute("Anchor", "LowLeft");
        dynamicObj.appendXMLComment("This is a \"well placed\" CTM defined mark\nThe anchor defines the 0,0 point to be transformed");
        lePart.appendBarcodeProductionParams().appendXMLComment("barcode details here");
        
        lePart=lep.appendLayoutElementPart();
        lePart.setAttribute("PageRange", "0");
        dynamicObj=lePart.appendElement("DynamicObject");
        dynamicObj.setAttribute("Position", "1.0 1.0");
        dynamicObj.setAttribute("Anchor", "TopRight");
        dynamicObj.appendXMLComment("This is a \"roughly placed\"  mark\nThe anchor at top right is placed at the right (=1.0) top(=1.0) position of the page.\nNo rotation is specified");
        lePart.appendBarcodeProductionParams().appendXMLComment("barcode details here");
        
        lePart=lep.appendLayoutElementPart();
        lePart.setAttribute("PageRange", "1");
        dynamicObj=lePart.appendElement("DynamicObject");
        dynamicObj.setAttribute("Position", "0.0 0.0");
        dynamicObj.setAttribute("Anchor", "TopLeft");
        dynamicObj.setAttribute("Orientation", "Rotate90");
        dynamicObj.appendXMLComment("This is a \"roughly placed\"  mark\nThe anchor at top left is defined in the !Unrotated! orientation.\n It is placed at the left (=0.0) bottom(=0.0) position of the page.\nThe text flows bottom to top (=Rotate 90 = counterclockwise)\n do we need margins?");
        lePart.appendBarcodeProductionParams().appendXMLComment("barcode details here");

        lePart=lep.appendLayoutElementPart();
        lePart.setAttribute("PageRange", "1 ~ 2");
        final JDFLayoutElement text = (JDFLayoutElement)lePart.appendElement("LayoutElement");
        lePart.appendXMLComment("This is a \"VERY roughly placed\" piece of text somewhere on pages 2-3");
        text.setElementType(EnumElementType.Text);
        text.appendComment().setText("Please add some text about the image of a palm tree on a beach here!");
        
        d.write2File(sm_dirTestDataTemp+File.separator+"LayoutDynamicObj.jdf",2,false);
     }

    /**
     * test preflight concepts in layoutelementproduction
     * @throws Exception
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
