/*
 *
 * The CIP4 Software License, Version 1.0
 *
 *
 * Copyright (c) 2001-2007 The International Cooperation for the Integration of 
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

import org.cip4.jdflib.auto.JDFAutoComponent.EnumComponentType;
import org.cip4.jdflib.auto.JDFAutoConventionalPrintingParams.EnumPrintingType;
import org.cip4.jdflib.auto.JDFAutoConventionalPrintingParams.EnumWorkStyle;
import org.cip4.jdflib.auto.JDFAutoMedia.EnumMediaType;
import org.cip4.jdflib.auto.JDFAutoPreview.EnumPreviewFileType;
import org.cip4.jdflib.auto.JDFAutoPreview.EnumPreviewUsage;
import org.cip4.jdflib.core.AttributeName;
import org.cip4.jdflib.core.ElementName;
import org.cip4.jdflib.core.JDFAudit;
import org.cip4.jdflib.core.JDFNodeInfo;
import org.cip4.jdflib.core.JDFResourceLink;
import org.cip4.jdflib.core.JDFSeparationList;
import org.cip4.jdflib.core.VString;
import org.cip4.jdflib.core.JDFElement.EnumNodeStatus;
import org.cip4.jdflib.core.JDFElement.EnumVersion;
import org.cip4.jdflib.core.JDFResourceLink.EnumUsage;
import org.cip4.jdflib.datatypes.JDFAttributeMap;
import org.cip4.jdflib.datatypes.JDFCMYKColor;
import org.cip4.jdflib.datatypes.JDFXYPair;
import org.cip4.jdflib.datatypes.VJDFAttributeMap;
import org.cip4.jdflib.node.JDFNode.EnumType;
import org.cip4.jdflib.resource.JDFResource;
import org.cip4.jdflib.resource.JDFResource.EnumPartIDKey;
import org.cip4.jdflib.resource.JDFResource.EnumResStatus;
import org.cip4.jdflib.resource.process.JDFColor;
import org.cip4.jdflib.resource.process.JDFColorPool;
import org.cip4.jdflib.resource.process.JDFColorantControl;
import org.cip4.jdflib.resource.process.JDFComponent;
import org.cip4.jdflib.resource.process.JDFConventionalPrintingParams;
import org.cip4.jdflib.resource.process.JDFExposedMedia;
import org.cip4.jdflib.resource.process.JDFLayout;
import org.cip4.jdflib.resource.process.JDFMedia;
import org.cip4.jdflib.resource.process.JDFPreview;

/**
 * @author Rainer Prosi
 * class that generates golden tickets based on ICS levels etc
 */
public class MISCPGoldenTicket extends MISGoldenTicket
{
    private boolean grayBox;
    private VJDFAttributeMap vParts;
    private VString cols;
    private VString colsActual;
    private final VString partIDKeys;
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
        cols = new VString("Cyan,Magenta,Yellow,Black,Spot1",",");
        colsActual = new VString("Cyan,Magenta,Gelb,Schwarz,RIP 4711",",");

