/*
 * The CIP4 Software License, Version 1.0
 *
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
 *    Alternately, this acknowledgment mrSubRefay appear in the software itself,
 *    if and wherever such third-party acknowledgments normally appear.
 *
 * 4. The names "CIP4" and "The International Cooperation for the Integration of
 *    Processes in  Prepress, Press and Postpress" must
 *    not be used to endorse or promote products derived from this
 *    software without prior written permission. For written
 *    permission, please contact info@cip4.org.
 *
 * 5. Products derived from this software may not be called "CIP4",
 *    nor may "CIP4" appear in their name, without prior writtenrestartProcesses()
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
 * SPECIAL, EXEMPLARY, OR CONSEQUENTIrSubRefAL DAMAGES (INCLUDING, BUT NOT
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
 * originally based on software restartProcesses()
 * copyright (c) 1999-2001, Heidelberger Druckmaschinen AG
 * copyright (c) 1999-2001, Agfa-Gevaert N.V.
 *
 * For more information on The International Cooperation for the
 * Integration of Processes in  Prepress, Press and Postpress , please see
 * <http://www.cip4.org/>.
 *
 */
package org.cip4.jdflib.extensions;

import java.util.Collection;
import java.util.Vector;

import org.cip4.jdflib.core.VString;
import org.cip4.jdflib.util.ContainerUtil;
import org.cip4.jdflib.util.StringUtil;

/**
 *
 * XJDF splitter that splits based on the types list
 *
 * @author rainer prosi
 *
 */
public class ProcessXJDFSplit extends AbstractXJDFSplit
{
	final Vector<VString> groups;

	/**
	 *
	 */
	public ProcessXJDFSplit()
	{
		super();
		groups = new Vector<VString>();
	}

	/**
	 *
	 * @see org.cip4.jdflib.extensions.AbstractXJDFSplit#splitXJDF(org.cip4.jdflib.extensions.XJDFHelper)
	 */
	@Override
	public Collection<XJDFHelper> splitXJDF(XJDFHelper root)
	{
		Vector<VString> newTypes = splitTypes(root);
		Vector<XJDFHelper> ret = new Vector<XJDFHelper>();
		if (newTypes != null && newTypes.size() > 0)
		{
			VString allTypes = root.getTypes();
			for (VString types : newTypes)
			{
				XJDFHelper h = root.clone();
				h.setTypes(types);
				String jobPartID = root.getJobPartID();
				if (jobPartID == null)
					jobPartID = "Part_";
				h.setJobPartID(jobPartID + StringUtil.setvString(types));
				h.setID(null);
				fixInOutLinks(h, allTypes);
				ret.add(h);
			}
			consolidateExchangeResources(ret);
		}
		else
		{
			ret.add(root);
		}
		return ret;
	}

	/**
	 *
	 * @param root
	 *
	 * @return the list of types to split into, null is a flag for no split
	 */
	protected Vector<VString> splitTypes(XJDFHelper root)
	{
		Vector<VString> ret = new Vector<VString>();
		VString types = calcTypes(root);
		if (types == null || types.size() <= 1)
		{
			// only one element - no need to split
			return null;
		}
		while (types.size() > 0)
		{
			String first = types.get(0);
			VString overlap = null;
			for (VString group : groups)
			{
				if (group.contains(first))
				{
					overlap = types.getOverlapping(group);
					if (overlap != null)
					{
						break;
					}
				}
			}
			if (overlap == null)
			{
				overlap = new VString();
				overlap.add(first);
			}
			ret.add(overlap);
			types.removeAll(overlap);
		}
		return ret.size() == 0 ? null : ret;
	}

	/**
	 * ensure that we have the appropriate types setting for the given product list
	 * @param root
	 * @return
	 */
	protected VString calcTypes(XJDFHelper root)
	{
		VString types = root.getTypes();
		if (types == null)
			types = new VString();
		types.remove("Product");
		Vector<ProductHelper> productHelpers = root.getProductHelpers();
		if (productHelpers != null)
		{
			types.insertElementAt("Product", 0);
		}
		return types;
	}

	/**
	 * add a group to split together
	 * @param group
	 */
	public void addGroup(VString group)
	{
		if (group != null && !group.isEmpty())
		{
			groups.add(group);
			ContainerUtil.unify(groups);
		}
	}

	/**
	 *
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString()
	{
		return "ProcessXJDFSplit [groups=" + groups + "]";
	}
}
