/*
 * The CIP4 Software License, Version 1.0
 *
 *
 * Copyright (c) 2001-2014 The International Cooperation for the Integration of
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
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Vector;

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
import org.cip4.jdflib.core.JDFException;
import org.cip4.jdflib.core.JDFSeparationList;
import org.cip4.jdflib.core.VElement;
import org.cip4.jdflib.core.VString;
import org.cip4.jdflib.datatypes.JDFXYPair;
import org.cip4.jdflib.resource.JDFStrippingParams;
import org.cip4.jdflib.resource.process.JDFMedia;
import org.cip4.jdflib.resource.process.JDFRunList;
import org.cip4.jdflib.util.JDFDate;

/**
*****************************************************************************
class JDFAutoGangElement : public JDFElement

*****************************************************************************
*/

public abstract class JDFAutoGangElement extends JDFElement
{

	private static final long serialVersionUID = 1L;

	private static AtrInfoTable[] atrInfoTable = new AtrInfoTable[19];
	static
	{
		atrInfoTable[0] = new AtrInfoTable(AttributeName.ASSEMBLYIDS, 0x33311111, AttributeInfo.EnumAttributeType.NMTOKENS, null, null);
		atrInfoTable[1] = new AtrInfoTable(AttributeName.COLLAPSEBLEEDS, 0x33311111, AttributeInfo.EnumAttributeType.boolean_, null, null);
		atrInfoTable[2] = new AtrInfoTable(AttributeName.DIMENSION, 0x33311111, AttributeInfo.EnumAttributeType.XYPair, null, null);
		atrInfoTable[3] = new AtrInfoTable(AttributeName.DUEDATE, 0x33311111, AttributeInfo.EnumAttributeType.dateTime, null, null);
		atrInfoTable[4] = new AtrInfoTable(AttributeName.FILLPRIORITY, 0x33311111, AttributeInfo.EnumAttributeType.integer, null, null);
		atrInfoTable[5] = new AtrInfoTable(AttributeName.GANGELEMENTID, 0x22211111, AttributeInfo.EnumAttributeType.ID, null, null);
		atrInfoTable[6] = new AtrInfoTable(AttributeName.GRAINDIRECTION, 0x33311111, AttributeInfo.EnumAttributeType.enumeration, EnumGrainDirection.getEnum(0), null);
		atrInfoTable[7] = new AtrInfoTable(AttributeName.GROUPCODE, 0x33311111, AttributeInfo.EnumAttributeType.string, null, null);
		atrInfoTable[8] = new AtrInfoTable(AttributeName.JOBID, 0x33311111, AttributeInfo.EnumAttributeType.string, null, null);
		atrInfoTable[9] = new AtrInfoTable(AttributeName.MAXQUANTITY, 0x33311111, AttributeInfo.EnumAttributeType.integer, null, null);
		atrInfoTable[10] = new AtrInfoTable(AttributeName.MINQUANTITY, 0x33311111, AttributeInfo.EnumAttributeType.integer, null, null);
		atrInfoTable[11] = new AtrInfoTable(AttributeName.NPAGE, 0x33311111, AttributeInfo.EnumAttributeType.integer, null, null);
		atrInfoTable[12] = new AtrInfoTable(AttributeName.NUMCOLORS, 0x33311111, AttributeInfo.EnumAttributeType.XYPair, null, null);
		atrInfoTable[13] = new AtrInfoTable(AttributeName.NUMBERUP, 0x33311111, AttributeInfo.EnumAttributeType.XYPair, null, null);
		atrInfoTable[14] = new AtrInfoTable(AttributeName.ONESHEET, 0x33311111, AttributeInfo.EnumAttributeType.NMTOKEN, null, null);
		atrInfoTable[15] = new AtrInfoTable(AttributeName.ORDERQUANTITY, 0x22211111, AttributeInfo.EnumAttributeType.integer, null, null);
		atrInfoTable[16] = new AtrInfoTable(AttributeName.PAGEDIMENSION, 0x33311111, AttributeInfo.EnumAttributeType.XYPair, null, null);
		atrInfoTable[17] = new AtrInfoTable(AttributeName.PRIORITY, 0x33311111, AttributeInfo.EnumAttributeType.integer, null, null);
		atrInfoTable[18] = new AtrInfoTable(AttributeName.PRODUCTID, 0x33311111, AttributeInfo.EnumAttributeType.string, null, null);
	}

