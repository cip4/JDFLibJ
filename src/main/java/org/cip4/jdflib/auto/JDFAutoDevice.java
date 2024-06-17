/*
 * The CIP4 Software License, Version 1.0
 *
 *
 * Copyright (c) 2001-2024 The International Cooperation for the Integration of
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
import org.cip4.jdflib.core.VString;
import org.cip4.jdflib.resource.JDFIconList;
import org.cip4.jdflib.resource.JDFResource;
import org.cip4.jdflib.resource.devicecapability.JDFDeviceCap;
import org.cip4.jdflib.resource.devicecapability.JDFModule;
import org.cip4.jdflib.resource.process.JDFCostCenter;

/**
 *****************************************************************************
 * class JDFAutoDevice : public JDFResource
 *****************************************************************************
 * 
 */

public abstract class JDFAutoDevice extends JDFResource
{

	private static final long serialVersionUID = 1L;

	private static AtrInfoTable[] atrInfoTable = new AtrInfoTable[26];
	static
	{
		atrInfoTable[0] = new AtrInfoTable(AttributeName.DEVICECLASS, 0x3333311111l, AttributeInfo.EnumAttributeType.NMTOKENS, null, null);
		atrInfoTable[1] = new AtrInfoTable(AttributeName.DEVICEFAMILY, 0x4444444443l, AttributeInfo.EnumAttributeType.string, null, null);
		atrInfoTable[2] = new AtrInfoTable(AttributeName.DEVICEID, 0x3333333331l, AttributeInfo.EnumAttributeType.string, null, null);
		atrInfoTable[3] = new AtrInfoTable(AttributeName.DEVICETYPE, 0x3333333331l, AttributeInfo.EnumAttributeType.string, null, null);
		atrInfoTable[4] = new AtrInfoTable(AttributeName.DIRECTORY, 0x3333333331l, AttributeInfo.EnumAttributeType.URL, null, null);
		atrInfoTable[5] = new AtrInfoTable(AttributeName.FRIENDLYNAME, 0x4444443331l, AttributeInfo.EnumAttributeType.string, null, null);
		atrInfoTable[6] = new AtrInfoTable(AttributeName.ICSVERSIONS, 0x3333333111l, AttributeInfo.EnumAttributeType.NMTOKENS, null, null);
		atrInfoTable[7] = new AtrInfoTable(AttributeName.JDFERRORURL, 0x3333333311l, AttributeInfo.EnumAttributeType.URL, null, null);
		atrInfoTable[8] = new AtrInfoTable(AttributeName.JDFINPUTURL, 0x3333333311l, AttributeInfo.EnumAttributeType.URL, null, null);
		atrInfoTable[9] = new AtrInfoTable(AttributeName.JDFOUTPUTURL, 0x3333333311l, AttributeInfo.EnumAttributeType.URL, null, null);
		atrInfoTable[10] = new AtrInfoTable(AttributeName.JDFVERSIONS, 0x3333333331l, AttributeInfo.EnumAttributeType.string, null, null);
		atrInfoTable[11] = new AtrInfoTable(AttributeName.JMFSENDERID, 0x3333333331l, AttributeInfo.EnumAttributeType.shortString, null, null);
		atrInfoTable[12] = new AtrInfoTable(AttributeName.JMFURL, 0x3333333331l, AttributeInfo.EnumAttributeType.URL, null, null);
		atrInfoTable[13] = new AtrInfoTable(AttributeName.KNOWNLOCALIZATIONS, 0x3333333311l, AttributeInfo.EnumAttributeType.languages, null, null);
		atrInfoTable[14] = new AtrInfoTable(AttributeName.MANUFACTURER, 0x3333333331l, AttributeInfo.EnumAttributeType.string, null, null);
		atrInfoTable[15] = new AtrInfoTable(AttributeName.MANUFACTURERURL, 0x3333333331l, AttributeInfo.EnumAttributeType.string, null, null);
		atrInfoTable[16] = new AtrInfoTable(AttributeName.MAXRUNSPEED, 0x3331111111l, AttributeInfo.EnumAttributeType.double_, null, null);
		atrInfoTable[17] = new AtrInfoTable(AttributeName.MODELDESCRIPTION, 0x3333333331l, AttributeInfo.EnumAttributeType.string, null, null);
		atrInfoTable[18] = new AtrInfoTable(AttributeName.MODELNAME, 0x3333333331l, AttributeInfo.EnumAttributeType.string, null, null);
		atrInfoTable[19] = new AtrInfoTable(AttributeName.MODELNUMBER, 0x3333333331l, AttributeInfo.EnumAttributeType.string, null, null);
		atrInfoTable[20] = new AtrInfoTable(AttributeName.MODELURL, 0x3333333331l, AttributeInfo.EnumAttributeType.string, null, null);
		atrInfoTable[21] = new AtrInfoTable(AttributeName.PRESENTATIONURL, 0x3333333331l, AttributeInfo.EnumAttributeType.string, null, null);
		atrInfoTable[22] = new AtrInfoTable(AttributeName.REVISION, 0x3333111111l, AttributeInfo.EnumAttributeType.string, null, null);
		atrInfoTable[23] = new AtrInfoTable(AttributeName.SERIALNUMBER, 0x3333333331l, AttributeInfo.EnumAttributeType.string, null, null);
		atrInfoTable[24] = new AtrInfoTable(AttributeName.SECUREJMFURL, 0x3333333111l, AttributeInfo.EnumAttributeType.URL, null, null);
		atrInfoTable[25] = new AtrInfoTable(AttributeName.UPC, 0x3333333331l, AttributeInfo.EnumAttributeType.string, null, null);
	}

