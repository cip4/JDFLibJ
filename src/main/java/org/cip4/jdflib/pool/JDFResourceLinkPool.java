/*
 * The CIP4 Software License, Version 1.0
 *
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
/**
 *
 * Copyright (c) 2001 Heidelberger Druckmaschinen AG, All Rights Reserved.
 *
 * JDFResourceLinkPool.java
 *
 * Last changes
 *
 * 2002-07-02   JG - added IsValid()
 * 2002-07-02   JG - now inherits from JDFPool
 * 2002-07-02   JG - added GetPoolChild, GetPoolChildren
 * 2002-07-02   JG - GetPartValues() first parameter is now JDFRessource::EnumPartIDKey
 *
 */
package org.cip4.jdflib.pool;

import java.util.HashSet;
import java.util.List;
import java.util.Vector;

import org.apache.xerces.dom.CoreDocumentImpl;
import org.cip4.jdflib.core.AttributeInfo;
import org.cip4.jdflib.core.AttributeName;
import org.cip4.jdflib.core.ElementInfo;
import org.cip4.jdflib.core.JDFConstants;
import org.cip4.jdflib.core.JDFElement;
import org.cip4.jdflib.core.JDFException;
import org.cip4.jdflib.core.JDFResourceLink;
import org.cip4.jdflib.core.JDFResourceLink.EnumUsage;
import org.cip4.jdflib.core.KElement;
import org.cip4.jdflib.core.VElement;
import org.cip4.jdflib.core.VString;
import org.cip4.jdflib.datatypes.JDFAttributeMap;
import org.cip4.jdflib.datatypes.VJDFAttributeMap;
import org.cip4.jdflib.node.JDFNode;
import org.cip4.jdflib.node.JDFNode.EnumProcessUsage;
import org.cip4.jdflib.resource.JDFResource;
import org.cip4.jdflib.resource.JDFResource.EnumPartIDKey;
import org.cip4.jdflib.util.ContainerUtil;
import org.cip4.jdflib.util.StringUtil;

/**
 *
 */
public class JDFResourceLinkPool extends JDFPool
{
	private static final long serialVersionUID = 1L;

	/**
	 * Constructor for JDFResourceLinkPool
	 *
	 * @param myOwnerDocument
	 * @param qualifiedName
	 */
	public JDFResourceLinkPool(final CoreDocumentImpl myOwnerDocument, final String qualifiedName)
	{
		super(myOwnerDocument, qualifiedName);
	}

	/**
	 * Constructor for JDFResourceLinkPool
	 *
	 * @param myOwnerDocument
	 * @param myNamespaceURI
	 * @param qualifiedName
	 */
	public JDFResourceLinkPool(final CoreDocumentImpl myOwnerDocument, final String myNamespaceURI, final String qualifiedName)
	{
		super(myOwnerDocument, myNamespaceURI, qualifiedName);
	}

	/**
	 * Constructor for JDFResourceLinkPool
	 *
	 * @param myOwnerDocument
	 * @param myNamespaceURI
	 * @param qualifiedName
	 * @param myLocalName
	 */
	public JDFResourceLinkPool(final CoreDocumentImpl myOwnerDocument, final String myNamespaceURI, final String qualifiedName, final String myLocalName)
	{
		super(myOwnerDocument, myNamespaceURI, qualifiedName, myLocalName);
	}

	/**
	 *
	 * @return
	 */
	@Override
	protected AttributeInfo getTheAttributeInfo()
	{
		AttributeInfo ai = AttributeInfo.fixedMap.get("JDFResourceLinkPool");
		if (ai != null)
			return ai;
		ai = super.getTheAttributeInfo();
		AttributeInfo.fixedMap.put("JDFResourceLinkPool", ai);
		return ai;
	}

	@Override
	protected ElementInfo getTheElementInfo()
	{
		return super.getTheElementInfo().updateAdd(JDFResourcePool.getLinkInfoTable());
	}

	/**
	 * toString
	 *
	 * @return String
	 */
	@Override
	public String toString()
	{
		return "JDFResourceLinkPool[ --> " + super.toString() + " ]";
	}

