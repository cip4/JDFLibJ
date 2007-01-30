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
import org.cip4.jdflib.datatypes.JDFXYPair;
    /*
    *****************************************************************************
    class JDFAutoCut : public JDFElement

    *****************************************************************************
    */

public abstract class JDFAutoCut extends JDFElement
{

    private static final long serialVersionUID = 1L;

    private static AtrInfoTable[] atrInfoTable = new AtrInfoTable[5];
    static
    {
        atrInfoTable[0] = new AtrInfoTable(AttributeName.RELATIVESTARTPOSITION, 0x33333311, AttributeInfo.EnumAttributeType.XYPair, null, null);
        atrInfoTable[1] = new AtrInfoTable(AttributeName.RELATIVEWORKINGPATH, 0x33333311, AttributeInfo.EnumAttributeType.XYPair, null, null);
        atrInfoTable[2] = new AtrInfoTable(AttributeName.STARTPOSITION, 0x33333331, AttributeInfo.EnumAttributeType.XYPair, null, null);
        atrInfoTable[3] = new AtrInfoTable(AttributeName.WORKINGPATH, 0x33333331, AttributeInfo.EnumAttributeType.XYPair, null, null);
        atrInfoTable[4] = new AtrInfoTable(AttributeName.WORKINGDIRECTION, 0x22222221, AttributeInfo.EnumAttributeType.enumeration, EnumWorkingDirection.getEnum(0), null);
    }
    
    protected AttributeInfo getTheAttributeInfo()
    {
        return super.getTheAttributeInfo().updateReplace(atrInfoTable);
    }



    /**
     * Constructor for JDFAutoCut
     * @param myOwnerDocument
     * @param qualifiedName
     */
    protected JDFAutoCut(
        CoreDocumentImpl myOwnerDocument,
        String qualifiedName)
    {
        super(myOwnerDocument, qualifiedName);
    }

    /**
     * Constructor for JDFAutoCut
     * @param myOwnerDocument
     * @param myNamespaceURI
     * @param qualifiedName
     */
    protected JDFAutoCut(
        CoreDocumentImpl myOwnerDocument,
        String myNamespaceURI,
        String qualifiedName)
    {
        super(myOwnerDocument, myNamespaceURI, qualifiedName);
    }

    /**
     * Constructor for JDFAutoCut
     * @param myOwnerDocument
     * @param myNamespaceURI
     * @param qualifiedName
     * @param myLocalName
     */
    protected JDFAutoCut(
        CoreDocumentImpl myOwnerDocument,
        String myNamespaceURI,
        String qualifiedName,
        String myLocalName)
    {
        super(myOwnerDocument, myNamespaceURI, qualifiedName, myLocalName);
    }


    public String toString()
    {
        return " JDFAutoCut[  --> " + super.toString() + " ]";
    }


        /**
        * Enumeration strings for WorkingDirection
        */

        public static class EnumWorkingDirection extends ValuedEnum
        {
            private static final long serialVersionUID = 1L;
            private static int m_startValue = 0;

            private EnumWorkingDirection(String name)
            {
                super(name, m_startValue++);
            }

            public static EnumWorkingDirection getEnum(String enumName)
            {
                return (EnumWorkingDirection) getEnum(EnumWorkingDirection.class, enumName);
            }

            public static EnumWorkingDirection getEnum(int enumValue)
            {
                return (EnumWorkingDirection) getEnum(EnumWorkingDirection.class, enumValue);
            }

            public static Map getEnumMap()
            {
                return getEnumMap(EnumWorkingDirection.class);
            }

            public static List getEnumList()
            {
                return getEnumList(EnumWorkingDirection.class);
            }

            public static Iterator iterator()
            {
                return iterator(EnumWorkingDirection.class);
            }

            public static final EnumWorkingDirection Top = new EnumWorkingDirection("Top");
            public static final EnumWorkingDirection Bottom = new EnumWorkingDirection("Bottom");
        }      



/* ************************************************************************
 * Attribute getter / setter
 * ************************************************************************
 */
        
