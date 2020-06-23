/**
 * The CIP4 Software License, Version 1.0
 *
 * Copyright (c) 2001-2020 The International Cooperation for the Integration of Processes in Prepress, Press and Postpress (CIP4). All rights reserved.
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
package org.cip4.jdflib.extensions.xjdfwalker.xjdftojdf;

import java.util.Collection;
import java.util.HashSet;
import java.util.List;

import org.cip4.jdflib.core.AttributeName;
import org.cip4.jdflib.core.ElementName;
import org.cip4.jdflib.core.JDFElement;
import org.cip4.jdflib.core.JDFNodeInfo;
import org.cip4.jdflib.core.JDFPartAmount;
import org.cip4.jdflib.core.JDFRefElement;
import org.cip4.jdflib.core.JDFResourceLink;
import org.cip4.jdflib.core.JDFResourceLink.EnumUsage;
import org.cip4.jdflib.core.KElement;
import org.cip4.jdflib.core.VElement;
import org.cip4.jdflib.core.VString;
import org.cip4.jdflib.datatypes.JDFAttributeMap;
import org.cip4.jdflib.datatypes.VJDFAttributeMap;
import org.cip4.jdflib.elementwalker.EnsureNSUri;
import org.cip4.jdflib.elementwalker.RemoveEmpty;
import org.cip4.jdflib.elementwalker.UnLinkFinder;
import org.cip4.jdflib.extensions.XJDFConstants;
import org.cip4.jdflib.node.JDFNode;
import org.cip4.jdflib.node.JDFNode.EnumType;
import org.cip4.jdflib.pool.JDFAmountPool;
import org.cip4.jdflib.pool.JDFResourcePool;
import org.cip4.jdflib.resource.JDFPageList;
import org.cip4.jdflib.resource.JDFPart;
import org.cip4.jdflib.resource.JDFResource;
import org.cip4.jdflib.resource.JDFResource.EnumPartUsage;
import org.cip4.jdflib.resource.JDFResource.EnumResStatus;
import org.cip4.jdflib.resource.intent.JDFArtDeliveryIntent;
import org.cip4.jdflib.resource.intent.JDFDeliveryIntent;
import org.cip4.jdflib.resource.process.JDFBinderySignature;
import org.cip4.jdflib.resource.process.JDFDeliveryParams;
import org.cip4.jdflib.resource.process.JDFDependencies;
import org.cip4.jdflib.resource.process.JDFEmployee;
import org.cip4.jdflib.resource.process.JDFIdentical;
import org.cip4.jdflib.resource.process.JDFLayoutElement;
import org.cip4.jdflib.resource.process.JDFLayoutElementProductionParams;
import org.cip4.jdflib.resource.process.JDFPageData;
import org.cip4.jdflib.resource.process.JDFRunList;
import org.cip4.jdflib.resource.process.JDFSignatureCell;
import org.cip4.jdflib.resource.process.JDFStripCellParams;
import org.cip4.jdflib.util.ContainerUtil;
import org.cip4.jdflib.util.StringUtil;

/**
 *
 *
 * @author rainer prosi
 * @date Feb 26, 2013
 */
class PostConverter
{
	/**
	 *
	 */
	private final XJDFToJDFImpl xjdfToJDFImpl;
	private final JDFNode theNode;

	/**
	 * @param xjdfToJDFImpl
	 * @param theNode
	 */
	PostConverter(final XJDFToJDFImpl xjdfToJDFImpl, final JDFNode theNode)
	{
		super();
		this.xjdfToJDFImpl = xjdfToJDFImpl;
		this.theNode = theNode;
	}

