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
import org.cip4.jdflib.core.VString;
import org.cip4.jdflib.datatypes.JDFMatrix;
import org.cip4.jdflib.datatypes.JDFRectangle;
import org.cip4.jdflib.datatypes.JDFXYPair;
import org.cip4.jdflib.resource.JDFDeviceMark;
import org.cip4.jdflib.resource.JDFJobField;
import org.cip4.jdflib.resource.JDFMarkActivation;
import org.cip4.jdflib.resource.JDFRefAnchor;
import org.cip4.jdflib.resource.JDFScavengerArea;
import org.cip4.jdflib.resource.process.JDFCIELABMeasuringField;
import org.cip4.jdflib.resource.process.JDFColorControlStrip;
import org.cip4.jdflib.resource.process.JDFDensityMeasuringField;
import org.cip4.jdflib.resource.process.JDFDynamicField;
import org.cip4.jdflib.resource.process.JDFFillMark;
import org.cip4.jdflib.resource.process.JDFIdentificationField;
import org.cip4.jdflib.resource.process.JDFLayoutElement;
import org.cip4.jdflib.resource.process.JDFRegisterMark;
import org.cip4.jdflib.resource.process.postpress.JDFCutMark;
import org.cip4.jdflib.util.JavaEnumUtil;

/**
 *****************************************************************************
 * class JDFAutoMarkObject : public JDFElement
 *****************************************************************************
 * 
 */

public abstract class JDFAutoMarkObject extends JDFElement
{

	private static final long serialVersionUID = 1L;

	private static AtrInfoTable[] atrInfoTable = new AtrInfoTable[21];
	static
	{
		atrInfoTable[0] = new AtrInfoTable(AttributeName.ORD, 0x3333333333l, AttributeInfo.EnumAttributeType.integer, null, null);
		atrInfoTable[1] = new AtrInfoTable(AttributeName.SOURCECLIPPATH, 0x3333333333l, AttributeInfo.EnumAttributeType.PDFPath, null, null);
		atrInfoTable[2] = new AtrInfoTable(AttributeName.TRIMCLIPPATH, 0x3333331111l, AttributeInfo.EnumAttributeType.PDFPath, null, null);
		atrInfoTable[3] = new AtrInfoTable(AttributeName.LOGICALSTACKORD, 0x3333331111l, AttributeInfo.EnumAttributeType.integer, null, null);
		atrInfoTable[4] = new AtrInfoTable(AttributeName.LAYOUTELEMENTPAGENUM, 0x4444443331l, AttributeInfo.EnumAttributeType.integer, null, "0");
		atrInfoTable[5] = new AtrInfoTable(AttributeName.CONTENTREF, 0x3333331111l, AttributeInfo.EnumAttributeType.IDREF, null, null);
		atrInfoTable[6] = new AtrInfoTable(AttributeName.CLIPBOXFORMAT, 0x3333331111l, AttributeInfo.EnumAttributeType.string, null, null);
		atrInfoTable[7] = new AtrInfoTable(AttributeName.ANCHOR, 0x3333331111l, AttributeInfo.EnumAttributeType.enumeration, EnumAnchor.getEnum(0), null);
		atrInfoTable[8] = new AtrInfoTable(AttributeName.ORDID, 0x3333333331l, AttributeInfo.EnumAttributeType.integer, null, null);
		atrInfoTable[9] = new AtrInfoTable(AttributeName.TYPE, 0x4444444443l, AttributeInfo.EnumAttributeType.enumeration, EnumType.getEnum(0), null);
		atrInfoTable[10] = new AtrInfoTable(AttributeName.CLIPBOX, 0x3333333333l, AttributeInfo.EnumAttributeType.rectangle, null, null);
		atrInfoTable[11] = new AtrInfoTable(AttributeName.COMPENSATIONCTMTEMPLATE, 0x3333331111l, AttributeInfo.EnumAttributeType.string, null, null);
		atrInfoTable[12] = new AtrInfoTable(AttributeName.LAYERID, 0x3333333331l, AttributeInfo.EnumAttributeType.integer, null, null);
		atrInfoTable[13] = new AtrInfoTable(AttributeName.TRIMCTM, 0x3333333331l, AttributeInfo.EnumAttributeType.matrix, null, null);
		atrInfoTable[14] = new AtrInfoTable(AttributeName.CLIPPATH, 0x3333333111l, AttributeInfo.EnumAttributeType.PDFPath, null, null);
		atrInfoTable[15] = new AtrInfoTable(AttributeName.HALFTONEPHASEORIGIN, 0x3333333333l, AttributeInfo.EnumAttributeType.XYPair, null, "0 0");
		atrInfoTable[16] = new AtrInfoTable(AttributeName.COMPENSATIONCTMFORMAT, 0x3333331111l, AttributeInfo.EnumAttributeType.string, null, null);
		atrInfoTable[17] = new AtrInfoTable(AttributeName.ASSEMBLYIDS, 0x3333311111l, AttributeInfo.EnumAttributeType.NMTOKENS, null, null);
		atrInfoTable[18] = new AtrInfoTable(AttributeName.CTM, 0x2222222222l, AttributeInfo.EnumAttributeType.matrix, null, null);
		atrInfoTable[19] = new AtrInfoTable(AttributeName.CLIPBOXTEMPLATE, 0x3333331111l, AttributeInfo.EnumAttributeType.string, null, null);
		atrInfoTable[20] = new AtrInfoTable(AttributeName.TRIMSIZE, 0x3333333311l, AttributeInfo.EnumAttributeType.XYPair, null, null);
	}

	@Override
	protected AttributeInfo getTheAttributeInfo()
	{
		return super.getTheAttributeInfo().updateReplace(atrInfoTable);
	}

