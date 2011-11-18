/*
 * The CIP4 Software License, Version 1.0
 *
 *
 * Copyright (c) 2001-2011 The International Cooperation for the Integration of
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
 *    Alternately, this acknowledgment mrSubRefay appear in the software itself,
 *    if and wherever such third-party acknowledgments normally appear.
 *
 * 4. The names "CIP4" and "The International Cooperation for the Integration of
 *    Processes in  Prepress, Press and Postpress" must
 *    not be used to endorse or promote products derived from this
 *    software without prior written permission. For written
 *    permission, please contact info@cip4.org.
 *
 * 5. Products derived from this software may not be called "CIP4",
 *    nor may "CIP4" appear in their name, without prior writtenrestartProcesses()
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
 * SPECIAL, EXEMPLARY, OR CONSEQUENTIrSubRefAL DAMAGES (INCLUDING, BUT NOT
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
 * originally based on software restartProcesses()
 * copyright (c) 1999-2001, Heidelberger Druckmaschinen AG
 * copyright (c) 1999-2001, Agfa-Gevaert N.V.
 *
 * For more information on The International Cooperation for the
 * Integration of Processes in  Prepress, Press and Postpress , please see
 * <http://www.cip4.org/>.
 *
 */
package org.cip4.jdflib.extensions;

import java.util.Vector;

import org.cip4.jdflib.core.AttributeName;
import org.cip4.jdflib.core.JDFResourceLink.EnumUsage;
import org.cip4.jdflib.core.KElement;
import org.cip4.jdflib.core.VElement;
import org.cip4.jdflib.datatypes.JDFAttributeMap;
import org.cip4.jdflib.datatypes.VJDFAttributeMap;
import org.cip4.jdflib.resource.JDFPart;
import org.cip4.jdflib.util.StringUtil;

/**
  * @author Rainer Prosi, Heidelberger Druckmaschinen *
 */
public class SetHelper
{
	/**
	 * 
	 */
	public static final String PARAMETER = "Parameter";
	/**
	 * 
	 */
	public static final String RESOURCE = "Resource";

	private int myIndex(final KElement e)
	{
		final KElement parent = e.getParentNode_KElement();
		int n = 0;
		final String nodeName = e.getNodeName();
		final String namespaceURI = e.getNamespaceURI();
		KElement sib = parent.getFirstChildElement(nodeName, namespaceURI);
		while (sib != e)
		{
			sib = sib.getNextSiblingElement(nodeName, namespaceURI);
			if (sib == null)
			{
				return -1;
			}
			n++;
		}
		return n;
	}

	/**
	 * @param set the set to help on
	 */
	public SetHelper(KElement set)
	{
		super();
		this.theSet = set;
	}

	protected KElement theSet;

	/**
	 * @param map
	 * @return 
	 */
	public PartitionHelper getPartition(JDFAttributeMap map)
	{
		Vector<PartitionHelper> v = getPartitions();
		for (PartitionHelper ph : v)
		{
			if (ph.matches(map))
				return ph;
		}
		return null;
	}

	/**
	 * @param map
	 * @param addRes 
	 * @return 
	 */
	public PartitionHelper getCreatePartition(JDFAttributeMap map, boolean addRes)
	{
		PartitionHelper e = getPartition(map);
		if (e == null)
		{
			e = appendPartition(map, addRes);
		}
		else if (!e.containsMap(map))
		{
			e = insertPartitionBefore(e, map, addRes);
		}
		return e;
	}

	/**
	 * @param e
	 * @param map
	 * @param addRes
	 * @return
	 */
	public PartitionHelper insertPartitionBefore(PartitionHelper e, JDFAttributeMap map, boolean addRes)
	{
		PartitionHelper e2 = appendPartition(map, addRes);
		if (e != null)
		{
			KElement e3 = e.getPartition();
			e3.getParentNode_KElement().moveElement(e2.getPartition(), e3);
		}
		return e2;
	}

