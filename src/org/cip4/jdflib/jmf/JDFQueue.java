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

import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;
import java.util.Vector;

import org.apache.xerces.dom.CoreDocumentImpl;
import org.cip4.jdflib.auto.JDFAutoQueue;
import org.cip4.jdflib.auto.JDFAutoQueueEntry.EnumQueueEntryStatus;
import org.cip4.jdflib.core.AttributeName;
import org.cip4.jdflib.core.ElementName;
import org.cip4.jdflib.core.KElement;
import org.cip4.jdflib.core.VElement;
import org.cip4.jdflib.core.VString;
import org.cip4.jdflib.datatypes.JDFAttributeMap;
import org.cip4.jdflib.datatypes.VJDFAttributeMap;
import org.cip4.jdflib.jmf.JDFQueueEntry.QueueEntryComparator;
import org.cip4.jdflib.node.JDFNode.NodeIdentifier;
import org.cip4.jdflib.util.JDFDate;

public class JDFQueue extends JDFAutoQueue
{
	private static final long serialVersionUID = 1L;
	/**
	 * number of concurrent running entries
	 */
	private int maxRunningEntries = 1;
	/**
	 * number of concurrent waiting entries
	 */
	private int maxWaitingEntries = 1000000;
	/**
	 * max number of completed entries to retain
	 */
	private int maxCompletedEntries = 0;
	private boolean automated = false;
	private boolean bAccepting = true; // new entries may be added to the queue
	private boolean bProcessing = true; // new entries may be processed by the
	// queue processor
	private CleanupCallback cleanupCallback = null;
	private ExecuteCallback executeCallback = null;
	private Comparator<KElement> queueSorter = new QueueEntryComparator();

	/**
	 * callback class definition for cleaning up in cleanup called once for every qe that is removed
	 * 
	 * @author prosirai
	 * 
	 */
	public abstract static class CleanupCallback
	{
		/**
		 * cleans up when a QueueEntry is removed by whatever method
		 * @param qe
		 */
		public abstract void cleanEntry(JDFQueueEntry qe);
	}

	/**
	 * callback class definition for specifying whether a QE may execute
	 * @return true if this is executable
	 * @author prosirai
	 * 
	 */
	public abstract static class ExecuteCallback
	{
		/**
		 * @param qe the queueentry to check
		 * @returntrue if this qe can be executed
		 */
		public abstract boolean canExecute(JDFQueueEntry qe);
	}

	/**
	 * Constructor for JDFQueue
	 * 
	 * @param myOwnerDocument
	 * @param qualifiedName
	 */
	public JDFQueue(CoreDocumentImpl myOwnerDocument, String qualifiedName)
	{
		super(myOwnerDocument, qualifiedName);
	}

	/**
	 * set the status as if an OpenQueue command has been sent
	 * 
	 * @return
	 */
	public EnumQueueStatus openQueue()
	{
		if (bAccepting)
			return getQueueStatus();
		bAccepting = true;
		return setStatusFromEntries();
	}

	/**
	 * set the status as if a CloseQueue command has been sent
	 * 
	 * @return
	 */
	public EnumQueueStatus closeQueue()
	{
		if (!bAccepting)
			return getQueueStatus();
		bAccepting = false;
		return setStatusFromEntries();
	}

	/**
	 * set the status as if a HoldQueue command has been sent
	 * 
	 * @return
	 */
	public EnumQueueStatus holdQueue()
	{
		if (!bProcessing)
			return getQueueStatus();
		bProcessing = false;
		return setStatusFromEntries();
	}

	/**
	 * set the status as if a HoldQueue command has been sent
	 * 
	 * @return
	 */
	public EnumQueueStatus resumeQueue()
	{
		if (bProcessing)
			return getQueueStatus();
		bProcessing = true;
		return setStatusFromEntries();
	}