	/**
	 * GetLinks - get the links matching mLinkAtt out of the resource link pool
	 * <p>
	 * default: GetLinks(null)
	 *
	 * @param mLinkAtt the attributes to search for
	 *
	 * @return mLinkAtt vector all all elements matching the condition mLinkAtt
	 * @deprecated use getPoolChildren()
	 */
	@Deprecated
	public VElement getLinks(final JDFAttributeMap mLinkAtt)
	{
		return getPoolChildren(null, mLinkAtt, null);
	}

	/**
	 * GetLinks - get the links matching elementName/nameSpaceURI from the resource pool<br>
	 * if you need all links in the doc, call getLinks from JDFElement
	 * <p>
	 * default: GetLinks(null, null)
	 *
	 * @param elementName Name of the Linked resource
	 * @param nameSpaceURI the namespace to search in
	 *
	 * @return VElement - vector all all elements matching the condition mLinkAtt
	 * @deprecated use getPoolChildren()
	 */
	@Deprecated
	public VElement getLinks(final String elementName, final String nameSpaceURI)
	{
		return getPoolChildren(elementName, null, nameSpaceURI);
	}

	/**
	 * Get the linked resources matching some conditions
	 * <p>
	 * default: GetLinkedResources(null, null, null, false)
	 *
	 * @param resName type(Name) of the resource to get
	 * @param mLinkAtt the link attribute to search for
	 * @param mResAtt attribute to search for
	 * @param bFollowRefs if true search all HRefs and add them to the list
	 *
	 * @return VElement - vector with all Resources matching the conditions
	 * @deprecated - use namespace enabled version getLinkedResources(resName, mLinkAtt, mResAtt, bFollowRefs, null);
	 */
	@Deprecated
	public VElement getLinkedResources(final String resName, final JDFAttributeMap mLinkAtt, final JDFAttributeMap mResAtt, final boolean bFollowRefs)
	{
		return getLinkedResources(resName, mLinkAtt, mResAtt, bFollowRefs, null);
	}

	/**
	 * Get the linked resources matching some conditions
	 * <p>
	 * default: GetLinkedResources(null, null, null, false)
	 *
	 * @param resName type(Name) of the resource to get
	 * @param mLinkAtt the link attribute to search for
	 * @param mResAtt attribute to search for
	 * @param bFollowRefs if true search all HRefs and add them to the list
	 * @param nameSpaceURI
	 *
	 * @return VElement - vector with all Resources matching the conditions
	 */
	public VElement getLinkedResources(String resName, final JDFAttributeMap mLinkAtt, final JDFAttributeMap mResAtt, final boolean bFollowRefs, final String nameSpaceURI)
	{
		final VElement vL = new VElement();
		final VElement v = getPoolChildren(null, mLinkAtt, null);
		if (v != null)
		{
			resName = StringUtil.getNonEmpty(resName);
			if (resName != null && resName.endsWith(JDFConstants.LINK))
			{
				resName = resName.substring(0, resName.length() - 4); // remove link
			}
			final boolean bColon = xmlnsPrefix(resName) != null;
			final int size = v.size();
			for (int i = 0; i < size; i++)
			{
				final JDFResourceLink l = (JDFResourceLink) v.elementAt(i);

				final JDFResource linkRoot = l.getLinkRoot();
				if (linkRoot != null)
				{
					boolean bNameMatches = (resName == null) || (bColon ? linkRoot.getNodeName().equals(resName) : linkRoot.getLocalName().equals(resName));
					if (bNameMatches && (resName != null) && JDFElement.isInJDFNameSpaceStatic(l) ^ JDFElement.isInJDFNameSpaceStatic(nameSpaceURI)) // the name matches but not
																																						// necessarily the namespace
																																						// since
					{
						bNameMatches = bColon;
					}
					if (bNameMatches && !JDFElement.isInJDFNameSpaceStatic(nameSpaceURI))
						bNameMatches = ContainerUtil.equals(nameSpaceURI, l.getNamespaceURI());
					if (bNameMatches && linkRoot.includesAttributes(mResAtt, true))
					{
						vL.addElement(linkRoot);
						if (bFollowRefs)
						{
							vL.appendUnique(linkRoot.getvHRefRes(bFollowRefs, true));
						}
					}
				}
			}
		}

		return vL;
	}

