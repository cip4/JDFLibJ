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
/**
 * 
 */
package org.cip4.jdflib.util;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Set;

import org.cip4.jdflib.auto.JDFAutoNotification.EnumClass;
import org.cip4.jdflib.core.AttributeName;
import org.cip4.jdflib.core.ElementName;
import org.cip4.jdflib.core.JDFAudit;
import org.cip4.jdflib.core.JDFConstants;
import org.cip4.jdflib.core.JDFElement;
import org.cip4.jdflib.core.JDFException;
import org.cip4.jdflib.core.JDFPartAmount;
import org.cip4.jdflib.core.JDFPartStatus;
import org.cip4.jdflib.core.JDFResourceLink;
import org.cip4.jdflib.core.KElement;
import org.cip4.jdflib.core.VElement;
import org.cip4.jdflib.core.VString;
import org.cip4.jdflib.core.JDFAudit.EnumAuditType;
import org.cip4.jdflib.core.JDFElement.EnumVersion;
import org.cip4.jdflib.datatypes.JDFAttributeMap;
import org.cip4.jdflib.datatypes.VJDFAttributeMap;
import org.cip4.jdflib.node.JDFNode;
import org.cip4.jdflib.node.JDFSpawned;
import org.cip4.jdflib.node.JDFNode.EnumCleanUpMerge;
import org.cip4.jdflib.pool.JDFAmountPool;
import org.cip4.jdflib.pool.JDFAncestorPool;
import org.cip4.jdflib.pool.JDFAuditPool;
import org.cip4.jdflib.pool.JDFResourceLinkPool;
import org.cip4.jdflib.pool.JDFResourcePool;
import org.cip4.jdflib.pool.JDFStatusPool;
import org.cip4.jdflib.resource.JDFMerged;
import org.cip4.jdflib.resource.JDFNotification;
import org.cip4.jdflib.resource.JDFProcessRun;
import org.cip4.jdflib.resource.JDFResource;
import org.cip4.jdflib.resource.JDFResource.EnumAmountMerge;
import org.cip4.jdflib.resource.JDFResource.EnumPartUsage;
import org.cip4.jdflib.resource.JDFResource.EnumSpawnStatus;

/**
 * @author Rainer Prosi This class is used when merging a JDF node it summarizes all merging routines the had been part
 *         of JDF Node
 */
public class JDFMerge
{

	private JDFSpawned spawnAudit = null;
	private final JDFNode m_ParentNode;
	private JDFNode subJDFNode;
	private Set<String> vsRO;
	private Set<String> vsRW;
	private String spawnID = null;
	private final VString previousMergeIDs = new VString(); // list of merges in
	// the ancestors
	private boolean bSnafu = true;
	private JDFNode overWriteNode;
	private HashMap<String, JDFSpawned> newSpawnMap = null;

	/**
	 * set this to true if you want to update the stati of the relevant parent nodes based on the new Stati of the
	 * merged node
	 */
	public boolean bUpdateStati = false;

	/**
	 * set this to true if you want to update the ProcessRun(s) timestamps for this merge
	 */
	public boolean bAddMergeToProcessRun = false;

	/**
	 * 
	 * @param parentNode the parent node to merge into. MAY be the actual node to be replace or any Parent thereof
	 */
	public JDFMerge(JDFNode parentNode)
	{
		m_ParentNode = parentNode;
	}

	/**
	 * merge a previously spawned JDF into a node that is a child of, or this root
	 * <p>
	 * default: mergeJDF(subJDFNode, null, JDFNode.EnumCleanUpMerge.None, JDFResource.EnumAmountMerge.None)
	 * 
	 * @param subJDFNode the previously spawned jdf node
	 * @param urlMerge the url of the ???
	 * @param cleanPolicy policy how to clean up the spawn and merge audits after merging
	 * @param amountPolicy policy how to clean up the Resource amounts after merging
	 * @return JDFNode - the merged node in the new document<br>
	 *         note that the return value used to be boolean. The boolean value is now replaced by exceptions. This
	 *         corresponds to <code>true</code> always.
	 * 
	 * @throws JDFException if subJDFNode has already been merged
	 * @throws JDFException if subJDFNode was not spawned from this
	 * @throws JDFException if subJDFNode has no AncestorPool
	 * 
	 *             default: mergeJDF(subJDFNode, null, JDFNode.EnumCleanUpMerge.None, JDFResource.EnumAmountMerge.None)
	 */

	public JDFNode mergeJDF(JDFNode _toMerge, String urlMerge, EnumCleanUpMerge cleanPolicy, JDFResource.EnumAmountMerge amountPolicy)
	{
		subJDFNode = _toMerge;
		if (subJDFNode == null || !subJDFNode.hasParent(m_ParentNode))
		{
			throw new JDFException("JDFNode.MergeJDF no matching parent found");
		}

		final String idm = subJDFNode.getID();
		overWriteNode = (JDFNode) m_ParentNode.getTarget(idm, AttributeName.ID);

		if (overWriteNode == null)
		{
			throw new JDFException("JDFNode.MergeJDF no Node with ID: " + idm);
		}

		analyzeAuditPool(idm);
		// get parts from audit
		final VJDFAttributeMap parts = spawnAudit.getPartMapVector();

		// merge copied readOnly resources
		vsRO = spawnAudit.getrRefsROCopied().getSet();
		vsRW = spawnAudit.getrRefsRWCopied().getSet();

		String preSpawn = mergeCheckPrespawn();

		mergeLocalLinks(parts);

		cleanROResources();
		mergeRWResources(amountPolicy);

		mergeLocalNodes(amountPolicy, parts);
		JDFMerged mergeAudit = mergeMainPools(parts, preSpawn, urlMerge);
		// an empty spawnID should never happen here, but check just in case
		// since an empty spawnID in CleanUpMerge removes all Spawned audits
		if (spawnID != null)
		{
			JDFNode overWriteParent = mergeAudit.getParentJDF(); // since all
			// links get
			// screwed
			// up, let's
			// relink
			// here
			cleanUpMerge(overWriteParent, cleanPolicy, false);
		}

		// now burn it in!
		overWriteNode = (JDFNode) overWriteNode.replaceElement(subJDFNode);
		overWriteNode.eraseEmptyNodes(true);
		// overWriteNode.synchParentAmounts(); // add all actualamounts into the
		// merged parent gray box
		// update all stati (generally in NodeInfo) of the merged node and of
		// the parents of the merged node
		if (bUpdateStati)
			overWriteNode.updatePartStatus(parts, true, true);
		return overWriteNode;
	}

