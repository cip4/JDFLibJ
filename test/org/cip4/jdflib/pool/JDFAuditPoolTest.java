/*
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
package org.cip4.jdflib.pool;

import org.cip4.jdflib.JDFTestCaseBase;
import org.cip4.jdflib.core.AttributeName;
import org.cip4.jdflib.core.ElementName;
import org.cip4.jdflib.core.JDFAudit;
import org.cip4.jdflib.core.JDFDoc;
import org.cip4.jdflib.core.VElement;
import org.cip4.jdflib.core.VString;
import org.cip4.jdflib.core.JDFAudit.EnumAuditType;
import org.cip4.jdflib.core.JDFElement.EnumNodeStatus;
import org.cip4.jdflib.datatypes.JDFAttributeMap;
import org.cip4.jdflib.datatypes.VJDFAttributeMap;
import org.cip4.jdflib.jmf.JDFDeviceInfo;
import org.cip4.jdflib.jmf.JDFJMF;
import org.cip4.jdflib.jmf.JDFJobPhase;
import org.cip4.jdflib.jmf.JDFQueueEntry;
import org.cip4.jdflib.jmf.JDFSignal;
import org.cip4.jdflib.jmf.JDFMessage.EnumType;
import org.cip4.jdflib.node.JDFNode;
import org.cip4.jdflib.node.JDFSpawned;
import org.cip4.jdflib.resource.JDFCreated;
import org.cip4.jdflib.resource.JDFMerged;
import org.cip4.jdflib.resource.JDFPhaseTime;
import org.cip4.jdflib.resource.JDFProcessRun;
import org.cip4.jdflib.resource.JDFResource;
import org.cip4.jdflib.util.JDFDate;

/**
 * @author MuchaD
 *
 * This implements the first fixture with unit tests for class JDFElement.
 */
public class JDFAuditPoolTest extends JDFTestCaseBase
{
    private JDFDoc jdfDoc;        
    private JDFNode jdfRoot;
    private JDFAuditPool myAuditPool;

    /**
     * Method testIncludesAttribute.
     * @throws Exception
     */
    public void testAddCreated() throws Exception
    {
         // Test AddCreated with one parameter
        myAuditPool.addCreated("A_Test_Author", null);
        JDFAudit myAudit = 
            myAuditPool.getAudit(1, JDFAudit.EnumAuditType.Created, new JDFAttributeMap(),null);                  
        String myText  = myAudit.getAuthor();
        assertEquals("Error: Author should be \"A_Test_Author\"", myText, "A_Test_Author");
        // Test AddCreate with two Parameter
                        
        // Get Create a ResourcePool
        JDFResourcePool myResourcePool = jdfRoot.getCreateResourcePool();
        //Append a ResoureElement
        myResourcePool.appendElement("BindingIntent", "");
                
        //Get that element back
        JDFResource e = (JDFResource) myResourcePool.getElement("BindingIntent", "", 0);
        myAuditPool.addCreated("A Test Author for JUnitTest 2", e);
                
        String strResourceID = e.buildXPath("/JDF",1);
        JDFCreated kResourceAudit = 
            (JDFCreated) myAuditPool.getChildWithAttribute(null, "XPath", null, strResourceID, 0, true);

        assertNotNull("Error: Audit not found ", kResourceAudit);                
    }

    /* (non-Javadoc)
     * @see org.cip4.jdflib.JDFTestCaseBase#setUp()
     */
    protected void setUp() throws Exception
    {
        // TODO Auto-generated method stub
        super.setUp();
 
        jdfDoc = new JDFDoc(ElementName.JDF);        
        jdfRoot = (JDFNode) jdfDoc.getRoot();
        jdfRoot.setJobID("jobID");
        
        myAuditPool =  jdfRoot.getCreateAuditPool();

    }    
    
