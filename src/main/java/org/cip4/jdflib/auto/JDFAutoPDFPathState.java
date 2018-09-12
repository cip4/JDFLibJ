/*
 * The CIP4 Software License, Version 1.0
 *
 *
 * Copyright (c) 2001-2018 The International Cooperation for the Integration of Processes in Prepress, Press and Postpress (CIP4). All rights reserved.
 *
 * Redistribution and use in source and binary forms, with or without modification, are permitted provided that the following conditions are met:
 *
 * 1. Redistributions of source code must retain the above copyright notice, this list of conditions and the following disclaimer.
 *
 * 2. Redistributions in binary form must reproduce the above copyright notice, this list of conditions and the following disclaimer in the documentation and/or other materials provided with the
 * distribution.
 *
 * 3. The end-user documentation included with the redistribution, if any, must include the following acknowledgment: "This product includes software developed by the The International Cooperation for
 * the Integration of Processes in Prepress, Press and Postpress (www.cip4.org)" Alternately, this acknowledgment may appear in the software itself, if and wherever such third-party acknowledgments
 * normally appear.
 *
 * 4. The names "CIP4" and "The International Cooperation for the Integration of Processes in Prepress, Press and Postpress" must not be used to endorse or promote products derived from this software
 * without prior written permission. For written permission, please contact info@cip4.org.
 *
 * 5. Products derived from this software may not be called "CIP4", nor may "CIP4" appear in their name, without prior written permission of the CIP4 organization
 *
 * Usage of this software in commercial products is subject to restrictions. For details please consult info@cip4.org.
 *
 * THIS SOFTWARE IS PROVIDED ``AS IS'' AND ANY EXPRESSED OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO, THE IMPLIED WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE ARE
 * DISCLAIMED. IN NO EVENT SHALL THE INTERNATIONAL COOPERATION FOR THE INTEGRATION OF PROCESSES IN PREPRESS, PRESS AND POSTPRESS OR ITS CONTRIBUTORS BE LIABLE FOR ANY DIRECT, INDIRECT, INCIDENTAL,
 * SPECIAL, EXEMPLARY, OR CONSEQUENTIAL DAMAGES (INCLUDING, BUT NOT LIMITED TO, PROCUREMENT OF SUBSTITUTE GOODS OR SERVICES; LOSS OF USE, DATA, OR PROFITS; OR BUSINESS INTERRUPTION) HOWEVER CAUSED AND
 * ON ANY THEORY OF LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY, OR TORT (INCLUDING NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE OF THIS SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY
 * OF SUCH DAMAGE. ====================================================================
 *
 * This software consists of voluntary contributions made by many individuals on behalf of the The International Cooperation for the Integration of Processes in Prepress, Press and Postpress and was
 * originally based on software copyright (c) 1999-2001, Heidelberger Druckmaschinen AG copyright (c) 1999-2001, Agfa-Gevaert N.V.
 *
 * For more information on The International Cooperation for the Integration of Processes in Prepress, Press and Postpress , please see <http://www.cip4.org/>.
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
import org.cip4.jdflib.datatypes.JDFIntegerRange;
import org.cip4.jdflib.resource.JDFResource;
import org.cip4.jdflib.resource.JDFValue;
import org.cip4.jdflib.resource.devicecapability.JDFLoc;

/**
 *****************************************************************************
 * class JDFAutoPDFPathState : public JDFResource
 *****************************************************************************
 * 
 */

public abstract class JDFAutoPDFPathState extends JDFResource
{

	private static final long serialVersionUID = 1L;

