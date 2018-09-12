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
import org.cip4.jdflib.core.ElemInfoTable;
import org.cip4.jdflib.core.ElementInfo;
import org.cip4.jdflib.core.ElementName;
import org.cip4.jdflib.core.JDFElement;
import org.cip4.jdflib.core.JDFException;
import org.cip4.jdflib.span.JDFNumberSpan;
import org.cip4.jdflib.span.JDFOptionSpan;
import org.cip4.jdflib.span.JDFSpanGlue;
import org.cip4.jdflib.span.JDFSpanGlueProcedure;
import org.cip4.jdflib.span.JDFSpanScoring;

/**
 *****************************************************************************
 * class JDFAutoSoftCoverBinding : public JDFElement
 *****************************************************************************
 * 
 */

public abstract class JDFAutoSoftCoverBinding extends JDFElement
{

	private static final long serialVersionUID = 1L;

	private static ElemInfoTable[] elemInfoTable = new ElemInfoTable[14];
	static
	{
		elemInfoTable[0] = new ElemInfoTable(ElementName.BLOCKTHREADSEWING, 0x66666661);
		elemInfoTable[1] = new ElemInfoTable(ElementName.ENDSHEETS, 0x66666661);
		elemInfoTable[2] = new ElemInfoTable(ElementName.FOLDINGWIDTH, 0x66666661);
		elemInfoTable[3] = new ElemInfoTable(ElementName.FOLDINGWIDTHBACK, 0x66666661);
		elemInfoTable[4] = new ElemInfoTable(ElementName.GLUEPROCEDURE, 0x66666661);
		elemInfoTable[5] = new ElemInfoTable(ElementName.SCORING, 0x66666661);
		elemInfoTable[6] = new ElemInfoTable(ElementName.SPINEBRUSHING, 0x66666661);
		elemInfoTable[7] = new ElemInfoTable(ElementName.SPINEFIBERROUGHING, 0x66666661);
		elemInfoTable[8] = new ElemInfoTable(ElementName.SPINEGLUE, 0x66666661);
		elemInfoTable[9] = new ElemInfoTable(ElementName.SPINELEVELLING, 0x66666661);
		elemInfoTable[10] = new ElemInfoTable(ElementName.SPINEMILLING, 0x66666661);
		elemInfoTable[11] = new ElemInfoTable(ElementName.SPINENOTCHING, 0x66666661);
		elemInfoTable[12] = new ElemInfoTable(ElementName.SPINESANDING, 0x66666661);
		elemInfoTable[13] = new ElemInfoTable(ElementName.SPINESHREDDING, 0x66666661);
	}

	@Override
	protected ElementInfo getTheElementInfo()
	{
		return super.getTheElementInfo().updateReplace(elemInfoTable);
	}

	/**
	 * Constructor for JDFAutoSoftCoverBinding
	 * 
	 * @param myOwnerDocument
	 * @param qualifiedName
	 */
	protected JDFAutoSoftCoverBinding(CoreDocumentImpl myOwnerDocument, String qualifiedName)
	{
		super(myOwnerDocument, qualifiedName);
	}

	/**
	 * Constructor for JDFAutoSoftCoverBinding
	 * 
	 * @param myOwnerDocument
	 * @param myNamespaceURI
	 * @param qualifiedName
	 */
	protected JDFAutoSoftCoverBinding(CoreDocumentImpl myOwnerDocument, String myNamespaceURI, String qualifiedName)
	{
		super(myOwnerDocument, myNamespaceURI, qualifiedName);
	}

	/**
	 * Constructor for JDFAutoSoftCoverBinding
	 * 
	 * @param myOwnerDocument
	 * @param myNamespaceURI
	 * @param qualifiedName
	 * @param myLocalName
	 */
	protected JDFAutoSoftCoverBinding(CoreDocumentImpl myOwnerDocument, String myNamespaceURI, String qualifiedName, String myLocalName)
	{
		super(myOwnerDocument, myNamespaceURI, qualifiedName, myLocalName);
	}

