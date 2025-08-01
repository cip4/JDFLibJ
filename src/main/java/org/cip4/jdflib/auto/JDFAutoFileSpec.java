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

import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Vector;

import org.apache.commons.lang.enums.ValuedEnum;
import org.apache.xerces.dom.CoreDocumentImpl;
import org.cip4.jdflib.core.AtrInfoTable;
import org.cip4.jdflib.core.AttributeInfo;
import org.cip4.jdflib.core.AttributeName;
import org.cip4.jdflib.core.ElemInfoTable;
import org.cip4.jdflib.core.ElementInfo;
import org.cip4.jdflib.core.ElementName;
import org.cip4.jdflib.core.JDFCoreConstants;
import org.cip4.jdflib.core.VElement;
import org.cip4.jdflib.resource.JDFNetworkHeader;
import org.cip4.jdflib.resource.JDFResource;
import org.cip4.jdflib.resource.process.JDFContainer;
import org.cip4.jdflib.resource.process.JDFDisposition;
import org.cip4.jdflib.resource.process.JDFFileAlias;
import org.cip4.jdflib.util.JavaEnumUtil;

/**
 *****************************************************************************
 * class JDFAutoFileSpec : public JDFResource
 *****************************************************************************
 * 
 */

public abstract class JDFAutoFileSpec extends JDFResource
{

	private static final long serialVersionUID = 1L;

	private static AtrInfoTable[] atrInfoTable = new AtrInfoTable[26];
	static
	{
		atrInfoTable[0] = new AtrInfoTable(AttributeName.COMPRESSION, 0x3333333311l, AttributeInfo.EnumAttributeType.NMTOKEN, null, "None");
		atrInfoTable[1] = new AtrInfoTable(AttributeName.APPLICATION, 0x3333333311l, AttributeInfo.EnumAttributeType.string, null, null);
		atrInfoTable[2] = new AtrInfoTable(AttributeName.APPOS, 0x4444333333l, AttributeInfo.EnumAttributeType.string, null, null);
		atrInfoTable[3] = new AtrInfoTable(AttributeName.APPVERSION, 0x3333333311l, AttributeInfo.EnumAttributeType.string, null, null);
		atrInfoTable[4] = new AtrInfoTable(AttributeName.CHECKSUM, 0x3333333331l, AttributeInfo.EnumAttributeType.hexBinary, null, null);
		atrInfoTable[5] = new AtrInfoTable(AttributeName.DISPOSITION, 0x4444444433l, AttributeInfo.EnumAttributeType.enumeration, EnumDisposition.getEnum(0), null);
		atrInfoTable[6] = new AtrInfoTable(AttributeName.DOCUMENTNATURALLANG, 0x3333333311l, AttributeInfo.EnumAttributeType.language, null, null);
		atrInfoTable[7] = new AtrInfoTable(AttributeName.ENCODING, 0x3333331111l, AttributeInfo.EnumAttributeType.string, null, null);
		atrInfoTable[8] = new AtrInfoTable(AttributeName.FILEFORMAT, 0x3333333311l, AttributeInfo.EnumAttributeType.string, null, null);
		atrInfoTable[9] = new AtrInfoTable(AttributeName.FILESIZE, 0x3333333311l, AttributeInfo.EnumAttributeType.integer, null, null);
		atrInfoTable[10] = new AtrInfoTable(AttributeName.FILETARGETDEVICEMODEL, 0x3333333311l, AttributeInfo.EnumAttributeType.string, null, null);
		atrInfoTable[11] = new AtrInfoTable(AttributeName.FILETEMPLATE, 0x3333333311l, AttributeInfo.EnumAttributeType.string, null, null);
		atrInfoTable[12] = new AtrInfoTable(AttributeName.FILEVERSION, 0x3333333331l, AttributeInfo.EnumAttributeType.string, null, null);
		atrInfoTable[13] = new AtrInfoTable(AttributeName.MIMETYPE, 0x3333333333l, AttributeInfo.EnumAttributeType.string, null, null);
		atrInfoTable[14] = new AtrInfoTable(AttributeName.MIMETYPEVERSION, 0x3333333311l, AttributeInfo.EnumAttributeType.string, null, null);
		atrInfoTable[15] = new AtrInfoTable(AttributeName.NPAGE, 0x3311111111l, AttributeInfo.EnumAttributeType.integer, null, null);
		atrInfoTable[16] = new AtrInfoTable(AttributeName.OVERWRITEPOLICY, 0x3333333311l, AttributeInfo.EnumAttributeType.enumeration, EnumOverwritePolicy.getEnum(0), null);
		atrInfoTable[17] = new AtrInfoTable(AttributeName.OSVERSION, 0x4444333333l, AttributeInfo.EnumAttributeType.string, null, null);
		atrInfoTable[18] = new AtrInfoTable(AttributeName.PAGEORDER, 0x3333333311l, AttributeInfo.EnumAttributeType.enumeration, EnumPageOrder.getEnum(0), null);
		atrInfoTable[19] = new AtrInfoTable(AttributeName.PASSWORD, 0x3333333111l, AttributeInfo.EnumAttributeType.string, null, null);
		atrInfoTable[20] = new AtrInfoTable(AttributeName.REQUESTQUALITY, 0x3333333111l, AttributeInfo.EnumAttributeType.double_, null, null);
		atrInfoTable[21] = new AtrInfoTable(AttributeName.RESOURCEUSAGE, 0x3333333311l, AttributeInfo.EnumAttributeType.NMTOKEN, null, null);
		atrInfoTable[22] = new AtrInfoTable(AttributeName.SEARCHDEPTH, 0x3333333311l, AttributeInfo.EnumAttributeType.integer, null, null);
		atrInfoTable[23] = new AtrInfoTable(AttributeName.UID, 0x3333333331l, AttributeInfo.EnumAttributeType.string, null, null);
		atrInfoTable[24] = new AtrInfoTable(AttributeName.URL, 0x3333333333l, AttributeInfo.EnumAttributeType.URL, null, null);
		atrInfoTable[25] = new AtrInfoTable(AttributeName.USERFILENAME, 0x3333333311l, AttributeInfo.EnumAttributeType.string, null, null);
	}

