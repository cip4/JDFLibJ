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
import org.cip4.jdflib.core.JDFElement;
import org.cip4.jdflib.resource.devicecapability.JDFLoc;
import org.cip4.jdflib.resource.process.JDFPreflightAction;
import org.cip4.jdflib.util.JavaEnumUtil;

/**
 *****************************************************************************
 * class JDFAutoAction : public JDFElement
 *****************************************************************************
 * 
 */

public abstract class JDFAutoAction extends JDFElement
{

	private static final long serialVersionUID = 1L;

	private static AtrInfoTable[] atrInfoTable = new AtrInfoTable[3];
	static
	{
		atrInfoTable[0] = new AtrInfoTable(AttributeName.SEVERITY, 0x3333333333l, AttributeInfo.EnumAttributeType.enumeration, EnumSeverity.getEnum(0), "Error");
		atrInfoTable[1] = new AtrInfoTable(AttributeName.ID, 0x2222222222l, AttributeInfo.EnumAttributeType.ID, null, null);
		atrInfoTable[2] = new AtrInfoTable(AttributeName.TESTREF, 0x2222222222l, AttributeInfo.EnumAttributeType.IDREF, null, null);
	}

	@Override
	protected AttributeInfo getTheAttributeInfo()
	{
		return super.getTheAttributeInfo().updateReplace(atrInfoTable);
	}

	private static ElemInfoTable[] elemInfoTable = new ElemInfoTable[2];
	static
	{
		elemInfoTable[0] = new ElemInfoTable(ElementName.LOC, 0x3333333333l);
		elemInfoTable[1] = new ElemInfoTable(ElementName.PREFLIGHTACTION, 0x3333333333l);
	}

	@Override
	protected ElementInfo getTheElementInfo()
	{
		return super.getTheElementInfo().updateReplace(elemInfoTable);
	}

	/**
	 * Constructor for JDFAutoAction
	 *
	 * @param myOwnerDocument
	 * @param qualifiedName
	 */
	protected JDFAutoAction(CoreDocumentImpl myOwnerDocument, String qualifiedName)
	{
		super(myOwnerDocument, qualifiedName);
	}

	/**
	 * Constructor for JDFAutoAction
	 *
	 * @param myOwnerDocument
	 * @param myNamespaceURI
	 * @param qualifiedName
	 */
	protected JDFAutoAction(CoreDocumentImpl myOwnerDocument, String myNamespaceURI, String qualifiedName)
	{
		super(myOwnerDocument, myNamespaceURI, qualifiedName);
	}

	/**
	 * Constructor for JDFAutoAction
	 *
	 * @param myOwnerDocument
	 * @param myNamespaceURI
	 * @param qualifiedName
	 * @param myLocalName
	 */
	protected JDFAutoAction(CoreDocumentImpl myOwnerDocument, String myNamespaceURI, String qualifiedName, String myLocalName)
	{
		super(myOwnerDocument, myNamespaceURI, qualifiedName, myLocalName);
	}

	/**
	 * Enumeration strings for Severity
	 */

	public enum ESeverity
	{
		Error, Warning, Information;

		public static ESeverity getEnum(String val)
		{
			return JavaEnumUtil.getEnumIgnoreCase(ESeverity.class, val, ESeverity.Error);
		}
	}

	/**
	 * Enumeration strings for Severity
	 */

	@SuppressWarnings("rawtypes")
	public static class EnumSeverity extends ValuedEnum
	{
		private static final long serialVersionUID = 1L;
		private static int m_startValue = 0;

		protected EnumSeverity(String name)
		{
			super(name, m_startValue++);
		}

		/**
		 * @param enumName the string to convert
		 * @return the enum
		 */
		public static EnumSeverity getEnum(String enumName)
		{
			return (EnumSeverity) getEnum(EnumSeverity.class, enumName);
		}

		/**
		 * @param enumValue the integer to convert
		 * @return the enum
		 */
		public static EnumSeverity getEnum(int enumValue)
		{
			return (EnumSeverity) getEnum(EnumSeverity.class, enumValue);
		}

		/**
		 * @return the map of enums
		 */
		public static Map getEnumMap()
		{
			return getEnumMap(EnumSeverity.class);
		}

		/**
		 * @return the list of enums
		 */
		public static List getEnumList()
		{
			return getEnumList(EnumSeverity.class);
		}

		/**
		 * @return the iterator
		 */
		public static Iterator iterator()
		{
			return iterator(EnumSeverity.class);
		}

		/**  */
		public static final EnumSeverity Error = new EnumSeverity("Error");
		/**  */
		public static final EnumSeverity Warning = new EnumSeverity("Warning");
		/**  */
		public static final EnumSeverity Information = new EnumSeverity("Information");
	}

