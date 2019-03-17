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

/**
 *
 * Copyright (c) 2001 Heidelberger Druckmaschinen AG, All Rights Reserved.
 *
 * JDFRunList.java
 *
 * Last changes
 *
 * 2002-07-02 JG - added ConsistentPartIDKeys()
 */
package org.cip4.jdflib.resource.process;

import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Vector;

import org.apache.xerces.dom.CoreDocumentImpl;
import org.cip4.jdflib.auto.JDFAutoRunList;
import org.cip4.jdflib.core.AtrInfoTable;
import org.cip4.jdflib.core.AttributeInfo;
import org.cip4.jdflib.core.AttributeName;
import org.cip4.jdflib.core.ElementName;
import org.cip4.jdflib.core.JDFConstants;
import org.cip4.jdflib.core.JDFException;
import org.cip4.jdflib.core.KElement;
import org.cip4.jdflib.core.VElement;
import org.cip4.jdflib.datatypes.JDFIntegerRange;
import org.cip4.jdflib.datatypes.JDFIntegerRangeList;
import org.cip4.jdflib.datatypes.VJDFAttributeMap;
import org.cip4.jdflib.resource.JDFPageList;
import org.cip4.jdflib.resource.JDFResource;
import org.cip4.jdflib.util.StringUtil;
import org.cip4.jdflib.util.UrlUtil;
import org.w3c.dom.DOMException;

/**
 * Wrapper around a JDF RunList
 */
public class JDFRunList extends JDFAutoRunList
{
	private static final long serialVersionUID = 1L;

	private static AtrInfoTable[] atrInfoTable = new AtrInfoTable[1];
	static
	{
		// need to add the default partition key doccopies -
		atrInfoTable[0] = new AtrInfoTable(AttributeName.DOCCOPIES, 0x33333333, AttributeInfo.EnumAttributeType.integer, null, "1");
	}

	@Override
	protected AttributeInfo getTheAttributeInfo()
	{
		return super.getTheAttributeInfo().updateReplace(atrInfoTable);
	}

	/**
	 * Constructor for JDFRunList
	 *
	 * @param myOwnerDocument
	 * @param qualifiedName
	 * @throws DOMException
	 *
	 */
	public JDFRunList(final CoreDocumentImpl myOwnerDocument, final String qualifiedName) throws DOMException
	{
		super(myOwnerDocument, qualifiedName);
	}

	/**
	 * Constructor for JDFRunList
	 *
	 * @param myOwnerDocument
	 * @param myNamespaceURI
	 *
	 * @param qualifiedName
	 * @throws DOMException
	 */
	public JDFRunList(final CoreDocumentImpl myOwnerDocument, final String myNamespaceURI, final String qualifiedName) throws DOMException
	{
		super(myOwnerDocument, myNamespaceURI, qualifiedName);
	}

	/**
	 * Constructor for JDFRunList
	 *
	 * @param myOwnerDocument
	 * @param myNamespaceURI
	 * @param qualifiedName
	 * @param myLocalName
	 *
	 * @throws DOMException
	 */
	public JDFRunList(final CoreDocumentImpl myOwnerDocument, final String myNamespaceURI, final String qualifiedName, final String myLocalName) throws DOMException
	{
		super(myOwnerDocument, myNamespaceURI, qualifiedName, myLocalName);
	}

	// **************************************** Methods
	// *********************************************
	/**
	 * toString
	 *
	 * @return String
	 */
	@Override
	public String toString()
	{
		return "JDFRunList[ --> " + super.toString() + " ]";
	}

	/**
	 * addRun
	 *
	 * @param fileName
	 * @deprecated
	 *
	 * @return JDFRunList
	 */
	@Deprecated
	public JDFRunList addRun(final String fileName)
	{
		return addRun(fileName, 0, -1);
	}

	/**
	 * addRun
	 *
	 * @param fileName
	 * @param first
	 * @deprecated
	 *
	 * @return JDFRunList
	 */
	@Deprecated
	public JDFRunList addRun(final String fileName, final int first)
	{
		return addRun(fileName, first, -1);
	}

	/**
	 * addRun
	 *
	 * @param fileName
	 * @param first
	 * @param last
	 *
	 * @return JDFRunList
	 */
	public JDFRunList addRun(final String fileName, final int first, final int last)
	{
		final String runID = "Run" + uniqueID(0);
		final JDFRunList r = (JDFRunList) addPartition(JDFResource.EnumPartIDKey.Run, runID); // JDFRun to RunList

		final JDFIntegerRangeList irl = new JDFIntegerRangeList();
		irl.append(first, last);
		r.setPages(irl);
		final JDFLayoutElement loe = (JDFLayoutElement) r.appendElement(ElementName.LAYOUTELEMENT, null);
		loe.setMimeURL(fileName);
		r.updateNPage(first, last, true);
		return r;
	}

	/**
	 * add the number of pages between first and last to NPage and its parents
	 *
	 * @param first
	 * @param last
	 * @param bLeaf if true, we are in a leaf and therefore start from scratch - else we need to increment
	 */
	private void updateNPage(final int first, final int last, final boolean bLeaf)
	{
		if (first < 0 || last < 0)
			return;

		int npage;
		if (bLeaf)
		{
			npage = 0;
		}
		else
		{
			final String s = getAttribute_KElement(AttributeName.NPAGE, null, null);
			npage = StringUtil.parseInt(s, 0);
		}

		final int n = Math.abs(last - first);
		npage += n + 1;
		setNPage(npage);
		final JDFRunList parent = (JDFRunList) getParentPartition();
		if (parent != null)
			parent.updateNPage(first, last, false);
	}

