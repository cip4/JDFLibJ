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
import org.cip4.jdflib.core.JDFException;
import org.cip4.jdflib.core.VElement;
import org.cip4.jdflib.core.VString;
import org.cip4.jdflib.datatypes.JDFIntegerList;
import org.cip4.jdflib.resource.JDFDevice;
import org.cip4.jdflib.resource.JDFResource;
import org.cip4.jdflib.resource.process.JDFBinderySignature;
import org.cip4.jdflib.resource.process.JDFExternalImpositionTemplate;
import org.cip4.jdflib.resource.process.JDFMedia;
import org.cip4.jdflib.resource.process.JDFPosition;
import org.cip4.jdflib.resource.process.JDFStripCellParams;
import org.cip4.jdflib.resource.process.JDFStripMark;

/**
*****************************************************************************
class JDFAutoStrippingParams : public JDFResource

*****************************************************************************
*/

public abstract class JDFAutoStrippingParams extends JDFResource
{

	private static final long serialVersionUID = 1L;

	private static AtrInfoTable[] atrInfoTable = new AtrInfoTable[12];
	static
	{
		atrInfoTable[0] = new AtrInfoTable(AttributeName.ASSEMBLYID, 0x44444311, AttributeInfo.EnumAttributeType.string, null, null);
		atrInfoTable[1] = new AtrInfoTable(AttributeName.ASSEMBLYIDS, 0x33333111, AttributeInfo.EnumAttributeType.NMTOKENS, null, null);
		atrInfoTable[2] = new AtrInfoTable(AttributeName.AUTOMATED, 0x33311111, AttributeInfo.EnumAttributeType.boolean_, null, null);
		atrInfoTable[3] = new AtrInfoTable(AttributeName.GANGELEMENTID, 0x33311111, AttributeInfo.EnumAttributeType.NMTOKEN, null, null);
		atrInfoTable[4] = new AtrInfoTable(AttributeName.INNERMOSTSHINGLING, 0x33331111, AttributeInfo.EnumAttributeType.double_, null, null);
		atrInfoTable[5] = new AtrInfoTable(AttributeName.JOBID, 0x33333311, AttributeInfo.EnumAttributeType.shortString, null, null);
		atrInfoTable[6] = new AtrInfoTable(AttributeName.OUTERMOSTSHINGLING, 0x33331111, AttributeInfo.EnumAttributeType.double_, null, null);
		atrInfoTable[7] = new AtrInfoTable(AttributeName.SECTIONLIST, 0x33333311, AttributeInfo.EnumAttributeType.IntegerList, null, null);
		atrInfoTable[8] = new AtrInfoTable(AttributeName.SHEETNAMEFORMAT, 0x33331111, AttributeInfo.EnumAttributeType.string, null, null);
		atrInfoTable[9] = new AtrInfoTable(AttributeName.SHEETNAMETEMPLATE, 0x33331111, AttributeInfo.EnumAttributeType.string, null, null);
		atrInfoTable[10] = new AtrInfoTable(AttributeName.STACKDEPTH, 0x33331111, AttributeInfo.EnumAttributeType.integer, null, null);
		atrInfoTable[11] = new AtrInfoTable(AttributeName.WORKSTYLE, 0x33333311, AttributeInfo.EnumAttributeType.enumeration, EnumWorkStyle.getEnum(0), null);
	}

	@Override
	protected AttributeInfo getTheAttributeInfo()
	{
		return super.getTheAttributeInfo().updateReplace(atrInfoTable);
	}

	private static ElemInfoTable[] elemInfoTable = new ElemInfoTable[7];
	static
	{
		elemInfoTable[0] = new ElemInfoTable(ElementName.BINDERYSIGNATURE, 0x66666611);
		elemInfoTable[1] = new ElemInfoTable(ElementName.DEVICE, 0x33333311);
		elemInfoTable[2] = new ElemInfoTable(ElementName.EXTERNALIMPOSITIONTEMPLATE, 0x66666111);
		elemInfoTable[3] = new ElemInfoTable(ElementName.MEDIA, 0x33333311);
		elemInfoTable[4] = new ElemInfoTable(ElementName.POSITION, 0x33333311);
		elemInfoTable[5] = new ElemInfoTable(ElementName.STRIPCELLPARAMS, 0x66666611);
		elemInfoTable[6] = new ElemInfoTable(ElementName.STRIPMARK, 0x33333111);
	}