	/**
	 * @return the string representation
	 */
	@Override
	public String toString()
	{
		return " JDFAutoSoftCoverBinding[  --> " + super.toString() + " ]";
	}

	/*
	 * *********************************************************************** Element getter / setter ***********************************************************************
	 */

	/**
	 * (24) const get element BlockThreadSewing
	 * 
	 * @return JDFOptionSpan the element
	 */
	public JDFOptionSpan getBlockThreadSewing()
	{
		return (JDFOptionSpan) getElement(ElementName.BLOCKTHREADSEWING, null, 0);
	}

	/**
	 * (25) getCreateBlockThreadSewing
	 * 
	 * @return JDFOptionSpan the element
	 */
	public JDFOptionSpan getCreateBlockThreadSewing()
	{
		return (JDFOptionSpan) getCreateElement_JDFElement(ElementName.BLOCKTHREADSEWING, null, 0);
	}

	/**
	 * (29) append element BlockThreadSewing
	 * 
	 * @return JDFOptionSpan the element
	 * @throws JDFException if the element already exists
	 */
	public JDFOptionSpan appendBlockThreadSewing() throws JDFException
	{
		return (JDFOptionSpan) appendElementN(ElementName.BLOCKTHREADSEWING, 1, null);
	}

	/**
	 * (24) const get element EndSheets
	 * 
	 * @return JDFOptionSpan the element
	 */
	public JDFOptionSpan getEndSheets()
	{
		return (JDFOptionSpan) getElement(ElementName.ENDSHEETS, null, 0);
	}

	/**
	 * (25) getCreateEndSheets
	 * 
	 * @return JDFOptionSpan the element
	 */
	public JDFOptionSpan getCreateEndSheets()
	{
		return (JDFOptionSpan) getCreateElement_JDFElement(ElementName.ENDSHEETS, null, 0);
	}

	/**
	 * (29) append element EndSheets
	 * 
	 * @return JDFOptionSpan the element
	 * @throws JDFException if the element already exists
	 */
	public JDFOptionSpan appendEndSheets() throws JDFException
	{
		return (JDFOptionSpan) appendElementN(ElementName.ENDSHEETS, 1, null);
	}

	/**
	 * (24) const get element FoldingWidth
	 * 
	 * @return JDFNumberSpan the element
	 */
	public JDFNumberSpan getFoldingWidth()
	{
		return (JDFNumberSpan) getElement(ElementName.FOLDINGWIDTH, null, 0);
	}

	/**
	 * (25) getCreateFoldingWidth
	 * 
	 * @return JDFNumberSpan the element
	 */
	public JDFNumberSpan getCreateFoldingWidth()
	{
		return (JDFNumberSpan) getCreateElement_JDFElement(ElementName.FOLDINGWIDTH, null, 0);
	}

	/**
	 * (29) append element FoldingWidth
	 * 
	 * @return JDFNumberSpan the element
	 * @throws JDFException if the element already exists
	 */
	public JDFNumberSpan appendFoldingWidth() throws JDFException
	{
		return (JDFNumberSpan) appendElementN(ElementName.FOLDINGWIDTH, 1, null);
	}

	/**
	 * (24) const get element FoldingWidthBack
	 * 
	 * @return JDFNumberSpan the element
	 */
	public JDFNumberSpan getFoldingWidthBack()
	{
		return (JDFNumberSpan) getElement(ElementName.FOLDINGWIDTHBACK, null, 0);
	}

	/**
	 * (25) getCreateFoldingWidthBack
	 * 
	 * @return JDFNumberSpan the element
	 */
	public JDFNumberSpan getCreateFoldingWidthBack()
	{
		return (JDFNumberSpan) getCreateElement_JDFElement(ElementName.FOLDINGWIDTHBACK, null, 0);
	}

	/**
	 * (29) append element FoldingWidthBack
	 * 
	 * @return JDFNumberSpan the element
	 * @throws JDFException if the element already exists
	 */
	public JDFNumberSpan appendFoldingWidthBack() throws JDFException
	{
		return (JDFNumberSpan) appendElementN(ElementName.FOLDINGWIDTHBACK, 1, null);
	}

