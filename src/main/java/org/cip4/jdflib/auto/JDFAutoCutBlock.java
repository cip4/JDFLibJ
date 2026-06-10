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
import org.cip4.jdflib.core.VString;
import org.cip4.jdflib.datatypes.JDFMatrix;
import org.cip4.jdflib.datatypes.JDFXYPair;
import org.cip4.jdflib.resource.JDFResource;
import org.cip4.jdflib.util.JavaEnumUtil;

/**
 ***************************************************************************** class JDFAutoCutBlock : public JDFResource
 */

public abstract class JDFAutoCutBlock extends JDFResource
{

	private static final long serialVersionUID = 1L;

	private static AtrInfoTable[] atrInfoTable = new AtrInfoTable[9];
	static
	{
		atrInfoTable[0] = new AtrInfoTable(AttributeName.BLOCKSIZE, 0x2222222222l, AttributeInfo.EnumAttributeType.XYPair, null, null);
		atrInfoTable[1] = new AtrInfoTable(AttributeName.BLOCKSUBDIVISION, 0x3333333333l, AttributeInfo.EnumAttributeType.XYPair, null, "1 1");
		atrInfoTable[2] = new AtrInfoTable(AttributeName.BLOCKTRF, 0x3333333333l, AttributeInfo.EnumAttributeType.matrix, null, "1 0 0 1 0 0");
		atrInfoTable[3] = new AtrInfoTable(AttributeName.BLOCKTYPE, 0x2222222222l, AttributeInfo.EnumAttributeType.enumeration,
				JavaEnumUtil.getEnum(EnumBlockType.class, 0), null);
		atrInfoTable[4] = new AtrInfoTable(AttributeName.ASSEMBLYIDS, 0x3333333111l, AttributeInfo.EnumAttributeType.NMTOKENS, null, null);
		atrInfoTable[5] = new AtrInfoTable(AttributeName.BLOCKELEMENTSIZE, 0x3333333333l, AttributeInfo.EnumAttributeType.XYPair, null, null);
		atrInfoTable[6] = new AtrInfoTable(AttributeName.BLOCKELEMENTTYPE, 0x3333333333l, AttributeInfo.EnumAttributeType.enumeration,
				JavaEnumUtil.getEnum(EnumBlockElementType.class, 0), null);
		atrInfoTable[7] = new AtrInfoTable(AttributeName.CUTWIDTH, 0x3333331111l, AttributeInfo.EnumAttributeType.double_, null, null);
		atrInfoTable[8] = new AtrInfoTable(AttributeName.OPERATIONS, 0x3331111111l, AttributeInfo.EnumAttributeType.NMTOKENS, null, null);
	}

	@Override
	protected AttributeInfo getTheAttributeInfo()
	{
		return super.getTheAttributeInfo().updateReplace(atrInfoTable);
	}

	/**
	 * Constructor for JDFAutoCutBlock
	 *
	 * @param myOwnerDocument
	 * @param qualifiedName
	 */
	protected JDFAutoCutBlock(final CoreDocumentImpl myOwnerDocument, final String qualifiedName)
	{
		super(myOwnerDocument, qualifiedName);
	}

	/**
	 * Constructor for JDFAutoCutBlock
	 *
	 * @param myOwnerDocument
	 * @param myNamespaceURI
	 * @param qualifiedName
	 */
	protected JDFAutoCutBlock(final CoreDocumentImpl myOwnerDocument, final String myNamespaceURI, final String qualifiedName)
	{
		super(myOwnerDocument, myNamespaceURI, qualifiedName);
	}

	/**
	 * Constructor for JDFAutoCutBlock
	 *
	 * @param myOwnerDocument
	 * @param myNamespaceURI
	 * @param qualifiedName
	 * @param myLocalName
	 */
	protected JDFAutoCutBlock(final CoreDocumentImpl myOwnerDocument, final String myNamespaceURI, final String qualifiedName, final String myLocalName)
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
	 * Enumeration strings for numBlockType
	 */

