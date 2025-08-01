/*
 *
 * The CIP4 Software License, Version 1.0
 *
 *
 * Copyright (c) 2001-2004 The International Cooperation for the Integration of 
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

/**
 * Copyright (c) 2001 Heidelberger Druckmaschinen AG, All Rights Reserved.
 * 
 * @author Elena Skobchenko
 *
 * JDFBooleanState.java
 *
 */

package org.cip4.jdflib.resource.devicecapability;

import java.util.Vector;

import org.apache.commons.lang.enums.ValuedEnum;
import org.apache.xerces.dom.CoreDocumentImpl;
import org.cip4.jdflib.core.AtrInfoTable;
import org.cip4.jdflib.core.AttributeInfo;
import org.cip4.jdflib.core.AttributeName;
import org.cip4.jdflib.core.ElemInfoTable;
import org.cip4.jdflib.core.ElementInfo;
import org.cip4.jdflib.core.ElementName;
import org.cip4.jdflib.core.JDFConstants;
import org.cip4.jdflib.core.VString;
import org.cip4.jdflib.resource.devicecapability.JDFTerm.EnumTerm;
import org.cip4.jdflib.util.ContainerUtil;

public class JDFBooleanState extends JDFAbstractState
{
	private static final long serialVersionUID = 1L;

	private static AtrInfoTable[] atrInfoTable = new AtrInfoTable[4];
	static
	{
		atrInfoTable[0] = new AtrInfoTable(AttributeName.ALLOWEDVALUELIST, 0x33333331, AttributeInfo.EnumAttributeType.enumerations, EnumBoolean.getEnum(0), null);
		atrInfoTable[1] = new AtrInfoTable(AttributeName.CURRENTVALUE, 0x33333331, AttributeInfo.EnumAttributeType.boolean_, null, null);
		atrInfoTable[2] = new AtrInfoTable(AttributeName.DEFAULTVALUE, 0x33333331, AttributeInfo.EnumAttributeType.boolean_, null, null);
		atrInfoTable[3] = new AtrInfoTable(AttributeName.PRESENTVALUELIST, 0x33333331, AttributeInfo.EnumAttributeType.enumerations, EnumBoolean.getEnum(0), null);
	}

	@Override
	protected AttributeInfo getTheAttributeInfo()
	{
		return super.getTheAttributeInfo().updateReplace(atrInfoTable);
	}

	private static ElemInfoTable[] elemInfoTable = new ElemInfoTable[1];
	static
	{
		elemInfoTable[0] = new ElemInfoTable(ElementName.VALUELOC, 0x33333311);
	}

	@Override
	protected ElementInfo getTheElementInfo()
	{
		return new ElementInfo(super.getTheElementInfo(), elemInfoTable);
	}

	/**
	 * Constructor for JDFBooleanState
	 * 
	 * @param myOwnerDocument
	 * @param qualifiedName
	 */
	public JDFBooleanState(final CoreDocumentImpl myOwnerDocument, final String qualifiedName)
	{
		super(myOwnerDocument, qualifiedName);
	}

	/**
	 * Constructor for JDFBooleanState
	 * 
	 * @param myOwnerDocument
	 * @param myNamespaceURI
	 * @param qualifiedName
	 */
	public JDFBooleanState(final CoreDocumentImpl myOwnerDocument, final String myNamespaceURI, final String qualifiedName)
	{
		super(myOwnerDocument, myNamespaceURI, qualifiedName);
	}

	/**
	 * Constructor for JDFBooleanState
	 * 
	 * @param myOwnerDocument
	 * @param myNamespaceURI
	 * @param qualifiedName
	 * @param myLocalName
	 */
	public JDFBooleanState(final CoreDocumentImpl myOwnerDocument, final String myNamespaceURI, final String qualifiedName, final String myLocalName)
	{
		super(myOwnerDocument, myNamespaceURI, qualifiedName, myLocalName);
	}

	// **************************************** Methods
	// *********************************************

	/**
	 * toString
	 * 
	 * @return String
	 */
	@Override
	public String toString()
	{
		return "JDFBooleanState[ --> " + super.toString() + " ]";
	}

	/**
	 * set attribute <code>CurrentValue</code>
	 * 
	 * @param value value to set the attribute to
	 */
	public void setCurrentValue(final boolean value)
	{
		setAttribute(AttributeName.CURRENTVALUE, value, null);
	}

	/**
	 * get attribute <code>CurrentValue</code>
	 * 
	 * @return the value of the attribute
	 */
	public boolean getCurrentValue()
	{
		return getBoolAttribute(AttributeName.CURRENTVALUE, JDFConstants.EMPTYSTRING, false);
	}

	/**
	 * set attribute <code>DefaultValue</code>
	 * 
	 * @param value the value to set the attribute to
	 */
	public void setDefaultValue(final boolean value)
	{
		setAttribute(AttributeName.DEFAULTVALUE, value, null);
	}