	/**
	 * Constructor for JDFQueue
	 * 
	 * @param myOwnerDocument
	 * @param myNamespaceURI
	 * @param qualifiedName
	 */
	public JDFQueue(CoreDocumentImpl myOwnerDocument, String myNamespaceURI, String qualifiedName)
	{
		super(myOwnerDocument, myNamespaceURI, qualifiedName);
	}

	/**
	 * Constructor for JDFQueue
	 * 
	 * @param myOwnerDocument
	 * @param myNamespaceURI
	 * @param qualifiedName
	 * @param myLocalName
	 */
	public JDFQueue(CoreDocumentImpl myOwnerDocument, String myNamespaceURI, String qualifiedName, String myLocalName)
	{
		super(myOwnerDocument, myNamespaceURI, qualifiedName, myLocalName);
	}

	/**
	 * toString()
	 * 
	 * @return String
	 */
	@Override
	public String toString()
	{
		return "JDFQueue[  --> " + super.toString() + " ]";
	}

	/**
	 * Method getEntryCount.
	 * 
	 * @return int quantity of QueueEntry children
	 */
	public int getEntryCount()
	{
		return numChildElements(ElementName.QUEUEENTRY, null);
	}

	/**
	 * Get a vector of all queueentry elements
	 * 
	 * @return VElement: the vector of queue entries
	 */
	public VElement getQueueEntryVector()
	{
		return getChildElementVector(ElementName.QUEUEENTRY, null, null, true, -1, true);
	}

	/**
	 * Get a vector of queueentry elements with a given set of attributes and part maps
	 * 
	 * @return VElement: the vector of queue entries
	 */
	public synchronized VElement getQueueEntryVector(JDFAttributeMap attMap, VJDFAttributeMap parts)
	{
		VElement v = getChildElementVector(ElementName.QUEUEENTRY, null, attMap, true, -1, true);
		if (parts != null)
		{
			for (int i = v.size() - 1; i >= 0; i--)
			{
				JDFQueueEntry qe = (JDFQueueEntry) v.elementAt(i);
				if (!parts.equals(qe.getPartMapVector()))
				{
					v.remove(i);
				}
			}
		}
		return (v == null || v.size() == 0) ? null : v;
	}

	/**
	 * get a map of queueentries that uses QueueEntryID as key
	 * @return the map, null if this is empty
	 */
	public synchronized Map<String, JDFQueueEntry> getQueueEntryIDMap()
	{
		HashMap<String, JDFQueueEntry> map = null;
		
		VElement v = getQueueEntryVector();
		if (v != null)
		{
			int siz = v.size();
			if (siz > 0)
			{
				map = new HashMap<String, JDFQueueEntry>(siz);
				for (int i = 0; i < siz; i++)
				{
					JDFQueueEntry qe = (JDFQueueEntry) v.get(i);
					map.put(qe.getQueueEntryID(), qe);
				}
			}
		}
		
		return map;
	}

	/**
	 * Get a vector of queueentry elements that matches a given nodeidentifier
	 * 
	 * @return VElement: the vector of queue entries
	 */
	public synchronized VElement getQueueEntryVector(NodeIdentifier nid)
	{
		VElement v = getQueueEntryVector();
		if (nid == null || v == null)
			return v;

		for (int i = v.size() - 1; i >= 0; i--)
		{
			JDFQueueEntry qe = (JDFQueueEntry) v.elementAt(i);
			if (!qe.matchesNodeIdentifier(nid))
			{
				v.remove(i);
			}
		}

		return (v.size() == 0) ? null : v;
	}

	/**
	 * Method getEntry: find a queuentry by position
	 * 
	 * @param i the index of the queueentry
	 * @return JDFQueueEntry
	 * @deprecated use getQueueEntry(int)
	 */
	@Deprecated
	public JDFQueueEntry getEntry(int i)
	{
		return getQueueEntry(i);
	}

