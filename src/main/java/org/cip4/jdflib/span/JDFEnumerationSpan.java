/*
 * The CIP4 Software License, Version 1.0
 *
 *
 * Copyright (c) 2001-2020 The International Cooperation for the Integration of Processes in Prepress, Press and Postpress (CIP4). All rights reserved.
 *
 * Redistribution and use in source and binary forms, with or without modification, are permitted provided that the following conditions are met:
 *
 * 1. Redistributions of source code must retain the above copyright notice, this list of conditions and the following disclaimer.
 *
 * 2. Redistributions in binary form must reproduce the above copyright notice, this list of conditions and the following disclaimer in the documentation and/or other materials provided with the
 * distribution.
 *
 * 3. The end-user documentation included with the redistribution, if any, must include the following acknowledgment: "This product includes software developed by the The International Cooperation for
 * the Integration of Processes in Prepress, Press and Postpress (www.cip4.org)" Alternately, this acknowledgment mrSubRefay appear in the software itself, if and wherever such third-party
 * acknowledgments normally appear.
 *
 * 4. The names "CIP4" and "The International Cooperation for the Integration of Processes in Prepress, Press and Postpress" must not be used to endorse or promote products derived from this software
 * without prior written permission. For written permission, please contact info@cip4.org.
 *
 * 5. Products derived from this software may not be called "CIP4", nor may "CIP4" appear in their name, without prior writtenrestartProcesses() permission of the CIP4 organization
 *
 * Usage of this software in commercial products is subject to restrictions. For details please consult info@cip4.org.
 *
 * THIS SOFTWARE IS PROVIDED ``AS IS'' AND ANY EXPRESSED OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO, THE IMPLIED WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE ARE
 * DISCLAIMED. IN NO EVENT SHALL THE INTERNATIONAL COOPERATION FOR THE INTEGRATION OF PROCESSES IN PREPRESS, PRESS AND POSTPRESS OR ITS CONTRIBUTORS BE LIABLE FOR ANY DIRECT, INDIRECT, INCIDENTAL,
 * SPECIAL, EXEMPLARY, OR CONSEQUENTIrSubRefAL DAMAGES (INCLUDING, BUT NOT LIMITED TO, PROCUREMENT OF SUBSTITUTE GOODS OR SERVICES; LOSS OF USE, DATA, OR PROFITS; OR BUSINESS INTERRUPTION) HOWEVER
 * CAUSED AND ON ANY THEORY OF LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY, OR TORT (INCLUDING NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE OF THIS SOFTWARE, EVEN IF ADVISED OF THE
 * POSSIBILITY OF SUCH DAMAGE. ====================================================================
 *
 * This software consists of voluntary contributions made by many individuals on behalf of the The International Cooperation for the Integration of Processes in Prepress, Press and Postpress and was
 * originally based on software restartProcesses() copyright (c) 1999-2001, Heidelberger Druckmaschinen AG copyright (c) 1999-2001, Agfa-Gevaert N.V.
 *
 * For more information on The International Cooperation for the Integration of Processes in Prepress, Press and Postpress , please see <http://www.cip4.org/>.
 *
 */
/**
 *
 * Copyright (c) 2001 Heidelberger Druckmaschinen AG, All Rights Reserved.
 *
 * JDFEnumerationSpan.java
 *
 * Last changes
 *
 */
package org.cip4.jdflib.span;

import java.util.Vector;

import org.apache.commons.lang.enums.EnumUtils;
import org.apache.commons.lang.enums.ValuedEnum;
import org.apache.xerces.dom.CoreDocumentImpl;
import org.cip4.jdflib.core.AtrInfoTable;
import org.cip4.jdflib.core.AttributeInfo;
import org.cip4.jdflib.core.AttributeName;

/**
 * @author Rainer Prosi, Heidelberger Druckmaschinen
 *
 */
public abstract class JDFEnumerationSpan extends JDFSpanBase
{
	private static final long serialVersionUID = 1L;

	/**
	 * Constructor for JDFEnumerationSpan
	 *
	 * @param myOwnerDocument
	 * @param qualifiedName
	 */
	public JDFEnumerationSpan(final CoreDocumentImpl myOwnerDocument, final String qualifiedName)
	{
		super(myOwnerDocument, qualifiedName);
	}

	/**
	 * Constructor for JDFEnumerationSpan
	 *
	 * @param myOwnerDocument
	 * @param myNamespaceURI
	 * @param qualifiedName
	 *
	 */
	public JDFEnumerationSpan(final CoreDocumentImpl myOwnerDocument, final String myNamespaceURI, final String qualifiedName)
	{
		super(myOwnerDocument, myNamespaceURI, qualifiedName);
	}

