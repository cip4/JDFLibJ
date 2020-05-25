/*
 * The CIP4 Software License, Version 1.0
 *
 *
 * Copyright (c) 2001-2020 The International Cooperation for the Integration of
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
import org.cip4.jdflib.resource.JDFCertification;
import org.cip4.jdflib.resource.intent.JDFIntentResource;
import org.cip4.jdflib.resource.process.JDFMediaLayers;
import org.cip4.jdflib.span.JDFIntegerSpan;
import org.cip4.jdflib.span.JDFNameSpan;
import org.cip4.jdflib.span.JDFNumberSpan;
import org.cip4.jdflib.span.JDFOptionSpan;
import org.cip4.jdflib.span.JDFSpanCoatings;
import org.cip4.jdflib.span.JDFSpanFluteDirection;
import org.cip4.jdflib.span.JDFSpanGrainDirection;
import org.cip4.jdflib.span.JDFSpanISOPaperSubstrate;
import org.cip4.jdflib.span.JDFSpanMediaType;
import org.cip4.jdflib.span.JDFSpanMediaUnit;
import org.cip4.jdflib.span.JDFSpanNamedColor;
import org.cip4.jdflib.span.JDFSpanOpacity;
import org.cip4.jdflib.span.JDFStringSpan;
import org.cip4.jdflib.span.JDFXYPairSpan;

/**
 *****************************************************************************
 * class JDFAutoMediaIntent : public JDFIntentResource
 *****************************************************************************
 *
 */

public abstract class JDFAutoMediaIntent extends JDFIntentResource
{

	private static final long serialVersionUID = 1L;

	private static AtrInfoTable[] atrInfoTable = new AtrInfoTable[3];
	static
	{
		atrInfoTable[0] = new AtrInfoTable(AttributeName.PREPRINTED, 0x33333333, AttributeInfo.EnumAttributeType.boolean_, null, "false");
		atrInfoTable[1] = new AtrInfoTable(AttributeName.MEDIASETCOUNT, 0x33333333, AttributeInfo.EnumAttributeType.integer, null, null);
		atrInfoTable[2] = new AtrInfoTable(AttributeName.USERMEDIATYPE, 0x44333333, AttributeInfo.EnumAttributeType.NMTOKEN, null, null);
	}

	@Override
	protected AttributeInfo getTheAttributeInfo()
	{
		return super.getTheAttributeInfo().updateReplace(atrInfoTable);
	}

	private static ElemInfoTable[] elemInfoTable = new ElemInfoTable[30];
	static
	{
		elemInfoTable[0] = new ElemInfoTable(ElementName.BACKCOATINGS, 0x66666666);
		elemInfoTable[1] = new ElemInfoTable(ElementName.BRIGHTNESS, 0x66666666);
		elemInfoTable[2] = new ElemInfoTable(ElementName.BUYERSUPPLIED, 0x66666666);
		elemInfoTable[3] = new ElemInfoTable(ElementName.CERTIFICATION, 0x33111111);
		elemInfoTable[4] = new ElemInfoTable(ElementName.DIMENSIONS, 0x77777766);
		elemInfoTable[5] = new ElemInfoTable(ElementName.FLUTE, 0x66661111);
		elemInfoTable[6] = new ElemInfoTable(ElementName.FLUTEDIRECTION, 0x66661111);
		elemInfoTable[7] = new ElemInfoTable(ElementName.FRONTCOATINGS, 0x66666666);
		elemInfoTable[8] = new ElemInfoTable(ElementName.GRADE, 0x77666666);
		elemInfoTable[9] = new ElemInfoTable(ElementName.GRAINDIRECTION, 0x66666611);
		elemInfoTable[10] = new ElemInfoTable(ElementName.HOLECOUNT, 0x77777776);
		elemInfoTable[11] = new ElemInfoTable(ElementName.HOLETYPE, 0x66666661);
		elemInfoTable[12] = new ElemInfoTable(ElementName.ISOPAPERSUBSTRATE, 0x66666661);
		elemInfoTable[13] = new ElemInfoTable(ElementName.MEDIACOLOR, 0x66666666);
		elemInfoTable[14] = new ElemInfoTable(ElementName.MEDIACOLORDETAILS, 0x66666611);
		elemInfoTable[15] = new ElemInfoTable(ElementName.MEDIAQUALITY, 0x66661111);
		elemInfoTable[16] = new ElemInfoTable(ElementName.MEDIATYPE, 0x66666661);
		elemInfoTable[17] = new ElemInfoTable(ElementName.MEDIATYPEDETAILS, 0x66666111);
		elemInfoTable[18] = new ElemInfoTable(ElementName.MEDIAUNIT, 0x77777766);
		elemInfoTable[19] = new ElemInfoTable(ElementName.OPACITY, 0x66666666);
		elemInfoTable[20] = new ElemInfoTable(ElementName.OPACITYLEVEL, 0x66666611);
		elemInfoTable[21] = new ElemInfoTable(ElementName.RECYCLED, 0x77777766);
		elemInfoTable[22] = new ElemInfoTable(ElementName.RECYCLEDPERCENTAGE, 0x66666611);
		elemInfoTable[23] = new ElemInfoTable(ElementName.STOCKBRAND, 0x66666666);
		elemInfoTable[24] = new ElemInfoTable(ElementName.STOCKTYPE, 0x66666666);
		elemInfoTable[25] = new ElemInfoTable(ElementName.TEXTURE, 0x66666666);
		elemInfoTable[26] = new ElemInfoTable(ElementName.THICKNESS, 0x66666661);
		elemInfoTable[27] = new ElemInfoTable(ElementName.USWEIGHT, 0x77777766);
		elemInfoTable[28] = new ElemInfoTable(ElementName.WEIGHT, 0x66666666);
		elemInfoTable[29] = new ElemInfoTable(ElementName.MEDIALAYERS, 0x66661111);
	}

