/*
 * The CIP4 Software License, Version 1.0
 *
 *
 * Copyright (c) 2001-2025 The International Cooperation for the Integration of
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

import org.apache.commons.lang.enums.ValuedEnum;
import org.apache.xerces.dom.CoreDocumentImpl;
import org.cip4.jdflib.core.AtrInfoTable;
import org.cip4.jdflib.core.AttributeInfo;
import org.cip4.jdflib.core.AttributeName;
import org.cip4.jdflib.core.ElemInfoTable;
import org.cip4.jdflib.core.ElementInfo;
import org.cip4.jdflib.core.ElementName;
import org.cip4.jdflib.core.JDFCoreConstants;
import org.cip4.jdflib.resource.JDFResource;
import org.cip4.jdflib.resource.process.JDFAutomatedOverPrintParams;
import org.cip4.jdflib.resource.process.JDFColorantAlias;
import org.cip4.jdflib.resource.process.JDFFileSpec;
import org.cip4.jdflib.resource.process.prepress.JDFColorCorrectionOp;
import org.cip4.jdflib.resource.process.prepress.JDFColorSpaceConversionOp;
import org.cip4.jdflib.util.JavaEnumUtil;

/**
 *****************************************************************************
 * class JDFAutoElementColorParams : public JDFResource
 *****************************************************************************
 * 
 */

public abstract class JDFAutoElementColorParams extends JDFResource
{

	private static final long serialVersionUID = 1L;

	private static AtrInfoTable[] atrInfoTable = new AtrInfoTable[2];
	static
	{
		atrInfoTable[0] = new AtrInfoTable(AttributeName.COLORMANAGEMENTSYSTEM, 0x3333333311l, AttributeInfo.EnumAttributeType.NMTOKEN, null, null);
		atrInfoTable[1] = new AtrInfoTable(AttributeName.ICCOUTPUTPROFILEUSAGE, 0x3333333311l, AttributeInfo.EnumAttributeType.enumeration, EnumICCOutputProfileUsage.getEnum(0),
				null);
	}

	@Override
	protected AttributeInfo getTheAttributeInfo()
	{
		return super.getTheAttributeInfo().updateReplace(atrInfoTable);
	}

	private static ElemInfoTable[] elemInfoTable = new ElemInfoTable[5];
	static
	{
		elemInfoTable[0] = new ElemInfoTable(ElementName.AUTOMATEDOVERPRINTPARAMS, 0x6666666611l);
		elemInfoTable[1] = new ElemInfoTable(ElementName.COLORANTALIAS, 0x3333333311l);
		elemInfoTable[2] = new ElemInfoTable(ElementName.COLORCORRECTIONOP, 0x3333333311l);
		elemInfoTable[3] = new ElemInfoTable(ElementName.COLORSPACECONVERSIONOP, 0x6666666611l);
		elemInfoTable[4] = new ElemInfoTable(ElementName.FILESPEC, 0x3333333311l);
	}

	@Override
	protected ElementInfo getTheElementInfo()
	{
		return super.getTheElementInfo().updateReplace(elemInfoTable);
	}

	/**
	 * Constructor for JDFAutoElementColorParams
	 *
	 * @param myOwnerDocument
	 * @param qualifiedName
	 */
	protected JDFAutoElementColorParams(CoreDocumentImpl myOwnerDocument, String qualifiedName)
	{
		super(myOwnerDocument, qualifiedName);
	}

	/**
	 * Constructor for JDFAutoElementColorParams
	 *
	 * @param myOwnerDocument
	 * @param myNamespaceURI
	 * @param qualifiedName
	 */
	protected JDFAutoElementColorParams(CoreDocumentImpl myOwnerDocument, String myNamespaceURI, String qualifiedName)
	{
		super(myOwnerDocument, myNamespaceURI, qualifiedName);
	}

	/**
	 * Constructor for JDFAutoElementColorParams
	 *
	 * @param myOwnerDocument
	 * @param myNamespaceURI
	 * @param qualifiedName
	 * @param myLocalName
	 */
	protected JDFAutoElementColorParams(CoreDocumentImpl myOwnerDocument, String myNamespaceURI, String qualifiedName, String myLocalName)
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
	 * Enumeration strings for ICCOutputProfileUsage
	 */

	public enum EICCOutputProfileUsage
	{
		PDLActual, PDLReference, IgnorePDL;

		public static EICCOutputProfileUsage getEnum(String val)
		{
			return JavaEnumUtil.getEnumIgnoreCase(EICCOutputProfileUsage.class, val, null);
		}
	}