	/**
	 * addPDF
	 *
	 * @param fileName
	 * @deprecated
	 *
	 * @return JDFRunList
	 */
	@Deprecated
	public JDFRunList addPDF(final String fileName)
	{
		return addPDF(fileName, 0, -1);
	}

	/**
	 * addPDF
	 *
	 * @param fileName
	 * @param first
	 * @deprecated
	 *
	 * @return JDFRunList
	 */
	@Deprecated
	public JDFRunList addPDF(final String fileName, final int first)
	{
		return addPDF(fileName, first, -1);
	}

	/**
	 * addPDF add a pdf file to this RunList
	 *
	 * @param fileName the URL (!) of the file
	 * @param first 0 based first page in the file
	 * @param last 0 based last page in the file
	 *
	 * @return JDFRunList
	 */
	public JDFRunList addPDF(final String fileName, final int first, final int last)
	{
		final JDFRunList r = addRun(fileName, first, last);
		final JDFFileSpec fs = r.getLayoutElement().getFileSpec();
		fs.setMimeType(JDFConstants.MIME_PDF);
		return r;
	}

	/**
	 * addSepRun
	 *
	 * @param fileNames
	 * @param sepNames
	 * @deprecated
	 *
	 * @return JDFRunList
	 */
	@SuppressWarnings("unchecked")
	@Deprecated
	public JDFRunList addSepRun(final Vector fileNames, final Vector sepNames)
	{
		return addSepRun(fileNames, sepNames, 0, 1, true);
	}

	/**
	 * addSepRun
	 *
	 * @param fileNames
	 * @param sepNames
	 * @param first
	 * @deprecated
	 *
	 * @return JDFRunList
	 */
	@SuppressWarnings("unchecked")
	@Deprecated
	public JDFRunList addSepRun(final Vector fileNames, final Vector sepNames, final int first)
	{
		return addSepRun(fileNames, sepNames, first, 1, true);
	}

	/**
	 * addSepRun
	 *
	 * @param fileNames
	 * @param sepNames
	 * @param first
	 * @param n
	 *
	 * @deprecated
	 *
	 * @return JDFRunList
	 */
	@SuppressWarnings("unchecked")
	@Deprecated
	public JDFRunList addSepRun(final Vector fileNames, final Vector sepNames, final int first, final int n)
	{
		return addSepRun(fileNames, sepNames, first, n, true);
	}

	/**
	 * addSepRun
	 *
	 * @param fileNames
	 * @param sepNames
	 * @param first
	 * @param pageMajor
	 * @deprecated
	 *
	 * @return JDFRunList
	 */
	@SuppressWarnings("unchecked")
	@Deprecated
	public JDFRunList addSepRun(final Vector fileNames, final Vector sepNames, final int first, final boolean pageMajor)
	{
		return addSepRun(fileNames, sepNames, first, 1, pageMajor);
	}

	/**
	 * addSepRun
	 *
	 * @param fileNames
	 * @param sepNames
	 * @param pageMajor
	 *
	 * @deprecated
	 *
	 * @return JDFRunList
	 */
	@SuppressWarnings("unchecked")
	@Deprecated
	public JDFRunList addSepRun(final Vector fileNames, final Vector sepNames, final boolean pageMajor)
	{
		return addSepRun(fileNames, sepNames, 0, 1, pageMajor);
	}

	/**
	 * add a run separation
	 *
	 * @param fileNames vector of file names for the URL attribute of the FileSpec in the LayoutElement
	 * @param sepNames parallel vector of separation names.
	 * @param first index of the first page in the file - Sets the RunList FirstPage attribute
	 * @param n the number of logical pages in this run
	 * @param pageMajor if true, separations are ordered as page Major, i.e CMYKCMYK<br>
	 *            if false, ordering is CCMMYYKK
	 *
	 * @return JDFRunList
	 */
	public JDFRunList addSepRun(final Vector fileNames, final Vector sepNames, final int first, final int n, final boolean pageMajor)
	{
		final JDFRunList r = (JDFRunList) addPartition(JDFResource.EnumPartIDKey.Run, "Run" + uniqueID(0));
		final int siz = fileNames.size();
		r.setNPage(n);
		r.setIsPage(true);

		for (int i = 0; i < sepNames.size(); i++)
		{
			final JDFRunList rs = (JDFRunList) r.addPartition(JDFResource.EnumPartIDKey.Separation, sepNames.elementAt(i).toString());
			final JDFLayoutElement lo = rs.appendLayoutElement();
			lo.setMimeURL(((String) fileNames.elementAt(Math.min(i, siz - 1))));
			rs.setIsPage(false);
			if (fileNames.size() == sepNames.size())
			{
				rs.setFirstPage(first);
			}
			else
			{
				if (pageMajor)
				{
					rs.setSkipPage(sepNames.size() - 1);
					rs.setFirstPage(i + first);
				}
				else
				{
					rs.setFirstPage(i * n + first);
				}
			}
		}
		return r;
	}

	/**
	 * addSepRun
	 *
	 * @param fileSpec
	 * @param sepNames
	 * @deprecated
	 *
	 * @return JDFRunList
	 */
	@Deprecated
	public JDFRunList addSepRun(final VElement fileSpec, final Vector sepNames)
	{
		return addSepRun(fileSpec, sepNames, 0, 1, true);
	}

	/**
	 * addSepRun
	 *
	 * @param fileSpec
	 * @param sepNames
	 * @param first
	 * @deprecated
	 *
	 * @return JDFRunList
	 */
	@SuppressWarnings("unchecked")
	@Deprecated
	public JDFRunList addSepRun(final VElement fileSpec, final Vector sepNames, final int first)
	{
		return addSepRun(fileSpec, sepNames, first, 1, true);
	}

