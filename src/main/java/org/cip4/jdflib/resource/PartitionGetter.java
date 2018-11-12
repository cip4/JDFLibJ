/*
 * The CIP4 Software License, Version 1.0
 *
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
package org.cip4.jdflib.resource;

import java.util.Iterator;
import java.util.Set;
import java.util.Vector;

import org.cip4.jdflib.core.AttributeName;
import org.cip4.jdflib.core.JDFException;
import org.cip4.jdflib.core.KElement;
import org.cip4.jdflib.core.VElement;
import org.cip4.jdflib.core.VString;
import org.cip4.jdflib.datatypes.JDFAttributeMap;
import org.cip4.jdflib.datatypes.VJDFAttributeMap;
import org.cip4.jdflib.node.JDFNode;
import org.cip4.jdflib.resource.JDFResource.EnumPartIDKey;
import org.cip4.jdflib.resource.JDFResource.EnumPartUsage;
import org.cip4.jdflib.util.ContainerUtil;

/**
 * class that evaluates the partitions based on the underlying PartitionMap This class will typically return JDFAttributeMaps that exist in the PartitionMap
 *
 * @author Rainer Prosi, Heidelberger Druckmaschinen *
 */
public class PartitionGetter
{
	/**
	 *
	 */
	private final JDFResource resourceRoot;

	/**
	 *
	 * @param leafMap
	 * @param partMap
	 * @param partUsage
	 * @return
	 */
	JDFAttributeMap getPartitionFromMap(final JDFAttributeMap partMap, EnumPartUsage partUsage)
	{
		if (partMap == null)
		{
			return new JDFAttributeMap();
		}
		else if (partMap.isEmpty())
		{
			return partMap;
		}
		else if (hasGap(partMap, null))
		{
			return null;
		}
		final JDFResource mapRes = leafMap.get(partMap);
		JDFAttributeMap ret = mapRes == null ? null : partMap;
		if (ret == null)
		{
			ret = checkPV(partMap, partUsage);
		}
		if (ret == null) // no explicit hit
		{
			if (partUsage == null)
			{
				partUsage = resourceRoot.getPartUsage();
			}
			if (!EnumPartUsage.Explicit.equals(partUsage))
			{
				ret = getImplicitPartitionFromMap(partMap, partUsage);
				if (EnumPartUsage.Sparse.equals(partUsage) && leafMap.get(ret).getDirectPartition(0) != null)
				{
					ret = null;
				}
			}
		}
		return ret;

	}

	/**
	 *
	 * @param partMap
	 * @param partUsage
	 * @return
	 */
	JDFAttributeMap checkPV(final JDFAttributeMap partMap, final EnumPartUsage partUsage)
	{
		JDFAttributeMap onlyMap = null;
		if (containsEvil(partMap) && !leafMap.hasMissingKeys(partMap))
		{
			for (final JDFAttributeMap map : leafMap.keySet())
			{
				if (JDFPart.subPartMap(map, partMap, strictPartVersion))
				{
					if (onlyMap != null)
					{
						onlyMap.remove(AttributeName.PARTVERSION);
						return EnumPartUsage.Explicit.equals(partUsage) || leafMap.get(onlyMap) == null ? null : onlyMap;
					}
					else
						onlyMap = map.clone();
				}
			}
		}

		return onlyMap;
	}

	boolean containsEvil(final JDFAttributeMap partMap)
	{
		int s = partMap.size();
		for (final String key : new String[] { AttributeName.RUN, AttributeName.SIGNATURENAME, AttributeName.SHEETNAME, AttributeName.SIDE, AttributeName.SEPARATION })
		{
			if (partMap.containsKey(key))
				s--;

			if (s == 0)
				return false;
		}

		if (partMap.containsKey(AttributeName.PARTVERSION) && !strictPartVersion || !leafMap.getPartIDKeys().contains(AttributeName.PARTVERSION))
		{
			s--;
		}
		return s == 0;
	}

	boolean hasSparseLeaf(final JDFAttributeMap current, JDFAttributeMap partMap)
	{
		partMap = partMap.clone();
		partMap.reduceMap(leafMap.getPartIDKeys());
		partMap.removeKeys(current.getKeys());
		return !partMap.isEmpty();
	}

