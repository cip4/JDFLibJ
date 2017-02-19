/*
 * The CIP4 Software License, Version 1.0
 *
 *
 * Copyright (c) 2001-2017 The International Cooperation for the Integration of
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
import org.cip4.jdflib.core.ElementName;
import org.cip4.jdflib.core.JDFResourceLink.EnumUsage;
import org.cip4.jdflib.core.KElement;
import org.cip4.jdflib.core.VElement;
import org.cip4.jdflib.core.VString;
import org.cip4.jdflib.datatypes.JDFAttributeMap;
import org.cip4.jdflib.datatypes.VJDFAttributeMap;
import org.cip4.jdflib.resource.JDFPart;
import org.cip4.jdflib.util.StringUtil;

/**
  * @author Rainer Prosi, Heidelberger Druckmaschinen *
 */
public class SetHelper extends BaseXJDFHelper
{
	/**
	 *
	 */
	public static final String SET = "Set";

	/**
	 *
	 * @author rainerprosi
	 *
	 */
	public enum EnumFamily
	{
		Parameter, Resource
	}

	/**
	 *
	 *
	 */
	public static final String PARAMETER_SET = EnumFamily.Parameter.name() + "Set";
	/**
	 *
	 */
	public static final String RESOURCE_SET = XJDFConstants.ResourceSet;

	/**
	 * @param set the set to help on
	 */
	public SetHelper(KElement set)
	{
		super();
		this.theElement = set;
	}

	/**
	 *
	 * is the element a set?
	 * @param e
	 * @return
	 */
	public static boolean isSet(KElement e)
	{
		String localName = e == null ? null : e.getLocalName();
		return isSet(localName);
	}

	/**
	 * is the element name a set?
	 * @param localName
	 * @return
	 */
	public static boolean isSet(String localName)
	{
		return RESOURCE_SET.equals(localName) || PARAMETER_SET.equals(localName);
	}

	/**
	 * @param map
	 * @return
	 */
	public ResourceHelper getPartition(JDFAttributeMap map)
	{
		Vector<ResourceHelper> v = getPartitions();
		for (ResourceHelper ph : v)
		{
			if (ph.matches(map))
			{
				return ph;
			}
		}
		return null;
	}

	/**
	 * @param map
	 * @return
	 */
	public ResourceHelper getPartition(String key, String value)
	{
		return getPartition(new JDFAttributeMap(key, value));
	}

	/**
	 * @param index
	 * @return
	 */
	public ResourceHelper getPartition(int index)
	{
		Vector<ResourceHelper> v = getPartitions();
		if (index < 0)
			index += v.size();
		if (index >= v.size())
			return null;
		return (index < 0) ? null : v.get(index);
	}

	/**
	 * @param map
	 * @param addRes
	 * @return
	 */
	public ResourceHelper getCreatePartition(JDFAttributeMap map, boolean addRes)
	{
		ResourceHelper e = getPartition(map);
		if (e == null)
		{
			e = appendPartition(map, addRes);
		}
		else if (!e.containsMap(map))
		{
			e = insertPartitionBefore(e, map, addRes);
		}
		if (addRes)
			e.getCreateResource();
		return e;
	}

	/**
	 * get an existing partition with at least a matching partmap
	 * @param map
	 * @param addRes
	 * @return
	 */
	public ResourceHelper getCreateVPartition(VJDFAttributeMap vmap, boolean addRes)
	{
		JDFAttributeMap map = vmap != null && !vmap.isEmpty() ? vmap.get(0) : null;
		ResourceHelper e = getPartition(map);
		if (e == null)
		{
			e = appendPartition(map, addRes);
		}
		else if (!e.containsMap(map))
		{
			e = insertPartitionBefore(e, map, addRes);
		}
		e.setPartMapVector(vmap);
		if (addRes)
			e.getCreateResource();
		return e;
	}

	/**
	 * @param e
	 * @param map
	 * @param addRes
	 * @return
	 */
	public ResourceHelper insertPartitionBefore(ResourceHelper e, JDFAttributeMap map, boolean addRes)
	{
		ResourceHelper e2 = appendPartition(map, addRes);
		if (e != null)
		{
			KElement e3 = e.getPartition();
			e3.getParentNode_KElement().moveElement(e2.getPartition(), e3);
		}
		return e2;
	}

	/**
	 * creates 1 partition for each entry in the map
	 *
	 * @param vmap
	 * @param addRes
	 * @return
	 */
	public Vector<ResourceHelper> getCreatePartitions(VJDFAttributeMap vmap, boolean addRes)
	{
		if (vmap == null)
		{
			vmap = new VJDFAttributeMap();
			vmap.add(null);
		}
		Vector<ResourceHelper> vp = new Vector<ResourceHelper>();
		for (JDFAttributeMap m : vmap)
		{
			vp.add(getCreatePartition(m, addRes));
		}
		return vp;
	}

