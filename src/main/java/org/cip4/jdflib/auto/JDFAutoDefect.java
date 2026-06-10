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
import org.cip4.jdflib.core.ElemInfoTable;
import org.cip4.jdflib.core.ElementInfo;
import org.cip4.jdflib.core.ElementName;
import org.cip4.jdflib.core.JDFCoreConstants;
import org.cip4.jdflib.core.JDFElement;
import org.cip4.jdflib.datatypes.JDFRectangle;
import org.cip4.jdflib.resource.process.JDFFileSpec;
import org.cip4.jdflib.util.JavaEnumUtil;

/**
 ***************************************************************************** class JDFAutoDefect : public JDFElement
 */

public abstract class JDFAutoDefect extends JDFElement
{

	private static final long serialVersionUID = 1L;

	private static AtrInfoTable[] atrInfoTable = new AtrInfoTable[7];
	static
	{
		atrInfoTable[0] = new AtrInfoTable(AttributeName.BOX, 0x3331111111l, AttributeInfo.EnumAttributeType.rectangle, null, null);
		atrInfoTable[1] = new AtrInfoTable(AttributeName.DEFECTREASON, 0x3333333311l, AttributeInfo.EnumAttributeType.NMTOKEN, null, null);
		atrInfoTable[2] = new AtrInfoTable(AttributeName.DEFECTTYPE, 0x3333333311l, AttributeInfo.EnumAttributeType.enumeration,
				JavaEnumUtil.getEnum(EnumDefectType.class, 0), null);
		atrInfoTable[3] = new AtrInfoTable(AttributeName.DEFECTTYPEDETAILS, 0x3333333311l, AttributeInfo.EnumAttributeType.NMTOKEN, null, null);
		atrInfoTable[4] = new AtrInfoTable(AttributeName.FACE, 0x3333333311l, AttributeInfo.EnumAttributeType.enumeration,
				JavaEnumUtil.getEnum(EnumFace.class, 0), null);
		atrInfoTable[5] = new AtrInfoTable(AttributeName.SEVERITY, 0x3331111111l, AttributeInfo.EnumAttributeType.integer, null, null);
		atrInfoTable[6] = new AtrInfoTable(AttributeName.SIZE, 0x3333333311l, AttributeInfo.EnumAttributeType.double_, null, null);
	}

	@Override
	protected AttributeInfo getTheAttributeInfo()
	{
		return super.getTheAttributeInfo().updateReplace(atrInfoTable);
	}

	private static ElemInfoTable[] elemInfoTable = new ElemInfoTable[1];
	static
	{
		elemInfoTable[0] = new ElemInfoTable(ElementName.FILESPEC, 0x6666666611l);
	}

	@Override
	protected ElementInfo getTheElementInfo()
	{
		return super.getTheElementInfo().updateReplace(elemInfoTable);
	}

	/**
	 * Constructor for JDFAutoDefect
	 *
	 * @param myOwnerDocument
	 * @param qualifiedName
	 */
	protected JDFAutoDefect(final CoreDocumentImpl myOwnerDocument, final String qualifiedName)
	{
		super(myOwnerDocument, qualifiedName);
	}

	/**
	 * Constructor for JDFAutoDefect
	 *
	 * @param myOwnerDocument
	 * @param myNamespaceURI
	 * @param qualifiedName
	 */
	protected JDFAutoDefect(final CoreDocumentImpl myOwnerDocument, final String myNamespaceURI, final String qualifiedName)
	{
		super(myOwnerDocument, myNamespaceURI, qualifiedName);
	}

	/**
	 * Constructor for JDFAutoDefect
	 *
	 * @param myOwnerDocument
	 * @param myNamespaceURI
	 * @param qualifiedName
	 * @param myLocalName
	 */
	protected JDFAutoDefect(final CoreDocumentImpl myOwnerDocument, final String myNamespaceURI, final String qualifiedName, final String myLocalName)
	{
		super(myOwnerDocument, myNamespaceURI, qualifiedName, myLocalName);
	}

	/**
	 * Enumeration strings for numDefectType
	 */

	public enum EnumDefectType
	{
		FinishingDefect, ImageDefect, ImageFinishingDefect, Other, SheetDefect, SubstrateDefect;

		public static EnumDefectType getEnum(final String val)
		{
			return JavaEnumUtil.getEnumIgnoreCase(EnumDefectType.class, val, null);
		}
	}

	/**
	 * Enumeration strings for numFace
	 */

	public enum EnumFace
	{
		Top, Bottom, Left, Right, Front, Back;

		public static EnumFace getEnum(final String val)
		{
			return JavaEnumUtil.getEnumIgnoreCase(EnumFace.class, val, null);
		}
	}/*
		 * ************************************************************************
		 * Attribute getter / setter
		 * ************************************************************************
		 */

	/*
	 * ---------------------------------------------------------------------
	 * Methods for Attribute Box
	 * ---------------------------------------------------------------------
	 */
	/**
	 * (36) set attribute Box
	 *
	 * @param value the value to set the attribute to
	 */
	public void setBox(final JDFRectangle value)
	{
		setAttribute(AttributeName.BOX, value, null);
	}

