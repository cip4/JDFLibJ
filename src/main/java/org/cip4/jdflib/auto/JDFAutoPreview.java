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
import org.cip4.jdflib.core.JDFComment;
import org.cip4.jdflib.core.JDFCoreConstants;
import org.cip4.jdflib.datatypes.JDFMatrix;
import org.cip4.jdflib.resource.JDFResource;
import org.cip4.jdflib.resource.process.JDFGeneralID;

/**
 *****************************************************************************
 * class JDFAutoPreview : public JDFResource
 *****************************************************************************
 * 
 */

public abstract class JDFAutoPreview extends JDFResource
{

	private static final long serialVersionUID = 1L;

	private static AtrInfoTable[] atrInfoTable = new AtrInfoTable[7];
	static
	{
		atrInfoTable[0] = new AtrInfoTable(AttributeName.PREVIEWFILETYPE, 0x3333333333l, AttributeInfo.EnumAttributeType.string, null, "PNG");
		atrInfoTable[1] = new AtrInfoTable(AttributeName.PREVIEWUSAGE, 0x3333333333l, AttributeInfo.EnumAttributeType.enumeration, EnumPreviewUsage.getEnum(0), "Separation");
		atrInfoTable[2] = new AtrInfoTable(AttributeName.URL, 0x3333333333l, AttributeInfo.EnumAttributeType.URL, null, null);
		atrInfoTable[3] = new AtrInfoTable(AttributeName.COMPENSATION, 0x3333333333l, AttributeInfo.EnumAttributeType.enumeration, EnumCompensation.getEnum(0), null);
		atrInfoTable[4] = new AtrInfoTable(AttributeName.CTM, 0x3333333333l, AttributeInfo.EnumAttributeType.matrix, null, null);
		atrInfoTable[5] = new AtrInfoTable(AttributeName.DIRECTORY, 0x3333333333l, AttributeInfo.EnumAttributeType.URL, null, null);
		atrInfoTable[6] = new AtrInfoTable(AttributeName.MIMETYPEDETAILS, 0x3333333333l, AttributeInfo.EnumAttributeType.string, null, null);
	}

	@Override
	protected AttributeInfo getTheAttributeInfo()
	{
		return super.getTheAttributeInfo().updateReplace(atrInfoTable);
	}

	private static ElemInfoTable[] elemInfoTable = new ElemInfoTable[2];
	static
	{
		elemInfoTable[0] = new ElemInfoTable(ElementName.COMMENT, 0x3333333333l);
		elemInfoTable[1] = new ElemInfoTable(ElementName.GENERALID, 0x3333333333l);
	}

	@Override
	protected ElementInfo getTheElementInfo()
	{
		return super.getTheElementInfo().updateReplace(elemInfoTable);
	}

	/**
	 * Constructor for JDFAutoPreview
	 *
	 * @param myOwnerDocument
	 * @param qualifiedName
	 */
	protected JDFAutoPreview(CoreDocumentImpl myOwnerDocument, String qualifiedName)
	{
		super(myOwnerDocument, qualifiedName);
	}

	/**
	 * Constructor for JDFAutoPreview
	 *
	 * @param myOwnerDocument
	 * @param myNamespaceURI
	 * @param qualifiedName
	 */
	protected JDFAutoPreview(CoreDocumentImpl myOwnerDocument, String myNamespaceURI, String qualifiedName)
	{
		super(myOwnerDocument, myNamespaceURI, qualifiedName);
	}

	/**
	 * Constructor for JDFAutoPreview
	 *
	 * @param myOwnerDocument
	 * @param myNamespaceURI
	 * @param qualifiedName
	 * @param myLocalName
	 */
	protected JDFAutoPreview(CoreDocumentImpl myOwnerDocument, String myNamespaceURI, String qualifiedName, String myLocalName)
	{
		super(myOwnerDocument, myNamespaceURI, qualifiedName, myLocalName);
	}