	private static ElemInfoTable[] elemInfoTable = new ElemInfoTable[14];
	static
	{
		elemInfoTable[0] = new ElemInfoTable(ElementName.CIELABMEASURINGFIELD, 0x3333333333l);
		elemInfoTable[1] = new ElemInfoTable(ElementName.COLORCONTROLSTRIP, 0x3333333333l);
		elemInfoTable[2] = new ElemInfoTable(ElementName.CUTMARK, 0x3333333333l);
		elemInfoTable[3] = new ElemInfoTable(ElementName.DENSITYMEASURINGFIELD, 0x3333333333l);
		elemInfoTable[4] = new ElemInfoTable(ElementName.DEVICEMARK, 0x6666666661l);
		elemInfoTable[5] = new ElemInfoTable(ElementName.DYNAMICFIELD, 0x3333333333l);
		elemInfoTable[6] = new ElemInfoTable(ElementName.FILLMARK, 0x3333311111l);
		elemInfoTable[7] = new ElemInfoTable(ElementName.IDENTIFICATIONFIELD, 0x3333333333l);
		elemInfoTable[8] = new ElemInfoTable(ElementName.JOBFIELD, 0x6666666661l);
		elemInfoTable[9] = new ElemInfoTable(ElementName.LAYOUTELEMENT, 0x7777776666l);
		elemInfoTable[10] = new ElemInfoTable(ElementName.MARKACTIVATION, 0x3333331111l);
		elemInfoTable[11] = new ElemInfoTable(ElementName.REFANCHOR, 0x6666661111l);
		elemInfoTable[12] = new ElemInfoTable(ElementName.REGISTERMARK, 0x3333333333l);
		elemInfoTable[13] = new ElemInfoTable(ElementName.SCAVENGERAREA, 0x3333333331l);
	}

	@Override
	protected ElementInfo getTheElementInfo()
	{
		return super.getTheElementInfo().updateReplace(elemInfoTable);
	}

	/**
	 * Constructor for JDFAutoMarkObject
	 *
	 * @param myOwnerDocument
	 * @param qualifiedName
	 */
	protected JDFAutoMarkObject(CoreDocumentImpl myOwnerDocument, String qualifiedName)
	{
		super(myOwnerDocument, qualifiedName);
	}

	/**
	 * Constructor for JDFAutoMarkObject
	 *
	 * @param myOwnerDocument
	 * @param myNamespaceURI
	 * @param qualifiedName
	 */
	protected JDFAutoMarkObject(CoreDocumentImpl myOwnerDocument, String myNamespaceURI, String qualifiedName)
	{
		super(myOwnerDocument, myNamespaceURI, qualifiedName);
	}

	/**
	 * Constructor for JDFAutoMarkObject
	 *
	 * @param myOwnerDocument
	 * @param myNamespaceURI
	 * @param qualifiedName
	 * @param myLocalName
	 */
	protected JDFAutoMarkObject(CoreDocumentImpl myOwnerDocument, String myNamespaceURI, String qualifiedName, String myLocalName)
	{
		super(myOwnerDocument, myNamespaceURI, qualifiedName, myLocalName);
	}

	/**
	 * Enumeration strings for Anchor
	 */

	public enum EAnchor
	{
		TopLeft, TopCenter, TopRight, CenterLeft, Center, CenterRight, BottomLeft, BottomCenter, BottomRight;

		public static EAnchor getEnum(String val)
		{
			return JavaEnumUtil.getEnumIgnoreCase(EAnchor.class, val, null);
		}
	}

	/**
	 * Enumeration strings for Anchor
	 */

	@SuppressWarnings("rawtypes")
	public static class EnumAnchor extends ValuedEnum
	{
		private static final long serialVersionUID = 1L;
		private static int m_startValue = 0;

		protected EnumAnchor(String name)
		{
			super(name, m_startValue++);
		}

		/**
		 * @param enumName the string to convert
		 * @return the enum
		 */
		public static EnumAnchor getEnum(String enumName)
		{
			return (EnumAnchor) getEnum(EnumAnchor.class, enumName);
		}

		/**
		 * @param enumValue the integer to convert
		 * @return the enum
		 */
		public static EnumAnchor getEnum(int enumValue)
		{
			return (EnumAnchor) getEnum(EnumAnchor.class, enumValue);
		}

		/**
		 * @return the map of enums
		 */
		public static Map getEnumMap()
		{
			return getEnumMap(EnumAnchor.class);
		}

		/**
		 * @return the list of enums
		 */
		public static List getEnumList()
		{
			return getEnumList(EnumAnchor.class);
		}

		/**
		 * @return the iterator
		 */
		public static Iterator iterator()
		{
			return iterator(EnumAnchor.class);
		}

		/**  */
		public static final EnumAnchor TopLeft = new EnumAnchor("TopLeft");
		/**  */
		public static final EnumAnchor TopCenter = new EnumAnchor("TopCenter");
		/**  */
		public static final EnumAnchor TopRight = new EnumAnchor("TopRight");
		/**  */
		public static final EnumAnchor CenterLeft = new EnumAnchor("CenterLeft");
		/**  */
		public static final EnumAnchor Center = new EnumAnchor("Center");
		/**  */
		public static final EnumAnchor CenterRight = new EnumAnchor("CenterRight");
		/**  */
		public static final EnumAnchor BottomLeft = new EnumAnchor("BottomLeft");
		/**  */
		public static final EnumAnchor BottomCenter = new EnumAnchor("BottomCenter");
		/**  */
		public static final EnumAnchor BottomRight = new EnumAnchor("BottomRight");
	}

	/**
	 * Enumeration strings for Type
	 */

	public enum EType
	{
		Content, Mark;

		public static EType getEnum(String val)
		{
			return JavaEnumUtil.getEnumIgnoreCase(EType.class, val, null);
		}
	}

	/**
	 * Enumeration strings for Type
	 */

	@SuppressWarnings("rawtypes")
	public static class EnumType extends ValuedEnum
	{
		private static final long serialVersionUID = 1L;
		private static int m_startValue = 0;

		protected EnumType(String name)
		{
			super(name, m_startValue++);
		}

		/**
		 * @param enumName the string to convert
		 * @return the enum
		 */
		public static EnumType getEnum(String enumName)
		{
			return (EnumType) getEnum(EnumType.class, enumName);
		}

		/**
		 * @param enumValue the integer to convert
		 * @return the enum
		 */
		public static EnumType getEnum(int enumValue)
		{
			return (EnumType) getEnum(EnumType.class, enumValue);
		}

		/**
		 * @return the map of enums
		 */
		public static Map getEnumMap()
		{
			return getEnumMap(EnumType.class);
		}

		/**
		 * @return the list of enums
		 */
		public static List getEnumList()
		{
			return getEnumList(EnumType.class);
		}

		/**
		 * @return the iterator
		 */
		public static Iterator iterator()
		{
			return iterator(EnumType.class);
		}

		/**  */
		public static final EnumType Content = new EnumType("Content");
		/**  */
		public static final EnumType Mark = new EnumType("Mark");
	}

	/*
	 * ************************************************************************ Attribute getter / setter ************************************************************************
	 */

	/*
	 * --------------------------------------------------------------------- Methods for Attribute Ord ---------------------------------------------------------------------
	 */
	/**
	 * (36) set attribute Ord
	 *
	 * @param value the value to set the attribute to
	 */
	public void setOrd(int value)
	{
		setAttribute(AttributeName.ORD, value, null);
	}

