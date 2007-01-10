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

import java.io.File;
import java.util.Vector;

import junit.framework.TestCase;

import org.cip4.jdflib.auto.JDFAutoQueue.EnumQueueStatus;
import org.cip4.jdflib.auto.JDFAutoQueueEntry.EnumQueueEntryStatus;
import org.cip4.jdflib.core.AttributeInfo.EnumAttributeType;
import org.cip4.jdflib.core.JDFElement.EnumNodeStatus;
import org.cip4.jdflib.core.JDFElement.EnumSettingsPolicy;
import org.cip4.jdflib.core.JDFElement.EnumVersion;
import org.cip4.jdflib.core.JDFElement.EnumXYRelation;
import org.cip4.jdflib.core.JDFResourceLink.EnumUsage;
import org.cip4.jdflib.core.KElement.EnumValidationLevel;
import org.cip4.jdflib.datatypes.JDFAttributeMap;
import org.cip4.jdflib.jmf.JDFAcknowledge;
import org.cip4.jdflib.jmf.JDFCommand;
import org.cip4.jdflib.jmf.JDFJMF;
import org.cip4.jdflib.jmf.JDFJobPhase;
import org.cip4.jdflib.jmf.JDFMessage;
import org.cip4.jdflib.jmf.JDFPipeParams;
import org.cip4.jdflib.jmf.JDFQueue;
import org.cip4.jdflib.jmf.JDFQueueEntry;
import org.cip4.jdflib.jmf.JDFResourceCmdParams;
import org.cip4.jdflib.jmf.JDFResourceInfo;
import org.cip4.jdflib.jmf.JDFResponse;
import org.cip4.jdflib.jmf.JDFMessage.EnumFamily;
import org.cip4.jdflib.node.JDFAncestor;
import org.cip4.jdflib.node.JDFNode;
import org.cip4.jdflib.node.JDFSpawned;
import org.cip4.jdflib.node.JDFNode.EnumProcessUsage;
import org.cip4.jdflib.pool.JDFAuditPool;
import org.cip4.jdflib.pool.JDFResourcePool;
import org.cip4.jdflib.resource.JDFPhaseTime;
import org.cip4.jdflib.resource.JDFResource;
import org.cip4.jdflib.resource.JDFResource.EnumPartIDKey;
import org.cip4.jdflib.resource.JDFResource.EnumResStatus;
import org.cip4.jdflib.resource.JDFResource.EnumResourceClass;
import org.cip4.jdflib.resource.process.JDFComChannel;
import org.cip4.jdflib.resource.process.JDFContact;
import org.cip4.jdflib.resource.process.JDFExposedMedia;
import org.cip4.jdflib.resource.process.JDFMedia;

/**
 * @author MuchaD
 *
 * This implements the first fixture with unit tests for class JDFElement.
 */
public class JDFElementTest extends TestCase
{
    static final String fileSeparator      = System.getProperty("file.separator");
    static final String sm_dirTestSchema   = "test"+fileSeparator+"Schema"+fileSeparator;
    static final String sm_dirTestData     = "test"+fileSeparator+"data"+fileSeparator;
    static final String sm_dirTestDataTemp = sm_dirTestData+"temp"+fileSeparator;
    
    // member variables for the fixture
    private JDFDoc     m_jdfDoc;
    private JDFNode    m_jdfRoot;
    private KElement   m_kElement;
    private JDFElement m_jdfElement;
    
    
    public void testAppendElement()
    {
        JDFDoc d=new JDFDoc("JDF");
        KElement r=d.getRoot();
        KElement e=r.appendElement("e");
        assertEquals(e.getNamespaceURI(),JDFElement.getSchemaURL());
        KElement foo=e.appendElement("pt:foo", "www.pt.com");
        assertEquals(foo.getNamespaceURI(), "www.pt.com");
        KElement bar=foo.appendElement("bar");
        assertNotNull(bar.getNamespaceURI());
        KElement foo2=bar.appendElement("pt:foo", "www.pt.com");
        assertEquals(foo2.getNamespaceURI(), "www.pt.com");              
    }    
    
    public void testCopyElement()
    {
        JDFDoc d=new JDFDoc("d1");
        JDFElement e=(JDFElement)d.getRoot();
        JDFDoc d2=new JDFDoc("d2");
        JDFElement e2=(JDFElement)d2.getRoot();
        KElement e3=e.copyElement(e2,null);
        JDFParser p=new JDFParser();
        JDFDoc dp=p.parseString("<Device xmlns=\"www.CIP4.org/JDFSchema_1_1\"/>");
        KElement ep=dp.getRoot();
//        assertFalse(ep.hasAttribute("xmlns"));
        KElement e4=e.copyElement(ep,null);
//        assertFalse(e4.hasAttribute("xmlns"));
        assertEquals(e3.getNamespaceURI(), e.getNamespaceURI());
        assertFalse(d.toString().indexOf("xmlns=\"\"")>=0);

    }
    
