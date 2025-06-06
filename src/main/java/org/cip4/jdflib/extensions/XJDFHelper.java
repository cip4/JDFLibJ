/*
 * The CIP4 Software License, Version 1.0
 *
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
 * the Integration of Processes in Prepress, Press and Postpress (www.cip4.org)" Alternately, this acknowledgment mrSubRefay appear in the software itself, if and wherever such third-party
 * acknowledgments normally appear.
 *
 * 4. The names "CIP4" and "The International Cooperation for the Integration of Processes in Prepress, Press and Postpress" must not be used to endorse or promote products derived from this software
 * without prior written permission. For written permission, please contact info@cip4.org.
 *
 * 5. Products derived from this software may not be called "CIP4", nor may "CIP4" appear in their name, without prior writtenrestartProcesses() permission of the CIP4 organization
 *
 * Usage of this software in commercial products is subject to restrictions. For details please consult info@cip4.org.
 *
 * THIS SOFTWARE IS PROVIDED ``AS IS'' AND ANY EXPRESSED OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO, THE IMPLIED WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE ARE
 * DISCLAIMED. IN NO EVENT SHALL THE INTERNATIONAL COOPERATION FOR THE INTEGRATION OF PROCESSES IN PREPRESS, PRESS AND POSTPRESS OR ITS CONTRIBUTORS BE LIABLE FOR ANY DIRECT, INDIRECT, INCIDENTAL,
 * SPECIAL, EXEMPLARY, OR CONSEQUENTIrSubRefAL DAMAGES (INCLUDING, BUT NOT LIMITED TO, PROCUREMENT OF SUBSTITUTE GOODS OR SERVICES; LOSS OF USE, DATA, OR PROFITS; OR BUSINESS INTERRUPTION) HOWEVER
 * CAUSED AND ON ANY THEORY OF LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY, OR TORT (INCLUDING NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE OF THIS SOFTWARE, EVEN IF ADVISED OF THE
 * POSSIBILITY OF SUCH DAMAGE. ====================================================================
 *
 * This software consists of voluntary contributions made by many individuals on behalf of the The International Cooperation for the Integration of Processes in Prepress, Press and Postpress and was
 * originally based on software restartProcesses() copyright (c) 1999-2001, Heidelberger Druckmaschinen AG copyright (c) 1999-2001, Agfa-Gevaert N.V.
 *
 * For more information on The International Cooperation for the Integration of Processes in Prepress, Press and Postpress , please see <http://www.cip4.org/>.
 *
 */
package org.cip4.jdflib.extensions;

import java.io.File;
import java.io.IOException;
import java.io.OutputStream;
import java.util.List;
import java.util.Vector;

import org.cip4.jdflib.core.AttributeName;
import org.cip4.jdflib.core.ElementName;
import org.cip4.jdflib.core.JDFDoc;
import org.cip4.jdflib.core.JDFElement;
import org.cip4.jdflib.core.JDFElement.EnumVersion;
import org.cip4.jdflib.core.JDFResourceLink.EnumUsage;
import org.cip4.jdflib.core.KElement;
import org.cip4.jdflib.core.VElement;
import org.cip4.jdflib.core.VString;
import org.cip4.jdflib.core.XMLDoc;
import org.cip4.jdflib.datatypes.JDFIntegerList;
import org.cip4.jdflib.datatypes.VJDFAttributeMap;
import org.cip4.jdflib.extensions.xjdfwalker.IDRemover;
import org.cip4.jdflib.ifaces.INodeIdentifiable;
import org.cip4.jdflib.node.ICSVersion;
import org.cip4.jdflib.node.JDFNode.EnumType;
import org.cip4.jdflib.node.NodeIdentifier;
import org.cip4.jdflib.resource.process.JDFGeneralID;
import org.cip4.jdflib.util.ContainerUtil;
import org.cip4.jdflib.util.EnumUtil;
import org.cip4.jdflib.util.JDFDate;
import org.cip4.jdflib.util.StringUtil;
import org.cip4.jdflib.util.UrlUtil;

/**
 * @author Rainer Prosi, Heidelberger Druckmaschinen *
 */
public class XJDFHelper extends BaseXJDFHelper implements Cloneable, INodeIdentifiable
{
	/**
	 *
	 */
	public static final String RESOURCE = XJDFConstants.Resource;
	/**
	 *
	 */
	public static final String XJDF = XJDFConstants.XJDF;
	/**
	 *
	 */
	public static final String XJMF = XJDFConstants.XJMF;
	/**
	 * @deprecated
	 */
	@Deprecated
	public static final String PARAMETER = "Parameter";