	/**
	 * (15) get int attribute Ord
	 *
	 * @return int the value of the attribute
	 */
	public int getOrd()
	{
		return getIntAttribute(AttributeName.ORD, null, 0);
	}

	/*
	 * --------------------------------------------------------------------- Methods for Attribute SourceClipPath
	 * ---------------------------------------------------------------------
	 */
	/**
	 * (36) set attribute SourceClipPath
	 *
	 * @param value the value to set the attribute to
	 */
	public void setSourceClipPath(String value)
	{
		setAttribute(AttributeName.SOURCECLIPPATH, value, null);
	}

	/**
	 * (23) get String attribute SourceClipPath
	 *
	 * @return the value of the attribute
	 */
	public String getSourceClipPath()
	{
		return getAttribute(AttributeName.SOURCECLIPPATH, null, JDFCoreConstants.EMPTYSTRING);
	}

	/*
	 * --------------------------------------------------------------------- Methods for Attribute TrimClipPath
	 * ---------------------------------------------------------------------
	 */
	/**
	 * (36) set attribute TrimClipPath
	 *
	 * @param value the value to set the attribute to
	 */
	public void setTrimClipPath(String value)
	{
		setAttribute(AttributeName.TRIMCLIPPATH, value, null);
	}

	/**
	 * (23) get String attribute TrimClipPath
	 *
	 * @return the value of the attribute
	 */
	public String getTrimClipPath()
	{
		return getAttribute(AttributeName.TRIMCLIPPATH, null, JDFCoreConstants.EMPTYSTRING);
	}

	/*
	 * --------------------------------------------------------------------- Methods for Attribute LogicalStackOrd
	 * ---------------------------------------------------------------------
	 */
	/**
	 * (36) set attribute LogicalStackOrd
	 *
	 * @param value the value to set the attribute to
	 */
	public void setLogicalStackOrd(int value)
	{
		setAttribute(AttributeName.LOGICALSTACKORD, value, null);
	}

	/**
	 * (15) get int attribute LogicalStackOrd
	 *
	 * @return int the value of the attribute
	 */
	public int getLogicalStackOrd()
	{
		return getIntAttribute(AttributeName.LOGICALSTACKORD, null, 0);
	}

	/*
	 * --------------------------------------------------------------------- Methods for Attribute LayoutElementPageNum
	 * ---------------------------------------------------------------------
	 */
	/**
	 * (36) set attribute LayoutElementPageNum
	 *
	 * @param value the value to set the attribute to
	 */
	public void setLayoutElementPageNum(int value)
	{
		setAttribute(AttributeName.LAYOUTELEMENTPAGENUM, value, null);
	}

	/**
	 * (15) get int attribute LayoutElementPageNum
	 *
	 * @return int the value of the attribute
	 */
	public int getLayoutElementPageNum()
	{
		return getIntAttribute(AttributeName.LAYOUTELEMENTPAGENUM, null, 0);
	}

	/*
	 * --------------------------------------------------------------------- Methods for Attribute ContentRef ---------------------------------------------------------------------
	 */
	/**
	 * (36) set attribute ContentRef
	 *
	 * @param value the value to set the attribute to
	 */
	public void setContentRef(String value)
	{
		setAttribute(AttributeName.CONTENTREF, value, null);
	}

	/**
	 * (23) get String attribute ContentRef
	 *
	 * @return the value of the attribute
	 */
	public String getContentRef()
	{
		return getAttribute(AttributeName.CONTENTREF, null, JDFCoreConstants.EMPTYSTRING);
	}

	/*
	 * --------------------------------------------------------------------- Methods for Attribute ClipBoxFormat
	 * ---------------------------------------------------------------------
	 */
	/**
	 * (36) set attribute ClipBoxFormat
	 *
	 * @param value the value to set the attribute to
	 */
	public void setClipBoxFormat(String value)
	{
		setAttribute(AttributeName.CLIPBOXFORMAT, value, null);
	}

	/**
	 * (23) get String attribute ClipBoxFormat
	 *
	 * @return the value of the attribute
	 */
	public String getClipBoxFormat()
	{
		return getAttribute(AttributeName.CLIPBOXFORMAT, null, JDFCoreConstants.EMPTYSTRING);
	}

	/*
	 * --------------------------------------------------------------------- Methods for Attribute Anchor ---------------------------------------------------------------------
	 */
	/**
	 * (5) set attribute Anchor
	 *
	 * @param enumVar the enumVar to set the attribute to
	 */
	public void setAnchor(EAnchor enumVar)
	{
		setAttribute(AttributeName.ANCHOR, enumVar == null ? null : enumVar.name(), null);
	}

	/**
	 * (9) get attribute Anchor
	 *
	 * @return the value of the attribute
	 */
	public EAnchor getEAnchor()
	{
		return EAnchor.getEnum(getAttribute(AttributeName.ANCHOR, null, null));
	}

	/*
	 * --------------------------------------------------------------------- Methods for Attribute Anchor ---------------------------------------------------------------------
	 */
	/**
	 * (5) set attribute Anchor
	 *
	 * @param enumVar the enumVar to set the attribute to
	 * @deprecated use java.lang.enum
	 */
	@Deprecated
	public void setAnchor(EnumAnchor enumVar)
	{
		setAttribute(AttributeName.ANCHOR, enumVar == null ? null : enumVar.getName(), null);
	}

	/**
	 * (9) get attribute Anchor
	 *
	 * @return the value of the attribute
	 */
	public EnumAnchor getAnchor()
	{
		return EnumAnchor.getEnum(getAttribute(AttributeName.ANCHOR, null, null));
	}

	/*
	 * --------------------------------------------------------------------- Methods for Attribute OrdID ---------------------------------------------------------------------
	 */
	/**
	 * (36) set attribute OrdID
	 *
	 * @param value the value to set the attribute to
	 */
	public void setOrdID(int value)
	{
		setAttribute(AttributeName.ORDID, value, null);
	}

	/**
	 * (15) get int attribute OrdID
	 *
	 * @return int the value of the attribute
	 */
	public int getOrdID()
	{
		return getIntAttribute(AttributeName.ORDID, null, 0);
	}

	/*
	 * --------------------------------------------------------------------- Methods for Attribute Type ---------------------------------------------------------------------
	 */
	/**
	 * (5) set attribute Type
	 *
	 * @param enumVar the enumVar to set the attribute to
	 */
	public void setType(EType enumVar)
	{
		setAttribute(AttributeName.TYPE, enumVar == null ? null : enumVar.name(), null);
	}