	/**
	 * GetInOutLinks - get the links from the pool (input or output)
	 * <p>
	 * default: GetInOutLinks(bInOut, true, JDFConstants.WILDCARD, JDFConstants.WILDCARD)
	 *
	 * @param bInOut what kind of links you want to have (true for input)
	 * @param bLink if true, return the resource links. if false return the resources
	 * @param resName type of the resource to get ( * for all)
	 * @param resProcUsage process usage of the resource to get (* for all)
	 * @deprecated use getInOutLinks with EnumUsage signature
	 * @return VElement - Vector with the found resource links
	 */
	@Deprecated
	public VElement getInOutLinks(final boolean bInOut, final boolean bLink, final String resName, final String resProcUsage)
	{
		return getInOutLinksExtended(bInOut ? EnumUsage.Input : EnumUsage.Output, bLink, resName, resProcUsage, null, true);
	}

	/**
	 * GetInOutLinks - get the links from the pool (input or output)
	 * <p>
	 * default: GetInOutLinks(null, true, null, null)
	 *
	 * @param usage what kind of links you want to have (input, output). If null all are selected.
	 * @param bLink if true, return the resource links. if false return the resources
	 * @param resName name of the resource to get ( * for all)
	 * @param procUsage process usage of the resource to get
	 *
	 * @return VElement - Vector with the found resource links
	 */
	public VElement getInOutLinks(final EnumUsage usage, final boolean bLink, final String resName, final EnumProcessUsage procUsage)
	{
		return getInOutLinksExtended(usage, bLink, resName, procUsage == null ? null : procUsage.getName(), null, true);
	}

	/**
	 * getInOutLinksExtended - get the links from the pool (input or output)
	 * <p>
	 * default: GetInOutLinks(null, true, null, null)
	 *
	 * @param usage what kind of links you want to have (input, output). If null all are selected.
	 * @param bLink if true, return the resource links. if false return the resources
	 * @param resName name of the resource to get ( * or null for all)
	 * @param procUsage process usage of the resource to get
	 * @param namespaceURI
	 * @param bAll
	 *
	 * @return VElement - Vector with the found resource links
	 */
	public VElement getInOutLinksExtended(final EnumUsage usage, final boolean bLink, final String resName, String procUsage, final String namespaceURI, final boolean bAll)
	{
		final JDFAttributeMap mA = (usage != null) ? new JDFAttributeMap(AttributeName.USAGE, usage.getName()) : null;
		if (isWildCard(procUsage))
			procUsage = null;

		final int loop1 = procUsage == null ? (bAll ? 1 : 0) : 1;
		VElement v0 = getPoolChildren(null, mA, null);
		for (int procUsageLoop = loop1; procUsageLoop < 2; procUsageLoop++)
		{
			final VElement v = new VElement();
			if (v0 == null || v0.size() == 0)
				return v0;

			v.addAll(v0);

			if (v != null)
			{
				for (int i = v.size() - 1; i >= 0; i--)
				{
					final JDFResourceLink li = (JDFResourceLink) v.elementAt(i);
					if (!isWildCard(resName))
					{
						final String linkedResourceName = li.getLinkedResourceName();
						boolean fitsNamespace = li.getNamespaceURI().equals(namespaceURI);
						fitsNamespace = fitsNamespace || (isWildCard(namespaceURI) && isInJDFNameSpaceStatic(li));

						if (!linkedResourceName.equals(resName) && (!fitsNamespace || fitsNamespace && !xmlnsLocalName(linkedResourceName).equals(resName)))
						{
							v.removeElementAt(i);
							continue;
						}
					}

					final String linkProcessUsage = StringUtil.getNonEmpty(li.getProcessUsage());
					if ((procUsage != null && !procUsage.equals(linkProcessUsage)) || (procUsage == null && linkProcessUsage != null && procUsageLoop == 0))
					{
						v.removeElementAt(i);
						continue;
					}
				}
			}
			if (v.size() > 0 || procUsageLoop == 1)
			{
				v0 = v;
				break;
			}
		}

		if (!bLink)
		{
			v0 = resourceVector(v0, null);
		}

		return v0;
	}

