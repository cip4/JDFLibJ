/*
 *
 * The CIP4 Software License, Version 1.0
 *
 *
 * Copyright (c) 2001-2008 The International Cooperation for the Integration of 
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
 * JDFDoc.java
 *
 * -------------------------------------------------------------------------------------------------
 */
package org.cip4.jdflib.core;


import java.io.File;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;

import javax.mail.BodyPart;

import org.apache.xerces.dom.DocumentImpl;
import org.cip4.jdflib.jmf.JDFJMF;
import org.cip4.jdflib.node.JDFNode;
import org.cip4.jdflib.pool.JDFResourcePool;
import org.cip4.jdflib.resource.JDFResource;
import org.cip4.jdflib.util.MimeUtil;
import org.cip4.jdflib.util.UrlUtil;
import org.w3c.dom.Document;

/**
 *
 */
public class JDFDoc extends XMLDoc
{
    //**************************************** Constructors ****************************************
    /**
     * constructor
     */
    public JDFDoc()
    {
        super();
    }

    /**
     * constructor
     *
     * @param document
     */
    public JDFDoc(Document document)
    {
        super(document);
    }

    /**
     * constructor
     *
     * @param document
     */
    public JDFDoc(DocumentImpl document)
    {
        super(document);
    }

    /**
     * constructor - create the kind of JDF you need
     *
     * @param strDocType - ElementName.JDF, ElementName.JMF, "Config" ...
     */
    public JDFDoc(String strDocType)
    {
        super(strDocType,JDFElement.getSchemaURL());
    }

    //**************************************** Methods *********************************************
    /**
     * GetJDFRoot - get the jdf root
     *
     * @return JDFNode - the root of the JDF file
     */
    public JDFNode getJDFRoot()
    {
        return (JDFNode) getJXFRoot(ElementName.JDF);
    }

    /**
     * GetJMFRoot - get the jmf root
     *
     * @return JDFJMF - the root of the JMF file
     */
    public JDFJMF getJMFRoot()
    {
        return (JDFJMF) getJXFRoot(ElementName.JMF);
    }

    /**
     * @param rootName ElementName.JDF or ElementName.JMF
     * @return
     */
    private KElement getJXFRoot(String rootName)
    {
        KElement root = getRoot();

        if (!root.getLocalName().equals(rootName))
        {
            root = root.getChildByTagName(rootName, JDFElement.getSchemaURL(), 0, null, true, false);
        }
        
        return root;
    }

    /**
     * clone
     *
     * @return Object the cloned JDFDoc
     * @throws CloneNotSupportedException
     */
    @Override
	public Object clone() 
    {
        return new JDFDoc(((XMLDoc)super.clone()).getMemberDocument());
    }
    
    /**
     * toString
     *
     * @return String
     */
    @Override
	public String toString()
    {
        return "JDFDoc: " + super.toString();
    }
    
    /**
     * CreateJDF
     *
     * @param JDFPath
     * @deprecated simply use constructor
     * @return JDFDoc
     */
    @Deprecated
	public static JDFDoc createJDF(String jdfPath)
    {
        final JDFDoc new_doc = new JDFDoc();
        final JDFNode root = (JDFNode) new_doc.createElement(ElementName.JDF);
        root.setAttribute (AttributeName.ID, JDFElement.uniqueID(50), JDFConstants.EMPTYSTRING);
        root.init();

        new_doc.appendChild(root);

        new_doc.write2File(jdfPath, 0, true);
        
        return new_doc;
    }

 
   /**
    * returns a JDFNode by its id attribute
    *
    * @param id the ID parameter of the JDF node
    * @return JDFNode - the corresponding JDF Node, null if no such node exists
    * @deprecated use getRoot().getTarget(id, AttributeName.ID) and cast.
    *
    */
    @Deprecated
	public JDFNode getJDFNodeByID(String id)
    {
        
        return (JDFNode) getRoot().getTarget(id, AttributeName.ID);
    }
    
