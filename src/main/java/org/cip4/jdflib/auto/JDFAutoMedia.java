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
import java.util.List;

import org.apache.xerces.dom.CoreDocumentImpl;
import org.cip4.jdflib.core.AtrInfoTable;
import org.cip4.jdflib.core.AttributeInfo;
import org.cip4.jdflib.core.AttributeName;
import org.cip4.jdflib.core.ElemInfoTable;
import org.cip4.jdflib.core.ElementInfo;
import org.cip4.jdflib.core.ElementName;
import org.cip4.jdflib.core.JDFCoreConstants;
import org.cip4.jdflib.datatypes.JDFLabColor;
import org.cip4.jdflib.datatypes.JDFTransferFunction;
import org.cip4.jdflib.datatypes.JDFXYPair;
import org.cip4.jdflib.resource.JDFCertification;
import org.cip4.jdflib.resource.JDFColorMeasurementConditions;
import org.cip4.jdflib.resource.JDFResource;
import org.cip4.jdflib.resource.process.JDFColor;
import org.cip4.jdflib.resource.process.JDFContact;
import org.cip4.jdflib.resource.process.JDFIdentificationField;
import org.cip4.jdflib.resource.process.JDFMediaLayers;
import org.cip4.jdflib.resource.process.JDFTabDimensions;
import org.cip4.jdflib.resource.process.postpress.JDFHoleList;
import org.cip4.jdflib.util.JavaEnumUtil;

/**
 ***************************************************************************** class JDFAutoMedia : public JDFResource
 */

public abstract class JDFAutoMedia extends JDFResource
{

	private static final long serialVersionUID = 1L;

	private static AtrInfoTable[] atrInfoTable = new AtrInfoTable[56];
	static
	{
		atrInfoTable[0] = new AtrInfoTable(AttributeName.HOLETYPE, 0x3333333331l, AttributeInfo.EnumAttributeType.enumerations,
				JavaEnumUtil.getEnum(EnumHoleType.class, 0), "None");
		atrInfoTable[1] = new AtrInfoTable(AttributeName.MEDIAUNIT, 0x3333333333l, AttributeInfo.EnumAttributeType.enumeration,
				JavaEnumUtil.getEnum(EnumMediaUnit.class, 0), "Sheet");
		atrInfoTable[2] = new AtrInfoTable(AttributeName.PREPRINTED, 0x3333333333l, AttributeInfo.EnumAttributeType.boolean_, null, "false");
		atrInfoTable[3] = new AtrInfoTable(AttributeName.BACKBRIGHTNESS, 0x3333311111l, AttributeInfo.EnumAttributeType.double_, null, null);
		atrInfoTable[4] = new AtrInfoTable(AttributeName.BACKCOATINGDETAIL, 0x3333331111l, AttributeInfo.EnumAttributeType.NMTOKEN, null, null);
		atrInfoTable[5] = new AtrInfoTable(AttributeName.BACKCOATINGS, 0x3333333333l, AttributeInfo.EnumAttributeType.enumeration,
				JavaEnumUtil.getEnum(EnumBackCoatings.class, 0), null);
		atrInfoTable[6] = new AtrInfoTable(AttributeName.BACKGLOSSVALUE, 0x3333333311l, AttributeInfo.EnumAttributeType.double_, null, null);
		atrInfoTable[7] = new AtrInfoTable(AttributeName.BACKISOPAPERSUBSTRATE, 0x3333111111l, AttributeInfo.EnumAttributeType.enumeration,
				JavaEnumUtil.getEnum(EnumBackISOPaperSubstrate.class, 0), null);
		atrInfoTable[8] = new AtrInfoTable(AttributeName.BACKOPTICALBRIGHTENING, 0x3111111111l, AttributeInfo.EnumAttributeType.double_, null, null);
		atrInfoTable[9] = new AtrInfoTable(AttributeName.BACKSPECTRUM, 0x3331111111l, AttributeInfo.EnumAttributeType.TransferFunction, null, null);
		atrInfoTable[10] = new AtrInfoTable(AttributeName.BRIGHTNESS, 0x3333333333l, AttributeInfo.EnumAttributeType.double_, null, null);
		atrInfoTable[11] = new AtrInfoTable(AttributeName.CIETINT, 0x3333333311l, AttributeInfo.EnumAttributeType.double_, null, null);
		atrInfoTable[12] = new AtrInfoTable(AttributeName.CIEWHITENESS, 0x3333333311l, AttributeInfo.EnumAttributeType.double_, null, null);
		atrInfoTable[13] = new AtrInfoTable(AttributeName.COLORNAME, 0x4444444431l, AttributeInfo.EnumAttributeType.string, null, null);
		atrInfoTable[14] = new AtrInfoTable(AttributeName.COREWEIGHT, 0x3333333111l, AttributeInfo.EnumAttributeType.double_, null, null);
		atrInfoTable[15] = new AtrInfoTable(AttributeName.DIMENSION, 0x3333333333l, AttributeInfo.EnumAttributeType.XYPair, null, null);
		atrInfoTable[16] = new AtrInfoTable(AttributeName.FLUTE, 0x3333333111l, AttributeInfo.EnumAttributeType.NMTOKEN, null, null);
		atrInfoTable[17] = new AtrInfoTable(AttributeName.FLUTEDIRECTION, 0x3333333111l, AttributeInfo.EnumAttributeType.enumeration,
				JavaEnumUtil.getEnum(EnumFluteDirection.class, 0), null);
		atrInfoTable[18] = new AtrInfoTable(AttributeName.FRONTCOATINGDETAIL, 0x3333331111l, AttributeInfo.EnumAttributeType.NMTOKEN, null, null);
		atrInfoTable[19] = new AtrInfoTable(AttributeName.FRONTCOATINGS, 0x3333333333l, AttributeInfo.EnumAttributeType.enumeration,
				JavaEnumUtil.getEnum(EnumFrontCoatings.class, 0), null);
		atrInfoTable[20] = new AtrInfoTable(AttributeName.FRONTGLOSSVALUE, 0x3333333311l, AttributeInfo.EnumAttributeType.double_, null, null);
		atrInfoTable[21] = new AtrInfoTable(AttributeName.GRADE, 0x4444333333l, AttributeInfo.EnumAttributeType.integer, null, null);
		atrInfoTable[22] = new AtrInfoTable(AttributeName.GRAINDIRECTION, 0x3333333331l, AttributeInfo.EnumAttributeType.enumeration,
				JavaEnumUtil.getEnum(EnumGrainDirection.class, 0), null);
		atrInfoTable[23] = new AtrInfoTable(AttributeName.HOLECOUNT, 0x4444444443l, AttributeInfo.EnumAttributeType.integer, null, null);
		atrInfoTable[24] = new AtrInfoTable(AttributeName.IMAGABLESIDE, 0x3333333333l, AttributeInfo.EnumAttributeType.enumeration,
				JavaEnumUtil.getEnum(EnumImagableSide.class, 0), null);
		atrInfoTable[25] = new AtrInfoTable(AttributeName.INNERCOREDIAMETER, 0x3333331111l, AttributeInfo.EnumAttributeType.double_, null, null);
		atrInfoTable[26] = new AtrInfoTable(AttributeName.INSIDELOSS, 0x3333333111l, AttributeInfo.EnumAttributeType.double_, null, null);
		atrInfoTable[27] = new AtrInfoTable(AttributeName.ISOPAPERSUBSTRATE, 0x3333311111l, AttributeInfo.EnumAttributeType.enumeration,
				JavaEnumUtil.getEnum(EnumISOPaperSubstrate.class, 0), null);
		atrInfoTable[28] = new AtrInfoTable(AttributeName.LABCOLORVALUE, 0x3333333311l, AttributeInfo.EnumAttributeType.LabColor, null, null);
		atrInfoTable[29] = new AtrInfoTable(AttributeName.MEDIACOLORNAME, 0x3333333333l, AttributeInfo.EnumAttributeType.string, null, null);
		atrInfoTable[30] = new AtrInfoTable(AttributeName.MEDIACOLORNAMEDETAILS, 0x3333333311l, AttributeInfo.EnumAttributeType.string, null, null);
		atrInfoTable[31] = new AtrInfoTable(AttributeName.MEDIAQUALITY, 0x3333331111l, AttributeInfo.EnumAttributeType.string, null, null);
		atrInfoTable[32] = new AtrInfoTable(AttributeName.MEDIASETCOUNT, 0x3333333333l, AttributeInfo.EnumAttributeType.integer, null, null);
		atrInfoTable[33] = new AtrInfoTable(AttributeName.MEDIATYPE, 0x3333333333l, AttributeInfo.EnumAttributeType.enumeration,
				JavaEnumUtil.getEnum(EnumMediaType.class, 0), null);
		atrInfoTable[34] = new AtrInfoTable(AttributeName.MEDIATYPEDETAILS, 0x3333333333l, AttributeInfo.EnumAttributeType.NMTOKEN, null, null);
		atrInfoTable[35] = new AtrInfoTable(AttributeName.OPACITY, 0x3333333333l, AttributeInfo.EnumAttributeType.enumeration,
				JavaEnumUtil.getEnum(EnumOpacity.class, 0), null);
		atrInfoTable[36] = new AtrInfoTable(AttributeName.OPACITYLEVEL, 0x3333333311l, AttributeInfo.EnumAttributeType.double_, null, null);
		atrInfoTable[37] = new AtrInfoTable(AttributeName.OPTICALBRIGHTENING, 0x3111111111l, AttributeInfo.EnumAttributeType.double_, null, null);
		atrInfoTable[38] = new AtrInfoTable(AttributeName.OUTERCOREDIAMETER, 0x3333333111l, AttributeInfo.EnumAttributeType.double_, null, null);
		atrInfoTable[39] = new AtrInfoTable(AttributeName.OUTSIDEGAIN, 0x3333333111l, AttributeInfo.EnumAttributeType.double_, null, null);
		atrInfoTable[40] = new AtrInfoTable(AttributeName.PLATETECHNOLOGY, 0x3333333111l, AttributeInfo.EnumAttributeType.enumeration,
				JavaEnumUtil.getEnum(EnumPlateTechnology.class, 0), null);
		atrInfoTable[41] = new AtrInfoTable(AttributeName.POLARITY, 0x3333333333l, AttributeInfo.EnumAttributeType.enumeration,
				JavaEnumUtil.getEnum(EnumPolarity.class, 0), null);
		atrInfoTable[42] = new AtrInfoTable(AttributeName.PRINTINGTECHNOLOGY, 0x3333331111l, AttributeInfo.EnumAttributeType.NMTOKEN, null, null);
		atrInfoTable[43] = new AtrInfoTable(AttributeName.RECYCLED, 0x4444444433l, AttributeInfo.EnumAttributeType.boolean_, null, null);
		atrInfoTable[44] = new AtrInfoTable(AttributeName.RECYCLEDPERCENTAGE, 0x3333333311l, AttributeInfo.EnumAttributeType.double_, null, null);
		atrInfoTable[45] = new AtrInfoTable(AttributeName.RELIEFTHICKNESS, 0x3333331111l, AttributeInfo.EnumAttributeType.double_, null, null);
		atrInfoTable[46] = new AtrInfoTable(AttributeName.ROLLDIAMETER, 0x3333333333l, AttributeInfo.EnumAttributeType.double_, null, null);
		atrInfoTable[47] = new AtrInfoTable(AttributeName.SHRINKINDEX, 0x3333333331l, AttributeInfo.EnumAttributeType.XYPair, null, null);
		atrInfoTable[48] = new AtrInfoTable(AttributeName.SLEEVEINTERLOCK, 0x4333331111l, AttributeInfo.EnumAttributeType.NMTOKEN, null, null);
		atrInfoTable[49] = new AtrInfoTable(AttributeName.SPECTRUM, 0x3331111111l, AttributeInfo.EnumAttributeType.TransferFunction, null, null);
		atrInfoTable[50] = new AtrInfoTable(AttributeName.STOCKTYPE, 0x3333333331l, AttributeInfo.EnumAttributeType.NMTOKEN, null, null);
		atrInfoTable[51] = new AtrInfoTable(AttributeName.TEXTURE, 0x3333333331l, AttributeInfo.EnumAttributeType.NMTOKEN, null, null);
		atrInfoTable[52] = new AtrInfoTable(AttributeName.THICKNESS, 0x3333333333l, AttributeInfo.EnumAttributeType.double_, null, null);
		atrInfoTable[53] = new AtrInfoTable(AttributeName.USERMEDIATYPE, 0x4444444443l, AttributeInfo.EnumAttributeType.NMTOKEN, null, null);
		atrInfoTable[54] = new AtrInfoTable(AttributeName.WEIGHT, 0x3333333333l, AttributeInfo.EnumAttributeType.double_, null, null);
		atrInfoTable[55] = new AtrInfoTable(AttributeName.WRAPPERWEIGHT, 0x3333333111l, AttributeInfo.EnumAttributeType.double_, null, null);
	}