	/**
	 * addSepRun
	 *
	 * @param fileSpec
	 * @param sepNames
	 * @param first
	 * @param n
	 * @deprecated
	 *
	 * @return JDFRunList
	 */
	@SuppressWarnings("unchecked")
	@Deprecated
	public JDFRunList addSepRun(final VElement fileSpec, final Vector sepNames, final int first, final int n)
	{
		return addSepRun(fileSpec, sepNames, first, n, true);
	}

	/**
	 * addSepRun
	 *
	 * @param fileSpec
	 * @param sepNames
	 * @param first
	 * @param pageMajor
	 * @deprecated
	 *
	 * @return JDFRunList
	 */
	@SuppressWarnings("unchecked")
	@Deprecated
	public JDFRunList addSepRun(final VElement fileSpec, final Vector sepNames, final int first, final boolean pageMajor)
	{
		return addSepRun(fileSpec, sepNames, first, 1, pageMajor);
	}

	/**
	 * addSepRun
	 *
	 * @param fileSpec
	 * @param sepNames
	 * @param pageMajor
	 * @deprecated
	 *
	 * @return JDFRunList
	 */
	@SuppressWarnings("unchecked")
	@Deprecated
	public JDFRunList addSepRun(final VElement fileSpec, final Vector sepNames, final boolean pageMajor)
	{
		return addSepRun(fileSpec, sepNames, 0, 1, pageMajor);
	}

	/**
	 * addSepRun
	 *
	 * @param fileSpec
	 * @param sepNames
	 * @param first
	 * @param n
	 * @param pageMajor
	 *
	 * @return JDFRunList
	 * @deprecated 060503 use the version with VString VString
	 */
	@SuppressWarnings("unchecked")
	@Deprecated
	public JDFRunList addSepRun(final VElement fileSpec, final Vector sepNames, final int first, final int n, final boolean pageMajor)
	{
		final JDFRunList r = (JDFRunList) addPartition(JDFResource.EnumPartIDKey.Run, "Run" + uniqueID(0)); // Test TBD
		final int siz = fileSpec.size();
		r.setNPage(n);

		for (int i = 0; i < sepNames.size(); i++)
		{
			final JDFRunList rs = (JDFRunList) r.addPartition(JDFResource.EnumPartIDKey.Separation, sepNames.elementAt(i).toString());
			// HIER LIEGT DAS PROBLEM
			// JDFRunList rs = new JDFRunList(r.AddPart("Seperation",
			// sepNames.elementAt(i).toString()) );
			// rs.setAttribute("Separation",(String)sepNames.elementAt(i));
			final JDFResource rfspec = (JDFResource) fileSpec.elementAt(Math.min(i, siz - 1));
			rs.refElement(rfspec);

			if (fileSpec.size() == sepNames.size())
			{
				rs.setAttribute("FirstPage", first, JDFConstants.EMPTYSTRING);
			}
			else
			{
				if (pageMajor)
				{
					rs.setAttribute("SkipPage", sepNames.size() - 1, JDFConstants.EMPTYSTRING);
					rs.setAttribute("FirstPage", i + first, JDFConstants.EMPTYSTRING);
				}
				else
				{
					rs.setAttribute("FirstPage", i * n + first, JDFConstants.EMPTYSTRING);
				}
			}
		}

		return r;
	}

	/**
	 * addSepRun
	 *
	 * @param fileSpec
	 * @param sepNames
	 * @deprecated
	 *
	 * @return JDFRunList
	 */
	@Deprecated
	public JDFRunList addSepRun(final JDFResource fileSpec, final String sepNames)
	{
		final VElement v = new VElement();
		v.add(fileSpec);
		return addSepRun(v, StringUtil.tokenize(sepNames, JDFConstants.COMMA, false), 0, 1, true);
	}

	/**
	 * addSepRun
	 *
	 * @param fileSpec
	 * @param sepNames
	 * @param first
	 * @deprecated
	 *
	 * @return JDFRunList
	 */
	@Deprecated
	public JDFRunList addSepRun(final JDFResource fileSpec, final String sepNames, final int first)
	{
		final VElement v = new VElement();
		v.add(fileSpec);
		return addSepRun(v, StringUtil.tokenize(sepNames, JDFConstants.COMMA, false), first, 1, true);
	}

	/**
	 * addSepRun
	 *
	 * @param fileSpec
	 * @param sepNames
	 * @param first
	 * @param n
	 * @deprecated
	 *
	 * @return JDFRunList
	 */
	@Deprecated
	public JDFRunList addSepRun(final JDFResource fileSpec, final String sepNames, final int first, final int n)
	{
		final VElement v = new VElement();
		v.add(fileSpec);
		return addSepRun(v, StringUtil.tokenize(sepNames, JDFConstants.COMMA, false), first, n, true);
	}

	/**
	 * addSepRun
	 *
	 * @param fileSpec
	 * @param sepNames
	 * @param first
	 * @param pageMajor
	 * @deprecated
	 *
	 * @return JDFRunList
	 */
	@Deprecated
	public JDFRunList addSepRun(final JDFResource fileSpec, final String sepNames, final int first, final boolean pageMajor)
	{
		final VElement v = new VElement();
		v.add(fileSpec);
		return addSepRun(v, StringUtil.tokenize(sepNames, JDFConstants.COMMA, false), first, 1, pageMajor);
	}

