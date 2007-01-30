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

import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.zip.DataFormatException;

import org.apache.commons.lang.enums.ValuedEnum;
import org.apache.xerces.dom.CoreDocumentImpl;
import org.cip4.jdflib.core.AtrInfoTable;
import org.cip4.jdflib.core.AttributeInfo;
import org.cip4.jdflib.core.AttributeName;
import org.cip4.jdflib.core.JDFConstants;
import org.cip4.jdflib.core.JDFElement;
import org.cip4.jdflib.datatypes.JDFRectangle;
    /*
    *****************************************************************************
    class JDFAutoPDFXParams : public JDFElement

    *****************************************************************************
    */

public abstract class JDFAutoPDFXParams extends JDFElement
{

    private static final long serialVersionUID = 1L;

    private static AtrInfoTable[] atrInfoTable = new AtrInfoTable[11];
    static
    {
        atrInfoTable[0] = new AtrInfoTable(AttributeName.PDFX1ACHECK, 0x33333311, AttributeInfo.EnumAttributeType.boolean_, null, "false");
        atrInfoTable[1] = new AtrInfoTable(AttributeName.PDFX3CHECK, 0x33333311, AttributeInfo.EnumAttributeType.boolean_, null, "false");
        atrInfoTable[2] = new AtrInfoTable(AttributeName.PDFXBLEEDBOXTOTRIMBOXOFFSET, 0x33333311, AttributeInfo.EnumAttributeType.rectangle, null, null);
        atrInfoTable[3] = new AtrInfoTable(AttributeName.PDFXCOMPLIANTPDFONLY, 0x33333311, AttributeInfo.EnumAttributeType.boolean_, null, "false");
        atrInfoTable[4] = new AtrInfoTable(AttributeName.PDFXOUTPUTCONDITION, 0x33333311, AttributeInfo.EnumAttributeType.string, null, null);
        atrInfoTable[5] = new AtrInfoTable(AttributeName.PDFXOUTPUTINTENTPROFILE, 0x33333311, AttributeInfo.EnumAttributeType.string, null, null);
        atrInfoTable[6] = new AtrInfoTable(AttributeName.PDFXNOTRIMBOXERROR, 0x33333311, AttributeInfo.EnumAttributeType.boolean_, null, "true");
        atrInfoTable[7] = new AtrInfoTable(AttributeName.PDFXREGISTRYNAME, 0x33333311, AttributeInfo.EnumAttributeType.URL, null, null);
        atrInfoTable[8] = new AtrInfoTable(AttributeName.PDFXSETBLEEDBOXTOMEDIABOX, 0x33333311, AttributeInfo.EnumAttributeType.boolean_, null, "true");
        atrInfoTable[9] = new AtrInfoTable(AttributeName.PDFXTRAPPED, 0x33333311, AttributeInfo.EnumAttributeType.enumeration, EnumPDFXTrapped.getEnum(0), null);
        atrInfoTable[10] = new AtrInfoTable(AttributeName.PDFXTRIMBOXTOMEDIABOXOFFSET, 0x33333311, AttributeInfo.EnumAttributeType.rectangle, null, null);
    }
    
    protected AttributeInfo getTheAttributeInfo()
    {
        return super.getTheAttributeInfo().updateReplace(atrInfoTable);
    }



    /**
     * Constructor for JDFAutoPDFXParams
     * @param myOwnerDocument
     * @param qualifiedName
     */
    protected JDFAutoPDFXParams(
        CoreDocumentImpl myOwnerDocument,
        String qualifiedName)
    {
        super(myOwnerDocument, qualifiedName);
    }

    /**
     * Constructor for JDFAutoPDFXParams
     * @param myOwnerDocument
     * @param myNamespaceURI
     * @param qualifiedName
     */
    protected JDFAutoPDFXParams(
        CoreDocumentImpl myOwnerDocument,
        String myNamespaceURI,
        String qualifiedName)
    {
        super(myOwnerDocument, myNamespaceURI, qualifiedName);
    }

    /**
     * Constructor for JDFAutoPDFXParams
     * @param myOwnerDocument
     * @param myNamespaceURI
     * @param qualifiedName
     * @param myLocalName
     */
    protected JDFAutoPDFXParams(
        CoreDocumentImpl myOwnerDocument,
        String myNamespaceURI,
        String qualifiedName,
        String myLocalName)
    {
        super(myOwnerDocument, myNamespaceURI, qualifiedName, myLocalName);
    }


    public String toString()
    {
        return " JDFAutoPDFXParams[  --> " + super.toString() + " ]";
    }


        /**
        * Enumeration strings for PDFXTrapped
        */

        public static class EnumPDFXTrapped extends ValuedEnum
        {
            private static final long serialVersionUID = 1L;
            private static int m_startValue = 0;

            private EnumPDFXTrapped(String name)
            {
                super(name, m_startValue++);
            }

