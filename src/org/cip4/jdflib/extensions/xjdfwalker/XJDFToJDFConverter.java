/*
 * The CIP4 Software License, Version 1.0
 *
 *
 * Copyright (c) 2001-2011 The International Cooperation for the Integration of
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
/**
 * 
 */
package org.cip4.jdflib.extensions.xjdfwalker;

import java.util.Iterator;
import java.util.Map;
import java.util.Vector;
import java.util.zip.DataFormatException;

import org.cip4.jdflib.auto.JDFAutoComponent.EnumComponentType;
import org.cip4.jdflib.core.AttributeInfo.EnumAttributeType;
import org.cip4.jdflib.core.AttributeName;
import org.cip4.jdflib.core.ElementName;
import org.cip4.jdflib.core.JDFDoc;
import org.cip4.jdflib.core.JDFElement;
import org.cip4.jdflib.core.JDFElement.EnumNodeStatus;
import org.cip4.jdflib.core.JDFElement.EnumVersion;
import org.cip4.jdflib.core.JDFNodeInfo;
import org.cip4.jdflib.core.JDFRefElement;
import org.cip4.jdflib.core.JDFResourceLink;
import org.cip4.jdflib.core.JDFResourceLink.EnumUsage;
import org.cip4.jdflib.core.JDFSeparationList;
import org.cip4.jdflib.core.KElement;
import org.cip4.jdflib.core.VElement;
import org.cip4.jdflib.core.VString;
import org.cip4.jdflib.datatypes.JDFAttributeMap;
import org.cip4.jdflib.datatypes.JDFXYPair;
import org.cip4.jdflib.datatypes.VJDFAttributeMap;
import org.cip4.jdflib.elementwalker.BaseElementWalker;
import org.cip4.jdflib.elementwalker.BaseWalker;
import org.cip4.jdflib.elementwalker.BaseWalkerFactory;
import org.cip4.jdflib.extensions.IntentHelper;
import org.cip4.jdflib.extensions.ProductHelper;
import org.cip4.jdflib.extensions.SetHelper;
import org.cip4.jdflib.extensions.XJDF20;
import org.cip4.jdflib.extensions.xjdfwalker.IDFinder.IDPart;
import org.cip4.jdflib.node.JDFNode;
import org.cip4.jdflib.node.JDFNode.EnumType;
import org.cip4.jdflib.pool.JDFAuditPool;
import org.cip4.jdflib.pool.JDFResourceLinkPool;
import org.cip4.jdflib.pool.JDFResourcePool;
import org.cip4.jdflib.resource.JDFBindItem;
import org.cip4.jdflib.resource.JDFEdgeGluing;
import org.cip4.jdflib.resource.JDFEmbossingItem;
import org.cip4.jdflib.resource.JDFHardCoverBinding;
import org.cip4.jdflib.resource.JDFInsert;
import org.cip4.jdflib.resource.JDFNumberItem;
import org.cip4.jdflib.resource.JDFPart;
import org.cip4.jdflib.resource.JDFProofItem;
import org.cip4.jdflib.resource.JDFResource;
import org.cip4.jdflib.resource.JDFResource.EnumPartIDKey;
import org.cip4.jdflib.resource.JDFResource.EnumResStatus;
import org.cip4.jdflib.resource.JDFResource.EnumResourceClass;
import org.cip4.jdflib.resource.JDFSoftCoverBinding;
import org.cip4.jdflib.resource.JDFStripBinding;
import org.cip4.jdflib.resource.JDFStrippingParams;
import org.cip4.jdflib.resource.JDFTabs;
import org.cip4.jdflib.resource.intent.JDFArtDelivery;
import org.cip4.jdflib.resource.intent.JDFColorIntent;
import org.cip4.jdflib.resource.intent.JDFDropIntent;
import org.cip4.jdflib.resource.intent.JDFDropItemIntent;
import org.cip4.jdflib.resource.intent.JDFIntentResource;
import org.cip4.jdflib.resource.intent.JDFShapeCut;
import org.cip4.jdflib.resource.process.JDFColorantControl;
import org.cip4.jdflib.resource.process.JDFComponent;
import org.cip4.jdflib.resource.process.JDFDependencies;
import org.cip4.jdflib.resource.process.JDFLayoutElement;
import org.cip4.jdflib.resource.process.JDFMedia;
import org.cip4.jdflib.resource.process.JDFRunList;
import org.cip4.jdflib.resource.process.postpress.JDFChannelBinding;
import org.cip4.jdflib.resource.process.postpress.JDFCoilBinding;
import org.cip4.jdflib.resource.process.postpress.JDFPlasticCombBinding;
import org.cip4.jdflib.resource.process.postpress.JDFRingBinding;
import org.cip4.jdflib.resource.process.postpress.JDFSaddleStitching;
import org.cip4.jdflib.resource.process.postpress.JDFSideSewing;
import org.cip4.jdflib.resource.process.postpress.JDFThreadSealing;
import org.cip4.jdflib.resource.process.postpress.JDFThreadSewing;
import org.cip4.jdflib.util.JDFDate;
import org.cip4.jdflib.util.StringUtil;
import org.cip4.jdflib.util.UnitParser;

/**
 * @author Rainer Prosi, Heidelberger Druckmaschinen
 * 
 */
public class XJDFToJDFConverter extends BaseElementWalker
{
	JDFDoc jdfDoc;
	// JDFNode theNode;
	Map<String, IDPart> idMap;
	boolean firstConvert;
	boolean firstproductInList;
	boolean foundProduct;
	protected JDFNode currentJDFNode = null;
	/**
	 * if true, create the product, else ignore it
	 */
	public boolean createProduct = true;
	/**
	 * 
	 */
	public boolean convertUnits = false;
	/**
	 * 
	 */
	private EnumVersion version = EnumVersion.Version_1_3;

	/**
	 * @param template the jdfdoc to fill this into
	 * 
	 */
	public XJDFToJDFConverter(final JDFDoc template)
	{
		super(new BaseWalkerFactory());
		firstConvert = firstproductInList = true;
		jdfDoc = template == null ? null : template.clone();
		// theNode = null;
		idMap = null;
		foundProduct = false;
	}

	/**
	 * reset the product so that multiple independent product xjdf elements can be merged
	 */
	public void resetProduct()
	{
		foundProduct = false;
	}

	/**
	 * @param xjdf
	 * @return the converted jdf
	 */
	public JDFDoc convert(KElement xjdf)
	{
		if (xjdf == null)
		{
			return null;
		}
		if (jdfDoc == null)
		{
			jdfDoc = new JDFDoc("JDF");
			jdfDoc.setBodyPart(xjdf.getOwnerDocument_KElement().getBodyPart());
		}
		JDFNode root = prepareRoot();
		xjdf = reparse(xjdf);

		final JDFNode theNode = findNode(xjdf, true);
		if (theNode == null)
		{
			return null;
		}

		idMap = new IDFinder().getMap(xjdf);
		walkTree(xjdf, theNode);

		root = theNode.getJDFRoot();
		if ("Product".equals(root.getType()))
		{
			mergeProductLinks(theNode, root);
		}
		cleanResources(theNode);
		fixDependencies(root);
		firstConvert = false;
		return jdfDoc;
	}

