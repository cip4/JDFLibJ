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
import org.cip4.jdflib.core.JDFCoreConstants;
import org.cip4.jdflib.core.JDFElement;
import org.cip4.jdflib.core.JDFResourceLink;
import org.cip4.jdflib.core.VString;
import org.cip4.jdflib.datatypes.JDFIntegerRangeList;
import org.cip4.jdflib.resource.devicecapability.JDFDevCap;
import org.cip4.jdflib.resource.devicecapability.JDFDeviceCap;
import org.cip4.jdflib.resource.devicecapability.JDFLoc;

/**
 *****************************************************************************
 * class JDFAutoDevCaps : public JDFElement
 *****************************************************************************
 * 
 */

public abstract class JDFAutoDevCaps extends JDFElement
{

	private static final long serialVersionUID = 1L;

	private static AtrInfoTable[] atrInfoTable = new AtrInfoTable[14];
	static
	{
		atrInfoTable[0] = new AtrInfoTable(AttributeName.AVAILABILITY, 0x33333311, AttributeInfo.EnumAttributeType.enumeration, JDFDeviceCap.EnumAvailability.getEnum(0), "Installed");
		atrInfoTable[1] = new AtrInfoTable(AttributeName.CONTEXT, 0x33333311, AttributeInfo.EnumAttributeType.enumeration, EnumContext.getEnum(0), "Resource");
		atrInfoTable[2] = new AtrInfoTable(AttributeName.DEVCAPREF, 0x33333111, AttributeInfo.EnumAttributeType.IDREFS, null, null);
		atrInfoTable[3] = new AtrInfoTable(AttributeName.DEVNS, 0x33333331, AttributeInfo.EnumAttributeType.URI, null, "http://www.CIP4.org/JDFSchema_1_1");
		atrInfoTable[4] = new AtrInfoTable(AttributeName.ID, 0x33333311, AttributeInfo.EnumAttributeType.ID, null, null);
		atrInfoTable[5] = new AtrInfoTable(AttributeName.LINKUSAGE, 0x33333311, AttributeInfo.EnumAttributeType.enumeration, JDFResourceLink.EnumUsage.getEnum(0), null);
		atrInfoTable[6] = new AtrInfoTable(AttributeName.MODULEREFS, 0x33333111, AttributeInfo.EnumAttributeType.IDREFS, null, null);
		atrInfoTable[7] = new AtrInfoTable(AttributeName.NAME, 0x22222221, AttributeInfo.EnumAttributeType.NMTOKEN, null, null);
		atrInfoTable[8] = new AtrInfoTable(AttributeName.PROCESSUSAGE, 0x33333111, AttributeInfo.EnumAttributeType.NMTOKEN, null, null);
		atrInfoTable[9] = new AtrInfoTable(AttributeName.REQUIRED, 0x33333311, AttributeInfo.EnumAttributeType.boolean_, null, "false");
		atrInfoTable[10] = new AtrInfoTable(AttributeName.RESOURCEUPDATE, 0x44444331, AttributeInfo.EnumAttributeType.NMTOKENS, null, null);
		atrInfoTable[11] = new AtrInfoTable(AttributeName.RESOURCEUSAGE, 0x33333111, AttributeInfo.EnumAttributeType.NMTOKEN, null, null);
		atrInfoTable[12] = new AtrInfoTable(AttributeName.TYPEOCCURRENCENUM, 0x33333311, AttributeInfo.EnumAttributeType.IntegerRangeList, null, null);
		atrInfoTable[13] = new AtrInfoTable(AttributeName.TYPES, 0x44444431, AttributeInfo.EnumAttributeType.NMTOKENS, null, null);
	}

	@Override
	protected AttributeInfo getTheAttributeInfo()
	{
		return super.getTheAttributeInfo().updateReplace(atrInfoTable);
	}

	private static ElemInfoTable[] elemInfoTable = new ElemInfoTable[2];
	static
	{
		elemInfoTable[0] = new ElemInfoTable(ElementName.DEVCAP, 0x33333331);
		elemInfoTable[1] = new ElemInfoTable(ElementName.LOC, 0x33333331);
	}

	@Override
	protected ElementInfo getTheElementInfo()
	{
		return super.getTheElementInfo().updateReplace(elemInfoTable);
	}

	/**
	 * Constructor for JDFAutoDevCaps
	 * 
	 * @param myOwnerDocument
	 * @param qualifiedName
	 */
	protected JDFAutoDevCaps(CoreDocumentImpl myOwnerDocument, String qualifiedName)
	{
		super(myOwnerDocument, qualifiedName);
	}