	private void analyzeAuditPool(final String idm)
	{
		// tbd multiple ancestor handling
		final JDFAncestorPool ancestorPool = subJDFNode.getAncestorPool();
		if (ancestorPool == null)
		{
			throw new JDFException("JDFNode.MergeJDF no Ancestor Pool in Node: " + idm);
		}
		final int numAncestors = ancestorPool.numChildElements(ElementName.ANCESTOR, null);

		if (numAncestors <= 0)
		{
			throw new JDFException("JDFNode.MergeJDF no Ancestors in AncestorPool found. Node: " + idm);
		}

		int iFound = 0;
		for (int whereToLook = 1; whereToLook <= numAncestors; whereToLook++)
		{
			// the last ancestor has the id!
			final String idParent = ancestorPool.getAncestor(numAncestors - whereToLook).getNodeID();
			final KElement k = m_ParentNode.getTarget(idParent, AttributeName.ID);
			if (k == null)
			{
				break;
			}

			JDFNode op = (JDFNode) k;
			final JDFAuditPool ap = op.getCreateAuditPool();

			// find all ids of previous merge operations for reverse merge
			// cleanup
			final VElement vMergeAudit = ap.getAudits(EnumAuditType.Merged, null, null);
			for (int nMerged = 0; nMerged < vMergeAudit.size(); nMerged++)
			{
				final JDFMerged merged = (JDFMerged) vMergeAudit.elementAt(nMerged);
				previousMergeIDs.appendUnique(merged.getMergeID());
			}

			if (iFound != 0) // we've already found a spawned Audit, just
			// looping for previous merges
			{
				continue;
			}

			// get appropriate spawned element
			final VElement vSpawnAudit = ap.getChildrenByTagName(ElementName.SPAWNED, null, new JDFAttributeMap(AttributeName.JREF, idm), true, true, 0);
			spawnID = subJDFNode.getSpawnID(false);

			for (int isp = vSpawnAudit.size() - 1; isp >= 0; isp--)
			{ // loop backwards because the latest is assumed correct
				JDFSpawned testSpawn = (JDFSpawned) vSpawnAudit.elementAt(isp);
				if (testSpawn.getNewSpawnID().equals(spawnID))
				{
					// tbd check for matching merged...
					spawnAudit = testSpawn;
					final JDFMerged matchingMerged = (JDFMerged) ap.getChildWithAttribute(ElementName.MERGED, AttributeName.MERGEID, null, spawnID, 0, true);

					if (matchingMerged != null)
					{
						throw new JDFException("JDFNode.MergeJDF Spawn Audit already merged, SpawnID: " + spawnID, JDFSpawn.exAlreadyMerged);
					}
					break;
				}
			}
			// found an audit that fits,
			if (spawnAudit != null)
			{
				iFound = whereToLook;
			}
		}

		// if the spawn Audit is not found at the first attempt, something went
		// badly wrong
		// we will insert a error audit later but continue limping along!
		bSnafu = iFound != 1;

		if (spawnAudit == null)
		{
			throw new JDFException("JDFNode.MergeJDF no matching Spawn Audit, SpawnID: " + spawnID);
		}
	}

	/**
	 * merge the audit pools
	 * 
	 * @param overWriteNode
	 * @param subJDFNode the source node of the audit pool to merge into this
	 */
	private void mergeAuditPool(JDFNode poverWriteNode, JDFNode toMerge)
	{
		// merge audit pool
		final JDFAuditPool overWriteAuditPool = poverWriteNode.getAuditPool();
		final JDFAuditPool toMergeAuditPool = toMerge.getAuditPool();

		// the node that is overwritten has an audit pool that must be merged
		if (overWriteAuditPool != null)
		{
			// the overwriting node node is empty, just copy the previous pool
			if (toMergeAuditPool == null)
			{
				toMerge.copyElement(overWriteAuditPool, null);
			}
			else
			{
				// must merge the old node into the overwriting node
				overWriteAuditPool.appendUnique(toMergeAuditPool);
				toMergeAuditPool.replaceElement(overWriteAuditPool);
			}
		}
	}

	// /////////////////////////////////////////////////////////////////

	private String mergeCheckPrespawn()
	{
		String preSpawn = spawnAudit.getSpawnID();
		// check all recursive previous spawns
		while (preSpawn != null && !preSpawn.equals(JDFConstants.EMPTYSTRING))
		{
			final JDFMerged preMerge = (JDFMerged) m_ParentNode.getTarget(preSpawn, AttributeName.MERGEID);
			if (preMerge != null)
			{
				final JDFSpawned preSpawnAudit = (JDFSpawned) m_ParentNode.getTarget(preSpawn, AttributeName.NEWSPAWNID);
				vsRO.addAll(preSpawnAudit.getrRefsROCopied());
				vsRW.addAll(preSpawnAudit.getrRefsRWCopied());
				preSpawn = preSpawnAudit.getSpawnID();
			}
			else
			{
				subJDFNode.setSpawnID(preSpawn);
				break;
			}
		}
		return preSpawn;
	}

	// ////////////////////////////////////////////////////////////////////

	private void mergeComments(JDFNode poverWriteNode, JDFNode toMerge)
	{
		VElement v = poverWriteNode.getChildElementVector(ElementName.COMMENT, null, null, false, 0, false);
		VElement vToMerge = toMerge.getChildElementVector(ElementName.COMMENT, null, null, false, 0, false);
		final int siz = vToMerge.size(); // size prior to appending
		vToMerge.appendUnique(v);
		for (int i = siz; i < vToMerge.size(); i++)
		{
			toMerge.moveElement(vToMerge.elementAt(i), null);
		}
	}

	private void mergeLocalLinks(final VJDFAttributeMap parts)
	{
		int numParts = parts == null ? 0 : parts.size();
		final VElement vn = overWriteNode.getvJDFNode(null, null, false);
		final int size = vn.size();
		// merge local (internal) partitioned resource links
		for (int nod = 0; nod < size; nod++)
		{
			final JDFNode overwriteLocalNode = (JDFNode) vn.elementAt(nod);
			final JDFNode toMergeLocalNode = subJDFNode.getChildJDFNode(overwriteLocalNode.getID(), false);
			mergeResourceLinkPool(overwriteLocalNode, toMergeLocalNode, parts);

			final EnumVersion version = toMergeLocalNode.getVersion(true);
			if ((version != null) && (version.getValue() >= EnumVersion.Version_1_3.getValue()))
			{
				final JDFNode.EnumNodeStatus stat = toMergeLocalNode.getStatus();
				if (stat != null && !stat.equals(JDFElement.EnumNodeStatus.Part)
						&& !stat.equals(JDFElement.EnumNodeStatus.Pool) && numParts > 0)
				{
					toMergeLocalNode.setPartStatus(parts, stat, null);
				}
			}
		}
	}