	/**
	 *
	 *
	 */
	void postConvert()
	{
		final JDFNode root = theNode.getJDFRoot();
		final String type = StringUtil.getNonEmpty(root.getType());
		if (type == null || XJDFConstants.Product.equals(type))
		{
			mergeProductLinks(theNode, root);
		}
		fixDelivery();
		new GangCleaner().cleanGangLinks();
		new ResourceCleaner().cleanResources();
		new ProcessCleaner().cleanProcesses();
		new DependencyCleaner().fixDependencies(root);
		new LinkAmountCleaner().cleanLinkAmounts();

		new UnLinkFinder().eraseUnlinked(root);
		xjdfToJDFImpl.firstConvert = false;

		final EnsureNSUri fixNS = new EnsureNSUri();
		fixNS.addNS(null, JDFElement.getSchemaURL());
		fixNS.walk(root);

		final RemoveEmpty re = new RemoveEmpty();
		re.removEmpty(root);
	}

	private class GangCleaner
	{
		void cleanGangLinks()
		{
			JDFNode n = theNode;
			while (n != null)
			{
				VElement links = theNode.getResourceLinks(ElementName.NODEINFO, null, null);
				links = (VElement) ContainerUtil.addAll(links, theNode.getResourceLinks(ElementName.CUSTOMERINFO, null, null));
				if (links != null)
				{
					for (final KElement e : links)
					{
						cleanGangLink(e);
					}
				}
				n = n.getParentJDF();
			}
		}

		/**
		 *
		 * @param e
		 */
		private void cleanGangLink(final KElement e)
		{
			final JDFResourceLink link = (JDFResourceLink) e;
			final VJDFAttributeMap linkMaps = link.getPartMapVector();
			if (linkMaps != null)
			{
				linkMaps.reduceMap(new VString(AttributeName.PRODUCTPART, null));
				if (!linkMaps.isEmpty())
				{
					boolean mustZapp = true;
					for (final JDFAttributeMap map : linkMaps)
					{
						final String val = map.get(AttributeName.PRODUCTPART);
						if (theNode.getID().equals(val))
						{
							mustZapp = false;
							break;
						}
					}
					if (mustZapp)
					{
						link.deleteNode();
					}
				}
			}
		}
	}

	private class LinkAmountCleaner
	{
		void cleanLinkAmounts()
		{
			JDFNode n = theNode;
			while (n != null)
			{
				final VElement links = theNode.getResourceLinks(null, null, null);
				if (links != null)
				{
					for (final KElement e : links)
					{
						cleanLink(e);
					}
				}
				n = n.getParentJDF();
			}
		}

		void cleanLink(final KElement e)
		{
			if (ElementName.BINDERYSIGNATURE.equals(((JDFResourceLink) e).getLinkedResourceName()))
			{
				e.deleteNode();
			}
			else
			{
				cleanLinkAmount(e);
			}
		}

		/**
		 *
		 * @param e
		 */
		void cleanLinkAmount(final KElement e)
		{
			final JDFResourceLink link = (JDFResourceLink) e;
			final JDFAmountPool ap = link.getAmountPool();
			final JDFPartAmount partAmount = ap == null ? null : ap.getPartAmount(0);
			if (partAmount != null && ap.getPartAmount(1) == null)
			{
				final VJDFAttributeMap linkMaps = link.getPartMapVector();
				final VJDFAttributeMap paMaps = partAmount.getPartMapVector();
				if (linkMaps != null && linkMaps.equals(paMaps))
				{
					link.copyAttribute(AttributeName.AMOUNT, partAmount);
					link.copyAttribute(AttributeName.ACTUALAMOUNT, partAmount);
					link.copyAttribute(AttributeName.MAXAMOUNT, partAmount);
					ap.deleteNode();
				}
			}
		}
	}