        /* ---------------------------------------------------------------------
        Methods for Attribute RelativeStartPosition
        --------------------------------------------------------------------- */
        /**
          * (36) set attribute RelativeStartPosition
          * @param value: the value to set the attribute to
          */
        public void setRelativeStartPosition(JDFXYPair value)
        {
            setAttribute(AttributeName.RELATIVESTARTPOSITION, value, null);
        }



        /**
          * (20) get JDFXYPair attribute RelativeStartPosition
          * @return JDFXYPairthe value of the attribute, null if a the
          *         attribute value is not a valid to create a JDFXYPair
          */
        public JDFXYPair getRelativeStartPosition()
        {
            String strAttrName = "";
            JDFXYPair nPlaceHolder = null;
            strAttrName = getAttribute(AttributeName.RELATIVESTARTPOSITION, null, JDFConstants.EMPTYSTRING);
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
        Methods for Attribute RelativeWorkingPath
        --------------------------------------------------------------------- */
        /**
          * (36) set attribute RelativeWorkingPath
          * @param value: the value to set the attribute to
          */
        public void setRelativeWorkingPath(JDFXYPair value)
        {
            setAttribute(AttributeName.RELATIVEWORKINGPATH, value, null);
        }



        /**
          * (20) get JDFXYPair attribute RelativeWorkingPath
          * @return JDFXYPairthe value of the attribute, null if a the
          *         attribute value is not a valid to create a JDFXYPair
          */
        public JDFXYPair getRelativeWorkingPath()
        {
            String strAttrName = "";
            JDFXYPair nPlaceHolder = null;
            strAttrName = getAttribute(AttributeName.RELATIVEWORKINGPATH, null, JDFConstants.EMPTYSTRING);
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
        Methods for Attribute StartPosition
        --------------------------------------------------------------------- */
        /**
          * (36) set attribute StartPosition
          * @param value: the value to set the attribute to
          */
        public void setStartPosition(JDFXYPair value)
        {
            setAttribute(AttributeName.STARTPOSITION, value, null);
        }



        /**
          * (20) get JDFXYPair attribute StartPosition
          * @return JDFXYPairthe value of the attribute, null if a the
          *         attribute value is not a valid to create a JDFXYPair
          */
        public JDFXYPair getStartPosition()
        {
            String strAttrName = "";
            JDFXYPair nPlaceHolder = null;
            strAttrName = getAttribute(AttributeName.STARTPOSITION, null, JDFConstants.EMPTYSTRING);
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
        Methods for Attribute WorkingPath
        --------------------------------------------------------------------- */
        /**
          * (36) set attribute WorkingPath
          * @param value: the value to set the attribute to
          */
        public void setWorkingPath(JDFXYPair value)
        {
            setAttribute(AttributeName.WORKINGPATH, value, null);
        }



        /**
          * (20) get JDFXYPair attribute WorkingPath
          * @return JDFXYPairthe value of the attribute, null if a the
          *         attribute value is not a valid to create a JDFXYPair
          */
        public JDFXYPair getWorkingPath()
        {
            String strAttrName = "";
            JDFXYPair nPlaceHolder = null;
            strAttrName = getAttribute(AttributeName.WORKINGPATH, null, JDFConstants.EMPTYSTRING);
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
        Methods for Attribute WorkingDirection
        --------------------------------------------------------------------- */
        /**
          * (5) set attribute WorkingDirection
          * @param enumVar: the enumVar to set the attribute to
          */
        public void setWorkingDirection(EnumWorkingDirection enumVar)
        {
            setAttribute(AttributeName.WORKINGDIRECTION, enumVar.getName(), null);
        }



        /**
          * (9) get attribute WorkingDirection
          * @return the value of the attribute
          */
        public EnumWorkingDirection getWorkingDirection()
        {
            return EnumWorkingDirection.getEnum(getAttribute(AttributeName.WORKINGDIRECTION, null, null));
        }



}// end namespace JDF
