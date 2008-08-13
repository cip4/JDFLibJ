/*
 *
 * The CIP4 Software License, Version 1.0
 *
 *
 * Copyright (c) 2001-2007 The International Cooperation for the Integration of 
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
 * JDFNumberState.java
 *
 */

package org.cip4.jdflib.resource.devicecapability;

import java.util.zip.DataFormatException;

import org.apache.xerces.dom.CoreDocumentImpl;
import org.cip4.jdflib.auto.JDFAutoBasicPreflightTest.EnumListType;
import org.cip4.jdflib.core.AtrInfoTable;
import org.cip4.jdflib.core.AttributeInfo;
import org.cip4.jdflib.core.AttributeName;
import org.cip4.jdflib.core.ElemInfoTable;
import org.cip4.jdflib.core.ElementInfo;
import org.cip4.jdflib.core.ElementName;
import org.cip4.jdflib.core.JDFException;
import org.cip4.jdflib.core.VString;
import org.cip4.jdflib.datatypes.JDFNumberList;
import org.cip4.jdflib.datatypes.JDFNumberRange;
import org.cip4.jdflib.datatypes.JDFNumberRangeList;
import org.cip4.jdflib.datatypes.JDFXYPair;
import org.cip4.jdflib.resource.devicecapability.JDFTerm.EnumTerm;
import org.cip4.jdflib.util.StringUtil;

public class JDFNumberState extends JDFAbstractState
{
	private static final long serialVersionUID = 1L;

	private static AtrInfoTable[] atrInfoTable = new AtrInfoTable[11];
	static
	{
		atrInfoTable[0] = new AtrInfoTable(AttributeName.ALLOWEDVALUELIST,
				0x33333331, AttributeInfo.EnumAttributeType.NumberRangeList,
				null, null);
		atrInfoTable[1] = new AtrInfoTable(AttributeName.ALLOWEDVALUEMAX,
				0x44444431, AttributeInfo.EnumAttributeType.double_, null, null);
		atrInfoTable[2] = new AtrInfoTable(AttributeName.ALLOWEDVALUEMIN,
				0x44444431, AttributeInfo.EnumAttributeType.double_, null, null);
		atrInfoTable[3] = new AtrInfoTable(AttributeName.ALLOWEDVALUEMOD,
				0x33333311, AttributeInfo.EnumAttributeType.XYPair, null, null);
		atrInfoTable[4] = new AtrInfoTable(AttributeName.CURRENTVALUE,
				0x33333331, AttributeInfo.EnumAttributeType.NumberList, null,
				null);
		atrInfoTable[5] = new AtrInfoTable(AttributeName.DEFAULTVALUE,
				0x33333331, AttributeInfo.EnumAttributeType.NumberList, null,
				null);
		atrInfoTable[6] = new AtrInfoTable(AttributeName.PRESENTVALUELIST,
				0x33333331, AttributeInfo.EnumAttributeType.NumberRangeList,
				null, null);
		atrInfoTable[7] = new AtrInfoTable(AttributeName.PRESENTVALUEMAX,
				0x44444431, AttributeInfo.EnumAttributeType.double_, null, null);
		atrInfoTable[8] = new AtrInfoTable(AttributeName.PRESENTVALUEMIN,
				0x44444431, AttributeInfo.EnumAttributeType.double_, null, null);
		atrInfoTable[9] = new AtrInfoTable(AttributeName.PRESENTVALUEMOD,
				0x33333311, AttributeInfo.EnumAttributeType.XYPair, null, null);
		atrInfoTable[10] = new AtrInfoTable(AttributeName.UNITTYPE, 0x33333311,
				AttributeInfo.EnumAttributeType.NMTOKEN, null, null);
	}

	protected AttributeInfo getTheAttributeInfo()
	{
		return super.getTheAttributeInfo().updateReplace(atrInfoTable);
	}

