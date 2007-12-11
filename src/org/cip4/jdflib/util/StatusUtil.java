/*
 *
 * The CIP4 Software License, Version 1.0
 *
 *
 * Copyright (c) 2001-2006 The International Cooperation for the Integration of
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
/**
 * Created on Jul 5, 2006, 11:45:44 AM
 * org.cip4.jdflib.util.MimeUtil.java
 * Project Name: mimeutil
 */
package org.cip4.jdflib.util;

import org.cip4.jdflib.auto.JDFAutoDeviceInfo.EnumDeviceStatus;
import org.cip4.jdflib.auto.JDFAutoResourceAudit.EnumReason;
import org.cip4.jdflib.core.AttributeName;
import org.cip4.jdflib.core.ElementName;
import org.cip4.jdflib.core.JDFDoc;
import org.cip4.jdflib.core.JDFException;
import org.cip4.jdflib.core.JDFResourceLink;
import org.cip4.jdflib.core.VElement;
import org.cip4.jdflib.core.JDFAudit.EnumAuditType;
import org.cip4.jdflib.core.JDFElement.EnumNodeStatus;
import org.cip4.jdflib.datatypes.JDFAttributeMap;
import org.cip4.jdflib.datatypes.VJDFAttributeMap;
import org.cip4.jdflib.jmf.JDFDeviceInfo;
import org.cip4.jdflib.jmf.JDFJMF;
import org.cip4.jdflib.jmf.JDFJobPhase;
import org.cip4.jdflib.jmf.JDFMessage;
import org.cip4.jdflib.jmf.JDFResourceInfo;
import org.cip4.jdflib.jmf.JDFResourceQuParams;
import org.cip4.jdflib.jmf.JDFSignal;
import org.cip4.jdflib.jmf.JDFMessage.EnumType;
import org.cip4.jdflib.node.JDFNode;
import org.cip4.jdflib.pool.JDFAuditPool;
import org.cip4.jdflib.resource.JDFPhaseTime;
import org.cip4.jdflib.resource.JDFProcessRun;
import org.cip4.jdflib.resource.JDFResourceAudit;
import org.cip4.jdflib.resource.JDFResource.EnumPartIDKey;

//TODO add time related metadata
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
/**
 * Utility class for status JDF and JMF
 *
 * @author prosirai
 * @deprecated - use StatusCounter
 */
public class StatusUtil {

    JDFNode m_Node;
    private JDFDoc docJMFPhaseTime;
    private JDFDoc docJMFResource;
    protected VJDFAttributeMap m_vPartMap;
    private String m_deviceID=null;
    private LinkAmount[] vLinkAmount=null;

    /**
     * construct a StatusUtil for a node n
     * @param node the JDFNode that is being processed
     * @param vPartMap the map of Parts that is being processed excluding the waste partition
     * @param vResLinks the reslinks that are tracked for amount handling
     */
    public StatusUtil(JDFNode node, VJDFAttributeMap vPartMap, VElement vResLinks)
    {
        m_Node=node;
        m_vPartMap=vPartMap;
        if(m_vPartMap==null)
        {
            m_vPartMap = m_Node.getPartMapVector();
        }
        setUpResLinks(vResLinks);
    }

    /**
     * get the matching AmountBag out of an array
     * @param refID the refID of the bag - this MUST match the refID of a ResourceLink
     * @param bags the array of bags to search in
     * @return the AmountBag with matching refID, null if none found or bags is null
     */
    public static AmountBag getBag(String refID, AmountBag[] bags)
    {
        if(bags==null || refID==null) {
			return null;
		}
        for(int i=0;i<bags.length;i++)
        {
            if(bags[i].refID.equals(refID)) {
				return bags[i];
			}
        }
        return null;
    }
    /**
     * get the matching LinkAmount out of this
     * @param refID the refID of the bag - this MUST match the refID of a ResourceLink
     * @return the LinkAmount with matching refID, null if none found or bags is null
     */
    public LinkAmount getLinkAmount(String refID)
    {
        if(vLinkAmount==null || refID==null) {
			return null;
		}
        for(int i=0;i<vLinkAmount.length;i++)
        {
            if(vLinkAmount[i].rl.getrRef().equals(refID)) {
				return vLinkAmount[i];
			}
        }
        return null;
    }