	@Override
	protected ElementInfo getTheElementInfo()
	{
		return super.getTheElementInfo().updateReplace(elemInfoTable);
	}

	/**
	 * Constructor for JDFAutoMediaIntent
	 *
	 * @param myOwnerDocument
	 * @param qualifiedName
	 */
	protected JDFAutoMediaIntent(CoreDocumentImpl myOwnerDocument, String qualifiedName)
	{
		super(myOwnerDocument, qualifiedName);
	}

	/**
	 * Constructor for JDFAutoMediaIntent
	 *
	 * @param myOwnerDocument
	 * @param myNamespaceURI
	 * @param qualifiedName
	 */
	protected JDFAutoMediaIntent(CoreDocumentImpl myOwnerDocument, String myNamespaceURI, String qualifiedName)
	{
		super(myOwnerDocument, myNamespaceURI, qualifiedName);
	}

	/**
	 * Constructor for JDFAutoMediaIntent
	 *
	 * @param myOwnerDocument
	 * @param myNamespaceURI
	 * @param qualifiedName
	 * @param myLocalName
	 */
	protected JDFAutoMediaIntent(CoreDocumentImpl myOwnerDocument, String myNamespaceURI, String qualifiedName, String myLocalName)
	{
		super(myOwnerDocument, myNamespaceURI, qualifiedName, myLocalName);
	}

	/* ************************************************************************
	 * Attribute getter / setter
	 * ************************************************************************
	 */

	/* ---------------------------------------------------------------------
	Methods for Attribute PrePrinted
	--------------------------------------------------------------------- */
	/**
	 * (36) set attribute PrePrinted
	 *
	 * @param value the value to set the attribute to
	 */
	public void setPrePrinted(boolean value)
	{
		setAttribute(AttributeName.PREPRINTED, value, null);
	}

	/**
	 * (18) get boolean attribute PrePrinted
	 *
	 * @return boolean the value of the attribute
	 */
	public boolean getPrePrinted()
	{
		return getBoolAttribute(AttributeName.PREPRINTED, null, false);
	}

	/* ---------------------------------------------------------------------
	Methods for Attribute MediaSetCount
	--------------------------------------------------------------------- */
	/**
	 * (36) set attribute MediaSetCount
	 *
	 * @param value the value to set the attribute to
	 */
	public void setMediaSetCount(int value)
	{
		setAttribute(AttributeName.MEDIASETCOUNT, value, null);
	}

	/**
	 * (15) get int attribute MediaSetCount
	 *
	 * @return int the value of the attribute
	 */
	public int getMediaSetCount()
	{
		return getIntAttribute(AttributeName.MEDIASETCOUNT, null, 0);
	}

	/* ---------------------------------------------------------------------
	Methods for Attribute UserMediaType
	--------------------------------------------------------------------- */
	/**
	 * (36) set attribute UserMediaType
	 *
	 * @param value the value to set the attribute to
	 */
	public void setUserMediaType(String value)
	{
		setAttribute(AttributeName.USERMEDIATYPE, value, null);
	}

