/*
 * The CIP4 Software License, Version 1.0
 *
 *
 * Copyright (c) 2001-2010 The International Cooperation for the Integration of
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
import org.cip4.jdflib.core.JDFResourceLink.EnumUsage;
import org.cip4.jdflib.core.KElement;
import org.cip4.jdflib.core.VString;
import org.cip4.jdflib.datatypes.VJDFAttributeMap;
import org.cip4.jdflib.pool.JDFAuditPool;
import org.cip4.jdflib.util.JDFDate;
import org.cip4.jdflib.util.StringUtil;

/**
  * @author Rainer Prosi, Heidelberger Druckmaschinen *
 */
public class XJDFHelper
{
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
			theXJDF = xjdf;
		}
		if (theXJDF instanceof JDFElement)
		{
			((JDFElement) theXJDF).getOwnerDocument_JDFElement().setInitOnCreate(false);
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
		theXJDF.setAttribute(AttributeName.JOBID, jobID);
		theXJDF.setAttribute(AttributeName.JOBPARTID, jobPartID);
		setParts(parts);
	}

	/**
	 * @param parts
	 */
	private void setParts(VJDFAttributeMap parts)
	{
		SetHelper niHelper = getCreateSet("Parameter", "NodeInfo", EnumUsage.Input);
		niHelper.getCreatePartitions(parts, true);
	}

	/**
	 * 
	 */
	private void newXJDF()
	{
		JDFDoc doc = new JDFDoc(XJDF20.rootName);
		doc.setInitOnCreate(false);
		theXJDF = doc.getRoot();
		JDFAuditPool ap = (JDFAuditPool) theXJDF.getCreateElement(ElementName.AUDITPOOL);
		ap.addAudit(EnumAuditType.Created, null).init();
	}

	/**
	 * @return the vector of parametersets and resourcesets
	 */
	public Vector<SetHelper> getSets()
	{
		Vector<SetHelper> v = new Vector<SetHelper>();
		KElement e = theXJDF.getFirstChildElement();
		while (e != null)
		{
			if (e.getLocalName().endsWith("Set"))
				v.add(new SetHelper(e));
			e = e.getNextSiblingElement();
		}
		return v.size() == 0 ? null : v;

	}

	/**
	 * 
	 * @return the xjdf root element
	 */
	public KElement getRoot()
	{
		return theXJDF;
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
		VString v = getRootProducts();
		Vector<ProductHelper> vp = new Vector<ProductHelper>();
		KElement productList = theXJDF.getElement("ProductList");
		if (v == null)
		{
			KElement product = productList.getElement("Product");
			if (product != null)
			{
				vp.add(new ProductHelper(product));
			}
		}
		else
		{
			for (String id : v)
			{
				KElement product = productList.getChildWithAttribute("Product", AttributeName.ID, null, id, 0, true);
				if (product != null)
				{
					vp.add(new ProductHelper(product));
				}
			}
		}
		return vp.size() == 0 ? null : vp;
	}

	/**
	 * @return
	 */
	private VString getRootProducts()
	{
		if (theXJDF == null)
			return null;
		KElement productList = theXJDF.getElement("ProductList");
		if (productList == null)
			return null;
		VString v = StringUtil.tokenize(productList.getAttribute("RootProducts", null, null), null, false);
		return v.size() == 0 ? null : v;
	}

	/**
	 * @param name 
	 * @param iSkip 
	 * @return the vector of parametersets and resourcesets
	 */
	public SetHelper getSet(String name, int iSkip)
	{
		KElement e = theXJDF.getFirstChildElement();
		int n = 0;
		KElement e2 = null;
		while (e != null)
		{
			if (e.getLocalName().endsWith("Set") && (name == null || name.equals(e.getAttribute("Name", null, null))))
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
	 * @param family 
	 * @param name 
	 * @param usage TODO
	 * @return a new set element
	 */
	public SetHelper appendSet(String family, String name, EnumUsage usage)
	{
		KElement newSet = theXJDF.appendElement(family + "Set");
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
		return appendSet("Parameter", name, usage);
	}

	/**
	 * @return a new set element
	 */
	public ProductHelper appendProduct()
	{
		KElement product = theXJDF.getCreateElement("ProductList").appendElement("Product");
		return new ProductHelper(product);
	}

	/**
	 * @param name 
	 * @param usage TODO
	 * @return a new set element
	 */
	public SetHelper appendResource(String name, EnumUsage usage)
	{
		return appendSet("Resource", name, usage);
	}

	protected KElement theXJDF;

	/**
	 * @see java.lang.Object#toString()
	 * @return
	*/
	@Override
	public String toString()
	{
		return "XJDFHelper: " + theXJDF;
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

}