	/**
	 * TODO Please insert comment!
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
	 * TODO Please insert comment!
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
					for (KElement rl2 : v2)
						rl2.renameElement("LayoutElementLink", null);
				v2 = root.getLinksAndRefs(false, true);
				if (v2 != null)
					for (KElement rl2 : v2)
						rl2.renameElement("LayoutElementRef", null);
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

	protected KElement reparse(KElement xjdf)
	{
		if (xjdf != null)
		{
			JDFDoc doc = new JDFDoc(xjdf.getOwnerDocument());
			doc.setInitOnCreate(false);
			xjdf = doc.getRoot();
		}
		return xjdf;
	}

	/**
	 * @return
	 */
	private JDFNode prepareRoot()
	{
		JDFNode root = jdfDoc.getJDFRoot();
		if (firstConvert)
		{
			root.getCreateAuditPool().addModified(null, null);
		}
		else
		{
			if (!"Product".equals(root.getType()))
			{
				root = createProductRoot(root);
			}
		}
		return root;
	}

	/**
	 * @param theNode
	 */
	private void cleanResources(final JDFNode theNode)
	{
		final JDFResourceLinkPool rlp = theNode.getResourceLinkPool();
		final VElement vRes = rlp == null ? null : rlp.getPoolChildren(null, null, null);
		if (vRes != null)
		{
			for (int i = 0; i < vRes.size(); i++)
			{
				final JDFResourceLink rl = (JDFResourceLink) vRes.get(i);
				final JDFResource r = rl.getLinkRoot();
				if (r != null)
				{
					final EnumResStatus s = r.getStatusFromLeaves(false);
					if (s != null)
					{
						r.setResStatus(s, false);
					}
				}
			}
		}
	}

	/**
	 * @param xjdf
	 * @return true if the element can be converted
	 */
	public boolean canConvert(final KElement xjdf)
	{
		return xjdf == null ? false : XJDF20.rootName.equals(xjdf.getLocalName());
	}

	/**
	 * find and optionally create the appropriate node
	 * @param xjdf
	 * @param create if true, creat the new node
	 * @return the node
	 */
	private JDFNode findNode(KElement xjdf, final boolean create)
	{
		final JDFNode root = jdfDoc.getJDFRoot();
		final String jpID = xjdf.getAttribute(AttributeName.JOBPARTID, null, null);
		JDFNode n = jpID == null ? null : root.getJobPart(jpID, null);
		if (n == null)
		{
			if (!root.hasAttribute(AttributeName.TYPE))
			{
				return root;
			}
			if (jpID == null)
			{
				final VElement nodes = root.getvJDFNode(null, null, false);
				final VString xTypes = StringUtil.tokenize(xjdf.getAttribute(AttributeName.TYPES), null, false);
				for (int i = 0; i < nodes.size(); i++)
				{
					final JDFNode n2 = (JDFNode) nodes.get(i);
					final VString vtypes = n2.getAllTypes();
					if (vtypes.containsAll(xTypes))
					{
						return n2;
					}
				}
			}
		}
		if (n == null && create)
		{
			n = root.addProcessGroup(new VString(xjdf.getAttribute(AttributeName.TYPES), null));
		}
		return n;
	}

	/**
	 * @param toCheck
	 * @return
	 */
	boolean isXResourceElement(final KElement toCheck)
	{
		boolean bReturn = false;

		if (toCheck != null)
		{
			final KElement parent = toCheck.getParentNode_KElement();
			if (parent == null)
			{
				return bReturn;
			}

			final KElement parent2 = parent.getParentNode_KElement();
			if (parent2 == null)
			{
				return bReturn;
			}

			final String parentName = parent2.getLocalName();
			boolean bL1 = parentName.endsWith("Set") && toCheck.getNodeName().equals(parent2.getAttribute("Name"));
			bL1 = bL1 || parentName.equals("Product") && toCheck.getNodeName().equals(parent.getAttribute("Name"));
			bReturn = bL1;
		}

		return bReturn;
	}

	/**
	 * @param toCheck
	 * @return
	 */
	boolean isXResource(final KElement toCheck)
	{
		final KElement parent = toCheck.getParentNode_KElement();
		if (parent == null)
		{
			return false;
		}

		final boolean bL1 = parent.getLocalName().endsWith("Set");
		return bL1 && parent.hasAttribute(AttributeName.NAME);
	}

	/**
	 * make a separationlist from an attribute
	 * @param rPart
	 * @param elem the separation list attribute / element
	 */
	protected JDFSeparationList createSeparationList(final KElement rPart, final String elem)
	{
		final String c = rPart.getAttribute(elem, null, null);
		JDFSeparationList sepList = null;
		if (c != null)
		{
			sepList = (JDFSeparationList) rPart.getCreateElement(elem);
			sepList.setSeparations(new VString(c, null));
			rPart.removeAttribute(elem);
		}
		return sepList;
	}

	/**
	 * make sure we have a product in case we have multiple nodes
	 * @param theNode
	 * @return
	 */
	protected JDFNode createProductRoot(JDFNode theNode)
	{
		final JDFNode parent = (JDFNode) jdfDoc.createElement("JDF");
		parent.setType(EnumType.Product);
		theNode = (JDFNode) parent.moveElement(theNode, null);
		jdfDoc.appendChild(parent);

		parent.moveAttribute(AttributeName.JOBID, theNode);
		parent.moveAttribute(AttributeName.VERSION, theNode);
		parent.setJobPartID("rootPart");
		parent.moveElement(theNode.getResourcePool(), null);

		final JDFComponent c = (JDFComponent) parent.addResource(ElementName.COMPONENT, EnumUsage.Output);
		c.setDescriptiveName("dummy output");
		c.setComponentType(EnumComponentType.FinalProduct, null);

		mergeProductLinks(theNode, parent);
		firstConvert = true;
		return parent;
	}

	/**
	 * @param e2
	 */
	protected void convertUnits(final KElement e2)
	{
		if (convertUnits)
		{
			final JDFAttributeMap map = e2.getAttributeMap();
			final Iterator<String> keyIt = map.getKeyIterator();
			final UnitParser up = new UnitParser();
			while (keyIt.hasNext())
			{
				final String key = keyIt.next();
				final String val = map.get(key);
				final String newVal = up.extractUnits(val);
				if (!val.equals(newVal))
				{
					e2.setAttribute(key, newVal);
				}
				//update dates in case they were specified in milliseconds
				if ((e2 instanceof JDFElement) && EnumAttributeType.dateTime.equals(((JDFElement) e2).getAttributeInfo().getAttributeType(key)))
				{
					JDFDate d = JDFDate.createDate(val);
					if (d != null && !val.equals(d.getDateTimeISO()))
						e2.setAttribute(key, d.getDateTimeISO());
				}
			}
		}
	}

