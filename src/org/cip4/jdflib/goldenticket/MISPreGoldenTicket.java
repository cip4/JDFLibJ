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

import java.util.ResourceBundle;

import org.cip4.jdflib.auto.JDFAutoConventionalPrintingParams.EnumWorkStyle;
import org.cip4.jdflib.auto.JDFAutoDeviceInfo.EnumDeviceStatus;
import org.cip4.jdflib.auto.JDFAutoIdentificationField.EnumEncoding;
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
import org.cip4.jdflib.node.JDFNode;
import org.cip4.jdflib.node.JDFNode.EnumProcessUsage;
import org.cip4.jdflib.node.JDFNode.EnumType;
import org.cip4.jdflib.resource.JDFResource;
import org.cip4.jdflib.resource.JDFResource.EnumPartIDKey;
import org.cip4.jdflib.resource.JDFResource.EnumPartUsage;
import org.cip4.jdflib.resource.JDFResource.EnumResStatus;
import org.cip4.jdflib.resource.intent.JDFColorIntent;
import org.cip4.jdflib.resource.process.JDFBarcodeProductionParams;
import org.cip4.jdflib.resource.process.JDFColor;
import org.cip4.jdflib.resource.process.JDFColorPool;
import org.cip4.jdflib.resource.process.JDFColorantControl;
import org.cip4.jdflib.resource.process.JDFExposedMedia;
import org.cip4.jdflib.resource.process.JDFIdentificationField;
import org.cip4.jdflib.resource.process.JDFLayout;
import org.cip4.jdflib.resource.process.JDFLayoutElementProductionParams;
import org.cip4.jdflib.resource.process.JDFMedia;
import org.cip4.jdflib.resource.process.JDFPreview;
import org.cip4.jdflib.resource.process.JDFRunList;
import org.cip4.jdflib.util.JDFDate;

/**
 * @author Rainer Prosi
 * class that generates golden tickets based on ICS levels etc
 */
public class MISPreGoldenTicket extends MISGoldenTicket
{
    public static final String MISPRE_CONTENTCREATION = "MISPRE.ContentCreation";
    public static final String MISPRE_IMPOSITIONPREPARATION = "MISPRE.ImpositionPreparation";
    public static final String MISPRE_PREPRESSPREPARATION = "MISPRE.PrePressPreparation";
    public static final String MISPRE_IMPOSITIONRIPING = "MISPRE.ImpositionRIPing";

    
    private VJDFAttributeMap vParts;
    public VString cols=new VString("Cyan,Magenta,Yellow,Black,Spot1,Spot2,Spot3,Spot4",",");
    public VString colsActual = new VString("Cyan,Magenta,Gelb,Schwarz,RIP 4711,RIP 4712,RIP 4713,RIP 4714",",");
    public int nCols=0;
    public EnumWorkStyle workStyle=EnumWorkStyle.Simplex;

    private final VString partIDKeys;
    protected int icsLevel;
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
    public MISPreGoldenTicket(int _icsLevel, EnumVersion jdfVersion, int _jmfLevel, int _misLevel, VJDFAttributeMap vPartMap)
    {
        super(_misLevel,jdfVersion,_jmfLevel);

        partIDKeys = new VString("SignatureName,SheetName,Side,Separation",",");
        vParts=vPartMap;
        icsLevel=_icsLevel; 
        
        catMap.put(MISPRE_CONTENTCREATION, new VString(EnumType.LayoutElementProduction.getName(),null));
        
        catMap.put(MISPRE_IMPOSITIONPREPARATION, new VString("ImpositionPreparation",null));
        
        if(_icsLevel==1)
            catMap.put(MISPRE_PREPRESSPREPARATION, new VString("PrePressPreparation",null));
        else
            catMap.put(MISPRE_PREPRESSPREPARATION, new VString("Stripping",null));

        catMap.put(MISPRE_IMPOSITIONRIPING, new VString("Imposition RIPing PreviewGeneration",null));
    }

    /**
     * initializes this node to a given ICS version
     * @param icsLevel the level to init to (1,2 or 3)
     */
    public void init()
    {
        initColsFromParent();

        //put level methods?

        while(cols.size()>nCols && nCols>0)
            cols.remove(nCols);

        if(icsLevel<0)
            return;
        String icsTag="MISPre_L"+icsLevel+"-"+theVersion.getName();
        theNode.appendAttribute(AttributeName.ICSVERSIONS, icsTag, null, " ", true);
        if(!theNode.hasAttribute(AttributeName.DESCRIPTIVENAME))
            theNode.setDescriptiveName("MISPre Golden Ticket Example Job - version: "+JDFAudit.software());

        String cat=getCategory();
        if(MISPRE_CONTENTCREATION.equals(cat))
        {
            initContentCreation();
        }
        else if(MISPRE_PREPRESSPREPARATION.equals(cat))
        {
            initPrePressPreparation();
        }
        else if(MISPRE_IMPOSITIONPREPARATION.equals(cat))
        {
            initImpositionPreparation();
        }
        else if(MISPRE_IMPOSITIONRIPING.equals(cat))
        {
            initImpositionRIPing();
        }
//        initPaperMedia();
//        initPlate();
//        initDevice();
//        initPreview();
        super.init();
        setActivePart(vParts, true);
     }

    /**
     * 
     */
    private void initImpositionRIPing()
    {
        initColorantControl();
        initDevice(null,"plateSetterID");
        
    }

