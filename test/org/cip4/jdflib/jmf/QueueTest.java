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
package org.cip4.jdflib.jmf;

import junit.framework.TestCase;

import org.cip4.jdflib.auto.JDFAutoQueue.EnumQueueStatus;
import org.cip4.jdflib.auto.JDFAutoQueueEntry.EnumQueueEntryStatus;
import org.cip4.jdflib.auto.JDFAutoQueueFilter.EnumQueueEntryDetails;
import org.cip4.jdflib.core.AttributeName;
import org.cip4.jdflib.core.ElementName;
import org.cip4.jdflib.core.JDFDoc;
import org.cip4.jdflib.core.VElement;
import org.cip4.jdflib.datatypes.JDFAttributeMap;
import org.cip4.jdflib.util.JDFDate;
import org.cip4.jdflib.util.StatusCounter;

import com.sun.org.apache.bcel.internal.classfile.Attribute;

/**
 * @author MuchaD
 *
 * This implements the first fixture with unit tests for class JDFQueue.
 */
public class QueueTest extends TestCase
{
    private JDFQueue q;
    protected static int iThread;
    protected class QueueTestThread implements Runnable
    {
        public void run()
        {
            int t=1000*iThread++;
            for(int i=0;i<100;i++)
            {
                final JDFQueueEntry qe = q.appendQueueEntry();
                qe.setQueueEntryID("q"+t+"_"+i);
                qe.setPriority((i*7)%100);
                qe.setQueueEntryStatus((t%2000==0) ? EnumQueueEntryStatus.Waiting :  EnumQueueEntryStatus.Held);
            }
            iThread--;
        }
    }

    public String toString()
    {
        return q==null ? "null" : q.toString();
    }
    public void testGetQueueEntry()
    {
        assertEquals("qe2",q.getQueueEntry(1).getQueueEntryID(),"qe2");
        assertEquals("qe2",q.getQueueEntry("qe1").getQueueEntryID(),"qe1");
        assertEquals("qe2",q.getQueueEntry("qe2").getQueueEntryID(),"qe2");
        assertEquals("qe2",q.getQueueEntry("qe3").getQueueEntryID(),"qe3");
        assertNull("qe6",q.getQueueEntry("qe6"));
        assertEquals("qe6",-1,q.getQueueEntryPos("qe6"));
        assertEquals("qe2",1,q.getQueueEntryPos("qe2"));
    }

    public void testGetTimes()
    {
        JDFQueueEntry qe=q.getQueueEntry(0);
        qe.setQueueEntryID("qe1");
        JDFDate d=qe.getEndTime();
        assertNull("date",d);
        qe.setEndTime(null);
        d=qe.getEndTime();
        assertEquals("date",d.getTimeInMillis(),new JDFDate().getTimeInMillis(),1000);        
    }

    public void testEndTime()
    {
        JDFQueueEntry _qe = (JDFQueueEntry) new JDFDoc(ElementName.QUEUEENTRY).getRoot();
        _qe.getEndTime();
    }
    
    public void testThreads()
    {
        q.setAutomated(true);
        q.removeChildren(ElementName.QUEUEENTRY, null,null);
        for(int i=0;i<10;i++)
        {
            final QueueTestThread queueTestThread = new QueueTestThread();
            new Thread(queueTestThread).start();
        }
        // now also zapp some...
        for(int j=0;j<100;j++)
        {
            JDFQueueEntry qex=q.getNextExecutableQueueEntry();
            if(qex!=null)
            {
                qex.setQueueEntryStatus(EnumQueueEntryStatus.Running);
                qex.setQueueEntryStatus(EnumQueueEntryStatus.Completed);
            }
        }
        while(iThread>0)
            StatusCounter.sleep(100); // wait for threads to be over
        assertEquals(q.getQueueSize(), 1000);
        int prio=999999;
        VElement v=q.getQueueEntryVector();
        for(int i=0;i<v.size();i++)
        {
            JDFQueueEntry qe=(JDFQueueEntry)v.elementAt(i);
            System.out.println(qe.getPriority()+" "+qe.getQueueEntryID()+" "+qe.getQueueEntryStatus());
            assertTrue(""+i,prio>=qe.getSortPriority());
            prio=qe.getSortPriority();
        }
        v=q.getQueueEntryVector(new JDFAttributeMap(AttributeName.STATUS,"Completed"),null);
        assertEquals(v.size(), 100);
        v=q.getQueueEntryVector(new JDFAttributeMap(AttributeName.STATUS,"Running"),null);
        assertNull(v);
        v=q.getQueueEntryVector(new JDFAttributeMap(AttributeName.STATUS,"Held"),null);
        assertEquals(v.size(), 500);
        v=q.getQueueEntryVector(new JDFAttributeMap(AttributeName.STATUS,"Waiting"),null);
        assertEquals(v.size(), 400);
    }

/////////////////////////////////////////////////////////////////////////////