	/**
	 * Enumeration strings for ICCOutputProfileUsage
	 */

	@SuppressWarnings("rawtypes")
	public static class EnumICCOutputProfileUsage extends ValuedEnum
	{
		private static final long serialVersionUID = 1L;
		private static int m_startValue = 0;

		protected EnumICCOutputProfileUsage(String name)
		{
			super(name, m_startValue++);
		}

		/**
		 * @param enumName the string to convert
		 * @return the enum
		 */
		public static EnumICCOutputProfileUsage getEnum(String enumName)
		{
			return (EnumICCOutputProfileUsage) getEnum(EnumICCOutputProfileUsage.class, enumName);
		}

		/**
		 * @param enumValue the integer to convert
		 * @return the enum
		 */
		public static EnumICCOutputProfileUsage getEnum(int enumValue)
		{
			return (EnumICCOutputProfileUsage) getEnum(EnumICCOutputProfileUsage.class, enumValue);
		}

		/**
		 * @return the map of enums
		 */
		public static Map getEnumMap()
		{
			return getEnumMap(EnumICCOutputProfileUsage.class);
		}

		/**
		 * @return the list of enums
		 */
		public static List getEnumList()
		{
			return getEnumList(EnumICCOutputProfileUsage.class);
		}

		/**
		 * @return the iterator
		 */
		public static Iterator iterator()
		{
			return iterator(EnumICCOutputProfileUsage.class);
		}

		/**  */
		public static final EnumICCOutputProfileUsage PDLActual = new EnumICCOutputProfileUsage("PDLActual");
		/**  */
		public static final EnumICCOutputProfileUsage PDLReference = new EnumICCOutputProfileUsage("PDLReference");
		/**  */
		public static final EnumICCOutputProfileUsage IgnorePDL = new EnumICCOutputProfileUsage("IgnorePDL");
	}

	/*
	 * ************************************************************************ Attribute getter / setter ************************************************************************
	 */

	/*
	 * --------------------------------------------------------------------- Methods for Attribute ColorManagementSystem
	 * ---------------------------------------------------------------------
	 */
	/**
	 * (36) set attribute ColorManagementSystem
	 *
	 * @param value the value to set the attribute to
	 */
	public void setColorManagementSystem(String value)
	{
		setAttribute(AttributeName.COLORMANAGEMENTSYSTEM, value, null);
	}

	/**
	 * (23) get String attribute ColorManagementSystem
	 *
	 * @return the value of the attribute
	 */
	public String getColorManagementSystem()
	{
		return getAttribute(AttributeName.COLORMANAGEMENTSYSTEM, null, JDFCoreConstants.EMPTYSTRING);
	}

	/*
	 * --------------------------------------------------------------------- Methods for Attribute ICCOutputProfileUsage
	 * ---------------------------------------------------------------------
	 */
	/**
	 * (5) set attribute ICCOutputProfileUsage
	 *
	 * @param enumVar the enumVar to set the attribute to
	 */
	public void setICCOutputProfileUsage(EICCOutputProfileUsage enumVar)
	{
		setAttribute(AttributeName.ICCOUTPUTPROFILEUSAGE, enumVar == null ? null : enumVar.name(), null);
	}

	/**
	 * (9) get attribute ICCOutputProfileUsage
	 *
	 * @return the value of the attribute
	 */
	public EICCOutputProfileUsage getEICCOutputProfileUsage()
	{
		return EICCOutputProfileUsage.getEnum(getAttribute(AttributeName.ICCOUTPUTPROFILEUSAGE, null, null));
	}

	/*
	 * --------------------------------------------------------------------- Methods for Attribute ICCOutputProfileUsage
	 * ---------------------------------------------------------------------
	 */
	/**
	 * (5) set attribute ICCOutputProfileUsage
	 *
	 * @param enumVar the enumVar to set the attribute to
	 * @deprecated use setICCOutputProfileUsage(EICCOutputProfileUsage) based on java.lang.enum instead
	 */
	@Deprecated
	public void setICCOutputProfileUsage(EnumICCOutputProfileUsage enumVar)
	{
		setAttribute(AttributeName.ICCOUTPUTPROFILEUSAGE, enumVar == null ? null : enumVar.getName(), null);
	}

