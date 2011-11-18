/*
 * The CIP4 Software License, Version 1.0
 *
 *
 * Copyright (c) 2001-2011 The International Cooperation for the Integration of
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

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.cip4.jdflib.auto.JDFAutoNotification.EnumClass;
import org.cip4.jdflib.core.AttributeName;
import org.cip4.jdflib.core.ElementName;
import org.cip4.jdflib.core.JDFAudit;
import org.cip4.jdflib.core.JDFAudit.EnumAuditType;
import org.cip4.jdflib.core.JDFConstants;
import org.cip4.jdflib.core.JDFElement;
import org.cip4.jdflib.core.JDFElement.EnumVersion;
import org.cip4.jdflib.core.JDFException;
import org.cip4.jdflib.core.JDFPartAmount;
import org.cip4.jdflib.core.JDFPartStatus;
import org.cip4.jdflib.core.JDFResourceLink;
import org.cip4.jdflib.core.KElement;
import org.cip4.jdflib.core.VElement;
import org.cip4.jdflib.core.VString;
import org.cip4.jdflib.datatypes.JDFAttributeMap;
import org.cip4.jdflib.datatypes.VJDFAttributeMap;
import org.cip4.jdflib.node.JDFNode;
import org.cip4.jdflib.node.JDFNode.EnumCleanUpMerge;
import org.cip4.jdflib.node.JDFSpawned;
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
 * @author Rainer Prosi This class is used when merging a JDF node it summarizes all merging routines the had been part of JDF Node
 */
public class JDFMerge
{
	private JDFSpawned spawnAudit;
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
	private String urlMerge;
	private final Log log;

	/**
	 * set this to true if you want to update the stati of the relevant parent nodes based on the new Stati of the merged node
	 */
	public boolean bUpdateStati = false;

	/**
	 * set this to true if you want to update the ProcessRun(s) timestamps for this merge
	 */
	public boolean bAddMergeToProcessRun = false;
	private EnumCleanUpMerge cleanPolicy;
	private EnumAmountMerge amountPolicy;
	private VJDFAttributeMap parts;

	/**
	 * 
	 * @param parentNode the parent node to merge into. MAY be the actual node to be replace or any Parent thereof
	 */
	public JDFMerge(final JDFNode parentNode)
	{
		super();
		spawnAudit = null;
		urlMerge = null;
		m_ParentNode = parentNode;
		parts = null;
		cleanPolicy = EnumCleanUpMerge.None;
		amountPolicy = EnumAmountMerge.None;
		log = LogFactory.getLog(getClass());
	}

	/**
	 * merge a previously spawned JDF into a node that is a child of, or this root
	 * <p>
	 * default: mergeJDF(subJDFNode, null, JDFNode.EnumCleanUpMerge.None, JDFResource.EnumAmountMerge.None)
	 * 
	 * @param _toMerge the previously spawned jdf node
	 * @param urlMerge the url of the ???
	 * @param cleanPolicy policy how to clean up the spawn and merge audits after merging
	 * @param amountPolicy policy how to clean up the Resource amounts after merging
	 * @return JDFNode - the merged node in the new document<br>
	 * note that the return value used to be boolean. The boolean value is now replaced by exceptions. This corresponds to <code>true</code> always.
	 * 
	 * @throws JDFException if subJDFNode has already been merged
	 * @throws JDFException if subJDFNode was not spawned from this
	 * @throws JDFException if subJDFNode has no AncestorPool
	 * 
	 * default: mergeJDF(subJDFNode, null, JDFNode.EnumCleanUpMerge.None, JDFResource.EnumAmountMerge.None)
	 */
	public JDFNode mergeJDF(final JDFNode _toMerge, final String urlMerge, final EnumCleanUpMerge cleanPolicy, final JDFResource.EnumAmountMerge amountPolicy)
	{
		this.urlMerge = urlMerge;
		this.cleanPolicy = cleanPolicy;
		this.amountPolicy = amountPolicy;
		return mergeJDF(_toMerge);
	}

