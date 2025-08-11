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
import org.cip4.jdflib.datatypes.JDFMatrix;
import org.cip4.jdflib.resource.JDFResource;
import org.cip4.jdflib.resource.process.JDFLot;
import org.cip4.jdflib.util.JDFDate;
import org.cip4.jdflib.util.JDFDuration;
import org.cip4.jdflib.util.JavaEnumUtil;

/**
 *****************************************************************************
 * class JDFAutoPartAmount : public JDFElement
 *****************************************************************************
 * 
 */

public abstract class JDFAutoPartAmount extends JDFElement
{

	private static final long serialVersionUID = 1L;

	private static AtrInfoTable[] atrInfoTable = new AtrInfoTable[17];
	static
	{
		atrInfoTable[0] = new AtrInfoTable(AttributeName.ACTUALAMOUNT, 0x3333333311l, AttributeInfo.EnumAttributeType.double_, null, null);
		atrInfoTable[1] = new AtrInfoTable(AttributeName.AMOUNT, 0x3333333331l, AttributeInfo.EnumAttributeType.double_, null, null);
		atrInfoTable[2] = new AtrInfoTable(AttributeName.DRAFTOK, 0x4444444331l, AttributeInfo.EnumAttributeType.boolean_, null, null);
		atrInfoTable[3] = new AtrInfoTable(AttributeName.DURATION, 0x3333333331l, AttributeInfo.EnumAttributeType.duration, null, null);
		atrInfoTable[4] = new AtrInfoTable(AttributeName.MAXAMOUNT, 0x3333333111l, AttributeInfo.EnumAttributeType.double_, null, null);
		atrInfoTable[5] = new AtrInfoTable(AttributeName.MINAMOUNT, 0x3333333111l, AttributeInfo.EnumAttributeType.double_, null, null);
		atrInfoTable[6] = new AtrInfoTable(AttributeName.MINLATESTATUS, 0x3333333111l, AttributeInfo.EnumAttributeType.enumeration, JDFResource.EnumResStatus.getEnum(0), null);
		atrInfoTable[7] = new AtrInfoTable(AttributeName.MINSTATUS, 0x3333333111l, AttributeInfo.EnumAttributeType.enumeration, JDFResource.EnumResStatus.getEnum(0), null);
		atrInfoTable[8] = new AtrInfoTable(AttributeName.ORIENTATION, 0x3333333331l, AttributeInfo.EnumAttributeType.enumeration, EnumOrientation.getEnum(0), null);
		atrInfoTable[9] = new AtrInfoTable(AttributeName.PIPEPAUSE, 0x3333333331l, AttributeInfo.EnumAttributeType.double_, null, null);
		atrInfoTable[10] = new AtrInfoTable(AttributeName.PIPERESUME, 0x3333333331l, AttributeInfo.EnumAttributeType.double_, null, null);
		atrInfoTable[11] = new AtrInfoTable(AttributeName.PIPEURL, 0x3333333331l, AttributeInfo.EnumAttributeType.URL, null, null);
		atrInfoTable[12] = new AtrInfoTable(AttributeName.REMOTEPIPEENDPAUSE, 0x4444433331l, AttributeInfo.EnumAttributeType.double_, null, null);
		atrInfoTable[13] = new AtrInfoTable(AttributeName.REMOTEPIPEENDRESUME, 0x4444433331l, AttributeInfo.EnumAttributeType.double_, null, null);
		atrInfoTable[14] = new AtrInfoTable(AttributeName.START, 0x3333333331l, AttributeInfo.EnumAttributeType.dateTime, null, null);
		atrInfoTable[15] = new AtrInfoTable(AttributeName.STARTOFFSET, 0x3333333331l, AttributeInfo.EnumAttributeType.duration, null, null);
		atrInfoTable[16] = new AtrInfoTable(AttributeName.TRANSFORMATION, 0x3333333331l, AttributeInfo.EnumAttributeType.matrix, null, null);
	}

