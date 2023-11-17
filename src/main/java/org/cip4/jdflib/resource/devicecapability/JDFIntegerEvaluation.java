/*
 *
 * The CIP4 Software License, Version 1.0
 *
 *
 * Copyright (c) 2001-2023 The International Cooperation for the Integration of 
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
 * JDFIntegerEvaluation.java
 *
 */

package org.cip4.jdflib.resource.devicecapability;

import java.util.zip.DataFormatException;

import org.apache.xerces.dom.CoreDocumentImpl;
import org.cip4.jdflib.auto.JDFAutoBasicPreflightTest.EnumListType;
import org.cip4.jdflib.core.AtrInfoTable;
import org.cip4.jdflib.core.AttributeInfo;
import org.cip4.jdflib.core.AttributeName;
import org.cip4.jdflib.core.JDFConstants;
import org.cip4.jdflib.core.JDFException;
import org.cip4.jdflib.datatypes.JDFIntegerList;
import org.cip4.jdflib.datatypes.JDFIntegerRangeList;
import org.cip4.jdflib.datatypes.JDFXYPair;
import org.cip4.jdflib.util.StringUtil;

public class JDFIntegerEvaluation extends JDFEvaluation
{
	private static final long serialVersionUID = 1L;

	private static AtrInfoTable[] atrInfoTable = new AtrInfoTable[2];
	static
	{
		atrInfoTable[0] = new AtrInfoTable(AttributeName.VALUELIST, 0x33333333, AttributeInfo.EnumAttributeType.IntegerRangeList, null, null);
		atrInfoTable[1] = new AtrInfoTable(AttributeName.VALUEMOD, 0x33333333, AttributeInfo.EnumAttributeType.XYPair, null, null);
	}

	@Override
	protected AttributeInfo getTheAttributeInfo()
	{
		return super.getTheAttributeInfo().updateReplace(atrInfoTable);
	}

	/**
	 * constructor for JDFIntegerEvaluation
	 * 
	 * @param myOwnerDocument
	 * @param qualifiedName
	 */
	public JDFIntegerEvaluation(CoreDocumentImpl myOwnerDocument, String qualifiedName)
	{
		super(myOwnerDocument, qualifiedName);
	}

	/**
	 * constructor for JDFIntegerEvaluation
	 * 
	 * @param myOwnerDocument
	 * @param myNamespaceURI
	 * @param qualifiedName
	 */
	public JDFIntegerEvaluation(CoreDocumentImpl myOwnerDocument, String myNamespaceURI, String qualifiedName)
	{
		super(myOwnerDocument, myNamespaceURI, qualifiedName);
	}