	/**
	 * merge a previously spawned JDF into a node that is a child of, or this root
	 * 
	 * @param _toMerge the previously spawned jdf node		
		 * @return JDFNode - the merged node in the new document<br>
		 * note that the return value used to be boolean. The boolean value is now replaced by exceptions. This corresponds to <code>true</code> always.
		 * 
		 * @throws JDFException if subJDFNode has already been merged
		 * @throws JDFException if subJDFNode was not spawned from this
		 * @throws JDFException if subJDFNode has no AncestorPool
		 */
	public synchronized JDFNode mergeJDF(final JDFNode _toMerge)
	{
		subJDFNode = _toMerge;
		if (subJDFNode == null || !subJDFNode.hasParent(m_ParentNode))
		{
			throw new JDFException("JDFNode.MergeJDF no matching parent found");
		}
		findOverwriteNode();

		analyzeAncestorPool(true);

		// merge copied readOnly resources
		vsRO = spawnAudit.getrRefsROCopied().getSet();
		vsRW = spawnAudit.getrRefsRWCopied().getSet();

		final String preSpawn = mergeCheckPrespawn();

		mergeLocalLinks();

		cleanROResources();
		mergeRWResources();

		mergeLocalNodes();
		final JDFMerged mergeAudit = mergeMainPools(preSpawn);
		// an empty spawnID should never happen here, but check just in case
		// since an empty spawnID in CleanUpMerge removes all Spawned audits
		if (spawnID != null)
		{
			final JDFNode overWriteParent = mergeAudit.getParentJDF(); // since all links get screwed up, let's relink here
			cleanUpMerge(overWriteParent);
		}

		// now burn it in!
		overWriteNode = (JDFNode) overWriteNode.replaceElement(subJDFNode);
		overWriteNode.eraseEmptyNodes(true);
		// overWriteNode.synchParentAmounts(); // add all actualamounts into the merged parent gray box
		// update all stati (generally in NodeInfo) of the merged node and of the parents of the merged node
		if (bUpdateStati)
		{
			overWriteNode.updatePartStatus(parts, true, true, 0);
		}
		return overWriteNode;
	}

	/**
	 * merge a previously spawned and previously merged JDF into a node that is a child of, or this root
	 * 
	 * @param _toMerge the previously spawned jdf node		
		 * @return JDFNode - the merged node in the new document<br>
		 * note that the return value used to be boolean. The boolean value is now replaced by exceptions. This corresponds to <code>true</code> always.
		 * 
		 * @throws JDFException if subJDFNode has no AncestorPool
		 */
	public synchronized JDFNode remergeJDF(final JDFNode _toMerge)
	{
		try
		{
			return mergeJDF(_toMerge);
		}
		catch (JDFException x)
		{
			log.error("Snafu merging - trying default merge", x);
		}

		subJDFNode = _toMerge;
		if (subJDFNode == null || !subJDFNode.hasParent(m_ParentNode))
		{
			throw new JDFException("JDFNode.remergeJDF no matching parent found");
		}
		findOverwriteNode();

		analyzeAncestorPool(false);
		mergeLocalLinks();
		remergeAuditPools();

		JDFNode overwriteParent = parts == null ? overWriteNode.getParentJDF() : overWriteNode;
		JDFAuditPool ap = overwriteParent.getCreateAuditPool();
		if (!EnumCleanUpMerge.RemoveAll.equals(cleanPolicy))
			createMergeAudit(ap, null);

		// update all stati (generally in NodeInfo) of the merged node and of the parents of the merged node
		if (bUpdateStati)
		{
			overWriteNode.updatePartStatus(parts, true, true, 0);
		}
		return overWriteNode;
	}

	protected void remergeAuditPools()
	{
		final VElement vn = overWriteNode.getvJDFNode(null, null, false);
		final int size = vn.size();
		// merge local (internal) partitioned resource links
		for (int nod = 0; nod < size; nod++)
		{
			JDFNode toMergeLocalNode = (JDFNode) vn.elementAt(nod);
			JDFNode overwriteLocalNode = subJDFNode.getChildJDFNode(toMergeLocalNode.getID(), false);
			// swap from / to in case of remerge, since we do not overwrite the main node
			mergeAuditPool(overwriteLocalNode, toMergeLocalNode, true);

		}
	}

	/**
	 * find the node to overwrite in main
	 * @throws JDFException if no node is found
	 */
	private void findOverwriteNode()
	{
		final String subJDFID = subJDFNode.getID();
		overWriteNode = (JDFNode) m_ParentNode.getTarget(subJDFID, AttributeName.ID);

		if (overWriteNode == null)
		{
			throw new JDFException("JDFNode.MergeJDF no Node with ID: " + subJDFID);
		}
	}

	/**
	 * 
	 * find matching audits	 
	 * 
	 */
	private void analyzeAncestorPool(boolean bFindSpawnAudit)
	{
		// tbd multiple ancestor handling
		final String subJDFID = subJDFNode.getID();

		final JDFAncestorPool ancestorPool = subJDFNode.getAncestorPool();
		if (ancestorPool == null)
		{
			throw new JDFException("JDFNode.MergeJDF no Ancestor Pool in Node: " + subJDFID);
		}
		this.parts = ancestorPool.getPartMapVector();

		if (bFindSpawnAudit)
			findSpawnAudit();
	}

