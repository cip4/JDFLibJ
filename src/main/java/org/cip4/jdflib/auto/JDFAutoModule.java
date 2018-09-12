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

import java.util.Collection;

import org.apache.xerces.dom.CoreDocumentImpl;
import org.cip4.jdflib.core.AtrInfoTable;
import org.cip4.jdflib.core.AttributeInfo;
import org.cip4.jdflib.core.AttributeName;
import org.cip4.jdflib.core.ElemInfoTable;
import org.cip4.jdflib.core.ElementInfo;
import org.cip4.jdflib.core.ElementName;
import org.cip4.jdflib.core.JDFCoreConstants;
import org.cip4.jdflib.core.JDFElement;
import org.cip4.jdflib.resource.devicecapability.JDFModule;

/**
 *****************************************************************************
 * class JDFAutoModule : public JDFElement
 *****************************************************************************
 * 
 */

public abstract class JDFAutoModule extends JDFElement
{

	private static final long serialVersionUID = 1L;

	private static AtrInfoTable[] atrInfoTable = new AtrInfoTable[12];
	static
	{
		atrInfoTable[0] = new AtrInfoTable(AttributeName.DEVICETYPE, 0x33333111, AttributeInfo.EnumAttributeType.string, null, null);
		atrInfoTable[1] = new AtrInfoTable(AttributeName.MANUFACTURER, 0x33333111, AttributeInfo.EnumAttributeType.string, null, null);
		atrInfoTable[2] = new AtrInfoTable(AttributeName.MANUFACTURERURL, 0x33333111, AttributeInfo.EnumAttributeType.string, null, null);
		atrInfoTable[3] = new AtrInfoTable(AttributeName.MODELDESCRIPTION, 0x33333111, AttributeInfo.EnumAttributeType.string, null, null);
		atrInfoTable[4] = new AtrInfoTable(AttributeName.MODELNAME, 0x33333111, AttributeInfo.EnumAttributeType.string, null, null);
		atrInfoTable[5] = new AtrInfoTable(AttributeName.MODELNUMBER, 0x33333111, AttributeInfo.EnumAttributeType.string, null, null);
		atrInfoTable[6] = new AtrInfoTable(AttributeName.MODELURL, 0x33333111, AttributeInfo.EnumAttributeType.string, null, null);
		atrInfoTable[7] = new AtrInfoTable(AttributeName.MODULEID, 0x33333111, AttributeInfo.EnumAttributeType.string, null, null);
		atrInfoTable[8] = new AtrInfoTable(AttributeName.MODULEINDEX, 0x33333111, AttributeInfo.EnumAttributeType.integer, null, null);
		atrInfoTable[9] = new AtrInfoTable(AttributeName.MODULETYPE, 0x33333111, AttributeInfo.EnumAttributeType.NMTOKEN, null, null);
		atrInfoTable[10] = new AtrInfoTable(AttributeName.SERIALNUMBER, 0x33333111, AttributeInfo.EnumAttributeType.string, null, null);
		atrInfoTable[11] = new AtrInfoTable(AttributeName.SUBMODULEINDEX, 0x33333111, AttributeInfo.EnumAttributeType.integer, null, null);
	}

	@Override
	protected AttributeInfo getTheAttributeInfo()
	{
		return super.getTheAttributeInfo().updateReplace(atrInfoTable);
	}

	private static ElemInfoTable[] elemInfoTable = new ElemInfoTable[1];
	static
	{
		elemInfoTable[0] = new ElemInfoTable(ElementName.MODULE, 0x33333111);
	}

	@Override
	protected ElementInfo getTheElementInfo()
	{
		return super.getTheElementInfo().updateReplace(elemInfoTable);
	}

	/**
	 * Constructor for JDFAutoModule
	 * 
	 * @param myOwnerDocument
	 * @param qualifiedName
	 */
	protected JDFAutoModule(CoreDocumentImpl myOwnerDocument, String qualifiedName)
	{
		super(myOwnerDocument, qualifiedName);
	}

	/**
	 * Constructor for JDFAutoModule
	 * 
	 * @param myOwnerDocument
	 * @param myNamespaceURI
	 * @param qualifiedName
	 */
	protected JDFAutoModule(CoreDocumentImpl myOwnerDocument, String myNamespaceURI, String qualifiedName)
	{
		super(myOwnerDocument, myNamespaceURI, qualifiedName);
	}

