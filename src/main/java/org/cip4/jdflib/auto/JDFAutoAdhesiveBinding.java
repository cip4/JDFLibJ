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
import org.cip4.jdflib.core.ElemInfoTable;
import org.cip4.jdflib.core.ElementInfo;
import org.cip4.jdflib.core.ElementName;
import org.cip4.jdflib.core.JDFElement;
import org.cip4.jdflib.span.JDFOptionSpan;
import org.cip4.jdflib.span.JDFSpanGlue;
import org.cip4.jdflib.span.JDFSpanScoring;

/**
 *****************************************************************************
 * class JDFAutoAdhesiveBinding : public JDFElement
 *****************************************************************************
 * 
 */

public abstract class JDFAutoAdhesiveBinding extends JDFElement
{

	private static final long serialVersionUID = 1L;

	private static ElemInfoTable[] elemInfoTable = new ElemInfoTable[3];
	static
	{
		elemInfoTable[0] = new ElemInfoTable(ElementName.SCORING, 0x7777777776l);
		elemInfoTable[1] = new ElemInfoTable(ElementName.SPINEGLUE, 0x7777777776l);
		elemInfoTable[2] = new ElemInfoTable(ElementName.TAPEBINDING, 0x7777777776l);
	}

	@Override
	protected ElementInfo getTheElementInfo()
	{
		return super.getTheElementInfo().updateReplace(elemInfoTable);
	}

	/**
	 * Constructor for JDFAutoAdhesiveBinding
	 *
	 * @param myOwnerDocument
	 * @param qualifiedName
	 */
	protected JDFAutoAdhesiveBinding(CoreDocumentImpl myOwnerDocument, String qualifiedName)
	{
		super(myOwnerDocument, qualifiedName);
	}

	/**
	 * Constructor for JDFAutoAdhesiveBinding
	 *
	 * @param myOwnerDocument
	 * @param myNamespaceURI
	 * @param qualifiedName
	 */
	protected JDFAutoAdhesiveBinding(CoreDocumentImpl myOwnerDocument, String myNamespaceURI, String qualifiedName)
	{
		super(myOwnerDocument, myNamespaceURI, qualifiedName);
	}

	/**
	 * Constructor for JDFAutoAdhesiveBinding
	 *
	 * @param myOwnerDocument
	 * @param myNamespaceURI
	 * @param qualifiedName
	 * @param myLocalName
	 */
	protected JDFAutoAdhesiveBinding(CoreDocumentImpl myOwnerDocument, String myNamespaceURI, String qualifiedName, String myLocalName)
	{
		super(myOwnerDocument, myNamespaceURI, qualifiedName, myLocalName);
	}

	/*
	 * *********************************************************************** Element getter / setter ***********************************************************************
	 */

	/**
	 * (24) const get element Scoring
	 *
	 * @return JDFSpanScoring the element
	 */
	public JDFSpanScoring getScoring()
	{
		return (JDFSpanScoring) getElement(ElementName.SCORING, null, 0);
	}

	/**
	 * (25) getCreateScoring
	 * 
	 * @return JDFSpanScoring the element
	 */
	public JDFSpanScoring getCreateScoring()
	{
		return (JDFSpanScoring) getCreateElement_JDFElement(ElementName.SCORING, null, 0);
	}

	/**
	 * (29) append element Scoring
	 *
	 * @return JDFSpanScoring the element @ if the element already exists
	 */
	public JDFSpanScoring appendScoring()
	{
		return (JDFSpanScoring) appendElementN(ElementName.SCORING, 1, null);
	}

	/**
	 * (24) const get element SpineGlue
	 *
	 * @return JDFSpanGlue the element
	 */
	public JDFSpanGlue getSpineGlue()
	{
		return (JDFSpanGlue) getElement(ElementName.SPINEGLUE, null, 0);
	}

	/**
	 * (25) getCreateSpineGlue
	 * 
	 * @return JDFSpanGlue the element
	 */
	public JDFSpanGlue getCreateSpineGlue()
	{
		return (JDFSpanGlue) getCreateElement_JDFElement(ElementName.SPINEGLUE, null, 0);
	}

	/**
	 * (29) append element SpineGlue
	 *
	 * @return JDFSpanGlue the element @ if the element already exists
	 */
	public JDFSpanGlue appendSpineGlue()
	{
		return (JDFSpanGlue) appendElementN(ElementName.SPINEGLUE, 1, null);
	}

	/**
	 * (24) const get element TapeBinding
	 *
	 * @return JDFOptionSpan the element
	 */
	public JDFOptionSpan getTapeBinding()
	{
		return (JDFOptionSpan) getElement(ElementName.TAPEBINDING, null, 0);
	}

	/**
	 * (25) getCreateTapeBinding
	 * 
	 * @return JDFOptionSpan the element
	 */
	public JDFOptionSpan getCreateTapeBinding()
	{
		return (JDFOptionSpan) getCreateElement_JDFElement(ElementName.TAPEBINDING, null, 0);
	}

	/**
	 * (29) append element TapeBinding
	 *
	 * @return JDFOptionSpan the element @ if the element already exists
	 */
	public JDFOptionSpan appendTapeBinding()
	{
		return (JDFOptionSpan) appendElementN(ElementName.TAPEBINDING, 1, null);
	}

}