    /**
     * get the refID of the first resource, i.e. the Resource that is being tracked in status messages
     * @return the rRef of the prime resource link
     */
    public String getFirstRefID()
    {
        if(vLinkAmount==null || vLinkAmount.length==0) {
			return null;
		}
        return vLinkAmount[0].rl.getrRef();

    }
    /**
     * @param resLinks
     */
    private void setUpResLinks(VElement resLinks)
    {
        if(resLinks==null || resLinks.size()==0) {
			return;
		}
        vLinkAmount=new LinkAmount[resLinks.size()];
        for(int i=0;i<vLinkAmount.length;i++)
        {
            vLinkAmount[i]=new LinkAmount((JDFResourceLink)resLinks.elementAt(i));
        }

    }

    /**
     * Set the Status and StatusDetails of this node
     * update the PhaseTime audit or append a new phasetime as appropriate
     * also prepare a status JMF
     *
     * @param nodeStatus the new status of the node
     * @param nodeStatusDetails the new statusDetails of the node
     * @param deviceStatus the new status of the device
     * @param deviceStatusDetails the new statusDetails of the device
     * @param vPartMap the vector of parts to that should be set
     * @param vResLink the resourcelinks that are used to fill the various amount attributes in jobphase and phasetime
     *
     */
    public void setPhase(EnumNodeStatus nodeStatus, String nodeStatusDetails, EnumDeviceStatus deviceStatus, String deviceStatusDetails, AmountBag[] amounts)
    {
        docJMFPhaseTime=new JDFDoc(ElementName.JMF);
        JDFJMF jmf=docJMFPhaseTime.getJMFRoot();
        docJMFResource=new JDFDoc(ElementName.JMF);
        JDFJMF jmfRes=docJMFResource.getJMFRoot();

        final AmountBag ab=getBag(getFirstRefID(), amounts);
        final LinkAmount la=getLinkAmount(getFirstRefID());
        final AmountBag lastAb=la==null ? null : la.lastBag;

        JDFAuditPool ap=m_Node.getCreateAuditPool();
        // TODO rethink when to send 2 phases
        JDFPhaseTime pt1= ap.getLastPhase(m_vPartMap,null);
        JDFPhaseTime pt2=pt1;
        boolean bEnd=nodeStatus.equals(EnumNodeStatus.Completed) || nodeStatus.equals(EnumNodeStatus.Aborted);

        pt2=ap.setPhase(nodeStatus,nodeStatusDetails,m_vPartMap);
        if(bEnd )
        {
            JDFProcessRun pr=(JDFProcessRun) ap.addAudit(EnumAuditType.ProcessRun, null);
            pr.setPartMapVector(m_vPartMap);
            VElement audits=ap.getAudits(EnumAuditType.PhaseTime, null, m_vPartMap);
            for(int i=0;i<audits.size();i++)
            {
                pr.addPhase((JDFPhaseTime)audits.elementAt(i));
            }
            pr.setEndStatus(nodeStatus);
        }

        if(pt1!=null && pt2!=pt1) // we explicitly added a new phasetime audit, thus we need to add a closing JMF for the original jobPhase
        {

            JDFSignal s=(JDFSignal)jmf.appendMessageElement(JDFMessage.EnumFamily.Signal,JDFMessage.EnumType.Status);
            JDFDeviceInfo deviceInfo = s.appendDeviceInfo();
            
            JDFJobPhase jp=deviceInfo.createJobPhaseFromPhaseTime(pt1);
            jp.setJobID(m_Node.getJobID(true));
            jp.setJobPartID(m_Node.getJobPartID(false));
            setJobPhaseAmounts(lastAb, jp);

            if(m_deviceID!=null) 
            {
				deviceInfo.setDeviceID(m_deviceID);
			}
        }

        if(pt2!=null)
        {
            JDFSignal s=(JDFSignal)jmf.appendMessageElement(JDFMessage.EnumFamily.Signal,JDFMessage.EnumType.Status);
            JDFDeviceInfo deviceInfo = s.appendDeviceInfo();
            if(!bEnd) // don't write a jobphase for an idle device
            {
                JDFJobPhase jp=deviceInfo.createJobPhaseFromPhaseTime(pt2);
                setJobPhaseAmounts(ab, jp);
            }

            deviceInfo.setDeviceStatus(deviceStatus);
            deviceInfo.setStatusDetails(deviceStatusDetails);
            deviceInfo.setDeviceID(m_deviceID);
            m_Node.setPartStatus(m_vPartMap,nodeStatus);
            getVResLink(amounts,2);// update the nodes links

            generateResourceSignal(amounts, jmfRes);

            if(bEnd)
            {
                pt2.deleteNode(); // zapp the last phasetime
            }
            else
            {
                pt2.setLinks(getVResLink(amounts,1));
                pt2.eraseEmptyAttributes(true);
            }
        }

        // cleanup!
        if(vLinkAmount!=null)
        {
            for(int i=0;i<vLinkAmount.length;i++)
            {
                String refID=vLinkAmount[i].rl.getrRef();
                AmountBag bag=getBag(refID, amounts);
                vLinkAmount[i].lastBag=new AmountBag(bag);
            }
        }
        jmf.eraseEmptyAttributes(true);
    }

