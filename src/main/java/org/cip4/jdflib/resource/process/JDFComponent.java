/*
 * The CIP4 Software License, Version 1.0
 *
 *
 * Copyright (c) 2001-2025 The International Cooperation for the Integration of
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
 * JDFComponent.java
 *
 * Last changes
 *
 */
package org.cip4.jdflib.resource.process;

import java.util.Vector;

import org.apache.commons.lang.enums.ValuedEnum;
import org.apache.xerces.dom.CoreDocumentImpl;
import org.cip4.jdflib.auto.JDFAutoComponent;
import org.cip4.jdflib.auto.JDFAutoDefect.EFace;
import org.cip4.jdflib.auto.JDFAutoMedia.EnumMediaType;
import org.cip4.jdflib.core.AttributeName;
import org.cip4.jdflib.core.ElementName;
import org.cip4.jdflib.datatypes.JDFShape;
import org.cip4.jdflib.datatypes.JDFXYPair;
import org.cip4.jdflib.resource.JDFSurfaceMark;
import org.w3c.dom.DOMException;

/**
 * @author Dr. Rainer Prosi, Heidelberger Druckmaschinen AG
 *         before June 3, 2009
 */
public class JDFComponent extends JDFAutoComponent
{
	private static final long serialVersionUID = 1L;

	/**
	 * Constructor for JDFComponent
	 *
	 * @param myOwnerDocument
	 * @param qualifiedName
	 * @throws DOMException
	 */
	public JDFComponent(final CoreDocumentImpl myOwnerDocument, final String qualifiedName) throws DOMException
	{
		super(myOwnerDocument, qualifiedName);
	}

	/**
	 * Constructor for JDFComponent
	 *
	 * @param myOwnerDocument
	 * @param myNamespaceURI
	 * @param qualifiedName
	 * @throws DOMException
	 */
	public JDFComponent(final CoreDocumentImpl myOwnerDocument, final String myNamespaceURI, final String qualifiedName) throws DOMException
	{
		super(myOwnerDocument, myNamespaceURI, qualifiedName);
	}

	/**
	 * Constructor for JDFComponent
	 *
	 * @param myOwnerDocument
	 * @param myNamespaceURI
	 * @param qualifiedName
	 * @param myLocalName
	 * @throws DOMException
	 */
	public JDFComponent(final CoreDocumentImpl myOwnerDocument, final String myNamespaceURI, final String qualifiedName, final String myLocalName)
			throws DOMException
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
		return "JDFComponent[  --> " + super.toString() + " ]";
	}

	/**
	 * sets the Dimension to X Y 0 convenience method to copy media dimension to component
	 *
	 * @param dimension
	 */
	public void setDimensions(final JDFXYPair dimension)
	{
		if (dimension != null)
		{
			final JDFShape s = new JDFShape(dimension.getX(), dimension.getY());
			super.setDimensions(s);
		}
	}

	/**
	 * sets the Dimension to X Y 0 convenience method to copy media dimension to component
	 *
	 * @param dimension
	 */
	public JDFSurfaceMark getCreateSurfaceMark(final EFace face)
	{
		JDFSurfaceMark sm = getSurfaceMark(face);
		if (sm == null)
		{
			sm = appendSurfaceMark(face);
		}
		return sm;
	}

	public JDFSurfaceMark getSurfaceMark(final EFace face)
	{
		return getChildWithAttribute(JDFSurfaceMark.class, AttributeName.SURFACE, face.name());
	}

	public JDFSurfaceMark appendSurfaceMark(final EFace face)
	{
		JDFSurfaceMark sm;
		sm = appendSurfaceMark();
		sm.setSurface(face);
		return sm;
	}

	/**
	 * @param partialFinal
	 * @param sheetWebProof
	 */
	public void setComponentType(final EnumComponentType partialFinal, final EnumComponentType sheetWebProof)
	{
		Vector<EnumComponentType> v = new Vector<>();
		if (partialFinal != null)
		{
			v.add(partialFinal);
		}
		if (sheetWebProof != null)
		{
			v.add(sheetWebProof);
		}
		if (v.size() == 0)
		{
			v = null;
		}
		setComponentType(v);
	}

	/**
	 * return true if this component contains typ
	 *
	 * @param typ
	 * @return
	 */
	public boolean isComponentType(final EnumComponentType typ)
	{
		final Vector<? extends ValuedEnum> v = getComponentType();
		return (v != null && typ != null && v.contains(typ));

	}

	/**
	 * get the media that is associated with this component, either directly or in the layout
	 *
	 * @return the media, null if none there
	 */
	@Override
	public JDFMedia getMedia()
	{
		JDFMedia m = (JDFMedia) getElement(ElementName.MEDIA);
		if (m != null)
		{
			return m;
		}
		JDFLayout lo = getLayout();
		if (lo == null)
		{
			return null;
		}
		lo = (JDFLayout) lo.getPartition(getPartMap(), EnumPartUsage.Implicit);
		m = lo.getMedia(EnumMediaType.Paper);
		if (m == null)
		{
			m = lo.getMedia(0);
		}
		return m;
	}
}
