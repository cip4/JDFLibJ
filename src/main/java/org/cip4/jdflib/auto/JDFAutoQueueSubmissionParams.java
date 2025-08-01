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
import org.cip4.jdflib.resource.process.JDFDisposition;
import org.cip4.jdflib.util.JavaEnumUtil;

/**
 *****************************************************************************
 * class JDFAutoQueueSubmissionParams : public JDFElement
 *****************************************************************************
 * 
 */

public abstract class JDFAutoQueueSubmissionParams extends JDFElement
{

	private static final long serialVersionUID = 1L;

	private static AtrInfoTable[] atrInfoTable = new AtrInfoTable[11];
	static
	{
		atrInfoTable[0] = new AtrInfoTable(AttributeName.GANGNAME, 0x3333333111l, AttributeInfo.EnumAttributeType.NMTOKEN, null, null);
		atrInfoTable[1] = new AtrInfoTable(AttributeName.GANGPOLICY, 0x3333333111l, AttributeInfo.EnumAttributeType.enumeration, EnumGangPolicy.getEnum(0), null);
		atrInfoTable[2] = new AtrInfoTable(AttributeName.HOLD, 0x3333333333l, AttributeInfo.EnumAttributeType.boolean_, null, "false");
		atrInfoTable[3] = new AtrInfoTable(AttributeName.NEXTQUEUEENTRYID, 0x3333333333l, AttributeInfo.EnumAttributeType.shortString, null, null);
		atrInfoTable[4] = new AtrInfoTable(AttributeName.PREVQUEUEENTRYID, 0x3333333333l, AttributeInfo.EnumAttributeType.shortString, null, null);
		atrInfoTable[5] = new AtrInfoTable(AttributeName.PRIORITY, 0x3333333333l, AttributeInfo.EnumAttributeType.integer, null, null);
		atrInfoTable[6] = new AtrInfoTable(AttributeName.REFID, 0x3333333311l, AttributeInfo.EnumAttributeType.NMTOKEN, null, null);
		atrInfoTable[7] = new AtrInfoTable(AttributeName.RETURNJMF, 0x3333333311l, AttributeInfo.EnumAttributeType.URL, null, null);
		atrInfoTable[8] = new AtrInfoTable(AttributeName.RETURNURL, 0x3333333311l, AttributeInfo.EnumAttributeType.URL, null, null);
		atrInfoTable[9] = new AtrInfoTable(AttributeName.URL, 0x2222222222l, AttributeInfo.EnumAttributeType.URL, null, null);
		atrInfoTable[10] = new AtrInfoTable(AttributeName.WATCHURL, 0x4444433333l, AttributeInfo.EnumAttributeType.URL, null, null);
	}

	@Override
	protected AttributeInfo getTheAttributeInfo()
	{
		return super.getTheAttributeInfo().updateReplace(atrInfoTable);
	}

	private static ElemInfoTable[] elemInfoTable = new ElemInfoTable[1];
	static
	{
		elemInfoTable[0] = new ElemInfoTable(ElementName.DISPOSITION, 0x6666666666l);
	}

	@Override
	protected ElementInfo getTheElementInfo()
	{
		return super.getTheElementInfo().updateReplace(elemInfoTable);
	}

	/**
	 * Constructor for JDFAutoQueueSubmissionParams
	 *
	 * @param myOwnerDocument
	 * @param qualifiedName
	 */
	protected JDFAutoQueueSubmissionParams(CoreDocumentImpl myOwnerDocument, String qualifiedName)
	{
		super(myOwnerDocument, qualifiedName);
	}

	/**
	 * Constructor for JDFAutoQueueSubmissionParams
	 *
	 * @param myOwnerDocument
	 * @param myNamespaceURI
	 * @param qualifiedName
	 */
	protected JDFAutoQueueSubmissionParams(CoreDocumentImpl myOwnerDocument, String myNamespaceURI, String qualifiedName)
	{
		super(myOwnerDocument, myNamespaceURI, qualifiedName);
	}

	/**
	 * Constructor for JDFAutoQueueSubmissionParams
	 *
	 * @param myOwnerDocument
	 * @param myNamespaceURI
	 * @param qualifiedName
	 * @param myLocalName
	 */
	protected JDFAutoQueueSubmissionParams(CoreDocumentImpl myOwnerDocument, String myNamespaceURI, String qualifiedName, String myLocalName)
	{
		super(myOwnerDocument, myNamespaceURI, qualifiedName, myLocalName);
	}

	/**
	 * Enumeration strings for GangPolicy
	 */

	public enum EGangPolicy
	{
		Gang, GangAndForce, NoGang;

		public static EGangPolicy getEnum(String val)
		{
			return JavaEnumUtil.getEnumIgnoreCase(EGangPolicy.class, val, null);
		}
	}

	/**
	 * Enumeration strings for GangPolicy
	 */

	@SuppressWarnings("rawtypes")
	public static class EnumGangPolicy extends ValuedEnum
	{
		private static final long serialVersionUID = 1L;
		private static int m_startValue = 0;

