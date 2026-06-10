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
import org.cip4.jdflib.core.JDFElement;
import org.cip4.jdflib.core.JDFResourceLink;
import org.cip4.jdflib.core.JDFResourceLink.EnumUsage;
import org.cip4.jdflib.datatypes.JDFIntegerList;
import org.cip4.jdflib.datatypes.JDFMatrix;
import org.cip4.jdflib.pool.JDFAmountPool;
import org.cip4.jdflib.resource.JDFResource;
import org.cip4.jdflib.resource.process.JDFLot;
import org.cip4.jdflib.util.JDFDate;
import org.cip4.jdflib.util.JDFDuration;
import org.cip4.jdflib.util.JavaEnumUtil;

/**
 ***************************************************************************** class JDFAutoResourceLink : public JDFElement
 */

public abstract class JDFAutoResourceLink extends JDFElement
{

	private static final long serialVersionUID = 1L;

	private static AtrInfoTable[] atrInfoTable = new AtrInfoTable[26];
	static
	{
		atrInfoTable[0] = new AtrInfoTable(AttributeName.ACTUALAMOUNT, 0x3333333331l, AttributeInfo.EnumAttributeType.double_, null, null);
		atrInfoTable[1] = new AtrInfoTable(AttributeName.AMOUNT, 0x3333333333l, AttributeInfo.EnumAttributeType.double_, null, null);
		atrInfoTable[2] = new AtrInfoTable(AttributeName.COMBINEDPROCESSINDEX, 0x3333333331l, AttributeInfo.EnumAttributeType.IntegerList, null, null);
		atrInfoTable[3] = new AtrInfoTable(AttributeName.COMBINEDPROCESSTYPE, 0x4444444433l, AttributeInfo.EnumAttributeType.NMTOKEN, null, null);
		atrInfoTable[4] = new AtrInfoTable(AttributeName.DRAFTOK, 0x4444444333l, AttributeInfo.EnumAttributeType.boolean_, null, null);
		atrInfoTable[5] = new AtrInfoTable(AttributeName.DURATION, 0x3333333333l, AttributeInfo.EnumAttributeType.duration, null, null);
		atrInfoTable[6] = new AtrInfoTable(AttributeName.MAXAMOUNT, 0x3333333111l, AttributeInfo.EnumAttributeType.double_, null, null);
		atrInfoTable[7] = new AtrInfoTable(AttributeName.MINAMOUNT, 0x3333333111l, AttributeInfo.EnumAttributeType.double_, null, null);
		atrInfoTable[8] = new AtrInfoTable(AttributeName.MINLATESTATUS, 0x3333333111l, AttributeInfo.EnumAttributeType.enumeration,
				JavaEnumUtil.getEnum(JDFResource.EnumResStatus.class, 0), null);
		atrInfoTable[9] = new AtrInfoTable(AttributeName.MINSTATUS, 0x3333333111l, AttributeInfo.EnumAttributeType.enumeration,
				JavaEnumUtil.getEnum(JDFResource.EnumResStatus.class, 0), null);
		atrInfoTable[10] = new AtrInfoTable(AttributeName.ORIENTATION, 0x3333333331l, AttributeInfo.EnumAttributeType.enumeration,
				JavaEnumUtil.getEnum(EnumOrientation.class, 0), null);
		atrInfoTable[11] = new AtrInfoTable(AttributeName.PIPEPARTIDKEYS, 0x4444333333l, AttributeInfo.EnumAttributeType.enumerations,
				JavaEnumUtil.getEnum(EnumPipePartIDKeys.class, 0), null);
		atrInfoTable[12] = new AtrInfoTable(AttributeName.PIPEPAUSE, 0x3333333333l, AttributeInfo.EnumAttributeType.double_, null, null);
		atrInfoTable[13] = new AtrInfoTable(AttributeName.PIPEPROTOCOL, 0x4444433331l, AttributeInfo.EnumAttributeType.NMTOKEN, null, null);
		atrInfoTable[14] = new AtrInfoTable(AttributeName.PIPERESUME, 0x3333333333l, AttributeInfo.EnumAttributeType.double_, null, null);
		atrInfoTable[15] = new AtrInfoTable(AttributeName.PIPEURL, 0x3333333333l, AttributeInfo.EnumAttributeType.URL, null, null);
		atrInfoTable[16] = new AtrInfoTable(AttributeName.PROCESSUSAGE, 0x3333333333l, AttributeInfo.EnumAttributeType.string, null, null);
		atrInfoTable[17] = new AtrInfoTable(AttributeName.RECOMMENDATION, 0x4444444433l, AttributeInfo.EnumAttributeType.boolean_, null, null);
		atrInfoTable[18] = new AtrInfoTable(AttributeName.REMOTEPIPEENDPAUSE, 0x4444433333l, AttributeInfo.EnumAttributeType.double_, null, null);
		atrInfoTable[19] = new AtrInfoTable(AttributeName.REMOTEPIPEENDRESUME, 0x4444433333l, AttributeInfo.EnumAttributeType.double_, null, null);
		atrInfoTable[20] = new AtrInfoTable(AttributeName.RREF, 0x2222222222l, AttributeInfo.EnumAttributeType.IDREF, null, null);
		atrInfoTable[21] = new AtrInfoTable(AttributeName.RSUBREF, 0x4444444433l, AttributeInfo.EnumAttributeType.IDREF, null, null);
		atrInfoTable[22] = new AtrInfoTable(AttributeName.START, 0x3333333333l, AttributeInfo.EnumAttributeType.dateTime, null, null);
		atrInfoTable[23] = new AtrInfoTable(AttributeName.STARTOFFSET, 0x3333333333l, AttributeInfo.EnumAttributeType.duration, null, null);
		atrInfoTable[24] = new AtrInfoTable(AttributeName.USAGE, 0x2222222222l, AttributeInfo.EnumAttributeType.enumeration,
				JavaEnumUtil.getEnum(JDFResourceLink.EnumUsage.class, 0), null);
		atrInfoTable[25] = new AtrInfoTable(AttributeName.TRANSFORMATION, 0x3333333331l, AttributeInfo.EnumAttributeType.matrix, null, null);
	}

