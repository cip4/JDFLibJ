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

import org.apache.xerces.dom.CoreDocumentImpl;
import org.cip4.jdflib.core.AtrInfoTable;
import org.cip4.jdflib.core.AttributeInfo;
import org.cip4.jdflib.core.AttributeName;
import org.cip4.jdflib.core.ElemInfoTable;
import org.cip4.jdflib.core.ElementInfo;
import org.cip4.jdflib.core.ElementName;
import org.cip4.jdflib.datatypes.JDFDateTimeRangeList;
import org.cip4.jdflib.datatypes.JDFDurationRangeList;
import org.cip4.jdflib.resource.JDFResource;
import org.cip4.jdflib.resource.devicecapability.JDFLoc;
import org.cip4.jdflib.resource.devicecapability.JDFValueLoc;
import org.cip4.jdflib.util.JDFDate;

/**
 *****************************************************************************
 * class JDFAutoDateTimeState : public JDFResource
 *****************************************************************************
 * 
 */

public abstract class JDFAutoDateTimeState extends JDFResource
{

	private static final long serialVersionUID = 1L;

	private static AtrInfoTable[] atrInfoTable = new AtrInfoTable[6];
	static
	{
		atrInfoTable[0] = new AtrInfoTable(AttributeName.DEFAULTVALUE, 0x3333333311l, AttributeInfo.EnumAttributeType.dateTime, null, null);
		atrInfoTable[1] = new AtrInfoTable(AttributeName.CURRENTVALUE, 0x3333333311l, AttributeInfo.EnumAttributeType.dateTime, null, null);
		atrInfoTable[2] = new AtrInfoTable(AttributeName.ALLOWEDVALUEDURATIONLIST, 0x3333333311l, AttributeInfo.EnumAttributeType.DurationRangeList, null, null);
		atrInfoTable[3] = new AtrInfoTable(AttributeName.ALLOWEDVALUELIST, 0x3333333311l, AttributeInfo.EnumAttributeType.DateTimeRangeList, null, null);
		atrInfoTable[4] = new AtrInfoTable(AttributeName.PRESENTVALUEDURATIONLIST, 0x3333333311l, AttributeInfo.EnumAttributeType.DurationRangeList, null, null);
		atrInfoTable[5] = new AtrInfoTable(AttributeName.PRESENTVALUELIST, 0x3333333311l, AttributeInfo.EnumAttributeType.DateTimeRangeList, null, null);
	}

	@Override
	protected AttributeInfo getTheAttributeInfo()
	{
		return super.getTheAttributeInfo().updateReplace(atrInfoTable);
	}

	private static ElemInfoTable[] elemInfoTable = new ElemInfoTable[2];
	static
	{
		elemInfoTable[0] = new ElemInfoTable(ElementName.LOC, 0x3333333311l);
		elemInfoTable[1] = new ElemInfoTable(ElementName.VALUELOC, 0x3333333311l);
	}

	@Override
	protected ElementInfo getTheElementInfo()
	{
		return super.getTheElementInfo().updateReplace(elemInfoTable);
	}

	/**
	 * Constructor for JDFAutoDateTimeState
	 *
	 * @param myOwnerDocument
	 * @param qualifiedName
	 */
	protected JDFAutoDateTimeState(CoreDocumentImpl myOwnerDocument, String qualifiedName)
	{
		super(myOwnerDocument, qualifiedName);
	}

	/**
	 * Constructor for JDFAutoDateTimeState
	 *
	 * @param myOwnerDocument
	 * @param myNamespaceURI
	 * @param qualifiedName
	 */
	protected JDFAutoDateTimeState(CoreDocumentImpl myOwnerDocument, String myNamespaceURI, String qualifiedName)
	{
		super(myOwnerDocument, myNamespaceURI, qualifiedName);
	}

	/**
	 * Constructor for JDFAutoDateTimeState
	 *
	 * @param myOwnerDocument
	 * @param myNamespaceURI
	 * @param qualifiedName
	 * @param myLocalName
	 */
	protected JDFAutoDateTimeState(CoreDocumentImpl myOwnerDocument, String myNamespaceURI, String qualifiedName, String myLocalName)
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

	/*
	 * ************************************************************************ Attribute getter / setter ************************************************************************
	 */

