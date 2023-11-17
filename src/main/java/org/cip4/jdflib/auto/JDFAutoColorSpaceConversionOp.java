/*
 * The CIP4 Software License, Version 1.0
 *
 *
 * Copyright (c) 2001-2023 The International Cooperation for the Integration of
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
    class JDFAutoColorSpaceConversionOp : public JDFElement

    *****************************************************************************
    */

public abstract class JDFAutoColorSpaceConversionOp extends JDFElement
{

    private static final long serialVersionUID = 1L;

    private static AtrInfoTable[] atrInfoTable = new AtrInfoTable[12];
    static
    {
        atrInfoTable[0] = new AtrInfoTable(AttributeName.BLACKPOINTCOMPENSATION, 0x33333333, AttributeInfo.EnumAttributeType.boolean_, null, null);
        atrInfoTable[1] = new AtrInfoTable(AttributeName.BLACKPOINTCOMPENSATIONDETAILS, 0x33333333, AttributeInfo.EnumAttributeType.NMTOKEN, null, null);
        atrInfoTable[2] = new AtrInfoTable(AttributeName.PRESERVEBLACK, 0x33333331, AttributeInfo.EnumAttributeType.boolean_, null, "false");
        atrInfoTable[3] = new AtrInfoTable(AttributeName.RENDERINGINTENT, 0x33333333, AttributeInfo.EnumAttributeType.enumeration, EnumRenderingIntent.getEnum(0), "ColorSpaceDependent");
        atrInfoTable[4] = new AtrInfoTable(AttributeName.RGBGRAY2BLACK, 0x33333333, AttributeInfo.EnumAttributeType.boolean_, null, "false");
        atrInfoTable[5] = new AtrInfoTable(AttributeName.RGBGRAY2BLACKTHRESHOLD, 0x33333311, AttributeInfo.EnumAttributeType.double_, null, "1");
        atrInfoTable[6] = new AtrInfoTable(AttributeName.SOURCEOBJECTS, 0x33333333, AttributeInfo.EnumAttributeType.enumerations, EnumSourceObjects.getEnum(0), "All");
        atrInfoTable[7] = new AtrInfoTable(AttributeName.IGNOREEMBEDDEDICC, 0x44443333, AttributeInfo.EnumAttributeType.boolean_, null, null);
        atrInfoTable[8] = new AtrInfoTable(AttributeName.OBJECTTAGS, 0x33331111, AttributeInfo.EnumAttributeType.NMTOKENS, null, null);
        atrInfoTable[9] = new AtrInfoTable(AttributeName.OPERATION, 0x33333333, AttributeInfo.EnumAttributeType.enumeration, EnumOperation.getEnum(0), null);
        atrInfoTable[10] = new AtrInfoTable(AttributeName.SOURCECS, 0x33333333, AttributeInfo.EnumAttributeType.enumeration, EnumSourceCS.getEnum(0), null);
        atrInfoTable[11] = new AtrInfoTable(AttributeName.SOURCERENDERINGINTENT, 0x33333311, AttributeInfo.EnumAttributeType.enumeration, EnumSourceRenderingIntent.getEnum(0), null);
    }
    
    protected AttributeInfo getTheAttributeInfo()
    {
        return super.getTheAttributeInfo().updateReplace(atrInfoTable);
    }


    private static ElemInfoTable[] elemInfoTable = new ElemInfoTable[3];
    static
    {
        elemInfoTable[0] = new ElemInfoTable(ElementName.DEVICENSPACE, 0x66666611);
        elemInfoTable[1] = new ElemInfoTable(ElementName.FILESPEC, 0x33333333);
        elemInfoTable[2] = new ElemInfoTable(ElementName.SEPARATIONSPEC, 0x33333311);
    }
    
    protected ElementInfo getTheElementInfo()
    {
        return super.getTheElementInfo().updateReplace(elemInfoTable);
    }



