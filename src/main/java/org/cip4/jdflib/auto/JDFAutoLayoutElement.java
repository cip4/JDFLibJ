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

import org.apache.xerces.dom.CoreDocumentImpl;
import org.cip4.jdflib.core.AtrInfoTable;
import org.cip4.jdflib.core.AttributeInfo;
import org.cip4.jdflib.core.AttributeName;
import org.cip4.jdflib.core.ElemInfoTable;
import org.cip4.jdflib.core.ElementInfo;
import org.cip4.jdflib.core.ElementName;
import org.cip4.jdflib.core.JDFCoreConstants;
import org.cip4.jdflib.core.VString;
import org.cip4.jdflib.datatypes.JDFIntegerRangeList;
import org.cip4.jdflib.datatypes.JDFRectangle;
import org.cip4.jdflib.resource.JDFPageList;
import org.cip4.jdflib.resource.JDFResource;
import org.cip4.jdflib.resource.process.JDFColorPool;
import org.cip4.jdflib.resource.process.JDFContentList;
import org.cip4.jdflib.resource.process.JDFDependencies;
import org.cip4.jdflib.resource.process.JDFElementColorParams;
import org.cip4.jdflib.resource.process.JDFFileSpec;
import org.cip4.jdflib.resource.process.JDFImageCompressionParams;
import org.cip4.jdflib.resource.process.JDFSeparationSpec;
import org.cip4.jdflib.resource.process.prepress.JDFScreeningParams;
import org.cip4.jdflib.util.JavaEnumUtil;

/**
 ***************************************************************************** class JDFAutoLayoutElement : public JDFResource
 */

public abstract class JDFAutoLayoutElement extends JDFResource
{

	private static final long serialVersionUID = 1L;

	private static AtrInfoTable[] atrInfoTable = new AtrInfoTable[16];
	static
	{
		atrInfoTable[0] = new AtrInfoTable(AttributeName.IGNOREPDLCOPIES, 0x3333333331l, AttributeInfo.EnumAttributeType.boolean_, null, "false");
		atrInfoTable[1] = new AtrInfoTable(AttributeName.IGNOREPDLIMPOSITION, 0x3333333331l, AttributeInfo.EnumAttributeType.boolean_, null, "true");
		atrInfoTable[2] = new AtrInfoTable(AttributeName.CLIPPATH, 0x3333333333l, AttributeInfo.EnumAttributeType.PDFPath, null, null);
		atrInfoTable[3] = new AtrInfoTable(AttributeName.CONTENTDATAREFS, 0x3333331111l, AttributeInfo.EnumAttributeType.IDREFS, null, null);
		atrInfoTable[4] = new AtrInfoTable(AttributeName.ELEMENTTYPE, 0x3333333333l, AttributeInfo.EnumAttributeType.enumeration,
				JavaEnumUtil.getEnum(EnumElementType.class, 0), null);
		atrInfoTable[5] = new AtrInfoTable(AttributeName.HASBLEEDS, 0x3333333333l, AttributeInfo.EnumAttributeType.boolean_, null, null);
		atrInfoTable[6] = new AtrInfoTable(AttributeName.ISBLANK, 0x3333333311l, AttributeInfo.EnumAttributeType.boolean_, null, null);
		atrInfoTable[7] = new AtrInfoTable(AttributeName.ISPRINTABLE, 0x3333333333l, AttributeInfo.EnumAttributeType.boolean_, null, null);
		atrInfoTable[8] = new AtrInfoTable(AttributeName.ISTRAPPED, 0x3333333333l, AttributeInfo.EnumAttributeType.boolean_, null, null);
		atrInfoTable[9] = new AtrInfoTable(AttributeName.PAGELISTINDEX, 0x3333333311l, AttributeInfo.EnumAttributeType.IntegerRangeList, null, null);
		atrInfoTable[10] = new AtrInfoTable(AttributeName.SETLEVEL, 0x3333331111l, AttributeInfo.EnumAttributeType.XPath, null, null);
		atrInfoTable[11] = new AtrInfoTable(AttributeName.SOURCEBLEEDBOX, 0x3333333333l, AttributeInfo.EnumAttributeType.rectangle, null, null);
		atrInfoTable[12] = new AtrInfoTable(AttributeName.SOURCECLIPBOX, 0x3333333333l, AttributeInfo.EnumAttributeType.rectangle, null, null);
		atrInfoTable[13] = new AtrInfoTable(AttributeName.SOURCEMEDIABOX, 0x3333331111l, AttributeInfo.EnumAttributeType.rectangle, null, null);
		atrInfoTable[14] = new AtrInfoTable(AttributeName.SOURCETRIMBOX, 0x3333333333l, AttributeInfo.EnumAttributeType.rectangle, null, null);
		atrInfoTable[15] = new AtrInfoTable(AttributeName.TEMPLATE, 0x3333333333l, AttributeInfo.EnumAttributeType.boolean_, null, null);
	}

