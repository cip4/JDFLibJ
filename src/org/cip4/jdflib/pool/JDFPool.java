/*
 *
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
 * JDFPool.java
 *
 * Last changes
 *
 * 02-07-2002  JG - remove GetPoolChildName, AddPoolElement
 * 02-07-2002  JG - remove         AddResID, GetHRefs GetvHRefRes
 *
 */
package org.cip4.jdflib.pool;

import org.apache.xerces.dom.CoreDocumentImpl;
import org.cip4.jdflib.core.AttributeName;
import org.cip4.jdflib.core.JDFComment;
import org.cip4.jdflib.core.JDFElement;
import org.cip4.jdflib.core.KElement;
import org.cip4.jdflib.core.VElement;
import org.cip4.jdflib.datatypes.JDFAttributeMap;

/**
 * This class represents a JDF-Pool which provides functionality for "network" containers and is the base class for JDFResourcePool and GarStepNetwork
 */
public abstract class JDFPool extends JDFElement
{
	private static final long serialVersionUID = 1L;

	/**
	 * Constructor for JDFPool
	 * 
	 * @param myOwnerDocument
	 * @param qualifiedName
	 */
	public JDFPool(final CoreDocumentImpl myOwnerDocument, final String qualifiedName)
	{
		super(myOwnerDocument, qualifiedName);
	}

	/**
	 * Constructor for JDFPool
	 * 
	 * @param myOwnerDocument
	 * @param myNamespaceURI
	 * @param qualifiedName
	 */
	public JDFPool(final CoreDocumentImpl myOwnerDocument, final String myNamespaceURI, final String qualifiedName)
	{
		super(myOwnerDocument, myNamespaceURI, qualifiedName);
	}

	/**
	 * Constructor for JDFPool
	 * 
	 * @param myOwnerDocument
	 * @param myNamespaceURI
	 * @param qualifiedName
	 * @param myLocalName
	 */
	public JDFPool(final CoreDocumentImpl myOwnerDocument, final String myNamespaceURI, final String qualifiedName, final String myLocalName)
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
		return "JDFPool[ --> " + super.toString() + " ]";
	}

	/**
	 * Gets all children with the attributes <code>name, mAttrib, nameSpaceURI</code> from the pool
	 * <p>
	 * default: GetPoolChildrenGeneric (JDFConstants.EMPTYSTRING, new JDFAttributeMap(), JDFConstants.EMPTYSTRING)
	 * 
	 * @param name name of the Child
	 * @param mAttrib the attribute to search for
	 * @param nameSpaceURI nameSpaceURI to search in
	 * @return VElement: a vector with all elements in the pool matching the conditions
	 */
	protected VElement getPoolChildrenGeneric(final String strName, final JDFAttributeMap mAttrib, final String nameSpaceURI)
	{
		final VElement v = getChildElementVector(strName, nameSpaceURI, mAttrib, true, 0, false);
		for (int i = v.size() - 1; i >= 0; i--)
		{
			if (v.elementAt(i) instanceof JDFComment)
			{
				v.removeElementAt(i);
			}
		}
		return v;
	}

	/**
	 * get a child from the pool matching the parameters
	 * 
	 * @param i the index of the child, or -1 to make a new one.
	 * @param name the name of the element
	 * @param mAttrib the attribute of the element
	 * @param nameSpaceURI the namespace to search in
	 * @return JDFElement: the pool child matching the above conditions
	 * 
	 * default: GetPoolChildGeneric (i, JDFConstants.EMPTYSTRING, null, JDFConstants.EMPTYSTRING)
	 */
	protected JDFElement getPoolChildGeneric(int i, final String strName, final JDFAttributeMap mAttrib, final String nameSpaceURI)
	{
		final VElement v = getPoolChildrenGeneric(strName, mAttrib, nameSpaceURI);
		if (i < 0)
		{
			i = v.size() + i;
			if (i < 0)
			{
				return null;
			}
		}
		if (v.size() <= i)
		{
			return null;
		}
		return (JDFElement) v.elementAt(i);
	}

	/**
	 * Append a new child if no identical child exists
	 * 
	 * @param p the Child to add to the element
	 */
	protected void appendUniqueGeneric(final JDFElement p)
	{
		if (!((getPoolChildrenGeneric(null, null, null).index(p) >= 0)))
		{
			copyElement(p, null);
		}
	}

	/**
	 * Append all children of p for which no identical child exists <br/>
	 * if elements have an ID attribute, this is sufficient for equivalence
	 * 
	 * @param p the Child to add to the element
	 */
	protected void appendUniqueGeneric(final JDFPool p)
	{
		final VElement vp = p.getPoolChildrenGeneric(null, null, null);

		final VElement v = getPoolChildrenGeneric(null, null, null);
		final int pSize = vp.size();
		for (int i = 0; i < pSize; i++)
		{

			final KElement elem = vp.elementAt(i);
			final String id = elem.getAttribute(AttributeName.ID, null, null);
			if (id != null)
			{
				final KElement elemHere = getChildWithAttribute(elem.getLocalName(), AttributeName.ID, null, id, 0, true);
				if (elemHere != null)
				{
					if (!elemHere.isEqual(elem)) // overwrite the old element if not equal
					{
						elemHere.replaceElement(elem);
					}
				}
				else
				// copy if it does not exist
				{
					copyElement(elem, null);
				}

			}
			else if (!v.containsElement(elem))
			{
				copyElement(elem, null);
			}
		}
	}
}