	/**
	 * ResourceVector - convert a link vector to a resource vector
	 *
	 * @param linkVector vector to convert
	 * @param resType kind of resType to add ( <code>*</code> for all)
	 *
	 * @return VElement - the converted vector
	 */
	public static VElement resourceVector(final VElement linkVector, final String resType)
	{
		if (linkVector == null)
		{
			return null;
		}

		final VElement v = new VElement();
		final boolean bResTypeWildcard = isWildCard(resType);

		for (final KElement e : linkVector)
		{
			final JDFResourceLink l = (JDFResourceLink) e;

			// 120803 rp follow parts of resource links
			if (bResTypeWildcard || (l.getLinkedResourceName().equals(resType)))
			{
				final VElement vRes = l.getTargetVector(-1);
				v.addAll(vRes);
			}
		}

		return v;
	}

	/**
	 * AppendResource - append resource r to this link pool
	 * <p>
	 * default: AppendResource(r, input, false)
	 *
	 * @param r the resource to append
	 * @param input usage of the link (true = inout, false = output)
	 * @param bForce if true create the link, even though it already exists - now ignored since it is useless
	 *
	 * @return JDFResourceLink - link to appended resource
	 *
	 * @throws JDFException if r is not in the same document as this
	 *
	 * @deprecated
	 */
	@Deprecated
	public JDFResourceLink appendResource(final JDFResource r, final boolean input, boolean bForce)
	{
		if (bForce)
		{
			bForce = true; // fool compiler
		}

		return linkResource(r, input ? EnumUsage.Input : EnumUsage.Output, null);
	}

	/**
	 * getLink - get the resourcelink that resides in the ResourceLinkPool of this node and references the resource r
	 * <p>
	 * default: getLink(r, EnumUsage.Input, null)
	 *
	 * @param r the resource you are searching a link for
	 * @param usage usage of the link (input/output)
	 * @param processUsage ProcessUsage of the link
	 *
	 * @return JDFResourceLink - the resource link you were searching for. If not found, a new empty JDFResourceLink is returned.
	 */
	public JDFResourceLink getLink(final JDFResource r, final EnumUsage usage, final EnumProcessUsage processUsage)
	{
		if (r == null)
		{
			return null;
		}

		final String id = r.getID();
		if (id == null)
		{
			return null;
		}

		// get any possible links
		final VElement v = getInOutLinks(usage, true, null, processUsage);
		if (v != null)
		{
			// is it the right one?
			final int vSize = v.size();
			for (int i = 0; i < vSize; i++)
			{
				final JDFResourceLink resLink = (JDFResourceLink) v.elementAt(i);

				if (resLink != null && resLink.getrRef().equals(id) && resLink.getNodeName().equals(r.getLinkString()))
				{
					return resLink;
				}
			}
		}

		// nothing found
		return null;
	}

	/**
	 * linkResource - link resource r to this link pool
	 * <p>
	 * default: linkResource(r, usage, null)
	 *
	 * @param r the resource to link
	 * @param usage usage of the link
	 * @param processUsage processUsage of the link, null if none
	 *
	 * @return JDFResourceLink - new link resource, null if an error occurred
	 *
	 */
	public JDFResourceLink linkResource(final JDFResource r, final JDFResourceLink.EnumUsage usage, final EnumProcessUsage processUsage)
	{
		if (r == null || usage == null)
		{
			return null;
		}

		String s = r.getID();
		if (isWildCard(s))
		{
			s = r.getResourceRoot().appendAnchor(null);
		}

		JDFResourceLink rl = (JDFResourceLink) appendElement(r.getLinkString(), r.getNamespaceURI());
		rl.setTarget(r);
		rl.setUsage(usage);
		rl.setProcessUsage(processUsage);

		final JDFNode parenNode = getParentJDF();
		try
		{
			parenNode.ensureValidRefsPosition(r);
		}
		catch (final Exception x)
		{
			rl.deleteNode();
			rl = null;
		}
		return rl;
	}

