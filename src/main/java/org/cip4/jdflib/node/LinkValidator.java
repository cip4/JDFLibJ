/**
 * The CIP4 Software License, Version 1.0
 *
 * Copyright (c) 2001-2014 The International Cooperation for the Integration of 
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

import java.util.Iterator;
import java.util.Vector;

import org.cip4.jdflib.core.AttributeName;
import org.cip4.jdflib.core.JDFConstants;
import org.cip4.jdflib.core.JDFElement;
import org.cip4.jdflib.core.JDFElement.EnumValidationLevel;
import org.cip4.jdflib.core.JDFException;
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

	/**
	 * 
	 */
	LinkValidator(JDFNode n)
	{
		this.node = n;
		validatorMap = LinkValidatorMap.getLinkValidatorMap();
	}

	/**
	 * definition of resource link names in the JDF namespace
	 * 
	 * @return String list of resource names that may be linked
	 */
	VString linkNames()
	{
		final EnumType typ = EnumType.getEnum(node.getType());
		VString vTypes = node.getTypes();
		if (typ == null)
		{
			return null;
		}
		return validatorMap.getLinkNames(typ, vTypes);
	}

	/**
	 * definition of resource link usage, cardinality and ProcessUsage in the JDF namespace
	 * 
	 * @return String list of resource information usages that may be linked
	 */
	Vector<LinkInfo> linkInfo()
	{
		final EnumType typ = EnumType.getEnum(node.getType());
		VString vTypes = node.getTypes();
		if (typ == null)
		{
			return null;
		}

		return validatorMap.getLinkInfo(typ, vTypes);
	}

	/**
	 * Get the index in Linknames of the ResourceLink described by rl
	 * 
	 * @param rl
	 * @param nOccur for looping over combined nodes
	 * @return -1 if it does not fit
	 */
	private int[] getMatchingNamIndex(final JDFResourceLink rl, final int nOccur)
	{
		final int[] ret = new int[2];
		ret[0] = ret[1] = -1;
		if (rl == null)
		{
			return ret;
		}

		// 311002 KM added nOccur for looping over combined nodes
		// TBD evaluate CombinedProcessIndex when generating nOccur
		final VString linkNames = linkNames();
		if (linkNames == null)
		{
			return ret;
		}

		int namIndex = linkNames.indexOf(rl.getLinkedResourceName());

		// int namIndex = vName.index((new
		// KString(rl.getNodeName())).leftStr(-4), nOccur);
		// 120802 rp add check for *
		if (namIndex < 0)
		{
			namIndex = linkNames.indexOf(JDFConstants.STAR);
		}

		if (namIndex < 0)
		{
			return ret;
		}

		final VString vIndex = vLinkInfo(namIndex);
		if (vIndex == null)
		{
			return ret;
		}

		int iLoop = 0;
		for (int i = 0; i < vIndex.size(); i++)
		{

			final String typ = vIndex.elementAt(i);

			if (typ.charAt(0) == 'i' && !JDFResourceLink.EnumUsage.Input.equals(rl.getUsage()))
			{
				continue;
			}
			if (typ.charAt(0) == 'o' && !JDFResourceLink.EnumUsage.Output.equals(rl.getUsage()))
			{
				continue;
			}
			if (typ.length() > 2)
			{ // processusage is specified
				if (!StringUtil.rightStr(typ, -2).equals(rl.getProcessUsage()))
				{
					continue;
				}
			}
			else
			{ // no processusage is specified
				if (rl.hasAttribute(AttributeName.PROCESSUSAGE))
				{
					continue;
				}

			}
			if (iLoop++ < nOccur)
			{
				continue;
			}
			ret[0] = namIndex;
			ret[1] = i;
			return ret;
		}
		return ret;
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
		final Vector<Integer> foundSingleLinks = new Vector<Integer>();
		final Vector<Integer> foundSingleLinks2 = new Vector<Integer>();

		final JDFResourceLinkPool linkPool = node.getResourceLinkPool();
		if (linkPool != null)
		{
			final VElement vLinks = linkPool.getPoolChildren(null, null, null);
			if (vLinks != null)
			{
				for (int i = vLinks.size() - 1; i >= 0; i--)
				{
					final JDFResourceLink rl = (JDFResourceLink) vLinks.elementAt(i);
					if (!isValidLink(level, rl, foundSingleLinks, foundSingleLinks2))
					{
						vElem.appendUnique(rl.getLinkedResourceName());
					}
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
		final VString names = linkNames();
		final VString vInsert = new VString();
		for (int i = 0; i < names.size(); i++)
		{
			final VString types = vLinkInfo(i);
			for (int j = 0; j < types.size(); j++)
			{
				final EnumProcessUsage pu = node.getEnumProcessUsage(types.elementAt(j), 0);
				if ((types.elementAt(j)).charAt(1) == '?' || (types.elementAt(j)).charAt(1) == '_')
				{
					// 110602 added
					if (getMatchingLink(names.elementAt(i), pu, 0) != null)
					{
						continue; // skip existing links with maxOccurs=1
					}
				}

				String s = names.elementAt(i) + "Link";
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
	VString vLinkInfo(int namIndex)
	{
		final VString vRet = new VString();
		final Vector<LinkInfo> linkInfo = linkInfo();
		if (linkInfo == null)
		{
			return null;
		}

		if (namIndex < 0)
		{
			VString v = new VString();
			for (LinkInfo li : linkInfo)
			{
				v.add(li.getString());
			}
			return v;
		}

		final VString linkNames = linkNames();
		final String strName = linkNames.stringAt(namIndex);

		while (namIndex >= 0)
		{
			final LinkInfo kToken = linkInfo.get(namIndex);
			final VString vToken = kToken.getVString();

			vRet.addAll(vToken);
			namIndex = linkNames.indexOf(strName, ++namIndex);
		}

		return vRet.isEmpty() ? null : vRet;
	}

	/**
	 * isValidLink check whether an index is legal for this class
	 * 
	 * @param level the checking level
	 * @param rl the JDFResourceLink to check
	 * @param doneNameList Vector of Integer
	 * @param doneIndexList Vector of Integer
	 * @return true if valid
	 */
	boolean isValidLink(final EnumValidationLevel level, final JDFResourceLink rl, Vector<Integer> doneNameList, Vector<Integer> doneIndexList)
	{

		if (rl == null)
		{
			return false;
		}

		if (!JDFElement.isInJDFNameSpaceStatic(rl))
		{
			return true;
		}

		// allow call with initial null
		if (doneIndexList == null)
		{
			doneIndexList = new Vector<Integer>();
		}
		if (doneNameList == null)
		{
			doneNameList = new Vector<Integer>();
		}

		int nOccur = 0;
		int iIndex;
		int namIndex;

		// loop over all potential occurrences
		while (true)
		{
			// on the C++ side the following two methods are represented with
			// a method getMatchingIndex(rl, iIndex, nOccur) that
			// has 3 parameters, one of them is a reference(!) at iIndex
			final int[] ii = getMatchingNamIndex(rl, nOccur);
			namIndex = ii[0];
			iIndex = ii[1];

			// not found -> check whether this node is known yet
			if (namIndex < 0)
			{
				final EnumType enumType = node.getEnumType();
				if (enumType == null || enumType.equals(EnumType.ProcessGroup) || (enumType.equals(EnumType.Combined) && node.getEnumTypes() == null))
				{
					return true;
				}
				return false;
			}

			// loop over all completed occurrences with maxOccurs=1
			// if they have already been found, search for next occurrence
			boolean bTryNext = false;
			final int dns = doneNameList.size();

			for (int i = 0; i < dns; i++)
			{
				if ((doneNameList.elementAt(i)).intValue() == namIndex && (doneIndexList.elementAt(i)).intValue() == iIndex)
				{
					nOccur++; // this one is gone, try next
					bTryNext = true;
					break; // 
				}
			}

			if (!bTryNext) // we got here and the link is potentially valid
			{
				break;
			}
		}

		final boolean isValid = true; // rl.isValid(level);

		// TODO remove line wchar_t card=vLinkInfo(namIndex)[iIndex][1];
		final VString vCard = vLinkInfo(namIndex);
		final String str = vCard.stringAt(iIndex);
		final char card = str.charAt(1);

		if (isValid && ((card == '_') || (card == '?')))
		{
			doneNameList.addElement(Integer.valueOf(namIndex));
			doneIndexList.addElement(Integer.valueOf(iIndex));
		}

		return isValid;
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

		final VString linkNames = linkNames();
		if (linkNames == null)
		{
			return rlp.getInOutLinks(null, bLink, resName, null);
		}

		int namIndex = linkNames.indexOf(resName);
		if (namIndex < 0)
		{
			namIndex = linkNames.indexOf(JDFConstants.STAR);
			if (namIndex < 0)
			{
				return null;
			}
		}

		final VString vInfo = vLinkInfo(namIndex);

		if (processUsage != null && processUsage.getValue() > EnumProcessUsage.AnyOutput.getValue())
		{
			final String pu = processUsage.getName();
			for (int i = 0; i < vInfo.size(); i++)
			{
				if ((vInfo.elementAt(i)).endsWith(pu))
				{
					final boolean bInput = (vInfo.elementAt(i)).charAt(0) == 'i';
					// 240502 RP bug fix by Komori
					vE = rlp.getInOutLinks(bInput ? EnumUsage.Input : EnumUsage.Output, bLink, resName, processUsage);
					break;
				}
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
		final VString names = linkNames();
		if (names == null)
		{
			return null;
		}

		if (node.getType().equals(EnumType.ProcessGroup.getName()))
		{ // TODO fix processgroup with Types and gray box category entries
			return null;
		}

		final VString vMissing = new VString();
		final int nameSize = names.size();
		for (int i = 0; i < nameSize; i++)
		{
			final String nam = names.stringAt(i);
			boolean namDone = false;
			for (int k = 0; k < i; k++)
			{
				if (names.stringAt(k).equals(nam))
				{
					namDone = true;
					break;
				}
			}

			if (namDone)
			{
				continue; // already tested this name - vLinkInfo collects all
				// data
			}

			final VString types = vLinkInfo(i);
			if (types != null)
			{
				final Iterator<String> typesIterator = types.iterator();
				while (typesIterator.hasNext())
				{
					final String typesAt = typesIterator.next();
					if (typesAt.charAt(1) == '+' || typesAt.charAt(1) == '_')
					{
						// 110602 added
						final EnumProcessUsage pu = node.getEnumProcessUsage(typesAt, 0);
						if (getMatchingLink(names.elementAt(i), pu, 0) == null)
						{
							String s = names.elementAt(i) + "Link";

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
	 * get the matching types for a given resource
	 * 
	 * @param resName
	 * @param processUsage
	 * @return null if anything goes, an empty list if nothing goes, else the list of valid entries
	 */
	private VString getMatchType(final String resName, final EnumProcessUsage processUsage)
	{
		final VString vRet = new VString();
		final VString linkNames = linkNames();
		if (linkNames == null)
		{
			return null;
		}

		int namIndex = linkNames.indexOf(resName);
		if (namIndex < 0)
		{
			namIndex = linkNames.indexOf(JDFConstants.STAR);
		}
		if (namIndex < 0)
		{
			throw new JDFException("JDFNode.appendMatchingResource invalid resName: " + resName);
		}

		final VString vInfo = vLinkInfo(namIndex);
		if (vInfo == null)
		{
			return null;
		}

		if ((processUsage == null))
		{
			return vInfo; // no filtering required
		}

		String infoTemp = null;
		final String pu = processUsage.getName();

		for (int i = 0; i < vInfo.size(); i++)
		{
			infoTemp = vInfo.elementAt(i);

			if (processUsage.getValue() > EnumProcessUsage.AnyOutput.getValue())
			{
				if (infoTemp.endsWith(pu))
				{
					vRet.add(infoTemp);
				}
			}
			else
			{
				if (processUsage.getValue() == EnumProcessUsage.AnyInput.getValue() && infoTemp.charAt(0) == 'i')
				{
					vRet.add(infoTemp);
				}
				else if (processUsage.getValue() == EnumProcessUsage.AnyOutput.getValue() && infoTemp.charAt(0) == 'o')
				{
					vRet.add(infoTemp);
				}
			}
		}

		return vRet;
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
		final VString vtyp = getMatchType(resName, processUsage);

		if (vtyp != null)
		{
			final Iterator<String> vtypIterator = vtyp.iterator();
			while (vtypIterator.hasNext())
			{
				final String typ = vtypIterator.next();
				if ((typ.charAt(1) == '?') || (typ.charAt(1) == '_'))
				{
					if (numMatchingLinks(resName, false, processUsage) > 0)
					{
						continue; // not this one...
					}
				}

				JDFResourceLink rl = node.linkResource(resource, typ.charAt(0) == 'i' ? EnumUsage.Input : EnumUsage.Output, null);
				if (typ.length() > 2)
				{
					rl.setProcessUsage(EnumProcessUsage.getEnum(typ.substring(2)));
				}

				rl.setPartMap(partMap);
				return rl;
			}
		}

		// should only get here it the link alreay exists
		throw new JDFException("JDFNode.LinkMatchingResource already exists");
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
	JDFResource appendMatchingResource(final String resName, final EnumProcessUsage processUsage, final JDFNode resourceRoot)
	{
		// TODO check comment for processUsage
		final VString vtyp = getMatchType(resName, processUsage);
		if (vtyp == null) // anything goes
		{
			final boolean bInput = !EnumProcessUsage.AnyOutput.equals(processUsage);
			final JDFResource r = node.addResource(resName, null, (bInput ? EnumUsage.Input : EnumUsage.Output), null, resourceRoot, null, null);
			final JDFResourceLink rl = node.getLink(r, null);
			if (processUsage != null && processUsage.getValue() > EnumProcessUsage.AnyOutput.getValue())
			{
				rl.setProcessUsage(processUsage);
			}
			return r;
		}

		int nFound = 0;
		String foundTyp = null;
		boolean foundMulti = false;
		int iInputFound = 0; // 1 is in, 2 is out

		for (int i = 0; i < vtyp.size(); i++)
		{
			final String typ = vtyp.elementAt(i);
			final boolean bInput = typ.charAt(0) == 'i';

			if ((typ.charAt(1) == '?') || (typ.charAt(1) == '_'))
			{
				if (numMatchingLinks(resName, false, processUsage) > nFound)
				{
					nFound++; // TODO need to fix this, it is only a 90%
					// solution
					continue;
				}
			}
			if (foundTyp == null)
			{
				foundTyp = typ;
				iInputFound = bInput ? 1 : 2;

			}
			else if (!typ.equals(foundTyp))
			{
				foundMulti = true;
				if ((bInput ? 1 : 2) != iInputFound)
				{
					iInputFound = 0; // we could have either in or out - cannot
					// link
					break;
				}
			}
		}
		if (foundTyp == null)
		{
			// should only get here it the link alreay exists
			throw new JDFException("JDFNode.appendMatchingResource already exists");
		}

		final JDFResource r = node.addResource(resName, null, null, null, resourceRoot, null, null);
		if (iInputFound > 0)
		{
			final JDFResourceLink rl = node.linkResource(r, iInputFound == 1 ? EnumUsage.Input : EnumUsage.Output, null);
			if (!foundMulti && foundTyp.length() > 2)
			{
				rl.setProcessUsage(EnumProcessUsage.getEnum(foundTyp.substring(2)));
			}
		}

		return r;
	}
}
