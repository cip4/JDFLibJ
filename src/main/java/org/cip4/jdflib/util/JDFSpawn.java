/*
 * The CIP4 Software License, Version 1.0
 *
 *
 * Copyright (c) 2001-2019 The International Cooperation for the Integration of Processes in Prepress, Press and Postpress (CIP4). All rights reserved.
 *
 * Redistribution and use in source and binary forms, with or without modification, are permitted provided that the following conditions are met:
 *
 * 1. Redistributions of source code must retain the above copyright notice, this list of conditions and the following disclaimer.
 *
 * 2. Redistributions in binary form must reproduce the above copyright notice, this list of conditions and the following disclaimer in the documentation and/or other materials provided with the
 * distribution.
 *
 * 3. The end-user documentation included with the redistribution, if any, must include the following acknowledgment: "This product includes software developed by the The International Cooperation for
 * the Integration of Processes in Prepress, Press and Postpress (www.cip4.org)" Alternately, this acknowledgment may appear in the software itself, if and wherever such third-party acknowledgments
 * normally appear.
 *
 * 4. The names "CIP4" and "The International Cooperation for the Integration of Processes in Prepress, Press and Postpress" must not be used to endorse or promote products derived from this software
 * without prior written permission. For written permission, please contact info@cip4.org.
 *
 * 5. Products derived from this software may not be called "CIP4", nor may "CIP4" appear in their name, without prior written permission of the CIP4 organization
 *
 * Usage of this software in commercial products is subject to restrictions. For details please consult info@cip4.org.
 *
 * THIS SOFTWARE IS PROVIDED ``AS IS'' AND ANY EXPRESSED OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO, THE IMPLIED WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE ARE
 * DISCLAIMED. IN NO EVENT SHALL THE INTERNATIONAL COOPERATION FOR THE INTEGRATION OF PROCESSES IN PREPRESS, PRESS AND POSTPRESS OR ITS CONTRIBUTORS BE LIABLE FOR ANY DIRECT, INDIRECT, INCIDENTAL,
 * SPECIAL, EXEMPLARY, OR CONSEQUENTIAL DAMAGES (INCLUDING, BUT NOT LIMITED TO, PROCUREMENT OF SUBSTITUTE GOODS OR SERVICES; LOSS OF USE, DATA, OR PROFITS; OR BUSINESS INTERRUPTION) HOWEVER CAUSED AND
 * ON ANY THEORY OF LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY, OR TORT (INCLUDING NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE OF THIS SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY
 * OF SUCH DAMAGE. ====================================================================
 *
 * This software consists of voluntary contributions made by many individuals on behalf of the The International Cooperation for the Integration of Processes in Prepress, Press and Postpress and was
 * originally based on software copyright (c) 1999-2001, Heidelberger Druckmaschinen AG copyright (c) 1999-2001, Agfa-Gevaert N.V.
 *
 * For more information on The International Cooperation for the Integration of Processes in Prepress, Press and Postpress , please see <http://www.cip4.org/>.
 *
 *
 */
/**
 *
 */
package org.cip4.jdflib.util;

import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.Vector;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.cip4.jdflib.core.AttributeInfo.EnumAttributeType;
import org.cip4.jdflib.core.AttributeName;
import org.cip4.jdflib.core.ElementName;
import org.cip4.jdflib.core.JDFAudit;
import org.cip4.jdflib.core.JDFAudit.EnumAuditType;
import org.cip4.jdflib.core.JDFConstants;
import org.cip4.jdflib.core.JDFDoc;
import org.cip4.jdflib.core.JDFElement;
import org.cip4.jdflib.core.JDFElement.EnumNodeStatus;
import org.cip4.jdflib.core.JDFException;
import org.cip4.jdflib.core.JDFNodeInfo;
import org.cip4.jdflib.core.JDFPartAmount;
import org.cip4.jdflib.core.JDFRefElement;
import org.cip4.jdflib.core.JDFResourceLink;
import org.cip4.jdflib.core.KElement;
import org.cip4.jdflib.core.VElement;
import org.cip4.jdflib.core.VString;
import org.cip4.jdflib.datatypes.JDFAttributeMap;
import org.cip4.jdflib.datatypes.VJDFAttributeMap;
import org.cip4.jdflib.elementwalker.AttributeReplacer;
import org.cip4.jdflib.node.JDFAncestor;
import org.cip4.jdflib.node.JDFNode;
import org.cip4.jdflib.node.JDFNode.EnumActivation;
import org.cip4.jdflib.node.JDFSpawned;
import org.cip4.jdflib.pool.JDFAmountPool;
import org.cip4.jdflib.pool.JDFAncestorPool;
import org.cip4.jdflib.pool.JDFAuditPool;
import org.cip4.jdflib.pool.JDFResourcePool;
import org.cip4.jdflib.resource.JDFPart;
import org.cip4.jdflib.resource.JDFResource;
import org.cip4.jdflib.resource.JDFResource.EnumPartIDKey;
import org.cip4.jdflib.resource.JDFResource.EnumPartUsage;
import org.cip4.jdflib.resource.JDFResource.EnumSpawnStatus;
import org.cip4.jdflib.resource.PartitionGetter;
import org.cip4.jdflib.resource.process.JDFIdentical;

/**
 * @author Rainer Prosi This class is used when spawning a JDF node it summarizes all spawning routines the had been part of JDF Node
 */
public class JDFSpawn
{

	private JDFNode node;
	private JDFNode rootOut;
	private JDFNode informativeRoot;
	private final Map<JDFNode, HashSet<String>> mapAllRefs;
	private final Map<JDFResource, VString> mapRefs;
	private final Set<String> noIdentical;
	private final Set<String> hasIdentical;
	private final Set<String> isReduced;
	private final Map<String, KElement> mapResources;
	private boolean idCacheFilled;
	private final Log log;
	/**
	 * if true, reduce read only partitions, else retain entire resource
	 */
	public boolean bSpawnROPartsOnly = true;

	/**
	 * if true, allow multiple rw spawning of resources note that this feature causes race conditions when merging
	 */
	public boolean bSpawnRWPartsMultiple = false;

	/**
	 * if true, copy node info
	 */
	public boolean bCopyNodeInfo = true;

	/**
	 * if true, copy identical elements
	 */
	public boolean bSpawnIdentical = true;

	/**
	 * if true, copy customer info
	 */
	public boolean bCopyCustomerInfo = true;

	/**
	 * if true, copy comments
	 */
	public boolean bCopyComments = false;

	/**
	 * if true, ensure sufficient partitioning of rw resources, else do not add missing partitions
	 */
	public boolean bFixResources = true;
	/**
	 *
	 */
	public String parentURL = null;
	/**
	 *
	 */
	public String spawnURL = null;
	/**
	 * list of all resources to copy rw
	 */
	public VString vRWResources_in = null;
	/**
	 * list of partitions to spawn
	 */
	public VJDFAttributeMap vSpawnParts = null;
	/**
	 * list of partitions to spawn
	 */
	private VString vROSpawnParts;

	/**
	 * @return the vROSpawnParts
	 */
	public VString getvROSpawnParts()
	{
		return vROSpawnParts;
	}

	/**
	 * @param vROSpawnParts the vROSpawnParts to set if set
	 */
	public void setvROSpawnParts(final VString vROSpawnParts)
	{
		this.vROSpawnParts = vROSpawnParts;
		bSpawnROPartsOnly = !VString.isEmpty(vROSpawnParts);
	}

	private final Set<JDFAttributeMap> setSpawnParts;
	private boolean bInformative = false;
	/**
	 * exception id for multiple merge attempt
	 */
	public static final int exAlreadyMerged = 10001;
	/**
	 * exception id for multiple rw spawns
	 */
	public static final int exMultiSpawnRW = 10002;

	/**
	 *
	 * @param nodeToSpawn the node to be spawned
	 */
	public JDFSpawn(final JDFNode nodeToSpawn)
	{
		log = LogFactory.getLog(getClass());
		node = nodeToSpawn;
		informativeRoot = null;
		mapRefs = new HashMap<>();
		mapAllRefs = new HashMap<>();
		mapResources = new HashMap<>();
		noIdentical = new HashSet<>();
		isReduced = new HashSet<>();
		hasIdentical = new HashSet<>();
		idCacheFilled = false;
		setSpawnParts = new HashSet<>();
	}

	/**
	 * set the node to spawn
	 *
	 * @param newNode the node to set
	 * @throws JDFException if node is NOT in the same document as the initial node
	 */
	public void setNode(final JDFNode newNode)
	{
		if (!newNode.getOwnerDocument().equals(node.getOwnerDocument()))
		{
			throw new JDFException("Setting illegal node in spawn");
		}
		if (node != newNode)
			idCacheFilled = false;

		node = newNode;

	}

	/**
	 * spawn a node; url is the file name of the new node, vRWResourceUsage is the vector of Resources Usages (or Names if no usage exists for the process) that are spawned RW, all others are spawned
	 * read only; vParts is the vector of part maps that are to be spawned, defaults to no part, i.e. the whole thing
	 *
	 * the format is one of:<br>
	 * ResName:Input<br>
	 * ResName:Output<br>
	 * ResName:ProcessUsage<br>
	 *
	 * @return The spawned node
	 * @since 050831 added bCopyComments @ tbd enhance nested spawning of partitioned nodes
	 *
	 *        default: spawn(parentURL, null, null, null, false, false, false, false)
	 */
	public JDFNode spawn()
	{
		// need copy in order to fix up 1.3 NodeInfo spawn
		final VString vRWResources = vRWResources_in == null ? new VString() : vRWResources_in;
		isReduced.clear();
		if (!bSpawnRWPartsMultiple)
		{
			checkMultipleRWRes();
		}

		// prepare the nodeinfos in main prior to spawning
		prepareNodeInfos();
		// merge this node into it
		rootOut = (JDFNode) node.cloneNewDoc();

		final String spawnID = "Sp" + KElement.uniqueID(0); // create a spawn id for this transaction

		rootOut.setSpawnID(spawnID);
		rootOut.setVersion(node.getVersion(true));

		// need copy in order to fix up 1.3 NodeInfo spawn
		final String nodeInfoNonAncestor = "NodeInfo:Input"; // named process usage
		if (!vRWResources.contains(nodeInfoNonAncestor))
		{
			vRWResources.addElement(nodeInfoNonAncestor); // the local nodeinfo MUST always be rw
		}

		JDFNode spawnParentNode = null;
		// we want to spawn a partition
		if (!VJDFAttributeMap.isEmpty(vSpawnParts))
		{
			spawnParentNode = node;
			// don't copy the whole history along
			rootOut.removeChild(ElementName.AUDITPOOL, null, 0);
			rootOut.appendAuditPool();

			// The AncestorPool of the original JDF contains the appropriate Part elements
			final JDFAncestorPool ancpool = rootOut.getAncestorPool();
			final VJDFAttributeMap preSpawnedParts = (ancpool == null) ? null : ancpool.getPartMapVector();

			// 150102 RP add AncestorPool pre spawn part handling
			if (!VJDFAttributeMap.isEmpty(preSpawnedParts))
			{
				vSpawnParts.overlapMap(preSpawnedParts);
			}
			// we arrived at a null vector of parts - that ain't no good
			if (vSpawnParts.isEmpty())
			{
				throw new JDFException("JDFNode.Spawn attempting to spawn incompatible partitions");
			}
			setSpawnParts.addAll(vSpawnParts);
		}
		else
		// Spawn a complete node -> no partition handling
		{
			spawnParentNode = node.getParentJDF();

			if (spawnParentNode == null)
			{
				log.error("JDFNode.Spawn cannot spawn unpartitioned root node");
				throw new JDFException("JDFNode.Spawn cannot spawn unpartitioned root node");
			}
		}

		// setup the ancestor nodes
		setSpawnParent(spawnParentNode);

		final JDFSpawned spawnAudit = createSpawnAudit(spawnID, spawnParentNode);

		// find resources that must be copied
		addSpawnedResources(spawnAudit);
		finalizeSpawn(spawnAudit);

		// return the spawned node
		return rootOut;
	}

