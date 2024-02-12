/**
 * The CIP4 Software License, Version 1.0
 *
 * Copyright (c) 2001-2024 The International Cooperation for the Integration of Processes in Prepress, Press and Postpress (CIP4). All rights reserved.
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
package org.cip4.jdflib.extensions.xjdfwalker.jdftoxjdf;

import java.util.ArrayList;
import java.util.List;

import org.cip4.jdflib.auto.JDFAutoMedia.EnumMediaType;
import org.cip4.jdflib.core.AttributeName;
import org.cip4.jdflib.core.ElementName;
import org.cip4.jdflib.core.JDFElement;
import org.cip4.jdflib.core.JDFPartAmount;
import org.cip4.jdflib.core.JDFRefElement;
import org.cip4.jdflib.core.JDFResourceLink;
import org.cip4.jdflib.core.JDFResourceLink.EnumUsage;
import org.cip4.jdflib.core.KElement;
import org.cip4.jdflib.core.VElement;
import org.cip4.jdflib.core.VString;
import org.cip4.jdflib.datatypes.JDFAttributeMap;
import org.cip4.jdflib.datatypes.VJDFAttributeMap;
import org.cip4.jdflib.extensions.ResourceHelper;
import org.cip4.jdflib.extensions.SetHelper;
import org.cip4.jdflib.extensions.XJDFConstants;
import org.cip4.jdflib.extensions.XJDFHelper;
import org.cip4.jdflib.jmf.JDFResourceInfo;
import org.cip4.jdflib.node.JDFNode;
import org.cip4.jdflib.pool.JDFAmountPool;
import org.cip4.jdflib.pool.JDFResourcePool;
import org.cip4.jdflib.resource.JDFPageList;
import org.cip4.jdflib.resource.JDFResource;
import org.cip4.jdflib.resource.JDFResource.EnumPartUsage;
import org.cip4.jdflib.resource.JDFResource.EnumResStatus;
import org.cip4.jdflib.resource.JDFStrippingParams;
import org.cip4.jdflib.resource.process.JDFComponent;
import org.cip4.jdflib.resource.process.JDFLayout;
import org.cip4.jdflib.resource.process.JDFMedia;
import org.cip4.jdflib.resource.process.JDFPageData;
import org.cip4.jdflib.resource.process.JDFSeparationSpec;
import org.cip4.jdflib.util.ContainerUtil;
import org.cip4.jdflib.util.StringUtil;

/**
 * @author Rainer Prosi, Heidelberger Druckmaschinen walker for the various resource sets
 */
public class WalkJDFElement extends WalkElement
{
	final String m_spawnInfo = "SpawnInfo";

	/**
	 *
	 */
	public WalkJDFElement()
	{
		super();
	}

	/**
	 * @param jdf
	 * @param xjdf
	 * @return the created resource
	 */
	@Override
	public KElement walk(final KElement jdf, final KElement xjdf)
	{
		final JDFElement je = (JDFElement) jdf;
		makeRefElements(je);
		return super.walk(jdf, xjdf);
	}

	/**
	 * make all inline resources to refelements
	 *
	 * @param je
	 */
	void makeRefElements(final JDFElement je)
	{
		removeUnusedElements(je);
		final List<KElement> v = je.getChildList();
		for (final KElement e : v)
		{
			if ((e instanceof JDFResource) && JDFElement.isInJDFNameSpaceStatic(e) && !mustInline(e.getLocalName()))
			{
				cleanRefs(je, (JDFResource) e);
			}
		}
	}

	/**
	 *
	 * @param node
	 * @return
	 */
	protected boolean matchesRootID(final JDFNode node)
	{
		boolean matchesID = node.getID().equals(jdfToXJDF.rootID);
		if (!matchesID && !jdfToXJDF.isSingleNode())
		{
			JDFNode parent = node.getParentJDF();
			while (parent != null && !matchesID)
			{
				matchesID = parent.getID().equals(jdfToXJDF.rootID);
				parent = parent.getParentJDF();
			}
		}
		return matchesID;
	}

