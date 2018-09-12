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
import org.cip4.jdflib.resource.JDFBundleItem;
import org.cip4.jdflib.resource.JDFResource;
import org.cip4.jdflib.resource.process.JDFContact;
import org.cip4.jdflib.resource.process.JDFIdentificationField;

/**
 *****************************************************************************
 * class JDFAutoBundle : public JDFResource
 *****************************************************************************
 * 
 */

public abstract class JDFAutoBundle extends JDFResource
{

	private static final long serialVersionUID = 1L;

	private static AtrInfoTable[] atrInfoTable = new AtrInfoTable[5];
	static
	{
		atrInfoTable[0] = new AtrInfoTable(AttributeName.BUNDLETYPE, 0x33333331, AttributeInfo.EnumAttributeType.enumeration, EnumBundleType.getEnum(0), "Stack");
		atrInfoTable[1] = new AtrInfoTable(AttributeName.FOLIOCOUNT, 0x33333331, AttributeInfo.EnumAttributeType.integer, null, null);
		atrInfoTable[2] = new AtrInfoTable(AttributeName.READERPAGECOUNT, 0x33333331, AttributeInfo.EnumAttributeType.integer, null, null);
		atrInfoTable[3] = new AtrInfoTable(AttributeName.SHEETCOUNT, 0x33311111, AttributeInfo.EnumAttributeType.integer, null, null);
		atrInfoTable[4] = new AtrInfoTable(AttributeName.TOTALAMOUNT, 0x33333331, AttributeInfo.EnumAttributeType.integer, null, null);
	}

	@Override
	protected AttributeInfo getTheAttributeInfo()
	{
		return super.getTheAttributeInfo().updateReplace(atrInfoTable);
	}

	private static ElemInfoTable[] elemInfoTable = new ElemInfoTable[3];
	static
	{
		elemInfoTable[0] = new ElemInfoTable(ElementName.BUNDLEITEM, 0x33333331);
		elemInfoTable[1] = new ElemInfoTable(ElementName.CONTACT, 0x33333331);
		elemInfoTable[2] = new ElemInfoTable(ElementName.IDENTIFICATIONFIELD, 0x33333331);
	}

	@Override
	protected ElementInfo getTheElementInfo()
	{
		return super.getTheElementInfo().updateReplace(elemInfoTable);
	}

	/**
	 * Constructor for JDFAutoBundle
	 * 
	 * @param myOwnerDocument
	 * @param qualifiedName
	 */
	protected JDFAutoBundle(CoreDocumentImpl myOwnerDocument, String qualifiedName)
	{
		super(myOwnerDocument, qualifiedName);
	}

	/**
	 * Constructor for JDFAutoBundle
	 * 
	 * @param myOwnerDocument
	 * @param myNamespaceURI
	 * @param qualifiedName
	 */
	protected JDFAutoBundle(CoreDocumentImpl myOwnerDocument, String myNamespaceURI, String qualifiedName)
	{
		super(myOwnerDocument, myNamespaceURI, qualifiedName);
	}

	/**
	 * Constructor for JDFAutoBundle
	 * 
	 * @param myOwnerDocument
	 * @param myNamespaceURI
	 * @param qualifiedName
	 * @param myLocalName
	 */
	protected JDFAutoBundle(CoreDocumentImpl myOwnerDocument, String myNamespaceURI, String qualifiedName, String myLocalName)
	{
		super(myOwnerDocument, myNamespaceURI, qualifiedName, myLocalName);
	}

	/**
	 * @return the string representation
	 */
	@Override
	public String toString()
	{
		return " JDFAutoBundle[  --> " + super.toString() + " ]";
	}

	/**
	 * @return true if ok
	 */
	@Override
	public boolean init()
	{
		final boolean bRet = super.init();
		setResourceClass(JDFResource.EnumResourceClass.Quantity);
		return bRet;
	}

	/**
	 * @return the resource Class
	 */
	@Override
	public EnumResourceClass getValidClass()
	{
		return JDFResource.EnumResourceClass.Quantity;
	}

	/**
	 * Enumeration strings for BundleType
	 */

	@SuppressWarnings("rawtypes")
	public static class EnumBundleType extends ValuedEnum
	{
		private static final long serialVersionUID = 1L;
		private static int m_startValue = 0;

