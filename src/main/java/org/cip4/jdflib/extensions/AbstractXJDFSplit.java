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
package org.cip4.jdflib.extensions;

import java.util.Collection;
import java.util.Vector;

import org.cip4.jdflib.core.JDFResourceLink.EnumUsage;
import org.cip4.jdflib.core.VString;
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
	public void fixInOutLinks(XJDFHelper xjdf)
	{
		LinkInfoMap map = getLinkInfoMap(xjdf);
		if (map != null)
		{
			Vector<SetHelper> sets = xjdf.getSets();
			if (sets != null)
			{
				VString types = xjdf.getTypes();
				for (SetHelper set : sets)
				{
					SetHelper set2 = matchesType(set, types);
					fixInOutLink(set2, map);
				}
			}
		}
	}

	/**
	 * 
	 * @param set the set to keep or zapp
	 * @param types from the xjdf root
	 * @return null if deleted or no further processing is required
	 */
	protected SetHelper matchesType(SetHelper set, VString types)
	{
		if (set != null && types != null)
		{
			String processUsage = set.getProcessUsage();
			VString allUsages = StringUtil.tokenize(processUsage, null, false);
			if (allUsages != null && allUsages.size() > 0)
			{
				if (!allUsages.containsAny(types))
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
	 * @param xjdf
	 * @return
	 */
	protected LinkInfoMap getLinkInfoMap(XJDFHelper xjdf)
	{
		EnumType typ = xjdf.getType();
		VString types = EnumType.ProcessGroup.equals(typ) ? xjdf.getTypes() : null;
		LinkInfoMap map = LinkValidatorMap.getLinkValidatorMap().getLinkInfoMap(typ, types);
		return map;
	}

	/**
	 * 
	 * @param set
	 * @param map
	 */
	protected void fixInOutLink(SetHelper set, LinkInfoMap map)
	{
		if (map == null || set == null)
		{
			return;
		}
		String name = set.getName();
		LinkInfo li = map.getStar(name);
		if (li == null)
		{
			set.deleteNode();
		}
		else
		{
			fixUsage(set, li);
		}
	}

	/**
	 * 
	 * @param set
	 * @param li
	 */
	protected void fixUsage(SetHelper set, LinkInfo li)
	{
		EnumUsage usage = set.getUsage();
		String processUsage = set.getProcessUsage();
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
				boolean inReq = li.isRequired(EnumUsage.Input);
				boolean outReq = li.isRequired(EnumUsage.Output);
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
	protected void consolidateExchangeResources(Vector<XJDFHelper> v)
	{
		if (v == null || v.size() < 2)
			return;
		for (int i = 1; i < v.size(); i++)
		{
			XJDFHelper h0 = v.get(i - 1);
			XJDFHelper h1 = v.get(i);
			Vector<SetHelper> sets0 = h0.getSets(null, EnumUsage.Output);
			Vector<SetHelper> sets1 = h1.getSets(null, EnumUsage.Output);
			if (sets0 != null && sets1 != null)
			{
				for (SetHelper set0 : sets0)
				{
					for (SetHelper set1 : sets1)
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
	 * 
	 * @param h0 
	 * @param set0
	 * @param h1 
	 * @param set1
	 */
	protected void consolidateExchangeResource(XJDFHelper h0, SetHelper set0, XJDFHelper h1, SetHelper set1)
	{
		String newID = "ID_Ex_" + StringUtil.setvString(h0.getTypes(), "_", null, null);
		SetHelper set0Out = h0.appendSet(set0.getFamily().name(), set0.getName(), EnumUsage.Output);
		set0Out.setID(newID);
		set0.deleteNode();
		SetHelper newSet = new SetHelper(h1.getRoot().copyElement(set0Out.getRoot(), null));
		newSet.setUsage(EnumUsage.Input);
	}
}