	@Override
	protected AttributeInfo getTheAttributeInfo()
	{
		return super.getTheAttributeInfo().updateReplace(atrInfoTable);
	}

	private static ElemInfoTable[] elemInfoTable = new ElemInfoTable[4];
	static
	{
		elemInfoTable[0] = new ElemInfoTable(ElementName.CONTAINER, 0x6666666611l);
		elemInfoTable[1] = new ElemInfoTable(ElementName.DISPOSITION, 0x6666666611l);
		elemInfoTable[2] = new ElemInfoTable(ElementName.FILEALIAS, 0x3333333311l);
		elemInfoTable[3] = new ElemInfoTable(ElementName.NETWORKHEADER, 0x3331111111l);
	}

	@Override
	protected ElementInfo getTheElementInfo()
	{
		return super.getTheElementInfo().updateReplace(elemInfoTable);
	}

	/**
	 * Constructor for JDFAutoFileSpec
	 *
	 * @param myOwnerDocument
	 * @param qualifiedName
	 */
	protected JDFAutoFileSpec(CoreDocumentImpl myOwnerDocument, String qualifiedName)
	{
		super(myOwnerDocument, qualifiedName);
	}

	/**
	 * Constructor for JDFAutoFileSpec
	 *
	 * @param myOwnerDocument
	 * @param myNamespaceURI
	 * @param qualifiedName
	 */
	protected JDFAutoFileSpec(CoreDocumentImpl myOwnerDocument, String myNamespaceURI, String qualifiedName)
	{
		super(myOwnerDocument, myNamespaceURI, qualifiedName);
	}

	/**
	 * Constructor for JDFAutoFileSpec
	 *
	 * @param myOwnerDocument
	 * @param myNamespaceURI
	 * @param qualifiedName
	 * @param myLocalName
	 */
	protected JDFAutoFileSpec(CoreDocumentImpl myOwnerDocument, String myNamespaceURI, String qualifiedName, String myLocalName)
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
	 * @return the resource Class
	 */
	@Override
	public EnumResourceClass getValidClass()
	{
		return JDFResource.EnumResourceClass.Parameter;
	}

	/**
	 * Enumeration strings for Disposition
	 */

	public enum EDisposition
	{
		Unlink, Delete, Retain;

		public static EDisposition getEnum(String val)
		{
			return JavaEnumUtil.getEnumIgnoreCase(EDisposition.class, val, null);
		}
	}

	/**
	 * Enumeration strings for Disposition
	 */

	@SuppressWarnings("rawtypes")
	public static class EnumDisposition extends ValuedEnum
	{
		private static final long serialVersionUID = 1L;
		private static int m_startValue = 0;

		protected EnumDisposition(String name)
		{
			super(name, m_startValue++);
		}

		/**
		 * @param enumName the string to convert
		 * @return the enum
		 */
		public static EnumDisposition getEnum(String enumName)
		{
			return (EnumDisposition) getEnum(EnumDisposition.class, enumName);
		}

		/**
		 * @param enumValue the integer to convert
		 * @return the enum
		 */
		public static EnumDisposition getEnum(int enumValue)
		{
			return (EnumDisposition) getEnum(EnumDisposition.class, enumValue);
		}

		/**
		 * @return the map of enums
		 */
		public static Map getEnumMap()
		{
			return getEnumMap(EnumDisposition.class);
		}

		/**
		 * @return the list of enums
		 */
		public static List getEnumList()
		{
			return getEnumList(EnumDisposition.class);
		}

