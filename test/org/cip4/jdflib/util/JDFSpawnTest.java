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
import org.cip4.jdflib.auto.JDFAutoNotification.EnumClass;
import org.cip4.jdflib.auto.JDFAutoPart.EnumSide;
import org.cip4.jdflib.core.AttributeName;
import org.cip4.jdflib.core.ElementName;
import org.cip4.jdflib.core.JDFAudit;
import org.cip4.jdflib.core.JDFComment;
import org.cip4.jdflib.core.JDFConstants;
import org.cip4.jdflib.core.JDFCustomerInfo;
import org.cip4.jdflib.core.JDFDoc;
import org.cip4.jdflib.core.JDFElement;
import org.cip4.jdflib.core.JDFException;
import org.cip4.jdflib.core.JDFNodeInfo;
import org.cip4.jdflib.core.JDFParser;
import org.cip4.jdflib.core.JDFResourceLink;
import org.cip4.jdflib.core.VElement;
import org.cip4.jdflib.core.VString;
import org.cip4.jdflib.core.XMLDoc;
import org.cip4.jdflib.core.JDFAudit.EnumAuditType;
import org.cip4.jdflib.core.JDFAudit.EnumSeverity;
import org.cip4.jdflib.core.JDFElement.EnumNodeStatus;
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
import org.cip4.jdflib.resource.JDFBufferParams;
import org.cip4.jdflib.resource.JDFMerged;
import org.cip4.jdflib.resource.JDFNotification;
import org.cip4.jdflib.resource.JDFProcessRun;
import org.cip4.jdflib.resource.JDFResource;
import org.cip4.jdflib.resource.JDFResourceAudit;
import org.cip4.jdflib.resource.JDFResourceTest;
import org.cip4.jdflib.resource.JDFResource.EnumAmountMerge;
import org.cip4.jdflib.resource.JDFResource.EnumPartIDKey;
import org.cip4.jdflib.resource.JDFResource.EnumPartUsage;
import org.cip4.jdflib.resource.JDFResource.EnumResStatus;
import org.cip4.jdflib.resource.JDFResource.EnumSpawnStatus;
import org.cip4.jdflib.resource.process.JDFApprovalSuccess;
import org.cip4.jdflib.resource.process.JDFComponent;
import org.cip4.jdflib.resource.process.JDFContact;
import org.cip4.jdflib.resource.process.JDFConventionalPrintingParams;
import org.cip4.jdflib.resource.process.JDFEmployee;
import org.cip4.jdflib.resource.process.JDFExposedMedia;
import org.cip4.jdflib.resource.process.JDFLayout;
import org.cip4.jdflib.resource.process.JDFMedia;
import org.cip4.jdflib.resource.process.JDFRunList;
import org.cip4.jdflib.resource.process.postpress.JDFFoldingParams;
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
            assertNull(root.getMultipleIDs("ID"));


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
            for(int j=0;j<2;j++)
            {
                JDFDoc d=new JDFDoc("JDF");
                JDFNode root=d.getJDFRoot();
                root.setType(EnumType.ProcessGroup);
                JDFNode cp=root.addJDFNode(EnumType.ConventionalPrinting);
                JDFComponent comp=(JDFComponent) cp.addResource("Component", null, EnumUsage.Output, null, null, null, null);
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
                JDFNodeInfo ni2=(JDFNodeInfo)ni.getCreatePartition(cMap,new VString("SignatureName SheetName Side"," "));
                JDFEmployee emp=ni2.appendEmployee();
                emp.makeRootResource(null, null, true);
                assertNull(root.getResourcePool());
                if(j==1)
                {
                    root.moveElement(cp.getResourcePool(), null);
                    assertNotNull(root.getResourcePool());
                }

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

                assertNotNull("The Employee that was referenced by a partition was correctly spawned",spawned.getResourcePool().getElement("Employee"));
            }
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

    /**
     * test wierd sequences of spawning and merging with the same res being spawned both rw and ro
     * 
     */
    public void testSpawnPartMultiRORW()
    {
        for(int i=0;i<2;i++) // partitioned or not
        {
            VJDFAttributeMap vMap=new VJDFAttributeMap();
            JDFAttributeMap map=new JDFAttributeMap();
            map.put("SignatureName","Sig1");
            vMap.add(map);
            if(i==1)
                vMap=null; // unpartitioned
            for(int j=0;j<4;j++) // rw or ro first
            {
                for(int k=0;k<2;k++) // copy local or copy from high level
                {
                    JDFDoc dRoot=new JDFDoc("JDF");
                    JDFNode nRoot=dRoot.getJDFRoot();

                    nRoot.setType("Product",true);

                    JDFDoc d=JDFResourceTest.creatXMDoc();
                    JDFNode n=(JDFNode) nRoot.copyElement(d.getJDFRoot(),null);
                    if(k>0)
                        nRoot.moveElement(n.getResourcePool(), null);

                    JDFExposedMedia xm=(JDFExposedMedia) n.getMatchingResource(ElementName.EXPOSEDMEDIA,EnumProcessUsage.AnyInput,null,0);
                    JDFExposedMedia xmPart=(JDFExposedMedia) xm.getPartition(map, null);
                    JDFMedia media=xm.getMedia();
                    media.makeRootResource("mediaID", n, true);

                    Vector vRWRes=new Vector();
                    if(j<2)
                        vRWRes.add(ElementName.EXPOSEDMEDIA);

                    JDFSpawn spawn=new JDFSpawn(n);
                    JDFNode spawnedNodeXMRW=spawn.spawn(null,null,vRWRes,vMap,false,true,true,true);

                    // this one spawns the component rw
                    vRWRes.clear();
                    JDFExposedMedia xmSpawn=(JDFExposedMedia)spawnedNodeXMRW.getMatchingResource(ElementName.EXPOSEDMEDIA, EnumProcessUsage.AnyInput, null, 0);
                    assertNotNull(xmSpawn);
                    assertEquals(xmPart.getSpawnStatus(), j<2 ? EnumSpawnStatus.SpawnedRW : EnumSpawnStatus.SpawnedRO);
                    if(j>=2) // swap sequence or rw / ro
                        vRWRes.add(ElementName.EXPOSEDMEDIA);

                    spawn=new JDFSpawn(n);

                    JDFNode spawnedNodeXMRO=spawn.spawn("thisUrl","newURL",vRWRes,vMap,false,true,true,true); 
                    // merge and immediately respawn the same thing
                    JDFNode s1=j%2==0?spawnedNodeXMRO:spawnedNodeXMRW;
                    String spawnIDRO=s1.getSpawnID(false);
                    JDFNode s2=j%2==1?spawnedNodeXMRO:spawnedNodeXMRW;
                    String spawnIDRW=s2.getSpawnID(false);
                    //n=new JDFMerge(nRoot).mergeJDF(s1, null, EnumCleanUpMerge.None, EnumAmountMerge.UpdateLink);
                    n=new JDFMerge(nRoot).mergeJDF(s1, null, EnumCleanUpMerge.RemoveAll, EnumAmountMerge.UpdateLink);
                    assertTrue("spawnID RO gone",nRoot.toString().indexOf(spawnIDRO)<0);
                    assertTrue("spawnID RW not gone",nRoot.toString().indexOf(spawnIDRW)>=0);
                    xm=(JDFExposedMedia) n.getMatchingResource(ElementName.EXPOSEDMEDIA,EnumProcessUsage.AnyInput,null,0);
                    xmPart=(JDFExposedMedia) xm.getPartition(map, null);
                    assertEquals(i+" "+j,xmPart.getSpawnStatus(), j==0 || j==3 ? EnumSpawnStatus.SpawnedRW : EnumSpawnStatus.SpawnedRO);

                    // merge and immediately respawn the same thing
                    n=new JDFMerge(nRoot).mergeJDF(s2, null, EnumCleanUpMerge.RemoveAll, EnumAmountMerge.UpdateLink);
//                  n=new JDFMerge(nRoot).mergeJDF(s2, null, EnumCleanUpMerge.None, EnumAmountMerge.UpdateLink);
                    assertTrue("spawnID gone",nRoot.toString().indexOf(spawnIDRW)<0);
                    assertTrue("spawnIDs gone",nRoot.toString().indexOf("SpawnIDs")<0);
                    xm=(JDFExposedMedia) n.getMatchingResource(ElementName.EXPOSEDMEDIA,EnumProcessUsage.AnyInput,null,0);
                    xmPart=(JDFExposedMedia) xm.getPartition(map, null);
                    assertEquals(xmPart.getSpawnStatus(),  EnumSpawnStatus.NotSpawned);
                }
            }
        }
    }

    /**
     * test whether getpartition works for when inconsistently called
     */
    public void testSpawnInconsistentPart()
    {   
        JDFDoc doc=new JDFDoc("JDF");
        JDFNode n=doc.getJDFRoot();
        n.setType(EnumType.Folding);
        JDFFoldingParams fp=(JDFFoldingParams) n.addResource(ElementName.FOLDINGPARAMS, null, EnumUsage.Input, null, null, null, null);
        JDFAttributeMap m=new JDFAttributeMap("SignatureName","Sig1");
        JDFNodeInfo ni=(JDFNodeInfo) n.addResource(ElementName.NODEINFO, null, EnumUsage.Input, null, null, null, null);
        m.put("SheetName","Sheet1");
        JDFResource rSheet=fp.getCreatePartition(m, new VString("SignatureName SheetName"," "));
        JDFResourceLink rl=n.getLink(fp, null);
        rl.setPartMap(m);
        m.put("BlockName","Block1");
        JDFResource r=fp.getCreatePartition(m, new VString("SignatureName SheetName BlockName"," "));
        m.put("BlockName","Block2");
        r=fp.getCreatePartition(m, new VString("SignatureName SheetName BlockName"," "));
        JDFAttributeMap m2=new JDFAttributeMap("SignatureName","Sig1");
        m2.put("SheetName","Sheet1");
        m2.put("Side","Front");
        ni.getCreatePartition(m2, new VString("SignatureName SheetName Side"," "));
        r=fp.getPartition(m2, EnumPartUsage.Implicit);
        assertEquals(r,rSheet);
        r=fp.getPartition(m2, EnumPartUsage.Explicit);
        assertNull(r);
        final JDFSpawn spawn=new JDFSpawn(n); // fudge to test output counting
        VJDFAttributeMap vMap=new VJDFAttributeMap();
        vMap.add(m2);
        spawn.spawn("thisUrl","newURL",null,vMap,true,true,true,true);

    }
    //////////////////////////////////////////////////////////////
    ///////////////////////////////////////////////////////////

    public void testSpawnMixPart()
    {
        for(int i=0;i<2;i++)
        {
            JDFDoc doc =new JDFDoc("JDF");
            JDFNode root=doc.getJDFRoot();
            root.setType(EnumType.Imposition);
            JDFRunList rl=(JDFRunList) root.addResource(ElementName.RUNLIST, null, EnumUsage.Input, null, null, null, null);
            JDFRunList rlEn=(JDFRunList) rl.addPartition(EnumPartIDKey.PartVersion, "EN");
            rlEn.addPDF("En.pdf", 0, -1);
            JDFRunList rlDe=(JDFRunList) rl.addPartition(EnumPartIDKey.PartVersion, "DE");
            rlDe.addPDF("De.pdf", 0, -1);

            JDFRunList rlOut=(JDFRunList) root.addResource(ElementName.RUNLIST, null, EnumUsage.Output, null, null, null, null);
            for(int j=0;j<4;j++)
            {
                JDFRunList rlS1=(JDFRunList) rlOut.addPartition(EnumPartIDKey.SheetName, "S"+j);
                JDFRunList rlS1F=(JDFRunList) rlS1.addPartition(EnumPartIDKey.Side, "Front");
                rlS1F.addPartition(EnumPartIDKey.PartVersion, "EN");
                rlS1F.addPartition(EnumPartIDKey.PartVersion, "DE");

                rlS1.addPartition(EnumPartIDKey.Side, "Back");
            }

            JDFSpawn spawn=new JDFSpawn(root);
            spawn.vSpawnParts=new VJDFAttributeMap();
            spawn.vSpawnParts.add(new JDFAttributeMap(EnumPartIDKey.PartVersion,"EN"));
            spawn.vRWResources_in=new VString("Output",null);
            spawn.bFixResources = i==0;

            JDFNode spawnedNode=spawn.spawn();

            assertNotNull(spawnedNode);

            JDFRunList rlOutSpawn=(JDFRunList) spawnedNode.getMatchingResource(ElementName.RUNLIST, EnumProcessUsage.AnyOutput, null, 0);
            VElement vOut=rlOutSpawn.getPartitionVector(new JDFAttributeMap(EnumPartIDKey.PartVersion,"DE"), null);
            assertEquals(vOut.size(),0);
            vOut=rlOutSpawn.getPartitionVector(new JDFAttributeMap(EnumPartIDKey.PartVersion,"EN"), null);
            assertEquals(vOut.size(),4);
            for(int j=0;j<vOut.size();j++)
                ((JDFResource)vOut.item(j)).setResStatus(EnumResStatus.Available, false);

            JDFMerge merge=new JDFMerge(root);
            root=merge.mergeJDF(spawnedNode, null, EnumCleanUpMerge.None, EnumAmountMerge.UpdateLink);
            assertNotNull(root);
            JDFRunList rlOutMerge=(JDFRunList) root.getMatchingResource(ElementName.RUNLIST, EnumProcessUsage.AnyOutput, null, 0);
            vOut=rlOutMerge.getPartitionVector(new JDFAttributeMap(EnumPartIDKey.PartVersion,"DE"), null);
            assertEquals(vOut.size(),4);

            vOut=rlOutMerge.getPartitionVector(new JDFAttributeMap(EnumPartIDKey.PartVersion,"EN"), null);
            assertEquals(vOut.size(),4);
            for(int j=0;j<vOut.size();j++)
                assertEquals(((JDFResource)vOut.item(j)).getResStatus(false), EnumResStatus.Available);
            vOut=rlOutMerge.getPartitionVector(new JDFAttributeMap(EnumPartIDKey.PartVersion,"DE"), null);
            assertEquals(vOut.size(),4);
            for(int j=0;j<vOut.size();j++)
                assertEquals(((JDFResource)vOut.item(j)).getResStatus(false), EnumResStatus.Unavailable);
        }
    }

    ///////////////////////////////////////////////////////////

    public void testSpawnPartNoSide()
    {
        for(int l=0;l<2;l++) 
        {
            JDFDoc d=new JDFDoc("JDF");
            JDFNode n=d.getJDFRoot();
            n.setType(EnumType.ProcessGroup);
            JDFNode n2=n.addJDFNode(EnumType.ConventionalPrinting);
            JDFLayout lo=(JDFLayout) n2.addResource("Layout", null, EnumUsage.Input, null, n, null, null);
            JDFConventionalPrintingParams cpp=(JDFConventionalPrintingParams) n2.addResource(ElementName.CONVENTIONALPRINTINGPARAMS, null, EnumUsage.Input, null, n, null, null);
            JDFComponent comp=(JDFComponent) n2.addResource(ElementName.COMPONENT, null, EnumUsage.Output, null, n, null, null);
            JDFNodeInfo ni=(JDFNodeInfo) n2.addResource(ElementName.NODEINFO, null, EnumUsage.Input, null, null, null, null);

            lo=(JDFLayout) lo.addPartition(EnumPartIDKey.SignatureName, "sig1");
            cpp=(JDFConventionalPrintingParams) cpp.addPartition(EnumPartIDKey.SignatureName, "sig1");
            comp=(JDFComponent) comp.addPartition(EnumPartIDKey.SignatureName, "sig1");
            ni=(JDFNodeInfo) ni.addPartition(EnumPartIDKey.SignatureName, "sig1");
            for(int i=0;i<2;i++)
            {
                JDFLayout lo2=(JDFLayout) lo.addPartition(EnumPartIDKey.SheetName, "sh"+i);
                cpp.addPartition(EnumPartIDKey.SheetName, "sh"+i);
                comp.addPartition(EnumPartIDKey.SheetName, "sh"+i);
                lo2.addPartition(EnumPartIDKey.Side, EnumSide.Front);
                lo2.addPartition(EnumPartIDKey.Side, EnumSide.Back);    
                JDFNodeInfo ni2= l==0 ? (JDFNodeInfo) ni.addPartition(EnumPartIDKey.SheetName, "sh"+i) : ni; 
                if(l==0 || i==0)
                {
                    ni2.addPartition(EnumPartIDKey.Side, EnumSide.Front);
                    ni2.addPartition(EnumPartIDKey.Side, EnumSide.Back);   
                }

            }
            JDFAttributeMap map=new JDFAttributeMap();
            map.put(EnumPartIDKey.SignatureName, "sig1");
            if(l==0) // miss a part for l=1
                map.put(EnumPartIDKey.SheetName, "sh1");
            map.put(EnumPartIDKey.Side, EnumSide.Front);
            VJDFAttributeMap vMap=new VJDFAttributeMap();
            vMap.add(map);

            JDFSpawn spawn=new JDFSpawn(n2);
            spawn.bFixResources=false;
            spawn.vRWResources_in=new VString("Output",null);
            spawn.vSpawnParts=vMap;
            spawn.bSpawnRWPartsMultiple=true;

            JDFNode nS1=spawn.spawn();
            assertNotNull(nS1);
            nS1.setXPathAttribute("./ResourcePool/Component/Component/Component[@SheetName=\"sh1\"]/@foo", "fnarf");
            map.put(EnumPartIDKey.Side, EnumSide.Back);
            JDFNode nS2=spawn.spawn();
            assertNotNull(nS2);

            nS2.setXPathAttribute("./ResourcePool/Component/Component/Component[@SheetName=\"sh1\"]/@foo", "bar");

            JDFMerge merge=new JDFMerge(n);
            merge.mergeJDF(nS1, null, EnumCleanUpMerge.None, EnumAmountMerge.None);
            assertEquals(n.getXPathAttribute("./ResourcePool/Component/Component/Component[@SheetName=\"sh1\"]/@foo",null),"fnarf");
            merge.mergeJDF(nS2, null, EnumCleanUpMerge.None, EnumAmountMerge.None);
            assertEquals(n.getXPathAttribute("./ResourcePool/Component/Component/Component[@SheetName=\"sh1\"]/@foo",null),"bar");
        }
    }

    ///////////////////////////////////////////////////////////

    public void testSpawnParallel()
    {
        JDFNode[] aSpawned=new JDFNode[3];
        JDFDoc d=JDFResourceTest.creatXMDoc();
        JDFNode n=d.getJDFRoot();
        for(int i=0;i<3;i++)
        {
            VJDFAttributeMap vPartMap=new VJDFAttributeMap();
            JDFAttributeMap map=new JDFAttributeMap();
            map.put("SignatureName","Sig1");
            map.put("SheetName","S"+i);
            vPartMap.add(map);

            final JDFSpawn spawn=new JDFSpawn(n); // fudge to test output counting}
            spawn.vSpawnParts=vPartMap;
            aSpawned[i]=spawn.spawn();           
        }
        for(int i=0;i<3;i++)
        {
            JDFElement.uniqueID(100);
            JDFAuditPool ap=aSpawned[i].getCreateAuditPool();
            for(int j=0;j<100;j++)
                ap.addNotification(EnumClass.Error, null, null);
        }       
        JDFElement.uniqueID(300);
        for(int i=0;i<3;i++)
        {
            final JDFMerge merge = new JDFMerge(n);
            merge.bAddMergeToProcessRun=true;

            // merge here
            JDFNode mergedNode=merge.mergeJDF(aSpawned[i], "merged", JDFNode.EnumCleanUpMerge.None, EnumAmountMerge.UpdateLink);

            assertNull(mergedNode.getMultipleIDs("ID"));
        }       
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
            Vector vRWRes=new Vector();
            vRWRes.add(ElementName.EXPOSEDMEDIA);
            VJDFAttributeMap vPartMap=new VJDFAttributeMap();
            JDFAttributeMap map=new JDFAttributeMap();
            map.put("SignatureName","Sig1");
            map.put("SheetName","S1");
            vPartMap.add(map);
            JDFResourceLink xmRL=n.getMatchingLink(ElementName.EXPOSEDMEDIA,EnumProcessUsage.AnyInput,0);
            xmRL.setAmount(40,i==1 ? map : null);
            xmRL.setUsage(EnumUsage.Output);
            xmRL.setAttribute("foo:bar", "a","www.foobar.com");

            final JDFSpawn spawn=new JDFSpawn(n); // fudge to test output counting
            JDFNode spawnedNode=spawn.spawn("thisUrl","newURL",vRWRes,vPartMap,false,true,true,true);
            String spawnID=spawnedNode.getSpawnID(false);
            assertNotSame(spawnID,"");
            spawnedNode.getCreateAuditPool().addProcessRun(EnumNodeStatus.Completed, null, vPartMap);

            assertTrue("no spawnStatus",spawnedNode.toString().indexOf(AttributeName.SPAWNSTATUS)<0);
            JDFResourceLink rl = spawnedNode.getMatchingLink(ElementName.EXPOSEDMEDIA,EnumProcessUsage.AnyOutput,0);
            JDFResourceAudit ra=spawnedNode.cloneResourceToModify(rl);
            String clonedResID=ra.getOldLink().getrRef();

            // check that the spawnIDs attribute is correctly placed in main and sub
            JDFExposedMedia xmSpawn=(JDFExposedMedia) rl.getTarget();
            assertNotNull(xmSpawn);
            assertEquals(new VString(spawnID,null), xmSpawn.getSpawnIDs(false));
            JDFAttributeMap mapXMSpawn=xmSpawn.getPartMap();
            JDFExposedMedia xmMain=(JDFExposedMedia) n.getMatchingResource(ElementName.EXPOSEDMEDIA, EnumProcessUsage.AnyOutput, null, 0);
            xmMain=(JDFExposedMedia) xmMain.getPartition(mapXMSpawn, null);
            assertNotNull(xmMain);
            assertEquals(new VString(spawnID,null), xmMain.getSpawnIDs(false));

            JDFExposedMedia xmSpawnFront=(JDFExposedMedia) xmSpawn.getPartition(new JDFAttributeMap("Side","Front"), null);
            assertNotNull(xmSpawnFront);
            JDFExposedMedia xmSpawnFrontEn=(JDFExposedMedia) xmSpawnFront.addPartition(EnumPartIDKey.PartVersion, "En");
            assertNotNull(xmSpawnFrontEn);


            JDFResourceLink loRL=spawnedNode.getMatchingLink(ElementName.LAYOUT,EnumProcessUsage.AnyInput,0);
            assertNull(loRL.getPartMapVector());

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

            final JDFMerge merge = new JDFMerge(n);
            merge.bAddMergeToProcessRun=true;

            // merge here
            JDFNode mergedNode=merge.mergeJDF(spawnedNode, "merged", JDFNode.EnumCleanUpMerge.None, EnumAmountMerge.UpdateLink);
            JDFAuditPool ap=mergedNode.getAuditPool();
            assertNotNull(ap);
            JDFMerged merged=(JDFMerged) ap.getAudit(0, EnumAuditType.Merged, null, null);
            JDFProcessRun pr=(JDFProcessRun) mergedNode.getChildByTagName(ElementName.PROCESSRUN, null, 0, null, false, true);
            assertEquals(pr.getSpawnID(), spawnID);
            assertEquals(pr.getAttribute("ReturnTime"), merged.getTimeStamp());

            assertNotNull(merged);
            assertEquals(vPartMap, merged.getPartMapVector());
            assertNull(ap.getElement(ElementName.PART));
            xmRL=mergedNode.getMatchingLink(ElementName.EXPOSEDMEDIA,EnumProcessUsage.AnyOutput,0);  
            final VElement poolChildren = mergedNode.getResourceLinkPool().getPoolChildren("NodeInfoLink", null, null);
            assertNotNull("poolChildren", poolChildren);
            assertEquals("no spurious ni added",poolChildren.size(), 1);
            assertEquals("Comment size",mergedNode.getChildElementVector(ElementName.COMMENT, null, null, true, 99,false).size(),3);
            assertEquals("merged amount ok",xmRL.getAmount(map),40.0,0.);
            assertEquals("did not overwrite rl attribute",xmRL.getAttribute("foo:bar","www.foobar.com",null),"a");
            assertTrue(xmRL.hasAttribute(AttributeName.RREF));

            JDFExposedMedia xm=(JDFExposedMedia) mergedNode.getMatchingResource(ElementName.EXPOSEDMEDIA,EnumProcessUsage.AnyOutput,null,0);
            assertTrue("PartVersion was added in spawned node",xm.getPartIDKeys().contains("PartVersion"));
            xm=(JDFExposedMedia) xm.getPartition(map,null);
            if(i<2)
            {
                assertTrue("merged act amount ok",xmRL.getActualAmount(map)==42);
                assertTrue("merged res amount ok",xm.getAmount()==42);
                assertTrue("merged res amountproduced ok",xm.getAmountProduced()==42);
            }


            // test whether anything modified and tracked in a resource audit got correctly merged
            JDFResourceAudit raMerge=(JDFResourceAudit) ap.getAudit(0, EnumAuditType.ResourceAudit, null,null);
            assertNotNull("res audit merged",raMerge);
            JDFResource rOld=raMerge.getOldLink().getTarget();
            assertNotNull("old res  merged",rOld);
            assertEquals("old res ID",rOld.getID(),clonedResID);
            JDFResource rNew=raMerge.getNewLink().getTarget();
            assertNotNull("new res  merged",rNew);
            assertNull(ap.getElement("Part"));
            JDFMerged mergedAudit=(JDFMerged) ap.getAudit(-1, EnumAuditType.Merged, null, null);
            assertNotNull(mergedAudit);
            assertEquals(mergedAudit.getPartMapVector().elementAt(0), map);
        }
    }

    //////////////////////////////////////////////////////////////
    ///////////////////////////////////////////////////////////

    public void testSpawnMergeSimple()
    {
        EnumCleanUpMerge cu[]=new EnumCleanUpMerge[]{EnumCleanUpMerge.None,EnumCleanUpMerge.RemoveAll,EnumCleanUpMerge.RemoveRRefs};
        for(int i=0;i<3;i++)
        {
            JDFDoc d=JDFResourceTest.creatXMDoc();
            JDFNode n=d.getJDFRoot();
            // test spawning of referenced resources in parent nodes
            n.setType(EnumType.ProcessGroup);
            JDFNode n2=n.addJDFNode(EnumType.ConventionalPrinting);
            n2.moveElement(n.getResourceLinkPool(), null);
            n2.getCreateAuditPool().addNotification(null, null, null).appendComment().setText("notification all pre");
            n2.removeNodeInfo();
            n2.appendNodeInfo();
            final JDFSpawn spawn=new JDFSpawn(n2); // fudge to test output counting
            n2.setStatus(EnumNodeStatus.Waiting);
            assertEquals(EnumNodeStatus.Waiting,n2.getPartStatus(null));
            n2.getCreateAuditPool().addNotification(null, null, null).appendComment().setText("notification 2 main");

            String pid=n2.getJobPartID(false);
            assertNotSame(pid,"");
            JDFNode spawnedNode=spawn.spawn("thisUrl","newURL",null,null,false,true,true,true);
            spawnedNode.getCreateAuditPool().addNotification(null, null, null).appendComment().setText("notification 3 sub");
            assertTrue("no spawnStatus",spawnedNode.toString().indexOf(AttributeName.SPAWNSTATUS)<0);

            spawnedNode.setStatus(EnumNodeStatus.Part);
            spawnedNode.getNodeInfo().setNodeStatus(EnumNodeStatus.Aborted);
            assertEquals(EnumNodeStatus.Part,spawnedNode.getStatus());
            assertEquals(EnumNodeStatus.Aborted,spawnedNode.getPartStatus(null));
            final JDFAuditPool auditPool = spawnedNode.getCreateAuditPool();
            auditPool.addProcessRun(EnumNodeStatus.Aborted, null, null);
            JDFNotification notif=(JDFNotification) auditPool.addAudit(EnumAuditType.Notification, null);
            notif.appendComment().setText("fooBar");
            final JDFMerge merge = new JDFMerge(n);
            merge.bAddMergeToProcessRun=true;

            JDFNode mergedNode=merge.mergeJDF(spawnedNode, "merged", cu[i], EnumAmountMerge.UpdateLink);

            assertEquals(EnumNodeStatus.Part,mergedNode.getStatus());
            assertEquals(EnumNodeStatus.Aborted,mergedNode.getPartStatus(null));

            final JDFNode jobPart = d.getJDFRoot().getJobPart(pid, null);
            assertEquals(jobPart, mergedNode);
            final JDFAuditPool auditPoolMerged = jobPart.getAuditPool();
            if(i==0) // spawned is cleaed away in loop 1 and 2
                assertEquals(auditPoolMerged.getAudit(0, EnumAuditType.ProcessRun, null, null).getAttribute("SubmitTime"), n.getAuditPool().getAudit(0, EnumAuditType.Spawned, null, null).getTimeStamp());
            assertNotNull(auditPoolMerged.getAudit(3, EnumAuditType.Notification, null, null));
            assertNull(auditPoolMerged.getAudit(4, EnumAuditType.Notification, null, null));
            assertEquals("comment text correctly merged",auditPoolMerged.getAudit(-1, EnumAuditType.Notification, null, null).getComment(0).getText(),"fooBar");
        }

    }
    ///////////////////////////////////////////////////////////

    public void testSpawnAddPartRoot()
    {
        JDFDoc d=JDFResourceTest.creatXMDoc();
        JDFNode n=d.getJDFRoot();
        // test spawning of referenced resources in parent nodes
        n.setType(EnumType.ProcessGroup);
        JDFNode n2=n.addJDFNode(EnumType.ConventionalPrinting);
        n2.moveElement(n.getResourceLinkPool(), null);
        n=n2;
        n.removeNodeInfo();
        JDFNodeInfo ni=n.getCreateNodeInfo();
        assertNotNull("ni",ni);
        JDFComment comment=n.appendComment();
        comment.setText("Comment 1");
        Vector vResRW=new Vector();
        vResRW.add(ElementName.EXPOSEDMEDIA);
        vResRW.add(ElementName.APPROVALSUCCESS);

        JDFResourceLink xmRL=n.getMatchingLink(ElementName.EXPOSEDMEDIA,EnumProcessUsage.AnyInput,0);
        xmRL.setAmount(40, null);
        xmRL.setUsage(EnumUsage.Output);

        JDFExposedMedia xmMain=(JDFExposedMedia) n.getMatchingResource(ElementName.EXPOSEDMEDIA,EnumProcessUsage.AnyOutput,null,0);
        JDFMedia media=xmMain.getMedia();
        media.makeRootResource("mediaID", n, true);

        final JDFSpawn spawn=new JDFSpawn(n); // fudge to test output counting

        JDFNode spawnedNode=spawn.spawn("thisUrl","newURL",vResRW,null,false,true,true,true);
        assertTrue("no spawnStatus",spawnedNode.toString().indexOf(AttributeName.SPAWNSTATUS)<0);

        JDFResourceLink xmRLspawn=spawnedNode.getMatchingLink(ElementName.EXPOSEDMEDIA,EnumProcessUsage.AnyOutput,0);
        JDFNodeInfo ni2=(JDFNodeInfo) spawnedNode.getMatchingResource(ElementName.NODEINFO,EnumProcessUsage.AnyInput,null,0);
        JDFExposedMedia xm=(JDFExposedMedia)xmRLspawn.getTarget();
        assertFalse("PartVersion was added in spawned node",xm.getPartIDKeys().contains("PartVersion"));
        assertFalse("PartVersion was added in spawned node",ni2.getPartIDKeys().contains("PartVersion"));
        assertNotNull(xm);

        ni2.addPartition(EnumPartIDKey.PartVersion, "En");


        JDFExposedMedia xmRoot=(JDFExposedMedia) xm.getResourceRoot();
        assertEquals(xm, xmRoot);

        JDFMedia spawnMedia=xm.getMedia();
        assertNotNull(spawnMedia);



        n=d.getJDFRoot();
        // test spawning of referenced resources in parent nodes
        n=(JDFNode)n.getElement("JDF");
        JDFNode mergedNode=new JDFMerge(n).mergeJDF(spawnedNode, "merged", JDFNode.EnumCleanUpMerge.None, EnumAmountMerge.UpdateLink);
        xmRL=mergedNode.getMatchingLink(ElementName.EXPOSEDMEDIA,EnumProcessUsage.AnyOutput,0);  
        ni2=(JDFNodeInfo) mergedNode.getMatchingResource(ElementName.NODEINFO,EnumProcessUsage.AnyInput,null,0);
        assertTrue("PartVersion was added in spawned node",ni2.getPartIDKeys().contains("PartVersion"));


    }
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
            n.appendMatchingResource(ElementName.APPROVALSUCCESS, EnumProcessUsage.AnyInput, null);
            assertNotNull("ni",ni);
            JDFComment comment=n.appendComment();
            comment.setText("Comment 1");