   /**
     * 
     *
     */
    public void testGetElement_KElement()
    {
        JDFDoc d=new JDFDoc("JDF");
        JDFNode root=d.getJDFRoot();
        JDFExposedMedia xm=(JDFExposedMedia)root.addResource("ExposedMedia", null, EnumUsage.Input, null, null, null, null);
        JDFMedia m=xm.appendMedia();
        m.makeRootResource(null, null, true);
        assertNull(xm.getElement_KElement("Media", null, 0));
        assertNotNull(xm.getElement_JDFElement("Media", null, 0));
     }    
    /**
     * 
    *
    */
   public void testGetChildElementVector_KElement()
   {
       JDFDoc d=new JDFDoc("JDF");
       JDFNode root=d.getJDFRoot();
       JDFExposedMedia xm=(JDFExposedMedia)root.addResource("ExposedMedia", null, EnumUsage.Input, null, null, null, null);
       JDFMedia m=xm.appendMedia();
       m.makeRootResource(null, null, true);
       assertEquals(xm.getChildElementVector_KElement("Media", null,null,true,-1).size(),0);
       assertEquals(xm.getChildElementVector_JDFElement("Media", null,null,true,-1,true).size(),1);
    }
    
    
    private void _setUp()
    {
        // setup the fixture
        String xmlFile = "bookintent.jdf";
        
        // test jdf functions
        // ==================
        JDFParser p = new JDFParser();
        m_jdfDoc = p.parseFile(sm_dirTestData+xmlFile);
        
        assertTrue(sm_dirTestData+xmlFile + ": Parse Error", m_jdfDoc!= null);
        
        m_jdfRoot    = (JDFNode) m_jdfDoc.getRoot();
        m_kElement   = m_jdfRoot.getChildByTagName("Dimensions", "", 0, null, false, true);
        m_jdfElement = (JDFElement) m_kElement;
        
    }
    
    public void testNameSpaceElement()
    {
        JDFDoc doc = new JDFDoc(ElementName.JDF);
        JDFNode root = doc.getJDFRoot();
        root.setType("foo:bar", false);
        root.addNameSpace("foo", "www.foo.com");
        JDFResource r=root.addResource("foo:res", EnumResourceClass.Parameter, EnumUsage.Input, null, null, null, null);
        JDFResourceLink rl=root.getLink(r, null);
        rl.setPartMap(new JDFAttributeMap("Side","Front"));
        assertEquals(rl.toString().indexOf("xmlns=\"\""), -1);
        assertEquals(rl.getPart(0).toString().indexOf("xmlns=\"\""), -1);
    }

    public void testRemoveChild()
    {
        JDFDoc d=new JDFDoc("JDF");
        JDFNode n=d.getJDFRoot();
        n.setType("ConventionalPrinting",true);
        JDFExposedMedia xmpl=(JDFExposedMedia)n.appendMatchingResource("ExposedMedia",EnumProcessUsage.Plate,null);
        JDFExposedMedia xmpr=(JDFExposedMedia)n.appendMatchingResource("ExposedMedia",EnumProcessUsage.Proof,null);
        JDFMedia m=xmpr.appendMedia();
        assertNotNull(xmpr.getMedia());
        m.setID("id1");
        KElement t1=n.getTarget("id1","ID");
        m=(JDFMedia) m.makeRootResource(null,null,true);
        assertEquals(t1,m);
        assertNotNull(xmpr.getMedia());
        xmpl.refElement(m);
        assertNotNull(xmpl.getMedia());
        JDFResourcePool rp=n.getResourcePool();
        assertNotNull(rp.getElement("Media"));
        xmpl.removeChild("Media",null,0);
        assertNull(xmpl.getMedia());
        assertNotNull(rp.getElement("Media"));
        xmpr.removeChildren("Media",null,null);
        assertNull(xmpr.getMedia());
        assertNotNull(rp.getElement("Media"));        
    }
    
////////////////////////////////////////////////////////////////////
    
