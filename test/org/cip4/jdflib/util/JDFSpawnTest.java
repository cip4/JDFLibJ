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
package org.cip4.jdflib.util;

import java.util.HashSet;
import java.util.Vector;

import org.cip4.jdflib.JDFTestCaseBase;
import org.cip4.jdflib.core.AttributeName;
import org.cip4.jdflib.core.ElementName;
import org.cip4.jdflib.core.JDFAudit;
import org.cip4.jdflib.core.JDFComment;
import org.cip4.jdflib.core.JDFConstants;
import org.cip4.jdflib.core.JDFCustomerInfo;
import org.cip4.jdflib.core.JDFDoc;
import org.cip4.jdflib.core.JDFNodeInfo;
import org.cip4.jdflib.core.JDFParser;
import org.cip4.jdflib.core.JDFResourceLink;
import org.cip4.jdflib.core.VElement;
import org.cip4.jdflib.core.VString;
import org.cip4.jdflib.core.XMLDoc;
import org.cip4.jdflib.core.JDFAudit.EnumAuditType;
import org.cip4.jdflib.core.JDFAudit.EnumSeverity;
import org.cip4.jdflib.core.JDFElement.EnumVersion;
import org.cip4.jdflib.core.JDFResourceLink.EnumUsage;
import org.cip4.jdflib.core.KElement.EnumValidationLevel;
import org.cip4.jdflib.datatypes.JDFAttributeMap;
import org.cip4.jdflib.datatypes.VJDFAttributeMap;
import org.cip4.jdflib.node.JDFAncestor;
import org.cip4.jdflib.node.JDFNode;
import org.cip4.jdflib.node.JDFNode.EnumCleanUpMerge;
import org.cip4.jdflib.node.JDFNode.EnumProcessUsage;
import org.cip4.jdflib.node.JDFNode.EnumType;
import org.cip4.jdflib.pool.JDFAuditPool;
import org.cip4.jdflib.resource.JDFResource;
import org.cip4.jdflib.resource.JDFResourceAudit;
import org.cip4.jdflib.resource.JDFResourceTest;
import org.cip4.jdflib.resource.JDFResource.EnumAmountMerge;
import org.cip4.jdflib.resource.JDFResource.EnumPartIDKey;
import org.cip4.jdflib.resource.JDFResource.EnumResStatus;
import org.cip4.jdflib.resource.process.JDFComponent;
import org.cip4.jdflib.resource.process.JDFExposedMedia;
import org.w3c.dom.Node;


public class JDFSpawnTest extends JDFTestCaseBase       
{

    /**
     * Method testUnSpawn.
     * @throws Exception
     */
    private void unSpawn (String strXMLFile, String strSpawnID)
    {
        String strXMLFileModified = "_" + strXMLFile;

        JDFParser p = new JDFParser();
        JDFDoc doc = p.parseFile(sm_dirTestDataTemp + strXMLFileModified);

        // parse the original file, which is already spawned 
        assertTrue("Parse of file "+sm_dirTestDataTemp+strXMLFileModified+" failed", doc != null);  // _bookintent.jdf

        JDFNode root = (JDFNode) doc.getRoot();
        assertNotNull(root);
        if (root != null)
        {
            root=root.unSpawn(strSpawnID);
            assertTrue (" root empty",root.toString().indexOf(strSpawnID)<0);

            // write out the unspawned file
            doc.write2File(sm_dirTestDataTemp + "Unspawn_" + strXMLFile, 0, true);   // Unspawn_bookintent.jdf
        }       

        // Vergleich, ob alles richtig gelaufen ist
//      compareXMLFiles (strXMLFile + "_Unspawn.jdf", strXMLFile+".jdf");
    }