	/**
	 * (20) get JDFRectangle attribute Box
	 *
	 * @return JDFRectangle the value of the attribute, null if a the
	 *         attribute value is not a valid to create a JDFRectangle
	 */
	public JDFRectangle getBox()
	{
		final String strAttrName = getAttribute(AttributeName.BOX, null, null);
		final JDFRectangle nPlaceHolder = JDFRectangle.createRectangle(strAttrName);
		return nPlaceHolder;
	}

	/*
	 * ---------------------------------------------------------------------
	 * Methods for Attribute DefectReason
	 * ---------------------------------------------------------------------
	 */
	/**
	 * (36) set attribute DefectReason
	 *
	 * @param value the value to set the attribute to
	 */
	public void setDefectReason(final String value)
	{
		setAttribute(AttributeName.DEFECTREASON, value, null);
	}

	/**
	 * (23) get String attribute DefectReason
	 *
	 * @return the value of the attribute
	 */
	public String getDefectReason()
	{
		return getAttribute(AttributeName.DEFECTREASON, null, JDFCoreConstants.EMPTYSTRING);
	}

	/*
	 * ---------------------------------------------------------------------
	 * Methods for Attribute DefectType
	 * ---------------------------------------------------------------------
	 */
	/**
	 * (5) set attribute DefectType
	 *
	 * @param enumVar the enumVar to set the attribute to
	 */
	public void setDefectType(final EnumDefectType enumVar)
	{
		setAttribute(AttributeName.DEFECTTYPE, JavaEnumUtil.getName(enumVar), null);
	}

	/**
	 * (9) get attribute DefectType
	 *
	 * @return the value of the attribute
	 */
	public EnumDefectType getDefectType()
	{
		return EnumDefectType.getEnum(getAttribute(AttributeName.DEFECTTYPE, null, null));
	}

	/*
	 * ---------------------------------------------------------------------
	 * Methods for Attribute DefectTypeDetails
	 * ---------------------------------------------------------------------
	 */
	/**
	 * (36) set attribute DefectTypeDetails
	 *
	 * @param value the value to set the attribute to
	 */
	public void setDefectTypeDetails(final String value)
	{
		setAttribute(AttributeName.DEFECTTYPEDETAILS, value, null);
	}

	/**
	 * (23) get String attribute DefectTypeDetails
	 *
	 * @return the value of the attribute
	 */
	public String getDefectTypeDetails()
	{
		return getAttribute(AttributeName.DEFECTTYPEDETAILS, null, JDFCoreConstants.EMPTYSTRING);
	}

	/*
	 * ---------------------------------------------------------------------
	 * Methods for Attribute Face
	 * ---------------------------------------------------------------------
	 */
	/**
	 * (5) set attribute Face
	 *
	 * @param enumVar the enumVar to set the attribute to
	 */
	public void setFace(final EnumFace enumVar)
	{
		setAttribute(AttributeName.FACE, JavaEnumUtil.getName(enumVar), null);
	}

	/**
	 * (9) get attribute Face
	 *
	 * @return the value of the attribute
	 */
	public EnumFace getFace()
	{
		return EnumFace.getEnum(getAttribute(AttributeName.FACE, null, null));
	}

	/*
	 * ---------------------------------------------------------------------
	 * Methods for Attribute Severity
	 * ---------------------------------------------------------------------
	 */
	/**
	 * (36) set attribute Severity
	 *
	 * @param value the value to set the attribute to
	 */
	public void setSeverity(final int value)
	{
		setAttribute(AttributeName.SEVERITY, value, null);
	}

	/**
	 * (15) get int attribute Severity
	 *
	 * @return int the value of the attribute
	 */
	public int getSeverity()
	{
		return getIntAttribute(AttributeName.SEVERITY, null, 0);
	}

	/*
	 * ---------------------------------------------------------------------
	 * Methods for Attribute Size
	 * ---------------------------------------------------------------------
	 */
	/**
	 * (36) set attribute Size
	 *
	 * @param value the value to set the attribute to
	 */
	public void setSize(final double value)
	{
		setAttribute(AttributeName.SIZE, value, null);
	}

	/**
	 * (17) get double attribute Size
	 *
	 * @return double the value of the attribute
	 */
	public double getSize()
	{
		return getRealAttribute(AttributeName.SIZE, null, 0.0);
	}

	/*
	 * ***********************************************************************
	 * Element getter / setter
	 * ***********************************************************************
	 */

	/**
	 * (24) const get element FileSpec
	 *
	 * @return JDFFileSpec the element
	 */
	public JDFFileSpec getFileSpec()
	{
		return (JDFFileSpec) getElement(ElementName.FILESPEC, null, 0);
	}

	/**
	 * (25) getCreateFileSpec
	 * 
	 * @return JDFFileSpec the element
	 */
	public JDFFileSpec getCreateFileSpec()
	{
		return (JDFFileSpec) getCreateElement_JDFElement(ElementName.FILESPEC, null, 0);
	}

	/**
	 * (29) append element FileSpec
	 *
	 * @return JDFFileSpec the element
	 * @ if the element already exists
	 */
	public JDFFileSpec appendFileSpec()
	{
		return (JDFFileSpec) appendElementN(ElementName.FILESPEC, 1, null);
	}

}
