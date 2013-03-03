/*
 * The CIP4 Software License, Version 1.0
 *
 *
 * Copyright (c) 2001-2012 The International Cooperation for the Integration of
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

import org.apache.commons.lang.enums.ValuedEnum;
import org.apache.xerces.dom.CoreDocumentImpl;
import org.cip4.jdflib.core.AtrInfoTable;
import org.cip4.jdflib.core.AttributeInfo;
import org.cip4.jdflib.core.AttributeName;
import org.cip4.jdflib.core.JDFCoreConstants;
import org.cip4.jdflib.datatypes.JDFShape;
import org.cip4.jdflib.datatypes.JDFXYPair;
import org.cip4.jdflib.resource.JDFResource;
    /**
    *****************************************************************************
    class JDFAutoPackingParams : public JDFResource

    *****************************************************************************
    */

public abstract class JDFAutoPackingParams extends JDFResource
{

    private static final long serialVersionUID = 1L;

    private static AtrInfoTable[] atrInfoTable = new AtrInfoTable[14];
    static
    {
        atrInfoTable[0] = new AtrInfoTable(AttributeName.PALLETWRAPPING, 0x44444443, AttributeInfo.EnumAttributeType.enumeration, EnumPalletWrapping.getEnum(0), "None");
        atrInfoTable[1] = new AtrInfoTable(AttributeName.WRAPPINGMATERIAL, 0x44444443, AttributeInfo.EnumAttributeType.NMTOKEN, null, "None");
        atrInfoTable[2] = new AtrInfoTable(AttributeName.BOXEDQUANTITY, 0x44444443, AttributeInfo.EnumAttributeType.integer, null, null);
        atrInfoTable[3] = new AtrInfoTable(AttributeName.BOXSHAPE, 0x44444443, AttributeInfo.EnumAttributeType.shape, null, null);
        atrInfoTable[4] = new AtrInfoTable(AttributeName.CARTONQUANTITY, 0x44444443, AttributeInfo.EnumAttributeType.integer, null, null);
        atrInfoTable[5] = new AtrInfoTable(AttributeName.CARTONSHAPE, 0x44444443, AttributeInfo.EnumAttributeType.shape, null, null);
        atrInfoTable[6] = new AtrInfoTable(AttributeName.CARTONMAXWEIGHT, 0x44444443, AttributeInfo.EnumAttributeType.double_, null, null);
        atrInfoTable[7] = new AtrInfoTable(AttributeName.CARTONSTRENGTH, 0x44444443, AttributeInfo.EnumAttributeType.double_, null, null);
        atrInfoTable[8] = new AtrInfoTable(AttributeName.PALLETQUANTITY, 0x44444443, AttributeInfo.EnumAttributeType.integer, null, null);
        atrInfoTable[9] = new AtrInfoTable(AttributeName.PALLETSIZE, 0x44444443, AttributeInfo.EnumAttributeType.XYPair, null, null);
        atrInfoTable[10] = new AtrInfoTable(AttributeName.PALLETMAXHEIGHT, 0x44444443, AttributeInfo.EnumAttributeType.double_, null, null);
        atrInfoTable[11] = new AtrInfoTable(AttributeName.PALLETMAXWEIGHT, 0x44444443, AttributeInfo.EnumAttributeType.double_, null, null);
        atrInfoTable[12] = new AtrInfoTable(AttributeName.PALLETTYPE, 0x44444443, AttributeInfo.EnumAttributeType.enumeration, EnumPalletType.getEnum(0), null);
        atrInfoTable[13] = new AtrInfoTable(AttributeName.WRAPPEDQUANTITY, 0x44444443, AttributeInfo.EnumAttributeType.integer, null, null);
    }
    
    protected AttributeInfo getTheAttributeInfo()
    {
        return super.getTheAttributeInfo().updateReplace(atrInfoTable);
    }



    /**
     * Constructor for JDFAutoPackingParams
     * @param myOwnerDocument
     * @param qualifiedName
     */
    protected JDFAutoPackingParams(
        CoreDocumentImpl myOwnerDocument,
        String qualifiedName)
    {
        super(myOwnerDocument, qualifiedName);
    }

    /**
     * Constructor for JDFAutoPackingParams
     * @param myOwnerDocument
     * @param myNamespaceURI
     * @param qualifiedName
     */
    protected JDFAutoPackingParams(
        CoreDocumentImpl myOwnerDocument,
        String myNamespaceURI,
        String qualifiedName)
    {
        super(myOwnerDocument, myNamespaceURI, qualifiedName);
    }