	/**
	 * (9) get attribute Type
	 *
	 * @return the value of the attribute
	 */
	public EType getEType()
	{
		return EType.getEnum(getAttribute(AttributeName.TYPE, null, null));
	}

	/*
	 * --------------------------------------------------------------------- Methods for Attribute Type ---------------------------------------------------------------------
	 */
	/**
	 * (5) set attribute Type
	 *
	 * @param enumVar the enumVar to set the attribute to
	 * @deprecated use java.lang.enum
	 */
	@Deprecated
	public void setType(EnumType enumVar)
	{
		setAttribute(AttributeName.TYPE, enumVar == null ? null : enumVar.getName(), null);
	}

	/**
	 * (9) get attribute Type
	 *
	 * @return the value of the attribute
	 */
	public EnumType getType()
	{
		return EnumType.getEnum(getAttribute(AttributeName.TYPE, null, null));
	}

	/*
	 * --------------------------------------------------------------------- Methods for Attribute ClipBox ---------------------------------------------------------------------
	 */
	/**
	 * (36) set attribute ClipBox
	 *
	 * @param value the value to set the attribute to
	 */
	public void setClipBox(JDFRectangle value)
	{
		setAttribute(AttributeName.CLIPBOX, value, null);
	}

	/**
	 * (20) get JDFRectangle attribute ClipBox
	 *
	 * @return JDFRectangle the value of the attribute, null if a the attribute value is not a valid to create a JDFRectangle
	 */
	public JDFRectangle getClipBox()
	{
		final String strAttrName = getAttribute(AttributeName.CLIPBOX, null, null);
		final JDFRectangle nPlaceHolder = JDFRectangle.createRectangle(strAttrName);
		return nPlaceHolder;
	}

	/*
	 * --------------------------------------------------------------------- Methods for Attribute CompensationCTMTemplate
	 * ---------------------------------------------------------------------
	 */
	/**
	 * (36) set attribute CompensationCTMTemplate
	 *
	 * @param value the value to set the attribute to
	 */
	public void setCompensationCTMTemplate(String value)
	{
		setAttribute(AttributeName.COMPENSATIONCTMTEMPLATE, value, null);
	}

	/**
	 * (23) get String attribute CompensationCTMTemplate
	 *
	 * @return the value of the attribute
	 */
	public String getCompensationCTMTemplate()
	{
		return getAttribute(AttributeName.COMPENSATIONCTMTEMPLATE, null, JDFCoreConstants.EMPTYSTRING);
	}

	/*
	 * --------------------------------------------------------------------- Methods for Attribute LayerID ---------------------------------------------------------------------
	 */
	/**
	 * (36) set attribute LayerID
	 *
	 * @param value the value to set the attribute to
	 */
	public void setLayerID(int value)
	{
		setAttribute(AttributeName.LAYERID, value, null);
	}

	/**
	 * (15) get int attribute LayerID
	 *
	 * @return int the value of the attribute
	 */
	public int getLayerID()
	{
		return getIntAttribute(AttributeName.LAYERID, null, 0);
	}

	/*
	 * --------------------------------------------------------------------- Methods for Attribute TrimCTM ---------------------------------------------------------------------
	 */
	/**
	 * (36) set attribute TrimCTM
	 *
	 * @param value the value to set the attribute to
	 */
	public void setTrimCTM(JDFMatrix value)
	{
		setAttribute(AttributeName.TRIMCTM, value, null);
	}

	/**
	 * (20) get JDFMatrix attribute TrimCTM
	 *
	 * @return JDFMatrix the value of the attribute, null if a the attribute value is not a valid to create a JDFMatrix
	 */
	public JDFMatrix getTrimCTM()
	{
		final String strAttrName = getAttribute(AttributeName.TRIMCTM, null, null);
		final JDFMatrix nPlaceHolder = JDFMatrix.createMatrix(strAttrName);
		return nPlaceHolder;
	}

	/*
	 * --------------------------------------------------------------------- Methods for Attribute ClipPath ---------------------------------------------------------------------
	 */
	/**
	 * (36) set attribute ClipPath
	 *
	 * @param value the value to set the attribute to
	 */
	public void setClipPath(String value)
	{
		setAttribute(AttributeName.CLIPPATH, value, null);
	}

	/**
	 * (23) get String attribute ClipPath
	 *
	 * @return the value of the attribute
	 */
	public String getClipPath()
	{
		return getAttribute(AttributeName.CLIPPATH, null, JDFCoreConstants.EMPTYSTRING);
	}

	/*
	 * --------------------------------------------------------------------- Methods for Attribute HalfTonePhaseOrigin
	 * ---------------------------------------------------------------------
	 */
	/**
	 * (36) set attribute HalfTonePhaseOrigin
	 *
	 * @param value the value to set the attribute to
	 */
	public void setHalfTonePhaseOrigin(JDFXYPair value)
	{
		setAttribute(AttributeName.HALFTONEPHASEORIGIN, value, null);
	}

	/**
	 * (20) get JDFXYPair attribute HalfTonePhaseOrigin
	 *
	 * @return JDFXYPair the value of the attribute, null if a the attribute value is not a valid to create a JDFXYPair
	 */
	public JDFXYPair getHalfTonePhaseOrigin()
	{
		final String strAttrName = getAttribute(AttributeName.HALFTONEPHASEORIGIN, null, null);
		final JDFXYPair nPlaceHolder = JDFXYPair.createXYPair(strAttrName);
		return nPlaceHolder;
	}

	/*
	 * --------------------------------------------------------------------- Methods for Attribute CompensationCTMFormat
	 * ---------------------------------------------------------------------
	 */
	/**
	 * (36) set attribute CompensationCTMFormat
	 *
	 * @param value the value to set the attribute to
	 */
	public void setCompensationCTMFormat(String value)
	{
		setAttribute(AttributeName.COMPENSATIONCTMFORMAT, value, null);
	}

	/**
	 * (23) get String attribute CompensationCTMFormat
	 *
	 * @return the value of the attribute
	 */
	public String getCompensationCTMFormat()
	{
		return getAttribute(AttributeName.COMPENSATIONCTMFORMAT, null, JDFCoreConstants.EMPTYSTRING);
	}

	/*
	 * --------------------------------------------------------------------- Methods for Attribute AssemblyIDs ---------------------------------------------------------------------
	 */
	/**
	 * (36) set attribute AssemblyIDs
	 *
	 * @param value the value to set the attribute to
	 */
	public void setAssemblyIDs(VString value)
	{
		setAttribute(AttributeName.ASSEMBLYIDS, value, null);
	}

