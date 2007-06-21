/*--------------------------------------------------------------------------------------------------
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
 */
package org.cip4.jdflib.core;

import java.io.File;
import java.util.Vector;

import org.cip4.jdflib.JDFTestCaseBase;
import org.cip4.jdflib.core.XMLDocUserData.EnumDirtyPolicy;
import org.cip4.jdflib.datatypes.VJDFAttributeMap;
import org.cip4.jdflib.node.JDFNode;
import org.cip4.jdflib.resource.JDFResourceTest;
import org.cip4.jdflib.util.JDFSpawn;
import org.w3c.dom.Attr;

/**
 * @author MuchaD
 */
public class XMLDocTest extends JDFTestCaseBase
{

    public void testIsDirty() 
    {
        XMLDoc doc=new XMLDoc("test",null);
        KElement e=doc.getRoot();
        assertFalse(e.isDirty());
        doc.getCreateXMLDocUserData().setDirtyPolicy(EnumDirtyPolicy.Doc);
        assertFalse(e.isDirty());
        e.setAttribute("foo","bar");
        assertTrue(e.isDirty());
        doc.clearDirtyIDs();
        assertFalse(e.isDirty());
        KElement e2=e.appendElement("foobar");
        assertTrue(e.isDirty());
        assertTrue(e2.isDirty());
        doc.getCreateXMLDocUserData().setDirtyPolicy(EnumDirtyPolicy.XPath);
        doc.clearDirtyIDs();
        assertFalse(e.isDirty());
        e2=e.appendElement("foobar");
        assertTrue(e.isDirty());
        assertTrue(e2.isDirty());
    }

    public void testSetSchemaLocation() throws Exception
    {
        XMLDoc doc=new XMLDoc("test",null);
        doc.write2File(sm_dirTestDataTemp+"schematest.xml", 0, false); // create a readable dummy
        final File schema = new File(sm_dirTestDataTemp+"schematest.xml");
        
        final String nsURI = "www.foo.com";
        doc.setSchemaLocation(nsURI, schema);
        assertNotNull(doc.getSchemaLocation(nsURI));
        assertEquals(doc.getSchemaLocationFile(nsURI).getCanonicalFile(), schema.getCanonicalFile());
    }
    
    
    public void testDirtyIDs() throws Exception
    {
        // -i bookintent.jdf -o spawned.jdf -p 4
        String xmlFile   = "bookintent.jdf";
        String outFile   = "spawned.jdf";
        String strPartID = "4";

        JDFParser p     = new JDFParser();
        JDFDoc jdfDocIn = p.parseFile(sm_dirTestData + xmlFile);

        assertTrue(jdfDocIn != null);
        if (jdfDocIn == null)
            return; // soothe findbugs ;)

        XMLDocUserData xmlUserData = jdfDocIn.getCreateXMLDocUserData();
        xmlUserData.setDirtyPolicy(XMLDocUserData.EnumDirtyPolicy.ID);

        JDFNode rootIn = (JDFNode) jdfDocIn.getRoot();

        JDFNode nodeToSpawn;
        if (strPartID.equals(""))
        {
            nodeToSpawn = rootIn;
        }
        else
        {
            nodeToSpawn = rootIn.getJobPart(strPartID, "");
        }

        if (nodeToSpawn == null)
        {
            fail("No such JobPartID: " + strPartID);
        }
        else
        {
            Vector vRWResources = new Vector();
            vRWResources.addElement("Component");
            vRWResources.addElement("RunList");

            VJDFAttributeMap vSpawnParts = new VJDFAttributeMap();
            final JDFSpawn spawn=new JDFSpawn(nodeToSpawn);

            JDFNode node = spawn.spawn(xmlFile,outFile,vRWResources,vSpawnParts,false,false,false,false);

            // neu gespawntes File rausschreiben
            JDFNode rootOut = node;
            XMLDoc docOut = rootOut.getOwnerDocument_KElement();
            docOut.write2File(sm_dirTestDataTemp + outFile, 0, true);

            // verändertes Ausgangsfile rausschreiben
            String strOutXMLFile = "_" + xmlFile;
            rootIn.eraseEmptyNodes(true);
            jdfDocIn.write2File(sm_dirTestDataTemp + strOutXMLFile, 0, true);
            assertTrue("SpawnJDF ok", true);

            // test, if all changed nodes are in our list

            VString vstrDirtyIDs = jdfDocIn.getDirtyIDs();
            assertEquals(vstrDirtyIDs.size() , 5);
            assertTrue(vstrDirtyIDs.contains("n0014")); // audit pool was added
            assertTrue(vstrDirtyIDs.contains("n0016")); // status changed: waiting --> spawned
            assertTrue(vstrDirtyIDs.contains("r0017")); // SpawnStatus="SpawnedRW" added
            assertTrue(vstrDirtyIDs.contains("r0018")); // SizeIntent added
        }
    }
    public void testCreateElement() throws Exception
    {
        XMLDoc d=new XMLDoc("TEST",null);
        KElement e=(KElement) d.createElement("foo:bar");
//        e.appendElement("bar:foo");
        e.setAttribute("foo:at", "1");
        e.appendElement("bar2");
        d.getRoot().appendChild(e);
        assertEquals(e.getAttribute("foo:at"),"1");
        
    }
    public void testCreateElementNoNS() throws Exception
    {
        XMLDoc d=new XMLDoc("TEST",null);
        d.getMemberDocument().setIgnoreNSDefault(true);
        KElement e=(KElement) d.createElement("bar");
        assertNull(e.getNamespaceURI());
        
    }
    public void testCreateElementThreads() throws Exception
    {
        XMLDoc d1=new XMLDoc("JDF",null);
        assertEquals("XMLDoc only creates KElement",d1.getRoot().getClass(), KElement.class);
        JDFDoc jd=new JDFDoc("JDF");
        assertEquals("JDFDoc creates typesafe elements",jd.getRoot().getClass(), JDFNode.class);
        XMLDoc d2=new XMLDoc("JDF",null);
        assertEquals("XMLDoc only creates KElement - Hasmap must not be applied",d2.getRoot().getClass(), KElement.class);         
    }

