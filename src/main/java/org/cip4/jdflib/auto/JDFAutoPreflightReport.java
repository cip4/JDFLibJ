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
import org.cip4.jdflib.resource.JDFResource;
import org.cip4.jdflib.resource.process.JDFFileSpec;
import org.cip4.jdflib.resource.process.JDFPRItem;
import org.cip4.jdflib.resource.process.JDFPreflightParams;
import org.cip4.jdflib.resource.process.JDFPreflightReportRulePool;
import org.cip4.jdflib.resource.process.JDFRunList;
import org.cip4.jdflib.util.JavaEnumUtil;

/**
 *****************************************************************************
 * class JDFAutoPreflightReport : public JDFResource
 *****************************************************************************
 * 
 */

public abstract class JDFAutoPreflightReport extends JDFResource
{

	private static final long serialVersionUID = 1L;

	private static AtrInfoTable[] atrInfoTable = new AtrInfoTable[3];
	static
	{
		atrInfoTable[0] = new AtrInfoTable(AttributeName.ERRORCOUNT, 0x3333333311l, AttributeInfo.EnumAttributeType.integer, null, null);
		atrInfoTable[1] = new AtrInfoTable(AttributeName.ERRORSTATE, 0x3333333311l, AttributeInfo.EnumAttributeType.enumeration, EnumErrorState.getEnum(0), null);
		atrInfoTable[2] = new AtrInfoTable(AttributeName.WARNINGCOUNT, 0x3333333311l, AttributeInfo.EnumAttributeType.integer, null, null);
	}

	@Override
	protected AttributeInfo getTheAttributeInfo()
	{
		return super.getTheAttributeInfo().updateReplace(atrInfoTable);
	}

	private static ElemInfoTable[] elemInfoTable = new ElemInfoTable[5];
	static
	{
		elemInfoTable[0] = new ElemInfoTable(ElementName.FILESPEC, 0x6666666611l);
		elemInfoTable[1] = new ElemInfoTable(ElementName.PREFLIGHTPARAMS, 0x5555555511l);
		elemInfoTable[2] = new ElemInfoTable(ElementName.PREFLIGHTREPORTRULEPOOL, 0x6666666611l);
		elemInfoTable[3] = new ElemInfoTable(ElementName.RUNLIST, 0x5555555511l);
		elemInfoTable[4] = new ElemInfoTable(ElementName.PRITEM, 0x3333333311l);
	}

	@Override
	protected ElementInfo getTheElementInfo()
	{
		return super.getTheElementInfo().updateReplace(elemInfoTable);
	}

	/**
	 * Constructor for JDFAutoPreflightReport
	 *
	 * @param myOwnerDocument
	 * @param qualifiedName
	 */
	protected JDFAutoPreflightReport(CoreDocumentImpl myOwnerDocument, String qualifiedName)
	{
		super(myOwnerDocument, qualifiedName);
	}

	/**
	 * Constructor for JDFAutoPreflightReport
	 *
	 * @param myOwnerDocument
	 * @param myNamespaceURI
	 * @param qualifiedName
	 */
	protected JDFAutoPreflightReport(CoreDocumentImpl myOwnerDocument, String myNamespaceURI, String qualifiedName)
	{
		super(myOwnerDocument, myNamespaceURI, qualifiedName);
	}

	/**
	 * Constructor for JDFAutoPreflightReport
	 *
	 * @param myOwnerDocument
	 * @param myNamespaceURI
	 * @param qualifiedName
	 * @param myLocalName
	 */
	protected JDFAutoPreflightReport(CoreDocumentImpl myOwnerDocument, String myNamespaceURI, String qualifiedName, String myLocalName)
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
	 * Enumeration strings for ErrorState
	 */

	public enum EErrorState
	{
		TestNotSupported, TestWrongPDL;

		public static EErrorState getEnum(String val)
		{
			return JavaEnumUtil.getEnumIgnoreCase(EErrorState.class, val, null);
		}
	}

	/**
	 * Enumeration strings for ErrorState
	 */

	@SuppressWarnings("rawtypes")
	public static class EnumErrorState extends ValuedEnum
	{
		private static final long serialVersionUID = 1L;
		private static int m_startValue = 0;

		protected EnumErrorState(String name)
		{
			super(name, m_startValue++);
		}

		/**
		 * @param enumName the string to convert
		 * @return the enum
		 */
		public static EnumErrorState getEnum(String enumName)
		{
			return (EnumErrorState) getEnum(EnumErrorState.class, enumName);
		}