	/**
	 * @return true if ok
	 */
	@Override
	public boolean init()
	{
		final boolean bRet = super.init();
		setResourceClass(JDFResource.EnumResourceClass.Parameter);
		return bRet;
	}

	/**
	 * Enumeration strings for PreviewUsage
	 */

	@SuppressWarnings("rawtypes")
	public static class EnumPreviewUsage extends ValuedEnum
	{
		private static final long serialVersionUID = 1L;
		private static int m_startValue = 0;

		protected EnumPreviewUsage(String name)
		{
			super(name, m_startValue++);
		}

		/**
		 * @param enumName the string to convert
		 * @return the enum
		 */
		public static EnumPreviewUsage getEnum(String enumName)
		{
			return (EnumPreviewUsage) getEnum(EnumPreviewUsage.class, enumName);
		}

		/**
		 * @param enumValue the integer to convert
		 * @return the enum
		 */
		public static EnumPreviewUsage getEnum(int enumValue)
		{
			return (EnumPreviewUsage) getEnum(EnumPreviewUsage.class, enumValue);
		}

		/**
		 * @return the map of enums
		 */
		public static Map getEnumMap()
		{
			return getEnumMap(EnumPreviewUsage.class);
		}

		/**
		 * @return the list of enums
		 */
		public static List getEnumList()
		{
			return getEnumList(EnumPreviewUsage.class);
		}

		/**
		 * @return the iterator
		 */
		public static Iterator iterator()
		{
			return iterator(EnumPreviewUsage.class);
		}

		/**  */
		public static final EnumPreviewUsage Animation = new EnumPreviewUsage("Animation");
		/**  */
		public static final EnumPreviewUsage Identification = new EnumPreviewUsage("Identification");
		/**  */
		public static final EnumPreviewUsage Separation = new EnumPreviewUsage("Separation");
		/**  */
		public static final EnumPreviewUsage SeparatedThumbNail = new EnumPreviewUsage("SeparatedThumbNail");
		/**  */
		public static final EnumPreviewUsage SeparationRaw = new EnumPreviewUsage("SeparationRaw");
		/**  */
		public static final EnumPreviewUsage ThumbNail = new EnumPreviewUsage("ThumbNail");
		/**  */
		public static final EnumPreviewUsage Static3D = new EnumPreviewUsage("Static3D");
		/**  */
		public static final EnumPreviewUsage Viewable = new EnumPreviewUsage("Viewable");
	}

	/**
	 * Enumeration strings for Compensation
	 */

	@SuppressWarnings("rawtypes")
	public static class EnumCompensation extends ValuedEnum
	{
		private static final long serialVersionUID = 1L;
		private static int m_startValue = 0;

		protected EnumCompensation(String name)
		{
			super(name, m_startValue++);
		}

		/**
		 * @param enumName the string to convert
		 * @return the enum
		 */
		public static EnumCompensation getEnum(String enumName)
		{
			return (EnumCompensation) getEnum(EnumCompensation.class, enumName);
		}

		/**
		 * @param enumValue the integer to convert
		 * @return the enum
		 */
		public static EnumCompensation getEnum(int enumValue)
		{
			return (EnumCompensation) getEnum(EnumCompensation.class, enumValue);
		}

		/**
		 * @return the map of enums
		 */
		public static Map getEnumMap()
		{
			return getEnumMap(EnumCompensation.class);
		}

		/**
		 * @return the list of enums
		 */
		public static List getEnumList()
		{
			return getEnumList(EnumCompensation.class);
		}

		/**
		 * @return the iterator
		 */
		public static Iterator iterator()
		{
			return iterator(EnumCompensation.class);
		}

		/**  */
		public static final EnumCompensation Unknown = new EnumCompensation("Unknown");
		/**  */
		public static final EnumCompensation None = new EnumCompensation("None");
		/**  */
		public static final EnumCompensation Film = new EnumCompensation("Film");
		/**  */
		public static final EnumCompensation Plate = new EnumCompensation("Plate");
		/**  */
		public static final EnumCompensation Press = new EnumCompensation("Press");
	}