		/**
		 * @return the iterator
		 */
		public static Iterator iterator()
		{
			return iterator(EnumDisposition.class);
		}

		/**  */
		public static final EnumDisposition Unlink = new EnumDisposition("Unlink");
		/**  */
		public static final EnumDisposition Delete = new EnumDisposition("Delete");
		/**  */
		public static final EnumDisposition Retain = new EnumDisposition("Retain");
	}

	/**
	 * Enumeration strings for OverwritePolicy
	 */

	public enum EOverwritePolicy
	{
		Overwrite, RenameNew, RenameOld, NewVersion, OperatorIntervention, Abort;

		public static EOverwritePolicy getEnum(String val)
		{
			return JavaEnumUtil.getEnumIgnoreCase(EOverwritePolicy.class, val, null);
		}
	}

	/**
	 * Enumeration strings for OverwritePolicy
	 */

	@SuppressWarnings("rawtypes")
	public static class EnumOverwritePolicy extends ValuedEnum
	{
		private static final long serialVersionUID = 1L;
		private static int m_startValue = 0;

		protected EnumOverwritePolicy(String name)
		{
			super(name, m_startValue++);
		}

		/**
		 * @param enumName the string to convert
		 * @return the enum
		 */
		public static EnumOverwritePolicy getEnum(String enumName)
		{
			return (EnumOverwritePolicy) getEnum(EnumOverwritePolicy.class, enumName);
		}

		/**
		 * @param enumValue the integer to convert
		 * @return the enum
		 */
		public static EnumOverwritePolicy getEnum(int enumValue)
		{
			return (EnumOverwritePolicy) getEnum(EnumOverwritePolicy.class, enumValue);
		}

		/**
		 * @return the map of enums
		 */
		public static Map getEnumMap()
		{
			return getEnumMap(EnumOverwritePolicy.class);
		}

		/**
		 * @return the list of enums
		 */
		public static List getEnumList()
		{
			return getEnumList(EnumOverwritePolicy.class);
		}

		/**
		 * @return the iterator
		 */
		public static Iterator iterator()
		{
			return iterator(EnumOverwritePolicy.class);
		}

		/**  */
		public static final EnumOverwritePolicy Overwrite = new EnumOverwritePolicy("Overwrite");
		/**  */
		public static final EnumOverwritePolicy RenameNew = new EnumOverwritePolicy("RenameNew");
		/**  */
		public static final EnumOverwritePolicy RenameOld = new EnumOverwritePolicy("RenameOld");
		/**  */
		public static final EnumOverwritePolicy NewVersion = new EnumOverwritePolicy("NewVersion");
		/**  */
		public static final EnumOverwritePolicy OperatorIntervention = new EnumOverwritePolicy("OperatorIntervention");
		/**  */
		public static final EnumOverwritePolicy Abort = new EnumOverwritePolicy("Abort");
	}

	/**
	 * Enumeration strings for PageOrder
	 */

	public enum EPageOrder
	{
		Ascending, Descending;

		public static EPageOrder getEnum(String val)
		{
			return JavaEnumUtil.getEnumIgnoreCase(EPageOrder.class, val, null);
		}
	}

	/**
	 * Enumeration strings for PageOrder
	 */

	@SuppressWarnings("rawtypes")
	public static class EnumPageOrder extends ValuedEnum
	{
		private static final long serialVersionUID = 1L;
		private static int m_startValue = 0;

		protected EnumPageOrder(String name)
		{
			super(name, m_startValue++);
		}

		/**
		 * @param enumName the string to convert
		 * @return the enum
		 */
		public static EnumPageOrder getEnum(String enumName)
		{
			return (EnumPageOrder) getEnum(EnumPageOrder.class, enumName);
		}

		/**
		 * @param enumValue the integer to convert
		 * @return the enum
		 */
		public static EnumPageOrder getEnum(int enumValue)
		{
			return (EnumPageOrder) getEnum(EnumPageOrder.class, enumValue);
		}

		/**
		 * @return the map of enums
		 */
		public static Map getEnumMap()
		{
			return getEnumMap(EnumPageOrder.class);
		}

		/**
		 * @return the list of enums
		 */
		public static List getEnumList()
		{
			return getEnumList(EnumPageOrder.class);
		}

		/**
		 * @return the iterator
		 */
		public static Iterator iterator()
		{
			return iterator(EnumPageOrder.class);
		}

		/**  */
		public static final EnumPageOrder Ascending = new EnumPageOrder("Ascending");
		/**  */
		public static final EnumPageOrder Descending = new EnumPageOrder("Descending");
	}

	/*
	 * ************************************************************************ Attribute getter / setter ************************************************************************
	 */