	/**
	 * loop to find partitions - will NOT find explicit matches which need to be checked first with a get()
	 *
	 * @param partMap
	 * @param partUsage
	 * @return
	 */
	JDFAttributeMap getImplicitPartitionFromMap(final JDFAttributeMap partMap, final EnumPartUsage partUsage)
	{
		int size = partMap.size();
		if (size > 1 || leafMap.hasMissingKeys(partMap))
		{
			final JDFAttributeMap reducedMap = partMap.clone();
			final VString partIDKeys = resourceRoot.getPartIDKeys();
			reducedMap.reduceMap(partIDKeys);
			for (int i = partIDKeys.size(); i >= 0; i--)
			{
				if (i < partIDKeys.size())
					reducedMap.remove(partIDKeys.get(i));
				if (reducedMap.size() != size)
				{
					size = reducedMap.size();
					final JDFResource mapRes = leafMap.get(reducedMap);

					if (mapRes != null)
					{
						return reducedMap;
					}
					else
					{
						final JDFAttributeMap pv = checkPV(reducedMap, partUsage);
						if (pv != null)
						{
							return pv;
						}
					}
				}
			}
		}
		return new JDFAttributeMap();
	}

	/**
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString()
	{
		return "PartitionGetter strict=" + strictPartVersion + " " + localPartMap + "\n" + resourceRoot.toString();
	}

	private boolean strictPartVersion;
	private boolean followIdentical;
	private final PartitionMap leafMap;
	private final JDFAttributeMap localPartMap;

	/**
	 * Getter for followIdentical attribute.
	 *
	 * @return the followIdentical
	 */
	public boolean isFollowIdentical()
	{
		return followIdentical;
	}

	/**
	 * Setter for followIdentical attribute.
	 *
	 * @param followIdentical the followIdentical to set
	 */
	public void setFollowIdentical(final boolean followIdentical)
	{
		this.followIdentical = followIdentical;
	}

	/**
	 * @param jdfResource TODO
	 *
	 */
	public PartitionGetter(final JDFResource jdfResource)
	{
		super();
		resourceRoot = jdfResource.getResourceRoot();
		strictPartVersion = false;
		followIdentical = true;
		leafMap = resourceRoot.getPartitionMapper();
		localPartMap = jdfResource.getPartMap();
	}

	/**
	 * Gets the vector of parts (resource leaves or nodes) that match mAttribute
	 *
	 * @param vm the map of key-value partitions (where key - PartIDKey, value - its value)
	 * @param partUsage also accept nodes that are are not completely specified in the partmap, e.g. if partitioned by run, RunPage and only Run is specified
	 *
	 * @return VElement - the vector of matching resource leaves or nodes
	 *
	 * @default getPartitionVector(m, null)
	 */
	public VElement getPartitionVector(VJDFAttributeMap vm, final EnumPartUsage partUsage)
	{
		vm = getCompletePartMapVector(vm);
		if (vm == null)
			return null;
		final VJDFAttributeMap vMap = getPartitionMaps(vm, partUsage);
		final VElement v = new VElement();
		for (final JDFAttributeMap map : vMap)
			v.add(leafMap.get(map));
		return v;
	}

	/**
	 * Gets the vector of parts (resource leaves or nodes) that match mAttribute
	 *
	 * @param vm the map of key-value partitions (where key - PartIDKey, value - its value)
	 * @param partUsage also accept nodes that are are not completely specified in the partmap, e.g. if partitioned by run, RunPage and only Run is specified
	 *
	 * @return VElement - the vector of matching resource leaves or nodes
	 *
	 * @default getPartitionVector(m, null)
	 */
	public VJDFAttributeMap getPartitionMaps(VJDFAttributeMap vm, final EnumPartUsage partUsage)
	{
		if (vm != null && resourceRoot.getImplicitPartitions() != null)
		{
			final VJDFAttributeMap vmNew = new VJDFAttributeMap();
			for (final JDFAttributeMap map : vm)
			{
				final JDFAttributeMap removeImplicitPartions = removeImplicitPartions(map.clone(), partUsage);
				if (removeImplicitPartions != null && !removeImplicitPartions.isEmpty())
				{
					vmNew.appendUnique(removeImplicitPartions);
				}
			}
			vm = vmNew;
		}

		final VJDFAttributeMap v = new VJDFAttributeMap();

		if (ContainerUtil.getNonEmpty(vm) == null)
		{
			v.add(new JDFAttributeMap());
		}
		else
		{
			for (final JDFAttributeMap map : vm)
			{
				final VJDFAttributeMap next = getPartitionMaps(map, partUsage, true);
				v.addAll(next);
			}
		}

		v.unify();
		return v;
	}

