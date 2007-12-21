/*
 *
 * The CIP4 Software License, Version 1.0
 *
 *
 * Copyright (c) 2001-2007 The International Cooperation for the Integration of 
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
 * Created on Jul 5, 2006, 11:45:44 AM
 * org.cip4.jdflib.util.MimeUtil.java
 * Project Name: mimeutil
 */
package org.cip4.jdflib.util;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Vector;

import javax.activation.DataHandler;
import javax.activation.DataSource;
import javax.activation.FileDataSource;
import javax.mail.BodyPart;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Multipart;
import javax.mail.Session;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;
import javax.mail.util.SharedFileInputStream;

import org.apache.commons.io.IOUtils;
import org.cip4.jdflib.core.AttributeName;
import org.cip4.jdflib.core.ElementName;
import org.cip4.jdflib.core.JDFConstants;
import org.cip4.jdflib.core.JDFDoc;
import org.cip4.jdflib.core.JDFElement;
import org.cip4.jdflib.core.JDFParser;
import org.cip4.jdflib.core.KElement;
import org.cip4.jdflib.core.VElement;
import org.cip4.jdflib.core.XMLDoc;
import org.cip4.jdflib.datatypes.JDFAttributeMap;
import org.cip4.jdflib.jmf.JDFCommand;
import org.cip4.jdflib.jmf.JDFJMF;
import org.cip4.jdflib.jmf.JDFResponse;
import org.cip4.jdflib.node.JDFNode;

/**
 * MIME utilities for reading and writing MIME/MULTIPART/RELATED streams
 *
 * @author Markus Nyman, (markus.cip4@myman.se)
 * 
 */
public class MimeUtil 
{

    /**
     * data source for binary files
     * 
     * @author prosirai
     *
     */
    public class ByteArrayDataSource implements DataSource
    {
        String contenType;
        ByteArrayIOStream ioStream;

        /**
         * create a data source from a byte array
         * @param ioStream the ByteArrayIOStream to use
         * @param _contentType the content type of the contents
         */
        public ByteArrayDataSource(ByteArrayIOStream _ioStream, String _contentType)
        {
            contenType=_contentType;
            ioStream=_ioStream;
        }
        /* (non-Javadoc)
         * @see javax.activation.DataSource#getContentType()
         */
        public String getContentType()
        {
            return contenType;
        }

        /* (non-Javadoc)
         * @see javax.activation.DataSource#getInputStream()
         */
        public InputStream getInputStream() 
        {
            return ioStream.getInputStream();
        }

        /* (non-Javadoc)
         * @see javax.activation.DataSource#getName()
         */
        public String getName()
        {
            // not needed 
            return null;
        }

        /* (non-Javadoc)
         * @see javax.activation.DataSource#getOutputStream()
         */
        public OutputStream getOutputStream()
        {
            return ioStream;
        }

    }
    /**
     * commonly used strings
     */
    public static final String CONTENT_TYPE = "Content-Type";
    public static final String CONTENT_ID = "Content-ID";
    public static final String TEXT_XML =JDFConstants.MIME_TEXTXML;
    public static final String TEXT_PLAIN ="text/plain";
    public static final String TEXT_UNKNOWN =JDFConstants.MIME_TEXTUNKNOWN;
    public static final String VND_JDF =JDFConstants.MIME_JDF;
    public static final String VND_JMF =JDFConstants.MIME_JMF;
    public static final String MULTIPART_RELATED = "multipart/related";
    public static final String POST = "POST";
    public static final String GET = "GET";


    //private static Logger log = Logger.getLogger(MimeUtil.class);
    /**
     * 
     */

    public static void setContentID(BodyPart bp, String cid) 
    {
        if(cid==null)
            return;

        try
        {
            bp.setHeader(CONTENT_ID, "<" + urlToCid(cid).substring(4)  + ">");
        }
        catch (MessagingException x)
        {
//          nop
        } 
    }