	/**
	 * prepare all nodeInfos of node for partitioned spawn
	 */
	private void prepareNodeInfos()
	{
		if (vSpawnParts == null)
		{
			return;
		}
		final VElement vn = node.getvJDFNode(null, null, false);
		// fill all resources and all links of all children into vResPool and links
		for (final KElement e : vn)
		{
			final JDFNode node = (JDFNode) e;
			// make sure we have a nodeinfo in all spawned nodes of main in case we have to merge stati
			node.prepareNodeInfo(vSpawnParts);
		}
	}

	/**
	 * check for multiple rw resources and throw a JDFException if an rw resource
	 */
	private void checkMultipleRWRes()
	{
		// only check if not explicitly requested not to check
		final Collection<JDFResource> vCheck = checkSpawnedResources();
		if (vCheck != null)
		{
			String strIDs = "JDFNode.spawn: multiply spawned rw resources: ";
			final VString vBad = new VString();
			for (final JDFResource check : vCheck)
			{
				vBad.appendUnique(check.getID());
			}
			strIDs += StringUtil.setvString(vBad, JDFConstants.BLANK, null, null);
			log.error(strIDs);
			throw new JDFException(strIDs, exMultiSpawnRW);
		}
	}

	/**
	 * cleans up node so that no multiple spawnIDs remain <br/>
	 * removes SpawnIDS and SpawnStatus from all resources that are spawnedRW by this spawn;<br/>
	 * note that the vRWResources_in and vSpawnParts MUST be populated<br/>
	 * audits are not modified
	 *
	 * @return the list of cleaned resource leaves, null if no cleanup was necessary
	 */
	public Collection<JDFResource> cleanSpawnedResources()
	{
		final Collection<JDFResource> multi = checkSpawnedResources();
		if (multi != null)
		{
			final Iterator<JDFResource> it = multi.iterator();
			while (it.hasNext())
			{
				final JDFResource r = it.next();
				r.removeInheritedAttributes(AttributeName.SPAWNIDS, null);
				r.removeInheritedAttributes(AttributeName.SPAWNSTATUS, null);
			}
		}
		return multi;
	}

	/**
	 * return the resources that would be spawned RW multiple times
	 *
	 *
	 * @return Collection: set of resources or resource partitions that would be spawned rw multiple times null if all is well
	 */
	public Collection<JDFResource> checkSpawnedResources()
	{
		final VString vRWResources = new VString(vRWResources_in);
		final HashSet<JDFResource> vMultiRes = new LinkedHashSet<>();
		// grab the root node and all it's children
		if (!idCacheFilled)
		{
			node.getOwnerDocument_JDFElement().getCreateXMLDocUserData().fillIDCache();
			idCacheFilled = true;
		}

		final HashSet<JDFElement> vRootLinks = node.getAllRefs(null, true);
		for (final JDFElement e : vRootLinks)
		{
			checkSpawnedResource(vRWResources, vMultiRes, e);
		}
		// empty if all is well
		return vMultiRes.isEmpty() ? null : vMultiRes;
	}

	/**
	 *
	 *
	 * @param vRWResources
	 * @param vMultiRes
	 * @param liRoot
	 */
	void checkSpawnedResource(final VString vRWResources, final HashSet<JDFResource> vMultiRes, final JDFElement liRoot)
	{
		JDFResource r = null;
		boolean bResRW = false;
		if (liRoot instanceof JDFResourceLink)
		{
			bResRW = linkFitsRWRes((JDFResourceLink) liRoot, vRWResources);
			if (bResRW)
			{
				final JDFResourceLink rl = (JDFResourceLink) liRoot;
				r = rl.getTarget();
			}
		}
		else if (liRoot instanceof JDFRefElement)
		{
			final JDFRefElement re = (JDFRefElement) liRoot;
			r = re.getTarget();
			if (r != null)
			{
				bResRW = resFitsRWRes(r, vRWResources);
			}
		}
		fillMultiRes(vMultiRes, r, bResRW);
	}

	/**
	 *
	 * @param vMultiRes
	 * @param r
	 * @param bResRW
	 */
	void fillMultiRes(final HashSet<JDFResource> vMultiRes, final JDFResource r, final boolean bResRW)
	{
		if (bResRW && r != null)
		{
			final VElement vRes = getSpawnLeaves(r);
			final VString partIDKeys = r.getPartIDKeys();
			for (final KElement e : vRes)
			{
				final JDFResource rTarget = (JDFResource) e;
				if (JDFResource.EnumSpawnStatus.SpawnedRW.equals(rTarget.getSpawnStatus()))
				{
					if (!vMultiRes.contains(rTarget) && (vSpawnParts == null || vSpawnParts.overlapsMap(rTarget.getPartMap(partIDKeys))))
					{
						vMultiRes.add(rTarget);
					}
				}
			}
		}
	}

	VElement getSpawnLeaves(final JDFResource r)
	{
		VElement vRes = new VElement();
		if (vSpawnParts == null || vSpawnParts.isEmpty())
		{
			vRes = r.getLeaves(false);
		}
		else
		{
			VElement partitionVector = r.getPartitionVector(vSpawnParts, null);
			if (ContainerUtil.isEmpty(partitionVector))
			{
				partitionVector = r.getPartitionVector(vSpawnParts, EnumPartUsage.Implicit);
			}
			if (partitionVector != null)
			{
				for (final KElement e : partitionVector)
				{
					final JDFResource rPart = (JDFResource) e;
					vRes.addAll(rPart.getLeafArray(false));
				}
				vRes.unify();
			}
		}
		return vRes;
	}

	private VElement prepareSpawnLinks(final JDFNode node)
	{
		final VElement vn = node.getvJDFNode(null, null, false);
		final int size = vn.size();
		final VElement outLinks = new VElement();

		// fill all links of all children of rootOut into vOutRes and outLinks
		for (int i = 0; i < size; i++)
		{
			final JDFNode vnNode_i = (JDFNode) vn.elementAt(i);
			outLinks.addAll(vnNode_i.getResourceLinks(null));
		}
		outLinks.unify();
		return outLinks;
	}

	/**
	 * @param parent
	 *
	 */
	private void setSpawnParent(final JDFNode parent)
	{
		final VString vs = parent.getAncestorIDs();
		JDFAncestorPool ancestorPool = parent.getJDFRoot().getAncestorPool();
		String lastAncestorID = JDFConstants.EMPTYSTRING;

		if (!(parent.equals(node))) // only do this if we are not spawning parallel
		{
			rootOut.removeChild(ElementName.ANCESTORPOOL, null, 0); // just in case
			if (parent.getJDFRoot().hasChildElement(ElementName.ANCESTORPOOL, null))
			{
				ancestorPool = (JDFAncestorPool) rootOut.copyElement(ancestorPool, null);
				final int numAncestors = ancestorPool.numChildElements(ElementName.ANCESTOR, null);
				if (numAncestors > 0)
				{
					lastAncestorID = ancestorPool.getAncestor(numAncestors - 1).getNodeID();
				}
			}
		}
		ancestorPool = rootOut.getCreateAncestorPool();
		ancestorPool.setPartMapVector(vSpawnParts);

		// avoid double counting of this node's root element in case of partitioned
		// spawning
		int startAncestorLoop = 0;
		if (!vs.isEmpty() && ((vs.elementAt(0)).equals(lastAncestorID)))
		{
			startAncestorLoop = 1;
		}

		// 010702 RP reversed in getAncestorIDs: the last in the list is the actual
		for (int i = startAncestorLoop; i < vs.size(); i++)
		{
			final JDFAncestor ancestor = ancestorPool.appendAncestor();
			ancestor.setNodeID(vs.elementAt(i));
			if (i == 0)
			{ // first in list is the parent
				if (parentURL != null && !parentURL.equals(JDFConstants.EMPTYSTRING))
				{
					ancestor.setFileName(parentURL);
				}
			}
		}
		rootOut.setJobID(parent.getJobID(true));

		// 180502 RP added line
		ancestorPool.copyNodeData(parent, bCopyNodeInfo, bCopyCustomerInfo, bCopyComments);
	}

	// ///////////////////////////////////////////////////////////////////////

	private JDFSpawned createSpawnAudit(final String spawnID, final JDFNode spawnParentNode)
	{
		// throw in the audits
		final JDFAuditPool p = spawnParentNode.getCreateAuditPool();
		final JDFSpawned spawnAudit = p.addSpawned(rootOut, null, null, null, null);

		// 210302 RP added if statement
		if (spawnURL != null && !spawnURL.equals(JDFConstants.EMPTYSTRING))
		{
			if (spawnURL.indexOf("://") == -1)
			{
				spawnAudit.setURL("file://" + spawnURL);
			}
			else
			{
				spawnAudit.setURL(spawnURL);
			}
		}
		spawnAudit.setNewSpawnID(spawnID);
		return spawnAudit;
	}

