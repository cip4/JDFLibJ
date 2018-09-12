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
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.enums.ValuedEnum;
import org.apache.xerces.dom.CoreDocumentImpl;
import org.cip4.jdflib.core.AtrInfoTable;
import org.cip4.jdflib.core.AttributeInfo;
import org.cip4.jdflib.core.AttributeName;
import org.cip4.jdflib.core.ElemInfoTable;
import org.cip4.jdflib.core.ElementInfo;
import org.cip4.jdflib.core.ElementName;
import org.cip4.jdflib.resource.JDFResource;
import org.cip4.jdflib.resource.process.JDFContact;
import org.cip4.jdflib.resource.process.JDFConvertingConfig;
import org.cip4.jdflib.resource.process.JDFRepeatDesc;

/**
 *****************************************************************************
 * class JDFAutoDieLayoutProductionParams : public JDFResource
 *****************************************************************************
 * 
 */

public abstract class JDFAutoDieLayoutProductionParams extends JDFResource
{

	private static final long serialVersionUID = 1L;

	private static AtrInfoTable[] atrInfoTable = new AtrInfoTable[2];
	static
	{
		atrInfoTable[0] = new AtrInfoTable(AttributeName.ESTIMATE, 0x33331111, AttributeInfo.EnumAttributeType.boolean_, null, null);
		atrInfoTable[1] = new AtrInfoTable(AttributeName.POSITION, 0x33331111, AttributeInfo.EnumAttributeType.enumeration, EnumPosition.getEnum(0), null);
	}

	@Override
	protected AttributeInfo getTheAttributeInfo()
	{
		return super.getTheAttributeInfo().updateReplace(atrInfoTable);
	}

	private static ElemInfoTable[] elemInfoTable = new ElemInfoTable[3];
	static
	{
		elemInfoTable[0] = new ElemInfoTable(ElementName.CONVERTINGCONFIG, 0x22221111);
		elemInfoTable[1] = new ElemInfoTable(ElementName.REPEATDESC, 0x22221111);
		elemInfoTable[2] = new ElemInfoTable(ElementName.CONTACT, 0x33331111);
	}

	@Override
	protected ElementInfo getTheElementInfo()
	{
		return super.getTheElementInfo().updateReplace(elemInfoTable);
	}

	/**
	 * Constructor for JDFAutoDieLayoutProductionParams
	 * 
	 * @param myOwnerDocument
	 * @param qualifiedName
	 */
	protected JDFAutoDieLayoutProductionParams(CoreDocumentImpl myOwnerDocument, String qualifiedName)
	{
		super(myOwnerDocument, qualifiedName);
	}

	/**
	 * Constructor for JDFAutoDieLayoutProductionParams
	 * 
	 * @param myOwnerDocument
	 * @param myNamespaceURI
	 * @param qualifiedName
	 */
	protected JDFAutoDieLayoutProductionParams(CoreDocumentImpl myOwnerDocument, String myNamespaceURI, String qualifiedName)
	{
		super(myOwnerDocument, myNamespaceURI, qualifiedName);
	}

	/**
	 * Constructor for JDFAutoDieLayoutProductionParams
	 * 
	 * @param myOwnerDocument
	 * @param myNamespaceURI
	 * @param qualifiedName
	 * @param myLocalName
	 */
	protected JDFAutoDieLayoutProductionParams(CoreDocumentImpl myOwnerDocument, String myNamespaceURI, String qualifiedName, String myLocalName)
	{
		super(myOwnerDocument, myNamespaceURI, qualifiedName, myLocalName);
	}

	/**
	 * @return the string representation
	 */
	@Override
	public String toString()
	{
		return " JDFAutoDieLayoutProductionParams[  --> " + super.toString() + " ]";
	}

	/**
	 * @return true if ok
	 */
	@Override
	public boolean init()
	{
		final boolean bRet = super.init();
		setResourceClass(JDFResource.EnumResourceClass.Parameter);
		return bRet;
	}

