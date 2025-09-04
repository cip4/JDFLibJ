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

import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.enums.ValuedEnum;
import org.apache.xerces.dom.CoreDocumentImpl;
import org.cip4.jdflib.auto.JDFAutoConventionalPrintingParams.EWorkStyle;
import org.cip4.jdflib.auto.JDFAutoConventionalPrintingParams.EnumWorkStyle;
import org.cip4.jdflib.auto.JDFAutoInterpretingParams.EMirrorAround;
import org.cip4.jdflib.auto.JDFAutoInterpretingParams.EnumMirrorAround;
import org.cip4.jdflib.auto.JDFAutoMedia.EPolarity;
import org.cip4.jdflib.auto.JDFAutoMedia.EnumPolarity;
import org.cip4.jdflib.core.AtrInfoTable;
import org.cip4.jdflib.core.AttributeInfo;
import org.cip4.jdflib.core.AttributeName;
import org.cip4.jdflib.core.ElemInfoTable;
import org.cip4.jdflib.core.ElementInfo;
import org.cip4.jdflib.core.ElementName;
import org.cip4.jdflib.core.JDFCoreConstants;
import org.cip4.jdflib.datatypes.JDFTransferFunction;
import org.cip4.jdflib.datatypes.JDFXYPair;
import org.cip4.jdflib.resource.JDFFitPolicy;
import org.cip4.jdflib.resource.JDFResource;
import org.cip4.jdflib.resource.process.JDFMedia;
import org.cip4.jdflib.util.JavaEnumUtil;

/**
 ***************************************************************************** class JDFAutoImageSetterParams : public JDFResource
 */

public abstract class JDFAutoImageSetterParams extends JDFResource
{

	private static final long serialVersionUID = 1L;

	private static AtrInfoTable[] atrInfoTable = new AtrInfoTable[18];
	static
	{
		atrInfoTable[0] = new AtrInfoTable(AttributeName.MIRRORAROUND, 0x3333333333l, AttributeInfo.EnumAttributeType.enumeration, EnumMirrorAround.getEnum(0),
				"None");
		atrInfoTable[1] = new AtrInfoTable(AttributeName.POLARITY, 0x3333333333l, AttributeInfo.EnumAttributeType.enumeration, EnumPolarity.getEnum(0),
				"Positive");
		atrInfoTable[2] = new AtrInfoTable(AttributeName.SIDES, 0x3333333311l, AttributeInfo.EnumAttributeType.enumeration, EnumSides.getEnum(0),
				"OneSidedFront");
		atrInfoTable[3] = new AtrInfoTable(AttributeName.ADVANCEDISTANCE, 0x3333333333l, AttributeInfo.EnumAttributeType.double_, null, null);
		atrInfoTable[4] = new AtrInfoTable(AttributeName.BURNOUTAREA, 0x3333333331l, AttributeInfo.EnumAttributeType.XYPair, null, null);
		atrInfoTable[5] = new AtrInfoTable(AttributeName.CENTERACROSS, 0x3333333333l, AttributeInfo.EnumAttributeType.enumeration, EnumCenterAcross.getEnum(0),
				null);
		atrInfoTable[6] = new AtrInfoTable(AttributeName.CUTMEDIA, 0x3333333333l, AttributeInfo.EnumAttributeType.boolean_, null, null);
		atrInfoTable[7] = new AtrInfoTable(AttributeName.MANUALFEED, 0x3333333311l, AttributeInfo.EnumAttributeType.boolean_, null, null);
		atrInfoTable[8] = new AtrInfoTable(AttributeName.NONPRINTABLEMARGINBOTTOM, 0x3333333111l, AttributeInfo.EnumAttributeType.double_, null, null);
		atrInfoTable[9] = new AtrInfoTable(AttributeName.NONPRINTABLEMARGINLEFT, 0x3333333111l, AttributeInfo.EnumAttributeType.double_, null, null);
		atrInfoTable[10] = new AtrInfoTable(AttributeName.NONPRINTABLEMARGINRIGHT, 0x3333333111l, AttributeInfo.EnumAttributeType.double_, null, null);
		atrInfoTable[11] = new AtrInfoTable(AttributeName.NONPRINTABLEMARGINTOP, 0x3333333111l, AttributeInfo.EnumAttributeType.double_, null, null);
		atrInfoTable[12] = new AtrInfoTable(AttributeName.PUNCH, 0x4444444333l, AttributeInfo.EnumAttributeType.boolean_, null, null);
		atrInfoTable[13] = new AtrInfoTable(AttributeName.PUNCHTYPE, 0x4444444333l, AttributeInfo.EnumAttributeType.string, null, null);
		atrInfoTable[14] = new AtrInfoTable(AttributeName.RESOLUTION, 0x3333333333l, AttributeInfo.EnumAttributeType.XYPair, null, null);
		atrInfoTable[15] = new AtrInfoTable(AttributeName.ROLLCUT, 0x3333333333l, AttributeInfo.EnumAttributeType.double_, null, null);
		atrInfoTable[16] = new AtrInfoTable(AttributeName.SOURCEWORKSTYLE, 0x3333333311l, AttributeInfo.EnumAttributeType.enumeration, EnumWorkStyle.getEnum(0),
				null);
		atrInfoTable[17] = new AtrInfoTable(AttributeName.TRANSFERCURVE, 0x3333333333l, AttributeInfo.EnumAttributeType.TransferFunction, null, null);
	}