	/**
	 * get attribute <code>DefaultValue</code>
	 * 
	 * @return the value of the attribute
	 */
	public boolean getDefaultValue()
	{
		return getBoolAttribute(AttributeName.DEFAULTVALUE, JDFConstants.EMPTYSTRING, false);
	}

	/**
	 * get the list of allowed values
	 * 
	 * @return Vector, <code>null</code> if no allowed values found
	 */
	public Vector getAllowedValueList()
	{
		final Vector<ValuedEnum> enumerationsAttribute = getEnumerationsAttribute(AttributeName.ALLOWEDVALUELIST, null, EnumBoolean.True, false);
		return (Vector) ContainerUtil.getNonEmpty(enumerationsAttribute);
	}

	/**
	 * set attribute <code>AllowedValueList</code>
	 * 
	 * @param value value to set the attribute to
	 */
	public void setAllowedValueList(final Vector value)
	{
		setEnumerationsAttribute(AttributeName.ALLOWEDVALUELIST, value, null);
	}

	/**
	 * get attribute <code>PresentValueList</code>
	 * 
	 * @return the value of the attribute
	 */
	public Vector getPresentValueList()
	{
		if (hasAttribute(AttributeName.PRESENTVALUELIST))
		{
			return getEnumerationsAttribute(AttributeName.PRESENTVALUELIST, null, EnumBoolean.True, false);
		}
		return getAllowedValueList();
	}

	/**
	 * set attribute <code>PresentValueList</code>
	 * 
	 * @param value
	 */
	public void setPresentValueList(final Vector value)
	{
		setEnumerationsAttribute(AttributeName.PRESENTVALUELIST, value, null);
	}

	/*
	 * // Element Getter / Setter
	 */

	/**
	 * fitsValue - tests, if the defined value matches the Allowed test lists or Present test lists, specified for this State
	 * 
	 * @param value value to test
	 * @param testlists test lists, that the value has to match. In this State there is only one test list - ValueList.<br>
	 *        Choose one of two values: FitsValue_Allowed or FitsValue_Present. Defaults to Allowed.
	 * 
	 * @return boolean - true, if <code>value</code> matches testlists or if AllowedValueList is not specified
	 */
	@Override
	public final boolean fitsValue(final String valueStr, final EnumFitsValue testlists)
	{
		if (fitsListType(valueStr))
		{
			final VString value = new VString(valueStr, null);

			for (int i = 0; i < value.size(); i++)
			{
				if (!fitsValueList(value.elementAt(i), testlists))
				{
					return false;
				}
			}
			return true; // if we are here a whole 'valueStr' fits
		}
		return false;
	}

	/**
	 * fitsValueList - tests, if the defined 'value' matches the AllowedValueList or the PresentValueList, specified for this State
	 * 
	 * @param value token to test
	 * @param valuelist Switches between AllowedValueList and PresentValueList.
	 * @return boolean - true, if <code>value</code> matches valuelist, or if AllowedValueList is not specified
	 */
	private final boolean fitsValueList(final String value, final EnumFitsValue valuelist)
	{
		Vector<EnumBoolean> v;
		final EnumBoolean eb = EnumBoolean.getEnum(value);
		if (eb == null)
		{
			return false;
		}
		if (valuelist.equals(EnumFitsValue.Allowed))
		{
			v = getAllowedValueList();
		}
		else
		{
			v = getPresentValueList();
		}

		if (v == null)
		{
			return true;
		}

		return v.contains(eb);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.cip4.jdflib.resource.devicecapability.JDFAbstractState#addValue(java .lang.String, org.cip4.jdflib.datatypes.JDFBaseDataTypes.EnumFitsValue)
	 */
	@Override
	public void addValue(final String value, final EnumFitsValue testlists)
	{
		if (fitsValue(value, testlists))
		{
			return;
		}
		final EnumBoolean b = EnumBoolean.getEnum(value);
		if (b == null)
		{
			return;
		}

		if (testlists == null || EnumFitsValue.Allowed.equals(testlists))
		{
			Vector<EnumBoolean> list = getAllowedValueList();
			if (list == null)
			{
				list = new Vector<EnumBoolean>();
			}
			list.add(b);
			setAllowedValueList(list);
		}
		if (testlists == null || EnumFitsValue.Present.equals(testlists))
		{
			Vector<EnumBoolean> list = getPresentValueList();
			if (list == null || !hasAttribute(AttributeName.PRESENTVALUELIST))
			{
				list = new Vector<EnumBoolean>();
			}
			list.add(b);
			setPresentValueList(list);
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.cip4.jdflib.ifaces.ICapabilityElement#getEvaluationType()
	 */
	@Override
	public EnumTerm getEvaluationType()
	{
		return EnumTerm.BooleanEvaluation;
	}

}