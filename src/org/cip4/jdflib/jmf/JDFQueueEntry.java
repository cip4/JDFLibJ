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
==========================================================================
class JDFQueueEntry extends JDFResource
==========================================================================
@COPYRIGHT Heidelberger Druckmaschinen AG, 1999-2001
ALL RIGHTS RESERVED
@Author: sabjon@topmail.de   using a code generator
Warning! very preliminary test version. Interface subject to change without prior notice!
Revision history:    ...
 **/


package org.cip4.jdflib.jmf;

import org.apache.xerces.dom.CoreDocumentImpl;
import org.cip4.jdflib.auto.JDFAutoQueueEntry;
import org.cip4.jdflib.core.AttributeName;
import org.cip4.jdflib.core.ElementName;
import org.cip4.jdflib.core.JDFNodeInfo;
import org.cip4.jdflib.core.KElement;
import org.cip4.jdflib.datatypes.JDFAttributeMap;
import org.cip4.jdflib.datatypes.VJDFAttributeMap;
import org.cip4.jdflib.node.JDFNode;
import org.cip4.jdflib.node.JDFNode.NodeIdentifier;
import org.cip4.jdflib.util.ContainerUtil;



//----------------------------------
public class JDFQueueEntry extends JDFAutoQueueEntry
{
    private static final long serialVersionUID = 1L;

    /**
     * Constructor for JDFQueueEntry
     * @param myOwnerDocument
     * @param qualifiedName
     */
    public JDFQueueEntry(
            CoreDocumentImpl myOwnerDocument,
            String qualifiedName)
    {
        super(myOwnerDocument, qualifiedName);
    }

    /**
     * Constructor for JDFQueueEntry
     * @param myOwnerDocument
     * @param myNamespaceURI
     * @param qualifiedName
     */
    public JDFQueueEntry(
            CoreDocumentImpl myOwnerDocument,
            String myNamespaceURI,
            String qualifiedName)
    {
        super(myOwnerDocument, myNamespaceURI, qualifiedName);
    }

    /**
     * Constructor for JDFQueueEntry
     * @param myOwnerDocument
     * @param myNamespaceURI
     * @param qualifiedName
     * @param myLocalName
     */
    public JDFQueueEntry(
            CoreDocumentImpl myOwnerDocument,
            String myNamespaceURI,
            String qualifiedName,
            String myLocalName)
    {
        super(myOwnerDocument, myNamespaceURI, qualifiedName, myLocalName);
    }