    /**
     * @param amounts
     * @param jmfRes
     */
    private void generateResourceSignal(AmountBag[] amounts, JDFJMF jmfRes)
    {
        if(amounts!=null)
        {
            VElement vResResourceInfo=getVResLink(amounts, 3);

            JDFSignal sig=jmfRes.appendSignal(EnumType.Resource);
            JDFResourceQuParams rqp=sig.appendResourceQuParams();
            rqp.setJDF(m_Node);
            rqp.setExact(false);
            boolean bAllExact=true;

            for(int i=0;i<vResResourceInfo.size();i++)
            {
                JDFResourceInfo ri=sig.appendResourceInfo();
                final JDFResourceLink rl = (JDFResourceLink)vResResourceInfo.elementAt(i);
                LinkAmount la=getLinkAmount(rl.getrRef());
                boolean bExact=la.bCopyResInfo;
                bAllExact=bAllExact && bExact;
                rqp.setExact(bExact);
                ri.setLink(rl,rqp);
            }
            rqp.setExact(bAllExact);
        }
    }

    /**
     * @param lastAb
     * @param jp
     */
    private void setJobPhaseAmounts(final AmountBag lastAb, JDFJobPhase jp)
    {
        if(lastAb==null) {
			return;
		}


        LinkAmount la=getLinkAmount(lastAb.refID);
        if(la==null) {
			return;
		}

        if(la.bTrackWaste)
        {
            if(lastAb.phaseAmount!=0) {
				jp.setPhaseAmount(lastAb.phaseAmount);
			}
            if(lastAb.totalAmount!=0) {
				jp.setAmount(lastAb.totalAmount);
			}
            if(lastAb.phaseWaste!=0) {
				jp.setPhaseWaste(lastAb.phaseWaste);
			}
            if(lastAb.totalWaste!=0) {
				jp.setWaste(lastAb.totalWaste);
			}
        }
        else
        {
            if((lastAb.phaseAmount + lastAb.phaseWaste)!=0) {
				jp.setPhaseAmount(lastAb.phaseAmount+lastAb.phaseWaste);
			}
            if((lastAb.totalAmount+lastAb.totalWaste)!=0) {
				jp.setAmount(lastAb.totalAmount+lastAb.totalWaste);
			}

        }
        double total=0;

        total=la.startAmount;
        if(total!=0)
        {
            jp.setTotalAmount(total);
            jp.setPercentCompleted(lastAb.totalAmount/total*100.0);
        }
    }


    /**
     * @param resLink
     * @return
     */
    private VElement getVResLink(AmountBag[] amounts, int n)
    {
        if(amounts==null && vLinkAmount==null) {
			return null;
		}
        if(vLinkAmount==null || amounts==null || vLinkAmount.length!=amounts.length) {
			throw new JDFException("incoherent resLink sizes");
		}
        VElement vRet=new VElement();
        for(int i=0;i<vLinkAmount.length;i++)
        {
            LinkAmount la=vLinkAmount[i];
            if(n==1) {
				vRet.add(la.getPhaseTimeLink(getBag(la.rl.getrRef(),amounts)));
			}
            if(n==2) {
				vRet.add(la.updateNodeLink(getBag(la.rl.getrRef(),amounts)));
			}
            if(n==3) {
				vRet.add(la.getResourceInfoLink(getBag(la.rl.getrRef(),amounts)));
			}
        }
        return vRet;
    }

