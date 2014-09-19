/*
 * The CIP4 Software License, Version 1.0
 *
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

import java.io.IOException;
import java.io.OutputStream;
import java.util.Vector;

import org.cip4.jdflib.core.AttributeName;
import org.cip4.jdflib.core.ElementName;
import org.cip4.jdflib.core.JDFAudit.EnumAuditType;
import org.cip4.jdflib.core.JDFDoc;
import org.cip4.jdflib.core.JDFElement;
import org.cip4.jdflib.core.JDFElement.EnumVersion;
import org.cip4.jdflib.core.JDFResourceLink.EnumUsage;
import org.cip4.jdflib.core.KElement;
import org.cip4.jdflib.core.VElement;
import org.cip4.jdflib.core.VString;
import org.cip4.jdflib.core.XMLDoc;
import org.cip4.jdflib.datatypes.VJDFAttributeMap;
import org.cip4.jdflib.extensions.SetHelper.EnumFamily;
import org.cip4.jdflib.node.JDFNode.EnumType;
import org.cip4.jdflib.pool.JDFAuditPool;
import org.cip4.jdflib.util.ContainerUtil;
import org.cip4.jdflib.util.JDFDate;
import org.cip4.jdflib.util.StringUtil;

/**
  * @author Rainer Prosi, Heidelberger Druckmaschinen *
 */
public class XJDFHelper extends BaseXJDFHelper
{
	/**
	 * 
	 */
	public static final String RESOURCE = "Resource";
	/**
	 * 
	 */
	public static final String XJDF = "XJDF";
	/**
	 * 
	 */
	public static final String XJMF = "XJMF";
	/**
	 * 
	 */
	public static final String PARAMETER = "Parameter";

	/**
	 * factory to create a helper from a doc
	 *  
	 * @param doc the xmldoc to parse
	 * @return the helper
	 */
	public static XJDFHelper getHelper(XMLDoc doc)
	{
		if (doc == null)
			return null;
		KElement root = doc.getRoot();
		return root.getLocalName().equals(XJDFHelper.XJDF) ? new XJDFHelper(root) : null;
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
		if (!root.getLocalName().equals(XJDFHelper.XJDF))
			root = root.getDeepParent(XJDFHelper.XJDF, 0);
		return (root != null) ? new XJDFHelper(root) : null;
	}