	private static AtrInfoTable[] atrInfoTable = new AtrInfoTable[4];
	static
	{
		atrInfoTable[0] = new AtrInfoTable(AttributeName.DEFAULTVALUE, 0x33333311, AttributeInfo.EnumAttributeType.PDFPath, null, null);
		atrInfoTable[1] = new AtrInfoTable(AttributeName.CURRENTVALUE, 0x33333311, AttributeInfo.EnumAttributeType.PDFPath, null, null);
		atrInfoTable[2] = new AtrInfoTable(AttributeName.ALLOWEDLENGTH, 0x33333311, AttributeInfo.EnumAttributeType.IntegerRange, null, null);
		atrInfoTable[3] = new AtrInfoTable(AttributeName.PRESENTLENGTH, 0x33333311, AttributeInfo.EnumAttributeType.IntegerRange, null, null);
	}

	@Override
	protected AttributeInfo getTheAttributeInfo()
	{
		return super.getTheAttributeInfo().updateReplace(atrInfoTable);
	}

	private static ElemInfoTable[] elemInfoTable = new ElemInfoTable[2];
	static
	{
		elemInfoTable[0] = new ElemInfoTable(ElementName.LOC, 0x33333311);
		elemInfoTable[1] = new ElemInfoTable(ElementName.VALUE, 0x33333311);
	}

	@Override
	protected ElementInfo getTheElementInfo()
	{
		return super.getTheElementInfo().updateReplace(elemInfoTable);
	}

	/**
	 * Constructor for JDFAutoPDFPathState
	 * 
	 * @param myOwnerDocument
	 * @param qualifiedName
	 */
	protected JDFAutoPDFPathState(CoreDocumentImpl myOwnerDocument, String qualifiedName)
	{
		super(myOwnerDocument, qualifiedName);
	}

	/**
	 * Constructor for JDFAutoPDFPathState
	 * 
	 * @param myOwnerDocument
	 * @param myNamespaceURI
	 * @param qualifiedName
	 */
	protected JDFAutoPDFPathState(CoreDocumentImpl myOwnerDocument, String myNamespaceURI, String qualifiedName)
	{
		super(myOwnerDocument, myNamespaceURI, qualifiedName);
	}

	/**
	 * Constructor for JDFAutoPDFPathState
	 * 
	 * @param myOwnerDocument
	 * @param myNamespaceURI
	 * @param qualifiedName
	 * @param myLocalName
	 */
	protected JDFAutoPDFPathState(CoreDocumentImpl myOwnerDocument, String myNamespaceURI, String qualifiedName, String myLocalName)
	{
		super(myOwnerDocument, myNamespaceURI, qualifiedName, myLocalName);
	}