    /**
     * set the filename header of a bodypart to a string
     * 
     * @param bp the bodypart
     * @param path the path to set
     * 
     */
    public static void setFileName(BodyPart bp, String path) 
    {
        if(path==null)
            return;
        try
        {
            bp.setFileName(new File(path).getName());
        }
        catch (MessagingException x)
        {
            // nop           
        }
    }

    /**
     * get the filename header of a bodypart a string
     * if no file name is set, a unique filename is generated from cid and content type
     * 
     * @param bp the bodypart
     * 
     * @return the file name
     */
    public static String getFileName(BodyPart bp) 
    {
        String s=null;
        try
        {
            s=bp.getFileName();
            if(s!=null)
                return s;
        }
        catch (MessagingException x)
        {
            // nop
        }
        // TODO handle extensions here
        s=getContentID(bp);
        return s;        
    }

    /**
     * get the ContentID header of a bodypart a string
     * @param bp the bodypart
     * 
     * @return the cid, null if there was an error
     */
    public static String getContentID(BodyPart bp) 
    {
        String[] cids=null;
        try
        {
            cids = bp.getHeader(CONTENT_ID);
        }
        catch (MessagingException e)
        {
            return null;
        }
        String s=StringUtil.setvString(cids, null, null, null);
        if(s==null)
            return s;

        return urlToCid(s).substring(4);
    }


    /**
     * Extracts all the parts of a multipart MIME message and returns
     * an array of InputStream for each of the separate MIME parts.
     * 
     * @param mimeStream
     * @return
     */
    public static BodyPart[] extractMultipartMime(InputStream mimeStream) {
        BodyPart[] bodyParts = null;
        Multipart mp = getMultiPart(mimeStream);
        bodyParts = getBodyParts(mp);
        return bodyParts;
    }


    /**
     * get all the parts of of a multipart an
     * @param mp the multiPart to extract
     * 
     * @return the array of parts, null if snafu...
     */
    public static BodyPart[] getBodyParts(Multipart mp) 
    {
        Vector<BodyPart> v=new Vector<BodyPart>();
        try
        {
            for (int i = 0; true; i++ ) 
            {
                BodyPart bp = mp.getBodyPart(i);
                v.add(bp);
            }

        }
        catch (MessagingException m)
        {
            return null;
        }
        // this may seem messy, but getCount() can be very costly since it requires a complete mime parse.
        // simply getting the next reduces the time by a factor 2, since only one linear parse is required
        catch (ArrayIndexOutOfBoundsException m)
        {
            if(v.size()==0)
                return null;
            BodyPart[] ret=new BodyPart[v.size()];
            ret=v.toArray(ret);
            return ret;
        }
    }

    /**
     * get the MIME BodyPart from a multiPart package with a given cid
     * 
     * @param mp the multipart package to search in
     * @param cid the cid of the requested bodypart
     * 
     * @return BodyPart the matching BodyPart, null if none is found
     */
    public static BodyPart getPartByCID(Multipart mp, String cid)
    {
        try {
            for (int i = 0; true; i++ ) {
                BodyPart bp = mp.getBodyPart(i);
                if(matchesCID(bp,cid))
                    return bp;
            }
        } 
        catch (MessagingException e) 
        {
            //log.error("MessagingException: ", e);
            return null;
        } 
        catch (ArrayIndexOutOfBoundsException e) 
        {
            //log.error("MessagingException: ", e);
            return null;
        } 
    }

    /**
     * get the MIME BodyPart from a multiPart package with a given cid
     * create one if it does not exist;
     * 
     * @param mp the multipart package to search in
     * @param cid the cid of the requested bodypart
     * 
     * @return BodyPart the matching BodyPart, null if none is found
     */
    public static BodyPart getCreatePartByCID(Multipart mp, String cid)
    {
        BodyPart bp=getPartByCID(mp, cid);
        if(bp!=null)
            return bp;

        bp= new MimeBodyPart();
        try
        {
            setContentID(bp, cid);
            mp.addBodyPart(bp);
        }
        catch (MessagingException x)
        {
            bp=null;
        }
        return bp;
    }