	/**
	 * @return the resource Class
	 */
	@Override
	public EnumResourceClass getValidClass()
	{
		return JDFResource.EnumResourceClass.Parameter;
	}

	/**
	 * Enumeration strings for Position
	 */

	@SuppressWarnings("rawtypes")
	public static class EnumPosition extends ValuedEnum
	{
		private static final long serialVersionUID = 1L;
		private static int m_startValue = 0;

		private EnumPosition(String name)
		{
			super(name, m_startValue++);
		}

		/**
		 * @param enumName the string to convert
		 * @return the enum
		 */
		public static EnumPosition getEnum(String enumName)
		{
			return (EnumPosition) getEnum(EnumPosition.class, enumName);
		}

		/**
		 * @param enumValue the integer to convert
		 * @return the enum
		 */
		public static EnumPosition getEnum(int enumValue)
		{
			return (EnumPosition) getEnum(EnumPosition.class, enumValue);
		}

		/**
		 * @return the map of enums
		 */
		public static Map getEnumMap()
		{
			return getEnumMap(EnumPosition.class);
		}

		/**
		 * @return the list of enums
		 */
		public static List getEnumList()
		{
			return getEnumList(EnumPosition.class);
		}

		/**
		 * @return the iterator
		 */
		public static Iterator iterator()
		{
			return iterator(EnumPosition.class);
		}

		/**  */
		public static final EnumPosition TopLeft = new EnumPosition("TopLeft");
		/**  */
		public static final EnumPosition TopCenter = new EnumPosition("TopCenter");
		/**  */
		public static final EnumPosition TopRight = new EnumPosition("TopRight");
		/**  */
		public static final EnumPosition CenterLeft = new EnumPosition("CenterLeft");
		/**  */
		public static final EnumPosition Center = new EnumPosition("Center");
		/**  */
		public static final EnumPosition CenterRight = new EnumPosition("CenterRight");
		/**  */
		public static final EnumPosition BottomLeft = new EnumPosition("BottomLeft");
		/**  */
		public static final EnumPosition BottomCenter = new EnumPosition("BottomCenter");
		/**  */
		public static final EnumPosition BottomRight = new EnumPosition("BottomRight");
	}

	/*
	 * ************************************************************************ Attribute getter / setter ************************************************************************
	 */

	/*
	 * --------------------------------------------------------------------- Methods for Attribute Estimate ---------------------------------------------------------------------
	 */
	/**
	 * (36) set attribute Estimate
	 * 
	 * @param value the value to set the attribute to
	 */
	public void setEstimate(boolean value)
	{
		setAttribute(AttributeName.ESTIMATE, value, null);
	}

	/**
	 * (18) get boolean attribute Estimate
	 * 
	 * @return boolean the value of the attribute
	 */
	public boolean getEstimate()
	{
		return getBoolAttribute(AttributeName.ESTIMATE, null, false);
	}

	/*
	 * --------------------------------------------------------------------- Methods for Attribute Position ---------------------------------------------------------------------
	 */
	/**
	 * (5) set attribute Position
	 * 
	 * @param enumVar the enumVar to set the attribute to
	 */
	public void setPosition(EnumPosition enumVar)
	{
		setAttribute(AttributeName.POSITION, enumVar == null ? null : enumVar.getName(), null);
	}

	/**
	 * (9) get attribute Position
	 * 
	 * @return the value of the attribute
	 */
	public EnumPosition getPosition()
	{
		return EnumPosition.getEnum(getAttribute(AttributeName.POSITION, null, null));
	}

	/*
	 * *********************************************************************** Element getter / setter ***********************************************************************
	 */

