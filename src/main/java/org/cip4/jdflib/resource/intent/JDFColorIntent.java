/*
 *
 * The CIP4 Software License, Version 1.0
 *
 *
 * Copyright (c) 2001-2017 The International Cooperation for the Integration of
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
 * class JDFColorIntent extends JDFResource
 * ==========================================================================
 * @COPYRIGHT Heidelberger Druckmaschinen AG, 1999-2001 ALL RIGHTS RESERVED
 * @Author: sabjon@topmail.de   using a code generator
 * Warning! very preliminary test version.
 * Interface subject to change without prior notice!
 * Revision history:   ...
 */

package org.cip4.jdflib.resource.intent;

import org.apache.xerces.dom.CoreDocumentImpl;
import org.cip4.jdflib.auto.JDFAutoColorIntent;
import org.cip4.jdflib.core.JDFConstants;
import org.cip4.jdflib.core.JDFSeparationList;
import org.cip4.jdflib.core.VString;
import org.cip4.jdflib.util.StringUtil;
import org.w3c.dom.DOMException;

public class JDFColorIntent extends JDFAutoColorIntent
{
	private static final long serialVersionUID = 1L;

	/**
	 * Constructor for JDFColorIntent
	 *
	 * @param ownerDocument
	 * @param qualifiedName
	 * @throws DOMException
	 */
	public JDFColorIntent(CoreDocumentImpl myOwnerDocument, String qualifiedName) throws DOMException
	{
		super(myOwnerDocument, qualifiedName);
	}

	/**
	 * Constructor for JDFColorIntent
	 *
	 * @param ownerDocument
	 * @param namespaceURI
	 * @param qualifiedName
	 * @throws DOMException
	 */
	public JDFColorIntent(CoreDocumentImpl myOwnerDocument, String myNamespaceURI, String qualifiedName) throws DOMException
	{
		super(myOwnerDocument, myNamespaceURI, qualifiedName);
	}

	/**
	 * Constructor for JDFColorIntent
	 *
	 * @param ownerDocument
	 * @param namespaceURI
	 * @param qualifiedName
	 * @param localName
	 * @throws DOMException
	 */
	public JDFColorIntent(CoreDocumentImpl myOwnerDocument, String myNamespaceURI, String qualifiedName, String myLocalName) throws DOMException
	{
		super(myOwnerDocument, myNamespaceURI, qualifiedName, myLocalName);
	}

	@Override
	public String toString()
	{
		return "JDFColorIntent[  --> " + super.toString() + " ]";
	}

	/**
	 *
	 * @return
	 */
	public int getNumColorsRaw()
	{
		return super.getNumColors();
	}

	/**
	 * returns the number of colors defined in ColorsUsed
	 *
	 * @return
	 */
	@Override
	public int getNumColors()
	{
		int n = super.getNumColors();
		JDFSeparationList sl = getColorsUsed();
		if (sl == null)
			return n;
		VString vSep = sl.getSeparations();
		if (n >= 1)
			vSep.remove(JDFConstants.SEPARATION_BLACK);
		if (n == 4)
		{
			vSep.remove(JDFConstants.SEPARATION_CYAN);
			vSep.remove(JDFConstants.SEPARATION_MAGENTA);
			vSep.remove(JDFConstants.SEPARATION_YELLOW);
		}
		return n + vSep.size();
	}

	/**
	 * returns the number of varnishes defined in ColorsUsed
	 *
	 * @return
	 */
	public int getNumVarnish()
	{
		JDFSeparationList sl = getColorsUsed();
		if (sl == null)
			return 0;
		VString seps = sl.getSeparations();
		int n = 0;
		for (String sep : seps)
		{
			if (isVarnish(sep))
			{
				n++;
			}
		}
		return n;
	}

	/**
	 * returns the number of colors defined in ColorsUsed
	 *
	 * @return true if the color is a varnish
	 */
	public static boolean isVarnish(String color)
	{
		color = StringUtil.normalize(color, true);
		if (color == null)
			return false;
		if (color.indexOf("varnish") >= 0)
			return true;
		if ("aqueous".equals(color) || "bronzing".equals(color))
			return true;
		return false;
	}
}