	@Override
	protected AttributeInfo getTheAttributeInfo()
	{
		return super.getTheAttributeInfo().updateReplace(atrInfoTable);
	}

	private static ElemInfoTable[] elemInfoTable = new ElemInfoTable[2];
	static
	{
		elemInfoTable[0] = new ElemInfoTable(ElementName.AMOUNTPOOL, 0x6666666661l);
		elemInfoTable[1] = new ElemInfoTable(ElementName.LOT, 0x4444333111l);
	}

	@Override
	protected ElementInfo getTheElementInfo()
	{
		return super.getTheElementInfo().updateReplace(elemInfoTable);
	}

	/**
	 * Constructor for JDFAutoResourceLink
	 *
	 * @param myOwnerDocument
	 * @param qualifiedName
	 */
	protected JDFAutoResourceLink(final CoreDocumentImpl myOwnerDocument, final String qualifiedName)
	{
		super(myOwnerDocument, qualifiedName);
	}

	/**
	 * Constructor for JDFAutoResourceLink
	 *
	 * @param myOwnerDocument
	 * @param myNamespaceURI
	 * @param qualifiedName
	 */
	protected JDFAutoResourceLink(final CoreDocumentImpl myOwnerDocument, final String myNamespaceURI, final String qualifiedName)
	{
		super(myOwnerDocument, myNamespaceURI, qualifiedName);
	}

	/**
	 * Constructor for JDFAutoResourceLink
	 *
	 * @param myOwnerDocument
	 * @param myNamespaceURI
	 * @param qualifiedName
	 * @param myLocalName
	 */
	protected JDFAutoResourceLink(final CoreDocumentImpl myOwnerDocument, final String myNamespaceURI, final String qualifiedName, final String myLocalName)
	{
		super(myOwnerDocument, myNamespaceURI, qualifiedName, myLocalName);
	}

	/**
	 * Enumeration strings for numMinLateStatus
	 */

	public enum EnumMinLateStatus
	{
		Incomplete, Rejected, Unavailable, InUse, Draft, Complete, Available;

		public static EnumMinLateStatus getEnum(final String val)
		{
			return JavaEnumUtil.getEnumIgnoreCase(EnumMinLateStatus.class, val, null);
		}
	}

	/**
	 * Enumeration strings for numMinStatus
	 */

	public enum EnumMinStatus
	{
		Incomplete, Rejected, Unavailable, InUse, Draft, Complete, Available;

		public static EnumMinStatus getEnum(final String val)
		{
			return JavaEnumUtil.getEnumIgnoreCase(EnumMinStatus.class, val, null);
		}
	}

	/**
	 * Enumeration strings for numOrientation
	 */

