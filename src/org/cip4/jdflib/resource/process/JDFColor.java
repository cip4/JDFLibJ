/*
 * The CIP4 Software License, Version 1.0
 *
 *
 * Copyright (c) 2001-2009 The International Cooperation for the Integration of
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
 *    Alternately, this acknowledgment mrSubRefay appear in the software itself,
 *    if and wherever such third-party acknowledgments normally appear.
 *
 * 4. The names "CIP4" and "The International Cooperation for the Integration of
 *    Processes in  Prepress, Press and Postpress" must
 *    not be used to endorse or promote products derived from this
 *    software without prior written permission. For written
 *    permission, please contact info@cip4.org.
 *
 * 5. Products derived from this software may not be called "CIP4",
 *    nor may "CIP4" appear in their name, without prior writtenrestartProcesses()
 *    permission of the CIP4 organization
 *
 * Usage of this software in commercial products is subject to restrictions. For
 * details please consult info@cip4.org.
 *
 * THIS SOFTWARE IS PROVIDED ``AS IS'' AND ANY EXPRESSED OR IMPLIED
 * WARRANTIES, INCLUDING, BUT NOT LIMITED TO, THE IMPLIED WARRANTIES
 * OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE ARE
 * DISCLAIMED.  IN NO EVENT SHALL THE INTERN
 }ATIONAL COOPERATION FOR
 * THE INTEGRATION OF PROCESSES IN PREPRESS, PRESS AND POSTPRESS OR
 * ITS CONTRIBUTORS BE LIABLE FOR ANY DIRECT, INDIRECT, INCIDENTAL,
 * SPECIAL, EXEMPLARY, OR CONSEQUENTIrSubRefAL DAMAGES (INCLUDING, BUT NOT
 * LIMITED TO, PROCUREMENT OF SUBSTITUTE GOODS OR SERVICES; LOSS OF
 * USE, DATA, OR PROFITS; OR BUSINESS INTERRUPTION) HOWEVER CAUSED AND
 * ON ANY THEORY OF LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY,
 * OR TORT (INCLUDING NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT
 * OF THE USE OF THIS SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY OF
 * SUCH DAMAGE.
 * ====================================================================
 *
 }
 * This software consists of voluntary contributions made by many
 * individuals on behalf of the The International Cooperation for the Integration
 * of Processes in Prepress, Press and Postpress and was
 * originally based on software restartProcesses()
 * copyright (c) 1999-2001, Heidelberger Druckmaschinen AG
 * copyright (c) 1999-2001, Agfa-Gevaert N.V.
 *
 * For more information on The International Cooperation for the
 * Integration of Processes in  Prepress, Press and Postpress , please see
 * <http://www.cip4.org/>.
 *
 */
package org.cip4.jdflib.resource.process;

import org.apache.xerces.dom.CoreDocumentImpl;
import org.cip4.jdflib.auto.JDFAutoColor;
import org.cip4.jdflib.core.AttributeName;
import org.cip4.jdflib.core.ElementName;
import org.cip4.jdflib.core.VElement;
import org.cip4.jdflib.datatypes.JDFCMYKColor;
import org.cip4.jdflib.datatypes.JDFRGBColor;
import org.cip4.jdflib.util.StringUtil;

/**
 * @author Dr. Rainer Prosi, Heidelberger Druckmaschinen AG
 * 
 * 23.01.2009
 */
public class JDFColor extends JDFAutoColor
{
	private static final long serialVersionUID = 1L;

	/**
	 * Constructor for JDFColor
	 * 
	 * @param myOwnerDocument
	 * @param qualifiedName
	 */
	public JDFColor(final CoreDocumentImpl myOwnerDocument, final String qualifiedName)
	{
		super(myOwnerDocument, qualifiedName);
	}

	/**
	 * Constructor for JDFColor
	 * 
	 * @param ownerDocument
	 * @param namespaceURI
	 * @param qualifiedName
	 */
	public JDFColor(final CoreDocumentImpl myOwnerDocument, final String myNamespaceURI, final String qualifiedName)
	{
		super(myOwnerDocument, myNamespaceURI, qualifiedName);
	}