	/**
	 * @param je
	 * @param r
	 */
	void cleanRefs(final JDFElement je, JDFResource r)
	{
		if (r.isResourceElement())
		{
			final JDFNode parentJDF = je.getParentJDF();
			if (parentJDF != null)
			{
				r = r.makeRootResource(null, parentJDF, false);
				final JDFResourcePool prevPool = parentJDF.getResourcePool();
				if (prevPool != null)
				{
					r = removeDuplicateRefs(r, prevPool);
				}
				jdfToXJDF.getResourceAlias().add(r.getID());
			}
			else if (je.getJMFRoot() != null)
			{
				final JDFResource resourceRoot = r.getResourceRoot();
				final JDFElement parent = (JDFElement) (resourceRoot == null ? null : resourceRoot.getParentNode_KElement());
				r = r.makeRootResource(null, parent, false);
			}
			r.setResStatus(EnumResStatus.Available, true);
			je.refElement(r);
		}
	}

	/**
	 * @param r
	 * @param prevPool
	 * @return
	 */
	private JDFResource removeDuplicateRefs(JDFResource r, final JDFResourcePool prevPool)
	{
		final JDFAttributeMap m = r.getAttributeMap();
		m.remove(AttributeName.ID);
		final VElement prevs = prevPool.getChildrenByTagName(jdfToXJDF.getSetName(r), null, m, true, true, 0);
		if (prevs != null)
		{
			for (final KElement e : prevs)
			{
				final JDFResource prev = (JDFResource) e;
				if (r == prev)
				{
					continue;
				}
				final String pid = prev.getID();
				final String rid = r.getID();
				prev.removeAttribute(AttributeName.ID); // for comparing
				r.removeAttribute(AttributeName.ID);
				if (r.isEqual(prev)) // found duplicate - remove and ref the original
				{
					r.deleteNode();
					r = prev;
					prev.setID(pid); // better put it back...
					break;
				}
				else
				{
					r.setID(rid);
					prev.setID(pid); // better put it back...
				}
			}
		}
		return r;
	}

	/**
	 * get the name for the attribute to become a reference - may add a "Refs" rather than ref for
	 *
	 * @param re the refelement to name
	 * @return the name
	 */
	protected String getRefName(final JDFRefElement re)
	{
		String name = re.getLocalName();
		if (jdfToXJDF.isMergeRunList() && "LayoutElementRef".equals(name))
		{
			name = "RunListRef";
		}
		if ("ContactRef".equals(name))
		{
			name += 's';
		}
		name = getMediaRefName(re, name);
		return name;
	}

	String getMediaRefName(final JDFRefElement re, String name)
	{
		if ("MediaRef".equals(name))
		{
			final KElement parent = re.getParentNode_KElement();
			if ((parent instanceof JDFLayout) || (parent instanceof JDFStrippingParams))
			{
				final JDFMedia m = (JDFMedia) re.getTarget();
				if (m != null)
				{
					final EnumMediaType t = m.getMediaType();
					if (EnumMediaType.Paper.equals(t))
					{
						name = "PaperRef";
					}
					if (EnumMediaType.Plate.equals(t))
					{
						name = "PlateRef";
					}
				}
			}
		}
		return name;
	}

	/**
	 * @see org.cip4.jdflib.elementwalker.BaseWalker#matches(org.cip4.jdflib.core.KElement)
	 * @param toCheck
	 * @return true if it matches
	 */
	@Override
	public boolean matches(final KElement toCheck)
	{
		return toCheck instanceof JDFElement;
	}

	/**
	 *
	 * @param xjdf
	 * @param rl
	 * @return
	 */
	KElement getProductForElement(final KElement xjdf, final JDFElement rl)
	{
		final JDFNode rlParent = (rl instanceof JDFNode) ? (JDFNode) rl : rl.getParentJDF();
		final String parentID = getXJDFProductID(rlParent);
		final KElement product = new XJDFHelper(xjdf).getCreateProduct(parentID, null).getProduct();
		if (jdfToXJDF.isRetainAll())
		{
			product.copyAttribute(AttributeName.JOBPARTID, rlParent);
		}
		return product;
	}