	@Override
	protected ElementInfo getTheElementInfo()
	{
		return super.getTheElementInfo().updateReplace(elemInfoTable);
	}

	/**
	 * Constructor for JDFAutoStrippingParams
	 * @param myOwnerDocument
	 * @param qualifiedName
	 */
	protected JDFAutoStrippingParams(CoreDocumentImpl myOwnerDocument, String qualifiedName)
	{
		super(myOwnerDocument, qualifiedName);
	}

	/**
	 * Constructor for JDFAutoStrippingParams
	 * @param myOwnerDocument
	 * @param myNamespaceURI
	 * @param qualifiedName
	 */
	protected JDFAutoStrippingParams(CoreDocumentImpl myOwnerDocument, String myNamespaceURI, String qualifiedName)
	{
		super(myOwnerDocument, myNamespaceURI, qualifiedName);
	}

	/**
	 * Constructor for JDFAutoStrippingParams
	 * @param myOwnerDocument
	 * @param myNamespaceURI
	 * @param qualifiedName
	 * @param myLocalName
	 */
	protected JDFAutoStrippingParams(CoreDocumentImpl myOwnerDocument, String myNamespaceURI, String qualifiedName, String myLocalName)
	{
		super(myOwnerDocument, myNamespaceURI, qualifiedName, myLocalName);
	}

	/**
	 * @return  the string representation
	 */
	@Override
	public String toString()
	{
		return " JDFAutoStrippingParams[  --> " + super.toString() + " ]";
	}

	/**
	 * @return  true if ok
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
	* Enumeration strings for WorkStyle
	*/

	@SuppressWarnings("rawtypes")
	public static class EnumWorkStyle extends ValuedEnum
	{
		private static final long serialVersionUID = 1L;
		private static int m_startValue = 0;

		private EnumWorkStyle(String name)
		{
			super(name, m_startValue++);
		}

		/**
		 * @param enumName the string to convert
		 * @return the enum
		 */
		public static EnumWorkStyle getEnum(String enumName)
		{
			return (EnumWorkStyle) getEnum(EnumWorkStyle.class, enumName);
		}

		/**
		 * @param enumValue the integer to convert
		 * @return the enum
		 */
		public static EnumWorkStyle getEnum(int enumValue)
		{
			return (EnumWorkStyle) getEnum(EnumWorkStyle.class, enumValue);
		}

		/**
		 * @return the map of enums
		 */
		public static Map getEnumMap()
		{
			return getEnumMap(EnumWorkStyle.class);
		}

		/**
		 * @return the list of enums
		 */
		public static List getEnumList()
		{
			return getEnumList(EnumWorkStyle.class);
		}

		/**
		 * @return the iterator
		 */
		public static Iterator iterator()
		{
			return iterator(EnumWorkStyle.class);
		}

		/**  */
		public static final EnumWorkStyle Simplex = new EnumWorkStyle("Simplex");
		/**  */
		public static final EnumWorkStyle Perfecting = new EnumWorkStyle("Perfecting");
		/**  */
		public static final EnumWorkStyle WorkAndBack = new EnumWorkStyle("WorkAndBack");
		/**  */
		public static final EnumWorkStyle WorkAndTurn = new EnumWorkStyle("WorkAndTurn");
		/**  */
		public static final EnumWorkStyle WorkAndTumble = new EnumWorkStyle("WorkAndTumble");
		/**  */
		public static final EnumWorkStyle WorkAndTwist = new EnumWorkStyle("WorkAndTwist");
	}

	/* ************************************************************************
	 * Attribute getter / setter
	 * ************************************************************************
	 */

	/* ---------------------------------------------------------------------
	Methods for Attribute AssemblyID
	--------------------------------------------------------------------- */
	/**
	  * (36) set attribute AssemblyID
	  * @param value the value to set the attribute to
	  */
	public void setAssemblyID(String value)
	{
		setAttribute(AttributeName.ASSEMBLYID, value, null);
	}

	/**
	  * (23) get String attribute AssemblyID
	  * @return the value of the attribute
	  */
	public String getAssemblyID()
	{
		return getAttribute(AttributeName.ASSEMBLYID, null, JDFCoreConstants.EMPTYSTRING);
	}

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
	Methods for Attribute Automated
	--------------------------------------------------------------------- */
	/**
	  * (36) set attribute Automated
	  * @param value the value to set the attribute to
	  */
	public void setAutomated(boolean value)
	{
		setAttribute(AttributeName.AUTOMATED, value, null);
	}

