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


public class JDFQueue extends JDFAutoQueue
{
    private static final long serialVersionUID = 1L;
    /**
     * number of concurrent running entries 
     */
    public int maxRunningEntries=1;
    /**
     * max number of completed entries to retain 
     */
    public int maxCompletedEntries=0;
    private boolean automated=false;

    /**
     * Constructor for JDFQueue
     * @param myOwnerDocument
     * @param qualifiedName
     */
    public JDFQueue(
            CoreDocumentImpl myOwnerDocument,
            String qualifiedName)
    {
        super(myOwnerDocument, qualifiedName);
    }

    /**
     * Constructor for JDFQueue
     * @param myOwnerDocument
     * @param myNamespaceURI
     * @param qualifiedName
     */
    public JDFQueue(
            CoreDocumentImpl myOwnerDocument,
            String myNamespaceURI,
            String qualifiedName)
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
    public JDFQueue(
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
    public VElement getQueueEntryVector(JDFAttributeMap attMap, VJDFAttributeMap parts)
    {
        VElement v=getChildrenByTagName(ElementName.QUEUEENTRY,null,attMap, false, true,0);
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
     */
    public JDFQueueEntry getEntry(int i)
    {
        return (JDFQueueEntry) getChildByTagName(ElementName.QUEUEENTRY,null,i,null, false, true);
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
            final JDFQueueEntry entry = getEntry (i);

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
     * 
     */
    public JDFQueueEntry getEntry (String strQEntryID)
    {
        if(isWildCard(strQEntryID))
            return null;
        return (JDFQueueEntry) getChildByTagName(ElementName.QUEUEENTRY,null,0,new JDFAttributeMap(AttributeName.QUEUEENTRYID,strQEntryID),true,true);
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
     * the first entry with equal priority gets selected
     * 
     * @return the executable queueEntry, null if none is available
     */
    public JDFQueueEntry getNextExecutableQueueEntry()
    {
        if(!canExecute())
            return null;
        VElement v=getQueueEntryVector(new JDFAttributeMap(AttributeName.STATUS,EnumQueueEntryStatus.Waiting),null);
        int siz=v==null ? 0 : v.size();
        JDFQueueEntry theEntry=null;
        for(int i=0;i<siz;i++)
        {
            JDFQueueEntry qe=(JDFQueueEntry)v.elementAt(i);
            if(theEntry==null)
            {
                theEntry=qe;
            }
            else if(qe.getPriority()>theEntry.getPriority())
            {
                theEntry=qe;
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
        return numEntries(null)<getQueueSize();         
    }

    /**
     * remove all entries with Staus=Removed and any entries 
     * over maxCompleted that are either aborted or completed @see {@link JDFQueueEntry}.isCompleted()
     */
    public void cleanup()
    {
        VElement v=getQueueEntryVector();
        int siz=v==null ? 0 : v.size();
        int nBad=0;
        for(int i=0;i<siz;i++)
        {
            JDFQueueEntry qe=(JDFQueueEntry)v.elementAt(i);
            EnumQueueEntryStatus status=qe.getQueueEntryStatus();
            if(status==null)
                qe.deleteNode();
            else if(EnumQueueEntryStatus.Removed.equals(status))
                qe.deleteNode();
            else if(qe.isCompleted())
            {
                if(nBad++>=maxCompletedEntries)
                    qe.deleteNode();
            }
        }         
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
     * thesize of the queue
     */
    public int getQueueSize()
    {
        if(hasAttribute(AttributeName.QUEUESIZE))
            return super.getQueueSize();
        return getEntryCount();
    }

    ///////////////////////////////////////////////////////////////////////

}