	/**
	 * (21) get VString attribute AssemblyIDs
	 *
	 * @return VString the value of the attribute
	 */
	public VString getAssemblyIDs()
	{
		final VString vStrAttrib = new VString();
		final String s = getAttribute(AttributeName.ASSEMBLYIDS, null, JDFCoreConstants.EMPTYSTRING);
		vStrAttrib.setAllStrings(s, " ");
		return vStrAttrib;
	}

	/*
	 * --------------------------------------------------------------------- Methods for Attribute CTM ---------------------------------------------------------------------
	 */
	/**
	 * (36) set attribute CTM
	 *
	 * @param value the value to set the attribute to
	 */
	public void setCTM(JDFMatrix value)
	{
		setAttribute(AttributeName.CTM, value, null);
	}

	/**
	 * (20) get JDFMatrix attribute CTM
	 *
	 * @return JDFMatrix the value of the attribute, null if a the attribute value is not a valid to create a JDFMatrix
	 */
	public JDFMatrix getCTM()
	{
		final String strAttrName = getAttribute(AttributeName.CTM, null, null);
		final JDFMatrix nPlaceHolder = JDFMatrix.createMatrix(strAttrName);
		return nPlaceHolder;
	}

	/*
	 * --------------------------------------------------------------------- Methods for Attribute ClipBoxTemplate
	 * ---------------------------------------------------------------------
	 */
	/**
	 * (36) set attribute ClipBoxTemplate
	 *
	 * @param value the value to set the attribute to
	 */
	public void setClipBoxTemplate(String value)
	{
		setAttribute(AttributeName.CLIPBOXTEMPLATE, value, null);
	}

	/**
	 * (23) get String attribute ClipBoxTemplate
	 *
	 * @return the value of the attribute
	 */
	public String getClipBoxTemplate()
	{
		return getAttribute(AttributeName.CLIPBOXTEMPLATE, null, JDFCoreConstants.EMPTYSTRING);
	}

	/*
	 * --------------------------------------------------------------------- Methods for Attribute TrimSize ---------------------------------------------------------------------
	 */
	/**
	 * (36) set attribute TrimSize
	 *
	 * @param value the value to set the attribute to
	 */
	public void setTrimSize(JDFXYPair value)
	{
		setAttribute(AttributeName.TRIMSIZE, value, null);
	}

	/**
	 * (20) get JDFXYPair attribute TrimSize
	 *
	 * @return JDFXYPair the value of the attribute, null if a the attribute value is not a valid to create a JDFXYPair
	 */
	public JDFXYPair getTrimSize()
	{
		final String strAttrName = getAttribute(AttributeName.TRIMSIZE, null, null);
		final JDFXYPair nPlaceHolder = JDFXYPair.createXYPair(strAttrName);
		return nPlaceHolder;
	}

	/*
	 * *********************************************************************** Element getter / setter ***********************************************************************
	 */

	/**
	 * (24) const get element CIELABMeasuringField
	 *
	 * @return JDFCIELABMeasuringField the element
	 */
	public JDFCIELABMeasuringField getCIELABMeasuringField()
	{
		return (JDFCIELABMeasuringField) getElement(ElementName.CIELABMEASURINGFIELD, null, 0);
	}

	/**
	 * (25) getCreateCIELABMeasuringField
	 * 
	 * @return JDFCIELABMeasuringField the element
	 */
	public JDFCIELABMeasuringField getCreateCIELABMeasuringField()
	{
		return (JDFCIELABMeasuringField) getCreateElement_JDFElement(ElementName.CIELABMEASURINGFIELD, null, 0);
	}

	/**
	 * (26) getCreateCIELABMeasuringField
	 * 
	 * @param iSkip number of elements to skip
	 * @return JDFCIELABMeasuringField the element
	 */
	public JDFCIELABMeasuringField getCreateCIELABMeasuringField(int iSkip)
	{
		return (JDFCIELABMeasuringField) getCreateElement_JDFElement(ElementName.CIELABMEASURINGFIELD, null, iSkip);
	}

	/**
	 * (27) const get element CIELABMeasuringField
	 *
	 * @param iSkip number of elements to skip
	 * @return JDFCIELABMeasuringField the element default is getCIELABMeasuringField(0)
	 */
	public JDFCIELABMeasuringField getCIELABMeasuringField(int iSkip)
	{
		return (JDFCIELABMeasuringField) getElement(ElementName.CIELABMEASURINGFIELD, null, iSkip);
	}

	/**
	 * Get all CIELABMeasuringField from the current element
	 * 
	 * @return Collection<JDFCIELABMeasuringField>, null if none are available
	 */
	public Collection<JDFCIELABMeasuringField> getAllCIELABMeasuringField()
	{
		return getChildArrayByClass(JDFCIELABMeasuringField.class, false, 0);
	}

	/**
	 * (30) append element CIELABMeasuringField
	 *
	 * @return JDFCIELABMeasuringField the element
	 */
	public JDFCIELABMeasuringField appendCIELABMeasuringField()
	{
		return (JDFCIELABMeasuringField) appendElement(ElementName.CIELABMEASURINGFIELD, null);
	}

	/**
	 * (31) create inter-resource link to refTarget
	 *
	 * @param refTarget the element that is referenced
	 */
	public void refCIELABMeasuringField(JDFCIELABMeasuringField refTarget)
	{
		refElement(refTarget);
	}

	/**
	 * (24) const get element ColorControlStrip
	 *
	 * @return JDFColorControlStrip the element
	 */
	public JDFColorControlStrip getColorControlStrip()
	{
		return (JDFColorControlStrip) getElement(ElementName.COLORCONTROLSTRIP, null, 0);
	}

	/**
	 * (25) getCreateColorControlStrip
	 * 
	 * @return JDFColorControlStrip the element
	 */
	public JDFColorControlStrip getCreateColorControlStrip()
	{
		return (JDFColorControlStrip) getCreateElement_JDFElement(ElementName.COLORCONTROLSTRIP, null, 0);
	}

	/**
	 * (26) getCreateColorControlStrip
	 * 
	 * @param iSkip number of elements to skip
	 * @return JDFColorControlStrip the element
	 */
	public JDFColorControlStrip getCreateColorControlStrip(int iSkip)
	{
		return (JDFColorControlStrip) getCreateElement_JDFElement(ElementName.COLORCONTROLSTRIP, null, iSkip);
	}

