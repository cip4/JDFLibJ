/*
 *
 * The CIP4 Software License, Version 1.0
 *
 *
 * Copyright (c) 2001-2006 The International Cooperation for the Integration of 
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
 * JDFRectangleEvaluation.java
 *
 */

package org.cip4.jdflib.resource.devicecapability;

import java.util.zip.DataFormatException;

import org.apache.xerces.dom.CoreDocumentImpl;
import org.cip4.jdflib.auto.JDFAutoBasicPreflightTest.EnumListType;
import org.cip4.jdflib.core.AtrInfoTable;
import org.cip4.jdflib.core.AttributeInfo;
import org.cip4.jdflib.core.AttributeName;
import org.cip4.jdflib.core.JDFElement;
import org.cip4.jdflib.core.JDFException;
import org.cip4.jdflib.datatypes.JDFRectangle;
import org.cip4.jdflib.datatypes.JDFRectangleRange;
import org.cip4.jdflib.datatypes.JDFRectangleRangeList;
import org.cip4.jdflib.datatypes.JDFXYPair;

public class JDFRectangleEvaluation extends JDFEvaluation
{
	private static final long serialVersionUID = 1L;

	private static AtrInfoTable[] atrInfoTable = new AtrInfoTable[3];
	static
	{
		atrInfoTable[0] = new AtrInfoTable(AttributeName.HWRELATION,
				0x33333333, AttributeInfo.EnumAttributeType.XYRelation, null,
				null);
		atrInfoTable[1] = new AtrInfoTable(AttributeName.TOLERANCE, 0x33333333,
				AttributeInfo.EnumAttributeType.XYPair, null, "0 0");
		atrInfoTable[2] = new AtrInfoTable(AttributeName.VALUELIST, 0x33333333,
				AttributeInfo.EnumAttributeType.RectangleRangeList, null, null);
	}

	protected AttributeInfo getTheAttributeInfo()
	{
		return super.getTheAttributeInfo().updateReplace(atrInfoTable);
	}

	/**
	 * JDFRectangleEvaluation
	 * 
	 * @param myOwnerDocument
	 * @param qualifiedName
	 */
	public JDFRectangleEvaluation(CoreDocumentImpl myOwnerDocument,
			String qualifiedName)
	{
		super(myOwnerDocument, qualifiedName);
	}

	/**
	 * JDFRectangleEvaluation
	 * 
	 * @param myOwnerDocument
	 * @param myNamespaceURI
	 * @param qualifiedName
	 */
	public JDFRectangleEvaluation(CoreDocumentImpl myOwnerDocument,
			String myNamespaceURI, String qualifiedName)
	{
		super(myOwnerDocument, myNamespaceURI, qualifiedName);
	}

	/**
	 * JDFRectangleEvaluation
	 * 
	 * @param myOwnerDocument
	 * @param myNamespaceURI
	 * @param qualifiedName
	 * @param myLocalName
	 */
	public JDFRectangleEvaluation(CoreDocumentImpl myOwnerDocument,
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
		return "JDFRectangleEvaluation[ --> " + super.toString() + " ]";
	}

	/*
	 * // Attribute getter / setter
	 */

	public void setValueList(JDFRectangleRangeList value)
	{
		setAttribute(AttributeName.VALUELIST, value.toString());
	}

	/**
	 * set valuelist to exactly one rectangle
	 * 
	 * @param value
	 */
	public void setValueList(JDFRectangle value)
	{
		setAttribute(AttributeName.VALUELIST, value.toString());
	}

	/**
	 * 
	 * @return
	 */
	public JDFRectangleRangeList getValueList()
	{
		final String vl = getAttribute(AttributeName.VALUELIST, null, null);
		if (vl == null)
			return null;

		try
		{
			return new JDFRectangleRangeList(vl);
		} catch (DataFormatException e)
		{
			throw new JDFException(
					"JDFRectangleEvaluation.getValueList: Attribute VALUELIST is not capable to create JDFRectangleRangeList");
		}
	}

	public void setHWRelation(EnumXYRelation value)
	{
		setAttribute(AttributeName.HWRELATION, value.getName(), null);
	}

	public JDFElement.EnumXYRelation getHWRelation()
	{
		return JDFElement.EnumXYRelation.getEnum(getAttribute(
				AttributeName.HWRELATION, null, null));
	}

	public void setTolerance(JDFXYPair value)
	{
		setAttribute(AttributeName.TOLERANCE, value.toString(), null);
	}

	public JDFXYPair getTolerance()
	{
		return super.getTolerance();
	}

	/*
	 * // FitsValue Methods
	 */

	/**
	 * fitsValue - checks whether <code>value</code> matches the testlists
	 * specified for this Evaluation
	 * 
	 * @param value
	 *            value to test
	 * @return boolean - true, if <code>value</code> matches the testlists or if
	 *         testlists are not specified
	 */
	public final boolean fitsValue(String value)
	{
		if (!fitsListType(value))
			return false;

		JDFRectangleRangeList rrl = null;
		try
		{
			rrl = new JDFRectangleRangeList(value);
		} catch (DataFormatException dfe)
		{
			return false;
		}

		int siz = rrl.size();
		for (int i = 0; i < siz; i++) // For every range, that rangelist
										// consists of,
		{ // we test both of range deliminators - right and left, if they fit
			// HWRelation
			// In this case test of deliminators is sufficient for evaluation of
			// the whole range
			JDFRectangleRange range = (JDFRectangleRange) rrl.at(i);

			JDFRectangle left = range.getLeft();
			JDFRectangle right = range.getRight();

			if (left.equals(right))
			{
				JDFRectangle rectangle = left;
				if ((fitsValueList(new JDFRectangleRange(rectangle)) && fitsHWRelation(rectangle)) == false)
					return false;
			} else
			{
				if ((fitsValueList(range) && fitsHWRelation(left) && fitsHWRelation(right)) == false)
					return false;
			}
		}
		return true;

	}

