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
 *
 * Copyright (c) 2001 Heidelberger Druckmaschinen AG, All Rights Reserved.
 *
 * JDFDoc.java
 *
 * -------------------------------------------------------------------------------------------------
 */
package org.cip4.jdflib.core;


import java.util.Vector;

import org.apache.xerces.dom.DocumentImpl;
import org.cip4.jdflib.jmf.JDFJMF;
import org.cip4.jdflib.node.JDFNode;
import org.cip4.jdflib.pool.JDFResourcePool;
import org.cip4.jdflib.resource.JDFResource;
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
     * @param Document document
     */
    public JDFDoc(Document document)
    {
        super(document);
    }

    /**
     * constructor
     *
     * @param DocumentImpl document
     */
    public JDFDoc(DocumentImpl document)
    {
        super(document);
    }

    /**
     * constructor - create the kind of JDF you need
     *
     * @param String strDocType - ElementName.JDF, ElementName.JMF, "Config" ...
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
     * @param rootName = ElementName.JDF / ElementName.JMF
     * @return
     */
    private JDFElement getJXFRoot(String rootName)
    {
        KElement root = getRoot();

        if (!root.getLocalName().equals(rootName))
        {
            root = root.getChildByTagName(rootName, JDFElement.getSchemaURL(), 1, null, true, false);
        }
        
        return (JDFElement)root;
    }

    
    /**
     * toString
     *
     * @return String
     */
    public String toString()
    {
        return "JDFDoc: " + super.toString();
    }
    
    /**
     * CreateJDF
     *
     * @param String JDFPath
     * @deprecated simply use constructor
     * @return JDFDoc
     */
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
    * @param KString the ID parameter of the JDF node
    * @return JDFNode the corresponding JDF Node, null if no such node exists
    * @deprecated use XMLDoc.getElementById and cast.
    *
    */

    public JDFNode getJDFNodeByID(String id)
    {
        
        return (JDFNode) getElementById(id);
    }
    
    /**
     * removes all dangling resources and cleans up the rrefs attributes
     *
     * @param VString nodeNames the list of nodenames to remove, remove all if nodenames is empty
     * @return int the number of resources that were removed
     * 
     * default: CollectGarbageResources(new vString())
     */
    public int collectGarbageResources(VString nodeNames)
    {
        final boolean bCollectAll = nodeNames.isEmpty();

        final Vector vProcs = getJDFRoot().getvJDFNode(null, null, false);
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
    
    public String getContentType() 
    {
        final KElement e=getRoot();
        String strContentType;
        
        if (e instanceof JDFNode){
            strContentType = "application/vnd.cip4-jdf+xml";
        }else if (e instanceof JDFJMF){
            strContentType = "application/vnd.cip4-jmf+xml";
        }else{
            throw new JDFException("GetContentType - illegal root element: "+e.getNodeName());
        }
        return strContentType;
    }

    public static JDFDoc parseFile(String fileName)
    {
        JDFParser p=new JDFParser();
        JDFDoc d=null;
        d= p.parseFile(fileName);
        return d;
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