		/**
		 * @param enumValue the integer to convert
		 * @return the enum
		 */
		public static EnumErrorState getEnum(int enumValue)
		{
			return (EnumErrorState) getEnum(EnumErrorState.class, enumValue);
		}

		/**
		 * @return the map of enums
		 */
		public static Map getEnumMap()
		{
			return getEnumMap(EnumErrorState.class);
		}

		/**
		 * @return the list of enums
		 */
		public static List getEnumList()
		{
			return getEnumList(EnumErrorState.class);
		}

		/**
		 * @return the iterator
		 */
		public static Iterator iterator()
		{
			return iterator(EnumErrorState.class);
		}

		/**  */
		public static final EnumErrorState TestNotSupported = new EnumErrorState("TestNotSupported");
		/**  */
		public static final EnumErrorState TestWrongPDL = new EnumErrorState("TestWrongPDL");
	}

	/*
	 * ************************************************************************ Attribute getter / setter ************************************************************************
	 */

	/*
	 * --------------------------------------------------------------------- Methods for Attribute ErrorCount ---------------------------------------------------------------------
	 */
	/**
	 * (36) set attribute ErrorCount
	 *
	 * @param value the value to set the attribute to
	 */
	public void setErrorCount(int value)
	{
		setAttribute(AttributeName.ERRORCOUNT, value, null);
	}

	/**
	 * (15) get int attribute ErrorCount
	 *
	 * @return int the value of the attribute
	 */
	public int getErrorCount()
	{
		return getIntAttribute(AttributeName.ERRORCOUNT, null, 0);
	}

	/*
	 * --------------------------------------------------------------------- Methods for Attribute ErrorState ---------------------------------------------------------------------
	 */
	/**
	 * (5) set attribute ErrorState
	 *
	 * @param enumVar the enumVar to set the attribute to
	 */
	public void setErrorState(EErrorState enumVar)
	{
		setAttribute(AttributeName.ERRORSTATE, enumVar == null ? null : enumVar.name(), null);
	}

	/**
	 * (9) get attribute ErrorState
	 *
	 * @return the value of the attribute
	 */
	public EErrorState getEErrorState()
	{
		return EErrorState.getEnum(getAttribute(AttributeName.ERRORSTATE, null, null));
	}

	/*
	 * --------------------------------------------------------------------- Methods for Attribute ErrorState ---------------------------------------------------------------------
	 */
	/**
	 * (5) set attribute ErrorState
	 *
	 * @param enumVar the enumVar to set the attribute to
	 * @deprecated use java.lang.enum
	 */
	@Deprecated
	public void setErrorState(EnumErrorState enumVar)
	{
		setAttribute(AttributeName.ERRORSTATE, enumVar == null ? null : enumVar.getName(), null);
	}

	/**
	 * (9) get attribute ErrorState
	 *
	 * @return the value of the attribute
	 */
	public EnumErrorState getErrorState()
	{
		return EnumErrorState.getEnum(getAttribute(AttributeName.ERRORSTATE, null, null));
	}

	/*
	 * --------------------------------------------------------------------- Methods for Attribute WarningCount
	 * ---------------------------------------------------------------------
	 */
	/**
	 * (36) set attribute WarningCount
	 *
	 * @param value the value to set the attribute to
	 */
	public void setWarningCount(int value)
	{
		setAttribute(AttributeName.WARNINGCOUNT, value, null);
	}

	/**
	 * (15) get int attribute WarningCount
	 *
	 * @return int the value of the attribute
	 */
	public int getWarningCount()
	{
		return getIntAttribute(AttributeName.WARNINGCOUNT, null, 0);
	}

