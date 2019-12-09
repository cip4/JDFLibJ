/**
 * The CIP4 Software License, Version 1.0
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
package org.cip4.jdflib.extensions.xjdfwalker.xjdftojdf;

import java.util.List;
import java.util.Vector;

import org.cip4.jdflib.core.AttributeName;
import org.cip4.jdflib.core.ElementName;
import org.cip4.jdflib.core.JDFConstants;
import org.cip4.jdflib.core.JDFElement;
import org.cip4.jdflib.core.JDFPartAmount;
import org.cip4.jdflib.core.JDFResourceLink;
import org.cip4.jdflib.core.JDFResourceLink.EnumUsage;
import org.cip4.jdflib.core.KElement;
import org.cip4.jdflib.core.StringArray;
import org.cip4.jdflib.core.VString;
import org.cip4.jdflib.datatypes.JDFAttributeMap;
import org.cip4.jdflib.datatypes.JDFNameRange;
import org.cip4.jdflib.datatypes.JDFNameRangeList;
import org.cip4.jdflib.datatypes.VJDFAttributeMap;
import org.cip4.jdflib.extensions.ResourceHelper;
import org.cip4.jdflib.extensions.SetHelper;
import org.cip4.jdflib.extensions.XJDFConstants;
import org.cip4.jdflib.extensions.xjdfwalker.XJDFToJDFConverter;
import org.cip4.jdflib.jmf.JDFResourceInfo;
import org.cip4.jdflib.node.JDFNode;
import org.cip4.jdflib.resource.JDFPart;
import org.cip4.jdflib.resource.JDFResource;
import org.cip4.jdflib.resource.JDFResource.EnumPartIDKey;
import org.cip4.jdflib.resource.JDFResource.EnumPartUsage;
import org.cip4.jdflib.resource.JDFResource.EnumResStatus;
import org.cip4.jdflib.resource.JDFResource.EnumResourceClass;
import org.cip4.jdflib.resource.PartitionGetter;
import org.cip4.jdflib.util.ContainerUtil;
import org.cip4.jdflib.util.StringUtil;

/**
 * @author Rainer Prosi, Heidelberger Druckmaschinen walker for the various resource sets
 */
public class WalkXJDFResource extends WalkXElement
{
	/**
	 *
	 */
	public WalkXJDFResource()
	{
		super();
	}

	/**
	 * @param xjdfRes
	 * @return the created resource
	 */
	@Override
	public KElement walk(final KElement xjdfRes, final KElement parent)
	{
		final JDFNode theNode = getNode(xjdfRes, parent);
		final JDFResource res;
		if (theNode != null)
		{
			res = getResourceRoot(theNode, xjdfRes);
		}
		else if (parent instanceof JDFResourceInfo)
		{
			res = getResInfoRoot((JDFResourceInfo) parent, xjdfRes);
		}
		else
		{
			return null;
		}

		final ResourceHelper rh = ResourceHelper.getHelper(xjdfRes);
		if (rh == null)
		{
			return null;
		}
		rh.getCreateResource();
		final VJDFAttributeMap vParts = getPartMaps(rh);

		final KElement newPartitionElement = createPartition(res, vParts.get(0), theNode);
		if (newPartitionElement == null)
		{
			return null;
		}
		final JDFAttributeMap map = getResMap(xjdfRes);

		if (newPartitionElement instanceof JDFResource)
		{
			final JDFResource newPartition = (JDFResource) newPartitionElement;
			JDFElement rl = null;
			if (theNode != null)
			{
				rl = ensureLink(theNode, newPartition);
			}
			else if (parent instanceof JDFResourceInfo)
			{
				rl = (JDFResourceInfo) parent;
			}
			final JDFAttributeMap partMap = vParts.get(0);
			handleParts(newPartition, vParts, rl);
			handleAmountPool(xjdfRes, partMap, map, rl);
			if (rl != null)
			{
				rl.setAttribute(AttributeName.ORIENTATION, map.remove(AttributeName.ORIENTATION));
				rl.setAttribute(AttributeName.TRANSFORMATION, map.remove(AttributeName.TRANSFORMATION));
			}
		}
		newPartitionElement.setAttributes(map);
		return newPartitionElement;
	}

	/**
	 *
	 * @param res
	 * @param vParts
	 * @param rl
	 */
	protected void handleParts(final JDFResource res, final VJDFAttributeMap vParts, final JDFElement rl)
	{
		if (vParts.size() > 1)
		{
			for (final String key : indexKeys)
			{
				final VString vals = vParts.getPartValues(key, false);
				if (ContainerUtil.size(vals) > 1)
				{
					final VString resVals = new VString(vals);
					for (int i = 0; i < vals.size(); i++)
					{
						final String single = vals.get(i);
						if (StringUtil.token(single, 1, null) == null)
						{
							resVals.set(i, single + JDFConstants.SPACE + single);
						}
					}
					final String newVal = vals.getString();
					vParts.put(key, newVal);
					vParts.unify();
					if (res.hasAttribute(key))
						res.setAttribute(key, resVals.getString());
				}
			}
		}
		handleLinkParts(vParts, rl);
		handleIdentical(vParts, res.getResourceRoot());
	}

