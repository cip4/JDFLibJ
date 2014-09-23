/*
 *
 * The CIP4 Software License, Version 1.0
 *
 *
 * Copyright (c) 2001-2013 The International Cooperation for the Integration of
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

import java.io.File;
import java.util.Collection;
import java.util.List;
import java.util.Vector;

import org.cip4.jdflib.JDFTestCaseBase;
import org.cip4.jdflib.auto.JDFAutoNotification.EnumClass;
import org.cip4.jdflib.auto.JDFAutoPart.EnumSide;
import org.cip4.jdflib.core.AttributeName;
import org.cip4.jdflib.core.ElementName;
import org.cip4.jdflib.core.JDFAudit;
import org.cip4.jdflib.core.JDFAudit.EnumAuditType;
import org.cip4.jdflib.core.JDFAudit.EnumSeverity;
import org.cip4.jdflib.core.JDFComment;
import org.cip4.jdflib.core.JDFConstants;
import org.cip4.jdflib.core.JDFCustomerInfo;
import org.cip4.jdflib.core.JDFDoc;
import org.cip4.jdflib.core.JDFElement.EnumNodeStatus;
import org.cip4.jdflib.core.JDFElement.EnumValidationLevel;
import org.cip4.jdflib.core.JDFElement.EnumVersion;
import org.cip4.jdflib.core.JDFException;
import org.cip4.jdflib.core.JDFNodeInfo;
import org.cip4.jdflib.core.JDFParser;
import org.cip4.jdflib.core.JDFResourceLink;
import org.cip4.jdflib.core.JDFResourceLink.EnumUsage;
import org.cip4.jdflib.core.KElement;
import org.cip4.jdflib.core.VElement;
import org.cip4.jdflib.core.VString;
import org.cip4.jdflib.core.XMLDoc;
import org.cip4.jdflib.datatypes.JDFAttributeMap;
import org.cip4.jdflib.datatypes.VJDFAttributeMap;
import org.cip4.jdflib.node.JDFAncestor;
import org.cip4.jdflib.node.JDFNode;
import org.cip4.jdflib.node.JDFNode.EnumActivation;
import org.cip4.jdflib.node.JDFNode.EnumCleanUpMerge;
import org.cip4.jdflib.node.JDFNode.EnumProcessUsage;
import org.cip4.jdflib.node.JDFNode.EnumType;
import org.cip4.jdflib.node.JDFSpawned;
import org.cip4.jdflib.node.NodeIdentifier;
import org.cip4.jdflib.pool.JDFAuditPool;
import org.cip4.jdflib.pool.JDFResourcePool;
import org.cip4.jdflib.resource.JDFBufferParams;
import org.cip4.jdflib.resource.JDFMerged;
import org.cip4.jdflib.resource.JDFNotification;
import org.cip4.jdflib.resource.JDFProcessRun;
import org.cip4.jdflib.resource.JDFResource;
import org.cip4.jdflib.resource.JDFResource.EnumAmountMerge;
import org.cip4.jdflib.resource.JDFResource.EnumPartIDKey;
import org.cip4.jdflib.resource.JDFResource.EnumPartUsage;
import org.cip4.jdflib.resource.JDFResource.EnumResStatus;
import org.cip4.jdflib.resource.JDFResource.EnumSpawnStatus;
import org.cip4.jdflib.resource.JDFResourceAudit;
import org.cip4.jdflib.resource.process.JDFApprovalSuccess;
import org.cip4.jdflib.resource.process.JDFComponent;
import org.cip4.jdflib.resource.process.JDFContact;
import org.cip4.jdflib.resource.process.JDFConventionalPrintingParams;
import org.cip4.jdflib.resource.process.JDFEmployee;
import org.cip4.jdflib.resource.process.JDFExposedMedia;
import org.cip4.jdflib.resource.process.JDFLayout;
import org.cip4.jdflib.resource.process.JDFMedia;
import org.cip4.jdflib.resource.process.JDFPreview;
import org.cip4.jdflib.resource.process.JDFRunList;
import org.cip4.jdflib.resource.process.JDFTransferCurvePool;
import org.cip4.jdflib.resource.process.postpress.JDFFoldingParams;
import org.junit.Before;
import org.junit.Test;
import org.w3c.dom.Node;

/**
 * @author Rainer Prosi, Heidelberger Druckmaschinen
 * 
 */
public class JDFSpawnTest extends JDFTestCaseBase
{

	/**
	 * Test method for multiple spawn
	 */
	@Test
	public void testSpawnSubSubJDFs()
	{

		final JDFDoc d = JDFDoc.parseFile(sm_dirTestData + "ApprovalSubJDF.jdf");
		final JDFNode subjdfDocRoot = d.getJDFRoot();
		final VJDFAttributeMap vspawnPartsMap = subjdfDocRoot.getAncestorPool().getPartMapVector();

		final int size = vspawnPartsMap.size();
		// final JDFAttributeMap elementAt =
		vspawnPartsMap.elementAt(size - 1);
		vspawnPartsMap.removeElementAt(size - 1);

		final VString vRWResources = new VString();
		vRWResources.add(JDFConstants.OUTPUT);

		final String fileURL = "C:\\testJDF.jdf";

		long endSpawnTime = System.currentTimeMillis();
		final long currentTimeMillis = endSpawnTime;
		final JDFNode nested_spawn_node = new JDFSpawn(subjdfDocRoot).spawn(fileURL, fileURL, vRWResources, vspawnPartsMap, false, true, true, true);
		System.out.println("Spawning took " + (endSpawnTime - currentTimeMillis));
		assertNotNull(nested_spawn_node);
		final JDFMerge merge = new JDFMerge(subjdfDocRoot);

		// this is the feature taht is being tested..
		merge.bUpdateStati = true;
		final JDFNode nodeM = merge.mergeJDF(nested_spawn_node, null, EnumCleanUpMerge.None, EnumAmountMerge.None);
		assertNotNull(nodeM);
		System.out.println("Merging took " + (endSpawnTime - endSpawnTime));
	}

	/**
	 * @param strXMLFile
	 * @param strSpawnID
	 */
	private void unSpawn(final String strXMLFile, final String strSpawnID)
	{
		final String strXMLFileModified = "_" + strXMLFile;

		final JDFParser p = new JDFParser();
		final JDFDoc doc = p.parseFile(sm_dirTestDataTemp + strXMLFileModified);
		// parse the original file, which is already spawned
		assertNotNull("Parse of file " + sm_dirTestDataTemp + strXMLFileModified + " failed", doc); // _bookintent.jdf

		JDFNode root = (JDFNode) doc.getRoot();
		assertNotNull(root);
		if (root != null)
		{
			root = new JDFSpawn(root).unSpawn(strSpawnID);
			assertTrue(" root empty", root.toString().indexOf(strSpawnID) < 0);
			assertNull(root.getMultipleIDs("ID"));

			// write out the unspawned file
			doc.write2File(sm_dirTestDataTemp + "Unspawn_" + strXMLFile, 0, true); // Unspawn_bookintent.jdf
		}
	}

	/**
	 * 
	 */
	@Test
	public void testCorruptPartitionedSpawn()
	{
		final JDFDoc d = new JDFDoc("JDF");
		final JDFNode root = d.getJDFRoot();
		root.setType(EnumType.ProcessGroup);
		final JDFNode cp = root.addJDFNode(EnumType.ConventionalPrinting);
		final JDFNode fold = root.addJDFNode(EnumType.Folding);
		final JDFComponent comp = (JDFComponent) cp.addResource("Component", null, EnumUsage.Output, null, root, null, null);
		final JDFAttributeMap cMap = new JDFAttributeMap();
		cMap.put(EnumPartIDKey.SignatureName, "Sig1");
		cMap.put(EnumPartIDKey.SheetName, "S1");
		cMap.put(EnumPartIDKey.Condition, "Good");
		comp.getCreatePartition(cMap, new VString("SignatureName SheetName Condition", " "));
		cMap.put(EnumPartIDKey.Condition, "Waste");
		comp.getCreatePartition(cMap, null);
		comp.setResStatus(EnumResStatus.Available, true);
		fold.linkResource(comp, EnumUsage.Input, null);

		final JDFComponent compOut = (JDFComponent) fold.addResource("Component", null, EnumUsage.Output, null, root, null, null);
		compOut.addPartition(EnumPartIDKey.Condition, "Good");
		compOut.addPartition(EnumPartIDKey.Condition, "Waste");
		final JDFAttributeMap map = new JDFAttributeMap();
		map.put(EnumPartIDKey.SignatureName, "Sig1");
		// map.put(EnumPartIDKey.SheetName, "Sht1");
		final VJDFAttributeMap v = new VJDFAttributeMap();
		v.add(map);
		final VString vRW = new VString("Output", null);
		final JDFSpawn spawn = new JDFSpawn(fold);
		final JDFNode spawned = spawn.spawn(null, null, vRW, v, true, true, true, true);

		final JDFComponent spCompOut = (JDFComponent) spawned.getMatchingLink("Component", EnumProcessUsage.AnyOutput, 0).getLinkRoot();
		assertTrue("partition structure is zapped", spCompOut.isValid(EnumValidationLevel.Incomplete));

	}

	/**
	 * 
	 */
	@Test
	public void testSubsetPartitionedSpawn()
	{
		for (int i = 0; i < 2; i++)
		{
			for (int j = 0; j < 2; j++)
			{
				final JDFDoc d = new JDFDoc("JDF");
				final JDFNode root = d.getJDFRoot();
				root.setType(EnumType.ProcessGroup);
				final JDFNode cp = root.addJDFNode(EnumType.ConventionalPrinting);
				final JDFComponent comp = (JDFComponent) cp.addResource("Component", null, EnumUsage.Output, null, null, null, null);
				final JDFAttributeMap cMap = new JDFAttributeMap();
				cMap.put(EnumPartIDKey.SignatureName, "Sig1");
				cMap.put(EnumPartIDKey.SheetName, "S1");
				comp.getCreatePartition(cMap, new VString("SignatureName SheetName", " "));
				comp.getCreatePartition(cMap, null);
				comp.setResStatus(EnumResStatus.Available, true);

				cMap.put(EnumPartIDKey.Side, "Front");
				final VJDFAttributeMap v = new VJDFAttributeMap();
				v.add(cMap);
				final VString vRW = new VString("Output", null);

				final JDFNodeInfo ni = cp.appendNodeInfo();
				final JDFNodeInfo ni2 = (JDFNodeInfo) ni.getCreatePartition(cMap, new VString("SignatureName SheetName Side", " "));
				final JDFEmployee emp = ni2.appendEmployee();
				emp.makeRootResource(null, null, true);
				assertNull(root.getResourcePool());
				if (j == 1)
				{
					root.moveElement(cp.getResourcePool(), null);
					assertNotNull(root.getResourcePool());
				}

				final JDFSpawn spawn = new JDFSpawn(cp);
				if (i == 0)
				{
					spawn.bFixResources = false;
				}
				final JDFNode spawned = spawn.spawn(null, null, vRW, v, true, true, true, true);

				final JDFComponent spCompOut = (JDFComponent) spawned.getMatchingLink("Component", EnumProcessUsage.AnyOutput, 0).getLinkRoot();
				if (i == 0)
				{
					assertNull("partition structure is zapped", spCompOut.getPartition(cMap, null));
				}
				else
				{
					assertNotNull("partition structure is notzapped " + i + " " + j, spCompOut.getPartition(cMap, null));
				}
				cMap.remove(EnumPartIDKey.Side);
				assertNotNull("partition structure is zapped", spCompOut.getPartition(cMap, null));

				assertNotNull("The Employee that was referenced by a partition was correctly spawned", spawned.getResourcePool().getElement("Employee"));
			}
		}
	}

	// /////////////////////////////////////////////////////////

	/**
	 * 
	 */
	@Test
	public void testSpawnPartImplicit()
	{
		final JDFDoc dRoot = new JDFDoc("JDF");
		final JDFNode nRoot = dRoot.getJDFRoot();
		final JDFCustomerInfo ci = nRoot.appendCustomerInfo();
		ci.setCustomerProjectID("foo");
		final VJDFAttributeMap vMap = new VJDFAttributeMap();
		final JDFAttributeMap map = new JDFAttributeMap();
		map.put("SignatureName", "Sig1");
		vMap.add(map);
		JDFSpawn spawn = new JDFSpawn(nRoot);
		spawn.vSpawnParts = vMap;
		final JDFNode spawnedNode = spawn.spawn();
		assertEquals(1, spawnedNode.getResourceLinkPool().getPoolChild(0, ElementName.CUSTOMERINFO + "Link", null, null).getPartMapVector().size());
		assertEquals(1, spawnedNode.getResourceLinkPool().getPoolChild(0, ElementName.NODEINFO + "Link", null, null).getPartMapVector().size());
	}

	/**
	 * 
	 */
	@Test
	public void testSpawnPartImplicitColorant()
	{
		final JDFDoc dRoot = new JDFDoc("JDF");
		final JDFNode nRoot = dRoot.getJDFRoot();
		final JDFResource r = nRoot.addResource(ElementName.COLORANTCONTROL, EnumUsage.Input);
		r.setPartUsage(EnumPartUsage.Implicit);
		final VJDFAttributeMap vMap = new VJDFAttributeMap();
		final JDFAttributeMap map = new JDFAttributeMap();
		map.put("SignatureName", "Sig1");
		map.put("SheetName", "S1");
		vMap.add(map.clone());
		map.put("SheetName", "S2");
		vMap.add(map.clone());
		r.addPartition(EnumPartIDKey.SignatureName, "Sig1");
		nRoot.getCreateNodeInfo().addPartition(EnumPartIDKey.SignatureName, "Sig1");
		JDFSpawn spawn = new JDFSpawn(nRoot);
		spawn.vSpawnParts = vMap;
		final JDFNode spawnedNode = spawn.spawn();
		assertEquals(2, spawnedNode.getResourceLinkPool().getPoolChild(0, ElementName.COLORANTCONTROL + "Link", null, null).getPartMapVector().size());
	}

	/**
	 * 
	 */
	@Test
	public void testSpawnPartAmountPool()
	{
		final JDFDoc dRoot = new JDFDoc("JDF");
		final JDFNode root = dRoot.getJDFRoot();
		JDFResource comp = root.addResource("Component", EnumUsage.Output);
		comp.addPartition(EnumPartIDKey.Run, "r1");
		comp.addPartition(EnumPartIDKey.Run, "r2");
		JDFResourceLink rl = root.getLink(comp, null);
		JDFAttributeMap mPart = new JDFAttributeMap("Run", "r1");
		final VJDFAttributeMap vMap = new VJDFAttributeMap();
		vMap.add(mPart.clone());
		mPart.put("Condition", "Good");
		rl.setAmount(1, mPart);
		JDFAttributeMap mPart2 = new JDFAttributeMap("Run", "r2");
		final VJDFAttributeMap vMap2 = new VJDFAttributeMap();
		vMap2.add(mPart2.clone());

		mPart2.put("Condition", "Good");
		rl.setAmount(2, mPart2);
		JDFSpawn spawn1 = new JDFSpawn(root);
		spawn1.vSpawnParts = vMap;
		spawn1.vRWResources_in = new VString("Output", null);
		JDFNode s1 = spawn1.spawn();
		JDFResource c1 = s1.getResource("Component", EnumUsage.Output, 0);
		JDFResourceLink rl1 = s1.getLink(c1, null);
		mPart.put("Condition", "Good");
		rl1.setActualAmount(1, mPart);
		mPart.put("Condition", "Waste");
		rl1.setActualAmount(10, mPart);

		JDFSpawn spawn2 = new JDFSpawn(root);
		spawn2.vSpawnParts = vMap2;
		spawn2.vRWResources_in = new VString("Output", null);
		JDFNode s2 = spawn2.spawn();
		JDFResource c2 = s2.getResource("Component", EnumUsage.Output, 0);
		JDFResourceLink rl2 = s2.getLink(c2, null);
		mPart2.put("Condition", "Good");
		rl2.setActualAmount(2, mPart2);
		mPart2.put("Condition", "Waste");
		rl2.setActualAmount(20, mPart2);
		assertEquals(rl.getAmountPool().numChildElements(ElementName.PARTAMOUNT, null), 2);

		JDFMerge m = new JDFMerge(root);
		m.mergeJDF(s1);
		rl = root.getLink(comp, null);
		assertEquals(rl.getAmountPool().numChildElements(ElementName.PARTAMOUNT, null), 3);
		JDFMerge m2 = new JDFMerge(root);
		m2.mergeJDF(s2);
		rl = root.getLink(comp, null);
		assertEquals(rl.getAmountPool().numChildElements(ElementName.PARTAMOUNT, null), 4);
	}

	/**
	* 
	*/
	@Test
	public void testSpawnPartAmountPoolPartition()
	{
		final JDFDoc dRoot = new JDFDoc("JDF");
		final JDFNode root = dRoot.getJDFRoot();
		JDFResource comp = root.addResource("Component", EnumUsage.Output);
		JDFResource sig = comp.addPartition(EnumPartIDKey.SignatureName, "sig1");
		JDFResource sheet = sig.addPartition(EnumPartIDKey.SheetName, "sh1");
		sheet.addPartition(EnumPartIDKey.Side, "Front");
		sheet.addPartition(EnumPartIDKey.Side, "Back");
		JDFResourceLink rl = root.getLink(comp, null);
		JDFAttributeMap mPart = new JDFAttributeMap(EnumPartIDKey.SignatureName, "sig1");
		mPart.put(EnumPartIDKey.SheetName, "sh1");
		final VJDFAttributeMap vMap = new VJDFAttributeMap();
		vMap.add(mPart.clone());
		mPart.put("Condition", "Good");
		rl.setAmount(100, mPart);

		JDFSpawn spawn1 = new JDFSpawn(root);
		spawn1.vSpawnParts = vMap;
		spawn1.vRWResources_in = new VString("Output", null);
		JDFNode s1 = spawn1.spawn();
		JDFResource c1 = s1.getResource("Component", EnumUsage.Output, 0);
		JDFResourceLink rl1 = s1.getLink(c1, null);
		mPart.put("Condition", "Good");
		mPart.put("Side", "Front");
		rl1.setActualAmount(105, mPart);
		mPart.put("Condition", "Waste");
		rl1.setActualAmount(10, mPart);
		mPart.put("Side", "Back");
		rl1.setActualAmount(105, mPart);
		mPart.put("Condition", "Waste");
		rl1.setActualAmount(10, mPart);

		JDFMerge m = new JDFMerge(root);
		m.setAmountPolicy(EnumAmountMerge.UpdateLink);
		m.mergeJDF(s1);
		rl = root.getLink(comp, null);
		assertEquals(rl.getAmountPool().numChildElements(ElementName.PARTAMOUNT, null), 4);
	}

	/**
	 * 
	 */
	@Test
	public void testSpawnMulti10()
	{
		final JDFDoc dRoot = new JDFDoc("JDF");
		final JDFNode nRoot = dRoot.getJDFRoot();
		final JDFCustomerInfo ci = nRoot.appendCustomerInfo();
		final JDFNodeInfo ni = nRoot.appendNodeInfo();
		ni.setNodeStatus(EnumNodeStatus.Waiting);
		nRoot.setStatus(EnumNodeStatus.Part);
		ci.setCustomerProjectID("foo");

		nRoot.setType("Product", true);
		JDFNode n2 = nRoot.addProduct();
		for (int i = 0; i < 10; i++)
		{
			n2 = nRoot.getJobPart(new NodeIdentifier(n2));
			JDFSpawn sp = new JDFSpawn(n2);
			JDFNode n3 = sp.spawn();
			JDFMerge m = new JDFMerge(nRoot);
			m.mergeJDF(n3);
		}
		assertEquals(n2.numChildNodes(KElement.COMMENT_NODE, true), 0);
		dRoot.write2File(sm_dirTestDataTemp + "spawn10.jdf", 2, false);
	}

	/**
	 * 
	 */
	@Test
	public void testSpawnMulti10Part()
	{
		final JDFDoc dRoot = new JDFDoc("JDF");
		final JDFNode nRoot = dRoot.getJDFRoot();
		final JDFCustomerInfo ci = nRoot.appendCustomerInfo();
		final JDFNodeInfo ni = nRoot.appendNodeInfo();
		ni.setNodeStatus(EnumNodeStatus.Waiting);
		nRoot.setStatus(EnumNodeStatus.Part);
		ci.setCustomerProjectID("foo");

		nRoot.setType("Product", true);
		JDFNode n2 = nRoot.addProduct();
		for (int i = 0; i < 10; i++)
		{
			n2 = nRoot.getJobPart(new NodeIdentifier(n2));
			JDFSpawn sp = new JDFSpawn(n2);
			sp.vSpawnParts = new VJDFAttributeMap();
			sp.vSpawnParts.add(new JDFAttributeMap("SheetName", "S1"));
			sp.vRWResources_in = new VString("NodeInfo", " ");
			JDFNode n3 = sp.spawn();
			JDFMerge m = new JDFMerge(nRoot);
			m.mergeJDF(n3);
		}
		assertEquals(n2.numChildNodes(KElement.COMMENT_NODE, true), 0);
		dRoot.write2File(sm_dirTestDataTemp + "spawn10Part.jdf", 2, false);
	}