	/**
	 * fitsListType - checks whether <code>value</code> matches the value of the
	 * ListType attribute specified for this Evaluation
	 * 
	 * @param value
	 *            value to test
	 * @return boolean - true, if <code>value</code> matches the specified value
	 *         of ListType
	 */
	private final boolean fitsListType(String value)
	{
		EnumListType listType = getListType();

		JDFRectangleRangeList rangelist;
		try
		{
			rangelist = new JDFRectangleRangeList(value);
		} catch (DataFormatException e)
		{
			return false;
		} catch (JDFException e)
		{
			return false;
		}

		if (listType.equals(EnumListType.SingleValue)
				|| listType.equals(EnumListType.getEnum(0)))
		{// default ListType = SingleValue
			try
			{
				new JDFRectangle(value);
			} catch (JDFException e)
			{
				return false;
			} catch (DataFormatException e)
			{
				return false;
			}
			return true;
		} else if (listType.equals(EnumListType.RangeList))
		{
			return true;
		} else if (listType.equals(EnumListType.List))
		{
			return rangelist.isList();
		} else if (listType.equals(EnumListType.OrderedList))
		{
			return (rangelist.isList() && rangelist.isOrdered());
		} else if (listType.equals(EnumListType.UniqueList))
		{
			return (rangelist.isList() && rangelist.isUnique());
		} else if (listType.equals(EnumListType.UniqueOrderedList))
		{
			return (rangelist.isList() && rangelist.isUniqueOrdered());
		} else if (listType.equals(EnumListType.OrderedRangeList))
		{
			return rangelist.isOrdered();
		} else if (listType.equals(EnumListType.UniqueRangeList))
		{
			return rangelist.isUnique();
		} else if (listType.equals(EnumListType.UniqueOrderedRangeList))
		{
			return (rangelist.isUniqueOrdered());
		} else
		{
			throw new JDFException(
					"JDFRectangleEvaluation.fitsListType illegal ListType attribute");
		}
	}

	/**
	 * fitsValueList - checks whether <code>range</code> is in the ValueList
	 * specified for this Evaluation
	 * 
	 * @param range
	 *            range to test
	 * @return boolean - true, if <code>range</code> is in the ValueList or if
	 *         ValueList is not specified
	 */
	private final boolean fitsValueList(JDFRectangleRange range)
	{
		if (!hasAttribute(AttributeName.VALUELIST))
			return true;

		JDFRectangleRangeList rangelist = getValueList();

		if (hasAttribute(AttributeName.TOLERANCE))
			return (fitsTolerance(rangelist).isPartOfRange(range));
		return rangelist.isPartOfRange(range);

	}

	/**
	 * fitsTolerance - checks whether this Evaluation has a specified Tolerance
	 * that it is not equal to "0 0", and expands original the rangelist to the
	 * rangelist that fits Tolerance.
	 * 
	 * @param rangeList
	 *            original rangelist
	 * @return NumberRangeList - expanded rangelist, returns original range if
	 *         Tolerance=="0 0"
	 */
	private JDFRectangleRangeList fitsTolerance(
			JDFRectangleRangeList origRangeList)
	{
		double nt = getTolerance().getX(); // negative tolerance
		double pt = getTolerance().getY(); // positive tolerance

		if ((nt == 0) && (pt == 0))
		{
			return origRangeList;
		}

		// expand our original range into the range +/- Tolerance

		JDFRectangleRangeList rangeList = new JDFRectangleRangeList(
				origRangeList);

		JDFRectangleRangeList tolRangeList = new JDFRectangleRangeList();

		int size = rangeList.size();
		for (int i = 0; i < size; i++)
		{
			JDFRectangleRange range = (JDFRectangleRange) rangeList.at(i);

			JDFRectangle left = range.getLeft();
			double leftLlx = left.getLlx();
			double leftLly = left.getLly();
			double leftUrx = left.getUrx();
			double leftUry = left.getUry();
			left.setLlx(leftLlx - nt);
			left.setLly(leftLly - nt);
			left.setUrx(leftUrx - nt);
			left.setUry(leftUry - nt);

			JDFRectangle right = range.getRight();
			double rightLlx = right.getLlx();
			double rightLly = right.getLly();
			double rightUrx = right.getUrx();
			double rightUry = right.getUry();
			right.setLlx(rightLlx + pt);
			right.setLly(rightLly + pt);
			right.setUrx(rightUrx + pt);
			right.setUry(rightUry + pt);

			range.setLeft(left);
			range.setRight(right);

			tolRangeList.append(range);

		}
		return tolRangeList;

	}

	/**
	 * fitsHWRelation - checks whether <code>rect</code> value matches the
	 * HWRelation specified for this Evaluation
	 * 
	 * @param rect
	 *            rectangle value to test
	 * @return boolean - true, if <code>rect</code> matches HWRelation or if
	 *         HWRelation is not specified
	 */
	private final boolean fitsHWRelation(JDFRectangle rect)
	{
		double width = rect.getUrx() - rect.getLlx();
		double height = rect.getUry() - rect.getLly();

		if (!hasAttribute(AttributeName.HWRELATION))
			return true;
		if (!hasAttribute(AttributeName.TOLERANCE))
			return getHWRelation().evaluateXY(width, height, EPSILON, EPSILON);

		double nt = getTolerance().getX(); // negative tolerance
		double pt = getTolerance().getY(); // positive tolerance

		return getHWRelation().evaluateXY(width, height, nt, pt);
	}

}