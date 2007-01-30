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
import org.cip4.jdflib.datatypes.JDFMatrix;
import org.cip4.jdflib.datatypes.JDFRectangle;
import org.cip4.jdflib.datatypes.JDFXYPair;
    /*
    *****************************************************************************
    class JDFAutoContentObject : public JDFElement

    *****************************************************************************
    */

public abstract class JDFAutoContentObject extends JDFElement
{

    private static final long serialVersionUID = 1L;

    private static AtrInfoTable[] atrInfoTable = new AtrInfoTable[14];
    static
    {
        atrInfoTable[0] = new AtrInfoTable(AttributeName.TYPE, 0x44444443, AttributeInfo.EnumAttributeType.enumeration, EnumType.getEnum(0), null);
        atrInfoTable[1] = new AtrInfoTable(AttributeName.SOURCECLIPPATH, 0x33333333, AttributeInfo.EnumAttributeType.PDFPath, null, null);
        atrInfoTable[2] = new AtrInfoTable(AttributeName.SETORD, 0x33333331, AttributeInfo.EnumAttributeType.integer, null, null);
        atrInfoTable[3] = new AtrInfoTable(AttributeName.LAYERID, 0x33333331, AttributeInfo.EnumAttributeType.integer, null, null);
        atrInfoTable[4] = new AtrInfoTable(AttributeName.CTM, 0x22222222, AttributeInfo.EnumAttributeType.matrix, null, null);
        atrInfoTable[5] = new AtrInfoTable(AttributeName.ORD, 0x33333333, AttributeInfo.EnumAttributeType.integer, null, null);
        atrInfoTable[6] = new AtrInfoTable(AttributeName.TRIMSIZE, 0x33333311, AttributeInfo.EnumAttributeType.XYPair, null, null);
        atrInfoTable[7] = new AtrInfoTable(AttributeName.HALFTONEPHASEORIGIN, 0x33333333, AttributeInfo.EnumAttributeType.XYPair, null, "0 0");
        atrInfoTable[8] = new AtrInfoTable(AttributeName.ORDEXPRESSION, 0x33333333, AttributeInfo.EnumAttributeType.string, null, null);
        atrInfoTable[9] = new AtrInfoTable(AttributeName.ORDID, 0x33333331, AttributeInfo.EnumAttributeType.integer, null, null);
        atrInfoTable[10] = new AtrInfoTable(AttributeName.TRIMCTM, 0x33333331, AttributeInfo.EnumAttributeType.matrix, null, null);
        atrInfoTable[11] = new AtrInfoTable(AttributeName.DOCORD, 0x33333331, AttributeInfo.EnumAttributeType.integer, null, null);
        atrInfoTable[12] = new AtrInfoTable(AttributeName.CLIPBOX, 0x33333333, AttributeInfo.EnumAttributeType.rectangle, null, null);
        atrInfoTable[13] = new AtrInfoTable(AttributeName.CLIPPATH, 0x33333333, AttributeInfo.EnumAttributeType.PDFPath, null, null);
    }
    
    protected AttributeInfo getTheAttributeInfo()
    {
        return super.getTheAttributeInfo().updateReplace(atrInfoTable);
    }



    /**
     * Constructor for JDFAutoContentObject
     * @param myOwnerDocument
     * @param qualifiedName
     */
    protected JDFAutoContentObject(
        CoreDocumentImpl myOwnerDocument,
        String qualifiedName)
    {
        super(myOwnerDocument, qualifiedName);
    }

    /**
     * Constructor for JDFAutoContentObject
     * @param myOwnerDocument
     * @param myNamespaceURI
     * @param qualifiedName
     */
    protected JDFAutoContentObject(
        CoreDocumentImpl myOwnerDocument,
        String myNamespaceURI,
        String qualifiedName)
    {
        super(myOwnerDocument, myNamespaceURI, qualifiedName);
    }

    /**
     * Constructor for JDFAutoContentObject
     * @param myOwnerDocument
     * @param myNamespaceURI
     * @param qualifiedName
     * @param myLocalName
     */
    protected JDFAutoContentObject(
        CoreDocumentImpl myOwnerDocument,
        String myNamespaceURI,
        String qualifiedName,
        String myLocalName)
    {
        super(myOwnerDocument, myNamespaceURI, qualifiedName, myLocalName);
    }


    public String toString()
    {
        return " JDFAutoContentObject[  --> " + super.toString() + " ]";
    }


        /**
        * Enumeration strings for Type
        */

        public static class EnumType extends ValuedEnum
        {
            private static final long serialVersionUID = 1L;
            private static int m_startValue = 0;

            private EnumType(String name)
            {
                super(name, m_startValue++);
            }

            public static EnumType getEnum(String enumName)
            {
                return (EnumType) getEnum(EnumType.class, enumName);
            }

            public static EnumType getEnum(int enumValue)
            {
                return (EnumType) getEnum(EnumType.class, enumValue);
            }

            public static Map getEnumMap()
            {
                return getEnumMap(EnumType.class);
            }