	/**
	 * addSepRun
	 *
	 * @param fileSpec
	 * @param sepNames
	 * @param pageMajor
	 * @deprecated
	 *
	 * @return JDFRunList
	 */
	@Deprecated
	public JDFRunList addSepRun(final JDFResource fileSpec, final String sepNames, final boolean pageMajor)
	{
		final VElement v = new VElement();
		v.add(fileSpec);
		return addSepRun(v, StringUtil.tokenize(sepNames, JDFConstants.COMMA, false), 0, 1, pageMajor);
	}

	/**
	 * addSepRun
	 *
	 * @param fileSpec
	 * @param sepNames
	 * @param sep
	 * @deprecated
	 *
	 * @return JDFRunList
	 */
	@Deprecated
	public JDFRunList addSepRun(final JDFResource fileSpec, final String sepNames, final String sep)
	{
		final VElement v = new VElement();
		v.add(fileSpec);
		return addSepRun(v, StringUtil.tokenize(sepNames, sep, false), 0, 1, true);
	}

	/**
	 * addSepRun
	 *
	 * @param fileSpec
	 * @param sepNames
	 * @param first
	 * @param sep
	 * @deprecated
	 *
	 * @return JDFRunList
	 */
	@Deprecated
	public JDFRunList addSepRun(final JDFResource fileSpec, final String sepNames, final int first, final String sep)
	{
		final VElement v = new VElement();
		v.add(fileSpec);
		return addSepRun(v, StringUtil.tokenize(sepNames, sep, false), first, 1, true);
	}

	/**
	 * addSepRun
	 *
	 * @param fileSpec
	 * @param sepNames
	 * @param first
	 * @param n
	 * @param sep
	 *
	 * @deprecated
	 *
	 * @return JDFRunList
	 */
	@Deprecated
	public JDFRunList addSepRun(final JDFResource fileSpec, final String sepNames, final int first, final int n, final String sep)
	{
		final VElement v = new VElement();
		v.add(fileSpec);
		return addSepRun(v, StringUtil.tokenize(sepNames, sep, false), first, n, true);
	}

	/**
	 * addSepRun
	 *
	 * @param fileSpec
	 * @param sepNames
	 * @param first
	 * @param pageMajor
	 * @param sep
	 *
	 * @deprecated
	 *
	 * @return JDFRunList
	 */
	@Deprecated
	public JDFRunList addSepRun(final JDFResource fileSpec, final String sepNames, final int first, final boolean pageMajor, final String sep)
	{
		final VElement v = new VElement();
		v.add(fileSpec);
		return addSepRun(v, StringUtil.tokenize(sepNames, sep, false), first, 1, pageMajor);
	}

	/**
	 * addSepRun
	 *
	 * @param fileSpec
	 * @param sepNames
	 * @param pageMajor
	 * @param sep
	 * @deprecated
	 *
	 * @return JDFRunList
	 */
	@Deprecated
	public JDFRunList addSepRun(final JDFResource fileSpec, final String sepNames, final boolean pageMajor, final String sep)
	{
		final VElement v = new VElement();
		v.add(fileSpec);
		return addSepRun(v, StringUtil.tokenize(sepNames, sep, false), 0, 1, pageMajor);
	}

	/**
	 * addSepRun
	 *
	 * @param fileSpec
	 * @param sepNames
	 * @param first
	 * @param n
	 * @param pageMajor
	 * @param sep
	 *
	 * @deprecated
	 *
	 * @return JDFRunList
	 */
	@Deprecated
	public JDFRunList addSepRun(final JDFResource fileSpec, final String sepNames, final int first, final int n, final boolean pageMajor, final String sep)
	{
		final VElement v = new VElement();
		v.add(fileSpec);
		return addSepRun(v, StringUtil.tokenize(sepNames, sep, false), first, n, pageMajor);
	}

	/**
	 * addSepRun
	 *
	 * @param fileNames
	 * @param sepNames
	 * @deprecated
	 *
	 * @return JDFRunList
	 */
	@Deprecated
	public JDFRunList addSepRun(final String fileNames, final String sepNames)
	{
		return addSepRun(fileNames, sepNames, 0, 1, true, JDFConstants.COMMA);
	}

	/**
	 * addSepRun
	 *
	 * @param fileNames
	 * @param sepNames
	 * @param first
	 * @deprecated
	 *
	 * @return JDFRunList
	 */
	@Deprecated
	public JDFRunList addSepRun(final String fileNames, final String sepNames, final int first)
	{
		return addSepRun(fileNames, sepNames, first, 1, true, JDFConstants.COMMA);
	}

	/**
	 * addSepRun
	 *
	 * @param fileNames
	 * @param sepNames
	 * @param first
	 * @param n
	 * @deprecated
	 *
	 * @return JDFRunList
	 */
	@Deprecated
	public JDFRunList addSepRun(final String fileNames, final String sepNames, final int first, final int n)
	{
		return addSepRun(fileNames, sepNames, first, n, true, JDFConstants.COMMA);
	}

	/**
	 * addSepRun
	 *
	 * @param fileNames
	 * @param sepNames
	 * @param first
	 * @param pageMajor
	 * @deprecated
	 *
	 * @return JDFRunList
	 */
	@Deprecated
	public JDFRunList addSepRun(final String fileNames, final String sepNames, final int first, final boolean pageMajor)
	{
		return addSepRun(fileNames, sepNames, first, 1, pageMajor, JDFConstants.COMMA);
	}

	/**
	 * addSepRun
	 *
	 * @param fileNames
	 * @param sepNames
	 * @param pageMajor
	 * @deprecated
	 *
	 * @return JDFRunList
	 */
	@Deprecated
	public JDFRunList addSepRun(final String fileNames, final String sepNames, final boolean pageMajor)
	{
		return addSepRun(fileNames, sepNames, 0, 1, pageMajor, JDFConstants.COMMA);
	}

