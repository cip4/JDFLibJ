/*
 * The CIP4 Software License, Version 1.0
 *
 *
 * Copyright (c) 2001-2020 The International Cooperation for the Integration of
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

import org.apache.xerces.dom.CoreDocumentImpl;
import org.cip4.jdflib.core.AtrInfoTable;
import org.cip4.jdflib.core.AttributeInfo;
import org.cip4.jdflib.core.AttributeName;
import org.cip4.jdflib.core.JDFCoreConstants;
import org.cip4.jdflib.core.JDFElement;
import org.cip4.jdflib.util.JDFDate;

/**
 *****************************************************************************
 * class JDFAutoActivity : public JDFElement
 *****************************************************************************
 *
 */

public abstract class JDFAutoActivity extends JDFElement
{

	private static final long serialVersionUID = 1L;

	private static AtrInfoTable[] atrInfoTable = new AtrInfoTable[5];
	static
	{
		atrInfoTable[0] = new AtrInfoTable(AttributeName.ACTIVITYID, 0x33333333, AttributeInfo.EnumAttributeType.shortString, null, null);
		atrInfoTable[1] = new AtrInfoTable(AttributeName.ACTIVITYNAME, 0x33333333, AttributeInfo.EnumAttributeType.shortString, null, null);
		atrInfoTable[2] = new AtrInfoTable(AttributeName.ENDTIME, 0x33333333, AttributeInfo.EnumAttributeType.dateTime, null, null);
		atrInfoTable[3] = new AtrInfoTable(AttributeName.PERSONALID, 0x33333333, AttributeInfo.EnumAttributeType.shortString, null, null);
		atrInfoTable[4] = new AtrInfoTable(AttributeName.STARTTIME, 0x33333333, AttributeInfo.EnumAttributeType.dateTime, null, null);
	}

	@Override
	protected AttributeInfo getTheAttributeInfo()
	{
		return super.getTheAttributeInfo().updateReplace(atrInfoTable);
	}

	/**
	 * Constructor for JDFAutoActivity
	 *
	 * @param myOwnerDocument
	 * @param qualifiedName
	 */
	protected JDFAutoActivity(CoreDocumentImpl myOwnerDocument, String qualifiedName)
	{
		super(myOwnerDocument, qualifiedName);
	}

	/**
	 * Constructor for JDFAutoActivity
	 *
	 * @param myOwnerDocument
	 * @param myNamespaceURI
	 * @param qualifiedName
	 */
	protected JDFAutoActivity(CoreDocumentImpl myOwnerDocument, String myNamespaceURI, String qualifiedName)
	{
		super(myOwnerDocument, myNamespaceURI, qualifiedName);
	}

	/**
	 * Constructor for JDFAutoActivity
	 *
	 * @param myOwnerDocument
	 * @param myNamespaceURI
	 * @param qualifiedName
	 * @param myLocalName
	 */
	protected JDFAutoActivity(CoreDocumentImpl myOwnerDocument, String myNamespaceURI, String qualifiedName, String myLocalName)
	{
		super(myOwnerDocument, myNamespaceURI, qualifiedName, myLocalName);
	}

	/* ************************************************************************
	 * Attribute getter / setter
	 * ************************************************************************
	 */

	/* ---------------------------------------------------------------------
	Methods for Attribute ActivityID
	--------------------------------------------------------------------- */
	/**
	 * (36) set attribute ActivityID
	 *
	 * @param value the value to set the attribute to
	 */
	public void setActivityID(String value)
	{
		setAttribute(AttributeName.ACTIVITYID, value, null);
	}

	/**
	 * (23) get String attribute ActivityID
	 *
	 * @return the value of the attribute
	 */
	public String getActivityID()
	{
		return getAttribute(AttributeName.ACTIVITYID, null, JDFCoreConstants.EMPTYSTRING);
	}

	/* ---------------------------------------------------------------------
	Methods for Attribute ActivityName
	--------------------------------------------------------------------- */
	/**
	 * (36) set attribute ActivityName
	 *
	 * @param value the value to set the attribute to
	 */
	public void setActivityName(String value)
	{
		setAttribute(AttributeName.ACTIVITYNAME, value, null);
	}

	/**
	 * (23) get String attribute ActivityName
	 *
	 * @return the value of the attribute
	 */
	public String getActivityName()
	{
		return getAttribute(AttributeName.ACTIVITYNAME, null, JDFCoreConstants.EMPTYSTRING);
	}

	/* ---------------------------------------------------------------------
	Methods for Attribute EndTime
	--------------------------------------------------------------------- */
	/**
	 * (11) set attribute EndTime
	 *
	 * @param value the value to set the attribute to or null
	 */
	public void setEndTime(JDFDate value)
	{
		JDFDate date = value;
		if (date == null)
		{
			date = new JDFDate();
		}
		setAttribute(AttributeName.ENDTIME, date.getDateTimeISO(), null);
	}

	/**
	 * (12) get JDFDate attribute EndTime
	 *
	 * @return JDFDate the value of the attribute
	 */
	public JDFDate getEndTime()
	{
		final String str = getAttribute(AttributeName.ENDTIME, null, null);
		final JDFDate ret = JDFDate.createDate(str);
		return ret;
	}

	/* ---------------------------------------------------------------------
	Methods for Attribute PersonalID
	--------------------------------------------------------------------- */
	/**
	 * (36) set attribute PersonalID
	 *
	 * @param value the value to set the attribute to
	 */
	public void setPersonalID(String value)
	{
		setAttribute(AttributeName.PERSONALID, value, null);
	}

	/**
	 * (23) get String attribute PersonalID
	 *
	 * @return the value of the attribute
	 */
	public String getPersonalID()
	{
		return getAttribute(AttributeName.PERSONALID, null, JDFCoreConstants.EMPTYSTRING);
	}

	/* ---------------------------------------------------------------------
	Methods for Attribute StartTime
	--------------------------------------------------------------------- */
	/**
	 * (11) set attribute StartTime
	 *
	 * @param value the value to set the attribute to or null
	 */
	public void setStartTime(JDFDate value)
	{
		JDFDate date = value;
		if (date == null)
		{
			date = new JDFDate();
		}
		setAttribute(AttributeName.STARTTIME, date.getDateTimeISO(), null);
	}

	/**
	 * (12) get JDFDate attribute StartTime
	 *
	 * @return JDFDate the value of the attribute
	 */
	public JDFDate getStartTime()
	{
		final String str = getAttribute(AttributeName.STARTTIME, null, null);
		final JDFDate ret = JDFDate.createDate(str);
		return ret;
	}

}
