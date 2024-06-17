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
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.enums.ValuedEnum;
import org.apache.xerces.dom.CoreDocumentImpl;
import org.cip4.jdflib.core.AtrInfoTable;
import org.cip4.jdflib.core.AttributeInfo;
import org.cip4.jdflib.core.AttributeName;
import org.cip4.jdflib.core.ElemInfoTable;
import org.cip4.jdflib.core.ElementInfo;
import org.cip4.jdflib.core.ElementName;
import org.cip4.jdflib.core.JDFSeparationList;
import org.cip4.jdflib.datatypes.JDFRectangle;
import org.cip4.jdflib.resource.JDFDevice;
import org.cip4.jdflib.resource.JDFResource;
import org.cip4.jdflib.resource.process.JDFFileSpec;
import org.cip4.jdflib.resource.process.JDFMedia;
import org.cip4.jdflib.resource.process.JDFRuleLength;
import org.cip4.jdflib.resource.process.JDFStation;

/**
 *****************************************************************************
 * class JDFAutoDieLayout : public JDFResource
 *****************************************************************************
 * 
 */

public abstract class JDFAutoDieLayout extends JDFResource
{

	private static final long serialVersionUID = 1L;

	private static AtrInfoTable[] atrInfoTable = new AtrInfoTable[5];
	static
	{
		atrInfoTable[0] = new AtrInfoTable(AttributeName.CUTBOX, 0x3333311111l, AttributeInfo.EnumAttributeType.rectangle, null, null);
		atrInfoTable[1] = new AtrInfoTable(AttributeName.DIESIDE, 0x3333331111l, AttributeInfo.EnumAttributeType.enumeration, EnumDieSide.getEnum(0), null);
		atrInfoTable[2] = new AtrInfoTable(AttributeName.MEDIASIDE, 0x3333331111l, AttributeInfo.EnumAttributeType.enumeration, EnumMediaSide.getEnum(0), null);
		atrInfoTable[3] = new AtrInfoTable(AttributeName.ROTATED, 0x3333331111l, AttributeInfo.EnumAttributeType.boolean_, null, null);
		atrInfoTable[4] = new AtrInfoTable(AttributeName.WASTE, 0x3333331111l, AttributeInfo.EnumAttributeType.double_, null, null);
	}

	@Override
	protected AttributeInfo getTheAttributeInfo()
	{
		return super.getTheAttributeInfo().updateReplace(atrInfoTable);
	}

	private static ElemInfoTable[] elemInfoTable = new ElemInfoTable[6];
	static
	{
		elemInfoTable[0] = new ElemInfoTable(ElementName.DEVICE, 0x3333333111l);
		elemInfoTable[1] = new ElemInfoTable(ElementName.FILESPEC, 0x3333333111l);
		elemInfoTable[2] = new ElemInfoTable(ElementName.MEDIA, 0x6666666111l);
		elemInfoTable[3] = new ElemInfoTable(ElementName.RULELENGTH, 0x3333333111l);
		elemInfoTable[4] = new ElemInfoTable(ElementName.STATION, 0x3333333111l);
		elemInfoTable[5] = new ElemInfoTable(ElementName.CUTLINES, 0x6666666111l);
	}

	@Override
	protected ElementInfo getTheElementInfo()
	{
		return super.getTheElementInfo().updateReplace(elemInfoTable);
	}

	/**
	 * Constructor for JDFAutoDieLayout
	 *
	 * @param myOwnerDocument
	 * @param qualifiedName
	 */
	protected JDFAutoDieLayout(CoreDocumentImpl myOwnerDocument, String qualifiedName)
	{
		super(myOwnerDocument, qualifiedName);
	}

	/**
	 * Constructor for JDFAutoDieLayout
	 *
	 * @param myOwnerDocument
	 * @param myNamespaceURI
	 * @param qualifiedName
	 */
	protected JDFAutoDieLayout(CoreDocumentImpl myOwnerDocument, String myNamespaceURI, String qualifiedName)
	{
		super(myOwnerDocument, myNamespaceURI, qualifiedName);
	}