    public void testAddMerged() throws Exception
    {
         // Test AddCreated with one parameter
        JDFDoc doc2=new JDFDoc("JDF");
        JDFNode node2=doc2.getJDFRoot();
        VJDFAttributeMap vMap=new VJDFAttributeMap();
        vMap.add(new JDFAttributeMap("SheetName","s1"));
       
        JDFMerged m1=myAuditPool.addMerged(node2, null, null, null);
        JDFMerged m2=myAuditPool.addMerged(node2, null, null, vMap);
        assertNotNull(m2);
        assertEquals(m2.getPartMapVector(), vMap);
        assertNull(myAuditPool.getElement(ElementName.PART));
        assertNotNull(m1);
    }
        
    public void testAddSpawned() throws Exception
    {
         // Test AddCreated with one parameter
        JDFDoc doc2=new JDFDoc("JDF");
        JDFNode node2=doc2.getJDFRoot();
        VJDFAttributeMap vMap=new VJDFAttributeMap();
        vMap.add(new JDFAttributeMap("SheetName","s1"));
        
        JDFSpawned m1=myAuditPool.addSpawned(node2, null, null, null, null);
        assertNotNull(m1);
        JDFSpawned m2=myAuditPool.addSpawned(node2, null, null, null, vMap);
        assertNotNull(m2);
        assertEquals(m2.getPartMapVector(), vMap);
        assertNull(myAuditPool.getElement(ElementName.PART));
    }
    
    public void testSetPhase() throws Exception
    {        
        JDFPhaseTime p1=myAuditPool.setPhase(EnumNodeStatus.Setup, null, null);
        assertNotNull(p1);
        assertEquals(myAuditPool.getChildElementVector(ElementName.PHASETIME, null, null, true, 0, true).size(), 1);
        JDFPhaseTime p2=myAuditPool.setPhase(EnumNodeStatus.Setup, "foobar", null);
        assertNotNull(p2);
        assertNotSame(p1,p2);
        assertEquals(myAuditPool.getChildElementVector(ElementName.PHASETIME, null, null, true, 0, true).size(), 2);
        p2=myAuditPool.setPhase(EnumNodeStatus.Setup, "foobar", null);
        assertNotNull(p2);
        assertEquals(myAuditPool.getChildElementVector(ElementName.PHASETIME, null, null, true, 0, true).size(), 2);
        p2=myAuditPool.setPhase(EnumNodeStatus.Ready, "foobar", null);
        assertNotNull(p2);
        assertEquals(myAuditPool.getChildElementVector(ElementName.PHASETIME, null, null, true, 0, true).size(), 3);
        p1=myAuditPool.setPhase(EnumNodeStatus.InProgress, null, null);
        assertNotNull(p1);
        assertEquals(myAuditPool.getChildElementVector(ElementName.PHASETIME, null, null, true, 0, true).size(), 4);
        p2=myAuditPool.setPhase(EnumNodeStatus.InProgress, null, null);
        assertNotNull(p2);
        assertEquals(p1,p2);
        assertEquals(myAuditPool.getChildElementVector(ElementName.PHASETIME, null, null, true, 0, true).size(), 4);
    }
    
    public void testGetLastPhase() throws Exception
    {        
        JDFPhaseTime p1=myAuditPool.setPhase(EnumNodeStatus.Setup, null, null);
        assertNotNull(p1);
        assertEquals(p1,myAuditPool.getLastPhase(null,null));
        VJDFAttributeMap vMap=new VJDFAttributeMap();
        vMap.add(new JDFAttributeMap("SheetName","s1"));
        VJDFAttributeMap vMap2=new VJDFAttributeMap();
        vMap2.add(new JDFAttributeMap("SheetName","s1"));
        JDFPhaseTime p2=myAuditPool.setPhase(EnumNodeStatus.Setup, null, vMap);
        assertEquals(p2,myAuditPool.getLastPhase(vMap,null));
        assertEquals(p2,myAuditPool.getLastPhase(null,null));
        JDFPhaseTime p3=myAuditPool.setPhase(EnumNodeStatus.Setup, null, vMap2);
        myAuditPool.addModified(null, jdfRoot);
        assertEquals(p2,myAuditPool.getLastPhase(vMap,null));
        assertEquals(p3,myAuditPool.getLastPhase(null,null));
        assertEquals(p3,myAuditPool.getLastPhase(vMap2,null));
        
        p1.setModules(new VString("m1",null), new VString("RIP",null));
        assertNull(myAuditPool.getLastPhase(null,"m2"));
        assertEquals(p1,myAuditPool.getLastPhase(null,"m1"));
                
     }
        