	public enum EnumOrientation
	{
		Rotate0, Rotate90, Rotate180, Rotate270, Flip0, Flip90, Flip180, Flip270;

		public static EnumOrientation getEnum(final String val)
		{
			return JavaEnumUtil.getEnumIgnoreCase(EnumOrientation.class, val, null);
		}
	}

	/**
	 * Enumeration strings for numPipePartIDKeys
	 */

	public enum EnumPipePartIDKeys
	{
		BinderySignatureName, BinderySignaturePaginationIndex, BlockName, BundleItemIndex, CellIndex, Condition, DeliveryUnit0, DeliveryUnit1, DeliveryUnit2, DeliveryUnit3, DeliveryUnit4, DeliveryUnit5, DeliveryUnit6, DeliveryUnit7, DeliveryUnit8, DeliveryUnit9, DocCopies, DocIndex, DocRunIndex, DocSheetIndex, DocTags, Edition, EditionVersion, FountainNumber, ItemNames, LayerIDs, Location, Metadata0, Metadata1, Metadata2, Metadata3, Metadata4, Metadata5, Metadata6, Metadata7, Metadata8, Metadata9, Option, PageNumber, PageTags, PlateLayout, PartVersion, PreflightRule, ProductPart, PreviewType, RibbonName, Run, RunIndex, RunPage, RunTags, RunSet, SectionIndex, Separation, SetCopies, SetDocIndex, SetIndex, SetRunIndex, SetSheetIndex, SetTags, SheetIndex, SheetName, Side, SignatureName, StationName, SubRun, TileID, WebName, WebProduct, WebSetup;

		public static EnumPipePartIDKeys getEnum(final String val)
		{
			return JavaEnumUtil.getEnumIgnoreCase(EnumPipePartIDKeys.class, val, null);
		}
	}/*
		 * ************************************************************************
		 * Attribute getter / setter
		 * ************************************************************************
		 */

	/*
	 * ---------------------------------------------------------------------
	 * Methods for Attribute ActualAmount
	 * ---------------------------------------------------------------------
	 */
	/**
	 * (36) set attribute ActualAmount
	 *
	 * @param value the value to set the attribute to
	 */
	public void setActualAmount(final double value)
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
	 * ---------------------------------------------------------------------
	 * Methods for Attribute Amount
	 * ---------------------------------------------------------------------
	 */
	/**
	 * (36) set attribute Amount
	 *
	 * @param value the value to set the attribute to
	 */
	public void setAmount(final double value)
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
	 * ---------------------------------------------------------------------
	 * Methods for Attribute CombinedProcessIndex
	 * ---------------------------------------------------------------------
	 */
	/**
	 * (36) set attribute CombinedProcessIndex
	 *
	 * @param value the value to set the attribute to
	 */
	public void setCombinedProcessIndex(final JDFIntegerList value)
	{
		setAttribute(AttributeName.COMBINEDPROCESSINDEX, value, null);
	}

	/**
	 * (20) get JDFIntegerList attribute CombinedProcessIndex
	 *
	 * @return JDFIntegerList the value of the attribute, null if a the
	 *         attribute value is not a valid to create a JDFIntegerList
	 */
	public JDFIntegerList getCombinedProcessIndex()
	{
		final String strAttrName = getAttribute(AttributeName.COMBINEDPROCESSINDEX, null, null);
		final JDFIntegerList nPlaceHolder = JDFIntegerList.createIntegerList(strAttrName);
		return nPlaceHolder;
	}

	/*
	 * ---------------------------------------------------------------------
	 * Methods for Attribute CombinedProcessType
	 * ---------------------------------------------------------------------
	 */
	/**
	 * (36) set attribute CombinedProcessType
	 *
	 * @param value the value to set the attribute to
	 */
	public void setCombinedProcessType(final String value)
	{
		setAttribute(AttributeName.COMBINEDPROCESSTYPE, value, null);
	}

	/**
	 * (23) get String attribute CombinedProcessType
	 *
	 * @return the value of the attribute
	 */
	public String getCombinedProcessType()
	{
		return getAttribute(AttributeName.COMBINEDPROCESSTYPE, null, JDFCoreConstants.EMPTYSTRING);
	}