	@Override
	protected AttributeInfo getTheAttributeInfo()
	{
		return super.getTheAttributeInfo().updateReplace(atrInfoTable);
	}

	private static ElemInfoTable[] elemInfoTable = new ElemInfoTable[1];
	static
	{
		elemInfoTable[0] = new ElemInfoTable(ElementName.LOT, 0x3333333331l);
	}

	@Override
	protected ElementInfo getTheElementInfo()
	{
		return super.getTheElementInfo().updateReplace(elemInfoTable);
	}

	/**
	 * Constructor for JDFAutoPartAmount
	 *
	 * @param myOwnerDocument
	 * @param qualifiedName
	 */
	protected JDFAutoPartAmount(CoreDocumentImpl myOwnerDocument, String qualifiedName)
	{
		super(myOwnerDocument, qualifiedName);
	}

	/**
	 * Constructor for JDFAutoPartAmount
	 *
	 * @param myOwnerDocument
	 * @param myNamespaceURI
	 * @param qualifiedName
	 */
	protected JDFAutoPartAmount(CoreDocumentImpl myOwnerDocument, String myNamespaceURI, String qualifiedName)
	{
		super(myOwnerDocument, myNamespaceURI, qualifiedName);
	}

	/**
	 * Constructor for JDFAutoPartAmount
	 *
	 * @param myOwnerDocument
	 * @param myNamespaceURI
	 * @param qualifiedName
	 * @param myLocalName
	 */
	protected JDFAutoPartAmount(CoreDocumentImpl myOwnerDocument, String myNamespaceURI, String qualifiedName, String myLocalName)
	{
		super(myOwnerDocument, myNamespaceURI, qualifiedName, myLocalName);
	}

	/**
	 * Enumeration strings for Orientation
	 */

	public enum EOrientation
	{
		Rotate0, Rotate90, Rotate180, Rotate270, Flip0, Flip90, Flip180, Flip270;

		public static EOrientation getEnum(String val)
		{
			return JavaEnumUtil.getEnumIgnoreCase(EOrientation.class, val, null);
		}
	}

	/**
	 * Enumeration strings for Orientation
	 */

	@SuppressWarnings("rawtypes")
	public static class EnumOrientation extends ValuedEnum
	{
		private static final long serialVersionUID = 1L;
		private static int m_startValue = 0;

		protected EnumOrientation(String name)
		{
			super(name, m_startValue++);
		}

		/**
		 * @param enumName the string to convert
		 * @return the enum
		 */
		public static EnumOrientation getEnum(String enumName)
		{
			return (EnumOrientation) getEnum(EnumOrientation.class, enumName);
		}

		/**
		 * @param enumValue the integer to convert
		 * @return the enum
		 */
		public static EnumOrientation getEnum(int enumValue)
		{
			return (EnumOrientation) getEnum(EnumOrientation.class, enumValue);
		}

		/**
		 * @return the map of enums
		 */
		public static Map getEnumMap()
		{
			return getEnumMap(EnumOrientation.class);
		}

		/**
		 * @return the list of enums
		 */
		public static List getEnumList()
		{
			return getEnumList(EnumOrientation.class);
		}

		/**
		 * @return the iterator
		 */
		public static Iterator iterator()
		{
			return iterator(EnumOrientation.class);
		}

		/**  */
		public static final EnumOrientation Rotate0 = new EnumOrientation("Rotate0");
		/**  */
		public static final EnumOrientation Rotate90 = new EnumOrientation("Rotate90");
		/**  */
		public static final EnumOrientation Rotate180 = new EnumOrientation("Rotate180");
		/**  */
		public static final EnumOrientation Rotate270 = new EnumOrientation("Rotate270");
		/**  */
		public static final EnumOrientation Flip0 = new EnumOrientation("Flip0");
		/**  */
		public static final EnumOrientation Flip90 = new EnumOrientation("Flip90");
		/**  */
		public static final EnumOrientation Flip180 = new EnumOrientation("Flip180");
		/**  */
		public static final EnumOrientation Flip270 = new EnumOrientation("Flip270");
	}

