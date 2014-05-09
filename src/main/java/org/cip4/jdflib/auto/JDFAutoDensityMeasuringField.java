/*
 * The CIP4 Software License, Version 1.0
 *
 *
 * Copyright (c) 2001-2014 The International Cooperation for the Integration of
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
import org.cip4.jdflib.core.ElemInfoTable;
import org.cip4.jdflib.core.ElementInfo;
import org.cip4.jdflib.core.ElementName;
import org.cip4.jdflib.core.JDFCoreConstants;
import org.cip4.jdflib.core.JDFException;
import org.cip4.jdflib.datatypes.JDFNumberList;
import org.cip4.jdflib.datatypes.JDFXYPair;
import org.cip4.jdflib.resource.JDFColorMeasurementConditions;
import org.cip4.jdflib.resource.JDFResource;

/**
*****************************************************************************
class JDFAutoDensityMeasuringField : public JDFResource

*****************************************************************************
*/

public abstract class JDFAutoDensityMeasuringField extends JDFResource
{

	private static final long serialVersionUID = 1L;

	private static AtrInfoTable[] atrInfoTable = new AtrInfoTable[13];
	static
	{
		atrInfoTable[0] = new AtrInfoTable(AttributeName.CENTER, 0x22222222, AttributeInfo.EnumAttributeType.XYPair, null, null);
		atrInfoTable[1] = new AtrInfoTable(AttributeName.DENSITY, 0x22222222, AttributeInfo.EnumAttributeType.string, null, null);
		atrInfoTable[2] = new AtrInfoTable(AttributeName.DIAMETER, 0x22222222, AttributeInfo.EnumAttributeType.double_, null, null);
		atrInfoTable[3] = new AtrInfoTable(AttributeName.DOTGAIN, 0x22222222, AttributeInfo.EnumAttributeType.double_, null, null);
		atrInfoTable[4] = new AtrInfoTable(AttributeName.PERCENTAGE, 0x22222222, AttributeInfo.EnumAttributeType.double_, null, null);
		atrInfoTable[5] = new AtrInfoTable(AttributeName.SCREEN, 0x22222222, AttributeInfo.EnumAttributeType.string, null, null);
		atrInfoTable[6] = new AtrInfoTable(AttributeName.SEPARATION, 0x22222222, AttributeInfo.EnumAttributeType.string, null, null);
		atrInfoTable[7] = new AtrInfoTable(AttributeName.TOLERANCECYAN, 0x22222222, AttributeInfo.EnumAttributeType.XYPair, null, null);
		atrInfoTable[8] = new AtrInfoTable(AttributeName.TOLERANCEMAGENTA, 0x22222222, AttributeInfo.EnumAttributeType.XYPair, null, null);
		atrInfoTable[9] = new AtrInfoTable(AttributeName.TOLERANCEYELLOW, 0x22222222, AttributeInfo.EnumAttributeType.XYPair, null, null);
		atrInfoTable[10] = new AtrInfoTable(AttributeName.TOLERANCEBLACK, 0x22222222, AttributeInfo.EnumAttributeType.XYPair, null, null);
		atrInfoTable[11] = new AtrInfoTable(AttributeName.TOLERANCEDOTGAIN, 0x22222222, AttributeInfo.EnumAttributeType.XYPair, null, null);
		atrInfoTable[12] = new AtrInfoTable(AttributeName.SETUP, 0x33333333, AttributeInfo.EnumAttributeType.string, null, null);
	}

	@Override
	protected AttributeInfo getTheAttributeInfo()
	{
		return super.getTheAttributeInfo().updateReplace(atrInfoTable);
	}

	private static ElemInfoTable[] elemInfoTable = new ElemInfoTable[1];
	static
	{
		elemInfoTable[0] = new ElemInfoTable(ElementName.COLORMEASUREMENTCONDITIONS, 0x66666661);
	}

	@Override
	protected ElementInfo getTheElementInfo()
	{
		return super.getTheElementInfo().updateReplace(elemInfoTable);
	}