	/**
	 * move stuff from delivery params to deliveryintent and or artdeliveryintent
	 */
	private void fixDelivery()
	{
		final JDFDeliveryParams dp = (JDFDeliveryParams) theNode.getResource(ElementName.DELIVERYPARAMS, EnumUsage.Input, 0);
		final VString allTypes = theNode.getAllTypes();
		if (dp != null && (allTypes.contains(XJDFConstants.Product) || dp.getChildWithAttribute(ElementName.PART, AttributeName.PRODUCTPART, null, "*", 0, false) != null))
		{
			boolean keepDI = theNode.getResource(ElementName.DELIVERYINTENT, EnumUsage.Input, 0) != null;
			final JDFDeliveryIntent di = (JDFDeliveryIntent) theNode.getCreateResource(ElementName.DELIVERYINTENT, EnumUsage.Input, 0);
			keepDI = di.setFromDeliveryParams(dp) || keepDI;
			if (!keepDI)
				di.deleteNode();

			boolean keepADI = theNode.getResource(ElementName.ARTDELIVERYINTENT, EnumUsage.Input, 0) != null;
			final JDFArtDeliveryIntent adi = (JDFArtDeliveryIntent) theNode.getCreateResource(ElementName.ARTDELIVERYINTENT, EnumUsage.Input, 0);
			keepADI = adi.setFromDeliveryParams(dp) || keepADI;
			if (!keepADI)
				adi.deleteNode();

			dp.deleteNode();
		}
	}

	class ProcessCleaner
	{

		/**
		 * @param theNode
		 */
		void cleanProcesses()
		{
			final VString types = theNode.getAllTypes();
			if (getFixType(types) != null)
			{
				cleanProcess(getFixType(types));
			}
		}

		private void cleanProcess(final String fixType)
		{
			final String resName = getRes(fixType);
			final EnumUsage usage = getUsage(fixType);
			if (theNode.getLink(resName, usage, null) == null)
			{
				final VElement v = theNode.getChildrenByTagName(resName, null, null, false, true, 0);
				if (v != null)
				{
					for (final KElement e : v)
					{
						final JDFResource r = (JDFResource) e;
						if (r.isResourceRootRoot())
						{
							theNode.ensureLink(r, usage, null);
						}
					}
				}
			}

		}

		private String getRes(final String fixType)
		{
			if (EnumType.DieLayoutProduction.getName().equals(fixType))
			{
				return ElementName.SHAPEDEF;
			}
			return null;
		}

		private EnumUsage getUsage(final String fixType)
		{
			if (EnumType.DieLayoutProduction.getName().equals(fixType))
			{
				return EnumUsage.Input;
			}
			return null;
		}

		private String getFixType(final VString types)
		{
			if (types != null && types.contains(EnumType.DieLayoutProduction.getName()))
			{
				return EnumType.DieLayoutProduction.getName();
			}
			return null;
		}
	}

	/**
	 *
	 * @author rainer prosi
	 *
	 */
	class ResourceCleaner
	{
		/**
		 *
		 */
		void cleanResources()
		{
			VElement vRes = collectResources();
			if (vRes != null)
			{
				for (final KElement rr : vRes)
				{
					splitDropID((JDFResource) rr);
				}
				vRes = collectResources();
				for (final KElement rr : vRes)
				{
					cleanResource(rr);
				}
			}
		}

		/**
		 *
		 *
		 * @param theNode
		 * @return
		 */
		private VElement collectResources()
		{
			JDFNode n = theNode;
			final VElement vRes = new VElement();
			while (n != null)
			{
				final JDFResourcePool rp = n.getResourcePool();
				final VElement v = rp == null ? null : rp.getPoolChildren(null, null, null);
				vRes.addAll(v);
				n = n.getParentJDF();
			}
			return vRes;
		}

		/**
		 *
		 *
		 * @param eRoot
		 */
		private void cleanResource(final KElement eRoot)
		{
			final JDFResource resRoot = (JDFResource) eRoot;
			if (resRoot != null)
			{
				final EnumResStatus s = resRoot.getStatusFromLeaves(false);
				if (s != null)
				{
					resRoot.setResStatus(s, false);
				}
				final String localName = resRoot.getLocalName();
				if (ElementName.COLORPOOL.equals(localName))
				{
					cleanColorPool(resRoot);
				}
				else if (ElementName.PAGELIST.equals(localName))
				{
					cleanPageList(resRoot);
				}
				else if (ElementName.NODEINFO.equals(localName))
				{
					updatePersonalID((JDFNodeInfo) resRoot);
				}
				else if (ElementName.BINDERYSIGNATURE.equals(localName))
				{
					cleanBinderySignature(resRoot);
				}
				cleanLeaf(resRoot, false);
				final List<JDFResource> leaves = resRoot.getLeafArray(true);
				for (final KElement leaf : leaves)
				{
					leaf.removeChildrenByClass(JDFPart.class);
				}
			}
		}