	@Override
	protected AttributeInfo getTheAttributeInfo()
	{
		return super.getTheAttributeInfo().updateReplace(atrInfoTable);
	}

	private static ElemInfoTable[] elemInfoTable = new ElemInfoTable[4];
	static
	{
		elemInfoTable[0] = new ElemInfoTable(ElementName.COSTCENTER, 0x6666666661l);
		elemInfoTable[1] = new ElemInfoTable(ElementName.DEVICECAP, 0x3333333331l);
		elemInfoTable[2] = new ElemInfoTable(ElementName.ICONLIST, 0x6666666661l);
		elemInfoTable[3] = new ElemInfoTable(ElementName.MODULE, 0x3333333111l);
	}

	@Override
	protected ElementInfo getTheElementInfo()
	{
		return super.getTheElementInfo().updateReplace(elemInfoTable);
	}

	/**
	 * Constructor for JDFAutoDevice
	 *
	 * @param myOwnerDocument
	 * @param qualifiedName
	 */
	protected JDFAutoDevice(CoreDocumentImpl myOwnerDocument, String qualifiedName)
	{
		super(myOwnerDocument, qualifiedName);
	}

	/**
	 * Constructor for JDFAutoDevice
	 *
	 * @param myOwnerDocument
	 * @param myNamespaceURI
	 * @param qualifiedName
	 */
	protected JDFAutoDevice(CoreDocumentImpl myOwnerDocument, String myNamespaceURI, String qualifiedName)
	{
		super(myOwnerDocument, myNamespaceURI, qualifiedName);
	}

	/**
	 * Constructor for JDFAutoDevice
	 *
	 * @param myOwnerDocument
	 * @param myNamespaceURI
	 * @param qualifiedName
	 * @param myLocalName
	 */
	protected JDFAutoDevice(CoreDocumentImpl myOwnerDocument, String myNamespaceURI, String qualifiedName, String myLocalName)
	{
		super(myOwnerDocument, myNamespaceURI, qualifiedName, myLocalName);
	}

	/**
	 * @return true if ok
	 */
	@Override
	public boolean init()
	{
		final boolean bRet = super.init();
		setResourceClass(JDFResource.EnumResourceClass.Implementation);
		return bRet;
	}

	/**
	 * @return the resource Class
	 */
	@Override
	public EnumResourceClass getValidClass()
	{
		return JDFResource.EnumResourceClass.Implementation;
	}

	/*
	 * ************************************************************************ Attribute getter / setter ************************************************************************
	 */