	/**
	 * add any resources that live in ancestor nodes to this node
	 *
	 * @param spawnAudit :
	 * @return int number of resources added to the spawned node
	 */
	private int addSpawnedResources(final JDFSpawned spawnAudit)
	{
		final VString vRWResources = new VString(vRWResources_in);
		int nSpawned = 0;
		final JDFResourcePool rPoolOut = rootOut.getCreateResourcePool();

		// must copy the ap to the nood to have a decent hook on ap referenced resources
		JDFAncestorPool ap = rootOut.getAncestorPool();
		if (ap != null)
		{
			ap = (JDFAncestorPool) node.copyElement(ap, null);
		}

		// grab the root node and all it's children
		if (!idCacheFilled)
		{
			node.getOwnerDocument_JDFElement().getCreateXMLDocUserData().fillIDCache();
			idCacheFilled = true;
		}
		final HashSet<JDFElement> vMainLinks = node.getAllRefs(null, false);

		// create a HashSet with all IDs of the newly created Node
		final HashSet<String> allIDsCopied = getAllIdsCopied();
		final String spawnID = spawnAudit.getNewSpawnID();

		// first check only read only resources, since there may be a collision
		for (int loopRORW = 0; loopRORW < 2; loopRORW++)
		{
			nSpawned = singleLoop(spawnAudit, vRWResources, nSpawned, rPoolOut, vMainLinks, allIDsCopied, spawnID, loopRORW);
		}
		// must remove ap after use
		if (ap != null)
		{
			ap.deleteNode();
		}

		return nSpawned;
	}

	int singleLoop(final JDFSpawned spawnAudit, final VString vRWResources, int nSpawned, final JDFResourcePool rPoolOut, final HashSet<JDFElement> vMainLinks, final HashSet<String> allIDsCopied,
			final String spawnID, final int loopRORW)
	{
		// loop over all links
		for (final JDFElement liRoot : vMainLinks)
		{
			// test for direct children of resourcepool - these will be added later
			if (liRoot.getDeepParent(ElementName.RESOURCEPOOL, 0) != null)
			{
				continue;
			}

			final String refID = liRoot.getAttribute(AttributeName.RREF); // must be either refelem or link, therefore will have rref

			boolean bResRW = false;
			JDFResource rRoot = null;
			JDFResourceLink resLink = null;
			VJDFAttributeMap vLinkMap = null;
			if (liRoot instanceof JDFResourceLink)
			{
				resLink = (JDFResourceLink) liRoot;
				bResRW = linkFitsRWRes(resLink, vRWResources);
				vLinkMap = resLink.getPartMapVector();
			}
			else if (liRoot instanceof JDFRefElement)
			{
				rRoot = getNodeResource(refID);
				bResRW = resFitsRWRes(rRoot, vRWResources);
			}

			// do only RO on the first loop and only RW on the second
			if (bResRW != (loopRORW != 0))
			{
				continue;
			}

			// 091204 RP bug fix for multiple deep copies
			// don't copy if it is already there!
			final boolean isThereAlready = allIDsCopied.contains(refID);
			final JDFResource.EnumSpawnStatus copyStatus = bResRW ? JDFResource.EnumSpawnStatus.SpawnedRW : JDFResource.EnumSpawnStatus.SpawnedRO;

			final HashSet<String> vvRO = new LinkedHashSet<>();
			final HashSet<String> vvRW = new LinkedHashSet<>();
			if (rRoot == null)
			{
				rRoot = getNodeResource(refID);
			}

			// check for null and throw an exception in picky mode
			if (rRoot != null)
			{
				// copy any missing linked resources, just in case
				// the root is in the original jdf and can be used as a hook to the original document
				// get a list of all resources referenced by this link
				// always do a copyresource in case some dangling rRefs are waiting
				copySpawnedResource(rPoolOut, rRoot, copyStatus, spawnID, vRWResources, vvRW, vvRO, allIDsCopied, vLinkMap);
				nSpawned += vvRO.size() + vvRW.size();

				// get the effected resources
				VElement vRes = new VElement();
				VElement vResRoot = new VElement();
				if (resLink != null)
				{
					// make sure that spawned resources are sufficiently
					// partitioned if spawning rw so that no merge conflicts arise
					// create a temporary dummy copy of the link so that we have
					// a guaranteed copy that behaves the same as the original
					final JDFResourceLink dummy = (JDFResourceLink) rootOut.getCreateResourceLinkPool().copyElement(liRoot, null);
					fixResLinks(bResRW, resLink, dummy);
					// reduce partitions in main so that the links remain consistent
					VJDFAttributeMap mainMap = resLink.getPartMapVector();
					if (VJDFAttributeMap.isEmpty(mainMap))
						mainMap = vSpawnParts;
					else
						mainMap = mainMap.getOrMaps(vSpawnParts);
					resLink.setPartMapVector(mainMap);
					dummy.setPartMapVector(mainMap);

					vResRoot = ((JDFResourceLink) liRoot).getTargetVector(-1);

					vRes = dummy.getTargetVector(-1);
					if (vRes.isEmpty())
					{
						final JDFResource r0 = dummy.getTarget();
						if (r0 != null)
						{
							vRes.add(r0);
						}
					}
					dummy.deleteNode();
					reduceLinkPartAmounts(resLink, vLinkMap);
				}
				else if (liRoot instanceof JDFRefElement)
				{
					vResRoot.add(((JDFRefElement) liRoot).getTarget());

					// create a temporary dummy copy of the link so that we have
					// a guaranteed copy that behaves the same as the original
					final JDFRefElement dummy = (JDFRefElement) rootOut.copyElement(liRoot, null);
					vRes.add(dummy.getTarget());
					dummy.deleteNode();
				}
				else
				{
					log.error("we have a link that is neither ref nor link. Whazzup? " + liRoot == null ? " null" : liRoot.getNodeName());
					continue; // snafu - should never get here
				}
				addIdentical(vResRoot);
				addIdentical(vRes);

				// fixed not to crash with corrupt jdfs.
				// Now just continue and ignore the corrupt resource
				final int siz = vRes.size() < vResRoot.size() ? vRes.size() : vResRoot.size();
				// loop over all partitions
				boolean bRealyRW = vSpawnParts == null || vSpawnParts.size() == 0;
				if (!bRealyRW && siz > 0 && (bResRW || bSpawnROPartsOnly))
				{
					// reduce partitions of all RW resources and of RO resources if requested
					final JDFResource copyRoot = ((JDFResource) vRes.elementAt(0)).getResourceRoot();
					reducePartitions(copyRoot, bResRW);
				}
				else
				{
					isReduced.add(rRoot.getID());
				}

				for (int resParts = 0; resParts < siz; resParts++)
				{
					final JDFResource r = (JDFResource) vRes.elementAt(resParts);
					try
					{
						if (resParts == 0 && spawnID.equals(r.getAttribute(AttributeName.SPAWNID)))
							break;
					}
					catch (final Exception x)
					{
						log.error("snafu spawning " + liRoot, x);
					}
					final JDFResource rRoot1 = (JDFResource) vResRoot.elementAt(resParts);
					final PartSpawn partSpawner = new PartSpawn();
					if (!bInformative)
					{
						partSpawner.spawnPart(rRoot1, spawnID, copyStatus, true, bSpawnROPartsOnly, vLinkMap);
					}
					partSpawner.spawnPart(r, spawnID, copyStatus, false, bSpawnROPartsOnly, vLinkMap);

					if (resParts == 0 && vSpawnParts != null && vSpawnParts.size() != 0 && (bResRW || bSpawnROPartsOnly))
					{
						if (EnumSpawnStatus.SpawnedRW.equals(rRoot1.getSpawnStatus()))
						{
							bRealyRW = true;
						}
					}
				}

				if (!bRealyRW && EnumSpawnStatus.SpawnedRO.equals(copyStatus))
				{
					bResRW = false;
					if (!vvRO.contains(rRoot.getID()) && !vvRW.contains(rRoot.getID()))
					{
						vvRO.add(rRoot.getID());
					}
				}
				if (isThereAlready && bResRW)
				{
					vvRW.add(rRoot.getID());
				}
				calcAuditSpawnIDs(spawnAudit, vvRO, vvRW);
			}
		}
		return nSpawned;
	}

	protected void calcAuditSpawnIDs(final JDFSpawned spawnAudit, final HashSet<String> vvRO, final HashSet<String> vvRW)
	{
		VString rRefsRW = spawnAudit.getrRefsRWCopied();
		VString rRefsRO = spawnAudit.getrRefsROCopied();
		for (final String s : vvRW)
		{
			rRefsRW.add(s);
			final int ind = rRefsRO.index(s);
			if (ind >= 0)
			{
				rRefsRO.remove(ind);
			}
		}
		for (final String s : vvRO)
		{
			rRefsRO.add(s);
		}
		rRefsRO.unify();
		rRefsRW.unify();
		if (rRefsRO.isEmpty())
		{
			rRefsRO = null;
		}
		if (rRefsRW.isEmpty())
		{
			rRefsRW = null;
		}

		spawnAudit.setrRefsROCopied(rRefsRO);
		spawnAudit.setrRefsRWCopied(rRefsRW);
	}

	/**
	 * @return
	 */
	private HashSet<String> getAllIdsCopied()
	{
		// the node will be reused, whereas rootout is a new copy every time
		HashSet<String> allIDsCopied = mapAllRefs.get(node);
		if (allIDsCopied == null)
		{
			allIDsCopied = rootOut.fillHashSet("ID", null);
			mapAllRefs.put(node, allIDsCopied);
		}
		final HashSet<String> hs = new HashSet<>();
		hs.addAll(allIDsCopied);
		return hs;
	}

	/**
	 * cache resources by id for performance
	 *
	 * @param refID
	 * @return
	 */
	private JDFResource getNodeResource(final String refID)
	{
		KElement rRoot = mapResources.get(refID);
		if (rRoot == null)
		{
			rRoot = node.getJDFRoot().getTarget(refID, AttributeName.ID);
			mapResources.put(refID, rRoot);
		}
		if (!(rRoot instanceof JDFResource))
			rRoot = null;
		return (JDFResource) rRoot;
	}

