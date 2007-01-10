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
import java.util.Vector;

import org.apache.xerces.dom.CoreDocumentImpl;
import org.cip4.jdflib.auto.JDFAutoRunList;
import org.cip4.jdflib.core.AtrInfoTable;
import org.cip4.jdflib.core.AttributeInfo;
import org.cip4.jdflib.core.AttributeName;
import org.cip4.jdflib.core.ElementName;
import org.cip4.jdflib.core.JDFConstants;
import org.cip4.jdflib.core.VElement;
import org.cip4.jdflib.datatypes.JDFIntegerRangeList;
import org.cip4.jdflib.datatypes.VJDFAttributeMap;
import org.cip4.jdflib.resource.JDFResource;
import org.cip4.jdflib.util.StringUtil;
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
        r.init();
        
        JDFIntegerRangeList irl = new JDFIntegerRangeList();
        irl.append(first, last);
        r.setPages(irl);
        JDFLayoutElement loe = (JDFLayoutElement) r.appendElement(ElementName.LAYOUTELEMENT, JDFConstants.EMPTYSTRING); 
        loe.setFileName(fileName);
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
    public JDFRunList addPDF(String fileName, int first)
    {
        return addPDF(fileName, first, -1);
    }
    
    /**
     * addPDF
     *
     * @param String fileName
     * @param int first
     * @param int last
     *
     * @return JDFRunList
     */
    public JDFRunList addPDF(String fileName, int first, int last)
    {
        JDFRunList r = addRun(fileName, first, last);
        JDFFileSpec fs=r.getLayoutElement().getFileSpec();
        fs.setMimeType("application/PDF");
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
    public JDFRunList addSepRun(Vector fileNames, Vector sepNames, boolean pageMajor)
    {
        return addSepRun(fileNames, sepNames, 0, 1, pageMajor);
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
     */
    public JDFRunList addSepRun(Vector fileNames,
            Vector sepNames,
            int first,int n, boolean pageMajor)
    {
        JDFRunList r = (JDFRunList) addPartition (JDFResource.EnumPartIDKey.Run, "Run" + uniqueID(0));
        int siz = fileNames.size();
        r.init();
        r.setNPage(n);
        r.setIsPage(true);
        
        for (int i = 0; i < sepNames.size(); i++)
        {
            JDFRunList rs = (JDFRunList)
            r.addPartition(JDFResource.EnumPartIDKey.Separation, sepNames.elementAt(i).toString());
            JDFLayoutElement lo = rs.appendLayoutElement();
            lo.setFileName((String)fileNames.elementAt(Math.min(i, siz - 1)));
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
    public JDFRunList addSepRun(VElement fileSpec, Vector sepNames, int first, int n, boolean pageMajor)
    {
        JDFRunList r = (JDFRunList) addPartition(JDFResource.EnumPartIDKey.Run, "Run" + uniqueID(0)); //Test TBD
        int siz = fileSpec.size();
        r.init();
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
        JDFLayoutElement lay = getCreateLayoutElement();
        
        if (lay == null)
        {
            return false;
        }
        
        JDFFileSpec fspec = lay.getCreateFileSpec();
        
        if (fspec == null)
        {
            return false;
        }
        
        fspec.setURL (url);
        
        return true;
    }
    
    /**
     * get RunList/LayoutElement/FileSpec/@URL
     * @return URL if it exists, else null
     */ 
    public String getFileURL() 
    {
        JDFFileSpec fspec=getFileSpec();
        if(fspec==null)
            return null;
        return fspec.getURL();
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
    
    public Vector getImplicitPartitions()
    {
        Vector v = super.getImplicitPartitions();
        if(v==null)
            v=new Vector();
        v.add(EnumPartIDKey.RunIndex);
        v.add(EnumPartIDKey.DocIndex);
        v.add(EnumPartIDKey.DocRunIndex);
        v.add(EnumPartIDKey.DocSheetIndex);
        v.add(EnumPartIDKey.SetIndex);
        v.add(EnumPartIDKey.PageNumber);
               
        return v;
    }
}
