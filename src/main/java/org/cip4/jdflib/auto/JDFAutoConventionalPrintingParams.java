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

import org.apache.xerces.dom.CoreDocumentImpl;
import org.cip4.jdflib.core.AtrInfoTable;
import org.cip4.jdflib.core.AttributeInfo;
import org.cip4.jdflib.core.AttributeName;
import org.cip4.jdflib.core.ElemInfoTable;
import org.cip4.jdflib.core.ElementInfo;
import org.cip4.jdflib.core.ElementName;
import org.cip4.jdflib.core.JDFCoreConstants;
import org.cip4.jdflib.datatypes.JDFIntegerRangeList;
import org.cip4.jdflib.resource.JDFResource;
import org.cip4.jdflib.resource.process.JDFApprovalParams;
import org.cip4.jdflib.resource.process.prepress.JDFInk;
import org.cip4.jdflib.util.JavaEnumUtil;

/**
 ***************************************************************************** class JDFAutoConventionalPrintingParams : public JDFResource
 */

public abstract class JDFAutoConventionalPrintingParams extends JDFResource
{

	private static final long serialVersionUID = 1L;

	private static AtrInfoTable[] atrInfoTable = new AtrInfoTable[20];
	static
	{
		atrInfoTable[0] = new AtrInfoTable(AttributeName.DIRECTPROOF, 0x3333333333l, AttributeInfo.EnumAttributeType.boolean_, null, "false");
		atrInfoTable[1] = new AtrInfoTable(AttributeName.DRYING, 0x3333333333l, AttributeInfo.EnumAttributeType.enumeration,
				JavaEnumUtil.getEnum(EnumDrying.class, 0), null);
		atrInfoTable[2] = new AtrInfoTable(AttributeName.FEEDSHEETLAY, 0x3111111111l, AttributeInfo.EnumAttributeType.enumeration,
				JavaEnumUtil.getEnum(EnumFeedSheetLay.class, 0), null);
		atrInfoTable[3] = new AtrInfoTable(AttributeName.FIRSTSURFACE, 0x3333333333l, AttributeInfo.EnumAttributeType.enumeration,
				JavaEnumUtil.getEnum(EnumFirstSurface.class, 0), null);
		atrInfoTable[4] = new AtrInfoTable(AttributeName.FOUNTAINSOLUTION, 0x3333333333l, AttributeInfo.EnumAttributeType.enumeration,
				JavaEnumUtil.getEnum(EnumFountainSolution.class, 0), null);
		atrInfoTable[5] = new AtrInfoTable(AttributeName.MEDIALOCATION, 0x3333333333l, AttributeInfo.EnumAttributeType.string, null, null);
		atrInfoTable[6] = new AtrInfoTable(AttributeName.MODULEAVAILABLEINDEX, 0x4444443331l, AttributeInfo.EnumAttributeType.IntegerRangeList, null, null);
		atrInfoTable[7] = new AtrInfoTable(AttributeName.MODULEDRYING, 0x3333333333l, AttributeInfo.EnumAttributeType.enumeration,
				JavaEnumUtil.getEnum(EnumModuleDrying.class, 0), null);
		atrInfoTable[8] = new AtrInfoTable(AttributeName.MODULEINDEX, 0x4444443333l, AttributeInfo.EnumAttributeType.IntegerRangeList, null, null);
		atrInfoTable[9] = new AtrInfoTable(AttributeName.NONPRINTABLEMARGINBOTTOM, 0x3333333333l, AttributeInfo.EnumAttributeType.double_, null, null);
		atrInfoTable[10] = new AtrInfoTable(AttributeName.NONPRINTABLEMARGINLEFT, 0x3333333111l, AttributeInfo.EnumAttributeType.double_, null, null);
		atrInfoTable[11] = new AtrInfoTable(AttributeName.NONPRINTABLEMARGINRIGHT, 0x3333333111l, AttributeInfo.EnumAttributeType.double_, null, null);
		atrInfoTable[12] = new AtrInfoTable(AttributeName.NONPRINTABLEMARGINTOP, 0x3333333111l, AttributeInfo.EnumAttributeType.double_, null, null);
		atrInfoTable[13] = new AtrInfoTable(AttributeName.PERFECTINGMODULE, 0x3333333331l, AttributeInfo.EnumAttributeType.integer, null, null);
		atrInfoTable[14] = new AtrInfoTable(AttributeName.POWDER, 0x3333333333l, AttributeInfo.EnumAttributeType.double_, null, null);
		atrInfoTable[15] = new AtrInfoTable(AttributeName.PRINTINGTYPE, 0x3333333333l, AttributeInfo.EnumAttributeType.enumeration,
				JavaEnumUtil.getEnum(EnumPrintingType.class, 0), null);
		atrInfoTable[16] = new AtrInfoTable(AttributeName.PRINTINGTECHNOLOGY, 0x4444431111l, AttributeInfo.EnumAttributeType.enumeration,
				JavaEnumUtil.getEnum(EnumPrintingTechnology.class, 0), null);
		atrInfoTable[17] = new AtrInfoTable(AttributeName.SHEETLAY, 0x3333333333l, AttributeInfo.EnumAttributeType.enumeration,
				JavaEnumUtil.getEnum(EnumSheetLay.class, 0), null);
		atrInfoTable[18] = new AtrInfoTable(AttributeName.SPEED, 0x4443333333l, AttributeInfo.EnumAttributeType.double_, null, null);
		atrInfoTable[19] = new AtrInfoTable(AttributeName.WORKSTYLE, 0x3333333333l, AttributeInfo.EnumAttributeType.enumeration,
				JavaEnumUtil.getEnum(EnumWorkStyle.class, 0), null);
	}