    /**
     * toString()
     * @return String
     */
    public String toString()
    {
        return "JDFQueueEntry[  --> " + super.toString() + " ]";
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
     * set all parts to those define in vParts
     * @param vParts vector of attribute maps for the parts
     */
    public void setPartMapVector(VJDFAttributeMap vParts)
    {
        super.setPartMapVector(vParts);
    }

    /**
     * set all parts to those defined by mPart
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
     * return true if this qe matches the input node identifier
     * @param ni
     * @return
     */
    public boolean matchesNodeIdentifier(NodeIdentifier ni)
    {
        if(ni==null)
            return false;
        NodeIdentifier niThis= new NodeIdentifier(this);
        return niThis.equals(ni);
    }

    /**
     * check whether the part defined by mPart is included
     * @param mPart attribute map to look for
     * @return boolean - returns true if the part exists
     */
    public boolean hasPartMap(JDFAttributeMap mPart)
    {
        return super.hasPartMap(mPart);
    }

    /* (non-Javadoc)
     * @see org.cip4.jdflib.auto.JDFAutoQueueEntry#setPriority(int)
     */
    public void setPriority(int value)
    {
        int oldVal=getPriority();
        if(isAutomated()&& value!=oldVal)
        {
            final JDFQueue queue = (JDFQueue)getParentNode_KElement();
            synchronized(queue)
            {
                super.setPriority(value);
                sortQueue(getSortPriority(getQueueEntryStatus(),oldVal));
            }
        }
        else if(value!=oldVal) // non automated
        {
            super.setPriority(value);
        }
    }

    /**
     * sort this into the queue based on current values
     * assumes presorted queue
     * @param oldVal - the previous sort value
     */
    private void sortQueue(int oldVal)
    {
        int value=getSortPriority();
        final JDFQueue queue = (JDFQueue)getParentNode_KElement();
        synchronized(queue)
        {
            JDFQueueEntry qEBefore=null;
            if(value>oldVal)
            {
                JDFQueueEntry qEPrev=getPreviousQueueEntry();
                while(qEPrev!=null)
                {
                    if(qEPrev.getSortPriority()<=value)
                    {
                        qEBefore=qEPrev;
                        qEPrev= qEPrev.getPreviousQueueEntry();
                    }
                    else
                        break;
                }
                if(qEBefore!=null)
                    moveMe(qEBefore);
            }
            else
            {
                JDFQueueEntry qENext=getNextQueueEntry();
                while(qENext!=null)
                {
                      if(qENext.getSortPriority()>=value)
                      {
                        qEBefore=qENext;
                        qENext= qENext.getNextQueueEntry();
                      }
                      else
                          break;
                }
                if(qEBefore!=null)
                {
                    qEBefore = qEBefore.getNextQueueEntry();
                    moveMe(qEBefore);
                }
            }
        }
    }

    /**
     * @return true if the parent queue is automated
     */
    private boolean isAutomated()
    {
        KElement e=getParentNode_KElement();
        if(e instanceof JDFQueue)
        {
            return ((JDFQueue)e).isAutomated();
        }
        return false;
    }

    /**
     * sets the QueueEntry/@Status
     * if the queue is automated, also resorts the queue to reflect the new Status and sets the Queue/@Status based on
     * the maximum number of concurrently running jobs
     * @param value the queuentry status to set
     * 
     * @see org.cip4.jdflib.auto.JDFAutoQueueEntry#setQueueEntryStatus(org.cip4.jdflib.auto.JDFAutoQueueEntry.EnumQueueEntryStatus)
     */
    public void setQueueEntryStatus(EnumQueueEntryStatus value)
    {
        EnumQueueEntryStatus oldVal=getQueueEntryStatus();
        if(isAutomated() &&!ContainerUtil.equals(oldVal, value))
        {
            final JDFQueue queue = (JDFQueue)getParentNode_KElement();
            synchronized(queue)
            {
                super.setQueueEntryStatus(value);
                sortQueue(getSortPriority(oldVal,getPriority()));
                queue.setStatusFromEntries();
                if(isCompleted())
                    queue.cleanup();
            }
        }
        else if(!ContainerUtil.equals(oldVal, value)) // non automated
        {
            super.setQueueEntryStatus(value);
        }
    }

    /**
     * get the next sibling queueentry
     * @return
     */
   public JDFQueueEntry getNextQueueEntry()
    {
        return (JDFQueueEntry)getNextSiblingElement(ElementName.QUEUEENTRY,null);
    }
    /**
     * get the previous sibling queueentry
     * @return
     */
    public JDFQueueEntry getPreviousQueueEntry()
    {
        return (JDFQueueEntry)getPreviousSiblingElement(ElementName.QUEUEENTRY,null);
    }

    /**
     * @return true if this entry is completed
     */
    public boolean isCompleted()
    {
       EnumQueueEntryStatus status=getQueueEntryStatus();
       return // (status==null) || 
       EnumQueueEntryStatus.Completed.equals(status) || 
       EnumQueueEntryStatus.Removed.equals(status) || 
       EnumQueueEntryStatus.Aborted.equals(status);
    }
    /**
     * return a value based on QueueEntryStatus and Priority to sort the queue
     * @return int a priority for sorting - low = back
     */
    public int getSortPriority()
    {
       return getSortPriority(getQueueEntryStatus(),getPriority());
    }
    /**
     * return a value based on QueueEntryStatus and Priority to sort the queue
     * the status is the major order whereas the priority is used to order within regions of identical status
     * 
     * @return int a priority for sorting - 
     *      low value = back of queue, 
     *      high value = front of queue
     */
    public static int getSortPriority(EnumQueueEntryStatus status, int priority)
    {
        int sort=(status==null) ? 0 : 10000 - 1000 * status.getValue();
        sort+=priority;
        return sort;
    }
    
    /**
     * populates this queuentry with the relevant parameters extracted from a JDF
     * jobid, partmap, jobpartid etc.
     * 
     * @param jdf
     */
    public void setFromJDF(JDFNode jdf)
    {
        if(jdf==null)
            return;
        setJobID(jdf.getJobID(true));
        setJobPartID(jdf.getJobPartID(false));
        setPartMapVector(jdf.getPartMapVector());
        
        if(!hasAttribute(AttributeName.PRIORITY))
        {
            JDFNodeInfo ni=jdf.getInheritedNodeInfo("@"+AttributeName.PRIORITY);
            if(ni!=null)
                copyAttribute(AttributeName.PRIORITY, ni, null, null, null);
        }
         
        this.eraseEmptyAttributes(true);
    }

}