	/**
	 * Constructor for JDFAutoModule
	 * 
	 * @param myOwnerDocument
	 * @param myNamespaceURI
	 * @param qualifiedName
	 * @param myLocalName
	 */
	protected JDFAutoModule(CoreDocumentImpl myOwnerDocument, String myNamespaceURI, String qualifiedName, String myLocalName)
	{
		super(myOwnerDocument, myNamespaceURI, qualifiedName, myLocalName);
	}

	/**
	 * @return the string representation
	 */
	@Override
	public String toString()
	{
		return " JDFAutoModule[  --> " + super.toString() + " ]";
	}

	/*
	 * ************************************************************************ Attribute getter / setter ************************************************************************
	 */

	/*
	 * --------------------------------------------------------------------- Methods for Attribute DeviceType ---------------------------------------------------------------------
	 */
	/**
	 * (36) set attribute DeviceType
	 * 
	 * @param value the value to set the attribute to
	 */
	public void setDeviceType(String value)
	{
		setAttribute(AttributeName.DEVICETYPE, value, null);
	}

	/**
	 * (23) get String attribute DeviceType
	 * 
	 * @return the value of the attribute
	 */
	public String getDeviceType()
	{
		return getAttribute(AttributeName.DEVICETYPE, null, JDFCoreConstants.EMPTYSTRING);
	}

	/*
	 * --------------------------------------------------------------------- Methods for Attribute Manufacturer ---------------------------------------------------------------------
	 */
	/**
	 * (36) set attribute Manufacturer
	 * 
	 * @param value the value to set the attribute to
	 */
	public void setManufacturer(String value)
	{
		setAttribute(AttributeName.MANUFACTURER, value, null);
	}

	/**
	 * (23) get String attribute Manufacturer
	 * 
	 * @return the value of the attribute
	 */
	public String getManufacturer()
	{
		return getAttribute(AttributeName.MANUFACTURER, null, JDFCoreConstants.EMPTYSTRING);
	}

	/*
	 * --------------------------------------------------------------------- Methods for Attribute ManufacturerURL ---------------------------------------------------------------------
	 */
	/**
	 * (36) set attribute ManufacturerURL
	 * 
	 * @param value the value to set the attribute to
	 */
	public void setManufacturerURL(String value)
	{
		setAttribute(AttributeName.MANUFACTURERURL, value, null);
	}

	/**
	 * (23) get String attribute ManufacturerURL
	 * 
	 * @return the value of the attribute
	 */
	public String getManufacturerURL()
	{
		return getAttribute(AttributeName.MANUFACTURERURL, null, JDFCoreConstants.EMPTYSTRING);
	}

	/*
	 * --------------------------------------------------------------------- Methods for Attribute ModelDescription ---------------------------------------------------------------------
	 */
	/**
	 * (36) set attribute ModelDescription
	 * 
	 * @param value the value to set the attribute to
	 */
	public void setModelDescription(String value)
	{
		setAttribute(AttributeName.MODELDESCRIPTION, value, null);
	}

	/**
	 * (23) get String attribute ModelDescription
	 * 
	 * @return the value of the attribute
	 */
	public String getModelDescription()
	{
		return getAttribute(AttributeName.MODELDESCRIPTION, null, JDFCoreConstants.EMPTYSTRING);
	}

	/*
	 * --------------------------------------------------------------------- Methods for Attribute ModelName ---------------------------------------------------------------------
	 */
	/**
	 * (36) set attribute ModelName
	 * 
	 * @param value the value to set the attribute to
	 */
	public void setModelName(String value)
	{
		setAttribute(AttributeName.MODELNAME, value, null);
	}

	/**
	 * (23) get String attribute ModelName
	 * 
	 * @return the value of the attribute
	 */
	public String getModelName()
	{
		return getAttribute(AttributeName.MODELNAME, null, JDFCoreConstants.EMPTYSTRING);
	}

	/*
	 * --------------------------------------------------------------------- Methods for Attribute ModelNumber ---------------------------------------------------------------------
	 */
	/**
	 * (36) set attribute ModelNumber
	 * 
	 * @param value the value to set the attribute to
	 */
	public void setModelNumber(String value)
	{
		setAttribute(AttributeName.MODELNUMBER, value, null);
	}

	/**
	 * (23) get String attribute ModelNumber
	 * 
	 * @return the value of the attribute
	 */
	public String getModelNumber()
	{
		return getAttribute(AttributeName.MODELNUMBER, null, JDFCoreConstants.EMPTYSTRING);
	}

	/*
	 * --------------------------------------------------------------------- Methods for Attribute ModelURL ---------------------------------------------------------------------
	 */
	/**
	 * (36) set attribute ModelURL
	 * 
	 * @param value the value to set the attribute to
	 */
	public void setModelURL(String value)
	{
		setAttribute(AttributeName.MODELURL, value, null);
	}

