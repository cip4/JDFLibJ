/*
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
import org.cip4.jdflib.core.ElementName;
import org.cip4.jdflib.core.JDFConstants;
import org.cip4.jdflib.core.JDFResourceLink.EnumUsage;
import org.cip4.jdflib.core.KElement;
import org.cip4.jdflib.core.StringArray;
import org.cip4.jdflib.core.VElement;
import org.cip4.jdflib.datatypes.JDFAttributeMap;
import org.cip4.jdflib.util.ContainerUtil;
import org.cip4.jdflib.util.EnumUtil;
import org.cip4.jdflib.util.StringUtil;

/**
 * @author Rainer Prosi, Heidelberger Druckmaschinen *
 */
public class ProductHelper extends BaseXJDFHelper
{
	public enum eProductType
	{
		BackCover, BlankBox, BlankSheet, BlankWeb, Body, Book, BookBlock, BookCase, Booklet, Box, Brochure, BusinessCard, Carton, Cover, CoverBoard, CoverLetter, EndSheet, Envelope, FlatBox, FlatWork, FrontCover, Insert, Jacket, Label, Leaflet, Letter, Map, Media, Newspaper, Notebook, Pallet, Postcard, Poster, Proof, ResponseCard, Section, SelfMailer, Spine, SpineBoard, Stack, WrapAroundCover;

		public static eProductType getEnum(final String val)
		{
			final String val2 = StringUtil.replaceString(val, JDFConstants.BLANK, null);
			return EnumUtil.getJavaEnumIgnoreCase(eProductType.class, val2);
		}
	}

	/**
	 *
	 */
	public static final String PRODUCT = XJDFConstants.Product;
	/**
	 *
	 */
	public static final String PRODUCTLIST = XJDFConstants.ProductList;
	/**
	 *
	 */
	public static boolean partitionProducts = false;
	/**
	 * root products attribute name
	 */
	public final static String rootProduct = XJDFConstants.IsRoot;

	/**
	 * @param product
	 */
	public ProductHelper(final KElement product)
	{
		theElement = product;
		theElement.appendAnchor(null);
	}

	/**
	 * factory to create a helper from an element
	 *
	 * @param root the element to parse
	 * @return the helper
	 */
	public static ProductHelper getHelper(KElement e)
	{
		while (e != null && !PRODUCT.equals(e.getLocalName()))
			e = e.getParentNode_KElement();
		return e != null ? new ProductHelper(e) : null;
	}

	/**
	 *
	 */
	public void setRoot()
	{
		setRoot(true);
	}

	/**
	 *
	 * @param isRoot
	 */
	public void setRoot(final boolean isRoot)
	{
		theElement.setAttribute(rootProduct, isRoot, null);
	}

	/**
	 * @param name
	 * @return
	 */
	public IntentHelper getCreateIntent(final String name)
	{
		IntentHelper ih = getIntent(name);
		if (ih == null)
		{
			ih = appendIntent(name);
		}
		return ih;
	}

	/**
	 *
	 * @param name
	 * @param ih
	 * @return
	 */
	public IntentHelper appendIntent(final String name)
	{
		final KElement intent = theElement.appendElement(XJDFConstants.Intent);
		intent.appendElement(name);
		intent.setAttribute(AttributeName.NAME, name);
		if (ElementName.COLORINTENT.equals(name))
			return new ColorIntentHelper(intent);
		else
			return new IntentHelper(intent);
	}

	/**
	 * @param name
	 * @return
	 */
	public IntentHelper getIntent(final String name)
	{
		final KElement intent = theElement.getChildWithAttribute(XJDFConstants.Intent, AttributeName.NAME, null, name, 0, true);
		if (intent == null)
			return null;
		else if (ElementName.COLORINTENT.equals(name))
			return new ColorIntentHelper(intent);
		else
			return new IntentHelper(intent);
	}

	/**
	 * @return the vector of intent helpers
	 */
	public Vector<IntentHelper> getIntents()
	{
		final VElement v = theElement.getChildElementVector(XJDFConstants.Intent, null);

		final Vector<IntentHelper> v2 = new Vector<>();
		if (v != null)
		{
			for (final KElement e : v)
			{
				v2.add(new IntentHelper(e));
			}
		}
		return v2;
	}

