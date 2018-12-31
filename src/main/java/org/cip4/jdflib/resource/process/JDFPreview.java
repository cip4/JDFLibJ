/*
 * The CIP4 Software License, Version 1.0
 *
 *
 * Copyright (c) 2001-2018 The International Cooperation for the Integration of Processes in Prepress, Press and Postpress (CIP4). All rights reserved.
 *
 * Redistribution and use in source and binary forms, with or without modification, are permitted provided that the following conditions are met:
 *
 * 1. Redistributions of source code must retain the above copyright notice, this list of conditions and the following disclaimer.
 *
 * 2. Redistributions in binary form must reproduce the above copyright notice, this list of conditions and the following disclaimer in the documentation and/or other materials provided with the
 * distribution.
 *
 * 3. The end-user documentation included with the redistribution, if any, must include the following acknowledgment: "This product includes software developed by the The International Cooperation for
 * the Integration of Processes in Prepress, Press and Postpress (www.cip4.org)" Alternately, this acknowledgment may appear in the software itself, if and wherever such third-party acknowledgments
 * normally appear.
 *
 * 4. The names "CIP4" and "The International Cooperation for the Integration of Processes in Prepress, Press and Postpress" must not be used to endorse or promote products derived from this software
 * without prior written permission. For written permission, please contact info@cip4.org.
 *
 * 5. Products derived from this software may not be called "CIP4", nor may "CIP4" appear in their name, without prior written permission of the CIP4 organization
 *
 * Usage of this software in commercial products is subject to restrictions. For details please consult info@cip4.org.
 *
 * THIS SOFTWARE IS PROVIDED ``AS IS'' AND ANY EXPRESSED OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO, THE IMPLIED WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE ARE
 * DISCLAIMED. IN NO EVENT SHALL THE INTERNATIONAL COOPERATION FOR THE INTEGRATION OF PROCESSES IN PREPRESS, PRESS AND POSTPRESS OR ITS CONTRIBUTORS BE LIABLE FOR ANY DIRECT, INDIRECT, INCIDENTAL,
 * SPECIAL, EXEMPLARY, OR CONSEQUENTIAL DAMAGES (INCLUDING, BUT NOT LIMITED TO, PROCUREMENT OF SUBSTITUTE GOODS OR SERVICES; LOSS OF USE, DATA, OR PROFITS; OR BUSINESS INTERRUPTION) HOWEVER CAUSED AND
 * ON ANY THEORY OF LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY, OR TORT (INCLUDING NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE OF THIS SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY
 * OF SUCH DAMAGE. ====================================================================
 *
 * This software consists of voluntary contributions made by many individuals on behalf of the The International Cooperation for the Integration of Processes in Prepress, Press and Postpress and was
 * originally based on software copyright (c) 1999-2001, Heidelberger Druckmaschinen AG copyright (c) 1999-2001, Agfa-Gevaert N.V.
 *
 * For more information on The International Cooperation for the Integration of Processes in Prepress, Press and Postpress , please see <http://www.cip4.org/>.
 *
 *
 */
/**
 * ========================================================================== class JDFPreview extends JDFResource ==========================================================================
 *
 * @COPYRIGHT Heidelberger Druckmaschinen AG, 1999-2001 ALL RIGHTS RESERVED
 * @Author sabjon@topmail.de using a code generator Warning! very preliminary test version. Interface subject to change without prior notice!
 */

package org.cip4.jdflib.resource.process;

import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.enums.ValuedEnum;
import org.apache.xerces.dom.CoreDocumentImpl;
import org.cip4.jdflib.auto.JDFAutoPreview;
import org.cip4.jdflib.core.AtrInfoTable;
import org.cip4.jdflib.core.AttributeInfo;
import org.cip4.jdflib.core.AttributeName;
import org.cip4.jdflib.ifaces.IURLSetter;
import org.w3c.dom.DOMException;

/**
 * @author Rainer Prosi, Heidelberger Druckmaschinen *
 */
