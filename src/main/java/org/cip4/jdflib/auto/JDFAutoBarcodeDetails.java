/*
 * The CIP4 Software License, Version 1.0
 *
 *
 * Copyright (c) 2001-2018 The International Cooperation for the Integration of Processes in Prepress, Press and Postpress (CIP4). All rights reserved.
 *
 * Redistribution and use in source and binary forms, with or without modification, are permitted provided that the following conditions are met:
 *
 * 1. Redistributions of source code must retain the above copyright notice, this list of conditions and the following disclaimer.
 *
 * 2. Redistributions in binary form must reproduce the above copyright notice, this list of conditions and the following disclaimer in the documentation and/or other materials provided with the
 * distribution.
 *
 * 3. The end-user documentation included with the redistribution, if any, must include the following acknowledgment: "This product includes software developed by the The International Cooperation for
 * the Integration of Processes in Prepress, Press and Postpress (www.cip4.org)" Alternately, this acknowledgment may appear in the software itself, if and wherever such third-party acknowledgments
 * normally appear.
 *
 * 4. The names "CIP4" and "The International Cooperation for the Integration of Processes in Prepress, Press and Postpress" must not be used to endorse or promote products derived from this software
 * without prior written permission. For written permission, please contact info@cip4.org.
 *
 * 5. Products derived from this software may not be called "CIP4", nor may "CIP4" appear in their name, without prior written permission of the CIP4 organization
 *
 * Usage of this software in commercial products is subject to restrictions. For details please consult info@cip4.org.
 *
 * THIS SOFTWARE IS PROVIDED ``AS IS'' AND ANY EXPRESSED OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO, THE IMPLIED WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE ARE
 * DISCLAIMED. IN NO EVENT SHALL THE INTERNATIONAL COOPERATION FOR THE INTEGRATION OF PROCESSES IN PREPRESS, PRESS AND POSTPRESS OR ITS CONTRIBUTORS BE LIABLE FOR ANY DIRECT, INDIRECT, INCIDENTAL,
 * SPECIAL, EXEMPLARY, OR CONSEQUENTIAL DAMAGES (INCLUDING, BUT NOT LIMITED TO, PROCUREMENT OF SUBSTITUTE GOODS OR SERVICES; LOSS OF USE, DATA, OR PROFITS; OR BUSINESS INTERRUPTION) HOWEVER CAUSED AND
 * ON ANY THEORY OF LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY, OR TORT (INCLUDING NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE OF THIS SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY
 * OF SUCH DAMAGE. ====================================================================
 *
 * This software consists of voluntary contributions made by many individuals on behalf of the The International Cooperation for the Integration of Processes in Prepress, Press and Postpress and was
 * originally based on software copyright (c) 1999-2001, Heidelberger Druckmaschinen AG copyright (c) 1999-2001, Agfa-Gevaert N.V.
 *
 * For more information on The International Cooperation for the Integration of Processes in Prepress, Press and Postpress , please see <http://www.cip4.org/>.
 *
 *
 */

package org.cip4.jdflib.auto;

import org.apache.xerces.dom.CoreDocumentImpl;
import org.cip4.jdflib.core.AtrInfoTable;
import org.cip4.jdflib.core.AttributeInfo;
import org.cip4.jdflib.core.AttributeName;
import org.cip4.jdflib.core.JDFCoreConstants;
import org.cip4.jdflib.core.JDFElement;

/**
 *****************************************************************************
 * class JDFAutoBarcodeDetails : public JDFElement
 *****************************************************************************
 * 
 */

public abstract class JDFAutoBarcodeDetails extends JDFElement
{

	private static final long serialVersionUID = 1L;

	private static AtrInfoTable[] atrInfoTable = new AtrInfoTable[4];
	static
	{
		atrInfoTable[0] = new AtrInfoTable(AttributeName.BARCODEVERSION, 0x33333111, AttributeInfo.EnumAttributeType.NMTOKEN, null, null);
		atrInfoTable[1] = new AtrInfoTable(AttributeName.ERRORCORRECTIONLEVEL, 0x33333111, AttributeInfo.EnumAttributeType.NMTOKEN, null, null);
		atrInfoTable[2] = new AtrInfoTable(AttributeName.XCELLS, 0x33333111, AttributeInfo.EnumAttributeType.integer, null, null);
		atrInfoTable[3] = new AtrInfoTable(AttributeName.YCELLS, 0x33333111, AttributeInfo.EnumAttributeType.integer, null, null);
	}