	/*
	 * --------------------------------------------------------------------- Methods for Attribute DeviceClass ---------------------------------------------------------------------
	 */
	/**
	 * (36) set attribute DeviceClass
	 *
	 * @param value the value to set the attribute to
	 */
	public void setDeviceClass(VString value)
	{
		setAttribute(AttributeName.DEVICECLASS, value, null);
	}

	/**
	 * (21) get VString attribute DeviceClass
	 *
	 * @return VString the value of the attribute
	 */
	public VString getDeviceClass()
	{
		final VString vStrAttrib = new VString();
		final String s = getAttribute(AttributeName.DEVICECLASS, null, JDFCoreConstants.EMPTYSTRING);
		vStrAttrib.setAllStrings(s, " ");
		return vStrAttrib;
	}

	/*
	 * --------------------------------------------------------------------- Methods for Attribute DeviceFamily
	 * ---------------------------------------------------------------------
	 */
	/**
	 * (36) set attribute DeviceFamily
	 *
	 * @param value the value to set the attribute to
	 */
	public void setDeviceFamily(String value)
	{
		setAttribute(AttributeName.DEVICEFAMILY, value, null);
	}

	/**
	 * (23) get String attribute DeviceFamily
	 *
	 * @return the value of the attribute
	 */
	public String getDeviceFamily()
	{
		return getAttribute(AttributeName.DEVICEFAMILY, null, JDFCoreConstants.EMPTYSTRING);
	}

	/*
	 * --------------------------------------------------------------------- Methods for Attribute DeviceID ---------------------------------------------------------------------
	 */
	/**
	 * (36) set attribute DeviceID
	 *
	 * @param value the value to set the attribute to
	 */
	public void setDeviceID(String value)
	{
		setAttribute(AttributeName.DEVICEID, value, null);
	}

	/**
	 * (23) get String attribute DeviceID
	 *
	 * @return the value of the attribute
	 */
	public String getDeviceID()
	{
		return getAttribute(AttributeName.DEVICEID, null, JDFCoreConstants.EMPTYSTRING);
	}

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
	 * --------------------------------------------------------------------- Methods for Attribute Directory ---------------------------------------------------------------------
	 */
	/**
	 * (36) set attribute Directory
	 *
	 * @param value the value to set the attribute to
	 */
	public void setDirectory(String value)
	{
		setAttribute(AttributeName.DIRECTORY, value, null);
	}

	/**
	 * (23) get String attribute Directory
	 *
	 * @return the value of the attribute
	 */
	public String getDirectory()
	{
		return getAttribute(AttributeName.DIRECTORY, null, JDFCoreConstants.EMPTYSTRING);
	}

	/*
	 * --------------------------------------------------------------------- Methods for Attribute FriendlyName
	 * ---------------------------------------------------------------------
	 */
	/**
	 * (36) set attribute FriendlyName
	 *
	 * @param value the value to set the attribute to
	 */
	public void setFriendlyName(String value)
	{
		setAttribute(AttributeName.FRIENDLYNAME, value, null);
	}

	/**
	 * (23) get String attribute FriendlyName
	 *
	 * @return the value of the attribute
	 */
	public String getFriendlyName()
	{
		return getAttribute(AttributeName.FRIENDLYNAME, null, JDFCoreConstants.EMPTYSTRING);
	}

	/*
	 * --------------------------------------------------------------------- Methods for Attribute ICSVersions ---------------------------------------------------------------------
	 */
	/**
	 * (36) set attribute ICSVersions
	 *
	 * @param value the value to set the attribute to
	 */
	public void setICSVersions(VString value)
	{
		setAttribute(AttributeName.ICSVERSIONS, value, null);
	}

	/**
	 * (21) get VString attribute ICSVersions
	 *
	 * @return VString the value of the attribute
	 */
	public VString getICSVersions()
	{
		final VString vStrAttrib = new VString();
		final String s = getAttribute(AttributeName.ICSVERSIONS, null, JDFCoreConstants.EMPTYSTRING);
		vStrAttrib.setAllStrings(s, " ");
		return vStrAttrib;
	}