	/**
	 * factory to create a helper from a doc
	 *
	 * @param doc the xmldoc to parse
	 * @return the helper
	 */
	public static XJDFHelper getHelper(final XMLDoc doc)
	{
		if (doc == null)
			return null;
		final KElement root = doc.getRoot();
		return getHelper(root);
	}

	/**
	 * factory to create a helper from an element
	 *
	 * @param root the element to parse if not an XJDF - search in ancestors of element
	 * @return the helper
	 */
	public static XJDFHelper getHelper(KElement root)
	{
		if (root == null)
			return null;
		if (!root.getLocalName().equals(XJDFConstants.XJDF))
			root = root.getDeepParent(XJDFConstants.XJDF, 0);
		return (root != null) ? new XJDFHelper(root) : null;
	}

	/**
	 *
	 * @param root
	 * @return
	 */
	public static boolean isXJDF(final KElement root)
	{
		return root != null && XJDFConstants.XJDF.equals(root.getLocalName());
	}

	/**
	 *
	 * @param root
	 * @return
	 */
	public static boolean isXJMF(final KElement root)
	{
		return root != null && XJDFConstants.XJMF.equals(root.getLocalName());
	}

	/**
	 *
	 * @param fileName
	 * @return
	 */
	public static XJDFHelper parseFile(final String fileName)
	{
		return getHelper(JDFDoc.parseFile(fileName));
	}

	/**
	 *
	 * @param fileName
	 * @return
	 */
	public static XJDFHelper parseFile(final File fileName)
	{
		return getHelper(JDFDoc.parseFile(fileName));
	}

	/**
	 * @param xjdf if null a new XJDF is generated, else the xjdf root to be manipulated
	 */
	public XJDFHelper(final KElement xjdf)
	{
		super();
		if (xjdf == null)
		{
			newXJDF(getDefaultVersion());
		}
		else
		{
			theElement = xjdf;
		}
		if (theElement instanceof JDFElement)
		{
			((JDFElement) theElement).getOwnerDocument_JDFElement().setInitOnCreate(false);
		}
	}

	/**
	 * @param jobID , if null a new jobid is generated
	 * @param jobPartID
	 * @param parts
	 */
	public XJDFHelper(String jobID, final String jobPartID, final VJDFAttributeMap parts)
	{
		super();
		newXJDF(null);
		if (jobID == null)
			jobID = "Job_" + new JDFDate().getFormattedDateTime("MMdd_hhmmss");
		setAttribute(AttributeName.JOBID, jobID);
		setAttribute(AttributeName.JOBPARTID, jobPartID);

		setParts(parts);
		cleanUp();
	}

	/**
	 *
	 * @param jobID
	 * @param jobPartID
	 */
	public XJDFHelper(final EnumVersion version, final String jobID)
	{
		super();
		newXJDF(version);
		setJobID(jobID);
		cleanUp();
	}

	/**
	 *
	 * @param jobID
	 * @param jobPartID
	 */
	public XJDFHelper(final String jobID, final String jobPartID)
	{
		this(jobID, jobPartID, null);
	}

	/**
	 * @param parts
	 */
	private void setParts(final VJDFAttributeMap parts)
	{
		final SetHelper niHelper = getCreateSet(ElementName.NODEINFO, EnumUsage.Input);
		niHelper.getCreatePartitions(parts, true);
	}

	/**
	 * @param version2
	 *
	 */
	void newXJDF(EnumVersion version)
	{
		if (version == null)
			version = getDefaultVersion();

		final JDFDoc doc = new JDFDoc(XJDFConstants.XJDF, version);
		doc.setInitOnCreate(false);

		theElement = doc.getRoot();
		if (EnumUtil.aLessThanB(EnumVersion.Version_2_0, version))
			theElement.setAttribute(AttributeName.VERSION, version.getName());
		final AuditPoolHelper aph = getCreateAuditPool();
		aph.appendMessage(XJDFConstants.AuditCreated);
	}

	/**
	 * @return the vector of parametersets and resourcesets
	 */
	public Vector<SetHelper> getSets()
	{
		return getSets(null, null);
	}