	/**
	 * (24) const get element GlueProcedure
	 * 
	 * @return JDFSpanGlueProcedure the element
	 */
	public JDFSpanGlueProcedure getGlueProcedure()
	{
		return (JDFSpanGlueProcedure) getElement(ElementName.GLUEPROCEDURE, null, 0);
	}

	/**
	 * (25) getCreateGlueProcedure
	 * 
	 * @return JDFSpanGlueProcedure the element
	 */
	public JDFSpanGlueProcedure getCreateGlueProcedure()
	{
		return (JDFSpanGlueProcedure) getCreateElement_JDFElement(ElementName.GLUEPROCEDURE, null, 0);
	}

	/**
	 * (29) append element GlueProcedure
	 * 
	 * @return JDFSpanGlueProcedure the element
	 * @throws JDFException if the element already exists
	 */
	public JDFSpanGlueProcedure appendGlueProcedure() throws JDFException
	{
		return (JDFSpanGlueProcedure) appendElementN(ElementName.GLUEPROCEDURE, 1, null);
	}

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
	 * @return JDFSpanScoring the element
	 * @throws JDFException if the element already exists
	 */
	public JDFSpanScoring appendScoring() throws JDFException
	{
		return (JDFSpanScoring) appendElementN(ElementName.SCORING, 1, null);
	}

	/**
	 * (24) const get element SpineBrushing
	 * 
	 * @return JDFOptionSpan the element
	 */
	public JDFOptionSpan getSpineBrushing()
	{
		return (JDFOptionSpan) getElement(ElementName.SPINEBRUSHING, null, 0);
	}

	/**
	 * (25) getCreateSpineBrushing
	 * 
	 * @return JDFOptionSpan the element
	 */
	public JDFOptionSpan getCreateSpineBrushing()
	{
		return (JDFOptionSpan) getCreateElement_JDFElement(ElementName.SPINEBRUSHING, null, 0);
	}

	/**
	 * (29) append element SpineBrushing
	 * 
	 * @return JDFOptionSpan the element
	 * @throws JDFException if the element already exists
	 */
	public JDFOptionSpan appendSpineBrushing() throws JDFException
	{
		return (JDFOptionSpan) appendElementN(ElementName.SPINEBRUSHING, 1, null);
	}

	/**
	 * (24) const get element SpineFiberRoughing
	 * 
	 * @return JDFOptionSpan the element
	 */
	public JDFOptionSpan getSpineFiberRoughing()
	{
		return (JDFOptionSpan) getElement(ElementName.SPINEFIBERROUGHING, null, 0);
	}

	/**
	 * (25) getCreateSpineFiberRoughing
	 * 
	 * @return JDFOptionSpan the element
	 */
	public JDFOptionSpan getCreateSpineFiberRoughing()
	{
		return (JDFOptionSpan) getCreateElement_JDFElement(ElementName.SPINEFIBERROUGHING, null, 0);
	}

