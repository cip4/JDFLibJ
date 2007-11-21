/*
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
 * JDFQueue.java
 * @author Dietrich Mucha 
 * Copyright (C) 1999-2005 Heidelberger Druckmaschinen AG. All rights reserved.
 **/

package org.cip4.jdflib.jmf;

import java.util.Vector;

import org.apache.xerces.dom.CoreDocumentImpl;
import org.cip4.jdflib.auto.JDFAutoQueue;
import org.cip4.jdflib.auto.JDFAutoQueueEntry.EnumQueueEntryStatus;
import org.cip4.jdflib.core.AttributeName;
import org.cip4.jdflib.core.ElementName;
import org.cip4.jdflib.core.VElement;
import org.cip4.jdflib.core.VString;
import org.cip4.jdflib.datatypes.JDFAttributeMap;
import org.cip4.jdflib.datatypes.VJDFAttributeMap;
import org.cip4.jdflib.node.JDFNode.NodeIdentifier;


public class JDFQueue extends JDFAutoQueue
{
    private static final long serialVersionUID = 1L;
    /**
     * number of concurrent running entries 
     */
    private int maxRunningEntries=1;
    /**
     * max number of completed entries to retain 
     */
    private int maxCompletedEntries=0;
    private boolean automated=false;

    /**
     * Constructor for JDFQueue
     * @param myOwnerDocument
     * @param qualifiedName
     */
    public JDFQueue(CoreDocumentImpl myOwnerDocument, String qualifiedName)
    {
        super(myOwnerDocument, qualifiedName);
    }

    /**
     * Constructor for JDFQueue
     * @param myOwnerDocument
     * @param myNamespaceURI
     * @param qualifiedName
     */
    public JDFQueue( CoreDocumentImpl myOwnerDocument,String myNamespaceURI, String qualifiedName)
    {
        super(myOwnerDocument, myNamespaceURI, qualifiedName);
    }

    /**
     * Constructor for JDFQueue
     * @param myOwnerDocument
     * @param myNamespaceURI
     * @param qualifiedName
     * @param myLocalName
     */
    public JDFQueue(CoreDocumentImpl myOwnerDocument, String myNamespaceURI,String qualifiedName, String myLocalName)
    {
        super(myOwnerDocument, myNamespaceURI, qualifiedName, myLocalName);
    }

    /**
     * toString()
     * @return String
     */
    public String toString()
    {
        return "JDFQueue[  --> " + super.toString() + " ]";
    }



    /**
     * Method getEntryCount.
     * @return int quantity of QueueEntry children
     */
    public int getEntryCount()
    {
        return numChildElements(ElementName.QUEUEENTRY,null);
    }

    /**
     * Get a vector of all queueentry elements
     * @return VElement: the vector of queue entries
     */
    public VElement getQueueEntryVector()
    {
        return getChildrenByTagName(ElementName.QUEUEENTRY,null,null, false, true,0);
    }
    /**
     * Get a vector of queueentry elements with a given set of attributes and part maps
     * @return VElement: the vector of queue entries
     */
    public synchronized VElement getQueueEntryVector(JDFAttributeMap attMap, VJDFAttributeMap parts)
    {
        VElement v=getChildrenByTagName(ElementName.QUEUEENTRY,null,attMap, true, true,0);
        if(parts!=null)
        {
            for(int i=v.size()-1;i>=0;i--)
            {
                JDFQueueEntry qe=(JDFQueueEntry)v.elementAt(i);
                if(!parts.equals(qe.getPartMapVector()))
                {
                    v.remove(i);
                }
            }
        }
        return (v==null || v.size()==0) ? null : v;
    }

    /**
     * Method getEntry: find a queuentry by position
     * @param i the index of the queueentry
     * @return JDFQueueEntry
     * @deprecated use getQueueEntry(int)
     */
    public JDFQueueEntry getEntry(int i)
    {
        return getQueueEntry(i);
    }

