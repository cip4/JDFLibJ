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
import org.cip4.jdflib.resource.JDFResource;
import org.cip4.jdflib.resource.process.JDFFontParams;
import org.cip4.jdflib.resource.process.prepress.JDFPDFToPSConversionParams;
import org.cip4.jdflib.resource.process.prepress.JDFPSToPDFConversionParams;

/**
 *****************************************************************************
 * class JDFAutoPDLCreationParams : public JDFResource
 *****************************************************************************
 * 
 */

public abstract class JDFAutoPDLCreationParams extends JDFResource
{

	private static final long serialVersionUID = 1L;

	private static AtrInfoTable[] atrInfoTable = new AtrInfoTable[1];
	static
	{
		atrInfoTable[0] = new AtrInfoTable(AttributeName.MIMETYPE, 0x2222222111l, AttributeInfo.EnumAttributeType.string, null, null);
	}

	@Override
	protected AttributeInfo getTheAttributeInfo()
	{
		return super.getTheAttributeInfo().updateReplace(atrInfoTable);
	}

	private static ElemInfoTable[] elemInfoTable = new ElemInfoTable[3];
	static
	{
		elemInfoTable[0] = new ElemInfoTable(ElementName.FONTPARAMS, 0x6666666111l);
		elemInfoTable[1] = new ElemInfoTable(ElementName.PDFTOPSCONVERSIONPARAMS, 0x6666666111l);
		elemInfoTable[2] = new ElemInfoTable(ElementName.PSTOPDFCONVERSIONPARAMS, 0x6666666111l);
	}

	@Override
	protected ElementInfo getTheElementInfo()
	{
		return super.getTheElementInfo().updateReplace(elemInfoTable);
	}

	/**
	 * Constructor for JDFAutoPDLCreationParams
	 *
	 * @param myOwnerDocument
	 * @param qualifiedName
	 */
	protected JDFAutoPDLCreationParams(CoreDocumentImpl myOwnerDocument, String qualifiedName)
	{
		super(myOwnerDocument, qualifiedName);
	}

	/**
	 * Constructor for JDFAutoPDLCreationParams
	 *
	 * @param myOwnerDocument
	 * @param myNamespaceURI
	 * @param qualifiedName
	 */
	protected JDFAutoPDLCreationParams(CoreDocumentImpl myOwnerDocument, String myNamespaceURI, String qualifiedName)
	{
		super(myOwnerDocument, myNamespaceURI, qualifiedName);
	}

	/**
	 * Constructor for JDFAutoPDLCreationParams
	 *
	 * @param myOwnerDocument
	 * @param myNamespaceURI
	 * @param qualifiedName
	 * @param myLocalName
	 */
	protected JDFAutoPDLCreationParams(CoreDocumentImpl myOwnerDocument, String myNamespaceURI, String qualifiedName, String myLocalName)
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

	/*
	 * ************************************************************************ Attribute getter / setter ************************************************************************
	 */

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
	 * *********************************************************************** Element getter / setter ***********************************************************************
	 */

	/**
	 * (24) const get element FontParams
	 *
	 * @return JDFFontParams the element
	 */
	public JDFFontParams getFontParams()
	{
		return (JDFFontParams) getElement(ElementName.FONTPARAMS, null, 0);
	}

	/**
	 * (25) getCreateFontParams
	 * 
	 * @return JDFFontParams the element
	 */
	public JDFFontParams getCreateFontParams()
	{
		return (JDFFontParams) getCreateElement_JDFElement(ElementName.FONTPARAMS, null, 0);
	}

	/**
	 * (29) append element FontParams
	 *
	 * @return JDFFontParams the element @ if the element already exists
	 */
	public JDFFontParams appendFontParams()
	{
		return (JDFFontParams) appendElementN(ElementName.FONTPARAMS, 1, null);
	}

	/**
	 * (31) create inter-resource link to refTarget
	 *
	 * @param refTarget the element that is referenced
	 */
	public void refFontParams(JDFFontParams refTarget)
	{
		refElement(refTarget);
	}

	/**
	 * (24) const get element PDFToPSConversionParams
	 *
	 * @return JDFPDFToPSConversionParams the element
	 */
	public JDFPDFToPSConversionParams getPDFToPSConversionParams()
	{
		return (JDFPDFToPSConversionParams) getElement(ElementName.PDFTOPSCONVERSIONPARAMS, null, 0);
	}

	/**
	 * (25) getCreatePDFToPSConversionParams
	 * 
	 * @return JDFPDFToPSConversionParams the element
	 */
	public JDFPDFToPSConversionParams getCreatePDFToPSConversionParams()
	{
		return (JDFPDFToPSConversionParams) getCreateElement_JDFElement(ElementName.PDFTOPSCONVERSIONPARAMS, null, 0);
	}

	/**
	 * (29) append element PDFToPSConversionParams
	 *
	 * @return JDFPDFToPSConversionParams the element @ if the element already exists
	 */
	public JDFPDFToPSConversionParams appendPDFToPSConversionParams()
	{
		return (JDFPDFToPSConversionParams) appendElementN(ElementName.PDFTOPSCONVERSIONPARAMS, 1, null);
	}

	/**
	 * (31) create inter-resource link to refTarget
	 *
	 * @param refTarget the element that is referenced
	 */
	public void refPDFToPSConversionParams(JDFPDFToPSConversionParams refTarget)
	{
		refElement(refTarget);
	}

	/**
	 * (24) const get element PSToPDFConversionParams
	 *
	 * @return JDFPSToPDFConversionParams the element
	 */
	public JDFPSToPDFConversionParams getPSToPDFConversionParams()
	{
		return (JDFPSToPDFConversionParams) getElement(ElementName.PSTOPDFCONVERSIONPARAMS, null, 0);
	}

	/**
	 * (25) getCreatePSToPDFConversionParams
	 * 
	 * @return JDFPSToPDFConversionParams the element
	 */
	public JDFPSToPDFConversionParams getCreatePSToPDFConversionParams()
	{
		return (JDFPSToPDFConversionParams) getCreateElement_JDFElement(ElementName.PSTOPDFCONVERSIONPARAMS, null, 0);
	}

	/**
	 * (29) append element PSToPDFConversionParams
	 *
	 * @return JDFPSToPDFConversionParams the element @ if the element already exists
	 */
	public JDFPSToPDFConversionParams appendPSToPDFConversionParams()
	{
		return (JDFPSToPDFConversionParams) appendElementN(ElementName.PSTOPDFCONVERSIONPARAMS, 1, null);
	}

	/**
	 * (31) create inter-resource link to refTarget
	 *
	 * @param refTarget the element that is referenced
	 */
	public void refPSToPDFConversionParams(JDFPSToPDFConversionParams refTarget)
	{
		refElement(refTarget);
	}

}