	/**
	 * @param theNode
	 * @param parent
	 */
	private void mergeProductLinks(final JDFNode theNode, final JDFNode parent)
	{
		mergeProductLink(theNode, parent, ElementName.CUSTOMERINFO, EnumUsage.Input);
		mergeProductLink(theNode, parent, ElementName.NODEINFO, EnumUsage.Input);
		final JDFResource r = parent.getResource(ElementName.COMPONENT, EnumUsage.Output, 0);
		if (r != null && "dummy outout".equals(r.getDescriptiveName()))
		{
			final JDFResource rNode = theNode.getResource(ElementName.COMPONENT, EnumUsage.Output, 0);
			if (rNode != null)
			{
				parent.getLink(r, EnumUsage.Output).deleteNode();
				r.deleteNode();
			}
		}
		mergeProductLink(theNode, parent, ElementName.COMPONENT, EnumUsage.Output);
	}

	/**
	 * @param theNode
	 * @param parent
	 * @param resName
	 * @param enumUsage
	 */
	private void mergeProductLink(final JDFNode theNode, final JDFNode parent, final String resName, final EnumUsage enumUsage)
	{
		final JDFResource r = parent.getResource(resName, enumUsage, 0);

		if (r == null)
		{
			final JDFResourceLink link = theNode.getLink(0, resName, new JDFAttributeMap("Usage", enumUsage), null);
			if (link != null)
			{
				parent.ensureLink(link.getLinkRoot(), enumUsage, null);
			}
		}
	}

	/**
	 * @param version the version to set
	 */
	public void setVersion(final EnumVersion version)
	{
		this.version = version;
	}

	/**
	 * @return the version
	 */
	public EnumVersion getVersion()
	{
		return version;
	}

	/**
	 * 
	 * @param e
	 * @param trackElem
	 */
	protected void attributesToSpan(final KElement e)
	{
		final JDFAttributeMap map = e.getAttributeMap();
		final JDFElement ir = (JDFElement) e;
		final VString keys = map.getKeys();
		final VString knownElements = ir.knownElements();
		for (final String name : keys)
		{
			if (knownElements.contains(name))
			{
				final KElement subElem = ir.appendElement(name);
				subElem.setAttribute("Actual", map.get(name));
				convertUnits(subElem);
				e.removeAttribute(name);
			}
		}
	}

	/**
	 * @param rl
	 * @param partmap
	 * @param map
	 * @param a
	 */
	protected void moveToLink(final JDFResourceLink rl, final JDFAttributeMap partmap, final JDFAttributeMap map, final String a)
	{
		if (map == null || map.isEmpty())
		{
			return; // nop
		}
		final VString vGW = new VString("Good Waste", null);
		for (final String gw : vGW)
		{
			final JDFAttributeMap pm = new JDFAttributeMap(partmap);
			pm.put("Condition", gw);
			if (map.get(a + gw) != null)
			{
				if (rl != null)
				{
					rl.setAmountPoolAttribute(a, map.get(a + gw), null, pm);
				}
				map.remove(a + gw);
			}
		}
	}

	/**
	 * @param partmap
	 * @param map
	 * @param rl
	 */
	protected void moveAmountsToLink(final JDFAttributeMap partmap, final JDFAttributeMap map, final JDFResourceLink rl)
	{
		moveToLink(rl, partmap, map, AttributeName.AMOUNT);
		moveToLink(rl, partmap, map, AttributeName.ACTUALAMOUNT);
		moveToLink(rl, partmap, map, AttributeName.MAXAMOUNT);
	}

	/**
	 * 
	 * @author Rainer Prosi, Heidelberger Druckmaschinen
	 * 
	 */
	protected class WalkXElement extends BaseWalker
	{

		/**
		 *  
		 */
		@SuppressWarnings("synthetic-access")
		public WalkXElement()
		{
			super(getFactory());
		}

		/**
		 * @param e
		 * @return element to continue with if must continue
		 */
		@Override
		public KElement walk(final KElement e, KElement trackElem)
		{
			final VElement v = trackElem.getChildElementVector(null, null);
			for (int i = 0; i < v.size(); i++)
			{
				final KElement kk = v.get(i);
				if (e.isEqual(kk))
				{
					return null;
				}
			}
			cleanRefs(e, trackElem);

			// dirty, dirty but needed in case of inherited inline resources
			if (isXResourceElement(e))
			{
				trackElem.setAttributes(e);
			}
			else
			{
				final KElement e2 = trackElem.copyElement(e, null);
				convertUnits(e2);
				fixNamespace(e2);
				e2.removeChildren(null, null, null); // will be copied later
				trackElem = e2;
			}
			convertUnits(trackElem);
			return trackElem;
		}

		/**
		 * move namespace to 1.1 for all 2.x values
		 * @param e2
		 */
		private void fixNamespace(KElement e2)
		{
			String namespace = e2.getNamespaceURI();
			if (JDFElement.getSchemaURL(2, 0).equals(StringUtil.leftStr(namespace, -1) + 0))
			{
				e2.setNamespaceURI(JDFElement.getSchemaURL(1, 1));
			}
		}

		/**
		 * @param val
		 * @return
		 */
		protected String getRefName(final String val)
		{
			final String refName = val.endsWith("Refs") ? StringUtil.leftStr(val, -1) : val;
			return refName;
		}

		/**
		 * @param e
		 * @param trackElem
		 */
		protected void cleanRefs(final KElement e, final KElement trackElem)
		{
			final JDFAttributeMap map = e.getAttributeMap();
			if (map == null)
			{
				return;
			}
			final VString keys = map.getKeys();
			if (keys != null)
			{
				final int keySize = keys.size();
				for (int i = 0; i < keySize; i++)
				{
					final String val = keys.get(i);
					if ((val.endsWith("Ref") || val.endsWith("Refs")) && !val.equals("rRef"))
					{
						final String values = map.get(val);
						cleanRef(e, trackElem, val, values);
					}
				}
			}
		}

		protected void cleanRef(final KElement e, final KElement trackElem, final String val, final String values)
		{
			final VString vValues = StringUtil.tokenize(values, null, false);
			for (final String value : vValues)
			{
				final IDPart p = idMap.get(value);
				if (p != null)
				{
					final String refName = getRefName(val);
					if (refName != null)
					{
						final KElement refOld = trackElem != null ? trackElem.getElement(refName) : null;
						final KElement ref = e.appendElement(refName);
						ref.setAttribute("rRef", p.getID());

						final VJDFAttributeMap vpartmap = p.getPartMap();
						if (vpartmap != null)
						{
							for (int j = 0; j < vpartmap.size(); j++)
							{
								ref.appendElement(ElementName.PART).setAttributes(vpartmap.get(j));
							}
						}
						// we've been here already
						if (ref.isEqual(refOld))
						{
							ref.deleteNode();
						}
					}
					e.removeAttribute(val);
				}
			}
		}
	}

