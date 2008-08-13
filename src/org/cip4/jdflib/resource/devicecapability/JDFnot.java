/*
 *
 * The CIP4 Software License, Version 1.0
 *
 *
 * Copyright (c) 2001-2006 The International Cooperation for the Integration of 
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

/**
 * @author Elena Skobchenko
 *
 * JDFnot.java
 *
 */
package org.cip4.jdflib.resource.devicecapability;

import org.apache.xerces.dom.CoreDocumentImpl;
import org.cip4.jdflib.core.KElement;
import org.cip4.jdflib.core.VElement;
import org.cip4.jdflib.core.VString;
import org.cip4.jdflib.datatypes.JDFAttributeMap;

public class JDFnot extends JDFNodeTerm
{
	private static final long serialVersionUID = 1L;

	/**
	 * Constructor for JDFnot
	 * 
	 * @param myOwnerDocument
	 * @param qualifiedName
	 */
	public JDFnot(CoreDocumentImpl myOwnerDocument, String qualifiedName)
	{
		super(myOwnerDocument, qualifiedName);
	}

	/**
	 * Constructor for JDFnot
	 * 
	 * @param myOwnerDocument
	 * @param myNamespaceURI
	 * @param qualifiedName
	 */
	public JDFnot(CoreDocumentImpl myOwnerDocument, String myNamespaceURI,
			String qualifiedName)
	{
		super(myOwnerDocument, myNamespaceURI, qualifiedName);
	}

	/**
	 * Constructor for JDFnot
	 * 
	 * @param myOwnerDocument
	 * @param myNamespaceURI
	 * @param qualifiedName
	 * @param myLocalName
	 */
	public JDFnot(CoreDocumentImpl myOwnerDocument, String myNamespaceURI,
			String qualifiedName, String myLocalName)
	{
		super(myOwnerDocument, myNamespaceURI, qualifiedName, myLocalName);
	}

	public String toString()
	{
		return " JDFnot[  --> " + super.toString() + " ]";
	}

	/**
	 * Inverts the boolean state of a Term element (and, or, xor, not,
	 * Evaluation, TestRef).<br>
	 * To determine the state of Term tests Evaluations that “not” consists of,
	 * it checks whether attribute map 'm' has a key, specified by
	 * Evaluation/BasicPreflightTest/@Name. If 'm' has such key, it checks
	 * whether its value fits the testlists specified for matching the
	 * Evaluation (uses FitsValue(value))
	 * 
	 * @param m
	 *            key-value pair attribute map
	 * @return boolean - true, if boolean “not” expression evaluates to “true”
	 */
	public boolean fitsMap(JDFAttributeMap m)
	{
		JDFTerm t = getTerm(null, 0);
		if (t == null)
			return false;
		return !t.fitsMap(m);
	}

	/**
	 * Inverts the boolean state of a Term child element (and, or, xor, not,
	 * Evaluation, TestRef)
	 * 
	 * @param jdf
	 *            JDFNode we test to know if the Device can accept it
	 * @param reportRoot
	 *            the report to generate. Set to <code>null</code> if no report
	 *            is requested.
	 * @return boolean - true, if boolean “not” expression evaluates to “true”
	 */
	public boolean fitsJDF(KElement jdf, KElement reportRoot)
	{
		VElement v = getTermVector(null);
		int siz = v.size();
		boolean b = false;
		if (reportRoot != null)
			reportRoot = reportRoot.appendElement("not");
		int count = 0;
		for (int i = 0; i < siz; i++)
		{
			final JDFTerm t = (JDFTerm) v.elementAt(i);
			b = !t.fitsJDF(jdf, reportRoot);
			count++;
			if (reportRoot != null)
				reportRoot.setAttribute("Value", b, null);
		}
		if (reportRoot != null && count != 1)
			reportRoot.setAttribute("SyntaxWarning",
					"Warning: not element with more than one term, count="
							+ String.valueOf(count));

		return b;
	}

	// //////////////////////////////////////////////////

	public VString getInvalidElements(EnumValidationLevel level,
			boolean bIgnorePrivate, int nMax)
	{
		if (bIgnorePrivate)
			bIgnorePrivate = false; // dummy to fool compiler
		VString v = super.getInvalidElements(level, bIgnorePrivate, nMax);
		if (v.size() >= nMax)
			return v;

		v.appendUnique(getInvalidTerms(1));
		return v;
	}

	// ///////////////////////////////////////////////////////

	public VString getMissingElements(int nMax)
	{
		VString v = super.getMissingElements(nMax);
		if (v.size() >= nMax)
			return v;

		v.appendUnique(getMissingTerms(1));
		return v;
	}

	// ///////////////////////////////////////////////////////

}