	/**
	 * create a queueEntry if this queue is accepting
	 * 
	 * @param bHeld , if true, set the qe Status to Held
	 * @return the newly created queueEntry, null if failed
	 */
	public JDFQueueEntry createQueueEntry(boolean bHeld)
	{
		if (!canAccept())
			return null;
		JDFQueueEntry qe = appendQueueEntry();
		qe.setQueueEntryID("qe" + uniqueID(0));
		qe.setSubmissionTime(new JDFDate());
		qe.setQueueEntryStatus(bHeld ? EnumQueueEntryStatus.Held : EnumQueueEntryStatus.Waiting);
		return qe;
	}

	/**
	 * flush this queue according to the rules defined in qf
	 * 
	 * @param qf
	 * @return null if none were removed, else vector of removed queuentries
	 */
	public synchronized VElement flushQueue(JDFQueueFilter qf)
	{
		int siz = 0;
		
		VElement ve = getQueueEntryVector();
		if (ve != null)
		{
			siz = ve.size();
			for (int i = siz - 1; i >= 0; i--)
			{
				JDFQueueEntry qe = (JDFQueueEntry) ve.get(i);
				if (qe.matchesQueueFilter(qf))
				{
					if (cleanupCallback != null)
						cleanupCallback.cleanEntry(qe);
					
					qe.deleteNode();
				}
				else
				{
					ve.remove(i);
					siz--;
				}
			}
		}
		
		if (automated)
			setStatusFromEntries();
		
		return siz == 0 ? null : ve;

	}

	/**
	 * Method findQueueEntries
	 * <p>
	 * default: findQueueEntries(jobID, jobPartID, new VJDFAttributeMap(), null)
	 * 
	 * @param strJobID Job ID.
	 * @param strJobPartID Job part ID.
	 * @param vamParts Partition to execute, may not be null
	 * @param status Queue Entry Status, null means any status.
	 * @deprecated use getQueueEntryVector(map, partmapvector)
	 * 
	 * @return VString: vector of QueueEntry IDs
	 */
	@Deprecated
	public VString findQueueEntries(String strJobID, String strJobPartID, VJDFAttributeMap vamParts, EnumQueueEntryStatus status)
	{
		final VString vsQEntryIDs = new VString();

		final int entryCount = getEntryCount();
		for (int i = 0; i < entryCount; i++)
		{
			final JDFQueueEntry entry = getQueueEntry(i);

			final String strQEJobID = entry.getJobID();
			final String strQEJobPartID = entry.getJobPartID();

			final VJDFAttributeMap vamQEParts = entry.getPartMapVector();

			final EnumQueueEntryStatus statusQE = entry.getQueueEntryStatus();

			if (strJobID.equals(strQEJobID) && strJobPartID.equals(strQEJobPartID) && vamParts.equals(vamQEParts))
			{
				if ((status == null) || (status.equals(statusQE)))
				{
					vsQEntryIDs.appendUnique(entry.getQueueEntryID());
				}
			}
		}

		return vsQEntryIDs;
	}

	/**
	 * Find a queueEntry by QueueEntryID<br>
	 * 
	 * note that you may want to use the generic getChildByTagName with the appropriate attribute map, if you have more
	 * information available
	 * 
	 * @param strQEntryID the QueueEntryID of the requeste QueueEntry
	 * @return the QueueEntry with QueueEntryID=strQEntryID, null if strQEntryID is null or empty string or the
	 *         queueentry does not exist
	 * @deprecated use getQueueEntry(id)
	 */
	@Deprecated
	public JDFQueueEntry getEntry(String strQEntryID)
	{
		return getQueueEntry(strQEntryID);
	}

	/**
	 * Find a queueEntry by QueueEntryID<br>
	 * 
	 * note that you may want to use the generic getChildByTagName with the appropriate attribute map, if you have more
	 * information available
	 * 
	 * @param strQEntryID the QueueEntryID of the requeste QueueEntry
	 * @return the QueueEntry with QueueEntryID=strQEntryID, null if strQEntryID is null or empty string or the
	 *         queueentry does not exist
	 * 
	 */
	public JDFQueueEntry getQueueEntry(String strQEntryID)
	{
		if (isWildCard(strQEntryID))
			return null;
		return (JDFQueueEntry) getChildByTagName(ElementName.QUEUEENTRY, null, 0, new JDFAttributeMap(AttributeName.QUEUEENTRYID, strQEntryID), true, true);
	}

