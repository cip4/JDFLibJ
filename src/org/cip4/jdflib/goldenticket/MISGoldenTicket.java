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

import java.util.HashMap;
import java.util.Map;

import org.cip4.jdflib.auto.JDFAutoStatusQuParams.EnumJobDetails;
import org.cip4.jdflib.core.AttributeName;
import org.cip4.jdflib.core.ElementName;
import org.cip4.jdflib.core.JDFAudit;
import org.cip4.jdflib.core.JDFComment;
import org.cip4.jdflib.core.JDFCustomerInfo;
import org.cip4.jdflib.core.JDFElement;
import org.cip4.jdflib.core.JDFNodeInfo;
import org.cip4.jdflib.core.VString;
import org.cip4.jdflib.core.JDFElement.EnumVersion;
import org.cip4.jdflib.core.JDFResourceLink.EnumUsage;
import org.cip4.jdflib.datatypes.VJDFAttributeMap;
import org.cip4.jdflib.jmf.JDFJMF;
import org.cip4.jdflib.jmf.JDFQuery;
import org.cip4.jdflib.jmf.JDFStatusQuParams;
import org.cip4.jdflib.jmf.JDFSubscription;
import org.cip4.jdflib.jmf.JDFMessage.EnumType;
import org.cip4.jdflib.node.JDFNode;
import org.cip4.jdflib.resource.JDFDevice;
import org.cip4.jdflib.resource.JDFResource.EnumResStatus;
import org.cip4.jdflib.resource.process.JDFCompany;
import org.cip4.jdflib.resource.process.JDFContact;
import org.cip4.jdflib.resource.process.JDFEmployee;
import org.cip4.jdflib.resource.process.JDFPerson;
import org.cip4.jdflib.util.StringUtil;

/**
 * @author prosirai
 * class that generates golden tickets based on ICS levels etc
 */
public class MISGoldenTicket extends BaseGoldenTicket
{
    protected int misICSLevel;
    protected int jmfICSLevel;
    protected Map<String,VString> catMap=new HashMap<String, VString>();
    private String category=null;
    /**
     * seconds ago that this started
     */
    public int preStart=600;
    /**
     * seconds this was active
     */
    public int duration=preStart/2;
    /**
     * create a BaseGoldenTicket
     * @param icsLevel the level to init to (1,2 or 3)
     * @param jdfVersion the version to generate a golden ticket for
     * @param jmfLevel level of jmf ICS to support
     */
    public MISGoldenTicket(int icsLevel, EnumVersion jdfVersion, int jmfLevel)
    {
        super(2,jdfVersion); // mis always requires base 2
        misICSLevel=icsLevel;
        jmfICSLevel=jmfLevel;
    }

    /**
     * 
     */
    public void assign(JDFNode node)
    {
        super.assign(node);
        if(jmfICSLevel>0)
        {
            new JMFGoldenTicket(jmfICSLevel,theVersion).assign(theNode);
        }
        super.init(); 
    }
    /**
     * simulate execution of this node
     * the internal node will be modified to reflect the excution
     */
    @Override
    public void execute(VJDFAttributeMap vNodeMap, boolean bOutAvail,boolean bFirst)
    {
        JDFComment c=theNode.appendComment();
        c.setName("OperatorText");
        c.setText(StringUtil.getRandomString());

        super.execute(vNodeMap,bOutAvail, bFirst);


    }

