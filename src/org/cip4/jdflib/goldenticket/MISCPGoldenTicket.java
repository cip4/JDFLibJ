/*
 *
 * The CIP4 Software License, Version 1.0
 *
 *
 * Copyright (c) 2001-2008 The International Cooperation for the Integration of 
 * Processes in  Prepress, Press and Postpress (CIP4).  All rights 
 * reserved.
 *
 * Redistribution and use in source and binary forms, with or without
 * modification, are permitted provided that the following conditions
 * are met:
 *
 * 1. Redistributions of source code must retain the above copyright
 *    notice, this list of conditions and the following disclaimer. 
 *
 * 2. Redistributions in binary form must reproduce the above copyright
 *    notice, this list of conditions and the following disclaimer in
 *    the documentation and/or other materials provided with the
 *    distribution.
 *
 * 3. The end-user documentation included with the redistribution,
 *    if any, must include the following acknowledgment:  
 *       "This product includes software developed by the
 *        The International Cooperation for the Integration of 
 *        Processes in  Prepress, Press and Postpress (www.cip4.org)"
 *    Alternately, this acknowledgment may appear in the software itself,
 *    if and wherever such third-party acknowledgments normally appear.
 *
 * 4. The names "CIP4" and "The International Cooperation for the Integration of 
 *    Processes in  Prepress, Press and Postpress" must
 *    not be used to endorse or promote products derived from this
 *    software without prior written permission. For written 
 *    permission, please contact info@cip4.org.
 *
 * 5. Products derived from this software may not be called "CIP4",
 *    nor may "CIP4" appear in their name, without prior written
 *    permission of the CIP4 organization
 *
 * Usage of this software in commercial products is subject to restrictions. For
 * details please consult info@cip4.org.
 *
 * THIS SOFTWARE IS PROVIDED ``AS IS'' AND ANY EXPRESSED OR IMPLIED
 * WARRANTIES, INCLUDING, BUT NOT LIMITED TO, THE IMPLIED WARRANTIES
 * OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE ARE
 * DISCLAIMED.  IN NO EVENT SHALL THE INTERNATIONAL COOPERATION FOR
 * THE INTEGRATION OF PROCESSES IN PREPRESS, PRESS AND POSTPRESS OR
 * ITS CONTRIBUTORS BE LIABLE FOR ANY DIRECT, INDIRECT, INCIDENTAL,
 * SPECIAL, EXEMPLARY, OR CONSEQUENTIAL DAMAGES (INCLUDING, BUT NOT
 * LIMITED TO, PROCUREMENT OF SUBSTITUTE GOODS OR SERVICES; LOSS OF
 * USE, DATA, OR PROFITS; OR BUSINESS INTERRUPTION) HOWEVER CAUSED AND
 * ON ANY THEORY OF LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY,
 * OR TORT (INCLUDING NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT
 * OF THE USE OF THIS SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY OF
 * SUCH DAMAGE.
 * ====================================================================
 *
 * This software consists of voluntary contributions made by many
 * individuals on behalf of the The International Cooperation for the Integration 
 * of Processes in Prepress, Press and Postpress and was
 * originally based on software 
 * copyright (c) 1999-2001, Heidelberger Druckmaschinen AG 
 * copyright (c) 1999-2001, Agfa-Gevaert N.V. 
 *  
 * For more information on The International Cooperation for the 
 * Integration of Processes in  Prepress, Press and Postpress , please see
 * <http://www.cip4.org/>.
 *  
 * 
 */
package org.cip4.jdflib.goldenticket;

import java.io.File;
import java.util.ResourceBundle;

import javax.swing.JOptionPane;