	/**
	 * @param node
	 * @return
	 */
	String getXJDFProductID(final JDFNode node)
	{
		final JDFComponent c = (JDFComponent) node.getResource(ElementName.COMPONENT, EnumUsage.Output, 0);
		String jpID = node.getJobPartID(false);
		if (c != null)
		{
			final VString cid = c.getAssemblyIDs();
			if (ContainerUtil.size(cid) == 1)
			{
				jpID = cid.get(0);
			}
			else
			{
				final String productID = c.getProductID();
				if (!StringUtil.isEmpty(productID))
				{
					jpID = productID;
				}
			}
		}
		final String id = StringUtil.isEmpty(jpID) ? node.getID() : jpID;
		return "IDP_" + id;
	}

	/**
	 * @see org.cip4.jdflib.extensions.XJDF20.WalkElement#removeUnusedElements(org.cip4.jdflib.core.KElement)
	 * @param newRootP
	 */
	@Override
	protected void updateAttributes(final JDFAttributeMap map)
	{
		if (!jdfToXJDF.isRetainAll())
		{
			map.remove(AttributeName.BESTEFFORTEXCEPTIONS);
			map.remove(AttributeName.LOCKED);
			map.remove(AttributeName.MAXVERSION);
			map.remove(AttributeName.MUSTHONOREXCEPTIONS);
			map.remove(AttributeName.OPERATORINTERVENTIONEXCEPTIONS);
			map.remove(AttributeName.PIPEPARTIDKEYS);
			map.remove(AttributeName.PIPEPAUSE);
			map.remove(AttributeName.PIPERESUME);
			map.remove(AttributeName.REMOTEPIPEENDPAUSE);
			map.remove(AttributeName.REMOTEPIPEENDRESUME);
			map.remove(AttributeName.SETTINGSPOLICY);
			map.remove(AttributeName.SOURCEWORKSTYLE);
			map.remove(AttributeName.SPAWNID);
			super.updateAttributes(map);
		}
	}

	protected void updateModule(final JDFAttributeMap map)
	{
		final String oldModule = map.remove(AttributeName.MODULEINDEX);
		if (!StringUtil.isEmpty(oldModule) && map.getNonEmpty(AttributeName.MODULEID) == null)
		{
			map.put(AttributeName.MODULEID, "Mod" + oldModule);
		}
	}

	/**
	 *
	 * @param r
	 */
	void updateColorPoolColors(final JDFResource r)
	{
		final VElement v = r.getChildElementVector(ElementName.COLOR, null);
		for (final KElement e : v)
		{
			if (!e.hasAttribute(AttributeName.ACTUALCOLORNAME))
			{
				e.copyAttribute(AttributeName.ACTUALCOLORNAME, e, AttributeName.NAME, null, null);
			}
			final String sep = StringUtil.replaceChar(e.getAttribute(AttributeName.NAME), ' ', "_", 0);
			e.setAttribute(AttributeName.SEPARATION, sep);
			e.removeAttribute(AttributeName.NAME);
		}
		final KElement cNew = safeRename(r, ElementName.COLOR);
		cNew.setAttribute(AttributeName.PARTIDKEYS, AttributeName.SEPARATION);
	}

	/**
	 * @param rl the refelement or reslink
	 * @param linkTarget
	 * @param xRoot
	 * @return the vector of partitions
	 */
	protected List<KElement> setResource(final JDFElement rl, final JDFResource linkTarget, final KElement xRoot)
	{
		final String className = jdfToXJDF.getClassName(linkTarget);
		if (className == null || xRoot == null || linkTarget == null)
		{
			return null;
		}
		final JDFResourceLink resLink = (rl instanceof JDFResourceLink) ? (JDFResourceLink) rl : null;
		if (isExchangeResource(resLink, linkTarget))
		{
			return null;
		}
		final boolean expandLink = rl instanceof JDFResourceLink;
		if (expandLink)
		{
			linkTarget.getResourceRoot().expand(false);
		}
		else
		{
			linkTarget.expand(false);
		}

		final String resID = getResID(linkTarget, rl);
		KElement resourceSet = getSet(resID, xRoot, className);
		if (resourceSet == null)
		{
			resourceSet = xRoot.appendElement(className + SetHelper.SET);
			resourceSet.setID(resID);
		}

		// TODO what if we have resources used as in and out in the same node?
		setSetAttributes(resourceSet, rl, linkTarget);
		final int nLeaves = resourceSet.numChildElements(className, null);
		final VElement vRes = getLeaves(rl, linkTarget, expandLink);

		return loopLeaves(rl, className, resourceSet, nLeaves, vRes);
	}

