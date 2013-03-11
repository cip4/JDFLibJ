/*
 *
 * The CIP4 Software License, Version 1.0
 *
 *
 * Copyright (c) 2001-2005 The International Cooperation for the Integration of 
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
 * JDFDateTimeEvaluation.java
 *
 */

package org.cip4.jdflib.resource.devicecapability;

import java.util.zip.DataFormatException;

import org.apache.xerces.dom.CoreDocumentImpl;
import org.cip4.jdflib.auto.JDFAutoBasicPreflightTest.EnumListType;
import org.cip4.jdflib.core.AtrInfoTable;
import org.cip4.jdflib.core.AttributeInfo;
import org.cip4.jdflib.core.AttributeName;
import org.cip4.jdflib.core.JDFException;
import org.cip4.jdflib.datatypes.JDFDateTimeRange;
import org.cip4.jdflib.datatypes.JDFDateTimeRangeList;
import org.cip4.jdflib.datatypes.JDFDurationRangeList;
import org.cip4.jdflib.util.JDFDate;
import org.cip4.jdflib.util.JDFDuration;

public class JDFDateTimeEvaluation extends JDFEvaluation
{
	private static final long serialVersionUID = 1L;

	private static AtrInfoTable[] atrInfoTable = new AtrInfoTable[2];
	static
	{
		atrInfoTable[0] = new AtrInfoTable(AttributeName.VALUEDURATIONLIST, 0x33333333, AttributeInfo.EnumAttributeType.DurationRangeList, null, null);
		atrInfoTable[1] = new AtrInfoTable(AttributeName.VALUELIST, 0x33333333, AttributeInfo.EnumAttributeType.DateTimeRangeList, null, null);
	}

	@Override
	protected AttributeInfo getTheAttributeInfo()
	{
		return super.getTheAttributeInfo().updateReplace(atrInfoTable);
	}

	/**
	 * constructor for JDFDateTimeEvaluation
	 * 
	 * @param myOwnerDocument
	 * @param qualifiedName
	 */
	public JDFDateTimeEvaluation(CoreDocumentImpl myOwnerDocument, String qualifiedName)
	{
		super(myOwnerDocument, qualifiedName);
	}

	/**
	 * constructor for JDFDateTimeEvaluation
	 * 
	 * @param myOwnerDocument
	 * @param myNamespaceURI
	 * @param qualifiedName
	 */
	public JDFDateTimeEvaluation(CoreDocumentImpl myOwnerDocument, String myNamespaceURI, String qualifiedName)
	{
		super(myOwnerDocument, myNamespaceURI, qualifiedName);
	}