	/**
	 * @param vRes
	 */
	private void addIdentical(final VElement vRes)
	{
		if (!bSpawnIdentical || ContainerUtil.isEmpty(vRes) || VJDFAttributeMap.isEmpty(vSpawnParts) || vRes.get(0) == null)
		{
			return;
		}

		final JDFResource jdfResource = (JDFResource) vRes.get(0);
		final JDFResource resRoot = jdfResource.getResourceRoot();
		final String id = resRoot.getID();
		if (noIdentical.contains(id))
			return;

		final List<JDFIdentical> identicals = resRoot.getChildArrayByClass(JDFIdentical.class, true, -1);
		if (ContainerUtil.isEmpty(identicals))
		{
			noIdentical.add(id);
			return;
		}
		else
		{
			hasIdentical.add(id);
		}

		for (final JDFIdentical ident : identicals)
		{
			final JDFResource identParent = (JDFResource) ident.getParentNode_KElement();
			final JDFAttributeMap identMap = identParent.getPartMap();
			for (final JDFAttributeMap mapPart : vSpawnParts)
			{
				if (mapPart.subMap(identMap))
				{
					vRes.add(identParent);
				}
			}
		}
	}

	/**
	 * fix linked resources so that all partitions in vSpawnParts actually exist
	 *
	 * @param bResRW
	 * @param liRootLink
	 * @param dummy
	 */
	private void fixResLinks(final boolean bResRW, final JDFResourceLink liRootLink, final JDFResourceLink dummy)
	{
		if (bFixResources && !VJDFAttributeMap.isEmpty(vSpawnParts) && bResRW)
		{
			final VString rootPartIDKeys = rootOut.getJDFRoot().getPartIDKeys(vSpawnParts.elementAt(0));
			final JDFResource linkRoot = liRootLink.getTarget();
			if (linkRoot != null)
			{
				try
				{
					linkRoot.createPartitions(vSpawnParts, rootPartIDKeys);
				}
				catch (final JDFException x)
				{
					for (int i = 0; i < vSpawnParts.size(); i++)
					{
						fixSpawnPartitions(linkRoot.getPartition(vSpawnParts.elementAt(i), null), rootPartIDKeys);
					}
				}
			}
			final JDFResource dummyRoot = dummy.getTarget();
			if (dummyRoot != null)
			{
				try
				{
					dummyRoot.createPartitions(vSpawnParts, rootPartIDKeys);
				}
				catch (final JDFException x)
				{
					for (int i = 0; i < vSpawnParts.size(); i++)
					{
						fixSpawnPartitions(dummyRoot.getPartition(vSpawnParts.elementAt(i), null), rootPartIDKeys);
					}
				}
			}
		}
	}

	// ///////////////////////////////////////////////////////////////////////

	private void finalizeSpawn(final JDFSpawned spawnAudit)
	{
		final VElement outLinks = prepareSpawnLinks(rootOut);
		final VElement mainLinks = prepareSpawnLinks(node);
		// add parts to resource links if necessary
		if (!VJDFAttributeMap.isEmpty(vSpawnParts))
		{
			finalizePartitions(spawnAudit, outLinks, mainLinks);
		}
		removeRO(outLinks, spawnAudit.getNewSpawnID(), false);
		removeRO(mainLinks, spawnAudit.getNewSpawnID(), true);
		finalizeStatusAndAudits(spawnAudit);
	}

	final static String ro = EnumSpawnStatus.SpawnedRO.getName();

	private void removeRO(final VElement outLinks, final String spawnID, final boolean isMain)
	{
		for (final KElement e : outLinks)
		{
			final JDFResourceLink rl = (JDFResourceLink) e;
			final JDFResource linkRoot = rl.getLinkRoot();
			if (linkRoot != null)
			{
				final VElement v = linkRoot.getLeaves(true);
				for (final KElement r : v)
				{
					removeROFromLeaf(spawnID, r);
				}
			}
		}
	}

	void removeROFromLeaf(final String spawnID, final KElement r)
	{
		if (ro.equals(r.getAttribute_KElement(AttributeName.SPAWNSTATUS)))
		{
			r.removeFromAttribute(AttributeName.SPAWNIDS, spawnID, null, null, 0);
			r.removeAttribute_KElement(AttributeName.SPAWNSTATUS, null);
		}
		if (r instanceof JDFElement)
		{
			removeROFromElement(spawnID, r);
		}
	}

	void removeROFromElement(final String spawnID, final KElement r)
	{
		final VElement refs = ((JDFElement) r).getRefElements();
		if (refs != null)
		{
			for (final KElement ref : refs)
			{
				final JDFResource targetRoot = ((JDFRefElement) ref).getTarget();
				final VElement vT = targetRoot == null ? null : targetRoot.getLeaves(true);
				if (vT != null)
				{
					for (final KElement target : vT)
					{
						if (ro.equals(target.getAttribute_KElement(AttributeName.SPAWNSTATUS)))
						{
							target.removeFromAttribute(AttributeName.SPAWNIDS, spawnID, null, null, 0);
							target.removeAttribute_KElement(AttributeName.SPAWNSTATUS, null);
						}
					}
				}
			}
		}
	}

	/**
	 * @param spawnAudit
	 * @param outLinks
	 * @param mainLinks
	 */
	private void finalizePartitions(final JDFSpawned spawnAudit, final VElement outLinks, final VElement mainLinks)
	{
		final int outLinkSize = outLinks.size();
		final String spawnID = spawnAudit.getNewSpawnID();
		for (int i = 0; i < outLinkSize; i++)
		{
			JDFResourceLink link = (JDFResourceLink) outLinks.elementAt(i);
			final JDFResource r = link.getTarget();

			// 2005-03-11 KM if the link is null continue, the JDF is invalid but in the
			// best case only an audit is missing and the JDF is still operable
			// in the worst caste the spawned JDF is not executable at all
			if (r != null)
			{
				final VJDFAttributeMap vPartMap = getSpawnLinkMap(vSpawnParts, r);
				final VJDFAttributeMap vNewMap = getSpawnedLinkPartMap(link, vPartMap);
				reduceLinkPartAmounts(link, vNewMap);
				updateSpawnIDs(spawnID, link);
				final String id = link.getrRef();
				link = (JDFResourceLink) mainLinks.elementAt(i);

				if (id.equals(link.getrRef()))
				{
					updateSpawnIDsInMain(spawnID, link, vPartMap);
				}
				else
				// the sequence of links changed - must search, hopefully we never get here
				{
					for (final KElement e : mainLinks)
					{
						link = (JDFResourceLink) e;
						if (id.equals(link.getrRef()))
						{
							updateSpawnIDsInMain(spawnID, link, vPartMap);
						}
					}
				}
			}
			else
			{
				log.warn("invalid link id=" + link.getrRef() + " Skipping " + rootOut.getJobID(true));
			}
		}
	}

	/**
	 * @param link
	 * @param vNewMap
	 */
	private void reduceLinkPartAmounts(final JDFResourceLink link, VJDFAttributeMap vNewMap)
	{
		if (!linkFitsRWRes(link, vRWResources_in) && !VString.isEmpty(vROSpawnParts) && !VJDFAttributeMap.isEmpty(vNewMap))
		{
			vNewMap = vNewMap.clone();
			final VString pik = getPartIDKeys(link.getLinkRoot(), false);
			vNewMap.reduceMap(pik);
		}
		link.setPartMapVector(vNewMap);
		final JDFAmountPool ap = link.getAmountPool();
		final VElement partAmounts = ap == null ? null : ap.getChildElementVector(ElementName.PARTAMOUNT, null);
		if (partAmounts != null)
		{
			for (final KElement e : partAmounts)
			{
				final JDFPartAmount pa = (JDFPartAmount) e;
				final VJDFAttributeMap paVector = pa.getPartMapVector();
				if (paVector != null && !paVector.overlapsMap(vNewMap))
				{
					pa.deleteNode();
				}
			}
		}
	}

	/**
	 * update only local resources
	 *
	 * @param spawnID
	 * @param link
	 * @param vPartMap
	 */
	void updateSpawnIDsInMain(final String spawnID, final JDFResourceLink link, final VJDFAttributeMap vPartMap)
	{
		JDFResource rMain = link.getTarget();
		if (rMain == null)
			rMain = link.getLinkRoot();
		if (rMain == null)
		{
			rMain = getNodeResource(link.getrRef());
			if (rMain == null)
			{
				log.error("cannot find main resource for: " + link.getNodeName() + " " + link.getrRef());
				return; // snafu return exit for corrupt links
			}
			else
			// clean up incorrectly positioned resource
			{
				final JDFNode parentNode = link.getParentJDF();
				parentNode.ensureValidRefsPosition(rMain);
			}
		}
		VElement vMainPart = rMain.getPartitionVector(vPartMap, null);
		if (ContainerUtil.isEmpty(vMainPart))
		{
			vMainPart = new VElement();
			vMainPart.add(rMain);
		}

		updateSpawnIDsinMainParts(spawnID, vPartMap, vMainPart);
	}

	void updateSpawnIDsinMainParts(final String spawnID, final VJDFAttributeMap vPartMap, final VElement vMainPart)
	{
		for (final KElement e : vMainPart)
		{
			final JDFResource rMainPart = (JDFResource) e;
			if (rMainPart == null)
			{
				continue;
			}

			final VElement leaves = rMainPart.getLeaves(true);
			boolean bSpawnID = false;

			// if any child node or leaf has this spawnID we need not do anything
			for (final KElement ee : leaves)
			{
				final JDFResource rMainLeaf = (JDFResource) ee;
				bSpawnID = rMainLeaf.includesMatchingAttribute(AttributeName.SPAWNIDS, spawnID, EnumAttributeType.NMTOKENS);
				if (bSpawnID)
				{
					break;
				}
			}
			bSpawnID = bSpawnID || !bSpawnIdentical && vPartMap != null && !vPartMap.subMap(rMainPart.getPartMap());

			if (!bSpawnID)
			{
				rMainPart.appendSpawnIDs(spawnID);
				rMainPart.setLocked(true);
				rMainPart.setSpawnStatus(EnumSpawnStatus.SpawnedRW);
			}
		}
	}

	/**
	 * @param link
	 * @param vPartMap
	 * @return
	 */
	private VJDFAttributeMap getSpawnedLinkPartMap(final JDFResourceLink link, final VJDFAttributeMap vPartMap)
	{
		final VJDFAttributeMap vLinkMap = link.getPartMapVector();
		VJDFAttributeMap vNewMap = new VJDFAttributeMap();

		if (VJDFAttributeMap.isEmpty(vLinkMap))
		{
			vNewMap = vPartMap;
		}
		else if (VJDFAttributeMap.isEmpty(vPartMap))
		{
			vNewMap = vLinkMap;
		}
		else
		{
			for (final JDFAttributeMap linkMap : vLinkMap)
			{
				if (setSpawnParts.contains(linkMap))
				{
					vNewMap.add(linkMap);
				}
				else
				{
					for (JDFAttributeMap m : vPartMap)
					{
						m = m.clone().orMap(linkMap);
						if (!m.isEmpty())
						{
							vNewMap.add(m);
						}
					}
				}
			}
			vNewMap.unify();
		}
		return vNewMap;
	}