    public void testCorruptPartitionedSpawn()
    {
        JDFDoc d=new JDFDoc("JDF");
        JDFNode root=d.getJDFRoot();
        root.setType(EnumType.ProcessGroup);
        JDFNode cp=root.addJDFNode(EnumType.ConventionalPrinting);
        JDFNode fold=root.addJDFNode(EnumType.Folding);
        JDFComponent comp=(JDFComponent) cp.addResource("Component", null, EnumUsage.Output, null, root, null, null);
        JDFAttributeMap cMap=new JDFAttributeMap();
        cMap.put(EnumPartIDKey.SignatureName, "Sig1");
        cMap.put(EnumPartIDKey.SheetName, "S1");
        cMap.put(EnumPartIDKey.Condition, "Good");
        comp.getCreatePartition(cMap, new VString("SignatureName SheetName Condition"," "));
        cMap.put(EnumPartIDKey.Condition, "Waste");
        comp.getCreatePartition(cMap, null);
        comp.setResStatus(EnumResStatus.Available, true);
        fold.linkResource(comp, EnumUsage.Input, null);

        JDFComponent compOut=(JDFComponent) fold.addResource("Component", null, EnumUsage.Output, null, root, null, null);
        compOut.addPartition(EnumPartIDKey.Condition, "Good");
        compOut.addPartition(EnumPartIDKey.Condition, "Waste");
        JDFAttributeMap map=new JDFAttributeMap();
        map.put(EnumPartIDKey.SignatureName, "Sig1");
//      map.put(EnumPartIDKey.SheetName, "Sht1");
        VJDFAttributeMap v=new VJDFAttributeMap();
        v.add(map);
        VString vRW=new VString("Output",null);
        JDFSpawn spawn=new JDFSpawn(fold);
        JDFNode spawned=spawn.spawn(null, null, vRW, v, true, true, true, true);

        JDFComponent spCompOut=(JDFComponent) spawned.getMatchingLink("Component", EnumProcessUsage.AnyOutput, 0).getLinkRoot();
        assertTrue("partition structure is zapped",spCompOut.isValid(EnumValidationLevel.Incomplete));

    }

    public void testSubsetPartitionedSpawn()
    {
        for(int i=0;i<2;i++)
        {
            JDFDoc d=new JDFDoc("JDF");
            JDFNode root=d.getJDFRoot();
            root.setType(EnumType.ProcessGroup);
            JDFNode cp=root.addJDFNode(EnumType.ConventionalPrinting);
            JDFComponent comp=(JDFComponent) cp.addResource("Component", null, EnumUsage.Output, null, root, null, null);
            JDFAttributeMap cMap=new JDFAttributeMap();
            cMap.put(EnumPartIDKey.SignatureName, "Sig1");
            cMap.put(EnumPartIDKey.SheetName, "S1");
            comp.getCreatePartition(cMap, new VString("SignatureName SheetName"," "));
            comp.getCreatePartition(cMap, null);
            comp.setResStatus(EnumResStatus.Available, true);

            cMap.put(EnumPartIDKey.Side, "Front");
            VJDFAttributeMap v=new VJDFAttributeMap();
            v.add(cMap);
            VString vRW=new VString("Output",null);

            JDFNodeInfo ni=cp.appendNodeInfo();
            ni.getCreatePartition(cMap,new VString("SignatureName SheetName Side"," "));
            JDFSpawn spawn=new JDFSpawn(cp);
            if(i==0)
                spawn.bFixResources=false;
            JDFNode spawned=spawn.spawn(null, null, vRW, v, true, true, true, true);

            JDFComponent spCompOut=(JDFComponent) spawned.getMatchingLink("Component", EnumProcessUsage.AnyOutput, 0).getLinkRoot();
            if(i==0)
                assertNull("partition structure is zapped",spCompOut.getPartition(cMap, null));
            else
                assertNotNull("partition structure is notzapped",spCompOut.getPartition(cMap, null));
            cMap.remove(EnumPartIDKey.Side);
            assertNotNull("partition structure is zapped",spCompOut.getPartition(cMap, null));
        }
    }

    ///////////////////////////////////////////////////////////