	/**
	 * Find a queueEntry by NodeIdentifier (jobid, jobpartid, part)<br>
	 * 
	 * note that you may want to use the generic getChildByTagName with the appropriate attribute map, if you have more
	 * information available
	 * 
	 * @param nodeID the identifier - jobID, jobPartID, parts - of the qe
	 * @param nSkip the number of nodes to skip, cout backwards if<0
	 * @return the QueueEntry with matching jobID, jobPartID, parts, null if nodeID is null or empty string or the
	 *         queueentry does not exist
	 * 
	 */
	public JDFQueueEntry getQueueEntry(NodeIdentifier nodeID, int nSkip)
	{
		if (nodeID == null)
			return null;
		
		VElement v = getQueueEntryVector();
		if (v != null)
		{
			int siz = v.size();
			int n = 0;
			if (nSkip >= 0)
			{
				for (int i = 0; i < siz; i++)
				{
					JDFQueueEntry qe = (JDFQueueEntry) v.elementAt(i);
					NodeIdentifier ni2 = qe.getIdentifier();
					if (ni2.matches(nodeID) && n++ >= nSkip)
						return qe;
				}
			}
			else
			{
				for (int i = siz - 1; i >= 0; i--)
				{
					JDFQueueEntry qe = (JDFQueueEntry) v.elementAt(i);
					NodeIdentifier ni2 = qe.getIdentifier();
					if (ni2.matches(nodeID) && --n <= nSkip)
						return qe;
				}
			}
		}

		return null;
	}

	// ////////////////////////////////////////////////////////////////////////

	/**
	 * Find the position of a queueEntry by QueueEntryID
	 * 
	 * @param strQEntryID the QueueEntryID of the requeste QueueEntry
	 * @return the position in the queue, -1 if not there
	 */
	public int getQueueEntryPos(String strQEntryID)
	{
		Vector v = getChildElementVector(ElementName.QUEUEENTRY, null, null, true, 0, false);
		for (int i = 0; i < v.size(); i++)
		{
			JDFQueueEntry qe = (JDFQueueEntry) v.elementAt(i);
			if (qe.getQueueEntryID().equals(strQEntryID))
				return i;
		}
		return -1;

	}

	// /////////////////////////////////////////////////////////////////////
	/**
	 * Get the next QueueEntry to be processed the first entry with highest priority gets selected if deviceID is
	 * specified, the entries with an explicit non matching deviceID are ignored the status of the QueueEntry MUST be
	 * waiting
	 * 
	 * @param deviceID the deviceID of the executing device - if null any deviceID will match
	 * @param proxyFlag if not null, the existance of this attribute in the queueentry excludes the qe from the search
	 *            used e.g. in case a queue is used as a proxy and represents previously submitted jobs as waiting
	 * 
	 * @return the executable queueEntry, null if none is available
	 */
	public synchronized JDFQueueEntry getNextExecutableQueueEntry()
	{
		JDFQueueEntry theEntry = null;

		if (!canExecute())
			return theEntry;
		
		VElement v = getQueueEntryVector(new JDFAttributeMap(AttributeName.STATUS, EnumQueueEntryStatus.Waiting), null);
		if (v != null)
		{
			int siz = v.size();
			for (int i = 0; i < siz; i++)
			{
				final JDFQueueEntry qe = (JDFQueueEntry) v.elementAt(i);

				if (executeCallback != null && !executeCallback.canExecute(qe))
					continue;

				if (theEntry == null)
				{
					theEntry = qe;
				}
				else if (qe.compareTo(theEntry) < 0)
				{
					theEntry = qe;
				}
			}
		}
		
		return theEntry;
	}