		void splitDropID(final JDFResource resRoot)
		{
			final VString partIDKeys = resRoot.getPartIDKeys();
			if (partIDKeys.contains(AttributeName.DROPID))
			{
				final VJDFAttributeMap partMapVector = resRoot.getPartMapVector(true);
				for (final int pos = partIDKeys.index(AttributeName.DROPID) + 1; pos < partIDKeys.size();)
				{
					partIDKeys.remove(pos);
				}
				partMapVector.reduceMap(partIDKeys);
				final VString vals = partMapVector.getPartValues(AttributeName.DROPID, false);
				final VElement copies = new VElement();
				copies.add(resRoot);
				final VElement vl = resRoot.getLinksAndRefs(true, false);
				final VElement vr = resRoot.getLinksAndRefs(false, true);
				for (int i = 1; i < vals.size(); i++)
				{
					copies.add(resRoot.getParentNode_KElement().copyElement(resRoot, resRoot));
				}
				for (int i = 0; i < copies.size(); i++)
				{
					splitSingleDrop(partMapVector, copies, vl, vr, i);
				}
			}
		}

		private void splitSingleDrop(final VJDFAttributeMap partMapVector, final VElement copies, final VElement vl, final VElement vr, int i)
		{
			final JDFResource newRoot = (JDFResource) copies.get(i);
			final JDFAttributeMap map = partMapVector.remove(0);
			final String currentDrop = map.get(AttributeName.DROPID);
			final JDFResource leaf = newRoot.getPartition(map, EnumPartUsage.Explicit);
			String id = newRoot.getID();
			if (i > 0)
			{
				id += map.get(AttributeName.DROPID);
				newRoot.setID(id);
			}
			if (vl != null)
			{
				copyLinks(partMapVector, vl, i, currentDrop, id);
			}
			if (vr != null)
			{
				copyRefs(vr, currentDrop, id);
			}

			for (final JDFAttributeMap map2 : partMapVector)
			{
				newRoot.getPartition(map2, EnumPartUsage.Explicit).deleteNode();
			}
			for (int j = i + 1; j < copies.size(); j++)
			{
				((JDFResource) copies.get(j)).getPartition(map, EnumPartUsage.Explicit).deleteNode();
			}

			leaf.removeAttribute(AttributeName.DROPID);
			leaf.getParentNode_KElement().copyInto(leaf, false);
			leaf.deleteNode();
			final VString partIDKeys2 = newRoot.getPartIDKeys();
			partIDKeys2.remove(AttributeName.DROPID);
			newRoot.setPartIDKeys(partIDKeys2);
		}

		private void copyRefs(final VElement vr, final String currentDrop, String id)
		{
			for (final KElement r : vr)
			{
				final JDFRefElement ref = (JDFRefElement) r;
				final JDFAttributeMap rMap = ref.getPartMap();

				if (!JDFAttributeMap.isEmpty(rMap) && currentDrop.equals(rMap.get(AttributeName.DROPID)))
				{
					rMap.remove(AttributeName.DROPID);
					ref.setPartMap(rMap);
					ref.setrRef(id);
				}
			}
		}

		private void copyLinks(final VJDFAttributeMap partMapVector, final VElement vl, int i, final String currentDrop, String id)
		{
			for (final KElement l : vl)
			{
				final JDFResourceLink link = (JDFResourceLink) l;
				final VJDFAttributeMap vlMap = link.getPartMapVector();

				if (!VJDFAttributeMap.isEmpty(vlMap) && currentDrop.equals(vlMap.getCommonMap().get(AttributeName.DROPID)))
				{
					vlMap.removeKey(AttributeName.DROPID);
					link.setPartMapVector(vlMap);
					link.setrRef(id);
				}
				else if (VJDFAttributeMap.isEmpty(vlMap) && i == 0)
				{
					for (final JDFAttributeMap map2 : partMapVector)
					{
						final String id2 = id + map2.get(AttributeName.DROPID);
						link.getParentNode_KElement().copyElement(link, null).setAttribute(AttributeName.RREF, id2);
					}
				}
			}
		}

