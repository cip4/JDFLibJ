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
import org.cip4.jdflib.datatypes.JDFXYPair;
import org.cip4.jdflib.util.JavaEnumUtil;

/**
 ***************************************************************************** class JDFAutoFitPolicy : public JDFElement
 */

public abstract class JDFAutoFitPolicy extends JDFElement
{

	private static final long serialVersionUID = 1L;

	private static AtrInfoTable[] atrInfoTable = new AtrInfoTable[5];
	static
	{
		atrInfoTable[0] = new AtrInfoTable(AttributeName.CLIPOFFSET, 0x3333333333l, AttributeInfo.EnumAttributeType.XYPair, null, null);
		atrInfoTable[1] = new AtrInfoTable(AttributeName.GUTTERPOLICY, 0x3333333333l, AttributeInfo.EnumAttributeType.enumeration,
				JavaEnumUtil.getEnum(EnumGutterPolicy.class, 0), "Fixed");
		atrInfoTable[2] = new AtrInfoTable(AttributeName.MINGUTTER, 0x3333333333l, AttributeInfo.EnumAttributeType.XYPair, null, null);
		atrInfoTable[3] = new AtrInfoTable(AttributeName.ROTATEPOLICY, 0x3333333333l, AttributeInfo.EnumAttributeType.enumeration,
				JavaEnumUtil.getEnum(EnumRotatePolicy.class, 0), null);
		atrInfoTable[4] = new AtrInfoTable(AttributeName.SIZEPOLICY, 0x3333333333l, AttributeInfo.EnumAttributeType.enumeration,
				JavaEnumUtil.getEnum(EnumSizePolicy.class, 0), null);
	}

	@Override
	protected AttributeInfo getTheAttributeInfo()
	{
		return super.getTheAttributeInfo().updateReplace(atrInfoTable);
	}

	/**
	 * Constructor for JDFAutoFitPolicy
	 *
	 * @param myOwnerDocument
	 * @param qualifiedName
	 */
	protected JDFAutoFitPolicy(final CoreDocumentImpl myOwnerDocument, final String qualifiedName)
	{
		super(myOwnerDocument, qualifiedName);
	}

	/**
	 * Constructor for JDFAutoFitPolicy
	 *
	 * @param myOwnerDocument
	 * @param myNamespaceURI
	 * @param qualifiedName
	 */
	protected JDFAutoFitPolicy(final CoreDocumentImpl myOwnerDocument, final String myNamespaceURI, final String qualifiedName)
	{
		super(myOwnerDocument, myNamespaceURI, qualifiedName);
	}

	/**
	 * Constructor for JDFAutoFitPolicy
	 *
	 * @param myOwnerDocument
	 * @param myNamespaceURI
	 * @param qualifiedName
	 * @param myLocalName
	 */
	protected JDFAutoFitPolicy(final CoreDocumentImpl myOwnerDocument, final String myNamespaceURI, final String qualifiedName, final String myLocalName)
	{
		super(myOwnerDocument, myNamespaceURI, qualifiedName, myLocalName);
	}

	/**
	 * Enumeration strings for numGutterPolicy
	 */

	public enum EnumGutterPolicy
	{
		Distribute, Fixed;

		public static EnumGutterPolicy getEnum(final String val)
		{
			return JavaEnumUtil.getEnumIgnoreCase(EnumGutterPolicy.class, val, EnumGutterPolicy.Fixed);
		}
	}

	/**
	 * Enumeration strings for numRotatePolicy
	 */

	public enum EnumRotatePolicy
	{
		NoRotate, RotateOrthogonal, RotateClockwise, RotateCounterClockwise;

		public static EnumRotatePolicy getEnum(final String val)
		{
			return JavaEnumUtil.getEnumIgnoreCase(EnumRotatePolicy.class, val, null);
		}
	}

	/**
	 * Enumeration strings for numSizePolicy
	 */

	public enum EnumSizePolicy
	{
		ClipToMaxPage, Abort, FitToPage, ReduceToFit, Tile;

		public static EnumSizePolicy getEnum(final String val)
		{
			return JavaEnumUtil.getEnumIgnoreCase(EnumSizePolicy.class, val, null);
		}
	}/*
		 * ************************************************************************
		 * Attribute getter / setter
		 * ************************************************************************
		 */