    public void testSpawnPartMulti()
    {
        JDFDoc dRoot=new JDFDoc("JDF");
        JDFNode nRoot=dRoot.getJDFRoot();
        JDFCustomerInfo ci=nRoot.appendCustomerInfo();
        ci.setCustomerProjectID("foo");

        nRoot.setType("Product",true);

        JDFDoc d=JDFResourceTest.creatXMDoc();
        JDFNode n=(JDFNode) nRoot.copyElement(d.getJDFRoot(),null);
        JDFExposedMedia xm=(JDFExposedMedia) n.getMatchingResource(ElementName.EXPOSEDMEDIA,EnumProcessUsage.AnyInput,null,0);
        JDFNode nPS=nRoot.addJDFNode("ImageSetting");
        nPS.linkResource(xm,false ? EnumUsage.Input : EnumUsage.Output,null);
        Vector v=new Vector();
        v.add(ElementName.EXPOSEDMEDIA);
        VJDFAttributeMap vMap=new VJDFAttributeMap();
        JDFAttributeMap map=new JDFAttributeMap();
        map.put("SignatureName","Sig1");
        vMap.add(map);

        JDFNode spawnedPSNodeInfo=nPS.spawnInformative(null,null,vMap,false,true,true,true);
        assertEquals("cpi",spawnedPSNodeInfo.getInheritedCustomerInfo("@CustomerProjectID").getCustomerProjectID(),"foo");

        JDFNode spawnedPSNode=nPS.spawn(null,null,v,vMap,false,true,true,true);
        assertEquals("cpi",spawnedPSNode.getInheritedCustomerInfo("@CustomerProjectID").getCustomerProjectID(),"foo");
        // this one spawns the component rw
        v=new Vector();
        v.add(ElementName.COMPONENT);

        JDFNode spawnedNodeAll=n.spawn("thisUrl","newURL",v,null,false,true,true,true); 
        String spawnID=spawnedNodeAll.getSpawnID();
        // merge and immediately respawn the same thing
        n=n.mergeJDF(spawnedNodeAll,null,EnumCleanUpMerge.RemoveAll,EnumAmountMerge.UpdateLink);
        assertTrue("spawnID gone",nRoot.toString().indexOf(spawnID)<0);

        JDFNode spawnedNode=n.spawn("thisUrl","newURL",v,vMap,false,true,true,true); 
        spawnID=spawnedNode.getSpawnID();
        assertTrue("AncestorPool",spawnedNode.hasChildElement(ElementName.ANCESTORPOOL,null));

        // merge and immediately respawn the same thing
        n=n.mergeJDF(spawnedNode,null,EnumCleanUpMerge.RemoveAll,EnumAmountMerge.UpdateLink);
        assertTrue("spawnID gone",nRoot.toString().indexOf(spawnID)<0);

        spawnedNode=n.spawn("thisUrl","newURL",v,vMap,false,true,true,true);
        assertTrue("AncestorPool after merge",spawnedNode.hasChildElement(ElementName.ANCESTORPOOL,null));

        map.put("SheetName","S1");
        JDFNode respawnedNode=spawnedNode.spawn("reUrl","renewURL",v,vMap,false,true,true,true);
        assertTrue("AncestorPool after respawn",spawnedNode.hasChildElement(ElementName.ANCESTORPOOL,null));

        xm=(JDFExposedMedia) respawnedNode.getMatchingResource(ElementName.EXPOSEDMEDIA,EnumProcessUsage.AnyInput,null,0);
        JDFComponent comp=(JDFComponent) respawnedNode.getMatchingResource(ElementName.COMPONENT,EnumProcessUsage.AnyOutput,null,0);
        VString vSpID=xm.getSpawnIDs(false);
        assertFalse("SpawnIDS",vSpID.isEmpty());
        xm=(JDFExposedMedia) xm.getPartition(map,null);
        assertTrue("xm rw",xm.getLocked());
        comp=(JDFComponent) comp.getPartition(map,null);
        assertFalse("comp rw",comp.getLocked());

        map.put("SheetName","S2");
        JDFNode respawnedNode2 = spawnedNode.spawn("reUrl","renewURL",v,vMap,false,true,true,true);
        xm=(JDFExposedMedia) respawnedNode2.getMatchingResource(ElementName.EXPOSEDMEDIA,EnumProcessUsage.AnyInput,null,0);
        comp=(JDFComponent) respawnedNode2.getMatchingResource(ElementName.COMPONENT,EnumProcessUsage.AnyOutput,null,0);
        vSpID=xm.getSpawnIDs(false);
        assertFalse("SpawnIDS",vSpID.isEmpty());
        xm=(JDFExposedMedia) xm.getPartition(map,null);       
        assertTrue("xm rw",xm.getLocked());
        comp=(JDFComponent) comp.getPartition(map,null);
        assertFalse("comp rw",comp.getLocked());
        String spawnID1=spawnedNode.getSpawnID();
        JDFNode testSpawnedNode=spawnedNode.mergeJDF(respawnedNode2,null,EnumCleanUpMerge.None,EnumAmountMerge.UpdateLink);
        assertTrue("AncestorPool after respawn merge",spawnedNode.hasChildElement(ElementName.ANCESTORPOOL,null));
        assertEquals("spawnID ok",spawnID1,testSpawnedNode.getSpawnID());
        respawnedNode2 = spawnedNode.spawn("reUrl","renewURL",v,vMap,false,true,true,true);

        // now go backwards!
        nRoot.mergeJDF(spawnedNode,null,EnumCleanUpMerge.None,EnumAmountMerge.UpdateLink);
        nRoot.mergeJDF(respawnedNode,null,EnumCleanUpMerge.None,EnumAmountMerge.UpdateLink);
        nRoot.mergeJDF(respawnedNode2,null,EnumCleanUpMerge.None,EnumAmountMerge.UpdateLink);


        nRoot.mergeJDF(spawnedPSNode,null,EnumCleanUpMerge.None,EnumAmountMerge.UpdateLink);
        assertTrue("spawnIDs gone",nRoot.toString().indexOf("SpawnIDs")<0);


    }