    /**
     * Constructor for JDFAutoColorSpaceConversionOp
     * @param myOwnerDocument
     * @param qualifiedName
     */
    protected JDFAutoColorSpaceConversionOp(
        CoreDocumentImpl myOwnerDocument,
        String qualifiedName)
    {
        super(myOwnerDocument, qualifiedName);
    }

    /**
     * Constructor for JDFAutoColorSpaceConversionOp
     * @param myOwnerDocument
     * @param myNamespaceURI
     * @param qualifiedName
     */
    protected JDFAutoColorSpaceConversionOp(
        CoreDocumentImpl myOwnerDocument,
        String myNamespaceURI,
        String qualifiedName)
    {
        super(myOwnerDocument, myNamespaceURI, qualifiedName);
    }

    /**
     * Constructor for JDFAutoColorSpaceConversionOp
     * @param myOwnerDocument
     * @param myNamespaceURI
     * @param qualifiedName
     * @param myLocalName
     */
    protected JDFAutoColorSpaceConversionOp(
        CoreDocumentImpl myOwnerDocument,
        String myNamespaceURI,
        String qualifiedName,
        String myLocalName)
    {
        super(myOwnerDocument, myNamespaceURI, qualifiedName, myLocalName);
    }


        /**
        * Enumeration strings for RenderingIntent
        */

        @SuppressWarnings("rawtypes")
        public static class EnumRenderingIntent extends ValuedEnum
        {
            private static final long serialVersionUID = 1L;
            private static int m_startValue = 0;

            protected EnumRenderingIntent(String name)
            {
                super(name, m_startValue++);
            }

    /**
     * @param enumName the string to convert
     * @return the enum
     */
            public static EnumRenderingIntent getEnum(String enumName)
            {
                return (EnumRenderingIntent) getEnum(EnumRenderingIntent.class, enumName);
            }

    /**
     * @param enumValue the integer to convert
     * @return the enum
     */
            public static EnumRenderingIntent getEnum(int enumValue)
            {
                return (EnumRenderingIntent) getEnum(EnumRenderingIntent.class, enumValue);
            }

    /**
     * @return the map of enums
     */
            public static Map getEnumMap()
            {
                return getEnumMap(EnumRenderingIntent.class);
            }

    /**
     * @return the list of enums
     */
            public static List getEnumList()
            {
                return getEnumList(EnumRenderingIntent.class);
            }

    /**
     * @return the iterator
     */
            public static Iterator iterator()
            {
                return iterator(EnumRenderingIntent.class);
            }

            /**  */
            public static final EnumRenderingIntent ColorSpaceDependent = new EnumRenderingIntent("ColorSpaceDependent");
            /**  */
            public static final EnumRenderingIntent Perceptual = new EnumRenderingIntent("Perceptual");
            /**  */
            public static final EnumRenderingIntent Saturation = new EnumRenderingIntent("Saturation");
            /**  */
            public static final EnumRenderingIntent RelativeColorimetric = new EnumRenderingIntent("RelativeColorimetric");
            /**  */
            public static final EnumRenderingIntent AbsoluteColorimetric = new EnumRenderingIntent("AbsoluteColorimetric");
        }      



        /**
        * Enumeration strings for SourceObjects
        */

        @SuppressWarnings("rawtypes")
        public static class EnumSourceObjects extends ValuedEnum
        {
            private static final long serialVersionUID = 1L;
            private static int m_startValue = 0;

            protected EnumSourceObjects(String name)
            {
                super(name, m_startValue++);
            }

    /**
     * @param enumName the string to convert
     * @return the enum
     */
            public static EnumSourceObjects getEnum(String enumName)
            {
                return (EnumSourceObjects) getEnum(EnumSourceObjects.class, enumName);
            }

    /**
     * @param enumValue the integer to convert
     * @return the enum
     */
            public static EnumSourceObjects getEnum(int enumValue)
            {
                return (EnumSourceObjects) getEnum(EnumSourceObjects.class, enumValue);
            }

    /**
     * @return the map of enums
     */
            public static Map getEnumMap()
            {
                return getEnumMap(EnumSourceObjects.class);
            }