    /**
     * removes all dangling resources and cleans up the rrefs attributes
     *
     * @param nodeNames the list of nodenames to remove, remove all if nodenames is empty
     * 
     * @default CollectGarbageResources(new vString())
     */
    public int collectGarbageResources(VString nodeNames)
    {
        final boolean bCollectAll = nodeNames.isEmpty();

        final VElement vProcs = getJDFRoot().getvJDFNode(null, null, false);
        VElement vResources       = new VElement();
        VElement vLinkedResources = new VElement();
        
        // loop over all jdf nodes
        int i;
        for (i = 0; i < vProcs.size(); i++)
        {
            int j;
            final JDFNode n = (JDFNode) vProcs.elementAt(i);
            vLinkedResources.appendUnique(n.getLinkedResources(null, true));

            final JDFResourcePool rp  = n.getResourcePool();
            final VElement resources = 
                rp.getPoolChildren(null,null,null);
            vResources.appendUnique(resources);
            
            for (j = 0; j < resources.size(); j++)
            {
                vResources.appendUnique(((JDFResource) resources.elementAt(j)).getvHRefRes(true,true));
            }
        }
        
        VElement vr = new VElement();
        for (i = 0; i < vResources.size(); i++)
        {
            vr.appendUnique(((JDFResource)vResources.elementAt(i)).getResourceRoot());
        }
        
        vResources = vr;
        vr.clear();
        for (i = 0; i < vLinkedResources.size(); i++)
        {
            vr.appendUnique(((JDFResource)vLinkedResources.elementAt(i)).getResourceRoot());
        }
        vLinkedResources = vr;

        int nDeleted = 0;
        for (i = 0; i < vResources.size(); i++)
        {
            final JDFResource r = (JDFResource) vResources.elementAt(i);
            if (!(vLinkedResources.index(r) >= 0))
            {
                if (bCollectAll || nodeNames.contains(r.getLocalName()))
                {
                    r.deleteNode();
                    nDeleted++;
                }
            }
        }
// run gc a few times to really clean up
        Runtime.getRuntime().gc();
        Runtime.getRuntime().gc();
        return nDeleted;
    }
    
    /**
     * gets the content type
     * @return the content type, Text/XML if neither jdf nor jmf
     */
    public String getContentType() 
    {
        final KElement e=getRoot();
        final String strContentType;
        
        if (e instanceof JDFNode){
            strContentType = JDFConstants.MIME_JDF;
        }else if (e instanceof JDFJMF){
            strContentType = JDFConstants.MIME_JMF;
        }else{
            strContentType = JDFConstants.MIME_TEXTXML;
        }
        return strContentType;
    }

    /**
     * parse a JDF file
     * @param fileName
     * @return the parsed JDFDoc
     */
    public static JDFDoc parseFile(String fileName)
    {
        JDFParser p=new JDFParser();
        return p.parseFile(fileName);
     }
    
    /**
     * parse a given url
     * @param url the url to search
     * @param bp the bodypart that the CID url is located in
     * @return the parsed JDFDoc
     */
    public static JDFDoc parseURL(String url, BodyPart bp)
    {
        JDFParser p=new JDFParser();
        InputStream inStream=UrlUtil.getURLInputStream(url, bp);
        File f=UrlUtil.urlToFile(url);

        JDFDoc d= p.parseStream(inStream);
        if(f!=null && f.canRead())
            d.setOriginalFileName(f.getAbsolutePath());
        return d;
    }

    /**
     * initialize a new root of strDocType in the document
     * called by constructor XMLDoc(String strDocType)
     *
     * @param strDocType   qualified tag name of the doc root to create if still empty
     * @param namespaceURI namespace URI of the doc root
     * @return KElement - the root element
     * 
     * @default setRoot(ElementName.JDF, null)
     */
    @Override
	public KElement setRoot(String strDocType, String namespaceURI)
    {
        KElement root = super.setRoot(strDocType, namespaceURI);
        if (root != null)
        {
            if (root instanceof JDFNode)
            {
                ((JDFNode) root).init();
            }
            else if (root instanceof JDFJMF)
            {
                String comment = "Generated by the CIP4 Java open source JDF Library version : ";
                comment += JDFAudit.software();
                ((JDFJMF) root).init();
                ((JDFJMF) root).appendXMLComment(comment, null);
            }
        }        
        return root;
    }
    