	private final static StringArray keepKeys = new StringArray("SignatureName SheetName Side PartVersion Separation BlockName Run DocIndex RunIndex SetIndex SheetIndex", null);
	private final static StringArray indexKeys = new StringArray("RunIndex DocIndex SetIndex SheetIndex", null);

	/**
	 *
	 * @param vParts
	 * @param rl
	 */
	protected void handleLinkParts(final VJDFAttributeMap vParts, final JDFElement rl)
	{
		if (rl != null && ContainerUtil.getNonEmpty(vParts) != null)
		{
			final VJDFAttributeMap clone = vParts.clone();
			clone.reduceMap(keepKeys);
			if (clone.containsKey(AttributeName.RUN))
			{
				for (final String index : indexKeys)
				{
					if (clone.containsKey(index))
					{
						clone.removeKey(AttributeName.RUN);
						break;
					}
				}
			}
			if (clone.size() > 1)
			{
				for (final String index : indexKeys)
				{
					final VString values = clone.getPartValues(index, false);
					if (values != null && values.size() > 1)
					{
						clone.removeKey(index);
						clone.put(index, StringUtil.setvString(values));
						if (clone.size() == 1)
						{
							break;
						}
					}
				}
			}
			clone.remove(new JDFAttributeMap());
			updatePartMaps(rl, clone);
		}
	}

	void updatePartMaps(final JDFElement rl, final VJDFAttributeMap clone)
	{
		VJDFAttributeMap oldParts = (rl instanceof JDFResourceLink) ? ((JDFResourceLink) rl).getPartMapVector() : ((JDFResourceInfo) rl).getPartMapVector();
		if (oldParts != null)
		{
			oldParts.appendUnique(clone);
		}
		else
		{
			oldParts = clone;
		}
		if (rl instanceof JDFResourceLink)
			((JDFResourceLink) rl).setPartMapVector(oldParts);
		else
			((JDFResourceInfo) rl).setPartMapVector(oldParts);
	}

	private void handleIdentical(final VJDFAttributeMap vParts, final JDFResource res)
	{
		if (vParts.size() > 1)
		{
			final JDFAttributeMap targetMap = vParts.get(0);
			for (int i = 1; i < vParts.size(); i++)
			{
				final JDFAttributeMap partMap = vParts.get(i);
				final PartitionGetter partitionGetter = new PartitionGetter(res);
				partitionGetter.setFollowIdentical(false);
				final JDFResource rPart = partitionGetter.getCreatePartition(partMap, JDFPart.guessPartIDKeys(partMap));

				rPart.getCreateIdentical().setPartMap(targetMap);
			}
		}

	}

	/**
	 *
	 * @param rh
	 * @return
	 */
	VJDFAttributeMap getPartMaps(final ResourceHelper rh)
	{
		final List<JDFPart> parts = rh.getRoot().getChildArrayByClass(JDFPart.class, false, 0);
		final VJDFAttributeMap vMap = new VJDFAttributeMap();
		for (final JDFPart part : parts)
		{
			final JDFAttributeMap partMap = getPartMap(part);
			vMap.add(partMap);
		}
		if (vMap.isEmpty())
			vMap.add(new JDFAttributeMap());
		vMap.unify();
		return vMap;
	}

	/**
	 *
	 * @param e
	 * @return
	 */
	protected String getJDFResName(final SetHelper sh)
	{
		final String name = sh.getName();
		return name;
	}