            public static List getEnumList()
            {
                return getEnumList(EnumType.class);
            }

            public static Iterator iterator()
            {
                return iterator(EnumType.class);
            }

            public static final EnumType Content = new EnumType("Content");
            public static final EnumType Mark = new EnumType("Mark");
        }      



/* ************************************************************************
 * Attribute getter / setter
 * ************************************************************************
 */
        
        /* ---------------------------------------------------------------------
        Methods for Attribute Type
        --------------------------------------------------------------------- */
        /**
          * (5) set attribute Type
          * @param enumVar: the enumVar to set the attribute to
          */
        public void setType(EnumType enumVar)
        {
            setAttribute(AttributeName.TYPE, enumVar.getName(), null);
        }



        /**
          * (9) get attribute Type
          * @return the value of the attribute
          */
        public EnumType getType()
        {
            return EnumType.getEnum(getAttribute(AttributeName.TYPE, null, null));
        }



        
        /* ---------------------------------------------------------------------
        Methods for Attribute SourceClipPath
        --------------------------------------------------------------------- */
        /**
          * (36) set attribute SourceClipPath
          * @param value: the value to set the attribute to
          */
        public void setSourceClipPath(String value)
        {
            setAttribute(AttributeName.SOURCECLIPPATH, value, null);
        }



        /**
          * (23) get String attribute SourceClipPath
          * @return the value of the attribute
          */
        public String getSourceClipPath()
        {
            return getAttribute(AttributeName.SOURCECLIPPATH, null, JDFConstants.EMPTYSTRING);
        }



        
        /* ---------------------------------------------------------------------
        Methods for Attribute SetOrd
        --------------------------------------------------------------------- */
        /**
          * (36) set attribute SetOrd
          * @param value: the value to set the attribute to
          */
        public void setSetOrd(int value)
        {
            setAttribute(AttributeName.SETORD, value, null);
        }



        /**
          * (15) get int attribute SetOrd
          * @return int the value of the attribute
          */
        public int getSetOrd()
        {
            return getIntAttribute(AttributeName.SETORD, null, 0);
        }



        
        /* ---------------------------------------------------------------------
        Methods for Attribute LayerID
        --------------------------------------------------------------------- */
        /**
          * (36) set attribute LayerID
          * @param value: the value to set the attribute to
          */
        public void setLayerID(int value)
        {
            setAttribute(AttributeName.LAYERID, value, null);
        }



        /**
          * (15) get int attribute LayerID
          * @return int the value of the attribute
          */
        public int getLayerID()
        {
            return getIntAttribute(AttributeName.LAYERID, null, 0);
        }



        
        /* ---------------------------------------------------------------------
        Methods for Attribute CTM
        --------------------------------------------------------------------- */
        /**
          * (36) set attribute CTM
          * @param value: the value to set the attribute to
          */
        public void setCTM(JDFMatrix value)
        {
            setAttribute(AttributeName.CTM, value, null);
        }



        /**
          * (20) get JDFMatrix attribute CTM
          * @return JDFMatrixthe value of the attribute, null if a the
          *         attribute value is not a valid to create a JDFMatrix
          */
        public JDFMatrix getCTM()
        {
            String strAttrName = "";
            JDFMatrix nPlaceHolder = null;
            strAttrName = getAttribute(AttributeName.CTM, null, JDFConstants.EMPTYSTRING);
            try
            {
                nPlaceHolder = new JDFMatrix(strAttrName);
            }
            catch(DataFormatException e)
            {
                return null;
            }
            return nPlaceHolder;
        }



        
        /* ---------------------------------------------------------------------
        Methods for Attribute Ord
        --------------------------------------------------------------------- */
        /**
          * (36) set attribute Ord
          * @param value: the value to set the attribute to
          */
        public void setOrd(int value)
        {
            setAttribute(AttributeName.ORD, value, null);
        }



        /**
          * (15) get int attribute Ord
          * @return int the value of the attribute
          */
        public int getOrd()
        {
            return getIntAttribute(AttributeName.ORD, null, 0);
        }



        
        /* ---------------------------------------------------------------------
        Methods for Attribute TrimSize
        --------------------------------------------------------------------- */
        /**
          * (36) set attribute TrimSize
          * @param value: the value to set the attribute to
          */
        public void setTrimSize(JDFXYPair value)
        {
            setAttribute(AttributeName.TRIMSIZE, value, null);
        }



        /**
          * (20) get JDFXYPair attribute TrimSize
          * @return JDFXYPairthe value of the attribute, null if a the
          *         attribute value is not a valid to create a JDFXYPair
          */
        public JDFXYPair getTrimSize()
        {
            String strAttrName = "";
            JDFXYPair nPlaceHolder = null;
            strAttrName = getAttribute(AttributeName.TRIMSIZE, null, JDFConstants.EMPTYSTRING);
            try
            {
                nPlaceHolder = new JDFXYPair(strAttrName);
            }
            catch(DataFormatException e)
            {
                return null;
            }
            return nPlaceHolder;
        }



        
        /* ---------------------------------------------------------------------
        Methods for Attribute HalfTonePhaseOrigin
        --------------------------------------------------------------------- */
        /**
          * (36) set attribute HalfTonePhaseOrigin
          * @param value: the value to set the attribute to
          */
        public void setHalfTonePhaseOrigin(JDFXYPair value)
        {
            setAttribute(AttributeName.HALFTONEPHASEORIGIN, value, null);
        }



