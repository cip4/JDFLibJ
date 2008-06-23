/*
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
/**
==========================================================================
class JDFJobPhase extends JDFResource
==========================================================================
@COPYRIGHT Heidelberger Druckmaschinen AG, 1999-2001
ALL RIGHTS RESERVED
@Author: sabjon@topmail.de   using a code generator
Warning! very preliminary test version. Interface subject to change without prior notice!
Revision history:    ...
 **/

package org.cip4.jdflib.jmf;

import org.apache.xerces.dom.CoreDocumentImpl;
import org.cip4.jdflib.auto.JDFAutoJobPhase;
import org.cip4.jdflib.auto.JDFAutoQueueEntry.EnumQueueEntryStatus;
import org.cip4.jdflib.core.AttributeName;
import org.cip4.jdflib.core.ElemInfoTable;
import org.cip4.jdflib.core.ElementInfo;
import org.cip4.jdflib.core.ElementName;
import org.cip4.jdflib.core.KElement;
import org.cip4.jdflib.datatypes.JDFAttributeMap;
import org.cip4.jdflib.datatypes.VJDFAttributeMap;
import org.cip4.jdflib.node.JDFNode;
import org.cip4.jdflib.node.JDFNode.NodeIdentifier;
import org.cip4.jdflib.resource.JDFModulePhase;
import org.cip4.jdflib.resource.JDFModuleStatus;
import org.cip4.jdflib.util.JDFDate;

//----------------------------------
/**
 * describes the actual status of jobs in a device
 * 
 * Note that the old EnumStatus local class has been move to @see JDFNode.EnumNodeStatus
 */
public class JDFJobPhase extends JDFAutoJobPhase
{
    private static final long serialVersionUID = 1L;

    /**
     * Constructor for JDFJobPhase
     * @param myOwnerDocument
     * @param qualifiedName
     */
    public JDFJobPhase(
            CoreDocumentImpl myOwnerDocument,
            String qualifiedName)
    {
        super(myOwnerDocument, qualifiedName);
    }

    /**
     * Constructor for JDFJobPhase
     * @param myOwnerDocument
     * @param myNamespaceURI
     * @param qualifiedName
     */
    public JDFJobPhase(
            CoreDocumentImpl myOwnerDocument,
            String myNamespaceURI,
            String qualifiedName)
    {
        super(myOwnerDocument, myNamespaceURI, qualifiedName);
    }

    /**
     * Constructor for JDFJobPhase
     * @param myOwnerDocument
     * @param myNamespaceURI
     * @param qualifiedName
     * @param myLocalName
     */
    public JDFJobPhase(
            CoreDocumentImpl myOwnerDocument,
            String myNamespaceURI,
            String qualifiedName,
            String myLocalName)
    {
        super(myOwnerDocument, myNamespaceURI, qualifiedName, myLocalName);
    }

    /**
     * toString()
     * @see org.cip4.jdflib.auto.JDFAutoJobPhase#toString()
     * @return String
     */
    public String toString()
    {
        return "JDFJobPhase[  --> " + super.toString() + " ]";
    }

    private static ElemInfoTable[] elemInfoTable = new ElemInfoTable[1];
    static
    {
        elemInfoTable[0] = new ElemInfoTable(ElementName.JDF, 0x33333333);
    }

    protected ElementInfo getTheElementInfo()
    {
        return new ElementInfo(super.getTheElementInfo(), elemInfoTable);
    }

    /**
     * Returns detailed status information.
     * @return String
     */
    public String getStatusDetails()
    {
        return getAttribute(AttributeName.STATUSDETAILS);
    }

    /**
     * Method getQueueEntryID.
     * @return String
     */
    public String getQueueEntryID()
    {
        return getInheritedStatusQuParamsAttribute(AttributeName.QUEUEENTRYID,null);
    }

    /**
     * Method getJobID.
     * @return String
     */
    public String getJobID()
    {
        return getInheritedStatusQuParamsAttribute(AttributeName.JOBID,null);
    }
    /**
     * Method getJobID.
     * @return String
     */
    public String getJobPartID()
    {
        return getInheritedStatusQuParamsAttribute(AttributeName.JOBPARTID,null);
    }

    /**
     * get part map vector
     * @return VJDFAttributeMap: vector of attribute maps, one for each part
     */
    public VJDFAttributeMap getPartMapVector()
    {
        return super.getPartMapVector();
    }

    /**
     * set all parts to those defined in vParts
     * @param vParts vector of attribute maps for the parts
     */
    public void setPartMapVector(VJDFAttributeMap vParts)
    {
        super.setPartMapVector(vParts);
    }

    /**
     * set part to the one defined in mPart
     * @param mPart attribute map for the part to set
     */
    public void setPartMap(JDFAttributeMap mPart)
    {
        super.setPartMap(mPart);
    }