	/**
	 * (23) get String attribute UserMediaType
	 *
	 * @return the value of the attribute
	 */
	public String getUserMediaType()
	{
		return getAttribute(AttributeName.USERMEDIATYPE, null, JDFCoreConstants.EMPTYSTRING);
	}

	/* ***********************************************************************
	 * Element getter / setter
	 * ***********************************************************************
	 */

	/**
	 * (24) const get element BackCoatings
	 *
	 * @return JDFSpanCoatings the element
	 */
	public JDFSpanCoatings getBackCoatings()
	{
		return (JDFSpanCoatings) getElement(ElementName.BACKCOATINGS, null, 0);
	}

	/**
	 * (25) getCreateBackCoatings
	 *
	 * @return JDFSpanCoatings the element
	 */
	public JDFSpanCoatings getCreateBackCoatings()
	{
		return (JDFSpanCoatings) getCreateElement_JDFElement(ElementName.BACKCOATINGS, null, 0);
	}

	/**
	 * (29) append element BackCoatings
	 *
	 * @return JDFSpanCoatings the element @ if the element already exists
	 */
	public JDFSpanCoatings appendBackCoatings()
	{
		return (JDFSpanCoatings) appendElementN(ElementName.BACKCOATINGS, 1, null);
	}

	/**
	 * (24) const get element Brightness
	 *
	 * @return JDFNumberSpan the element
	 */
	public JDFNumberSpan getBrightness()
	{
		return (JDFNumberSpan) getElement(ElementName.BRIGHTNESS, null, 0);
	}

	/**
	 * (25) getCreateBrightness
	 *
	 * @return JDFNumberSpan the element
	 */
	public JDFNumberSpan getCreateBrightness()
	{
		return (JDFNumberSpan) getCreateElement_JDFElement(ElementName.BRIGHTNESS, null, 0);
	}

	/**
	 * (29) append element Brightness
	 *
	 * @return JDFNumberSpan the element @ if the element already exists
	 */
	public JDFNumberSpan appendBrightness()
	{
		return (JDFNumberSpan) appendElementN(ElementName.BRIGHTNESS, 1, null);
	}

	/**
	 * (24) const get element BuyerSupplied
	 *
	 * @return JDFOptionSpan the element
	 */
	public JDFOptionSpan getBuyerSupplied()
	{
		return (JDFOptionSpan) getElement(ElementName.BUYERSUPPLIED, null, 0);
	}

	/**
	 * (25) getCreateBuyerSupplied
	 *
	 * @return JDFOptionSpan the element
	 */
	public JDFOptionSpan getCreateBuyerSupplied()
	{
		return (JDFOptionSpan) getCreateElement_JDFElement(ElementName.BUYERSUPPLIED, null, 0);
	}

	/**
	 * (29) append element BuyerSupplied
	 *
	 * @return JDFOptionSpan the element @ if the element already exists
	 */
	public JDFOptionSpan appendBuyerSupplied()
	{
		return (JDFOptionSpan) appendElementN(ElementName.BUYERSUPPLIED, 1, null);
	}

	/**
	 * (26) getCreateCertification
	 *
	 * @param iSkip number of elements to skip
	 * @return JDFCertification the element
	 */
	public JDFCertification getCreateCertification(int iSkip)
	{
		return (JDFCertification) getCreateElement_JDFElement(ElementName.CERTIFICATION, null, iSkip);
	}

	/**
	 * (27) const get element Certification
	 *
	 * @param iSkip number of elements to skip
	 * @return JDFCertification the element default is getCertification(0)
	 */
	public JDFCertification getCertification(int iSkip)
	{
		return (JDFCertification) getElement(ElementName.CERTIFICATION, null, iSkip);
	}

	/**
	 * Get all Certification from the current element
	 *
	 * @return Collection<JDFCertification>, null if none are available
	 */
	public Collection<JDFCertification> getAllCertification()
	{
		return getChildArrayByClass(JDFCertification.class, false, 0);
	}

	/**
	 * (30) append element Certification
	 *
	 * @return JDFCertification the element
	 */
	public JDFCertification appendCertification()
	{
		return (JDFCertification) appendElement(ElementName.CERTIFICATION, null);
	}

	/**
	 * (24) const get element Dimensions
	 *
	 * @return JDFXYPairSpan the element
	 */
	public JDFXYPairSpan getDimensions()
	{
		return (JDFXYPairSpan) getElement(ElementName.DIMENSIONS, null, 0);
	}

