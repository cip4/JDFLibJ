/*
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
==========================================================================
class JDFQueueSubmissionParams extends JDFResource
==========================================================================
@COPYRIGHT Heidelberger Druckmaschinen AG, 1999-2001
ALL RIGHTS RESERVED
@Author: sabjon@topmail.de   using a code generator
Warning! very preliminary test version. Interface subject to change without prior notice!
Revision history:    ...
**/


package org.cip4.jdflib.jmf;

import java.net.URL;

import org.apache.xerces.dom.CoreDocumentImpl;
import org.cip4.jdflib.auto.JDFAutoQueueSubmissionParams;
import org.cip4.jdflib.core.AttributeName;
import org.cip4.jdflib.core.JDFDoc;



//----------------------------------
    public class JDFQueueSubmissionParams extends JDFAutoQueueSubmissionParams
{
    private static final long serialVersionUID = 1L;

    /**
     * Constructor for JDFQueueSubmissionParams
     * @param myOwnerDocument
     * @param qualifiedName
     */
    public JDFQueueSubmissionParams(
        CoreDocumentImpl myOwnerDocument,
        String qualifiedName)
    {
        super(myOwnerDocument, qualifiedName);
    }

    /**
     * @param myOwnerDocument
     * @param myNamespaceURI
     * @param qualifiedName
     */
    public JDFQueueSubmissionParams(
        CoreDocumentImpl myOwnerDocument,
        String myNamespaceURI,
        String qualifiedName)
    {
        super(myOwnerDocument, myNamespaceURI, qualifiedName);
    }

    /**
     * @param myOwnerDocument
     * @param myNamespaceURI
     * @param qualifiedName
     * @param myLocalName
     */
    public JDFQueueSubmissionParams(
        CoreDocumentImpl myOwnerDocument,
        String myNamespaceURI,
        String qualifiedName,
        String myLocalName)
    {
        super(myOwnerDocument, myNamespaceURI, qualifiedName, myLocalName);
    }

    /**
     * toString()
     * return String
     */
    public String toString()
    {
        return "JDFQueueSubmissionParams[  --> " + super.toString() + " ]";
    }
    
    /**
     * add a queueentry to a queue based on the parameters of this
     * you can get the new queueentry by {@link}getQueueEntry(0) on the response
     * 
     * @param theQueue the queue to submit to, note that the queue IS modified by this call
     * @param responseIn the jmf that serves as a container for the new response
     * 
     * @return the response jmf to the submission message
     */
    public JDFResponse addEntry(JDFQueue theQueue, JDFJMF responseIn)
    {
        JDFCommand command=(JDFCommand)getParentNode_KElement();
        JDFJMF jmf=command.createResponse();
        JDFResponse resp=jmf.getResponse(0);
        if(responseIn!=null)
        {
            resp = (JDFResponse) responseIn.copyElement(resp, null);
        }
        if(theQueue==null)
        {
            resp.setErrorText("No Queue specified");
            resp.setReturnCode(2); // 
            return resp;
        } 
        
        if(!theQueue.canAccept())
        {
            resp.setReturnCode(112); //  
            resp.copyElement(theQueue, null);
            return resp;
        }
        JDFQueueEntry qe=theQueue.createQueueEntry(getHold());
         
        final String copyAtts[]=new String[]{
               AttributeName.GANGNAME, AttributeName.GANGPOLICY};
        
        for(int i=0;i<copyAtts.length;i++)
        if(hasAttribute(copyAtts[i]))
            qe.copyAttribute(copyAtts[i], this, null, null, null);
        
        // TODO more attributes e.g prev. next...
        if(hasAttribute(AttributeName.PRIORITY))
            qe.setPriority(getPriority()); // calls the automated function, therfore not in the list above

        
        resp.copyElement(theQueue, null);
        resp.copyElement(qe, null);
        
        return resp;
    }
    
    /**
     * returns the jdf doc referenced by url
     * @return the document
     */
    public JDFDoc getURLDoc()
    {
        return super.getURLDoc();
    }
    
    /**
     * sets ReturnURL to the value of url
     * @param url
     */
    public void setReturnURL(URL url)
    {
        super.setReturnURL(url==null ? null : url.toExternalForm());
    }
    /**
     * sets ReturnURL to the value of url
     * @param url
     */
    public void setReturnJMF(URL url)
    {
        super.setReturnJMF(url==null ? null : url.toExternalForm());
    }

}