            public static EnumPDFXTrapped getEnum(String enumName)
            {
                return (EnumPDFXTrapped) getEnum(EnumPDFXTrapped.class, enumName);
            }

            public static EnumPDFXTrapped getEnum(int enumValue)
            {
                return (EnumPDFXTrapped) getEnum(EnumPDFXTrapped.class, enumValue);
            }

            public static Map getEnumMap()
            {
                return getEnumMap(EnumPDFXTrapped.class);
            }

            public static List getEnumList()
            {
                return getEnumList(EnumPDFXTrapped.class);
            }

            public static Iterator iterator()
            {
                return iterator(EnumPDFXTrapped.class);
            }

            public static final EnumPDFXTrapped Unknown = new EnumPDFXTrapped("Unknown");
            public static final EnumPDFXTrapped True = new EnumPDFXTrapped("True");
            public static final EnumPDFXTrapped False = new EnumPDFXTrapped("False");
        }      



/* ************************************************************************
 * Attribute getter / setter
 * ************************************************************************
 */
        
        /* ---------------------------------------------------------------------
        Methods for Attribute PDFX1aCheck
        --------------------------------------------------------------------- */
        /**
          * (36) set attribute PDFX1aCheck
          * @param value: the value to set the attribute to
          */
        public void setPDFX1aCheck(boolean value)
        {
            setAttribute(AttributeName.PDFX1ACHECK, value, null);
        }



        /**
          * (18) get boolean attribute PDFX1aCheck
          * @return boolean the value of the attribute
          */
        public boolean getPDFX1aCheck()
        {
            return getBoolAttribute(AttributeName.PDFX1ACHECK, null, false);
        }



        
        /* ---------------------------------------------------------------------
        Methods for Attribute PDFX3Check
        --------------------------------------------------------------------- */
        /**
          * (36) set attribute PDFX3Check
          * @param value: the value to set the attribute to
          */
        public void setPDFX3Check(boolean value)
        {
            setAttribute(AttributeName.PDFX3CHECK, value, null);
        }



        /**
          * (18) get boolean attribute PDFX3Check
          * @return boolean the value of the attribute
          */
        public boolean getPDFX3Check()
        {
            return getBoolAttribute(AttributeName.PDFX3CHECK, null, false);
        }



        
        /* ---------------------------------------------------------------------
        Methods for Attribute PDFXBleedBoxToTrimBoxOffset
        --------------------------------------------------------------------- */
        /**
          * (36) set attribute PDFXBleedBoxToTrimBoxOffset
          * @param value: the value to set the attribute to
          */
        public void setPDFXBleedBoxToTrimBoxOffset(JDFRectangle value)
        {
            setAttribute(AttributeName.PDFXBLEEDBOXTOTRIMBOXOFFSET, value, null);
        }



        /**
          * (20) get JDFRectangle attribute PDFXBleedBoxToTrimBoxOffset
          * @return JDFRectanglethe value of the attribute, null if a the
          *         attribute value is not a valid to create a JDFRectangle
          */
        public JDFRectangle getPDFXBleedBoxToTrimBoxOffset()
        {
            String strAttrName = "";
            JDFRectangle nPlaceHolder = null;
            strAttrName = getAttribute(AttributeName.PDFXBLEEDBOXTOTRIMBOXOFFSET, null, JDFConstants.EMPTYSTRING);
            try
            {
                nPlaceHolder = new JDFRectangle(strAttrName);
            }
            catch(DataFormatException e)
            {
                return null;
            }
            return nPlaceHolder;
        }



        
        /* ---------------------------------------------------------------------
        Methods for Attribute PDFXCompliantPDFOnly
        --------------------------------------------------------------------- */
        /**
          * (36) set attribute PDFXCompliantPDFOnly
          * @param value: the value to set the attribute to
          */
        public void setPDFXCompliantPDFOnly(boolean value)
        {
            setAttribute(AttributeName.PDFXCOMPLIANTPDFONLY, value, null);
        }



        /**
          * (18) get boolean attribute PDFXCompliantPDFOnly
          * @return boolean the value of the attribute
          */
        public boolean getPDFXCompliantPDFOnly()
        {
            return getBoolAttribute(AttributeName.PDFXCOMPLIANTPDFONLY, null, false);
        }



        
        /* ---------------------------------------------------------------------
        Methods for Attribute PDFXOutputCondition
        --------------------------------------------------------------------- */
        /**
          * (36) set attribute PDFXOutputCondition
          * @param value: the value to set the attribute to
          */
        public void setPDFXOutputCondition(String value)
        {
            setAttribute(AttributeName.PDFXOUTPUTCONDITION, value, null);
        }