    /**
     * @return the list of enums
     */
            public static List getEnumList()
            {
                return getEnumList(EnumSourceObjects.class);
            }

    /**
     * @return the iterator
     */
            public static Iterator iterator()
            {
                return iterator(EnumSourceObjects.class);
            }

            /**  */
            public static final EnumSourceObjects All = new EnumSourceObjects("All");
            /**  */
            public static final EnumSourceObjects ImagePhotographic = new EnumSourceObjects("ImagePhotographic");
            /**  */
            public static final EnumSourceObjects ImageScreenShot = new EnumSourceObjects("ImageScreenShot");
            /**  */
            public static final EnumSourceObjects LineArt = new EnumSourceObjects("LineArt");
            /**  */
            public static final EnumSourceObjects SmoothShades = new EnumSourceObjects("SmoothShades");
            /**  */
            public static final EnumSourceObjects Text = new EnumSourceObjects("Text");
        }      



        /**
        * Enumeration strings for Operation
        */

        @SuppressWarnings("rawtypes")
        public static class EnumOperation extends ValuedEnum
        {
            private static final long serialVersionUID = 1L;
            private static int m_startValue = 0;

            protected EnumOperation(String name)
            {
                super(name, m_startValue++);
            }

    /**
     * @param enumName the string to convert
     * @return the enum
     */
            public static EnumOperation getEnum(String enumName)
            {
                return (EnumOperation) getEnum(EnumOperation.class, enumName);
            }

    /**
     * @param enumValue the integer to convert
     * @return the enum
     */
            public static EnumOperation getEnum(int enumValue)
            {
                return (EnumOperation) getEnum(EnumOperation.class, enumValue);
            }

    /**
     * @return the map of enums
     */
            public static Map getEnumMap()
            {
                return getEnumMap(EnumOperation.class);
            }

    /**
     * @return the list of enums
     */
            public static List getEnumList()
            {
                return getEnumList(EnumOperation.class);
            }

    /**
     * @return the iterator
     */
            public static Iterator iterator()
            {
                return iterator(EnumOperation.class);
            }

            /**  */
            public static final EnumOperation Convert = new EnumOperation("Convert");
            /**  */
            public static final EnumOperation Tag = new EnumOperation("Tag");
            /**  */
            public static final EnumOperation Untag = new EnumOperation("Untag");
            /**  */
            public static final EnumOperation Retag = new EnumOperation("Retag");
            /**  */
            public static final EnumOperation ConvertIgnore = new EnumOperation("ConvertIgnore");
        }      



        /**
        * Enumeration strings for SourceCS
        */

        @SuppressWarnings("rawtypes")
        public static class EnumSourceCS extends ValuedEnum
        {
            private static final long serialVersionUID = 1L;
            private static int m_startValue = 0;

            protected EnumSourceCS(String name)
            {
                super(name, m_startValue++);
            }

    /**
     * @param enumName the string to convert
     * @return the enum
     */
            public static EnumSourceCS getEnum(String enumName)
            {
                return (EnumSourceCS) getEnum(EnumSourceCS.class, enumName);
            }

    /**
     * @param enumValue the integer to convert
     * @return the enum
     */
            public static EnumSourceCS getEnum(int enumValue)
            {
                return (EnumSourceCS) getEnum(EnumSourceCS.class, enumValue);
            }

    /**
     * @return the map of enums
     */
            public static Map getEnumMap()
            {
                return getEnumMap(EnumSourceCS.class);
            }

    /**
     * @return the list of enums
     */
            public static List getEnumList()
            {
                return getEnumList(EnumSourceCS.class);
            }

    /**
     * @return the iterator
     */
            public static Iterator iterator()
            {
                return iterator(EnumSourceCS.class);
            }

