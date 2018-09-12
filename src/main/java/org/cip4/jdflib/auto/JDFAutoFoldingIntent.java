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

package org.cip4.jdflib.auto;

import java.util.Collection;

import org.apache.xerces.dom.CoreDocumentImpl;
import org.cip4.jdflib.core.AtrInfoTable;
import org.cip4.jdflib.core.AttributeInfo;
import org.cip4.jdflib.core.AttributeName;
import org.cip4.jdflib.core.ElemInfoTable;
import org.cip4.jdflib.core.ElementInfo;
import org.cip4.jdflib.core.ElementName;
import org.cip4.jdflib.core.JDFException;
import org.cip4.jdflib.datatypes.JDFXYPair;
import org.cip4.jdflib.resource.intent.JDFIntentResource;
import org.cip4.jdflib.resource.process.postpress.JDFFold;
import org.cip4.jdflib.span.JDFNameSpan;

/**
 *****************************************************************************
 * class JDFAutoFoldingIntent : public JDFIntentResource
 *****************************************************************************
 * 
 */

public abstract class JDFAutoFoldingIntent extends JDFIntentResource
{

	private static final long serialVersionUID = 1L;

	private static AtrInfoTable[] atrInfoTable = new AtrInfoTable[1];
	static
	{
		atrInfoTable[0] = new AtrInfoTable(AttributeName.FOLDS, 0x44444443, AttributeInfo.EnumAttributeType.XYPair, null, null);
	}

	@Override
	protected AttributeInfo getTheAttributeInfo()
	{
		return super.getTheAttributeInfo().updateReplace(atrInfoTable);
	}

	private static ElemInfoTable[] elemInfoTable = new ElemInfoTable[2];
	static
	{
		elemInfoTable[0] = new ElemInfoTable(ElementName.FOLDINGCATALOG, 0x55555555);
		elemInfoTable[1] = new ElemInfoTable(ElementName.FOLD, 0x33333331);
	}

	@Override
	protected ElementInfo getTheElementInfo()
	{
		return super.getTheElementInfo().updateReplace(elemInfoTable);
	}

	/**
	 * Constructor for JDFAutoFoldingIntent
	 * 
	 * @param myOwnerDocument
	 * @param qualifiedName
	 */
	protected JDFAutoFoldingIntent(CoreDocumentImpl myOwnerDocument, String qualifiedName)
	{
		super(myOwnerDocument, qualifiedName);
	}

	/**
	 * Constructor for JDFAutoFoldingIntent
	 * 
	 * @param myOwnerDocument
	 * @param myNamespaceURI
	 * @param qualifiedName
	 */
	protected JDFAutoFoldingIntent(CoreDocumentImpl myOwnerDocument, String myNamespaceURI, String qualifiedName)
	{
		super(myOwnerDocument, myNamespaceURI, qualifiedName);
	}

	/**
	 * Constructor for JDFAutoFoldingIntent
	 * 
	 * @param myOwnerDocument
	 * @param myNamespaceURI
	 * @param qualifiedName
	 * @param myLocalName
	 */
	protected JDFAutoFoldingIntent(CoreDocumentImpl myOwnerDocument, String myNamespaceURI, String qualifiedName, String myLocalName)
	{
		super(myOwnerDocument, myNamespaceURI, qualifiedName, myLocalName);
	}

	/**
	 * @return the string representation
	 */
	@Override
	public String toString()
	{
		return " JDFAutoFoldingIntent[  --> " + super.toString() + " ]";
	}

	/*
	 * ************************************************************************ Attribute getter / setter ************************************************************************
	 */

	/*
	 * --------------------------------------------------------------------- Methods for Attribute Folds ---------------------------------------------------------------------
	 */
	/**
	 * (36) set attribute Folds
	 * 
	 * @param value the value to set the attribute to
	 */
	public void setFolds(JDFXYPair value)
	{
		setAttribute(AttributeName.FOLDS, value, null);
	}

	/**
	 * (20) get JDFXYPair attribute Folds
	 * 
	 * @return JDFXYPair the value of the attribute, null if a the attribute value is not a valid to create a JDFXYPair
	 */
	public JDFXYPair getFolds()
	{
		final String strAttrName = getAttribute(AttributeName.FOLDS, null, null);
		final JDFXYPair nPlaceHolder = JDFXYPair.createXYPair(strAttrName);
		return nPlaceHolder;
	}

	/*
	 * *********************************************************************** Element getter / setter ***********************************************************************
	 */

	/**
	 * (24) const get element FoldingCatalog
	 * 
	 * @return JDFNameSpan the element
	 */
	public JDFNameSpan getFoldingCatalog()
	{
		return (JDFNameSpan) getElement(ElementName.FOLDINGCATALOG, null, 0);
	}

	/**
	 * (25) getCreateFoldingCatalog
	 * 
	 * @return JDFNameSpan the element
	 */
	public JDFNameSpan getCreateFoldingCatalog()
	{
		return (JDFNameSpan) getCreateElement(ElementName.FOLDINGCATALOG, null, 0);
	}

	/**
	 * (29) append element FoldingCatalog
	 * 
	 * @return JDFNameSpan the element
	 * @throws JDFException if the element already exists
	 */
	public JDFNameSpan appendFoldingCatalog() throws JDFException
	{
		return (JDFNameSpan) appendElementN(ElementName.FOLDINGCATALOG, 1, null);
	}

	/**
	 * (26) getCreateFold
	 * 
	 * @param iSkip number of elements to skip
	 * @return JDFFold the element
	 */
	public JDFFold getCreateFold(int iSkip)
	{
		return (JDFFold) getCreateElement(ElementName.FOLD, null, iSkip);
	}

	/**
	 * (27) const get element Fold
	 * 
	 * @param iSkip number of elements to skip
	 * @return JDFFold the element default is getFold(0)
	 */
	public JDFFold getFold(int iSkip)
	{
		return (JDFFold) getElement(ElementName.FOLD, null, iSkip);
	}

	/**
	 * Get all Fold from the current element
	 * 
	 * @return Collection<JDFFold>, null if none are available
	 */
	public Collection<JDFFold> getAllFold()
	{
		return getChildrenByClass(JDFFold.class, false, 0);
	}

	/**
	 * (30) append element Fold
	 * 
	 * @return JDFFold the element
	 */
	public JDFFold appendFold()
	{
		return (JDFFold) appendElement(ElementName.FOLD, null);
	}

}// end namespace JDF