	protected void findSpawnAudit()
	{
		final String subJDFID = subJDFNode.getID();
		final JDFAncestorPool ancestorPool = subJDFNode.getAncestorPool();
		final int numAncestors = ancestorPool.numChildElements(ElementName.ANCESTOR, null);

		if (numAncestors <= 0)
		{
			throw new JDFException("JDFNode.MergeJDF no Ancestors in AncestorPool found. Node: " + subJDFID);
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

			final JDFNode nodeInParent = (JDFNode) k;
			final JDFAuditPool auditPool = nodeInParent.getCreateAuditPool();

			// find all ids of previous merge operations for reverse merge cleanup
			final VElement vMergeAudit = auditPool.getAudits(EnumAuditType.Merged, null, null);
			for (int nMerged = 0; nMerged < vMergeAudit.size(); nMerged++)
			{
				final JDFMerged merged = (JDFMerged) vMergeAudit.elementAt(nMerged);
				previousMergeIDs.appendUnique(merged.getMergeID());
			}

			if (iFound != 0) // we've already found a spawned Audit, just looping for previous merges
			{
				continue;
			}

			// get appropriate spawned element
			final VElement vSpawnAudit = auditPool.getChildrenByTagName(ElementName.SPAWNED, null, new JDFAttributeMap(AttributeName.JREF, subJDFID), true, true, 0);
			spawnID = subJDFNode.getSpawnID(false);

			for (int isp = vSpawnAudit.size() - 1; isp >= 0; isp--)
			{ // loop backwards because the latest is assumed correct
				final JDFSpawned testSpawn = (JDFSpawned) vSpawnAudit.elementAt(isp);
				String newSpawnID = testSpawn.getNewSpawnID();
				if (newSpawnID != null && newSpawnID.equals(spawnID))
				{
					// tbd check for matching merged...
					spawnAudit = testSpawn;
					final JDFMerged matchingMerged = (JDFMerged) auditPool.getChildWithAttribute(ElementName.MERGED, AttributeName.MERGEID, null, spawnID, 0, true);

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

		// if the spawn Audit is not found at the first attempt, something went badly wrong
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
	 * @param _overWriteNode
	 * @param toMerge the source node of the audit pool to merge into this
	 */
	private void mergeAuditPool(final JDFNode _overWriteNode, final JDFNode toMerge, boolean remerge)
	{
		// merge audit pool
		final JDFAuditPool overWriteAuditPool = _overWriteNode.getAuditPool();
		final JDFAuditPool toMergeAuditPool = toMerge.getAuditPool();

		// the node that is overwritten has an audit pool that must be merged
		if (overWriteAuditPool != null)
		{
			// the overwriting node node is empty, just copy the previous pool
			if (toMergeAuditPool == null)
			{
				toMerge.copyElement(overWriteAuditPool, null);
			}
			else if (remerge)
			{
				toMergeAuditPool.appendUnique(overWriteAuditPool);
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

	private void mergeComments(final JDFNode poverWriteNode, final JDFNode toMerge)
	{
		final VElement v = poverWriteNode.getChildElementVector(ElementName.COMMENT, null, null, false, 0, false);
		final VElement vToMerge = toMerge.getChildElementVector(ElementName.COMMENT, null, null, false, 0, false);
		final int siz = vToMerge.size(); // size prior to appending
		vToMerge.appendUniqueElement(v);
		for (int i = siz; i < vToMerge.size(); i++)
		{
			toMerge.moveElement(vToMerge.elementAt(i), null);
		}
	}

	/**
	 * 
	 * merge the local resource links - mainly amounts
	 */
	private void mergeLocalLinks()
	{
		final int numParts = parts == null ? 0 : parts.size();
		final VElement vn = overWriteNode.getvJDFNode(null, null, false);
		final int size = vn.size();
		// merge local (internal) partitioned resource links
		for (int nod = 0; nod < size; nod++)
		{
			JDFNode overwriteLocalNode = (JDFNode) vn.elementAt(nod);
			JDFNode toMergeLocalNode = subJDFNode.getChildJDFNode(overwriteLocalNode.getID(), false);
			// swap from / to in case of remerge, since we do not overwrite the main node
			mergeResourceLinkPool(overwriteLocalNode, toMergeLocalNode);

			final EnumVersion version = toMergeLocalNode.getVersion(true);
			if ((version != null) && (version.getValue() >= EnumVersion.Version_1_3.getValue()))
			{
				final JDFNode.EnumNodeStatus stat = toMergeLocalNode.getStatus();
				if (stat != null && !stat.equals(JDFElement.EnumNodeStatus.Part) && !stat.equals(JDFElement.EnumNodeStatus.Pool) && numParts > 0)
				{
					toMergeLocalNode.setPartStatus(parts, stat, null);
				}
			}
		}
	}

	// //////////////////////////////////////////////////////////////////////////
	/**
	 * merge the node and all its child nodes<br/>
	 * NOTE: the incoming node is modified and finally copied into the main, therefore stuff is copied from main to sub, rather than sub to main as would naively be expected
	 */
	private void mergeLocalNodes()
	{
		// merge local (internal) partitioned resources
		final VElement vn = overWriteNode.getvJDFNode(null, null, false);
		for (int nod = 0; nod < vn.size(); nod++)
		{
			final JDFNode overwriteLocalNode = (JDFNode) vn.elementAt(nod);
			mergeLocalNode(overwriteLocalNode);
		}
	}

	/**
	 * merge a local node<br/>
	 * NOTE: the incoming node is modified and finally copied into the main, therefore stuff is copied from main to sub, rather than sub to main as would naively be expected
	 */
	private void mergeLocalNode(final JDFNode overwriteLocalNode)
	{
		final JDFNode toMergeLocalNode = (JDFNode) subJDFNode.getTarget(overwriteLocalNode.getID(), AttributeName.ID);
		mergeLocalResourcePool(overwriteLocalNode, toMergeLocalNode);

		// retain all other elements of the original (non spawned) JDF Node if the spawn is partitioned
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
					mergeAuditPool(overwriteLocalNode, toMergeLocalNode, false);
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

	protected void mergeLocalResourcePool(final JDFNode overwriteLocalNode, final JDFNode toMergeLocalNode)
	{
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
	}

	/**
	 * Merges partitioned resources into this resource uses PartIDKey to identify the correct resources
	 * @param targetRes
	 * 
	 * @param resToMerge the resource leaf to merge into this
	 * @param spawnID the spawnID of the spawning that will now be merged
	 * @param amountPolicy how to clean up the Resource amounts after merging
	 * @param bLocalResource must be true for the local resources in a spawned node and its subnodes, which default to RW
	 * @return
	 * 
	 * @throws JDFException if here is an attempt to merge incompatible resources
	 * @throws JDFException if here is an attempt to merge incompatible partitions
	 * 
	 * @default mergePartition (resToMerge, spawnID, EnumAmountMerge.None, false);
	 */
	/*
	 * mergePartition will stay public, as long as deprecated JDFResource.mergePartition is not deleted
	 */
	static public JDFResource mergePartition(final JDFResource targetRes, final JDFResource resToMerge, final String spawnID, final EnumAmountMerge amountPolicy, final boolean bLocalResource)
	{
		if (resToMerge == null)
			return null;

		if (!targetRes.getID().equals(resToMerge.getID()))
		{
			throw new JDFException("JDFResource.mergePartition  merging incompatible resources ID=" + targetRes.getID() + " IDMerge=" + resToMerge.getID());
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
		HashMap<JDFAttributeMap, JDFResource> cacheMap = allChildren.size() > 2 ? targetRes.getPartitionMap() : null;
		for (int i = 0; i < allChildren.size(); i++)
		{
			final JDFResource src = (JDFResource) allChildren.elementAt(i);
			if (src.getIdentical() != null)
			{
				continue; // no need to merge identical elements
			}
			final JDFAttributeMap srcMap = src.getPartMap(mergeIDKeys);
			JDFResource trg = cacheMap == null ? targetRes.getPartition(srcMap, EnumPartUsage.Implicit) : cacheMap.get(srcMap);

			if (trg == null)
			{
				trg = targetRes;
			}
			JDFAttributeMap trgMap = trg.getPartMap();

			// RP 220605 - not puristic, but pragmatic
			// found only a root or high level partition for an rw resource partition
			// try to create the new partition and pray that it will not be  subsequently completely overwritten
			// this is still better than throwing an exception or silentlyignoring the rw resource
			if ((src.getLocked() == false) && (trgMap.getKeys().size() < srcMap.getKeys().size()))
			{
				LogFactory.getLog(JDFMerge.class).warn("creating non existing rw partition: " + srcMap);
				trg = targetRes.getCreatePartition(srcMap, partIDKeys);
				// fool the algorithm to think that the new partition is rw (which it probably was)
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
						final KElement copyElement = targetRes.copyElement(src, null);
						trg = (JDFResource) trg.replaceElement(copyElement);
					}
				}
				else if (srcMap.subMap(trgMap))
				{
					if (trgMap.size() + 1 != srcMap.size())
					{
						throw new JDFException("JDFResource.mergePartition attempting to merge incompatible sub-partitions in: " + trg.getID());
					}

					trg.copyElement(src, null);
				}
				else
				{ // oops
					throw new JDFException("JDFResource.mergePartition attempting to merge incompatible partitions in: " + trg.getID());
				}
			}
			// update the partitions amounts
			if ((amountPolicy != EnumAmountMerge.None) && targetRes.isPhysical())
			{
				final JDFResource trgKeep = trg;
				trg = root.getPartition(srcMap, EnumPartUsage.Implicit); // must repeat since replaceelement does not modify itself
				if (trg == null)
				{
					trg = trgKeep;
				}
				final VElement vr = trg.getLeaves(true);
				for (int l = 0; l < vr.size(); l++)
				{
					final JDFResource r = (JDFResource) vr.elementAt(l);
					r.updateAmounts(EnumAmountMerge.UpdateLink.equals(amountPolicy));
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

	private void mergeLocalResource(final JDFResource.EnumAmountMerge amountPolicy, final JDFResourcePool poolToMerge, JDFResource res1)
	{
		final String resID = res1.getID();
		final JDFResource res2 = poolToMerge.getResourceByID(resID);

		if (res2 != null)
		{
			mergeSpawnIDs(res2, res1, false);
			res1 = mergePartition(res1, res2, spawnID, amountPolicy, true); // esp. deletes res2 from subJDFNode node
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
			{
				spawnIDs.removeAll(previousMergeIDs);
			}
			leafRes.setSpawnIDs(spawnIDs);
			calcSpawnStatus(leafRes, true);
		}
	}

	private void calcSpawnStatus(final JDFResource leafRes, final boolean bLocal)
	{
		if (leafRes == null)
		{
			return;
		}
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

			for (int i = 0; i < spawnIDs.size(); i++) // check for multiple rw spawns
			{

				final String resSpawnID = spawnIDs.stringAt(i);
				final JDFSpawned spawnedAudit = newSpawnMap.get(resSpawnID);
				if (spawnedAudit != null)
				{
					final VString rw = spawnedAudit.getrRefsRWCopied();
					if (rw != null && rw.contains(resID))
					{
						bWrite = true;
					}
				}
				else
				// clean up spurious spawnids of spawns that were initiated off line
				{
					final String mainSpawnID = leafRes.getJDFRoot().getSpawnID(true);
					if (KElement.isWildCard(mainSpawnID)) // only remove unknown
					// spawnids in a real main ticket
					// Spawned spawnids may be specified in a spawn ancestor
					{
						leafRes.removeFromAttribute(AttributeName.SPAWNIDS, resSpawnID, null, null, -1);
						final String spawnIDsNew = leafRes.getAttribute_KElement(AttributeName.SPAWNIDS, null, null);
						if (StringUtil.getNonEmpty(spawnIDsNew) == null)
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
		{
			return;
		}

		newSpawnMap = new HashMap<String, JDFSpawned>();
		// collect in main AND sub jdf in case someone spawned in the sub jdf...
		final JDFNode[] array = new JDFNode[2];
		array[0] = subJDFNode;
		array[1] = m_ParentNode;
		for (final JDFNode nParent : array)
		{
			final VElement v = nParent.getvJDFNode(null, null, false);
			for (int i = 0; i < v.size(); i++)
			{
				final JDFNode n = (JDFNode) v.get(i);
				final JDFAuditPool ap = n.getAuditPool();
				final VElement v2 = ap == null ? null : ap.getAudits(EnumAuditType.Spawned, null, null);
				if (v2 != null)
				{
					// JDFSpawned s=(JDFSpawned) v.get(i);
					final int siz = v2.size();
					for (int j = 0; j < siz; j++)
					{
						final JDFSpawned s = (JDFSpawned) v2.get(j);
						final String nsID = s.getNewSpawnID();
						if (!KElement.isWildCard(nsID))
						{
							newSpawnMap.put(nsID, s);
						}
					}
				}
			}
		}
	}

	// ///////////////////////////////////////////////////////////////////

	private JDFMerged mergeMainPools(final String preSpawn)
	{
		// add the merged audit - maintain sychronicity of spawned and merged
		JDFNode overWriteParent = null;
		JDFAuditPool ap = subJDFNode.getAuditPool();
		JDFSpawned spawnedAudit = null;

		if (ap != null)
		{
			spawnedAudit = (JDFSpawned) ap.getChildWithAttribute(ElementName.SPAWNED, AttributeName.NEWSPAWNID, null, spawnID, 0, true);
			overWriteParent = overWriteNode;
		}

		if (spawnedAudit == null)
		{
			overWriteParent = overWriteNode.getParentJDF();
			if (overWriteParent == null)
			{
				throw new JDFException("mergeMainPools - corrupt audit structure");
			}

			ap = overWriteParent.getAuditPool();
			if (ap != null)
			{
				spawnedAudit = (JDFSpawned) ap.getChildWithAttribute(ElementName.SPAWNED, AttributeName.NEWSPAWNID, null, spawnID, 0, true);
			}
		}

		if (spawnedAudit == null || overWriteParent == null || ap == null)
		{
			// ????
			throw new JDFException("mergeMainPools - corrupt audit structure; no Spawn Audit found");
		}

		// JDFNode overWriteParent=ap.getParentJDF();
		final VString vs = new VString();
		final Iterator<String> it = vsRW.iterator();
		while (it.hasNext())
		{
			vs.add(it.next());
		}

		final JDFMerged mergeAudit = createMergeAudit(ap, vs);

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

	private JDFMerged createMergeAudit(JDFAuditPool ap, final VString vs)
	{
		JDFMerged lastMerged = (JDFMerged) ap.getAudit(-1, EnumAuditType.Merged, new JDFAttributeMap(AttributeName.MERGEID, spawnID), null);
		final JDFMerged mergeAudit = ap.addMerged(subJDFNode, vs, null, parts);
		mergeAudit.setRef(lastMerged);

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
		return mergeAudit;
	}

	/**
	 * merge the resource link pools
	 * @param mainNode 
	 * @param toMerge the source node of the status pool to merge into this
	 */
	private void mergeResourceLinkPool(final JDFNode mainNode, final JDFNode toMerge)
	{
		final JDFResourceLinkPool resourceLinkPool = toMerge.getResourceLinkPool();
		expandLinkedResources(resourceLinkPool);

		final JDFResourceLinkPool toMergeRLP = resourceLinkPool;
		if (toMergeRLP == null)
		{
			return; // nothing to do
		}

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
							// blast the spawned link into the overWritePool completely
							overWriteLink.replaceElement(toMergeLink);
						}
					}
					else
					// 100119 RP the link was created within the spawned process - must copy it
					{
						toMergeLink.setPartMapVector(null);
						overWriteRLP.copyElement(toMergeLink, null);
					}
				}
			}

			toMergeRLP.deleteNode();
			toMerge.copyElement(overWriteRLP, null);
		}
		else
		// copy the rlp from sub to main so that amount recalc in mergerw finds all required links in main
		{
			overWriteRLP.deleteNode();
			mainNode.copyElement(toMergeRLP, null);
		}
	}

	/**
	 * @param mainLink
	 * @param subLink
	 */
	private void fixPartAmountAttributes(final JDFResourceLink mainLink, final JDFResourceLink subLink)
	{
		final JDFAmountPool subAmountPool = subLink.getAmountPool();
		final VJDFAttributeMap subLinkParts = subLink.getPartMapVector();
		final int partSize = subLinkParts.size();
		for (int i = 0; i < partSize; i++)
		{
			// final boolean hasAP =
			// mainLink.hasChildElement(ElementName.AMOUNTPOOL, null);
			VElement vSubPartAmounts = null;
			if (subAmountPool != null)
			{
				vSubPartAmounts = subAmountPool.getMatchingPartAmountVector(subLinkParts.elementAt(i));
			}

			if (vSubPartAmounts == null)
			{
				final JDFAttributeMap subLinkMap = subLink.getAttributeMap();
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
					final JDFPartAmount mainPartAmount = mainLink.getCreateAmountPool().getCreatePartAmount(subLinkParts.elementAt(i));
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
					final JDFPartAmount subPartAmount = (JDFPartAmount) vSubPartAmounts.elementAt(j);
					final JDFAttributeMap subAmountMap = subPartAmount.getAttributeMap();
					removeIdenticalFromPartAmountMap(mainLink, subAmountMap);

					if (!subAmountMap.isEmpty())
					{
						final JDFAttributeMap subPartMap = subPartAmount.getPartMap();
						final JDFPartAmount mainPartAmount = mainLink.getCreateAmountPool().getCreatePartAmount(subPartMap);
						mainPartAmount.setAttributes(subAmountMap);
						mainLink.removeAttributes(subAmountMap.getKeys());
					}
				}
			}

			// nothing has changed --> leave as is
		}
	}

	private void removeIdenticalFromPartAmountMap(final JDFResourceLink mainLink, final JDFAttributeMap subLinkMap)
	{
		{
			final JDFAttributeMap mainLinkMap = mainLink.getAttributeMap();
			final Iterator<String> iter = mainLinkMap.getKeyIterator();
			while (iter.hasNext())
			{
				final String key = iter.next();
				if (mainLinkMap.get(key).equals(subLinkMap.get(key)))
				{
					subLinkMap.remove(key);
				}
			}
		}
	}

	/**
	 * @param resourceLinkPool the resourceLinkPool that contains the links to the resources to expand
	 */
	private void expandLinkedResources(final JDFResourceLinkPool resourceLinkPool)
	{
		final VElement links = resourceLinkPool == null ? null : resourceLinkPool.getPoolChildren(null, null, null);
		if (links != null)
		{
			final int size = links.size();
			for (int i = 0; i < size; i++)
			{
				final JDFResourceLink rl = (JDFResourceLink) links.elementAt(i);
				// 071214 only expand if rw
				if (vsRW.contains(rl.getrRef()))
				{
					rl.expandTarget(false);
				}
			}
		}
	}

	/**
	 * Merges the spawnIDs of the various partitions <br>
	 * also updates SpawnStatus, if necessary <br>
	 * this routine is needed to correctly handle nested spawning and merging
	 * 
	 * @param mainRes the resource in the main jdf to merge to
	 * @param resToMerge the resource with potentially new spawnIDs
	 * @param bReadOnly if true, don't add anything since it was RO
	 * 
	 */
	private void mergeSpawnIDs(final JDFResource mainRes, final JDFResource resToMerge, final boolean bReadOnly)
	{
		if (mainRes == null || resToMerge == null)
			return;

		if (!ContainerUtil.equals(mainRes.getID(), resToMerge.getID()))
		{
			throw new JDFException("JDFResource.mergeSpawnIDs  merging incompatible resources ID = " + mainRes.getID() + " IDMerge = " + resToMerge.getID());
		}

		final VElement allLeaves = new VElement();
		final VElement spawnedLeaves = mainRes.getNodesWithSpawnID(spawnID);

		// Only manipulate leaves and subleaves that were explicitly spawned
		if (spawnedLeaves != null)
		{
			for (int i = 0; i < spawnedLeaves.size(); i++)
			{
				allLeaves.addAll(((JDFResource) spawnedLeaves.get(i)).getLeaves(true));
			}
			allLeaves.unify();
		}

		final VString partIDKeys = mainRes.getPartIDKeys();
		HashMap<JDFAttributeMap, JDFResource> cacheMap = allLeaves.size() > 2 ? resToMerge.getPartitionMap() : null;
		for (int i = 0; i < allLeaves.size(); i++)
		{
			final JDFResource thisResNode = (JDFResource) allLeaves.elementAt(i);
			JDFAttributeMap partMap = thisResNode.getPartMap(partIDKeys);
			final JDFResource mergeResNode = cacheMap != null ? cacheMap.get(partMap) : resToMerge.getPartition(partMap, EnumPartUsage.Explicit);

			if (mergeResNode != null)
			{
				VString vSpawnIDs = thisResNode.getSpawnIDs(false);
				final int siz = vSpawnIDs == null ? 0 : vSpawnIDs.size();
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
				{
					vSpawnIDs.removeStrings(previousMergeIDs, 999999);
				}

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
	 * @param amountPolicy policy how to clean up the Resource amounts after merging
	 */
	private void mergeRWResources()
	{
		// merge rw resources
		final Iterator<String> it = vsRW.iterator();
		while (it.hasNext())
		{
			final String s = it.next();
			JDFResource oldRes = overWriteNode.getLinkRoot(s);
			if (oldRes == null) // also check in tree below
			{
				oldRes = overWriteNode.getTargetResource(s);
				if (oldRes == null) // also check in entire tree below root
				{
					oldRes = overWriteNode.getTargetResource(s);
				}
			}

			if (oldRes != null)
			{
				final JDFResource newRes = subJDFNode.getTargetResource(s);

				// merge all potential new spawnIds from this to subJDFNode before
				// merging them
				mergeSpawnIDs(oldRes, newRes, false);
				// do both, since some leaves may be RO
				mergeSpawnIDs(newRes, oldRes, false);

				if (newRes != null)
				{
					try
					{
						// merge the resource from the spawned node into the lower level resourcepool
						oldRes = mergePartition(oldRes, newRes, spawnID, amountPolicy, false);
					}
					catch (final Exception e)
					{
						throw new JDFException("JDFNode:mergeJDF, error in mergePartition: ID=" + (oldRes == null ? ">>> oldRes is null !!! <<<" : oldRes.getID()) + " SpawnID="
								+ spawnID);
					}
				}

				final VElement oldResLeafsSpawned = oldRes.getNodesWithSpawnID(spawnID);
				for (int leaf = 0; leaf < oldResLeafsSpawned.size(); leaf++)
				{
					final JDFResource leafRes = (JDFResource) oldResLeafsSpawned.elementAt(leaf);
					leafRes.removeFromSpawnIDs(spawnID);
					calcSpawnStatus(leafRes, false);
				}
			}
		}
	}

	/**
	 * merge the status pools
	 * 
	 * @param poverWriteNode the source node of the status pool to merge into this
	 * @param toMerge 
	 * @param parts the partitions to merge
	 */
	private void mergeStatusPool(final JDFNode poverWriteNode, final JDFNode toMerge, final VJDFAttributeMap parts)
	{
		if (toMerge.hasChildElement(ElementName.STATUSPOOL, null) || poverWriteNode.hasChildElement(ElementName.STATUSPOOL, null))
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
				if (parts != null)
				{
					for (int i = 0; i < size; i++)
					{
						// clean up the pool to overwrite
						final VElement vpso = overWriteStatusPool.getMatchingPartStatusVector(parts.elementAt(i));
						for (int j = 0; j < vpso.size(); j++)
						{
							// remove all matching partstatus elements in case
							// they were expanded in the spawned node
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
				}

				toMergeStatusPool.replaceElement(overWriteStatusPool);
			}
			else
			{
				// this part of the program will probably never be executed,
				// but...
				if (parts != null)
				{
					for (int i = 0; i < size; i++)
					{
						overWriteStatusPool.setStatus(parts.elementAt(i), toMerge.getStatus(), null);
					}
				}
				if (toMergeStatusPool != null)
				{
					toMergeStatusPool.deleteNode();
				}
				toMerge.setStatus(JDFElement.EnumNodeStatus.Pool);
				toMerge.moveElement(overWriteStatusPool, null);
			}
		}
	}

	/**
	 * merge the RO resources of the main JDF
	 * 
	 */
	private void cleanROResources()
	{
		final Iterator<String> it = vsRO.iterator();
		while (it.hasNext())
		{
			final String ro = it.next();
			final JDFResource newRes = subJDFNode.getTargetResource(ro);
			final JDFResource oldRes = (JDFResource) overWriteNode.getTarget(ro, AttributeName.ID);
			if (oldRes == null || newRes == null)
			{
				continue; // snafu, lets just ignore the rest and limp along
			}

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
				// this has been copied from lower down up and MUST be deleted...
				newRes.deleteNode();
			}
			else
			{
				// replace the ro in the tomerge node with a clean copy of the ro resource from main
				newRes.replaceElement(oldRes);
			}
		}
	}

	/**
	 * clean up the spawn and merge audits in this Node
	 * <p>
	 * default: CleanUpMerge(EnumCleanUpMerge cleanPolicy, JDFConstants.EMPTYSTRING, false)
	 * @param overWriteTmpNode 
	 * 
	 * @param cleanPolicy policy how to clean up the spawn and merge audits after merging
	 * If not specified all spawns will be cleaned up.
	 * @param bRecurse if true also recurse into all child JDF nodes; default=false
	 */

	private void cleanUpMerge(final JDFNode overWriteTmpNode)
	{
		if (cleanPolicy == null)
		{
			cleanPolicy = EnumCleanUpMerge.None;
		}
		if (bAddMergeToProcessRun)
		{
			final VElement vProcessRun = subJDFNode.getChildrenByTagName(ElementName.PROCESSRUN, null, new JDFAttributeMap(AttributeName.SPAWNID, spawnID), false, true, -1);
			final JDFSpawned spawned = (JDFSpawned) overWriteTmpNode.getChildByTagName(ElementName.SPAWNED, null, 0, new JDFAttributeMap(AttributeName.NEWSPAWNID, spawnID), false, true);
			final JDFMerged merged = (JDFMerged) overWriteTmpNode.getChildByTagName(ElementName.MERGED, null, 0, new JDFAttributeMap(AttributeName.MERGEID, spawnID), false, true);
			for (int k = 0; k < vProcessRun.size(); k++)
			{
				final JDFProcessRun pr = (JDFProcessRun) vProcessRun.elementAt(k);
				if (!pr.hasAttribute("ReturnTime"))
				{
					if (merged != null)
					{
						pr.setReturnTime(merged.getTimeStampDate());
					}
					if (spawned != null)
					{
						pr.setSubmissionTime(spawned.getTimeStampDate());
					}
				}
			}
		}
		if (!EnumCleanUpMerge.None.equals(cleanPolicy))
		{
			final JDFAuditPool auditPool = overWriteTmpNode.getAuditPool();
			if (auditPool != null)
			{
				cleanUpMergeAudits(auditPool);
			}
		}
	}

	/**
	 * @param pool 
	 * @param cleanPolicy
	 */
	private void cleanUpMergeAudits(final JDFAuditPool pool)
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
				final KElement doubleSpawn = pool.getChildWithAttribute(ElementName.SPAWNED, AttributeName.SPAWNID, null, mergeID, 0, true);
				if (doubleSpawn != null)
				{
					continue; // skip cleanup in case spawned audits rely on
					// this
				}

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
							throw new JDFException("JDFNode.EnumCleanUpMerge: illegal cleanPolicy enumeration: " + cleanPolicy.getValue());
						}
					}
				}
			}
		}
	}

	/**
	 * 
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString()
	{
		return "JDFMerge: " + spawnID + " parts: " + parts + "\n" + subJDFNode;
	}

	/**
	 * 
	 * @return
	 */
	public EnumCleanUpMerge getCleanPolicy()
	{
		return cleanPolicy;
	}

	/**
	 * 
	 * 
	 * @param cleanPolicy
	 */
	public void setCleanPolicy(EnumCleanUpMerge cleanPolicy)
	{
		this.cleanPolicy = cleanPolicy;
	}

	/**
	 * 
	 * 
	 * @return
	 */
	public EnumAmountMerge getAmountPolicy()
	{
		return amountPolicy;
	}

	/**
	 * 
	 * 
	 * @param amountPolicy
	 */
	public void setAmountPolicy(EnumAmountMerge amountPolicy)
	{
		this.amountPolicy = amountPolicy;
	}
}