	@Override
	protected AttributeInfo getTheAttributeInfo()
	{
		return super.getTheAttributeInfo().updateReplace(atrInfoTable);
	}

	private static ElemInfoTable[] elemInfoTable = new ElemInfoTable[9];
	static
	{
		elemInfoTable[0] = new ElemInfoTable(ElementName.COLORPOOL, 0x6666666611l);
		elemInfoTable[1] = new ElemInfoTable(ElementName.CONTENTLIST, 0x3333331111l);
		elemInfoTable[2] = new ElemInfoTable(ElementName.DEPENDENCIES, 0x6666666611l);
		elemInfoTable[3] = new ElemInfoTable(ElementName.ELEMENTCOLORPARAMS, 0x6666666611l);
		elemInfoTable[4] = new ElemInfoTable(ElementName.FILESPEC, 0x6666666666l);
		elemInfoTable[5] = new ElemInfoTable(ElementName.IMAGECOMPRESSIONPARAMS, 0x6666666611l);
		elemInfoTable[6] = new ElemInfoTable(ElementName.PAGELIST, 0x6666666611l);
		elemInfoTable[7] = new ElemInfoTable(ElementName.SCREENINGPARAMS, 0x6666666611l);
		elemInfoTable[8] = new ElemInfoTable(ElementName.SEPARATIONSPEC, 0x3333333333l);
	}

	@Override
	protected ElementInfo getTheElementInfo()
	{
		return super.getTheElementInfo().updateReplace(elemInfoTable);
	}

	/**
	 * Constructor for JDFAutoLayoutElement
	 *
	 * @param myOwnerDocument
	 * @param qualifiedName
	 */
	protected JDFAutoLayoutElement(final CoreDocumentImpl myOwnerDocument, final String qualifiedName)
	{
		super(myOwnerDocument, qualifiedName);
	}

	/**
	 * Constructor for JDFAutoLayoutElement
	 *
	 * @param myOwnerDocument
	 * @param myNamespaceURI
	 * @param qualifiedName
	 */
	protected JDFAutoLayoutElement(final CoreDocumentImpl myOwnerDocument, final String myNamespaceURI, final String qualifiedName)
	{
		super(myOwnerDocument, myNamespaceURI, qualifiedName);
	}

	/**
	 * Constructor for JDFAutoLayoutElement
	 *
	 * @param myOwnerDocument
	 * @param myNamespaceURI
	 * @param qualifiedName
	 * @param myLocalName
	 */
	protected JDFAutoLayoutElement(final CoreDocumentImpl myOwnerDocument, final String myNamespaceURI, final String qualifiedName, final String myLocalName)
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
	 * Enumeration strings for numElementType
	 */

	public enum EnumElementType
	{
		Auxiliary, Barcode, Composed, Document, Graphic, IdentificationField, Image, MultiDocument, MultiSet, Page, Reservation, Surface, Text, Tile, Unknown;

		public static EnumElementType getEnum(final String val)
		{
			return JavaEnumUtil.getEnumIgnoreCase(EnumElementType.class, val, null);
		}
	}/*
		 * ************************************************************************
		 * Attribute getter / setter
		 * ************************************************************************
		 */

	/*
	 * ---------------------------------------------------------------------
	 * Methods for Attribute IgnorePDLCopies
	 * ---------------------------------------------------------------------
	 */
	/**
	 * (36) set attribute IgnorePDLCopies
	 *
	 * @param value the value to set the attribute to
	 */
	public void setIgnorePDLCopies(final boolean value)
	{
		setAttribute(AttributeName.IGNOREPDLCOPIES, value, null);
	}

	/**
	 * (18) get boolean attribute IgnorePDLCopies
	 *
	 * @return boolean the value of the attribute
	 */
	public boolean getIgnorePDLCopies()
	{
		return getBoolAttribute(AttributeName.IGNOREPDLCOPIES, null, false);
	}

