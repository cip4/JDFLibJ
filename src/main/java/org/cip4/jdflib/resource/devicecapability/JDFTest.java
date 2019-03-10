/*
 *
 * The CIP4 Software License, Version 1.0
 *
 *
 * Copyright (c) 2001-2019 The International Cooperation for the Integration of Processes in Prepress, Press and Postpress (CIP4). All rights reserved.
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

/**
 * @author Elena Skobchenko
 *
 *         JDFTest.java
 *
 */
package org.cip4.jdflib.resource.devicecapability;

import org.apache.xerces.dom.CoreDocumentImpl;
import org.cip4.jdflib.core.AtrInfoTable;
import org.cip4.jdflib.core.AttributeInfo;
import org.cip4.jdflib.core.AttributeName;
import org.cip4.jdflib.core.KElement;
import org.cip4.jdflib.core.VString;
import org.cip4.jdflib.datatypes.JDFAttributeMap;

public class JDFTest extends JDFNodeTerm
{
	private static final long serialVersionUID = 1L;

	private static AtrInfoTable[] atrInfoTable = new AtrInfoTable[2];
	static
	{
		atrInfoTable[0] = new AtrInfoTable(AttributeName.ID, 0x22222222, AttributeInfo.EnumAttributeType.ID, null, null);
		atrInfoTable[1] = new AtrInfoTable(AttributeName.CONTEXT, 0x33333333, AttributeInfo.EnumAttributeType.XPath, null, null);
	}

	@Override
	protected AttributeInfo getTheAttributeInfo()
	{
		return super.getTheAttributeInfo().updateReplace(atrInfoTable);
	}

	/**
	 * Constructor for JDFTest
	 *
	 * @param myOwnerDocument
	 * @param qualifiedName
	 */
	public JDFTest(final CoreDocumentImpl myOwnerDocument, final String qualifiedName)
	{
		super(myOwnerDocument, qualifiedName);
	}

	/**
	 * Constructor for JDFTest
	 *
	 * @param myOwnerDocument
	 * @param myNamespaceURI
	 * @param qualifiedName
	 */
	public JDFTest(final CoreDocumentImpl myOwnerDocument, final String myNamespaceURI, final String qualifiedName)
	{
		super(myOwnerDocument, myNamespaceURI, qualifiedName);
	}

	/**
	 * Constructor for JDFTest
	 *
	 * @param myOwnerDocument
	 * @param myNamespaceURI
	 * @param qualifiedName
	 * @param myLocalName
	 */
	public JDFTest(final CoreDocumentImpl myOwnerDocument, final String myNamespaceURI, final String qualifiedName, final String myLocalName)
	{
		super(myOwnerDocument, myNamespaceURI, qualifiedName, myLocalName);
	}

	/**
	 * @see org.cip4.jdflib.core.JDFElement#toString()
	 * @return
	 */
	@Override
	public String toString()
	{
		return " JDFTest[  --> " + super.toString() + " ]";
	}

	// //////////////////////////////////////////////////////////////////////

	/**
	 * Evaluates the boolean expression (child Term element) if it fits the attribute map 'm'
	 *
	 * @param m key-value pair attribute map
	 * @return boolean - true, if the boolean expression (child Term element) evaluates to true
	 */
	@Override
	public boolean fitsMap(final JDFAttributeMap m)
	{
		final JDFTerm t = getTerm();
		return t == null ? false : t.fitsMap(m);
	}

	/**
	 * Evaluates the boolean expression (child Term element) if it fits the JDFNode 'jdf' a value of true corresponds to a failed test, i.e. the test describes INVALID states for the jdf
	 *
	 * @param jdf JDFNode to test to know if the Device can accept it
	 * @param reportRoot the report to generate. Set to <code>null</code> if no report is requested.
	 * @return boolean - true, if boolean expression (child Term element) evaluates to �true�
	 */
	@Override
	public boolean fitsJDF(final KElement jdf, final KElement reportRoot)
	{
		KElement reportRootLocal = reportRoot;

		if (reportRootLocal != null)
			reportRootLocal = reportRootLocal.appendElement("TestReport");
		final JDFTerm t = getTerm();
		if (t == null)
			return true; // no term --> assume it is a non test; i.e. ok

		boolean checkContext = true;
		if (hasAttribute(AttributeName.CONTEXT))
		{
			checkContext = !jdf.matchesPath(getContext(), true);
		}
		if (checkContext && !t.fitsContext(jdf))
			return true;
		final boolean b = t.fitsJDF(jdf, reportRootLocal);
		if (reportRootLocal != null)
			reportRootLocal.setAttribute("Value", b, null);
		return b;
	}

	/**
	 * gets the term from a test
	 *
	 * @return JDFTerm the term that defines this test, <code>null</code> if there is no term
	 */
	public JDFTerm getTerm()
	{
		return getTerm(null, 0);
	}

	/**
	 * @see org.cip4.jdflib.core.KElement#init()
	 * @return
	 */
	@Override
	public boolean init()
	{
		appendAnchor(null);
		return super.init();
	}

	/**
	 * getIDPrefix
	 *
	 * @return the default ID prefix of non-overwritten JDF elements
	 */
	@Override
	protected String getIDPrefix()
	{
		return "T";
	}

	/**
	 * @see org.cip4.jdflib.core.JDFElement#getInvalidElements(org.cip4.jdflib.core.KElement.EnumValidationLevel, boolean, int)
	 * @param level
	 * @param bIgnorePrivate
	 * @param nMax
	 * @return
	 */
	@Override
	public VString getInvalidElements(final EnumValidationLevel level, final boolean bIgnorePrivate, final int nMax)
	{
		final VString v = super.getInvalidElements(level, bIgnorePrivate, nMax);
		if (v.size() >= nMax)
			return v;

		v.appendUnique(getInvalidTerms(1));

		return v;
	}

	// ///////////////////////////////////////////////////////

	/**
	 * @see org.cip4.jdflib.core.KElement#getMissingElements(int)
	 * @param nMax
	 * @return
	 */
	@Override
	public VString getMissingElements(final int nMax)
	{
		final VString v = super.getMissingElements(nMax);
		if (v.size() >= nMax)
			return v;

		v.appendUnique(getMissingTerms(1));
		return v;
	}

	// ///////////////////////////////////////////////////////
	/**
	 * check whether the boolean logic defined by a Test and a test's subelements makes sense in the context of the tested element jdf
	 */
	@Override
	public boolean fitsContext(final KElement testElement)
	{
		if (hasAttribute(AttributeName.CONTEXT))
		{
			return testElement.matchesPath(getContext(), true);
		}
		return super.fitsContext(testElement);
	}

	// ////////////////////////////////////////////////////////////////

	/**
	 * get attribute <code>Context</code> of this Test element
	 *
	 * @return String - the value of the <code>Context</code> attribute of this test
	 */
	public String getContext()
	{
		return getAttribute(AttributeName.CONTEXT, null, null);
	}

	// ////////////////////////////////////////////////////////////////

	/**
	 * set attribute <code>Context</code> of this Test element
	 *
	 * @param context the value of the <code>Context</code> attribute of this test
	 */
	public void setContext(final String context)
	{
		setAttribute(AttributeName.CONTEXT, context);
	}
}