	/*
	 * ************************************************************************ Attribute getter / setter ************************************************************************
	 */

	/*
	 * --------------------------------------------------------------------- Methods for Attribute ActualAmount
	 * ---------------------------------------------------------------------
	 */
	/**
	 * (36) set attribute ActualAmount
	 *
	 * @param value the value to set the attribute to
	 */
	public void setActualAmount(double value)
	{
		setAttribute(AttributeName.ACTUALAMOUNT, value, null);
	}

	/**
	 * (17) get double attribute ActualAmount
	 *
	 * @return double the value of the attribute
	 */
	public double getActualAmount()
	{
		return getRealAttribute(AttributeName.ACTUALAMOUNT, null, 0.0);
	}

	/*
	 * --------------------------------------------------------------------- Methods for Attribute Amount ---------------------------------------------------------------------
	 */
	/**
	 * (36) set attribute Amount
	 *
	 * @param value the value to set the attribute to
	 */
	public void setAmount(double value)
	{
		setAttribute(AttributeName.AMOUNT, value, null);
	}

	/**
	 * (17) get double attribute Amount
	 *
	 * @return double the value of the attribute
	 */
	public double getAmount()
	{
		return getRealAttribute(AttributeName.AMOUNT, null, 0.0);
	}

	/*
	 * --------------------------------------------------------------------- Methods for Attribute DraftOK ---------------------------------------------------------------------
	 */
	/**
	 * (36) set attribute DraftOK
	 *
	 * @param value the value to set the attribute to
	 */
	public void setDraftOK(boolean value)
	{
		setAttribute(AttributeName.DRAFTOK, value, null);
	}

	/**
	 * (18) get boolean attribute DraftOK
	 *
	 * @return boolean the value of the attribute
	 */
	public boolean getDraftOK()
	{
		return getBoolAttribute(AttributeName.DRAFTOK, null, false);
	}

	/*
	 * --------------------------------------------------------------------- Methods for Attribute Duration ---------------------------------------------------------------------
	 */
	/**
	 * (36) set attribute Duration
	 *
	 * @param value the value to set the attribute to
	 */
	public void setDuration(JDFDuration value)
	{
		setAttribute(AttributeName.DURATION, value, null);
	}

	/**
	 * (20) get JDFDuration attribute Duration
	 *
	 * @return JDFDuration the value of the attribute, null if a the attribute value is not a valid to create a JDFDuration
	 */
	public JDFDuration getDuration()
	{
		final String strAttrName = getAttribute(AttributeName.DURATION, null, null);
		final JDFDuration nPlaceHolder = JDFDuration.createDuration(strAttrName);
		return nPlaceHolder;
	}

	/*
	 * --------------------------------------------------------------------- Methods for Attribute MaxAmount ---------------------------------------------------------------------
	 */
	/**
	 * (36) set attribute MaxAmount
	 *
	 * @param value the value to set the attribute to
	 */
	public void setMaxAmount(double value)
	{
		setAttribute(AttributeName.MAXAMOUNT, value, null);
	}

	/**
	 * (17) get double attribute MaxAmount
	 *
	 * @return double the value of the attribute
	 */
	public double getMaxAmount()
	{
		return getRealAttribute(AttributeName.MAXAMOUNT, null, 0.0);
	}

	/*
	 * --------------------------------------------------------------------- Methods for Attribute MinAmount ---------------------------------------------------------------------
	 */
	/**
	 * (36) set attribute MinAmount
	 *
	 * @param value the value to set the attribute to
	 */
	public void setMinAmount(double value)
	{
		setAttribute(AttributeName.MINAMOUNT, value, null);
	}

	/**
	 * (17) get double attribute MinAmount
	 *
	 * @return double the value of the attribute
	 */
	public double getMinAmount()
	{
		return getRealAttribute(AttributeName.MINAMOUNT, null, 0.0);
	}

