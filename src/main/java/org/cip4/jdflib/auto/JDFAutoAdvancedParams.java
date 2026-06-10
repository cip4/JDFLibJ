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
import org.cip4.jdflib.core.JDFElement;
import org.cip4.jdflib.util.JavaEnumUtil;

/**
 ***************************************************************************** class JDFAutoAdvancedParams : public JDFElement
 */

public abstract class JDFAutoAdvancedParams extends JDFElement
{

	private static final long serialVersionUID = 1L;

	private static AtrInfoTable[] atrInfoTable = new AtrInfoTable[17];
	static
	{
		atrInfoTable[0] = new AtrInfoTable(AttributeName.ALLOWPSXOBJECTS, 0x3333333311l, AttributeInfo.EnumAttributeType.boolean_, null, "true");
		atrInfoTable[1] = new AtrInfoTable(AttributeName.ALLOWTRANSPARENCY, 0x3333333311l, AttributeInfo.EnumAttributeType.boolean_, null, "false");
		atrInfoTable[2] = new AtrInfoTable(AttributeName.AUTOPOSITIONEPSINFO, 0x3333333333l, AttributeInfo.EnumAttributeType.boolean_, null, "true");
		atrInfoTable[3] = new AtrInfoTable(AttributeName.EMBEDJOBOPTIONS, 0x3333333311l, AttributeInfo.EnumAttributeType.boolean_, null, "false");
		atrInfoTable[4] = new AtrInfoTable(AttributeName.EMITDSCWARNINGS, 0x3333333333l, AttributeInfo.EnumAttributeType.boolean_, null, "false");
		atrInfoTable[5] = new AtrInfoTable(AttributeName.LOCKDISTILLERPARAMS, 0x3333333333l, AttributeInfo.EnumAttributeType.boolean_, null, "true");
		atrInfoTable[6] = new AtrInfoTable(AttributeName.PARSEDSCCOMMENTS, 0x3333333333l, AttributeInfo.EnumAttributeType.boolean_, null, "true");
		atrInfoTable[7] = new AtrInfoTable(AttributeName.PARSEDSCCOMMENTSFORDOCINFO, 0x3333333333l, AttributeInfo.EnumAttributeType.boolean_, null, "true");
		atrInfoTable[8] = new AtrInfoTable(AttributeName.PASSTHROUGHJPEGIMAGES, 0x3333333311l, AttributeInfo.EnumAttributeType.boolean_, null, "false");
		atrInfoTable[9] = new AtrInfoTable(AttributeName.PRESERVECOPYPAGE, 0x3333333333l, AttributeInfo.EnumAttributeType.boolean_, null, "true");
		atrInfoTable[10] = new AtrInfoTable(AttributeName.PRESERVEEPSINFO, 0x3333333333l, AttributeInfo.EnumAttributeType.boolean_, null, "true");
		atrInfoTable[11] = new AtrInfoTable(AttributeName.PRESERVEHALFTONEINFO, 0x3333333333l, AttributeInfo.EnumAttributeType.boolean_, null, "false");
		atrInfoTable[12] = new AtrInfoTable(AttributeName.PRESERVEOVERPRINTSETTINGS, 0x3333333331l, AttributeInfo.EnumAttributeType.boolean_, null, "true");
		atrInfoTable[13] = new AtrInfoTable(AttributeName.PRESERVEOPICOMMENTS, 0x3333333333l, AttributeInfo.EnumAttributeType.boolean_, null, "true");
		atrInfoTable[14] = new AtrInfoTable(AttributeName.TRANSFERFUNCTIONINFO, 0x3333333331l, AttributeInfo.EnumAttributeType.enumeration,
				JavaEnumUtil.getEnum(EnumTransferFunctionInfo.class, 0), "Preserve");
		atrInfoTable[15] = new AtrInfoTable(AttributeName.UCRANDBGINFO, 0x3333333331l, AttributeInfo.EnumAttributeType.enumeration,
				JavaEnumUtil.getEnum(EnumUCRandBGInfo.class, 0), "Preserve");
		atrInfoTable[16] = new AtrInfoTable(AttributeName.USEPROLOGUE, 0x3333333333l, AttributeInfo.EnumAttributeType.boolean_, null, "false");
	}

	@Override
	protected AttributeInfo getTheAttributeInfo()
	{
		return super.getTheAttributeInfo().updateReplace(atrInfoTable);
	}

	/**
	 * Constructor for JDFAutoAdvancedParams
	 *
	 * @param myOwnerDocument
	 * @param qualifiedName
	 */
	protected JDFAutoAdvancedParams(final CoreDocumentImpl myOwnerDocument, final String qualifiedName)
	{
		super(myOwnerDocument, qualifiedName);
	}