	private static ElemInfoTable[] elemInfoTable = new ElemInfoTable[1];
	static
	{
		elemInfoTable[0] = new ElemInfoTable(ElementName.VALUELOC, 0x33333311);
	}

	protected ElementInfo getTheElementInfo()
	{
		return new ElementInfo(super.getTheElementInfo(), elemInfoTable);
	}

	/**
	 * constructor for JDFNumberState
	 * 
	 * @param myOwnerDocument
	 * @param qualifiedName
	 */
	public JDFNumberState(CoreDocumentImpl myOwnerDocument, String qualifiedName)
	{
		super(myOwnerDocument, qualifiedName);
	}

	/**
	 * constructor for JDFNumberState
	 * 
	 * @param myOwnerDocument
	 * @param myNamespaceURI
	 * @param qualifiedName
	 */
	public JDFNumberState(CoreDocumentImpl myOwnerDocument,
			String myNamespaceURI, String qualifiedName)
	{
		super(myOwnerDocument, myNamespaceURI, qualifiedName);
	}

	/**
	 * constructor for JDFNumberState
	 * 
	 * @param myOwnerDocument
	 * @param myNamespaceURI
	 * @param qualifiedName
	 * @param myLocalName
	 */
	public JDFNumberState(CoreDocumentImpl myOwnerDocument,
			String myNamespaceURI, String qualifiedName, String myLocalName)
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
	public String toString()
	{
		return "JDFNumberState[ --> " + super.toString() + " ]";
	}

	/*
	 * // Attribute getter/ setter
	 */

	public void setDefaultValue(double value)
	{
		setAttribute(AttributeName.DEFAULTVALUE, value, null);
	}

	public void setDefaultValue(JDFNumberList value)
	{
		setAttribute(AttributeName.DEFAULTVALUE, value, null);
	}

	public void setCurrentValue(JDFNumberList value)
	{
		setAttribute(AttributeName.CURRENTVALUE, value, null);
	}

	public JDFNumberList getDefaultValue()
	{
		return getNumberList(AttributeName.DEFAULTVALUE);
	}

	public void setCurrentValue(double value)
	{
		setAttribute(AttributeName.CURRENTVALUE, value, null);
	}

	public JDFNumberList getCurrentValue()
	{
		return getNumberList(AttributeName.CURRENTVALUE);
	}

	public void setAllowedValueList(JDFNumberRangeList value)
	{
		setAttribute(AttributeName.ALLOWEDVALUELIST, value.toString(), null);
	}

	public JDFNumberRangeList getAllowedValueList()
	{
		return getNumberRangeList(AttributeName.ALLOWEDVALUELIST);
	}

	public void setPresentValueList(JDFNumberRangeList value)
	{
		setAttribute(AttributeName.PRESENTVALUELIST, value.toString(), null);
	}

	public JDFNumberRangeList getPresentValueList()
	{
		JDFNumberRangeList nl = getNumberRangeList(AttributeName.PRESENTVALUELIST);
		return (nl == null) ? getAllowedValueList() : nl;
	}

	// ///////////////////////////////////////////////////////////////
	public void setAllowedValueMax(double value)
	{
		setAttribute(AttributeName.ALLOWEDVALUEMAX, value, null);
	}

	// ///////////////////////////////////////////////////////////////
	public double getAllowedValueMax()
	{
		return getRealAttribute(AttributeName.ALLOWEDVALUEMAX, null, 0.0);
	}

	// ///////////////////////////////////////////////////////////////
	public void setPresentValueMax(double value)
	{
		setAttribute(AttributeName.PRESENTVALUEMAX, value, null);
	}

	public double getPresentValueMax()
	{
		if (hasAttribute(AttributeName.PRESENTVALUEMAX))
		{
			return getRealAttribute(AttributeName.PRESENTVALUEMAX, null, 0.0);
		}
		return getAllowedValueMax();
	}