	/**
	 * (25) getCreateDimensions
	 *
	 * @return JDFXYPairSpan the element
	 */
	public JDFXYPairSpan getCreateDimensions()
	{
		return (JDFXYPairSpan) getCreateElement_JDFElement(ElementName.DIMENSIONS, null, 0);
	}

	/**
	 * (29) append element Dimensions
	 *
	 * @return JDFXYPairSpan the element @ if the element already exists
	 */
	public JDFXYPairSpan appendDimensions()
	{
		return (JDFXYPairSpan) appendElementN(ElementName.DIMENSIONS, 1, null);
	}

	/**
	 * (24) const get element Flute
	 *
	 * @return JDFNameSpan the element
	 */
	public JDFNameSpan getFlute()
	{
		return (JDFNameSpan) getElement(ElementName.FLUTE, null, 0);
	}

	/**
	 * (25) getCreateFlute
	 *
	 * @return JDFNameSpan the element
	 */
	public JDFNameSpan getCreateFlute()
	{
		return (JDFNameSpan) getCreateElement_JDFElement(ElementName.FLUTE, null, 0);
	}

	/**
	 * (29) append element Flute
	 *
	 * @return JDFNameSpan the element @ if the element already exists
	 */
	public JDFNameSpan appendFlute()
	{
		return (JDFNameSpan) appendElementN(ElementName.FLUTE, 1, null);
	}

	/**
	 * (24) const get element FluteDirection
	 *
	 * @return JDFSpanFluteDirection the element
	 */
	public JDFSpanFluteDirection getFluteDirection()
	{
		return (JDFSpanFluteDirection) getElement(ElementName.FLUTEDIRECTION, null, 0);
	}

	/**
	 * (25) getCreateFluteDirection
	 *
	 * @return JDFSpanFluteDirection the element
	 */
	public JDFSpanFluteDirection getCreateFluteDirection()
	{
		return (JDFSpanFluteDirection) getCreateElement_JDFElement(ElementName.FLUTEDIRECTION, null, 0);
	}

	/**
	 * (29) append element FluteDirection
	 *
	 * @return JDFSpanFluteDirection the element @ if the element already exists
	 */
	public JDFSpanFluteDirection appendFluteDirection()
	{
		return (JDFSpanFluteDirection) appendElementN(ElementName.FLUTEDIRECTION, 1, null);
	}

	/**
	 * (24) const get element FrontCoatings
	 *
	 * @return JDFSpanCoatings the element
	 */
	public JDFSpanCoatings getFrontCoatings()
	{
		return (JDFSpanCoatings) getElement(ElementName.FRONTCOATINGS, null, 0);
	}

	/**
	 * (25) getCreateFrontCoatings
	 *
	 * @return JDFSpanCoatings the element
	 */
	public JDFSpanCoatings getCreateFrontCoatings()
	{
		return (JDFSpanCoatings) getCreateElement_JDFElement(ElementName.FRONTCOATINGS, null, 0);
	}

	/**
	 * (29) append element FrontCoatings
	 *
	 * @return JDFSpanCoatings the element @ if the element already exists
	 */
	public JDFSpanCoatings appendFrontCoatings()
	{
		return (JDFSpanCoatings) appendElementN(ElementName.FRONTCOATINGS, 1, null);
	}

	/**
	 * (24) const get element Grade
	 *
	 * @return JDFIntegerSpan the element
	 */
	public JDFIntegerSpan getGrade()
	{
		return (JDFIntegerSpan) getElement(ElementName.GRADE, null, 0);
	}

	/**
	 * (25) getCreateGrade
	 *
	 * @return JDFIntegerSpan the element
	 */
	public JDFIntegerSpan getCreateGrade()
	{
		return (JDFIntegerSpan) getCreateElement_JDFElement(ElementName.GRADE, null, 0);
	}

	/**
	 * (29) append element Grade
	 *
	 * @return JDFIntegerSpan the element @ if the element already exists
	 */
	public JDFIntegerSpan appendGrade()
	{
		return (JDFIntegerSpan) appendElementN(ElementName.GRADE, 1, null);
	}

	/**
	 * (24) const get element GrainDirection
	 *
	 * @return JDFSpanGrainDirection the element
	 */
	public JDFSpanGrainDirection getGrainDirection()
	{
		return (JDFSpanGrainDirection) getElement(ElementName.GRAINDIRECTION, null, 0);
	}

	/**
	 * (25) getCreateGrainDirection
	 *
	 * @return JDFSpanGrainDirection the element
	 */
	public JDFSpanGrainDirection getCreateGrainDirection()
	{
		return (JDFSpanGrainDirection) getCreateElement_JDFElement(ElementName.GRAINDIRECTION, null, 0);
	}