	protected VElement getLeaves(final JDFElement rl, final JDFResource linkTarget, final boolean expandLink)
	{
		if (expandLink)
		{
			final VElement rawTargetVector = ((JDFResourceLink) rl).getRawTargetVector(0);
			final VElement newTargetVector = new VElement();
			boolean changed = false;
			for (final KElement e : rawTargetVector)
			{
				if (((JDFResource) e).isLeaf())
				{
					newTargetVector.add(e);
				}
				else
				{
					newTargetVector.addAll(((JDFResource) e).getLeaves(checkAllLeaves(linkTarget)));
					changed = true;
				}
			}
			if (changed)
				ContainerUtil.unify(newTargetVector);
			return newTargetVector;
		}
		else
		{
			return linkTarget.getLeaves(false);
		}
	}

	protected boolean checkAllLeaves(final JDFResource linkTarget)
	{
		final boolean implicit = jdfToXJDF.wantImplicit() && EnumPartUsage.Implicit.equals(linkTarget.getPartUsage());
		final boolean isLayout = (linkTarget instanceof JDFLayout) || (linkTarget instanceof JDFStrippingParams);
		return implicit && !(isLayout && ContainerUtil.contains(linkTarget.getPartIDKeyList(), AttributeName.SHEETNAME));
	}

	List<KElement> loopLeaves(final JDFElement rl, final String className, final KElement resourceSet, final int nLeaves, final VElement vRes)
	{
		final List<KElement> v = new ArrayList<>();
		final SetHelper setHelper = new SetHelper(resourceSet);
		int currentLeaves = nLeaves;
		for (final KElement e : vRes)
		{
			final JDFResource r = (JDFResource) e;
			final List<JDFResource> vLeaves = r.getLeafArray(checkAllLeaves(r));
			for (final JDFResource leaf : vLeaves)
			{
				if (AttributeName.SIGNATURENAME.equals(leaf.getLocalPartitionKey()))
					continue;
				final KElement newBaseRes = setBaseResource(rl, leaf, setHelper);
				if (newBaseRes != null)
				{
					final int nn = resourceSet.numChildElements_KElement(className, null);
					if (nn > currentLeaves)
					{
						jdfToXJDF.walkTree(leaf, newBaseRes);
						currentLeaves = nn;
					}
					v.add(newBaseRes);
				}
			}
		}
		return v;
	}

	/**
	 *
	 * @param linkTarget
	 * @param rl
	 * @return
	 */
	protected String getResID(final JDFResource linkTarget, final JDFElement rl)
	{
		String resID = linkTarget.getID();
		if (jdfToXJDF.getResourceAlias().contains(resID) && (rl instanceof JDFRefElement))
		{
			// we don't want a new res for each refelement
			final String localName = rl.getParentNode_KElement().getLocalName();
			resID = localName + "_" + (localName.hashCode() % 100000);
		}
		return resID;
	}

	/**
	 *
	 * @param resLink
	 * @param linkTarget
	 * @return
	 */
	boolean isExchangeResource(final JDFResourceLink resLink, final JDFResource linkTarget)
	{
		if (!jdfToXJDF.isSingleNode() || (resLink != null && EnumUsage.Input.equals(resLink.getUsage()) && resLink.hasNonEmpty(AttributeName.COMBINEDPROCESSINDEX)
				&& !resLink.getCombinedProcessIndex().contains(0)))
		{

			final JDFResource resInRoot = linkTarget == null ? null : linkTarget.getResourceRoot();
			if (resInRoot != null)
			{
				final VElement vCreators = resInRoot.getCreator(true);
				if (!ContainerUtil.isEmpty(vCreators))
				{
					final VElement vConsumers = resInRoot.getCreator(false);
					return (!ContainerUtil.isEmpty(vConsumers));
				}
			}
		}
		return false;
	}

	/**
	 *
	 * @param linkTarget
	 * @param xRoot
	 * @param className
	 * @return
	 */
	protected KElement getSet(final String resID, final KElement xRoot, final String className)
	{
		final KElement resourceSet = xRoot.getChildWithAttribute(className + SetHelper.SET, AttributeName.ID, null, resID, 0, true);
		return resourceSet;
	}