	/*
	 * ---------------------------------------------------------------------
	 * Methods for Attribute DraftOK
	 * ---------------------------------------------------------------------
	 */
	/**
	 * (36) set attribute DraftOK
	 *
	 * @param value the value to set the attribute to
	 */
	public void setDraftOK(final boolean value)
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
	 * ---------------------------------------------------------------------
	 * Methods for Attribute Duration
	 * ---------------------------------------------------------------------
	 */
	/**
	 * (36) set attribute Duration
	 *
	 * @param value the value to set the attribute to
	 */
	public void setDuration(final JDFDuration value)
	{
		setAttribute(AttributeName.DURATION, value, null);
	}

	/**
	 * (20) get JDFDuration attribute Duration
	 *
	 * @return JDFDuration the value of the attribute, null if a the
	 *         attribute value is not a valid to create a JDFDuration
	 */
	public JDFDuration getDuration()
	{
		final String strAttrName = getAttribute(AttributeName.DURATION, null, null);
		final JDFDuration nPlaceHolder = JDFDuration.createDuration(strAttrName);
		return nPlaceHolder;
	}

	/*
	 * ---------------------------------------------------------------------
	 * Methods for Attribute MaxAmount
	 * ---------------------------------------------------------------------
	 */
	/**
	 * (36) set attribute MaxAmount
	 *
	 * @param value the value to set the attribute to
	 */
	public void setMaxAmount(final double value)
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
	 * ---------------------------------------------------------------------
	 * Methods for Attribute MinAmount
	 * ---------------------------------------------------------------------
	 */
	/**
	 * (36) set attribute MinAmount
	 *
	 * @param value the value to set the attribute to
	 */
	public void setMinAmount(final double value)
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
	 * ---------------------------------------------------------------------
	 * Methods for Attribute MinLateStatus
	 * ---------------------------------------------------------------------
	 */
	/**
	 * (5) set attribute MinLateStatus
	 *
	 * @param enumVar the enumVar to set the attribute to
	 */
	public void setMinLateStatus(final JDFResource.EnumResStatus enumVar)
	{
		setAttribute(AttributeName.MINLATESTATUS, JavaEnumUtil.getName(enumVar), null);
	}

	/**
	 * (9) get attribute MinLateStatus
	 *
	 * @return the value of the attribute
	 */
	public JDFResource.EnumResStatus getMinLateStatus()
	{
		return JDFResource.EnumResStatus.getEnum(getAttribute(AttributeName.MINLATESTATUS, null, null));
	}

	/*
	 * ---------------------------------------------------------------------
	 * Methods for Attribute MinStatus
	 * ---------------------------------------------------------------------
	 */
	/**
	 * (5) set attribute MinStatus
	 *
	 * @param enumVar the enumVar to set the attribute to
	 */
	public void setMinStatus(final JDFResource.EnumResStatus enumVar)
	{
		setAttribute(AttributeName.MINSTATUS, JavaEnumUtil.getName(enumVar), null);
	}

	/**
	 * (9) get attribute MinStatus
	 *
	 * @return the value of the attribute
	 */
	public JDFResource.EnumResStatus getMinStatus()
	{
		return JDFResource.EnumResStatus.getEnum(getAttribute(AttributeName.MINSTATUS, null, null));
	}

	/*
	 * ---------------------------------------------------------------------
	 * Methods for Attribute Orientation
	 * ---------------------------------------------------------------------
	 */
	/**
	 * (5) set attribute Orientation
	 *
	 * @param enumVar the enumVar to set the attribute to
	 */
	public void setOrientation(final EnumOrientation enumVar)
	{
		setAttribute(AttributeName.ORIENTATION, JavaEnumUtil.getName(enumVar), null);
	}

	/**
	 * (9) get attribute Orientation
	 *
	 * @return the value of the attribute
	 */
	public EnumOrientation getOrientation()
	{
		return EnumOrientation.getEnum(getAttribute(AttributeName.ORIENTATION, null, null));
	}

	/*
	 * ---------------------------------------------------------------------
	 * Methods for Attribute PipePartIDKeys
	 * ---------------------------------------------------------------------
	 */
	/**
	 * (5.2) set attribute PipePartIDKeys
	 *
	 * @param v List of the enumeration values
	 */
	public void setPipePartIDKeys(final List<EnumPipePartIDKeys> v)
	{
		setEnumsAttribute(AttributeName.PIPEPARTIDKEYS, v, null);
	}

	/**
	 * (9.2) get PipePartIDKeys attribute PipePartIDKeys
	 *
	 * @return Vector of the enumerations
	 */
	public List<EnumPipePartIDKeys> getPipePartIDKeys()
	{
		return getEnumerationsAttribute(AttributeName.PIPEPARTIDKEYS, null, EnumPipePartIDKeys.class);
	}