        if(icsLevel<0)
            return;
        String icsTag="MISCPS_L"+icsLevel+"-"+theVersion.getName();
        theNode.appendAttribute(AttributeName.ICSVERSIONS, icsTag, null, " ", true);
        if(!theNode.hasAttribute(AttributeName.DESCRIPTIVENAME))
            theNode.setDescriptiveName("MICPS Golden Ticket Example Job - version: "+JDFAudit.software());
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
        initPaperMedia();
        initPlate();
        initDevice();
        initOutputComponent();
        initPreview();
        super.init();
    }
    
    /**
     * @param icsLevel
     */
    protected void initPaperMedia()
    {
        JDFMedia m=(JDFMedia) theNode.getCreateResource(ElementName.MEDIA,EnumUsage.Input, 0);
        m.setDescriptiveName("the paper to print on");
        m.setResStatus(EnumResStatus.Available, false);
        m.setMediaType(EnumMediaType.Paper);
        m.setDimensionCM(new JDFXYPair(70,102));
        m.setWeight(90);
        m.setThickness(90/0.8);
        VJDFAttributeMap reducedMap = getReducedMap(new VString("Side Separation"," "));
        int n=reducedMap.size();
        JDFResourceLink rl=theNode.getLink(m, EnumUsage.Input);
        rl.setAmount(n*1.1*sheetAmount, new JDFAttributeMap("Condition","Good"));
        rl.setMaxAmount(n*0.1*sheetAmount, new JDFAttributeMap("Condition","Waste"));


    }
    /**
     * @param icsLevel
     */
    protected void initPlate()
    {
        JDFExposedMedia xm=(JDFExposedMedia) theNode.getCreateResource(ElementName.EXPOSEDMEDIA,EnumUsage.Input, 0);
        JDFMedia m= xm.getCreateMedia();
        xm.setResStatus(EnumResStatus.Available, false);
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
                for(int j=0;j<cols.size();j++)
                    xmp.addPartition(EnumPartIDKey.Separation, cols.stringAt(j));
            }
        }
    }

    /**
     * @param icsLevel
     */
    protected void initOutputComponent()
    {
        JDFComponent outComp=(JDFComponent) theNode.getCreateResource(ElementName.COMPONENT, EnumUsage.Output, 0);
        outComp.setComponentType(EnumComponentType.FinalProduct,EnumComponentType.Sheet);
        JDFResourceLink rl=theNode.getLink(outComp, EnumUsage.Output);
        if(vParts!=null)
        {
            VJDFAttributeMap reducedMap = getReducedMap(new VString("Side Separation"," "));
            for(int i=0;i<reducedMap.size();i++)
            {
                final JDFAttributeMap part = reducedMap.elementAt(i);
                JDFComponent outCompPart=(JDFComponent) outComp.getCreatePartition(part, partIDKeys);
                JDFAttributeMap newMap=new JDFAttributeMap(part);
                newMap.put(AttributeName.CONDITION, "Good");
                rl.setAmount(sheetAmount, newMap);
                rl.setMaxAmount(sheetAmount*1.1, newMap);
            }
        }
        JDFLayout lo=outComp.appendLayout();
        JDFMedia inMedia=(JDFMedia) theNode.getResource(ElementName.MEDIA, EnumUsage.Input, 0);
        outComp.setDimensions(inMedia.getDimension());

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
        JDFPreview pv=(JDFPreview) theNode.getCreateResource(ElementName.PREVIEW,EnumUsage.Input, 0);
        pv.setResStatus(EnumResStatus.Incomplete, false);
        pv.setPreviewUsage(EnumPreviewUsage.Separation);
        pv.setPreviewFileType(EnumPreviewFileType.PNG);

        if(vParts!=null)
        {
            for(int i=0;i<vParts.size();i++)
            {
                final JDFAttributeMap part = vParts.elementAt(i);
                JDFPreview pvp=(JDFPreview) pv.getCreatePartition(part, partIDKeys);
                for(int j=0;j<cols.size();j++)
                {
                    pvp.addPartition(EnumPartIDKey.Separation, cols.stringAt(j));
                    if(previewAvailable)
                    {
                        pvp.setResStatus(EnumResStatus.Available, false);
                        pvp.setURL("file://preveiewHost/previewDir/"+part.toString()+".png");
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
        cpp.setWorkStyle(EnumWorkStyle.WorkAndTurn);
        cpp.setResStatus(EnumResStatus.Available, false);

    }
    /**
     * @param icsLevel
     */
    protected void initColorantControl()
    {
        JDFColorantControl cc=(JDFColorantControl) theNode.getCreateResource(ElementName.COLORANTCONTROL,EnumUsage.Input, 0);
        JDFColorPool cp=cc.getCreateColorPool();
        cc.setResStatus(EnumResStatus.Available, false);

        cp.makeRootResource(null, null, true);
        JDFSeparationList co=cc.getCreateColorantOrder();
        co.setSeparations(cols);
        cc.setProcessColorModel("DeviceCMYK");
        cc.appendColorantParams().setSeparations(new VString(cols.stringAt(4)," "));

        for(int i=0;i<cols.size();i++)
        {
            String name=cols.stringAt(i);
            JDFColor c=cp.appendColorWithName(name, null);
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
     * @param icsLevel
     */
    protected void initNodeInfo()
    {
        super.initNodeInfo();
        JDFNodeInfo ni=theNode.getCreateNodeInfo();
        if(vParts!=null)
        {
            VJDFAttributeMap reducedMap=new VJDFAttributeMap(vParts);
            VString reduceKeys=new VString(partIDKeys);
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
    }


}
