/**
 * The CIP4 Software License, Version 1.0
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
package org.cip4.jdflib.extensions.xjdfwalker.xjdftojdf;

import java.util.Comparator;
import java.util.List;

import org.cip4.jdflib.core.AttributeName;
import org.cip4.jdflib.core.KElement;
import org.cip4.jdflib.core.VString;
import org.cip4.jdflib.extensions.ProductHelper;
import org.cip4.jdflib.extensions.ResourceHelper;
import org.cip4.jdflib.extensions.SetHelper;
import org.cip4.jdflib.node.JDFNode;
import org.cip4.jdflib.node.JDFNode.EnumType;
import org.cip4.jdflib.util.ContainerUtil;
import org.cip4.jdflib.util.StringUtil;

/**
 * @author Rainer Prosi, Heidelberger Druckmaschinen walker for the various resource sets
 */
public class WalkSet extends WalkXElement
{
	/**
	 *
	 */
	public WalkSet()
	{
		super();
	}

	/**
	 * @param xjdf
	 * @param jdf
	 *
	 * @return the current parent node
	 */
	@Override
	public KElement walk(final KElement xjdf, final KElement jdf)
	{
		if (jdf instanceof JDFNode)
		{
			final JDFNode parentNode = (JDFNode) jdf;
			final String procUsage = StringUtil.getNonEmpty(xjdf.getAttribute(AttributeName.PROCESSUSAGE));
			if (ProductHelper.PRODUCT.equals(procUsage))
			{
				if (!EnumType.Product.equals(parentNode.getEnumType()))
				{
					return null;
				}
			}
			final VString types = parentNode.getTypes();
			if (types == null || types.size() <= 1)
			{
				xjdf.removeAttribute(AttributeName.COMBINEDPROCESSINDEX);
			}
		}
		reorderResources(xjdf);
		return jdf;
	}

	/**
	 * ensure that lower level resources get processed first
	 *
	 * @param xjdf
	 */
	void reorderResources(final KElement xjdf)
	{
		final SetHelper h = new SetHelper(xjdf);
		final List<ResourceHelper> vp = h.getPartitions();
		if (ContainerUtil.size(vp) > 1)
		{
			vp.sort(new PartSizeComparator());
		}
		for (final ResourceHelper p : vp)
		{
			xjdf.moveElement(p.getRoot(), null);
		}
	}

	static class PartSizeComparator implements Comparator<ResourceHelper>
	{
		@Override
		public int compare(final ResourceHelper o1, final ResourceHelper o2)
		{
			return o1.getPartMap().size() - o2.getPartMap().size();
		}
	}

	/**
	 *
	 * @param e
	 * @return
	 */
	protected String getJDFResName(final KElement e)
	{
		final SetHelper h = new SetHelper(e);
		final String name = h.getName();
		return name;
	}

	/**
	 * @see org.cip4.jdflib.elementwalker.BaseWalker#matches(org.cip4.jdflib.core.KElement)
	 * @param toCheck
	 * @return true if it matches
	 */
	@Override
	public boolean matches(final KElement toCheck)
	{
		return SetHelper.isSet(toCheck);
	}

}