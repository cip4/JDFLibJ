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

import org.apache.xerces.dom.CoreDocumentImpl;
import org.cip4.jdflib.core.AtrInfoTable;
import org.cip4.jdflib.core.AttributeInfo;
import org.cip4.jdflib.core.AttributeName;
import org.cip4.jdflib.core.JDFCoreConstants;
import org.cip4.jdflib.core.JDFElement;
import org.cip4.jdflib.util.JavaEnumUtil;

/**
 ***************************************************************************** class JDFAutoChangedPath : public JDFElement
 */

public abstract class JDFAutoChangedPath extends JDFElement
{

	private static final long serialVersionUID = 1L;

	private static AtrInfoTable[] atrInfoTable = new AtrInfoTable[4];
	static
	{
		atrInfoTable[0] = new AtrInfoTable(AttributeName.PATH, 0x2222222222l, AttributeInfo.EnumAttributeType.XPath, null, null);
		atrInfoTable[1] = new AtrInfoTable(AttributeName.MODIFICATION, 0x2222222222l, AttributeInfo.EnumAttributeType.enumeration,
				JavaEnumUtil.getEnum(EnumModification.class, 0), null);
		atrInfoTable[2] = new AtrInfoTable(AttributeName.OLDVALUE, 0x3333333333l, AttributeInfo.EnumAttributeType.string, null, null);
		atrInfoTable[3] = new AtrInfoTable(AttributeName.NEWVALUE, 0x3333333333l, AttributeInfo.EnumAttributeType.string, null, null);
	}

	@Override
	protected AttributeInfo getTheAttributeInfo()
	{
		return super.getTheAttributeInfo().updateReplace(atrInfoTable);
	}

	/**
	 * Constructor for JDFAutoChangedPath
	 *
	 * @param myOwnerDocument
	 * @param qualifiedName
	 */
	protected JDFAutoChangedPath(final CoreDocumentImpl myOwnerDocument, final String qualifiedName)
	{
		super(myOwnerDocument, qualifiedName);
	}

	/**
	 * Constructor for JDFAutoChangedPath
	 *
	 * @param myOwnerDocument
	 * @param myNamespaceURI
	 * @param qualifiedName
	 */
	protected JDFAutoChangedPath(final CoreDocumentImpl myOwnerDocument, final String myNamespaceURI, final String qualifiedName)
	{
		super(myOwnerDocument, myNamespaceURI, qualifiedName);
	}

	/**
	 * Constructor for JDFAutoChangedPath
	 *
	 * @param myOwnerDocument
	 * @param myNamespaceURI
	 * @param qualifiedName
	 * @param myLocalName
	 */
	protected JDFAutoChangedPath(final CoreDocumentImpl myOwnerDocument, final String myNamespaceURI, final String qualifiedName, final String myLocalName)
	{
		super(myOwnerDocument, myNamespaceURI, qualifiedName, myLocalName);
	}

	/**
	 * Enumeration strings for numModification
	 */

	public enum EnumModification
	{
		Create, Delete, Modify;

		public static EnumModification getEnum(final String val)
		{
			return JavaEnumUtil.getEnumIgnoreCase(EnumModification.class, val, null);
		}
	}/*
		 * ************************************************************************
		 * Attribute getter / setter
		 * ************************************************************************
		 */

	/*
	 * ---------------------------------------------------------------------
	 * Methods for Attribute Path
	 * ---------------------------------------------------------------------
	 */
	/**
	 * (36) set attribute Path
	 *
	 * @param value the value to set the attribute to
	 */
	public void setPath(final String value)
	{
		setAttribute(AttributeName.PATH, value, null);
	}

	/**
	 * (23) get String attribute Path
	 *
	 * @return the value of the attribute
	 */
	public String getPath()
	{
		return getAttribute(AttributeName.PATH, null, JDFCoreConstants.EMPTYSTRING);
	}

	/*
	 * ---------------------------------------------------------------------
	 * Methods for Attribute Modification
	 * ---------------------------------------------------------------------
	 */
	/**
	 * (5) set attribute Modification
	 *
	 * @param enumVar the enumVar to set the attribute to
	 */
	public void setModification(final EnumModification enumVar)
	{
		setAttribute(AttributeName.MODIFICATION, JavaEnumUtil.getName(enumVar), null);
	}

	/**
	 * (9) get attribute Modification
	 *
	 * @return the value of the attribute
	 */
	public EnumModification getModification()
	{
		return EnumModification.getEnum(getAttribute(AttributeName.MODIFICATION, null, null));
	}

	/*
	 * ---------------------------------------------------------------------
	 * Methods for Attribute OldValue
	 * ---------------------------------------------------------------------
	 */
	/**
	 * (36) set attribute OldValue
	 *
	 * @param value the value to set the attribute to
	 */
	public void setOldValue(final String value)
	{
		setAttribute(AttributeName.OLDVALUE, value, null);
	}

	/**
	 * (23) get String attribute OldValue
	 *
	 * @return the value of the attribute
	 */
	public String getOldValue()
	{
		return getAttribute(AttributeName.OLDVALUE, null, JDFCoreConstants.EMPTYSTRING);
	}

	/*
	 * ---------------------------------------------------------------------
	 * Methods for Attribute NewValue
	 * ---------------------------------------------------------------------
	 */
	/**
	 * (36) set attribute NewValue
	 *
	 * @param value the value to set the attribute to
	 */
	public void setNewValue(final String value)
	{
		setAttribute(AttributeName.NEWVALUE, value, null);
	}

	/**
	 * (23) get String attribute NewValue
	 *
	 * @return the value of the attribute
	 */
	public String getNewValue()
	{
		return getAttribute(AttributeName.NEWVALUE, null, JDFCoreConstants.EMPTYSTRING);
	}

}