	/*
	 * ---------------------------------------------------------------------
	 * Methods for Attribute IgnorePDLImposition
	 * ---------------------------------------------------------------------
	 */
	/**
	 * (36) set attribute IgnorePDLImposition
	 *
	 * @param value the value to set the attribute to
	 */
	public void setIgnorePDLImposition(final boolean value)
	{
		setAttribute(AttributeName.IGNOREPDLIMPOSITION, value, null);
	}

	/**
	 * (18) get boolean attribute IgnorePDLImposition
	 *
	 * @return boolean the value of the attribute
	 */
	public boolean getIgnorePDLImposition()
	{
		return getBoolAttribute(AttributeName.IGNOREPDLIMPOSITION, null, true);
	}

	/*
	 * ---------------------------------------------------------------------
	 * Methods for Attribute ClipPath
	 * ---------------------------------------------------------------------
	 */
	/**
	 * (36) set attribute ClipPath
	 *
	 * @param value the value to set the attribute to
	 */
	public void setClipPath(final String value)
	{
		setAttribute(AttributeName.CLIPPATH, value, null);
	}

	/**
	 * (23) get String attribute ClipPath
	 *
	 * @return the value of the attribute
	 */
	public String getClipPath()
	{
		return getAttribute(AttributeName.CLIPPATH, null, JDFCoreConstants.EMPTYSTRING);
	}

	/*
	 * ---------------------------------------------------------------------
	 * Methods for Attribute ContentDataRefs
	 * ---------------------------------------------------------------------
	 */
	/**
	 * (36) set attribute ContentDataRefs
	 *
	 * @param value the value to set the attribute to
	 */
	public void setContentDataRefs(final VString value)
	{
		setAttribute(AttributeName.CONTENTDATAREFS, value, null);
	}

	/**
	 * (21) get VString attribute ContentDataRefs
	 *
	 * @return VString the value of the attribute
	 */
	public VString getContentDataRefs()
	{
		final VString vStrAttrib = new VString();
		final String s = getAttribute(AttributeName.CONTENTDATAREFS, null, JDFCoreConstants.EMPTYSTRING);
		vStrAttrib.setAllStrings(s, " ");
		return vStrAttrib;
	}

	/*
	 * ---------------------------------------------------------------------
	 * Methods for Attribute ElementType
	 * ---------------------------------------------------------------------
	 */
	/**
	 * (5) set attribute ElementType
	 *
	 * @param enumVar the enumVar to set the attribute to
	 */
	public void setElementType(final EnumElementType enumVar)
	{
		setAttribute(AttributeName.ELEMENTTYPE, JavaEnumUtil.getName(enumVar), null);
	}

	/**
	 * (9) get attribute ElementType
	 *
	 * @return the value of the attribute
	 */
	public EnumElementType getElementType()
	{
		return EnumElementType.getEnum(getAttribute(AttributeName.ELEMENTTYPE, null, null));
	}

	/*
	 * ---------------------------------------------------------------------
	 * Methods for Attribute HasBleeds
	 * ---------------------------------------------------------------------
	 */
	/**
	 * (36) set attribute HasBleeds
	 *
	 * @param value the value to set the attribute to
	 */
	public void setHasBleeds(final boolean value)
	{
		setAttribute(AttributeName.HASBLEEDS, value, null);
	}

	/**
	 * (18) get boolean attribute HasBleeds
	 *
	 * @return boolean the value of the attribute
	 */
	public boolean getHasBleeds()
	{
		return getBoolAttribute(AttributeName.HASBLEEDS, null, false);
	}

	/*
	 * ---------------------------------------------------------------------
	 * Methods for Attribute IsBlank
	 * ---------------------------------------------------------------------
	 */
	/**
	 * (36) set attribute IsBlank
	 *
	 * @param value the value to set the attribute to
	 */
	public void setIsBlank(final boolean value)
	{
		setAttribute(AttributeName.ISBLANK, value, null);
	}

	/**
	 * (18) get boolean attribute IsBlank
	 *
	 * @return boolean the value of the attribute
	 */
	public boolean getIsBlank()
	{
		return getBoolAttribute(AttributeName.ISBLANK, null, false);
	}

	/*
	 * ---------------------------------------------------------------------
	 * Methods for Attribute IsPrintable
	 * ---------------------------------------------------------------------
	 */
	/**
	 * (36) set attribute IsPrintable
	 *
	 * @param value the value to set the attribute to
	 */
	public void setIsPrintable(final boolean value)
	{
		setAttribute(AttributeName.ISPRINTABLE, value, null);
	}

