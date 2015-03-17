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

import java.util.HashSet;

import org.cip4.jdflib.core.AttributeName;
import org.cip4.jdflib.core.ElementName;
import org.cip4.jdflib.core.JDFElement;
import org.cip4.jdflib.core.JDFPartAmount;
import org.cip4.jdflib.core.JDFRefElement;
import org.cip4.jdflib.core.JDFResourceLink;
import org.cip4.jdflib.core.JDFResourceLink.EnumUsage;
import org.cip4.jdflib.core.KElement;
import org.cip4.jdflib.core.VElement;
import org.cip4.jdflib.datatypes.JDFAttributeMap;
import org.cip4.jdflib.extensions.SetHelper;
import org.cip4.jdflib.node.JDFNode;
import org.cip4.jdflib.pool.JDFAmountPool;
import org.cip4.jdflib.pool.JDFResourcePool;
import org.cip4.jdflib.resource.JDFResource;
import org.cip4.jdflib.resource.JDFResource.EnumResStatus;

/**
 * @author Rainer Prosi, Heidelberger Druckmaschinen walker for the various resource sets
 */
public class WalkJDFElement extends WalkElement
{
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
	 * @param je
	 */
	private void makeRefElements(final JDFElement je)
	{
		final VElement v = je.getChildElementVector_KElement(null, null, null, true, 0);
		for (KElement e : v)
		{
			if (e instanceof JDFResource)
			{
				final JDFResource r = (JDFResource) e;
				if (!mustInline(r.getLocalName()))
				{
					cleanRefs(je, r);
				}
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
	private void cleanRefs(final JDFElement je, JDFResource r)
	{
		final JDFNode parentJDF = je.getParentJDF();
		if (parentJDF != null)
		{
			r = r.makeRootResource(null, parentJDF, false);
			r.setResStatus(EnumResStatus.Available, true);
			final JDFResourcePool prevPool = parentJDF.getResourcePool();
			if (prevPool != null)
			{
				r = removeDuplicateRefs(r, prevPool);
			}
			je.refElement(r);
		}
		else if (je.getJMFRoot() != null)
		{
			final JDFResource resourceRoot = r.getResourceRoot();
			final JDFElement parent = (JDFElement) (resourceRoot == null ? null : resourceRoot.getParentNode_KElement());
			r = r.makeRootResource(null, parent, false);
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
			for (KElement e : prevs)
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
		return name;
	}

	/**
	 * @param re
	 * @return true if must inline re
	 */
	protected boolean mustInline(final JDFRefElement re)
	{
		return mustInline(re.getRefLocalName());
	}

	private final static HashSet<String> inlineSet = new HashSet<String>();

	/**
	 * @param refLocalName
	 * @return true if must inline refLocalName
	 */
	protected boolean mustInline(final String refLocalName)
	{
		if (inlineSet.isEmpty())
		{
			synchronized (inlineSet)
			{
				inlineSet.add(ElementName.OBJECTRESOLUTION);
				inlineSet.add(ElementName.BARCODECOMPPARAMS);
				inlineSet.add(ElementName.BARCODEREPROPARAMS);
				inlineSet.add(ElementName.COMCHANNEL);
				inlineSet.add(ElementName.INTERPRETEDPDLDATA);
				inlineSet.add(ElementName.BYTEMAP);
				inlineSet.add(ElementName.ADDRESS);
				inlineSet.add(ElementName.COSTCENTER);
				inlineSet.add(ElementName.COMPANY);
				inlineSet.add(ElementName.PERSON);
				inlineSet.add(ElementName.DEVICE);
				inlineSet.add(ElementName.DEVICENSPACE);
				inlineSet.add(ElementName.COLORANTALIAS);
				inlineSet.add(ElementName.GLUELINE);
				inlineSet.add(ElementName.GLUEAPPLICATION);
				inlineSet.add(ElementName.CIELABMEASURINGFIELD);
				inlineSet.add(ElementName.REGISTERMARK);
				inlineSet.add(ElementName.FITPOLICY);
				inlineSet.add(ElementName.CUTBLOCK);
				inlineSet.add(ElementName.EMPLOYEE);
				inlineSet.add(ElementName.ELEMENTCOLORPARAMS);
				inlineSet.add(ElementName.CUT);
				inlineSet.add(ElementName.PDLRESOURCEALIAS);
				inlineSet.add(ElementName.HOLELIST);
				inlineSet.add(ElementName.HOLE);
				inlineSet.add(ElementName.MISDETAILS);
				inlineSet.add(ElementName.HOLELINE);
				inlineSet.add(ElementName.JOBFIELD);
				inlineSet.add(ElementName.AUTOMATEDOVERPRINTPARAMS);
				inlineSet.add(ElementName.EXTERNALIMPOSITIONTEMPLATE);
				inlineSet.add(ElementName.PRODUCTIONPATH);
				inlineSet.add(ElementName.SHAPE);
				inlineSet.add(ElementName.SCAVENGERAREA);
				inlineSet.add(ElementName.TRAPREGION);
				inlineSet.add(ElementName.TRANSFERCURVE);
				inlineSet.add(ElementName.COLORCONTROLSTRIP);
				inlineSet.add(ElementName.LAYERLIST);
				inlineSet.add(ElementName.PAGECONDITION);
				inlineSet.add(ElementName.CONTENTOBJECT);
				inlineSet.add(ElementName.MARKOBJECT);
				inlineSet.add(ElementName.LAYERDETAILS);
				inlineSet.add(ElementName.FILESPEC);
				inlineSet.add(ElementName.IDENTIFICATIONFIELD);
			}
		}
		return inlineSet.contains(refLocalName);
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
	 * @see org.cip4.jdflib.extensions.XJDF20.WalkElement#removeUnused(org.cip4.jdflib.core.KElement)
	 * @param newRootP
	*/
	@Override
	protected void removeUnused(KElement newRootP)
	{
		newRootP.removeAttribute(AttributeName.SPAWNID);
		super.removeUnused(newRootP);
	}

	/**
	 * @param rl the refelement or reslink
	 * @param linkTarget
	 * @param xRoot
	 * @return the vector of partitions
	 */
	protected VElement setResource(final JDFElement rl, final JDFResource linkTarget, final KElement xRoot)
	{
		final String className = jdfToXJDF.getClassName(linkTarget);
		if (className == null || xRoot == null)
		{
			return null;
		}
		linkTarget.expand(false);

		final String resID = linkTarget.getID();
		KElement resourceSet = xRoot.getChildWithAttribute(className + SetHelper.SET, AttributeName.ID, null, resID, 0, true);
		if (resourceSet == null)
		{
			resourceSet = xRoot.appendElement(className + SetHelper.SET);
			resourceSet.setID(linkTarget.getID());
		}

		// TODO what if we have resources used as in and out in the same node?
		setSetAttributes(resourceSet, rl, linkTarget);
		int nLeaves = resourceSet.numChildElements(className, null);
		final VElement vRes = (rl instanceof JDFResourceLink) ? ((JDFResourceLink) rl).getTargetVector(0) : linkTarget.getLeaves(false);

		final VElement v = new VElement();
		for (KElement e : vRes)
		{
			final JDFResource r = (JDFResource) e;
			final VElement vLeaves = r.getLeaves(false);
			for (KElement eLeaf : vLeaves)
			{
				final JDFResource leaf = (JDFResource) eLeaf;
				final KElement newBaseRes = setBaseResource(rl, leaf, resourceSet);
				final int nn = resourceSet.numChildElements(className, null);
				if (nn > nLeaves)
				{
					nLeaves = nn;
					jdfToXJDF.walkTree(leaf, newBaseRes);
				}
				v.add(newBaseRes);
			}
		}
		return v;
	}

	/**
	 * @param rl 
	 * @param r 
	 * @param xjdfSet 
	 * @return 
	 * 
	 */
	protected KElement setBaseResource(final JDFElement rl, final JDFResource r, final KElement xjdfSet)
	{
		final JDFAttributeMap map = r.getPartMap();
		SetHelper sh = new SetHelper(xjdfSet);
		KElement newLeaf = sh.getCreatePartition(map, false).getPartition();
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
		// JDFAttributeMap attMap=leaf.getAttributeMap();
		// attMap.remove("ID");
		setAmountPool(rl, newLeaf, partMap);

		// retain spawn information
		if (jdfToXJDF.bRetainSpawnInfo && leaf.hasAttribute(AttributeName.SPAWNIDS))
		{
			final KElement spawnInfo = newLeaf.getDocRoot().getCreateElement(jdfToXJDF.m_spawnInfo, null, 0);
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
		if (rl == null)
		{
			return;
		}
		JDFAmountPool ap = (JDFAmountPool) rl.getElement(ElementName.AMOUNTPOOL);
		if (ap == null)
		{
			JDFAttributeMap amounts = rl.getAttributeMap().reduceMap(JDFToXJDF.amountAttribs);
			if (amounts.size() > 0)
			{
				ap = (JDFAmountPool) newLeaf.getCreateElement(ElementName.AMOUNTPOOL);
				for (String key : amounts.keySet())
				{
					ap.setPartAttribute(key, amounts.get(key), null, partMap);
					rl.removeAttribute(key);
				}
			}
		}
		else
		{
			final VElement vPartAmounts = ap.getMatchingPartAmountVector(partMap);
			if (vPartAmounts != null && vPartAmounts.size() > 0)
			{
				ap = (JDFAmountPool) newLeaf.getCreateElement(ElementName.AMOUNTPOOL);
				for (KElement e : vPartAmounts)
				{
					JDFPartAmount pa = (JDFPartAmount) e;
					JDFPartAmount paNew = ap.getCreatePartAmount(pa.getPartMapVector());
					paNew.setAttributes(pa);
				}
			}
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
		resourceSet.setAttribute("Name", jdfToXJDF.getSetName(linkRoot));
		resourceSet.setAttributes(rl);
		//TODO orientation + coordinate system stuff
		resourceSet.removeAttribute(AttributeName.RREF);
		resourceSet.removeAttribute(AttributeName.RSUBREF);
		resourceSet.removeAttribute(AttributeName.AMOUNT);
		resourceSet.removeAttribute(AttributeName.AMOUNTPRODUCED);
		resourceSet.removeAttribute(AttributeName.MAXAMOUNT);
		resourceSet.removeAttribute(AttributeName.ACTUALAMOUNT);

		if (rl instanceof JDFResourceLink)
		{
			final JDFResourceLink resLink = (JDFResourceLink) rl;
			final JDFNode rootIn = resLink.getJDFRoot();

			final JDFResource resInRoot = rootIn == null ? linkRoot : (JDFResource) rootIn.getChildWithAttribute(null, AttributeName.ID, null, resLink.getrRef(), 0, false);
			if (resInRoot != null)
			{
				final VElement vCreators = resInRoot.getCreator(EnumUsage.Input.equals(resLink.getUsage()));
				if (vCreators != null)
				{
					final int size = vCreators.size();
					for (int i = 0; i < size; i++)
					{
						final JDFNode depNode = (JDFNode) vCreators.elementAt(i);
						final KElement dependent = resourceSet.appendElement("Dependent");
						dependent.setAttribute(AttributeName.JOBID, depNode.getJobID(true));
						dependent.copyAttribute(AttributeName.JMFURL, depNode, null, null, null);
						dependent.copyAttribute(AttributeName.JOBPARTID, depNode, null, null, null);
					}
				}
			}
		}
	}

}