	/**
	 * Constructor for JDFAutoDevCaps
	 * 
	 * @param myOwnerDocument
	 * @param myNamespaceURI
	 * @param qualifiedName
	 */
	protected JDFAutoDevCaps(CoreDocumentImpl myOwnerDocument, String myNamespaceURI, String qualifiedName)
	{
		super(myOwnerDocument, myNamespaceURI, qualifiedName);
	}

	/**
	 * Constructor for JDFAutoDevCaps
	 * 
	 * @param myOwnerDocument
	 * @param myNamespaceURI
	 * @param qualifiedName
	 * @param myLocalName
	 */
	protected JDFAutoDevCaps(CoreDocumentImpl myOwnerDocument, String myNamespaceURI, String qualifiedName, String myLocalName)
	{
		super(myOwnerDocument, myNamespaceURI, qualifiedName, myLocalName);
	}

	/**
	 * @return the string representation
	 */
	@Override
	public String toString()
	{
		return " JDFAutoDevCaps[  --> " + super.toString() + " ]";
	}

	/**
	 * Enumeration strings for Context
	 */

	@SuppressWarnings("rawtypes")
	public static class EnumContext extends ValuedEnum
	{
		private static final long serialVersionUID = 1L;
		private static int m_startValue = 0;

		private EnumContext(String name)
		{
			super(name, m_startValue++);
		}

		/**
		 * @param enumName the string to convert
		 * @return the enum
		 */
		public static EnumContext getEnum(String enumName)
		{
			return (EnumContext) getEnum(EnumContext.class, enumName);
		}

		/**
		 * @param enumValue the integer to convert
		 * @return the enum
		 */
		public static EnumContext getEnum(int enumValue)
		{
			return (EnumContext) getEnum(EnumContext.class, enumValue);
		}

		/**
		 * @return the map of enums
		 */
		public static Map getEnumMap()
		{
			return getEnumMap(EnumContext.class);
		}

		/**
		 * @return the list of enums
		 */
		public static List getEnumList()
		{
			return getEnumList(EnumContext.class);
		}

		/**
		 * @return the iterator
		 */
		public static Iterator iterator()
		{
			return iterator(EnumContext.class);
		}

		/**  */
		public static final EnumContext Resource = new EnumContext("Resource");
		/**  */
		public static final EnumContext Link = new EnumContext("Link");
		/**  */
		public static final EnumContext JMF = new EnumContext("JMF");
		/**  */
		public static final EnumContext Element = new EnumContext("Element");
	}

	/*
	 * ************************************************************************ Attribute getter / setter ************************************************************************
	 */

	/*
	 * --------------------------------------------------------------------- Methods for Attribute Availability ---------------------------------------------------------------------
	 */
	/**
	 * (5) set attribute Availability
	 * 
	 * @param enumVar the enumVar to set the attribute to
	 */
	public void setAvailability(JDFDeviceCap.EnumAvailability enumVar)
	{
		setAttribute(AttributeName.AVAILABILITY, enumVar == null ? null : enumVar.getName(), null);
	}

	/**
	 * (9) get attribute Availability
	 * 
	 * @return the value of the attribute
	 */
	public JDFDeviceCap.EnumAvailability getAvailability()
	{
		return JDFDeviceCap.EnumAvailability.getEnum(getAttribute(AttributeName.AVAILABILITY, null, "Installed"));
	}

	/*
	 * --------------------------------------------------------------------- Methods for Attribute Context ---------------------------------------------------------------------
	 */
	/**
	 * (5) set attribute Context
	 * 
	 * @param enumVar the enumVar to set the attribute to
	 */
	public void setContext(EnumContext enumVar)
	{
		setAttribute(AttributeName.CONTEXT, enumVar == null ? null : enumVar.getName(), null);
	}

	/**
	 * (9) get attribute Context
	 * 
	 * @return the value of the attribute
	 */
	public EnumContext getContext()
	{
		return EnumContext.getEnum(getAttribute(AttributeName.CONTEXT, null, "Resource"));
	}

	/*
	 * --------------------------------------------------------------------- Methods for Attribute DevCapRef ---------------------------------------------------------------------
	 */
	/**
	 * (36) set attribute DevCapRef
	 * 
	 * @param value the value to set the attribute to
	 */
	public void setDevCapRef(VString value)
	{
		setAttribute(AttributeName.DEVCAPREF, value, null);
	}

	/**
	 * (21) get VString attribute DevCapRef
	 * 
	 * @return VString the value of the attribute
	 */
	public VString getDevCapRef()
	{
		final VString vStrAttrib = new VString();
		final String s = getAttribute(AttributeName.DEVCAPREF, null, JDFCoreConstants.EMPTYSTRING);
		vStrAttrib.setAllStrings(s, " ");
		return vStrAttrib;
	}

