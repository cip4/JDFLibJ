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
import org.cip4.jdflib.core.JDFNodeInfo;
import org.cip4.jdflib.resource.JDFPart;
import org.cip4.jdflib.util.JavaEnumUtil;

/**
 *****************************************************************************
 * class JDFAutoNodeInfoCmdParams : public JDFElement
 *****************************************************************************
 * 
 */

public abstract class JDFAutoNodeInfoCmdParams extends JDFElement
{

	private static final long serialVersionUID = 1L;

	private static AtrInfoTable[] atrInfoTable = new AtrInfoTable[4];
	static
	{
		atrInfoTable[0] = new AtrInfoTable(AttributeName.JOBID, 0x4444444211l, AttributeInfo.EnumAttributeType.shortString, null, null);
		atrInfoTable[1] = new AtrInfoTable(AttributeName.JOBPARTID, 0x4444444311l, AttributeInfo.EnumAttributeType.shortString, null, null);
		atrInfoTable[2] = new AtrInfoTable(AttributeName.QUEUEENTRYID, 0x4444444311l, AttributeInfo.EnumAttributeType.shortString, null, null);
		atrInfoTable[3] = new AtrInfoTable(AttributeName.UPDATEMETHOD, 0x4444444311l, AttributeInfo.EnumAttributeType.enumeration, EnumUpdateMethod.getEnum(0), "Complete");
	}

	@Override
	protected AttributeInfo getTheAttributeInfo()
	{
		return super.getTheAttributeInfo().updateReplace(atrInfoTable);
	}

	private static ElemInfoTable[] elemInfoTable = new ElemInfoTable[2];
	static
	{
		elemInfoTable[0] = new ElemInfoTable(ElementName.PART, 0x4444444311l);
		elemInfoTable[1] = new ElemInfoTable(ElementName.NODEINFO, 0x7777777611l);
	}

	@Override
	protected ElementInfo getTheElementInfo()
	{
		return super.getTheElementInfo().updateReplace(elemInfoTable);
	}

	/**
	 * Constructor for JDFAutoNodeInfoCmdParams
	 *
	 * @param myOwnerDocument
	 * @param qualifiedName
	 */
	protected JDFAutoNodeInfoCmdParams(CoreDocumentImpl myOwnerDocument, String qualifiedName)
	{
		super(myOwnerDocument, qualifiedName);
	}

	/**
	 * Constructor for JDFAutoNodeInfoCmdParams
	 *
	 * @param myOwnerDocument
	 * @param myNamespaceURI
	 * @param qualifiedName
	 */
	protected JDFAutoNodeInfoCmdParams(CoreDocumentImpl myOwnerDocument, String myNamespaceURI, String qualifiedName)
	{
		super(myOwnerDocument, myNamespaceURI, qualifiedName);
	}

	/**
	 * Constructor for JDFAutoNodeInfoCmdParams
	 *
	 * @param myOwnerDocument
	 * @param myNamespaceURI
	 * @param qualifiedName
	 * @param myLocalName
	 */
	protected JDFAutoNodeInfoCmdParams(CoreDocumentImpl myOwnerDocument, String myNamespaceURI, String qualifiedName, String myLocalName)
	{
		super(myOwnerDocument, myNamespaceURI, qualifiedName, myLocalName);
	}

	/**
	 * Enumeration strings for UpdateMethod
	 */

	public enum EUpdateMethod
	{
		Complete, Incremental, Remove;

		public static EUpdateMethod getEnum(String val)
		{
			return JavaEnumUtil.getEnumIgnoreCase(EUpdateMethod.class, val, EUpdateMethod.Complete);
		}
	}

	/**
	 * Enumeration strings for UpdateMethod
	 */

	@SuppressWarnings("rawtypes")
	public static class EnumUpdateMethod extends ValuedEnum
	{
		private static final long serialVersionUID = 1L;
		private static int m_startValue = 0;

		protected EnumUpdateMethod(String name)
		{
			super(name, m_startValue++);
		}

		/**
		 * @param enumName the string to convert
		 * @return the enum
		 */
		public static EnumUpdateMethod getEnum(String enumName)
		{
			return (EnumUpdateMethod) getEnum(EnumUpdateMethod.class, enumName);
		}

		/**
		 * @param enumValue the integer to convert
		 * @return the enum
		 */
		public static EnumUpdateMethod getEnum(int enumValue)
		{
			return (EnumUpdateMethod) getEnum(EnumUpdateMethod.class, enumValue);
		}

		/**
		 * @return the map of enums
		 */
		public static Map getEnumMap()
		{
			return getEnumMap(EnumUpdateMethod.class);
		}

		/**
		 * @return the list of enums
		 */
		public static List getEnumList()
		{
			return getEnumList(EnumUpdateMethod.class);
		}

		/**
		 * @return the iterator
		 */
		public static Iterator iterator()
		{
			return iterator(EnumUpdateMethod.class);
		}

		/**  */
		public static final EnumUpdateMethod Complete = new EnumUpdateMethod("Complete");
		/**  */
		public static final EnumUpdateMethod Incremental = new EnumUpdateMethod("Incremental");
		/**  */
		public static final EnumUpdateMethod Remove = new EnumUpdateMethod("Remove");
	}

	/*
	 * ************************************************************************ Attribute getter / setter ************************************************************************
	 */

	/*
	 * --------------------------------------------------------------------- Methods for Attribute JobID ---------------------------------------------------------------------
	 */
	/**
	 * (36) set attribute JobID
	 *
	 * @param value the value to set the attribute to
	 */
	public void setJobID(String value)
	{
		setAttribute(AttributeName.JOBID, value, null);
	}