	/**
	 * addSepRun
	 *
	 * @param fileNames
	 * @param sepNames
	 * @param sep
	 * @deprecated
	 *
	 * @return JDFRunList
	 */
	@Deprecated
	public JDFRunList addSepRun(final String fileNames, final String sepNames, final String sep)
	{
		return addSepRun(fileNames, sepNames, 0, 1, true, sep);
	}

	/**
	 * addSepRun
	 *
	 * @param fileNames
	 * @param sepNames
	 * @param first
	 * @param sep
	 * @deprecated
	 *
	 * @return JDFRunList
	 */
	@Deprecated
	public JDFRunList addSepRun(final String fileNames, final String sepNames, final int first, final String sep)
	{
		return addSepRun(fileNames, sepNames, first, 1, true, sep);
	}

	/**
	 * addSepRun
	 *
	 * @param fileNames
	 * @param sepNames
	 * @param first
	 * @param n
	 * @param sep
	 *
	 * @deprecated
	 *
	 * @return JDFRunList
	 */
	@Deprecated
	public JDFRunList addSepRun(final String fileNames, final String sepNames, final int first, final int n, final String sep)
	{
		return addSepRun(fileNames, sepNames, first, n, true, sep);
	}

	/**
	 * addSepRun
	 *
	 * @param fileNames
	 * @param sepNames
	 * @param first
	 * @param pageMajor
	 * @param sep
	 *
	 * @deprecated
	 *
	 * @return JDFRunList
	 */
	@Deprecated
	public JDFRunList addSepRun(final String fileNames, final String sepNames, final int first, final boolean pageMajor, final String sep)
	{
		return addSepRun(fileNames, sepNames, first, 1, pageMajor, sep);
	}

	/**
	 * addSepRun
	 *
	 * @param fileNames
	 * @param sepNames
	 * @param pageMajor
	 * @param sep
	 *
	 * @deprecated
	 *
	 * @return JDFRunList
	 */
	@Deprecated
	public JDFRunList addSepRun(final String fileNames, final String sepNames, final boolean pageMajor, final String sep)
	{
		return addSepRun(fileNames, sepNames, 0, 1, pageMajor, sep);
	}

	/**
	 * addSepRun
	 *
	 * @param fileNames
	 * @param sepNames
	 * @param first
	 * @param n
	 * @param pageMajor
	 * @param sep
	 *
	 * @deprecated
	 *
	 * @return JDFRunList
	 */
	@Deprecated
	public JDFRunList addSepRun(final String fileNames, final String sepNames, final int first, final int n, final boolean pageMajor, final String sep)
	{
		return addSepRun(StringUtil.tokenize(fileNames, sep, false), StringUtil.tokenize(sepNames, sep, false), first, n, pageMajor);
	}

	/**
	 * set RunList/LayoutElement/FileSpec/@URL
	 *
	 * @param url the url to set
	 * @return true if ok
	 */
	public boolean setFileURL(final String url)
	{
		final JDFFileSpec fspec = getCreateLayoutElement().getCreateFileSpec();
		fspec.setMimeURL(url);
		return true;
	}

	/**
	 * set RunList/FileSpec/@URL - only for 2.0
	 *
	 * @param url the url to set
	 * @return true if ok
	 */
	public boolean setFileSpecURL(final String url)
	{
		final JDFFileSpec fspec = getCreateFileSpec();
		fspec.setMimeURL(url);
		return true;
	}

	/**
	 * (25) getCreateFileSpec
	 *
	 * @return JDFFileSpec the element
	 */
	public JDFFileSpec getCreateFileSpec()
	{
		return (JDFFileSpec) getCreateElement_KElement(ElementName.FILESPEC, null, 0);
	}

	/**
	 * set RunList/ByteMap/FileSpec/@URL
	 *
	 * @param url the url to set
	 * @return true if ok
	 */
	public boolean setByteMapURL(final String url)
	{
		final JDFFileSpec fspec = getCreateByteMap().getCreateFileSpec(0);
		fspec.setResourceUsage("RasterFileLocation");
		fspec.setMimeURL(url);
		return true;
	}

	/**
	 * get RunList/LayoutElement/FileSpec/@URL also evaluate RunList/@directory and concatinate Directory + URL in case URL is a relative URL
	 *
	 * @Directory is ignored if URL contains a scheme or is an absolute URL
	 *
	 * @return URL if a URL or Directory attribute exists, else null
	 */
	public String getFileURL()
	{
		final JDFFileSpec fspec = getFileSpec();
		if (fspec == null)
		{
			return null;
		}

		return UrlUtil.getURLWithDirectory(getDirectory(), fspec.getURL());
	}

	/**
	 * (36) set attribute Pages
	 *
	 * @param value : the value to set the attribute to
	 */
	@Override
	public void setPages(JDFIntegerRangeList value)
	{
		super.setPages(value);

		if (value == null)
		{
			return;
		}
		if (value.getDef() <= 0 && super.getNPage() > 0)
		{
			value = new JDFIntegerRangeList(value);
			value.setDef(super.getNPage());
		}

		if (value.getElementCount() > 0)
		{
			setNPage(value.getElementCount());
		}
	}

	/**
	 * get RunList/LayoutElement/FileSpec/@MimeType
	 *
	 * @return MIMEType if it exists, else null
	 */
	public String getFileMimeType()
	{
		final JDFFileSpec fspec = getFileSpec();
		if (fspec == null)
		{
			return null;
		}
		return fspec.getMimeType();
	}