	/**
	 * (29) append element GrainDirection
	 *
	 * @return JDFSpanGrainDirection the element @ if the element already exists
	 */
	public JDFSpanGrainDirection appendGrainDirection()
	{
		return (JDFSpanGrainDirection) appendElementN(ElementName.GRAINDIRECTION, 1, null);
	}

	/**
	 * (24) const get element HoleCount
	 *
	 * @return JDFIntegerSpan the element
	 */
	public JDFIntegerSpan getHoleCount()
	{
		return (JDFIntegerSpan) getElement(ElementName.HOLECOUNT, null, 0);
	}

	/**
	 * (25) getCreateHoleCount
	 *
	 * @return JDFIntegerSpan the element
	 */
	public JDFIntegerSpan getCreateHoleCount()
	{
		return (JDFIntegerSpan) getCreateElement_JDFElement(ElementName.HOLECOUNT, null, 0);
	}

	/**
	 * (29) append element HoleCount
	 *
	 * @return JDFIntegerSpan the element @ if the element already exists
	 */
	public JDFIntegerSpan appendHoleCount()
	{
		return (JDFIntegerSpan) appendElementN(ElementName.HOLECOUNT, 1, null);
	}

	/**
	 * (24) const get element HoleType
	 *
	 * @return JDFStringSpan the element
	 */
	public JDFStringSpan getHoleType()
	{
		return (JDFStringSpan) getElement(ElementName.HOLETYPE, null, 0);
	}

	/**
	 * (25) getCreateHoleType
	 *
	 * @return JDFStringSpan the element
	 */
	public JDFStringSpan getCreateHoleType()
	{
		return (JDFStringSpan) getCreateElement_JDFElement(ElementName.HOLETYPE, null, 0);
	}

	/**
	 * (29) append element HoleType
	 *
	 * @return JDFStringSpan the element @ if the element already exists
	 */
	public JDFStringSpan appendHoleType()
	{
		return (JDFStringSpan) appendElementN(ElementName.HOLETYPE, 1, null);
	}

	/**
	 * (24) const get element ISOPaperSubstrate
	 *
	 * @return JDFSpanISOPaperSubstrate the element
	 */
	public JDFSpanISOPaperSubstrate getISOPaperSubstrate()
	{
		return (JDFSpanISOPaperSubstrate) getElement(ElementName.ISOPAPERSUBSTRATE, null, 0);
	}

	/**
	 * (25) getCreateISOPaperSubstrate
	 *
	 * @return JDFSpanISOPaperSubstrate the element
	 */
	public JDFSpanISOPaperSubstrate getCreateISOPaperSubstrate()
	{
		return (JDFSpanISOPaperSubstrate) getCreateElement_JDFElement(ElementName.ISOPAPERSUBSTRATE, null, 0);
	}

	/**
	 * (29) append element ISOPaperSubstrate
	 *
	 * @return JDFSpanISOPaperSubstrate the element @ if the element already exists
	 */
	public JDFSpanISOPaperSubstrate appendISOPaperSubstrate()
	{
		return (JDFSpanISOPaperSubstrate) appendElementN(ElementName.ISOPAPERSUBSTRATE, 1, null);
	}

	/**
	 * (24) const get element MediaColor
	 *
	 * @return JDFSpanNamedColor the element
	 */
	public JDFSpanNamedColor getMediaColor()
	{
		return (JDFSpanNamedColor) getElement(ElementName.MEDIACOLOR, null, 0);
	}

	/**
	 * (25) getCreateMediaColor
	 *
	 * @return JDFSpanNamedColor the element
	 */
	public JDFSpanNamedColor getCreateMediaColor()
	{
		return (JDFSpanNamedColor) getCreateElement_JDFElement(ElementName.MEDIACOLOR, null, 0);
	}

	/**
	 * (29) append element MediaColor
	 *
	 * @return JDFSpanNamedColor the element @ if the element already exists
	 */
	public JDFSpanNamedColor appendMediaColor()
	{
		return (JDFSpanNamedColor) appendElementN(ElementName.MEDIACOLOR, 1, null);
	}

	/**
	 * (24) const get element MediaColorDetails
	 *
	 * @return JDFStringSpan the element
	 */
	public JDFStringSpan getMediaColorDetails()
	{
		return (JDFStringSpan) getElement(ElementName.MEDIACOLORDETAILS, null, 0);
	}

