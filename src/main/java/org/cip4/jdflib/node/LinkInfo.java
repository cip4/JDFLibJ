/**
 * The CIP4 Software License, Version 1.0
 *
 * Copyright (c) 2001-2014 The International Cooperation for the Integration of 
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
package org.cip4.jdflib.node;

import org.cip4.jdflib.core.JDFResourceLink.EnumUsage;
import org.cip4.jdflib.core.VString;
import org.cip4.jdflib.node.JDFNode.EnumProcessUsage;
import org.cip4.jdflib.util.StringUtil;

/**
 * @author rainer prosi
 *
 */
class LinkInfo
{
	/**
	 * 
	 * @param info
	 */
	LinkInfo(LinkInfo info)
	{
		super();
		theInfo = new VString(info.getVString());
	}

	/**
	 * 
	 * @return
	 */
	public int size()
	{
		return theInfo == null ? 0 : theInfo.size();
	}

	/**
	 * 
	 * @param info
	 */
	LinkInfo(String info)
	{
		super();
		theInfo = new VString(info, " ");
	}

	/**
	 * 
	 * @param info
	 */
	LinkInfo(VString info)
	{
		super();
		theInfo = new VString(info);
	}

	private final VString theInfo;

	/**
	 * 
	 * @return
	 */
	public VString getVString()
	{
		return theInfo;
	}

	/**
	 * 
	 * @return
	 */
	public String getString()
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
	 * @param value
	 */
	public void merge(LinkInfo value)
	{
		if (value != null)
		{
			theInfo.addAll(value.getVString());
		}
	}

	/**
	 * 
	 * @param processUsage
	 * @return
	 */
	public boolean hasInput(String processUsage)
	{
		for (String s : theInfo)
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
	public boolean matchesUsage(int iPos, EnumUsage usage)
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
	public boolean isInput(int iPos)
	{
		String s = theInfo.get(iPos);
		return s != null && s.startsWith("i");
	}

	/**
	 * 
	 * @return
	 */
	public boolean isSingle(int iPos)
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
	public boolean isOutput(int iPos)
	{
		String s = theInfo.get(iPos);
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
	public String getProcessUsage(final int iPos)
	{
		String pu = getPU(iPos);

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
	public EnumProcessUsage getEnumProcessUsage(final int iPos)
	{
		String pu = getPU(iPos);

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
	public boolean hasOutput(String processUsage)
	{
		for (String s : theInfo)
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
	public EnumUsage getUsage(String processUsage)
	{
		boolean bInput = hasInput(processUsage);
		boolean bOutput = hasOutput(processUsage);
		if (bInput ^ bOutput)
			return bInput ? EnumUsage.Input : EnumUsage.Output;
		return null;
	}

	/**
	 * 
	 * @param bIn
	 * @param bOut
	 */
	public void makeOptional(boolean bIn, boolean bOut)
	{
		for (int i = 0; i < theInfo.size(); i++)
		{
			String s = get2(i);

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

	private String getPU(int i)
	{
		String strWork = theInfo.get(i);

		return strWork.length() == 2 ? "" : strWork.substring(2);
	}

	/**
	 * 
	 * @param usage
	 * @return
	 */
	public boolean isRequired(EnumUsage usage)
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
	public int maxAllowed(EnumUsage usage)
	{
		int n = 0;
		for (int i = 0; i < theInfo.size(); i++)
		{
			if (matchesUsage(i, usage))
			{
				int nn = maxAllowed(i);
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
	public int maxAllowed(int i)
	{
		String s = StringUtil.rightStr(get2(i), 1);
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
	public boolean isRequired(int i)
	{
		String s = get2(i);
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
	private String get2(int i)
	{
		return StringUtil.leftStr(theInfo.get(i), 2);
	}

	/**
	 * 
	 * @param procU
	 * @return
	 */
	public boolean hasProcessUsage(String procU)
	{
		if (procU == null)
			return theInfo.size() > 0;

		for (String s : theInfo)
		{
			if (procU.equals(StringUtil.rightStr(s, -2)))
			{
				return true;
			}
		}
		return false;
	}

}