	/**
	 * @param spawnID
	 * @param link
	 */
	private void updateSpawnIDs(final String spawnID, final JDFResourceLink link)
	{
		final VElement vRes = link.getTargetVector(-1);
		for (final KElement e : vRes)
		{
			final JDFResource res = (JDFResource) e;
			// only fix those local resources that haven't been fixed along the way...
			if (!res.includesMatchingAttribute(AttributeName.SPAWNIDS, spawnID, EnumAttributeType.NMTOKENS))
			{
				res.appendSpawnIDs(spawnID);
				res.setLocked(false);
			}
		}
	}

	/**
	 * @param spawnAudit
	 */
	private void finalizeStatusAndAudits(final JDFSpawned spawnAudit)
	{
		// add partition information to the audits and StatusPool or NodeInfo
		// 050906 RP move to the back so that it occurs after any global
		// resources have been copied
		spawnAudit.setPartMapVector(vSpawnParts);
		final EnumNodeStatus partStatus;
		if (vSpawnParts != null && !vSpawnParts.isEmpty())
		{
			partStatus = node.getPartStatus(vSpawnParts.elementAt(0), 0);
		}
		else
		// No partitioning - set Audit + Status globally
		{
			partStatus = node.getPartStatus(null, 0);
		}
		if (partStatus != null)
		{
			spawnAudit.setStatus(partStatus);
		}
		final VJDFAttributeMap vMap = getNIPartitions();
		node.setPartStatus(vMap, EnumNodeStatus.Spawned, null);
	}

	/**
	 *
	 * get the actual nodeinfo attribute map based on vmAttribute
	 *
	 * @return
	 */
	private VJDFAttributeMap getNIPartitions()
	{
		final VJDFAttributeMap vMap;
		if (vSpawnParts != null)
		{
			final JDFNodeInfo nodeInfo = node.getNodeInfo();
			final VElement vNI = nodeInfo == null ? null : nodeInfo.getPartitionVector(vSpawnParts, EnumPartUsage.Explicit);
			if (vNI != null)
			{
				vMap = new VJDFAttributeMap();
				for (final KElement e : vNI)
				{
					vMap.add(((JDFResource) e).getPartMap());
				}
			}
			else
			{
				vMap = vSpawnParts;
			}
		}
		else
		{
			vMap = vSpawnParts;
		}
		return vMap;
	}

	/**
	 * @param vLocalSpawnParts
	 * @param r
	 * @return
	 */
	private VJDFAttributeMap getSpawnLinkMap(final VJDFAttributeMap vLocalSpawnParts, final JDFResource r)
	{
		final VJDFAttributeMap vPartMap = new VJDFAttributeMap(vLocalSpawnParts);

		// 160802 RP leave implied resource link parts if PartUsage=implicit
		if (!JDFResource.EnumPartUsage.Implicit.equals(r.getPartUsage()))
		{
			final VString vPartKeys = r.getPartIDKeys();
			final Vector<EnumPartIDKey> vImplicitPartitions = r.getImplicitPartitions();
			if (vImplicitPartitions != null)
			{
				for (final JDFResource.EnumPartIDKey e : vImplicitPartitions)
				{
					vPartKeys.add(e.getName());
				}
			}
			vPartMap.reduceMap(vPartKeys.getSet());
		}
		return vPartMap;
	}

	/**
	 * calculate whether a link should be RW or RO
	 *
	 * @param li the link to check
	 * @param vRWResources the list of resource ids, process usages, usages, names. If any match, the referenced resource is considered rw
	 * @return boolean true if rw
	 */
	private boolean linkFitsRWRes(final JDFResourceLink li, final VString vRWResources)
	{
		if (vRWResources == null)
			return false;
		boolean bResRW = vRWResources.contains(li.getNamedProcessUsage());
		// 200602 RP added fix
		if (!bResRW)
		{
			bResRW = vRWResources.contains(li.getLinkedResourceName());
		}

		// 230802 RP added check for ID in vRWResources
		if (!bResRW)
		{
			bResRW = vRWResources.contains(li.getrRef());
		}

		// 040902 RP added check for Usage in vRWResources
		if (!bResRW)
		{
			bResRW = vRWResources.contains(li.getAttribute(AttributeName.USAGE));
		}
		return bResRW;
	}

	/**
	 * Reduces partition so that only the parts that overlap with vResources remain
	 *
	 * @param r
	 * @param nodeName
	 * @param nsURI
	 * @param partIDKeys
	 * @param partIDPos
	 * @param parentMap
	 * @param identical
	 * @return the reduced partitions
	 */
	private Set<KElement> reducePartitions(final JDFResource r, final String nodeName, final String nsURI, final VString partIDKeys, final int partIDPos, final JDFAttributeMap parentMap,
			final VElement identical)
	{
		final Set<KElement> bad = new HashSet<>();
		final VElement children = r.getChildElementVector_KElement(nodeName, nsURI, null, true, -1);
		if (children != null)
		{
			final int kidSize = children.size();
			for (int i = 0; i < kidSize; i++)
			{
				final JDFResource child = (JDFResource) children.elementAt(i);
				final String key = partIDKeys.get(partIDPos);
				if (key != null)
				{
					final String val = child.getAttribute_KElement(key, null, null);
					if (val != null)
					{
						final JDFAttributeMap testMap = new JDFAttributeMap(parentMap);
						testMap.put(key, val);
						if (overlapsPartMap(testMap))
						{
							final JDFIdentical id = child.getIdentical();
							if (id != null)
							{
								if (bSpawnIdentical)
								{
									addIdentical(r, identical, testMap);
								}
								else
								{
									bad.add(child);
								}
							}
							if (partIDPos + 1 < partIDKeys.size())
							{
								bad.addAll(reducePartitions(child, nodeName, nsURI, partIDKeys, partIDPos + 1, testMap, identical));
							}
							else
							{
								final List<JDFIdentical> v = child.getChildArrayByClass(JDFIdentical.class, true, 0);
								if (v != null && !v.isEmpty())
								{

									final VElement v2 = new VElement();
									for (int iii = 0; iii < v.size(); iii++)
									{
										final KElement parentNode_KElement = v.get(iii).getParentNode_KElement();
										if (parentNode_KElement != child)
										{
											v2.add(parentNode_KElement);
										}
									}
									v2.unify();
									for (int iii = 0; iii < v2.size(); iii++)
									{
										final JDFResource identParent = (JDFResource) v2.get(iii);
										if (bSpawnIdentical)
										{
											addIdentical(identParent, identical, identParent.getIdenticalMap());
										}
										else
										{
											identParent.deleteNode();
										}
									}
								}
							}
						}
						else
						// no overlap with testmap
						{
							if (bSpawnIdentical)
							{
								// only needed so that we can remove non-identical referenced leaves in case we
								// keep the parent
								bad.addAll(child.getLeaves(true));
							}
							else
							{
								bad.add(child);
							}
						}
					}
				}
			}

			// add parent of kids if all kids were zapped
			if (bad.size() >= children.size() && !bSpawnIdentical && !r.isResourceRoot() && bad.containsAll(children))
			{
				bad.add(r);
			}
		}
		return bad;
	}

	/**
	 * @param r
	 * @param identical
	 * @param testMap
	 */
	private void addIdentical(final JDFResource r, final VElement identical, final JDFAttributeMap testMap)
	{
		final JDFResource resourceRoot = r.getResourceRoot();
		JDFResource partition = resourceRoot.getPartition(testMap, null);
		while (partition != resourceRoot && partition != null)
		{
			identical.add(partition);
			partition = (JDFResource) partition.getParentNode_KElement();
		}
	}

	/**
	 * @param testMap
	 * @return
	 */
	private boolean overlapsPartMap(final JDFAttributeMap testMap)
	{
		if (vSpawnParts == null || testMap == null)
		{
			return true;
		}
		if (setSpawnParts.contains(testMap))
		{
			return true;
		}

		final VString keys = testMap.getKeys();
		final int ks = keys.size();

		for (int i = 0; i < vSpawnParts.size(); i++)
		{
			boolean bOK = true;
			final JDFAttributeMap map = vSpawnParts.elementAt(i);
			for (int j = 0; j < ks; j++)
			{
				final String key = keys.get(j);
				final String linkValue = map.get(key);
				if (linkValue != null && !JDFPart.matchesPart(key, testMap.get(key), linkValue, false))
				{
					bOK = false;
					break;
				}
			}
			if (bOK)
			{
				return true;
			}
		}
		return ks <= 0;
	}

	/**
	 *
	 *
	 * @param r
	 */
	private void reducePartitions(final JDFResource r, final boolean bRW)
	{
		if (r == null || vSpawnParts == null || vSpawnParts.size() == 0 || isReduced.contains(r.getID()))
		{
			return;
		}

		final VString partIDKeys = getPartIDKeys(r, bRW);
		if (VString.isEmpty(partIDKeys))
		{
			return;
		}
		final String id = r.getID();
		if (!bSpawnIdentical)
		{
			reduceFast(r, partIDKeys, 0, new JDFAttributeMap());
		}
		// in case we spawn lower than, only remove partitions that are parallel to our spawning
		int nMax = 999;

		final VElement vSubParts = r.getPartitionVector(vSpawnParts, EnumPartUsage.Implicit);
		for (int j = 0; j < vSubParts.size(); j++)
		{
			final JDFResource rr = (JDFResource) vSubParts.get(j);
			final JDFAttributeMap partMap = rr.getPartMap(partIDKeys);
			final int mapSize = partMap == null ? 0 : partMap.size();
			if (mapSize < nMax)
			{
				nMax = mapSize;
				if (nMax == 0)
				{
					return; // nothing left to do
				}
			}
		}
		for (int i = partIDKeys.size() - 1; i >= nMax; i--)
		{
			partIDKeys.remove(i);
		}

		final String nodeName = r.getLocalName();
		final String nsURI = r.getNamespaceURI();
		final VElement identical = new VElement();
		final Set<KElement> vBad = reducePartitions(r, nodeName, nsURI, partIDKeys, 0, new JDFAttributeMap(), identical);
		if (identical.size() > 0)
		{
			identical.unify();
			vBad.removeAll(identical);
		}
		for (final KElement bad : vBad)
		{
			bad.deleteNode();
		}
		isReduced.add(id);
	}

