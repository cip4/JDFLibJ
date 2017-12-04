/**
 * The CIP4 Software License, Version 1.0
 *
 * Copyright (c) 2001-2017 The International Cooperation for the Integration of
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
package org.cip4.jdflib.node;

import java.util.Collection;
import java.util.Iterator;
import java.util.Vector;

import org.cip4.jdflib.core.AttributeName;
import org.cip4.jdflib.core.JDFConstants;
import org.cip4.jdflib.core.JDFElement;
import org.cip4.jdflib.core.JDFElement.EnumValidationLevel;
import org.cip4.jdflib.core.JDFResourceLink;
import org.cip4.jdflib.core.JDFResourceLink.EnumUsage;
import org.cip4.jdflib.core.KElement;
import org.cip4.jdflib.core.VElement;
import org.cip4.jdflib.core.VString;
import org.cip4.jdflib.datatypes.JDFAttributeMap;
import org.cip4.jdflib.node.JDFNode.EnumProcessUsage;
import org.cip4.jdflib.node.JDFNode.EnumType;
import org.cip4.jdflib.pool.JDFResourceLinkPool;
import org.cip4.jdflib.resource.JDFResource;
import org.cip4.jdflib.util.ContainerUtil;
import org.cip4.jdflib.util.StringUtil;

/**
 *implementation of the link validation routines
 * @author rainer prosi
 * @date May 27, 2014
 */
public class LinkValidator
{
	private final JDFNode node;
	private final LinkValidatorMap validatorMap;
	private LinkInfoMap theMap;

	/**
	 *
	 */
	LinkValidator(final JDFNode n)
	{
		this.node = n;
		validatorMap = LinkValidatorMap.getLinkValidatorMap();
		theMap = null;
	}

	/**
	 * definition of resource link names in the JDF namespace
	 *
	 * @return String list of resource names that may be linked
	 */
	VString linkNames()
	{
		final EnumType typ = EnumType.getEnum(node.getType());
		final VString vTypes = node.getTypes();
		return validatorMap.getLinkNames(typ, vTypes);
	}

	/**
	 * definition of resource link usage, cardinality and ProcessUsage in the JDF namespace
	 *
	 * @return String list of resource information usages that may be linked
	 */
	LinkInfoMap getLinkInfoMap()
	{
		if (theMap == null)
		{
			final EnumType typ = EnumType.getEnum(node.getType());
			final VString vTypes = node.getTypes();
			theMap = validatorMap.getLinkInfoMap(typ, vTypes);
		}
		return theMap;
	}

	/**
	 * typesafe validator utility
	 *
	 * @param level validation level
	 * @param nMax max. size of the returned vector
	 * @return vector of invalid Link names
	 *
	 * @default getInvalidLinks (ValidationLevel_Complete, Integer.MAX_VALUE)
	 */
	VString getInvalidLinks(final EnumValidationLevel level, final int nMax)
	{
		final VString vElem = new VString();

		final JDFResourceLinkPool linkPool = node.getResourceLinkPool();
		final VElement vLinks = linkPool == null ? null : linkPool.getPoolChildren(null, null, null);
		if (vLinks != null)
		{
			for (final KElement link : vLinks)
			{
				final JDFResourceLink rl = (JDFResourceLink) link;
				if (!isValidLink(level, rl))
				{
					vElem.appendUnique(rl.getLinkedResourceName());
				}
			}
		}

		if (EnumValidationLevel.isRequired(level))
		{
			vElem.appendUnique(getMissingLinkVector(nMax));
		}

		return vElem;
	}

	/**
	 * get a vector of Link names that may be inserted in this element if the links need a processusage, the format is LinkName:ProcessUsage
	 *
	 * @param nMax maximum size of the returned vector
	 * @return vector of strings that contains insertable link names
	 */
	VString getInsertLinkVector(final int nMax)
	{
		final LinkInfoMap map = getLinkInfoMap();
		final VString vInsert = new VString();
		final Vector<String> resNames = ContainerUtil.getKeyVector(map);
		for (final String resName : resNames)
		{
			final LinkInfo li = map.get(resName);

			for (int j = 0; j < li.size(); j++)
			{
				final EnumProcessUsage pu = li.getEnumProcessUsage(j);
				if (li.isSingle(j))
				{
					// 110602 added
					if (getMatchingLink(resName, pu, 0) != null)
					{
						continue; // skip existing links with maxOccurs=1
					}
				}

				String s = resName + "Link";
				if (pu != null)
				{
					s += JDFConstants.COLON + pu.getName();
				}
				vInsert.add(s);
				if (vInsert.size() >= nMax)
				{
					break;
				}
			}
		}
		return vInsert;
	}