	/**
	 * @param rl
	 * @param r
	 * @param xjdfSet
	 * @return
	 *
	 */
	protected KElement setBaseResource(final JDFElement rl, final JDFResource r, final SetHelper sh)
	{
		final KElement newLeaf;
		if (jdfToXJDF.getResourceAlias().contains(r.getID()))
		{
			newLeaf = sh.appendPartition(null, false).getPartition();
		}
		else
		{
			JDFAttributeMap map = r.getPartMap();
			map = convertRanges(map, r);
			final ResourceHelper tmp = sh.getExactPartition(map);
			if (tmp != null)
			{
				return tmp.getRoot();
			}
			newLeaf = sh.getCreateExactPartition(map, false).getPartition();
		}
		setLeafAttributes(r, rl, newLeaf);
		return newLeaf;
	}

	/**
	 * @param leaf
	 * @param rl
	 * @param newLeaf
	 */
	protected void setLeafAttributes(final JDFResource leaf, final JDFElement rl, final KElement newLeaf)
	{
		final JDFAttributeMap partMap = leaf.getPartMap();
		setAmountPool(rl, newLeaf, partMap);
		// retain spawn information
		if (jdfToXJDF.isRetainSpawnInfo() && leaf.hasAttribute(AttributeName.SPAWNIDS))
		{
			final KElement spawnInfo = newLeaf.getDocRoot().getCreateElement(m_spawnInfo, null, 0);
			final KElement spawnID = spawnInfo.appendElement("SpawnID");
			spawnID.moveAttribute(AttributeName.SPAWNIDS, newLeaf, null, null, null);
			spawnID.moveAttribute(AttributeName.SPAWNSTATUS, newLeaf, null, null, null);
			spawnID.copyAttribute(AttributeName.RESOURCEID, newLeaf, AttributeName.ID, null, null);
		}
	}

	/**
	 *
	 *
	 * @param rl
	 * @param newLeaf
	 * @param partMap
	 */
	protected void setAmountPool(final JDFElement rl, final KElement newLeaf, final JDFAttributeMap partMap)
	{
		if (rl != null)
		{
			JDFAmountPool ap = (JDFAmountPool) rl.getElement(ElementName.AMOUNTPOOL);
			if (ap == null)
			{
				final JDFAttributeMap amounts = rl.getAttributeMap().reduceMap(JDFToXJDFDataCache.getAmountAttribs());
				if (!amounts.isEmpty())
				{
					ap = (JDFAmountPool) newLeaf.getCreateElement(ElementName.AMOUNTPOOL);
					for (final String key : amounts.keySet())
					{
						ap.setPartAttribute(key, amounts.get(key), null, partMap);
						rl.removeAttribute(key);
					}
				}
			}
			else
			{
				processAmountPool(newLeaf, partMap, ap);
			}
		}
	}

	void processAmountPool(final KElement newLeaf, final JDFAttributeMap partMap, JDFAmountPool ap)
	{
		final VElement vPartAmounts = ap.getMatchingPartAmountVector(partMap);
		if (vPartAmounts != null && vPartAmounts.size() > 0)
		{
			ap = (JDFAmountPool) newLeaf.getCreateElement(ElementName.AMOUNTPOOL);
			for (final KElement e : vPartAmounts)
			{
				final JDFPartAmount pa = (JDFPartAmount) e;
				moveToAmountPool(ap, pa);
			}
		}
	}

	void setSeparations(final KElement jdf, final KElement xjdf, final String attName)
	{
		final VElement v = jdf.getChildElementVector(ElementName.SEPARATIONSPEC, null, null, false, 0, false);
		final VString vsep = new VString();
		for (final KElement e : v)
		{
			final JDFSeparationSpec sep = (JDFSeparationSpec) e;
			final String sepName = sep.getName();
			if (!StringUtil.isEmpty(sepName))
			{
				vsep.add(StringUtil.replaceChar(sepName, ' ', "_", 0));
			}
			e.deleteNode();
		}
		if (!vsep.isEmpty() && xjdf != null)
		{
			xjdf.setAttribute(attName, StringUtil.setvString(vsep));
		}
	}