	/**
	 * Constructor for JDFAutoDieLayout
	 *
	 * @param myOwnerDocument
	 * @param myNamespaceURI
	 * @param qualifiedName
	 * @param myLocalName
	 */
	protected JDFAutoDieLayout(CoreDocumentImpl myOwnerDocument, String myNamespaceURI, String qualifiedName, String myLocalName)
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
	 * Enumeration strings for DieSide
	 */

	@SuppressWarnings("rawtypes")
	public static class EnumDieSide extends ValuedEnum
	{
		private static final long serialVersionUID = 1L;
		private static int m_startValue = 0;

		protected EnumDieSide(String name)
		{
			super(name, m_startValue++);
		}

		/**
		 * @param enumName the string to convert
		 * @return the enum
		 */
		public static EnumDieSide getEnum(String enumName)
		{
			return (EnumDieSide) getEnum(EnumDieSide.class, enumName);
		}

		/**
		 * @param enumValue the integer to convert
		 * @return the enum
		 */
		public static EnumDieSide getEnum(int enumValue)
		{
			return (EnumDieSide) getEnum(EnumDieSide.class, enumValue);
		}

		/**
		 * @return the map of enums
		 */
		public static Map getEnumMap()
		{
			return getEnumMap(EnumDieSide.class);
		}

		/**
		 * @return the list of enums
		 */
		public static List getEnumList()
		{
			return getEnumList(EnumDieSide.class);
		}

		/**
		 * @return the iterator
		 */
		public static Iterator iterator()
		{
			return iterator(EnumDieSide.class);
		}

		/**  */
		public static final EnumDieSide Up = new EnumDieSide("Up");
		/**  */
		public static final EnumDieSide Down = new EnumDieSide("Down");
	}

	/**
	 * Enumeration strings for MediaSide
	 */

	@SuppressWarnings("rawtypes")
	public static class EnumMediaSide extends ValuedEnum
	{
		private static final long serialVersionUID = 1L;
		private static int m_startValue = 0;

		protected EnumMediaSide(String name)
		{
			super(name, m_startValue++);
		}

		/**
		 * @param enumName the string to convert
		 * @return the enum
		 */
		public static EnumMediaSide getEnum(String enumName)
		{
			return (EnumMediaSide) getEnum(EnumMediaSide.class, enumName);
		}

		/**
		 * @param enumValue the integer to convert
		 * @return the enum
		 */
		public static EnumMediaSide getEnum(int enumValue)
		{
			return (EnumMediaSide) getEnum(EnumMediaSide.class, enumValue);
		}

		/**
		 * @return the map of enums
		 */
		public static Map getEnumMap()
		{
			return getEnumMap(EnumMediaSide.class);
		}

		/**
		 * @return the list of enums
		 */
		public static List getEnumList()
		{
			return getEnumList(EnumMediaSide.class);
		}

		/**
		 * @return the iterator
		 */
		public static Iterator iterator()
		{
			return iterator(EnumMediaSide.class);
		}

		/**  */
		public static final EnumMediaSide Front = new EnumMediaSide("Front");
		/**  */
		public static final EnumMediaSide Back = new EnumMediaSide("Back");
		/**  */
		public static final EnumMediaSide Both = new EnumMediaSide("Both");
	}

	/*
	 * ************************************************************************ Attribute getter / setter ************************************************************************
	 */

	/*
	 * --------------------------------------------------------------------- Methods for Attribute CutBox ---------------------------------------------------------------------
	 */
	/**
	 * (36) set attribute CutBox
	 *
	 * @param value the value to set the attribute to
	 */
	public void setCutBox(JDFRectangle value)
	{
		setAttribute(AttributeName.CUTBOX, value, null);
	}

	/**
	 * (20) get JDFRectangle attribute CutBox
	 *
	 * @return JDFRectangle the value of the attribute, null if a the attribute value is not a valid to create a JDFRectangle
	 */
	public JDFRectangle getCutBox()
	{
		final String strAttrName = getAttribute(AttributeName.CUTBOX, null, null);
		final JDFRectangle nPlaceHolder = JDFRectangle.createRectangle(strAttrName);
		return nPlaceHolder;
	}