	/*
	 * --------------------------------------------------------------------- Methods for Attribute DevNS ---------------------------------------------------------------------
	 */
	/**
	 * (36) set attribute DevNS
	 * 
	 * @param value the value to set the attribute to
	 */
	public void setDevNS(String value)
	{
		setAttribute(AttributeName.DEVNS, value, null);
	}

	/**
	 * (23) get String attribute DevNS
	 * 
	 * @return the value of the attribute
	 */
	public String getDevNS()
	{
		return getAttribute(AttributeName.DEVNS, null, "http://www.CIP4.org/JDFSchema_1_1");
	}

	/*
	 * --------------------------------------------------------------------- Methods for Attribute ID ---------------------------------------------------------------------
	 */
	/**
	 * (36) set attribute ID
	 * 
	 * @param value the value to set the attribute to
	 */
	@Override
	public void setID(String value)
	{
		setAttribute(AttributeName.ID, value, null);
	}

	/**
	 * (23) get String attribute ID
	 * 
	 * @return the value of the attribute
	 */
	@Override
	public String getID()
	{
		return getAttribute(AttributeName.ID, null, JDFCoreConstants.EMPTYSTRING);
	}

	/*
	 * --------------------------------------------------------------------- Methods for Attribute LinkUsage ---------------------------------------------------------------------
	 */
	/**
	 * (5) set attribute LinkUsage
	 * 
	 * @param enumVar the enumVar to set the attribute to
	 */
	public void setLinkUsage(JDFResourceLink.EnumUsage enumVar)
	{
		setAttribute(AttributeName.LINKUSAGE, enumVar == null ? null : enumVar.getName(), null);
	}

	/**
	 * (9) get attribute LinkUsage
	 * 
	 * @return the value of the attribute
	 */
	public JDFResourceLink.EnumUsage getLinkUsage()
	{
		return JDFResourceLink.EnumUsage.getEnum(getAttribute(AttributeName.LINKUSAGE, null, null));
	}

	/*
	 * --------------------------------------------------------------------- Methods for Attribute ModuleRefs ---------------------------------------------------------------------
	 */
	/**
	 * (36) set attribute ModuleRefs
	 * 
	 * @param value the value to set the attribute to
	 */
	public void setModuleRefs(VString value)
	{
		setAttribute(AttributeName.MODULEREFS, value, null);
	}

	/**
	 * (21) get VString attribute ModuleRefs
	 * 
	 * @return VString the value of the attribute
	 */
	public VString getModuleRefs()
	{
		final VString vStrAttrib = new VString();
		final String s = getAttribute(AttributeName.MODULEREFS, null, JDFCoreConstants.EMPTYSTRING);
		vStrAttrib.setAllStrings(s, " ");
		return vStrAttrib;
	}

	/*
	 * --------------------------------------------------------------------- Methods for Attribute Name ---------------------------------------------------------------------
	 */
	/**
	 * (36) set attribute Name
	 * 
	 * @param value the value to set the attribute to
	 */
	public void setName(String value)
	{
		setAttribute(AttributeName.NAME, value, null);
	}

	/**
	 * (23) get String attribute Name
	 * 
	 * @return the value of the attribute
	 */
	public String getName()
	{
		return getAttribute(AttributeName.NAME, null, JDFCoreConstants.EMPTYSTRING);
	}

	/*
	 * --------------------------------------------------------------------- Methods for Attribute ProcessUsage ---------------------------------------------------------------------
	 */
	/**
	 * (36) set attribute ProcessUsage
	 * 
	 * @param value the value to set the attribute to
	 */
	public void setProcessUsage(String value)
	{
		setAttribute(AttributeName.PROCESSUSAGE, value, null);
	}

	/**
	 * (23) get String attribute ProcessUsage
	 * 
	 * @return the value of the attribute
	 */
	public String getProcessUsage()
	{
		return getAttribute(AttributeName.PROCESSUSAGE, null, JDFCoreConstants.EMPTYSTRING);
	}

	/*
	 * --------------------------------------------------------------------- Methods for Attribute Required ---------------------------------------------------------------------
	 */
	/**
	 * (36) set attribute Required
	 * 
	 * @param value the value to set the attribute to
	 */
	public void setRequired(boolean value)
	{
		setAttribute(AttributeName.REQUIRED, value, null);
	}

	/**
	 * (18) get boolean attribute Required
	 * 
	 * @return boolean the value of the attribute
	 */
	public boolean getRequired()
	{
		return getBoolAttribute(AttributeName.REQUIRED, null, false);
	}