	/**
	 * @param index
	 * @param addRes
	 * @return
	 */
	public ResourceHelper getCreatePartition(int index, boolean addRes)
	{
		Vector<ResourceHelper> v = getPartitions();
		int size = v.size();
		if (index < 0)
			index += size;
		while (index < 0)
		{
			appendPartition(null, addRes);
			index++;
		}
		while (index >= size)
		{
			appendPartition(null, addRes);
			size++;
		}
		if (size != v.size())
			v = getPartitions();
		ResourceHelper ph = v.get(index);
		if (addRes)
			ph.getCreateResource();
		return ph;
	}

	/**
	 * remove all partitions in this set
	 */
	public void removePartitions()
	{
		Vector<ResourceHelper> v = getPartitions();
		for (ResourceHelper ph : v)
		{
			ph.getPartition().deleteNode();
		}
	}

	/**
	 * convenience ... @see {@link SetHelper#appendPartition(JDFAttributeMap, boolean)}
	 *
	 * @param partKey
	 * @param partValue
	 * @param addRes
	 * @return
	 */
	public ResourceHelper appendPartition(String partKey, String partValue, boolean addRes)
	{
		return appendPartition(new JDFAttributeMap(partKey, partValue), addRes);
	}

	/**
	 * convenience ... @see {@link SetHelper#getCreatePartition(JDFAttributeMap, boolean)}
	 *
	 * @param partKey
	 * @param partValue
	 * @param addRes
	 * @return
	 */
	public ResourceHelper getCreatePartition(String partKey, String partValue, boolean addRes)
	{
		return getCreatePartition(new JDFAttributeMap(partKey, partValue), addRes);
	}

