/*
 * The CIP4 Software License, Version 1.0
 *
 *
 * Copyright (c) 2001-2024 The International Cooperation for the Integration of
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
import org.cip4.jdflib.core.ElemInfoTable;
import org.cip4.jdflib.core.ElementInfo;
import org.cip4.jdflib.core.ElementName;
import org.cip4.jdflib.core.JDFSeparationList;
import org.cip4.jdflib.resource.intent.JDFIntentResource;
import org.cip4.jdflib.span.JDFIntegerSpan;
import org.cip4.jdflib.span.JDFNumberSpan;
import org.cip4.jdflib.span.JDFSpanVariableQuality;
import org.cip4.jdflib.span.JDFSpanVariableType;

/**
 *****************************************************************************
 * class JDFAutoVariableIntent : public JDFIntentResource
 *****************************************************************************
 * 
 */

public abstract class JDFAutoVariableIntent extends JDFIntentResource
{

	private static final long serialVersionUID = 1L;

	private static ElemInfoTable[] elemInfoTable = new ElemInfoTable[8];
	static
	{
		elemInfoTable[0] = new ElemInfoTable(ElementName.AREA, 0x6666111111l);
		elemInfoTable[1] = new ElemInfoTable(ElementName.AVERAGEPAGES, 0x6666111111l);
		elemInfoTable[2] = new ElemInfoTable(ElementName.MAXPAGES, 0x3333111111l);
		elemInfoTable[3] = new ElemInfoTable(ElementName.MINPAGES, 0x6666111111l);
		elemInfoTable[4] = new ElemInfoTable(ElementName.NUMBEROFCOPIES, 0x6666111111l);
		elemInfoTable[5] = new ElemInfoTable(ElementName.VARIABLETYPE, 0x6666111111l);
		elemInfoTable[6] = new ElemInfoTable(ElementName.VARIABLEQUALITY, 0x6666111111l);
		elemInfoTable[7] = new ElemInfoTable(ElementName.COLORSUSED, 0x6666111111l);
	}

	@Override
	protected ElementInfo getTheElementInfo()
	{
		return super.getTheElementInfo().updateReplace(elemInfoTable);
	}

	/**
	 * Constructor for JDFAutoVariableIntent
	 *
	 * @param myOwnerDocument
	 * @param qualifiedName
	 */
	protected JDFAutoVariableIntent(CoreDocumentImpl myOwnerDocument, String qualifiedName)
	{
		super(myOwnerDocument, qualifiedName);
	}

	/**
	 * Constructor for JDFAutoVariableIntent
	 *
	 * @param myOwnerDocument
	 * @param myNamespaceURI
	 * @param qualifiedName
	 */
	protected JDFAutoVariableIntent(CoreDocumentImpl myOwnerDocument, String myNamespaceURI, String qualifiedName)
	{
		super(myOwnerDocument, myNamespaceURI, qualifiedName);
	}

	/**
	 * Constructor for JDFAutoVariableIntent
	 *
	 * @param myOwnerDocument
	 * @param myNamespaceURI
	 * @param qualifiedName
	 * @param myLocalName
	 */
	protected JDFAutoVariableIntent(CoreDocumentImpl myOwnerDocument, String myNamespaceURI, String qualifiedName, String myLocalName)
	{
		super(myOwnerDocument, myNamespaceURI, qualifiedName, myLocalName);
	}

	/*
	 * *********************************************************************** Element getter / setter ***********************************************************************
	 */

	/**
	 * (24) const get element Area
	 *
	 * @return JDFNumberSpan the element
	 */
	public JDFNumberSpan getArea()
	{
		return (JDFNumberSpan) getElement(ElementName.AREA, null, 0);
	}

	/**
	 * (25) getCreateArea
	 * 
	 * @return JDFNumberSpan the element
	 */
	public JDFNumberSpan getCreateArea()
	{
		return (JDFNumberSpan) getCreateElement_JDFElement(ElementName.AREA, null, 0);
	}

	/**
	 * (29) append element Area
	 *
	 * @return JDFNumberSpan the element @ if the element already exists
	 */
	public JDFNumberSpan appendArea()
	{
		return (JDFNumberSpan) appendElementN(ElementName.AREA, 1, null);
	}

	/**
	 * (24) const get element AveragePages
	 *
	 * @return JDFIntegerSpan the element
	 */
	public JDFIntegerSpan getAveragePages()
	{
		return (JDFIntegerSpan) getElement(ElementName.AVERAGEPAGES, null, 0);
	}

	/**
	 * (25) getCreateAveragePages
	 * 
	 * @return JDFIntegerSpan the element
	 */
	public JDFIntegerSpan getCreateAveragePages()
	{
		return (JDFIntegerSpan) getCreateElement_JDFElement(ElementName.AVERAGEPAGES, null, 0);
	}

	/**
	 * (29) append element AveragePages
	 *
	 * @return JDFIntegerSpan the element @ if the element already exists
	 */
	public JDFIntegerSpan appendAveragePages()
	{
		return (JDFIntegerSpan) appendElementN(ElementName.AVERAGEPAGES, 1, null);
	}

	/**
	 * (26) getCreatemaxPages
	 * 
	 * @param iSkip number of elements to skip
	 * @return JDFIntegerSpan the element
	 */
	public JDFIntegerSpan getCreatemaxPages(int iSkip)
	{
		return (JDFIntegerSpan) getCreateElement_JDFElement(ElementName.MAXPAGES, null, iSkip);
	}

