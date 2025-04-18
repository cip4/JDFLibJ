/*
 * The CIP4 Software License, Version 1.0
 *
 *
 * Copyright (c) 2001-2018 The International Cooperation for the Integration of Processes in Prepress, Press and Postpress (CIP4). All rights reserved.
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

package org.cip4.jdflib.resource.process;

import java.util.HashSet;

import org.apache.xerces.dom.CoreDocumentImpl;
import org.cip4.jdflib.auto.JDFAutoColorPool;
import org.cip4.jdflib.core.AttributeName;
import org.cip4.jdflib.core.ElementName;
import org.cip4.jdflib.core.JDFException;
import org.cip4.jdflib.core.VElement;
import org.cip4.jdflib.core.VString;
import org.cip4.jdflib.datatypes.JDFAttributeMap;
import org.cip4.jdflib.util.StringUtil;
import org.w3c.dom.DOMException;

/**
 *
 * @author Rainer Prosi, Heidelberger Druckmaschinen *
 */
public class JDFColorPool extends JDFAutoColorPool
{
	private static final long serialVersionUID = 1L;

	/**
	 * Constructor for JDFColorPool
	 *
	 * @param myOwnerDocument
	 * @param qualifiedName
	 * @throws DOMException
	 *
	 */
	public JDFColorPool(final CoreDocumentImpl myOwnerDocument, final String qualifiedName) throws DOMException
	{
		super(myOwnerDocument, qualifiedName);
	}

	/**
	 * Constructor for JDFColorPool
	 *
	 * @param myOwnerDocument
	 * @param myNamespaceURI
	 * @param qualifiedName
	 * @throws DOMException
	 *
	 */
	public JDFColorPool(final CoreDocumentImpl myOwnerDocument, final String myNamespaceURI, final String qualifiedName) throws DOMException
	{
		super(myOwnerDocument, myNamespaceURI, qualifiedName);
	}