	/**
	 * @param partMap
	 * @param addRes if true, also add the detailed resource element, e.g. Layout
	 * @return
	 */
	public ResourceHelper appendPartition(JDFAttributeMap partMap, boolean addRes)
	{
		KElement newPart = theElement.appendElement(getPartitionName());
		ResourceHelper partitionHelper = new ResourceHelper(newPart);
		partitionHelper.cleanUp();
		if (partMap != null && partMap.size() > 0)
		{
			JDFPart part = (JDFPart) newPart.appendElement(ElementName.PART);
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
	 *
	 * @return
	 */
	public XJDFHelper getXJDF()
	{
		return XJDFHelper.getHelper(theElement);
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
	public Vector<ResourceHelper> getPartitions()
	{
		VElement v = theElement.getChildElementVector(getPartitionName(), null);

		Vector<ResourceHelper> v2 = new Vector<ResourceHelper>();
		if (v != null)
		{
			for (KElement e : v)
			{
				v2.add(new ResourceHelper(e));
			}
		}
		return v2;
	}

	/**
	 *
	 */
	@Override
	public void cleanUp()
	{
		if (!theElement.hasAttribute(AttributeName.NAME))
		{
			theElement.setAttribute(AttributeName.NAME, getName());
		}
		Vector<ResourceHelper> kids = getPartitions();
		if (kids != null)
		{
			for (ResourceHelper kid : kids)
			{
				kid.cleanUp();
				theElement.moveElement(kid.getRoot(), null);
			}
		}
	}

	/**
	 * @return the name of the resource set
	 * also fixes it in case it needs to be calculated from kids
	 */
	public String getName()
	{
		String name = theElement.getAttribute(AttributeName.NAME, null, null);
		if (name == null)
		{
			Vector<ResourceHelper> v = getPartitions();
			for (ResourceHelper ph : v)
			{
				KElement res = ph.getResource();
				if (res != null)
				{
					name = res.getNodeName();
					theElement.setAttribute(AttributeName.NAME, name);
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
		String name = getSetName(theElement.getLocalName());
		return name;
	}

	/**
	 *
	 * @param e the element to test
	 * @return  then asset type name (Parameter, Resource etc)
	 */
	public static String getSetName(KElement e)
	{
		return e == null ? null : getSetName(e.getLocalName());
	}

	/**
	 *
	 * @param setName the name of the set element
	 * @return then asset type name (Parameter, Resource etc)
	 */
	public static String getSetName(String setName)
	{
		return (isSet(setName) || "IntentSet".equals(setName)) ? StringUtil.leftStr(setName, -3) : null;
	}

	/**
	 * @param vmap
	 * @return
	 */
	public ResourceHelper getPartition(VJDFAttributeMap vmap)
	{
		Vector<ResourceHelper> v = getPartitions();
		for (ResourceHelper ph : v)
		{
			if (ph.matches(vmap))
			{
				return ph;
			}
		}
		return null;
	}

	/**
	 * @param vmap
	 * @return
	 */
	public Vector<ResourceHelper> getPartitions(VJDFAttributeMap vmap)
	{
		Vector<ResourceHelper> v = getPartitions();
		Vector<ResourceHelper> vRet = new Vector<ResourceHelper>();
		for (ResourceHelper ph : v)
		{
			if (ph.matches(vmap))
			{
				vRet.add(ph);
			}
		}
		return vRet;
	}

	/**
	 * @param map
	 * @return
	 */
	public Vector<ResourceHelper> getPartitions(JDFAttributeMap map)
	{
		Vector<ResourceHelper> v = getPartitions();
		Vector<ResourceHelper> vRet = new Vector<ResourceHelper>();
		for (ResourceHelper ph : v)
		{
			if (ph.matches(map))
			{
				vRet.add(ph);
			}
		}
		return vRet;
	}

	/**
	 * @return
	 */
	public KElement getSet()
	{
		return theElement;
	}

	/**
	 * @param value
	 */
	public void setUsage(EnumUsage value)
	{
		theElement.setAttribute(AttributeName.USAGE, value == null ? null : value.getName());
	}

	/**
	 *
	 *
	 * @return
	 */
	public EnumUsage getUsage()
	{
		return EnumUsage.getEnum(theElement.getAttribute(AttributeName.USAGE, null, null));
	}

	/**
	 *
	 *
	 * @return
	 */
	public String getProcessUsage()
	{
		return theElement.getAttribute(AttributeName.PROCESSUSAGE, null, null);
	}

	/**
	 *
	 *
	 * @return
	 */
	public void setProcessUsage(String processUsage)
	{
		theElement.setAttribute(AttributeName.PROCESSUSAGE, processUsage, null);
	}

	/**
	 * @see java.lang.Object#toString()
	 * @return
	*/
	@Override
	public String toString()
	{
		return "SetHelper: " + theElement;
	}

	/**
	 * get a partition by id
	 * @param id
	 * @return
	 */
	public ResourceHelper getPartition(String id)
	{
		if (id == null)
			return null;
		String partitionName = getPartitionName();
		KElement e = theElement.getFirstChildElement(partitionName, null);
		while (e != null)
		{
			if (id.equals(e.getID()))
				return new ResourceHelper(e);
			e = e.getNextSiblingElement(partitionName, null);
		}
		return null;
	}

	/**
	 *
	 * @return
	 */
	public EnumFamily getFamily()
	{
		if (theElement == null)
			return null;
		String name = theElement.getLocalName();
		name = StringUtil.leftStr(name, -3);
		return EnumFamily.valueOf(name);
	}

	/**
	 *
	 * @see org.cip4.jdflib.extensions.BaseXJDFHelper#setID(java.lang.String)
	 */
	@Override
	public void setID(String newID)
	{
		String oldID = getID();
		super.setID(newID);
		Vector<ResourceHelper> parts = getPartitions();
		if (parts != null)
		{
			for (ResourceHelper part : parts)
			{
				String partID = part.getID();
				partID = StringUtil.replaceString(partID, oldID, newID);
				part.setID(newID);
			}
		}
	}

	/**
	 *
	 * @return
	 */
	public VJDFAttributeMap getPartMapVector()
	{
		Vector<ResourceHelper> vph = getPartitions();
		VJDFAttributeMap vMap = new VJDFAttributeMap();
		for (ResourceHelper ph : vph)
		{
			vMap.addAll(ph.getPartMapVector());
		}
		vMap.unify();
		return vMap;
	}

	/**
	 *
	 * @return
	 */
	public VString getDependentJobParts()
	{
		VElement deps = theElement.getChildElementVector(XJDFConstants.Dependent, null);
		VString ret = new VString();
		if (deps != null)
		{
			for (KElement dep : deps)
			{
				String jpID = dep.getNonEmpty(AttributeName.JOBPARTID);
				ret.appendUnique(jpID);
			}
		}
		return ret.isEmpty() ? null : ret;
	}

	/**
	 *
	 * @param name
	 */
	public void setName(String name)
	{
		setAttribute(AttributeName.NAME, name);
	}

	public void removeIDs()
	{
		removeAttribute(AttributeName.ID, null);
		Vector<ResourceHelper> v = getPartitions();
		if (v != null)
		{
			for (ResourceHelper ph : v)
			{
				ph.removeAttribute(AttributeName.ID, null);
			}
		}

	}

	/**
	 *
	 * @param bsHelper
	 * @return
	 */
	public int indexOf(ResourceHelper bsHelper)
	{
		if (bsHelper == null)
			return -1;
		VElement v = theElement.getChildElementVector(getPartitionName(), null);
		if (v == null)
			return -1;
		return v.indexOf(bsHelper.getRoot());

	}
}
