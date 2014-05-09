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

import java.util.Collection;
import java.util.Vector;

import org.apache.xerces.dom.CoreDocumentImpl;
import org.cip4.jdflib.core.ElemInfoTable;
import org.cip4.jdflib.core.ElementInfo;
import org.cip4.jdflib.core.ElementName;
import org.cip4.jdflib.core.JDFException;
import org.cip4.jdflib.core.VElement;
import org.cip4.jdflib.resource.JDFResource;
import org.cip4.jdflib.resource.intent.JDFIntentResource;
import org.cip4.jdflib.span.JDFNameSpan;
import org.cip4.jdflib.span.JDFSpanPrintPreference;

/**
*****************************************************************************
class JDFAutoProductionIntent : public JDFIntentResource

*****************************************************************************
*/

public abstract class JDFAutoProductionIntent extends JDFIntentResource
{

	private static final long serialVersionUID = 1L;

	private static ElemInfoTable[] elemInfoTable = new ElemInfoTable[3];
	static
	{
		elemInfoTable[0] = new ElemInfoTable(ElementName.PRINTPREFERENCE, 0x66666666);
		elemInfoTable[1] = new ElemInfoTable(ElementName.PRINTPROCESS, 0x66666666);
		elemInfoTable[2] = new ElemInfoTable(ElementName.RESOURCE, 0x33333333);
	}

	@Override
	protected ElementInfo getTheElementInfo()
	{
		return super.getTheElementInfo().updateReplace(elemInfoTable);
	}

	/**
	 * Constructor for JDFAutoProductionIntent
	 * @param myOwnerDocument
	 * @param qualifiedName
	 */
	protected JDFAutoProductionIntent(CoreDocumentImpl myOwnerDocument, String qualifiedName)
	{
		super(myOwnerDocument, qualifiedName);
	}

	/**
	 * Constructor for JDFAutoProductionIntent
	 * @param myOwnerDocument
	 * @param myNamespaceURI
	 * @param qualifiedName
	 */
	protected JDFAutoProductionIntent(CoreDocumentImpl myOwnerDocument, String myNamespaceURI, String qualifiedName)
	{
		super(myOwnerDocument, myNamespaceURI, qualifiedName);
	}

	/**
	 * Constructor for JDFAutoProductionIntent
	 * @param myOwnerDocument
	 * @param myNamespaceURI
	 * @param qualifiedName
	 * @param myLocalName
	 */
	protected JDFAutoProductionIntent(CoreDocumentImpl myOwnerDocument, String myNamespaceURI, String qualifiedName, String myLocalName)
	{
		super(myOwnerDocument, myNamespaceURI, qualifiedName, myLocalName);
	}

	/**
	 * @return  the string representation
	 */
	@Override
	public String toString()
	{
		return " JDFAutoProductionIntent[  --> " + super.toString() + " ]";
	}

	/* ***********************************************************************
	 * Element getter / setter
	 * ***********************************************************************
	 */

	/**
	 * (24) const get element PrintPreference
	 * @return JDFSpanPrintPreference the element
	 */
	public JDFSpanPrintPreference getPrintPreference()
	{
		return (JDFSpanPrintPreference) getElement(ElementName.PRINTPREFERENCE, null, 0);
	}

	/** (25) getCreatePrintPreference
	 * 
	 * @return JDFSpanPrintPreference the element
	 */
	public JDFSpanPrintPreference getCreatePrintPreference()
	{
		return (JDFSpanPrintPreference) getCreateElement_KElement(ElementName.PRINTPREFERENCE, null, 0);
	}

	/**
	 * (29) append element PrintPreference
	 * @return JDFSpanPrintPreference the element
	 * @throws JDFException if the element already exists
	 */
	public JDFSpanPrintPreference appendPrintPreference() throws JDFException
	{
		return (JDFSpanPrintPreference) appendElementN(ElementName.PRINTPREFERENCE, 1, null);
	}

	/**
	 * (24) const get element PrintProcess
	 * @return JDFNameSpan the element
	 */
	public JDFNameSpan getPrintProcess()
	{
		return (JDFNameSpan) getElement(ElementName.PRINTPROCESS, null, 0);
	}

	/** (25) getCreatePrintProcess
	 * 
	 * @return JDFNameSpan the element
	 */
	public JDFNameSpan getCreatePrintProcess()
	{
		return (JDFNameSpan) getCreateElement_KElement(ElementName.PRINTPROCESS, null, 0);
	}

	/**
	 * (29) append element PrintProcess
	 * @return JDFNameSpan the element
	 * @throws JDFException if the element already exists
	 */
	public JDFNameSpan appendPrintProcess() throws JDFException
	{
		return (JDFNameSpan) appendElementN(ElementName.PRINTPROCESS, 1, null);
	}

	/** (26) getCreateResource
	 * 
	 * @param iSkip number of elements to skip
	 * @return JDFResource the element
	 */
	public JDFResource getCreateResource(int iSkip)
	{
		return (JDFResource) getCreateElement_KElement(ElementName.RESOURCE, null, iSkip);
	}

	/**
	 * (27) const get element Resource
	 * @param iSkip number of elements to skip
	 * @return JDFResource the element
	 * default is getResource(0)     */
	public JDFResource getResource(int iSkip)
	{
		return (JDFResource) getElement(ElementName.RESOURCE, null, iSkip);
	}

	/**
	 * Get all Resource from the current element
	 * 
	 * @return Collection<JDFResource>, null if none are available
	 */
	public Collection<JDFResource> getAllResource()
	{
		final VElement vc = getChildElementVector(ElementName.RESOURCE, null);
		if (vc == null || vc.size() == 0)
		{
			return null;
		}

		final Vector<JDFResource> v = new Vector<JDFResource>();
		for (int i = 0; i < vc.size(); i++)
		{
			v.add((JDFResource) vc.get(i));
		}

		return v;
	}

	/**
	 * (30) append element Resource
	 * @return JDFResource the element
	 */
	public JDFResource appendResource()
	{
		return (JDFResource) appendElement(ElementName.RESOURCE, null);
	}

	/**
	  * (31) create inter-resource link to refTarget
	  * @param refTarget the element that is referenced
	  */
	public void refResource(JDFResource refTarget)
	{
		refElement(refTarget);
	}

}// end namespace JDF
