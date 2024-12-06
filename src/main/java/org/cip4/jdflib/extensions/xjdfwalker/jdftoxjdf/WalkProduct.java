/**
 * The CIP4 Software License, Version 1.0
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
package org.cip4.jdflib.extensions.xjdfwalker.jdftoxjdf;

import org.cip4.jdflib.auto.JDFAutoComponent.EnumComponentType;
import org.cip4.jdflib.core.AttributeName;
import org.cip4.jdflib.core.ElementName;
import org.cip4.jdflib.core.JDFResourceLink;
import org.cip4.jdflib.core.JDFResourceLink.EnumUsage;
import org.cip4.jdflib.core.KElement;
import org.cip4.jdflib.core.StringArray;
import org.cip4.jdflib.core.VElement;
import org.cip4.jdflib.core.VString;
import org.cip4.jdflib.datatypes.JDFAttributeMap;
import org.cip4.jdflib.extensions.ProductHelper;
import org.cip4.jdflib.extensions.XJDFConstants;
import org.cip4.jdflib.node.JDFNode;
import org.cip4.jdflib.node.JDFNode.EnumType;
import org.cip4.jdflib.resource.process.JDFComponent;

/**
 * @author Rainer Prosi, Heidelberger Druckmaschinen walker for the various resource sets
 */
public class WalkProduct extends WalkJDF
{

	/**
	 *
	 */
	public WalkProduct()
	{
		super();
	}

	/**
	 * @param jdf
	 * @return the created resource
	 */
	@Override
	public KElement walk(final KElement jdf, final KElement xjdf)
	{
		final JDFNode node = (JDFNode) jdf;
		final boolean matchesID = matchesRootID(node);
		if (matchesID)
		{
			prepareRoot(node, xjdf);
			jdfToXJDF.first.add(jdf.getID());
		}
		walkProduct(jdf, xjdf);
		return xjdf;
	}

	/**
	 * @see org.cip4.jdflib.elementwalker.BaseWalker#matches(org.cip4.jdflib.core.KElement)
	 * @param toCheck
	 * @return true if it matches
	 */
	@Override
	public boolean matches(final KElement toCheck)
	{
		return super.matches(toCheck) && jdfToXJDF.isWantProduct() && EnumType.Product.equals(((JDFNode) toCheck).getEnumType());
	}

	@Override
	void prepareRootMap(final JDFNode node, final KElement newRootP)
	{
		final JDFAttributeMap map = node.getAttributeMap();
		super.updateAttributes(map);
		newRootP.setAttributes(map);
		super.prepareRootMap(node, newRootP);
	}

	/**
	 * @param newRootP
	 */
	@Override
	protected void removeUnusedElements(final KElement newRootP)
	{
		// status is set only in the NodeInfo
		newRootP.removeAttribute(AttributeName.STATUS);
		newRootP.removeAttribute(AttributeName.STATUSDETAILS);
		newRootP.removeAttribute(AttributeName.ACTIVATION);
		newRootP.removeAttribute(AttributeName.TEMPLATE);
		super.removeUnusedElements(newRootP);
	}

	/**
	 * @param node
	 * @param prod
	 */
	private void calcChildren(final JDFNode node, final KElement prod)
	{
		final VString kids = new VString();
		final VElement vComp = node.getPredecessors(true, true);
		if (vComp != null)
		{
			for (final KElement e : vComp)
			{
				final JDFNode nPre = (JDFNode) e;
				if (EnumType.Product.equals(nPre.getEnumType()))
				{
					kids.add(getXJDFProductID(nPre));
				}
			}
		}

		for (final String kid : kids)
		{
			prod.appendAttribute(XJDFConstants.ChildRefs, kid, null, null, false);
		}
	}

	final private static StringArray copyKeep = new StringArray(
			new String[] { XJDFConstants.ExternalID, AttributeName.PRODUCTTYPE, AttributeName.PARTVERSION, AttributeName.PRODUCTTYPEDETAILS, AttributeName.DESCRIPTIVENAME });

	/**
	 * @param node
	 * @param prod
	 */
	boolean readComponent(final JDFNode node, final KElement prod)
	{
		final JDFResourceLink cOutLink = node.getLink(0, ElementName.COMPONENT, new JDFAttributeMap(AttributeName.USAGE, EnumUsage.Output), null);
		if (cOutLink == null)
			return false;
		final int amount = (int) cOutLink.getAmountPoolSumDouble(AttributeName.AMOUNT, null);
		if (amount > 0)
		{
			prod.setAttribute(AttributeName.AMOUNT, amount, null);
		}
		prod.renameAttribute("AmountGood", "Amount", null, null);
		prod.removeAttribute("AmountWaste");

		final JDFComponent component = (JDFComponent) cOutLink.getTarget();
		if (component != null)
		{
			final JDFAttributeMap map = component.getAttributeMap();
			map.removeEmpty();

			map.renameKey(AttributeName.PRODUCTID, XJDFConstants.ExternalID);
			map.reduceMap(copyKeep);
			prod.setAttributes(map);
			if (component.isComponentType(EnumComponentType.FinalProduct))
			{
				new ProductHelper(prod).setRoot();
			}
			jdfToXJDF.putComponentProduct(component.getID(), prod.getID());
		}
		return true;
	}

	/**
	 * @param jdf
	 * @param xjdf
	 * @return the created resource
	 */
	KElement walkProduct(final KElement jdf, final KElement xjdf)
	{
		final JDFNode node = (JDFNode) jdf;

		final KElement prod = getProductForElement(xjdf, node);
		if (node.isJDFRoot())
		{
			new ProductHelper(prod).setRoot();
		}

		if (readComponent(node, prod))
		{
			final JDFAttributeMap map = jdf.getAttributeMap();
			if (prod.getNonEmpty(XJDFConstants.ExternalID) == null)
			{
				final String xid = WalkResLink.getXJDFExternalID(node);
				map.put(XJDFConstants.ExternalID, xid);
			}
			updateAttributes(map);
			prod.setAttributes(map);

			calcChildren(node, prod);

			return prod;
		}
		else
		{
			prod.deleteNode();
			return xjdf;
		}
	}

	@Override
	protected void updateAttributes(final JDFAttributeMap map)
	{
		map.remove(AttributeName.ID);
		map.remove(AttributeName.CATEGORY);
		map.remove(AttributeName.TYPE);
		map.remove(AttributeName.ACTIVATION);
		map.remove(AttributeName.VERSION);
		map.remove(AttributeName.MAXVERSION);
		map.remove(AttributeName.ICSVERSIONS);
		map.remove(AttributeName.PROJECTID);
		map.remove(AttributeName.STATUS);
		map.remove(AttributeName.STATUSDETAILS);
		map.remove(AttributeName.XMLNS);
		map.remove(AttributeName.XSITYPE);
		map.remove(AttributeName.JOBID);

		map.remove("xmlns:xsi");
		map.remove(AttributeName.JOBPARTID);
		super.updateAttributes(map);
	}
}