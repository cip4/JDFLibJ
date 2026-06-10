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

package org.cip4.jdflib.auto;

import org.apache.xerces.dom.CoreDocumentImpl;
import org.cip4.jdflib.core.AtrInfoTable;
import org.cip4.jdflib.core.AttributeInfo;
import org.cip4.jdflib.core.AttributeName;
import org.cip4.jdflib.core.JDFElement;
import org.cip4.jdflib.datatypes.JDFRectangle;
import org.cip4.jdflib.util.JavaEnumUtil;

/**
 ***************************************************************************** class JDFAutoBoxArgument : public JDFElement
 */

public abstract class JDFAutoBoxArgument extends JDFElement
{

	private static final long serialVersionUID = 1L;

	private static AtrInfoTable[] atrInfoTable = new AtrInfoTable[4];
	static
	{
		atrInfoTable[0] = new AtrInfoTable(AttributeName.BOX, 0x2222222222l, AttributeInfo.EnumAttributeType.enumeration,
				JavaEnumUtil.getEnum(EnumBox.class, 0), null);
		atrInfoTable[1] = new AtrInfoTable(AttributeName.MIRRORMARGINS, 0x3333333333l, AttributeInfo.EnumAttributeType.enumeration,
				JavaEnumUtil.getEnum(EnumMirrorMargins.class, 0), null);
		atrInfoTable[2] = new AtrInfoTable(AttributeName.OFFSET, 0x3333333333l, AttributeInfo.EnumAttributeType.rectangle, null, null);
		atrInfoTable[3] = new AtrInfoTable(AttributeName.OVERLAP, 0x3333333333l, AttributeInfo.EnumAttributeType.boolean_, null, "false");
	}

	@Override
	protected AttributeInfo getTheAttributeInfo()
	{
		return super.getTheAttributeInfo().updateReplace(atrInfoTable);
	}

	/**
	 * Constructor for JDFAutoBoxArgument
	 *
	 * @param myOwnerDocument
	 * @param qualifiedName
	 */
	protected JDFAutoBoxArgument(final CoreDocumentImpl myOwnerDocument, final String qualifiedName)
	{
		super(myOwnerDocument, qualifiedName);
	}

	/**
	 * Constructor for JDFAutoBoxArgument
	 *
	 * @param myOwnerDocument
	 * @param myNamespaceURI
	 * @param qualifiedName
	 */
	protected JDFAutoBoxArgument(final CoreDocumentImpl myOwnerDocument, final String myNamespaceURI, final String qualifiedName)
	{
		super(myOwnerDocument, myNamespaceURI, qualifiedName);
	}

	/**
	 * Constructor for JDFAutoBoxArgument
	 *
	 * @param myOwnerDocument
	 * @param myNamespaceURI
	 * @param qualifiedName
	 * @param myLocalName
	 */
	protected JDFAutoBoxArgument(final CoreDocumentImpl myOwnerDocument, final String myNamespaceURI, final String qualifiedName, final String myLocalName)
	{
		super(myOwnerDocument, myNamespaceURI, qualifiedName, myLocalName);
	}

	/**
	 * Enumeration strings for numBox
	 */

	public enum EnumBox
	{
		ArtBox, BleedBox, CropBox, MarginsBox, MediaBox, SlugBox, TrimBox;

		public static EnumBox getEnum(final String val)
		{
			return JavaEnumUtil.getEnumIgnoreCase(EnumBox.class, val, null);
		}
	}

	/**
	 * Enumeration strings for numMirrorMargins
	 */

	public enum EnumMirrorMargins
	{
		Vertical, Horizontal;

		public static EnumMirrorMargins getEnum(final String val)
		{
			return JavaEnumUtil.getEnumIgnoreCase(EnumMirrorMargins.class, val, null);
		}
	}/*
		 * ************************************************************************
		 * Attribute getter / setter
		 * ************************************************************************
		 */

	/*
	 * ---------------------------------------------------------------------
	 * Methods for Attribute Box
	 * ---------------------------------------------------------------------
	 */
	/**
	 * (5) set attribute Box
	 *
	 * @param enumVar the enumVar to set the attribute to
	 */
	public void setBox(final EnumBox enumVar)
	{
		setAttribute(AttributeName.BOX, JavaEnumUtil.getName(enumVar), null);
	}

	/**
	 * (9) get attribute Box
	 *
	 * @return the value of the attribute
	 */
	public EnumBox getBox()
	{
		return EnumBox.getEnum(getAttribute(AttributeName.BOX, null, null));
	}

	/*
	 * ---------------------------------------------------------------------
	 * Methods for Attribute MirrorMargins
	 * ---------------------------------------------------------------------
	 */
	/**
	 * (5) set attribute MirrorMargins
	 *
	 * @param enumVar the enumVar to set the attribute to
	 */
	public void setMirrorMargins(final EnumMirrorMargins enumVar)
	{
		setAttribute(AttributeName.MIRRORMARGINS, JavaEnumUtil.getName(enumVar), null);
	}

	/**
	 * (9) get attribute MirrorMargins
	 *
	 * @return the value of the attribute
	 */
	public EnumMirrorMargins getMirrorMargins()
	{
		return EnumMirrorMargins.getEnum(getAttribute(AttributeName.MIRRORMARGINS, null, null));
	}

	/*
	 * ---------------------------------------------------------------------
	 * Methods for Attribute Offset
	 * ---------------------------------------------------------------------
	 */
	/**
	 * (36) set attribute Offset
	 *
	 * @param value the value to set the attribute to
	 */
	public void setOffset(final JDFRectangle value)
	{
		setAttribute(AttributeName.OFFSET, value, null);
	}

	/**
	 * (20) get JDFRectangle attribute Offset
	 *
	 * @return JDFRectangle the value of the attribute, null if a the
	 *         attribute value is not a valid to create a JDFRectangle
	 */
	public JDFRectangle getOffset()
	{
		final String strAttrName = getAttribute(AttributeName.OFFSET, null, null);
		final JDFRectangle nPlaceHolder = JDFRectangle.createRectangle(strAttrName);
		return nPlaceHolder;
	}

	/*
	 * ---------------------------------------------------------------------
	 * Methods for Attribute Overlap
	 * ---------------------------------------------------------------------
	 */
	/**
	 * (36) set attribute Overlap
	 *
	 * @param value the value to set the attribute to
	 */
	public void setOverlap(final boolean value)
	{
		setAttribute(AttributeName.OVERLAP, value, null);
	}

	/**
	 * (18) get boolean attribute Overlap
	 *
	 * @return boolean the value of the attribute
	 */
	public boolean getOverlap()
	{
		return getBoolAttribute(AttributeName.OVERLAP, null, false);
	}

}
