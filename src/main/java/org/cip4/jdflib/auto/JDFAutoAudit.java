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
 * class JDFAutoAudit : public JDFElement
 *****************************************************************************
 *
 */

public abstract class JDFAutoAudit extends JDFElement
{

	private static final long serialVersionUID = 1L;

	private static AtrInfoTable[] atrInfoTable = new AtrInfoTable[8];
	static
	{
		atrInfoTable[0] = new AtrInfoTable(AttributeName.AGENTNAME, 0x33333311, AttributeInfo.EnumAttributeType.string, null, null);
		atrInfoTable[1] = new AtrInfoTable(AttributeName.AGENTVERSION, 0x33333311, AttributeInfo.EnumAttributeType.string, null, null);
		atrInfoTable[2] = new AtrInfoTable(AttributeName.AUTHOR, 0x44443333, AttributeInfo.EnumAttributeType.string, null, null);
		atrInfoTable[3] = new AtrInfoTable(AttributeName.ID, 0x33333311, AttributeInfo.EnumAttributeType.ID, null, null);
		atrInfoTable[4] = new AtrInfoTable(AttributeName.QUEUEENTRYID, 0x33331111, AttributeInfo.EnumAttributeType.string, null, null);
		atrInfoTable[5] = new AtrInfoTable(AttributeName.REFID, 0x33333311, AttributeInfo.EnumAttributeType.IDREF, null, null);
		atrInfoTable[6] = new AtrInfoTable(AttributeName.SPAWNID, 0x33333331, AttributeInfo.EnumAttributeType.shortString, null, null);
		atrInfoTable[7] = new AtrInfoTable(AttributeName.TIMESTAMP, 0x22222222, AttributeInfo.EnumAttributeType.dateTime, null, null);
	}

	@Override
	protected AttributeInfo getTheAttributeInfo()
	{
		return super.getTheAttributeInfo().updateReplace(atrInfoTable);
	}

	/**
	 * Constructor for JDFAutoAudit
	 *
	 * @param myOwnerDocument
	 * @param qualifiedName
	 */
	protected JDFAutoAudit(CoreDocumentImpl myOwnerDocument, String qualifiedName)
	{
		super(myOwnerDocument, qualifiedName);
	}

	/**
	 * Constructor for JDFAutoAudit
	 *
	 * @param myOwnerDocument
	 * @param myNamespaceURI
	 * @param qualifiedName
	 */
	protected JDFAutoAudit(CoreDocumentImpl myOwnerDocument, String myNamespaceURI, String qualifiedName)
	{
		super(myOwnerDocument, myNamespaceURI, qualifiedName);
	}

	/**
	 * Constructor for JDFAutoAudit
	 *
	 * @param myOwnerDocument
	 * @param myNamespaceURI
	 * @param qualifiedName
	 * @param myLocalName
	 */
	protected JDFAutoAudit(CoreDocumentImpl myOwnerDocument, String myNamespaceURI, String qualifiedName, String myLocalName)
	{
		super(myOwnerDocument, myNamespaceURI, qualifiedName, myLocalName);
	}

	/* ************************************************************************
	 * Attribute getter / setter
	 * ************************************************************************
	 */

	/* ---------------------------------------------------------------------
	Methods for Attribute AgentName
	--------------------------------------------------------------------- */
	/**
	 * (36) set attribute AgentName
	 *
	 * @param value the value to set the attribute to
	 */
	public void setAgentName(String value)
	{
		setAttribute(AttributeName.AGENTNAME, value, null);
	}

	/**
	 * (23) get String attribute AgentName
	 *
	 * @return the value of the attribute
	 */
	public String getAgentName()
	{
		return getAttribute(AttributeName.AGENTNAME, null, JDFCoreConstants.EMPTYSTRING);
	}

	/* ---------------------------------------------------------------------
	Methods for Attribute AgentVersion
	--------------------------------------------------------------------- */
	/**
	 * (36) set attribute AgentVersion
	 *
	 * @param value the value to set the attribute to
	 */
	public void setAgentVersion(String value)
	{
		setAttribute(AttributeName.AGENTVERSION, value, null);
	}

	/**
	 * (23) get String attribute AgentVersion
	 *
	 * @return the value of the attribute
	 */
	public String getAgentVersion()
	{
		return getAttribute(AttributeName.AGENTVERSION, null, JDFCoreConstants.EMPTYSTRING);
	}

	/* ---------------------------------------------------------------------
	Methods for Attribute Author
	--------------------------------------------------------------------- */
	/**
	 * (36) set attribute Author
	 *
	 * @param value the value to set the attribute to
	 */
	public void setAuthor(String value)
	{
		setAttribute(AttributeName.AUTHOR, value, null);
	}

	/**
	 * (23) get String attribute Author
	 *
	 * @return the value of the attribute
	 */
	public String getAuthor()
	{
		return getAttribute(AttributeName.AUTHOR, null, JDFCoreConstants.EMPTYSTRING);
	}

	/* ---------------------------------------------------------------------
	Methods for Attribute ID
	--------------------------------------------------------------------- */
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

	/* ---------------------------------------------------------------------
	Methods for Attribute QueueEntryID
	--------------------------------------------------------------------- */
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

	/* ---------------------------------------------------------------------
	Methods for Attribute refID
	--------------------------------------------------------------------- */
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

	/* ---------------------------------------------------------------------
	Methods for Attribute SpawnID
	--------------------------------------------------------------------- */
	/**
	 * (36) set attribute SpawnID
	 *
	 * @param value the value to set the attribute to
	 */
	public void setSpawnID(String value)
	{
		setAttribute(AttributeName.SPAWNID, value, null);
	}

	/**
	 * (23) get String attribute SpawnID
	 *
	 * @return the value of the attribute
	 */
	public String getSpawnID()
	{
		return getAttribute(AttributeName.SPAWNID, null, JDFCoreConstants.EMPTYSTRING);
	}

	/* ---------------------------------------------------------------------
	Methods for Attribute TimeStamp
	--------------------------------------------------------------------- */
	/**
	 * (11) set attribute TimeStamp
	 *
	 * @param value the value to set the attribute to or null
	 */
	public void setTimeStamp(JDFDate value)
	{
		JDFDate date = value;
		if (date == null)
		{
			date = new JDFDate();
		}
		setAttribute(AttributeName.TIMESTAMP, date.getDateTimeISO(), null);
	}

	/**
	 * (12) get JDFDate attribute TimeStamp
	 *
	 * @return JDFDate the value of the attribute
	 */
	public JDFDate getTimeStamp()
	{
		final String str = getAttribute(AttributeName.TIMESTAMP, null, null);
		final JDFDate ret = JDFDate.createDate(str);
		return ret;
	}

}