    /**
     * This method sends the contents of this JDFDoc to the URL <code>strURL</code> 
     * and receives the response in the returned JDFDoc.
     * the content type is automagically set to either text/xml for undefined xml or to
     * application/vnd.cip4-jdf+xml or application/vnd.cip4-jmf+xml 
     * @param strURL            the URL to write to
     * 
     * @return docResponse the response received from URL. 
     * A Null document if no response was received, or an exception occurred
     */
    public JDFDoc write2URL(String strURL)
    {
        KElement e=getRoot();
        if(e==null)
            return null;
        String strContentType = getContentType(e);
        
        XMLDoc d=super.write2URL(strURL, strContentType);
        return d==null ? null : new JDFDoc(d.getMemberDocument());
    }
    public HttpURLConnection write2HTTPURL(URL strURL) 
    {
        KElement e=getRoot();
        if(e==null)
            return null;
        String strContentType = getContentType(e);
        
        return super.write2HTTPURL(strURL, strContentType);
    }

    /**
     * gets the contentType for a given root element
     * @param e
     * @return
     */
    public static String getContentType(KElement e)
    {
        String strContentType=MimeUtil.TEXT_XML;
        if(e instanceof JDFNode)
            strContentType=MimeUtil.VND_JDF;
        else if(e instanceof JDFJMF)
            strContentType=MimeUtil.VND_JMF;
        return strContentType;
    }

//    //////////////////////////////////////////////////////////////////////
//
//    JDFDoc write2URL(String strURL, String schemaLocation) {
//
//        return XMLDoc.write2URL(strURL,getContentType(),schemaLocation);
//    }
//    
//    //////////////////////////////////////////////////////////////////////
//
//    boolean stringParse(String in, boolean bValidate, boolean bEraseEmpty,
//        boolean bDoNamespaces, ErrorHandler pErrorHandler, String schemaLocation)
//    {
//        boolean b = XMLDoc.stringParse( in, bValidate,  bEraseEmpty, 
//                                bDoNamespaces, pErrorHandler,schemaLocation);
//        getJDFRoot().getMinID();
//        return b;
//    }
//    
//    //////////////////////////////////////////////////////////////////////
//    
//    boolean streamParse(InputStream in, boolean bValidate, boolean bEraseEmpty,
//        boolean bDoNamespaces, ErrorHandler pErrorHandler, String schemaLocation)
//    {
//        boolean b = XMLDoc.streamParse( in, bValidate,  bEraseEmpty, 
//                                bDoNamespaces, pErrorHandler,schemaLocation);
//        getJDFRoot().getMinID();
//        return b;
//    }
//    
//    
//    //////////////////////////////////////////////////////////////////////
//    
//    boolean parse(String inFile,boolean bValidate, boolean bEraseEmpty,
//        boolean bDoNamespaces, ErrorHandler pErrorHandler, String schemaLocation)
//    {
//        boolean b = XMLDoc.parse( inFile, bValidate,  bEraseEmpty, 
//                                bDoNamespaces, pErrorHandler,schemaLocation);
//        getJDFRoot().getMinID();
//        return b;
//    }
//    
//    
//    //////////////////////////////////////////////////////////////////////
//    
//    boolean parse(JDF.File inFile, boolean bValidate, boolean bEraseEmpty,
//        boolean bDoNamespaces, ErrorHandler pErrorHandler, String schemaLocation)
//    {
//        boolean b = XMLDoc.parse(inFile, bValidate,  bEraseEmpty, 
//                            bDoNamespaces, pErrorHandler,schemaLocation);
//        getJDFRoot().getMinID();
//        return b;
//    }
//    //////////////////////////////////////////////////////////////////////
//
//    MIMEMessage createMIMEMessage() {
//
//        JDF.MIMEBasicPart mbp = createMIMEBasicPart();
//        MIMEMultiPart mmp = new JDF.MIMEMultiPart();
//        mmp.addBodyPart(mbp, false); // false->don't clone it
//        mmp.setContentSubType(L"related");
//        
//        
//        // make a MIMEMessage out of it
//        MIMEMessage mmsg = new JDF.MIMEMessage();
//        mmsg.setBody(mmp,false);
//        
//        return mmsg;
//    }
//    //////////////////////////////////////////////////////////////////////
//
//    MIMEBasicPart createMIMEBasicPart() {
//        JDF.MIMEBasicPart mbp = new JDF.MIMEBasicPart (MIMEBasicPart.APPLICATION);
//        String docString;
//        write2String(docString);
//        mbp.setBodyData (docString);
//        mbp.setContentSubType(GetContentType().substr(12));
//
//        return mbp;
//    }

}