	/**
	 * (18) get boolean attribute IsPrintable
	 *
	 * @return boolean the value of the attribute
	 */
	public boolean getIsPrintable()
	{
		return getBoolAttribute(AttributeName.ISPRINTABLE, null, false);
	}

	/*
	 * ---------------------------------------------------------------------
	 * Methods for Attribute IsTrapped
	 * ---------------------------------------------------------------------
	 */
	/**
	 * (36) set attribute IsTrapped
	 *
	 * @param value the value to set the attribute to
	 */
	public void setIsTrapped(final boolean value)
	{
		setAttribute(AttributeName.ISTRAPPED, value, null);
	}

	/**
	 * (18) get boolean attribute IsTrapped
	 *
	 * @return boolean the value of the attribute
	 */
	public boolean getIsTrapped()
	{
		return getBoolAttribute(AttributeName.ISTRAPPED, null, false);
	}

	/*
	 * ---------------------------------------------------------------------
	 * Methods for Attribute PageListIndex
	 * ---------------------------------------------------------------------
	 */
	/**
	 * (36) set attribute PageListIndex
	 *
	 * @param value the value to set the attribute to
	 */
	public void setPageListIndex(final JDFIntegerRangeList value)
	{
		setAttribute(AttributeName.PAGELISTINDEX, value, null);
	}

	/**
	 * (20) get JDFIntegerRangeList attribute PageListIndex
	 *
	 * @return JDFIntegerRangeList the value of the attribute, null if a the
	 *         attribute value is not a valid to create a JDFIntegerRangeList
	 */
	public JDFIntegerRangeList getPageListIndex()
	{
		final String strAttrName = getAttribute(AttributeName.PAGELISTINDEX, null, null);
		final JDFIntegerRangeList nPlaceHolder = JDFIntegerRangeList.createIntegerRangeList(strAttrName);
		return nPlaceHolder;
	}

	/*
	 * ---------------------------------------------------------------------
	 * Methods for Attribute SetLevel
	 * ---------------------------------------------------------------------
	 */
	/**
	 * (36) set attribute SetLevel
	 *
	 * @param value the value to set the attribute to
	 */
	public void setSetLevel(final String value)
	{
		setAttribute(AttributeName.SETLEVEL, value, null);
	}

	/**
	 * (23) get String attribute SetLevel
	 *
	 * @return the value of the attribute
	 */
	public String getSetLevel()
	{
		return getAttribute(AttributeName.SETLEVEL, null, JDFCoreConstants.EMPTYSTRING);
	}

	/*
	 * ---------------------------------------------------------------------
	 * Methods for Attribute SourceBleedBox
	 * ---------------------------------------------------------------------
	 */
	/**
	 * (36) set attribute SourceBleedBox
	 *
	 * @param value the value to set the attribute to
	 */
	public void setSourceBleedBox(final JDFRectangle value)
	{
		setAttribute(AttributeName.SOURCEBLEEDBOX, value, null);
	}

	/**
	 * (20) get JDFRectangle attribute SourceBleedBox
	 *
	 * @return JDFRectangle the value of the attribute, null if a the
	 *         attribute value is not a valid to create a JDFRectangle
	 */
	public JDFRectangle getSourceBleedBox()
	{
		final String strAttrName = getAttribute(AttributeName.SOURCEBLEEDBOX, null, null);
		final JDFRectangle nPlaceHolder = JDFRectangle.createRectangle(strAttrName);
		return nPlaceHolder;
	}

	/*
	 * ---------------------------------------------------------------------
	 * Methods for Attribute SourceClipBox
	 * ---------------------------------------------------------------------
	 */
	/**
	 * (36) set attribute SourceClipBox
	 *
	 * @param value the value to set the attribute to
	 */
	public void setSourceClipBox(final JDFRectangle value)
	{
		setAttribute(AttributeName.SOURCECLIPBOX, value, null);
	}

	/**
	 * (20) get JDFRectangle attribute SourceClipBox
	 *
	 * @return JDFRectangle the value of the attribute, null if a the
	 *         attribute value is not a valid to create a JDFRectangle
	 */
	public JDFRectangle getSourceClipBox()
	{
		final String strAttrName = getAttribute(AttributeName.SOURCECLIPBOX, null, null);
		final JDFRectangle nPlaceHolder = JDFRectangle.createRectangle(strAttrName);
		return nPlaceHolder;
	}