	/**
	 *
	 * @param setName
	 * @param usage
	 * @return the vector of parametersets and resourcesets
	 */
	public Vector<SetHelper> getSets(final String setName, final EnumUsage usage)
	{
		final Vector<SetHelper> v = new Vector<>();
		KElement e = theElement.getFirstChildElement();
		while (e != null)
		{
			if (SetHelper.isSet(e))
			{
				final SetHelper set = new SetHelper(e);
				if ((setName == null || setName.equals(set.getName())) && (usage == null || usage.equals(set.getUsage())))
				{
					v.add(set);
				}
			}
			e = e.getNextSiblingElement();
		}
		return v.size() == 0 ? null : v;
	}

	/**
	 *
	 * @return
	 */
	public AuditPoolHelper getAuditPool()
	{
		final KElement auditPool = theElement.getElement(ElementName.AUDITPOOL);
		return auditPool == null ? null : new AuditPoolHelper(auditPool);
	}

	/**
	 *
	 * @return
	 */
	public AuditPoolHelper getCreateAuditPool()
	{
		final KElement auditPool = theElement.getCreateElement(ElementName.AUDITPOOL);
		return new AuditPoolHelper(auditPool);
	}

	/**
	 * @param id
	 * @return the parameterset and resourceset with ID=iD
	 */
	public SetHelper getSet(final String id)
	{
		if (StringUtil.isEmpty(id))
			return null;
		KElement e = theElement.getFirstChildElement();
		while (e != null)
		{
			if (id.equals(e.getID()) && SetHelper.isSet(e))
				return new SetHelper(e);
			e = e.getNextSiblingElement();
		}

		return null;
	}

	/**
	 *
	 * @return
	 */
	public SetHelper getNodeInfo()
	{
		return getSet(ElementName.NODEINFO, 0);
	}

	/**
	 *
	 * @return
	 */
	public SetHelper getCreateNodeInfo()
	{
		return getCreateSet(ElementName.NODEINFO, EnumUsage.Input);
	}

	/**
	 * @param id
	 * @return the parameterset and resourceset with a child partiton with ID=iD
	 */
	public SetHelper getSetForPartition(final String id)
	{
		final ResourceHelper ph = getPartition(id);
		if (ph != null)
		{
			return ph.getSet();
		}
		else
		{
			return getSet(id);
		}
	}

	/**
	 * @param id
	 * @return the parameterset and resourceset with ID=iD
	 */
	public ResourceHelper getPartition(final String id)
	{
		if (id == null)
			return null;
		KElement e = theElement.getFirstChildElement();
		while (e != null)
		{
			if (SetHelper.isSet(e))
			{
				final ResourceHelper ph = new SetHelper(e).getPartition(id);
				if (ph != null)
				{
					return ph;
				}
			}
			e = e.getNextSiblingElement();
		}
		return null;
	}

	/**
	 *
	 * is the element a set?
	 *
	 * @param e
	 * @return
	 * @deprecated use SetHelper.isSet
	 */
	@Deprecated
	public boolean isSet(final KElement e)
	{
		return SetHelper.isSet(e);
	}

	/**
	 *
	 * @return the
	 */
	public String getJobID()
	{
		return getXPathValue("@JobID");
	}

	/**
	 *
	 * @param usage if Input, get predecessors, if Output get followers, if null get any
	 *
	 * @return the list of dependents, null if none were found
	 */
	public VString getDependentJobParts(final EnumUsage usage)
	{
		final Vector<SetHelper> vsh = getSets(null, usage);
		final VString ret = new VString();
		if (vsh != null)
		{
			for (final SetHelper sh : vsh)
			{
				ret.appendUnique(sh.getDependentJobParts());
			}
		}
		return ret.isEmpty() ? null : ret;
	}

	/**
	 *
	 * @param id
	 * @return
	 */
	public ProductHelper getProduct(final String id)
	{
		final KElement productList = theElement == null ? null : theElement.getElement(ProductHelper.PRODUCTLIST);
		final KElement productElement = productList == null ? null : productList.getChildWithAttribute(ProductHelper.PRODUCT, AttributeName.ID, null, id, 0, true);
		return productElement == null ? null : new ProductHelper(productElement);
	}

	/**
	 *
	 * @param id
	 * @return
	 */
	public ProductHelper getProductByExternalID(final String id)
	{
		final KElement productList = theElement == null ? null : theElement.getElement(ProductHelper.PRODUCTLIST);
		final KElement productElement = productList == null ? null : productList.getChildWithAttribute(ProductHelper.PRODUCT, XJDFConstants.ExternalID, null, id, 0, true);
		return productElement == null ? null : new ProductHelper(productElement);
	}