	/**
	 * @author Rainer Prosi, Heidelberger Druckmaschinen walker for the xjdf root
	 */
	public class WalkXJDF extends WalkXElement
	{
		// ///////////////////////////////////////////////////////////////////////////////
		/**
		 * @param e
		 * @return true if must continue
		 */
		@Override
		public KElement walk(final KElement e, final KElement trackElem)
		{
			currentJDFNode = (JDFNode) trackElem;
			currentJDFNode.setAttributes(e);
			currentJDFNode.setVersion(getVersion());
			currentJDFNode.setStatus(EnumNodeStatus.Part);
			removeInheritedJobID();
			setTypes();
			return currentJDFNode;
		}

		/**
		 * 
		 */
		private void setTypes()
		{
			String t = currentJDFNode.getAttribute("Types");
			if ("Product".equals(t))
			{
				currentJDFNode.setType(EnumType.Product);
				currentJDFNode.removeAttribute("Types");
			}
			else
				currentJDFNode.setType(EnumType.ProcessGroup);
		}

		/**
		 *  
		 */
		private void removeInheritedJobID()
		{
			final JDFNode parent = currentJDFNode.getParentJDF();
			if (parent != null)
			{
				final String jobID = StringUtil.getNonEmpty(parent.getJobID(true));
				final String myJobID = StringUtil.getNonEmpty(currentJDFNode.getJobID(false));
				if (myJobID != null && myJobID.equals(jobID))
				{
					currentJDFNode.removeAttribute(AttributeName.JOBID);
				}
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
			return super.matches(toCheck) && XJDF20.rootName.equals(toCheck.getLocalName());
		}
	}

	/**
	 * @author Rainer Prosi, Heidelberger Druckmaschinen walker for the xjdf root
	 */
	public class WalkSpan extends WalkXElement
	{
		// ///////////////////////////////////////////////////////////////////////////////
		/**
		 * invert XXXSpan/@Datatype=foo to FooSpan/@Name=Datatype
		 * @param e
		 * @return true if must continue
		 */
		@Override
		public KElement walk(final KElement e, final KElement trackElem)
		{
			final KElement eNew = trackElem.appendElement(e.getAttribute("Name"));
			eNew.setAttributes(e);
			convertUnits(eNew);
			eNew.removeAttribute(AttributeName.NAME);
			eNew.setAttribute(AttributeName.DATATYPE, e.getLocalName());
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
			return toCheck.getLocalName().endsWith("Span");
		}
	}

	/**
	 * @author Rainer Prosi, Heidelberger Druckmaschinen walker for the various resource sets
	 */
	public class WalkSet extends WalkXElement
	{
		/**
		 * @param e
		 * @return the created resource
		 */
		@Override
		public KElement walk(final KElement e, final KElement trackElem)
		{
			final JDFNode parent = (JDFNode) trackElem;
			final JDFNode root = parent.getJDFRoot();
			EnumUsage inOut = EnumUsage.getEnum(e.getAttribute(AttributeName.USAGE));
			final String id = e.getAttribute(AttributeName.ID, null, null);
			if (inOut == null && "ParameterSet".equals(e.getLocalName()))
			{
				final SetHelper h = new SetHelper(e);
				final String name = h.getName();
				if (!ElementName.CONTACT.equals(name) && !ElementName.LAYOUTELEMENT.equals(name) && !ElementName.RUNLIST.equals(name))
				{
					inOut = EnumUsage.Input;
				}
			}

			JDFResource r = null;
			final KElement idElem = root.getCreateResourcePool().getChildWithAttribute(null, "ID", null, id, 0, true);
			if (idElem instanceof JDFResource)
			{
				r = (JDFResource) idElem;
			}
			else
			{
				r = (JDFResource) root.getChildWithAttribute(null, "ID", null, id, 0, false);
				if (r != null)
				{
					final JDFResourcePool rp = root.getCreateResourcePool();
					if (!rp.equals(r.getParentNode_KElement()))
					{
						rp.moveElement(r, null);
					}
				}
			}
			if (r == null)
			{
				final SetHelper h = new SetHelper(e);
				final String name = h.getName();
				if (name != null)
				{
					r = root.addResource(name, null);
					r.removeAttribute(AttributeName.STATUS); // don't want the default
				}
			}
			if (r != null)
			{
				r.setAttributes(e);
				if (r.getResourceClass() == null)
				{
					final String name = StringUtil.leftStr(e.getLocalName(), -3);
					r.setResourceClass("Parameter".equals(name) ? EnumResourceClass.Parameter : EnumResourceClass.Handling);
				}
				if (inOut != null)
				{
					final JDFResourceLink rl = parent.ensureLink(r, inOut, null);
					if (rl != null)
					{
						rl.setrRef(id);
						r.removeAttribute(AttributeName.USAGE);
						rl.moveAttribute(AttributeName.PROCESSUSAGE, r);
						rl.moveAttribute(AttributeName.AMOUNT, r);
						rl.moveAttribute(AttributeName.ACTUALAMOUNT, r);
						rl.moveAttribute(AttributeName.MAXAMOUNT, r);
						rl.moveAttribute(AttributeName.MINAMOUNT, r);
					}
				}

				// not linked are also available - they will typically be referenced resources
				if (!r.hasAttribute(AttributeName.STATUS))
				{
					r.setResStatus(EnumUsage.Output.equals(inOut) ? EnumResStatus.Unavailable : EnumResStatus.Available, true);
				}

				r.removeAttribute(AttributeName.NAME);
				r.removeAttribute(AttributeName.USAGE);
			}
			return r;
		}

		/**
		 * @see org.cip4.jdflib.elementwalker.BaseWalker#matches(org.cip4.jdflib.core.KElement)
		 * @param toCheck
		 * @return true if it matches
		 */
		@Override
		public boolean matches(final KElement toCheck)
		{
			final KElement parent = toCheck.getParentNode_KElement();
			final boolean bL1 = parent != null && (parent.getLocalName().equals(XJDF20.rootName) || parent.getLocalName().equals("Product"));
			final String localName = toCheck.getLocalName();
			return bL1 && super.matches(toCheck) && localName.endsWith("Set")
					&& (toCheck.hasAttribute(AttributeName.NAME) || toCheck.getElement(StringUtil.leftStr(localName, -3)) != null);
		}

	}

	/**
	 * @author Rainer Prosi, Heidelberger Druckmaschinen walker for the various resource sets
	 */
	public class WalkResource extends WalkXElement
	{
		/**
		 * @param e
		 * @return the created resource
		 */
		@Override
		public KElement walk(final KElement e, KElement trackElem)
		{
			e.removeAttribute("Class");
			return super.walk(e, trackElem);
		}

		/**
		 * @see org.cip4.jdflib.elementwalker.BaseWalker#matches(org.cip4.jdflib.core.KElement)
		 * @param toCheck
		 * @return true if it matches
		 */
		@Override
		public boolean matches(final KElement toCheck)
		{
			// test on grandparent
			return super.matches(toCheck) && isXResourceElement(toCheck);
		}
	}