    /**
     * 
     */
    private void initPrePressPreparation()
    {
        if(thePreviousNode!=null)
            theNode.linkOutputs(thePreviousNode);
        else
            initDocumentRunList();
        
        initConduitRunListOut();
    }

    /**
     * 
     */
    private void initImpositionPreparation()
    {
       theNode.linkOutputs(thePreviousNode);
       initOutputLayout();
        
    }

    /**
     * 
     */
    private void initOutputLayout()
    {
        JDFLayout lo=(JDFLayout) theNode.getCreateResource(ElementName.LAYOUT, EnumUsage.Output, 0);
        lo.setResStatus(EnumResStatus.Unavailable, false);
        lo.setPartIDKeys(new VString("SignatureName SheetName Side",null));       
    }

    /**
     * 
     */
    private void initContentCreation()
    {
       initDocumentRunList();   
       initLayoutElementProductionParams();
       initConduitRunListOut();
    }

    /**
     * 
     */
    private void initConduitRunListOut()
    {
        JDFRunList rl=(JDFRunList) theNode.getCreateResource(ElementName.RUNLIST, EnumUsage.Output, 0);
        rl.setResStatus(EnumResStatus.Unavailable, false);        
    }

    /**
     * 
     */
    private void initLayoutElementProductionParams()
    {
       JDFLayoutElementProductionParams lep=(JDFLayoutElementProductionParams) theNode.getCreateResource(ElementName.LAYOUTELEMENTPRODUCTIONPARAMS, EnumUsage.Input, 0);
       JDFBarcodeProductionParams bp=lep.getCreateLayoutElementPart(0).appendBarcodeProductionParams();
       final JDFIdentificationField idf = bp.appendIdentificationField();
       idf.setEncoding(EnumEncoding.Barcode);
       idf.setEncodingDetails("EAN");
       idf.setValue("123456");
    }

    /**
     * 
     */
    private void initDocumentRunList()
    {
        JDFRunList rl=(JDFRunList) theNode.getCreateResource(ElementName.RUNLIST, EnumUsage.Input, 0);
        JDFResourceLink rll=theNode.getLink(rl, null);
        if("Marks".equals(rll.getProcessUsage()))
        {
            rl=(JDFRunList) theNode.getCreateResource(ElementName.RUNLIST, EnumUsage.Input, 1);
            rll=theNode.getLink(rl, null);           
        }
        rll.setProcessUsage(EnumProcessUsage.Document);
        rl.addPDF("file://foobar/here/file.pdf", 0, -1);
        rl.setNPage(4);
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


    public void setActivePart(VJDFAttributeMap vp, boolean bFirst)
    {
        amountLinks=null;
        if(bFirst)
            addAmountLink("Media:Input");
        addAmountLink("ExposedMedia:Output");
        theStatusCounter.setActiveNode(theNode, vp, getNodeLinks());
    }
    /**
     * simulate execution of this node
     * the internal node will be modified to reflect the excution
     */
    @Override
    public void execute(VJDFAttributeMap parts, boolean outputAvailable, boolean bFirst)
    {

        parts=parts==null ? vParts : parts;
        setActivePart(parts, bFirst);
        super.execute(parts,outputAvailable,bFirst);
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
//        int n=
        	reducedMap.size();
//        JDFResourceLink rl=
        	theNode.getLink(m, EnumUsage.Input);
        return m;


    }
    /**
     * @param icsLevel
     */
    protected void initPlate()
    {

        JDFExposedMedia xm=(JDFExposedMedia) theNode.getCreateResource(ElementName.EXPOSEDMEDIA,EnumUsage.Output, 0);
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
        theNode.linkResource(m, EnumUsage.Input, null);
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
        JDFPreview pv=(JDFPreview) theNode.getCreateResource(ElementName.PREVIEW,EnumUsage.Output, 0);
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
                    pvp.setResStatus(EnumResStatus.Incomplete, false);
                }
            }
        }
    }

    /**
     * @param icsLevel
     */
    protected void initColorantControl()
    {
        JDFResourceLink ccLink=null;
        if(thePreviousNode!=null)
        {
            ccLink=theNode.linkResource(thePreviousNode.getResource(ElementName.COLORANTCONTROL, EnumUsage.Input, 0),EnumUsage.Input,null);
        }
        
        JDFColorantControl cc=(JDFColorantControl) (ccLink==null ? (JDFColorantControl) theNode.getCreateResource(ElementName.COLORANTCONTROL,EnumUsage.Input, 0) : ccLink.getTarget());
        cc.setResStatus(EnumResStatus.Available, false);
        
        JDFColorPool cp=(JDFColorPool) theNode.getJDFRoot().getChildByTagName(ElementName.COLORPOOL, null, 0, null, false, false);
        if(cp==null)
        {
            cp=(JDFColorPool) theNode.getCreateResource(ElementName.COLORPOOL, EnumUsage.Input, 0);
        }
        
 
        cc.refColorPool(cp);
        JDFSeparationList co=cc.getCreateColorantOrder();
        co.setSeparations(cols);
        cc.setProcessColorModel("DeviceCMYK");
        for(int i=4;i<getNCols();i++)
            cc.getCreateColorantParams().appendSeparation(cols.stringAt(i));
        for(int i=0;i<getNCols();i++)
        {
            String name=cols.stringAt(i);
            JDFColor c=cp.getCreateColorWithName(name, null);
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
     * @return 
     */
    protected  JDFNodeInfo initNodeInfo()
    {
        JDFNodeInfo ni=super.initNodeInfo();
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

}