	// //////////////////////////////////////////////////////////////////////////
	// /////////

	private void mergeLocalNodes(JDFResource.EnumAmountMerge amountPolicy, final VJDFAttributeMap parts)
	{
		// merge local (internal) partitioned resources
		final VElement vn = overWriteNode.getvJDFNode(null, null, false);
		for (int nod = 0; nod < vn.size(); nod++)
		{
			final JDFNode overwriteLocalNode = (JDFNode) vn.elementAt(nod);

			final JDFNode toMergeLocalNode = (JDFNode) subJDFNode.getTarget(overwriteLocalNode.getID(), AttributeName.ID);
			final JDFResourcePool poolOverWrite = overwriteLocalNode.getResourcePool();
			final JDFResourcePool poolToMerge = toMergeLocalNode.getResourcePool();

			if (poolOverWrite != null)
			{
				final VElement resOverWrite = poolOverWrite.getPoolChildren(null, null, null);

				final int size = resOverWrite.size();
				for (int i = 0; i < size; i++)
				{
					final JDFResource res1 = (JDFResource) resOverWrite.elementAt(i);
					mergeLocalResource(amountPolicy, poolToMerge, res1);
				}
			}

			// retain all other elements of the original (non spawned) JDF Node
			// if the spawn is partitioned¬
			final VElement localChildren = overwriteLocalNode.getChildElementVector(null, null, null, true, 0, false);

			final int siz = localChildren.size();
			for (int i = 0; i < siz; i++)
			{
				final KElement e = localChildren.elementAt(i);
				// skip all pools
				final String nodeName = e.getLocalName();
				if (nodeName.endsWith("Pool"))
				{
					if (nodeName.equals(ElementName.RESOURCELINKPOOL))
					{
						continue;
					}
					if (nodeName.equals(ElementName.RESOURCEPOOL))
					{
						continue;
					}
					if (nodeName.equals(ElementName.AUDITPOOL))
					{
						mergeAuditPool(overwriteLocalNode, toMergeLocalNode);
						continue;
					}
					if (nodeName.equals(ElementName.STATUSPOOL))
					{
						mergeStatusPool(overwriteLocalNode, toMergeLocalNode, parts);
						continue;
					}
					if (nodeName.equals(ElementName.ANCESTORPOOL))
					{
						continue;
					}
				}

				// 131204 RP also skip all sub-JDF nodes!!!
				if (nodeName.equals(ElementName.JDF))
				{
					continue;
				}
				// 050708 RP special handling for comments
				if (nodeName == ElementName.COMMENT)
				{
					mergeComments(overwriteLocalNode, toMergeLocalNode);
					continue;
				}

				toMergeLocalNode.removeChildren(nodeName, null, null);
				toMergeLocalNode.moveElement(e, null);

				// repeat in case of multiple identical elements (e.g. comments)
				for (int j = i + 1; j < siz; j++)
				{
					final JDFElement localChild = (JDFElement) localChildren.elementAt(j);
					if (localChild != null)
					{
						if (localChild.getNodeName().equals(nodeName))
						{
							toMergeLocalNode.moveElement(localChild, null);
							localChildren.set(j, null);
						}
					}
				}
			}
		}
	}

	/**
	 * Merges partitioned resources into this resource uses PartIDKey to identify the correct resources
	 * 
	 * @param resToMerge the resource leaf to merge into this
	 * @param spawnID the spawnID of the spawning that will now be merged
	 * @param amountPolicy how to clean up the Resource amounts after merging
	 * @param bLocalResource must be true for the local resources in a spawned node and its subnodes, which default to
	 *            RW
	 * 
	 * @throws JDFException if here is an attempt to merge incompatible resources
	 * @throws JDFException if here is an attempt to merge incompatible partitions
	 * 
	 * @default mergePartition (resToMerge, spawnID, EnumAmountMerge.None, false);
	 */
	/*
	 * mergePartition will stay public, as long as deprecated JDFResource.mergePartition is not deleted
	 */
	static public JDFResource mergePartition(JDFResource targetRes, JDFResource resToMerge, String spawnID, EnumAmountMerge amountPolicy, boolean bLocalResource)
	{
		if (!targetRes.getID().equals(resToMerge.getID()))
		{
			throw new JDFException("JDFResource.mergePartition  merging incompatible resources ID=" + targetRes.getID()
					+ " IDMerge=" + resToMerge.getID());
		}

		// / TBD RP SpawnStatus Handling!!!!
		final JDFResource toMerge = resToMerge;
		JDFResource root = targetRes.getResourceRoot();
		final VString partIDKeys = root.getPartIDKeys();
		final VString mergeIDKeys = toMerge.getPartIDKeys();
		final VElement allChildren = resToMerge.getNodesWithSpawnID(spawnID);

		// No spawntargets take all
		if (allChildren.isEmpty())
		{
			allChildren.addElement(toMerge);
		}

		boolean bTargetGone = false;
		for (int i = 0; i < allChildren.size(); i++)
		{
			final JDFResource src = (JDFResource) allChildren.elementAt(i);
			if (src.getIdentical() != null)
				continue; // no need to merge identical elements
			final JDFAttributeMap srcMap = src.getPartMap(mergeIDKeys);
			JDFResource trg = targetRes.getPartition(srcMap, EnumPartUsage.Implicit);

			if (trg == null)
			{
				trg = targetRes;
			}
			JDFAttributeMap trgMap = trg.getPartMap();

			// RP 220605 - not puristic, but pragmatic
			// found only a root or high level partition for an rw resource
			// partition
			// try to create the new partition and pray that it will not be
			// subsequently completely overwritten
			// this is still better than throwing an exception or silently
			// ignoring the rw resource
			if ((src.getLocked() == false) && (trgMap.getKeys().size() < srcMap.getKeys().size()))
			{
				trg = targetRes.getCreatePartition(srcMap, partIDKeys);
				// fool the algorithm to think that the new partition is rw
				// (which it probably was)
				trg.setSpawnStatus(EnumSpawnStatus.SpawnedRW);
				trgMap = trg.getPartMap(); // 061114 fix!
			}

			if (bLocalResource || trg.getSpawnStatus() == JDFResource.EnumSpawnStatus.SpawnedRW)
			{

				if (srcMap.equals(trgMap))
				{
					if (trgMap.isEmpty())
					{ // we actually replaced the root nothing left to do
						bTargetGone = true;
						trg = (JDFResource) targetRes.replaceElement(src);
						root = trg.getResourceRoot();
					}
					else
					{
						KElement copyElement = targetRes.copyElement(src, null);
						trg = (JDFResource) trg.replaceElement(copyElement);
					}
				}
				else if (srcMap.subMap(trgMap))
				{
					if (trgMap.size() + 1 != srcMap.size())
						throw new JDFException("JDFResource.mergePartition attempting to merge incompatible sub-partitions!");

					trg.copyElement(src, null);
				}
				else
				{ // oops
					throw new JDFException("JDFResource.mergePartition attempting to merge incompatible partitions");
				}
			}
			// update the partitions amounts
			if ((amountPolicy != EnumAmountMerge.None) && targetRes.isPhysical())
			{
				JDFResource trgKeep = trg;
				trg = root.getPartition(srcMap, EnumPartUsage.Implicit); // must
				// repeat
				// since
				// replaceelement
				// does
				// not
				// modify
				// itself
				if (trg == null)
				{
					trg = trgKeep;
				}
				VElement vr = trg.getLeaves(true);
				for (int l = 0; l < vr.size(); l++)
				{
					double amo = 0;
					JDFResource r = (JDFResource) vr.elementAt(l);
					if (amountPolicy != EnumAmountMerge.LinkOnly)
					{
						amo = r.getAmount();
					}
					r.updateAmounts(amo);
				}
			}
		}

		// some crap is left - remove it
		if (!bTargetGone)
		{
			toMerge.deleteNode();
		}

		partIDKeys.appendUnique(mergeIDKeys);

		if (partIDKeys.isEmpty())
		{
			root.removeAttribute(AttributeName.PARTIDKEYS);
		}
		else
		{
			root.setPartIDKeys(partIDKeys);
		}
		return root;
	}

