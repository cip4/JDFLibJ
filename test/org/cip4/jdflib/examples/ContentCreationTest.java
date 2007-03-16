/*
 * JDFExampleDocTest.java
 * 
 * @author muchadie
 */
package org.cip4.jdflib.examples;

import java.io.File;

import org.cip4.jdflib.auto.JDFAutoDeviceInfo.EnumDeviceStatus;
import org.cip4.jdflib.auto.JDFAutoLayoutElement.EnumElementType;
import org.cip4.jdflib.auto.JDFAutoPosition.EnumOrientation;
import org.cip4.jdflib.auto.JDFAutoStripMark.EnumMarkSide;
import org.cip4.jdflib.core.ElementName;
import org.cip4.jdflib.core.JDFComment;
import org.cip4.jdflib.core.JDFDoc;
import org.cip4.jdflib.core.JDFElement;
import org.cip4.jdflib.core.KElement;
import org.cip4.jdflib.core.JDFElement.EnumNodeStatus;
import org.cip4.jdflib.core.JDFResourceLink.EnumUsage;
import org.cip4.jdflib.datatypes.JDFRectangle;
import org.cip4.jdflib.datatypes.JDFXYPair;
import org.cip4.jdflib.node.JDFNode.EnumProcessUsage;
import org.cip4.jdflib.node.JDFNode.EnumType;
import org.cip4.jdflib.resource.JDFStrippingParams;
import org.cip4.jdflib.resource.JDFResource.EnumPartIDKey;
import org.cip4.jdflib.resource.devicecapability.JDFActionPool;
import org.cip4.jdflib.resource.process.JDFBinderySignature;
import org.cip4.jdflib.resource.process.JDFLayoutElement;
import org.cip4.jdflib.resource.process.JDFLayoutElementPart;
import org.cip4.jdflib.resource.process.JDFLayoutElementProductionParams;
import org.cip4.jdflib.resource.process.JDFMedia;
import org.cip4.jdflib.resource.process.JDFPosition;
import org.cip4.jdflib.resource.process.JDFRunList;
import org.cip4.jdflib.resource.process.JDFStripMark;


