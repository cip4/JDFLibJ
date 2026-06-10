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

import java.util.Collection;

import org.apache.xerces.dom.CoreDocumentImpl;
import org.cip4.jdflib.core.AtrInfoTable;
import org.cip4.jdflib.core.AttributeInfo;
import org.cip4.jdflib.core.AttributeName;
import org.cip4.jdflib.core.ElemInfoTable;
import org.cip4.jdflib.core.ElementInfo;
import org.cip4.jdflib.core.ElementName;
import org.cip4.jdflib.core.JDFCoreConstants;
import org.cip4.jdflib.core.JDFElement;
import org.cip4.jdflib.core.VString;
import org.cip4.jdflib.resource.process.JDFMarkColor;
import org.cip4.jdflib.util.JavaEnumUtil;

/**
 ***************************************************************************** class JDFAutoFillMark : public JDFElement
 */

public abstract class JDFAutoFillMark extends JDFElement
{

	private static final long serialVersionUID = 1L;

	private static AtrInfoTable[] atrInfoTable = new AtrInfoTable[3];
	static
	{
		atrInfoTable[0] = new AtrInfoTable(AttributeName.KNOCKOUTBLEED, 0x3333333333l, AttributeInfo.EnumAttributeType.double_, null, null);
		atrInfoTable[1] = new AtrInfoTable(AttributeName.KNOCKOUTREFS, 0x3333333333l, AttributeInfo.EnumAttributeType.IDREFS, null, null);
		atrInfoTable[2] = new AtrInfoTable(AttributeName.KNOCKOUTSOURCE, 0x2222222222l, AttributeInfo.EnumAttributeType.enumeration,
				JavaEnumUtil.getEnum(EnumKnockoutSource.class, 0), null);
	}

	@Override
	protected AttributeInfo getTheAttributeInfo()
	{
		return super.getTheAttributeInfo().updateReplace(atrInfoTable);
	}

	private static ElemInfoTable[] elemInfoTable = new ElemInfoTable[1];
	static
	{
		elemInfoTable[0] = new ElemInfoTable(ElementName.MARKCOLOR, 0x3333333333l);
	}

	@Override
	protected ElementInfo getTheElementInfo()
	{
		return super.getTheElementInfo().updateReplace(elemInfoTable);
	}

	/**
	 * Constructor for JDFAutoFillMark
	 *
	 * @param myOwnerDocument
	 * @param qualifiedName
	 */
	protected JDFAutoFillMark(final CoreDocumentImpl myOwnerDocument, final String qualifiedName)
	{
		super(myOwnerDocument, qualifiedName);
	}

	/**
	 * Constructor for JDFAutoFillMark
	 *
	 * @param myOwnerDocument
	 * @param myNamespaceURI
	 * @param qualifiedName
	 */
	protected JDFAutoFillMark(final CoreDocumentImpl myOwnerDocument, final String myNamespaceURI, final String qualifiedName)
	{
		super(myOwnerDocument, myNamespaceURI, qualifiedName);
	}

	/**
	 * Constructor for JDFAutoFillMark
	 *
	 * @param myOwnerDocument
	 * @param myNamespaceURI
	 * @param qualifiedName
	 * @param myLocalName
	 */
	protected JDFAutoFillMark(final CoreDocumentImpl myOwnerDocument, final String myNamespaceURI, final String qualifiedName, final String myLocalName)
	{
		super(myOwnerDocument, myNamespaceURI, qualifiedName, myLocalName);
	}

	/**
	 * Enumeration strings for numKnockoutSource
	 */

	public enum EnumKnockoutSource
	{
		ClipPath, SourceClipPath, TrimClipPath, TrimBox;

		public static EnumKnockoutSource getEnum(final String val)
		{
			return JavaEnumUtil.getEnumIgnoreCase(EnumKnockoutSource.class, val, null);
		}
	}/*
		 * ************************************************************************
		 * Attribute getter / setter
		 * ************************************************************************
		 */

