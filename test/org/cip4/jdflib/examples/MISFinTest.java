/*
 * JDFExampleDocTest.java
 * 
 * @author muchadie
 */
package org.cip4.jdflib.examples;

import java.io.File;

import org.cip4.jdflib.JDFTestCaseBase;
import org.cip4.jdflib.auto.JDFAutoBundle.EnumBundleType;
import org.cip4.jdflib.core.ElementName;
import org.cip4.jdflib.core.JDFDoc;
import org.cip4.jdflib.core.JDFElement;
import org.cip4.jdflib.core.JDFResourceLink;
import org.cip4.jdflib.core.VString;
import org.cip4.jdflib.core.JDFResourceLink.EnumUsage;
import org.cip4.jdflib.node.JDFNode;
import org.cip4.jdflib.node.JDFNode.EnumType;
import org.cip4.jdflib.resource.JDFBundle;
import org.cip4.jdflib.resource.JDFBundleItem;
import org.cip4.jdflib.resource.JDFResource.EnumPartIDKey;
import org.cip4.jdflib.resource.JDFResource.EnumResStatus;
import org.cip4.jdflib.resource.process.JDFComponent;


public class MISFinTest extends JDFTestCaseBase
{
    /**
     * test MIS to Finishing ICS
     * @return
     */
    public void testAmount()
    {
        JDFElement.setLongID(false);
        JDFDoc d=new JDFDoc("JDF");
        JDFNode n=d.getJDFRoot();
        n.setType(EnumType.ProcessGroup);
        n.setTypes(new VString("Binding Stacking BoxPacking Palletizing"," "));
        JDFComponent comp=(JDFComponent) n.addResource(ElementName.COMPONENT, null, EnumUsage.Output, null, null, null, null);
        JDFResourceLink rl=n.getLink(comp,null);
        rl.setAmount(2,null);
        rl.setDescriptiveName("The link point to 2 pallets with a total comtent of 10000 brochures in 200 boxes");
        
        // create a pallet partition - may also use the root
        JDFComponent compPallet=(JDFComponent) comp.addPartition(EnumPartIDKey.DeliveryUnit0,"Pallet");
        
        JDFComponent compBox=(JDFComponent) compPallet.addPartition(EnumPartIDKey.DeliveryUnit1,"Box");
        JDFBundle bundlePallet=compPallet.appendBundle();
        bundlePallet.setDescriptiveName("Bundle describing 100 boxes with 5000 Brochures Contents total");
        bundlePallet.setTotalAmount(5000);
        bundlePallet.setBundleType(EnumBundleType.Pallet);
        JDFBundleItem biBoxes=bundlePallet.appendBundleItem();
        biBoxes.refElement(compBox);
        biBoxes.setAmount(100);
        
        JDFComponent compBrochure=(JDFComponent) compBox.addPartition(EnumPartIDKey.DeliveryUnit2,"Brochure");
        JDFBundle bundleBox=compBox.appendBundle();
        bundleBox.setDescriptiveName("Bundle describing 1 boxes with 50 Brochures Contents per box");
        bundleBox.setTotalAmount(50);
        bundleBox.setBundleType(EnumBundleType.Box);
        
        JDFBundleItem biBrochures=bundleBox.appendBundleItem();
        biBrochures.refElement(compBrochure);
        biBrochures.setAmount(50);
        
        JDFBundle bundleBrochure=compBrochure.appendBundle();
        bundleBrochure.setDescriptiveName("Dummy Bundle to inhibit inheritence of the box Bundle");
        d.write2File(sm_dirTestDataTemp+File.separator+"MISFinAmount.jdf",2,false);
    }
    
    /////////////////////////////////////////////////////////////////////////
    
