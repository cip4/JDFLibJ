/*
 * The CIP4 Software License, Version 1.0
 *
 *
 * Copyright (c) 2001-2005 The International Cooperation for the Integration of
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
 * JDFSurface.java
 *
 * Last changes
 *
 * 2002-07-02 JG - added virtual ConsistentPartIDKeys()
 *
 */

package org.cip4.jdflib.resource.process;

import org.apache.xerces.dom.CoreDocumentImpl;
import org.cip4.jdflib.auto.JDFAutoPart.EnumSide;
import org.cip4.jdflib.core.AtrInfoTable;
import org.cip4.jdflib.core.AttributeInfo;
import org.cip4.jdflib.core.AttributeName;
import org.cip4.jdflib.core.ElementName;
import org.cip4.jdflib.core.KElement;
import org.cip4.jdflib.core.VElement;
import org.cip4.jdflib.ifaces.IPlacedObject;
import org.cip4.jdflib.resource.process.postpress.JDFSheet;
import org.w3c.dom.Node;

public class JDFSurface extends JDFSheet
{
	private static final long serialVersionUID = 1L;

	/**
	 * Constructor for JDFSurface
	 * 
	 * @param ownerDocument
	 * @param qualifiedName
	 */
	public JDFSurface(CoreDocumentImpl myOwnerDocument, String qualifiedName)
	{
		super(myOwnerDocument, qualifiedName);
	}

	/**
	 * Constructor for JDFSurface
	 * 
	 * @param ownerDocument
	 * @param namespaceURI
	 * @param qualifiedName
	 */
	public JDFSurface(CoreDocumentImpl myOwnerDocument, String myNamespaceURI, String qualifiedName)
	{
		super(myOwnerDocument, myNamespaceURI, qualifiedName);
	}

	/**
	 * Constructor for JDFSurface
	 * 
	 * @param ownerDocument
	 * @param namespaceURI
	 * @param qualifiedName
	 * @param localName
	 */
	public JDFSurface(CoreDocumentImpl myOwnerDocument, String myNamespaceURI, String qualifiedName, String myLocalName)
	{
		super(myOwnerDocument, myNamespaceURI, qualifiedName, myLocalName);
	}

	private static AtrInfoTable[] atrInfoTable = new AtrInfoTable[2];
	static
	{
		atrInfoTable[0] = new AtrInfoTable(AttributeName.SIDE, 0x44444333, AttributeInfo.EnumAttributeType.enumeration, EnumSide.getEnum(0), null);
		atrInfoTable[1] = new AtrInfoTable(AttributeName.SURFACECONTENTSBOX, 0x44444333, AttributeInfo.EnumAttributeType.rectangle, null, null);
	}

	@Override
	protected AttributeInfo getTheAttributeInfo()
	{
		AttributeInfo ai = super.getTheAttributeInfo();
		if (getLocalName().equals(ElementName.SURFACE))
			ai.updateReplace(atrInfoTable);
		return ai;
	}

	@Override
	public String toString()
	{
		return "JDFSurface[  --> " + super.toString() + " ]";
	}

	/**
	 * Get the placed object (Content or Mark)
	 * 
	 * @param int iSkip number of objects to skip
	 * @return JDFElement the placed object
	 */
	/**
	 * gets the vector of all placed objects that reside directly in this
	 * partition retains the order of marks and content
	 * 
	 * @return the placedobject, null if none were found
	 */
	public IPlacedObject getPlacedObject(int nSkip)
	{
		int nSkipLocal = nSkip;

		if (nSkipLocal < 0)
		{
			VElement v = getPlacedObjectVector();
			if (v == null)
				return null;

			nSkipLocal = nSkipLocal + v.size();
			if (nSkipLocal < 0)
				return null;

			return (IPlacedObject) v.elementAt(nSkipLocal);
		}

		int nFound = 0;
		Node n = getFirstChild();
		while (n != null)
		{
			if (n instanceof IPlacedObject)
			{
				if (nFound >= nSkipLocal)
					return (IPlacedObject) n;

				nFound++;
			}

			n = n.getNextSibling();
		}

		return null;
	}

	/**
	 * gets the vector of all placed objects that reside directly in this
	 * partition retains the order of marks and content
	 * 
	 * @return the vector of placedobjects, null if none were found
	 */
	public VElement getPlacedObjectVector()
	{
		VElement v = new VElement();
		Node n = getFirstChild();
		while (n != null)
		{
			if (n instanceof IPlacedObject)
			{
				v.add((KElement) n);
			}
			n = n.getNextSibling();
		}
		return v.isEmpty() ? null : v;
	}

}
