/*
 *
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

/**
 *
 * Copyright (c) 2001 Heidelberger Druckmaschinen AG, All Rights Reserved.
 *
 * JDFDevCap.java
 *
 * @author Elena Skobchenko
 *
 */
package org.cip4.jdflib.resource.devicecapability;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;

import org.apache.xerces.dom.CoreDocumentImpl;
import org.cip4.jdflib.auto.JDFAutoBasicPreflightTest.EnumListType;
import org.cip4.jdflib.auto.JDFAutoDevCap;
import org.cip4.jdflib.core.AtrInfoTable;
import org.cip4.jdflib.core.AttributeInfo;
import org.cip4.jdflib.core.AttributeName;
import org.cip4.jdflib.core.ElemInfoTable;
import org.cip4.jdflib.core.ElementInfo;
import org.cip4.jdflib.core.ElementName;
import org.cip4.jdflib.core.JDFComment;
import org.cip4.jdflib.core.JDFConstants;
import org.cip4.jdflib.core.JDFDoc;
import org.cip4.jdflib.core.JDFElement;
import org.cip4.jdflib.core.JDFException;
import org.cip4.jdflib.core.JDFRefElement;
import org.cip4.jdflib.core.KElement;
import org.cip4.jdflib.core.VElement;
import org.cip4.jdflib.core.VString;
import org.cip4.jdflib.core.XMLDoc;
import org.cip4.jdflib.datatypes.JDFAttributeMap;
import org.cip4.jdflib.datatypes.JDFBaseDataTypes.EnumFitsValue;
import org.cip4.jdflib.ifaces.ICapabilityElement;
import org.cip4.jdflib.jmf.JDFJMF;
import org.cip4.jdflib.jmf.JDFMessageService;
import org.cip4.jdflib.node.JDFNode;
import org.cip4.jdflib.resource.JDFResource;
import org.cip4.jdflib.resource.JDFResource.EnumPartUsage;
import org.cip4.jdflib.resource.JDFResource.EnumResStatus;
import org.cip4.jdflib.resource.JDFResource.EnumResourceClass;
import org.cip4.jdflib.resource.devicecapability.JDFDeviceCap.EnumAvailability;
import org.cip4.jdflib.resource.devicecapability.JDFTerm.EnumTerm;
import org.cip4.jdflib.span.JDFSpanBase;
import org.cip4.jdflib.util.StringUtil;

// ----------------------------------
/**
 *
 */
public class JDFDevCap extends JDFAutoDevCap implements ICapabilityElement
{

	/**
	 *
	 */
	private static final long serialVersionUID = -8230286210621220326L;
	/**
	 *
	 */
	private static ElemInfoTable[] elemInfoTable = new ElemInfoTable[14];
	static
	{
		elemInfoTable[0] = new ElemInfoTable(ElementName.DEVCAP, 0x33333333);
		elemInfoTable[1] = new ElemInfoTable(ElementName.BOOLEANSTATE, 0x33333333);
		elemInfoTable[2] = new ElemInfoTable(ElementName.DATETIMESTATE, 0x33333333);
		elemInfoTable[3] = new ElemInfoTable(ElementName.DURATIONSTATE, 0x33333333);
		elemInfoTable[4] = new ElemInfoTable(ElementName.ENUMERATIONSTATE, 0x33333333);
		elemInfoTable[5] = new ElemInfoTable(ElementName.INTEGERSTATE, 0x33333333);
		elemInfoTable[6] = new ElemInfoTable(ElementName.MATRIXSTATE, 0x33333333);
		elemInfoTable[7] = new ElemInfoTable(ElementName.NAMESTATE, 0x33333333);
		elemInfoTable[8] = new ElemInfoTable(ElementName.NUMBERSTATE, 0x33333333);
		elemInfoTable[9] = new ElemInfoTable(ElementName.PDFPATHSTATE, 0x33333333);
		elemInfoTable[10] = new ElemInfoTable(ElementName.RECTANGLESTATE, 0x33333333);
		elemInfoTable[11] = new ElemInfoTable(ElementName.SHAPESTATE, 0x33333333);
		elemInfoTable[12] = new ElemInfoTable(ElementName.STRINGSTATE, 0x33333333);
		elemInfoTable[13] = new ElemInfoTable(ElementName.XYPAIRSTATE, 0x33333333);
	}

	@Override
	protected ElementInfo getTheElementInfo()
	{
		return super.getTheElementInfo().updateReplace(elemInfoTable);
	}

	private static AtrInfoTable[] atrInfoTable = new AtrInfoTable[1];
	static
	{
		atrInfoTable[0] = new AtrInfoTable(AttributeName.MAXOCCURS, 0x33333331, AttributeInfo.EnumAttributeType.unbounded, null, "1");
	}

	@Override
	protected AttributeInfo getTheAttributeInfo()
	{
		return super.getTheAttributeInfo().updateReplace(atrInfoTable);
	}

	/**
	 * Constructor for JDFDevCap
	 *
	 * @param myOwnerDocument
	 * @param qualifiedName
	 */
	public JDFDevCap(final CoreDocumentImpl myOwnerDocument, final String qualifiedName)
	{
		super(myOwnerDocument, qualifiedName);
	}

	/**
	 * Constructor for JDFDevCap
	 *
	 * @param myOwnerDocument
	 * @param myNamespaceURI
	 * @param qualifiedName
	 */
	public JDFDevCap(final CoreDocumentImpl myOwnerDocument, final String myNamespaceURI, final String qualifiedName)
	{
		super(myOwnerDocument, myNamespaceURI, qualifiedName);
	}

	/**
	 * Constructor for JDFDevCap
	 *
	 * @param myOwnerDocument
	 * @param myNamespaceURI
	 * @param qualifiedName
	 * @param myLocalName
	 */
	public JDFDevCap(final CoreDocumentImpl myOwnerDocument, final String myNamespaceURI, final String qualifiedName, final String myLocalName)
	{
		super(myOwnerDocument, myNamespaceURI, qualifiedName, myLocalName);
	}

	/**
	 * toString()
	 *
	 * @return String
	 */
	@Override
	public String toString()
	{
		return "JDFDevCap[  --> " + super.toString() + " ]";
	}

	/**
	 * all devcap elements should have an id
	 */
	@Override
	public boolean init()
	{
		appendAnchor(null); // just in case it is missing
		return super.init();
	}

	/**
	 * set attribute <code>DevCapRefs</code>
	 *
	 * @param value the value to set the attribute to
	 */
	@Override
	public void setDevCapRefs(final VString value)
	{
		setAttribute(AttributeName.DEVCAPREFS, value, null);
	}

	/**
	 * set attribute <code>ID</code>
	 *
	 * @param value the value to set the attribute to
	 */
	@Override
	public void setID(final String value)
	{
		setAttribute(AttributeName.ID, value, null);
	}