//          ni.addPartition(JDFResource.EnumPartIDKey.SignatureName,"Sig1");
            JDFResourceLink l=n.getMatchingLink(ElementName.NODEINFO,EnumProcessUsage.AnyInput,0);
            l.setPart("SignatureName","Sig1");
            Vector vResRW=new Vector();
            vResRW.add(ElementName.EXPOSEDMEDIA);
            vResRW.add(ElementName.APPROVALSUCCESS);
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

            JDFNode spawnedNode=spawn.spawn("thisUrl","newURL",vResRW,vMap,false,true,true,true);
            assertTrue("no spawnStatus",spawnedNode.toString().indexOf(AttributeName.SPAWNSTATUS)<0);

            JDFResourceLink xmRLspawn=spawnedNode.getMatchingLink(ElementName.EXPOSEDMEDIA,EnumProcessUsage.AnyOutput,0);
            JDFNodeInfo ni2=(JDFNodeInfo) spawnedNode.getMatchingResource(ElementName.NODEINFO,EnumProcessUsage.AnyInput,null,0);
            JDFExposedMedia xm=(JDFExposedMedia)xmRLspawn.getTarget();
            assertFalse("PartVersion was added in spawned node",xm.getPartIDKeys().contains("PartVersion"));
            assertFalse("PartVersion was added in spawned node",ni2.getPartIDKeys().contains("PartVersion"));
            assertNotNull(xm);
            JDFApprovalSuccess as2=(JDFApprovalSuccess) spawnedNode.getMatchingResource(ElementName.APPROVALSUCCESS,EnumProcessUsage.AnyInput,null,0);
            assertNotNull(as2);
            as2.addPartition(EnumPartIDKey.PartVersion, "En");
            assertTrue(as2.getPartIDKeys().contains(EnumPartIDKey.PartVersion.getName()));



            JDFExposedMedia xmSpawnFront=(JDFExposedMedia) xm.getPartition(new JDFAttributeMap("Side","Front"), null);
            assertNotNull(xmSpawnFront);
            JDFExposedMedia xmSpawnFrontEn=(JDFExposedMedia) xmSpawnFront.addPartition(EnumPartIDKey.PartVersion, "En");
            assertNotNull(xmSpawnFrontEn);
            ni2.addPartition(EnumPartIDKey.PartVersion, "En");


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
            assertTrue("PartVersion was added in spawned node",xmMRoot.getPartIDKeys().contains("PartVersion"));
            ni2=(JDFNodeInfo) mergedNode.getMatchingResource(ElementName.NODEINFO,EnumProcessUsage.AnyInput,null,0);
            assertTrue("PartVersion was added in spawned node",ni2.getPartIDKeys().contains("PartVersion"));


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

        for(int i=0;i<3;i++)
        {
            JDFNode n2=n.addCombined(v);
            JDFNodeInfo ni=n2.appendNodeInfo();
            final JDFSpawn spawn=new JDFSpawn(n2);
            VJDFAttributeMap vSpawnParts=null;
            if(i>=1)
            {
                if(i==2)
                    ni.addPartition(EnumPartIDKey.Run, "r1");
                vSpawnParts=new VJDFAttributeMap();
                vSpawnParts.add(new JDFAttributeMap("Run","r1"));
            }
            if(i==1)
                spawn.bFixResources=false;
            JDFNode spawnedNode=spawn.spawn("thisFile","spawnFile",null,vSpawnParts,true,true,true,true);
            JDFNodeInfo niSpawn= spawnedNode.getInheritedNodeInfo(null);
            assertNotNull("ni",niSpawn);
            if(i>=1)
                assertEquals(niSpawn.getPartMapVector(false), vSpawnParts);
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
                ni.setFirstEnd(new JDFDate());
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

                final JDFSpawn spawn2=new JDFSpawn(n2);
                spawnedNode=spawn2.spawn("thisFile","spawnFile",null,partmapvector,true,true,true,true);
                spawnID = spawnedNode.getSpawnID(false);

                niSpawn= spawnedNode.getInheritedNodeInfo(null);
                assertNotNull("ni",niSpawn);

                spawnedNode.setPartStatus(partmapvector, EnumNodeStatus.Aborted);
                assertEquals(spawnedNode.getPartStatus(j==1 ? partmapvector.elementAt(0): null),EnumNodeStatus.Aborted);

                JDFNode mergedNode=new JDFMerge(n2).mergeJDF(spawnedNode, "merged", JDFNode.EnumCleanUpMerge.None, EnumAmountMerge.UpdateLink);
                assertEquals(mergedNode.getPartStatus(j==1 ? partmapvector.elementAt(0): null),EnumNodeStatus.Aborted);
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
    }    ///////////////////////////////////////////////////////////////////////////

    public void testManySpawn()   throws Exception    
    {        
        final String strJDFName = "000023_Test_PR3.0.jdf";
//      final String strJDFName = "biginline.jdf";
        String strJDFPath = sm_dirTestData +  strJDFName;
        JDFParser parser = new JDFParser ();
        JDFDoc jdfDoc = parser.parseFile(strJDFPath);
        final JDFNode nodeRoot = jdfDoc.getJDFRoot ();
        final VElement vNodes=nodeRoot.getTree("JDF", null, null, false, false);
        for(int i=1;i<vNodes.size();i++)
        {
            JDFNode nodeProc = (JDFNode)vNodes.elementAt(i);
            final String jobPartID = nodeProc.getJobPartID(false);
            System.out.println("i= "+i+" of "+(vNodes.size()-1)+" : "+jobPartID);
            VString vsRWResourceIDs = new VString ();
            vsRWResourceIDs.add ("Link84847227_000309");
            vsRWResourceIDs.add ("r85326439_027691");
            vsRWResourceIDs.add ("Output");
            nodeProc=nodeRoot.getJobPart(jobPartID, null); // in case it was overwritten by a previos s-m
            final JDFSpawn spawn=new JDFSpawn(nodeProc);
//          JDFNode nodeProc = nodeRoot;
            JDFNode nodeSubJDF = spawn.spawn(strJDFPath,null,vsRWResourceIDs,null,true,true,true,true);
            assertNotNull(nodeSubJDF);

            nodeSubJDF.getOwnerDocument_KElement().write2File(sm_dirTestDataTemp+"manySub"+i+".jdf", 2, true);
            jdfDoc.write2File(sm_dirTestDataTemp+"bigMainMany"+i+".jdf", 2, true);

            assertEquals(nodeProc.getID(), nodeSubJDF.getID());
            JDFDoc d2=parser.parseFile(sm_dirTestDataTemp+"manySub"+i+".jdf");
            assertNotNull("The subjdf could be parsed!",d2);
            String spawnID=nodeSubJDF.getSpawnID(false);
            JDFMerge m=new JDFMerge(nodeRoot);
            assertTrue(nodeRoot.toString().indexOf(spawnID)>0);
            m.mergeJDF(nodeSubJDF, "dummy", EnumCleanUpMerge.RemoveAll, EnumAmountMerge.UpdateLink);
            assertTrue(nodeRoot.toString().indexOf(spawnID)<0);
        }
        jdfDoc.write2File(sm_dirTestDataTemp+"bigMainMany.jdf", 2, true);     

    }

    ///////////////////////////////////////////////////////////////////////


    public void testMergeUpdateNI() throws Exception    
    {
        JDFDoc doc=new JDFDoc("JDF");
        JDFNode root = doc.getJDFRoot();
        root.setType(EnumType.ProcessGroup);
        JDFAttributeMap map1=new JDFAttributeMap("Run","r1");
        JDFAttributeMap map2=new JDFAttributeMap("Run","r2");
        VJDFAttributeMap vMap=new VJDFAttributeMap();
        vMap.add(map1);
        vMap.add(map2);
        JDFNode[] nodes=new JDFNode[3];
        root.setPartStatus(vMap, EnumNodeStatus.Waiting);
        nodes[0]=root.addJDFNode(EnumType.Approval);
        nodes[1]=root.addJDFNode(EnumType.Bending);
        nodes[2]=root.addJDFNode(EnumType.ImageReplacement);
        for(int i=0;i<3;i++)
            nodes[i].setPartStatus(vMap, EnumNodeStatus.Waiting);

        vMap.removeElementAt(1);
        for(int i=0;i<3;i++)
        {

            JDFNode node = nodes[i];
            assertNotNull(node.getNodeInfo());
            JDFSpawn spawn=new JDFSpawn(node);
            spawn.vSpawnParts=vMap;
            JDFNode spawnedNode=spawn.spawn();
            spawnedNode.setPartStatus(map1, EnumNodeStatus.Completed);
            JDFMerge merge=new JDFMerge(node);

            // this is the feature taht is being tested..
            merge.bUpdateStati=true;
            node=merge.mergeJDF(spawnedNode, null, EnumCleanUpMerge.None, EnumAmountMerge.None);
            assertEquals(node.getID(), nodes[i].getID());
            assertEquals(root.getPartStatus(map1), i==2 ? EnumNodeStatus.Completed : EnumNodeStatus.Waiting);
            assertEquals(root.getPartStatus(map2), EnumNodeStatus.Waiting);
            assertEquals(node.getPartStatus(map1), EnumNodeStatus.Completed );
            assertEquals(node.getPartStatus(map2), EnumNodeStatus.Waiting);
        }

    }
    ///////////////////////////////////////////////////////////////////////


    public void testBigMerge() throws Exception    
    {       
        //       testBigSpawn();
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
            assertNull(nodeMain.getMultipleIDs("ID"));
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

        JDFNode nodeProc = jdfDoc.getJDFRoot().getJobPart ("IPD0.I", null);
        final Collection vSpawned = nodeProc.checkSpawnedResources (vsRWResourceIDs, vamParts);
        assertNull(vSpawned);
        JDFSpawn spawn=new JDFSpawn(nodeProc);
        spawn.vRWResources_in=vsRWResourceIDs;
        spawn.vSpawnParts=vamParts;
        JDFNode s2=spawn.spawn();
        assertNotNull(spawn.checkSpawnedResources());
        assertNull(s2.getMultipleIDs("ID"));

        try
        {
            spawn.spawn();
            fail("multi rw spawn");
        }
        catch(JDFException x)
        {     // nop
        }
        spawn.bSpawnRWPartsMultiple=true;
        assertNotNull(spawn.checkSpawnedResources());
        spawn.spawn();

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

    public void testPartitionedSpawnNI()
    {
        for(int i=0;i<2;i++)
        {
            JDFDoc d=new JDFDoc("JDF");
            JDFNode nRoot=d.getJDFRoot();
            nRoot.setType(EnumType.ProcessGroup);
            JDFNode n2=nRoot.addJDFNode(EnumType.Buffer);
            JDFAttributeMap map=new JDFAttributeMap(EnumPartIDKey.SheetName, "s1");
            JDFNodeInfo ni=(JDFNodeInfo) n2.addResource(ElementName.NODEINFO, null, EnumUsage.Input, null, null, null, null);
            JDFBufferParams bp=(JDFBufferParams) n2.addResource(ElementName.BUFFERPARAMS, null, EnumUsage.Input, null, nRoot, null, null);
            bp.addPartition(EnumPartIDKey.SheetName, "s1");
            JDFComponent comp=(JDFComponent)n2.addResource(ElementName.COMPONENT, null, EnumUsage.Output, null, nRoot, null, null);
            comp.addPartition(EnumPartIDKey.SheetName, "s1");
            JDFSpawn spawn=new JDFSpawn(n2);
            spawn.vRWResources_in=new VString("Output NodeInfo"," ");
            spawn.vSpawnParts=new VJDFAttributeMap();
            spawn.vSpawnParts.add(map);
            if(i==0)
                spawn.bFixResources=false;
            JDFNode spawnedNode=spawn.spawn();
            assertTrue(ni.toString().indexOf(AttributeName.SPAWNIDS)>0);
            JDFNodeInfo ni2=spawnedNode.getNodeInfo();
            assertTrue(ni2.toString().indexOf(AttributeName.SPAWNIDS)>0);
        }

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

        assertNull(m_jdfDoc.getRoot().getMultipleIDs("ID"));
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


    /* (non-Javadoc)
     * @see org.cip4.jdflib.JDFTestCaseBase#setUp()
     */
    protected void setUp() throws Exception
    {
        super.setUp();
        JDFElement.setLongID(false);
    }



}