	/**
	 * get an attribute from an explicit intent resource
	 *
	 * @param intentName
	 * @param attName
	 * @return
	 */
	public String getIntentAttribute(final String intentName, final String attName)
	{
		KElement intent = theElement.getChildWithAttribute(XJDFConstants.Intent, AttributeName.NAME, null, intentName, 0, true);
		intent = intent == null ? null : intent.getElement(intentName);
		return intent == null ? null : intent.getNonEmpty(attName);
	}

	/**
	 * get an attribute from an explicit intent resource
	 *
	 * @param intentName
	 * @param attName
	 * @return
	 */
	public SetHelper getNodeInfo()
	{
		return getProductSet(ElementName.NODEINFO);
	}

	/**
	 * 
	 * @param name
	 * @return
	 */
	public SetHelper getProductSet(final String name)
	{
		final XJDFHelper xjdfRoot = getXJDFRoot();
		if (xjdfRoot == null)
			return null;

		SetHelper sh = xjdfRoot.getSet(name, EnumUsage.Input, XJDFConstants.Product);
		if (sh == null)
		{
			sh = xjdfRoot.getSet(name, EnumUsage.Input, XJDFConstants.EndCustomer);
		}
		if (sh == null)
		{
			sh = xjdfRoot.getSet(name, EnumUsage.Input);
		}
		return sh;
	}

	/**
	 * 
	 * @param name the resource set name
	 * @return
	 */
	public ResourceHelper getCreateProductResource(final String name)
	{
		SetHelper sh = getProductSet(name);
		if (sh == null || (XJDFConstants.Product + ',' + XJDFConstants.EndCustomer).contains(sh.getProcessUsage()))
		{
			sh = getXJDFRoot().getCreateSet(name, EnumUsage.Input, XJDFConstants.Product);
		}

		final JDFAttributeMap map = new JDFAttributeMap();
		map.putNotNull(XJDFConstants.ExternalID, getExternalID());
		return sh == null ? null : sh.getCreateExactPartition(map, true);
	}

	/**
	 * 
	 * @param name the resource set name
	 * @return
	 */
	public ResourceHelper getProductResource(final String name)
	{
		final SetHelper sh = getProductSet(name);

		return sh == null ? null : sh.getPartition(XJDFConstants.ExternalID, getExternalID());
	}

	/**
	 * @return amount the amount to get
	 */
	public int getAmount()
	{
		return theElement.getIntAttribute(AttributeName.AMOUNT, null, -1);
	}

	/**
	 * @param amount the amount to set
	 */
	public void setAmount(final int amount)
	{
		theElement.setAttribute(AttributeName.AMOUNT, amount, null);
	}

	/**
	 * @return amount the max amount to get, defaults to the value of amount
	 */
	public int getMaxAmount()
	{
		final int a = theElement.getIntAttribute(AttributeName.MAXAMOUNT, null, -4242);
		return a == -4242 ? getAmount() : a;
	}