	@Override
	protected AttributeInfo getTheAttributeInfo()
	{
		return super.getTheAttributeInfo().updateReplace(atrInfoTable);
	}

	private static ElemInfoTable[] elemInfoTable = new ElemInfoTable[5];
	static
	{
		elemInfoTable[0] = new ElemInfoTable(ElementName.MEDIA, 0x22211111);
		elemInfoTable[1] = new ElemInfoTable(ElementName.RUNLIST, 0x22211111);
		elemInfoTable[2] = new ElemInfoTable(ElementName.SEPARATIONLISTBACK, 0x66611111);
		elemInfoTable[3] = new ElemInfoTable(ElementName.SEPARATIONLISTFRONT, 0x66611111);
		elemInfoTable[4] = new ElemInfoTable(ElementName.STRIPPINGPARAMS, 0x66611111);
	}

	@Override
	protected ElementInfo getTheElementInfo()
	{
		return super.getTheElementInfo().updateReplace(elemInfoTable);
	}

	/**
	 * Constructor for JDFAutoGangElement
	 * @param myOwnerDocument
	 * @param qualifiedName
	 */
	protected JDFAutoGangElement(CoreDocumentImpl myOwnerDocument, String qualifiedName)
	{
		super(myOwnerDocument, qualifiedName);
	}

	/**
	 * Constructor for JDFAutoGangElement
	 * @param myOwnerDocument
	 * @param myNamespaceURI
	 * @param qualifiedName
	 */
	protected JDFAutoGangElement(CoreDocumentImpl myOwnerDocument, String myNamespaceURI, String qualifiedName)
	{
		super(myOwnerDocument, myNamespaceURI, qualifiedName);
	}

	/**
	 * Constructor for JDFAutoGangElement
	 * @param myOwnerDocument
	 * @param myNamespaceURI
	 * @param qualifiedName
	 * @param myLocalName
	 */
	protected JDFAutoGangElement(CoreDocumentImpl myOwnerDocument, String myNamespaceURI, String qualifiedName, String myLocalName)
	{
		super(myOwnerDocument, myNamespaceURI, qualifiedName, myLocalName);
	}

	/**
	 * @return  the string representation
	 */
	@Override
	public String toString()
	{
		return " JDFAutoGangElement[  --> " + super.toString() + " ]";
	}

	/**
	* Enumeration strings for GrainDirection
	*/

	@SuppressWarnings("rawtypes")
	public static class EnumGrainDirection extends ValuedEnum
	{
		private static final long serialVersionUID = 1L;
		private static int m_startValue = 0;

		private EnumGrainDirection(String name)
		{
			super(name, m_startValue++);
		}

		/**
		 * @param enumName the string to convert
		 * @return the enum
		 */
		public static EnumGrainDirection getEnum(String enumName)
		{
			return (EnumGrainDirection) getEnum(EnumGrainDirection.class, enumName);
		}

		/**
		 * @param enumValue the integer to convert
		 * @return the enum
		 */
		public static EnumGrainDirection getEnum(int enumValue)
		{
			return (EnumGrainDirection) getEnum(EnumGrainDirection.class, enumValue);
		}

		/**
		 * @return the map of enums
		 */
		public static Map getEnumMap()
		{
			return getEnumMap(EnumGrainDirection.class);
		}

		/**
		 * @return the list of enums
		 */
		public static List getEnumList()
		{
			return getEnumList(EnumGrainDirection.class);
		}

