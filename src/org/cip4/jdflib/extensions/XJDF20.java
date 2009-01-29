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
import java.util.Iterator;
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
import org.cip4.jdflib.core.JDFPartAmount;
import org.cip4.jdflib.core.JDFRefElement;
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
import org.cip4.jdflib.resource.JDFCreasingParams;
import org.cip4.jdflib.resource.JDFCuttingParams;
import org.cip4.jdflib.resource.JDFMarkObject;
import org.cip4.jdflib.resource.JDFMerged;
import org.cip4.jdflib.resource.JDFPart;
import org.cip4.jdflib.resource.JDFResource;
import org.cip4.jdflib.resource.JDFResource.EnumResourceClass;
import org.cip4.jdflib.resource.process.JDFColor;
import org.cip4.jdflib.resource.process.JDFColorPool;
import org.cip4.jdflib.resource.process.JDFContentObject;
import org.cip4.jdflib.resource.process.JDFLayout;
import org.cip4.jdflib.resource.process.JDFRunList;
import org.cip4.jdflib.resource.process.postpress.JDFFoldingParams;
import org.cip4.jdflib.span.JDFSpanBase;
import org.cip4.jdflib.util.StringUtil;

/**
 * @author Dr. Rainer Prosi, Heidelberger Druckmaschinen AG
 * 
 * 15.01.2009
 */
public class XJDF20 extends BaseElementWalker
{

	/**
	 * 
	 */
	public XJDF20()
	{
		super(new BaseWalkerFactory());
		init();
	}

	/**
	 * the root nodename
	 */
	public final static String rootName = "XJDF";
	private final String m_spawnInfo = "SpawnInfo";
	protected VString resAttribs;
	protected KElement newRoot = null;
	protected boolean walkingProduct = false;
	protected boolean first = true;
	// private VJDFAttributeMap vPartMap = null;
	/**
	 * if true add an htmlcolor attribute to color elements for xsl display purposes
	 */
	public boolean bHTMLColor = false;

	/**
	 * @param node
	 * @param vMap
	 * @return
	 */
	public KElement makeNewJDF(JDFNode node, @SuppressWarnings("unused") final VJDFAttributeMap vMap)
	{
		// vPartMap = vMap;
		final JDFNode root = ((JDFDoc) node.getOwnerDocument_JDFElement().clone()).getJDFRoot();
		node = (JDFNode) root.getChildWithAttribute(null, "ID", null, node.getID(), 0, false);
		if (node == null)
		{
			node = root;
		}
		final JDFNode rootIn = node.getJDFRoot();
		final JDFDoc newDoc = new JDFDoc(rootName);

		newRoot = newDoc.getRoot();
		first = true;
		walkTree(node, newRoot);

		walkingProduct = true;
		final KElement productList = newRoot.appendElement("ProductList");
		walkTree(rootIn, productList);
		if (productList.getElement("Product") == null)
		{
			productList.deleteNode();
		}
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
		final JDFPart part = (JDFPart) dummyResPool.appendElement(ElementName.PART);
		resAttribs = paramRes.knownAttributes();
		resAttribs.appendUnique(physRes.knownAttributes());
		resAttribs.appendUnique(intRes.knownAttributes());
		resAttribs.appendUnique(part.knownAttributes());
	}

	String getClassName(final JDFResource r)
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
					final KElement newRootL = makeNewJDF(n, null);
					newRootL.getOwnerDocument_KElement().write2Stream(zos, 2, true);
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

