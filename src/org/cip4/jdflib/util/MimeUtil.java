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
 * Created on Jul 5, 2006, 11:45:44 AM
 * org.cip4.jdflib.util.MimeUtil.java
 * Project Name: mimeutil
 */
package org.cip4.jdflib.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Vector;

import javax.activation.DataHandler;
import javax.activation.URLDataSource;
import javax.mail.BodyPart;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Multipart;
import javax.mail.Session;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;

import org.apache.commons.io.IOUtils;
import org.cip4.jdflib.core.AttributeName;
import org.cip4.jdflib.core.JDFConstants;
import org.cip4.jdflib.core.JDFDoc;
import org.cip4.jdflib.core.JDFParser;
import org.cip4.jdflib.core.KElement;
import org.cip4.jdflib.core.VElement;
import org.cip4.jdflib.core.XMLDoc;
import org.cip4.jdflib.datatypes.JDFAttributeMap;

/**
 * TODO JAVADOC
 *
 * @author Markus Nyman, (markus.cip4@myman.se)
 * 
 */
public class MimeUtil {

    public static final String CONTENT_TYPE = "Content-Type";
    public static final String CONTENT_ID = "Content-ID";


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
// nop
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
            s=null;
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
        try {
            Multipart mp = getMultiPart(mimeStream);
            int parts = mp.getCount();
            bodyParts = new BodyPart[parts];
            for (int i = 0; i<parts; i++ ) {
                //log.debug("Partno:" + i);
                BodyPart bp = mp.getBodyPart(i);
                bodyParts[i] = bp;
            }
        } 
        catch (MessagingException e) 
        {
            //log.error("MessagingException: ", e);
            return null;
        } 

        try {
            mimeStream.reset();
        } catch (IOException e) {
            // tries to reset the InputStream
            //log.error("Couldn´t reset stream.", e);

        }
        return bodyParts;
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
            int parts = mp.getCount();
            for (int i = 0; i<parts; i++ ) {
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

         return null;
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
     * helper to create a root multipart from an file
     * 
     * @param fileName the name of the file used as input
     * 
     * @return MultiPart the Multipart that represents the root mime, 
     * null if something went wrong
     */
    public static Multipart getMultiPart(String fileName)
    {
        File f=new File(fileName);
        FileInputStream fis;
        try
        {
            fis = new FileInputStream(f);
            Multipart mp=MimeUtil.getMultiPart(fis);
            return mp;
        }
        catch (FileNotFoundException e)
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
     * the doc is modified so that all URLs arer cids
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

        String cid=null;
        if(docJMF!=null)
        {
            updateXMLMultipart(multipart,docJMF,null);
            KElement e=docJMF.getRoot();
            VElement v=e.getChildrenByTagName(null, null, new JDFAttributeMap(AttributeName.URL,"*"), false, false, 0);
            String[] urlStrings = listURLs(v);
            if(urlStrings.length>0)
            {
                cid=urlToCid(urlStrings[0]);
            }

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

    private static int extendMultipart(Multipart multipart, JDFDoc docJDF , String cid)
    {
        int n=0;
        if(docJDF==null)
            return 0;

        KElement e=docJDF.getRoot();
        // get a list of all referenced document urls
        VElement v=e.getChildrenByTagName(null, null, new JDFAttributeMap(AttributeName.URL,"*"), false, false, 0);
        final int vSize = v==null ? 0 : v.size();
        String[] urlStrings = listURLs(v);
        for(int i=0;i<urlStrings.length;i++)
        {
            if(urlStrings[i]!=null)
            {
                v.item(i).setAttribute(AttributeName.URL,urlToCid(urlStrings[i]),null);
            }
        }
        updateXMLMultipart(multipart, docJDF, cid);
       
        URL urls[]=new URL[vSize];
        // add a new body part for each url
        for(int i=0;i<vSize;i++)
        {
            final String urlString=urlStrings[i];
            if(urlString!=null)
            {
                boolean alreadyPacked=false;
                for(int j=0;j<i;j++)
                {
                    alreadyPacked=alreadyPacked || urlString.equals(urlStrings[j]);
                }
                if(!alreadyPacked)
                {    
                    try
                    {
                        urls[i]=new URL(urlString);
                        URLDataSource uds=new URLDataSource(urls[i]);
                        BodyPart messageBodyPart = new MimeBodyPart();
                        messageBodyPart.setDataHandler(new DataHandler(uds));
                        setFileName(messageBodyPart,urlString);
                        //messageBodyPart.setHeader("Content-Type", JMFServlet.JDF_CONTENT_TYPE); // JDF: application/vnd.cip4-jdf+xml
                        setContentID(messageBodyPart,urlString);
                        multipart.addBodyPart(messageBodyPart);
                        n++;
                    }
                    catch (MalformedURLException e1)
                    {
                        // nop
                    } 
                    catch (MessagingException e1)
                    {
                        // nop
                    } 
                }
            }

        }
        return n;
    }

    private static String[] listURLs(VElement v)
    {
        final int vSize = v==null ? 0 : v.size();
        String[] urlStrings=new String[vSize];
        for(int i=0;i<vSize;i++)
        {
            urlStrings[i]=v.item(i).getAttribute(AttributeName.URL,null,null);
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
     * @param vXMLDocs   the Vector of XMLDoc representing the JMF and JDFs to be stored as the first part
     *                      of the package t
     * @return a Message representing the resulting MIME package, null if an error occured
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
            cid="CID_"+xmlDoc.getRoot().getAttribute("ID");
        
        BodyPart messageBodyPart=getCreatePartByCID(multipart, cid);
        try
        {
            setFileName(messageBodyPart,originalFileName);
            messageBodyPart.setContent(xmlDoc.write2String(0), "text/xml");
            setContentID(messageBodyPart,cid);
            xmlDoc.setBodyPart(messageBodyPart);
            if(originalFileName==null || originalFileName.toLowerCase().endsWith(".jmf"))
            {
                messageBodyPart.setHeader(CONTENT_TYPE, JDFConstants.MIME_JMF); // JDF: application/vnd.cip4-jmf+xml
            }
            else
            {
                messageBodyPart.setHeader(CONTENT_TYPE, JDFConstants.MIME_JDF); // JDF: application/vnd.cip4-jmf+xml
            }
        }
        catch (MessagingException x)
        {
            // skip this one
        }
        return messageBodyPart;
    }

    /**
     * write a Multipart to an output stream
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
     * write a Multipart to a Directory
     * 
     * @param mp the mime MultiPart to write
     * @param outStream the existing output stream
     * 
     * @throws IOException
     * @throws MessagingException
     */
    public static void writeToStream(Multipart m, OutputStream outStream) throws IOException, MessagingException 
    {
        Message mess = new MimeMessage((Session)null);
        mess.setContent(m);
        mess.writeTo(outStream);
        outStream.flush();
        outStream.close();
    }
    /**
     * write a Message to an existing output stream
     * 
     * @param mp the mime Message to write
     * @param outStream the existing output stream
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
        FileOutputStream fos=new FileOutputStream(outFile);
        InputStream ins=bp.getInputStream();
        IOUtils.copy(ins,fos);
        fos.flush();
        fos.close();
    }
}