	@Override
	protected AttributeInfo getTheAttributeInfo()
	{
		return super.getTheAttributeInfo().updateReplace(atrInfoTable);
	}

	/**
	 * Constructor for JDFAutoBarcodeDetails
	 * 
	 * @param myOwnerDocument
	 * @param qualifiedName
	 */
	protected JDFAutoBarcodeDetails(CoreDocumentImpl myOwnerDocument, String qualifiedName)
	{
		super(myOwnerDocument, qualifiedName);
	}

	/**
	 * Constructor for JDFAutoBarcodeDetails
	 * 
	 * @param myOwnerDocument
	 * @param myNamespaceURI
	 * @param qualifiedName
	 */
	protected JDFAutoBarcodeDetails(CoreDocumentImpl myOwnerDocument, String myNamespaceURI, String qualifiedName)
	{
		super(myOwnerDocument, myNamespaceURI, qualifiedName);
	}

	/**
	 * Constructor for JDFAutoBarcodeDetails
	 * 
	 * @param myOwnerDocument
	 * @param myNamespaceURI
	 * @param qualifiedName
	 * @param myLocalName
	 */
	protected JDFAutoBarcodeDetails(CoreDocumentImpl myOwnerDocument, String myNamespaceURI, String qualifiedName, String myLocalName)
	{
		super(myOwnerDocument, myNamespaceURI, qualifiedName, myLocalName);
	}

	/**
	 * @return the string representation
	 */
	@Override
	public String toString()
	{
		return " JDFAutoBarcodeDetails[  --> " + super.toString() + " ]";
	}

	/*
	 * ************************************************************************ Attribute getter / setter ************************************************************************
	 */

	/*
	 * --------------------------------------------------------------------- Methods for Attribute BarcodeVersion ---------------------------------------------------------------------
	 */
	/**
	 * (36) set attribute BarcodeVersion
	 * 
	 * @param value the value to set the attribute to
	 */
	public void setBarcodeVersion(String value)
	{
		setAttribute(AttributeName.BARCODEVERSION, value, null);
	}

	/**
	 * (23) get String attribute BarcodeVersion
	 * 
	 * @return the value of the attribute
	 */
	public String getBarcodeVersion()
	{
		return getAttribute(AttributeName.BARCODEVERSION, null, JDFCoreConstants.EMPTYSTRING);
	}

	/*
	 * --------------------------------------------------------------------- Methods for Attribute ErrorCorrectionLevel ---------------------------------------------------------------------
	 */
	/**
	 * (36) set attribute ErrorCorrectionLevel
	 * 
	 * @param value the value to set the attribute to
	 */
	public void setErrorCorrectionLevel(String value)
	{
		setAttribute(AttributeName.ERRORCORRECTIONLEVEL, value, null);
	}

	/**
	 * (23) get String attribute ErrorCorrectionLevel
	 * 
	 * @return the value of the attribute
	 */
	public String getErrorCorrectionLevel()
	{
		return getAttribute(AttributeName.ERRORCORRECTIONLEVEL, null, JDFCoreConstants.EMPTYSTRING);
	}

	/*
	 * --------------------------------------------------------------------- Methods for Attribute XCells ---------------------------------------------------------------------
	 */
	/**
	 * (36) set attribute XCells
	 * 
	 * @param value the value to set the attribute to
	 */
	public void setXCells(int value)
	{
		setAttribute(AttributeName.XCELLS, value, null);
	}

	/**
	 * (15) get int attribute XCells
	 * 
	 * @return int the value of the attribute
	 */
	public int getXCells()
	{
		return getIntAttribute(AttributeName.XCELLS, null, 0);
	}

	/*
	 * --------------------------------------------------------------------- Methods for Attribute YCells ---------------------------------------------------------------------
	 */
	/**
	 * (36) set attribute YCells
	 * 
	 * @param value the value to set the attribute to
	 */
	public void setYCells(int value)
	{
		setAttribute(AttributeName.YCELLS, value, null);
	}

	/**
	 * (15) get int attribute YCells
	 * 
	 * @return int the value of the attribute
	 */
	public int getYCells()
	{
		return getIntAttribute(AttributeName.YCELLS, null, 0);
	}

}// end namespace JDF