	/*
	 * --------------------------------------------------------------------- Methods for Attribute ResourceUpdate ---------------------------------------------------------------------
	 */
	/**
	 * (36) set attribute ResourceUpdate
	 * 
	 * @param value the value to set the attribute to
	 */
	public void setResourceUpdate(VString value)
	{
		setAttribute(AttributeName.RESOURCEUPDATE, value, null);
	}

	/**
	 * (21) get VString attribute ResourceUpdate
	 * 
	 * @return VString the value of the attribute
	 */
	public VString getResourceUpdate()
	{
		final VString vStrAttrib = new VString();
		final String s = getAttribute(AttributeName.RESOURCEUPDATE, null, JDFCoreConstants.EMPTYSTRING);
		vStrAttrib.setAllStrings(s, " ");
		return vStrAttrib;
	}

	/*
	 * --------------------------------------------------------------------- Methods for Attribute ResourceUsage ---------------------------------------------------------------------
	 */
	/**
	 * (36) set attribute ResourceUsage
	 * 
	 * @param value the value to set the attribute to
	 */
	public void setResourceUsage(String value)
	{
		setAttribute(AttributeName.RESOURCEUSAGE, value, null);
	}

	/**
	 * (23) get String attribute ResourceUsage
	 * 
	 * @return the value of the attribute
	 */
	public String getResourceUsage()
	{
		return getAttribute(AttributeName.RESOURCEUSAGE, null, JDFCoreConstants.EMPTYSTRING);
	}

	/*
	 * --------------------------------------------------------------------- Methods for Attribute TypeOccurrenceNum ---------------------------------------------------------------------
	 */
	/**
	 * (36) set attribute TypeOccurrenceNum
	 * 
	 * @param value the value to set the attribute to
	 */
	public void setTypeOccurrenceNum(JDFIntegerRangeList value)
	{
		setAttribute(AttributeName.TYPEOCCURRENCENUM, value, null);
	}

	/**
	 * (20) get JDFIntegerRangeList attribute TypeOccurrenceNum
	 * 
	 * @return JDFIntegerRangeList the value of the attribute, null if a the attribute value is not a valid to create a JDFIntegerRangeList
	 */
	public JDFIntegerRangeList getTypeOccurrenceNum()
	{
		final String strAttrName = getAttribute(AttributeName.TYPEOCCURRENCENUM, null, null);
		final JDFIntegerRangeList nPlaceHolder = JDFIntegerRangeList.createIntegerRangeList(strAttrName);
		return nPlaceHolder;
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
	 * (26) getCreateDevCap
	 * 
	 * @param iSkip number of elements to skip
	 * @return JDFDevCap the element
	 */
	public JDFDevCap getCreateDevCap(int iSkip)
	{
		return (JDFDevCap) getCreateElement_JDFElement(ElementName.DEVCAP, null, iSkip);
	}

	/**
	 * (27) const get element DevCap
	 * 
	 * @param iSkip number of elements to skip
	 * @return JDFDevCap the element default is getDevCap(0)
	 */
	public JDFDevCap getDevCap(int iSkip)
	{
		return (JDFDevCap) getElement(ElementName.DEVCAP, null, iSkip);
	}

	/**
	 * Get all DevCap from the current element
	 * 
	 * @return Collection<JDFDevCap>, null if none are available
	 */
	public Collection<JDFDevCap> getAllDevCap()
	{
		return getChildrenByClass(JDFDevCap.class, false, 0);
	}

	/**
	 * (30) append element DevCap
	 * 
	 * @return JDFDevCap the element
	 */
	public JDFDevCap appendDevCap()
	{
		return (JDFDevCap) appendElement(ElementName.DEVCAP, null);
	}

	/**
	 * (26) getCreateLoc
	 * 
	 * @param iSkip number of elements to skip
	 * @return JDFLoc the element
	 */
	public JDFLoc getCreateLoc(int iSkip)
	{
		return (JDFLoc) getCreateElement_JDFElement(ElementName.LOC, null, iSkip);
	}

	/**
	 * (27) const get element Loc
	 * 
	 * @param iSkip number of elements to skip
	 * @return JDFLoc the element default is getLoc(0)
	 */
	public JDFLoc getLoc(int iSkip)
	{
		return (JDFLoc) getElement(ElementName.LOC, null, iSkip);
	}

	/**
	 * Get all Loc from the current element
	 * 
	 * @return Collection<JDFLoc>, null if none are available
	 */
	public Collection<JDFLoc> getAllLoc()
	{
		return getChildrenByClass(JDFLoc.class, false, 0);
	}

	/**
	 * (30) append element Loc
	 * 
	 * @return JDFLoc the element
	 */
	public JDFLoc appendLoc()
	{
		return (JDFLoc) appendElement(ElementName.LOC, null);
	}

}// end namespace JDF
