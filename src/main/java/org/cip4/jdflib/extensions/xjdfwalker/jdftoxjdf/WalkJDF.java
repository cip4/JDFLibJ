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
package org.cip4.jdflib.extensions.xjdfwalker.jdftoxjdf;

import java.util.HashSet;

import org.cip4.jdflib.auto.JDFAutoGeneralID.EnumDataType;
import org.cip4.jdflib.core.AttributeName;
import org.cip4.jdflib.core.ElementName;
import org.cip4.jdflib.core.JDFAudit;
import org.cip4.jdflib.core.JDFConstants;
import org.cip4.jdflib.core.JDFNodeInfo;
import org.cip4.jdflib.core.KElement;
import org.cip4.jdflib.core.VElement;
import org.cip4.jdflib.core.VString;
import org.cip4.jdflib.datatypes.JDFAttributeMap;
import org.cip4.jdflib.datatypes.VJDFAttributeMap;
import org.cip4.jdflib.extensions.XJDFConstants;
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
	private final HashSet<String> deprecatedTypes;
	private final HashSet<String> looseBindingTypes;

	/**
	 *
	 */
	public WalkJDF()
	{
		super();
		deprecatedTypes = new HashSet<>();
		deprecatedTypes.add(EnumType.Buffer.getName());
		deprecatedTypes.add(EnumType.Combine.getName());
		deprecatedTypes.add(EnumType.Dividing.getName());
		deprecatedTypes.add(EnumType.Ordering.getName());
		deprecatedTypes.add(EnumType.Packing.getName());
		deprecatedTypes.add(EnumType.ResourceDefinition.getName());
		deprecatedTypes.add(EnumType.Split.getName());

		deprecatedTypes.add(EnumType.AssetListCreation.getName());
		deprecatedTypes.add(EnumType.ContactCopying.getName());
		deprecatedTypes.add(EnumType.ContoneCalibration.getName());
		deprecatedTypes.add(EnumType.CylinderLayoutPreparation.getName());
		deprecatedTypes.add(EnumType.DBDocTemplateLayout.getName());
		deprecatedTypes.add(EnumType.DBTemplateMerging.getName());
		deprecatedTypes.add(EnumType.DigitalDelivery.getName());
		deprecatedTypes.add(EnumType.ImageReplacement.getName());
		deprecatedTypes.add(EnumType.PageAssigning.getName());
		deprecatedTypes.add(EnumType.PDFToPSConversion.getName());
		deprecatedTypes.add(EnumType.Proofing.getName());
		deprecatedTypes.add(EnumType.PSToPDFConversion.getName());
		deprecatedTypes.add(EnumType.ResourceDefinition.getName());
		deprecatedTypes.add(EnumType.Scanning.getName());
		deprecatedTypes.add(EnumType.StaticBlocking.getName());
		deprecatedTypes.add(EnumType.Tiling.getName());

		looseBindingTypes = new HashSet<>();
		looseBindingTypes.add(EnumType.ChannelBinding.getName());
		looseBindingTypes.add(EnumType.CoilBinding.getName());
		looseBindingTypes.add(EnumType.PlasticCombBinding.getName());
		looseBindingTypes.add(EnumType.RingBinding.getName());
		looseBindingTypes.add(EnumType.StripBinding.getName());
		looseBindingTypes.add(EnumType.WireCombBinding.getName());

	}

	/**
	 * @param jdf
	 * @return the created resource
	 */
	@Override
	public KElement walk(final KElement jdf, final KElement xjdf)
	{
		jdf.appendAnchor(null);
		if (jdfToXJDF.first.contains(jdf.getID()) || jdfToXJDF.isSingleNode() && jdfToXJDF.first.size() > 0)
		{
			return null;
		}
		final JDFNode node = (JDFNode) jdf;
		final boolean matchesID = matchesRootID(node);
		if (matchesID)
		{
			prepareRoot(node, xjdf);
			jdfToXJDF.first.add(jdf.getID());
			return xjdf;
		}
		else
		{
			final JDFNode nodeKid = node.getChildJDFNode(jdfToXJDF.rootID, false);
			if (nodeKid != null && !nodeKid.equals(jdf))
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
	void prepareRoot(final JDFNode node, final KElement newRootP)
	{
		if (EnumType.Product.equals(node.getEnumType()) && node.getParentJDF() != null && !jdfToXJDF.first.isEmpty())
		{
			// me be sub product
			return;
		}

		final JDFNodeInfo ni = node.getCreateNodeInfo();
		final VElement niLeaves = ni.getLeaves(false);
		for (final KElement leaf : niLeaves)
		{
			final JDFNodeInfo niLeaf = (JDFNodeInfo) leaf;
			final JDFAttributeMap map = niLeaf.getPartMap();
			niLeaf.setNodeStatus(node.getPartStatus(map, 0));
			niLeaf.setNodeStatusDetails(StringUtil.getNonEmpty(node.getPartStatusDetails(map)));
		}
		if (jdfToXJDF.rootID.equals(node.getID()))
		{
			setRootAttributes(node, newRootP);
		}
		else
		{
			updateTypes(newRootP, node.getTypesString());
		}
	}

	private void setRootAttributes(final JDFNode node, final KElement newRootP)
	{
		newRootP.setXMLComment("JDFToXJDF version: using: " + JDFAudit.getStaticAgentName() + " " + JDFAudit.getStaticAgentVersion());
		newRootP.setAttribute(AttributeName.JOBID, node.getJobID(true));
		setAttributes(node, newRootP);

		removeUnusedElements(newRootP);

		if (jdfToXJDF.isUpdateVersion())
		{
			newRootP.setID(null);
		}
		updateTypes(newRootP, node.getTypesString());
		namedFeaturesToGeneralID(node, newRootP);
		updateSpawnInfo(node, newRootP);
		final JDFNode parentProduct = node.getParentProduct();
		if (parentProduct != null && parentProduct != node && parentProduct != node.getJDFRoot())
		{
			newRootP.setAttribute(XJDFConstants.ParentID, parentProduct.getJobPartID(false));
		}
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
	 * @param newRootP
	 */
	private void updateTypes(final KElement newRootP, final String types)
	{
		if (newRootP.hasAttribute(AttributeName.TYPES))
		{
			newRootP.removeAttribute(AttributeName.TYPE);
		}
		else
		{
			newRootP.renameAttribute(AttributeName.TYPE, AttributeName.TYPES, null, null);
		}
		final VString t2 = StringUtil.tokenize(types, null, false);
		final VString t1 = StringUtil.tokenize(newRootP.getAttribute(AttributeName.TYPES), null, false);
		//t1.removeStrings(JDFConstants.PRODUCT, 0);
		t1.appendUnique(t2);
		t1.removeStrings(JDFConstants.PROCESSGROUP, 0);
		t1.removeStrings(JDFConstants.COMBINED, 0);

		removeDeprecatedTypes(t1);
		if (t1.isEmpty())
		{
			t1.add(JDFConstants.PRODUCT);
		}
		newRootP.setAttribute(AttributeName.TYPES, t1, null);
	}

	/**
	 * replace deprecated types with manualLabor
	 * @param t1
	 */
	void removeDeprecatedTypes(final VString t1)
	{

		for (int i = t1.size() - 1; i >= 0; i--)
		{
			final String typ = t1.get(i);
			if (isDeprecatedType(typ))
			{
				t1.setElementAt(EnumType.ManualLabor.getName(), i);
			}
			else if (looseBindingTypes.contains(typ))
			{
				t1.setElementAt(XJDFConstants.LooseBinding, i);
			}
		}

	}

	private boolean isDeprecatedType(final String typ)
	{
		return deprecatedTypes.contains(typ);
	}

	/**
	 * @param node
	 * @param newRootP
	 */
	private void updateSpawnInfo(final JDFNode node, final KElement newRootP)
	{
		if (jdfToXJDF.isRetainSpawnInfo() && newRootP.hasAttribute(AttributeName.SPAWNID))
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
						final KElement part = spawnInfo.appendElement(ElementName.PART);
						part.setAttributes(vParts.elementAt(i));
					}
				}
			}
		}
	}

	/**
	 * @see org.cip4.jdflib.extensions.xjdfwalker.jdftoxjdf.WalkJDFElement#updateAttributes(org.cip4.jdflib.datatypes.JDFAttributeMap)
	 */
	@Override
	protected void updateAttributes(final JDFAttributeMap map)
	{
		map.remove(AttributeName.AGENTVERSION);
		map.remove(AttributeName.MAXVERSION);
		map.remove(AttributeName.STATUS);
		map.remove(AttributeName.STATUSDETAILS);
		map.remove(AttributeName.TEMPLATE);
		map.remove(AttributeName.TEMPLATEID);
		map.remove(AttributeName.TEMPLATEVERSION);
		map.remove(AttributeName.VERSION);
		map.remove(AttributeName.XMLNS);
		super.updateAttributes(map);
	}

}