	/**
	 * @param xjdf if null a new XJDF is generated, else the xjdf root to be manipulated
	 */
	public XJDFHelper(KElement xjdf)
	{
		super();
		if (xjdf == null)
		{
			newXJDF();
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
	public XJDFHelper(String jobID, String jobPartID, VJDFAttributeMap parts)
	{
		super();
		newXJDF();
		if (jobID == null)
			jobID = "Job_" + new JDFDate().getFormattedDateTime("MMdd_hhmmss");
		theElement.setAttribute(AttributeName.JOBID, jobID);
		theElement.setAttribute(AttributeName.JOBPARTID, jobPartID);
		setParts(parts);
	}

	/**
	 * @param parts
	 */
	private void setParts(VJDFAttributeMap parts)
	{
		SetHelper niHelper = getCreateSet(PARAMETER, "NodeInfo", EnumUsage.Input);
		niHelper.getCreatePartitions(parts, true);
	}

	/**
	 * 
	 */
	private void newXJDF()
	{
		JDFDoc doc = new JDFDoc(XJDFHelper.XJDF, EnumVersion.Version_2_0);
		doc.setInitOnCreate(false);
		theElement = doc.getRoot();
		JDFAuditPool ap = (JDFAuditPool) theElement.getCreateElement(ElementName.AUDITPOOL);
		ap.addAudit(EnumAuditType.Created, null).init();
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
	 * @param family
	 * @param usage
	 * @return the vector of parametersets and resourcesets
	 */
	public Vector<SetHelper> getSets(EnumFamily family, EnumUsage usage)
	{
		Vector<SetHelper> v = new Vector<SetHelper>();
		KElement e = theElement.getFirstChildElement();
		while (e != null)
		{
			if (isSet(e))
			{
				SetHelper set = new SetHelper(e);
				if ((family == null || set.getFamily().equals(family)) && (usage == null || usage.equals(set.getUsage())))
				{
					v.add(set);
				}
			}
			e = e.getNextSiblingElement();
		}
		return v.size() == 0 ? null : v;
	}

	/**
	 * @param id 
	 * @return the  parameterset and resourceset with ID=iD
	 */
	public SetHelper getSet(String id)
	{
		if (id == null)
			return null;
		KElement e = theElement.getFirstChildElement();
		while (e != null)
		{
			if (id.equals(e.getID()) && isSet(e))
				return new SetHelper(e);
			e = e.getNextSiblingElement();
		}

		return null;
	}

	/**
	 * @param id 
	 * @return the  parameterset and resourceset with a child partiton with ID=iD
	 */
	public SetHelper getSetForPartition(String id)
	{
		PartitionHelper ph = getPartition(id);
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
	 * @return the  parameterset and resourceset with ID=iD
	 */
	public PartitionHelper getPartition(String id)
	{
		if (id == null)
			return null;
		KElement e = theElement.getFirstChildElement();
		while (e != null)
		{
			if (isSet(e))
			{
				PartitionHelper ph = new SetHelper(e).getPartition(id);
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
	 * @param e
	 * @return
	 */
	public boolean isSet(KElement e)
	{
		String localName = e.getLocalName();
		return (RESOURCE + SetHelper.SET).equals(localName) || (PARAMETER + SetHelper.SET).equals(localName);
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
	 * get the Root product by index
	 * - note that this need not be the index in the product list but rather depends on the index of the ID in ProductList/@RootProducts
	 * 
	 * @param iProduct the index of root products 
	 * @return the product, null if no matching product exists
	 */
	public ProductHelper getRootProduct(int iProduct)
	{
		final Vector<ProductHelper> rootProductHelpers = getRootProductHelpers();
		if (rootProductHelpers == null)
		{
			return null;
		}
		if (iProduct < 0)
			iProduct = rootProductHelpers.size() + iProduct;
		if (iProduct >= rootProductHelpers.size() || iProduct < 0)
			return null;
		ProductHelper productHelper = rootProductHelpers.get(iProduct);
		return productHelper;
	}

	/**
	 * 
	 * @return the xjdf root element
	 */
	public Vector<ProductHelper> getRootProductHelpers()
	{
		Vector<ProductHelper> vp = getProductHelpers();
		if (vp == null)
			return null;
		Vector<ProductHelper> vp2 = new Vector<ProductHelper>();

		for (ProductHelper ph : vp)
		{
			if (ph.isRootProduct())
			{
				vp2.add(ph);
			}
		}
		return vp2.size() == 0 ? null : vp2;
	}

	/**
	 * @return
	 */
	public Vector<ProductHelper> getProductHelpers()
	{
		if (theElement == null)
			return null;
		KElement productList = theElement.getElement("ProductList");
		VElement products = productList == null ? null : productList.getChildElementVector("Product", null);
		if (products == null || products.size() == 0)
			return null;
		Vector<ProductHelper> vph = new Vector<ProductHelper>();
		for (KElement e : products)
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
	public PartitionHelper getPartition(String name, int iSet, int iPart)
	{
		SetHelper sh = getSet(name, iSet);
		return sh == null ? null : sh.getPartition(iPart);
	}

	/**
	 * @param name 
	 * @param iSet 
	 * @param iPart 
	 * @return resource for the requested partition, null if it ain't there
	 */
	public KElement getResource(String name, int iSet, int iPart)
	{
		PartitionHelper ph = getPartition(name, iSet, iPart);
		return ph == null ? null : ph.getResource();
	}

	/**
	 * @param name 
	 * @param iSkip 
	 * @return the SetHelper for the vector of parametersets and resourcesets
	 */
	public SetHelper getSet(String name, int iSkip)
	{
		KElement e = theElement.getFirstChildElement();
		int n = 0;
		KElement e2 = null;
		while (e != null)
		{
			if (isSet(e) && (name == null || name.equals(e.getAttribute("Name", null, null))))
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
	 * @param processUsage
	 *  
	 * @return the SetHelper for the vector of parametersets and resourcesets
	 */
	public SetHelper getSet(String name, String processUsage)
	{
		KElement e = theElement.getFirstChildElement();
		while (e != null)
		{
			if (isSet(e) && (name == null || name.equals(e.getAttribute("Name", null, null)))
					&& ContainerUtil.equals(StringUtil.getNonEmpty(processUsage), e.getAttribute(AttributeName.PROCESSUSAGE, null, null)))
			{
				return new SetHelper(e);
			}
			e = e.getNextSiblingElement();
		}
		return null;
	}

	/**
	 * @param family 
	 * @param name 
	 * @param usage 
	 * @return a new set element
	 */
	public SetHelper appendSet(String family, String name, EnumUsage usage)
	{
		KElement newSet = theElement.appendElement(family + "Set");
		newSet.setAttribute("Name", name);
		if (name == null)
			name = "Set";
		newSet.setAttribute(AttributeName.ID, name + KElement.uniqueID(0));
		SetHelper h = new SetHelper(newSet);
		if (usage != null)
			h.setUsage(usage);
		return h;
	}

	/**
	 * @param family 
	 * @param name 
	 * @param usage 
	 * @return a new set element
	 */
	public SetHelper getCreateSet(String family, String name, EnumUsage usage)
	{
		SetHelper newSet = getSet(name, 0);
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
	 */
	public SetHelper getCreateResourceSet(String name, EnumUsage usage)
	{
		return getCreateSet("Resource", name, usage);
	}

	/**
	 *  
	 * @param name 
	 * @param usage 
	 * @return a new set element
	 */
	public SetHelper getCreateParameterSet(String name, EnumUsage usage)
	{
		return getCreateSet("Parameter", name, usage);
	}

	/**
	 * @param name 
	 */
	public void removeSet(String name)
	{
		SetHelper newSet = getSet(name, 0);
		if (newSet != null)
		{
			newSet.getSet().deleteNode();
		}
	}

	/**
	 * @param name 
	 * @param usage TODO
	 * @return a new set element
	 */
	public SetHelper appendParameter(String name, EnumUsage usage)
	{
		return appendSet(PARAMETER, name, usage);
	}

	/**
	 * @return a new producthelper 
	 */
	public ProductHelper appendProduct()
	{
		KElement product = theElement.getCreateElement(ProductHelper.PRODUCTLIST).appendElement(ProductHelper.PRODUCT);
		return new ProductHelper(product);
	}

	/**
	 * @param name 
	 * @param usage TODO
	 * @return a new set element
	 */
	public SetHelper appendResource(String name, EnumUsage usage)
	{
		return appendSet(RESOURCE, name, usage);
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
	public boolean writeToFile(String file)
	{
		boolean b = getRoot().getOwnerDocument_KElement().write2File(file, 2, false);
		return b;
	}

	/**
	 * @param os
	 * @throws IOException 
	 */
	public void writeToStream(OutputStream os) throws IOException
	{
		getRoot().getOwnerDocument_KElement().write2Stream(os, 2, false);
	}

	/**
	 * 
	 * @param types
	 */
	public void setTypes(String types)
	{
		VString vtypes = VString.getVString(types, null);
		setTypes(vtypes);
	}

	/**
	 * 
	 * @param vtypes
	 */
	public void setTypes(VString vtypes)
	{
		setXPathValue("@Types", StringUtil.setvString(vtypes));
	}

	/**
	 * 
	 * @return types the vector of types
	 */
	public VString getTypes()
	{
		return VString.getVString(getXPathValue("@Types"), null);
	}

	/**
	 * 
	 * @return types the vector of types
	 */
	public EnumType getType()
	{
		VString types = VString.getVString(getXPathValue("@Types"), null);
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
		Vector<SetHelper> v = getSets();
		for (SetHelper sh : v)
		{
			sh.cleanUp();
		}
	}

	/**
	 * 
	 * @return
	 */
	@Override
	public XJDFHelper clone()
	{
		KElement k = (theElement == null) ? null : theElement.cloneNewDoc();
		return new XJDFHelper(k);
	}

	/**
	 * 
	 * @return
	 */
	public String getJobPartID()
	{
		return getXPathValue("@" + AttributeName.JOBPARTID);
	}

	/**
	 * 
	 * @param jobPartID
	 */
	public void setJobPartID(String jobPartID)
	{
		setXPathValue("@" + AttributeName.JOBPARTID, jobPartID);
	}

	/**
	 * 
	 * @param jobID
	 */
	public void setJobID(String jobID)
	{
		setXPathValue("@" + AttributeName.JOBID, jobID);
	}

}
