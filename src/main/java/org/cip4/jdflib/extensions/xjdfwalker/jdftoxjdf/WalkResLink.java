/**
 * The CIP4 Software License, Version 1.0
 *
 * Copyright (c) 2001-2015 The International Cooperation for the Integration of 
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
import org.cip4.jdflib.core.JDFConstants;
import org.cip4.jdflib.core.JDFPartAmount;
import org.cip4.jdflib.core.JDFResourceLink;
import org.cip4.jdflib.core.JDFResourceLink.EnumUsage;
import org.cip4.jdflib.core.KElement;
import org.cip4.jdflib.core.VElement;
import org.cip4.jdflib.extensions.ProductHelper;
import org.cip4.jdflib.node.JDFNode;
import org.cip4.jdflib.node.JDFNode.EnumType;
import org.cip4.jdflib.resource.JDFResource;
import org.cip4.jdflib.resource.JDFResource.EnumResourceClass;
import org.cip4.jdflib.resource.process.JDFComponent;
import org.cip4.jdflib.util.StringUtil;

/**
 * @author Rainer Prosi, Heidelberger Druckmaschinen walker for the various resource sets
 */
public class WalkResLink extends WalkJDFElement
{

	/**
	 * 
	 */
	public WalkResLink()
	{
		super();
	}

	/**
	 * @param jdf
	 * @param xjdf
	 * @return the created resource in this case just remove the pool
	 */
	@Override
	public KElement walk(final KElement jdf, final KElement xjdf)
	{
		final JDFResourceLink rl = (JDFResourceLink) jdf;
		final JDFResource linkTarget = rl.getLinkRoot();
		JDFNode n = rl.getParentJDF();
		// we do not explicitly call out components for products
		if (linkTarget == null)
		{
			return null;
		}

		if (EnumType.Product.equals(n.getEnumType()))
		{
			if (linkTarget instanceof JDFComponent || (!jdfToXJDF.wantProduct && !matchesRootID(n)))
			{
				return null;
			}
			if (isProductResource(linkTarget))
			{
				KElement product = xjdf.getCreateElement(ProductHelper.PRODUCTLIST).getCreateElement(ProductHelper.PRODUCT, null, -1);
				setResource(rl, linkTarget, product);
			}
			else
			{
				setResource(rl, linkTarget, xjdf);
			}
		}
		else
		{
			if (!jdfToXJDF.isSingleNode())
			{
				setProcess(rl);
			}
			setResource(rl, linkTarget, xjdf);
		}
		return null;
	}

	/**
	 * 
	 * @param linkTarget
	 * @return
	 */
	private boolean isProductResource(final JDFResource linkTarget)
	{
		return EnumResourceClass.Intent.equals(linkTarget.getResourceClass());
	}

	/**
	 * 
	 *  
	 * @param rl
	 * @param linkTarget
	 * @param xjdf
	 * @return 
	 */
	VElement setResource(final JDFResourceLink rl, final JDFResource linkTarget, final KElement xjdf)
	{
		return jdfToXJDF.setResource(rl, linkTarget, xjdf);
	}

	/**
	 * @param rl
	 */
	private void setProcess(JDFResourceLink rl)
	{
		if (rl == null)
			return;

		KElement process = getProcess(rl);
		setLink(process, rl);
	}

	/**
	 * @param process
	 * @param rl
	 */
	private void setLink(KElement process, JDFResourceLink rl)
	{
		if (rl == null || process == null)
			return;
		EnumUsage usage = rl.getUsage();
		if (usage == null)
			return;
		String attName = usage.getName();
		if (attName != null)
		{
			process.appendAttribute(attName, rl.getrRef(), null, " ", true);
		}
	}

	/**
	 * @param rl
	 * @return 
	 */
	private KElement getProcess(JDFResourceLink rl)
	{
		JDFNode parent = rl.getParentJDF();
		if (parent == null || JDFConstants.PRODUCT.equals(parent.getType()))
		{
			// products are handled by productList
			return null;
		}
		String jobPartID = getJobPartID(parent);
		if (jobPartID == null)
			return null;

		KElement processList = jdfToXJDF.newRoot.getCreateElement("ProcessList", null, 0);
		KElement process = processList.getChildWithAttribute("Process", AttributeName.JOBPARTID, null, jobPartID, 0, true);
		if (process == null)
		{
			process = processList.appendElement("Process");
			process.setAttribute(AttributeName.JOBPARTID, jobPartID);
			JDFNode grandparent = parent.getParentJDF();

			if (parent.hasAttribute(AttributeName.TYPES))
			{
				process.copyAttribute("Types", parent);
			}
			else
			{
				process.copyAttribute("Types", parent, "Type", null, null);
			}

			if (grandparent != null)
			{
				process.setAttribute("Parent", getJobPartID(grandparent));
			}
		}
		return process;
	}

	/**
	 * @param parent
	 * @return
	 */
	private String getJobPartID(JDFNode parent)
	{
		String jobPartID = StringUtil.getNonEmpty(parent.getJobPartID(false));
		if (jobPartID == null)
			jobPartID = StringUtil.getNonEmpty(parent.getID());
		return jobPartID;
	}

	/**
	 * @see org.cip4.jdflib.elementwalker.BaseWalker#matches(org.cip4.jdflib.core.KElement)
	 * @param toCheck
	 * @return true if it matches
	 */
	@Override
	public boolean matches(final KElement toCheck)
	{
		return toCheck instanceof JDFResourceLink && !(toCheck instanceof JDFPartAmount);
	}
}