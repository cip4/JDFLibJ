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
package org.cip4.jdflib.node;

import org.cip4.jdflib.core.JDFResourceLink.EnumUsage;
import org.cip4.jdflib.core.VString;
import org.cip4.jdflib.node.JDFNode.EnumProcessUsage;
import org.cip4.jdflib.util.ContainerUtil;
import org.cip4.jdflib.util.StringUtil;

/**
 * @author rainer prosi
 *
 */
public class LinkInfo
{
	/**
	 *
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode()
	{
		final int prime = 31;
		int result = 1;
		result = prime * result + ((theInfo == null) ? 0 : theInfo.hashCode());
		return result;
	}

	/**
	 *
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	@Override
	public boolean equals(final Object obj)
	{
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		final LinkInfo other = (LinkInfo) obj;
		return ContainerUtil.equals(other.theInfo, theInfo);
	}

	/**
	 *
	 * @param info
	 */
	LinkInfo(final LinkInfo info)
	{
		super();
		theInfo = new VString(info.getVString());
	}

	/**
	 *
	 * @return
	 */
	int size()
	{
		return theInfo == null ? 0 : theInfo.size();
	}

	/**
	 *
	 * @param info
	 */
	LinkInfo(final String info)
	{
		super();
		theInfo = new VString(info);
	}

	/**
	 *
	 * @param info
	 */
	LinkInfo(final VString info)
	{
		super();
		theInfo = new VString(info);
	}

	private final VString theInfo;

	/**
	 *
	 * @return
	 */
	VString getVString()
	{
		return theInfo;
	}

	/**
	 *
	 * @return
	 */
	String getString()
	{
		return StringUtil.setvString(theInfo);
	}

	/**
	 *
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString()
	{
		return "LinkInfo " + theInfo;
	}

	/**
	 * merge two LinkInfos into one
	 *
	 * @param value
	 */
	void merge(final LinkInfo value)
	{
		if (value != null)
		{
			for (int i = 0; i < value.size(); i++)
			{
				boolean needAdd = value.getProcessUsage(i) != null;
				if (!needAdd)
				{
					final EnumUsage u = value.isInput(i) ? EnumUsage.Input : EnumUsage.Output;
					needAdd = maxAllowed(u) < Integer.MAX_VALUE;
					if (!needAdd)
					{
						needAdd = value.isRequired(i) && !isRequired(u);
					}
				}
				if (needAdd)
				{
					add(value.theInfo.get(i));
				}
			}
		}
	}

	private void add(final String s)
	{
		if ((s.equals("i*") || s.equals("i?")) && theInfo.contains("i*"))
		{

		}
		else if ((s.equals("o*") || s.equals("o?")) && theInfo.contains("o*"))
		{
		}
		else if ((s.equals("i+") || s.equals("i_")) && theInfo.contains("i*"))
		{
			theInfo.remove("i*");
			theInfo.add("i+");
		}
		else if ((s.equals("o+") || s.equals("o_")) && theInfo.contains("o*"))
		{
			theInfo.remove("o*");
			theInfo.add("o+");
		}
		else
		{
			theInfo.add(s);
		}
	}

	/**
	 *
	 * @param processUsage
	 * @return
	 */
	public boolean hasInput(final String processUsage)
	{
		for (final String s : theInfo)
		{
			if (s.startsWith("i") && (processUsage == null || processUsage.equals(StringUtil.rightStr(s, -2))))
			{
				return true;
			}
		}
		return false;
	}

	/**
	 *
	 * @return
	 */
	boolean matchesUsage(final int iPos, final EnumUsage usage)
	{
		if (EnumUsage.Input.equals(usage))
			return isInput(iPos);
		if (EnumUsage.Output.equals(usage))
			return isOutput(iPos);
		return theInfo.get(iPos) != null;
	}

	/**
	 *
	 * @return
	 */
	boolean isInput(final int iPos)
	{
		final String s = theInfo.get(iPos);
		return s != null && s.startsWith("i");
	}

	/**
	 *
	 * @return
	 */
	boolean isSingle(final int iPos)
	{
		String s = get2(iPos);
		if (s != null)
		{
			s = s.substring(1);
			return "?".equals(s) || "_".equals(s);
		}
		return false;
	}

	/**
	 *
	 * @return
	 */
	boolean isOutput(final int iPos)
	{
		final String s = theInfo.get(iPos);
		return s != null && s.startsWith("o");
	}

	/**
	 * get the process usage resource that matches the typesafe link described by i
	 *
	 *
	 * @param iPos the index of the pu to find
	 * @return the enumerated process usage of this checked link
	 *
	 *
	 */
	String getProcessUsage(final int iPos)
	{
		final String pu = getPU(iPos);

		if (pu.length() > 0)
		{
			return pu;
		}
		else
		{
			return null;
		}
	}

