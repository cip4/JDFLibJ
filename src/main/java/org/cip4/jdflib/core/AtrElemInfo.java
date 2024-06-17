/**
 *
 *
 * The CIP4 Software License, Version 1.0
 *
 *
 * Copyright (c) 2001-2024 The International Cooperation for the Integration of Processes in Prepress, Press and Postpress (CIP4). All rights reserved.
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
 * Created on Feb 9, 2005 by funkevol 09022005 VF initial version
 */
package org.cip4.jdflib.core;

import org.cip4.jdflib.core.JDFElement.EnumVersion;

/**
 * @author Rainer Prosi, Heidelberger Druckmaschinen *
 */
public abstract class AtrElemInfo
{
	// the number of maximum possible versions
	protected final static int MAXLOOP = 10;// 1 + EnumVersion.getEnumList().indexOf(JDFElement.getDefaultJDFVersion()); // index of default JDF version +1
	protected final long validity;

	/**
	 * Constructor
	 *
	 * @param s
	 */
	public AtrElemInfo(final long s)
	{
		final long masked = s & (0xFl << (4 * (MAXLOOP - 1)));
		// we have hand coded stuff - assume that the last valid version (32 bit) applies to later versions
		if (masked == 0)
		{
			long last = 0;
			long s2 = 0;

			for (int i = 0; i < MAXLOOP; i++)
			{
				final int i4 = 4 * i;
				long next = s & 0xFl << i4;
				next = next >> i4;
				if (next == 0)
				{
					next = last;
				}
				last = next;
				s2 += next << i4;
			}
			validity = s2;
		}
		else
		{
			validity = s;
		}
	}

	/**
	 * @return Returns the elemValidityStatus.
	 */
	public long getValidityStatus()
	{
		return validity;
	}

	/**
	 * @see java.lang.Object#toString()
	 * @return
	 */
	@Override
	public String toString()
	{
		return getClass().getSimpleName() + " " + Long.toHexString(validity) + " " + getFirstVersion().getName() + " - " + getLastVersion().getName();
	}

	/**
	 * get the first jdf version where an attribute of this type is valid
	 *
	 * @return the first valid version
	 */
	public EnumVersion getFirstVersion()
	{
		for (int i = 0; i < MAXLOOP; i++)
		{
			long masked = validity & (0xFl << (4 * i));
			masked = masked >> (4 * i);
			if (isMasked(masked))
			{
				return EnumVersion.getEnum(i + 1);
			}
		}
		return null;
	}

	abstract boolean isMasked(long masked);

	/**
	 * get the last jdf version where an element of this type is valid
	 *
	 * @return
	 */
	public EnumVersion getLastVersion()
	{
		for (int i = MAXLOOP - 1; i >= 0; i--)
		{
			long masked = validity & 0xFl << (4 * i);
			masked = masked >> (4 * i);
			if (masked == 2 || masked == 3)
			{
				EnumVersion ret = EnumVersion.getEnum(i + 1);
				if (i == MAXLOOP - 1)
					ret = ret.getXJDFVersion();
				return ret;
			}
		}
		return null;
	}

}