	/*
	 * --------------------------------------------------------------------- Methods for Attribute Compression ---------------------------------------------------------------------
	 */
	/**
	 * (36) set attribute Compression
	 *
	 * @param value the value to set the attribute to
	 */
	public void setCompression(String value)
	{
		setAttribute(AttributeName.COMPRESSION, value, null);
	}

	/**
	 * (23) get String attribute Compression
	 *
	 * @return the value of the attribute
	 */
	public String getCompression()
	{
		return getAttribute(AttributeName.COMPRESSION, null, "None");
	}

	/*
	 * --------------------------------------------------------------------- Methods for Attribute Application ---------------------------------------------------------------------
	 */
	/**
	 * (36) set attribute Application
	 *
	 * @param value the value to set the attribute to
	 */
	public void setApplication(String value)
	{
		setAttribute(AttributeName.APPLICATION, value, null);
	}

	/**
	 * (23) get String attribute Application
	 *
	 * @return the value of the attribute
	 */
	public String getApplication()
	{
		return getAttribute(AttributeName.APPLICATION, null, JDFCoreConstants.EMPTYSTRING);
	}

	/*
	 * --------------------------------------------------------------------- Methods for Attribute AppOS ---------------------------------------------------------------------
	 */
	/**
	 * (36) set attribute AppOS
	 *
	 * @param value the value to set the attribute to
	 */
	public void setAppOS(String value)
	{
		setAttribute(AttributeName.APPOS, value, null);
	}

	/**
	 * (23) get String attribute AppOS
	 *
	 * @return the value of the attribute
	 */
	public String getAppOS()
	{
		return getAttribute(AttributeName.APPOS, null, JDFCoreConstants.EMPTYSTRING);
	}

	/*
	 * --------------------------------------------------------------------- Methods for Attribute AppVersion ---------------------------------------------------------------------
	 */
	/**
	 * (36) set attribute AppVersion
	 *
	 * @param value the value to set the attribute to
	 */
	public void setAppVersion(String value)
	{
		setAttribute(AttributeName.APPVERSION, value, null);
	}

	/**
	 * (23) get String attribute AppVersion
	 *
	 * @return the value of the attribute
	 */
	public String getAppVersion()
	{
		return getAttribute(AttributeName.APPVERSION, null, JDFCoreConstants.EMPTYSTRING);
	}

	/*
	 * --------------------------------------------------------------------- Methods for Attribute CheckSum ---------------------------------------------------------------------
	 */
	/**
	 * (36) set attribute CheckSum
	 *
	 * @param value the value to set the attribute to
	 */
	public void setCheckSum(String value)
	{
		setAttribute(AttributeName.CHECKSUM, value, null);
	}

	/**
	 * (23) get String attribute CheckSum
	 *
	 * @return the value of the attribute
	 */
	public String getCheckSum()
	{
		return getAttribute(AttributeName.CHECKSUM, null, JDFCoreConstants.EMPTYSTRING);
	}

	/*
	 * --------------------------------------------------------------------- Methods for Attribute Disposition ---------------------------------------------------------------------
	 */
	/**
	 * (5) set attribute Disposition
	 *
	 * @param enumVar the enumVar to set the attribute to
	 */
	public void setDisposition(EDisposition enumVar)
	{
		setAttribute(AttributeName.DISPOSITION, enumVar == null ? null : enumVar.name(), null);
	}

	/**
	 * (9) get attribute Disposition
	 *
	 * @return the value of the attribute
	 */
	public EDisposition getEDisposition()
	{
		return EDisposition.getEnum(getAttribute(AttributeName.DISPOSITION, null, null));
	}

	/*
	 * --------------------------------------------------------------------- Methods for Attribute Disposition ---------------------------------------------------------------------
	 */
	/**
	 * (5) set attribute Disposition
	 *
	 * @param enumVar the enumVar to set the attribute to
	 * @deprecated use java.lang.enum
	 */
	@Deprecated
	public void setDisposition(EnumDisposition enumVar)
	{
		setAttribute(AttributeName.DISPOSITION, enumVar == null ? null : enumVar.getName(), null);
	}

	/**
	 * (9) get attribute Disposition
	 *
	 * @return the value of the attribute
	 */
	public EnumDisposition getDisposition()
	{
		return EnumDisposition.getEnum(getAttribute(AttributeName.DISPOSITION, null, null));
	}

	/*
	 * --------------------------------------------------------------------- Methods for Attribute DocumentNaturalLang
	 * ---------------------------------------------------------------------
	 */
	/**
	 * (36) set attribute DocumentNaturalLang
	 *
	 * @param value the value to set the attribute to
	 */
	public void setDocumentNaturalLang(String value)
	{
		setAttribute(AttributeName.DOCUMENTNATURALLANG, value, null);
	}

