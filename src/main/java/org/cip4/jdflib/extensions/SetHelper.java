/*
 * The CIP4 Software License, Version 1.0
 *
 *
 * Copyright (c) 2001-2025 The International Cooperation for the Integration of Processes in Prepress, Press and Postpress (CIP4). All rights reserved.
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

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Vector;

import org.cip4.jdflib.core.AttributeName;
import org.cip4.jdflib.core.JDFConstants;
import org.cip4.jdflib.core.JDFElement.eUnit;
import org.cip4.jdflib.core.JDFResourceLink.EnumUsage;
import org.cip4.jdflib.core.KElement;
import org.cip4.jdflib.core.VElement;
import org.cip4.jdflib.core.VString;
import org.cip4.jdflib.datatypes.JDFAttributeMap;
import org.cip4.jdflib.datatypes.JDFAttributeMapArray;
import org.cip4.jdflib.datatypes.JDFIntegerList;
import org.cip4.jdflib.datatypes.VJDFAttributeMap;
import org.cip4.jdflib.ifaces.IMatches;
import org.cip4.jdflib.resource.process.JDFGeneralID;
import org.cip4.jdflib.util.ContainerUtil;
import org.cip4.jdflib.util.StringUtil;

/**
 * @author Rainer Prosi, Heidelberger Druckmaschinen *
 */