	/**
	 * @param vmap
	 * @param addRes 
	 * @return 
	 */
	public Vector<PartitionHelper> getCreatePartitions(VJDFAttributeMap vmap, boolean addRes)
	{
		if (vmap == null)
		{
			vmap = new VJDFAttributeMap();
			vmap.add(null);
		}
		Vector<PartitionHelper> vp = new Vector<PartitionHelper>();
		for (int i = 0; i < vmap.size(); i++)
		{
			JDFAttributeMap m = vmap.get(i);
			vp.add(getCreatePartition(m, addRes));
		}

		return vp;
	}

	/**
	 * remove all partitions in this set
	 */
	public void removePartitions()
	{
		Vector<PartitionHelper> v = getPartitions();
		for (PartitionHelper ph : v)
		{
			ph.getPartition().deleteNode();
		}
	}

	/**
	 * @param partMap
	 * @param addRes 
	 * @return
	 */
	public PartitionHelper appendPartition(JDFAttributeMap partMap, boolean addRes)
	{
		KElement newPart = theSet.appendElement(getPartitionName());
		PartitionHelper partitionHelper = new PartitionHelper(newPart);
		partitionHelper.cleanUp();
		if (partMap != null && partMap.size() > 0)
		{
			JDFPart part = (JDFPart) newPart.appendElement("Part");
			updatePartitions(partMap);
			part.setPartMap(partMap);
		}

		String resName = getName();
		if (resName != null && addRes)
		{
			KElement newRes = newPart.appendElement(resName);
			newRes.removeAttribute(AttributeName.CLASS);
		}
		return partitionHelper;
	}

	/**
	 * modify all partitions here
	 * @param partMap
	 */
	private void updatePartitions(JDFAttributeMap partMap)
	{
		String sep = partMap.get(AttributeName.SEPARATION);
		if (sep != null)
			partMap.put(AttributeName.SEPARATION, StringUtil.replaceChar(sep, ' ', "_", 0));
	}

	/**
	 * @return the vector of partition helpers 
	 */
	public Vector<PartitionHelper> getPartitions()
	{
		VElement v = theSet.getChildElementVector(getPartitionName(), null);

		Vector<PartitionHelper> v2 = new Vector<PartitionHelper>();
		if (v != null)
		{
			for (KElement e : v)
				v2.add(new PartitionHelper(e));
		}
		return v2;
	}

	/**
	 * 
	 */
	public void cleanUp()
	{
		if (!theSet.hasAttribute("Name"))
		{
			theSet.setAttribute("Name", getName());
		}
		if (!theSet.hasAttribute("ID"))
		{
			theSet.appendAnchor(null);
		}
		Vector<PartitionHelper> kids = getPartitions();
		if (kids != null)
		{
			for (PartitionHelper kid : kids)
			{
				kid.cleanUp();
			}
		}
	}

	/**
	 * @return the name of the resource set 
	 * also fixes it in case it needs to be calculated from kids
	 */
	public String getName()
	{
		String name = theSet.getAttribute("Name", null, null);
		if (name == null)
		{
			Vector<PartitionHelper> v = getPartitions();
			for (PartitionHelper ph : v)
			{
				KElement res = ph.getResource();
				if (res != null)
				{
					name = res.getNodeName();
					theSet.setAttribute("Name", name);
					return name;
				}
			}
		}
		return name;
	}

	/**
	 * @return 
	 * 
	 */
	public String getPartitionName()
	{
		String name = StringUtil.leftStr(theSet.getLocalName(), -3);
		return name;
	}

	/**
	 * @param vmap
	 * @return
	 */
	public PartitionHelper getPartition(VJDFAttributeMap vmap)
	{
		Vector<PartitionHelper> v = getPartitions();
		for (PartitionHelper ph : v)
		{
			if (ph.matches(vmap))
				return ph;
		}
		return null;
	}

	/**
	 * @return
	 */
	public KElement getSet()
	{
		return theSet;
	}

	/**
	 * @param value
	 */
	public void setUsage(EnumUsage value)
	{
		theSet.setAttribute(AttributeName.USAGE, value == null ? null : value.getName());
	}

	/**
	 * @see java.lang.Object#toString()
	 * @return
	*/
	@Override
	public String toString()
	{
		return "SetHelper: " + theSet;
	}

}