    /**
     * get the JDF Doc from a given body part
     * 
     * @param bp the BodyPart to search in
     * @return JDFDoc the parsed xml JDFDoc, null if bp does not contain xml
     */
    public static JDFDoc getJDFDoc(BodyPart bp)
    {
        if(bp==null)
            return null;

        try
        {
            String mimeType=bp.getContentType();
            if(!isJDFMimeType(mimeType))
                return null;
            InputStream is=bp.getInputStream();
            JDFParser p=new JDFParser();
            JDFDoc doc=p.parseStream(is);
            if(doc!=null)
                doc.setBodyPart(bp);
            return doc;
        }
        catch (IOException e)
        {
            return null; // snafu
        }
        catch (MessagingException e)
        {
            return null; // snafu
        }
    }

    /**
     * check if a BodyPart matches a given cid
     * @param bp the bodyPart to check
     * @param cid the cid string  any '<' '>' or 'cid:' prefixes are removed
     *  if null, anything matches
     *  
     * @return true if this bp matches the cid
     */
    public static boolean matchesCID(BodyPart bp, String cid) 
    {
        if(cid==null)
            return true; // wildcard

        if(cid.startsWith("<"))
            cid=cid.substring(1);
        if(cid.toLowerCase().startsWith("cid:"))
            cid=cid.substring(4);
        if(cid.endsWith(">"))
            cid=cid.substring(0,cid.length()-1);

        String s=getContentID(bp);
        if(s==null)
            return false;


        return cid.equalsIgnoreCase(s);
    }

    /**
     * helper to create a root multipart from a file
     * 
     * @param fileName the name of the file used as input
     * 
     * @return MultiPart the Multipart that represents the root mime, 
     * null if something went wrong
     */
    public static Multipart getMultiPart(String fileName)
    {
        File f=new File(fileName);
        try
        {
            SharedFileInputStream fis = new SharedFileInputStream(f);

            Multipart mp=MimeUtil.getMultiPart(fis);
            return mp;
        }
        catch (FileNotFoundException e)
        {
            return null;
        }
        catch (IOException e)
        {
            return null;
        }
    }
    /**
     * create a root multipart from an input stream
     * 
     * @param mimeStream the input stream
     * 
     * @return MultiPart the Multipart that represents the root mime, 
     * null if something went wrong
     */
    public static Multipart getMultiPart(InputStream mimeStream)
    {
        if(mimeStream==null)
            return null;

        try
        {
//          ManagedMemoryDataSource
            // TODO rethink memory management for large files
            //SharedInputStream sis=new Shared
            Message mimeMessage = new MimeMessage(null, mimeStream);

            Multipart mp = new MimeMultipart(mimeMessage.getDataHandler().getDataSource());
            return mp;
        }
        catch (MessagingException e)
        {
            return null;
        }
    }

    /**
     * checkst whether the mime type corresponds to one of
     *      "application/vnd.cip4-jdf+xml";
     * "application/vnd.cip4-jmf+xml";
     * "text/xml";
     *
     * @param mimeType the string to test
     * @return true if matches
     */
    public static boolean isJDFMimeType(String mimeType)
    {
        if(mimeType==null)
            return false;
        int posSemicolon=mimeType.indexOf(";");
        if(posSemicolon>0)
            mimeType=mimeType.substring(0, posSemicolon);

        return  JDFConstants.MIME_JDF.equalsIgnoreCase(mimeType)  ||
        JDFConstants.MIME_JMF.equalsIgnoreCase(mimeType)  ||
        JDFConstants.MIME_TEXTXML.equalsIgnoreCase(mimeType); 
    }


//  public static MimeMessage parseMime(InputStream mimeStream) {
//  //MimeMessage
//  return null;
//  }

//  public static boolean isMultipart(Message message) 
//  throws MessagingException {
//  return message.isMimeType("multipart/*");		
//  }