	/**
	 * 
	 */
	@Test
	public void testSpawnPartMulti()
	{
		final JDFDoc dRoot = new JDFDoc("JDF");
		final JDFNode nRoot = dRoot.getJDFRoot();
		final JDFCustomerInfo ci = nRoot.appendCustomerInfo();
		ci.setCustomerProjectID("foo");

		nRoot.setType("Product", true);

		final JDFDoc d = JDFTestCaseBase.creatXMDoc();
		JDFNode n = (JDFNode) nRoot.copyElement(d.getJDFRoot(), null);
		JDFExposedMedia xm = (JDFExposedMedia) n.getMatchingResource(ElementName.EXPOSEDMEDIA, EnumProcessUsage.AnyInput, null, 0);
		final JDFMedia media = xm.getMedia();
		media.makeRootResource("mediaID", nRoot, true);

		final JDFNode nodeImageSet = nRoot.addJDFNode("ImageSetting");
		nodeImageSet.linkResource(xm, EnumUsage.Output, null);
		VString v = new VString();
		v.add(ElementName.EXPOSEDMEDIA);
		final VJDFAttributeMap vMap = new VJDFAttributeMap();
		final JDFAttributeMap map = new JDFAttributeMap();
		map.put("SignatureName", "Sig1");
		vMap.add(map);
		JDFSpawn spawn = new JDFSpawn(nodeImageSet);
		final JDFNode spawnedPSNodeInfo = spawn.spawnInformative(null, null, vMap, false, true, true, true);
		assertEquals("cpi", spawnedPSNodeInfo.getInheritedCustomerInfo("@CustomerProjectID").getCustomerProjectID(), "foo");

		spawn = new JDFSpawn(nodeImageSet);
		final JDFNode spawnedPSNode = spawn.spawn(null, null, v, vMap, false, true, true, true);
		assertEquals("cpi", spawnedPSNode.getInheritedCustomerInfo("@CustomerProjectID").getCustomerProjectID(), "foo");
		// this one spawns the component rw
		v = new VString();
		v.add(ElementName.COMPONENT);
		final JDFExposedMedia xmSpawn = (JDFExposedMedia) spawnedPSNode.getMatchingResource(ElementName.EXPOSEDMEDIA, EnumProcessUsage.AnyOutput, null, 0);
		assertNotNull(xmSpawn);
		final JDFMedia mediaSpawn = xmSpawn.getMedia();
		assertNotNull("The referenced Media gets spawned correctly", mediaSpawn);

		spawn = new JDFSpawn(n);

		final JDFNode spawnedNodeAll = spawn.spawn("thisUrl", "newURL", v, null, false, true, true, true);
		String spawnID = spawnedNodeAll.getSpawnID(false);
		// merge and immediately respawn the same thing
		n = new JDFMerge(n).mergeJDF(spawnedNodeAll, null, EnumCleanUpMerge.RemoveAll, EnumAmountMerge.UpdateLink);
		assertTrue("spawnID gone", nRoot.toString().indexOf(spawnID) < 0);
		spawn = new JDFSpawn(n);

		JDFNode spawnedNode = spawn.spawn("thisUrl", "newURL", v, vMap, false, true, true, true);
		spawnID = spawnedNode.getSpawnID(false);
		assertTrue("AncestorPool", spawnedNode.hasChildElement(ElementName.ANCESTORPOOL, null));

		// merge and immediately respawn the same thing
		n = new JDFMerge(n).mergeJDF(spawnedNode, null, EnumCleanUpMerge.RemoveAll, EnumAmountMerge.UpdateLink);
		assertTrue("spawnID gone", nRoot.toString().indexOf(spawnID) < 0);
		spawn = new JDFSpawn(n);

		spawnedNode = spawn.spawn("thisUrl", "newURL", v, vMap, false, true, true, true);
		assertTrue("AncestorPool after merge", spawnedNode.hasChildElement(ElementName.ANCESTORPOOL, null));

		map.put("SheetName", "S1");
		spawn = new JDFSpawn(spawnedNode);
		final JDFNode respawnedNode = spawn.spawn("reUrl", "renewURL", v, vMap, false, true, true, true);
		assertTrue("AncestorPool after respawn", spawnedNode.hasChildElement(ElementName.ANCESTORPOOL, null));

		xm = (JDFExposedMedia) respawnedNode.getMatchingResource(ElementName.EXPOSEDMEDIA, EnumProcessUsage.AnyInput, null, 0);
		JDFComponent comp = (JDFComponent) respawnedNode.getMatchingResource(ElementName.COMPONENT, EnumProcessUsage.AnyOutput, null, 0);
		VString vSpID = xm.getSpawnIDs(false);
		assertFalse("SpawnIDS", vSpID.isEmpty());
		xm = (JDFExposedMedia) xm.getPartition(map, null);
		assertTrue("xm rw", xm.getLocked());
		comp = (JDFComponent) comp.getPartition(map, null);
		assertFalse("comp rw", comp.getLocked());

		map.put("SheetName", "S2");
		spawn = new JDFSpawn(spawnedNode);
		JDFNode respawnedNode2 = spawn.spawn("reUrl", "renewURL", v, vMap, false, true, true, true);
		xm = (JDFExposedMedia) respawnedNode2.getMatchingResource(ElementName.EXPOSEDMEDIA, EnumProcessUsage.AnyInput, null, 0);
		comp = (JDFComponent) respawnedNode2.getMatchingResource(ElementName.COMPONENT, EnumProcessUsage.AnyOutput, null, 0);
		vSpID = xm.getSpawnIDs(false);
		assertFalse("SpawnIDS", vSpID.isEmpty());
		xm = (JDFExposedMedia) xm.getPartition(map, null);
		assertTrue("xm rw", xm.getLocked());
		comp = (JDFComponent) comp.getPartition(map, null);
		assertFalse("comp rw", comp.getLocked());
		final String spawnID1 = spawnedNode.getSpawnID(false);
		final JDFNode testSpawnedNode = new JDFMerge(spawnedNode).mergeJDF(respawnedNode2, null, EnumCleanUpMerge.None, EnumAmountMerge.UpdateLink);
		assertTrue("AncestorPool after respawn merge", spawnedNode.hasChildElement(ElementName.ANCESTORPOOL, null));
		assertEquals("spawnID ok", spawnID1, testSpawnedNode.getSpawnID(false));
		spawn = new JDFSpawn(spawnedNode);
		respawnedNode2 = spawn.spawn("reUrl", "renewURL", v, vMap, false, true, true, true);

		// now go backwards!
		spawnedNode.getOwnerDocument_JDFElement().write2File(sm_dirTestDataTemp + "multiSpawn_s1.jdf", 2, false);
		new JDFMerge(nRoot).mergeJDF(spawnedNode, null, EnumCleanUpMerge.None, EnumAmountMerge.UpdateLink);
		nRoot.getOwnerDocument_JDFElement().write2File(sm_dirTestDataTemp + "multiSpawn_m1.jdf", 2, false);
		respawnedNode.getOwnerDocument_JDFElement().write2File(sm_dirTestDataTemp + "multiSpawn_s2.jdf", 2, false);
		new JDFMerge(nRoot).mergeJDF(respawnedNode, null, EnumCleanUpMerge.None, EnumAmountMerge.UpdateLink);
		nRoot.getOwnerDocument_JDFElement().write2File(sm_dirTestDataTemp + "multiSpawn_m2.jdf", 2, false);
		respawnedNode2.getOwnerDocument_JDFElement().write2File(sm_dirTestDataTemp + "multiSpawn_s3.jdf", 2, false);
		new JDFMerge(nRoot).mergeJDF(respawnedNode2, null, EnumCleanUpMerge.None, EnumAmountMerge.UpdateLink);
		nRoot.getOwnerDocument_JDFElement().write2File(sm_dirTestDataTemp + "multiSpawn_m3.jdf", 2, false);

		new JDFMerge(nRoot).mergeJDF(spawnedPSNode, null, EnumCleanUpMerge.None, EnumAmountMerge.UpdateLink);
		assertTrue("spawnIDs gone", nRoot.toString().indexOf("SpawnIDs") < 0);
		nRoot.getOwnerDocument_JDFElement().write2File(sm_dirTestDataTemp + "multiSpawn.jdf", 2, false);
		assertTrue(nRoot.isValid(EnumValidationLevel.Incomplete));

	}

	/**
	 * 
	 */
	@Test
	public void testSpawnRWGap()
	{
		JDFNode n = new JDFDoc("JDF").getJDFRoot();
		n.setType(EnumType.ProcessGroup);
		JDFNode n2 = n.addJDFNode(EnumType.ConventionalPrinting);
		VString v = new VString("SignatureName SheetName Side Separation PartVersion", null);
		JDFConventionalPrintingParams cpp = (JDFConventionalPrintingParams) n2.addResource(ElementName.CONVENTIONALPRINTINGPARAMS, EnumUsage.Input);
		JDFComponent comp = (JDFComponent) n2.addResource(ElementName.COMPONENT, EnumUsage.Output);
		JDFNodeInfo ni = n2.getCreateNodeInfo();
		Vector<JDFResource> vr = new Vector<JDFResource>();
		vr.add(ni);
		vr.add(cpp);
		vr.add(comp);
		JDFAttributeMap partmap = new JDFAttributeMap();
		partmap.put("SignatureName", "Sig1");
		partmap.put("SheetName", "S1");
		partmap.put("Side", "Front");
		partmap.put("Separation", "Black");
		for (JDFResource r : vr)
		{
			JDFResource r2 = r.getCreatePartition(partmap, v);
			assertNotNull(r2);
			JDFAttributeMap clone = partmap.clone();
			clone.put("Separation", "Cyan");
			r.getCreatePartition(clone, v);
		}
		partmap.put("PartVersion", "EN");
		ni.getCreatePartition(partmap, v);
		JDFAttributeMap subMap = partmap.clone();
		subMap.removeKeys(new VString("Side Separation ", null));
		subMap.put("PartVersion", "EN");
		JDFSpawn sp = new JDFSpawn(n2);
		VJDFAttributeMap vmap = new VJDFAttributeMap();
		vmap.add(subMap);
		JDFNode nsp = sp.spawn(null, null, new VString("NodeInfo Output", null), vmap, false, true, true, true);
		JDFComponent co = (JDFComponent) nsp.getResource(ElementName.COMPONENT, null, 0);
		co.getCreatePartition(partmap, v);
		co.expand(true);
		JDFMerge m = new JDFMerge(n);
		JDFNode out = m.mergeJDF(nsp);
		assertNotNull(out);
	}

	/**
	 * 
	 */
	@Test
	public void testSpawnPartChain()
	{
		for (int i = 0; i < 3; i++)
		{
			final JDFDoc dRoot = new JDFDoc("JDF");
			final JDFNode nRoot = dRoot.getJDFRoot();
			final JDFCustomerInfo ci = nRoot.appendCustomerInfo();
			ci.setCustomerProjectID("foo");

			nRoot.setType("Product", true);

			final JDFDoc d = JDFTestCaseBase.creatXMDoc();
			final JDFNode n = (JDFNode) nRoot.copyElement(d.getJDFRoot(), null);
			JDFExposedMedia xm = (JDFExposedMedia) n.getMatchingResource(ElementName.EXPOSEDMEDIA, EnumProcessUsage.AnyInput, null, 0);
			final JDFMedia media = xm.getMedia();
			media.makeRootResource("mediaID", nRoot, true);
			final JDFResourcePool rp = nRoot.getCreateResourcePool();
			rp.moveElement(xm, null);
			rp.moveElement(n.getMatchingResource(ElementName.COMPONENT, EnumProcessUsage.AnyOutput, null, 0), null);

			final VJDFAttributeMap vMap = new VJDFAttributeMap();
			JDFAttributeMap map = new JDFAttributeMap();
			map.put("SignatureName", "Sig1");
			vMap.add(map);
			final VString v = new VString();
			v.add(ElementName.COMPONENT);
			JDFSpawn spawn = new JDFSpawn(n);

			final JDFNode spawnedNode = spawn.spawn("thisUrl", "newURL", v, vMap, false, true, true, true);
			nRoot.getOwnerDocument_JDFElement().write2File(sm_dirTestDataTemp + "chainSpawn_main0.jdf", 2, false);
			JDFComponent coSig = (JDFComponent) spawnedNode.getResource(ElementName.COMPONENT, EnumUsage.Output, 0);
			coSig = (JDFComponent) coSig.getPartition(map, null);
			assertNotNull(coSig);
			JDFNodeInfo niSig = (JDFNodeInfo) spawnedNode.getResource(ElementName.NODEINFO, EnumUsage.Input, 0);
			niSig = (JDFNodeInfo) niSig.getPartition(map, null);
			assertNotNull(niSig);
			JDFExposedMedia xmSig = (JDFExposedMedia) spawnedNode.getResource(ElementName.EXPOSEDMEDIA, EnumUsage.Input, 0);
			xmSig = (JDFExposedMedia) xmSig.getPartition(map, null);
			assertNotNull(xmSig);
			for (int j = 3; j < 9; j++)
			{
				coSig.addPartition(EnumPartIDKey.SheetName, "S" + j);
				niSig.addPartition(EnumPartIDKey.SheetName, "S" + j);
				xmSig.addPartition(EnumPartIDKey.SheetName, "S" + j);
			}
			// String spawnID=
			spawnedNode.getSpawnID(false);
			assertTrue("AncestorPool", spawnedNode.hasChildElement(ElementName.ANCESTORPOOL, null));

			map.put("SheetName", "S1");
			for (int j = 2; j < 9; j++)
			{
				final JDFAttributeMap map2 = new JDFAttributeMap(map);
				map2.put("SheetName", "S" + j);
				vMap.add(map2);
			}

			JDFNode respawnedNode = spawnedNode;
			final JDFNode[] nodes = new JDFNode[9];
			nodes[0] = spawnedNode;
			for (int j = 1; j < 9; j++)
			{
				System.out.println("Spawn" + j);
				spawn = new JDFSpawn(respawnedNode);
				nodes[j] = respawnedNode = spawn.spawn("reUrl1" + j, "renewURL1" + j, v, vMap, false, true, true, true);
				assertTrue("AncestorPool after respawn", respawnedNode.hasChildElement(ElementName.ANCESTORPOOL, null));
				assertEquals("AncestorPool after respawn", respawnedNode.getAncestorPool().getPartMapVector(), vMap);

				xm = (JDFExposedMedia) respawnedNode.getMatchingResource(ElementName.EXPOSEDMEDIA, EnumProcessUsage.AnyInput, null, 0);
				JDFComponent comp = (JDFComponent) respawnedNode.getMatchingResource(ElementName.COMPONENT, EnumProcessUsage.AnyOutput, null, 0);
				map = vMap.elementAt(0);
				final VString vSpID = xm.getSpawnIDs(false);
				assertFalse("SpawnIDS", vSpID.isEmpty());
				xm = (JDFExposedMedia) xm.getPartition(map, null);
				assertTrue("xm rw", xm.getLocked());
				comp = (JDFComponent) comp.getCreatePartition(map, null);
				comp.setResStatus(EnumResStatus.Available, false);
				assertFalse("comp rw", comp.getLocked());
				vMap.removeElementAt(0);
				respawnedNode.getOwnerDocument_JDFElement().write2File(sm_dirTestDataTemp + "chainSpawn_s" + j + ".jdf", 2, false);
				if (j > 1)
				{
					nodes[j - 1].getOwnerDocument_JDFElement().write2File(sm_dirTestDataTemp + "chainSpawn_ms" + j + ".jdf", 2, false);
				}
			}
			for (int j = 0; j < 9; j++)
			{

				System.out.println("Merge" + j);
				final JDFNode nodeJ = nodes[j];
				final EnumCleanUpMerge enumCleanUpMerge = EnumCleanUpMerge.getEnum(i);
				// JDFNode overwritten=
				new JDFMerge(nRoot).mergeJDF(nodeJ, null, enumCleanUpMerge, EnumAmountMerge.UpdateLink);

				assertEquals(nRoot.getChildrenByTagName(null, null, new JDFAttributeMap(AttributeName.ID, xm.getID()), false, true, 99, false).size(), 1);
				assertTrue(nRoot.isValid(EnumValidationLevel.Incomplete));
				nRoot.getOwnerDocument_JDFElement().write2File(sm_dirTestDataTemp + "chainSpawn_m" + j + ".jdf", 2, false);

			}

			assertTrue("spawnIDs gone", nRoot.toString().indexOf("SpawnIDs") < 0);

		}
	}

	/**
	 * test wierd sequences of spawning and merging with the same res being spawned both rw and ro
	 * 
	 */
	@Test
	public void testSpawnPartMultiRORW()
	{
		for (int i = 0; i < 2; i++) // partitioned or not
		{
			VJDFAttributeMap vMap = new VJDFAttributeMap();
			final JDFAttributeMap map = new JDFAttributeMap();
			map.put("SignatureName", "Sig1");
			vMap.add(map);
			if (i == 1)
			{
				vMap = null; // unpartitioned
			}
			for (int j = 0; j < 4; j++) // rw or ro first
			{
				for (int k = 0; k < 2; k++) // copy local or copy from high level
				{
					for (int kk = 0; kk < 2; kk++) // reslinks have part map
					{
						final JDFDoc dRoot = new JDFDoc("JDF");
						final JDFNode nRoot = dRoot.getJDFRoot();

						nRoot.setType("Product", true);

						final JDFDoc d = JDFTestCaseBase.creatXMDoc();
						JDFNode n = (JDFNode) nRoot.copyElement(d.getJDFRoot(), null);
						if (k > 0)
						{
							nRoot.moveElement(n.getResourcePool(), null);
						}

						JDFExposedMedia xm = (JDFExposedMedia) n.getMatchingResource(ElementName.EXPOSEDMEDIA, EnumProcessUsage.AnyInput, null, 0);
						JDFExposedMedia xmPart = (JDFExposedMedia) xm.getPartition(map, null);
						final JDFMedia media = xm.getMedia();
						media.makeRootResource("mediaID", n, true);
						final JDFResourceLink rlXM = n.getLink(xm, null);
						if (kk > 0)
						{
							final VJDFAttributeMap vMap2 = new VJDFAttributeMap();
							final JDFAttributeMap mapBad = new JDFAttributeMap("SignatureName", "Sig2");
							vMap2.add(mapBad);
							vMap2.add(map);
							rlXM.setPartMapVector(vMap2);
						}

						final VString vRWRes = new VString();
						if (j < 2)
						{
							vRWRes.add(ElementName.EXPOSEDMEDIA);
						}

						JDFSpawn spawn = new JDFSpawn(n);
						final JDFNode spawnedNodeXMRW = spawn.spawn(null, null, vRWRes, vMap, false, true, true, true);

						// this one spawns the component rw
						vRWRes.clear();
						final JDFExposedMedia xmSpawn = (JDFExposedMedia) spawnedNodeXMRW.getMatchingResource(ElementName.EXPOSEDMEDIA, EnumProcessUsage.AnyInput, null, 0);
						assertNotNull(xmSpawn);
						assertEquals(xmPart.getSpawnStatus(), j < 2 ? EnumSpawnStatus.SpawnedRW : EnumSpawnStatus.SpawnedRO);
						if (j >= 2)
						{
							vRWRes.add(ElementName.EXPOSEDMEDIA);
						}

						spawn = new JDFSpawn(n);

						final JDFNode spawnedNodeXMRO = spawn.spawn("thisUrl", "newURL", vRWRes, vMap, false, true, true, true);
						// merge and immediately respawn the same thing
						final JDFNode s1 = j % 2 == 0 ? spawnedNodeXMRO : spawnedNodeXMRW;
						final String spawnIDRO = s1.getSpawnID(false);
						final JDFNode s2 = j % 2 == 1 ? spawnedNodeXMRO : spawnedNodeXMRW;
						final String spawnIDRW = s2.getSpawnID(false);
						// n=new JDFMerge(nRoot).mergeJDF(s1, null,
						// EnumCleanUpMerge.None, EnumAmountMerge.UpdateLink);
						n = new JDFMerge(nRoot).mergeJDF(s1, null, EnumCleanUpMerge.RemoveAll, EnumAmountMerge.UpdateLink);
						assertTrue("spawnID RO gone", nRoot.toString().indexOf(spawnIDRO) < 0);
						assertTrue("spawnID RW not gone", nRoot.toString().indexOf(spawnIDRW) >= 0);
						xm = (JDFExposedMedia) n.getMatchingResource(ElementName.EXPOSEDMEDIA, EnumProcessUsage.AnyInput, null, 0);
						xmPart = (JDFExposedMedia) xm.getPartition(map, null);
						assertEquals(i + " " + j, xmPart.getSpawnStatus(), j == 0 || j == 3 ? EnumSpawnStatus.SpawnedRW : EnumSpawnStatus.SpawnedRO);

						// merge and immediately respawn the same thing
						n = new JDFMerge(nRoot).mergeJDF(s2, null, EnumCleanUpMerge.RemoveAll, EnumAmountMerge.UpdateLink);
						// n=new JDFMerge(nRoot).mergeJDF(s2, null,
						// EnumCleanUpMerge.None, EnumAmountMerge.UpdateLink);
						assertTrue("spawnID gone " + i + " " + j + " " + k + " " + kk, nRoot.toString().indexOf(spawnIDRW) < 0);
						assertTrue("spawnIDs gone " + i + " " + j + " " + k + " " + kk, nRoot.toString().indexOf("SpawnIDs") < 0);
						xm = (JDFExposedMedia) n.getMatchingResource(ElementName.EXPOSEDMEDIA, EnumProcessUsage.AnyInput, null, 0);
						xmPart = (JDFExposedMedia) xm.getPartition(map, null);
						assertEquals(xmPart.getSpawnStatus(), EnumSpawnStatus.NotSpawned);
					}
				}
			}
		}
	}

	/**
	 * 
	 */
	@Test
	public void testCleanSpawnedResources()
	{
		final JDFDoc doc = new JDFDoc("JDF");
		final JDFNode n = doc.getJDFRoot();
		n.setType(EnumType.Folding);
		// final JDFFoldingParams fp = (JDFFoldingParams)
		n.addResource(ElementName.FOLDINGPARAMS, EnumUsage.Output);
		final JDFAttributeMap m = new JDFAttributeMap("SignatureName", "Sig1");

		JDFSpawn spawn = new JDFSpawn(n);
		final VJDFAttributeMap vMap = new VJDFAttributeMap();
		vMap.add(m);
		// JDFNode nsp =
		spawn.spawn("thisUrl", "newURL", new VString("Output", null), vMap, true, true, true, true);

		spawn = new JDFSpawn(n);
		try
		{
			spawn.spawn("thisUrl", "newURL", new VString("Output", null), vMap, true, true, true, true);
			fail("twice is bad!");
		}
		catch (final JDFException x)
		{
			// nop
		}
		spawn = new JDFSpawn(n);
		spawn.vRWResources_in = new VString("Output", null);
		spawn.vSpawnParts = vMap;
		spawn.cleanSpawnedResources();
		// nsp =
		spawn.spawn("thisUrl", "newURL", new VString("Output", null), vMap, true, true, true, true);
	}

	/**
	 * test whether getpartition works for when inconsistently called
	 */
	@Test
	public void testSpawnInconsistentPart()
	{
		final JDFDoc doc = new JDFDoc("JDF");
		final JDFNode n = doc.getJDFRoot();
		n.setType(EnumType.Folding);
		final JDFFoldingParams fp = (JDFFoldingParams) n.addResource(ElementName.FOLDINGPARAMS, null, EnumUsage.Input, null, null, null, null);
		final JDFAttributeMap m = new JDFAttributeMap("SignatureName", "Sig1");
		final JDFNodeInfo ni = (JDFNodeInfo) n.addResource(ElementName.NODEINFO, null, EnumUsage.Input, null, null, null, null);
		m.put("SheetName", "Sheet1");
		final JDFResource rSheet = fp.getCreatePartition(m, new VString("SignatureName SheetName", " "));
		final JDFResourceLink rl = n.getLink(fp, null);
		rl.setPartMap(m);
		m.put("BlockName", "Block1");
		JDFResource r = fp.getCreatePartition(m, new VString("SignatureName SheetName BlockName", " "));
		m.put("BlockName", "Block2");
		r = fp.getCreatePartition(m, new VString("SignatureName SheetName BlockName", " "));
		final JDFAttributeMap m2 = new JDFAttributeMap("SignatureName", "Sig1");
		m2.put("SheetName", "Sheet1");
		m2.put("Side", "Front");
		ni.getCreatePartition(m2, new VString("SignatureName SheetName Side", " "));
		r = fp.getPartition(m2, EnumPartUsage.Implicit);
		assertEquals(r, rSheet);
		r = fp.getPartition(m2, EnumPartUsage.Explicit);
		assertNull(r);
		final JDFSpawn spawn = new JDFSpawn(n); // fudge to test output counting
		final VJDFAttributeMap vMap = new VJDFAttributeMap();
		vMap.add(m2);
		spawn.spawn("thisUrl", "newURL", null, vMap, true, true, true, true);

	}

	/**
	 * test whether getpartition works for when inconsistently called
	 */
	@Test
	public void testSpawnNoPart()
	{
		// 0: resources are local, 1 resources ore global
		for (int i = 0; i < 2; i++)
		{
			// 0: no nodeinfo, 1 with nodeinfo
			for (int j = 0; j < 2; j++)
			{
				final JDFDoc doc = new JDFDoc("JDF");
				JDFNode n = doc.getJDFRoot();
				n.setType(EnumType.ProcessGroup);
				n = n.addJDFNode(EnumType.ProcessGroup);
				final JDFNode n2 = n.addJDFNode(EnumType.ImageSetting);
				final JDFResource xm = n2.addResource("ExposedMedia", EnumUsage.Input);
				final JDFResource m = n.addResource("Media", null);
				xm.refElement(m);
				JDFNode nodeToSpawn = i == 1 ? n : n2;
				if (j == 1)
				{
					JDFNodeInfo ni = (JDFNodeInfo) nodeToSpawn.addResource("NodeInfo", EnumUsage.Input);
					ni.setNodeStatus(EnumNodeStatus.Waiting);
					nodeToSpawn.setStatus(EnumNodeStatus.Part);
				}
				final JDFSpawn spawn = new JDFSpawn(nodeToSpawn);
				final JDFNode si = spawn.spawn();
				assertNotNull(si.getResourcePool().getElement("Media"));
				assertEquals(nodeToSpawn.getPartStatus(null, 0), EnumNodeStatus.Spawned);
				assertEquals(((JDFSpawned) nodeToSpawn.getParentJDF().getAuditPool().getAudit(0, EnumAuditType.Spawned, null, null)).getStatus(), EnumNodeStatus.Waiting);
			}
		}
	}