    public void testGetHRefs()
    {
        JDFDoc d=new JDFDoc("JDF");
        JDFNode n=d.getJDFRoot();
        n.setType("ConventionalPrinting",true);
        JDFExposedMedia xmpl=(JDFExposedMedia)n.appendMatchingResource("ExposedMedia",EnumProcessUsage.Plate,null);
        JDFExposedMedia xmpr=(JDFExposedMedia)n.appendMatchingResource("ExposedMedia",EnumProcessUsage.Proof,null);
        JDFMedia m=xmpr.appendMedia();
        assertNotNull(xmpr.getMedia());
        m.setID("id1");
        KElement t1=n.getTarget("id1","ID");
        m=(JDFMedia) m.makeRootResource(null,null,true);
        assertEquals(t1,m);
        assertTrue(n.getHRefs(null,true,false).contains("id1"));
        assertTrue(xmpr.getHRefs(null,true,false).contains("id1"));
        assertFalse(xmpl.getHRefs(null,true,false).contains("id1"));          
        assertTrue(n.getHRefs(null,true,true).contains("id1"));
        assertTrue(xmpr.getHRefs(null,true,true).contains("id1"));
        assertFalse(xmpl.getHRefs(null,true,true).contains("id1"));          
    }
    
////////////////////////////////////////////////////////////////////
    
    public void testDefaultVersion()
    {
        JDFDoc doc=new JDFDoc("JDF");
        JDFNode n=doc.getJDFRoot();
        assertEquals(n.getVersion(true),EnumVersion.Version_1_3);
        JDFElement.setDefaultJDFVersion(EnumVersion.Version_1_2);
        n.setType("ProcessGroup",true);
        n=n.addJDFNode("Combined");
        assertEquals(n.getVersion(true),EnumVersion.Version_1_3);
        
        
        doc=new JDFDoc("JDF");
        n=doc.getJDFRoot();
        assertEquals(n.getVersion(true),EnumVersion.Version_1_2);
        n.setType("ProcessGroup",true);
        n=n.addJDFNode("Combined");
        assertEquals(n.getVersion(true),EnumVersion.Version_1_2);
        
        doc=new JDFDoc("JMF");
        JDFJMF jmf=doc.getJMFRoot();
        assertEquals(jmf.getVersion(true),EnumVersion.Version_1_2);
        
    }
////////////////////////////////////////////////////////////////////
    
    public void testEvaluateXY()
    {
        EnumXYRelation xyR=EnumXYRelation.eq;
        assertTrue("eq",xyR.evaluateXY(2,2,0,0));
        assertTrue("eq",xyR.evaluateXY(1.9,2,0.1,0.1));
        assertFalse("eq",xyR.evaluateXY(1.9,2,0.0,0.15));
        assertTrue("eq",xyR.evaluateXY(1.9,2,0.1,0.0));
        
        xyR=EnumXYRelation.ne;
        assertFalse("ne",xyR.evaluateXY(2,2,0,0));
        assertFalse("ne",xyR.evaluateXY(1.9,2,0.1,0.1));
        assertTrue("ne",xyR.evaluateXY(1.9,2,0.0,0.15));
        assertFalse("ne",xyR.evaluateXY(1.9,2,0.1,0.0));
        
        
        xyR=EnumXYRelation.gt;
        assertTrue("gt",xyR.evaluateXY(3,2,0,0));
        assertTrue("gt",xyR.evaluateXY(1.9,2,0.2,0.2));
        assertFalse("gt",xyR.evaluateXY(2.00,2,0.0,0.0));
        assertTrue("gt",xyR.evaluateXY(1.95,2,0.1,0.0));
        
        xyR=EnumXYRelation.lt;
        assertTrue("lt",xyR.evaluateXY(1.9,2,0.0,0.0));
        
        xyR=EnumXYRelation.le;
        assertTrue("le",xyR.evaluateXY(1.9,2,0.0,0.0));
        assertTrue("le",xyR.evaluateXY(2.0,2,0.0,0.0));
        assertFalse("le",xyR.evaluateXY(3.0,2,0.0,0.0));
     }
    