	/**
	 * (26) getCreateConvertingConfig
	 * 
	 * @param iSkip number of elements to skip
	 * @return JDFConvertingConfig the element
	 */
	public JDFConvertingConfig getCreateConvertingConfig(int iSkip)
	{
		return (JDFConvertingConfig) getCreateElement_JDFElement(ElementName.CONVERTINGCONFIG, null, iSkip);
	}

	/**
	 * (27) const get element ConvertingConfig
	 * 
	 * @param iSkip number of elements to skip
	 * @return JDFConvertingConfig the element default is getConvertingConfig(0)
	 */
	public JDFConvertingConfig getConvertingConfig(int iSkip)
	{
		return (JDFConvertingConfig) getElement(ElementName.CONVERTINGCONFIG, null, iSkip);
	}

	/**
	 * Get all ConvertingConfig from the current element
	 * 
	 * @return Collection<JDFConvertingConfig>, null if none are available
	 */
	public Collection<JDFConvertingConfig> getAllConvertingConfig()
	{
		return getChildrenByClass(JDFConvertingConfig.class, false, 0);
	}

	/**
	 * (30) append element ConvertingConfig
	 * 
	 * @return JDFConvertingConfig the element
	 */
	public JDFConvertingConfig appendConvertingConfig()
	{
		return (JDFConvertingConfig) appendElement(ElementName.CONVERTINGCONFIG, null);
	}

	/**
	 * (26) getCreateRepeatDesc
	 * 
	 * @param iSkip number of elements to skip
	 * @return JDFRepeatDesc the element
	 */
	public JDFRepeatDesc getCreateRepeatDesc(int iSkip)
	{
		return (JDFRepeatDesc) getCreateElement_JDFElement(ElementName.REPEATDESC, null, iSkip);
	}

	/**
	 * (27) const get element RepeatDesc
	 * 
	 * @param iSkip number of elements to skip
	 * @return JDFRepeatDesc the element default is getRepeatDesc(0)
	 */
	public JDFRepeatDesc getRepeatDesc(int iSkip)
	{
		return (JDFRepeatDesc) getElement(ElementName.REPEATDESC, null, iSkip);
	}

	/**
	 * Get all RepeatDesc from the current element
	 * 
	 * @return Collection<JDFRepeatDesc>, null if none are available
	 */
	public Collection<JDFRepeatDesc> getAllRepeatDesc()
	{
		return getChildrenByClass(JDFRepeatDesc.class, false, 0);
	}

	/**
	 * (30) append element RepeatDesc
	 * 
	 * @return JDFRepeatDesc the element
	 */
	public JDFRepeatDesc appendRepeatDesc()
	{
		return (JDFRepeatDesc) appendElement(ElementName.REPEATDESC, null);
	}

	/**
	 * (26) getCreateContact
	 * 
	 * @param iSkip number of elements to skip
	 * @return JDFContact the element
	 */
	public JDFContact getCreateContact(int iSkip)
	{
		return (JDFContact) getCreateElement_JDFElement(ElementName.CONTACT, null, iSkip);
	}

	/**
	 * (27) const get element Contact
	 * 
	 * @param iSkip number of elements to skip
	 * @return JDFContact the element default is getContact(0)
	 */
	public JDFContact getContact(int iSkip)
	{
		return (JDFContact) getElement(ElementName.CONTACT, null, iSkip);
	}

	/**
	 * Get all Contact from the current element
	 * 
	 * @return Collection<JDFContact>, null if none are available
	 */
	public Collection<JDFContact> getAllContact()
	{
		return getChildrenByClass(JDFContact.class, false, 0);
	}

	/**
	 * (30) append element Contact
	 * 
	 * @return JDFContact the element
	 */
	@Override
	public JDFContact appendContact()
	{
		return (JDFContact) appendElement(ElementName.CONTACT, null);
	}

	/**
	 * (31) create inter-resource link to refTarget
	 * 
	 * @param refTarget the element that is referenced
	 */
	public void refContact(JDFContact refTarget)
	{
		refElement(refTarget);
	}

}// end namespace JDF