		protected EnumGangPolicy(String name)
		{
			super(name, m_startValue++);
		}

		/**
		 * @param enumName the string to convert
		 * @return the enum
		 */
		public static EnumGangPolicy getEnum(String enumName)
		{
			return (EnumGangPolicy) getEnum(EnumGangPolicy.class, enumName);
		}

		/**
		 * @param enumValue the integer to convert
		 * @return the enum
		 */
		public static EnumGangPolicy getEnum(int enumValue)
		{
			return (EnumGangPolicy) getEnum(EnumGangPolicy.class, enumValue);
		}

		/**
		 * @return the map of enums
		 */
		public static Map getEnumMap()
		{
			return getEnumMap(EnumGangPolicy.class);
		}

		/**
		 * @return the list of enums
		 */
		public static List getEnumList()
		{
			return getEnumList(EnumGangPolicy.class);
		}

		/**
		 * @return the iterator
		 */
		public static Iterator iterator()
		{
			return iterator(EnumGangPolicy.class);
		}

		/**  */
		public static final EnumGangPolicy Gang = new EnumGangPolicy("Gang");
		/**  */
		public static final EnumGangPolicy GangAndForce = new EnumGangPolicy("GangAndForce");
		/**  */
		public static final EnumGangPolicy NoGang = new EnumGangPolicy("NoGang");
	}

	/*
	 * ************************************************************************ Attribute getter / setter ************************************************************************
	 */

	/*
	 * --------------------------------------------------------------------- Methods for Attribute GangName ---------------------------------------------------------------------
	 */
	/**
	 * (36) set attribute GangName
	 *
	 * @param value the value to set the attribute to
	 */
	public void setGangName(String value)
	{
		setAttribute(AttributeName.GANGNAME, value, null);
	}

	/**
	 * (23) get String attribute GangName
	 *
	 * @return the value of the attribute
	 */
	public String getGangName()
	{
		return getAttribute(AttributeName.GANGNAME, null, JDFCoreConstants.EMPTYSTRING);
	}

	/*
	 * --------------------------------------------------------------------- Methods for Attribute GangPolicy ---------------------------------------------------------------------
	 */
	/**
	 * (5) set attribute GangPolicy
	 *
	 * @param enumVar the enumVar to set the attribute to
	 */
	public void setGangPolicy(EGangPolicy enumVar)
	{
		setAttribute(AttributeName.GANGPOLICY, enumVar == null ? null : enumVar.name(), null);
	}

	/**
	 * (9) get attribute GangPolicy
	 *
	 * @return the value of the attribute
	 */
	public EGangPolicy getEGangPolicy()
	{
		return EGangPolicy.getEnum(getAttribute(AttributeName.GANGPOLICY, null, null));
	}

	/*
	 * --------------------------------------------------------------------- Methods for Attribute GangPolicy ---------------------------------------------------------------------
	 */
	/**
	 * (5) set attribute GangPolicy
	 *
	 * @param enumVar the enumVar to set the attribute to
	 * @deprecated use java.lang.enum
	 */
	@Deprecated
	public void setGangPolicy(EnumGangPolicy enumVar)
	{
		setAttribute(AttributeName.GANGPOLICY, enumVar == null ? null : enumVar.getName(), null);
	}

	/**
	 * (9) get attribute GangPolicy
	 *
	 * @return the value of the attribute
	 */
	public EnumGangPolicy getGangPolicy()
	{
		return EnumGangPolicy.getEnum(getAttribute(AttributeName.GANGPOLICY, null, null));
	}

	/*
	 * --------------------------------------------------------------------- Methods for Attribute Hold ---------------------------------------------------------------------
	 */
	/**
	 * (36) set attribute Hold
	 *
	 * @param value the value to set the attribute to
	 */
	public void setHold(boolean value)
	{
		setAttribute(AttributeName.HOLD, value, null);
	}

	/**
	 * (18) get boolean attribute Hold
	 *
	 * @return boolean the value of the attribute
	 */
	public boolean getHold()
	{
		return getBoolAttribute(AttributeName.HOLD, null, false);
	}

	/*
	 * --------------------------------------------------------------------- Methods for Attribute NextQueueEntryID
	 * ---------------------------------------------------------------------
	 */
	/**
	 * (36) set attribute NextQueueEntryID
	 *
	 * @param value the value to set the attribute to
	 */
	public void setNextQueueEntryID(String value)
	{
		setAttribute(AttributeName.NEXTQUEUEENTRYID, value, null);
	}

	/**
	 * (23) get String attribute NextQueueEntryID
	 *
	 * @return the value of the attribute
	 */
	public String getNextQueueEntryID()
	{
		return getAttribute(AttributeName.NEXTQUEUEENTRYID, null, JDFCoreConstants.EMPTYSTRING);
	}