    public void testAmountPalletteManifest()
    {
        JDFElement.setLongID(false);
        JDFDoc d=new JDFDoc("JDF");
        JDFNode n=d.getJDFRoot();
        n.setType(EnumType.ProcessGroup);
        n.setTypes(new VString("Binding Stacking BoxPacking Palletizing"," "));
        JDFComponent comp=(JDFComponent) n.addResource(ElementName.COMPONENT, null, EnumUsage.Output, null, null, null, null);
        JDFResourceLink rl=n.getLink(comp,null);
        rl.setAmount(2,null);
        rl.setDescriptiveName("The link point to 2 pallets with a total comtent of 10000 brochures in 200 boxes");
        JDFComponent compBrochure=(JDFComponent) n.addResource(ElementName.COMPONENT, null, EnumUsage.Input, null, null, null, null);
        compBrochure.setResStatus(EnumResStatus.Available,true);
        compBrochure.setDescriptiveName("The individual Brochures");
        JDFResourceLink rlB=n.getLink(compBrochure,null);
        rlB.setAmount(10000,null);
        rlB.setActualAmount(9700,null);
        
        for(int i=0;i<2;i++)
        {
            // create a pallet partition - may also use the root
            JDFComponent compPallet=(JDFComponent) comp.addPartition(EnumPartIDKey.DeliveryUnit0,"Pallet"+i);
            compPallet.setProductID("Pallet_"+i);
            
            JDFBundle bundlePallet=compPallet.getCreateBundle();
            int nBox=i==0 ? 100 : 94 ;
            bundlePallet.setDescriptiveName("Pallet Bundle describing "+nBox+" boxes with "+nBox*50+" Brochures Contents total");
            bundlePallet.setTotalAmount(nBox*50);
            bundlePallet.setBundleType(EnumBundleType.Pallet);
            JDFBundleItem biBoxes=bundlePallet.appendBundleItem();

            JDFComponent compBox=(JDFComponent) compPallet.addPartition(EnumPartIDKey.DeliveryUnit1,"Box");
            biBoxes.refElement(compBox);
            biBoxes.setAmount(nBox);
            
            JDFBundle bundleBox=compBox.appendBundle();
            bundleBox.setDescriptiveName("Bundle describing 1 boxes with 50 Brochures Contents per box");
            bundleBox.setTotalAmount(50);
            bundleBox.setBundleType(EnumBundleType.Box);
            
            JDFBundleItem biBrochures=bundleBox.appendBundleItem();
            biBrochures.refElement(compBrochure);
            biBrochures.setAmount(50);
        }
        
        d.write2File(sm_dirTestDataTemp+File.separator+"MISFinAmountManifest.jdf",2,false);
    }
    
    /////////////////////////////////////////////////////////////////////////
   /////////////////////////////////////////////////////////////////////////
    
    public void testAmountPalletteCompleteManifest()
    {
        JDFElement.setLongID(false);
        JDFDoc d=new JDFDoc("JDF");
        JDFNode n=d.getJDFRoot();
        n.setType(EnumType.ProcessGroup);
        n.setTypes(new VString("Binding Stacking BoxPacking Palletizing"," "));
        JDFComponent comp=(JDFComponent) n.addResource(ElementName.COMPONENT, null, EnumUsage.Output, null, null, null, null);
        JDFResourceLink rl=n.getLink(comp,null);
        rl.setAmount(2,null);
        rl.setDescriptiveName("The link point to 2 pallets with a total comtent of 10000 brochures in 200 boxes");
        JDFComponent compBrochure=(JDFComponent) n.addResource(ElementName.COMPONENT, null, EnumUsage.Input, null, null, null, null);
        compBrochure.setResStatus(EnumResStatus.Available,true);
        compBrochure.setDescriptiveName("The individual Brochures");
        JDFResourceLink rlB=n.getLink(compBrochure,null);
        rlB.setAmount(10000,null);
        rlB.setActualAmount(9700,null);
        
        for(int i=0;i<2;i++)
        {
            // create a pallet partition - may also use the root
            JDFComponent compPallet=(JDFComponent) comp.addPartition(EnumPartIDKey.DeliveryUnit0,"Pallet"+i);
            compPallet.setProductID("Pallet_"+i);
            
            JDFBundle bundlePallet=compPallet.getCreateBundle();
            int nBox=i==0 ? 100 : 94 ;
            bundlePallet.setDescriptiveName("Pallet Bundle describing "+nBox+" boxes with "+nBox*50+" Brochures Contents total");
            bundlePallet.setTotalAmount(nBox*50);
            bundlePallet.setBundleType(EnumBundleType.Pallet);

            for(int j=0;j<nBox;j++)
            {
                JDFBundleItem biBoxes=bundlePallet.appendBundleItem();
                JDFComponent compBox=(JDFComponent) compPallet.addPartition(EnumPartIDKey.DeliveryUnit1,"Box_"+i+"_"+j);
                compBox.setProductID("Box_"+i+"_"+j);
                biBoxes.refElement(compBox);
                biBoxes.setAmount(1);
                
                JDFBundle bundleBox=compBox.appendBundle();
                bundleBox.setDescriptiveName("Bundle describing box #"+j+" with 50 Brochures Contents per box");
                bundleBox.setTotalAmount(50);
                bundleBox.setBundleType(EnumBundleType.Box);
                
                JDFBundleItem biBrochures=bundleBox.appendBundleItem();
                biBrochures.refElement(compBrochure);
                biBrochures.setAmount(50);
            }
        }
        
        d.write2File(sm_dirTestDataTemp+File.separator+"MISFinAmountCompleteManifest.jdf",2,false);
    }
    
    /////////////////////////////////////////////////////////////////////////
}