	/**
	 * @return the string representation
	 */
	@Override
	public String toString()
	{
		return " JDFAutoPDFPathState[  --> " + super.toString() + " ]";
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

	/*
	 * ************************************************************************ Attribute getter / setter ************************************************************************
	 */

	/*
	 * --------------------------------------------------------------------- Methods for Attribute DefaultValue ---------------------------------------------------------------------
	 */
	/**
	 * (36) set attribute DefaultValue
	 * 
	 * @param value the value to set the attribute to
	 */
	public void setDefaultValue(String value)
	{
		setAttribute(AttributeName.DEFAULTVALUE, value, null);
	}

	/**
	 * (23) get String attribute DefaultValue
	 * 
	 * @return the value of the attribute
	 */
	public String getDefaultValue()
	{
		return getAttribute(AttributeName.DEFAULTVALUE, null, JDFCoreConstants.EMPTYSTRING);
	}

	/*
	 * --------------------------------------------------------------------- Methods for Attribute CurrentValue ---------------------------------------------------------------------
	 */
	/**
	 * (36) set attribute CurrentValue
	 * 
	 * @param value the value to set the attribute to
	 */
	public void setCurrentValue(String value)
	{
		setAttribute(AttributeName.CURRENTVALUE, value, null);
	}

	/**
	 * (23) get String attribute CurrentValue
	 * 
	 * @return the value of the attribute
	 */
	public String getCurrentValue()
	{
		return getAttribute(AttributeName.CURRENTVALUE, null, JDFCoreConstants.EMPTYSTRING);
	}

	/*
	 * --------------------------------------------------------------------- Methods for Attribute AllowedLength ---------------------------------------------------------------------
	 */
	/**
	 * (36) set attribute AllowedLength
	 * 
	 * @param value the value to set the attribute to
	 */
	public void setAllowedLength(JDFIntegerRange value)
	{
		setAttribute(AttributeName.ALLOWEDLENGTH, value, null);
	}

	/**
	 * (20) get JDFIntegerRange attribute AllowedLength
	 * 
	 * @return JDFIntegerRange the value of the attribute, null if a the attribute value is not a valid to create a JDFIntegerRange
	 */
	public JDFIntegerRange getAllowedLength()
	{
		final String strAttrName = getAttribute(AttributeName.ALLOWEDLENGTH, null, null);
		final JDFIntegerRange nPlaceHolder = JDFIntegerRange.createIntegerRange(strAttrName);
		return nPlaceHolder;
	}

	/*
	 * --------------------------------------------------------------------- Methods for Attribute PresentLength ---------------------------------------------------------------------
	 */
	/**
	 * (36) set attribute PresentLength
	 * 
	 * @param value the value to set the attribute to
	 */
	public void setPresentLength(JDFIntegerRange value)
	{
		setAttribute(AttributeName.PRESENTLENGTH, value, null);
	}

	/**
	 * (20) get JDFIntegerRange attribute PresentLength
	 * 
	 * @return JDFIntegerRange the value of the attribute, null if a the attribute value is not a valid to create a JDFIntegerRange
	 */
	public JDFIntegerRange getPresentLength()
	{
		final String strAttrName = getAttribute(AttributeName.PRESENTLENGTH, null, null);
		final JDFIntegerRange nPlaceHolder = JDFIntegerRange.createIntegerRange(strAttrName);
		return nPlaceHolder;
	}

	/*
	 * *********************************************************************** Element getter / setter ***********************************************************************
	 */

	/**
	 * (26) getCreateLoc
	 * 
	 * @param iSkip number of elements to skip
	 * @return JDFLoc the element
	 */
	public JDFLoc getCreateLoc(int iSkip)
	{
		return (JDFLoc) getCreateElement(ElementName.LOC, null, iSkip);
	}

	/**
	 * (27) const get element Loc
	 * 
	 * @param iSkip number of elements to skip
	 * @return JDFLoc the element default is getLoc(0)
	 */
	public JDFLoc getLoc(int iSkip)
	{
		return (JDFLoc) getElement(ElementName.LOC, null, iSkip);
	}

	/**
	 * Get all Loc from the current element
	 * 
	 * @return Collection<JDFLoc>, null if none are available
	 */
	public Collection<JDFLoc> getAllLoc()
	{
		return getChildrenByClass(JDFLoc.class, false, 0);
	}

	/**
	 * (30) append element Loc
	 * 
	 * @return JDFLoc the element
	 */
	public JDFLoc appendLoc()
	{
		return (JDFLoc) appendElement(ElementName.LOC, null);
	}

	/**
	 * (26) getCreateValue
	 * 
	 * @param iSkip number of elements to skip
	 * @return JDFValue the element
	 */
	public JDFValue getCreateValue(int iSkip)
	{
		return (JDFValue) getCreateElement(ElementName.VALUE, null, iSkip);
	}

	/**
	 * (27) const get element Value
	 * 
	 * @param iSkip number of elements to skip
	 * @return JDFValue the element default is getValue(0)
	 */
	public JDFValue getValue(int iSkip)
	{
		return (JDFValue) getElement(ElementName.VALUE, null, iSkip);
	}

	/**
	 * Get all Value from the current element
	 * 
	 * @return Collection<JDFValue>, null if none are available
	 */
	public Collection<JDFValue> getAllValue()
	{
		return getChildrenByClass(JDFValue.class, false, 0);
	}

	/**
	 * (30) append element Value
	 * 
	 * @return JDFValue the element
	 */
	public JDFValue appendValue()
	{
		return (JDFValue) appendElement(ElementName.VALUE, null);
	}

}// end namespace JDF