import org.cip4.jdflib.auto.JDFAutoComponent.EnumComponentType;
import org.cip4.jdflib.auto.JDFAutoConventionalPrintingParams.EnumPrintingType;
import org.cip4.jdflib.auto.JDFAutoConventionalPrintingParams.EnumWorkStyle;
import org.cip4.jdflib.auto.JDFAutoDeviceInfo.EnumDeviceStatus;
import org.cip4.jdflib.auto.JDFAutoMedia.EnumMediaType;
import org.cip4.jdflib.auto.JDFAutoPreview.EnumPreviewFileType;
import org.cip4.jdflib.auto.JDFAutoPreview.EnumPreviewUsage;
import org.cip4.jdflib.core.AttributeName;
import org.cip4.jdflib.core.ElementName;
import org.cip4.jdflib.core.JDFAudit;
import org.cip4.jdflib.core.JDFNodeInfo;
import org.cip4.jdflib.core.JDFResourceLink;
import org.cip4.jdflib.core.JDFSeparationList;
import org.cip4.jdflib.core.VElement;
import org.cip4.jdflib.core.VString;
import org.cip4.jdflib.core.JDFElement.EnumNodeStatus;
import org.cip4.jdflib.core.JDFElement.EnumVersion;
import org.cip4.jdflib.core.JDFResourceLink.EnumUsage;
import org.cip4.jdflib.datatypes.JDFAttributeMap;
import org.cip4.jdflib.datatypes.JDFCMYKColor;
import org.cip4.jdflib.datatypes.JDFXYPair;
import org.cip4.jdflib.datatypes.VJDFAttributeMap;
import org.cip4.jdflib.node.JDFNode;
import org.cip4.jdflib.node.JDFNode.EnumProcessUsage;
import org.cip4.jdflib.node.JDFNode.EnumType;
import org.cip4.jdflib.resource.JDFDevice;
import org.cip4.jdflib.resource.JDFResource;
import org.cip4.jdflib.resource.JDFResource.EnumPartIDKey;
import org.cip4.jdflib.resource.JDFResource.EnumPartUsage;
import org.cip4.jdflib.resource.JDFResource.EnumResStatus;
import org.cip4.jdflib.resource.intent.JDFColorIntent;
import org.cip4.jdflib.resource.process.JDFColor;
import org.cip4.jdflib.resource.process.JDFColorPool;
import org.cip4.jdflib.resource.process.JDFColorantControl;
import org.cip4.jdflib.resource.process.JDFComponent;
import org.cip4.jdflib.resource.process.JDFConventionalPrintingParams;
import org.cip4.jdflib.resource.process.JDFExposedMedia;
import org.cip4.jdflib.resource.process.JDFMedia;
import org.cip4.jdflib.resource.process.JDFPreview;
import org.cip4.jdflib.util.JDFDate;
import org.cip4.jdflib.util.UrlUtil;

/**
 * @author Rainer Prosi
 * class that generates golden tickets based on ICS levels etc
 */
public class MISCPGoldenTicket extends MISGoldenTicket
{
    private boolean grayBox;
    private VJDFAttributeMap vParts;
    public VString cols=new VString("Cyan,Magenta,Yellow,Black,Spot1,Spot2,Spot3,Spot4",",");
    public VString colsActual = new VString("Cyan,Magenta,Gelb,Schwarz,RIP 4711,RIP 4712,RIP 4713,RIP 4714",",");
    public int nCols=0;
    public EnumWorkStyle workStyle=EnumWorkStyle.Simplex;
    
    private final VString partIDKeys;
    protected int icsLevel;
    public boolean previewAvailable=false;
    public int sheetAmount=1000;
    public ResourceBundle m_littleBundle;
    
	public String[] l1 = { "1", "2" };
	public String[] l2 = { "1", "2", "3" };

    /**
     * create a BaseGoldenTicket
     * @param icsLevel the level to init to (1,2 or 3)
     * @param jdfVersion the version to generate a golden ticket for
     * @param jmfLevel level of jmf ICS to support
     * @param misLevel level of MIS ICS to support
     * @param isGrayBox if true, write a grayBox
     */
    public MISCPGoldenTicket(int _icsLevel, EnumVersion jdfVersion, int _jmfLevel, int _misLevel, boolean isGrayBox, VJDFAttributeMap vPartMap)
    {
        super(_misLevel,jdfVersion,_jmfLevel);
        
        grayBox=isGrayBox;
        partIDKeys = new VString("SignatureName,SheetName,Side,Separation",",");
        vParts=vPartMap;
        icsLevel=_icsLevel;        
    }
    
