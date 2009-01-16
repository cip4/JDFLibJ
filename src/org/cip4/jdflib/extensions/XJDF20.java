/*
 * The CIP4 Software License, Version 1.0
 *
 *
 * Copyright (c) 2001-2009 The International Cooperation for the Integration of
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

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;
import java.util.zip.ZipEntry;
import java.util.zip.ZipException;
import java.util.zip.ZipOutputStream;

import org.cip4.jdflib.core.AttributeName;
import org.cip4.jdflib.core.ElementName;
import org.cip4.jdflib.core.JDFAudit;
import org.cip4.jdflib.core.JDFCustomerInfo;
import org.cip4.jdflib.core.JDFDoc;
import org.cip4.jdflib.core.JDFElement;
import org.cip4.jdflib.core.JDFException;
import org.cip4.jdflib.core.JDFNodeInfo;
import org.cip4.jdflib.core.JDFPartAmount;
import org.cip4.jdflib.core.JDFResourceLink;
import org.cip4.jdflib.core.KElement;
import org.cip4.jdflib.core.VElement;
import org.cip4.jdflib.core.VString;
import org.cip4.jdflib.core.JDFResourceLink.EnumUsage;
import org.cip4.jdflib.datatypes.JDFAttributeMap;
import org.cip4.jdflib.datatypes.VJDFAttributeMap;
import org.cip4.jdflib.elementwalker.BaseElementWalker;
import org.cip4.jdflib.elementwalker.BaseWalker;
import org.cip4.jdflib.elementwalker.BaseWalkerFactory;
import org.cip4.jdflib.node.JDFNode;
import org.cip4.jdflib.node.JDFSpawned;
import org.cip4.jdflib.node.JDFNode.EnumType;
import org.cip4.jdflib.pool.JDFAmountPool;
import org.cip4.jdflib.pool.JDFAncestorPool;
import org.cip4.jdflib.pool.JDFAuditPool;
import org.cip4.jdflib.pool.JDFResourceLinkPool;
import org.cip4.jdflib.pool.JDFResourcePool;
import org.cip4.jdflib.resource.JDFMerged;
import org.cip4.jdflib.resource.JDFResource;
import org.cip4.jdflib.resource.JDFResource.EnumResourceClass;
import org.cip4.jdflib.resource.intent.JDFDropItemIntent;
import org.cip4.jdflib.resource.process.JDFComponent;
import org.cip4.jdflib.util.StringUtil;

/**
 * @author Dr. Rainer Prosi, Heidelberger Druckmaschinen AG
 * 
 * 15.01.2009
 */
public class XJDF20 extends BaseElementWalker
{

	/**
	 * @param factory
	 */
	public XJDF20()
	{
		super(new BaseWalkerFactory());
		init();
	}

	public final static String rootName = "XJDF";
	private final String m_spawnInfo = "SpawnInfo";
	protected VString resAttribs;
	protected Set<String> doneRes;
	protected Set<String> refRes;
	protected boolean walkingProduct = false;
	protected boolean first = true;
	private VJDFAttributeMap vPartMap = null;

	/**
	 * @param node
	 * @param rootIn
	 * @return
	 */
	public KElement makeNewJDF(JDFNode node, final VJDFAttributeMap vMap)
	{
		vPartMap = vMap;
		final JDFNode root = ((JDFDoc) node.getOwnerDocument_JDFElement().clone()).getJDFRoot();
		node = (JDFNode) root.getChildWithAttribute(null, "ID", null, node.getID(), 0, false);
		final JDFNode rootIn = node.getJDFRoot();
		final JDFDoc newDoc = new JDFDoc(rootName);

		final KElement newRoot = newDoc.getRoot();
		first = true;
		walkTree(node, newRoot);

		walkingProduct = true;
		final KElement productList = newRoot.appendElement("ProductList");
		walkTree(rootIn, productList);
		walkingProduct = false;

		newRoot.eraseEmptyNodes(true);

		return newRoot;
	}

	/**
	 * 
	 */
	private void init()
	{
		final JDFResourcePool dummyResPool = (JDFResourcePool) new JDFDoc("ResourcePool").getRoot();
		final JDFResource intRes = dummyResPool.appendResource("intent", EnumResourceClass.Intent, null);
		final JDFResource physRes = dummyResPool.appendResource("physical", EnumResourceClass.Consumable, null);
		final JDFResource paramRes = dummyResPool.appendResource("param", EnumResourceClass.Parameter, null);
		resAttribs = paramRes.knownAttributes();
		resAttribs.appendUnique(physRes.knownAttributes());
		resAttribs.appendUnique(intRes.knownAttributes());
		refRes = new HashSet<String>();
		doneRes = new HashSet<String>();
	}