    ///////////////////////////////////////////////////////////

    public void testSpawnPart()
    {
        JDFDoc d=JDFResourceTest.creatXMDoc();
        JDFNode n=d.getJDFRoot();
        n.removeNodeInfo();
        JDFNodeInfo ni=n.getCreateNodeInfo();
        assertNotNull("ni",ni);
        JDFComment comment=n.appendComment();
        comment.setText("Comment 1");
//      ni.addPartition(JDFResource.EnumPartIDKey.SignatureName,"Sig1");
        JDFResourceLink l=n.getMatchingLink(ElementName.NODEINFO,EnumProcessUsage.AnyInput,0);
        l.setPart("SignatureName","Sig1");
        Vector v=new Vector();
        v.add(ElementName.EXPOSEDMEDIA);
        VJDFAttributeMap vMap=new VJDFAttributeMap();
        JDFAttributeMap map=new JDFAttributeMap();
        map.put("SignatureName","Sig1");
        map.put("SheetName","S1");
        vMap.add(map);
        JDFResourceLink xmRL=n.getMatchingLink(ElementName.EXPOSEDMEDIA,EnumProcessUsage.AnyInput,0);
        xmRL.setAmount(40,map);
        xmRL.setUsage(EnumUsage.Output); // fudge to test output counting
        JDFNode spawnedNode=n.spawn("thisUrl","newURL",v,vMap,false,true,true,true);
        assertTrue("no spawnStatus",spawnedNode.toString().indexOf(AttributeName.SPAWNSTATUS)<0);
        JDFResourceLink rl = spawnedNode.getMatchingLink(ElementName.EXPOSEDMEDIA,EnumProcessUsage.AnyOutput,0);
        JDFResourceAudit ra=spawnedNode.cloneResourceToModify(rl);
        String clonedResID=ra.getOldLink().getrRef();


        n=d.getJDFRoot();
        JDFComment comment2=n.appendComment();
        comment2.setText("Comment 2 after");
        JDFComment comment3=spawnedNode.appendComment();
        comment3.setText("Comment 3 spawned");

        JDFResourceLink xmRLspawn=spawnedNode.getMatchingLink(ElementName.EXPOSEDMEDIA,EnumProcessUsage.AnyOutput,0);
        xmRLspawn.setActualAmount(42,map);
        assertEquals("amount ok",xmRLspawn.getAmount(map),40.,0);
        assertEquals("act amount ok",xmRLspawn.getActualAmount(map),42.,0.);

        JDFNode mergedNode=n.mergeJDF(spawnedNode,"merged",JDFNode.EnumCleanUpMerge.None , EnumAmountMerge.UpdateLink);
        xmRL=mergedNode.getMatchingLink(ElementName.EXPOSEDMEDIA,EnumProcessUsage.AnyOutput,0);  
        assertEquals("Comment size",mergedNode.getChildElementVector(ElementName.COMMENT, null, null, true, 99,false).size(),3);
        assertTrue("merged amount ok",xmRL.getAmount(map)==40);
        assertTrue("merged act amount ok",xmRL.getActualAmount(map)==42);
        JDFExposedMedia xm=(JDFExposedMedia) n.getMatchingResource(ElementName.EXPOSEDMEDIA,EnumProcessUsage.AnyOutput,null,0);
        xm=(JDFExposedMedia) xm.getPartition(map,null);
        assertTrue("merged res amount ok",xm.getAmount()==42);
        assertTrue("merged res amountproduced ok",xm.getAmountProduced()==42);

        // test whether anythin modified and tracked in a resource audit got correctly merged
        JDFResourceAudit raMerge=(JDFResourceAudit) n.getAuditPool().getAudit(0,EnumAuditType.ResourceAudit,null);
        assertNotNull("res audit merged",raMerge);
        JDFResource rOld=raMerge.getOldLink().getTarget();
        assertNotNull("old res  merged",rOld);
        assertEquals("old res ID",rOld.getID(),clonedResID);
        JDFResource rNew=raMerge.getNewLink().getTarget();
        assertNotNull("new res  merged",rNew);
    }

