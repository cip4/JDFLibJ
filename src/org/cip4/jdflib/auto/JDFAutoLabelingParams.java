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
import org.cip4.jdflib.datatypes.JDFMatrix;
import org.cip4.jdflib.resource.JDFResource;

public abstract class JDFAutoLabelingParams extends JDFResource
{

    private static final long serialVersionUID = 1L;

    private static AtrInfoTable[] atrInfoTable = new AtrInfoTable[3];
    static
    {
        atrInfoTable[0] = new AtrInfoTable(AttributeName.CTM, 0x33333331, AttributeInfo.EnumAttributeType.matrix, null, null);
        atrInfoTable[1] = new AtrInfoTable(AttributeName.APPLICATION, 0x33333331, AttributeInfo.EnumAttributeType.NMTOKEN, null, null);
        atrInfoTable[2] = new AtrInfoTable(AttributeName.POSITION, 0x33333331, AttributeInfo.EnumAttributeType.enumeration, EnumPosition.getEnum(0), null);
    }
    
    protected AttributeInfo getTheAttributeInfo()
    {
        return super.getTheAttributeInfo().updateReplace(atrInfoTable);
    }



    /**
     * Constructor for JDFAutoLabelingParams
     * @param myOwnerDocument
     * @param qualifiedName
     */
    protected JDFAutoLabelingParams(
        CoreDocumentImpl myOwnerDocument,
        String qualifiedName)
    {
        super(myOwnerDocument, qualifiedName);
    }

    /**
     * Constructor for JDFAutoLabelingParams
     * @param myOwnerDocument
     * @param myNamespaceURI
     * @param qualifiedName
     */
    protected JDFAutoLabelingParams(
        CoreDocumentImpl myOwnerDocument,
        String myNamespaceURI,
        String qualifiedName)
    {
        super(myOwnerDocument, myNamespaceURI, qualifiedName);
    }

    /**
     * Constructor for JDFAutoLabelingParams
     * @param myOwnerDocument
     * @param myNamespaceURI
     * @param qualifiedName
     * @param myLocalName
     */
    protected JDFAutoLabelingParams(
        CoreDocumentImpl myOwnerDocument,
        String myNamespaceURI,
        String qualifiedName,
        String myLocalName)
    {
        super(myOwnerDocument, myNamespaceURI, qualifiedName, myLocalName);
    }


    public String toString()
    {
        return " JDFAutoLabelingParams[  --> " + super.toString() + " ]";
    }


    public boolean  init()
    {
        boolean bRet = super.init();
        setResourceClass(JDFResource.EnumResourceClass.Parameter);
        return bRet;
    }


    public EnumResourceClass getValidClass()
    {
        return JDFResource.EnumResourceClass.Parameter;
    }


        /**
        * Enumeration strings for Position
        */

        public static class EnumPosition extends ValuedEnum
        {
            private static final long serialVersionUID = 1L;
            private static int m_startValue = 0;

            private EnumPosition(String name)
            {
                super(name, m_startValue++);
            }

            public static EnumPosition getEnum(String enumName)
            {
                return (EnumPosition) getEnum(EnumPosition.class, enumName);
            }

            public static EnumPosition getEnum(int enumValue)
            {
                return (EnumPosition) getEnum(EnumPosition.class, enumValue);
            }

            public static Map getEnumMap()
            {
                return getEnumMap(EnumPosition.class);
            }

            public static List getEnumList()
            {
                return getEnumList(EnumPosition.class);
            }

            public static Iterator iterator()
            {
                return iterator(EnumPosition.class);
            }

            public static final EnumPosition Top = new EnumPosition("Top");
            public static final EnumPosition Bottom = new EnumPosition("Bottom");
            public static final EnumPosition Left = new EnumPosition("Left");
            public static final EnumPosition Right = new EnumPosition("Right");
            public static final EnumPosition Front = new EnumPosition("Front");
            public static final EnumPosition Back = new EnumPosition("Back");
        }      



/* ************************************************************************
 * Attribute getter / setter
 * ************************************************************************
 */
        
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
          * @return JDFMatrix the value of the attribute, null if a the
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
        Methods for Attribute Application
        --------------------------------------------------------------------- */
        /**
          * (36) set attribute Application
          * @param value: the value to set the attribute to
          */
        public void setApplication(String value)
        {
            setAttribute(AttributeName.APPLICATION, value, null);
        }

        /**
          * (23) get String attribute Application
          * @return the value of the attribute
          */
        public String getApplication()
        {
            return getAttribute(AttributeName.APPLICATION, null, JDFConstants.EMPTYSTRING);
        }

        
        /* ---------------------------------------------------------------------
        Methods for Attribute Position
        --------------------------------------------------------------------- */
        /**
          * (5) set attribute Position
          * @param enumVar: the enumVar to set the attribute to
          */
        public void setPosition(EnumPosition enumVar)
        {
            setAttribute(AttributeName.POSITION, enumVar==null ? null : enumVar.getName(), null);
        }

        /**
          * (9) get attribute Position
          * @return the value of the attribute
          */
        public EnumPosition getPosition()
        {
            return EnumPosition.getEnum(getAttribute(AttributeName.POSITION, null, null));
        }

}// end namespace JDF
