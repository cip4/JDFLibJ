/*
 *
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
 * ==========================================================================
 * class JDFObjectResolution
 * ==========================================================================
 * @COPYRIGHT Heidelberger Druckmaschinen AG, 1999-2001 ALL RIGHTS RESERVED
 * @Author: sabjon@topmail.de    using a code generator 
 * Warning! very preliminary test version. 
 * Interface subject to change without prior notice! 
 */

package org.cip4.jdflib.resource.process;

import java.util.Vector;

import org.apache.commons.lang.enums.ValuedEnum;
import org.apache.xerces.dom.CoreDocumentImpl;
import org.cip4.jdflib.auto.JDFAutoObjectResolution;
import org.cip4.jdflib.core.AttributeName;
import org.cip4.jdflib.core.VString;
import org.cip4.jdflib.datatypes.JDFXYPair;
import org.cip4.jdflib.ifaces.IMatches;
import org.cip4.jdflib.util.MyPair;
import org.w3c.dom.DOMException;

/**
 * 
 * @author rainer prosi
 *
 */
public class JDFObjectResolution extends JDFAutoObjectResolution implements IMatches
{
	private static final long serialVersionUID = 1L;

	/**
	 * 
	 * @param myOwnerDocument
	 * @param qualifiedName
	 * @throws DOMException
	 */
	public JDFObjectResolution(CoreDocumentImpl myOwnerDocument, String qualifiedName) throws DOMException
	{
		super(myOwnerDocument, qualifiedName);
	}

	/**
	 * 
	 * @param myOwnerDocument
	 * @param myNamespaceURI
	 * @param qualifiedName
	 * @throws DOMException
	 */
	public JDFObjectResolution(CoreDocumentImpl myOwnerDocument, String myNamespaceURI, String qualifiedName) throws DOMException
	{
		super(myOwnerDocument, myNamespaceURI, qualifiedName);
	}

	/**
	 * 
	 * @param myOwnerDocument
	 * @param myNamespaceURI
	 * @param qualifiedName
	 * @param myLocalName
	 * @throws DOMException
	 */
	public JDFObjectResolution(CoreDocumentImpl myOwnerDocument, String myNamespaceURI, String qualifiedName, String myLocalName) throws DOMException
	{
		super(myOwnerDocument, myNamespaceURI, qualifiedName, myLocalName);
	}

	/**
	 * 
	 * @param newTag
	 */
	public String addObjectTag(String newTag)
	{
		return appendAttribute(AttributeName.OBJECTTAGS, newTag, null, " ", true);
	}

	/**
	 * 
	 * @param newObject
	 */
	public String addSourceObject(EnumSourceObjects newObject)
	{
		String newTag = newObject == null ? null : newObject.getName();
		return appendAttribute(AttributeName.SOURCEOBJECTS, newTag, null, " ", true);
	}

	/**
	 * convenience setter for symmetrical resolution
	 * @param resolution
	 */
	public void setResolution(double resolution)
	{
		setResolution(new JDFXYPair(resolution, resolution));
	}

	/**
	 * 
	 * @see org.cip4.jdflib.auto.JDFAutoObjectResolution#toString()
	 */
	@Override
	public String toString()
	{
		return "JDFObjectResolution[  --> " + super.toString() + " ]";
	}

	/**
	 * convenience method
	 * 
	 * @param sourceObject
	 * @param objectTag
	 * @return
	 */
	public boolean matches(EnumSourceObjects sourceObject, String objectTag)
	{
		MyPair<EnumSourceObjects, String> subset = new MyPair<EnumSourceObjects, String>(sourceObject, objectTag);
		return matches(subset);
	}

	/**
	 * 
	 * @see org.cip4.jdflib.ifaces.IMatches#matches(java.lang.Object)
	 */
	@SuppressWarnings("unchecked")
	@Override
	public boolean matches(Object subset)
	{
		if (subset == null)
			return true;

		if (subset instanceof String)
		{
			VString objectTags = getObjectTags();
			return objectTags == null || objectTags.size() == 0 || objectTags.contains(subset);
		}
		if (subset instanceof EnumSourceObjects)
		{
			Vector<? extends ValuedEnum> sourceObjects = getSourceObjects();
			return sourceObjects == null || sourceObjects.size() == 0 || sourceObjects.contains(subset) || sourceObjects.contains(EnumSourceObjects.All);
		}
		if (subset instanceof MyPair<?, ?>)
		{
			try
			{
				MyPair<EnumSourceObjects, String> pair = (MyPair<EnumSourceObjects, String>) subset;
				return matches(pair.a) && matches(pair.b);
			}
			catch (ClassCastException x)
			{
				return false;
			}
		}
		return false;
	}
}