	/**
	 *
	 * @param theNode
	 * @param xjdfRes
	 * @return
	 */
	private JDFResource getResourceRoot(final JDFNode theNode, final KElement xjdfRes)
	{
		final JDFNode newRoot = theNode.getJDFRoot();
		final ResourceHelper ph = new ResourceHelper(xjdfRes);
		final SetHelper sh = ph.getSet();
		final String name = getJDFResName(sh);
		final String processUsage = sh.getProcessUsage();
		final EnumUsage inOut = getLinkUsage(theNode, sh, name, processUsage);
		final String id = xjdfToJDFImpl.idMap.get(xjdfRes.getID()).getID();
		JDFResource res = (JDFResource) newRoot.getCreateResourcePool().getChildWithAttribute(null, AttributeName.ID, null, id, 0, true);
		boolean isNew = false;
		final KElement setRoot = sh.getRoot();
		if (res == null)
		{
			final boolean combine = !StringUtil.equals(id, sh.getID());
			res = combine ? theNode.getResource(name, inOut, processUsage, null, 0) : null;
			if (res == null)
			{
				isNew = true;
				res = createNewResource(theNode, name, id, setRoot);
			}
			if (theNode != newRoot)
			{
				newRoot.getCreateResourcePool().moveElement(res, null);
			}
		}
		if (inOut != null)
		{
			JDFResourceLink rl = theNode.getLink(res, inOut);
			if (rl == null)
			{
				rl = theNode.ensureLinkPU(res, inOut, processUsage);
				rl.copyAttribute(AttributeName.COMBINEDPROCESSINDEX, setRoot);
				rl.setrRef(id);
				res.removeAttribute(AttributeName.USAGE);
				final VString reslinks = XJDFToJDFConverter.getResLinkAttribs();
				for (final String key : reslinks)
				{
					if (res.hasAttribute(key))
					{
						rl.moveAttribute(key, res);
					}
				}
			}
			// parameters and consumables are assumed to be available by default
			final EnumResourceClass resClass = res.getResourceClass();
			if (isNew && EnumUsage.Input.equals(inOut) && (EnumResourceClass.Parameter.equals(resClass) || EnumResourceClass.Consumable.equals(resClass) || EnumResourceClass.Intent.equals(resClass)))
			{
				res.setResStatus(EnumResStatus.Available, false);
			}

		}
		return res;
	}

	/**
	 *
	 * @param theNode
	 * @param name
	 * @param id
	 * @param setRoot
	 * @return
	 */
	protected JDFResource createNewResource(final JDFNode theNode, final String name, final String id, final KElement setRoot)
	{
		final JDFResource res = theNode.addResource(name, null);
		res.setID(id);
		res.copyAttribute(AttributeName.DESCRIPTIVENAME, setRoot);
		res.copyAttribute(AttributeName.COMMENTURL, setRoot);
		res.copyAttribute(AttributeName.UNIT, setRoot);
		return res;
	}

	EnumUsage getLinkUsage(final JDFNode theNode, final SetHelper sh, final String name, final String processUsage)
	{
		EnumUsage inOut = sh.getUsage();
		if (inOut == null && xjdfToJDFImpl.isHeuristicLink())
		{
			if (!ElementName.CONTACT.equals(name) && !ElementName.LAYOUTELEMENT.equals(name) && !ElementName.RUNLIST.equals(name) && !ElementName.COMPONENT.equals(name)
					&& !ElementName.COLORPOOL.equals(name) && !ElementName.MEDIA.equals(name) && !ElementName.EXPOSEDMEDIA.equals(name) && theNode.isValidLink(name, EnumUsage.Input, processUsage))
			{
				inOut = EnumUsage.Input;
			}
		}
		return inOut;
	}

	/**
	 *
	 * @param theNode
	 * @param xjdfRes
	 * @return
	 */
	private JDFResource getResInfoRoot(final JDFResourceInfo parent, final KElement xjdfRes)
	{
		final ResourceHelper ph = new ResourceHelper(xjdfRes);
		final SetHelper sh = ph.getSet();
		final String name = getJDFResName(sh);
		final String processUsage = sh.getProcessUsage();
		parent.setProcessUsage(processUsage);
		final EnumUsage inOut = sh.getUsage();
		parent.setUsage(inOut);
		parent.setResourceName(name);
		final JDFResource res = parent.getCreateResource(name);

		final VString reslinks = XJDFToJDFConverter.getResLinkAttribs();
		for (final String key : reslinks)
		{
			if (res.hasAttribute(key))
			{
				parent.moveAttribute(key, res);
			}
		}
		return res;

	}

	private JDFAttributeMap getResMap(final KElement xjdfRes)
	{
		final JDFAttributeMap map = xjdfRes.getAttributeMap();
		map.remove(AttributeName.ID);
		map.remove(AttributeName.PARTIDKEYS);
		return map;
	}