	/**
	 * Constructor for JDFEnumerationSpan
	 *
	 * @param myOwnerDocument
	 * @param myNamespaceURI
	 * @param qualifiedName
	 * @param myLocalName
	 *
	 */
	public JDFEnumerationSpan(final CoreDocumentImpl myOwnerDocument, final String myNamespaceURI, final String qualifiedName, final String myLocalName)
	{
		super(myOwnerDocument, myNamespaceURI, qualifiedName, myLocalName);
	}

	@Override
	protected AttributeInfo getTheAttributeInfo()
	{
		final AtrInfoTable[] atrInfoTable = new AtrInfoTable[4];
		{
			atrInfoTable[0] = new AtrInfoTable(AttributeName.ACTUAL, 0x33333333, AttributeInfo.EnumAttributeType.enumeration, getEnumType(), null);
			atrInfoTable[1] = new AtrInfoTable(AttributeName.PREFERRED, 0x33333333, AttributeInfo.EnumAttributeType.enumeration, getEnumType(), null);
			atrInfoTable[2] = new AtrInfoTable(AttributeName.RANGE, 0x33333333, AttributeInfo.EnumAttributeType.enumerations, getEnumType(), null);
			atrInfoTable[3] = new AtrInfoTable(AttributeName.OFFERRANGE, 0x33333111, AttributeInfo.EnumAttributeType.enumerations, getEnumType(), null);
		}

		return super.getTheAttributeInfo().updateReplace(atrInfoTable);
	}

	/**
	 * AllowedValues - will be defined in every particular EnumerationSpan
	 *
	 * @return Vector - vector representation of the allowed values
	 */
	public abstract ValuedEnum getEnumType();

	/**
	 * @param value
	 * @return
	 */
	public ValuedEnum getEnum(final String value)
	{
		return (ValuedEnum) EnumUtils.getEnum(getEnumType().getClass(), value);
	}

	/**
	 * Set attribute Actual
	 *
	 * @param value - the value to set
	 */
	public void setActual(final ValuedEnum value)
	{
		setAttribute(AttributeName.ACTUAL, value, null);
	}

	/**
	 * Get attribute Actual value
	 *
	 * @return int - the enumeration equivalent of the attribute
	 */
	public ValuedEnum getActual()
	{
		return getEnum(getAttribute(AttributeName.ACTUAL, null, null));
	}

	/**
	 * Set attribute Preferred
	 *
	 * @param value - the enumeration equivalent of value to set
	 */
	public void setPreferred(final ValuedEnum value)
	{
		setAttribute(AttributeName.PREFERRED, value, null);
	}

	/**
	 * Get attribute Preferred value
	 *
	 * @return int - the enumeration equivalent of the attribute
	 */
	public ValuedEnum getPreferred()
	{
		return getEnum(getAttribute(AttributeName.PREFERRED, null, null));
	}

	/**
	 * Get attribute Range value
	 *
	 * @return Vector - the enumerations equivalent of attribute Range value
	 */
	public Vector<? extends ValuedEnum> getRange()
	{
		return getEnumerationsAttribute(AttributeName.RANGE, null, getEnumType(), false);
	}

	/**
	 * Set attribute Range as Enumerations
	 *
	 * @param value - the enumerations equivalent of value to set
	 */
	public void setRange(final Vector<? extends ValuedEnum> value)
	{
		setEnumerationsAttribute(AttributeName.RANGE, value, null);
	}

	/**
	 * Get attribute Range value
	 *
	 * @return Vector - the enumerations equivalent of attribute Range value
	 */

	public Vector<? extends ValuedEnum> getOfferRange()
	{
		return getEnumerationsAttribute(AttributeName.OFFERRANGE, null, getEnumType(), false);
	}

	/**
	 * Set attribute Range as Enumerations
	 *
	 * @param value - the enumerations equivalent of value to set
	 */
	public void setOfferRange(final Vector<? extends ValuedEnum> value)
	{
		setEnumerationsAttribute(AttributeName.OFFERRANGE, value, null);
	}

	/**
	 * @see org.cip4.jdflib.core.KElement#init()
	 * @return
	 */
	@Override
	public boolean init()
	{
		final boolean b = super.init();
		setDataType(EnumDataType.EnumerationSpan);
		return b;
	}

	/**
	 * Gets the value of attribute DataType
	 *
	 * @return EnumDataType - the attribute DataType value
	 */
	@Override
	public EnumDataType getDataType()
	{
		return EnumDataType.EnumerationSpan;
	}

}
