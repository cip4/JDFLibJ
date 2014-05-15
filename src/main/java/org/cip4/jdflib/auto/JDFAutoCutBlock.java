/*
 * The CIP4 Software License, Version 1.0
 *
 *
 * Copyright (c) 2001-2014 The International Cooperation for the Integration of
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

import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.enums.ValuedEnum;
import org.apache.xerces.dom.CoreDocumentImpl;
import org.cip4.jdflib.core.AtrInfoTable;
import org.cip4.jdflib.core.AttributeInfo;
import org.cip4.jdflib.core.AttributeName;
import org.cip4.jdflib.core.JDFCoreConstants;
import org.cip4.jdflib.core.VString;
import org.cip4.jdflib.datatypes.JDFMatrix;
import org.cip4.jdflib.datatypes.JDFXYPair;
import org.cip4.jdflib.resource.JDFResource;

/**
*****************************************************************************
class JDFAutoCutBlock : public JDFResource

*****************************************************************************
*/

public abstract class JDFAutoCutBlock extends JDFResource
{

	private static final long serialVersionUID = 1L;

	private static AtrInfoTable[] atrInfoTable = new AtrInfoTable[8];
	static
	{
		atrInfoTable[0] = new AtrInfoTable(AttributeName.BLOCKSIZE, 0x22222222, AttributeInfo.EnumAttributeType.XYPair, null, null);
		atrInfoTable[1] = new AtrInfoTable(AttributeName.BLOCKSUBDIVISION, 0x33333333, AttributeInfo.EnumAttributeType.XYPair, null, "1 1");
		atrInfoTable[2] = new AtrInfoTable(AttributeName.BLOCKTRF, 0x33333333, AttributeInfo.EnumAttributeType.matrix, null, "1 0 0 1 0 0");
		atrInfoTable[3] = new AtrInfoTable(AttributeName.BLOCKTYPE, 0x22222222, AttributeInfo.EnumAttributeType.enumeration, EnumBlockType.getEnum(0), null);
		atrInfoTable[4] = new AtrInfoTable(AttributeName.ASSEMBLYIDS, 0x33333111, AttributeInfo.EnumAttributeType.NMTOKENS, null, null);
		atrInfoTable[5] = new AtrInfoTable(AttributeName.CUTWIDTH, 0x33331111, AttributeInfo.EnumAttributeType.double_, null, null);
		atrInfoTable[6] = new AtrInfoTable(AttributeName.BLOCKELEMENTSIZE, 0x33333333, AttributeInfo.EnumAttributeType.XYPair, null, null);
		atrInfoTable[7] = new AtrInfoTable(AttributeName.BLOCKELEMENTTYPE, 0x33333333, AttributeInfo.EnumAttributeType.enumeration, EnumBlockElementType.getEnum(0), null);
	}

	@Override
	protected AttributeInfo getTheAttributeInfo()
	{
		return super.getTheAttributeInfo().updateReplace(atrInfoTable);
	}

	/**
	 * Constructor for JDFAutoCutBlock
	 * @param myOwnerDocument
	 * @param qualifiedName
	 */
	protected JDFAutoCutBlock(CoreDocumentImpl myOwnerDocument, String qualifiedName)
	{
		super(myOwnerDocument, qualifiedName);
	}

	/**
	 * Constructor for JDFAutoCutBlock
	 * @param myOwnerDocument
	 * @param myNamespaceURI
	 * @param qualifiedName
	 */
	protected JDFAutoCutBlock(CoreDocumentImpl myOwnerDocument, String myNamespaceURI, String qualifiedName)
	{
		super(myOwnerDocument, myNamespaceURI, qualifiedName);
	}

	/**
	 * Constructor for JDFAutoCutBlock
	 * @param myOwnerDocument
	 * @param myNamespaceURI
	 * @param qualifiedName
	 * @param myLocalName
	 */
	protected JDFAutoCutBlock(CoreDocumentImpl myOwnerDocument, String myNamespaceURI, String qualifiedName, String myLocalName)
	{
		super(myOwnerDocument, myNamespaceURI, qualifiedName, myLocalName);
	}

	/**
	 * @return  the string representation
	 */
	@Override
	public String toString()
	{
		return " JDFAutoCutBlock[  --> " + super.toString() + " ]";
	}

	/**
	 * @return  true if ok
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
	* Enumeration strings for BlockType
	*/

	@SuppressWarnings("rawtypes")
	public static class EnumBlockType extends ValuedEnum
	{
		private static final long serialVersionUID = 1L;
		private static int m_startValue = 0;

