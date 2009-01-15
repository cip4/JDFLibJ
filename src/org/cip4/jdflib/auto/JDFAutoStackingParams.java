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
import org.cip4.jdflib.core.ElemInfoTable;
import org.cip4.jdflib.core.ElementInfo;
import org.cip4.jdflib.core.ElementName;
import org.cip4.jdflib.core.JDFConstants;
import org.cip4.jdflib.core.JDFException;
import org.cip4.jdflib.datatypes.JDFIntegerList;
import org.cip4.jdflib.resource.JDFResource;
import org.cip4.jdflib.resource.process.JDFDisjointing;

public abstract class JDFAutoStackingParams extends JDFResource
{

    private static final long serialVersionUID = 1L;

    private static AtrInfoTable[] atrInfoTable = new AtrInfoTable[15];
    static
    {
        atrInfoTable[0] = new AtrInfoTable(AttributeName.BUNDLEDEPTH, 0x33331111, AttributeInfo.EnumAttributeType.integer, null, "0");
        atrInfoTable[1] = new AtrInfoTable(AttributeName.COMPENSATE, 0x33333331, AttributeInfo.EnumAttributeType.boolean_, null, "true");
        atrInfoTable[2] = new AtrInfoTable(AttributeName.LAYERAMOUNT, 0x33333331, AttributeInfo.EnumAttributeType.IntegerList, null, null);
        atrInfoTable[3] = new AtrInfoTable(AttributeName.LAYERLIFT, 0x33331111, AttributeInfo.EnumAttributeType.boolean_, null, null);
        atrInfoTable[4] = new AtrInfoTable(AttributeName.LAYERCOMPRESSION, 0x33331111, AttributeInfo.EnumAttributeType.boolean_, null, null);
        atrInfoTable[5] = new AtrInfoTable(AttributeName.MAXAMOUNT, 0x33333331, AttributeInfo.EnumAttributeType.integer, null, null);
        atrInfoTable[6] = new AtrInfoTable(AttributeName.MAXHEIGHT, 0x33331111, AttributeInfo.EnumAttributeType.integer, null, null);
        atrInfoTable[7] = new AtrInfoTable(AttributeName.MINAMOUNT, 0x33333331, AttributeInfo.EnumAttributeType.integer, null, null);
        atrInfoTable[8] = new AtrInfoTable(AttributeName.MAXWEIGHT, 0x33333331, AttributeInfo.EnumAttributeType.double_, null, null);
        atrInfoTable[9] = new AtrInfoTable(AttributeName.OFFSET, 0x44444431, AttributeInfo.EnumAttributeType.boolean_, null, null);
        atrInfoTable[10] = new AtrInfoTable(AttributeName.PRESTACKAMOUNT, 0x33331111, AttributeInfo.EnumAttributeType.integer, null, null);
        atrInfoTable[11] = new AtrInfoTable(AttributeName.PRESTACKMETHOD, 0x33333331, AttributeInfo.EnumAttributeType.enumeration, EnumPreStackMethod.getEnum(0), null);
        atrInfoTable[12] = new AtrInfoTable(AttributeName.STACKCOMPRESSION, 0x33331111, AttributeInfo.EnumAttributeType.boolean_, null, null);
        atrInfoTable[13] = new AtrInfoTable(AttributeName.UNDERLAYS, 0x33333111, AttributeInfo.EnumAttributeType.IntegerList, null, null);
        atrInfoTable[14] = new AtrInfoTable(AttributeName.STANDARDAMOUNT, 0x33333331, AttributeInfo.EnumAttributeType.integer, null, null);
    }
    
    @Override
	protected AttributeInfo getTheAttributeInfo()
    {
        return super.getTheAttributeInfo().updateReplace(atrInfoTable);
    }


    private static ElemInfoTable[] elemInfoTable = new ElemInfoTable[1];
    static
    {
        elemInfoTable[0] = new ElemInfoTable(ElementName.DISJOINTING, 0x66666611);
    }
    
    @Override
	protected ElementInfo getTheElementInfo()
    {
        return super.getTheElementInfo().updateReplace(elemInfoTable);
    }



