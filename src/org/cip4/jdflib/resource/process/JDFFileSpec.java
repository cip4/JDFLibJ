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
 * ==========================================================================
 * class JDFFileSpec
 * ==========================================================================
 * @COPYRIGHT Heidelberger Druckmaschinen AG, 1999-2001 ALL RIGHTS RESERVED
 * @Author: sabjon@topmail.de    using a code generator 
 * Warning! very preliminary test version. 
 * Interface subject to change without prior notice! 
 */

package org.cip4.jdflib.resource.process;

import java.io.File;
import java.io.InputStream;
import java.util.HashMap;

import org.apache.xerces.dom.CoreDocumentImpl;
import org.cip4.jdflib.auto.JDFAutoFileSpec;
import org.cip4.jdflib.core.JDFConstants;
import org.cip4.jdflib.util.UrlUtil;
import org.w3c.dom.DOMException;


public class JDFFileSpec extends JDFAutoFileSpec
{
    private static final long serialVersionUID = 1L;
    private static HashMap mimeMap=null;

    /**
     * Constructor for JDFFileSpec
     * @param ownerDocument
     * @param qualifiedName
     * @throws DOMException
     */
     public JDFFileSpec(
        CoreDocumentImpl myOwnerDocument,
        String qualifiedName)
        throws DOMException
    {
        super(myOwnerDocument, qualifiedName);
    }


    /**
     * Constructor for JDFFileSpec
     * @param ownerDocument
     * @param namespaceURI
     * @param qualifiedName
     * @throws DOMException
     */
    public JDFFileSpec(
        CoreDocumentImpl myOwnerDocument,
        String myNamespaceURI,
        String qualifiedName)
         throws DOMException
    {
        super(myOwnerDocument, myNamespaceURI, qualifiedName);
    }

    /**
     * Constructor for JDFFileSpec
     * @param ownerDocument
     * @param namespaceURI
     * @param qualifiedName
     * @param localName
     * @throws DOMException
     */
    public JDFFileSpec(
        CoreDocumentImpl myOwnerDocument,
        String myNamespaceURI,
        String qualifiedName,
        String myLocalName)
        throws DOMException
    {
        super(myOwnerDocument, myNamespaceURI, qualifiedName, myLocalName);
    }

    @Override
	public String toString()
    {
        return "JDFFileSpec[  --> " + super.toString() + " ]";
    }
    
    /**
     * sets the URL attribute to an absolute file IRL (internationalized)
     * 
     * @param f the file to set the URL to
     * @param boolean bEscape128 if true, escape chars>128 (URL) else don't escape (IRL)
     */
    public void setAbsoluteFileURL(File f, boolean bEscape128)
    {
        final String s=UrlUtil.fileToUrl(f, bEscape128);
        setMimeURL(s);
    }
    
    /**
     * sets the URL attribute to an absolute file IRL (internationalized)
     * 
     * @param f the file to set the URL to
     * @param baseDir the File representing the relative location. if null use current working dir
     * @param boolean bEscape128 if true, escape chars>128 (URL) else don't escape (IRL)
     */
    public void setRelativeURL(File f, File baseDir, boolean bEscape128)
    {
        final String s=UrlUtil.getRelativeURL(f, baseDir, bEscape128);
        setMimeURL(s);
    }
    ///////////////////////////////////////////////////////////////////

    /**
     * get the input stream that reads from URL
     * @return InputStream the input stream that the url points to, null if the url is inaccessible
     */
    public InputStream getURLInputStream()
    {
        String url=getURL();
        if(url.equals(JDFConstants.EMPTYSTRING))
            return null;
        return UrlUtil.getURLInputStream(getURL(),getOwnerDocument_KElement().getBodyPart());
    }


    /**
     * sets URL and MimeType by matching the extensions
     * @param url the url to set URL
     */
    public void setMimeURL(String url)
    {
        setURL(url);
        setMimeType(getMimeTypeFromURL(url));        
    }


    /**
     * generates the correct MIMEType for a given URL and sets it
     * @param url
     */
    public static String getMimeTypeFromURL(String url)
    {
        if(mimeMap==null)
        {
            mimeMap=new HashMap();
            mimeMap.put("pdf", "application/pdf");
            mimeMap.put("ps", "application/postscript");
            
            mimeMap.put("ppf", "application/vnd.cip3-ppf");
            mimeMap.put("ppml", "application/vnd.podi-ppml+xml");
            mimeMap.put("jdf", "application/vnd.cip4-jdf+xml");
            mimeMap.put("jmf", "application/vnd.cip4-jmf+xml");
            
            mimeMap.put("xml", "text/xml");
            
            mimeMap.put("jpg", "image/jpeg");
            mimeMap.put("jpeg", "image/jpeg");
            mimeMap.put("tif", "image/tiff");
            mimeMap.put("tiff", "image/tiff");
        }
        String extension=UrlUtil.extension(url);
        return extension==null ? null : (String) mimeMap.get(extension.toLowerCase());
     }
}