        /**
          * (23) get String attribute PDFXOutputCondition
          * @return the value of the attribute
          */
        public String getPDFXOutputCondition()
        {
            return getAttribute(AttributeName.PDFXOUTPUTCONDITION, null, JDFConstants.EMPTYSTRING);
        }



        
        /* ---------------------------------------------------------------------
        Methods for Attribute PDFXOutputIntentProfile
        --------------------------------------------------------------------- */
        /**
          * (36) set attribute PDFXOutputIntentProfile
          * @param value: the value to set the attribute to
          */
        public void setPDFXOutputIntentProfile(String value)
        {
            setAttribute(AttributeName.PDFXOUTPUTINTENTPROFILE, value, null);
        }



        /**
          * (23) get String attribute PDFXOutputIntentProfile
          * @return the value of the attribute
          */
        public String getPDFXOutputIntentProfile()
        {
            return getAttribute(AttributeName.PDFXOUTPUTINTENTPROFILE, null, JDFConstants.EMPTYSTRING);
        }



        
        /* ---------------------------------------------------------------------
        Methods for Attribute PDFXNoTrimBoxError
        --------------------------------------------------------------------- */
        /**
          * (36) set attribute PDFXNoTrimBoxError
          * @param value: the value to set the attribute to
          */
        public void setPDFXNoTrimBoxError(boolean value)
        {
            setAttribute(AttributeName.PDFXNOTRIMBOXERROR, value, null);
        }



        /**
          * (18) get boolean attribute PDFXNoTrimBoxError
          * @return boolean the value of the attribute
          */
        public boolean getPDFXNoTrimBoxError()
        {
            return getBoolAttribute(AttributeName.PDFXNOTRIMBOXERROR, null, true);
        }



        
        /* ---------------------------------------------------------------------
        Methods for Attribute PDFXRegistryName
        --------------------------------------------------------------------- */
        /**
          * (36) set attribute PDFXRegistryName
          * @param value: the value to set the attribute to
          */
        public void setPDFXRegistryName(String value)
        {
            setAttribute(AttributeName.PDFXREGISTRYNAME, value, null);
        }



        /**
          * (23) get String attribute PDFXRegistryName
          * @return the value of the attribute
          */
        public String getPDFXRegistryName()
        {
            return getAttribute(AttributeName.PDFXREGISTRYNAME, null, JDFConstants.EMPTYSTRING);
        }



        
        /* ---------------------------------------------------------------------
        Methods for Attribute PDFXSetBleedBoxToMediaBox
        --------------------------------------------------------------------- */
        /**
          * (36) set attribute PDFXSetBleedBoxToMediaBox
          * @param value: the value to set the attribute to
          */
        public void setPDFXSetBleedBoxToMediaBox(boolean value)
        {
            setAttribute(AttributeName.PDFXSETBLEEDBOXTOMEDIABOX, value, null);
        }



        /**
          * (18) get boolean attribute PDFXSetBleedBoxToMediaBox
          * @return boolean the value of the attribute
          */
        public boolean getPDFXSetBleedBoxToMediaBox()
        {
            return getBoolAttribute(AttributeName.PDFXSETBLEEDBOXTOMEDIABOX, null, true);
        }



        
        /* ---------------------------------------------------------------------
        Methods for Attribute PDFXTrapped
        --------------------------------------------------------------------- */
        /**
          * (5) set attribute PDFXTrapped
          * @param enumVar: the enumVar to set the attribute to
          */
        public void setPDFXTrapped(EnumPDFXTrapped enumVar)
        {
            setAttribute(AttributeName.PDFXTRAPPED, enumVar.getName(), null);
        }



        /**
          * (9) get attribute PDFXTrapped
          * @return the value of the attribute
          */
        public EnumPDFXTrapped getPDFXTrapped()
        {
            return EnumPDFXTrapped.getEnum(getAttribute(AttributeName.PDFXTRAPPED, null, null));
        }



        
        /* ---------------------------------------------------------------------
        Methods for Attribute PDFXTrimBoxToMediaBoxOffset
        --------------------------------------------------------------------- */
        /**
          * (36) set attribute PDFXTrimBoxToMediaBoxOffset
          * @param value: the value to set the attribute to
          */
        public void setPDFXTrimBoxToMediaBoxOffset(JDFRectangle value)
        {
            setAttribute(AttributeName.PDFXTRIMBOXTOMEDIABOXOFFSET, value, null);
        }



        /**
          * (20) get JDFRectangle attribute PDFXTrimBoxToMediaBoxOffset
          * @return JDFRectanglethe value of the attribute, null if a the
          *         attribute value is not a valid to create a JDFRectangle
          */
        public JDFRectangle getPDFXTrimBoxToMediaBoxOffset()
        {
            String strAttrName = "";
            JDFRectangle nPlaceHolder = null;
            strAttrName = getAttribute(AttributeName.PDFXTRIMBOXTOMEDIABOXOFFSET, null, JDFConstants.EMPTYSTRING);
            try
            {
                nPlaceHolder = new JDFRectangle(strAttrName);
            }
            catch(DataFormatException e)
            {
                return null;
            }
            return nPlaceHolder;
        }



}// end namespace JDF