	/*
	 * --------------------------------------------------------------------- Methods for Attribute MinLateStatus
	 * ---------------------------------------------------------------------
	 */
	/**
	 * (5) set attribute MinLateStatus
	 *
	 * @param enumVar the enumVar to set the attribute to
	 */
	public void setMinLateStatus(ENodeStatus enumVar)
	{
		setAttribute(AttributeName.MINLATESTATUS, enumVar == null ? null : enumVar.name(), null);
	}

	/**
	 * (9) get attribute MinLateStatus
	 *
	 * @return the value of the attribute
	 */
	public ENodeStatus getEMinLateStatus()
	{
		return ENodeStatus.getEnum(getAttribute(AttributeName.MINLATESTATUS, null, null));
	}

	/*
	 * --------------------------------------------------------------------- Methods for Attribute MinLateStatus
	 * ---------------------------------------------------------------------
	 */
	/**
	 * (5) set attribute MinLateStatus
	 *
	 * @param enumVar the enumVar to set the attribute to
	 * @deprecated use setMinLateStatus(ENodeStatus) based on java.lang.enum instead
	 */
	@Deprecated
	public void setMinLateStatus(EnumNodeStatus enumVar)
	{
		setAttribute(AttributeName.MINLATESTATUS, enumVar == null ? null : enumVar.getName(), null);
	}

	/**
	 * (9) get attribute MinLateStatus
	 *
	 * @return the value of the attribute
	 * @deprecated use ENodeStatus getEMinLateStatus() based on java.lang.enum instead
	 */
	@Deprecated
	public EnumNodeStatus getMinLateStatus()
	{
		return EnumNodeStatus.getEnum(getAttribute(AttributeName.MINLATESTATUS, null, null));
	}

	/*
	 * --------------------------------------------------------------------- Methods for Attribute MinStatus ---------------------------------------------------------------------
	 */
	/**
	 * (5) set attribute MinStatus
	 *
	 * @param enumVar the enumVar to set the attribute to
	 */
	public void setMinStatus(ENodeStatus enumVar)
	{
		setAttribute(AttributeName.MINSTATUS, enumVar == null ? null : enumVar.name(), null);
	}

	/**
	 * (9) get attribute MinStatus
	 *
	 * @return the value of the attribute
	 */
	public ENodeStatus getEMinStatus()
	{
		return ENodeStatus.getEnum(getAttribute(AttributeName.MINSTATUS, null, null));
	}

	/*
	 * --------------------------------------------------------------------- Methods for Attribute MinStatus ---------------------------------------------------------------------
	 */
	/**
	 * (5) set attribute MinStatus
	 *
	 * @param enumVar the enumVar to set the attribute to
	 * @deprecated use setMinStatus(ENodeStatus) based on java.lang.enum instead
	 */
	@Deprecated
	public void setMinStatus(EnumNodeStatus enumVar)
	{
		setAttribute(AttributeName.MINSTATUS, enumVar == null ? null : enumVar.getName(), null);
	}

	/**
	 * (9) get attribute MinStatus
	 *
	 * @return the value of the attribute
	 * @deprecated use ENodeStatus getEMinStatus() based on java.lang.enum instead
	 */
	@Deprecated
	public EnumNodeStatus getMinStatus()
	{
		return EnumNodeStatus.getEnum(getAttribute(AttributeName.MINSTATUS, null, null));
	}

	/*
	 * --------------------------------------------------------------------- Methods for Attribute Orientation ---------------------------------------------------------------------
	 */
	/**
	 * (5) set attribute Orientation
	 *
	 * @param enumVar the enumVar to set the attribute to
	 */
	public void setOrientation(EOrientation enumVar)
	{
		setAttribute(AttributeName.ORIENTATION, enumVar == null ? null : enumVar.name(), null);
	}

	/**
	 * (9) get attribute Orientation
	 *
	 * @return the value of the attribute
	 */
	public EOrientation getEOrientation()
	{
		return EOrientation.getEnum(getAttribute(AttributeName.ORIENTATION, null, null));
	}