 ////////////////////////////////////////////////////////////////////////
    /**
     * Method testGenerateDotID.
     * @throws Exception
     */
    public void testGenerateDotID() throws Exception
    {
        JDFDoc doc=new JDFDoc("JDF");
        JDFNode e=doc.getJDFRoot();
        final String dotID = e.generateDotID("foo",null);
        e.setAttribute("foo",dotID,null);
        assertNotNull(dotID);
        assertTrue(dotID.startsWith("n"));
        JDFNode e2=(JDFNode) e.appendElement("JDF",null);
        String generateDotID = e2.generateDotID("foo",null);
        e2.setAttribute("foo",generateDotID,null);
        assertEquals(generateDotID,dotID+".1");

        JDFNode e3=(JDFNode) e2.appendElement("JDF",null);
        generateDotID = e3.generateDotID("foo",null);
        e3.setAttribute("foo",generateDotID,null);
        assertEquals(generateDotID,dotID+".1.1");
        e3=(JDFNode) e2.appendElement("JDF",null);
        generateDotID = e3.generateDotID("foo",null);
        e3.setAttribute("foo",generateDotID,null);
        assertEquals(generateDotID,dotID+".1.2");
        
        
        e2.setAttribute("foo","whatever",null);
        e2=(JDFNode) e.appendElement("JDF",null);
        generateDotID = e2.generateDotID("foo",null);
        e2.setAttribute("foo",generateDotID,null);
        assertEquals(generateDotID,dotID+".2");
        for(int i=3;i<22;i++)
        {
            e2=(JDFNode) e.appendElement("JDF",null);
            generateDotID = e2.generateDotID("foo",null);
            e2.setAttribute("foo",generateDotID,null);
            assertEquals(generateDotID,dotID+"."+String.valueOf(i));
        }
    }
    /**
     * Method testIncludesMatchingAttribute.
     * @throws Exception
     */
    public void testIncludesMatchingAttribute() throws Exception
    {
        _setUp();
        
        assertTrue("isInside (600 800) = ", m_jdfElement.includesMatchingAttribute("Range", "600 800", AttributeInfo.EnumAttributeType.XYPairRangeList));
        assertFalse("isOutside(500 700) = ", m_jdfElement.includesMatchingAttribute("Range", "500 700", AttributeInfo.EnumAttributeType.XYPairRangeList));
        
        JDFDoc d=new JDFDoc("JDF");
        JDFElement e=d.getJDFRoot();
        e.setAttribute("abc","a b c");
        assertTrue("b",e.includesMatchingAttribute("abc","a",EnumAttributeType.NMTOKENS));
        assertTrue("b",e.includesMatchingAttribute("abc","b",EnumAttributeType.NMTOKENS));
        assertTrue("b",e.includesMatchingAttribute("abc","c",EnumAttributeType.NMTOKENS));
        assertFalse("b",e.includesMatchingAttribute("abc","d",EnumAttributeType.NMTOKENS));
        e.setAttribute("intlist","-1 3 5");
        assertTrue(e.includesMatchingAttribute("intlist","-1",EnumAttributeType.IntegerList));
        assertTrue(e.includesMatchingAttribute("intlist","3",EnumAttributeType.IntegerList));
        assertTrue(e.includesMatchingAttribute("intlist","5",EnumAttributeType.IntegerList));
        assertFalse(e.includesMatchingAttribute("intlist","4",EnumAttributeType.IntegerList));
        assertFalse(e.includesMatchingAttribute("intlist","8",EnumAttributeType.IntegerList));
    }
    
    /**
     * Method testChildElementVector.
     * @throws Exception
     */
    public void testGetChildElementVector() throws Exception
    {
        _setUp();
        VElement velem = m_jdfRoot.getChildElementVector(null,null,null, true, 0, false);
        assertEquals(velem.size(),5);
        KElement elem = (KElement)velem.elementAt(0);
        assertEquals (elem.getNodeName(),"AuditPool");
        velem = m_jdfRoot.getChildElementVector(null,null,null, true, 3, false);
        assertEquals(velem.size(),3);
    }
    /**
     * Method testChildElementVector.
     * @throws Exception
     */
    public void testGetParentJDFStatic() throws Exception
    {
        JDFDoc d=new JDFDoc("JDF");
        JDFElement root=d.getJDFRoot();
        assertNull(JDFElement.getParentJDF(root));
        assertNull(JDFElement.getParentJDF(null));
        KElement k=root.appendElement("NodeInfo");
        assertEquals(root, JDFElement.getParentJDF(k));
        k=k.appendElement("foo:Bar","www.foo.com");
        assertEquals(root, JDFElement.getParentJDF(k));
        k=root.appendElement("JDF");
        assertEquals(root, JDFElement.getParentJDF(k));
    }

    ///////////////////////////////////////////////////7
    
    public void testGetSettingsPolicy() 
    {
        JDFDoc d=new JDFDoc("JDF");
        JDFNode n=d.getJDFRoot();
        assertNull(n.getSettingsPolicy(false));
        JDFAuditPool ap=n.getAuditPool();
        assertNull(ap.getSettingsPolicy(true));
        n.setSettingsPolicy(EnumSettingsPolicy.MustHonor);
        assertEquals(ap.getSettingsPolicy(true),EnumSettingsPolicy.MustHonor);
    }
    
    ///////////////////////////////////////////////////7
    
    public void testGetParentJDFNode() 
    {
        JDFDoc d=new JDFDoc("JDF");
        JDFNode n=d.getJDFRoot();
        n.setType("ProcessGroup",true);
        JDFNode n2=n.addJDFNode("Scanning");
        assertEquals("n2.parent n",n2.getParentJDF(),n);
        assertNull("n parent",n.getParentJDF());
        JDFAuditPool ap=n.getCreateAuditPool();
        assertEquals("ap.parent n",ap.getParentJDF(),n);
        ap=n2.getCreateAuditPool();
        assertEquals("ap.parent n2",ap.getParentJDF(),n2);
        assertEquals("a.parent n2",ap.addCreated("me",n2).getParentJDF(),n2);
        
        
    }
 