	/**
	 *
	 *
	 * @param r
	 * @param partIDKeys
	 * @param depth
	 * @param currentMap
	 */
	private void reduceFast(final JDFResource r, final VString partIDKeys, final int depth, final JDFAttributeMap currentMap)
	{
		final String key0 = partIDKeys.get(depth);
		final int size = partIDKeys.size();
		// Set<String> keys = EnumPartIDKey.PartVersion.getName().equals(key0) ? null :
		// ContainerUtil.toHashSet(vSpawnParts.getPartValues(key0, true));
		final List<? extends KElement> firstLevel = r.getDirectPartitionArray();
		if (firstLevel != null)
		{
			for (final KElement e : firstLevel)
			{
				final JDFResource rFirstLevel = (JDFResource) e;
				final String partValue = rFirstLevel.getAttribute_KElement(key0, null, null);
				currentMap.put(key0, partValue);
				if (!JDFPart.overlapPartMap(currentMap, vSpawnParts, false))
				{
					e.deleteNode();
				}
				else
				{
					if (depth + 1 < size)
					{
						reduceFast(rFirstLevel, partIDKeys, depth + 1, currentMap);
					}
				}
			}
			currentMap.remove(key0);
		}
	}

	/**
	 * calculate whether a link should be RW or RO
	 *
	 * @param r the resource to check
	 * @param vRWResources the list of resource ids, process usages, usages, names. If any match, the referenced resource is considered rw
	 * @return boolean true if rw
	 */
	private boolean resFitsRWRes(final JDFResource r, final VString vRWResources)
	{
		if (r == null)
		{
			return false;
		}
		boolean bResRW = vRWResources.contains(r.getLocalName());
		// 200602 RP added fix
		if (!bResRW)
		{
			bResRW = vRWResources.contains(JDFConstants.STAR);
		}

		// 230802 RP added check for ID in vRWResources
		if (!bResRW)
		{
			bResRW = vRWResources.contains(r.getID());
		}

		return bResRW;
	}

	/**
	 * copies a resource recursively and optionally fixes status flags and locks in the source resource
	 *
	 * @param targetResPool the pool to copy r into
	 * @param r the resource to copy
	 * @param copyStatus rw or ro
	 * @param spawnID the spawnID of the spawning that initiated the copy
	 * @param vRWResources write resources
	 * @param vRWIDs
	 * @param vROIDs
	 * @param allIDsCopied
	 *
	 */
	private void copySpawnedResource(final JDFResourcePool targetResPool, final JDFResource r, JDFResource.EnumSpawnStatus copyStatus, final String spawnID, final VString vRWResources,
			final HashSet<String> vRWIDs, final HashSet<String> vROIDs, final HashSet<String> allIDsCopied, final VJDFAttributeMap vLinkMap)
	{
		if (r == null)
		{
			log.error("attempting to copy null resource - bailing out");
			return;
		}

		// r is not yet here copy r
		boolean bRW = copyStatus == JDFResource.EnumSpawnStatus.SpawnedRW;
		final String rID = r.getID();
		if (!allIDsCopied.contains(rID))
		{
			final JDFResource rNew = copyPart(targetResPool, r, bRW);

			// if spawning, fix stati and locks
			final PartSpawn partSpawner = new PartSpawn();
			if (!bInformative) // in case of informative, we kill main anyhow - no use modifying it
			{
				copyStatus = partSpawner.spawnPart(r, spawnID, copyStatus, true, bSpawnROPartsOnly, vLinkMap);
			}
			copyStatus = partSpawner.spawnPart(rNew, spawnID, copyStatus, false, bSpawnROPartsOnly, vLinkMap);
			bRW = copyStatus == JDFResource.EnumSpawnStatus.SpawnedRW;
			if (bRW)
			{
				vRWIDs.add(rID);
			}
			else
			{
				vROIDs.add(rID);
			}

			allIDsCopied.add(rID);
		}

		VString vs = mapRefs.get(r);
		if (vs == null)
		{
			vs = r.getHRefs(new VString(), false, false);
			mapRefs.put(r, vs);
		}
		else
		{
			// System.out.println("reuse" + r);
		}
		// add recursively copied resource references
		for (final String id : vs)
		{
			// the referenced resource is already in this pool - continue
			if (!allIDsCopied.contains(id))
			{
				// 071101 RP added r is by definition in the original document
				// which also contains the rrefs elements
				final JDFResource next = getNodeResource(id);

				// and now all those interlinked resources
				if (next != null)
				{
					// only copy refelements rw if they are explicitly in the list
					if (bRW)
					{
						copyStatus = resFitsRWRes(next, vRWResources) ? JDFResource.EnumSpawnStatus.SpawnedRW : JDFResource.EnumSpawnStatus.SpawnedRO;
					}
					else if (resFitsRWRes(next, vRWResources))
					{
						continue; // we are spawning ro and the referenced resource is rw
					}

					// recurse into refelements
					copySpawnedResource(targetResPool, next, copyStatus, spawnID, vRWResources, vRWIDs, vROIDs, allIDsCopied, vLinkMap);
				}
			}
		}

		return;
	}

	private JDFResource copyPart(final JDFResourcePool targetResPool, final JDFResource r, final boolean bRW)
	{
		final JDFResource rNew;
		final VString partIDKeys = getPartIDKeys(r, bRW);
		if ((bRW || bSpawnROPartsOnly) && !VString.isEmpty(partIDKeys))
		{
			final String resName = r.getNodeName();
			rNew = (JDFResource) targetResPool.appendElement(resName);
			rNew.setAttributesRaw(r);
			final VJDFAttributeMap linkedIdentical = bSpawnIdentical ? new VJDFAttributeMap() : null;
			copyPartImpl(r, rNew, partIDKeys, 0, new JDFAttributeMap(), linkedIdentical, resName);
			if (!VJDFAttributeMap.isEmpty(linkedIdentical))
			{
				linkedIdentical.unify();
				final VString idParts = r.getPartIDKeys();
				for (final JDFAttributeMap idMap : linkedIdentical)
				{
					copySingle(rNew, r, idMap, idParts, 0);
				}
			}
		}
		else
		{
			rNew = (JDFResource) targetResPool.copyElement(r, null);
		}
		isReduced.add(r.getID());
		return rNew;
	}

	VString getPartIDKeys(final JDFResource r, final boolean bRW)
	{
		final VString partIDKeys = r == null ? null : r.getPartIDKeys();
		if (!bRW && partIDKeys != null && !VString.isEmpty(vROSpawnParts))
		{
			final int lastPart = partIDKeys.size();
			for (int i = 0; i < lastPart; i++)
			{
				if (!vROSpawnParts.contains(partIDKeys.get(i)))
				{
					for (int j = i; j < lastPart; j++)
					{
						partIDKeys.remove(i);
					}
					break;
				}
			}
		}
		return partIDKeys;
	}

	/**
	 *
	 * @param rNew
	 * @param r
	 * @param idMap
	 * @param partIDKeys
	 * @param depth
	 */
	private void copySingle(final JDFResource rNew, final JDFResource r, final JDFAttributeMap idMap, final VString partIDKeys, final int depth)
	{
		final int size = partIDKeys == null ? 0 : partIDKeys.size();
		if (depth < size)
		{
			final String key0 = partIDKeys.get(depth);
			final String val = idMap.get(key0);
			final String nodeName = r.getNodeName();
			final JDFResource rChild = (JDFResource) r.getChildWithAttribute(nodeName, key0, null, val, 0, true);
			if (rChild != null)
			{
				JDFResource rNewChild = (JDFResource) rNew.getChildWithAttribute(nodeName, key0, null, val, 0, true);
				if (rNewChild == null)
				{
					rNewChild = (JDFResource) rNew.appendElement(nodeName);
					rNewChild.setAttributesRaw(rChild);
					KElement child = rChild.getFirstChildElement();
					while (child != null)
					{
						if (!nodeName.equals(child.getNodeName()))
						{
							rNewChild.copyElement(child, null);
						}
						child = child.getNextSiblingElement();
					}
				}
				copySingle(rNewChild, rChild, idMap, partIDKeys, depth + 1);
			}
		}
	}

	private void copyPartImpl(final JDFResource r, final JDFResource target, final VString partIDKeys, final int depth, final JDFAttributeMap currentMap, final VJDFAttributeMap linkedIdentical,
			final String resName)
	{
		final String key0 = partIDKeys.get(depth);
		final int size = partIDKeys.size();
		KElement child = r.getFirstChildElement();
		while (child != null)
		{
			if (resName.equals(child.getNodeName()))
			{
				final String partValue = child.getAttribute_KElement(key0, null, null);
				currentMap.put(key0, partValue);
				if (JDFPart.overlapPartMap(currentMap, vSpawnParts, false))
				{
					if (depth + 1 < size)
					{
						final KElement newTarget = target.appendElement(resName);
						newTarget.setAttributesRaw(child);
						copyPartImpl((JDFResource) child, (JDFResource) newTarget, partIDKeys, depth + 1, currentMap, linkedIdentical, resName);
					}
					else
					{
						final VJDFAttributeMap idMap = getIdenticals((JDFResource) child);
						if (linkedIdentical != null)
						{
							target.copyElement(child, null);
							if (idMap != null)
							{
								linkedIdentical.addAll(idMap);
							}
						}
						else if (child.getElement(ElementName.IDENTICAL) == null) // we don't want identicals but have one - don't copy
						{
							final JDFResource copied = (JDFResource) target.copyElement(child, null);
							if (idMap != null)
							{
								final List<JDFResource> cLeaves = copied.getLeafArray(false);
								for (final JDFResource leaf : cLeaves)
								{
									if (leaf.hasChildElement(ElementName.IDENTICAL, null))
									{
										leaf.deleteNode();
									}
								}
							}
						}
					}
				}
			}
			else
			{
				target.copyElement(child, null);
			}
			child = child.getNextSiblingElement();
		}
		currentMap.remove(key0);
	}

	VJDFAttributeMap getIdenticals(final JDFResource child)
	{
		final VJDFAttributeMap ret = new VJDFAttributeMap();
		final List<JDFResource> leaves = child.getLeafArray(false);
		for (final JDFResource leaf : leaves)
		{
			final JDFAttributeMap single = leaf.getIdenticalMap();
			if (single != null)
			{
				ret.add(single);
			}
		}
		ret.unify();
		return VJDFAttributeMap.isEmpty(ret) ? null : ret;
	}