	public enum EnumBlockType
	{
		CutBlock, SaveBlock, TempBlock, MarkBlock;

		public static EnumBlockType getEnum(final String val)
		{
			return JavaEnumUtil.getEnumIgnoreCase(EnumBlockType.class, val, null);
		}
	}

	/**
	 * Enumeration strings for numBlockElementType
	 */

	public enum EnumBlockElementType
	{
		CutElement, PunchElement;

		public static EnumBlockElementType getEnum(final String val)
		{
			return JavaEnumUtil.getEnumIgnoreCase(EnumBlockElementType.class, val, null);
		}
	}/*
		 * ************************************************************************
		 * Attribute getter / setter
		 * ************************************************************************
		 */

	/*
	 * ---------------------------------------------------------------------
	 * Methods for Attribute BlockSize
	 * ---------------------------------------------------------------------
	 */
	/**
	 * (36) set attribute BlockSize
	 *
	 * @param value the value to set the attribute to
	 */
	public void setBlockSize(final JDFXYPair value)
	{
		setAttribute(AttributeName.BLOCKSIZE, value, null);
	}

	/**
	 * (20) get JDFXYPair attribute BlockSize
	 *
	 * @return JDFXYPair the value of the attribute, null if a the
	 *         attribute value is not a valid to create a JDFXYPair
	 */
	public JDFXYPair getBlockSize()
	{
		final String strAttrName = getAttribute(AttributeName.BLOCKSIZE, null, null);
		final JDFXYPair nPlaceHolder = JDFXYPair.createXYPair(strAttrName);
		return nPlaceHolder;
	}

	/*
	 * ---------------------------------------------------------------------
	 * Methods for Attribute BlockSubdivision
	 * ---------------------------------------------------------------------
	 */
	/**
	 * (36) set attribute BlockSubdivision
	 *
	 * @param value the value to set the attribute to
	 */
	public void setBlockSubdivision(final JDFXYPair value)
	{
		setAttribute(AttributeName.BLOCKSUBDIVISION, value, null);
	}

	/**
	 * (20) get JDFXYPair attribute BlockSubdivision
	 *
	 * @return JDFXYPair the value of the attribute, null if a the
	 *         attribute value is not a valid to create a JDFXYPair
	 */
	public JDFXYPair getBlockSubdivision()
	{
		final String strAttrName = getAttribute(AttributeName.BLOCKSUBDIVISION, null, null);
		final JDFXYPair nPlaceHolder = JDFXYPair.createXYPair(strAttrName);
		return nPlaceHolder;
	}

	/*
	 * ---------------------------------------------------------------------
	 * Methods for Attribute BlockTrf
	 * ---------------------------------------------------------------------
	 */
	/**
	 * (36) set attribute BlockTrf
	 *
	 * @param value the value to set the attribute to
	 */
	public void setBlockTrf(final JDFMatrix value)
	{
		setAttribute(AttributeName.BLOCKTRF, value, null);
	}

	/**
	 * (20) get JDFMatrix attribute BlockTrf
	 *
	 * @return JDFMatrix the value of the attribute, null if a the
	 *         attribute value is not a valid to create a JDFMatrix
	 */
	public JDFMatrix getBlockTrf()
	{
		final String strAttrName = getAttribute(AttributeName.BLOCKTRF, null, null);
		final JDFMatrix nPlaceHolder = JDFMatrix.createMatrix(strAttrName);
		return nPlaceHolder;
	}

	/*
	 * ---------------------------------------------------------------------
	 * Methods for Attribute BlockType
	 * ---------------------------------------------------------------------
	 */
	/**
	 * (5) set attribute BlockType
	 *
	 * @param enumVar the enumVar to set the attribute to
	 */
	public void setBlockType(final EnumBlockType enumVar)
	{
		setAttribute(AttributeName.BLOCKTYPE, JavaEnumUtil.getName(enumVar), null);
	}

	/**
	 * (9) get attribute BlockType
	 *
	 * @return the value of the attribute
	 */
	public EnumBlockType getBlockType()
	{
		return EnumBlockType.getEnum(getAttribute(AttributeName.BLOCKTYPE, null, null));
	}

