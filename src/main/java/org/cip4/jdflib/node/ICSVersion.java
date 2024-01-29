/*--------------------------------------------------------------------------------------------------
 * The CIP4 Software License, Version 1.0
 *
 *
 * Copyright (c) 2001-2024 The International Cooperation for the Integration of 
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
 */
package org.cip4.jdflib.node;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import org.cip4.jdflib.core.JDFConstants;
import org.cip4.jdflib.core.JDFElement.EnumVersion;
import org.cip4.jdflib.core.StringArray;
import org.cip4.jdflib.util.ContainerUtil;
import org.cip4.jdflib.util.StringUtil;

/**
 * @author prosirai
 */
public class ICSVersion
{

	public int getLevel()
	{
		return level;
	}

	public EnumVersion getVersion()
	{
		return version;
	}

	public String getIcs()
	{
		return ics;
	}

	private final int level;
	private final EnumVersion version;
	private final String ics;

	/**
	 * @param _theNode the jdf node to parse
	 * @param vParts the job part to search for, if null don't filter
	 * @param inlineUpdates replace all updated audits with the updated version
	 */
	public ICSVersion(final String ics, final int level, final EnumVersion version)
	{
		super();
		this.ics = ics;
		this.version = version;
		this.level = level;
	}

	@Override
	public int hashCode()
	{
		return Objects.hash(ics, level, version);
	}

	@Override
	public boolean equals(final Object obj)
	{
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		final ICSVersion other = (ICSVersion) obj;
		return Objects.equals(ics, other.ics) && level == other.level && Objects.equals(version, other.version);
	}

	public static List<ICSVersion> getVersions(final String input)
	{
		final ArrayList<ICSVersion> ret = new ArrayList<>();
		final StringArray versions = StringArray.getVString(input, null);
		if (versions != null)
		{
			for (final String ics : versions)
			{
				ContainerUtil.add(ret, getVersion(ics));
			}
		}
		return ret;
	}

	public static ICSVersion getVersion(final String in)
	{
		final int li = StringUtil.index(in, "_L", 0);
		if (li > 0)
		{
			if (in.length() >= li + 7)
			{
				final String ics = in.substring(0, li);
				final int level = StringUtil.parseInt(in.substring(li + 2, li + 3), -1);
				final EnumVersion v = level >= 0 ? EnumVersion.getEnum(in.substring(li + 4, li + 7)) : null;
				return v == null ? null : new ICSVersion(ics, level, v);

			}

		}
		return null;
	}

	@Override
	public String toString()
	{
		return ics + "_L" + level + JDFConstants.HYPHEN + version;
	}

	/**
	 * get a base ics of the same version family
	 * 
	 * @param dependentICS
	 * @param depLevel
	 * @return
	 */
	public ICSVersion getBase(final String dependentICS, final int depLevel)
	{
		return new ICSVersion(dependentICS, depLevel, version);
	}
}
