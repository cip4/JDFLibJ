/**
 * The CIP4 Software License, Version 1.0
 *
 * Copyright (c) 2001-2019 The International Cooperation for the Integration of Processes in Prepress, Press and Postpress (CIP4). All rights reserved.
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
package org.cip4.jdflib.node;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Vector;

import org.cip4.jdflib.core.ElementName;
import org.cip4.jdflib.core.JDFConstants;
import org.cip4.jdflib.util.ContainerUtil;

/**
 *
 * @author rainer prosi
 *
 */
public class LinkInfoMap extends HashMap<String, LinkInfo>
{

	final static String[] exchangeSet = new String[] { ElementName.COMPONENT, ElementName.RUNLIST, ElementName.EXPOSEDMEDIA };

	/**
	 *
	 */
	LinkInfoMap()
	{
		super();
	}

	/**
	 *
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * copy ctor
	 *
	 * @param info
	 */
	LinkInfoMap(final LinkInfoMap info)
	{
		final Vector<String> keys = ContainerUtil.getKeyVector(info);
		if (keys != null)
		{
			for (final String key : keys)
			{
				put(key, new LinkInfo(info.get(key)));
			}
		}
	}

	/**
	 *
	 * @see java.util.HashMap#put(java.lang.Object, java.lang.Object)
	 */
	@Override
	public LinkInfo put(final String key, final LinkInfo value)
	{
		final LinkInfo old = get(key);
		if (old != null)
		{
			old.merge(value);
			return old;
		}
		return super.put(key, value);
	}

	/**
	 *
	 * @param typeLinkInfo
	 */
	void merge(final LinkInfoMap typeLinkInfo)
	{
		if (typeLinkInfo != null)
		{
			final Vector<String> resNames = ContainerUtil.getKeyVector(typeLinkInfo);
			if (resNames != null)
			{
				for (final String resName : resNames)
				{
					final LinkInfo li = get(resName);
					LinkInfo li2 = typeLinkInfo.get(resName);

					if (li != null && li2 != null)
					{
						if (!LinkValidatorMap.getLinkValidatorMap().getGenericLinkNames().contains(resName) || !li.equals(li2))
						{
							if (li.hasOutput(null) && li2.hasInput(null))
							{
								li.makeOptional(false, true);
								li2 = new LinkInfo(li2);
								li2.makeOptional(true, false);
							}
							li.merge(li2);
						}
					}
					else if (li2 != null)
					{
						put(resName, new LinkInfo(li2));
					}
				}
			}
		}

	}

	public LinkInfo getStar(final String key)
	{
		return getStar(key, false);
	}

	/**
	 * get the entry for key or "*" if key does not exist
	 *
	 * also checks for "*" by merging both
	 *
	 * @see java.util.HashMap#get(java.lang.Object)
	 */
	public LinkInfo getStar(final String key, final boolean exchangeOnly)
	{
		LinkInfo li = super.get(key);
		final boolean wantStar = !exchangeOnly || Arrays.binarySearch(exchangeSet, key) >= 0;
		if (wantStar)
		{
			final LinkInfo li2 = super.get(JDFConstants.STAR);
			if (li == null)
			{
				if (li2 != null)
				{
					li = new LinkInfo(li2);
				}
			}
			else
			{
				li = new LinkInfo(li);
				li.merge(li2);
			}
		}
		return li;
	}

}
