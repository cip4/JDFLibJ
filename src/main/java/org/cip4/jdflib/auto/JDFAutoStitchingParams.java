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

import org.apache.xerces.dom.CoreDocumentImpl;
import org.cip4.jdflib.core.AtrInfoTable;
import org.cip4.jdflib.core.AttributeInfo;
import org.cip4.jdflib.core.AttributeName;
import org.cip4.jdflib.core.JDFCoreConstants;
import org.cip4.jdflib.datatypes.JDFNumberList;
import org.cip4.jdflib.resource.JDFResource;
import org.cip4.jdflib.util.JavaEnumUtil;

/**
 ***************************************************************************** class JDFAutoStitchingParams : public JDFResource
 */

public abstract class JDFAutoStitchingParams extends JDFResource
{

	private static final long serialVersionUID = 1L;

	private static AtrInfoTable[] atrInfoTable = new AtrInfoTable[13];
	static
	{
		atrInfoTable[0] = new AtrInfoTable(AttributeName.STITCHORIGIN, 0x3333331111l, AttributeInfo.EnumAttributeType.enumeration,
				JavaEnumUtil.getEnum(EnumStitchOrigin.class, 0), "UntrimmedJogSide");
		atrInfoTable[1] = new AtrInfoTable(AttributeName.ANGLE, 0x3333333333l, AttributeInfo.EnumAttributeType.double_, null, null);
		atrInfoTable[2] = new AtrInfoTable(AttributeName.NUMBEROFSTITCHES, 0x3333333333l, AttributeInfo.EnumAttributeType.integer, null, null);
		atrInfoTable[3] = new AtrInfoTable(AttributeName.OFFSET, 0x3333333333l, AttributeInfo.EnumAttributeType.double_, null, null);
		atrInfoTable[4] = new AtrInfoTable(AttributeName.REFERENCEEDGE, 0x4444444431l, AttributeInfo.EnumAttributeType.enumeration,
				JavaEnumUtil.getEnum(EnumReferenceEdge.class, 0), null);
		atrInfoTable[5] = new AtrInfoTable(AttributeName.STAPLESHAPE, 0x3333333333l, AttributeInfo.EnumAttributeType.enumeration,
				JavaEnumUtil.getEnum(EnumStapleShape.class, 0), null);
		atrInfoTable[6] = new AtrInfoTable(AttributeName.STITCHFROMFRONT, 0x4444444433l, AttributeInfo.EnumAttributeType.boolean_, null, null);
		atrInfoTable[7] = new AtrInfoTable(AttributeName.STITCHPOSITIONS, 0x3333333333l, AttributeInfo.EnumAttributeType.string, null, null);
		atrInfoTable[8] = new AtrInfoTable(AttributeName.STITCHTYPE, 0x3333333333l, AttributeInfo.EnumAttributeType.enumeration,
				JavaEnumUtil.getEnum(EnumStitchType.class, 0), null);
		atrInfoTable[9] = new AtrInfoTable(AttributeName.STITCHWIDTH, 0x3333333333l, AttributeInfo.EnumAttributeType.double_, null, null);
		atrInfoTable[10] = new AtrInfoTable(AttributeName.TIGHTBACKING, 0x3333311111l, AttributeInfo.EnumAttributeType.enumeration,
				JavaEnumUtil.getEnum(EnumTightBacking.class, 0), null);
		atrInfoTable[11] = new AtrInfoTable(AttributeName.WIREGAUGE, 0x3333333333l, AttributeInfo.EnumAttributeType.double_, null, null);
		atrInfoTable[12] = new AtrInfoTable(AttributeName.WIREBRAND, 0x3333333333l, AttributeInfo.EnumAttributeType.string, null, null);
	}

	@Override
	protected AttributeInfo getTheAttributeInfo()
	{
		return super.getTheAttributeInfo().updateReplace(atrInfoTable);
	}