	/**
	 * Constructor for JDFAutoDensityMeasuringField
	 * @param myOwnerDocument
	 * @param qualifiedName
	 */
	protected JDFAutoDensityMeasuringField(CoreDocumentImpl myOwnerDocument, String qualifiedName)
	{
		super(myOwnerDocument, qualifiedName);
	}

	/**
	 * Constructor for JDFAutoDensityMeasuringField
	 * @param myOwnerDocument
	 * @param myNamespaceURI
	 * @param qualifiedName
	 */
	protected JDFAutoDensityMeasuringField(CoreDocumentImpl myOwnerDocument, String myNamespaceURI, String qualifiedName)
	{
		super(myOwnerDocument, myNamespaceURI, qualifiedName);
	}

	/**
	 * Constructor for JDFAutoDensityMeasuringField
	 * @param myOwnerDocument
	 * @param myNamespaceURI
	 * @param qualifiedName
	 * @param myLocalName
	 */
	protected JDFAutoDensityMeasuringField(CoreDocumentImpl myOwnerDocument, String myNamespaceURI, String qualifiedName, String myLocalName)
	{
		super(myOwnerDocument, myNamespaceURI, qualifiedName, myLocalName);
	}

	/**
	 * @return  the string representation
	 */
	@Override
	public String toString()
	{
		return " JDFAutoDensityMeasuringField[  --> " + super.toString() + " ]";
	}

	/**
	 * @return  true if ok
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

	/* ************************************************************************
	 * Attribute getter / setter
	 * ************************************************************************
	 */

	/* ---------------------------------------------------------------------
	Methods for Attribute Center
	--------------------------------------------------------------------- */
	/**
	  * (36) set attribute Center
	  * @param value the value to set the attribute to
	  */
	public void setCenter(JDFXYPair value)
	{
		setAttribute(AttributeName.CENTER, value, null);
	}

	/**
	  * (20) get JDFXYPair attribute Center
	  * @return JDFXYPair the value of the attribute, null if a the
	  *         attribute value is not a valid to create a JDFXYPair
	  */
	public JDFXYPair getCenter()
	{
		final String strAttrName = getAttribute(AttributeName.CENTER, null, null);
		final JDFXYPair nPlaceHolder = JDFXYPair.createXYPair(strAttrName);
		return nPlaceHolder;
	}

	/* ---------------------------------------------------------------------
	Methods for Attribute Density
	--------------------------------------------------------------------- */
	/**
	  * (36) set attribute Density
	  * @param value the value to set the attribute to
	  */
	public void setDensity(JDFNumberList value)
	{
		setAttribute(AttributeName.DENSITY, value, null);
	}

	/**
	  * (20) get JDFNumberList attribute Density
	  * @return JDFNumberList the value of the attribute, null if a the
	  *         attribute value is not a valid to create a JDFNumberList
	  */
	public JDFNumberList getDensity()
	{
		final String strAttrName = getAttribute(AttributeName.DENSITY, null, null);
		final JDFNumberList nPlaceHolder = JDFNumberList.createNumberList(strAttrName);
		return nPlaceHolder;
	}

	/* ---------------------------------------------------------------------
	Methods for Attribute Diameter
	--------------------------------------------------------------------- */
	/**
	  * (36) set attribute Diameter
	  * @param value the value to set the attribute to
	  */
	public void setDiameter(double value)
	{
		setAttribute(AttributeName.DIAMETER, value, null);
	}

	/**
	  * (17) get double attribute Diameter
	  * @return double the value of the attribute
	  */
	public double getDiameter()
	{
		return getRealAttribute(AttributeName.DIAMETER, null, 0.0);
	}

	/* ---------------------------------------------------------------------
	Methods for Attribute DotGain
	--------------------------------------------------------------------- */
	/**
	  * (36) set attribute DotGain
	  * @param value the value to set the attribute to
	  */
	public void setDotGain(double value)
	{
		setAttribute(AttributeName.DOTGAIN, value, null);
	}

	/**
	  * (17) get double attribute DotGain
	  * @return double the value of the attribute
	  */
	public double getDotGain()
	{
		return getRealAttribute(AttributeName.DOTGAIN, null, 0.0);
	}