	private void mergeLocalResource(JDFResource.EnumAmountMerge amountPolicy, final JDFResourcePool poolToMerge, JDFResource res1)
	{
		final String resID = res1.getID();
		final JDFResource res2 = poolToMerge.getResourceByID(resID);

		if (res2 != null)
		{
			mergeSpawnIDs(res2, res1, false);
			res1 = mergePartition(res1, res2, spawnID, amountPolicy, true); // esp
			// .
			// deletes
			// res2
			// from
			// subJDFNode
			// node
		}
		// copy resource from orig to spawned node
		poolToMerge.copyElement(res1, null);
		res1 = poolToMerge.getResourceByID(resID);
		final VElement resLeafsSpawned = res1.getNodesWithSpawnID(spawnID);
		for (int leaf = 0; leaf < resLeafsSpawned.size(); leaf++)
		{
			final JDFResource leafRes = (JDFResource) resLeafsSpawned.elementAt(leaf);
			leafRes.removeFromSpawnIDs(spawnID);
			final VString spawnIDs = leafRes.getSpawnIDs(false);
			if (spawnIDs != null)
				spawnIDs.removeAll(previousMergeIDs);
			leafRes.setSpawnIDs(spawnIDs);
			calcSpawnStatus(leafRes, true);
		}
	}

	private void calcSpawnStatus(final JDFResource leafRes, boolean bLocal)
	{
		if (leafRes == null)
			return;
		prepareNewSpawnMap();
		final VString spawnIDs = leafRes.getSpawnIDs(false);
		final String resID = leafRes.getID();
		if (spawnIDs == null || spawnIDs.isEmpty())
		{
			removeSpawnAttributes(leafRes);
			return;
		}
		else if (bLocal || vsRW.contains(resID))
		{
			boolean bWrite = bLocal;

			for (int i = 0; i < spawnIDs.size(); i++) // check for multiple rw
			// spawns
			{

				final String resSpawnID = spawnIDs.stringAt(i);
				// JDFSpawned spawnedAudit=(JDFSpawned)
				// (m_ParentNode.getChildByTagName(ElementName.SPAWNED, null, 0,
				// new JDFAttributeMap(AttributeName.NEWSPAWNID,resSpawnID),
				// false, true));
				JDFSpawned spawnedAudit = newSpawnMap.get(resSpawnID);
				if (spawnedAudit != null)
				{
					VString rw = spawnedAudit.getrRefsRWCopied();
					if (rw != null && rw.contains(resID))
					{
						bWrite = true;
					}
				}
				else
				// clean up spurious spawnids of spawns that were initiated off
				// line
				{
					String mainSpawnID = leafRes.getJDFRoot().getSpawnID(true);
					if (KElement.isWildCard(mainSpawnID)) // only remove unknown
					// spawnids in a
					// real main ticket
					// Spawned spawnids
					// may be specified
					// in a spawn
					// ancestor
					{
						leafRes.removeFromAttribute(AttributeName.SPAWNIDS, resSpawnID, null, null, -1);
						final VString spawnIDsNew = leafRes.getSpawnIDs(false);
						if (spawnIDsNew == null || spawnIDsNew.isEmpty())
						{
							removeSpawnAttributes(leafRes);
							return;
						}
					}
				}
			}
			if (bWrite)
			{
				leafRes.setSpawnStatus(EnumSpawnStatus.SpawnedRW);
				leafRes.setLocked(true);
			}
			else
			{
				leafRes.setSpawnStatus(EnumSpawnStatus.SpawnedRO);
				leafRes.setLocked(false);
			}
		}
		else
		// this was ro
		{
			// nop
		}
	}

	/**
	 * @param leafRes
	 */
	private void removeSpawnAttributes(final JDFResource leafRes)
	{
		leafRes.removeAttribute(AttributeName.SPAWNIDS);
		leafRes.removeAttribute(AttributeName.SPAWNSTATUS);
		leafRes.removeAttribute(AttributeName.LOCKED);
	}