    //////////////////////////////////////////////////////////////

    public void testSpawn2()
    {
        String fileNameIn            = "km2";
        String spawnNodeID           = "Link08539766_000147";

        JDFParser p = new JDFParser();
        JDFDoc jdfDocIn = p.parseFile(sm_dirTestData+fileNameIn+".jdf");
        assertTrue("Parse of file "+sm_dirTestData+fileNameIn+" failed", jdfDocIn != null);

        if (jdfDocIn != null)
        {
            // prepare the spawn process
            JDFNode rootIn = (JDFNode) jdfDocIn.getRoot();

            JDFNode spawnNode = null;
            if (spawnNodeID.equals(""))
            {
                spawnNode = rootIn;
            }
            else
            {
                spawnNode = (JDFNode) rootIn.getTarget(spawnNodeID, AttributeName.ID);
            }
            assertNotNull("No such ID " + spawnNodeID, spawnNode);

            if (spawnNode != null)
            {
                JDFNode jdfSpawned = spawnNode.spawn(
                        sm_dirTestData+fileNameIn+".jdf", null, 
                        null, null, false, true, true,true);

                // verändertes Ausgangsfile rausschreiben
                rootIn.eraseEmptyNodes(true);
                String strXMLFileModified = fileNameIn + "_spawnedSource.xml";
                jdfDocIn.write2File(sm_dirTestDataTemp + strXMLFileModified, 2, true);

                XMLDoc doc2 = jdfSpawned.getOwnerDocument_KElement();
                String strXMLFileModified2 = fileNameIn + "_spawnedTarget.xml";
                doc2.write2File(sm_dirTestDataTemp + strXMLFileModified2, 0, true);
            }
        }
    }