	/**
	 * if the outgoing device processor is accepting new entries
	 * 
	 * @return true, if new entries are accepted
	 */
	public boolean canExecute()
	{
		EnumQueueStatus status = getQueueStatus();
		if (EnumQueueStatus.Blocked.equals(status))
			return false;
		if (EnumQueueStatus.Held.equals(status))
			return false;
		if (EnumQueueStatus.Full.equals(status))
			return false;
		if (EnumQueueStatus.Running.equals(status))
			return false;
		if (EnumQueueStatus.Waiting.equals(status))
			return true;
		// if(EnumQueueStatus.Blocked.equals(status))
		// blocked or null(illegal)
		return numEntries(EnumQueueEntryStatus.Running) < maxRunningEntries;
	}

	/**
	 * if the incoming queue processor is accepting new entries
	 * 
	 * @return true, if new entries are accepted
	 */
	public boolean canAccept()
	{
		EnumQueueStatus status = getQueueStatus();
		if (EnumQueueStatus.Blocked.equals(status))
			return false;
		if (EnumQueueStatus.Closed.equals(status))
			return false;
		if (EnumQueueStatus.Full.equals(status))
			return false;
		if (EnumQueueStatus.Waiting.equals(status))
			return true;
		// if(EnumQueueStatus.Blocked.equals(status))
		// blocked or null(illegal)
		return hasAttribute(AttributeName.QUEUESIZE) ? numEntries(null) < getQueueSize() : !maxWaiting();
	}

	/**
	 * remove all entries with Status=Removed and any entries over maxCompleted that are either aborted or completed @see
	 * {@link JDFQueueEntry} .isCompleted()
	 * 
	 * @return a vector of all removed elements
	 */

	public synchronized void cleanup()
	{
		VElement v = getQueueEntryVector();
		if (v != null)
		{
			int siz = v.size();
			int nBad = 0;
			for (int i = 0; i < siz; i++)
			{
				JDFQueueEntry qe = (JDFQueueEntry) v.elementAt(i);
				EnumQueueEntryStatus status = qe.getQueueEntryStatus();
				if (EnumQueueEntryStatus.Removed.equals(status))
				{
					if (cleanupCallback != null)
						cleanupCallback.cleanEntry(qe);

					qe.deleteNode();
				}
				else if (qe.isCompleted())
				{
					if (nBad++ >= maxCompletedEntries)
					{
						if (cleanupCallback != null)
							cleanupCallback.cleanEntry(qe);

						qe.deleteNode();
					}
				}
			}
		}
		
		setStatusFromEntries();
	}

	/**
	 * copies this to the JDF Response resp, applying the filters defined in filter
	 * 
	 * @param resp the JDFResponse to copy this to
	 * @param filter the QueueFilter that sets the queue size
	 * @return the copied queue
	 */
	synchronized public JDFQueue copyToResponse(JDFResponse resp, JDFQueueFilter filter)
	{
		if (resp == null)
			return null;
		resp.removeChildren(ElementName.QUEUE, null, null);
		JDFQueue newQueue = (JDFQueue) resp.copyElement(this, null);
		if (filter != null)
		{
			filter.match(newQueue);
		}
		return newQueue;

	}

	/**
	 * return the number of entries
	 * 
	 * @param qeStatus the queueentry status of the enries to count, if null, do not filter
	 * @return the number of active processors
	 */
	public int numEntries(EnumQueueEntryStatus qeStatus)
	{
		int n = 0;
		JDFQueueEntry qe = (JDFQueueEntry) getFirstChildElement(ElementName.QUEUEENTRY, null);
		String stat = qeStatus == null ? null : qeStatus.getName();
		while (qe != null)
		{
			if (stat == null || stat.equals(qe.getAttribute(AttributeName.STATUS)))
				n++;
			qe = (JDFQueueEntry) qe.getNextSiblingElement(ElementName.QUEUEENTRY, null);
		}
		return n;
	}

	/**
	 * @return true if the number of entries running is exceeded - performance
	 */
	private boolean maxRunning()
	{
		return numEntries(EnumQueueEntryStatus.Running) >= maxRunningEntries;
	}