	/**
	 * (25) getCreateMediaColorDetails
	 *
	 * @return JDFStringSpan the element
	 */
	public JDFStringSpan getCreateMediaColorDetails()
	{
		return (JDFStringSpan) getCreateElement_JDFElement(ElementName.MEDIACOLORDETAILS, null, 0);
	}

	/**
	 * (29) append element MediaColorDetails
	 *
	 * @return JDFStringSpan the element @ if the element already exists
	 */
	public JDFStringSpan appendMediaColorDetails()
	{
		return (JDFStringSpan) appendElementN(ElementName.MEDIACOLORDETAILS, 1, null);
	}

	/**
	 * (24) const get element MediaQuality
	 *
	 * @return JDFStringSpan the element
	 */
	public JDFStringSpan getMediaQuality()
	{
		return (JDFStringSpan) getElement(ElementName.MEDIAQUALITY, null, 0);
	}

	/**
	 * (25) getCreateMediaQuality
	 *
	 * @return JDFStringSpan the element
	 */
	public JDFStringSpan getCreateMediaQuality()
	{
		return (JDFStringSpan) getCreateElement_JDFElement(ElementName.MEDIAQUALITY, null, 0);
	}

	/**
	 * (29) append element MediaQuality
	 *
	 * @return JDFStringSpan the element @ if the element already exists
	 */
	public JDFStringSpan appendMediaQuality()
	{
		return (JDFStringSpan) appendElementN(ElementName.MEDIAQUALITY, 1, null);
	}

	/**
	 * (24) const get element MediaType
	 *
	 * @return JDFSpanMediaType the element
	 */
	public JDFSpanMediaType getMediaType()
	{
		return (JDFSpanMediaType) getElement(ElementName.MEDIATYPE, null, 0);
	}

	/**
	 * (25) getCreateMediaType
	 *
	 * @return JDFSpanMediaType the element
	 */
	public JDFSpanMediaType getCreateMediaType()
	{
		return (JDFSpanMediaType) getCreateElement_JDFElement(ElementName.MEDIATYPE, null, 0);
	}

	/**
	 * (29) append element MediaType
	 *
	 * @return JDFSpanMediaType the element @ if the element already exists
	 */
	public JDFSpanMediaType appendMediaType()
	{
		return (JDFSpanMediaType) appendElementN(ElementName.MEDIATYPE, 1, null);
	}

	/**
	 * (24) const get element MediaTypeDetails
	 *
	 * @return JDFNameSpan the element
	 */
	public JDFNameSpan getMediaTypeDetails()
	{
		return (JDFNameSpan) getElement(ElementName.MEDIATYPEDETAILS, null, 0);
	}

	/**
	 * (25) getCreateMediaTypeDetails
	 *
	 * @return JDFNameSpan the element
	 */
	public JDFNameSpan getCreateMediaTypeDetails()
	{
		return (JDFNameSpan) getCreateElement_JDFElement(ElementName.MEDIATYPEDETAILS, null, 0);
	}

	/**
	 * (29) append element MediaTypeDetails
	 *
	 * @return JDFNameSpan the element @ if the element already exists
	 */
	public JDFNameSpan appendMediaTypeDetails()
	{
		return (JDFNameSpan) appendElementN(ElementName.MEDIATYPEDETAILS, 1, null);
	}

	/**
	 * (24) const get element MediaUnit
	 *
	 * @return JDFSpanMediaUnit the element
	 */
	public JDFSpanMediaUnit getMediaUnit()
	{
		return (JDFSpanMediaUnit) getElement(ElementName.MEDIAUNIT, null, 0);
	}

	/**
	 * (25) getCreateMediaUnit
	 *
	 * @return JDFSpanMediaUnit the element
	 */
	public JDFSpanMediaUnit getCreateMediaUnit()
	{
		return (JDFSpanMediaUnit) getCreateElement_JDFElement(ElementName.MEDIAUNIT, null, 0);
	}

	/**
	 * (29) append element MediaUnit
	 *
	 * @return JDFSpanMediaUnit the element @ if the element already exists
	 */
	public JDFSpanMediaUnit appendMediaUnit()
	{
		return (JDFSpanMediaUnit) appendElementN(ElementName.MEDIAUNIT, 1, null);
	}

	/**
	 * (24) const get element Opacity
	 *
	 * @return JDFSpanOpacity the element
	 */
	public JDFSpanOpacity getOpacity()
	{
		return (JDFSpanOpacity) getElement(ElementName.OPACITY, null, 0);
	}