    /**
     * initializes this node to a given ICS version
     * @param icsLevel the level to init to (1,2 or 3)
     */
    public void init()
    {
        initColsFromParent();
        initAmountsFromParent();
        
        //put level methods?
        
        while(cols.size()>nCols && nCols>0)
            cols.remove(nCols);
     
        if(icsLevel<0)
            return;
        String icsTag="MISCPS_L"+icsLevel+"-"+theVersion.getName();
        theNode.appendAttribute(AttributeName.ICSVERSIONS, icsTag, null, " ", true);
        if(!theNode.hasAttribute(AttributeName.DESCRIPTIVENAME))
            theNode.setDescriptiveName("MISCPS Golden Ticket Example Job - version: "+JDFAudit.software());
        if(grayBox)
        {
            theNode.setType(EnumType.ProcessGroup);
            theNode.addTypes(EnumType.InkZoneCalculation);
            theNode.addTypes(EnumType.ConventionalPrinting);
        }
        else
        {
            theNode.setType(EnumType.ConventionalPrinting);
        }
        theNode.setCategory("MISCPS.Printing");
        initColorantControl();
        initConventionalPrintingParams();
        JDFMedia m=initPaperMedia();
        initPlate();
        initDevice();
        JDFComponent c=initOutputComponent();
        initPreview();
        super.init();
        setActivePart(vParts, true);
        theStatusCounter.setTrackWaste(c.getID(), true);
        theStatusCounter.setTrackWaste(m.getID(), true);
    }

    /**
     * recalculate ncols from parent color intent if it exists
     */
    private void initColsFromParent()
    {
        if(theParentNode==null)
            return;
        JDFColorIntent ci=(JDFColorIntent) theParentNode.getResource(ElementName.COLORINTENT, EnumUsage.Input, 0);
        if(ci==null)
            return;
        int c=ci.getNumColors();
        if(c>0)
            nCols=c;       
    }
    /**
     * recalculate ncols from parent color intent if it exists
     */
    private void initAmountsFromParent()
    {
        if(theParentNode==null)
            return;
        JDFComponent c=(JDFComponent) theParentNode.getResource(ElementName.COMPONENT, EnumUsage.Output, 0);
        JDFResourceLink cl=theParentNode.getLink(c, EnumUsage.Output);
        if(cl==null)
            return;
        double amount=cl.getAmount(null);
        if(amount>0)
            sheetAmount=(int) amount;
    }

    public void setActivePart(VJDFAttributeMap vp, boolean bFirst)
    {
        VElement vResLinks=new VElement();
        if(bFirst)
            vResLinks.add(theNode.getLink(theNode.getResource("Media", null, 0),null));
        vResLinks.add(theNode.getLink(theNode.getResource("Component", EnumUsage.Output, 0),null));
        theStatusCounter.setActiveNode(theNode, vp, vResLinks);
    }
     /**
     * simulate execution of this node
     * the internal node will be modified to reflect the excution
     */
   @Override
    public void execute(VJDFAttributeMap parts, boolean outputAvailable, boolean bFirst, int good, int waste)
    {

        parts=parts==null ? vParts : parts;
        setActivePart(parts, bFirst);
        super.execute(parts,outputAvailable,bFirst, good, waste);
    }
    /**
     * simulate execution of this node
     * the internal node will be modified to reflect the excution
     */
    public void schedule(VJDFAttributeMap nodesToCombine, int starthours, int durationhours)
    {
        theNode.setPartStatus(nodesToCombine, EnumNodeStatus.Waiting);
        JDFNodeInfo ni=theNode.getNodeInfo();
        ni=(JDFNodeInfo) ni.getPartition(nodesToCombine.elementAt(0), null);
        JDFDate d=new JDFDate();
        d.addOffset(0,0,starthours, 0);
        ni.setStart(d);
        d.addOffset(0,0,durationhours, 0);
        ni.setEnd(d);
    }
    