    /**
     * Method findQueueEntries
     * <p>
     * default: findQueueEntries(jobID, jobPartID, new VJDFAttributeMap(), null)
     * 
     * @param strJobID     Job ID.
     * @param strJobPartID Job part ID.
     * @param vamParts     Partition to execute, may not be null
     * @param status       Queue Entry Status, null means any status.
     * @deprecated use getQueueEntryVector(map, partmapvector)
     * 
     * @return VString: vector of QueueEntry IDs
     */
    public VString findQueueEntries (String strJobID, String strJobPartID, 
            VJDFAttributeMap vamParts, EnumQueueEntryStatus status)
    {
        final VString vsQEntryIDs = new VString ();

        final int entryCount = getEntryCount();
        for (int i = 0; i < entryCount; i++)
        {
            final JDFQueueEntry entry = getQueueEntry(i);

            final String strQEJobID = entry.getJobID ();
            final String strQEJobPartID = entry.getJobPartID();

            final VJDFAttributeMap vamQEParts = entry.getPartMapVector ();

            final EnumQueueEntryStatus statusQE = entry.getQueueEntryStatus();

            if (strJobID.equals (strQEJobID)
                    && strJobPartID.equals (strQEJobPartID)
                    && vamParts.equals (vamQEParts))
            {
                if ((status == null) || (status.equals (statusQE)))
                {
                    vsQEntryIDs.appendUnique (entry.getQueueEntryID ());
                }
            }
        }

        return vsQEntryIDs;
    }

    /**
     * Find a queueEntry by QueueEntryID<br>
     * 
     * note that you may want to use the generic getChildByTagName with the appropriate 
     * attribute map, if you have more information available
     * 
     * @param strQEntryID the QueueEntryID of the requeste QueueEntry
     * @return the QueueEntry with QueueEntryID=strQEntryID, null if strQEntryID is null or empty string 
     * or the queueentry does not exist
     * @deprecated use getQueueEntry(id)
     */
    public JDFQueueEntry getEntry (String strQEntryID)
    {
        return getQueueEntry(strQEntryID);
    }

    /**
     * Find a queueEntry by QueueEntryID<br>
     * 
     * note that you may want to use the generic getChildByTagName with the appropriate 
     * attribute map, if you have more information available
     * 
     * @param strQEntryID the QueueEntryID of the requeste QueueEntry
     * @return the QueueEntry with QueueEntryID=strQEntryID, null if strQEntryID is null or empty string 
     * or the queueentry does not exist
     * 
     */
    public JDFQueueEntry getQueueEntry(String strQEntryID)
    {
        if(isWildCard(strQEntryID))
            return null;
        return (JDFQueueEntry) getChildByTagName(ElementName.QUEUEENTRY,null,0,new JDFAttributeMap(AttributeName.QUEUEENTRYID,strQEntryID),true,true);
    }
    /**
     * Find a queueEntry by NodeIdentifier (jobid, jobpartid, part)<br>
     * 
     * note that you may want to use the generic getChildByTagName with the appropriate 
     * attribute map, if you have more information available
     * 
     * @param nodeID the identifier - jobID, jobPartID, parts - of the qe
     * @param nSkip the number of nodes to skip, cout backwards if<0
     * @return the QueueEntry with matching jobID, jobPartID, parts, 
     * null if nodeID is null or empty string or the queueentry does not exist
     * 
     */
    public JDFQueueEntry getQueueEntry(NodeIdentifier nodeID, int nSkip)
    {
        if(nodeID==null)
            return null;
        VElement v=getQueueEntryVector();
        NodeIdentifier ni2=new NodeIdentifier();
        int siz=v==null ? 0 : v.size();
        int n=0;
        if(nSkip>=0)
        {
            for(int i=0;i<siz;i++)
            {
                JDFQueueEntry qe=(JDFQueueEntry) v.elementAt(i);
                ni2.setQueueEntry(qe);
                if(ni2.equals(nodeID)&&n++>=nSkip)
                    return qe;
            }
        }
        else
        {
            for(int i=siz-1;i>=0;i--)
            {
                JDFQueueEntry qe=(JDFQueueEntry) v.elementAt(i);
                ni2.setQueueEntry(qe);
                if(ni2.equals(nodeID)&&--n<=nSkip)
                    return qe;
            }
            
        }
        return null;
                
    }

    //////////////////////////////////////////////////////////////////////////