	/**
	 * (25) getCreateOpacity
	 *
	 * @return JDFSpanOpacity the element
	 */
	public JDFSpanOpacity getCreateOpacity()
	{
		return (JDFSpanOpacity) getCreateElement_JDFElement(ElementName.OPACITY, null, 0);
	}

	/**
	 * (29) append element Opacity
	 *
	 * @return JDFSpanOpacity the element @ if the element already exists
	 */
	public JDFSpanOpacity appendOpacity()
	{
		return (JDFSpanOpacity) appendElementN(ElementName.OPACITY, 1, null);
	}

	/**
	 * (24) const get element OpacityLevel
	 *
	 * @return JDFNumberSpan the element
	 */
	public JDFNumberSpan getOpacityLevel()
	{
		return (JDFNumberSpan) getElement(ElementName.OPACITYLEVEL, null, 0);
	}

	/**
	 * (25) getCreateOpacityLevel
	 *
	 * @return JDFNumberSpan the element
	 */
	public JDFNumberSpan getCreateOpacityLevel()
	{
		return (JDFNumberSpan) getCreateElement_JDFElement(ElementName.OPACITYLEVEL, null, 0);
	}

	/**
	 * (29) append element OpacityLevel
	 *
	 * @return JDFNumberSpan the element @ if the element already exists
	 */
	public JDFNumberSpan appendOpacityLevel()
	{
		return (JDFNumberSpan) appendElementN(ElementName.OPACITYLEVEL, 1, null);
	}

	/**
	 * (24) const get element Recycled
	 *
	 * @return JDFOptionSpan the element
	 */
	public JDFOptionSpan getRecycled()
	{
		return (JDFOptionSpan) getElement(ElementName.RECYCLED, null, 0);
	}

	/**
	 * (25) getCreateRecycled
	 *
	 * @return JDFOptionSpan the element
	 */
	public JDFOptionSpan getCreateRecycled()
	{
		return (JDFOptionSpan) getCreateElement_JDFElement(ElementName.RECYCLED, null, 0);
	}

	/**
	 * (29) append element Recycled
	 *
	 * @return JDFOptionSpan the element @ if the element already exists
	 */
	public JDFOptionSpan appendRecycled()
	{
		return (JDFOptionSpan) appendElementN(ElementName.RECYCLED, 1, null);
	}

	/**
	 * (24) const get element RecycledPercentage
	 *
	 * @return JDFNumberSpan the element
	 */
	public JDFNumberSpan getRecycledPercentage()
	{
		return (JDFNumberSpan) getElement(ElementName.RECYCLEDPERCENTAGE, null, 0);
	}

	/**
	 * (25) getCreateRecycledPercentage
	 *
	 * @return JDFNumberSpan the element
	 */
	public JDFNumberSpan getCreateRecycledPercentage()
	{
		return (JDFNumberSpan) getCreateElement_JDFElement(ElementName.RECYCLEDPERCENTAGE, null, 0);
	}

	/**
	 * (29) append element RecycledPercentage
	 *
	 * @return JDFNumberSpan the element @ if the element already exists
	 */
	public JDFNumberSpan appendRecycledPercentage()
	{
		return (JDFNumberSpan) appendElementN(ElementName.RECYCLEDPERCENTAGE, 1, null);
	}

	/**
	 * (24) const get element StockBrand
	 *
	 * @return JDFStringSpan the element
	 */
	public JDFStringSpan getStockBrand()
	{
		return (JDFStringSpan) getElement(ElementName.STOCKBRAND, null, 0);
	}

	/**
	 * (25) getCreateStockBrand
	 *
	 * @return JDFStringSpan the element
	 */
	public JDFStringSpan getCreateStockBrand()
	{
		return (JDFStringSpan) getCreateElement_JDFElement(ElementName.STOCKBRAND, null, 0);
	}

	/**
	 * (29) append element StockBrand
	 *
	 * @return JDFStringSpan the element @ if the element already exists
	 */
	public JDFStringSpan appendStockBrand()
	{
		return (JDFStringSpan) appendElementN(ElementName.STOCKBRAND, 1, null);
	}

	/**
	 * (24) const get element StockType
	 *
	 * @return JDFNameSpan the element
	 */
	public JDFNameSpan getStockType()
	{
		return (JDFNameSpan) getElement(ElementName.STOCKTYPE, null, 0);
	}

	/**
	 * (25) getCreateStockType
	 *
	 * @return JDFNameSpan the element
	 */
	public JDFNameSpan getCreateStockType()
	{
		return (JDFNameSpan) getCreateElement_JDFElement(ElementName.STOCKTYPE, null, 0);
	}