	private class PartSpawn
	{
		/**
		 *
		 */
		private PartSpawn()
		{
			super();
		}

		/**
		 * @param r
		 * @param spawnID
		 * @param copyStatus
		 * @param bStayinMain
		 * @param vLinkMap
		 * @return
		 */
		EnumSpawnStatus spawnPart(final JDFResource r, final String spawnID, JDFResource.EnumSpawnStatus copyStatus, final boolean bStayinMain, final boolean partsRO, final VJDFAttributeMap vLinkMap)
		{
			final boolean isRW = EnumSpawnStatus.SpawnedRW.equals(copyStatus);
			if (!VJDFAttributeMap.isEmpty(vSpawnParts) && (JDFResource.EnumSpawnStatus.SpawnedRW.equals(copyStatus) || partsRO))
			{
				copyStatus = spawnPartitionedPart(r, spawnID, copyStatus, bStayinMain, vLinkMap, isRW);
			}
			else
			// no partitions
			{
				spawnComplete(r, spawnID, copyStatus, bStayinMain, isRW);

			}
			return copyStatus;
		}

		void spawnComplete(final JDFResource r, final String spawnID, final JDFResource.EnumSpawnStatus copyStatus, final boolean bStayinMain, final boolean isRW)
		{
			if (bStayinMain)
			{
				if (isRW || (!EnumSpawnStatus.SpawnedRW.equals(r.getSpawnStatus())))
				{
					r.setSpawnStatus(copyStatus);
					r.setLocked(isRW);
				}
				r.appendSpawnIDs(spawnID);
			}
			else
			{
				r.setLocked(!isRW);
				r.setSpawnIDs(new VString(spawnID, null));
			}
		}

		JDFResource.EnumSpawnStatus spawnPartitionedPart(final JDFResource r, final String spawnID, final JDFResource.EnumSpawnStatus copyStatus, final boolean bStayinMain,
				final VJDFAttributeMap vLinkMap, final boolean isRW)
		{
			final JDFAttributeMap partMap = r.getPartMap();
			final VElement vSubParts = getSubParts(r, partMap, vLinkMap);

			if (ContainerUtil.isEmpty(vSubParts))
			{
				return spawnPart(r, spawnID, JDFResource.EnumSpawnStatus.SpawnedRO, bStayinMain, false, vLinkMap);
			}
			for (final KElement e : vSubParts)
			{
				final JDFResource pLeaf = (JDFResource) e;
				// set the lock of the leaf to true if it is RO, else unlock it
				if (bStayinMain)
				{
					if (isRW || !EnumSpawnStatus.SpawnedRW.equals(pLeaf.getSpawnStatus()))
					{
						pLeaf.setSpawnStatus(copyStatus);
						pLeaf.setLocked(isRW);
					}
					pLeaf.appendSpawnIDs(spawnID);
				}
				else
				{
					pLeaf.setLocked(!isRW);
					pLeaf.setSpawnIDs(spawnID);
				}
			}
			return copyStatus;
		}

		/**
		 *
		 *
		 * @param r
		 * @param partMap
		 * @param vLinkMap
		 * @return
		 */
		private VElement getSubParts(final JDFResource r, final JDFAttributeMap partMap, final VJDFAttributeMap vLinkMap)
		{
			VElement vSubParts;
			if (setSpawnParts.contains(partMap))
			{
				vSubParts = new VElement();
				vSubParts.add(r);
			}
			else
			{
				// always implicit - in the worst case some partitions may be multiply spawned
				// vSubParts = r.getPartitionVector(vSpawnParts, EnumPartUsage.Implicit);

				// 100208 spawn only as requested - NOT explicit, don't automatically create anything
				final PartitionGetter partitionGetter = new PartitionGetter(r.getResourceRoot());
				partitionGetter.setFollowIdentical(bSpawnIdentical);
				vSubParts = partitionGetter.getPartitionVector(vSpawnParts, null);
				if (bSpawnIdentical && (vSubParts == null || vSubParts.size() == 0) && !EnumPartUsage.Implicit.equals(r.getPartUsage()))
				{
					final VJDFAttributeMap other;
					if (!VJDFAttributeMap.isEmpty(vLinkMap))
					{
						other = new VJDFAttributeMap(vSpawnParts);
						other.overlapMap(vLinkMap);
					}
					else
					{
						other = vSpawnParts;
					}
					vSubParts = partitionGetter.getPartitionVector(other, EnumPartUsage.Implicit);
				}
			}
			return vSubParts;
		}
	}

	private void fixSpawnPartitions(final JDFResource r, final VString rootPartIDKeys)
	{
		if (r == null)
		{
			return;
		}
		final VString oldParts = r.getPartIDKeys();
		if (oldParts.containsAny(rootPartIDKeys))
		{
			throw new JDFException("fixSpawnPartitions - adding incompatible resources");
		}

		// move away all pre-existing partitions and dump them into the new nodes
		final Collection<KElement> ve = r.getChildArray_KElement(r.getNodeName(), r.getNamespaceURI(), null, true, 9999999);
		final KElement[] tmp = new KElement[ve.size()];
		int n = 0;
		for (final KElement e : ve)
		{
			tmp[n++] = e.deleteNode();
		}
		r.removeAttribute(AttributeName.PARTIDKEYS);
		final VElement vNew = r.getResourceRoot().createPartitions(vSpawnParts, rootPartIDKeys);

		// copy initial leaves into the newly created stuff
		for (int i = 0; i < vNew.size(); i++)
		{
			for (int j = 0; j < tmp.length; j++)
			{
				vNew.item(i).copyElement(tmp[j], null);
			}
		}

		// fix partidkeys
		final VString partIDKeys = r.getPartIDKeys();
		partIDKeys.appendUnique(oldParts);
		r.setPartIDKeys(partIDKeys);
	}

	// ///////////////////////////////////////////////////////////////////////

	/**
	 * spawn a node in informative mode without modifying the root JDF; url is the file name of the new node, the parameters except for the list of rw resources, which are by definition empty, are
	 * identical to those of Spawn
	 *
	 * vRWResourceUsage is the vector of Resources Usages, Resource Names or Resource IDs that are spawned RW, all others are spawned read only; vParts is the vector of part maps that are to be
	 * spawned, defaults to no part, i.e. the whole thing
	 *
	 * @return JDFDoc: The spawned node's owner document.
	 *
	 *
	 */
	public JDFNode spawnInformative()
	{
		bInformative = true;
		// only create a copy once
		if (informativeRoot == null)
		{
			final JDFDoc docNew = new JDFDoc(ElementName.JDF);
			informativeRoot = (JDFNode) docNew.getRoot();
			final JDFNode thisRoot = node.getJDFRoot();
			// merge this node into it
			docNew.setInitOnCreate(false);
			informativeRoot.copyInto(thisRoot, true);
			docNew.setInitOnCreate(true);
		}
		final JDFNode copyOfThis = informativeRoot.getChildJDFNode(node.getID(), false);
		final JDFNode tmp = node;
		node = copyOfThis;
		if (tmp != node)
		{
			mapResources.clear();
		}
		final VString vRWTmp = vRWResources_in;
		final boolean spawnMultKeep = bSpawnRWPartsMultiple;
		JDFNode nodeNew = null;
		try
		{
			bSpawnRWPartsMultiple = true; // never check multiple resources when spawning informative - who cares
			nodeNew = spawn();
			nodeNew.setActivation(EnumActivation.Informative);
		}
		finally
		{
			node = tmp;
			bSpawnRWPartsMultiple = spawnMultKeep;
			vRWResources_in = vRWTmp;
		}
		return nodeNew;
	}

	/**
	 * spawn a node; url is the file name of the new node, vRWResourceUsage is the vector of Resources Usages (or Names if no usage exists for the process) that are spawned RW, all others are spawned
	 * read only; vParts is the vector of part maps that are to be spawned, defaults to no part, i.e. the whole thing
	 *
	 * @param _parentURL
	 * @param _spawnURL : URL of the spawned JDF file
	 * @param _vRWResources_in : vector of resource names and Usage / ProcessUsage that are spawned as rw <br>
	 *            the format is one of:<br>
	 *            ResName:Input<br>
	 *            ResName:Output<br>
	 *            ResName:ProcessUsage<br>
	 * @param _vSpawnParts vector of mAttributes that describe the parts to spawn, only valid PartIDKeys are allowed
	 * @param _bSpawnROPartsOnly if true, only the parts of RO resources that are specified in vParts are spawned, else the complete resource is spawned
	 * @param _bCopyNodeInfo copy the NodeInfo elements into the Ancestors
	 * @param _bCopyCustomerInfo copy the CustomerInfo elements into the Ancestors
	 * @param _bCopyComments copy the Comment elements into the Ancestors
	 *
	 * @return The spawned node
	 * @since 050831 added bCopyComments @ tbd enhance nested spawning of partitioned nodes default: spawn(parentURL, null, null, null, false, false, false, false)
	 */
	public JDFNode spawn(final String _parentURL, final String _spawnURL, final VString _vRWResources_in, final VJDFAttributeMap _vSpawnParts, final boolean _bSpawnROPartsOnly,
			final boolean _bCopyNodeInfo, final boolean _bCopyCustomerInfo, final boolean _bCopyComments)
	{
		bCopyComments = _bCopyComments;
		bCopyCustomerInfo = _bCopyCustomerInfo;
		bCopyNodeInfo = _bCopyNodeInfo;
		bSpawnROPartsOnly = _bSpawnROPartsOnly;
		vSpawnParts = _vSpawnParts;
		vRWResources_in = new VString(_vRWResources_in);
		spawnURL = _spawnURL;
		parentURL = _parentURL;
		bInformative = false;
		return spawn();
	}

	// ///////////////////////////////////////////////////////////////////////