	@Override
	protected AttributeInfo getTheAttributeInfo()
	{
		return super.getTheAttributeInfo().updateReplace(atrInfoTable);
	}

	private static ElemInfoTable[] elemInfoTable = new ElemInfoTable[8];
	static
	{
		elemInfoTable[0] = new ElemInfoTable(ElementName.CERTIFICATION, 0x3333111111l);
		elemInfoTable[1] = new ElemInfoTable(ElementName.COLOR, 0x7777777776l);
		elemInfoTable[2] = new ElemInfoTable(ElementName.COLORMEASUREMENTCONDITIONS, 0x6666666611l);
		elemInfoTable[3] = new ElemInfoTable(ElementName.MEDIALAYERS, 0x6666666111l);
		elemInfoTable[4] = new ElemInfoTable(ElementName.HOLELIST, 0x6666666611l);
		elemInfoTable[5] = new ElemInfoTable(ElementName.TABDIMENSIONS, 0x3333333333l);
		elemInfoTable[6] = new ElemInfoTable(ElementName.CONTACT, 0x3333333333l);
		elemInfoTable[7] = new ElemInfoTable(ElementName.IDENTIFICATIONFIELD, 0x3333333333l);
	}

	@Override
	protected ElementInfo getTheElementInfo()
	{
		return super.getTheElementInfo().updateReplace(elemInfoTable);
	}

	/**
	 * Constructor for JDFAutoMedia
	 *
	 * @param myOwnerDocument
	 * @param qualifiedName
	 */
	protected JDFAutoMedia(final CoreDocumentImpl myOwnerDocument, final String qualifiedName)
	{
		super(myOwnerDocument, qualifiedName);
	}

	/**
	 * Constructor for JDFAutoMedia
	 *
	 * @param myOwnerDocument
	 * @param myNamespaceURI
	 * @param qualifiedName
	 */
	protected JDFAutoMedia(final CoreDocumentImpl myOwnerDocument, final String myNamespaceURI, final String qualifiedName)
	{
		super(myOwnerDocument, myNamespaceURI, qualifiedName);
	}