	@Override
	protected AttributeInfo getTheAttributeInfo()
	{
		return super.getTheAttributeInfo().updateReplace(atrInfoTable);
	}

	private static ElemInfoTable[] elemInfoTable = new ElemInfoTable[2];
	static
	{
		elemInfoTable[0] = new ElemInfoTable(ElementName.INK, 0x4444443333l);
		elemInfoTable[1] = new ElemInfoTable(ElementName.APPROVALPARAMS, 0x6666666611l);
	}

	@Override
	protected ElementInfo getTheElementInfo()
	{
		return super.getTheElementInfo().updateReplace(elemInfoTable);
	}

	/**
	 * Constructor for JDFAutoConventionalPrintingParams
	 *
	 * @param myOwnerDocument
	 * @param qualifiedName
	 */
	protected JDFAutoConventionalPrintingParams(final CoreDocumentImpl myOwnerDocument, final String qualifiedName)
	{
		super(myOwnerDocument, qualifiedName);
	}

	/**
	 * Constructor for JDFAutoConventionalPrintingParams
	 *
	 * @param myOwnerDocument
	 * @param myNamespaceURI
	 * @param qualifiedName
	 */
	protected JDFAutoConventionalPrintingParams(final CoreDocumentImpl myOwnerDocument, final String myNamespaceURI, final String qualifiedName)
	{
		super(myOwnerDocument, myNamespaceURI, qualifiedName);
	}

	/**
	 * Constructor for JDFAutoConventionalPrintingParams
	 *
	 * @param myOwnerDocument
	 * @param myNamespaceURI
	 * @param qualifiedName
	 * @param myLocalName
	 */
	protected JDFAutoConventionalPrintingParams(final CoreDocumentImpl myOwnerDocument, final String myNamespaceURI, final String qualifiedName, final String myLocalName)
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
	 * Enumeration strings for numDrying
	 */

	public enum EnumDrying
	{
		UV, Heatset, IR, On, Off;

		public static EnumDrying getEnum(final String val)
		{
			return JavaEnumUtil.getEnumIgnoreCase(EnumDrying.class, val, null);
		}
	}

	/**
	 * Enumeration strings for numFeedSheetLay
	 */

	public enum EnumFeedSheetLay
	{
		Leading, Trailing;

		public static EnumFeedSheetLay getEnum(final String val)
		{
			return JavaEnumUtil.getEnumIgnoreCase(EnumFeedSheetLay.class, val, null);
		}
	}

	/**
	 * Enumeration strings for numFirstSurface
	 */

	public enum EnumFirstSurface
	{
		Either, Front, Back;

		public static EnumFirstSurface getEnum(final String val)
		{
			return JavaEnumUtil.getEnumIgnoreCase(EnumFirstSurface.class, val, null);
		}
	}

	/**
	 * Enumeration strings for numFountainSolution
	 */

	public enum EnumFountainSolution
	{
		On, Off;