		/**
		 * @return the iterator
		 */
		public static Iterator iterator()
		{
			return iterator(EnumGrainDirection.class);
		}

		/**  */
		public static final EnumGrainDirection Any = new EnumGrainDirection("Any");
		/**  */
		public static final EnumGrainDirection ShortEdge = new EnumGrainDirection("ShortEdge");
		/**  */
		public static final EnumGrainDirection LongEdge = new EnumGrainDirection("LongEdge");
		/**  */
		public static final EnumGrainDirection SameDirection = new EnumGrainDirection("SameDirection");
		/**  */
		public static final EnumGrainDirection XDirection = new EnumGrainDirection("XDirection");
		/**  */
		public static final EnumGrainDirection YDirection = new EnumGrainDirection("YDirection");
	}

	/* ************************************************************************
	 * Attribute getter / setter
	 * ************************************************************************
	 */

	/* ---------------------------------------------------------------------
	Methods for Attribute AssemblyIDs
	--------------------------------------------------------------------- */
	/**
	  * (36) set attribute AssemblyIDs
	  * @param value the value to set the attribute to
	  */
	public void setAssemblyIDs(VString value)
	{
		setAttribute(AttributeName.ASSEMBLYIDS, value, null);
	}

	/**
	  * (21) get VString attribute AssemblyIDs
	  * @return VString the value of the attribute
	  */
	public VString getAssemblyIDs()
	{
		final VString vStrAttrib = new VString();
		final String s = getAttribute(AttributeName.ASSEMBLYIDS, null, JDFCoreConstants.EMPTYSTRING);
		vStrAttrib.setAllStrings(s, " ");
		return vStrAttrib;
	}

	/* ---------------------------------------------------------------------
	Methods for Attribute CollapseBleeds
	--------------------------------------------------------------------- */
	/**
	  * (36) set attribute CollapseBleeds
	  * @param value the value to set the attribute to
	  */
	public void setCollapseBleeds(boolean value)
	{
		setAttribute(AttributeName.COLLAPSEBLEEDS, value, null);
	}

	/**
	  * (18) get boolean attribute CollapseBleeds
	  * @return boolean the value of the attribute
	  */
	public boolean getCollapseBleeds()
	{
		return getBoolAttribute(AttributeName.COLLAPSEBLEEDS, null, false);
	}

	/* ---------------------------------------------------------------------
	Methods for Attribute Dimension
	--------------------------------------------------------------------- */
	/**
	  * (36) set attribute Dimension
	  * @param value the value to set the attribute to
	  */
	public void setDimension(JDFXYPair value)
	{
		setAttribute(AttributeName.DIMENSION, value, null);
	}

	/**
	  * (20) get JDFXYPair attribute Dimension
	  * @return JDFXYPair the value of the attribute, null if a the
	  *         attribute value is not a valid to create a JDFXYPair
	  */
	public JDFXYPair getDimension()
	{
		final String strAttrName = getAttribute(AttributeName.DIMENSION, null, null);
		final JDFXYPair nPlaceHolder = JDFXYPair.createXYPair(strAttrName);
		return nPlaceHolder;
	}

	/* ---------------------------------------------------------------------
	Methods for Attribute DueDate
	--------------------------------------------------------------------- */
	/**
	  * (11) set attribute DueDate
	  * @param value the value to set the attribute to or null
	  */
	public void setDueDate(JDFDate value)
	{
		JDFDate date = value;
		if (date == null)
		{
			date = new JDFDate();
		}
		setAttribute(AttributeName.DUEDATE, date.getDateTimeISO(), null);
	}

	/**
	  * (12) get JDFDate attribute DueDate
	  * @return JDFDate the value of the attribute
	  */
	public JDFDate getDueDate()
	{
		final String str = getAttribute(AttributeName.DUEDATE, null, null);
		final JDFDate ret = JDFDate.createDate(str);
		return ret;
	}

