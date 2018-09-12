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

package org.cip4.jdflib.auto;

import org.apache.xerces.dom.CoreDocumentImpl;
import org.cip4.jdflib.core.AtrInfoTable;
import org.cip4.jdflib.core.AttributeInfo;
import org.cip4.jdflib.core.AttributeName;
import org.cip4.jdflib.core.ElemInfoTable;
import org.cip4.jdflib.core.ElementInfo;
import org.cip4.jdflib.core.ElementName;
import org.cip4.jdflib.core.JDFException;
import org.cip4.jdflib.core.JDFSeparationList;
import org.cip4.jdflib.resource.intent.JDFIntentResource;
import org.cip4.jdflib.resource.process.JDFColorPool;
import org.cip4.jdflib.span.JDFNameSpan;
import org.cip4.jdflib.span.JDFNumberSpan;
import org.cip4.jdflib.span.JDFStringSpan;

/**
 *****************************************************************************
 * class JDFAutoColorIntent : public JDFIntentResource
 *****************************************************************************
 * 
 */

public abstract class JDFAutoColorIntent extends JDFIntentResource
{

	private static final long serialVersionUID = 1L;

	private static AtrInfoTable[] atrInfoTable = new AtrInfoTable[1];
	static
	{
		atrInfoTable[0] = new AtrInfoTable(AttributeName.NUMCOLORS, 0x33333333, AttributeInfo.EnumAttributeType.integer, null, null);
	}

	@Override
	protected AttributeInfo getTheAttributeInfo()
	{
		return super.getTheAttributeInfo().updateReplace(atrInfoTable);
	}

	private static ElemInfoTable[] elemInfoTable = new ElemInfoTable[7];
	static
	{
		elemInfoTable[0] = new ElemInfoTable(ElementName.COATINGS, 0x66666666);
		elemInfoTable[1] = new ElemInfoTable(ElementName.COLORSTANDARD, 0x66666666);
		elemInfoTable[2] = new ElemInfoTable(ElementName.COLORICCSTANDARD, 0x66666611);
		elemInfoTable[3] = new ElemInfoTable(ElementName.COVERAGE, 0x66666666);
		elemInfoTable[4] = new ElemInfoTable(ElementName.INKMANUFACTURER, 0x77777766);
		elemInfoTable[5] = new ElemInfoTable(ElementName.COLORPOOL, 0x66666661);
		elemInfoTable[6] = new ElemInfoTable(ElementName.COLORSUSED, 0x66666666);
	}

	@Override
	protected ElementInfo getTheElementInfo()
	{
		return super.getTheElementInfo().updateReplace(elemInfoTable);
	}

	/**
	 * Constructor for JDFAutoColorIntent
	 * 
	 * @param myOwnerDocument
	 * @param qualifiedName
	 */
	protected JDFAutoColorIntent(CoreDocumentImpl myOwnerDocument, String qualifiedName)
	{
		super(myOwnerDocument, qualifiedName);
	}

	/**
	 * Constructor for JDFAutoColorIntent
	 * 
	 * @param myOwnerDocument
	 * @param myNamespaceURI
	 * @param qualifiedName
	 */
	protected JDFAutoColorIntent(CoreDocumentImpl myOwnerDocument, String myNamespaceURI, String qualifiedName)
	{
		super(myOwnerDocument, myNamespaceURI, qualifiedName);
	}

	/**
	 * Constructor for JDFAutoColorIntent
	 * 
	 * @param myOwnerDocument
	 * @param myNamespaceURI
	 * @param qualifiedName
	 * @param myLocalName
	 */
	protected JDFAutoColorIntent(CoreDocumentImpl myOwnerDocument, String myNamespaceURI, String qualifiedName, String myLocalName)
	{
		super(myOwnerDocument, myNamespaceURI, qualifiedName, myLocalName);
	}

	/**
	 * @return the string representation
	 */
	@Override
	public String toString()
	{
		return " JDFAutoColorIntent[  --> " + super.toString() + " ]";
	}

	/*
	 * ************************************************************************ Attribute getter / setter ************************************************************************
	 */

	/*
	 * --------------------------------------------------------------------- Methods for Attribute NumColors ---------------------------------------------------------------------
	 */
	/**
	 * (36) set attribute NumColors
	 * 
	 * @param value the value to set the attribute to
	 */
	public void setNumColors(int value)
	{
		setAttribute(AttributeName.NUMCOLORS, value, null);
	}

	/**
	 * (15) get int attribute NumColors
	 * 
	 * @return int the value of the attribute
	 */
	public int getNumColors()
	{
		return getIntAttribute(AttributeName.NUMCOLORS, null, 0);
	}

	/*
	 * *********************************************************************** Element getter / setter ***********************************************************************
	 */

	/**
	 * (24) const get element Coatings
	 * 
	 * @return JDFStringSpan the element
	 */
	public JDFStringSpan getCoatings()
	{
		return (JDFStringSpan) getElement(ElementName.COATINGS, null, 0);
	}

	/**
	 * (25) getCreateCoatings
	 * 
	 * @return JDFStringSpan the element
	 */
	public JDFStringSpan getCreateCoatings()
	{
		return (JDFStringSpan) getCreateElement_KElement(ElementName.COATINGS, null, 0);
	}