    /**
     * @return the docJMFPhaseTime
     */
    public JDFDoc getDocJMFPhaseTime()
    {
        return docJMFPhaseTime;
    }

    /**
     * @return the docJMFResource
     */
    public JDFDoc getDocJMFResource()
    {
        return docJMFResource;
    }

    ///////////////////////////////////////////////////////////////////////
    /**
     * container class to set amounts and waste in phaseTime
     */
    public class AmountBag
    {
        /**
         * refID of the resourceLink to set
         */
        public String refID;
        public double totalAmount;
        public double phaseAmount;
        public double totalWaste;
        public double phaseWaste;


        public String toString()
        {
            return "[AmountBag refID="+refID+" totalAmount="+totalAmount+" phaseAmount="+phaseAmount+" totalWaste="+totalWaste+" phaseWaste="+phaseWaste+" ]";
        }

        /**
         *
         * @param rl resourceLink to the resource that is being counted
         */
        public AmountBag(JDFResourceLink rl)
        {
           this(rl.getrRef());
        }
        /**
         *
         * @param _refID refID of the resource that is being counted
         */
        public AmountBag(String _refID)
        {
            refID=_refID;
            reset();
        }

        /**
         *
         */
        public void reset()
        {
            totalAmount=0;
            phaseAmount=0;
            totalWaste=0;
            phaseWaste=0;
        }

        /**
         * copy ctor
         * @param bag
         */
        public AmountBag(AmountBag bag)
        {
            refID=bag.refID;
            totalAmount=bag.totalAmount;
            phaseAmount=bag.phaseAmount;
            totalWaste=bag.totalWaste;
            phaseWaste=bag.phaseWaste;
        }

        /**
         * @param iLoop
         * @param j
         * @param b
         */
        public void addPhase(double amount, double waste, boolean bNewPhase)
        {
            totalAmount+=amount;
            totalWaste+=waste;
            if(bNewPhase)
            {
                phaseAmount=amount;
                phaseWaste=waste;
            }
            else
            {
                phaseAmount+=amount;
                phaseWaste+=waste;
            }
        }
    }

    ///////////////////////////////////////////////////////////////////////

    private class LinkAmount
    {
        double startAmount=0;
        double startWaste=0;
        JDFResourceLink rl;
        AmountBag lastBag;
        public boolean bTrackWaste=false;
        public boolean bCopyResInfo=false;

        LinkAmount(JDFResourceLink _rl)
        {
            JDFNode dump=new JDFDoc("JDF").getJDFRoot();
            dump.appendResourceLinkPool().copyElement(_rl, null);
            dump.appendResourcePool().copyElement(_rl.getTarget(), null);
            rl=(JDFResourceLink)dump.getResourceLinkPool().getElement(_rl.getNodeName(), null, 0);
            JDFAttributeMap map=(m_vPartMap==null || m_vPartMap.size()==0) ? null : m_vPartMap.elementAt(0);
            startAmount=rl.getAmount(map);
            if(startAmount==-1)
            {
                map=new JDFAttributeMap(map);
                map.put(EnumPartIDKey.Condition, "Good");
                startAmount=rl.getAmount(map);
                if(startAmount==-1) {
					startAmount=0;
				}
                map.put(EnumPartIDKey.Condition, "Waste");
                startWaste=rl.getAmount(map);
                if(startWaste==-1) {
					startWaste=0;
				}
            }
        }