	/*
	 * --------------------------------------------------------------------- Methods for Attribute DieSide ---------------------------------------------------------------------
	 */
	/**
	 * (5) set attribute DieSide
	 *
	 * @param enumVar the enumVar to set the attribute to
	 */
	public void setDieSide(EnumDieSide enumVar)
	{
		setAttribute(AttributeName.DIESIDE, enumVar == null ? null : enumVar.getName(), null);
	}

	/**
	 * (9) get attribute DieSide
	 *
	 * @return the value of the attribute
	 */
	public EnumDieSide getDieSide()
	{
		return EnumDieSide.getEnum(getAttribute(AttributeName.DIESIDE, null, null));
	}

	/*
	 * --------------------------------------------------------------------- Methods for Attribute MediaSide ---------------------------------------------------------------------
	 */
	/**
	 * (5) set attribute MediaSide
	 *
	 * @param enumVar the enumVar to set the attribute to
	 */
	public void setMediaSide(EnumMediaSide enumVar)
	{
		setAttribute(AttributeName.MEDIASIDE, enumVar == null ? null : enumVar.getName(), null);
	}

	/**
	 * (9) get attribute MediaSide
	 *
	 * @return the value of the attribute
	 */
	public EnumMediaSide getMediaSide()
	{
		return EnumMediaSide.getEnum(getAttribute(AttributeName.MEDIASIDE, null, null));
	}

	/*
	 * --------------------------------------------------------------------- Methods for Attribute Rotated ---------------------------------------------------------------------
	 */
	/**
	 * (36) set attribute Rotated
	 *
	 * @param value the value to set the attribute to
	 */
	public void setRotated(boolean value)
	{
		setAttribute(AttributeName.ROTATED, value, null);
	}

	/**
	 * (18) get boolean attribute Rotated
	 *
	 * @return boolean the value of the attribute
	 */
	public boolean getRotated()
	{
		return getBoolAttribute(AttributeName.ROTATED, null, false);
	}

	/*
	 * --------------------------------------------------------------------- Methods for Attribute Waste ---------------------------------------------------------------------
	 */
	/**
	 * (36) set attribute Waste
	 *
	 * @param value the value to set the attribute to
	 */
	public void setWaste(double value)
	{
		setAttribute(AttributeName.WASTE, value, null);
	}

	/**
	 * (17) get double attribute Waste
	 *
	 * @return double the value of the attribute
	 */
	public double getWaste()
	{
		return getRealAttribute(AttributeName.WASTE, null, 0.0);
	}

	/*
	 * *********************************************************************** Element getter / setter ***********************************************************************
	 */

	/**
	 * (26) getCreateDevice
	 * 
	 * @param iSkip number of elements to skip
	 * @return JDFDevice the element
	 */
	public JDFDevice getCreateDevice(int iSkip)
	{
		return (JDFDevice) getCreateElement_JDFElement(ElementName.DEVICE, null, iSkip);
	}

	/**
	 * (27) const get element Device
	 *
	 * @param iSkip number of elements to skip
	 * @return JDFDevice the element default is getDevice(0)
	 */
	public JDFDevice getDevice(int iSkip)
	{
		return (JDFDevice) getElement(ElementName.DEVICE, null, iSkip);
	}

	/**
	 * Get all Device from the current element
	 * 
	 * @return Collection<JDFDevice>, null if none are available
	 */
	public Collection<JDFDevice> getAllDevice()
	{
		return getChildArrayByClass(JDFDevice.class, false, 0);
	}

	/**
	 * (30) append element Device
	 *
	 * @return JDFDevice the element
	 */
	public JDFDevice appendDevice()
	{
		return (JDFDevice) appendElement(ElementName.DEVICE, null);
	}

	/**
	 * (31) create inter-resource link to refTarget
	 *
	 * @param refTarget the element that is referenced
	 */
	public void refDevice(JDFDevice refTarget)
	{
		refElement(refTarget);
	}

	/**
	 * (26) getCreateFileSpec
	 * 
	 * @param iSkip number of elements to skip
	 * @return JDFFileSpec the element
	 */
	public JDFFileSpec getCreateFileSpec(int iSkip)
	{
		return (JDFFileSpec) getCreateElement_JDFElement(ElementName.FILESPEC, null, iSkip);
	}

