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
import org.cip4.jdflib.auto.JDFAutoLayoutIntent.EnumSides;
import org.cip4.jdflib.auto.JDFAutoMedia.EnumMediaType;
import org.cip4.jdflib.auto.JDFAutoPart.EnumSide;
import org.cip4.jdflib.core.AttributeName;
import org.cip4.jdflib.core.ElementName;
import org.cip4.jdflib.core.JDFAudit;
import org.cip4.jdflib.core.JDFCustomerInfo;
import org.cip4.jdflib.core.JDFNodeInfo;
import org.cip4.jdflib.core.JDFResourceLink;
import org.cip4.jdflib.core.VElement;
import org.cip4.jdflib.core.VString;
import org.cip4.jdflib.core.JDFElement.EnumVersion;
import org.cip4.jdflib.core.JDFResourceLink.EnumUsage;
import org.cip4.jdflib.datatypes.JDFShape;
import org.cip4.jdflib.node.JDFNode;
import org.cip4.jdflib.node.JDFNode.EnumProcessUsage;
import org.cip4.jdflib.node.JDFNode.EnumType;
import org.cip4.jdflib.resource.JDFResource.EnumPartIDKey;
import org.cip4.jdflib.resource.JDFResource.EnumResStatus;
import org.cip4.jdflib.resource.intent.JDFBindingIntent;
import org.cip4.jdflib.resource.intent.JDFColorIntent;
import org.cip4.jdflib.resource.intent.JDFDeliveryIntent;
import org.cip4.jdflib.resource.intent.JDFDropItemIntent;
import org.cip4.jdflib.resource.intent.JDFFoldingIntent;
import org.cip4.jdflib.resource.intent.JDFLayoutIntent;
import org.cip4.jdflib.resource.intent.JDFMediaIntent;
import org.cip4.jdflib.resource.process.JDFComponent;
import org.cip4.jdflib.resource.process.JDFContact;
import org.cip4.jdflib.resource.process.JDFContact.EnumContactType;
import org.cip4.jdflib.span.JDFSpanBindingType.EnumSpanBindingType;
import org.cip4.jdflib.span.JDFSpanCoatings.EnumSpanCoatings;
import org.cip4.jdflib.util.JDFDate;

/**
 * @author Rainer Prosi
 * class that generates golden tickets based on ICS levels etc
 */
public class ProductGoldenTicket extends MISGoldenTicket
{
    private final int icsLevel;
    private JDFMediaIntent mediaIntent;

    public ProductGoldenTicket(int _icsLevel, EnumVersion jdfVersion, int _jmfLevel, int _misLevel)
    {
        super(_misLevel,jdfVersion,_jmfLevel);
        icsLevel=_icsLevel;        
    }

    /**
     * initializes this node to a given ICS version
     * @param icsLevel the level to init to (1,2 or 3)
     */
    @Override
	public void init()
    {

        if(icsLevel<0)
            return;
//      String icsTag="MISCPS_L"+icsLevel+"-"+theVersion.getName();
//      theNode.appendAttribute(AttributeName.ICSVERSIONS, icsTag, null, " ", true);
        if(!theNode.hasAttribute(AttributeName.DESCRIPTIVENAME))
            theNode.setDescriptiveName("Product Golden Ticket Example Job - version: "+JDFAudit.software());
        theNode.setType(EnumType.Product);

        super.init();
    }


    /**
     * @param icsLevel
     */
    protected void initPaperMedia()
    {
        mediaIntent=(JDFMediaIntent) theNode.getCreateResource(ElementName.MEDIAINTENT,EnumUsage.Input, 0);
        mediaIntent.setDescriptiveName("the paper to print on");
        mediaIntent.setResStatus(EnumResStatus.Available, false);
        mediaIntent.appendMediaType().setPreferred(EnumMediaType.Paper);
        mediaIntent.appendWeight().setPreferred(90);

        mediaIntent.setResStatus(EnumResStatus.Available, false);
        mediaIntent.preferredToActual();
    }
    /**
     * @param icsLevel
     */
    protected JDFComponent initOutputComponent(JDFNode node, JDFLayoutIntent li)
    {
        JDFComponent outComp=(JDFComponent) node.getCreateResource(ElementName.COMPONENT, EnumUsage.Output, 0);
        outComp.setComponentType(EnumComponentType.FinalProduct,EnumComponentType.Sheet);

        theNode.getResource(ElementName.LAYOUTINTENT, null, 0);
        JDFShape s=li.getFinishedDimensions().getActual();
        outComp.setDimensions(s);
        return outComp;

    }
    /**
     * initialize deliveryintent and also output component
     * @param amount
     */
    protected void initDeliveryIntent(int amount)
    {
        JDFComponent outComp=(JDFComponent) theNode.getCreateResource(ElementName.COMPONENT, EnumUsage.Output, 0);
        JDFDeliveryIntent di=(JDFDeliveryIntent) theNode.addResource(ElementName.DELIVERYINTENT, EnumUsage.Input);
        JDFDate d=new JDFDate();
        d.addOffset(0, 0, 0, 7);
        di.appendRequired().setPreferred(d);
        JDFDropItemIntent dit=di.appendDropIntent().appendDropItemIntent();
        dit.refElement(outComp);
        dit.setAmount(amount);
        JDFResourceLink rl=theNode.getLink(outComp, null);
        rl.setAmount(amount, null);
        
        di.setResStatus(EnumResStatus.Available, false);
        di.preferredToActual();
    }
    /**
     */
    @Override
	protected JDFNodeInfo initNodeInfo()
    {
        super.initNodeInfo();
        JDFNodeInfo ni=theNode.getCreateNodeInfo();
        ni.setStart(null);
        return ni;
    }
    /**
     */
    protected JDFCustomerInfo initCustomerInfo(String firstame, String lastame, String companyName, String jobName)
    {
        JDFCustomerInfo ci=theNode.getCreateCustomerInfo();
        ci.setCustomerJobName(jobName);
        JDFContact c=ci.appendContact(EnumContactType.Customer);
        c.setPerson(firstame,lastame);
        if(companyName!=null)
            c.getCreateCompany().setOrganizationName(companyName);
        return ci;
    }