	/**
	 * Gets the vector of parts (resource leaves or nodes) that match mAttribute
	 *
	 * @param m the map of key-value partitions (where key - PartIDKey, value - its value)
	 * @param partUsage also accept nodes that are are not completely specified in the partmap, e.g. if partitioned by run, RunPage and only Run is specified
	 *
	 * @return VElement - the vector of matching resource leaves or nodes
	 *
	 * @default getPartitionVector(m, null)
	 */
	VElement getPartitionLeafVector(final JDFAttributeMap m, final EnumPartUsage partUsage)
	{
		final VElement v = getPartitionVector(m, partUsage);
		final VElement vNew = new VElement();
		for (final KElement e : v)
		{
			final JDFResource r = (JDFResource) e;
			final VElement vr = r.getLeaves(false);
			vNew.addAll(vr);
		}
		vNew.unify();
		return vNew;
	}

	/**
	 * Gets the vector of parts (resource leaves or nodes) that match mAttribute
	 *
	 * @param m the map of key-value partitions (where key - PartIDKey, value - its value)
	 * @param partUsage also accept nodes that are are not completely specified in the partmap, e.g. if partitioned by run, RunPage and only Run is specified
	 *
	 * @return VElement - the vector of matching resource leaves or nodes
	 *
	 * @default getPartitionVector(m, null)
	 */
	public VElement getPartitionVector(JDFAttributeMap m, final EnumPartUsage partUsage)
	{
		m = getCompletePartMap(m, false);
		if (m == null)
			return null;

		final VJDFAttributeMap vMap = getPartitionMaps(m, partUsage, true);
		final VElement v = new VElement();
		for (final JDFAttributeMap map : vMap)
			v.add(leafMap.get(map));
		return v;
	}

	/**
	 * Gets the vector of parts (resource leaves or nodes) that match mAttribute
	 *
	 * @param m the map of key-value partitions (where key - PartIDKey, value - its value)
	 * @param partUsage also accept nodes that are are not completely specified in the partmap, e.g. if partitioned by run, RunPage and only Run is specified
	 *
	 * @return VElement - the vector of matching resource leaves or nodes
	 *
	 * @default getPartitionVector(m, null)
	 */
	VJDFAttributeMap getPartitionMaps(JDFAttributeMap m, EnumPartUsage partUsage, final boolean updateIdentical)
	{
		if (partUsage == null)
			partUsage = resourceRoot.getPartUsage();
		if (m != null)
			m = removeImplicitPartions(m, partUsage);

		JDFAttributeMap fast = getPartitionFromMap(m, EnumPartUsage.Explicit);
		if (updateIdentical && fast != null)
			fast = updateIdentical(fast);
		final VJDFAttributeMap v = (fast != null) ? new VJDFAttributeMap(fast) : specialSearch(m, partUsage);

		return v;
	}

	/**
	 * @param m
	 * @param partUsage
	 * @param v
	 */
	VJDFAttributeMap specialSearch(final JDFAttributeMap m, final EnumPartUsage partUsage)
	{
		final int maxSize = 1 + lastPos(m, resourceRoot.getPartIDKeys(), true);
		final VJDFAttributeMap v = new VJDFAttributeMap();
		for (final JDFAttributeMap map : leafMap.keySet())
		{
			if (map.size() <= maxSize)
			{
				if (JDFPart.subPartMap(map, m, strictPartVersion))
				{
					v.add(map);
				}
				else if (EnumPartUsage.Implicit.equals(partUsage) && JDFPart.overlapPartMap(map, m, strictPartVersion))
				{
					v.add(map);
				}
				else if (EnumPartUsage.Sparse.equals(partUsage) && JDFPart.overlapPartMap(map, m, strictPartVersion) && leafMap.get(map).getDirectPartition(0) == null)
				{
					v.add(map);
				}
			}
		}

		v.unify();
		if (EnumPartUsage.Implicit.equals(partUsage))
		{
			removeImplicitDuplicates(v);
		}
		return v;
	}