	/**
	 * 
	 */
	@Test
	public void testSpawnMixPart()
	{
		for (int i = 0; i < 2; i++)
		{
			final JDFDoc doc = new JDFDoc("JDF");
			JDFNode root = doc.getJDFRoot();
			root.setType(EnumType.Imposition);
			final JDFRunList rl = (JDFRunList) root.addResource(ElementName.RUNLIST, null, EnumUsage.Input, null, null, null, null);
			final JDFRunList rlEn = (JDFRunList) rl.addPartition(EnumPartIDKey.PartVersion, "EN");
			rlEn.addPDF("En.pdf", 0, -1);
			final JDFRunList rlDe = (JDFRunList) rl.addPartition(EnumPartIDKey.PartVersion, "DE");
			rlDe.addPDF("De.pdf", 0, -1);

			final JDFRunList rlOut = (JDFRunList) root.addResource(ElementName.RUNLIST, null, EnumUsage.Output, null, null, null, null);
			for (int j = 0; j < 4; j++)
			{
				final JDFRunList rlS1 = (JDFRunList) rlOut.addPartition(EnumPartIDKey.SheetName, "S" + j);
				final JDFRunList rlS1F = (JDFRunList) rlS1.addPartition(EnumPartIDKey.Side, "Front");
				rlS1F.addPartition(EnumPartIDKey.PartVersion, "EN");
				rlS1F.addPartition(EnumPartIDKey.PartVersion, "DE");

				rlS1.addPartition(EnumPartIDKey.Side, "Back");
			}

			final JDFSpawn spawn = new JDFSpawn(root);
			spawn.vSpawnParts = new VJDFAttributeMap();
			spawn.vSpawnParts.add(new JDFAttributeMap(EnumPartIDKey.PartVersion, "EN"));
			spawn.vRWResources_in = new VString("Output", null);
			spawn.bFixResources = i == 0;

			final JDFNode spawnedNode = spawn.spawn();

			assertNotNull(spawnedNode);

			final JDFRunList rlOutSpawn = (JDFRunList) spawnedNode.getMatchingResource(ElementName.RUNLIST, EnumProcessUsage.AnyOutput, null, 0);
			VElement vOut = rlOutSpawn.getPartitionVector(new JDFAttributeMap(EnumPartIDKey.PartVersion, "DE"), null);
			// assertEquals(vOut.size(), 0);
			vOut = rlOutSpawn.getPartitionVector(new JDFAttributeMap(EnumPartIDKey.PartVersion, "EN"), null);
			assertEquals(vOut.size(), 4);
			for (int j = 0; j < vOut.size(); j++)
			{
				((JDFResource) vOut.item(j)).setResStatus(EnumResStatus.Available, false);
			}

			final JDFMerge merge = new JDFMerge(root);
			root = merge.mergeJDF(spawnedNode, null, EnumCleanUpMerge.None, EnumAmountMerge.UpdateLink);
			assertNotNull(root);
			final JDFRunList rlOutMerge = (JDFRunList) root.getMatchingResource(ElementName.RUNLIST, EnumProcessUsage.AnyOutput, null, 0);
			vOut = rlOutMerge.getPartitionVector(new JDFAttributeMap(EnumPartIDKey.PartVersion, "DE"), null);
			assertEquals(vOut.size(), 4);

			vOut = rlOutMerge.getPartitionVector(new JDFAttributeMap(EnumPartIDKey.PartVersion, "EN"), null);
			assertEquals(vOut.size(), 4);
			for (int j = 0; j < vOut.size(); j++)
			{
				assertEquals("bad status: " + j, ((JDFResource) vOut.item(j)).getResStatus(false), EnumResStatus.Available);
			}
			vOut = rlOutMerge.getPartitionVector(new JDFAttributeMap(EnumPartIDKey.PartVersion, "DE"), null);
			assertEquals(vOut.size(), 4);
			for (int j = 0; j < vOut.size(); j++)
			{
				assertEquals(((JDFResource) vOut.item(j)).getResStatus(false), EnumResStatus.Unavailable);
			}
		}
	}

	// /////////////////////////////////////////////////////////
	/**
	 * 
	 */
	@Test
	public void testSpawnIntermediateMissing()
	{
		final JDFDoc d = new JDFDoc("JDF");
		final JDFNode n = d.getJDFRoot();
		n.setType(EnumType.ProcessGroup);
		final JDFNode n2 = n.addJDFNode(EnumType.PreviewGeneration);
		// final JDFMedia m = (JDFMedia)
		final JDFPreview pv = (JDFPreview) n2.addResource(ElementName.PREVIEW, null, EnumUsage.Output, null, n, null, null);
		JDFResource rp = pv.addPartition(EnumPartIDKey.SignatureName, "s1");
		rp = rp.addPartition(EnumPartIDKey.SheetName, "s1");
		rp = rp.addPartition(EnumPartIDKey.Side, "Front");
		rp = rp.addPartition(EnumPartIDKey.Separation, "Black");
		rp = rp.addPartition(EnumPartIDKey.PartVersion, "EN");
		final JDFNodeInfo ni = n2.appendNodeInfo();
		ni.setPartIDKeys(new VString("SignatureName SheetName Side Separation PartVersion", null));

		final JDFAttributeMap map = new JDFAttributeMap();
		map.put(EnumPartIDKey.SignatureName, "s1");
		map.put(EnumPartIDKey.SheetName, "s1");
		map.put(EnumPartIDKey.Side, "Front");
		map.put(EnumPartIDKey.PartVersion, "EN");
		final VJDFAttributeMap vMap = new VJDFAttributeMap();
		vMap.add(map);

		final JDFAttributeMap mapNI = new JDFAttributeMap(map);
		mapNI.put(EnumPartIDKey.Separation, "Black");
		ni.getCreatePartition(mapNI, null);

		final JDFSpawn spawn = new JDFSpawn(n2);
		spawn.bFixResources = false;
		spawn.vRWResources_in = new VString("Output", null);
		spawn.vSpawnParts = vMap;
		spawn.bSpawnRWPartsMultiple = true;

		final JDFNode nS1 = spawn.spawn();
		assertNotNull(nS1);
		final JDFPreview pvs = (JDFPreview) nS1.getResource(ElementName.PREVIEW, EnumUsage.Output, 0);
		assertNotNull(pvs);
		final JDFResourceLink rl = nS1.getLink(0, ElementName.PREVIEW, null, null);
		assertNotNull(rl);
		assertEquals("EN", rl.getTarget().getPartMap().get("PartVersion"));
	}

	/**
	 * 
	 */
	@Test
	public void testSpawnPartMissing()
	{
		for (int loop = 0; loop < 2; loop++)
		{
			final JDFDoc d = new JDFDoc("JDF");
			final JDFNode n = d.getJDFRoot();
			n.setType(EnumType.ProcessGroup);
			final JDFNode n2 = n.addJDFNode(EnumType.ConventionalPrinting);
			// final JDFMedia m = (JDFMedia)
			n2.addResource(ElementName.MEDIA, null, EnumUsage.Input, null, n, null, null);

			final JDFComponent comp = (JDFComponent) n2.addResource(ElementName.COMPONENT, null, EnumUsage.Output, null, n, null, null);
			JDFComponent cs = (JDFComponent) comp.addPartition(EnumPartIDKey.SheetName, "S1");
			// final JDFComponent csEn = (JDFComponent)
			cs.addPartition(EnumPartIDKey.PartVersion, "EN");
			// final JDFComponent csFr = (JDFComponent)
			cs.addPartition(EnumPartIDKey.PartVersion, "FR");

			final JDFAttributeMap map = new JDFAttributeMap();
			map.put(EnumPartIDKey.SheetName, "S1");
			map.put(EnumPartIDKey.PartVersion, loop == 0 ? "DE" : "EN");
			final VJDFAttributeMap vMap = new VJDFAttributeMap();
			vMap.add(map);
			JDFNodeInfo ni = n2.getCreateNodeInfo();
			ni.clonePartitions(comp, null);

			final JDFSpawn spawn = new JDFSpawn(n2);
			spawn.bFixResources = false;
			spawn.vRWResources_in = new VString("Output", null);
			spawn.vSpawnParts = vMap;
			spawn.bSpawnRWPartsMultiple = true;

			final JDFNode nS1 = spawn.spawn();
			assertNotNull(nS1);
			final JDFComponent c = (JDFComponent) nS1.getResource(ElementName.COMPONENT, EnumUsage.Output, 0);
			final JDFResourceLink rl = nS1.getLink(0, ElementName.COMPONENT, null, null);
			assertNotNull(rl);
			if (loop == 0)
			{
				assertNull(rl.getTarget());
			}
			else
			{
				assertEquals("EN", rl.getTarget().getPartMap().get("PartVersion"));
				assertEquals(rl.getTarget(), c);
			}
		}
	}

	/**
	* 
	*/
	@Test
	public void testSpawnSeparationMissing()
	{
		final JDFDoc d = new JDFDoc("JDF");
		final JDFNode n = d.getJDFRoot();
		n.setType(EnumType.ProcessGroup);
		final JDFNode n2 = n.addJDFNode(EnumType.ConventionalPrinting);
		// final JDFMedia m = (JDFMedia)
		n2.addResource(ElementName.MEDIA, null, EnumUsage.Input, null, n, null, null);

		final JDFComponent comp = (JDFComponent) n2.addResource(ElementName.COMPONENT, null, EnumUsage.Output, null, n, null, null);
		JDFComponent cs0 = (JDFComponent) comp.addPartition(EnumPartIDKey.SheetName, "S1");
		JDFComponent cs = (JDFComponent) cs0.addPartition(EnumPartIDKey.Separation, "Black");
		// final JDFComponent csEn = (JDFComponent)
		cs.addPartition(EnumPartIDKey.PartVersion, "EN");
		// final JDFComponent csFr = (JDFComponent)
		cs.addPartition(EnumPartIDKey.PartVersion, "FR");
		JDFComponent cs1 = (JDFComponent) cs0.addPartition(EnumPartIDKey.Separation, "Cyan");
		// final JDFComponent csEn = (JDFComponent)
		cs1.addPartition(EnumPartIDKey.PartVersion, "EN");
		// final JDFComponent csFr = (JDFComponent)
		cs1.addPartition(EnumPartIDKey.PartVersion, "FR");

		final JDFAttributeMap map = new JDFAttributeMap();
		map.put(EnumPartIDKey.SheetName, "S1");
		map.put(EnumPartIDKey.PartVersion, "EN");
		final VJDFAttributeMap vMap = new VJDFAttributeMap();
		vMap.add(map);
		JDFNodeInfo ni = n2.getCreateNodeInfo();
		ni.clonePartitions(comp, null);

		final JDFSpawn spawn = new JDFSpawn(n2);
		spawn.bFixResources = false;
		spawn.vRWResources_in = new VString("Output", null);
		spawn.vSpawnParts = vMap;
		spawn.bSpawnRWPartsMultiple = true;

		final JDFNode nS1 = spawn.spawn();
		assertNotNull(nS1);
		final JDFComponent c = (JDFComponent) nS1.getResource(ElementName.COMPONENT, EnumUsage.Output, 0);
		assertNotNull(c);
		final JDFResourceLink rl = nS1.getLink(0, ElementName.COMPONENT, null, null);
		assertNotNull(rl);
		assertEquals("EN", ((JDFResource) rl.getTargetVector(-1).get(0)).getPartMap().get("PartVersion"));
		assertEquals("EN", ((JDFResource) rl.getTargetVector(-1).get(1)).getPartMap().get("PartVersion"));
	}

	/**
	 * 
	 */
	@Test
	public void testSpawnPartPVVariation()
	{
		for (int loop1 = 0; loop1 < 2; loop1++)
		{
			for (int loop = 0; loop < 2; loop++)
			{
				final JDFDoc d = new JDFDoc("JDF");
				final JDFNode n = d.getJDFRoot();
				n.setType(EnumType.ProcessGroup);
				final JDFNode n2 = n.addJDFNode(EnumType.ConventionalPrinting);
				// final JDFMedia m = (JDFMedia)
				n2.addResource(ElementName.MEDIA, null, EnumUsage.Input, null, n, null, null);

				final JDFComponent comp = (JDFComponent) n2.addResource(ElementName.COMPONENT, null, EnumUsage.Output, null, n, null, null);
				final JDFComponent cs = (JDFComponent) comp.addPartition(EnumPartIDKey.SheetName, "S1");
				final JDFAttributeMap map = new JDFAttributeMap();
				map.put(EnumPartIDKey.SheetName, "S1");
				if (loop1 == 0)
				{
					cs.addPartition(EnumPartIDKey.PartVersion, "EN EN");
					cs.addPartition(EnumPartIDKey.PartVersion, "FR FR");
					map.put(EnumPartIDKey.PartVersion, loop == 0 ? "DE " : "EN");
				}
				else
				{
					cs.addPartition(EnumPartIDKey.PartVersion, "EN EN DE DE");
					cs.addPartition(EnumPartIDKey.PartVersion, "FR FR DE DE");
					map.put(EnumPartIDKey.PartVersion, loop == 0 ? "DE DE" : "EN EN DE DE");
				}

				final VJDFAttributeMap vMap = new VJDFAttributeMap();
				vMap.add(map);

				final JDFSpawn spawn = new JDFSpawn(n2);
				spawn.bSpawnROPartsOnly = false;
				spawn.bFixResources = false;
				spawn.vRWResources_in = new VString("Output", null);
				spawn.vSpawnParts = vMap;
				spawn.bSpawnRWPartsMultiple = true;

				final JDFNode nS1 = spawn.spawn();
				assertNotNull(nS1);
				final JDFComponent c = (JDFComponent) nS1.getResource(ElementName.COMPONENT, EnumUsage.Output, 0);
				final JDFResourceLink rl = nS1.getLink(0, ElementName.COMPONENT, null, null);
				assertNotNull(rl);
				if (loop == 0)
				{
					assertNull(rl.getTarget());
				}
				else
				{
					assertEquals((loop1 == 0) ? "EN EN" : "EN EN DE DE", rl.getTarget().getPartMap().get("PartVersion"));
					assertEquals(-1, rl.getLinkRoot().toXML().indexOf("FR FR"));
					assertEquals(rl.getTarget(), c);
				}
			}
		}
	}

	/**
	 * 
	 */
	@Test
	public void testSpawnPartParallel()
	{
		JDFNode n2 = createSpawnMain(0);
		JDFAttributeMap map = new JDFAttributeMap();
		map.put(EnumPartIDKey.SignatureName, "sig1");
		map.put(EnumPartIDKey.SheetName, "sh1");
		map.put(EnumPartIDKey.Side, EnumSide.Front);
		map.put(EnumPartIDKey.PartVersion, "a b");
		final VJDFAttributeMap vMap = new VJDFAttributeMap();
		vMap.add(map);
		map = new JDFAttributeMap(map);
		map.put(EnumPartIDKey.Side, EnumSide.Back);
		vMap.add(map);

		final JDFSpawn spawn = new JDFSpawn(n2);
		spawn.bFixResources = false;
		spawn.vRWResources_in = new VString("Output", null);
		spawn.vSpawnParts = vMap;
		spawn.bSpawnRWPartsMultiple = true;
		JDFNodeInfo niMain = n2.getNodeInfo();
		VElement vnipMain = niMain.getPartitionVector(vMap, null);
		for (int i = 0; i < vnipMain.size(); i++)
		{
			((JDFResource) vnipMain.get(i)).addPartition(EnumPartIDKey.PartVersion, "a b");
		}

		final JDFNode nS1 = spawn.spawn();
		assertNotNull(nS1);
		JDFNode parentJDF = n2.getParentJDF();
		JDFNodeInfo ni = nS1.getNodeInfo();
		VElement vnip = ni.getPartitionVector(vMap, null);
		vnip = ni.getPartitionVector(vMap, null);
		assertEquals(vnip.size(), 2);
		vnipMain = niMain.getPartitionVector(vMap, null);
		assertEquals(vnipMain.size(), 2);
		for (int i = 0; i < vnip.size(); i++)
		{
			JDFNodeInfo nip = (JDFNodeInfo) vnip.get(i);
			assertEquals(nip.getSpawnIDs(false).size(), 1);
			nip = (JDFNodeInfo) vnipMain.get(i);
			assertEquals(nip.getSpawnIDs(false).size(), 1);
		}
		JDFMerge m = new JDFMerge(parentJDF);
		JDFNode n = m.mergeJDF(nS1, null, EnumCleanUpMerge.RemoveAll, null);
		assertEquals(n.toXML().indexOf("SpawnID"), -1);
	}

	/**
	 * 
	 */
	@Test
	public void testSpawnPartNoSide()
	{
		for (int l = 0; l < 2; l++)
		{
			JDFNode n2 = createSpawnMain(l);
			final JDFAttributeMap map = new JDFAttributeMap();
			map.put(EnumPartIDKey.SignatureName, "sig1");
			if (l == 0)
			{
				map.put(EnumPartIDKey.SheetName, "sh1");
			}
			map.put(EnumPartIDKey.Side, EnumSide.Front);
			final VJDFAttributeMap vMap = new VJDFAttributeMap();
			vMap.add(map);

			final JDFSpawn spawn = new JDFSpawn(n2);
			spawn.bFixResources = false;
			spawn.vRWResources_in = new VString("Output", null);
			spawn.vSpawnParts = vMap;
			spawn.bSpawnRWPartsMultiple = true;

			final JDFNode nS1 = spawn.spawn();
			assertNotNull(nS1);
			nS1.setXPathAttribute("./ResourcePool/Component/Component/Component[@SheetName=\"sh1\"]/@foo", "fnarf");
			map.put(EnumPartIDKey.Side, EnumSide.Back);
			final JDFNode nS2 = spawn.spawn();
			assertNotNull(nS2);

			nS2.setXPathAttribute("./ResourcePool/Component/Component/Component[@SheetName=\"sh1\"]/@foo", "bar");

			JDFNode n = n2.getParentJDF();
			final JDFMerge merge = new JDFMerge(n);
			merge.mergeJDF(nS1, null, EnumCleanUpMerge.None, EnumAmountMerge.None);
			assertEquals(n.getXPathAttribute("./ResourcePool/Component/Component/Component[@SheetName=\"sh1\"]/@foo", null), "fnarf");
			merge.mergeJDF(nS2, null, EnumCleanUpMerge.None, EnumAmountMerge.None);
			assertEquals(n.getXPathAttribute("./ResourcePool/Component/Component/Component[@SheetName=\"sh1\"]/@foo", null), "bar");
		}
	}

	/**
	 * @param l
	 * @return
	 */
	private JDFNode createSpawnMain(int l)
	{
		final JDFDoc d = new JDFDoc("JDF");
		final JDFNode n = d.getJDFRoot();
		n.setType(EnumType.ProcessGroup);
		final JDFNode n2 = n.addJDFNode(EnumType.ConventionalPrinting);
		JDFLayout lo = (JDFLayout) n2.addResource("Layout", null, EnumUsage.Input, null, n, null, null);
		JDFConventionalPrintingParams cpp = (JDFConventionalPrintingParams) n2.addResource(ElementName.CONVENTIONALPRINTINGPARAMS, null, EnumUsage.Input, null, n, null, null);
		JDFComponent comp = (JDFComponent) n2.addResource(ElementName.COMPONENT, null, EnumUsage.Output, null, n, null, null);
		JDFNodeInfo ni = (JDFNodeInfo) n2.addResource(ElementName.NODEINFO, null, EnumUsage.Input, null, null, null, null);

		lo = (JDFLayout) lo.addPartition(EnumPartIDKey.SignatureName, "sig1");
		cpp = (JDFConventionalPrintingParams) cpp.addPartition(EnumPartIDKey.SignatureName, "sig1");
		comp = (JDFComponent) comp.addPartition(EnumPartIDKey.SignatureName, "sig1");
		ni = (JDFNodeInfo) ni.addPartition(EnumPartIDKey.SignatureName, "sig1");
		for (int i = 0; i < 2; i++)
		{
			final JDFLayout lo2 = (JDFLayout) lo.addPartition(EnumPartIDKey.SheetName, "sh" + i);
			cpp.addPartition(EnumPartIDKey.SheetName, "sh" + i);
			comp.addPartition(EnumPartIDKey.SheetName, "sh" + i);
			lo2.addPartition(EnumPartIDKey.Side, EnumSide.Front);
			lo2.addPartition(EnumPartIDKey.Side, EnumSide.Back);
			final JDFNodeInfo ni2 = l == 0 ? (JDFNodeInfo) ni.addPartition(EnumPartIDKey.SheetName, "sh" + i) : ni;
			if (l == 0 || i == 0)
			{
				ni2.addPartition(EnumPartIDKey.Side, EnumSide.Front);
				ni2.addPartition(EnumPartIDKey.Side, EnumSide.Back);
			}

		}
		return n2;
	}

	/**
	 * 
	 */
	@Test
	public void testSpawnPart2Side()
	{
		final JDFDoc d = new JDFDoc("JDF");
		final JDFNode n = d.getJDFRoot();
		n.setType(EnumType.ProcessGroup);
		final JDFNode n2 = n.addJDFNode(EnumType.ConventionalPrinting);
		JDFComponent comp = (JDFComponent) n2.addResource(ElementName.COMPONENT, null, EnumUsage.Output, null, n, null, null);
		JDFNodeInfo ni = n2.getCreateNodeInfo();
		comp = (JDFComponent) comp.addPartition(EnumPartIDKey.SignatureName, "sig1");
		ni = (JDFNodeInfo) ni.addPartition(EnumPartIDKey.SignatureName, "sig1");
		for (int i = 0; i < 2; i++)
		{
			final JDFComponent c2 = (JDFComponent) comp.addPartition(EnumPartIDKey.SheetName, "sh" + i);
			c2.addPartition(EnumPartIDKey.Condition, "Good");
			c2.addPartition(EnumPartIDKey.Condition, "Waste");
			final JDFNodeInfo ni2 = (JDFNodeInfo) ni.addPartition(EnumPartIDKey.SheetName, "sh" + i);
			ni2.addPartition(EnumPartIDKey.Side, "Front");
			ni2.addPartition(EnumPartIDKey.Side, "Back");
		}
		JDFAttributeMap map = new JDFAttributeMap();
		map.put(EnumPartIDKey.SignatureName, "sig1");
		map.put(EnumPartIDKey.SheetName, "sh1");
		map.put(EnumPartIDKey.Side, EnumSide.Front);
		final VJDFAttributeMap vMap = new VJDFAttributeMap();
		vMap.add(map);
		map = new JDFAttributeMap(map);
		map.put(EnumPartIDKey.Side, EnumSide.Back);
		vMap.add(map);

		final JDFSpawn spawn = new JDFSpawn(n2);
		spawn.bFixResources = false;
		spawn.vRWResources_in = new VString("Output", null);
		spawn.vSpawnParts = vMap;

		final JDFNode nS1 = spawn.spawn();
		assertNotNull(nS1);
		nS1.setXPathAttribute("./ResourcePool/Component/Component/Component[@SheetName=\"sh1\"]/@foo", "fnarf");

		final JDFMerge merge = new JDFMerge(n);
		merge.mergeJDF(nS1, null, EnumCleanUpMerge.None, EnumAmountMerge.None);
		// assertEquals("only the sides are apawned, not the sheet proper",
		// n.getXPathAttribute("./ResourcePool/Component/Component/Component[@SheetName=\"sh1\"]/@foo", null), null);
	}

	/**
	 * 
	 */
	@Test
	public void testSpawnParallel()
	{
		final JDFNode[] aSpawned = new JDFNode[3];
		final JDFDoc d = JDFTestCaseBase.creatXMDoc();
		final JDFNode n = d.getJDFRoot();
		for (int i = 0; i < 3; i++)
		{
			final VJDFAttributeMap vPartMap = new VJDFAttributeMap();
			final JDFAttributeMap map = new JDFAttributeMap();
			map.put("SignatureName", "Sig1");
			map.put("SheetName", "S" + i);
			vPartMap.add(map);

			final JDFSpawn spawn = new JDFSpawn(n); // fudge to test output
			// counting}
			spawn.vSpawnParts = vPartMap;
			aSpawned[i] = spawn.spawn();
		}
		for (int i = 0; i < 3; i++)
		{
			KElement.uniqueID(100);
			final JDFAuditPool ap = aSpawned[i].getCreateAuditPool();
			for (int j = 0; j < 5; j++)
			{
				ap.addNotification(EnumClass.Error, null, null);
			}
			ap.addProcessRun(EnumNodeStatus.Completed, "me", aSpawned[i].getPartMapVector());
		}
		KElement.uniqueID(300);
		for (int i = 0; i < 3; i++)
		{
			final JDFMerge merge = new JDFMerge(n);
			merge.bAddMergeToProcessRun = true;

			// merge here
			final JDFNode mergedNode = merge.mergeJDF(aSpawned[i], "merged", JDFNode.EnumCleanUpMerge.None, EnumAmountMerge.UpdateLink);

			final VJDFAttributeMap partMapVector = aSpawned[i].getPartMapVector();
			final JDFProcessRun pr = (JDFProcessRun) mergedNode.getAuditPool().getAudit(0, EnumAuditType.ProcessRun, null, partMapVector);
			final JDFSpawned sp = (JDFSpawned) mergedNode.getAuditPool().getAudit(0, EnumAuditType.Spawned, null, partMapVector);
			final JDFMerged me = (JDFMerged) mergedNode.getAuditPool().getAudit(0, EnumAuditType.Merged, null, partMapVector);
			assertNotNull("loop " + i, pr);
			assertEquals(sp.getTimeStamp(), pr.getSubmissionTime());
			assertEquals(me.getTimeStamp(), pr.getReturnTime());

			assertNull(mergedNode.getMultipleIDs("ID"));
		}
	}