    /**
     * build a MIME package that contains all references in all FileSpecs of a given JDFDoc
     * the doc is modified so that all URLs are cids
     * 
     * @param docJMF the JDFDoc representation of the JMF that references the jdf to package,
     * if null only the jdf is packaged note that the URL of docJDF must already be specified as a CID
     * @param docJDF the JDFDoc representation of the JDF to package
     * 
     * @return a Message representing the resulting MIME package, null if an error occured
     */
    static public Multipart buildMimePackage(JDFDoc docJMF, JDFDoc docJDF)  
    {
        // Create a MIME package
        Message message = new MimeMessage((Session)null);
        Multipart multipart = new MimeMultipart("related"); // JDF: multipart/related

        String cid = null;
        if (docJDF != null)
        {
			String originalFileName = docJDF.getOriginalFileName();
			if (KElement.isWildCard(originalFileName))
				originalFileName = "TheJDF.jdf";

			cid = urlToCid(originalFileName);
        }
        
        if(docJMF!=null && cid!=null)
        {
            KElement e=docJMF.getRoot();
            VElement v=e.getChildrenByTagName(null, null, new JDFAttributeMap(AttributeName.URL,"*"), false, false, 0);
            int siz=v==null ? 0 : v.size();
            for(int i=0;i<siz;i++)
                v.item(i).setAttribute(AttributeName.URL, cid);
            updateXMLMultipart(multipart,docJMF,null);
        }

        extendMultipart(multipart, docJDF, cid);
        // Put parts in message
        try
        {
            message.setContent(multipart);
        }
        catch (MessagingException x)
        {
            return null;
        }
        return multipart;
    }

    /**
     * Adds a JDF document to a multipart. Any files referenced by the JDF
     * document using FileSpec/@URL are also included in the multipart.
     * 
     * @param multipart
     *            the multipart to add the JDF document to
     * @param docJDF
     *            the JDF document
     * @param cid
     *            the CID the JDF document should have in the multipart
     * @return the number of files added to the multipart
     */
    private static int extendMultipart(Multipart multipart, JDFDoc docJDF, String cid) {
        int n = 0;
        if (docJDF == null) {
            return 0;
        }
        // Get all FileSpec elements
        final KElement e = docJDF.getRoot();
        final VElement fileSpecs = e.getChildrenByTagName(ElementName.FILESPEC, null, new JDFAttributeMap(AttributeName.URL, "*"), false, false, 0);
        final int vSize = fileSpecs == null ? 0 : fileSpecs.size();
        String[] urlStrings = listURLs(fileSpecs);
        for (int i = 0; i < urlStrings.length; i++) {
            if (urlStrings[i] != null) {
                // Convert URL to CID and update FileSpec
                File f = UrlUtil.urlToFile(urlStrings[i]);
                if (f != null && !f.isAbsolute()) {
                    // Resolve relative URLs
                    if (docJDF.getOriginalFileName() != null) {
                        File jdfFile = new File(docJDF.getOriginalFileName());
                        f = new File(jdfFile.getParent(), f.getPath());
                        try {
                            urlStrings[i] = f.toURL().toExternalForm();
                        } catch (MalformedURLException e1) {	
                            // nop
                        }
                    }
                }
                if (f == null || !f.canRead()) {
                    // Ignore unreadable files
                    urlStrings[i] = null;
                } else {
                    // Update FileSpec's URL
                    fileSpecs.item(i).setAttribute(AttributeName.URL, urlToCid(urlStrings[i]), null);
                }
                // Set duplicate URLs to null so that the file is only added once to multipart  
                for (int j = 0; j < i; j++) {
                    if (urlStrings[i] != null && urlStrings[i].equals(urlStrings[j])) {
                        urlStrings[i] = null;
                    }
                }
            }
        }
        updateXMLMultipart(multipart, docJDF, cid);

        // add a new body part for each url
        for (int i = 0; i < vSize; i++) {
            final String urlString = urlStrings[i];
            if (urlString != null) {
                try {
                    DataSource dataSrc = null;
                    File f = UrlUtil.urlToFile(urlString);
                    if (f != null && f.canRead()) {
                        dataSrc = new FileDataSource(f);
                    }
                    if (dataSrc == null) {
                        continue; // no data source
                    }
                    BodyPart messageBodyPart = new MimeBodyPart();
                    messageBodyPart.setDataHandler(new DataHandler(dataSrc));

                    setFileName(messageBodyPart, f == null ? null : f.getAbsolutePath());
                    // messageBodyPart.setHeader("Content-Type",
                    // JMFServlet.JDF_CONTENT_TYPE); // JDF:
                    // application/vnd.cip4-jdf+xml
                    setContentID(messageBodyPart, urlString);
                    multipart.addBodyPart(messageBodyPart);
                    n++;
                } catch (MessagingException e1) {
                    // nop
                }
            }
        }
        return n;
    }