		private EnumBundleType(String name)
		{
			super(name, m_startValue++);
		}

		/**
		 * @param enumName the string to convert
		 * @return the enum
		 */
		public static EnumBundleType getEnum(String enumName)
		{
			return (EnumBundleType) getEnum(EnumBundleType.class, enumName);
		}

		/**
		 * @param enumValue the integer to convert
		 * @return the enum
		 */
		public static EnumBundleType getEnum(int enumValue)
		{
			return (EnumBundleType) getEnum(EnumBundleType.class, enumValue);
		}

		/**
		 * @return the map of enums
		 */
		public static Map getEnumMap()
		{
			return getEnumMap(EnumBundleType.class);
		}

		/**
		 * @return the list of enums
		 */
		public static List getEnumList()
		{
			return getEnumList(EnumBundleType.class);
		}

		/**
		 * @return the iterator
		 */
		public static Iterator iterator()
		{
			return iterator(EnumBundleType.class);
		}

		/**  */
		public static final EnumBundleType BoundSet = new EnumBundleType("BoundSet");
		/**  */
		public static final EnumBundleType Box = new EnumBundleType("Box");
		/**  */
		public static final EnumBundleType Carton = new EnumBundleType("Carton");
		/**  */
		public static final EnumBundleType CollectedStack = new EnumBundleType("CollectedStack");
		/**  */
		public static final EnumBundleType CompensatedStack = new EnumBundleType("CompensatedStack");
		/**  */
		public static final EnumBundleType Pallet = new EnumBundleType("Pallet");
		/**  */
		public static final EnumBundleType Roll = new EnumBundleType("Roll");
		/**  */
		public static final EnumBundleType Sheet = new EnumBundleType("Sheet");
		/**  */
		public static final EnumBundleType SheetStream = new EnumBundleType("SheetStream");
		/**  */
		public static final EnumBundleType Stack = new EnumBundleType("Stack");
		/**  */
		public static final EnumBundleType StrappedStack = new EnumBundleType("StrappedStack");
		/**  */
		public static final EnumBundleType StrappedCompensatedStack = new EnumBundleType("StrappedCompensatedStack");
		/**  */
		public static final EnumBundleType WrappedBundle = new EnumBundleType("WrappedBundle");
	}

	/*
	 * ************************************************************************ Attribute getter / setter ************************************************************************
	 */

	/*
	 * --------------------------------------------------------------------- Methods for Attribute BundleType ---------------------------------------------------------------------
	 */
	/**
	 * (5) set attribute BundleType
	 * 
	 * @param enumVar the enumVar to set the attribute to
	 */
	public void setBundleType(EnumBundleType enumVar)
	{
		setAttribute(AttributeName.BUNDLETYPE, enumVar == null ? null : enumVar.getName(), null);
	}

	/**
	 * (9) get attribute BundleType
	 * 
	 * @return the value of the attribute
	 */
	public EnumBundleType getBundleType()
	{
		return EnumBundleType.getEnum(getAttribute(AttributeName.BUNDLETYPE, null, "Stack"));
	}

	/*
	 * --------------------------------------------------------------------- Methods for Attribute FolioCount ---------------------------------------------------------------------
	 */
	/**
	 * (36) set attribute FolioCount
	 * 
	 * @param value the value to set the attribute to
	 */
	public void setFolioCount(int value)
	{
		setAttribute(AttributeName.FOLIOCOUNT, value, null);
	}

	/**
	 * (15) get int attribute FolioCount
	 * 
	 * @return int the value of the attribute
	 */
	public int getFolioCount()
	{
		return getIntAttribute(AttributeName.FOLIOCOUNT, null, 0);
	}

	/*
	 * --------------------------------------------------------------------- Methods for Attribute ReaderPageCount ---------------------------------------------------------------------
	 */
	/**
	 * (36) set attribute ReaderPageCount
	 * 
	 * @param value the value to set the attribute to
	 */
	public void setReaderPageCount(int value)
	{
		setAttribute(AttributeName.READERPAGECOUNT, value, null);
	}

	/**
	 * (15) get int attribute ReaderPageCount
	 * 
	 * @return int the value of the attribute
	 */
	public int getReaderPageCount()
	{
		return getIntAttribute(AttributeName.READERPAGECOUNT, null, 0);
	}

