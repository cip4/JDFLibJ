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
package org.cip4.jdflib.extensions.xjdfwalker.jdftoxjdf;

import org.cip4.jdflib.auto.JDFAutoGeneralID.EnumDataType;
import org.cip4.jdflib.core.AttributeName;
import org.cip4.jdflib.core.ElementName;
import org.cip4.jdflib.core.JDFAudit;
import org.cip4.jdflib.core.JDFNodeInfo;
import org.cip4.jdflib.core.KElement;
import org.cip4.jdflib.core.VElement;
import org.cip4.jdflib.core.VString;
import org.cip4.jdflib.datatypes.JDFAttributeMap;
import org.cip4.jdflib.datatypes.VJDFAttributeMap;
import org.cip4.jdflib.node.JDFNode;
import org.cip4.jdflib.node.JDFNode.EnumType;
import org.cip4.jdflib.pool.JDFAncestorPool;
import org.cip4.jdflib.resource.process.JDFGeneralID;
import org.cip4.jdflib.util.StringUtil;

/**
 * @author Rainer Prosi, Heidelberger Druckmaschinen walker for the various resource sets
 */
public class WalkJDF extends WalkJDFElement
{
	/**
	 * 
	 */
	public WalkJDF()
	{
		super();
	}

	/**
	 * @param jdf
	 * @return the created resource
	 */
	@Override
	public KElement walk(final KElement jdf, final KElement xjdf)
	{
		if (jdfToXJDF.first.contains(jdf.getID()) || jdfToXJDF.isSingleNode() && jdfToXJDF.first.size() > 0)
		{
			return null;
		}
		final JDFNode node = (JDFNode) jdf;
		boolean matchesID = matchesRootID(node);
		if (matchesID)
		{
			setRootAttributes(node, xjdf);
			jdfToXJDF.first.add(jdf.getID());
			return xjdf;
		}
		else
		{
			JDFNode nodeKid = node.getChildJDFNode(jdfToXJDF.rootID, false);
			if (nodeKid != null)
			{
				jdfToXJDF.walkTree(nodeKid, xjdf);
			}
			return null;
		}
	}

	/**
	 * @see org.cip4.jdflib.elementwalker.BaseWalker#matches(org.cip4.jdflib.core.KElement)
	 * @param toCheck
	 * @return true if it matches
	 */
	@Override
	public boolean matches(final KElement toCheck)
	{
		return (toCheck instanceof JDFNode);
	}

	/**
	 * @param node
	 * @param newRootP
	 */
	void setRootAttributes(final JDFNode node, final KElement newRootP)
	{
		if (EnumType.Product.equals(node.getEnumType()) && node.getParentJDF() != null && !jdfToXJDF.first.isEmpty())
		{
			// me be sub product
			return;
		}
		newRootP.setXMLComment("Preliminary prototype version: using: " + JDFAudit.getStaticAgentName() + " " + JDFAudit.getStaticAgentVersion());
		newRootP.setAttribute(AttributeName.JOBID, node.getJobID(true));

		final JDFNodeInfo ni = node.getCreateNodeInfo();
		final VElement niLeaves = ni.getLeaves(false);
		for (int i = 0; i < niLeaves.size(); i++)
		{
			final JDFNodeInfo niLeaf = (JDFNodeInfo) niLeaves.get(i);
			final JDFAttributeMap map = niLeaf.getPartMap();
			niLeaf.setNodeStatus(node.getPartStatus(map, 0));
			niLeaf.setNodeStatusDetails(StringUtil.getNonEmpty(node.getPartStatusDetails(map)));
		}
		String types = newRootP.getAttribute(AttributeName.TYPES, null, null);
		newRootP.setAttributes(node);

		removeUnused(newRootP);

		if (jdfToXJDF.isUpdateVersion())
		{
			newRootP.setAttribute("Version", "2.0");
			newRootP.setAttribute("MaxVersion", "2.0");
			newRootP.setID(null);
		}
		updateTypes(newRootP, types);
		namedFeaturesToGeneralID(node, newRootP);
		updateSpawnInfo(node, newRootP);
	}

	/**
	 * @param node
	 * @param newRootP
	 */
	private void namedFeaturesToGeneralID(final JDFNode node, final KElement newRootP)
	{
		if (node.hasAttribute(AttributeName.NAMEDFEATURES))
		{
			final VString vnf = node.getNamedFeatures();
			for (int i = 0; i < vnf.size(); i += 2)
			{
				final JDFGeneralID gi = (JDFGeneralID) newRootP.appendElement(ElementName.GENERALID);
				gi.setIDUsage(vnf.get(i));
				gi.setIDValue(vnf.get(i + 1));
				gi.setDataType(EnumDataType.NMTOKEN);
				gi.setDescriptiveName("Copy from NamedFeatures");
			}
			newRootP.removeAttribute(AttributeName.NAMEDFEATURES);
		}
	}

	/**
	 * @param xjdfElement
	 */
	@Override
	protected void removeUnused(final KElement xjdfElement)
	{
		// status is set only in the NodeInfo
		xjdfElement.removeAttribute(AttributeName.STATUS);
		xjdfElement.removeAttribute(AttributeName.STATUSDETAILS);
		xjdfElement.removeAttribute(AttributeName.ACTIVATION);
		xjdfElement.removeAttribute(AttributeName.TEMPLATE);
		super.removeUnused(xjdfElement);
	}

	/**
	 * @param newRootP
	 * @param types 
	 */
	private void updateTypes(final KElement newRootP, String types)
	{
		if (!newRootP.hasAttribute(AttributeName.TYPES))
		{
			newRootP.renameAttribute("Type", "Types", null, null);
		}
		else
		{
			newRootP.removeAttribute("Type");
		}
		VString t1 = StringUtil.tokenize(types, null, false);
		VString t2 = StringUtil.tokenize(newRootP.getAttribute(AttributeName.TYPES), null, false);
		t1.removeStrings("Product", 0);
		t1.appendUnique(t2);
		t1.removeStrings("ProcessGroup", 0);
		t1.removeStrings("Combined", 0);
		newRootP.setAttribute(AttributeName.TYPES, t1, null);
	}

	/**
	 * @param node
	 * @param newRootP
	 */
	private void updateSpawnInfo(final JDFNode node, final KElement newRootP)
	{
		if (m_spawnInfo != null && newRootP.hasAttribute(AttributeName.SPAWNID))
		{
			final KElement spawnInfo = newRootP.appendElement(m_spawnInfo, "www.cip4.org/SpawnInfo");
			spawnInfo.moveAttribute(AttributeName.SPAWNID, newRootP, null, null, null);
			final JDFAncestorPool ancestorPool = node.getAncestorPool();
			if (ancestorPool != null)
			{
				final VJDFAttributeMap vParts = ancestorPool.getPartMapVector();
				if (vParts != null)
				{
					final int size = vParts.size();
					for (int i = 0; i < size; i++)
					{
						spawnInfo.appendElement(ElementName.PART).setAttributes(vParts.elementAt(i));
					}
				}
			}
		}
	}
}