	/**
	 * (27) const get element maxPages
	 *
	 * @param iSkip number of elements to skip
	 * @return JDFIntegerSpan the element default is getmaxPages(0)
	 */
	public JDFIntegerSpan getmaxPages(int iSkip)
	{
		return (JDFIntegerSpan) getElement(ElementName.MAXPAGES, null, iSkip);
	}

	/**
	 * Get all maxPages from the current element
	 * 
	 * @return Collection<JDFIntegerSpan>, null if none are available
	 */
	public Collection<JDFIntegerSpan> getAllmaxPages()
	{
		return getChildArrayByClass(JDFIntegerSpan.class, false, 0);
	}

	/**
	 * (30) append element maxPages
	 *
	 * @return JDFIntegerSpan the element
	 */
	public JDFIntegerSpan appendmaxPages()
	{
		return (JDFIntegerSpan) appendElement(ElementName.MAXPAGES, null);
	}

	/**
	 * (24) const get element MinPages
	 *
	 * @return JDFIntegerSpan the element
	 */
	public JDFIntegerSpan getMinPages()
	{
		return (JDFIntegerSpan) getElement(ElementName.MINPAGES, null, 0);
	}

	/**
	 * (25) getCreateMinPages
	 * 
	 * @return JDFIntegerSpan the element
	 */
	public JDFIntegerSpan getCreateMinPages()
	{
		return (JDFIntegerSpan) getCreateElement_JDFElement(ElementName.MINPAGES, null, 0);
	}

	/**
	 * (29) append element MinPages
	 *
	 * @return JDFIntegerSpan the element @ if the element already exists
	 */
	public JDFIntegerSpan appendMinPages()
	{
		return (JDFIntegerSpan) appendElementN(ElementName.MINPAGES, 1, null);
	}

	/**
	 * (24) const get element NumberOfCopies
	 *
	 * @return JDFIntegerSpan the element
	 */
	public JDFIntegerSpan getNumberOfCopies()
	{
		return (JDFIntegerSpan) getElement(ElementName.NUMBEROFCOPIES, null, 0);
	}

	/**
	 * (25) getCreateNumberOfCopies
	 * 
	 * @return JDFIntegerSpan the element
	 */
	public JDFIntegerSpan getCreateNumberOfCopies()
	{
		return (JDFIntegerSpan) getCreateElement_JDFElement(ElementName.NUMBEROFCOPIES, null, 0);
	}

	/**
	 * (29) append element NumberOfCopies
	 *
	 * @return JDFIntegerSpan the element @ if the element already exists
	 */
	public JDFIntegerSpan appendNumberOfCopies()
	{
		return (JDFIntegerSpan) appendElementN(ElementName.NUMBEROFCOPIES, 1, null);
	}

	/**
	 * (24) const get element VariableType
	 *
	 * @return JDFSpanVariableType the element
	 */
	public JDFSpanVariableType getVariableType()
	{
		return (JDFSpanVariableType) getElement(ElementName.VARIABLETYPE, null, 0);
	}

	/**
	 * (25) getCreateVariableType
	 * 
	 * @return JDFSpanVariableType the element
	 */
	public JDFSpanVariableType getCreateVariableType()
	{
		return (JDFSpanVariableType) getCreateElement_JDFElement(ElementName.VARIABLETYPE, null, 0);
	}

	/**
	 * (29) append element VariableType
	 *
	 * @return JDFSpanVariableType the element @ if the element already exists
	 */
	public JDFSpanVariableType appendVariableType()
	{
		return (JDFSpanVariableType) appendElementN(ElementName.VARIABLETYPE, 1, null);
	}

	/**
	 * (24) const get element VariableQuality
	 *
	 * @return JDFSpanVariableQuality the element
	 */
	public JDFSpanVariableQuality getVariableQuality()
	{
		return (JDFSpanVariableQuality) getElement(ElementName.VARIABLEQUALITY, null, 0);
	}

	/**
	 * (25) getCreateVariableQuality
	 * 
	 * @return JDFSpanVariableQuality the element
	 */
	public JDFSpanVariableQuality getCreateVariableQuality()
	{
		return (JDFSpanVariableQuality) getCreateElement_JDFElement(ElementName.VARIABLEQUALITY, null, 0);
	}

	/**
	 * (29) append element VariableQuality
	 *
	 * @return JDFSpanVariableQuality the element @ if the element already exists
	 */
	public JDFSpanVariableQuality appendVariableQuality()
	{
		return (JDFSpanVariableQuality) appendElementN(ElementName.VARIABLEQUALITY, 1, null);
	}

	/**
	 * (24) const get element ColorsUsed
	 *
	 * @return JDFSeparationList the element
	 */
	public JDFSeparationList getColorsUsed()
	{
		return (JDFSeparationList) getElement(ElementName.COLORSUSED, null, 0);
	}

	/**
	 * (25) getCreateColorsUsed
	 * 
	 * @return JDFSeparationList the element
	 */
	public JDFSeparationList getCreateColorsUsed()
	{
		return (JDFSeparationList) getCreateElement_JDFElement(ElementName.COLORSUSED, null, 0);
	}

	/**
	 * (29) append element ColorsUsed
	 *
	 * @return JDFSeparationList the element @ if the element already exists
	 */
	public JDFSeparationList appendColorsUsed()
	{
		return (JDFSeparationList) appendElementN(ElementName.COLORSUSED, 1, null);
	}

}
