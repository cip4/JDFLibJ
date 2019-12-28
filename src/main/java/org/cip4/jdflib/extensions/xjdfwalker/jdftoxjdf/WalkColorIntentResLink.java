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
package org.cip4.jdflib.extensions.xjdfwalker.jdftoxjdf;

import java.util.List;

import org.cip4.jdflib.auto.JDFAutoPart.EnumSide;
import org.cip4.jdflib.core.AttributeName;
import org.cip4.jdflib.core.ElementName;
import org.cip4.jdflib.core.JDFElement;
import org.cip4.jdflib.core.KElement;
import org.cip4.jdflib.datatypes.JDFXYPair;
import org.cip4.jdflib.extensions.XJDFConstants;
import org.cip4.jdflib.resource.JDFPart;
import org.cip4.jdflib.resource.JDFResource;

/**
 *
 *
 * @author rainer prosi
 * @date Feb 26, 2013
 */
public class WalkColorIntentResLink extends WalkResLink
{

	/**
	 *
	 */
	public WalkColorIntentResLink()
	{
		super();
	}

	/**
	 *
	 * @see org.cip4.jdflib.extensions.xjdfwalker.jdftoxjdf.WalkResLink#setResource(org.cip4.jdflib.core.JDFResourceLink, org.cip4.jdflib.resource.JDFResource, org.cip4.jdflib.core.KElement)
	 */
	@Override
	protected List<KElement> setResource(final JDFElement rl, final JDFResource linkTarget, final KElement xjdf)
	{
		final List<KElement> v = super.setResource(rl, linkTarget, xjdf);
		KElement thecolorIntent = null;
		for (final KElement e1 : v)
		{
			final JDFPart part = (JDFPart) e1.getElement(ElementName.PART);
			final KElement colorIntent = e1.getElement(ElementName.COLORINTENT);
			if (thecolorIntent == null)
				thecolorIntent = colorIntent;

			final EnumSide side = part == null ? EnumSide.Front : part.getSide();
			final boolean both = part == null;
			if (thecolorIntent != null)
			{
				final KElement surfaceColor = thecolorIntent.getCreateChildWithAttribute(XJDFConstants.SurfaceColor, XJDFConstants.Surface, null, side.getName(), 0);
				fixNumColors(surfaceColor, colorIntent);
				if (part != null)
				{
					part.deleteNode();
				}
				surfaceColor.setAttributes(colorIntent.getAttributeMap());
				thecolorIntent.removeAttributes(null);
				surfaceColor.moveArray(colorIntent.getChildArray(null, null), null);
				if (both)
				{
					thecolorIntent.copyElement(surfaceColor, null).setAttribute(XJDFConstants.Surface, EnumSide.Back.getName());
				}
			}
		}

		return v;

	}

	/**
	 * move numColors to an xypair
	 *
	 * @param surfaceColor
	 * @param colorIntent
	 */
	private void fixNumColors(final KElement surfaceColor, final KElement colorIntent)
	{
		final int nCols = colorIntent.getIntAttribute(AttributeName.NUMCOLORS, null, -1);
		if (nCols >= 0)
		{
			final KElement parentCI = surfaceColor.getParentNode_KElement();
			JDFXYPair xy = JDFXYPair.createXYPair(parentCI.getAttribute(AttributeName.NUMCOLORS));
			if (xy == null)
			{
				xy = new JDFXYPair(0, 0);
			}
			final String surface = surfaceColor.getAttribute("Surface");
			if ("Both".equals(surface) || "Front".equals(surface))
			{
				xy.setX(nCols);
			}
			if ("Both".equals(surface) || "Back".equals(surface))
			{
				xy.setY(nCols);
			}
			parentCI.setAttribute(AttributeName.NUMCOLORS, xy.toString());
		}
	}

	/**
	 *
	 * @see org.cip4.jdflib.extensions.xjdfwalker.jdftoxjdf.WalkResLink#walk(org.cip4.jdflib.core.KElement, org.cip4.jdflib.core.KElement)
	 */
	@Override
	public KElement walk(final KElement jdf, final KElement xjdf)
	{
		final KElement e = super.walk(jdf, xjdf);
		return e;
	}

	/**
	 *
	 * @see org.cip4.jdflib.extensions.xjdfwalker.jdftoxjdf.WalkResLink#matches(org.cip4.jdflib.core.KElement)
	 */
	@Override
	public boolean matches(final KElement toCheck)
	{
		return !jdfToXJDF.isRetainAll() && super.matches(toCheck) && "ColorIntentLink".equals(toCheck.getLocalName());
	}

}