    /**
     * @param icsLevel
     */
    protected JDFMedia initPaperMedia()
    {
        if(thePreviousNode!=null)
            theNode.linkResource(thePreviousNode.getResource(ElementName.MEDIA, EnumUsage.Input, 0),EnumUsage.Input,null);

        JDFMedia m=(JDFMedia) theNode.getCreateResource(ElementName.MEDIA,EnumUsage.Input, 0);
        m.setDescriptiveName("the paper to print on");
        m.setResStatus(EnumResStatus.Unavailable, false);
        m.setMediaType(EnumMediaType.Paper);
        m.setDimensionCM(new JDFXYPair(70,102));
        m.setWeight(90);
        m.setThickness(90/0.8);
        VJDFAttributeMap reducedMap = getReducedMap(new VString("Side Separation"," "));
        int n=reducedMap.size();
        JDFResourceLink rl=theNode.getLink(m, EnumUsage.Input);
        rl.setAmount(n*1.1*sheetAmount, new JDFAttributeMap("Condition","Good"));
        rl.setMaxAmount(n*0.1*sheetAmount, new JDFAttributeMap("Condition","Waste"));
        return m;


    }
    /**
     * @param icsLevel
     */
    protected void initPlate()
    {
        if(thePreviousNode!=null)
            theNode.linkResource(thePreviousNode.getResource(ElementName.EXPOSEDMEDIA, EnumUsage.Input, 0),EnumUsage.Input,null);

        JDFExposedMedia xm=(JDFExposedMedia) theNode.getCreateResource(ElementName.EXPOSEDMEDIA,EnumUsage.Input, 0);
        xm.setPartUsage(EnumPartUsage.Explicit);
        JDFResourceLink rl=theNode.getLink(xm, null);
        rl.setProcessUsage(EnumProcessUsage.Plate);
        
        JDFMedia m= xm.getCreateMedia();
        xm.setResStatus(EnumResStatus.Unavailable, false);
        m.setResStatus(EnumResStatus.Available, false);
        m.makeRootResource(null, null, true);
        m.setDescriptiveName("the plates to use");
        m.setMediaType(EnumMediaType.Plate);
        m.setDimensionCM(new JDFXYPair(70,102));
        if(vParts!=null)
        {
            for(int i=0;i<vParts.size();i++)
            {
                JDFResource xmp=xm.getCreatePartition(vParts.elementAt(i), partIDKeys);
                int ncols = getNCols();

                for(int j=0;j<ncols;j++)
                    xmp.addPartition(EnumPartIDKey.Separation, cols.stringAt(j));
            }
        }
    }

    public int getNCols()
    {
        int ncols=nCols==0 ? cols.size() : nCols;
        return ncols;
    }

