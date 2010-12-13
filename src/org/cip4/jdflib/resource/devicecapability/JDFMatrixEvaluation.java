/*
 *
 * The CIP4 Software License, Version 1.0
 *
 *
 * Copyright (c) 2001-2010 The International Cooperation for the Integration of 
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
 * JDFMatrixEvaluation.java
 *
 */

package org.cip4.jdflib.resource.devicecapability;

import java.util.Vector;
import java.util.zip.DataFormatException;

import org.apache.xerces.dom.CoreDocumentImpl;
import org.cip4.jdflib.auto.JDFAutoBasicPreflightTest.EnumListType;
import org.cip4.jdflib.core.AtrInfoTable;
import org.cip4.jdflib.core.AttributeInfo;
import org.cip4.jdflib.core.AttributeName;
import org.cip4.jdflib.core.ElemInfoTable;
import org.cip4.jdflib.core.ElementInfo;
import org.cip4.jdflib.core.ElementName;
import org.cip4.jdflib.core.JDFConstants;
import org.cip4.jdflib.core.JDFElement;
import org.cip4.jdflib.core.JDFException;
import org.cip4.jdflib.core.VElement;
import org.cip4.jdflib.core.VString;
import org.cip4.jdflib.datatypes.JDFMatrix;
import org.cip4.jdflib.datatypes.JDFRectangle;
import org.cip4.jdflib.datatypes.JDFXYPair;
import org.cip4.jdflib.resource.JDFValue;

public class JDFMatrixEvaluation extends JDFEvaluation
{
	private static final long serialVersionUID = 1L;

	private static AtrInfoTable[] atrInfoTable = new AtrInfoTable[4];
	static
	{
		atrInfoTable[0] = new AtrInfoTable(AttributeName.ROTATEMOD, 0x33333333, AttributeInfo.EnumAttributeType.double_, null, null);
		atrInfoTable[1] = new AtrInfoTable(AttributeName.SHIFT, 0x33333333, AttributeInfo.EnumAttributeType.NumberList, null, null);
		atrInfoTable[2] = new AtrInfoTable(AttributeName.TOLERANCE, 0x33333333, AttributeInfo.EnumAttributeType.XYPair, null, "0 0");
		atrInfoTable[3] = new AtrInfoTable(AttributeName.TRANSFORMS, 0x33333333, AttributeInfo.EnumAttributeType.enumerations, EnumOrientation.getEnum(0), null);
	}

	@Override
	protected AttributeInfo getTheAttributeInfo()
	{
		return super.getTheAttributeInfo().updateReplace(atrInfoTable);
	}

	private static ElemInfoTable[] elemInfoTable = new ElemInfoTable[1];
	static
	{
		elemInfoTable[0] = new ElemInfoTable(ElementName.BASICPREFLIGHTTEST, 0x33333333);
	}

	@Override
	protected ElementInfo getTheElementInfo()
	{
		return new ElementInfo(super.getTheElementInfo(), elemInfoTable);
	}

	/**
	 * constructor for JDFMatrixEvaluation
	 * 
	 * @param myOwnerDocument
	 * @param qualifiedName
	 */
	public JDFMatrixEvaluation(CoreDocumentImpl myOwnerDocument, String qualifiedName)
	{
		super(myOwnerDocument, qualifiedName);
	}

	/**
	 * constructor for JDFMatrixEvaluation
	 * 
	 * @param myOwnerDocument
	 * @param myNamespaceURI
	 * @param qualifiedName
	 */
	public JDFMatrixEvaluation(CoreDocumentImpl myOwnerDocument, String myNamespaceURI, String qualifiedName)
	{
		super(myOwnerDocument, myNamespaceURI, qualifiedName);
	}