	/**
	 * TODO discuss and implement varying numcolors for front and back, e.g. 4/1
	  * @author Rainer Prosi, Heidelberger Druckmaschinen *
	 */
	public class WalkColorIntent extends WalkIntentResource
	{
		/**
		 * @param e
		 * @return the created resource
		 */
		@Override
		public KElement walk(final KElement e, final KElement trackElem)
		{
			if (e.hasAttribute("ColorsUsed"))
			{
				evaluateColorsUsed(e);
			}
			if (e.hasAttribute("NumColors") && !e.hasAttribute("ColorsUsed"))
			{
				evaluateNumColors(e, trackElem);
			}
			repartitionSide(e, trackElem);
			return super.walk(e, trackElem);
		}

		/**
		 * repartition in case stuff that is side dependent exists - note that trackEleme is the new element
		 * @param e
		 */
		private void repartitionSide(final KElement e, final KElement trackElem)
		{
			KElement slBack = e.getElement("ColorsUsedBack");
			if (slBack != null)
			{
				JDFResource ciFront = ((JDFResource) trackElem).addPartition(EnumPartIDKey.Side, "Front");
				ciFront.moveElement(e.getElement("ColorsUsed"), null);
				JDFResource ciBack = ((JDFResource) trackElem).addPartition(EnumPartIDKey.Side, "Back");
				ciBack.moveElement(e.getElement("ColorsUsedBack"), null).renameElement("ColorsUsed", null);
			}
		}

		/**
		 * @param e
		 */
		private void evaluateColorsUsed(final KElement e)
		{
			JDFSeparationList sl = createSeparationList(e, "ColorsUsed");
			if (e.hasAttribute("ColorsUsedBack"))
			{
				if (sl != null)
					sl = (JDFSeparationList) sl.deleteNode();
				e.renameAttribute("ColorsUsedBack", "ColorsUsed", null, null);
				JDFSeparationList slBack = createSeparationList(e, "ColorsUsed");
				slBack.renameElement("ColorsUsedBack", null);
				if (sl != null)
					e.moveElement(sl, slBack);
			}
		}

		/**
		 * @param e
		 * @param trackElem
		 */
		private void evaluateNumColors(final KElement e, final KElement trackElem)
		{
			JDFXYPair xyp;
			try
			{
				xyp = new JDFXYPair(e.getAttribute("NumColors", null, null));
			}
			catch (DataFormatException e1)
			{
				xyp = null;
			}
			if (xyp != null)
			{
				double[] list = xyp.getDoubleList();
				e.removeAttribute("NumColors");
				for (int i = list.length - 1; i >= 0; i--)
				{
					int n = (int) (list[i] + 0.5);
					if (n > 0)
					{
						VString v = new VString("Black Cyan Magenta Yellow Spot1 Spot2 Spot3 Spot4", null);
						while (v.size() > n)
							v.remove(n);
						JDFSeparationList newElem = ((JDFSeparationList) trackElem.appendElement(ElementName.COLORSUSED));
						newElem.setSeparations(v);
						if (i == 1)
							newElem.renameElement(ElementName.COLORSUSED + "Back", null);
					}
				}
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
			return super.matches(toCheck) && (toCheck instanceof JDFColorIntent);
		}
	}

	/**
	 * 
	 * @author Dr. Rainer Prosi, Heidelberger Druckmaschinen AG
	 * 
	 * Mar 17, 2010
	 */
	public class WalkIntentResource extends WalkResource
	{
		/**
		 * @param e
		 * @return the created resource
		 */
		@Override
		public KElement walk(final KElement e, final KElement trackElem)
		{
			attributesToSpan(e);
			return super.walk(e, trackElem);
		}

		/**
		 * @see org.cip4.jdflib.elementwalker.BaseWalker#matches(org.cip4.jdflib.core.KElement)
		 * @param toCheck
		 * @return true if it matches
		 */
		@Override
		public boolean matches(final KElement toCheck)
		{
			return super.matches(toCheck) && (toCheck instanceof JDFIntentResource);
		}
	}

	/**
	 * 
	 * @author Dr. Rainer Prosi, Heidelberger Druckmaschinen AG
	 * 
	 * Mar 17, 2010
	 */
	public class WalkIntentElement extends WalkXElement
	{
		/**
		 * @param e
		 * @return the created element
		 */
		@Override
		public KElement walk(final KElement e, final KElement trackElem)
		{
			attributesToSpan(e);
			return super.walk(e, trackElem);
		}

		/**
		 * @see org.cip4.jdflib.elementwalker.BaseWalker#matches(org.cip4.jdflib.core.KElement)
		 * @param toCheck
		 * @return true if it matches
		 */
		@Override
		public boolean matches(final KElement toCheck)
		{
			return (toCheck instanceof JDFDropIntent) || (toCheck instanceof JDFDropItemIntent) || (toCheck instanceof JDFArtDelivery) || (toCheck instanceof JDFBindItem)
					|| (toCheck instanceof JDFChannelBinding) || (toCheck instanceof JDFCoilBinding) || (toCheck instanceof JDFEdgeGluing)
					|| (toCheck instanceof JDFHardCoverBinding) || (toCheck instanceof JDFPlasticCombBinding) || (toCheck instanceof JDFRingBinding)
					|| (toCheck instanceof JDFSaddleStitching) || (toCheck instanceof JDFSideSewing) || (toCheck instanceof JDFSoftCoverBinding)
					|| (toCheck instanceof JDFStripBinding) || (toCheck instanceof JDFTabs) || (toCheck instanceof JDFThreadSealing) || (toCheck instanceof JDFThreadSewing)
					|| (toCheck instanceof JDFEmbossingItem) || (toCheck instanceof JDFInsert) || (toCheck instanceof JDFNumberItem) || (toCheck instanceof JDFProofItem)
					|| (toCheck instanceof JDFShapeCut);
		}
	}

	/**
	 * @author Rainer Prosi, Heidelberger Druckmaschinen walker for the various resource sets
	 */
	public class WalkReplace extends WalkXElement
	{
		/**
		 * @param e
		 * @return the created resource
		 */
		@Override
		public KElement walk(final KElement e, final KElement trackElem)
		{
			trackElem.removeChildren(e.getNodeName(), null, null);
			return super.walk(e, trackElem);
		}

		/**
		 * @see org.cip4.jdflib.elementwalker.BaseWalker#matches(org.cip4.jdflib.core.KElement)
		 * @param toCheck
		 * @return true if it matches
		 */
		@Override
		public boolean matches(final KElement toCheck)
		{
			return super.matches(toCheck) && (toCheck instanceof JDFAuditPool);
		}

	}

