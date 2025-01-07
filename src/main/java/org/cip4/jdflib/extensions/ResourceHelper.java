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

import java.util.Collection;
import java.util.List;

import org.apache.commons.lang.enums.ValuedEnum;
import org.cip4.jdflib.core.AttributeName;
import org.cip4.jdflib.core.ElementName;
import org.cip4.jdflib.core.JDFComment;
import org.cip4.jdflib.core.JDFConstants;
import org.cip4.jdflib.core.JDFPartAmount;
import org.cip4.jdflib.core.KElement;
import org.cip4.jdflib.core.VString;
import org.cip4.jdflib.datatypes.JDFAttributeMap;
import org.cip4.jdflib.datatypes.JDFAttributeMapArray;
import org.cip4.jdflib.datatypes.VJDFAttributeMap;
import org.cip4.jdflib.ifaces.IAmountPoolContainer;
import org.cip4.jdflib.pool.JDFAmountPool;
import org.cip4.jdflib.pool.JDFAmountPool.AmountPoolHelper;
import org.cip4.jdflib.resource.JDFPart;
import org.cip4.jdflib.resource.JDFResource;
import org.cip4.jdflib.resource.JDFResource.EnumResStatus;
import org.cip4.jdflib.resource.process.JDFGeneralID;
import org.cip4.jdflib.util.StringUtil;

/**
 * @author Rainer Prosi, Heidelberger Druckmaschinen *
 */
public class ResourceHelper extends BaseXJDFHelper implements IAmountPoolContainer
{
	/**
	 * @param partition
	 */
	public ResourceHelper(final KElement partition)
	{
		super();
		this.theElement = partition;
	}

	/**
	 * @param toCheck
	 * @return true if toCheck is an asset (Resource, Parameter...)
	 */
	public static boolean isAsset(final KElement toCheck)
	{
		if (toCheck == null)
			return false;
		final KElement parent = toCheck.getParentNode_KElement();
		final String setName = SetHelper.getSetName(parent);
		return toCheck.getLocalName().equals(setName);
	}

	/**
	 * @param toCheck
	 * @return true if toCheck is an asset (Resource, Parameter...)
	 */
	public static boolean isAsset(final KElement toCheck, final String resName)
	{
		if (isAsset(toCheck))
		{
			if (StringUtil.getNonEmpty(resName) == null)
			{
				return true;
			}
			final KElement parent = toCheck.getParentNode_KElement();
			final String name = parent.getAttribute("Name");
			return name.equals(resName);
		}
		return false;
	}

	/**
	 * @param toCheck
	 * @return true if toCheck is an explicit resource element (Media, RunList, ...)
	 */
	public static boolean isResourceElement(final KElement toCheck)
	{
		if (toCheck == null)
			return false;
		final KElement parent = toCheck.getParentNode_KElement();
		return ResourceHelper.isAsset(parent) && new ResourceHelper(parent).getResource() == toCheck;
	}

	/**
	 * factory to create a helper from an element
	 *
	 * @param res the element to parseeither a "Resource" or a resource element
	 * @return the helper
	 */
	public static ResourceHelper getHelper(final KElement res)
	{
		if (isAsset(res))
			return new ResourceHelper(res);
		if (isResourceElement(res))
			return new ResourceHelper(res.getParentNode_KElement());
		return null;
	}

	/**
	 * @return
	 */
	@Override
	public VJDFAttributeMap getPartMapVector()
	{
		final VJDFAttributeMap vMap = new VJDFAttributeMap();
		addParts(vMap);
		return vMap;
	}

	private void addParts(final List<JDFAttributeMap> vMap)
	{
		final Collection<KElement> vParts = theElement.getChildList(ElementName.PART, null);
		for (final KElement e : vParts)
			vMap.add(e.getAttributeMap());
		if (vParts.isEmpty())
			vMap.add(new JDFAttributeMap());
	}

	/**
	 * @return
	 */
	public List<JDFAttributeMap> getPartMapList()
	{
		final JDFAttributeMapArray vMap = new JDFAttributeMapArray();
		addParts(vMap);
		return vMap;
	}

