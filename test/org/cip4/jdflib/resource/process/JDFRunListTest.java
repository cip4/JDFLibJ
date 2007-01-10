/*
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

package org.cip4.jdflib.resource.process;

import java.util.Iterator;

import junit.framework.TestCase;

import org.cip4.jdflib.core.ElementName;
import org.cip4.jdflib.core.JDFDoc;
import org.cip4.jdflib.core.KElement;
import org.cip4.jdflib.core.VElement;
import org.cip4.jdflib.core.JDFResourceLink.EnumUsage;
import org.cip4.jdflib.datatypes.JDFIntegerRange;
import org.cip4.jdflib.datatypes.JDFIntegerRangeList;
import org.cip4.jdflib.node.JDFNode;
import org.cip4.jdflib.pool.JDFResourcePool;
import org.cip4.jdflib.resource.JDFResource.EnumPartIDKey;
import org.cip4.jdflib.resource.process.JDFRunList.JDFRunData;

public class JDFRunListTest extends TestCase
{

    public JDFRunListTest (String arg0)
    {
        super(arg0);
    }

    public final void testGetFileURL()
    {
        JDFDoc doc = new JDFDoc("JDF");
        JDFNode root = doc.getJDFRoot();
        JDFRunList rl=(JDFRunList) root.addResource(ElementName.RUNLIST,null,EnumUsage.Input,null,null,null,null);
        rl.setFileURL("./foo/bar.pdf");
        rl.setDirectory("File://c/fnarf");
        assertEquals(rl.getFileURL(), "File://c/fnarf/foo/bar.pdf");
      }

    public final void testSetPages()
    {
        JDFDoc doc = new JDFDoc("JDF");
        JDFNode root = doc.getJDFRoot();
        JDFRunList rl=(JDFRunList) root.addResource(ElementName.RUNLIST,null,EnumUsage.Input,null,null,null,null);
        final JDFIntegerRangeList integerRangeList = new JDFIntegerRangeList(new JDFIntegerRange(0,-1,8));
        rl.setPages(integerRangeList);
        assertEquals(rl.getPages(), integerRangeList);
        assertEquals(rl.getNPage(), 8);
     }
    
    
    public final void testGetMimeType()
    {
        JDFDoc doc = new JDFDoc("JDF");
        JDFNode root = doc.getJDFRoot();
        JDFResourcePool resPool = root.getCreateResourcePool();
            KElement kElem = resPool.appendResource(ElementName.RUNLIST, null, null);
        assertTrue(kElem instanceof JDFRunList);
        JDFRunList ruli=(JDFRunList) kElem;
        assertNull(ruli.getFileMimeType());
        kElem.setXPathAttribute("LayoutElement/FileSpec/@MimeType","application/pdf");
        assertEquals(ruli.getFileMimeType(),"application/pdf");
    }
    /*
     * Test method for 'org.cip4.jdflib.resource.process.JDFMedia.setDimensionCM(JDFXYPair)'
     */
    public final void testGetNPage() throws Exception
    {
        JDFDoc doc = new JDFDoc("JDF");
        JDFNode root = doc.getJDFRoot();
        JDFResourcePool resPool = root.getCreateResourcePool();
        JDFRunList rl = (JDFRunList)resPool.appendResource(ElementName.RUNLIST, null, null);
        JDFRunList rlp=(JDFRunList)rl.addPartition(EnumPartIDKey.Run, "r1");
        rlp.setPages(new JDFIntegerRangeList("1 3 5 7"));
        assertEquals(rlp.getNPage(), 4);
        rlp.setNPage(3);
        assertEquals(rlp.getNPage(), 3);
        JDFRunList rlp2=(JDFRunList)rl.addPartition(EnumPartIDKey.Run, "r2");
        rlp2.setPages(new JDFIntegerRangeList("0 2 4 6"));
        assertEquals(rlp2.getNPage(), 4);
        rlp2.setNPage(3);
        assertEquals(rlp2.getNPage(), 3);
    }
    /*
     * Test method for 'org.cip4.jdflib.resource.process.JDFMedia.setDimensionCM(JDFXYPair)'
     */
    public final void testGetIndexPartition() throws Exception
    {
        JDFDoc doc = new JDFDoc("JDF");
        JDFNode root = doc.getJDFRoot();
        JDFResourcePool resPool = root.getCreateResourcePool();
        JDFRunList rl = (JDFRunList)resPool.appendResource(ElementName.RUNLIST, null, null);
        JDFRunList rlp=(JDFRunList)rl.addPartition(EnumPartIDKey.Run, "r1");
        rlp.setPages(new JDFIntegerRangeList("1 3 5 7"));
        assertEquals(rl.getIndexPartition(0),rlp);
        assertEquals(rl.getIndexPartition(3),rlp);
        rlp.setNPage(3);
        JDFRunList rlp2=(JDFRunList)rl.addPartition(EnumPartIDKey.Run, "r2");
        rlp2.setPages(new JDFIntegerRangeList("0 2 4 6"));
        assertEquals(rl.getIndexPartition(3), rlp2);
        assertEquals(rl.getIndexPartition(6), rlp2);
        assertNull(rl.getIndexPartition(7));
    }
    /*
     * Test method for 'org.cip4.jdflib.resource.process.JDFMedia.setDimensionCM(JDFXYPair)'
     */
    public final void testGetPageInFile() throws Exception
    {
        JDFDoc doc = new JDFDoc("JDF");
        JDFNode root = doc.getJDFRoot();
        JDFResourcePool resPool = root.getCreateResourcePool();
        JDFRunList rl = (JDFRunList)resPool.appendResource(ElementName.RUNLIST, null, null);
        JDFRunList rlp=(JDFRunList)rl.addPartition(EnumPartIDKey.Run, "r1");
        rlp.setPages(new JDFIntegerRangeList("1 3 5 7"));
        assertEquals(rlp.getPageInFile(0), 1);
        assertEquals(rlp.getPageInFile(1), 3);
        assertEquals(rlp.getPageInFile(3), 7);
        rlp.setNPage(3);
        assertEquals(rlp.getPageInFile(3), -1);
        JDFRunList rlp2=(JDFRunList)rl.addPartition(EnumPartIDKey.Run, "r2");
        rlp2.setPages(new JDFIntegerRangeList("0 2 4 6"));
        assertEquals(rlp2.getPageInFile(0), -1);
        assertEquals(rlp2.getPageInFile(3), 0);
        assertEquals(rlp2.getPageInFile(5), 4);
        assertEquals(rlp2.getPageInFile(6), 6);
        rlp2.setNPage(3);
        assertEquals(rlp2.getPageInFile(6), -1);
        rlp2.setNPage(4);
        assertEquals(rlp2.getPageInFile(6), 6);
    }
    /*
     * Test method for 'org.cip4.jdflib.resource.process.JDFMedia.setDimensionCM(JDFXYPair)'
     */
    public final void testGetPageLeaves() throws Exception
    {
        JDFDoc doc = new JDFDoc("JDF");
        JDFNode root = doc.getJDFRoot();
        JDFResourcePool resPool = root.getCreateResourcePool();
        JDFRunList rl = (JDFRunList)resPool.appendResource(ElementName.RUNLIST, null, null);
        JDFRunList rlp=(JDFRunList)rl.addPartition(EnumPartIDKey.Run, "r1");
        JDFRunList rlp2=(JDFRunList)rl.addPartition(EnumPartIDKey.Run, "r2");
        VElement v=rl.getPageLeaves();
        assertTrue(v.contains(rlp));
        assertTrue(v.contains(rlp2));
        assertEquals(v.size(),2);
        JDFRunList rlp21=(JDFRunList)rlp2.addPartition(EnumPartIDKey.RunSet, "s1");
        JDFRunList rlp22=(JDFRunList)rlp2.addPartition(EnumPartIDKey.RunSet, "s2");
        v=rl.getPageLeaves();
        assertTrue(v.contains(rlp));
        assertFalse(v.contains(rlp2));
        assertTrue(v.contains(rlp21));
        assertTrue(v.contains(rlp22));
        assertEquals(v.size(),3);
        rlp21.setIsPage(false);
        rlp22.setIsPage(false);
        v=rl.getPageLeaves();
        assertTrue(v.contains(rlp));
        assertTrue(v.contains(rlp2));
        assertEquals(v.size(),2);
        v=rlp2.getPageLeaves();
        assertTrue(v.contains(rlp2));
        assertEquals(v.size(),1);

    }
    /*
     * Test method for 'org.cip4.jdflib.resource.process.JDFMedia.setDimensionCM(JDFXYPair)'
     */
    public final void testGetIndex() throws Exception
    {
        JDFDoc doc = new JDFDoc("JDF");
        JDFNode root = doc.getJDFRoot();
        JDFResourcePool resPool = root.getCreateResourcePool();
        JDFRunList rl = (JDFRunList)resPool.appendResource(ElementName.RUNLIST, null, null);
        JDFRunList rlp=(JDFRunList)rl.addPartition(EnumPartIDKey.Run, "r1");
        rlp.setPages(new JDFIntegerRangeList("1 3 5 7"));
        assertEquals("first partition starts at 0",rlp.getFirstIndex(), 0);
        assertEquals(rlp.getLastIndex(), 3);
        rlp.setNPage(3);
        assertEquals(rlp.getFirstIndex(), 0);
        assertEquals(rlp.getLastIndex(), 2);
        JDFRunList rlp2=(JDFRunList)rl.addPartition(EnumPartIDKey.Run, "r2");
        rlp2.setPages(new JDFIntegerRangeList("0 2 4 6"));
        assertEquals(rlp2.getFirstIndex(), 3);
        assertEquals(rlp2.getLastIndex(), 6);
        rlp2.setNPage(2);
        assertEquals(rlp2.getFirstIndex(), 3);
        assertEquals(rlp2.getLastIndex(), 4);
        JDFRunList rlp3=(JDFRunList)rl.addPartition(EnumPartIDKey.Run, "r3");
        rlp2.setLogicalPage(11);
        rlp3.setPages(new JDFIntegerRangeList("0 2 4 6"));
        assertEquals(rlp3.getFirstIndex(), 13);
        assertEquals(rlp3.getLastIndex(), 16);
        rlp3.setNPage(2);
        rlp3.setLogicalPage(22);
        assertEquals(rlp3.getFirstIndex(), 22);
        assertEquals(rlp3.getLastIndex(), 23);
    }

    /*
     * Test method for 'org.cip4.jdflib.resource.process.JDFMedia.setDimensionCM(JDFXYPair)'
     */
    public final void testPageIterator() throws Exception
    {
        JDFDoc doc = new JDFDoc("JDF");
        JDFNode root = doc.getJDFRoot();
        JDFResourcePool resPool = root.getCreateResourcePool();
        JDFRunList rl = (JDFRunList)resPool.appendResource(ElementName.RUNLIST, null, null);
        JDFRunList rlp=(JDFRunList)rl.addPartition(EnumPartIDKey.Run, "r1");
        rlp.setPages(new JDFIntegerRangeList("1 3 5 7"));
        rlp.setNPage(3);
        JDFRunList rlp2=(JDFRunList)rl.addPartition(EnumPartIDKey.Run, "r2");
        rlp2.setPages(new JDFIntegerRangeList("0 2 4 6"));
        Iterator it=rl.getPageIterator();
        int n=0;
        while(it.hasNext())
        {
            JDFRunData ri=(JDFRunData)it.next();
            assertEquals(n, ri.runIndex);
            assertEquals(n<3 ? rlp : rlp2, ri.runList);
            n++;
        }
        assertEquals(n, 7);
    }
    /**
     * performance check for the runlist iterator
     * @throws Exception
     */
    public final void testPageIteratorSpeed() throws Exception
    {
        JDFDoc doc = new JDFDoc("JDF");
        JDFNode root = doc.getJDFRoot();
        JDFResourcePool resPool = root.getCreateResourcePool();
        JDFRunList rl = (JDFRunList)resPool.appendResource(ElementName.RUNLIST, null, null);
        int nMax=3000;
        for(int i=0;i<nMax;i++)
        {
            JDFRunList rlp=(JDFRunList)rl.addPartition(EnumPartIDKey.Run, "r"+i);
            rlp.setPages(new JDFIntegerRangeList("1 3 5 7"));
            rlp.setFileURL("File://Test"+i+".pdf");
        }
        Iterator it=rl.getPageIterator();
        int n=0;

        while(it.hasNext())
        {
            JDFRunData ri=(JDFRunData)it.next();
            assertEquals(n, ri.runIndex);
            assertEquals(((ri.getPageInFile()-1)/2)%4,n%4);
            n++;
        }
        assertEquals(n, 4*nMax);
    }

}