	/**
	 * @author Rainer Prosi, Heidelberger Druckmaschinen walker for the various resource sets
	 */
	public class WalkProduct extends WalkXElement
	{
		/**
		 * @param e
		 * @return the created resource
		 */
		@Override
		public KElement walk(final KElement e, final KElement trackElem)
		{
			JDFNode theNode = (JDFNode) trackElem;
			if ("Product".equals(theNode.getType()))
			{
				if (theNode != currentJDFNode && !firstproductInList)
					theNode = theNode.addProduct();
			}
			else
			{
				theNode = createProductRoot(theNode);
			}
			firstproductInList = false;
			theNode.setAttributes(e);
			fixComponent(theNode, e);
			return theNode;
		}

		/**
		 * @param theNode
		 * @param xjdfProduct
		 */
		private void fixComponent(final JDFNode theNode, final KElement xjdfProduct)
		{
			JDFComponent c = (JDFComponent) theNode.getResource(ElementName.COMPONENT, EnumUsage.Output, 0);
			if (c == null)
			{
				c = (JDFComponent) theNode.addResource(ElementName.COMPONENT, EnumUsage.Output);
				final EnumComponentType partialFinal = new ProductHelper(xjdfProduct).isRootProduct() ? EnumComponentType.FinalProduct : EnumComponentType.PartialProduct;
				c.setComponentType(partialFinal, null);
			}
			c.moveAttribute(AttributeName.PRODUCTTYPE, theNode);
			c.moveAttribute(AttributeName.PRODUCTTYPEDETAILS, theNode);
			final JDFResourceLink rl = theNode.getLink(c, EnumUsage.Output);
			if (rl != null)
			{
				rl.moveAttribute(AttributeName.AMOUNT, theNode);
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
			return super.matches(toCheck) && (toCheck.getLocalName().equals("Product"));
		}

	}

	/**
	 * @author Rainer Prosi, Heidelberger Druckmaschinen walker for the various resource sets
	 */
	public class WalkProductList extends WalkXElement
	{
		/**
		 * 
		 */
		public WalkProductList()
		{
			super();
		}

		/**
		 * @param e
		 * @return the root, else null if we are in a second pass
		 */
		@Override
		public KElement walk(final KElement e, final KElement trackElem)
		{
			e.deleteNode();
			final boolean bFirst = foundProduct;
			foundProduct = true;
			// only convert products in the first pass
			// TODO rethink product conversion switch
			if (createProduct && !bFirst && e.numChildElements("Product", null) > 1)
			{
				createProductRoot(currentJDFNode);
			}
			KElement theReturn = currentJDFNode;
			if (!"Product".equals(currentJDFNode.getType()))
				theReturn = jdfDoc.getJDFRoot();
			return createProduct && !bFirst ? theReturn : null;
		}

		/**
		 * @see org.cip4.jdflib.elementwalker.BaseWalker#matches(org.cip4.jdflib.core.KElement)
		 * @param toCheck
		 * @return true if it matches
		 */
		@Override
		public boolean matches(final KElement toCheck)
		{
			return super.matches(toCheck) && (toCheck.getLocalName().equals("ProductList"));
		}
	}

	/**
	 * continue walking on these withot copying e
	 * @author Rainer Prosi, Heidelberger Druckmaschinen walker for the various resource sets
	 */
	public class WalkContinue extends WalkXElement
	{
		/**
		 * @param e
		 * @return the created resource
		 */
		@Override
		public KElement walk(final KElement e, final KElement trackElem)
		{
			return trackElem;
		}

		/**
		 * @see org.cip4.jdflib.elementwalker.BaseWalker#matches(org.cip4.jdflib.core.KElement)
		 * @param toCheck
		 * @return true if it matches
		 */
		@Override
		public boolean matches(final KElement toCheck)
		{
			// final String name = toCheck.getLocalName();
			return false;
		}

	}

	/**
	 * simply stop walking on these
	 * @author Rainer Prosi, Heidelberger Druckmaschinen walker for the various resource sets
	 */
	public class WalkIgnore extends WalkXElement
	{
		/**
		 * @param e
		 * @return the created resource
		 */
		@Override
		public KElement walk(final KElement e, final KElement trackElem)
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
			return super.matches(toCheck) && (toCheck instanceof JDFPart) && isXResource(toCheck.getParentNode_KElement());
		}

	}

	/**
	 * @author Rainer Prosi, Heidelberger Druckmaschinen walker for the various resource sets
	 */
	public class WalkXJDFColorResource extends WalkXJDFResource
	{
		/**
		 * @param e
		 * @param trackElem
		 * @return
		 */
		@Override
		protected JDFResource createPartition(final KElement e, final KElement trackElem, final JDFPart part)
		{
			final JDFNode theNode = ((JDFElement) trackElem).getParentJDF();
			final JDFResource r = (JDFResource) trackElem;
			final String sep = part.getSeparation();
			final KElement col = r.getChildWithAttribute("Color", "Name", null, sep, 0, true);
			if (col != null)
			{
				return null; // been here already
			}
			final JDFResource rPart = r.getCreatePartition(part.getPartMap(), part.guessPartIDKeys());
			final JDFResourceLink rll = theNode.getLink(r, null);
			if (rll != null)
			{
				rll.removeChildren(ElementName.PART, null, null);
			}
			rPart.renameElement(ElementName.COLOR, null);
			rPart.renameAttribute(AttributeName.SEPARATION, AttributeName.NAME, null, null);
			r.removeFromAttribute(AttributeName.PARTIDKEYS, AttributeName.SEPARATION, null, " ", -1);
			return rPart;
		}

		/**
		 * @see org.cip4.jdflib.elementwalker.BaseWalker#matches(org.cip4.jdflib.core.KElement)
		 * @param toCheck
		 * @return true if it matches
		 */
		@Override
		public boolean matches(final KElement toCheck)
		{
			final KElement parent = toCheck.getParentNode_KElement();
			if (parent == null)
			{
				return false;
			}

			final boolean bL1 = parent.getLocalName().endsWith("Set");
			return bL1 && super.matches(toCheck) && ElementName.COLOR.equals(parent.getAttribute(AttributeName.NAME));
		}

		/**
		 * @see org.cip4.jdflib.extensions.xjdfwalker.XJDFToJDFConverter.WalkXJDFResource#walk(org.cip4.jdflib.core.KElement, org.cip4.jdflib.core.KElement)
		 */
		@Override
		public KElement walk(final KElement e, final KElement trackElem)
		{
			final KElement rPart = super.walk(e, trackElem);
			if (rPart != null)
			{
				rPart.removeAttribute(AttributeName.STATUS);
			}
			return rPart;

		}

	}

	/**
	 * @author Rainer Prosi, Heidelberger Druckmaschinen walker for the various resource sets
	 */
	public class WalkXJDFResource extends WalkXElement
	{

