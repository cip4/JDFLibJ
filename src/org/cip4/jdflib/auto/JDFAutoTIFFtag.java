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

import java.util.zip.DataFormatException;

import org.apache.xerces.dom.CoreDocumentImpl;
import org.cip4.jdflib.core.AtrInfoTable;
import org.cip4.jdflib.core.AttributeInfo;
import org.cip4.jdflib.core.AttributeName;
import org.cip4.jdflib.core.JDFConstants;
import org.cip4.jdflib.core.JDFElement;
import org.cip4.jdflib.datatypes.JDFIntegerList;
import org.cip4.jdflib.datatypes.JDFNumberList;
    /*
    *****************************************************************************
    class JDFAutoTIFFtag : public JDFElement

    *****************************************************************************
    */

public abstract class JDFAutoTIFFtag extends JDFElement
{

    private static final long serialVersionUID = 1L;

    private static AtrInfoTable[] atrInfoTable = new AtrInfoTable[6];
    static
    {
        atrInfoTable[0] = new AtrInfoTable(AttributeName.TAGNUMBER, 0x22222211, AttributeInfo.EnumAttributeType.integer, null, null);
        atrInfoTable[1] = new AtrInfoTable(AttributeName.TAGTYPE, 0x22222211, AttributeInfo.EnumAttributeType.integer, null, null);
        atrInfoTable[2] = new AtrInfoTable(AttributeName.INTEGERVALUE, 0x33333311, AttributeInfo.EnumAttributeType.IntegerList, null, null);
        atrInfoTable[3] = new AtrInfoTable(AttributeName.NUMBERVALUE, 0x33333311, AttributeInfo.EnumAttributeType.string, null, null);
        atrInfoTable[4] = new AtrInfoTable(AttributeName.STRINGVALUE, 0x33333311, AttributeInfo.EnumAttributeType.string, null, null);
        atrInfoTable[5] = new AtrInfoTable(AttributeName.BINARYVALUE, 0x33333311, AttributeInfo.EnumAttributeType.hexBinary, null, null);
    }
    
    protected AttributeInfo getTheAttributeInfo()
    {
        return super.getTheAttributeInfo().updateReplace(atrInfoTable);
    }



    /**
     * Constructor for JDFAutoTIFFtag
     * @param myOwnerDocument
     * @param qualifiedName
     */
    protected JDFAutoTIFFtag(
        CoreDocumentImpl myOwnerDocument,
        String qualifiedName)
    {
        super(myOwnerDocument, qualifiedName);
    }

    /**
     * Constructor for JDFAutoTIFFtag
     * @param myOwnerDocument
     * @param myNamespaceURI
     * @param qualifiedName
     */
    protected JDFAutoTIFFtag(
        CoreDocumentImpl myOwnerDocument,
        String myNamespaceURI,
        String qualifiedName)
    {
        super(myOwnerDocument, myNamespaceURI, qualifiedName);
    }

    /**
     * Constructor for JDFAutoTIFFtag
     * @param myOwnerDocument
     * @param myNamespaceURI
     * @param qualifiedName
     * @param myLocalName
     */
    protected JDFAutoTIFFtag(
        CoreDocumentImpl myOwnerDocument,
        String myNamespaceURI,
        String qualifiedName,
        String myLocalName)
    {
        super(myOwnerDocument, myNamespaceURI, qualifiedName, myLocalName);
    }


    public String toString()
    {
        return " JDFAutoTIFFtag[  --> " + super.toString() + " ]";
    }


/* ************************************************************************
 * Attribute getter / setter
 * ************************************************************************
 */
        
        /* ---------------------------------------------------------------------
        Methods for Attribute TagNumber
        --------------------------------------------------------------------- */
        /**
          * (36) set attribute TagNumber
          * @param value: the value to set the attribute to
          */
        public void setTagNumber(int value)
        {
            setAttribute(AttributeName.TAGNUMBER, value, null);
        }