		void updatePersonalID(final JDFNodeInfo ni)
		{
			final String pi = ni.getNonEmpty(AttributeName.PERSONALID);
			if (pi != null)
			{
				final JDFEmployee e = theNode.getChildWithAttribute(JDFEmployee.class, AttributeName.PERSONALID, pi, true);
				if (e != null && !ni.equals(e.getParentNode()))
				{
					ni.copyElement(e, null);
					ni.removeAttribute(AttributeName.PERSONALID);
				}
			}

		}

		private void cleanBinderySignature(final JDFResource bsRoot)
		{
			final JDFResource strippingParams = theNode.getResource(ElementName.STRIPPINGPARAMS, null, 0);
			if (strippingParams != null)
			{
				final List<JDFResource> spLeaves = strippingParams.getLeafArray(false);
				final Collection<String> moved = new HashSet<>();
				for (final JDFResource sp : spLeaves)
				{
					final JDFResource bs = bsRoot.getPartition(sp.getPartMap(), EnumPartUsage.Implicit);
					moved.addAll(moveToStripping(bs, sp));
				}
				if (!moved.isEmpty())
				{
					for (final JDFResource sp : spLeaves)
					{
						final JDFBinderySignature bs = (JDFBinderySignature) bsRoot.getPartition(sp.getPartMap(), EnumPartUsage.Implicit);
						final Collection<JDFSignatureCell> scs = bs.getAllSignatureCell();
						for (final JDFSignatureCell sc : scs)
						{
							sc.removeAttributes(moved);
							if (JDFAttributeMap.isEmpty(sc.getAttributeMap()))
							{
								sc.deleteNode();
							}
						}
					}
				}
				cleanidenticals(bsRoot, strippingParams);
			}

		}

		void cleanidenticals(final JDFResource bsRoot, final JDFResource spRoot)
		{
			final List<JDFIdentical> ids = bsRoot.getChildArrayByClass(JDFIdentical.class, true, 0);
			if (!ContainerUtil.isEmpty(ids))
			{
				final List<JDFResource> spLeaves = spRoot.getLeafArray(false);
				for (final JDFIdentical id : ids)
				{
					final JDFResource parentResource = id.getParentResource();
					final JDFAttributeMap src = parentResource.getPartMap();
					final String srcBS = src == null ? null : src.get(AttributeName.BINDERYSIGNATURENAME);
					final JDFAttributeMap trg = id.getPartMap();
					final String trgBS = trg == null ? null : trg.get(AttributeName.BINDERYSIGNATURENAME);
					if (srcBS != null && trgBS != null)
					{
						for (final JDFResource sp : spLeaves)
						{
							if (srcBS.equals(sp.getBinderySignatureName()))
							{
								final JDFRefElement re = (JDFRefElement) sp.getElement_KElement("BinderySignatureRef", null, 0);
								final JDFPart p = re == null ? null : re.getPart();
								if (p != null)
								{
									p.setBinderySignatureName(trgBS);
								}
							}
						}
					}
					parentResource.deleteNode();
				}
			}

		}

		Collection<String> moveToStripping(final JDFResource bs, final JDFResource sp)
		{
			final List<KElement> vsc = bs.getChildArray_KElement(ElementName.SIGNATURECELL, null, null, false, 0);
			final VJDFAttributeMap tmp = new VJDFAttributeMap();
			if (!ContainerUtil.isEmpty(vsc))
			{
				for (final KElement sc : vsc)
				{
					tmp.add(moveToStripCell(sc, sp));
				}
			}
			return tmp.getKeys();
		}

