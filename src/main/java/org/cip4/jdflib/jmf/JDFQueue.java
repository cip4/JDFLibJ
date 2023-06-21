/*
 * The CIP4 Software License, Version 1.0
 *
 *
 * Copyright (c) 2001-2023 The International Cooperation for the Integration of Processes in Prepress, Press and Postpress (CIP4). All rights reserved.
 *
 * Redistribution and use in source and binary forms, with or without modification, are permitted provided that the following conditions are met:
 *
 * 1. Redistributions of source code must retain the above copyright notice, this list of conditions and the following disclaimer.
 *
 * 2. Redistributions in binary form must reproduce the above copyright notice, this list of conditions and the following disclaimer in the documentation and/or other materials provided with the
 * distribution.
 *
 * 3. The end-user documentation included with the redistribution, if any, must include the following acknowledgment: "This product includes software developed by the The International Cooperation for
 * the Integration of Processes in Prepress, Press and Postpress (www.cip4.org)" Alternately, this acknowledgment may appear in the software itself, if and wherever such third-party acknowledgments
 * normally appear.
 *
 * 4. The names "CIP4" and "The International Cooperation for the Integration of Processes in Prepress, Press and Postpress" must not be used to endorse or promote products derived from this software
 * without prior written permission. For written permission, please contact info@cip4.org.
 *
 * 5. Products derived from this software may not be called "CIP4", nor may "CIP4" appear in their name, without prior written permission of the CIP4 organization
 *
 * Usage of this software in commercial products is subject to restrictions. For details please consult info@cip4.org.
 *
 * THIS SOFTWARE IS PROVIDED ``AS IS'' AND ANY EXPRESSED OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO, THE IMPLIED WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE ARE
 * DISCLAIMED. IN NO EVENT SHALL THE INTERNATIONAL COOPERATION FOR THE INTEGRATION OF PROCESSES IN PREPRESS, PRESS AND POSTPRESS OR ITS CONTRIBUTORS BE LIABLE FOR ANY DIRECT, INDIRECT, INCIDENTAL,
 * SPECIAL, EXEMPLARY, OR CONSEQUENTIAL DAMAGES (INCLUDING, BUT NOT LIMITED TO, PROCUREMENT OF SUBSTITUTE GOODS OR SERVICES; LOSS OF USE, DATA, OR PROFITS; OR BUSINESS INTERRUPTION) HOWEVER CAUSED AND
 * ON ANY THEORY OF LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY, OR TORT (INCLUDING NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE OF THIS SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY
 * OF SUCH DAMAGE. ====================================================================
 *
 * This software consists of voluntary contributions made by many individuals on behalf of the The International Cooperation for the Integration of Processes in Prepress, Press and Postpress and was
 * originally based on software copyright (c) 1999-2001, Heidelberger Druckmaschinen AG copyright (c) 1999-2001, Agfa-Gevaert N.V.
 *
 * For more information on The International Cooperation for the Integration of Processes in Prepress, Press and Postpress , please see <http://www.cip4.org/>.
 *
 *
 */
/**
 * JDFQueue.java
 *
 * @author Dietrich Mucha Copyright (C) 1999-2005 Heidelberger Druckmaschinen AG. All rights reserved.
 **/

package org.cip4.jdflib.jmf;

import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
import org.cip4.jdflib.jmf.JDFMessage.EnumType;
import org.cip4.jdflib.jmf.JDFQueueEntry.QueueEntryComparator;
import org.cip4.jdflib.jmf.JDFQueueFilter.QueueEntryMatcher;
import org.cip4.jdflib.node.NodeIdentifier;
import org.cip4.jdflib.util.JDFDate;
import org.cip4.jdflib.util.StringUtil;

/**
 * The JDF Queue
 *
 * @author Dr. Rainer Prosi, Heidelberger Druckmaschinen AG
 *
 *         01.12.2008
 */
