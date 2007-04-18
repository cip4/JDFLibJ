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
import org.cip4.jdflib.datatypes.JDFRectangle;
import org.cip4.jdflib.resource.JDFResource;
    /*
    *****************************************************************************
    class JDFAutoPDFToPSConversionParams : public JDFResource

    *****************************************************************************
    */

public abstract class JDFAutoPDFToPSConversionParams extends JDFResource
{

    private static final long serialVersionUID = 1L;

    private static AtrInfoTable[] atrInfoTable = new AtrInfoTable[37];
    static
    {
        atrInfoTable[0] = new AtrInfoTable(AttributeName.BINARYOK, 0x33333333, AttributeInfo.EnumAttributeType.boolean_, null, "true");
        atrInfoTable[1] = new AtrInfoTable(AttributeName.CENTERCROPBOX, 0x33333333, AttributeInfo.EnumAttributeType.boolean_, null, "true");
        atrInfoTable[2] = new AtrInfoTable(AttributeName.GENERATEPAGESTREAMS, 0x33333333, AttributeInfo.EnumAttributeType.boolean_, null, "false");
        atrInfoTable[3] = new AtrInfoTable(AttributeName.IGNOREANNOTFORMS, 0x33333333, AttributeInfo.EnumAttributeType.boolean_, null, "false");
        atrInfoTable[4] = new AtrInfoTable(AttributeName.IGNOREBG, 0x33333331, AttributeInfo.EnumAttributeType.boolean_, null, "true");
        atrInfoTable[5] = new AtrInfoTable(AttributeName.IGNORECOLORSEPS, 0x33333333, AttributeInfo.EnumAttributeType.boolean_, null, "false");
        atrInfoTable[6] = new AtrInfoTable(AttributeName.IGNOREDSC, 0x33333333, AttributeInfo.EnumAttributeType.boolean_, null, "true");
        atrInfoTable[7] = new AtrInfoTable(AttributeName.IGNOREEXTERNSTREAMREF, 0x33333333, AttributeInfo.EnumAttributeType.boolean_, null, "false");
        atrInfoTable[8] = new AtrInfoTable(AttributeName.IGNOREHALFTONES, 0x33333333, AttributeInfo.EnumAttributeType.boolean_, null, "false");
        atrInfoTable[9] = new AtrInfoTable(AttributeName.IGNOREOVERPRINT, 0x33333331, AttributeInfo.EnumAttributeType.boolean_, null, "true");
        atrInfoTable[10] = new AtrInfoTable(AttributeName.IGNOREPAGEROTATION, 0x33333333, AttributeInfo.EnumAttributeType.boolean_, null, "false");
        atrInfoTable[11] = new AtrInfoTable(AttributeName.IGNORERAWDATA, 0x33333333, AttributeInfo.EnumAttributeType.boolean_, null, "false");
        atrInfoTable[12] = new AtrInfoTable(AttributeName.IGNORESEPARABLEIMAGESONLY, 0x33333333, AttributeInfo.EnumAttributeType.boolean_, null, "false");
        atrInfoTable[13] = new AtrInfoTable(AttributeName.IGNORESHOWPAGE, 0x33333333, AttributeInfo.EnumAttributeType.boolean_, null, "false");
        atrInfoTable[14] = new AtrInfoTable(AttributeName.IGNORETRANSFERS, 0x33333331, AttributeInfo.EnumAttributeType.boolean_, null, "true");
        atrInfoTable[15] = new AtrInfoTable(AttributeName.IGNORETTFONTSFIRST, 0x33333333, AttributeInfo.EnumAttributeType.boolean_, null, "false");
        atrInfoTable[16] = new AtrInfoTable(AttributeName.IGNOREUCR, 0x33333331, AttributeInfo.EnumAttributeType.boolean_, null, "true");
        atrInfoTable[17] = new AtrInfoTable(AttributeName.INCLUDEBASEFONTS, 0x33333333, AttributeInfo.EnumAttributeType.enumeration, EnumIncludeBaseFonts.getEnum(0), "IncludeNever");
        atrInfoTable[18] = new AtrInfoTable(AttributeName.INCLUDECIDFONTS, 0x33333333, AttributeInfo.EnumAttributeType.enumeration, EnumIncludeCIDFonts.getEnum(0), "IncludeOncePerDoc");
        atrInfoTable[19] = new AtrInfoTable(AttributeName.INCLUDEEMBEDDEDFONTS, 0x33333333, AttributeInfo.EnumAttributeType.enumeration, EnumIncludeEmbeddedFonts.getEnum(0), "IncludeOncePerDoc");
        atrInfoTable[20] = new AtrInfoTable(AttributeName.INCLUDEOTHERRESOURCES, 0x33333333, AttributeInfo.EnumAttributeType.enumeration, EnumIncludeOtherResources.getEnum(0), "IncludeOncePerDoc");
        atrInfoTable[21] = new AtrInfoTable(AttributeName.INCLUDEPROCSETS, 0x33333333, AttributeInfo.EnumAttributeType.enumeration, EnumIncludeProcSets.getEnum(0), "IncludeOncePerDoc");
        atrInfoTable[22] = new AtrInfoTable(AttributeName.INCLUDETRUETYPEFONTS, 0x33333333, AttributeInfo.EnumAttributeType.enumeration, EnumIncludeTrueTypeFonts.getEnum(0), "IncludeOncePerDoc");
        atrInfoTable[23] = new AtrInfoTable(AttributeName.INCLUDETYPE1FONTS, 0x33333333, AttributeInfo.EnumAttributeType.enumeration, EnumIncludeType1Fonts.getEnum(0), "IncludeOncePerDoc");
        atrInfoTable[24] = new AtrInfoTable(AttributeName.INCLUDETYPE3FONTS, 0x33333333, AttributeInfo.EnumAttributeType.enumeration, EnumIncludeType3Fonts.getEnum(0), null);
        atrInfoTable[25] = new AtrInfoTable(AttributeName.OUTPUTTYPE, 0x33333333, AttributeInfo.EnumAttributeType.enumeration, EnumOutputType.getEnum(0), "PostScript");
        atrInfoTable[26] = new AtrInfoTable(AttributeName.PSLEVEL, 0x33333333, AttributeInfo.EnumAttributeType.integer, null, "2");
        atrInfoTable[27] = new AtrInfoTable(AttributeName.SCALE, 0x33333333, AttributeInfo.EnumAttributeType.double_, null, "100");
        atrInfoTable[28] = new AtrInfoTable(AttributeName.SETPAGESIZE, 0x33333333, AttributeInfo.EnumAttributeType.boolean_, null, "false");
        atrInfoTable[29] = new AtrInfoTable(AttributeName.SETUPPROCSETS, 0x33333333, AttributeInfo.EnumAttributeType.boolean_, null, "true");
        atrInfoTable[30] = new AtrInfoTable(AttributeName.SHRINKTOFIT, 0x33333333, AttributeInfo.EnumAttributeType.boolean_, null, "false");
        atrInfoTable[31] = new AtrInfoTable(AttributeName.SUPPRESSCENTER, 0x33333333, AttributeInfo.EnumAttributeType.boolean_, null, "false");
        atrInfoTable[32] = new AtrInfoTable(AttributeName.SUPPRESSROTATE, 0x33333333, AttributeInfo.EnumAttributeType.boolean_, null, "false");
        atrInfoTable[33] = new AtrInfoTable(AttributeName.TTAST42, 0x33333333, AttributeInfo.EnumAttributeType.boolean_, null, "false");
        atrInfoTable[34] = new AtrInfoTable(AttributeName.USEFONTALIASNAMES, 0x33333333, AttributeInfo.EnumAttributeType.boolean_, null, "false");
        atrInfoTable[35] = new AtrInfoTable(AttributeName.IGNOREDEVICEEXTGSTATE, 0x44444443, AttributeInfo.EnumAttributeType.boolean_, null, null);
        atrInfoTable[36] = new AtrInfoTable(AttributeName.BOUNDINGBOX, 0x33333333, AttributeInfo.EnumAttributeType.rectangle, null, null);
    }
    