	/**
	 * (23) get String attribute DocumentNaturalLang
	 *
	 * @return the value of the attribute
	 */
	public String getDocumentNaturalLang()
	{
		return getAttribute(AttributeName.DOCUMENTNATURALLANG, null, JDFCoreConstants.EMPTYSTRING);
	}

	/*
	 * --------------------------------------------------------------------- Methods for Attribute Encoding ---------------------------------------------------------------------
	 */
	/**
	 * (36) set attribute Encoding
	 *
	 * @param value the value to set the attribute to
	 */
	public void setEncoding(String value)
	{
		setAttribute(AttributeName.ENCODING, value, null);
	}

	/**
	 * (23) get String attribute Encoding
	 *
	 * @return the value of the attribute
	 */
	public String getEncoding()
	{
		return getAttribute(AttributeName.ENCODING, null, JDFCoreConstants.EMPTYSTRING);
	}

	/*
	 * --------------------------------------------------------------------- Methods for Attribute FileFormat ---------------------------------------------------------------------
	 */
	/**
	 * (36) set attribute FileFormat
	 *
	 * @param value the value to set the attribute to
	 */
	public void setFileFormat(String value)
	{
		setAttribute(AttributeName.FILEFORMAT, value, null);
	}

	/**
	 * (23) get String attribute FileFormat
	 *
	 * @return the value of the attribute
	 */
	public String getFileFormat()
	{
		return getAttribute(AttributeName.FILEFORMAT, null, JDFCoreConstants.EMPTYSTRING);
	}

	/*
	 * --------------------------------------------------------------------- Methods for Attribute FileSize ---------------------------------------------------------------------
	 */
	/**
	 * (36) set attribute FileSize
	 *
	 * @param value the value to set the attribute to
	 */
	public void setFileSize(String value)
	{
		setAttribute(AttributeName.FILESIZE, value, null);
	}

	/**
	 * (23) get String attribute FileSize
	 *
	 * @return the value of the attribute
	 */
	public String getFileSize()
	{
		return getAttribute(AttributeName.FILESIZE, null, JDFCoreConstants.EMPTYSTRING);
	}

	/*
	 * --------------------------------------------------------------------- Methods for Attribute FileTargetDeviceModel
	 * ---------------------------------------------------------------------
	 */
	/**
	 * (36) set attribute FileTargetDeviceModel
	 *
	 * @param value the value to set the attribute to
	 */
	public void setFileTargetDeviceModel(String value)
	{
		setAttribute(AttributeName.FILETARGETDEVICEMODEL, value, null);
	}

	/**
	 * (23) get String attribute FileTargetDeviceModel
	 *
	 * @return the value of the attribute
	 */
	public String getFileTargetDeviceModel()
	{
		return getAttribute(AttributeName.FILETARGETDEVICEMODEL, null, JDFCoreConstants.EMPTYSTRING);
	}

	/*
	 * --------------------------------------------------------------------- Methods for Attribute FileTemplate
	 * ---------------------------------------------------------------------
	 */
	/**
	 * (36) set attribute FileTemplate
	 *
	 * @param value the value to set the attribute to
	 */
	public void setFileTemplate(String value)
	{
		setAttribute(AttributeName.FILETEMPLATE, value, null);
	}

	/**
	 * (23) get String attribute FileTemplate
	 *
	 * @return the value of the attribute
	 */
	public String getFileTemplate()
	{
		return getAttribute(AttributeName.FILETEMPLATE, null, JDFCoreConstants.EMPTYSTRING);
	}

	/*
	 * --------------------------------------------------------------------- Methods for Attribute FileVersion ---------------------------------------------------------------------
	 */
	/**
	 * (36) set attribute FileVersion
	 *
	 * @param value the value to set the attribute to
	 */
	public void setFileVersion(String value)
	{
		setAttribute(AttributeName.FILEVERSION, value, null);
	}

	/**
	 * (23) get String attribute FileVersion
	 *
	 * @return the value of the attribute
	 */
	public String getFileVersion()
	{
		return getAttribute(AttributeName.FILEVERSION, null, JDFCoreConstants.EMPTYSTRING);
	}

	/*
	 * --------------------------------------------------------------------- Methods for Attribute MimeType ---------------------------------------------------------------------
	 */
	/**
	 * (36) set attribute MimeType
	 *
	 * @param value the value to set the attribute to
	 */
	public void setMimeType(String value)
	{
		setAttribute(AttributeName.MIMETYPE, value, null);
	}

	/**
	 * (23) get String attribute MimeType
	 *
	 * @return the value of the attribute
	 */
	public String getMimeType()
	{
		return getAttribute(AttributeName.MIMETYPE, null, JDFCoreConstants.EMPTYSTRING);
	}

	/*
	 * --------------------------------------------------------------------- Methods for Attribute MimeTypeVersion
	 * ---------------------------------------------------------------------
	 */
	/**
	 * (36) set attribute MimeTypeVersion
	 *
	 * @param value the value to set the attribute to
	 */
	public void setMimeTypeVersion(String value)
	{
		setAttribute(AttributeName.MIMETYPEVERSION, value, null);
	}

