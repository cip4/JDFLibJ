/*
 * The CIP4 Software License, Version 1.0
 *
 *
 * Copyright (c) 2001-2005 The International Cooperation for the Integration of
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

package org.cip4.jdflib.auto;

import org.apache.xerces.dom.CoreDocumentImpl;
import org.cip4.jdflib.core.AtrInfoTable;
import org.cip4.jdflib.core.AttributeInfo;
import org.cip4.jdflib.core.AttributeName;
import org.cip4.jdflib.core.JDFConstants;
import org.cip4.jdflib.core.JDFElement;

public abstract class JDFAutoCreditCard extends JDFElement
{

    private static final long serialVersionUID = 1L;

    private static AtrInfoTable[] atrInfoTable = new AtrInfoTable[5];
    static
    {
        atrInfoTable[0] = new AtrInfoTable(AttributeName.AUTHORIZATION, 0x44444331, AttributeInfo.EnumAttributeType.string, null, null);
        atrInfoTable[1] = new AtrInfoTable(AttributeName.AUTHORIZATIONEXPIRES, 0x44444331, AttributeInfo.EnumAttributeType.string, null, null);
        atrInfoTable[2] = new AtrInfoTable(AttributeName.EXPIRES, 0x44444221, AttributeInfo.EnumAttributeType.string, null, null);
        atrInfoTable[3] = new AtrInfoTable(AttributeName.NUMBER, 0x44444221, AttributeInfo.EnumAttributeType.NMTOKEN, null, null);
        atrInfoTable[4] = new AtrInfoTable(AttributeName.TYPE, 0x44444221, AttributeInfo.EnumAttributeType.NMTOKEN, null, null);
    }
    
    @Override
	protected AttributeInfo getTheAttributeInfo()
    {
        return super.getTheAttributeInfo().updateReplace(atrInfoTable);
    }



    /**
     * Constructor for JDFAutoCreditCard
     * @param myOwnerDocument
     * @param qualifiedName
     */
    protected JDFAutoCreditCard(
        CoreDocumentImpl myOwnerDocument,
        String qualifiedName)
    {
        super(myOwnerDocument, qualifiedName);
    }

    /**
     * Constructor for JDFAutoCreditCard
     * @param myOwnerDocument
     * @param myNamespaceURI
     * @param qualifiedName
     */
    protected JDFAutoCreditCard(
        CoreDocumentImpl myOwnerDocument,
        String myNamespaceURI,
        String qualifiedName)
    {
        super(myOwnerDocument, myNamespaceURI, qualifiedName);
    }

    /**
     * Constructor for JDFAutoCreditCard
     * @param myOwnerDocument
     * @param myNamespaceURI
     * @param qualifiedName
     * @param myLocalName
     */
    protected JDFAutoCreditCard(
        CoreDocumentImpl myOwnerDocument,
        String myNamespaceURI,
        String qualifiedName,
        String myLocalName)
    {
        super(myOwnerDocument, myNamespaceURI, qualifiedName, myLocalName);
    }


    @Override
	public String toString()
    {
        return " JDFAutoCreditCard[  --> " + super.toString() + " ]";
    }


/* ************************************************************************
 * Attribute getter / setter
 * ************************************************************************
 */
        
        /* ---------------------------------------------------------------------
        Methods for Attribute Authorization
        --------------------------------------------------------------------- */
        /**
          * (36) set attribute Authorization
          * @param value: the value to set the attribute to
          */
        public void setAuthorization(String value)
        {
            setAttribute(AttributeName.AUTHORIZATION, value, null);
        }

        /**
          * (23) get String attribute Authorization
          * @return the value of the attribute
          */
        public String getAuthorization()
        {
            return getAttribute(AttributeName.AUTHORIZATION, null, JDFConstants.EMPTYSTRING);
        }

        
        /* ---------------------------------------------------------------------
        Methods for Attribute AuthorizationExpires
        --------------------------------------------------------------------- */
        /**
          * (36) set attribute AuthorizationExpires
          * @param value: the value to set the attribute to
          */
        public void setAuthorizationExpires(String value)
        {
            setAttribute(AttributeName.AUTHORIZATIONEXPIRES, value, null);
        }

        /**
          * (23) get String attribute AuthorizationExpires
          * @return the value of the attribute
          */
        public String getAuthorizationExpires()
        {
            return getAttribute(AttributeName.AUTHORIZATIONEXPIRES, null, JDFConstants.EMPTYSTRING);
        }

        
        /* ---------------------------------------------------------------------
        Methods for Attribute Expires
        --------------------------------------------------------------------- */
        /**
          * (36) set attribute Expires
          * @param value: the value to set the attribute to
          */
        public void setExpires(String value)
        {
            setAttribute(AttributeName.EXPIRES, value, null);
        }

        /**
          * (23) get String attribute Expires
          * @return the value of the attribute
          */
        public String getExpires()
        {
            return getAttribute(AttributeName.EXPIRES, null, JDFConstants.EMPTYSTRING);
        }

        
        /* ---------------------------------------------------------------------
        Methods for Attribute Number
        --------------------------------------------------------------------- */
        /**
          * (36) set attribute Number
          * @param value: the value to set the attribute to
          */
        public void setNumber(String value)
        {
            setAttribute(AttributeName.NUMBER, value, null);
        }

        /**
          * (23) get String attribute Number
          * @return the value of the attribute
          */
        public String getNumber()
        {
            return getAttribute(AttributeName.NUMBER, null, JDFConstants.EMPTYSTRING);
        }

        
        /* ---------------------------------------------------------------------
        Methods for Attribute Type
        --------------------------------------------------------------------- */
        /**
          * (36) set attribute Type
          * @param value: the value to set the attribute to
          */
        public void setType(String value)
        {
            setAttribute(AttributeName.TYPE, value, null);
        }

        /**
          * (23) get String attribute Type
          * @return the value of the attribute
          */
        public String getType()
        {
            return getAttribute(AttributeName.TYPE, null, JDFConstants.EMPTYSTRING);
        }

}// end namespace JDF
