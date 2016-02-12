/**
 * The CIP4 Software License, Version 1.0
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
package org.cip4.jdflib.extensions.xjdfwalker.xjdftojdf;

import org.cip4.jdflib.auto.JDFAutoComponent.EnumComponentType;
import org.cip4.jdflib.core.AttributeInfo;
import org.cip4.jdflib.core.AttributeName;
import org.cip4.jdflib.core.ElementName;
import org.cip4.jdflib.core.JDFResourceLink;
import org.cip4.jdflib.core.JDFResourceLink.EnumUsage;
import org.cip4.jdflib.core.KElement;
import org.cip4.jdflib.core.VString;
import org.cip4.jdflib.extensions.ProductHelper;
import org.cip4.jdflib.extensions.xjdfwalker.IDFinder;
import org.cip4.jdflib.node.JDFNode;
import org.cip4.jdflib.node.JDFNode.EnumType;
import org.cip4.jdflib.resource.intent.JDFDeliveryIntent;
import org.cip4.jdflib.resource.intent.JDFDropItemIntent;
import org.cip4.jdflib.resource.process.JDFComponent;
import org.cip4.jdflib.util.StringUtil;

/**
 * @author Rainer Prosi, Heidelberger Druckmaschinen walker for the various resource sets
 */
public class WalkProduct extends WalkXElement
{
	/**
	 * 
	 */
	public WalkProduct()
	{
		super();
	}

	/**
	 * @param xjdfProduct
	 * @return the created resource
	 */
	@Override
	public KElement walk(final KElement xjdfProduct, KElement trackElem)
	{
		JDFNode theNode = (JDFNode) trackElem;
		if (ProductHelper.PRODUCT.equals(theNode.getType()))
		{
			JDFNode tmp = theNode.getRoot().getChildJDFNode(xjdfProduct.getAttribute(AttributeName.ID), false);
			if (tmp != null)
			{
				theNode = tmp;
			}
			else if (!xjdfToJDFImpl.firstproductInList)
			{
				theNode = theNode.addProduct();
			}
			else
			{
				//nop
			}
		}
		else
		{
			theNode = xjdfToJDFImpl.createProductRoot();
		}
		xjdfToJDFImpl.firstproductInList = false;
		copyToNode(xjdfProduct, theNode);
		JDFComponent c = fixComponent(theNode, xjdfProduct);

		updateDeliveryIntent(xjdfProduct, theNode, c);
		return theNode;
	}

	/**
	 * merge minamount and maxamount into deliveryintent
	 * 
	 * @param xjdfProduct
	 * @param theNode
	 * @param c
	 */
	private void updateDeliveryIntent(final KElement xjdfProduct, JDFNode theNode, JDFComponent c)
	{
		JDFResourceLink rlc = theNode.getLink(c, EnumUsage.Output);
		double overage = rlc.getMaxAmount();
		double underage = rlc.getMinAmount();
		double amount = StringUtil.parseDouble(xjdfProduct.getAttribute(AttributeName.AMOUNT, null, null), -1000.);
		if (amount > 0 && (overage > 0 || underage > 0))
		{
			JDFDeliveryIntent di = (JDFDeliveryIntent) theNode.getCreateResource(ElementName.DELIVERYINTENT, EnumUsage.Input, 0);
			if (overage > 0)
			{
				di.appendOverage().setActual(100.0 * (overage - amount) / amount);
			}
			if (underage > 0)
			{
				di.appendUnderage().setActual(100.0 * (amount - underage) / amount);
			}
			final JDFDropItemIntent dropItemIntent = di.appendDropIntent().appendDropItemIntent();
			dropItemIntent.setAmount((int) amount);
			dropItemIntent.refElement(c);
		}
	}

	/**
	 * 
	 *  
	 * @param e
	 * @param theNode
	 */
	private void copyToNode(final KElement e, JDFNode theNode)
	{
		VString ignore = new VString("IsRoot", null);
		theNode.setAttributes(e, ignore);
	}

	/**
	 * @param theNode
	 * @param xjdfProduct
	 */
	private JDFComponent fixComponent(final JDFNode theNode, final KElement xjdfProduct)
	{
		JDFComponent c = (JDFComponent) theNode.getResource(ElementName.COMPONENT, EnumUsage.Output, 0);
		if (c == null)
		{
			c = (JDFComponent) theNode.addResource(ElementName.COMPONENT, EnumUsage.Output);
			xjdfToJDFImpl.idMap.put(c.getID(), new IDFinder().new IDPart(c.getID(), null));
			boolean isRootProduct = new ProductHelper(xjdfProduct).isRootProduct();
			final EnumComponentType partialFinal = isRootProduct ? EnumComponentType.FinalProduct : EnumComponentType.PartialProduct;
			c.setComponentType(partialFinal, null);
			if (!isRootProduct)
			{
				JDFNode parent = theNode.getParentJDF();
				if (parent != null && EnumType.Product.equals(parent.getEnumType()))
				{
					parent.ensureLink(c, EnumUsage.Input, null);
				}
			}
		}
		AttributeInfo info = c.getAttributeInfo();
		VString cKnown = info.knownAttribs();
		AttributeInfo infoNode = theNode.getAttributeInfo();
		cKnown.removeAll(infoNode.knownAttribs());
		cKnown.remove(AttributeName.AMOUNT);
		cKnown.remove(AttributeName.ACTUALAMOUNT);
		for (String known : cKnown)
		{
			if (theNode.hasAttribute(known))
			{
				c.moveAttribute(known, theNode);
			}
		}
		final JDFResourceLink rl = theNode.getLink(c, EnumUsage.Output);
		if (rl != null)
		{
			rl.moveAttribute(AttributeName.AMOUNT, theNode);
			rl.moveAttribute(AttributeName.MINAMOUNT, theNode);
			rl.moveAttribute(AttributeName.MAXAMOUNT, theNode);
		}
		return c;
	}

	/**
	 * @see org.cip4.jdflib.elementwalker.BaseWalker#matches(org.cip4.jdflib.core.KElement)
	 * @param toCheck
	 * @return true if it matches
	 */
	@Override
	public boolean matches(final KElement toCheck)
	{
		return super.matches(toCheck) && (toCheck.getLocalName().equals("Product"));
	}

}