	/**
	 * Constructor for JDFAutoAdvancedParams
	 *
	 * @param myOwnerDocument
	 * @param myNamespaceURI
	 * @param qualifiedName
	 */
	protected JDFAutoAdvancedParams(final CoreDocumentImpl myOwnerDocument, final String myNamespaceURI, final String qualifiedName)
	{
		super(myOwnerDocument, myNamespaceURI, qualifiedName);
	}

	/**
	 * Constructor for JDFAutoAdvancedParams
	 *
	 * @param myOwnerDocument
	 * @param myNamespaceURI
	 * @param qualifiedName
	 * @param myLocalName
	 */
	protected JDFAutoAdvancedParams(final CoreDocumentImpl myOwnerDocument, final String myNamespaceURI, final String qualifiedName, final String myLocalName)
	{
		super(myOwnerDocument, myNamespaceURI, qualifiedName, myLocalName);
	}

	/**
	 * Enumeration strings for numTransferFunctionInfo
	 */

	public enum EnumTransferFunctionInfo
	{
		Preserve, Remove, Apply;

		public static EnumTransferFunctionInfo getEnum(final String val)
		{
			return JavaEnumUtil.getEnumIgnoreCase(EnumTransferFunctionInfo.class, val, EnumTransferFunctionInfo.Preserve);
		}
	}

	/**
	 * Enumeration strings for numUCRandBGInfo
	 */

	public enum EnumUCRandBGInfo
	{
		Preserve, Remove;

		public static EnumUCRandBGInfo getEnum(final String val)
		{
			return JavaEnumUtil.getEnumIgnoreCase(EnumUCRandBGInfo.class, val, EnumUCRandBGInfo.Preserve);
		}
	}/*
		 * ************************************************************************
		 * Attribute getter / setter
		 * ************************************************************************
		 */

	/*
	 * ---------------------------------------------------------------------
	 * Methods for Attribute AllowPSXObjects
	 * ---------------------------------------------------------------------
	 */
	/**
	 * (36) set attribute AllowPSXObjects
	 *
	 * @param value the value to set the attribute to
	 */
	public void setAllowPSXObjects(final boolean value)
	{
		setAttribute(AttributeName.ALLOWPSXOBJECTS, value, null);
	}

	/**
	 * (18) get boolean attribute AllowPSXObjects
	 *
	 * @return boolean the value of the attribute
	 */
	public boolean getAllowPSXObjects()
	{
		return getBoolAttribute(AttributeName.ALLOWPSXOBJECTS, null, true);
	}

	/*
	 * ---------------------------------------------------------------------
	 * Methods for Attribute AllowTransparency
	 * ---------------------------------------------------------------------
	 */
	/**
	 * (36) set attribute AllowTransparency
	 *
	 * @param value the value to set the attribute to
	 */
	public void setAllowTransparency(final boolean value)
	{
		setAttribute(AttributeName.ALLOWTRANSPARENCY, value, null);
	}

	/**
	 * (18) get boolean attribute AllowTransparency
	 *
	 * @return boolean the value of the attribute
	 */
	public boolean getAllowTransparency()
	{
		return getBoolAttribute(AttributeName.ALLOWTRANSPARENCY, null, false);
	}

	/*
	 * ---------------------------------------------------------------------
	 * Methods for Attribute AutoPositionEPSInfo
	 * ---------------------------------------------------------------------
	 */
	/**
	 * (36) set attribute AutoPositionEPSInfo
	 *
	 * @param value the value to set the attribute to
	 */
	public void setAutoPositionEPSInfo(final boolean value)
	{
		setAttribute(AttributeName.AUTOPOSITIONEPSINFO, value, null);
	}

	/**
	 * (18) get boolean attribute AutoPositionEPSInfo
	 *
	 * @return boolean the value of the attribute
	 */
	public boolean getAutoPositionEPSInfo()
	{
		return getBoolAttribute(AttributeName.AUTOPOSITIONEPSINFO, null, true);
	}

	/*
	 * ---------------------------------------------------------------------
	 * Methods for Attribute EmbedJobOptions
	 * ---------------------------------------------------------------------
	 */
	/**
	 * (36) set attribute EmbedJobOptions
	 *
	 * @param value the value to set the attribute to
	 */
	public void setEmbedJobOptions(final boolean value)
	{
		setAttribute(AttributeName.EMBEDJOBOPTIONS, value, null);
	}

	/**
	 * (18) get boolean attribute EmbedJobOptions
	 *
	 * @return boolean the value of the attribute
	 */
	public boolean getEmbedJobOptions()
	{
		return getBoolAttribute(AttributeName.EMBEDJOBOPTIONS, null, false);
	}

