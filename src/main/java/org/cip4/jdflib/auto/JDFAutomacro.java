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
import org.cip4.jdflib.core.JDFElement;
import org.cip4.jdflib.resource.devicecapability.JDFcall;
import org.cip4.jdflib.resource.devicecapability.JDFchoice;
import org.cip4.jdflib.resource.devicecapability.JDFset;

/**
 *****************************************************************************
 * class JDFAutomacro : public JDFElement
 *****************************************************************************
 * 
 */

public abstract class JDFAutomacro extends JDFElement
{

	private static final long serialVersionUID = 1L;

	private static AtrInfoTable[] atrInfoTable = new AtrInfoTable[1];
	static
	{
		atrInfoTable[0] = new AtrInfoTable(AttributeName.ID, 0x22222222, AttributeInfo.EnumAttributeType.ID, null, null);
	}

	@Override
	protected AttributeInfo getTheAttributeInfo()
	{
		return super.getTheAttributeInfo().updateReplace(atrInfoTable);
	}

	private static ElemInfoTable[] elemInfoTable = new ElemInfoTable[3];
	static
	{
		elemInfoTable[0] = new ElemInfoTable(ElementName.CHOICE, 0x33333333);
		elemInfoTable[1] = new ElemInfoTable(ElementName.SET, 0x33333333);
		elemInfoTable[2] = new ElemInfoTable(ElementName.CALL, 0x33333333);
	}

	@Override
	protected ElementInfo getTheElementInfo()
	{
		return super.getTheElementInfo().updateReplace(elemInfoTable);
	}

	/**
	 * Constructor for JDFAutomacro
	 * 
	 * @param myOwnerDocument
	 * @param qualifiedName
	 */
	protected JDFAutomacro(CoreDocumentImpl myOwnerDocument, String qualifiedName)
	{
		super(myOwnerDocument, qualifiedName);
	}

	/**
	 * Constructor for JDFAutomacro
	 * 
	 * @param myOwnerDocument
	 * @param myNamespaceURI
	 * @param qualifiedName
	 */
	protected JDFAutomacro(CoreDocumentImpl myOwnerDocument, String myNamespaceURI, String qualifiedName)
	{
		super(myOwnerDocument, myNamespaceURI, qualifiedName);
	}

	/**
	 * Constructor for JDFAutomacro
	 * 
	 * @param myOwnerDocument
	 * @param myNamespaceURI
	 * @param qualifiedName
	 * @param myLocalName
	 */
	protected JDFAutomacro(CoreDocumentImpl myOwnerDocument, String myNamespaceURI, String qualifiedName, String myLocalName)
	{
		super(myOwnerDocument, myNamespaceURI, qualifiedName, myLocalName);
	}

	/**
	 * @return the string representation
	 */
	@Override
	public String toString()
	{
		return " JDFAutomacro[  --> " + super.toString() + " ]";
	}

	/*
	 * ************************************************************************ Attribute getter / setter ************************************************************************
	 */

	/*
	 * --------------------------------------------------------------------- Methods for Attribute ID ---------------------------------------------------------------------
	 */
	/**
	 * (36) set attribute ID
	 * 
	 * @param value the value to set the attribute to
	 */
	@Override
	public void setID(String value)
	{
		setAttribute(AttributeName.ID, value, null);
	}

	/**
	 * (23) get String attribute ID
	 * 
	 * @return the value of the attribute
	 */
	@Override
	public String getID()
	{
		return getAttribute(AttributeName.ID, null, JDFCoreConstants.EMPTYSTRING);
	}

	/*
	 * *********************************************************************** Element getter / setter ***********************************************************************
	 */

	/**
	 * (26) getCreatechoice
	 * 
	 * @param iSkip number of elements to skip
	 * @return JDFchoice the element
	 */
	public JDFchoice getCreatechoice(int iSkip)
	{
		return (JDFchoice) getCreateElement_JDFElement(ElementName.CHOICE, null, iSkip);
	}

	/**
	 * (27) const get element choice
	 * 
	 * @param iSkip number of elements to skip
	 * @return JDFchoice the element default is getchoice(0)
	 */
	public JDFchoice getchoice(int iSkip)
	{
		return (JDFchoice) getElement(ElementName.CHOICE, null, iSkip);
	}

	/**
	 * Get all choice from the current element
	 * 
	 * @return Collection<JDFchoice>, null if none are available
	 */
	public Collection<JDFchoice> getAllchoice()
	{
		return getChildrenByClass(JDFchoice.class, false, 0);
	}

	/**
	 * (30) append element choice
	 * 
	 * @return JDFchoice the element
	 */
	public JDFchoice appendchoice()
	{
		return (JDFchoice) appendElement(ElementName.CHOICE, null);
	}

	/**
	 * (26) getCreateset
	 * 
	 * @param iSkip number of elements to skip
	 * @return JDFset the element
	 */
	public JDFset getCreateset(int iSkip)
	{
		return (JDFset) getCreateElement_JDFElement(ElementName.SET, null, iSkip);
	}

	/**
	 * (27) const get element set
	 * 
	 * @param iSkip number of elements to skip
	 * @return JDFset the element default is getset(0)
	 */
	public JDFset getset(int iSkip)
	{
		return (JDFset) getElement(ElementName.SET, null, iSkip);
	}

	/**
	 * Get all set from the current element
	 * 
	 * @return Collection<JDFset>, null if none are available
	 */
	public Collection<JDFset> getAllset()
	{
		return getChildrenByClass(JDFset.class, false, 0);
	}

	/**
	 * (30) append element set
	 * 
	 * @return JDFset the element
	 */
	public JDFset appendset()
	{
		return (JDFset) appendElement(ElementName.SET, null);
	}

	/**
	 * (26) getCreatecall
	 * 
	 * @param iSkip number of elements to skip
	 * @return JDFcall the element
	 */
	public JDFcall getCreatecall(int iSkip)
	{
		return (JDFcall) getCreateElement_JDFElement(ElementName.CALL, null, iSkip);
	}

	/**
	 * (27) const get element call
	 * 
	 * @param iSkip number of elements to skip
	 * @return JDFcall the element default is getcall(0)
	 */
	public JDFcall getcall(int iSkip)
	{
		return (JDFcall) getElement(ElementName.CALL, null, iSkip);
	}

	/**
	 * Get all call from the current element
	 * 
	 * @return Collection<JDFcall>, null if none are available
	 */
	public Collection<JDFcall> getAllcall()
	{
		return getChildrenByClass(JDFcall.class, false, 0);
	}

	/**
	 * (30) append element call
	 * 
	 * @return JDFcall the element
	 */
	public JDFcall appendcall()
	{
		return (JDFcall) appendElement(ElementName.CALL, null);
	}

}// end namespace JDF
