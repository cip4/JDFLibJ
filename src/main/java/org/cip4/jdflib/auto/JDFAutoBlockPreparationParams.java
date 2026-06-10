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
import org.cip4.jdflib.resource.JDFRegisterRibbon;
import org.cip4.jdflib.resource.JDFResource;
import org.cip4.jdflib.util.JavaEnumUtil;

/**
 ***************************************************************************** class JDFAutoBlockPreparationParams : public JDFResource
 */

public abstract class JDFAutoBlockPreparationParams extends JDFResource
{

	private static final long serialVersionUID = 1L;

	private static AtrInfoTable[] atrInfoTable = new AtrInfoTable[3];
	static
	{
		atrInfoTable[0] = new AtrInfoTable(AttributeName.BACKING, 0x3333333331l, AttributeInfo.EnumAttributeType.double_, null, null);
		atrInfoTable[1] = new AtrInfoTable(AttributeName.ROUNDING, 0x3333333331l, AttributeInfo.EnumAttributeType.double_, null, null);
		atrInfoTable[2] = new AtrInfoTable(AttributeName.TIGHTBACKING, 0x3333333331l, AttributeInfo.EnumAttributeType.enumeration,
				JavaEnumUtil.getEnum(EnumTightBacking.class, 0), null);
	}

	@Override
	protected AttributeInfo getTheAttributeInfo()
	{
		return super.getTheAttributeInfo().updateReplace(atrInfoTable);
	}

	private static ElemInfoTable[] elemInfoTable = new ElemInfoTable[1];
	static
	{
		elemInfoTable[0] = new ElemInfoTable(ElementName.REGISTERRIBBON, 0x3333333331l);
	}

	@Override
	protected ElementInfo getTheElementInfo()
	{
		return super.getTheElementInfo().updateReplace(elemInfoTable);
	}

	/**
	 * Constructor for JDFAutoBlockPreparationParams
	 *
	 * @param myOwnerDocument
	 * @param qualifiedName
	 */
	protected JDFAutoBlockPreparationParams(final CoreDocumentImpl myOwnerDocument, final String qualifiedName)
	{
		super(myOwnerDocument, qualifiedName);
	}

	/**
	 * Constructor for JDFAutoBlockPreparationParams
	 *
	 * @param myOwnerDocument
	 * @param myNamespaceURI
	 * @param qualifiedName
	 */
	protected JDFAutoBlockPreparationParams(final CoreDocumentImpl myOwnerDocument, final String myNamespaceURI, final String qualifiedName)
	{
		super(myOwnerDocument, myNamespaceURI, qualifiedName);
	}

	/**
	 * Constructor for JDFAutoBlockPreparationParams
	 *
	 * @param myOwnerDocument
	 * @param myNamespaceURI
	 * @param qualifiedName
	 * @param myLocalName
	 */
	protected JDFAutoBlockPreparationParams(final CoreDocumentImpl myOwnerDocument, final String myNamespaceURI, final String qualifiedName, final String myLocalName)
	{
		super(myOwnerDocument, myNamespaceURI, qualifiedName, myLocalName);
	}

	/**
	 * @return true if ok
	 */
	@Override
	public boolean init()
	{
		final boolean bRet = super.init();
		setResourceClass(JDFResource.EnumResourceClass.Parameter);
		return bRet;
	}

	/**
	 * @return the resource Class
	 */
	@Override
	public EnumResourceClass getValidClass()
	{
		return JDFResource.EnumResourceClass.Parameter;
	}

	/**
	 * Enumeration strings for numTightBacking
	 */

	public enum EnumTightBacking
	{
		Flat, Round, FlatBacked, RoundBacked;

		public static EnumTightBacking getEnum(final String val)
		{
			return JavaEnumUtil.getEnumIgnoreCase(EnumTightBacking.class, val, null);
		}
	}/*
		 * ************************************************************************
		 * Attribute getter / setter
		 * ************************************************************************
		 */

	/*
	 * ---------------------------------------------------------------------
	 * Methods for Attribute Backing
	 * ---------------------------------------------------------------------
	 */
	/**
	 * (36) set attribute Backing
	 *
	 * @param value the value to set the attribute to
	 */
	public void setBacking(final double value)
	{
		setAttribute(AttributeName.BACKING, value, null);
	}