	/**
	 * (23) get String attribute JobID
	 *
	 * @return the value of the attribute
	 */
	public String getJobID()
	{
		return getAttribute(AttributeName.JOBID, null, JDFCoreConstants.EMPTYSTRING);
	}

	/*
	 * --------------------------------------------------------------------- Methods for Attribute JobPartID ---------------------------------------------------------------------
	 */
	/**
	 * (36) set attribute JobPartID
	 *
	 * @param value the value to set the attribute to
	 */
	public void setJobPartID(String value)
	{
		setAttribute(AttributeName.JOBPARTID, value, null);
	}

	/**
	 * (23) get String attribute JobPartID
	 *
	 * @return the value of the attribute
	 */
	public String getJobPartID()
	{
		return getAttribute(AttributeName.JOBPARTID, null, JDFCoreConstants.EMPTYSTRING);
	}

	/*
	 * --------------------------------------------------------------------- Methods for Attribute QueueEntryID
	 * ---------------------------------------------------------------------
	 */
	/**
	 * (36) set attribute QueueEntryID
	 *
	 * @param value the value to set the attribute to
	 */
	public void setQueueEntryID(String value)
	{
		setAttribute(AttributeName.QUEUEENTRYID, value, null);
	}

	/**
	 * (23) get String attribute QueueEntryID
	 *
	 * @return the value of the attribute
	 */
	public String getQueueEntryID()
	{
		return getAttribute(AttributeName.QUEUEENTRYID, null, JDFCoreConstants.EMPTYSTRING);
	}

	/*
	 * --------------------------------------------------------------------- Methods for Attribute UpdateMethod
	 * ---------------------------------------------------------------------
	 */
	/**
	 * (5) set attribute UpdateMethod
	 *
	 * @param enumVar the enumVar to set the attribute to
	 */
	public void setUpdateMethod(EUpdateMethod enumVar)
	{
		setAttribute(AttributeName.UPDATEMETHOD, enumVar == null ? null : enumVar.name(), null);
	}

	/**
	 * (9) get attribute UpdateMethod
	 *
	 * @return the value of the attribute
	 */
	public EUpdateMethod getEUpdateMethod()
	{
		return EUpdateMethod.getEnum(getAttribute(AttributeName.UPDATEMETHOD, null, "Complete"));
	}

	/*
	 * --------------------------------------------------------------------- Methods for Attribute UpdateMethod
	 * ---------------------------------------------------------------------
	 */
	/**
	 * (5) set attribute UpdateMethod
	 *
	 * @param enumVar the enumVar to set the attribute to
	 * @deprecated use java.lang.enum
	 */
	@Deprecated
	public void setUpdateMethod(EnumUpdateMethod enumVar)
	{
		setAttribute(AttributeName.UPDATEMETHOD, enumVar == null ? null : enumVar.getName(), null);
	}

	/**
	 * (9) get attribute UpdateMethod
	 *
	 * @return the value of the attribute
	 */
	public EnumUpdateMethod getUpdateMethod()
	{
		return EnumUpdateMethod.getEnum(getAttribute(AttributeName.UPDATEMETHOD, null, "Complete"));
	}

	/*
	 * *********************************************************************** Element getter / setter ***********************************************************************
	 */

	/**
	 * (24) const get element Part
	 *
	 * @return JDFPart the element
	 */
	public JDFPart getPart()
	{
		return (JDFPart) getElement(ElementName.PART, null, 0);
	}

	/**
	 * (25) getCreatePart
	 * 
	 * @return JDFPart the element
	 */
	public JDFPart getCreatePart()
	{
		return (JDFPart) getCreateElement_JDFElement(ElementName.PART, null, 0);
	}

	/**
	 * (26) getCreatePart
	 * 
	 * @param iSkip number of elements to skip
	 * @return JDFPart the element
	 */
	public JDFPart getCreatePart(int iSkip)
	{
		return (JDFPart) getCreateElement_JDFElement(ElementName.PART, null, iSkip);
	}

	/**
	 * (27) const get element Part
	 *
	 * @param iSkip number of elements to skip
	 * @return JDFPart the element default is getPart(0)
	 */
	public JDFPart getPart(int iSkip)
	{
		return (JDFPart) getElement(ElementName.PART, null, iSkip);
	}

	/**
	 * Get all Part from the current element
	 * 
	 * @return Collection<JDFPart>, null if none are available
	 */
	public Collection<JDFPart> getAllPart()
	{
		return getChildArrayByClass(JDFPart.class, false, 0);
	}

	/**
	 * (30) append element Part
	 *
	 * @return JDFPart the element
	 */
	public JDFPart appendPart()
	{
		return (JDFPart) appendElement(ElementName.PART, null);
	}

	/**
	 * (24) const get element NodeInfo
	 *
	 * @return JDFNodeInfo the element
	 */
	public JDFNodeInfo getNodeInfo()
	{
		return (JDFNodeInfo) getElement(ElementName.NODEINFO, null, 0);
	}

	/**
	 * (25) getCreateNodeInfo
	 * 
	 * @return JDFNodeInfo the element
	 */
	public JDFNodeInfo getCreateNodeInfo()
	{
		return (JDFNodeInfo) getCreateElement_JDFElement(ElementName.NODEINFO, null, 0);
	}

	/**
	 * (29) append element NodeInfo
	 *
	 * @return JDFNodeInfo the element @ if the element already exists
	 */
	public JDFNodeInfo appendNodeInfo()
	{
		return (JDFNodeInfo) appendElementN(ElementName.NODEINFO, 1, null);
	}

}
