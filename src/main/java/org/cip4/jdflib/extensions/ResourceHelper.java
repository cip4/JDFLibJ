/*
 * The CIP4 Software License, Version 1.0
 *
 *
 * Copyright (c) 2001-2016 The International Cooperation for the Integration of
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

import org.cip4.jdflib.core.AttributeName;
import org.cip4.jdflib.core.ElementName;
import org.cip4.jdflib.core.JDFComment;
import org.cip4.jdflib.core.JDFPartAmount;
import org.cip4.jdflib.core.KElement;
import org.cip4.jdflib.core.VElement;
import org.cip4.jdflib.datatypes.JDFAttributeMap;
import org.cip4.jdflib.datatypes.VJDFAttributeMap;
import org.cip4.jdflib.ifaces.IAmountPoolContainer;
import org.cip4.jdflib.pool.JDFAmountPool;
import org.cip4.jdflib.resource.JDFPart;
import org.cip4.jdflib.resource.JDFResource;
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
	public ResourceHelper(KElement partition)
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
		String setName = SetHelper.getSetName(parent);
		return toCheck.getLocalName().equals(setName);
	}

	/**
	 * @param toCheck
	 * @return true if toCheck is an asset (Resource, Parameter...)
	 */
	public static boolean isAsset(final KElement toCheck, String resName)
	{
		if (isAsset(toCheck))
		{
			if (StringUtil.getNonEmpty(resName) == null)
			{
				return true;
			}
			final KElement parent = toCheck.getParentNode_KElement();
			String name = parent.getAttribute("Name");
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
	public static ResourceHelper getHelper(KElement res)
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
		VJDFAttributeMap vMap = new VJDFAttributeMap();
		VElement vParts = theElement.getChildElementVector(ElementName.PART, null);
		if (vParts != null)
		{
			for (KElement e : vParts)
				vMap.add(e.getAttributeMap());
			if (vParts.size() == 0)
				vMap.add(new JDFAttributeMap());
		}
		return vMap;
	}

	/**
	 * convenience to get the partmap of the first (and typically only) part element
	 * @return one non-null part element that may be empty
	 */
	public JDFAttributeMap getPartMap()
	{
		JDFPart part = (JDFPart) theElement.getElement(ElementName.PART);
		return part == null ? new JDFAttributeMap() : part.getAttributeMap();
	}

	/**
	 *
	 * @param map the partmap to set the part element
	 */
	public void setPartMap(JDFAttributeMap map)
	{
		if (getPartMapVector().size() > 0)
		{
			theElement.removeChildren(ElementName.PART, null, null);
		}
		JDFPart part = (JDFPart) theElement.getCreateElement(ElementName.PART);
		part.setAttributes(map);
	}

	/**
	 *
	 * @param key
	 * @param value
	 */
	public void ensurePart(String key, String value)
	{
		if (StringUtil.getNonEmpty(value) != null)
		{
			VJDFAttributeMap partMapVector = getPartMapVector();
			partMapVector.put(key, value);
			setPartMapVector(partMapVector);
		}
	}

	/**
	 *
	 * @param vPart the vector of partmaps to set the part element
	 */
	public void setPartMapVector(VJDFAttributeMap vPart)
	{
		theElement.removeChildrenByClass(JDFPart.class);
		if (vPart != null)
		{
			for (JDFAttributeMap part : vPart)
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
		VJDFAttributeMap vexisting = getPartMapVector();
		if (vexisting != null)
		{
			vPart = vPart.clone();
			vPart.removeAll(vexisting);
		}
		if (vPart != null && vPart.size() > 0)
		{
			for (JDFAttributeMap part : vPart)
			{
				appendPartMap(part);
			}
		}
	}

	/**
	 *
	 * @param part
	 */
	public void appendPartMap(JDFAttributeMap part)
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
		VJDFAttributeMap vm = getPartMapVector();
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
		KElement parent = theElement.getParentNode_KElement();
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
	public void setAmount(double amount, JDFAttributeMap moreMap, boolean bGood)
	{
		final JDFAmountPool ap = getCreateAmountPool();
		final JDFPartAmount pa0 = ap.getCreatePartAmount(new VJDFAttributeMap(moreMap));
		pa0.setAttribute((bGood ? AttributeName.AMOUNT : AttributeName.WASTE), StringUtil.formatDouble(amount), null);
	}

	/**
	 * @return the actual detailed resource
	 */
	public KElement getResource()
	{
		KElement set = theElement.getParentNode_KElement();
		String name = set != null ? set.getAttribute("Name", null, null) : null;
		if (name != null)
		{
			return theElement.getElement(name);
		}
		else
		{
			KElement e = theElement.getFirstChildElement();
			while (e != null)
			{
				if (!(e instanceof JDFPart) && !(e instanceof JDFGeneralID) && !(e instanceof JDFComment))
					return e;
				e = e.getNextSiblingElement();
			}
		}
		return null;
	}

	/**
	 * @return
	 */
	public KElement getCreateResource()
	{
		KElement set = theElement.getParentNode_KElement();
		String name = set != null ? set.getAttribute("Name", null, null) : null;
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
		if (!theElement.hasAttribute(AttributeName.ID))
		{
			theElement.setID(theElement.generateDotID(AttributeName.ID, null));
		}
		super.cleanUp();
		theElement.moveElement(getResource(), null);
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
	 * @see org.cip4.jdflib.ifaces.IAmountPoolContainer#getAttribute(java.lang.String, java.lang.String, java.lang.String)
	 */
	@Override
	public String getAttribute(String attrib, String nameSpaceURI, String def)
	{
		return getRoot().getAttribute(attrib, nameSpaceURI, def);
	}

	/**
	 * @see org.cip4.jdflib.ifaces.IAmountPoolContainer#getRealAttribute(java.lang.String, java.lang.String, double)
	 */
	@Override
	public double getRealAttribute(String attName, String namespace, double def)
	{
		return def;
	}

	/**
	 * @see org.cip4.jdflib.ifaces.IAmountPoolContainer#hasAttribute(java.lang.String)
	 */
	@Override
	public boolean hasAttribute(String attName)
	{
		return StringUtil.getNonEmpty(getAttribute(attName, null, null)) != null;
	}

	/**
	 * @see org.cip4.jdflib.ifaces.IAmountPoolContainer#getLinkRoot()
	 */
	@Override
	public JDFResource getLinkRoot()
	{
		return (JDFResource) getResource();
	}

	/**
	 * @see org.cip4.jdflib.ifaces.IAmountPoolContainer#setAttribute(java.lang.String, java.lang.String, java.lang.String)
	 */
	@Override
	public void setAttribute(String attrib, String value, String nameSpaceURI)
	{
		getRoot().setAttribute(attrib, value, nameSpaceURI);
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
	public void setBrand(String brand)
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
	 * @param externalID
	 */
	public void setExternalID(String externalID)
	{
		setAttribute(XJDFConstants.ExternalID, externalID);
	}

	/**
	 *
	 * @return the externalID
	 */
	public String getExternalID()
	{
		return getAttribute(XJDFConstants.ExternalID);
	}

}