	/**
	 * constructor for JDFDateTimeEvaluation
	 * 
	 * @param myOwnerDocument
	 * @param myNamespaceURI
	 * @param qualifiedName
	 * @param myLocalName
	 */
	public JDFDateTimeEvaluation(CoreDocumentImpl myOwnerDocument, String myNamespaceURI, String qualifiedName, String myLocalName)
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
		return "JDFDateTimeEvaluation[  --> " + super.toString() + " ]";
	}

	/*
	 * // Attribute getter/ Setter
	 */

	/**
	 * set attribute <code>ValueList</code>
	 * 
	 * @param value
	 *            the value to set the attribute to
	 */
	public void setValueList(JDFDateTimeRangeList value)
	{
		setAttribute(AttributeName.VALUELIST, value.toString());
	}

	/**
	 * get attribute <code>ValueList</code>
	 * 
	 * @return the value of the attribute
	 */
	public JDFDateTimeRangeList getValueList()
	{
		try
		{
			final String attribute = getAttribute(AttributeName.VALUELIST, null, null);
			return attribute == null ? null : new JDFDateTimeRangeList(attribute);
		}
		catch (DataFormatException dfe)
		{
			return null;
		}

	}

	/**
	 * set attribute <code>ValueDurationList</code>
	 * 
	 * @param value
	 *            the value to set the attribute to
	 */
	public void setValueDurationList(JDFDurationRangeList value)
	{
		setAttribute(AttributeName.VALUEDURATIONLIST, value.toString());
	}

	/**
	 * get attribute <code>ValueDurationList</code>
	 * 
	 * @return the value of the attribute
	 */
	public JDFDurationRangeList getValueDurationList()
	{
		try
		{
			return new JDFDurationRangeList(getAttribute(AttributeName.VALUEDURATIONLIST));
		}
		catch (DataFormatException dfe)
		{
			return null;
		}

	}

	/*
	 * // FitsValue Methods
	 */

	/**
	 * fitsValue - tests, if the defined value matches ValueList, specified for
	 * this Evaluation
	 * 
	 * @param value
	 *            value to test
	 * @return boolean - true, if the value matches ValueList or if ValueList is
	 *         not specified
	 */
	@Override
	public final boolean fitsValue(String value)
	{
		if (!fitsListType(value))
			return false;

		JDFDateTimeRangeList rangelist = null;
		try
		{
			rangelist = new JDFDateTimeRangeList(value);
		}
		catch (DataFormatException dfe)
		{
			return false;
		}
		return fitsValueList(rangelist) && fitsValueDurationList(rangelist);
	}

	/**
	 * fitsListType - tests, if the defined <code>value</code> matches the value
	 * of the ListType attribute, specified for this Evaluation
	 * 
	 * @param value
	 *            value to test
	 * @return boolean - true, if <code>value</code> matches specified ListType
	 */
	private final boolean fitsListType(String value)
	{
		EnumListType listType = getListType();

		JDFDateTimeRangeList rangelist;
		try
		{
			rangelist = new JDFDateTimeRangeList(value);
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
			if (value.indexOf("P") != 0)
				return false;

			try
			{
				new JDFDate(value);
			}
			catch (JDFException e)
			{
				return false;
			}
			catch (DataFormatException e)
			{
				return false;
			}

			return true;
		}
		else if (listType.equals(EnumListType.RangeList) || listType.equals(EnumListType.Span))
		{
			return true;
		}
		else if (listType.equals(EnumListType.List))
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
			throw new JDFException("JDFDateTimeEvaluation.fitsListType illegal ListType attribute");
		}
	}

	/**
	 * fitsValueList - tests, if the defined <code>rangelist</code> matches the
	 * ValueList specified for this Evaluation
	 * 
	 * @param rangelist
	 *            range list to test
	 * 
	 * @return boolean - true, if <code>rangelist</code> matches the valuelist
	 *         or if ValueList is not specified
	 */
	private final boolean fitsValueList(JDFDateTimeRangeList rangelist)
	{
		if (!hasAttribute(AttributeName.VALUELIST))
			return true;
		return getValueList().isPartOfRange(rangelist);
	}

	/**
	 * fitsValueDurationList - tests, if the duration of the defined 'rangelist'
	 * value matches ValueDurationList, specified for this State
	 * 
	 * @param rangelist
	 *            range list to test
	 * @return boolean - true, if the duration of the defined
	 *         <code>rangelist</code> is in the ValueList or if
	 *         ValueDurationList is not specified
	 */
	private final boolean fitsValueDurationList(JDFDateTimeRangeList rangelist)
	{
		if (hasAttribute(AttributeName.VALUEDURATIONLIST))
		{
			JDFDurationRangeList list = getValueDurationList();

			int siz = rangelist.size();
			for (int i = 0; i < siz; i++)
			{
				JDFDateTimeRange range = (JDFDateTimeRange) rangelist.at(i);

				int duration = (int) ((range.getRight().getTimeInMillis() - range.getLeft().getTimeInMillis()) / 1000);
				JDFDuration d = new JDFDuration();
				d.setDuration(duration);
				if (!list.inRange(d))
					return false;
			}
			return true;
		}
		return true;
	}

}