public class ContentCreationTest extends PreflightTest
{
    /**
     * test iteration
     * @return
     */
    public void testLayoutElementPositioning() throws Exception
    {
        // TBD: Fuzzy, Sizes, literal text via comments
        JDFElement.setLongID(false);
        JDFDoc d=new JDFDoc("JDF");
        n=d.getJDFRoot();
        n.setType(EnumType.LayoutElementProduction);

        JDFRunList outRun=(JDFRunList)n.appendMatchingResource(ElementName.RUNLIST,EnumProcessUsage.AnyOutput,null);
        outRun.setFileURL("output.pdf");

        JDFLayoutElementProductionParams lep=(JDFLayoutElementProductionParams) n.appendMatchingResource(ElementName.LAYOUTELEMENTPRODUCTIONPARAMS,EnumProcessUsage.AnyInput,null);
        lep.appendXMLComment("This is a \"well placed\" CTM defined mark\nThe anchor defines the 0,0 point to be transformed\nThe element to be placed is referenced by LayoutElement/FileSpec/URL");
        JDFLayoutElementPart lePart=lep.appendLayoutElementPart();
        lePart.setAttribute("PageRange", "0");
        KElement dynamicObj=lePart.appendElement("PositionObject");
        dynamicObj.setAttribute("CTM", "1 0 0 1 0 0");
        dynamicObj.setAttribute("Anchor", "LowLeft");
        final JDFLayoutElement bkg = (JDFLayoutElement)lePart.appendElement("LayoutElement");
        bkg.setMimeURL("bkg.pdf");

        lep.appendXMLComment("This is a \"roughly placed\" reservation in the middle of the page");
        lePart=lep.appendLayoutElementPart();
        lePart.setAttribute("PageRange", "0");
        dynamicObj=lePart.appendElement("PositionObject");
        dynamicObj.setAttribute("Position", "0.5 0.5");
        dynamicObj.setAttribute("Anchor", "CenterCenter");
        String id=lePart.appendAnchor(null);

        JDFLayoutElement image = (JDFLayoutElement)lePart.appendElement("LayoutElement");
        image.setElementType(EnumElementType.Image);
        image.appendComment().setText("Please add an image of a palm tree on a beach here!");

        lep.appendXMLComment("This is a \"roughly placed\" reservation 36 points below the previous image;\n NextPosition points from Anchor on this to NextAnchor on next,\n i.e. a positive vector specifies that next is shifted in the positive direction in the parent (in this case page) coordinate system");
        lePart=lep.appendLayoutElementPart();
        lePart.setAttribute("PageRange", "0");
        dynamicObj=lePart.appendElement("PositionObject");
        dynamicObj.setAttribute("Anchor", "TopCenter");
        dynamicObj.setAttribute("NextAnchor", "BottomCenter");
        dynamicObj.setAttribute("NextPosition", "0 36");
        dynamicObj.setAttribute("NextRef",id);

        image = (JDFLayoutElement)lePart.appendElement("LayoutElement");
        image.setElementType(EnumElementType.Image);
        image.appendComment().setText("Please add an image of a beach ball below the palm tree!");



        lep.appendXMLComment("This is a \"well placed\" CTM defined mark\nThe anchor defines the 0,0 point to be transformed");
        lePart=lep.appendLayoutElementPart();
        lePart.setAttribute("PageRange", "0");
        dynamicObj=lePart.appendElement("PositionObject");
        dynamicObj.setAttribute("CTM", "1 0 0 1 2 3");
        dynamicObj.setAttribute("Anchor", "LowLeft");
        lePart.appendBarcodeProductionParams().appendXMLComment("barcode details here");

        lePart=lep.appendLayoutElementPart();
        lePart.setAttribute("PageRange", "0");
        dynamicObj=lePart.appendElement("PositionObject");
        dynamicObj.setAttribute("Position", "1.0 1.0");
        dynamicObj.setAttribute("Anchor", "TopRight");
        dynamicObj.appendXMLComment("This is a \"roughly placed\"  mark\nThe anchor at top right is placed at the right (=1.0) top(=1.0) position of the page.\nNo rotation is specified");
        lePart.appendBarcodeProductionParams().appendXMLComment("barcode details here");

        lep.appendXMLComment("This is a \"roughly placed\"  container for marks\nThe anchor at top left is defined in the !Unrotated! orientation.\n It is placed at the left (=0.0) bottom(=0.0) position of the page.\nThe text flows bottom to top (=Rotate 90 = counterclockwise)\n do we need margins?");
        lePart=lep.appendLayoutElementPart();
        String idParent=lePart.appendAnchor(null);
        lePart.setAttribute("PageRange", "1");
        dynamicObj=lePart.appendElement("PositionObject");
        dynamicObj.setAttribute("Position", "0.0 0.0");
        dynamicObj.setAttribute("Anchor", "TopLeft");
        dynamicObj.setAttribute("Orientation", "Rotate90");

        lep.appendXMLComment("This is a  barcode inside the previous container\nThe anchor at bottom left is defined in the !Unrotated! orientation.\n It is placed at the left (=0.0) bottom(=0.0) position of the container.");
        lePart=lep.appendLayoutElementPart();
        id=lePart.appendAnchor(null);
        dynamicObj=lePart.appendElement("PositionObject");
        dynamicObj.setAttribute("Position", "0.0 0.0");
        dynamicObj.setAttribute("Anchor", "BottomLeft");
        dynamicObj.setAttribute("ParentRef", idParent);
        lePart.appendBarcodeProductionParams().appendXMLComment("barcode details here");

        lep.appendXMLComment("This is a disclaimer text inside the previous container\nThe anchor at top left is defined in the !Unrotated! orientation.\n The barcode and text are justified with their top margins and spaced by 72 points\n which corresponds to the left of the page because the container is rotated 90°");
        lePart=lep.appendLayoutElementPart();
        dynamicObj=lePart.appendElement("PositionObject");
        dynamicObj.setAttribute("NextAnchor", "TopRight");
        dynamicObj.setAttribute("NextPosition", "-72 0");
        dynamicObj.setAttribute("NextRef",id);
        dynamicObj.setAttribute("Anchor", "TopLeft");
        dynamicObj.setAttribute("ParentRef", idParent);
        JDFLayoutElement text = (JDFLayoutElement)lePart.appendElement("LayoutElement");
        text.setElementType(EnumElementType.Text);
        text.setMimeURL("file://myServer/disclaimers/de/aspirin.txt");


        lep.appendXMLComment("This is a \"VERY roughly placed\" piece of text somewhere on pages 2-3");
        lePart=lep.appendLayoutElementPart();
        lePart.setAttribute("PageRange", "1 ~ 2");
        text = (JDFLayoutElement)lePart.appendElement("LayoutElement");
        text.setElementType(EnumElementType.Text);
        text.appendComment().setText("Please add some text about the image of a palm tree on a beach here!");

        d.write2File(sm_dirTestDataTemp+File.separator+"LayoutDynamicObj.jdf",2,false);
    }