	/**
     * 
     */
	private void prepareNewSpawnMap()
	{
		if (newSpawnMap != null)
			return;
		newSpawnMap = new HashMap<String, JDFSpawned>();
		// VElement v=m_ParentNode.getChildrenByTagName(ElementName.SPAWNED,
		// null, null, false, true, 0, false);
		VElement v = m_ParentNode.getvJDFNode(null, null, false);
		for (int i = 0; i < v.size(); i++)
		{
			JDFNode n = (JDFNode) v.get(i);
			JDFAuditPool ap = n.getAuditPool();
			VElement v2 = ap == null ? null : ap.getAudits(EnumAuditType.Spawned, null, null);
			// JDFSpawned s=(JDFSpawned) v.get(i);
			int siz = v2 == null ? 0 : v2.size();
			for (int j = 0; j < siz; j++)
			{
				JDFSpawned s = (JDFSpawned) v2.get(j);
				String nsID = s.getNewSpawnID();
				if (!KElement.isWildCard(nsID))
					newSpawnMap.put(nsID, s);
			}
		}

	}

	// ///////////////////////////////////////////////////////////////////

	private JDFMerged mergeMainPools(final VJDFAttributeMap parts, String preSpawn, String urlMerge)
	{
		// add the merged audit - maintain sychronicity of spawned and merged
		JDFNode overWriteParent = null;
		JDFAuditPool ap = subJDFNode.getAuditPool();
		JDFSpawned sa = null;

		if (ap != null)
		{
			sa = (JDFSpawned) ap.getChildWithAttribute(ElementName.SPAWNED, AttributeName.NEWSPAWNID, null, spawnID, 0, true);
			overWriteParent = overWriteNode;
		}

		if (sa == null)
		{
			overWriteParent = overWriteNode.getParentJDF();
			if (overWriteParent == null)
			{
				throw new JDFException("mergeMainPools - corrupt audit structure");
			}

			ap = overWriteParent.getAuditPool();
			if (ap != null)
				sa = (JDFSpawned) ap.getChildWithAttribute(ElementName.SPAWNED, AttributeName.NEWSPAWNID, null, spawnID, 0, true);
		}

		if (sa == null)
		{
			// ????
			throw new JDFException("mergeMainPools - corrupt audit structure; no Spawn Audit found");
		}

		// JDFNode overWriteParent=ap.getParentJDF();
		VString vs = new VString();
		Iterator<String> it = vsRW.iterator();
		while (it.hasNext())
			vs.add(it.next());

		final JDFMerged mergeAudit = ap.addMerged(subJDFNode, vs, null, parts);

		if (urlMerge != null && !urlMerge.equals(JDFConstants.EMPTYSTRING))
		{
			String url = urlMerge;
			// 300802 RP added check for preexisting file prefix
			if (url.indexOf("://") == -1)
			{
				url = "File://" + url;
			}
			mergeAudit.setURL(url);
		}
		mergeAudit.setMergeID(spawnID);

		// if something went wrong, also add a notification
		if (bSnafu)
		{
			final JDFNotification notification = ap.addNotification(EnumClass.Error, "JDFNode.MergeJDF ", parts);
			notification.setType("Error");
			notification.appendComment().appendText("The Ancestor list was incorrectly ordered for merging in the spawned JDF");
		}

		// cleanup
		subJDFNode.removeChild(ElementName.ANCESTORPOOL, null, 0);
		if (parts != null && parts.size() >= 1)
		{
			mergeStatusPool(overWriteNode, subJDFNode, parts);
			// handle ancestor pools only in partitioned spawns
			final JDFAncestorPool ancPool = overWriteParent.getAncestorPool();
			if (ancPool != null)
			{
				subJDFNode.copyElement(ancPool, null);
			}
		}

		final String jid = overWriteParent.getJobID(true);
		if (subJDFNode.getAttribute(AttributeName.JOBID, null, JDFConstants.EMPTYSTRING).equals(jid))
		{
			subJDFNode.removeAttribute(AttributeName.JOBID, null);
		}
		if (preSpawn == null || preSpawn.equals(JDFConstants.EMPTYSTRING))
		{
			subJDFNode.removeAttribute(AttributeName.SPAWNID, null);
			mergeAudit.removeAttribute(AttributeName.SPAWNID, null);
		}
		else
		{
			subJDFNode.setSpawnID(preSpawn);
		}
		return mergeAudit;
	}

	/**
	 * merge the resource link pools
	 * 
	 * @param oMerge the source node of the status pool to merge into this
	 * @param parts the partitions to merge
	 */
	private void mergeResourceLinkPool(JDFNode mainNode, JDFNode toMerge, VJDFAttributeMap parts)
	{
		final JDFResourceLinkPool resourceLinkPool = toMerge.getResourceLinkPool();
		expandLinkedResources(resourceLinkPool);

		final JDFResourceLinkPool toMergeRLP = resourceLinkPool;
		if (toMergeRLP == null)
			return; // nothing to do

		final JDFResourceLinkPool overWriteRLP = mainNode.getCreateResourceLinkPool();
		if (parts != null && !parts.isEmpty())
		{
			final VElement overWriteLinks = overWriteRLP.getPoolChildren(null, null, null);
			final VElement toMergeLinks = toMergeRLP.getPoolChildren(null, null, null);

			if (toMergeLinks != null && overWriteLinks != null)
			{
				for (int rl = 0; rl < toMergeLinks.size(); rl++)
				{
					JDFResourceLink overWriteLink = null;
					final JDFResourceLink toMergeLink = (JDFResourceLink) toMergeLinks.elementAt(rl);
					final String rRef = toMergeLink.getAttribute(AttributeName.RREF);

					for (int k = 0; k < overWriteLinks.size(); k++)
					{
						if (((JDFResourceLink) overWriteLinks.elementAt(k)).getAttribute(AttributeName.RREF).equals(rRef))
						{
							overWriteLink = (JDFResourceLink) overWriteLinks.elementAt(k);
							overWriteLinks.remove(overWriteLinks.elementAt(k));
							break;
						}
					}

					if (overWriteLink != null)
					{
						if (toMergeLink.hasChildElement(ElementName.PART, null))
						{
							fixPartAmountAttributes(overWriteLink, toMergeLink);
						}
						else
						{
							// blast the spawned link into the overWritePool
							// completely
							overWriteLink.replaceElement(toMergeLink);
						}
					}
				}
			}

			toMergeRLP.deleteNode();
			toMerge.copyElement(overWriteRLP, null);
		}
		else
		// copy the rlp from sub to main so that amount recalc in mergerw finds
		// all required links in main
		{
			overWriteRLP.deleteNode();
			mainNode.copyElement(toMergeRLP, null);
		}
	}