    public void testParseNoNS() throws Exception
    {
        XMLDoc d=new XMLDoc("TEST",null);
        final String fn = sm_dirTestDataTemp+"testParseNoNS.xml";
        d.write2File(fn, 2, true);
        JDFParser p=new JDFParser();
        JDFDoc d2=p.parseFile(fn);
        KElement root=d2.getRoot();
//        assertNull(root.getNamespaceURI());
        assertFalse(d2.toString().indexOf("xmlns=\"\"")>=0);
        assertFalse(d.toString().indexOf("xmlns=\"\"")>=0);
        assertFalse(root.toString().indexOf("xmlns=\"\"")>=0);
        KElement foo=root.appendElement("foo");
        assertNull(foo.getNamespaceURI());
        
    }
    
    public void testCreateAttribute() throws Exception
    {
        XMLDoc d=new XMLDoc("TEST",null);
        Attr a=d.createAttribute("dom1");
        assertNotNull("a",a);
        boolean bcatch=false;
        try{
            d.createAttribute("xmlns:foo");
        }catch (Exception e){
            bcatch=true;
        }
        assertTrue("catch b",!bcatch);
        bcatch=false;
        try{
            d.createAttribute("foo:dom1");
        }catch (Exception e){
            bcatch=true;
        }
        assertTrue("catch c",!bcatch);

    }

    public void testRegisterClass() throws Exception
    {
        JDFDoc d = new JDFDoc("JDF");
        JDFNode n = d.getJDFRoot();

        XMLDoc.registerCustomClass("JDFTestType", "org.cip4.jdflib.core.JDFTestType");
        JDFTestType tt = (JDFTestType) n.appendElement("JDFTestType", null);
        tt.setAttribute("fnarf", 3, null);
        assertTrue("extension is valid", tt.isValid(KElement.EnumValidationLevel.Complete));

        XMLDoc.registerCustomClass("fnarf:JDFTestType", "org.cip4.jdflib.core.JDFTestType");
        tt = (JDFTestType) n.appendElement("fnarf:JDFTestType", "WWW.fnarf.com");
        tt.setAttribute("fnarf", 3, null);
        assertTrue("ns extension is valid", tt.isValid(KElement.EnumValidationLevel.Complete));
        tt.setAttribute("fnarf", "a", null); // illegal - must be integer
        assertTrue("ns extension is valid", !tt.isValid(KElement.EnumValidationLevel.Complete));
        tt.removeAttribute("fnarf", null);
        assertTrue("ns extension is valid", tt.isValid(KElement.EnumValidationLevel.Complete));
        tt.setAttribute("gnu", "a", null); // illegal - non existent
        assertFalse("ns extension is valid", tt.isValid(KElement.EnumValidationLevel.Complete));

//      boolean bClassCast = false;
//      try
//      {
//      tt = (JDFTestType) n.appendElement("blub:JDFTestType", "WWW.fnarf2.com");
//      }
//      catch (ClassCastException exc)
//      {
//      bClassCast = true;
//      }
//      assertTrue("ns extension works", bClassCast);
        assertTrue("ns extension works", !(n.appendElement("blub:JDFTestType", "WWW.fnarf2.com") instanceof JDFTestType));
    } 