    /**
     * 
     */
    protected JDFMediaIntent initMediaIntent(JDFNode node, double gsm, EnumSpanCoatings coating)
    {
        JDFMediaIntent mi=(JDFMediaIntent) node.addResource(ElementName.MEDIAINTENT, EnumUsage.Input);
        mi.getCreateWeight().setPreferred(gsm);
        mi.getCreateFrontCoatings().setPreferred(coating);
        mi.setResStatus(EnumResStatus.Available, false);
        mi.preferredToActual();
        return mi;
    }
    /**
     * 
     */
    protected JDFColorIntent initColorIntent(JDFNode node, int front, int back, String coatings)
    {
        VString colors=new VString("Cyan Magenta Yello Black Gray Blue Spot1 Spot2"," ");
        JDFColorIntent ci=(JDFColorIntent) node.addResource(ElementName.COLORINTENT, EnumUsage.Input);
        VElement vci=new VElement();
        if(front!=back && back!=0)
        {
            vci.add(ci.addPartition(EnumPartIDKey.Side, EnumSide.Front));
            vci.add(ci.addPartition(EnumPartIDKey.Side, EnumSide.Back));
        }
        else
        {
            vci.add(ci);
        }

        for(int i=0;i<vci.size();i++)
        {
            int cols=i==0?front:back;
            VString newCols=new VString();
            for(int j=0;j<cols;j++)
                newCols.add(colors.elementAt(j));
            JDFColorIntent cip=(JDFColorIntent)vci.elementAt(i);
            cip.appendColorsUsed().setSeparations(newCols);
        }
        ci.setResStatus(EnumResStatus.Available, false);
        ci.preferredToActual();
        return ci;
    }

    /**
     * 
     */
    protected JDFLayoutIntent initLayoutIntent(JDFNode node, double xCM, double yCM, int pages, int sides)
    {
        JDFLayoutIntent loi=(JDFLayoutIntent) node.addResource(ElementName.LAYOUTINTENT, EnumUsage.Input);
        loi.getCreateFinishedDimensions().setPreferred(xCM*72/2.54,yCM*72/2.54,0);
        loi.getCreatePages().setPreferred(pages);
        loi.setSides(sides ==2 ? EnumSides.TwoSidedHeadToHead : EnumSides.OneSided);
        loi.setResStatus(EnumResStatus.Available, false);
        loi.preferredToActual();
        return loi;
    }
    /**
     * 
     */
    protected JDFFoldingIntent initFoldingIntent(JDFNode node, String foldCatalog)
    {
        JDFFoldingIntent fi=(JDFFoldingIntent) node.addResource(ElementName.FOLDINGINTENT, EnumUsage.Input);
        fi.appendFoldingCatalog().setPreferred(foldCatalog);
        fi.setResStatus(EnumResStatus.Available, false);
        fi.preferredToActual();
        return fi;
    }

    public void createPostCards() throws Exception
    {
        initCustomerInfo(null,null,"Volkswagen AG","Freeway postcards");
        theNode.setDescriptiveName("7.5.1   Postcards 4c/4c");
        initMediaIntent(theNode,300, EnumSpanCoatings.Coated);
        JDFLayoutIntent li=initLayoutIntent(theNode,14.8, 10.5, 16, 2);
        initColorIntent(theNode,4,4,null);
        initOutputComponent(theNode,li);
        initDeliveryIntent(5000);
    }