        /**
          * (15) get int attribute TagNumber
          * @return int the value of the attribute
          */
        public int getTagNumber()
        {
            return getIntAttribute(AttributeName.TAGNUMBER, null, 0);
        }



        
        /* ---------------------------------------------------------------------
        Methods for Attribute TagType
        --------------------------------------------------------------------- */
        /**
          * (36) set attribute TagType
          * @param value: the value to set the attribute to
          */
        public void setTagType(int value)
        {
            setAttribute(AttributeName.TAGTYPE, value, null);
        }



        /**
          * (15) get int attribute TagType
          * @return int the value of the attribute
          */
        public int getTagType()
        {
            return getIntAttribute(AttributeName.TAGTYPE, null, 0);
        }



        
        /* ---------------------------------------------------------------------
        Methods for Attribute IntegerValue
        --------------------------------------------------------------------- */
        /**
          * (36) set attribute IntegerValue
          * @param value: the value to set the attribute to
          */
        public void setIntegerValue(JDFIntegerList value)
        {
            setAttribute(AttributeName.INTEGERVALUE, value, null);
        }



        /**
          * (20) get JDFIntegerList attribute IntegerValue
          * @return JDFIntegerList the value of the attribute, null if a the
          *         attribute value is not a valid to create a JDFIntegerList
          */
        public JDFIntegerList getIntegerValue()
        {
            String strAttrName = "";
            JDFIntegerList nPlaceHolder = null;
            strAttrName = getAttribute(AttributeName.INTEGERVALUE, null, JDFConstants.EMPTYSTRING);
            try
            {
                nPlaceHolder = new JDFIntegerList(strAttrName);
            }
            catch(DataFormatException e)
            {
                return null;
            }
            return nPlaceHolder;
        }



        
        /* ---------------------------------------------------------------------
        Methods for Attribute NumberValue
        --------------------------------------------------------------------- */
        /**
          * (36) set attribute NumberValue
          * @param value: the value to set the attribute to
          */
        public void setNumberValue(JDFNumberList value)
        {
            setAttribute(AttributeName.NUMBERVALUE, value, null);
        }



        /**
          * (20) get JDFNumberList attribute NumberValue
          * @return JDFNumberList the value of the attribute, null if a the
          *         attribute value is not a valid to create a JDFNumberList
          */
        public JDFNumberList getNumberValue()
        {
            String strAttrName = "";
            JDFNumberList nPlaceHolder = null;
            strAttrName = getAttribute(AttributeName.NUMBERVALUE, null, JDFConstants.EMPTYSTRING);
            try
            {
                nPlaceHolder = new JDFNumberList(strAttrName);
            }
            catch(DataFormatException e)
            {
                return null;
            }
            return nPlaceHolder;
        }



        
        /* ---------------------------------------------------------------------
        Methods for Attribute StringValue
        --------------------------------------------------------------------- */
        /**
          * (36) set attribute StringValue
          * @param value: the value to set the attribute to
          */
        public void setStringValue(String value)
        {
            setAttribute(AttributeName.STRINGVALUE, value, null);
        }



        /**
          * (23) get String attribute StringValue
          * @return the value of the attribute
          */
        public String getStringValue()
        {
            return getAttribute(AttributeName.STRINGVALUE, null, JDFConstants.EMPTYSTRING);
        }



        
        /* ---------------------------------------------------------------------
        Methods for Attribute BinaryValue
        --------------------------------------------------------------------- */
        /**
          * (36) set attribute BinaryValue
          * @param value: the value to set the attribute to
          */
        public void setBinaryValue(String value)
        {
            setAttribute(AttributeName.BINARYVALUE, value, null);
        }



        /**
          * (23) get String attribute BinaryValue
          * @return the value of the attribute
          */
        public String getBinaryValue()
        {
            return getAttribute(AttributeName.BINARYVALUE, null, JDFConstants.EMPTYSTRING);
        }



}// end namespace JDF