    /**
     * Constructor for JDFAutoPackingParams
     * @param myOwnerDocument
     * @param myNamespaceURI
     * @param qualifiedName
     * @param myLocalName
     */
    protected JDFAutoPackingParams(
        CoreDocumentImpl myOwnerDocument,
        String myNamespaceURI,
        String qualifiedName,
        String myLocalName)
    {
        super(myOwnerDocument, myNamespaceURI, qualifiedName, myLocalName);
    }


    /**
     * @return  the string representation
     */
    @Override
    public String toString()
    {
        return " JDFAutoPackingParams[  --> " + super.toString() + " ]";
    }


    /**
     * @return  true if ok
     */
    @Override
    public boolean  init()
    {
        boolean bRet = super.init();
        setResourceClass(JDFResource.EnumResourceClass.Parameter);
        return bRet;
    }


    /**
     * @return the resource Class
     */
    @Override
    public EnumResourceClass getValidClass()
    {
        return JDFResource.EnumResourceClass.Parameter;
    }


        /**
        * Enumeration strings for PalletWrapping
        */

        public static class EnumPalletWrapping extends ValuedEnum
        {
            private static final long serialVersionUID = 1L;
            private static int m_startValue = 0;

            private EnumPalletWrapping(String name)
            {
                super(name, m_startValue++);
            }

    /**
     * @param enumName the string to convert
     * @return the enum
     */
            public static EnumPalletWrapping getEnum(String enumName)
            {
                return (EnumPalletWrapping) getEnum(EnumPalletWrapping.class, enumName);
            }

    /**
     * @param enumValue the integer to convert
     * @return the enum
     */
            public static EnumPalletWrapping getEnum(int enumValue)
            {
                return (EnumPalletWrapping) getEnum(EnumPalletWrapping.class, enumValue);
            }

    /**
     * @return the map of enums
     */
            public static Map getEnumMap()
            {
                return getEnumMap(EnumPalletWrapping.class);
            }

    /**
     * @return the list of enums
     */
            public static List getEnumList()
            {
                return getEnumList(EnumPalletWrapping.class);
            }

    /**
     * @return the iterator
     */
            public static Iterator iterator()
            {
                return iterator(EnumPalletWrapping.class);
            }

            public static final EnumPalletWrapping StretchWrap = new EnumPalletWrapping("StretchWrap");
            public static final EnumPalletWrapping Banding = new EnumPalletWrapping("Banding");
            public static final EnumPalletWrapping None = new EnumPalletWrapping("None");
        }      



        /**
        * Enumeration strings for PalletType
        */

        public static class EnumPalletType extends ValuedEnum
        {
            private static final long serialVersionUID = 1L;
            private static int m_startValue = 0;

            private EnumPalletType(String name)
            {
                super(name, m_startValue++);
            }

    /**
     * @param enumName the string to convert
     * @return the enum
     */
            public static EnumPalletType getEnum(String enumName)
            {
                return (EnumPalletType) getEnum(EnumPalletType.class, enumName);
            }

    /**
     * @param enumValue the integer to convert
     * @return the enum
     */
            public static EnumPalletType getEnum(int enumValue)
            {
                return (EnumPalletType) getEnum(EnumPalletType.class, enumValue);
            }

    /**
     * @return the map of enums
     */
            public static Map getEnumMap()
            {
                return getEnumMap(EnumPalletType.class);
            }

    /**
     * @return the list of enums
     */
            public static List getEnumList()
            {
                return getEnumList(EnumPalletType.class);
            }

    /**
     * @return the iterator
     */
            public static Iterator iterator()
            {
                return iterator(EnumPalletType.class);
            }

            public static final EnumPalletType         PalletType_2Way = new EnumPalletType("2Way");
            public static final EnumPalletType         PalletType_4Way = new EnumPalletType("4Way");
            public static final EnumPalletType Euro = new EnumPalletType("Euro");
        }      



/* ************************************************************************
 * Attribute getter / setter
 * ************************************************************************
 */
        
        /* ---------------------------------------------------------------------
        Methods for Attribute PalletWrapping
        --------------------------------------------------------------------- */
        /**
          * (5) set attribute PalletWrapping
          * @param enumVar the enumVar to set the attribute to
          */
        public void setPalletWrapping(EnumPalletWrapping enumVar)
        {
            setAttribute(AttributeName.PALLETWRAPPING, enumVar==null ? null : enumVar.getName(), null);
        }