	/* ---------------------------------------------------------------------
	Methods for Attribute FillPriority
	--------------------------------------------------------------------- */
	/**
	  * (36) set attribute FillPriority
	  * @param value the value to set the attribute to
	  */
	public void setFillPriority(int value)
	{
		setAttribute(AttributeName.FILLPRIORITY, value, null);
	}

	/**
	  * (15) get int attribute FillPriority
	  * @return int the value of the attribute
	  */
	public int getFillPriority()
	{
		return getIntAttribute(AttributeName.FILLPRIORITY, null, 0);
	}

	/* ---------------------------------------------------------------------
	Methods for Attribute GangElementID
	--------------------------------------------------------------------- */
	/**
	  * (36) set attribute GangElementID
	  * @param value the value to set the attribute to
	  */
	public void setGangElementID(String value)
	{
		setAttribute(AttributeName.GANGELEMENTID, value, null);
	}

	/**
	  * (23) get String attribute GangElementID
	  * @return the value of the attribute
	  */
	public String getGangElementID()
	{
		return getAttribute(AttributeName.GANGELEMENTID, null, JDFCoreConstants.EMPTYSTRING);
	}

	/* ---------------------------------------------------------------------
	Methods for Attribute GrainDirection
	--------------------------------------------------------------------- */
	/**
	  * (5) set attribute GrainDirection
	  * @param enumVar the enumVar to set the attribute to
	  */
	public void setGrainDirection(EnumGrainDirection enumVar)
	{
		setAttribute(AttributeName.GRAINDIRECTION, enumVar == null ? null : enumVar.getName(), null);
	}

	/**
	  * (9) get attribute GrainDirection
	  * @return the value of the attribute
	  */
	public EnumGrainDirection getGrainDirection()
	{
		return EnumGrainDirection.getEnum(getAttribute(AttributeName.GRAINDIRECTION, null, null));
	}

	/* ---------------------------------------------------------------------
	Methods for Attribute GroupCode
	--------------------------------------------------------------------- */
	/**
	  * (36) set attribute GroupCode
	  * @param value the value to set the attribute to
	  */
	public void setGroupCode(String value)
	{
		setAttribute(AttributeName.GROUPCODE, value, null);
	}

	/**
	  * (23) get String attribute GroupCode
	  * @return the value of the attribute
	  */
	public String getGroupCode()
	{
		return getAttribute(AttributeName.GROUPCODE, null, JDFCoreConstants.EMPTYSTRING);
	}

	/* ---------------------------------------------------------------------
	Methods for Attribute JobID
	--------------------------------------------------------------------- */
	/**
	  * (36) set attribute JobID
	  * @param value the value to set the attribute to
	  */
	public void setJobID(String value)
	{
		setAttribute(AttributeName.JOBID, value, null);
	}

	/**
	  * (23) get String attribute JobID
	  * @return the value of the attribute
	  */
	public String getJobID()
	{
		return getAttribute(AttributeName.JOBID, null, JDFCoreConstants.EMPTYSTRING);
	}

	/* ---------------------------------------------------------------------
	Methods for Attribute MaxQuantity
	--------------------------------------------------------------------- */
	/**
	  * (36) set attribute MaxQuantity
	  * @param value the value to set the attribute to
	  */
	public void setMaxQuantity(int value)
	{
		setAttribute(AttributeName.MAXQUANTITY, value, null);
	}

	/**
	  * (15) get int attribute MaxQuantity
	  * @return int the value of the attribute
	  */
	public int getMaxQuantity()
	{
		return getIntAttribute(AttributeName.MAXQUANTITY, null, 0);
	}

	/* ---------------------------------------------------------------------
	Methods for Attribute MinQuantity
	--------------------------------------------------------------------- */
	/**
	  * (36) set attribute MinQuantity
	  * @param value the value to set the attribute to
	  */
	public void setMinQuantity(int value)
	{
		setAttribute(AttributeName.MINQUANTITY, value, null);
	}

