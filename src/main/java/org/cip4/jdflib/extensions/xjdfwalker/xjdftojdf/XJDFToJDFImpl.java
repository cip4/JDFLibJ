/*
 * The CIP4 Software License, Version 1.0
 *
 *
 * Copyright (c) 2001-2017 The International Cooperation for the Integration of
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
package org.cip4.jdflib.extensions.xjdfwalker.xjdftojdf;

import java.util.Iterator;
import java.util.Map;

import org.cip4.jdflib.auto.JDFAutoComponent.EnumComponentType;
import org.cip4.jdflib.core.AttributeInfo.EnumAttributeType;
import org.cip4.jdflib.core.AttributeName;
import org.cip4.jdflib.core.DocumentJDFImpl;
import org.cip4.jdflib.core.ElementName;
import org.cip4.jdflib.core.JDFAudit;
import org.cip4.jdflib.core.JDFDoc;
import org.cip4.jdflib.core.JDFElement;
import org.cip4.jdflib.core.JDFElement.EnumVersion;
import org.cip4.jdflib.core.JDFPartAmount;
import org.cip4.jdflib.core.JDFResourceLink;
import org.cip4.jdflib.core.JDFResourceLink.EnumUsage;
import org.cip4.jdflib.core.KElement;
import org.cip4.jdflib.core.VElement;
import org.cip4.jdflib.core.VString;
import org.cip4.jdflib.datatypes.JDFAttributeMap;
import org.cip4.jdflib.datatypes.JDFNameRange;
import org.cip4.jdflib.datatypes.JDFNameRangeList;
import org.cip4.jdflib.elementwalker.BaseWalker;
import org.cip4.jdflib.elementwalker.BaseWalkerFactory;
import org.cip4.jdflib.elementwalker.PackageElementWalker;
import org.cip4.jdflib.extensions.XJDFConstants;
import org.cip4.jdflib.extensions.XJDFHelper;
import org.cip4.jdflib.extensions.xjdfwalker.IDFinder;
import org.cip4.jdflib.extensions.xjdfwalker.IDFinder.IDPart;
import org.cip4.jdflib.node.JDFNode;
import org.cip4.jdflib.node.JDFNode.EnumType;
import org.cip4.jdflib.resource.JDFResource;
import org.cip4.jdflib.resource.process.JDFComponent;
import org.cip4.jdflib.util.StringUtil;
import org.cip4.jdflib.util.UnitParser;

/**
 * @author Rainer Prosi, Heidelberger Druckmaschinen
 *
 */
public class XJDFToJDFImpl extends PackageElementWalker
{
	JDFDoc jdfDoc;
	Map<String, IDPart> idMap;
	boolean firstConvert;
	boolean firstproductInList;
	boolean foundProduct;
	boolean foundProductList;
	JDFNode currentJDFNode;
	KElement xjdf;
	/**
	 * if true, create the product, else ignore it
	 */
	boolean createProduct;

	/**
	 * @see org.cip4.jdflib.elementwalker.PackageElementWalker#constructWalker(java.lang.String)
	 */
	@Override
	protected BaseWalker constructWalker(String name)
	{
		WalkXElement constructWalker = (WalkXElement) super.constructWalker(name);
		if (constructWalker != null)
			constructWalker.setParent(this);
		return constructWalker;
	}

	/**
	 * Getter for createProduct attribute.
	 * @return the createProduct
	 */
	public boolean isCreateProduct()
	{
		return createProduct;
	}

	/**
	 * Setter for createProduct attribute.
	 * @param createProduct the createProduct to set
	 */
	public void setCreateProduct(boolean createProduct)
	{
		this.createProduct = createProduct;
	}

	/**
	 * Getter for convertUnits attribute.
	 * @return the convertUnits
	 */
	public boolean isConvertUnits()
	{
		return convertUnits;
	}

	/**
	 * Setter for convertUnits attribute.
	 * @param convertUnits the convertUnits to set
	 */
	public void setConvertUnits(boolean convertUnits)
	{
		this.convertUnits = convertUnits;
	}

	/**
	 * Getter for bConvertTilde attribute.
	 * @return the bConvertTilde
	 */
	public boolean isbConvertTilde()
	{
		return bConvertTilde;
	}

	/**
	 *
	 */
	boolean convertUnits;
	/**
	 *
	 */
	private EnumVersion version;
	private boolean bConvertTilde;
	private boolean typeLinks;
	private boolean heuristicLink;

	/**
	 * @param template the jdfdoc to fill this into
	 *
	 */
	public XJDFToJDFImpl(final JDFDoc template)
	{
		super(new BaseWalkerFactory());
		reset(template);
	}

