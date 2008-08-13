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
 * JDFShapeState.java
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
import org.cip4.jdflib.datatypes.JDFNumberRange;
import org.cip4.jdflib.datatypes.JDFNumberRangeList;
import org.cip4.jdflib.datatypes.JDFShape;
import org.cip4.jdflib.datatypes.JDFShapeRange;
import org.cip4.jdflib.datatypes.JDFShapeRangeList;
import org.cip4.jdflib.resource.devicecapability.JDFTerm.EnumTerm;

public class JDFShapeState extends JDFAbstractState
{
	private static final long serialVersionUID = 1L;

	private static AtrInfoTable[] atrInfoTable = new AtrInfoTable[14];
	static
	{
		atrInfoTable[0] = new AtrInfoTable(AttributeName.ALLOWEDVALUELIST,
				0x33333331, AttributeInfo.EnumAttributeType.ShapeRangeList,
				null, null);
		atrInfoTable[1] = new AtrInfoTable(AttributeName.ALLOWEDVALUEMAX,
				0x44444431, AttributeInfo.EnumAttributeType.shape, null, null);
		atrInfoTable[2] = new AtrInfoTable(AttributeName.ALLOWEDVALUEMIN,
				0x44444431, AttributeInfo.EnumAttributeType.shape, null, null);
		atrInfoTable[3] = new AtrInfoTable(AttributeName.ALLOWEDX, 0x33333311,
				AttributeInfo.EnumAttributeType.NumberRangeList, null, null);
		atrInfoTable[4] = new AtrInfoTable(AttributeName.ALLOWEDY, 0x33333311,
				AttributeInfo.EnumAttributeType.NumberRangeList, null, null);
		atrInfoTable[5] = new AtrInfoTable(AttributeName.ALLOWEDZ, 0x33333311,
				AttributeInfo.EnumAttributeType.NumberRangeList, null, null);
		atrInfoTable[6] = new AtrInfoTable(AttributeName.CURRENTVALUE,
				0x33333331, AttributeInfo.EnumAttributeType.shape, null, null);
		atrInfoTable[7] = new AtrInfoTable(AttributeName.DEFAULTVALUE,
				0x33333331, AttributeInfo.EnumAttributeType.shape, null, null);
		atrInfoTable[8] = new AtrInfoTable(AttributeName.PRESENTVALUELIST,
				0x33333311, AttributeInfo.EnumAttributeType.ShapeRangeList,
				null, null);
		atrInfoTable[9] = new AtrInfoTable(AttributeName.PRESENTVALUEMAX,
				0x44444431, AttributeInfo.EnumAttributeType.shape, null, null);
		atrInfoTable[10] = new AtrInfoTable(AttributeName.PRESENTVALUEMIN,
				0x44444431, AttributeInfo.EnumAttributeType.shape, null, null);
		atrInfoTable[11] = new AtrInfoTable(AttributeName.PRESENTX, 0x33333311,
				AttributeInfo.EnumAttributeType.NumberRangeList, null, null);
		atrInfoTable[12] = new AtrInfoTable(AttributeName.PRESENTY, 0x33333311,
				AttributeInfo.EnumAttributeType.NumberRangeList, null, null);
		atrInfoTable[13] = new AtrInfoTable(AttributeName.PRESENTZ, 0x33333311,
				AttributeInfo.EnumAttributeType.NumberRangeList, null, null);
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
	 * constructor for JDFShapeState
	 * 
	 * @param myOwnerDocument
	 * @param qualifiedName
	 */
	public JDFShapeState(CoreDocumentImpl myOwnerDocument, String qualifiedName)
	{
		super(myOwnerDocument, qualifiedName);
	}

	/**
	 * constructor for JDFShapeState
	 * 
	 * @param myOwnerDocument
	 * @param myNamespaceURI
	 * @param qualifiedName
	 */
	public JDFShapeState(CoreDocumentImpl myOwnerDocument,
			String myNamespaceURI, String qualifiedName)
	{
		super(myOwnerDocument, myNamespaceURI, qualifiedName);
	}

	/**
	 * constructor for JDFShapeState
	 * 
	 * @param myOwnerDocument
	 * @param myNamespaceURI
	 * @param qualifiedName
	 * @param myLocalName
	 */
	public JDFShapeState(CoreDocumentImpl myOwnerDocument,
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
		return "JDFShapeState[  --> " + super.toString() + " ]";
	}

	/*
	 * // Attribute getter/ setter
	 */

	public void setCurrentValue(JDFShape value)
	{
		setAttribute(AttributeName.CURRENTVALUE, value.toString(), null);
	}

	public JDFShape getCurrentValue()
	{
		try
		{
			return new JDFShape(getAttribute(AttributeName.CURRENTVALUE));
		} catch (DataFormatException e)
		{
			throw new JDFException(
					"JDFShapeState.getCurrentValue: Attribute CURRENTVALUE is not capable to create JDFShape");
		}
	}

	public void setDefaultValue(JDFShape value)
	{
		setAttribute(AttributeName.DEFAULTVALUE, value.toString(), null);
	}

	public JDFShape getDefaultValue()
	{
		try
		{
			return new JDFShape(getAttribute(AttributeName.DEFAULTVALUE));
		} catch (DataFormatException e)
		{
			throw new JDFException(
					"JDFShapeState.getDefaultValue: Attribute DEFAULTVALUE is not capable to create JDFShape");
		}
	}

	public void setAllowedValueList(JDFShapeRangeList value)
	{
		setAttribute(AttributeName.ALLOWEDVALUELIST, value.toString());
	}

	public JDFShapeRangeList getAllowedValueList()
	{
		try
		{
			final String attribute = getAttribute(
					AttributeName.ALLOWEDVALUELIST, null, null);
			return attribute == null ? null : new JDFShapeRangeList(attribute);
		} catch (DataFormatException e)
		{
			throw new JDFException(
					"JDFShapeState.getAllowedValueList: Attribute ALLOWEDVALUELIST is not capable to create JDFShapeRangeList");
		}
	}

	public void setPresentValueList(JDFShapeRangeList value)
	{
		setAttribute(AttributeName.PRESENTVALUELIST, value.toString(), null);
	}

	public JDFShapeRangeList getPresentValueList()
	{
		if (hasAttribute(AttributeName.PRESENTVALUELIST))
		{
			try
			{
				return new JDFShapeRangeList(
						getAttribute(AttributeName.PRESENTVALUELIST));
			} catch (DataFormatException e)
			{
				throw new JDFException(
						"JDFShapeState.getPresentValueList: Attribute PRESENTVALUELIST is not capable to create JDFShapeRangeList");
			}
		}
		return getAllowedValueList();
	}

	public void setAllowedX(JDFNumberRangeList value)
	{
		setAttribute(AttributeName.ALLOWEDX, value.toString(), null);
	}

	public JDFNumberRangeList getAllowedX()
	{
		try
		{
			return new JDFNumberRangeList(getAttribute(AttributeName.ALLOWEDX));
		} catch (DataFormatException e)
		{
			throw new JDFException(
					"JDFShapeState.getAllowedX: Attribute ALLOWEDX is not capable to create JDFNumberRangeList");
		}
	}

	public void setPresentX(JDFNumberRangeList value)
	{
		setAttribute(AttributeName.PRESENTX, value.toString(), null);
	}

	public JDFNumberRangeList getPresentX()
	{
		if (hasAttribute(AttributeName.PRESENTX))
		{
			try
			{
				return new JDFNumberRangeList(
						getAttribute(AttributeName.PRESENTX));
			} catch (DataFormatException e)
			{
				throw new JDFException(
						"JDFShapeState.getPresentX: Attribute PRESENTX is not capable to create JDFNumberRangeList");
			}
		}
		return getAllowedX();
	}

	public void setAllowedY(JDFNumberRangeList value)
	{
		setAttribute(AttributeName.ALLOWEDY, value.toString(), null);
	}

	public JDFNumberRangeList getAllowedY()
	{
		try
		{
			return new JDFNumberRangeList(getAttribute(AttributeName.ALLOWEDY));
		} catch (DataFormatException e)
		{
			throw new JDFException(
					"JDFShapeState.getAllowedY: Attribute ALLOWEDY is not capable to create JDFNumberRangeList");
		}
	}

	public void setPresentY(JDFNumberRangeList value)
	{
		setAttribute(AttributeName.PRESENTY, value.toString(), null);
	}

	public JDFNumberRangeList getPresentY()
	{
		if (hasAttribute(AttributeName.PRESENTY))
		{
			try
			{
				return new JDFNumberRangeList(
						getAttribute(AttributeName.PRESENTY));
			} catch (DataFormatException e)
			{
				throw new JDFException(
						"JDFShapeState.getPresentY: Attribute PRESENTY is not capable to create JDFNumberRangeList");
			}
		}
		return getAllowedY();
	}

	public void setAllowedZ(JDFNumberRangeList value)
	{
		setAttribute(AttributeName.ALLOWEDZ, value.toString(), null);
	}

	public JDFNumberRangeList getAllowedZ()
	{
		try
		{
			return new JDFNumberRangeList(getAttribute(AttributeName.ALLOWEDZ));
		} catch (DataFormatException e)
		{
			throw new JDFException(
					"JDFShapeState.getAllowedZ: Attribute ALLOWEDZ is not capable to create JDFNumberRangeList");
		}
	}

	public void setPresentZ(JDFNumberRangeList value)
	{
		setAttribute(AttributeName.PRESENTZ, value.toString(), null);
	}

	public JDFNumberRangeList getPresentZ()
	{
		if (hasAttribute(AttributeName.PRESENTZ))
		{
			try
			{
				return new JDFNumberRangeList(
						getAttribute(AttributeName.PRESENTZ));
			} catch (DataFormatException e)
			{
				throw new JDFException(
						"JDFShapeState.getPresentZ: Attribute PRESENTZ is not capable to create JDFNumberRangeList");
			}
		}
		return getAllowedZ();
	}

	public void setAllowedValueMax(JDFShape value)
	{
		setAttribute(AttributeName.ALLOWEDVALUEMAX, value.toString(), null);
	}

	public JDFShape getAllowedValueMax()
	{
		try
		{
			return new JDFShape(getAttribute(AttributeName.ALLOWEDVALUEMAX));
		} catch (DataFormatException e)
		{
			throw new JDFException(
					"JDFShapeState.getAllowedValueMax: Attribute ALLOWEDVALUEMAX is not capable to create JDFShape");
		}
	}

	public void setPresentValueMax(JDFShape value)
	{
		setAttribute(AttributeName.PRESENTVALUEMAX, value.toString(), null);
	}

	public JDFShape getPresentValueMax()
	{
		if (hasAttribute(AttributeName.PRESENTVALUEMAX))
		{
			try
			{
				return new JDFShape(getAttribute(AttributeName.PRESENTVALUEMAX));
			} catch (DataFormatException e)
			{
				throw new JDFException(
						"JDFShapeState.getPresentValueMax: Attribute PRESENTVALUEMAX is not capable to create JDFShape");
			}

		}
		return getAllowedValueMax();
	}

	public void setAllowedValueMin(JDFShape value)
	{
		setAttribute(AttributeName.ALLOWEDVALUEMIN, value.toString(), null);
	}

	public JDFShape getAllowedValueMin()
	{
		try
		{
			return new JDFShape(getAttribute(AttributeName.ALLOWEDVALUEMIN));
		} catch (DataFormatException e)
		{
			throw new JDFException(
					"JDFShapeState.setAllowedValueMin: Attribute ALLOWEDVALUEMIN is not capable to create JDFShape");
		}
	}

	public void setPresentValueMin(JDFShape value)
	{
		setAttribute(AttributeName.PRESENTVALUEMIN, value.toString(), null);
	}

	public JDFShape getPresentValueMin()
	{
		if (hasAttribute(AttributeName.PRESENTVALUEMIN))
		{
			try
			{
				return new JDFShape(getAttribute(AttributeName.PRESENTVALUEMIN));
			} catch (DataFormatException dfe)
			{
				throw new JDFException(
						"JDFShapeState.getPresentValueMin: Attribute PRESENTVALUEMIN is not capable to create JDFShape");
			}

		}
		return getAllowedValueMin();
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

		JDFShape rect;
		try
		{
			rect = new JDFShape(value);
		} catch (DataFormatException x)
		{
			return; // nop for bad values
		}
		if (testlists == null || EnumFitsValue.Allowed.equals(testlists))
		{
			JDFShapeRangeList list = getAllowedValueList();
			if (list == null)
				list = new JDFShapeRangeList();
			list.append(rect);
			setAllowedValueList(list);
		}
		if (testlists == null || EnumFitsValue.Present.equals(testlists))
		{
			JDFShapeRangeList list = getPresentValueList();
			if (list == null || !hasAttribute(AttributeName.PRESENTVALUELIST))
				list = new JDFShapeRangeList();
			list.append(rect);
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
		if (!fitsListType(value))
			return false;

		JDFShapeRangeList rangelist = null;
		try
		{
			rangelist = new JDFShapeRangeList(value);
		} catch (DataFormatException dfe)
		{
			return false;
		}

		return (fitsValueList(rangelist, testlists) && fitsXYZ(rangelist,
				testlists));

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
	private final boolean fitsValueList(JDFShapeRangeList rangelist,
			EnumFitsValue valuelist)
	{
		JDFShapeRangeList list;
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
			JDFShapeRange range = (JDFShapeRange) rangelist.at(i);

			if (!list.isPartOfRange(range))
				return false;
		}
		return true;
	}

	/**
	 * fitsXYZ - checks whether <code>rangelist</code> matches the (AllowedX,
	 * AllowedY, AllowedZ) or (PresentX, PresentY, PresentZ) values specified
	 * for this State
	 * 
	 * @param rangelist
	 *            range list to test
	 * @param xyzlist
	 *            switches between (AllowedX, AllowedY, AllowedZ) and (PresentX,
	 *            PresentY, PresentZ).
	 * @return boolean - true, if the 'rangelist' matches xyzlist or if
	 *         AllowedX, AllowedY, AllowedZ are not specified
	 */
	private boolean fitsXYZ(JDFShapeRangeList rangelist, EnumFitsValue xyzlist)
	{
		int siz = rangelist.size();
		for (int i = 0; i < siz; i++)
		{
			JDFShapeRange range = (JDFShapeRange) rangelist.at(i);

			JDFNumberRangeList x, y, z;

			JDFShape left = range.getLeft();
			JDFShape right = range.getRight();

			double leftX = left.getY();
			double rightX = right.getY();
			JDFNumberRange rangeX = new JDFNumberRange(leftX, rightX);

			double leftY = left.getX();
			double rightY = right.getX();
			JDFNumberRange rangeY = new JDFNumberRange(leftY, rightY);

			double leftZ = left.getZ();
			double rightZ = right.getZ();
			JDFNumberRange rangeZ = new JDFNumberRange(leftZ, rightZ);

			if (xyzlist.equals(EnumFitsValue.Allowed))
			{
				x = getAllowedX();
				y = getAllowedY();
				z = getAllowedZ();
			} else
			{
				x = getPresentX();
				y = getPresentY();
				z = getPresentZ();
			}

			boolean bFit = true;
			if (x.size() != 0)
			{
				bFit = x.isPartOfRange(rangeX);
			}
			if (!bFit)
				return false;

			if (y.size() != 0)
			{
				bFit = y.isPartOfRange(rangeY);
			}
			if (!bFit)
				return false;

			if (z.size() != 0)
			{
				bFit = z.isPartOfRange(rangeZ);
			}
			if (!bFit)
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
	 */
	private final boolean fitsCompleteList(JDFShapeRangeList value,
			JDFShapeRangeList list)
	{
		int v_size = value.size();
		int l_size = list.size();

		if (v_size != l_size)
			return false;

		if (!value.isUnique())
			return false;

		JDFShapeRangeList valueList = new JDFShapeRangeList(value);

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
	private final boolean fitsCompleteOrderedList(JDFShapeRangeList value,
			JDFShapeRangeList list)
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
	private final boolean fitsContainedList(JDFShapeRangeList value,
			JDFShapeRangeList list)
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

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.cip4.jdflib.ifaces.ICapabilityElement#getEvaluationType()
	 */
	public EnumTerm getEvaluationType()
	{
		return EnumTerm.RectangleEvaluation;
	}

}