	/* ---------------------------------------------------------------------
	Methods for Attribute Percentage
	--------------------------------------------------------------------- */
	/**
	  * (36) set attribute Percentage
	  * @param value the value to set the attribute to
	  */
	public void setPercentage(double value)
	{
		setAttribute(AttributeName.PERCENTAGE, value, null);
	}

	/**
	  * (17) get double attribute Percentage
	  * @return double the value of the attribute
	  */
	public double getPercentage()
	{
		return getRealAttribute(AttributeName.PERCENTAGE, null, 0.0);
	}

	/* ---------------------------------------------------------------------
	Methods for Attribute Screen
	--------------------------------------------------------------------- */
	/**
	  * (36) set attribute Screen
	  * @param value the value to set the attribute to
	  */
	public void setScreen(String value)
	{
		setAttribute(AttributeName.SCREEN, value, null);
	}

	/**
	  * (23) get String attribute Screen
	  * @return the value of the attribute
	  */
	public String getScreen()
	{
		return getAttribute(AttributeName.SCREEN, null, JDFCoreConstants.EMPTYSTRING);
	}

	/* ---------------------------------------------------------------------
	Methods for Attribute Separation
	--------------------------------------------------------------------- */
	/**
	  * (36) set attribute Separation
	  * @param value the value to set the attribute to
	  */
	@Override
	public void setSeparation(String value)
	{
		setAttribute(AttributeName.SEPARATION, value, null);
	}

	/**
	  * (23) get String attribute Separation
	  * @return the value of the attribute
	  */
	@Override
	public String getSeparation()
	{
		return getAttribute(AttributeName.SEPARATION, null, JDFCoreConstants.EMPTYSTRING);
	}

	/* ---------------------------------------------------------------------
	Methods for Attribute ToleranceCyan
	--------------------------------------------------------------------- */
	/**
	  * (36) set attribute ToleranceCyan
	  * @param value the value to set the attribute to
	  */
	public void setToleranceCyan(JDFXYPair value)
	{
		setAttribute(AttributeName.TOLERANCECYAN, value, null);
	}

	/**
	  * (20) get JDFXYPair attribute ToleranceCyan
	  * @return JDFXYPair the value of the attribute, null if a the
	  *         attribute value is not a valid to create a JDFXYPair
	  */
	public JDFXYPair getToleranceCyan()
	{
		final String strAttrName = getAttribute(AttributeName.TOLERANCECYAN, null, null);
		final JDFXYPair nPlaceHolder = JDFXYPair.createXYPair(strAttrName);
		return nPlaceHolder;
	}

	/* ---------------------------------------------------------------------
	Methods for Attribute ToleranceMagenta
	--------------------------------------------------------------------- */
	/**
	  * (36) set attribute ToleranceMagenta
	  * @param value the value to set the attribute to
	  */
	public void setToleranceMagenta(JDFXYPair value)
	{
		setAttribute(AttributeName.TOLERANCEMAGENTA, value, null);
	}

	/**
	  * (20) get JDFXYPair attribute ToleranceMagenta
	  * @return JDFXYPair the value of the attribute, null if a the
	  *         attribute value is not a valid to create a JDFXYPair
	  */
	public JDFXYPair getToleranceMagenta()
	{
		final String strAttrName = getAttribute(AttributeName.TOLERANCEMAGENTA, null, null);
		final JDFXYPair nPlaceHolder = JDFXYPair.createXYPair(strAttrName);
		return nPlaceHolder;
	}

	/* ---------------------------------------------------------------------
	Methods for Attribute ToleranceYellow
	--------------------------------------------------------------------- */
	/**
	  * (36) set attribute ToleranceYellow
	  * @param value the value to set the attribute to
	  */
	public void setToleranceYellow(JDFXYPair value)
	{
		setAttribute(AttributeName.TOLERANCEYELLOW, value, null);
	}