	/**
	 * 
	 * @param exactMap the map that must be present
	 * @return
	 */
	public boolean hasPartition(final JDFAttributeMap exactMap)
	{
		final VJDFAttributeMap partMapVector = getPartMapVector();
		if (JDFAttributeMap.isEmpty(exactMap))
		{
			return VJDFAttributeMap.isEmpty(partMapVector);
		}
		return partMapVector.contains(exactMap);
	}

	/**
	 * convenience to get the partmap of the first (and typically only) part element
	 *
	 * @return one non-null part element that may be empty
	 */
	public JDFAttributeMap getPartMap()
	{
		final JDFPart part = (JDFPart) theElement.getElement(ElementName.PART);
		return part == null ? new JDFAttributeMap() : part.getAttributeMap();
	}

	/**
	 * convenience to get the partmap of the first (and typically only) part element
	 *
	 * @return one non-null part element that may be empty
	 */
	public String getPartKey(final String key)
	{
		final VJDFAttributeMap vjdfAttributeMap = getPartMapVector();
		final JDFAttributeMap cm = vjdfAttributeMap == null ? null : vjdfAttributeMap.getCommonMap();
		return cm == null ? null : cm.get(key);
	}

	/**
	 *
	 * @param map the partmap to set the part element
	 */
	public void setPartMap(final JDFAttributeMap map)
	{
		if (getPartMapVector().size() > 0)
		{
			theElement.removeChildren(ElementName.PART, null, null);
		}
		final JDFPart part = (JDFPart) theElement.getCreateElement(ElementName.PART);
		part.setAttributes(map);
	}