	/**
	 * constructor for JDFMatrixEvaluation
	 * 
	 * @param myOwnerDocument
	 * @param myNamespaceURI
	 * @param qualifiedName
	 * @param myLocalName
	 */
	public JDFMatrixEvaluation(CoreDocumentImpl myOwnerDocument, String myNamespaceURI, String qualifiedName, String myLocalName)
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
		return "JDFMatrixEvaluation[ --> " + super.toString() + " ]";
	}

	/*
	 * // Attribute getter/ setter
	 */

	public void setRotateMod(double value)
	{
		setAttribute(AttributeName.ROTATEMOD, value, null);
	}

	public double getRotateMod()
	{
		return getRealAttribute(AttributeName.ROTATEMOD, null, 0.0);
	}

	public void setShift(JDFRectangle value)
	{
		setAttribute(AttributeName.SHIFT, value.toString());
	}

	public JDFRectangle getShift()
	{

		try
		{
			return new JDFRectangle(getAttribute(AttributeName.SHIFT, null, JDFConstants.EMPTYSTRING));
		}
		catch (DataFormatException e)
		{
			throw new JDFException("JDFMatrixEvaluation.getShift: AttributeValue not capable to create JDFRectangle");
		}
	}

	/**
	 * 
	 * @return
	 */
	public Vector<EnumOrientation> getTransforms()
	{
		return (Vector<EnumOrientation>) getEnumerationsAttribute(AttributeName.TRANSFORMS, null, JDFElement.EnumOrientation.getEnum(0), false);
	}

	/**
	 * 
	 * @param value
	 */
	public void setTransforms(Vector<EnumOrientation> value)
	{
		setEnumerationsAttribute(AttributeName.TRANSFORMS, value, null);
	}

	public void setTolerance(JDFXYPair value)
	{
		setAttribute(AttributeName.TOLERANCE, value.toString(), null);
	}

	@Override
	public JDFXYPair getTolerance()
	{
		return super.getTolerance();
	}

	/*
	 * // Element getter / setter
	 */

	public JDFValue getValue(int iSkip)
	{
		JDFValue e = (JDFValue) getElement(ElementName.VALUE, JDFConstants.EMPTYSTRING, iSkip);
		return e;
	}

	public JDFValue appendValue()
	{
		return (JDFValue) appendElement(ElementName.VALUE, null);
	}

	/*
	 * // Subelement attribute and element Getter / Setter
	 */

	/*
	 * // Subelement attribute and element Getter / Setter
	 */

	/**
	 * Sets the <code>Value</code> attribute of the i-th subelement Value
	 * 
	 * @param iSkip
	 *            the number of Value elements to skip
	 * @param value
	 *            value to set the attribute to
	 */
	public void setValueValue(int iSkip, JDFMatrix value)
	{
		JDFValue e = (JDFValue) getElement(ElementName.VALUE, null, iSkip);
		e.setValue(value.toString());
	}

	/**
	 * Gets the <code>Value</code> attribute of the i-th subelement Value
	 * 
	 * @param iSkipthe
	 *            number of Value elements to skip
	 * @return JDFMatrix: the attribute value
	 */
	public final JDFMatrix getValueValue(int iSkip)
	{
		JDFValue e = (JDFValue) getElement(ElementName.VALUE, null, iSkip);

		try
		{
			return new JDFMatrix(e.getValue());
		}
		catch (DataFormatException dfe)
		{
			throw new JDFException("JDFMatrixState.getValueValue: AttributeValue not capable to create JDFMatrix");
		}
	}

	/*
	 * // FitsValue Methods
	 */

	/**
	 * fitsValue - tests, if the defined 'value' matches testlists, specified
	 * for this Evaluation
	 * 
	 * @param String
	 *            value - value to test
	 * @return boolean - true, if 'value' matches testlists or if testlists are
	 *         not specified
	 */
	@Override
	public boolean fitsValue(String value)
	{
		VString vs = new VString(value, JDFConstants.BLANK);
		int siz = vs.size();
		if (siz % 6 != 0)
		{
			return false;
		}
		VString matrixList = new VString();
		int i = 0;
		StringBuffer sb = new StringBuffer(250);
		sb.append(vs.elementAt(i));
		while ((i + 1) < siz)
		{
			do
			{
				sb.append(JDFConstants.BLANK);
				i++;
				sb.append(vs.elementAt(i));
			}
			while ((i + 1) % 6 != 0);
			matrixList.add(sb.toString());
			if ((i + 1) < siz)
			{
				i++;
				sb = new StringBuffer(250);
				sb.append(vs.elementAt(i));
			}
		}

		if (!fitsListType(matrixList))
			return false;

		for (int k = 0; k < matrixList.size(); k++)
		{
			String str = matrixList.elementAt(k);
			JDFMatrix matrix;
			try
			{
				matrix = new JDFMatrix(str);
			}
			catch (DataFormatException dfe)
			{
				return false;
			}
			if ((fitsRotateMod(matrix) && fitsShift(matrix) && fitsTransforms(matrix) && fitsValueElem(matrix)) == false)
				return false;
		}
		return true;

	}

	/**
	 * fitsListType - checks whether <code>matrixList</code> matches the
	 * ListType attribute specified for this Evaluation
	 * 
	 * @param matrixList
	 *            value to test
	 * @return boolean - true, if <code>matrixList</code> matches specified
	 *         value of ListType
	 */
	public final boolean fitsListType(VString matrixList)
	{
		EnumListType listType = getListType();

		int size = matrixList.size();
		for (int i = 0; i < size; i++)
		{
			try
			{
				new JDFMatrix(matrixList.elementAt(i));
			}
			catch (JDFException e)
			{
				return false;
			}
			catch (DataFormatException dfe)
			{
				return false;
			}
		}

		if (listType.equals(EnumListType.SingleValue) || listType.equals(EnumListType.getEnum(0)))
		{// default ListType = SingleValue
			return (size == 1);
		}
		else if (listType.equals(EnumListType.List))
		{
			return true;
		}
		else if (listType.equals(EnumListType.UniqueList))
		{
			for (int i = 0; i < size; i++)
			{
				for (int j = 0; j < size; j++)
				{
					if (j != i)
					{
						String mi = matrixList.elementAt(i);
						String mj = matrixList.elementAt(j);
						if (mi.compareTo(mj) == 0)
							return false;
					}
				}
			}
			return true;
		}
		else
		{
			throw new JDFException("JDFMatrixEvaluation.fitsListType illegal ListType attribute");
		}
	}

	/**
	 * fitsValueElem - checks whether <code>matrix</code> matches subelement
	 * Value, specified for this Evaluation
	 * 
	 * @param matrix
	 *            JDFMatrix to test
	 * 
	 * @return boolean - true, if 'matrix' matches subelement Value
	 */
	public final boolean fitsValueElem(JDFMatrix matrix)
	{

		VElement v = getChildElementVector(ElementName.VALUE, null, null, true, 0, false);
		int siz = v.size();
		if (siz == 0)
		{
			return true; // Evaluation has no Value elements
		}
		for (int i = 0; i < siz; i++)
		{
			JDFMatrix value = getValueValue(i); // JDFValue elm = (JDFValue)
												// v.elementAt(i);
			if (value.equals(matrix))
				return true; // we have found it

		}
		return false;
	}

	/**
	 * fitsRotateMod - checks whether <code>matrix</code> matches the
	 * <code>RotateMod</code> attribute specified for this Evaluation
	 * 
	 * @param matrix
	 *            matrix to test
	 * @return boolean - true, if <code>matrix</code> matches the RotateMod or
	 *         if RotateMod is not specified
	 */
	public final boolean fitsRotateMod(JDFMatrix matrix)
	{
		if (!hasAttribute(AttributeName.ROTATEMOD))
			return true;

		double rm = getRotateMod();

		double a = matrix.getA();
		double b = matrix.getB();
		double c = matrix.getC();
		double d = matrix.getD();

		if ((a * d - b * c) == 0)
			return false;

		double nT; // negative tolerance
		double pT; // positive tolerance

		if (hasAttribute(AttributeName.TOLERANCE))
		{
			nT = getTolerance().getX();
			pT = getTolerance().getY();
		}
		else
		{
			nT = pT = EPSILON;
		}

		double param = a / java.lang.Math.sqrt(java.lang.Math.abs(a * d - b * c));

		if (((param - EPSILON) > 1) || ((param + EPSILON) < -1))
		{
			return false;
		}
		if (param > 1)
		{
			param = param - EPSILON;
		}
		if (param < -1)
		{
			param = param + EPSILON;
		}

		double fi = java.lang.Math.acos(param) * 180 / java.lang.Math.PI; //0~180

		double result = (fi + nT) - (rm * (int) ((fi + nT) / rm));
		double result180 = (fi + 180 + nT) - (rm * (int) ((fi + 180 + nT) / rm));

		return (java.lang.Math.abs(result) <= (nT + pT) || java.lang.Math.abs(result180) <= (nT + pT)); // (fi+nT)%rm <= (nT+pT)

	}

	/**
	 * fitsShift - checks whether <code>matrix</code> matches the
	 * <code>Shift</code> attribute specified for this Evaluation
	 * 
	 * @param matrix
	 *            matrix to test
	 * @return boolean - true, if 'matrix' matches the Shift or if Shift is not
	 *         specified
	 */
	public final boolean fitsShift(JDFMatrix matrix)
	{
		if (!hasAttribute(AttributeName.SHIFT))
			return true;

		JDFRectangle shiftValue = new JDFRectangle(getShift());

		double minTx = shiftValue.getLlx();
		double minTy = shiftValue.getLly();
		double maxTx = shiftValue.getUrx();
		double maxTy = shiftValue.getUry();

		double Tx = matrix.getTx();
		double Ty = matrix.getTy();

		if (!hasAttribute(AttributeName.TOLERANCE))
		{
			return ((Tx >= minTx) && (Tx <= maxTx) && (Ty >= minTy) && (Ty <= maxTy));
		}

		double nT = getTolerance().getX(); // negative tolerance
		double pT = getTolerance().getY(); // positive tolerance

		return ((Tx - nT >= minTx) && (Tx + pT <= maxTx) && (Ty - nT >= minTy) && (Ty + pT <= maxTy));
	}

	// ////////////////////////////////////////////////////////////////////

	/**
	 * fitsTransforms - checks whether <code>matrix</code> matches the
	 * <code>Transforms</code> attribute specified for this Evaluation
	 * 
	 * @param matrix
	 *            matrix to test
	 * @return boolean - true, if <code>matrix</code> matches the Transforms or
	 *         if Transforms is not specified
	 */
	public final boolean fitsTransforms(JDFMatrix matrix)
	{
		Vector<EnumOrientation> vTransf = getTransforms();
		if (vTransf == null || vTransf.size() == 0)
			return true;

		double nT; // negative tolerance
		double pT; // positive tolerance

		if (hasAttribute(AttributeName.TOLERANCE))
		{
			nT = getTolerance().getX();
			pT = getTolerance().getY();
		}
		else
		{
			nT = pT = EPSILON;
		}

		double a = matrix.getA();
		double b = matrix.getB();
		double c = matrix.getC();
		double d = matrix.getD();

		double det = (a * d - b * c);

		if (det == 0)
			return false;

		det = java.lang.Math.sqrt(java.lang.Math.abs(det));
		a = a / det;
		b = b / det;
		c = c / det;
		d = d / det;

		for (EnumOrientation orientation : vTransf)
		{
			if (orientation.equals(EnumOrientation.Flip0)) // a=1 b=0 c=0 d=-1
			{
				if ((a - 1 < pT) && (a - 1 > -nT) && (b < pT) && (b > -nT) && (c < pT) && (c > -nT) && (d + 1 < pT) && (d + 1 > -nT))
					return true;
			}
			else if (orientation.equals(EnumOrientation.Flip90)) // a=0 b=-1 c=-1 d=0
			{
				if ((a < pT) && (a > -nT) && (b + 1 < pT) && (b + 1 > -nT) && (c + 1 < pT) && (c + 1 > -nT) && (d < pT) && (d > -nT))
					return true;
			}
			else if (orientation.equals(EnumOrientation.Flip180)) // a=-1 b=0 c=0 d=1
			{
				if ((a + 1 < pT) && (a + 1 > -nT) && (b < pT) && (b > -nT) && (c < pT) && (c > -nT) && (d - 1 < pT) && (d - 1 > -nT))
					return true;
			}
			else if (orientation.equals(EnumOrientation.Flip270)) // a=0 b=1 c=1 d=0
			{
				if ((a < pT) && (a > -nT) && (b - 1 < pT) && (b - 1 > -nT) && (c - 1 < pT) && (c - 1 > -nT) && (d < pT) && (d > -nT))
					return true;
			}
			else if (orientation.equals(EnumOrientation.Rotate0)) // a=1 b=0 c=0 d=1
			{
				if ((a - 1 < pT) && (a - 1 > -nT) && (b < pT) && (b > -nT) && (c < pT) && (c > -nT) && (d - 1 < pT) && (d - 1 > -nT))
					return true;
			}
			else if (orientation.equals(EnumOrientation.Rotate90)) // a=0 b=1 c=-1  d=0
			{
				if ((a < pT) && (a > -nT) && (b - 1 < pT) && (b - 1 > -nT) && (c + 1 < pT) && (c + 1 > -nT) && (d < pT) && (d > -nT))
					return true;
			}
			else if (orientation.equals(EnumOrientation.Rotate180)) // a=-1  b=0 c=0 d=-1
			{
				if ((a + 1 < pT) && (a + 1 > -nT) && (b < pT) && (b > -nT) && (c < pT) && (c > -nT) && (d + 1 < pT) && (d + 1 > -nT))
					return true;
			}
			else if (orientation.equals(EnumOrientation.Rotate270)) // a=0 b=-1 c=1 d=0
			{
				if ((a < pT) && (a > -nT) && (b + 1 < pT) && (b + 1 > -nT) && (c - 1 < pT) && (c - 1 > -nT) && (d < pT) && (d > -nT))
					return true;
			}
			else
			{
				return true;
			}
		}
		return false;
	}
}