	/**
	 * getPartMapVector - get the part map vector from the actual element
	 *
	 * @param bComplete if true, expand all parts defined in PartIDKeys
	 *
	 * @return VJDFAttributeMap - map of all parts linked by this resourcelinkpool
	 */
	public VJDFAttributeMap getPartMapVector(final boolean bComplete)
	{
		final VJDFAttributeMap vMap = new VJDFAttributeMap();

		if (bComplete)
		{
			final VString vKeys = getPartIDKeys();

			final int keySize = vKeys.size();
			final Vector<VString> vvValues = new Vector<VString>();
			final int pI[] = new int[keySize];
			final int pISize[] = new int[keySize];

			for (int i = 0; i < keySize; i++)
			{
				if (EnumPartIDKey.getEnum(vKeys.elementAt(i)) != null)
				{
					vvValues.addElement(getPartValues(JDFResource.EnumPartIDKey.getEnum(i)));

					pI[i] = 0;
					pISize[i] = vvValues.elementAt(i).size();
				}
			}

			while (true)
			{
				final JDFAttributeMap m = new JDFAttributeMap();
				boolean bDone = false;

				for (int i = 0; i < keySize; i++)
				{
					m.put(vKeys.elementAt(i), vvValues.elementAt(i).elementAt(pI[i]));
				}

				vMap.addElement(m);

				for (int n = 0; n < keySize; n++)
				{
					if (++pI[n] >= pISize[n])
					{
						pI[n] = 0;

						if (n == keySize - 1)
						{
							bDone = true;
						}
					}
					else
					{
						break;
					}
				}

				if (bDone)
				{
					break;
				}
			}
		}
		else
		{
			final VElement links = getPoolChildren(null, null, null);
			if (links != null)
			{
				for (int l = 0; l < links.size(); l++)
				{
					final JDFResourceLink link = (JDFResourceLink) links.elementAt(l);
					final VJDFAttributeMap tempMap = link.getPartMapVector();

					for (int i = 0; i < tempMap.size(); i++)
					{
						final JDFAttributeMap mTmp = tempMap.elementAt(i);
						boolean bFound = false;
						boolean bReplace = false;

						for (int j = vMap.size() - 1; j >= 0; j--)
						{
							// backwards because of potential erasing
							final JDFAttributeMap mAtt = vMap.elementAt(j);

							if (!bReplace && mAtt.subMap(mTmp))
							{
								bFound = true;
								break;
							}

							if (mTmp.subMap(mAtt))
							{
								if (bReplace)
								{
									final int j1 = j;
									vMap.set(j1, mTmp);
								}
								else
								{ // already replaced one, clear all other
									// matches
									vMap.clear();
								}

								bReplace = true;
							}
						}

						if (!bFound)
						{
							vMap.add(mTmp);
						}
					}
				}
			}
		}

		return vMap;
	}

	/**
	 * get a vector of all part id keys linked
	 *
	 * @return Vector
	 */
	public VString getPartIDKeys()
	{
		final VString vs = new VString();
		final VElement links = getPoolChildren(null, null, null);
		if (links != null)
		{
			for (int i = 0; i < links.size(); i++)
			{
				final JDFResourceLink link = (JDFResourceLink) links.elementAt(i);
				final VString keys = link.getLinkRoot().getPartIDKeys();

				for (int j = 0; j < keys.size(); j++)
				{
					if (!vs.contains(keys.elementAt(j)))
					{
						vs.addElement(keys.elementAt(j));
					}
				}
			}
		}

		return vs;
	}

	/**
	 * GetPartValues - get a list of the values for attribute partType within the leaves of all linked resources
	 *
	 * @param partType the attribute name you which to get the values
	 *
	 * @return Vector - vector with all values of the attribute partType
	 */
	public VString getPartValues(final JDFResource.EnumPartIDKey partType)
	{
		final VString vs = new VString();
		final VElement links = getPoolChildren(null, null, null);
		if (links != null)
		{
			for (int i = 0; i < links.size(); i++)
			{
				final JDFResourceLink link = (JDFResourceLink) links.elementAt(i);
				final VString keys = link.getLinkRoot().getPartValues(partType);

				for (int j = 0; j < keys.size(); j++)
				{
					if (!vs.contains(keys.elementAt(j)))
					{
						vs.addElement(keys.elementAt(j));
					}
				}
			}
		}

		return vs;
	}

