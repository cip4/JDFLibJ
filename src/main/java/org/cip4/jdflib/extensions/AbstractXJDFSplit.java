/*
 * The CIP4 Software License, Version 1.0
 *
 *
 * Copyright (c) 2001-2018 The International Cooperation for the Integration of Processes in Prepress, Press and Postpress (CIP4). All rights reserved.
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
package org.cip4.jdflib.extensions;

import java.util.Collection;
import java.util.Vector;

import org.cip4.jdflib.core.AttributeName;
import org.cip4.jdflib.core.ElementName;
import org.cip4.jdflib.core.JDFConstants;
import org.cip4.jdflib.core.JDFResourceLink.EnumUsage;
import org.cip4.jdflib.core.VString;
import org.cip4.jdflib.datatypes.JDFIntegerList;
import org.cip4.jdflib.ifaces.IXJDFSplit;
import org.cip4.jdflib.node.JDFNode.EnumType;
import org.cip4.jdflib.node.LinkInfo;
import org.cip4.jdflib.node.LinkInfoMap;
import org.cip4.jdflib.node.LinkValidatorMap;
import org.cip4.jdflib.util.StringUtil;

/**
 * @author rainer prosi
 *
 */
public abstract class AbstractXJDFSplit implements IXJDFSplit
{

	public AbstractXJDFSplit()
	{
		super();
	}

	private static VString productResNames = new VString(new String[] { ElementName.CUSTOMERINFO, ElementName.NODEINFO });

	/**
	 * @see org.cip4.jdflib.ifaces.IXJDFSplit#splitXJDF(org.cip4.jdflib.extensions.XJDFHelper)
	 */
	@Override
	public abstract Collection<XJDFHelper> splitXJDF(XJDFHelper root);

	/**
	 * update the Usage of resource links according to the value of types
	 *
	 * @param xjdf
	 */
	@Deprecated
	protected void fixInOutLinks(final XJDFHelper xjdf)
	{
		fixInOutLinks(xjdf, xjdf.getTypes());
	}

	/**
	 * update the Usage of resource links according to the value of types
	 *
	 * @param xjdf
	 */
	protected void fixInOutLinks(final XJDFHelper xjdf, final VString allTypes)
	{
		final LinkInfoMap map = getLinkInfoMap(xjdf);
		if (map != null)
		{
			final Vector<SetHelper> sets = xjdf.getSets();
			if (sets != null)
			{
				final VString types = xjdf.getTypes();
				for (final SetHelper set : sets)
				{
					final SetHelper set2 = matchesType(set, types, allTypes);
					fixInOutLink(set2, map);
				}
			}
		}
	}

	/**
	 *
	 * @param set the set to keep or zapp
	 * @param types from the xjdf root
	 * @param allTypes
	 * @return null if deleted or no further processing is required
	 */
	@Deprecated
	protected SetHelper matchesType(final SetHelper set, final VString types)
	{
		return matchesType(set, types, types);
	}

	/**
	 *
	 * @param set the set to keep or zapp
	 * @param types from the xjdf root
	 * @param allTypes
	 * @return null if deleted or no further processing is required
	 */
	protected SetHelper matchesType(SetHelper set, final VString types, final VString allTypes)
	{
		if (set != null && types != null)
		{
			set = checkProcessUsage(set, types);
			set = checkCPI(set, types, allTypes);
			set = checkProduct(set, types);
		}
		return set;

	}

	/**
	 *
	 * @param set
	 * @param types
	 * @return
	 */
	protected SetHelper checkProduct(SetHelper set, final VString types)
	{
		if (set != null && types.contains(JDFConstants.PRODUCT))
		{
			final String name = set.getName();
			if (name == null || !getProductResources().contains(name))
			{
				set.deleteNode();
				set = null;
			}
		}
		return set;
	}

	/**
	 *
	 * @return
	 */
	protected VString getProductResources()
	{
		return productResNames;
	}

	protected SetHelper checkProcessUsage(SetHelper set, final VString types)
	{
		String processUsage = set.getProcessUsage();
		if (processUsage != null)
		{
			if ("EndCustomer".equals(processUsage) && types.contains(JDFConstants.PRODUCT))
			{
				processUsage = JDFConstants.PRODUCT;
				set = null;
			}
			else if (EnumType.getEnum(processUsage) != null)
			{
				if (!types.contains(processUsage))
				{
					set.deleteNode();
				}
				// we still flag null but do not delete to avoid further processing in case we found an explicit match
				set = null;
			}

		}
		return set;
	}