	/**
	 * @param parts
	 * @param mainLink
	 * @param subLink
	 * @param jdfAmountPool
	 */
	private void fixPartAmountAttributes(JDFResourceLink mainLink, final JDFResourceLink subLink)
	{
		final JDFAmountPool subAmountPool = subLink.getAmountPool();
		VJDFAttributeMap subLinkParts = subLink.getPartMapVector();
		final int partSize = subLinkParts.size();
		for (int i = 0; i < partSize; i++)
		{
			// final boolean hasAP =
			// mainLink.hasChildElement(ElementName.AMOUNTPOOL, null);
			VElement vSubPartAmounts = null;
			if (subAmountPool != null)
				vSubPartAmounts = subAmountPool.getMatchingPartAmountVector(subLinkParts.elementAt(i));

			if (vSubPartAmounts == null)
			{
				JDFAttributeMap subLinkMap = subLink.getAttributeMap();
				// remove generic link stuff
				subLinkMap.remove(AttributeName.COMBINEDPROCESSINDEX);
				subLinkMap.remove(AttributeName.COMBINEDPROCESSTYPE);
				// tbd opa.RemoveAttribute(atr_PipePartIDKeys);
				subLinkMap.remove(AttributeName.PIPEPROTOCOL);
				subLinkMap.remove(AttributeName.PROCESSUSAGE);
				subLinkMap.remove(AttributeName.RREF);
				subLinkMap.remove(AttributeName.RSUBREF);
				subLinkMap.remove(AttributeName.USAGE);

				removeIdenticalFromPartAmountMap(mainLink, subLinkMap);

				// create a new partamount for each spawned part that contains
				// the contents of the outer link
				if (!subLinkMap.isEmpty() && !subLinkMap.equals(subLinkParts.elementAt(i)))
				{
					JDFPartAmount mainPartAmount = mainLink.getCreateAmountPool().getCreatePartAmount(subLinkParts.elementAt(i));
					mainPartAmount.setAttributes(subLinkMap);
					mainLink.removeAttributes(subLinkMap.getKeys());
				}
			}
			else
			// we found matching partamounts, clean them up and move them in
			{
				// loop over all fitting part amounts and blast them in
				for (int j = 0; j < vSubPartAmounts.size(); j++)
				{
					JDFPartAmount subPartAmount = (JDFPartAmount) vSubPartAmounts.elementAt(j);
					JDFAttributeMap subAmountMap = subPartAmount.getAttributeMap();
					removeIdenticalFromPartAmountMap(mainLink, subAmountMap);

					if (!subAmountMap.isEmpty())
					{
						JDFAttributeMap subPartMap = subPartAmount.getPartMap();
						JDFPartAmount mainPartAmount = mainLink.getCreateAmountPool().getCreatePartAmount(subPartMap);
						mainPartAmount.setAttributes(subAmountMap);
						mainLink.removeAttributes(subAmountMap.getKeys());
					}
				}
			}

			// nothing has changed --> leave as is
		}
	}

	private void removeIdenticalFromPartAmountMap(JDFResourceLink mainLink, JDFAttributeMap subLinkMap)
	{
		{
			final JDFAttributeMap mainLinkMap = mainLink.getAttributeMap();
			Iterator<String> iter = mainLinkMap.getKeyIterator();
			while (iter.hasNext())
			{
				final String key = iter.next();
				if (mainLinkMap.get(key).equals(subLinkMap.get(key)))
					subLinkMap.remove(key);
			}
		}
	}

	/**
	 * @param resourceLinkPool the resourceLinkPool that contains the links to the resources to expand
	 */
	private void expandLinkedResources(final JDFResourceLinkPool resourceLinkPool)
	{
		if (resourceLinkPool != null)
		{
			final VElement links = resourceLinkPool.getPoolChildren(null, null, null);
			final int size = (links == null) ? 0 : links.size();
			for (int i = 0; i < size; i++)
			{
				JDFResourceLink rl = (JDFResourceLink) links.elementAt(i);
				// 071214 only expand if rw
				if (vsRW.contains(rl.getrRef()))
					rl.expandTarget(false);
			}
		}
	}

	/**
	 * Merges the spawnIDs of the various partitions <br>
	 * also updates SpawnStatus, if necessary <br>
	 * this routine is needed to correctly handle nested spawning and merging
	 * 
	 * @param mainres the resource in the main jdf to merge to
	 * @param resToMerge the resource with potentially new spawnIDs
	 * @param bReadOnly if true, don't add anything since it was RO
	 * 
	 */
	private void mergeSpawnIDs(JDFResource mainRes, JDFResource resToMerge, boolean bReadOnly)
	{
		if (!mainRes.getID().equals(resToMerge.getID()))
		{
			throw new JDFException("JDFResource.mergeSpawnIDs  merging incompatible resources ID = " + mainRes.getID()
					+ " IDMerge = " + resToMerge.getID());
		}

		final VElement allLeaves = mainRes.getLeaves(true);
		final VString partIDKeys = mainRes.getPartIDKeys();
		for (int i = 0; i < allLeaves.size(); i++)
		{
			final JDFResource thisResNode = (JDFResource) allLeaves.elementAt(i);
			final JDFResource mergeResNode = resToMerge.getPartition(thisResNode.getPartMap(partIDKeys), EnumPartUsage.Explicit);

			if (mergeResNode != null)
			{
				VString vSpawnIDs = thisResNode.getSpawnIDs(false);
				int siz = vSpawnIDs == null ? 0 : vSpawnIDs.size();
				if (!bReadOnly) // only append from rw resources, not from ro
				{
					if (vSpawnIDs == null)
					{
						vSpawnIDs = mergeResNode.getSpawnIDs(false);
					}
					else
					{
						vSpawnIDs.appendUnique(mergeResNode.getSpawnIDs(false));
					}
				}
				if (vSpawnIDs != null)
					vSpawnIDs.removeStrings(previousMergeIDs, 999999);

				if (vSpawnIDs == null || vSpawnIDs.isEmpty())
				{
					removeSpawnAttributes(thisResNode);
				}
				else if (siz < vSpawnIDs.size())
				{
					thisResNode.setSpawnIDs(vSpawnIDs);
					// one of the spawnstatus elements was rw, must also be
					// valid here
					if (mergeResNode.getSpawnStatus() == EnumSpawnStatus.SpawnedRW)
					{
						thisResNode.setSpawnStatus(EnumSpawnStatus.SpawnedRW);
					}
				}
			}
		}
	}