	/**
	 * @return true if the number of entries running is exceeded - performance
	 */
	private boolean maxWaiting()
	{
		return numEntries(EnumQueueEntryStatus.Waiting) >= maxWaitingEntries;
	}

	/**
	 * make this a smart queue when modifying queueentries
	 * 
	 * @param _automated automate if true
	 */
	public void setAutomated(boolean _automated)
	{
		automated = _automated;
		if (automated)
			setStatusFromEntries();
	}

	/**
	 * is this a smart queue when modifying queueentries
	 * 
	 * @return  true if this is automated
	 */
	public boolean isAutomated()
	{
		return automated;
	}

	/**
	 * get the queuesize attribute or if it does not exist, count queuentry elements
	 * 
	 * @return the size of the queue
	 */
	@Override
	public int getQueueSize()
	{
		if (hasAttribute(AttributeName.QUEUESIZE))
			return super.getQueueSize();
		return getEntryCount();
	}

	/**
	 * set the status of this queue based on the status values of the queueentries
	 * 
	 * @return the newly set Status, null if not modified
	 */
	public synchronized EnumQueueStatus setStatusFromEntries()
	{
		// EnumQueueStatus queueStatus = getQueueStatus();
		EnumQueueStatus newStatus = null;
		if (bAccepting)
		{
			if (bProcessing)
			{
				boolean maxRunning = maxRunning();
				boolean maxWaiting = maxWaiting();
				if (!maxRunning)
				{
					if (!maxWaiting)
						newStatus = EnumQueueStatus.Waiting;
					else
						newStatus = EnumQueueStatus.Closed;

				}
				else if (!maxWaiting)
					newStatus = EnumQueueStatus.Running;
				else
					newStatus = EnumQueueStatus.Full;
			}
			else
			// accepting but not processing
			{
				newStatus = EnumQueueStatus.Held;
			}
		}
		else
		// queue is closed
		{
			if (bProcessing)
			{
				newStatus = EnumQueueStatus.Closed;
			}
			else
			// accepting but not processing
			{
				newStatus = EnumQueueStatus.Blocked;
			}
		}

		if (newStatus != null)
			setQueueStatus(newStatus);

		return newStatus;
	}

	// /////////////////////////////////////////////////////////////////////
	/**
	 * sorts all child elements by alphabet
	 * 
	 */
	@Override
	public void sortChildren()
	{
		sortChildren(queueSorter);
	}

	/**
	 * @return the maxCompletedEntries
	 */
	public int getMaxCompletedEntries()
	{
		return maxCompletedEntries;
	}

	/**
	 * set the maximum number of completed entries to keep also call cleanup if we are automated
	 * 
	 * @param maxCompletedEntries the maxCompletedEntries to set
	 * @return {@link VElement} the list of removed entries due to cleanup
	 */
	public void setMaxCompletedEntries(int _maxCompletedEntries)
	{
		this.maxCompletedEntries = _maxCompletedEntries;
		// VElement v=null;
		if (automated)
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
		if (automated)
			setStatusFromEntries();
	}

	/**
	 * @param _maxWaitingEntries the setMaxWaitingEntries to set, excluding held entries
	 */
	public void setMaxWaitingEntries(int _maxWaitingEntries)
	{
		this.maxWaitingEntries = _maxWaitingEntries;
		if (automated)
			setStatusFromEntries();
	}

	/**
	 * @param _cleanupCallback the cleanupCallback to set
	 */
	public void setCleanupCallback(CleanupCallback _cleanupCallback)
	{
		this.cleanupCallback = _cleanupCallback;
	}

	/**
	 * @param _callback the ExecuteCallback to set
	 */
	public void setExecuteCallback(ExecuteCallback _callback)
	{
		this.executeCallback = _callback;
	}

	/**
	 * @param queueSorter the queueSorter to set sets the Comparator to sort this queuewith
	 */
	public void setQueueSorter(Comparator<KElement> _queueSorter)
	{
		this.queueSorter = _queueSorter;
	}

}