	/**
	 * (17) get double attribute Backing
	 *
	 * @return double the value of the attribute
	 */
	public double getBacking()
	{
		return getRealAttribute(AttributeName.BACKING, null, 0.0);
	}

	/*
	 * ---------------------------------------------------------------------
	 * Methods for Attribute Rounding
	 * ---------------------------------------------------------------------
	 */
	/**
	 * (36) set attribute Rounding
	 *
	 * @param value the value to set the attribute to
	 */
	public void setRounding(final double value)
	{
		setAttribute(AttributeName.ROUNDING, value, null);
	}

	/**
	 * (17) get double attribute Rounding
	 *
	 * @return double the value of the attribute
	 */
	public double getRounding()
	{
		return getRealAttribute(AttributeName.ROUNDING, null, 0.0);
	}

	/*
	 * ---------------------------------------------------------------------
	 * Methods for Attribute TightBacking
	 * ---------------------------------------------------------------------
	 */
	/**
	 * (5) set attribute TightBacking
	 *
	 * @param enumVar the enumVar to set the attribute to
	 */
	public void setTightBacking(final EnumTightBacking enumVar)
	{
		setAttribute(AttributeName.TIGHTBACKING, JavaEnumUtil.getName(enumVar), null);
	}

	/**
	 * (9) get attribute TightBacking
	 *
	 * @return the value of the attribute
	 */
	public EnumTightBacking getTightBacking()
	{
		return EnumTightBacking.getEnum(getAttribute(AttributeName.TIGHTBACKING, null, null));
	}

	/*
	 * ***********************************************************************
	 * Element getter / setter
	 * ***********************************************************************
	 */

	/**
	 * (24) const get element RegisterRibbon
	 *
	 * @return JDFRegisterRibbon the element
	 */
	public JDFRegisterRibbon getRegisterRibbon()
	{
		return (JDFRegisterRibbon) getElement(ElementName.REGISTERRIBBON, null, 0);
	}

	/**
	 * (25) getCreateRegisterRibbon
	 * 
	 * @return JDFRegisterRibbon the element
	 */
	public JDFRegisterRibbon getCreateRegisterRibbon()
	{
		return (JDFRegisterRibbon) getCreateElement_JDFElement(ElementName.REGISTERRIBBON, null, 0);
	}

	/**
	 * (26) getCreateRegisterRibbon
	 * 
	 * @param iSkip number of elements to skip
	 * @return JDFRegisterRibbon the element
	 */
	public JDFRegisterRibbon getCreateRegisterRibbon(final int iSkip)
	{
		return (JDFRegisterRibbon) getCreateElement_JDFElement(ElementName.REGISTERRIBBON, null, iSkip);
	}

	/**
	 * (27) const get element RegisterRibbon
	 *
	 * @param iSkip number of elements to skip
	 * @return JDFRegisterRibbon the element
	 *         default is getRegisterRibbon(0)
	 */
	public JDFRegisterRibbon getRegisterRibbon(final int iSkip)
	{
		return (JDFRegisterRibbon) getElement(ElementName.REGISTERRIBBON, null, iSkip);
	}

	/**
	 * Get all RegisterRibbon from the current element
	 * 
	 * @return Collection<JDFRegisterRibbon>, null if none are available
	 */
	public Collection<JDFRegisterRibbon> getAllRegisterRibbon()
	{
		return getChildArrayByClass(JDFRegisterRibbon.class, false, 0);
	}

	/**
	 * (30) append element RegisterRibbon
	 *
	 * @return JDFRegisterRibbon the element
	 */
	public JDFRegisterRibbon appendRegisterRibbon()
	{
		return (JDFRegisterRibbon) appendElement(ElementName.REGISTERRIBBON, null);
	}

	/**
	 * (31) create inter-resource link to refTarget
	 *
	 * @param refTarget the element that is referenced
	 */
	public void refRegisterRibbon(final JDFRegisterRibbon refTarget)
	{
		refElement(refTarget);
	}

}
