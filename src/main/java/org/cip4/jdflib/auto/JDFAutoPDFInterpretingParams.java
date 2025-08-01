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
import org.cip4.jdflib.core.JDFCoreConstants;
import org.cip4.jdflib.core.JDFElement;
import org.cip4.jdflib.resource.process.JDFOCGControl;
import org.cip4.jdflib.resource.process.JDFReferenceXObjParams;
import org.cip4.jdflib.util.JavaEnumUtil;

/**
 *****************************************************************************
 * class JDFAutoPDFInterpretingParams : public JDFElement
 *****************************************************************************
 * 
 */

public abstract class JDFAutoPDFInterpretingParams extends JDFElement
{

	private static final long serialVersionUID = 1L;

	private static AtrInfoTable[] atrInfoTable = new AtrInfoTable[13];
	static
	{
		atrInfoTable[0] = new AtrInfoTable(AttributeName.EMITPDFBG, 0x3333333331l, AttributeInfo.EnumAttributeType.boolean_, null, "true");
		atrInfoTable[1] = new AtrInfoTable(AttributeName.EMITPDFHALFTONES, 0x3333333331l, AttributeInfo.EnumAttributeType.boolean_, null, "true");
		atrInfoTable[2] = new AtrInfoTable(AttributeName.EMITPDFTRANSFERS, 0x3333333331l, AttributeInfo.EnumAttributeType.boolean_, null, "true");
		atrInfoTable[3] = new AtrInfoTable(AttributeName.EMITPDFUCR, 0x3333333331l, AttributeInfo.EnumAttributeType.boolean_, null, "true");
		atrInfoTable[4] = new AtrInfoTable(AttributeName.HONORPDFOVERPRINT, 0x3333333331l, AttributeInfo.EnumAttributeType.boolean_, null, "true");
		atrInfoTable[5] = new AtrInfoTable(AttributeName.ICCCOLORASDEVICECOLOR, 0x3333333331l, AttributeInfo.EnumAttributeType.boolean_, null, "false");
		atrInfoTable[6] = new AtrInfoTable(AttributeName.OCGDEFAULT, 0x3333333111l, AttributeInfo.EnumAttributeType.enumeration, EnumOCGDefault.getEnum(0), "FromPDF");
		atrInfoTable[7] = new AtrInfoTable(AttributeName.OCGINTENT, 0x3333333111l, AttributeInfo.EnumAttributeType.NMTOKEN, null, null);
		atrInfoTable[8] = new AtrInfoTable(AttributeName.OCGPROCESS, 0x3333333111l, AttributeInfo.EnumAttributeType.NMTOKEN, null, null);
		atrInfoTable[9] = new AtrInfoTable(AttributeName.OCGZOOM, 0x3333333111l, AttributeInfo.EnumAttributeType.double_, null, "1.0");
		atrInfoTable[10] = new AtrInfoTable(AttributeName.PRINTPDFANNOTATIONS, 0x3333333331l, AttributeInfo.EnumAttributeType.boolean_, null, "false");
		atrInfoTable[11] = new AtrInfoTable(AttributeName.PRINTTRAPANNOTATIONS, 0x3333333111l, AttributeInfo.EnumAttributeType.boolean_, null, "false");
		atrInfoTable[12] = new AtrInfoTable(AttributeName.TRANSPARENCYRENDERINGQUALITY, 0x3333333331l, AttributeInfo.EnumAttributeType.double_, null, null);
	}

	@Override
	protected AttributeInfo getTheAttributeInfo()
	{
		return super.getTheAttributeInfo().updateReplace(atrInfoTable);
	}

	private static ElemInfoTable[] elemInfoTable = new ElemInfoTable[2];
	static
	{
		elemInfoTable[0] = new ElemInfoTable(ElementName.OCGCONTROL, 0x3333333331l);
		elemInfoTable[1] = new ElemInfoTable(ElementName.REFERENCEXOBJPARAMS, 0x6666666661l);
	}

	@Override
	protected ElementInfo getTheElementInfo()
	{
		return super.getTheElementInfo().updateReplace(elemInfoTable);
	}