	/**
	 * definition of resource link usage, cardinality and ProcessUsage in the JDF namespace for one index
	 *
	 * @param namIndex index of the named list, if<0 tokenize all
	 * @return list of resource process usages that may be linked
	 */
	VString vLinkInfo(final String name)
	{
		final LinkInfoMap linkInfoMap = getLinkInfoMap();
		if (linkInfoMap == null)
		{
			return null;
		}
		final Collection<LinkInfo> linkInfos = linkInfoMap.values();
		final VString v = new VString();
		if (name == null)
		{
			for (final LinkInfo li : linkInfos)
			{
				v.add(li.getString());
			}
		}
		else
		{

			final LinkInfo kToken = linkInfoMap.get(name);
			if (kToken != null)
			{
				v.add(kToken.getString());
			}
		}
		return v.size() == 0 ? null : v;
	}

	/**
	 * isValidLink check whether an index is legal for this class
	 *
	 * @param level the checking level
	 * @param rl the JDFResourceLink to check
	 * @return true if valid
	 */
	boolean isValidLink(final EnumValidationLevel level, final JDFResourceLink rl)
	{
		final EnumUsage usage = rl == null ? null : rl.getUsage();
		if (usage == null)
		{
			return false;
		}

		if (!JDFElement.isInJDFNameSpaceStatic(rl))
		{
			return true;
		}

		final String nam = rl.getLinkedResourceName();
		final String procU = StringUtil.getNonEmpty(rl.getProcessUsage());
		return isValidLink(nam, usage, procU);
	}

	/**
	 * get the links that match the typesafe resource name if the Resource type is not defined for the process represented by this node see chapter 6 JDFSpec,
	 * then the links are ignored
	 *
	 * @param resName of the resource to remove
	 * @param bLink if false, returns the linked resources, else if true, returns the ResourceLink elements
	 * @param processUsage enum that defines if all links matching the name or only those matching the name usage and/or processusage are requested
	 * @return vector of resourcelink elements
	 */
	VElement getMatchingLinks(final String resName, final boolean bLink, final EnumProcessUsage processUsage)
	{
		VElement vE = null;

		final JDFResourceLinkPool rlp = node.getResourceLinkPool();
		if (rlp == null)
		{
			return null;
		}

		final LinkInfoMap map = getLinkInfoMap();
		if (map == null)
		{
			return rlp.getInOutLinks(null, bLink, resName, null);
		}

		final LinkInfo li = map.getStar(resName);
		if (li == null)
			return null;

		if (processUsage != null && processUsage.getValue() > EnumProcessUsage.AnyOutput.getValue())
		{
			final boolean pu = li.hasProcessUsage(processUsage.getName());
			if (pu)
			{
				final EnumUsage usage = li.getUsage(processUsage.getName());
				vE = rlp.getInOutLinks(usage, bLink, resName, processUsage);
			}
		}
		else
		{
			if (processUsage == null)
			{
				String linkName = null;
				if (resName != null && !resName.endsWith("Link"))
				{
					linkName = resName + "Link";
				}
				vE = rlp.getPoolChildren(linkName, null, null);
				if (!bLink)
				{
					vE = JDFResourceLinkPool.resourceVector(vE, resName);
				}
			}
			else if (processUsage == EnumProcessUsage.AnyInput)
			{
				vE = rlp.getInOutLinks(EnumUsage.Input, bLink, resName, null);
				// 170205 RP remove internal pipes from all inputs
				// TODO ideally we would check if they are connected, but this is a sufficient 98% solution
				if (bLink && vE != null)
				{
					final Iterator<KElement> vEIterator = vE.iterator();
					while (vEIterator.hasNext())
					{
						final JDFResourceLink rl = (JDFResourceLink) vEIterator.next();
						if (rl.getPipeProtocol().equals(JDFConstants.INTERNAL))
						{
							vEIterator.remove();
						}
					}
				}
			}
			else if (processUsage == EnumProcessUsage.AnyOutput)
			{
				vE = rlp.getInOutLinks(EnumUsage.Output, bLink, resName, null);
				// 170205 RP remove internal pipes from all outputs
				// TODO ideally we would check if they are connected, but this is a sufficient 98% solution
				if (bLink && vE != null)
				{
					final Iterator<KElement> vEIterator = vE.iterator();
					while (vEIterator.hasNext())
					{
						final JDFResourceLink rl = (JDFResourceLink) vEIterator.next();
						if (JDFConstants.INTERNAL.equals(rl.getPipeProtocol()))
						{
							vEIterator.remove();
						}
					}
				}
			}
		}
		return vE;
	}

