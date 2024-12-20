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
 * JDFPageData.java
 *
 * Last changes
 *
 */
package org.cip4.jdflib.resource.process;

import org.apache.xerces.dom.CoreDocumentImpl;
import org.cip4.jdflib.auto.JDFAutoPageData;
import org.cip4.jdflib.core.AttributeName;
import org.cip4.jdflib.core.ElementName;
import org.cip4.jdflib.core.KElement;
import org.cip4.jdflib.datatypes.JDFIntegerRangeList;
import org.cip4.jdflib.resource.JDFPageList;
import org.w3c.dom.DOMException;

/**
 * 
 * 
 * @author rainer prosi
 * @date before... Jan 9, 2012
 */
public class JDFPageData extends JDFAutoPageData
{
	private static final long serialVersionUID = 1L;

	/**
	 * Constructor for JDFPageData
	 * 
	 * @param myOwnerDocument
	 * @param qualifiedName
	 * @throws DOMException
	 */
	public JDFPageData(CoreDocumentImpl myOwnerDocument, String qualifiedName) throws DOMException
	{
		super(myOwnerDocument, qualifiedName);
	}

	/**
	 * Constructor for JDFPageData
	 * 
	 * @param myOwnerDocument
	 * @param myNamespaceURI
	 * @param qualifiedName
	 * @throws DOMException
	 */
	public JDFPageData(CoreDocumentImpl myOwnerDocument, String myNamespaceURI, String qualifiedName) throws DOMException
	{
		super(myOwnerDocument, myNamespaceURI, qualifiedName);
	}

	/**
	 * Constructor for JDFPageData
	 * 
	 * @param myOwnerDocument
	 * @param myNamespaceURI
	 * @param qualifiedName
	 * @param myLocalName
	 * @throws DOMException
	 * 
	 */
	public JDFPageData(CoreDocumentImpl myOwnerDocument, String myNamespaceURI, String qualifiedName, String myLocalName) throws DOMException
	{
		super(myOwnerDocument, myNamespaceURI, qualifiedName, myLocalName);
	}

	// **************************************** Methods
	// *********************************************
	/**
	 * toString
	 * 
	 * @return String
	 */
	@Override
	public String toString()
	{
		return "JDFPageData[  --> " + super.toString() + " ]";
	}

	/**
	 * @param letter
	 */
	public void refContentData(JDFContentData letter)
	{
		JDFPageElement pe = appendPageElement();
		if (letter != null)
			pe.setContentListIndex(letter.getIndex());
	}

	/**
	 * if not explicitly specified, assume that the PageData elements are all ordered
	 * 
	 * @see org.cip4.jdflib.auto.JDFAutoPageData#getPageIndex()
	 */
	@Override
	public JDFIntegerRangeList getPageIndex()
	{
		JDFIntegerRangeList pi = super.getPageIndex();
		if (pi != null && pi.size() > 0)
			return pi;
		KElement prev = getPreviousSiblingElement(ElementName.PAGEDATA, null);
		int n = 0;
		while (prev != null)
		{
			n++;
			prev = prev.getPreviousSiblingElement(ElementName.PAGEDATA, null);
		}
		JDFIntegerRangeList integerRangeList = new JDFIntegerRangeList();
		integerRangeList.append(n);
		return integerRangeList;
	}

	/**
	 * gets the AssemblyID but alse inherits from the parent PageList
	 * 
	 * @see org.cip4.jdflib.auto.JDFAutoPageData#getAssemblyID()
	 */
	@Override
	public String getAssemblyID()
	{
		if (hasAttribute(AttributeName.ASSEMBLYID))
			return super.getAssemblyID();
		JDFPageList parent = getPageList();
		if (parent != null)
			return parent.getAssemblyID();
		return super.getAssemblyID();
	}

	/**
	 * returns the parent pageList if the parent is a pagelist
	 * 
	 * @return
	 */
	public JDFPageList getPageList()
	{
		KElement parent = getParentNode_KElement();
		return (parent instanceof JDFPageList) ? (JDFPageList) parent : null;
	}

	/**
	 * convenience for a single integer index
	 * 
	 * @param value the integer value
	 * 
	 * @see org.cip4.jdflib.auto.JDFAutoPageData#setPageIndex(org.cip4.jdflib.datatypes.JDFIntegerRangeList)
	 */
	public void setPageIndex(int value)
	{
		setAttribute(AttributeName.PAGEINDEX, value, null);
	}
}