	/**
	 * get RunList/LayoutElement/FileSpec or RunList/FileSpec in case of XJDF
	 *
	 * @return JDFFileSpec FileSpec if it exists, else null
	 */
	public JDFFileSpec getFileSpec()
	{
		final JDFLayoutElement lay = getLayoutElement();
		if (lay == null)
		{
			return (JDFFileSpec) getElement(ElementName.FILESPEC);
		}
		return lay.getFileSpec();
	}

	/**
	 * get a map of VJDFAttributeMap that are sorted by the fileSpec URL key<br>
	 * each url key maps the leaves that share the same URL
	 *
	 * @return fileSpecMap a map of VJDFAttributeMap
	 */
	public HashMap<String, VJDFAttributeMap> getCommonURLFileSpecMap()
	{
		final VElement vE = getLeaves(false);
		final HashMap<String, VJDFAttributeMap> fileMap = new HashMap<>();

		// loop over all leaves of the runlist
		for (int i = 0; i < vE.size(); i++)
		{
			final JDFRunList rl = (JDFRunList) vE.elementAt(i);
			final String url = rl.getFileURL();
			// do we have a preexisting leaf that shares the same filespec URL

			// add a new key
			VJDFAttributeMap vPart = null;
			if (!fileMap.containsKey(url))
			{
				vPart = new VJDFAttributeMap();
				vPart.addElement(rl.getPartMap());
				fileMap.put(url, vPart);
			}
			else
			{
				// append this leaf to a preexisting key
				vPart = fileMap.get(url);
				vPart.addElement(rl.getPartMap());
			}
		}

		return fileMap;
	}

	/**
	 * get a list of all partition keys that this resource may be implicitly partitioned by e.g. RunIndex for RunList...
	 *
	 * @return vector of EnumPartIDKey
	 */

	/**
	 *
	 * @see org.cip4.jdflib.resource.JDFResource#getImplicitPartitions()
	 */
	@Override
	public Vector<EnumPartIDKey> getImplicitPartitions()
	{
		Vector<EnumPartIDKey> v = super.getImplicitPartitions();
		if ("Dynamic".equals(getAttribute(AttributeName.AUTOMATION)))
			return v;

		if (v == null)
		{
			v = new Vector<>();
		}
		v.add(EnumPartIDKey.RunIndex);
		v.add(EnumPartIDKey.DocIndex);
		v.add(EnumPartIDKey.DocRunIndex);
		v.add(EnumPartIDKey.DocSheetIndex);
		v.add(EnumPartIDKey.SetIndex);
		v.add(EnumPartIDKey.PageNumber);
		return v;
	}

	/**
	 *
	 * @return
	 */
	public Iterator<JDFRunData> getPageIterator()
	{
		return new PageIterator(this);
	}

	// //////////////////////////////////////////////////////
	/**
	 * class that abstracts a RunList Partition so that you can efficiently access the File using RunIndex as a marker
	 */
	public class JDFRunData
	{
		protected JDFRunList runList;
		protected int runIndex;
		protected int firstIndex;
		protected int lastIndex;

		/**
		 * copy constructor
		 *
		 * @param other the JDFRunIndex object to clone
		 */
		public JDFRunData(final JDFRunData other)
		{
			runList = other.runList;
			runIndex = other.runIndex;
			firstIndex = other.firstIndex;
			lastIndex = other.lastIndex;
		}

		/**
		 * null constructor
		 *
		 */
		protected JDFRunData()
		{
			runList = null;
			runIndex = 0;
			firstIndex = 0;
			lastIndex = 0;
		}

		/**
		 * get the 0 based page number in the file specified by RunList/@URL
		 *
		 * @return the page number in the file; -1 if out of range
		 */
		public int getPageInFile()
		{
			int page = -1;

			final int delta = runIndex - firstIndex;
			if (runList.hasAttribute(AttributeName.PAGES))
			{
				final int pages[] = runList.getPages().getIntegerList().getIntArray();
				if (delta >= pages.length)
				{
					throw new JDFException("getPageInFile: Pages is kaputt");
				}
				page = pages[delta];
			}
			else
			{
				page = runList.getFirstPage() + delta;
			}
			return page;
		}

		/**
		 * @return the lastIndex
		 */
		public int getLastIndex()
		{
			return lastIndex;
		}

		/**
		 * @return the runIndex
		 */
		public int getRunIndex()
		{
			return runIndex;
		}

		/**
		 * @return the runList
		 */
		public JDFRunList getRunList()
		{
			return runList;
		}

		/**
		 * @return the firstIndex
		 */
		public int getFirstIndex()
		{
			return firstIndex;
		}
	}

	/**
	 * gets the first logical RunIndex for this partition
	 *
	 * @return the first RunIndex that this RunList partition specifies
	 */
	public int getFirstIndex()
	{
		return getFirstIndex(null);
	}

	/**
	 * gets the first logical RunIndex for this partition
	 *
	 * @param last
	 *
	 * @return the first RunIndex that this RunList partition specifies
	 */
	protected int getFirstIndex(final JDFRunData last)
	{
		if (hasAttribute(AttributeName.LOGICALPAGE))
		{
			return getLogicalPage();
		}

		if (!getIsPage())
		{
			final KElement e = getParentNode_KElement();
			if (e instanceof JDFRunList)
			{
				return ((JDFRunList) e).getFirstIndex(last);
			}
		}
		final JDFRunList rl = (JDFRunList) getElement_KElement(ElementName.RUNLIST, null, 0);
		if (rl != null && rl.getIsPage())
		{
			return rl.getFirstIndex(last);
		}

		final JDFRunList previousRL = (JDFRunList) getPreviousSiblingElement(ElementName.RUNLIST, null);
		if (previousRL == null)
		{
			return 0;
		}
		int offset = 0;
		if (last != null && previousRL == last.runList)
		{
			offset = last.lastIndex;
		}
		else
		{
			offset = previousRL.getLastIndex(last);
		}
		return offset + 1;
	}