	/**
	  * (15) get int attribute MinQuantity
	  * @return int the value of the attribute
	  */
	public int getMinQuantity()
	{
		return getIntAttribute(AttributeName.MINQUANTITY, null, 0);
	}

	/* ---------------------------------------------------------------------
	Methods for Attribute NPage
	--------------------------------------------------------------------- */
	/**
	  * (36) set attribute NPage
	  * @param value the value to set the attribute to
	  */
	public void setNPage(int value)
	{
		setAttribute(AttributeName.NPAGE, value, null);
	}

	/**
	  * (15) get int attribute NPage
	  * @return int the value of the attribute
	  */
	public int getNPage()
	{
		return getIntAttribute(AttributeName.NPAGE, null, 0);
	}

	/* ---------------------------------------------------------------------
	Methods for Attribute NumColors
	--------------------------------------------------------------------- */
	/**
	  * (36) set attribute NumColors
	  * @param value the value to set the attribute to
	  */
	public void setNumColors(JDFXYPair value)
	{
		setAttribute(AttributeName.NUMCOLORS, value, null);
	}

	/**
	  * (20) get JDFXYPair attribute NumColors
	  * @return JDFXYPair the value of the attribute, null if a the
	  *         attribute value is not a valid to create a JDFXYPair
	  */
	public JDFXYPair getNumColors()
	{
		final String strAttrName = getAttribute(AttributeName.NUMCOLORS, null, null);
		final JDFXYPair nPlaceHolder = JDFXYPair.createXYPair(strAttrName);
		return nPlaceHolder;
	}

	/* ---------------------------------------------------------------------
	Methods for Attribute NumberUp
	--------------------------------------------------------------------- */
	/**
	  * (36) set attribute NumberUp
	  * @param value the value to set the attribute to
	  */
	public void setNumberUp(JDFXYPair value)
	{
		setAttribute(AttributeName.NUMBERUP, value, null);
	}

	/**
	  * (20) get JDFXYPair attribute NumberUp
	  * @return JDFXYPair the value of the attribute, null if a the
	  *         attribute value is not a valid to create a JDFXYPair
	  */
	public JDFXYPair getNumberUp()
	{
		final String strAttrName = getAttribute(AttributeName.NUMBERUP, null, null);
		final JDFXYPair nPlaceHolder = JDFXYPair.createXYPair(strAttrName);
		return nPlaceHolder;
	}

	/* ---------------------------------------------------------------------
	Methods for Attribute OneSheet
	--------------------------------------------------------------------- */
	/**
	  * (36) set attribute OneSheet
	  * @param value the value to set the attribute to
	  */
	public void setOneSheet(String value)
	{
		setAttribute(AttributeName.ONESHEET, value, null);
	}

	/**
	  * (23) get String attribute OneSheet
	  * @return the value of the attribute
	  */
	public String getOneSheet()
	{
		return getAttribute(AttributeName.ONESHEET, null, JDFCoreConstants.EMPTYSTRING);
	}

	/* ---------------------------------------------------------------------
	Methods for Attribute OrderQuantity
	--------------------------------------------------------------------- */
	/**
	  * (36) set attribute OrderQuantity
	  * @param value the value to set the attribute to
	  */
	public void setOrderQuantity(int value)
	{
		setAttribute(AttributeName.ORDERQUANTITY, value, null);
	}

	/**
	  * (15) get int attribute OrderQuantity
	  * @return int the value of the attribute
	  */
	public int getOrderQuantity()
	{
		return getIntAttribute(AttributeName.ORDERQUANTITY, null, 0);
	}

	/* ---------------------------------------------------------------------
	Methods for Attribute PageDimension
	--------------------------------------------------------------------- */
	/**
	  * (36) set attribute PageDimension
	  * @param value the value to set the attribute to
	  */
	public void setPageDimension(JDFXYPair value)
	{
		setAttribute(AttributeName.PAGEDIMENSION, value, null);
	}