    public void createHarley() throws Exception
    {
        initCustomerInfo(null,null,"ABC Promotions Company","Speed-Point Harley Poster");
        theNode.setDescriptiveName("7.5.4   Poster 4c/0c");
        initMediaIntent(theNode,170, EnumSpanCoatings.Glossy);
        JDFLayoutIntent li=initLayoutIntent(theNode,43, 32.6, 1, 1);
        initColorIntent(theNode,4,0,null);
        initOutputComponent(theNode,li);
        initDeliveryIntent(5000);
    }

    /**
     * @param cover
     * @param body
     * @return 
     */
    protected JDFBindingIntent initBindingIntent(JDFComponent cover, JDFComponent body, int numStitches)
    {
        JDFBindingIntent bi=(JDFBindingIntent) theNode.addResource(ElementName.BINDINGINTENT, EnumUsage.Input);
        if(numStitches>0)
        {
            bi.appendSaddleStitching().appendStitchNumber().setActual(numStitches);
            bi.appendBindingType().setActual(EnumSpanBindingType.SaddleStitch);
        }
        theNode.linkResource(cover, EnumUsage.Input, EnumProcessUsage.Cover);
        theNode.linkResource(body, EnumUsage.Input, null);
        bi.setResStatus(EnumResStatus.Available, false);
        bi.preferredToActual();
        return bi;

    }

    public void createAddressBook() throws Exception
    {
        initCustomerInfo(null,null,"Art Point","Address Pocketbook");
        theNode.setDescriptiveName("7.5.2   A5 brochure 4c/4c, 4pg Cover, 32 pg Text");
        JDFNode cover=theNode.addJDFNode(EnumType.Product);
        cover.setDescriptiveName("Address Book Cover");

        initMediaIntent(cover,200, EnumSpanCoatings.Glossy);
        JDFLayoutIntent li=initLayoutIntent(cover,14.8, 21, 4, 2);
        JDFColorIntent ci=initColorIntent(cover,4,4,null);
        JDFComponent cCover=initOutputComponent(cover,li);
        cCover.setComponentType(EnumComponentType.PartialProduct, EnumComponentType.Sheet);


        JDFNode body=theNode.addJDFNode(EnumType.Product);
        body.setDescriptiveName("Address Book Body");
        body.linkResource(ci, EnumUsage.Input, null);
        initMediaIntent(body,135, EnumSpanCoatings.Coated);
        initLayoutIntent(body,14.8, 21, 32, 2);
        JDFComponent cBody=initOutputComponent(body,li);
        cBody.setComponentType(EnumComponentType.PartialProduct, EnumComponentType.Sheet);

        initBindingIntent(cCover, cBody,2);

        initOutputComponent(theNode,li);
        initDeliveryIntent(5000);
    }
    
    public void createHDCity() throws Exception
    {
        initCustomerInfo(null,null,"Heidelberger Druckmaschinen AG","Heidelberg A4 brochure");
        theNode.setDescriptiveName("7.5.5   A4 brochure with spot colors, 4pg Cover 6c/4c, 32 pg Text 4c/4c");
        JDFNode cover=theNode.addJDFNode(EnumType.Product);
        cover.setDescriptiveName("HD Brochure Cover");

        initMediaIntent(cover,200, EnumSpanCoatings.Glossy);
        JDFLayoutIntent li=initLayoutIntent(cover,21, 29.7, 4, 2);
    	initColorIntent(cover,6,4,null);
        JDFComponent cCover=initOutputComponent(cover,li);
        cCover.setComponentType(EnumComponentType.PartialProduct, EnumComponentType.Sheet);


        JDFNode body=theNode.addJDFNode(EnumType.Product);
        body.setDescriptiveName("HD Brochure Body");
    	initColorIntent(body,4,4,null);
        initMediaIntent(body,135, EnumSpanCoatings.Coated);
        initLayoutIntent(body,21, 29.7, 32, 2);
        JDFComponent cBody=initOutputComponent(body,li);
        cBody.setComponentType(EnumComponentType.PartialProduct, EnumComponentType.Sheet);

        initBindingIntent(cCover, cBody,2);

        initOutputComponent(theNode,li);
        initDeliveryIntent(5000);
    }

    public void createWatches() throws Exception
    {
        initCustomerInfo(null,null,"ABC Promotions Company","Sinn watches double flap");
         theNode.setDescriptiveName("7.5.3 Flyer with special fold 4c/4c");

        initMediaIntent(theNode,170, EnumSpanCoatings.Coated);
        JDFLayoutIntent li=initLayoutIntent(theNode,21, 29.7, 6, 2);
    	initColorIntent(theNode,4,4,null);
        JDFFoldingIntent fi=initFoldingIntent(theNode, "F6-3");
        fi.setDescriptiveName("F6-3 should be the gate fold");
        initOutputComponent(theNode,li);
        initDeliveryIntent(5000);
    } 
    
    
    
}