	/*
	 * *********************************************************************** Element getter / setter ***********************************************************************
	 */

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
	 * (29) append element FileSpec
	 *
	 * @return JDFFileSpec the element @ if the element already exists
	 */
	public JDFFileSpec appendFileSpec()
	{
		return (JDFFileSpec) appendElementN(ElementName.FILESPEC, 1, null);
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

	/**
	 * (24) const get element PreflightParams
	 *
	 * @return JDFPreflightParams the element
	 */
	public JDFPreflightParams getPreflightParams()
	{
		return (JDFPreflightParams) getElement(ElementName.PREFLIGHTPARAMS, null, 0);
	}

	/**
	 * (25) getCreatePreflightParams
	 * 
	 * @return JDFPreflightParams the element
	 */
	public JDFPreflightParams getCreatePreflightParams()
	{
		return (JDFPreflightParams) getCreateElement_JDFElement(ElementName.PREFLIGHTPARAMS, null, 0);
	}

	/**
	 * (29) append element PreflightParams
	 *
	 * @return JDFPreflightParams the element @ if the element already exists
	 */
	public JDFPreflightParams appendPreflightParams()
	{
		return (JDFPreflightParams) appendElementN(ElementName.PREFLIGHTPARAMS, 1, null);
	}

	/**
	 * (31) create inter-resource link to refTarget
	 *
	 * @param refTarget the element that is referenced
	 */
	public void refPreflightParams(JDFPreflightParams refTarget)
	{
		refElement(refTarget);
	}

	/**
	 * (24) const get element PreflightReportRulePool
	 *
	 * @return JDFPreflightReportRulePool the element
	 */
	public JDFPreflightReportRulePool getPreflightReportRulePool()
	{
		return (JDFPreflightReportRulePool) getElement(ElementName.PREFLIGHTREPORTRULEPOOL, null, 0);
	}

	/**
	 * (25) getCreatePreflightReportRulePool
	 * 
	 * @return JDFPreflightReportRulePool the element
	 */
	public JDFPreflightReportRulePool getCreatePreflightReportRulePool()
	{
		return (JDFPreflightReportRulePool) getCreateElement_JDFElement(ElementName.PREFLIGHTREPORTRULEPOOL, null, 0);
	}

	/**
	 * (29) append element PreflightReportRulePool
	 *
	 * @return JDFPreflightReportRulePool the element @ if the element already exists
	 */
	public JDFPreflightReportRulePool appendPreflightReportRulePool()
	{
		return (JDFPreflightReportRulePool) appendElementN(ElementName.PREFLIGHTREPORTRULEPOOL, 1, null);
	}

	/**
	 * (31) create inter-resource link to refTarget
	 *
	 * @param refTarget the element that is referenced
	 */
	public void refPreflightReportRulePool(JDFPreflightReportRulePool refTarget)
	{
		refElement(refTarget);
	}

	/**
	 * (24) const get element RunList
	 *
	 * @return JDFRunList the element
	 */
	public JDFRunList getRunList()
	{
		return (JDFRunList) getElement(ElementName.RUNLIST, null, 0);
	}

	/**
	 * (25) getCreateRunList
	 * 
	 * @return JDFRunList the element
	 */
	public JDFRunList getCreateRunList()
	{
		return (JDFRunList) getCreateElement_JDFElement(ElementName.RUNLIST, null, 0);
	}

	/**
	 * (29) append element RunList
	 *
	 * @return JDFRunList the element @ if the element already exists
	 */
	public JDFRunList appendRunList()
	{
		return (JDFRunList) appendElementN(ElementName.RUNLIST, 1, null);
	}

	/**
	 * (31) create inter-resource link to refTarget
	 *
	 * @param refTarget the element that is referenced
	 */
	public void refRunList(JDFRunList refTarget)
	{
		refElement(refTarget);
	}

	/**
	 * (24) const get element PRItem
	 *
	 * @return JDFPRItem the element
	 */
	public JDFPRItem getPRItem()
	{
		return (JDFPRItem) getElement(ElementName.PRITEM, null, 0);
	}

	/**
	 * (25) getCreatePRItem
	 * 
	 * @return JDFPRItem the element
	 */
	public JDFPRItem getCreatePRItem()
	{
		return (JDFPRItem) getCreateElement_JDFElement(ElementName.PRITEM, null, 0);
	}

	/**
	 * (26) getCreatePRItem
	 * 
	 * @param iSkip number of elements to skip
	 * @return JDFPRItem the element
	 */
	public JDFPRItem getCreatePRItem(int iSkip)
	{
		return (JDFPRItem) getCreateElement_JDFElement(ElementName.PRITEM, null, iSkip);
	}

	/**
	 * (27) const get element PRItem
	 *
	 * @param iSkip number of elements to skip
	 * @return JDFPRItem the element default is getPRItem(0)
	 */
	public JDFPRItem getPRItem(int iSkip)
	{
		return (JDFPRItem) getElement(ElementName.PRITEM, null, iSkip);
	}

	/**
	 * Get all PRItem from the current element
	 * 
	 * @return Collection<JDFPRItem>, null if none are available
	 */
	public Collection<JDFPRItem> getAllPRItem()
	{
		return getChildArrayByClass(JDFPRItem.class, false, 0);
	}

	/**
	 * (30) append element PRItem
	 *
	 * @return JDFPRItem the element
	 */
	public JDFPRItem appendPRItem()
	{
		return (JDFPRItem) appendElement(ElementName.PRITEM, null);
	}

}