    /**
     * Constructor for JDFAutoStackingParams
     * @param myOwnerDocument
     * @param qualifiedName
     */
    protected JDFAutoStackingParams(
        CoreDocumentImpl myOwnerDocument,
        String qualifiedName)
    {
        super(myOwnerDocument, qualifiedName);
    }

    /**
     * Constructor for JDFAutoStackingParams
     * @param myOwnerDocument
     * @param myNamespaceURI
     * @param qualifiedName
     */
    protected JDFAutoStackingParams(
        CoreDocumentImpl myOwnerDocument,
        String myNamespaceURI,
        String qualifiedName)
    {
        super(myOwnerDocument, myNamespaceURI, qualifiedName);
    }

    /**
     * Constructor for JDFAutoStackingParams
     * @param myOwnerDocument
     * @param myNamespaceURI
     * @param qualifiedName
     * @param myLocalName
     */
    protected JDFAutoStackingParams(
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
        return " JDFAutoStackingParams[  --> " + super.toString() + " ]";
    }


    @Override
	public boolean  init()
    {
        boolean bRet = super.init();
        setResourceClass(JDFResource.EnumResourceClass.Parameter);
        return bRet;
    }


    @Override
	public EnumResourceClass getValidClass()
    {
        return JDFResource.EnumResourceClass.Parameter;
    }


        /**
        * Enumeration strings for PreStackMethod
        */

        public static class EnumPreStackMethod extends ValuedEnum
        {
            private static final long serialVersionUID = 1L;
            private static int m_startValue = 0;

            private EnumPreStackMethod(String name)
            {
                super(name, m_startValue++);
            }

            public static EnumPreStackMethod getEnum(String enumName)
            {
                return (EnumPreStackMethod) getEnum(EnumPreStackMethod.class, enumName);
            }

            public static EnumPreStackMethod getEnum(int enumValue)
            {
                return (EnumPreStackMethod) getEnum(EnumPreStackMethod.class, enumValue);
            }

            public static Map getEnumMap()
            {
                return getEnumMap(EnumPreStackMethod.class);
            }

            public static List getEnumList()
            {
                return getEnumList(EnumPreStackMethod.class);
            }

            public static Iterator iterator()
            {
                return iterator(EnumPreStackMethod.class);
            }

            public static final EnumPreStackMethod All = new EnumPreStackMethod("All");
            public static final EnumPreStackMethod First = new EnumPreStackMethod("First");
            public static final EnumPreStackMethod None = new EnumPreStackMethod("None");
        }      



/* ************************************************************************
 * Attribute getter / setter
 * ************************************************************************
 */
        
        /* ---------------------------------------------------------------------
        Methods for Attribute BundleDepth
        --------------------------------------------------------------------- */
        /**
          * (36) set attribute BundleDepth
          * @param value: the value to set the attribute to
          */
        public void setBundleDepth(int value)
        {
            setAttribute(AttributeName.BUNDLEDEPTH, value, null);
        }

        /**
          * (15) get int attribute BundleDepth
          * @return int the value of the attribute
          */
        public int getBundleDepth()
        {
            return getIntAttribute(AttributeName.BUNDLEDEPTH, null, 0);
        }

        
        /* ---------------------------------------------------------------------
        Methods for Attribute Compensate
        --------------------------------------------------------------------- */
        /**
          * (36) set attribute Compensate
          * @param value: the value to set the attribute to
          */
        public void setCompensate(boolean value)
        {
            setAttribute(AttributeName.COMPENSATE, value, null);
        }

        /**
          * (18) get boolean attribute Compensate
          * @return boolean the value of the attribute
          */
        public boolean getCompensate()
        {
            return getBoolAttribute(AttributeName.COMPENSATE, null, true);
        }

        
        /* ---------------------------------------------------------------------
        Methods for Attribute LayerAmount
        --------------------------------------------------------------------- */
        /**
          * (36) set attribute LayerAmount
          * @param value: the value to set the attribute to
          */
        public void setLayerAmount(JDFIntegerList value)
        {
            setAttribute(AttributeName.LAYERAMOUNT, value, null);
        }