	/**
	  * (20) get JDFXYPair attribute PageDimension
	  * @return JDFXYPair the value of the attribute, null if a the
	  *         attribute value is not a valid to create a JDFXYPair
	  */
	public JDFXYPair getPageDimension()
	{
		final String strAttrName = getAttribute(AttributeName.PAGEDIMENSION, null, null);
		final JDFXYPair nPlaceHolder = JDFXYPair.createXYPair(strAttrName);
		return nPlaceHolder;
	}

	/* ---------------------------------------------------------------------
	Methods for Attribute Priority
	--------------------------------------------------------------------- */
	/**
	  * (36) set attribute Priority
	  * @param value the value to set the attribute to
	  */
	public void setPriority(int value)
	{
		setAttribute(AttributeName.PRIORITY, value, null);
	}

	/**
	  * (15) get int attribute Priority
	  * @return int the value of the attribute
	  */
	public int getPriority()
	{
		return getIntAttribute(AttributeName.PRIORITY, null, 0);
	}

	/* ---------------------------------------------------------------------
	Methods for Attribute ProductID
	--------------------------------------------------------------------- */
	/**
	  * (36) set attribute ProductID
	  * @param value the value to set the attribute to
	  */
	public void setProductID(String value)
	{
		setAttribute(AttributeName.PRODUCTID, value, null);
	}

	/**
	  * (23) get String attribute ProductID
	  * @return the value of the attribute
	  */
	public String getProductID()
	{
		return getAttribute(AttributeName.PRODUCTID, null, JDFCoreConstants.EMPTYSTRING);
	}

	/* ***********************************************************************
	 * Element getter / setter
	 * ***********************************************************************
	 */

	/** (26) getCreateMedia
	 * 
	 * @param iSkip number of elements to skip
	 * @return JDFMedia the element
	 */
	public JDFMedia getCreateMedia(int iSkip)
	{
		return (JDFMedia) getCreateElement_KElement(ElementName.MEDIA, null, iSkip);
	}

	/**
	 * (27) const get element Media
	 * @param iSkip number of elements to skip
	 * @return JDFMedia the element
	 * default is getMedia(0)     */
	public JDFMedia getMedia(int iSkip)
	{
		return (JDFMedia) getElement(ElementName.MEDIA, null, iSkip);
	}

	/**
	 * Get all Media from the current element
	 * 
	 * @return Collection<JDFMedia>, null if none are available
	 */
	public Collection<JDFMedia> getAllMedia()
	{
		final VElement vc = getChildElementVector(ElementName.MEDIA, null);
		if (vc == null || vc.size() == 0)
		{
			return null;
		}

		final Vector<JDFMedia> v = new Vector<JDFMedia>();
		for (int i = 0; i < vc.size(); i++)
		{
			v.add((JDFMedia) vc.get(i));
		}

		return v;
	}

	/**
	 * (30) append element Media
	 * @return JDFMedia the element
	 */
	public JDFMedia appendMedia()
	{
		return (JDFMedia) appendElement(ElementName.MEDIA, null);
	}

	/**
	  * (31) create inter-resource link to refTarget
	  * @param refTarget the element that is referenced
	  */
	public void refMedia(JDFMedia refTarget)
	{
		refElement(refTarget);
	}

	/** (26) getCreateRunList
	 * 
	 * @param iSkip number of elements to skip
	 * @return JDFRunList the element
	 */
	public JDFRunList getCreateRunList(int iSkip)
	{
		return (JDFRunList) getCreateElement_KElement(ElementName.RUNLIST, null, iSkip);
	}

	/**
	 * (27) const get element RunList
	 * @param iSkip number of elements to skip
	 * @return JDFRunList the element
	 * default is getRunList(0)     */
	public JDFRunList getRunList(int iSkip)
	{
		return (JDFRunList) getElement(ElementName.RUNLIST, null, iSkip);
	}