	/*
	 * ---------------------------------------------------------------------
	 * Methods for Attribute PipePause
	 * ---------------------------------------------------------------------
	 */
	/**
	 * (36) set attribute PipePause
	 *
	 * @param value the value to set the attribute to
	 */
	public void setPipePause(final double value)
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
	 * ---------------------------------------------------------------------
	 * Methods for Attribute PipeProtocol
	 * ---------------------------------------------------------------------
	 */
	/**
	 * (36) set attribute PipeProtocol
	 *
	 * @param value the value to set the attribute to
	 */
	public void setPipeProtocol(final String value)
	{
		setAttribute(AttributeName.PIPEPROTOCOL, value, null);
	}

	/**
	 * (23) get String attribute PipeProtocol
	 *
	 * @return the value of the attribute
	 */
	public String getPipeProtocol()
	{
		return getAttribute(AttributeName.PIPEPROTOCOL, null, JDFCoreConstants.EMPTYSTRING);
	}

	/*
	 * ---------------------------------------------------------------------
	 * Methods for Attribute PipeResume
	 * ---------------------------------------------------------------------
	 */
	/**
	 * (36) set attribute PipeResume
	 *
	 * @param value the value to set the attribute to
	 */
	public void setPipeResume(final double value)
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
	 * ---------------------------------------------------------------------
	 * Methods for Attribute PipeURL
	 * ---------------------------------------------------------------------
	 */
	/**
	 * (36) set attribute PipeURL
	 *
	 * @param value the value to set the attribute to
	 */
	public void setPipeURL(final String value)
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
	 * ---------------------------------------------------------------------
	 * Methods for Attribute ProcessUsage
	 * ---------------------------------------------------------------------
	 */
	/**
	 * (36) set attribute ProcessUsage
	 *
	 * @param value the value to set the attribute to
	 */
	public void setProcessUsage(final String value)
	{
		setAttribute(AttributeName.PROCESSUSAGE, value, null);
	}

	/**
	 * (23) get String attribute ProcessUsage
	 *
	 * @return the value of the attribute
	 */
	public String getProcessUsage()
	{
		return getAttribute(AttributeName.PROCESSUSAGE, null, JDFCoreConstants.EMPTYSTRING);
	}

	/*
	 * ---------------------------------------------------------------------
	 * Methods for Attribute Recommendation
	 * ---------------------------------------------------------------------
	 */
	/**
	 * (36) set attribute Recommendation
	 *
	 * @param value the value to set the attribute to
	 */
	public void setRecommendation(final boolean value)
	{
		setAttribute(AttributeName.RECOMMENDATION, value, null);
	}

	/**
	 * (18) get boolean attribute Recommendation
	 *
	 * @return boolean the value of the attribute
	 */
	public boolean getRecommendation()
	{
		return getBoolAttribute(AttributeName.RECOMMENDATION, null, false);
	}

	/*
	 * ---------------------------------------------------------------------
	 * Methods for Attribute RemotePipeEndPause
	 * ---------------------------------------------------------------------
	 */
	/**
	 * (36) set attribute RemotePipeEndPause
	 *
	 * @param value the value to set the attribute to
	 */
	public void setRemotePipeEndPause(final double value)
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
	 * ---------------------------------------------------------------------
	 * Methods for Attribute RemotePipeEndResume
	 * ---------------------------------------------------------------------
	 */
	/**
	 * (36) set attribute RemotePipeEndResume
	 *
	 * @param value the value to set the attribute to
	 */
	public void setRemotePipeEndResume(final double value)
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
	 * ---------------------------------------------------------------------
	 * Methods for Attribute rRef
	 * ---------------------------------------------------------------------
	 */
	/**
	 * (36) set attribute rRef
	 *
	 * @param value the value to set the attribute to
	 */
	public void setrRef(final String value)
	{
		setAttribute(AttributeName.RREF, value, null);
	}

	/**
	 * (23) get String attribute rRef
	 *
	 * @return the value of the attribute
	 */
	public String getrRef()
	{
		return getAttribute(AttributeName.RREF, null, JDFCoreConstants.EMPTYSTRING);
	}

	/*
	 * ---------------------------------------------------------------------
	 * Methods for Attribute rSubRef
	 * ---------------------------------------------------------------------
	 */
	/**
	 * (36) set attribute rSubRef
	 *
	 * @param value the value to set the attribute to
	 */
	public void setrSubRef(final String value)
	{
		setAttribute(AttributeName.RSUBREF, value, null);
	}