	/**
	 * Constructor for JDFAutoStitchingParams
	 *
	 * @param myOwnerDocument
	 * @param qualifiedName
	 */
	protected JDFAutoStitchingParams(final CoreDocumentImpl myOwnerDocument, final String qualifiedName)
	{
		super(myOwnerDocument, qualifiedName);
	}

	/**
	 * Constructor for JDFAutoStitchingParams
	 *
	 * @param myOwnerDocument
	 * @param myNamespaceURI
	 * @param qualifiedName
	 */
	protected JDFAutoStitchingParams(final CoreDocumentImpl myOwnerDocument, final String myNamespaceURI, final String qualifiedName)
	{
		super(myOwnerDocument, myNamespaceURI, qualifiedName);
	}

	/**
	 * Constructor for JDFAutoStitchingParams
	 *
	 * @param myOwnerDocument
	 * @param myNamespaceURI
	 * @param qualifiedName
	 * @param myLocalName
	 */
	protected JDFAutoStitchingParams(final CoreDocumentImpl myOwnerDocument, final String myNamespaceURI, final String qualifiedName, final String myLocalName)
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
	 * Enumeration strings for numStitchOrigin
	 */

	public enum EnumStitchOrigin
	{
		TrimBoxCenter, TrimBoxJogSide, UntrimmedJogSide;

		public static EnumStitchOrigin getEnum(final String val)
		{
			return JavaEnumUtil.getEnumIgnoreCase(EnumStitchOrigin.class, val, EnumStitchOrigin.UntrimmedJogSide);
		}
	}

	/**
	 * Enumeration strings for numReferenceEdge
	 */

	public enum EnumReferenceEdge
	{
		Top, Left, Right, Bottom;

		public static EnumReferenceEdge getEnum(final String val)
		{
			return JavaEnumUtil.getEnumIgnoreCase(EnumReferenceEdge.class, val, null);
		}
	}

	/**
	 * Enumeration strings for numStapleShape
	 */

	public enum EnumStapleShape
	{
		Crown, Overlap, Butted, ClinchOut, Eyelet;

		public static EnumStapleShape getEnum(final String val)
		{
			return JavaEnumUtil.getEnumIgnoreCase(EnumStapleShape.class, val, null);
		}
	}

	/**
	 * Enumeration strings for numStitchType
	 */

	public enum EnumStitchType
	{
		Saddle, Side, Corner;

		public static EnumStitchType getEnum(final String val)
		{
			return JavaEnumUtil.getEnumIgnoreCase(EnumStitchType.class, val, null);
		}
	}

	/**
	 * Enumeration strings for numTightBacking
	 */

	public enum EnumTightBacking
	{
		Flat, Pressure;

		public static EnumTightBacking getEnum(final String val)
		{
			return JavaEnumUtil.getEnumIgnoreCase(EnumTightBacking.class, val, null);
		}
	}/*
		 * ************************************************************************
		 * Attribute getter / setter
		 * ************************************************************************
		 */

	/*
	 * ---------------------------------------------------------------------
	 * Methods for Attribute StitchOrigin
	 * ---------------------------------------------------------------------
	 */
	/**
	 * (5) set attribute StitchOrigin
	 *
	 * @param enumVar the enumVar to set the attribute to
	 */
	public void setStitchOrigin(final EnumStitchOrigin enumVar)
	{
		setAttribute(AttributeName.STITCHORIGIN, JavaEnumUtil.getName(enumVar), null);
	}

	/**
	 * (9) get attribute StitchOrigin
	 *
	 * @return the value of the attribute
	 */
	public EnumStitchOrigin getStitchOrigin()
	{
		return EnumStitchOrigin.getEnum(getAttribute(AttributeName.STITCHORIGIN, null, "UntrimmedJogSide"));
	}

	/*
	 * ---------------------------------------------------------------------
	 * Methods for Attribute Angle
	 * ---------------------------------------------------------------------
	 */
	/**
	 * (36) set attribute Angle
	 *
	 * @param value the value to set the attribute to
	 */
	public void setAngle(final double value)
	{
		setAttribute(AttributeName.ANGLE, value, null);
	}