	/**
	 * @param v
	 */
	void removeImplicitDuplicates(final VJDFAttributeMap v)
	{
		for (int i = v.size() - 1; i >= 0; i--)
		{
			for (int j = i - 1; j >= 0; j--)
			{
				if (v.get(j).subMap(v.get(i)))
				{
					v.remove(i);
					break;
				}
				else if (v.get(i).subMap(v.get(j)))
				{
					v.remove(j);
					i--;
				}
			}
		}
	}

	private JDFAttributeMap updateIdentical(final JDFAttributeMap fast)
	{
		if (fast != null && isFollowIdentical())
		{
			final JDFResource r = leafMap.get(fast);
			final JDFAttributeMap idMap = r == null ? null : r.getIdenticalMap();
			if (idMap != null)
			{
				return idMap;
			}
		}
		return fast;
	}

	/**
	 *
	 * @param m
	 * @return
	 */
	private JDFAttributeMap removeImplicitPartions(JDFAttributeMap m, final EnumPartUsage partUsage)
	{
		if (m == null || m.isEmpty())
		{
			return m;
		}
		final Vector<EnumPartIDKey> v = resourceRoot.getImplicitPartitions();
		m = new JDFAttributeMap(m);
		if (v != null)
		{
			for (final EnumPartIDKey e : v)
			{
				m.remove(e.getName());
			}
		}
		if (!EnumPartUsage.Explicit.equals(partUsage))
		{
			m.reduceMap(leafMap.getPartIDKeys());
		}
		return m;
	}

	/**
	 * Gets the first part that matches mAttribute
	 *
	 * @param m the map of key-value partitions (where key - PartIDKey, value - its value)
	 * @param partUsage also accept nodes that are are not completely specified in the partmap, e.g. if partitioned by run, RunPage and only Run is specified
	 *
	 * @return JDFResource: the first matching resource leaf or node
	 * @default getPartition(m, null)
	 */
	public JDFResource getPartition(final JDFAttributeMap m, final EnumPartUsage partUsage)
	{
		final JDFAttributeMap map0 = getCompletePartMap(m, false);
		if (map0 == null)
		{
			return null;
		}
		JDFAttributeMap map = getPartitionImpl(map0, partUsage);
		if (map != null)
		{
			final JDFAttributeMap map2 = updateIdentical(map);
			if (!map.equals(map2))
			{
				map = getPartitionImpl(map2, partUsage);
			}
		}
		return map == null ? null : leafMap.get(map);
	}

	JDFAttributeMap getPartitionImpl(JDFAttributeMap m, EnumPartUsage partUsage)
	{
		if (m.isEmpty())
		{
			return m;
		}
		if (leafMap.get(m) != null)
		{
			return m;
		}
		if (partUsage == null)
		{
			partUsage = resourceRoot.getPartUsage();
		}
		m = removeImplicitPartions(m, partUsage);
		if (m.isEmpty())
		{
			return m;
		}
		JDFAttributeMap partitionFromMap = getPartitionFromMap(m, partUsage);
		if (partitionFromMap == null || !m.equals(partitionFromMap) && (!JDFPart.getFastparts().containsAll(partitionFromMap.keySet())))
		{
			partitionFromMap = reducePartMap(m, partUsage);
		}
		return partitionFromMap;

	}

	/**
	 */
	JDFAttributeMap reducePartMap(final JDFAttributeMap m, final EnumPartUsage partUsage)
	{
		JDFAttributeMap partitionFromMap = null;
		final VJDFAttributeMap vMap = getPartitionMaps(m, partUsage, false);
		if (vMap != null)
		{
			if (vMap.size() == 1)
			{
				partitionFromMap = vMap.get(0);
			}
			else if (vMap.size() > 1)
			{
				if (EnumPartUsage.Explicit.equals(partUsage))
				{
					partitionFromMap = null;
				}
				else
				{
					// not nice, but this is what the old algorithm did - find the closest common ancestor
					partitionFromMap = vMap.getCommonMap();
					final VString partIDKeys = resourceRoot.getPartIDKeys();
					final int lastPos = lastPos(partitionFromMap, partIDKeys, false) - 1;
					final int firstPos = firstPos(partitionFromMap, partIDKeys);
					for (int i = Math.max(firstPos, lastPos); i < partIDKeys.size(); i++)
					{
						partitionFromMap.remove(partIDKeys.get(i));
					}
				}
			}
		}
		return partitionFromMap;
	}