	/**
	 * (27) const get element ColorControlStrip
	 *
	 * @param iSkip number of elements to skip
	 * @return JDFColorControlStrip the element default is getColorControlStrip(0)
	 */
	public JDFColorControlStrip getColorControlStrip(int iSkip)
	{
		return (JDFColorControlStrip) getElement(ElementName.COLORCONTROLSTRIP, null, iSkip);
	}

	/**
	 * Get all ColorControlStrip from the current element
	 * 
	 * @return Collection<JDFColorControlStrip>, null if none are available
	 */
	public Collection<JDFColorControlStrip> getAllColorControlStrip()
	{
		return getChildArrayByClass(JDFColorControlStrip.class, false, 0);
	}

	/**
	 * (30) append element ColorControlStrip
	 *
	 * @return JDFColorControlStrip the element
	 */
	public JDFColorControlStrip appendColorControlStrip()
	{
		return (JDFColorControlStrip) appendElement(ElementName.COLORCONTROLSTRIP, null);
	}

	/**
	 * (31) create inter-resource link to refTarget
	 *
	 * @param refTarget the element that is referenced
	 */
	public void refColorControlStrip(JDFColorControlStrip refTarget)
	{
		refElement(refTarget);
	}

	/**
	 * (24) const get element CutMark
	 *
	 * @return JDFCutMark the element
	 */
	public JDFCutMark getCutMark()
	{
		return (JDFCutMark) getElement(ElementName.CUTMARK, null, 0);
	}

	/**
	 * (25) getCreateCutMark
	 * 
	 * @return JDFCutMark the element
	 */
	public JDFCutMark getCreateCutMark()
	{
		return (JDFCutMark) getCreateElement_JDFElement(ElementName.CUTMARK, null, 0);
	}

	/**
	 * (26) getCreateCutMark
	 * 
	 * @param iSkip number of elements to skip
	 * @return JDFCutMark the element
	 */
	public JDFCutMark getCreateCutMark(int iSkip)
	{
		return (JDFCutMark) getCreateElement_JDFElement(ElementName.CUTMARK, null, iSkip);
	}

	/**
	 * (27) const get element CutMark
	 *
	 * @param iSkip number of elements to skip
	 * @return JDFCutMark the element default is getCutMark(0)
	 */
	public JDFCutMark getCutMark(int iSkip)
	{
		return (JDFCutMark) getElement(ElementName.CUTMARK, null, iSkip);
	}

	/**
	 * Get all CutMark from the current element
	 * 
	 * @return Collection<JDFCutMark>, null if none are available
	 */
	public Collection<JDFCutMark> getAllCutMark()
	{
		return getChildArrayByClass(JDFCutMark.class, false, 0);
	}

	/**
	 * (30) append element CutMark
	 *
	 * @return JDFCutMark the element
	 */
	public JDFCutMark appendCutMark()
	{
		return (JDFCutMark) appendElement(ElementName.CUTMARK, null);
	}

	/**
	 * (31) create inter-resource link to refTarget
	 *
	 * @param refTarget the element that is referenced
	 */
	public void refCutMark(JDFCutMark refTarget)
	{
		refElement(refTarget);
	}

	/**
	 * (24) const get element DensityMeasuringField
	 *
	 * @return JDFDensityMeasuringField the element
	 */
	public JDFDensityMeasuringField getDensityMeasuringField()
	{
		return (JDFDensityMeasuringField) getElement(ElementName.DENSITYMEASURINGFIELD, null, 0);
	}

	/**
	 * (25) getCreateDensityMeasuringField
	 * 
	 * @return JDFDensityMeasuringField the element
	 */
	public JDFDensityMeasuringField getCreateDensityMeasuringField()
	{
		return (JDFDensityMeasuringField) getCreateElement_JDFElement(ElementName.DENSITYMEASURINGFIELD, null, 0);
	}

	/**
	 * (26) getCreateDensityMeasuringField
	 * 
	 * @param iSkip number of elements to skip
	 * @return JDFDensityMeasuringField the element
	 */
	public JDFDensityMeasuringField getCreateDensityMeasuringField(int iSkip)
	{
		return (JDFDensityMeasuringField) getCreateElement_JDFElement(ElementName.DENSITYMEASURINGFIELD, null, iSkip);
	}

	/**
	 * (27) const get element DensityMeasuringField
	 *
	 * @param iSkip number of elements to skip
	 * @return JDFDensityMeasuringField the element default is getDensityMeasuringField(0)
	 */
	public JDFDensityMeasuringField getDensityMeasuringField(int iSkip)
	{
		return (JDFDensityMeasuringField) getElement(ElementName.DENSITYMEASURINGFIELD, null, iSkip);
	}

	/**
	 * Get all DensityMeasuringField from the current element
	 * 
	 * @return Collection<JDFDensityMeasuringField>, null if none are available
	 */
	public Collection<JDFDensityMeasuringField> getAllDensityMeasuringField()
	{
		return getChildArrayByClass(JDFDensityMeasuringField.class, false, 0);
	}

	/**
	 * (30) append element DensityMeasuringField
	 *
	 * @return JDFDensityMeasuringField the element
	 */
	public JDFDensityMeasuringField appendDensityMeasuringField()
	{
		return (JDFDensityMeasuringField) appendElement(ElementName.DENSITYMEASURINGFIELD, null);
	}

	/**
	 * (31) create inter-resource link to refTarget
	 *
	 * @param refTarget the element that is referenced
	 */
	public void refDensityMeasuringField(JDFDensityMeasuringField refTarget)
	{
		refElement(refTarget);
	}

	/**
	 * (24) const get element DeviceMark
	 *
	 * @return JDFDeviceMark the element
	 */
	public JDFDeviceMark getDeviceMark()
	{
		return (JDFDeviceMark) getElement(ElementName.DEVICEMARK, null, 0);
	}

	/**
	 * (25) getCreateDeviceMark
	 * 
	 * @return JDFDeviceMark the element
	 */
	public JDFDeviceMark getCreateDeviceMark()
	{
		return (JDFDeviceMark) getCreateElement_JDFElement(ElementName.DEVICEMARK, null, 0);
	}

	/**
	 * (29) append element DeviceMark
	 *
	 * @return JDFDeviceMark the element @ if the element already exists
	 */
	public JDFDeviceMark appendDeviceMark()
	{
		return (JDFDeviceMark) appendElementN(ElementName.DEVICEMARK, 1, null);
	}

