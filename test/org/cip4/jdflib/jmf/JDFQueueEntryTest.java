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
package org.cip4.jdflib.jmf;

import junit.framework.TestCase;

import org.cip4.jdflib.auto.JDFAutoQueue.EnumQueueStatus;
import org.cip4.jdflib.auto.JDFAutoQueueEntry.EnumQueueEntryStatus;
import org.cip4.jdflib.core.AttributeName;
import org.cip4.jdflib.core.ElementName;
import org.cip4.jdflib.core.JDFDoc;
import org.cip4.jdflib.core.VElement;
import org.cip4.jdflib.datatypes.JDFAttributeMap;
import org.cip4.jdflib.jmf.JDFMessage.EnumType;
import org.cip4.jdflib.node.JDFNode.NodeIdentifier;
import org.cip4.jdflib.util.JDFDate;
import org.cip4.jdflib.util.StatusCounter;


/**
 * @author MuchaD
 *
 * This implements the first fixture with unit tests for class JDFQueue.
 */
public class JDFQueueEntryTest extends TestCase
{
    private JDFQueue q;
    public String toString()
    {
        return q==null ? "null" : q.toString();
    }
 

    public void testEndTime()
    {
        JDFQueueEntry _qe = (JDFQueueEntry) new JDFDoc(ElementName.QUEUEENTRY).getRoot();
        JDFDate d=_qe.getEndTime();
        assertNull(d);
    }

    public void testGetNextStatusVector()
    {
        JDFQueueEntry qe = (JDFQueueEntry) new JDFDoc(ElementName.QUEUEENTRY).getRoot();
        qe.setQueueEntryStatus(EnumQueueEntryStatus.Aborted);
        assertTrue(qe.getNextStatusVector().contains(EnumQueueEntryStatus.PendingReturn));
        assertTrue(qe.getNextStatusVector().contains(EnumQueueEntryStatus.Aborted));
        assertFalse(qe.getNextStatusVector().contains(EnumQueueEntryStatus.Running));
    }

  

/////////////////////////////////////////////////////////////////////////////

    public void testSetPriority()
    {
        JDFQueueEntry qe=q.getQueueEntry("qe2");
        assertEquals(q.getQueueEntryPos("qe2"), 1);
        q.setAutomated(true);
        int l=q.numEntries(null);
        qe.setPriority(99);
        assertEquals(q.numEntries(null), l);
        assertEquals(q.getQueueEntryPos("qe2"), 0);

        qe.setPriority(0);
        assertEquals(q.numEntries(null), l);
        assertEquals(q.getQueueEntryPos("qe2"), 1);
        q.removeChildren(ElementName.QUEUEENTRY, null,null);
        for(int i=0;i<1000;i++)
        {
            qe = q.appendQueueEntry();
            qe.setQueueEntryID("q"+i);
            qe.setPriority((i*7)%100);
            qe.setQueueEntryStatus((i%3!=0) ? EnumQueueEntryStatus.Waiting : EnumQueueEntryStatus.Running);
        }
        int n=9999999;
        for(int i=0;i<1000;i++)
        {
            qe=q.getQueueEntry(i);
            int n2=qe.getSortPriority();
            assertTrue("queue is sorted: "+i,n2<=n);
            n=n2;
        }
    }


/////////////////////////////////////////////////////////////////////////////

    public void testSetQueueEntryStatus()
    {
        JDFQueueEntry qe=q.getQueueEntry("qe2");
        assertEquals(q.getQueueEntryPos("qe2"), 1);
        q.setAutomated(true);
        assertNull(q.getQueueStatus());
        q.setMaxRunningEntries(3);
        q.setMaxCompletedEntries(9999);
        int l=q.numEntries(null);
        qe.setQueueEntryStatus(EnumQueueEntryStatus.Completed);
        assertEquals(q.numEntries(null), l);
        assertEquals(q.getQueueEntryPos("qe2"), 2);
        assertEquals("3 is max", EnumQueueStatus.Waiting,q.getQueueStatus());
        qe.setQueueEntryStatus(EnumQueueEntryStatus.Running);
        assertEquals("3 is max", EnumQueueStatus.Waiting,q.getQueueStatus());
        assertEquals(q.numEntries(null), l);
        assertEquals(q.getQueueEntryPos("qe2"), 0);
        qe=q.getQueueEntry("qe1");
        qe.setQueueEntryStatus(EnumQueueEntryStatus.Running);
        assertEquals("3 is max", EnumQueueStatus.Running,q.getQueueStatus());
        assertEquals(q.numEntries(null), l);
        assertEquals(q.getQueueEntryPos("qe1"), 1);

        qe=q.getQueueEntry("qe5");
        qe.setQueueEntryStatus(EnumQueueEntryStatus.Aborted);
        assertEquals("3 is max", EnumQueueStatus.Waiting,q.getQueueStatus());
        assertEquals(q.numEntries(null), l);
        assertEquals(q.getQueueEntryPos("qe5"), 4);

        qe=q.getQueueEntry("qe1");
        qe.setQueueEntryStatus(EnumQueueEntryStatus.Aborted);
        assertEquals("3 is max", EnumQueueStatus.Waiting,q.getQueueStatus());
        assertEquals(q.numEntries(null), l);
        assertEquals(q.getQueueEntryPos("qe1"), 3);
        
        qe.setQueueEntryStatus(EnumQueueEntryStatus.Removed);
        assertEquals("3 is max", EnumQueueStatus.Waiting,q.getQueueStatus());
        assertEquals(q.numEntries(null), l-1);
        assertEquals(q.getQueueEntryPos("qe1"), -1);
        assertNull(q.getQueueEntry("qe1"));
    }

    /* (non-Javadoc)
     * @see junit.framework.TestCase#setUp()
     */
    protected void setUp() throws Exception
    {
        // TODO Auto-generated method stub
        super.setUp();
        JDFDoc doc = new JDFDoc(ElementName.QUEUE);
        q=(JDFQueue) doc.getRoot();
        JDFQueueEntry qe=q.appendQueueEntry();
        qe.setQueueEntryID("qe1");
        qe.setQueueEntryStatus(EnumQueueEntryStatus.Waiting);
        qe.setPriority(5);
        qe=q.appendQueueEntry();
        qe.setQueueEntryStatus(EnumQueueEntryStatus.Waiting);
        qe.setPriority(55);
        qe.setQueueEntryID("qe2");
        qe=q.appendQueueEntry();
        qe.setQueueEntryStatus(EnumQueueEntryStatus.Held);
        qe.setPriority(99);
        qe.setQueueEntryID("qe3");
        qe=q.appendQueueEntry();
        qe.setQueueEntryStatus(EnumQueueEntryStatus.Completed);
        qe.setQueueEntryID("qe4");
        qe=q.appendQueueEntry();
        qe.setQueueEntryStatus(EnumQueueEntryStatus.Running);
        qe.setQueueEntryID("qe5");
   }

 
}