	/**
	 * (9) get attribute ICCOutputProfileUsage
	 *
	 * @return the value of the attribute
	 * @deprecated use EICCOutputProfileUsage getEICCOutputProfileUsage() based on java.lang.enum instead
	 */
	@Deprecated
	public EnumICCOutputProfileUsage getICCOutputProfileUsage()
	{
		return EnumICCOutputProfileUsage.getEnum(getAttribute(AttributeName.ICCOUTPUTPROFILEUSAGE, null, null));
	}

	/*
	 * *********************************************************************** Element getter / setter ***********************************************************************
	 */

	/**
	 * (24) const get element AutomatedOverPrintParams
	 *
	 * @return JDFAutomatedOverPrintParams the element
	 */
	public JDFAutomatedOverPrintParams getAutomatedOverPrintParams()
	{
		return (JDFAutomatedOverPrintParams) getElement(ElementName.AUTOMATEDOVERPRINTPARAMS, null, 0);
	}

	/**
	 * (25) getCreateAutomatedOverPrintParams
	 * 
	 * @return JDFAutomatedOverPrintParams the element
	 */
	public JDFAutomatedOverPrintParams getCreateAutomatedOverPrintParams()
	{
		return (JDFAutomatedOverPrintParams) getCreateElement_JDFElement(ElementName.AUTOMATEDOVERPRINTPARAMS, null, 0);
	}

	/**
	 * (29) append element AutomatedOverPrintParams
	 *
	 * @return JDFAutomatedOverPrintParams the element @ if the element already exists
	 */
	public JDFAutomatedOverPrintParams appendAutomatedOverPrintParams()
	{
		return (JDFAutomatedOverPrintParams) appendElementN(ElementName.AUTOMATEDOVERPRINTPARAMS, 1, null);
	}

	/**
	 * (24) const get element ColorantAlias
	 *
	 * @return JDFColorantAlias the element
	 */
	public JDFColorantAlias getColorantAlias()
	{
		return (JDFColorantAlias) getElement(ElementName.COLORANTALIAS, null, 0);
	}

	/**
	 * (25) getCreateColorantAlias
	 * 
	 * @return JDFColorantAlias the element
	 */
	public JDFColorantAlias getCreateColorantAlias()
	{
		return (JDFColorantAlias) getCreateElement_JDFElement(ElementName.COLORANTALIAS, null, 0);
	}

	/**
	 * (26) getCreateColorantAlias
	 * 
	 * @param iSkip number of elements to skip
	 * @return JDFColorantAlias the element
	 */
	public JDFColorantAlias getCreateColorantAlias(int iSkip)
	{
		return (JDFColorantAlias) getCreateElement_JDFElement(ElementName.COLORANTALIAS, null, iSkip);
	}

	/**
	 * (27) const get element ColorantAlias
	 *
	 * @param iSkip number of elements to skip
	 * @return JDFColorantAlias the element default is getColorantAlias(0)
	 */
	public JDFColorantAlias getColorantAlias(int iSkip)
	{
		return (JDFColorantAlias) getElement(ElementName.COLORANTALIAS, null, iSkip);
	}

	/**
	 * Get all ColorantAlias from the current element
	 * 
	 * @return Collection<JDFColorantAlias>, null if none are available
	 */
	public Collection<JDFColorantAlias> getAllColorantAlias()
	{
		return getChildArrayByClass(JDFColorantAlias.class, false, 0);
	}

	/**
	 * (30) append element ColorantAlias
	 *
	 * @return JDFColorantAlias the element
	 */
	public JDFColorantAlias appendColorantAlias()
	{
		return (JDFColorantAlias) appendElement(ElementName.COLORANTALIAS, null);
	}

	/**
	 * (31) create inter-resource link to refTarget
	 *
	 * @param refTarget the element that is referenced
	 */
	public void refColorantAlias(JDFColorantAlias refTarget)
	{
		refElement(refTarget);
	}

	/**
	 * (24) const get element ColorCorrectionOp
	 *
	 * @return JDFColorCorrectionOp the element
	 */
	public JDFColorCorrectionOp getColorCorrectionOp()
	{
		return (JDFColorCorrectionOp) getElement(ElementName.COLORCORRECTIONOP, null, 0);
	}

	/**
	 * (25) getCreateColorCorrectionOp
	 * 
	 * @return JDFColorCorrectionOp the element
	 */
	public JDFColorCorrectionOp getCreateColorCorrectionOp()
	{
		return (JDFColorCorrectionOp) getCreateElement_JDFElement(ElementName.COLORCORRECTIONOP, null, 0);
	}

