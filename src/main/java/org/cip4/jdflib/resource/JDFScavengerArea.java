/*
 *
 * The CIP4 Software License, Version 1.0
 *
 *
 * Copyright (c) 2001-2019 The International Cooperation for the Integration of Processes in Prepress, Press and Postpress (CIP4). All rights reserved.
 *
 * Redistribution and use in source and binary forms, with or without modification, are permitted provided that the following conditions are met:
 *
 * 1. Redistributions of source code must retain the above copyright notice, this list of conditions and the following disclaimer.
 *
 * 2. Redistributions in binary form must reproduce the above copyright notice, this list of conditions and the following disclaimer in the documentation and/or other materials provided with the
 * distribution.
 *
 * 3. The end-user documentation included with the redistribution, if any, must include the following acknowledgment: "This product includes software developed by the The International Cooperation for
 * the Integration of Processes in Prepress, Press and Postpress (www.cip4.org)" Alternately, this acknowledgment may appear in the software itself, if and wherever such third-party acknowledgments
 * normally appear.
 *
 * 4. The names "CIP4" and "The International Cooperation for the Integration of Processes in Prepress, Press and Postpress" must not be used to endorse or promote products derived from this software
 * without prior written permission. For written permission, please contact info@cip4.org.
 *
 * 5. Products derived from this software may not be called "CIP4", nor may "CIP4" appear in their name, without prior written permission of the CIP4 organization
 *
 * Usage of this software in commercial products is subject to restrictions. For details please consult info@cip4.org.
 *
 * THIS SOFTWARE IS PROVIDED ``AS IS'' AND ANY EXPRESSED OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO, THE IMPLIED WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE ARE
 * DISCLAIMED. IN NO EVENT SHALL THE INTERNATIONAL COOPERATION FOR THE INTEGRATION OF PROCESSES IN PREPRESS, PRESS AND POSTPRESS OR ITS CONTRIBUTORS BE LIABLE FOR ANY DIRECT, INDIRECT, INCIDENTAL,
 * SPECIAL, EXEMPLARY, OR CONSEQUENTIAL DAMAGES (INCLUDING, BUT NOT LIMITED TO, PROCUREMENT OF SUBSTITUTE GOODS OR SERVICES; LOSS OF USE, DATA, OR PROFITS; OR BUSINESS INTERRUPTION) HOWEVER CAUSED AND
 * ON ANY THEORY OF LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY, OR TORT (INCLUDING NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE OF THIS SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY
 * OF SUCH DAMAGE. ====================================================================
 *
 * This software consists of voluntary contributions made by many individuals on behalf of the The International Cooperation for the Integration of Processes in Prepress, Press and Postpress and was
 * originally based on software copyright (c) 1999-2001, Heidelberger Druckmaschinen AG copyright (c) 1999-2001, Agfa-Gevaert N.V.
 *
 * For more information on The International Cooperation for the Integration of Processes in Prepress, Press and Postpress , please see <http://www.cip4.org/>.
 *
 *
 */
/**
 *
 * Copyright (c) 2001 Heidelberger Druckmaschinen AG, All Rights Reserved.
 *
 * JDFAmount.java
 *
 * Last changes
 *
 */
package org.cip4.jdflib.resource;

import java.util.Collection;

import org.apache.xerces.dom.CoreDocumentImpl;
import org.cip4.jdflib.auto.JDFAutoScavengerArea;
import org.cip4.jdflib.core.ElementName;
import org.cip4.jdflib.core.KElement;
import org.cip4.jdflib.core.VString;
import org.cip4.jdflib.datatypes.JDFMatrix;
import org.cip4.jdflib.datatypes.JDFRectangle;
import org.cip4.jdflib.datatypes.JDFXYPair;
import org.cip4.jdflib.resource.process.JDFSeparationSpec;
import org.w3c.dom.DOMException;

public class JDFScavengerArea extends JDFAutoScavengerArea
{
	private static final long serialVersionUID = 1L;

	/**
	 * Constructor for JDFScavengerArea
	 *
	 * @param ownerDocument
	 * @param qualifiedName
	 * @throws DOMException
	 */
	public JDFScavengerArea(final CoreDocumentImpl myOwnerDocument, final String qualifiedName) throws DOMException
	{
		super(myOwnerDocument, qualifiedName);
	}

	/**
	 * Constructor for JDFScavengerArea
	 *
	 * @param ownerDocument
	 * @param namespaceURI
	 * @param qualifiedName
	 * @throws DOMException
	 */
	public JDFScavengerArea(final CoreDocumentImpl myOwnerDocument, final String myNamespaceURI, final String qualifiedName) throws DOMException
	{
		super(myOwnerDocument, myNamespaceURI, qualifiedName);
	}

	/**
	 * Constructor for JDFScavengerArea
	 *
	 * @param ownerDocument
	 * @param namespaceURI
	 * @param qualifiedName
	 * @param localName
	 * @throws DOMException
	 */
	public JDFScavengerArea(final CoreDocumentImpl myOwnerDocument, final String myNamespaceURI, final String qualifiedName, final String myLocalName) throws DOMException
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
		return "JDFScavengerArea[  --> " + super.toString() + " ]";
	}

	/**
	 *
	 * @return the rectangle in layout coordinate system
	 */
	public JDFRectangle getRect()
	{
		final JDFXYPair c = getCenter();
		final JDFXYPair s = getSize();
		if (c == null || s == null)
			return null;

		final double rot = getRotation();
		if (rot == 90 || rot == 270)
		{
			s.swapXY();
		}

		final JDFRectangle r = new JDFRectangle(s);
		r.setCenter(c);
		final JDFMarkObject mo = getParentMarkObject();
		final JDFMatrix m = mo == null ? null : mo.getTrimCTM();
		if (m == null)
		{
			return r;
		}
		else
		{
			return m.transform(r);
		}
	}

	/**
	 *
	 * @return
	 */
	public JDFMarkObject getParentMarkObject()
	{
		final KElement e = getParentNode_KElement();
		return e instanceof JDFMarkObject ? (JDFMarkObject) e : null;
	}

	/**
	 * Get a list of all separation names in the SeparationSpec elements
	 *
	 * @return the vector of separation names - empty VString if no separations are present
	 */
	public VString getSeparations()
	{
		final VString vs = new VString();
		final Collection<JDFSeparationSpec> v = getAllSeparationSpec();
		for (final JDFSeparationSpec s : v)
		{
			final String sepName = s.getName();
			vs.add(sepName);
		}
		return vs;
	}

	/**
	 * set all separation names in the SeparationSpec elements, remove any prior elements
	 *
	 * @param vSeps the vector of separation names to set
	 */
	public void setSeparations(final VString vSeps)
	{
		removeChildren(ElementName.SEPARATIONSPEC, null, null);
		appendSeparations(vSeps);
	}

	/**
	 * append a separationspec with a given name to this
	 *
	 * @param sep the separation name
	 */
	public JDFSeparationSpec appendSeparation(final String sep)
	{
		final JDFSeparationSpec ss = appendSeparationSpec();
		ss.setName(sep);
		return ss;
	}

	/**
	 * append all separation names in the SeparationSpec elements without removing any prior elements
	 *
	 * @param vSeps the vector of separation names to append
	 */
	public void appendSeparations(final VString vSeps)
	{
		if (vSeps == null)
			return;

		for (final String sep : vSeps)
		{
			appendSeparation(sep);
		}
	}
}
