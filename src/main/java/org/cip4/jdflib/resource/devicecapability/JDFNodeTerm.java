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
package org.cip4.jdflib.resource.devicecapability;

import org.apache.xerces.dom.CoreDocumentImpl;
import org.cip4.jdflib.core.ElemInfoTable;
import org.cip4.jdflib.core.ElementInfo;
import org.cip4.jdflib.core.ElementName;
import org.cip4.jdflib.core.KElement;
import org.cip4.jdflib.core.VElement;
import org.cip4.jdflib.core.VString;
import org.cip4.jdflib.resource.JDFResource;
import org.w3c.dom.DOMException;
import org.w3c.dom.Node;

/**
 * class to collect getters and setters for term intermediate nodes
 *
 * @author prosirai
 */
abstract public class JDFNodeTerm extends JDFTerm
{
	private static final long serialVersionUID = -4750863965825892402L;

	private static ElemInfoTable[] elemInfoTable = new ElemInfoTable[19];
	static
	{
		elemInfoTable[0] = new ElemInfoTable(ElementName.AND, 0x33333311);
		elemInfoTable[1] = new ElemInfoTable(ElementName.NOT, 0x33333311);
		elemInfoTable[2] = new ElemInfoTable(ElementName.OR, 0x33333311);
		elemInfoTable[3] = new ElemInfoTable(ElementName.XOR, 0x33333311);
		elemInfoTable[4] = new ElemInfoTable(ElementName.BOOLEANEVALUATION, 0x33333311);
		elemInfoTable[5] = new ElemInfoTable(ElementName.DATETIMEEVALUATION, 0x33333311);
		elemInfoTable[6] = new ElemInfoTable(ElementName.DURATIONEVALUATION, 0x33333311);
		elemInfoTable[7] = new ElemInfoTable(ElementName.ENUMERATIONEVALUATION, 0x33333311);
		elemInfoTable[8] = new ElemInfoTable(ElementName.INTEGEREVALUATION, 0x33333311);
		elemInfoTable[9] = new ElemInfoTable(ElementName.ISPRESENTEVALUATION, 0x33333311);
		elemInfoTable[10] = new ElemInfoTable(ElementName.MATRIXEVALUATION, 0x33333311);
		elemInfoTable[11] = new ElemInfoTable(ElementName.NAMEEVALUATION, 0x33333311);
		elemInfoTable[12] = new ElemInfoTable(ElementName.NUMBEREVALUATION, 0x33333311);
		elemInfoTable[13] = new ElemInfoTable(ElementName.PDFPATHEVALUATION, 0x33333311);
		elemInfoTable[14] = new ElemInfoTable(ElementName.RECTANGLEEVALUATION, 0x33333311);
		elemInfoTable[15] = new ElemInfoTable(ElementName.SHAPEEVALUATION, 0x33333311);
		elemInfoTable[16] = new ElemInfoTable(ElementName.STRINGEVALUATION, 0x33333311);
		elemInfoTable[17] = new ElemInfoTable(ElementName.XYPAIREVALUATION, 0x33333311);
		elemInfoTable[18] = new ElemInfoTable(ElementName.TESTREF, 0x33333311);
	}

	@Override
	protected ElementInfo getTheElementInfo()
	{
		return new ElementInfo(super.getTheElementInfo(), elemInfoTable);
	}

	public JDFNodeTerm(final CoreDocumentImpl myOwnerDocument, final String qualifiedName) throws DOMException
	{
		super(myOwnerDocument, qualifiedName);
	}

	public JDFNodeTerm(final CoreDocumentImpl myOwnerDocument, final String myNamespaceURI, final String qualifiedName) throws DOMException
	{
		super(myOwnerDocument, myNamespaceURI, qualifiedName);
	}

	public JDFNodeTerm(final CoreDocumentImpl myOwnerDocument, final String myNamespaceURI, final String qualifiedName, final String myLocalName) throws DOMException
	{
		super(myOwnerDocument, myNamespaceURI, qualifiedName, myLocalName);
	}

	////////////////////////////////////////////////////////////////////////////
	// ////

