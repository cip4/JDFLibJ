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

import org.cip4.jdflib.core.AttributeName;
import org.cip4.jdflib.core.ElementName;
import org.cip4.jdflib.core.JDFAudit;
import org.cip4.jdflib.core.JDFDoc;
import org.cip4.jdflib.core.JDFElement;
import org.cip4.jdflib.core.JDFPartAmount;
import org.cip4.jdflib.core.JDFResourceLink;
import org.cip4.jdflib.core.VElement;
import org.cip4.jdflib.core.JDFElement.EnumNodeStatus;
import org.cip4.jdflib.core.JDFElement.EnumVersion;
import org.cip4.jdflib.core.JDFResourceLink.EnumUsage;
import org.cip4.jdflib.datatypes.JDFAttributeMap;
import org.cip4.jdflib.node.JDFNode;
import org.cip4.jdflib.resource.JDFResource;
import org.cip4.jdflib.resource.JDFResource.EnumResStatus;
import org.cip4.jdflib.util.EnumUtil;
import org.cip4.jdflib.util.UrlUtil;

/**
 * @author prosirai
 * class that generates golden tickets based on ICS levels etc
 * basegolden ticket should generally be the last in the cascade domain - mis - jmf - base
 * 
 * To generate a new golden ticket, follow these steps
 * 1.) construct the appropriate domain subclass, e.g. MISCPGoldenTicket for mis to conventional print
 * 2.) call .assign(null) (or your favorite hand-coded jdf node)
 * 3.) retrieve the updated copy with .getNode()
 * 
 */
public class BaseGoldenTicket
{
    protected JDFNode theNode=null;
    protected EnumVersion theVersion=null;
    protected int baseICSLevel;
    /**
     * percentage allowed maxamount waste to be used for audits
     */
    public int wastePercent=150; 
    
    /**
     * percentage of amount to be used as actual for audits
     */
    public int actualPercent=103; 

    /**
     * create a BaseGoldenTicket
     * @param icsLevel the level to init to (1,2 or 3)
     * @param jdfVersion the version to generate a golden ticket for
     */
    public BaseGoldenTicket(int icsLevel, EnumVersion jdfVersion)
    {
        baseICSLevel=icsLevel;
        theVersion=jdfVersion;
    }

    /**
     * assign a node to this golden ticket instance
     * @param node the node to assign, if null a new conforming node is generated from scratch
     */
    public void assign(JDFNode node)
    {
        theNode=node==null ? new JDFDoc("JDF").getJDFRoot() : node;
        setVersion();
        init();
    }

    /**
     * simulate execution of this node
     * the internal node will be modified to reflect the excution
     */
    public void execute()
    {
        VElement vResLinks=theNode.getResourceLinks(null);
        int siz= vResLinks!=null ? vResLinks.size() : 0;
        for(int i=0;i<siz;i++)
        {
            JDFResourceLink rl=(JDFResourceLink)vResLinks.elementAt(i);
            if(rl.hasChildElement(ElementName.AMOUNTPOOL, null))
            {
                int ipa=0;
                while(true)
                {
                    JDFPartAmount pa=rl.getAmountPool().getPartAmount(ipa++);
                    if(pa==null)
                        break;
                    JDFAttributeMap mPA=pa.getPartMap();
                    if(mPA.containsKey("Condition"))
                    {
                        if(mPA.get("Condition").equals("Good"))
                        {
                            pa.setActualAmount(pa.getAmount(null)*actualPercent/100,null);
                        }
                        else if(mPA.get("Condition").equals("Waste"))
                        {
                            pa.setActualAmount(pa.getMaxAmount(null)*wastePercent/100,null);
                        }
                        
                    }
                    else if(pa.hasAttribute(AttributeName.AMOUNT))
                    {
                        pa.setActualAmount(pa.getAmount(null)*actualPercent/100,null);
                    }
                }
            }
            else if(rl.hasAttribute(AttributeName.AMOUNT))
            {
                rl.setActualAmount(rl.getAmount(null)*actualPercent/100, null);
            }
            if(EnumUsage.Output.equals(rl.getUsage()))
            {
                VElement vRes=rl.getTargetVector(-1);
                for(int j=0;j<vRes.size();j++)
                {
                    JDFResource r=(JDFResource) vRes.elementAt(j);
                    r.setResStatus(EnumResStatus.Available, true);
                }
            }
        }
            
        // base requires no generic excute support
//      JDFProcessRun pr=(JDFProcessRun) theNode.getCreateAuditPool().addAudit(EnumAuditType.ProcessRun, null);

    }


    protected void setVersion()
    {
        if(theVersion==null)
            theVersion= theNode.getVersion(true);
        if(theVersion==null)
            theVersion=JDFElement.getDefaultJDFVersion();
    }
    /**
     * initializes this node to a given ICS version
     * @param icsLevel the level to init to (1,2 or 3)
     */
    public void init()
    {
        String icsTag="Base_L"+baseICSLevel+"-"+theVersion.getName();
        theNode.appendAttribute(AttributeName.ICSVERSIONS, icsTag, null, " ", true);
        theNode.setVersion(theVersion);
        theNode.setMaxVersion(theVersion);
        theNode.setStatus(EnumNodeStatus.Waiting);
        if(!theNode.hasAttribute(AttributeName.DESCRIPTIVENAME))
            theNode.setDescriptiveName("Base Golden Ticket Example Job - version: "+JDFAudit.software());

        if(!theNode.hasAttribute(AttributeName.COMMENTURL))
            theNode.setCommentURL(UrlUtil.StringToURL("//MyHost/data/Comments.html").toExternalForm());
    }

    /**
     * gets the current state of the node
     * @return the theNode
     */
    public JDFNode getNode()
    {
        return theNode;
    }

    /* (non-Javadoc)
     * @see java.lang.Object#toString()
     */
    public String toString()
    {
        String s= "["+this.getClass().getName()+" Version: "+EnumUtil.getName(theVersion)+"]";
        if(theNode!=null)
            s+=theNode.toString();
        return s;
    }
}