public class JDFPreview extends JDFAutoPreview implements IURLSetter
{
	private static final long serialVersionUID = 1L;
	/*
	 * we loosened the schema without loosening the spec to allow incomplete without URL, but still needed here
	 */
	private static AtrInfoTable[] atrInfoTable = new AtrInfoTable[1];
	static
	{
		atrInfoTable[0] = new AtrInfoTable(AttributeName.URL, 0x22222222, AttributeInfo.EnumAttributeType.URL, null, null);
	}

	@Override
	protected AttributeInfo getTheAttributeInfo()
	{
		return super.getTheAttributeInfo().updateReplace(atrInfoTable);
	}

	/**
	 * Constructor for JDFPreview
	 *
	 * @param myOwnerDocument
	 * @param qualifiedName
	 * @throws DOMException
	 *
	 */
	public JDFPreview(final CoreDocumentImpl myOwnerDocument, final String qualifiedName) throws DOMException
	{
		super(myOwnerDocument, qualifiedName);
	}

	/**
	 * Constructor for JDFPreview
	 *
	 * @param myOwnerDocument
	 * @param myNamespaceURI
	 * @param qualifiedName
	 *
	 * @throws DOMException
	 */
	public JDFPreview(final CoreDocumentImpl myOwnerDocument, final String myNamespaceURI, final String qualifiedName) throws DOMException
	{
		super(myOwnerDocument, myNamespaceURI, qualifiedName);
	}

	/**
	 * Constructor for JDFPreview
	 *
	 * @param myOwnerDocument
	 * @param myNamespaceURI
	 * @param qualifiedName
	 * @param myLocalName
	 *
	 * @throws DOMException
	 */
	public JDFPreview(final CoreDocumentImpl myOwnerDocument, final String myNamespaceURI, final String qualifiedName, final String myLocalName) throws DOMException
	{
		super(myOwnerDocument, myNamespaceURI, qualifiedName, myLocalName);
	}

	/**
	 * Enumeration strings for PreviewFileType
	 */
	public static class EnumPreviewFileType extends ValuedEnum
	{
		private static final long serialVersionUID = 1L;
		private static int m_startValue = 0;

		private EnumPreviewFileType(final String name)
		{
			super(name, m_startValue++);
		}

		public static EnumPreviewFileType getEnum(final String enumName)
		{
			return (EnumPreviewFileType) getEnum(EnumPreviewFileType.class, enumName);
		}

		public static EnumPreviewFileType getEnum(final int enumValue)
		{
			return (EnumPreviewFileType) getEnum(EnumPreviewFileType.class, enumValue);
		}

		public static Map getEnumMap()
		{
			return getEnumMap(EnumPreviewFileType.class);
		}

		public static List getEnumList()
		{
			return getEnumList(EnumPreviewFileType.class);
		}

		public static Iterator iterator()
		{
			return iterator(EnumPreviewFileType.class);
		}

		public static final EnumPreviewFileType PNG = new EnumPreviewFileType("PNG");
		public static final EnumPreviewFileType CIP3Multiple = new EnumPreviewFileType("CIP3Multiple");
		public static final EnumPreviewFileType CIP3Single = new EnumPreviewFileType("CIP3Single");
	}

	/**
	 *
	 * @see org.cip4.jdflib.auto.JDFAutoPreview#toString()
	 * @return
	 */
	@Override
	public String toString()
	{
		return "JDFPreview[  --> " + super.toString() + " ]";
	}

	/**
	 *
	 * @return the filename of this; null if not implemented
	 */
	@Override
	public String getUserFileName()
	{
		return null;
	}

	/**
	 * this method is provided for backwards compatibility with enum previewfiletype
	 *
	 * @param enumPreviewFileType
	 */
	public void setPreviewFileType(final EnumPreviewFileType enumPreviewFileType)
	{
		super.setPreviewFileType(enumPreviewFileType == null ? null : enumPreviewFileType.getName());
	}

	/**
	 * this method is provided for backwards compatibility with enum previewfiletype
	 *
	 * @return the value of the attribute
	 */
	public EnumPreviewFileType getEnumPreviewFileType()
	{
		return EnumPreviewFileType.getEnum(getAttribute(AttributeName.PREVIEWFILETYPE, null, EnumPreviewFileType.PNG.getName()));
	}

}