	/*
	 * --------------------------------------------------------------------- Methods for Attribute DefaultValue
	 * ---------------------------------------------------------------------
	 */
	/**
	 * (11) set attribute DefaultValue
	 *
	 * @param value the value to set the attribute to or null
	 */
	public void setDefaultValue(JDFDate value)
	{
		JDFDate date = value;
		if (date == null)
		{
			date = new JDFDate();
		}
		setAttribute(AttributeName.DEFAULTVALUE, date.getDateTimeISO(), null);
	}

	/**
	 * (12) get JDFDate attribute DefaultValue
	 *
	 * @return JDFDate the value of the attribute
	 */
	public JDFDate getDefaultValue()
	{
		final String str = getAttribute(AttributeName.DEFAULTVALUE, null, null);
		final JDFDate ret = JDFDate.createDate(str);
		return ret;
	}

	/*
	 * --------------------------------------------------------------------- Methods for Attribute CurrentValue
	 * ---------------------------------------------------------------------
	 */
	/**
	 * (11) set attribute CurrentValue
	 *
	 * @param value the value to set the attribute to or null
	 */
	public void setCurrentValue(JDFDate value)
	{
		JDFDate date = value;
		if (date == null)
		{
			date = new JDFDate();
		}
		setAttribute(AttributeName.CURRENTVALUE, date.getDateTimeISO(), null);
	}

	/**
	 * (12) get JDFDate attribute CurrentValue
	 *
	 * @return JDFDate the value of the attribute
	 */
	public JDFDate getCurrentValue()
	{
		final String str = getAttribute(AttributeName.CURRENTVALUE, null, null);
		final JDFDate ret = JDFDate.createDate(str);
		return ret;
	}

	/*
	 * --------------------------------------------------------------------- Methods for Attribute AllowedValueDurationList
	 * ---------------------------------------------------------------------
	 */
	/**
	 * (36) set attribute AllowedValueDurationList
	 *
	 * @param value the value to set the attribute to
	 */
	public void setAllowedValueDurationList(JDFDurationRangeList value)
	{
		setAttribute(AttributeName.ALLOWEDVALUEDURATIONLIST, value, null);
	}

	/**
	 * (20) get JDFDurationRangeList attribute AllowedValueDurationList
	 *
	 * @return JDFDurationRangeList the value of the attribute, null if a the attribute value is not a valid to create a JDFDurationRangeList
	 */
	public JDFDurationRangeList getAllowedValueDurationList()
	{
		final String strAttrName = getAttribute(AttributeName.ALLOWEDVALUEDURATIONLIST, null, null);
		final JDFDurationRangeList nPlaceHolder = JDFDurationRangeList.createDurationRangeList(strAttrName);
		return nPlaceHolder;
	}

	/*
	 * --------------------------------------------------------------------- Methods for Attribute AllowedValueList
	 * ---------------------------------------------------------------------
	 */
	/**
	 * (36) set attribute AllowedValueList
	 *
	 * @param value the value to set the attribute to
	 */
	public void setAllowedValueList(JDFDateTimeRangeList value)
	{
		setAttribute(AttributeName.ALLOWEDVALUELIST, value, null);
	}

	/**
	 * (20) get JDFDateTimeRangeList attribute AllowedValueList
	 *
	 * @return JDFDateTimeRangeList the value of the attribute, null if a the attribute value is not a valid to create a JDFDateTimeRangeList
	 */
	public JDFDateTimeRangeList getAllowedValueList()
	{
		final String strAttrName = getAttribute(AttributeName.ALLOWEDVALUELIST, null, null);
		final JDFDateTimeRangeList nPlaceHolder = JDFDateTimeRangeList.createDateTimeRangeList(strAttrName);
		return nPlaceHolder;
	}

	/*
	 * --------------------------------------------------------------------- Methods for Attribute PresentValueDurationList
	 * ---------------------------------------------------------------------
	 */
	/**
	 * (36) set attribute PresentValueDurationList
	 *
	 * @param value the value to set the attribute to
	 */
	public void setPresentValueDurationList(JDFDurationRangeList value)
	{
		setAttribute(AttributeName.PRESENTVALUEDURATIONLIST, value, null);
	}

