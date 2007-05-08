/*
 * JDFExampleDocTest.java
 * 
 * @author muchadie
 */
package org.cip4.jdflib.examples;

import org.cip4.jdflib.JDFTestCaseBase;
import org.cip4.jdflib.auto.JDFAutoMedia.EnumMediaType;
import org.cip4.jdflib.core.ElementName;
import org.cip4.jdflib.core.JDFDoc;
import org.cip4.jdflib.core.JDFElement;
import org.cip4.jdflib.core.JDFResourceLink;
import org.cip4.jdflib.core.VString;
import org.cip4.jdflib.core.JDFResourceLink.EnumUsage;
import org.cip4.jdflib.datatypes.JDFIntegerList;
import org.cip4.jdflib.datatypes.JDFIntegerRangeList;
import org.cip4.jdflib.node.JDFNode;
import org.cip4.jdflib.resource.JDFResource;
import org.cip4.jdflib.resource.JDFResource.EnumPartIDKey;
import org.cip4.jdflib.resource.JDFResource.EnumResourceClass;
import org.cip4.jdflib.resource.process.JDFColorantControl;
import org.cip4.jdflib.resource.process.JDFConventionalPrintingParams;
import org.cip4.jdflib.resource.process.JDFExposedMedia;
import org.cip4.jdflib.resource.process.JDFMedia;
import org.cip4.jdflib.resource.process.prepress.JDFInk;


public class VarnishTest extends JDFTestCaseBase
{
    /**
     * test iteration
     * @return
     */
    public void testCombinedVarnish() throws Exception
    {
        JDFElement.setLongID(false);
        JDFDoc d=new JDFDoc("JDF");
        JDFNode n=d.getJDFRoot();
        VString vCombiNodes=new VString("ConventionalPrinting Varnishing"," ");
        VString vSeparations=new VString("Cyan Magenta Yellow Black Varnish"," ");
        n.setCombined(vCombiNodes);
        
        JDFConventionalPrintingParams cpp=(JDFConventionalPrintingParams)n.addResource(ElementName.CONVENTIONALPRINTINGPARAMS, null, EnumUsage.Input, null, null, null, null);
        cpp.setModuleAvailableIndex(new JDFIntegerRangeList("1 ~ 6"));
        cpp.setModuleIndex(new JDFIntegerRangeList("1 ~ 4 6"));
        cpp.appendXMLComment("Module 0 and 7 are varnishing modules, 1-4 are process colors and 6 is the ink module used to varnish", null);
        n.appendMatchingResource("Component",JDFNode.EnumProcessUsage.AnyOutput,null);
        JDFExposedMedia xm=(JDFExposedMedia)n.appendMatchingResource("ExposedMedia",JDFNode.EnumProcessUsage.Plate,null);
        n.appendNodeInfo();
        JDFMedia media=xm.appendMedia();
        media.setMediaType(EnumMediaType.Plate);
        JDFInk ink=(JDFInk)n.appendMatchingResource("Ink",JDFNode.EnumProcessUsage.AnyInput,null);

        JDFResource vp=n.addResource("VarnishingParams", EnumResourceClass.Parameter, EnumUsage.Input, null, null, null, null);
        JDFExposedMedia xmVarnish=(JDFExposedMedia)n.addResource("ExposedMedia", null, EnumUsage.Input, null, null, null, null);
        JDFMedia mediaVarnish=xmVarnish.appendMedia();
        mediaVarnish.setAttribute("MediaType", "Sleeve");
        
        JDFResourceLink rl=n.getLink(xmVarnish, null);
        JDFColorantControl cc=(JDFColorantControl)n.appendMatchingResource(ElementName.COLORANTCONTROL,JDFNode.EnumProcessUsage.AnyInput,null);
        cc.getCreateDeviceColorantOrder().appendXMLComment("Should the VarnishingParams seps be excluded, as is shown here?", null);
        cc.getCreateDeviceColorantOrder().setSeparations(vSeparations);
        
        rl.setCombinedProcessIndex(new JDFIntegerList("1"));
        vSeparations.addAll(new VString("PreVarnish Varnish2"," "));
        
        for(int i=0;i<vSeparations.size();i++)
        {
            String sep=vSeparations.stringAt(i);
            ink.addPartition(EnumPartIDKey.Separation, sep);
            if(!sep.equals("PreVarnish")&&!sep.equals("Varnish2"))
            {
                xm.addPartition(EnumPartIDKey.Separation, sep);
            }
            if(sep.equals("Varnish2"))
            {
                vp.appendXMLComment("full varnishing in a varnishing module with or wihtout a sleeve. Full varnishing means to cover the complete media surface.", null);
                xmVarnish.addPartition(EnumPartIDKey.Separation, sep);
                JDFResource varnishPart=vp.addPartition(EnumPartIDKey.Separation, sep);
                varnishPart.setAttribute("ModuleIndex", "7");
                varnishPart.setAttribute("VarnishMethod", "Sleeve");
                varnishPart.setAttribute("VarnishArea", "Spot");
            }
            else if(sep.equals("Varnish"))
            {
                vp.appendXMLComment("varnishing in a printing module only  with a mandatory plate. The plate may be exposed or not, for example,  for full varnihing. ", null);
                //xmVarnish.addPartition(EnumPartIDKey.Separation, sep);
                JDFResource varnishPart=vp.addPartition(EnumPartIDKey.Separation, sep);
                varnishPart.setAttribute("ModuleIndex", "6");
                varnishPart.setAttribute("VarnishMethod", "Plate");
                varnishPart.setAttribute("VarnishArea", "Full");
            }
            else if(sep.equals("PreVarnish"))
            {
                vp.appendXMLComment("varnishing in a varnishing module only with a mandatory prepared sleeve ", null);
                xmVarnish.addPartition(EnumPartIDKey.Separation, sep);
                JDFResource varnishPart=vp.addPartition(EnumPartIDKey.Separation, sep);
                varnishPart.setAttribute("ModuleIndex", "0");
                varnishPart.setAttribute("VarnishMethod", "Sleeve");
                varnishPart.setAttribute("VarnishArea", "Full");
            }
        }
       
        d.write2File(sm_dirTestDataTemp+"varnishing.jdf", 2, true);
    }
}
