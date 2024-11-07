/*
 * The CIP4 Software License, Version 1.0
 *
 *
 * Copyright (c) 2001-2024 The International Cooperation for the Integration of
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
 * JDFSignal.java
 *
 * Last changes
 *
 * 2002-07-02 JG - init() Also call super::init()
 *
 */
package org.cip4.jdflib.jmf;

import org.apache.xerces.dom.CoreDocumentImpl;
import org.cip4.jdflib.auto.JDFAutoSignal;
import org.cip4.jdflib.core.AttributeName;
import org.cip4.jdflib.core.JDFElement;
import org.cip4.jdflib.core.KElement;
import org.cip4.jdflib.core.VElement;

/**
 *
 */
public class JDFSignal extends JDFAutoSignal
{
	private static final long serialVersionUID = 1L;

	/**
	 * Constructor for JDFSignal
	 *
	 * @param myOwnerDocument
	 * @param qualifiedName
	 */
	public JDFSignal(final CoreDocumentImpl myOwnerDocument, final String qualifiedName)
	{
		super(myOwnerDocument, qualifiedName);
	}

	/**
	 * Constructor for JDFSignal
	 *
	 * @param myOwnerDocument
	 * @param myNamespaceURI
	 * @param qualifiedName
	 */
	public JDFSignal(final CoreDocumentImpl myOwnerDocument, final String myNamespaceURI, final String qualifiedName)
	{
		super(myOwnerDocument, myNamespaceURI, qualifiedName);
	}

	/**
	 * Constructor for JDFSignal
	 *
	 * @param myOwnerDocument
	 * @param myNamespaceURI
	 * @param qualifiedName
	 * @param myLocalName
	 */
	public JDFSignal(final CoreDocumentImpl myOwnerDocument, final String myNamespaceURI, final String qualifiedName, final String myLocalName)
	{
		super(myOwnerDocument, myNamespaceURI, qualifiedName, myLocalName);
	}

	/**
	 * converts a response to a signal that can be sent individually
	 *
	 * @param response the response to convert
	 * @return true if successful
	 * @deprecated use the two parameter variant
	 */
	@Deprecated
	public boolean convertResponse(final JDFResponse response)
	{
		return convertResponse(response, null);
	}

	/**
	 * converts a response to a signal that can be sent individually
	 *
	 * @param response the response to convert - should not be null
	 * @param q the query that should be merged into the signal - may be null
	 * @return true if successful
	 */
	public boolean convertResponse(final JDFResponse response, final JDFQuery q)
	{
		if (response == null)
		{
			return false;
		}
		// 100223 Swapped query and response so that query is in front of response
		setResponseDetails(response);
		setQueryDetails(response, q);
		return true;
	}

	/**
	 * @param response
	 */
	private void setResponseDetails(final JDFResponse response)
	{
		setAttributes(response);
		final VElement elements = response.getChildElementVector(null, null, null, true, 0, true);
		for (int i = 0; i < elements.size(); i++)
		{
			final JDFElement element = (JDFElement) elements.elementAt(i);
			copyElement(element, null);
		}
	}

	/**
	 * create a new response for this if this is any message except response correctly fills refId, type etc.
	 *
	 * @return the newly created message
	 */
	@Override
	public JDFJMF createResponse()
	{

		final JDFJMF jmf = JDFJMF.createJMF(EnumFamily.Response, getEnumType());
		final JDFResponse response = jmf.getResponse();

		response.mergeElement(this, false);
		for (final KElement e : response.getChildArray(null, null))
		{
			if (!response.isValidMessageElement(e.getLocalName(), 0))
			{
				response.removeChild(e.getLocalName(), null, 0);
			}
		}
		return jmf;

	}

	/**
	 * @param response
	 * @param q
	 */
	private void setQueryDetails(final JDFResponse response, final JDFQuery q)
	{
		if (q != null)
		{
			final VElement v = q.getChildElementVector(null, null, null, true, 0, true);
			final KElement k0 = getFirstChildElement();
			for (int i = 0; i < v.size(); i++)
			{
				final KElement item = v.item(i);
				if (item instanceof JDFSubscription)
				{
					continue;
				}
				copyElement(item, k0);
			}
			setQuery(q);
		}
		else
		{
			setType(response.getEnumType());
			copyAttribute(AttributeName.REFID, response);
		}
	}

}