            /**  */
            public static final EnumSourceCS CalGray = new EnumSourceCS("CalGray");
            /**  */
            public static final EnumSourceCS CalRGB = new EnumSourceCS("CalRGB");
            /**  */
            public static final EnumSourceCS Calibrated = new EnumSourceCS("Calibrated");
            /**  */
            public static final EnumSourceCS CIEBased = new EnumSourceCS("CIEBased");
            /**  */
            public static final EnumSourceCS CMYK = new EnumSourceCS("CMYK");
            /**  */
            public static final EnumSourceCS DeviceN = new EnumSourceCS("DeviceN");
            /**  */
            public static final EnumSourceCS DevIndep = new EnumSourceCS("DevIndep");
            /**  */
            public static final EnumSourceCS RGB = new EnumSourceCS("RGB");
            /**  */
            public static final EnumSourceCS Gray = new EnumSourceCS("Gray");
            /**  */
            public static final EnumSourceCS ICCBased = new EnumSourceCS("ICCBased");
            /**  */
            public static final EnumSourceCS ICCCMYK = new EnumSourceCS("ICCCMYK");
            /**  */
            public static final EnumSourceCS ICCGray = new EnumSourceCS("ICCGray");
            /**  */
            public static final EnumSourceCS ICCLAB = new EnumSourceCS("ICCLAB");
            /**  */
            public static final EnumSourceCS ICCRGB = new EnumSourceCS("ICCRGB");
            /**  */
            public static final EnumSourceCS Lab = new EnumSourceCS("Lab");
            /**  */
            public static final EnumSourceCS Separation = new EnumSourceCS("Separation");
            /**  */
            public static final EnumSourceCS YUV = new EnumSourceCS("YUV");
            /**  */
            public static final EnumSourceCS All = new EnumSourceCS("All");
        }      



        /**
        * Enumeration strings for SourceRenderingIntent
        */

        @SuppressWarnings("rawtypes")
        public static class EnumSourceRenderingIntent extends ValuedEnum
        {
            private static final long serialVersionUID = 1L;
            private static int m_startValue = 0;

            protected EnumSourceRenderingIntent(String name)
            {
                super(name, m_startValue++);
            }

    /**
     * @param enumName the string to convert
     * @return the enum
     */
            public static EnumSourceRenderingIntent getEnum(String enumName)
            {
                return (EnumSourceRenderingIntent) getEnum(EnumSourceRenderingIntent.class, enumName);
            }

    /**
     * @param enumValue the integer to convert
     * @return the enum
     */
            public static EnumSourceRenderingIntent getEnum(int enumValue)
            {
                return (EnumSourceRenderingIntent) getEnum(EnumSourceRenderingIntent.class, enumValue);
            }

    /**
     * @return the map of enums
     */
            public static Map getEnumMap()
            {
                return getEnumMap(EnumSourceRenderingIntent.class);
            }

    /**
     * @return the list of enums
     */
            public static List getEnumList()
            {
                return getEnumList(EnumSourceRenderingIntent.class);
            }

    /**
     * @return the iterator
     */
            public static Iterator iterator()
            {
                return iterator(EnumSourceRenderingIntent.class);
            }

            /**  */
            public static final EnumSourceRenderingIntent ColorSpaceDependent = new EnumSourceRenderingIntent("ColorSpaceDependent");
            /**  */
            public static final EnumSourceRenderingIntent Perceptual = new EnumSourceRenderingIntent("Perceptual");
            /**  */
            public static final EnumSourceRenderingIntent Saturation = new EnumSourceRenderingIntent("Saturation");
            /**  */
            public static final EnumSourceRenderingIntent RelativeColorimetric = new EnumSourceRenderingIntent("RelativeColorimetric");
            /**  */
            public static final EnumSourceRenderingIntent AbsoluteColorimetric = new EnumSourceRenderingIntent("AbsoluteColorimetric");
        }      



/* ************************************************************************
 * Attribute getter / setter
 * ************************************************************************
 */
        