	/*
	 * --------------------------------------------------------------------- Methods for Attribute JDFErrorURL ---------------------------------------------------------------------
	 */
	/**
	 * (36) set attribute JDFErrorURL
	 *
	 * @param value the value to set the attribute to
	 */
	public void setJDFErrorURL(String value)
	{
		setAttribute(AttributeName.JDFERRORURL, value, null);
	}

	/**
	 * (23) get String attribute JDFErrorURL
	 *
	 * @return the value of the attribute
	 */
	public String getJDFErrorURL()
	{
		return getAttribute(AttributeName.JDFERRORURL, null, JDFCoreConstants.EMPTYSTRING);
	}

	/*
	 * --------------------------------------------------------------------- Methods for Attribute JDFInputURL ---------------------------------------------------------------------
	 */
	/**
	 * (36) set attribute JDFInputURL
	 *
	 * @param value the value to set the attribute to
	 */
	public void setJDFInputURL(String value)
	{
		setAttribute(AttributeName.JDFINPUTURL, value, null);
	}

	/**
	 * (23) get String attribute JDFInputURL
	 *
	 * @return the value of the attribute
	 */
	public String getJDFInputURL()
	{
		return getAttribute(AttributeName.JDFINPUTURL, null, JDFCoreConstants.EMPTYSTRING);
	}

	/*
	 * --------------------------------------------------------------------- Methods for Attribute JDFOutputURL
	 * ---------------------------------------------------------------------
	 */
	/**
	 * (36) set attribute JDFOutputURL
	 *
	 * @param value the value to set the attribute to
	 */
	public void setJDFOutputURL(String value)
	{
		setAttribute(AttributeName.JDFOUTPUTURL, value, null);
	}

	/**
	 * (23) get String attribute JDFOutputURL
	 *
	 * @return the value of the attribute
	 */
	public String getJDFOutputURL()
	{
		return getAttribute(AttributeName.JDFOUTPUTURL, null, JDFCoreConstants.EMPTYSTRING);
	}

	/*
	 * --------------------------------------------------------------------- Methods for Attribute JDFVersions ---------------------------------------------------------------------
	 */
	/**
	 * (36) set attribute JDFVersions
	 *
	 * @param value the value to set the attribute to
	 */
	public void setJDFVersions(String value)
	{
		setAttribute(AttributeName.JDFVERSIONS, value, null);
	}

	/**
	 * (23) get String attribute JDFVersions
	 *
	 * @return the value of the attribute
	 */
	public String getJDFVersions()
	{
		return getAttribute(AttributeName.JDFVERSIONS, null, JDFCoreConstants.EMPTYSTRING);
	}

	/*
	 * --------------------------------------------------------------------- Methods for Attribute JMFSenderID ---------------------------------------------------------------------
	 */
	/**
	 * (36) set attribute JMFSenderID
	 *
	 * @param value the value to set the attribute to
	 */
	public void setJMFSenderID(String value)
	{
		setAttribute(AttributeName.JMFSENDERID, value, null);
	}

	/**
	 * (23) get String attribute JMFSenderID
	 *
	 * @return the value of the attribute
	 */
	public String getJMFSenderID()
	{
		return getAttribute(AttributeName.JMFSENDERID, null, JDFCoreConstants.EMPTYSTRING);
	}

	/*
	 * --------------------------------------------------------------------- Methods for Attribute JMFURL ---------------------------------------------------------------------
	 */
	/**
	 * (36) set attribute JMFURL
	 *
	 * @param value the value to set the attribute to
	 */
	public void setJMFURL(String value)
	{
		setAttribute(AttributeName.JMFURL, value, null);
	}

	/**
	 * (23) get String attribute JMFURL
	 *
	 * @return the value of the attribute
	 */
	public String getJMFURL()
	{
		return getAttribute(AttributeName.JMFURL, null, JDFCoreConstants.EMPTYSTRING);
	}