	/**
	 * spawn a node in informative mode without modifying the root JDF; url is the file name of the new node, the parameters except for the list of rw resources, which are by definition empty, are
	 * identical to those of Spawn
	 *
	 * vRWResourceUsage is the vector of Resources Usages, Resource Names or Resource IDs that are spawned RW, all others are spawned read only; vParts is the vector of part maps that are to be
	 * spawned, defaults to no part, i.e. the whole thing
	 *
	 * @param _parentURL
	 *
	 * @param _spawnURL : URL of the spawned JDF file
	 * @param _vSpawnParts : vector of mAttributes that describe the parts to spawn
	 * @param _bSpawnROPartsOnly if true, only the parts of RO resources that are specified in vParts are spawned, else the complete resource is spawned
	 * @param _bCopyNodeInfo copy the NodeInfo elements into the Ancestors
	 * @param _bCopyCustomerInfo copy the CustomerInfo elements into the Ancestors
	 * @param _bCopyComments copy the Comment elements into the Ancestors
	 * @return JDFDoc: The spawned node's owner document.
	 *
	 * @default spawnInformative(parentURL, null, null, false, false, false, false);
	 *
	 */
	public JDFNode spawnInformative(final String _parentURL, final String _spawnURL, final VJDFAttributeMap _vSpawnParts, final boolean _bSpawnROPartsOnly, final boolean _bCopyNodeInfo,
			final boolean _bCopyCustomerInfo, final boolean _bCopyComments)
	{
		bCopyComments = _bCopyComments;
		bCopyCustomerInfo = _bCopyCustomerInfo;
		bCopyNodeInfo = _bCopyNodeInfo;
		bSpawnROPartsOnly = _bSpawnROPartsOnly;
		vSpawnParts = _vSpawnParts;
		vRWResources_in = null;
		spawnURL = _spawnURL;
		parentURL = _parentURL;
		return spawnInformative();
	}

	// /////////////////////////////////////////////////////////////////

	/**
	 * Method unSpawn. undo a spawn, removing any and all bookkeeping of that spawning
	 *
	 * @param spawnID spawnID of the spawn to undo, if null find the first spawned node
	 * @return the fixed unspawned node
	 */
	public JDFNode unSpawn(String spawnID)
	{
		log.info("Unspawning node ID=" + spawnID);
		final JDFNode nodeParent = findUnSpawnNode(node, spawnID);
		if (nodeParent != null && spawnID == null)
		{
			final JDFSpawned spawnAudit = (JDFSpawned) nodeParent.getAuditPool().getAudit(0, EnumAuditType.Spawned, null, null);
			if (spawnAudit != null)
			{
				spawnID = spawnAudit.getNewSpawnID();
				log.info("calculating spawnID from parent: SpawnID=" + spawnID);
			}
		}
		return new Unspawn(nodeParent, spawnID).unSpawnNode();
	}

	/**
	 * @param myNode
	 * @param spawnID
	 * @return
	 */
	private JDFNode findUnSpawnNode(final JDFNode myNode, final String spawnID)
	{
		final VElement vJDFNodes = myNode.getvJDFNode(null, null, false);
		final JDFAttributeMap mapSpawn = new JDFAttributeMap(AttributeName.NEWSPAWNID, spawnID);

		int i = 0;
		final int size = vJDFNodes.size();
		// loop over all
		for (i = 0; i < size; i++)
		{
			final JDFNode nodeParent = (JDFNode) vJDFNodes.elementAt(i);

			final JDFAuditPool auditPool = nodeParent.getAuditPool();
			if (auditPool != null)
			{
				final JDFAudit spawnAudit = auditPool.getAudit(0, JDFAudit.EnumAuditType.Spawned, mapSpawn, null);
				// we have a matching spawned audit -> n is the parent node that spawned spawnID
				// let n fix the rest!
				if (spawnAudit != null)
				{
					// loop over all
					// look into the audit pool and search something, which was merged
					final JDFAttributeMap mapMerge = new JDFAttributeMap(JDFConstants.MERGEID, spawnID);

					final JDFAudit mergedAudit = auditPool.getAudit(0, JDFAudit.EnumAuditType.Merged, mapMerge, null);

					if (mergedAudit == null)
					{
						return nodeParent;
					}
				}
			}
		}
		final JDFNode parent = myNode.getParentJDF();
		return parent == null ? null : findUnSpawnNode(parent, spawnID);
	}

	private class Unspawn
	{
		private final String strSpawnID;
		private final JDFNode parent;

		public Unspawn(final JDFNode parent, final String strSpawnID)
		{
			this.parent = parent;
			this.strSpawnID = strSpawnID;
		}

		/**
		 * unSpawnNode - undo a spawn of a node hier muss noch nachportiert werden - es gibt jetzt in JDFRoot eine Methode gleichen Namens, bei der man komfortabler das undo aufrufen kann. die Methode
		 * hier in JDFNode wird dann umbenannt (protected) und aus JDFRoot heraus aufgerufen.
		 *
		 *
		 *
		 *
		 * @return the fixed unspawned node
		 */
		JDFNode unSpawnNode()
		{
			if (parent == null)
			{
				log.warn("No parent to unspawn, bailing out");
				return null;
			}
			JDFSpawned spawnAudit = null;

			JDFNode localNode = null;
			if (StringUtil.getNonEmpty(strSpawnID) != null)
			{
				spawnAudit = findSpawnAudit();

			}

			if (spawnAudit != null)
			{
				final VJDFAttributeMap parts = unspawnRO(spawnAudit);
				unspawnRW(spawnAudit);
				localNode = (JDFNode) parent.getTarget(spawnAudit.getjRef(), AttributeName.ID);
				unspawnLocal(localNode);
				updateStatus(spawnAudit, localNode, parts);
				spawnAudit.deleteNode();
			}

			return localNode;
		}

		private JDFSpawned findSpawnAudit()
		{
			final JDFAuditPool auditPool = parent.getAuditPool();
			if (auditPool != null)
			{
				// look into the audit pool and search something, which was spawned

				final JDFAttributeMap mapSpawn = new JDFAttributeMap(JDFConstants.NEWSPAWNID, strSpawnID);
				JDFSpawned spawnAudit;
				spawnAudit = (JDFSpawned) auditPool.getAudit(0, JDFAudit.EnumAuditType.Spawned, mapSpawn, null);
				if (spawnAudit == null)
				{
					log.warn("No parent audit to unspawn, bailing out");
				}
				return spawnAudit;
			}
			return null;
		}

		private void unspawnRW(final JDFSpawned spawnAudit)
		{
			// merge rw resources
			final VString vRWCopied = spawnAudit.getrRefsRWCopied();
			if (vRWCopied != null)
			{
				for (final String rwCopied : vRWCopied)
				{
					final JDFResource oldRes = (JDFResource) parent.getTarget(rwCopied, AttributeName.ID);
					if (oldRes != null)
					{
						oldRes.unSpawnPart(strSpawnID, JDFResource.EnumSpawnStatus.SpawnedRW);
					}
				}
			}
		}

		private void updateStatus(final JDFSpawned spawnAudit, final JDFNode localNode, final VJDFAttributeMap parts)
		{
			EnumNodeStatus status = JDFElement.EnumNodeStatus.Waiting;
			final boolean fHasAuditStatus = spawnAudit.hasAttribute(AttributeName.STATUS);
			if (fHasAuditStatus)
			{
				status = spawnAudit.getStatus();
			}

			if (parts != null)
			{
				final EnumNodeStatus parentStatus = parent.getStatus();
				if (JDFElement.EnumNodeStatus.Pool.equals(parentStatus) || JDFElement.EnumNodeStatus.Part.equals(parentStatus))
				{
					for (final JDFAttributeMap part : parts)
					{
						if (fHasAuditStatus || JDFElement.EnumNodeStatus.Spawned.equals(parent.getPartStatus(part, 0)))
						{
							parent.setPartStatus(part, status, null);
						}
					}
				}
				else if (JDFElement.EnumNodeStatus.Spawned.equals(parentStatus) || spawnAudit.hasAttribute(AttributeName.STATUS))
				{
					parent.setStatus(status);
				}
			}
			else if (localNode != null)
			{
				// we either must overwrite because it is now definitely not spawned
				// or had an explicit correct status in the spawned audit
				if (JDFElement.EnumNodeStatus.Spawned.equals(localNode.getStatus()) || spawnAudit.hasAttribute(AttributeName.STATUS))
				{
					localNode.setStatus(status);
				}
			}
		}

		private void unspawnLocal(final JDFNode localNode)
		{
			final VElement vn = localNode == null ? null : localNode.getvJDFNode(null, null, false);
			// in C++ Vector is VJDFNode, which is typesafe

			// loop over all child nodes of the spawned node to be unspawned
			if (vn != null)
			{
				for (int nod = 0; nod < vn.size(); nod++)
				{
					final JDFNode deepNode = (JDFNode) vn.elementAt(nod);
					final JDFResourcePool resPool = deepNode.getResourcePool();

					if (resPool != null)
					{
						final VElement vRes = resPool.getPoolChildren(null, null, null);

						for (int i = 0; i < vRes.size(); i++)
						{
							final JDFResource res1 = (JDFResource) vRes.elementAt(i);
							res1.unSpawnPart(strSpawnID, JDFResource.EnumSpawnStatus.SpawnedRW);
						}
					}
				}
			}
		}

		private VJDFAttributeMap unspawnRO(final JDFSpawned spawnAudit)
		{
			// get parts from audit
			final VJDFAttributeMap parts = spawnAudit.getPartMapVector();
			final VString vROCopied = spawnAudit.getrRefsROCopied();

			if (vROCopied != null)
			{
				for (final String roCopied : vROCopied)
				{
					final JDFResource oldRes = (JDFResource) parent.getTarget(roCopied, AttributeName.ID);
					if (oldRes != null)
					{
						oldRes.unSpawnPart(strSpawnID, JDFResource.EnumSpawnStatus.SpawnedRO);
					}
				}
			}
			return parts;
		}
	}

	/**
	 * @see java.lang.Object#toString()
	 * @return
	 */
	@Override
	public String toString()
	{
		final String jpID = node == null ? " - " : node.getJobPartID(false);
		return "[JDFSpawn JobPartID=" + jpID + "\nParts: " + vSpawnParts + "\nRW: " + vRWResources_in;
	}

	/**
	 * remove all spawn related stuff from the spawned childNode
	 *
	 * @param childNode
	 */
	public void unSpawnChild(final JDFNode childNode)
	{
		childNode.removeChild(ElementName.ANCESTORPOOL, null, 0);

		final JDFAttributeMap spawnStuff = new JDFAttributeMap();
		spawnStuff.put(AttributeName.SPAWNID, (String) null);
		spawnStuff.put(AttributeName.SPAWNIDS, (String) null);
		spawnStuff.put(AttributeName.SPAWNSTATUS, (String) null);
		spawnStuff.put(AttributeName.LOCKED, (String) null);
		new AttributeReplacer(spawnStuff, null).replace(childNode);
	}

}