	/*
	 * --------------------------------------------------------------------- Methods for Attribute Orientation ---------------------------------------------------------------------
	 */
	/**
	 * (5) set attribute Orientation
	 *
	 * @param enumVar the enumVar to set the attribute to
	 * @deprecated use setOrientation(EOrientation) based on java.lang.enum instead
	 */
	@Deprecated
	public void setOrientation(EnumOrientation enumVar)
	{
		setAttribute(AttributeName.ORIENTATION, enumVar == null ? null : enumVar.getName(), null);
	}

	/**
	 * (9) get attribute Orientation
	 *
	 * @return the value of the attribute
	 * @deprecated use EOrientation getEOrientation() based on java.lang.enum instead
	 */
	@Deprecated
	public EnumOrientation getOrientation()
	{
		return EnumOrientation.getEnum(getAttribute(AttributeName.ORIENTATION, null, null));
	}

	/*
	 * --------------------------------------------------------------------- Methods for Attribute PipePause ---------------------------------------------------------------------
	 */
	/**
	 * (36) set attribute PipePause
	 *
	 * @param value the value to set the attribute to
	 */
	public void setPipePause(double value)
	{
		setAttribute(AttributeName.PIPEPAUSE, value, null);
	}

	/**
	 * (17) get double attribute PipePause
	 *
	 * @return double the value of the attribute
	 */
	public double getPipePause()
	{
		return getRealAttribute(AttributeName.PIPEPAUSE, null, 0.0);
	}

	/*
	 * --------------------------------------------------------------------- Methods for Attribute PipeResume ---------------------------------------------------------------------
	 */
	/**
	 * (36) set attribute PipeResume
	 *
	 * @param value the value to set the attribute to
	 */
	public void setPipeResume(double value)
	{
		setAttribute(AttributeName.PIPERESUME, value, null);
	}

	/**
	 * (17) get double attribute PipeResume
	 *
	 * @return double the value of the attribute
	 */
	public double getPipeResume()
	{
		return getRealAttribute(AttributeName.PIPERESUME, null, 0.0);
	}

	/*
	 * --------------------------------------------------------------------- Methods for Attribute PipeURL ---------------------------------------------------------------------
	 */
	/**
	 * (36) set attribute PipeURL
	 *
	 * @param value the value to set the attribute to
	 */
	public void setPipeURL(String value)
	{
		setAttribute(AttributeName.PIPEURL, value, null);
	}

	/**
	 * (23) get String attribute PipeURL
	 *
	 * @return the value of the attribute
	 */
	public String getPipeURL()
	{
		return getAttribute(AttributeName.PIPEURL, null, JDFCoreConstants.EMPTYSTRING);
	}

	/*
	 * --------------------------------------------------------------------- Methods for Attribute RemotePipeEndPause
	 * ---------------------------------------------------------------------
	 */
	/**
	 * (36) set attribute RemotePipeEndPause
	 *
	 * @param value the value to set the attribute to
	 */
	public void setRemotePipeEndPause(double value)
	{
		setAttribute(AttributeName.REMOTEPIPEENDPAUSE, value, null);
	}

	/**
	 * (17) get double attribute RemotePipeEndPause
	 *
	 * @return double the value of the attribute
	 */
	public double getRemotePipeEndPause()
	{
		return getRealAttribute(AttributeName.REMOTEPIPEENDPAUSE, null, 0.0);
	}

	/*
	 * --------------------------------------------------------------------- Methods for Attribute RemotePipeEndResume
	 * ---------------------------------------------------------------------
	 */
	/**
	 * (36) set attribute RemotePipeEndResume
	 *
	 * @param value the value to set the attribute to
	 */
	public void setRemotePipeEndResume(double value)
	{
		setAttribute(AttributeName.REMOTEPIPEENDRESUME, value, null);
	}

	/**
	 * (17) get double attribute RemotePipeEndResume
	 *
	 * @return double the value of the attribute
	 */
	public double getRemotePipeEndResume()
	{
		return getRealAttribute(AttributeName.REMOTEPIPEENDRESUME, null, 0.0);
	}

