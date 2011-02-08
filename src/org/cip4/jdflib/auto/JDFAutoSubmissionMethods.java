/*
 * The CIP4 Software License, Version 1.0
 *
 *
 * Copyright (c) 2001-2010 The International Cooperation for the Integration of
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

import java.util.Collection;                        
import java.util.Iterator;                          
import java.util.List;                              
import java.util.Map;                               
import java.util.Vector;                            
import java.util.zip.DataFormatException;           

import org.apache.commons.lang.enums.ValuedEnum;    
import org.w3c.dom.Element;                         
import org.apache.xerces.dom.CoreDocumentImpl;      
import org.cip4.jdflib.*;                           
import org.cip4.jdflib.auto.*;                      
import org.cip4.jdflib.core.*;                      
import org.cip4.jdflib.core.ElementInfo;                      
import org.cip4.jdflib.span.*;                      
import org.cip4.jdflib.node.*;                      
import org.cip4.jdflib.pool.*;                      
import org.cip4.jdflib.jmf.*;                       
import org.cip4.jdflib.datatypes.*;                 
import org.cip4.jdflib.resource.*;                  
import org.cip4.jdflib.resource.devicecapability.*; 
import org.cip4.jdflib.resource.intent.*;           
import org.cip4.jdflib.resource.process.*;          
import org.cip4.jdflib.resource.process.postpress.*;
import org.cip4.jdflib.resource.process.press.*;    
import org.cip4.jdflib.resource.process.prepress.*; 
import org.cip4.jdflib.util.*;           
    /**
    *****************************************************************************
    class JDFAutoSubmissionMethods : public JDFElement

    *****************************************************************************
    */

public abstract class JDFAutoSubmissionMethods extends JDFElement
{

    private static final long serialVersionUID = 1L;

    private static AtrInfoTable[] atrInfoTable = new AtrInfoTable[6];
    static
    {
        atrInfoTable[0] = new AtrInfoTable(AttributeName.FILE, 0x44444433, AttributeInfo.EnumAttributeType.boolean_, null, null);
        atrInfoTable[1] = new AtrInfoTable(AttributeName.HOTFOLDER, 0x44443333, AttributeInfo.EnumAttributeType.URL, null, null);
        atrInfoTable[2] = new AtrInfoTable(AttributeName.HTTPGET, 0x44444433, AttributeInfo.EnumAttributeType.boolean_, null, null);
        atrInfoTable[3] = new AtrInfoTable(AttributeName.PACKAGING, 0x33333311, AttributeInfo.EnumAttributeType.enumerations, EnumPackaging.getEnum(0), null);
        atrInfoTable[4] = new AtrInfoTable(AttributeName.MIME, 0x44444433, AttributeInfo.EnumAttributeType.boolean_, null, null);
        atrInfoTable[5] = new AtrInfoTable(AttributeName.URLSCHEMES, 0x33333311, AttributeInfo.EnumAttributeType.NMTOKENS, null, null);
    }
    
    protected AttributeInfo getTheAttributeInfo()
    {
        return super.getTheAttributeInfo().updateReplace(atrInfoTable);
    }



    /**
     * Constructor for JDFAutoSubmissionMethods
     * @param myOwnerDocument
     * @param qualifiedName
     */
    protected JDFAutoSubmissionMethods(
        CoreDocumentImpl myOwnerDocument,
        String qualifiedName)
    {
        super(myOwnerDocument, qualifiedName);
    }

    /**
     * Constructor for JDFAutoSubmissionMethods
     * @param myOwnerDocument
     * @param myNamespaceURI
     * @param qualifiedName
     */
    protected JDFAutoSubmissionMethods(
        CoreDocumentImpl myOwnerDocument,
        String myNamespaceURI,
        String qualifiedName)
    {
        super(myOwnerDocument, myNamespaceURI, qualifiedName);
    }

    /**
     * Constructor for JDFAutoSubmissionMethods
     * @param myOwnerDocument
     * @param myNamespaceURI
     * @param qualifiedName
     * @param myLocalName
     */
    protected JDFAutoSubmissionMethods(
        CoreDocumentImpl myOwnerDocument,
        String myNamespaceURI,
        String qualifiedName,
        String myLocalName)
    {
        super(myOwnerDocument, myNamespaceURI, qualifiedName, myLocalName);
    }


    public String toString()
    {
        return " JDFAutoSubmissionMethods[  --> " + super.toString() + " ]";
    }


        /**
        * Enumeration strings for Packaging
        */