	/*
	 * ---------------------------------------------------------------------
	 * Methods for Attribute SourceMediaBox
	 * ---------------------------------------------------------------------
	 */
	/**
	 * (36) set attribute SourceMediaBox
	 *
	 * @param value the value to set the attribute to
	 */
	public void setSourceMediaBox(final JDFRectangle value)
	{
		setAttribute(AttributeName.SOURCEMEDIABOX, value, null);
	}

	/**
	 * (20) get JDFRectangle attribute SourceMediaBox
	 *
	 * @return JDFRectangle the value of the attribute, null if a the
	 *         attribute value is not a valid to create a JDFRectangle
	 */
	public JDFRectangle getSourceMediaBox()
	{
		final String strAttrName = getAttribute(AttributeName.SOURCEMEDIABOX, null, null);
		final JDFRectangle nPlaceHolder = JDFRectangle.createRectangle(strAttrName);
		return nPlaceHolder;
	}

	/*
	 * ---------------------------------------------------------------------
	 * Methods for Attribute SourceTrimBox
	 * ---------------------------------------------------------------------
	 */
	/**
	 * (36) set attribute SourceTrimBox
	 *
	 * @param value the value to set the attribute to
	 */
	public void setSourceTrimBox(final JDFRectangle value)
	{
		setAttribute(AttributeName.SOURCETRIMBOX, value, null);
	}

	/**
	 * (20) get JDFRectangle attribute SourceTrimBox
	 *
	 * @return JDFRectangle the value of the attribute, null if a the
	 *         attribute value is not a valid to create a JDFRectangle
	 */
	public JDFRectangle getSourceTrimBox()
	{
		final String strAttrName = getAttribute(AttributeName.SOURCETRIMBOX, null, null);
		final JDFRectangle nPlaceHolder = JDFRectangle.createRectangle(strAttrName);
		return nPlaceHolder;
	}

	/*
	 * ---------------------------------------------------------------------
	 * Methods for Attribute Template
	 * ---------------------------------------------------------------------
	 */
	/**
	 * (36) set attribute Template
	 *
	 * @param value the value to set the attribute to
	 */
	public void setTemplate(final boolean value)
	{
		setAttribute(AttributeName.TEMPLATE, value, null);
	}

	/**
	 * (18) get boolean attribute Template
	 *
	 * @return boolean the value of the attribute
	 */
	public boolean getTemplate()
	{
		return getBoolAttribute(AttributeName.TEMPLATE, null, false);
	}

	/*
	 * ***********************************************************************
	 * Element getter / setter
	 * ***********************************************************************
	 */

	/**
	 * (24) const get element ColorPool
	 *
	 * @return JDFColorPool the element
	 */
	public JDFColorPool getColorPool()
	{
		return (JDFColorPool) getElement(ElementName.COLORPOOL, null, 0);
	}

	/**
	 * (25) getCreateColorPool
	 * 
	 * @return JDFColorPool the element
	 */
	public JDFColorPool getCreateColorPool()
	{
		return (JDFColorPool) getCreateElement_JDFElement(ElementName.COLORPOOL, null, 0);
	}

	/**
	 * (29) append element ColorPool
	 *
	 * @return JDFColorPool the element
	 * @ if the element already exists
	 */
	public JDFColorPool appendColorPool()
	{
		return (JDFColorPool) appendElementN(ElementName.COLORPOOL, 1, null);
	}

	/**
	 * (31) create inter-resource link to refTarget
	 *
	 * @param refTarget the element that is referenced
	 */
	public void refColorPool(final JDFColorPool refTarget)
	{
		refElement(refTarget);
	}

	/**
	 * (24) const get element ContentList
	 *
	 * @return JDFContentList the element
	 */
	public JDFContentList getContentList()
	{
		return (JDFContentList) getElement(ElementName.CONTENTLIST, null, 0);
	}

	/**
	 * (25) getCreateContentList
	 * 
	 * @return JDFContentList the element
	 */
	public JDFContentList getCreateContentList()
	{
		return (JDFContentList) getCreateElement_JDFElement(ElementName.CONTENTLIST, null, 0);
	}

	/**
	 * (26) getCreateContentList
	 * 
	 * @param iSkip number of elements to skip
	 * @return JDFContentList the element
	 */
	public JDFContentList getCreateContentList(final int iSkip)
	{
		return (JDFContentList) getCreateElement_JDFElement(ElementName.CONTENTLIST, null, iSkip);
	}

