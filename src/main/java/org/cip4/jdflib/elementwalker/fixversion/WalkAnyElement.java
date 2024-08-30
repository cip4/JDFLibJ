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
package org.cip4.jdflib.elementwalker.fixversion;

import org.cip4.jdflib.core.AttributeName;
import org.cip4.jdflib.core.KElement;
import org.cip4.jdflib.core.StringArray;
import org.cip4.jdflib.core.VString;
import org.cip4.jdflib.elementwalker.BaseWalker;
import org.cip4.jdflib.span.JDFTimeSpan;
import org.cip4.jdflib.util.ContainerUtil;
import org.cip4.jdflib.util.JDFDate;
import org.cip4.jdflib.util.StringUtil;

/**
 * the resource walker note the naming convention Walkxxx so that it is automagically instantiated by the super classes
 *
 * @author prosirai
 *
 */
public class WalkAnyElement extends BaseWalker
{

	@Override
	public KElement walk(final KElement e, final KElement trackElem)
	{
		return null;
	}

	/**
	 * fills this into the factory
	 */
	public WalkAnyElement()
	{
		super();
		fixVersion = null;
	}

	/**
	 *
	 */
	FixVersionImpl fixVersion;

	/**
	 * fills this into the factory
	 *
	 * @param fixVersion
	 */
	public void setParent(final FixVersionImpl fixVersion)
	{
		this.fixVersion = fixVersion;
	}

	/**
	 * @param el
	 * @param key
	 * @param value
	 */
	void fixDateTime(final KElement el, final String key, final String value, final boolean zapp)
	{
		int hour = -1;
		int minute = 0;
		String check = key;
		if (el instanceof JDFTimeSpan)
		{
			check = el.getLocalName();
		}
		final String timeToken = StringUtil.token(value, 1, "T");
		if (check != null && StringUtil.length(timeToken) < 9)
		{
			hour = StringUtil.parseInt(StringUtil.substring(timeToken, 0, 2), -1);
			minute = StringUtil.parseInt(StringUtil.substring(timeToken, 3, 5), 0);
			if (hour < 0)
			{
				if (check.endsWith(AttributeName.END) || AttributeName.REQUIRED.equals(check))
				{
					hour = fixVersion.lasthour;
				}
				else if (check.endsWith(AttributeName.START) || AttributeName.EARLIEST.equals(check))
				{
					hour = fixVersion.firsthour;
				}
			}
		}
		final VString vals = StringUtil.tokenize(value, null, false);
		if (ContainerUtil.size(vals) > 1)
		{
			final StringArray newVals = new StringArray();
			boolean ok = false;
			for (final String val : vals)
			{
				final JDFDate d = fixDate(val, hour, minute);
				ok = ok || d != null;
				newVals.add(d == null ? val : d.getDateTimeISO());
			}
			if (ok)
			{
				el.setAttribute(key, newVals.getString());
			}
		}
		else
		{
			final JDFDate d = fixDate(value, hour, minute);
			if (d != null)
			{
				el.setAttribute(key, d.getDateTimeISO());
			}
			else if (zapp)
			{
				el.removeAttribute(key);
			}
		}
	}

	private JDFDate fixDate(final String value, final int hour, final int minute)
	{
		final JDFDate d = value.contains("INF") || StringUtil.isNumber(value) ? null : JDFDate.createDate(value);
		if (d != null)
		{
			if (hour > 0 || fixVersion.newYear > 0)
			{
				if (hour >= 0 && d.getHour() == JDFDate.getDefaultHour() && d.getMinute() == 0)
				{
					d.setTime(hour, minute, 0);
				}
				if (fixVersion.newYear > 0)
				{
					d.setYear(fixVersion.newYear);
				}
			}
		}
		return d;
	}

}