	/**
	 * (17) get double attribute Angle
	 *
	 * @return double the value of the attribute
	 */
	public double getAngle()
	{
		return getRealAttribute(AttributeName.ANGLE, null, 0.0);
	}

	/*
	 * ---------------------------------------------------------------------
	 * Methods for Attribute NumberOfStitches
	 * ---------------------------------------------------------------------
	 */
	/**
	 * (36) set attribute NumberOfStitches
	 *
	 * @param value the value to set the attribute to
	 */
	public void setNumberOfStitches(final int value)
	{
		setAttribute(AttributeName.NUMBEROFSTITCHES, value, null);
	}

	/**
	 * (15) get int attribute NumberOfStitches
	 *
	 * @return int the value of the attribute
	 */
	public int getNumberOfStitches()
	{
		return getIntAttribute(AttributeName.NUMBEROFSTITCHES, null, 0);
	}

	/*
	 * ---------------------------------------------------------------------
	 * Methods for Attribute Offset
	 * ---------------------------------------------------------------------
	 */
	/**
	 * (36) set attribute Offset
	 *
	 * @param value the value to set the attribute to
	 */
	public void setOffset(final double value)
	{
		setAttribute(AttributeName.OFFSET, value, null);
	}

	/**
	 * (17) get double attribute Offset
	 *
	 * @return double the value of the attribute
	 */
	public double getOffset()
	{
		return getRealAttribute(AttributeName.OFFSET, null, 0.0);
	}

	/*
	 * ---------------------------------------------------------------------
	 * Methods for Attribute ReferenceEdge
	 * ---------------------------------------------------------------------
	 */
	/**
	 * (5) set attribute ReferenceEdge
	 *
	 * @param enumVar the enumVar to set the attribute to
	 */
	public void setReferenceEdge(final EnumReferenceEdge enumVar)
	{
		setAttribute(AttributeName.REFERENCEEDGE, JavaEnumUtil.getName(enumVar), null);
	}

	/**
	 * (9) get attribute ReferenceEdge
	 *
	 * @return the value of the attribute
	 */
	public EnumReferenceEdge getReferenceEdge()
	{
		return EnumReferenceEdge.getEnum(getAttribute(AttributeName.REFERENCEEDGE, null, null));
	}

	/*
	 * ---------------------------------------------------------------------
	 * Methods for Attribute StapleShape
	 * ---------------------------------------------------------------------
	 */
	/**
	 * (5) set attribute StapleShape
	 *
	 * @param enumVar the enumVar to set the attribute to
	 */
	public void setStapleShape(final EnumStapleShape enumVar)
	{
		setAttribute(AttributeName.STAPLESHAPE, JavaEnumUtil.getName(enumVar), null);
	}

	/**
	 * (9) get attribute StapleShape
	 *
	 * @return the value of the attribute
	 */
	public EnumStapleShape getStapleShape()
	{
		return EnumStapleShape.getEnum(getAttribute(AttributeName.STAPLESHAPE, null, null));
	}

	/*
	 * ---------------------------------------------------------------------
	 * Methods for Attribute StitchFromFront
	 * ---------------------------------------------------------------------
	 */
	/**
	 * (36) set attribute StitchFromFront
	 *
	 * @param value the value to set the attribute to
	 */
	public void setStitchFromFront(final boolean value)
	{
		setAttribute(AttributeName.STITCHFROMFRONT, value, null);
	}

	/**
	 * (18) get boolean attribute StitchFromFront
	 *
	 * @return boolean the value of the attribute
	 */
	public boolean getStitchFromFront()
	{
		return getBoolAttribute(AttributeName.STITCHFROMFRONT, null, false);
	}

	/*
	 * ---------------------------------------------------------------------
	 * Methods for Attribute StitchPositions
	 * ---------------------------------------------------------------------
	 */
	/**
	 * (36) set attribute StitchPositions
	 *
	 * @param value the value to set the attribute to
	 */
	public void setStitchPositions(final JDFNumberList value)
	{
		setAttribute(AttributeName.STITCHPOSITIONS, value, null);
	}