	/**
	 * (23) get String attribute ModelURL
	 * 
	 * @return the value of the attribute
	 */
	public String getModelURL()
	{
		return getAttribute(AttributeName.MODELURL, null, JDFCoreConstants.EMPTYSTRING);
	}

	/*
	 * --------------------------------------------------------------------- Methods for Attribute ModuleID ---------------------------------------------------------------------
	 */
	/**
	 * (36) set attribute ModuleID
	 * 
	 * @param value the value to set the attribute to
	 */
	public void setModuleID(String value)
	{
		setAttribute(AttributeName.MODULEID, value, null);
	}

	/**
	 * (23) get String attribute ModuleID
	 * 
	 * @return the value of the attribute
	 */
	public String getModuleID()
	{
		return getAttribute(AttributeName.MODULEID, null, JDFCoreConstants.EMPTYSTRING);
	}

	/*
	 * --------------------------------------------------------------------- Methods for Attribute ModuleIndex ---------------------------------------------------------------------
	 */
	/**
	 * (36) set attribute ModuleIndex
	 * 
	 * @param value the value to set the attribute to
	 */
	public void setModuleIndex(int value)
	{
		setAttribute(AttributeName.MODULEINDEX, value, null);
	}

	/**
	 * (15) get int attribute ModuleIndex
	 * 
	 * @return int the value of the attribute
	 */
	public int getModuleIndex()
	{
		return getIntAttribute(AttributeName.MODULEINDEX, null, 0);
	}

	/*
	 * --------------------------------------------------------------------- Methods for Attribute ModuleType ---------------------------------------------------------------------
	 */
	/**
	 * (36) set attribute ModuleType
	 * 
	 * @param value the value to set the attribute to
	 */
	public void setModuleType(String value)
	{
		setAttribute(AttributeName.MODULETYPE, value, null);
	}

	/**
	 * (23) get String attribute ModuleType
	 * 
	 * @return the value of the attribute
	 */
	public String getModuleType()
	{
		return getAttribute(AttributeName.MODULETYPE, null, JDFCoreConstants.EMPTYSTRING);
	}

	/*
	 * --------------------------------------------------------------------- Methods for Attribute SerialNumber ---------------------------------------------------------------------
	 */
	/**
	 * (36) set attribute SerialNumber
	 * 
	 * @param value the value to set the attribute to
	 */
	public void setSerialNumber(String value)
	{
		setAttribute(AttributeName.SERIALNUMBER, value, null);
	}

	/**
	 * (23) get String attribute SerialNumber
	 * 
	 * @return the value of the attribute
	 */
	public String getSerialNumber()
	{
		return getAttribute(AttributeName.SERIALNUMBER, null, JDFCoreConstants.EMPTYSTRING);
	}

	/*
	 * --------------------------------------------------------------------- Methods for Attribute SubModuleIndex ---------------------------------------------------------------------
	 */
	/**
	 * (36) set attribute SubModuleIndex
	 * 
	 * @param value the value to set the attribute to
	 */
	public void setSubModuleIndex(int value)
	{
		setAttribute(AttributeName.SUBMODULEINDEX, value, null);
	}

	/**
	 * (15) get int attribute SubModuleIndex
	 * 
	 * @return int the value of the attribute
	 */
	public int getSubModuleIndex()
	{
		return getIntAttribute(AttributeName.SUBMODULEINDEX, null, 0);
	}

	/*
	 * *********************************************************************** Element getter / setter ***********************************************************************
	 */

	/**
	 * (26) getCreateModule
	 * 
	 * @param iSkip number of elements to skip
	 * @return JDFModule the element
	 */
	public JDFModule getCreateModule(int iSkip)
	{
		return (JDFModule) getCreateElement(ElementName.MODULE, null, iSkip);
	}

	/**
	 * (27) const get element Module
	 * 
	 * @param iSkip number of elements to skip
	 * @return JDFModule the element default is getModule(0)
	 */
	public JDFModule getModule(int iSkip)
	{
		return (JDFModule) getElement(ElementName.MODULE, null, iSkip);
	}

	/**
	 * Get all Module from the current element
	 * 
	 * @return Collection<JDFModule>, null if none are available
	 */
	public Collection<JDFModule> getAllModule()
	{
		return getChildrenByClass(JDFModule.class, false, 0);
	}

	/**
	 * (30) append element Module
	 * 
	 * @return JDFModule the element
	 */
	public JDFModule appendModule()
	{
		return (JDFModule) appendElement(ElementName.MODULE, null);
	}

}// end namespace JDF