public class SetHelper extends BaseXJDFHelper implements IMatches
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
	 *
	 * @param bGood good=true
	 * @return
	 */
	public double getAmountSum(final boolean bGood)
	{
		double a = 0;
		final List<ResourceHelper> l = getPartitionList();
		for (final ResourceHelper h : l)
			a += h.getAmountSum(bGood);
		return a;
	}

	/**
	 * @param set the set to help on
	 */
	public SetHelper(final KElement set)
	{
		super();
		this.theElement = set;
	}

	/**
	 * ensure a reference *FROM* src
	 *
	 * @param src
	 * @param key the reference key in src
	 *
	 */
	public void ensureReference(final KElement src, String key)
	{
		if (src != null && theElement != null)
		{
			if (StringUtil.isEmpty(key))
				key = getName();
			if (!key.endsWith(JDFConstants.REF) && !key.endsWith(JDFConstants.REFS))
				key += JDFConstants.REF;
			final String id = ensureID();
			src.setAttribute(key, id);
		}
	}

	/**
	 *
	 * is the element a set?
	 *
	 * @param e
	 * @return
	 */
	public static boolean isSet(final KElement e)
	{
		final String localName = e == null ? null : e.getLocalName();
		return isSet(localName);
	}

	/**
	 * is the element name a set?
	 *
	 * @param localName
	 * @return
	 */
	public static boolean isSet(final String localName)
	{
		return RESOURCE_SET.equals(localName) || PARAMETER_SET.equals(localName);
	}

	/**
	 * @param map
	 * @return
	 */
	public ResourceHelper getPartition(final JDFAttributeMap map)
	{
		return getResource(map);
	}

	/**
	 * @param map
	 * @return
	 */
	public ResourceHelper getResource(final JDFAttributeMap map)
	{
		final List<ResourceHelper> v = getPartitionList();
		for (final ResourceHelper ph : v)
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
	public ResourceHelper getPartition(final String key, final String value)
	{
		return getResource(new JDFAttributeMap(key, value));
	}

	/**
	 * @param map
	 * @return
	 */
	public ResourceHelper getResource(final String key, final String value)
	{
		return getResource(new JDFAttributeMap(key, value));
	}

	/**
	 * @param index
	 * @return
	 */
	public ResourceHelper getPartition(final int index)
	{
		return ContainerUtil.get(getResourceList(), index);
	}

	/**
	 * @param index
	 * @return
	 */
	public ResourceHelper getResource(final int index)
	{
		return ContainerUtil.get(getResourceList(), index);
	}

	/**
	 * @param map
	 * @param addRes
	 * @return
	 */
	public ResourceHelper getCreatePartition(final JDFAttributeMap map, final boolean addRes)
	{
		return getCreateResource(map, addRes);
	}

	/**
	 * @param map
	 * @param addRes
	 * @return
	 */
	public ResourceHelper getCreateResource(final JDFAttributeMap map, final boolean addRes)
	{
		ResourceHelper e = getResource(map);
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
	 *
	 * @param vmap
	 * @param addRes
	 * @return
	 */
	public ResourceHelper getCreateVPartition(final VJDFAttributeMap vmap, final boolean addRes)
	{
		ResourceHelper e = null;
		JDFAttributeMap map = null;
		if (vmap != null)
		{
			for (final JDFAttributeMap map2 : vmap)
			{
				e = getPartition(map2);
				if (e != null)
				{
					map = map2;
					break;
				}
			}
		}
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
	 * get an existing partition with at least a matching partmap
	 *
	 * @param vmap
	 * @param addRes
	 * @return
	 */
	public ResourceHelper getCreateExactPartition(final JDFAttributeMap map, final boolean addRes)
	{
		final ResourceHelper h = getExactPartition(map);

		return h != null ? h : appendPartition(map, addRes);
	}

	/**
	 * 
	 * @param map
	 * @return
	 */
	public ResourceHelper getExactPartition(final JDFAttributeMap map)
	{
		for (final ResourceHelper h : getPartitionList())
		{
			if (h.hasPartition(map))
			{
				return h;
			}
		}
		return null;
	}

	/**
	 * @param e
	 * @param map
	 * @param addRes
	 * @return
	 */
	public ResourceHelper insertPartitionBefore(final ResourceHelper e, final JDFAttributeMap map, final boolean addRes)
	{
		final ResourceHelper e2 = appendPartition(map, addRes);
		if (e != null)
		{
			final KElement e3 = e.getPartition();
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
	public Vector<ResourceHelper> getCreatePartitions(VJDFAttributeMap vmap, final boolean addRes)
	{
		if (vmap == null)
		{
			vmap = new VJDFAttributeMap();
			vmap.add(null);
		}
		final Vector<ResourceHelper> vp = new Vector<>();
		for (final JDFAttributeMap m : vmap)
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
	public ResourceHelper getCreatePartition(final int index, final boolean addRes)
	{
		return getCreateResource(index, addRes);
	}

	public ResourceHelper getCreateResource()
	{
		return getCreateResource(0, true);
	}

	/**
	 * @param index
	 * @param addRes
	 * @return
	 */
	public ResourceHelper getCreateResource(int index, final boolean addRes)
	{
		List<ResourceHelper> v = getResourceList();
		int size = v.size();
		if (index < 0)
			index += size;
		while (index < 0)
		{
			appendResource((JDFAttributeMap) null, addRes);
			index++;
		}
		while (index >= size)
		{
			appendResource((JDFAttributeMap) null, addRes);
			size++;
		}
		if (size != v.size())
			v = getResourceList();
		final ResourceHelper ph = v.get(index);
		if (addRes)
			ph.getCreateResource();
		return ph;
	}

	/**
	 * remove all partitions in this set
	 */
	public void removePartitions()
	{
		final List<ResourceHelper> v = getPartitionList();
		for (final ResourceHelper ph : v)
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
	public ResourceHelper appendResource(final String partKey, final String partValue, final boolean addRes)
	{
		return appendResource(new JDFAttributeMap(partKey, partValue), addRes);
	}

	/**
	 * convenience ... @see {@link SetHelper#appendPartition(JDFAttributeMap, boolean)}
	 *
	 * @param partKey
	 * @param partValue
	 * @param addRes
	 * @return
	 */
	public ResourceHelper appendPartition(final String partKey, final String partValue, final boolean addRes)
	{
		return appendResource(partKey, partValue, addRes);
	}

	/**
	 * convenience ... @see {@link SetHelper#getCreatePartition(JDFAttributeMap, boolean)}
	 *
	 * @param partKey
	 * @param partValue
	 * @param addRes
	 * @return
	 */
	public ResourceHelper getCreatePartition(final String partKey, final String partValue, final boolean addRes)
	{
		return getCreatePartition(new JDFAttributeMap(partKey, partValue), addRes);
	}

	/**
	 * @param partMap
	 * @param addRes if true, also add the detailed resource element, e.g. Layout
	 * @return
	 */
	public ResourceHelper appendResource(final JDFAttributeMap partMap, final boolean addRes)
	{
		return appendResource(JDFAttributeMap.isEmpty(partMap) ? null : new VJDFAttributeMap(partMap), addRes);
	}

	/**
	 * @param partMap
	 * @param addRes if true, also add the detailed resource element, e.g. Layout
	 * @return
	 */
	public ResourceHelper appendPartition(final JDFAttributeMap partMap, final boolean addRes)
	{
		return appendResource(partMap, addRes);
	}

	/**
	 * @param partMap
	 * @param addRes if true, also add the detailed resource element, e.g. Layout
	 * @return
	 */
	public ResourceHelper appendResource(final VJDFAttributeMap partMaps, final boolean addRes)
	{
		final KElement newPart = theElement.appendElement(getPartitionName());
		final ResourceHelper partitionHelper = new ResourceHelper(newPart);
		partitionHelper.cleanUp();
		partitionHelper.setPartMapVector(partMaps);

		final String resName = getName();
		if (resName != null && addRes)
		{
			final KElement newRes = newPart.appendElement(resName);
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
	 * @return the vector of partition helpers
	 */
	public Vector<ResourceHelper> getPartitions()
	{
		final Collection<KElement> v = theElement.getChildList(getPartitionName(), null);

		final Vector<ResourceHelper> v2 = new Vector<>();
		if (v != null)
		{
			for (final KElement e : v)
			{
				v2.add(new ResourceHelper(e));
			}
		}
		return v2;
	}

	/**
	 * @return the list of partition helpers - never null
	 */
	public List<ResourceHelper> getPartitionList()
	{
		return getResourceList();
	}

	/**
	 * @return the list of partition helpers - never null
	 */
	public List<ResourceHelper> getResourceList()
	{
		final Collection<KElement> v = theElement.getChildList(getPartitionName(), null);

		final ArrayList<ResourceHelper> v2 = new ArrayList<>();
		for (final KElement e : v)
		{
			v2.add(new ResourceHelper(e));
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
		final List<ResourceHelper> kids = getPartitionList();
		if (!ContainerUtil.isEmpty(kids))
		{
			for (final ResourceHelper kid : kids)
			{
				kid.cleanUp();
			}
			theElement.sortChildren(new XJDFSetCleanupComparator());
		}
	}

	/**
	 * @return the name of the resource set also fixes it in case it needs to be calculated from kids
	 */
	public String getName()
	{
		String name = theElement.getAttribute(AttributeName.NAME, null, null);
		if (name == null)
		{
			final List<ResourceHelper> v = getPartitionList();
			for (final ResourceHelper ph : v)
			{
				final KElement res = ph.getResource();
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
		final String name = getSetName(theElement.getLocalName());
		return name;
	}

	/**
	 *
	 * @param e the element to test
	 * @return the asset type name (Parameter, Resource etc)
	 */
	public static String getSetName(final KElement e)
	{
		return e == null ? null : getSetName(e.getLocalName());
	}

	/**
	 *
	 * @param e the element to test
	 * @return then resourceset @Name (RunList, Layout etc)
	 */
	public static String getResourceName(final KElement e)
	{
		final SetHelper h = getHelper(e);
		return h == null ? null : h.getName();
	}

	/**
	 *
	 * @param setName the name of the set element
	 * @return then asset type name (Parameter, Resource etc)
	 */
	public static String getSetName(final String setName)
	{
		return (isSet(setName) || "IntentSet".equals(setName)) ? StringUtil.leftStr(setName, -3) : null;
	}

	/**
	 * @param name
	 * @param usage
	 * @param processUsage
	 *
	 * @return the SetHelper for the vector of resourcesets
	 */
	public static SetHelper getSet(final KElement parent, final String name, final EnumUsage usage, final String processUsage, final JDFIntegerList cpi)
	{
		KElement e = parent.getFirstChildElement();
		final String usageString = usage == null ? null : usage.getName();
		while (e != null)
		{
			final JDFIntegerList setCpi = new SetHelper(e).getCombinedProcessIndex();
			if (isSet(e) && (name == null || name.equals(e.getNonEmpty(AttributeName.NAME))) && StringUtil.equals(usageString, e.getNonEmpty(AttributeName.USAGE))
					&& StringUtil.equals(processUsage, e.getNonEmpty(AttributeName.PROCESSUSAGE)) && (setCpi == null || ContainerUtil.containsAny(setCpi, cpi)))
			{
				return new SetHelper(e);
			}
			e = e.getNextSiblingElement();
		}
		return null;
	}

	/**
	 * @param name
	 * @param usage
	 *
	 * @return the SetHelper for the vector of resourcesets
	 */
	public static SetHelper getSet(final KElement parent, final String name, final EnumUsage usage)
	{
		KElement e = parent.getFirstChildElement();
		final String usageString = usage == null ? null : usage.getName();
		while (e != null)
		{
			if (SetHelper.isSet(e) && (name == null || name.equals(e.getNonEmpty(AttributeName.NAME))) && StringUtil.equals(usageString, e.getNonEmpty(AttributeName.USAGE)))
			{
				return new SetHelper(e);
			}
			e = e.getNextSiblingElement();
		}
		if (usage == null)
		{
			e = parent.getFirstChildElement();
			while (e != null)
			{
				if (SetHelper.isSet(e) && (name == null || name.equals(e.getNonEmpty(AttributeName.NAME))))
				{
					return new SetHelper(e);
				}
				e = e.getNextSiblingElement();
			}
		}
		return null;
	}

	/**
	 *
	 * @param name
	 * @param usage
	 * @return a new set element
	 */
	public static SetHelper getCreateSet(final KElement parent, final String name, final EnumUsage usage)
	{
		SetHelper set = getSet(parent, name, usage);
		if (set == null)
			set = appendSet(parent, name, usage);
		return set;
	}

	/**
	 * @param name
	 * @param usage
	 * @param processUsage
	 *
	 * @return the SetHelper
	 */
	public static SetHelper getCreateSet(final KElement parent, final String name, final EnumUsage usage, final String processUsage, final JDFIntegerList cpi)
	{
		SetHelper s0 = getSet(parent, name, usage, processUsage, cpi);
		if (s0 == null)
		{
			s0 = appendSet(parent, name, usage);
			s0.setProcessUsage(processUsage);
			s0.setCombinedProcessIndex(cpi);
		}
		return s0;
	}

	/**
	 * @param family - always RESOURCE better use appendResourceSet
	 * @param name
	 * @param usage
	 * @return a new set element
	 */
	public static SetHelper appendSet(final KElement parent, final String name, final EnumUsage usage)
	{
		final KElement newSet = parent.appendElement(XJDFConstants.ResourceSet);
		newSet.setAttribute(AttributeName.NAME, name);
		final SetHelper h = new SetHelper(newSet);
		h.setID(KElement.xmlnsLocalName(name) + KElement.uniqueID(0));
		h.setUsage(usage);
		return h;
	}

	/**
	 * @param vmap
	 * @return
	 */
	public ResourceHelper getPartition(final VJDFAttributeMap vmap)
	{
		final List<ResourceHelper> v = getPartitionList();
		for (final ResourceHelper ph : v)
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
	public Vector<ResourceHelper> getPartitions(final VJDFAttributeMap vmap)
	{
		final Vector<ResourceHelper> v = getPartitions();
		final Vector<ResourceHelper> vRet = new Vector<>();
		for (final ResourceHelper ph : v)
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
	public Vector<ResourceHelper> getPartitions(final JDFAttributeMap map)
	{
		final Vector<ResourceHelper> v = getPartitions();
		final Vector<ResourceHelper> vRet = new Vector<>();
		for (final ResourceHelper ph : v)
		{
			if (ph.matches(map))
			{
				vRet.add(ph);
			}
		}
		return vRet;
	}

	/**
	 * returns all partitions that are a superset of map
	 *
	 * @param map
	 * @return
	 */
	public Vector<ResourceHelper> getSuperPartitions(final JDFAttributeMap map)
	{
		final Vector<ResourceHelper> v = getPartitions();
		final Vector<ResourceHelper> vRet = new Vector<>();
		for (final ResourceHelper ph : v)
		{
			final VJDFAttributeMap vMap = ph.getPartMapVector();
			if (map == null || vMap != null && vMap.subMap(map))
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
	public void setUsage(final EnumUsage value)
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
		return getAttribute(AttributeName.PROCESSUSAGE);
	}

	/**
	 *
	 *
	 * @return
	 */
	public JDFIntegerList getCombinedProcessIndex()
	{
		return JDFIntegerList.createIntegerList(getAttribute(AttributeName.COMBINEDPROCESSINDEX));
	}

	/**
	 * remove a CPI process
	 *
	 * @param typ
	 * @param pos
	 */
	public void removeTypeFromCPI(final int pos)
	{
		final JDFIntegerList il = getCombinedProcessIndex();
		if (il != null)
		{
			boolean mod = false;
			for (int i = 0; i < il.size(); i++)
			{
				final int iPos = il.getInt(i);
				if (iPos == pos)
				{
					il.remove(i);
					i--;
					mod = true;
				}
				else if (iPos > pos)
				{
					il.setInt(i, iPos - 1);
					mod = true;
				}
			}
			if (mod)
			{
				setCombinedProcessIndex(il);
			}
		}
	}

	public void addTypeToCPI(final int pos)
	{
		addTypeToCPI(pos, false);
	}

	/**
	 * add a CPI process
	 *
	 * @param typ
	 * @param pos
	 */
	public void addTypeToCPI(final int pos, final boolean insert)
	{
		final JDFIntegerList il = getCombinedProcessIndex();
		if (il != null)
		{
			boolean mod = false;
			for (int i = 0; i < il.size(); i++)
			{
				final int iPos = il.getInt(i);
				if (iPos >= pos)
				{
					il.setInt(i, iPos + 1);
					mod = true;
				}
			}
			if (!mod && insert)
			{
				il.add(pos);
				mod = true;
			}
			if (mod)
			{
				setCombinedProcessIndex(il);
			}
		}
	}

	/**
	 *
	 *
	 * @return
	 */
	public void setCombinedProcessIndex(final JDFIntegerList cpi)
	{
		setAttribute(AttributeName.COMBINEDPROCESSINDEX, cpi == null || cpi.isEmpty() ? null : cpi.toString());
	}

	/**
	 *
	 *
	 * @return
	 */
	public void setProcessUsage(final String processUsage)
	{
		setAttribute(AttributeName.PROCESSUSAGE, processUsage);
	}

	/**
	 *
	 *
	 * @return
	 */
	public void setUnit(final eUnit unit)
	{
		setAttribute(AttributeName.UNIT, unit == null ? null : unit.name());
	}

	/**
	 *
	 *
	 * @return
	 */
	public eUnit getUnit()
	{
		return eUnit.getEnum(getAttribute(AttributeName.UNIT));
	}

	/**
	 *
	 * @return
	 */
	@Override
	public String getDescriptiveName()
	{
		return getAttribute(AttributeName.DESCRIPTIVENAME);
	}

	/**
	 * @return the productID of the product
	 *
	 */
	@Override
	public String getExternalID()
	{
		return getAttribute(XJDFConstants.ExternalID);
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
	 *
	 * @param id
	 * @return
	 */
	public ResourceHelper getPartition(final String id)
	{
		if (!StringUtil.isEmpty(id))
		{
			final String partitionName = getPartitionName();
			KElement e = theElement.getFirstChildElement(partitionName, null);
			while (e != null)
			{
				if (id.equals(e.getID()))
					return new ResourceHelper(e);
				e = e.getNextSiblingElement(partitionName, null);
			}
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
	public void setID(final String newID)
	{
		final String oldID = getID();
		super.setID(newID);
		final List<ResourceHelper> parts = getPartitionList();
		if (parts != null)
		{
			for (final ResourceHelper part : parts)
			{
				String partID = part.getID();
				partID = StringUtil.replaceString(partID, oldID, newID);
				part.setID(newID);
			}
		}
	}

	/**
	 * returns the unified partmaps
	 *
	 * @return
	 */
	public VJDFAttributeMap getPartMapVector()
	{
		final List<ResourceHelper> vph = getPartitionList();
		final VJDFAttributeMap vMap = new VJDFAttributeMap();
		for (final ResourceHelper ph : vph)
		{
			vMap.addAll(ph.getPartMapList());
		}
		vMap.unify();
		return vMap;
	}

	/**
	 * returns the unified partmaps
	 *
	 * @return
	 */
	public List<JDFAttributeMap> getPartMapList()
	{
		final List<ResourceHelper> vph = getPartitionList();
		final JDFAttributeMapArray vMap = new JDFAttributeMapArray();
		for (final ResourceHelper ph : vph)
		{
			vMap.addAll(ph.getPartMapList());
		}
		vMap.unify();
		return vMap;
	}

	/**
	 *
	 * @return the ordered collection of PartMapVectors of each child resource
	 */
	public Collection<VJDFAttributeMap> getPartMapVectors()
	{
		final List<ResourceHelper> vph = getPartitionList();
		final ArrayList<VJDFAttributeMap> vMap = new ArrayList<>();
		for (final ResourceHelper ph : vph)
		{
			vMap.add(ph.getPartMapVector());
		}
		return vMap;
	}

	/**
	 *
	 * @return the ordered collection of PartMapVectors of each child resource
	 */
	public Collection<List<JDFAttributeMap>> getPartMapLists()
	{
		final List<ResourceHelper> vph = getPartitionList();
		final ArrayList<List<JDFAttributeMap>> vMap = new ArrayList<>();
		for (final ResourceHelper ph : vph)
		{
			vMap.add(ph.getPartMapVector());
		}
		return vMap;
	}

	/**
	 *
	 * @return
	 */
	public VString getDependentJobParts()
	{
		final VElement deps = theElement.getChildElementVector(XJDFConstants.Dependent, null);
		final VString ret = new VString();
		if (deps != null)
		{
			for (final KElement dep : deps)
			{
				final String jpID = dep.getNonEmpty(AttributeName.JOBPARTID);
				ret.appendUnique(jpID);
			}
		}
		return ret.isEmpty() ? null : ret;
	}

	/**
	 *
	 * @param name
	 */
	public void setName(final String name)
	{
		setAttribute(AttributeName.NAME, name);
	}

	/**
	 *
	 */
	public void removeIDs()
	{
		removeAttribute(AttributeName.ID, null);
		final List<ResourceHelper> v = getPartitionList();
		if (v != null)
		{
			for (final ResourceHelper ph : v)
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
	public int indexOf(final ResourceHelper bsHelper)
	{
		if (bsHelper == null)
			return -1;
		final List<KElement> v = theElement.getChildList(getPartitionName(), null);
		if (v == null)
			return -1;
		return v.indexOf(bsHelper.getRoot());

	}

	/**
	 * factory to create a helper from an element
	 *
	 * @param root the element to parse
	 * @return the helper
	 */
	public static SetHelper getHelper(KElement e)
	{
		while (e != null && !isSet(e))
			e = e.getParentNode_KElement();
		return isSet(e) ? new SetHelper(e) : null;
	}

	/**
	 * @see org.cip4.jdflib.extensions.BaseXJDFHelper#setExternalID(java.lang.String)
	 */
	@Override
	public void setExternalID(final String newID)
	{
		super.setExternalID(newID);
	}

	/**
	 * @see org.cip4.jdflib.extensions.BaseXJDFHelper#setDescriptiveName(java.lang.String)
	 */
	@Override
	public void setDescriptiveName(final String description)
	{
		super.setDescriptiveName(description);
	}

	/**
	 * @see org.cip4.jdflib.extensions.BaseXJDFHelper#setGeneralID(java.lang.String, java.lang.String)
	 */
	@Override
	public JDFGeneralID setGeneralID(final String idUsage, final String idValue)
	{
		return super.setGeneralID(idUsage, idValue);
	}

	@Override
	public boolean matches(final Object subset)
	{
		if (!(subset instanceof SetHelper))
			return false;
		final SetHelper sh = (SetHelper) subset;
		if (!ContainerUtil.equals(sh.getUsage(), getUsage()))
			return false;
		if (!ContainerUtil.equals(sh.getProcessUsage(), getProcessUsage()))
			return false;
		if (!ContainerUtil.equals(sh.getCombinedProcessIndex(), getCombinedProcessIndex()))
			return false;
		return true;
	}

	/**
	 * the number of partitions (resource elements)
	 * 
	 * @return
	 */
	public int size()
	{
		return theElement == null ? 0 : ContainerUtil.size(theElement.getChildList(getPartitionName(), null));
	}

	public boolean isEmpty()
	{
		return getResource(0) == null;
	}
}