	/**
	 * get the iSkip'th Term of type term, do not create it if it does not exist
	 *
	 * @param term
	 *              type of term to append
	 * @param iSkip
	 *              number of terms to skip, 0 is the first
	 * @return JDFTerm - the requested term, <code>null</code> if none exists
	 */
	public JDFTerm getTerm(final EnumTerm term, final int iSkip)
	{
		if (term != null)
		{
			return (JDFTerm) getElement(term.name(), null, iSkip);
		}
		Node e = getFirstChild();
		int n = 0;
		while (e != null)
		{
			if (e instanceof JDFTerm)
			{
				if (++n > iSkip)
				{
					return (JDFTerm) e;
				}
			}
			e = e.getNextSibling();
		}
		return null;

	}

	/**
	 * get the iSkip'th Term of type <code>term</code>, create it if it does not
	 * exist
	 *
	 * @param term
	 *              type of term to append
	 * @param iSkip
	 *              number of terms to skip, 0 is the first
	 * @return JDFTerm - the requested term
	 */
	public JDFTerm getCreateTerm(final EnumTerm term, final int iSkip)
	{
		return (JDFTerm) getCreateElement(term.name(), null, iSkip);
	}

	/**
	 * append a Term as defined by <code>term</code>
	 *
	 * @param term
	 *             type of term to append
	 * @return JDFTerm the appended term
	 */
	public JDFTerm appendTerm(final EnumTerm term)
	{
		return (JDFTerm) appendElement(term.name(), null);
	}

	/**
	 * check whether the boolean logic defined by a Test and a test's
	 * subelements make sense in the context of the tested element jdf
	 */
	@Override
	public boolean fitsContext(final KElement testElement)
	{
		// we only want the leaves as of now
		if (testElement instanceof JDFResource)
		{
			final JDFResource r = (JDFResource) testElement;
			if (!r.isLeaf())
			{
				return false;
			}
		}
		final VElement v = getTermVector(null);
		final int siz = v.size();
		for (int i = 0; i < siz; i++)
		{
			final JDFTerm t = (JDFTerm) v.elementAt(i);
			if (!t.fitsContext(testElement)) // one bad context spoils the
			{
				// barrell
				return false;
			}
		}
		return siz > 0; // if no subelements, then no context
	}

	/**
	 * gets a vector of all terms
	 *
	 * @deprecated use getTermVector(null)
	 * @return
	 */
	@Deprecated
	public VElement getTermVector()
	{
		return getTermVector(null);
	}

	/**
	 * gets a vector of all terms
	 *
	 * @return VElement - vector of JDFTerm
	 */
	public VElement getTermVector(final EnumTerm term)
	{
		if (term != null)
		{
			return getChildElementVector(term.name(), null, null, true, 0, false);
		}

		final VElement v = new VElement();
		KElement e = getFirstChildElement();
		while (e != null)
		{
			if (e instanceof JDFTerm)
			{
				v.add(e);
			}
			e = e.getNextSiblingElement();
		}
		return v; // if no subelements, then no context
	}

	/**
	 * gets the iSkip'th term
	 *
	 * @param iSkip
	 *              the number of terms tos skip
	 * @return JDFTerm - the iSkip'th Term
	 * @deprecated
	 */
	@Deprecated
	public JDFTerm getTerm(final int iSkip)
	{
		return getTerm(null, iSkip);
	}

	// //////////////////////////////////////////////////

	protected VString getInvalidTerms(final int iMax)
	{
		final VElement v = getTermVector(null);
		final int vSize = v.size();
		final VString v2 = new VString();
		if (vSize > iMax) // no more than iMax
		{
			for (int i = 0; i < vSize; i++)
			{
				final String strName = v.item(i).getLocalName();
				v2.appendUnique(strName);
			}
		}
		return v2;
	}

	// //////////////////////////////////////////////////

	protected VString getMissingTerms(final int iMin)
	{
		final VElement v = getTermVector(null);
		VString v2 = null;
		if (v.size() < iMin)
		{
			v2 = new VString();
			v2.add("Term");
		}
		return v2;
	}

	// //////////////////////////////////////////////////

}