    public void testCreateSubmitProcessRun() throws Exception
    {        
        
       JDFProcessRun pr=myAuditPool.createSubmitProcessRun(null);
       assertNotNull(pr.getSubmissionTime());
       assertFalse("has submissiontime before now",new JDFDate().before(pr.getSubmissionTime()));
       assertTrue(pr.getAttribute(AttributeName.QUEUEENTRYID).startsWith("qe"));
       
       JDFDoc d=new JDFDoc(ElementName.QUEUEENTRY);
       JDFQueueEntry qe=(JDFQueueEntry) d.getRoot();
       
       JDFDate dat=new JDFDate();
       dat.addOffset(0, 0, 6, 2);
       qe.setSubmissionTime(dat);
       qe.setQueueEntryID("q1");
       pr=myAuditPool.createSubmitProcessRun(qe);
       assertEquals(pr.getSubmissionTime(), dat);
       assertEquals(pr.getAttribute(AttributeName.QUEUEENTRYID), "q1");
    }
 
    public void testSetPhaseJMF() throws Exception
    {        
        
        JDFDoc docJMF=new JDFDoc("JMF");
        JDFJMF jmf=docJMF.getJMFRoot();
        JDFSignal sig=jmf.appendSignal(EnumType.Status);
        JDFDeviceInfo di=sig.appendDeviceInfo();
        JDFJobPhase phase=di.appendJobPhase();
        phase.setPhaseStartTime(new JDFDate());
        phase.setStatus(EnumNodeStatus.Setup);
        phase.setJobID(jdfRoot.getJobID(true));
        phase.setJobPartID(jdfRoot.getJobPartID(true));
        
        VElement el=myAuditPool.setPhase(jmf);
        assertNotNull(el);
        assertEquals(myAuditPool.getChildElementVector(ElementName.PHASETIME, null, null, true, 0, true).size(), 1);
        assertEquals(myAuditPool.getChildElementVector(ElementName.PHASETIME, null, null, true, 0, true),el);
        
        el=myAuditPool.setPhase(jmf);
        assertNotNull(el);
        assertEquals(myAuditPool.getChildElementVector(ElementName.PHASETIME, null, null, true, 0, true).size(), 1);
        assertEquals(myAuditPool.getChildElementVector(ElementName.PHASETIME, null, null, true, 0, true),el);
       
        phase.setStatus(EnumNodeStatus.Aborted);
        el=myAuditPool.setPhase(jmf);
        assertNotNull(el);
        assertEquals(myAuditPool.getChildElementVector(ElementName.PHASETIME, null, null, true, 0, true).size(), 2);
        assertEquals(myAuditPool.getChildElementVector(ElementName.PHASETIME, null, null, true, 0, true).elementAt(1),el.elementAt(0));
        
    }    
    
    public void testGetAudit()
    {
        JDFAudit a1=myAuditPool.addAudit(EnumAuditType.Deleted, null);
        JDFAudit a2=myAuditPool.addAudit(EnumAuditType.Created, null);
        JDFAudit a3=myAuditPool.addAudit(EnumAuditType.PhaseTime, null);
        JDFAudit a4=myAuditPool.addAudit(EnumAuditType.Deleted, null);
        assertEquals(myAuditPool.getAudit(-1, null, null, null), a4);
        assertEquals(myAuditPool.getAudit(1, EnumAuditType.Deleted, null, null), a4);
        assertEquals(myAuditPool.getAudit(-2, null, null, null), a3);
        assertEquals(myAuditPool.getAudit(-2, EnumAuditType.Deleted, null, null), a1);
        assertEquals(myAuditPool.getAudit(0, EnumAuditType.Deleted, null, null), a1);
        assertEquals(myAuditPool.getAudit(-1, EnumAuditType.Created, null, null), a2);
    }
    @Override
    public String toString()
    {
        return myAuditPool==null ? "null" : myAuditPool.toString();
    }
}