	/*
	 * ---------------------------------------------------------------------
	 * Methods for Attribute KnockoutBleed
	 * ---------------------------------------------------------------------
	 */
	/**
	 * (36) set attribute KnockoutBleed
	 *
	 * @param value the value to set the attribute to
	 */
	public void setKnockoutBleed(final double value)
	{
		setAttribute(AttributeName.KNOCKOUTBLEED, value, null);
	}

	/**
	 * (17) get double attribute KnockoutBleed
	 *
	 * @return double the value of the attribute
	 */
	public double getKnockoutBleed()
	{
		return getRealAttribute(AttributeName.KNOCKOUTBLEED, null, 0.0);
	}

	/*
	 * ---------------------------------------------------------------------
	 * Methods for Attribute KnockoutRefs
	 * ---------------------------------------------------------------------
	 */
	/**
	 * (36) set attribute KnockoutRefs
	 *
	 * @param value the value to set the attribute to
	 */
	public void setKnockoutRefs(final VString value)
	{
		setAttribute(AttributeName.KNOCKOUTREFS, value, null);
	}

	/**
	 * (21) get VString attribute KnockoutRefs
	 *
	 * @return VString the value of the attribute
	 */
	public VString getKnockoutRefs()
	{
		final VString vStrAttrib = new VString();
		final String s = getAttribute(AttributeName.KNOCKOUTREFS, null, JDFCoreConstants.EMPTYSTRING);
		vStrAttrib.setAllStrings(s, " ");
		return vStrAttrib;
	}

	/*
	 * ---------------------------------------------------------------------
	 * Methods for Attribute KnockoutSource
	 * ---------------------------------------------------------------------
	 */
	/**
	 * (5) set attribute KnockoutSource
	 *
	 * @param enumVar the enumVar to set the attribute to
	 */
	public void setKnockoutSource(final EnumKnockoutSource enumVar)
	{
		setAttribute(AttributeName.KNOCKOUTSOURCE, JavaEnumUtil.getName(enumVar), null);
	}

	/**
	 * (9) get attribute KnockoutSource
	 *
	 * @return the value of the attribute
	 */
	public EnumKnockoutSource getKnockoutSource()
	{
		return EnumKnockoutSource.getEnum(getAttribute(AttributeName.KNOCKOUTSOURCE, null, null));
	}

	/*
	 * ***********************************************************************
	 * Element getter / setter
	 * ***********************************************************************
	 */

	/**
	 * (24) const get element MarkColor
	 *
	 * @return JDFMarkColor the element
	 */
	public JDFMarkColor getMarkColor()
	{
		return (JDFMarkColor) getElement(ElementName.MARKCOLOR, null, 0);
	}

	/**
	 * (25) getCreateMarkColor
	 * 
	 * @return JDFMarkColor the element
	 */
	public JDFMarkColor getCreateMarkColor()
	{
		return (JDFMarkColor) getCreateElement_JDFElement(ElementName.MARKCOLOR, null, 0);
	}

	/**
	 * (26) getCreateMarkColor
	 * 
	 * @param iSkip number of elements to skip
	 * @return JDFMarkColor the element
	 */
	public JDFMarkColor getCreateMarkColor(final int iSkip)
	{
		return (JDFMarkColor) getCreateElement_JDFElement(ElementName.MARKCOLOR, null, iSkip);
	}

	/**
	 * (27) const get element MarkColor
	 *
	 * @param iSkip number of elements to skip
	 * @return JDFMarkColor the element
	 *         default is getMarkColor(0)
	 */
	public JDFMarkColor getMarkColor(final int iSkip)
	{
		return (JDFMarkColor) getElement(ElementName.MARKCOLOR, null, iSkip);
	}

	/**
	 * Get all MarkColor from the current element
	 * 
	 * @return Collection<JDFMarkColor>, null if none are available
	 */
	public Collection<JDFMarkColor> getAllMarkColor()
	{
		return getChildArrayByClass(JDFMarkColor.class, false, 0);
	}

	/**
	 * (30) append element MarkColor
	 *
	 * @return JDFMarkColor the element
	 */
	public JDFMarkColor appendMarkColor()
	{
		return (JDFMarkColor) appendElement(ElementName.MARKCOLOR, null);
	}

}