	/*
	 * --------------------------------------------------------------------- Methods for Attribute PrevQueueEntryID
	 * ---------------------------------------------------------------------
	 */
	/**
	 * (36) set attribute PrevQueueEntryID
	 *
	 * @param value the value to set the attribute to
	 */
	public void setPrevQueueEntryID(String value)
	{
		setAttribute(AttributeName.PREVQUEUEENTRYID, value, null);
	}

	/**
	 * (23) get String attribute PrevQueueEntryID
	 *
	 * @return the value of the attribute
	 */
	public String getPrevQueueEntryID()
	{
		return getAttribute(AttributeName.PREVQUEUEENTRYID, null, JDFCoreConstants.EMPTYSTRING);
	}

	/*
	 * --------------------------------------------------------------------- Methods for Attribute Priority ---------------------------------------------------------------------
	 */
	/**
	 * (36) set attribute Priority
	 *
	 * @param value the value to set the attribute to
	 */
	public void setPriority(int value)
	{
		setAttribute(AttributeName.PRIORITY, value, null);
	}

	/**
	 * (15) get int attribute Priority
	 *
	 * @return int the value of the attribute
	 */
	public int getPriority()
	{
		return getIntAttribute(AttributeName.PRIORITY, null, 0);
	}

	/*
	 * --------------------------------------------------------------------- Methods for Attribute refID ---------------------------------------------------------------------
	 */
	/**
	 * (36) set attribute refID
	 *
	 * @param value the value to set the attribute to
	 */
	public void setrefID(String value)
	{
		setAttribute(AttributeName.REFID, value, null);
	}

	/**
	 * (23) get String attribute refID
	 *
	 * @return the value of the attribute
	 */
	public String getrefID()
	{
		return getAttribute(AttributeName.REFID, null, JDFCoreConstants.EMPTYSTRING);
	}

	/*
	 * --------------------------------------------------------------------- Methods for Attribute ReturnJMF ---------------------------------------------------------------------
	 */
	/**
	 * (36) set attribute ReturnJMF
	 *
	 * @param value the value to set the attribute to
	 */
	public void setReturnJMF(String value)
	{
		setAttribute(AttributeName.RETURNJMF, value, null);
	}

	/**
	 * (23) get String attribute ReturnJMF
	 *
	 * @return the value of the attribute
	 */
	public String getReturnJMF()
	{
		return getAttribute(AttributeName.RETURNJMF, null, JDFCoreConstants.EMPTYSTRING);
	}

	/*
	 * --------------------------------------------------------------------- Methods for Attribute ReturnURL ---------------------------------------------------------------------
	 */
	/**
	 * (36) set attribute ReturnURL
	 *
	 * @param value the value to set the attribute to
	 */
	public void setReturnURL(String value)
	{
		setAttribute(AttributeName.RETURNURL, value, null);
	}

	/**
	 * (23) get String attribute ReturnURL
	 *
	 * @return the value of the attribute
	 */
	public String getReturnURL()
	{
		return getAttribute(AttributeName.RETURNURL, null, JDFCoreConstants.EMPTYSTRING);
	}

	/*
	 * --------------------------------------------------------------------- Methods for Attribute URL ---------------------------------------------------------------------
	 */
	/**
	 * (36) set attribute URL
	 *
	 * @param value the value to set the attribute to
	 */
	public void setURL(String value)
	{
		setAttribute(AttributeName.URL, value, null);
	}

	/**
	 * (23) get String attribute URL
	 *
	 * @return the value of the attribute
	 */
	public String getURL()
	{
		return getAttribute(AttributeName.URL, null, JDFCoreConstants.EMPTYSTRING);
	}

	/*
	 * --------------------------------------------------------------------- Methods for Attribute WatchURL ---------------------------------------------------------------------
	 */
	/**
	 * (36) set attribute WatchURL
	 *
	 * @param value the value to set the attribute to
	 */
	public void setWatchURL(String value)
	{
		setAttribute(AttributeName.WATCHURL, value, null);
	}

	/**
	 * (23) get String attribute WatchURL
	 *
	 * @return the value of the attribute
	 */
	public String getWatchURL()
	{
		return getAttribute(AttributeName.WATCHURL, null, JDFCoreConstants.EMPTYSTRING);
	}

	/*
	 * *********************************************************************** Element getter / setter ***********************************************************************
	 */

	/**
	 * (24) const get element Disposition
	 *
	 * @return JDFDisposition the element
	 */
	public JDFDisposition getDisposition()
	{
		return (JDFDisposition) getElement(ElementName.DISPOSITION, null, 0);
	}

	/**
	 * (25) getCreateDisposition
	 * 
	 * @return JDFDisposition the element
	 */
	public JDFDisposition getCreateDisposition()
	{
		return (JDFDisposition) getCreateElement_JDFElement(ElementName.DISPOSITION, null, 0);
	}

	/**
	 * (29) append element Disposition
	 *
	 * @return JDFDisposition the element @ if the element already exists
	 */
	public JDFDisposition appendDisposition()
	{
		return (JDFDisposition) appendElementN(ElementName.DISPOSITION, 1, null);
	}

}