	/**
	 * Constructor for JDFAutoPDFInterpretingParams
	 *
	 * @param myOwnerDocument
	 * @param qualifiedName
	 */
	protected JDFAutoPDFInterpretingParams(CoreDocumentImpl myOwnerDocument, String qualifiedName)
	{
		super(myOwnerDocument, qualifiedName);
	}

	/**
	 * Constructor for JDFAutoPDFInterpretingParams
	 *
	 * @param myOwnerDocument
	 * @param myNamespaceURI
	 * @param qualifiedName
	 */
	protected JDFAutoPDFInterpretingParams(CoreDocumentImpl myOwnerDocument, String myNamespaceURI, String qualifiedName)
	{
		super(myOwnerDocument, myNamespaceURI, qualifiedName);
	}

	/**
	 * Constructor for JDFAutoPDFInterpretingParams
	 *
	 * @param myOwnerDocument
	 * @param myNamespaceURI
	 * @param qualifiedName
	 * @param myLocalName
	 */
	protected JDFAutoPDFInterpretingParams(CoreDocumentImpl myOwnerDocument, String myNamespaceURI, String qualifiedName, String myLocalName)
	{
		super(myOwnerDocument, myNamespaceURI, qualifiedName, myLocalName);
	}

	/**
	 * Enumeration strings for OCGDefault
	 */

	public enum EOCGDefault
	{
		Exclude, FromPDF, Include;

		public static EOCGDefault getEnum(String val)
		{
			return JavaEnumUtil.getEnumIgnoreCase(EOCGDefault.class, val, EOCGDefault.FromPDF);
		}
	}

	/**
	 * Enumeration strings for OCGDefault
	 */

	@SuppressWarnings("rawtypes")
	public static class EnumOCGDefault extends ValuedEnum
	{
		private static final long serialVersionUID = 1L;
		private static int m_startValue = 0;

		protected EnumOCGDefault(String name)
		{
			super(name, m_startValue++);
		}

		/**
		 * @param enumName the string to convert
		 * @return the enum
		 */
		public static EnumOCGDefault getEnum(String enumName)
		{
			return (EnumOCGDefault) getEnum(EnumOCGDefault.class, enumName);
		}

		/**
		 * @param enumValue the integer to convert
		 * @return the enum
		 */
		public static EnumOCGDefault getEnum(int enumValue)
		{
			return (EnumOCGDefault) getEnum(EnumOCGDefault.class, enumValue);
		}

		/**
		 * @return the map of enums
		 */
		public static Map getEnumMap()
		{
			return getEnumMap(EnumOCGDefault.class);
		}

		/**
		 * @return the list of enums
		 */
		public static List getEnumList()
		{
			return getEnumList(EnumOCGDefault.class);
		}

		/**
		 * @return the iterator
		 */
		public static Iterator iterator()
		{
			return iterator(EnumOCGDefault.class);
		}

		/**  */
		public static final EnumOCGDefault Exclude = new EnumOCGDefault("Exclude");
		/**  */
		public static final EnumOCGDefault FromPDF = new EnumOCGDefault("FromPDF");
		/**  */
		public static final EnumOCGDefault Include = new EnumOCGDefault("Include");
	}

	/*
	 * ************************************************************************ Attribute getter / setter ************************************************************************
	 */

	/*
	 * --------------------------------------------------------------------- Methods for Attribute EmitPDFBG ---------------------------------------------------------------------
	 */
	/**
	 * (36) set attribute EmitPDFBG
	 *
	 * @param value the value to set the attribute to
	 */
	public void setEmitPDFBG(boolean value)
	{
		setAttribute(AttributeName.EMITPDFBG, value, null);
	}

	/**
	 * (18) get boolean attribute EmitPDFBG
	 *
	 * @return boolean the value of the attribute
	 */
	public boolean getEmitPDFBG()
	{
		return getBoolAttribute(AttributeName.EMITPDFBG, null, true);
	}

