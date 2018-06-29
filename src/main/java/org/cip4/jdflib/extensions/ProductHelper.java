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

import java.util.Vector;

import org.cip4.jdflib.core.AttributeName;
import org.cip4.jdflib.core.ElementName;
import org.cip4.jdflib.core.KElement;
import org.cip4.jdflib.core.VElement;

/**
 * @author Rainer Prosi, Heidelberger Druckmaschinen *
 */
public class ProductHelper extends BaseXJDFHelper
{
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
		return new IntentHelper(intent);
	}

	/**
	 * @param name
	 * @return
	 */
	public IntentHelper getIntent(final String name)
	{
		final KElement intent = theElement.getChildWithAttribute(XJDFConstants.Intent, AttributeName.NAME, null, name, 0, true);
		return intent == null ? null : new IntentHelper(intent);
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
	public String getExternalID()
	{
		return theElement.getNonEmpty(XJDFConstants.ExternalID);
	}

	/**
	 * @return the productID of the product
	 */
	public String getProductType()
	{
		return theElement.getNonEmpty(AttributeName.PRODUCTTYPE);
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

	/**
	 * get the nth child of this
	 *
	 * @param nChild the index of the child
	 * @return
	 * @deprecated
	 */
	@Deprecated
	public ProductHelper getChild(final int nChild)
	{
		final KElement e = theElement.getElement("ChildProduct", null, nChild);
		if (e == null)
		{
			return null;
		}
		final String id = e.getAttribute("ChildRef", null, null);
		if (id == null)
		{
			return null;
		}
		final KElement list = theElement.getParentNode_KElement();
		final KElement kid = list.getChildWithAttribute("Product", "ID", null, id, 0, true);
		return kid == null ? null : new ProductHelper(kid);
	}

	/**
	 * get the nth child of this
	 *
	 * @param productType the productType attribute
	 * @param n the index of the child
	 * @return
	 * @deprecated
	 */
	@Deprecated
	public ProductHelper getChild(final String productType, int n)
	{
		final Vector<ProductHelper> v = getChildren();
		if (v == null || v.size() < n)
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
	 * @deprecated
	 */
	@Deprecated
	public Vector<ProductHelper> getChildren()
	{
		final VElement v = theElement.getChildElementVector("ChildProduct", null);
		if (v == null)
		{
			return null;
		}
		final Vector<ProductHelper> vRet = new Vector<>();
		final KElement list = theElement.getParentNode_KElement();
		for (final KElement e : v)
		{
			final String id = e.getAttribute("ChildRef", null, null);
			if (id == null)
			{
				continue;
			}
			final KElement kid = list.getChildWithAttribute("Product", "ID", null, id, 0, true);
			if (kid != null)
				vRet.add(new ProductHelper(kid));
		}
		return vRet;
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
			b = (list != null && list.getElement(XJDFConstants.Product, null, 0) == theElement && list.getChildWithAttribute(XJDFConstants.Product, rootProduct, null, "true", 0, true) == null);
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