	int firstPos(final JDFAttributeMap partitionFromMap, final VString partIDKeys)
	{
		int firstPos = 0;
		for (final String key : partIDKeys)
		{
			if (partitionFromMap.get(key) == null)
				break;
			firstPos++;
		}
		return firstPos;
	}

	/**
	 * Gets a matching part from somewhere down there,<br>
	 * returns the closest ancestor of all matching elements within the target vector
	 *
	 * @param m map of attributes that should fit
	 * @param partUsage lso accept nodes that are are not completely specified in the partmap, e.g. if partitioned by run, RunPage and only Run is specified
	 * @return the first found matching resource node or leaf
	 */
	protected JDFResource getDeepPart(final JDFAttributeMap m, final EnumPartUsage partUsage)
	{
		JDFResource retRes = null;
		final VString partIDKeys = resourceRoot.getPartIDKeys();

		final VElement vRes = getPartitionVector(m, partUsage);

		if (vRes != null)
		{
			final int siz = vRes.size();

			if (siz == 0)
			{
				// nothing fits at all
				return retRes;
			}
			else if (siz == 1)
			{ // only one, take it
				retRes = (JDFResource) vRes.elementAt(0);
			}
			else
			{ // multiple, get the closest ancestor
				JDFResource e = (JDFResource) vRes.elementAt(0);
				if (e.isResourceRoot())
				{
					return e;
				}

				e = (JDFResource) e.getParentNode();
				do // if more than one, loop at leas once
				{
					int i = 0;
					for (i = siz - 1; i > 0; i--) // e is always an ancestor of
					// vElm[i];
					// go backwards since the chance of mismatch is
					// greater at the end of the list
					{
						if (!e.isAncestor(vRes.elementAt(i))) // found a
						// misMatch
						{
							e = (JDFResource) e.getParentNode();
							break;
						}
					}
					if (i == 0) // went through the entire loop with no mismatch
					// --> heureka and ciao
					{
						retRes = e;
						break; // while e!=this
					}
				}
				while (e != resourceRoot);

				if (e.isResourceRoot())
				{
					return e; // the root is always ok
				}
			}
		}

		if (retRes == null)
		{
			return null;
		}

		int retSize = -1;
		JDFResource loopRes = retRes;
		final Set<String> vKeys = m.keySet();

		// loop until we hit this or root, whichever is closer
		while (true)
		{
			final JDFAttributeMap returnMap = loopRes.getPartMap(partIDKeys);
			// only check the keys, not the values since <Identical> elements
			// may screw up the values...
			returnMap.reduceMap(vKeys);
			// we lost a key - break one prior to this
			if (returnMap.size() < retSize)
			{
				return retRes; // the child of the tested element
			}
			else if (retSize == -1)
			{ // first loop initialization
				retSize = returnMap.size();
			}
			// we hit the starting point - nothing left to do
			if ((loopRes == resourceRoot) || loopRes.isResourceRoot())
			{
				return loopRes;
			}
			// no check failed - go one closer to the root
			retRes = loopRes;
			loopRes = (JDFResource) loopRes.getParentNode();
			if (loopRes == null)
			{
				throw (new JDFException("JDFResource::GetDeepPart ran into null element while searching tree, ID=" + resourceRoot.getID()));
			}
		}
		// return retRes;
	}

	/**
	 * reorder the partIDKeys used for generating the new partition based on existing partIDKeys
	 *
	 * @param vPartKeys
	 * @return VString the reordered VString of partIDKeys
	 */
	private VString reorderPartKeys(final VString vPartKeys)
	{
		if (vPartKeys == null || vPartKeys.isEmpty())
		{
			return resourceRoot.getPartIDKeys();
		}
		VString vPartIDKeys = new VString(vPartKeys);
		final VString vExistingPartKeys = resourceRoot.getPartIDKeys();
		final VString vTmpPartIDKeys = new VString();
		if (vExistingPartKeys != null && !vExistingPartKeys.isEmpty())
		{
			boolean allIn = true;
			for (int i = 0; allIn && i < vPartIDKeys.size(); i++)
			{
				if (!vExistingPartKeys.contains(vPartIDKeys.get(i)))
					allIn = false;
			}
			if (allIn)
				return vExistingPartKeys;

			int n = vExistingPartKeys.size();
			if (vPartIDKeys.size() < n)
			{
				n = vPartIDKeys.size();
			}

			for (int i = 0; i < n; i++)
			{
				final String partKey = vExistingPartKeys.elementAt(i);
				if (!vPartIDKeys.contains(partKey)) // allow reordering of the
				// existing partidkeys
				{
					throw new JDFException("reorderPartKeys: reordering incompatible partitions for ID=" + resourceRoot.getID() + ". Key: " + partKey + " " + vPartIDKeys);
				}
				vTmpPartIDKeys.add(partKey);
				vPartIDKeys.remove(partKey);
			}
			for (int i = 0; i < vPartIDKeys.size(); i++)
			{
				vTmpPartIDKeys.add(vPartIDKeys.elementAt(i));
			}
			vPartIDKeys = vTmpPartIDKeys;
		}
		return vPartIDKeys;
	}