        /* ---------------------------------------------------------------------
        Methods for Attribute BlackPointCompensation
        --------------------------------------------------------------------- */
        /**
          * (36) set attribute BlackPointCompensation
          * @param value the value to set the attribute to
          */
        public void setBlackPointCompensation(boolean value)
        {
            setAttribute(AttributeName.BLACKPOINTCOMPENSATION, value, null);
        }

        /**
          * (18) get boolean attribute BlackPointCompensation
          * @return boolean the value of the attribute
          */
        public boolean getBlackPointCompensation()
        {
            return getBoolAttribute(AttributeName.BLACKPOINTCOMPENSATION, null, false);
        }

        
        /* ---------------------------------------------------------------------
        Methods for Attribute BlackPointCompensationDetails
        --------------------------------------------------------------------- */
        /**
          * (36) set attribute BlackPointCompensationDetails
          * @param value the value to set the attribute to
          */
        public void setBlackPointCompensationDetails(String value)
        {
            setAttribute(AttributeName.BLACKPOINTCOMPENSATIONDETAILS, value, null);
        }

        /**
          * (23) get String attribute BlackPointCompensationDetails
          * @return the value of the attribute
          */
        public String getBlackPointCompensationDetails()
        {
            return getAttribute(AttributeName.BLACKPOINTCOMPENSATIONDETAILS, null, JDFCoreConstants.EMPTYSTRING);
        }

        
        /* ---------------------------------------------------------------------
        Methods for Attribute PreserveBlack
        --------------------------------------------------------------------- */
        /**
          * (36) set attribute PreserveBlack
          * @param value the value to set the attribute to
          */
        public void setPreserveBlack(boolean value)
        {
            setAttribute(AttributeName.PRESERVEBLACK, value, null);
        }

        /**
          * (18) get boolean attribute PreserveBlack
          * @return boolean the value of the attribute
          */
        public boolean getPreserveBlack()
        {
            return getBoolAttribute(AttributeName.PRESERVEBLACK, null, false);
        }

        
        /* ---------------------------------------------------------------------
        Methods for Attribute RenderingIntent
        --------------------------------------------------------------------- */
        /**
          * (5) set attribute RenderingIntent
          * @param enumVar the enumVar to set the attribute to
          */
        public void setRenderingIntent(EnumRenderingIntent enumVar)
        {
            setAttribute(AttributeName.RENDERINGINTENT, enumVar==null ? null : enumVar.getName(), null);
        }

        /**
          * (9) get attribute RenderingIntent
          * @return the value of the attribute
          */
        public EnumRenderingIntent getRenderingIntent()
        {
            return EnumRenderingIntent.getEnum(getAttribute(AttributeName.RENDERINGINTENT, null, "ColorSpaceDependent"));
        }

        
        /* ---------------------------------------------------------------------
        Methods for Attribute RGBGray2Black
        --------------------------------------------------------------------- */
        /**
          * (36) set attribute RGBGray2Black
          * @param value the value to set the attribute to
          */
        public void setRGBGray2Black(boolean value)
        {
            setAttribute(AttributeName.RGBGRAY2BLACK, value, null);
        }

        /**
          * (18) get boolean attribute RGBGray2Black
          * @return boolean the value of the attribute
          */
        public boolean getRGBGray2Black()
        {
            return getBoolAttribute(AttributeName.RGBGRAY2BLACK, null, false);
        }

        
        /* ---------------------------------------------------------------------
        Methods for Attribute RGBGray2BlackThreshold
        --------------------------------------------------------------------- */
        /**
          * (36) set attribute RGBGray2BlackThreshold
          * @param value the value to set the attribute to
          */
        public void setRGBGray2BlackThreshold(double value)
        {
            setAttribute(AttributeName.RGBGRAY2BLACKTHRESHOLD, value, null);
        }

