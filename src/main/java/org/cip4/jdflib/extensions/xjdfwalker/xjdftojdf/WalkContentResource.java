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
package org.cip4.jdflib.extensions.xjdfwalker.xjdftojdf;

import org.cip4.jdflib.core.AttributeName;
import org.cip4.jdflib.core.ElementName;
import org.cip4.jdflib.core.JDFResourceLink;
import org.cip4.jdflib.core.KElement;
import org.cip4.jdflib.datatypes.JDFAttributeMap;
import org.cip4.jdflib.extensions.PartitionHelper;
import org.cip4.jdflib.extensions.SetHelper;
import org.cip4.jdflib.node.JDFNode;
import org.cip4.jdflib.resource.JDFPageList;
import org.cip4.jdflib.resource.JDFPart;
import org.cip4.jdflib.resource.JDFResource;
import org.cip4.jdflib.resource.process.JDFPageData;

/**
 * @author Rainer Prosi, Heidelberger Druckmaschinen 
 * 
 * walker for the colorSet - this gets translated back to a colorpool
 */
public class WalkContentResource extends WalkXJDFResource
{
	/**
	 * 
	 */
	public WalkContentResource()
	{
		super();
	}

	/**
	 * 
	 * @param e
	 * @return
	 */
	@Override
	protected String getJDFResName(final SetHelper sh)
	{
		return ElementName.PAGELIST;
	}

	/**
	 * @param e
	 * @param jdfRes
	 * @return
	 */
	@Override
	protected KElement createPartition(final KElement e, final JDFResource jdfRes, final JDFPart part, JDFNode theNode)
	{
		JDFAttributeMap restMap = getPartMap(part);
		if (restMap.size() > 0)
		{
			return super.createPartition(e, jdfRes, part, theNode);
		}
		final JDFPageList pageList = (JDFPageList) jdfRes;
		final String pages = part.getAttribute(AttributeName.PAGENUMBER);
		final KElement pd = pageList.getChildWithAttribute(ElementName.PAGEDATA, AttributeName.PAGEINDEX, null, pages, 0, true);
		if (pd != null)
		{
			return null; // been here already
		}

		final JDFPageData rPageData = pageList.appendPageData();
		rPageData.copyAttribute(AttributeName.PAGEINDEX, part, AttributeName.PAGENUMBER, null, null);
		final JDFResourceLink rll = theNode.getLink(pageList, null);
		if (rll != null)
		{
			rll.removeChildren(ElementName.PART, null, null);
		}
		pageList.removeFromAttribute(AttributeName.PARTIDKEYS, AttributeName.PAGENUMBER, null, " ", -1);
		return rPageData;
	}

	/**
	 * @see org.cip4.jdflib.elementwalker.BaseWalker#matches(org.cip4.jdflib.core.KElement)
	 * @param toCheck
	 * @return true if it matches
	 */
	@Override
	public boolean matches(final KElement toCheck)
	{
		return PartitionHelper.isAsset(toCheck, "Content");
	}

	/**
	 * 
	 * @see org.cip4.jdflib.extensions.xjdfwalker.xjdftojdf.WalkXJDFResource#walk(org.cip4.jdflib.core.KElement, org.cip4.jdflib.core.KElement)
	 */
	@Override
	public KElement walk(final KElement e, final KElement trackElem)
	{
		final KElement rPart = super.walk(e, trackElem);
		if (rPart != null)
		{
			rPart.removeAttribute(AttributeName.STATUS);
		}
		return rPart;

	}

	/**
	 * @see org.cip4.jdflib.extensions.xjdfwalker.xjdftojdf.WalkXJDFResource#getPartMap(org.cip4.jdflib.resource.JDFPart)
	 */
	@Override
	JDFAttributeMap getPartMap(JDFPart part)
	{
		JDFAttributeMap partMap = super.getPartMap(part);
		partMap.remove(AttributeName.PAGENUMBER);
		return partMap;
	}

}