	/**
	 * Recursively adds the partition leaves defined in partMap
	 *
	 * @param partMap the map of part keys
	 * @param vPartKeys the vector of partIDKeys strings of the resource. If empty (the default), the Resource PartIDKeys attribute is used
	 *
	 * @return JDFResource the last created partition leaf
	 *
	 * @throws JDFException if there are in the partMap not matching partitions
	 * @throws JDFException if there is an attempt to fill non-matching partIDKeys
	 * @throws JDFException if by adding of last partition key there is either non-continuous partmap or left more than one key
	 *
	 * @default getCreatePartition(partMap, null)
	 */
	public JDFResource getCreatePartition(JDFAttributeMap partMap, VString vPartKeys)
	{
		partMap = getCompletePartMap(partMap, true);
		if (partMap == null || partMap.isEmpty())
		{
			return resourceRoot.getResourceRoot();
		}
		JDFResource r = getPartition(partMap, EnumPartUsage.Explicit);
		if (r != null)
			return r;

		r = getPartition(partMap, EnumPartUsage.Implicit);
		final JDFAttributeMap thisMap = r.getPartMap();
		final VString localKeys = thisMap.getKeys();
		if (thisMap.size() == partMap.size())
			return r;
		final int lastPos = 1 + lastPos(partMap, vPartKeys, false);
		if (vPartKeys != null && lastPos < vPartKeys.size())
		{
			vPartKeys = new VString(vPartKeys);
			while (lastPos < vPartKeys.size())
			{
				vPartKeys.remove(lastPos);
			}
		}
		VString vPartIDKeys = reorderPartKeys(vPartKeys);
		// check whether we are already ok
		final VString newKeys = partMap.getKeys();
		if (vPartIDKeys != null)
		{
			newKeys.removeAll(vPartIDKeys);
		}
		if (newKeys.size() == 1)
		{
			vPartIDKeys = (VString) ContainerUtil.addAll(vPartIDKeys, newKeys);
		}
		// only heuristically add stuff if needed...
		if (newKeys.size() > 1)
		{
			vPartIDKeys = expandKeysFromNode(partMap, vPartIDKeys);
		}
		resourceRoot.setPartIDKeys(vPartIDKeys);
		leafMap.updatePartIDKeys(vPartIDKeys);

		final int s = vPartIDKeys == null ? 0 : vPartIDKeys.size();
		if (s < partMap.size())
		{
			throw new JDFException("GetCreatePartition: " + resourceRoot.getNodeName() + " ID=" + resourceRoot.getID() + "insufficient partIDKeys " + leafMap.getPartIDKeys() + " for " + partMap);
		}
		// create all partitions
		JDFAttributeMap map = thisMap;
		for (int i = localKeys.size(); i < partMap.size(); i++)
		{
			final String key = vPartIDKeys.get(i);
			final String value = partMap.get(key);

			if (value != null)
			{
				r = (JDFResource) r.appendElementRaw(resourceRoot.getNodeName(), resourceRoot.getNamespaceURI());
				r.init();
				r.setAttribute(key, value);
				map = map.clone();
				map.put(key, value);
				leafMap.put(map, r);
			}
			else
			{
				throw new JDFException("GetCreatePartition: " + resourceRoot.getNodeName() + " ID=" + resourceRoot.getID() + " attempting to fill non-matching partIDKeys: " + key + " valid keys: "
						+ "Current PartIDKeys: " + resourceRoot.getPartIDKeys() + " complete map: " + partMap);
			}
		}
		return r;
	}