        /**
          * (20) get JDFXYPair attribute HalfTonePhaseOrigin
          * @return JDFXYPairthe value of the attribute, null if a the
          *         attribute value is not a valid to create a JDFXYPair
          */
        public JDFXYPair getHalfTonePhaseOrigin()
        {
            String strAttrName = "";
            JDFXYPair nPlaceHolder = null;
            strAttrName = getAttribute(AttributeName.HALFTONEPHASEORIGIN, null, JDFConstants.EMPTYSTRING);
            try
            {
                nPlaceHolder = new JDFXYPair(strAttrName);
            }
            catch(DataFormatException e)
            {
                return null;
            }
            return nPlaceHolder;
        }



        
        /* ---------------------------------------------------------------------
        Methods for Attribute OrdExpression
        --------------------------------------------------------------------- */
        /**
          * (36) set attribute OrdExpression
          * @param value: the value to set the attribute to
          */
        public void setOrdExpression(String value)
        {
            setAttribute(AttributeName.ORDEXPRESSION, value, null);
        }



        /**
          * (23) get String attribute OrdExpression
          * @return the value of the attribute
          */
        public String getOrdExpression()
        {
            return getAttribute(AttributeName.ORDEXPRESSION, null, JDFConstants.EMPTYSTRING);
        }



        
        /* ---------------------------------------------------------------------
        Methods for Attribute OrdID
        --------------------------------------------------------------------- */
        /**
          * (36) set attribute OrdID
          * @param value: the value to set the attribute to
          */
        public void setOrdID(int value)
        {
            setAttribute(AttributeName.ORDID, value, null);
        }



        /**
          * (15) get int attribute OrdID
          * @return int the value of the attribute
          */
        public int getOrdID()
        {
            return getIntAttribute(AttributeName.ORDID, null, 0);
        }



        
        /* ---------------------------------------------------------------------
        Methods for Attribute TrimCTM
        --------------------------------------------------------------------- */
        /**
          * (36) set attribute TrimCTM
          * @param value: the value to set the attribute to
          */
        public void setTrimCTM(JDFMatrix value)
        {
            setAttribute(AttributeName.TRIMCTM, value, null);
        }



        /**
          * (20) get JDFMatrix attribute TrimCTM
          * @return JDFMatrixthe value of the attribute, null if a the
          *         attribute value is not a valid to create a JDFMatrix
          */
        public JDFMatrix getTrimCTM()
        {
            String strAttrName = "";
            JDFMatrix nPlaceHolder = null;
            strAttrName = getAttribute(AttributeName.TRIMCTM, null, JDFConstants.EMPTYSTRING);
            try
            {
                nPlaceHolder = new JDFMatrix(strAttrName);
            }
            catch(DataFormatException e)
            {
                return null;
            }
            return nPlaceHolder;
        }



        
        /* ---------------------------------------------------------------------
        Methods for Attribute DocOrd
        --------------------------------------------------------------------- */
        /**
          * (36) set attribute DocOrd
          * @param value: the value to set the attribute to
          */
        public void setDocOrd(int value)
        {
            setAttribute(AttributeName.DOCORD, value, null);
        }



        /**
          * (15) get int attribute DocOrd
          * @return int the value of the attribute
          */
        public int getDocOrd()
        {
            return getIntAttribute(AttributeName.DOCORD, null, 0);
        }



        
        /* ---------------------------------------------------------------------
        Methods for Attribute ClipBox
        --------------------------------------------------------------------- */
        /**
          * (36) set attribute ClipBox
          * @param value: the value to set the attribute to
          */
        public void setClipBox(JDFRectangle value)
        {
            setAttribute(AttributeName.CLIPBOX, value, null);
        }



        /**
          * (20) get JDFRectangle attribute ClipBox
          * @return JDFRectanglethe value of the attribute, null if a the
          *         attribute value is not a valid to create a JDFRectangle
          */
        public JDFRectangle getClipBox()
        {
            String strAttrName = "";
            JDFRectangle nPlaceHolder = null;
            strAttrName = getAttribute(AttributeName.CLIPBOX, null, JDFConstants.EMPTYSTRING);
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
        Methods for Attribute ClipPath
        --------------------------------------------------------------------- */
        /**
          * (36) set attribute ClipPath
          * @param value: the value to set the attribute to
          */
        public void setClipPath(String value)
        {
            setAttribute(AttributeName.CLIPPATH, value, null);
        }



        /**
          * (23) get String attribute ClipPath
          * @return the value of the attribute
          */
        public String getClipPath()
        {
            return getAttribute(AttributeName.CLIPPATH, null, JDFConstants.EMPTYSTRING);
        }



}// end namespace JDF