        /**
          * (9) get attribute PalletWrapping
          * @return the value of the attribute
          */
        public EnumPalletWrapping getPalletWrapping()
        {
            return EnumPalletWrapping.getEnum(getAttribute(AttributeName.PALLETWRAPPING, null, "None"));
        }

        
        /* ---------------------------------------------------------------------
        Methods for Attribute WrappingMaterial
        --------------------------------------------------------------------- */
        /**
          * (36) set attribute WrappingMaterial
          * @param value the value to set the attribute to
          */
        public void setWrappingMaterial(String value)
        {
            setAttribute(AttributeName.WRAPPINGMATERIAL, value, null);
        }

        /**
          * (23) get String attribute WrappingMaterial
          * @return the value of the attribute
          */
        public String getWrappingMaterial()
        {
            return getAttribute(AttributeName.WRAPPINGMATERIAL, null, "None");
        }

        
        /* ---------------------------------------------------------------------
        Methods for Attribute BoxedQuantity
        --------------------------------------------------------------------- */
        /**
          * (36) set attribute BoxedQuantity
          * @param value the value to set the attribute to
          */
        public void setBoxedQuantity(int value)
        {
            setAttribute(AttributeName.BOXEDQUANTITY, value, null);
        }

        /**
          * (15) get int attribute BoxedQuantity
          * @return int the value of the attribute
          */
        public int getBoxedQuantity()
        {
            return getIntAttribute(AttributeName.BOXEDQUANTITY, null, 0);
        }

        
        /* ---------------------------------------------------------------------
        Methods for Attribute BoxShape
        --------------------------------------------------------------------- */
        /**
          * (36) set attribute BoxShape
          * @param value the value to set the attribute to
          */
        public void setBoxShape(JDFShape value)
        {
            setAttribute(AttributeName.BOXSHAPE, value, null);
        }

        /**
          * (20) get JDFShape attribute BoxShape
          * @return JDFShape the value of the attribute, null if a the
          *         attribute value is not a valid to create a JDFShape
          */
        public JDFShape getBoxShape()
        {
            String strAttrName = getAttribute(AttributeName.BOXSHAPE, null, JDFCoreConstants.EMPTYSTRING);
            JDFShape nPlaceHolder = JDFShape.createShape(strAttrName);
            return nPlaceHolder;
        }

        
        /* ---------------------------------------------------------------------
        Methods for Attribute CartonQuantity
        --------------------------------------------------------------------- */
        /**
          * (36) set attribute CartonQuantity
          * @param value the value to set the attribute to
          */
        public void setCartonQuantity(int value)
        {
            setAttribute(AttributeName.CARTONQUANTITY, value, null);
        }

        /**
          * (15) get int attribute CartonQuantity
          * @return int the value of the attribute
          */
        public int getCartonQuantity()
        {
            return getIntAttribute(AttributeName.CARTONQUANTITY, null, 0);
        }

        
        /* ---------------------------------------------------------------------
        Methods for Attribute CartonShape
        --------------------------------------------------------------------- */
        /**
          * (36) set attribute CartonShape
          * @param value the value to set the attribute to
          */
        public void setCartonShape(JDFShape value)
        {
            setAttribute(AttributeName.CARTONSHAPE, value, null);
        }

        /**
          * (20) get JDFShape attribute CartonShape
          * @return JDFShape the value of the attribute, null if a the
          *         attribute value is not a valid to create a JDFShape
          */
        public JDFShape getCartonShape()
        {
            String strAttrName = getAttribute(AttributeName.CARTONSHAPE, null, JDFCoreConstants.EMPTYSTRING);
            JDFShape nPlaceHolder = JDFShape.createShape(strAttrName);
            return nPlaceHolder;
        }

        
        /* ---------------------------------------------------------------------
        Methods for Attribute CartonMaxWeight
        --------------------------------------------------------------------- */
        /**
          * (36) set attribute CartonMaxWeight
          * @param value the value to set the attribute to
          */
        public void setCartonMaxWeight(double value)
        {
            setAttribute(AttributeName.CARTONMAXWEIGHT, value, null);
        }

        /**
          * (17) get double attribute CartonMaxWeight
          * @return double the value of the attribute
          */
        public double getCartonMaxWeight()
        {
            return getRealAttribute(AttributeName.CARTONMAXWEIGHT, null, 0.0);
        }

        
        /* ---------------------------------------------------------------------
        Methods for Attribute CartonStrength
        --------------------------------------------------------------------- */
        /**
          * (36) set attribute CartonStrength
          * @param value the value to set the attribute to
          */
        public void setCartonStrength(double value)
        {
            setAttribute(AttributeName.CARTONSTRENGTH, value, null);
        }

