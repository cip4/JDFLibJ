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

import org.cip4.jdflib.auto.JDFAutoComponent.EnumComponentType;
import org.cip4.jdflib.auto.JDFAutoConventionalPrintingParams.EnumPrintingType;
import org.cip4.jdflib.auto.JDFAutoConventionalPrintingParams.EnumWorkStyle;
import org.cip4.jdflib.auto.JDFAutoDeviceInfo.EnumDeviceStatus;
import org.cip4.jdflib.auto.JDFAutoPreview.EnumPreviewFileType;
import org.cip4.jdflib.auto.JDFAutoPreview.EnumPreviewUsage;
import org.cip4.jdflib.core.AttributeName;
import org.cip4.jdflib.core.ElementName;
import org.cip4.jdflib.core.JDFAudit;
import org.cip4.jdflib.core.JDFNodeInfo;
import org.cip4.jdflib.core.JDFResourceLink;
import org.cip4.jdflib.core.VElement;
import org.cip4.jdflib.core.VString;
import org.cip4.jdflib.core.JDFElement.EnumNodeStatus;
import org.cip4.jdflib.core.JDFElement.EnumVersion;
import org.cip4.jdflib.core.JDFResourceLink.EnumUsage;
import org.cip4.jdflib.datatypes.JDFAttributeMap;
import org.cip4.jdflib.datatypes.VJDFAttributeMap;
import org.cip4.jdflib.node.JDFNode;
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
import org.cip4.jdflib.resource.process.JDFMedia;
import org.cip4.jdflib.resource.process.JDFPreview;
import org.cip4.jdflib.resource.process.prepress.JDFInk;
import org.cip4.jdflib.util.JDFDate;
import org.cip4.jdflib.util.UrlUtil;

/**
 * @author Rainer Prosi
 * class that generates golden tickets based on ICS levels etc
 */
public class MISCPGoldenTicket extends MISGoldenTicket
{
    /**
     * 
     */
    public static final String MISCPS_PRINTING = "MISCPS.Printing";
    private boolean grayBox;
    public VString inks=null;
    public VString inkProductIDs=null;

