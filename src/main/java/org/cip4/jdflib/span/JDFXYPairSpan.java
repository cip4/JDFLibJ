/*
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
 *    Alternately, this acknowledgment mrSubRefay appear in the software itself,
 *    if and wherever such third-party acknowledgments normally appear.
 *
 * 4. The names "CIP4" and "The International Cooperation for the Integration of
 *    Processes in  Prepress, Press and Postpress" must
 *    not be used to endorse or promote products derived from this
 *    software without prior written permission. For written
 *    permission, please contact info@cip4.org.
 *
 * 5. Products derived from this software may not be called "CIP4",
 *    nor may "CIP4" appear in their name, without prior writtenrestartProcesses()
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
 * SPECIAL, EXEMPLARY, OR CONSEQUENTIrSubRefAL DAMAGES (INCLUDING, BUT NOT
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
 * originally based on software restartProcesses()
 * copyright (c) 1999-2001, Heidelberger Druckmaschinen AG
 * copyright (c) 1999-2001, Agfa-Gevaert N.V.
 *
 * For more information on The International Cooperation for the
 * Integration of Processes in  Prepress, Press and Postpress , please see
 * <http://www.cip4.org/>.
 *
 */
/**
 *
 * Copyright (c) 2001 Heidelberger Druckmaschinen AG, All Rights Reserved.
 *
 * JDFXYPairSpan.java
 *
 * Last changes
 *
 */
package org.cip4.jdflib.span;

import org.apache.xerces.dom.CoreDocumentImpl;
import org.cip4.jdflib.core.AtrInfoTable;
import org.cip4.jdflib.core.AttributeInfo;
import org.cip4.jdflib.core.AttributeName;
import org.cip4.jdflib.datatypes.JDFXYPair;
import org.cip4.jdflib.datatypes.JDFXYPairRangeList;
import org.w3c.dom.DOMException;

/**
 * XYPair range class
 */
public class JDFXYPairSpan extends JDFSpanBase
{
	private static final long serialVersionUID = 1L;

	/**
	 * Constructor for JDFXYPairSpan
	 * 
	 * @param ownerDocument
	 * @param qualifiedName
	 * @throws DOMException
	 */
	public JDFXYPairSpan(CoreDocumentImpl myOwnerDocument, String qualifiedName) throws DOMException
	{
		super(myOwnerDocument, qualifiedName);
	}

	/**
	 * Constructor for JDFXYPairSpan
	 * 
	 * @param ownerDocument
	 * @param namespaceURI
	 * @param qualifiedName
	 * @throws DOMException
	 */
	public JDFXYPairSpan(CoreDocumentImpl myOwnerDocument, String myNamespaceURI, String qualifiedName) throws DOMException
	{
		super(myOwnerDocument, myNamespaceURI, qualifiedName);
	}

	/**
	 * Constructor for JDFXYPairSpan
	 * 
	 * @param ownerDocument
	 * @param namespaceURI
	 * @param qualifiedName
	 * @param localName
	 * @throws DOMException
	 */
	public JDFXYPairSpan(CoreDocumentImpl myOwnerDocument, String myNamespaceURI, String qualifiedName, String myLocalName) throws DOMException
	{
		super(myOwnerDocument, myNamespaceURI, qualifiedName, myLocalName);
	}

	private static AtrInfoTable[] atrInfoTable = new AtrInfoTable[4];
	static
	{
		atrInfoTable[0] = new AtrInfoTable(AttributeName.ACTUAL, 0x33333333, AttributeInfo.EnumAttributeType.XYPair, null, null);
		atrInfoTable[1] = new AtrInfoTable(AttributeName.PREFERRED, 0x33333333, AttributeInfo.EnumAttributeType.XYPair, null, null);
		atrInfoTable[2] = new AtrInfoTable(AttributeName.RANGE, 0x33333333, AttributeInfo.EnumAttributeType.XYPairRangeList, null, null);
		atrInfoTable[3] = new AtrInfoTable(AttributeName.OFFERRANGE, 0x33333111, AttributeInfo.EnumAttributeType.XYPairRangeList, null, null);
	}

	@Override
	protected AttributeInfo getTheAttributeInfo()
	{
		return super.getTheAttributeInfo().updateReplace(atrInfoTable);
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
		return "JDFXYPairSpan[ --> " + super.toString() + " ]";
	}

	public void setActual(JDFXYPair value)
	{
		setAttribute(AttributeName.ACTUAL, value, null);
	}

	public JDFXYPair getActual()
	{
		return JDFXYPair.createXYPair(getAttribute(AttributeName.ACTUAL));
	}

	public void setPreferred(JDFXYPair value)
	{
		setAttribute(AttributeName.PREFERRED, value, null);
	}

	public JDFXYPair getPreferred()
	{
		return JDFXYPair.createXYPair(getAttribute(AttributeName.PREFERRED));
	}

	/**
	 * Gets the value of attribute DataType
	 * 
	 * @return EnumDataType - the attribute DataType value
	 */
	@Override
	public EnumDataType getDataType()
	{
		return EnumDataType.XYPairSpan;
	}

	public void setRange(JDFXYPairRangeList value)
	{
		setAttribute(AttributeName.RANGE, value.toString());
	}

	public JDFXYPairRangeList getRange()
	{
		return JDFXYPairRangeList.createXYPairRangeList(getAttribute(AttributeName.RANGE));
	}

	@Override
	public boolean init()
	{
		boolean b = super.init();
		setDataType(JDFSpanBase.EnumDataType.XYPairSpan);
		return b;
	}

	/**
	 * SetPreferred
	 * 
	 * @param double x
	 * @param double y
	 */
	public void setPreferred(double x, double y)
	{
		setPreferred(new JDFXYPair(x, y));
	}

	/**
	 * add an element xy to the Range attribute
	 * 
	 * @param double x - x value of the JDFXYPair value to add
	 * @param double y - y value of the JDFXYPair value to add
	 */
	public void addRange(double x, double y)
	{
		addRange(new JDFXYPair(x, y));
	}

	/**
	 * add an element xy to the Range attribute
	 * 
	 * @param double x1 - x value of the left JDFXYPairRange value to add
	 * @param double y1 - y value of the left JDFXYPairRange value to add
	 * @param double x2 - x value of the right JDFXYPairRange value to add
	 * @param double y2 - y value of the right JDFXYPairRange value to add
	 */
	public void addRange(double x1, double y1, double x2, double y2)
	{
		addRange(new JDFXYPair(x1, y1), new JDFXYPair(x2, y2));
	}

	/**
	 * add an element xy to the Range attribute as a JDFRange
	 * 
	 * @param JDFXYPair xy - the Range value to add
	 */
	public void addRange(JDFXYPair xy)
	{
		JDFXYPairRangeList rl = getRange();
		rl.append(xy);
		setRange(rl);
	}

	/**
	 * add an element xy to the Range attribute as a JDFRange
	 * 
	 * @param JDFXYPair xy1 - left JDFXYPairRange value to add
	 * @param JDFXYPair xy2 - right JDFXYPairRange value to add
	 */
	public void addRange(JDFXYPair xy1, JDFXYPair xy2)
	{
		JDFXYPairRangeList rl = getRange();
		rl.append(xy1, xy2);
		setRange(rl);
	}
}