	/*
	 * ---------------------------------------------------------------------
	 * Methods for Attribute AssemblyIDs
	 * ---------------------------------------------------------------------
	 */
	/**
	 * (36) set attribute AssemblyIDs
	 *
	 * @param value the value to set the attribute to
	 */
	public void setAssemblyIDs(final VString value)
	{
		setAttribute(AttributeName.ASSEMBLYIDS, value, null);
	}

	/**
	 * (21) get VString attribute AssemblyIDs
	 *
	 * @return VString the value of the attribute
	 */
	public VString getAssemblyIDs()
	{
		final VString vStrAttrib = new VString();
		final String s = getAttribute(AttributeName.ASSEMBLYIDS, null, JDFCoreConstants.EMPTYSTRING);
		vStrAttrib.setAllStrings(s, " ");
		return vStrAttrib;
	}

	/*
	 * ---------------------------------------------------------------------
	 * Methods for Attribute BlockElementSize
	 * ---------------------------------------------------------------------
	 */
	/**
	 * (36) set attribute BlockElementSize
	 *
	 * @param value the value to set the attribute to
	 */
	public void setBlockElementSize(final JDFXYPair value)
	{
		setAttribute(AttributeName.BLOCKELEMENTSIZE, value, null);
	}

	/**
	 * (20) get JDFXYPair attribute BlockElementSize
	 *
	 * @return JDFXYPair the value of the attribute, null if a the
	 *         attribute value is not a valid to create a JDFXYPair
	 */
	public JDFXYPair getBlockElementSize()
	{
		final String strAttrName = getAttribute(AttributeName.BLOCKELEMENTSIZE, null, null);
		final JDFXYPair nPlaceHolder = JDFXYPair.createXYPair(strAttrName);
		return nPlaceHolder;
	}

	/*
	 * ---------------------------------------------------------------------
	 * Methods for Attribute BlockElementType
	 * ---------------------------------------------------------------------
	 */
	/**
	 * (5) set attribute BlockElementType
	 *
	 * @param enumVar the enumVar to set the attribute to
	 */
	public void setBlockElementType(final EnumBlockElementType enumVar)
	{
		setAttribute(AttributeName.BLOCKELEMENTTYPE, JavaEnumUtil.getName(enumVar), null);
	}

	/**
	 * (9) get attribute BlockElementType
	 *
	 * @return the value of the attribute
	 */
	public EnumBlockElementType getBlockElementType()
	{
		return EnumBlockElementType.getEnum(getAttribute(AttributeName.BLOCKELEMENTTYPE, null, null));
	}

	/*
	 * ---------------------------------------------------------------------
	 * Methods for Attribute CutWidth
	 * ---------------------------------------------------------------------
	 */
	/**
	 * (36) set attribute CutWidth
	 *
	 * @param value the value to set the attribute to
	 */
	public void setCutWidth(final double value)
	{
		setAttribute(AttributeName.CUTWIDTH, value, null);
	}

	/**
	 * (17) get double attribute CutWidth
	 *
	 * @return double the value of the attribute
	 */
	public double getCutWidth()
	{
		return getRealAttribute(AttributeName.CUTWIDTH, null, 0.0);
	}

	/*
	 * ---------------------------------------------------------------------
	 * Methods for Attribute Operations
	 * ---------------------------------------------------------------------
	 */
	/**
	 * (36) set attribute Operations
	 *
	 * @param value the value to set the attribute to
	 */
	public void setOperations(final VString value)
	{
		setAttribute(AttributeName.OPERATIONS, value, null);
	}

	/**
	 * (21) get VString attribute Operations
	 *
	 * @return VString the value of the attribute
	 */
	public VString getOperations()
	{
		final VString vStrAttrib = new VString();
		final String s = getAttribute(AttributeName.OPERATIONS, null, JDFCoreConstants.EMPTYSTRING);
		vStrAttrib.setAllStrings(s, " ");
		return vStrAttrib;
	}

}
