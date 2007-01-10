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

import java.util.Collection;
import java.util.List;
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
import org.cip4.jdflib.node.JDFSpawned;
import org.cip4.jdflib.node.JDFNode.EnumCleanUpMerge;
import org.cip4.jdflib.node.JDFNode.EnumProcessUsage;
import org.cip4.jdflib.node.JDFNode.EnumType;
import org.cip4.jdflib.pool.JDFAuditPool;
import org.cip4.jdflib.resource.JDFMerged;
import org.cip4.jdflib.resource.JDFResource;
import org.cip4.jdflib.resource.JDFResourceAudit;
import org.cip4.jdflib.resource.JDFResourceTest;
import org.cip4.jdflib.resource.JDFResource.EnumAmountMerge;
import org.cip4.jdflib.resource.JDFResource.EnumPartIDKey;
import org.cip4.jdflib.resource.JDFResource.EnumResStatus;
import org.cip4.jdflib.resource.process.JDFComponent;
import org.cip4.jdflib.resource.process.JDFContact;
import org.cip4.jdflib.resource.process.JDFExposedMedia;
import org.cip4.jdflib.resource.process.JDFMedia;
import org.w3c.dom.Node;


public class JDFSpawnTest extends JDFTestCaseBase       
{

    /**
     * Method testUnSpawn.
     * @throws Exception
     */
    private void unSpawn(String strXMLFile, String strSpawnID)
    {
        String strXMLFileModified = "_" + strXMLFile;

        JDFParser p = new JDFParser();
        JDFDoc doc = p.parseFile(sm_dirTestDataTemp + strXMLFileModified);

        // parse the original file, which is already spawned 
        assertNotNull("Parse of file "+sm_dirTestDataTemp+strXMLFileModified+" failed", doc);  // _bookintent.jdf

        JDFNode root = (JDFNode) doc.getRoot();
        assertNotNull(root);
        if (root != null)
        {
            root=new JDFSpawn(root).unSpawn(strSpawnID);
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
        JDFMedia media=xm.getMedia();
        media.makeRootResource("mediaID", n, true);

        
        JDFNode nPS=nRoot.addJDFNode("ImageSetting");
        nPS.linkResource(xm,false ? EnumUsage.Input : EnumUsage.Output,null);
        Vector v=new Vector();
        v.add(ElementName.EXPOSEDMEDIA);
        VJDFAttributeMap vMap=new VJDFAttributeMap();
        JDFAttributeMap map=new JDFAttributeMap();
        map.put("SignatureName","Sig1");
        vMap.add(map);
        JDFSpawn spawn=new JDFSpawn(nPS);
        JDFNode spawnedPSNodeInfo=spawn.spawnInformative(null, null, vMap, false, true, true, true);
        assertEquals("cpi",spawnedPSNodeInfo.getInheritedCustomerInfo("@CustomerProjectID").getCustomerProjectID(),"foo");
        
        spawn=new JDFSpawn(nPS);
        JDFNode spawnedPSNode=spawn.spawn(null,null,v,vMap,false,true,true,true);
        assertEquals("cpi",spawnedPSNode.getInheritedCustomerInfo("@CustomerProjectID").getCustomerProjectID(),"foo");
        // this one spawns the component rw
        v=new Vector();
        v.add(ElementName.COMPONENT);
        JDFExposedMedia xmSpawn=(JDFExposedMedia)spawnedPSNode.getMatchingResource(ElementName.EXPOSEDMEDIA, EnumProcessUsage.AnyOutput, null, 0);
        assertNotNull(xmSpawn);
        JDFMedia mediaSpawn=xmSpawn.getMedia();
        assertNotNull("The referenced Media gets spawned correctly",mediaSpawn);
                
        spawn=new JDFSpawn(n);

        JDFNode spawnedNodeAll=spawn.spawn("thisUrl","newURL",v,null,false,true,true,true); 
        String spawnID=spawnedNodeAll.getSpawnID(false);
        // merge and immediately respawn the same thing
        n=new JDFMerge(n).mergeJDF(spawnedNodeAll, null, EnumCleanUpMerge.RemoveAll, EnumAmountMerge.UpdateLink);
        assertTrue("spawnID gone",nRoot.toString().indexOf(spawnID)<0);
        spawn=new JDFSpawn(n);

        JDFNode spawnedNode=spawn.spawn("thisUrl","newURL",v,vMap,false,true,true,true); 
        spawnID=spawnedNode.getSpawnID(false);
        assertTrue("AncestorPool",spawnedNode.hasChildElement(ElementName.ANCESTORPOOL,null));

        // merge and immediately respawn the same thing
        n=new JDFMerge(n).mergeJDF(spawnedNode, null, EnumCleanUpMerge.RemoveAll, EnumAmountMerge.UpdateLink);
        assertTrue("spawnID gone",nRoot.toString().indexOf(spawnID)<0);
        spawn=new JDFSpawn(n);

        spawnedNode=spawn.spawn("thisUrl","newURL",v,vMap,false,true,true,true);
        assertTrue("AncestorPool after merge",spawnedNode.hasChildElement(ElementName.ANCESTORPOOL,null));

        map.put("SheetName","S1");
        spawn=new JDFSpawn(spawnedNode);
        JDFNode respawnedNode=spawn.spawn("reUrl","renewURL",v,vMap,false,true,true,true);
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
        spawn=new JDFSpawn(spawnedNode);
        JDFNode respawnedNode2 = spawn.spawn("reUrl","renewURL",v,vMap,false,true,true,true);
        xm=(JDFExposedMedia) respawnedNode2.getMatchingResource(ElementName.EXPOSEDMEDIA,EnumProcessUsage.AnyInput,null,0);
        comp=(JDFComponent) respawnedNode2.getMatchingResource(ElementName.COMPONENT,EnumProcessUsage.AnyOutput,null,0);
        vSpID=xm.getSpawnIDs(false);
        assertFalse("SpawnIDS",vSpID.isEmpty());
        xm=(JDFExposedMedia) xm.getPartition(map,null);       
        assertTrue("xm rw",xm.getLocked());
        comp=(JDFComponent) comp.getPartition(map,null);
        assertFalse("comp rw",comp.getLocked());
        String spawnID1=spawnedNode.getSpawnID(false);
        JDFNode testSpawnedNode=new JDFMerge(spawnedNode).mergeJDF(respawnedNode2, null, EnumCleanUpMerge.None, EnumAmountMerge.UpdateLink);
        assertTrue("AncestorPool after respawn merge",spawnedNode.hasChildElement(ElementName.ANCESTORPOOL,null));
        assertEquals("spawnID ok",spawnID1,testSpawnedNode.getSpawnID(false));
        spawn=new JDFSpawn(spawnedNode);
        respawnedNode2 = spawn.spawn("reUrl","renewURL",v,vMap,false,true,true,true);

        // now go backwards!
        new JDFMerge(nRoot).mergeJDF(spawnedNode, null, EnumCleanUpMerge.None, EnumAmountMerge.UpdateLink);
        new JDFMerge(nRoot).mergeJDF(respawnedNode, null, EnumCleanUpMerge.None, EnumAmountMerge.UpdateLink);
        new JDFMerge(nRoot).mergeJDF(respawnedNode2, null, EnumCleanUpMerge.None, EnumAmountMerge.UpdateLink);


        new JDFMerge(nRoot).mergeJDF(spawnedPSNode, null, EnumCleanUpMerge.None, EnumAmountMerge.UpdateLink);
        assertTrue("spawnIDs gone",nRoot.toString().indexOf("SpawnIDs")<0);


    }

    ///////////////////////////////////////////////////////////

    public void testSpawnPart()
    {
        for(int i=0;i<3;i++)
        {
            JDFDoc d=JDFResourceTest.creatXMDoc();
            JDFNode n=d.getJDFRoot();
            n.removeNodeInfo();
            JDFNodeInfo ni=n.getCreateNodeInfo();
            assertNotNull("ni",ni);
            JDFComment comment=n.appendComment();
            comment.setText("Comment 1");
//          ni.addPartition(JDFResource.EnumPartIDKey.SignatureName,"Sig1");
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
            xmRL.setAmount(40,i==1 ? map : null);
            xmRL.setUsage(EnumUsage.Output);
            xmRL.setAttribute("foo:bar", "a","www.foobar.com");

            final JDFSpawn spawn=new JDFSpawn(n); // fudge to test output counting
            JDFNode spawnedNode=spawn.spawn("thisUrl","newURL",v,vMap,false,true,true,true);
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
            xmRLspawn.setActualAmount(42,i!=0 ? map : null);
            assertEquals(xmRLspawn.getAttribute("foo:bar","www.foobar.com",null),"a");
            assertEquals("amount ok",xmRLspawn.getAmount(map), 40. ,0);
            assertEquals("act amount ok",xmRLspawn.getActualAmount(map),42. ,0.);

            //xmRLspawn.setAttribute("foo:bar","bb","www.foobar.com");
            if(i==2)
            {
                xmRLspawn.getAmountPool().getPartAmount(map, 0).removeAttribute(AttributeName.ACTUALAMOUNT);
                assertEquals(xmRLspawn.getAmountPool().getPartAmount(map, 0).getAttributeMap().size(), 0);
                xmRLspawn.setActualAmount(42,null);
            }
            JDFNode mergedNode=new JDFMerge(n).mergeJDF(spawnedNode, "merged", JDFNode.EnumCleanUpMerge.None, EnumAmountMerge.UpdateLink);
            xmRL=mergedNode.getMatchingLink(ElementName.EXPOSEDMEDIA,EnumProcessUsage.AnyOutput,0);  
            assertEquals("no spurious ni added",mergedNode.getResourceLinkPool().getPoolChildren("NodeInfoLink", null, null).size(), 1);
            assertEquals("Comment size",mergedNode.getChildElementVector(ElementName.COMMENT, null, null, true, 99,false).size(),3);
            assertEquals("merged amount ok",xmRL.getAmount(map),40.0,0.);
             assertEquals("did not overwrite rl attribute",xmRL.getAttribute("foo:bar","www.foobar.com",null),"a");
            assertTrue(xmRL.hasAttribute(AttributeName.RREF));
            
            JDFExposedMedia xm=(JDFExposedMedia) n.getMatchingResource(ElementName.EXPOSEDMEDIA,EnumProcessUsage.AnyOutput,null,0);
            xm=(JDFExposedMedia) xm.getPartition(map,null);
            if(i<2)
            {
                assertTrue("merged act amount ok",xmRL.getActualAmount(map)==42);
                assertTrue("merged res amount ok",xm.getAmount()==42);
                assertTrue("merged res amountproduced ok",xm.getAmountProduced()==42);
            }

            // test whether anythin modified and tracked in a resource audit got correctly merged
            JDFResourceAudit raMerge=(JDFResourceAudit) n.getAuditPool().getAudit(0, EnumAuditType.ResourceAudit, null,null);
            assertNotNull("res audit merged",raMerge);
            JDFResource rOld=raMerge.getOldLink().getTarget();
            assertNotNull("old res  merged",rOld);
            assertEquals("old res ID",rOld.getID(),clonedResID);
            JDFResource rNew=raMerge.getNewLink().getTarget();
            assertNotNull("new res  merged",rNew);
        }
    }

    //////////////////////////////////////////////////////////////

    ///////////////////////////////////////////////////////////

    public void testSpawnAddPart()
    {
        for(int i=0;i<2;i++)
        {
            JDFDoc d=JDFResourceTest.creatXMDoc();
            JDFNode n=d.getJDFRoot();
            if(i==1)
            {
                // test spawning of referenced resources in parent nodes
                n.setType(EnumType.ProcessGroup);
                JDFNode n2=n.addJDFNode(EnumType.ConventionalPrinting);
                n2.moveElement(n.getResourceLinkPool(), null);
                n=n2;
            }
            n.removeNodeInfo();
            JDFNodeInfo ni=n.getCreateNodeInfo();
            assertNotNull("ni",ni);
            JDFComment comment=n.appendComment();
            comment.setText("Comment 1");
//          ni.addPartition(JDFResource.EnumPartIDKey.SignatureName,"Sig1");
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
            xmRL.setAmount(40,i==0 ? map : null);
            xmRL.setUsage(EnumUsage.Output);

            JDFExposedMedia xmMain=(JDFExposedMedia) n.getMatchingResource(ElementName.EXPOSEDMEDIA,EnumProcessUsage.AnyOutput,null,0);
            JDFMedia media=xmMain.getMedia();
            media.makeRootResource("mediaID", n, true);

            final JDFSpawn spawn=new JDFSpawn(n); // fudge to test output counting
            JDFNode spawnedNode=spawn.spawn("thisUrl","newURL",v,vMap,false,true,true,true);
            assertTrue("no spawnStatus",spawnedNode.toString().indexOf(AttributeName.SPAWNSTATUS)<0);

            JDFResourceLink xmRLspawn=spawnedNode.getMatchingLink(ElementName.EXPOSEDMEDIA,EnumProcessUsage.AnyOutput,0);
            JDFExposedMedia xm=(JDFExposedMedia)xmRLspawn.getTarget();
            assertNotNull(xm);
            JDFExposedMedia xmRoot=(JDFExposedMedia) xm.getResourceRoot();
            assertNotSame(xm, xmRoot);
            
            JDFMedia spawnMedia=xm.getMedia();
            assertNotNull(spawnMedia);
            
            assertEquals(xmRoot.getSpawnIDs(false).size(),0);
            final VString spawnIDs = xm.getSpawnIDs(false);
            final String spawnID = spawnedNode.getSpawnID(false);
            assertEquals(spawnIDs.stringAt(0),spawnID);
            
            JDFAttributeMap mapNew1=new JDFAttributeMap();
            mapNew1.put("SignatureName","Sig1");
            mapNew1.put("SheetName","S1_OK");
            JDFExposedMedia xmNew1=(JDFExposedMedia) xmRoot.getCreatePartition(mapNew1,null);
            xmNew1.appendSpawnIDs(spawnID);
            xmNew1.setDescriptiveName("good new parallel");
            
            JDFAttributeMap mapNew2=new JDFAttributeMap();
            mapNew2.put("SignatureName","Sig1");
            mapNew2.put("SheetName","S1_Bad");
            xmRoot.getCreatePartition(mapNew1,null);
                      
            n=d.getJDFRoot();
            // test spawning of referenced resources in parent nodes
            if(i==1)
                n=(JDFNode)n.getElement("JDF");
            JDFNode mergedNode=new JDFMerge(n).mergeJDF(spawnedNode, "merged", JDFNode.EnumCleanUpMerge.None, EnumAmountMerge.UpdateLink);
            xmRL=mergedNode.getMatchingLink(ElementName.EXPOSEDMEDIA,EnumProcessUsage.AnyOutput,0);  
 
            JDFExposedMedia xmMRoot=(JDFExposedMedia) mergedNode.getMatchingResource(ElementName.EXPOSEDMEDIA,EnumProcessUsage.AnyOutput,null,0);
            JDFExposedMedia xmM1=(JDFExposedMedia) xmMRoot.getPartition(mapNew1,null);
            assertEquals("Merged xm1",xmNew1.getDescriptiveName(), xmM1.getDescriptiveName());
            
            JDFExposedMedia xmM2=(JDFExposedMedia) xmMRoot.getPartition(mapNew2,null);
            assertNull("did not merge xm2",xmM2);

        }
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
                final JDFSpawn spawn=new JDFSpawn(spawnNode);
                JDFNode jdfSpawned = spawn.spawn(sm_dirTestData+fileNameIn+".jdf",null,null,null,false,true,true,true);

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
            final JDFSpawn spawn=new JDFSpawn(spawnNode);
            JDFNode spawnedNode = spawn.spawn(sm_dirTestData + fileNameIn + ".jdf",null,null,null,false,true,true,true);
            v2.add(spawnedNode);
            nEvent+=spawnedNode.getChildrenByTagName("Notification","",new JDFAttributeMap(),false,false,0).size();
            nComment+=spawnedNode.numChildNodes(Node.COMMENT_NODE);
        }
        for(int i = 0; i < v2.size(); i++)
        {
            JDFNode nodeToMerge = (JDFNode)v2.elementAt(i);
            new JDFMerge(root).mergeJDF(nodeToMerge, JDFConstants.EMPTYSTRING, JDFNode.EnumCleanUpMerge.None, JDFResource.EnumAmountMerge.None);
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
    public void testSpawnNI13()
    {
        JDFDoc d=new JDFDoc("JDF");
        JDFNode n=d.getJDFRoot();
        assertEquals("null cid",n.getInheritedCustomerInfo("@CustomerOrderID"),null);
        n.setType("ProcessGroup",false);
        VString v=new VString();
        v.add("Interpreting");
        v.add("Rendering");

        for(int i=0;i<2;i++)
        {
            JDFNode n2=n.addCombined(v);
            JDFNodeInfo ni=n2.appendNodeInfo();
            final JDFSpawn spawn=new JDFSpawn(n2);
            VJDFAttributeMap vSpawnParts=null;
            if(i==1)
            {
                ni.addPartition(EnumPartIDKey.Run, "r1");
                vSpawnParts=new VJDFAttributeMap();
                vSpawnParts.add(new JDFAttributeMap("Run","r1"));
            }
            JDFNode spawnedNode=spawn.spawn("thisFile","spawnFile",null,vSpawnParts,true,true,true,true);
            JDFNodeInfo niSpawn= spawnedNode.getInheritedNodeInfo(null);
            assertNotNull("ni",niSpawn);
            assertEquals(spawnedNode.getResourcePool().numChildElements(ElementName.NODEINFO, null), 1);
        }
    }
    
    /**
     * test customerinfo and nodeinfo related stuff
     * including high level access to information in the AncestorPool
     *
     */
    public void testUnSpawn()
    {
        JDFDoc d=new JDFDoc("JDF");
        JDFNode n=d.getJDFRoot();
        assertEquals("null cid",n.getInheritedCustomerInfo("@CustomerOrderID"),null);
        n.setType("ProcessGroup",false);
        VString v=new VString();
        v.add("Interpreting");
        v.add("Rendering");

        for(int i=0;i<2;i++) // 0 = now part, 1==part
        {
            JDFNode n2=n.addCombined(v);
            final JDFSpawn spawn=new JDFSpawn(n2);
            VJDFAttributeMap vMap=new VJDFAttributeMap();
            vMap.add(new JDFAttributeMap("Side","Front"));
            
            JDFNode spawnedNode=spawn.spawn("thisFile","spawnFile",null,i==0 ? null : vMap,true,true,true,true);
            String spawnID=spawnedNode.getSpawnID(false);
            assertNotSame(spawnID,"");
            final JDFSpawn spawn2=new JDFSpawn(n2);
            spawn2.unSpawn(spawnID);
            final String toString = n.toString();
            assertTrue(toString.indexOf(spawnID)<0);
            assertTrue(toString.indexOf("Spawn")<0);
            assertTrue(toString.indexOf("Merge")<0);
        }
        
     }    
    /**
     * test customerinfo and nodeinfo related stuff
     * including high level access to information in the AncestorPool
     *
     */
    public void testSpawnCINI()
    {
        for(int i=0;i<2;i++)
        {
            for(int j=0;j<2;j++)
            {
                final VJDFAttributeMap partmapvector = new VJDFAttributeMap();
                if(j==1)
                {
                    JDFAttributeMap partmap=new JDFAttributeMap();
                    partmap.put("SheetName", "S1");
                    partmapvector.add(partmap);
                }

                JDFDoc d=new JDFDoc("JDF");
                JDFNode n=d.getJDFRoot();
                assertEquals("null cid",n.getInheritedCustomerInfo("@CustomerOrderID"),null);
                n.setType("ProcessGroup",false);
                final EnumVersion version = i==0 ? EnumVersion.Version_1_2 : EnumVersion.Version_1_2;
                n.setVersion(version);
                JDFCustomerInfo ci=n.appendCustomerInfo();
                JDFContact contact=ci.appendContact();
                contact=(JDFContact) contact.makeRootResource("ID_Contact1", n, true);
                ci.setCustomerOrderID("cid");
                assertEquals("cid",n.getInheritedCustomerInfo("@CustomerOrderID").getCustomerOrderID(),"cid");

                VString v=new VString();
                v.add("Interpreting");
                v.add("Rendering");

                JDFNode n2=n.addCombined(v);
                JDFNodeInfo ni=n2.appendNodeInfo();
                final JDFSpawn spawn=new JDFSpawn(n2);
                JDFNode spawnedNode=spawn.spawn("thisFile","spawnFile",null,partmapvector,true,true,true,true);
                ci= spawnedNode.getInheritedCustomerInfo(null);
                assertNotNull("ci",ci);
                JDFContact refContact=ci.getContact(0);
                assertNotNull("ref to contact",refContact);
                JDFNodeInfo niSpawn= spawnedNode.getInheritedNodeInfo(null);
                assertNotNull("ni",niSpawn);

                assertNotNull("ci",ci);
                assertNotNull("spawned",(j==1 ? n2 : n).getAuditPool().getAudit(0, JDFAudit.EnumAuditType.Spawned, null,null));
                String spawnID = spawnedNode.getSpawnID(false);
                assertEquals("cid",spawnedNode.getInheritedCustomerInfo("@CustomerOrderID").getCustomerOrderID(),"cid");
                JDFAncestor anc=spawnedNode.getAncestorPool().getAncestor(0);
                assertNull("no xsi:type", anc.getAttribute("type",AttributeName.XSIURI,null));

                n=new JDFSpawn(n).unSpawn(spawnID);
                assertNotNull("n2",n);
                assertNull("spawned",d.getJDFRoot().getAuditPool().getAudit(0, JDFAudit.EnumAuditType.Spawned, null,null));

                n.removeCustomerInfo();
                n.setVersion(version);
                ci=n.appendCustomerInfo();
                contact=ci.appendContact();
                contact=(JDFContact) contact.makeRootResource("ID_Contact1", n, true);

                final JDFSpawn _spawn=new JDFSpawn(n2);
                spawnedNode=_spawn.spawn("thisFile","spawnFile",null,partmapvector,true,true,true,true);
                spawnID = spawnedNode.getSpawnID(false);

                ci=spawnedNode.getInheritedCustomerInfo(null);
                assertNotNull("ci",ci);
                refContact=ci.getContact(0);
                assertNotNull("ref to contact: "+version.getName(),refContact);

                n2=new JDFSpawn(d.getJDFRoot()).unSpawn(spawnID);
                assertNotNull("n2",n2);
                assertTrue("n2 no spawnID",n2.toString().indexOf(spawnID)<0);
            }
        }
    }    

    ///////////////////////////////////////////////////////////////////////////

    public void testBigSpawn()   throws Exception    
    {        
        final String strJDFName = "000023_Test_PR3.0.jdf";
//      final String strJDFName = "biginline.jdf";
        String strJDFPath = sm_dirTestData +  strJDFName;
        JDFParser parser = new JDFParser ();
        JDFDoc jdfDoc = parser.parseFile(strJDFPath);
        for(int i=1;i<10;i++)
        {
            System.out.println("i="+i);
            VJDFAttributeMap vamParts = new VJDFAttributeMap ();
            JDFAttributeMap amParts0 = new JDFAttributeMap ();
            amParts0.put ("Side", "Front");
            amParts0.put ("SignatureName", "Sig00"+i);
            amParts0.put ("SheetName", "FB 00"+i);
            vamParts.add (amParts0);
            JDFAttributeMap amParts1 = new JDFAttributeMap ();
            amParts1.put ("Side", "Back");
            amParts1.put ("SignatureName", "Sig00"+i);
            amParts1.put ("SheetName", "FB 00"+i);
            vamParts.add (amParts1);
            VString vsRWResourceIDs = new VString ();
            vsRWResourceIDs.add ("Link84847227_000309");
            vsRWResourceIDs.add ("r85326439_027691");
            final JDFNode nodeRoot = jdfDoc.getJDFRoot ();
            JDFNode nodeProc = nodeRoot.getJobPart ("IPD0.I", null);
            final JDFSpawn spawn=new JDFSpawn(nodeProc);
//          JDFNode nodeProc = nodeRoot;
            JDFNode nodeSubJDF = spawn.spawn(strJDFPath,null,vsRWResourceIDs,vamParts,true,true,true,true);
            assertNotNull(nodeSubJDF);

            if (nodeSubJDF != null) 
            {
                nodeSubJDF.getOwnerDocument_KElement().write2File(sm_dirTestDataTemp+"bigSub"+i+".jdf", 2, true);
                if(i==9)
                    jdfDoc.write2File(sm_dirTestDataTemp+"bigMainPost.jdf", 2, true);     
            }
        }
    }

    ///////////////////////////////////////////////////////////////////////


    public void testBigMerge()    
    throws Exception    
    {       
        testBigSpawn();
        JDFParser parser = new JDFParser ();
        JDFDoc jdfDoc = parser.parseFile(sm_dirTestDataTemp+"bigMainPost.jdf");
        for(int i=9;i>0;i--)
        {
            JDFParser parser2 = new JDFParser ();
            JDFDoc jdfDocSub = parser2.parseFile(sm_dirTestDataTemp+"bigSub"+i+".jdf");
            JDFNode nodeMain=jdfDoc.getJDFRoot();      
            JDFNode nodeSub=jdfDocSub.getJDFRoot();
            JDFNode overWrite=new JDFMerge(nodeMain).mergeJDF(nodeSub, null, EnumCleanUpMerge.RemoveRRefs, EnumAmountMerge.UpdateLink);
            JDFAuditPool ap=overWrite.getAuditPool();
            final JDFAudit audit = ap.getAudit(0, EnumAuditType.Merged, null,null);
            assertNotNull(audit);
            assertFalse(audit.hasAttribute(AttributeName.SPAWNID));
        }
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
        final Collection vSpawned = nodeProc.checkSpawnedResources (vsRWResourceIDs, vamParts);
        assertNull(vSpawned);
    }


    public void testBookintent()
    {
        String fileNameIn            = "bookintent.jdf";
        String fileNameOut           = "spawned.jdf";
        String spawnNodeID           = "n0016";

        Vector vRWResources = new Vector();
        vRWResources.addElement("Component");
        vRWResources.addElement("RunList");
        VJDFAttributeMap vSpawnParts = new VJDFAttributeMap();

        String strSpawnID = spawn(fileNameIn, fileNameOut, spawnNodeID, vRWResources, vSpawnParts);
        unSpawn(fileNameIn, strSpawnID);   // "Sp7cb:-7fff"
    }


    private String spawn (  String strXMLFile,
            String strSpawnedFile,
            String strElementID,
            Vector vRWResources,
            VJDFAttributeMap vSpawnParts )
    {
        String strSpawnID = JDFConstants.EMPTYSTRING;

        // parse input file
        JDFParser p = new JDFParser();
        JDFDoc jdfDocIn = p.parseFile(sm_dirTestData+strXMLFile);
        assertTrue("Parse of file "+sm_dirTestData+strXMLFile+" failed", jdfDocIn != null);

        if (jdfDocIn != null)
        {
            // prepare the spawn process
            JDFNode rootIn = (JDFNode) jdfDocIn.getRoot();

            JDFNode spawnNode = null;
            if (strElementID.equals(""))
            {
                spawnNode = rootIn;
            }
            else
            {
                spawnNode = (JDFNode) rootIn.getTarget(strElementID, AttributeName.ID);
            }
            assertTrue("No such ID " + strElementID, spawnNode != null);

            if (spawnNode != null)
            {
                final JDFSpawn _spawn=new JDFSpawn(spawnNode);
                JDFNode jdfSpawned = _spawn.spawnInformative(sm_dirTestData+strXMLFile, null, null, false, true, true, true);
                String spawnID=jdfSpawned.getSpawnID(false);

                String big=jdfDocIn.write2String(0);
                assertTrue("no spawnID in main",big.indexOf(spawnID)<0);
                final JDFSpawn spawn1=new JDFSpawn(spawnNode);

                jdfSpawned = spawn1.spawn(sm_dirTestData+strXMLFile,null,vRWResources,vSpawnParts,false,true,true,true);
                spawnID=jdfSpawned.getSpawnID(false);
                big=jdfDocIn.write2String(0);
                assertTrue("spawnID in main",big.indexOf(spawnID)>=0);

                // neu gespawntes File rausschreiben
                JDFNode rootOut = jdfSpawned;
                XMLDoc docOut = rootOut.getOwnerDocument_KElement();
                docOut.write2File(sm_dirTestDataTemp + strSpawnedFile, 0, true);

                // verändertes Ausgangsfile rausschreiben
                rootIn.eraseEmptyNodes(true);
                String strXMLFileModified = "_" + strXMLFile;
                jdfDocIn.write2File(sm_dirTestDataTemp + strXMLFileModified, 0, true);

                // Vergleich, ob alles richtig gelaufen ist
                //        compareXMLFiles (strXMLFileModified,    strXMLFileModifiedMaster);
                //        compareXMLFiles (strSpawnedFile+".jdf", strSpawnedFileMaster);

                strSpawnID = rootOut.getSpawnID(false);
            }
        }

        return strSpawnID;
    }


    //////////////////////////////////////////////////////////

    public void testPartitionedSpawn()
    {
        String fileNameIn            = "partitionedSource.jdf";
        String fileNameOut           = "partitionedSpawned.jdf";
        String spawnNodeID           = "n0016";

        Vector vRWResources = new Vector();
        vRWResources.addElement("Component");
        vRWResources.addElement("RunList");

        JDFAttributeMap spawnPart = new JDFAttributeMap();
        VJDFAttributeMap vSpawnParts = new VJDFAttributeMap();
        //spawnPart.put("Class", "Intent");
        vSpawnParts.addElement(spawnPart);

        String strSpawnID = spawn(fileNameIn, fileNameOut, spawnNodeID, vRWResources, vSpawnParts);
        unSpawn(fileNameIn, strSpawnID);
    }


    ///////////////////////////////////////////////////////////////////////////


    public void testRef()
    {
        String fileNameIn            = "ref.jdf";
        String fileNameOut           = "spawn.jdf";
        String spawnNodeID           = "n0027";

        Vector vRWResources = new Vector();
        vRWResources.addElement("Media");
        vRWResources.addElement("ExposedMedia");
        VJDFAttributeMap vSpawnParts = new VJDFAttributeMap();

        String strSpawnID = spawn(fileNameIn, fileNameOut, spawnNodeID, vRWResources, vSpawnParts);
        unSpawn(fileNameIn, strSpawnID);  
    }


    //////////////////////////////////////////////////////////

    public void testMergeJDF()
    {
        // job.jdf subjdf.jdf -o merged.jdf
        String m_xmlFile1 = "_bookintent.jdf";
        String m_xmlFile2 = "spawned.jdf";
        String m_outFile = "merged.jdf";
        JDFDoc m_jdfDoc;
        JDFDoc m_jdfDoc2;

        JDFParser p = new JDFParser();
        m_jdfDoc = p.parseFile(sm_dirTestDataTemp + m_xmlFile1);

        assertNotNull(sm_dirTestDataTemp + m_xmlFile1 + ": Parse Error\n"
                + "MergeJDF: JDF merger simulation;\n"
                + "Arguments: 1=parent input JDF, 2=child input JDF;\n" + "-o: output JDF;\n"
                + "-d: delete completed tasks from the output JDF\n", m_jdfDoc);

        JDFParser p2 = new JDFParser();
        m_jdfDoc2 = p2.parseFile(sm_dirTestDataTemp + m_xmlFile2);

        assertTrue(sm_dirTestDataTemp + m_xmlFile2 + ": Parse Error", m_jdfDoc2 != null);
        if (m_jdfDoc2 == null)
            return; // soothe findbugs ;)

        JDFNode rootMain =  m_jdfDoc.getJDFRoot();
        JDFNode rootSpawn =  m_jdfDoc2.getJDFRoot();
        rootSpawn.setDescriptiveName("fixup");
        String root2ID = rootSpawn.getID();
        JDFComment c2 = rootSpawn.appendComment();
        c2.setText("Comment 1");

        new JDFMerge(rootMain).mergeJDF(rootSpawn, sm_dirTestDataTemp + m_xmlFile2, JDFNode.EnumCleanUpMerge.None, JDFResource.EnumAmountMerge.None);
        JDFNode nodemerged = rootMain.getChildJDFNode(root2ID, false);
        assertTrue("MergeJDF fixup", nodemerged.getDescriptiveName().equals("fixup"));

        m_jdfDoc.write2File(sm_dirTestDataTemp + m_outFile, 2, true);

        assertTrue("MergeJDF ok", true);
    }

    //////////////////////////////////////////////////////////

    public void testCleanupMerge()
    {
        List l=EnumCleanUpMerge.getEnumList();
        for(int i=0;i<l.size();i++)
        {
            JDFDoc doc=new JDFDoc("JDF");
            JDFNode node=doc.getJDFRoot();
            node.setType(EnumType.ProcessGroup);
            JDFNode n2=node.addJDFNode(EnumType.AdhesiveBinding);
            JDFResource r=n2.addResource(ElementName.ADHESIVEBINDINGPARAMS, null, EnumUsage.Input, null, node, null, null);
            JDFSpawn sp=new JDFSpawn(n2);
            JDFNode spn=sp.spawn();
            final JDFSpawned auditSpawn = (JDFSpawned)node.getAuditPool().getAudit(0, EnumAuditType.Spawned, null, null);
            assertNotNull(auditSpawn);
            assertTrue(auditSpawn.getrRefsROCopied().contains(r.getID()));
            EnumCleanUpMerge cm=(EnumCleanUpMerge)l.get(i);
            new JDFMerge(node).mergeJDF(spn, null, cm, JDFResource.EnumAmountMerge.None);
            JDFSpawned auditSpawn2 = (JDFSpawned)node.getAuditPool().getAudit(0, EnumAuditType.Spawned, null, null);
            JDFMerged mergeSpawn2 = (JDFMerged)node.getAuditPool().getAudit(0, EnumAuditType.Merged, null, null);
            if(cm.equals(EnumCleanUpMerge.None))
            {
                assertNotNull(auditSpawn2);
                assertTrue(auditSpawn2.getrRefsROCopied().contains(r.getID()));
                assertEquals(auditSpawn, auditSpawn2);
                assertNotNull(mergeSpawn2);
                assertEquals(auditSpawn2.getrRefsRWCopied(), mergeSpawn2.getrRefsOverwritten());
            }
            else if(cm.equals(EnumCleanUpMerge.RemoveRRefs))
            {
                assertNotNull(auditSpawn2);
                assertTrue(auditSpawn2.getrRefsROCopied().isEmpty());
                assertEquals(auditSpawn, auditSpawn2);
                assertNotNull(mergeSpawn2);
                assertEquals(auditSpawn2.getrRefsRWCopied(), mergeSpawn2.getrRefsOverwritten());
            }
            else if(cm.equals(EnumCleanUpMerge.RemoveAll))
            {
                assertNull(auditSpawn2);
                assertNull(mergeSpawn2);
            }
        }
    }


    // project folder must look to test.jdf
    public void testMergeJDF2()
    {
        JDFParser p         = new JDFParser();
        JDFDoc mydoc        = p.parseFile(sm_dirTestData+"testMergeJDF2.jdf");
        JDFNode root        = (JDFNode) mydoc.getRoot();
        Vector rwResources  = new Vector();

        JDFAttributeMap myMap = new JDFAttributeMap();

        myMap.put("Type", "Imposition");
        myMap.put("Status", "Waiting");


        VElement elemVec = root.getChildrenByTagName("JDF", JDFConstants.EMPTYSTRING, myMap ,false ,true , 0); 
        JDFNode spawnnode = (JDFNode) elemVec.elementAt(0);

        rwResources.add("Output");
        final JDFSpawn spawn=new JDFSpawn(spawnnode);

        spawnnode = spawn.spawn(sm_dirTestData+"testMergeJDF2.jdf",sm_dirTestDataTemp+"myTest_spawned.jdf",rwResources,null,false,false,false,false);

        assertTrue(mydoc.write2File(sm_dirTestDataTemp+"testMergeJDF2_spawned.jdf", 0, true));
        new JDFMerge(root).mergeJDF(spawnnode, JDFConstants.EMPTYSTRING, JDFNode.EnumCleanUpMerge.None, JDFResource.EnumAmountMerge.None);

        JDFResource myres = (JDFResource)root.getTarget("Res_Impos_Out_Run_1_3011", AttributeName.ID);
        assertTrue("Merged Resource contains SpawnID", 
                myres.getAttribute("SpawnIDs", null, JDFConstants.EMPTYSTRING).
                equals(JDFConstants.EMPTYSTRING)); 

        assertTrue(mydoc.write2File(sm_dirTestDataTemp+"testMergeJDF2_merged.jdf", 0, true));
    }


    public void testMergeJDF3()
    {
        // job.jdf subjdf.jdf -o merged.jdf
        String m_xmlFile1 = "km111.jdf";
        String m_xmlFile2 = "Link76645060_000155km111Qua0.NSp76664048_000633_28_out.jdf";
        String m_outFile = "km111_merged.xml";
        JDFDoc m_jdfDoc;
        JDFDoc m_jdfDoc2;

        JDFParser p = new JDFParser();
        m_jdfDoc = p.parseFile(m_xmlFile1);

        JDFParser p2 = new JDFParser();
        m_jdfDoc2 = p2.parseFile(m_xmlFile2);

        if (m_jdfDoc2 == null)
            return; // soothe findbugs ;)

        JDFNode root = (JDFNode) m_jdfDoc.getRoot();
        JDFNode root2 = (JDFNode) m_jdfDoc2.getRoot();
        if (root == null)
            return; // soothe findbugs ;)

        new JDFMerge(root).mergeJDF(root2, m_xmlFile2, JDFNode.EnumCleanUpMerge.None, JDFResource.EnumAmountMerge.None);

        m_jdfDoc.write2File(sm_dirTestDataTemp + m_outFile, 2, true);
    }



}