	/**
	 * get the list of RunList Leaves with IsPage=true
	 *
	 * @return
	 */
	public VElement getPageLeaves()
	{
		final VElement v = getLeaves(false);
		final int size = v.size();
		for (int i = 0; i < size; i++)
		{
			JDFRunList rl = (JDFRunList) v.elementAt(i);
			boolean bRep = false;
			while (rl != null && !rl.getIsPage())
			{
				bRep = true;
				rl = (JDFRunList) rl.getParentNode_KElement();
			}
			if (bRep)
			{
				v.set(i, rl);
			}
		}
		v.unify();
		return v;
	}

	/**
	 * gets the last logical RunIndex for this partition
	 *
	 * @return the last RunIndex that this RunList partition specifies
	 */
	public int getLastIndex()
	{
		return getLastIndex(null);
	}

	/**
	 * gets the last logical RunIndex for this partition
	 *
	 * @param last
	 *
	 * @return the last RunIndex that this RunList partition specifies
	 */
	protected int getLastIndex(final JDFRunData last)
	{
		if (!getIsPage())
		{
			final KElement e = getParentNode_KElement();
			if (e instanceof JDFRunList)
			{
				return ((JDFRunList) e).getLastIndex(last);
			}
		}
		final JDFRunList rl = (JDFRunList) getElement_KElement(ElementName.RUNLIST, null, -1);
		if (rl != null && rl.getIsPage())
		{
			return rl.getLastIndex(last);
		}

		int offset = -1;
		if (hasAttribute(AttributeName.LOGICALPAGE))
		{
			offset = getLogicalPage() - 1;
		}
		else
		{
			final JDFRunList previousRL = (JDFRunList) getPreviousSiblingElement(ElementName.RUNLIST, null);
			if (previousRL != null)
			{
				if (last != null && last.runList == previousRL)
				{
					offset = last.lastIndex;
				}
				else
				{
					offset = previousRL.getLastIndex(last);
				}
			}
		}
		return offset + getNPage();
	}

	/**
	 * calculates nPage from the leaves if possible - else does the standard stuff
	 *
	 * @see org.cip4.jdflib.auto.JDFAutoRunList#getNPage()
	 */
	@Override
	public int getNPage()
	{
		int n = 0;

		if (!getIsPage())
		{
			return 0;
		}

		if (hasAttribute_KElement(AttributeName.NPAGE, null, false))
		{
			return super.getNPage();
		}
		if (hasAttribute_KElement(AttributeName.PAGES, null, false))
		{
			final JDFIntegerRangeList pages = getPages();
			final int nPage = pages.getElementCount();
			return nPage;
		}
		final List<KElement> v = getChildArray_KElement(getLocalName(), getNamespaceURI(), null, true, 0);

		if (v != null)
		{
			for (final KElement e : v)
			{
				final int page = ((JDFRunList) e).getNPage();
				if (page < 0)
				{
					return -1;
				}

				n += page;
			}
		}

		return n <= 0 ? super.getNPage() : n;
	}

	/**
	 * get the Partition that corresponds to a given runIndex
	 *
	 * @param index the runIndex to search for
	 * @return JDFRunList the partition that contains this index. use @see getPageInFile to find the correct page
	 *
	 *         warning blindly calling this from inside a loop may cause performance issues - use the getPageIterator if you need performance optimized access
	 */
	public JDFRunList getIndexPartition(final int index)
	{
		final VElement leaves = getPageLeaves();
		for (int i = 0; i < leaves.size(); i++)
		{
			final JDFRunList rl = (JDFRunList) leaves.elementAt(i);
			if (rl.getFirstIndex() <= index && rl.getLastIndex() >= index)
			{
				return rl;
			}
		}
		return null;

	}

	/**
	 * get the 0 based page number in the specified file
	 *
	 * @param runIndex
	 *
	 * @return the page number in the file; -1 if ot of range
	 */
	public int getPageInFile(final int runIndex)
	{
		final JDFRunData ri = new JDFRunData();
		ri.firstIndex = getFirstIndex();
		if (runIndex < ri.firstIndex)
		{
			return -1;
		}
		ri.lastIndex = getLastIndex();
		if (runIndex > ri.lastIndex)
		{
			return -1;
		}
		ri.runIndex = runIndex;
		ri.runList = this;
		return ri.getPageInFile();
	}

	// //////////////////////////////////////////////////////
	/**
	 * inner iterator class
	 */
	private class PageIterator implements Iterator<JDFRunData>
	{

		private final JDFRunList rl;
		private int index;
		private final int maxIndex;
		private final JDFRunData[] vRunIndex;
		private int lastIndex;

		/**
		 * @param list
		 */
		public PageIterator(final JDFRunList list)
		{
			lastIndex = 0;
			rl = (JDFRunList) list.getResourceRoot();
			index = list.getFirstIndex();
			maxIndex = list.getLastIndex();
			final VElement leaves = rl.getPageLeaves();
			vRunIndex = new JDFRunData[leaves.size()];
			JDFRunData last = null;
			for (int i = 0; i < leaves.size(); i++)
			{
				final JDFRunList _rl = (JDFRunList) leaves.elementAt(i);
				final int firstIndex = _rl.getFirstIndex(last);
				final int _lastIndex = firstIndex + _rl.getNPage() - 1;
				final JDFRunData ri = new JDFRunData();
				ri.runList = _rl;
				ri.firstIndex = firstIndex;
				ri.lastIndex = _lastIndex;
				vRunIndex[i] = ri;
				last = ri;
			}
		}