    /**
     * remove the part defined in mPart
     * @param mPart attribute map for the part to remove
     */
    public void removePartMap(JDFAttributeMap mPart)
    {
        super.removePartMap(mPart);
    }

    /**
     * check whether the part defined in mPart is included
     * @param mPart attribute map for the part to remove
     * @return boolean - returns true if the part exists
     */
    public boolean hasPartMap(JDFAttributeMap mPart)
    {
        return super.hasPartMap(mPart);
    }

    /**
     * get a node, create if it doesn't exist
     * @return the node
     */
    public JDFNode getCreateNode() 
    {
        return (JDFNode) getCreateElement_KElement(ElementName.JDF, null, 0);
    }

    /**
     * append a node
     * @return the appended node
     */
    public JDFNode appendNode() 
    {
        return (JDFNode) appendElementN(ElementName.JDF, 1, null);
    }

    /**
     * get node
     * @return the node
     */
    public JDFNode getNode() {
        return (JDFNode) getElement(ElementName.JDF, null, 0);
    }

    /**
     * get the {@link JDFStatusQuParams} that apply to the jobphase
     * @return
     */
    public JDFStatusQuParams getStatusQuParams()
    {
        KElement parent=getParentNode_KElement();
        if(!(parent instanceof JDFDeviceInfo))
            return null;
        parent=parent.getParentNode_KElement();
        if(!(parent instanceof JDFMessage))
            return null;
        return ((JDFMessage)parent).getStatusQuParams();
        
    }
    /**
     * gets the NodeIdetifier that matches this
     * @return
     */
    public NodeIdentifier getIdentifier()
    {
        NodeIdentifier ni= new NodeIdentifier();
        ni.setTo(getJobID(), getJobPartID(), getPartMapVector());
        return ni;
    }
   
    private String getInheritedStatusQuParamsAttribute(String key,String nameSpaceURI)
    {
        String val=getAttribute(key, nameSpaceURI, null);
        if(val!=null)
            return val;
        JDFStatusQuParams sqp=getStatusQuParams();
        if(sqp==null)
            return null;
        return sqp.getAttribute(key, nameSpaceURI, null);
    }

    /**
     * creates a new ModuleStatus in this based on the values in mp
     * generally used to create messages from audits
     * 
     * @param mp the modulephase to copy
     * @return the new ModuleStatus element
     * 
     */
    public JDFModuleStatus createModuleStatusFromModulePhase(JDFModulePhase mp)
    {
        if(mp==null)
            return null;
        JDFModuleStatus ms=appendModuleStatus();
        ms.copyAttribute(AttributeName.MODULETYPE, mp);
        ms.copyAttribute(AttributeName.MODULEINDEX, mp);
        ms.copyAttribute(AttributeName.MODULEID, mp);

        return ms;
    }
    
    /**
     * return the differential amount produced between this phase and lastphase
     * @param lastphase the phase
     * @return
     */
    public double getAmountDifference(JDFJobPhase lastphase)
    {
        if(lastphase!=null)
        {
            JDFDate startTime=getPhaseStartTime();
            JDFDate lastStartTime=lastphase.getPhaseStartTime();
            if(startTime!=null &&startTime.equals(lastStartTime))
            {
                return getPhaseAmount()-lastphase.getPhaseAmount();
            }
        }
        return getPhaseAmount();
    }
    /**
     * return the differential waste amount produced between this phase and lastphase
     * @param lastphase
     * @return
     */
    public double getWasteDifference(JDFJobPhase lastphase)
    {
        if(lastphase!=null)
        {
            JDFDate startTime=getPhaseStartTime();
            JDFDate lastStartTime=lastphase.getPhaseStartTime();
            if(startTime!=null &&startTime.equals(lastStartTime))
            {
                return getPhaseWaste()-lastphase.getPhaseWaste();
            }
        }
        return getPhaseWaste();
    }

    /**
     * returns the phase amount, defaults to amount if not specified
     */
    @Override
    public double getPhaseAmount()
    {
        if(hasAttribute(AttributeName.PHASEAMOUNT))
            return super.getPhaseAmount();
        return super.getAmount();
    }

    /**
     * returns the phase starttime, defaults to starttime if not specified
     */
    @Override
    public JDFDate getPhaseStartTime()
    {
        if(hasAttribute(AttributeName.PHASESTARTTIME))
            return super.getPhaseStartTime();
        return super.getStartTime();
    }

    /**
     * returns the phase waste amount, defaults to waste if not specified
     */
    @Override
    public double getPhaseWaste()
    {
        if(hasAttribute(AttributeName.PHASEWASTE))
            return super.getPhaseWaste();
        return super.getWaste();
    }

    /**
     * @return the queueentry status that corresponds to the status of this
     */
    public EnumQueueEntryStatus getQueueEntryStatus()
    {
       return EnumNodeStatus.getQueueEntryStatus(getStatus()); 
    }
}