	/**
	 * Constructor for JDFColorPool
	 *
	 * @param myOwnerDocument
	 * @param myNamespaceURI
	 * @param qualifiedName
	 * @param myLocalName
	 *
	 * @throws DOMException
	 */
	public JDFColorPool(final CoreDocumentImpl myOwnerDocument, final String myNamespaceURI, final String qualifiedName, final String myLocalName) throws DOMException
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
		return "JDFColorPool[  --> " + super.toString() + " ]";
	}

	/**
	 *
	 * @param strColorName
	 */
	public void removeColor(final String strColorName)
	{
		final VElement vElem = getChildElementVector(ElementName.COLOR, null, null, true, 0, false);
		if (vElem != null)
		{
			final int size = vElem.size();
			for (int i = 0; i < size; i++)
			{
				final JDFColor c = (JDFColor) vElem.elementAt(i);
				if (strColorName.equals(c.getActualColorName()))
				{
					c.deleteNode();
				}
			}
		}
	}

	/**
	 * typesafe validator
	 *
	 * @param level validation level
	 * @return boolean true if this is valid
	 */
	@Override
	public boolean isValid(final EnumValidationLevel level)
	{
		boolean bValid = super.isValid(level);
		if (!bValid)
		{
			return false;
		}

		bValid = getDuplicateColors() == null;
		return bValid;
	}

	/**
	 * does this ColorPool have Color elements with identical Name or RawName eattributes return false if no Color elements with identical Name or RawName tags exist
	 *
	 * @return
	 *
	 * @deprecated use getDuplicateColors()==null
	 */
	@Deprecated
	public boolean hasDuplicateColors()
	{
		return getDuplicateColors() != null;
	}

	/**
	 * does this ColorPool have Color elements with identical Name or RawName eattributes return false if no Color elements with identical Name or RawName tags exist
	 *
	 * @return
	 */
	public VString getDuplicateColors()
	{
		final VElement v = getChildElementVector(ElementName.COLOR, null, null, true, 0, false);
		final HashSet<String> vName = new HashSet<>();
		final int nCols = v.size();
		final VString vRet = new VString();
		for (int i = 0; i < nCols; i++)
		{
			final JDFColor color = (JDFColor) v.elementAt(i);
			final String colorName = color.getName();
			if (vName.contains(colorName))
			{
				vRet.appendUnique(colorName);
			}
			final String rawName = color.get8BitName();
			if (vName.contains(rawName))
			{
				vRet.appendUnique(colorName);
			}
			vName.add(colorName);
			vName.add(rawName);
		}

		return vRet.size() == 0 ? null : vRet;
	}

	/**
	 * Get the Color Element with Name=name
	 *
	 * @param colorName the name of the color
	 *
	 * @return JDFColor the color with the matching name, or null if no matching element exists
	 */
	public JDFColor getColorWithName(final String colorName)
	{
		JDFColor color = null;

		if (colorName == null)
		{
			throw new JDFException("Bad colorname:" + colorName);
		}

		final VElement v = getChildElementVector(ElementName.COLOR, null, null, true, 0, false);
		if (v != null)
		{
			int pos = -1;
			final int siz = v.size();
			for (int i = 0; i < siz; i++)
			{
				color = (JDFColor) v.elementAt(i);
				if (colorName.equals(color.getName()) || colorName.equals(color.getActualColorName()))
				{
					if (pos < 0)
					{
						pos = i;
						break;
					}
				}
			}

			color = (JDFColor) (pos == -1 ? null : v.elementAt(pos));
		}

		return color;
	}

	/**
	 * Get the Color Element with RawName=rawName or Name=rawName in the momentary encoding
	 *
	 * @param rawName the 8 bit representation of the rawName of the color
	 *
	 * @return JDFColor the color with the matching name, null if no matching element exists
	 */
	public JDFColor getColorWith8BitName(final String rawName)
	{
		final VElement v = getChildElementVector(ElementName.COLOR, null, null, true, 0, false);

		for (int i = 0; i < v.size(); i++)
		{
			final JDFColor c = (JDFColor) v.elementAt(i);
			final String pRawName = new String(StringUtil.getRawBytes(c.get8BitName()));
			if (pRawName.equals(rawName))
			{
				return c;
			}
		}

		return null;
	}

	/**
	 * Get the Color Element with RawName=rawName
	 *
	 * @param rawName the 8 bit representation of the rawName of the color
	 *
	 * @return JDFColor the color with the matching name or null if no matching element exists
	 */
	public JDFColor getColorWithRawName(final String rawName)
	{
		final JDFAttributeMap m = new JDFAttributeMap();
		final String hexRawName = StringUtil.setHexBinaryBytes(rawName.getBytes(), -1);

		m.put(AttributeName.RAWNAME, hexRawName);
		final VElement v = getChildElementVector(ElementName.COLOR, null, m, true, 0, false);
		if (v.size() == 0)
		{
			return null;
		}
		return (JDFColor) v.elementAt(0);
	}

	/**
	 * Append a Color Element with RawName=rawName and Name = Name
	 *
	 * @param colorName the name of the color
	 * @param rawName he 8 bit representation of the rawName of the color
	 *
	 * @return JDFColor the color with the matching name, or null f no matching element exists
	 */
	public JDFColor appendColorWithName(final String colorName, final String rawName)
	{
		JDFColor col = getColorWithName(colorName);
		if (col == null)
		{
			col = getColorWith8BitName(rawName);
		}
		if (col == null)
		{
			col = appendColor();
			if (rawName != null)
			{
				final String rawNameBytes = StringUtil.setHexBinaryBytes(rawName.getBytes(), -1);
				col.setRawName(rawNameBytes);
			}
			col.setName(colorName);
		}
		else
		{
			throw new JDFException("JDFColorPool::AppendColorWithName color exists: " + colorName + "/" + rawName);
		}

		return col;
	}

	/**
	 * Get an existing or append a Color Element with RawName=rawName and Name = Name
	 *
	 * @param colorName the name of the color
	 * @param rawName he 8 bit representation of the rawName of the color
	 *
	 * @return JDFColor the color with the matching name or null JDFColor if no matching element exists
	 */
	public JDFColor getCreateColorWithName(final String colorName, final String rawName)
	{
		JDFColor col = rawName != null ? getColorWithRawName(rawName) : null;
		if (col != null)
		{
			return col;
		}

		// it only defaulted throught the transcoder, therefor redo
		col = getColorWithName(colorName);

		if (col == null)
		{
			col = appendColor();
			if (rawName != null)
			{
				final String rawNameBytes = StringUtil.setHexBinaryBytes(rawName.getBytes(), -1);
				col.setRawName(rawNameBytes);
			}
			col.setName(colorName);
		}
		else
		{
			if (col.hasAttribute(AttributeName.RAWNAME) && rawName != null)
			{
				// snafu - the rawname is different
				throw new JDFException("JDFColorPool.getCreateColorWithName color is inconsistent: " + colorName);
			}
		}

		return col;
	}
}