	void setAmountPool(final JDFResourceLink rl, final KElement newLeaf, final JDFAttributeMap partMap)
	{
		if (rl == null)
		{
			return;
		}
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
					if (partMap != null)
					{
						map.removeKeys(partMap.keySet());
					}
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

	/**
	 * 
	 */
	private KElement setBaseResource(final JDFResourceLink rl, final JDFResource r, final KElement xjdfSet)
	{
		KElement newLeaf = null;
		final JDFAttributeMap map = r.getPartMap();
		if (map == null || map.isEmpty())
		{
			newLeaf = xjdfSet.getElement(StringUtil.leftStr(xjdfSet.getNodeName(), -3));
		}
		else
		{
			final KElement oldPart = xjdfSet.getChildByTagName("Part", null, 0, map, false, true);
			if (oldPart != null)
			{
				newLeaf = oldPart.getParentNode_KElement();
			}
		}
		if (newLeaf == null)
		{
			newLeaf = xjdfSet.appendElement(StringUtil.leftStr(xjdfSet.getNodeName(), -3));
			newLeaf.setAttribute("ID", r.getAttribute("ID") + "." + StringUtil.formatInteger(myIndex(newLeaf)));
		}
		setLeafAttributes(r, rl, newLeaf);
		return newLeaf;
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
		if (partMap != null && partMap.size() > 0 && newLeaf.getElement("Part") == null)
		{
			newLeaf.appendElement("Part").setAttributes(partMap);
		}
	}

	/**
	 * @param newRoot
	 * @param rl
	 */
	private void setLinkAttributes(final KElement resourceSet, final KElement rl, final JDFResource linkRoot)
	{
		resourceSet.setAttribute("Name", linkRoot.getNodeName());
		resourceSet.setAttributes(rl);
		resourceSet.removeAttribute(AttributeName.RREF);
		resourceSet.removeAttribute(AttributeName.RSUBREF);
		resourceSet.removeAttribute(AttributeName.AMOUNT);
		resourceSet.removeAttribute(AttributeName.AMOUNTPRODUCED);
		resourceSet.removeAttribute(AttributeName.MAXAMOUNT);
		resourceSet.removeAttribute(AttributeName.ACTUALAMOUNT);
		
		if (rl instanceof JDFResourceLink)
		{
			final JDFResourceLink resLink = (JDFResourceLink) rl;
			final JDFNode rootIn = resLink.getJDFRoot();

			final JDFResource resInRoot = rootIn == null ? linkRoot : (JDFResource) rootIn.getChildWithAttribute(null, "ID", null, resLink.getrRef(), 0, false);
			if (resInRoot != null)
			{
				final VElement vCreators = resInRoot.getCreator(EnumUsage.Input.equals(resLink.getUsage()));
				if (vCreators != null)
				{
					final int size = vCreators.size();
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
	}

	/**
	 * @param newRoot
	 * @param rl
	 * @param linkTarget
	 */
	protected VElement setResource(final JDFResourceLink rl, final JDFResource linkTarget)
	{
		final VElement v = new VElement();
		final String className = getClassName(linkTarget);
		if (className == null)
		{
			return null;
		}
		final String resID = linkTarget.getAttribute("ID");

		KElement resourceSet = newRoot.getChildWithAttribute(className + "Set", "ID", null, resID, 0, true);
		if (resourceSet == null)
		{
			resourceSet = newRoot.appendElement(className + "Set");
			resourceSet.setAttribute("ID", linkTarget.getID());

			setLinkAttributes(resourceSet, rl, linkTarget);
		}
		int nLeaves = resourceSet.numChildElements(className, null);
		final VElement vRes = rl == null ? linkTarget.getLeaves(false) : rl.getTargetVector(0);
		for (int j = 0; j < vRes.size(); j++)
		{
			final JDFResource r = (JDFResource) vRes.elementAt(j);
			final VElement vLeaves = r.getLeaves(false);
			for (int k = 0; k < vLeaves.size(); k++)
			{
				final JDFResource leaf = (JDFResource) vLeaves.elementAt(k);
				final KElement newBaseRes = setBaseResource(rl, leaf, resourceSet);
				final int nn = resourceSet.numChildElements(className, null);
				if (nn > nLeaves)
				{
					nLeaves = nn;
					walkTree(leaf, newBaseRes);
				}
				v.add(newBaseRes);
			}
		}
		return v;
	}

	// //////////////////////////////////////////////////////////////////////////////
	/**
	 * @author Rainer Prosi, Heidelberger Druckmaschinen walker for the various resource sets
	 */
	public class WalkResource extends WalkJDFElement
	{
		/**
		 * 
		 */
		public WalkResource()
		{
			super();
			// TODO Auto-generated constructor stub
		}

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
		 * 
		 */
		public WalkJDFElement()
		{
			super();
		}

		/**
		 * @param e
		 * @return the created resource
		 */
		@Override
		public KElement walk(final KElement jdf, final KElement xjdf)
		{
			final JDFElement je = (JDFElement) jdf;
			makeRefElements(je);
			convertRefElements(je);
			je.inlineRefElements(null, null, false);
			je.removeAttribute(AttributeName.SPAWNID);
			return super.walk(jdf, xjdf);
		}

		/**
		 * @param je
		 */
		private void makeRefElements(final JDFElement je)
		{
			final VElement v = je.getChildElementVector_KElement(null, null, null, true, 0);
			for (int i = 0; i < v.size(); i++)
			{
				final KElement e = v.get(i);
				if (e instanceof JDFResource)
				{
					final JDFResource r = (JDFResource) e;
					if (!mustInline(r.getLocalName()))
					{
						cleanRefs(je, r);
					}
				}
			}
		}

		/**
		 * @param je
		 * @param r
		 */
		private void cleanRefs(final JDFElement je, JDFResource r)
		{
			r = r.makeRootResource(null, je.getParentJDF(), false);
			final JDFResourcePool prevPool = je.getParentJDF().getResourcePool();
			if (prevPool != null)
			{
				r = removeDuplicateRefs(r, prevPool);
			}
			je.refElement(r);
		}

		/**
		 * @param r
		 * @param prevPool
		 * @return
		 */
		private JDFResource removeDuplicateRefs(JDFResource r, final JDFResourcePool prevPool)
		{
			final JDFAttributeMap m = r.getAttributeMap();
			final VElement prevs = prevPool.getChildrenByTagName(r.getNodeName(), null, m, true, true, 0);
			if (prevs != null)
			{
				for (int j = 0; j < prevs.size(); j++)
				{
					final JDFResource prev = (JDFResource) prevs.get(j);
					if (r == prev)
					{
						continue;
					}
					final String id = prev.getID();
					prev.removeAttribute("ID"); // for comparing
					if (r.isEqual(prev)) // found duplicate - remove and ref the original
					{
						r.deleteNode();
						r = prev;
						prev.setID(id);
						break;
					}
					prev.setID(id); // better put it back...
				}
			}
			return r;
		}

		/**
		 * @param je
		 */
		private void convertRefElements(final JDFElement je)
		{
			final VElement v = je.getRefElements();
			for (int i = 0; i < v.size(); i++)
			{
				final JDFRefElement re = (JDFRefElement) v.get(i);
				if (!mustInline(re))
				{
					makeRefAttribute(re);
				}
			}
		}

		/**
		 * @param re
		 */
		private void makeRefAttribute(final JDFRefElement re)
		{
			final String attName = getRefName(re);
			final VElement v = setResource(null, re.getTarget());
			if (v != null)
			{
				final KElement parentElement = re.getParentNode_KElement();
				for (int i = 0; i < v.size(); i++)
				{
					final KElement ref = v.get(i);
					parentElement.appendAttribute(attName, ref.getAttribute("ID"), null, " ", true);
				}
			}
			re.deleteNode();
		}

		/**
		 * @param re
		 * @return
		 */
		protected String getRefName(final JDFRefElement re)
		{
			return re.getLocalName();
		}

		/**
		 * @param re
		 * @return
		 */
		private boolean mustInline(final JDFRefElement re)
		{
			return mustInline(re.getRefLocalName());
		}

		/**
		 * @param refLocalName
		 * @return
		 */
		protected boolean mustInline(final String refLocalName)
		{
			return ElementName.COMCHANNEL.equals(refLocalName) || ElementName.ADDRESS.equals(refLocalName) || ElementName.PERSON.equals(refLocalName);
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
		 * 
		 */
		public WalkResLink()
		{
			super();
			// TODO Auto-generated constructor stub
		}

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
			setResource(rl, linkTarget);
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
	}

	/**
	 * @author Rainer Prosi, Heidelberger Druckmaschinen walker for the various resource sets
	 */
	public class WalkResLinkPool extends WalkJDFElement
	{
		/**
		 * 
		 */
		public WalkResLinkPool()
		{
			super();
			// TODO Auto-generated constructor stub
		}

		/**
		 * @param e
		 * @return ter created resource in this case just remove the pool
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
		 * 
		 */
		public WalkJDF()
		{
			super();
			// TODO Auto-generated constructor stub
		}

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
		 * @param newRootP
		 */
		private void setRootAttributes(final JDFNode node, final KElement newRootP)
		{
			newRootP.appendXMLComment("Very preliminary experimental prototype trial version: using: " + JDFAudit.getStaticAgentName() + " " + JDFAudit.getStaticAgentVersion(), null);
			newRootP.setAttribute(AttributeName.JOBID, node.getJobID(true));
			newRootP.setAttributes(node);

			if (!newRootP.hasAttribute(AttributeName.TYPES))
			{
				newRootP.renameAttribute("Type", "Types", null, null);
			}
			else
			{
				newRootP.removeAttribute("Type");
			}
			if (newRootP.hasAttribute(AttributeName.SPAWNID))
			{
				final KElement spawnInfo = newRootP.appendElement(m_spawnInfo, "www.cip4.org/SpawnInfo");
				spawnInfo.moveAttribute(AttributeName.SPAWNID, newRootP, null, null, null);
				final JDFAncestorPool ancestorPool = node.getAncestorPool();
				if (ancestorPool != null)
				{
					final VJDFAttributeMap vParts = ancestorPool.getPartMapVector();
					if (vParts != null)
					{
						final int size = vParts.size();
						for (int i = 0; i < size; i++)
						{
							spawnInfo.appendElement(ElementName.PART).setAttributes(vParts.elementAt(i));
						}
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
		@SuppressWarnings("synthetic-access")
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

	/**
	 * 
	 * @author Rainer Prosi, Heidelberger Druckmaschinen
	 * 
	 */
	protected class WalkSpan extends WalkJDFElement
	{
		/**
		 * 
		 */
		public WalkSpan()
		{
			super();
			// TODO Auto-generated constructor stub
		}

		/**
		 * invert XXXSpan/@Datatype=foo to FooSpan/@Name=Datatype
		 * @param xjdf
		 * @return true if must continue
		 */
		@Override
		public KElement walk(final KElement jdf, final KElement xjdf)
		{
			final JDFSpanBase je = (JDFSpanBase) jdf;
			je.inlineRefElements(null, null, false);
			final KElement eNew = xjdf.appendElement(je.getDataType().getName());
			eNew.setAttributes(jdf);
			eNew.removeAttribute(AttributeName.DATATYPE);
			eNew.setAttribute(AttributeName.NAME, je.getLocalName());
			return eNew;
		}

		/**
		 * @see org.cip4.jdflib.elementwalker.BaseWalker#matches(org.cip4.jdflib.core.KElement)
		 * @param toCheck
		 * @return true if it matches
		 */
		@Override
		public boolean matches(final KElement toCheck)
		{
			return toCheck instanceof JDFSpanBase;
		}
	}

	/**
	 * 
	 * @author Rainer Prosi, Heidelberger Druckmaschinen
	 * 
	 */
	protected class WalkLayout extends WalkResource
	{
		/**
		 * 
		 */
		public WalkLayout()
		{
			super();
			// TODO Auto-generated constructor stub
		}

		/**
		 * @see org.cip4.jdflib.elementwalker.BaseWalker#matches(org.cip4.jdflib.core.KElement)
		 * @param toCheck
		 * @return true if it matches
		 */
		@Override
		public boolean matches(final KElement toCheck)
		{
			return toCheck instanceof JDFLayout;
		}

		/**
		 * @see org.cip4.jdflib.extensions.XJDF20.WalkJDFElement#mustInline(java.lang.String)
		 */
		@Override
		protected boolean mustInline(final String refLocalName)
		{
			final boolean b = ElementName.LAYERLIST.equals(refLocalName) || ElementName.PAGECONDITION.equals(refLocalName) || ElementName.CONTENTOBJECT.equals(refLocalName)
					|| ElementName.MARKOBJECT.equals(refLocalName) || ElementName.LAYERDETAILS.equals(refLocalName);
			return b || super.mustInline(refLocalName);
		}
	}

	/**
	 * 
	 * @author Rainer Prosi, Heidelberger Druckmaschinen
	 * 
	 */
	protected class WalkInlineAllElement extends WalkJDFElement
	{
		/**
		 * 
		 */
		public WalkInlineAllElement()
		{
			super();
		}

		/**
		 * @see org.cip4.jdflib.elementwalker.BaseWalker#matches(org.cip4.jdflib.core.KElement)
		 * @param toCheck
		 * @return true if it matches
		 */
		@Override
		public boolean matches(final KElement toCheck)
		{
			return (toCheck instanceof JDFContentObject) || (toCheck instanceof JDFMarkObject);
		}

		/**
		 * @see org.cip4.jdflib.extensions.XJDF20.WalkJDFElement#mustInline(java.lang.String)
		 */
		@Override
		protected boolean mustInline(final String refLocalName)
		{
			return true;
		}
	}

	/**
	 * 
	 * @author Rainer Prosi, Heidelberger Druckmaschinen
	 * 
	 */
	protected class WalkInlineAllRes extends WalkResource
	{
		/**
		 * 
		 */
		public WalkInlineAllRes()
		{
			super();
		}

		/**
		 * @see org.cip4.jdflib.elementwalker.BaseWalker#matches(org.cip4.jdflib.core.KElement)
		 * @param toCheck
		 * @return true if it matches
		 */
		@Override
		public boolean matches(final KElement toCheck)
		{
			return (toCheck instanceof JDFRunList) || (toCheck instanceof JDFCuttingParams) || (toCheck instanceof JDFCreasingParams) || (toCheck instanceof JDFFoldingParams);
		}

		/**
		 * @see org.cip4.jdflib.extensions.XJDF20.WalkJDFElement#mustInline(java.lang.String)
		 */
		@Override
		protected boolean mustInline(final String refLocalName)
		{
			return true;
		}
	}

	/**
	 * 
	 * @author Rainer Prosi, Heidelberger Druckmaschinen
	 * 
	 */
	protected class WalkColorPool extends WalkResource
	{
		/**
		 * 
		 */
		public WalkColorPool()
		{
			super();
			// TODO Auto-generated constructor stub
		}

		/**
		 * @see org.cip4.jdflib.elementwalker.BaseWalker#matches(org.cip4.jdflib.core.KElement)
		 * @param toCheck
		 * @return true if it matches
		 */
		@Override
		public boolean matches(final KElement toCheck)
		{
			return toCheck instanceof JDFColorPool;
		}

		/**
		 * @see org.cip4.jdflib.extensions.XJDF20.WalkJDFElement#mustInline(java.lang.String)
		 */
		@Override
		protected boolean mustInline(final String refLocalName)
		{
			final boolean b = ElementName.COLOR.equals(refLocalName);
			return b || super.mustInline(refLocalName);
		}
	}

	/**
	 * 
	 * @author Rainer Prosi, Heidelberger Druckmaschinen
	 * 
	 */
	protected class WalkColor extends WalkResource
	{

		/**
		 * 
		 */
		public WalkColor()
		{
			super();
			// TODO Auto-generated constructor stub
		}

		/**
		 * invert XXXSpan/@Datatype=foo to FooSpan/@Name=Datatype
		 * @param xjdf
		 * @return true if must continue
		 */
		@Override
		public KElement walk(final KElement jdf, final KElement xjdf)
		{
			final KElement k = super.walk(jdf, xjdf);
			if (bHTMLColor)
			{
				k.setAttribute("HTMLColor", ((JDFColor) jdf).getHTMLColor());
			}
			return k;
		}

		/**
		 * @see org.cip4.jdflib.elementwalker.BaseWalker#matches(org.cip4.jdflib.core.KElement)
		 * @param toCheck
		 * @return true if it matches
		 */
		@Override
		public boolean matches(final KElement toCheck)
		{
			return toCheck instanceof JDFColor;
		}
	}

	/**
	 * @param res
	 * @return omaMaps
	 */
	protected static VJDFAttributeMap getPartMapVector(final KElement res)
	{
		VJDFAttributeMap omaMaps = null;
		final VElement parts = res.getChildElementVector(ElementName.PART, null, null, true, 0, false);
		if (parts != null && parts.size() > 0)
		{
			omaMaps = new VJDFAttributeMap();
			for (int i = 0; i < parts.size(); i++)
			{
				omaMaps.add(((JDFPart) parts.get(i)).getPartMap());
			}
		}
		return omaMaps;
	}

}