	/*
	 * ---------------------------------------------------------------------
	 * Methods for Attribute ClipOffset
	 * ---------------------------------------------------------------------
	 */
	/**
	 * (36) set attribute ClipOffset
	 *
	 * @param value the value to set the attribute to
	 */
	public void setClipOffset(final JDFXYPair value)
	{
		setAttribute(AttributeName.CLIPOFFSET, value, null);
	}

	/**
	 * (20) get JDFXYPair attribute ClipOffset
	 *
	 * @return JDFXYPair the value of the attribute, null if a the
	 *         attribute value is not a valid to create a JDFXYPair
	 */
	public JDFXYPair getClipOffset()
	{
		final String strAttrName = getAttribute(AttributeName.CLIPOFFSET, null, null);
		final JDFXYPair nPlaceHolder = JDFXYPair.createXYPair(strAttrName);
		return nPlaceHolder;
	}

	/*
	 * ---------------------------------------------------------------------
	 * Methods for Attribute GutterPolicy
	 * ---------------------------------------------------------------------
	 */
	/**
	 * (5) set attribute GutterPolicy
	 *
	 * @param enumVar the enumVar to set the attribute to
	 */
	public void setGutterPolicy(final EnumGutterPolicy enumVar)
	{
		setAttribute(AttributeName.GUTTERPOLICY, JavaEnumUtil.getName(enumVar), null);
	}

	/**
	 * (9) get attribute GutterPolicy
	 *
	 * @return the value of the attribute
	 */
	public EnumGutterPolicy getGutterPolicy()
	{
		return EnumGutterPolicy.getEnum(getAttribute(AttributeName.GUTTERPOLICY, null, "Fixed"));
	}

	/*
	 * ---------------------------------------------------------------------
	 * Methods for Attribute MinGutter
	 * ---------------------------------------------------------------------
	 */
	/**
	 * (36) set attribute MinGutter
	 *
	 * @param value the value to set the attribute to
	 */
	public void setMinGutter(final JDFXYPair value)
	{
		setAttribute(AttributeName.MINGUTTER, value, null);
	}

	/**
	 * (20) get JDFXYPair attribute MinGutter
	 *
	 * @return JDFXYPair the value of the attribute, null if a the
	 *         attribute value is not a valid to create a JDFXYPair
	 */
	public JDFXYPair getMinGutter()
	{
		final String strAttrName = getAttribute(AttributeName.MINGUTTER, null, null);
		final JDFXYPair nPlaceHolder = JDFXYPair.createXYPair(strAttrName);
		return nPlaceHolder;
	}

	/*
	 * ---------------------------------------------------------------------
	 * Methods for Attribute RotatePolicy
	 * ---------------------------------------------------------------------
	 */
	/**
	 * (5) set attribute RotatePolicy
	 *
	 * @param enumVar the enumVar to set the attribute to
	 */
	public void setRotatePolicy(final EnumRotatePolicy enumVar)
	{
		setAttribute(AttributeName.ROTATEPOLICY, JavaEnumUtil.getName(enumVar), null);
	}

	/**
	 * (9) get attribute RotatePolicy
	 *
	 * @return the value of the attribute
	 */
	public EnumRotatePolicy getRotatePolicy()
	{
		return EnumRotatePolicy.getEnum(getAttribute(AttributeName.ROTATEPOLICY, null, null));
	}

	/*
	 * ---------------------------------------------------------------------
	 * Methods for Attribute SizePolicy
	 * ---------------------------------------------------------------------
	 */
	/**
	 * (5) set attribute SizePolicy
	 *
	 * @param enumVar the enumVar to set the attribute to
	 */
	public void setSizePolicy(final EnumSizePolicy enumVar)
	{
		setAttribute(AttributeName.SIZEPOLICY, JavaEnumUtil.getName(enumVar), null);
	}

	/**
	 * (9) get attribute SizePolicy
	 *
	 * @return the value of the attribute
	 */
	public EnumSizePolicy getSizePolicy()
	{
		return EnumSizePolicy.getEnum(getAttribute(AttributeName.SIZEPOLICY, null, null));
	}

}
