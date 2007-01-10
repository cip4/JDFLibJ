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
 * XMLErrorHandler.java - implementation of the error handler
 *
 * @author Elena Skobchenko
 */

package org.cip4.jdflib.core;

import org.xml.sax.ErrorHandler;
import org.xml.sax.SAXParseException;


public class XMLErrorHandler implements ErrorHandler {
    
    private XMLDoc xmlOutput = null;
    private KElement root;
    
    public XMLErrorHandler() 
    {
        super();
        xmlOutput = new XMLDoc("SchemaValidationOutput",null);
        root = xmlOutput.getRoot();
    }
    
    public void warning(final SAXParseException exception)
    {
        String warn = exception.getMessage();
        KElement kEl = root.appendElement("Warning");
        kEl.setAttribute("Message", warn);
    }
    
    public void error(final SAXParseException exception)
    {
        // print out all parser errors except undefined variables for non-JDF stuff
        String er = exception.getMessage();
        
        if ((er.indexOf("http://www.CIP4.org/JDFSchema") != -1) ||
                (er.indexOf("is not declared for") == -1))
        {
            KElement kEl = root.appendElement("Error");
            kEl.setAttribute("Message", er);
        }
    }
    
    /**
     * @param exception SAXParseException
     * @throws JDFException - if fatal error occurs
     */
    public void fatalError(final SAXParseException exception)
    {
        String er = exception.getMessage();
        KElement kEl = root.appendElement("FatalError");
        kEl.setAttribute("Message", er);
        
        throw new JDFException("Fatal error in the Parser:" + er);
    }
    
    public XMLDoc getXMLOutput() 
    {
        return xmlOutput;
    }
    
    /**
     * remove duplicate warnings from the list and set schemaloction
     * @param schemaLocation
     */
    public void cleanXML(String schemaLocation)
    {
        VElement v=root.getChildElementVector(null,null,null,true,0,false);
        v.unifyElement();
        root.removeChildren(null,null,null);
        final int size = v.size();
        for(int i=0;i<size;i++)
            root.appendChild(v.item(i));
        if(schemaLocation==null)
        {
            root.setAttribute("ValidationResult","NotPerformed");
        }
        else
        {
            root.setAttribute("SchemaLocation",schemaLocation);
            if(root.hasChildElement("FatalError",null))
                root.setAttribute("ValidationResult","FatalError");
            else if(root.hasChildElement("Error",null))
                root.setAttribute("ValidationResult","Error");
            else if(root.hasChildElement("Warning",null))
                root.setAttribute("ValidationResult","Warning");
            else
                root.setAttribute("ValidationResult","Valid");
        }            
    }
 //////////////////////////////////////////////////////////////////////////////////////   
}