	/*
	 * ---------------------------------------------------------------------
	 * Methods for Attribute EmitDSCWarnings
	 * ---------------------------------------------------------------------
	 */
	/**
	 * (36) set attribute EmitDSCWarnings
	 *
	 * @param value the value to set the attribute to
	 */
	public void setEmitDSCWarnings(final boolean value)
	{
		setAttribute(AttributeName.EMITDSCWARNINGS, value, null);
	}

	/**
	 * (18) get boolean attribute EmitDSCWarnings
	 *
	 * @return boolean the value of the attribute
	 */
	public boolean getEmitDSCWarnings()
	{
		return getBoolAttribute(AttributeName.EMITDSCWARNINGS, null, false);
	}

	/*
	 * ---------------------------------------------------------------------
	 * Methods for Attribute LockDistillerParams
	 * ---------------------------------------------------------------------
	 */
	/**
	 * (36) set attribute LockDistillerParams
	 *
	 * @param value the value to set the attribute to
	 */
	public void setLockDistillerParams(final boolean value)
	{
		setAttribute(AttributeName.LOCKDISTILLERPARAMS, value, null);
	}

	/**
	 * (18) get boolean attribute LockDistillerParams
	 *
	 * @return boolean the value of the attribute
	 */
	public boolean getLockDistillerParams()
	{
		return getBoolAttribute(AttributeName.LOCKDISTILLERPARAMS, null, true);
	}

	/*
	 * ---------------------------------------------------------------------
	 * Methods for Attribute ParseDSCComments
	 * ---------------------------------------------------------------------
	 */
	/**
	 * (36) set attribute ParseDSCComments
	 *
	 * @param value the value to set the attribute to
	 */
	public void setParseDSCComments(final boolean value)
	{
		setAttribute(AttributeName.PARSEDSCCOMMENTS, value, null);
	}

	/**
	 * (18) get boolean attribute ParseDSCComments
	 *
	 * @return boolean the value of the attribute
	 */
	public boolean getParseDSCComments()
	{
		return getBoolAttribute(AttributeName.PARSEDSCCOMMENTS, null, true);
	}

	/*
	 * ---------------------------------------------------------------------
	 * Methods for Attribute ParseDSCCommentsForDocInfo
	 * ---------------------------------------------------------------------
	 */
	/**
	 * (36) set attribute ParseDSCCommentsForDocInfo
	 *
	 * @param value the value to set the attribute to
	 */
	public void setParseDSCCommentsForDocInfo(final boolean value)
	{
		setAttribute(AttributeName.PARSEDSCCOMMENTSFORDOCINFO, value, null);
	}

	/**
	 * (18) get boolean attribute ParseDSCCommentsForDocInfo
	 *
	 * @return boolean the value of the attribute
	 */
	public boolean getParseDSCCommentsForDocInfo()
	{
		return getBoolAttribute(AttributeName.PARSEDSCCOMMENTSFORDOCINFO, null, true);
	}

	/*
	 * ---------------------------------------------------------------------
	 * Methods for Attribute PassThroughJPEGImages
	 * ---------------------------------------------------------------------
	 */
	/**
	 * (36) set attribute PassThroughJPEGImages
	 *
	 * @param value the value to set the attribute to
	 */
	public void setPassThroughJPEGImages(final boolean value)
	{
		setAttribute(AttributeName.PASSTHROUGHJPEGIMAGES, value, null);
	}

	/**
	 * (18) get boolean attribute PassThroughJPEGImages
	 *
	 * @return boolean the value of the attribute
	 */
	public boolean getPassThroughJPEGImages()
	{
		return getBoolAttribute(AttributeName.PASSTHROUGHJPEGIMAGES, null, false);
	}

	/*
	 * ---------------------------------------------------------------------
	 * Methods for Attribute PreserveCopyPage
	 * ---------------------------------------------------------------------
	 */
	/**
	 * (36) set attribute PreserveCopyPage
	 *
	 * @param value the value to set the attribute to
	 */
	public void setPreserveCopyPage(final boolean value)
	{
		setAttribute(AttributeName.PRESERVECOPYPAGE, value, null);
	}

	/**
	 * (18) get boolean attribute PreserveCopyPage
	 *
	 * @return boolean the value of the attribute
	 */
	public boolean getPreserveCopyPage()
	{
		return getBoolAttribute(AttributeName.PRESERVECOPYPAGE, null, true);
	}

	/*
	 * ---------------------------------------------------------------------
	 * Methods for Attribute PreserveEPSInfo
	 * ---------------------------------------------------------------------
	 */
	/**
	 * (36) set attribute PreserveEPSInfo
	 *
	 * @param value the value to set the attribute to
	 */
	public void setPreserveEPSInfo(final boolean value)
	{
		setAttribute(AttributeName.PRESERVEEPSINFO, value, null);
	}