    public void testGetQueueEntryVector()
    {
        assertEquals(5,q.getQueueEntryVector().size()); 
        assertEquals(2,q.getQueueEntryVector(new JDFAttributeMap("Status",EnumQueueEntryStatus.Waiting),null).size()); 
    }

    public void testCanExecute()
    {
        assertFalse(q.canExecute());
        q.maxRunningEntries=2;
        assertTrue(q.canExecute());
        q.setQueueStatus(EnumQueueStatus.Held);
        assertFalse(q.canExecute());
        q.setQueueStatus(EnumQueueStatus.Waiting);
        assertTrue("note that this is inconsistent",q.canExecute());
    }

/////////////////////////////////////////////////////////////////////////////

    public void testGetNextExecutableQueueEntry()
    {
        assertNull(q.getNextExecutableQueueEntry());
        q.maxRunningEntries=2;
        assertEquals(q.getNextExecutableQueueEntry(), q.getQueueEntry("qe2"));
        q.setQueueStatus(EnumQueueStatus.Held);
        assertNull(q.getNextExecutableQueueEntry());
        q.setQueueStatus(EnumQueueStatus.Waiting);
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
            qe.setQueueEntryStatus(EnumQueueEntryStatus.Waiting);
        }

    }

/////////////////////////////////////////////////////////////////////////////

    public void testCleanup()
    {
        JDFQueueEntry qe=q.appendQueueEntry();
        qe.setQueueEntryStatus(EnumQueueEntryStatus.Removed);
        q.maxCompletedEntries=1;
        q.cleanup();
        assertFalse(q.getQueueEntryVector().contains(qe));
        assertEquals("removed completed and aborted",q.numEntries(null), 5);
        q.maxCompletedEntries=0;
        q.cleanup();
        assertEquals("removed completed and aborted",q.numEntries(null), 4);
    }
/////////////////////////////////////////////////////////////////////////////

    public void testSetQueueEntryStatus()
    {
        JDFQueueEntry qe=q.getQueueEntry("qe2");
        assertEquals(q.getQueueEntryPos("qe2"), 1);
        q.setAutomated(true);
        int l=q.numEntries(null);
        qe.setQueueEntryStatus(EnumQueueEntryStatus.Completed);
        assertEquals(q.numEntries(null), l);
        assertEquals(q.getQueueEntryPos("qe2"), 2);
        qe.setQueueEntryStatus(EnumQueueEntryStatus.Running);
        assertEquals(q.numEntries(null), l);
        assertEquals(q.getQueueEntryPos("qe2"), 0);
        qe=q.getQueueEntry("qe1");
        qe.setQueueEntryStatus(EnumQueueEntryStatus.Running);
        assertEquals(q.numEntries(null), l);
        assertEquals(q.getQueueEntryPos("qe1"), 1);
        
        qe=q.getQueueEntry("qe5");
        qe.setQueueEntryStatus(EnumQueueEntryStatus.Aborted);
        assertEquals(q.numEntries(null), l);
        assertEquals(q.getQueueEntryPos("qe5"), 4);
        
        qe=q.getQueueEntry("qe1");
        qe.setQueueEntryStatus(EnumQueueEntryStatus.Aborted);
        assertEquals(q.numEntries(null), l);
        assertEquals(q.getQueueEntryPos("qe1"), 3);

        
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
    
    public void testgetQueueSize()
    {
        assertEquals("no size set - count entries",q.getQueueSize(), 5);
        q.setQueueSize(10);
        assertEquals(q.getQueueSize(), 10);
    }
}