        /**
          * (17) get double attribute RGBGray2BlackThreshold
          * @return double the value of the attribute
          */
        public double getRGBGray2BlackThreshold()
        {
            return getRealAttribute(AttributeName.RGBGRAY2BLACKTHRESHOLD, null, 0.0);
        }

        
        /* ---------------------------------------------------------------------
        Methods for Attribute SourceObjects
        --------------------------------------------------------------------- */
        /**
          * (5.2) set attribute SourceObjects
          * @param v vector of the enumeration values
          */
        public void setSourceObjects(Vector<? extends ValuedEnum> v)
        {
            setEnumerationsAttribute(AttributeName.SOURCEOBJECTS, v, null);
        }

        /**
          * (9.2) get SourceObjects attribute SourceObjects
          * @return Vector of the enumerations
          */
        public Vector<? extends ValuedEnum> getSourceObjects()
        {
            return getEnumerationsAttribute(AttributeName.SOURCEOBJECTS, null, EnumSourceObjects.All, false);
        }

        
        /* ---------------------------------------------------------------------
        Methods for Attribute IgnoreEmbeddedICC
        --------------------------------------------------------------------- */
        /**
          * (36) set attribute IgnoreEmbeddedICC
          * @param value the value to set the attribute to
          */
        public void setIgnoreEmbeddedICC(boolean value)
        {
            setAttribute(AttributeName.IGNOREEMBEDDEDICC, value, null);
        }

        /**
          * (18) get boolean attribute IgnoreEmbeddedICC
          * @return boolean the value of the attribute
          */
        public boolean getIgnoreEmbeddedICC()
        {
            return getBoolAttribute(AttributeName.IGNOREEMBEDDEDICC, null, false);
        }

        
        /* ---------------------------------------------------------------------
        Methods for Attribute ObjectTags
        --------------------------------------------------------------------- */
        /**
          * (36) set attribute ObjectTags
          * @param value the value to set the attribute to
          */
        public void setObjectTags(VString value)
        {
            setAttribute(AttributeName.OBJECTTAGS, value, null);
        }

        /**
          * (21) get VString attribute ObjectTags
          * @return VString the value of the attribute
          */
        public VString getObjectTags()
        {
            VString vStrAttrib = new VString();
            String  s = getAttribute(AttributeName.OBJECTTAGS, null, JDFCoreConstants.EMPTYSTRING);
            vStrAttrib.setAllStrings(s, " ");
            return vStrAttrib;
        }

        
        /* ---------------------------------------------------------------------
        Methods for Attribute Operation
        --------------------------------------------------------------------- */
        /**
          * (5) set attribute Operation
          * @param enumVar the enumVar to set the attribute to
          */
        public void setOperation(EnumOperation enumVar)
        {
            setAttribute(AttributeName.OPERATION, enumVar==null ? null : enumVar.getName(), null);
        }

        /**
          * (9) get attribute Operation
          * @return the value of the attribute
          */
        public EnumOperation getOperation()
        {
            return EnumOperation.getEnum(getAttribute(AttributeName.OPERATION, null, null));
        }

        
        /* ---------------------------------------------------------------------
        Methods for Attribute SourceCS
        --------------------------------------------------------------------- */
        /**
          * (5) set attribute SourceCS
          * @param enumVar the enumVar to set the attribute to
          */
        public void setSourceCS(EnumSourceCS enumVar)
        {
            setAttribute(AttributeName.SOURCECS, enumVar==null ? null : enumVar.getName(), null);
        }

        /**
          * (9) get attribute SourceCS
          * @return the value of the attribute
          */
        public EnumSourceCS getSourceCS()
        {
            return EnumSourceCS.getEnum(getAttribute(AttributeName.SOURCECS, null, null));
        }

        
        /* ---------------------------------------------------------------------
        Methods for Attribute SourceRenderingIntent
        --------------------------------------------------------------------- */
        /**
          * (5) set attribute SourceRenderingIntent
          * @param enumVar the enumVar to set the attribute to
          */
        public void setSourceRenderingIntent(EnumSourceRenderingIntent enumVar)
        {
            setAttribute(AttributeName.SOURCERENDERINGINTENT, enumVar==null ? null : enumVar.getName(), null);
        }