	@Override
	protected AttributeInfo getTheAttributeInfo()
	{
		return super.getTheAttributeInfo().updateReplace(atrInfoTable);
	}

	private static ElemInfoTable[] elemInfoTable = new ElemInfoTable[2];
	static
	{
		elemInfoTable[0] = new ElemInfoTable(ElementName.MEDIA, 0x6666666661l);
		elemInfoTable[1] = new ElemInfoTable(ElementName.FITPOLICY, 0x6666666611l);
	}

	@Override
	protected ElementInfo getTheElementInfo()
	{
		return super.getTheElementInfo().updateReplace(elemInfoTable);
	}

	/**
	 * Constructor for JDFAutoImageSetterParams
	 *
	 * @param myOwnerDocument
	 * @param qualifiedName
	 */
	protected JDFAutoImageSetterParams(CoreDocumentImpl myOwnerDocument, String qualifiedName)
	{
		super(myOwnerDocument, qualifiedName);
	}

	/**
	 * Constructor for JDFAutoImageSetterParams
	 *
	 * @param myOwnerDocument
	 * @param myNamespaceURI
	 * @param qualifiedName
	 */
	protected JDFAutoImageSetterParams(CoreDocumentImpl myOwnerDocument, String myNamespaceURI, String qualifiedName)
	{
		super(myOwnerDocument, myNamespaceURI, qualifiedName);
	}

	/**
	 * Constructor for JDFAutoImageSetterParams
	 *
	 * @param myOwnerDocument
	 * @param myNamespaceURI
	 * @param qualifiedName
	 * @param myLocalName
	 */
	protected JDFAutoImageSetterParams(CoreDocumentImpl myOwnerDocument, String myNamespaceURI, String qualifiedName, String myLocalName)
	{
		super(myOwnerDocument, myNamespaceURI, qualifiedName, myLocalName);
	}