	/*
	 * --------------------------------------------------------------------- Methods for Attribute Start ---------------------------------------------------------------------
	 */
	/**
	 * (11) set attribute Start
	 *
	 * @param value the value to set the attribute to or null
	 */
	public void setStart(JDFDate value)
	{
		JDFDate date = value;
		if (date == null)
		{
			date = new JDFDate();
		}
		setAttribute(AttributeName.START, date.getDateTimeISO(), null);
	}

	/**
	 * (12) get JDFDate attribute Start
	 *
	 * @return JDFDate the value of the attribute
	 */
	public JDFDate getStart()
	{
		final String str = getAttribute(AttributeName.START, null, null);
		final JDFDate ret = JDFDate.createDate(str);
		return ret;
	}

	/*
	 * --------------------------------------------------------------------- Methods for Attribute StartOffset ---------------------------------------------------------------------
	 */
	/**
	 * (36) set attribute StartOffset
	 *
	 * @param value the value to set the attribute to
	 */
	public void setStartOffset(JDFDuration value)
	{
		setAttribute(AttributeName.STARTOFFSET, value, null);
	}

	/**
	 * (20) get JDFDuration attribute StartOffset
	 *
	 * @return JDFDuration the value of the attribute, null if a the attribute value is not a valid to create a JDFDuration
	 */
	public JDFDuration getStartOffset()
	{
		final String strAttrName = getAttribute(AttributeName.STARTOFFSET, null, null);
		final JDFDuration nPlaceHolder = JDFDuration.createDuration(strAttrName);
		return nPlaceHolder;
	}

	/*
	 * --------------------------------------------------------------------- Methods for Attribute Transformation
	 * ---------------------------------------------------------------------
	 */
	/**
	 * (36) set attribute Transformation
	 *
	 * @param value the value to set the attribute to
	 */
	public void setTransformation(JDFMatrix value)
	{
		setAttribute(AttributeName.TRANSFORMATION, value, null);
	}

	/**
	 * (20) get JDFMatrix attribute Transformation
	 *
	 * @return JDFMatrix the value of the attribute, null if a the attribute value is not a valid to create a JDFMatrix
	 */
	public JDFMatrix getTransformation()
	{
		final String strAttrName = getAttribute(AttributeName.TRANSFORMATION, null, null);
		final JDFMatrix nPlaceHolder = JDFMatrix.createMatrix(strAttrName);
		return nPlaceHolder;
	}

	/*
	 * *********************************************************************** Element getter / setter ***********************************************************************
	 */

	/**
	 * (24) const get element Lot
	 *
	 * @return JDFLot the element
	 */
	public JDFLot getLot()
	{
		return (JDFLot) getElement(ElementName.LOT, null, 0);
	}

	/**
	 * (25) getCreateLot
	 * 
	 * @return JDFLot the element
	 */
	public JDFLot getCreateLot()
	{
		return (JDFLot) getCreateElement_JDFElement(ElementName.LOT, null, 0);
	}

	/**
	 * (26) getCreateLot
	 * 
	 * @param iSkip number of elements to skip
	 * @return JDFLot the element
	 */
	public JDFLot getCreateLot(int iSkip)
	{
		return (JDFLot) getCreateElement_JDFElement(ElementName.LOT, null, iSkip);
	}

	/**
	 * (27) const get element Lot
	 *
	 * @param iSkip number of elements to skip
	 * @return JDFLot the element default is getLot(0)
	 */
	public JDFLot getLot(int iSkip)
	{
		return (JDFLot) getElement(ElementName.LOT, null, iSkip);
	}

	/**
	 * Get all Lot from the current element
	 * 
	 * @return Collection<JDFLot>, null if none are available
	 */
	public Collection<JDFLot> getAllLot()
	{
		return getChildArrayByClass(JDFLot.class, false, 0);
	}

	/**
	 * (30) append element Lot
	 *
	 * @return JDFLot the element
	 */
	public JDFLot appendLot()
	{
		return (JDFLot) appendElement(ElementName.LOT, null);
	}

}