    /**
     * Returns the values of the <i>URL</i> attribute of each element in the
     * input list.
     * 
     * @param fileSpecs
     *            a list of elements with <i>URL</i> attributes
     * @return an array containing the value of the <i>URL</i> attribute of
     *         each element in the input list. The order of values in the
     *         returned array corresponds to the order of the elements in the
     *         input list.
     */
    private static String[] listURLs(VElement fileSpecs) {
        final int vSize = fileSpecs == null ? 0 : fileSpecs.size();
        String[] urlStrings = new String[vSize];
        for (int i = 0; i < vSize; i++) {
            urlStrings[i] = fileSpecs.item(i).getAttribute(AttributeName.URL, null, null);
        }
        return urlStrings;
    }

    private static String urlToCid(String urlString)
    {
        if(urlString==null)
            return null;
        if(urlString.startsWith("<"))
            urlString=urlString.substring(1);
        if(urlString.toLowerCase().startsWith("cid:"))
            urlString=urlString.substring(4);
        if(urlString.endsWith(">"))
            urlString=urlString.substring(0,urlString.length()-1);

        return "cid:"+new File(urlString).getName(); // 
    }

    /**
     * Builds a MIME package.
     * 
     * @param vXMLDocs
     *            the Vector of XMLDoc representing the JMF and JDFs to be
     *            stored as the first part of the package t
     * @return a Message representing the resulting MIME package, null if an
     *         error occured
     */
    static public Multipart buildMimePackage(Vector vXMLDocs)  
    {
        if(vXMLDocs==null || vXMLDocs.size()==0)
            return null;

        // Create a MIME package
        Message message = new MimeMessage((Session)null);
        Multipart multipart = new MimeMultipart("related"); // JDF: multipart/related
        // Add other body parts
        final int imax=vXMLDocs.size();
        for(int i=0; i<imax; i++) {
            XMLDoc d1=(XMLDoc) vXMLDocs.elementAt(i);
            updateXMLMultipart(multipart, d1, null);
        }
        // Put parts in message
        try
        {
            message.setContent(multipart);
        }
        catch (MessagingException x)
        {
            return null;
        }

        return multipart;
    }

    public static BodyPart updateXMLMultipart(Multipart multipart, XMLDoc xmlDoc, String cid)
    {
        if(xmlDoc==null)
            return null;
        String originalFileName=xmlDoc.getOriginalFileName();
        if(cid==null)
            cid=originalFileName;
        if(cid==null)
        {
            final KElement root = xmlDoc.getRoot();
            cid="CID_"+((root instanceof JDFNode && root.hasAttribute(AttributeName.ID)) ? 
                    ((JDFNode)root).getID() : 
                    JDFElement.uniqueID(0));
           
        }

        BodyPart messageBodyPart=getCreatePartByCID(multipart, cid);
        try
        {
            setFileName(messageBodyPart,originalFileName);
            setContent(messageBodyPart, xmlDoc);
            setContentID(messageBodyPart,cid);
        }
        catch (MessagingException x)
        {
            // skip this one
        }
        catch (IOException x)
        {
            // skip this one
        }

        return messageBodyPart;
    }


