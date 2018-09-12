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
import org.cip4.jdflib.core.ElemInfoTable;
import org.cip4.jdflib.core.ElementInfo;
import org.cip4.jdflib.core.ElementName;
import org.cip4.jdflib.core.JDFCoreConstants;
import org.cip4.jdflib.core.JDFElement;
import org.cip4.jdflib.core.JDFException;
import org.cip4.jdflib.core.VString;
import org.cip4.jdflib.resource.JDFDevice;

/**
 *****************************************************************************
 * class JDFAutoIDInfo : public JDFElement
 *****************************************************************************
 * 
 */

public abstract class JDFAutoIDInfo extends JDFElement
{

	private static final long serialVersionUID = 1L;

	private static AtrInfoTable[] atrInfoTable = new AtrInfoTable[9];
	static
	{
		atrInfoTable[0] = new AtrInfoTable(AttributeName.CATEGORY, 0x33333311, AttributeInfo.EnumAttributeType.NMTOKEN, null, null);
		atrInfoTable[1] = new AtrInfoTable(AttributeName.JDFURL, 0x33311111, AttributeInfo.EnumAttributeType.URL, null, null);
		atrInfoTable[2] = new AtrInfoTable(AttributeName.JOBID, 0x33333311, AttributeInfo.EnumAttributeType.shortString, null, null);
		atrInfoTable[3] = new AtrInfoTable(AttributeName.JOBPARTID, 0x33333311, AttributeInfo.EnumAttributeType.shortString, null, null);
		atrInfoTable[4] = new AtrInfoTable(AttributeName.PARENTJOBID, 0x33333311, AttributeInfo.EnumAttributeType.string, null, null);
		atrInfoTable[5] = new AtrInfoTable(AttributeName.PARENTJOBPARTID, 0x33333311, AttributeInfo.EnumAttributeType.string, null, null);
		atrInfoTable[6] = new AtrInfoTable(AttributeName.PROJECTID, 0x33311111, AttributeInfo.EnumAttributeType.string, null, null);
		atrInfoTable[7] = new AtrInfoTable(AttributeName.TYPE, 0x33333311, AttributeInfo.EnumAttributeType.NMTOKEN, null, null);
		atrInfoTable[8] = new AtrInfoTable(AttributeName.TYPES, 0x33333311, AttributeInfo.EnumAttributeType.NMTOKENS, null, null);
	}

	@Override
	protected AttributeInfo getTheAttributeInfo()
	{
		return super.getTheAttributeInfo().updateReplace(atrInfoTable);
	}

	private static ElemInfoTable[] elemInfoTable = new ElemInfoTable[1];
	static
	{
		elemInfoTable[0] = new ElemInfoTable(ElementName.DEVICE, 0x66666611);
	}

	@Override
	protected ElementInfo getTheElementInfo()
	{
		return super.getTheElementInfo().updateReplace(elemInfoTable);
	}

	/**
	 * Constructor for JDFAutoIDInfo
	 * 
	 * @param myOwnerDocument
	 * @param qualifiedName
	 */
	protected JDFAutoIDInfo(CoreDocumentImpl myOwnerDocument, String qualifiedName)
	{
		super(myOwnerDocument, qualifiedName);
	}

	/**
	 * Constructor for JDFAutoIDInfo
	 * 
	 * @param myOwnerDocument
	 * @param myNamespaceURI
	 * @param qualifiedName
	 */
	protected JDFAutoIDInfo(CoreDocumentImpl myOwnerDocument, String myNamespaceURI, String qualifiedName)
	{
		super(myOwnerDocument, myNamespaceURI, qualifiedName);
	}

	/**
	 * Constructor for JDFAutoIDInfo
	 * 
	 * @param myOwnerDocument
	 * @param myNamespaceURI
	 * @param qualifiedName
	 * @param myLocalName
	 */
	protected JDFAutoIDInfo(CoreDocumentImpl myOwnerDocument, String myNamespaceURI, String qualifiedName, String myLocalName)
	{
		super(myOwnerDocument, myNamespaceURI, qualifiedName, myLocalName);
	}

	/**
	 * @return the string representation
	 */
	@Override
	public String toString()
	{
		return " JDFAutoIDInfo[  --> " + super.toString() + " ]";
	}

	/*
	 * ************************************************************************ Attribute getter / setter ************************************************************************
	 */

	/*
	 * --------------------------------------------------------------------- Methods for Attribute Category ---------------------------------------------------------------------
	 */
	/**
	 * (36) set attribute Category
	 * 
	 * @param value the value to set the attribute to
	 */
	public void setCategory(String value)
	{
		setAttribute(AttributeName.CATEGORY, value, null);
	}

	/**
	 * (23) get String attribute Category
	 * 
	 * @return the value of the attribute
	 */
	public String getCategory()
	{
		return getAttribute(AttributeName.CATEGORY, null, JDFCoreConstants.EMPTYSTRING);
	}

	/*
	 * --------------------------------------------------------------------- Methods for Attribute JDFURL ---------------------------------------------------------------------
	 */
	/**
	 * (36) set attribute JDFURL
	 * 
	 * @param value the value to set the attribute to
	 */
	public void setJDFURL(String value)
	{
		setAttribute(AttributeName.JDFURL, value, null);
	}

	/**
	 * (23) get String attribute JDFURL
	 * 
	 * @return the value of the attribute
	 */
	public String getJDFURL()
	{
		return getAttribute(AttributeName.JDFURL, null, JDFCoreConstants.EMPTYSTRING);
	}