	/**
	 * (23) get String attribute MimeTypeVersion
	 *
	 * @return the value of the attribute
	 */
	public String getMimeTypeVersion()
	{
		return getAttribute(AttributeName.MIMETYPEVERSION, null, JDFCoreConstants.EMPTYSTRING);
	}

	/*
	 * --------------------------------------------------------------------- Methods for Attribute NPage ---------------------------------------------------------------------
	 */
	/**
	 * (36) set attribute NPage
	 *
	 * @param value the value to set the attribute to
	 */
	public void setNPage(int value)
	{
		setAttribute(AttributeName.NPAGE, value, null);
	}

	/**
	 * (15) get int attribute NPage
	 *
	 * @return int the value of the attribute
	 */
	public int getNPage()
	{
		return getIntAttribute(AttributeName.NPAGE, null, 0);
	}

	/*
	 * --------------------------------------------------------------------- Methods for Attribute OverwritePolicy
	 * ---------------------------------------------------------------------
	 */
	/**
	 * (5) set attribute OverwritePolicy
	 *
	 * @param enumVar the enumVar to set the attribute to
	 */
	public void setOverwritePolicy(EOverwritePolicy enumVar)
	{
		setAttribute(AttributeName.OVERWRITEPOLICY, enumVar == null ? null : enumVar.name(), null);
	}

	/**
	 * (9) get attribute OverwritePolicy
	 *
	 * @return the value of the attribute
	 */
	public EOverwritePolicy getEOverwritePolicy()
	{
		return EOverwritePolicy.getEnum(getAttribute(AttributeName.OVERWRITEPOLICY, null, null));
	}

	/*
	 * --------------------------------------------------------------------- Methods for Attribute OverwritePolicy
	 * ---------------------------------------------------------------------
	 */
	/**
	 * (5) set attribute OverwritePolicy
	 *
	 * @param enumVar the enumVar to set the attribute to
	 * @deprecated use java.lang.enum
	 */
	@Deprecated
	public void setOverwritePolicy(EnumOverwritePolicy enumVar)
	{
		setAttribute(AttributeName.OVERWRITEPOLICY, enumVar == null ? null : enumVar.getName(), null);
	}

	/**
	 * (9) get attribute OverwritePolicy
	 *
	 * @return the value of the attribute
	 */
	public EnumOverwritePolicy getOverwritePolicy()
	{
		return EnumOverwritePolicy.getEnum(getAttribute(AttributeName.OVERWRITEPOLICY, null, null));
	}

	/*
	 * --------------------------------------------------------------------- Methods for Attribute OSVersion ---------------------------------------------------------------------
	 */
	/**
	 * (36) set attribute OSVersion
	 *
	 * @param value the value to set the attribute to
	 */
	public void setOSVersion(String value)
	{
		setAttribute(AttributeName.OSVERSION, value, null);
	}

	/**
	 * (23) get String attribute OSVersion
	 *
	 * @return the value of the attribute
	 */
	public String getOSVersion()
	{
		return getAttribute(AttributeName.OSVERSION, null, JDFCoreConstants.EMPTYSTRING);
	}

	/*
	 * --------------------------------------------------------------------- Methods for Attribute PageOrder ---------------------------------------------------------------------
	 */
	/**
	 * (5) set attribute PageOrder
	 *
	 * @param enumVar the enumVar to set the attribute to
	 */
	public void setPageOrder(EPageOrder enumVar)
	{
		setAttribute(AttributeName.PAGEORDER, enumVar == null ? null : enumVar.name(), null);
	}

	/**
	 * (9) get attribute PageOrder
	 *
	 * @return the value of the attribute
	 */
	public EPageOrder getEPageOrder()
	{
		return EPageOrder.getEnum(getAttribute(AttributeName.PAGEORDER, null, null));
	}

	/*
	 * --------------------------------------------------------------------- Methods for Attribute PageOrder ---------------------------------------------------------------------
	 */
	/**
	 * (5) set attribute PageOrder
	 *
	 * @param enumVar the enumVar to set the attribute to
	 * @deprecated use java.lang.enum
	 */
	@Deprecated
	public void setPageOrder(EnumPageOrder enumVar)
	{
		setAttribute(AttributeName.PAGEORDER, enumVar == null ? null : enumVar.getName(), null);
	}

	/**
	 * (9) get attribute PageOrder
	 *
	 * @return the value of the attribute
	 */
	public EnumPageOrder getPageOrder()
	{
		return EnumPageOrder.getEnum(getAttribute(AttributeName.PAGEORDER, null, null));
	}