	/**
	 * (24) const get element DynamicField
	 *
	 * @return JDFDynamicField the element
	 */
	public JDFDynamicField getDynamicField()
	{
		return (JDFDynamicField) getElement(ElementName.DYNAMICFIELD, null, 0);
	}

	/**
	 * (25) getCreateDynamicField
	 * 
	 * @return JDFDynamicField the element
	 */
	public JDFDynamicField getCreateDynamicField()
	{
		return (JDFDynamicField) getCreateElement_JDFElement(ElementName.DYNAMICFIELD, null, 0);
	}

	/**
	 * (26) getCreateDynamicField
	 * 
	 * @param iSkip number of elements to skip
	 * @return JDFDynamicField the element
	 */
	public JDFDynamicField getCreateDynamicField(int iSkip)
	{
		return (JDFDynamicField) getCreateElement_JDFElement(ElementName.DYNAMICFIELD, null, iSkip);
	}

	/**
	 * (27) const get element DynamicField
	 *
	 * @param iSkip number of elements to skip
	 * @return JDFDynamicField the element default is getDynamicField(0)
	 */
	public JDFDynamicField getDynamicField(int iSkip)
	{
		return (JDFDynamicField) getElement(ElementName.DYNAMICFIELD, null, iSkip);
	}

	/**
	 * Get all DynamicField from the current element
	 * 
	 * @return Collection<JDFDynamicField>, null if none are available
	 */
	public Collection<JDFDynamicField> getAllDynamicField()
	{
		return getChildArrayByClass(JDFDynamicField.class, false, 0);
	}

	/**
	 * (30) append element DynamicField
	 *
	 * @return JDFDynamicField the element
	 */
	public JDFDynamicField appendDynamicField()
	{
		return (JDFDynamicField) appendElement(ElementName.DYNAMICFIELD, null);
	}

	/**
	 * (24) const get element FillMark
	 *
	 * @return JDFFillMark the element
	 */
	public JDFFillMark getFillMark()
	{
		return (JDFFillMark) getElement(ElementName.FILLMARK, null, 0);
	}

	/**
	 * (25) getCreateFillMark
	 * 
	 * @return JDFFillMark the element
	 */
	public JDFFillMark getCreateFillMark()
	{
		return (JDFFillMark) getCreateElement_JDFElement(ElementName.FILLMARK, null, 0);
	}

	/**
	 * (26) getCreateFillMark
	 * 
	 * @param iSkip number of elements to skip
	 * @return JDFFillMark the element
	 */
	public JDFFillMark getCreateFillMark(int iSkip)
	{
		return (JDFFillMark) getCreateElement_JDFElement(ElementName.FILLMARK, null, iSkip);
	}

	/**
	 * (27) const get element FillMark
	 *
	 * @param iSkip number of elements to skip
	 * @return JDFFillMark the element default is getFillMark(0)
	 */
	public JDFFillMark getFillMark(int iSkip)
	{
		return (JDFFillMark) getElement(ElementName.FILLMARK, null, iSkip);
	}

	/**
	 * Get all FillMark from the current element
	 * 
	 * @return Collection<JDFFillMark>, null if none are available
	 */
	public Collection<JDFFillMark> getAllFillMark()
	{
		return getChildArrayByClass(JDFFillMark.class, false, 0);
	}

