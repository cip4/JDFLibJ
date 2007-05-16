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
package org.cip4.jdflib.core;

import org.cip4.jdflib.JDFTestCaseBase;
import org.cip4.jdflib.core.JDFAudit.EnumAuditType;
import org.cip4.jdflib.core.JDFElement.EnumNodeStatus;
import org.cip4.jdflib.core.JDFResourceLink.EnumUsage;
import org.cip4.jdflib.node.JDFNode;
import org.cip4.jdflib.node.JDFNode.EnumType;
import org.cip4.jdflib.pool.JDFAuditPool;
import org.cip4.jdflib.resource.JDFCreated;
import org.cip4.jdflib.resource.JDFModified;
import org.cip4.jdflib.resource.JDFPhaseTime;
import org.cip4.jdflib.resource.JDFProcessRun;
import org.cip4.jdflib.resource.JDFResource;
import org.cip4.jdflib.util.JDFDate;

/**
 * @author MuchaD
 *
 * This implements the first fixture with unit tests for class JDFAudit.
 */
public class JDFAuditTest extends JDFTestCaseBase
{
    private boolean b;
    public void testInit() throws Exception
    {
        JDFDoc d=new JDFDoc(ElementName.JDF);
        JDFNode n=d.getJDFRoot();
        n.setType("ConventionalPrinting",true);
        JDFAuditPool ap=n.getAuditPool();
        assertNotNull(ap);
        JDFCreated crea=(JDFCreated) ap.getAudit(0, EnumAuditType.Created, null,null);
        assertTrue(crea.hasAttribute("ID"));
        assertTrue(crea.getID().startsWith("a"));
        n.setVersion(JDFElement.EnumVersion.Version_1_2);
        JDFModified mod=ap.addModified("me",n);
        assertFalse(mod.hasAttribute("ID"));        
     }
    
 
    /////////////////////////////////////////////////////////////////////
    public void testFixVersion() throws Exception
    {
        JDFDoc d=new JDFDoc(ElementName.JDF);
        JDFNode n=d.getJDFRoot();
        n.setType("ConventionalPrinting",true);
        JDFAuditPool ap=n.getAuditPool();
        assertNotNull(ap);
        JDFCreated crea=(JDFCreated) ap.getAudit(0, EnumAuditType.Created, null,null);
        assertTrue(crea.hasAttribute("ID"));
        n.fixVersion(JDFElement.EnumVersion.Version_1_2);
        assertFalse(crea.hasAttribute("ID"));        
     }
    
    /////////////////////////////////////////////////////////////////////
    public void testSetRef() throws Exception
    {
        JDFDoc d=new JDFDoc(ElementName.JDF);
        JDFNode n=d.getJDFRoot();
        n.setType("ConventionalPrinting",true);
        JDFAuditPool ap=n.getAuditPool();
        assertNotNull(ap);
        JDFPhaseTime pt=ap.setPhase(EnumNodeStatus.Stopped, null, null);
        JDFPhaseTime pt2=ap.setPhase(EnumNodeStatus.Aborted, null, null);
        pt2.setRef(pt);
        assertEquals(pt.getID(), pt2.getrefID());
     }
    /////////////////////////////////////////////////////////////////////
    public void testCreateUpdate() throws Exception
    {
        JDFDoc d=new JDFDoc(ElementName.JDF);
        JDFNode n=d.getJDFRoot();
        n.setType("ConventionalPrinting",true);
        JDFAuditPool ap=n.getAuditPool();
        assertNotNull(ap);
        JDFPhaseTime pt=ap.setPhase(EnumNodeStatus.Stopped, null, null);
        JDFPhaseTime pt2=(JDFPhaseTime) pt.createUpdateAudit();
        assertEquals(pt.getID(), pt2.getrefID());
        assertNotSame(pt.getID(), "");       
        assertNotSame(pt2.getID(), "");       
        assertNotSame(pt2.getID(), pt.getID());       
     }