	/*
	 * --------------------------------------------------------------------- Methods for Attribute EmitPDFHalftones
	 * ---------------------------------------------------------------------
	 */
	/**
	 * (36) set attribute EmitPDFHalftones
	 *
	 * @param value the value to set the attribute to
	 */
	public void setEmitPDFHalftones(boolean value)
	{
		setAttribute(AttributeName.EMITPDFHALFTONES, value, null);
	}

	/**
	 * (18) get boolean attribute EmitPDFHalftones
	 *
	 * @return boolean the value of the attribute
	 */
	public boolean getEmitPDFHalftones()
	{
		return getBoolAttribute(AttributeName.EMITPDFHALFTONES, null, true);
	}

	/*
	 * --------------------------------------------------------------------- Methods for Attribute EmitPDFTransfers
	 * ---------------------------------------------------------------------
	 */
	/**
	 * (36) set attribute EmitPDFTransfers
	 *
	 * @param value the value to set the attribute to
	 */
	public void setEmitPDFTransfers(boolean value)
	{
		setAttribute(AttributeName.EMITPDFTRANSFERS, value, null);
	}

	/**
	 * (18) get boolean attribute EmitPDFTransfers
	 *
	 * @return boolean the value of the attribute
	 */
	public boolean getEmitPDFTransfers()
	{
		return getBoolAttribute(AttributeName.EMITPDFTRANSFERS, null, true);
	}

	/*
	 * --------------------------------------------------------------------- Methods for Attribute EmitPDFUCR ---------------------------------------------------------------------
	 */
	/**
	 * (36) set attribute EmitPDFUCR
	 *
	 * @param value the value to set the attribute to
	 */
	public void setEmitPDFUCR(boolean value)
	{
		setAttribute(AttributeName.EMITPDFUCR, value, null);
	}

	/**
	 * (18) get boolean attribute EmitPDFUCR
	 *
	 * @return boolean the value of the attribute
	 */
	public boolean getEmitPDFUCR()
	{
		return getBoolAttribute(AttributeName.EMITPDFUCR, null, true);
	}

	/*
	 * --------------------------------------------------------------------- Methods for Attribute HonorPDFOverprint
	 * ---------------------------------------------------------------------
	 */
	/**
	 * (36) set attribute HonorPDFOverprint
	 *
	 * @param value the value to set the attribute to
	 */
	public void setHonorPDFOverprint(boolean value)
	{
		setAttribute(AttributeName.HONORPDFOVERPRINT, value, null);
	}

	/**
	 * (18) get boolean attribute HonorPDFOverprint
	 *
	 * @return boolean the value of the attribute
	 */
	public boolean getHonorPDFOverprint()
	{
		return getBoolAttribute(AttributeName.HONORPDFOVERPRINT, null, true);
	}

	/*
	 * --------------------------------------------------------------------- Methods for Attribute ICCColorAsDeviceColor
	 * ---------------------------------------------------------------------
	 */
	/**
	 * (36) set attribute ICCColorAsDeviceColor
	 *
	 * @param value the value to set the attribute to
	 */
	public void setICCColorAsDeviceColor(boolean value)
	{
		setAttribute(AttributeName.ICCCOLORASDEVICECOLOR, value, null);
	}

	/**
	 * (18) get boolean attribute ICCColorAsDeviceColor
	 *
	 * @return boolean the value of the attribute
	 */
	public boolean getICCColorAsDeviceColor()
	{
		return getBoolAttribute(AttributeName.ICCCOLORASDEVICECOLOR, null, false);
	}

	/*
	 * --------------------------------------------------------------------- Methods for Attribute OCGDefault ---------------------------------------------------------------------
	 */
	/**
	 * (5) set attribute OCGDefault
	 *
	 * @param enumVar the enumVar to set the attribute to
	 */
	public void setOCGDefault(EOCGDefault enumVar)
	{
		setAttribute(AttributeName.OCGDEFAULT, enumVar == null ? null : enumVar.name(), null);
	}