		private EnumBlockType(String name)
		{
			super(name, m_startValue++);
		}

		/**
		 * @param enumName the string to convert
		 * @return the enum
		 */
		public static EnumBlockType getEnum(String enumName)
		{
			return (EnumBlockType) getEnum(EnumBlockType.class, enumName);
		}

		/**
		 * @param enumValue the integer to convert
		 * @return the enum
		 */
		public static EnumBlockType getEnum(int enumValue)
		{
			return (EnumBlockType) getEnum(EnumBlockType.class, enumValue);
		}

		/**
		 * @return the map of enums
		 */
		public static Map getEnumMap()
		{
			return getEnumMap(EnumBlockType.class);
		}

		/**
		 * @return the list of enums
		 */
		public static List getEnumList()
		{
			return getEnumList(EnumBlockType.class);
		}

		/**
		 * @return the iterator
		 */
		public static Iterator iterator()
		{
			return iterator(EnumBlockType.class);
		}

		/**  */
		public static final EnumBlockType CutBlock = new EnumBlockType("CutBlock");
		/**  */
		public static final EnumBlockType SaveBlock = new EnumBlockType("SaveBlock");
		/**  */
		public static final EnumBlockType TempBlock = new EnumBlockType("TempBlock");
		/**  */
		public static final EnumBlockType MarkBlock = new EnumBlockType("MarkBlock");
	}

	/**
	* Enumeration strings for BlockElementType
	*/

	@SuppressWarnings("rawtypes")
	public static class EnumBlockElementType extends ValuedEnum
	{
		private static final long serialVersionUID = 1L;
		private static int m_startValue = 0;

		private EnumBlockElementType(String name)
		{
			super(name, m_startValue++);
		}

		/**
		 * @param enumName the string to convert
		 * @return the enum
		 */
		public static EnumBlockElementType getEnum(String enumName)
		{
			return (EnumBlockElementType) getEnum(EnumBlockElementType.class, enumName);
		}

		/**
		 * @param enumValue the integer to convert
		 * @return the enum
		 */
		public static EnumBlockElementType getEnum(int enumValue)
		{
			return (EnumBlockElementType) getEnum(EnumBlockElementType.class, enumValue);
		}

		/**
		 * @return the map of enums
		 */
		public static Map getEnumMap()
		{
			return getEnumMap(EnumBlockElementType.class);
		}

		/**
		 * @return the list of enums
		 */
		public static List getEnumList()
		{
			return getEnumList(EnumBlockElementType.class);
		}

		/**
		 * @return the iterator
		 */
		public static Iterator iterator()
		{
			return iterator(EnumBlockElementType.class);
		}

		/**  */
		public static final EnumBlockElementType CutElement = new EnumBlockElementType("CutElement");
		/**  */
		public static final EnumBlockElementType PunchElement = new EnumBlockElementType("PunchElement");
	}

	/* ************************************************************************
	 * Attribute getter / setter
	 * ************************************************************************
	 */

	/* ---------------------------------------------------------------------
	Methods for Attribute BlockSize
	--------------------------------------------------------------------- */
	/**
	  * (36) set attribute BlockSize
	  * @param value the value to set the attribute to
	  */
	public void setBlockSize(JDFXYPair value)
	{
		setAttribute(AttributeName.BLOCKSIZE, value, null);
	}

	/**
	  * (20) get JDFXYPair attribute BlockSize
	  * @return JDFXYPair the value of the attribute, null if a the
	  *         attribute value is not a valid to create a JDFXYPair
	  */
	public JDFXYPair getBlockSize()
	{
		final String strAttrName = getAttribute(AttributeName.BLOCKSIZE, null, null);
		final JDFXYPair nPlaceHolder = JDFXYPair.createXYPair(strAttrName);
		return nPlaceHolder;
	}

	/* ---------------------------------------------------------------------
	Methods for Attribute BlockSubdivision
	--------------------------------------------------------------------- */
	/**
	  * (36) set attribute BlockSubdivision
	  * @param value the value to set the attribute to
	  */
	public void setBlockSubdivision(JDFXYPair value)
	{
		setAttribute(AttributeName.BLOCKSUBDIVISION, value, null);
	}

	/**
	  * (20) get JDFXYPair attribute BlockSubdivision
	  * @return JDFXYPair the value of the attribute, null if a the
	  *         attribute value is not a valid to create a JDFXYPair
	  */
	public JDFXYPair getBlockSubdivision()
	{
		final String strAttrName = getAttribute(AttributeName.BLOCKSUBDIVISION, null, null);
		final JDFXYPair nPlaceHolder = JDFXYPair.createXYPair(strAttrName);
		return nPlaceHolder;
	}