	/**
	 * (20) get JDFNumberList attribute StitchPositions
	 *
	 * @return JDFNumberList the value of the attribute, null if a the
	 *         attribute value is not a valid to create a JDFNumberList
	 */
	public JDFNumberList getStitchPositions()
	{
		final String strAttrName = getAttribute(AttributeName.STITCHPOSITIONS, null, null);
		final JDFNumberList nPlaceHolder = JDFNumberList.createNumberList(strAttrName);
		return nPlaceHolder;
	}

	/*
	 * ---------------------------------------------------------------------
	 * Methods for Attribute StitchType
	 * ---------------------------------------------------------------------
	 */
	/**
	 * (5) set attribute StitchType
	 *
	 * @param enumVar the enumVar to set the attribute to
	 */
	public void setStitchType(final EnumStitchType enumVar)
	{
		setAttribute(AttributeName.STITCHTYPE, JavaEnumUtil.getName(enumVar), null);
	}

	/**
	 * (9) get attribute StitchType
	 *
	 * @return the value of the attribute
	 */
	public EnumStitchType getStitchType()
	{
		return EnumStitchType.getEnum(getAttribute(AttributeName.STITCHTYPE, null, null));
	}

	/*
	 * ---------------------------------------------------------------------
	 * Methods for Attribute StitchWidth
	 * ---------------------------------------------------------------------
	 */
	/**
	 * (36) set attribute StitchWidth
	 *
	 * @param value the value to set the attribute to
	 */
	public void setStitchWidth(final double value)
	{
		setAttribute(AttributeName.STITCHWIDTH, value, null);
	}

	/**
	 * (17) get double attribute StitchWidth
	 *
	 * @return double the value of the attribute
	 */
	public double getStitchWidth()
	{
		return getRealAttribute(AttributeName.STITCHWIDTH, null, 0.0);
	}

	/*
	 * ---------------------------------------------------------------------
	 * Methods for Attribute TightBacking
	 * ---------------------------------------------------------------------
	 */
	/**
	 * (5) set attribute TightBacking
	 *
	 * @param enumVar the enumVar to set the attribute to
	 */
	public void setTightBacking(final EnumTightBacking enumVar)
	{
		setAttribute(AttributeName.TIGHTBACKING, JavaEnumUtil.getName(enumVar), null);
	}

	/**
	 * (9) get attribute TightBacking
	 *
	 * @return the value of the attribute
	 */
	public EnumTightBacking getTightBacking()
	{
		return EnumTightBacking.getEnum(getAttribute(AttributeName.TIGHTBACKING, null, null));
	}

	/*
	 * ---------------------------------------------------------------------
	 * Methods for Attribute WireGauge
	 * ---------------------------------------------------------------------
	 */
	/**
	 * (36) set attribute WireGauge
	 *
	 * @param value the value to set the attribute to
	 */
	public void setWireGauge(final double value)
	{
		setAttribute(AttributeName.WIREGAUGE, value, null);
	}

	/**
	 * (17) get double attribute WireGauge
	 *
	 * @return double the value of the attribute
	 */
	public double getWireGauge()
	{
		return getRealAttribute(AttributeName.WIREGAUGE, null, 0.0);
	}

	/*
	 * ---------------------------------------------------------------------
	 * Methods for Attribute WireBrand
	 * ---------------------------------------------------------------------
	 */
	/**
	 * (36) set attribute WireBrand
	 *
	 * @param value the value to set the attribute to
	 */
	public void setWireBrand(final String value)
	{
		setAttribute(AttributeName.WIREBRAND, value, null);
	}

	/**
	 * (23) get String attribute WireBrand
	 *
	 * @return the value of the attribute
	 */
	public String getWireBrand()
	{
		return getAttribute(AttributeName.WIREBRAND, null, JDFCoreConstants.EMPTYSTRING);
	}

}
