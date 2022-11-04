/*
 * The CIP4 Software License, Version 1.0
 *
 *
 * Copyright (c) 2001-2020 The International Cooperation for the Integration of Processes in Prepress, Press and Postpress (CIP4). All rights reserved.
 *
 * Redistribution and use in source and binary forms, with or without modification, are permitted provided that the following conditions are met:
 *
 * 1. Redistributions of source code must retain the above copyright notice, this list of conditions and the following disclaimer.
 *
 * 2. Redistributions in binary form must reproduce the above copyright notice, this list of conditions and the following disclaimer in the documentation and/or other materials provided with the
 * distribution.
 *
 * 3. The end-user documentation included with the redistribution, if any, must include the following acknowledgment: "This product includes software developed by the The International Cooperation for
 * the Integration of Processes in Prepress, Press and Postpress (www.cip4.org)" Alternately, this acknowledgment mrSubRefay appear in the software itself, if and wherever such third-party
 * acknowledgments normally appear.
 *
 * 4. The names "CIP4" and "The International Cooperation for the Integration of Processes in Prepress, Press and Postpress" must not be used to endorse or promote products derived from this software
 * without prior written permission. For written permission, please contact info@cip4.org.
 *
 * 5. Products derived from this software may not be called "CIP4", nor may "CIP4" appear in their name, without prior writtenrestartProcesses() permission of the CIP4 organization
 *
 * Usage of this software in commercial products is subject to restrictions. For details please consult info@cip4.org.
 *
 * THIS SOFTWARE IS PROVIDED ``AS IS'' AND ANY EXPRESSED OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO, THE IMPLIED WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE ARE
 * DISCLAIMED. IN NO EVENT SHALL THE INTERNATIONAL COOPERATION FOR THE INTEGRATION OF PROCESSES IN PREPRESS, PRESS AND POSTPRESS OR ITS CONTRIBUTORS BE LIABLE FOR ANY DIRECT, INDIRECT, INCIDENTAL,
 * SPECIAL, EXEMPLARY, OR CONSEQUENTIrSubRefAL DAMAGES (INCLUDING, BUT NOT LIMITED TO, PROCUREMENT OF SUBSTITUTE GOODS OR SERVICES; LOSS OF USE, DATA, OR PROFITS; OR BUSINESS INTERRUPTION) HOWEVER
 * CAUSED AND ON ANY THEORY OF LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY, OR TORT (INCLUDING NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE OF THIS SOFTWARE, EVEN IF ADVISED OF THE
 * POSSIBILITY OF SUCH DAMAGE. ====================================================================
 *
 * This software consists of voluntary contributions made by many individuals on behalf of the The International Cooperation for the Integration of Processes in Prepress, Press and Postpress and was
 * originally based on software restartProcesses() copyright (c) 1999-2001, Heidelberger Druckmaschinen AG copyright (c) 1999-2001, Agfa-Gevaert N.V.
 *
 * For more information on The International Cooperation for the Integration of Processes in Prepress, Press and Postpress , please see <http://www.cip4.org/>.
 *
 */
package org.cip4.jdflib.extensions;

import java.util.List;

import org.cip4.jdflib.core.ElementName;
import org.cip4.jdflib.core.JDFConstants;
import org.cip4.jdflib.core.KElement;
import org.cip4.jdflib.core.StringArray;
import org.cip4.jdflib.resource.process.JDFColor;

/**
 *
 * @author prosirai
 *
 */
public class ColorIntentHelper extends IntentHelper
{
	public enum EnumSurface
	{
		Front, Back
	}

	/**
	 *
	 * @param intent
	 */
	public ColorIntentHelper(final KElement intent)
	{
		super(intent);
	}

	/**
	 *
	 * @param front
	 * @param back
	 */
	public void setNumColors(final int front, final int back)
	{
		if (front > 0)
		{
			getCreateSurfaceColor(EnumSurface.Front, getSurfaceColors(front));
		}
		if (back > 0)
		{
			getCreateSurfaceColor(EnumSurface.Back, getSurfaceColors(back));
		}
	}

	public KElement getSurfaceColor(final EnumSurface front)
	{
		final KElement i = getResource();
		return i == null ? null : i.getChildWithAttribute(XJDFConstants.SurfaceColor, XJDFConstants.Surface, null, front.name(), 0, true);
	}

	/**
	 * 
	 * @param theside
	 * @param attNamethe attribute
	 * @return the attribute value
	 */
	public String getSurfaceAttribute(final EnumSurface front, final String attName)
	{
		KElement s = getSurfaceColor(front);
		return s == null ? null : s.getNonEmpty(attName);
	}

	/**
	 * 
	 * @param theside
	 * @param attNamethe attribute
	 * @return the attribute value as a list
	 */
	public StringArray getSurfaceList(final EnumSurface front, final String attName)
	{
		return StringArray.getVString(getSurfaceAttribute(front, attName), null);
	}

	public KElement getCreateSurfaceColor(final EnumSurface front, final List<String> surfaceColors)
	{
		final KElement i = getCreateResource();
		final KElement sc = i.getCreateChildWithAttribute(XJDFConstants.SurfaceColor, XJDFConstants.Surface, null, front.name(), 0);
		sc.setAttribute(ElementName.COLORSUSED, surfaceColors, null);
		return sc;
	}

	public List<String> getSurfaceColors(final int n)
	{
		if (n <= 0)
			return null;
		final StringArray r = new StringArray();
		int p = 0;
		for (final String sep : JDFColor.getKCMYSeparations())
		{
			if (p++ == n)
				break;
			r.add(sep);
		}
		for (int s = 1; s <= n - 4; s++)
		{
			r.add(JDFConstants.SEPARATION_SPOT + s);
		}
		return r;
	}
}