	/**
	 * (9) get attribute OCGDefault
	 *
	 * @return the value of the attribute
	 */
	public EOCGDefault getEOCGDefault()
	{
		return EOCGDefault.getEnum(getAttribute(AttributeName.OCGDEFAULT, null, "FromPDF"));
	}

	/*
	 * --------------------------------------------------------------------- Methods for Attribute OCGDefault ---------------------------------------------------------------------
	 */
	/**
	 * (5) set attribute OCGDefault
	 *
	 * @param enumVar the enumVar to set the attribute to
	 * @deprecated use java.lang.enum
	 */
	@Deprecated
	public void setOCGDefault(EnumOCGDefault enumVar)
	{
		setAttribute(AttributeName.OCGDEFAULT, enumVar == null ? null : enumVar.getName(), null);
	}

	/**
	 * (9) get attribute OCGDefault
	 *
	 * @return the value of the attribute
	 */
	public EnumOCGDefault getOCGDefault()
	{
		return EnumOCGDefault.getEnum(getAttribute(AttributeName.OCGDEFAULT, null, "FromPDF"));
	}

	/*
	 * --------------------------------------------------------------------- Methods for Attribute OCGIntent ---------------------------------------------------------------------
	 */
	/**
	 * (36) set attribute OCGIntent
	 *
	 * @param value the value to set the attribute to
	 */
	public void setOCGIntent(String value)
	{
		setAttribute(AttributeName.OCGINTENT, value, null);
	}

	/**
	 * (23) get String attribute OCGIntent
	 *
	 * @return the value of the attribute
	 */
	public String getOCGIntent()
	{
		return getAttribute(AttributeName.OCGINTENT, null, JDFCoreConstants.EMPTYSTRING);
	}

	/*
	 * --------------------------------------------------------------------- Methods for Attribute OCGProcess ---------------------------------------------------------------------
	 */
	/**
	 * (36) set attribute OCGProcess
	 *
	 * @param value the value to set the attribute to
	 */
	public void setOCGProcess(String value)
	{
		setAttribute(AttributeName.OCGPROCESS, value, null);
	}

	/**
	 * (23) get String attribute OCGProcess
	 *
	 * @return the value of the attribute
	 */
	public String getOCGProcess()
	{
		return getAttribute(AttributeName.OCGPROCESS, null, JDFCoreConstants.EMPTYSTRING);
	}

	/*
	 * --------------------------------------------------------------------- Methods for Attribute OCGZoom ---------------------------------------------------------------------
	 */
	/**
	 * (36) set attribute OCGZoom
	 *
	 * @param value the value to set the attribute to
	 */
	public void setOCGZoom(double value)
	{
		setAttribute(AttributeName.OCGZOOM, value, null);
	}

	/**
	 * (17) get double attribute OCGZoom
	 *
	 * @return double the value of the attribute
	 */
	public double getOCGZoom()
	{
		return getRealAttribute(AttributeName.OCGZOOM, null, 0.0);
	}

	/*
	 * --------------------------------------------------------------------- Methods for Attribute PrintPDFAnnotations
	 * ---------------------------------------------------------------------
	 */
	/**
	 * (36) set attribute PrintPDFAnnotations
	 *
	 * @param value the value to set the attribute to
	 */
	public void setPrintPDFAnnotations(boolean value)
	{
		setAttribute(AttributeName.PRINTPDFANNOTATIONS, value, null);
	}

	/**
	 * (18) get boolean attribute PrintPDFAnnotations
	 *
	 * @return boolean the value of the attribute
	 */
	public boolean getPrintPDFAnnotations()
	{
		return getBoolAttribute(AttributeName.PRINTPDFANNOTATIONS, null, false);
	}

	/*
	 * --------------------------------------------------------------------- Methods for Attribute PrintTrapAnnotations
	 * ---------------------------------------------------------------------
	 */
	/**
	 * (36) set attribute PrintTrapAnnotations
	 *
	 * @param value the value to set the attribute to
	 */
	public void setPrintTrapAnnotations(boolean value)
	{
		setAttribute(AttributeName.PRINTTRAPANNOTATIONS, value, null);
	}

