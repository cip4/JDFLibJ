/*
 * The CIP4 Software License, Version 1.0
 *
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
/**
 *
 * Copyright (c) 2001 Heidelberger Druckmaschinen AG, All Rights Reserved.
 *
 * JDFBand.java
 *
 * Last changes
 *
 */
package org.cip4.jdflib.resource;

import java.util.Vector;

import org.apache.xerces.dom.CoreDocumentImpl;
import org.cip4.jdflib.auto.JDFAutoPageList;
import org.cip4.jdflib.core.AttributeName;
import org.cip4.jdflib.core.KElement;
import org.cip4.jdflib.datatypes.JDFIntegerList;
import org.cip4.jdflib.datatypes.JDFIntegerRangeList;
import org.cip4.jdflib.resource.process.JDFPageData;
import org.cip4.jdflib.util.StringUtil;
import org.w3c.dom.DOMException;

/**
 * 
 *  
 * @author rainer prosi
 * @date way before Jan 9, 2012
 */
public class JDFPageList extends JDFAutoPageList
{
	private static final long serialVersionUID = 1L;

	/**
	 * Constructor for JDFPageList
	 * @param myOwnerDocument 
	 * @param qualifiedName 
	 * @throws DOMException 
	 * 
	 */
	public JDFPageList(CoreDocumentImpl myOwnerDocument, String qualifiedName) throws DOMException
	{
		super(myOwnerDocument, qualifiedName);
	}

	/**
	 * Constructor for JDFPageList
	 * @param myOwnerDocument 
	 * @param myNamespaceURI 
	 * 
	 * @param qualifiedName
	 * @throws DOMException
	 */
	public JDFPageList(CoreDocumentImpl myOwnerDocument, String myNamespaceURI, String qualifiedName) throws DOMException
	{
		super(myOwnerDocument, myNamespaceURI, qualifiedName);
	}

	/**
	 * Constructor for JDFPageList
	 * @param myOwnerDocument 
	 * @param myNamespaceURI 
	 * @param qualifiedName 
	 * @param myLocalName 
	 * @throws DOMException 
	 * 
	 */
	public JDFPageList(CoreDocumentImpl myOwnerDocument, String myNamespaceURI, String qualifiedName, String myLocalName) throws DOMException
	{
		super(myOwnerDocument, myNamespaceURI, qualifiedName, myLocalName);
	}

	/**
	 * toString
	 * 
	 * @return String
	 */
	@Override
	public String toString()
	{
		return "JDFPageList[  --> " + super.toString() + " ]";
	}

	/**
	 * 
	 * get the number of pages in this pagelist
	 * @return
	 */
	public int getNPage()
	{
		Vector<JDFPageData> vPages = getChildrenByClass(JDFPageData.class, false, 0);
		if (vPages.size() > 0)
		{
			JDFPageData pd0 = vPages.get(0);
			if (pd0.hasAttribute(AttributeName.PAGEINDEX))
			{
				int max = 0;
				for (JDFPageData pd : vPages)
				{
					JDFIntegerRangeList rl = pd.getPageIndex();
					rl.normalize(true);
					int n = rl.getElement(-1);
					if (n >= max)
						max = n + 1;
				}
				return max;
			}
			else
			{
				return vPages.size();
			}
		}
		else
		{
			return 0;
		}
	}

	/**
	 * 
	 * get pagedata by zero based page index
	 * @param index page index, if <0, count backwards (-1=last...)
	 * @return
	 */
	public JDFPageData getPageDataByIndex(int index)
	{
		if (index < 0)
			index += getNPage();
		if (index < 0)
			return null;

		Vector<JDFPageData> vPages = getChildrenByClass(JDFPageData.class, false, 0);
		if (vPages.size() > 0)
		{
			JDFPageData pd0 = vPages.get(0);
			if (pd0.hasAttribute(AttributeName.PAGEINDEX))
			{
				for (JDFPageData pd : vPages)
				{
					JDFIntegerRangeList rl = pd.getPageIndex();
					if (rl.inRange(index))
					{
						return pd;
					}
				}
				return null;
			}
			else
			{
				return index < vPages.size() ? vPages.get(index) : null;
			}
		}
		else
		{
			return null;
		}
	}

	/**
	 * ensure a normalized pagelist where @PageIndex is a single Integer and all PageData are ordered by PageIndex
	 * @see org.cip4.jdflib.core.KElement#normalize()
	 */
	@Override
	public void normalize()
	{
		if (!isIndexed())
		{
			Vector<JDFPageData> v = getChildrenByClass(JDFPageData.class, false, 0);
			if (v != null)
			{
				for (int i = 0; i < v.size(); i++)
				{
					v.get(i).setPageIndex(i);
				}
			}
		}
		else if (!isNormal())
		{
			Vector<JDFPageData> v = getChildrenByClass(JDFPageData.class, false, 0);
			int lastEmpty = 0;
			if (v != null)
			{
				int size = v.size();
				for (int i = 0; i < size; i++)
				{
					JDFPageData pageData = v.get(i);
					JDFIntegerRangeList irl = pageData.getPageIndex();
					if (irl != null && irl.size() > 0)
					{
						JDFIntegerList il = irl.getIntegerList();
						if (il.size() > 1)
						{
							pageData.setPageIndex(il.getInt(0));
							for (int index = 1; index < il.size(); index++)
							{
								JDFPageData pd2 = (JDFPageData) copyElement(pageData, null);
								pd2.setPageIndex(il.getInt(index));
								v.add(pd2);
							}
						}
					}
					else
					{
						v.remove(pageData);
						v.insertElementAt(pageData, lastEmpty++);
					}
				}
				for (int i = 0; i < lastEmpty; i++)
				{
					v.remove(0).deleteNode();
				}
				v.sort(new KElement.SingleAttributeComparator(AttributeName.PAGEINDEX, false));

				// remove any duplicates
				for (int i = 0; i < v.size(); i++)
				{
					JDFPageData pd = v.get(i);
					int index = StringUtil.parseInt(pd.getAttribute(AttributeName.PAGEINDEX), -1);
					if (index < i)
					{
						pd.deleteNode();
						v.remove(i);
						i--;

					}
					else
					{
						while (index > i)
						{
							JDFPageData newPageData = appendPageData();
							v.insertElementAt(newPageData, i);
							newPageData.setPageIndex(i++);
						}
						moveElement(pd, null);
					}
				}
			}
		}
	}

	/**
	 * 
	 * @return true if pageData are ordered and all pagedata elements have a single index
	 */
	public boolean isNormal()
	{
		JDFPageData pd = getFirstChildElement(JDFPageData.class);
		for (int i = 0; pd != null; i++)
		{
			if (StringUtil.parseInt(pd.getAttribute(AttributeName.PAGEINDEX), -1) != i)
			{
				return false;
			}
			pd = pd.getNextSiblingElement(JDFPageData.class);
		}
		return true;
	}

	/**
	 * 
	 * @return true if all pagedata element have an index
	 */
	public boolean isIndexed()
	{
		JDFPageData pd = getPageData(0);
		while (pd != null)
		{
			if (StringUtil.getNonEmpty(pd.getAttribute(AttributeName.PAGEINDEX, null, null)) == null)
			{
				return false;
			}
			pd = pd.getNextSiblingElement(JDFPageData.class);
		}
		return true;
	}
}