	/**
	 * 
	 */
	@Test
	public void testSpawnPartAmount()
	{
		final JDFDoc d = new JDFDoc("JDF");
		JDFNode n = d.getJDFRoot();
		n.setType(EnumType.ConventionalPrinting);
		final JDFComponent c = (JDFComponent) n.addResource(ElementName.COMPONENT, EnumUsage.Output);
		final JDFResourceLink rl = n.getLink(c, null);
		rl.setAttribute("foo:bar", "abc", "www.foo.com");
		for (int i = 1; i < 4; i++)
		{
			final JDFAttributeMap mapSheet = new JDFAttributeMap(EnumPartIDKey.SheetName, "s" + i);
			final JDFAttributeMap mapGood = mapSheet.clone();
			mapGood.put("Condition", "Good");
			final JDFAttributeMap mapWaste = mapSheet.clone();
			mapWaste.put("Condition", "Waste");
			rl.setAmount(40, mapGood);
			rl.setAmount(11, mapWaste);
		}
		final JDFSpawn spawn = new JDFSpawn(n);
		spawn.vSpawnParts = new VJDFAttributeMap();
		JDFAttributeMap mapS1 = new JDFAttributeMap(EnumPartIDKey.SheetName, "s1");
		JDFAttributeMap mapS1Good = mapS1.clone();
		mapS1Good.put(EnumPartIDKey.Condition, "Good");
		spawn.vSpawnParts.add(mapS1);
		spawn.vRWResources_in = new VString("Output", null);
		final JDFNode nSpawn = spawn.spawn();

		final JDFResourceLink rl2 = (JDFResourceLink) nSpawn.getChildByTagName("ComponentLink", null, 0, null, false, false);
		assertNotNull(rl2);
		assertNotNull(rl2.getAmountPool().getPartAmount(mapS1Good));
		JDFAttributeMap mapS2Good = mapS1Good.clone();
		mapS2Good.put(EnumPartIDKey.SheetName, "s2");
		assertNull("we zapped non-matching partAmounts", rl2.getAmountPool().getPartAmount(mapS2Good));
		rl2.setActualAmount(44, mapS1Good);
		final JDFAttributeMap mapgf = new JDFAttributeMap(mapS1Good);
		mapgf.put("Side", "Front");
		rl2.setActualAmount(22, mapgf);
		final JDFMerge m = new JDFMerge(n);
		n = m.mergeJDF(nSpawn, null, EnumCleanUpMerge.None, EnumAmountMerge.LinkOnly);
		final JDFResourceLink rlmerge = (JDFResourceLink) nSpawn.getChildByTagName("ComponentLink", null, 0, null, false, false);
		assertTrue(rlmerge.hasAttribute("foo:bar"));
		assertEquals(rlmerge.getActualAmount(mapgf), 22., 0.);
		assertEquals("the actualamount was spawned ro", rlmerge.getActualAmount(mapS1Good), 22., 0.);
		assertEquals("partamount for sheet2-4 were not touched", rlmerge.getAmount(mapS2Good), 40., 0.);
		final JDFComponent comp = (JDFComponent) rlmerge.getTarget().getPartition(mapS1, null);
		assertEquals(comp.getAmount(), 22.0, 0.0);
		assertEquals(comp.getAmountProduced(), 22.0, 0.0);
	}

	// /////////////////////////////////////////////////////////

	/**
	 * 
	 */
	@Test
	public void testSpawnIdenticalDeep()
	{
		for (int i = 0; i < 2; i++)
		{
			JDFNode n = new JDFDoc("JDF").getJDFRoot();
			n.setType(EnumType.ImageSetting);
			JDFResource xm = n.addResource("ExposedMedia", EnumUsage.Output);
			JDFResource m = n.addResource("Media", EnumUsage.Input);
			VString vs = new VString("SignatureName SheetName Side Separation", null);
			VString vsep = new VString("Cyan Black", null);
			JDFAttributeMap map1 = new JDFAttributeMap();
			map1.put("SignatureName", "Sig1");
			map1.put("SheetName", "S1");
			map1.put("Side", "Front");
			JDFResource xm11F = xm.getCreatePartition(map1, vs);
			VElement vxm1f = xm11F.addPartitions(EnumPartIDKey.Separation, vsep);
			JDFResource xm1fb = (JDFResource) vxm1f.get(0);
			JDFResource m11F = m.getCreatePartition(map1, vs);
			VElement vm1f = m11F.addPartitions(EnumPartIDKey.Separation, vsep);
			JDFResource m1fb = (JDFResource) vm1f.get(0);

			JDFAttributeMap map2 = new JDFAttributeMap(map1);
			map2.put("Side", "Back");
			JDFResource xm21F = xm.getCreatePartition(map2, vs);
			VElement vxm2f = xm21F.addPartitions(EnumPartIDKey.Separation, vsep);
			JDFResource xm2fb = (JDFResource) vxm2f.get(0);
			xm2fb.setIdentical(xm1fb);
			JDFResource m21F = m.getCreatePartition(map2, vs);
			VElement vm2f = m21F.addPartitions(EnumPartIDKey.Separation, vsep);
			JDFResource m2fb = (JDFResource) vm2f.get(0);
			m2fb.setIdentical(m1fb);

			final JDFSpawn spawn = new JDFSpawn(n); // fudge to test output
			spawn.bSpawnIdentical = i == 0;

			VJDFAttributeMap vSpawnParts = new VJDFAttributeMap();
			vSpawnParts.add(map2);
			JDFNode spawned = spawn.spawn(null, null, new VString("ExposedMedia", null), vSpawnParts, true, true, true, true);

			if (spawn.bSpawnIdentical)
				assertNotNull(spawned.getChildByTagName(ElementName.IDENTICAL, null, 0, null, false, false));
			else
				assertNull(spawned.getChildByTagName(ElementName.IDENTICAL, null, 0, null, false, false));
		}
	}

	/**
	 * 
	 */
	@Test
	public void testSpawnIdentical()
	{
		for (int i = 1; i < 2; i++)
		{
			for (int ii = 0; ii < 2; ii++) // spawnidentical = true / false
			{
				JDFNode n = new JDFDoc("JDF").getJDFRoot();
				final JDFNode root = n;
				if (i == 0)
				{
					n.setType(EnumType.ImageSetting);
				}
				else
				{
					n.setType(EnumType.ProcessGroup);
				}
				for (int j = 0; j < 2; j++) // in or out
				{
					JDFResource r = j == 0 ? n.addResource("ExposedMedia", EnumUsage.Output) : n.addResource("Media", EnumUsage.Input);
					r = r.addPartition(EnumPartIDKey.SheetName, "s1");
					final JDFResource rEN = r.addPartition(EnumPartIDKey.PartVersion, "EN");
					final JDFResource rDE = r.addPartition(EnumPartIDKey.PartVersion, "DE");
					r.addPartition(EnumPartIDKey.PartVersion, "FR");
					// de is master
					rEN.setIdentical(rDE);
				}
				final VString vRWRes = new VString();
				vRWRes.add(ElementName.EXPOSEDMEDIA);
				final VJDFAttributeMap vPartMap = new VJDFAttributeMap();
				final JDFAttributeMap map = new JDFAttributeMap("SheetName", "s1");
				map.put("PartVersion", "EN");
				vPartMap.add(map);
				final JDFAttributeMap map2 = new JDFAttributeMap("SheetName", "s1");
				map2.put("PartVersion", "DE");
				if (i == 1)
				{
					n = root.addJDFNode(EnumType.ImageSetting);
					n.copyElement(root.getResourceLinkPool(), null);
				}
				final JDFSpawn spawn = new JDFSpawn(n); // fudge to test output
				spawn.bFixResources = false;
				if (ii == 1)
				{
					spawn.bSpawnIdentical = false;
				}

				final JDFNode spawnedNode = spawn.spawn("thisUrl", "newURL", vRWRes, vPartMap, true, true, true, true);
				for (int j = 0; j < 2; j++)
				{
					final String resName = (j == 0 ? "Exposed" : "") + ElementName.MEDIA;
					final JDFResource rS = spawnedNode.getResourceRoot(resName, null, 0);
					final JDFResource rs2 = rS.getPartition(map, null);
					if (ii == 0)
					{
						assertNotNull(resName + " has no identical loop " + j + " " + i, rs2);
						assertTrue(rS.toXML().indexOf(ElementName.IDENTICAL) > 0);
					}
					else if (ii == 1)
					{
						assertNull(resName + " has identical loop " + j + " " + i, rs2);
						assertFalse(rS.toXML().indexOf(ElementName.IDENTICAL) > 0);
					}
					assertNull(rS.getPartition(new JDFAttributeMap(EnumPartIDKey.PartVersion, "FR"), null));
				}

				final JDFMerge merge = new JDFMerge(n);
				merge.bAddMergeToProcessRun = true;

				// merge here
				final JDFNode mergedNode = merge.mergeJDF(spawnedNode, "merged", JDFNode.EnumCleanUpMerge.None, EnumAmountMerge.UpdateLink);
				for (int j = 0; j < 2; j++)
				{
					final JDFResource rMerge = mergedNode.getResource((j == 0 ? "Exposed" : "") + ElementName.MEDIA, null, 0);
					assertEquals(rMerge.toString().indexOf("Spawn"), -1);
					assertTrue(rMerge.toXML().indexOf(ElementName.IDENTICAL) > 0);
				}
			}
		}
	}

	/**
	 * 
	 */
	@Test
	public void testSpawnPartSubElemIdent()
	{
		JDFNode nn = new JDFDoc("JDF").getJDFRoot();
		nn.setType("Product", false);
		JDFNode n = nn.addJDFNode(EnumType.ImageSetting);
		JDFExposedMedia xm = (JDFExposedMedia) n.addResource(ElementName.EXPOSEDMEDIA, EnumUsage.Output);
		JDFExposedMedia xm1 = (JDFExposedMedia) xm.addPartition(EnumPartIDKey.SheetName, "s1");

		JDFExposedMedia xm2 = (JDFExposedMedia) xm.addPartition(EnumPartIDKey.SheetName, "s2");
		JDFResource xm2f = xm2.addPartition(EnumPartIDKey.Side, "Front");
		xm2.appendMedia();

		JDFResource xm1f = xm1.addPartition(EnumPartIDKey.Side, "Front");
		xm1f.setIdentical(xm2f);

		nn.getCreateResourcePool().moveElement(xm, null);

		JDFSpawn s = new JDFSpawn(n);
		s.vRWResources_in = new VString("ExposedMedia", null);
		s.vSpawnParts = new VJDFAttributeMap();
		JDFAttributeMap e = new JDFAttributeMap(EnumPartIDKey.SheetName, "s1");
		e.put("Side", "Front");
		s.vSpawnParts.add(e);
		JDFNode n2 = s.spawn();
		assertNotNull(n2.getXPathElement("ResourcePool/ExposedMedia/ExposedMedia/Media"));
	}

	/**
	 * 
	 */
	@Test
	public void testSpawnPartRefElem()
	{
		JDFNode nn = new JDFDoc("JDF").getJDFRoot();
		nn.setType("Product", false);
		JDFNode n = nn.addJDFNode(EnumType.ImageSetting);
		n.setType(EnumType.ImageSetting);
		JDFExposedMedia xm = (JDFExposedMedia) n.addResource(ElementName.EXPOSEDMEDIA, EnumUsage.Output);
		nn.linkResource(xm, EnumUsage.Input, null);
		JDFExposedMedia xm1 = (JDFExposedMedia) xm.addPartition(EnumPartIDKey.SheetName, "s1");
		JDFExposedMedia xm2 = (JDFExposedMedia) xm.addPartition(EnumPartIDKey.SheetName, "s2");
		JDFMedia m = (JDFMedia) xm.appendMedia().makeRootResource(null, nn, true);
		n.linkResource(m, EnumUsage.Input, null);
		JDFMedia m1 = (JDFMedia) m.addPartition(EnumPartIDKey.SheetName, "s1");
		JDFMedia m2 = (JDFMedia) m.addPartition(EnumPartIDKey.SheetName, "s2");

		JDFSpawn s = new JDFSpawn(n);
		s.vRWResources_in = new VString("Media", null);
		s.vSpawnParts = new VJDFAttributeMap();
		JDFAttributeMap e = new JDFAttributeMap(EnumPartIDKey.SheetName, "s1");
		s.vSpawnParts.add(e);
		JDFNode n2 = s.spawn();
		assertNull(n2.getXPathAttribute("ResourcePool/Media/@SpawnIDs", null));
	}

	/**
	 * 
	 */
	@Test
	public void testSpawnPart()
	{
		for (int i = 0; i < 3; i++)
		{
			for (int j = 0; j < 2; j++)
			{
				for (int k = 0; k < 2; k++)
				{
					final boolean bSpawnroOnly = j == 1;
					final JDFDoc d = JDFTestCaseBase.creatXMDoc();
					JDFNode n = d.getJDFRoot();
					n.removeNodeInfo();
					final JDFNodeInfo ni = n.getCreateNodeInfo();
					assertNotNull("ni", ni);
					final JDFComment comment = n.appendComment();
					comment.setText("Comment 1");
					// ni.addPartition(JDFResource.EnumPartIDKey.SignatureName,
					// "Sig1");
					final JDFResourceLink l = n.getMatchingLink(ElementName.NODEINFO, EnumProcessUsage.AnyInput, 0);
					l.setPart("SignatureName", "Sig1");
					final VString vRWRes = new VString();
					vRWRes.add(ElementName.EXPOSEDMEDIA);
					final VJDFAttributeMap vPartMap = new VJDFAttributeMap();
					final JDFAttributeMap map = new JDFAttributeMap();
					map.put("SignatureName", "Sig1");
					map.put("SheetName", "S1");
					vPartMap.add(map);
					JDFResourceLink xmRL = n.getMatchingLink(ElementName.EXPOSEDMEDIA, EnumProcessUsage.AnyInput, 0);
					xmRL.setAmount(40, i == 1 ? map : null);
					xmRL.setUsage(EnumUsage.Output);
					xmRL.setAttribute("foo:bar", "a", "www.foobar.com");

					final JDFSpawn spawn = new JDFSpawn(n); // fudge to test
					// output counting

					JDFNode spawnedNode;
					if (k == 0)
					{
						spawnedNode = spawn.spawn("thisUrl", "newURL", vRWRes, vPartMap, bSpawnroOnly, true, true, true);
					}
					else
					{
						spawnedNode = spawn.spawnInformative("thisUrl", "newURL", vPartMap, bSpawnroOnly, true, true, true);
					}

					final String spawnID = spawnedNode.getSpawnID(false);
					assertNotSame(spawnID, "");
					spawnedNode.getCreateAuditPool().addProcessRun(EnumNodeStatus.Completed, null, vPartMap);

					JDFComponent spawnedROComponent = (JDFComponent) spawnedNode.getResource(ElementName.COMPONENT, EnumUsage.Output, 0);
					spawnedROComponent = (JDFComponent) spawnedROComponent.getResourceRoot();
					assertNotNull(spawnedROComponent);
					assertNotNull(spawnedROComponent.getPartition(map, EnumPartUsage.Explicit));
					final JDFAttributeMap map2 = new JDFAttributeMap();
					map2.put("SignatureName", "Sig2");
					map2.put("SheetName", "S2");
					if (j == 0)
					{
						assertNotNull("ro resources are spawned complete", spawnedROComponent.getPartition(map2, EnumPartUsage.Explicit));
					}
					else
					{
						assertNull("ro resources are spawned reduced", spawnedROComponent.getPartition(map2, EnumPartUsage.Explicit));
					}

					assertTrue("no spawnStatus", spawnedNode.toString().indexOf(AttributeName.SPAWNSTATUS) < 0);
					final JDFResourceLink rl = spawnedNode.getMatchingLink(ElementName.EXPOSEDMEDIA, EnumProcessUsage.AnyOutput, 0);
					final JDFResourceAudit ra = spawnedNode.cloneResourceToModify(rl);
					final String clonedResID = ra.getOldLink().getrRef();

					// check that the spawnIDs attribute is correctly placed in
					// main and sub
					final JDFExposedMedia xmSpawn = (JDFExposedMedia) rl.getTarget();
					assertNotNull(xmSpawn);
					assertEquals(new VString(spawnID, null), xmSpawn.getSpawnIDs(false));
					final JDFAttributeMap mapXMSpawn = xmSpawn.getPartMap();
					JDFExposedMedia xmMain = (JDFExposedMedia) n.getMatchingResource(ElementName.EXPOSEDMEDIA, EnumProcessUsage.AnyOutput, null, 0);
					xmMain = (JDFExposedMedia) xmMain.getPartition(mapXMSpawn, null);
					assertNotNull(xmMain);
					if (k == 0)
					{
						assertEquals(new VString(spawnID, null), xmMain.getSpawnIDs(false));
					}

					final JDFExposedMedia xmSpawnFront = (JDFExposedMedia) xmSpawn.getPartition(new JDFAttributeMap("Side", "Front"), null);
					assertNotNull(xmSpawnFront);
					final JDFExposedMedia xmSpawnFrontEn = (JDFExposedMedia) xmSpawnFront.addPartition(EnumPartIDKey.PartVersion, "En");
					assertNotNull(xmSpawnFrontEn);

					final JDFResourceLink loRL = spawnedNode.getMatchingLink(ElementName.LAYOUT, EnumProcessUsage.AnyInput, 0);
					assertNull(loRL.getPartMapVector());

					n = d.getJDFRoot();
					final JDFComment comment2 = n.appendComment();
					comment2.setText("Comment 2 after");
					final JDFComment comment3 = spawnedNode.appendComment();
					comment3.setText("Comment 3 spawned");

					final JDFResourceLink xmRLspawn = spawnedNode.getMatchingLink(ElementName.EXPOSEDMEDIA, EnumProcessUsage.AnyOutput, 0);
					xmRLspawn.setActualAmount(42, i != 0 ? map : null);
					assertEquals(xmRLspawn.getAttribute("foo:bar", "www.foobar.com", null), "a");
					assertEquals("amount ok", xmRLspawn.getAmount(map), 40., 0);
					assertEquals("act amount ok - ", xmRLspawn.getActualAmount(map), 42., 0.);

					// xmRLspawn.setAttribute("foo:bar","bb","www.foobar.com");
					if (i == 2)
					{
						xmRLspawn.getAmountPool().getPartAmount(map, 0).removeAttribute(AttributeName.ACTUALAMOUNT);
						assertEquals(xmRLspawn.getAmountPool().getPartAmount(map, 0).getAttributeMap().size(), 0);
						xmRLspawn.setActualAmount(42, null);
					}
					if (k > 0)
					{
						continue; // don't merge if spawn informative
					}

					final JDFMerge merge = new JDFMerge(n);
					merge.bAddMergeToProcessRun = true;

					// merge here
					final JDFNode mergedNode = merge.mergeJDF(spawnedNode, "merged", JDFNode.EnumCleanUpMerge.None, EnumAmountMerge.UpdateLink);
					final JDFAuditPool ap = mergedNode.getAuditPool();
					assertNotNull(ap);
					final JDFMerged merged = (JDFMerged) ap.getAudit(0, EnumAuditType.Merged, null, null);
					final JDFProcessRun pr = (JDFProcessRun) mergedNode.getChildByTagName(ElementName.PROCESSRUN, null, 0, null, false, true);
					assertEquals(pr.getSpawnID(), spawnID);
					assertEquals(pr.getReturnTime(), merged.getTimeStamp());

					assertNotNull(merged);
					assertEquals(vPartMap, merged.getPartMapVector());
					assertNull(ap.getElement(ElementName.PART));
					xmRL = mergedNode.getMatchingLink(ElementName.EXPOSEDMEDIA, EnumProcessUsage.AnyOutput, 0);
					final VElement poolChildren = mergedNode.getResourceLinkPool().getPoolChildren("NodeInfoLink", null, null);
					assertNotNull("poolChildren", poolChildren);
					assertEquals("no spurious ni added", poolChildren.size(), 1);
					assertEquals("Comment size" + i + " " + j + " " + k, mergedNode.getChildElementVector(ElementName.COMMENT, null, null, true, 99, false).size(), 3);
					assertEquals("merged amount ok", xmRL.getAmount(map), 40.0, 0.);
					assertEquals("did not overwrite rl attribute", xmRL.getAttribute("foo:bar", "www.foobar.com", null), "a");
					assertTrue(xmRL.hasAttribute(AttributeName.RREF));

					JDFExposedMedia xm = (JDFExposedMedia) mergedNode.getMatchingResource(ElementName.EXPOSEDMEDIA, EnumProcessUsage.AnyOutput, null, 0);
					assertTrue("PartVersion was added in spawned node", xm.getPartIDKeys().contains("PartVersion"));
					xm = (JDFExposedMedia) xm.getPartition(map, null);
					if (i < 2)
					{
						assertEquals("merged act amount ok", xmRL.getActualAmount(map), 42, 0);
						assertEquals("merged res amount ok", xm.getAmount(), 42, 0);
						assertEquals("merged res amountproduced ok", xm.getAmountProduced(), 42, 0);
					}

					// test whether anything modified and tracked in a resource
					// audit got correctly merged
					final JDFResourceAudit raMerge = (JDFResourceAudit) ap.getAudit(0, EnumAuditType.ResourceAudit, null, null);
					assertNotNull("res audit merged", raMerge);
					final JDFResource rOld = raMerge.getOldLink().getTarget();
					assertNotNull("old res  merged", rOld);
					assertEquals("old res ID", rOld.getID(), clonedResID);
					final JDFResource rNew = raMerge.getNewLink().getTarget();
					assertNotNull("new res  merged", rNew);
					assertNull(ap.getElement("Part"));
					final JDFMerged mergedAudit = (JDFMerged) ap.getAudit(-1, EnumAuditType.Merged, null, null);
					assertNotNull(mergedAudit);
					assertEquals(mergedAudit.getPartMapVector().elementAt(0), map);
				}
			}
		}
	}

	/**
	 * 
	 */
	@Test
	public void testSpawnMergeSimple()
	{
		final EnumCleanUpMerge cu[] = new EnumCleanUpMerge[] { EnumCleanUpMerge.None, EnumCleanUpMerge.RemoveAll, EnumCleanUpMerge.RemoveRRefs };
		for (int i = 0; i < 3; i++)
		{
			final JDFDoc d = JDFTestCaseBase.creatXMDoc();
			final JDFNode n = d.getJDFRoot();
			// test spawning of referenced resources in parent nodes
			n.setType(EnumType.ProcessGroup);
			final JDFNode n2 = n.addJDFNode(EnumType.ConventionalPrinting);

			n2.moveElement(n.getResourceLinkPool(), null);
			// JDFComponent c=(JDFComponent)
			// n2.addResource(ElementName.COMPONENT, EnumUsage.Output);
			n2.getCreateAuditPool().addNotification(null, null, null).appendComment().setText("notification all pre");
			n2.removeNodeInfo();
			n2.appendNodeInfo();
			final JDFSpawn spawn = new JDFSpawn(n2); // fudge to test output
			// counting
			n2.setStatus(EnumNodeStatus.Waiting);
			assertEquals(EnumNodeStatus.Waiting, n2.getPartStatus(null, 0));
			n2.getCreateAuditPool().addNotification(null, null, null).appendComment().setText("notification 2 main");

			final String pid = n2.getJobPartID(false);
			assertNotSame(pid, "");

			final JDFNode spawnedNode = spawn.spawn("thisUrl", "newURL", new VString("Component", null), null, false, true, true, true);
			spawnedNode.getCreateAuditPool().addNotification(null, null, null).appendComment().setText("notification 3 sub");
			assertTrue("no spawnStatus", spawnedNode.toString().indexOf(AttributeName.SPAWNSTATUS) < 0);
			assertEquals("n2 is spawned after the spawn, duh!", n2.getPartStatus(null, 0), EnumNodeStatus.Spawned);
			assertEquals("n2 is spawned after the spawn, duh!", n2.getNodeInfo().getNodeStatus(), EnumNodeStatus.Spawned);
			assertEquals("n2 is spawned after the spawn, duh!", n2.getStatus(), EnumNodeStatus.Spawned);

			final JDFResourceLink nLink = spawnedNode.getMatchingLink(ElementName.NODEINFO, null, 0);
			assertNull(nLink.getPart(0));
			final JDFResourceLink cLink = spawnedNode.getMatchingLink(ElementName.COMPONENT, null, 0);
			assertNotNull(cLink);
			assertNull(cLink.getPart(0));
			cLink.setActualAmount(42, null);

			spawnedNode.setStatus(EnumNodeStatus.Part);
			spawnedNode.getNodeInfo().setNodeStatus(EnumNodeStatus.Aborted);
			assertEquals(EnumNodeStatus.Part, spawnedNode.getStatus());
			assertEquals(EnumNodeStatus.Aborted, spawnedNode.getPartStatus(null, 0));
			final JDFAuditPool auditPool = spawnedNode.getCreateAuditPool();
			auditPool.addProcessRun(EnumNodeStatus.Aborted, null, null);
			final JDFNotification notif = (JDFNotification) auditPool.addAudit(EnumAuditType.Notification, null);
			notif.appendComment().setText("fooBar");

			final JDFMerge merge = new JDFMerge(n);
			merge.bAddMergeToProcessRun = true;

			final JDFNode mergedNode = merge.mergeJDF(spawnedNode, "merged", cu[i], EnumAmountMerge.UpdateLink);

			assertEquals(EnumNodeStatus.Part, mergedNode.getStatus());
			assertEquals(EnumNodeStatus.Aborted, mergedNode.getPartStatus(null, 0));

			final JDFNode jobPart = d.getJDFRoot().getJobPart(pid, null);
			assertEquals(jobPart, mergedNode);
			final JDFAuditPool auditPoolMerged = jobPart.getAuditPool();
			if (i == 0)
			{
				assertEquals(((JDFProcessRun) auditPoolMerged.getAudit(0, EnumAuditType.ProcessRun, null, null)).getSubmissionTime(), n.getAuditPool().getAudit(0, EnumAuditType.Spawned, null, null).getTimeStamp());
			}
			assertNotNull(auditPoolMerged.getAudit(3, EnumAuditType.Notification, null, null));
			assertNull(auditPoolMerged.getAudit(4, EnumAuditType.Notification, null, null));
			assertEquals("comment text correctly merged", auditPoolMerged.getAudit(-1, EnumAuditType.Notification, null, null).getComment(0).getText(), "fooBar");
			assertEquals(((JDFComponent) jobPart.getResource(ElementName.COMPONENT, EnumUsage.Output, 0)).getAmountProduced(), 42., 0.);
		}

	}