	/**
	 * (23) get String attribute rSubRef
	 *
	 * @return the value of the attribute
	 */
	public String getrSubRef()
	{
		return getAttribute(AttributeName.RSUBREF, null, JDFCoreConstants.EMPTYSTRING);
	}

	/*
	 * ---------------------------------------------------------------------
	 * Methods for Attribute Start
	 * ---------------------------------------------------------------------
	 */
	/**
	 * (11) set attribute Start
	 *
	 * @param value the value to set the attribute to or null
	 */
	public void setStart(final JDFDate value)
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
	 * ---------------------------------------------------------------------
	 * Methods for Attribute StartOffset
	 * ---------------------------------------------------------------------
	 */
	/**
	 * (36) set attribute StartOffset
	 *
	 * @param value the value to set the attribute to
	 */
	public void setStartOffset(final JDFDuration value)
	{
		setAttribute(AttributeName.STARTOFFSET, value, null);
	}

	/**
	 * (20) get JDFDuration attribute StartOffset
	 *
	 * @return JDFDuration the value of the attribute, null if a the
	 *         attribute value is not a valid to create a JDFDuration
	 */
	public JDFDuration getStartOffset()
	{
		final String strAttrName = getAttribute(AttributeName.STARTOFFSET, null, null);
		final JDFDuration nPlaceHolder = JDFDuration.createDuration(strAttrName);
		return nPlaceHolder;
	}

	/*
	 * ---------------------------------------------------------------------
	 * Methods for Attribute Usage
	 * ---------------------------------------------------------------------
	 */
	/**
	 * (5) set attribute Usage
	 *
	 * @param enumVar the enumVar to set the attribute to
	 */
	public void setUsage(final EnumUsage enumVar)
	{
		setAttribute(AttributeName.USAGE, JavaEnumUtil.getName(enumVar), null);
	}

	/**
	 * (9) get attribute Usage
	 *
	 * @return the value of the attribute
	 */
	public EnumUsage getUsage()
	{
		return EnumUsage.getEnum(getAttribute(AttributeName.USAGE, null, null));
	}

	/*
	 * ---------------------------------------------------------------------
	 * Methods for Attribute Transformation
	 * ---------------------------------------------------------------------
	 */
	/**
	 * (36) set attribute Transformation
	 *
	 * @param value the value to set the attribute to
	 */
	public void setTransformation(final JDFMatrix value)
	{
		setAttribute(AttributeName.TRANSFORMATION, value, null);
	}

	/**
	 * (20) get JDFMatrix attribute Transformation
	 *
	 * @return JDFMatrix the value of the attribute, null if a the
	 *         attribute value is not a valid to create a JDFMatrix
	 */
	public JDFMatrix getTransformation()
	{
		final String strAttrName = getAttribute(AttributeName.TRANSFORMATION, null, null);
		final JDFMatrix nPlaceHolder = JDFMatrix.createMatrix(strAttrName);
		return nPlaceHolder;
	}

	/*
	 * ***********************************************************************
	 * Element getter / setter
	 * ***********************************************************************
	 */

	/**
	 * (24) const get element AmountPool
	 *
	 * @return JDFAmountPool the element
	 */
	public JDFAmountPool getAmountPool()
	{
		return (JDFAmountPool) getElement(ElementName.AMOUNTPOOL, null, 0);
	}

	/**
	 * (25) getCreateAmountPool
	 * 
	 * @return JDFAmountPool the element
	 */
	public JDFAmountPool getCreateAmountPool()
	{
		return (JDFAmountPool) getCreateElement_JDFElement(ElementName.AMOUNTPOOL, null, 0);
	}

	/**
	 * (29) append element AmountPool
	 *
	 * @return JDFAmountPool the element
	 * @ if the element already exists
	 */
	public JDFAmountPool appendAmountPool()
	{
		return (JDFAmountPool) appendElementN(ElementName.AMOUNTPOOL, 1, null);
	}

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
	public JDFLot getCreateLot(final int iSkip)
	{
		return (JDFLot) getCreateElement_JDFElement(ElementName.LOT, null, iSkip);
	}

	/**
	 * (27) const get element Lot
	 *
	 * @param iSkip number of elements to skip
	 * @return JDFLot the element
	 *         default is getLot(0)
	 */
	public JDFLot getLot(final int iSkip)
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