    /**
     * sets the content of a bodypart to the xmlDoc - correctly handling non-ascii features and setting the 
     * correct content type
     * 
     * @param messageBodyPart the BodyPart to fill
     * @param xmlDoc the xmlDoc to fill in
     * @throws MessagingException
     */
    public static void setContent(BodyPart messageBodyPart, XMLDoc xmlDoc) throws MessagingException, IOException
    {
        if(messageBodyPart==null || xmlDoc==null)
            throw new MessagingException("null parameters in setContent");

        //TODO better performing solution for multibyte this quick hack makes quite a few copies...
        ByteArrayIOStream ios=new ByteArrayIOStream();
        xmlDoc.write2Stream(ios,0,true);
        ByteArrayDataSource ds=new MimeUtil().new ByteArrayDataSource(ios,"text/xml");

        messageBodyPart.setDataHandler(new DataHandler(ds));
        xmlDoc.setBodyPart(messageBodyPart);
        final KElement root = xmlDoc.getRoot();
        if(root instanceof JDFJMF)
        {
            messageBodyPart.setHeader(CONTENT_TYPE, JDFConstants.MIME_JMF); // JDF: application/vnd.cip4-jmf+xml
        }
        else if(root instanceof JDFNode)
        {
            messageBodyPart.setHeader(CONTENT_TYPE, JDFConstants.MIME_JDF); // JDF: application/vnd.cip4-jmf+xml
        } 

    }

    /////////////////////////////////////////////////////////////////////////////////////////////

    /**
     * write a Multipart to an output URL 
     * File: and http: are currently supported
     * Use HttpURLConnection.getInputStream() to retrieve the http response
     * 
     * @param mp the mime MultiPart to write
     * @param strUrl the URL to write to
     * 
     * @return {@link HttpURLConnection} the opened http connection, null in case of error or file
     * 
     * @throws IOException 
     * @throws MessagingException 
     */
    public static HttpURLConnection writeToURL(Multipart mp, String strUrl) throws IOException, MessagingException 
    {
        URL url=new URL(strUrl);
        if("File".equalsIgnoreCase(url.getProtocol()))
        {
            writeToFile(mp, UrlUtil.urlToFile(strUrl).getAbsolutePath());
            return null;
        }
        else // assume http
        {
            HttpURLConnection httpURLconnection = (HttpURLConnection) url.openConnection();
            httpURLconnection.setRequestMethod(POST);
            httpURLconnection.setRequestProperty("Connection", "close");
            String contentType = mp.getContentType();
            contentType=StringUtil.token(contentType, 0, "\r");
            contentType=StringUtil.token(contentType, 0, "\n");
            httpURLconnection.setRequestProperty(CONTENT_TYPE,contentType );
            httpURLconnection.setDoOutput(true);

            httpURLconnection.setChunkedStreamingMode(10000);
            final OutputStream out= httpURLconnection.getOutputStream();

            writeToStream(mp, out);
            
            return httpURLconnection;   
        }
    }    
    
    /**
     * submit  a multipart file to a queue
     * @param mp
     * @param strUrl
     * @return
     * @throws IOException
     * @throws MessagingException
     */
    public static JDFDoc writeToQueue(JDFDoc docJMF, JDFDoc docJDF, String strUrl) throws IOException, MessagingException
    {
        Multipart mp=buildMimePackage(docJMF, docJDF);
        HttpURLConnection uc=writeToURL(mp, strUrl);
        if(uc==null)
            return null; // file
        int rc=uc.getResponseCode();
        if(rc==200)
        {
            final InputStream inputStream = uc.getInputStream();
            BufferedInputStream bis=new BufferedInputStream(inputStream);
            bis.mark(100000);
            Multipart mpRet=getMultiPart(bis);
            if(mpRet!=null)
            {
                try{
                    BodyPart bp = mpRet.getBodyPart(0);
                    return getJDFDoc(bp);
                }
                catch (MessagingException e) {
                    // nop - try simple doc
                }
            }
            bis.reset();
            JDFDoc d=new JDFParser().parseStream(bis);
            if(d!=null)
                return d;
        }       
        JDFCommand c=docJMF.getJMFRoot().getCommand(0);
       final JDFJMF respJMF = c.createResponse();
       JDFResponse r=respJMF.getResponse(0);
       r.setErrorText("Invalid http response - RC="+rc);
       r.setReturnCode(3); // TODO correct rcs
       return respJMF.getOwnerDocument_JDFElement();       
    }
    /**
     * write a Multipart to an output file
     * 
     * @param mp the mime MultiPart to write
     * @param outStream the existing output stream
     * 
     * @throws IOException
     * @throws MessagingException
     */
    public static File writeToFile(Multipart m, String fileName) 
    {
        final File file = new File(fileName);
        try
        {
            FileOutputStream fos = new FileOutputStream(file);
            writeToStream(m, fos);
            return file;
        }
        catch (FileNotFoundException e)
        {
            return null;
        }
        catch (IOException e)
        {
            return null;
        }
        catch (MessagingException e)
        {
            return null;
        }
    }