	/**
	 * @param product
	 * @param rootIn
	 */
	private void setProductResources(final KElement product, final JDFNode rootIn)
	{
		final VElement prodLinks = rootIn.getResourceLinks(null);
		final HashMap componentMap = new HashMap();
		final int size = prodLinks == null ? 0 : prodLinks.size();
		for (int i = size - 1; i >= 0; i--)
		{
			final JDFResourceLink rl = (JDFResourceLink) prodLinks.elementAt(i);
			final JDFResource linkRoot = rl.getLinkRoot();
			if (linkRoot instanceof JDFNodeInfo)
			{
				prodLinks.remove(i);
			}
			if (linkRoot instanceof JDFCustomerInfo)
			{
				prodLinks.remove(i);
			}
			if (linkRoot instanceof JDFComponent)
			{
				prodLinks.remove(i);
				if (EnumUsage.Output.equals(rl.getUsage()))
				{
					linkRoot.setAttribute("tmp_id", linkRoot.getID());
					componentMap.put(linkRoot.getID(), rootIn.getID());
				}
			}
		}
		setResources(product, rootIn, prodLinks, null);
		final VElement vDropItems = product.getChildrenByTagName(ElementName.DROPITEMINTENT, null, null, false, true, 0);
		for (int i = 0; i < vDropItems.size(); i++)
		{
			final JDFDropItemIntent dropItemIntent = (JDFDropItemIntent) vDropItems.item(i);
			final JDFComponent c = dropItemIntent.getComponent();
			if (c != null)
			{
				final String id = (String) componentMap.get(c.getAttribute("tmp_id", null, ""));
				if (id != null)
				{
					dropItemIntent.setAttribute("ProductRef", id);
					c.deleteNode();
				}
			}
		}
	}

	private void setResources(final KElement newRoot, final JDFNode nodeIn, final VElement resLinks, final JDFNode rootIn)
	{
		final VElement vResLinks = resLinks == null ? nodeIn.getResourceLinks(null) : resLinks;
		if (vResLinks == null)
		{
			return;
		}
		final boolean bProduct = EnumType.Product.equals(nodeIn.getEnumType());

		for (int i = 0; i < vResLinks.size(); i++)
		{
			final JDFResourceLink rl = (JDFResourceLink) vResLinks.elementAt(i);
			final JDFResource linkTarget = rl.getLinkRoot();
			if (bProduct && linkTarget instanceof JDFComponent)
			{
				continue;
			}
			linkTarget.expand(false);
			// setResource(newRoot, rl, linkTarget);
		}
		return;
	}

	/**
	 * @param r
	 */
	private String getClassName(final JDFResource r)
	{
		if (r == null)
		{
			return null;
		}
		final EnumResourceClass resourceClass = r.getResourceClass();
		if (resourceClass == null)
		{
			return null;
		}
		String className = "Resource";
		if (resourceClass.equals(EnumResourceClass.Parameter) || resourceClass.equals(EnumResourceClass.Intent))
		{
			className = resourceClass.getName();
		}
		if (resourceClass.equals(EnumResourceClass.PlaceHolder))
		{
			return null;
		}
		return className;
	}

	protected int myIndex(final KElement e)
	{
		final KElement parent = e.getParentNode_KElement();
		int n = 0;
		final String nodeName = e.getNodeName();
		final String namespaceURI = e.getNamespaceURI();
		KElement sib = parent.getFirstChildElement(nodeName, namespaceURI);
		while (sib != e)
		{
			sib = sib.getNextSiblingElement(nodeName, namespaceURI);
			if (sib == null)
			{
				return -1;
			}
			n++;
		}
		return n;
	}

	/**
	 * calculate a file extension name based of rootName
	 * @return String
	 */
	public static String getExtension()
	{
		return rootName.toLowerCase();
	}

