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
import org.cip4.jdflib.resource.JDFResource;
import org.cip4.jdflib.resource.process.postpress.JDFHoleMakingParams;
    /*
    *****************************************************************************
    class JDFAutoWireCombBindingParams : public JDFResource

    *****************************************************************************
    */

public abstract class JDFAutoWireCombBindingParams extends JDFResource
{

    private static final long serialVersionUID = 1L;

    private static AtrInfoTable[] atrInfoTable = new AtrInfoTable[8];
    static
    {
        atrInfoTable[0] = new AtrInfoTable(AttributeName.FLIPBACKCOVER, 0x33333331, AttributeInfo.EnumAttributeType.boolean_, null, "false");
        atrInfoTable[1] = new AtrInfoTable(AttributeName.SHAPE, 0x33333333, AttributeInfo.EnumAttributeType.enumeration, EnumShape.getEnum(0), "Single");
        atrInfoTable[2] = new AtrInfoTable(AttributeName.BRAND, 0x33333333, AttributeInfo.EnumAttributeType.string, null, null);
        atrInfoTable[3] = new AtrInfoTable(AttributeName.COLOR, 0x33333333, AttributeInfo.EnumAttributeType.string, null, null);
        atrInfoTable[4] = new AtrInfoTable(AttributeName.DIAMETER, 0x33333333, AttributeInfo.EnumAttributeType.double_, null, null);
        atrInfoTable[5] = new AtrInfoTable(AttributeName.DISTANCE, 0x44444433, AttributeInfo.EnumAttributeType.double_, null, null);
        atrInfoTable[6] = new AtrInfoTable(AttributeName.MATERIAL, 0x33333333, AttributeInfo.EnumAttributeType.enumeration, EnumMaterial.getEnum(0), null);
        atrInfoTable[7] = new AtrInfoTable(AttributeName.THICKNESS, 0x33333333, AttributeInfo.EnumAttributeType.double_, null, null);
    }
    
    protected AttributeInfo getTheAttributeInfo()
    {
        return super.getTheAttributeInfo().updateReplace(atrInfoTable);
    }


    private static ElemInfoTable[] elemInfoTable = new ElemInfoTable[1];
    static
    {
        elemInfoTable[0] = new ElemInfoTable(ElementName.HOLEMAKINGPARAMS, 0x66666611);
    }
    
    protected ElementInfo getTheElementInfo()
    {
        return super.getTheElementInfo().updateReplace(elemInfoTable);
    }



    /**
     * Constructor for JDFAutoWireCombBindingParams
     * @param myOwnerDocument
     * @param qualifiedName
     */
    protected JDFAutoWireCombBindingParams(
        CoreDocumentImpl myOwnerDocument,
        String qualifiedName)
    {
        super(myOwnerDocument, qualifiedName);
    }

    /**
     * Constructor for JDFAutoWireCombBindingParams
     * @param myOwnerDocument
     * @param myNamespaceURI
     * @param qualifiedName
     */
    protected JDFAutoWireCombBindingParams(
        CoreDocumentImpl myOwnerDocument,
        String myNamespaceURI,
        String qualifiedName)
    {
        super(myOwnerDocument, myNamespaceURI, qualifiedName);
    }

    /**
     * Constructor for JDFAutoWireCombBindingParams
     * @param myOwnerDocument
     * @param myNamespaceURI
     * @param qualifiedName
     * @param myLocalName
     */
    protected JDFAutoWireCombBindingParams(
        CoreDocumentImpl myOwnerDocument,
        String myNamespaceURI,
        String qualifiedName,
        String myLocalName)
    {
        super(myOwnerDocument, myNamespaceURI, qualifiedName, myLocalName);
    }


