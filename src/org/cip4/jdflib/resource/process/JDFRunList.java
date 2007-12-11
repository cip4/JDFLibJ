/*
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
import java.util.Vector;

import org.apache.commons.lang.enums.ValuedEnum;
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
import org.cip4.jdflib.datatypes.JDFIntegerRangeList;
import org.cip4.jdflib.datatypes.VJDFAttributeMap;
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
     * @param ownerDocument
     * @param qualifiedName
     * @throws DOMException
     */
    public JDFRunList(
            CoreDocumentImpl myOwnerDocument,
            String qualifiedName)
    throws DOMException
    {
        super(myOwnerDocument, qualifiedName);
    }


    /**
     * Constructor for JDFRunList
     * @param ownerDocument
     * @param namespaceURI
     * @param qualifiedName
     * @throws DOMException
     */
    public JDFRunList(
            CoreDocumentImpl myOwnerDocument,
            String myNamespaceURI,
            String qualifiedName)
    throws DOMException
    {
        super(myOwnerDocument, myNamespaceURI, qualifiedName);
    }

    /**
     * Constructor for JDFRunList
     * @param ownerDocument
     * @param namespaceURI
     * @param qualifiedName
     * @param localName
     * @throws DOMException
     */
    public JDFRunList(
            CoreDocumentImpl myOwnerDocument,
            String myNamespaceURI,
            String qualifiedName,
            String myLocalName)
    throws DOMException
    {
        super(myOwnerDocument, myNamespaceURI, qualifiedName, myLocalName);
    }

    //**************************************** Methods *********************************************
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
     * @param String fileName
     * @deprecated
     *
     * @return JDFRunList
     */
    @Deprecated
	public JDFRunList addRun(String fileName)
    {
        return addRun(fileName, 0, -1);
    }

    /**
     * addRun
     *
     * @param String fileName
     * @param int first
     * @deprecated
     *
     * @return JDFRunList
     */
    @Deprecated
	public JDFRunList addRun(String fileName, int first)
    {
        return addRun(fileName, first, -1);
    }

    /**
     * addRun
     *
     * @param String fileName
     * @param int first
     * @param int last
     *
     * @return JDFRunList
     */
    public JDFRunList addRun(String fileName, int first, int last)
    {
        String runID = "Run" + uniqueID(0);
        JDFRunList r = (JDFRunList) addPartition(JDFResource.EnumPartIDKey.Run, runID); //JDFRun to RunList

        JDFIntegerRangeList irl = new JDFIntegerRangeList();
        irl.append(first, last);
        r.setPages(irl);
        JDFLayoutElement loe = (JDFLayoutElement) r.appendElement(ElementName.LAYOUTELEMENT, JDFConstants.EMPTYSTRING); 
        loe.setMimeURL(fileName);
        return r;
    }

    /**
     * addPDF
     *
     * @param String fileName
     * @deprecated
     *
     * @return JDFRunList
     */
    @Deprecated
	public JDFRunList addPDF(String fileName)
    {
        return addPDF(fileName, 0, -1);
    }

    /**
     * addPDF
     *
     * @param String fileName
     * @param int first
     * @deprecated
     *
     * @return JDFRunList
     */
    @Deprecated
	public JDFRunList addPDF(String fileName, int first)
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
    public JDFRunList addPDF(String fileName, int first, int last)
    {
        JDFRunList r = addRun(fileName, first, last);
        JDFFileSpec fs=r.getLayoutElement().getFileSpec();
        fs.setMimeType(JDFConstants.MIME_PDF);
        return r;
    }

    /**
     * addSepRun
     *
     * @param Vector  fileSpec
     * @param Vector  sepNames
     * @deprecated
     *
     * @return JDFRunList
     */
    @Deprecated
	public JDFRunList addSepRun(Vector fileNames, Vector sepNames)
    {
        return addSepRun(fileNames, sepNames, 0, 1, true);
    }

    /**
     * addSepRun
     *
     * @param Vector  fileSpec
     * @param Vector  sepNames
     * @param int     first
     * @deprecated
     *
     * @return JDFRunList
     */
    @Deprecated
	public JDFRunList addSepRun(Vector fileNames, Vector sepNames, int first)
    {
        return addSepRun(fileNames, sepNames, first, 1, true);
    }

    /**
     * addSepRun
     *
     * @param Vector  fileSpec
     * @param Vector  sepNames
     * @param int     first
     * @param int     n
     * @deprecated
     *
     * @return JDFRunList
     */
    @Deprecated
	public JDFRunList addSepRun(Vector fileNames, Vector sepNames, int first, int n)
    {
        return addSepRun(fileNames, sepNames, first, n, true);
    }

    /**
     * addSepRun
     *
     * @param Vector  fileSpec
     * @param Vector  sepNames
     * @param int     first
     * @param boolean pageMajor
     * @deprecated
     *
     * @return JDFRunList
     */
    @Deprecated
	public JDFRunList addSepRun(Vector fileNames, Vector sepNames, int first, boolean pageMajor)
    {
        return addSepRun(fileNames, sepNames, first, 1, pageMajor);
    }

    /**
     * addSepRun
     *
     * @param Vector  fileSpec
     * @param Vector  sepNames
     * @param boolean pageMajor
     * @deprecated
     *
     * @return JDFRunList
     */
    @Deprecated
	public JDFRunList addSepRun(Vector fileNames, Vector sepNames, boolean pageMajor)
    {
        return addSepRun(fileNames, sepNames, 0, 1, pageMajor);
    }

    /**
     * add a run separation 
     *
     * @param fileNames vector of file names for the URL attribute of the FileSpec in the LayoutElement
     * @param sepNames  parallel vector of separation names.
     * @param first     ndex of the first page in the file - Sets the RunList FirstPage attribute
     * @param n         the number of logical pages in this run
     * @param pageMajor if true, separations are ordered as page Major, i.e CMYKCMYK<br>
     *                  if false, ordering is CCMMYYKK
     *
     * @return JDFRunList
     */
    public JDFRunList addSepRun(Vector fileNames,
            Vector sepNames,
            int first,int n, boolean pageMajor)
    {
        JDFRunList r = (JDFRunList) addPartition (JDFResource.EnumPartIDKey.Run, "Run" + uniqueID(0));
        int siz = fileNames.size();
        r.setNPage(n);
        r.setIsPage(true);

        for (int i = 0; i < sepNames.size(); i++)
        {
            JDFRunList rs = (JDFRunList)
            r.addPartition(JDFResource.EnumPartIDKey.Separation, sepNames.elementAt(i).toString());
            JDFLayoutElement lo = rs.appendLayoutElement();
            lo.setMimeURL(((String)fileNames.elementAt(Math.min(i, siz - 1))));
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
     * @param VElement fileSpec
     * @param Vector   sepNames
     * @deprecated
     *
     * @return JDFRunList
     */
    @Deprecated
	public JDFRunList addSepRun(VElement fileSpec, Vector sepNames)
    {
        return addSepRun(fileSpec, sepNames, 0, 1, true);
    }

    /**
     * addSepRun
     *
     * @param VElement fileSpec
     * @param Vector   sepNames
     * @param int      first
     * @deprecated
     *
     * @return JDFRunList
     */
    @Deprecated
	public JDFRunList addSepRun(VElement fileSpec, Vector sepNames, int first)
    {
        return addSepRun(fileSpec, sepNames, first, 1, true);
    }

    /**
     * addSepRun
     *
     * @param VElement fileSpec
     * @param Vector   sepNames
     * @param int      first
     * @param int      n
     * @deprecated
     *
     * @return JDFRunList
     */
    @Deprecated
	public JDFRunList addSepRun(VElement fileSpec, Vector sepNames, int first, int n)
    {
        return addSepRun(fileSpec, sepNames, first, n, true);
    }

    /**
     * addSepRun
     *
     * @param VElement fileSpec
     * @param Vector   sepNames
     * @param int      first
     * @param boolean  pageMajor
     * @deprecated
     *
     * @return JDFRunList
     */
    @Deprecated
	public JDFRunList addSepRun(VElement fileSpec, Vector sepNames, int first, boolean pageMajor)
    {
        return addSepRun(fileSpec, sepNames, first, 1, pageMajor);
    }

    /**
     * addSepRun
     *
     * @param VElement fileSpec
     * @param Vector   sepNames
     * @param boolean  pageMajor
     * @deprecated
     *
     * @return JDFRunList
     */
    @Deprecated
	public JDFRunList addSepRun(VElement fileSpec, Vector sepNames, boolean pageMajor)
    {
        return addSepRun(fileSpec, sepNames, 0, 1, pageMajor);
    }

    /**
     * addSepRun
     *
     * @param VElement fileSpec
     * @param Vector   sepNames
     * @param int      first
     * @param int      n
     * @param boolean  pageMajor
     *
     * @return JDFRunList
     * @deprecated 060503 use the version with VString VString
     */
    @Deprecated
	public JDFRunList addSepRun(VElement fileSpec, Vector sepNames, int first, int n, boolean pageMajor)
    {
        JDFRunList r = (JDFRunList) addPartition(JDFResource.EnumPartIDKey.Run, "Run" + uniqueID(0)); //Test TBD
        int siz = fileSpec.size();
        r.setNPage(n);

        for (int i = 0; i < sepNames.size(); i++)
        {
            JDFRunList rs = (JDFRunList) 
            r.addPartition(JDFResource.EnumPartIDKey.Separation, sepNames.elementAt(i).toString());
            // HIER LIEGT DAS PROBLEM
            //JDFRunList rs = new JDFRunList(r.AddPart("Seperation", sepNames.elementAt(i).toString()) );
            //rs.setAttribute("Separation",(String)sepNames.elementAt(i));
            JDFResource rfspec = (JDFResource) fileSpec.elementAt(Math.min(i, siz - 1));
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
     * @param JDFResource fileSpec
     * @param String      sepNames
     * @deprecated
     *
     * @return JDFRunList
     */
    @Deprecated
	public JDFRunList addSepRun(JDFResource fileSpec, String sepNames)
    {
        VElement v = new VElement();
        v.add(fileSpec);
        return addSepRun(v, StringUtil.tokenize(sepNames, JDFConstants.COMMA, false), 0, 1, true);
    }

    /**
     * addSepRun
     *
     * @param JDFResource fileSpec
     * @param String      sepNames
     * @param int         first
     * @deprecated
     *
     * @return JDFRunList
     */
    @Deprecated
	public JDFRunList addSepRun(JDFResource fileSpec, String sepNames, int first)
    {
        VElement v = new VElement();
        v.add(fileSpec);
        return addSepRun(v, StringUtil.tokenize(sepNames, JDFConstants.COMMA, false), first, 1, true);
    }

    /**
     * addSepRun
     *
     * @param JDFResource fileSpec
     * @param String      sepNames
     * @param int         first
     * @param int         n
     * @deprecated
     *
     * @return JDFRunList
     */
    @Deprecated
	public JDFRunList addSepRun(JDFResource fileSpec, String sepNames, int first, int n)
    {
        VElement v = new VElement();
        v.add(fileSpec);
        return addSepRun(v, StringUtil.tokenize(sepNames, JDFConstants.COMMA, false), first, n, true);
    }

    /**
     * addSepRun
     *
     * @param JDFResource fileSpec
     * @param String      sepNames
     * @param int         first
     * @param boolean     pageMajor
     * @deprecated
     *
     * @return JDFRunList
     */
    @Deprecated
	public JDFRunList addSepRun(JDFResource fileSpec, String sepNames, int first, boolean pageMajor)
    {
        VElement v = new VElement();
        v.add(fileSpec);
        return addSepRun(v, StringUtil.tokenize(sepNames, JDFConstants.COMMA, false), first, 1, pageMajor);
    }

    /**
     * addSepRun
     *
     * @param JDFResource fileSpec
     * @param String      sepNames
     * @param boolean     pageMajor
     * @deprecated
     *
     * @return JDFRunList
     */
    @Deprecated
	public JDFRunList addSepRun(JDFResource fileSpec, String sepNames, boolean pageMajor)
    {
        VElement v = new VElement();
        v.add(fileSpec);
        return addSepRun(v, StringUtil.tokenize(sepNames, JDFConstants.COMMA, false), 0, 1, pageMajor);
    }

    /**
     * addSepRun
     *
     * @param JDFResource fileSpec
     * @param String      sepNames
     * @param String      sep
     * @deprecated
     *
     * @return JDFRunList
     */
    @Deprecated
	public JDFRunList addSepRun(JDFResource fileSpec, String sepNames, String sep)
    {
        VElement v = new VElement();
        v.add(fileSpec);
        return addSepRun(v, StringUtil.tokenize(sepNames, sep, false), 0, 1, true);
    }

    /**
     * addSepRun
     *
     * @param JDFResource fileSpec
     * @param String      sepNames
     * @param int         first
     * @param String      sep
     * @deprecated
     *
     * @return JDFRunList
     */
    @Deprecated
	public JDFRunList addSepRun(JDFResource fileSpec, String sepNames, int first, String sep)
    {
        VElement v = new VElement();
        v.add(fileSpec);
        return addSepRun(v, StringUtil.tokenize(sepNames, sep, false), first, 1, true);
    }

    /**
     * addSepRun
     *
     * @param JDFResource fileSpec
     * @param String      sepNames
     * @param int         first
     * @param int         n
     * @param String      sep
     * @deprecated
     *
     * @return JDFRunList
     */
    @Deprecated
	public JDFRunList addSepRun(JDFResource fileSpec, String sepNames, int first, int n, String sep)
    {
        VElement v = new VElement();
        v.add(fileSpec);
        return addSepRun(v, StringUtil.tokenize(sepNames, sep, false), first, n, true);
    }

    /**
     * addSepRun
     *
     * @param JDFResource fileSpec
     * @param String      sepNames
     * @param int         first
     * @param boolean     pageMajor
     * @param String      sep
     * @deprecated
     *
     * @return JDFRunList
     */
    @Deprecated
	public JDFRunList addSepRun(JDFResource fileSpec,
            String sepNames,
            int first,
            boolean pageMajor,
            String sep)
    {
        VElement v = new VElement();
        v.add(fileSpec);
        return addSepRun(v, StringUtil.tokenize(sepNames, sep, false), first, 1, pageMajor);
    }

    /**
     * addSepRun
     *
     * @param JDFResource fileSpec
     * @param String      sepNames
     * @param boolean     pageMajor
     * @param String      sep
     * @deprecated
     *
     * @return JDFRunList
     */
    @Deprecated
	public JDFRunList addSepRun(JDFResource fileSpec, String sepNames, boolean pageMajor, String sep)
    {
        VElement v = new VElement();
        v.add(fileSpec);
        return addSepRun(v, StringUtil.tokenize(sepNames, sep, false), 0, 1, pageMajor);
    }

    /**
     * addSepRun
     *
     * @param JDFResource fileSpec
     * @param String      sepNames
     * @param int         first
     * @param int         n
     * @param boolean     pageMajor
     * @param String      sep
     * @deprecated
     *
     * @return JDFRunList
     */
    @Deprecated
	public JDFRunList addSepRun(JDFResource fileSpec,
            String sepNames,
            int first,
            int n,
            boolean pageMajor,
            String sep)
    {
        VElement v = new VElement();
        v.add(fileSpec);
        return addSepRun(v, StringUtil.tokenize(sepNames, sep, false), first, n, pageMajor);
    }

    /**
     * addSepRun
     *
     * @param String  fileNames
     * @param String  sepNames
     * @deprecated
     *
     * @return JDFRunList
     */
    @Deprecated
	public JDFRunList addSepRun(String fileNames, String sepNames)
    {
        return addSepRun(fileNames, sepNames, 0, 1, true, JDFConstants.COMMA);
    }

    /**
     * addSepRun
     *
     * @param String  fileNames
     * @param String  sepNames
     * @param int     first
     * @deprecated
     *
     * @return JDFRunList
     */
    @Deprecated
	public JDFRunList addSepRun(String fileNames, String sepNames, int first)
    {
        return addSepRun(fileNames, sepNames, first, 1, true, JDFConstants.COMMA);
    }

    /**
     * addSepRun
     *
     * @param String  fileNames
     * @param String  sepNames
     * @param int     first
     * @param int     n
     * @deprecated
     *
     * @return JDFRunList
     */
    @Deprecated
	public JDFRunList addSepRun(String fileNames, String sepNames, int first, int n)
    {
        return addSepRun(fileNames, sepNames, first, n, true, JDFConstants.COMMA);
    }

    /**
     * addSepRun
     *
     * @param String  fileNames
     * @param String  sepNames
     * @param int     first
     * @param boolean pageMajor
     * @deprecated
     *
     * @return JDFRunList
     */
    @Deprecated
	public JDFRunList addSepRun(String fileNames, String sepNames, int first, boolean pageMajor)
    {
        return addSepRun(fileNames, sepNames, first, 1, pageMajor, JDFConstants.COMMA);
    }

    /**
     * addSepRun
     *
     * @param String  fileNames
     * @param String  sepNames
     * @param boolean pageMajor
     * @deprecated
     *
     * @return JDFRunList
     */
    @Deprecated
	public JDFRunList addSepRun(String fileNames, String sepNames, boolean pageMajor)
    {
        return addSepRun(fileNames, sepNames, 0, 1, pageMajor, JDFConstants.COMMA);
    }

    /**
     * addSepRun
     *
     * @param String  fileNames
     * @param String  sepNames
     * @param String  sep
     * @deprecated
     *
     * @return JDFRunList
     */
    @Deprecated
	public JDFRunList addSepRun(String fileNames, String sepNames, String sep)
    {
        return addSepRun(fileNames, sepNames, 0, 1, true, sep);
    }

    /**
     * addSepRun
     *
     * @param String  fileNames
     * @param String  sepNames
     * @param int     first
     * @param String  sep
     * @deprecated
     *
     * @return JDFRunList
     */
    @Deprecated
	public JDFRunList addSepRun(String fileNames, String sepNames, int first, String sep)
    {
        return addSepRun(fileNames, sepNames, first, 1, true, sep);
    }

    /**
     * addSepRun
     *
     * @param String  fileNames
     * @param String  sepNames
     * @param int     first
     * @param int     n
     * @param String  sep
     * @deprecated
     *
     * @return JDFRunList
     */
    @Deprecated
	public JDFRunList addSepRun(String fileNames, String sepNames, int first, int n, String sep)
    {
        return addSepRun(fileNames, sepNames, first, n, true, sep);
    }

    /**
     * addSepRun
     *
     * @param String  fileNames
     * @param String  sepNames
     * @param int     first
     * @param boolean pageMajor
     * @param String  sep
     * @deprecated
     *
     * @return JDFRunList
     */
    @Deprecated
	public JDFRunList addSepRun(String fileNames,
            String sepNames,
            int first,
            boolean pageMajor,
            String sep)
    {
        return addSepRun(fileNames, sepNames, first, 1, pageMajor, sep);
    }

    /**
     * addSepRun
     *
     * @param String  fileNames
     * @param String  sepNames
     * @param boolean pageMajor
     * @param String  sep
     * @deprecated
     *
     * @return JDFRunList
     */
    @Deprecated
	public JDFRunList addSepRun(String fileNames, String sepNames, boolean pageMajor, String sep)
    {
        return addSepRun(fileNames, sepNames, 0, 1, pageMajor, sep);
    }

    /**
     * addSepRun
     *
     * @param String  fileNames
     * @param String  sepNames
     * @param int     first
     * @param int     n
     * @param boolean pageMajor
     * @param String  sep
     * @deprecated
     *
     * @return JDFRunList
     */
    @Deprecated
	public JDFRunList addSepRun(String fileNames,
            String sepNames,
            int first,
            int n,
            boolean pageMajor,
            String sep)
    {
        return addSepRun(StringUtil.tokenize(fileNames, sep, false),
                StringUtil.tokenize(sepNames, sep, false), first, n, pageMajor);
    }


    /**
     * set RunList/LayoutElement/FileSpec/@URL
     * @param url the url to set
     * @return true if ok
     */
    public boolean setFileURL (String url)
    {
        JDFFileSpec fspec = getCreateLayoutElement().getCreateFileSpec();
        fspec.setMimeURL (url);
        return true;
    }

    /**
     * get RunList/LayoutElement/FileSpec/@URL
     * also evaluate RunList/@directory and concatinate Directory + URL in case 
     * URL is a relative URL
     * @Directory is ignored if URL contains a scheme or is an absolute URL
     * 
     * @return URL if a URL or Directory attribute exists, else null
     */ 
    public String getFileURL() 
    {
        final JDFFileSpec fspec=getFileSpec();
        if(fspec==null)
            return null;
        
        return UrlUtil.getURLWithDirectory(getDirectory(),fspec.getURL());
    }
    
    /**
     * (36) set attribute Pages
     * @param value: the value to set the attribute to
     */
   @Override
public void setPages(JDFIntegerRangeList value)
   {
       super.setPages(value);
       if(value==null)
           return;
       if(value.getElementCount()!=0)
           setNPage(value.getElementCount());
   }


    /**
     * get RunList/LayoutElement/FileSpec/@MimeType
     * @return MIMEType if it exists, else null
     */ 
    public String getFileMimeType() 
    {
        JDFFileSpec fspec=getFileSpec();
        if(fspec==null)
            return null;
        return fspec.getMimeType();
    }

    /**
     * get RunList/LayoutElement/FileSpec
     * @return JDFFileSpec FileSpec if it exists, else null
     */ 
    public JDFFileSpec getFileSpec()
    {        
        final JDFLayoutElement lay = getLayoutElement();        
        if (lay==null)
        {
            return null;
        }        
        return lay.getFileSpec();        
    }   
    /**
     * get a map of VJDFAttributeMap that are sorted by the fileSpec URL key<br>
     * each url key maps the leaves that share the same URL
     * @return fileSpecMap a map of VJDFAttributeMap
     */
    public HashMap getCommonURLFileSpecMap()
    {
        VElement vE = getLeaves(false);
        HashMap fileMap = new HashMap();

        // loop over all leaves of the runlist
        for (int i = 0; i < vE.size(); i++)
        {
            JDFRunList rl = (JDFRunList)vE.elementAt(i);
            String url = rl.getFileURL();
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
                vPart = (VJDFAttributeMap) fileMap.get(url);
                vPart.addElement(rl.getPartMap());
            }
        }

        return fileMap;
    }


    /**
     * get a list of all partition keys that this resource may be implicitly partitioned by
     * e.g. RunIndex for RunList...
     *
     * @return vector of EnumPartIDKey
     */

    @Override
	public Vector getImplicitPartitions()
    {
        Vector<ValuedEnum> v = super.getImplicitPartitions();
        if(v==null)
            v=new Vector<ValuedEnum>();
        v.add(EnumPartIDKey.RunIndex);
        v.add(EnumPartIDKey.DocIndex);
        v.add(EnumPartIDKey.DocRunIndex);
        v.add(EnumPartIDKey.DocSheetIndex);
        v.add(EnumPartIDKey.SetIndex);
        v.add(EnumPartIDKey.PageNumber);
        return v;
    }

    public Iterator getPageIterator()
    {
        return new PageIterator(this);
    }

    ////////////////////////////////////////////////////////
    /**
     * class that abstracts a RunList Partition so that you can efficiently access the
     * File using RunIndex as a marker
     */
    public class JDFRunData
    {
        protected JDFRunList runList;
        protected int runIndex;
        protected int firstIndex;
        protected int lastIndex;
        
        /**
         * copy constructor
         * @param other the JDFRunIndex object to clone
         */
        public JDFRunData(JDFRunData other)
        {
            runList=other.runList;
            runIndex=other.runIndex;
            firstIndex=other.firstIndex;
            lastIndex=other.lastIndex;
        }
        /**
         * null constructor
         *
         */
        protected JDFRunData()
        {
            runList=null;
            runIndex=0;
            firstIndex=0;
            lastIndex=0;
        }
        
        /**
         * get the 0 based page number in the file specified by RunList/@URL
         * @return the page number in the file; -1 if out of range
         */
        public int getPageInFile()
        {
            int page=-1;

            int delta=runIndex-firstIndex;
            if(runList.hasAttribute(AttributeName.PAGES))
            {
                int pages[]=runList.getPages().getIntegerList().getIntArray();
                if(delta>=pages.length)
                    throw new JDFException("getPageInFile: Pages is kaputt");
                page=pages[delta];
            }
            else
            {
                page=runList.getFirstPage()+delta;
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
         * @param firstIndex the firstIndex to set
         */
        public int getFirstIndex()
        {
            return firstIndex;
        }
    }

    /**
     * gets the first logical RunIndex for this partition
     * @return the first RunIndex that this RunList partition specifies
     */
    public int getFirstIndex()
    {
        return getFirstIndex(null);
    }
    /**
     * gets the first logical RunIndex for this partition
     * @return the first RunIndex that this RunList partition specifies
     */
    protected int getFirstIndex(JDFRunData last)
    {
        if(hasAttribute(AttributeName.LOGICALPAGE))
            return getLogicalPage();

        if(!getIsPage())
        {
            KElement e=getParentNode_KElement();
            if(e instanceof JDFRunList)
            {
                return ((JDFRunList)e).getFirstIndex(last);
            }
        }
        JDFRunList rl= (JDFRunList) getElement_KElement(ElementName.RUNLIST, null, 0);
        if(rl!=null && rl.getIsPage())
            return rl.getFirstIndex(last);

        JDFRunList previousRL=(JDFRunList) getPreviousSiblingElement(ElementName.RUNLIST, null);
        if(previousRL==null)
            return 0;
        int offset=0;
        if(last!=null && previousRL==last.runList)
            offset=last.lastIndex;
        else
            offset=previousRL.getLastIndex(last);
        return offset+1;
    }
    /**
     * get the list of RunList Leaves with IsPage=true
     * @return
     */
    public VElement getPageLeaves()
    {
        VElement v=getLeaves(false);
        for(int i=0;i<v.size();i++)
        {
            JDFRunList rl=(JDFRunList) v.elementAt(i);
            boolean bRep=false;
            while(rl!=null && !rl.getIsPage())
            {
                bRep=true;
                rl=(JDFRunList)rl.getParentNode_KElement();
            }
            if(bRep)
                v.set(i, rl);
        }
        v.unify();
        return v;
    }

    /**
     * gets the last logical RunIndex for this partition
     * @return the last RunIndex that this RunList partition specifies
     */
    public int getLastIndex()
    {
        return getLastIndex(null);
    }
    /**
     * gets the last logical RunIndex for this partition
     * @return the last RunIndex that this RunList partition specifies
     */
    protected int getLastIndex(JDFRunData last)
    {
        if(!getIsPage())
        {
            KElement e=getParentNode_KElement();
            if(e instanceof JDFRunList)
            {
                return ((JDFRunList)e).getLastIndex(last);
            }
        }
        JDFRunList rl= (JDFRunList) getElement_KElement(ElementName.RUNLIST, null, -1);
        if(rl!=null && rl.getIsPage())
            return rl.getLastIndex(last);

        int offset=-1;
        if(hasAttribute(AttributeName.LOGICALPAGE))
        {
            offset=getLogicalPage()-1;
        }
        else
        {
            JDFRunList previousRL=(JDFRunList) getPreviousSiblingElement(ElementName.RUNLIST, null);
            if(previousRL!=null)
            {
                if(last!=null && last.runList==previousRL)
                    offset=last.lastIndex;
                else
                    offset=previousRL.getLastIndex(last);
            }
        }
        return offset+getNPage();
    }

    /* (non-Javadoc)
     * @see org.cip4.jdflib.auto.JDFAutoRunList#getNPage()
     */
    @Override
	public int getNPage()
    {
        if(hasAttribute(AttributeName.NPAGE))
            return super.getNPage();
        if(hasAttribute(AttributeName.PAGES))
        {
            JDFIntegerRangeList pages=getPages();
            return pages.getElementCount();
        }
        return 0; // TODO what is the default
    }

    /**
     * get the Partition that corresponds to a given runIndex
     * @param index the runIndex to search for
     * @return JDFRunList the partition that contains this index.
     * use @see getPageInFile to find the correct page
     * 
     * warning blindly calling this from inside a loop may cause performance issues - 
     * use the getPageIterator if you need performance optimized access
     */
    public JDFRunList getIndexPartition(int index)
    {
        VElement leaves = getPageLeaves();
        for(int i=0;i<leaves.size();i++)
        {
            JDFRunList rl=(JDFRunList) leaves.elementAt(i);
            if(rl.getFirstIndex()<=index && rl.getLastIndex()>=index)
            {
                return rl;
            }
        }
        return null;

    }
    /**
     * get the 0 based page number in the specified file
     * @return the page number in the file; -1 if ot of range
     */
    public int getPageInFile(int runIndex)
    {
        JDFRunData ri=new JDFRunData();
        ri.firstIndex=getFirstIndex();
        if(runIndex<ri.firstIndex)
            return -1;
        ri.lastIndex=getLastIndex();
        if(runIndex>ri.lastIndex)
            return -1;
        ri.runIndex=runIndex;
        ri.runList=this;
        return ri.getPageInFile();
    }
    ////////////////////////////////////////////////////////
    /**
     * inner iterator class
     */
    private class PageIterator implements Iterator
    {

        private final JDFRunList rl;
        private int index;
        private final int maxIndex;
        private final JDFRunData[] vRunIndex;
        private int lastIndex;
        /**
         * @param list
         */
        public PageIterator(JDFRunList list)
        {
            lastIndex=0;
            rl=(JDFRunList) list.getResourceRoot();
            index=list.getFirstIndex();
            maxIndex=list.getLastIndex();
            VElement leaves=rl.getPageLeaves();
            vRunIndex=new JDFRunData[leaves.size()];
            JDFRunData last=null;
            for(int i=0;i<leaves.size();i++)
            {
                JDFRunList _rl=(JDFRunList) leaves.elementAt(i);
                final int firstIndex = _rl.getFirstIndex(last);
                final int _lastIndex = firstIndex+_rl.getNPage()-1;
                JDFRunData ri=new JDFRunData();
                ri.runList=_rl;
                ri.firstIndex=firstIndex;
                ri.lastIndex=_lastIndex;
                vRunIndex[i]=ri;
                last=ri;
            }
        }

        /* (non-Javadoc)
         * @see java.util.Iterator#hasNext()
         */
        public boolean hasNext()
        {
            return index<=maxIndex;
        }

        /**
         * 
         * @see java.util.Iterator#next()
         * returns a JDFRunIndex object that refers to the RunList entry and the index within it
         */
        public Object next()
        {
            for(int i=lastIndex;i<vRunIndex.length;i++)
            {
                JDFRunData ri=vRunIndex[i];
                if(ri.firstIndex<=index && ri.lastIndex>=index)
                {
                    JDFRunData ri2=new JDFRunData(ri);
                    ri2.runIndex=index;
                    index++;
                    lastIndex=i;
                    return ri2;
                }
            }
            return null;
        }

        /** (non-Javadoc)
         * don't even dream of removing individual pages in this iterator
         */
        public void remove()
        {
            throw new JDFException("remove not implented");
        }
    }
    ///// End of iterator class /////    
}