	public void saveZip(final String fileName, final JDFNode rootNode, final boolean replace)
	{
		final File file = new File(fileName);
		if (file.canRead())
		{
			if (replace)
			{
				file.delete();
			}
			else
			{
				throw new JDFException("output file exists: " + file.getPath());
			}
		}
		// file.createNewFile(fileName);

		try
		{
			final VElement v = rootNode.getvJDFNode(null, null, false);
			final FileOutputStream fos = new FileOutputStream(fileName);
			final ZipOutputStream zos = new ZipOutputStream(fos);
			for (int i = 0; i < v.size(); i++)
			{
				final JDFNode n = (JDFNode) v.elementAt(i);
				String nam = n.getJobPartID(false);
				if (nam == "")
				{
					nam = "Node" + i;
				}
				try
				{
					nam += "." + rootName;
					final ZipEntry ze = new ZipEntry(nam);
					zos.putNextEntry(ze);
					final KElement newRoot = makeNewJDF(n, null);
					newRoot.getOwnerDocument_KElement().write2Stream(zos, 2, true);
					zos.closeEntry();

				}
				catch (final ZipException x)
				{
					// TODO Auto-generated catch block
					x.printStackTrace();
				}
				catch (final IOException x)
				{
					// TODO Auto-generated catch block
					x.printStackTrace();
				}
			}
			zos.close();
		}
		catch (final IOException x)
		{
			// TODO Auto-generated catch block
			x.printStackTrace();
		}
	}

	private void setAmountPool(final JDFResourceLink rl, final KElement newLeaf, final JDFAttributeMap partMap)
	{
		JDFAmountPool ap = rl.getAmountPool();
		if (ap == null)
		{
			if (rl.hasAttribute(AttributeName.AMOUNT) || rl.hasAttribute(AttributeName.ACTUALAMOUNT) || rl.hasAttribute(AttributeName.MAXAMOUNT))
			{
				ap = (JDFAmountPool) newLeaf.appendElement(ElementName.AMOUNTPOOL);
				final JDFPartAmount pa = ap.appendPartAmount();
				pa.copyAttribute(AttributeName.AMOUNT, rl);
				pa.copyAttribute(AttributeName.ACTUALAMOUNT, rl);
				pa.copyAttribute(AttributeName.MAXAMOUNT, rl);
			}
		}
		else
		{
			final VElement vPartAmounts = ap.getMatchingPartAmountVector(partMap);
			if (vPartAmounts != null)
			{
				for (int i = 0; i < vPartAmounts.size(); i++)
				{
					JDFPartAmount pa = (JDFPartAmount) vPartAmounts.item(i);
					final JDFAttributeMap map = pa.getPartMap();
					map.removeKeys(partMap.keySet());
					if (map.isEmpty()) // no further subdevision - simply blast into leaf
					{
						newLeaf.setAttributes(pa);
					}
					else if (map.size() == 1 && map.containsKey(AttributeName.CONDITION))
					{
						final JDFAttributeMap attMap = pa.getAttributeMap();
						final Iterator it = attMap.getKeyIterator();
						final String condition = map.get(AttributeName.CONDITION);
						while (it.hasNext())
						{
							final String key = (String) it.next();
							// if(key.indexOf(AttributeName.AMOUNT)>0)
							// {
							newLeaf.setAttribute(key + condition, attMap.get(key));
							// }
						}
					}
					else
					// retain ap
					{
						final KElement amountPool = newLeaf.getCreateElement("AmountPool");
						pa = (JDFPartAmount) amountPool.copyElement(pa, null);
						pa.setPartMap(map);
					}
					// TODO special handling for condition
				}
			}
		}
	}

	// //////////////////////////////////////////////////////////////////////////////
	/**
	 * @author Rainer Prosi, Heidelberger Druckmaschinen walker for the various resource sets
	 */
	public class WalkResource extends WalkJDFElement
	{
		/**
		 * @param e
		 * @return the created resource
		 */
		@Override
		public KElement walk(final KElement jdf, final KElement xjdf)
		{
			final JDFResource r = (JDFResource) jdf;
			final KElement newResLeaf = super.walk(jdf, xjdf);
			newResLeaf.removeAttributes(r.getPartIDKeys());
			newResLeaf.removeAttribute(AttributeName.ID);
			newResLeaf.removeAttribute(AttributeName.CLASS);
			newResLeaf.removeAttribute(AttributeName.PARTUSAGE);
			newResLeaf.removeAttribute(AttributeName.LOCKED);

			final String localName = xjdf.getLocalName();
			final boolean bRoot = "Intent".equals(localName) || "Parameter".equals(localName) || "Resource".equals(localName);
			for (int i = 0; i < resAttribs.size(); i++)
			{
				if (newResLeaf.hasAttribute(resAttribs.stringAt(i)))
				{
					if (bRoot)
					{
						xjdf.moveAttribute(resAttribs.stringAt(i), newResLeaf, null, null, null);
					}
					else
					{
						newResLeaf.removeAttribute(resAttribs.stringAt(i));
					}

				}
			}

			return newResLeaf;
		}