    protected int icsLevel;
    public boolean previewAvailable=false;
    public int sheetAmount=1000;

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
        catMap.put(MISCPS_PRINTING, new VString("InkZoneCalculation ConventionalPrinting",null));
        if(grayBox)
            setCategory(MISCPS_PRINTING);
        partIDKeys = new VString("SignatureName,SheetName,Side,Separation",",");
        vParts=vPartMap;
        icsLevel=_icsLevel; 
        theStatusCounter.addIgnorePart(EnumPartIDKey.Side);
        theStatusCounter.addIgnorePart(EnumPartIDKey.Separation);
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
        if(!grayBox)
        {
            theNode.setType(EnumType.ConventionalPrinting);
        }
        initColorantControl();
        initConventionalPrintingParams();
        JDFMedia m=initPaperMedia();
        initPlateXM(EnumUsage.Input);
        initDevice(thePreviousNode);
        JDFComponent c=initOutputComponent();
        initInk();
        super.init();
        initPreview();
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
        amountLinks=null;
        if(bFirst)
            addAmountLink("Media:Input");
        addAmountLink("Component:Output");
        super.setActivePart(vp, bFirst);
    }


    /**
     * @param icsLevel
     */
    @Override
    protected JDFMedia initPaperMedia()
    {
        JDFMedia m=super.initPaperMedia();
        theNode.linkResource(m, EnumUsage.Input, null);
        return m;
    }




    protected void initInk()
    {
        if(inks==null)
            return;
        JDFInk ink=(JDFInk) theNode.getCreateResource(ElementName.INK, EnumUsage.Input, 0);
        int ncols = getNCols();
        for(int j=0;j<ncols;j++)
        {
            JDFInk inkp=(JDFInk) ink.addPartition(EnumPartIDKey.Separation, cols.stringAt(j));
            inkp.setInkName(inks.elementAt(j));
            if((cols.get(j).toLowerCase().indexOf("varnish")>=0) || (inks.get(j).toLowerCase().indexOf("varnish")>=0))
                inkp.setFamily("Varnish");
            if(inkProductIDs!=null)
                inkp.setProductID(inkProductIDs.get(j));
        }
    }


    /**
     * @param icsLevel
     */
    protected JDFComponent initOutputComponent()
    {
        if(thePreviousNode!=null)
        {
            final JDFResource parentOutComp = thePreviousNode.getResource(ElementName.COMPONENT, EnumUsage.Output, 0);
            if(parentOutComp!=null)
            {
                theNode.linkResource(parentOutComp,EnumUsage.Input,null);
            }
        }
        JDFComponent outComp=(JDFComponent) theNode.getCreateResource(ElementName.COMPONENT, EnumUsage.Output, 0);
        outComp.setComponentType(EnumComponentType.FinalProduct,EnumComponentType.Sheet);
        outComp.setProductType("Unknown");

        JDFResourceLink rl=theNode.getLink(outComp, EnumUsage.Output);
        if(vParts!=null)
        {
            VJDFAttributeMap reducedMap = getReducedMap(new VString("Side Separation"," "));
            final int size = reducedMap==null ? 0 : reducedMap.size();
            for(int i=0;i<size;i++)
            {
                final JDFAttributeMap part = reducedMap.elementAt(i);
                JDFResource partComp=outComp.getCreatePartition(part, partIDKeys);
                partComp.setDescriptiveName("Description for Component part# "+i);
                JDFAttributeMap newMap=new JDFAttributeMap(part);
                newMap.put(AttributeName.CONDITION, "Good");
                rl.setAmount(sheetAmount, newMap);
                newMap.put(AttributeName.CONDITION, "Waste");
                rl.setAmount(sheetAmount, newMap);
                rl.setMaxAmount(sheetAmount*0.1, newMap);
            }
        }
        else
        {
            outComp.setDescriptiveName("MIS-CP output Component");
        }
        outComp.appendLayout();
        JDFMedia inMedia=(JDFMedia) theNode.getResource(ElementName.MEDIA, EnumUsage.Input, 0);
        outComp.setDimensions(inMedia.getDimension());
        return outComp;

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
                        pvp.setURL("http://example.com/?"+part.toString()+".png");
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

    @Override
    protected JDFDevice initDevice(JDFNode reuseNode)
    {
        if(misICSLevel<2)
            return null;
        super.initDevice(reuseNode);

        VJDFAttributeMap reducedMap = getReducedMap(new VString("Side Separation"," "));

        JDFDevice dev = (JDFDevice) theNode.getCreateResource(ElementName.DEVICE, EnumUsage.Input, 0);
        final int size = reducedMap==null ? 0 : reducedMap.size();
        for(int i=0;i<size;i++)
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
    protected void makeReadyColorantControl()
    {
        JDFColorantControl cc=(JDFColorantControl) theExpandedNode.getCreateResource(ElementName.COLORANTCONTROL,EnumUsage.Input, 0);
        JDFColorPool cp=cc.getCreateColorPool();
        for(int i=0;i<getNCols();i++)
        {
            String name=cols.stringAt(i);
            JDFColor c=cp.getCreateColorWithName(name, null);
            c.setActualColorName(colsActual.stringAt(i));
        }
    } 

    /**
     * prepare an mis fuzzy node and make it runnable by the device
     *
     */
    public void makeReady()
    {
        super.makeReady();

        JDFPreview pv=(JDFPreview) theNode.getResource(ElementName.PREVIEW,EnumUsage.Input, 0);
        VElement v=pv.getLeaves(false);
        for(int i=0;i<v.size();i++)
        {
            final JDFPreview pvp = (JDFPreview)v.elementAt(i);
            pvp.setURL(UrlUtil.fileToUrl(new File("\\\\Share\\Dir\\Preview_"+pvp.getSheetName()+"_"+pvp.getSide().getName()+"_"+pvp.getSeparation()+".png"), false));
        }
        makeReadyColorantControl();

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
        runSinglePhase(good, waste);
        finalize(); // prior to processRun
        theStatusCounter.setPhase(EnumNodeStatus.Completed, "Done", EnumDeviceStatus.Idle, "Waiting");
    }

    @Override
    public void assign(JDFNode node)
    {

        super.assign(node);
        theNode.getCreateNodeInfo().setPartIDKeys(partIDKeys);
    }

    /**
     * zapp any direct links to colorpool
     */
    @Override
    protected void initColorantControl()
    {
        super.initColorantControl();
        JDFColorPool cp=(JDFColorPool) theNode.getResource(ElementName.COLORPOOL, EnumUsage.Input, 0);
        if(cp!=null)
        {
            JDFResourceLink rl=theNode.getLink(cp, EnumUsage.Input);
            if(rl!=null)
                rl.deleteNode();
        }       
    }
}