	/**
	 * (26) getCreateColorCorrectionOp
	 * 
	 * @param iSkip number of elements to skip
	 * @return JDFColorCorrectionOp the element
	 */
	public JDFColorCorrectionOp getCreateColorCorrectionOp(int iSkip)
	{
		return (JDFColorCorrectionOp) getCreateElement_JDFElement(ElementName.COLORCORRECTIONOP, null, iSkip);
	}

	/**
	 * (27) const get element ColorCorrectionOp
	 *
	 * @param iSkip number of elements to skip
	 * @return JDFColorCorrectionOp the element default is getColorCorrectionOp(0)
	 */
	public JDFColorCorrectionOp getColorCorrectionOp(int iSkip)
	{
		return (JDFColorCorrectionOp) getElement(ElementName.COLORCORRECTIONOP, null, iSkip);
	}

	/**
	 * Get all ColorCorrectionOp from the current element
	 * 
	 * @return Collection<JDFColorCorrectionOp>, null if none are available
	 */
	public Collection<JDFColorCorrectionOp> getAllColorCorrectionOp()
	{
		return getChildArrayByClass(JDFColorCorrectionOp.class, false, 0);
	}

	/**
	 * (30) append element ColorCorrectionOp
	 *
	 * @return JDFColorCorrectionOp the element
	 */
	public JDFColorCorrectionOp appendColorCorrectionOp()
	{
		return (JDFColorCorrectionOp) appendElement(ElementName.COLORCORRECTIONOP, null);
	}

	/**
	 * (24) const get element ColorSpaceConversionOp
	 *
	 * @return JDFColorSpaceConversionOp the element
	 */
	public JDFColorSpaceConversionOp getColorSpaceConversionOp()
	{
		return (JDFColorSpaceConversionOp) getElement(ElementName.COLORSPACECONVERSIONOP, null, 0);
	}

	/**
	 * (25) getCreateColorSpaceConversionOp
	 * 
	 * @return JDFColorSpaceConversionOp the element
	 */
	public JDFColorSpaceConversionOp getCreateColorSpaceConversionOp()
	{
		return (JDFColorSpaceConversionOp) getCreateElement_JDFElement(ElementName.COLORSPACECONVERSIONOP, null, 0);
	}

	/**
	 * (29) append element ColorSpaceConversionOp
	 *
	 * @return JDFColorSpaceConversionOp the element @ if the element already exists
	 */
	public JDFColorSpaceConversionOp appendColorSpaceConversionOp()
	{
		return (JDFColorSpaceConversionOp) appendElementN(ElementName.COLORSPACECONVERSIONOP, 1, null);
	}

	/**
	 * (24) const get element FileSpec
	 *
	 * @return JDFFileSpec the element
	 */
	public JDFFileSpec getFileSpec()
	{
		return (JDFFileSpec) getElement(ElementName.FILESPEC, null, 0);
	}

	/**
	 * (25) getCreateFileSpec
	 * 
	 * @return JDFFileSpec the element
	 */
	public JDFFileSpec getCreateFileSpec()
	{
		return (JDFFileSpec) getCreateElement_JDFElement(ElementName.FILESPEC, null, 0);
	}

	/**
	 * (26) getCreateFileSpec
	 * 
	 * @param iSkip number of elements to skip
	 * @return JDFFileSpec the element
	 */
	public JDFFileSpec getCreateFileSpec(int iSkip)
	{
		return (JDFFileSpec) getCreateElement_JDFElement(ElementName.FILESPEC, null, iSkip);
	}

	/**
	 * (27) const get element FileSpec
	 *
	 * @param iSkip number of elements to skip
	 * @return JDFFileSpec the element default is getFileSpec(0)
	 */
	public JDFFileSpec getFileSpec(int iSkip)
	{
		return (JDFFileSpec) getElement(ElementName.FILESPEC, null, iSkip);
	}

	/**
	 * Get all FileSpec from the current element
	 * 
	 * @return Collection<JDFFileSpec>, null if none are available
	 */
	public Collection<JDFFileSpec> getAllFileSpec()
	{
		return getChildArrayByClass(JDFFileSpec.class, false, 0);
	}

	/**
	 * (30) append element FileSpec
	 *
	 * @return JDFFileSpec the element
	 */
	public JDFFileSpec appendFileSpec()
	{
		return (JDFFileSpec) appendElement(ElementName.FILESPEC, null);
	}

	/**
	 * (31) create inter-resource link to refTarget
	 *
	 * @param refTarget the element that is referenced
	 */
	public void refFileSpec(JDFFileSpec refTarget)
	{
		refElement(refTarget);
	}

}
