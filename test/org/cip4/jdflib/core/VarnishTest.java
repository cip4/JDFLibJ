/*
 * JDFExampleDocTest.java
 * 
 * @author muchadie
 */
package org.cip4.jdflib.core;

import org.cip4.jdflib.auto.JDFAutoMedia.EnumMediaType;
import org.cip4.jdflib.core.JDFResourceLink.EnumUsage;
import org.cip4.jdflib.datatypes.JDFIntegerList;
import org.cip4.jdflib.node.JDFNode;
import org.cip4.jdflib.resource.JDFResource;
import org.cip4.jdflib.resource.JDFResource.EnumPartIDKey;
import org.cip4.jdflib.resource.JDFResource.EnumResourceClass;
import org.cip4.jdflib.resource.process.JDFColorantControl;
import org.cip4.jdflib.resource.process.JDFComponent;
import org.cip4.jdflib.resource.process.JDFConventionalPrintingParams;
import org.cip4.jdflib.resource.process.JDFExposedMedia;
import org.cip4.jdflib.resource.process.JDFMedia;
import org.cip4.jdflib.resource.process.prepress.JDFInk;


public class VarnishTest extends PreflightTest
{
    /**
     * test iteration
     * @return
     */
    public void testCombinedVarnish() throws Exception
    {
        JDFElement.setLongID(false);
        JDFDoc d=new JDFDoc("JDF");
        n=d.getJDFRoot();
        VString vCombiNodes=new VString("ConventionalPrinting Varnishing"," ");
        VString vSeparations=new VString("PreVarnish Cyan Magenta Yellow Black Varnish Varnish2"," ");
        n.setCombined(vCombiNodes);
        
        JDFConventionalPrintingParams cpp=(JDFConventionalPrintingParams)n.addResource(ElementName.CONVENTIONALPRINTINGPARAMS, null, EnumUsage.Input, null, null, null, null);
        JDFComponent comp=(JDFComponent)n.appendMatchingResource("Component",JDFNode.EnumProcessUsage.AnyOutput,null);
        JDFExposedMedia xm=(JDFExposedMedia)n.appendMatchingResource("ExposedMedia",JDFNode.EnumProcessUsage.Plate,null);
        JDFNodeInfo ni=n.appendNodeInfo();
        JDFMedia media=xm.appendMedia();
        media.setMediaType(EnumMediaType.Plate);
        JDFInk ink=(JDFInk)n.appendMatchingResource("Ink",JDFNode.EnumProcessUsage.AnyInput,null);

        JDFResource vp=n.addResource("VarnishingParams", EnumResourceClass.Parameter, EnumUsage.Input, null, null, null, null);
        JDFExposedMedia xmVarnish=(JDFExposedMedia)n.addResource("ExposedMedia", null, EnumUsage.Input, null, null, null, null);
        JDFMedia mediaVarnish=xmVarnish.appendMedia();
        mediaVarnish.setAttribute("MediaType", "Sleeve");
        
        JDFResourceLink rl=n.getLink(xmVarnish, null);
        JDFColorantControl cc=(JDFColorantControl)n.appendMatchingResource(ElementName.COLORANTCONTROL,JDFNode.EnumProcessUsage.AnyInput,null);
        cc.appendDeviceColorantOrder().setSeparations(vSeparations);
        rl.setCombinedProcessIndex(new JDFIntegerList("1"));
        
        for(int i=0;i<vSeparations.size();i++)
        {
            String sep=vSeparations.stringAt(i);
            ink.addPartition(EnumPartIDKey.Separation, sep);
            if(!sep.equals("PreVarnish")&&!sep.equals("Varnish2"))
            {
                xm.addPartition(EnumPartIDKey.Separation, sep);
            }
            else if(sep.equals("Varnish2"))
            {
                xmVarnish.addPartition(EnumPartIDKey.Separation, sep);
            }
            else if(sep.equals("PreVarnish"))
            {
                vp.setXPathAttribute("FullVarnish/SeparationSpec/@Name", sep);
            }
        }
       
        d.write2File(sm_dirTestDataTemp+"varnishing.jdf", 2, true);
    }
}
