/**
 * The CIP4 Software License, Version 1.0
 *
 * Copyright (c) 2001-2021 The International Cooperation for the Integration of
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
import org.cip4.jdflib.core.JDFPartAmount;
import org.cip4.jdflib.core.JDFResourceLink;
import org.cip4.jdflib.core.KElement;
import org.cip4.jdflib.core.VString;
import org.cip4.jdflib.datatypes.JDFAttributeMap;
import org.cip4.jdflib.datatypes.VJDFAttributeMap;
import org.cip4.jdflib.extensions.ResourceHelper;
import org.cip4.jdflib.extensions.XJDFConstants;
import org.cip4.jdflib.resource.JDFPart;
import org.cip4.jdflib.util.StringUtil;

/**
 * @author Rainer Prosi, Heidelberger Druckmaschinen walker for Media elements
 */
public class WalkPartAmount extends WalkXElement
{
	/**
	 *
	 */
	public WalkPartAmount()
	{
		super();
	}

	/**
	 * @see org.cip4.jdflib.elementwalker.BaseWalker#matches(org.cip4.jdflib.core.KElement)
	 * @param toCheck
	 * @return true if it matches
	 */
	@Override
	public boolean matches(final KElement toCheck)
	{
		return toCheck instanceof JDFPartAmount;
	}

	/**
	 * @param map
	 * @param xjdfPartAmount
	 * @return the created resource
	 */
	private void walkSingle(final JDFAttributeMap map, final KElement xjdfPartAmount, final KElement jdfAmountPool)
	{
		if (xjdfPartAmount != null && map != null)
		{
			xjdfPartAmount.removeAttributes(null);
			final String condition = map.remove(AttributeName.CONDITION);
			xjdfPartAmount.setAttributes(map);
			final JDFPartAmount pa = (JDFPartAmount) xjdfPartAmount;
			final JDFPartAmount newPA = (JDFPartAmount) super.walk(xjdfPartAmount, jdfAmountPool);
			VJDFAttributeMap vPartMap = pa.getPartMapVector();
			if (vPartMap == null)
			{
				vPartMap = new VJDFAttributeMap();
			}
			if (condition != null)
			{
				vPartMap.put(AttributeName.CONDITION, condition);
			}
			final KElement res = xjdfPartAmount.getDeepParent((String) null, 2);
			final ResourceHelper rh = ResourceHelper.getHelper(res);
			final VJDFAttributeMap rlMap = rh == null ? null : rh.getPartMapVector();
			if (rlMap != null)
			{
				final JDFAttributeMap commonMap = rlMap.getCommonMap();
				if (commonMap != null)
				{
					vPartMap.put(commonMap);
				}
			}

			newPA.setPartMapVector(vPartMap);
		}
	}

	/**
	 * @param xjdfPartAmount
	 * @return the created resource
	 */
	@Override
	public KElement walk(final KElement xjdfPartAmount, final KElement jdfAmountPool)
	{
		final VJDFAttributeMap split = splitWaste(xjdfPartAmount, true);

		if (split.size() <= 1)
		{
			final JDFPart part = (JDFPart) xjdfPartAmount.getElement(ElementName.PART);
			final JDFAttributeMap partMap = part == null ? null : part.getPartMap();
			if (partMap == null || partMap.isEmpty())
			{
				final KElement parent = jdfAmountPool.getParentNode_KElement();
				if (parent instanceof JDFResourceLink)
				{
					final JDFResourceLink rl = (JDFResourceLink) parent;
					rl.copyAttribute(AttributeName.AMOUNT, xjdfPartAmount);
					rl.copyAttribute(AttributeName.ACTUALAMOUNT, xjdfPartAmount);
					rl.copyAttribute(AttributeName.MAXAMOUNT, xjdfPartAmount);
					jdfAmountPool.deleteNode();
					return null;
				}
			}
		}
		for (final JDFAttributeMap map : split)
		{
			walkSingle(map, xjdfPartAmount, jdfAmountPool);
		}

		return null;
	}

	/**
	 *
	 * @param xjdfPartAmount
	 * @param bGood
	 * @return
	 */
	private VJDFAttributeMap splitWaste(final KElement xjdfPartAmount, final boolean bGood)
	{
		final VJDFAttributeMap vMap = new VJDFAttributeMap();
		final JDFAttributeMap map = xjdfPartAmount.getAttributeMap();
		final boolean bAmount = map.containsKey(AttributeName.ACTUALAMOUNT) || map.containsKey(AttributeName.AMOUNT);
		final boolean bWaste = map.containsKey(XJDFConstants.Waste);
		String wasteKey = map.remove(XJDFConstants.WasteDetails);
		if (StringUtil.isEmpty(wasteKey))
		{
			wasteKey = XJDFConstants.Waste;
		}
		if (bAmount && bWaste)
		{
			map.put(AttributeName.CONDITION, "Good");
		}
		if (bWaste)
		{
			final JDFAttributeMap mapWaste = map.clone();
			mapWaste.put(AttributeName.CONDITION, wasteKey);
			mapWaste.put(AttributeName.AMOUNT, mapWaste.remove(XJDFConstants.Waste));
			mapWaste.remove(AttributeName.MINAMOUNT);
			mapWaste.remove(AttributeName.MAXAMOUNT);
			vMap.add(mapWaste);
		}
		if (bAmount)
		{
			map.remove(XJDFConstants.Waste);
			vMap.add(map);
		}
		return vMap;
	}

	/**
	 * @see org.cip4.jdflib.elementwalker.BaseWalker#getElementNames()
	 */
	@Override
	public VString getElementNames()
	{
		return VString.getVString(ElementName.PARTAMOUNT, null);
	}
}