	/**
	 * (29) append element StockType
	 *
	 * @return JDFNameSpan the element @ if the element already exists
	 */
	public JDFNameSpan appendStockType()
	{
		return (JDFNameSpan) appendElementN(ElementName.STOCKTYPE, 1, null);
	}

	/**
	 * (24) const get element Texture
	 *
	 * @return JDFNameSpan the element
	 */
	public JDFNameSpan getTexture()
	{
		return (JDFNameSpan) getElement(ElementName.TEXTURE, null, 0);
	}

	/**
	 * (25) getCreateTexture
	 *
	 * @return JDFNameSpan the element
	 */
	public JDFNameSpan getCreateTexture()
	{
		return (JDFNameSpan) getCreateElement_JDFElement(ElementName.TEXTURE, null, 0);
	}

	/**
	 * (29) append element Texture
	 *
	 * @return JDFNameSpan the element @ if the element already exists
	 */
	public JDFNameSpan appendTexture()
	{
		return (JDFNameSpan) appendElementN(ElementName.TEXTURE, 1, null);
	}

	/**
	 * (24) const get element Thickness
	 *
	 * @return JDFNumberSpan the element
	 */
	public JDFNumberSpan getThickness()
	{
		return (JDFNumberSpan) getElement(ElementName.THICKNESS, null, 0);
	}

	/**
	 * (25) getCreateThickness
	 *
	 * @return JDFNumberSpan the element
	 */
	public JDFNumberSpan getCreateThickness()
	{
		return (JDFNumberSpan) getCreateElement_JDFElement(ElementName.THICKNESS, null, 0);
	}

	/**
	 * (29) append element Thickness
	 *
	 * @return JDFNumberSpan the element @ if the element already exists
	 */
	public JDFNumberSpan appendThickness()
	{
		return (JDFNumberSpan) appendElementN(ElementName.THICKNESS, 1, null);
	}

	/**
	 * (24) const get element USWeight
	 *
	 * @return JDFNumberSpan the element
	 */
	public JDFNumberSpan getUSWeight()
	{
		return (JDFNumberSpan) getElement(ElementName.USWEIGHT, null, 0);
	}

	/**
	 * (25) getCreateUSWeight
	 *
	 * @return JDFNumberSpan the element
	 */
	public JDFNumberSpan getCreateUSWeight()
	{
		return (JDFNumberSpan) getCreateElement_JDFElement(ElementName.USWEIGHT, null, 0);
	}

	/**
	 * (29) append element USWeight
	 *
	 * @return JDFNumberSpan the element @ if the element already exists
	 */
	public JDFNumberSpan appendUSWeight()
	{
		return (JDFNumberSpan) appendElementN(ElementName.USWEIGHT, 1, null);
	}

	/**
	 * (24) const get element Weight
	 *
	 * @return JDFNumberSpan the element
	 */
	public JDFNumberSpan getWeight()
	{
		return (JDFNumberSpan) getElement(ElementName.WEIGHT, null, 0);
	}

	/**
	 * (25) getCreateWeight
	 *
	 * @return JDFNumberSpan the element
	 */
	public JDFNumberSpan getCreateWeight()
	{
		return (JDFNumberSpan) getCreateElement_JDFElement(ElementName.WEIGHT, null, 0);
	}

	/**
	 * (29) append element Weight
	 *
	 * @return JDFNumberSpan the element @ if the element already exists
	 */
	public JDFNumberSpan appendWeight()
	{
		return (JDFNumberSpan) appendElementN(ElementName.WEIGHT, 1, null);
	}

	/**
	 * (24) const get element MediaLayers
	 *
	 * @return JDFMediaLayers the element
	 */
	public JDFMediaLayers getMediaLayers()
	{
		return (JDFMediaLayers) getElement(ElementName.MEDIALAYERS, null, 0);
	}

	/**
	 * (25) getCreateMediaLayers
	 *
	 * @return JDFMediaLayers the element
	 */
	public JDFMediaLayers getCreateMediaLayers()
	{
		return (JDFMediaLayers) getCreateElement_JDFElement(ElementName.MEDIALAYERS, null, 0);
	}

	/**
	 * (29) append element MediaLayers
	 *
	 * @return JDFMediaLayers the element @ if the element already exists
	 */
	public JDFMediaLayers appendMediaLayers()
	{
		return (JDFMediaLayers) appendElementN(ElementName.MEDIALAYERS, 1, null);
	}

}