	/**
	 * (29) append element Coatings
	 * 
	 * @return JDFStringSpan the element
	 * @throws JDFException if the element already exists
	 */
	public JDFStringSpan appendCoatings() throws JDFException
	{
		return (JDFStringSpan) appendElementN(ElementName.COATINGS, 1, null);
	}

	/**
	 * (24) const get element ColorStandard
	 * 
	 * @return JDFNameSpan the element
	 */
	public JDFNameSpan getColorStandard()
	{
		return (JDFNameSpan) getElement(ElementName.COLORSTANDARD, null, 0);
	}

	/**
	 * (25) getCreateColorStandard
	 * 
	 * @return JDFNameSpan the element
	 */
	public JDFNameSpan getCreateColorStandard()
	{
		return (JDFNameSpan) getCreateElement_KElement(ElementName.COLORSTANDARD, null, 0);
	}

	/**
	 * (29) append element ColorStandard
	 * 
	 * @return JDFNameSpan the element
	 * @throws JDFException if the element already exists
	 */
	public JDFNameSpan appendColorStandard() throws JDFException
	{
		return (JDFNameSpan) appendElementN(ElementName.COLORSTANDARD, 1, null);
	}

	/**
	 * (24) const get element ColorICCStandard
	 * 
	 * @return JDFStringSpan the element
	 */
	public JDFStringSpan getColorICCStandard()
	{
		return (JDFStringSpan) getElement(ElementName.COLORICCSTANDARD, null, 0);
	}

	/**
	 * (25) getCreateColorICCStandard
	 * 
	 * @return JDFStringSpan the element
	 */
	public JDFStringSpan getCreateColorICCStandard()
	{
		return (JDFStringSpan) getCreateElement_KElement(ElementName.COLORICCSTANDARD, null, 0);
	}

	/**
	 * (29) append element ColorICCStandard
	 * 
	 * @return JDFStringSpan the element
	 * @throws JDFException if the element already exists
	 */
	public JDFStringSpan appendColorICCStandard() throws JDFException
	{
		return (JDFStringSpan) appendElementN(ElementName.COLORICCSTANDARD, 1, null);
	}

	/**
	 * (24) const get element Coverage
	 * 
	 * @return JDFNumberSpan the element
	 */
	public JDFNumberSpan getCoverage()
	{
		return (JDFNumberSpan) getElement(ElementName.COVERAGE, null, 0);
	}

	/**
	 * (25) getCreateCoverage
	 * 
	 * @return JDFNumberSpan the element
	 */
	public JDFNumberSpan getCreateCoverage()
	{
		return (JDFNumberSpan) getCreateElement_KElement(ElementName.COVERAGE, null, 0);
	}

	/**
	 * (29) append element Coverage
	 * 
	 * @return JDFNumberSpan the element
	 * @throws JDFException if the element already exists
	 */
	public JDFNumberSpan appendCoverage() throws JDFException
	{
		return (JDFNumberSpan) appendElementN(ElementName.COVERAGE, 1, null);
	}

	/**
	 * (24) const get element InkManufacturer
	 * 
	 * @return JDFNameSpan the element
	 */
	public JDFNameSpan getInkManufacturer()
	{
		return (JDFNameSpan) getElement(ElementName.INKMANUFACTURER, null, 0);
	}

	/**
	 * (25) getCreateInkManufacturer
	 * 
	 * @return JDFNameSpan the element
	 */
	public JDFNameSpan getCreateInkManufacturer()
	{
		return (JDFNameSpan) getCreateElement_KElement(ElementName.INKMANUFACTURER, null, 0);
	}

	/**
	 * (29) append element InkManufacturer
	 * 
	 * @return JDFNameSpan the element
	 * @throws JDFException if the element already exists
	 */
	public JDFNameSpan appendInkManufacturer() throws JDFException
	{
		return (JDFNameSpan) appendElementN(ElementName.INKMANUFACTURER, 1, null);
	}

	/**
	 * (24) const get element ColorPool
	 * 
	 * @return JDFColorPool the element
	 */
	public JDFColorPool getColorPool()
	{
		return (JDFColorPool) getElement(ElementName.COLORPOOL, null, 0);
	}

	/**
	 * (25) getCreateColorPool
	 * 
	 * @return JDFColorPool the element
	 */
	public JDFColorPool getCreateColorPool()
	{
		return (JDFColorPool) getCreateElement_KElement(ElementName.COLORPOOL, null, 0);
	}

	/**
	 * (29) append element ColorPool
	 * 
	 * @return JDFColorPool the element
	 * @throws JDFException if the element already exists
	 */
	public JDFColorPool appendColorPool() throws JDFException
	{
		return (JDFColorPool) appendElementN(ElementName.COLORPOOL, 1, null);
	}

	/**
	 * (31) create inter-resource link to refTarget
	 * 
	 * @param refTarget the element that is referenced
	 */
	public void refColorPool(JDFColorPool refTarget)
	{
		refElement(refTarget);
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
		return (JDFSeparationList) getCreateElement_KElement(ElementName.COLORSUSED, null, 0);
	}

	/**
	 * (29) append element ColorsUsed
	 * 
	 * @return JDFSeparationList the element
	 * @throws JDFException if the element already exists
	 */
	public JDFSeparationList appendColorsUsed() throws JDFException
	{
		return (JDFSeparationList) appendElementN(ElementName.COLORSUSED, 1, null);
	}

}// end namespace JDF
