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
import org.cip4.jdflib.datatypes.JDFNumberList;
import org.cip4.jdflib.resource.JDFResource;
    /*
    *****************************************************************************
    class JDFAutoThreadSealingParams : public JDFResource

    *****************************************************************************
    */

public abstract class JDFAutoThreadSealingParams extends JDFResource
{

    private static final long serialVersionUID = 1L;

    private static AtrInfoTable[] atrInfoTable = new AtrInfoTable[6];
    static
    {
        atrInfoTable[0] = new AtrInfoTable(AttributeName.BLINDSTITCH, 0x33333331, AttributeInfo.EnumAttributeType.boolean_, null, null);
        atrInfoTable[1] = new AtrInfoTable(AttributeName.THREADMATERIAL, 0x33333331, AttributeInfo.EnumAttributeType.enumeration, EnumThreadMaterial.getEnum(0), null);
        atrInfoTable[2] = new AtrInfoTable(AttributeName.THREADPOSITIONS, 0x33333331, AttributeInfo.EnumAttributeType.string, null, null);
        atrInfoTable[3] = new AtrInfoTable(AttributeName.THREADLENGTH, 0x33333331, AttributeInfo.EnumAttributeType.double_, null, null);
        atrInfoTable[4] = new AtrInfoTable(AttributeName.THREADSTITCHWIDTH, 0x33333331, AttributeInfo.EnumAttributeType.double_, null, null);
        atrInfoTable[5] = new AtrInfoTable(AttributeName.SEALINGTEMPERATURE, 0x33333331, AttributeInfo.EnumAttributeType.integer, null, null);
    }
    
    protected AttributeInfo getTheAttributeInfo()
    {
        return super.getTheAttributeInfo().updateReplace(atrInfoTable);
    }



    /**
     * Constructor for JDFAutoThreadSealingParams
     * @param myOwnerDocument
     * @param qualifiedName
     */
    protected JDFAutoThreadSealingParams(
        CoreDocumentImpl myOwnerDocument,
        String qualifiedName)
    {
        super(myOwnerDocument, qualifiedName);
    }

    /**
     * Constructor for JDFAutoThreadSealingParams
     * @param myOwnerDocument
     * @param myNamespaceURI
     * @param qualifiedName
     */
    protected JDFAutoThreadSealingParams(
        CoreDocumentImpl myOwnerDocument,
        String myNamespaceURI,
        String qualifiedName)
    {
        super(myOwnerDocument, myNamespaceURI, qualifiedName);
    }

    /**
     * Constructor for JDFAutoThreadSealingParams
     * @param myOwnerDocument
     * @param myNamespaceURI
     * @param qualifiedName
     * @param myLocalName
     */
    protected JDFAutoThreadSealingParams(
        CoreDocumentImpl myOwnerDocument,
        String myNamespaceURI,
        String qualifiedName,
        String myLocalName)
    {
        super(myOwnerDocument, myNamespaceURI, qualifiedName, myLocalName);
    }


