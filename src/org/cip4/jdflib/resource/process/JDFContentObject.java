/*
 * The CIP4 Software License, Version 1.0
 *
 *
 * Copyright (c) 2001-2006 The International Cooperation for the Integration of
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
 * JDFContentObject.java
 *
 * Last changes
 *
 * 2002-07-02 JG init() removed SetType()
 *
 */
package org.cip4.jdflib.resource.process;

import org.apache.xerces.dom.CoreDocumentImpl;
import org.cip4.jdflib.auto.JDFAutoContentObject;
import org.cip4.jdflib.datatypes.JDFXYPair;
import org.cip4.jdflib.ifaces.IPlacedObject;

public class JDFContentObject extends JDFAutoContentObject implements
		IPlacedObject
{
	private static final long serialVersionUID = 1L;

	/**
	 * Constructor for JDFContentObject
	 * 
	 * @param ownerDocument
	 * @param qualifiedName
	 */
	public JDFContentObject(CoreDocumentImpl myOwnerDocument,
			String qualifiedName)
	{
		super(myOwnerDocument, qualifiedName);
	}

	/**
	 * Constructor for JDFContentObject
	 * 
	 * @param ownerDocument
	 * @param namespaceURI
	 * @param qualifiedName
	 */
	public JDFContentObject(CoreDocumentImpl myOwnerDocument,
			String myNamespaceURI, String qualifiedName)
	{
		super(myOwnerDocument, myNamespaceURI, qualifiedName);
	}

	/**
	 * Constructor for JDFContentObject
	 * 
	 * @param ownerDocument
	 * @param namespaceURI
	 * @param qualifiedName
	 * @param localName
	 */
	public JDFContentObject(CoreDocumentImpl myOwnerDocument,
			String myNamespaceURI, String qualifiedName, String myLocalName)
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
		return "JDFContentObject[  --> " + super.toString() + " ]";
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.cip4.jdflib.ifaces.IPlacedObject#setTrimCTM(double, double)
	 */
	public void setTrimSize(double x, double y)
	{
		setTrimSize(new JDFXYPair(x, y));
	}

	/**
	 * calculates a "real" ord value in an automated layout
	 * 
	 * @param ord
	 *            the Value of Ord in the layout
	 * @param nPages
	 *            the total number of pages that are consumed by the Layout, if
	 *            frontOffset!=0 the pages before frontOffset are NOT counted
	 * @param loop
	 *            which sheet loop are we on?
	 * @param maxOrdFront
	 *            number of pages consumed from the front of the list
	 * @param maxOrdBack
	 *            positive number of pages consumed from the back of the list
	 * @param frontOffset
	 *            page number of the first page to be placed on ord 0 in loop 0
	 * @return the pge to assign in this Ord, -1 if no page fits
	 */
	public static int calcOrd(int ord, int nPages, int loop, int maxOrdFront,
			int maxOrdBack, int frontOffset)
	{
		final int maxOrd = maxOrdFront + maxOrdBack;
		if (maxOrd * loop >= nPages)
			return -1; // we are in a loop that has no remaining pages
		int page;
		if (ord >= 0) // count from front
		{
			page = ord + loop * maxOrdFront;
		} else
		{
			int end = nPages + maxOrd - 1 - ((nPages + maxOrd - 1) % maxOrd); // the
																				// page
																				// to
																				// put
																				// on
																				// -
																				// 1
			page = end - loop * maxOrdBack + ord;
		}
		return page < nPages ? page + frontOffset : -1; // if a page evaluates
														// to e.g. 10 and we
														// only have 9 pages,
														// ciao
	}
}