	/**
	 *
	 * @param key
	 * @param value
	 * @return this - useful for lazy chaining
	 */
	public ResourceHelper ensurePart(final String key, final String value)
	{
		if (StringUtil.getNonEmpty(value) != null)
		{
			final VJDFAttributeMap partMapVector = getPartMapVector();
			final JDFAttributeMap newMap = new JDFAttributeMap(key, value);
			if (partMapVector.overlapsMap(newMap))
			{
				partMapVector.put(key, value);
			}
			else
			{
				partMapVector.add(newMap);
			}
			setPartMapVector(partMapVector);
		}
		return this;
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
	 * ensure a reference *FROM* src's explicit resource
	 *
	 * @param src
	 * @param key the reference key in src
	 *
	 */
	public void ensureReference(final ResourceHelper src, final String key)
	{
		ensureReference(src == null ? null : src.getResource(), key);
	}

	/**
	 *
	 * @param vPart the vector of partmaps to set the part element
	 */
	public void setPartMapVector(final VJDFAttributeMap vPart)
	{
		theElement.removeChildrenByClass(JDFPart.class);
		if (vPart != null && vPart.maxSize() > 0)
		{
			for (final JDFAttributeMap part : vPart)
			{
				appendPartMap(part);
			}
		}
	}

	/**
	 *
	 * @param vPart the vector of partmaps to set the part element
	 */
	public void appendPartMapVector(VJDFAttributeMap vPart)
	{
		final VJDFAttributeMap vexisting = getPartMapVector();
		if (vexisting != null)
		{
			vPart = vPart.clone();
			vPart.removeAll(vexisting);
		}
		if (vPart != null && vPart.size() > 0)
		{
			for (final JDFAttributeMap part : vPart)
			{
				appendPartMap(part);
			}
		}
	}

	/**
	 *
	 * @param part
	 */
	public void appendPartMap(final JDFAttributeMap part)
	{
		final KElement p = theElement.appendElement(ElementName.PART, null);
		p.setAttributes(part);
	}

	/**
	 * @param map
	 * @return
	 */
	public boolean matches(JDFAttributeMap map)
	{
		if (map == null)
			map = new JDFAttributeMap();
		return map.subMap(getPartMapVector());
	}

	/**
	 * @param map
	 * @return
	 */
	public boolean containsMap(JDFAttributeMap map)
	{
		if (map == null)
			map = new JDFAttributeMap();
		final VJDFAttributeMap vm = getPartMapVector();
		return vm.contains(map);
	}

	/**
	 * @param vmap
	 * @return
	 */
	public boolean matches(VJDFAttributeMap vmap)
	{
		if (vmap == null)
			vmap = new VJDFAttributeMap();
		return vmap.subMap(getPartMapVector());
	}

	/**
	 * @return the generic Parameter or Resource element
	 */
	public KElement getPartition()
	{
		return theElement;
	}

	/**
	 * @return the parent set
	 */
	public SetHelper getSet()
	{
		final KElement parent = theElement.getParentNode_KElement();
		if (parent != null && parent.getNodeName().equals(theElement.getNodeName() + "Set"))
			return new SetHelper(parent);
		return null;
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
	 *
	 * @param amount
	 * @param moreMap
	 * @param bGood
	 */
	public void setAmount(final double amount, final JDFAttributeMap moreMap, final boolean bGood)
	{
		setVAmount(amount, new VJDFAttributeMap(moreMap), bGood);
	}

	/**
	 *
	 * @param amount
	 * @param moreMap
	 * @param bGood
	 */
	public void setVAmount(final double amount, final VJDFAttributeMap moreMaps, final boolean bGood)
	{
		final JDFAmountPool ap = getCreateAmountPool();
		final VJDFAttributeMap vMap = moreMaps == null ? null : moreMaps.clone();
		if (vMap != null)
		{
			final VJDFAttributeMap partMap = getPartMapVector();
			final VString keys = partMap.getKeys();
			vMap.removeKeys(keys);
		}
		final JDFPartAmount pa0 = ap.getCreatePartAmount(vMap);
		pa0.setAttribute((bGood ? AttributeName.AMOUNT : AttributeName.WASTE), StringUtil.formatDouble(amount), null);
	}

	/**
	 * @return the actual detailed resource
	 */
	public KElement getResource()
	{
		final String name = getName();
		if (name != null)
		{
			return theElement.getElement(name);
		}
		else
		{
			KElement e = theElement.getFirstChildElement();
			while (e != null)
			{
				if (!(e instanceof JDFPart) && !(e instanceof JDFGeneralID) && !(e instanceof JDFAmountPool) && !(e instanceof JDFComment))
					return e;
				e = e.getNextSiblingElement();
			}
		}
		return null;
	}

	/**
	 *
	 * @return the name of this resource - calculated from ResourceSet/@Name
	 */
	public String getName()
	{
		final SetHelper set = getSet();
		final String name = set != null ? set.getAttribute(AttributeName.NAME) : null;
		return name;
	}

	/**
	 * @return
	 */
	public KElement getCreateResource()
	{
		final String name = getName();
		if (name != null)
			return theElement.getCreateElement(name);
		return null;
	}

	/**
	 * @see java.lang.Object#toString()
	 * @return
	 */
	@Override
	public String toString()
	{
		return "PartitionHelper: " + theElement;
	}

	/**
	 * generic cleanup stuff
	 */
	@Override
	public void cleanUp()
	{
		VJDFAttributeMap vParts = getPartMapVector();
		if (vParts != null && vParts.size() > 0)
		{
			vParts.unify();
			if (vParts.size() == 1 && vParts.get(0).isEmpty())
			{
				vParts = null;
			}
			setPartMapVector(vParts);
		}
		super.cleanUp();
	}

	/**
	 * @see org.cip4.jdflib.ifaces.IAmountPoolContainer#getAmountPool()
	 */
	@Override
	public JDFAmountPool getAmountPool()
	{
		return (JDFAmountPool) getRoot().getElement(ElementName.AMOUNTPOOL);
	}

	/**
	 *
	 * @param partMap
	 * @return
	 */
	public double getAmount(final JDFAttributeMap partMap, final boolean bGood)
	{
		final JDFAmountPool p = getAmountPool();
		final JDFPartAmount pa = p == null ? null : p.getPartAmount(partMap);
		return pa == null ? 0 : pa.getRealAttribute((bGood ? AttributeName.AMOUNT : AttributeName.WASTE), null, 0);
	}

	/**
	 *
	 * @param partMap
	 * @return
	 */
	public double getAmountSum(final boolean bGood)
	{
		return AmountPoolHelper.getAmountPoolSumDouble(this, (bGood ? AttributeName.AMOUNT : AttributeName.WASTE), null);
	}

	/**
	 * @see org.cip4.jdflib.ifaces.IAmountPoolContainer#getAttribute(java.lang.String, java.lang.String, java.lang.String)
	 */
	@Override
	public String getAttribute(final String attrib, final String nameSpaceURI, final String def)
	{
		return getRoot().getAttribute(attrib, nameSpaceURI, def);
	}

	/**
	 * @see org.cip4.jdflib.ifaces.IAmountPoolContainer#getRealAttribute(java.lang.String, java.lang.String, double)
	 */
	@Override
	public double getRealAttribute(final String attName, final String namespace, final double def)
	{
		return def;
	}

	/**
	 * @see org.cip4.jdflib.ifaces.IAmountPoolContainer#getLinkRoot()
	 */
	@Override
	public JDFResource getLinkRoot()
	{
		return (JDFResource) ((getResource() instanceof JDFResource) ? getResource() : null);
	}

	/**
	 * @see org.cip4.jdflib.ifaces.IAmountPoolContainer#setAttribute(java.lang.String, java.lang.String, java.lang.String)
	 */
	@Override
	public void setAttribute(final String attrib, final String value, final String nameSpaceURI)
	{
		getRoot().setAttribute(attrib, value, nameSpaceURI);
	}

	/**
	 *
	 */
	public void setResourceAttribute(final String attrib, final String value)
	{
		getCreateResource().setAttribute(attrib, value);
	}

	/**
	 *
	 */
	public void setResourceEnum(final String attrib, final ValuedEnum value)
	{
		setResourceAttribute(attrib, value == null ? null : value.getName());
	}

	/**
	 * @see org.cip4.jdflib.ifaces.IAmountPoolContainer#getCreateAmountPool()
	 */
	@Override
	public JDFAmountPool getCreateAmountPool()
	{
		return (JDFAmountPool) getRoot().getCreateElement(ElementName.AMOUNTPOOL);
	}

	/**
	 *
	 * @param brand
	 */
	public void setBrand(final String brand)
	{
		setAttribute(AttributeName.BRAND, brand);
	}

	/**
	 *
	 * @return the brand
	 */
	public String getBrand()
	{
		return getAttribute(AttributeName.BRAND);
	}

	/**
	 *
	 * @return the comment text
	 */
	@Override
	public String getComment(final int i)
	{
		final KElement root = getRoot();
		final KElement c = root == null ? null : root.getElement(ElementName.COMMENT, null, i);
		return c == null ? null : c.getText();
	}

	/**
	 *
	 * @param externalID
	 */
	@Override
	public void setExternalID(final String externalID)
	{
		super.setExternalID(externalID);
	}

	/**
	 * @return the descriptive name of the product
	 *
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
	 *
	 * @param available
	 */
	public void setStatus(final EnumResStatus status)
	{
		if (EnumResStatus.Available.equals(status) || EnumResStatus.Unavailable.equals(status))
			setAttribute(AttributeName.STATUS, status.getName());
		else
			setAttribute(AttributeName.STATUS, null);
	}

	/**
	 *
	 * @return the res status enum
	 */
	public EnumResStatus getStatus()
	{
		return EnumResStatus.getEnum(getAttribute(AttributeName.STATUS));
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

	/**
	 *
	 * @param string
	 * @return the attribute of the detailed resource, null if empty
	 */
	public String getResourceAttribute(final String string)
	{
		final KElement resource = getResource();
		return resource == null ? null : resource.getNonEmpty(string);
	}

	/**
	 * return a clone of this, placed just behind this
	 * 
	 * @return
	 */
	public ResourceHelper clonePartition()
	{
		if (theElement == null || theElement.getParentNode_KElement() == null)
		{
			return new ResourceHelper(null);
		}
		final KElement newElement = theElement.getParentNode_KElement().copyElement(theElement, theElement.getNextSiblingElement());
		return new ResourceHelper(newElement);
	}

}