	/**
	 * (30) append element FillMark
	 *
	 * @return JDFFillMark the element
	 */
	public JDFFillMark appendFillMark()
	{
		return (JDFFillMark) appendElement(ElementName.FILLMARK, null);
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
	public JDFIdentificationField getCreateIdentificationField(int iSkip)
	{
		return (JDFIdentificationField) getCreateElement_JDFElement(ElementName.IDENTIFICATIONFIELD, null, iSkip);
	}

	/**
	 * (27) const get element IdentificationField
	 *
	 * @param iSkip number of elements to skip
	 * @return JDFIdentificationField the element default is getIdentificationField(0)
	 */
	public JDFIdentificationField getIdentificationField(int iSkip)
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
	public JDFIdentificationField appendIdentificationField()
	{
		return (JDFIdentificationField) appendElement(ElementName.IDENTIFICATIONFIELD, null);
	}

	/**
	 * (31) create inter-resource link to refTarget
	 *
	 * @param refTarget the element that is referenced
	 */
	public void refIdentificationField(JDFIdentificationField refTarget)
	{
		refElement(refTarget);
	}

	/**
	 * (24) const get element JobField
	 *
	 * @return JDFJobField the element
	 */
	public JDFJobField getJobField()
	{
		return (JDFJobField) getElement(ElementName.JOBFIELD, null, 0);
	}

	/**
	 * (25) getCreateJobField
	 * 
	 * @return JDFJobField the element
	 */
	public JDFJobField getCreateJobField()
	{
		return (JDFJobField) getCreateElement_JDFElement(ElementName.JOBFIELD, null, 0);
	}

	/**
	 * (29) append element JobField
	 *
	 * @return JDFJobField the element @ if the element already exists
	 */
	public JDFJobField appendJobField()
	{
		return (JDFJobField) appendElementN(ElementName.JOBFIELD, 1, null);
	}

	/**
	 * (24) const get element LayoutElement
	 *
	 * @return JDFLayoutElement the element
	 */
	public JDFLayoutElement getLayoutElement()
	{
		return (JDFLayoutElement) getElement(ElementName.LAYOUTELEMENT, null, 0);
	}

	/**
	 * (25) getCreateLayoutElement
	 * 
	 * @return JDFLayoutElement the element
	 */
	public JDFLayoutElement getCreateLayoutElement()
	{
		return (JDFLayoutElement) getCreateElement_JDFElement(ElementName.LAYOUTELEMENT, null, 0);
	}

	/**
	 * (29) append element LayoutElement
	 *
	 * @return JDFLayoutElement the element @ if the element already exists
	 */
	public JDFLayoutElement appendLayoutElement()
	{
		return (JDFLayoutElement) appendElementN(ElementName.LAYOUTELEMENT, 1, null);
	}

	/**
	 * (31) create inter-resource link to refTarget
	 *
	 * @param refTarget the element that is referenced
	 */
	public void refLayoutElement(JDFLayoutElement refTarget)
	{
		refElement(refTarget);
	}

	/**
	 * (24) const get element MarkActivation
	 *
	 * @return JDFMarkActivation the element
	 */
	public JDFMarkActivation getMarkActivation()
	{
		return (JDFMarkActivation) getElement(ElementName.MARKACTIVATION, null, 0);
	}

	/**
	 * (25) getCreateMarkActivation
	 * 
	 * @return JDFMarkActivation the element
	 */
	public JDFMarkActivation getCreateMarkActivation()
	{
		return (JDFMarkActivation) getCreateElement_JDFElement(ElementName.MARKACTIVATION, null, 0);
	}

	/**
	 * (26) getCreateMarkActivation
	 * 
	 * @param iSkip number of elements to skip
	 * @return JDFMarkActivation the element
	 */
	public JDFMarkActivation getCreateMarkActivation(int iSkip)
	{
		return (JDFMarkActivation) getCreateElement_JDFElement(ElementName.MARKACTIVATION, null, iSkip);
	}

	/**
	 * (27) const get element MarkActivation
	 *
	 * @param iSkip number of elements to skip
	 * @return JDFMarkActivation the element default is getMarkActivation(0)
	 */
	public JDFMarkActivation getMarkActivation(int iSkip)
	{
		return (JDFMarkActivation) getElement(ElementName.MARKACTIVATION, null, iSkip);
	}

	/**
	 * Get all MarkActivation from the current element
	 * 
	 * @return Collection<JDFMarkActivation>, null if none are available
	 */
	public Collection<JDFMarkActivation> getAllMarkActivation()
	{
		return getChildArrayByClass(JDFMarkActivation.class, false, 0);
	}

	/**
	 * (30) append element MarkActivation
	 *
	 * @return JDFMarkActivation the element
	 */
	public JDFMarkActivation appendMarkActivation()
	{
		return (JDFMarkActivation) appendElement(ElementName.MARKACTIVATION, null);
	}

	/**
	 * (24) const get element RefAnchor
	 *
	 * @return JDFRefAnchor the element
	 */
	public JDFRefAnchor getRefAnchor()
	{
		return (JDFRefAnchor) getElement(ElementName.REFANCHOR, null, 0);
	}

	/**
	 * (25) getCreateRefAnchor
	 * 
	 * @return JDFRefAnchor the element
	 */
	public JDFRefAnchor getCreateRefAnchor()
	{
		return (JDFRefAnchor) getCreateElement_JDFElement(ElementName.REFANCHOR, null, 0);
	}

	/**
	 * (29) append element RefAnchor
	 *
	 * @return JDFRefAnchor the element @ if the element already exists
	 */
	public JDFRefAnchor appendRefAnchor()
	{
		return (JDFRefAnchor) appendElementN(ElementName.REFANCHOR, 1, null);
	}

	/**
	 * (24) const get element RegisterMark
	 *
	 * @return JDFRegisterMark the element
	 */
	public JDFRegisterMark getRegisterMark()
	{
		return (JDFRegisterMark) getElement(ElementName.REGISTERMARK, null, 0);
	}

	/**
	 * (25) getCreateRegisterMark
	 * 
	 * @return JDFRegisterMark the element
	 */
	public JDFRegisterMark getCreateRegisterMark()
	{
		return (JDFRegisterMark) getCreateElement_JDFElement(ElementName.REGISTERMARK, null, 0);
	}

	/**
	 * (26) getCreateRegisterMark
	 * 
	 * @param iSkip number of elements to skip
	 * @return JDFRegisterMark the element
	 */
	public JDFRegisterMark getCreateRegisterMark(int iSkip)
	{
		return (JDFRegisterMark) getCreateElement_JDFElement(ElementName.REGISTERMARK, null, iSkip);
	}

	/**
	 * (27) const get element RegisterMark
	 *
	 * @param iSkip number of elements to skip
	 * @return JDFRegisterMark the element default is getRegisterMark(0)
	 */
	public JDFRegisterMark getRegisterMark(int iSkip)
	{
		return (JDFRegisterMark) getElement(ElementName.REGISTERMARK, null, iSkip);
	}

	/**
	 * Get all RegisterMark from the current element
	 * 
	 * @return Collection<JDFRegisterMark>, null if none are available
	 */
	public Collection<JDFRegisterMark> getAllRegisterMark()
	{
		return getChildArrayByClass(JDFRegisterMark.class, false, 0);
	}

	/**
	 * (30) append element RegisterMark
	 *
	 * @return JDFRegisterMark the element
	 */
	public JDFRegisterMark appendRegisterMark()
	{
		return (JDFRegisterMark) appendElement(ElementName.REGISTERMARK, null);
	}

	/**
	 * (31) create inter-resource link to refTarget
	 *
	 * @param refTarget the element that is referenced
	 */
	public void refRegisterMark(JDFRegisterMark refTarget)
	{
		refElement(refTarget);
	}

	/**
	 * (24) const get element ScavengerArea
	 *
	 * @return JDFScavengerArea the element
	 */
	public JDFScavengerArea getScavengerArea()
	{
		return (JDFScavengerArea) getElement(ElementName.SCAVENGERAREA, null, 0);
	}

	/**
	 * (25) getCreateScavengerArea
	 * 
	 * @return JDFScavengerArea the element
	 */
	public JDFScavengerArea getCreateScavengerArea()
	{
		return (JDFScavengerArea) getCreateElement_JDFElement(ElementName.SCAVENGERAREA, null, 0);
	}

	/**
	 * (26) getCreateScavengerArea
	 * 
	 * @param iSkip number of elements to skip
	 * @return JDFScavengerArea the element
	 */
	public JDFScavengerArea getCreateScavengerArea(int iSkip)
	{
		return (JDFScavengerArea) getCreateElement_JDFElement(ElementName.SCAVENGERAREA, null, iSkip);
	}

	/**
	 * (27) const get element ScavengerArea
	 *
	 * @param iSkip number of elements to skip
	 * @return JDFScavengerArea the element default is getScavengerArea(0)
	 */
	public JDFScavengerArea getScavengerArea(int iSkip)
	{
		return (JDFScavengerArea) getElement(ElementName.SCAVENGERAREA, null, iSkip);
	}

	/**
	 * Get all ScavengerArea from the current element
	 * 
	 * @return Collection<JDFScavengerArea>, null if none are available
	 */
	public Collection<JDFScavengerArea> getAllScavengerArea()
	{
		return getChildArrayByClass(JDFScavengerArea.class, false, 0);
	}

	/**
	 * (30) append element ScavengerArea
	 *
	 * @return JDFScavengerArea the element
	 */
	public JDFScavengerArea appendScavengerArea()
	{
		return (JDFScavengerArea) appendElement(ElementName.SCAVENGERAREA, null);
	}

	/**
	 * (31) create inter-resource link to refTarget
	 *
	 * @param refTarget the element that is referenced
	 */
	public void refScavengerArea(JDFScavengerArea refTarget)
	{
		refElement(refTarget);
	}

}