	/**
	 * 
	 */
	@Test
	public void testSpawnAddPartRoot()
	{
		final JDFDoc d = JDFTestCaseBase.creatXMDoc();
		JDFNode n = d.getJDFRoot();
		// test spawning of referenced resources in parent nodes
		n.setType(EnumType.ProcessGroup);
		final JDFNode n2 = n.addJDFNode(EnumType.ConventionalPrinting);
		n2.moveElement(n.getResourceLinkPool(), null);
		n = n2;
		n.removeNodeInfo();
		final JDFNodeInfo ni = n.getCreateNodeInfo();
		assertNotNull("ni", ni);
		final JDFComment comment = n.appendComment();
		comment.setText("Comment 1");
		final VString vResRW = new VString();
		vResRW.add(ElementName.EXPOSEDMEDIA);
		vResRW.add(ElementName.APPROVALSUCCESS);

		JDFResourceLink xmRL = n.getMatchingLink(ElementName.EXPOSEDMEDIA, EnumProcessUsage.AnyInput, 0);
		xmRL.setAmount(40, null);
		xmRL.setUsage(EnumUsage.Output);

		final JDFExposedMedia xmMain = (JDFExposedMedia) n.getMatchingResource(ElementName.EXPOSEDMEDIA, EnumProcessUsage.AnyOutput, null, 0);
		final JDFMedia media = xmMain.getMedia();
		media.makeRootResource("mediaID", n, true);

		final JDFSpawn spawn = new JDFSpawn(n); // fudge to test output counting

		final JDFNode spawnedNode = spawn.spawn("thisUrl", "newURL", vResRW, null, false, true, true, true);
		assertTrue("no spawnStatus", spawnedNode.toString().indexOf(AttributeName.SPAWNSTATUS) < 0);

		final JDFResourceLink xmRLspawn = spawnedNode.getMatchingLink(ElementName.EXPOSEDMEDIA, EnumProcessUsage.AnyOutput, 0);
		JDFNodeInfo ni2 = (JDFNodeInfo) spawnedNode.getMatchingResource(ElementName.NODEINFO, EnumProcessUsage.AnyInput, null, 0);
		final JDFExposedMedia xm = (JDFExposedMedia) xmRLspawn.getTarget();
		assertFalse("PartVersion was added in spawned node", xm.getPartIDKeys().contains("PartVersion"));
		assertFalse("PartVersion was added in spawned node", ni2.getPartIDKeys().contains("PartVersion"));
		assertNotNull(xm);

		ni2.addPartition(EnumPartIDKey.PartVersion, "En");

		final JDFExposedMedia xmRoot = (JDFExposedMedia) xm.getResourceRoot();
		assertEquals(xm, xmRoot);

		final JDFMedia spawnMedia = xm.getMedia();
		assertNotNull(spawnMedia);

		n = d.getJDFRoot();
		// test spawning of referenced resources in parent nodes
		n = (JDFNode) n.getElement("JDF");
		final JDFNode mergedNode = new JDFMerge(n).mergeJDF(spawnedNode, "merged", JDFNode.EnumCleanUpMerge.None, EnumAmountMerge.UpdateLink);
		xmRL = mergedNode.getMatchingLink(ElementName.EXPOSEDMEDIA, EnumProcessUsage.AnyOutput, 0);
		ni2 = (JDFNodeInfo) mergedNode.getMatchingResource(ElementName.NODEINFO, EnumProcessUsage.AnyInput, null, 0);
		assertTrue("PartVersion was added in spawned node", ni2.getPartIDKeys().contains("PartVersion"));

	}

	/**
	 * 
	 */
	@Test
	public void testSpawnAddPart()
	{
		for (int i = 0; i < 2; i++)
		{
			final JDFDoc d = JDFTestCaseBase.creatXMDoc();
			JDFNode n = d.getJDFRoot();
			if (i == 1)
			{
				// test spawning of referenced resources in parent nodes
				n.setType(EnumType.ProcessGroup);
				final JDFNode n2 = n.addJDFNode(EnumType.ConventionalPrinting);
				n2.moveElement(n.getResourceLinkPool(), null);
				n = n2;
			}
			n.removeNodeInfo();
			final JDFNodeInfo ni = n.getCreateNodeInfo();
			n.appendMatchingResource(ElementName.APPROVALSUCCESS, EnumProcessUsage.AnyInput, null);
			assertNotNull("ni", ni);
			final JDFComment comment = n.appendComment();
			comment.setText("Comment 1");
			// ni.addPartition(JDFResource.EnumPartIDKey.SignatureName,"Sig1");
			final JDFResourceLink l = n.getMatchingLink(ElementName.NODEINFO, EnumProcessUsage.AnyInput, 0);
			l.setPart("SignatureName", "Sig1");
			final VString vResRW = new VString();
			vResRW.add(ElementName.EXPOSEDMEDIA);
			vResRW.add(ElementName.APPROVALSUCCESS);
			final VJDFAttributeMap vMap = new VJDFAttributeMap();
			final JDFAttributeMap map = new JDFAttributeMap();
			map.put("SignatureName", "Sig1");
			map.put("SheetName", "S1");
			vMap.add(map);

			JDFResourceLink xmRL = n.getMatchingLink(ElementName.EXPOSEDMEDIA, EnumProcessUsage.AnyInput, 0);
			xmRL.setAmount(40, i == 0 ? map : null);
			xmRL.setUsage(EnumUsage.Output);

			final JDFExposedMedia xmMain = (JDFExposedMedia) n.getMatchingResource(ElementName.EXPOSEDMEDIA, EnumProcessUsage.AnyOutput, null, 0);
			final JDFMedia media = xmMain.getMedia();
			media.makeRootResource("mediaID", n, true);

			final JDFSpawn spawn = new JDFSpawn(n); // fudge to test output
			// counting

			final JDFNode spawnedNode = spawn.spawn("thisUrl", "newURL", vResRW, vMap, false, true, true, true);
			assertTrue("no spawnStatus", spawnedNode.toString().indexOf(AttributeName.SPAWNSTATUS) < 0);

			final JDFResourceLink xmRLspawn = spawnedNode.getMatchingLink(ElementName.EXPOSEDMEDIA, EnumProcessUsage.AnyOutput, 0);
			JDFNodeInfo ni2 = (JDFNodeInfo) spawnedNode.getMatchingResource(ElementName.NODEINFO, EnumProcessUsage.AnyInput, null, 0);
			final JDFExposedMedia xm = (JDFExposedMedia) xmRLspawn.getTarget();
			assertFalse("PartVersion was added in spawned node", xm.getPartIDKeys().contains("PartVersion"));
			assertFalse("PartVersion was added in spawned node", ni2.getPartIDKeys().contains("PartVersion"));
			assertNotNull(xm);
			final JDFApprovalSuccess as2 = (JDFApprovalSuccess) spawnedNode.getMatchingResource(ElementName.APPROVALSUCCESS, EnumProcessUsage.AnyInput, null, 0);
			assertNotNull(as2);
			as2.addPartition(EnumPartIDKey.PartVersion, "En");
			assertTrue(as2.getPartIDKeys().contains(EnumPartIDKey.PartVersion.getName()));

			final JDFExposedMedia xmSpawnFront = (JDFExposedMedia) xm.getPartition(new JDFAttributeMap("Side", "Front"), null);
			assertNotNull(xmSpawnFront);
			final JDFExposedMedia xmSpawnFrontEn = (JDFExposedMedia) xmSpawnFront.addPartition(EnumPartIDKey.PartVersion, "En");
			assertNotNull(xmSpawnFrontEn);
			ni2.addPartition(EnumPartIDKey.PartVersion, "En");

			final JDFExposedMedia xmRoot = (JDFExposedMedia) xm.getResourceRoot();
			assertNotSame(xm, xmRoot);

			final JDFMedia spawnMedia = xm.getMedia();
			assertNotNull(spawnMedia);

			assertNull(xmRoot.getSpawnIDs(false));
			final VString spawnIDs = xm.getSpawnIDs(false);
			final String spawnID = spawnedNode.getSpawnID(false);
			assertEquals(spawnIDs.get(0), spawnID);

			final JDFAttributeMap mapNew1 = new JDFAttributeMap();
			mapNew1.put("SignatureName", "Sig1");
			mapNew1.put("SheetName", "S1_OK");
			final JDFExposedMedia xmNew1 = (JDFExposedMedia) xmRoot.getCreatePartition(mapNew1, null);
			xmNew1.appendSpawnIDs(spawnID);
			xmNew1.setDescriptiveName("good new parallel");

			final JDFAttributeMap mapNew2 = new JDFAttributeMap();
			mapNew2.put("SignatureName", "Sig1");
			mapNew2.put("SheetName", "S1_Bad");
			xmRoot.getCreatePartition(mapNew1, null);

			n = d.getJDFRoot();
			// test spawning of referenced resources in parent nodes
			if (i == 1)
			{
				n = (JDFNode) n.getElement("JDF");
			}
			final JDFNode mergedNode = new JDFMerge(n).mergeJDF(spawnedNode, "merged", JDFNode.EnumCleanUpMerge.None, EnumAmountMerge.UpdateLink);
			xmRL = mergedNode.getMatchingLink(ElementName.EXPOSEDMEDIA, EnumProcessUsage.AnyOutput, 0);
			final JDFExposedMedia xmMRoot = (JDFExposedMedia) mergedNode.getMatchingResource(ElementName.EXPOSEDMEDIA, EnumProcessUsage.AnyOutput, null, 0);
			assertTrue("PartVersion was added in spawned node", xmMRoot.getPartIDKeys().contains("PartVersion"));
			ni2 = (JDFNodeInfo) mergedNode.getMatchingResource(ElementName.NODEINFO, EnumProcessUsage.AnyInput, null, 0);
			assertTrue("PartVersion was added in spawned node", ni2.getPartIDKeys().contains("PartVersion"));

			final JDFExposedMedia xmM1 = (JDFExposedMedia) xmMRoot.getPartition(mapNew1, null);
			assertEquals("Merged xm1", xmNew1.getDescriptiveName(), xmM1.getDescriptiveName());

			final JDFExposedMedia xmM2 = (JDFExposedMedia) xmMRoot.getPartition(mapNew2, null);
			assertNull("did not merge xm2", xmM2);
		}
	}