	/*
	 * --------------------------------------------------------------------- Methods for Attribute JobID ---------------------------------------------------------------------
	 */
	/**
	 * (36) set attribute JobID
	 * 
	 * @param value the value to set the attribute to
	 */
	public void setJobID(String value)
	{
		setAttribute(AttributeName.JOBID, value, null);
	}

	/**
	 * (23) get String attribute JobID
	 * 
	 * @return the value of the attribute
	 */
	public String getJobID()
	{
		return getAttribute(AttributeName.JOBID, null, JDFCoreConstants.EMPTYSTRING);
	}

	/*
	 * --------------------------------------------------------------------- Methods for Attribute JobPartID ---------------------------------------------------------------------
	 */
	/**
	 * (36) set attribute JobPartID
	 * 
	 * @param value the value to set the attribute to
	 */
	public void setJobPartID(String value)
	{
		setAttribute(AttributeName.JOBPARTID, value, null);
	}

	/**
	 * (23) get String attribute JobPartID
	 * 
	 * @return the value of the attribute
	 */
	public String getJobPartID()
	{
		return getAttribute(AttributeName.JOBPARTID, null, JDFCoreConstants.EMPTYSTRING);
	}

	/*
	 * --------------------------------------------------------------------- Methods for Attribute ParentJobID ---------------------------------------------------------------------
	 */
	/**
	 * (36) set attribute ParentJobID
	 * 
	 * @param value the value to set the attribute to
	 */
	public void setParentJobID(String value)
	{
		setAttribute(AttributeName.PARENTJOBID, value, null);
	}

	/**
	 * (23) get String attribute ParentJobID
	 * 
	 * @return the value of the attribute
	 */
	public String getParentJobID()
	{
		return getAttribute(AttributeName.PARENTJOBID, null, JDFCoreConstants.EMPTYSTRING);
	}

	/*
	 * --------------------------------------------------------------------- Methods for Attribute ParentJobPartID ---------------------------------------------------------------------
	 */
	/**
	 * (36) set attribute ParentJobPartID
	 * 
	 * @param value the value to set the attribute to
	 */
	public void setParentJobPartID(String value)
	{
		setAttribute(AttributeName.PARENTJOBPARTID, value, null);
	}

	/**
	 * (23) get String attribute ParentJobPartID
	 * 
	 * @return the value of the attribute
	 */
	public String getParentJobPartID()
	{
		return getAttribute(AttributeName.PARENTJOBPARTID, null, JDFCoreConstants.EMPTYSTRING);
	}

	/*
	 * --------------------------------------------------------------------- Methods for Attribute ProjectID ---------------------------------------------------------------------
	 */
	/**
	 * (36) set attribute ProjectID
	 * 
	 * @param value the value to set the attribute to
	 */
	public void setProjectID(String value)
	{
		setAttribute(AttributeName.PROJECTID, value, null);
	}

	/**
	 * (23) get String attribute ProjectID
	 * 
	 * @return the value of the attribute
	 */
	public String getProjectID()
	{
		return getAttribute(AttributeName.PROJECTID, null, JDFCoreConstants.EMPTYSTRING);
	}

	/*
	 * --------------------------------------------------------------------- Methods for Attribute Type ---------------------------------------------------------------------
	 */
	/**
	 * (36) set attribute Type
	 * 
	 * @param value the value to set the attribute to
	 */
	public void setType(String value)
	{
		setAttribute(AttributeName.TYPE, value, null);
	}

	/**
	 * (23) get String attribute Type
	 * 
	 * @return the value of the attribute
	 */
	public String getType()
	{
		return getAttribute(AttributeName.TYPE, null, JDFCoreConstants.EMPTYSTRING);
	}

	/*
	 * --------------------------------------------------------------------- Methods for Attribute Types ---------------------------------------------------------------------
	 */
	/**
	 * (36) set attribute Types
	 * 
	 * @param value the value to set the attribute to
	 */
	public void setTypes(VString value)
	{
		setAttribute(AttributeName.TYPES, value, null);
	}

	/**
	 * (21) get VString attribute Types
	 * 
	 * @return VString the value of the attribute
	 */
	public VString getTypes()
	{
		final VString vStrAttrib = new VString();
		final String s = getAttribute(AttributeName.TYPES, null, JDFCoreConstants.EMPTYSTRING);
		vStrAttrib.setAllStrings(s, " ");
		return vStrAttrib;
	}

	/*
	 * *********************************************************************** Element getter / setter ***********************************************************************
	 */

	/**
	 * (24) const get element Device
	 * 
	 * @return JDFDevice the element
	 */
	public JDFDevice getDevice()
	{
		return (JDFDevice) getElement(ElementName.DEVICE, null, 0);
	}

	/**
	 * (25) getCreateDevice
	 * 
	 * @return JDFDevice the element
	 */
	public JDFDevice getCreateDevice()
	{
		return (JDFDevice) getCreateElement_JDFElement(ElementName.DEVICE, null, 0);
	}

	/**
	 * (29) append element Device
	 * 
	 * @return JDFDevice the element
	 * @throws JDFException if the element already exists
	 */
	public JDFDevice appendDevice() throws JDFException
	{
		return (JDFDevice) appendElementN(ElementName.DEVICE, 1, null);
	}

}// end namespace JDF
