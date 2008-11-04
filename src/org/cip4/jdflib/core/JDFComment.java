/*
 * The CIP4 Software License, Version 1.0
 *
 *
 * Copyright (c) 2001-2008 The International Cooperation for the Integration of
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
 *    Alternately, this acknowledgment mrSubRefay appear in the software itself,
 *    if and wherever such third-party acknowledgments normally appear.
 *
 * 4. The names "CIP4" and "The International Cooperation for the Integration of
 *    Processes in  Prepress, Press and Postpress" must
 *    not be used to endorse or promote products derived from this
 *    software without prior written permission. For written
 *    permission, please contact info@cip4.org.
 *
 * 5. Products derived from this software may not be called "CIP4",
 *    nor may "CIP4" appear in their name, without prior writtenrestartProcesses()
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
 * SPECIAL, EXEMPLARY, OR CONSEQUENTIrSubRefAL DAMAGES (INCLUDING, BUT NOT
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
 * originally based on software restartProcesses()
 * copyright (c) 1999-2001, Heidelberger Druckmaschinen AG
 * copyright (c) 1999-2001, Agfa-Gevaert N.V.
 *
 * For more information on The International Cooperation for the
 * Integration of Processes in  Prepress, Press and Postpress , please see
 * <http://www.cip4.org/>.
 *
 */
/**
 *
 * Copyright (c) 2001 Heidelberger Druckmaschinen AG, All Rights Reserved.
 *
 * JDFComment.java
 *
 * Last changes
 *
 */
package org.cip4.jdflib.core;

import org.apache.xerces.dom.CoreDocumentImpl;
import org.cip4.jdflib.auto.JDFAutoComment;

/**
 * this is the class for all text elements, obviously including <Comment> but also others, e.g ExtendedAddress
 * 
 * @author prosirai
 * 
 */
public class JDFComment extends JDFAutoComment
{
	private static final long serialVersionUID = 1L;

	/**
	 * Constructor for JDFComment
	 * 
	 * @param myOwnerDocument
	 * @param qualifiedName
	 */
	public JDFComment(CoreDocumentImpl myOwnerDocument, String qualifiedName)
	{
		super(myOwnerDocument, qualifiedName);
	}

	/**
	 * getIDPrefix
	 * 
	 * @return the ID prefix of JDFNode
	 */
	@Override
	public String getIDPrefix()
	{
		return "c";
	}

	/**
	 * Constructor for JDFComment
	 * 
	 * @param myOwnerDocument
	 * @param myNamespaceURI
	 * @param qualifiedName
	 */
	public JDFComment(CoreDocumentImpl myOwnerDocument, String myNamespaceURI, String qualifiedName)
	{
		super(myOwnerDocument, myNamespaceURI, qualifiedName);
	}

	/**
	 * Constructor for JDFComment
	 * 
	 * @param myOwnerDocument
	 * @param myNamespaceURI
	 * @param qualifiedName
	 * @param myLocalName
	 */
	public JDFComment(CoreDocumentImpl myOwnerDocument, String myNamespaceURI, String qualifiedName, String myLocalName)
	{
		super(myOwnerDocument, myNamespaceURI, qualifiedName, myLocalName);
	}

	// **************************************** Methods
	// *********************************************
	/**
	 * toString - StringRepresentation of JDFNode
	 * 
	 * @return String
	 */
	@Override
	public String toString()
	{
		return "JDFComment[  --> " + super.toString() + " ]";
	}

	/**
	 * init this comment by adding agentname, agentversion and id
	 * 
	 * @return boolean
	 */
	@Override
	public boolean init()
	{
		EnumVersion v = getVersion(true);
		if (v.getValue() >= EnumVersion.Version_1_3.getValue() && getLocalName().equals(ElementName.COMMENT))
		{
			appendAnchor(null);
			setAgentName(JDFAudit.getStaticAgentName());
			setAgentVersion(JDFAudit.getStaticAgentVersion());
		}
		return super.init();
	}

	/**
	 * version fixing routine for JDF
	 * 
	 * uses heuristics to modify this element and its children to be compatible with a given version<br>
	 * in general, it will be able to move from low to high versions but potentially fail when attempting to move from
	 * higher to lower versions
	 * 
	 * @param version version that the resulting element should correspond to
	 * @return true if successful
	 */
	@Override
	public boolean fixVersion(EnumVersion version)
	{
		if (version != null)
		{
			if (version.getValue() >= EnumVersion.Version_1_3.getValue() && getLocalName().equals(ElementName.COMMENT))
			{
				appendAnchor(null);
			}
			else
			{
				removeAttribute(AttributeName.ID);
				removeAttribute(AttributeName.AGENTNAME);
				removeAttribute(AttributeName.AGENTVERSION);
				removeAttribute(AttributeName.AUTHOR);
				removeAttribute(AttributeName.TIMESTAMP);
			}
		}
		return super.fixVersion(version);
	}

	/**
	 * Erases all empty text nodes in 'this' and its subelements If there any empty text nodes removes them. Does
	 * nothing in text nodes so that whitespace in comments, addresses etc. is always preserved
	 * 
	 * @param bTrimWhite trims whitespace of text, default = true
	 * @return int the number of removed nodes
	 */
	@Override
	public int eraseEmptyNodes(boolean bTrimWhite)
	{
		return bTrimWhite ? 0 : 0; // NOP to fool compiler. also retain
		// whitespace in comments
	}
}