	/**
	 * merge the RW resources of the main JDF
	 * 
	 * @param subJDFNode the source node of the status pool to merge into this
	 * @param subJDFNode the source node of the status pool to merge into this
	 * @param previousMergeIDs SpawnIDs of previously merged
	 * @param vsRW Resource IDs of non-local spawned resources
	 * @param spawnID the original spawnID
	 * @param amountPolicy policy how to clean up the Resource amounts after merging
	 */
	private void mergeRWResources(JDFResource.EnumAmountMerge amountPolicy)
	{
		// merge rw resources
		Iterator<String> it = vsRW.iterator();
		while (it.hasNext())
		{
			String s = it.next();
			JDFResource oldRes = overWriteNode.getLinkRoot(s);
			if (oldRes == null) // also check in tree below
			{
				oldRes = overWriteNode.getTargetResource(s);
				if (oldRes == null) // also check in entire tree below root
				{
					oldRes = overWriteNode.getTargetResource(s);
				}
			}
			if (oldRes == null)
			{
				continue;
			}

			final JDFResource newRes = subJDFNode.getTargetResource(s);

			// merge all potential new spawnIds from this to subJDFNode before
			// merging them
			mergeSpawnIDs(oldRes, newRes, false);
			// do both, since some leaves may be RO
			mergeSpawnIDs(newRes, oldRes, false);

			try
			{
				// merge the resource from the spawned node into the lower level
				// resourcepool
				oldRes = mergePartition(oldRes, newRes, spawnID, amountPolicy, false);
			}
			catch (Exception e)
			{
				throw new JDFException("JDFNode:mergeJDF, error in mergePartition: ID=" + oldRes.getID() + " SpawnID="
						+ spawnID);
			}

			final String oldID = oldRes.getID();
			final JDFResource myRes = (JDFResource) overWriteNode.getTarget(oldID, AttributeName.ID);
			if (myRes == null)
			{
				throw new JDFException("JDFNode.mergeJDF: Merged Resource not found! Cant remove SpawnIDs");
			}
			final VElement oldResLeafsSpawned = myRes.getNodesWithSpawnID(spawnID);
			for (int leaf = 0; leaf < oldResLeafsSpawned.size(); leaf++)
			{
				final JDFResource leafRes = (JDFResource) oldResLeafsSpawned.elementAt(leaf);
				leafRes.removeFromSpawnIDs(spawnID);
				calcSpawnStatus(leafRes, false);
			}
		}
	}

	/**
	 * merge the status pools
	 * 
	 * @param subJDFNode the source node of the status pool to merge into this
	 * @param parts the partitions to merge
	 */
	private void mergeStatusPool(JDFNode poverWriteNode, JDFNode toMerge, VJDFAttributeMap parts)
	{
		if (toMerge.hasChildElement(ElementName.STATUSPOOL, null)
				|| poverWriteNode.hasChildElement(ElementName.STATUSPOOL, null))
		{
			final JDFStatusPool overWriteStatusPool = poverWriteNode.getCreateStatusPool();
			if (!poverWriteNode.getStatus().equals(JDFElement.EnumNodeStatus.Pool))
			{
				overWriteStatusPool.setStatus(poverWriteNode.getStatus());
				poverWriteNode.setStatus(JDFElement.EnumNodeStatus.Pool);
			}

			final JDFStatusPool toMergeStatusPool = toMerge.getStatusPool();
			final int size = parts == null ? 0 : parts.size();
			if (JDFElement.EnumNodeStatus.Pool.equals(toMerge.getStatus()))
			{
				for (int i = 0; i < size; i++)
				{
					// clean up the pool to overwrite
					final VElement vpso = overWriteStatusPool.getMatchingPartStatusVector(parts.elementAt(i));
					for (int j = 0; j < vpso.size(); j++)
					{
						// remove all matching partstatus elements in case they
						// were expanded in the spawned node
						((JDFPartStatus) vpso.elementAt(j)).deleteNode();
					}

					// extract data from spawned node
					final VElement vps = toMergeStatusPool.getMatchingPartStatusVector(parts.elementAt(i));
					for (int j = 0; j < vps.size(); j++)
					{
						final JDFPartStatus ps = (JDFPartStatus) vps.elementAt(j);
						final JDFAttributeMap m = ps.getPartMap();
						overWriteStatusPool.setStatus(m, ps.getStatus(), ps.getStatusDetails());
					}

					// 100305 RP the following lines cause problems with nested
					// spawn and therefore are commented out
					// final JDFPartStatus
					// ps=overWriteStatusPool.getPartStatus(parts.elementAt(i));
					// just in case someone updated detailed partstatus elements
					// if (ps != null && ps.getStatus() ==
					// EnumNodeStatus.Spawned)
					// ps.deleteNode();
				}
				toMergeStatusPool.replaceElement(overWriteStatusPool);
			}
			else
			{
				// this part of the program will probably never be executed,
				// but...
				for (int i = 0; i < size; i++)
					overWriteStatusPool.setStatus(parts.elementAt(i), toMerge.getStatus(), null);
				if (toMergeStatusPool != null)
					toMergeStatusPool.deleteNode();
				toMerge.setStatus(JDFElement.EnumNodeStatus.Pool);
				toMerge.moveElement(overWriteStatusPool, null);
			}
		}
	}