	public void setAllowedValueMin(double value)
	{
		setAttribute(AttributeName.ALLOWEDVALUEMIN, value, null);
	}

	public double getAllowedValueMin()
	{
		return getRealAttribute(AttributeName.ALLOWEDVALUEMIN, null, 0.0);
	}

	public void setPresentValueMin(double value)
	{
		setAttribute(AttributeName.PRESENTVALUEMIN, value, null);
	}

	public double getPresentValueMin()
	{
		if (hasAttribute(AttributeName.PRESENTVALUEMIN))
		{
			return getRealAttribute(AttributeName.PRESENTVALUEMIN, null, 0.0);
		}
		return getAllowedValueMin();
	}

	public void setAllowedValueMod(JDFXYPair value)
	{
		setAttribute(AttributeName.ALLOWEDVALUEMOD, value.toString(), null);
	}

	public JDFXYPair getAllowedValueMod()
	{
		try
		{
			return new JDFXYPair(getAttribute(AttributeName.ALLOWEDVALUEMOD));
		} catch (DataFormatException e)
		{
			throw new JDFException(
					"JDFNumberState.setAllowedValueMod: Attribute allowvaluemod is not applicable to create JDFXYPair");
		}

	}

	public void setPresentValueMod(JDFXYPair value)
	{
		setAttribute(AttributeName.PRESENTVALUEMOD, value.toString());
	}

	public JDFXYPair getPresentValueMod()
	{
		try
		{
			if (hasAttribute(AttributeName.PRESENTVALUEMOD))
			{
				return new JDFXYPair(
						getAttribute(AttributeName.PRESENTVALUEMOD));
			}
			return getAllowedValueMod();
		} catch (DataFormatException e)
		{
			throw new JDFException(
					"JDFNumberState.setAllowedValueMod: The XYPair value is invalid!");
		}

	}

	public String getUnitType()
	{
		return getAttribute(AttributeName.UNITTYPE);
	}

	public void setUnitType(String value)
	{
		setAttribute(AttributeName.UNITTYPE, value);
	}