    /**
     * Method testGetElementByID.
     * @throws Exception
     */
    public void testGetElementByID () throws Exception
    {
        _setUp();
        KElement kelem = m_jdfRoot.getChildWithAttribute ("*","ID", "*","n0006", 0, true);
        assertTrue ("kelem==null",kelem!=null);
        if (kelem == null) return;     // soothe findbugs ;)
        String strAtrib = kelem.getAttribute("ID", "", "");
        assertTrue ("ID!=n0006", strAtrib.equals("n0006"));
        
        // second try
        KElement kelem2 = m_jdfRoot.getTarget("n0006", "ID");
        assertTrue ("kelem2==null",kelem2!=null);
        if (kelem2 == null) return;     // soothe findbugs ;)
        String strAtrib2 = kelem2.getAttribute("ID", "", "");
        assertTrue ("ID!=n0006", strAtrib2.equals("n0006"));
        
        // third try
        KElement kelem3 = m_jdfRoot.getTarget("198", "Preferred");
        assertTrue ("kelem3==null",kelem3!=null);
        if (kelem3 == null) return;     // soothe findbugs ;)
        String strAtrib3 = kelem3.getAttribute("Preferred", "", "");
        assertTrue ("Preferred!=198", strAtrib3.equals("198"));
        
        // fourth try: GetChildWithAttribute does only find direct children but no deep children
        KElement kelem4 = m_jdfRoot.getChildWithAttribute ("*","Preferred", "*","198", 0, true);
        assertTrue ("kelem4!=null",kelem4==null);
    }
     
//  public void testGetDueLevel ()
//  {
//  JDFNodeInfo info = new JDFNodeInfo(m_kElement);
//  info.GetDueLevel();
//  }
    
    public void testIsCommentStatic()
    {
        
        _setUp();
        assertFalse ("Bug: This is a comment!", m_kElement instanceof JDFComment);
        m_jdfElement.appendComment();
        m_kElement   = m_jdfElement.getChildByTagName("Comment", "", 0, null, false, true);
        assertTrue ("Bug: This is no comment!", m_kElement instanceof JDFComment);
    }
    
    public void testIsResourceStatic()
    {
        _setUp();
        m_kElement   = m_jdfRoot.getChildByTagName("ComponentLink", "", 0, null, false, true);
        assertFalse ("Bug: "+m_kElement.getNodeName()+" is a Resource!", m_kElement instanceof JDFResource);
        m_kElement   = m_jdfRoot.getChildByTagName("SizeIntent", "", 0, null, false, true);
        assertTrue  ("Bug: "+m_kElement.getNodeName()+" is no Resource!", m_kElement instanceof JDFResource);
        m_kElement   = m_jdfRoot.getChildByTagName("Dimensions", "", 0, null, false, true);
        assertFalse ("Bug: "+m_kElement.getNodeName()+" is a Resource!", m_kElement instanceof JDFResource);
    }
    
    public void testIsResourceLinkStatic()
    {
        _setUp();
        m_kElement   = m_jdfRoot.getChildByTagName("Dimensions", "", 0, null, false, true);
        assertFalse ("Bug: This is a ResourceLink!", m_kElement instanceof JDFResourceLink);
        m_kElement   = m_jdfRoot.getChildByTagName("ComponentLink", "", 0, null, false, true);
        assertTrue ("Bug: This is no ResourceLink!", m_kElement instanceof JDFResourceLink);
    }
    
    
    public void testInheritedVersionInfo()
    {
        JDFDoc doc = new JDFDoc(ElementName.JDF);
        JDFNode node = doc.getJDFRoot();
        node.setVersion(JDFElement.EnumVersion.Version_1_3);
        node.setType(JDFConstants.PROCESSGROUP,true);
        node=node.addJDFNode("Scanning");
        JDFNodeInfo ni=node.appendNodeInfo();
        assertTrue(ni.hasAttribute(AttributeName.CLASS));
        assertEquals(ni.getVersion(true),EnumVersion.Version_1_3);
    }
    