	/**
	 * (27) const get element ContentList
	 *
	 * @param iSkip number of elements to skip
	 * @return JDFContentList the element
	 *         default is getContentList(0)
	 */
	public JDFContentList getContentList(final int iSkip)
	{
		return (JDFContentList) getElement(ElementName.CONTENTLIST, null, iSkip);
	}

	/**
	 * Get all ContentList from the current element
	 * 
	 * @return Collection<JDFContentList>, null if none are available
	 */
	public Collection<JDFContentList> getAllContentList()
	{
		return getChildArrayByClass(JDFContentList.class, false, 0);
	}

	/**
	 * (30) append element ContentList
	 *
	 * @return JDFContentList the element
	 */
	public JDFContentList appendContentList()
	{
		return (JDFContentList) appendElement(ElementName.CONTENTLIST, null);
	}

	/**
	 * (31) create inter-resource link to refTarget
	 *
	 * @param refTarget the element that is referenced
	 */
	public void refContentList(final JDFContentList refTarget)
	{
		refElement(refTarget);
	}

	/**
	 * (24) const get element Dependencies
	 *
	 * @return JDFDependencies the element
	 */
	public JDFDependencies getDependencies()
	{
		return (JDFDependencies) getElement(ElementName.DEPENDENCIES, null, 0);
	}

	/**
	 * (25) getCreateDependencies
	 * 
	 * @return JDFDependencies the element
	 */
	public JDFDependencies getCreateDependencies()
	{
		return (JDFDependencies) getCreateElement_JDFElement(ElementName.DEPENDENCIES, null, 0);
	}

	/**
	 * (29) append element Dependencies
	 *
	 * @return JDFDependencies the element
	 * @ if the element already exists
	 */
	public JDFDependencies appendDependencies()
	{
		return (JDFDependencies) appendElementN(ElementName.DEPENDENCIES, 1, null);
	}

	/**
	 * (24) const get element ElementColorParams
	 *
	 * @return JDFElementColorParams the element
	 */
	public JDFElementColorParams getElementColorParams()
	{
		return (JDFElementColorParams) getElement(ElementName.ELEMENTCOLORPARAMS, null, 0);
	}

	/**
	 * (25) getCreateElementColorParams
	 * 
	 * @return JDFElementColorParams the element
	 */
	public JDFElementColorParams getCreateElementColorParams()
	{
		return (JDFElementColorParams) getCreateElement_JDFElement(ElementName.ELEMENTCOLORPARAMS, null, 0);
	}

	/**
	 * (29) append element ElementColorParams
	 *
	 * @return JDFElementColorParams the element
	 * @ if the element already exists
	 */
	public JDFElementColorParams appendElementColorParams()
	{
		return (JDFElementColorParams) appendElementN(ElementName.ELEMENTCOLORPARAMS, 1, null);
	}

	/**
	 * (31) create inter-resource link to refTarget
	 *
	 * @param refTarget the element that is referenced
	 */
	public void refElementColorParams(final JDFElementColorParams refTarget)
	{
		refElement(refTarget);
	}

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

	/**
	 * (31) create inter-resource link to refTarget
	 *
	 * @param refTarget the element that is referenced
	 */
	public void refFileSpec(final JDFFileSpec refTarget)
	{
		refElement(refTarget);
	}

	/**
	 * (24) const get element ImageCompressionParams
	 *
	 * @return JDFImageCompressionParams the element
	 */
	public JDFImageCompressionParams getImageCompressionParams()
	{
		return (JDFImageCompressionParams) getElement(ElementName.IMAGECOMPRESSIONPARAMS, null, 0);
	}

	/**
	 * (25) getCreateImageCompressionParams
	 * 
	 * @return JDFImageCompressionParams the element
	 */
	public JDFImageCompressionParams getCreateImageCompressionParams()
	{
		return (JDFImageCompressionParams) getCreateElement_JDFElement(ElementName.IMAGECOMPRESSIONPARAMS, null, 0);
	}

	/**
	 * (29) append element ImageCompressionParams
	 *
	 * @return JDFImageCompressionParams the element
	 * @ if the element already exists
	 */
	public JDFImageCompressionParams appendImageCompressionParams()
	{
		return (JDFImageCompressionParams) appendElementN(ElementName.IMAGECOMPRESSIONPARAMS, 1, null);
	}