	/**
	 * (27) const get element FileSpec
	 *
	 * @param iSkip number of elements to skip
	 * @return JDFFileSpec the element default is getFileSpec(0)
	 */
	public JDFFileSpec getFileSpec(int iSkip)
	{
		return (JDFFileSpec) getElement(ElementName.FILESPEC, null, iSkip);
	}

	/**
	 * Get all FileSpec from the current element
	 * 
	 * @return Collection<JDFFileSpec>, null if none are available
	 */
	public Collection<JDFFileSpec> getAllFileSpec()
	{
		return getChildArrayByClass(JDFFileSpec.class, false, 0);
	}

	/**
	 * (30) append element FileSpec
	 *
	 * @return JDFFileSpec the element
	 */
	public JDFFileSpec appendFileSpec()
	{
		return (JDFFileSpec) appendElement(ElementName.FILESPEC, null);
	}

	/**
	 * (31) create inter-resource link to refTarget
	 *
	 * @param refTarget the element that is referenced
	 */
	public void refFileSpec(JDFFileSpec refTarget)
	{
		refElement(refTarget);
	}

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
	 * @return JDFMedia the element @ if the element already exists
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
	 * (26) getCreateRuleLength
	 * 
	 * @param iSkip number of elements to skip
	 * @return JDFRuleLength the element
	 */
	public JDFRuleLength getCreateRuleLength(int iSkip)
	{
		return (JDFRuleLength) getCreateElement_JDFElement(ElementName.RULELENGTH, null, iSkip);
	}

	/**
	 * (27) const get element RuleLength
	 *
	 * @param iSkip number of elements to skip
	 * @return JDFRuleLength the element default is getRuleLength(0)
	 */
	public JDFRuleLength getRuleLength(int iSkip)
	{
		return (JDFRuleLength) getElement(ElementName.RULELENGTH, null, iSkip);
	}

	/**
	 * Get all RuleLength from the current element
	 * 
	 * @return Collection<JDFRuleLength>, null if none are available
	 */
	public Collection<JDFRuleLength> getAllRuleLength()
	{
		return getChildArrayByClass(JDFRuleLength.class, false, 0);
	}

	/**
	 * (30) append element RuleLength
	 *
	 * @return JDFRuleLength the element
	 */
	public JDFRuleLength appendRuleLength()
	{
		return (JDFRuleLength) appendElement(ElementName.RULELENGTH, null);
	}

	/**
	 * (26) getCreateStation
	 * 
	 * @param iSkip number of elements to skip
	 * @return JDFStation the element
	 */
	public JDFStation getCreateStation(int iSkip)
	{
		return (JDFStation) getCreateElement_JDFElement(ElementName.STATION, null, iSkip);
	}

	/**
	 * (27) const get element Station
	 *
	 * @param iSkip number of elements to skip
	 * @return JDFStation the element default is getStation(0)
	 */
	public JDFStation getStation(int iSkip)
	{
		return (JDFStation) getElement(ElementName.STATION, null, iSkip);
	}

	/**
	 * Get all Station from the current element
	 * 
	 * @return Collection<JDFStation>, null if none are available
	 */
	public Collection<JDFStation> getAllStation()
	{
		return getChildArrayByClass(JDFStation.class, false, 0);
	}

	/**
	 * (30) append element Station
	 *
	 * @return JDFStation the element
	 */
	public JDFStation appendStation()
	{
		return (JDFStation) appendElement(ElementName.STATION, null);
	}

	/**
	 * (24) const get element CutLines
	 *
	 * @return JDFSeparationList the element
	 */
	public JDFSeparationList getCutLines()
	{
		return (JDFSeparationList) getElement(ElementName.CUTLINES, null, 0);
	}

	/**
	 * (25) getCreateCutLines
	 * 
	 * @return JDFSeparationList the element
	 */
	public JDFSeparationList getCreateCutLines()
	{
		return (JDFSeparationList) getCreateElement_JDFElement(ElementName.CUTLINES, null, 0);
	}

	/**
	 * (29) append element CutLines
	 *
	 * @return JDFSeparationList the element @ if the element already exists
	 */
	public JDFSeparationList appendCutLines()
	{
		return (JDFSeparationList) appendElementN(ElementName.CUTLINES, 1, null);
	}

}