	/**
	 * (29) append element SpineFiberRoughing
	 * 
	 * @return JDFOptionSpan the element
	 * @throws JDFException if the element already exists
	 */
	public JDFOptionSpan appendSpineFiberRoughing() throws JDFException
	{
		return (JDFOptionSpan) appendElementN(ElementName.SPINEFIBERROUGHING, 1, null);
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
	 * @return JDFSpanGlue the element
	 * @throws JDFException if the element already exists
	 */
	public JDFSpanGlue appendSpineGlue() throws JDFException
	{
		return (JDFSpanGlue) appendElementN(ElementName.SPINEGLUE, 1, null);
	}

	/**
	 * (24) const get element SpineLevelling
	 * 
	 * @return JDFOptionSpan the element
	 */
	public JDFOptionSpan getSpineLevelling()
	{
		return (JDFOptionSpan) getElement(ElementName.SPINELEVELLING, null, 0);
	}

	/**
	 * (25) getCreateSpineLevelling
	 * 
	 * @return JDFOptionSpan the element
	 */
	public JDFOptionSpan getCreateSpineLevelling()
	{
		return (JDFOptionSpan) getCreateElement_JDFElement(ElementName.SPINELEVELLING, null, 0);
	}

	/**
	 * (29) append element SpineLevelling
	 * 
	 * @return JDFOptionSpan the element
	 * @throws JDFException if the element already exists
	 */
	public JDFOptionSpan appendSpineLevelling() throws JDFException
	{
		return (JDFOptionSpan) appendElementN(ElementName.SPINELEVELLING, 1, null);
	}

	/**
	 * (24) const get element SpineMilling
	 * 
	 * @return JDFOptionSpan the element
	 */
	public JDFOptionSpan getSpineMilling()
	{
		return (JDFOptionSpan) getElement(ElementName.SPINEMILLING, null, 0);
	}

	/**
	 * (25) getCreateSpineMilling
	 * 
	 * @return JDFOptionSpan the element
	 */
	public JDFOptionSpan getCreateSpineMilling()
	{
		return (JDFOptionSpan) getCreateElement_JDFElement(ElementName.SPINEMILLING, null, 0);
	}

	/**
	 * (29) append element SpineMilling
	 * 
	 * @return JDFOptionSpan the element
	 * @throws JDFException if the element already exists
	 */
	public JDFOptionSpan appendSpineMilling() throws JDFException
	{
		return (JDFOptionSpan) appendElementN(ElementName.SPINEMILLING, 1, null);
	}

	/**
	 * (24) const get element SpineNotching
	 * 
	 * @return JDFOptionSpan the element
	 */
	public JDFOptionSpan getSpineNotching()
	{
		return (JDFOptionSpan) getElement(ElementName.SPINENOTCHING, null, 0);
	}

	/**
	 * (25) getCreateSpineNotching
	 * 
	 * @return JDFOptionSpan the element
	 */
	public JDFOptionSpan getCreateSpineNotching()
	{
		return (JDFOptionSpan) getCreateElement_JDFElement(ElementName.SPINENOTCHING, null, 0);
	}

	/**
	 * (29) append element SpineNotching
	 * 
	 * @return JDFOptionSpan the element
	 * @throws JDFException if the element already exists
	 */
	public JDFOptionSpan appendSpineNotching() throws JDFException
	{
		return (JDFOptionSpan) appendElementN(ElementName.SPINENOTCHING, 1, null);
	}

	/**
	 * (24) const get element SpineSanding
	 * 
	 * @return JDFOptionSpan the element
	 */
	public JDFOptionSpan getSpineSanding()
	{
		return (JDFOptionSpan) getElement(ElementName.SPINESANDING, null, 0);
	}

	/**
	 * (25) getCreateSpineSanding
	 * 
	 * @return JDFOptionSpan the element
	 */
	public JDFOptionSpan getCreateSpineSanding()
	{
		return (JDFOptionSpan) getCreateElement_JDFElement(ElementName.SPINESANDING, null, 0);
	}

	/**
	 * (29) append element SpineSanding
	 * 
	 * @return JDFOptionSpan the element
	 * @throws JDFException if the element already exists
	 */
	public JDFOptionSpan appendSpineSanding() throws JDFException
	{
		return (JDFOptionSpan) appendElementN(ElementName.SPINESANDING, 1, null);
	}

	/**
	 * (24) const get element SpineShredding
	 * 
	 * @return JDFOptionSpan the element
	 */
	public JDFOptionSpan getSpineShredding()
	{
		return (JDFOptionSpan) getElement(ElementName.SPINESHREDDING, null, 0);
	}

	/**
	 * (25) getCreateSpineShredding
	 * 
	 * @return JDFOptionSpan the element
	 */
	public JDFOptionSpan getCreateSpineShredding()
	{
		return (JDFOptionSpan) getCreateElement_JDFElement(ElementName.SPINESHREDDING, null, 0);
	}

	/**
	 * (29) append element SpineShredding
	 * 
	 * @return JDFOptionSpan the element
	 * @throws JDFException if the element already exists
	 */
	public JDFOptionSpan appendSpineShredding() throws JDFException
	{
		return (JDFOptionSpan) appendElementN(ElementName.SPINESHREDDING, 1, null);
	}

}// end namespace JDF