	/**
	 * Constructor for JDFAutoMedia
	 *
	 * @param myOwnerDocument
	 * @param myNamespaceURI
	 * @param qualifiedName
	 * @param myLocalName
	 */
	protected JDFAutoMedia(final CoreDocumentImpl myOwnerDocument, final String myNamespaceURI, final String qualifiedName, final String myLocalName)
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
		setResourceClass(JDFResource.EnumResourceClass.Consumable);
		return bRet;
	}

	/**
	 * @return the resource Class
	 */
	@Override
	public EnumResourceClass getValidClass()
	{
		return JDFResource.EnumResourceClass.Consumable;
	}

	/**
	 * Enumeration strings for numHoleType
	 */

	public enum EnumHoleType
	{
		None, S1_generic, S_generic, R2_generic, R2m_DIN, R2m_ISO, R2m_MIB, R2i_US_a, R2i_US_b, R3_generic, R3i_US, R4_generic, R4m_DIN_A4, R4m_DIN_A5, R4m_swedish, R4i_US, R5_generic, R5i_US_a, R5i_US_b, R5i_US_c, R6_generic, R6m_4h2s, R6m_DIN_A5, R7_generic, R7i_US_a, R7i_US_b, R7i_US_c, R11m_7h4s, P16_9i_rect_0t, P12m_rect_0t, W2_1i_round_0t, W2_1i_square_0t, W3_1i_square_0t, C9_5m_round_0t, Explicit;

		public static EnumHoleType getEnum(final String val)
		{
			return JavaEnumUtil.getEnumIgnoreCase(EnumHoleType.class, val, EnumHoleType.None);
		}
	}

	/**
	 * Enumeration strings for numMediaUnit
	 */

	public enum EnumMediaUnit
	{
		Continuous, Roll, Sheet;

		public static EnumMediaUnit getEnum(final String val)
		{
			return JavaEnumUtil.getEnumIgnoreCase(EnumMediaUnit.class, val, EnumMediaUnit.Sheet);
		}
	}

	/**
	 * Enumeration strings for numBackCoatings
	 */

	public enum EnumBackCoatings
	{
		None, Coated, Glossy, HighGloss, InkJet, Matte, Polymer, Silver, Satin, Semigloss;

		public static EnumBackCoatings getEnum(final String val)
		{
			return JavaEnumUtil.getEnumIgnoreCase(EnumBackCoatings.class, val, null);
		}
	}

	/**
	 * Enumeration strings for numBackISOPaperSubstrate
	 */

	public enum EnumBackISOPaperSubstrate
	{
		LWCPlus, LWCStandard, NewsPlus, PS1, PS2, PS3, PS4, PS5, PS6, PS7, PS8, SCPlus, SCStandard, SNP;

		public static EnumBackISOPaperSubstrate getEnum(final String val)
		{
			return JavaEnumUtil.getEnumIgnoreCase(EnumBackISOPaperSubstrate.class, val, null);
		}
	}

	/**
	 * Enumeration strings for numFluteDirection
	 */

	public enum EnumFluteDirection
	{
		Any, Both, ShortEdge, LongEdge, SameDirection, XDirection, YDirection;

		public static EnumFluteDirection getEnum(final String val)
		{
			return JavaEnumUtil.getEnumIgnoreCase(EnumFluteDirection.class, val, null);
		}
	}

	/**
	 * Enumeration strings for numFrontCoatings
	 */

	public enum EnumFrontCoatings
	{
		None, Coated, Glossy, HighGloss, InkJet, Matte, Polymer, Silver, Satin, Semigloss;

		public static EnumFrontCoatings getEnum(final String val)
		{
			return JavaEnumUtil.getEnumIgnoreCase(EnumFrontCoatings.class, val, null);
		}
	}

	/**
	 * Enumeration strings for numGrainDirection
	 */

	public enum EnumGrainDirection
	{
		Any, Both, ShortEdge, LongEdge, SameDirection, XDirection, YDirection;

		public static EnumGrainDirection getEnum(final String val)
		{
			return JavaEnumUtil.getEnumIgnoreCase(EnumGrainDirection.class, val, null);
		}
	}

	/**
	 * Enumeration strings for numImagableSide
	 */

	public enum EnumImagableSide
	{
		Front, Back, Both, Neither;

		public static EnumImagableSide getEnum(final String val)
		{
			return JavaEnumUtil.getEnumIgnoreCase(EnumImagableSide.class, val, null);
		}
	}

	/**
	 * Enumeration strings for numISOPaperSubstrate
	 */

	public enum EnumISOPaperSubstrate
	{
		LWCPlus, LWCStandard, NewsPlus, PS1, PS2, PS3, PS4, PS5, PS6, PS7, PS8, SCPlus, SCStandard, SNP;

		public static EnumISOPaperSubstrate getEnum(final String val)
		{
			return JavaEnumUtil.getEnumIgnoreCase(EnumISOPaperSubstrate.class, val, null);
		}
	}

	/**
	 * Enumeration strings for numMediaType
	 */

	public enum EnumMediaType
	{
		Blanket, CorrugatedBoard, Disc, EndBoard, EmbossingFoil, Film, Foil, GravureCylinder, ImagingCylinder, LaminatingFoil, MountingTape, Other, Paper, Plate, Screen, SelfAdhesive, Sleeve, ShrinkFoil, Synthetic, Textile, Transparency, Unknown, Vinyl;

		public static EnumMediaType getEnum(final String val)
		{
			return JavaEnumUtil.getEnumIgnoreCase(EnumMediaType.class, val, null);
		}
	}

	/**
	 * Enumeration strings for numOpacity
	 */

	public enum EnumOpacity
	{
		Opaque, Translucent, Transparent;

		public static EnumOpacity getEnum(final String val)
		{
			return JavaEnumUtil.getEnumIgnoreCase(EnumOpacity.class, val, null);
		}
	}

	/**
	 * Enumeration strings for numPlateTechnology
	 */

	public enum EnumPlateTechnology
	{
		FlexoAnalogSolvent, FlexoAnalogThermal, FlexoDigitalSolvent, FlexoDigitalThermal, FlexoDirectEngraving, InkJet, Thermal, UV, Visible;

		public static EnumPlateTechnology getEnum(final String val)
		{
			return JavaEnumUtil.getEnumIgnoreCase(EnumPlateTechnology.class, val, null);
		}
	}

	/**
	 * Enumeration strings for numPolarity
	 */

	public enum EnumPolarity
	{
		Positive, Negative;

		public static EnumPolarity getEnum(final String val)
		{
			return JavaEnumUtil.getEnumIgnoreCase(EnumPolarity.class, val, null);
		}
	}/*
		 * ************************************************************************
		 * Attribute getter / setter
		 * ************************************************************************
		 */

	/*
	 * ---------------------------------------------------------------------
	 * Methods for Attribute HoleType
	 * ---------------------------------------------------------------------
	 */
	/**
	 * (5.2) set attribute HoleType
	 *
	 * @param v List of the enumeration values
	 */
	public void setHoleType(final List<EnumHoleType> v)
	{
		setEnumsAttribute(AttributeName.HOLETYPE, v, null);
	}

	/**
	 * (9.2) get HoleType attribute HoleType
	 *
	 * @return Vector of the enumerations
	 */
	public List<EnumHoleType> getHoleType()
	{
		return getEnumerationsAttribute(AttributeName.HOLETYPE, null, EnumHoleType.class);
	}

	/*
	 * ---------------------------------------------------------------------
	 * Methods for Attribute MediaUnit
	 * ---------------------------------------------------------------------
	 */
	/**
	 * (5) set attribute MediaUnit
	 *
	 * @param enumVar the enumVar to set the attribute to
	 */
	public void setMediaUnit(final EnumMediaUnit enumVar)
	{
		setAttribute(AttributeName.MEDIAUNIT, JavaEnumUtil.getName(enumVar), null);
	}

	/**
	 * (9) get attribute MediaUnit
	 *
	 * @return the value of the attribute
	 */
	public EnumMediaUnit getMediaUnit()
	{
		return EnumMediaUnit.getEnum(getAttribute(AttributeName.MEDIAUNIT, null, "Sheet"));
	}

	/*
	 * ---------------------------------------------------------------------
	 * Methods for Attribute PrePrinted
	 * ---------------------------------------------------------------------
	 */
	/**
	 * (36) set attribute PrePrinted
	 *
	 * @param value the value to set the attribute to
	 */
	public void setPrePrinted(final boolean value)
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

	/*
	 * ---------------------------------------------------------------------
	 * Methods for Attribute BackBrightness
	 * ---------------------------------------------------------------------
	 */
	/**
	 * (36) set attribute BackBrightness
	 *
	 * @param value the value to set the attribute to
	 */
	public void setBackBrightness(final double value)
	{
		setAttribute(AttributeName.BACKBRIGHTNESS, value, null);
	}

	/**
	 * (17) get double attribute BackBrightness
	 *
	 * @return double the value of the attribute
	 */
	public double getBackBrightness()
	{
		return getRealAttribute(AttributeName.BACKBRIGHTNESS, null, 0.0);
	}

	/*
	 * ---------------------------------------------------------------------
	 * Methods for Attribute BackCoatingDetail
	 * ---------------------------------------------------------------------
	 */
	/**
	 * (36) set attribute BackCoatingDetail
	 *
	 * @param value the value to set the attribute to
	 */
	public void setBackCoatingDetail(final String value)
	{
		setAttribute(AttributeName.BACKCOATINGDETAIL, value, null);
	}

	/**
	 * (23) get String attribute BackCoatingDetail
	 *
	 * @return the value of the attribute
	 */
	public String getBackCoatingDetail()
	{
		return getAttribute(AttributeName.BACKCOATINGDETAIL, null, JDFCoreConstants.EMPTYSTRING);
	}

	/*
	 * ---------------------------------------------------------------------
	 * Methods for Attribute BackCoatings
	 * ---------------------------------------------------------------------
	 */
	/**
	 * (5) set attribute BackCoatings
	 *
	 * @param enumVar the enumVar to set the attribute to
	 */
	public void setBackCoatings(final EnumBackCoatings enumVar)
	{
		setAttribute(AttributeName.BACKCOATINGS, JavaEnumUtil.getName(enumVar), null);
	}

	/**
	 * (9) get attribute BackCoatings
	 *
	 * @return the value of the attribute
	 */
	public EnumBackCoatings getBackCoatings()
	{
		return EnumBackCoatings.getEnum(getAttribute(AttributeName.BACKCOATINGS, null, null));
	}

	/*
	 * ---------------------------------------------------------------------
	 * Methods for Attribute BackGlossValue
	 * ---------------------------------------------------------------------
	 */
	/**
	 * (36) set attribute BackGlossValue
	 *
	 * @param value the value to set the attribute to
	 */
	public void setBackGlossValue(final double value)
	{
		setAttribute(AttributeName.BACKGLOSSVALUE, value, null);
	}

	/**
	 * (17) get double attribute BackGlossValue
	 *
	 * @return double the value of the attribute
	 */
	public double getBackGlossValue()
	{
		return getRealAttribute(AttributeName.BACKGLOSSVALUE, null, 0.0);
	}

	/*
	 * ---------------------------------------------------------------------
	 * Methods for Attribute BackISOPaperSubstrate
	 * ---------------------------------------------------------------------
	 */
	/**
	 * (5) set attribute BackISOPaperSubstrate
	 *
	 * @param enumVar the enumVar to set the attribute to
	 */
	public void setBackISOPaperSubstrate(final EnumBackISOPaperSubstrate enumVar)
	{
		setAttribute(AttributeName.BACKISOPAPERSUBSTRATE, JavaEnumUtil.getName(enumVar), null);
	}

	/**
	 * (9) get attribute BackISOPaperSubstrate
	 *
	 * @return the value of the attribute
	 */
	public EnumBackISOPaperSubstrate getBackISOPaperSubstrate()
	{
		return EnumBackISOPaperSubstrate.getEnum(getAttribute(AttributeName.BACKISOPAPERSUBSTRATE, null, null));
	}

	/*
	 * ---------------------------------------------------------------------
	 * Methods for Attribute BackOpticalBrightening
	 * ---------------------------------------------------------------------
	 */
	/**
	 * (36) set attribute BackOpticalBrightening
	 *
	 * @param value the value to set the attribute to
	 */
	public void setBackOpticalBrightening(final double value)
	{
		setAttribute(AttributeName.BACKOPTICALBRIGHTENING, value, null);
	}

	/**
	 * (17) get double attribute BackOpticalBrightening
	 *
	 * @return double the value of the attribute
	 */
	public double getBackOpticalBrightening()
	{
		return getRealAttribute(AttributeName.BACKOPTICALBRIGHTENING, null, 0.0);
	}

	/*
	 * ---------------------------------------------------------------------
	 * Methods for Attribute BackSpectrum
	 * ---------------------------------------------------------------------
	 */
	/**
	 * (36) set attribute BackSpectrum
	 *
	 * @param value the value to set the attribute to
	 */
	public void setBackSpectrum(final JDFTransferFunction value)
	{
		setAttribute(AttributeName.BACKSPECTRUM, value, null);
	}

	/**
	 * (20) get JDFTransferFunction attribute BackSpectrum
	 *
	 * @return JDFTransferFunction the value of the attribute, null if a the
	 *         attribute value is not a valid to create a JDFTransferFunction
	 */
	public JDFTransferFunction getBackSpectrum()
	{
		final String strAttrName = getAttribute(AttributeName.BACKSPECTRUM, null, null);
		final JDFTransferFunction nPlaceHolder = JDFTransferFunction.createTransferFunction(strAttrName);
		return nPlaceHolder;
	}

	/*
	 * ---------------------------------------------------------------------
	 * Methods for Attribute Brightness
	 * ---------------------------------------------------------------------
	 */
	/**
	 * (36) set attribute Brightness
	 *
	 * @param value the value to set the attribute to
	 */
	public void setBrightness(final double value)
	{
		setAttribute(AttributeName.BRIGHTNESS, value, null);
	}

	/**
	 * (17) get double attribute Brightness
	 *
	 * @return double the value of the attribute
	 */
	public double getBrightness()
	{
		return getRealAttribute(AttributeName.BRIGHTNESS, null, 0.0);
	}

	/*
	 * ---------------------------------------------------------------------
	 * Methods for Attribute CIETint
	 * ---------------------------------------------------------------------
	 */
	/**
	 * (36) set attribute CIETint
	 *
	 * @param value the value to set the attribute to
	 */
	public void setCIETint(final double value)
	{
		setAttribute(AttributeName.CIETINT, value, null);
	}

	/**
	 * (17) get double attribute CIETint
	 *
	 * @return double the value of the attribute
	 */
	public double getCIETint()
	{
		return getRealAttribute(AttributeName.CIETINT, null, 0.0);
	}

	/*
	 * ---------------------------------------------------------------------
	 * Methods for Attribute CIEWhiteness
	 * ---------------------------------------------------------------------
	 */
	/**
	 * (36) set attribute CIEWhiteness
	 *
	 * @param value the value to set the attribute to
	 */
	public void setCIEWhiteness(final double value)
	{
		setAttribute(AttributeName.CIEWHITENESS, value, null);
	}

	/**
	 * (17) get double attribute CIEWhiteness
	 *
	 * @return double the value of the attribute
	 */
	public double getCIEWhiteness()
	{
		return getRealAttribute(AttributeName.CIEWHITENESS, null, 0.0);
	}

	/*
	 * ---------------------------------------------------------------------
	 * Methods for Attribute ColorName
	 * ---------------------------------------------------------------------
	 */
	/**
	 * (36) set attribute ColorName
	 *
	 * @param value the value to set the attribute to
	 */
	public void setColorName(final String value)
	{
		setAttribute(AttributeName.COLORNAME, value, null);
	}

	/**
	 * (23) get String attribute ColorName
	 *
	 * @return the value of the attribute
	 */
	public String getColorName()
	{
		return getAttribute(AttributeName.COLORNAME, null, JDFCoreConstants.EMPTYSTRING);
	}

	/*
	 * ---------------------------------------------------------------------
	 * Methods for Attribute CoreWeight
	 * ---------------------------------------------------------------------
	 */
	/**
	 * (36) set attribute CoreWeight
	 *
	 * @param value the value to set the attribute to
	 */
	public void setCoreWeight(final double value)
	{
		setAttribute(AttributeName.COREWEIGHT, value, null);
	}

	/**
	 * (17) get double attribute CoreWeight
	 *
	 * @return double the value of the attribute
	 */
	public double getCoreWeight()
	{
		return getRealAttribute(AttributeName.COREWEIGHT, null, 0.0);
	}

	/*
	 * ---------------------------------------------------------------------
	 * Methods for Attribute Dimension
	 * ---------------------------------------------------------------------
	 */
	/**
	 * (36) set attribute Dimension
	 *
	 * @param value the value to set the attribute to
	 */
	public void setDimension(final JDFXYPair value)
	{
		setAttribute(AttributeName.DIMENSION, value, null);
	}

	/**
	 * (20) get JDFXYPair attribute Dimension
	 *
	 * @return JDFXYPair the value of the attribute, null if a the
	 *         attribute value is not a valid to create a JDFXYPair
	 */
	public JDFXYPair getDimension()
	{
		final String strAttrName = getAttribute(AttributeName.DIMENSION, null, null);
		final JDFXYPair nPlaceHolder = JDFXYPair.createXYPair(strAttrName);
		return nPlaceHolder;
	}

	/*
	 * ---------------------------------------------------------------------
	 * Methods for Attribute Flute
	 * ---------------------------------------------------------------------
	 */
	/**
	 * (36) set attribute Flute
	 *
	 * @param value the value to set the attribute to
	 */
	public void setFlute(final String value)
	{
		setAttribute(AttributeName.FLUTE, value, null);
	}

	/**
	 * (23) get String attribute Flute
	 *
	 * @return the value of the attribute
	 */
	public String getFlute()
	{
		return getAttribute(AttributeName.FLUTE, null, JDFCoreConstants.EMPTYSTRING);
	}

	/*
	 * ---------------------------------------------------------------------
	 * Methods for Attribute FluteDirection
	 * ---------------------------------------------------------------------
	 */
	/**
	 * (5) set attribute FluteDirection
	 *
	 * @param enumVar the enumVar to set the attribute to
	 */
	public void setFluteDirection(final EnumFluteDirection enumVar)
	{
		setAttribute(AttributeName.FLUTEDIRECTION, JavaEnumUtil.getName(enumVar), null);
	}

	/**
	 * (9) get attribute FluteDirection
	 *
	 * @return the value of the attribute
	 */
	public EnumFluteDirection getFluteDirection()
	{
		return EnumFluteDirection.getEnum(getAttribute(AttributeName.FLUTEDIRECTION, null, null));
	}

	/*
	 * ---------------------------------------------------------------------
	 * Methods for Attribute FrontCoatingDetail
	 * ---------------------------------------------------------------------
	 */
	/**
	 * (36) set attribute FrontCoatingDetail
	 *
	 * @param value the value to set the attribute to
	 */
	public void setFrontCoatingDetail(final String value)
	{
		setAttribute(AttributeName.FRONTCOATINGDETAIL, value, null);
	}

	/**
	 * (23) get String attribute FrontCoatingDetail
	 *
	 * @return the value of the attribute
	 */
	public String getFrontCoatingDetail()
	{
		return getAttribute(AttributeName.FRONTCOATINGDETAIL, null, JDFCoreConstants.EMPTYSTRING);
	}

	/*
	 * ---------------------------------------------------------------------
	 * Methods for Attribute FrontCoatings
	 * ---------------------------------------------------------------------
	 */
	/**
	 * (5) set attribute FrontCoatings
	 *
	 * @param enumVar the enumVar to set the attribute to
	 */
	public void setFrontCoatings(final EnumFrontCoatings enumVar)
	{
		setAttribute(AttributeName.FRONTCOATINGS, JavaEnumUtil.getName(enumVar), null);
	}

	/**
	 * (9) get attribute FrontCoatings
	 *
	 * @return the value of the attribute
	 */
	public EnumFrontCoatings getFrontCoatings()
	{
		return EnumFrontCoatings.getEnum(getAttribute(AttributeName.FRONTCOATINGS, null, null));
	}

	/*
	 * ---------------------------------------------------------------------
	 * Methods for Attribute FrontGlossValue
	 * ---------------------------------------------------------------------
	 */
	/**
	 * (36) set attribute FrontGlossValue
	 *
	 * @param value the value to set the attribute to
	 */
	public void setFrontGlossValue(final double value)
	{
		setAttribute(AttributeName.FRONTGLOSSVALUE, value, null);
	}

	/**
	 * (17) get double attribute FrontGlossValue
	 *
	 * @return double the value of the attribute
	 */
	public double getFrontGlossValue()
	{
		return getRealAttribute(AttributeName.FRONTGLOSSVALUE, null, 0.0);
	}

	/*
	 * ---------------------------------------------------------------------
	 * Methods for Attribute Grade
	 * ---------------------------------------------------------------------
	 */
	/**
	 * (36) set attribute Grade
	 *
	 * @param value the value to set the attribute to
	 */
	public void setGrade(final int value)
	{
		setAttribute(AttributeName.GRADE, value, null);
	}

	/**
	 * (15) get int attribute Grade
	 *
	 * @return int the value of the attribute
	 */
	public int getGrade()
	{
		return getIntAttribute(AttributeName.GRADE, null, 0);
	}

	/*
	 * ---------------------------------------------------------------------
	 * Methods for Attribute GrainDirection
	 * ---------------------------------------------------------------------
	 */
	/**
	 * (5) set attribute GrainDirection
	 *
	 * @param enumVar the enumVar to set the attribute to
	 */
	public void setGrainDirection(final EnumGrainDirection enumVar)
	{
		setAttribute(AttributeName.GRAINDIRECTION, JavaEnumUtil.getName(enumVar), null);
	}

	/**
	 * (9) get attribute GrainDirection
	 *
	 * @return the value of the attribute
	 */
	public EnumGrainDirection getGrainDirection()
	{
		return EnumGrainDirection.getEnum(getAttribute(AttributeName.GRAINDIRECTION, null, null));
	}

	/*
	 * ---------------------------------------------------------------------
	 * Methods for Attribute HoleCount
	 * ---------------------------------------------------------------------
	 */
	/**
	 * (36) set attribute HoleCount
	 *
	 * @param value the value to set the attribute to
	 */
	public void setHoleCount(final int value)
	{
		setAttribute(AttributeName.HOLECOUNT, value, null);
	}

	/**
	 * (15) get int attribute HoleCount
	 *
	 * @return int the value of the attribute
	 */
	public int getHoleCount()
	{
		return getIntAttribute(AttributeName.HOLECOUNT, null, 0);
	}

	/*
	 * ---------------------------------------------------------------------
	 * Methods for Attribute ImagableSide
	 * ---------------------------------------------------------------------
	 */
	/**
	 * (5) set attribute ImagableSide
	 *
	 * @param enumVar the enumVar to set the attribute to
	 */
	public void setImagableSide(final EnumImagableSide enumVar)
	{
		setAttribute(AttributeName.IMAGABLESIDE, JavaEnumUtil.getName(enumVar), null);
	}

	/**
	 * (9) get attribute ImagableSide
	 *
	 * @return the value of the attribute
	 */
	public EnumImagableSide getImagableSide()
	{
		return EnumImagableSide.getEnum(getAttribute(AttributeName.IMAGABLESIDE, null, null));
	}

	/*
	 * ---------------------------------------------------------------------
	 * Methods for Attribute InnerCoreDiameter
	 * ---------------------------------------------------------------------
	 */
	/**
	 * (36) set attribute InnerCoreDiameter
	 *
	 * @param value the value to set the attribute to
	 */
	public void setInnerCoreDiameter(final double value)
	{
		setAttribute(AttributeName.INNERCOREDIAMETER, value, null);
	}

	/**
	 * (17) get double attribute InnerCoreDiameter
	 *
	 * @return double the value of the attribute
	 */
	public double getInnerCoreDiameter()
	{
		return getRealAttribute(AttributeName.INNERCOREDIAMETER, null, 0.0);
	}

	/*
	 * ---------------------------------------------------------------------
	 * Methods for Attribute InsideLoss
	 * ---------------------------------------------------------------------
	 */
	/**
	 * (36) set attribute InsideLoss
	 *
	 * @param value the value to set the attribute to
	 */
	public void setInsideLoss(final double value)
	{
		setAttribute(AttributeName.INSIDELOSS, value, null);
	}

	/**
	 * (17) get double attribute InsideLoss
	 *
	 * @return double the value of the attribute
	 */
	public double getInsideLoss()
	{
		return getRealAttribute(AttributeName.INSIDELOSS, null, 0.0);
	}

	/*
	 * ---------------------------------------------------------------------
	 * Methods for Attribute ISOPaperSubstrate
	 * ---------------------------------------------------------------------
	 */
	/**
	 * (5) set attribute ISOPaperSubstrate
	 *
	 * @param enumVar the enumVar to set the attribute to
	 */
	public void setISOPaperSubstrate(final EnumISOPaperSubstrate enumVar)
	{
		setAttribute(AttributeName.ISOPAPERSUBSTRATE, JavaEnumUtil.getName(enumVar), null);
	}

	/**
	 * (9) get attribute ISOPaperSubstrate
	 *
	 * @return the value of the attribute
	 */
	public EnumISOPaperSubstrate getISOPaperSubstrate()
	{
		return EnumISOPaperSubstrate.getEnum(getAttribute(AttributeName.ISOPAPERSUBSTRATE, null, null));
	}

	/*
	 * ---------------------------------------------------------------------
	 * Methods for Attribute LabColorValue
	 * ---------------------------------------------------------------------
	 */
	/**
	 * (36) set attribute LabColorValue
	 *
	 * @param value the value to set the attribute to
	 */
	public void setLabColorValue(final JDFLabColor value)
	{
		setAttribute(AttributeName.LABCOLORVALUE, value, null);
	}

	/**
	 * (20) get JDFLabColor attribute LabColorValue
	 *
	 * @return JDFLabColor the value of the attribute, null if a the
	 *         attribute value is not a valid to create a JDFLabColor
	 */
	public JDFLabColor getLabColorValue()
	{
		final String strAttrName = getAttribute(AttributeName.LABCOLORVALUE, null, null);
		final JDFLabColor nPlaceHolder = JDFLabColor.createLabColor(strAttrName);
		return nPlaceHolder;
	}

	/*
	 * ---------------------------------------------------------------------
	 * Methods for Attribute MediaColorName
	 * ---------------------------------------------------------------------
	 */
	/**
	 * (13) set attribute MediaColorName
	 *
	 * @param value the value to set the attribute to
	 */
	public void setMediaColorName(final EnumNamedColor value)
	{
		setAttribute(AttributeName.MEDIACOLORNAME, value == null ? null : value.getName(), null);
	}

	/**
	 * (19) get EnumNamedColor attribute MediaColorName
	 *
	 * @return EnumNamedColor the value of the attribute
	 */
	public EnumNamedColor getMediaColorName()
	{
		String strAttrName = "";
		EnumNamedColor nPlaceHolder = null;
		strAttrName = getAttribute(AttributeName.MEDIACOLORNAME, null, JDFCoreConstants.EMPTYSTRING);
		nPlaceHolder = EnumNamedColor.getEnum(strAttrName);
		return nPlaceHolder;
	}

	/*
	 * ---------------------------------------------------------------------
	 * Methods for Attribute MediaColorNameDetails
	 * ---------------------------------------------------------------------
	 */
	/**
	 * (36) set attribute MediaColorNameDetails
	 *
	 * @param value the value to set the attribute to
	 */
	public void setMediaColorNameDetails(final String value)
	{
		setAttribute(AttributeName.MEDIACOLORNAMEDETAILS, value, null);
	}

	/**
	 * (23) get String attribute MediaColorNameDetails
	 *
	 * @return the value of the attribute
	 */
	public String getMediaColorNameDetails()
	{
		return getAttribute(AttributeName.MEDIACOLORNAMEDETAILS, null, JDFCoreConstants.EMPTYSTRING);
	}

	/*
	 * ---------------------------------------------------------------------
	 * Methods for Attribute MediaQuality
	 * ---------------------------------------------------------------------
	 */
	/**
	 * (36) set attribute MediaQuality
	 *
	 * @param value the value to set the attribute to
	 */
	public void setMediaQuality(final String value)
	{
		setAttribute(AttributeName.MEDIAQUALITY, value, null);
	}

	/**
	 * (23) get String attribute MediaQuality
	 *
	 * @return the value of the attribute
	 */
	public String getMediaQuality()
	{
		return getAttribute(AttributeName.MEDIAQUALITY, null, JDFCoreConstants.EMPTYSTRING);
	}

	/*
	 * ---------------------------------------------------------------------
	 * Methods for Attribute MediaSetCount
	 * ---------------------------------------------------------------------
	 */
	/**
	 * (36) set attribute MediaSetCount
	 *
	 * @param value the value to set the attribute to
	 */
	public void setMediaSetCount(final int value)
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

	/*
	 * ---------------------------------------------------------------------
	 * Methods for Attribute MediaType
	 * ---------------------------------------------------------------------
	 */
	/**
	 * (5) set attribute MediaType
	 *
	 * @param enumVar the enumVar to set the attribute to
	 */
	public void setMediaType(final EnumMediaType enumVar)
	{
		setAttribute(AttributeName.MEDIATYPE, JavaEnumUtil.getName(enumVar), null);
	}

	/**
	 * (9) get attribute MediaType
	 *
	 * @return the value of the attribute
	 */
	public EnumMediaType getMediaType()
	{
		return EnumMediaType.getEnum(getAttribute(AttributeName.MEDIATYPE, null, null));
	}

	/*
	 * ---------------------------------------------------------------------
	 * Methods for Attribute MediaTypeDetails
	 * ---------------------------------------------------------------------
	 */
	/**
	 * (36) set attribute MediaTypeDetails
	 *
	 * @param value the value to set the attribute to
	 */
	public void setMediaTypeDetails(final String value)
	{
		setAttribute(AttributeName.MEDIATYPEDETAILS, value, null);
	}

	/**
	 * (23) get String attribute MediaTypeDetails
	 *
	 * @return the value of the attribute
	 */
	public String getMediaTypeDetails()
	{
		return getAttribute(AttributeName.MEDIATYPEDETAILS, null, JDFCoreConstants.EMPTYSTRING);
	}

	/*
	 * ---------------------------------------------------------------------
	 * Methods for Attribute Opacity
	 * ---------------------------------------------------------------------
	 */
	/**
	 * (5) set attribute Opacity
	 *
	 * @param enumVar the enumVar to set the attribute to
	 */
	public void setOpacity(final EnumOpacity enumVar)
	{
		setAttribute(AttributeName.OPACITY, JavaEnumUtil.getName(enumVar), null);
	}

	/**
	 * (9) get attribute Opacity
	 *
	 * @return the value of the attribute
	 */
	public EnumOpacity getOpacity()
	{
		return EnumOpacity.getEnum(getAttribute(AttributeName.OPACITY, null, null));
	}

	/*
	 * ---------------------------------------------------------------------
	 * Methods for Attribute OpacityLevel
	 * ---------------------------------------------------------------------
	 */
	/**
	 * (36) set attribute OpacityLevel
	 *
	 * @param value the value to set the attribute to
	 */
	public void setOpacityLevel(final double value)
	{
		setAttribute(AttributeName.OPACITYLEVEL, value, null);
	}

	/**
	 * (17) get double attribute OpacityLevel
	 *
	 * @return double the value of the attribute
	 */
	public double getOpacityLevel()
	{
		return getRealAttribute(AttributeName.OPACITYLEVEL, null, 0.0);
	}

	/*
	 * ---------------------------------------------------------------------
	 * Methods for Attribute OpticalBrightening
	 * ---------------------------------------------------------------------
	 */
	/**
	 * (36) set attribute OpticalBrightening
	 *
	 * @param value the value to set the attribute to
	 */
	public void setOpticalBrightening(final double value)
	{
		setAttribute(AttributeName.OPTICALBRIGHTENING, value, null);
	}

	/**
	 * (17) get double attribute OpticalBrightening
	 *
	 * @return double the value of the attribute
	 */
	public double getOpticalBrightening()
	{
		return getRealAttribute(AttributeName.OPTICALBRIGHTENING, null, 0.0);
	}

	/*
	 * ---------------------------------------------------------------------
	 * Methods for Attribute OuterCoreDiameter
	 * ---------------------------------------------------------------------
	 */
	/**
	 * (36) set attribute OuterCoreDiameter
	 *
	 * @param value the value to set the attribute to
	 */
	public void setOuterCoreDiameter(final double value)
	{
		setAttribute(AttributeName.OUTERCOREDIAMETER, value, null);
	}

	/**
	 * (17) get double attribute OuterCoreDiameter
	 *
	 * @return double the value of the attribute
	 */
	public double getOuterCoreDiameter()
	{
		return getRealAttribute(AttributeName.OUTERCOREDIAMETER, null, 0.0);
	}

	/*
	 * ---------------------------------------------------------------------
	 * Methods for Attribute OutsideGain
	 * ---------------------------------------------------------------------
	 */
	/**
	 * (36) set attribute OutsideGain
	 *
	 * @param value the value to set the attribute to
	 */
	public void setOutsideGain(final double value)
	{
		setAttribute(AttributeName.OUTSIDEGAIN, value, null);
	}

	/**
	 * (17) get double attribute OutsideGain
	 *
	 * @return double the value of the attribute
	 */
	public double getOutsideGain()
	{
		return getRealAttribute(AttributeName.OUTSIDEGAIN, null, 0.0);
	}

	/*
	 * ---------------------------------------------------------------------
	 * Methods for Attribute PlateTechnology
	 * ---------------------------------------------------------------------
	 */
	/**
	 * (5) set attribute PlateTechnology
	 *
	 * @param enumVar the enumVar to set the attribute to
	 */
	public void setPlateTechnology(final EnumPlateTechnology enumVar)
	{
		setAttribute(AttributeName.PLATETECHNOLOGY, JavaEnumUtil.getName(enumVar), null);
	}

	/**
	 * (9) get attribute PlateTechnology
	 *
	 * @return the value of the attribute
	 */
	public EnumPlateTechnology getPlateTechnology()
	{
		return EnumPlateTechnology.getEnum(getAttribute(AttributeName.PLATETECHNOLOGY, null, null));
	}

	/*
	 * ---------------------------------------------------------------------
	 * Methods for Attribute Polarity
	 * ---------------------------------------------------------------------
	 */
	/**
	 * (5) set attribute Polarity
	 *
	 * @param enumVar the enumVar to set the attribute to
	 */
	public void setPolarity(final EnumPolarity enumVar)
	{
		setAttribute(AttributeName.POLARITY, JavaEnumUtil.getName(enumVar), null);
	}

	/**
	 * (9) get attribute Polarity
	 *
	 * @return the value of the attribute
	 */
	public EnumPolarity getPolarity()
	{
		return EnumPolarity.getEnum(getAttribute(AttributeName.POLARITY, null, null));
	}

	/*
	 * ---------------------------------------------------------------------
	 * Methods for Attribute PrintingTechnology
	 * ---------------------------------------------------------------------
	 */
	/**
	 * (36) set attribute PrintingTechnology
	 *
	 * @param value the value to set the attribute to
	 */
	public void setPrintingTechnology(final String value)
	{
		setAttribute(AttributeName.PRINTINGTECHNOLOGY, value, null);
	}

	/**
	 * (23) get String attribute PrintingTechnology
	 *
	 * @return the value of the attribute
	 */
	public String getPrintingTechnology()
	{
		return getAttribute(AttributeName.PRINTINGTECHNOLOGY, null, JDFCoreConstants.EMPTYSTRING);
	}

	/*
	 * ---------------------------------------------------------------------
	 * Methods for Attribute Recycled
	 * ---------------------------------------------------------------------
	 */
	/**
	 * (36) set attribute Recycled
	 *
	 * @param value the value to set the attribute to
	 */
	public void setRecycled(final boolean value)
	{
		setAttribute(AttributeName.RECYCLED, value, null);
	}

	/**
	 * (18) get boolean attribute Recycled
	 *
	 * @return boolean the value of the attribute
	 */
	public boolean getRecycled()
	{
		return getBoolAttribute(AttributeName.RECYCLED, null, false);
	}

	/*
	 * ---------------------------------------------------------------------
	 * Methods for Attribute RecycledPercentage
	 * ---------------------------------------------------------------------
	 */
	/**
	 * (36) set attribute RecycledPercentage
	 *
	 * @param value the value to set the attribute to
	 */
	public void setRecycledPercentage(final double value)
	{
		setAttribute(AttributeName.RECYCLEDPERCENTAGE, value, null);
	}

	/**
	 * (17) get double attribute RecycledPercentage
	 *
	 * @return double the value of the attribute
	 */
	public double getRecycledPercentage()
	{
		return getRealAttribute(AttributeName.RECYCLEDPERCENTAGE, null, 0.0);
	}

	/*
	 * ---------------------------------------------------------------------
	 * Methods for Attribute ReliefThickness
	 * ---------------------------------------------------------------------
	 */
	/**
	 * (36) set attribute ReliefThickness
	 *
	 * @param value the value to set the attribute to
	 */
	public void setReliefThickness(final double value)
	{
		setAttribute(AttributeName.RELIEFTHICKNESS, value, null);
	}

	/**
	 * (17) get double attribute ReliefThickness
	 *
	 * @return double the value of the attribute
	 */
	public double getReliefThickness()
	{
		return getRealAttribute(AttributeName.RELIEFTHICKNESS, null, 0.0);
	}

	/*
	 * ---------------------------------------------------------------------
	 * Methods for Attribute RollDiameter
	 * ---------------------------------------------------------------------
	 */
	/**
	 * (36) set attribute RollDiameter
	 *
	 * @param value the value to set the attribute to
	 */
	public void setRollDiameter(final double value)
	{
		setAttribute(AttributeName.ROLLDIAMETER, value, null);
	}

	/**
	 * (17) get double attribute RollDiameter
	 *
	 * @return double the value of the attribute
	 */
	public double getRollDiameter()
	{
		return getRealAttribute(AttributeName.ROLLDIAMETER, null, 0.0);
	}

	/*
	 * ---------------------------------------------------------------------
	 * Methods for Attribute ShrinkIndex
	 * ---------------------------------------------------------------------
	 */
	/**
	 * (36) set attribute ShrinkIndex
	 *
	 * @param value the value to set the attribute to
	 */
	public void setShrinkIndex(final JDFXYPair value)
	{
		setAttribute(AttributeName.SHRINKINDEX, value, null);
	}

	/**
	 * (20) get JDFXYPair attribute ShrinkIndex
	 *
	 * @return JDFXYPair the value of the attribute, null if a the
	 *         attribute value is not a valid to create a JDFXYPair
	 */
	public JDFXYPair getShrinkIndex()
	{
		final String strAttrName = getAttribute(AttributeName.SHRINKINDEX, null, null);
		final JDFXYPair nPlaceHolder = JDFXYPair.createXYPair(strAttrName);
		return nPlaceHolder;
	}

	/*
	 * ---------------------------------------------------------------------
	 * Methods for Attribute SleeveInterlock
	 * ---------------------------------------------------------------------
	 */
	/**
	 * (36) set attribute SleeveInterlock
	 *
	 * @param value the value to set the attribute to
	 */
	public void setSleeveInterlock(final String value)
	{
		setAttribute(AttributeName.SLEEVEINTERLOCK, value, null);
	}

	/**
	 * (23) get String attribute SleeveInterlock
	 *
	 * @return the value of the attribute
	 */
	public String getSleeveInterlock()
	{
		return getAttribute(AttributeName.SLEEVEINTERLOCK, null, JDFCoreConstants.EMPTYSTRING);
	}

	/*
	 * ---------------------------------------------------------------------
	 * Methods for Attribute Spectrum
	 * ---------------------------------------------------------------------
	 */
	/**
	 * (36) set attribute Spectrum
	 *
	 * @param value the value to set the attribute to
	 */
	public void setSpectrum(final JDFTransferFunction value)
	{
		setAttribute(AttributeName.SPECTRUM, value, null);
	}

	/**
	 * (20) get JDFTransferFunction attribute Spectrum
	 *
	 * @return JDFTransferFunction the value of the attribute, null if a the
	 *         attribute value is not a valid to create a JDFTransferFunction
	 */
	public JDFTransferFunction getSpectrum()
	{
		final String strAttrName = getAttribute(AttributeName.SPECTRUM, null, null);
		final JDFTransferFunction nPlaceHolder = JDFTransferFunction.createTransferFunction(strAttrName);
		return nPlaceHolder;
	}

	/*
	 * ---------------------------------------------------------------------
	 * Methods for Attribute StockType
	 * ---------------------------------------------------------------------
	 */
	/**
	 * (36) set attribute StockType
	 *
	 * @param value the value to set the attribute to
	 */
	public void setStockType(final String value)
	{
		setAttribute(AttributeName.STOCKTYPE, value, null);
	}

	/**
	 * (23) get String attribute StockType
	 *
	 * @return the value of the attribute
	 */
	public String getStockType()
	{
		return getAttribute(AttributeName.STOCKTYPE, null, JDFCoreConstants.EMPTYSTRING);
	}

	/*
	 * ---------------------------------------------------------------------
	 * Methods for Attribute Texture
	 * ---------------------------------------------------------------------
	 */
	/**
	 * (36) set attribute Texture
	 *
	 * @param value the value to set the attribute to
	 */
	public void setTexture(final String value)
	{
		setAttribute(AttributeName.TEXTURE, value, null);
	}

	/**
	 * (23) get String attribute Texture
	 *
	 * @return the value of the attribute
	 */
	public String getTexture()
	{
		return getAttribute(AttributeName.TEXTURE, null, JDFCoreConstants.EMPTYSTRING);
	}

	/*
	 * ---------------------------------------------------------------------
	 * Methods for Attribute Thickness
	 * ---------------------------------------------------------------------
	 */
	/**
	 * (36) set attribute Thickness
	 *
	 * @param value the value to set the attribute to
	 */
	public void setThickness(final double value)
	{
		setAttribute(AttributeName.THICKNESS, value, null);
	}

	/**
	 * (17) get double attribute Thickness
	 *
	 * @return double the value of the attribute
	 */
	public double getThickness()
	{
		return getRealAttribute(AttributeName.THICKNESS, null, 0.0);
	}

	/*
	 * ---------------------------------------------------------------------
	 * Methods for Attribute UserMediaType
	 * ---------------------------------------------------------------------
	 */
	/**
	 * (36) set attribute UserMediaType
	 *
	 * @param value the value to set the attribute to
	 */
	public void setUserMediaType(final String value)
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

	/*
	 * ---------------------------------------------------------------------
	 * Methods for Attribute Weight
	 * ---------------------------------------------------------------------
	 */
	/**
	 * (36) set attribute Weight
	 *
	 * @param value the value to set the attribute to
	 */
	public void setWeight(final double value)
	{
		setAttribute(AttributeName.WEIGHT, value, null);
	}

	/**
	 * (17) get double attribute Weight
	 *
	 * @return double the value of the attribute
	 */
	public double getWeight()
	{
		return getRealAttribute(AttributeName.WEIGHT, null, 0.0);
	}

	/*
	 * ---------------------------------------------------------------------
	 * Methods for Attribute WrapperWeight
	 * ---------------------------------------------------------------------
	 */
	/**
	 * (36) set attribute WrapperWeight
	 *
	 * @param value the value to set the attribute to
	 */
	public void setWrapperWeight(final double value)
	{
		setAttribute(AttributeName.WRAPPERWEIGHT, value, null);
	}

	/**
	 * (17) get double attribute WrapperWeight
	 *
	 * @return double the value of the attribute
	 */
	public double getWrapperWeight()
	{
		return getRealAttribute(AttributeName.WRAPPERWEIGHT, null, 0.0);
	}

	/*
	 * ***********************************************************************
	 * Element getter / setter
	 * ***********************************************************************
	 */

	/**
	 * (24) const get element Certification
	 *
	 * @return JDFCertification the element
	 */
	public JDFCertification getCertification()
	{
		return (JDFCertification) getElement(ElementName.CERTIFICATION, null, 0);
	}

	/**
	 * (25) getCreateCertification
	 * 
	 * @return JDFCertification the element
	 */
	public JDFCertification getCreateCertification()
	{
		return (JDFCertification) getCreateElement_JDFElement(ElementName.CERTIFICATION, null, 0);
	}

	/**
	 * (26) getCreateCertification
	 * 
	 * @param iSkip number of elements to skip
	 * @return JDFCertification the element
	 */
	public JDFCertification getCreateCertification(final int iSkip)
	{
		return (JDFCertification) getCreateElement_JDFElement(ElementName.CERTIFICATION, null, iSkip);
	}

	/**
	 * (27) const get element Certification
	 *
	 * @param iSkip number of elements to skip
	 * @return JDFCertification the element
	 *         default is getCertification(0)
	 */
	public JDFCertification getCertification(final int iSkip)
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
	 * (24) const get element Color
	 *
	 * @return JDFColor the element
	 */
	public JDFColor getColor()
	{
		return (JDFColor) getElement(ElementName.COLOR, null, 0);
	}

	/**
	 * (25) getCreateColor
	 * 
	 * @return JDFColor the element
	 */
	public JDFColor getCreateColor()
	{
		return (JDFColor) getCreateElement_JDFElement(ElementName.COLOR, null, 0);
	}

	/**
	 * (29) append element Color
	 *
	 * @return JDFColor the element
	 * @ if the element already exists
	 */
	public JDFColor appendColor()
	{
		return (JDFColor) appendElementN(ElementName.COLOR, 1, null);
	}

	/**
	 * (31) create inter-resource link to refTarget
	 *
	 * @param refTarget the element that is referenced
	 */
	public void refColor(final JDFColor refTarget)
	{
		refElement(refTarget);
	}

	/**
	 * (24) const get element ColorMeasurementConditions
	 *
	 * @return JDFColorMeasurementConditions the element
	 */
	public JDFColorMeasurementConditions getColorMeasurementConditions()
	{
		return (JDFColorMeasurementConditions) getElement(ElementName.COLORMEASUREMENTCONDITIONS, null, 0);
	}

	/**
	 * (25) getCreateColorMeasurementConditions
	 * 
	 * @return JDFColorMeasurementConditions the element
	 */
	public JDFColorMeasurementConditions getCreateColorMeasurementConditions()
	{
		return (JDFColorMeasurementConditions) getCreateElement_JDFElement(ElementName.COLORMEASUREMENTCONDITIONS, null, 0);
	}

	/**
	 * (29) append element ColorMeasurementConditions
	 *
	 * @return JDFColorMeasurementConditions the element
	 * @ if the element already exists
	 */
	public JDFColorMeasurementConditions appendColorMeasurementConditions()
	{
		return (JDFColorMeasurementConditions) appendElementN(ElementName.COLORMEASUREMENTCONDITIONS, 1, null);
	}

	/**
	 * (31) create inter-resource link to refTarget
	 *
	 * @param refTarget the element that is referenced
	 */
	public void refColorMeasurementConditions(final JDFColorMeasurementConditions refTarget)
	{
		refElement(refTarget);
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
	 * @return JDFMediaLayers the element
	 * @ if the element already exists
	 */
	public JDFMediaLayers appendMediaLayers()
	{
		return (JDFMediaLayers) appendElementN(ElementName.MEDIALAYERS, 1, null);
	}

	/**
	 * (24) const get element HoleList
	 *
	 * @return JDFHoleList the element
	 */
	public JDFHoleList getHoleList()
	{
		return (JDFHoleList) getElement(ElementName.HOLELIST, null, 0);
	}

	/**
	 * (25) getCreateHoleList
	 * 
	 * @return JDFHoleList the element
	 */
	public JDFHoleList getCreateHoleList()
	{
		return (JDFHoleList) getCreateElement_JDFElement(ElementName.HOLELIST, null, 0);
	}

	/**
	 * (29) append element HoleList
	 *
	 * @return JDFHoleList the element
	 * @ if the element already exists
	 */
	public JDFHoleList appendHoleList()
	{
		return (JDFHoleList) appendElementN(ElementName.HOLELIST, 1, null);
	}

	/**
	 * (24) const get element TabDimensions
	 *
	 * @return JDFTabDimensions the element
	 */
	public JDFTabDimensions getTabDimensions()
	{
		return (JDFTabDimensions) getElement(ElementName.TABDIMENSIONS, null, 0);
	}

	/**
	 * (25) getCreateTabDimensions
	 * 
	 * @return JDFTabDimensions the element
	 */
	public JDFTabDimensions getCreateTabDimensions()
	{
		return (JDFTabDimensions) getCreateElement_JDFElement(ElementName.TABDIMENSIONS, null, 0);
	}

	/**
	 * (26) getCreateTabDimensions
	 * 
	 * @param iSkip number of elements to skip
	 * @return JDFTabDimensions the element
	 */
	public JDFTabDimensions getCreateTabDimensions(final int iSkip)
	{
		return (JDFTabDimensions) getCreateElement_JDFElement(ElementName.TABDIMENSIONS, null, iSkip);
	}

	/**
	 * (27) const get element TabDimensions
	 *
	 * @param iSkip number of elements to skip
	 * @return JDFTabDimensions the element
	 *         default is getTabDimensions(0)
	 */
	public JDFTabDimensions getTabDimensions(final int iSkip)
	{
		return (JDFTabDimensions) getElement(ElementName.TABDIMENSIONS, null, iSkip);
	}

	/**
	 * Get all TabDimensions from the current element
	 * 
	 * @return Collection<JDFTabDimensions>, null if none are available
	 */
	public Collection<JDFTabDimensions> getAllTabDimensions()
	{
		return getChildArrayByClass(JDFTabDimensions.class, false, 0);
	}

	/**
	 * (30) append element TabDimensions
	 *
	 * @return JDFTabDimensions the element
	 */
	public JDFTabDimensions appendTabDimensions()
	{
		return (JDFTabDimensions) appendElement(ElementName.TABDIMENSIONS, null);
	}

	/**
	 * (24) const get element Contact
	 *
	 * @return JDFContact the element
	 */
	@Override
	public JDFContact getContact()
	{
		return (JDFContact) getElement(ElementName.CONTACT, null, 0);
	}

	/**
	 * (25) getCreateContact
	 * 
	 * @return JDFContact the element
	 */
	@Override
	public JDFContact getCreateContact()
	{
		return (JDFContact) getCreateElement_JDFElement(ElementName.CONTACT, null, 0);
	}

	/**
	 * (26) getCreateContact
	 * 
	 * @param iSkip number of elements to skip
	 * @return JDFContact the element
	 */
	public JDFContact getCreateContact(final int iSkip)
	{
		return (JDFContact) getCreateElement_JDFElement(ElementName.CONTACT, null, iSkip);
	}

	/**
	 * (27) const get element Contact
	 *
	 * @param iSkip number of elements to skip
	 * @return JDFContact the element
	 *         default is getContact(0)
	 */
	public JDFContact getContact(final int iSkip)
	{
		return (JDFContact) getElement(ElementName.CONTACT, null, iSkip);
	}

	/**
	 * Get all Contact from the current element
	 * 
	 * @return Collection<JDFContact>, null if none are available
	 */
	public Collection<JDFContact> getAllContact()
	{
		return getChildArrayByClass(JDFContact.class, false, 0);
	}

	/**
	 * (30) append element Contact
	 *
	 * @return JDFContact the element
	 */
	@Override
	public JDFContact appendContact()
	{
		return (JDFContact) appendElement(ElementName.CONTACT, null);
	}

	/**
	 * (31) create inter-resource link to refTarget
	 *
	 * @param refTarget the element that is referenced
	 */
	public void refContact(final JDFContact refTarget)
	{
		refElement(refTarget);
	}

	/**
	 * (24) const get element IdentificationField
	 *
	 * @return JDFIdentificationField the element
	 */
	public JDFIdentificationField getIdentificationField()
	{
		return (JDFIdentificationField) getElement(ElementName.IDENTIFICATIONFIELD, null, 0);
	}

	/**
	 * (25) getCreateIdentificationField
	 * 
	 * @return JDFIdentificationField the element
	 */
	public JDFIdentificationField getCreateIdentificationField()
	{
		return (JDFIdentificationField) getCreateElement_JDFElement(ElementName.IDENTIFICATIONFIELD, null, 0);
	}

	/**
	 * (26) getCreateIdentificationField
	 * 
	 * @param iSkip number of elements to skip
	 * @return JDFIdentificationField the element
	 */
	@Override
	public JDFIdentificationField getCreateIdentificationField(final int iSkip)
	{
		return (JDFIdentificationField) getCreateElement_JDFElement(ElementName.IDENTIFICATIONFIELD, null, iSkip);
	}

	/**
	 * (27) const get element IdentificationField
	 *
	 * @param iSkip number of elements to skip
	 * @return JDFIdentificationField the element
	 *         default is getIdentificationField(0)
	 */
	@Override
	public JDFIdentificationField getIdentificationField(final int iSkip)
	{
		return (JDFIdentificationField) getElement(ElementName.IDENTIFICATIONFIELD, null, iSkip);
	}

	/**
	 * Get all IdentificationField from the current element
	 * 
	 * @return Collection<JDFIdentificationField>, null if none are available
	 */
	public Collection<JDFIdentificationField> getAllIdentificationField()
	{
		return getChildArrayByClass(JDFIdentificationField.class, false, 0);
	}

	/**
	 * (30) append element IdentificationField
	 *
	 * @return JDFIdentificationField the element
	 */
	@Override
	public JDFIdentificationField appendIdentificationField()
	{
		return (JDFIdentificationField) appendElement(ElementName.IDENTIFICATIONFIELD, null);
	}

	/**
	 * (31) create inter-resource link to refTarget
	 *
	 * @param refTarget the element that is referenced
	 */
	public void refIdentificationField(final JDFIdentificationField refTarget)
	{
		refElement(refTarget);
	}

}