	/**
	 *
	 * @param id
	 * @return
	 */
	public ProductHelper getCreateProduct(final String id)
	{
		return getCreateProduct(id, null);
	}

	/**
	 *
	 * @param id
	 * @return
	 */
	public ProductHelper getCreateProduct(final int iProduct)
	{
		final ProductHelper ph = getProduct(iProduct);
		return (ph == null) ? appendProduct() : ph;
	}

	/**
	 *
	 * @param id
	 * @param externalID TODO
	 * @return
	 */
	public ProductHelper getCreateProduct(final String id, final String externalID)
	{
		ProductHelper ph = getProduct(id);
		if (ph == null)
		{
			ph = appendProduct();
			ph.setID(id);
			ph.setExternalID(externalID);
		}
		return ph;
	}

	/**
	 * get the Root product by index - note that this need not be the index in the product list but rather depends on the index of the ID in ProductList/@RootProducts
	 *
	 * @param iProduct the index of root products
	 * @return the product, null if no matching product exists
	 */
	public ProductHelper getProduct(final int iProduct)
	{
		final List<ProductHelper> productHelpers = getProductHelpers();
		return ContainerUtil.get(productHelpers, iProduct);
	}

	/**
	 * get the Root product by index - note that this need not be the index in the product list but rather depends on the index of the ID in ProductList/@RootProducts
	 *
	 * @param iProduct the index of root products
	 * @return the product, null if no matching product exists
	 */
	public ProductHelper getRootProduct(final int iProduct)
	{
		final List<ProductHelper> rootProductHelpers = getRootProductHelpers();
		return ContainerUtil.get(rootProductHelpers, iProduct);
	}

	/**
	 * get the Root product by index - note that this need not be the index in the product list but rather depends on the index of the ID in ProductList/@RootProducts
	 *
	 * @param iProduct the index of root products
	 * @return the product, null if no matching product exists
	 */
	public ProductHelper getCreateRootProduct(final int iProduct)
	{
		ProductHelper productHelper = getRootProduct(iProduct);
		if (productHelper == null)
		{
			productHelper = appendProduct();
			productHelper.setRoot();
		}
		return productHelper;
	}

	/**
	 *
	 * @param rootOnly
	 * @return
	 */
	public int numProductHelpers(final boolean rootOnly)
	{
		final Vector<ProductHelper> v = rootOnly ? getRootProductHelpers() : getProductHelpers();
		return v == null ? 0 : v.size();
	}

	/**
	 *
	 * @return the xjdf root element
	 */
	public Vector<ProductHelper> getRootProductHelpers()
	{
		final Vector<ProductHelper> vp = getProductHelpers();
		if (vp == null)
			return null;
		final Vector<ProductHelper> vp2 = new Vector<>();

		for (final ProductHelper ph : vp)
		{
			if (ph.isRootProduct())
			{
				vp2.add(ph);
			}
		}
		return vp2.size() == 0 ? null : vp2;
	}

	/**
	 * the vector of product helpers; null if no ProductList or no ProductList/Product
	 *
	 * @return the vector of product helpers
	 */
	public Vector<ProductHelper> getProductHelpers()
	{
		final KElement productList = theElement == null ? null : theElement.getElement(ProductHelper.PRODUCTLIST);
		final VElement products = productList == null ? null : productList.getChildElementVector(ProductHelper.PRODUCT, null);
		if (ContainerUtil.isEmpty(products))
			return null;

		final Vector<ProductHelper> vph = new Vector<>();
		for (final KElement e : products)
		{
			vph.add(new ProductHelper(e));
		}
		return vph;
	}

	/**
	 * @param name
	 * @param iSet
	 * @param iPart
	 * @return PartitionHelper for the requested partition, null if it ain't there
	 */
	public ResourceHelper getPartition(final String name, final int iSet, final int iPart)
	{
		final SetHelper sh = getSet(name, iSet);
		return sh == null ? null : sh.getPartition(iPart);
	}

	/**
	 * @param name
	 * @param iSet
	 * @param iPart
	 * @return resource for the requested partition, null if it ain't there
	 */
	public KElement getResource(final String name, final int iSet, final int iPart)
	{
		final ResourceHelper ph = getPartition(name, iSet, iPart);
		return ph == null ? null : ph.getResource();
	}