		/**
		 * @param e
		 * @return the created resource
		 */
		@Override
		public KElement walk(final KElement e, final KElement trackElem)
		{
			final JDFNode theNode = currentJDFNode == null ? ((JDFElement) trackElem).getParentJDF() : currentJDFNode;
			final JDFPart part = (JDFPart) e.getElement(ElementName.PART);
			JDFAttributeMap partmap = null;
			final JDFResource newPartition;
			if (part != null)
			{
				newPartition = createPartition(e, trackElem, part);
				partmap = part.getPartMap();
			}
			else
			{
				newPartition = (JDFResource) trackElem;
			}
			if (newPartition == null)
			{
				return null;
			}

			final JDFAttributeMap map = e.getAttributeMap();
			map.remove(AttributeName.ID);
			final JDFResourceLink rl = theNode.getLink(newPartition, null);
			moveAmountsToLink(partmap, map, rl);
			newPartition.setAttributes(map);

			return newPartition;
		}

		/**
		 * @param e
		 * @param trackElem
		 * @param part
		 * @return
		 */
		protected JDFResource createPartition(final KElement e, final KElement trackElem, final JDFPart part)
		{
			final JDFNode theNode = currentJDFNode == null ? ((JDFElement) trackElem).getParentJDF() : currentJDFNode;
			final JDFResource r = (JDFResource) trackElem;
			final JDFAttributeMap partMap = part.getPartMap();
			final JDFResource rPart = r.getCreatePartition(partMap, part.guessPartIDKeys());
			final JDFResourceLink rll = theNode.getLink(r, null);
			final VJDFAttributeMap partMapVector = rll != null ? rll.getPartMapVector() : null;
			if (rll != null && (partMapVector == null || !partMapVector.contains(partMap)))
			{
				rll.moveElement(part, null);
			}
			return rPart;
		}

		/**
		 * @see org.cip4.jdflib.elementwalker.BaseWalker#matches(org.cip4.jdflib.core.KElement)
		 * @param toCheck
		 * @return true if it matches
		 */
		@Override
		public boolean matches(final KElement toCheck)
		{
			return super.matches(toCheck) && isXResource(toCheck);
		}
	}

	/**
	 * @author Rainer Prosi, Heidelberger Druckmaschinen walker for the various resource sets
	 */
	public class WalkXJDFColorSet extends WalkSet
	{
		/**
		 * @see org.cip4.jdflib.elementwalker.BaseWalker#matches(org.cip4.jdflib.core.KElement)
		 * @param toCheck
		 * @return true if it matches
		 */
		@Override
		public boolean matches(final KElement toCheck)
		{
			return super.matches(toCheck) && ElementName.COLOR.equals(toCheck.getAttribute(AttributeName.NAME));
		}

		/**
		 * @see org.cip4.jdflib.extensions.xjdfwalker.XJDFToJDFConverter.WalkXElement#walk(org.cip4.jdflib.core.KElement, org.cip4.jdflib.core.KElement)
		 */
		@Override
		public KElement walk(final KElement e, final KElement trackElem)
		{
			final JDFNode theNode = (JDFNode) trackElem;
			final KElement k2 = super.walk(e, trackElem);
			final JDFResource r = (JDFResource) k2;
			final JDFResourceLink rl = theNode.getLink(r, null);
			r.renameElement(ElementName.COLORPOOL, null);
			if (rl != null)
			{
				rl.renameElement("ColorPoolLink", null);
			}
			return k2;
		}
	}

	/**
	 * @author Rainer Prosi, Heidelberger Druckmaschinen walker for Media elements
	 */
	public class WalkStrippingParams extends WalkResource
	{
		/**
		 * @see org.cip4.jdflib.elementwalker.BaseWalker#matches(org.cip4.jdflib.core.KElement)
		 * @param toCheck
		 * @return true if it matches
		 */
		@Override
		public boolean matches(final KElement toCheck)
		{
			return toCheck instanceof JDFStrippingParams;
		}

		/**
		 * @see org.cip4.jdflib.extensions.xjdfwalker.XJDFToJDFConverter.WalkXElement#getRefName(java.lang.String)
		 */
		@Override
		protected String getRefName(final String val)
		{
			if ("PaperRef".equals(val) || "PlateRef".equals(val) || "ProofRef".equals(val))
			{
				return "MediaRef";
			}
			return super.getRefName(val);
		}

	}

	/**
	 * @author Rainer Prosi, Heidelberger Druckmaschinen walker for Media elements
	 */
	public class WalkMedia extends WalkResource
	{
		/**
		 * @see org.cip4.jdflib.elementwalker.BaseWalker#matches(org.cip4.jdflib.core.KElement)
		 * @param toCheck
		 * @return true if it matches
		 */
		@Override
		public boolean matches(final KElement toCheck)
		{
			return toCheck instanceof JDFMedia;
		}

		/**
		 * @see org.cip4.jdflib.extensions.xjdfwalker.XJDFToJDFConverter.WalkXJDFResource#walk(org.cip4.jdflib.core.KElement, org.cip4.jdflib.core.KElement)
		 */
		@Override
		public KElement walk(final KElement e, final KElement trackElem)
		{
			final KElement rPart = super.walk(e, trackElem);
			if (rPart != null)
			{
				final JDFResource root = ((JDFResource) rPart).getResourceRoot();
				if (root != null && root != rPart && !root.hasAttribute(AttributeName.MEDIATYPE))
				{
					root.copyAttribute(AttributeName.MEDIATYPE, rPart);
				}
			}
			return rPart;
		}
	}

	/**
	 * @author Rainer Prosi, Heidelberger Druckmaschinen walker for Media elements
	 */
	public class WalkColorantControl extends WalkResource
	{
		/**
		 * @see org.cip4.jdflib.elementwalker.BaseWalker#matches(org.cip4.jdflib.core.KElement)
		 * @param toCheck
		 * @return true if it matches
		 */
		@Override
		public boolean matches(final KElement toCheck)
		{
			return toCheck instanceof JDFColorantControl;
		}

		/**
		 * @see org.cip4.jdflib.extensions.xjdfwalker.XJDFToJDFConverter.WalkXJDFResource#walk(org.cip4.jdflib.core.KElement, org.cip4.jdflib.core.KElement)
		 */
		@Override
		public KElement walk(final KElement e, final KElement trackElem)
		{
			final KElement rPart = super.walk(e, trackElem);
			createSeparationList(rPart, ElementName.COLORANTPARAMS);
			createSeparationList(rPart, ElementName.COLORANTORDER);
			createSeparationList(rPart, ElementName.DEVICECOLORANTORDER);
			return rPart;
		}
	}

	/**
	 * @author Rainer Prosi, Heidelberger Druckmaschinen walker for Media elements
	 */
	public class WalkRunList extends WalkResource
	{
		/**
		 * @see org.cip4.jdflib.elementwalker.BaseWalker#matches(org.cip4.jdflib.core.KElement)
		 * @param toCheck
		 * @return true if it matches
		 */
		@Override
		public boolean matches(final KElement toCheck)
		{
			return toCheck instanceof JDFRunList;
		}

		/**
		 * @see org.cip4.jdflib.extensions.xjdfwalker.XJDFToJDFConverter.WalkXJDFResource#walk(org.cip4.jdflib.core.KElement, org.cip4.jdflib.core.KElement)
		 */
		@Override
		public KElement walk(final KElement e, final KElement trackElem)
		{
			splitLayoutElem(e);
			return super.walk(e, trackElem);
		}

