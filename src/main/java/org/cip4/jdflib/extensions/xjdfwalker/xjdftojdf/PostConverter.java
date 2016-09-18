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

import org.cip4.jdflib.core.AttributeName;
import org.cip4.jdflib.core.ElementName;
import org.cip4.jdflib.core.JDFElement;
import org.cip4.jdflib.core.JDFRefElement;
import org.cip4.jdflib.core.JDFResourceLink;
import org.cip4.jdflib.core.JDFResourceLink.EnumUsage;
import org.cip4.jdflib.core.KElement;
import org.cip4.jdflib.core.VElement;
import org.cip4.jdflib.core.VString;
import org.cip4.jdflib.datatypes.JDFAttributeMap;
import org.cip4.jdflib.datatypes.VJDFAttributeMap;
import org.cip4.jdflib.elementwalker.EnsureNSUri;
import org.cip4.jdflib.elementwalker.RemoveEmpty;
import org.cip4.jdflib.elementwalker.UnLinkFinder;
import org.cip4.jdflib.extensions.XJDFConstants;
import org.cip4.jdflib.node.JDFNode;
import org.cip4.jdflib.pool.JDFResourcePool;
import org.cip4.jdflib.resource.JDFResource;
import org.cip4.jdflib.resource.JDFResource.EnumResStatus;
import org.cip4.jdflib.resource.intent.JDFArtDeliveryIntent;
import org.cip4.jdflib.resource.intent.JDFDeliveryIntent;
import org.cip4.jdflib.resource.process.JDFDeliveryParams;
import org.cip4.jdflib.resource.process.JDFDependencies;
import org.cip4.jdflib.resource.process.JDFLayoutElement;
import org.cip4.jdflib.resource.process.JDFRunList;
import org.cip4.jdflib.util.ContainerUtil;
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
	PostConverter(XJDFToJDFImpl xjdfToJDFImpl, JDFNode theNode)
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
		JDFNode root = theNode.getJDFRoot();
		String type = StringUtil.getNonEmpty(root.getType());
		if (type == null || XJDFConstants.Product.equals(type))
		{
			xjdfToJDFImpl.mergeProductLinks(theNode, root);
		}
		fixDelivery();
		new GangCleaner().cleanGangLinks();
		new ResourceCleaner().cleanResources();
		new DependencyCleaner().fixDependencies(root);

		new UnLinkFinder().eraseUnlinked(root);
		xjdfToJDFImpl.firstConvert = false;

		EnsureNSUri fixNS = new EnsureNSUri();
		fixNS.addNS(null, JDFElement.getSchemaURL());
		fixNS.walk(root);

		RemoveEmpty re = new RemoveEmpty();
		re.removEmpty(root);
	}

	private class GangCleaner
	{
		void cleanGangLinks()
		{
			JDFNode n = theNode;
			while (n != null)
			{
				VElement links = theNode.getResourceLinks(ElementName.NODEINFO, null, null);
				links = (VElement) ContainerUtil.addAll(links, theNode.getResourceLinks(ElementName.CUSTOMERINFO, null, null));
				if (links != null)
				{
					for (KElement e : links)
					{
						cleanGangLink(e);
					}
				}
				n = n.getParentJDF();
			}
		}

		/**
		 * 
		 * @param e
		 */
		private void cleanGangLink(KElement e)
		{
			JDFResourceLink link = (JDFResourceLink) e;
			VJDFAttributeMap linkMaps = link.getPartMapVector();
			if (linkMaps != null)
			{
				linkMaps.reduceMap(new VString(AttributeName.PRODUCTPART, null));
				if (!linkMaps.isEmpty())
				{
					boolean mustZapp = true;
					for (JDFAttributeMap map : linkMaps)
					{
						String val = map.get(AttributeName.PRODUCTPART);
						if (theNode.getID().equals(val))
						{
							mustZapp = false;
							break;
						}
					}
					if (mustZapp)
					{
						link.deleteNode();
					}
				}
			}
		}
	}

	/**
	 * move stuff from delivery params to deliveryintent and or artdeliveryintent
	 */
	private void fixDelivery()
	{
		JDFDeliveryParams dp = (JDFDeliveryParams) theNode.getResource(ElementName.DELIVERYPARAMS, EnumUsage.Input, 0);
		VString allTypes = theNode.getAllTypes();
		if (dp != null && allTypes.contains(XJDFConstants.Product))
		{
			boolean keepDI = theNode.getResource(ElementName.DELIVERYINTENT, EnumUsage.Input, 0) != null;
			JDFDeliveryIntent di = (JDFDeliveryIntent) theNode.getCreateResource(ElementName.DELIVERYINTENT, EnumUsage.Input, 0);
			keepDI = di.setFromDeliveryParams(dp) || keepDI;
			if (!keepDI)
				di.deleteNode();

			boolean keepADI = theNode.getResource(ElementName.ARTDELIVERYINTENT, EnumUsage.Input, 0) != null;
			JDFArtDeliveryIntent adi = (JDFArtDeliveryIntent) theNode.getCreateResource(ElementName.ARTDELIVERYINTENT, EnumUsage.Input, 0);
			keepADI = adi.setFromDeliveryParams(dp) || keepADI;
			if (!keepADI)
				adi.deleteNode();

			dp.deleteNode();
		}
	}

	private class ResourceCleaner
	{
		/**
		 * @param theNode
		 */
		void cleanResources()
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
		 *  
		 * @param eRoot
		 */
		private void cleanResource(KElement eRoot)
		{
			final JDFResource resRoot = (JDFResource) eRoot;
			if (resRoot != null)
			{
				final EnumResStatus s = resRoot.getStatusFromLeaves(false);
				if (s != null)
				{
					resRoot.setResStatus(s, false);
				}
				String localName = resRoot.getLocalName();
				if (ElementName.COLORPOOL.equals(localName))
				{
					cleanColorPool(resRoot);
				}
				else if (ElementName.PAGELIST.equals(localName))
				{
					cleanPageList(resRoot);
				}
				cleanClass(resRoot, false);
			}
		}

		private void cleanClass(KElement elem, boolean cleanMe)
		{
			if (cleanMe)
			{
				elem.removeAttribute_KElement(AttributeName.CLASS, null);
			}

			KElement e2 = elem.getFirstChildElement();
			while (e2 != null)
			{
				cleanClass(e2, true);
				e2 = e2.getNextSiblingElement();
			}

		}

		private void cleanPageList(final JDFResource r)
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
						if ("ContentRef".equals(name))
						{
							e.renameElement("PageListRef", null);
						}
						else if ("ContentLink".equals(name))
						{
							e.renameElement("PageListLink", null);
						}
					}
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
							e.renameElement("ColorPoolRef", null);
						}
						else if ("ColorLink".equals(name))
						{
							e.renameElement("ColorPoolLink", null);
						}
						else if ("ContentRef".equals(name))
						{
							e.renameElement("PageListRef", null);
						}
						else if ("ContentLink".equals(name))
						{
							e.renameElement("PageListLink", null);
						}
					}
				}
			}
		}
	}

	private class DependencyCleaner
	{
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

	@Override
	public String toString()
	{
		return "PostConverter [theNode=" + theNode + "]";
	}
}