	/**
	 * Constructor for JDFColor
	 * 
	 * @param ownerDocument
	 * @param namespaceURI
	 * @param qualifiedName
	 * @param localName
	 */
	public JDFColor(final CoreDocumentImpl myOwnerDocument, final String myNamespaceURI, final String qualifiedName, final String myLocalName)
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
		return "JDFColor[  --> " + super.toString() + " ]";
	}

	/**
	 * Set the Name and RawName attributes to the value given in pName The value in Name uses the default encoding
	 * 
	 * @param cName the 8 bit string to set the name to
	 */
	public void set8BitNames(final byte[] cName)
	{
		final String rawName = StringUtil.setHexBinaryBytes(cName, -1);
		setRawName(rawName);
		setName(new String(cName));
	}

	/**
	 * get the html color representation of this color in the format 0xrrggbb;
	 */
	public String getHTMLColor()
	{
		JDFRGBColor rgb = new JDFRGBColor();
		if (hasAttribute(AttributeName.SRGB))
		{
			rgb = getsRGB();
		}
		else if (hasAttribute(AttributeName.CMYK))
		{
			final JDFCMYKColor cmyk = getCMYK();
			rgb = cmyk.getRGB();
		}
		return rgb.getHTMLColor();
	}

	/**
	 * Gets the ActualColorName or Name if no ActualColorName is set
	 * 
	 * @return String Name of the color extracted from RawName, or if this is missing from Name, using the default transcoder
	 */
	@Override
	public String getActualColorName()
	{
		final String strName = getAttribute(AttributeName.ACTUALCOLORNAME, null, null);
		return strName == null ? getName() : strName;
	}

	/**
	 * Gets the 16 bit representation of the 8 bit color name Use String GetRawBytes() to extract the 8 bit representation
	 * 
	 * @return String Name of the color extracted from RawName, or if this is missing from Name, using the default transcoder
	 */
	public String get8BitName()
	{
		final String strName = getAttribute(AttributeName.RAWNAME, null, null);
		if (strName != null)
		{
			final byte[] rawName = strName.getBytes();
			final byte[] foundName = StringUtil.getHexBinaryBytes(rawName);

			return new String(foundName);
		}
		return getActualColorName();
	}

	// //////////////////////////////////////////////////////////////

	public JDFFileSpec getColorProfile()
	{
		final VElement v = getChildElementVector(ElementName.FILESPEC, null, null, true, 0, false);
		if (v != null)
		{
			final int siz = v.size();
			for (int i = 0; i < siz; i++)
			{
				final JDFFileSpec res = (JDFFileSpec) v.elementAt(i);
				if (res.hasAttribute(AttributeName.RESOURCEUSAGE))
				{
					if (res.getResourceUsage().equals("ColorProfile"))
					{
						return res;
					}
				}
			}
		}

		return null;
	}

	// /////////////////////////////////////////////////////////////////

	public JDFFileSpec getCreateColorProfile()
	{
		JDFFileSpec res = getColorProfile();
		if (res == null)
		{
			res = appendColorProfile();
		}
		return res;
	}

	// /////////////////////////////////////////////////////////////////

	public JDFFileSpec appendColorProfile()
	{
		final JDFFileSpec res = appendFileSpec();
		res.setResourceUsage("ColorProfile");

		return res;
	}

	// /////////////////////////////////////////////////////////////////
	// /////////////////////////////////////////////////////////////////

	public JDFFileSpec getTargetProfile()
	{
		final VElement v = getChildElementVector(ElementName.FILESPEC, null, null, true, 0, false);

		final int siz = v.size();
		for (int i = 0; i < siz; i++)
		{
			final JDFFileSpec res = (JDFFileSpec) v.elementAt(i);
			if (res.hasAttribute(AttributeName.RESOURCEUSAGE))
			{
				if (res.getResourceUsage().equals("TargetProfile"))
				{
					return res;
				}
			}
		}
		return null;
	}

	// /////////////////////////////////////////////////////////////////

	public JDFFileSpec getCreateTargetProfile()
	{
		JDFFileSpec res = getTargetProfile();
		if (res == null)
		{
			res = appendTargetProfile();
		}
		return res;
	}

	// /////////////////////////////////////////////////////////////////

	JDFFileSpec appendTargetProfile()
	{
		final JDFFileSpec res = appendFileSpec();
		res.setResourceUsage("TargetProfile");

		return res;
	}

	@Override
	public boolean fixVersion(final EnumVersion version)
	{
		if (hasAttribute(AttributeName.USEPDLALTERNATECS))
		{
			if (!hasAttribute(AttributeName.MAPPINGSELECTION))
			{
				setMappingSelection(getUsePDLAlternateCS() ? EnumMappingSelection.UsePDLValues : EnumMappingSelection.UseProcessColorValues);
			}
			removeAttribute(AttributeName.USEPDLALTERNATECS);
		}
		return super.fixVersion(version);
	}

}