    /**
     * Find the position of a queueEntry by QueueEntryID
     * @param strQEntryID the QueueEntryID of the requeste QueueEntry
     * @return the position in the queue, -1 if not there
     */
    public int getQueueEntryPos (String strQEntryID)
    {
        Vector v=getChildElementVector(ElementName.QUEUEENTRY,null,null,true,0,false);
        for(int i=0;i<v.size();i++)
        {
            JDFQueueEntry qe=(JDFQueueEntry)v.elementAt(i);
            if(qe.getQueueEntryID().equals(strQEntryID))
                return i;
        }
        return -1;

    }

    ///////////////////////////////////////////////////////////////////////
    /**
     * Get the next QueueEntry to be processed
     * the first entry with highest priority gets selected
     * the status of the QueueEntry MUST be waiting  
     * 
     * @return the executable queueEntry, null if none is available
     * @deprecated use getNextExecutableQueueEntry(null);
     */
    public synchronized JDFQueueEntry getNextExecutableQueueEntry()
    {
        return getNextExecutableQueueEntry(null,null);
    }
    ///////////////////////////////////////////////////////////////////////
    /**
     * Get the next QueueEntry to be processed
     * the first entry with highest priority gets selected
     * if deviceID is specified, the entries with an explicit non matching deviceID are ignored
     * the status of the QueueEntry MUST be waiting  
     * @param deviceID the deviceID of the executing device - if null any deviceID will match
     * @param proxyFlag if not null, the existance of this attribute in the queueentry excludes the qe from the search
     * used e.g. in case a queue is used as a proxy and represents previously submitted jobs as waiting
     * 
     * @return the executable queueEntry, null if none is available
     */
    public synchronized JDFQueueEntry getNextExecutableQueueEntry(String deviceID, String proxyFlag)
    {
        if(!canExecute())
            return null;
        VElement v=getQueueEntryVector(new JDFAttributeMap(AttributeName.STATUS,EnumQueueEntryStatus.Waiting),null);
        int siz=v==null ? 0 : v.size();
        JDFQueueEntry theEntry=null;
        for(int i=0;i<siz;i++)
        {
            final JDFQueueEntry qe=(JDFQueueEntry)v.elementAt(i);
            
            if(proxyFlag!=null && qe.hasAttribute(proxyFlag))
                continue;
            
            if(deviceID==null || isWildCard(qe.getDeviceID()) || deviceID.equals(qe.getDeviceID()))
            {
                if(theEntry==null)
                {
                    theEntry=qe;
                }
                else if(qe.getPriority()>theEntry.getPriority())
                {
                    theEntry=qe;
                }
            }
        }
        return theEntry; 
    }

    /**
     * if the outgoing device processor is accepting new entries
     * @return true, if new entries are accepted
     */
    public boolean canExecute()
    {
        EnumQueueStatus status=getQueueStatus();
        if(EnumQueueStatus.Blocked.equals(status))
            return false;
        if(EnumQueueStatus.Held.equals(status))
            return false;
        if(EnumQueueStatus.Full.equals(status))
            return false;
        if(EnumQueueStatus.Running.equals(status))
            return false;
        if(EnumQueueStatus.Waiting.equals(status))
            return true;
        //if(EnumQueueStatus.Blocked.equals(status))
        // blocked or null(illegal)
        return numEntries(EnumQueueEntryStatus.Running)<maxRunningEntries;         
    }

    /**
     * if the incoming queue processor is accepting new entries
     * @return true, if new entries are accepted
     */
    public boolean canAccept()
    {
        EnumQueueStatus status=getQueueStatus();
        if(EnumQueueStatus.Blocked.equals(status))
            return false;
        if(EnumQueueStatus.Held.equals(status))
            return false;
        if(EnumQueueStatus.Full.equals(status))
            return false;
        if(EnumQueueStatus.Waiting.equals(status))
            return true;
        //if(EnumQueueStatus.Blocked.equals(status))
        // blocked or null(illegal)
        return hasAttribute(AttributeName.QUEUESIZE)?numEntries(null)<getQueueSize():true;         
    }

