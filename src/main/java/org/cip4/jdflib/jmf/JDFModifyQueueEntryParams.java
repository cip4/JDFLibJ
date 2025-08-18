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

package org.cip4.jdflib.jmf;

import org.apache.xerces.dom.CoreDocumentImpl;
import org.cip4.jdflib.core.AtrInfoTable;
import org.cip4.jdflib.core.AttributeInfo;
import org.cip4.jdflib.core.AttributeName;
import org.cip4.jdflib.core.ElemInfoTable;
import org.cip4.jdflib.core.ElementInfo;
import org.cip4.jdflib.core.ElementName;
import org.cip4.jdflib.core.JDFCoreConstants;
import org.cip4.jdflib.core.JDFElement;
import org.cip4.jdflib.util.JavaEnumUtil;
import org.cip4.jdflib.util.StringUtil;

/**
 *****************************************************************************
 * class JDFModifyQueueEntryParams : public JDFElement
 *****************************************************************************
 * 
 */

public class JDFModifyQueueEntryParams extends JDFElement
{

	public enum eOperation
	{
		Abort, Complete, Hold, Move, Remove, Resume, SetGang, Suspend;

		public static eOperation getEnum(final String s)
		{
			eOperation op = JavaEnumUtil.getEnumIgnoreCase(eOperation.class, s);
			if (op == null)
			{
				op = JavaEnumUtil.getEnumIgnoreCase(eOperation.class, StringUtil.leftStr(s, -10));
				if (op == null && s != null && s.toLowerCase().startsWith("setqueueentryp"))
					op = eOperation.Move;
			}
			return op;
		}
	}

	private static final long serialVersionUID = 1L;

	private static ElemInfoTable[] elemInfoTable = new ElemInfoTable[1];
	static
	{
		elemInfoTable[0] = new ElemInfoTable(ElementName.QUEUEFILTER, 0x6666611111l);
	}
	private static AtrInfoTable[] atrInfoTable = new AtrInfoTable[5];
	static
	{
		atrInfoTable[0] = new AtrInfoTable(AttributeName.PRIORITY, 0x3333333333l, AttributeInfo.EnumAttributeType.integer, null, null);
		atrInfoTable[1] = new AtrInfoTable(AttributeName.NEXTQUEUEENTRYID, 0x3333333333l, AttributeInfo.EnumAttributeType.shortString, null, null);
		atrInfoTable[2] = new AtrInfoTable(AttributeName.PREVQUEUEENTRYID, 0x3333333333l, AttributeInfo.EnumAttributeType.shortString, null, null);
		atrInfoTable[3] = new AtrInfoTable(AttributeName.POSITION, 0x3333333333l, AttributeInfo.EnumAttributeType.integer, null, null);
		atrInfoTable[0] = new AtrInfoTable(AttributeName.OPERATION, 0x2222222222l, AttributeInfo.EnumAttributeType.NMTOKEN, null, null);
	}

	@Override
	protected ElementInfo getTheElementInfo()
	{
		return super.getTheElementInfo().updateReplace(elemInfoTable);
	}

	/**
	 * (36) set attribute Priority
	 *
	 * @param value the value to set the attribute to
	 */
	public void setPriority(final int value)
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

	/**
	 * (36) set attribute NextQueueEntryID
	 *
	 * @param value the value to set the attribute to
	 */
	public void setNextQueueEntryID(final String value)
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
	public void setPrevQueueEntryID(final String value)
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
	 * --------------------------------------------------------------------- Methods for Attribute Position ---------------------------------------------------------------------
	 */
	/**
	 * (36) set attribute Position
	 *
	 * @param value the value to set the attribute to
	 */
	public void setPosition(final int value)
	{
		setAttribute(AttributeName.POSITION, value, null);
	}

	/**
	 * (15) get int attribute Position
	 *
	 * @return int the value of the attribute
	 */
	public int getPosition()
	{
		return getIntAttribute(AttributeName.POSITION, null, 0);
	}

	/*
	 * --------------------------------------------------------------------- Methods for Attribute Position ---------------------------------------------------------------------
	 */
	/**
	 * (36) set attribute Position
	 *
	 * @param value the value to set the attribute to
	 */
	public void setOperation(final eOperation value)
	{
		setAttribute(AttributeName.OPERATION, value, null);
	}

	/**
	 * (15) get int attribute Position
	 *
	 * @return int the value of the attribute
	 */
	public eOperation getOperation()
	{
		return eOperation.getEnum(getAttribute(AttributeName.OPERATION));
	}

	/**
	 * Constructor for JDFModifyQueueEntryParams
	 *
	 * @param myOwnerDocument
	 * @param qualifiedName
	 */
	public JDFModifyQueueEntryParams(final CoreDocumentImpl myOwnerDocument, final String qualifiedName)
	{
		super(myOwnerDocument, qualifiedName);
	}

	/**
	 * Constructor for JDFModifyQueueEntryParams
	 *
	 * @param myOwnerDocument
	 * @param myNamespaceURI
	 * @param qualifiedName
	 */
	public JDFModifyQueueEntryParams(final CoreDocumentImpl myOwnerDocument, final String myNamespaceURI, final String qualifiedName)
	{
		super(myOwnerDocument, myNamespaceURI, qualifiedName);
	}

	/**
	 * Constructor for JDFModifyQueueEntryParams
	 *
	 * @param myOwnerDocument
	 * @param myNamespaceURI
	 * @param qualifiedName
	 * @param myLocalName
	 */
	public JDFModifyQueueEntryParams(final CoreDocumentImpl myOwnerDocument, final String myNamespaceURI, final String qualifiedName, final String myLocalName)
	{
		super(myOwnerDocument, myNamespaceURI, qualifiedName, myLocalName);
	}

	/*
	 * *********************************************************************** Element getter / setter ***********************************************************************
	 */

	/**
	 * (24) const get element QueueFilter
	 *
	 * @return JDFQueueFilter the element
	 */
	public JDFQueueFilter getQueueFilter()
	{
		return (JDFQueueFilter) getElement(ElementName.QUEUEFILTER, null, 0);
	}

	/**
	 * (25) getCreateQueueFilter
	 * 
	 * @return JDFQueueFilter the element
	 */
	public JDFQueueFilter getCreateQueueFilter()
	{
		return (JDFQueueFilter) getCreateElement_JDFElement(ElementName.QUEUEFILTER, null, 0);
	}

	/**
	 * (29) append element QueueFilter
	 *
	 * @return JDFQueueFilter the element @ if the element already exists
	 */
	public JDFQueueFilter appendQueueFilter()
	{
		return (JDFQueueFilter) appendElementN(ElementName.QUEUEFILTER, 1, null);
	}

}
