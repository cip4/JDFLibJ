/*
 * The CIP4 Software License, Version 1.0
 *
 *
 * Copyright (c) 2001-2024 The International Cooperation for the Integration of
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
import org.cip4.jdflib.core.ElemInfoTable;
import org.cip4.jdflib.core.ElementInfo;
import org.cip4.jdflib.core.ElementName;
import org.cip4.jdflib.resource.JDFInsertList;
import org.cip4.jdflib.resource.intent.JDFIntentResource;
import org.cip4.jdflib.span.JDFSpanGlueType;
import org.cip4.jdflib.span.JDFSpanMethod;

/**
 *****************************************************************************
 * class JDFAutoInsertingIntent : public JDFIntentResource
 *****************************************************************************
 * 
 */

public abstract class JDFAutoInsertingIntent extends JDFIntentResource
{

	private static final long serialVersionUID = 1L;

	private static ElemInfoTable[] elemInfoTable = new ElemInfoTable[3];
	static
	{
		elemInfoTable[0] = new ElemInfoTable(ElementName.GLUETYPE, 0x6666666666l);
		elemInfoTable[1] = new ElemInfoTable(ElementName.INSERTLIST, 0x5555555555l);
		elemInfoTable[2] = new ElemInfoTable(ElementName.METHOD, 0x6666666666l);
	}

	@Override
	protected ElementInfo getTheElementInfo()
	{
		return super.getTheElementInfo().updateReplace(elemInfoTable);
	}

	/**
	 * Constructor for JDFAutoInsertingIntent
	 *
	 * @param myOwnerDocument
	 * @param qualifiedName
	 */
	protected JDFAutoInsertingIntent(CoreDocumentImpl myOwnerDocument, String qualifiedName)
	{
		super(myOwnerDocument, qualifiedName);
	}

	/**
	 * Constructor for JDFAutoInsertingIntent
	 *
	 * @param myOwnerDocument
	 * @param myNamespaceURI
	 * @param qualifiedName
	 */
	protected JDFAutoInsertingIntent(CoreDocumentImpl myOwnerDocument, String myNamespaceURI, String qualifiedName)
	{
		super(myOwnerDocument, myNamespaceURI, qualifiedName);
	}

	/**
	 * Constructor for JDFAutoInsertingIntent
	 *
	 * @param myOwnerDocument
	 * @param myNamespaceURI
	 * @param qualifiedName
	 * @param myLocalName
	 */
	protected JDFAutoInsertingIntent(CoreDocumentImpl myOwnerDocument, String myNamespaceURI, String qualifiedName, String myLocalName)
	{
		super(myOwnerDocument, myNamespaceURI, qualifiedName, myLocalName);
	}

	/*
	 * *********************************************************************** Element getter / setter ***********************************************************************
	 */

	/**
	 * (24) const get element GlueType
	 *
	 * @return JDFSpanGlueType the element
	 */
	public JDFSpanGlueType getGlueType()
	{
		return (JDFSpanGlueType) getElement(ElementName.GLUETYPE, null, 0);
	}

	/**
	 * (25) getCreateGlueType
	 * 
	 * @return JDFSpanGlueType the element
	 */
	public JDFSpanGlueType getCreateGlueType()
	{
		return (JDFSpanGlueType) getCreateElement_JDFElement(ElementName.GLUETYPE, null, 0);
	}

	/**
	 * (29) append element GlueType
	 *
	 * @return JDFSpanGlueType the element @ if the element already exists
	 */
	public JDFSpanGlueType appendGlueType()
	{
		return (JDFSpanGlueType) appendElementN(ElementName.GLUETYPE, 1, null);
	}

	/**
	 * (24) const get element InsertList
	 *
	 * @return JDFInsertList the element
	 */
	public JDFInsertList getInsertList()
	{
		return (JDFInsertList) getElement(ElementName.INSERTLIST, null, 0);
	}

	/**
	 * (25) getCreateInsertList
	 * 
	 * @return JDFInsertList the element
	 */
	public JDFInsertList getCreateInsertList()
	{
		return (JDFInsertList) getCreateElement_JDFElement(ElementName.INSERTLIST, null, 0);
	}

	/**
	 * (29) append element InsertList
	 *
	 * @return JDFInsertList the element @ if the element already exists
	 */
	public JDFInsertList appendInsertList()
	{
		return (JDFInsertList) appendElementN(ElementName.INSERTLIST, 1, null);
	}

	/**
	 * (24) const get element Method
	 *
	 * @return JDFSpanMethod the element
	 */
	public JDFSpanMethod getMethod()
	{
		return (JDFSpanMethod) getElement(ElementName.METHOD, null, 0);
	}

	/**
	 * (25) getCreateMethod
	 * 
	 * @return JDFSpanMethod the element
	 */
	public JDFSpanMethod getCreateMethod()
	{
		return (JDFSpanMethod) getCreateElement_JDFElement(ElementName.METHOD, null, 0);
	}

	/**
	 * (29) append element Method
	 *
	 * @return JDFSpanMethod the element @ if the element already exists
	 */
	public JDFSpanMethod appendMethod()
	{
		return (JDFSpanMethod) appendElementN(ElementName.METHOD, 1, null);
	}

}