	/**
	 * @param name
	 * @param iSkip
	 * @return the SetHelper for the resourceset with a given name
	 */
	public SetHelper getSet(final String name, final int iSkip)
	{
		KElement e = theElement.getFirstChildElement();
		int n = 0;
		KElement e2 = null;
		while (e != null)
		{
			if (SetHelper.isSet(e) && (name == null || name.equals(e.getAttribute(AttributeName.NAME, null, null))))
			{
				if (n++ == iSkip)
				{
					e2 = e;
					break;
				}
			}
			e = e.getNextSiblingElement();
		}
		return e2 == null ? null : new SetHelper(e2);
	}

	/**
	 * @param name
	 * @param usage
	 * @param processUsage - if uses as a type then the cpi is calculated and returned
	 *
	 * @return the SetHelper for the vector of resourcesets
	 */
	public SetHelper getSet(final String name, final EnumUsage usage, final String processUsage)
	{
		SetHelper set = getSet(name, usage, processUsage, null);

		if (set == null)
		{
			final int cpi = indexOfType(processUsage, 0);
			if (cpi >= 0)
			{
				set = getSet(name, usage, null, cpi);
			}
		}
		return set;
	}

	/**
	 * @param name
	 * @param usage
	 * @param processUsage
	 *
	 * @return the SetHelper for the vector of resourcesets
	 */
	public SetHelper getSet(final String name, final EnumUsage usage, final String processUsage, final JDFIntegerList cpi, final boolean explicitCPI)
	{
		return SetHelper.getSet(theElement, name, usage, processUsage, cpi, explicitCPI);
	}

	/**
	 * @param name
	 * @param usage
	 * @param processUsage
	 *
	 * @return the SetHelper for the vector of resourcesets
	 */
	public SetHelper getSet(final String name, final EnumUsage usage, final String processUsage, final JDFIntegerList cpi)
	{
		return getSet(name, usage, processUsage, cpi, true);
	}

	/**
	 * @param name
	 * @param usage
	 * @param processUsage
	 *
	 * @return the SetHelper
	 */
	public SetHelper getSet(final String name, final EnumUsage usage, final String processUsage, final int cpi)
	{
		return getSet(name, usage, processUsage, cpi < 0 ? null : new JDFIntegerList(cpi));
	}

	/**
	 * @param name
	 * @param usage
	 * @param processUsage
	 *
	 * @return the SetHelper
	 */
	public SetHelper getCreateSet(final String name, final EnumUsage usage, final String processUsage, final JDFIntegerList cpi)
	{
		return getCreateSet(name, usage, processUsage, cpi, true);
	}

	/**
	 * @param name
	 * @param usage
	 * @param processUsage
	 * @param explicitCPI TODO
	 * @return the SetHelper
	 */
	public SetHelper getCreateSet(final String name, final EnumUsage usage, final String processUsage, final JDFIntegerList cpi, final boolean explicitCPI)
	{
		return SetHelper.getCreateSet(theElement, name, usage, processUsage, cpi, explicitCPI);
	}

	/**
	 * @param name
	 * @param usage
	 *
	 * @return the SetHelper for the vector of resourcesets
	 */
	public SetHelper getSet(final String name, final EnumUsage usage)
	{
		return SetHelper.getSet(theElement, name, usage);
	}

	/**
	 * @param family - always RESOURCE better use appendResourceSet
	 * @param name
	 * @param usage
	 * @return a new set element
	 */
	public SetHelper appendSet(final String family, final String name, final EnumUsage usage)
	{
		return SetHelper.appendSet(theElement, name, usage);
	}

	/**
	 * @param family
	 * @param name
	 * @param usage
	 * @return a new set element
	 * @deprecated - all sets are now resource sets
	 */
	@Deprecated
	public SetHelper getCreateSet(final String family, final String name, final EnumUsage usage)
	{
		SetHelper newSet = getSet(name, usage, null);
		if (newSet == null)
		{
			newSet = appendSet(family, name, usage);
		}
		return newSet;
	}

	/**
	 *
	 * @param name
	 * @param usage
	 * @return a new set element
	 * @deprecated use getCreateSet
	 */
	@Deprecated
	public SetHelper getCreateResourceSet(final String name, final EnumUsage usage)
	{
		return getCreateSet(XJDFConstants.Resource, name, usage);
	}

	/**
	 *
	 * @param name
	 * @param usage
	 * @return a new set element
	 */
	public SetHelper getCreateSet(final String name, final EnumUsage usage)
	{
		SetHelper set = getSet(name, usage);
		if (set == null)
			set = appendResourceSet(name, usage);
		return set;
	}