	/**
	 * (18) get boolean attribute PreserveEPSInfo
	 *
	 * @return boolean the value of the attribute
	 */
	public boolean getPreserveEPSInfo()
	{
		return getBoolAttribute(AttributeName.PRESERVEEPSINFO, null, true);
	}

	/*
	 * ---------------------------------------------------------------------
	 * Methods for Attribute PreserveHalftoneInfo
	 * ---------------------------------------------------------------------
	 */
	/**
	 * (36) set attribute PreserveHalftoneInfo
	 *
	 * @param value the value to set the attribute to
	 */
	public void setPreserveHalftoneInfo(final boolean value)
	{
		setAttribute(AttributeName.PRESERVEHALFTONEINFO, value, null);
	}

	/**
	 * (18) get boolean attribute PreserveHalftoneInfo
	 *
	 * @return boolean the value of the attribute
	 */
	public boolean getPreserveHalftoneInfo()
	{
		return getBoolAttribute(AttributeName.PRESERVEHALFTONEINFO, null, false);
	}

	/*
	 * ---------------------------------------------------------------------
	 * Methods for Attribute PreserveOverprintSettings
	 * ---------------------------------------------------------------------
	 */
	/**
	 * (36) set attribute PreserveOverprintSettings
	 *
	 * @param value the value to set the attribute to
	 */
	public void setPreserveOverprintSettings(final boolean value)
	{
		setAttribute(AttributeName.PRESERVEOVERPRINTSETTINGS, value, null);
	}

	/**
	 * (18) get boolean attribute PreserveOverprintSettings
	 *
	 * @return boolean the value of the attribute
	 */
	public boolean getPreserveOverprintSettings()
	{
		return getBoolAttribute(AttributeName.PRESERVEOVERPRINTSETTINGS, null, true);
	}

	/*
	 * ---------------------------------------------------------------------
	 * Methods for Attribute PreserveOPIComments
	 * ---------------------------------------------------------------------
	 */
	/**
	 * (36) set attribute PreserveOPIComments
	 *
	 * @param value the value to set the attribute to
	 */
	public void setPreserveOPIComments(final boolean value)
	{
		setAttribute(AttributeName.PRESERVEOPICOMMENTS, value, null);
	}

	/**
	 * (18) get boolean attribute PreserveOPIComments
	 *
	 * @return boolean the value of the attribute
	 */
	public boolean getPreserveOPIComments()
	{
		return getBoolAttribute(AttributeName.PRESERVEOPICOMMENTS, null, true);
	}

	/*
	 * ---------------------------------------------------------------------
	 * Methods for Attribute TransferFunctionInfo
	 * ---------------------------------------------------------------------
	 */
	/**
	 * (5) set attribute TransferFunctionInfo
	 *
	 * @param enumVar the enumVar to set the attribute to
	 */
	public void setTransferFunctionInfo(final EnumTransferFunctionInfo enumVar)
	{
		setAttribute(AttributeName.TRANSFERFUNCTIONINFO, JavaEnumUtil.getName(enumVar), null);
	}

	/**
	 * (9) get attribute TransferFunctionInfo
	 *
	 * @return the value of the attribute
	 */
	public EnumTransferFunctionInfo getTransferFunctionInfo()
	{
		return EnumTransferFunctionInfo.getEnum(getAttribute(AttributeName.TRANSFERFUNCTIONINFO, null, "Preserve"));
	}

	/*
	 * ---------------------------------------------------------------------
	 * Methods for Attribute UCRandBGInfo
	 * ---------------------------------------------------------------------
	 */
	/**
	 * (5) set attribute UCRandBGInfo
	 *
	 * @param enumVar the enumVar to set the attribute to
	 */
	public void setUCRandBGInfo(final EnumUCRandBGInfo enumVar)
	{
		setAttribute(AttributeName.UCRANDBGINFO, JavaEnumUtil.getName(enumVar), null);
	}

	/**
	 * (9) get attribute UCRandBGInfo
	 *
	 * @return the value of the attribute
	 */
	public EnumUCRandBGInfo getUCRandBGInfo()
	{
		return EnumUCRandBGInfo.getEnum(getAttribute(AttributeName.UCRANDBGINFO, null, "Preserve"));
	}

	/*
	 * ---------------------------------------------------------------------
	 * Methods for Attribute UsePrologue
	 * ---------------------------------------------------------------------
	 */
	/**
	 * (36) set attribute UsePrologue
	 *
	 * @param value the value to set the attribute to
	 */
	public void setUsePrologue(final boolean value)
	{
		setAttribute(AttributeName.USEPROLOGUE, value, null);
	}

	/**
	 * (18) get boolean attribute UsePrologue
	 *
	 * @return boolean the value of the attribute
	 */
	public boolean getUsePrologue()
	{
		return getBoolAttribute(AttributeName.USEPROLOGUE, null, false);
	}

}