	/**
	 * (20) get JDFDurationRangeList attribute PresentValueDurationList
	 *
	 * @return JDFDurationRangeList the value of the attribute, null if a the attribute value is not a valid to create a JDFDurationRangeList
	 */
	public JDFDurationRangeList getPresentValueDurationList()
	{
		final String strAttrName = getAttribute(AttributeName.PRESENTVALUEDURATIONLIST, null, null);
		final JDFDurationRangeList nPlaceHolder = JDFDurationRangeList.createDurationRangeList(strAttrName);
		return nPlaceHolder;
	}

	/*
	 * --------------------------------------------------------------------- Methods for Attribute PresentValueList
	 * ---------------------------------------------------------------------
	 */
	/**
	 * (36) set attribute PresentValueList
	 *
	 * @param value the value to set the attribute to
	 */
	public void setPresentValueList(JDFDateTimeRangeList value)
	{
		setAttribute(AttributeName.PRESENTVALUELIST, value, null);
	}

	/**
	 * (20) get JDFDateTimeRangeList attribute PresentValueList
	 *
	 * @return JDFDateTimeRangeList the value of the attribute, null if a the attribute value is not a valid to create a JDFDateTimeRangeList
	 */
	public JDFDateTimeRangeList getPresentValueList()
	{
		final String strAttrName = getAttribute(AttributeName.PRESENTVALUELIST, null, null);
		final JDFDateTimeRangeList nPlaceHolder = JDFDateTimeRangeList.createDateTimeRangeList(strAttrName);
		return nPlaceHolder;
	}

	/*
	 * *********************************************************************** Element getter / setter ***********************************************************************
	 */

	/**
	 * (24) const get element Loc
	 *
	 * @return JDFLoc the element
	 */
	public JDFLoc getLoc()
	{
		return (JDFLoc) getElement(ElementName.LOC, null, 0);
	}

	/**
	 * (25) getCreateLoc
	 * 
	 * @return JDFLoc the element
	 */
	public JDFLoc getCreateLoc()
	{
		return (JDFLoc) getCreateElement_JDFElement(ElementName.LOC, null, 0);
	}

	/**
	 * (26) getCreateLoc
	 * 
	 * @param iSkip number of elements to skip
	 * @return JDFLoc the element
	 */
	public JDFLoc getCreateLoc(int iSkip)
	{
		return (JDFLoc) getCreateElement_JDFElement(ElementName.LOC, null, iSkip);
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
		return getChildArrayByClass(JDFLoc.class, false, 0);
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
	 * (24) const get element ValueLoc
	 *
	 * @return JDFValueLoc the element
	 */
	public JDFValueLoc getValueLoc()
	{
		return (JDFValueLoc) getElement(ElementName.VALUELOC, null, 0);
	}

	/**
	 * (25) getCreateValueLoc
	 * 
	 * @return JDFValueLoc the element
	 */
	public JDFValueLoc getCreateValueLoc()
	{
		return (JDFValueLoc) getCreateElement_JDFElement(ElementName.VALUELOC, null, 0);
	}

	/**
	 * (26) getCreateValueLoc
	 * 
	 * @param iSkip number of elements to skip
	 * @return JDFValueLoc the element
	 */
	public JDFValueLoc getCreateValueLoc(int iSkip)
	{
		return (JDFValueLoc) getCreateElement_JDFElement(ElementName.VALUELOC, null, iSkip);
	}

	/**
	 * (27) const get element ValueLoc
	 *
	 * @param iSkip number of elements to skip
	 * @return JDFValueLoc the element default is getValueLoc(0)
	 */
	public JDFValueLoc getValueLoc(int iSkip)
	{
		return (JDFValueLoc) getElement(ElementName.VALUELOC, null, iSkip);
	}

	/**
	 * Get all ValueLoc from the current element
	 * 
	 * @return Collection<JDFValueLoc>, null if none are available
	 */
	public Collection<JDFValueLoc> getAllValueLoc()
	{
		return getChildArrayByClass(JDFValueLoc.class, false, 0);
	}

	/**
	 * (30) append element ValueLoc
	 *
	 * @return JDFValueLoc the element
	 */
	public JDFValueLoc appendValueLoc()
	{
		return (JDFValueLoc) appendElement(ElementName.VALUELOC, null);
	}

}
