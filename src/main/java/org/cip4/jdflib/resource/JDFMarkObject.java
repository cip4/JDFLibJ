/*
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
 * JDFMarkObject.java
 *
 * Last changes
 *
 * 2002-07-02 JG init() removed SetType()
 *
 */

package org.cip4.jdflib.resource;

import org.apache.xerces.dom.CoreDocumentImpl;
import org.cip4.jdflib.auto.JDFAutoMarkObject;
import org.cip4.jdflib.core.AttributeName;
import org.cip4.jdflib.core.JDFException;
import org.cip4.jdflib.core.VString;
import org.cip4.jdflib.datatypes.JDFMatrix;
import org.cip4.jdflib.datatypes.JDFRectangle;
import org.cip4.jdflib.datatypes.JDFXYPair;
import org.cip4.jdflib.ifaces.IPlacedObject;
import org.cip4.jdflib.util.StringUtil;

/**
 *
 *
 * @author rainer prosi
 * @date Sep 18, 2012
 */
public class JDFMarkObject extends JDFAutoMarkObject implements IPlacedObject
{
	private static final long serialVersionUID = 1L;

	/**
	 * Constructor for JDFMarkObject
	 *
	 * @param myOwnerDocument
	 * @param qualifiedName
	 */
	public JDFMarkObject(final CoreDocumentImpl myOwnerDocument, final String qualifiedName)
	{
		super(myOwnerDocument, qualifiedName);
	}

	/**
	 * @param myOwnerDocument
	 * @param myNamespaceURI
	 * @param qualifiedName
	 */
	public JDFMarkObject(final CoreDocumentImpl myOwnerDocument, final String myNamespaceURI, final String qualifiedName)
	{
		super(myOwnerDocument, myNamespaceURI, qualifiedName);
	}

	/**
	 * Constructor for JDFMarkObject
	 *
	 * @param myOwnerDocument
	 * @param myNamespaceURI
	 * @param qualifiedName
	 * @param myLocalName
	 */
	public JDFMarkObject(final CoreDocumentImpl myOwnerDocument, final String myNamespaceURI, final String qualifiedName, final String myLocalName)
	{
		super(myOwnerDocument, myNamespaceURI, qualifiedName, myLocalName);
	}

	/**
	 * toString()
	 *
	 * @return String
	 */
	@Override
	public String toString()
	{
		return "JDFMarkObject[  --> " + super.toString() + " ]";
	}

	/**
	 * set attribute Ord
	 *
	 * @param value the value to set the attribute to
	 * @throws JDFException with stack trace if value < 0
	 */
	@Override
	public void setOrd(final int value)
	{

		if (value < 0)
		{
			throw new JDFException("Found invalid negative Ord : " + value);
		}

		setAttribute(AttributeName.ORD, value, null);
	}

	/**
	 * get int attribute Ord
	 *
	 * @return int: the value of the attribute
	 * @throws JDFException with stack trace if result < 0
	 */
	@Override
	public int getOrd()
	{
		final int result = super.getOrd();

		if (result < 0)
		{
			throw new JDFException("Found invalid negative Ord : " + result);
		}

		return result;
	}

	/**
	 *
	 * @see org.cip4.jdflib.ifaces.IPlacedObject#setTrimSize(double, double)
	 */
	@Override
	public void setTrimSize(final double x, final double y)
	{
		setTrimSize(new JDFXYPair(x, y));
	}

	/**
	 * @param x
	 * @param y
	 * @param precision number of digits in decimal
	 */
	@Override
	public void setTrimSize(final double x, final double y, final int precision)
	{
		setTrimSize(new JDFXYPair(x, y), precision);
	}

	/**
	 * @param jdfxyPair
	 * @param precision number of digits in decimal
	 */
	@Override
	public void setTrimSize(final JDFXYPair jdfxyPair, final int precision)
	{
		setAttribute(AttributeName.TRIMSIZE, jdfxyPair, null, precision);
	}

	/**
	 * @see org.cip4.jdflib.ifaces.IPlacedObject#setCTM(org.cip4.jdflib.datatypes.JDFMatrix, int)
	 */
	@Override
	public void setCTM(final JDFMatrix value, final int precision)
	{
		setAttribute(AttributeName.CTM, value, null, precision);
	}

	/**
	 * @see org.cip4.jdflib.ifaces.IPlacedObject#setTrimCTM(org.cip4.jdflib.datatypes.JDFMatrix, int)
	 */
	@Override
	public void setTrimCTM(final JDFMatrix value, final int precision)
	{
		setAttribute(AttributeName.TRIMCTM, value, null, precision);
	}

	/**
	 *
	 * @see org.cip4.jdflib.ifaces.IPlacedObject#setClipPath(java.lang.String, int)
	 */
	@Override
	public void setClipPath(final String value, final int precision)
	{
		final VString v = StringUtil.tokenize(value, " ", false);
		if (v != null)
		{
			for (int i = 0; i < v.size(); i++)
			{
				final String s = v.get(i);
				if (StringUtil.isNumber(s))
					v.set(i, StringUtil.formatDouble(StringUtil.parseDouble(s, 0), precision));
			}
		}
		super.setClipPath(StringUtil.setvString(v));
	}

	/**
	 * @see org.cip4.jdflib.ifaces.IPlacedObject#getRect()
	 */
	@Override
	public JDFRectangle getRect()
	{
		final JDFMatrix ctm = getTrimCTM();
		final JDFXYPair trimSize = getTrimSize();
		if (ctm == null || trimSize == null)
			return null;
		return ctm.transform(new JDFRectangle(trimSize));
	}

	/**
	 * @see org.cip4.jdflib.auto.JDFAutoMarkObject#getTrimCTM()
	 */
	@Override
	public JDFMatrix getTrimCTM()
	{
		final JDFMatrix trimCTM = super.getTrimCTM();
		return trimCTM == null ? getCTM() : trimCTM;
	}

	/**
	 * (20) get JDFXYPair attribute TrimSize
	 *
	 * @return JDFXYPair the value of the attribute, null if a the attribute value is not a valid to create a JDFXYPair
	 */
	@Override
	public JDFXYPair getTrimSize()
	{
		final JDFXYPair s = super.getTrimSize();
		if (s == null)
		{
			final JDFRectangle r = getClipBox();
			return r == null ? null : r.getSize();
		}
		return s;
	}

}