    /**
     * write a Multipart to a Stream
     * 
     * @param mp the mime MultiPart to write
     * @param outStream the existing output stream, 
     * note that a buffered output stream is created in case outStream is unbuffered
     * 
     * @throws IOException
     * @throws MessagingException
     */
    public static void writeToStream(Multipart m, OutputStream outStream) throws IOException, MessagingException 
    {
        MimeMessage mm=new MimeMessage((Session)null);
        mm.setContent(m);
        
        // buffers are good - the encoders decoders otherwise hit stream read/write once per byte...
        if(!(outStream instanceof BufferedOutputStream))
            outStream=new BufferedOutputStream(outStream);
        mm.writeTo(outStream);
        outStream.flush();
        outStream.close();
    }

    /**
     * write a Message to a directory
     * 
     * @param mp the mime Message to write
     * @param directory the directory to use as '.' for writing the mime parts
     * @throws MessagingException 
     * 
     * @throws IOException
     * @throws MessagingException
     */
    public static void writeToDir(Multipart mp, File directory) throws MessagingException, IOException  
    {
        boolean exists = directory.exists();
        if(!exists)
            exists = directory.mkdir();

        if(!exists)
            throw new FileNotFoundException();

        if(!directory.canWrite())
            throw new IOException();

        int parts = mp.getCount();
        for (int i = 0; i<parts; i++ ) {
            BodyPart bp = mp.getBodyPart(i);
            writeBodyPartToFile(bp, directory);
            // TODO update urls to the new file values
        }
    }


    /**
     * @param bp
     * @param directory
     * @throws MessagingException 
     * @throws IOException 
     */
    public static void writeBodyPartToFile(BodyPart bp, File directory) throws IOException, MessagingException
    {
        boolean exists = directory.exists();
        if(!exists)
            exists = directory.mkdir();

        if(!exists)
            throw new FileNotFoundException();

        String fileName=getFileName(bp);
        File outFile=new File(directory.getPath()+File.separator+fileName);
        BufferedOutputStream fos=new BufferedOutputStream(new FileOutputStream(outFile));
        InputStream ins=bp.getInputStream();
        IOUtils.copy(ins,fos);
        fos.flush();
        fos.close();
    }


    /**
     * gets the JMF document of a submitqueueentry or returnqueuentry 
     * and the attached jdf document
     *  
     * @param bp the array of body parts to search
     * @return two JDFDocs: bp[0] is the jmf, bp[1] is the jdf
     * null if the bp does not contain a jmf and at least one jdf
     */
    public static JDFDoc[] getJMFSubmission(Multipart mp)
    {
        BodyPart bp[]=getBodyParts(mp);
        if(bp==null || bp.length<2)
            return null;
        JDFDoc jmf=getJDFDoc(bp[0]);
        if(jmf==null || jmf.getJMFRoot()==null)
            return null;
        JDFJMF jmfRoot=jmf.getJMFRoot();
        String subURL=jmfRoot.getSubmissionURL();

        if(subURL==null)
            return null;
        BodyPart bpJDF=getPartByCID(mp, subURL);
        JDFDoc docs[]=new JDFDoc[2];
        docs[0]=jmf;
        docs[1]=getJDFDoc(bpJDF);
        if(docs[1]==null)
            return null;
        return docs;
    }
}