	/**
	 *
	 * @param name
	 * @param usage
	 * @return a new set element
	 */
	public KElement getCreateResource(final String name, final EnumUsage usage, final String processUsage)
	{
		final SetHelper set = getCreateSet(name, usage, processUsage);
		final ResourceHelper rh = set.getCreatePartition(null, true);
		return rh.getResource();
	}

	/**
	 * get or create a ResourceSet with a name, usage and processUsage
	 *
	 * @param name
	 * @param usage
	 * @param processUsage
	 * @return a new set element
	 */
	public SetHelper getCreateSet(final String name, final EnumUsage usage, final String processUsage)
	{
		SetHelper set = getSet(name, usage, processUsage);
		if (set == null)
		{
			set = appendSet(name, usage);
			set.setProcessUsage(processUsage);
		}
		return set;
	}

	/**
	 *
	 * @param name
	 * @param usage
	 * @return a new set element
	 * @deprecated
	 */
	@Deprecated
	public SetHelper getCreateParameterSet(final String name, final EnumUsage usage)
	{
		return getCreateSet(XJDFConstants.Resource, name, usage);
	}

	/**
	 * @param name
	 */
	public void removeSet(final String name)
	{
		final SetHelper newSet = getSet(name, 0);
		if (newSet != null)
		{
			newSet.getSet().deleteNode();
		}
	}

	/**
	 * @param name
	 * @param usage
	 * @return a new set element
	 * @deprecated
	 *
	 */
	@Deprecated
	public SetHelper appendParameter(final String name, final EnumUsage usage)
	{
		return appendResourceSet(name, usage);
	}

	/**
	 * @return a new producthelper
	 */
	public ProductHelper appendProduct()
	{
		final KElement product = theElement.getCreateElement(ProductHelper.PRODUCTLIST).appendElement(ProductHelper.PRODUCT);
		final ProductHelper productHelper = new ProductHelper(product);
		productHelper.setRoot(productHelper.isRootProduct());
		return productHelper;
	}

	/**
	 * @param name
	 * @param usage
	 * @return a new set element
	 * @deprecated
	 */
	@Deprecated
	public SetHelper appendResource(final String name, final EnumUsage usage)
	{
		return appendResourceSet(name, usage);
	}

	/**
	 * @param name
	 * @param usage
	 * @return a new set element
	 */
	public SetHelper appendResourceSet(final String name, final EnumUsage usage)
	{
		return SetHelper.appendSet(theElement, name, usage);
	}

	/**
	 * @param name
	 * @param usage
	 * @return a new set element
	 */
	public SetHelper appendSet(final String name, final EnumUsage usage)
	{
		return SetHelper.appendSet(theElement, name, usage);
	}

	/**
	 * @see java.lang.Object#toString()
	 * @return
	 */
	@Override
	public String toString()
	{
		return "XJDFHelper: " + theElement;
	}

	/**
	 * @param file
	 * @return
	 */
	public boolean writeToFile(final String file)
	{
		cleanUp();
		final boolean b = getRoot().getOwnerDocument_KElement().write2File(file, 2, false);
		return b;
	}

	/**
	 * write to a directory - potentially generating a jobPartID
	 *
	 * @param dir
	 * @return
	 */
	public File writeToDir(final String dir)
	{
		String jobID = getJobID();
		if (StringUtil.getNonEmpty(jobID) == null)
		{
			jobID = "xjdf";
		}
		String jobPartID = getJobPartID();
		if (StringUtil.getNonEmpty(jobPartID) == null)
		{
			String types = getAttribute(AttributeName.TYPES);
			if (StringUtil.getNonEmpty(types) == null)
			{
				types = "unknown";
			}
			jobPartID = types;
		}
		final String file = jobID + "." + jobPartID + ".xjdf";
		final String newURL = UrlUtil.getURLWithDirectory(dir, file);
		final boolean ok = writeToFile(newURL);
		return ok ? new File(newURL) : null;
	}

	/**
	 * @param os
	 * @throws IOException
	 */
	public void writeToStream(final OutputStream os) throws IOException
	{
		cleanUp();
		getRoot().getOwnerDocument_KElement().write2Stream(os, 2, false);
	}

	/**
	 *
	 * @param types
	 */
	public void setTypes(final String types)
	{
		final VString vtypes = VString.getVString(types, null);
		setTypes(vtypes);
	}