    public void testMatchesPath()
    {
        JDFDoc doc = new JDFDoc(ElementName.JDF);
        JDFNode node = doc.getJDFRoot();
        node.setType("Product",true);
        node.setVersion(JDFElement.EnumVersion.Version_1_3);
        JDFNodeInfo ni=node.appendNodeInfo();
        ni=(JDFNodeInfo) ni.addPartition(EnumPartIDKey.Run,"R1");
        JDFContact c=(JDFContact)node.addResource(ElementName.CONTACT, null, null, null, null, null, null);
        ni.refElement(c);
        JDFComChannel cc=(JDFComChannel)node.addResource(ElementName.COMCHANNEL, null, null, null, null, null, null);
        c.refElement(cc);
        assertTrue("contact",ni.getContact()==c);
        assertTrue("hasrefelement",ni.hasChildElement(ElementName.CONTACT,null));
        JDFRefElement re=(JDFRefElement)ni.getElement("ContactRef");          
        assertTrue("refelementok", re.getTarget()==c);
        assertTrue("comchannel",c.getComChannel(0)==cc);
        assertTrue("hasrefelement",c.hasChildElement(ElementName.COMCHANNEL,null));
        JDFNode n2=node.addProduct();
        JDFNodeInfo ni2=n2.appendNodeInfo();
        ni2.refElement(c);
        assertTrue("follow refs in matchespath",c.matchesPath("ResourcePool/NodeInfo/Contact",true));
        assertTrue("follow refs in matchespath",cc.matchesPath("ResourcePool/NodeInfo/Contact/ComChannel",true));
        assertTrue("follow refs in matchespath",cc.matchesPath("JDF/ResourcePool/NodeInfo/Contact/ComChannel",true));
        assertTrue("follow refs in matchespath",cc.matchesPath("/JDF/ResourcePool/NodeInfo/Contact/ComChannel",true));
        assertTrue("follow refs in matchespath",cc.matchesPath("JDF/JDF/ResourcePool/NodeInfo/Contact/ComChannel",true));
        assertTrue("follow refs in matchespath",cc.matchesPath("/JDF/JDF/ResourcePool/NodeInfo/Contact/ComChannel",true));
        assertFalse("follow refs in matchespath",cc.matchesPath("JDF/JDF/JDF/ResourcePool/NodeInfo/Contact/ComChannel",true));
        assertFalse("follow refs in matchespath",cc.matchesPath("JDF/JDF/JDF/ResourcePool/NodeInfo/Contact/ComChannel",true));
        assertFalse("follow refs in matchespath",c.matchesPath("ResourcePool/NodeInfo/Contact/ComChannel",true));
        
    }
    public void testRefElement()
    {
        JDFDoc doc = new JDFDoc(ElementName.JDF);
        JDFNode node = doc.getJDFRoot();
        node.setType("Product",true);
        node.setVersion(JDFElement.EnumVersion.Version_1_2);
        JDFNodeInfo ni=node.appendNodeInfo();
        ni.appendElement("foo:bar","www.foo.com"); // want a non jdf ns element to see if any class casts occur
        JDFContact c=(JDFContact)node.addResource(ElementName.CONTACT, null, null, null, null, null, null);
        VString vCTypes=new VString();
        vCTypes.add("Customer");
        c.setContactTypes(vCTypes);
        
        ni.refElement(c);
        JDFComChannel cc=(JDFComChannel)node.addResource(ElementName.COMCHANNEL, null, null, null, null, null, null);
        c.refElement(cc);
        
        assertEquals("contact",ni.getChildWithMatchingAttribute(ElementName.CONTACT,"ContactTypes",null,"Customer",0,true,null),c);
        assertEquals("contact",ni.getParentJDF().getChildWithAttribute(ElementName.CONTACT,"ContactTypes",null,"Customer",0,false),c);

        assertEquals("contact",ni.getContact(),c);
        assertTrue("hasrefelement",ni.hasChildElement(ElementName.CONTACT,null));
        JDFRefElement re=(JDFRefElement)ni.getElement("ContactRef");          
        assertTrue("refelementok", re.getTarget()==c);
        assertTrue("comchannel",c.getComChannel(0)==cc);
        assertTrue("hasrefelement",c.hasChildElement(ElementName.COMCHANNEL,null));
        JDFNode n2=node.addProduct();
        JDFNodeInfo ni2=n2.appendNodeInfo();
        ni2.refElement(c);
        assertTrue("follow refs in matchespath",c.matchesPath("NodeInfo/Contact",true));
        assertTrue("follow refs in matchespath",cc.matchesPath("NodeInfo/Contact/ComChannel",true));
        assertFalse("follow refs in matchespath",c.matchesPath("NodeInfo/Contact/ComChannel",true));
        
        assertTrue("contact 2",ni2.getContact()==c);
        assertTrue("hasrefelement 2",ni2.hasChildElement(ElementName.CONTACT,null));
        re=(JDFRefElement)ni2.getElement("ContactRef");          
        assertTrue("refelementok 2", re.getTarget()==c);
        
        ni2.inlineRefElements(null,null,true);
        assertNull("get ref post inline",ni2.getElement("ContactRef"));
        assertNotNull("refElement has been removed",node.getResourcePool().getElement("Contact"));
        assertTrue("haselement 3",ni2.hasChildElement(ElementName.CONTACT,null));
        c=ni2.getContact();
        re=(JDFRefElement)c.getElement("ComChannelRef");          
        assertTrue("refelementok 2", re.getTarget()==cc);
        ni2.inlineRefElements(null,null,false);
        assertNull("get ref post inline 2",ni2.getElement("ComChannelRef"));
        assertTrue("haselement 4",c.hasChildElement(ElementName.COMCHANNEL,null));
        
        ni.inlineRefElements(null,null,true);
        assertNull("get ref post inline",ni.getElement("ContactRef"));
        assertNull("refElement has been removed",node.getResourcePool().getElement("Contact"));
        assertTrue("haselement 3",ni.hasChildElement(ElementName.CONTACT,null));
        
        c=ni.getContact();
        c.makeRootResource(null,null,true);
        re=(JDFRefElement)ni.getElement("ContactRef");
        re.deleteRef(true);
        assertNull(c.getElement("ContactRef"));        
    }
    
////////////////////////////////////////////////////////////////////////////
    