	/*
	 * --------------------------------------------------------------------- Methods for Attribute Password ---------------------------------------------------------------------
	 */
	/**
	 * (36) set attribute Password
	 *
	 * @param value the value to set the attribute to
	 */
	public void setPassword(String value)
	{
		setAttribute(AttributeName.PASSWORD, value, null);
	}

	/**
	 * (23) get String attribute Password
	 *
	 * @return the value of the attribute
	 */
	public String getPassword()
	{
		return getAttribute(AttributeName.PASSWORD, null, JDFCoreConstants.EMPTYSTRING);
	}

	/*
	 * --------------------------------------------------------------------- Methods for Attribute RequestQuality
	 * ---------------------------------------------------------------------
	 */
	/**
	 * (36) set attribute RequestQuality
	 *
	 * @param value the value to set the attribute to
	 */
	public void setRequestQuality(double value)
	{
		setAttribute(AttributeName.REQUESTQUALITY, value, null);
	}

	/**
	 * (17) get double attribute RequestQuality
	 *
	 * @return double the value of the attribute
	 */
	public double getRequestQuality()
	{
		return getRealAttribute(AttributeName.REQUESTQUALITY, null, 0.0);
	}

	/*
	 * --------------------------------------------------------------------- Methods for Attribute ResourceUsage
	 * ---------------------------------------------------------------------
	 */
	/**
	 * (36) set attribute ResourceUsage
	 *
	 * @param value the value to set the attribute to
	 */
	public void setResourceUsage(String value)
	{
		setAttribute(AttributeName.RESOURCEUSAGE, value, null);
	}

	/**
	 * (23) get String attribute ResourceUsage
	 *
	 * @return the value of the attribute
	 */
	public String getResourceUsage()
	{
		return getAttribute(AttributeName.RESOURCEUSAGE, null, JDFCoreConstants.EMPTYSTRING);
	}

	/*
	 * --------------------------------------------------------------------- Methods for Attribute SearchDepth ---------------------------------------------------------------------
	 */
	/**
	 * (36) set attribute SearchDepth
	 *
	 * @param value the value to set the attribute to
	 */
	public void setSearchDepth(int value)
	{
		setAttribute(AttributeName.SEARCHDEPTH, value, null);
	}

	/**
	 * (15) get int attribute SearchDepth
	 *
	 * @return int the value of the attribute
	 */
	public int getSearchDepth()
	{
		return getIntAttribute(AttributeName.SEARCHDEPTH, null, 0);
	}

	/*
	 * --------------------------------------------------------------------- Methods for Attribute UID ---------------------------------------------------------------------
	 */
	/**
	 * (36) set attribute UID
	 *
	 * @param value the value to set the attribute to
	 */
	public void setUID(String value)
	{
		setAttribute(AttributeName.UID, value, null);
	}