	public void reset(final JDFDoc template)
	{
		firstConvert = firstproductInList = createProduct = true;
		currentJDFNode = null;
		foundProductList = false;
		jdfDoc = template == null ? null : template.clone();
		idMap = null;
		foundProduct = false;
		bConvertTilde = true;
		convertUnits = false;
		typeLinks = false;
		heuristicLink = true;
		version = getVersion(template);
	}

	private EnumVersion getVersion(final JDFDoc template)
	{
		JDFNode n = template == null ? null : template.getJDFRoot();
		EnumVersion v = (n != null) ? n.getVersion(true) : null;
		if (v == null)
			v = JDFAudit.getDefaultJDFVersion();
		return v;
	}

	/**
	 * reset the product so that multiple independent product xjdf elements can be merged
	 */
	public void resetProduct()
	{
		foundProduct = false;
		foundProductList = false;
		firstproductInList = true;
	}

	/**
	 * @param _xjdf
	 * @return the converted jdf
	 */
	protected JDFDoc convert(KElement _xjdf)
	{
		if (_xjdf == null)
		{
			return null;
		}
		boolean isJMF = prepareConvert(_xjdf);
		if (isJMF)
		{
			convertXJMF();
		}
		else
		{
			prepareRoot();
			final JDFNode theNode = findNode(xjdf, true);
			if (theNode == null)
			{
				return null;
			}
			idMap = new IDFinder().getMap(xjdf);
			walkTree(xjdf, theNode);
			new PostConverter(this, theNode).postConvert();
		}
		return jdfDoc;
	}

	/**
	 *
	 */
	private void convertXJMF()
	{
		idMap = new IDFinder().getMap(xjdf);
		walkTree(xjdf, jdfDoc.getRoot());
	}

	/**
	 *
	 * @param _xjdf
	 * @return
	 */
	private boolean prepareConvert(KElement _xjdf)
	{
		xjdf = _xjdf.cloneNewDoc();
		String docType = xjdf.getLocalName();
		boolean isJMF = ElementName.JMF.equals(docType) || XJDFConstants.XJMF.equals(docType);
		if (jdfDoc == null)
		{
			String strDocType = isJMF ? ElementName.JMF : ElementName.JDF;
			jdfDoc = new JDFDoc(strDocType);
			jdfDoc.copyMeta(xjdf.getOwnerDocument_KElement());
		}
		xjdf = reparse(xjdf);
		xjdf.setAttribute(AttributeName.VERSION, version.getName());
		xjdf.setAttribute(AttributeName.MAXVERSION, version.getName());
		new XJDFHelper(xjdf).reorder();
		return isJMF;
	}