		/**
		 *
		 * @param sp
		 * @param bs
		 * @return
		 */
		JDFAttributeMap moveToStripCell(final KElement signatureCell, final JDFResource sp)
		{
			final JDFStripCellParams stripCell = (JDFStripCellParams) sp.appendElement(ElementName.STRIPCELLPARAMS);
			final VString stripCellKnown = stripCell.knownAttributes();
			final JDFAttributeMap sigCelMap = signatureCell.getAttributeMap();
			sigCelMap.reduceMap(stripCellKnown);
			if (sigCelMap.isEmpty())
			{
				stripCell.deleteNode();
			}
			else
			{
				for (final String key : sigCelMap.keySet())
				{
					stripCell.copyAttribute(key, signatureCell);
				}
			}
			return sigCelMap;
		}

		private void cleanLeaf(final KElement elem, final boolean cleanMe)
		{
			if (cleanMe)
			{
				elem.removeAttribute_KElement(AttributeName.CLASS, null);
			}

			KElement e2 = elem.getFirstChildElement();
			while (e2 != null)
			{
				cleanLeaf(e2, true);
				e2 = e2.getNextSiblingElement();
			}
		}

		private void cleanPageList(final JDFResource r)
		{
			final String id = r.getID();
			final JDFPageList pl = (JDFPageList) r;
			final Collection<JDFPageData> vpd = pl.getAllPageData();
			if (vpd != null)
			{
				for (final JDFPageData pd : vpd)
				{
					pd.removeChildrenByClass(JDFPart.class);
				}
			}

			if (StringUtil.getNonEmpty(id) != null)
			{
				final VElement v = theNode.getRoot().getChildrenByTagName_KElement(null, null, new JDFAttributeMap(AttributeName.RREF, id), false, false, 0);
				if (v != null)
				{
					for (final KElement e : v)
					{
						final String name = e.getLocalName();
						if ("ContentRef".equals(name))
						{
							e.renameElement("PageListRef", null);
							if (e.getParentNode_KElement() instanceof JDFLayoutElementProductionParams)
							{
								fixLayoutElementProductionParams(e.getParentNode_KElement());
							}
						}
						else if ("ContentLink".equals(name))
						{
							e.renameElement("PageListLink", null);
						}
					}
				}
			}
		}

		private void fixLayoutElementProductionParams(final KElement lopp)
		{
			final JDFPageList pl = (JDFPageList) lopp.getElement(ElementName.PAGELIST);
			final Collection<JDFPageData> vpd = pl.getAllPageData();
			if (vpd != null)
			{
				for (final JDFPageData pd : vpd)
				{
					final KElement ren = lopp.moveElement(pd, null).renameElement(ElementName.LAYOUTELEMENTPART, null);
					final KElement el = lopp.copyElement(ren, ren);
					final KElement fs = el.getElement(ElementName.FILESPEC);
					if (fs != null)
					{
						el.appendElement(ElementName.LAYOUTELEMENT).moveElement(fs, null);
					}
					ren.deleteNode();
				}
			}
			lopp.getElement("PageListRef").deleteNode();
		}

		/**
		 *
		 *
		 * @param r
		 */
		private void cleanColorPool(final JDFResource r)
		{
			final String id = r.getID();
			if (StringUtil.getNonEmpty(id) != null)
			{
				final VElement v = theNode.getRoot().getChildrenByTagName_KElement(null, null, new JDFAttributeMap("rRef", id), false, false, 0);
				if (v != null)
				{
					for (final KElement e : v)
					{
						final String name = e.getLocalName();
						if ("ColorRef".equals(name))
						{
							e.renameElement("ColorPoolRef", null);
						}
						else if ("ColorLink".equals(name))
						{
							e.renameElement("ColorPoolLink", null);
						}
						else if ("ContentRef".equals(name))
						{
							e.renameElement("PageListRef", null);
						}
						else if ("ContentLink".equals(name))
						{
							e.renameElement("PageListLink", null);
						}
					}
				}
			}
		}

	}