    public String toString()
    {
        return " JDFAutoWireCombBindingParams[  --> " + super.toString() + " ]";
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
        * Enumeration strings for Shape
        */

        public static class EnumShape extends ValuedEnum
        {
            private static final long serialVersionUID = 1L;
            private static int m_startValue = 0;

            private EnumShape(String name)
            {
                super(name, m_startValue++);
            }

            public static EnumShape getEnum(String enumName)
            {
                return (EnumShape) getEnum(EnumShape.class, enumName);
            }

            public static EnumShape getEnum(int enumValue)
            {
                return (EnumShape) getEnum(EnumShape.class, enumValue);
            }

            public static Map getEnumMap()
            {
                return getEnumMap(EnumShape.class);
            }

            public static List getEnumList()
            {
                return getEnumList(EnumShape.class);
            }

            public static Iterator iterator()
            {
                return iterator(EnumShape.class);
            }

            public static final EnumShape Single = new EnumShape("Single");
            public static final EnumShape Twin = new EnumShape("Twin");
        }      



        /**
        * Enumeration strings for Material
        */

        public static class EnumMaterial extends ValuedEnum
        {
            private static final long serialVersionUID = 1L;
            private static int m_startValue = 0;

            private EnumMaterial(String name)
            {
                super(name, m_startValue++);
            }

            public static EnumMaterial getEnum(String enumName)
            {
                return (EnumMaterial) getEnum(EnumMaterial.class, enumName);
            }

            public static EnumMaterial getEnum(int enumValue)
            {
                return (EnumMaterial) getEnum(EnumMaterial.class, enumValue);
            }

            public static Map getEnumMap()
            {
                return getEnumMap(EnumMaterial.class);
            }

            public static List getEnumList()
            {
                return getEnumList(EnumMaterial.class);
            }

            public static Iterator iterator()
            {
                return iterator(EnumMaterial.class);
            }

            public static final EnumMaterial LaqueredSteel = new EnumMaterial("LaqueredSteel");
            public static final EnumMaterial TinnedSteel = new EnumMaterial("TinnedSteel");
            public static final EnumMaterial ZincsSteel = new EnumMaterial("ZincsSteel");
        }      



/* ************************************************************************
 * Attribute getter / setter
 * ************************************************************************
 */
        
        /* ---------------------------------------------------------------------
        Methods for Attribute FlipBackCover
        --------------------------------------------------------------------- */
        /**
          * (36) set attribute FlipBackCover
          * @param value: the value to set the attribute to
          */
        public void setFlipBackCover(boolean value)
        {
            setAttribute(AttributeName.FLIPBACKCOVER, value, null);
        }

        /**
          * (18) get boolean attribute FlipBackCover
          * @return boolean the value of the attribute
          */
        public boolean getFlipBackCover()
        {
            return getBoolAttribute(AttributeName.FLIPBACKCOVER, null, false);
        }

        
        /* ---------------------------------------------------------------------
        Methods for Attribute Shape
        --------------------------------------------------------------------- */
        /**
          * (5) set attribute Shape
          * @param enumVar: the enumVar to set the attribute to
          */
        public void setShape(EnumShape enumVar)
        {
            setAttribute(AttributeName.SHAPE, enumVar==null ? null : enumVar.getName(), null);
        }

        /**
          * (9) get attribute Shape
          * @return the value of the attribute
          */
        public EnumShape getShape()
        {
            return EnumShape.getEnum(getAttribute(AttributeName.SHAPE, null, "Single"));
        }

        
        /* ---------------------------------------------------------------------
        Methods for Attribute Brand
        --------------------------------------------------------------------- */
        /**
          * (36) set attribute Brand
          * @param value: the value to set the attribute to
          */
        public void setBrand(String value)
        {
            setAttribute(AttributeName.BRAND, value, null);
        }

        /**
          * (23) get String attribute Brand
          * @return the value of the attribute
          */
        public String getBrand()
        {
            return getAttribute(AttributeName.BRAND, null, JDFConstants.EMPTYSTRING);
        }

        
        /* ---------------------------------------------------------------------
        Methods for Attribute Color
        --------------------------------------------------------------------- */
        /**
          * (13) set attribute Color
          * @param value: the value to set the attribute to
          */
        public void setColor(EnumNamedColor value)
        {
            setAttribute(AttributeName.COLOR, value==null ? null : value.getName(), null);
        }

        /**
          * (19) get EnumNamedColor attribute Color
          * @return EnumNamedColor the value of the attribute
          */
        public EnumNamedColor getColor()
        {
            String strAttrName = "";
            EnumNamedColor nPlaceHolder = null;
            strAttrName = getAttribute(AttributeName.COLOR, null, JDFConstants.EMPTYSTRING);
            nPlaceHolder = EnumNamedColor.getEnum(strAttrName);
            return nPlaceHolder;
        }

        
        /* ---------------------------------------------------------------------
        Methods for Attribute Diameter
        --------------------------------------------------------------------- */
        /**
          * (36) set attribute Diameter
          * @param value: the value to set the attribute to
          */
        public void setDiameter(double value)
        {
            setAttribute(AttributeName.DIAMETER, value, null);
        }

        /**
          * (17) get double attribute Diameter
          * @return double the value of the attribute
          */
        public double getDiameter()
        {
            return getRealAttribute(AttributeName.DIAMETER, null, 0.0);
        }

        
        /* ---------------------------------------------------------------------
        Methods for Attribute Distance
        --------------------------------------------------------------------- */
        /**
          * (36) set attribute Distance
          * @param value: the value to set the attribute to
          */
        public void setDistance(double value)
        {
            setAttribute(AttributeName.DISTANCE, value, null);
        }

        /**
          * (17) get double attribute Distance
          * @return double the value of the attribute
          */
        public double getDistance()
        {
            return getRealAttribute(AttributeName.DISTANCE, null, 0.0);
        }

        
        /* ---------------------------------------------------------------------
        Methods for Attribute Material
        --------------------------------------------------------------------- */
        /**
          * (5) set attribute Material
          * @param enumVar: the enumVar to set the attribute to
          */
        public void setMaterial(EnumMaterial enumVar)
        {
            setAttribute(AttributeName.MATERIAL, enumVar==null ? null : enumVar.getName(), null);
        }

        /**
          * (9) get attribute Material
          * @return the value of the attribute
          */
        public EnumMaterial getMaterial()
        {
            return EnumMaterial.getEnum(getAttribute(AttributeName.MATERIAL, null, null));
        }

        
        /* ---------------------------------------------------------------------
        Methods for Attribute Thickness
        --------------------------------------------------------------------- */
        /**
          * (36) set attribute Thickness
          * @param value: the value to set the attribute to
          */
        public void setThickness(double value)
        {
            setAttribute(AttributeName.THICKNESS, value, null);
        }

        /**
          * (17) get double attribute Thickness
          * @return double the value of the attribute
          */
        public double getThickness()
        {
            return getRealAttribute(AttributeName.THICKNESS, null, 0.0);
        }

/* ***********************************************************************
 * Element getter / setter
 * ***********************************************************************
 */

    /**
     * (24) const get element HoleMakingParams
     * @return JDFHoleMakingParams the element
     */
    public JDFHoleMakingParams getHoleMakingParams()
    {
        return (JDFHoleMakingParams) getElement(ElementName.HOLEMAKINGPARAMS, null, 0);
    }

    /** (25) getCreateHoleMakingParams
     * 
     * @return JDFHoleMakingParams the element
     */
    public JDFHoleMakingParams getCreateHoleMakingParams()
    {
        return (JDFHoleMakingParams) getCreateElement_KElement(ElementName.HOLEMAKINGPARAMS, null, 0);
    }

    /**
     * (29) append element HoleMakingParams
     */
    public JDFHoleMakingParams appendHoleMakingParams() throws JDFException
    {
        return (JDFHoleMakingParams) appendElementN(ElementName.HOLEMAKINGPARAMS, 1, null);
    }

    /**
      * (31) create inter-resource link to refTarget
      * @param refTarget the element that is referenced
      */
    public void refHoleMakingParams(JDFHoleMakingParams refTarget)
    {
        refElement(refTarget);
    }

}// end namespace JDF