    /////////////////////////////////////////////////////////////////////
    public void testCreated() throws Exception
    {
        JDFDoc d=new JDFDoc(ElementName.JDF);
        JDFNode n=d.getJDFRoot();
        n.setType(EnumType.ProcessGroup);
        JDFAuditPool ap=n.getAuditPool();
        assertNotNull(ap);
        JDFNode n2=n.addJDFNode(EnumType.CaseMaking);
        JDFCreated c1=ap.addCreated("foo", n2);
        assertEquals(n2.buildXPath(ap.getParentJDF().buildXPath(null,1),1), c1.getXPath());
        JDFResource r=n2.addResource("CaseMakingParams", null, EnumUsage.Input, null, null, null, null);
        JDFCreated c2=ap.addCreated("foo", r);
        assertEquals(r.buildXPath(ap.getParentJDF().buildXPath(null,1),1), c2.getXPath());
        
        d.write2File(sm_dirTestDataTemp+"createdTest.jdf", 0, false);
        
    }    
    
    
    public void testProcessRun() throws Exception
    {
        JDFDoc d=new JDFDoc(ElementName.JDF);
        JDFNode n=d.getJDFRoot();
        n.setType(EnumType.ProcessGroup);
        JDFAuditPool ap=n.getAuditPool();
        assertNotNull(ap);
        JDFProcessRun p1=ap.addProcessRun(EnumNodeStatus.Completed, null, null);
        assertEquals(p1.getTimeStamp(), new JDFDate().getDateTimeISO());
       
    
    }    
    
    /////////////////////////////////////////////////////////////////////
    public void testSpawnID() throws Exception
    {
        JDFDoc d=new JDFDoc(ElementName.JDF);
        JDFNode n=d.getJDFRoot();
        n.setSpawnID("spawn");
        n.setType(EnumType.ProcessGroup);
        JDFAuditPool ap=n.getAuditPool();
        assertNotNull(ap);
        JDFProcessRun p1=ap.addProcessRun(EnumNodeStatus.Completed, null, null);
        assertEquals(p1.getSpawnID(), n.getSpawnID(false));
        JDFNode n2=n.addJDFNode(EnumType.CaseMaking);
        JDFProcessRun p2=n.getCreateAuditPool().addProcessRun(EnumNodeStatus.Completed, null, null);
        assertEquals(p2.getSpawnID(), n2.getSpawnID(true));
        assertEquals(p2.getSpawnID(), n.getSpawnID(false));

    }    
    
    /////////////////////////////////////////////////////////////////////
    public void testSetStaticAgentVersion() throws Exception
    {
        JDFDoc d=new JDFDoc(ElementName.JDF);
        JDFNode n=d.getJDFRoot();
        n.setType("ConventionalPrinting",true);
        JDFAuditPool ap=n.getAuditPool();
        assertNotNull(ap);
        JDFCreated crea=(JDFCreated) ap.getAudit(0, EnumAuditType.Created, null,null);
        assertEquals(crea.getAgentName(),JDFAudit.getStaticAgentName());

        JDFResource.setAutoAgent(true);
        JDFResource r=n.appendMatchingResource(ElementName.CONVENTIONALPRINTINGPARAMS, null, null);
        assertEquals(r.getAgentName(), JDFAudit.getStaticAgentName());
        assertEquals(r.getAgentVersion(), JDFAudit.getStaticAgentVersion());
        JDFAudit.setStaticAgentName(null);
        JDFAudit.setStaticAgentVersion(null);
        JDFAudit.setStaticAuthor(null);
        d=new JDFDoc(ElementName.JDF);
        n=d.getJDFRoot();
        n.setType("ConventionalPrinting",true);
        ap=n.getAuditPool();
        assertNotNull(ap);
        crea=(JDFCreated) ap.getAudit(0, EnumAuditType.Created, null,null);
        assertEquals(crea.getAgentName(),"");
        assertEquals(crea.getAgentVersion(),"");
        assertEquals(crea.getAuthor(),"");
        r=n.appendMatchingResource(ElementName.CONVENTIONALPRINTINGPARAMS, null, null);
        assertFalse(r.hasAttribute(AttributeName.AGENTNAME));
        assertFalse(r.hasAttribute(AttributeName.AGENTVERSION));
        
        
     }


    /* (non-Javadoc)
     * @see org.cip4.jdflib.JDFTestCaseBase#tearDown()
     */
    protected void tearDown() throws Exception
    {
        // TODO Auto-generated method stub
        super.tearDown();
        JDFResource.setAutoAgent(b);

    }


    /* (non-Javadoc)
     * @see org.cip4.jdflib.JDFTestCaseBase#setUp()
     */
    protected void setUp() throws Exception
    {
        // TODO Auto-generated method stub
        super.setUp();
        b=JDFResource.getAutoAgent();
        JDFElement.setLongID(false);

    }
    
    
    /////////////////////////////////////////////////////////////////////

}