		/**
		 * @see org.cip4.jdflib.elementwalker.BaseWalker#matches(org.cip4.jdflib.core.KElement)
		 * @param toCheck
		 * @return true if it matches
		 */
		@Override
		public boolean matches(final KElement toCheck)
		{
			return toCheck instanceof JDFResource;
		}
	} // //////////////////////////////////////////////////////////////////////////////

	/**
	 * @author Rainer Prosi, Heidelberger Druckmaschinen walker for the various resource sets
	 */
	public class WalkJDFElement extends WalkElement
	{
		/**
		 * @param e
		 * @return the created resource
		 */
		@Override
		public KElement walk(final KElement jdf, final KElement xjdf)
		{
			final JDFElement je = (JDFElement) jdf;
			je.inlineRefElements(null, null, false);
			return super.walk(jdf, xjdf);
		}

		/**
		 * @see org.cip4.jdflib.elementwalker.BaseWalker#matches(org.cip4.jdflib.core.KElement)
		 * @param toCheck
		 * @return true if it matches
		 */
		@Override
		public boolean matches(final KElement toCheck)
		{
			return toCheck instanceof JDFElement;
		}
	}

	/**
	 * @author Rainer Prosi, Heidelberger Druckmaschinen walker for the various resource sets
	 */
	public class WalkResLink extends WalkJDFElement
	{
		/**
		 * @param e
		 * @return thr created resource in this case just remove the pool
		 */
		@Override
		public KElement walk(final KElement jdf, final KElement xjdf)
		{
			final JDFResourceLink rl = (JDFResourceLink) jdf;
			final JDFResource linkTarget = rl.getLinkRoot();
			if (linkTarget == null)
			{
				return null;
			}
			final boolean bCustomerInfo = linkTarget instanceof JDFCustomerInfo;
			if (walkingProduct)
			{
				if (!bCustomerInfo && !EnumResourceClass.Intent.equals(linkTarget.getResourceClass()))
				{
					return null;
				}
			}
			else
			{
				if (bCustomerInfo || EnumResourceClass.Intent.equals(linkTarget.getResourceClass()))
				{
					return null;
				}
			}
			linkTarget.expand(false);
			setResource(xjdf, rl, linkTarget);
			return null;
		}

		/**
		 * @see org.cip4.jdflib.elementwalker.BaseWalker#matches(org.cip4.jdflib.core.KElement)
		 * @param toCheck
		 * @return true if it matches
		 */
		@Override
		public boolean matches(final KElement toCheck)
		{
			return toCheck instanceof JDFResourceLink;
		}

		/**
		 * @param newRoot
		 * @param rl
		 * @param linkTarget
		 */
		private void setResource(final KElement newRoot, final JDFResourceLink rl, final JDFResource linkTarget)
		{
			final String className = getClassName(linkTarget);
			if (className == null)
			{
				return;
			}

			final KElement resourceSet = newRoot.appendElement(className + "Set");

			setLinkAttributes(resourceSet, rl, linkTarget);

			final VElement vRes = rl.getTargetVector(0);
			for (int j = 0; j < vRes.size(); j++)
			{
				final JDFResource r = (JDFResource) vRes.elementAt(j);
				final VElement vLeaves = r.getLeaves(false);
				for (int k = 0; k < vLeaves.size(); k++)
				{
					final JDFResource leaf = (JDFResource) vLeaves.elementAt(k);
					final KElement newBaseRes = setBaseResource(rl, leaf, resourceSet);
					walkTree(leaf, newBaseRes);
				}
			}
		}

		/**
		 * 
		 */
		private KElement setBaseResource(final JDFResourceLink rl, final JDFResource r, final KElement xjdfSet)
		{
			final KElement newLeaf = xjdfSet.appendElement(StringUtil.leftStr(xjdfSet.getNodeName(), -3));
			newLeaf.setAttribute("ID", r.getAttribute("ID") + "." + StringUtil.formatInteger(myIndex(newLeaf)));
			setLeafAttributes(r, rl, newLeaf);
			return newLeaf;
		}

