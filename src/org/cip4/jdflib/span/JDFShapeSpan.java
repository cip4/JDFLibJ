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
 * JDFShapeSpan.java
 *
 */
package org.cip4.jdflib.span;

import java.util.zip.DataFormatException;

import org.apache.xerces.dom.CoreDocumentImpl;
import org.cip4.jdflib.core.AtrInfoTable;
import org.cip4.jdflib.core.AttributeInfo;
import org.cip4.jdflib.core.AttributeName;
import org.cip4.jdflib.core.JDFException;
import org.cip4.jdflib.datatypes.JDFShape;
import org.cip4.jdflib.datatypes.JDFShapeRangeList;

public class JDFShapeSpan extends JDFSpanBase
{
	private static final long serialVersionUID = 1L;

	/**
	 * Constructor for JDFShapeSpan
	 * 
	 * @param ownerDocument
	 * @param qualifiedName
	 */
	public JDFShapeSpan(CoreDocumentImpl myOwnerDocument, String qualifiedName)
	{
		super(myOwnerDocument, qualifiedName);
	}

	/**
	 * Constructor for JDFShapeSpan
	 * 
	 * @param ownerDocument
	 * @param namespaceURI
	 * @param qualifiedName
	 */
	public JDFShapeSpan(CoreDocumentImpl myOwnerDocument, String myNamespaceURI, String qualifiedName)
	{
		super(myOwnerDocument, myNamespaceURI, qualifiedName);
	}

	/**
	 * Constructor for JDFShapeSpan
	 * 
	 * @param ownerDocument
	 * @param namespaceURI
	 * @param qualifiedName
	 * @param localName
	 */
	public JDFShapeSpan(CoreDocumentImpl myOwnerDocument, String myNamespaceURI, String qualifiedName, String myLocalName)
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
		return "JDFShapeSpan[ --> " + super.toString() + " ]";
	}

	private static AtrInfoTable[] atrInfoTable = new AtrInfoTable[4];
	static
	{
		atrInfoTable[0] = new AtrInfoTable(AttributeName.ACTUAL, 0x33333333, AttributeInfo.EnumAttributeType.shape, null, null);
		atrInfoTable[1] = new AtrInfoTable(AttributeName.PREFERRED, 0x33333333, AttributeInfo.EnumAttributeType.shape, null, null);
		atrInfoTable[2] = new AtrInfoTable(AttributeName.RANGE, 0x33333333, AttributeInfo.EnumAttributeType.shape, null, null);
		atrInfoTable[3] = new AtrInfoTable(AttributeName.OFFERRANGE, 0x33333111, AttributeInfo.EnumAttributeType.shape, null, null);
	}

	protected AttributeInfo getTheAttributeInfo()
	{
		return super.getTheAttributeInfo().updateReplace(atrInfoTable);
	}

	public void setActual(JDFShape value)
	{
		setAttribute(AttributeName.ACTUAL, value.toString(), null);
	}

	public JDFShape getActual()
	{
		try
		{
			return new JDFShape(getAttribute(AttributeName.ACTUAL));
		}
		catch (DataFormatException e)
		{
			throw new JDFException("JDFShapeState.getActual: Attribute Actual is not capable to create JDFShape");
		}
	}

	public void setPreferred(JDFShape value)
	{
		setAttribute(AttributeName.PREFERRED, value.toString(), null);
	}

	public JDFShape getPreferred()
	{
		try
		{
			return new JDFShape(getAttribute(AttributeName.PREFERRED));
		}
		catch (DataFormatException e)
		{
			throw new JDFException("JDFShapeState.getPreferred: Attribute Preferred is not capable to create JDFShape");
		}
	}

	public void setRange(JDFShapeRangeList value)
	{
		setAttribute(AttributeName.RANGE, value.toString());
	}

	public JDFShapeRangeList getRange()
	{
		try
		{
			return new JDFShapeRangeList(getAttribute(AttributeName.RANGE));
		}
		catch (DataFormatException e)
		{
			throw new JDFException("JDFShapeState.getRange: Attribute Range is not capable to create JDFShapeRangeList");
		}
	}

	public boolean init()
	{
		boolean b = super.init();
		setDataType(EnumDataType.ShapeSpan);
		return b;
	}

	/**
	 * set the Preferred attribute
	 * 
	 * @param double x the preferred width
	 * @param double y the preferred height
	 * @param double z the preferred depth
	 */
	public void setPreferred(double x, double y, double z)
	{
		setPreferred(new JDFShape(x, y, z));
	}

	/**
	 * add an element shape to the Range attribute as a JDFRange
	 * 
	 * @param JDFShape shape the Range value
	 */
	public void addRange(JDFShape shape)
	{
		JDFShapeRangeList srl = getRange();
		srl.append(shape);
		setRange(srl);
	}

	/**
	 * add an element x y to the Range attribute as a JDFRange
	 * 
	 * @param JDFShape shape1 the Range value
	 * @param JDFShape shape2 the Range value
	 */
	public void addRange(JDFShape shape1, JDFShape shape2)
	{
		JDFShapeRangeList srl = getRange();
		srl.append(shape1, shape2);
		setRange(srl);
	}

	/**
	 * add an element x y zto the Range attribute as a JDFRange
	 * 
	 * @param double x the width
	 * @param double y the height
	 * @param double z the depth
	 */
	public void addRange(double x, double y, double z)
	{
		JDFShapeRangeList srl = getRange();
		srl.append(new JDFShape(x, y, z));
		setRange(srl);
	}

	/**
	 * add an element x1 y1 z1~ x2 y2 z2to the Range attribute as a JDFRange
	 * 
	 * @param double x1 the 1. width
	 * @param double y1 the 1. height
	 * @param double z1 the 1. depth
	 * @param double x2 the 2. width
	 * @param double y2 the 2. height
	 * @param double z2 the 2. depth
	 */
	public void addRange(double x1, double y1, double z1, double x2, double y2, double z2)
	{
		JDFShapeRangeList srl = getRange();
		srl.append(new JDFShape(x1, y1, z1), new JDFShape(x2, y2, z2));
		setRange(srl);
	}
}