	/**
	 *
	 * @param xjdfRes
	 * @param partmap
	 * @param map
	 * @param rl
	 */
	private void handleAmountPool(final KElement xjdfRes, final JDFAttributeMap partmap, final JDFAttributeMap map, final JDFElement rl)
	{
		final KElement ap = xjdfRes.getElement(ElementName.AMOUNTPOOL);
		if (ap != null && rl != null)
		{
			final KElement newAmountPool = rl.getCreateElement(ElementName.AMOUNTPOOL);
			final Vector<JDFPartAmount> vpa = ap.getChildrenByClass(JDFPartAmount.class, false, 0);
			for (final JDFPartAmount pa : vpa)
			{
				VJDFAttributeMap vParts = pa.getPartMapVector();
				if (vParts == null)
				{
					if (!JDFAttributeMap.isEmpty(partmap))
					{
						vParts = new VJDFAttributeMap(partmap);
					}
				}
				else
				{
					vParts.put(partmap);
				}
				pa.setPartMapVector(vParts);
				xjdfToJDFImpl.walkTree(pa, newAmountPool);
			}
			ap.deleteNode();
		}
		if (rl instanceof JDFResourceLink)
		{
			xjdfToJDFImpl.moveAmountsToLink(partmap, map, (JDFResourceLink) rl);
		}
	}

	/**
	 *
	 * @param partialProductNode
	 * @param newPartition
	 * @param rl
	 * @return
	 */
	private JDFResourceLink ensureLink(final JDFNode partialProductNode, final JDFResource newPartition)
	{
		if (partialProductNode != null)
		{
			return partialProductNode.getLink(newPartition, null);
		}
		return null;
	}

	/**
	 *
	 * @param jdfRes
	 * @param partMap
	 * @param theNode
	 * @return
	 */
	protected KElement createPartition(final JDFResource jdfRes, final JDFAttributeMap partMap, final JDFNode theNode)
	{
		if (partMap == null || partMap.isEmpty())
		{
			return jdfRes;
		}
		final JDFAttributeMap reduced = removeImplicitParts(jdfRes, partMap);
		if (reduced == null || reduced.isEmpty())
		{
			return jdfRes;
		}
		try
		{
			final JDFResource p0 = jdfRes.getPartition(reduced, EnumPartUsage.Explicit);
			if (p0 != null)
			{
				// we don't redo existing resources that would duplicate lots of stuff.
				return null;
			}
			return jdfRes.getCreatePartition(reduced, JDFPart.guessPartIDKeys(reduced));
		}
		catch (final Exception x)
		{
			log.error("Cannot create partition for: " + jdfRes.getNodeName(), x);

		}
		return null;
	}

	protected JDFAttributeMap removeImplicitParts(final JDFResource jdfRes, final JDFAttributeMap partMap)
	{
		final Vector<EnumPartIDKey> implicitPartitions = jdfRes.getImplicitPartitions();
		if (implicitPartitions != null)
		{
			final JDFAttributeMap ret = partMap.clone();
			for (final EnumPartIDKey key : implicitPartitions)
			{
				ret.remove(key.getName());
			}
			return ret;
		}
		else
		{
			return partMap;
		}
	}

	/**
	 * ensure that we always have a SIGNATURENAME partition in case we have a SHEETNAME
	 *
	 * @param part the partmap
	 *
	 * @return
	 */
	JDFAttributeMap getPartMap(final JDFPart part)
	{
		final JDFAttributeMap p = part == null ? new JDFAttributeMap() : part.getAttributeMap();

		p.renameKey(XJDFConstants.BinderySignatureID, AttributeName.BINDERYSIGNATURENAME);
		final String sheetName = p.getNonEmpty(AttributeName.SHEETNAME);
		String signatureName = p.getNonEmpty(AttributeName.SIGNATURENAME);
		if (sheetName != null && signatureName == null)
		{
			signatureName = "Sig_" + sheetName;
			p.put(AttributeName.SIGNATURENAME, signatureName);
			part.setSignatureName(signatureName);
		}
		p.renameKey(AttributeName.METADATA, AttributeName.METADATA0);
		final VString keys = p.getKeys();
		for (final String key : keys)
		{
			if (EnumPartIDKey.getEnum(key) == null)
			{
				p.remove(key);
			}
			else if (key.endsWith("Index"))
			{
				final String val = p.getNonEmpty(key);
				if (val != null)
				{
					final VString v = new VString(val, null);
					if (v.size() % 2 == 0)
					{
						final JDFNameRangeList nrl = new JDFNameRangeList();
						for (int i = 0; i < v.size(); i += 2)
						{
							nrl.append(new JDFNameRange(v.get(i), v.get(i + 1)));
						}
						final String newVal = nrl.getString(0);
						p.put(key, newVal);
					}
				}
			}
		}
		p.remove(AttributeName.PRODUCTPART);
		p.remove(AttributeName.DROPID);
		p.remove(XJDFConstants.ContactType);
		return p;
	}

	/**
	 * @see org.cip4.jdflib.elementwalker.BaseWalker#matches(org.cip4.jdflib.core.KElement)
	 * @param toCheck
	 * @return true if it matches
	 */
	@Override
	public boolean matches(final KElement toCheck)
	{
		return super.matches(toCheck) && ResourceHelper.isAsset(toCheck);
	}
}