	/**
	 * (18) get boolean attribute PrintTrapAnnotations
	 *
	 * @return boolean the value of the attribute
	 */
	public boolean getPrintTrapAnnotations()
	{
		return getBoolAttribute(AttributeName.PRINTTRAPANNOTATIONS, null, false);
	}

	/*
	 * --------------------------------------------------------------------- Methods for Attribute TransparencyRenderingQuality
	 * ---------------------------------------------------------------------
	 */
	/**
	 * (36) set attribute TransparencyRenderingQuality
	 *
	 * @param value the value to set the attribute to
	 */
	public void setTransparencyRenderingQuality(double value)
	{
		setAttribute(AttributeName.TRANSPARENCYRENDERINGQUALITY, value, null);
	}

	/**
	 * (17) get double attribute TransparencyRenderingQuality
	 *
	 * @return double the value of the attribute
	 */
	public double getTransparencyRenderingQuality()
	{
		return getRealAttribute(AttributeName.TRANSPARENCYRENDERINGQUALITY, null, 0.0);
	}

	/*
	 * *********************************************************************** Element getter / setter ***********************************************************************
	 */

	/**
	 * (24) const get element OCGControl
	 *
	 * @return JDFOCGControl the element
	 */
	public JDFOCGControl getOCGControl()
	{
		return (JDFOCGControl) getElement(ElementName.OCGCONTROL, null, 0);
	}

	/**
	 * (25) getCreateOCGControl
	 * 
	 * @return JDFOCGControl the element
	 */
	public JDFOCGControl getCreateOCGControl()
	{
		return (JDFOCGControl) getCreateElement_JDFElement(ElementName.OCGCONTROL, null, 0);
	}

	/**
	 * (26) getCreateOCGControl
	 * 
	 * @param iSkip number of elements to skip
	 * @return JDFOCGControl the element
	 */
	public JDFOCGControl getCreateOCGControl(int iSkip)
	{
		return (JDFOCGControl) getCreateElement_JDFElement(ElementName.OCGCONTROL, null, iSkip);
	}

	/**
	 * (27) const get element OCGControl
	 *
	 * @param iSkip number of elements to skip
	 * @return JDFOCGControl the element default is getOCGControl(0)
	 */
	public JDFOCGControl getOCGControl(int iSkip)
	{
		return (JDFOCGControl) getElement(ElementName.OCGCONTROL, null, iSkip);
	}

	/**
	 * Get all OCGControl from the current element
	 * 
	 * @return Collection<JDFOCGControl>, null if none are available
	 */
	public Collection<JDFOCGControl> getAllOCGControl()
	{
		return getChildArrayByClass(JDFOCGControl.class, false, 0);
	}

	/**
	 * (30) append element OCGControl
	 *
	 * @return JDFOCGControl the element
	 */
	public JDFOCGControl appendOCGControl()
	{
		return (JDFOCGControl) appendElement(ElementName.OCGCONTROL, null);
	}

	/**
	 * (24) const get element ReferenceXObjParams
	 *
	 * @return JDFReferenceXObjParams the element
	 */
	public JDFReferenceXObjParams getReferenceXObjParams()
	{
		return (JDFReferenceXObjParams) getElement(ElementName.REFERENCEXOBJPARAMS, null, 0);
	}

	/**
	 * (25) getCreateReferenceXObjParams
	 * 
	 * @return JDFReferenceXObjParams the element
	 */
	public JDFReferenceXObjParams getCreateReferenceXObjParams()
	{
		return (JDFReferenceXObjParams) getCreateElement_JDFElement(ElementName.REFERENCEXOBJPARAMS, null, 0);
	}

	/**
	 * (29) append element ReferenceXObjParams
	 *
	 * @return JDFReferenceXObjParams the element @ if the element already exists
	 */
	public JDFReferenceXObjParams appendReferenceXObjParams()
	{
		return (JDFReferenceXObjParams) appendElementN(ElementName.REFERENCEXOBJPARAMS, 1, null);
	}

}