	/**
	  * (18) get boolean attribute Automated
	  * @return boolean the value of the attribute
	  */
	public boolean getAutomated()
	{
		return getBoolAttribute(AttributeName.AUTOMATED, null, false);
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
	Methods for Attribute InnermostShingling
	--------------------------------------------------------------------- */
	/**
	  * (36) set attribute InnermostShingling
	  * @param value the value to set the attribute to
	  */
	public void setInnermostShingling(double value)
	{
		setAttribute(AttributeName.INNERMOSTSHINGLING, value, null);
	}

	/**
	  * (17) get double attribute InnermostShingling
	  * @return double the value of the attribute
	  */
	public double getInnermostShingling()
	{
		return getRealAttribute(AttributeName.INNERMOSTSHINGLING, null, 0.0);
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
	Methods for Attribute OutermostShingling
	--------------------------------------------------------------------- */
	/**
	  * (36) set attribute OutermostShingling
	  * @param value the value to set the attribute to
	  */
	public void setOutermostShingling(double value)
	{
		setAttribute(AttributeName.OUTERMOSTSHINGLING, value, null);
	}

	/**
	  * (17) get double attribute OutermostShingling
	  * @return double the value of the attribute
	  */
	public double getOutermostShingling()
	{
		return getRealAttribute(AttributeName.OUTERMOSTSHINGLING, null, 0.0);
	}

	/* ---------------------------------------------------------------------
	Methods for Attribute SectionList
	--------------------------------------------------------------------- */
	/**
	  * (36) set attribute SectionList
	  * @param value the value to set the attribute to
	  */
	public void setSectionList(JDFIntegerList value)
	{
		setAttribute(AttributeName.SECTIONLIST, value, null);
	}

	/**
	  * (20) get JDFIntegerList attribute SectionList
	  * @return JDFIntegerList the value of the attribute, null if a the
	  *         attribute value is not a valid to create a JDFIntegerList
	  */
	public JDFIntegerList getSectionList()
	{
		final String strAttrName = getAttribute(AttributeName.SECTIONLIST, null, null);
		final JDFIntegerList nPlaceHolder = JDFIntegerList.createIntegerList(strAttrName);
		return nPlaceHolder;
	}

	/* ---------------------------------------------------------------------
	Methods for Attribute SheetNameFormat
	--------------------------------------------------------------------- */
	/**
	  * (36) set attribute SheetNameFormat
	  * @param value the value to set the attribute to
	  */
	public void setSheetNameFormat(String value)
	{
		setAttribute(AttributeName.SHEETNAMEFORMAT, value, null);
	}

	/**
	  * (23) get String attribute SheetNameFormat
	  * @return the value of the attribute
	  */
	public String getSheetNameFormat()
	{
		return getAttribute(AttributeName.SHEETNAMEFORMAT, null, JDFCoreConstants.EMPTYSTRING);
	}

	/* ---------------------------------------------------------------------
	Methods for Attribute SheetNameTemplate
	--------------------------------------------------------------------- */
	/**
	  * (36) set attribute SheetNameTemplate
	  * @param value the value to set the attribute to
	  */
	public void setSheetNameTemplate(String value)
	{
		setAttribute(AttributeName.SHEETNAMETEMPLATE, value, null);
	}

	/**
	  * (23) get String attribute SheetNameTemplate
	  * @return the value of the attribute
	  */
	public String getSheetNameTemplate()
	{
		return getAttribute(AttributeName.SHEETNAMETEMPLATE, null, JDFCoreConstants.EMPTYSTRING);
	}

	/* ---------------------------------------------------------------------
	Methods for Attribute StackDepth
	--------------------------------------------------------------------- */
	/**
	  * (36) set attribute StackDepth
	  * @param value the value to set the attribute to
	  */
	public void setStackDepth(int value)
	{
		setAttribute(AttributeName.STACKDEPTH, value, null);
	}

	/**
	  * (15) get int attribute StackDepth
	  * @return int the value of the attribute
	  */
	public int getStackDepth()
	{
		return getIntAttribute(AttributeName.STACKDEPTH, null, 0);
	}

	/* ---------------------------------------------------------------------
	Methods for Attribute WorkStyle
	--------------------------------------------------------------------- */
	/**
	  * (5) set attribute WorkStyle
	  * @param enumVar the enumVar to set the attribute to
	  */
	public void setWorkStyle(EnumWorkStyle enumVar)
	{
		setAttribute(AttributeName.WORKSTYLE, enumVar == null ? null : enumVar.getName(), null);
	}

	/**
	  * (9) get attribute WorkStyle
	  * @return the value of the attribute
	  */
	public EnumWorkStyle getWorkStyle()
	{
		return EnumWorkStyle.getEnum(getAttribute(AttributeName.WORKSTYLE, null, null));
	}

	/* ***********************************************************************
	 * Element getter / setter
	 * ***********************************************************************
	 */

	/**
	 * (24) const get element BinderySignature
	 * @return JDFBinderySignature the element
	 */
	public JDFBinderySignature getBinderySignature()
	{
		return (JDFBinderySignature) getElement(ElementName.BINDERYSIGNATURE, null, 0);
	}

	/** (25) getCreateBinderySignature
	 * 
	 * @return JDFBinderySignature the element
	 */
	public JDFBinderySignature getCreateBinderySignature()
	{
		return (JDFBinderySignature) getCreateElement_KElement(ElementName.BINDERYSIGNATURE, null, 0);
	}

	/**
	 * (29) append element BinderySignature
	 * @return JDFBinderySignature the element
	 * @throws JDFException if the element already exists
	 */
	public JDFBinderySignature appendBinderySignature() throws JDFException
	{
		return (JDFBinderySignature) appendElementN(ElementName.BINDERYSIGNATURE, 1, null);
	}

	/**
	  * (31) create inter-resource link to refTarget
	  * @param refTarget the element that is referenced
	  */
	public void refBinderySignature(JDFBinderySignature refTarget)
	{
		refElement(refTarget);
	}

	/** (26) getCreateDevice
	 * 
	 * @param iSkip number of elements to skip
	 * @return JDFDevice the element
	 */
	public JDFDevice getCreateDevice(int iSkip)
	{
		return (JDFDevice) getCreateElement_KElement(ElementName.DEVICE, null, iSkip);
	}

	/**
	 * (27) const get element Device
	 * @param iSkip number of elements to skip
	 * @return JDFDevice the element
	 * default is getDevice(0)     */
	public JDFDevice getDevice(int iSkip)
	{
		return (JDFDevice) getElement(ElementName.DEVICE, null, iSkip);
	}

	/**
	 * Get all Device from the current element
	 * 
	 * @return Collection<JDFDevice>, null if none are available
	 */
	public Collection<JDFDevice> getAllDevice()
	{
		final VElement vc = getChildElementVector(ElementName.DEVICE, null);
		if (vc == null || vc.size() == 0)
		{
			return null;
		}

		final Vector<JDFDevice> v = new Vector<JDFDevice>();
		for (int i = 0; i < vc.size(); i++)
		{
			v.add((JDFDevice) vc.get(i));
		}

		return v;
	}

	/**
	 * (30) append element Device
	 * @return JDFDevice the element
	 */
	public JDFDevice appendDevice()
	{
		return (JDFDevice) appendElement(ElementName.DEVICE, null);
	}

	/**
	  * (31) create inter-resource link to refTarget
	  * @param refTarget the element that is referenced
	  */
	public void refDevice(JDFDevice refTarget)
	{
		refElement(refTarget);
	}

	/**
	 * (24) const get element ExternalImpositionTemplate
	 * @return JDFExternalImpositionTemplate the element
	 */
	public JDFExternalImpositionTemplate getExternalImpositionTemplate()
	{
		return (JDFExternalImpositionTemplate) getElement(ElementName.EXTERNALIMPOSITIONTEMPLATE, null, 0);
	}

	/** (25) getCreateExternalImpositionTemplate
	 * 
	 * @return JDFExternalImpositionTemplate the element
	 */
	public JDFExternalImpositionTemplate getCreateExternalImpositionTemplate()
	{
		return (JDFExternalImpositionTemplate) getCreateElement_KElement(ElementName.EXTERNALIMPOSITIONTEMPLATE, null, 0);
	}

	/**
	 * (29) append element ExternalImpositionTemplate
	 * @return JDFExternalImpositionTemplate the element
	 * @throws JDFException if the element already exists
	 */
	public JDFExternalImpositionTemplate appendExternalImpositionTemplate() throws JDFException
	{
		return (JDFExternalImpositionTemplate) appendElementN(ElementName.EXTERNALIMPOSITIONTEMPLATE, 1, null);
	}

	/**
	  * (31) create inter-resource link to refTarget
	  * @param refTarget the element that is referenced
	  */
	public void refExternalImpositionTemplate(JDFExternalImpositionTemplate refTarget)
	{
		refElement(refTarget);
	}

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

	/** (26) getCreatePosition
	 * 
	 * @param iSkip number of elements to skip
	 * @return JDFPosition the element
	 */
	public JDFPosition getCreatePosition(int iSkip)
	{
		return (JDFPosition) getCreateElement_KElement(ElementName.POSITION, null, iSkip);
	}

	/**
	 * (27) const get element Position
	 * @param iSkip number of elements to skip
	 * @return JDFPosition the element
	 * default is getPosition(0)     */
	public JDFPosition getPosition(int iSkip)
	{
		return (JDFPosition) getElement(ElementName.POSITION, null, iSkip);
	}

	/**
	 * Get all Position from the current element
	 * 
	 * @return Collection<JDFPosition>, null if none are available
	 */
	public Collection<JDFPosition> getAllPosition()
	{
		final VElement vc = getChildElementVector(ElementName.POSITION, null);
		if (vc == null || vc.size() == 0)
		{
			return null;
		}

		final Vector<JDFPosition> v = new Vector<JDFPosition>();
		for (int i = 0; i < vc.size(); i++)
		{
			v.add((JDFPosition) vc.get(i));
		}

		return v;
	}

	/**
	 * (30) append element Position
	 * @return JDFPosition the element
	 */
	public JDFPosition appendPosition()
	{
		return (JDFPosition) appendElement(ElementName.POSITION, null);
	}

	/**
	 * (24) const get element StripCellParams
	 * @return JDFStripCellParams the element
	 */
	public JDFStripCellParams getStripCellParams()
	{
		return (JDFStripCellParams) getElement(ElementName.STRIPCELLPARAMS, null, 0);
	}

	/** (25) getCreateStripCellParams
	 * 
	 * @return JDFStripCellParams the element
	 */
	public JDFStripCellParams getCreateStripCellParams()
	{
		return (JDFStripCellParams) getCreateElement_KElement(ElementName.STRIPCELLPARAMS, null, 0);
	}

	/**
	 * (29) append element StripCellParams
	 * @return JDFStripCellParams the element
	 * @throws JDFException if the element already exists
	 */
	public JDFStripCellParams appendStripCellParams() throws JDFException
	{
		return (JDFStripCellParams) appendElementN(ElementName.STRIPCELLPARAMS, 1, null);
	}

	/** (26) getCreateStripMark
	 * 
	 * @param iSkip number of elements to skip
	 * @return JDFStripMark the element
	 */
	public JDFStripMark getCreateStripMark(int iSkip)
	{
		return (JDFStripMark) getCreateElement_KElement(ElementName.STRIPMARK, null, iSkip);
	}

	/**
	 * (27) const get element StripMark
	 * @param iSkip number of elements to skip
	 * @return JDFStripMark the element
	 * default is getStripMark(0)     */
	public JDFStripMark getStripMark(int iSkip)
	{
		return (JDFStripMark) getElement(ElementName.STRIPMARK, null, iSkip);
	}

	/**
	 * Get all StripMark from the current element
	 * 
	 * @return Collection<JDFStripMark>, null if none are available
	 */
	public Collection<JDFStripMark> getAllStripMark()
	{
		final VElement vc = getChildElementVector(ElementName.STRIPMARK, null);
		if (vc == null || vc.size() == 0)
		{
			return null;
		}

		final Vector<JDFStripMark> v = new Vector<JDFStripMark>();
		for (int i = 0; i < vc.size(); i++)
		{
			v.add((JDFStripMark) vc.get(i));
		}

		return v;
	}

	/**
	 * (30) append element StripMark
	 * @return JDFStripMark the element
	 */
	public JDFStripMark appendStripMark()
	{
		return (JDFStripMark) appendElement(ElementName.STRIPMARK, null);
	}

}// end namespace JDF