		public static EnumFountainSolution getEnum(final String val)
		{
			return JavaEnumUtil.getEnumIgnoreCase(EnumFountainSolution.class, val, null);
		}
	}

	/**
	 * Enumeration strings for numModuleDrying
	 */

	public enum EnumModuleDrying
	{
		UV, Heatset, IR, On, Off;

		public static EnumModuleDrying getEnum(final String val)
		{
			return JavaEnumUtil.getEnumIgnoreCase(EnumModuleDrying.class, val, null);
		}
	}

	/**
	 * Enumeration strings for numPrintingType
	 */

	public enum EnumPrintingType
	{
		ContinuousFed, SheetFed, WebFed, WebMultiple, WebSingle;

		public static EnumPrintingType getEnum(final String val)
		{
			return JavaEnumUtil.getEnumIgnoreCase(EnumPrintingType.class, val, null);
		}
	}

	/**
	 * Enumeration strings for numPrintingTechnology
	 */

	public enum EnumPrintingTechnology
	{
		Flexo, Gravure, Offset, Screen;

		public static EnumPrintingTechnology getEnum(final String val)
		{
			return JavaEnumUtil.getEnumIgnoreCase(EnumPrintingTechnology.class, val, null);
		}
	}

	/**
	 * Enumeration strings for numSheetLay
	 */

	public enum EnumSheetLay
	{
		Left, Right, Center;

		public static EnumSheetLay getEnum(final String val)
		{
			return JavaEnumUtil.getEnumIgnoreCase(EnumSheetLay.class, val, null);
		}
	}

	/**
	 * Enumeration strings for numWorkStyle
	 */

	public enum EnumWorkStyle
	{
		Simplex, Perfecting, WorkAndBack, WorkAndTurn, WorkAndTumble, WorkAndTwist;

		public static EnumWorkStyle getEnum(final String val)
		{
			return JavaEnumUtil.getEnumIgnoreCase(EnumWorkStyle.class, val, null);
		}
	}/*
		 * ************************************************************************
		 * Attribute getter / setter
		 * ************************************************************************
		 */

	/*
	 * ---------------------------------------------------------------------
	 * Methods for Attribute DirectProof
	 * ---------------------------------------------------------------------
	 */
	/**
	 * (36) set attribute DirectProof
	 *
	 * @param value the value to set the attribute to
	 */
	public void setDirectProof(final boolean value)
	{
		setAttribute(AttributeName.DIRECTPROOF, value, null);
	}

	/**
	 * (18) get boolean attribute DirectProof
	 *
	 * @return boolean the value of the attribute
	 */
	public boolean getDirectProof()
	{
		return getBoolAttribute(AttributeName.DIRECTPROOF, null, false);
	}

	/*
	 * ---------------------------------------------------------------------
	 * Methods for Attribute Drying
	 * ---------------------------------------------------------------------
	 */
	/**
	 * (5) set attribute Drying
	 *
	 * @param enumVar the enumVar to set the attribute to
	 */
	public void setDrying(final EnumDrying enumVar)
	{
		setAttribute(AttributeName.DRYING, JavaEnumUtil.getName(enumVar), null);
	}

	/**
	 * (9) get attribute Drying
	 *
	 * @return the value of the attribute
	 */
	public EnumDrying getDrying()
	{
		return EnumDrying.getEnum(getAttribute(AttributeName.DRYING, null, null));
	}

	/*
	 * ---------------------------------------------------------------------
	 * Methods for Attribute FeedSheetLay
	 * ---------------------------------------------------------------------
	 */
	/**
	 * (5) set attribute FeedSheetLay
	 *
	 * @param enumVar the enumVar to set the attribute to
	 */
	public void setFeedSheetLay(final EnumFeedSheetLay enumVar)
	{
		setAttribute(AttributeName.FEEDSHEETLAY, JavaEnumUtil.getName(enumVar), null);
	}

	/**
	 * (9) get attribute FeedSheetLay
	 *
	 * @return the value of the attribute
	 */
	public EnumFeedSheetLay getFeedSheetLay()
	{
		return EnumFeedSheetLay.getEnum(getAttribute(AttributeName.FEEDSHEETLAY, null, null));
	}