    public void testSpawn3()
    {
        String fileNameIn            = "km2";

        JDFParser p = new JDFParser();
        JDFDoc jdfDocIn = p.parseFile(sm_dirTestData+fileNameIn+".jdf");

        JDFNode root   = jdfDocIn.getJDFRoot();
        JDFNode subJob = root.getJobPart("Qua0", null); //Link08539766_000147
        VElement v = subJob.getChildElementVector("JDF", null, new JDFAttributeMap(), false, Integer.MAX_VALUE,false);
        VElement v2 = new VElement();
        int nEvent=0;
        int nComment=0;
        for(int i = 0; i < v.size(); i++)
        {
            JDFNode spawnNode = (JDFNode)v.elementAt(i);
            spawnNode.getCreateAuditPool().addEvent("me",EnumSeverity.Event);
            spawnNode.appendComment().setText("Comment"+String.valueOf(i));
            JDFNode spawnedNode = spawnNode.spawn(
                    sm_dirTestData + fileNameIn + ".jdf", null, null, null,
                    false, true, true, true);
            v2.add(spawnedNode);
            nEvent+=spawnedNode.getChildrenByTagName("Notification","",new JDFAttributeMap(),false,false,0).size();
            nComment+=spawnedNode.numChildNodes(Node.COMMENT_NODE);
        }
        for(int i = 0; i < v2.size(); i++)
        {
            JDFNode nodeToMerge = (JDFNode)v2.elementAt(i);
            root.mergeJDF(nodeToMerge, JDFConstants.EMPTYSTRING, JDFNode.EnumCleanUpMerge.None,
                    JDFResource.EnumAmountMerge.None);
        }
        assertEquals(nEvent,root.getChildrenByTagName("Notification","",new JDFAttributeMap(),false,false,0).size());

        int copyComments=0;
        v = subJob.getChildElementVector("JDF", null, null, false, Integer.MAX_VALUE,false);
        for(int i = 0; i < v.size(); i++)
        {
            JDFNode spawnNode = (JDFNode)v.elementAt(i);
            copyComments+=spawnNode.numChildNodes(Node.COMMENT_NODE);           
        }        

        assertEquals(nComment,copyComments);

        jdfDocIn.write2File(sm_dirTestDataTemp + "km2_AllSpawnedAndMerged.xml", 2, true);
    }

    /**
     * test customerinfo and nodeinfo related stuff
     * including high level access to information in the AncestorPool
     *
     */
    public void testSpawnCINI()
    {
        JDFDoc d=new JDFDoc("JDF");
        JDFNode n=d.getJDFRoot();
        assertEquals("null cid",n.getInheritedCustomerInfo("@CustomerOrderID"),null);
        n.setType("ProcessGroup",false);
        n.setVersion(EnumVersion.Version_1_2);
        JDFCustomerInfo ci=n.appendCustomerInfo();
        ci.setCustomerOrderID("cid");
        assertEquals("cid",n.getInheritedCustomerInfo("@CustomerOrderID").getCustomerOrderID(),"cid");

        VString v=new VString();
        v.add("Interpreting");
        v.add("Rendering");

        JDFNode n2=n.addCombined(v);
        JDFNode spawnedNode=n2.spawn("thisFile","spawnFile",null,null,true,true,true,true);
        ci= spawnedNode.getInheritedCustomerInfo(null);
        assertNotNull("ci",ci);
        assertNotNull("spawned",n.getAuditPool().getAudit(0,JDFAudit.EnumAuditType.Spawned,null));
        String spawnID = spawnedNode.getSpawnID();
        assertEquals("cid",spawnedNode.getInheritedCustomerInfo("@CustomerOrderID").getCustomerOrderID(),"cid");
        JDFAncestor anc=spawnedNode.getAncestorPool().getAncestor(0);
        assertNull("no xsi:type", anc.getAttribute("type",AttributeName.XSIURI,null));

        n=n.unSpawn(spawnID);
        assertNotNull("n2",n);
        assertNull("spawned",d.getJDFRoot().getAuditPool().getAudit(0,JDFAudit.EnumAuditType.Spawned,null));

        n.removeCustomerInfo();
        n.setVersion(EnumVersion.Version_1_3);
        n.appendCustomerInfo();
        spawnedNode=n2.spawn("thisFile","spawnFile",null,null,true,true,true,true);
        spawnID = spawnedNode.getSpawnID();

        ci=spawnedNode.getInheritedCustomerInfo(null);
        assertNotNull("ci",ci);

        n2=d.getJDFRoot().unSpawn(spawnID);
        assertNotNull("n2",n2);
        assertTrue("n2 no spawnID",n2.toString().indexOf(spawnID)<0);
    }    

    ///////////////////////////////////////////////////////////////////////////