	/**
	 * get String attribute <code>ID</code>
	 *
	 * @return String: the value of the attribute
	 */
	@Override
	public String getID()
	{
		return getAttribute(AttributeName.ID, null, JDFConstants.EMPTYSTRING);
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see org.cip4.jdflib.core.JDFElement#getIDPrefix()
	 */
	@Override
	protected String getIDPrefix()
	{
		return "d";
	}

	/**
	 * getAvailability - gets typesafe enumerated attribute <code>Availability</code>
	 *
	 * @return EnumAvailability: the enumeration value of the attribute
	 */
	@Override
	public EnumAvailability getAvailability()
	{
		EnumAvailability avail = EnumAvailability.getEnum(getAttribute(AttributeName.AVAILABILITY, null, null));

		if (avail == null)
		{
			final String parName = getParentNode().getNodeName();
			if (parName.equals(ElementName.DEVCAP))
			{
				final JDFDevCap devCap = (JDFDevCap) getParentNode();
				avail = devCap.getAvailability();
			}
			else if (parName.equals(ElementName.DEVCAPS))
			{
				final JDFDevCaps devCaps = (JDFDevCaps) getParentNode();
				avail = devCaps.getAvailability();
			}
		}
		return avail == null ? EnumAvailability.Installed : avail;
	}

	// ///////////////////////////////////////////////////////////////////

	/**
	 * get iSkip'th element <code>DevCap</code>
	 *
	 * @param iSkip number of elements to skip (0 -> get first element)
	 * @return the value of the attribute
	 */
	public JDFDevCap getDevCap(final int iSkip)
	{
		return (JDFDevCap) getElement(ElementName.DEVCAP, JDFConstants.EMPTYSTRING, iSkip);
	}

	// ///////////////////////////////////////////////////////////////////

	/**
	 * get iSkip'th element <code>DevCap</code>, create if it doesn't exist
	 *
	 * @param iSkip number of elements to skip
	 * @return the value of the attribute
	 */
	public JDFDevCap getCreateDevCap(final int iSkip)
	{
		return (JDFDevCap) getCreateElement(ElementName.DEVCAP, JDFConstants.EMPTYSTRING, iSkip);
	}

	// ///////////////////////////////////////////////////////////////////

	/**
	 * append element <code>DevCap</code>
	 *
	 * @return the appended element
	 */
	public JDFDevCap appendDevCap()
	{
		return (JDFDevCap) appendElement(ElementName.DEVCAP, null);
	}

	// ///////////////////////////////////////////////////////////////////

	/**
	 * append dc/@ID to the value of devCap/@ID
	 *
	 * @param dc the devCap to append
	 */
	public void appendDevCapRefs(final JDFDevCap dc)
	{
		if (!(dc.getParentNode() instanceof JDFDevCapPool))
			throw new JDFException("appendDevCapRefs: referenced devCap must reide in a devCapPool");
		dc.appendAnchor(null); // just in case it is missing
		final String id2 = dc.getID();
		appendDevCapRefs(id2);
	}

	// ///////////////////////////////////////////////////////////////////

	/**
	 * append dc/@ID to the value of devCap/@ID
	 *
	 * @param dcID
	 * @ID of the devCap to append
	 */
	public void appendDevCapRefs(final String dcID)
	{
		appendAttribute(AttributeName.DEVCAPREFS, dcID, null, " ", true);
	}

	// ///////////////////////////////////////////////////////////////////

	/**
	 * gets the iSkip'th existing BooleanState
	 *
	 * @param iSkip number of elements to skip (0 -> get first element)
	 * @return JDFBooleanState: the existing BooleanState
	 */
	public JDFBooleanState getBooleanState(final int iSkip)
	{
		return (JDFBooleanState) getElement(ElementName.BOOLEANSTATE, null, iSkip);
	}

	// ///////////////////////////////////////////////////////////////////

	/**
	 * gets an existing BooleanState with @Name="name"
	 *
	 * @param nam the Name attribute of the newly appended BooleanState
	 * @return JDFBooleanState: the existing BooleanState
	 */
	public JDFBooleanState getBooleanState(final String nam)
	{
		return (JDFBooleanState) getChildWithAttribute(ElementName.BOOLEANSTATE, AttributeName.NAME, null, nam, 0, true);
	}

	// ///////////////////////////////////////////////////////////////////

	/**
	 * @deprecated use method with parameter (string) instead
	 * @return
	 */
	@Deprecated
	public JDFBooleanState getCreateBooleanState(final int iSkip)
	{
		return (JDFBooleanState) getCreateElement(ElementName.BOOLEANSTATE, null, iSkip);
	}

	// ///////////////////////////////////////////////////////////////////

	/**
	 * gets a NumberState with @Name="name", appends it if it does not exist
	 *
	 * @param nam the name attribute of the newly appended NumberState
	 * @return JDFNumberState: the existing or newly appended NumberState
	 */
	public JDFBooleanState getCreateBooleanState(final String nam)
	{
		JDFBooleanState s = getBooleanState(nam);
		if (s == null)
			s = appendBooleanState(nam);
		return s;
	}

	// ///////////////////////////////////////////////////////////////////

	/**
	 * @deprecated use method with parameter (string) instead
	 * @return
	 */
	@Deprecated
	public JDFBooleanState appendBooleanState()
	{
		return (JDFBooleanState) appendElement(ElementName.BOOLEANSTATE, null);
	}

	/**
	 * appends a BooleanState with @Name="name"
	 *
	 * @param nam the name attribute of the newly appended BooleanState
	 * @return JDFBooleanState: the newly appended BooleanState
	 */
	public JDFBooleanState appendBooleanState(final String nam)
	{
		final JDFBooleanState s = (JDFBooleanState) appendElement(ElementName.BOOLEANSTATE, null);
		s.setName(nam);
		return s;
	}

	// ///////////////////////////////////////////////////////////////////
	// ///////////////////////////////////////////////////////////////////

	/**
	 * gets the iSkip'th existing IntegerState
	 *
	 * @param iSkip number of elements to skip (0 -> get first element)
	 * @return JDFIntegerState: the existing IntegerState
	 */
	public JDFIntegerState getIntegerState(final int iSkip)
	{
		final JDFIntegerState e = (JDFIntegerState) getElement(ElementName.INTEGERSTATE, null, iSkip);
		return e;
	}

	// ///////////////////////////////////////////////////////////////////

	/**
	 * gets an existing IntegerState with @Name="name"
	 *
	 * @param nam the name attribute of the newly appended IntegerState
	 * @return JDFIntegerState: the existing IntegerState
	 */
	public JDFIntegerState getIntegerState(final String nam)
	{
		return (JDFIntegerState) getChildWithAttribute(ElementName.INTEGERSTATE, AttributeName.NAME, null, nam, 0, true);
	}

	// ///////////////////////////////////////////////////////////////////

	/**
	 * @deprecated use method with parameter (string) instead
	 * @return
	 */
	@Deprecated
	public JDFIntegerState getCreateIntegerState(final int iSkip)
	{
		return (JDFIntegerState) getCreateElement(ElementName.INTEGERSTATE, null, iSkip);
	}

	/**
	 * gets an IntegerState with @Name="name", appends it if it does not yet exist
	 *
	 * @param nam the name attribute of the newly appended IntegerState
	 * @return JDFIntegerState: the existing or newly appended IntegerState
	 */
	public JDFIntegerState getCreateIntegerState(final String nam)
	{
		JDFIntegerState s = getIntegerState(nam);
		if (s == null)
			s = appendIntegerState(nam);
		return s;
	}

	// ///////////////////////////////////////////////////////////////////

	/**
	 * append an IntegerState with no name set
	 *
	 * @return JDFIntegerState: the newly appended IntegerState
	 */
	public JDFIntegerState appendIntegerState()
	{
		return (JDFIntegerState) appendElement(ElementName.INTEGERSTATE, null);
	}

	// ///////////////////////////////////////////////////////////////////

	/**
	 * appends an IntegerState with @Name="name"
	 *
	 * @param name the Name attribute of the newly appended IntegerState
	 * @return JDFIntegerState: the newly appended IntegerState
	 */
	public JDFIntegerState appendIntegerState(final String nam)
	{
		final JDFIntegerState s = (JDFIntegerState) appendElement(ElementName.INTEGERSTATE, null);
		s.setName(nam);
		return s;
	}

	// ///////////////////////////////////////////////////////////////////
	// ///////////////////////////////////////////////////////////////////

	/**
	 * gets the iSkip'th existing NumberState
	 *
	 * @param iSkip number of elements to skip
	 * @return JDFNumberState: the existing NumberState
	 */
	public JDFNumberState getNumberState(final int iSkip)
	{
		return (JDFNumberState) getElement(ElementName.NUMBERSTATE, null, iSkip);
	}

	// ///////////////////////////////////////////////////////////////////
	/**
	 * gets an existing NumberState with @Name="name"
	 *
	 * @param nam the Name attribute of the newly appended NumberState
	 * @return JDFNumberState: the existing NumberState
	 */
	public JDFNumberState getNumberState(final String nam)
	{
		return (JDFNumberState) getChildWithAttribute(ElementName.NUMBERSTATE, AttributeName.NAME, null, nam, 0, true);
	}

	// ///////////////////////////////////////////////////////////////////

	/**
	 * @deprecated use method with parameter (string) instead
	 * @return
	 */
	@Deprecated
	public JDFNumberState getCreateNumberState(final int iSkip)
	{
		return (JDFNumberState) getCreateElement(ElementName.NUMBERSTATE, null, iSkip);
	}

	// ///////////////////////////////////////////////////////////////////

	/**
	 * gets a NumberState with @Name="name", appends it if it does not yet exist
	 *
	 * @param nam the Name attribute of the newly appended NumberState
	 * @return JDFNumberState: the existing or newly appended NumberState
	 */
	public JDFNumberState getCreateNumberState(final String nam)
	{
		JDFNumberState s = getNumberState(nam);
		if (s == null)
			s = appendNumberState(nam);
		return s;
	}

	// ///////////////////////////////////////////////////////////////////
	/**
	 * appends a NumberState with @Name="name"
	 *
	 * @param nam the Name attribute of the newly appended NumberState
	 * @return JDFNumberState: the newly appended NumberState
	 */
	public JDFNumberState appendNumberState(final String nam)
	{
		final JDFNumberState s = (JDFNumberState) appendElement(ElementName.NUMBERSTATE, null);
		s.setName(nam);
		return s;
	}

	/**
	 * @deprecated use method with parameter (string) instead
	 * @return
	 */
	@Deprecated
	public JDFNumberState appendNumberState()
	{
		return appendNumberState(null);
	}

	// ///////////////////////////////////////////////////////////////////
	// ///////////////////////////////////////////////////////////////////

	/**
	 * gets the iSkip'th existing EnumerationState
	 *
	 * @param iSkip number of elements to skip (0 -> get first element)
	 * @return JDFEnumerationState: the existing EnumerationState
	 */
	public JDFEnumerationState getEnumerationState(final int iSkip)
	{
		return (JDFEnumerationState) getElement(ElementName.ENUMERATIONSTATE, null, iSkip);
	}

	// ///////////////////////////////////////////////////////////////////

	/**
	 * gets an existing EnumerationState with @Name="name"
	 *
	 * @param name the Name attribute of the newly appended EnumerationState
	 * @return JDFEnumerationState: the existing EnumerationState
	 */
	public JDFEnumerationState getEnumerationState(final String nam)
	{
		return (JDFEnumerationState) getChildWithAttribute(ElementName.ENUMERATIONSTATE, AttributeName.NAME, null, nam, 0, true);
	}

	// ///////////////////////////////////////////////////////////////////

	/**
	 * gets the iSkip'th existing EnumerationState, creates it if it doesn't exist
	 *
	 * @param iSkip number of elements to skip (0 -> get first element)
	 * @return JDFEnumerationState: the existing EnumerationState
	 * @deprecated
	 */
	@Deprecated
	public JDFEnumerationState getCreateEnumerationState(final int iSkip)
	{
		return (JDFEnumerationState) getCreateElement(ElementName.ENUMERATIONSTATE, null, iSkip);
	}

	// ///////////////////////////////////////////////////////////////////

	/**
	 * gets a EnumerationState with @Name="name", appends it if it does not exist
	 *
	 * @param nam the name attribute of the newly appended EnumerationState
	 * @return JDFEnumerationState the existing or newly appended EnumerationState
	 */
	public JDFEnumerationState getCreateEnumerationState(final String nam)
	{
		JDFEnumerationState s = getEnumerationState(nam);
		if (s == null)
			s = appendEnumerationState(nam);
		return s;
	}

	// ///////////////////////////////////////////////////////////////////

	/**
	 * @deprecated use method with parameter (string) instead
	 * @return
	 */
	@Deprecated
	public JDFEnumerationState appendEnumerationState()
	{
		return (JDFEnumerationState) appendElement(ElementName.ENUMERATIONSTATE, null);
	}

	// ///////////////////////////////////////////////////////////////////

	/**
	 * appends a NumberState with @Name="name"
	 *
	 * @param nam the name attribute of the newly appended NumberState
	 * @return JDFNumberState: the newly appended NumberState
	 */
	public JDFEnumerationState appendEnumerationState(final String nam)
	{
		final JDFEnumerationState s = (JDFEnumerationState) appendElement(ElementName.ENUMERATIONSTATE, null);
		s.setName(nam);
		return s;
	}

	// ///////////////////////////////////////////////////////////////////
	// ///////////////////////////////////////////////////////////////////

	/**
	 * gets the iSkip'th existing NameState
	 *
	 * @param iSkip number of elements to skip
	 * @return JDFNameState: the existing NameState
	 */
	public JDFNameState getNameState(final int iSkip)
	{
		return (JDFNameState) getElement(ElementName.NAMESTATE, null, iSkip);
	}

	// ///////////////////////////////////////////////////////////////////

	/**
	 * gets an existing NameState with @Name="name"
	 *
	 * @param nam the Name attribute of the newly appended NameState
	 * @return JDFNameState: the existing NameState
	 */
	public JDFNameState getNameState(final String nam)
	{
		return (JDFNameState) getChildWithAttribute(ElementName.NAMESTATE, AttributeName.NAME, null, nam, 0, true);
	}

	// ///////////////////////////////////////////////////////////////////

	/**
	 * @deprecated use method with parameter (string) instead
	 * @return
	 */
	@Deprecated
	public JDFNameState getCreateNameState(final int iSkip)
	{
		return (JDFNameState) getCreateElement(ElementName.NAMESTATE, null, iSkip);
	}

	// ///////////////////////////////////////////////////////////////////

	/**
	 * gets a NameState with @Name="name", appends it if it does not exist
	 *
	 * @param nam the name attribute of the newly appended NameState
	 * @return JDFNameState: the existing or newly appended NameState
	 */
	public JDFNameState getCreateNameState(final String nam)
	{
		JDFNameState s = getNameState(nam);
		if (s == null)
			s = appendNameState(nam);
		return s;
	}

	// ///////////////////////////////////////////////////////////////////

	/**
	 * @deprecated use method with parameter (string) instead
	 * @return
	 */
	@Deprecated
	public JDFNameState appendNameState()
	{
		return (JDFNameState) appendElement(ElementName.NAMESTATE, null);
	}

	// ///////////////////////////////////////////////////////////////////

	/**
	 * appends a NameState with @Name="name"
	 *
	 * @param nam the name attribute of the newly appended NameState
	 * @return JDFNameState: the newly appended NameState
	 */
	public JDFNameState appendNameState(final String nam)
	{
		final JDFNameState s = (JDFNameState) appendElement(ElementName.NAMESTATE, null);
		s.setName(nam);
		return s;
	}

	// ///////////////////////////////////////////////////////////////////
	// ///////////////////////////////////////////////////////////////////

	/**
	 * gets the iSkip'th existing StringState
	 *
	 * @param iSkip number of elements to skip
	 * @return JDFStringState: the existing StringState
	 */
	public JDFStringState getStringState(final int iSkip)
	{
		return (JDFStringState) getElement(ElementName.STRINGSTATE, null, iSkip);
	}

	// ///////////////////////////////////////////////////////////////////

	/**
	 * gets an existing StringState with @Name="name"
	 *
	 * @param nam the Name attribute of the newly appended StringState
	 * @return JDFStringState: the existing StringState
	 */
	public JDFStringState getStringState(final String nam)
	{
		return (JDFStringState) getChildWithAttribute(ElementName.STRINGSTATE, AttributeName.NAME, null, nam, 0, true);
	}

	// ///////////////////////////////////////////////////////////////////

	/**
	 * @deprecated use method with parameter (string) instead
	 * @return
	 */
	@Deprecated
	public JDFStringState getCreateStringState(final int iSkip)
	{
		return (JDFStringState) getCreateElement(ElementName.STRINGSTATE, null, iSkip);
	}

	// ///////////////////////////////////////////////////////////////////

	/**
	 * gets a StringState with @Name="name", appends it if it does not yet exist
	 *
	 * @param nam the Name attribute of the newly appended StringState
	 * @return JDFStringState: the existing or newly appended StringState
	 */
	public JDFStringState getCreateStringState(final String nam)
	{
		JDFStringState s = getStringState(nam);
		if (s == null)
			s = appendStringState(nam);
		return s;
	}

	// ///////////////////////////////////////////////////////////////////

	/**
	 * @deprecated use method with parameter (string) instead
	 * @return
	 */
	@Deprecated
	public JDFStringState appendStringState()
	{
		return (JDFStringState) appendElement(ElementName.STRINGSTATE, null);
	}

	// ///////////////////////////////////////////////////////////////////

	/**
	 * appends a StringState with @Name="name"
	 *
	 * @param nam the Name attribute of the newly appended StringState
	 * @return JDFStringState: the newly appended StringState
	 */
	public JDFStringState appendStringState(final String nam)
	{
		final JDFStringState s = (JDFStringState) appendElement(ElementName.STRINGSTATE, null);
		s.setName(nam);
		return s;
	}

	// ///////////////////////////////////////////////////////////////////
	// ///////////////////////////////////////////////////////////////////

	/**
	 * gets the iSkip'th existing XYPairState
	 *
	 * @param iSkip number of elements to skip
	 * @return JDFXYPairState: the existing XYPairState
	 */
	public JDFXYPairState getXYPairState(final int iSkip)
	{
		return (JDFXYPairState) getElement(ElementName.XYPAIRSTATE, null, iSkip);
	}

	// ///////////////////////////////////////////////////////////////////

	/**
	 * gets an existing XYPairState with @Name="name"
	 *
	 * @param nam the Name attribute of the newly appended XYPairState
	 * @return JDFXYPairState: the existing XYPairState
	 */
	public JDFXYPairState getXYPairState(final String nam)
	{
		return (JDFXYPairState) getChildWithAttribute(ElementName.XYPAIRSTATE, AttributeName.NAME, null, nam, 0, true);
	}

	// ///////////////////////////////////////////////////////////////////

	/**
	 * @deprecated use method with parameter (string) instead
	 * @return
	 */
	@Deprecated
	public JDFXYPairState getCreateXYPairState(final int iSkip)
	{
		return (JDFXYPairState) getCreateElement(ElementName.XYPAIRSTATE, null, iSkip);
	}

	// ///////////////////////////////////////////////////////////////////

	/**
	 * gets a XYPairState with @Name="name", appends it if it does not yet exist
	 *
	 * @param nam the Name attribute of the newly appended XYPairState
	 * @return JDFXYPairState: the existing or newly appended XYPairState
	 */
	public JDFXYPairState getCreateXYPairState(final String nam)
	{
		JDFXYPairState s = getXYPairState(nam);
		if (s == null)
			s = appendXYPairState(nam);
		return s;
	}

	// ///////////////////////////////////////////////////////////////////

	/**
	 * @deprecated use method with parameter (string) instead
	 * @return
	 */
	@Deprecated
	public JDFXYPairState appendXYPairState()
	{
		return (JDFXYPairState) appendElement(ElementName.XYPAIRSTATE, null);
	}

	// ///////////////////////////////////////////////////////////////////

	/**
	 * appends a XYPairState with @Name="name"
	 *
	 * @param nam the Name attribute of the newly appended XYPairState
	 * @return JDFXYPairState: the newly appended XYPairState
	 */
	public JDFXYPairState appendXYPairState(final String nam)
	{
		final JDFXYPairState s = (JDFXYPairState) appendElement(ElementName.XYPAIRSTATE, null);
		s.setName(nam);
		return s;
	}

	// ///////////////////////////////////////////////////////////////////
	// ///////////////////////////////////////////////////////////////////

	/**
	 * gets the iSkip'th existing ShapeState
	 *
	 * @param iSkip number of elements to skip (0 -> get first element)
	 * @return JDFShapeState the existing ShapeState
	 */
	public JDFShapeState getShapeState(final int iSkip)
	{
		return (JDFShapeState) getElement(ElementName.SHAPESTATE, null, iSkip);
	}

	// ///////////////////////////////////////////////////////////////////

	/**
	 * gets an existing ShapeState with @Name="name"
	 *
	 * @param nam the Name attribute of the newly appended ShapeState
	 * @return JDFShapeState: the existing ShapeState
	 */
	public JDFShapeState getShapeState(final String nam)
	{
		return (JDFShapeState) getChildWithAttribute(ElementName.SHAPESTATE, AttributeName.NAME, null, nam, 0, true);
	}

	// ///////////////////////////////////////////////////////////////////

	/**
	 * @deprecated use method with parameter (string) instead
	 * @return
	 */
	@Deprecated
	public JDFShapeState getCreateShapeState(final int iSkip)
	{
		return (JDFShapeState) getCreateElement(ElementName.SHAPESTATE, null, iSkip);
	}

	// ///////////////////////////////////////////////////////////////////

	/**
	 * gets a ShapeState with @Name="name", appends it if it does not yet exist
	 *
	 * @param nam the Name attribute of the newly appended ShapeState
	 * @return JDFShapeState: the existing or newly appended ShapeState
	 */
	public JDFShapeState getCreateShapeState(final String nam)
	{
		JDFShapeState s = getShapeState(nam);
		if (s == null)
			s = appendShapeState(nam);
		return s;
	}

	// ///////////////////////////////////////////////////////////////////

	/**
	 * @deprecated use method with parameter (string) instead
	 * @return
	 */
	@Deprecated
	public JDFShapeState appendShapeState()
	{
		return (JDFShapeState) appendElement(ElementName.SHAPESTATE, null);
	}

	// ///////////////////////////////////////////////////////////////////

	/**
	 * appends a ShapeState with @Name="name"
	 *
	 * @param nam the Name attribute of the newly appended ShapeState
	 * @return JDFShapeState: the newly appended ShapeState
	 */
	public JDFShapeState appendShapeState(final String nam)
	{
		final JDFShapeState s = (JDFShapeState) appendElement(ElementName.SHAPESTATE, null);
		s.setName(nam);
		return s;
	}

	// ///////////////////////////////////////////////////////////////////
	// ///////////////////////////////////////////////////////////////////

	/**
	 * gets the iSkip'th existing MatrixState
	 *
	 * @param iSkip number of elements to skip (0 -> get first element)
	 * @return JDFMatrixState the existing MatrixState
	 */
	public JDFMatrixState getMatrixState(final int iSkip)
	{
		return (JDFMatrixState) getElement(ElementName.MATRIXSTATE, null, iSkip);
	}

	// ///////////////////////////////////////////////////////////////////

	/**
	 * gets an existing MatrixState with @Name="name"
	 *
	 * @param nam the Name attribute of the newly appended MatrixState
	 * @return JDFMatrixState: the existing MatrixState
	 */
	public JDFMatrixState getMatrixState(final String nam)
	{
		return (JDFMatrixState) getChildWithAttribute(ElementName.MATRIXSTATE, AttributeName.NAME, null, nam, 0, true);
	}

	// ///////////////////////////////////////////////////////////////////

	/**
	 * @deprecated use method with parameter (string) instead
	 * @return
	 */
	@Deprecated
	public JDFMatrixState getCreateMatrixState(final int iSkip)
	{
		return (JDFMatrixState) getCreateElement(ElementName.MATRIXSTATE, null, iSkip);
	}

	/**
	 * gets a MatrixState with @Name="name", appends it if it does not yet exist
	 *
	 * @param nam the Name attribute of the newly appended MatrixState
	 * @return JDFMatrixState: the existing or newly appended MatrixState
	 */
	public JDFMatrixState getCreateMatrixState(final String nam)
	{
		JDFMatrixState s = getMatrixState(nam);
		if (s == null)
			s = appendMatrixState(nam);
		return s;
	}

	// ///////////////////////////////////////////////////////////////////

	/**
	 * @deprecated use method with parameter (string) instead
	 * @return
	 */
	@Deprecated
	public JDFMatrixState appendMatrixState()
	{
		return (JDFMatrixState) appendElement(ElementName.MATRIXSTATE, null);
	}

	// ///////////////////////////////////////////////////////////////////

	/**
	 * appends a MatrixState with @Name="name"
	 *
	 * @param nam the Name attribute of the newly appended MatrixState
	 * @return JDFMatrixState: the newly appended MatrixState
	 */
	public JDFMatrixState appendMatrixState(final String nam)
	{
		final JDFMatrixState s = (JDFMatrixState) appendElement(ElementName.MATRIXSTATE, null);
		s.setName(nam);
		return s;
	}

	// ///////////////////////////////////////////////////////////////////
	// ///////////////////////////////////////////////////////////////////

	/**
	 * gets the iSkip'th existing DateTimeState
	 *
	 * @param iSkip number of elements to skip (0 -> get first element)
	 * @return JDFDateTimeState the existing DateTimeState
	 */
	public JDFDateTimeState getDateTimeState(final int iSkip)
	{
		return (JDFDateTimeState) getElement(ElementName.DATETIMESTATE, null, iSkip);
	}

	// ///////////////////////////////////////////////////////////////////

	/**
	 * gets an existing DateTimeState with @Name="name"
	 *
	 * @param nam the Name attribute of the newly appended DateTimeState
	 * @return JDFDateTimeState: the existing DateTimeState
	 */
	public JDFDateTimeState getDateTimeState(final String nam)
	{
		return (JDFDateTimeState) getChildWithAttribute(ElementName.DATETIMESTATE, AttributeName.NAME, null, nam, 0, true);
	}

	// ///////////////////////////////////////////////////////////////////

	/**
	 * @deprecated use method with parameter (string) instead
	 * @return
	 */
	@Deprecated
	public JDFDateTimeState getCreateDateTimeState(final int iSkip)
	{
		return (JDFDateTimeState) getCreateElement(ElementName.DATETIMESTATE, null, iSkip);
	}

	// ///////////////////////////////////////////////////////////////////

	/**
	 * gets a DateTimeState with @Name="name", appends it if it does not yet exist
	 *
	 * @param nam the Name attribute of the newly appended DateTimeState
	 * @return JDFDateTimeState: the existing or newly appended DateTimeState
	 */
	public JDFDateTimeState getCreateDateTimeState(final String nam)
	{
		JDFDateTimeState s = getDateTimeState(nam);
		if (s == null)
			s = appendDateTimeState(nam);
		return s;
	}

	// ///////////////////////////////////////////////////////////////////

	/**
	 * @deprecated use method with parameter (string) instead
	 * @return
	 */
	@Deprecated
	public JDFDateTimeState appendDateTimeState()
	{
		return (JDFDateTimeState) appendElement(ElementName.DATETIMESTATE, null);
	}

	// ///////////////////////////////////////////////////////////////////

	/**
	 * appends a DateTimeState with @Name="name"
	 *
	 * @param nam the Name attribute of the newly appended DateTimeState
	 * @return JDFDateTimeState: the newly appended DateTimeState
	 */
	public JDFDateTimeState appendDateTimeState(final String nam)
	{
		final JDFDateTimeState s = (JDFDateTimeState) appendElement(ElementName.DATETIMESTATE, null);
		s.setName(nam);
		return s;
	}

	// ///////////////////////////////////////////////////////////////////
	// ///////////////////////////////////////////////////////////////////

	/**
	 * gets the iSkip'th existing DurationState
	 *
	 * @param iSkip number of elements to skip (0 -> get first element)
	 * @return JDFDurationState the existing DurationState
	 */
	public JDFDurationState getDurationState(final int iSkip)
	{
		return (JDFDurationState) getElement(ElementName.DURATIONSTATE, null, iSkip);
	}

	// ///////////////////////////////////////////////////////////////////

	/**
	 * gets an existing DurationState with @Name="name"
	 *
	 * @param nam the Name attribute of the newly appended DurationState
	 * @return JDFDurationState: the existing DurationState
	 */
	public JDFDurationState getDurationState(final String nam)
	{
		return (JDFDurationState) getChildWithAttribute(ElementName.DURATIONSTATE, AttributeName.NAME, null, nam, 0, true);
	}

	// ///////////////////////////////////////////////////////////////////

	/**
	 * @deprecated use method with parameter (string) instead
	 * @return
	 */
	@Deprecated
	public JDFDurationState getCreateDurationState(final int iSkip)
	{
		return (JDFDurationState) getCreateElement(ElementName.DURATIONSTATE, null, iSkip);
	}

	// ///////////////////////////////////////////////////////////////////

	/**
	 * gets a DurationState with @Name="name", appends it if it does not yet exist
	 *
	 * @param nam the Name attribute of the newly appended DurationState
	 * @return JDFDurationState: the existing or newly appended DurationState
	 */
	public JDFDurationState getCreateDurationState(final String nam)
	{
		JDFDurationState s = getDurationState(nam);
		if (s == null)
			s = appendDurationState(nam);
		return s;
	}

	// ///////////////////////////////////////////////////////////////////

	/**
	 * @deprecated use method with parameter (string) instead
	 * @return
	 */
	@Deprecated
	public JDFDurationState appendDurationState()
	{
		return (JDFDurationState) appendElement(ElementName.DURATIONSTATE, null);
	}

	// ///////////////////////////////////////////////////////////////////

	/**
	 * appends a DurationState with @Name="name"
	 *
	 * @param nam the Name attribute of the newly appended DurationState
	 * @return JDFDurationState: the newly appended DurationState
	 */
	public JDFDurationState appendDurationState(final String nam)
	{
		final JDFDurationState s = (JDFDurationState) appendElement(ElementName.DURATIONSTATE, null);
		s.setName(nam);
		return s;
	}

	// ///////////////////////////////////////////////////////////////////

	/**
	 * gets the iSkip'th existing PDFPathState
	 *
	 * @param iSkip number of elements to skip (0 -> get first element)
	 * @return JDFPDFPathState: the existing PDFPathState
	 */
	public JDFPDFPathState getPDFPathState(final int iSkip)
	{
		return (JDFPDFPathState) getElement(ElementName.PDFPATHSTATE, null, iSkip);
	}

	// ///////////////////////////////////////////////////////////////////

	/**
	 * gets an existing PDFPathState with @Name="name"
	 *
	 * @param nam the Name attribute of the newly appended PDFPathState
	 * @return JDFPDFPathState: the existing PDFPathState
	 */
	public JDFPDFPathState getPDFPathState(final String nam)
	{
		return (JDFPDFPathState) getChildWithAttribute(ElementName.PDFPATHSTATE, AttributeName.NAME, null, nam, 0, true);
	}

	// ///////////////////////////////////////////////////////////////////

	/**
	 * @deprecated use method with parameter (string) instead
	 * @return
	 */
	@Deprecated
	public JDFPDFPathState getCreatePDFPathState(final int iSkip)
	{
		return (JDFPDFPathState) getCreateElement(ElementName.PDFPATHSTATE, null, iSkip);
	}

	// ///////////////////////////////////////////////////////////////////

	/**
	 * gets a PDFPathState with @Name="name", appends it if it does not yet exist
	 *
	 * @param nam the Name attribute of the newly appended PDFPathState
	 * @return JDFPDFPathState: the existing or newly appended PDFPathState
	 */
	public JDFPDFPathState getCreatePDFPathState(final String nam)
	{
		JDFPDFPathState s = getPDFPathState(nam);
		if (s == null)
			s = appendPDFPathState(nam);
		return s;
	}

	// ///////////////////////////////////////////////////////////////////

	/**
	 * @deprecated use method with parameter (string) instead
	 * @return
	 */
	@Deprecated
	public JDFPDFPathState appendPDFPathState()
	{
		return (JDFPDFPathState) appendElement(ElementName.PDFPATHSTATE, null);
	}

	// ///////////////////////////////////////////////////////////////////

	/**
	 * appends a PDFPathState with @Name="name"
	 *
	 * @param nam the Name attribute of the newly appended PDFPathState
	 * @return JDFPDFPathState: the newly appended PDFPathState
	 */
	public JDFPDFPathState appendPDFPathState(final String nam)
	{
		final JDFPDFPathState s = (JDFPDFPathState) appendElement(ElementName.PDFPATHSTATE, null);
		s.setName(nam);
		return s;
	}

	// ///////////////////////////////////////////////////////////////////
	// ///////////////////////////////////////////////////////////////////

	/**
	 * gets the iSkip'th existing RectangleState
	 *
	 * @param iSkip number of elements to skip (0 -> get first element)
	 * @return JDFRectangleState: the existing RectangleState
	 */
	public JDFRectangleState getRectangleState(final int iSkip)
	{
		return (JDFRectangleState) getElement(ElementName.RECTANGLESTATE, null, iSkip);
	}

	// ///////////////////////////////////////////////////////////////////

	/**
	 * gets an existing RectangleState with @Name="name"
	 *
	 * @param nam the Name attribute of the newly appended RectangleState
	 * @return JDFRectangleState: the existing RectangleState
	 */
	public JDFRectangleState getRectangleState(final String nam)
	{
		return (JDFRectangleState) getChildWithAttribute(ElementName.RECTANGLESTATE, AttributeName.NAME, null, nam, 0, true);
	}

	// ///////////////////////////////////////////////////////////////////

	/**
	 * @deprecated use method with parameter (string) instead
	 * @return
	 */
	@Deprecated
	public JDFRectangleState getCreateRectangleState(final int iSkip)
	{
		return (JDFRectangleState) getCreateElement(ElementName.RECTANGLESTATE, null, iSkip);
	}

	// ///////////////////////////////////////////////////////////////////

	/**
	 * gets a RectangleState with @Name="name", appends it if it does not yet exist
	 *
	 * @param nam the Name attribute of the newly appended RectangleState
	 * @return JDFRectangleState: the existing or newly appended RectangleState
	 */
	public JDFRectangleState getCreateRectangleState(final String nam)
	{
		JDFRectangleState s = getRectangleState(nam);
		if (s == null)
			s = appendRectangleState(nam);
		return s;
	}

	// ///////////////////////////////////////////////////////////////////

	/**
	 * @deprecated use method with parameter (string) instead
	 * @return
	 */
	@Deprecated
	public JDFRectangleState appendRectangleState()
	{
		return (JDFRectangleState) appendElement(ElementName.RECTANGLESTATE, null);
	}

	// ///////////////////////////////////////////////////////////////////

	/**
	 * appends a RectangleState with @Name="name"
	 *
	 * @param nam the Name attribute of the newly appended RectangleState
	 * @return JDFRectangleState: the newly appended RectangleState
	 */
	public JDFRectangleState appendRectangleState(final String nam)
	{
		final JDFRectangleState s = (JDFRectangleState) appendElement(ElementName.RECTANGLESTATE, null);
		s.setName(nam);
		return s;
	}

	/**
	 * get the DEvCapPool that contains devcap elements referenced by this
	 *
	 * @return JDFDevCapPool the pool
	 */
	private KElement getParentPool(final String poolName)
	{
		final KElement parent = getPoolParent();
		return parent.getElement(poolName);
	}

	/**
	 * get the DEvCapPool that contains devcap elements referenced by this
	 *
	 * @return JDFDevCapPool the pool
	 */
	private KElement getCreateParentPool(final String poolName)
	{
		final KElement parent = getPoolParent();
		return parent.getCreateElement(poolName);
	}

	private boolean getIgnoreDefaults()
	{
		final KElement p = getPoolParent();
		if (p instanceof JDFDeviceCap)
			return ((JDFDeviceCap) p).isIgnoreDefaults();
		return false;
	}

	private KElement getPoolParent()
	{
		KElement parent = getDeepParent(ElementName.DEVICECAP, 0);
		if (parent == null)
			parent = getDeepParent(ElementName.MESSAGESERVICE, 0);
		if (!(parent instanceof JDFDeviceCap) && !(parent instanceof JDFMessageService))
			throw new JDFException("JDFDevCap.getParentPool - invalid parent context");
		return parent;
	}

	/**
	 * Gets of this the Vector of all direct child DevCap elements plus the referenced (by attribute DevCapRefs) reusable DevCap elements, that are located in DevCapPool
	 *
	 * @param devCaps
	 * @param bDirect
	 *
	 * @return VElement - vector of all direct child DevCap elements plus the referenced reusable DevCap elements, that are located in DevCapPool.
	 */
	public final VElement getDevCapVector(final VElement devCaps, final boolean bDirect)
	{
		int preFill = 0;
		VElement vDevCap = getChildElementVector(ElementName.DEVCAP, null, null, true, 0, false);
		if (devCaps != null)
		{
			preFill = devCaps.size();
			devCaps.appendUnique(vDevCap);
			vDevCap = devCaps;
		}
		else
		// first call
		{
			if (bDirect == false)
				vDevCap.appendUnique(this);
		}

		if (hasAttribute(AttributeName.DEVCAPREFS))
		{
			final JDFDevCapPool devCapPool = (JDFDevCapPool) getParentPool(ElementName.DEVCAPPOOL);
			if (devCapPool != null)
			{
				final VString idRefs = getDevCapRefs();
				for (int i = 0; i < idRefs.size(); i++)
				{
					final JDFDevCap devCap = devCapPool.getDevCap(idRefs.get(i));
					if (devCap != null)
					{
						vDevCap.appendUnique(devCap);
					}
					else
					{
						throw new JDFException("JDFDevCap.getDevCapVector: Attribute DevCapRefs refers to the non-existent DevCap: " + idRefs.get(i));
					}
				}
			}
			else
			{
				throw new JDFException("JDFDevCap.getDevCapVector: Attribute DevCapRefs refers to the non-existent DevCapPool");
			}
		}
		if (bDirect == false)
		{
			for (int i = preFill; i < vDevCap.size(); i++)
			{
				final JDFDevCap dcChild = (JDFDevCap) vDevCap.elementAt(i);
				vDevCap = dcChild.getDevCapVector(vDevCap, bDirect);
			}
		}
		return vDevCap;
	}

	/**
	 * Tests if the attributes and subelements of the given element match the corresponding States and DevCap subelements of this DevCap.<br>
	 * Composes a detailed report of the found errors in XML form. If XMLDoc equals null - there are no errors
	 *
	 * @param e element to test
	 * @param testlists FitsValue_Allowed or FitsValue_Present testlists that are specified for the State elements. (Will be used in fitsValue method of the State element)
	 * @param level validation level
	 * @return XMLDoc - XMLDoc output of the error messages. If XMLDoc is <code>null</code> there are no errors.<br>
	 *         Element <code>e</code> fits the corresponding States and DevCap subelements of this DevCap.
	 */
	public final KElement stateReport(final KElement e, final EnumFitsValue testlists, final EnumValidationLevel level, final boolean ignoreExtensions, final boolean bRecurse,
			final KElement parentReport)
	{
		boolean bRecurseLocal = bRecurse;
		KElement parentReportLocal = parentReport;

		// 'e' in DeviceCapabilities is described by this DevCap

		// first test if there are any subelements of 'e' that are not described
		// by DevCap
		if (!(e instanceof JDFNode) && !ignoreExtensions)
		{
			missingDevCap(e, parentReportLocal);
		}

		// DevCap contains: (1) description of parts;
		// (2) description of subelements;
		// (3) description of attributes

		// (1) Test Partition Leaves: 'e' - is partitioned, its leaves must be
		// described by 'this' DevCap
		if (e instanceof JDFResource && bRecurseLocal)
		{
			final JDFResource res = (JDFResource) e;
			final JDFResource resourceRoot = res.getResourceRoot();
			if (resourceRoot.hasAttribute_KElement(AttributeName.PARTIDKEYS, null, false))
			{
				final VElement vLeaves = res.getLeaves(!EnumPartUsage.Explicit.equals(resourceRoot.getPartUsage()));

				final int size = vLeaves.size();
				for (int i = 0; i < size; i++)
				{
					final JDFResource leaf = (JDFResource) vLeaves.elementAt(i);
					final KElement p = parentReportLocal.appendElement("InvalidPartitionLeaf");
					final KElement partTestResult = stateReport(leaf, testlists, level, ignoreExtensions, false, p);
					if (partTestResult != null)
					{
						final String path = leaf.buildXPath(null, 1);
						p.setAttribute("XPath", path);
						final String leafPath = path.substring(res.buildXPath(null, 1).length());
						p.setAttribute("LeafXPath", res.getNodeName() + leafPath);
					}
					else
					{
						p.deleteNode();
					}
				}
			}
			else
			{
				bRecurseLocal = false; // not partitioned - just pass through e
			}
		}
		else
		{
			bRecurseLocal = false;
		}

		if (!bRecurseLocal)
		{
			subelementsTest(e, testlists, level, ignoreExtensions, parentReportLocal);

			// (3) Test Attributes, Spans and Comments - described by States
			spanAndAttributesTest(e, testlists, level, ignoreExtensions, parentReportLocal);
		}
		if (!parentReportLocal.hasChildElements())
		{
			parentReportLocal = null;
		}
		return parentReportLocal;
	}

	/**
	 * Tests subelements of the element <code>e</code> whether they fit the corresponding DevCap elements of <code>this</code>.<br>
	 * ! Recursively returns to stateReport to test the attributes of the subelements.<br>
	 * Composes a detailed report of the found errors in XML form. <br>
	 * If XMLDoc is <code>null</code> there are no errors.
	 *
	 * @param testElem element to test
	 * @param testlists FitsValue_Allowed or FitsValue_Present testlists that are specified for the State elements. (Will be used in fitsValue method of the State element.)
	 * @param level validation level
	 * @return XMLDoc - XMLDoc output of the error messages. If XMLDoc equals null - there are no errors, 'e' fits the corresponding DevCap elements of 'this' DevCap
	 */
	private final KElement subelementsTest(final KElement testElem, final EnumFitsValue testlists, final EnumValidationLevel level, final boolean ignoreExtensions, final KElement parentReport)
	{
		KElement parentReportLocal = parentReport;

		final VElement vDevCap = getDevCapVector(null, true); // vDevCap - contains
		// DevCap elements of
		// this DevCap

		final HashSet goodElems = new HashSet();
		final HashMap badElems = new HashMap();

		for (int k = 0; k < vDevCap.size(); k++)
		{ // if there are any DevCap subelements in this DavCap
			// then element e must have subelements, and we are going to check
			// them first
			final JDFDevCap dc = (JDFDevCap) vDevCap.elementAt(k);
			final String dcName = dc.getName();
			final EnumAvailability av = dc.getModuleAvailability();
			if (!EnumAvailability.Installed.equals(av))
				continue;

			// vElem - direct children of the Node.
			// If 'dcName' is a partition Leaf - gets only children of the Leaf.
			final VElement vElem = dc.getMatchingElementsFromParent(testElem, vDevCap);

			final int occurs = vElem == null ? 0 : vElem.size();
			KElement r;
			final int minOccurs = dc.getMinOccurs();
			final int maxOccurs = dc.getMaxOccurs();
			if (occurs > maxOccurs && vElem != null)
			{
				for (int j = 0; j < occurs; j++)
				{
					final KElement subEl = vElem.item(j);
					r = parentReportLocal.appendElement("InvalidElement");
					r.setAttribute("CapXPath", dc.getNamePath(true));
					r.setAttribute("XPath", subEl.buildXPath(null, 1));
					r.setAttribute("Name", dcName);
					r.setAttribute("Message", "Element occurrence exceeds value of MaxOccurs");
					r.setAttribute("MaxOccurs", maxOccurs, null); // MaxOccurs
					r.setAttribute("FoundElements", occurs, null);
				}
			}
			else if (occurs < minOccurs && EnumValidationLevel.isRequired(level))
			{
				r = parentReportLocal.appendElement("MissingElement");
				r.setAttribute("CapXPath", dc.getNamePath(true));
				r.setAttribute("XPath", testElem.buildXPath(null, 1) + "/" + dcName);
				r.setAttribute("Name", dcName);
				r.setAttribute("Message", "Element occurrence is less than value of MinOccurs");
				r.setAttribute("MinOccurs", minOccurs, null);
				r.setAttribute("FoundElements", occurs, null);
			}

			if (vElem != null)
			{
				// there were elements that didn't pass some of the tests - assume
				// that these are actually invalid and report on them
				for (int j = 0; j < occurs; j++)
				{
					final KElement subEl = vElem.item(j);
					if (goodElems.contains(subEl))
						continue;
					r = parentReportLocal.appendElement("InvalidElement");
					final KElement recursionResult = dc.stateReport(subEl, testlists, level, ignoreExtensions, true, r);

					if (recursionResult == null)
					{
						goodElems.add(subEl);
						final KElement badReport = (KElement) badElems.get(subEl);
						if (badReport != null)
							badReport.deleteNode();
					}
					else
					{
						recursionResult.appendAttribute("DevCapRefs", dc.getID(), null, " ", true);
						badElems.put(subEl, recursionResult);
					}

					if (recursionResult != null)
					{
						r.setAttribute("CapXPath", dc.getNamePath(true));
						r.setAttribute("XPath", subEl.buildXPath(null, 1));
						r.setAttribute("Name", dcName);
					}
					else
					{
						r.deleteNode();
					}
				}
			}
		}

		if (!parentReportLocal.hasChildElements())
		{
			parentReportLocal = null;
		}

		return parentReportLocal;
	}

	/**
	 * Tests attributes and span elements (if <code>e</code> is a intent resource) of the element <code>e</code>.<br>
	 * Checks whether they fit the corresponding State elements of <code>this</code>.<br>
	 * Composes a detailed report of the errors found in XML form. If XMLDoc is <code>null</code> there are no errors.
	 *
	 * @param e element to test
	 * @param testlists FitsValue_Allowed or FitsValue_Present testlists that are specified for the State elements. (Will be used in fitsValue method of the State element.)
	 * @param level validation level
	 * @return XMLDoc - XMLDoc output of the error messages. If XMLDoc is <code>null</code> there are no errors, <code>e</code> fits the corresponding State elements of <code>this</code>
	 */
	private final KElement spanAndAttributesTest(final KElement e, final EnumFitsValue testlists, final EnumValidationLevel level, final boolean ignoreExtensions, final KElement parentReport)
	{
		KElement parentReportLocal = parentReport;

		final KElement msp = parentReportLocal.appendElement("UnknownAttributes");
		final KElement map = parentReportLocal.appendElement("MissingAttributes");
		final KElement iap = parentReportLocal.appendElement("InvalidAttributes");

		// vSubElem - contains all subelements of this DevCap
		final VElement vStates = getStates(true, null);
		final JDFAttributeMap missMap = new JDFAttributeMap();
		final JDFAttributeMap capMap = new JDFAttributeMap();

		final JDFAttributeMap defMap = (getIgnoreDefaults() || !(e instanceof JDFElement)) ? null : ((JDFElement) e).getDefaultAttributeMap();
		final JDFAttributeMap am = new JDFAttributeMap(defMap);
		am.putAll(e.getAttributeMap()); // only attribute map of 'e'
		final JDFAttributeMap m = getSpanAndAttributesMap(e); // get of 'e' as a map
		// of attributes: (1)
		// all attributes,
		// (2) span key-values (in case of intent resource), (3) comments
		final VString vKeys = m.getKeys(); // we'll use "keys" to find the appropriate
		// State elements in DevCap

		if (vStates != null)
		{
			final int sizeStates = vStates.size();
			for (int j = 0; j < sizeStates; j++) // SubElem can be DevCap, can be
			// Loc, can be any State element
			{ // here we need only States
				final JDFAbstractState state = (JDFAbstractState) vStates.item(j);
				final String nam = state.getName();
				final EnumAvailability av = state.getModuleAvailability();
				if (!EnumAvailability.Installed.equals(av))
					continue;

				final int size = vKeys.size();
				for (int i = size - 1; i >= 0; i--)
				{
					final String key = vKeys.elementAt(i);
					if (nam.equals(key) || (key.equals("CommentText") && nam.length() == 0)) // if
					// name
					// is
					// absent
					// -
					// state
					// describes
					// a
					// Comment
					{
						final String value = nam.length() != 0 ? m.get(key) : m.get("CommentText");
						if (!state.fitsValue(value, testlists))
						{ // The attribute/span was found but it has the wrong value

							KElement r;
							if (!am.containsKey(key) && !key.equals("CommentText")) // it
							// is
							// Span
							// but
							// not
							// Attribute
							{
								r = iap.appendElement("InvalidSpan");
								r.setAttribute("XPath", e.buildXPath(null, 1) + "/" + key);
								r.setAttribute("Message", "Span value doesn't fit this State description");
							}
							else if (key.equals("CommentText"))
							{
								r = iap.appendElement("InvalidComment");
								r.setAttribute("XPath", e.buildXPath(null, 1) + "/" + key);
								r.setAttribute("Message", "Comment doesn't fit this State description");
							}
							else
							{
								r = iap.appendElement("InvalidAttribute");
								r.setAttribute("XPath", e.buildXPath(null, 1) + "/@" + key);
								r.setAttribute("Message", "Attribute value doesn't fit this State description");
							}

							r.setAttribute("Name", key);
							r.setAttribute("CapXPath", state.getNamePath());
							r.setAttribute("Value", value);
							r.copyElement(state, null);

						}

						vKeys.removeElementAt(i); // The attribute/span was found,
						// checked, so we don't need it
						// any more in vKeys
						break; // go to next State
					}
				}

				if ((size == vKeys.size()) && state.getRequired() && EnumValidationLevel.isRequired(level))
				{ // No attribute/span found but state is required

					if (state.getListType().equals(EnumListType.Span))
					{
						missMap.put(nam, "Span");
					}
					else
					{
						missMap.put(nam, "Attribute");
					}
					capMap.put(nam, state.getNamePath());
				}
			}
		}

		EnumValidationLevel l2 = level;
		if (e instanceof JDFResource)
		{
			if (EnumResStatus.Incomplete.equals(((JDFResource) e).getResStatus(false)))
				l2 = EnumValidationLevel.incompleteLevel(level);
		}

		if (EnumValidationLevel.isRequired(l2) && (e instanceof JDFElement))
		{
			final JDFElement je = (JDFElement) e;
			VString missAts = je.getMissingAttributes(9999999);
			for (int i = 0; i < missAts.size(); i++)
				missMap.put(missAts.get(i), "Attribute");
			missAts = je.getMissingElements(99999);
			for (int i = 0; i < missAts.size(); i++)
			{
				final String stringAt = missAts.get(i);
				if (stringAt.endsWith("Span"))
					missMap.put(stringAt, "Span");
			}
		}

		final Iterator<String> missIt = missMap.keySet().iterator();
		while (missIt.hasNext())
		{ // No attribute/span found but state is required
			final String nam = missIt.next();
			final String typ = missMap.get(nam);

			final KElement ms = map.appendElement("Missing" + typ);
			ms.setAttribute("Message", "Missing " + typ);
			ms.setAttribute("XPath", e.buildXPath(null, 1) + "/@" + nam);
			final String capNamePath = capMap.get(nam);
			if (capNamePath != null)
				ms.setAttribute("CapXPath", capNamePath);
			ms.setAttribute("Name", nam);
		}

		if (!ignoreExtensions)
		{
			for (int x = 0; x < vKeys.size(); x++)
			{ // if there are in vKeys still some attibutes, they must be
				// generic attributes
				final String key = vKeys.elementAt(x);

				if (!isGenericAttribute(key) && (defMap == null || !defMap.containsKey(key)))
				{ // if the rest attributes are not generic -> Node is rejected
					KElement us;
					if (!am.containsKey(key) && !key.equals("CommentText"))
					{
						us = msp.appendElement("UnknownSpan");
						us.setAttribute("XPath", e.buildXPath(null, 1) + "/" + key);
						us.setAttribute("CapXPath", getNamePath(true) + "/" + key);
					}
					else
					{
						us = msp.appendElement("UnknownAttribute");
						us.setAttribute("XPath", e.buildXPath(null, 1) + "/@" + key);
						us.setAttribute("CapXPath", getNamePath(true) + "/@" + key);
					}
					us.setAttribute("Name", key);
					us.setAttribute("Message", "No State description for " + key);
				}
			}
		}

		if (!map.hasChildElements())
			parentReportLocal.removeChild(map);

		if (!iap.hasChildElements())
			parentReportLocal.removeChild(iap);

		if (!msp.hasChildElements())
			parentReportLocal.removeChild(msp);

		if (!parentReportLocal.hasChildElements())
		{
			parentReportLocal = null;
		}

		return parentReportLocal;
	}

	/**
	 * Gets a map of Attributes, Comments and Span key-value pairs for <code>e</code>. All of them must be described as State elements.
	 *
	 * @param e element to get the attribute map for
	 * @return JDFAttributeMap - a map of Attributes, Comments and Span key-value pairs
	 */
	private final JDFAttributeMap getSpanAndAttributesMap(final KElement e)
	{
		final JDFAttributeMap defMap = (getIgnoreDefaults() || !(e instanceof JDFElement)) ? null : ((JDFElement) e).getDefaultAttributeMap();
		final JDFAttributeMap m = new JDFAttributeMap(defMap);
		m.putAll(e.getAttributeMap()); // only attribute map of 'e'

		if (e instanceof JDFResource)
		{
			final JDFResource r = (JDFResource) e; // if 'e' is an Intent Resource
			// add to 'm' a 'spanMap'
			if (r.getResourceClass() == EnumResourceClass.Intent)
			{
				final JDFAttributeMap spanMap = getSpanValueMap(r);
				final VString vSpanMapKeys = spanMap.getKeys();
				for (int k = 0; k < vSpanMapKeys.size(); k++)
				{
					final String spanKey = vSpanMapKeys.elementAt(k);
					m.put(spanKey, spanMap.get(spanKey));
				}
			}
		}
		else if (e instanceof JDFComment)
		{
			final JDFComment c = (JDFComment) e;
			m.put("CommentText", c.getText(0));
		}

		return m;
	}

	/**
	 * Gets a map of key-value pairs for the intent resource <code>r</code>. The key of the map is a Span NodeName, the value is a combination of actual, preferred and range values
	 *
	 * @param r intent resource to get the SpanValueMap of
	 * @return JDFAttributeMap - SpanValueMap
	 */
	private final JDFAttributeMap getSpanValueMap(final JDFResource r)
	{
		final JDFAttributeMap map = new JDFAttributeMap();
		final VElement v = r.getChildElementVector(null, null, null, true, 0, false);
		for (int i = 0; i < v.size(); i++)
		{
			final KElement el = v.item(i);
			if (el instanceof JDFSpanBase)
			{
				// JDFSpanBase span = (JDFSpanBase) el;
				// String value = span.getValue();

				String value = el.hasAttribute(AttributeName.ACTUAL) ? el.getAttribute(AttributeName.ACTUAL) : el.getAttribute(AttributeName.PREFERRED);

				if (value.length() == 0)
					value = el.getAttribute(AttributeName.RANGE);

				map.put(el.getNodeName(), value);
			}
		}
		return map;
	}

	/**
	 * Tests if there are any subelements of the element elem that are not described by DevCap.<br>
	 * Composes a detailed report of the found errors in XML form. If XMLDoc is <code>null</code> there are no errors.
	 *
	 * @param elem element to test
	 * @return KElement - output of the error messages. If XMLDoc is <code>null</code> there are no errors.
	 */
	private final KElement missingDevCap(final KElement elem, final KElement parentReport)
	{
		KElement root = parentReport.appendElement("UnknownElements");
		final VElement vDevCap = getDevCapVector(null, true);

		final VElement vSubElem = elem.getChildElementVector(null, null, null, true, 0, true); // follows the refelements
		// for every one child of the 'elem' we look for the corresponding
		// DevCap description
		for (int i = 0; i < vSubElem.size(); i++)
		{
			final KElement e = vSubElem.item(i);
			if (e instanceof JDFSpanBase)
			{
				continue; // Spans are described as State elements not as DevCap
			}
			else if (e instanceof JDFNode)
			{
				continue; // nodes are described as high level DevCaps
			}
			else
			{
				final String nam = e.getNodeName(); // we are looking for DevCap whose
				// atr_Name is equal 'nam'
				boolean bFound = false;
				EnumAvailability foundAv = null;

				for (int k = 0; k < vDevCap.size(); k++)
				{
					final JDFDevCap devCap = (JDFDevCap) vDevCap.elementAt(k);
					if (devCap.getName().equals(nam)) // getName() as default
					// takes the name of a
					// parent DevCaps
					{
						final EnumAvailability moduleAvailability = devCap.getModuleAvailability();
						if (EnumAvailability.Installed.equals(moduleAvailability))
						{
							bFound = true;
							break;
						}
						else if (foundAv == null)
						{
							foundAv = moduleAvailability;
						}
						else if (moduleAvailability != null && foundAv.getValue() < moduleAvailability.getValue())
						{
							foundAv = moduleAvailability;
						}
					}
				}
				if (!bFound)
				{
					final KElement us = root.appendElement("UnknownElement");
					us.setAttribute("XPath", e.buildXPath(null, 1));
					us.setAttribute("CapXPath", getNamePath(false) + JDFConstants.SLASH + nam);
					us.setAttribute("Name", nam);
					us.setAttribute("Message", "Found no DevCap description for this element");
					us.setAttribute("Availability", foundAv == null ? "None" : foundAv.getName());
				}
			}
		}

		if (!root.hasChildElements())
		{
			root.deleteNode();
			root = null;
		}
		return root;
	}

	/**
	 * Checks if the attributes key of the tested element is a generic attribute. (Gets this attribute of DeviceCap element)
	 *
	 * @param key attribute key to test
	 * @return boolean - true if the key is a generic attribute
	 */
	private final boolean isGenericAttribute(final String key)
	{
		if (key == null)
			return true;
		if (key.startsWith(AttributeName.XMLNS))
			return true;

		KElement deviceCap = getDeepParent(ElementName.DEVICECAP, 0);
		if (deviceCap == null)
			deviceCap = getDeepParent(ElementName.MESSAGESERVICE, 0);
		if (deviceCap == null)
			return false;

		final VString s = StringUtil.tokenize(deviceCap.getAttribute(AttributeName.GENERICATTRIBUTES), " ", false);
		return s != null && (s.contains(key) || s.contains("*"));
	}

	/**
	 * Gets the NamePath of this DevCap in form "DevCapsName/SubelemName1/SubelemName2/..."<br>
	 * If this DevCap is located in DevCapPool and not in a DevCaps - it describes the reusable resource and DevCap root will have the attribute "Name" = value of DevCaps/@Name, but will have no info
	 * about DevCaps/@Context or DevCaps/@LinkUsage
	 * <p>
	 * default: getNamePath(false)
	 *
	 * @param bRecurse if true, returns "DevCapsName/SubelemName1/SubelemName2/..."
	 * @return String - NamePath of this DevCap
	 */
	public final String getNamePath(final boolean bRecurse)
	{
		final VString paths = getNamePathVector(bRecurse);
		if (paths == null || paths.size() < 1)
			return null;
		return paths.get(0);

	}

	@Override
	public VString getNamePathVector()
	{
		return getNamePathVector(true);
	}

	/**
	 * Gets the NamePath of this DevCap in form "DevCapsName/SubelemName1/SubelemName2/..."<br>
	 * If this DevCap is located in DevCapPool and not in a DevCaps - it describes the reusable resource and DevCap root will have the attribute "Name" = value of DevCaps/@Name, but will have no info
	 * about DevCaps/@Context or DevCaps/@LinkUsage
	 *
	 * @param bRecurse if true, returns "DevCapsName/SubelemName1/SubelemName2/..."
	 * @return String - NamePath of this DevCap, null if no name is specified
	 *
	 *         default: getNamePath(true)
	 */
	public final VString getNamePathVector(final boolean bRecurse)
	{
		final String result = getName();
		VString vResult = null;
		if (result == null)
			return null;

		final KElement parentNode = getParentNode_KElement();

		if (parentNode instanceof JDFDevCap)
		{ // subsub elem - always recurse
			final JDFDevCap devCap = (JDFDevCap) parentNode;
			vResult = devCap.getNamePathVector(bRecurse);
		}
		else if (parentNode instanceof JDFDevCapPool) // if DevCap is located
		// in DevCapPool it is
		// reusable and there
		// are no info from
		// DevCaps
		// (Context,LinkUsage)
		{
			if (bRecurse)
			{
				final String id = getID();
				if (!id.equals(""))
				{
					final VElement v = parentNode.getChildrenByTagName("DevCap", null, null, false, true, 0);
					final KElement deviceCap = parentNode.getParentNode_KElement();

					for (int i = 0; i < v.size(); i++)
					{
						final JDFDevCap dc = (JDFDevCap) v.elementAt(i);
						final VString refs = dc.getDevCapRefs();
						if (refs.contains(id))
						{
							if (vResult == null)
								vResult = new VString();
							final String dcID = dc.getAttribute(AttributeName.ID, null, null);
							final JDFDevCaps dcs = (JDFDevCaps) (dcID == null ? null : deviceCap.getChildWithAttribute(ElementName.DEVCAPS, AttributeName.DEVCAPREF, null, dcID, 0, true));
							if (dcs != null)
							{
								vResult.appendUnique(dcs.getNamePathVector());
							}
							else
							{
								vResult.appendUnique(dc.getNamePathVector(bRecurse));
							}
						}
					}
					final JDFDevCaps dcs = (JDFDevCaps) deviceCap.getChildWithAttribute(ElementName.DEVCAPS, AttributeName.DEVCAPREF, null, id, 0, true);
					if (dcs != null)
					{
						final VString vResult2 = dcs.getNamePathVector();
						if (vResult == null)
						{
							vResult = vResult2;
						}
						else
						{
							StringUtil.concatStrings(vResult, "/" + result);
							vResult.appendUnique(vResult2);
						}
						return vResult;
					}
					// TODO mixed mode devcaps subelements + devcappool
				}
			}
		}
		else if (parentNode instanceof JDFDevCaps) // need to add jdf in case
		// of Context="Element"
		// or Context="JDF"
		{
			final JDFDevCaps devCaps = (JDFDevCaps) parentNode;
			vResult = devCaps.getNamePathVector();
			return vResult; // return here directly because the first devCap in
			// the tree repeats the name of the devCaps
		}
		if (vResult == null)
		{
			vResult = new VString();
			vResult.add(result);
		}
		else
		{
			StringUtil.concatStrings(vResult, "/" + result);
		}
		return vResult;
	}

	/**
	 * gets String attribute Name, inherits from devcap or devcaps if necessary
	 *
	 * @return String - the value of the attribute
	 */
	@Override
	public String getName()
	{
		String s = getAttribute(AttributeName.NAME, null, null);
		if (s == null)
		{
			final KElement parentNode = getParentNode_KElement();
			if (parentNode instanceof JDFDevCaps)
			{
				final JDFDevCaps devCaps = (JDFDevCaps) getParentNode();
				s = devCaps.getName();
			}
			else if (parentNode instanceof JDFDevCap)
			{
				final JDFDevCap devCap = (JDFDevCap) getParentNode();
				s = devCap.getName();
			}
			else if (parentNode instanceof JDFDevCapPool)
			{
				final String id = getID();
				if (!id.equals(""))
				{
					final JDFDeviceCap dec = (JDFDeviceCap) parentNode.getParentNode();
					final JDFDevCaps dcs = (JDFDevCaps) dec.getChildWithAttribute(ElementName.DEVCAPS, AttributeName.DEVCAPREF, null, id, 0, true);
					if (dcs != null)
						s = dcs.getName();

				}
			}
		}
		return s;
	}

	/**
	 * return the vector of all states
	 *
	 * @param bDirect if false, recurse into child elements, else return only direct child states
	 * @param id ID attribute of the requested string
	 * @return VElement
	 */
	public VElement getStates(final boolean bDirect, final String id)
	{
		JDFAttributeMap m = null;
		if (id != null)
		{
			m = new JDFAttributeMap(AttributeName.ID, id);
		}
		VElement v = null;
		if (bDirect == true)
		{
			v = getChildrenByTagName(null, null, m, bDirect, true, 0);
			for (int i = v.size() - 1; i >= 0; i--)
			{
				if (!(v.elementAt(i) instanceof JDFAbstractState))
					v.remove(i);
			}
		}
		else
		{
			v = new VElement();
			final VElement vdevCap = getDevCapVector(null, false);
			for (int i = 0; i < vdevCap.size(); i++)
			{
				final JDFDevCap child = (JDFDevCap) vdevCap.elementAt(i);
				v.appendUnique(child.getStates(true, id));
			}
		}
		return v;
	}

	/**
	 * Sets attribute <code>MaxOccurs</code>, also handles unbounded
	 *
	 * @param value the value to set the attribute to
	 */
	@Override
	public void setMaxOccurs(final int value)
	{
		if (value == Integer.MAX_VALUE)
		{
			setAttribute(AttributeName.MAXOCCURS, JDFConstants.POSINF, null);
		}
		else
		{
			setAttribute(AttributeName.MAXOCCURS, value, null);
		}
	}

	/**
	 * Gets integer attribute <code>MaxOccurs</code>, also handles unbounded
	 *
	 * @return int: the attribute value
	 */
	@Override
	public int getMaxOccurs()
	{
		final String s = getAttribute(AttributeName.MAXOCCURS, null, null);
		if (JDFConstants.UNBOUNDED.equals(s))
			return Integer.MAX_VALUE;
		return StringUtil.parseInt(s, 1);
	}

	/**
	 * gets the matching elements in the node that match <code>this</code>
	 *
	 * @param node the node to search in
	 * @param testValidity if true, recusively check for validity of the elements, else only get children by name
	 *
	 * @return VElement - the element vector of matching elements, <code>null</code> if none were found
	 */
	public VElement getMatchingElementsFromParent(final KElement parent, final VElement vDevCap)
	{
		final VElement v = getAllMatchingElementsFromParent(parent);
		if (v == null)
			return null;

		final String _name = getName();
		final VElement vOther = new VElement();
		if (vDevCap != null)
		{
			for (int i = 0; i < vDevCap.size(); i++)
			{
				final JDFDevCap dcOther = (JDFDevCap) vDevCap.elementAt(i);
				if (dcOther == this)
					continue;
				if (!_name.equals(dcOther.getName()))
					continue;
				final VElement vOtherMatch = dcOther.getAllMatchingElementsFromParent(parent);
				if (vOtherMatch == null)
					continue;
				vOther.add(dcOther);
			}
		}
		if (vOther.size() == 0) // no other elements that we have to worry about
			return v;

		final XMLDoc doc = new XMLDoc("dummy", null);
		final KElement repRootDummy = doc.getRoot();
		for (int i = v.size() - 1; i >= 0; i--)
		{
			final KElement e = v.item(i);

			repRootDummy.flush();
			if (spanAndAttributesTest(e, EnumFitsValue.Allowed, EnumValidationLevel.Incomplete, true, repRootDummy) != null
					|| subelementsTest(e, EnumFitsValue.Allowed, EnumValidationLevel.Incomplete, true, repRootDummy) != null)
			{
				// check if an element fits to a different devcap in this, if so
				// remove it from the check
				for (int j = 0; j < vOther.size(); j++)
				{
					repRootDummy.flush();
					final JDFDevCap dcOther = (JDFDevCap) vOther.elementAt(j);
					if (dcOther.spanAndAttributesTest(e, EnumFitsValue.Allowed, EnumValidationLevel.Incomplete, true, repRootDummy) == null
							&& dcOther.subelementsTest(e, EnumFitsValue.Allowed, EnumValidationLevel.Incomplete, true, repRootDummy) == null)
					{
						v.remove(i);
						break; // j
					}
				}
			}
		}
		return v.size() != 0 ? v : null;
	}

	/**
	 * gets the matching elements in the node that match the nodename of this
	 *
	 * @param node the node to search in
	 * @return VElement - the element vector of matching elements, <code>null</code> if none were found
	 */
	public VElement getAllMatchingElementsFromParent(final KElement parent)
	{
		final String nam = getName();
		JDFAttributeMap map = null;
		if (parent instanceof JDFJMF)
		{
			final JDFNameState ns = getNameState(AttributeName.TYPE);
			if (ns != null)
			{
				final VString valList = ns.getAllowedValueList();
				if (valList != null)
				{
					map = new JDFAttributeMap(AttributeName.TYPE, valList.get(0));
				}
			}
		}

		final VElement subElems = parent.getChildElementVector(nam, null, map, true, 999999, true);
		return subElems.size() == 0 ? null : subElems;
	}

	/**
	 * sets the element and attribute defaults
	 *
	 * @param element the element that is defaulted
	 * @param bAll if false, only add if minOccurs>=1 and required=true or a default exists
	 * @return ignored
	 */
	public boolean setDefaultsFromCaps(final KElement element, final boolean bAll)
	{
		boolean success = false;
		if (element instanceof JDFResource)
		{
			final JDFResource r = (JDFResource) element;
			if (!r.isLeaf())
			{
				final VElement vR = r.getLeaves(false);
				for (int i = 0; i < vR.size(); i++)
				{
					success = setDefaultsFromCaps(vR.item(i), bAll) || success;
				}
				return success;
			}
		}
		// default leaf or element case -
		VElement vSubDevCap = null;
		try
		{
			vSubDevCap = getDevCapVector(null, true);
		}
		catch (final JDFException x)
		{
			return false;
		}
		for (int i = 0; i < vSubDevCap.size(); i++)
		{
			final JDFDevCap subDevCap = (JDFDevCap) vSubDevCap.elementAt(i);
			int minOccurs = subDevCap.getMinOccurs();
			if (minOccurs == 0 && bAll)
				minOccurs = 1;
			VElement subElms = subDevCap.getMatchingElementsFromParent(element, vSubDevCap);
			if (minOccurs > 0)
			{
				final int occurs = subElms == null ? 0 : subElms.size();
				if (occurs < minOccurs && subElms == null)
					subElms = new VElement();

				if (subElms != null)
				{
					for (int ii = occurs; ii < minOccurs; ii++)
					{
						final String id = subDevCap.getID();
						final KElement isThere = id == null ? null : element.getOwnerDocument_KElement().getRoot().getTarget(id, AttributeName.ID);
						if (!(isThere instanceof JDFResource) || !(element instanceof JDFElement))
						{
							final KElement newSub = element.appendElement(subDevCap.getName(), subDevCap.getDevNS());
							subElms.add(newSub);
						}
						else
						{
							final JDFRefElement re = ((JDFElement) element).refElement((JDFResource) isThere);
							subElms.add(re.getTarget());
						}

						success = true;
					}
				}
			}

			if (subElms != null)
			{
				final int subSize = subElms.size();
				for (int ii = 0; ii < subSize; ii++)
				{
					success = subDevCap.setDefaultsFromCaps(subElms.item(ii), bAll) || success;
				}
			}
		}

		// states
		final VElement vStates = getStates(true, null);
		for (int i = 0; i < vStates.size(); i++)
		{
			final JDFAbstractState state = (JDFAbstractState) vStates.elementAt(i);
			success = state.setDefaultsFromCaps(element, bAll) || success;
		}

		return false;
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see org.cip4.jdflib.core.JDFElement#getInvalidAttributes(org.cip4.jdflib. core.KElement.EnumValidationLevel, boolean, int)
	 */
	@Override
	public VString getInvalidAttributes(final EnumValidationLevel level, final boolean bIgnorePrivate, final int nMax)
	{
		final VString vs = super.getInvalidAttributes(level, bIgnorePrivate, nMax);
		if (nMax > 0 && vs.size() > nMax)
			return vs;

		if (EnumValidationLevel.isRecursive(level) && !vs.contains(AttributeName.DEVCAPREFS) && hasAttribute(AttributeName.DEVCAPREFS))
		{
			final JDFDeviceCap deviceCap = (JDFDeviceCap) getDeepParent(ElementName.DEVICECAP, 0);
			final JDFDevCapPool devCapPool = deviceCap == null ? null : deviceCap.getDevCapPool();
			if (devCapPool == null)
			{
				vs.add(AttributeName.DEVCAPREFS);
				return vs;
			}
			final VString idRefs = getDevCapRefs();
			for (int i = 0; i < idRefs.size(); i++)
			{
				final JDFDevCap devCap = devCapPool.getDevCap(idRefs.get(i));
				if (devCap == null)
				{
					vs.add(AttributeName.DEVCAPREFS);
					return vs;
				}
			}
		}
		String nam = getName();
		if (!vs.contains(AttributeName.NAME) && nam != null && getDevNS().equals(JDFElement.getSchemaURL()))
		{
			nam = KElement.xmlnsLocalName(nam);
			final JDFDoc tmpDoc = new JDFDoc(nam);
			final KElement tmpRoot = tmpDoc.getRoot();
			if (JDFElement.class.equals(tmpRoot.getClass()))
			{
				vs.add(AttributeName.DEVNS); // the element is not a known jdf
				// resource
			}
		}
		return vs;
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see org.cip4.jdflib.ifaces.IModuleCapability#getModulePool()
	 */
	@Override
	public JDFModulePool getModulePool()
	{
		return (JDFModulePool) getParentPool(ElementName.MODULEPOOL);
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see org.cip4.jdflib.ifaces.IModuleCapability#getModulePool()
	 */
	@Override
	public JDFModulePool getCreateModulePool()
	{
		return (JDFModulePool) getCreateParentPool(ElementName.MODULEPOOL);
	}

	/**
	 * get the availability of this devcaps based on the list of installed modules in ModuleRefs and ModulePool
	 *
	 * @return
	 */
	public EnumAvailability getModuleAvailability()
	{
		return JDFModulePool.getModuleAvailability(this);
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see org.cip4.jdflib.ifaces.IModuleCapability#appendModuleRef(java.lang.String )
	 */
	@Override
	public JDFModuleCap appendModuleRef(final String id)
	{
		return JDFModulePool.appendModuleRef(this, id);
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see org.cip4.jdflib.ifaces.ICapabilityElement#getEvaluationType()
	 */
	@Override
	public EnumTerm getEvaluationType()
	{
		return EnumTerm.IsPresentEvaluation;
	}

	// /////////////////////////////////////////////////////
}
