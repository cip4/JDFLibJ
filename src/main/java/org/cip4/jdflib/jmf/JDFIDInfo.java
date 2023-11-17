/*
 * The CIP4 Software License, Version 1.0
 *
 *
 * Copyright (c) 2001-2011 The International Cooperation for the Integration of
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
/**
 *
 * Copyright (c) 2001 Heidelberger Druckmaschinen AG, All Rights Reserved.
 *
 * JDFIDInfo.java
 *
 * Last changes
 *
 * 2002-07-02 JG - init() Also call super::init()
 *
 */
package org.cip4.jdflib.jmf;

import org.apache.xerces.dom.CoreDocumentImpl;
import org.cip4.jdflib.auto.JDFAutoIDInfo;
import org.cip4.jdflib.core.AttributeName;
import org.cip4.jdflib.core.ElemInfoTable;
import org.cip4.jdflib.core.ElementInfo;
import org.cip4.jdflib.core.ElementName;
import org.cip4.jdflib.core.JDFDoc;
import org.cip4.jdflib.ifaces.INodeIdentifiable;
import org.cip4.jdflib.node.JDFNode;
import org.cip4.jdflib.node.NodeIdentifier;
import org.cip4.jdflib.util.StringUtil;

/**
 *
 */
public class JDFIDInfo extends JDFAutoIDInfo implements INodeIdentifiable
{
	private static final long serialVersionUID = 1L;
	private static ElemInfoTable[] elemInfoTable = new ElemInfoTable[1];
	static
	{
		elemInfoTable[0] = new ElemInfoTable(ElementName.JDF, 0x66666611);
	}

	@Override
	protected ElementInfo getTheElementInfo()
	{
		return super.getTheElementInfo().updateReplace(elemInfoTable);
	}

	/**
	 * Constructor for JDFIDInfo
	 * 
	 * @param myOwnerDocument
	 * @param qualifiedName
	 */
	public JDFIDInfo(final CoreDocumentImpl myOwnerDocument, final String qualifiedName)
	{
		super(myOwnerDocument, qualifiedName);
	}

	/**
	 * Constructor for JDFIDInfo
	 * 
	 * @param myOwnerDocument
	 * @param myNamespaceURI
	 * @param qualifiedName
	 */
	public JDFIDInfo(final CoreDocumentImpl myOwnerDocument, final String myNamespaceURI, final String qualifiedName)
	{
		super(myOwnerDocument, myNamespaceURI, qualifiedName);
	}

	/**
	 * Constructor for JDFIDInfo
	 * 
	 * @param myOwnerDocument
	 * @param myNamespaceURI
	 * @param qualifiedName
	 * @param myLocalName
	 */
	public JDFIDInfo(final CoreDocumentImpl myOwnerDocument, final String myNamespaceURI, final String qualifiedName, final String myLocalName)
	{
		super(myOwnerDocument, myNamespaceURI, qualifiedName, myLocalName);
	}

	// **************************************** Methods
	// *********************************************
	/**
	 * toString
	 * 
	 * @return String
	 */
	@Override
	public String toString()
	{
		return "JDFIDInfo[  --> " + super.toString() + " ]";
	}

	/**
	 * creates a matching IDInfo for a node. Note: Does NOT copy the jdf element
	 * 
	 * @param n the jdf node to copy data from
	 * @param message message to place the new idinfo: if null a root idinfo is created
	 * @return the idinfo
	 */
	public static JDFIDInfo createFromJDF(final JDFNode n, final JDFMessage message)
	{
		final JDFIDInfo newInfo = message == null ? (JDFIDInfo) new JDFDoc(ElementName.IDINFO).getRoot() : message.appendIDInfo();
		if (n != null)
		{
			final String[] directCopy = { AttributeName.TYPE, AttributeName.TYPES, AttributeName.DESCRIPTIVENAME, AttributeName.JOBPARTID, AttributeName.CATEGORY };
			for (int i = 0; i < directCopy.length; i++)
			{
				newInfo.copyAttribute(directCopy[i], n);
			}
			newInfo.setJobID(StringUtil.getNonEmpty(n.getJobID(true)));
			final JDFNode parent = n.getParentJDF();
			if (parent != null)
			{
				newInfo.setParentJobID(StringUtil.getNonEmpty(parent.getJobID(true)));
				newInfo.setParentJobPartID(StringUtil.getNonEmpty(parent.getJobPartID(false)));
			}
		}
		return newInfo;
	}

	/**
	 * returns the parentjobid which defaults to jobID
	 * 
	 * @see org.cip4.jdflib.auto.JDFAutoIDInfo#getParentJobID()
	 */
	@Override
	public String getParentJobID()
	{
		final String s = StringUtil.getNonEmpty(super.getParentJobID());
		return s == null ? getJobID() : s;
	}

	/**
	 * @see org.cip4.jdflib.ifaces.INodeIdentifiable#getIdentifier()
	 */
	@Override
	public NodeIdentifier getIdentifier()
	{
		return new NodeIdentifier(getJobID(), getJobPartID(), null);
	}

	/**
	 * @see org.cip4.jdflib.ifaces.INodeIdentifiable#setIdentifier(org.cip4.jdflib.node.JDFNode.NodeIdentifier)
	 */
	@Override
	public void setIdentifier(final NodeIdentifier ni)
	{
		if (ni != null)
		{
			setJobID(ni.getJobID());
			setJobPartID(ni.getJobPartID());
		}
	}
}