	/**
	 *
	 * @param vtypes
	 */
	public void setTypes(final VString vtypes)
	{
		setAttribute(AttributeName.TYPES, StringUtil.setvString(vtypes));
	}

	/**
	 *
	 * @return types the vector of types
	 */
	public VString getTypes()
	{
		return VString.getVString(getAttribute(AttributeName.TYPES), null);
	}

	/**
	 *
	 * @return the category
	 */
	public String getCategory()
	{
		return getAttribute(AttributeName.CATEGORY);
	}

	/**
	 *
	 * @return the category
	 */
	public VJDFAttributeMap getPartMapVector()
	{
		final SetHelper sh = getSet(ElementName.NODEINFO, EnumUsage.Input, null);
		return sh == null ? null : sh.getPartMapVector();
	}

	/**
	 *
	 * @return the implied JDF/@Type - if only one type in @Types, then we use said @Types, else ProcessGroup
	 */
	public EnumType getType()
	{
		final VString types = getTypes();
		EnumType typ = null;
		if (types != null && types.size() == 1)
		{
			typ = EnumType.getEnum(types.get(0));
		}
		return typ == null ? EnumType.ProcessGroup : typ;
	}

	/**
	 * @see org.cip4.jdflib.extensions.BaseXJDFHelper#cleanUp()
	 */
	@Override
	public void cleanUp()
	{
		cleanUp(true);
	}

	/**
	 * @see org.cip4.jdflib.extensions.BaseXJDFHelper#cleanUp()
	 */
	public void cleanUp(final boolean zappIDs)
	{
		super.cleanUp();
		final List<SetHelper> v = getSets();
		if (v != null)
		{
			for (final SetHelper sh : v)
			{
				sh.cleanUp();
				theElement.moveElement(sh.getSet(), null);
			}
		}
		final Vector<ProductHelper> vp = getProductHelpers();
		if (!ContainerUtil.isEmpty(vp))
		{
			sortProducts(vp);
			for (final ProductHelper ph : vp)
			{
				ph.cleanUp();
				theElement.getElement(XJDFConstants.ProductList).moveElement(ph.getProduct(), null);
			}
		}
		final AuditPoolHelper auditPool = getAuditPool();
		if (auditPool != null)
		{
			auditPool.cleanUp();
		}
		if (zappIDs)
			new IDRemover().removeIDs(theElement);
	}

	void sortProducts(final List<ProductHelper> vp)
	{
		int jRoot = 0;
		final int size = vp.size();
		for (int i = 0; i < size; i++)
		{
			if (vp.get(i).isRootProduct())
			{
				if (jRoot != i)
				{
					final ProductHelper ph0 = vp.set(jRoot, vp.get(i));
					vp.set(i, ph0);
					i--;
				}
				jRoot++;
			}
		}
		for (int i = 0; i < size; i++)
		{
			final List<String> kids = vp.get(i).getChildRefs(false);
			if (!ContainerUtil.isEmpty(kids))
			{
				int p0 = i + 1;
				for (int j = i + 1; j < size; j++)
				{
					final String id = vp.get(j).getID();
					if (kids.contains(id))
					{
						if (p0 != j)
						{
							final ProductHelper ph0 = vp.set(p0, vp.get(j));
							vp.set(j, ph0);
						}
						p0++;
					}
				}
			}
		}

	}

	/**
	 *
	 * @return a clone of this; any underlying documents or elements are also cloned
	 */
	@Override
	public XJDFHelper clone()
	{
		final KElement k = (theElement == null) ? null : theElement.cloneNewDoc();
		return new XJDFHelper(k);
	}

	/**
	 *
	 * @return
	 */
	public String getJobPartID()
	{
		return getAttribute(AttributeName.JOBPARTID);
	}

	/**
	 *
	 * @param jobPartID
	 */
	public void setJobPartID(final String jobPartID)
	{
		setAttribute(AttributeName.JOBPARTID, jobPartID);
	}

	/**
	 *
	 * @param jobID
	 */
	public void setJobID(final String jobID)
	{
		setAttribute(AttributeName.JOBID, jobID);
	}

	/**
	 *
	 * @param cat
	 */
	public void setCategory(final String cat)
	{
		setAttribute(AttributeName.CATEGORY, cat);
	}

	/**
	 * @return the productID of the product
	 *
	 */
	@Override
	public String getDescriptiveName()
	{
		return getAttribute(AttributeName.DESCRIPTIVENAME);
	}