	/**
	 *
	 * @return
	 */
	public Vector<JDFResourceLink> getLinks()
	{
		return getChildrenByClass(JDFResourceLink.class, false, 0);
	}

	/**
	 *
	 * @return
	 */
	public List<JDFResourceLink> getLinkArray()
	{
		return getChildArrayByClass(JDFResourceLink.class, false, 0);
	}

	/**
	 * Gets all children with the attribute <code>name, mAttrib, nameSpaceURI</code> out of the pool
	 *
	 * @param strName name of the Child
	 * @param mAttrib a attribute to search for
	 * @param nameSpaceURI the namespace uri
	 *
	 * @return VElement: a vector with all elements in the pool matching the conditions
	 *
	 *         default: getPoolChildren(null, null, null)
	 */
	public VElement getPoolChildren(final String strName, final JDFAttributeMap mAttrib, final String nameSpaceURI)
	{
		final VElement v = getPoolChildrenGeneric(strName, mAttrib, nameSpaceURI);
		if (v == null)
		{
			return null;
		}
		for (int i = v.size() - 1; i >= 0; i--)
		{
			if (!(v.elementAt(i) instanceof JDFResourceLink))
			{
				v.removeElementAt(i);
			}
		}
		return v.size() == 0 ? null : v;
	}

	/**
	 * get a child resource from the pool matching the parameters
	 *
	 * @param i the index of the child, or -1 to make a new one.
	 * @param strName the name of the element
	 * @param mAttrib the attribute of the element
	 * @param nameSpaceURI the namespace to search in
	 *
	 * @return JDFElement: the pool child matching the above conditions
	 */
	public JDFResourceLink getPoolChild(int i, final String strName, final JDFAttributeMap mAttrib, final String nameSpaceURI)
	{
		JDFResourceLink resLink = null;
		final VElement v = getPoolChildren(strName, mAttrib, nameSpaceURI);
		if (v != null)
		{
			final int size = v.size();
			if (i < 0)
			{
				i = size + i;
				if (i < 0)
				{
					return null;
				}
			}

			if (size > i)
			{
				resLink = (JDFResourceLink) v.elementAt(i);
			}
		}
		return resLink;
	}

	/**
	 * return a vector of unknown element nodenames
	 * <p>
	 * default: GetInvalidElements(true, 999999)
	 *
	 * @param bIgnorePrivate used by JDFElement during the validation
	 * @param nMax maximum size of the returned vector
	 * @return Vector - vector of unknown element nodenames
	 *
	 *         !!! Do not change the signature of this method
	 */
	@Override
	public VString getUnknownElements(final boolean bIgnorePrivate, final int nMax)
	{
		return getUnknownPoolElements(JDFElement.EnumPoolType.ResourceLinkPool, nMax);
	}

	/**
	 * get inter-resource linked resource ids
	 *
	 * @param vDoneRefs (null, used for recursion)
	 * @param bRecurse if true, also return recursively linked IDS
	 * @return vElement: the vector of referenced resource ids
	 */
	@Override
	public HashSet<JDFElement> getAllRefs(HashSet<JDFElement> vDoneRefs, final boolean bRecurse)
	{
		final List<JDFResourceLink> vResourceLinks = getLinkArray();
		if (vResourceLinks != null)
		{
			for (final JDFResourceLink rl : vResourceLinks)
			{
				if (!vDoneRefs.contains(rl))
				{
					vDoneRefs.add(rl);
					if (bRecurse)
					{
						final JDFResource r = rl.getLinkRoot();
						if (r != null && !vDoneRefs.contains(r))
						{
							vDoneRefs = r.getAllRefs(vDoneRefs, bRecurse);
						}
					}
				}
			}
		}
		return vDoneRefs;
	}
}