	/**
	 * Adds a new part to this node, also handles PartIDKeys in the root etc.
	 *
	 * @param partType part type of a new part
	 * @param value its value
	 *
	 * @return JDFResource - the newly created part
	 */
	JDFResource addPartition(final EnumPartIDKey partType, final String value)
	{
		final JDFResource parent = getPartition(localPartMap, EnumPartUsage.Explicit);
		if (parent.isResourceElement())
		{
			throw new JDFException("Attempting to add partition to resource element: " + parent.buildXPath(null, 1));
		}
		if (partType == null)
		{
			throw new JDFException("Attempting to add null partition to resource: " + parent.buildXPath(null, 1));
		}
		VString partIDKeys = leafMap.getPartIDKeys();
		final int posOfType = partIDKeys == null ? -1 : partIDKeys.indexOf(partType.getName());
		if (posOfType < 0)
		{
			if (!parent.isLeaf())
			{
				throw new JDFException("addPartition: adding inconsistent partition ID=" + resourceRoot.getID() + " - parent must be a leaf");
			}
		}
		else if (posOfType == 0)
		{
			if (!parent.isResourceRootRoot())
			{
				throw new JDFException("addPartition: adding inconsistent partition ID=" + resourceRoot.getID() + " - must be root");
			}
		}
		else if (partIDKeys != null)
		{
			final String parentPart = partIDKeys.get(posOfType - 1);
			if (!parent.hasAttribute_KElement(parentPart, null, false))
			{
				throw new JDFException("addPartition: adding inconsistent partition  ID=" + resourceRoot.getID() + "- parent must have partIDKey: " + parentPart);
			}
		}

		if (partIDKeys == null || !partIDKeys.contains(partType.getName()))
		{
			resourceRoot.addPartIDKey(partType);
			partIDKeys = resourceRoot.getPartIDKeys();
			leafMap.updatePartIDKeys(partIDKeys);
		}
		final JDFAttributeMap map = parent.getPartMap();
		map.put(partType.getName(), value);
		final JDFAttributeMap m = getPartitionFromMap(map, EnumPartUsage.Explicit);
		if (m != null)
		{
			throw new JDFException("addPartition: adding duplicate partition for ID=" + resourceRoot.getID() + " " + partType + "=" + value);
		}

		final JDFResource p = (JDFResource) parent.appendElementRaw(resourceRoot.getNodeName(), resourceRoot.getNamespaceURI());
		p.init();
		p.setPartIDKey(partType, value);
		return p;
	}

	/**
	 * Recursively adds the partition leaves defined in vPartMap
	 *
	 * @param vPartMap the vector of maps of part keys
	 * @param vPartIDKeys the vector of partIDKeys strings of the resource. If empty (the default) the Resource PartIDKeys attribute is used
	 * @return VElement - vector of newly created partitions
	 *
	 * @throws JDFException if there are in the partMap not matching partitions
	 * @throws JDFException if there is an attempt to fill non-matching partIDKeys
	 * @throws JDFException if by adding of last partition key there is either non-continuous partmap or left more than one key
	 *
	 * @default createPartitions(vPartMap, VString.emptyVector)
	 */
	public VElement createPartitions(final VJDFAttributeMap vPartMap, final VString vPartIDKeys)
	{
		final VElement v = new VElement();
		if (vPartMap != null)
		{
			VString currentPartIDKeys = resourceRoot.getPartIDKeys();
			if (currentPartIDKeys != null)
			{
				currentPartIDKeys.appendUnique(vPartIDKeys);
			}
			else
			{
				currentPartIDKeys = vPartIDKeys;
			}

			final VJDFAttributeMap newMaps = updateCreate(vPartMap, currentPartIDKeys);
			for (final JDFAttributeMap partMap : newMaps)
			{
				v.add(getCreatePartition(partMap, vPartIDKeys));
			}
		}
		return v;
	}

	private VJDFAttributeMap updateCreate(final VJDFAttributeMap vPartMap, final VString vPartIDKeys)
	{
		final VJDFAttributeMap newMap = new VJDFAttributeMap();
		for (final JDFAttributeMap map : vPartMap)
		{
			for (final JDFAttributeMap key : leafMap.keySet())
			{
				if (key.overlapMap(map))
				{
					final JDFAttributeMap next = map.getOrMap(key);
					if (!hasGap(next, vPartIDKeys))
					{
						newMap.add(next);
					}
				}
			}
		}
		newMap.unify();
		return newMap;
	}