	/**
	 * (23) get String attribute UID
	 *
	 * @return the value of the attribute
	 */
	public String getUID()
	{
		return getAttribute(AttributeName.UID, null, JDFCoreConstants.EMPTYSTRING);
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
	 * --------------------------------------------------------------------- Methods for Attribute UserFileName
	 * ---------------------------------------------------------------------
	 */
	/**
	 * (36) set attribute UserFileName
	 *
	 * @param value the value to set the attribute to
	 */
	public void setUserFileName(String value)
	{
		setAttribute(AttributeName.USERFILENAME, value, null);
	}

	/**
	 * (23) get String attribute UserFileName
	 *
	 * @return the value of the attribute
	 */
	public String getUserFileName()
	{
		return getAttribute(AttributeName.USERFILENAME, null, JDFCoreConstants.EMPTYSTRING);
	}

	/*
	 * *********************************************************************** Element getter / setter ***********************************************************************
	 */

	/**
	 * (28) const get element Container
	 *
	 * @param iSkip number of elements to skip
	 * @return JDFContainer the element default is getContainer(0)
	 */
	public JDFContainer getContainer(int iSkip)
	{
		return (JDFContainer) getElement(ElementName.CONTAINER, null, iSkip);
	}

	/**
	 * Get all Container from the current element
	 * 
	 * @return Collection<JDFContainer>, null if none are available
	 */
	public Collection<JDFContainer> getAllContainer()
	{
		final VElement vc = getChildElementVector(ElementName.CONTAINER, null);
		if (vc == null || vc.isEmpty())
		{
			return null;
		}

		final Vector<JDFContainer> v = new Vector<>();
		for (int i = 0; i < vc.size(); i++)
		{
			v.add((JDFContainer) vc.get(i));
		}

		return v;
	}

	/**
	 * (25) getCreateContainer
	 * 
	 * @return JDFContainer the element
	 */
	public JDFContainer getCreateContainer()
	{
		return (JDFContainer) getCreateElement_JDFElement(ElementName.CONTAINER, null, 0);
	}

	/**
	 * (29) append element Container
	 *
	 * @return JDFContainer the element @ if the element already exists
	 */
	public JDFContainer appendContainer()
	{
		return (JDFContainer) appendElementN(ElementName.CONTAINER, 1, null);
	}

	/**
	 * (28) const get element Disposition
	 *
	 * @param iSkip number of elements to skip
	 * @return JDFDisposition the element default is getDisposition(0)
	 */
	public JDFDisposition getDisposition(int iSkip)
	{
		return (JDFDisposition) getElement(ElementName.DISPOSITION, null, iSkip);
	}

	/**
	 * Get all Disposition from the current element
	 * 
	 * @return Collection<JDFDisposition>, null if none are available
	 */
	public Collection<JDFDisposition> getAllDisposition()
	{
		final VElement vc = getChildElementVector(ElementName.DISPOSITION, null);
		if (vc == null || vc.isEmpty())
		{
			return null;
		}

		final Vector<JDFDisposition> v = new Vector<>();
		for (int i = 0; i < vc.size(); i++)
		{
			v.add((JDFDisposition) vc.get(i));
		}

		return v;
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

	/**
	 * (24) const get element FileAlias
	 *
	 * @return JDFFileAlias the element
	 */
	public JDFFileAlias getFileAlias()
	{
		return (JDFFileAlias) getElement(ElementName.FILEALIAS, null, 0);
	}

	/**
	 * (25) getCreateFileAlias
	 * 
	 * @return JDFFileAlias the element
	 */
	public JDFFileAlias getCreateFileAlias()
	{
		return (JDFFileAlias) getCreateElement_JDFElement(ElementName.FILEALIAS, null, 0);
	}

	/**
	 * (26) getCreateFileAlias
	 * 
	 * @param iSkip number of elements to skip
	 * @return JDFFileAlias the element
	 */
	public JDFFileAlias getCreateFileAlias(int iSkip)
	{
		return (JDFFileAlias) getCreateElement_JDFElement(ElementName.FILEALIAS, null, iSkip);
	}

	/**
	 * (27) const get element FileAlias
	 *
	 * @param iSkip number of elements to skip
	 * @return JDFFileAlias the element default is getFileAlias(0)
	 */
	public JDFFileAlias getFileAlias(int iSkip)
	{
		return (JDFFileAlias) getElement(ElementName.FILEALIAS, null, iSkip);
	}

	/**
	 * Get all FileAlias from the current element
	 * 
	 * @return Collection<JDFFileAlias>, null if none are available
	 */
	public Collection<JDFFileAlias> getAllFileAlias()
	{
		return getChildArrayByClass(JDFFileAlias.class, false, 0);
	}

	/**
	 * (30) append element FileAlias
	 *
	 * @return JDFFileAlias the element
	 */
	public JDFFileAlias appendFileAlias()
	{
		return (JDFFileAlias) appendElement(ElementName.FILEALIAS, null);
	}

	/**
	 * (24) const get element NetworkHeader
	 *
	 * @return JDFNetworkHeader the element
	 */
	public JDFNetworkHeader getNetworkHeader()
	{
		return (JDFNetworkHeader) getElement(ElementName.NETWORKHEADER, null, 0);
	}

	/**
	 * (25) getCreateNetworkHeader
	 * 
	 * @return JDFNetworkHeader the element
	 */
	public JDFNetworkHeader getCreateNetworkHeader()
	{
		return (JDFNetworkHeader) getCreateElement_JDFElement(ElementName.NETWORKHEADER, null, 0);
	}

	/**
	 * (26) getCreateNetworkHeader
	 * 
	 * @param iSkip number of elements to skip
	 * @return JDFNetworkHeader the element
	 */
	public JDFNetworkHeader getCreateNetworkHeader(int iSkip)
	{
		return (JDFNetworkHeader) getCreateElement_JDFElement(ElementName.NETWORKHEADER, null, iSkip);
	}

	/**
	 * (27) const get element NetworkHeader
	 *
	 * @param iSkip number of elements to skip
	 * @return JDFNetworkHeader the element default is getNetworkHeader(0)
	 */
	public JDFNetworkHeader getNetworkHeader(int iSkip)
	{
		return (JDFNetworkHeader) getElement(ElementName.NETWORKHEADER, null, iSkip);
	}

	/**
	 * Get all NetworkHeader from the current element
	 * 
	 * @return Collection<JDFNetworkHeader>, null if none are available
	 */
	public Collection<JDFNetworkHeader> getAllNetworkHeader()
	{
		return getChildArrayByClass(JDFNetworkHeader.class, false, 0);
	}

	/**
	 * (30) append element NetworkHeader
	 *
	 * @return JDFNetworkHeader the element
	 */
	public JDFNetworkHeader appendNetworkHeader()
	{
		return (JDFNetworkHeader) appendElement(ElementName.NETWORKHEADER, null);
	}

}