    public String toString()
    {
        return " JDFAutoThreadSealingParams[  --> " + super.toString() + " ]";
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
        * Enumeration strings for ThreadMaterial
        */

        public static class EnumThreadMaterial extends ValuedEnum
        {
            private static final long serialVersionUID = 1L;
            private static int m_startValue = 0;

            private EnumThreadMaterial(String name)
            {
                super(name, m_startValue++);
            }

            public static EnumThreadMaterial getEnum(String enumName)
            {
                return (EnumThreadMaterial) getEnum(EnumThreadMaterial.class, enumName);
            }

            public static EnumThreadMaterial getEnum(int enumValue)
            {
                return (EnumThreadMaterial) getEnum(EnumThreadMaterial.class, enumValue);
            }

            public static Map getEnumMap()
            {
                return getEnumMap(EnumThreadMaterial.class);
            }

            public static List getEnumList()
            {
                return getEnumList(EnumThreadMaterial.class);
            }

            public static Iterator iterator()
            {
                return iterator(EnumThreadMaterial.class);
            }

            public static final EnumThreadMaterial Cotton = new EnumThreadMaterial("Cotton");
            public static final EnumThreadMaterial Nylon = new EnumThreadMaterial("Nylon");
            public static final EnumThreadMaterial Polyester = new EnumThreadMaterial("Polyester");
        }      



/* ************************************************************************
 * Attribute getter / setter
 * ************************************************************************
 */
        
        /* ---------------------------------------------------------------------
        Methods for Attribute BlindStitch
        --------------------------------------------------------------------- */
        /**
          * (36) set attribute BlindStitch
          * @param value: the value to set the attribute to
          */
        public void setBlindStitch(boolean value)
        {
            setAttribute(AttributeName.BLINDSTITCH, value, null);
        }

        /**
          * (18) get boolean attribute BlindStitch
          * @return boolean the value of the attribute
          */
        public boolean getBlindStitch()
        {
            return getBoolAttribute(AttributeName.BLINDSTITCH, null, false);
        }

        
        /* ---------------------------------------------------------------------
        Methods for Attribute ThreadMaterial
        --------------------------------------------------------------------- */
        /**
          * (5) set attribute ThreadMaterial
          * @param enumVar: the enumVar to set the attribute to
          */
        public void setThreadMaterial(EnumThreadMaterial enumVar)
        {
            setAttribute(AttributeName.THREADMATERIAL, enumVar.getName(), null);
        }

        /**
          * (9) get attribute ThreadMaterial
          * @return the value of the attribute
          */
        public EnumThreadMaterial getThreadMaterial()
        {
            return EnumThreadMaterial.getEnum(getAttribute(AttributeName.THREADMATERIAL, null, null));
        }

        
        /* ---------------------------------------------------------------------
        Methods for Attribute ThreadPositions
        --------------------------------------------------------------------- */
        /**
          * (36) set attribute ThreadPositions
          * @param value: the value to set the attribute to
          */
        public void setThreadPositions(JDFNumberList value)
        {
            setAttribute(AttributeName.THREADPOSITIONS, value, null);
        }

        /**
          * (20) get JDFNumberList attribute ThreadPositions
          * @return JDFNumberList the value of the attribute, null if a the
          *         attribute value is not a valid to create a JDFNumberList
          */
        public JDFNumberList getThreadPositions()
        {
            String strAttrName = "";
            JDFNumberList nPlaceHolder = null;
            strAttrName = getAttribute(AttributeName.THREADPOSITIONS, null, JDFConstants.EMPTYSTRING);
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
        Methods for Attribute ThreadLength
        --------------------------------------------------------------------- */
        /**
          * (36) set attribute ThreadLength
          * @param value: the value to set the attribute to
          */
        public void setThreadLength(double value)
        {
            setAttribute(AttributeName.THREADLENGTH, value, null);
        }

        /**
          * (17) get double attribute ThreadLength
          * @return double the value of the attribute
          */
        public double getThreadLength()
        {
            return getRealAttribute(AttributeName.THREADLENGTH, null, 0.0);
        }

        
        /* ---------------------------------------------------------------------
        Methods for Attribute ThreadStitchWidth
        --------------------------------------------------------------------- */
        /**
          * (36) set attribute ThreadStitchWidth
          * @param value: the value to set the attribute to
          */
        public void setThreadStitchWidth(double value)
        {
            setAttribute(AttributeName.THREADSTITCHWIDTH, value, null);
        }

        /**
          * (17) get double attribute ThreadStitchWidth
          * @return double the value of the attribute
          */
        public double getThreadStitchWidth()
        {
            return getRealAttribute(AttributeName.THREADSTITCHWIDTH, null, 0.0);
        }

        
        /* ---------------------------------------------------------------------
        Methods for Attribute SealingTemperature
        --------------------------------------------------------------------- */
        /**
          * (36) set attribute SealingTemperature
          * @param value: the value to set the attribute to
          */
        public void setSealingTemperature(int value)
        {
            setAttribute(AttributeName.SEALINGTEMPERATURE, value, null);
        }

        /**
          * (15) get int attribute SealingTemperature
          * @return int the value of the attribute
          */
        public int getSealingTemperature()
        {
            return getIntAttribute(AttributeName.SEALINGTEMPERATURE, null, 0);
        }

}// end namespace JDF