    public void testNSRoot()
    {
        XMLDoc d=new XMLDoc("JDF:foo",null);
        KElement e=d.getRoot();
        assertFalse("E K",e instanceof JDFElement);


        d=new XMLDoc("a:foo","bar");
        e=d.getRoot();
        assertFalse("E K",e instanceof JDFElement);

        d=new XMLDoc("_foo",null);
        e=d.getRoot();
        assertFalse("E K",e instanceof JDFElement);

        d=new XMLDoc("bar:foo",JDFConstants.JDFNAMESPACE);
        e=d.getRoot();
        assertTrue("E K",e instanceof JDFElement);

        d=new XMLDoc("Myfoo",JDFConstants.JDFNAMESPACE);
        e=d.getRoot();
        assertTrue("E K",e instanceof JDFElement);

        d=new XMLDoc("JDF:Myfoo",JDFConstants.JDFNAMESPACE);
        e=d.getRoot();
        assertTrue("E K",e instanceof JDFElement);

        d=new XMLDoc("Myfoo",null);
        e=d.getRoot();
        assertFalse("E K",e instanceof JDFElement);
    }

    public void testClone() throws Exception
    {
        XMLDoc doc=new XMLDoc("foobar",null);
        XMLDoc doc2=(XMLDoc)doc.clone();
        assertNotNull(doc.getDocumentElement());
        assertNotNull(doc2.getDocumentElement());
        assertNotSame(doc.getDocumentElement(), doc2.getDocumentElement());
        KElement e=doc.getRoot();
        e.setAttribute("foo", "bar");
        assertTrue(e.hasAttribute("foo"));
        KElement e2=doc2.getRoot();
        assertFalse(e2.hasAttribute("foo"));
        assertEquals(doc.getDoctype(), doc2.getDoctype());
        assertNotSame(doc.getXMLDocUserData(), doc2.getXMLDocUserData());

    }
    
    public void testWriteToFile() throws Exception
    {
        XMLDoc d=new XMLDoc("doc",null);
        String out=sm_dirTestDataTemp+File.separator+"dir"+File.separator+"dir2";
        File dir=new File(out);
        if(dir.isDirectory())
            dir.delete();
        else
            dir.mkdirs();
        
        out+=File.separator+"d.xml";
        
        assertTrue(d.write2File(out, 2, true));
        File f=new File(out);
        assertTrue(f.canRead());
    }
    
    public void testSize()
    {
        Runtime.getRuntime().gc();
        Runtime.getRuntime().gc();
        Runtime.getRuntime().gc();
        XMLDoc d = new XMLDoc("JDF:foo", null);
        long mem = d.getDocMemoryUsed();
        String s = d.write2String(0);
        assertTrue("mem", mem + 100000 > s.length());
        // the gc is messy and sometimes returns +/- a few 10k
        assertTrue("mem", mem + 100000 > s.length());
        d = JDFResourceTest.creatXMDoc();
        mem = d.getDocMemoryUsed();
        s = d.write2String(0);
        assertTrue("mem", mem + 10000 > s.length());
        d = new XMLDoc("foo", null);
        KElement e = d.getRoot();
        KElement e2 = e.appendElement("e2");
        KElement e3 = e2.appendElement("e3");
        for (int i = 33; i < 999; i++)
            e3.setAttribute("k" + String.valueOf(i), "value" + String.valueOf(i));
        for (int j = 0; j < 99; j++)
            e2.copyElement(e3, null);
        mem = d.getDocMemoryUsed();
        s = d.write2String(0);
        assertTrue("mem", mem + 10000 > s.length());
    }

    ////////////////////////////////////////////////////////
    /**
     * test whether the serializer correctly serializes quotes etc.
     */
    public void testEscapeStrings()
    {
        XMLDoc d=new XMLDoc("foo","www.foo.com");
        KElement e=d.getRoot();
        e.setAttribute("bar", "><&'\"");
        String s=d.write2String(0);
        assertTrue(s.indexOf("&lt;")>0);
        assertTrue(s.indexOf("&amp;")>0);
        assertTrue(s.indexOf("&quot;")>0);
    }
}