	/*
	 * --------------------------------------------------------------------- Methods for Attribute SheetCount ---------------------------------------------------------------------
	 */
	/**
	 * (36) set attribute SheetCount
	 * 
	 * @param value the value to set the attribute to
	 */
	public void setSheetCount(int value)
	{
		setAttribute(AttributeName.SHEETCOUNT, value, null);
	}

	/**
	 * (15) get int attribute SheetCount
	 * 
	 * @return int the value of the attribute
	 */
	public int getSheetCount()
	{
		return getIntAttribute(AttributeName.SHEETCOUNT, null, 0);
	}

	/*
	 * --------------------------------------------------------------------- Methods for Attribute TotalAmount ---------------------------------------------------------------------
	 */
	/**
	 * (36) set attribute TotalAmount
	 * 
	 * @param value the value to set the attribute to
	 */
	public void setTotalAmount(int value)
	{
		setAttribute(AttributeName.TOTALAMOUNT, value, null);
	}

	/**
	 * (15) get int attribute TotalAmount
	 * 
	 * @return int the value of the attribute
	 */
	public int getTotalAmount()
	{
		return getIntAttribute(AttributeName.TOTALAMOUNT, null, 0);
	}

	/*
	 * *********************************************************************** Element getter / setter ***********************************************************************
	 */

	/**
	 * (26) getCreateBundleItem
	 * 
	 * @param iSkip number of elements to skip
	 * @return JDFBundleItem the element
	 */
	public JDFBundleItem getCreateBundleItem(int iSkip)
	{
		return (JDFBundleItem) getCreateElement_JDFElement(ElementName.BUNDLEITEM, null, iSkip);
	}

	/**
	 * (27) const get element BundleItem
	 * 
	 * @param iSkip number of elements to skip
	 * @return JDFBundleItem the element default is getBundleItem(0)
	 */
	public JDFBundleItem getBundleItem(int iSkip)
	{
		return (JDFBundleItem) getElement(ElementName.BUNDLEITEM, null, iSkip);
	}

	/**
	 * Get all BundleItem from the current element
	 * 
	 * @return Collection<JDFBundleItem>, null if none are available
	 */
	public Collection<JDFBundleItem> getAllBundleItem()
	{
		return getChildrenByClass(JDFBundleItem.class, false, 0);
	}

	/**
	 * (30) append element BundleItem
	 * 
	 * @return JDFBundleItem the element
	 */
	public JDFBundleItem appendBundleItem()
	{
		return (JDFBundleItem) appendElement(ElementName.BUNDLEITEM, null);
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

	/**
	 * (26) getCreateIdentificationField
	 * 
	 * @param iSkip number of elements to skip
	 * @return JDFIdentificationField the element
	 */
	@Override
	public JDFIdentificationField getCreateIdentificationField(int iSkip)
	{
		return (JDFIdentificationField) getCreateElement_JDFElement(ElementName.IDENTIFICATIONFIELD, null, iSkip);
	}

	/**
	 * (27) const get element IdentificationField
	 * 
	 * @param iSkip number of elements to skip
	 * @return JDFIdentificationField the element default is getIdentificationField(0)
	 */
	@Override
	public JDFIdentificationField getIdentificationField(int iSkip)
	{
		return (JDFIdentificationField) getElement(ElementName.IDENTIFICATIONFIELD, null, iSkip);
	}

	/**
	 * Get all IdentificationField from the current element
	 * 
	 * @return Collection<JDFIdentificationField>, null if none are available
	 */
	public Collection<JDFIdentificationField> getAllIdentificationField()
	{
		return getChildrenByClass(JDFIdentificationField.class, false, 0);
	}

	/**
	 * (30) append element IdentificationField
	 * 
	 * @return JDFIdentificationField the element
	 */
	@Override
	public JDFIdentificationField appendIdentificationField()
	{
		return (JDFIdentificationField) appendElement(ElementName.IDENTIFICATIONFIELD, null);
	}

	/**
	 * (31) create inter-resource link to refTarget
	 * 
	 * @param refTarget the element that is referenced
	 */
	public void refIdentificationField(JDFIdentificationField refTarget)
	{
		refElement(refTarget);
	}

}// end namespace JDF