	/**
	 *
	 * @param newAP
	 * @param pa
	 */
	protected void moveToAmountPool(final JDFAmountPool newAP, final JDFPartAmount pa)
	{
		JDFAttributeMap partMap = pa.getPartMap();
		partMap = convertRanges(partMap, pa);
		VJDFAttributeMap partMapVector = pa.getPartMapVector();
		if (partMap != null && jdfToXJDF.isExplicitWaste())
		{
			final String condition = partMap.get(AttributeName.CONDITION);
			final boolean bWaste = StringUtil.getNonEmpty(condition) != null && !"Good".equals(condition);
			if (partMapVector != null)
			{
				partMapVector.removeKey(AttributeName.CONDITION);
				final VJDFAttributeMap converted = new VJDFAttributeMap();
				for (final JDFAttributeMap m : partMapVector)
				{
					converted.add(convertRanges(m, pa.getPart(0)));
				}
				partMapVector = converted;
			}
			final JDFPartAmount paNew = newAP.getCreatePartAmount(partMapVector);
			final JDFAttributeMap map = pa.getAttributeMap();
			if (bWaste)
			{
				map.remove(AttributeName.AMOUNT);
				map.remove(AttributeName.ACTUALAMOUNT);
				paNew.setAttributes(map);
				// Note that actualamount and actualwaste will be copied in a post processing step by @see PostXJDFWalker
				final String wasteName = "Waste".equals(condition) ? null : condition;
				paNew.copyAttribute("ActualWaste", pa, AttributeName.ACTUALAMOUNT, null, null);
				paNew.copyAttribute(XJDFConstants.Waste, pa, AttributeName.AMOUNT, null, null);
				paNew.setAttribute("WasteDetails", wasteName);
			}
			else
			{
				paNew.setAttributes(map);
			}
		}
		else
		{
			final JDFPartAmount paNew = newAP.getCreatePartAmount(partMapVector);
			paNew.setAttributes(pa);
		}
	}

	/**
	 * set the attributes of the set based on the resource and resourcelink
	 *
	 * @param resourceSet
	 * @param rl
	 * @param linkRoot
	 */
	protected void setSetAttributes(final KElement resourceSet, final KElement rl, final JDFResource linkRoot)
	{
		resourceSet.setAttribute(AttributeName.NAME, jdfToXJDF.getSetName(linkRoot));
		if (rl instanceof JDFResourceLink)
			resourceSet.setAttributes(rl);
		final JDFResource rootroot = linkRoot == null ? null : linkRoot.getResourceRoot();
		resourceSet.copyAttribute(AttributeName.DESCRIPTIVENAME, rootroot);
		resourceSet.moveAttribute(AttributeName.COMMENTURL, rootroot);
		resourceSet.copyAttribute(AttributeName.UNIT, rootroot);

		// TODO orientation + coordinate system stuff
		resourceSet.removeAttribute(AttributeName.RREF);
		resourceSet.removeAttribute(AttributeName.RSUBREF);
		resourceSet.removeAttribute(AttributeName.AMOUNT);
		resourceSet.removeAttribute(AttributeName.AMOUNTPRODUCED);
		resourceSet.removeAttribute(AttributeName.AMOUNTREQUIRED);
		resourceSet.removeAttribute(AttributeName.MINSTATUS);
		resourceSet.removeAttribute(AttributeName.MINAMOUNT);
		resourceSet.removeAttribute(AttributeName.MAXAMOUNT);
		resourceSet.removeAttribute(AttributeName.ACTUALAMOUNT);
		resourceSet.removeAttribute(AttributeName.PIPEPROTOCOL);
		resourceSet.removeAttribute(AttributeName.PIPEPAUSE);
		resourceSet.removeAttribute(AttributeName.PIPERESUME);
		if (rl instanceof JDFResourceInfo)
		{
			resourceSet.copyAttribute(AttributeName.USAGE, rl);
			resourceSet.copyAttribute(AttributeName.PROCESSUSAGE, rl);
		}
		if (jdfToXJDF.isSingleNode() && jdfToXJDF.wantDependent())
		{
			setDependent(resourceSet, rl, linkRoot);
		}
	}