    /**
     * remove all entries with Status=Removed and any entries 
     * over maxCompleted that are either aborted or completed @see {@link JDFQueueEntry}.isCompleted()
     */
    public synchronized void cleanup()
    {
        VElement v=getQueueEntryVector();
        int siz=v==null ? 0 : v.size();
        int nBad=0;
        for(int i=0;i<siz;i++)
        {
            JDFQueueEntry qe=(JDFQueueEntry)v.elementAt(i);
            EnumQueueEntryStatus status=qe.getQueueEntryStatus();
            if(EnumQueueEntryStatus.Removed.equals(status))
                qe.deleteNode();
            else if(qe.isCompleted())
            {
                if(nBad++>=maxCompletedEntries)
                    qe.deleteNode();
            }
        }         
    }

    /**
     * copies this to the JDF Response resp, applying the filters defined in filter
     * 
     * @param resp the JDFResponse to copy this to
     * @param filter the QueueFilter that sets the queue size
     * @return the copied queue
     */
    public JDFQueue copyToResponse(JDFResponse resp, JDFQueueFilter filter)
    {
       if(resp==null)
           return null;
       resp.removeChildren(ElementName.QUEUE, null,null);
       JDFQueue newQueue=(JDFQueue) resp.copyElement(this, null);
       if(filter!=null)
       {
           filter.match(newQueue);
       }
       return newQueue;
       
    }
    /**
     * return the number of  entries
     * @param qeStatus the queueentry status of the enries to count, 
     * if null, do not filter
     * @return the number of active processors
     */
    public int numEntries(EnumQueueEntryStatus qeStatus)
    {
        VElement v=getQueueEntryVector(qeStatus==null ? null : new JDFAttributeMap(AttributeName.STATUS,qeStatus),null);
        return v==null ? 0 : v.size();
    }

    /**
     * return true if the number of  entries running is exceeded - performance
      */
    private boolean maxRunning()
    {
        VElement v=getChildrenByTagName(ElementName.QUEUEENTRY,null,new JDFAttributeMap(AttributeName.STATUS,"Running"), true, true,maxRunningEntries);
        int n= v==null ? 0 : v.size();
        return n==maxRunningEntries;
    }
    /**
     * make this a smart queue when modifying queueentries
     * @param_automated automate if true
     */
    public void setAutomated(boolean _automated)
    {
        automated=_automated;        
    }

    /**
     * is this a smart queue when modifying queueentries
     * @param_automated automate if true
     */
    public boolean isAutomated()
    {
        return automated;
    }

    /**
     * get the queuesize attribute or if it does not exist, count queuentry elements
     * @return the size of the queue
     */
    public int getQueueSize()
    {
        if(hasAttribute(AttributeName.QUEUESIZE))
            return super.getQueueSize();
        return getEntryCount();
    }
    
    /**
     * set the status of this queue based on the status values of the queueentries
     * @return the newly set Status, null if not modified
     */
    public EnumQueueStatus setStatusFromEntries()
    {
        EnumQueueStatus queueStatus = getQueueStatus();
        EnumQueueStatus newStatus = null;
        if(queueStatus==null || EnumQueueStatus.Waiting.equals(queueStatus) || EnumQueueStatus.Running.equals(queueStatus))
        {
            if(!maxRunning())
                newStatus=EnumQueueStatus.Waiting;
            else 
                newStatus=EnumQueueStatus.Running;
        }
 
        if(newStatus!=null)
            setQueueStatus(newStatus);
        
        return newStatus;
    }
    ///////////////////////////////////////////////////////////////////////

    /**
     * @return the maxCompletedEntries
     */
    public int getMaxCompletedEntries()
    {
        return maxCompletedEntries;
    }

    /**
     * set the maximum number of completed entries to keep
     * also call cleanup if we are automated
     * 
     * @param maxCompletedEntries the maxCompletedEntries to set
     */
    public void setMaxCompletedEntries(int _maxCompletedEntries)
    {
        this.maxCompletedEntries = _maxCompletedEntries;
        if(automated)
            cleanup();
    }

    /**
     * @return the maxRunningEntries
     */
    public int getMaxRunningEntries()
    {
        return maxRunningEntries;
    }

    /**
     * @param maxRunningEntries the maxRunningEntries to set
     */
    public void setMaxRunningEntries(int _maxRunningEntries)
    {
        this.maxRunningEntries = _maxRunningEntries;
        if(automated)
            setStatusFromEntries();
    }

}



