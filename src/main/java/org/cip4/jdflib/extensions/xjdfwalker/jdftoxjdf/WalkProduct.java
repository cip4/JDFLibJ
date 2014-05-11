/**
 * The CIP4 Software License, Version 1.0
 *
 * Copyright (c) 2001-2014 The International Cooperation for the Integration of 
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
package org.cip4.jdflib.extensions.xjdfwalker.jdftoxjdf;

import org.cip4.jdflib.core.AttributeName;
import org.cip4.jdflib.core.JDFResourceLink;
import org.cip4.jdflib.core.KElement;
import org.cip4.jdflib.core.VElement;
import org.cip4.jdflib.core.VString;
import org.cip4.jdflib.datatypes.JDFAttributeMap;
import org.cip4.jdflib.node.JDFNode;
import org.cip4.jdflib.node.JDFNode.EnumType;
import org.cip4.jdflib.resource.process.JDFComponent;

/**
 * @author Rainer Prosi, Heidelberger Druckmaschinen walker for the various resource sets
 */
public class WalkProduct extends WalkJDFElement
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
	 * @param xjdf
	 * @return the created resource
	 */
	@Override
	public KElement walk(final KElement jdf, final KElement xjdf)
	{
		final KElement pList = "Product".equals(xjdf.getLocalName()) ? xjdf.getParentNode_KElement() : xjdf;
		final JDFNode node = (JDFNode) jdf;
		if (!EnumType.Product.equals(node.getEnumType()))
		{
			return null;
		}
		final KElement prod = pList.appendElement("Product");
		prod.setAttributes(jdf);
		prod.removeAttribute(AttributeName.TYPE);
		prod.removeAttribute(AttributeName.ACTIVATION);
		prod.removeAttribute(AttributeName.VERSION);
		prod.removeAttribute(AttributeName.MAXVERSION);
		prod.removeAttribute(AttributeName.ICSVERSIONS);
		prod.removeAttribute(AttributeName.STATUS);
		prod.removeAttribute(AttributeName.STATUSDETAILS);
		prod.removeAttribute(AttributeName.XMLNS);
		prod.removeAttribute(AttributeName.XSITYPE);
		prod.removeAttribute(AttributeName.JOBID);
		prod.renameAttribute(AttributeName.JOBPARTID, AttributeName.PRODUCTID, null, null);
		prod.removeAttribute("xmlns:xsi");
		calcChildren(node, prod);
		readComponent(node, prod);
		return prod;
	}

	/**
	 * @param node
	 * @param prod
	 */
	private void readComponent(final JDFNode node, final KElement prod)
	{
		final JDFResourceLink cOut = node.getLink(0, "ComponentLink", new JDFAttributeMap("Usage", "Output"), null);
		if (cOut == null)
			return;
		this.jdfToXJDF.setAmountPool(cOut, prod, null);
		prod.renameAttribute("AmountGood", "Amount", null, null);
		prod.removeAttribute("AmountWaste");

		JDFComponent component = (JDFComponent) cOut.getTarget();
		prod.copyAttribute(AttributeName.PRODUCTTYPE, component);
		prod.copyAttribute(AttributeName.PRODUCTTYPEDETAILS, component);
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
			final int siz = vComp.size();
			for (int i = 0; i < siz; i++)
			{
				final JDFNode nPre = (JDFNode) vComp.get(i);
				if (EnumType.Product.equals(nPre.getEnumType()))
				{
					kids.add(nPre.getID());
				}
			}
		}

		if (kids.size() > 0)
		{
			for (int i = 0; i < kids.size(); i++)
			{
				final KElement sub = prod.appendElement("ChildProduct");
				sub.setAttribute("ChildRef", kids.get(i), null);
				// TODO add processusage from input / output resources
			}
		}
		else
		{
			node.setAttribute("RootProduct", true, null);
		}
	}

	/**
	 * @see org.cip4.jdflib.elementwalker.BaseWalker#matches(org.cip4.jdflib.core.KElement)
	 * @param toCheck
	 * @return true if it matches
	 */
	@Override
	public boolean matches(final KElement toCheck)
	{
		return this.jdfToXJDF.walkingProduct && toCheck instanceof JDFNode;
	}
}