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
/**
 ==========================================================================
 class JDFQueueEntry extends JDFResource
 ==========================================================================
 @COPYRIGHT Heidelberger Druckmaschinen AG, 1999-2001
 ALL RIGHTS RESERVED
 @Author sabjontopmail.de   using a code generator
 Warning! very preliminary test version. Interface subject to change without prior notice!
 Revision history:    ...
 **/
package org.cip4.jdflib.jmf;

import java.util.Comparator;
import java.util.Iterator;
import java.util.Vector;

import org.apache.xerces.dom.CoreDocumentImpl;
import org.cip4.jdflib.auto.JDFAutoQueueEntry;
import org.cip4.jdflib.core.AttributeName;
import org.cip4.jdflib.core.ElementName;
import org.cip4.jdflib.core.JDFNodeInfo;
import org.cip4.jdflib.core.KElement;
import org.cip4.jdflib.datatypes.JDFAttributeMap;
import org.cip4.jdflib.datatypes.VJDFAttributeMap;
import org.cip4.jdflib.ifaces.INodeIdentifiable;
import org.cip4.jdflib.node.JDFNode;
import org.cip4.jdflib.node.JDFNode.NodeIdentifier;
import org.cip4.jdflib.util.ContainerUtil;
import org.cip4.jdflib.util.JDFDate;

//----------------------------------
/**
 * @author prosirai
 *
 */
public class JDFQueueEntry extends JDFAutoQueueEntry implements Comparable, INodeIdentifiable
{
	public static class QueueEntryComparator implements Comparator
	{

		/*
		 * (non-Javadoc)
		 * 
		 * @see java.util.Comparator#compare(java.lang.Object, java.lang.Object)
		 */
		public int compare(Object a1, Object a2)
		{
			if (!(a1 instanceof KElement))
				return -1;
			if (!(a2 instanceof KElement))
				return 1;
			KElement o1 = (KElement) a1;
			KElement o2 = (KElement) a2;
			int i = o1.getNodeName().compareTo(o2.getNodeName());
			if (i != 0)
				return i;
			if ((o1 instanceof JDFQueueEntry) && (o2 instanceof JDFQueueEntry))
			{
				JDFQueueEntry q1 = (JDFQueueEntry) o1;
				JDFQueueEntry q2 = (JDFQueueEntry) o2;
				EnumQueueEntryStatus status1 = q1.getQueueEntryStatus();
				EnumQueueEntryStatus status2 = q2.getQueueEntryStatus();
				int s1 = (status1 == null) ? 0 : status1.getValue();
				int s2 = (status2 == null) ? 0 : status2.getValue();
				if (s1 != s2)
					return s1 - s2;
				if (q1.isCompleted())
				{
					JDFDate d1 = q1.getEndTime();
					JDFDate d2 = q1.getEndTime();
					if (d1 != null && d2 != null)
						return d1.compareTo(d2);
				}
				else
				{
					s1 = q1.getPriority();
					s2 = q2.getPriority();
					if (s1 != s2)
						return s2 - s1;
				}

				JDFDate d1 = q1.getStartTime();
				JDFDate d2 = q2.getStartTime();
				int d = ContainerUtil.compare(d1, d2);
				if (d != 0)
					return d;

				d1 = q1.getSubmissionTime();
				d2 = q2.getSubmissionTime();
				d = ContainerUtil.compare(d1, d2);
				if (d != 0)
					return d;
			}
			return 0;
		}
	}

	private static final long serialVersionUID = 1L;

	/**
	 * Constructor for JDFQueueEntry
	 * 
	 * @param myOwnerDocument
	 * @param qualifiedName
	 */
	public JDFQueueEntry(CoreDocumentImpl myOwnerDocument, String qualifiedName)
	{
		super(myOwnerDocument, qualifiedName);
	}