	/**
	 * get a vector of Link names that are missing in this element<br>
	 * if the links need a processusage, the format is LinkName:ProcessUsage
	 *
	 * @param nMax maximum size of the returned vector
	 * @return VString vector of strings that contains missing Link names
	 */
	VString getMissingLinkVector(final int nMax)
	{
		final LinkInfoMap liMap = getLinkInfoMap();
		if (liMap == null)
		{
			return null;
		}

		if (node.getType().equals(EnumType.ProcessGroup.getName()))
		{ // TODO fix processgroup with Types and gray box category entries
			return null;
		}

		final VString vMissing = new VString();
		final Vector<String> names = ContainerUtil.getKeyVector(liMap);
		for (final String nam : names)
		{
			final LinkInfo li = liMap.get(nam);
			if (li.isRequired(null))
			{
				for (int i = 0; i < li.size(); i++)
				{
					if (li.isRequired(i))
					{
						final EnumProcessUsage pu = li.getEnumProcessUsage(i);
						if (getMatchingLink(nam, pu, 0) == null)
						{
							String s = nam + "Link";
							if (pu != null)
							{
								s += JDFConstants.COLON + pu.getName();
							}

							vMissing.addElement(s);
							if (vMissing.size() >= nMax)
							{
								break;
							}
						}
					}
				}
			}
		}
		return vMissing;
	}

	/**
	 * Append a resource that matches the typesafe link described by resource name
	 *
	 * @param resource the resource to link
	 * @param processUsage enum that defines if all links matching the name or only those matching the name usage and/or processusage are requested
	 * @param partMap the Attribute map of parts
	 * @return the new link, null if failure
	 *
	 * @default linkMatchingResource(resource, processUsage, null)
	 */
	JDFResourceLink linkMatchingResource(final JDFResource resource, final EnumProcessUsage processUsage, final JDFAttributeMap partMap)
	{
		final String resName = resource.getLocalName();
		final EnumUsage usage = extractUsage(resName, processUsage);
		JDFResourceLink rl = node.getLink(resource, usage);
		final boolean exists = rl != null;
		final String sProcessUsage = extractProcessUsage(processUsage);
		if (rl == null)
		{
			rl = node.ensureLink(resource, usage, null);
		}
		if (rl != null && !exists && !isValidLink(EnumValidationLevel.Complete, rl))
		{
			rl.deleteNode();
			rl = null;
		}
		else if (rl != null)
		{
			if (partMap != null)
				rl.setPartMap(partMap);
			if (sProcessUsage != null)
				rl.setProcessUsage(sProcessUsage);
		}
		return rl;
	}

	/**
	 *
	 * @param processUsage
	 * @return
	 */
	private String extractProcessUsage(final EnumProcessUsage processUsage)
	{
		if (processUsage == null)
			return null;
		if (EnumProcessUsage.AnyInput.equals(processUsage) || EnumProcessUsage.AnyOutput.equals(processUsage))
			return null;
		return processUsage.getName();
	}

	/**
	 *
	 * @param name
	 * @param processUsage
	 * @return
	 */
	private EnumUsage extractUsage(final String name, final EnumProcessUsage processUsage)
	{
		if (EnumProcessUsage.AnyInput.equals(processUsage))
			return EnumUsage.Input;
		if (EnumProcessUsage.AnyOutput.equals(processUsage))
			return EnumUsage.Output;

		for (int i = 0; i < 2; i++)
		{
			final LinkInfo info = getLinkInfo(name, i == 1);
			if (info != null && info.size() > 0)
			{
				EnumUsage ret = null;
				if (processUsage != null)
				{
					ret = info.getUsage(processUsage.getName());
				}
				if (ret == null)
				{
					ret = info.getUsage(null);
				}
				if (ret != null)
				{
					return ret;
				}
			}
		}
		return null;
	}