    /**
     * @param icsLevel
     */
    protected JDFComponent initOutputComponent()
    {
        if(theParentNode!=null)
        {
            final JDFResource parentOutComp = theParentNode.getResource(ElementName.COMPONENT, EnumUsage.Output, 0);
            if(parentOutComp!=null)
            {
                theNode.linkResource(parentOutComp,EnumUsage.Input,null);
            }
        }
        JDFComponent outComp=(JDFComponent) theNode.getCreateResource(ElementName.COMPONENT, EnumUsage.Output, 0);
        outComp.setComponentType(EnumComponentType.FinalProduct,EnumComponentType.Sheet);
        JDFResourceLink rl=theNode.getLink(outComp, EnumUsage.Output);
        if(vParts!=null)
        {
            VJDFAttributeMap reducedMap = getReducedMap(new VString("Side Separation"," "));
            for(int i=0;i<reducedMap.size();i++)
            {
                final JDFAttributeMap part = reducedMap.elementAt(i);
                outComp.getCreatePartition(part, partIDKeys);
                JDFAttributeMap newMap=new JDFAttributeMap(part);
                newMap.put(AttributeName.CONDITION, "Good");
                rl.setAmount(sheetAmount, newMap);
                rl.setMaxAmount(sheetAmount*1.1, newMap);
            }
        }
        outComp.appendLayout();
        JDFMedia inMedia=(JDFMedia) theNode.getResource(ElementName.MEDIA, EnumUsage.Input, 0);
        outComp.setDimensions(inMedia.getDimension());
        return outComp;

    }
    private VJDFAttributeMap getReducedMap(VString reduceKeys)
    {
        VJDFAttributeMap reducedMap=new VJDFAttributeMap(vParts);
        reducedMap.removeKeys(reduceKeys.getSet());
        return reducedMap;
    }
    /**
     * @param icsLevel
     */
    protected void initPreview()
    {
        if(theNode.getCombinedProcessIndex(EnumType.InkZoneCalculation, 0)<0)
            return;
        if(thePreviousNode!=null)
        {
            theNode.linkResource(thePreviousNode.getResource(ElementName.PREVIEW, EnumUsage.Output, 0),EnumUsage.Input,null);
        }

        JDFPreview pv=(JDFPreview) theNode.getCreateResource(ElementName.PREVIEW,EnumUsage.Input, 0);
        pv.setResStatus(EnumResStatus.Incomplete, false);
        pv.setPreviewUsage(EnumPreviewUsage.Separation);
        pv.setPartUsage(EnumPartUsage.Explicit);
        pv.setPreviewFileType(EnumPreviewFileType.PNG);

        if(vParts!=null)
        {
            for(int i=0;i<vParts.size();i++)
            {
                final JDFAttributeMap part = vParts.elementAt(i);
                JDFPreview pvp=(JDFPreview) pv.getCreatePartition(part, partIDKeys);
                for(int j=0;j<getNCols();j++)
                {
                    pvp.addPartition(EnumPartIDKey.Separation, cols.stringAt(j));
                    if(previewAvailable)
                    {
                        pvp.setResStatus(EnumResStatus.Available, false);
                        pvp.setURL("file://previewHost/previewDir/"+part.toString()+".png");
                    }
                    else
                    {
                        pvp.setResStatus(EnumResStatus.Incomplete, false);
                    }
                }
            }
        }
    }

    /**
     * @param icsLevel
     */
    protected void initConventionalPrintingParams()
    {
        JDFConventionalPrintingParams cpp=(JDFConventionalPrintingParams) theNode.getCreateResource(ElementName.CONVENTIONALPRINTINGPARAMS,EnumUsage.Input, 0);
        cpp.setPrintingType(EnumPrintingType.SheetFed);
        cpp.setWorkStyle(workStyle);
        cpp.setResStatus(EnumResStatus.Available, false);
    }
    
    protected JDFDevice initDevice()
    {
        if(misICSLevel<2)
            return null;
        super.initDevice();
        
        VJDFAttributeMap reducedMap = getReducedMap(new VString("Side Separation"," "));

        JDFDevice dev = (JDFDevice) theNode.getCreateResource(ElementName.DEVICE, EnumUsage.Input, 0);
        for(int i=0;i<reducedMap.size();i++)
        {
            final JDFAttributeMap part = reducedMap.elementAt(i);
            JDFDevice devPart=(JDFDevice) dev.getCreatePartition(part, partIDKeys);

            devPart.setResStatus(EnumResStatus.Available, false);
            devPart.setDeviceID("deviceID_"+i);
        }
        return dev;
    }

