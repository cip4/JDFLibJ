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
import org.cip4.jdflib.jmf.JDFQueue.CleanupCallback;
import org.cip4.jdflib.node.JDFNode.NodeIdentifier;
import org.cip4.jdflib.util.JDFDate;
import org.cip4.jdflib.util.StatusCounter;


/**
 * @author MuchaD
 *
 * This implements the first fixture with unit tests for class JDFQueue.
 */
public class QueueTest extends TestCase
{
    private JDFQueue q;
    protected static int iThread=0;
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

    private class MyClean extends CleanupCallback
    {

        public int i=0;
        /* (non-Javadoc)
         * @see org.cip4.jdflib.jmf.JDFQueue.CleanupCallback#cleanEntry(org.cip4.jdflib.jmf.JDFQueueEntry)
         */
        @Override
        public void cleanEntry(JDFQueueEntry qe)
        {
            i++;
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
    public void testGetQueueEntryByIdentifier()
    {
        q.getQueueEntry(1).setJobID("j7");
        NodeIdentifier ni=new NodeIdentifier("j7",null,null);
        assertEquals(q.getQueueEntry(ni,0), q.getQueueEntry(1));
        assertEquals(q.getQueueEntry(ni,-1), q.getQueueEntry(1));
        assertNull(q.getQueueEntry(ni,-2));
        assertNull(q.getQueueEntry(ni,1));
        q.getQueueEntry(3).setJobID("j7");
        
        assertEquals(q.getQueueEntry(ni,0), q.getQueueEntry(1));
        assertEquals(q.getQueueEntry(ni,-1), q.getQueueEntry(3));
        assertEquals(q.getQueueEntry(ni,1), q.getQueueEntry(3));
        assertEquals(q.getQueueEntry(ni,-2), q.getQueueEntry(1));
        assertNull(q.getQueueEntry(ni,-3));
        assertNull(q.getQueueEntry(ni,4));
    }
    public void testGetQueueEntryVectorByIdentifier()
    {
        NodeIdentifier ni=new NodeIdentifier("j2",null,null);
        assertEquals(q.getQueueEntryVector(ni).elementAt(0), q.getQueueEntry(1));
        assertEquals(q.getQueueEntryVector(null).elementAt(0), q.getQueueEntry(0));
        assertEquals(q.getQueueEntryVector(null).size(), q.numChildElements(ElementName.QUEUEENTRY, null));
    }

    public void testGetTimes()
    {
        JDFQueueEntry qe=q.getQueueEntry(0);
        qe.setQueueEntryID("qe1");
        JDFDate d=qe.getEndTime();
        assertNull("date",d);
        qe.setEndTime(null);
        d=qe.getEndTime();
        assertEquals("date",d.getTimeInMillis(),new JDFDate().getTimeInMillis(),30000);        
    }
    public void testFlushQueue()
    {
        JDFQueueFilter qf=(JDFQueueFilter) new JDFDoc(ElementName.QUEUEFILTER).getRoot();  
        qf.appendQueueEntryDef("qe5");
        VElement v=q.flushQueue(qf);
        assertEquals(v.size(),1);
        assertEquals(((JDFQueueEntry)v.get(0)).getQueueEntryID(),"qe5");
        assertEquals(q.numEntries(null), 4);

    }

    public void testThreads()
    {
        q.setAutomated(true);
        q.removeChildren(ElementName.QUEUEENTRY, null,null);
        q.setMaxCompletedEntries(999999999);
        for(int i=0;i<10;i++)
        {
            final QueueTestThread queueTestThread = new QueueTestThread();
            new Thread(queueTestThread).start();
        }
        // now also zapp some...
        for(int j=0;j<100;j++)
        {
            JDFQueueEntry qex=q.getNextExecutableQueueEntry(null,null);
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
        q.setMaxRunningEntries(2);
        assertTrue(q.canExecute());
        q.setQueueStatus(EnumQueueStatus.Held);
        assertFalse(q.canExecute());
        q.setQueueStatus(EnumQueueStatus.Waiting);
        assertTrue("note that this is inconsistent",q.canExecute());
    }

/////////////////////////////////////////////////////////////////////////////

    public void testGetNextExecutableQueueEntry()
    {
        assertNull(q.getNextExecutableQueueEntry(null,null));
        q.setMaxRunningEntries(2);
        assertEquals(q.getNextExecutableQueueEntry(null,null), q.getQueueEntry("qe2"));
        q.setQueueStatus(EnumQueueStatus.Held);
        assertNull(q.getNextExecutableQueueEntry(null,null));
        q.setQueueStatus(EnumQueueStatus.Waiting);
        assertEquals(q.getNextExecutableQueueEntry(null,null), q.getQueueEntry("qe2"));
        q.getQueueEntry("qe4").setQueueEntryStatus(EnumQueueEntryStatus.Waiting);
        q.getQueueEntry("qe2").setDeviceID("d1");
        assertEquals(q.getNextExecutableQueueEntry(null,null), q.getQueueEntry("qe2"));
        assertEquals(q.getNextExecutableQueueEntry("d1",null), q.getQueueEntry("qe2"));
        q.getQueueEntry("qe4").setQueueEntryStatus(EnumQueueEntryStatus.Waiting);
        assertEquals(q.getNextExecutableQueueEntry("d2",null), q.getQueueEntry("qe1"));
        q.getQueueEntry("qe1").setDeviceID("d1");
        assertEquals(q.getNextExecutableQueueEntry("d2",null), q.getQueueEntry("qe4"));
        assertEquals(q.getNextExecutableQueueEntry("d1","foo:foo2"), q.getQueueEntry("qe2"));
        q.getQueueEntry("qe2").setAttribute("foo:foo2","bar","www.foo");
        assertEquals(q.getNextExecutableQueueEntry("d1","foo:foo2"), q.getQueueEntry("qe1"));
    }


/////////////////////////////////////////////////////////////////////////////

    public void testCleanup()
    {
        
        JDFQueueEntry qe=q.appendQueueEntry();
        MyClean myClean = new MyClean();
        assertEquals(myClean.i, 0);
        q.setCleanupCallback(myClean);
        qe.setQueueEntryStatus(EnumQueueEntryStatus.Removed);
        q.setAutomated(true);
        q.setMaxCompletedEntries(1);
        assertFalse(q.getQueueEntryVector().contains(qe));
        assertEquals(myClean.i, 1);
        assertEquals("removed completed and aborted",q.numEntries(null), 5);
        q.setMaxCompletedEntries(0);
        q.cleanup();
        assertEquals(myClean.i, 2);

        assertEquals("removed completed and aborted",q.numEntries(null), 4);
    }
/////////////////////////////////////////////////////////////////////////////

    public void testCopyToResponse()
    {
        JDFResponse r=JDFJMF.createJMF(JDFMessage.EnumFamily.Response, EnumType.AbortQueueEntry).getResponse(0);
        JDFQueueFilter qf=(JDFQueueFilter) new JDFDoc(ElementName.QUEUEFILTER).getRoot();
        qf.setMaxEntries(3);
        JDFQueue q2=q.copyToResponse(r, qf);
        assertEquals(q2,r.getQueue(0));
        assertEquals(q2.numEntries(null), 3);
        assertNotSame(q, q2);
        assertTrue(q.numEntries(null)>3);
        q2=q.copyToResponse(r, qf);
        assertEquals(q2,r.getQueue(0));
        assertNull(r.getElement("Queue",null,1));
        assertEquals(q2.numEntries(null), 3);
        assertNotSame(q, q2);
        assertTrue(q.numEntries(null)>3);
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
        qe.setJobID("j1");
        qe.setQueueEntryStatus(EnumQueueEntryStatus.Waiting);
        qe.setPriority(5);
        qe=q.appendQueueEntry();
        qe.setQueueEntryStatus(EnumQueueEntryStatus.Waiting);
        qe.setPriority(55);
        qe.setQueueEntryID("qe2");
        qe.setJobID("j2");
        qe=q.appendQueueEntry();
        qe.setQueueEntryStatus(EnumQueueEntryStatus.Held);
        qe.setPriority(99);
        qe.setQueueEntryID("qe3");
        qe.setJobID("j3");
        qe=q.appendQueueEntry();
        qe.setQueueEntryStatus(EnumQueueEntryStatus.Completed);
        qe.setQueueEntryID("qe4");
        qe.setJobID("j4");
        qe=q.appendQueueEntry();
        qe.setQueueEntryStatus(EnumQueueEntryStatus.Running);
        qe.setQueueEntryID("qe5");
        qe.setJobID("j5");
        iThread=0;
    }

    public void testgetQueueSize()
    {
        assertEquals("no size set - count entries",q.getQueueSize(), 5);
        q.setQueueSize(10);
        assertEquals(q.getQueueSize(), 10);
    }
}