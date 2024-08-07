/*--------------------------------------------------------------------------------------------------
 * The CIP4 Software License, Version 1.0
 *
 *
 * Copyright (c) 2001-2018 The International Cooperation for the Integration of
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
 */
/**
 *
 * Copyright (c) 2001 Heidelberger Druckmaschinen AG, All Rights Reserved.
 *
 * JDFIdentical.java
 *
 * Last changes
 *
 */
package org.cip4.jdflib.resource.process;

import org.apache.xerces.dom.CoreDocumentImpl;
import org.cip4.jdflib.auto.JDFAutoIdentical;
import org.cip4.jdflib.core.ElementName;
import org.cip4.jdflib.core.KElement;
import org.cip4.jdflib.core.VElement;
import org.cip4.jdflib.core.VString;
import org.cip4.jdflib.datatypes.JDFAttributeMap;
import org.cip4.jdflib.resource.JDFPart;
import org.cip4.jdflib.resource.JDFResource;
import org.cip4.jdflib.util.ContainerUtil;
import org.w3c.dom.DOMException;

/**
 * @author Dr. Rainer Prosi, Heidelberger Druckmaschinen AG
 *
 *         Jul 9, 2009
 */
public class JDFIdentical extends JDFAutoIdentical
{
	private static final long serialVersionUID = 1L;

	/**
	 * Constructor for JDFIdentical
	 *
	 * @param myOwnerDocument
	 * @param qualifiedName
	 * @throws DOMException
	 */
	public JDFIdentical(final CoreDocumentImpl myOwnerDocument, final String qualifiedName) throws DOMException
	{
		super(myOwnerDocument, qualifiedName);
	}

	/**
	 * Constructor for JDFIdentical
	 *
	 * @param myOwnerDocument
	 * @param myNamespaceURI
	 * @param qualifiedName
	 * @throws DOMException
	 */
	public JDFIdentical(final CoreDocumentImpl myOwnerDocument, final String myNamespaceURI, final String qualifiedName) throws DOMException
	{
		super(myOwnerDocument, myNamespaceURI, qualifiedName);
	}

	/**
	 * Constructor for JDFIdentical
	 *
	 * @param myOwnerDocument
	 * @param myNamespaceURI
	 * @param qualifiedName
	 * @param myLocalName
	 *
	 * @throws DOMException
	 */
	public JDFIdentical(final CoreDocumentImpl myOwnerDocument, final String myNamespaceURI, final String qualifiedName, final String myLocalName) throws DOMException
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
		return "JDFIdentical[  --> " + super.toString() + " ]";
	}

	/**
	 * set all parts to those define in vParts
	 *
	 * @param mPart attribute map for the part to set
	 */
	@Override
	public void setPartMap(final JDFAttributeMap mPart)
	{
		getCreatePart().setPartMap(mPart);
	}

	/**
	 * get part map
	 *
	 * @return JDFAttributeMap: the map of parts
	 */
	@Override
	public JDFAttributeMap getPartMap()
	{
		final JDFPart p = getPart();
		return p == null ? null : p.getPartMap();
	}

	/**
	 * return the target resource of this
	 *
	 * @see org.cip4.jdflib.core.JDFElement#getTarget()
	 */
	@Override
	public JDFResource getTarget()
	{
		final JDFAttributeMap identityMap = getPartMap();
		final JDFResource parentResource = getParentResource();
		if (parentResource == null)
			return null;
		final JDFAttributeMap myMap = parentResource.getPartMap();
		if (ContainerUtil.equals(identityMap, myMap))
			return parentResource;
		else
			return parentResource.getResourceRoot().getPartition(identityMap, null);
	}

	/**
	 * return the parent resource, null if parent is not a resource
	 *
	 * @return the parent resource
	 */
	public JDFResource getParentResource()
	{
		final KElement p = getParentNode_KElement();
		return (JDFResource) ((p instanceof JDFResource) ? p : null);
	}

	/**
	 * add Part in case it is inconsistent
	 *
	 * @see org.cip4.jdflib.core.JDFElement#getInvalidElements(org.cip4.jdflib.core.JDFElement.EnumValidationLevel, boolean, int)
	 */
	@Override
	public VString getInvalidElements(final EnumValidationLevel level, final boolean ignorePrivate, final int max)
	{
		final VString v = super.getInvalidElements(level, ignorePrivate, max);
		final JDFAttributeMap myMap = getPartMap();
		final JDFResource parentResource = getParentResource();
		final JDFAttributeMap resMap = parentResource == null ? null : parentResource.getPartMap();
		if (resMap == null || myMap == null || resMap.overlapMap(myMap))
		{
			v.appendUnique(ElementName.PART);
		}
		return v;
	}

	/**
	 *
	 * @param leaves
	 */
	public static VElement removeIdenticals(final VElement leaves)
	{
		if (leaves != null)
		{
			for (int n = leaves.size() - 1; n >= 0; n--)
			{
				if (leaves.get(n).getElementByClass(JDFIdentical.class, 0, false) != null)
				{
					leaves.remove(n);
				}
			}
		}
		return leaves;
	}
}