    public void testIsValid()
    {
        File testData = new File(sm_dirTestData + "SampleFiles");
        assertTrue("testData dir", testData.isDirectory());
        File[] fList = testData.listFiles();
        JDFParser p = new JDFParser();
        JDFParser p2 = new JDFParser();
        p2.m_SchemaLocation = sm_dirTestSchema + "JDF.xsd";
        
        for (int i = 0; i < fList.length; i++)
        {
            File file = fList[i];
            // skip directories in CVS environments
            if (file.isDirectory())
                continue;
            // skip schema files
            if (file.getPath().endsWith(".xsd"))
                continue;
            
            System.out.println("Parsing: " + file.getPath());
            JDFDoc jdfDoc = p.parseFile(file.getPath());
            assertTrue("parse ok", jdfDoc != null);
            KElement e = null;
            if (jdfDoc != null)
            {
                e = jdfDoc.getRoot();
                assertTrue("valid doc: " + file.getPath(), e.isValid(EnumValidationLevel.RecursiveComplete));
            }
            
            // now with schema validation
            jdfDoc = p2.parseFile(file.getPath());
            assertTrue("schema parse ok", jdfDoc != null);
            // TODO fix handling of prerelease default attributes
            if (jdfDoc != null)
            {
                e = jdfDoc.getRoot();
                assertTrue("valid doc: " + file.getPath(), e.isValid(EnumValidationLevel.RecursiveComplete));
            }
        }
    }
    
///////////////////////////////////////////////////////////////////////////////////
    
    public void testIsInvalid()
    {
        File testData = new File(sm_dirTestData + "BadSampleFiles");
        assertTrue("testData dir", testData.isDirectory());
        File[] fList = testData.listFiles();
        JDFParser p = new JDFParser();
        JDFParser p2 = new JDFParser();
        p2.m_SchemaLocation = sm_dirTestSchema + "JDF.xsd";
        
        for (int i = 0; i < fList.length; i++)
        {
            File file = fList[i];
            // skip directories in CVS environments
            if (file.isDirectory())
                continue;
            
            // skip schema files
            if (file.getPath().endsWith(".xsd"))
                continue;
            
            System.out.println("Parsing: " + file.getPath());
            JDFDoc jdfDoc = p.parseFile(file.getPath());
            assertTrue("parse ok", jdfDoc != null);
            KElement e = null;
            if (jdfDoc != null)
            {
                e = jdfDoc.getRoot();
                assertFalse("valid doc: " + file.getPath(), e
                        .isValid(EnumValidationLevel.RecursiveComplete));
            }
            
            // now with schema validation
            jdfDoc = p2.parseFile(file.getPath());
            assertTrue("schema parse ok", jdfDoc != null);
            // TODO fix handling of prerelease default attributes
            if (jdfDoc != null)
            {
                e = jdfDoc.getRoot();
                assertFalse("valid doc: " + file.getPath(), e
                        .isValid(EnumValidationLevel.RecursiveComplete));
            }
        }
    }
    
///////////////////////////////////////////////////////////////////////////    
    