        /**
          * (9) get attribute SourceRenderingIntent
          * @return the value of the attribute
          */
        public EnumSourceRenderingIntent getSourceRenderingIntent()
        {
            return EnumSourceRenderingIntent.getEnum(getAttribute(AttributeName.SOURCERENDERINGINTENT, null, null));
        }

/* ***********************************************************************
 * Element getter / setter
 * ***********************************************************************
 */

    /**
     * (24) const get element DeviceNSpace
     * @return JDFDeviceNSpace the element
     */
    public JDFDeviceNSpace getDeviceNSpace()
    {
        return (JDFDeviceNSpace) getElement(ElementName.DEVICENSPACE, null, 0);
    }

    /** (25) getCreateDeviceNSpace
     * 
     * @return JDFDeviceNSpace the element
     */
    public JDFDeviceNSpace getCreateDeviceNSpace()
    {
        return (JDFDeviceNSpace) getCreateElement_JDFElement(ElementName.DEVICENSPACE, null, 0);
    }

    /**
     * (29) append element DeviceNSpace
     * @return JDFDeviceNSpace the element
     * @ if the element already exists
     */
    public JDFDeviceNSpace appendDeviceNSpace() 
    {
        return (JDFDeviceNSpace) appendElementN(ElementName.DEVICENSPACE, 1, null);
    }

    /** (26) getCreateFileSpec
     * 
     * @param iSkip number of elements to skip
     * @return JDFFileSpec the element
     */
    public JDFFileSpec getCreateFileSpec(int iSkip)
    {
        return (JDFFileSpec) getCreateElement_JDFElement(ElementName.FILESPEC, null, iSkip);
    }

    /**
     * (27) const get element FileSpec
     * @param iSkip number of elements to skip
     * @return JDFFileSpec the element
     * default is getFileSpec(0)     */
    public JDFFileSpec getFileSpec(int iSkip)
    {
        return (JDFFileSpec) getElement(ElementName.FILESPEC, null, iSkip);
    }

    /**
     * Get all FileSpec from the current element
     * 
     * @return Collection<JDFFileSpec>, null if none are available
     */
    public Collection<JDFFileSpec> getAllFileSpec()
    {
        return getChildArrayByClass(JDFFileSpec.class, false, 0);
    }

    /**
     * (30) append element FileSpec
     * @return JDFFileSpec the element
     */
    public JDFFileSpec appendFileSpec()
    {
        return (JDFFileSpec) appendElement(ElementName.FILESPEC, null);
    }

    /**
      * (31) create inter-resource link to refTarget
      * @param refTarget the element that is referenced
      */
    public void refFileSpec(JDFFileSpec refTarget)
    {
        refElement(refTarget);
    }

    /** (26) getCreateSeparationSpec
     * 
     * @param iSkip number of elements to skip
     * @return JDFSeparationSpec the element
     */
    public JDFSeparationSpec getCreateSeparationSpec(int iSkip)
    {
        return (JDFSeparationSpec) getCreateElement_JDFElement(ElementName.SEPARATIONSPEC, null, iSkip);
    }

    /**
     * (27) const get element SeparationSpec
     * @param iSkip number of elements to skip
     * @return JDFSeparationSpec the element
     * default is getSeparationSpec(0)     */
    public JDFSeparationSpec getSeparationSpec(int iSkip)
    {
        return (JDFSeparationSpec) getElement(ElementName.SEPARATIONSPEC, null, iSkip);
    }

    /**
     * Get all SeparationSpec from the current element
     * 
     * @return Collection<JDFSeparationSpec>, null if none are available
     */
    public Collection<JDFSeparationSpec> getAllSeparationSpec()
    {
        return getChildArrayByClass(JDFSeparationSpec.class, false, 0);
    }

    /**
     * (30) append element SeparationSpec
     * @return JDFSeparationSpec the element
     */
    public JDFSeparationSpec appendSeparationSpec()
    {
        return (JDFSeparationSpec) appendElement(ElementName.SEPARATIONSPEC, null);
    }

}
