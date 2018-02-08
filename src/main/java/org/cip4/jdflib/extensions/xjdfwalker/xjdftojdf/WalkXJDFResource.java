/**
 * The CIP4 Software License, Version 1.0
 *
 * Copyright (c) 2001-2018 The International Cooperation for the Integration of
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
package org.cip4.jdflib.extensions.xjdfwalker.xjdftojdf;

import java.util.Vector;

import org.cip4.jdflib.core.AttributeName;
import org.cip4.jdflib.core.ElementName;
import org.cip4.jdflib.core.JDFPartAmount;
import org.cip4.jdflib.core.JDFResourceLink;
import org.cip4.jdflib.core.JDFResourceLink.EnumUsage;
import org.cip4.jdflib.core.KElement;
import org.cip4.jdflib.core.VString;
import org.cip4.jdflib.datatypes.JDFAttributeMap;
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
import org.cip4.jdflib.resource.JDFResource.EnumResStatus;
import org.cip4.jdflib.resource.JDFResource.EnumResourceClass;
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
			JDFResourceLink rl = null;
			if (theNode != null)
			{
				theNode.getLink(newPartition, null);
				rl = ensureLink(theNode, newPartition, rl);
			}
			final JDFAttributeMap partMap = vParts.get(0);
			handleIdentical(vParts, res, rl);
			handleAmountPool(xjdfRes, partMap, map, rl);
		}
		newPartitionElement.setAttributes(map);
		return newPartitionElement;
	}

	private void handleIdentical(final VJDFAttributeMap vParts, final JDFResource res, final JDFResourceLink rl)
	{
		if (vParts.size() > 1)
		{
			final JDFAttributeMap targetMap = vParts.get(0);
			for (int i = 1; i < vParts.size(); i++)
			{
				final JDFAttributeMap partMap = vParts.get(i);
				final JDFResource rPart = res.getCreatePartition(partMap, JDFPart.guessPartIDKeys(partMap));
				rPart.appendIdentical().setPartMap(targetMap);
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
		final Vector<JDFPart> parts = rh.getRoot().getChildrenByClass(JDFPart.class, false, 0);
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
		if (res == null)
		{
			final boolean combine = !StringUtil.equals(id, sh.getID());
			res = combine ? theNode.getResource(name, inOut, processUsage, null, 0) : null;
			if (res == null)
			{
				isNew = true;
				res = theNode.addResource(name, null);
				res.setID(id);
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
				rl.copyAttribute(AttributeName.COMBINEDPROCESSINDEX, sh.getRoot());
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
			if (isNew && EnumUsage.Input.equals(inOut)
					&& (EnumResourceClass.Parameter.equals(resClass) || EnumResourceClass.Consumable.equals(resClass) || EnumResourceClass.Intent.equals(resClass)))
			{
				res.setResStatus(EnumResStatus.Available, false);
			}

		}
		return res;
	}

	EnumUsage getLinkUsage(final JDFNode theNode, final SetHelper sh, final String name, final String processUsage)
	{
		EnumUsage inOut = sh.getUsage();
		if (inOut == null && xjdfToJDFImpl.isHeuristicLink())
		{
			if (!ElementName.CONTACT.equals(name) && !ElementName.LAYOUTELEMENT.equals(name) && !ElementName.RUNLIST.equals(name) && !ElementName.COMPONENT.equals(name)
					&& !ElementName.COLORPOOL.equals(name) && !ElementName.MEDIA.equals(name) && !ElementName.EXPOSEDMEDIA.equals(name)
					&& theNode.isValidLink(name, EnumUsage.Input, processUsage))
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
	private void handleAmountPool(final KElement xjdfRes, final JDFAttributeMap partmap, final JDFAttributeMap map, final JDFResourceLink rl)
	{
		final KElement ap = xjdfRes.getElement(ElementName.AMOUNTPOOL);
		if (ap != null)
		{
			final KElement newAmountPool = rl.getCreateAmountPool();
			final Vector<JDFPartAmount> vpa = ap.getChildrenByClass(JDFPartAmount.class, false, 0);
			for (final JDFPartAmount pa : vpa)
			{
				xjdfToJDFImpl.walkTree(pa, newAmountPool);
			}
			ap.deleteNode();
		}
		xjdfToJDFImpl.moveAmountsToLink(partmap, map, rl);
	}

	/**
	 *
	 * @param partialProductNode
	 * @param newPartition
	 * @param rl
	 * @return
	 */
	private JDFResourceLink ensureLink(final JDFNode partialProductNode, final JDFResource newPartition, JDFResourceLink rl)
	{
		if (partialProductNode != null)
		{
			JDFResourceLink rlpart = partialProductNode.getLink(newPartition, null);
			final EnumUsage newUsage = rl == null ? null : rl.getUsage();
			if (rlpart == null && newUsage != null)
			{
				rlpart = partialProductNode.ensureLink(newPartition, newUsage, null);
			}
			if (rlpart != null)
			{
				rl = rlpart;
			}

		}
		return rl;
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
		return jdfRes.getCreatePartition(partMap, JDFPart.guessPartIDKeys(partMap));
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