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

import org.cip4.jdflib.auto.JDFAutoDeviceInfo.EnumDeviceStatus;
import org.cip4.jdflib.auto.JDFAutoResourceAudit.EnumReason;
import org.cip4.jdflib.core.AttributeName;
import org.cip4.jdflib.core.JDFAudit;
import org.cip4.jdflib.core.JDFDoc;
import org.cip4.jdflib.core.JDFElement;
import org.cip4.jdflib.core.JDFPartAmount;
import org.cip4.jdflib.core.JDFResourceLink;
import org.cip4.jdflib.core.VElement;
import org.cip4.jdflib.core.VString;
import org.cip4.jdflib.core.JDFElement.EnumNodeStatus;
import org.cip4.jdflib.core.JDFElement.EnumVersion;
import org.cip4.jdflib.core.JDFResourceLink.EnumUsage;
import org.cip4.jdflib.datatypes.JDFAttributeMap;
import org.cip4.jdflib.datatypes.VJDFAttributeMap;
import org.cip4.jdflib.node.JDFNode;
import org.cip4.jdflib.resource.JDFResource;
import org.cip4.jdflib.resource.JDFResource.EnumResStatus;
import org.cip4.jdflib.util.EnumUtil;
import org.cip4.jdflib.util.StatusCounter;
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
    protected VString amountLinks=null;
    protected JDFNode theNode=null;
    protected JDFNode thePreviousNode=null;
    protected JDFNode theParentNode=null;
    protected EnumVersion theVersion=null;
    protected int baseICSLevel;
    protected StatusCounter theStatusCounter;
    protected static String misURL=null;
    protected static String deviceURL=null;

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
        theStatusCounter=new StatusCounter(null,null,null);
        JDFElement.setLongID(false);
    }

    /**
     * assign a node to this golden ticket instance
     * @param node the node to assign, if null a new conforming node is generated from scratch
     */
    public void assign(JDFNode node)
    {
        theNode=node==null ? new JDFDoc("JDF").getJDFRoot() : node;
        if(theParentNode==null && theNode.getParentJDF()!=null)
            theParentNode=theNode.getParentJDF();
        setVersion();
        init();
    }
    /**
     * assign a previous node to this golden ticket instance, e.g. an imagesetting node
     * @param node the node to assign, if null a new conforming node is generated from scratch
     */
    public void setPreviousNode(JDFNode node)
    {
        thePreviousNode=node;
    }
    /**
     * simulate makeReady of this node
     * the internal node will be modified to reflect the makeready
     * all required resources should be available
     */
    public void makeReady()
    {
        VElement vResLinks=theNode.getResourceLinks(null);
        int siz= vResLinks!=null ? vResLinks.size() : 0;
        for(int i=0;i<siz;i++)
        {
            JDFResourceLink rl=(JDFResourceLink)vResLinks.elementAt(i);

            if(EnumUsage.Input.equals(rl.getUsage()))
            {
                VElement vRes=rl.getTargetVector(-1);                
                for(int j=0;j<vRes.size();j++)
                {
                    VElement leaves=((JDFResource)vRes.elementAt(j)).getLeaves(false);
                    for(int k=0;k<leaves.size();k++)
                    {
                        JDFResource r=(JDFResource) leaves.elementAt(k);
                        r.setResStatus(EnumResStatus.Available, true);
                    }
                }
            }
        }
    }

    /**
     * simulate execution of this node
     * the internal node will be modified to reflect the excution
     */
    public void execute(VJDFAttributeMap vMap, boolean bOutAvail, boolean bFirst, int good, int waste)
    {
        theNode.setPartStatus(vMap, EnumNodeStatus.Completed);
        runphases(good, waste);
        
        VElement vResLinks=theNode.getResourceLinks(null);
        int siz= vResLinks!=null ? vResLinks.size() : 0;
        for(int i=0;i<siz;i++)
        {
            JDFResourceLink rl=(JDFResourceLink)vResLinks.elementAt(i);

            if(bOutAvail && EnumUsage.Output.equals(rl.getUsage()))
            {
                VElement vRes=rl.getTargetVector(-1);

                for(int j=0;j<vRes.size();j++)
                {
                    VElement leaves=((JDFResource)vRes.elementAt(j)).getLeaves(false);
                    for(int k=0;k<leaves.size();k++)
                    {
                        JDFResource r=(JDFResource) leaves.elementAt(k);
                        JDFAttributeMap map=r.getPartMap();
                        if(vMap==null || vMap.overlapsMap(map))
                        {
                            r.setResStatus(EnumResStatus.Available, true);

                            if(good>=0)
                            {
                                map.put("Condition", "Good");
                                JDFPartAmount pa=rl.getCreateAmountPool().getPartAmount(map);
                                double preSet=pa==null ? 0 : pa.getActualAmount(null);
                                rl.setActualAmount(preSet+good, map);
                            }
                            if(waste>=0)
                            {
                                map.put("Condition", "Waste");
                                JDFPartAmount pa=rl.getCreateAmountPool().getPartAmount(map);
                                double preSet=pa==null ? 0 : pa.getActualAmount(null);
                                rl.setActualAmount(preSet+waste, map);
                            }
                        }
                    }
                }
            }
        }

        // base requires no generic excute support
//      JDFProcessRun pr=(JDFProcessRun) theNode.getCreateAuditPool().addAudit(EnumAuditType.ProcessRun, null);

    }

    protected void runphases(int good, int waste)
    {
        theStatusCounter.setPhase(EnumNodeStatus.InProgress, "SD", EnumDeviceStatus.Running, "SD");
        runSinglePhase(good, waste);
        finalize(); // prior to processRun
        theStatusCounter.setPhase(EnumNodeStatus.Completed, "SD", EnumDeviceStatus.Idle, "SD");
    }

    /**
     * @param good
     * @param waste
     */
    protected void runSinglePhase(int good, int waste)
    {
        VElement vResLinks=theNode.getResourceLinks(null);
        int siz= vResLinks!=null ? vResLinks.size() : 0;
        for(int i=0;i<siz;i++)
        {
            JDFResourceLink rl=(JDFResourceLink)vResLinks.elementAt(i);
            theStatusCounter.addPhase(rl.getrRef(), good, waste);
        }
    }


    /** 
     * do the last steps prior to processrun
     */
    protected void finalize()
    {
        int siz =amountLinks==null ? 0 : amountLinks.size();
        for(int i=0;i<siz;i++)
        {
            theStatusCounter.setResourceAudit(amountLinks.elementAt(i), EnumReason.ProcessResult);
        }
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
        VElement nodeLinks = getNodeLinks();
        theStatusCounter.setActiveNode(theNode, null, nodeLinks);

    }

    /**
     * @return
     */
    protected VElement getNodeLinks()
    {
        VElement nodeLinks=null;
        if(amountLinks!=null)
        {
            nodeLinks=new VElement();
            VElement resLinks=theNode.getResourceLinks(null);
            for(int i=0;i<amountLinks.size();i++)
            {
                for(int j=0;j<resLinks.size();j++)
                {
                    JDFResourceLink rl=(JDFResourceLink) resLinks.elementAt(j);
                    if(rl.matchesString(amountLinks.elementAt(i)))
                        nodeLinks.add(rl);
                }
            }
        }
        return nodeLinks;
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

    /**
     * @param string
     * @param i
     * @param b
     */
    public void write2File(String file, int indent)
    {
        theNode.getOwnerDocument_KElement().write2File(file, indent, indent==0);

    }

    public StatusCounter getStatusCounter()
    {
        return theStatusCounter;
    }

    public static String getDeviceURL()
    {
        return deviceURL;
    }

    public static void setDeviceURL(String deviceURL)
    {
        BaseGoldenTicket.deviceURL = deviceURL;
    }

    public static String getMisURL()
    {
        return misURL;
    }

    public static void setMisURL(String misURL)
    {
        BaseGoldenTicket.misURL = misURL;
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


}