	/**
	 *
	 * @param resourceSet
	 * @param rl
	 * @param linkRoot
	 */
	private void setDependent(final KElement resourceSet, final KElement rl, final JDFResource linkRoot)
	{
		if (JDFResourceLink.isResourceLink(rl))
		{
			final JDFResourceLink resLink = (JDFResourceLink) rl;
			final VElement vResInRoot = resLink.getTargetVector(0);
			if (vResInRoot != null)
			{
				final VElement vCreators = new VElement();
				for (final KElement r : vResInRoot)
				{
					final VElement vTmp = ((JDFResource) r).getCreator(EnumUsage.Input.equals(resLink.getUsage()));
					if (vTmp != null)
					{
						vCreators.addAll(vTmp);
					}
				}
				vCreators.unify();
				if (vCreators.isEmpty())
				{
					createNewDependent(resourceSet, linkRoot, resLink);
				}
				else
				{
					for (final KElement creator : vCreators)
					{
						processCreator(resourceSet, linkRoot, resLink, creator);
					}
				}
			}
		}
	}

	void createNewDependent(final KElement resourceSet, final JDFResource linkRoot, final JDFResourceLink resLink)
	{
		final KElement dependent = resourceSet.appendElement(XJDFConstants.Dependent);
		dependent.moveAttribute(AttributeName.PIPEPAUSE, linkRoot);
		dependent.moveAttribute(AttributeName.PIPEPAUSE, resLink);
		dependent.moveAttribute(AttributeName.PIPERESUME, linkRoot);
		dependent.moveAttribute(AttributeName.PIPERESUME, resLink);
		dependent.moveAttribute(AttributeName.PIPEPROTOCOL, linkRoot);
		dependent.moveAttribute(AttributeName.PIPEPROTOCOL, resLink);
		dependent.moveAttribute(AttributeName.PIPEID, linkRoot);
		dependent.copyAttribute(AttributeName.PIPEPARTIDKEYS, resLink);
		removeDuplicateDependents(resourceSet);
	}

	void processCreator(final KElement resourceSet, final JDFResource linkRoot, final JDFResourceLink resLink, final KElement creator)
	{
		final JDFNode depNode = (JDFNode) creator;
		if (!depNode.isGroupNode())
		{
			final KElement dependent = resourceSet.appendElement(XJDFConstants.Dependent);
			dependent.setAttribute(AttributeName.JOBID, depNode.getJobID(true));
			dependent.copyAttribute(AttributeName.JMFURL, depNode);
			dependent.copyAttribute(AttributeName.JOBPARTID, depNode);
			dependent.moveAttribute(AttributeName.PIPEPROTOCOL, linkRoot);
			dependent.moveAttribute(AttributeName.PIPEPROTOCOL, resLink);
			dependent.moveAttribute(AttributeName.PIPEID, linkRoot);
			removeDuplicateDependents(resourceSet);
		}
	}

	void removeDuplicateDependents(final KElement resourceSet)
	{
		final VElement exist = resourceSet.getChildElementVector(XJDFConstants.Dependent, null);
		if (exist.size() > 1)
		{
			final KElement newDep = exist.get(-1);
			for (int i = exist.size() - 2; i >= 0; i--)
			{
				final KElement old = exist.get(i);
				if (old.isEqual(newDep))
				{
					newDep.deleteNode();
					break;
				}
			}
		}

	}

	/**
	 *
	 * @param r
	 */
	void moveToContent(final JDFPageList pl)
	{
		if (!pl.isIndexed())
		{
			pl.uniqueIndex();
		}
		final KElement cNew = safeRename(pl, XJDFConstants.Content);
		cNew.appendAttribute(AttributeName.PARTIDKEYS, AttributeName.PAGENUMBER, null, null, true);
		final List<JDFPageData> vpd = cNew.getChildArrayByClass(JDFPageData.class, true, 0);
		if (vpd != null)
		{
			int i = 0;
			for (final JDFPageData pd : vpd)
			{
				if (!pd.hasNonEmpty(AttributeName.PAGEINDEX))
				{
					pd.setPageIndex(i);
				}
				pd.renameAttribute(AttributeName.PAGEINDEX, AttributeName.PAGENUMBER, null, null);
				pd.setAttribute(AttributeName.CONTENTTYPE, "Page");
				safeRename(pd, XJDFConstants.Content);
				i++;
			}
		}
	}
}
