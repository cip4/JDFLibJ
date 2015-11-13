/**
 * The CIP4 Software License, Version 1.0
 *
 * Copyright (c) 2001-2015 The International Cooperation for the Integration of 
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
import org.cip4.jdflib.core.JDFElement;
import org.cip4.jdflib.core.JDFPartAmount;
import org.cip4.jdflib.core.JDFResourceLink;
import org.cip4.jdflib.core.JDFResourceLink.EnumUsage;
import org.cip4.jdflib.core.KElement;
import org.cip4.jdflib.core.VString;
import org.cip4.jdflib.datatypes.JDFAttributeMap;
import org.cip4.jdflib.datatypes.VJDFAttributeMap;
import org.cip4.jdflib.extensions.PartitionHelper;
import org.cip4.jdflib.extensions.SetHelper;
import org.cip4.jdflib.extensions.XJDFConstants;
import org.cip4.jdflib.extensions.xjdfwalker.XJDFToJDFConverter;
import org.cip4.jdflib.node.JDFNode;
import org.cip4.jdflib.resource.JDFPart;
import org.cip4.jdflib.resource.JDFResource;
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
	public KElement walk(final KElement xjdfRes, final KElement jdfNode)
	{
		JDFNode theNode = getNode(xjdfRes, jdfNode);
		JDFResource res = getResourceRoot(theNode, xjdfRes);
		final JDFPart part = (JDFPart) xjdfRes.getElement(ElementName.PART);
		KElement newPartitionElement = createPartition(xjdfRes, res, part, theNode);
		if (newPartitionElement == null)
		{
			return null;
		}
		final JDFAttributeMap map = getResMap(xjdfRes);

		if (newPartitionElement instanceof JDFResource)
		{
			JDFResource newPartition = (JDFResource) newPartitionElement;
			JDFResourceLink rl = theNode.getLink(newPartition, null);
			rl = ensureLink(theNode, newPartition, rl);
			final JDFAttributeMap partMap = getPartMap(part);
			handleAmountPool(xjdfRes, partMap, map, rl);
		}
		newPartitionElement.setAttributes(map);

		return newPartitionElement;
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
	private JDFResource getResourceRoot(JDFNode theNode, KElement xjdfRes)
	{
		JDFNode newRoot = theNode.getJDFRoot();
		PartitionHelper ph = new PartitionHelper(xjdfRes);
		SetHelper sh = ph.getSet();
		final String name = getJDFResName(sh);
		EnumUsage inOut = sh.getUsage();
		String processUsage = sh.getProcessUsage();
		if (inOut == null && xjdfToJDFImpl.isHeuristicLink())
		{
			if (!ElementName.CONTACT.equals(name) && !ElementName.LAYOUTELEMENT.equals(name) && !ElementName.RUNLIST.equals(name) && !ElementName.COMPONENT.equals(name)
					&& !ElementName.COLORPOOL.equals(name) && !ElementName.MEDIA.equals(name) && !ElementName.EXPOSEDMEDIA.equals(name)
					&& theNode.isValidLink(name, EnumUsage.Input, processUsage))
			{
				inOut = EnumUsage.Input;
			}
		}
		String id = xjdfToJDFImpl.idMap.get(xjdfRes.getID()).getID();
		JDFResource res = (JDFResource) newRoot.getCreateResourcePool().getChildWithAttribute(null, AttributeName.ID, null, id, 0, true);
		if (res == null)
		{
			res = theNode.getCreateResource(name, inOut, processUsage);
			if (theNode != newRoot)
			{
				newRoot.getCreateResourcePool().moveElement(res, null);
			}
			if (inOut != null)
			{
				final JDFResourceLink rl = theNode.getLink(res, inOut);
				rl.setrRef(id);
				res.removeAttribute(AttributeName.USAGE);
				VString reslinks = XJDFToJDFConverter.getResLinkAttribs();
				for (String key : reslinks)
				{
					if (res.hasAttribute(key))
					{
						rl.moveAttribute(key, res);
					}
				}
			}
			res.setID(id);
		}

		return res;
	}

	/**
	 * @param xjdfRes
	 * @return the created resource
	 */
	public KElement oldwalk(final KElement xjdfRes, final KElement jdfResource)
	{
		JDFNode theNode = xjdfToJDFImpl.currentJDFNode == null ? ((JDFElement) jdfResource).getParentJDF() : xjdfToJDFImpl.currentJDFNode;
		final JDFPart part = (JDFPart) xjdfRes.getElement(ElementName.PART);
		JDFAttributeMap partmap = null;
		final KElement newPartitionElement;
		JDFNode partialProductNode = null;
		if (part != null)
		{
			newPartitionElement = createPartition(xjdfRes, (JDFResource) jdfResource, part, theNode);
			partmap = part.getPartMap();

			String productPart = partmap == null ? null : StringUtil.getNonEmpty(partmap.get(AttributeName.PRODUCTPART));
			partialProductNode = productPart == null ? null : theNode.getChildJDFNode(productPart, false);
		}
		else if (xjdfRes.getPreviousSiblingElement(xjdfRes.getNodeName(), null) != null)
		{
			newPartitionElement = theNode.getJDFRoot().addResource(jdfResource.getLocalName(), null);
			newPartitionElement.copyAttribute(AttributeName.ID, xjdfRes);
		}
		else
		{
			newPartitionElement = jdfResource;
		}
		if (newPartitionElement == null)
		{
			return null;
		}

		final JDFAttributeMap map = getResMap(xjdfRes);

		if (newPartitionElement instanceof JDFResource)
		{
			JDFResource newPartition = (JDFResource) newPartitionElement;
			JDFResourceLink rl = theNode.getLink(newPartition, null);
			rl = ensureLink(partialProductNode, newPartition, rl);
			handleAmountPool(xjdfRes, partmap, map, rl);
		}
		newPartitionElement.setAttributes(map);

		return newPartitionElement;
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
	private void handleAmountPool(final KElement xjdfRes, JDFAttributeMap partmap, final JDFAttributeMap map, JDFResourceLink rl)
	{
		KElement ap = xjdfRes.getElement(ElementName.AMOUNTPOOL);
		if (ap != null)
		{
			KElement newAmountPool = rl.getCreateAmountPool();
			Vector<JDFPartAmount> vpa = ap.getChildrenByClass(JDFPartAmount.class, false, 0);
			for (JDFPartAmount pa : vpa)
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
	private JDFResourceLink ensureLink(JDFNode partialProductNode, JDFResource newPartition, JDFResourceLink rl)
	{
		JDFResourceLink rlpart;
		if (partialProductNode != null)
		{
			rlpart = partialProductNode.getLink(newPartition, null);
			EnumUsage newUsage = rl == null ? null : rl.getUsage();
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
	 * @param theNode
	 * @param xjdfRes
	 * @param jdfRes
	 * @param part
	 * @return
	 */
	protected KElement createPartition(final KElement xjdfRes, final JDFResource jdfRes, final JDFPart part, JDFNode theNode)
	{
		if (part == null)
		{
			return jdfRes;
		}
		final JDFAttributeMap partMap = getPartMap(part);
		final JDFResource rPart = jdfRes.getCreatePartition(partMap, JDFPart.guessPartIDKeys(partMap));
		final JDFResourceLink rll = theNode.getLink(jdfRes, null);
		final VJDFAttributeMap partMapVector = rll != null ? rll.getPartMapVector() : null;
		if (rll != null && (partMapVector == null || !partMapVector.contains(partMap)))
		{
			rll.appendPart().setPartMap(partMap);
			//			part.deleteNode();
		}
		return rPart;
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
		final JDFAttributeMap p = part == null ? null : part.getPartMap();
		if (p != null)
		{
			String sheetName = p.get(AttributeName.SHEETNAME);
			String signatureName = p.get(AttributeName.SIGNATURENAME);
			if (StringUtil.getNonEmpty(sheetName) != null && StringUtil.getNonEmpty(signatureName) == null)
			{
				signatureName = "Sig_" + sheetName;
				p.put(AttributeName.SIGNATURENAME, signatureName);
				part.setSignatureName(signatureName);
			}
			p.remove(AttributeName.PRODUCTPART);
			p.remove(XJDFConstants.ProcessTypes);
		}
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
		return super.matches(toCheck) && PartitionHelper.isAsset(toCheck);
	}
}