        /**
         * @param bag
         * @return
         */
        public JDFResourceLink updateNodeLink(AmountBag bag)
        {
            final JDFResourceLink nodeLink=m_Node.getLink(0,null,new JDFAttributeMap(AttributeName.RREF,rl.getrRef()),null);
            if(bag!=null)
            {
                VJDFAttributeMap vMap=new VJDFAttributeMap(m_vPartMap);
                if(vMap.size()==0) {
					vMap.add(new JDFAttributeMap());
				}
                    if(nodeLink!=null)
                    {
                        if(bTrackWaste)
                        {
                            vMap.put(EnumPartIDKey.Condition, "Good");
                            nodeLink.setAmountPoolAttribute(AttributeName.ACTUALAMOUNT, StringUtil.formatDouble(bag.totalAmount), null, vMap);
                            vMap.put(EnumPartIDKey.Condition, "Waste");
                            nodeLink.setAmountPoolAttribute(AttributeName.ACTUALAMOUNT, StringUtil.formatDouble(bag.totalWaste), null, vMap);
                        }
                        else
                        {
                            nodeLink.setAmountPoolAttribute(AttributeName.ACTUALAMOUNT, StringUtil.formatDouble(bag.totalAmount+bag.totalWaste), null, vMap);
                        }
                    }
            }
            return nodeLink;
        }

        /**
         * @return
         */
        public JDFResourceLink getPhaseTimeLink(AmountBag bag)
        {
            cleanAmounts();
            if(bag!=null)
            {
                VJDFAttributeMap vMap=new VJDFAttributeMap(m_vPartMap);
                if(vMap.size()==0) {
					vMap.add(new JDFAttributeMap());
				}
                if(bTrackWaste)
                {
                    vMap.put(EnumPartIDKey.Condition, "Good");
                    if(bag.phaseAmount!=0) {
						rl.setAmountPoolAttribute(AttributeName.ACTUALAMOUNT, StringUtil.formatDouble(bag.phaseAmount), null, vMap);
					}
                    if(startAmount!=0) {
						rl.setAmountPoolAttribute(AttributeName.AMOUNT, StringUtil.formatDouble(startAmount), null, vMap);
					}
                    vMap.put(EnumPartIDKey.Condition, "Waste");
                    if(bag.phaseWaste!=0) {
						rl.setAmountPoolAttribute(AttributeName.ACTUALAMOUNT, StringUtil.formatDouble(bag.phaseWaste), null, vMap);
					}
                    if(startWaste!=0) {
						rl.setAmountPoolAttribute(AttributeName.AMOUNT, StringUtil.formatDouble(startWaste), null, vMap);
					}
                }
                else
                {
                    if(bag.phaseAmount + bag.phaseWaste !=0) {
						rl.setAmountPoolAttribute(AttributeName.ACTUALAMOUNT, StringUtil.formatDouble(bag.phaseAmount+bag.phaseWaste), null, vMap);
					}
                    if(startAmount+startWaste!=0) {
						rl.setAmountPoolAttribute(AttributeName.AMOUNT, StringUtil.formatDouble(startAmount+startWaste), null, vMap);
					}
                }
            }
            return rl;
        }