	/**
	 * (31) create inter-resource link to refTarget
	 *
	 * @param refTarget the element that is referenced
	 */
	public void refImageCompressionParams(final JDFImageCompressionParams refTarget)
	{
		refElement(refTarget);
	}

	/**
	 * (24) const get element PageList
	 *
	 * @return JDFPageList the element
	 */
	public JDFPageList getPageList()
	{
		return (JDFPageList) getElement(ElementName.PAGELIST, null, 0);
	}

	/**
	 * (25) getCreatePageList
	 * 
	 * @return JDFPageList the element
	 */
	public JDFPageList getCreatePageList()
	{
		return (JDFPageList) getCreateElement_JDFElement(ElementName.PAGELIST, null, 0);
	}

	/**
	 * (29) append element PageList
	 *
	 * @return JDFPageList the element
	 * @ if the element already exists
	 */
	public JDFPageList appendPageList()
	{
		return (JDFPageList) appendElementN(ElementName.PAGELIST, 1, null);
	}

	/**
	 * (31) create inter-resource link to refTarget
	 *
	 * @param refTarget the element that is referenced
	 */
	public void refPageList(final JDFPageList refTarget)
	{
		refElement(refTarget);
	}

	/**
	 * (24) const get element ScreeningParams
	 *
	 * @return JDFScreeningParams the element
	 */
	public JDFScreeningParams getScreeningParams()
	{
		return (JDFScreeningParams) getElement(ElementName.SCREENINGPARAMS, null, 0);
	}

	/**
	 * (25) getCreateScreeningParams
	 * 
	 * @return JDFScreeningParams the element
	 */
	public JDFScreeningParams getCreateScreeningParams()
	{
		return (JDFScreeningParams) getCreateElement_JDFElement(ElementName.SCREENINGPARAMS, null, 0);
	}

	/**
	 * (29) append element ScreeningParams
	 *
	 * @return JDFScreeningParams the element
	 * @ if the element already exists
	 */
	public JDFScreeningParams appendScreeningParams()
	{
		return (JDFScreeningParams) appendElementN(ElementName.SCREENINGPARAMS, 1, null);
	}

	/**
	 * (31) create inter-resource link to refTarget
	 *
	 * @param refTarget the element that is referenced
	 */
	public void refScreeningParams(final JDFScreeningParams refTarget)
	{
		refElement(refTarget);
	}

	/**
	 * (24) const get element SeparationSpec
	 *
	 * @return JDFSeparationSpec the element
	 */
	public JDFSeparationSpec getSeparationSpec()
	{
		return (JDFSeparationSpec) getElement(ElementName.SEPARATIONSPEC, null, 0);
	}

	/**
	 * (25) getCreateSeparationSpec
	 * 
	 * @return JDFSeparationSpec the element
	 */
	public JDFSeparationSpec getCreateSeparationSpec()
	{
		return (JDFSeparationSpec) getCreateElement_JDFElement(ElementName.SEPARATIONSPEC, null, 0);
	}

	/**
	 * (26) getCreateSeparationSpec
	 * 
	 * @param iSkip number of elements to skip
	 * @return JDFSeparationSpec the element
	 */
	public JDFSeparationSpec getCreateSeparationSpec(final int iSkip)
	{
		return (JDFSeparationSpec) getCreateElement_JDFElement(ElementName.SEPARATIONSPEC, null, iSkip);
	}

	/**
	 * (27) const get element SeparationSpec
	 *
	 * @param iSkip number of elements to skip
	 * @return JDFSeparationSpec the element
	 *         default is getSeparationSpec(0)
	 */
	public JDFSeparationSpec getSeparationSpec(final int iSkip)
	{
		return (JDFSeparationSpec) getElement(ElementName.SEPARATIONSPEC, null, iSkip);
	}

	/**
	 * Get all SeparationSpec from the current element
	 * 
	 * @return Collection<JDFSeparationSpec>, null if none are available
	 */
	public Collection<JDFSeparationSpec> getAllSeparationSpec()
	{
		return getChildArrayByClass(JDFSeparationSpec.class, false, 0);
	}

	/**
	 * (30) append element SeparationSpec
	 *
	 * @return JDFSeparationSpec the element
	 */
	public JDFSeparationSpec appendSeparationSpec()
	{
		return (JDFSeparationSpec) appendElement(ElementName.SEPARATIONSPEC, null);
	}

}