		/**
		 * @param newRoot
		 * @param rl
		 */
		private void setLinkAttributes(final KElement resourceSet, final KElement rl, final JDFResource linkRoot)
		{
			resourceSet.setAttribute("Name", linkRoot.getNodeName());
			resourceSet.copyAttribute("ID", linkRoot, null, null, null);
			resourceSet.setAttributes(rl);
			resourceSet.removeAttribute(AttributeName.RREF);
			resourceSet.removeAttribute(AttributeName.RSUBREF);
			if (rl instanceof JDFResourceLink)
			{
				final JDFResourceLink resLink = (JDFResourceLink) rl;
				final JDFNode rootIn = resLink.getJDFRoot();

				final JDFResource resInRoot = rootIn == null ? linkRoot : (JDFResource) rootIn.getChildWithAttribute(null, "ID", null, resLink.getrRef(), 0, false);
				if (resInRoot != null)
				{
					final VElement vCreators = resInRoot.getCreator(EnumUsage.Input.equals(resLink.getUsage()));
					final int size = vCreators == null ? 0 : vCreators.size();
					for (int i = 0; i < size; i++)
					{
						final JDFNode depNode = (JDFNode) vCreators.elementAt(i);
						final KElement dependent = resourceSet.appendElement("Dependent");
						dependent.setAttribute(AttributeName.JOBID, depNode.getJobID(true));
						dependent.copyAttribute(AttributeName.JMFURL, depNode, null, null, null);
						dependent.copyAttribute(AttributeName.JOBPARTID, depNode, null, null, null);
					}
				}
			}
		}

		/**
		 * @param leaf
		 * @param newLeaf
		 */
		private void setLeafAttributes(final JDFResource leaf, final JDFResourceLink rl, final KElement newLeaf)
		{
			final JDFAttributeMap partMap = leaf.getPartMap();
			// JDFAttributeMap attMap=leaf.getAttributeMap();
			// attMap.remove("ID");
			setAmountPool(rl, newLeaf, partMap);

			// retain spawn informatiom
			if (leaf.hasAttribute(AttributeName.SPAWNIDS))
			{
				final KElement spawnInfo = newLeaf.getDocRoot().getCreateElement(m_spawnInfo, null, 0);
				final KElement spawnID = spawnInfo.appendElement("SpawnID");
				spawnID.moveAttribute(AttributeName.SPAWNIDS, newLeaf, null, null, null);
				spawnID.moveAttribute(AttributeName.SPAWNSTATUS, newLeaf, null, null, null);
				spawnID.copyAttribute(AttributeName.RESOURCEID, newLeaf, AttributeName.ID, null, null);
			}
			if (partMap != null && partMap.size() > 0)
			{
				newLeaf.appendElement("Part").setAttributes(partMap);
				// attMap.removeKeys(partMap.keySet());
			}
		}
	}

	/**
	 * @author Rainer Prosi, Heidelberger Druckmaschinen walker for the various resource sets
	 */
	public class WalkResLinkPool extends WalkJDFElement
	{
		/**
		 * @param e
		 * @return thr created resource in this case just remove the pool
		 */
		@Override
		public KElement walk(final KElement jdf, final KElement xjdf)
		{
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
			return toCheck instanceof JDFResourceLinkPool;
		}
	}

	/**
	 * @author Rainer Prosi, Heidelberger Druckmaschinen walker for the various resource sets
	 */
	public class WalkJDF extends WalkJDFElement
	{
		/**
		 * @param e
		 * @return thr created resource
		 */
		@Override
		public KElement walk(final KElement jdf, final KElement xjdf)
		{
			if (!first)
			{
				return null;
			}
			first = false;
			final JDFNode node = (JDFNode) jdf;
			setRootAttributes(node, xjdf);
			xjdf.removeAttribute(AttributeName.ACTIVATION);
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
			return !walkingProduct && (toCheck instanceof JDFNode);
		}

