/*--------------------------------------------------------------------------------------------------
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
 */
/**
 *
 * Copyright (c) 2001 Heidelberger Druckmaschinen AG, All Rights Reserved.
 *
 * JDFResponse.java
 *
 * Last changes
 *
 * 2002-07-02   JG - init() Also call super::init()
 */
package org.cip4.jdflib.jmf;

import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.enums.ValuedEnum;
import org.apache.xerces.dom.CoreDocumentImpl;
import org.cip4.jdflib.auto.JDFAutoResponse;
import org.cip4.jdflib.core.JDFComment;
import org.cip4.jdflib.resource.JDFNotification;


public class JDFResponse extends JDFAutoResponse //JDFMessage
{
    private static final long serialVersionUID = 1L;

    /**
     * Constructor for JDFResponse
     * @param myOwnerDocument
     * @param qualifiedName
     */
    public JDFResponse(
            CoreDocumentImpl myOwnerDocument,
            String qualifiedName)
    {
        super(myOwnerDocument, qualifiedName);
    }

    /**
     * Constructor for JDFResponse
     * @param myOwnerDocument
     * @param myNamespaceURI
     * @param qualifiedName
     */
    public JDFResponse(
            CoreDocumentImpl myOwnerDocument,
            String myNamespaceURI,
            String qualifiedName)
    {
        super(myOwnerDocument, myNamespaceURI, qualifiedName);
    }

    /**
     * Constructor for JDFResponse
     * @param myOwnerDocument
     * @param myNamespaceURI
     * @param qualifiedName
     * @param myLocalName
     */
    public JDFResponse(
            CoreDocumentImpl myOwnerDocument,
            String myNamespaceURI,
            String qualifiedName,
            String myLocalName)
    {
        super(myOwnerDocument, myNamespaceURI, qualifiedName, myLocalName);
    }

    /**
     * inner class EnumClass
     */
    public static final class EnumError extends ValuedEnum
    {
        private static final long serialVersionUID = 1L;
        private static int m_startValue = 0;

        private EnumError(String status) 
        {
            super(status, m_startValue++);
        }

        public static EnumError getEnum(String status) 
        {
            return (EnumError) getEnum(EnumError.class, status);
        }

        public static EnumError getEnum(int value) 
        {
            return (EnumError) getEnum(EnumError.class, value);
        }

        public static Map getEnumMap() 
        {
            return getEnumMap(EnumError.class);
        }

        public static List getEnumList() 
        {
            return getEnumList(EnumError.class);
        }

        public static Iterator iterator() 
        {
            return iterator(EnumError.class);
        }

        /**
         * constants EnumError
         */
        public static final EnumError ErrorUnknown      = new EnumError("ErrorUnknown");
        public static final EnumError ErrorUnknownQuery = new EnumError("ErrorUnknownQuery");

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
        return "JDFResponse[  --> " + super.toString() + " ]";
    }

    /**
     * Set ErrorText, (Notification/Comment/#text)
     * also sets Notification/@Type=Error and Notification/@Class=Error
     * doesn't create a notification if ErroerText=null
     *
     * @param errorText new error text
     * @return JDFNotification the newly created Notification element
     */
    public JDFNotification setErrorText(String errorText)
    {
        final JDFNotification n=getCreateNotification();
        n.setType("Error");
        n.setClass(JDFNotification.EnumClass.Error);
        if(errorText!=null)
        {
            JDFComment c=n.getComment(0);
            if(c!=null)
                c.appendText("\n");
            else
                c=n.appendComment();
            c.appendText(errorText);
        }
        return n;
    }
}