	/**
	  * (20) get JDFXYPair attribute ToleranceYellow
	  * @return JDFXYPair the value of the attribute, null if a the
	  *         attribute value is not a valid to create a JDFXYPair
	  */
	public JDFXYPair getToleranceYellow()
	{
		final String strAttrName = getAttribute(AttributeName.TOLERANCEYELLOW, null, null);
		final JDFXYPair nPlaceHolder = JDFXYPair.createXYPair(strAttrName);
		return nPlaceHolder;
	}

	/* ---------------------------------------------------------------------
	Methods for Attribute ToleranceBlack
	--------------------------------------------------------------------- */
	/**
	  * (36) set attribute ToleranceBlack
	  * @param value the value to set the attribute to
	  */
	public void setToleranceBlack(JDFXYPair value)
	{
		setAttribute(AttributeName.TOLERANCEBLACK, value, null);
	}

	/**
	  * (20) get JDFXYPair attribute ToleranceBlack
	  * @return JDFXYPair the value of the attribute, null if a the
	  *         attribute value is not a valid to create a JDFXYPair
	  */
	public JDFXYPair getToleranceBlack()
	{
		final String strAttrName = getAttribute(AttributeName.TOLERANCEBLACK, null, null);
		final JDFXYPair nPlaceHolder = JDFXYPair.createXYPair(strAttrName);
		return nPlaceHolder;
	}

	/* ---------------------------------------------------------------------
	Methods for Attribute ToleranceDotGain
	--------------------------------------------------------------------- */
	/**
	  * (36) set attribute ToleranceDotGain
	  * @param value the value to set the attribute to
	  */
	public void setToleranceDotGain(JDFXYPair value)
	{
		setAttribute(AttributeName.TOLERANCEDOTGAIN, value, null);
	}

	/**
	  * (20) get JDFXYPair attribute ToleranceDotGain
	  * @return JDFXYPair the value of the attribute, null if a the
	  *         attribute value is not a valid to create a JDFXYPair
	  */
	public JDFXYPair getToleranceDotGain()
	{
		final String strAttrName = getAttribute(AttributeName.TOLERANCEDOTGAIN, null, null);
		final JDFXYPair nPlaceHolder = JDFXYPair.createXYPair(strAttrName);
		return nPlaceHolder;
	}

	/* ---------------------------------------------------------------------
	Methods for Attribute Setup
	--------------------------------------------------------------------- */
	/**
	  * (36) set attribute Setup
	  * @param value the value to set the attribute to
	  */
	public void setSetup(String value)
	{
		setAttribute(AttributeName.SETUP, value, null);
	}

	/**
	  * (23) get String attribute Setup
	  * @return the value of the attribute
	  */
	public String getSetup()
	{
		return getAttribute(AttributeName.SETUP, null, JDFCoreConstants.EMPTYSTRING);
	}

	/* ***********************************************************************
	 * Element getter / setter
	 * ***********************************************************************
	 */

	/**
	 * (24) const get element ColorMeasurementConditions
	 * @return JDFColorMeasurementConditions the element
	 */
	public JDFColorMeasurementConditions getColorMeasurementConditions()
	{
		return (JDFColorMeasurementConditions) getElement(ElementName.COLORMEASUREMENTCONDITIONS, null, 0);
	}

	/** (25) getCreateColorMeasurementConditions
	 * 
	 * @return JDFColorMeasurementConditions the element
	 */
	public JDFColorMeasurementConditions getCreateColorMeasurementConditions()
	{
		return (JDFColorMeasurementConditions) getCreateElement_KElement(ElementName.COLORMEASUREMENTCONDITIONS, null, 0);
	}

	/**
	 * (29) append element ColorMeasurementConditions
	 * @return JDFColorMeasurementConditions the element
	 * @throws JDFException if the element already exists
	 */
	public JDFColorMeasurementConditions appendColorMeasurementConditions() throws JDFException
	{
		return (JDFColorMeasurementConditions) appendElementN(ElementName.COLORMEASUREMENTCONDITIONS, 1, null);
	}

	/**
	  * (31) create inter-resource link to refTarget
	  * @param refTarget the element that is referenced
	  */
	public void refColorMeasurementConditions(JDFColorMeasurementConditions refTarget)
	{
		refElement(refTarget);
	}

}// end namespace JDF