		/**
		 * split a RunList into a RunList and a RunList/LayoutElement
		 * @param e
		 */
		private void splitLayoutElem(final KElement e)
		{
			if (!e.hasChildElement(ElementName.LAYOUTELEMENT, null))
			{
				final JDFElement loe = (JDFElement) e.appendElement(ElementName.LAYOUTELEMENT);
				final VString vAtt = loe.knownAttributes();
				vAtt.appendUnique(ElementName.DEPENDENCIES);
				final JDFAttributeMap map = e.getAttributeMap();
				final Iterator<String> it = map.getKeyIterator();
				while (it.hasNext())
				{
					final String s = it.next();
					if (vAtt.contains(s))
					{
						loe.setAttribute(s, map.get(s));
						e.removeAttribute(s);
					}
				}
				final VString vElm = loe.knownElements();
				final VElement vMyElm = e.getChildElementVector(null, null);
				for (int i = 0; i < vMyElm.size(); i++)
				{
					if (vElm.contains(vMyElm.get(i).getLocalName()))
					{
						loe.moveElement(vMyElm.get(i), null);
					}
				}
			}
		}
	}

	/**
	 * @author Rainer Prosi, Heidelberger Druckmaschinen walker for Media elements
	 */
	public class WalkLayoutElement extends WalkResource
	{
		/**
		 * @see org.cip4.jdflib.elementwalker.BaseWalker#matches(org.cip4.jdflib.core.KElement)
		 * @param toCheck
		 * @return true if it matches
		 */
		@Override
		public boolean matches(final KElement toCheck)
		{
			return toCheck instanceof JDFLayoutElement;
		}

		/**
		 * @see org.cip4.jdflib.extensions.xjdfwalker.XJDFToJDFConverter.WalkXJDFResource#walk(org.cip4.jdflib.core.KElement, org.cip4.jdflib.core.KElement)
		 */
		@Override
		public KElement walk(final KElement e, final KElement trackElem)
		{
			if (e.hasAttribute(ElementName.DEPENDENCIES))
				e.appendElement(ElementName.DEPENDENCIES).moveAttribute("RunListRefs", e, ElementName.DEPENDENCIES, null, null);
			return super.walk(e, trackElem);
		}

	}

	/**
	 * @author Rainer Prosi, Heidelberger Druckmaschinen walker for Media elements
	 */
	public class WalkNodeInfo extends WalkResource
	{
		/**
		 * @see org.cip4.jdflib.elementwalker.BaseWalker#matches(org.cip4.jdflib.core.KElement)
		 * @param toCheck
		 * @return true if it matches
		 */
		@Override
		public boolean matches(final KElement toCheck)
		{
			return toCheck instanceof JDFNodeInfo;
		}

		/**
		 * @see org.cip4.jdflib.extensions.xjdfwalker.XJDFToJDFConverter.WalkXJDFResource#walk(org.cip4.jdflib.core.KElement, org.cip4.jdflib.core.KElement)
		 */
		@Override
		public KElement walk(final KElement e, final KElement trackElem)
		{
			final JDFNode theNode = currentJDFNode == null ? ((JDFElement) trackElem).getParentJDF() : currentJDFNode;
			final JDFNodeInfo ni = (JDFNodeInfo) trackElem;
			final JDFAttributeMap partmap = ni.getPartMap();

			final JDFAttributeMap map = e.getAttributeMap();
			final JDFAttributeMap map2 = map.clone();
			final JDFResourceLink rl = theNode.getLink(ni, null);
			moveAmountsToLink(partmap, map, rl);
			map2.removeKeys(map.getKeys());
			e.removeAttributes(map2.getKeys());

			return super.walk(e, trackElem);
		}
	}

	/**
	 * @author Rainer Prosi, Heidelberger Druckmaschinen walker for non partitioned intent elements
	 */
	public class WalkIntent extends WalkXElement
	{
		/**
		 * @param e
		 * @return the created resource
		 */
		@Override
		public KElement walk(final KElement e, final KElement trackElem)
		{
			final JDFNode parent = (JDFNode) trackElem;
			final JDFNode root = parent.getJDFRoot();
			EnumUsage inOut = EnumUsage.getEnum(e.getAttribute(AttributeName.USAGE));
			if (inOut == null)
			{
				inOut = EnumUsage.Input;
			}
			String id = e.getAttribute(AttributeName.ID, null, null);
			if (id == null)
			{
				// we need an id to copy. technically no id is invalid, but... whatever
				id = "r" + KElement.uniqueID(0);
				e.setAttribute(AttributeName.ID, id);
			}
			JDFResource r = null;
			final KElement idElem = parent.getCreateResourcePool().getChildWithAttribute(null, "ID", null, id, 0, true);
			if (idElem instanceof JDFResource)
			{
				r = (JDFResource) idElem;
			}
			else
			{
				r = (JDFResource) root.getChildWithAttribute(null, "ID", null, id, 0, false);
				if (r != null)
				{
					final JDFResourcePool rp = root.getCreateResourcePool();
					if (!rp.equals(r.getParentNode_KElement()))
					{
						rp.moveElement(r, null);
					}
				}
			}
			if (r == null)
			{
				final IntentHelper h = new IntentHelper(e);
				final String name = h.getName();
				if (name != null)
				{
					r = parent.addResource(name, null);
					r.removeAttribute(AttributeName.STATUS);
				}
			}
			if (r != null)
			{
				r.setAttributes(e);
				if (r.getResourceClass() == null)
				{
					r.setResourceClass(EnumResourceClass.Intent);
				}
				final JDFResourceLink rl = parent.ensureLink(r, inOut, null);
				if (rl != null)
				{
					rl.setrRef(id);
					r.removeAttribute(AttributeName.USAGE);
					rl.moveAttribute(AttributeName.PROCESSUSAGE, r);
					rl.moveAttribute(AttributeName.AMOUNT, r);
					rl.moveAttribute(AttributeName.ACTUALAMOUNT, r);
					rl.moveAttribute(AttributeName.MAXAMOUNT, r);
					rl.moveAttribute(AttributeName.MINAMOUNT, r);
				}
				r.removeAttribute(AttributeName.NAME);
				r.removeAttribute(AttributeName.USAGE);
				if (!r.hasAttribute(AttributeName.STATUS))
				{
					r.setResStatus(EnumResStatus.Available, true);
				}
			}
			return r;
		}

		/**
		 * @see org.cip4.jdflib.elementwalker.BaseWalker#matches(org.cip4.jdflib.core.KElement)
		 * @param toCheck
		 * @return true if it matches
		 */
		@Override
		public boolean matches(final KElement toCheck)
		{
			final KElement parent = toCheck.getParentNode_KElement();
			final boolean bL1 = parent != null && parent.getLocalName().equals("Product");
			return bL1 && super.matches(toCheck) && toCheck.getLocalName().equals("Intent");
		}

	}
}