	/**
	 * merge the RO resources of the main JDF
	 * 
	 * @param subJDFNode the source node of the status pool to merge into this
	 * @param previousMergeIDs SpawnIDs of previously merged
	 * @param vsRO Resource IDs of non-local spawned resources
	 * @param spawnID the original spawnID
	 */
	private void cleanROResources()
	{
		final Iterator<String> it = vsRO.iterator();
		while (it.hasNext())
		{
			String ro = it.next();
			final JDFResource newRes = subJDFNode.getTargetResource(ro);
			final JDFResource oldRes = (JDFResource) overWriteNode.getTarget(ro, AttributeName.ID);
			if (oldRes == null || newRes == null)
				continue; // snafu, lets just ignore the rest and limp along

			// merge all potential new spawnIds from subJDFNode to this
			mergeSpawnIDs(oldRes, newRes, true);
			final VElement oldResLeafsSpawned = oldRes.getNodesWithSpawnID(spawnID);
			for (int leaf = 0; leaf < oldResLeafsSpawned.size(); leaf++)
			{
				final JDFResource leafRes = (JDFResource) oldResLeafsSpawned.elementAt(leaf);
				// handle multiple spawns (reference count of spawned audits!)
				leafRes.removeFromSpawnIDs(spawnID);
				calcSpawnStatus(leafRes, false);
			}
			if (!newRes.getParentJDF().getID().equals(oldRes.getParentJDF().getID()))
			{
				// this has been copied from lower down up and MUST be
				// deleted...
				newRes.deleteNode();
			}
			else
			{
				// don't use a simple for because deleting a parent may
				// invalidate later resources!
				VElement newResLeafsSpawned = newRes.getNodesWithSpawnID(spawnID);
				// just in case: if no SpawnID exists assume the whole thing
				if (newResLeafsSpawned.size() == 0)
				{
					newResLeafsSpawned.add(newRes);
				}
				while (newResLeafsSpawned.size() > 0)
				{
					// use the last because it is potentially the root...
					final JDFResource leafRes = (JDFResource) newResLeafsSpawned.elementAt(newResLeafsSpawned.size() - 1);
					final boolean bZappRoot = leafRes.equals(newRes);
					leafRes.deleteNode();
					// we killed the root, nothing can be left...
					if (bZappRoot)
					{
						break;
					}
					// regenerate the list
					newResLeafsSpawned = newRes.getNodesWithSpawnID(spawnID);
				}
			}
		}
	}

	/**
	 * clean up the spawn and merge audits in this Node
	 * <p>
	 * default: CleanUpMerge(EnumCleanUpMerge cleanPolicy, JDFConstants.EMPTYSTRING, false)
	 * 
	 * @param cleanPolicy policy how to clean up the spawn and merge audits after merging
	 * @param spawnID the SpawnID of the spawn and MergeID of the merge to clean up.<br>
	 *            If not specified all spawns will be cleaned up.
	 * @param bRecurse if true also recurse into all child JDF nodes; default=false
	 */

	private void cleanUpMerge(JDFNode overWriteTmpNode, EnumCleanUpMerge cleanPolicy, boolean bRecurse)
	{
		if (bAddMergeToProcessRun)
		{
			VElement vProcessRun = subJDFNode.getChildrenByTagName(ElementName.PROCESSRUN, null, new JDFAttributeMap(AttributeName.SPAWNID, spawnID), false, true, -1);
			JDFSpawned spawned = (JDFSpawned) overWriteTmpNode.getChildByTagName(ElementName.SPAWNED, null, 0, new JDFAttributeMap(AttributeName.NEWSPAWNID, spawnID), false, true);
			JDFMerged merged = (JDFMerged) overWriteTmpNode.getChildByTagName(ElementName.MERGED, null, 0, new JDFAttributeMap(AttributeName.MERGEID, spawnID), false, true);
			for (int k = 0; k < vProcessRun.size(); k++)
			{
				JDFProcessRun pr = (JDFProcessRun) vProcessRun.elementAt(k);
				if (!pr.hasAttribute("ReturnTime"))
				{
					if (merged != null)
						pr.setReturnTime(merged.getTimeStampDate());
					if (spawned != null)
						pr.setSubmissionTime(spawned.getTimeStampDate());
				}
			}
		}
		if (!cleanPolicy.equals(EnumCleanUpMerge.None))
		{
			if (bRecurse)
			{
				final VElement v = overWriteTmpNode.getvJDFNode(null, null, false);
				for (int i = v.size(); i >= 0; i--)
				{
					cleanUpMerge((JDFNode) v.elementAt(i), cleanPolicy, false);
				}
			}
			else
			{
				JDFAuditPool auditPool = overWriteTmpNode.getAuditPool();
				if (auditPool != null)
					cleanUpMergeAudits(auditPool, cleanPolicy);
			}
		}
	}

	/**
	 * @param cleanPolicy
	 * @param spawnID
	 */
	private void cleanUpMergeAudits(JDFAuditPool pool, JDFNode.EnumCleanUpMerge cleanPolicy)
	{
		if (cleanPolicy != JDFNode.EnumCleanUpMerge.None)
		{
			VElement vMerged = new VElement();
			VElement vSpawned = new VElement();
			if (KElement.isWildCard(spawnID))
			{
				vMerged = pool.getAudits(JDFAudit.EnumAuditType.Merged, null, null);
				vSpawned = pool.getAudits(JDFAudit.EnumAuditType.Spawned, null, null);
			}
			else
			{

				final JDFAttributeMap mSpawnID = new JDFAttributeMap(AttributeName.MERGEID, spawnID);
				JDFAudit a = pool.getAudit(0, JDFAudit.EnumAuditType.Merged, mSpawnID, null);
				if (a != null)
				{
					vMerged.add(a);
				}
				mSpawnID.clear();
				mSpawnID.put(AttributeName.NEWSPAWNID, spawnID);
				a = pool.getAudit(0, JDFAudit.EnumAuditType.Spawned, mSpawnID, null);
				if (a != null)
				{
					vSpawned.add(a);
				}
			}
			for (int i = vMerged.size() - 1; i >= 0; i--)
			{
				final JDFMerged merged = (JDFMerged) vMerged.elementAt(i);
				final String mergeID = merged.getMergeID();
				KElement doubleSpawn = pool.getChildWithAttribute(ElementName.SPAWNED, AttributeName.SPAWNID, null, mergeID, 0, true);
				if (doubleSpawn != null)
					continue; // skip cleanup in case spawned audits rely on
				// this

				for (int j = vSpawned.size() - 1; j >= 0; j--)
				{
					final JDFSpawned spawned = (JDFSpawned) vSpawned.elementAt(i);
					if (spawned.getNewSpawnID().equals(mergeID))
					{

						if (cleanPolicy == JDFNode.EnumCleanUpMerge.RemoveAll)
						{
							spawned.deleteNode();
							merged.deleteNode();
							vSpawned.remove(j);
						}
						else if (cleanPolicy == JDFNode.EnumCleanUpMerge.RemoveRRefs)
						{
							spawned.removeAttribute(AttributeName.RREFSRWCOPIED);
							spawned.removeAttribute(AttributeName.RREFSROCOPIED);
							merged.removeAttribute(AttributeName.RREFSOVERWRITTEN);
						}
						else
						{
							// never get here
							throw new JDFException("JDFNode.EnumCleanUpMerge: illegal cleanPolicy enumeration: "
									+ cleanPolicy.getValue());
						}
					}
				}
			}
		}
	}
}
