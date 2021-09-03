/**
 * The CIP4 Software License, Version 1.0
 *
 * Copyright (c) 2001-2016 The International Cooperation for the Integration of Processes in Prepress, Press and Postpress (CIP4). All rights reserved.
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

import java.util.List;

import org.cip4.jdflib.core.AttributeName;
import org.cip4.jdflib.core.ElementName;
import org.cip4.jdflib.core.JDFConstants;
import org.cip4.jdflib.core.JDFElement;
import org.cip4.jdflib.core.JDFElement.EnumVersion;
import org.cip4.jdflib.core.JDFResourceLink;
import org.cip4.jdflib.core.JDFResourceLink.EnumUsage;
import org.cip4.jdflib.core.KElement;
import org.cip4.jdflib.core.VString;
import org.cip4.jdflib.datatypes.VJDFAttributeMap;
import org.cip4.jdflib.extensions.ResourceHelper;
import org.cip4.jdflib.extensions.XJDF20;
import org.cip4.jdflib.extensions.XJDFConstants;
import org.cip4.jdflib.extensions.xjdfwalker.jdftoxjdf.JDFToXJDF.EnumProcessPartition;
import org.cip4.jdflib.node.JDFNode;
import org.cip4.jdflib.resource.JDFResource;
import org.cip4.jdflib.resource.process.JDFComponent;
import org.cip4.jdflib.util.ContainerUtil;
import org.cip4.jdflib.util.EnumUtil;
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
		final JDFNode n = rl.getParentJDF();
		if (jdfToXJDF.getClassName(linkTarget) == null)
		{
			return null;
		}

		// we do not explicitly call out components for products
		if (n.isProduct())
		{
			if ((linkTarget instanceof JDFComponent) || (!jdfToXJDF.isWantProduct() && !matchesRootID(n)))
			{
				return null;
			}
			walkProductLink(xjdf, rl, linkTarget, n);
		}
		else
		{
			if (!jdfToXJDF.isSingleNode())
			{
				setProcess(rl);
			}
			setResource(rl, linkTarget, jdfToXJDF.newRoot);
		}
		return null;
	}

	/**
	 * @param xjdf
	 * @param rl
	 * @param linkTarget
	 * @param n
	 */
	void walkProductLink(final KElement xjdf, final JDFResourceLink rl, final JDFResource linkTarget, final JDFNode n)
	{
		if (jdfToXJDF.isProductResource(linkTarget))
		{
			final KElement product = getProductForElement(xjdf, rl);
			setResource(rl, linkTarget, product);
		}
		else
		{
			final List<KElement> v = setResource(rl, linkTarget, jdfToXJDF.newRoot);
			if (v != null)
			{
				final boolean isLegacy = EnumUtil.aLessThanB(jdfToXJDF.getNewVersion(), EnumVersion.Version_2_1);
				final String productID = isLegacy ? getXJDFProductID(n) : getXJDFExternalID(n);
				final String key = isLegacy ? XJDFConstants.ProductPart : XJDFConstants.Product;

				for (final KElement e : v)
				{
					final ResourceHelper resourceHelper = new ResourceHelper(e);
					resourceHelper.ensurePart(key, productID);
				}
			}
		}
	}

	static String getXJDFExternalID(final JDFNode node)
	{
		final JDFComponent c = (JDFComponent) node.getResource(ElementName.COMPONENT, EnumUsage.Output, 0);
		if (c != null)
		{
			final VString cid = c.getAssemblyIDs();
			if (ContainerUtil.size(cid) == 1)
			{
				return cid.get(0);
			}
			else
			{
				final String productID = c.getProductID();
				if (!StringUtil.isEmpty(productID))
					return productID;
			}
		}
		final String jpid = node.ensureJobPartID();
		return "P" + jpid.substring(1);
	}

	/**
	 * @param rl
	 */
	private void setProcess(final JDFResourceLink rl)
	{
		if (!jdfToXJDF.isWantProcessList() || rl == null)
			return;

		final KElement process = getProcess(rl);
		setLink(process, rl);
	}

	/**
	 * @param process
	 * @param rl
	 */
	private void setLink(final KElement process, final JDFResourceLink rl)
	{
		if (rl == null || process == null)
			return;
		final EnumUsage usage = rl.getUsage();
		final String usageName = usage == null ? null : usage.getName();
		if (usageName != null)
		{
			process.appendAttribute(usageName, rl.getrRef(), null, JDFConstants.BLANK, true);
		}
	}

	/**
	 * @param rl
	 * @return
	 */
	private KElement getProcess(final JDFResourceLink rl)
	{
		final JDFNode parent = rl.getParentJDF();
		if (parent == null || parent.isProduct())
		{
			// products are handled by productList
			return null;
		}
		if (parent.getElement(ElementName.JDF) != null)
		{
			return null;
		}
		final String jobPartID = getJobPartID(parent);
		if (jobPartID == null)
			return null;

		final KElement processList = jdfToXJDF.newRoot.getCreateElement(XJDFConstants.ProcessList, null, 0);
		KElement process = processList.getChildWithAttribute(XJDFConstants.Process, AttributeName.JOBPARTID, null, jobPartID, 0, true);
		if (process == null)
		{
			process = processList.appendElement(XJDFConstants.Process);
			process.setAttribute(AttributeName.JOBPARTID, jobPartID);

			if (parent.hasAttribute(AttributeName.TYPES))
			{
				process.copyAttribute(AttributeName.TYPES, parent);
			}
			else
			{
				process.copyAttribute(AttributeName.TYPES, parent, AttributeName.TYPE, null, null);
			}
			process.copyAttribute(AttributeName.CATEGORY, parent);
			process.copyAttribute(AttributeName.DESCRIPTIVENAME, parent);

			JDFNode grandparent = parent.getParentJDF();
			while (grandparent != null && !grandparent.isProduct())
			{
				grandparent = grandparent.getParentJDF();
			}

			if (grandparent != null)
			{
				process.setAttribute(XJDFConstants.Parent, getJobPartID(grandparent));
			}
		}
		return process;
	}

	/**
	 * @param parent
	 * @return
	 */
	private String getJobPartID(final JDFNode parent)
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
		return JDFResourceLink.isResourceLink(toCheck);
	}

	/**
	 * @see org.cip4.jdflib.extensions.xjdfwalker.jdftoxjdf.WalkJDFElement#setResource(org.cip4.jdflib.core.JDFElement, org.cip4.jdflib.resource.JDFResource,
	 *      org.cip4.jdflib.core.KElement)
	 */
	@Override
	protected List<KElement> setResource(final JDFElement rl, final JDFResource linkTarget, final KElement xRoot)
	{
		final List<KElement> newResources = super.setResource(rl, linkTarget, xRoot);
		if (XJDF20.rootName.equals(xRoot.getLocalName()))
		{
			setNodePartitions(rl, newResources);
		}

		return newResources;
	}

	private void setNodePartitions(final JDFElement rl, final List<KElement> newResources)
	{
		if (!ContainerUtil.isEmpty(newResources) && !jdfToXJDF.isWantProcessList() && !jdfToXJDF.isSingleNode())
		{
			final JDFNode parentNode = rl.getParentJDF();
			final JDFNode parentProduct = getParentProduct(parentNode);
			final VString xTypes = VString.getVString(jdfToXJDF.newRoot.getNonEmpty(AttributeName.TYPES), null);
			final VString jTypes = VString.getVString(parentNode.getTypesString(), null);

			for (final KElement newResource : newResources)
			{
				final ResourceHelper ph = new ResourceHelper(newResource);
				final VJDFAttributeMap partMaps = ph.getPartMapVector();
				boolean bChange = false;
				if (parentProduct != null)
				{
					final boolean isLegacy = EnumUtil.aLessThanB(jdfToXJDF.getNewVersion(), EnumVersion.Version_2_1);
					final String productID = isLegacy ? getXJDFProductID(parentProduct) : getXJDFExternalID(parentProduct);
					final String key = isLegacy ? XJDFConstants.ProductPart : XJDFConstants.Product;
					partMaps.put(key, productID);
					bChange = true;
				}
				if (EnumProcessPartition.processTypes.equals(jdfToXJDF.getProcessPart()))
				{
					if (parentNode != parentProduct)
					{
						if (xTypes != null && jTypes != null)
						{
							final VString cpi = new VString();
							for (final String jtyp : jTypes)
							{
								final int i = xTypes.indexOf(jtyp);
								if (i >= 0)
								{
									cpi.add("" + i);
								}
							}
							if (!cpi.isEmpty())
							{
								final KElement set = newResource.getParentNode_KElement();
								set.setAttribute(AttributeName.COMBINEDPROCESSINDEX, StringUtil.setvString(cpi));
							}
						}
					}
				}
				else if (EnumProcessPartition.jobPartID.equals(jdfToXJDF.getProcessPart()))
				{
					if (parentNode != parentProduct)
					{
						final String jobPartID = StringUtil.getNonEmpty(parentNode.getJobPartID(false));
						if (jobPartID != null)
						{
							partMaps.put(XJDFConstants.ProductPart, jobPartID);
							bChange = true;
						}
					}
				}

				if (bChange)
				{
					ph.setPartMapVector(partMaps);
				}
			}
		}
	}

	protected JDFNode getParentProduct(final JDFNode parentNode)
	{
		JDFNode parentProduct = parentNode.getParentProduct();
		if (parentProduct != null && parentProduct.isJDFRoot())
		{
			parentProduct = null;
		}
		return parentProduct;
	}
}