    public void testBigSpawn()    
    throws Exception    
    {        
        final String strJDFName = "000023_Test_PR3.0.jdf";
//      final String strJDFName = "biginline.jdf";
        String strJDFPath = sm_dirTestData +  strJDFName;
        JDFParser parser = new JDFParser ();
        JDFDoc jdfDoc = parser.parseFile(strJDFPath);
        VJDFAttributeMap vamParts = new VJDFAttributeMap ();
        JDFAttributeMap amParts0 = new JDFAttributeMap ();
        amParts0.put ("Side", "Front");
        amParts0.put ("SignatureName", "Sig002");
        amParts0.put ("SheetName", "FB 002");
        vamParts.add (amParts0);
        JDFAttributeMap amParts1 = new JDFAttributeMap ();
        amParts1.put ("Side", "Back");
        amParts1.put ("SignatureName", "Sig002");
        amParts1.put ("SheetName", "FB 002");
        vamParts.add (amParts1);
        VString vsRWResourceIDs = new VString ();
        vsRWResourceIDs.add ("Link84847227_000309");
        vsRWResourceIDs.add ("r85326439_027691");
        final JDFNode nodeRoot = jdfDoc.getJDFRoot ();
        JDFNode nodeProc = nodeRoot.getJobPart ("IPD0.I", JDFConstants.EMPTYSTRING);
//      JDFNode nodeProc = nodeRoot;
        JDFNode nodeSubJDF = nodeProc.spawn (strJDFPath, JDFConstants.EMPTYSTRING, vsRWResourceIDs, vamParts, true, true, true,true);
        assertTrue (nodeSubJDF != null);

        if (nodeSubJDF != null) 
        {
            nodeSubJDF.getOwnerDocument_KElement().write2File(sm_dirTestDataTemp+"bigSub.jdf", 2, true);
            jdfDoc.write2File(sm_dirTestDataTemp+"bigMainPost.jdf", 2, true);     
        }
    }

    ///////////////////////////////////////////////////////////////////////


    public void testBigMerge()    
    throws Exception    
    {       
        testBigSpawn();
        JDFParser parser = new JDFParser ();
        JDFDoc jdfDoc = parser.parseFile(sm_dirTestDataTemp+"bigMainPost.jdf");
        JDFParser parser2 = new JDFParser ();
        JDFDoc jdfDocSub = parser2.parseFile(sm_dirTestDataTemp+"bigSub.jdf");
        JDFNode nodeMain=jdfDoc.getJDFRoot();      
        JDFNode nodeSub=jdfDocSub.getJDFRoot();
        JDFNode overWrite=nodeMain.mergeJDF(nodeSub,null,EnumCleanUpMerge.RemoveRRefs,EnumAmountMerge.UpdateLink);
        JDFAuditPool ap=overWrite.getAuditPool();
        assertNotNull(ap.getAudit(0,EnumAuditType.Merged,null));
        jdfDoc.write2File(sm_dirTestDataTemp+"BigMerge.jdf", 2, true);
    }

    ///////////////////////////////////////////////////////////////////////


    public void testCheckSpawnedResources()
    throws Exception
    {
        final String strJDFName = "000023_Test_PR3.0.jdf";
        String strJDFPath = sm_dirTestData +  strJDFName;
        JDFParser parser = new JDFParser ();
        JDFDoc jdfDoc = parser.parseFile(strJDFPath);
//      jdfDoc.getCreateXMLDocUserData().setDirtyPolicy(EnumDirtyPolicy.None);
        VJDFAttributeMap vamParts = new VJDFAttributeMap ();
        JDFAttributeMap amParts0 = new JDFAttributeMap ();
        amParts0.put ("Side", "Front");
        amParts0.put ("SignatureName", "Sig002");
        amParts0.put ("SheetName", "FB 002");
        vamParts.add (amParts0);

        JDFAttributeMap amParts1 = new JDFAttributeMap ();
        amParts1.put ("Side", "Back");
        amParts1.put ("SignatureName", "Sig002");
        amParts1.put ("SheetName", "FB 002");
        vamParts.add (amParts1);

        VString vsRWResourceIDs = new VString ();
        vsRWResourceIDs.add ("Link84847227_000309");
        vsRWResourceIDs.add ("r85326439_027691");

        JDFNode nodeProc = jdfDoc.getJDFRoot ().getJobPart ("IPD0.I", JDFConstants.EMPTYSTRING);

        final HashSet vSpawned = nodeProc.checkSpawnedResources (vsRWResourceIDs, vamParts);

        assertTrue (vSpawned.size () == 0);

    }



}