        /**
         * @return
         */
        public JDFResourceLink getResourceAuditLink(AmountBag bag)
        {
            cleanAmounts();
            if(bag!=null)
            {
                VJDFAttributeMap vMap=new VJDFAttributeMap(m_vPartMap);
                if(vMap.size()==0) {
					vMap.add(new JDFAttributeMap());
				}
                if(bTrackWaste)
                {
                    vMap.put(EnumPartIDKey.Condition, "Good");
                    if(bag.totalAmount!=0) {
						rl.setAmountPoolAttribute(AttributeName.ACTUALAMOUNT, StringUtil.formatDouble(bag.totalAmount), null, vMap);
					}
                    if(startAmount!=0) {
						rl.setAmountPoolAttribute(AttributeName.AMOUNT, StringUtil.formatDouble(startAmount), null, vMap);
					}
                    vMap.put(EnumPartIDKey.Condition, "Waste");
                    if(bag.totalWaste!=0) {
						rl.setAmountPoolAttribute(AttributeName.ACTUALAMOUNT, StringUtil.formatDouble(bag.totalWaste), null, vMap);
					}
                    if(startWaste!=0) {
						rl.setAmountPoolAttribute(AttributeName.AMOUNT, StringUtil.formatDouble(startWaste), null, vMap);
					}
                }
                else
                {
                    if(bag.totalAmount + bag.totalWaste !=0) {
						rl.setAmountPoolAttribute(AttributeName.ACTUALAMOUNT, StringUtil.formatDouble(bag.totalAmount+bag.totalWaste), null, vMap);
					}
                    if(startAmount+startWaste!=0) {
						rl.setAmountPoolAttribute(AttributeName.AMOUNT, StringUtil.formatDouble(startAmount+startWaste), null, vMap);
					}
                }
            }
            return rl;
        }
        /**
         * @return
         */
        public JDFResourceLink getResourceInfoLink(AmountBag bag)
        {
            cleanAmounts();
            if(bag!=null)
            {
                VJDFAttributeMap vMap=new VJDFAttributeMap(m_vPartMap);
                if(vMap.size()==0) {
					vMap.add(new JDFAttributeMap());
				}
                if(bTrackWaste)
                {
                    vMap.put(EnumPartIDKey.Condition, "Good");
                    if(bag.totalAmount!=0) {
						rl.setAmountPoolAttribute(AttributeName.ACTUALAMOUNT, StringUtil.formatDouble(bag.totalAmount), null, vMap);
					}
                    if(startAmount!=0) {
						rl.setAmountPoolAttribute(AttributeName.AMOUNT, StringUtil.formatDouble(startAmount), null, vMap);
					}
                    vMap.put(EnumPartIDKey.Condition, "Waste");
                    if(bag.totalWaste!=0) {
						rl.setAmountPoolAttribute(AttributeName.ACTUALAMOUNT, StringUtil.formatDouble(bag.totalWaste), null, vMap);
					}
                    if(startWaste!=0) {
						rl.setAmountPoolAttribute(AttributeName.AMOUNT, StringUtil.formatDouble(startWaste), null, vMap);
					}
                }
                else
                {
                    if(bag.totalAmount + bag.totalWaste !=0) {
						rl.setAmountPoolAttribute(AttributeName.ACTUALAMOUNT, StringUtil.formatDouble(bag.totalAmount+bag.totalWaste), null, vMap);
					}
                    if(startAmount+startWaste!=0) {
						rl.setAmountPoolAttribute(AttributeName.AMOUNT, StringUtil.formatDouble(startAmount+startWaste), null, vMap);
					}
                }
            }
            return rl;       }

        /**
         * @param rl2
         */
        private void cleanAmounts()
        {
            rl.removeAttribute(AttributeName.AMOUNT);
            rl.removeAttribute(AttributeName.ACTUALAMOUNT);
            rl.removeChild(ElementName.AMOUNTPOOL, null,0);
        }


    }

    /*
     * @return the m_deviceID
     */
    public String getDeviceID()
    {
        return m_deviceID;
    }

    /**
     * @param m_deviceid the m_deviceID to set
     */
    public void setDeviceID(String deviceid)
    {
        m_deviceID = deviceid;
    }

    /**
     * set waste tracking on or off for the resourcelink rl
     * @param rl the resourcelink to the resource to track
     * @param b tracking on or off
     */
    public void setTrackWaste(JDFResourceLink rl, boolean b)
    {
        LinkAmount la=getLinkAmount(rl.getrRef());
        if(la!=null) {
			la.bTrackWaste=b;
		}
    }

    /**
     * set copying the resource into resourceInfo on or off for the resourcelink rl
     * @param rl the resourcelink to the resource to copy
     * @param b tracking on or off
     */
    public void setCopyResInResInfo(JDFResourceLink rl, boolean b)
    {
        LinkAmount la=getLinkAmount(rl.getrRef());
        if(la!=null) {
			la.bCopyResInfo=b;
		}
    }

    /**
     * @param bag
     * @return JDFResourceAudit the generated audit
     */
    public JDFResourceAudit setResourceAudit(AmountBag bag, EnumReason reason)
    {
        JDFAuditPool ap=m_Node.getCreateAuditPool();

       JDFResourceAudit ra=ap.addResourceAudit(null);
       ra.setContentsModified(false);
       ra.setReason(reason);

       final LinkAmount la=getLinkAmount(bag.refID);
       ra.copyElement(la.getResourceAuditLink(bag), null);
       ra.setPartMapVector(m_vPartMap);

       return ra;
    }

    /**
     *
     */
    public JDFProcessRun setProcessResult(EnumNodeStatus endStatus)
    {
        JDFAuditPool ap=m_Node.getCreateAuditPool();

        JDFProcessRun pr=ap.addProcessRun(endStatus, null, m_vPartMap);
        return pr;

    }


}