	/**
	 * Get all RunList from the current element
	 * 
	 * @return Collection<JDFRunList>, null if none are available
	 */
	public Collection<JDFRunList> getAllRunList()
	{
		final VElement vc = getChildElementVector(ElementName.RUNLIST, null);
		if (vc == null || vc.size() == 0)
		{
			return null;
		}

		final Vector<JDFRunList> v = new Vector<JDFRunList>();
		for (int i = 0; i < vc.size(); i++)
		{
			v.add((JDFRunList) vc.get(i));
		}

		return v;
	}

	/**
	 * (30) append element RunList
	 * @return JDFRunList the element
	 */
	public JDFRunList appendRunList()
	{
		return (JDFRunList) appendElement(ElementName.RUNLIST, null);
	}

	/**
	  * (31) create inter-resource link to refTarget
	  * @param refTarget the element that is referenced
	  */
	public void refRunList(JDFRunList refTarget)
	{
		refElement(refTarget);
	}

	/**
	 * (24) const get element SeparationListBack
	 * @return JDFSeparationList the element
	 */
	public JDFSeparationList getSeparationListBack()
	{
		return (JDFSeparationList) getElement(ElementName.SEPARATIONLISTBACK, null, 0);
	}

	/** (25) getCreateSeparationListBack
	 * 
	 * @return JDFSeparationList the element
	 */
	public JDFSeparationList getCreateSeparationListBack()
	{
		return (JDFSeparationList) getCreateElement_KElement(ElementName.SEPARATIONLISTBACK, null, 0);
	}

	/**
	 * (29) append element SeparationListBack
	 * @return JDFSeparationList the element
	 * @throws JDFException if the element already exists
	 */
	public JDFSeparationList appendSeparationListBack() throws JDFException
	{
		return (JDFSeparationList) appendElementN(ElementName.SEPARATIONLISTBACK, 1, null);
	}

	/**
	 * (24) const get element SeparationListFront
	 * @return JDFSeparationList the element
	 */
	public JDFSeparationList getSeparationListFront()
	{
		return (JDFSeparationList) getElement(ElementName.SEPARATIONLISTFRONT, null, 0);
	}

	/** (25) getCreateSeparationListFront
	 * 
	 * @return JDFSeparationList the element
	 */
	public JDFSeparationList getCreateSeparationListFront()
	{
		return (JDFSeparationList) getCreateElement_KElement(ElementName.SEPARATIONLISTFRONT, null, 0);
	}

	/**
	 * (29) append element SeparationListFront
	 * @return JDFSeparationList the element
	 * @throws JDFException if the element already exists
	 */
	public JDFSeparationList appendSeparationListFront() throws JDFException
	{
		return (JDFSeparationList) appendElementN(ElementName.SEPARATIONLISTFRONT, 1, null);
	}

	/**
	 * (24) const get element StrippingParams
	 * @return JDFStrippingParams the element
	 */
	public JDFStrippingParams getStrippingParams()
	{
		return (JDFStrippingParams) getElement(ElementName.STRIPPINGPARAMS, null, 0);
	}

	/** (25) getCreateStrippingParams
	 * 
	 * @return JDFStrippingParams the element
	 */
	public JDFStrippingParams getCreateStrippingParams()
	{
		return (JDFStrippingParams) getCreateElement_KElement(ElementName.STRIPPINGPARAMS, null, 0);
	}

	/**
	 * (29) append element StrippingParams
	 * @return JDFStrippingParams the element
	 * @throws JDFException if the element already exists
	 */
	public JDFStrippingParams appendStrippingParams() throws JDFException
	{
		return (JDFStrippingParams) appendElementN(ElementName.STRIPPINGPARAMS, 1, null);
	}

	/**
	  * (31) create inter-resource link to refTarget
	  * @param refTarget the element that is referenced
	  */
	public void refStrippingParams(JDFStrippingParams refTarget)
	{
		refElement(refTarget);
	}

}// end namespace JDF