        public static class EnumPackaging extends ValuedEnum
        {
            private static final long serialVersionUID = 1L;
            private static int m_startValue = 0;

            private EnumPackaging(String name)
            {
                super(name, m_startValue++);
            }

            public static EnumPackaging getEnum(String enumName)
            {
                return (EnumPackaging) getEnum(EnumPackaging.class, enumName);
            }

            public static EnumPackaging getEnum(int enumValue)
            {
                return (EnumPackaging) getEnum(EnumPackaging.class, enumValue);
            }

            public static Map getEnumMap()
            {
                return getEnumMap(EnumPackaging.class);
            }

            public static List getEnumList()
            {
                return getEnumList(EnumPackaging.class);
            }

            public static Iterator iterator()
            {
                return iterator(EnumPackaging.class);
            }

            public static final EnumPackaging MIME = new EnumPackaging("MIME");
            public static final EnumPackaging None = new EnumPackaging("None");
        }      



/* ************************************************************************
 * Attribute getter / setter
 * ************************************************************************
 */
        
        /* ---------------------------------------------------------------------
        Methods for Attribute File
        --------------------------------------------------------------------- */
        /**
          * (36) set attribute File
          * @param value: the value to set the attribute to
          */
        public void setFile(boolean value)
        {
            setAttribute(AttributeName.FILE, value, null);
        }

        /**
          * (18) get boolean attribute File
          * @return boolean the value of the attribute
          */
        public boolean getFile()
        {
            return getBoolAttribute(AttributeName.FILE, null, false);
        }

        
        /* ---------------------------------------------------------------------
        Methods for Attribute HotFolder
        --------------------------------------------------------------------- */
        /**
          * (36) set attribute HotFolder
          * @param value: the value to set the attribute to
          */
        public void setHotFolder(String value)
        {
            setAttribute(AttributeName.HOTFOLDER, value, null);
        }

        /**
          * (23) get String attribute HotFolder
          * @return the value of the attribute
          */
        public String getHotFolder()
        {
            return getAttribute(AttributeName.HOTFOLDER, null, JDFConstants.EMPTYSTRING);
        }

        
        /* ---------------------------------------------------------------------
        Methods for Attribute HttpGet
        --------------------------------------------------------------------- */
        /**
          * (36) set attribute HttpGet
          * @param value: the value to set the attribute to
          */
        public void setHttpGet(boolean value)
        {
            setAttribute(AttributeName.HTTPGET, value, null);
        }

        /**
          * (18) get boolean attribute HttpGet
          * @return boolean the value of the attribute
          */
        public boolean getHttpGet()
        {
            return getBoolAttribute(AttributeName.HTTPGET, null, false);
        }

        
        /* ---------------------------------------------------------------------
        Methods for Attribute Packaging
        --------------------------------------------------------------------- */
        /**
          * (5.2) set attribute Packaging
          * @param v vector of the enumeration values
          */
        public void setPackaging(Vector v)
        {
            setEnumerationsAttribute(AttributeName.PACKAGING, v, null);
        }

        /**
          * (9.2) get Packaging attribute Packaging
          * @return Vector of the enumerations
          */
        public Vector getPackaging()
        {
            return getEnumerationsAttribute(AttributeName.PACKAGING, null, EnumPackaging.getEnum(0), false);
        }

        
        /* ---------------------------------------------------------------------
        Methods for Attribute MIME
        --------------------------------------------------------------------- */
        /**
          * (36) set attribute MIME
          * @param value: the value to set the attribute to
          */
        public void setMIME(boolean value)
        {
            setAttribute(AttributeName.MIME, value, null);
        }

        /**
          * (18) get boolean attribute MIME
          * @return boolean the value of the attribute
          */
        public boolean getMIME()
        {
            return getBoolAttribute(AttributeName.MIME, null, false);
        }

        
        /* ---------------------------------------------------------------------
        Methods for Attribute URLSchemes
        --------------------------------------------------------------------- */
        /**
          * (36) set attribute URLSchemes
          * @param value: the value to set the attribute to
          */
        public void setURLSchemes(VString value)
        {
            setAttribute(AttributeName.URLSCHEMES, value, null);
        }

        /**
          * (21) get VString attribute URLSchemes
          * @return VString the value of the attribute
          */
        public VString getURLSchemes()
        {
            VString vStrAttrib = new VString();
            String  s = getAttribute(AttributeName.URLSCHEMES, null, JDFConstants.EMPTYSTRING);
            vStrAttrib.setAllStrings(s, " ");
            return vStrAttrib;
        }

}// end namespace JDF