	/**
	 *
	 * @param set
	 * @param types
	 * @param allTypes
	 * @return
	 */
	protected SetHelper checkCPI(SetHelper set, final VString types, final VString allTypes)
	{
		if (set != null && allTypes != null)
		{
			final JDFIntegerList cpi = set.getCombinedProcessIndex();
			if (cpi != null)
			{
				boolean ok = false;
				for (int i = 0; i < cpi.size(); i++)
				{
					final int pos = cpi.getInt(i);
					final String proc = allTypes.get(pos);
					if (proc != null && types.contains(proc))
					{
						ok = true;
						break;
					}
				}
				if (!ok)
				{
					set.deleteNode();
					set = null;
				}
			}
		}
		return set;
	}

	/**
	 *
	 * @param xjdf
	 * @return
	 */
	protected LinkInfoMap getLinkInfoMap(final XJDFHelper xjdf)
	{
		final EnumType typ = xjdf.getType();
		final VString types = EnumType.ProcessGroup.equals(typ) ? xjdf.getTypes() : null;
		final LinkInfoMap map = LinkValidatorMap.getLinkValidatorMap().getLinkInfoMap(typ, types);
		return map;
	}

	/**
	 *
	 * @param set
	 * @param map
	 */
	protected void fixInOutLink(final SetHelper set, final LinkInfoMap map)
	{
		if (map == null || set == null)
		{
			return;
		}
		final String name = getName(set);
		final LinkInfo li = map.getStar(name, true);
		if (li == null)
		{
			// we never delete in case of refs; we only zapp the usage so that they disappear if unreferenced
			set.setUsage(null);
		}
		else
		{
			fixUsage(set, li);
			fixCPI(set, li);
		}
	}

	/**
	 * we need to map to jdf names in order to use linkmap
	 *
	 * @param set
	 * @return
	 */
	protected String getName(final SetHelper set)
	{
		String name = set.getName();
		if (ElementName.COLOR.equals(name))
			name = ElementName.COLORPOOL;
		else if (XJDFConstants.Content.equals(name))
			name = ElementName.PAGELIST;
		return name;
	}

	/**
	 * fix the combinedprocessindex - initial implementation is delete only
	 *
	 * @param set
	 * @param li
	 */
	protected void fixCPI(final SetHelper set, final LinkInfo li)
	{
		set.removeAttribute(AttributeName.COMBINEDPROCESSINDEX, null);
	}

	/**
	 *
	 * @param set
	 * @param li
	 */
	protected void fixUsage(final SetHelper set, final LinkInfo li)
	{
		EnumUsage usage = set.getUsage();
		final String processUsage = set.getProcessUsage();
		if (!li.isValidLink(usage, processUsage, 1))
		{
			if (usage == null || !li.isValidLink(usage.invert(), processUsage, 1))
			{
				set.deleteNode();
			}
			else if (usage != null)
			{
				set.setUsage(usage.invert());
			}
		}
		else if (usage == null)
		{
			usage = li.getUsage(processUsage);
			if (usage == null)
			{
				usage = li.getUsage(null);
			}
			if (usage == null)
			{
				final boolean inReq = li.isRequired(EnumUsage.Input);
				final boolean outReq = li.isRequired(EnumUsage.Output);
				if (inReq ^ outReq)
				{
					usage = inReq ? EnumUsage.Input : EnumUsage.Output;
				}
			}
			set.setUsage(usage);
		}
	}

	/**
	 *
	 * @param v
	 */
	protected void consolidateExchangeResources(final Vector<XJDFHelper> v)
	{
		if (v == null || v.size() < 2)
			return;
		for (int i = 1; i < v.size(); i++)
		{
			final XJDFHelper h0 = v.get(i - 1);
			final XJDFHelper h1 = v.get(i);
			final Vector<SetHelper> sets0 = h0.getSets(null, EnumUsage.Output);
			final Vector<SetHelper> sets1 = h1.getSets(null, EnumUsage.Output);
			if (sets0 != null && sets1 != null)
			{
				for (final SetHelper set0 : sets0)
				{
					for (final SetHelper set1 : sets1)
					{
						if (set0.isEqual(set1))
						{
							consolidateExchangeResource(h0, set0, h1, set1);
						}
					}
				}
			}
		}
	}

	/**
	 * remove all but the last exchange resource and replace internal links with dummies
	 *
	 * @param h0
	 * @param set0
	 * @param h1
	 * @param set1
	 */
	protected void consolidateExchangeResource(final XJDFHelper h0, final SetHelper set0, final XJDFHelper h1, final SetHelper set1)
	{
		final String newID = "ID_Ex_" + StringUtil.setvString(h0.getTypes(), "_", null, null);
		final SetHelper set0Out = h0.appendSet(set0.getFamily().name(), getName(set0), EnumUsage.Output);
		set0Out.setID(newID);
		set0.deleteNode();
		final SetHelper newSet = new SetHelper(h1.getRoot().copyElement(set0Out.getRoot(), null));
		newSet.setUsage(EnumUsage.Input);
	}
}