	/*
	 * --------------------------------------------------------------------- Methods for Attribute KnownLocalizations
	 * ---------------------------------------------------------------------
	 */
	/**
	 * (36) set attribute KnownLocalizations
	 *
	 * @param value the value to set the attribute to
	 */
	public void setKnownLocalizations(VString value)
	{
		setAttribute(AttributeName.KNOWNLOCALIZATIONS, value, null);
	}

	/**
	 * (21) get VString attribute KnownLocalizations
	 *
	 * @return VString the value of the attribute
	 */
	public VString getKnownLocalizations()
	{
		final VString vStrAttrib = new VString();
		final String s = getAttribute(AttributeName.KNOWNLOCALIZATIONS, null, JDFCoreConstants.EMPTYSTRING);
		vStrAttrib.setAllStrings(s, " ");
		return vStrAttrib;
	}

	/*
	 * --------------------------------------------------------------------- Methods for Attribute Manufacturer
	 * ---------------------------------------------------------------------
	 */
	/**
	 * (36) set attribute Manufacturer
	 *
	 * @param value the value to set the attribute to
	 */
	@Override
	public void setManufacturer(String value)
	{
		setAttribute(AttributeName.MANUFACTURER, value, null);
	}

	/**
	 * (23) get String attribute Manufacturer
	 *
	 * @return the value of the attribute
	 */
	@Override
	public String getManufacturer()
	{
		return getAttribute(AttributeName.MANUFACTURER, null, JDFCoreConstants.EMPTYSTRING);
	}

	/*
	 * --------------------------------------------------------------------- Methods for Attribute ManufacturerURL
	 * ---------------------------------------------------------------------
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
	 * --------------------------------------------------------------------- Methods for Attribute MaxRunSpeed ---------------------------------------------------------------------
	 */
	/**
	 * (36) set attribute MaxRunSpeed
	 *
	 * @param value the value to set the attribute to
	 */
	public void setMaxRunSpeed(double value)
	{
		setAttribute(AttributeName.MAXRUNSPEED, value, null);
	}

	/**
	 * (17) get double attribute MaxRunSpeed
	 *
	 * @return double the value of the attribute
	 */
	public double getMaxRunSpeed()
	{
		return getRealAttribute(AttributeName.MAXRUNSPEED, null, 0.0);
	}

	/*
	 * --------------------------------------------------------------------- Methods for Attribute ModelDescription
	 * ---------------------------------------------------------------------
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
	 * --------------------------------------------------------------------- Methods for Attribute PresentationURL
	 * ---------------------------------------------------------------------
	 */
	/**
	 * (36) set attribute PresentationURL
	 *
	 * @param value the value to set the attribute to
	 */
	public void setPresentationURL(String value)
	{
		setAttribute(AttributeName.PRESENTATIONURL, value, null);
	}

	/**
	 * (23) get String attribute PresentationURL
	 *
	 * @return the value of the attribute
	 */
	public String getPresentationURL()
	{
		return getAttribute(AttributeName.PRESENTATIONURL, null, JDFCoreConstants.EMPTYSTRING);
	}

	/*
	 * --------------------------------------------------------------------- Methods for Attribute Revision ---------------------------------------------------------------------
	 */
	/**
	 * (36) set attribute Revision
	 *
	 * @param value the value to set the attribute to
	 */
	public void setRevision(String value)
	{
		setAttribute(AttributeName.REVISION, value, null);
	}

	/**
	 * (23) get String attribute Revision
	 *
	 * @return the value of the attribute
	 */
	public String getRevision()
	{
		return getAttribute(AttributeName.REVISION, null, JDFCoreConstants.EMPTYSTRING);
	}

	/*
	 * --------------------------------------------------------------------- Methods for Attribute SerialNumber
	 * ---------------------------------------------------------------------
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
	 * --------------------------------------------------------------------- Methods for Attribute SecureJMFURL
	 * ---------------------------------------------------------------------
	 */
	/**
	 * (36) set attribute SecureJMFURL
	 *
	 * @param value the value to set the attribute to
	 */
	public void setSecureJMFURL(String value)
	{
		setAttribute(AttributeName.SECUREJMFURL, value, null);
	}