    public void testVersions()
    {
        assertEquals(JDFVersions.getTheOffset(EnumVersion.Version_1_0),0);
        assertEquals(JDFVersions.getTheOffset(EnumVersion.Version_1_2),8);
    }
///////////////////////////////////////////////////////////////////////////    
    public void testSetEnumerationsAttribute()
    {
        JDFDoc d=new JDFDoc("JDF");
        JDFElement root=d.getJDFRoot();
        root.setEnumerationsAttribute("dummy", null, null);
        assertNull(root.getEnumerationsAttribute("dummy", null, EnumNodeStatus.Aborted, false));
        Vector v=new Vector();
        v.add(EnumNodeStatus.Cleanup);
        v.add(EnumNodeStatus.Completed);
        root.setEnumerationsAttribute("dummy", v, null);
        assertEquals("round trip enumerations",root.getEnumerationsAttribute("dummy", null, EnumNodeStatus.Aborted, false),v);
     }
///////////////////////////////////////////////////////////////////////////    
        
        public void testStatusEquals()
        {
        // test if the auto classes implement the correct status
        
        // compare EnumNodeStatus
        JDFAuditPool myAuditPool = null;
        
        JDFDoc jdfDoc = new JDFDoc(ElementName.JDF);
        
        JDFNode jdfRoot = (JDFNode) jdfDoc.getRoot();
        assertTrue("No Root found", jdfRoot != null);
        if (jdfRoot == null)
            return; // soothe findbugs ;)
        
        JDFAncestor ancestor = jdfRoot.appendAncestorPool().appendAncestor();
        ancestor.setStatus(EnumNodeStatus.Completed);
        
        myAuditPool = jdfRoot.getCreateAuditPool();
        JDFPhaseTime phaseTime = myAuditPool.addPhaseTime(JDFElement.EnumNodeStatus.Completed,null,null);
        JDFSpawned spawned = myAuditPool.addSpawned(jdfRoot, null,null,null,null);
        spawned.setStatus(JDFElement.EnumNodeStatus.Completed);
        
        assertEquals(spawned.getStatus(), phaseTime.getStatus());
        assertEquals(spawned.getStatus(), ancestor.getStatus());
        
        
        JDFDoc jmfDoc = new JDFDoc(ElementName.JMF);
        
        JDFJMF jmfRoot = jmfDoc.getJMFRoot();
        assertTrue("No Root found", jmfRoot != null);
        if (jmfRoot == null)
            return; // soothe findbugs ;)
        
        JDFAcknowledge acknowledge = jmfRoot.appendAcknowledge();
        acknowledge.setType("PipePush");    // Type is required and its existance is validated for messages
        JDFJobPhase jobPhase = acknowledge.appendJobPhase();
        jobPhase.setStatus(EnumNodeStatus.Completed);
        
        JDFMessage message = jmfRoot.appendMessageElement(EnumFamily.Command, null);
        message.setType("PipePush");    // Type is required and its existance is validated for messages
        JDFPipeParams pipeParams = message.appendPipeParams();
        pipeParams.setStatus(EnumNodeStatus.Completed);
        
        assertEquals(jobPhase.getStatus(), pipeParams.getStatus());
        assertEquals(spawned.getStatus(), pipeParams.getStatus());
        
        
        // compare EnumResStatus
        JDFDoc responseDoc = new JDFDoc(ElementName.RESPONSE);       
        JDFResponse responseRoot = (JDFResponse) responseDoc.getRoot();
        assertTrue("No Root found", responseRoot != null);
        if (responseRoot == null)
            return; // soothe findbugs ;)
        
        responseRoot.setType(ElementName.RESOURCE);
        JDFResourceInfo resInfo = responseRoot.appendResourceInfo();
        resInfo.setResStatus(EnumResStatus.Available);
        
        JDFDoc commandDoc = new JDFDoc(ElementName.COMMAND);       
        JDFCommand commandRoot = (JDFCommand) commandDoc.getRoot();
        assertTrue("No Root found", commandRoot != null);
        if (commandRoot == null)
            return; // soothe findbugs ;)
        
        commandRoot.setType(ElementName.RESOURCE);
        JDFResourceCmdParams resCmdParams = commandRoot.appendResourceCmdParams();
        resCmdParams.setResStatus(EnumResStatus.Available);
        
        assertEquals(resInfo.getStatus(), resCmdParams.getStatus());
        
        
        // check EnumQueueStatus
        JDFDoc queueDoc = new JDFDoc(ElementName.QUEUE);       
        JDFQueue queueRoot = (JDFQueue) queueDoc.getRoot();
        assertTrue("No Root found", queueRoot != null);
        if (queueRoot == null)
            return; // soothe findbugs ;)
        
        queueRoot.setQueueStatus(EnumQueueStatus.Running);
        
        // check EnumQueueEntryStatus
        JDFQueueEntry queueEntry = queueRoot.appendQueueEntry();
        queueEntry.setQueueEntryStatus(EnumQueueEntryStatus.Running);
    }
    
}