	/**
	 *
	 * @param next
	 * @param vPartIDKeys
	 * @return
	 */
	boolean hasGap(final JDFAttributeMap next, VString vPartIDKeys)
	{
		if (vPartIDKeys == null)
			vPartIDKeys = resourceRoot.getPartIDKeys();
		return lastPos(next, vPartIDKeys, false) > next.size() - 1;
	}

	/**
	 *
	 * @param next
	 * @param vPartIDKeys
	 * @return
	 */
	int lastPos(final JDFAttributeMap next, final VString vPartIDKeys, final boolean newIsGap)
	{
		int last = -1;
		if (vPartIDKeys != null)
		{
			final Set<String> keys = next.keySet();
			for (final String pik : keys)
			{
				int nextlast = vPartIDKeys.index(pik);
				if (newIsGap && nextlast == -1)
					nextlast = Math.max(vPartIDKeys.size(), next.size());
				last = Math.max(last, nextlast);
			}
		}
		return last;
	}

	/**
	 * heuristic guess of the best possible partidkey sequence<br/>
	 * note that we have no link context and therefore can only search in the local parent node
	 *
	 * @param partMap the partmap that we want to create
	 * @param vPartIDKeys the known base partidkeys
	 * @return the best guess vector of partidkeys
	 */
	private VString expandKeysFromNode(final JDFAttributeMap partMap, final VString vPartIDKeys)
	{
		final JDFNode n = resourceRoot.getParentJDF();
		if (n == null)
		{
			return vPartIDKeys;
		}

		final VString nodeKeys = n.getPartIDKeys(partMap);
		final int nodeKeySize = nodeKeys.size();

		final int partKeySize = vPartIDKeys != null ? vPartIDKeys.size() : 0;
		if (nodeKeySize <= partKeySize)
		{
			return vPartIDKeys;
		}

		if (vPartIDKeys != null)
		{
			final Iterator<String> nodeKeysIterator = nodeKeys.iterator();
			final Iterator<String> vPartIDKeysIterator = vPartIDKeys.iterator();
			while (vPartIDKeysIterator.hasNext())
			{
				if (!vPartIDKeysIterator.next().equals(nodeKeysIterator.next()))
				{
					return vPartIDKeys; // nodekeys and partkeys are
					// incompatible, return the input
				}
			}
		}

		// all beginning elements are equal but we have more - use these as a
		// best guess
		return nodeKeys;
	}

	/**
	 *
	 * if set to true, partversion will only match if the string matches exactly<br/>
	 * if set to false (the default) partversions will match if tokens overlap
	 *
	 * @param strictPartVersion
	 */
	public void setStrictPartVersion(final boolean strictPartVersion)
	{
		this.strictPartVersion = strictPartVersion;
	}

	JDFAttributeMap getCompletePartMap(final JDFAttributeMap partMap, final boolean create)
	{
		if (localPartMap.isEmpty())
		{
			return partMap == null ? localPartMap : partMap;
		}
		else
		{
			if (partMap != null && !partMap.isEmpty())
			{
				if (!JDFPart.overlapPartMap(localPartMap, partMap, strictPartVersion))
				{
					if (create)
						throw new JDFException("Incompatible part maps: local: " + localPartMap.showKeys(null) + " request: " + partMap.showKeys(null) + " ID=" + resourceRoot.getID());
					else
						return null;
				}
				final JDFAttributeMap clone = partMap.clone();
				clone.putAll(localPartMap);
				return clone;
			}
		}
		return localPartMap;
	}

	VJDFAttributeMap getCompletePartMapVector(VJDFAttributeMap vm)
	{
		if (vm == null)
			vm = new VJDFAttributeMap();
		if (vm.isEmpty())
			vm.add(new JDFAttributeMap());
		if (localPartMap.isEmpty())
			return vm;
		final VJDFAttributeMap newMap = new VJDFAttributeMap();
		for (final JDFAttributeMap m : vm)
		{
			final JDFAttributeMap completePartMap = getCompletePartMap(m, false);
			if (completePartMap != null)
			{
				newMap.add(completePartMap);
			}
		}
		return newMap.size() > 0 ? newMap : null;
	}

}