	private class DependencyCleaner
	{
		/**
		 *
		 * @param root
		 */
		private void fixDependencies(final JDFNode root)
		{
			final List<JDFDependencies> vDep = root.getChildArrayByClass(JDFDependencies.class, true, 0);
			if (vDep == null)
				return;
			for (final JDFDependencies dep : vDep)
			{
				fixOneDependencies(dep);
			}

		}

		/**
		 *
		 * @param dep
		 */
		private void fixOneDependencies(final JDFDependencies dep)
		{
			if (dep == null)
				return;
			final List<KElement> v = dep.getChildArray_KElement("RunListRef", null, null, true, 0);
			if (v == null)
				return;
			for (final KElement e : v)
			{
				final JDFRefElement rl = (JDFRefElement) e;
				rl.renameElement("LayoutElementRef", null);
				final JDFResource root = rl.getTargetRoot();
				if (root != null)
				{
					final List<JDFResource> vR = root.getLeafArray(true);
					VElement v2 = root.getLinksAndRefs(true, false);
					if (v2 != null)
					{
						for (final KElement rl2 : v2)
						{
							rl2.renameElement("LayoutElementLink", null);
						}
					}
					v2 = root.getLinksAndRefs(false, true);
					if (v2 != null)
					{
						for (final KElement rl2 : v2)
						{
							rl2.renameElement("LayoutElementRef", null);
						}
					}
					for (final KElement r : vR)
					{
						final JDFLayoutElement loe = (r instanceof JDFRunList) ? ((JDFRunList) r).getLayoutElement() : null;
						if (loe != null)
						{
							final Collection<KElement> v3 = loe.getChildArray_KElement(null, null, null, true, 0);
							r.moveArray(v3, null);
							r.setAttributes(loe);
							loe.deleteNode();
						}
						r.renameElement(ElementName.LAYOUTELEMENT, null);
					}
				}
			}
		}

	}

	@Override
	public String toString()
	{
		return "PostConverter [theNode=" + theNode + "]";
	}

	/**
	 * @param childNode
	 * @param parentProduct
	 * @param resName
	 * @param enumUsage
	 */
	private JDFResource mergeProductLink(final JDFNode childNode, final JDFNode parentProduct, final String resName, final EnumUsage enumUsage)
	{
		JDFResource r = parentProduct.getResource(resName, enumUsage, 0);
		int n = 0;
		while (r == null)
		{
			final JDFResourceLink link = childNode.getLink(n, resName, new JDFAttributeMap(AttributeName.USAGE, enumUsage), null);
			if (link == null)
			{
				break;
			}
			else if (link.getCombinedProcessIndex() == null)
			{
				r = link.getLinkRoot();
				parentProduct.ensureLink(r, enumUsage, null);
			}
			n++;
		}
		return r;
	}

	/**
	 * @param childNode
	 * @param parentProduct
	 */
	void mergeProductLinks(final JDFNode childNode, final JDFNode parentProduct)
	{
		if (childNode == parentProduct)
			return;

		mergeProductLink(childNode, parentProduct, ElementName.CUSTOMERINFO, EnumUsage.Input);
		final JDFResource ni = mergeProductLink(childNode, parentProduct, ElementName.NODEINFO, EnumUsage.Input);
		if (ni == null)
		{
			parentProduct.appendNodeInfo().setDescriptiveName("Generated root NodeInfo");
		}
		final JDFResource r = parentProduct.getResource(ElementName.COMPONENT, EnumUsage.Output, 0);
		if (r != null && XJDFToJDFImpl.PRODUCT_NAME.equals(r.getDescriptiveName()))
		{
			final JDFResource rNode = childNode.getResource(ElementName.COMPONENT, EnumUsage.Output, 0);
			if (rNode != null)
			{
				parentProduct.getLink(r, EnumUsage.Output).deleteNode();
				r.deleteNode();
			}
		}
		mergeProductLink(childNode, parentProduct, ElementName.COMPONENT, EnumUsage.Output);
	}
}