	/**
	 * constructor for JDFIntegerEvaluation
	 * 
	 * @param myOwnerDocument
	 * @param myNamespaceURI
	 * @param qualifiedName
	 * @param myLocalName
	 */
	public JDFIntegerEvaluation(CoreDocumentImpl myOwnerDocument, String myNamespaceURI, String qualifiedName, String myLocalName)
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
		return "JDFIntegerEvaluation[ --> " + super.toString() + " ]";
	}

	/*
	 * // Attribute getter/ setter
	 */

	/**
	 * set attribute <code>ValueList</code>
	 * 
	 * @param value the value to set the attribute to
	 */
	public void setValueList(JDFIntegerRangeList value)
	{
		setAttribute(AttributeName.VALUELIST, value.toString(), null);
	}

	/**
	 * append the value of int to @ValueList
	 * 
	 * @param value the integer value to append
	 */
	public void appendValueList(int value)
	{
		JDFIntegerRangeList irl = getValueList();
		irl.append(value);
		setValueList(irl);
	}

	/**
	 * get attribute <code>ValueList</code>
	 * 
	 * @return JDFIntegerRangeList - the value of the attribute
	 */
	public JDFIntegerRangeList getValueList()
	{
		return JDFIntegerRangeList.createIntegerRangeList(getAttribute(AttributeName.VALUELIST, null, JDFConstants.EMPTYSTRING));
	}

	/**
	 * set attribute <code>ValueMod</code>
	 * 
	 * @param value the value to set the attribute to
	 */
	public void setValueMod(JDFXYPair value)
	{
		setAttribute(AttributeName.VALUEMOD, value.toString());
	}

	/**
	 * get attribute ValueMod
	 * 
	 * @return JDFXYPair - the value of the attribute
	 */
	public JDFXYPair getValueMod()
	{
		return JDFXYPair.createXYPair(getAttribute(AttributeName.VALUEMOD));
	}

	/*
	 * // FitsValue Methods
	 */

	/**
	 * fitsValue - tests if the defined 'value' matches testlists, specified for this Evaluation
	 * 
	 * @param value value to test
	 * @return boolean - true, if 'value' matches testlists or if testlists are not specified
	 */
	@Override
	public boolean fitsValue(String value)
	{
		if (fitsListType(value))
		{
			JDFIntegerRangeList rangelist = null;
			try
			{
				rangelist = new JDFIntegerRangeList(value);
			}
			catch (DataFormatException dfe)
			{
				return false;
			}

			return (fitsValueList(rangelist) && fitsValueMod(rangelist));
		}
		return false; // the value doesn't fit ListType attribute of this
						// Evaluation
	}

	/**
	 * fitsListType - tests if the defined 'value' matches ListType attribute, specified for this Evaluation
	 * 
	 * @param value value to test
	 * @return boolean - true, if 'value' matches specified value of ListType
	 */
	private final boolean fitsListType(String value)
	{
		EnumListType listType = getListType();

		JDFIntegerRangeList rangelist;
		try
		{
			rangelist = new JDFIntegerRangeList(value);
		}
		catch (DataFormatException e)
		{
			return false;
		}
		catch (JDFException e)
		{
			return false;
		}

		if (listType.equals(EnumListType.SingleValue) || listType.equals(EnumListType.getEnum(0)))
		{// default ListType = SingleValue
			return (StringUtil.isInteger(value));
		}
		else if (listType.equals(EnumListType.RangeList) || listType.equals(EnumListType.Span))
		{
			return true;
		}
		else if (listType.equals(EnumListType.List) || listType.equals(EnumListType.CompleteList) || // complete or
																										// not -
																										// tested in
																										// fitsValueList
				listType.equals(EnumListType.CompleteOrderedList) || // tested
																		// in
																		// fitsValueList
				listType.equals(EnumListType.ContainedList)) // tested in
																// fitsValueList
		{
			return rangelist.isList();
		}
		else if (listType.equals(EnumListType.OrderedList))
		{
			return (rangelist.isList() && rangelist.isOrdered());
		}
		else if (listType.equals(EnumListType.UniqueList))
		{
			return (rangelist.isList() && rangelist.isUnique());
		}
		else if (listType.equals(EnumListType.UniqueOrderedList))
		{
			return (rangelist.isList() && rangelist.isUniqueOrdered());
		}
		else if (listType.equals(EnumListType.OrderedRangeList))
		{
			return rangelist.isOrdered();
		}
		else if (listType.equals(EnumListType.UniqueRangeList))
		{
			return rangelist.isUnique();
		}
		else if (listType.equals(EnumListType.UniqueOrderedRangeList))
		{
			return (rangelist.isUniqueOrdered());
		}
		else
		{
			throw new JDFException("JDFIntegerEvaluation.fitsListType illegal ListType attribute");
		}
	}

	/**
	 * fitsValueList - tests if the defined 'rangelist' matches the ValueList, specified for this Evaluation
	 * 
	 * @param rangelist range list to test
	 * @return boolean - true, if 'rangelist' matches the ValueList or if ValueList is not specified
	 */
	private final boolean fitsValueList(JDFIntegerRangeList rangelist)
	{
		if (!hasAttribute(AttributeName.VALUELIST))
			return true;

		JDFIntegerRangeList valuelist = getValueList();

		EnumListType listType = getListType();

		if (listType.equals(EnumListType.CompleteList))
		{
			return fitsCompleteList(rangelist, valuelist);
		}
		else if (listType.equals(EnumListType.CompleteOrderedList))
		{
			return fitsCompleteOrderedList(rangelist, valuelist);
		}
		else if (listType.equals(EnumListType.ContainedList))
		{
			return fitsContainedList(rangelist, valuelist);
		}

		int siz = rangelist.size();
		for (int i = 0; i < siz; i++)
		{
			if (!valuelist.isPartOfRange(rangelist.at(i)))
				return false;
		}
		return true;
	}

	/**
	 * fitsValueMod - tests if the defined 'rangelist' matches the ValueMod, specified for this Evaluation
	 * 
	 * @param rangelist range list to test
	 * @return boolean - true, if 'rangelist' matches the ValueMod or if ValueMod is not specified
	 */
	private final boolean fitsValueMod(JDFIntegerRangeList rangelist)
	{
		if (!hasAttribute(AttributeName.VALUEMOD))
			return true;

		JDFXYPair mod = getValueMod();

		int divi = (int) (mod.getX() + 0.5); // X - the Modulo
		int shift = (int) (mod.getY() + 0.5); // Y - offset of the
												// allowed/present value

		if (divi == 0) // ValueMod can't be "0 x"
			return false;

		JDFIntegerList v = rangelist.getIntegerList();
		int[] vi = v.getIntArray();
		int siz = vi.length;
		for (int i = 0; i < siz; i++)
		{
			if ((((vi[i] % divi) - shift) % divi) != 0)
				return false;
		}
		return true;
	}

	/**
	 * fitsContainedList - tests for the case, when ListType=CompleteList, does the defined 'value' match ValueList, specified for this Evaluation
	 * 
	 * @param value value to test
	 * @param list specified ValueList
	 * @return boolean - true, if 'value' matches testlist
	 */
	private final boolean fitsCompleteList(JDFIntegerRangeList value, JDFIntegerRangeList list)
	{
		int v_size = value.size();
		int l_size = list.size();

		if (v_size != l_size)
			return false;

		if (!value.isUnique())
			return false;

		JDFIntegerRangeList valueList = new JDFIntegerRangeList(value);

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
	 * fitsCompleteOrderedList - tests whether <code>value</code> matches the given ValueList (ListType=CompleteOrderedList)
	 * 
	 * @param value value to test
	 * @param list specified ValueList
	 * @return boolean - true, if 'value' matches testlist
	 */
	private final boolean fitsCompleteOrderedList(JDFIntegerRangeList value, JDFIntegerRangeList list)
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
	 * fitsContainedList - tests whether <code>value</code> matches the given ValueList (ListType=ContainedList)
	 * 
	 * @param value value to test
	 * @param list specified ValueList
	 * @return boolean - true, if <code>value</code> matches testlist
	 */
	private final boolean fitsContainedList(JDFIntegerRangeList value, JDFIntegerRangeList list)
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

}