    /**
     * @param icsLevel
     */
    protected void initColorantControl()
    {
        if(thePreviousNode!=null)
            theNode.linkResource(thePreviousNode.getResource(ElementName.COLORANTCONTROL, EnumUsage.Input, 0),EnumUsage.Input,null);

        JDFColorantControl cc=(JDFColorantControl) theNode.getCreateResource(ElementName.COLORANTCONTROL,EnumUsage.Input, 0);
        JDFColorPool cp=cc.getCreateColorPool();
        cc.setResStatus(EnumResStatus.Available, false);

        cp.makeRootResource(null, null, true);
        JDFSeparationList co=cc.getCreateColorantOrder();
        co.setSeparations(cols);
        cc.setProcessColorModel("DeviceCMYK");
        for(int i=4;i<getNCols();i++)
            cc.getCreateColorantParams().appendSeparation(cols.stringAt(i));
        for(int i=0;i<getNCols();i++)
        {
            String name=cols.stringAt(i);
            JDFColor c=cp.getCreateColorWithName(name, null);
            c.setActualColorName(colsActual.stringAt(i));
            if(i==0)
                c.setCMYK(new JDFCMYKColor(1,0,0,0));
            if(i==1)
                c.setCMYK(new JDFCMYKColor(0,1,0,0));
            if(i==2)
                c.setCMYK(new JDFCMYKColor(0,0,1,0));
            if(i==3)
                c.setCMYK(new JDFCMYKColor(0,0,0,1));
        }

    } 

    /**
     * prepare an mis fuzzy node and make it runnable by the device
     *
     */
    public void makeReady()
    {
        JDFExposedMedia xm=(JDFExposedMedia) theNode.getResource(ElementName.EXPOSEDMEDIA,EnumUsage.Input, 0);
        VElement v=xm.getLeaves(false);
        for(int i=0;i<v.size();i++)
            ((JDFResource)v.elementAt(i)).setResStatus(EnumResStatus.Available,false);
        
        JDFPreview pv=(JDFPreview) theNode.getResource(ElementName.PREVIEW,EnumUsage.Input, 0);
        v=pv.getLeaves(false);
        for(int i=0;i<v.size();i++)
        {
            final JDFPreview pvp = (JDFPreview)v.elementAt(i);
            pvp.setResStatus(EnumResStatus.Available,false);
            pvp.setURL(UrlUtil.fileToUrl(new File("\\\\Share\\Dir\\Preview_"+pvp.getSheetName()+"_"+pvp.getSide().getName()+"_"+pvp.getSeparation()+".png"), false));
        }
    }
    /**
     * @param icsLevel
     * @return 
     */
    protected  JDFNodeInfo initNodeInfo()
    {
        JDFNodeInfo ni=super.initNodeInfo();
        if(vParts!=null)
        {
            VJDFAttributeMap reducedMap=new VJDFAttributeMap(vParts);
            VString reduceKeys=new VString(partIDKeys);
            // simplex and perfecting are one run only
            if(EnumWorkStyle.Simplex.equals(workStyle)||EnumWorkStyle.Perfecting.equals(workStyle))
                reduceKeys.remove(AttributeName.SIDE);
            reducedMap.reduceMap(reduceKeys.getSet());
            theNode.setPartStatus(reducedMap, EnumNodeStatus.Waiting);
            for(int i=0;i<reducedMap.size();i++)
            {
                final JDFAttributeMap part = reducedMap.elementAt(i);
                JDFNodeInfo niPart=(JDFNodeInfo) ni.getCreatePartition(part, partIDKeys);
                niPart.setDescriptiveName("Printing for"+part.toString());
            }
        }
        return ni;
    }

    @Override
    protected void runphases(int good, int waste)
    {
        theStatusCounter.setPhase(EnumNodeStatus.InProgress, "Good", EnumDeviceStatus.Running, "Printing");
        JDFResourceLink rl=theNode.getLink(0, "Media",new JDFAttributeMap("Usage","Input"),null);
        theStatusCounter.addPhase(rl.getrRef(), good, waste);
        rl=theNode.getLink(0, "Component",new JDFAttributeMap("Usage","Output"),null);
        theStatusCounter.addPhase(rl.getrRef(), good, waste);
        theStatusCounter.setPhase(EnumNodeStatus.Completed, "Done", EnumDeviceStatus.Idle, "Waiting");
    }

    @Override
    public void assign(JDFNode node)
    {

        super.assign(node);
        theNode.getCreateNodeInfo().setPartIDKeys(partIDKeys);
    }
    
}