	/*
	 * ************************************************************************ Attribute getter / setter ************************************************************************
	 */

	/*
	 * --------------------------------------------------------------------- Methods for Attribute Severity ---------------------------------------------------------------------
	 */
	/**
	 * (5) set attribute Severity
	 *
	 * @param enumVar the enumVar to set the attribute to
	 */
	public void setSeverity(ESeverity enumVar)
	{
		setAttribute(AttributeName.SEVERITY, enumVar == null ? null : enumVar.name(), null);
	}

	/**
	 * (9) get attribute Severity
	 *
	 * @return the value of the attribute
	 */
	public ESeverity getESeverity()
	{
		return ESeverity.getEnum(getAttribute(AttributeName.SEVERITY, null, "Error"));
	}

	/*
	 * --------------------------------------------------------------------- Methods for Attribute Severity ---------------------------------------------------------------------
	 */
	/**
	 * (5) set attribute Severity
	 *
	 * @param enumVar the enumVar to set the attribute to
	 * @deprecated use java.lang.enum
	 */
	@Deprecated
	public void setSeverity(EnumSeverity enumVar)
	{
		setAttribute(AttributeName.SEVERITY, enumVar == null ? null : enumVar.getName(), null);
	}

	/**
	 * (9) get attribute Severity
	 *
	 * @return the value of the attribute
	 */
	public EnumSeverity getSeverity()
	{
		return EnumSeverity.getEnum(getAttribute(AttributeName.SEVERITY, null, "Error"));
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
	 * --------------------------------------------------------------------- Methods for Attribute TestRef ---------------------------------------------------------------------
	 */
	/**
	 * (36) set attribute TestRef
	 *
	 * @param value the value to set the attribute to
	 */
	public void setTestRef(String value)
	{
		setAttribute(AttributeName.TESTREF, value, null);
	}

	/**
	 * (23) get String attribute TestRef
	 *
	 * @return the value of the attribute
	 */
	public String getTestRef()
	{
		return getAttribute(AttributeName.TESTREF, null, JDFCoreConstants.EMPTYSTRING);
	}

	/*
	 * *********************************************************************** Element getter / setter ***********************************************************************
	 */

	/**
	 * (24) const get element Loc
	 *
	 * @return JDFLoc the element
	 */
	public JDFLoc getLoc()
	{
		return (JDFLoc) getElement(ElementName.LOC, null, 0);
	}

	/**
	 * (25) getCreateLoc
	 * 
	 * @return JDFLoc the element
	 */
	public JDFLoc getCreateLoc()
	{
		return (JDFLoc) getCreateElement_JDFElement(ElementName.LOC, null, 0);
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
		return getChildArrayByClass(JDFLoc.class, false, 0);
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

	/**
	 * (24) const get element PreflightAction
	 *
	 * @return JDFPreflightAction the element
	 */
	public JDFPreflightAction getPreflightAction()
	{
		return (JDFPreflightAction) getElement(ElementName.PREFLIGHTACTION, null, 0);
	}

	/**
	 * (25) getCreatePreflightAction
	 * 
	 * @return JDFPreflightAction the element
	 */
	public JDFPreflightAction getCreatePreflightAction()
	{
		return (JDFPreflightAction) getCreateElement_JDFElement(ElementName.PREFLIGHTACTION, null, 0);
	}

	/**
	 * (26) getCreatePreflightAction
	 * 
	 * @param iSkip number of elements to skip
	 * @return JDFPreflightAction the element
	 */
	public JDFPreflightAction getCreatePreflightAction(int iSkip)
	{
		return (JDFPreflightAction) getCreateElement_JDFElement(ElementName.PREFLIGHTACTION, null, iSkip);
	}

	/**
	 * (27) const get element PreflightAction
	 *
	 * @param iSkip number of elements to skip
	 * @return JDFPreflightAction the element default is getPreflightAction(0)
	 */
	public JDFPreflightAction getPreflightAction(int iSkip)
	{
		return (JDFPreflightAction) getElement(ElementName.PREFLIGHTACTION, null, iSkip);
	}

	/**
	 * Get all PreflightAction from the current element
	 * 
	 * @return Collection<JDFPreflightAction>, null if none are available
	 */
	public Collection<JDFPreflightAction> getAllPreflightAction()
	{
		return getChildArrayByClass(JDFPreflightAction.class, false, 0);
	}

	/**
	 * (30) append element PreflightAction
	 *
	 * @return JDFPreflightAction the element
	 */
	public JDFPreflightAction appendPreflightAction()
	{
		return (JDFPreflightAction) appendElement(ElementName.PREFLIGHTACTION, null);
	}

}