	/**
	 * @return the productID of the product
	 * @deprecated use getExternalID
	 */
	@Deprecated
	public String getProductID()
	{
		return theElement.getNonEmpty(AttributeName.PRODUCTID);
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
	 * @param id
	 */
	@Override
	public void setExternalID(final String id)
	{
		setAttribute(XJDFConstants.ExternalID, id);
	}

	/**
	 * @return the productID of the product
	 *
	 */
	@Override
	public String getDescriptiveName()
	{
		String descName = getAttribute(AttributeName.DESCRIPTIVENAME);
		if (StringUtil.isEmpty(descName))
		{
			final XJDFHelper xh = XJDFHelper.getHelper(theElement);
			if (xh != null)
				descName = xh.getDescriptiveName();
		}
		return descName;
	}

	/**
	 * @param id
	 */
	@Override
	public void setDescriptiveName(final String id)
	{
		setAttribute(AttributeName.DESCRIPTIVENAME, id);
	}

	/**
	 * @return the product typeof the product
	 */
	public String getProductType()
	{
		return theElement.getNonEmpty(AttributeName.PRODUCTTYPE);
	}

	/**
	 * @return the productID of the product
	 */
	public eProductType getProductTypeEnum()
	{
		return eProductType.getEnum(theElement.getNonEmpty(AttributeName.PRODUCTTYPE));
	}

	/**
	 *
	 * @param productType
	 */
	public void setProductType(final String productType)
	{
		theElement.setNonEmpty(AttributeName.PRODUCTTYPE, productType);
	}

	/**
	 *
	 * @param productType
	 */
	public void setProductType(final eProductType productType)
	{
		theElement.setNonEmpty(AttributeName.PRODUCTTYPE, productType.name());
	}

	/**
	 * @return overproduction in %
	 */
	public double getOverproduction()
	{
		final int a1 = getAmount();
		final int max = getMaxAmount();
		return a1 > 0 ? (100.0 * (max - a1)) / a1 : 0;
	}

	/**
	 * @param amount the amount to set
	 */
	public void setMaxAmount(final int amount)
	{
		theElement.setAttribute(AttributeName.MAXAMOUNT, amount, null);
	}

	/**
	 * @return amount the minamount to get, if not set default to Amount
	 */
	public int getMinAmount()
	{
		final int a = theElement.getIntAttribute(AttributeName.MINAMOUNT, null, -4242);
		return a == -4242 ? getAmount() : a;
	}

	/**
	 * @param amount the amount to set
	 */
	public void setMinAmount(final int amount)
	{
		theElement.setAttribute(AttributeName.MINAMOUNT, amount, null);
	}

	/**
	 * @param phCover
	 * @param amount
	 * @deprecated
	 */
	@Deprecated
	public void setChild(final ProductHelper phCover, final int amount)
	{
		if (phCover != null)
		{
			final IntentHelper childIntent = getCreateChildIntent();
			KElement e = theElement.getChildWithAttribute("ChildProduct", "Childref", null, phCover.theElement.getID(), 0, true);
			if (e == null)
			{
				e = theElement.appendElement("ChildProduct");
				e.copyAttribute("ChildRef", phCover.theElement, "ID", null, null);
				phCover.theElement.removeAttribute(rootProduct);
			}
			if (amount > 0)
			{
				e.setAttribute("Amount", amount, null);
			}
		}
	}

	/**
	 * @param phCover
	 *
	 */
	public void setChild(final ProductHelper phKid)
	{
		if (phKid != null)
		{
			final IntentHelper childIntent = getCreateChildIntent();
			final String id = phKid.ensureID();
			phKid.setRoot(false);
			childIntent.getCreateResource().appendAttribute(XJDFConstants.ChildRefs, id, null, null, true);
		}
	}

	/**
	 * the intenthelper that accepts kids
	 *
	 * @return
	 */
	IntentHelper getCreateChildIntent()
	{
		IntentHelper ret = getChildIntent();
		if (ret == null)
			ret = getCreateIntent(ElementName.BINDINGINTENT);
		return ret;
	}

	List<String> getChildRefs(final boolean bRecurse)
	{
		final IntentHelper kids = getChildIntent();

		if (kids == null)
			return null;
		final String refs = kids.getSpan(XJDFConstants.ChildRefs);
		final StringArray a = StringArray.getVString(refs, null);
		if (a == null || !bRecurse)
			return a;
		final Collection<KElement> v = theElement.getParentNode_KElement().getChildList(JDFConstants.PRODUCT, null);
		for (int i = 0; i < a.size(); i++)
		{
			final String ida = a.get(i);
			for (final KElement e : v)
			{
				if (ida.equals(e.getID()))
				{
					final List<String> grandkids = new ProductHelper(e).getChildRefs(false);
					if (grandkids != null)
					{
						a.appendUnique(grandkids);
					}
				}
			}
		}
		return a;
	}

	/**
	 *
	 * @return
	 */
	IntentHelper getChildIntent()
	{
		IntentHelper ret = getIntent(ElementName.BINDINGINTENT);
		if (ret == null)
			ret = getIntent(ElementName.INSERTINGINTENT);
		if (ret == null)
			ret = getIntent(XJDFConstants.AssemblingIntent);
		return ret;
	}

	/**
	 * get the nth child of this
	 *
	 * @param nChild the index of the child
	 * @return
	 *
	 */
	public ProductHelper getChild(final int nChild)
	{
		final List<String> childRefs = getChildRefs(false);
		final String kidRef = childRefs == null ? null : childRefs.get(nChild);
		if (kidRef != null)
		{
			final KElement list = theElement.getParentNode_KElement();
			final KElement kid = list.getChildWithAttribute(XJDFConstants.Product, AttributeName.ID, null, kidRef, 0, true);
			return kid == null ? null : new ProductHelper(kid);
		}
		return null;

	}

	/**
	 * get the nth child of this
	 *
	 * @param nChild the index of the child
	 * @return
	 *
	 */
	public ProductHelper getParent()
	{
		if (isRootProduct())
			return null;
		final String id = getID();
		if (StringUtil.isEmpty(id))
			return null;
		final List<KElement> list = theElement.getParentNode_KElement().getChildArray_KElement(XJDFConstants.Product, null, null, false, 0);
		if (ContainerUtil.isEmpty(list))
			return null;

		for (final KElement e : list)
		{
			final ProductHelper ph = new ProductHelper(e);
			final List<String> childRefs = ph.getChildRefs(false);
			if (childRefs != null && childRefs.contains(id))
				return ph;
		}

		return null;

	}

	/**
	 * get the nth child of this
	 *
	 * @param productType the productType attribute
	 * @param n the index of the child
	 * @return
	 *
	 */
	public ProductHelper getChild(final String productType, int n)
	{
		final List<ProductHelper> v = getChildren(false);
		if (ContainerUtil.isEmpty(v))
		{
			return null;
		}
		for (final ProductHelper p : v)
		{
			if (productType == null || productType.equals(p.getProduct().getAttribute(AttributeName.PRODUCTTYPE)))
			{
				if (n-- == 0)
					return p;
			}
		}
		return null;
	}

	/**
	 * get the vector of children of this
	 *
	 * @return
	 */
	public List<ProductHelper> getChildren(final boolean recurse)
	{
		final List<String> childRefs = getChildRefs(recurse);
		if (childRefs != null)
		{
			final KElement list = theElement.getParentNode_KElement();
			final List<ProductHelper> ret = new ArrayList<>();
			for (final String id : childRefs)
			{

				final KElement kid = list.getChildWithAttribute(XJDFConstants.Product, AttributeName.ID, null, id, 0, true);
				ret.add(new ProductHelper(kid));
			}
			return ret;
		}
		return null;
	}

	/**
	 * get the vector of children of this
	 *
	 * @return
	 * @deprecated use list / boolean
	 */
	@Deprecated
	public Vector<ProductHelper> getChildren()
	{
		final List<ProductHelper> kids = getChildren(false);
		final Vector<ProductHelper> vph = new Vector<>();
		if (kids != null)
			vph.addAll(kids);
		return vph;
	}

	/**
	 * @return
	 *
	 */
	public KElement getProduct()
	{
		return theElement;
	}

	/**
	 * @return the explicit value or the heuristic value if not set
	 */
	public boolean isRootProduct()
	{
		boolean b = theElement.getBoolAttribute(rootProduct, null, false);
		if (!b && theElement.getBoolAttribute(rootProduct, null, true))
		{
			final KElement list = theElement.getParentNode_KElement();
			b = (list != null && list.getElement(XJDFConstants.Product, null, 0) == theElement
					&& list.getChildWithAttribute(XJDFConstants.Product, rootProduct, null, "true", 0, true) == null);
		}
		return b;
	}

	/**
	 * reference the customerinfo specified by ph
	 *
	 * @param cuph
	 */
	public void setCustomerInfo(final ResourceHelper cuph)
	{
		if (cuph != null)
			getRoot().setAttribute(ElementName.CUSTOMERINFO + "Ref", cuph.getID());
	}

	/**
	 *
	 * @see org.cip4.jdflib.extensions.BaseXJDFHelper#cleanUp()
	 */
	@Override
	public void cleanUp()
	{
		// nop
	}

}