	/**
	 * remove a types token
	 *
	 * @param typ
	 * @param iSkip
	 */
	public void removeType(final String typ, final int iSkip)
	{
		final VString types = getTypes();
		if (types != null && types.indexOf(typ, iSkip) >= 0)
		{
			final int pos = types.indexOf(typ, iSkip);
			types.remove(pos);
			setTypes(types);
			final Vector<SetHelper> sets = getSets();
			if (sets != null)
			{
				for (final SetHelper set : sets)
				{
					set.removeTypeFromCPI(pos);
				}
			}
		}
	}

	/**
	 * append a types token
	 *
	 * @param typ
	 *
	 */
	public XJDFHelper addType(final String typ)
	{
		addType(typ, -1);
		return this;
	}

	public void addType(final String typ, final int i)
	{
		addType(typ, i, false);

	}

	/**
	 * append a enumerated types token
	 *
	 * @param typ
	 *
	 */
	public XJDFHelper addType(final EnumType typ)
	{
		return addType(typ.getName());
	}

	/**
	 * add a types token
	 *
	 * @param typ
	 * @param iSkip <0 for append
	 * @param insert
	 */
	public void addType(final String typ, int iSkip, final boolean insert)
	{
		VString types = getTypes();
		if (types == null)
		{
			types = new VString();
		}
		final int lastPos = types.size();
		if (iSkip < 0)
			iSkip = lastPos;

		if (iSkip <= lastPos)
		{
			types.insertElementAt(typ, iSkip);
			setTypes(types);
			final Vector<SetHelper> sets = getSets();
			if (sets != null)
			{
				for (final SetHelper set : sets)
				{
					set.addTypeToCPI(iSkip, insert);
				}
			}
		}
	}

	/**
	 *
	 * @param typ
	 * @param iSkip
	 * @return the cpi of the iSkip occurrence of typ
	 */
	public int indexOfType(final String typ, int iSkip)
	{
		final VString types = getTypes();
		if (types == null || typ == null)
		{
			return -1;
		}
		int n = -1;
		while (iSkip-- >= 0)
		{
			final int i = types.indexOf(typ, n + 1);
			if (i >= 0)
			{
				n = i;
			}
			else
			{
				n = -1;
				break;
			}
		}
		return n;
	}

	/**
	 *
	 * @return default version - currently 2.0
	 */
	public static EnumVersion defaultVersion()
	{
		return getDefaultVersion();
	}

	/**
	 *
	 * @return default version - currently 2.0
	 */
	public void setVersion(final EnumVersion v)
	{
		setAttribute(AttributeName.VERSION, v == null ? defaultVersion().getName() : v.getName());
	}

	/**
	 * set attribute ICSVersions
	 *
	 * @param value the value to set the attribute to
	 */
	public VString setICSVersions(final ICSVersion... versions)
	{
		setAttribute(AttributeName.ICSVERSIONS, null);
		for (final ICSVersion v : versions)
		{
			appendICSVersion(v);
		}
		return VString.getVString(getAttribute(AttributeName.ICSVERSIONS), null);
	}

	public String appendICSVersion(final ICSVersion v)
	{
		return getRoot().appendAttribute(AttributeName.ICSVERSIONS, v == null ? null : v.toString(), true);
	}

	/**
	 * @see org.cip4.jdflib.extensions.BaseXJDFHelper#setExternalID(java.lang.String)
	 */
	@Override
	public void setExternalID(final String newID)
	{
		getCreateNodeInfo().setExternalID(newID);
	}

	/**
	 * @see org.cip4.jdflib.extensions.BaseXJDFHelper#setDescriptiveName(java.lang.String)
	 */
	@Override
	public void setDescriptiveName(final String description)
	{
		super.setDescriptiveName(description);
	}

	@Override
	public NodeIdentifier getIdentifier()
	{
		return new NodeIdentifier(getJobID(), getJobPartID(), getPartMapVector());
	}

	@Override
	public void setIdentifier(final NodeIdentifier ni)
	{
		if (ni != null)
		{
			setJobID(ni.getJobID());
			setJobPartID(ni.getJobPartID());
			setParts(ni.getPartMapVector());
		}

	}

	/**
	 * @see org.cip4.jdflib.extensions.BaseXJDFHelper#setGeneralID(java.lang.String, java.lang.String)
	 */
	@Override
	public JDFGeneralID setGeneralID(final String idUsage, final String idValue)
	{
		return super.setGeneralID(idUsage, idValue);
	}

}