    /**
     * initializes this node to a given ICS version
     * @param icsLevel the level to init to (1,2 or 3)
     */
    public void init()
    {
        if(misICSLevel<0)
            return;
        String icsTag="MIS_L"+misICSLevel+"-"+theVersion.getName();
        theNode.appendAttribute(AttributeName.ICSVERSIONS, icsTag, null, " ", true);
        if(!theNode.hasAttribute(AttributeName.DESCRIPTIVENAME))
            theNode.setDescriptiveName("MIS Golden Ticket Example Job - version: "+JDFAudit.software());
        if(!theNode.hasAttribute(AttributeName.JOBID))
            theNode.setJobID("Job_"+JDFElement.uniqueID(0));
        final VString types = getTypes();
        if(types!=null)
        {
            theNode.setCategory(getCategory());
            theNode.setTypes(types);
            theNode.setType(org.cip4.jdflib.node.JDFNode.EnumType.ProcessGroup);
        }
        initNodeInfo();
        initCustomerInfo();
    }
    /**
     * @param icsLevel
     */
    protected JDFNodeInfo initNodeInfo()
    {

        JDFNodeInfo ni=theNode.getCreateNodeInfo();
        ni.setResStatus(EnumResStatus.Available, false);

        if(theParentNode==null)
        {
            JDFEmployee emp=ni.appendEmployee();
            emp.setPersonalID("personalID1");
            emp.setRoles(new VString("CSR",null));
            if(jmfICSLevel>=1 && misICSLevel>=2)
            {
                JDFJMF jmf=ni.appendJMF();
                jmf.setSenderID("MISGTSender");
                JDFQuery q=jmf.appendQuery(EnumType.Status);
                final JDFStatusQuParams statusQuParams = q.appendStatusQuParams();
                statusQuParams.setJobID(theNode.getJobID(true));
                statusQuParams.setJobPartID(theNode.getJobPartID(false));
                statusQuParams.setJobDetails(EnumJobDetails.Brief);
                final JDFSubscription subscription = q.appendSubscription();
                subscription.setRepeatTime(600);
                subscription.setURL(misURL==null?"http://MIS.printer.com/JMFSignal":misURL);
            }
        }
        return ni;
    }
    /**
     * @param icsLevel
     */
    protected JDFCustomerInfo initCustomerInfo()
    {
        if(theParentNode!=null)
        {
            final JDFCustomerInfo customerInfo = theParentNode.getCustomerInfo();
            if(customerInfo!=null)
            {
                theNode.linkResource(customerInfo, EnumUsage.Input, null);
                return customerInfo;
            }
        }
        JDFCustomerInfo ci=theNode.getCreateCustomerInfo();
        ci.setResStatus(EnumResStatus.Available, false);

        ci.setCustomerID("customerID");
        ci.setCustomerJobName("customer job name");
        ci.setCustomerOrderID("customerOrder_1");
        JDFContact contact=ci.appendContact();
        contact.makeRootResource(null, null, true);
        contact.setContactTypes(new VString("Customer Administrator"," "));
        JDFPerson person=contact.appendPerson();
        person.setFamilyName("Töpfer");
        person.setFirstName("Harald");
        JDFCompany comp=contact.appendCompany();
        comp.setOrganizationName("The Pits");
        return ci;
    }

    
    protected JDFDevice initDevice(JDFNode reuseNode, String devID)
    {
        if(misICSLevel<2)
            return null;
        if(reuseNode!=null)
            theNode.linkResource(reuseNode.getResource(ElementName.DEVICE, EnumUsage.Input, 0),EnumUsage.Input,null);
        JDFDevice dev = (JDFDevice) theNode.getCreateResource(ElementName.DEVICE, EnumUsage.Input, 0);
        dev.setResStatus(EnumResStatus.Available, false);
        dev.setDeviceID(devID);
        dev.setDescriptiveName("Device "+devID);
        return dev;
    }

    /**
     * add the type of amount link for resource audits etc
     * @param link
     */
    public void addAmountLink(String link)
    {
        if(amountLinks==null)
            amountLinks=new VString();
        amountLinks.appendUnique(link);
    }

    public String getCategory()
    {
        return category;
    }

    public void setCategory(String _category)
    {
        category = _category;
    }

    /**
     * get the correct Types from category
     * @return
     */
    public VString getTypes()
    {
        if(category==null)
            return null;
        return catMap.get(category);
    }
}