        /**
          * (17) get double attribute CartonStrength
          * @return double the value of the attribute
          */
        public double getCartonStrength()
        {
            return getRealAttribute(AttributeName.CARTONSTRENGTH, null, 0.0);
        }

        
        /* ---------------------------------------------------------------------
        Methods for Attribute PalletQuantity
        --------------------------------------------------------------------- */
        /**
          * (36) set attribute PalletQuantity
          * @param value the value to set the attribute to
          */
        public void setPalletQuantity(int value)
        {
            setAttribute(AttributeName.PALLETQUANTITY, value, null);
        }

        /**
          * (15) get int attribute PalletQuantity
          * @return int the value of the attribute
          */
        public int getPalletQuantity()
        {
            return getIntAttribute(AttributeName.PALLETQUANTITY, null, 0);
        }

        
        /* ---------------------------------------------------------------------
        Methods for Attribute PalletSize
        --------------------------------------------------------------------- */
        /**
          * (36) set attribute PalletSize
          * @param value the value to set the attribute to
          */
        public void setPalletSize(JDFXYPair value)
        {
            setAttribute(AttributeName.PALLETSIZE, value, null);
        }

        /**
          * (20) get JDFXYPair attribute PalletSize
          * @return JDFXYPair the value of the attribute, null if a the
          *         attribute value is not a valid to create a JDFXYPair
          */
        public JDFXYPair getPalletSize()
        {
            String strAttrName = getAttribute(AttributeName.PALLETSIZE, null, JDFCoreConstants.EMPTYSTRING);
            JDFXYPair nPlaceHolder = JDFXYPair.createXYPair(strAttrName);
            return nPlaceHolder;
        }

        
        /* ---------------------------------------------------------------------
        Methods for Attribute PalletMaxHeight
        --------------------------------------------------------------------- */
        /**
          * (36) set attribute PalletMaxHeight
          * @param value the value to set the attribute to
          */
        public void setPalletMaxHeight(double value)
        {
            setAttribute(AttributeName.PALLETMAXHEIGHT, value, null);
        }

        /**
          * (17) get double attribute PalletMaxHeight
          * @return double the value of the attribute
          */
        public double getPalletMaxHeight()
        {
            return getRealAttribute(AttributeName.PALLETMAXHEIGHT, null, 0.0);
        }

        
        /* ---------------------------------------------------------------------
        Methods for Attribute PalletMaxWeight
        --------------------------------------------------------------------- */
        /**
          * (36) set attribute PalletMaxWeight
          * @param value the value to set the attribute to
          */
        public void setPalletMaxWeight(double value)
        {
            setAttribute(AttributeName.PALLETMAXWEIGHT, value, null);
        }

        /**
          * (17) get double attribute PalletMaxWeight
          * @return double the value of the attribute
          */
        public double getPalletMaxWeight()
        {
            return getRealAttribute(AttributeName.PALLETMAXWEIGHT, null, 0.0);
        }

        
        /* ---------------------------------------------------------------------
        Methods for Attribute PalletType
        --------------------------------------------------------------------- */
        /**
          * (5) set attribute PalletType
          * @param enumVar the enumVar to set the attribute to
          */
        public void setPalletType(EnumPalletType enumVar)
        {
            setAttribute(AttributeName.PALLETTYPE, enumVar==null ? null : enumVar.getName(), null);
        }

        /**
          * (9) get attribute PalletType
          * @return the value of the attribute
          */
        public EnumPalletType getPalletType()
        {
            return EnumPalletType.getEnum(getAttribute(AttributeName.PALLETTYPE, null, null));
        }

        
        /* ---------------------------------------------------------------------
        Methods for Attribute WrappedQuantity
        --------------------------------------------------------------------- */
        /**
          * (36) set attribute WrappedQuantity
          * @param value the value to set the attribute to
          */
        public void setWrappedQuantity(int value)
        {
            setAttribute(AttributeName.WRAPPEDQUANTITY, value, null);
        }

        /**
          * (15) get int attribute WrappedQuantity
          * @return int the value of the attribute
          */
        public int getWrappedQuantity()
        {
            return getIntAttribute(AttributeName.WRAPPEDQUANTITY, null, 0);
        }

}// end namespace JDF
