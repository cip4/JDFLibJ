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

import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedHashSet;
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
	 * if true, copy node info
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

	private Set<JDFAttributeMap> setSpawnParts = null;
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
		mapRefs = new HashMap<JDFResource, VString>();
		mapAllRefs = new HashMap<JDFNode, HashSet<String>>();
		mapResources = new HashMap<String, KElement>();
		noIdentical = new HashSet<String>();
		idCacheFilled = false;
	}

	/**
	 * set the node to spawn
	 * @param newNode the node to set
	 * @throws JDFException if node is NOT in the same document as the initial node
	 */
	public void setNode(JDFNode newNode)
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
	 * spawn a node; url is the file name of the new node, vRWResourceUsage is the vector of Resources Usages (or Names if no usage exists for the process) that
	 * are spawned RW, all others are spawned read only; vParts is the vector of part maps that are to be spawned, defaults to no part, i.e. the whole thing
	 * 
	 * the format is one of:<br>
	 * ResName:Input<br>
	 * ResName:Output<br>
	 * ResName:ProcessUsage<br>
	 * @return The spawned node
	 * @since 050831 added bCopyComments @ tbd enhance nested spawning of partitioned nodes
	 * 
	 * default: spawn(parentURL, null, null, null, false, false, false, false)
	 */
	public JDFNode spawn()
	{
		// need copy in order to fix up 1.3 NodeInfo spawn
		final VString vRWResources = vRWResources_in == null ? new VString() : vRWResources_in;

		if (!bSpawnRWPartsMultiple)
		{
			checkMultipleRWRes();
		}

		// create a new jdf document that contains the node to be spawned
		final JDFDoc docOut = new JDFDoc(ElementName.JDF);
		rootOut = (JDFNode) docOut.getRoot();

		// prepare the nodeinfos in main prior to spawning
		prepareNodeInfos();

		// merge this node into it
		rootOut.copyInto(node, true); // "copy" this node into the new created document
		docOut.setNSMap(node.getOwnerDocument_KElement());
		final String spawnID = "Sp" + KElement.uniqueID(-666); // create a spawn id for this transaction
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
		if (vSpawnParts != null && !vSpawnParts.isEmpty())
		{
			spawnParentNode = node;
			// don't copy the whole history along
			JDFAuditPool ap = rootOut.getAuditPool();
			if (ap != null)
			{
				rootOut.removeChild(ap);
				ap = rootOut.appendAuditPool();
			}

			// The AncestorPool of the original JDF contains the appropriate Part elements
			final JDFAncestorPool ancpool = docOut.getJDFRoot().getAncestorPool();
			VJDFAttributeMap preSpawnedParts = new VJDFAttributeMap();

			if (ancpool != null)
			{
				preSpawnedParts = ancpool.getPartMapVector();
			}
			// 150102 RP add AncestorPool pre spawn part handling
			if (!preSpawnedParts.isEmpty())
			{
				vSpawnParts.overlapMap(preSpawnedParts);
			}
			// we arrived at a null vector of parts - that ain't no good
			if (vSpawnParts.isEmpty())
			{
				throw new JDFException("JDFNode.Spawn attempting to spawn incompatible partitions");
			}
			setSpawnParts = ContainerUtil.toHashSet(vSpawnParts);
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
		final int size = vn.size();
		// fill all resources and all links of all children into vResPool and links
		for (int i = 0; i < size; i++)
		{
			final JDFNode vnNode_i = (JDFNode) vn.elementAt(i);
			// make sure we have a nodeinfo in all spawned nodes of main in case we have to merge stati
			vnNode_i.prepareNodeInfo(vSpawnParts);
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
			final Iterator<JDFResource> iterCheck = vCheck.iterator();
			while (iterCheck.hasNext())
			{
				vBad.appendUnique((iterCheck.next()).getAttribute(AttributeName.ID));
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
		final HashSet<JDFResource> vMultiRes = new LinkedHashSet<JDFResource>();
		// grab the root node and all it's children
		if (!idCacheFilled)
		{
			node.getOwnerDocument_JDFElement().getCreateXMLDocUserData().fillIDCache();
			idCacheFilled = true;
		}

		final HashSet<JDFElement> vRootLinks = node.getAllRefs(null, true);
		final Iterator<JDFElement> iter = vRootLinks.iterator();
		while (iter.hasNext())
		{
			final JDFElement liRoot = iter.next();
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
			if (bResRW && r != null)
			{
				VElement vRes = new VElement();
				if (vSpawnParts == null || vSpawnParts.isEmpty())
				{
					vRes = r.getLeaves(false);
				}
				else
				{
					vRes.appendUnique(r.getPartitionVector(vSpawnParts, null));
				}
				for (int k = 0; k < vRes.size(); k++)
				{
					final JDFResource rTarget = (JDFResource) vRes.elementAt(k);
					if (rTarget.getSpawnStatus() == JDFResource.EnumSpawnStatus.SpawnedRW)
					{
						if (!vMultiRes.contains(rTarget))
						{
							vMultiRes.add(rTarget);
						}
					}
				}
			}
		}
		// empty if all is well
		return vMultiRes.isEmpty() ? null : vMultiRes;
	}

	// ///////////////////////////////////////////////////////////////////////

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
			rootOut.removeChild(ElementName.ANCESTORPOOL, null, 0); // just in
			// case
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

		// avoid double counting of this node's root element in case of partitioned spawning
		int startAncestorLoop = 0;
		if ((vs.size() > 0) && ((vs.elementAt(0)).equals(lastAncestorID)))
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
		final JDFResourcePool rPool = rootOut.getCreateResourcePool();

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
		final HashSet<JDFElement> vRootLinks = node.getAllRefs(null, false);

		// create a HashSet with all IDs of the newly created Node
		final HashSet<String> allIDsCopied = getAllIdsCopied();
		final String spawnID = spawnAudit.getNewSpawnID();

		// first check only read only resources, since there may be a collision
		for (int loopRORW = 0; loopRORW < 2; loopRORW++)
		{
			// loop over all links
			final Iterator<JDFElement> iter = vRootLinks.iterator();
			while (iter.hasNext())
			{
				final JDFElement liRoot = iter.next();

				// test for direct children of resourcepool - these will be added later
				if (liRoot.getDeepParent(ElementName.RESOURCEPOOL, 0) != null)
				{
					continue;
				}

				final String refID = liRoot.getAttribute(AttributeName.RREF); // must be either refelem or link, therefore will have rref

				boolean bResRW = false;
				JDFResource rRoot = null;
				if (liRoot instanceof JDFResourceLink)
				{
					bResRW = linkFitsRWRes((JDFResourceLink) liRoot, vRWResources);
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

				final HashSet<String> vvRO = new LinkedHashSet<String>();
				final HashSet<String> vvRW = new LinkedHashSet<String>();
				if (rRoot == null)
				{
					rRoot = getNodeResource(refID);
				}

				// check for null and throw an exception in picky mode
				if (rRoot == null)
				{
					continue;
				}
				// copy any missing linked resources, just in case
				// the root is in the original jdf and can be used as a hook to  the original document
				// get a list of all resources referenced by this link
				// always do a copyresource in case some dangling rRefs are waiting
				copySpawnedResource(rPool, rRoot, copyStatus, spawnID, vRWResources, vvRW, vvRO, allIDsCopied);
				nSpawned += vvRO.size() + vvRW.size();

				// get the effected resources
				VElement vRes = new VElement();
				VElement vResRoot = new VElement();
				if (liRoot instanceof JDFResourceLink)
				{
					final JDFResourceLink liRootLink = (JDFResourceLink) liRoot;
					final VJDFAttributeMap vLinkMap = liRootLink.getPartMapVector();
					// make sure that spawned resources are sufficiently
					// partitioned if spawning rw so that no merge conflicts arise
					// create a temporary dummy copy of the link so that we have
					// a guaranteed copy that behaves the same as the original
					final JDFResourceLink dummy = (JDFResourceLink) rootOut.getCreateResourceLinkPool().copyElement(liRoot, null);
					fixResLinks(bResRW, liRootLink, dummy);
					// reduce partitions in main so that the links remain consistent
					liRootLink.setPartMapVector(vSpawnParts);
					dummy.setPartMapVector(vSpawnParts);

					vResRoot = ((JDFResourceLink) liRoot).getTargetVector(-1);

					vRes = dummy.getTargetVector(-1);
					dummy.deleteNode();
					reduceLinkPartition(liRootLink, vLinkMap);
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
					final JDFResource r = (JDFResource) vRes.elementAt(0);
					// reduce partitions of all RW resources and of RO resources if requested
					reducePartitions(r.getResourceRoot());
				}
				for (int resParts = 0; resParts < siz; resParts++)
				{
					final JDFResource r = (JDFResource) vRes.elementAt(resParts);
					final JDFResource rRoot1 = (JDFResource) vResRoot.elementAt(resParts);

					new PartSpawn().spawnPart(rRoot1, spawnID, copyStatus, true);
					new PartSpawn().spawnPart(r, spawnID, copyStatus, false);
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

		// must remove ap after use
		if (ap != null)
		{
			ap.deleteNode();
		}

		return nSpawned;
	}

	protected void calcAuditSpawnIDs(final JDFSpawned spawnAudit, final HashSet<String> vvRO, final HashSet<String> vvRW)
	{
		{
			VString rRefsRW = spawnAudit.getrRefsRWCopied();
			VString rRefsRO = spawnAudit.getrRefsROCopied();
			for (String s : vvRW)
			{
				rRefsRW.add(s);
				final int ind = rRefsRO.index(s);
				if (ind >= 0)
				{
					rRefsRO.remove(ind);
				}
			}
			for (String s : vvRO)
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
		HashSet<String> hs = new HashSet<String>();
		hs.addAll(allIDsCopied);
		return hs;
	}

	/**
	 * cache resources by id for performance
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
		if (!bSpawnIdentical)
		{
			return;
		}

		if (vRes == null || vRes.size() == 0 || vSpawnParts == null || vSpawnParts.size() == 0)
		{
			return;
		}
		final JDFResource root = ((JDFResource) vRes.get(0)).getResourceRoot();
		String id = root.getID();
		if (noIdentical.contains(id))
			return;
		final VElement identicals = root.getChildrenByTagName(ElementName.IDENTICAL, null, null, false, true, -1, false);
		if (identicals == null || identicals.size() == 0)
		{
			noIdentical.add(id);
			return;
		}

		for (int i = 0; i < identicals.size(); i++)
		{
			final JDFIdentical ident = (JDFIdentical) identicals.get(i);
			final JDFResource identParent = (JDFResource) ident.getParentNode_KElement();
			final JDFAttributeMap identMap = identParent.getPartMap();
			for (int j = 0; j < vSpawnParts.size(); j++)
			{
				final JDFAttributeMap mapPart = vSpawnParts.elementAt(j);
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
		if (bFixResources && vSpawnParts != null && vSpawnParts.size() != 0 && bResRW)
		{
			final VString rootPartIDKeys = rootOut.getJDFRoot().getPartIDKeys(vSpawnParts.elementAt(0));
			final JDFResource linkRoot = liRootLink.getLinkRoot();
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
			final JDFResource dummyRoot = dummy.getLinkRoot();
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
		// add parts to resource links if necessary
		if (vSpawnParts != null && !vSpawnParts.isEmpty())
		{
			final VElement outLinks = prepareSpawnLinks(rootOut);
			final VElement mainLinks = prepareSpawnLinks(node);
			finalizePartitions(spawnAudit, outLinks, mainLinks);
		}
		finalizeStatusAndAudits(spawnAudit);
	}

	/**
	 * @param spawnAudit
	 * @param outLinks
	 * @param mainLinks
	 */
	private void finalizePartitions(final JDFSpawned spawnAudit, final VElement outLinks, final VElement mainLinks)
	{
		final int mainLinkLen = mainLinks.size();
		final int outLinkSize = outLinks.size();
		final String spawnID = spawnAudit.getNewSpawnID();
		for (int i = 0; i < outLinkSize; i++)
		{
			JDFResourceLink link = (JDFResourceLink) outLinks.elementAt(i);
			final JDFResource r = link.getLinkRoot();

			// 2005-03-11 KM if the link is null continue, the JDF is invalid but in the best case only an audit is missing and the JDF is still operable
			// in the worst caste the spawned JDF is not executable at all
			if (r != null)
			{
				final VJDFAttributeMap vPartMap = getSpawnLinkMap(vSpawnParts, r);
				final VJDFAttributeMap vNewMap = getSpawnedLinkPartMap(link, vPartMap);
				reduceLinkPartition(link, vNewMap);
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
					for (int ii = 0; ii < mainLinkLen; ii++)
					{
						link = (JDFResourceLink) mainLinks.elementAt(ii);
						if (id.equals(link.getrRef()))
						{
							updateSpawnIDsInMain(spawnID, link, vPartMap);
						}
					}
				}
			}
			else
			{
				log.warn("invalid link id=" + link.getrRef() + " Skipping");
			}
		}
	}

	/**
	 * @param link
	 * @param vNewMap
	 */
	private void reduceLinkPartition(JDFResourceLink link, final VJDFAttributeMap vNewMap)
	{
		link.setPartMapVector(vNewMap);
		JDFAmountPool ap = link.getAmountPool();
		if (ap != null)
		{
			VElement partAmounts = ap.getChildElementVector(ElementName.PARTAMOUNT, null);
			if (partAmounts != null)
			{
				for (KElement e : partAmounts)
				{
					JDFPartAmount pa = (JDFPartAmount) e;
					if (!pa.getPartMapVector().overlapsMap(vNewMap))
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
	private void updateSpawnIDsInMain(final String spawnID, final JDFResourceLink link, final VJDFAttributeMap vPartMap)
	{
		JDFResource rMain = link.getLinkRoot();
		if (rMain == null)
		{
			rMain = getNodeResource(link.getrRef());
			if (rMain == null)
			{
				return; // snafu return exit for corrupt links
			}
			else
			// clean up incorrectly positioned resource
			{
				JDFNode parentNode = link.getParentJDF();
				parentNode.ensureValidRefsPosition(rMain);
			}
		}
		final VElement vMainPart = rMain.getPartitionVector(vPartMap, null);
		for (int kk = 0; kk < vMainPart.size(); kk++)
		{
			final JDFResource rMainPart = (JDFResource) vMainPart.elementAt(kk);
			if (rMainPart == null)
			{
				continue;
			}

			final VElement leaves = rMainPart.getLeaves(true);
			boolean bSpawnID = false;

			// if any child node or leaf has this spawnID we need not do
			// anything
			for (int kkk = 0; kkk < leaves.size(); kkk++)
			{
				final JDFResource rMainLeaf = (JDFResource) leaves.elementAt(kkk);
				bSpawnID = rMainLeaf.includesMatchingAttribute(AttributeName.SPAWNIDS, spawnID, EnumAttributeType.NMTOKENS);
				if (bSpawnID)
				{
					break;
				}
			}
			if (!bSpawnIdentical && !bSpawnID && vPartMap != null)
			{
				if (!vPartMap.subMap(rMainPart.getPartMap()))
				{
					bSpawnID = true; // bluff existing spawnID so that it does not get set below
				}
			}

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

		if (vLinkMap == null)
		{
			vNewMap = vPartMap;
		}
		else
		{
			for (JDFAttributeMap linkMap : vLinkMap)
			{
				if (setSpawnParts.contains(linkMap))
				{
					vNewMap.add(linkMap);
				}
				else
				{
					for (JDFAttributeMap m : vPartMap)
					{
						m = m.orMap(linkMap);

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
		for (int t = 0; t < vRes.size(); t++)
		{
			final JDFResource res = (JDFResource) vRes.elementAt(t);
			// only fix those local resources that haven't been fixed along the
			// way...
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
	 *get the actual nodeinfo attribute map based on vmAttribute
	 * @return
	 */
	private VJDFAttributeMap getNIPartitions()
	{
		final VJDFAttributeMap vMap;
		if (vSpawnParts != null)
		{
			JDFNodeInfo nodeInfo = node.getNodeInfo();
			VElement vNI = nodeInfo == null ? null : nodeInfo.getPartitionVector(vSpawnParts, EnumPartUsage.Explicit);
			if (vNI != null)
			{
				vMap = new VJDFAttributeMap();
				for (KElement e : vNI)
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
		if (!r.getPartUsage().equals(JDFResource.EnumPartUsage.Implicit) || true)
		{
			final VString vPartKeys = r.getPartIDKeys();
			final Vector<EnumPartIDKey> vImplicitPartitions = r.getImplicitPartitions();
			if (vImplicitPartitions != null)
			{
				for (int ii = 0; ii < vImplicitPartitions.size(); ii++)
				{
					final JDFResource.EnumPartIDKey e = vImplicitPartitions.elementAt(ii);
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
	 * @param r
	 * @param nodeName
	 * @param nsURI
	 * @param partIDKeys
	 * @param partIDPos
	 * @param parentMap
	 * @param identical
	 * @return the reduced partitions
	 */
	private VElement reducePartitions(final JDFResource r, final String nodeName, final String nsURI, final VString partIDKeys, final int partIDPos, final JDFAttributeMap parentMap, final VElement identical)
	{
		final VElement bad = new VElement();
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
								bad.appendUnique(reducePartitions(child, nodeName, nsURI, partIDKeys, partIDPos + 1, testMap, identical));
							}
							else
							{
								Vector<JDFIdentical> v = child.getChildrenByClass(JDFIdentical.class, true, 0);
								if (v != null && v.size() > 0)
								{

									VElement v2 = new VElement();
									for (int iii = 0; iii < v.size(); iii++)
									{
										KElement parentNode_KElement = v.get(iii).getParentNode_KElement();
										if (parentNode_KElement != child)
											v2.add(parentNode_KElement);
									}
									v2.unify();
									for (int iii = 0; iii < v2.size(); iii++)
									{
										JDFResource identParent = (JDFResource) v2.get(iii);
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
								//only needed so that we can remove non-identical referenced leaves in case we keep the parent
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
		while (partition != resourceRoot)
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
				final String key = keys.stringAt(j);
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
	private void reducePartitions(final JDFResource r)
	{
		if (r == null || vSpawnParts == null || vSpawnParts.size() == 0)
		{
			return;
		}

		final VString partIDKeys = r.getPartIDKeys();
		if (partIDKeys == null || partIDKeys.size() == 0)
		{
			return;
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
		final VElement vBad = reducePartitions(r, nodeName, nsURI, partIDKeys, 0, new JDFAttributeMap(), identical);
		vBad.unify();
		if (identical.size() > 0)
		{
			identical.unify();
			vBad.removeAll(identical);
		}
		for (int i = 0; i < vBad.size(); i++)
		{
			vBad.get(i).deleteNode();
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
	 * @param p the pool to copy r into
	 * @param r the resource to copy
	 * @param copyStatus rw or ro
	 * @param spawnID the spawnID of the spawning that initiated the copy
	 * @param vRWResources write resources
	 * @param vRWIDs 
	 * @param vROIDs 
	 * @param allIDsCopied 
	 * 
	 */
	private void copySpawnedResource(final JDFResourcePool p, final JDFResource r, JDFResource.EnumSpawnStatus copyStatus, final String spawnID, final VString vRWResources, final HashSet<String> vRWIDs, final HashSet<String> vROIDs, final HashSet<String> allIDsCopied)
	{
		if (r == null)
		{
			log.error("attempting to copy null resource - bailing out");
			return;
		}

		// r is not yet here copy r
		final boolean bRW = copyStatus == JDFResource.EnumSpawnStatus.SpawnedRW;
		final String rID = r.getID();
		if (!allIDsCopied.contains(rID))
		{
			final JDFResource rNew = (JDFResource) p.copyElement(r, null);
			// if spawning, fix stati and locks

			if (bRW || bSpawnROPartsOnly)
			{
				reducePartitions(rNew);
			}

			if (!bInformative) // in case of informative, we kill main anyhow - no use modifying it
			{
				new PartSpawn().spawnPart(r, spawnID, copyStatus, true);
			}
			new PartSpawn().spawnPart(rNew, spawnID, copyStatus, false);

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
			//			System.out.println("reuse" + r);
		}
		// add recursively copied resource references
		final int size = vs.size();
		for (int i = 0; i < size; i++)
		{
			final String id = vs.elementAt(i);

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
					// recurse into refelements
					copySpawnedResource(p, next, copyStatus, spawnID, vRWResources, vRWIDs, vROIDs, allIDsCopied);
				}
			}
		}

		return;
	}

	private class PartSpawn
	{
		/**
		 * @param r
		 * @param spawnID
		 * @param copyStatus
		 * @param bStayinMain
		 */
		private void spawnPart(final JDFResource r, final String spawnID, final JDFResource.EnumSpawnStatus copyStatus, final boolean bStayinMain)
		{

			if (vSpawnParts != null && vSpawnParts.size() > 0)
			{
				final JDFAttributeMap partMap = r.getPartMap();
				VElement vSubParts = getSubParts(r, partMap);
				for (int k = 0; k < vSubParts.size(); k++)
				{
					final JDFResource pLeaf = (JDFResource) vSubParts.item(k);
					if (pLeaf != null)
					{
						// set the lock of the leaf to true if it is RO, else unlock it
						if (bStayinMain)
						{
							if ((copyStatus == EnumSpawnStatus.SpawnedRW) || (pLeaf.getSpawnStatus() != EnumSpawnStatus.SpawnedRW))
							{
								pLeaf.setSpawnStatus(copyStatus);
								pLeaf.setLocked(copyStatus == EnumSpawnStatus.SpawnedRW);
							}
							pLeaf.appendSpawnIDs(spawnID);
						}
						else
						{
							pLeaf.setLocked(copyStatus != EnumSpawnStatus.SpawnedRW);
							pLeaf.setSpawnIDs(new VString(spawnID, null));
						}
					}
				}
			}
			else
			// no partitions
			{
				if (bStayinMain)
				{
					if ((copyStatus == EnumSpawnStatus.SpawnedRW) || (r.getSpawnStatus() != EnumSpawnStatus.SpawnedRW))
					{
						r.setSpawnStatus(copyStatus);
						r.setLocked(copyStatus == EnumSpawnStatus.SpawnedRW);
					}
				}
				else
				{
					r.setLocked(copyStatus != EnumSpawnStatus.SpawnedRW);
				}

				if (bStayinMain)
				{
					r.appendSpawnIDs(spawnID);
				}
				else
				{
					r.setSpawnIDs(new VString(spawnID, null));
				}
			}
		}

		private VElement getSubParts(final JDFResource r, final JDFAttributeMap partMap)
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
				//				vSubParts = r.getPartitionVector(vSpawnParts, EnumPartUsage.Implicit);

				//100208 spawn only as requested - NOT explicit, don't automatically create anything
				vSubParts = r.getPartitionVector(vSpawnParts, null);
				if (vSubParts == null || vSubParts.size() == 0)
					vSubParts = r.getPartitionVector(vSpawnParts, EnumPartUsage.Implicit);
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
		final VElement ve = r.getChildElementVector_KElement(r.getNodeName(), r.getNamespaceURI(), null, true, 9999999);
		final KElement[] tmp = new KElement[ve.size()];
		for (int i = 0; i < ve.size(); i++)
		{
			tmp[i] = ve.item(i).deleteNode();
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
	 * spawn a node in informative mode without modifying the root JDF; url is the file name of the new node, the parameters except for the list of rw
	 * resources, which are by definition empty, are identical to those of Spawn
	 * 
	 * vRWResourceUsage is the vector of Resources Usages, Resource Names or Resource IDs that are spawned RW, all others are spawned read only; vParts is the
	 * vector of part maps that are to be spawned, defaults to no part, i.e. the whole thing
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
	 * spawn a node; url is the file name of the new node, vRWResourceUsage is the vector of Resources Usages (or Names if no usage exists for the process) that
	 * are spawned RW, all others are spawned read only; vParts is the vector of part maps that are to be spawned, defaults to no part, i.e. the whole thing
	 * 
	 * @param _parentURL
	 * @param _spawnURL : URL of the spawned JDF file
	 * @param _vRWResources_in : vector of resource names and Usage / ProcessUsage that are spawned as rw <br>
	 * the format is one of:<br>
	 * ResName:Input<br>
	 * ResName:Output<br>
	 * ResName:ProcessUsage<br>
	 * @param _vSpawnParts vector of mAttributes that describe the parts to spawn, only valid PartIDKeys are allowed
	 * @param _bSpawnROPartsOnly if true, only the parts of RO resources that are specified in vParts are spawned, else the complete resource is spawned
	 * @param _bCopyNodeInfo copy the NodeInfo elements into the Ancestors
	 * @param _bCopyCustomerInfo copy the CustomerInfo elements into the Ancestors
	 * @param _bCopyComments copy the Comment elements into the Ancestors
	 * 
	 * @return The spawned node
	 * @since 050831 added bCopyComments @ tbd enhance nested spawning of partitioned nodes default: spawn(parentURL, null, null, null, false, false, false,
	 * false)
	 */
	public JDFNode spawn(final String _parentURL, final String _spawnURL, final VString _vRWResources_in, final VJDFAttributeMap _vSpawnParts, final boolean _bSpawnROPartsOnly, final boolean _bCopyNodeInfo, final boolean _bCopyCustomerInfo, final boolean _bCopyComments)
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
	 * spawn a node in informative mode without modifying the root JDF; url is the file name of the new node, the parameters except for the list of rw
	 * resources, which are by definition empty, are identical to those of Spawn
	 * 
	 * vRWResourceUsage is the vector of Resources Usages, Resource Names or Resource IDs that are spawned RW, all others are spawned read only; vParts is the
	 * vector of part maps that are to be spawned, defaults to no part, i.e. the whole thing
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
	public JDFNode spawnInformative(final String _parentURL, final String _spawnURL, final VJDFAttributeMap _vSpawnParts, final boolean _bSpawnROPartsOnly, final boolean _bCopyNodeInfo, final boolean _bCopyCustomerInfo, final boolean _bCopyComments)
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
			JDFSpawned spawnAudit = (JDFSpawned) nodeParent.getAuditPool().getAudit(0, EnumAuditType.Spawned, null, null);
			if (spawnAudit != null)
			{
				spawnID = spawnAudit.getNewSpawnID();
				log.info("calculating spawnID from parent: SpawnID=" + spawnID);
			}
		}
		return unSpawnNode(nodeParent, spawnID);
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

	/**
	 * unSpawnNode - undo a spawn of a node hier muss noch nachportiert werden - es gibt jetzt in JDFRoot eine Methode gleichen Namens, bei der man komfortabler
	 * das undo aufrufen kann. die Methode hier in JDFNode wird dann umbenannt (protected) und aus JDFRoot heraus aufgerufen.
	 * @param parent 
	 * @param strSpawnID 
	 * 
	 * 
	 * @return the fixed unspawned node
	 */
	private JDFNode unSpawnNode(final JDFNode parent, final String strSpawnID)
	{
		if (parent == null)
		{
			log.warn("No parent to unspawn, bailing out");
			return null;
		}
		JDFSpawned spawnAudit = null;

		JDFNode localNode = null;
		if (strSpawnID != null && !strSpawnID.equals(JDFConstants.EMPTYSTRING))
		{
			final JDFAuditPool auditPool = parent.getAuditPool();
			if (auditPool != null)
			{
				// look into the audit pool and search something, which was spawned
				final JDFAttributeMap mapSpawn = new JDFAttributeMap(JDFConstants.NEWSPAWNID, strSpawnID);
				spawnAudit = (JDFSpawned) auditPool.getAudit(0, JDFAudit.EnumAuditType.Spawned, mapSpawn, null);
				if (spawnAudit == null)
				{
					log.warn("No parent audit to unspawn, bailing out");
					return null; // nothing was spawned so we can undo nothing
				}

				// get parts from audit
				final VJDFAttributeMap parts = spawnAudit.getPartMapVector();
				final VString vs = spawnAudit.getrRefsROCopied();

				for (int i = 0; i < vs.size(); i++)
				{
					final JDFResource oldRes = (JDFResource) parent.getTarget(vs.elementAt(i), AttributeName.ID);
					if (oldRes != null)
					{
						oldRes.unSpawnPart(strSpawnID, JDFResource.EnumSpawnStatus.SpawnedRO);
					}
				}

				// merge rw resources
				final VString vRWCopied = spawnAudit.getrRefsRWCopied();

				for (int i = 0; i < vRWCopied.size(); i++)
				{
					final JDFResource oldRes = (JDFResource) parent.getTarget(vRWCopied.elementAt(i), AttributeName.ID);
					if (oldRes != null)
					{
						oldRes.unSpawnPart(strSpawnID, JDFResource.EnumSpawnStatus.SpawnedRW);
					}
				}

				localNode = (JDFNode) parent.getTarget(spawnAudit.getjRef(), AttributeName.ID);
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
						for (int i = 0; i < parts.size(); i++)
						{
							if ((parent.getPartStatus(parts.elementAt(i), 0).equals(JDFElement.EnumNodeStatus.Spawned)) || fHasAuditStatus)
							{
								parent.setPartStatus(parts.elementAt(i), status, null);
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

			if (spawnAudit != null)
			{
				spawnAudit.deleteNode();
			}
		}

		return localNode;
	}

	/**
	 * @see java.lang.Object#toString()
	 * @return
	*/
	@Override
	public String toString()
	{
		String jpID = node == null ? " - " : node.getJobPartID(false);
		return "[JDFSpawn JobPartID=" + jpID + "\nParts: " + vSpawnParts + "\nRW: " + vRWResources_in;
	}

}