    /**
     * test preflight concepts in layoutelementproduction
     * @throws Exception
     */
    public void testStripMarks() throws Exception
    {
        //TODO relativeSize AbsoluteSize NextPosition
        JDFElement.setLongID(false);
        JDFDoc d=new JDFDoc("JDF");
        n=d.getJDFRoot();
        n.setType(EnumType.Stripping);
        n.getCreateResourcePool().appendXMLComment("StrippingParams with one sheet for simplicity");
        JDFStrippingParams stripParams=(JDFStrippingParams) n.addResource(ElementName.STRIPPINGPARAMS, null, EnumUsage.Input, null, null, null, null);
        JDFMedia media=stripParams.appendMedia();
        media.setDimensionCM(new JDFXYPair(100,70));

        JDFStrippingParams spS1=(JDFStrippingParams) stripParams.addPartition(EnumPartIDKey.SheetName, "Sheet_1");
        spS1.appendXMLComment("The following mark is on the press sheet (see new Attribute: MarkContext)\n@Anchor defines the cs origin of the mark, @NextAnchor defines the cs origin of the container, in this case the sheet\nThus the top center of the mark is rotated by 90° and placed exactly on (Position=0 0) the center right of the sheet\nNote that position is defined in absolute coordinates so that it alligns with Margin\nI propose deprecating StripMark/Position because the box paradigm does not fit well with resizeable marks.");
        {
            JDFStripMark sm1=spS1.appendStripMark();
            sm1.setAttribute("MarkContext","Sheet");
            sm1.setMarkName("ColorControlStrip");
            sm1.setMarkSide(EnumMarkSide.TwoSidedIndependent);
            sm1.setAttribute("Orientation", "Rotate90");
            sm1.setAttribute("Anchor", "TopCenter");
            sm1.setAttribute("NextAnchor", "CenterRight");
            sm1.setAttribute("Position", "0 0");
            sm1.appendElement(ElementName.COLORCONTROLSTRIP).appendXMLComment("The various explicit mark elements should be allowed here for their associated metatdata");
        }

        JDFStrippingParams spBS1=(JDFStrippingParams) spS1.addPartition(EnumPartIDKey.BinderySignatureName, "BS_1");
        JDFBinderySignature bs1=spBS1.appendBinderySignature();
        bs1.setNumberUp(new JDFXYPair(2,4));
        JDFPosition posBS1_1=spBS1.appendPosition();
        posBS1_1.setRelativeBox(new JDFRectangle(0,0,0.5,1));
        spBS1.appendXMLComment("The following describes a back to back mark on the folding signature (see new Attribute: MarkContext)\n@Anchor defines the cs origin of the mark, @NextAnchor defines the cs origin of the container, in this case the bindery signature.\nThus the center of the cutmark is positioned 5 pts left(-5) and 5 pts up(+5) from the bottom right of the bindery Signature");
        {
            JDFStripMark sm1_1=spBS1.appendStripMark();
            sm1_1.setAttribute("MarkContext","BinderySignature");
            sm1_1.setMarkName("CutMark");
            sm1_1.setMarkSide(EnumMarkSide.TwoSidedBackToBack);
            sm1_1.setAttribute("Orientation", "Rotate0");
            sm1_1.setAttribute("Anchor", "CenterCenter");
            sm1_1.setAttribute("NextAnchor", "BottomRight");
            sm1_1.setAttribute("Position", "-5 5");
            sm1_1.appendElement(ElementName.CUTMARK).appendXMLComment("The various explicit mark elements should be allowed here for their associated metatdata");
        } 
// TODO page cs vs. cell cs
        spBS1.appendXMLComment("The following describes a 4 back marks, one on each pair of Strip Cells (page) (see new Attribute: MarkContext)\n@Anchor defines the cs origin of the mark, @NextAnchor defines the cs origin of the container, in this case the spine of a pair of Page cells.\nThus the center of the bar code is positioned 0 pts right and 5 point up from the bottom spine of the cell page.\n Position is applied prior to rotating the mark.");
        {
            JDFStripMark sm1_2=spBS1.appendStripMark();
            sm1_2.setAttribute("MarkContext","StripCellPair");
            sm1_2.setMarkName("IdentificationField");
            sm1_2.setMarkSide(EnumMarkSide.Back);
            sm1_2.setAttribute("Orientation", "Rotate90");
            sm1_2.setAttribute("Anchor", "CenterLeft");
            sm1_2.setAttribute("NextAnchor", "BottomSpine");
            sm1_2.setAttribute("Position", "5 0");
            sm1_2.appendElement(ElementName.IDENTIFICATIONFIELD).appendXMLComment("The various explicit mark elements should be allowed here for their associated metatdata");
        } 
        
        spBS1.appendXMLComment("The following describes a back mark on each of the 8 Strip Cells (page) (see new Attribute: MarkContext)\n@Anchor defines the cs origin of the mark, @NextAnchor defines the cs origin of the container, in this case the bottom center of a Page cell.\nThus the center of the bar code is positioned 0 pts right and 5 point up from the bottom cell page.");
        {
            JDFStripMark sm1_3=spBS1.appendStripMark();
            sm1_3.setAttribute("MarkContext","StripCell");
            sm1_3.setMarkName("IdentificationField");
            sm1_3.setMarkSide(EnumMarkSide.Back);
            sm1_3.setAttribute("Orientation", "Rotate0");
            sm1_3.setAttribute("Anchor", "BottomCenter");
            sm1_3.setAttribute("NextAnchor", "BottomCenter");
            sm1_3.setAttribute("Position", "0 5");
            sm1_3.setAttribute("Size", "20 10");
            sm1_3.appendElement(ElementName.IDENTIFICATIONFIELD).appendXMLComment("The various explicit mark elements should be allowed here for their associated metatdata");
        } 

        JDFStrippingParams spBS2=(JDFStrippingParams) spS1.addPartition(EnumPartIDKey.BinderySignatureName, "BS_2");
        JDFBinderySignature bs2=spBS2.appendBinderySignature();
        JDFPosition posBS2_1=spBS2.appendPosition();
        posBS2_1.setRelativeBox(new JDFRectangle(0.5,0,1,0.5));
        posBS2_1.setOrientation(EnumOrientation.Rotate270);

        JDFPosition posBS2_2=spBS2.appendPosition();
        posBS2_2.setRelativeBox(new JDFRectangle(0.5,0.5,1,1));
        posBS2_2.setOrientation(EnumOrientation.Flip90);

        spBS2.appendXMLComment("The following describes single sided barcode on the two folding signatures (see new Attribute: MarkContext)\n@Anchor defines the cs origin of the mark, @NextAnchor defines the cs origin of the container, in this case the 2 bindery signatures.\nThus the top left of the barcode is positioned 3 pts right(+3) and 3 pts below(-3) from the top left of the front side of the bindery Signature.\nSince ther are two position elements, this results in two marks:\nPosition one is rotated by 90 degrees so that the barcode remains on the front of the sheet. Due to the Position/@Rotation, the mark is also rotated by 90° on the press sheet\n Position 2 is also flipped so that the barcode eventually ends up on the back of the press sheet.");
        {
            JDFStripMark sm2_1=spBS2.appendStripMark();
            sm2_1.setAttribute("MarkContext","BinderySignature");
            sm2_1.setMarkName("IdentificationField");
            sm2_1.setMarkSide(EnumMarkSide.Front);
            sm2_1.setAttribute("Orientation", "Rotate0");
            sm2_1.setAttribute("Anchor", "TopLeft");
            sm2_1.setAttribute("NextAnchor", "TopLeft");
            sm2_1.setAttribute("Position", "3 -3");
            sm2_1.appendElement(ElementName.IDENTIFICATIONFIELD).appendXMLComment("The various explicit mark elements should be allowed here for their associated metatdata");
        } 

        d.write2File(sm_dirTestDataTemp+File.separator+"StripMarks.jdf",2,false);

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
