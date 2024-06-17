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

import org.apache.xerces.dom.CoreDocumentImpl;
import org.cip4.jdflib.core.AtrInfoTable;
import org.cip4.jdflib.core.AttributeInfo;
import org.cip4.jdflib.core.AttributeName;
import org.cip4.jdflib.core.ElemInfoTable;
import org.cip4.jdflib.core.ElementInfo;
import org.cip4.jdflib.core.ElementName;
import org.cip4.jdflib.core.JDFCoreConstants;
import org.cip4.jdflib.core.VString;
import org.cip4.jdflib.resource.JDFDeviceMark;
import org.cip4.jdflib.resource.JDFResource;

/**
 *****************************************************************************
 * class JDFAutoJobField : public JDFResource
 *****************************************************************************
 * 
 */

public abstract class JDFAutoJobField extends JDFResource
{

	private static final long serialVersionUID = 1L;

	private static AtrInfoTable[] atrInfoTable = new AtrInfoTable[5];
	static
	{
		atrInfoTable[0] = new AtrInfoTable(AttributeName.SHOWLIST, 0x3333333331l, AttributeInfo.EnumAttributeType.NMTOKENS, null, null);
		atrInfoTable[1] = new AtrInfoTable(AttributeName.JOBFORMAT, 0x3333333331l, AttributeInfo.EnumAttributeType.string, null, null);
		atrInfoTable[2] = new AtrInfoTable(AttributeName.JOBTEMPLATE, 0x3333333331l, AttributeInfo.EnumAttributeType.string, null, null);
		atrInfoTable[3] = new AtrInfoTable(AttributeName.OPERATORTEXT, 0x3333333331l, AttributeInfo.EnumAttributeType.string, null, null);
		atrInfoTable[4] = new AtrInfoTable(AttributeName.USERTEXT, 0x3333333331l, AttributeInfo.EnumAttributeType.string, null, null);
	}

	@Override
	protected AttributeInfo getTheAttributeInfo()
	{
		return super.getTheAttributeInfo().updateReplace(atrInfoTable);
	}

	private static ElemInfoTable[] elemInfoTable = new ElemInfoTable[1];
	static
	{
		elemInfoTable[0] = new ElemInfoTable(ElementName.DEVICEMARK, 0x6666666661l);
	}

	@Override
	protected ElementInfo getTheElementInfo()
	{
		return super.getTheElementInfo().updateReplace(elemInfoTable);
	}

	/**
	 * Constructor for JDFAutoJobField
	 *
	 * @param myOwnerDocument
	 * @param qualifiedName
	 */
	protected JDFAutoJobField(CoreDocumentImpl myOwnerDocument, String qualifiedName)
	{
		super(myOwnerDocument, qualifiedName);
	}

	/**
	 * Constructor for JDFAutoJobField
	 *
	 * @param myOwnerDocument
	 * @param myNamespaceURI
	 * @param qualifiedName
	 */
	protected JDFAutoJobField(CoreDocumentImpl myOwnerDocument, String myNamespaceURI, String qualifiedName)
	{
		super(myOwnerDocument, myNamespaceURI, qualifiedName);
	}

	/**
	 * Constructor for JDFAutoJobField
	 *
	 * @param myOwnerDocument
	 * @param myNamespaceURI
	 * @param qualifiedName
	 * @param myLocalName
	 */
	protected JDFAutoJobField(CoreDocumentImpl myOwnerDocument, String myNamespaceURI, String qualifiedName, String myLocalName)
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

	/*
	 * ************************************************************************ Attribute getter / setter ************************************************************************
	 */

	/*
	 * --------------------------------------------------------------------- Methods for Attribute ShowList ---------------------------------------------------------------------
	 */
	/**
	 * (36) set attribute ShowList
	 *
	 * @param value the value to set the attribute to
	 */
	public void setShowList(VString value)
	{
		setAttribute(AttributeName.SHOWLIST, value, null);
	}

	/**
	 * (21) get VString attribute ShowList
	 *
	 * @return VString the value of the attribute
	 */
	public VString getShowList()
	{
		final VString vStrAttrib = new VString();
		final String s = getAttribute(AttributeName.SHOWLIST, null, JDFCoreConstants.EMPTYSTRING);
		vStrAttrib.setAllStrings(s, " ");
		return vStrAttrib;
	}

	/*
	 * --------------------------------------------------------------------- Methods for Attribute JobFormat ---------------------------------------------------------------------
	 */
	/**
	 * (36) set attribute JobFormat
	 *
	 * @param value the value to set the attribute to
	 */
	public void setJobFormat(String value)
	{
		setAttribute(AttributeName.JOBFORMAT, value, null);
	}

	/**
	 * (23) get String attribute JobFormat
	 *
	 * @return the value of the attribute
	 */
	public String getJobFormat()
	{
		return getAttribute(AttributeName.JOBFORMAT, null, JDFCoreConstants.EMPTYSTRING);
	}

	/*
	 * --------------------------------------------------------------------- Methods for Attribute JobTemplate ---------------------------------------------------------------------
	 */
	/**
	 * (36) set attribute JobTemplate
	 *
	 * @param value the value to set the attribute to
	 */
	public void setJobTemplate(String value)
	{
		setAttribute(AttributeName.JOBTEMPLATE, value, null);
	}

	/**
	 * (23) get String attribute JobTemplate
	 *
	 * @return the value of the attribute
	 */
	public String getJobTemplate()
	{
		return getAttribute(AttributeName.JOBTEMPLATE, null, JDFCoreConstants.EMPTYSTRING);
	}

	/*
	 * --------------------------------------------------------------------- Methods for Attribute OperatorText
	 * ---------------------------------------------------------------------
	 */
	/**
	 * (36) set attribute OperatorText
	 *
	 * @param value the value to set the attribute to
	 */
	public void setOperatorText(String value)
	{
		setAttribute(AttributeName.OPERATORTEXT, value, null);
	}

	/**
	 * (23) get String attribute OperatorText
	 *
	 * @return the value of the attribute
	 */
	public String getOperatorText()
	{
		return getAttribute(AttributeName.OPERATORTEXT, null, JDFCoreConstants.EMPTYSTRING);
	}

	/*
	 * --------------------------------------------------------------------- Methods for Attribute UserText ---------------------------------------------------------------------
	 */
	/**
	 * (36) set attribute UserText
	 *
	 * @param value the value to set the attribute to
	 */
	public void setUserText(String value)
	{
		setAttribute(AttributeName.USERTEXT, value, null);
	}

	/**
	 * (23) get String attribute UserText
	 *
	 * @return the value of the attribute
	 */
	public String getUserText()
	{
		return getAttribute(AttributeName.USERTEXT, null, JDFCoreConstants.EMPTYSTRING);
	}

	/*
	 * *********************************************************************** Element getter / setter ***********************************************************************
	 */

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
	 * (31) create inter-resource link to refTarget
	 *
	 * @param refTarget the element that is referenced
	 */
	public void refDeviceMark(JDFDeviceMark refTarget)
	{
		refElement(refTarget);
	}

}