	/*
	 * ************************************************************************ Attribute getter / setter ************************************************************************
	 */

	/*
	 * --------------------------------------------------------------------- Methods for Attribute PreviewFileType
	 * ---------------------------------------------------------------------
	 */
	/**
	 * (36) set attribute PreviewFileType
	 *
	 * @param value the value to set the attribute to
	 */
	public void setPreviewFileType(String value)
	{
		setAttribute(AttributeName.PREVIEWFILETYPE, value, null);
	}

	/**
	 * (23) get String attribute PreviewFileType
	 *
	 * @return the value of the attribute
	 */
	public String getPreviewFileType()
	{
		return getAttribute(AttributeName.PREVIEWFILETYPE, null, "PNG");
	}

	/*
	 * --------------------------------------------------------------------- Methods for Attribute PreviewUsage
	 * ---------------------------------------------------------------------
	 */
	/**
	 * (5) set attribute PreviewUsage
	 *
	 * @param enumVar the enumVar to set the attribute to
	 */
	public void setPreviewUsage(EnumPreviewUsage enumVar)
	{
		setAttribute(AttributeName.PREVIEWUSAGE, enumVar == null ? null : enumVar.getName(), null);
	}

	/**
	 * (9) get attribute PreviewUsage
	 *
	 * @return the value of the attribute
	 */
	public EnumPreviewUsage getPreviewUsage()
	{
		return EnumPreviewUsage.getEnum(getAttribute(AttributeName.PREVIEWUSAGE, null, "Separation"));
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
	 * --------------------------------------------------------------------- Methods for Attribute Compensation
	 * ---------------------------------------------------------------------
	 */
	/**
	 * (5) set attribute Compensation
	 *
	 * @param enumVar the enumVar to set the attribute to
	 */
	public void setCompensation(EnumCompensation enumVar)
	{
		setAttribute(AttributeName.COMPENSATION, enumVar == null ? null : enumVar.getName(), null);
	}

	/**
	 * (9) get attribute Compensation
	 *
	 * @return the value of the attribute
	 */
	public EnumCompensation getCompensation()
	{
		return EnumCompensation.getEnum(getAttribute(AttributeName.COMPENSATION, null, null));
	}

	/*
	 * --------------------------------------------------------------------- Methods for Attribute CTM ---------------------------------------------------------------------
	 */
	/**
	 * (36) set attribute CTM
	 *
	 * @param value the value to set the attribute to
	 */
	public void setCTM(JDFMatrix value)
	{
		setAttribute(AttributeName.CTM, value, null);
	}

	/**
	 * (20) get JDFMatrix attribute CTM
	 *
	 * @return JDFMatrix the value of the attribute, null if a the attribute value is not a valid to create a JDFMatrix
	 */
	public JDFMatrix getCTM()
	{
		final String strAttrName = getAttribute(AttributeName.CTM, null, null);
		final JDFMatrix nPlaceHolder = JDFMatrix.createMatrix(strAttrName);
		return nPlaceHolder;
	}

	/*
	 * --------------------------------------------------------------------- Methods for Attribute Directory ---------------------------------------------------------------------
	 */
	/**
	 * (36) set attribute Directory
	 *
	 * @param value the value to set the attribute to
	 */
	public void setDirectory(String value)
	{
		setAttribute(AttributeName.DIRECTORY, value, null);
	}

	/**
	 * (23) get String attribute Directory
	 *
	 * @return the value of the attribute
	 */
	public String getDirectory()
	{
		return getAttribute(AttributeName.DIRECTORY, null, JDFCoreConstants.EMPTYSTRING);
	}

	/*
	 * --------------------------------------------------------------------- Methods for Attribute MimeTypeDetails
	 * ---------------------------------------------------------------------
	 */
	/**
	 * (36) set attribute MimeTypeDetails
	 *
	 * @param value the value to set the attribute to
	 */
	public void setMimeTypeDetails(String value)
	{
		setAttribute(AttributeName.MIMETYPEDETAILS, value, null);
	}

	/**
	 * (23) get String attribute MimeTypeDetails
	 *
	 * @return the value of the attribute
	 */
	public String getMimeTypeDetails()
	{
		return getAttribute(AttributeName.MIMETYPEDETAILS, null, JDFCoreConstants.EMPTYSTRING);
	}

	/*
	 * *********************************************************************** Element getter / setter ***********************************************************************
	 */

	/**
	 * (24) const get element Comment
	 *
	 * @return JDFComment the element
	 */
	public JDFComment getComment()
	{
		return (JDFComment) getElement(ElementName.COMMENT, null, 0);
	}

	/**
	 * (25) getCreateComment
	 * 
	 * @return JDFComment the element
	 */
	public JDFComment getCreateComment()
	{
		return (JDFComment) getCreateElement_JDFElement(ElementName.COMMENT, null, 0);
	}

	/**
	 * (26) getCreateComment
	 * 
	 * @param iSkip number of elements to skip
	 * @return JDFComment the element
	 */
	@Override
	public JDFComment getCreateComment(int iSkip)
	{
		return (JDFComment) getCreateElement_JDFElement(ElementName.COMMENT, null, iSkip);
	}

	/**
	 * (27) const get element Comment
	 *
	 * @param iSkip number of elements to skip
	 * @return JDFComment the element default is getComment(0)
	 */
	@Override
	public JDFComment getComment(int iSkip)
	{
		return (JDFComment) getElement(ElementName.COMMENT, null, iSkip);
	}

	/**
	 * Get all Comment from the current element
	 * 
	 * @return Collection<JDFComment>, null if none are available
	 */
	public Collection<JDFComment> getAllComment()
	{
		return getChildArrayByClass(JDFComment.class, false, 0);
	}

	/**
	 * (30) append element Comment
	 *
	 * @return JDFComment the element
	 */
	@Override
	public JDFComment appendComment()
	{
		return (JDFComment) appendElement(ElementName.COMMENT, null);
	}

	/**
	 * (24) const get element GeneralID
	 *
	 * @return JDFGeneralID the element
	 */
	public JDFGeneralID getGeneralID()
	{
		return (JDFGeneralID) getElement(ElementName.GENERALID, null, 0);
	}

	/**
	 * (25) getCreateGeneralID
	 * 
	 * @return JDFGeneralID the element
	 */
	public JDFGeneralID getCreateGeneralID()
	{
		return (JDFGeneralID) getCreateElement_JDFElement(ElementName.GENERALID, null, 0);
	}

	/**
	 * (26) getCreateGeneralID
	 * 
	 * @param iSkip number of elements to skip
	 * @return JDFGeneralID the element
	 */
	public JDFGeneralID getCreateGeneralID(int iSkip)
	{
		return (JDFGeneralID) getCreateElement_JDFElement(ElementName.GENERALID, null, iSkip);
	}

	/**
	 * (27) const get element GeneralID
	 *
	 * @param iSkip number of elements to skip
	 * @return JDFGeneralID the element default is getGeneralID(0)
	 */
	@Override
	public JDFGeneralID getGeneralID(int iSkip)
	{
		return (JDFGeneralID) getElement(ElementName.GENERALID, null, iSkip);
	}

	/**
	 * Get all GeneralID from the current element
	 * 
	 * @return Collection<JDFGeneralID>, null if none are available
	 */
	public Collection<JDFGeneralID> getAllGeneralID()
	{
		return getChildArrayByClass(JDFGeneralID.class, false, 0);
	}

	/**
	 * (30) append element GeneralID
	 *
	 * @return JDFGeneralID the element
	 */
	@Override
	public JDFGeneralID appendGeneralID()
	{
		return (JDFGeneralID) appendElement(ElementName.GENERALID, null);
	}

}
