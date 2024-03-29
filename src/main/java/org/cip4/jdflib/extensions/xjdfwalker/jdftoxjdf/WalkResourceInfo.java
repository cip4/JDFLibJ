/**
 * The CIP4 Software License, Version 1.0
 *
 * Copyright (c) 2001-2022 The International Cooperation for the Integration of Processes in Prepress, Press and Postpress (CIP4). All rights reserved.
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
package org.cip4.jdflib.extensions.xjdfwalker.jdftoxjdf;

import java.util.Collection;
import java.util.List;

import org.cip4.jdflib.auto.JDFAutoResourceInfo.EnumScope;
import org.cip4.jdflib.core.AttributeName;
import org.cip4.jdflib.core.ElementName;
import org.cip4.jdflib.core.JDFElement;
import org.cip4.jdflib.core.JDFPartAmount;
import org.cip4.jdflib.core.KElement;
import org.cip4.jdflib.core.VElement;
import org.cip4.jdflib.core.VString;
import org.cip4.jdflib.datatypes.JDFAttributeMap;
import org.cip4.jdflib.datatypes.VJDFAttributeMap;
import org.cip4.jdflib.extensions.ResourceHelper;
import org.cip4.jdflib.extensions.SetHelper;
import org.cip4.jdflib.extensions.XJDFConstants;
import org.cip4.jdflib.jmf.JDFResourceInfo;
import org.cip4.jdflib.pool.JDFAmountPool;
import org.cip4.jdflib.resource.JDFPart;
import org.cip4.jdflib.resource.JDFResource;

/**
 * @author Rainer Prosi, Heidelberger Druckmaschinen walker for the various resource sets
 */
public class WalkResourceInfo extends WalkJDFSubElement
{

	/**
	 *
	 */
	public WalkResourceInfo()
	{
		super();
	}

	/**
	 * @param resInfo
	 * @return the created resource
	 */
	@Override
	public KElement walk(final KElement resInfo, final KElement xjdf)
	{
		final JDFResourceInfo ri = (JDFResourceInfo) resInfo;
		final KElement eNew = super.walk(ri, xjdf);

		int nRes = 0;
		int n0 = 1;
		while (n0 > 0)
		{
			final Collection<KElement> vr = ri.getChildArray(null, null);
			n0 = 0;
			for (final KElement e : vr)
			{
				if (e instanceof JDFResource)
				{
					n0++;
					final JDFResource r = (JDFResource) e;
					if (nRes == 0)
					{
						setResource(ri, r, eNew);
					}
					else
					{
						setResource(null, r, eNew);
					}
					r.deleteNode();
					nRes++;
				}
			}

		}

		moveToResourceSet((JDFResourceInfo) eNew, ri);
		updateInfos((JDFResourceInfo) eNew);
		eNew.removeAttribute(AttributeName.RESOURCENAME);
		resInfo.removeChildrenByClass(JDFPart.class);
		return eNew;
	}

	void updateInfos(final JDFResourceInfo eNew)
	{
		final VElement v = eNew.getChildElementVector(XJDFConstants.ResourceSet, null);
		final int size = v.size();
		if (size > 1)
		{
			final KElement parent = eNew.getParentNode_KElement();
			if (parent != null)
			{
				int n = 0;
				final VElement vRI = new VElement();
				vRI.add(eNew);
				for (int i = 1; i < size; i++)
				{
					vRI.add(parent.copyElement(eNew, null));
				}
				for (final KElement ri : vRI)
				{
					for (int ii = size - 1; ii >= 0; ii--)
					{
						if (ii != n)
						{
							ri.removeChild(XJDFConstants.ResourceSet, null, ii);
						}
					}
					n++;
					ri.copyAttribute(AttributeName.RESOURCENAME, ri.getElement(XJDFConstants.ResourceSet), AttributeName.NAME, null, null);
				}
			}
		}
	}

	/**
	 *
	 * @param ri
	 * @param ap
	 */
	void moveToResourceSet(final JDFResourceInfo ri, final JDFResourceInfo jdfRI)
	{
		final SetHelper sh0 = SetHelper.getHelper(ri.getElement(XJDFConstants.ResourceSet));
		final VJDFAttributeMap vPartMap = getPartMaps(jdfRI, sh0);
		// needed for no amountpool in original
		setAmountPool(jdfRI, jdfRI, null);
		final JDFAmountPool ap = jdfRI.getAmountPool();
		setScope(ri, jdfRI, sh0);

		final KElement set = getSetElement(ri);

		final SetHelper sh = new SetHelper(set);
		final List<ResourceHelper> newParts = sh.getCreatePartitions(vPartMap, false);
		final boolean isEstimate = EnumScope.Estimate.equals(ri.getScope());
		for (final ResourceHelper ph : newParts)
		{
			processPart(ri, ap, newParts, isEstimate, ph);
		}
		if (newParts.size() > 1)
		{
			set.moveAttribute(XJDFConstants.ExternalID, ri);
		}
		else
		{
			ri.removeAttribute(XJDFConstants.ExternalID);
		}
		ri.removeChild(ElementName.AMOUNTPOOL, null, 0);
		jdfRI.removeChild(ElementName.AMOUNTPOOL, null, 0);
	}

