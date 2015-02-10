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
package org.cip4.jdflib.extensions.xjdfwalker.xjdftojdf;

import java.util.Vector;

import org.cip4.jdflib.core.ElementName;
import org.cip4.jdflib.core.JDFElement;
import org.cip4.jdflib.core.JDFRefElement;
import org.cip4.jdflib.core.KElement;
import org.cip4.jdflib.core.VElement;
import org.cip4.jdflib.datatypes.JDFAttributeMap;
import org.cip4.jdflib.elementwalker.EnsureNSUri;
import org.cip4.jdflib.elementwalker.RemoveEmpty;
import org.cip4.jdflib.elementwalker.UnLinkFinder;
import org.cip4.jdflib.node.JDFNode;
import org.cip4.jdflib.pool.JDFResourcePool;
import org.cip4.jdflib.resource.JDFResource;
import org.cip4.jdflib.resource.JDFResource.EnumResStatus;
import org.cip4.jdflib.resource.process.JDFDependencies;
import org.cip4.jdflib.resource.process.JDFLayoutElement;
import org.cip4.jdflib.resource.process.JDFRunList;
import org.cip4.jdflib.util.StringUtil;

/**
 * 
 *  
 * @author rainer prosi
 * @date Feb 26, 2013
 */
class PostConverter
{
	/**
	 * 
	 */
	private final XJDFToJDFImpl xjdfToJDFImpl;
	private final JDFNode theNode;

	/**
	 * @param xjdfToJDFImpl 
	 * @param theNode
	 */
	public PostConverter(XJDFToJDFImpl xjdfToJDFImpl, JDFNode theNode)
	{
		super();
		this.xjdfToJDFImpl = xjdfToJDFImpl;
		this.theNode = theNode;
	}

	/**
	 * 
	 * 
	 */
	void postConvert()
	{
		JDFNode root;
		root = theNode.getJDFRoot();
		String type = StringUtil.getNonEmpty(root.getType());
		if (type == null || "Product".equals(type))
		{
			xjdfToJDFImpl.mergeProductLinks(theNode, root);
		}
		cleanResources();
		fixDependencies(root);
		new UnLinkFinder().eraseUnlinked(root);
		this.xjdfToJDFImpl.firstConvert = false;
		EnsureNSUri fixNS = new EnsureNSUri();
		fixNS.addNS(null, JDFElement.getSchemaURL());
		fixNS.walk(root);
		RemoveEmpty re = new RemoveEmpty();
		re.removEmpty(root);
	}

	/**
	 * @param theNode
	 */
	private void cleanResources()
	{
		VElement vRes = collectResources();
		if (vRes != null)
		{
			for (KElement rr : vRes)
			{
				cleanResource(rr);
			}
		}
	}

	/**
	 * 
	 *  
	 * @param theNode
	 * @return
	 */
	private VElement collectResources()
	{
		JDFNode n = theNode;
		VElement vRes = new VElement();
		while (n != null)
		{
			JDFResourcePool rp = n.getResourcePool();
			final VElement v = rp == null ? null : rp.getPoolChildren(null, null, null);
			vRes.addAll(v);
			n = n.getParentJDF();
		}
		return vRes;
	}

	/**
	 *  
	 * @param theNode
	 * @param rr
	 */
	private void cleanResource(KElement rr)
	{
		final JDFResource r = (JDFResource) rr;
		if (r != null)
		{
			final EnumResStatus s = r.getStatusFromLeaves(false);
			if (s != null)
			{
				r.setResStatus(s, false);
			}
			if (ElementName.COLORPOOL.equals(r.getLocalName()))
			{
				cleanColorPool(r);
			}
		}
	}

	/**
	 * 
	 *  
	 * @param theNode
	 * @param r
	 */
	private void cleanColorPool(final JDFResource r)
	{
		String id = r.getID();
		if (StringUtil.getNonEmpty(id) != null)
		{
			VElement v = theNode.getRoot().getChildrenByTagName_KElement(null, null, new JDFAttributeMap("rRef", id), false, false, 0);
			if (v != null)
			{
				for (KElement e : v)
				{
					String name = e.getLocalName();
					if ("ColorRef".equals(name))
					{
						name = StringUtil.leftStr(e.getNodeName(), -3) + "PoolRef";
						e.renameElement(name, null);
					}
					else if ("ColorLink".equals(name))
					{
						name = StringUtil.leftStr(e.getNodeName(), -4) + "PoolLink";
						e.renameElement(name, null);
					}
				}
			}
		}
	}

	/**
	 *  
	 * @param root 
	 */
	private void fixDependencies(JDFNode root)
	{
		Vector<JDFDependencies> vDep = root.getChildrenByClass(JDFDependencies.class, true, 0);
		if (vDep == null)
			return;
		for (JDFDependencies dep : vDep)
		{
			fixOneDependencies(dep);
		}

	}

	/**
	 *  
	 * @param dep
	 */
	private void fixOneDependencies(JDFDependencies dep)
	{
		if (dep == null)
			return;
		VElement v = dep.getChildElementVector_KElement("RunListRef", null, null, true, 0);
		if (v == null)
			return;
		for (KElement e : v)
		{
			JDFRefElement rl = (JDFRefElement) e;
			rl.renameElement("LayoutElementRef", null);
			JDFResource root = rl.getTargetRoot();
			if (root != null)
			{
				VElement vR = root.getLeaves(true);
				VElement v2 = root.getLinksAndRefs(true, false);
				if (v2 != null)
				{
					for (KElement rl2 : v2)
					{
						rl2.renameElement("LayoutElementLink", null);
					}
				}
				v2 = root.getLinksAndRefs(false, true);
				if (v2 != null)
				{
					for (KElement rl2 : v2)
					{
						rl2.renameElement("LayoutElementRef", null);
					}
				}
				for (KElement r : vR)
				{
					JDFLayoutElement loe = (r instanceof JDFRunList) ? ((JDFRunList) r).getLayoutElement() : null;
					if (loe != null)
					{
						r.moveElements(loe.getChildElementVector_KElement(null, null, null, true, 0), null);
						r.setAttributes(loe);
						loe.deleteNode();
					}
					r.renameElement(ElementName.LAYOUTELEMENT, null);
				}
			}
		}

	}
}