	/**
	 *
	 * @param xjdf
	 * @return
	 */
	protected KElement reparse(KElement xjdf)
	{
		if (xjdf != null && !(xjdf.getOwnerDocument() instanceof DocumentJDFImpl))
		{
			JDFDoc doc = new JDFDoc(xjdf.getOwnerDocument());
			doc.setInitOnCreate(false);
			KElement xjdf2 = doc.getRoot();
			if (!xjdf2.getLocalName().equals(xjdf.getLocalName()))
				xjdf2 = xjdf2.getChildByTagName(xjdf.getNodeName(), xjdf.getNamespaceURI(), 0, xjdf.getAttributeMap(), false, true);
			int i = 0;
			while (xjdf2 != null)
			{
				if (xjdf.isEqual(xjdf2))
				{
					xjdf = xjdf2;
					break;
				}
				xjdf2 = xjdf2.getChildByTagName(xjdf.getNodeName(), xjdf.getNamespaceURI(), ++i, xjdf.getAttributeMap(), false, true);
			}
			if (xjdf2 == null)
			{
				log.error("SNAFU converting xjdf - retaining old");
			}
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
				root = createProductRoot();
			}
		}
		return root;
	}

	/**
	 * @param xjdf
	 * @return true if the element can be converted
	 */
	public boolean canConvert(final KElement xjdf)
	{
		return xjdf == null ? false : XJDFConstants.XJDF.equals(xjdf.getLocalName());
	}

	/**
	 * find and optionally create the appropriate node
	 * @param xjdf
	 * @param create if true, create the new node
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
				for (KElement e : nodes)
				{
					final JDFNode n2 = (JDFNode) e;
					final VString vtypes = n2.getAllTypes();
					if (vtypes != null && xTypes != null && vtypes.containsAll(xTypes))
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
	 * make sure we have a product in case we have multiple nodes
	 *
	 * @return
	 */
	protected JDFNode createProductRoot()
	{
		final JDFNode parent = (JDFNode) jdfDoc.createElement("JDF");
		parent.setType(EnumType.Product);
		JDFNode oldParent = jdfDoc.getJDFRoot();
		oldParent = (JDFNode) parent.moveElement(oldParent, null);
		jdfDoc.appendChild(parent);

		parent.moveAttribute(AttributeName.JOBID, oldParent);
		parent.moveAttribute(AttributeName.VERSION, oldParent);
		parent.moveAttribute(AttributeName.MAXVERSION, oldParent);
		parent.moveAttribute(AttributeName.COMMENTURL, oldParent);
		parent.setJobPartID("rootPart");
		parent.moveElement(oldParent.getResourcePool(), null);

		final JDFComponent c = (JDFComponent) parent.addResource(ElementName.COMPONENT, EnumUsage.Output);
		c.setDescriptiveName("dummy output");
		c.setComponentType(EnumComponentType.FinalProduct, null);

		mergeProductLinks(oldParent, parent);
		firstConvert = true;
		return parent;
	}

	/**
	 * @param element
	 */
	protected void convertUnits(final KElement element)
	{
		if (convertUnits)
		{
			new UnitParser().convertUnits(element);
		}
	}

	/**
	 * @param e2
	 */
	protected void convertTilde(final KElement e2)
	{
		if (bConvertTilde)
		{
			final JDFAttributeMap map = e2.getAttributeMap();
			final Iterator<String> keyIt = map.getKeyIterator();
			while (keyIt.hasNext())
			{
				final String key = keyIt.next();
				final String val = map.get(key);
				if ((e2 instanceof JDFElement) && EnumAttributeType.isRange(((JDFElement) e2).getAttributeInfo().getAttributeType(key)))
				{
					VString v = new VString(val, null);
					if (v.size() % 2 == 0)
					{
						JDFNameRangeList nrl = new JDFNameRangeList();
						for (int i = 0; i < v.size(); i += 2)
						{
							nrl.append(new JDFNameRange(v.get(i), v.get(i + 1)));
						}
						String newVal = nrl.getString(0);
						if (!val.equals(newVal))
						{
							e2.setAttribute(key, newVal);
						}
					}
				}
			}
		}
	}

	/**
	 * @param theNode
	 * @param parent
	 */
	void mergeProductLinks(final JDFNode theNode, final JDFNode parent)
	{
		if (theNode == parent)
			return;

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
	 *
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
				attributeToSpan(e, name);
			}
		}
	}

	/**
	 *
	 *
	 * @param e
	 * @param name
	 * @return the new span element
	 */
	protected KElement attributeToSpan(final KElement e, final String name)
	{
		final KElement subElem = e.appendElement(name);
		subElem.init();
		subElem.setAttribute(AttributeName.ACTUAL, e.getAttribute(name));
		convertUnits(subElem);
		convertTilde(subElem);
		e.removeAttribute(name);
		return subElem;
	}

	/**
	 * @param rl
	 * @param partmap
	 * @param map
	 * @param a
	 */
	private void moveToLink(final JDFResourceLink rl, final JDFAttributeMap partmap, final JDFAttributeMap map, final String a)
	{
		if (map == null || map.isEmpty() || rl == null)
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
				rl.setAmountPoolAttribute(a, map.get(a + gw), null, pm);
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
		if (rl != null)
		{
			moveToLink(rl, partmap, map, AttributeName.AMOUNT);
			moveToLink(rl, partmap, map, AttributeName.ACTUALAMOUNT);
			moveToLink(rl, partmap, map, AttributeName.MAXAMOUNT);
			if (partmap == null && rl.getXPathElement("AmountPool/PartAmount/Part") == null && rl.getAmountPool() != null)
			{
				JDFPartAmount pa = rl.getAmountPool().getPartAmount(0);
				rl.copyAttribute(AttributeName.AMOUNT, pa);
				rl.copyAttribute(AttributeName.ACTUALAMOUNT, pa);
				rl.copyAttribute(AttributeName.MAXAMOUNT, pa);
				rl.getAmountPool().deleteNode();
			}
		}
	}

	/**
	 * if true tildes are  converted to pairs in xjdf 2.0
	 * @param b
	 */
	public void setConvertTilde(boolean b)
	{
		bConvertTilde = b;
	}

	/**
	 * Setter for typeLinks attribute. if true, we will only create links that are appropriate for the respective type or types
	 * @param heuristicLink the typeLinks to set
	 */
	public void setTypeLinks(boolean typeLinks)
	{
		this.typeLinks = typeLinks;
	}

	/**
	 * Getter for typeLinks attribute.
	 * @return the typeLinks
	 */
	public boolean isTypeLinks()
	{
		return typeLinks;
	}

	/**
	 *
	 * @return
	 */
	public boolean isHeuristicLink()
	{
		return heuristicLink;
	}

	/**
	 * if true, we will find a @Usage for sets with no Usage using fuzzy heuristics
	 * @param heuristicLink
	 */
	public void setHeuristicLink(boolean heuristicLink)
	{
		this.heuristicLink = heuristicLink;
	}
}