	/**
	 * (23) get String attribute SecureJMFURL
	 *
	 * @return the value of the attribute
	 */
	public String getSecureJMFURL()
	{
		return getAttribute(AttributeName.SECUREJMFURL, null, JDFCoreConstants.EMPTYSTRING);
	}

	/*
	 * --------------------------------------------------------------------- Methods for Attribute UPC ---------------------------------------------------------------------
	 */
	/**
	 * (36) set attribute UPC
	 *
	 * @param value the value to set the attribute to
	 */
	public void setUPC(String value)
	{
		setAttribute(AttributeName.UPC, value, null);
	}

	/**
	 * (23) get String attribute UPC
	 *
	 * @return the value of the attribute
	 */
	public String getUPC()
	{
		return getAttribute(AttributeName.UPC, null, JDFCoreConstants.EMPTYSTRING);
	}

	/*
	 * *********************************************************************** Element getter / setter ***********************************************************************
	 */

	/**
	 * (24) const get element CostCenter
	 *
	 * @return JDFCostCenter the element
	 */
	public JDFCostCenter getCostCenter()
	{
		return (JDFCostCenter) getElement(ElementName.COSTCENTER, null, 0);
	}

	/**
	 * (25) getCreateCostCenter
	 * 
	 * @return JDFCostCenter the element
	 */
	public JDFCostCenter getCreateCostCenter()
	{
		return (JDFCostCenter) getCreateElement_JDFElement(ElementName.COSTCENTER, null, 0);
	}

	/**
	 * (29) append element CostCenter
	 *
	 * @return JDFCostCenter the element @ if the element already exists
	 */
	public JDFCostCenter appendCostCenter()
	{
		return (JDFCostCenter) appendElementN(ElementName.COSTCENTER, 1, null);
	}

	/**
	 * (26) getCreateDeviceCap
	 * 
	 * @param iSkip number of elements to skip
	 * @return JDFDeviceCap the element
	 */
	public JDFDeviceCap getCreateDeviceCap(int iSkip)
	{
		return (JDFDeviceCap) getCreateElement_JDFElement(ElementName.DEVICECAP, null, iSkip);
	}

	/**
	 * (27) const get element DeviceCap
	 *
	 * @param iSkip number of elements to skip
	 * @return JDFDeviceCap the element default is getDeviceCap(0)
	 */
	public JDFDeviceCap getDeviceCap(int iSkip)
	{
		return (JDFDeviceCap) getElement(ElementName.DEVICECAP, null, iSkip);
	}

	/**
	 * Get all DeviceCap from the current element
	 * 
	 * @return Collection<JDFDeviceCap>, null if none are available
	 */
	public Collection<JDFDeviceCap> getAllDeviceCap()
	{
		return getChildArrayByClass(JDFDeviceCap.class, false, 0);
	}

	/**
	 * (30) append element DeviceCap
	 *
	 * @return JDFDeviceCap the element
	 */
	public JDFDeviceCap appendDeviceCap()
	{
		return (JDFDeviceCap) appendElement(ElementName.DEVICECAP, null);
	}

	/**
	 * (24) const get element IconList
	 *
	 * @return JDFIconList the element
	 */
	public JDFIconList getIconList()
	{
		return (JDFIconList) getElement(ElementName.ICONLIST, null, 0);
	}

	/**
	 * (25) getCreateIconList
	 * 
	 * @return JDFIconList the element
	 */
	public JDFIconList getCreateIconList()
	{
		return (JDFIconList) getCreateElement_JDFElement(ElementName.ICONLIST, null, 0);
	}

	/**
	 * (29) append element IconList
	 *
	 * @return JDFIconList the element @ if the element already exists
	 */
	public JDFIconList appendIconList()
	{
		return (JDFIconList) appendElementN(ElementName.ICONLIST, 1, null);
	}

	/**
	 * (26) getCreateModule
	 * 
	 * @param iSkip number of elements to skip
	 * @return JDFModule the element
	 */
	public JDFModule getCreateModule(int iSkip)
	{
		return (JDFModule) getCreateElement_JDFElement(ElementName.MODULE, null, iSkip);
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
		return getChildArrayByClass(JDFModule.class, false, 0);
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

}