    protected AttributeInfo getTheAttributeInfo()
    {
        return super.getTheAttributeInfo().updateReplace(atrInfoTable);
    }



    /**
     * Constructor for JDFAutoPDFToPSConversionParams
     * @param myOwnerDocument
     * @param qualifiedName
     */
    protected JDFAutoPDFToPSConversionParams(
        CoreDocumentImpl myOwnerDocument,
        String qualifiedName)
    {
        super(myOwnerDocument, qualifiedName);
    }

    /**
     * Constructor for JDFAutoPDFToPSConversionParams
     * @param myOwnerDocument
     * @param myNamespaceURI
     * @param qualifiedName
     */
    protected JDFAutoPDFToPSConversionParams(
        CoreDocumentImpl myOwnerDocument,
        String myNamespaceURI,
        String qualifiedName)
    {
        super(myOwnerDocument, myNamespaceURI, qualifiedName);
    }

    /**
     * Constructor for JDFAutoPDFToPSConversionParams
     * @param myOwnerDocument
     * @param myNamespaceURI
     * @param qualifiedName
     * @param myLocalName
     */
    protected JDFAutoPDFToPSConversionParams(
        CoreDocumentImpl myOwnerDocument,
        String myNamespaceURI,
        String qualifiedName,
        String myLocalName)
    {
        super(myOwnerDocument, myNamespaceURI, qualifiedName, myLocalName);
    }