	void processPart(final JDFResourceInfo ri, final JDFAmountPool ap, final List<ResourceHelper> newParts, final boolean isEstimate, final ResourceHelper ph)
	{
		JDFAmountPool apx = ph.getAmountPool();
		if (apx == null)
		{
			if (ap == null)
			{
				// TODO use correct amounts
				if (ri.hasNonEmpty(AttributeName.ACTUALAMOUNT))
				{
					ph.setAmount(ri.getActualAmount(), null, true);
				}
				if (newParts.size() == 1)
				{
					ph.getRoot().moveAttribute(XJDFConstants.ExternalID, ri);
				}
			}
			else if (ph.getRoot().getElement(ElementName.AMOUNTPOOL) == null)
			{
				ap.reducePartAmounts(ph.getPartMapVector());
				jdfToXJDF.walkTree(ap, ph.getRoot());
				ap.deleteNode();
			}
		}
		if (!isEstimate)
		{
			apx = ph.getAmountPool();

			final Collection<JDFPartAmount> cpa = apx == null ? null : apx.getAllPartAmount();
			if (cpa != null)
			{
				for (final JDFPartAmount pa : cpa)
				{
					pa.renameAttribute(AttributeName.ACTUALAMOUNT, AttributeName.AMOUNT);
					pa.renameAttribute("ActualWaste", AttributeName.WASTE);
				}
			}
		}
	}

	KElement getSetElement(final JDFResourceInfo ri)
	{
		String resName = ri.getXPathAttribute("ResourceSet/@Name", null);
		if (resName == null)
		{
			resName = ri.getResourceName();
		}

		KElement set = ri.getChildWithAttribute(XJDFConstants.ResourceSet, AttributeName.NAME, null, resName, 0, true);
		if (set == null)
		{
			set = ri.appendElement(XJDFConstants.ResourceSet);
			set.setAttribute(AttributeName.NAME, resName);
			set.setAttribute(AttributeName.DESCRIPTIVENAME, resName);
		}

		set.moveAttribute(AttributeName.PROCESSUSAGE, ri);
		set.moveAttribute(AttributeName.ORIENTATION, ri);
		set.moveAttribute(AttributeName.UNIT, ri);
		set.moveAttribute(AttributeName.USAGE, ri);
		if (ri.hasNonEmpty(AttributeName.DESCRIPTIVENAME))
			set.moveAttribute(AttributeName.DESCRIPTIVENAME, ri);
		return set;
	}

	void setScope(final JDFResourceInfo ri, final JDFResourceInfo jdfRI, final SetHelper sh0)
	{
		final boolean hasScope = jdfRI.hasNonEmpty(AttributeName.SCOPE);
		if (!hasScope)
		{
			boolean isJobScope;
			if (sh0 != null)
			{
				isJobScope = sh0.getAmountSum(true) <= 0;
			}
			else
			{
				isJobScope = jdfRI.getAmountPoolSumDouble(AttributeName.ACTUALAMOUNT, null) > 0;
			}
			isJobScope = isJobScope || jdfRI.getTotalAmount() > 0;
			final String value = isJobScope ? "Job" : "Estimate";
			ri.setAttribute(AttributeName.SCOPE, value);
		}
	}

	VJDFAttributeMap getPartMaps(final JDFResourceInfo jdfRI, final SetHelper sh0)
	{
		VJDFAttributeMap vPartMap = jdfRI.getPartMapVector();
		if (VJDFAttributeMap.isEmpty(vPartMap))
		{
			vPartMap = sh0 == null ? null : sh0.getPartMapVector();
		}
		else
		{
			final List<ResourceHelper> vp = sh0 == null ? null : sh0.getPartitionList();
			if (vp != null)
			{
				for (final ResourceHelper rh : vp)
				{
					VJDFAttributeMap m = rh.getPartMapVector();
					m = VJDFAttributeMap.isEmpty(m) ? vPartMap : m.getOrMaps(vPartMap);
					rh.setPartMapVector(m);
				}
			}

		}
		return vPartMap;
	}

	/**
	 * @see org.cip4.jdflib.elementwalker.BaseWalker#matches(org.cip4.jdflib.core.KElement)
	 * @param toCheck
	 * @return true if it matches
	 */
	@Override
	public boolean matches(final KElement toCheck)
	{
		return !jdfToXJDF.isRetainAll() && toCheck instanceof JDFResourceInfo;
	}

	/**
	 * @see org.cip4.jdflib.extensions.xjdfwalker.jdftoxjdf.WalkJDFSubElement#updateAttributes(org.cip4.jdflib.datatypes.JDFAttributeMap)
	 */
	@Override
	protected void updateAttributes(final JDFAttributeMap map)
	{
		map.remove(AttributeName.ACTUALAMOUNT);
		map.remove(AttributeName.AMOUNT);
		map.remove(AttributeName.AVAILABLEAMOUNT);
		map.remove(AttributeName.DEVICEID);
		map.remove(AttributeName.LOTCONTROLLED);
		map.remove(AttributeName.RESOURCEID);
		map.remove(AttributeName.STATUS);
		updateModule(map);
		super.updateAttributes(map);
	}

	/**
	 * @see org.cip4.jdflib.extensions.xjdfwalker.jdftoxjdf.WalkJDFElement#makeRefElements(org.cip4.jdflib.core.JDFElement)
	 */
	@Override
	void makeRefElements(final JDFElement je)
	{
		// nop
	}

	/**
	 * @see org.cip4.jdflib.elementwalker.BaseWalker#getElementNames()
	 */
	@Override
	public VString getElementNames()
	{
		return VString.getVString(ElementName.RESOURCEINFO, null);
	}
}