	/*
	 * ---------------------------------------------------------------------
	 * Methods for Attribute FirstSurface
	 * ---------------------------------------------------------------------
	 */
	/**
	 * (5) set attribute FirstSurface
	 *
	 * @param enumVar the enumVar to set the attribute to
	 */
	public void setFirstSurface(final EnumFirstSurface enumVar)
	{
		setAttribute(AttributeName.FIRSTSURFACE, JavaEnumUtil.getName(enumVar), null);
	}

	/**
	 * (9) get attribute FirstSurface
	 *
	 * @return the value of the attribute
	 */
	public EnumFirstSurface getFirstSurface()
	{
		return EnumFirstSurface.getEnum(getAttribute(AttributeName.FIRSTSURFACE, null, null));
	}

	/*
	 * ---------------------------------------------------------------------
	 * Methods for Attribute FountainSolution
	 * ---------------------------------------------------------------------
	 */
	/**
	 * (5) set attribute FountainSolution
	 *
	 * @param enumVar the enumVar to set the attribute to
	 */
	public void setFountainSolution(final EnumFountainSolution enumVar)
	{
		setAttribute(AttributeName.FOUNTAINSOLUTION, JavaEnumUtil.getName(enumVar), null);
	}

	/**
	 * (9) get attribute FountainSolution
	 *
	 * @return the value of the attribute
	 */
	public EnumFountainSolution getFountainSolution()
	{
		return EnumFountainSolution.getEnum(getAttribute(AttributeName.FOUNTAINSOLUTION, null, null));
	}

	/*
	 * ---------------------------------------------------------------------
	 * Methods for Attribute MediaLocation
	 * ---------------------------------------------------------------------
	 */
	/**
	 * (36) set attribute MediaLocation
	 *
	 * @param value the value to set the attribute to
	 */
	public void setMediaLocation(final String value)
	{
		setAttribute(AttributeName.MEDIALOCATION, value, null);
	}

	/**
	 * (23) get String attribute MediaLocation
	 *
	 * @return the value of the attribute
	 */
	public String getMediaLocation()
	{
		return getAttribute(AttributeName.MEDIALOCATION, null, JDFCoreConstants.EMPTYSTRING);
	}

	/*
	 * ---------------------------------------------------------------------
	 * Methods for Attribute ModuleAvailableIndex
	 * ---------------------------------------------------------------------
	 */
	/**
	 * (36) set attribute ModuleAvailableIndex
	 *
	 * @param value the value to set the attribute to
	 */
	public void setModuleAvailableIndex(final JDFIntegerRangeList value)
	{
		setAttribute(AttributeName.MODULEAVAILABLEINDEX, value, null);
	}

	/**
	 * (20) get JDFIntegerRangeList attribute ModuleAvailableIndex
	 *
	 * @return JDFIntegerRangeList the value of the attribute, null if a the
	 *         attribute value is not a valid to create a JDFIntegerRangeList
	 */
	public JDFIntegerRangeList getModuleAvailableIndex()
	{
		final String strAttrName = getAttribute(AttributeName.MODULEAVAILABLEINDEX, null, null);
		final JDFIntegerRangeList nPlaceHolder = JDFIntegerRangeList.createIntegerRangeList(strAttrName);
		return nPlaceHolder;
	}

	/*
	 * ---------------------------------------------------------------------
	 * Methods for Attribute ModuleDrying
	 * ---------------------------------------------------------------------
	 */
	/**
	 * (5) set attribute ModuleDrying
	 *
	 * @param enumVar the enumVar to set the attribute to
	 */
	public void setModuleDrying(final EnumModuleDrying enumVar)
	{
		setAttribute(AttributeName.MODULEDRYING, JavaEnumUtil.getName(enumVar), null);
	}

	/**
	 * (9) get attribute ModuleDrying
	 *
	 * @return the value of the attribute
	 */
	public EnumModuleDrying getModuleDrying()
	{
		return EnumModuleDrying.getEnum(getAttribute(AttributeName.MODULEDRYING, null, null));
	}

	/*
	 * ---------------------------------------------------------------------
	 * Methods for Attribute ModuleIndex
	 * ---------------------------------------------------------------------
	 */
	/**
	 * (36) set attribute ModuleIndex
	 *
	 * @param value the value to set the attribute to
	 */
	public void setModuleIndex(final JDFIntegerRangeList value)
	{
		setAttribute(AttributeName.MODULEINDEX, value, null);
	}