	/* ---------------------------------------------------------------------
	Methods for Attribute BlockTrf
	--------------------------------------------------------------------- */
	/**
	  * (36) set attribute BlockTrf
	  * @param value the value to set the attribute to
	  */
	public void setBlockTrf(JDFMatrix value)
	{
		setAttribute(AttributeName.BLOCKTRF, value, null);
	}

	/**
	  * (20) get JDFMatrix attribute BlockTrf
	  * @return JDFMatrix the value of the attribute, null if a the
	  *         attribute value is not a valid to create a JDFMatrix
	  */
	public JDFMatrix getBlockTrf()
	{
		final String strAttrName = getAttribute(AttributeName.BLOCKTRF, null, null);
		final JDFMatrix nPlaceHolder = JDFMatrix.createMatrix(strAttrName);
		return nPlaceHolder;
	}

	/* ---------------------------------------------------------------------
	Methods for Attribute BlockType
	--------------------------------------------------------------------- */
	/**
	  * (5) set attribute BlockType
	  * @param enumVar the enumVar to set the attribute to
	  */
	public void setBlockType(EnumBlockType enumVar)
	{
		setAttribute(AttributeName.BLOCKTYPE, enumVar == null ? null : enumVar.getName(), null);
	}

	/**
	  * (9) get attribute BlockType
	  * @return the value of the attribute
	  */
	public EnumBlockType getBlockType()
	{
		return EnumBlockType.getEnum(getAttribute(AttributeName.BLOCKTYPE, null, null));
	}

	/* ---------------------------------------------------------------------
	Methods for Attribute AssemblyIDs
	--------------------------------------------------------------------- */
	/**
	  * (36) set attribute AssemblyIDs
	  * @param value the value to set the attribute to
	  */
	public void setAssemblyIDs(VString value)
	{
		setAttribute(AttributeName.ASSEMBLYIDS, value, null);
	}

	/**
	  * (21) get VString attribute AssemblyIDs
	  * @return VString the value of the attribute
	  */
	public VString getAssemblyIDs()
	{
		final VString vStrAttrib = new VString();
		final String s = getAttribute(AttributeName.ASSEMBLYIDS, null, JDFCoreConstants.EMPTYSTRING);
		vStrAttrib.setAllStrings(s, " ");
		return vStrAttrib;
	}

	/* ---------------------------------------------------------------------
	Methods for Attribute CutWidth
	--------------------------------------------------------------------- */
	/**
	  * (36) set attribute CutWidth
	  * @param value the value to set the attribute to
	  */
	public void setCutWidth(double value)
	{
		setAttribute(AttributeName.CUTWIDTH, value, null);
	}

	/**
	  * (17) get double attribute CutWidth
	  * @return double the value of the attribute
	  */
	public double getCutWidth()
	{
		return getRealAttribute(AttributeName.CUTWIDTH, null, 0.0);
	}

	/* ---------------------------------------------------------------------
	Methods for Attribute BlockElementSize
	--------------------------------------------------------------------- */
	/**
	  * (36) set attribute BlockElementSize
	  * @param value the value to set the attribute to
	  */
	public void setBlockElementSize(JDFXYPair value)
	{
		setAttribute(AttributeName.BLOCKELEMENTSIZE, value, null);
	}

	/**
	  * (20) get JDFXYPair attribute BlockElementSize
	  * @return JDFXYPair the value of the attribute, null if a the
	  *         attribute value is not a valid to create a JDFXYPair
	  */
	public JDFXYPair getBlockElementSize()
	{
		final String strAttrName = getAttribute(AttributeName.BLOCKELEMENTSIZE, null, null);
		final JDFXYPair nPlaceHolder = JDFXYPair.createXYPair(strAttrName);
		return nPlaceHolder;
	}

	/* ---------------------------------------------------------------------
	Methods for Attribute BlockElementType
	--------------------------------------------------------------------- */
	/**
	  * (5) set attribute BlockElementType
	  * @param enumVar the enumVar to set the attribute to
	  */
	public void setBlockElementType(EnumBlockElementType enumVar)
	{
		setAttribute(AttributeName.BLOCKELEMENTTYPE, enumVar == null ? null : enumVar.getName(), null);
	}

	/**
	  * (9) get attribute BlockElementType
	  * @return the value of the attribute
	  */
	public EnumBlockElementType getBlockElementType()
	{
		return EnumBlockElementType.getEnum(getAttribute(AttributeName.BLOCKELEMENTTYPE, null, null));
	}

}// end namespace JDF