		/**
		 * @param node
		 * @param newRoot
		 */
		private void setRootAttributes(final JDFNode node, final KElement newRoot)
		{
			newRoot.appendXMLComment("Very preliminary experimental prototype trial version: using: " + JDFAudit.getStaticAgentName() + " " + JDFAudit.getStaticAgentVersion(), null);
			newRoot.setAttribute(AttributeName.JOBID, node.getJobID(true));
			newRoot.setAttributes(node);

			if (!newRoot.hasAttribute(AttributeName.TYPES))
			{
				newRoot.renameAttribute("Type", "Types", null, null);
			}
			else
			{
				newRoot.removeAttribute("Type");
			}
			if (newRoot.hasAttribute(AttributeName.SPAWNID))
			{
				final KElement spawnInfo = newRoot.appendElement(m_spawnInfo, "www.cip4.org/SpawnInfo");
				spawnInfo.moveAttribute(AttributeName.SPAWNID, newRoot, null, null, null);
				final JDFAncestorPool ancestorPool = node.getAncestorPool();
				if (ancestorPool != null)
				{
					final VJDFAttributeMap vParts = ancestorPool.getPartMapVector();
					final int size = vParts == null ? 0 : vParts.size();
					for (int i = 0; i < size; i++)
					{
						spawnInfo.appendElement(ElementName.PART).setAttributes(vParts.elementAt(i));
					}
				}
			}
		}
	}

	/**
	 * @author Rainer Prosi, Heidelberger Druckmaschinen walker for the various resource sets
	 */
	public class WalkProduct extends WalkJDFElement
	{
		/**
		 * @param e
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
			calcAmounts(node, prod);
			return prod;
		}

		/**
		 * @param node
		 * @param prod
		 */
		private void calcAmounts(final JDFNode node, final KElement prod)
		{
			final JDFResourceLink cOut = node.getLink(0, "ComponentLink", new JDFAttributeMap("Usage", "Output"), null);
			if (cOut != null)
			{
				setAmountPool(cOut, prod, null);
			}

		}

		/**
		 * @param node
		 */
		private void calcChildren(final JDFNode node, final KElement prod)
		{
			final VElement vComp = node.getPredecessors(true, true);
			final int siz = vComp == null ? 0 : vComp.size();
			final VString kids = new VString();
			for (int i = 0; i < siz; i++)
			{
				final JDFNode nPre = (JDFNode) vComp.get(i);
				if (EnumType.Product.equals(nPre.getEnumType()))
				{
					kids.add(nPre.getID());
				}
			}
			if (kids.size() > 0)
			{
				prod.setAttribute("ProductRefs", kids, null);
			}
			else
			{
				final KElement list = prod.getParentNode_KElement();
				list.appendAttribute("RootProducts", node.getID(), null, null, true);
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
			return walkingProduct && toCheck instanceof JDFNode;
		}
	}

	/**
	 * 
	 * @author Rainer Prosi, Heidelberger Druckmaschinen
	 * 
	 */
	protected class WalkElement extends BaseWalker
	{
		public WalkElement()
		{
			super(getFactory());
		}

		/**
		 * @param xjdf
		 * @return true if must continue
		 */
		@Override
		public KElement walk(final KElement jdf, final KElement xjdf)
		{
			final KElement eNew = xjdf.copyElement(jdf, null);
			eNew.removeChildren(null, null, null);
			return eNew;
		}
	}

	/**
	 * 
	 * @author Rainer Prosi, Heidelberger Druckmaschinen
	 * 
	 */
	protected class WalkIgnore extends WalkJDFElement
	{

		public WalkIgnore()
		{
			super();
		}

		/**
		 * @param xjdf
		 * @return true if must continue
		 */
		@Override
		public KElement walk(final KElement jdf, final KElement xjdf)
		{
			return null;
		}

		/**
		 * @see org.cip4.jdflib.elementwalker.BaseWalker#matches(org.cip4.jdflib.core.KElement)
		 * @param toCheck
		 * @return true if it matches
		 */
		@Override
		public boolean matches(final KElement toCheck)
		{
			return toCheck instanceof JDFAncestorPool || toCheck instanceof JDFResourcePool || toCheck instanceof JDFSpawned || toCheck instanceof JDFMerged;
		}
	}

	/**
	 * 
	 * @author Rainer Prosi, Heidelberger Druckmaschinen
	 * 
	 */
	protected class WalkAuditPool extends WalkJDFElement
	{

		public WalkAuditPool()
		{
			super();
		}

		/**
		 * @param xjdf
		 * @return true if must continue
		 */
		@Override
		public KElement walk(final KElement jdf, final KElement xjdf)
		{
			if (walkingProduct)
			{
				return null;
			}
			return super.walk(jdf, xjdf);
		}

		/**
		 * @see org.cip4.jdflib.elementwalker.BaseWalker#matches(org.cip4.jdflib.core.KElement)
		 * @param toCheck
		 * @return true if it matches
		 */
		@Override
		public boolean matches(final KElement toCheck)
		{
			return toCheck instanceof JDFAuditPool;
		}

	}
}