		/**
		 * (non-Javadoc)
		 *
		 * @see java.util.Iterator#hasNext()
		 */
		@Override
		public boolean hasNext()
		{
			return index <= maxIndex;
		}

		/**
		 *
		 * @see java.util.Iterator#next() returns a JDFRunIndex object that refers to the RunList entry and the index within it
		 */
		@Override
		public JDFRunData next()
		{
			for (int i = lastIndex; i < vRunIndex.length; i++)
			{
				final JDFRunData ri = vRunIndex[i];
				if (ri.firstIndex <= index && ri.lastIndex >= index)
				{
					final JDFRunData ri2 = new JDFRunData(ri);
					ri2.runIndex = index;
					index++;
					lastIndex = i;
					return ri2;
				}
			}
			return null;
		}

		/**
		 * not implemented
		 *
		 * @throws JDFException (always...)
		 */
		@Override
		public void remove() throws JDFException
		{
			throw new JDFException("remove not implented");
		}
	}

	// /// End of iterator class /////

	/**
	 * collapse all redundant attributes and elements
	 *
	 * @param bCollapseToNode only collapse redundant attriutes and elements that pre-exist in the nodes
	 * @param bCollapseElements if true, collapse elements, else only collapse attributes
	 *
	 * @default Collapse(false)
	 */
	@Override
	public void collapse(final boolean bCollapseToNode, final boolean bCollapseElements)
	{
		super.collapse(bCollapseToNode, bCollapseElements);
		fixNPage();
	}

	/**
	 * @see org.cip4.jdflib.resource.JDFResource#expand(boolean)
	 * @param bDeleteFromNode if true, removes all intermediate elements and attributes
	 */
	@Override
	public void expand(final boolean bDeleteFromNode)
	{
		fixNPage();
		super.expand(bDeleteFromNode);
	}

	/**
	 * write NPage into all leaves with IsPage=true and write the appropriate value into the lower level nodes
	 *
	 *
	 */
	public void fixNPage()
	{
		int siz = 0;

		final VElement v = getPageLeaves();
		if (v != null)
		{
			siz = v.size();
			for (int i = 0; i < siz; i++)
			{
				final JDFRunList pageLeaf = (JDFRunList) v.elementAt(i);
				final int page = pageLeaf.getNPage();
				if (page > 0)
				{
					pageLeaf.setNPage(page);
				}
			}
		}

		final List<JDFResource> v2 = getLeafArray(true);
		if (v2 != null)
		{
			siz = v2.size();
			for (int i = siz - 1; i >= 0; i--)
			{
				final JDFRunList rl = (JDFRunList) v2.get(i);
				if (v != null && v.contains(rl))
				{
					v2.remove(rl); // it's a leaf
				}
				else
				{
					rl.removeAttribute(AttributeName.NPAGE);
				}
			}
		}

		if (v2 != null)
		{
			siz = v2.size();
			for (int i = siz - 1; i >= 0; i--)
			{
				final JDFRunList rl = (JDFRunList) v2.get(i);
				final int page = rl.getNPage();
				if (page > 0)
				{
					rl.setNPage(page);
				}
			}
		}
	}

	/**
	 * get the first matching parent or this with IsPage==true
	 *
	 * @return
	 */
	public JDFRunList getTruePage()
	{
		JDFRunList rl = this;
		while (true)
		{
			if (rl.getIsPage())
			{
				return rl;
			}
			final KElement parent = rl.getParentNode_KElement();
			if (parent instanceof JDFRunList)
			{
				rl = (JDFRunList) parent;
			}
			else
			{
				return null;
			}
		}
	}

	/**
	 * get the first matching parent or this with IsPage==true
	 *
	 * @return
	 */
	public boolean isPageLeaf()
	{
		if (!getIsPage())
		{
			return false;
		}
		final VElement v1 = new VElement();
		v1.addAll(getChildArray_KElement(getLocalName(), getNamespaceURI(), null, true, 0));

		final VElement v = v1;
		if (v != null)
		{
			final int siz = v.size();
			for (int i = 0; i < siz; i++)
			{
				final JDFRunList rl = (JDFRunList) v.elementAt(i);
				if (rl.getIsPage())
				{
					return false;
				}
			}
		}

		return true;
	}

	/**
	 * @see org.cip4.jdflib.auto.JDFAutoRunList#getPages()
	 */
	@Override
	public JDFIntegerRangeList getPages()
	{
		JDFIntegerRangeList irl = super.getPages();
		if (irl == null)
		{
			irl = new JDFIntegerRangeList(new JDFIntegerRange(0, -1));
		}
		final int nPage = super.getNPage();
		if (nPage > 0)
		{
			irl.setDef(nPage);
		}
		return irl;
	}

	/**
	 * get the pageListIndex with a reasonable def for number of pages<br/>
	 * first try the pagelist, then do for local npage
	 *
	 * @see org.cip4.jdflib.auto.JDFAutoRunList#getPageListIndex()
	 */
	@Override
	public JDFIntegerRangeList getPageListIndex()
	{
		JDFIntegerRangeList list = super.getPageListIndex();
		if (list == null)
			list = new JDFIntegerRangeList();
		final JDFPageList pl = getPageList();
		int nPage = 0;

		if (pl != null)
		{
			nPage = pl.getNPage();
		}
		if (nPage <= 0)
			nPage = getNPage();
		list.setDef(nPage);
		return list;
	}
}