	/**
	 * Constructor for JDFQueueEntry
	 * 
	 * @param myOwnerDocument
	 * @param myNamespaceURI
	 * @param qualifiedName
	 */
	public JDFQueueEntry(CoreDocumentImpl myOwnerDocument, String myNamespaceURI, String qualifiedName)
	{
		super(myOwnerDocument, myNamespaceURI, qualifiedName);
	}

	/**
	 * Constructor for JDFQueueEntry
	 * 
	 * @param myOwnerDocument
	 * @param myNamespaceURI
	 * @param qualifiedName
	 * @param myLocalName
	 */
	public JDFQueueEntry(CoreDocumentImpl myOwnerDocument, String myNamespaceURI, String qualifiedName, String myLocalName)
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
		return "JDFQueueEntry[  --> " + super.toString() + " ]";
	}

	/**
	 * get part map vector
	 * 
	 * @return VJDFAttributeMap: vector of attribute maps, one for each part
	 */
	@Override
	public VJDFAttributeMap getPartMapVector()
	{
		return super.getPartMapVector();
	}

	/**
	 * set all parts to those define in vParts
	 * 
	 * @param vParts vector of attribute maps for the parts
	 */
	@Override
	public void setPartMapVector(VJDFAttributeMap vParts)
	{
		super.setPartMapVector(vParts);
	}

	/**
	 * set all parts to those defined by mPart
	 * 
	 * @param mPart attribute map for the part to set
	 */
	@Override
	public void setPartMap(JDFAttributeMap mPart)
	{
		super.setPartMap(mPart);
	}

	/**
	 * remove the part defined in mPart
	 * 
	 * @param mPart attribute map for the part to remove
	 */
	@Override
	public void removePartMap(JDFAttributeMap mPart)
	{
		super.removePartMap(mPart);
	}

	/**
	 * return true if this qe matches the input node identifier
	 * 
	 * @param ni
	 * @return
	 */
	public boolean matchesNodeIdentifier(NodeIdentifier ni)
	{
		if (ni == null)
			return false;
		final NodeIdentifier niThis = getIdentifier();
		return niThis.matches(ni);
	}

	/**
	 * return true if this qe matches the input QueueFilter
	 * 
	 * @param ni
	 * @return
	 */
	public boolean matchesQueueFilter(JDFQueueFilter filter)
	{
		if (filter == null)
			return true;
		return filter.matches(this);
	}

	/**
	 * check whether the part defined by mPart is included
	 * 
	 * @param mPart attribute map to look for
	 * @return boolean - returns true if the part exists
	 */
	@Override
	public boolean hasPartMap(JDFAttributeMap mPart)
	{
		return super.hasPartMap(mPart);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.cip4.jdflib.auto.JDFAutoQueueEntry#setPriority(int)
	 */
	@Override
	public void setPriority(int value)
	{
		int oldVal = hasAttribute(AttributeName.PRIORITY) ? getPriority() : -1;
		if (isAutomated() && value != oldVal)
		{
			final JDFQueue queue = (JDFQueue) getParentNode_KElement();
			synchronized (queue)
			{
				super.setPriority(value);
				queue.sortChildren();
			}
		}
		else if (value != oldVal) // non automated
		{
			super.setPriority(value);
		}
	}

	/**
	 * sort this into the queue based on current values assumes presorted queue
	 * 
	 * @param oldVal - the previous sort value, use -1 to sort from back
	 * @deprecated call JDFQueue.sortChildren()
	 */
	@Deprecated
	public void sortQueue(@SuppressWarnings("unused") int oldVal)
	{
		final JDFQueue queue = (JDFQueue) getParentNode_KElement();
		queue.sortChildren();
	}

	/**
	 * @return true if the parent queue is automated
	 */
	private boolean isAutomated()
	{
		KElement e = getParentNode_KElement();
		if (e instanceof JDFQueue)
		{
			return ((JDFQueue) e).isAutomated();
		}
		return false;
	}

	/**
	 * sets the QueueEntry/@Status if the queue is automated, also resorts the queue to reflect the new Status and sets
	 * the Queue/@Status based on the maximum number of concurrently running jobs also sets StartTime and EndTime
	 * appropriately if the queue is automated
	 * 
	 * @param value the queuentry status to set
	 * 
	 * @see org.cip4.jdflib.auto.JDFAutoQueueEntry#setQueueEntryStatus(org.cip4.jdflib.auto.JDFAutoQueueEntry.EnumQueueEntryStatus)
	 */
	@Override
	public void setQueueEntryStatus(EnumQueueEntryStatus value)
	{
		EnumQueueEntryStatus oldVal = getQueueEntryStatus();
		if (isAutomated() && !ContainerUtil.equals(oldVal, value))
		{
			final JDFQueue queue = (JDFQueue) getParentNode_KElement();
			synchronized (queue)
			{
				super.setQueueEntryStatus(value);
				if (isCompleted())
				{
					if (!hasAttribute(AttributeName.ENDTIME))
						super.setEndTime(new JDFDate());
					queue.cleanup();
				}
				if (EnumQueueEntryStatus.Running.equals(value))
				{
					if (!hasAttribute(AttributeName.STARTTIME))
						super.setStartTime(new JDFDate());
					removeAttribute(AttributeName.ENDTIME);

				}
				queue.sortChildren();
				queue.setStatusFromEntries();
			}
		}
		else if (!ContainerUtil.equals(oldVal, value)) // non automated
		{
			super.setQueueEntryStatus(value);
		}
	}

	/**
	 * gets the NodeIdetifier that matches this
	 * 
	 * @return
	 */
	public NodeIdentifier getIdentifier()
	{
		NodeIdentifier ni = new NodeIdentifier();
		ni.setTo(getJobID(), getJobPartID(), getPartMapVector());
		return ni;
	}

	/**
	 * gets the NodeIdetifier that matches this
	 * 
	 * @return
	 */
	public void setIdentifier(NodeIdentifier ni)
	{
		if (ni == null)
			return;
		setPartMapVector(ni.getPartMapVector());
		setJobID(ni.getJobID());
		setJobPartID(ni.getJobPartID());
	}

	/**
	 * get the next sibling queueentry
	 * 
	 * @return
	 */
	public JDFQueueEntry getNextQueueEntry()
	{
		return (JDFQueueEntry) getNextSiblingElement(ElementName.QUEUEENTRY, null);
	}

	/**
	 * get the previous sibling queueentry
	 * 
	 * @return
	 */
	public JDFQueueEntry getPreviousQueueEntry()
	{
		return (JDFQueueEntry) getPreviousSiblingElement(ElementName.QUEUEENTRY, null);
	}

	/**
	 * get the vector of valid next @Status values for this queue entry based on the current staus based on the table of
	 * valid queue entry transitions
	 * 
	 * @return Vector<EnumQueueEntryStatus> the vector of valid new stati
	 */
	public Vector<EnumQueueEntryStatus> getNextStatusVector()
	{
		Vector<EnumQueueEntryStatus> v = new Vector<EnumQueueEntryStatus>();

		final EnumQueueEntryStatus qesThis = getQueueEntryStatus();
		if (qesThis == null)
		{
			Iterator<EnumQueueEntryStatus> it = EnumQueueEntryStatus.iterator();
			while (it.hasNext())
				v.add(it.next());
		}
		else if (EnumQueueEntryStatus.Running.equals(qesThis))
		{
			v.add(EnumQueueEntryStatus.Running);
			v.add(EnumQueueEntryStatus.PendingReturn);
			v.add(EnumQueueEntryStatus.Completed);
			v.add(EnumQueueEntryStatus.Aborted);
		}
		else if (EnumQueueEntryStatus.Waiting.equals(qesThis))
		{
			v.add(EnumQueueEntryStatus.Running);
			v.add(EnumQueueEntryStatus.Waiting);
			v.add(EnumQueueEntryStatus.Held);
			v.add(EnumQueueEntryStatus.Removed);
			v.add(EnumQueueEntryStatus.PendingReturn);
			v.add(EnumQueueEntryStatus.Aborted);
		}
		else if (EnumQueueEntryStatus.Held.equals(qesThis))
		{
			v.add(EnumQueueEntryStatus.Waiting);
			v.add(EnumQueueEntryStatus.Held);
			v.add(EnumQueueEntryStatus.Removed);
			v.add(EnumQueueEntryStatus.PendingReturn);
			v.add(EnumQueueEntryStatus.Aborted);
		}
		else if (EnumQueueEntryStatus.Removed.equals(qesThis))
		{
			v.add(EnumQueueEntryStatus.Removed);
		}
		else if (EnumQueueEntryStatus.Suspended.equals(qesThis))
		{
			v.add(EnumQueueEntryStatus.Running);
			v.add(EnumQueueEntryStatus.Suspended);
			v.add(EnumQueueEntryStatus.Aborted);
		}
		else if (EnumQueueEntryStatus.PendingReturn.equals(qesThis))
		{
			v.add(EnumQueueEntryStatus.PendingReturn);
			v.add(EnumQueueEntryStatus.Completed);
			v.add(EnumQueueEntryStatus.Aborted);
		}
		else if (EnumQueueEntryStatus.Completed.equals(qesThis))
		{
			v.add(EnumQueueEntryStatus.Waiting);
			v.add(EnumQueueEntryStatus.Removed);
			v.add(EnumQueueEntryStatus.PendingReturn);
			v.add(EnumQueueEntryStatus.Completed);
			v.add(EnumQueueEntryStatus.Aborted);
		}
		else if (EnumQueueEntryStatus.Aborted.equals(qesThis))
		{
			v.add(EnumQueueEntryStatus.Waiting);
			v.add(EnumQueueEntryStatus.Removed);
			v.add(EnumQueueEntryStatus.PendingReturn);
			v.add(EnumQueueEntryStatus.Aborted);
		}

		return v;
	}

	/**
	 * @return true if this entry is completed
	 */
	public boolean isCompleted()
	{
		EnumQueueEntryStatus status = getQueueEntryStatus();
		return // (status==null) ||
		EnumQueueEntryStatus.Completed.equals(status) || EnumQueueEntryStatus.Removed.equals(status)
				|| EnumQueueEntryStatus.Aborted.equals(status);
	}

	/**
	 * return a value based on QueueEntryStatus and Priority to sort the queue
	 * 
	 * @return int a priority for sorting - low = back
	 */
	@Deprecated
	public int getSortPriority()
	{
		return getSortPriority(getQueueEntryStatus(), getPriority());
	}

	/**
	 * return a value based on QueueEntryStatus and Priority to sort the queue the status is the major order whereas the
	 * priority is used to order within regions of identical status
	 * 
	 * @return int a priority for sorting - low value = back of queue, high value = front of queue
	 */
	@Deprecated
	public static int getSortPriority(EnumQueueEntryStatus status, int priority)
	{
		int sort = (status == null) ? 0 : 10000 - 1000 * status.getValue();
		sort += priority;
		return sort;
	}

	/**
	 * populates this queuentry with the relevant parameters extracted from a JDF jobid, partmap, jobpartid etc.
	 * 
	 * @param jdf
	 */
	public void setFromJDF(JDFNode jdf)
	{
		if (jdf == null)
			return;
		setJobID(jdf.getJobID(true));
		setJobPartID(jdf.getJobPartID(false));
		setPartMapVector(jdf.getPartMapVector());

		if (!hasAttribute(AttributeName.PRIORITY))
		{
			JDFNodeInfo ni = jdf.getInheritedNodeInfo("@" + AttributeName.PRIORITY);
			if (ni != null)
				copyAttribute(AttributeName.PRIORITY, ni, null, null, null);
		}

		this.eraseEmptyAttributes(true);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Comparable#compareTo(java.lang.Object)
	 */
	public int compareTo(Object arg0)
	{
		return new QueueEntryComparator().compare(this, arg0);
	}

}