	/**
	 * @return true if ok
	 */
	@Override
	public boolean init()
	{
		boolean bRet = super.init();
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
	 * Enumeration strings for CenterAcross
	 */

	public enum ECenterAcross
	{
		None, FeedDirection, MediaWidth, Both;

		public static ECenterAcross getEnum(String val)
		{
			return JavaEnumUtil.getEnumIgnoreCase(ECenterAcross.class, val, null);
		}
	}

	/**
	 * Enumeration strings for CenterAcross
	 */

	@SuppressWarnings("rawtypes")
	public static class EnumCenterAcross extends ValuedEnum
	{
		private static final long serialVersionUID = 1L;
		private static int m_startValue = 0;

		protected EnumCenterAcross(String name)
		{
			super(name, m_startValue++);
		}

		/**
		 * @param enumName the string to convert
		 * @return the enum
		 */
		public static EnumCenterAcross getEnum(String enumName)
		{
			return (EnumCenterAcross) getEnum(EnumCenterAcross.class, enumName);
		}

		/**
		 * @param enumValue the integer to convert
		 * @return the enum
		 */
		public static EnumCenterAcross getEnum(int enumValue)
		{
			return (EnumCenterAcross) getEnum(EnumCenterAcross.class, enumValue);
		}

		/**
		 * @return the map of enums
		 */
		public static Map getEnumMap()
		{
			return getEnumMap(EnumCenterAcross.class);
		}

		/**
		 * @return the list of enums
		 */
		public static List getEnumList()
		{
			return getEnumList(EnumCenterAcross.class);
		}

		/**
		 * @return the iterator
		 */
		public static Iterator iterator()
		{
			return iterator(EnumCenterAcross.class);
		}

		/**  */
		public static final EnumCenterAcross None = new EnumCenterAcross("None");
		/**  */
		public static final EnumCenterAcross FeedDirection = new EnumCenterAcross("FeedDirection");
		/**  */
		public static final EnumCenterAcross MediaWidth = new EnumCenterAcross("MediaWidth");
		/**  */
		public static final EnumCenterAcross Both = new EnumCenterAcross("Both");
	}

	/*
	 * ************************************************************************
	 * Attribute getter / setter
	 * ************************************************************************
	 */

	/*
	 * ---------------------------------------------------------------------
	 * Methods for Attribute MirrorAround
	 * ---------------------------------------------------------------------
	 */
	/**
	 * (5) set attribute MirrorAround
	 *
	 * @param enumVar the enumVar to set the attribute to
	 */
	public void setMirrorAround(EMirrorAround enumVar)
	{
		setAttribute(AttributeName.MIRRORAROUND, enumVar == null ? null : enumVar.name(), null);
	}

	/**
	 * (9) get attribute MirrorAround
	 *
	 * @return the value of the attribute
	 */
	public EMirrorAround getEMirrorAround()
	{
		return EMirrorAround.getEnum(getAttribute(AttributeName.MIRRORAROUND, null, "None"));
	}

	/*
	 * ---------------------------------------------------------------------
	 * Methods for Attribute MirrorAround
	 * ---------------------------------------------------------------------
	 */
	/**
	 * (5) set attribute MirrorAround
	 *
	 * @param enumVar the enumVar to set the attribute to
	 * @deprecated use SetMirrorAround(EMirrorAround) based on java.lang.enum instead
	 */
	@Deprecated
	public void setMirrorAround(EnumMirrorAround enumVar)
	{
		setAttribute(AttributeName.MIRRORAROUND, enumVar == null ? null : enumVar.getName(), null);
	}

	/**
	 * (9) get attribute MirrorAround
	 *
	 * @return the value of the attribute
	 * @deprecated use EMirrorAround GetEMirrorAround() based on java.lang.enum instead
	 */
	@Deprecated
	public EnumMirrorAround getMirrorAround()
	{
		return EnumMirrorAround.getEnum(getAttribute(AttributeName.MIRRORAROUND, null, "None"));
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
	public void setPolarity(EPolarity enumVar)
	{
		setAttribute(AttributeName.POLARITY, enumVar == null ? null : enumVar.name(), null);
	}

	/**
	 * (9) get attribute Polarity
	 *
	 * @return the value of the attribute
	 */
	public EPolarity getEPolarity()
	{
		return EPolarity.getEnum(getAttribute(AttributeName.POLARITY, null, "Positive"));
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
	 * @deprecated use SetPolarity(EPolarity) based on java.lang.enum instead
	 */
	@Deprecated
	public void setPolarity(EnumPolarity enumVar)
	{
		setAttribute(AttributeName.POLARITY, enumVar == null ? null : enumVar.getName(), null);
	}

	/**
	 * (9) get attribute Polarity
	 *
	 * @return the value of the attribute
	 * @deprecated use EPolarity GetEPolarity() based on java.lang.enum instead
	 */
	@Deprecated
	public EnumPolarity getPolarity()
	{
		return EnumPolarity.getEnum(getAttribute(AttributeName.POLARITY, null, "Positive"));
	}

	/*
	 * ---------------------------------------------------------------------
	 * Methods for Attribute Sides
	 * ---------------------------------------------------------------------
	 */
	/**
	 * (5) set attribute Sides
	 *
	 * @param enumVar the enumVar to set the attribute to
	 */
	public void setSides(ESides enumVar)
	{
		setAttribute(AttributeName.SIDES, enumVar == null ? null : enumVar.name(), null);
	}

	/**
	 * (9) get attribute Sides
	 *
	 * @return the value of the attribute
	 */
	public ESides getESides()
	{
		return ESides.getEnum(getAttribute(AttributeName.SIDES, null, "OneSidedFront"));
	}

	/*
	 * ---------------------------------------------------------------------
	 * Methods for Attribute Sides
	 * ---------------------------------------------------------------------
	 */
	/**
	 * (5) set attribute Sides
	 *
	 * @param enumVar the enumVar to set the attribute to
	 * @deprecated use SetSides(ESides) based on java.lang.enum instead
	 */
	@Deprecated
	public void setSides(EnumSides enumVar)
	{
		setAttribute(AttributeName.SIDES, enumVar == null ? null : enumVar.getName(), null);
	}

	/**
	 * (9) get attribute Sides
	 *
	 * @return the value of the attribute
	 * @deprecated use ESides GetESides() based on java.lang.enum instead
	 */
	@Deprecated
	public EnumSides getSides()
	{
		return EnumSides.getEnum(getAttribute(AttributeName.SIDES, null, "OneSidedFront"));
	}

	/*
	 * ---------------------------------------------------------------------
	 * Methods for Attribute AdvanceDistance
	 * ---------------------------------------------------------------------
	 */
	/**
	 * (36) set attribute AdvanceDistance
	 *
	 * @param value the value to set the attribute to
	 */
	public void setAdvanceDistance(double value)
	{
		setAttribute(AttributeName.ADVANCEDISTANCE, value, null);
	}

	/**
	 * (17) get double attribute AdvanceDistance
	 *
	 * @return double the value of the attribute
	 */
	public double getAdvanceDistance()
	{
		return getRealAttribute(AttributeName.ADVANCEDISTANCE, null, 0.0);
	}

	/*
	 * ---------------------------------------------------------------------
	 * Methods for Attribute BurnOutArea
	 * ---------------------------------------------------------------------
	 */
	/**
	 * (36) set attribute BurnOutArea
	 *
	 * @param value the value to set the attribute to
	 */
	public void setBurnOutArea(JDFXYPair value)
	{
		setAttribute(AttributeName.BURNOUTAREA, value, null);
	}

	/**
	 * (20) get JDFXYPair attribute BurnOutArea
	 *
	 * @return JDFXYPair the value of the attribute, null if a the
	 *         attribute value is not a valid to create a JDFXYPair
	 */
	public JDFXYPair getBurnOutArea()
	{
		String strAttrName = getAttribute(AttributeName.BURNOUTAREA, null, null);
		JDFXYPair nPlaceHolder = JDFXYPair.createXYPair(strAttrName);
		return nPlaceHolder;
	}

	/*
	 * ---------------------------------------------------------------------
	 * Methods for Attribute CenterAcross
	 * ---------------------------------------------------------------------
	 */
	/**
	 * (5) set attribute CenterAcross
	 *
	 * @param enumVar the enumVar to set the attribute to
	 */
	public void setCenterAcross(ECenterAcross enumVar)
	{
		setAttribute(AttributeName.CENTERACROSS, enumVar == null ? null : enumVar.name(), null);
	}

	/**
	 * (9) get attribute CenterAcross
	 *
	 * @return the value of the attribute
	 */
	public ECenterAcross getECenterAcross()
	{
		return ECenterAcross.getEnum(getAttribute(AttributeName.CENTERACROSS, null, null));
	}

	/*
	 * ---------------------------------------------------------------------
	 * Methods for Attribute CenterAcross
	 * ---------------------------------------------------------------------
	 */
	/**
	 * (5) set attribute CenterAcross
	 *
	 * @param enumVar the enumVar to set the attribute to
	 * @deprecated use SetCenterAcross(ECenterAcross) based on java.lang.enum instead
	 */
	@Deprecated
	public void setCenterAcross(EnumCenterAcross enumVar)
	{
		setAttribute(AttributeName.CENTERACROSS, enumVar == null ? null : enumVar.getName(), null);
	}

	/**
	 * (9) get attribute CenterAcross
	 *
	 * @return the value of the attribute
	 * @deprecated use ECenterAcross GetECenterAcross() based on java.lang.enum instead
	 */
	@Deprecated
	public EnumCenterAcross getCenterAcross()
	{
		return EnumCenterAcross.getEnum(getAttribute(AttributeName.CENTERACROSS, null, null));
	}

	/*
	 * ---------------------------------------------------------------------
	 * Methods for Attribute CutMedia
	 * ---------------------------------------------------------------------
	 */
	/**
	 * (36) set attribute CutMedia
	 *
	 * @param value the value to set the attribute to
	 */
	public void setCutMedia(boolean value)
	{
		setAttribute(AttributeName.CUTMEDIA, value, null);
	}

	/**
	 * (18) get boolean attribute CutMedia
	 *
	 * @return boolean the value of the attribute
	 */
	public boolean getCutMedia()
	{
		return getBoolAttribute(AttributeName.CUTMEDIA, null, false);
	}

	/*
	 * ---------------------------------------------------------------------
	 * Methods for Attribute ManualFeed
	 * ---------------------------------------------------------------------
	 */
	/**
	 * (36) set attribute ManualFeed
	 *
	 * @param value the value to set the attribute to
	 */
	public void setManualFeed(boolean value)
	{
		setAttribute(AttributeName.MANUALFEED, value, null);
	}

	/**
	 * (18) get boolean attribute ManualFeed
	 *
	 * @return boolean the value of the attribute
	 */
	public boolean getManualFeed()
	{
		return getBoolAttribute(AttributeName.MANUALFEED, null, false);
	}

	/*
	 * ---------------------------------------------------------------------
	 * Methods for Attribute NonPrintableMarginBottom
	 * ---------------------------------------------------------------------
	 */
	/**
	 * (36) set attribute NonPrintableMarginBottom
	 *
	 * @param value the value to set the attribute to
	 */
	public void setNonPrintableMarginBottom(double value)
	{
		setAttribute(AttributeName.NONPRINTABLEMARGINBOTTOM, value, null);
	}

	/**
	 * (17) get double attribute NonPrintableMarginBottom
	 *
	 * @return double the value of the attribute
	 */
	public double getNonPrintableMarginBottom()
	{
		return getRealAttribute(AttributeName.NONPRINTABLEMARGINBOTTOM, null, 0.0);
	}

	/*
	 * ---------------------------------------------------------------------
	 * Methods for Attribute NonPrintableMarginLeft
	 * ---------------------------------------------------------------------
	 */
	/**
	 * (36) set attribute NonPrintableMarginLeft
	 *
	 * @param value the value to set the attribute to
	 */
	public void setNonPrintableMarginLeft(double value)
	{
		setAttribute(AttributeName.NONPRINTABLEMARGINLEFT, value, null);
	}

	/**
	 * (17) get double attribute NonPrintableMarginLeft
	 *
	 * @return double the value of the attribute
	 */
	public double getNonPrintableMarginLeft()
	{
		return getRealAttribute(AttributeName.NONPRINTABLEMARGINLEFT, null, 0.0);
	}

	/*
	 * ---------------------------------------------------------------------
	 * Methods for Attribute NonPrintableMarginRight
	 * ---------------------------------------------------------------------
	 */
	/**
	 * (36) set attribute NonPrintableMarginRight
	 *
	 * @param value the value to set the attribute to
	 */
	public void setNonPrintableMarginRight(double value)
	{
		setAttribute(AttributeName.NONPRINTABLEMARGINRIGHT, value, null);
	}

	/**
	 * (17) get double attribute NonPrintableMarginRight
	 *
	 * @return double the value of the attribute
	 */
	public double getNonPrintableMarginRight()
	{
		return getRealAttribute(AttributeName.NONPRINTABLEMARGINRIGHT, null, 0.0);
	}

	/*
	 * ---------------------------------------------------------------------
	 * Methods for Attribute NonPrintableMarginTop
	 * ---------------------------------------------------------------------
	 */
	/**
	 * (36) set attribute NonPrintableMarginTop
	 *
	 * @param value the value to set the attribute to
	 */
	public void setNonPrintableMarginTop(double value)
	{
		setAttribute(AttributeName.NONPRINTABLEMARGINTOP, value, null);
	}

	/**
	 * (17) get double attribute NonPrintableMarginTop
	 *
	 * @return double the value of the attribute
	 */
	public double getNonPrintableMarginTop()
	{
		return getRealAttribute(AttributeName.NONPRINTABLEMARGINTOP, null, 0.0);
	}

	/*
	 * ---------------------------------------------------------------------
	 * Methods for Attribute Punch
	 * ---------------------------------------------------------------------
	 */
	/**
	 * (36) set attribute Punch
	 *
	 * @param value the value to set the attribute to
	 */
	public void setPunch(boolean value)
	{
		setAttribute(AttributeName.PUNCH, value, null);
	}

	/**
	 * (18) get boolean attribute Punch
	 *
	 * @return boolean the value of the attribute
	 */
	public boolean getPunch()
	{
		return getBoolAttribute(AttributeName.PUNCH, null, false);
	}

	/*
	 * ---------------------------------------------------------------------
	 * Methods for Attribute PunchType
	 * ---------------------------------------------------------------------
	 */
	/**
	 * (36) set attribute PunchType
	 *
	 * @param value the value to set the attribute to
	 */
	public void setPunchType(String value)
	{
		setAttribute(AttributeName.PUNCHTYPE, value, null);
	}

	/**
	 * (23) get String attribute PunchType
	 *
	 * @return the value of the attribute
	 */
	public String getPunchType()
	{
		return getAttribute(AttributeName.PUNCHTYPE, null, JDFCoreConstants.EMPTYSTRING);
	}

	/*
	 * ---------------------------------------------------------------------
	 * Methods for Attribute Resolution
	 * ---------------------------------------------------------------------
	 */
	/**
	 * (36) set attribute Resolution
	 *
	 * @param value the value to set the attribute to
	 */
	public void setResolution(JDFXYPair value)
	{
		setAttribute(AttributeName.RESOLUTION, value, null);
	}

	/**
	 * (20) get JDFXYPair attribute Resolution
	 *
	 * @return JDFXYPair the value of the attribute, null if a the
	 *         attribute value is not a valid to create a JDFXYPair
	 */
	public JDFXYPair getResolution()
	{
		String strAttrName = getAttribute(AttributeName.RESOLUTION, null, null);
		JDFXYPair nPlaceHolder = JDFXYPair.createXYPair(strAttrName);
		return nPlaceHolder;
	}

	/*
	 * ---------------------------------------------------------------------
	 * Methods for Attribute RollCut
	 * ---------------------------------------------------------------------
	 */
	/**
	 * (36) set attribute RollCut
	 *
	 * @param value the value to set the attribute to
	 */
	public void setRollCut(double value)
	{
		setAttribute(AttributeName.ROLLCUT, value, null);
	}

	/**
	 * (17) get double attribute RollCut
	 *
	 * @return double the value of the attribute
	 */
	public double getRollCut()
	{
		return getRealAttribute(AttributeName.ROLLCUT, null, 0.0);
	}

	/*
	 * ---------------------------------------------------------------------
	 * Methods for Attribute SourceWorkStyle
	 * ---------------------------------------------------------------------
	 */
	/**
	 * (5) set attribute SourceWorkStyle
	 *
	 * @param enumVar the enumVar to set the attribute to
	 */
	public void setSourceWorkStyle(EWorkStyle enumVar)
	{
		setAttribute(AttributeName.SOURCEWORKSTYLE, enumVar == null ? null : enumVar.name(), null);
	}

	/**
	 * (9) get attribute SourceWorkStyle
	 *
	 * @return the value of the attribute
	 */
	public EWorkStyle getESourceWorkStyle()
	{
		return EWorkStyle.getEnum(getAttribute(AttributeName.SOURCEWORKSTYLE, null, null));
	}

	/*
	 * ---------------------------------------------------------------------
	 * Methods for Attribute SourceWorkStyle
	 * ---------------------------------------------------------------------
	 */
	/**
	 * (5) set attribute SourceWorkStyle
	 *
	 * @param enumVar the enumVar to set the attribute to
	 * @deprecated use SetSourceWorkStyle(EWorkStyle) based on java.lang.enum instead
	 */
	@Deprecated
	public void setSourceWorkStyle(EnumWorkStyle enumVar)
	{
		setAttribute(AttributeName.SOURCEWORKSTYLE, enumVar == null ? null : enumVar.getName(), null);
	}

	/**
	 * (9) get attribute SourceWorkStyle
	 *
	 * @return the value of the attribute
	 * @deprecated use EWorkStyle GetESourceWorkStyle() based on java.lang.enum instead
	 */
	@Deprecated
	public EnumWorkStyle getSourceWorkStyle()
	{
		return EnumWorkStyle.getEnum(getAttribute(AttributeName.SOURCEWORKSTYLE, null, null));
	}

	/*
	 * ---------------------------------------------------------------------
	 * Methods for Attribute TransferCurve
	 * ---------------------------------------------------------------------
	 */
	/**
	 * (36) set attribute TransferCurve
	 *
	 * @param value the value to set the attribute to
	 */
	public void setTransferCurve(JDFTransferFunction value)
	{
		setAttribute(AttributeName.TRANSFERCURVE, value, null);
	}

	/**
	 * (20) get JDFTransferFunction attribute TransferCurve
	 *
	 * @return JDFTransferFunction the value of the attribute, null if a the
	 *         attribute value is not a valid to create a JDFTransferFunction
	 */
	public JDFTransferFunction getTransferCurve()
	{
		String strAttrName = getAttribute(AttributeName.TRANSFERCURVE, null, null);
		JDFTransferFunction nPlaceHolder = JDFTransferFunction.createTransferFunction(strAttrName);
		return nPlaceHolder;
	}

	/*
	 * ***********************************************************************
	 * Element getter / setter
	 * ***********************************************************************
	 */

	/**
	 * (24) const get element Media
	 *
	 * @return JDFMedia the element
	 */
	public JDFMedia getMedia()
	{
		return (JDFMedia) getElement(ElementName.MEDIA, null, 0);
	}

	/**
	 * (25) getCreateMedia
	 *
	 * @return JDFMedia the element
	 */
	public JDFMedia getCreateMedia()
	{
		return (JDFMedia) getCreateElement_JDFElement(ElementName.MEDIA, null, 0);
	}

	/**
	 * (29) append element Media
	 *
	 * @return JDFMedia the element
	 * @ if the element already exists
	 */
	public JDFMedia appendMedia()
	{
		return (JDFMedia) appendElementN(ElementName.MEDIA, 1, null);
	}

	/**
	 * (31) create inter-resource link to refTarget
	 *
	 * @param refTarget the element that is referenced
	 */
	public void refMedia(JDFMedia refTarget)
	{
		refElement(refTarget);
	}

	/**
	 * (24) const get element FitPolicy
	 *
	 * @return JDFFitPolicy the element
	 */
	public JDFFitPolicy getFitPolicy()
	{
		return (JDFFitPolicy) getElement(ElementName.FITPOLICY, null, 0);
	}

	/**
	 * (25) getCreateFitPolicy
	 *
	 * @return JDFFitPolicy the element
	 */
	public JDFFitPolicy getCreateFitPolicy()
	{
		return (JDFFitPolicy) getCreateElement_JDFElement(ElementName.FITPOLICY, null, 0);
	}

	/**
	 * (29) append element FitPolicy
	 *
	 * @return JDFFitPolicy the element
	 * @ if the element already exists
	 */
	public JDFFitPolicy appendFitPolicy()
	{
		return (JDFFitPolicy) appendElementN(ElementName.FITPOLICY, 1, null);
	}

}