	private LinkInfo getLinkInfo(final String name, final boolean checkStar)
	{
		final LinkInfoMap map = getLinkInfoMap();
		if (map == null)
			return null;
		return checkStar ? map.getStar(name) : map.get(name);
	}

	/**
	 * get the number of links that match the typesafe link resource name
	 *
	 * @param resName name of the resources to match
	 * @param bLink if false: returns the linked resources, if true: returns the ResourceLink elements
	 * @param processUsage enum that defines if all links matching the name or only those matching the name usage and/or processusage are requested
	 * @return int - the number of resourcelink elements
	 *
	 * @default numMatchingLinks(resName, true, ProcessUsage_Any.getValue())
	 */
	int numMatchingLinks(final String resName, final boolean bLink, final EnumProcessUsage processUsage)
	{
		int iNumMatchingLinks = 0;
		final VElement v = getMatchingLinks(resName, bLink, processUsage);
		if (v != null)
		{
			iNumMatchingLinks = v.size();
		}
		return iNumMatchingLinks;
	}

	/**
	 * get the link that matches the typesafe resource name<br>
	 * if the Resource type is not defined for the process represented by this node, the link is ignored (see JDF Spec Chapter 6)
	 *
	 * @param resName name of the resource to remove
	 * @param processUsage enum that defines if all links matching the name or only those matching the name usage and/or processusage are requested
	 * @param pos the position of the link (if multiple matching links exist)
	 * @return JDFResourceLink - the resourcelink
	 */
	JDFResourceLink getMatchingLink(final String resName, final EnumProcessUsage processUsage, final int pos)
	{
		final VElement vE = getMatchingLinks(resName, true, processUsage);
		return (vE != null && vE.size() > pos) ? (JDFResourceLink) vE.elementAt(pos) : null;
	}

	/**
	 * Method AppendMatchingResource. Appends a resource and link it to this if it is listed in the list of valid nodes for for a JDF with the given type also
	 * creates the matching resource link in this
	 *
	 * @param resName the name of the resource to add
	 * @param processUsage the processUsage of the resourcelink of the resource to add: <li>null EnumProcessUsage.AnyOutput - for input but no processUsage</li>
	 * <li>EnumProcessUsage.AnyOutput - for output but no processUsage</li>
	 *
	 * @param resourceRoot the root JDF node, that is the parent of the resourcepool where the resource should be added. If null, this node is assumed.
	 *
	 * @return JDFResource the newly created resource
	 */
	JDFResource appendMatchingResource(final String resName, final EnumProcessUsage processUsage, JDFNode resourceRoot)
	{
		if (resourceRoot == null)
			resourceRoot = node;
		JDFResource r = resourceRoot.addResource(resName, null);
		final JDFResourceLink rl = linkMatchingResource(r, processUsage, null);
		if (rl == null)
		{
			final EnumUsage usage = extractUsage(resName, processUsage);
			boolean mustZapp = usage != null;
			if (!mustZapp)
			{
				final LinkInfo li = getLinkInfo(resName, true);
				mustZapp = li == null || li.size() == 0;
				if (!mustZapp)
				{
					final int maxAllowed = li.maxAllowed(null);
					if (maxAllowed < Integer.MAX_VALUE)
					{
						final VElement resourceLinks = node.getResourceLinks(resName, null, null);
						mustZapp = resourceLinks != null && resourceLinks.size() >= maxAllowed;
					}
				}
			}
			if (mustZapp)
			{
				r.deleteNode();
				r = null;
			}
		}
		return r;
	}

	/**
	 *
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString()
	{
		return "LinkValidator " + node.getTypesString() + " [theMap=" + getLinkInfoMap() + "]";
	}

	/**
	 *
	 * @param resName
	 * @param usage
	 * @param processUsage
	 * @return
	 */
	boolean isValidLink(final String resName, final EnumUsage usage, final String processUsage)
	{
		final LinkInfo li = getLinkInfo(resName, true);
		if (li == null)
		{
			return false;
		}
		final JDFAttributeMap uMap = usage == null ? null : new JDFAttributeMap(AttributeName.USAGE, usage);
		final VElement vExist = node.getResourceLinks(resName, uMap, null);
		return li.isValidLink(usage, processUsage, vExist == null ? 0 : vExist.size());
	}
}