	/*
	 * // Element getter / setter
	 */
	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.cip4.jdflib.resource.devicecapability.JDFAbstractState#addValue(java
	 * .lang.String, org.cip4.jdflib.datatypes.JDFBaseDataTypes.EnumFitsValue)
	 */
	public void addValue(String value, EnumFitsValue testlists)
	{
		if (fitsValue(value, testlists))
			return;

		if (!StringUtil.isNumber(value))
			return;
		double d = StringUtil.parseDouble(value, 0);

		if (testlists == null || EnumFitsValue.Allowed.equals(testlists))
		{
			JDFNumberRangeList list = getAllowedValueList();
			if (list == null)
				list = new JDFNumberRangeList();
			list.append(d);
			setAllowedValueList(list);
		}
		if (testlists == null || EnumFitsValue.Present.equals(testlists))
		{
			JDFNumberRangeList list = getPresentValueList();
			if (list == null || !hasAttribute(AttributeName.PRESENTVALUELIST))
				list = new JDFNumberRangeList();
			list.append(d);
			setPresentValueList(list);
		}
	}

	/**
	 * fitsValue - checks whether <code>value</code> matches the Allowed/Present
	 * test lists specified for this State
	 * 
	 * @param value
	 *            value to test
	 * @param testlists
	 *            the test lists the value has to match. In this State the test
	 *            lists are ValueList AND ValueMod.<br>
	 *            Choose one of two values: FitsValue_Allowed or
	 *            FitsValue_Present. (Defaults to Allowed)
	 * 
	 * @return boolean - true, if <code>value</code> matches testlists or if
	 *         AllowedValueList and AllowedValueMod are not specified
	 */
	public boolean fitsValue(String value, EnumFitsValue testlists)
	{
		if (fitsListType(value))
		{
			JDFNumberRangeList rangelist = null;
			try
			{
				rangelist = new JDFNumberRangeList(value);
			} catch (DataFormatException dfe)
			{
				return false;
			}

			return (fitsValueList(rangelist, testlists) && fitsValueMod(
					rangelist, testlists));
		}
		return false; // the value doesn't fit ListType attribute of this State
	}

	/**
	 * fitsValueList - checks whether <code>rangelist</code> matches the
	 * AllowedValueList/PresentValueList specified for this State
	 * 
	 * @param rangelist
	 *            range list to test
	 * @param valuelist
	 *            switches between AllowedValueList and PresentValueList.
	 * 
	 * @return boolean - true, if <code>rangelist</code> matches the valuelist
	 *         or if AllowedValueList is not specified
	 */
	private final boolean fitsValueList(JDFNumberRangeList rangelist,
			EnumFitsValue valuelist)
	{
		JDFNumberRangeList list;
		if (valuelist.equals(EnumFitsValue.Allowed))
		{
			list = getAllowedValueList();
		} else
		{
			list = getPresentValueList();
		}
		if (list == null)
			return true;

		EnumListType listType = getListType();
		if (listType.equals(EnumListType.CompleteList))
		{
			return fitsCompleteList(rangelist, list);
		} else if (listType.equals(EnumListType.CompleteOrderedList))
		{
			return fitsCompleteOrderedList(rangelist, list);
		} else if (listType.equals(EnumListType.ContainedList))
		{
			return fitsContainedList(rangelist, list);
		}

		int siz = rangelist.size();
		for (int i = 0; i < siz; i++)
		{
			JDFNumberRange range = (JDFNumberRange) rangelist.at(i);

			if (!list.isPartOfRange(range))
				return false;
		}
		return true;
	}

	/**
	 * fitsValueMod - checks whether <code>range</code> matches the
	 * <code>ValueMod</code> specified for this State.
	 * 
	 * @param rangelist
	 *            range list to test
	 * @param valuemod
	 *            switches between AllowedValueMod and PresentValueMod.
	 * 
	 * @return boolean - true, if <code>rangelist</code> matches the valuemod or
	 *         if AllowedValueMod is not specified
	 */
	private final boolean fitsValueMod(JDFNumberRangeList rangelist,
			EnumFitsValue valuemod)
	{
		if (valuemod.equals(EnumFitsValue.Allowed))
		{
			if (!hasAttribute(AttributeName.ALLOWEDVALUEMOD))
				return true;
		} else
		{
			if (!hasAttribute(AttributeName.ALLOWEDVALUEMOD)
					&& !hasAttribute(AttributeName.PRESENTVALUEMOD))
				return true;
		}

		JDFXYPair mod;
		if (valuemod.equals(EnumFitsValue.Allowed))
		{
			mod = getAllowedValueMod();
		} else
		{
			mod = getPresentValueMod();
		}

		int siz = rangelist.size();
		for (int i = 0; i < siz; i++)
		{
			JDFNumberRange range = (JDFNumberRange) rangelist.at(i);

			double left = range.getLeft();
			double right = range.getRight();
			if (left != right) // if we have a range return false, check only
								// single value
				return false;

			double elem = left; // single value
			double divi = mod.getX(); // X - the Modulo
			double shift = mod.getY(); // Y - offset of the allowed/present
										// value

			if (divi == 0)
				return false;

			// if ValueMod is not "0 x"
			double n = ((elem - divi * (int) (elem / divi)) - shift); // n =
																		// elem
																		// %
																		// divi
																		// -
																		// shift
			if (java.lang.Math.abs(n) < EPSILON * java.lang.Math.abs(divi))
			{
				return true;
			}

			double m = (n - divi * (int) (n / divi)); // m = ( elem % divi -
														// shift ) % divi
			if (java.lang.Math.abs(m) < EPSILON * java.lang.Math.abs(divi))
			{
				return true;
			}
			return false;

		}
		return true;
	}

	/**
	 * fitsCompleteList - tests whether <code>value</code> matches the given
	 * testlist (ListType=fitsCompleteList)
	 * 
	 * @param value
	 *            value to test
	 * @param list
	 *            testlist, either AllowedValueList or PresentValueList.
	 * 
	 * @return boolean - true, if <code>value</code> matches the testlist
	 */
	private final boolean fitsCompleteList(JDFNumberRangeList value,
			JDFNumberRangeList list)
	{
		int v_size = value.size();
		int l_size = list.size();

		if (v_size != l_size)
			return false;

		if (!value.isUnique())
			return false;

		JDFNumberRangeList valueList = new JDFNumberRangeList(value);

		boolean bFound;
		for (int i = l_size - 1; i >= 0; i--)
		{
			bFound = false;
			for (int j = valueList.size() - 1; j >= 0; j--)
			{
				if (list.at(i).equals(valueList.at(j)))
				{
					valueList.erase(j);
					bFound = true;
					break;
				}
			}
			if (!bFound)
			{
				return false;
			}
		}
		return true;
	}

	/**
	 * fitsCompleteOrderedList - tests whether <code>value</code> matches the
	 * given testlist (ListType=CompleteOrderedList)
	 * 
	 * @param value
	 *            value to test
	 * @param list
	 *            testlist, either AllowedValueList or PresentValueList.
	 * 
	 * @return boolean - true, if <code>value</code> matches the testlist
	 */
	private final boolean fitsCompleteOrderedList(JDFNumberRangeList value,
			JDFNumberRangeList list)
	{
		int v_size = value.size();
		int l_size = list.size();

		if (v_size != l_size)
			return false;

		if (!value.isUnique())
			return false;

		for (int i = 0; i < l_size; i++)
		{
			if (!list.at(i).equals(value.at(i)))
			{
				return false;
			}
		}
		return true;
	}

	/**
	 * fitsContainedList - tests whether <code>value</code> matches the given
	 * testlist (ListType=ContainedList)
	 * 
	 * @param value
	 *            value to test
	 * @param list
	 *            testlist, either AllowedValueList or PresentValueList.
	 * 
	 * @return boolean - true, if <code>value</code> matches testlist
	 */
	private final boolean fitsContainedList(JDFNumberRangeList value,
			JDFNumberRangeList list)
	{
		int v_size = value.size();
		int l_size = list.size();

		for (int i = 0; i < v_size; i++)
		{
			for (int j = 0; j < l_size; j++)
			{
				if (value.at(i).equals(list.at(j)))
				{
					return true;
				}
			}
		}
		return false;
	}

	/**
	 * @param listName
	 * @return
	 */
	private JDFNumberList getNumberList(final String listName)
	{
		try
		{
			final String attribute = getAttribute(listName, null, null);
			if (attribute == null)
				return null;
			return new JDFNumberList(attribute);
		} catch (DataFormatException e)
		{
			throw new JDFException(
					"JDFNumberState.getNumberList, Unable to create JDFIntegerRangeList from Attribute value: "
							+ listName);
		}
	}

	/**
	 * @param listName
	 * @return
	 */
	private JDFNumberRangeList getNumberRangeList(final String listName)
	{
		try
		{
			final String attribute = getAttribute(listName, null, null);
			if (attribute == null)
				return null;
			return new JDFNumberRangeList(attribute);
		} catch (DataFormatException e)
		{
			throw new JDFException(
					"JDFIntegerState.getIntegerRangeList, Unable to create JDFIntegerRangeList from Attribute value: "
							+ listName);
		}
	}

	public VString getInvalidAttributes(EnumValidationLevel level,
			boolean bIgnorePrivate, int nMax)
	{
		return getInvalidAttributesImpl(level, bIgnorePrivate, nMax);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.cip4.jdflib.ifaces.ICapabilityElement#getEvaluationType()
	 */
	public EnumTerm getEvaluationType()
	{
		return EnumTerm.NumberEvaluation;
	}

}