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
 * ==========================================================================
 * class JDFFoldingParams
 * ==========================================================================
 * @COPYRIGHT Heidelberger Druckmaschinen AG, 1999-2001 ALL RIGHTS RESERVED
 * @Author: sabjon@topmail.de    using a code generator
 * Warning! very preliminary test version.
 * Interface subject to change without prior notice!
 */

package org.cip4.jdflib.resource.process.postpress;

import org.apache.xerces.dom.CoreDocumentImpl;
import org.cip4.jdflib.auto.JDFAutoFoldingParams;
import org.cip4.jdflib.core.AttributeName;
import org.cip4.jdflib.core.VString;
import org.cip4.jdflib.datatypes.JDFXYPair;
import org.cip4.jdflib.util.ContainerUtil;
import org.cip4.jdflib.util.StringUtil;
import org.w3c.dom.DOMException;

public class JDFFoldingParams extends JDFAutoFoldingParams
{
	private static final long serialVersionUID = 1L;

	/**
	 *
	 * @param myOwnerDocument
	 * @param qualifiedName
	 * @throws DOMException
	 */
	public JDFFoldingParams(final CoreDocumentImpl myOwnerDocument, final String qualifiedName) throws DOMException
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
	public JDFFoldingParams(final CoreDocumentImpl myOwnerDocument, final String myNamespaceURI, final String qualifiedName) throws DOMException
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
	public JDFFoldingParams(final CoreDocumentImpl myOwnerDocument, final String myNamespaceURI, final String qualifiedName, final String myLocalName) throws DOMException
	{
		super(myOwnerDocument, myNamespaceURI, qualifiedName, myLocalName);
	}

	@Override
	public String toString()
	{
		return "JDFFoldingParams[  --> " + super.toString() + " ]";
	}

	/**
	 * get the correctly formatted fold catalog string
	 *
	 * @param pages number of finished pages
	 * @param index index of the catalog
	 * @return
	 */
	public static String getCatalog(final int pages, final int index)
	{
		return "F" + pages + "-" + index;
	}

	/**
	 * get the correctly formatted fold catalog string
	 *
	 * @param pages number of finished pages
	 * @param index index of the catalog
	 * @return
	 */
	public static JDFXYPair getNumUpFromCatalog(final String foldCatalog)
	{
		final JDFXYPair xyFold = getCatalogXY(foldCatalog);
		if (xyFold == null)
			return null;
		final int p = (int) xyFold.getX();
		final int i = (int) xyFold.getY();
		if (i <= getMax(p))
		{
			final int y = getY(p, i);
			return new JDFXYPair(p / (y * 2), y);
		}
		return null;

	}

	static int getY(final int p, final int i)
	{
		if (isWrap(p, i))
		{
			return 1;
		}
		else if (is2Y(p, i))
		{
			return 2;
		}
		else if (is3Y(p, i))
		{
			return 3;
		}
		else if (is4Y(p, i))
		{
			return 4;
		}
		else
		{
			return 6;
		}

	}

	static int getMax(final int p)
	{
		if (p < 2 || p % 2 != 0 || p > 64)
			return 0;
		switch (p)
		{
		case 2:
		case 14:
		case 28:
		case 40:
			return 1;
		case 4:
		case 20:
		case 36:
		case 48:
		case 64:
			return 2;
		case 6:
			return 8;
		case 8:
			return 7;
		case 10:
			return 3;
		case 12:
		case 16:
			return 14;
		case 18:
		case 32:
			return 9;
		case 24:
			return 11;

		default:
			return 0;
		}
	}

	/**
	 * the following lines are living proof that the fold catalog is a bit weird...
	 * 
	 * @param p
	 * @param i
	 * @return
	 */
	static boolean isWrap(final int p, final int i)
	{
		return p <= 6 || (p == 8 && i <= 6) || p == 10 || ((p == 12 && i <= 6)) || p == 14 || (p == 16 && i <= 5) || (p == 18 && i <= 4) || (p == 32 && i <= 1);
	}

	static boolean is2Y(final int p, final int i)
	{
		return (p == 8 && i >= 7) || (p == 12 && i >= 6 && i <= 11) || (p == 16 && i >= 6 && i <= 12) || p == 20 || (p == 24 && i <= 7) || p == 28 || (p == 32 && i >= 2 && i <= 3)
				|| (p == 36 && i <= 1);
	}

	static boolean is3Y(final int p, final int i)
	{
		return (p == 12 && i >= 12) || (p == 18 && i >= 5) || (p == 24 && i >= 11) || (p == 36 && i >= 2);
	}

	static boolean is4Y(final int p, final int i)
	{
		return (p == 16 && i >= 13) || (p == 24 && i >= 8 && i <= 10) || (p == 32 && i >= 4) || p == 40 || p == 48 && i <= 1 || p == 64;
	}

	static boolean is6Y(final int p, final int i)
	{
		return p == 48 && i >= 2;
	}

	public static JDFXYPair getCatalogXY(final String foldCatalog)
	{
		final String pi0 = StringUtil.rightStr(foldCatalog, -1);
		final VString v = StringUtil.tokenize(pi0, "-", false);
		if (ContainerUtil.size(v) == 2)
		{
			final int p = StringUtil.parseInt(v.get(0), -1);
			final int i = StringUtil.parseInt(v.get(1), -1);
			return (i > 0 && p > 0) ? new JDFXYPair(p, i) : null;
		}
		return null;
	}

	/**
	 * set the correctly formatted fold catalog string
	 *
	 * @param pages number of finished pages
	 * @param index index of the catalog
	 * @return
	 */
	public void setFoldCatalog(final int pages, final int index)
	{
		setFoldCatalog(getCatalog(pages, index));
	}

	@Override
	public VString getInvalidAttributes(final EnumValidationLevel level, final boolean bIgnorePrivate, final int nMax)
	{
		final VString invalidAttributes = super.getInvalidAttributes(level, bIgnorePrivate, nMax);
		if (!bIgnorePrivate)
		{
			final String fc = getFoldCatalog();
			if (!StringUtil.isEmpty(fc) && getNumUpFromCatalog(fc) == null)
				invalidAttributes.appendUnique(AttributeName.FOLDCATALOG);
		}
		return invalidAttributes;
	}

}