public class JDFQueue extends JDFAutoQueue
{
	private static final long serialVersionUID = 1L;
	/**
	 * number of concurrent running entries
	 */
	private int maxRunningEntries = -1;
	/**
	 * number of concurrent waiting entries
	 */
	private int maxWaitingEntries = -1;
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
	private Comparator<KElement> queueSorter = null;

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
		 *
		 * @param qe
		 */
		public abstract void cleanEntry(JDFQueueEntry qe);
	}

	/**
	 * callback class definition for specifying whether a QE may execute
	 *
	 * @author prosirai
	 *
	 */
	public abstract static class ExecuteCallback
	{
		/**
		 * @param qe the queueentry to check
		 * @return true if this qe can be executed
		 */
		public abstract boolean canExecute(JDFQueueEntry qe);
	}

	/**
	 * Constructor for JDFQueue
	 *
	 * @param myOwnerDocument
	 * @param qualifiedName
	 */
	public JDFQueue(final CoreDocumentImpl myOwnerDocument, final String qualifiedName)
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
		{
			return getQueueStatus();
		}
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
		{
			return getQueueStatus();
		}
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
		{
			return getQueueStatus();
		}
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
		{
			return getQueueStatus();
		}
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
	public JDFQueue(final CoreDocumentImpl myOwnerDocument, final String myNamespaceURI, final String qualifiedName)
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
	public JDFQueue(final CoreDocumentImpl myOwnerDocument, final String myNamespaceURI, final String qualifiedName, final String myLocalName)
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
		return numChildrenByClass(JDFQueueEntry.class, false);
	}

	/**
	 * Get a vector of all queueentry elements
	 *
	 * @return VElement: the vector of queue entries
	 */
	public VElement getQueueEntryVector()
	{
		final List<JDFQueueEntry> v1 = getChildArrayByClass(JDFQueueEntry.class, false, -1);
		final VElement v = new VElement();
		if (v1 != null)
		{
			v.ensureCapacity(v1.size());
			v.addAll(v1);
		}
		return v;
	}

	/**
	 * Get a vector of queueentry elements with a given set of attributes and part maps
	 *
	 * @param attMap
	 * @param parts
	 *
	 * @return VElement: the vector of queue entries
	 */
	public synchronized VElement getQueueEntryVector(final JDFAttributeMap attMap, final VJDFAttributeMap parts)
	{
		final VElement v = getChildElementVector(ElementName.QUEUEENTRY, null, attMap, true, -1, true);
		if (parts != null)
		{
			for (int i = v.size() - 1; i >= 0; i--)
			{
				final JDFQueueEntry qe = (JDFQueueEntry) v.elementAt(i);
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
	 *
	 * @return the map, null if this is empty
	 */
	public synchronized Map<String, JDFQueueEntry> getQueueEntryIDMap()
	{
		HashMap<String, JDFQueueEntry> map = null;

		final List<JDFQueueEntry> v = getChildArrayByClass(JDFQueueEntry.class, false, -1);
		if (v != null)
		{
			final int siz = v.size();
			if (siz > 0)
			{
				map = new HashMap<>(siz);
				for (int i = 0; i < siz; i++)
				{
					final JDFQueueEntry qe = v.get(i);
					map.put(qe.getQueueEntryID(), qe);
				}
			}
		}

		return map;
	}

	/**
	 * Get a vector of queueentry elements that matches a given nodeidentifier
	 *
	 * @param nid
	 *
	 * @return VElement: the vector of queue entries
	 */
	public synchronized VElement getQueueEntryVector(final NodeIdentifier nid)
	{
		final VElement v = getQueueEntryVector();
		if (nid == null || v == null || nid.equals(new NodeIdentifier()))
		{
			return v;
		}

		for (int i = v.size() - 1; i >= 0; i--)
		{
			final JDFQueueEntry qe = (JDFQueueEntry) v.elementAt(i);
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
	public JDFQueueEntry getEntry(final int i)
	{
		return getQueueEntry(i);
	}

	/**
	 * create a queueEntry if this queue is accepting
	 *
	 * @param bHeld , if true, set the qe Status to Held
	 * @return the newly created queueEntry, null if failed
	 */
	public JDFQueueEntry createQueueEntry(final boolean bHeld)
	{
		if (!canAccept())
		{
			return null;
		}
		final JDFQueueEntry qe = appendQueueEntry();
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
	public synchronized VElement flushQueue(final JDFQueueFilter qf)
	{
		int siz = 0;

		final VElement ve = getQueueEntryVector();
		if (ve != null)
		{
			siz = ve.size();
			final QueueEntryMatcher qeMatch = qf == null ? null : qf.new QueueEntryMatcher();
			for (int i = siz - 1; i >= 0; i--)
			{
				final JDFQueueEntry qe = (JDFQueueEntry) ve.get(i);
				if (qeMatch == null || qeMatch.matches(qe))
				{
					if (cleanupCallback != null)
					{
						cleanupCallback.cleanEntry(qe);
					}

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
		{
			setStatusFromEntries();
		}

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
	public VString findQueueEntries(final String strJobID, final String strJobPartID, final VJDFAttributeMap vamParts, final EnumQueueEntryStatus status)
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
	 * note that you may want to use the generic getChildByTagName with the appropriate attribute map, if you have more information available
	 *
	 * @param strQEntryID the QueueEntryID of the requeste QueueEntry
	 * @return the QueueEntry with QueueEntryID=strQEntryID, null if strQEntryID is null or empty string or the queueentry does not exist
	 * @deprecated use getQueueEntry(id)
	 */
	@Deprecated
	public JDFQueueEntry getEntry(final String strQEntryID)
	{
		return getQueueEntry(strQEntryID);
	}

	/**
	 * Find a queueEntry by QueueEntryID<br>
	 *
	 * note that you may want to use the generic getChildByTagName with the appropriate attribute map, if you have more information available
	 *
	 * @param strQEntryID the QueueEntryID of the requeste QueueEntry
	 * @return the QueueEntry with QueueEntryID=strQEntryID, null if strQEntryID is null or empty string or the queueentry does not exist
	 *
	 */
	public JDFQueueEntry getQueueEntry(final String strQEntryID)
	{
		if (isWildCard(strQEntryID))
		{
			return null;
		}
		JDFQueueEntry qe = getFirstChildElement(JDFQueueEntry.class);
		while (qe != null)
		{
			if (strQEntryID.equals(qe.getQueueEntryID()))
				return qe;
			qe = qe.getNextSiblingElement(JDFQueueEntry.class);
		}
		return null;
	}

	/**
	 * Find a queueEntry by NodeIdentifier (jobid, jobpartid, part)<br>
	 *
	 * note that you may want to use the generic getChildByTagName with the appropriate attribute map, if you have more information available
	 *
	 * @param nodeID the identifier - jobID, jobPartID, parts - of the qe
	 * @param nSkip the number of nodes to skip, cout backwards if<0
	 * @return the QueueEntry with matching jobID, jobPartID, parts, null if nodeID is null or empty string or the queueentry does not exist
	 *
	 */
	public JDFQueueEntry getQueueEntry(final NodeIdentifier nodeID, final int nSkip)
	{
		if (nodeID == null)
		{
			return null;
		}

		final VElement v = getQueueEntryVector();
		if (v != null)
		{
			final int siz = v.size();
			int n = 0;
			if (nSkip >= 0)
			{
				for (int i = 0; i < siz; i++)
				{
					final JDFQueueEntry qe = (JDFQueueEntry) v.elementAt(i);
					final NodeIdentifier ni2 = qe.getIdentifier();
					if (ni2.matches(nodeID) && n++ >= nSkip)
					{
						return qe;
					}
				}
			}
			else
			{
				for (int i = siz - 1; i >= 0; i--)
				{
					final JDFQueueEntry qe = (JDFQueueEntry) v.elementAt(i);
					final NodeIdentifier ni2 = qe.getIdentifier();
					if (ni2.matches(nodeID) && --n <= nSkip)
					{
						return qe;
					}
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
	public int getQueueEntryPos(final String strQEntryID)
	{
		if (StringUtil.getNonEmpty(strQEntryID) == null)
		{
			return -1;
		}
		final VElement v = getQueueEntryVector();
		final int size = v.size();
		for (int i = 0; i < size; i++)
		{
			final JDFQueueEntry qe = (JDFQueueEntry) v.elementAt(i);
			if (strQEntryID.equals(qe.getQueueEntryID()))
			{
				return i;
			}
		}
		return -1;

	}

	// /////////////////////////////////////////////////////////////////////

	/**
	 * @return
	 */
	public synchronized JDFQueueEntry getNextExecutableQueueEntry()
	{
		return getNextExecutableQueueEntry(null);
	}

	/**
	 * Get the next QueueEntry to be processed the first entry with highest priority gets selected if deviceID is specified, the entries with an explicit non matching deviceID are
	 * ignored the status of the QueueEntry MUST be waiting
	 *
	 * proxy and represents previously submitted jobs as waiting
	 *
	 * @param cb
	 *
	 * @return the executable queueEntry, null if none is available
	 */
	public synchronized JDFQueueEntry getNextExecutableQueueEntry(ExecuteCallback cb)
	{
		if (cb == null)
		{
			cb = executeCallback;
		}

		JDFQueueEntry theEntry = null;

		if (!canExecute())
		{
			return theEntry;
		}

		JDFQueueEntry qe = getFirstChildElement(JDFQueueEntry.class);
		while (qe != null)
		{

			final String qeStatus = qe.getAttribute(AttributeName.STATUS);
			if ("Waiting".equals(qeStatus))
			{
				final String qeActivation = StringUtil.getNonEmpty(qe.getAttribute(AttributeName.ACTIVATION));
				if ((qeActivation == null || "Active".equals(qeActivation)) && (cb == null || cb.canExecute(qe)))
				{
					theEntry = qe;
					break;
				}
			}
			else if (!"Running".equals(qeStatus))
			{
				// the queue is sorted and we have passed the waiting and running
				break;
			}
			qe = qe.getNextSiblingElement(JDFQueueEntry.class);
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
		final EnumQueueStatus status = getQueueStatus();
		if (EnumQueueStatus.Blocked.equals(status))
		{
			return false;
		}
		if (EnumQueueStatus.Held.equals(status))
		{
			return false;
		}
		if (EnumQueueStatus.Full.equals(status))
		{
			return false;
		}
		if (EnumQueueStatus.Running.equals(status))
		{
			return false;
		}
		if (EnumQueueStatus.Waiting.equals(status))
		{
			return true;
		}
		return !maxRunning();
	}

	/**
	 * if the incoming queue processor is accepting new entries
	 *
	 * @return true, if new entries are accepted
	 */
	public boolean canAccept()
	{
		final EnumQueueStatus status = getQueueStatus();
		if (EnumQueueStatus.Blocked.equals(status))
		{
			return false;
		}
		if (EnumQueueStatus.Closed.equals(status))
		{
			return false;
		}
		if (EnumQueueStatus.Full.equals(status))
		{
			return false;
		}
		if (EnumQueueStatus.Waiting.equals(status))
		{
			return true;
		}
		final boolean b = hasAttribute(AttributeName.QUEUESIZE) ? numEntries(null) < getQueueSize() : !maxWaiting();
		return b;
	}

	/**
	 * remove all entries with Status=Removed and any entries over maxCompleted that are either aborted or completed @see {@link JDFQueueEntry} .isCompleted()
	 *
	 */

	@Override
	public synchronized void cleanup()
	{
		final VElement v = getQueueEntryVector();
		if (v != null)
		{
			final int siz = v.size();
			int nBad = 0;
			for (int i = 0; i < siz; i++)
			{
				final JDFQueueEntry qe = (JDFQueueEntry) v.elementAt(i);
				final EnumQueueEntryStatus status = qe.getQueueEntryStatus();
				if (isWildCard(qe.getQueueEntryID()))
				{
					if (cleanupCallback != null)
					{
						cleanupCallback.cleanEntry(qe);
					}
					qe.deleteNode();
				}
				else if (EnumQueueEntryStatus.Removed.equals(status))
				{
					if (cleanupCallback != null)
					{
						cleanupCallback.cleanEntry(qe);
					}

					qe.deleteNode();
				}
				else if (maxCompletedEntries >= 0 && qe.isCompleted())
				{
					if (nBad++ >= maxCompletedEntries)
					{
						if (cleanupCallback != null)
						{
							cleanupCallback.cleanEntry(qe);
						}

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
	 * @deprecated use 3 parameter method
	 */
	@Deprecated
	synchronized public JDFQueue copyToResponse(final JDFResponse resp, final JDFQueueFilter filter)
	{

		return copyToResponse(resp, filter, null);
	}

	/**
	 * copies this to the JDF Response resp, applying the filters defined in filter
	 *
	 * @param resp the JDFResponse to copy this to
	 * @param filter the QueueFilter that sets the queue size
	 * @param priorQueue the prior que to apply thr filter to incase updategranularity is incremental
	 * @return the copied queue
	 */
	synchronized public JDFQueue copyToResponse(final JDFResponse resp, final JDFQueueFilter filter, final JDFQueue priorQueue)
	{
		if (resp == null)
		{
			return null;
		}
		resp.removeChildren(ElementName.QUEUE, null, null);
		final JDFQueue newQueue;
		if (EnumType.QueueStatus.equals(resp.getEnumType()))
		{
			if (filter != null)
			{
				newQueue = filter.copy(this, priorQueue, resp);
			}
			else
			{
				newQueue = (JDFQueue) resp.copyElement(this, null);
			}
		}
		else
		{
			newQueue = null;
		}
		return newQueue;

	}

	/**
	 * return the number of entries
	 *
	 * @param qeStatus the queueentry status of the enries to count, if null, do not filter
	 * @return the number of active processors
	 */
	public int numEntries(final EnumQueueEntryStatus qeStatus)
	{
		int n = 0;
		JDFQueueEntry qe = getFirstChildElement(JDFQueueEntry.class);
		final String stat = qeStatus == null ? null : qeStatus.getName();
		while (qe != null)
		{
			if (stat == null || stat.equals(qe.getAttribute(AttributeName.STATUS)))
			{
				n++;
			}
			qe = qe.getNextSiblingElement(JDFQueueEntry.class);
		}
		return n;
	}

	/**
	 * @return true if the number of entries running is exceeded - performance
	 */
	private boolean maxRunning()
	{
		return maxRunningEntries > 0 && hasFewerEntries(EnumQueueEntryStatus.Running, maxRunningEntries);
	}

	/**
	 * @return true if the number of entries running is exceeded - performance
	 */
	private boolean maxWaiting()
	{
		return maxWaitingEntries > 0 && hasFewerEntries(EnumQueueEntryStatus.Waiting, maxWaitingEntries);
	}

	/**
	 * return true if the queue has less than entries elements
	 *
	 * @param qeStatus the status of the JDFQueueEntry to count
	 * @param entries the number of entries after which we stop counting
	 * @return true if the queue has < entries entries with a given QE Status
	 */
	public boolean hasFewerEntries(final EnumQueueEntryStatus qeStatus, final int entries)
	{
		int n = 0;
		JDFQueueEntry qe = getFirstChildElement(JDFQueueEntry.class);
		final String stat = qeStatus == null ? null : qeStatus.getName();
		while (qe != null)
		{
			if (stat == null || stat.equals(qe.getAttribute(AttributeName.STATUS)))
			{
				n++;
				if (n >= entries)
					return true;
			}
			qe = qe.getNextSiblingElement(JDFQueueEntry.class);
		}
		return false;
	}

	/**
	 * make this a smart queue when modifying queueentries
	 *
	 * @param _automated automate if true
	 */
	public void setAutomated(final boolean _automated)
	{
		setAutomated(_automated, true);
	}

	/**
	 * make this a smart queue when modifying queueentries
	 *
	 * @param _automated automate if true
	 */
	public void setAutomated(final boolean _automated, boolean recalc)
	{
		automated = _automated;
		if (automated && recalc)
		{
			setStatusFromEntries();
		}
	}

	/**
	 * is this a smart queue when modifying queueentries
	 *
	 * @return true if this is automated
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
		{
			return super.getQueueSize();
		}
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
				final boolean maxRunning = maxRunning();
				final boolean maxWaiting = maxWaiting();
				if (!maxRunning)
				{
					if (!maxWaiting)
					{
						newStatus = EnumQueueStatus.Waiting;
					}
					else
					{
						newStatus = EnumQueueStatus.Closed;
					}

				}
				else if (!maxWaiting)
				{
					newStatus = EnumQueueStatus.Running;
				}
				else
				{
					newStatus = EnumQueueStatus.Full;
				}
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
		{
			setQueueStatus(newStatus);
		}

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
		if (queueSorter == null)
			queueSorter = new QueueEntryComparator();

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
	 * @param _maxCompletedEntries the maxCompletedEntries to set
	 */
	public void setMaxCompletedEntries(final int _maxCompletedEntries)
	{
		this.maxCompletedEntries = _maxCompletedEntries;
		// VElement v=null;
		if (automated)
		{
			cleanup();
		}
	}

	/**
	 * @return the maxRunningEntries
	 */
	public int getMaxRunningEntries()
	{
		return maxRunningEntries;
	}

	/**
	 * @param _maxRunningEntries the maxRunningEntries to set
	 */
	public void setMaxRunningEntries(final int _maxRunningEntries)
	{
		this.maxRunningEntries = _maxRunningEntries;
		if (automated)
		{
			setStatusFromEntries();
		}
	}

	/**
	 * @param _maxWaitingEntries the setMaxWaitingEntries to set, excluding held entries
	 */
	public void setMaxWaitingEntries(final int _maxWaitingEntries)
	{
		this.maxWaitingEntries = _maxWaitingEntries;
		if (automated)
		{
			setStatusFromEntries();
		}
	}

	/**
	 * @param _cleanupCallback the cleanupCallback to set
	 */
	public void setCleanupCallback(final CleanupCallback _cleanupCallback)
	{
		this.cleanupCallback = _cleanupCallback;
	}

	/**
	 * @param _callback the ExecuteCallback to set
	 */
	public void setExecuteCallback(final ExecuteCallback _callback)
	{
		this.executeCallback = _callback;
	}

	/**
	 * @param _queueSorter the queueSorter to set sets the Comparator to sort this queuewith
	 */
	public void setQueueSorter(final Comparator<KElement> _queueSorter)
	{
		this.queueSorter = _queueSorter;
	}

	/**
	 * @param qe
	 */
	public void sortChild(final JDFQueueEntry qe)
	{
		if (queueSorter == null)
			queueSorter = new QueueEntryComparator();
		sortChild(qe, queueSorter);
	}
}