	/**
	 * 
	 */
	@Test
	public void testSpawn2()
	{
		final String fileNameIn = "km2";
		final String spawnNodeID = "Link08539766_000147";

		final JDFParser p = new JDFParser();
		final JDFDoc jdfDocIn = p.parseFile(sm_dirTestData + fileNameIn + ".jdf");
		assertTrue("Parse of file " + sm_dirTestData + fileNameIn + " failed", jdfDocIn != null);

		if (jdfDocIn != null)
		{
			// prepare the spawn process
			final JDFNode rootIn = (JDFNode) jdfDocIn.getRoot();

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
				final JDFSpawn spawn = new JDFSpawn(spawnNode);
				final JDFNode jdfSpawned = spawn.spawn(sm_dirTestData + fileNameIn + ".jdf", null, null, null, false, true, true, true);

				// ver�ndertes Ausgangsfile rausschreiben
				rootIn.eraseEmptyNodes(true);
				final String strXMLFileModified = fileNameIn + "_spawnedSource.xml";
				jdfDocIn.write2File(sm_dirTestDataTemp + strXMLFileModified, 2, true);

				final XMLDoc doc2 = jdfSpawned.getOwnerDocument_KElement();
				final String strXMLFileModified2 = fileNameIn + "_spawnedTarget.xml";
				doc2.write2File(sm_dirTestDataTemp + strXMLFileModified2, 0, true);
			}
		}
	}

	/**
	 * 
	 */
	@Test
	public void testSpawn3()
	{
		final String fileNameIn = "km2";

		final JDFParser p = new JDFParser();
		final JDFDoc jdfDocIn = p.parseFile(sm_dirTestData + fileNameIn + ".jdf");

		final JDFNode root = jdfDocIn.getJDFRoot();
		final JDFNode subJob = root.getJobPart("Qua0", null); // Link08539766_000147
		VElement v = subJob.getChildElementVector("JDF", null, new JDFAttributeMap(), false, Integer.MAX_VALUE, false);
		final VElement v2 = new VElement();
		int nEvent = 0;
		int nComment = 0;
		for (int i = 0; i < v.size(); i++)
		{
			final JDFNode spawnNode = (JDFNode) v.elementAt(i);
			spawnNode.getCreateAuditPool().addEvent("me", EnumSeverity.Event);
			spawnNode.appendComment().setText("Comment" + String.valueOf(i));
			final JDFSpawn spawn = new JDFSpawn(spawnNode);
			final JDFNode spawnedNode = spawn.spawn(sm_dirTestData + fileNameIn + ".jdf", null, null, null, false, true, true, true);
			v2.add(spawnedNode);
			nEvent += spawnedNode.getChildrenByTagName("Notification", "", new JDFAttributeMap(), false, false, 0).size();
			nComment += spawnedNode.numChildNodes(Node.COMMENT_NODE, false);
		}
		for (int i = 0; i < v2.size(); i++)
		{
			final JDFNode nodeToMerge = (JDFNode) v2.elementAt(i);
			new JDFMerge(root).mergeJDF(nodeToMerge, JDFConstants.EMPTYSTRING, JDFNode.EnumCleanUpMerge.None, JDFResource.EnumAmountMerge.None);
		}
		assertEquals(nEvent, root.getChildrenByTagName("Notification", "", new JDFAttributeMap(), false, false, 0).size());

		int copyComments = 0;
		v = subJob.getChildElementVector("JDF", null, null, false, Integer.MAX_VALUE, false);
		for (int i = 0; i < v.size(); i++)
		{
			final JDFNode spawnNode = (JDFNode) v.elementAt(i);
			copyComments += spawnNode.numChildNodes(Node.COMMENT_NODE, false);
		}

		assertEquals(nComment, copyComments);

		jdfDocIn.write2File(sm_dirTestDataTemp + "km2_AllSpawnedAndMerged.xml", 2, true);
	}

	/**
	 * test customerinfo and nodeinfo related stuff including high level access to information in the AncestorPool
	 * 
	 */
	@Test
	public void testSpawnNI13()
	{
		final JDFDoc d = new JDFDoc("JDF");
		final JDFNode n = d.getJDFRoot();
		assertEquals("null cid", n.getInheritedCustomerInfo("@CustomerOrderID"), null);
		n.setType("ProcessGroup", false);
		final VString v = new VString();
		v.add("Interpreting");
		v.add("Rendering");

		for (int i = 0; i < 3; i++)
		{
			final JDFNode n2 = n.addCombined(v);
			final JDFNodeInfo ni = n2.appendNodeInfo();
			final JDFSpawn spawn = new JDFSpawn(n2);
			VJDFAttributeMap vSpawnParts = null;
			if (i >= 1)
			{
				if (i == 2)
				{
					ni.addPartition(EnumPartIDKey.Run, "r1");
				}
				vSpawnParts = new VJDFAttributeMap();
				vSpawnParts.add(new JDFAttributeMap("Run", "r1"));
			}
			if (i == 1)
			{
				spawn.bFixResources = false;
			}
			final JDFNode spawnedNode = spawn.spawn("thisFile", "spawnFile", null, vSpawnParts, true, true, true, true);
			final JDFNodeInfo niSpawn = spawnedNode.getInheritedNodeInfo(null);
			assertNotNull("ni", niSpawn);
			if (i >= 1)
			{
				assertEquals(niSpawn.getPartMapVector(false), vSpawnParts);
			}
			assertEquals(spawnedNode.getResourcePool().numChildElements(ElementName.NODEINFO, null), 1);
		}
	}

	/**
	 * test customerinfo and nodeinfo related stuff including high level access to information in the AncestorPool
	 * 
	 */
	@Test
	public void testUnSpawnChild()
	{
		final JDFDoc d = new JDFDoc("JDF");
		final JDFNode n = d.getJDFRoot();
		assertEquals("null cid", n.getInheritedCustomerInfo("@CustomerOrderID"), null);
		n.setType("ProcessGroup", false);
		final VString v = new VString("Interpreting Rendering", null);

		final JDFNode n2 = n.addCombined(v);
		final JDFSpawn spawn = new JDFSpawn(n2);

		final JDFNode spawnedNode = spawn.spawn("thisFile", "spawnFile", null, null, true, true, true, true);
		final String spawnID = spawnedNode.getSpawnID(false);
		assertNotSame(spawnID, "");
		new JDFSpawn(spawnedNode).unSpawnChild(spawnedNode);
		final String toString = spawnedNode.toString();
		assertTrue(toString.indexOf(spawnID) < 0);
		assertTrue(toString.indexOf("Spawn") < 0);
		assertTrue(toString.indexOf("Merge") < 0);
	}

	/**
	 * test customerinfo and nodeinfo related stuff including high level access to information in the AncestorPool
	 * 
	 */
	@Test
	public void testUnSpawn()
	{
		final JDFDoc d = new JDFDoc("JDF");
		final JDFNode n = d.getJDFRoot();
		assertEquals("null cid", n.getInheritedCustomerInfo("@CustomerOrderID"), null);
		n.setType("ProcessGroup", false);
		final VString v = new VString("Interpreting Rendering", null);

		for (int i = 0; i < 2; i++) // 0 = now part, 1==part
		{
			final JDFNode n2 = n.addCombined(v);
			final JDFSpawn spawn = new JDFSpawn(n2);
			final VJDFAttributeMap vMap = new VJDFAttributeMap();
			vMap.add(new JDFAttributeMap("Side", "Front"));

			final JDFNode spawnedNode = spawn.spawn("thisFile", "spawnFile", null, i == 0 ? null : vMap, true, true, true, true);
			final String spawnID = spawnedNode.getSpawnID(false);
			assertNotSame(spawnID, "");
			final JDFSpawn spawn2 = new JDFSpawn(n2);
			spawn2.unSpawn(spawnID);
			final String toString = n.toString();
			assertTrue(toString.indexOf(spawnID) < 0);
			assertTrue(toString.indexOf("Spawn") < 0);
			assertTrue(toString.indexOf("Merge") < 0);
		}
	}

	/**
	 * test customerinfo and nodeinfo related stuff including high level access to information in the AncestorPool
	 * 
	 */
	@Test
	public void testUnSpawnNull()
	{
		final JDFDoc d = new JDFDoc("JDF");
		final JDFNode n = d.getJDFRoot();
		assertEquals("null cid", n.getInheritedCustomerInfo("@CustomerOrderID"), null);
		n.setType("ProcessGroup", false);
		final VString v = new VString();
		v.add("Interpreting");
		v.add("Rendering");

		for (int i = 0; i < 2; i++) // 0 = now part, 1==part
		{
			final JDFNode n2 = n.addCombined(v);
			final JDFSpawn spawn = new JDFSpawn(n2);
			final VJDFAttributeMap vMap = new VJDFAttributeMap();
			vMap.add(new JDFAttributeMap("Side", "Front"));

			final JDFNode spawnedNode = spawn.spawn("thisFile", "spawnFile", null, i == 0 ? null : vMap, true, true, true, true);
			final String spawnID = spawnedNode.getSpawnID(false);
			assertNotSame(spawnID, "");
			final JDFSpawn spawn2 = new JDFSpawn(n2);
			JDFNode parent = spawn2.unSpawn(null);
			assertNotNull("did something", parent);
			final String toString = n.toString();
			assertTrue(toString.indexOf(spawnID) < 0);
			assertTrue(toString.indexOf("Spawn") < 0);
			assertTrue(toString.indexOf("Merge") < 0);
			parent = spawn2.unSpawn(null);
			assertNull("nothing left", parent);
		}

	}

	/**
	 * test auditpool spawn merge stuff to information in the AncestorPool
	 * 
	 */
	@Test
	public void testSpawnAuditPool()
	{
		KElement.setLongID(true);

		for (int j = 0; j < 2; j++)
		{

			final JDFDoc d = new JDFDoc("JDF");
			final JDFNode n = d.getJDFRoot();
			assertEquals("null cid", n.getInheritedCustomerInfo("@CustomerOrderID"), null);
			n.setType("ProcessGroup", false);
			n.setVersion(EnumVersion.Version_1_3);

			final VString v = new VString();
			v.add("Interpreting");
			v.add("Rendering");

			final JDFNode n2 = n.addCombined(v);
			// JDFNotification n0 =
			final JDFAuditPool auditPoolMain = n2.getCreateAuditPool();
			auditPoolMain.addNotification(EnumClass.Event, null, null);
			auditPoolMain.addProcessRun(EnumNodeStatus.Completed, null, null);

			final JDFSpawn spawn = new JDFSpawn(n2);
			final JDFNode spawnedNode = spawn.spawn("thisFile", "spawnFile", null, null, true, true, true, true);
			final String spawnID = spawnedNode.getSpawnID(false);
			final JDFAuditPool ap = spawnedNode.getCreateAuditPool();
			ap.getAudit(0, EnumAuditType.ProcessRun, null, null).setDescriptiveName("changed");
			final JDFNotification not1 = ap.addNotification(EnumClass.Event, null, null);
			not1.appendComment();
			final JDFNotification not2 = ap.addNotification(EnumClass.Event, null, null);
			not2.appendComment();
			final String id1 = not1.getID();
			final String id2 = not2.getID();
			assertNotSame(id1, id2);
			final JDFMerge merge = new JDFMerge(n);
			final JDFNode mergedNode = merge.mergeJDF(spawnedNode, null, EnumCleanUpMerge.RemoveAll, EnumAmountMerge.None);
			final JDFAuditPool ap2 = mergedNode.getCreateAuditPool();
			final VElement vNotifs = ap2.getAudits(EnumAuditType.Notification, null, null);
			assertEquals(vNotifs.size(), 3);
			assertTrue("ids are correctly copied", vNotifs.get(1).getAttribute("ID").endsWith(id1.substring(2)));
			assertTrue("ids are correctly copied", vNotifs.get(2).getAttribute("ID").endsWith(id2.substring(2)));
			assertTrue("ids contain spawnid", vNotifs.get(1).getAttribute("ID").contains(spawnID.substring(spawnID.length() - 6)));
			assertEquals("pr was not duplicated ", ap2.numChildElements(ElementName.PROCESSRUN, null), 1);
			final JDFAudit prMerge = ap2.getAudit(0, EnumAuditType.ProcessRun, null, null);
			assertEquals("pr was not duplicated ", prMerge.getDescriptiveName(), "changed");
		}
	}

	/**
	 * test customerinfo and nodeinfo related stuff including high level access to information in the AncestorPool
	 * 
	 */
	@Test
	public void testSpawnNI()
	{
		for (int i = 0; i < 2; i++)
		{
			for (int j = 0; j < 2; j++)
			{

				final JDFDoc d = new JDFDoc("JDF");
				final JDFNode n = d.getJDFRoot();
				assertEquals("null cid", n.getInheritedCustomerInfo("@CustomerOrderID"), null);
				n.setType("ProcessGroup", false);
				final EnumVersion version = i == 0 ? EnumVersion.Version_1_2 : EnumVersion.Version_1_3;
				n.setVersion(version);

				final VString v = new VString();
				v.add("Interpreting");
				v.add("Rendering");

				final JDFNode n2 = n.addCombined(v);

				final JDFNodeInfo ni = j == 0 ? n2.appendNodeInfo() : n.appendNodeInfo();
				ni.setFirstEnd(new JDFDate());
				ni.appendJMF();
				final JDFSpawn spawn = new JDFSpawn(n2);
				final JDFNode spawnedNode = spawn.spawn("thisFile", "spawnFile", null, null, true, true, true, true);

				final JDFNodeInfo niSpawn = spawnedNode.getInheritedNodeInfo(null);
				assertNotNull("ni", niSpawn);
				final JDFAncestor a = spawnedNode.getAncestorPool().getAncestor(0);
				if (j == 0)
				{
					assertNull(a.getNodeInfo());
					assertNotNull(spawnedNode.getNodeInfo());
				}
				else
				{
					assertNotNull(a.getNodeInfo());
					assertNull(spawnedNode.getNodeInfo());
				}

			}
		}
	}

	/**
	 * test customerinfo and nodeinfo related stuff including high level access to information in the AncestorPool
	 * 
	 */
	@Test
	public void testSpawnNIStatus()
	{

		final JDFDoc d = new JDFDoc("JDF");
		final JDFNode n = d.getJDFRoot();
		assertEquals("null cid", n.getInheritedCustomerInfo("@CustomerOrderID"), null);
		n.setType("ProcessGroup", false);

		final VString v = new VString();
		v.add("Interpreting");
		v.add("Rendering");

		final JDFNode n2 = n.addCombined(v);
		n2.setJobPartID("J1");
		n2.appendNodeInfo();
		n2.setPartStatus((JDFAttributeMap) null, EnumNodeStatus.Setup, null);

		final JDFSpawn spawn = new JDFSpawn(n2);
		final JDFNode spawnedNode = spawn.spawn("thisFile", "spawnFile", null, null, true, true, true, true);
		spawnedNode.setPartStatus((JDFAttributeMap) null, EnumNodeStatus.Cleanup, null);
		spawnedNode.setActivation(EnumActivation.Inactive);
		JDFMerge m = new JDFMerge(n);
		m.mergeJDF(spawnedNode);
		JDFNode n3 = n.getJobPart("J1", null);
		assertEquals(n3.getPartStatus(null, 0), EnumNodeStatus.Cleanup);
		assertEquals(n3.getActivation(true), EnumActivation.Inactive);
	}

	/**
	 * test customerinfo and nodeinfo related stuff including high level access to information in the AncestorPool
	 * 
	 */
	@Test
	public void testSpawnNILocalPartStatus()
	{
		final JDFDoc d = new JDFDoc("JDF");
		final JDFNode n = d.getJDFRoot();
		n.setType("ProcessGroup", false);

		final VString v = new VString();
		v.add("Interpreting");
		v.add("Rendering");

		final JDFNode n2 = n.addCombined(v);
		n2.setJobPartID("J1");
		JDFAttributeMap partMap = new JDFAttributeMap();
		partMap.put("SignatureName", "Sig1");
		partMap.put("SheetName", "S1");

		JDFNodeInfo nodeInfo = n2.getCreateNodeInfo();
		nodeInfo.setPartIDKeys(new VString("SignatureName SheetName", null));
		n2.setPartStatus(partMap, EnumNodeStatus.Setup, null);
		nodeInfo.setNodeStatus(EnumNodeStatus.Waiting);
		n2.setStatus(EnumNodeStatus.Waiting);

		final JDFSpawn spawn = new JDFSpawn(n2);
		VJDFAttributeMap vMap = new VJDFAttributeMap(partMap);
		final JDFNode spawnedNode = spawn.spawn("thisFile", "spawnFile", null, vMap, true, true, true, true);
		spawnedNode.setPartStatus(partMap, EnumNodeStatus.Cleanup, null);
		spawnedNode.setStatus(EnumNodeStatus.Suspended);

		JDFMerge m = new JDFMerge(n);
		m.mergeJDF(spawnedNode);
		JDFNode n3 = n.getJobPart("J1", null);
		assertEquals("inconsistent parts get merged to the root value", n3.getPartStatus(partMap, 0), EnumNodeStatus.Suspended);
		n3.setStatus(EnumNodeStatus.Part);
		assertEquals("inconsistent parts get merged to the root value", n3.getPartStatus(partMap, 0), EnumNodeStatus.Suspended);
	}

	/**
	 * test customerinfo and nodeinfo related stuff including high level access to information in the AncestorPool
	 * 
	 */
	@Test
	public void testSpawnCINI()
	{
		for (int i = 0; i < 2; i++)
		{
			for (int j = 0; j < 2; j++)
			{
				final VJDFAttributeMap partmapvector = new VJDFAttributeMap();
				if (j == 1)
				{
					final JDFAttributeMap partmap = new JDFAttributeMap();
					partmap.put("SheetName", "S1");
					partmapvector.add(partmap);
				}

				final JDFDoc d = new JDFDoc("JDF");
				JDFNode n = d.getJDFRoot();
				assertEquals("null cid", n.getInheritedCustomerInfo("@CustomerOrderID"), null);
				n.setType("ProcessGroup", false);
				final EnumVersion version = i == 0 ? EnumVersion.Version_1_2 : EnumVersion.Version_1_4;
				n.setVersion(version);
				JDFCustomerInfo ci = n.appendCustomerInfo();
				JDFContact contact = ci.appendContact();
				contact = (JDFContact) contact.makeRootResource("ID_Contact1", n, true);
				ci.setCustomerOrderID("cid");
				assertEquals("cid", n.getInheritedCustomerInfo("@CustomerOrderID").getCustomerOrderID(), "cid");

				final VString v = new VString();
				v.add("Interpreting");
				v.add("Rendering");

				JDFNode n2 = n.addCombined(v);
				final JDFNodeInfo ni = n2.appendNodeInfo();
				ni.setFirstEnd(new JDFDate());
				final JDFSpawn spawn = new JDFSpawn(n2);
				JDFNode spawnedNode = spawn.spawn("thisFile", "spawnFile", null, partmapvector, true, true, true, true);
				ci = spawnedNode.getInheritedCustomerInfo(null);
				assertNotNull("ci", ci);
				JDFContact refContact = ci.getContact(0);
				assertNotNull("ref to contact", refContact);
				JDFNodeInfo niSpawn = spawnedNode.getInheritedNodeInfo(null);
				assertNotNull("ni", niSpawn);

				assertNotNull("ci", ci);
				assertNotNull("spawned", (j == 1 ? n2 : n).getAuditPool().getAudit(0, JDFAudit.EnumAuditType.Spawned, null, null));
				String spawnID = spawnedNode.getSpawnID(false);
				assertEquals("cid", spawnedNode.getInheritedCustomerInfo("@CustomerOrderID").getCustomerOrderID(), "cid");
				final JDFAncestor anc = spawnedNode.getAncestorPool().getAncestor(0);
				assertNull("no xsi:type", anc.getAttribute("type", AttributeName.XSIURI, null));

				n = new JDFSpawn(n).unSpawn(spawnID);
				assertNotNull("n2", n);
				assertNull("spawned", d.getJDFRoot().getAuditPool().getAudit(0, JDFAudit.EnumAuditType.Spawned, null, null));

				n.removeCustomerInfo();
				n.setVersion(version);
				ci = n.appendCustomerInfo();
				contact = ci.appendContact();
				contact = (JDFContact) contact.makeRootResource("ID_Contact1", n, true);

				final JDFSpawn _spawn = new JDFSpawn(n2);
				spawnedNode = _spawn.spawn("thisFile", "spawnFile", null, partmapvector, true, true, true, true);
				spawnID = spawnedNode.getSpawnID(false);

				ci = spawnedNode.getInheritedCustomerInfo(null);
				assertNotNull("ci", ci);
				refContact = ci.getContact(0);
				assertNotNull("ref to contact: " + version.getName(), refContact);

				n2 = new JDFSpawn(d.getJDFRoot()).unSpawn(spawnID);
				assertNotNull("n2", n2);
				assertTrue("n2 no spawnID", n2.toString().indexOf(spawnID) < 0);

				final JDFSpawn spawn2 = new JDFSpawn(n2);
				spawnedNode = spawn2.spawn("thisFile", "spawnFile", null, partmapvector, true, true, true, true);
				spawnID = spawnedNode.getSpawnID(false);

				niSpawn = spawnedNode.getInheritedNodeInfo(null);
				assertNotNull("ni", niSpawn);

				spawnedNode.setPartStatus(partmapvector, EnumNodeStatus.Aborted, null);
				assertEquals(spawnedNode.getPartStatus((j == 1 ? partmapvector.elementAt(0) : null), 0), EnumNodeStatus.Aborted);

				final JDFNode mergedNode = new JDFMerge(n2).mergeJDF(spawnedNode, "merged", JDFNode.EnumCleanUpMerge.None, EnumAmountMerge.UpdateLink);
				assertEquals(mergedNode.getPartStatus((j == 1 ? partmapvector.elementAt(0) : null), 0), EnumNodeStatus.Aborted);
			}
		}
	}

	/**
	 * test customerinfo and nodeinfo related stuff including high level access to information in the AncestorPool
	 * 
	 */
	@Test
	public void testSpawnLayout()
	{
		final JDFDoc d = new JDFDoc("JDF");
		JDFNode n = d.getJDFRoot();
		final VJDFAttributeMap partmapvector = new VJDFAttributeMap();
		final JDFAttributeMap partmap = new JDFAttributeMap();
		partmap.put(AttributeName.SIGNATURENAME, "Sig1");
		JDFNodeInfo ni = n.getCreateNodeInfo();
		ni.getCreatePartition(partmap, null);
		partmap.put("SheetName", "S1");
		ni.getCreatePartition(partmap, null);
		partmap.put("Side", "Front");
		ni.getCreatePartition(partmap, null);
		partmapvector.add(partmap);
		final JDFAttributeMap partmapback = partmap.clone();
		partmapback.put("Side", "Back");

		final VString v = new VString("Imposition Interpreting Rendering", null);
		n.setCombined(v);
		JDFRunList ruli = (JDFRunList) n.addResource("RunList", null, EnumUsage.Input, EnumProcessUsage.Document, null, null, null);
		ruli.addPDF("file1.pdf", 0, 8);
		ruli.addPDF("file2.pdf", 0, 33);
		JDFRunList ruliMarks = (JDFRunList) n.addResource("RunList", null, EnumUsage.Input, EnumProcessUsage.Marks, null, null, null);
		((JDFRunList) ruliMarks.getCreatePartition(partmap, null)).setFileURL("marks.pdf");
		ruliMarks.getCreatePartition(partmapback, null);
		JDFLayout lo = (JDFLayout) n.addResource("Layout", EnumUsage.Input);
		lo.getCreatePartition(partmap, null);
		lo.getCreatePartition(partmapback, null);
		JDFExposedMedia xm = (JDFExposedMedia) n.addResource("ExposedMedia", EnumUsage.Output);
		xm.getCreatePartition(partmap, null);
		xm.getCreatePartition(partmapback, null);
		final JDFSpawn spawn = new JDFSpawn(n);
		JDFNode spawnedNode = spawn.spawn("thisFile", "spawnFile", null, partmapvector, true, true, true, true);
		assertTrue(spawnedNode.toXML().contains("file1.pdf"));
		final JDFNode mergedNode = new JDFMerge(n).mergeJDF(spawnedNode, "merged", JDFNode.EnumCleanUpMerge.None, EnumAmountMerge.UpdateLink);
		assertNotNull(mergedNode);
	}

	/**
	 * 
	 */
	@Test
	public void testBigSpawn()
	{
		final String strJDFName = "000023_Test_PR3.0.jdf";
		// final String strJDFName = "biginline.jdf";
		final String strJDFPath = sm_dirTestData + strJDFName;
		final JDFParser parser = new JDFParser();
		final JDFDoc jdfDoc = parser.parseFile(strJDFPath);
		for (int i = 1; i < 10; i++)
		{
			System.out.println("i=" + i);
			final VJDFAttributeMap vamParts = new VJDFAttributeMap();
			final JDFAttributeMap amParts0 = new JDFAttributeMap();
			amParts0.put("Side", "Front");
			amParts0.put("SignatureName", "Sig00" + i);
			amParts0.put("SheetName", "FB 00" + i);
			vamParts.add(amParts0);
			final JDFAttributeMap amParts1 = new JDFAttributeMap();
			amParts1.put("Side", "Back");
			amParts1.put("SignatureName", "Sig00" + i);
			amParts1.put("SheetName", "FB 00" + i);
			vamParts.add(amParts1);
			final VString vsRWResourceIDs = new VString();
			vsRWResourceIDs.add("Link84847227_000309");
			vsRWResourceIDs.add("r85326439_027691");
			final JDFNode nodeRoot = jdfDoc.getJDFRoot();
			final JDFNode nodeProc = nodeRoot.getJobPart("IPD0.I", null);
			final JDFSpawn spawn = new JDFSpawn(nodeProc);
			// JDFNode nodeProc = nodeRoot;
			final JDFNode nodeSubJDF = spawn.spawn(strJDFPath, null, vsRWResourceIDs, vamParts, true, true, true, true);
			assertNotNull(nodeSubJDF);

			if (nodeSubJDF != null)
			{
				nodeSubJDF.getOwnerDocument_KElement().write2File(sm_dirTestDataTemp + "bigSub" + i + ".jdf", 2, true);
				if (i == 9)
				{
					jdfDoc.write2File(sm_dirTestDataTemp + "bigMainPost.jdf", 2, true);
				}
			}
		}
	} // ////////////////////////////////////////////////////////////////////////

	// /

	/**
	 * 
	 */
	@Test
	public void testManySpawn()
	{
		final String strJDFName = "000023_Test_PR3.0.jdf";
		// final String strJDFName = "biginline.jdf";
		final String strJDFPath = sm_dirTestData + strJDFName;
		final JDFParser parser = new JDFParser();
		final JDFDoc jdfDoc = parser.parseFile(strJDFPath);
		final JDFNode nodeRoot = jdfDoc.getJDFRoot();
		final VElement vNodes = nodeRoot.getTree("JDF", null, null, false, false);
		JDFSpawn spawn = null;
		long tMerge = 0;
		for (int j = 0; j < 2; j++)
		{
			long t0 = System.currentTimeMillis();
			long t00 = t0;
			for (int i = 1; i < vNodes.size(); i++)
			{
				JDFNode nodeProc = (JDFNode) vNodes.elementAt(i);
				final String jobPartID = nodeProc.getJobPartID(false);
				final VString vsRWResourceIDs = new VString();
				vsRWResourceIDs.add("Link84847227_000309");
				vsRWResourceIDs.add("r85326439_027691");
				vsRWResourceIDs.add("Output");
				nodeProc = nodeRoot.getJobPart(jobPartID, null); // in case it was
				// overwritten by a previous s-m
				if (i == 1 || j == 1)
					spawn = new JDFSpawn(nodeProc);
				else if (spawn != null)
					spawn.setNode(nodeProc);
				else
				{
					fail("whazzup?");
					return;
				}

				final JDFNode nodeSubJDF = spawn.spawn(strJDFPath, null, vsRWResourceIDs, null, true, true, true, true);
				assertNotNull(nodeSubJDF);
				long t1 = System.currentTimeMillis();

				nodeSubJDF.getOwnerDocument_KElement().write2File(sm_dirTestDataTemp + "manySub" + i + ".jdf", 2, true);
				jdfDoc.write2File(sm_dirTestDataTemp + "bigMainMany" + i + ".jdf", 2, true);

				assertEquals(nodeProc.getID(), nodeSubJDF.getID());
				final JDFDoc d2 = parser.parseFile(sm_dirTestDataTemp + "manySub" + i + ".jdf");
				assertNotNull("The subjdf could be parsed!", d2);
				final String spawnID = nodeSubJDF.getSpawnID(false);
				long t11 = System.currentTimeMillis();
				final JDFMerge m = new JDFMerge(nodeRoot);
				assertTrue(nodeRoot.toString().indexOf(spawnID) > 0);
				m.mergeJDF(nodeSubJDF, "dummy", EnumCleanUpMerge.RemoveAll, EnumAmountMerge.UpdateLink);
				assertTrue(nodeRoot.toString().indexOf(spawnID) < 0);
				long t2 = System.currentTimeMillis();
				tMerge += (t2 - t11);
				System.out.println("j= " + j + " i= " + i + " of " + (vNodes.size() - 1) + " : " + jobPartID + " time Spawn: " + (t1 - t0) + " time Write: " + (t11 - t1)
						+ " time Merge: " + (t2 - t11) + " / " + tMerge + " total " + (t2 - t00));
				t0 = t2;
			}
			jdfDoc.write2File(sm_dirTestDataTemp + "bigMainMany.jdf", 2, true);
		}

	}

	/**
	 * 
	 */
	@Test
	public void testManySpawnInformative()
	{
		final String strJDFName = "000023_Test_PR3.0.jdf";
		// final String strJDFName = "biginline.jdf";
		final String strJDFPath = sm_dirTestData + strJDFName;
		final JDFParser parser = new JDFParser();
		final JDFDoc jdfDoc = parser.parseFile(strJDFPath);
		final JDFNode nodeRoot = jdfDoc.getJDFRoot();
		final VElement vNodes = nodeRoot.getTree("JDF", null, null, false, false);
		for (int j = 0; j < 2; j++)
		{
			long t0 = System.currentTimeMillis();
			long t00 = System.currentTimeMillis();
			JDFSpawn spawn = null;
			for (int i = 1; i < vNodes.size(); i++)
			{
				JDFNode nodeProc = (JDFNode) vNodes.elementAt(i);
				final String jobPartID = nodeProc.getJobPartID(false);
				final VString vsRWResourceIDs = new VString();
				vsRWResourceIDs.add("Link84847227_000309");
				vsRWResourceIDs.add("r85326439_027691");
				vsRWResourceIDs.add("Output");
				nodeProc = nodeRoot.getJobPart(jobPartID, null); // in case it was
				// overwritten by a previous s-m
				if (i == 1 || j == 1)
					spawn = new JDFSpawn(nodeProc);
				else if (spawn != null)
					spawn.setNode(nodeProc);
				else
				{
					fail("whazzup?");
					return;
				}
				final JDFNode nodeSubJDF = spawn.spawnInformative(strJDFPath, null, null, true, true, true, true);
				assertNotNull(nodeSubJDF);

				nodeSubJDF.getOwnerDocument_KElement().write2File(sm_dirTestDataTemp + "manySubInf" + i + "." + j + ".jdf", 2, true);
				assertEquals(nodeProc.getID(), nodeSubJDF.getID());
				final JDFDoc d2 = parser.parseFile(sm_dirTestDataTemp + "manySubInf" + i + "." + j + ".jdf");
				assertNotNull("The subjdf could be parsed!", d2);
				long t1 = System.currentTimeMillis();
				System.out.println("j= " + j + " i= " + i + " of " + (vNodes.size() - 1) + " : " + jobPartID + " time: " + (t1 - t0) + " total " + (t1 - t00));
				t0 = t1;
			}
		}
		jdfDoc.write2File(sm_dirTestDataTemp + "bigMainMany.jdf", 2, true);

	}

	/**
	 * 
	 */
	@Test
	public void testManySpawnPartInformative()
	{
		final String strJDFName = "bigWhite.jdf";
		// final String strJDFName = "biginline.jdf";
		final String strJDFPath = sm_dirTestData + strJDFName;
		final JDFParser parser = new JDFParser();
		final JDFDoc jdfDoc = parser.parseFile(strJDFPath);
		final JDFNode nodeRoot = jdfDoc.getJDFRoot();
		JDFNode nodeProc = nodeRoot.getJobPart("Qua0.A", null);
		JDFRunList rlOut = (JDFRunList) nodeProc.getResource("RunList", EnumUsage.Output, 0);
		VJDFAttributeMap vmap = rlOut.getPartMapVector(false);
		for (int j = 0; j < 2; j++)
		{
			long t0 = System.currentTimeMillis();
			long t00 = System.currentTimeMillis();
			JDFSpawn spawn = null;
			for (int i = 0; i < 100; i++)
			{
				JDFAttributeMap map = vmap.get(i);
				VJDFAttributeMap vMap1 = new VJDFAttributeMap();
				vMap1.add(map);
				final VString vsRWResourceIDs = new VString();
				vsRWResourceIDs.add("Output");
				if (i == 0 || j > 2)
					spawn = new JDFSpawn(nodeProc);
				if (spawn == null)
				{
					fail("whazzup?");
					return;
				}
				final JDFNode nodeSubJDF;
				if (j == 0)
					nodeSubJDF = spawn.spawnInformative(strJDFPath, null, vMap1, true, true, true, true);
				else
					nodeSubJDF = spawn.spawn(strJDFPath, null, vsRWResourceIDs, vMap1, true, true, true, true);

				assertNotNull(nodeSubJDF);
				long t1 = System.currentTimeMillis();

				nodeSubJDF.getOwnerDocument_KElement().write2File(sm_dirTestDataTemp + "manySubInf" + i + "." + j + ".jdf", 2, true);
				assertEquals(nodeProc.getID(), nodeSubJDF.getID());
				final JDFDoc d2 = parser.parseFile(sm_dirTestDataTemp + "manySubInf" + i + "." + j + ".jdf");
				assertNotNull("The subjdf could be parsed!", d2);
				long t2 = System.currentTimeMillis();
				System.out.println("j= " + j + " i= " + i + " of " + (vmap.size() - 1) + " : " + map + " time: " + (t2 - t1) + "/" + (t1 - t0) + " total " + (t2 - t00));
				t0 = t1;
			}
		}
		jdfDoc.write2File(sm_dirTestDataTemp + "bigMainMany.jdf", 2, true);

	}

	/**
	 * 
	 */
	@Test
	public void testMergeVersion()
	{

		final JDFDoc d = new JDFDoc("JDF");
		final JDFNode n = d.getJDFRoot();
		n.setType(EnumType.Product);
		final JDFAttributeMap partMap = new JDFAttributeMap();
		partMap.put("SheetName", "S1");
		JDFResource comp = n.addResource(ElementName.COMPONENT, EnumUsage.Output);
		comp.setPartUsage(EnumPartUsage.Sparse);
		JDFResource comps = comp.addPartition(EnumPartIDKey.SheetName, "S1");
		partMap.put("PartVersion", "En De");
		comps.addPartition(EnumPartIDKey.PartVersion, "En De");
		comps.addPartition(EnumPartIDKey.PartVersion, "Fr De");
		comps.addPartition(EnumPartIDKey.PartVersion, "En");
		comps.addPartition(EnumPartIDKey.PartVersion, "De");
		comps.addPartition(EnumPartIDKey.PartVersion, "Fr");
		JDFNode n2 = n.addJDFNode(EnumType.ConventionalPrinting);
		n2.linkResource(comp, EnumUsage.Output, null);

		JDFSpawn spawn = new JDFSpawn(n2);
		spawn.vSpawnParts = new VJDFAttributeMap(partMap);
		spawn.vRWResources_in = new VString("Output", null);
		JDFNode nSpawned = spawn.spawn();
		JDFResource rspawned = nSpawned.getResource(ElementName.COMPONENT, null, 0).getResourceRoot();
		VElement vLeaves = rspawned.getLeaves(false);
		vLeaves.get(1).deleteNode();

		JDFMerge m = new JDFMerge(n2);
		JDFNode nMerged = m.mergeJDF(nSpawned);
		assertTrue(nMerged.toXML().indexOf(AttributeName.SPAWNSTATUS) < 0);
		assertTrue(n.toXML().indexOf(AttributeName.SPAWNSTATUS) < 0);

	}

	/**
	 * 
	 */
	@Test
	public void testMergeUpdateNI()
	{
		final JDFDoc doc = new JDFDoc("JDF");
		final JDFNode root = doc.getJDFRoot();
		root.setType(EnumType.ProcessGroup);
		final JDFAttributeMap map1 = new JDFAttributeMap("Run", "r1");
		final JDFAttributeMap map2 = new JDFAttributeMap("Run", "r2");
		final VJDFAttributeMap vMap = new VJDFAttributeMap();
		vMap.add(map1);
		vMap.add(map2);
		final JDFNode[] nodes = new JDFNode[3];
		root.setPartStatus(vMap, EnumNodeStatus.Waiting, null);
		nodes[0] = root.addJDFNode(EnumType.Approval);
		nodes[1] = root.addJDFNode(EnumType.Bending);
		nodes[2] = root.addJDFNode(EnumType.ImageReplacement);
		for (int i = 0; i < 3; i++)
		{
			nodes[i].setPartStatus(vMap, EnumNodeStatus.Waiting, null);
		}

		vMap.removeElementAt(1);
		for (int i = 0; i < 3; i++)
		{

			JDFNode node = nodes[i];
			assertNotNull(node.getNodeInfo());
			final JDFSpawn spawn = new JDFSpawn(node);
			spawn.vSpawnParts = vMap;
			spawn.vRWResources_in = new VString("NodeInfo", null);
			final JDFNode spawnedNode = spawn.spawn();
			spawnedNode.setPartStatus(map1, EnumNodeStatus.Completed, null);
			final JDFMerge merge = new JDFMerge(node);

			// this is the feature taht is being tested..
			merge.bUpdateStati = true;
			node = merge.mergeJDF(spawnedNode, null, EnumCleanUpMerge.None, EnumAmountMerge.None);
			assertEquals(node.getID(), nodes[i].getID());
			assertEquals(root.getPartStatus(map1, 0), i == 2 ? EnumNodeStatus.Completed : EnumNodeStatus.Waiting);
			assertEquals(root.getPartStatus(map2, 0), EnumNodeStatus.Waiting);
			assertEquals(node.getPartStatus(map1, 0), EnumNodeStatus.Completed);
			assertEquals(node.getPartStatus(map2, 0), EnumNodeStatus.Waiting);
		}

	}

	// /////////////////////////////////////////////////////////////////////

	/**
	 * TODO @Rainer (2013-03-10) - This test case is not independent from other cases!
	 */
	//	@Test
	//	public void testBigMerge()
	//	{
	//		// testBigSpawn();
	//		final JDFParser parser = new JDFParser();
	//		final JDFDoc jdfDoc = parser.parseFile(sm_dirTestDataTemp + "bigMainPost.jdf");
	//		for (int i = 9; i > 0; i--)
	//		{
	//			File f = new File(sm_dirTestDataTemp + "bigSub" + i + ".jdf");
	//			assertTrue(String.format("File %s does not exist.", f.getAbsolutePath()), f.exists());
	//			
	//			final JDFParser parser2 = new JDFParser();
	//			final JDFDoc jdfDocSub = parser2.parseFile(sm_dirTestDataTemp + "bigSub" + i + ".jdf");
	//			final JDFNode nodeMain = jdfDoc.getJDFRoot();
	//			final JDFNode nodeSub = jdfDocSub.getJDFRoot();
	//			final JDFNode overWrite = new JDFMerge(nodeMain).mergeJDF(nodeSub, null, EnumCleanUpMerge.RemoveRRefs, EnumAmountMerge.UpdateLink);
	//			final JDFAuditPool ap = overWrite.getAuditPool();
	//			final JDFAudit audit = ap.getAudit(0, EnumAuditType.Merged, null, null);
	//			assertNotNull(audit);
	//			assertFalse(audit.hasAttribute(AttributeName.SPAWNID));
	//			assertNull(nodeMain.getMultipleIDs("ID"));
	//		}
	//		jdfDoc.write2File(sm_dirTestDataTemp + "BigMerge.jdf", 2, true);
	//	}

	// /////////////////////////////////////////////////////////////////////

	/**
	 * 
	 */
	@Test
	public void testCheckSpawnedResourcesRWImplicit()
	{
		for (int i = 0; i < 2; i++)
		{
			final JDFNode nr = new JDFDoc("JDF").getJDFRoot();
			nr.setType(EnumType.Product);
			JDFNode n = nr.addJDFNode("Combined");
			n.setCombined(new VString("Interpreting Rendering ImageSetting", null));
			final JDFRunList rl = (JDFRunList) n.addResource(ElementName.RUNLIST, null, EnumUsage.Output, null, nr, null, null);
			rl.setPartUsage(i == 0 ? EnumPartUsage.Implicit : EnumPartUsage.Explicit);
			rl.addPartition(EnumPartIDKey.Run, "r1");
			final VJDFAttributeMap vamParts = new VJDFAttributeMap();
			final JDFAttributeMap map = new JDFAttributeMap(AttributeName.RUN, "r1");
			vamParts.add(map);
			JDFSpawn spawn = new JDFSpawn(n);
			spawn.vSpawnParts = vamParts;
			spawn.vRWResources_in = new VString("RunList", null);
			spawn.spawn();
			map.put(AttributeName.RUN, "r2");// doesn't exist
			JDFNode nsp = spawn.spawn();
			assertNotNull(nsp.getResource("RunList", EnumUsage.Output, 0).getPartition(map, null));
		}
	}

	/**
	 * 
	 */
	@Test
	public void testCheckSpawnedResources()
	{
		final String strJDFName = "000023_Test_PR3.0.jdf";
		final String strJDFPath = sm_dirTestData + strJDFName;
		final JDFParser parser = new JDFParser();
		final JDFDoc jdfDoc = parser.parseFile(strJDFPath);
		// jdfDoc.getCreateXMLDocUserData().setDirtyPolicy(EnumDirtyPolicy.None);
		final VJDFAttributeMap vamParts = new VJDFAttributeMap();
		final JDFAttributeMap amParts0 = new JDFAttributeMap();
		amParts0.put("Side", "Front");
		amParts0.put("SignatureName", "Sig002");
		amParts0.put("SheetName", "FB 002");
		vamParts.add(amParts0);

		final JDFAttributeMap amParts1 = new JDFAttributeMap();
		amParts1.put("Side", "Back");
		amParts1.put("SignatureName", "Sig002");
		amParts1.put("SheetName", "FB 002");
		vamParts.add(amParts1);

		final VString vsRWResourceIDs = new VString();
		vsRWResourceIDs.add("Link84847227_000309");
		vsRWResourceIDs.add("r85326439_027691");

		final JDFNode nodeProc = jdfDoc.getJDFRoot().getJobPart("IPD0.I", null);
		final Collection<JDFResource> vSpawned = nodeProc.checkSpawnedResources(vsRWResourceIDs, vamParts);
		assertNull(vSpawned);
		final JDFSpawn spawn = new JDFSpawn(nodeProc);
		spawn.vRWResources_in = vsRWResourceIDs;
		spawn.vSpawnParts = vamParts;
		final JDFNode s2 = spawn.spawn();
		assertNotNull(spawn.checkSpawnedResources());
		assertNull(s2.getMultipleIDs("ID"));

		try
		{
			spawn.spawn();
			fail("multi rw spawn");
		}
		catch (final JDFException x)
		{ // nop
		}
		spawn.bSpawnRWPartsMultiple = true;
		assertNotNull(spawn.checkSpawnedResources());
		spawn.spawn();

	}

	/**
	 * 
	 */
	@Test
	public void testBookintent()
	{
		final String fileNameIn = "bookintent.jdf";
		final String fileNameOut = "spawned.jdf";
		final String spawnNodeID = "n0016";

		final VString vRWResources = new VString();
		vRWResources.addElement("Component");
		vRWResources.addElement("RunList");
		final VJDFAttributeMap vSpawnParts = new VJDFAttributeMap();

		final String strSpawnID = spawn(fileNameIn, fileNameOut, spawnNodeID, vRWResources, vSpawnParts);
		unSpawn(fileNameIn, strSpawnID); // "Sp7cb:-7fff"
	}

	/**
	 * 
	 * @param strXMLFile
	 * @param strSpawnedFile
	 * @param strElementID
	 * @param vRWResources
	 * @param vSpawnParts
	 * @return
	 */
	private String spawn(final String strXMLFile, final String strSpawnedFile, final String strElementID, final VString vRWResources, final VJDFAttributeMap vSpawnParts)
	{
		String strSpawnID = JDFConstants.EMPTYSTRING;

		// parse input file
		final JDFParser p = new JDFParser();
		final JDFDoc jdfDocIn = p.parseFile(sm_dirTestData + strXMLFile);
		assertTrue("Parse of file " + sm_dirTestData + strXMLFile + " failed", jdfDocIn != null);

		if (jdfDocIn != null)
		{
			// prepare the spawn process
			final JDFNode rootIn = (JDFNode) jdfDocIn.getRoot();

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
				final JDFSpawn _spawn = new JDFSpawn(spawnNode);
				JDFNode jdfSpawned = _spawn.spawnInformative(sm_dirTestData + strXMLFile, null, null, false, true, true, true);
				String spawnID = jdfSpawned.getSpawnID(false);

				String big = jdfDocIn.write2String(0);
				assertTrue("no spawnID in main", big.indexOf(spawnID) < 0);
				final JDFSpawn spawn1 = new JDFSpawn(spawnNode);

				jdfSpawned = spawn1.spawn(sm_dirTestData + strXMLFile, null, vRWResources, vSpawnParts, false, true, true, true);
				spawnID = jdfSpawned.getSpawnID(false);
				big = jdfDocIn.write2String(0);
				assertTrue("spawnID in main", big.indexOf(spawnID) >= 0);

				// neu gespawntes File rausschreiben
				final JDFNode rootOut = jdfSpawned;
				final XMLDoc docOut = rootOut.getOwnerDocument_KElement();
				docOut.write2File(sm_dirTestDataTemp + strSpawnedFile, 0, true);

				// ver�ndertes Ausgangsfile rausschreiben
				rootIn.eraseEmptyNodes(true);
				final String strXMLFileModified = "_" + strXMLFile;
				jdfDocIn.write2File(sm_dirTestDataTemp + strXMLFileModified, 0, true);

				// Vergleich, ob alles richtig gelaufen ist
				// compareXMLFiles (strXMLFileModified,
				// strXMLFileModifiedMaster);
				// compareXMLFiles (strSpawnedFile+".jdf",
				// strSpawnedFileMaster);

				strSpawnID = rootOut.getSpawnID(false);
			}
		}

		return strSpawnID;
	}

	// ////////////////////////////////////////////////////////

	/**
	 * 
	 */
	@Test
	public void testNestedSpawn()
	{
		JDFNode root = JDFDoc.parseFile(sm_dirTestData + "spawnRoot.jdf").getJDFRoot();
		JDFAttributeMap map = new JDFAttributeMap();
		map.put("Run", "Run_100303_102859963_000349");
		JDFNode n2s = root;
		VJDFAttributeMap v = new VJDFAttributeMap();
		JDFAttributeMap map2 = new JDFAttributeMap(map);
		Vector<JDFNode> vn = new Vector<JDFNode>();
		for (int i = 1; i < 10; i++)
		{
			for (int j = i; j < 40; j++)
			{
				map2.put("RunPage", "" + j);
				v.add(new JDFAttributeMap(map2));
			}
			JDFSpawn sp = new JDFSpawn(n2s);
			JDFNode n3s = sp.spawn(null, null, new VString("RunList NodeInfo", null), v, false, true, true, true);
			vn.add(n3s);
			n2s.getOwnerDocument_JDFElement().write2File(sm_dirTestDataTemp + "rootMainNest" + i + ".jdf", 2, false);
			n3s.getOwnerDocument_JDFElement().write2File(sm_dirTestDataTemp + "rootSubNest" + i + ".jdf", 2, false);
			n2s = n3s;
		}
		JDFMerge m = new JDFMerge(root);
		for (int i = 0; i < 9; i++)
		{
			JDFNode mn = m.mergeJDF(vn.elementAt(i));
			mn.getOwnerDocument_JDFElement().write2File(sm_dirTestDataTemp + "mergeNest" + i + ".jdf", 2, false);
		}

	}

	/**
	 * 
	 */
	@Test
	public void testSpawnRootNestedPerformance()
	{
		JDFNode root = JDFDoc.parseFile(sm_dirTestData + "spawnRoot.jdf").getJDFRoot();
		JDFAttributeMap map = new JDFAttributeMap();
		map.put("Run", "Run_100303_102859963_000349");
		JDFNode n2s = root;
		CPUTimer ct = new CPUTimer(true);
		for (int i = 1; i < 100; i++)
		{
			JDFSpawn sp = new JDFSpawn(n2s);
			VJDFAttributeMap v = new VJDFAttributeMap();
			JDFAttributeMap map2 = new JDFAttributeMap(map);
			for (int j = i; j < 400; j++)
			{
				map2.put("RunPage", "" + j);
				v.add(new JDFAttributeMap(map2));
			}
			ct.start();
			root = n2s;
			n2s = sp.spawn(null, null, new VString("RunList:Output", null), v, false, true, true, true);
			System.out.println(i + " " + ct);
			ct.stop();
			if (i == 7)
			{
				root.getOwnerDocument_JDFElement().write2File(sm_dirTestDataTemp + "rootMainNest.jdf", 2, false);
				n2s.getOwnerDocument_JDFElement().write2File(sm_dirTestDataTemp + "rootSubNest.jdf", 2, false);
			}
		}
	}

	/**
	 * 
	 */
	@Test
	public void testSpawnRootListPerformance()
	{
		JDFNode root = JDFDoc.parseFile(sm_dirTestData + "spawnRoot.jdf").getJDFRoot();
		JDFAttributeMap map = new JDFAttributeMap();
		map.put("Run", "Run_100303_102859963_000349");
		JDFNode n2s = root;
		CPUTimer ct = new CPUTimer(false);
		JDFSpawn sp = new JDFSpawn(n2s);
		VElement nodes = new VElement();
		nodes.add(null);
		for (int i = 1; i < 400; i++)
		{
			VJDFAttributeMap v = new VJDFAttributeMap();
			JDFAttributeMap map2 = new JDFAttributeMap(map);
			map2.put("RunPage", "" + i);
			v.add(map2);
			ct.start();
			JDFNode node = sp.spawn(null, null, new VString("RunList:Output", null), v, false, true, true, true);
			nodes.add(node);
			if ((i % 100) == 99)
				System.out.println(i + " " + ct.toString());
			ct.stop();
		}
		ct = new CPUTimer(false);
		for (int i = 1; i < 400; i++)
		{
			ct.start();
			JDFMerge m = new JDFMerge(root);
			m.mergeJDF((JDFNode) nodes.get(i), null, EnumCleanUpMerge.RemoveAll, EnumAmountMerge.UpdateLink);
			nodes.setElementAt(null, i);
			if ((i % 100) == 99)
				System.out.println(i + " " + ct.toString());
			ct.stop();
		}
		root.getOwnerDocument_JDFElement().write2File(sm_dirTestDataTemp + "sm_many.jdf", 0, true);
	}

	/**
	 * 
	 */
	@Test
	public void testMergeRootList()
	{
		File f = new File(sm_dirTestData + "rootSubNest.jdf");
		assertTrue(String.format("File %s does not exist.", f.getAbsolutePath()), f.exists());
		JDFNode sub = JDFDoc.parseFile(sm_dirTestData + "rootSubNest.jdf").getJDFRoot();

		JDFNode main = JDFDoc.parseFile(sm_dirTestData + "rootMainNest.jdf").getJDFRoot();
		CPUTimer ct = new CPUTimer(false);
		for (int i = 0; i < 10; i++)
		{
			JDFNode clone = (JDFNode) main.clone();
			JDFNode cloneSub = (JDFNode) sub.clone();
			ct.start();
			JDFMerge m = new JDFMerge(clone);
			m.mergeJDF(cloneSub, null, EnumCleanUpMerge.None, EnumAmountMerge.UpdateLink);
			System.out.println(i + " " + ct.toString());
			ct.stop();
		}
	}

	/**
	 * 
	 */
	@Test
	public void testSpawnSheetMix()
	{
		final JDFNode nr = new JDFDoc("JDF").getJDFRoot();
		nr.setType(EnumType.Product);
		JDFNode n = nr.addJDFNode("Combined");
		final JDFNodeInfo ni = n.getCreateNodeInfo();
		n.setCombined(new VString("Interpreting Rendering ImageSetting", null));
		final JDFRunList rl = (JDFRunList) n.addResource(ElementName.RUNLIST, null, EnumUsage.Output, null, nr, null, null);
		rl.setPartUsage(EnumPartUsage.Implicit);
		final VString pik = new VString("SignatureName SheetName Side Separation PartVersion", null);
		final JDFAttributeMap map = new JDFAttributeMap(AttributeName.SIGNATURENAME, "Sig1");
		map.put(AttributeName.PARTVERSION, "en");
		map.put(AttributeName.SHEETNAME, "s1");
		map.put(AttributeName.SIDE, "Front");
		map.put(AttributeName.SEPARATION, "Black");
		rl.getCreatePartition(map, pik);
		ni.getCreatePartition(map, pik);
		map.put(AttributeName.SEPARATION, "Cyan");
		rl.getCreatePartition(map, pik);
		ni.getCreatePartition(map, pik);
		map.put(AttributeName.SEPARATION, "Yellow");
		ni.getCreatePartition(map, pik);

		final VJDFAttributeMap vm = new VJDFAttributeMap();
		final JDFAttributeMap ms = new JDFAttributeMap(AttributeName.SIGNATURENAME, "Sig1");
		ms.put(AttributeName.PARTVERSION, "en");
		ms.put(AttributeName.SEPARATION, "Black");
		vm.add(ms);
		final JDFAttributeMap ms2 = new JDFAttributeMap(ms);
		ms2.put(AttributeName.SEPARATION, "Yellow"); // not there...
		vm.add(ms2);

		final JDFSpawn sp = new JDFSpawn(n);
		sp.vSpawnParts = vm;
		sp.vRWResources_in = new VString("Output", null);
		sp.bFixResources = false;

		final JDFNode spawned = sp.spawn();

		final JDFRunList rlS = (JDFRunList) spawned.getResourceRoot(ElementName.RUNLIST, null, 0);
		// why??? assertTrue(rlS.toString().indexOf("Cyan")>0);
		map.put(AttributeName.SEPARATION, "Magenta");
		rlS.getCreatePartition(map, pik);

		assertTrue(rl.toString().indexOf("SpawnedRW") > 0);
		assertEquals(spawned.getAncestorPool().getPartMapVector(), vm);

		//		assertEquals(rl.getPartition(ms2, null).getAttribute_KElement(AttributeName.SPAWNSTATUS), "SpawnedRW");
		// assertEquals(rl.getPartition(ms,
		// null).getAttribute_KElement(AttributeName.SPAWNSTATUS),"");

		final JDFMerge m = new JDFMerge(nr);
		n = m.mergeJDF(spawned, null, EnumCleanUpMerge.RemoveAll, EnumAmountMerge.UpdateLink);

		assertTrue(rl.toString().indexOf("SpawnedRW") < 0);
		assertTrue(n.toString().indexOf("SpawnedRW") < 0);
		assertTrue(n.toString().indexOf("SpawnID") < 0);
		assertNotNull(rl.getPartition(map, null));
	}

	/**
	 * 
	 */
	// ////////////////////////////////////////////////////////
	@Test
	public void testSpawnSheetNeedsSide()
	{
		final JDFDoc readJDF = JDFDoc.parseFile(sm_dirTestData + "pdyv5.jdf");
		assertNotNull(readJDF);
		final JDFNode root = readJDF.getJDFRoot();
		final JDFNode spawnNode = (JDFNode) root.getTarget("Link070822_032611973_013511", AttributeName.ID);
		final VJDFAttributeMap vSpawnParts = new VJDFAttributeMap();
		// "ImP1.MR" with "VJDFAttributeMap: [0]JDFAttributeMap: { (PartVersion
		// = Eng Eng) (SheetName = S0C) (SignatureName = Sig001) }
		final JDFAttributeMap partMap = new JDFAttributeMap();
		partMap.put("PartVersion", "Eng Eng");
		partMap.put("SheetName", "S0C");
		partMap.put("SignatureName", "Sig001");

		vSpawnParts.add(partMap);
		final VString vRWResources_in = new VString();
		final JDFNode spawnedNode = new JDFSpawn(spawnNode).spawn("parentURL", "spawnURL", vRWResources_in, vSpawnParts, false, true, false, false);
		final JDFNodeInfo ni = spawnedNode.getCreateNodeInfo();
		assertNull(ni.getPartition(partMap, EnumPartUsage.Explicit));
		partMap.put("Side", "Front");
		assertNotNull(ni.getPartition(partMap, EnumPartUsage.Explicit));
	}

	/**
	 * 
	 */
	@Test
	public void testSpawnNameSpace()
	{
		for (int i = 0; i < 3; i++)
		{
			final JDFNode node = new JDFDoc("JDF").getJDFRoot();
			node.setType("ProcessGroup", false);
			final JDFResource r = node.addResource("RunList", null);
			final JDFNode n2 = node.addJDFNode("Dummy");
			n2.linkResource(r, EnumUsage.Input, null);
			if (i == 0)
			{
				// best!
				r.setAttribute("foo:bar", "test", "fooNS");

			}
			else if (i == 1)
			{
				// ok
				node.setAttribute("xmlns:foo", "fooNS");
				r.setAttribute("foo:bar", "test");
			}
			else
			{
				// this one is bad because there is no hook to link prefix and
				// ns at time of attribute setting
				// 100121 - added fix for this one too
				r.setAttribute("foo:bar", "test");
				node.setAttribute("xmlns:foo", "fooNS");
			}
			final JDFSpawn spawn = new JDFSpawn(n2);
			final JDFNode n3 = spawn.spawn();

			final String n3String = n3.getOwnerDocument_JDFElement().write2String(2);
			final JDFParser parser = new JDFParser();
			assertNotNull("ns, bad parse loop=" + i, parser.parseString(n3String));
		}
	}

	/**
	 * 
	 */
	@Test
	public void testPartitionedSpawn()
	{
		final String fileNameIn = "partitionedSource.jdf";
		final String fileNameOut = "partitionedSpawned.jdf";
		final String spawnNodeID = "n0016";

		final VString vRWResources = new VString();
		vRWResources.addElement("Component");
		vRWResources.addElement("RunList");

		final JDFAttributeMap spawnPart = new JDFAttributeMap();
		final VJDFAttributeMap vSpawnParts = new VJDFAttributeMap();
		// spawnPart.put("Class", "Intent");
		vSpawnParts.addElement(spawnPart);

		final String strSpawnID = spawn(fileNameIn, fileNameOut, spawnNodeID, vRWResources, vSpawnParts);
		unSpawn(fileNameIn, strSpawnID);
	}

	/**
	 * 
	 */
	@Test
	public void testPartitionedSpawnNI()
	{
		for (int i = 0; i < 2; i++)
		{
			final JDFDoc d = new JDFDoc("JDF");
			final JDFNode nRoot = d.getJDFRoot();
			nRoot.setType(EnumType.ProcessGroup);
			final JDFNode n2 = nRoot.addJDFNode(EnumType.Buffer);
			final JDFAttributeMap map = new JDFAttributeMap(EnumPartIDKey.SheetName, "s1");
			final JDFNodeInfo ni = (JDFNodeInfo) n2.addResource(ElementName.NODEINFO, null, EnumUsage.Input, null, null, null, null);
			final JDFBufferParams bp = (JDFBufferParams) n2.addResource(ElementName.BUFFERPARAMS, null, EnumUsage.Input, null, nRoot, null, null);
			bp.addPartition(EnumPartIDKey.SheetName, "s1");
			final JDFComponent comp = (JDFComponent) n2.addResource(ElementName.COMPONENT, null, EnumUsage.Output, null, nRoot, null, null);
			comp.addPartition(EnumPartIDKey.SheetName, "s1");
			final JDFSpawn spawn = new JDFSpawn(n2);
			spawn.vRWResources_in = new VString("Output NodeInfo", " ");
			spawn.vSpawnParts = new VJDFAttributeMap();
			spawn.vSpawnParts.add(map);
			if (i == 0)
			{
				spawn.bFixResources = false;
			}
			final JDFNode spawnedNode = spawn.spawn();
			assertTrue(ni.toString().indexOf(AttributeName.SPAWNIDS) > 0);
			final JDFNodeInfo ni2 = spawnedNode.getNodeInfo();
			assertTrue(ni2.toString().indexOf(AttributeName.SPAWNIDS) > 0);
		}

	}

	/**
	 * 
	 */
	@Test
	public void testRef()
	{
		final String fileNameIn = "ref.jdf";
		final String fileNameOut = "spawn.jdf";
		final String spawnNodeID = "n0027";

		final VString vRWResources = new VString();
		vRWResources.addElement("Media");
		vRWResources.addElement("ExposedMedia");
		final VJDFAttributeMap vSpawnParts = new VJDFAttributeMap();

		final String strSpawnID = spawn(fileNameIn, fileNameOut, spawnNodeID, vRWResources, vSpawnParts);
		unSpawn(fileNameIn, strSpawnID);
	}

	// ////////////////////////////////////////////////////////

	/**
	 * 
	 */
	@Test
	public void testMergeExpandedPartition()
	{
		final JDFDoc d = new JDFDoc("JDF");
		final JDFNode n = d.getJDFRoot();
		final JDFAttributeMap partMap = new JDFAttributeMap();
		partMap.put("SheetName", "S1");
		partMap.put("Side", "Front");
		final JDFAttributeMap partMap2 = new JDFAttributeMap(partMap);
		partMap2.put("Separation", "Black");
		final JDFTransferCurvePool tcp = (JDFTransferCurvePool) n.addResource(ElementName.TRANSFERCURVEPOOL, EnumUsage.Output);
		// final JDFTransferCurvePool tcPart = (JDFTransferCurvePool)
		tcp.getCreatePartition(partMap, new VString("SheetName Side", null));

		final JDFSpawn sp = new JDFSpawn(n);
		final VJDFAttributeMap spawnParts = new VJDFAttributeMap();
		spawnParts.add(partMap2); // want more granular
		final JDFNode spNode = sp.spawn(null, null, new VString(ElementName.TRANSFERCURVEPOOL, null), spawnParts, false, false, false, false);
		final JDFTransferCurvePool tpSpawn = (JDFTransferCurvePool) spNode.getResource(ElementName.TRANSFERCURVEPOOL, null, 0);
		tpSpawn.getCreatePartition(partMap2, null);

		final JDFMerge m = new JDFMerge(n);
		final JDFNode merged = m.mergeJDF(spNode, null, null, null);
		assertTrue(merged.toString().indexOf("SpawnIDS") < 0);

	}

	/**
	 * 
	 */
	@Test
	public void testMergeNewNamespace()
	{
		final JDFDoc d = new JDFDoc("JDF");
		final JDFNode n = d.getJDFRoot();
		final JDFAttributeMap partMap = new JDFAttributeMap();
		partMap.put("SheetName", "S1");
		partMap.put("Side", "Front");
		final JDFTransferCurvePool tcp = (JDFTransferCurvePool) n.addResource(ElementName.TRANSFERCURVEPOOL, EnumUsage.Output);
		JDFTransferCurvePool tcpPart = (JDFTransferCurvePool) tcp.getCreatePartition(partMap, new VString("SheetName Side", null));

		tcpPart.setAttribute("MYNS:Foo", "bar", "www.myns.com");
		n.setAttributeRaw("xmlns:MYNS", "www.MYNS.com");
		n.setAttributeRaw("MYNS:Foo", "bar");

		final JDFSpawn sp = new JDFSpawn(n);
		final VJDFAttributeMap spawnParts = new VJDFAttributeMap();
		spawnParts.add(partMap); // want more granular
		final JDFNode spNode = sp.spawn(null, null, new VString(ElementName.TRANSFERCURVEPOOL, null), spawnParts, false, false, false, false);
		JDFTransferCurvePool tcpSpawn = (JDFTransferCurvePool) spNode.getResource(ElementName.TRANSFERCURVEPOOL, EnumUsage.Output, 0);
		spNode.addNameSpace("MYNS", "www.myns.com");
		JDFTransferCurvePool tcpSpawnPart = (JDFTransferCurvePool) tcpSpawn.getCreatePartition(partMap, new VString("SheetName Side", null));
		tcpSpawnPart.setAttribute("MYNS:Foo2", "bar");
		final JDFMerge m = new JDFMerge(n);
		final JDFNode merged = m.mergeJDF(spNode, null, null, null);
		assertTrue(merged.toString().toLowerCase().indexOf("ns1") < 0);

	}

	/**
	 * 
	 */
	@Test
	public void testMergeNewResource()
	{
		final JDFDoc d = new JDFDoc("JDF");
		final JDFNode n = d.getJDFRoot();
		final JDFAttributeMap partMap = new JDFAttributeMap();
		partMap.put("SheetName", "S1");
		partMap.put("Side", "Front");
		final JDFTransferCurvePool tcp = (JDFTransferCurvePool) n.addResource(ElementName.TRANSFERCURVEPOOL, EnumUsage.Output);
		tcp.getCreatePartition(partMap, new VString("SheetName Side", null));

		final JDFSpawn sp = new JDFSpawn(n);
		final VJDFAttributeMap spawnParts = new VJDFAttributeMap();
		spawnParts.add(partMap); // want more granular
		final JDFNode spNode = sp.spawn(null, null, new VString(ElementName.TRANSFERCURVEPOOL, null), spawnParts, false, false, false, false);

		JDFResource r = spNode.addResource("RunList", EnumUsage.Input);
		r.getCreatePartition(partMap, new VString("SheetName Side", null));
		JDFResourceLink rl = spNode.getLink(r, null);
		rl.setPartMap(partMap);

		final JDFMerge m = new JDFMerge(n);
		final JDFNode merged = m.mergeJDF(spNode, null, null, null);
		assertTrue(merged.toString().indexOf("SpawnIDS") < 0);

		rl = merged.getLink(0, "RunList", null, null);
		assertNotNull(rl);
	}

	/**
	 * 
	 */
	@Test
	public void testMergeResourceOrder()
	{
		final JDFDoc d = new JDFDoc("JDF");
		final JDFNode n = d.getJDFRoot();
		final JDFAttributeMap partMap = new JDFAttributeMap();
		partMap.put("SheetName", "S1");
		final JDFTransferCurvePool tcp = (JDFTransferCurvePool) n.addResource(ElementName.TRANSFERCURVEPOOL, EnumUsage.Output);
		tcp.getCreatePartition(partMap, null);

		final JDFSpawn sp = new JDFSpawn(n);
		final VJDFAttributeMap spawnParts = new VJDFAttributeMap();
		spawnParts.add(new JDFAttributeMap("SheetName", "S1")); // want more granular
		final JDFNode spNode = sp.spawn(null, null, new VString(ElementName.TRANSFERCURVEPOOL, null), spawnParts, false, false, false, false);

		JDFTransferCurvePool tcps = (JDFTransferCurvePool) spNode.getResource(ElementName.TRANSFERCURVEPOOL, EnumUsage.Output, 0).getCreatePartition(partMap, null);
		JDFTransferCurvePool tcpsf = (JDFTransferCurvePool) tcps.addPartition(EnumPartIDKey.SectionIndex, "Front");
		JDFTransferCurvePool tcpsb = (JDFTransferCurvePool) tcps.addPartition(EnumPartIDKey.SectionIndex, "Back");
		for (int i = 0; i < 4; i++)
		{
			tcpsf.addPartition(EnumPartIDKey.PartVersion, "V" + i);
			tcpsb.addPartition(EnumPartIDKey.PartVersion, "V" + i);
		}

		final JDFMerge m = new JDFMerge(n);
		final JDFNode merged = m.mergeJDF(spNode, null, null, null);
		assertTrue(merged.toString().indexOf("SpawnIDS") < 0);

		JDFResource tcpMerged = merged.getResource(ElementName.TRANSFERCURVEPOOL, EnumUsage.Output, 0);
		VElement v = tcpMerged.getLeaves(false);
		assertNotNull(v);
	}

	/**
	* 
	*/
	@Test
	public void testRemerge()
	{
		for (int i = 0; i < 2; i++)
		{
			final JDFDoc d = new JDFDoc("JDF");
			final JDFNode n = d.getJDFRoot();
			final JDFAttributeMap partMap = new JDFAttributeMap();
			partMap.put("SheetName", "S1");
			partMap.put("Side", "Front");
			final JDFExposedMedia xp = (JDFExposedMedia) n.addResource(ElementName.EXPOSEDMEDIA, EnumUsage.Output);
			xp.getCreatePartition(partMap, new VString("SheetName Side", null));

			final JDFSpawn sp = new JDFSpawn(n);
			final VJDFAttributeMap spawnParts = new VJDFAttributeMap();
			spawnParts.add(partMap); // want more granular
			final JDFNode spNode = sp.spawn(null, null, new VString(ElementName.EXPOSEDMEDIA, null), spawnParts, false, false, false, false);

			JDFExposedMedia xmSpawn = (JDFExposedMedia) spNode.getResource(ElementName.EXPOSEDMEDIA, null, 0);
			JDFResourceLink rl = spNode.getLink(xmSpawn, null);
			rl.setActualAmount(2, partMap);

			final JDFMerge m = new JDFMerge(n);
			if (i == 1)
				m.setCleanPolicy(EnumCleanUpMerge.RemoveAll);

			final JDFNode merged = m.mergeJDF((JDFNode) spNode.clone());

			JDFExposedMedia xmMerged = (JDFExposedMedia) merged.getResource(ElementName.EXPOSEDMEDIA, null, 0);
			JDFResourceLink rlm = merged.getLink(xmMerged, null);
			assertEquals("", rlm.getActualAmount(partMap), 2.0, 0.0);

			rl.setActualAmount(3, partMap);
			spNode.getCreateAuditPool().addProcessRun(EnumNodeStatus.Suspended, null, spawnParts);
			final JDFNode remerged = m.remergeJDF(spNode);
			JDFExposedMedia xmreMerged = (JDFExposedMedia) remerged.getResource(ElementName.EXPOSEDMEDIA, null, 0);
			JDFResourceLink rlrm = merged.getLink(xmreMerged, null);
			assertEquals(rlrm.getActualAmount(partMap), 3.0, 0.0);
		}

	}

	/**
	 * 
	 */
	@Test
	public void testMergeRemovedResource()
	{
		for (int i = 1; i < 2; i++)
		{
			final JDFDoc d = new JDFDoc("JDF");
			JDFNode n = d.getJDFRoot();
			final JDFAttributeMap partMap = new JDFAttributeMap();
			partMap.put("SheetName", "S1");
			partMap.put("Side", "Front");
			final JDFTransferCurvePool tcp = (JDFTransferCurvePool) n.addResource(ElementName.TRANSFERCURVEPOOL, EnumUsage.Output);
			tcp.getCreatePartition(partMap, new VString("SheetName Side", null));

			JDFResource r = n.addResource("NodeInfo", EnumUsage.Input);
			r.getCreatePartition(partMap, new VString("SheetName Side", null));
			r = n.addResource("RunList", EnumUsage.Output);
			r.getCreatePartition(partMap, new VString("SheetName Side", null));
			final JDFNode spawnNode;
			if (i == 1)
			{
				n.setType(JDFNode.EnumType.ProcessGroup);
				JDFNode n2 = n.addJDFNode(JDFNode.EnumType.ProcessGroup);
				r = n2.addResource("NodeInfo", EnumUsage.Input);
				r.getCreatePartition(partMap, new VString("SheetName Side", null));
				n2 = n2.addJDFNode(JDFNode.EnumType.ProcessGroup);
				n2.copyElement(n.getResourceLinkPool(), null);
				spawnNode = n2;
			}
			else
			{
				spawnNode = n;
			}
			final JDFSpawn sp = new JDFSpawn(spawnNode);
			final VJDFAttributeMap spawnParts = new VJDFAttributeMap();
			spawnParts.add(partMap); // want more granular
			final JDFNode spNode = sp.spawn(null, null, new VString(ElementName.RUNLIST, null), spawnParts, false, false, false, false);

			assertNotNull(spNode.getResource("RunList", null, 0));
			JDFResourceLink rl1 = spNode.getLink(0, "RunList", null, null);
			rl1.getLinkRoot().deleteNode();
			rl1.deleteNode();
			assertNull(spNode.getResource("RunList", null, 0));

			final JDFMerge m = new JDFMerge(n);
			final JDFNode merged = m.mergeJDF(spNode, null, null, null);
			assertTrue(merged.toString().indexOf("SpawnIDS") < 0);

			JDFResourceLink rl = merged.getLink(0, "RunList", null, null);
			assertNotNull(rl);
			assertNotNull(rl.getTarget());
			assertTrue(rl.getTarget().toXML().indexOf("Spawn") < 0);
		}
	}

	/**
	 * 
	 */
	@Test
	public void testSpawnMultiDepthRWResource()
	{
		final JDFDoc d = new JDFDoc("JDF");
		JDFNode n = d.getJDFRoot();
		final JDFAttributeMap partMap = new JDFAttributeMap();
		partMap.put("SignatureName", "S1");
		partMap.put("SheetName", "S1");
		partMap.put("Side", "Front");
		final JDFAttributeMap partMapSheet = new JDFAttributeMap();
		partMapSheet.put("SignatureName", "S1");
		partMapSheet.put("SheetName", "S1");
		final JDFAttributeMap partMapSig = new JDFAttributeMap();
		partMapSig.put("SignatureName", "S1");

		JDFResource r = n.addResource("RunList", EnumUsage.Output);
		r.getCreatePartition(partMap, new VString("SignatureName SheetName Side", null));
		n.setType(JDFNode.EnumType.ProcessGroup);
		JDFNode n2 = n.addJDFNode(JDFNode.EnumType.ProcessGroup);
		r = n2.addResource("NodeInfo", EnumUsage.Input);
		r.getCreatePartition(partMap, new VString("SignatureName SheetName Side", null));
		n2 = n2.addJDFNode(JDFNode.EnumType.ProcessGroup);
		n2.copyElement(n.getResourceLinkPool(), null);

		final JDFSpawn sp = new JDFSpawn(n2);
		final VJDFAttributeMap spawnPartsSheet = new VJDFAttributeMap();
		spawnPartsSheet.add(partMapSheet);
		final VJDFAttributeMap spawnParts = new VJDFAttributeMap();
		spawnParts.add(partMap);
		final VJDFAttributeMap spawnPartsSig = new VJDFAttributeMap();
		spawnPartsSig.add(partMapSig);
		final JDFNode spNodeSheet = sp.spawn(null, null, new VString(ElementName.RUNLIST, null), spawnPartsSheet, false, false, false, false);
		assertNotNull(spNodeSheet);
		try
		{
			sp.spawn(null, null, new VString(ElementName.RUNLIST, null), spawnParts, false, false, false, false);
			fail("multi bad");
		}
		catch (JDFException x)
		{
		}
		try
		{
			sp.spawn(null, null, new VString(ElementName.RUNLIST, null), spawnPartsSig, false, false, false, false);
			fail("multi bad");
		}
		catch (JDFException x)
		{
		}
	}

	/**
	 * test merging amounts - also over multiple nodes
	 * 
	 */
	@Test
	public void testMergeAmount()
	{
		JDFNode n = new JDFDoc("JDF").getJDFRoot();
		n.setType(EnumType.ProcessGroup);
		JDFNode n2 = n.addJDFNode(EnumType.ConventionalPrinting);
		JDFNode n3 = n.addJDFNode(EnumType.ConventionalPrinting);
		JDFComponent c = (JDFComponent) n.addResource(ElementName.COMPONENT, EnumUsage.Output);
		JDFResourceLink rl = n.ensureLink(c, EnumUsage.Output, null);
		rl.setAmount(700, null);
		JDFResourceLink rl2 = n2.ensureLink(c, EnumUsage.Output, null);
		rl2.setAmount(200, null);
		JDFResourceLink rl3 = n3.ensureLink(c, EnumUsage.Output, null);
		rl3.setAmount(400, null);
		JDFSpawn sp2 = new JDFSpawn(n2);
		sp2.vRWResources_in = new VString("Component", null);
		JDFNode n2s = sp2.spawn();
		JDFResourceLink rl2s = n2s.ensureLink(c, EnumUsage.Output, null);
		rl2s.setActualAmount(222, new JDFAttributeMap("Condition", "Good"));
		rl2s.setActualAmount(33, new JDFAttributeMap("Condition", "Waste"));
		JDFMerge merge = new JDFMerge(n);
		merge.setAmountPolicy(EnumAmountMerge.LinkOnly);
		n = merge.mergeJDF(n2s);

		assertEquals(((JDFComponent) n.getResource("Component", null, 0)).getAmountProduced(), 222, 0);
		n = n.getParentJDF();
		JDFSpawn sp3 = new JDFSpawn(n3);
		sp3.vRWResources_in = new VString("Component", null);
		JDFNode n3s = sp3.spawn();
		JDFResourceLink rl3s = n3s.ensureLink(c, EnumUsage.Output, null);
		rl3s.setActualAmount(555, new JDFAttributeMap("Condition", "Good"));
		rl3s.setActualAmount(66, new JDFAttributeMap("Condition", "Waste"));
		merge = new JDFMerge(n);
		merge.setAmountPolicy(EnumAmountMerge.LinkOnly);
		n = merge.mergeJDF(n3s);
		n = n.getParentJDF();
		assertEquals(((JDFComponent) n.getResource("Component", null, 0)).getAmountProduced(), 777, 0);
	}

	/**
	 * 
	 */
	@Test
	public void testMergeJDF()
	{
		// job.jdf subjdf.jdf -o merged.jdf
		final String m_xmlFile1 = "_bookintent.jdf";
		final String m_xmlFile2 = "spawned.jdf";
		final String m_outFile = "merged.jdf";
		JDFDoc m_jdfDoc;
		JDFDoc m_jdfDoc2;

		final JDFParser p = new JDFParser();
		m_jdfDoc = p.parseFile(sm_dirTestDataTemp + m_xmlFile1);

		assertNotNull(sm_dirTestDataTemp + m_xmlFile1 + ": Parse Error\n" + "MergeJDF: JDF merger simulation;\n" + "Arguments: 1=parent input JDF, 2=child input JDF;\n"
				+ "-o: output JDF;\n" + "-d: delete completed tasks from the output JDF\n", m_jdfDoc);

		final JDFParser p2 = new JDFParser();
		m_jdfDoc2 = p2.parseFile(sm_dirTestDataTemp + m_xmlFile2);

		assertTrue(sm_dirTestDataTemp + m_xmlFile2 + ": Parse Error", m_jdfDoc2 != null);
		if (m_jdfDoc2 == null)
		{
			return; // soothe findbugs ;)
		}

		final JDFNode rootMain = m_jdfDoc.getJDFRoot();
		final JDFNode rootSpawn = m_jdfDoc2.getJDFRoot();
		rootSpawn.setDescriptiveName("fixup");
		final String root2ID = rootSpawn.getID();
		final JDFComment c2 = rootSpawn.appendComment();
		c2.setText("Comment 1");

		new JDFMerge(rootMain).mergeJDF(rootSpawn, sm_dirTestDataTemp + m_xmlFile2, JDFNode.EnumCleanUpMerge.None, JDFResource.EnumAmountMerge.None);
		final JDFNode nodemerged = rootMain.getChildJDFNode(root2ID, false);
		assertTrue("MergeJDF fixup", nodemerged.getDescriptiveName().equals("fixup"));

		assertNull(m_jdfDoc.getRoot().getMultipleIDs("ID"));
		m_jdfDoc.write2File(sm_dirTestDataTemp + m_outFile, 2, true);

		assertTrue("MergeJDF ok", true);
	}

	// ////////////////////////////////////////////////////////

	/**
	 * 
	 */
	@Test
	public void testCleanupMerge()
	{
		final List<EnumCleanUpMerge> l = EnumCleanUpMerge.getEnumList();
		for (int i = 0; i < l.size(); i++)
		{
			final JDFDoc doc = new JDFDoc("JDF");
			final JDFNode node = doc.getJDFRoot();
			node.setType(EnumType.ProcessGroup);
			final JDFNode n2 = node.addJDFNode(EnumType.AdhesiveBinding);
			final JDFResource r = n2.addResource(ElementName.ADHESIVEBINDINGPARAMS, null, EnumUsage.Input, null, node, null, null);
			final JDFSpawn sp = new JDFSpawn(n2);
			final JDFNode spn = sp.spawn();
			final JDFSpawned auditSpawn = (JDFSpawned) node.getAuditPool().getAudit(0, EnumAuditType.Spawned, null, null);
			assertNotNull(auditSpawn);
			assertTrue(auditSpawn.getrRefsROCopied().contains(r.getID()));
			final EnumCleanUpMerge cm = l.get(i);
			new JDFMerge(node).mergeJDF(spn, null, cm, JDFResource.EnumAmountMerge.None);
			final JDFSpawned auditSpawn2 = (JDFSpawned) node.getAuditPool().getAudit(0, EnumAuditType.Spawned, null, null);
			final JDFMerged mergeSpawn2 = (JDFMerged) node.getAuditPool().getAudit(0, EnumAuditType.Merged, null, null);
			if (cm.equals(EnumCleanUpMerge.None))
			{
				assertNotNull(auditSpawn2);
				assertTrue(auditSpawn2.getrRefsROCopied().contains(r.getID()));
				assertEquals(auditSpawn, auditSpawn2);
				assertNotNull(mergeSpawn2);
				assertEquals(auditSpawn2.getrRefsRWCopied(), mergeSpawn2.getrRefsOverwritten());
			}
			else if (cm.equals(EnumCleanUpMerge.RemoveRRefs))
			{
				assertNotNull(auditSpawn2);
				assertTrue(auditSpawn2.getrRefsROCopied().isEmpty());
				assertEquals(auditSpawn, auditSpawn2);
				assertNotNull(mergeSpawn2);
				assertEquals(auditSpawn2.getrRefsRWCopied(), mergeSpawn2.getrRefsOverwritten());
			}
			else if (cm.equals(EnumCleanUpMerge.RemoveAll))
			{
				assertNull(auditSpawn2);
				assertNull(mergeSpawn2);
			}
		}
	}

	// project folder must look to test.jdf

	/**
	 * 
	 */
	@Test
	public void testMergeJDF2()
	{
		final JDFParser p = new JDFParser();
		final JDFDoc mydoc = p.parseFile(sm_dirTestData + "testMergeJDF2.jdf");
		final JDFNode root = (JDFNode) mydoc.getRoot();
		final VString rwResources = new VString();

		final JDFAttributeMap myMap = new JDFAttributeMap();

		myMap.put("Type", "Imposition");
		myMap.put("Status", "Waiting");

		final VElement elemVec = root.getChildrenByTagName("JDF", JDFConstants.EMPTYSTRING, myMap, false, true, 0);
		JDFNode spawnnode = (JDFNode) elemVec.elementAt(0);

		rwResources.add("Output");
		final JDFSpawn spawn = new JDFSpawn(spawnnode);

		spawnnode = spawn.spawn(sm_dirTestData + "testMergeJDF2.jdf", sm_dirTestDataTemp + "myTest_spawned.jdf", rwResources, null, false, false, false, false);

		assertTrue(mydoc.write2File(sm_dirTestDataTemp + "testMergeJDF2_spawned.jdf", 0, true));
		new JDFMerge(root).mergeJDF(spawnnode, JDFConstants.EMPTYSTRING, JDFNode.EnumCleanUpMerge.None, JDFResource.EnumAmountMerge.None);

		final JDFResource myres = (JDFResource) root.getTarget("Res_Impos_Out_Run_1_3011", AttributeName.ID);
		assertTrue("Merged Resource contains SpawnID", myres.getAttribute("SpawnIDs", null, JDFConstants.EMPTYSTRING).equals(JDFConstants.EMPTYSTRING));

		assertTrue(mydoc.write2File(sm_dirTestDataTemp + "testMergeJDF2_merged.jdf", 0, true));
	}

	/**
	 * 
	 */
	@Test
	public void testMergeJDF3()
	{
		// job.jdf subjdf.jdf -o merged.jdf
		final String m_xmlFile1 = "km111.jdf";
		final String m_xmlFile2 = "Link76645060_000155km111Qua0.NSp76664048_000633_28_out.jdf";
		final String m_outFile = "km111_merged.xml";
		JDFDoc m_jdfDoc;
		JDFDoc m_jdfDoc2;

		final JDFParser p = new JDFParser();
		m_jdfDoc = p.parseFile(m_xmlFile1);

		final JDFParser p2 = new JDFParser();
		m_jdfDoc2 = p2.parseFile(m_xmlFile2);

		if (m_jdfDoc2 == null)
		{
			return; // soothe findbugs ;)
		}

		final JDFNode root = (JDFNode) m_jdfDoc.getRoot();
		final JDFNode root2 = (JDFNode) m_jdfDoc2.getRoot();
		if (root == null)
		{
			return; // soothe findbugs ;)
		}

		new JDFMerge(root).mergeJDF(root2, m_xmlFile2, JDFNode.EnumCleanUpMerge.None, JDFResource.EnumAmountMerge.None);

		m_jdfDoc.write2File(sm_dirTestDataTemp + m_outFile, 2, true);
	}

	/**
	 * 
	 * @see org.cip4.jdflib.JDFTestCaseBase#setUp()
	 */
	@Override
	@Before
	public void setUp() throws Exception
	{
		super.setUp();
		KElement.setLongID(false);
	}

}