	/**
	 * (20) get JDFIntegerRangeList attribute ModuleIndex
	 *
	 * @return JDFIntegerRangeList the value of the attribute, null if a the
	 *         attribute value is not a valid to create a JDFIntegerRangeList
	 */
	public JDFIntegerRangeList getModuleIndex()
	{
		final String strAttrName = getAttribute(AttributeName.MODULEINDEX, null, null);
		final JDFIntegerRangeList nPlaceHolder = JDFIntegerRangeList.createIntegerRangeList(strAttrName);
		return nPlaceHolder;
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
	public void setNonPrintableMarginBottom(final double value)
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
	public void setNonPrintableMarginLeft(final double value)
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
	public void setNonPrintableMarginRight(final double value)
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
	public void setNonPrintableMarginTop(final double value)
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
	 * Methods for Attribute PerfectingModule
	 * ---------------------------------------------------------------------
	 */
	/**
	 * (36) set attribute PerfectingModule
	 *
	 * @param value the value to set the attribute to
	 */
	public void setPerfectingModule(final int value)
	{
		setAttribute(AttributeName.PERFECTINGMODULE, value, null);
	}

	/**
	 * (15) get int attribute PerfectingModule
	 *
	 * @return int the value of the attribute
	 */
	public int getPerfectingModule()
	{
		return getIntAttribute(AttributeName.PERFECTINGMODULE, null, 0);
	}

	/*
	 * ---------------------------------------------------------------------
	 * Methods for Attribute Powder
	 * ---------------------------------------------------------------------
	 */
	/**
	 * (36) set attribute Powder
	 *
	 * @param value the value to set the attribute to
	 */
	public void setPowder(final double value)
	{
		setAttribute(AttributeName.POWDER, value, null);
	}

	/**
	 * (17) get double attribute Powder
	 *
	 * @return double the value of the attribute
	 */
	public double getPowder()
	{
		return getRealAttribute(AttributeName.POWDER, null, 0.0);
	}

	/*
	 * ---------------------------------------------------------------------
	 * Methods for Attribute PrintingType
	 * ---------------------------------------------------------------------
	 */
	/**
	 * (5) set attribute PrintingType
	 *
	 * @param enumVar the enumVar to set the attribute to
	 */
	public void setPrintingType(final EnumPrintingType enumVar)
	{
		setAttribute(AttributeName.PRINTINGTYPE, JavaEnumUtil.getName(enumVar), null);
	}

	/**
	 * (9) get attribute PrintingType
	 *
	 * @return the value of the attribute
	 */
	public EnumPrintingType getPrintingType()
	{
		return EnumPrintingType.getEnum(getAttribute(AttributeName.PRINTINGTYPE, null, null));
	}

	/*
	 * ---------------------------------------------------------------------
	 * Methods for Attribute PrintingTechnology
	 * ---------------------------------------------------------------------
	 */
	/**
	 * (5) set attribute PrintingTechnology
	 *
	 * @param enumVar the enumVar to set the attribute to
	 */
	public void setPrintingTechnology(final EnumPrintingTechnology enumVar)
	{
		setAttribute(AttributeName.PRINTINGTECHNOLOGY, JavaEnumUtil.getName(enumVar), null);
	}

	/**
	 * (9) get attribute PrintingTechnology
	 *
	 * @return the value of the attribute
	 */
	public EnumPrintingTechnology getPrintingTechnology()
	{
		return EnumPrintingTechnology.getEnum(getAttribute(AttributeName.PRINTINGTECHNOLOGY, null, null));
	}

	/*
	 * ---------------------------------------------------------------------
	 * Methods for Attribute SheetLay
	 * ---------------------------------------------------------------------
	 */
	/**
	 * (5) set attribute SheetLay
	 *
	 * @param enumVar the enumVar to set the attribute to
	 */
	public void setSheetLay(final EnumSheetLay enumVar)
	{
		setAttribute(AttributeName.SHEETLAY, JavaEnumUtil.getName(enumVar), null);
	}

	/**
	 * (9) get attribute SheetLay
	 *
	 * @return the value of the attribute
	 */
	public EnumSheetLay getSheetLay()
	{
		return EnumSheetLay.getEnum(getAttribute(AttributeName.SHEETLAY, null, null));
	}

	/*
	 * ---------------------------------------------------------------------
	 * Methods for Attribute Speed
	 * ---------------------------------------------------------------------
	 */
	/**
	 * (36) set attribute Speed
	 *
	 * @param value the value to set the attribute to
	 */
	public void setSpeed(final double value)
	{
		setAttribute(AttributeName.SPEED, value, null);
	}

	/**
	 * (17) get double attribute Speed
	 *
	 * @return double the value of the attribute
	 */
	public double getSpeed()
	{
		return getRealAttribute(AttributeName.SPEED, null, 0.0);
	}

	/*
	 * ---------------------------------------------------------------------
	 * Methods for Attribute WorkStyle
	 * ---------------------------------------------------------------------
	 */
	/**
	 * (5) set attribute WorkStyle
	 *
	 * @param enumVar the enumVar to set the attribute to
	 */
	public void setWorkStyle(final EnumWorkStyle enumVar)
	{
		setAttribute(AttributeName.WORKSTYLE, JavaEnumUtil.getName(enumVar), null);
	}

	/**
	 * (9) get attribute WorkStyle
	 *
	 * @return the value of the attribute
	 */
	public EnumWorkStyle getWorkStyle()
	{
		return EnumWorkStyle.getEnum(getAttribute(AttributeName.WORKSTYLE, null, null));
	}

	/*
	 * ***********************************************************************
	 * Element getter / setter
	 * ***********************************************************************
	 */

	/**
	 * (24) const get element Ink
	 *
	 * @return JDFInk the element
	 */
	public JDFInk getInk()
	{
		return (JDFInk) getElement(ElementName.INK, null, 0);
	}

	/**
	 * (25) getCreateInk
	 * 
	 * @return JDFInk the element
	 */
	public JDFInk getCreateInk()
	{
		return (JDFInk) getCreateElement_JDFElement(ElementName.INK, null, 0);
	}

	/**
	 * (26) getCreateInk
	 * 
	 * @param iSkip number of elements to skip
	 * @return JDFInk the element
	 */
	public JDFInk getCreateInk(final int iSkip)
	{
		return (JDFInk) getCreateElement_JDFElement(ElementName.INK, null, iSkip);
	}

	/**
	 * (27) const get element Ink
	 *
	 * @param iSkip number of elements to skip
	 * @return JDFInk the element
	 *         default is getInk(0)
	 */
	public JDFInk getInk(final int iSkip)
	{
		return (JDFInk) getElement(ElementName.INK, null, iSkip);
	}

	/**
	 * Get all Ink from the current element
	 * 
	 * @return Collection<JDFInk>, null if none are available
	 */
	public Collection<JDFInk> getAllInk()
	{
		return getChildArrayByClass(JDFInk.class, false, 0);
	}

	/**
	 * (30) append element Ink
	 *
	 * @return JDFInk the element
	 */
	public JDFInk appendInk()
	{
		return (JDFInk) appendElement(ElementName.INK, null);
	}

	/**
	 * (31) create inter-resource link to refTarget
	 *
	 * @param refTarget the element that is referenced
	 */
	public void refInk(final JDFInk refTarget)
	{
		refElement(refTarget);
	}

	/**
	 * (24) const get element ApprovalParams
	 *
	 * @return JDFApprovalParams the element
	 */
	public JDFApprovalParams getApprovalParams()
	{
		return (JDFApprovalParams) getElement(ElementName.APPROVALPARAMS, null, 0);
	}

	/**
	 * (25) getCreateApprovalParams
	 * 
	 * @return JDFApprovalParams the element
	 */
	public JDFApprovalParams getCreateApprovalParams()
	{
		return (JDFApprovalParams) getCreateElement_JDFElement(ElementName.APPROVALPARAMS, null, 0);
	}

	/**
	 * (29) append element ApprovalParams
	 *
	 * @return JDFApprovalParams the element
	 * @ if the element already exists
	 */
	public JDFApprovalParams appendApprovalParams()
	{
		return (JDFApprovalParams) appendElementN(ElementName.APPROVALPARAMS, 1, null);
	}

	/**
	 * (31) create inter-resource link to refTarget
	 *
	 * @param refTarget the element that is referenced
	 */
	public void refApprovalParams(final JDFApprovalParams refTarget)
	{
		refElement(refTarget);
	}

}