	/**
	 * get the process usage resource that matches the typesafe link described by i
	 *
	 *
	 * @param iPos the index of the pu to find
	 * @return the enumerated process usage of this checked link
	 *
	 *
	 */
	EnumProcessUsage getEnumProcessUsage(final int iPos)
	{
		final String pu = getPU(iPos);

		if (pu.length() > 0)
		{
			return EnumProcessUsage.getEnum(pu);
		}
		else if (isInput(iPos))
		{
			return EnumProcessUsage.AnyInput;
		}
		else if (isOutput(iPos))
		{
			return EnumProcessUsage.AnyOutput;
		}
		else
		{
			return null;
		}
	}

	/**
	 *
	 * @param processUsage
	 * @return
	 */
	public boolean hasOutput(final String processUsage)
	{
		for (final String s : theInfo)
		{
			if (s.startsWith("o") && (processUsage == null || processUsage.equals(StringUtil.rightStr(s, -2))))
			{
				return true;
			}
		}
		return false;
	}

	/**
	 *
	 * @param processUsage
	 * @return
	 */
	public EnumUsage getUsage(final String processUsage)
	{
		final boolean bInput = hasInput(processUsage);
		final boolean bOutput = hasOutput(processUsage);
		if (bInput ^ bOutput)
			return bInput ? EnumUsage.Input : EnumUsage.Output;
		return null;
	}

	/**
	 *
	 * @param usage
	 * @param procU
	 * @param nLink
	 * @return
	 */
	public boolean isValidLink(final EnumUsage usage, String procU, final int nLink)
	{

		final int maxAllowed = maxAllowed(usage);
		if (nLink > maxAllowed)
		{
			return false;
		}
		if (!hasProcessUsage(procU))
			procU = null;
		return usage == null || usage.isInput() ? hasInput(procU) : hasOutput(procU);
	}

	/**
	 *
	 * @param bIn
	 * @param bOut
	 */
	void makeOptional(final boolean bIn, final boolean bOut)
	{
		for (int i = 0; i < theInfo.size(); i++)
		{
			final String s = get2(i);

			if (bOut && s.startsWith("o"))
			{
				if ("o_".equals(s))
				{
					theInfo.set(i, "o?" + getPU(i));
				}
				else if ("o+".equals(s))
				{
					theInfo.set(i, "o*" + getPU(i));
				}
			}
			else if (bIn && s.startsWith("i"))
			{
				if ("i_".equals(s))
				{
					theInfo.set(i, "i?" + getPU(i));
				}
				else if ("i+".equals(s))
				{
					theInfo.set(i, "i*" + getPU(i));
				}
			}
		}
	}

	private String getPU(final int i)
	{
		final String strWork = theInfo.get(i);

		return strWork.length() == 2 ? "" : strWork.substring(2);
	}

	/**
	 *
	 * @param usage
	 * @return
	 */
	public boolean isRequired(final EnumUsage usage)
	{
		for (int i = 0; i < theInfo.size(); i++)
		{
			if (matchesUsage(i, usage) && isRequired(i))
			{
				return true;
			}
		}
		return false;
	}

	/**
	 *
	 * @param usage
	 * @return
	 */
	public int maxAllowed(final EnumUsage usage)
	{
		int n = 0;
		for (int i = 0; i < theInfo.size(); i++)
		{
			if (matchesUsage(i, usage))
			{
				final int nn = maxAllowed(i);
				if (nn == Integer.MAX_VALUE)
					return nn;
				n += nn;
			}
		}
		return n;
	}

	/**
	 *
	 * @param i
	 * @return
	 */
	int maxAllowed(final int i)
	{
		final String s = StringUtil.rightStr(get2(i), 1);
		if (s == null)
			return 0;
		if ("+".equals(s) || "*".equals(s))
			return Integer.MAX_VALUE;
		if ("_".equals(s) || "?".equals(s))
			return 1;
		return 0;
	}

	/**
	 *
	 * @param bInput
	 * @param bOutput
	 * @param i
	 * @return
	 */
	boolean isRequired(final int i)
	{
		final String s = get2(i);
		if ("i_".equals(s) || "i+".equals(s))
			return true;
		if ("o_".equals(s) || "o+".equals(s))
			return true;
		return false;
	}

	/**
	 *
	 * @param i
	 * @return
	 */
	private String get2(final int i)
	{
		return StringUtil.leftStr(theInfo.get(i), 2);
	}

	/**
	 *
	 * @param procU
	 * @return
	 */
	public boolean hasProcessUsage(final String procU)
	{
		if (procU == null)
			return theInfo.size() > 0;

		for (final String s : theInfo)
		{
			if (procU.equals(StringUtil.rightStr(s, -2)))
			{
				return true;
			}
		}
		return false;
	}

}