    public String toString()
    {
        return " JDFAutoPDFToPSConversionParams[  --> " + super.toString() + " ]";
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
        * Enumeration strings for IncludeBaseFonts
        */

        public static class EnumIncludeBaseFonts extends ValuedEnum
        {
            private static final long serialVersionUID = 1L;
            private static int m_startValue = 0;

            private EnumIncludeBaseFonts(String name)
            {
                super(name, m_startValue++);
            }

            public static EnumIncludeBaseFonts getEnum(String enumName)
            {
                return (EnumIncludeBaseFonts) getEnum(EnumIncludeBaseFonts.class, enumName);
            }

            public static EnumIncludeBaseFonts getEnum(int enumValue)
            {
                return (EnumIncludeBaseFonts) getEnum(EnumIncludeBaseFonts.class, enumValue);
            }

            public static Map getEnumMap()
            {
                return getEnumMap(EnumIncludeBaseFonts.class);
            }

            public static List getEnumList()
            {
                return getEnumList(EnumIncludeBaseFonts.class);
            }

            public static Iterator iterator()
            {
                return iterator(EnumIncludeBaseFonts.class);
            }

            public static final EnumIncludeBaseFonts IncludeNever = new EnumIncludeBaseFonts("IncludeNever");
            public static final EnumIncludeBaseFonts IncludeOncePerDoc = new EnumIncludeBaseFonts("IncludeOncePerDoc");
            public static final EnumIncludeBaseFonts IncludeOncePerPage = new EnumIncludeBaseFonts("IncludeOncePerPage");
        }      



        /**
        * Enumeration strings for IncludeCIDFonts
        */

        public static class EnumIncludeCIDFonts extends ValuedEnum
        {
            private static final long serialVersionUID = 1L;
            private static int m_startValue = 0;

            private EnumIncludeCIDFonts(String name)
            {
                super(name, m_startValue++);
            }

            public static EnumIncludeCIDFonts getEnum(String enumName)
            {
                return (EnumIncludeCIDFonts) getEnum(EnumIncludeCIDFonts.class, enumName);
            }

            public static EnumIncludeCIDFonts getEnum(int enumValue)
            {
                return (EnumIncludeCIDFonts) getEnum(EnumIncludeCIDFonts.class, enumValue);
            }

            public static Map getEnumMap()
            {
                return getEnumMap(EnumIncludeCIDFonts.class);
            }

            public static List getEnumList()
            {
                return getEnumList(EnumIncludeCIDFonts.class);
            }

            public static Iterator iterator()
            {
                return iterator(EnumIncludeCIDFonts.class);
            }

            public static final EnumIncludeCIDFonts IncludeNever = new EnumIncludeCIDFonts("IncludeNever");
            public static final EnumIncludeCIDFonts IncludeOncePerDoc = new EnumIncludeCIDFonts("IncludeOncePerDoc");
            public static final EnumIncludeCIDFonts IncludeOncePerPage = new EnumIncludeCIDFonts("IncludeOncePerPage");
        }      



        /**
        * Enumeration strings for IncludeEmbeddedFonts
        */

        public static class EnumIncludeEmbeddedFonts extends ValuedEnum
        {
            private static final long serialVersionUID = 1L;
            private static int m_startValue = 0;

            private EnumIncludeEmbeddedFonts(String name)
            {
                super(name, m_startValue++);
            }

            public static EnumIncludeEmbeddedFonts getEnum(String enumName)
            {
                return (EnumIncludeEmbeddedFonts) getEnum(EnumIncludeEmbeddedFonts.class, enumName);
            }

            public static EnumIncludeEmbeddedFonts getEnum(int enumValue)
            {
                return (EnumIncludeEmbeddedFonts) getEnum(EnumIncludeEmbeddedFonts.class, enumValue);
            }

            public static Map getEnumMap()
            {
                return getEnumMap(EnumIncludeEmbeddedFonts.class);
            }

            public static List getEnumList()
            {
                return getEnumList(EnumIncludeEmbeddedFonts.class);
            }

            public static Iterator iterator()
            {
                return iterator(EnumIncludeEmbeddedFonts.class);
            }

            public static final EnumIncludeEmbeddedFonts IncludeNever = new EnumIncludeEmbeddedFonts("IncludeNever");
            public static final EnumIncludeEmbeddedFonts IncludeOncePerDoc = new EnumIncludeEmbeddedFonts("IncludeOncePerDoc");
            public static final EnumIncludeEmbeddedFonts IncludeOncePerPage = new EnumIncludeEmbeddedFonts("IncludeOncePerPage");
        }      



        /**
        * Enumeration strings for IncludeOtherResources
        */

        public static class EnumIncludeOtherResources extends ValuedEnum
        {
            private static final long serialVersionUID = 1L;
            private static int m_startValue = 0;

            private EnumIncludeOtherResources(String name)
            {
                super(name, m_startValue++);
            }

            public static EnumIncludeOtherResources getEnum(String enumName)
            {
                return (EnumIncludeOtherResources) getEnum(EnumIncludeOtherResources.class, enumName);
            }

            public static EnumIncludeOtherResources getEnum(int enumValue)
            {
                return (EnumIncludeOtherResources) getEnum(EnumIncludeOtherResources.class, enumValue);
            }

            public static Map getEnumMap()
            {
                return getEnumMap(EnumIncludeOtherResources.class);
            }

            public static List getEnumList()
            {
                return getEnumList(EnumIncludeOtherResources.class);
            }

            public static Iterator iterator()
            {
                return iterator(EnumIncludeOtherResources.class);
            }

            public static final EnumIncludeOtherResources IncludeNever = new EnumIncludeOtherResources("IncludeNever");
            public static final EnumIncludeOtherResources IncludeOncePerDoc = new EnumIncludeOtherResources("IncludeOncePerDoc");
            public static final EnumIncludeOtherResources IncludeOncePerPage = new EnumIncludeOtherResources("IncludeOncePerPage");
        }      



        /**
        * Enumeration strings for IncludeProcSets
        */

        public static class EnumIncludeProcSets extends ValuedEnum
        {
            private static final long serialVersionUID = 1L;
            private static int m_startValue = 0;

            private EnumIncludeProcSets(String name)
            {
                super(name, m_startValue++);
            }

            public static EnumIncludeProcSets getEnum(String enumName)
            {
                return (EnumIncludeProcSets) getEnum(EnumIncludeProcSets.class, enumName);
            }

            public static EnumIncludeProcSets getEnum(int enumValue)
            {
                return (EnumIncludeProcSets) getEnum(EnumIncludeProcSets.class, enumValue);
            }

            public static Map getEnumMap()
            {
                return getEnumMap(EnumIncludeProcSets.class);
            }

            public static List getEnumList()
            {
                return getEnumList(EnumIncludeProcSets.class);
            }

            public static Iterator iterator()
            {
                return iterator(EnumIncludeProcSets.class);
            }

            public static final EnumIncludeProcSets IncludeNever = new EnumIncludeProcSets("IncludeNever");
            public static final EnumIncludeProcSets IncludeOncePerDoc = new EnumIncludeProcSets("IncludeOncePerDoc");
            public static final EnumIncludeProcSets IncludeOncePerPage = new EnumIncludeProcSets("IncludeOncePerPage");
        }      



        /**
        * Enumeration strings for IncludeTrueTypeFonts
        */

        public static class EnumIncludeTrueTypeFonts extends ValuedEnum
        {
            private static final long serialVersionUID = 1L;
            private static int m_startValue = 0;

            private EnumIncludeTrueTypeFonts(String name)
            {
                super(name, m_startValue++);
            }

            public static EnumIncludeTrueTypeFonts getEnum(String enumName)
            {
                return (EnumIncludeTrueTypeFonts) getEnum(EnumIncludeTrueTypeFonts.class, enumName);
            }

            public static EnumIncludeTrueTypeFonts getEnum(int enumValue)
            {
                return (EnumIncludeTrueTypeFonts) getEnum(EnumIncludeTrueTypeFonts.class, enumValue);
            }

            public static Map getEnumMap()
            {
                return getEnumMap(EnumIncludeTrueTypeFonts.class);
            }

            public static List getEnumList()
            {
                return getEnumList(EnumIncludeTrueTypeFonts.class);
            }

            public static Iterator iterator()
            {
                return iterator(EnumIncludeTrueTypeFonts.class);
            }

            public static final EnumIncludeTrueTypeFonts IncludeNever = new EnumIncludeTrueTypeFonts("IncludeNever");
            public static final EnumIncludeTrueTypeFonts IncludeOncePerDoc = new EnumIncludeTrueTypeFonts("IncludeOncePerDoc");
            public static final EnumIncludeTrueTypeFonts IncludeOncePerPage = new EnumIncludeTrueTypeFonts("IncludeOncePerPage");
        }      



        /**
        * Enumeration strings for IncludeType1Fonts
        */

        public static class EnumIncludeType1Fonts extends ValuedEnum
        {
            private static final long serialVersionUID = 1L;
            private static int m_startValue = 0;

            private EnumIncludeType1Fonts(String name)
            {
                super(name, m_startValue++);
            }

            public static EnumIncludeType1Fonts getEnum(String enumName)
            {
                return (EnumIncludeType1Fonts) getEnum(EnumIncludeType1Fonts.class, enumName);
            }

            public static EnumIncludeType1Fonts getEnum(int enumValue)
            {
                return (EnumIncludeType1Fonts) getEnum(EnumIncludeType1Fonts.class, enumValue);
            }

            public static Map getEnumMap()
            {
                return getEnumMap(EnumIncludeType1Fonts.class);
            }

            public static List getEnumList()
            {
                return getEnumList(EnumIncludeType1Fonts.class);
            }

            public static Iterator iterator()
            {
                return iterator(EnumIncludeType1Fonts.class);
            }

            public static final EnumIncludeType1Fonts IncludeNever = new EnumIncludeType1Fonts("IncludeNever");
            public static final EnumIncludeType1Fonts IncludeOncePerDoc = new EnumIncludeType1Fonts("IncludeOncePerDoc");
            public static final EnumIncludeType1Fonts IncludeOncePerPage = new EnumIncludeType1Fonts("IncludeOncePerPage");
        }      



        /**
        * Enumeration strings for IncludeType3Fonts
        */

        public static class EnumIncludeType3Fonts extends ValuedEnum
        {
            private static final long serialVersionUID = 1L;
            private static int m_startValue = 0;

            private EnumIncludeType3Fonts(String name)
            {
                super(name, m_startValue++);
            }

            public static EnumIncludeType3Fonts getEnum(String enumName)
            {
                return (EnumIncludeType3Fonts) getEnum(EnumIncludeType3Fonts.class, enumName);
            }

            public static EnumIncludeType3Fonts getEnum(int enumValue)
            {
                return (EnumIncludeType3Fonts) getEnum(EnumIncludeType3Fonts.class, enumValue);
            }

            public static Map getEnumMap()
            {
                return getEnumMap(EnumIncludeType3Fonts.class);
            }

            public static List getEnumList()
            {
                return getEnumList(EnumIncludeType3Fonts.class);
            }

            public static Iterator iterator()
            {
                return iterator(EnumIncludeType3Fonts.class);
            }

            public static final EnumIncludeType3Fonts IncludeNever = new EnumIncludeType3Fonts("IncludeNever");
            public static final EnumIncludeType3Fonts IncludeOncePerDoc = new EnumIncludeType3Fonts("IncludeOncePerDoc");
            public static final EnumIncludeType3Fonts IncludeOncePerPage = new EnumIncludeType3Fonts("IncludeOncePerPage");
        }      



        /**
        * Enumeration strings for OutputType
        */

        public static class EnumOutputType extends ValuedEnum
        {
            private static final long serialVersionUID = 1L;
            private static int m_startValue = 0;

            private EnumOutputType(String name)
            {
                super(name, m_startValue++);
            }

            public static EnumOutputType getEnum(String enumName)
            {
                return (EnumOutputType) getEnum(EnumOutputType.class, enumName);
            }

            public static EnumOutputType getEnum(int enumValue)
            {
                return (EnumOutputType) getEnum(EnumOutputType.class, enumValue);
            }

            public static Map getEnumMap()
            {
                return getEnumMap(EnumOutputType.class);
            }

            public static List getEnumList()
            {
                return getEnumList(EnumOutputType.class);
            }

            public static Iterator iterator()
            {
                return iterator(EnumOutputType.class);
            }

            public static final EnumOutputType PostScript = new EnumOutputType("PostScript");
            public static final EnumOutputType EPS = new EnumOutputType("EPS");
        }      



/* ************************************************************************
 * Attribute getter / setter
 * ************************************************************************
 */
        
        /* ---------------------------------------------------------------------
        Methods for Attribute BinaryOK
        --------------------------------------------------------------------- */
        /**
          * (36) set attribute BinaryOK
          * @param value: the value to set the attribute to
          */
        public void setBinaryOK(boolean value)
        {
            setAttribute(AttributeName.BINARYOK, value, null);
        }

        /**
          * (18) get boolean attribute BinaryOK
          * @return boolean the value of the attribute
          */
        public boolean getBinaryOK()
        {
            return getBoolAttribute(AttributeName.BINARYOK, null, true);
        }

        
        /* ---------------------------------------------------------------------
        Methods for Attribute CenterCropBox
        --------------------------------------------------------------------- */
        /**
          * (36) set attribute CenterCropBox
          * @param value: the value to set the attribute to
          */
        public void setCenterCropBox(boolean value)
        {
            setAttribute(AttributeName.CENTERCROPBOX, value, null);
        }

        /**
          * (18) get boolean attribute CenterCropBox
          * @return boolean the value of the attribute
          */
        public boolean getCenterCropBox()
        {
            return getBoolAttribute(AttributeName.CENTERCROPBOX, null, true);
        }

        
        /* ---------------------------------------------------------------------
        Methods for Attribute GeneratePageStreams
        --------------------------------------------------------------------- */
        /**
          * (36) set attribute GeneratePageStreams
          * @param value: the value to set the attribute to
          */
        public void setGeneratePageStreams(boolean value)
        {
            setAttribute(AttributeName.GENERATEPAGESTREAMS, value, null);
        }

        /**
          * (18) get boolean attribute GeneratePageStreams
          * @return boolean the value of the attribute
          */
        public boolean getGeneratePageStreams()
        {
            return getBoolAttribute(AttributeName.GENERATEPAGESTREAMS, null, false);
        }

        
        /* ---------------------------------------------------------------------
        Methods for Attribute IgnoreAnnotForms
        --------------------------------------------------------------------- */
        /**
          * (36) set attribute IgnoreAnnotForms
          * @param value: the value to set the attribute to
          */
        public void setIgnoreAnnotForms(boolean value)
        {
            setAttribute(AttributeName.IGNOREANNOTFORMS, value, null);
        }

        /**
          * (18) get boolean attribute IgnoreAnnotForms
          * @return boolean the value of the attribute
          */
        public boolean getIgnoreAnnotForms()
        {
            return getBoolAttribute(AttributeName.IGNOREANNOTFORMS, null, false);
        }

        
        /* ---------------------------------------------------------------------
        Methods for Attribute IgnoreBG
        --------------------------------------------------------------------- */
        /**
          * (36) set attribute IgnoreBG
          * @param value: the value to set the attribute to
          */
        public void setIgnoreBG(boolean value)
        {
            setAttribute(AttributeName.IGNOREBG, value, null);
        }

        /**
          * (18) get boolean attribute IgnoreBG
          * @return boolean the value of the attribute
          */
        public boolean getIgnoreBG()
        {
            return getBoolAttribute(AttributeName.IGNOREBG, null, true);
        }

        
        /* ---------------------------------------------------------------------
        Methods for Attribute IgnoreColorSeps
        --------------------------------------------------------------------- */
        /**
          * (36) set attribute IgnoreColorSeps
          * @param value: the value to set the attribute to
          */
        public void setIgnoreColorSeps(boolean value)
        {
            setAttribute(AttributeName.IGNORECOLORSEPS, value, null);
        }

        /**
          * (18) get boolean attribute IgnoreColorSeps
          * @return boolean the value of the attribute
          */
        public boolean getIgnoreColorSeps()
        {
            return getBoolAttribute(AttributeName.IGNORECOLORSEPS, null, false);
        }

        
        /* ---------------------------------------------------------------------
        Methods for Attribute IgnoreDSC
        --------------------------------------------------------------------- */
        /**
          * (36) set attribute IgnoreDSC
          * @param value: the value to set the attribute to
          */
        public void setIgnoreDSC(boolean value)
        {
            setAttribute(AttributeName.IGNOREDSC, value, null);
        }

        /**
          * (18) get boolean attribute IgnoreDSC
          * @return boolean the value of the attribute
          */
        public boolean getIgnoreDSC()
        {
            return getBoolAttribute(AttributeName.IGNOREDSC, null, true);
        }

        
        /* ---------------------------------------------------------------------
        Methods for Attribute IgnoreExternStreamRef
        --------------------------------------------------------------------- */
        /**
          * (36) set attribute IgnoreExternStreamRef
          * @param value: the value to set the attribute to
          */
        public void setIgnoreExternStreamRef(boolean value)
        {
            setAttribute(AttributeName.IGNOREEXTERNSTREAMREF, value, null);
        }

        /**
          * (18) get boolean attribute IgnoreExternStreamRef
          * @return boolean the value of the attribute
          */
        public boolean getIgnoreExternStreamRef()
        {
            return getBoolAttribute(AttributeName.IGNOREEXTERNSTREAMREF, null, false);
        }

        
        /* ---------------------------------------------------------------------
        Methods for Attribute IgnoreHalftones
        --------------------------------------------------------------------- */
        /**
          * (36) set attribute IgnoreHalftones
          * @param value: the value to set the attribute to
          */
        public void setIgnoreHalftones(boolean value)
        {
            setAttribute(AttributeName.IGNOREHALFTONES, value, null);
        }

        /**
          * (18) get boolean attribute IgnoreHalftones
          * @return boolean the value of the attribute
          */
        public boolean getIgnoreHalftones()
        {
            return getBoolAttribute(AttributeName.IGNOREHALFTONES, null, false);
        }

        
        /* ---------------------------------------------------------------------
        Methods for Attribute IgnoreOverprint
        --------------------------------------------------------------------- */
        /**
          * (36) set attribute IgnoreOverprint
          * @param value: the value to set the attribute to
          */
        public void setIgnoreOverprint(boolean value)
        {
            setAttribute(AttributeName.IGNOREOVERPRINT, value, null);
        }

        /**
          * (18) get boolean attribute IgnoreOverprint
          * @return boolean the value of the attribute
          */
        public boolean getIgnoreOverprint()
        {
            return getBoolAttribute(AttributeName.IGNOREOVERPRINT, null, true);
        }

        
        /* ---------------------------------------------------------------------
        Methods for Attribute IgnorePageRotation
        --------------------------------------------------------------------- */
        /**
          * (36) set attribute IgnorePageRotation
          * @param value: the value to set the attribute to
          */
        public void setIgnorePageRotation(boolean value)
        {
            setAttribute(AttributeName.IGNOREPAGEROTATION, value, null);
        }

        /**
          * (18) get boolean attribute IgnorePageRotation
          * @return boolean the value of the attribute
          */
        public boolean getIgnorePageRotation()
        {
            return getBoolAttribute(AttributeName.IGNOREPAGEROTATION, null, false);
        }

        
        /* ---------------------------------------------------------------------
        Methods for Attribute IgnoreRawData
        --------------------------------------------------------------------- */
        /**
          * (36) set attribute IgnoreRawData
          * @param value: the value to set the attribute to
          */
        public void setIgnoreRawData(boolean value)
        {
            setAttribute(AttributeName.IGNORERAWDATA, value, null);
        }

        /**
          * (18) get boolean attribute IgnoreRawData
          * @return boolean the value of the attribute
          */
        public boolean getIgnoreRawData()
        {
            return getBoolAttribute(AttributeName.IGNORERAWDATA, null, false);
        }

        
        /* ---------------------------------------------------------------------
        Methods for Attribute IgnoreSeparableImagesOnly
        --------------------------------------------------------------------- */
        /**
          * (36) set attribute IgnoreSeparableImagesOnly
          * @param value: the value to set the attribute to
          */
        public void setIgnoreSeparableImagesOnly(boolean value)
        {
            setAttribute(AttributeName.IGNORESEPARABLEIMAGESONLY, value, null);
        }

        /**
          * (18) get boolean attribute IgnoreSeparableImagesOnly
          * @return boolean the value of the attribute
          */
        public boolean getIgnoreSeparableImagesOnly()
        {
            return getBoolAttribute(AttributeName.IGNORESEPARABLEIMAGESONLY, null, false);
        }

        
        /* ---------------------------------------------------------------------
        Methods for Attribute IgnoreShowPage
        --------------------------------------------------------------------- */
        /**
          * (36) set attribute IgnoreShowPage
          * @param value: the value to set the attribute to
          */
        public void setIgnoreShowPage(boolean value)
        {
            setAttribute(AttributeName.IGNORESHOWPAGE, value, null);
        }

        /**
          * (18) get boolean attribute IgnoreShowPage
          * @return boolean the value of the attribute
          */
        public boolean getIgnoreShowPage()
        {
            return getBoolAttribute(AttributeName.IGNORESHOWPAGE, null, false);
        }

        
        /* ---------------------------------------------------------------------
        Methods for Attribute IgnoreTransfers
        --------------------------------------------------------------------- */
        /**
          * (36) set attribute IgnoreTransfers
          * @param value: the value to set the attribute to
          */
        public void setIgnoreTransfers(boolean value)
        {
            setAttribute(AttributeName.IGNORETRANSFERS, value, null);
        }

        /**
          * (18) get boolean attribute IgnoreTransfers
          * @return boolean the value of the attribute
          */
        public boolean getIgnoreTransfers()
        {
            return getBoolAttribute(AttributeName.IGNORETRANSFERS, null, true);
        }

        
        /* ---------------------------------------------------------------------
        Methods for Attribute IgnoreTTFontsFirst
        --------------------------------------------------------------------- */
        /**
          * (36) set attribute IgnoreTTFontsFirst
          * @param value: the value to set the attribute to
          */
        public void setIgnoreTTFontsFirst(boolean value)
        {
            setAttribute(AttributeName.IGNORETTFONTSFIRST, value, null);
        }

        /**
          * (18) get boolean attribute IgnoreTTFontsFirst
          * @return boolean the value of the attribute
          */
        public boolean getIgnoreTTFontsFirst()
        {
            return getBoolAttribute(AttributeName.IGNORETTFONTSFIRST, null, false);
        }

        
        /* ---------------------------------------------------------------------
        Methods for Attribute IgnoreUCR
        --------------------------------------------------------------------- */
        /**
          * (36) set attribute IgnoreUCR
          * @param value: the value to set the attribute to
          */
        public void setIgnoreUCR(boolean value)
        {
            setAttribute(AttributeName.IGNOREUCR, value, null);
        }

        /**
          * (18) get boolean attribute IgnoreUCR
          * @return boolean the value of the attribute
          */
        public boolean getIgnoreUCR()
        {
            return getBoolAttribute(AttributeName.IGNOREUCR, null, true);
        }

        
        /* ---------------------------------------------------------------------
        Methods for Attribute IncludeBaseFonts
        --------------------------------------------------------------------- */
        /**
          * (5) set attribute IncludeBaseFonts
          * @param enumVar: the enumVar to set the attribute to
          */
        public void setIncludeBaseFonts(EnumIncludeBaseFonts enumVar)
        {
            setAttribute(AttributeName.INCLUDEBASEFONTS, enumVar.getName(), null);
        }

        /**
          * (9) get attribute IncludeBaseFonts
          * @return the value of the attribute
          */
        public EnumIncludeBaseFonts getIncludeBaseFonts()
        {
            return EnumIncludeBaseFonts.getEnum(getAttribute(AttributeName.INCLUDEBASEFONTS, null, "IncludeNever"));
        }

        
        /* ---------------------------------------------------------------------
        Methods for Attribute IncludeCIDFonts
        --------------------------------------------------------------------- */
        /**
          * (5) set attribute IncludeCIDFonts
          * @param enumVar: the enumVar to set the attribute to
          */
        public void setIncludeCIDFonts(EnumIncludeCIDFonts enumVar)
        {
            setAttribute(AttributeName.INCLUDECIDFONTS, enumVar.getName(), null);
        }

        /**
          * (9) get attribute IncludeCIDFonts
          * @return the value of the attribute
          */
        public EnumIncludeCIDFonts getIncludeCIDFonts()
        {
            return EnumIncludeCIDFonts.getEnum(getAttribute(AttributeName.INCLUDECIDFONTS, null, "IncludeOncePerDoc"));
        }

        
        /* ---------------------------------------------------------------------
        Methods for Attribute IncludeEmbeddedFonts
        --------------------------------------------------------------------- */
        /**
          * (5) set attribute IncludeEmbeddedFonts
          * @param enumVar: the enumVar to set the attribute to
          */
        public void setIncludeEmbeddedFonts(EnumIncludeEmbeddedFonts enumVar)
        {
            setAttribute(AttributeName.INCLUDEEMBEDDEDFONTS, enumVar.getName(), null);
        }

        /**
          * (9) get attribute IncludeEmbeddedFonts
          * @return the value of the attribute
          */
        public EnumIncludeEmbeddedFonts getIncludeEmbeddedFonts()
        {
            return EnumIncludeEmbeddedFonts.getEnum(getAttribute(AttributeName.INCLUDEEMBEDDEDFONTS, null, "IncludeOncePerDoc"));
        }

        
        /* ---------------------------------------------------------------------
        Methods for Attribute IncludeOtherResources
        --------------------------------------------------------------------- */
        /**
          * (5) set attribute IncludeOtherResources
          * @param enumVar: the enumVar to set the attribute to
          */
        public void setIncludeOtherResources(EnumIncludeOtherResources enumVar)
        {
            setAttribute(AttributeName.INCLUDEOTHERRESOURCES, enumVar.getName(), null);
        }

        /**
          * (9) get attribute IncludeOtherResources
          * @return the value of the attribute
          */
        public EnumIncludeOtherResources getIncludeOtherResources()
        {
            return EnumIncludeOtherResources.getEnum(getAttribute(AttributeName.INCLUDEOTHERRESOURCES, null, "IncludeOncePerDoc"));
        }

        
        /* ---------------------------------------------------------------------
        Methods for Attribute IncludeProcSets
        --------------------------------------------------------------------- */
        /**
          * (5) set attribute IncludeProcSets
          * @param enumVar: the enumVar to set the attribute to
          */
        public void setIncludeProcSets(EnumIncludeProcSets enumVar)
        {
            setAttribute(AttributeName.INCLUDEPROCSETS, enumVar.getName(), null);
        }

        /**
          * (9) get attribute IncludeProcSets
          * @return the value of the attribute
          */
        public EnumIncludeProcSets getIncludeProcSets()
        {
            return EnumIncludeProcSets.getEnum(getAttribute(AttributeName.INCLUDEPROCSETS, null, "IncludeOncePerDoc"));
        }

        
        /* ---------------------------------------------------------------------
        Methods for Attribute IncludeTrueTypeFonts
        --------------------------------------------------------------------- */
        /**
          * (5) set attribute IncludeTrueTypeFonts
          * @param enumVar: the enumVar to set the attribute to
          */
        public void setIncludeTrueTypeFonts(EnumIncludeTrueTypeFonts enumVar)
        {
            setAttribute(AttributeName.INCLUDETRUETYPEFONTS, enumVar.getName(), null);
        }

        /**
          * (9) get attribute IncludeTrueTypeFonts
          * @return the value of the attribute
          */
        public EnumIncludeTrueTypeFonts getIncludeTrueTypeFonts()
        {
            return EnumIncludeTrueTypeFonts.getEnum(getAttribute(AttributeName.INCLUDETRUETYPEFONTS, null, "IncludeOncePerDoc"));
        }

        
        /* ---------------------------------------------------------------------
        Methods for Attribute IncludeType1Fonts
        --------------------------------------------------------------------- */
        /**
          * (5) set attribute IncludeType1Fonts
          * @param enumVar: the enumVar to set the attribute to
          */
        public void setIncludeType1Fonts(EnumIncludeType1Fonts enumVar)
        {
            setAttribute(AttributeName.INCLUDETYPE1FONTS, enumVar.getName(), null);
        }

        /**
          * (9) get attribute IncludeType1Fonts
          * @return the value of the attribute
          */
        public EnumIncludeType1Fonts getIncludeType1Fonts()
        {
            return EnumIncludeType1Fonts.getEnum(getAttribute(AttributeName.INCLUDETYPE1FONTS, null, "IncludeOncePerDoc"));
        }

        
        /* ---------------------------------------------------------------------
        Methods for Attribute IncludeType3Fonts
        --------------------------------------------------------------------- */
        /**
          * (5) set attribute IncludeType3Fonts
          * @param enumVar: the enumVar to set the attribute to
          */
        public void setIncludeType3Fonts(EnumIncludeType3Fonts enumVar)
        {
            setAttribute(AttributeName.INCLUDETYPE3FONTS, enumVar.getName(), null);
        }

        /**
          * (9) get attribute IncludeType3Fonts
          * @return the value of the attribute
          */
        public EnumIncludeType3Fonts getIncludeType3Fonts()
        {
            return EnumIncludeType3Fonts.getEnum(getAttribute(AttributeName.INCLUDETYPE3FONTS, null, null));
        }

        
        /* ---------------------------------------------------------------------
        Methods for Attribute OutputType
        --------------------------------------------------------------------- */
        /**
          * (5) set attribute OutputType
          * @param enumVar: the enumVar to set the attribute to
          */
        public void setOutputType(EnumOutputType enumVar)
        {
            setAttribute(AttributeName.OUTPUTTYPE, enumVar.getName(), null);
        }

        /**
          * (9) get attribute OutputType
          * @return the value of the attribute
          */
        public EnumOutputType getOutputType()
        {
            return EnumOutputType.getEnum(getAttribute(AttributeName.OUTPUTTYPE, null, "PostScript"));
        }

        
        /* ---------------------------------------------------------------------
        Methods for Attribute PSLevel
        --------------------------------------------------------------------- */
        /**
          * (36) set attribute PSLevel
          * @param value: the value to set the attribute to
          */
        public void setPSLevel(int value)
        {
            setAttribute(AttributeName.PSLEVEL, value, null);
        }

        /**
          * (15) get int attribute PSLevel
          * @return int the value of the attribute
          */
        public int getPSLevel()
        {
            return getIntAttribute(AttributeName.PSLEVEL, null, 2);
        }

        
        /* ---------------------------------------------------------------------
        Methods for Attribute Scale
        --------------------------------------------------------------------- */
        /**
          * (36) set attribute Scale
          * @param value: the value to set the attribute to
          */
        public void setScale(double value)
        {
            setAttribute(AttributeName.SCALE, value, null);
        }

        /**
          * (17) get double attribute Scale
          * @return double the value of the attribute
          */
        public double getScale()
        {
            return getRealAttribute(AttributeName.SCALE, null, 0.0);
        }

        
        /* ---------------------------------------------------------------------
        Methods for Attribute SetPageSize
        --------------------------------------------------------------------- */
        /**
          * (36) set attribute SetPageSize
          * @param value: the value to set the attribute to
          */
        public void setSetPageSize(boolean value)
        {
            setAttribute(AttributeName.SETPAGESIZE, value, null);
        }

        /**
          * (18) get boolean attribute SetPageSize
          * @return boolean the value of the attribute
          */
        public boolean getSetPageSize()
        {
            return getBoolAttribute(AttributeName.SETPAGESIZE, null, false);
        }

        
        /* ---------------------------------------------------------------------
        Methods for Attribute SetupProcsets
        --------------------------------------------------------------------- */
        /**
          * (36) set attribute SetupProcsets
          * @param value: the value to set the attribute to
          */
        public void setSetupProcsets(boolean value)
        {
            setAttribute(AttributeName.SETUPPROCSETS, value, null);
        }

        /**
          * (18) get boolean attribute SetupProcsets
          * @return boolean the value of the attribute
          */
        public boolean getSetupProcsets()
        {
            return getBoolAttribute(AttributeName.SETUPPROCSETS, null, true);
        }

        
        /* ---------------------------------------------------------------------
        Methods for Attribute ShrinkToFit
        --------------------------------------------------------------------- */
        /**
          * (36) set attribute ShrinkToFit
          * @param value: the value to set the attribute to
          */
        public void setShrinkToFit(boolean value)
        {
            setAttribute(AttributeName.SHRINKTOFIT, value, null);
        }

        /**
          * (18) get boolean attribute ShrinkToFit
          * @return boolean the value of the attribute
          */
        public boolean getShrinkToFit()
        {
            return getBoolAttribute(AttributeName.SHRINKTOFIT, null, false);
        }

        
        /* ---------------------------------------------------------------------
        Methods for Attribute SuppressCenter
        --------------------------------------------------------------------- */
        /**
          * (36) set attribute SuppressCenter
          * @param value: the value to set the attribute to
          */
        public void setSuppressCenter(boolean value)
        {
            setAttribute(AttributeName.SUPPRESSCENTER, value, null);
        }

        /**
          * (18) get boolean attribute SuppressCenter
          * @return boolean the value of the attribute
          */
        public boolean getSuppressCenter()
        {
            return getBoolAttribute(AttributeName.SUPPRESSCENTER, null, false);
        }

        
        /* ---------------------------------------------------------------------
        Methods for Attribute SuppressRotate
        --------------------------------------------------------------------- */
        /**
          * (36) set attribute SuppressRotate
          * @param value: the value to set the attribute to
          */
        public void setSuppressRotate(boolean value)
        {
            setAttribute(AttributeName.SUPPRESSROTATE, value, null);
        }

        /**
          * (18) get boolean attribute SuppressRotate
          * @return boolean the value of the attribute
          */
        public boolean getSuppressRotate()
        {
            return getBoolAttribute(AttributeName.SUPPRESSROTATE, null, false);
        }

        
        /* ---------------------------------------------------------------------
        Methods for Attribute TTasT42
        --------------------------------------------------------------------- */
        /**
          * (36) set attribute TTasT42
          * @param value: the value to set the attribute to
          */
        public void setTTasT42(boolean value)
        {
            setAttribute(AttributeName.TTAST42, value, null);
        }

        /**
          * (18) get boolean attribute TTasT42
          * @return boolean the value of the attribute
          */
        public boolean getTTasT42()
        {
            return getBoolAttribute(AttributeName.TTAST42, null, false);
        }

        
        /* ---------------------------------------------------------------------
        Methods for Attribute UseFontAliasNames
        --------------------------------------------------------------------- */
        /**
          * (36) set attribute UseFontAliasNames
          * @param value: the value to set the attribute to
          */
        public void setUseFontAliasNames(boolean value)
        {
            setAttribute(AttributeName.USEFONTALIASNAMES, value, null);
        }

        /**
          * (18) get boolean attribute UseFontAliasNames
          * @return boolean the value of the attribute
          */
        public boolean getUseFontAliasNames()
        {
            return getBoolAttribute(AttributeName.USEFONTALIASNAMES, null, false);
        }

        
        /* ---------------------------------------------------------------------
        Methods for Attribute IgnoreDeviceExtGState
        --------------------------------------------------------------------- */
        /**
          * (36) set attribute IgnoreDeviceExtGState
          * @param value: the value to set the attribute to
          */
        public void setIgnoreDeviceExtGState(boolean value)
        {
            setAttribute(AttributeName.IGNOREDEVICEEXTGSTATE, value, null);
        }

        /**
          * (18) get boolean attribute IgnoreDeviceExtGState
          * @return boolean the value of the attribute
          */
        public boolean getIgnoreDeviceExtGState()
        {
            return getBoolAttribute(AttributeName.IGNOREDEVICEEXTGSTATE, null, false);
        }

        
        /* ---------------------------------------------------------------------
        Methods for Attribute BoundingBox
        --------------------------------------------------------------------- */
        /**
          * (36) set attribute BoundingBox
          * @param value: the value to set the attribute to
          */
        public void setBoundingBox(JDFRectangle value)
        {
            setAttribute(AttributeName.BOUNDINGBOX, value, null);
        }

        /**
          * (20) get JDFRectangle attribute BoundingBox
          * @return JDFRectangle the value of the attribute, null if a the
          *         attribute value is not a valid to create a JDFRectangle
          */
        public JDFRectangle getBoundingBox()
        {
            String strAttrName = "";
            JDFRectangle nPlaceHolder = null;
            strAttrName = getAttribute(AttributeName.BOUNDINGBOX, null, JDFConstants.EMPTYSTRING);
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