        /**
          * (20) get JDFIntegerList attribute LayerAmount
          * @return JDFIntegerList the value of the attribute, null if a the
          *         attribute value is not a valid to create a JDFIntegerList
          */
        public JDFIntegerList getLayerAmount()
        {
            String strAttrName = "";
            JDFIntegerList nPlaceHolder = null;
            strAttrName = getAttribute(AttributeName.LAYERAMOUNT, null, JDFConstants.EMPTYSTRING);
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
        Methods for Attribute LayerLift
        --------------------------------------------------------------------- */
        /**
          * (36) set attribute LayerLift
          * @param value: the value to set the attribute to
          */
        public void setLayerLift(boolean value)
        {
            setAttribute(AttributeName.LAYERLIFT, value, null);
        }

        /**
          * (18) get boolean attribute LayerLift
          * @return boolean the value of the attribute
          */
        public boolean getLayerLift()
        {
            return getBoolAttribute(AttributeName.LAYERLIFT, null, false);
        }

        
        /* ---------------------------------------------------------------------
        Methods for Attribute LayerCompression
        --------------------------------------------------------------------- */
        /**
          * (36) set attribute LayerCompression
          * @param value: the value to set the attribute to
          */
        public void setLayerCompression(boolean value)
        {
            setAttribute(AttributeName.LAYERCOMPRESSION, value, null);
        }

        /**
          * (18) get boolean attribute LayerCompression
          * @return boolean the value of the attribute
          */
        public boolean getLayerCompression()
        {
            return getBoolAttribute(AttributeName.LAYERCOMPRESSION, null, false);
        }

        
        /* ---------------------------------------------------------------------
        Methods for Attribute MaxAmount
        --------------------------------------------------------------------- */
        /**
          * (36) set attribute MaxAmount
          * @param value: the value to set the attribute to
          */
        public void setMaxAmount(int value)
        {
            setAttribute(AttributeName.MAXAMOUNT, value, null);
        }

        /**
          * (15) get int attribute MaxAmount
          * @return int the value of the attribute
          */
        public int getMaxAmount()
        {
            return getIntAttribute(AttributeName.MAXAMOUNT, null, 0);
        }

        
        /* ---------------------------------------------------------------------
        Methods for Attribute MaxHeight
        --------------------------------------------------------------------- */
        /**
          * (36) set attribute MaxHeight
          * @param value: the value to set the attribute to
          */
        public void setMaxHeight(int value)
        {
            setAttribute(AttributeName.MAXHEIGHT, value, null);
        }

        /**
          * (15) get int attribute MaxHeight
          * @return int the value of the attribute
          */
        public int getMaxHeight()
        {
            return getIntAttribute(AttributeName.MAXHEIGHT, null, 0);
        }

        
        /* ---------------------------------------------------------------------
        Methods for Attribute MinAmount
        --------------------------------------------------------------------- */
        /**
          * (36) set attribute MinAmount
          * @param value: the value to set the attribute to
          */
        public void setMinAmount(int value)
        {
            setAttribute(AttributeName.MINAMOUNT, value, null);
        }

        /**
          * (15) get int attribute MinAmount
          * @return int the value of the attribute
          */
        public int getMinAmount()
        {
            return getIntAttribute(AttributeName.MINAMOUNT, null, 0);
        }

        
        /* ---------------------------------------------------------------------
        Methods for Attribute MaxWeight
        --------------------------------------------------------------------- */
        /**
          * (36) set attribute MaxWeight
          * @param value: the value to set the attribute to
          */
        public void setMaxWeight(double value)
        {
            setAttribute(AttributeName.MAXWEIGHT, value, null);
        }

        /**
          * (17) get double attribute MaxWeight
          * @return double the value of the attribute
          */
        public double getMaxWeight()
        {
            return getRealAttribute(AttributeName.MAXWEIGHT, null, 0.0);
        }

        
        /* ---------------------------------------------------------------------
        Methods for Attribute Offset
        --------------------------------------------------------------------- */
        /**
          * (36) set attribute Offset
          * @param value: the value to set the attribute to
          */
        public void setOffset(boolean value)
        {
            setAttribute(AttributeName.OFFSET, value, null);
        }

        /**
          * (18) get boolean attribute Offset
          * @return boolean the value of the attribute
          */
        public boolean getOffset()
        {
            return getBoolAttribute(AttributeName.OFFSET, null, false);
        }

        
        /* ---------------------------------------------------------------------
        Methods for Attribute PreStackAmount
        --------------------------------------------------------------------- */
        /**
          * (36) set attribute PreStackAmount
          * @param value: the value to set the attribute to
          */
        public void setPreStackAmount(int value)
        {
            setAttribute(AttributeName.PRESTACKAMOUNT, value, null);
        }

        /**
          * (15) get int attribute PreStackAmount
          * @return int the value of the attribute
          */
        public int getPreStackAmount()
        {
            return getIntAttribute(AttributeName.PRESTACKAMOUNT, null, 0);
        }

        
        /* ---------------------------------------------------------------------
        Methods for Attribute PreStackMethod
        --------------------------------------------------------------------- */
        /**
          * (5) set attribute PreStackMethod
          * @param enumVar: the enumVar to set the attribute to
          */
        public void setPreStackMethod(EnumPreStackMethod enumVar)
        {
            setAttribute(AttributeName.PRESTACKMETHOD, enumVar==null ? null : enumVar.getName(), null);
        }

        /**
          * (9) get attribute PreStackMethod
          * @return the value of the attribute
          */
        public EnumPreStackMethod getPreStackMethod()
        {
            return EnumPreStackMethod.getEnum(getAttribute(AttributeName.PRESTACKMETHOD, null, null));
        }

        
        /* ---------------------------------------------------------------------
        Methods for Attribute StackCompression
        --------------------------------------------------------------------- */
        /**
          * (36) set attribute StackCompression
          * @param value: the value to set the attribute to
          */
        public void setStackCompression(boolean value)
        {
            setAttribute(AttributeName.STACKCOMPRESSION, value, null);
        }

        /**
          * (18) get boolean attribute StackCompression
          * @return boolean the value of the attribute
          */
        public boolean getStackCompression()
        {
            return getBoolAttribute(AttributeName.STACKCOMPRESSION, null, false);
        }

        
        /* ---------------------------------------------------------------------
        Methods for Attribute UnderLays
        --------------------------------------------------------------------- */
        /**
          * (36) set attribute UnderLays
          * @param value: the value to set the attribute to
          */
        public void setUnderLays(JDFIntegerList value)
        {
            setAttribute(AttributeName.UNDERLAYS, value, null);
        }

        /**
          * (20) get JDFIntegerList attribute UnderLays
          * @return JDFIntegerList the value of the attribute, null if a the
          *         attribute value is not a valid to create a JDFIntegerList
          */
        public JDFIntegerList getUnderLays()
        {
            String strAttrName = "";
            JDFIntegerList nPlaceHolder = null;
            strAttrName = getAttribute(AttributeName.UNDERLAYS, null, JDFConstants.EMPTYSTRING);
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
        Methods for Attribute StandardAmount
        --------------------------------------------------------------------- */
        /**
          * (36) set attribute StandardAmount
          * @param value: the value to set the attribute to
          */
        public void setStandardAmount(int value)
        {
            setAttribute(AttributeName.STANDARDAMOUNT, value, null);
        }

        /**
          * (15) get int attribute StandardAmount
          * @return int the value of the attribute
          */
        public int getStandardAmount()
        {
            return getIntAttribute(AttributeName.STANDARDAMOUNT, null, 0);
        }

/* ***********************************************************************
 * Element getter / setter
 * ***********************************************************************
 */

    /**
     * (24) const get element Disjointing
     * @return JDFDisjointing the element
     */
    public JDFDisjointing getDisjointing()
    {
        return (JDFDisjointing) getElement(ElementName.DISJOINTING, null, 0);
    }

    /** (25) getCreateDisjointing
     * 
     * @return JDFDisjointing the element
     */
    public JDFDisjointing getCreateDisjointing()
    {
        return (JDFDisjointing) getCreateElement_KElement(ElementName.DISJOINTING, null, 0);
    }

    /**
     * (29) append element Disjointing
     */
    public JDFDisjointing appendDisjointing() throws JDFException
    {
        return (JDFDisjointing) appendElementN(ElementName.DISJOINTING, 1, null);
    }

}// end namespace JDF
