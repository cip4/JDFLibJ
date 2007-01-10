/*
*
* The CIP4 Software License, Version 1.0
*
*
* Copyright (c) 2001-2002 The International Cooperation for the Integration of 
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

//EndCopyRight
//////////////////////////////////////////////////////////////////////
//
// COPYRIGHT Heidelberger Druckmaschinen AG, 1999-2001
//      ALL RIGHTS RESERVED 
//
//  Author: Dr. Kai Mattern
// 
// Revision history:
// created  30.March.2004
//
// JDFState.java: implementation of the JDFState 
// contains specializations of the JDFState hierarchy
//
//////////////////////////////////////////////////////////////////////

package org.cip4.jdflib.resource.devicecapability;

import org.apache.xerces.dom.CoreDocumentImpl;
import org.cip4.jdflib.core.AttributeName;
import org.w3c.dom.DOMException;

/**
 * @author matternk
 *
 * To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Generation - Code and Comments
 */
public class JDFRangeType extends JDFAbstractState
{
    
    private static final long serialVersionUID = 1L;

    /**
     * ructor for JDFState
     * @param ownerDocument
     * @param qualifiedName
     * @throws DOMException
     */
    public JDFRangeType(CoreDocumentImpl myOwnerDocument, String qualifiedName)
        throws DOMException
    {
        super(myOwnerDocument, qualifiedName);
    }

    /**
     * ructor for JDFState
     * @param ownerDocument
     * @param namespaceURI
     * @param qualifiedName
     * @throws DOMException
     */
    public JDFRangeType(
        CoreDocumentImpl myOwnerDocument,
        String myNamespaceURI,
        String qualifiedName)
        throws DOMException
    {
        super(myOwnerDocument, myNamespaceURI, qualifiedName);
    }

    /**
     * ructor for JDFState
     * @param ownerDocument
     * @param namespaceURI
     * @param qualifiedName
     * @param localName
     * @throws DOMException
     */
    public JDFRangeType(
        CoreDocumentImpl myOwnerDocument,
        String myNamespaceURI,
        String qualifiedName,
        String myLocalName)
        throws DOMException
    {
        super(myOwnerDocument, myNamespaceURI, qualifiedName, myLocalName);
    }

    /**
     * toString
     * @return String
     */
    public String toString()
    {
        return "JDFRangeType[ --> " + super.toString() + " ]";
    }
//  **************************************** Methods *********************************************    
    
    /**
    * Typesafe attribute validation of DefaultValue
    *
    * @param EnumValidationLevel level: level of attribute validation 
    * @return bool: true if valid
    */
    public boolean validDefaultValue()
    {
        //TODO this is defined as templates in C++. Cant translate this 1 to 1
//        try
//        {
//            if (!hasAttribute(AttributeName.DEFAULTVALUE))
//            {
//                return true;
//            }
//            JDFRangeType nr = getAttribute(AttributeName.DEFAULTVALUE);
//        }
//        catch (IllegalArgumentException e)
//        {
//            return false;
//        }
//        catch (JDFException e)
//        {
//            return false;
//        }
        return true;
    }
    
    /**
    * Typesafe attribute validation of CurrentValue
    *
    * @param EnumValidationLevel level: level of attribute validation 
    * @return bool: true if valid
    */
    public boolean validCurrentValue()
    {
        //TODO this is defined as templates in C++. Cant translate this 1 to 1
//        try
//        {
//            if (!hasAttribute(AttributeName.CURRENTVALUE))
//            {   
//                return true; 
//            } 
//            JDFRangeType nr = new JDFRangeType(getAttribute(AttributeName.CURRENTVALUE));
//        }
//        catch (IllegalArgumentException e)
//        {
//            return false;
//        }
//        catch (JDFException e)
//        {
//            return false;
//        }
        return true;
    }
    
    
    
    /**
    * Gets the value of attribute DefaultValue
    *
    * @return RangeType: the attribute value
    */
    public String getDefaultValue()
    {
        return getAttribute(AttributeName.DEFAULTVALUE);
    }
    
    /**
    * Sets the DefaultValue attribute
    *
    * @param RangeType: the value to set the attribute to
    */
    public void setDefaultValue(String n)
    {
        setAttribute(AttributeName.DEFAULTVALUE, n);
    }
    
    /**
    * Gets the value of attribute CurrentValue
    *
    * @return RangeType: the attribute value
    */
    public String getCurrentValue()
    {
        return getAttribute(AttributeName.CURRENTVALUE);
    }
    
    /**
    * Sets the CurrentValue attribute
    *
    * @param RangeType: the value to set the attribute to
    */
    public void setCurrentValue(String n)
    {
        setAttribute(AttributeName.CURRENTVALUE, n);
    }
    
    public boolean fitsValue(String value, EnumFitsValue testlists)
    {
        //TODO implement
        return true;
    }
    
}
