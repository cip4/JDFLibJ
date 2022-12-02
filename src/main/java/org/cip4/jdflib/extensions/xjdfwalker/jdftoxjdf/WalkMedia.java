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

import org.cip4.jdflib.auto.JDFAutoMedia.EnumGrainDirection;
import org.cip4.jdflib.auto.JDFAutoMedia.EnumISOPaperSubstrate;
import org.cip4.jdflib.auto.JDFAutoMedia.EnumMediaType;
import org.cip4.jdflib.core.AttributeName;
import org.cip4.jdflib.core.ElementName;
import org.cip4.jdflib.core.KElement;
import org.cip4.jdflib.core.VString;
import org.cip4.jdflib.datatypes.JDFAttributeMap;
import org.cip4.jdflib.datatypes.JDFXYPair;
import org.cip4.jdflib.extensions.XJDFConstants;
import org.cip4.jdflib.resource.process.JDFMedia;
import org.cip4.jdflib.util.StringUtil;

/**
 *
 * @author Rainer Prosi, Heidelberger Druckmaschinen
 *
 */
public class WalkMedia extends WalkIntentResource
{
	/**
	 *
	 */
	public WalkMedia()
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
		return !jdfToXJDF.isRetainAll();
	}

	/**
	 * @see org.cip4.jdflib.elementwalker.BaseWalker#getElementNames()
	 */
	@Override
	public VString getElementNames()
	{
		return new VString(ElementName.MEDIA, null);
	}

	/**
	 * @see org.cip4.jdflib.extensions.xjdfwalker.jdftoxjdf.WalkJDFElement#updateAttributes(org.cip4.jdflib.datatypes.JDFAttributeMap)
	 */
	@Override
	protected void updateAttributes(final JDFAttributeMap map)
	{
		map.remove(AttributeName.PREPRINTED);
		final String mediaType = map.get(AttributeName.MEDIATYPE);
		if (mediaType == null || EnumMediaType.Unknown.getName().equals(mediaType))
		{
			map.put(AttributeName.MEDIATYPE, EnumMediaType.Other);
		}
		updateGrade(map, AttributeName.GRADE, AttributeName.ISOPAPERSUBSTRATE);
		updateGrade(map, "BackGrade", AttributeName.BACKISOPAPERSUBSTRATE);
		updateFluteGrain(AttributeName.FLUTEDIRECTION, map);
		updateFluteGrain(AttributeName.GRAINDIRECTION, map);
		map.renameKey(AttributeName.FRONTCOATINGS, XJDFConstants.Coating);
		map.renameKey(AttributeName.FRONTCOATINGS, XJDFConstants.CoatingDetail);
		map.renameKey(AttributeName.FRONTGLOSSVALUE, XJDFConstants.GlossValue);
		super.updateAttributes(map);
	}

	private void updateGrade(final JDFAttributeMap map, final String oldGrade, final String newGrade)
	{
		final String grade = map.remove(oldGrade);
		if (map.getNonEmpty(newGrade) == null)
		{
			final int igrade = StringUtil.parseInt(grade, 0);
			final EnumISOPaperSubstrate ips = JDFMedia.getIsoPaperFromGrade(igrade);
			if (ips != null)
			{
				map.put(newGrade, ips.getName());
			}
		}
	}

	private void updateFluteGrain(final String att, final JDFAttributeMap map)
	{
		String value = map.getNonEmpty(att);
		if (EnumGrainDirection.LongEdge.getName().equals(value))
		{
			final JDFXYPair dim = JDFXYPair.createXYPair(map.get(AttributeName.DIMENSION));
			if (dim != null)
			{
				value = dim.getX() > dim.getY() ? EnumGrainDirection.XDirection.getName() : EnumGrainDirection.YDirection.getName();
			}
			else
			{
				value = null;
			}
		}
		else if (EnumGrainDirection.ShortEdge.getName().equals(value))
		{
			final JDFXYPair dim = JDFXYPair.createXYPair(map.get(AttributeName.DIMENSION));
			if (dim != null)
			{
				value = dim.getX() < dim.getY() ? EnumGrainDirection.XDirection.getName() : EnumGrainDirection.YDirection.getName();
			}
			else
			{
				value = null;
			}
		}
		map.remove(att);
		map.putNotNull(att, value);
	}

}