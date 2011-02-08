/*
 * The CIP4 Software License, Version 1.0
 *
 *
 * Copyright (c) 2001-2010 The International Cooperation for the Integration of
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
import org.apache.xerces.dom.CoreDocumentImpl;
import org.cip4.jdflib.core.AtrInfoTable;
import org.cip4.jdflib.core.AttributeInfo;
import org.cip4.jdflib.core.AttributeName;
import org.cip4.jdflib.core.ElemInfoTable;
import org.cip4.jdflib.core.ElementInfo;
import org.cip4.jdflib.core.ElementName;
import org.cip4.jdflib.core.JDFConstants;
import org.cip4.jdflib.core.JDFException;
import org.cip4.jdflib.core.VElement;
import org.cip4.jdflib.datatypes.JDFLabColor;
import org.cip4.jdflib.datatypes.JDFXYPair;
import org.cip4.jdflib.resource.JDFColorMeasurementConditions;
import org.cip4.jdflib.resource.JDFResource;
import org.cip4.jdflib.resource.process.JDFColor;
import org.cip4.jdflib.resource.process.JDFContact;
import org.cip4.jdflib.resource.process.JDFIdentificationField;
import org.cip4.jdflib.resource.process.JDFMediaLayers;
import org.cip4.jdflib.resource.process.JDFTabDimensions;
import org.cip4.jdflib.resource.process.postpress.JDFHoleList;
    /**
    *****************************************************************************
    class JDFAutoMedia : public JDFResource

    *****************************************************************************
    */

public abstract class JDFAutoMedia extends JDFResource
{

    private static final long serialVersionUID = 1L;

    private static AtrInfoTable[] atrInfoTable = new AtrInfoTable[48];
    static
    {
        atrInfoTable[0] = new AtrInfoTable(AttributeName.HOLETYPE, 0x33333331, AttributeInfo.EnumAttributeType.enumerations, EnumHoleType.getEnum(0), "None");
        atrInfoTable[1] = new AtrInfoTable(AttributeName.MEDIAUNIT, 0x33333333, AttributeInfo.EnumAttributeType.enumeration, EnumMediaUnit.getEnum(0), "Sheet");
        atrInfoTable[2] = new AtrInfoTable(AttributeName.PREPRINTED, 0x33333333, AttributeInfo.EnumAttributeType.boolean_, null, "false");
        atrInfoTable[3] = new AtrInfoTable(AttributeName.BACKCOATINGDETAIL, 0x33331111, AttributeInfo.EnumAttributeType.NMTOKEN, null, null);
        atrInfoTable[4] = new AtrInfoTable(AttributeName.BACKCOATINGS, 0x33333333, AttributeInfo.EnumAttributeType.enumeration, EnumBackCoatings.getEnum(0), null);
        atrInfoTable[5] = new AtrInfoTable(AttributeName.BACKGLOSSVALUE, 0x33333311, AttributeInfo.EnumAttributeType.double_, null, null);
        atrInfoTable[6] = new AtrInfoTable(AttributeName.BRIGHTNESS, 0x33333333, AttributeInfo.EnumAttributeType.double_, null, null);
        atrInfoTable[7] = new AtrInfoTable(AttributeName.CIETINT, 0x33333311, AttributeInfo.EnumAttributeType.double_, null, null);
        atrInfoTable[8] = new AtrInfoTable(AttributeName.CIEWHITENESS, 0x33333311, AttributeInfo.EnumAttributeType.double_, null, null);
        atrInfoTable[9] = new AtrInfoTable(AttributeName.COLORNAME, 0x44444431, AttributeInfo.EnumAttributeType.string, null, null);
        atrInfoTable[10] = new AtrInfoTable(AttributeName.COREWEIGHT, 0x33333111, AttributeInfo.EnumAttributeType.double_, null, null);
        atrInfoTable[11] = new AtrInfoTable(AttributeName.DIMENSION, 0x33333333, AttributeInfo.EnumAttributeType.XYPair, null, null);
        atrInfoTable[12] = new AtrInfoTable(AttributeName.FLUTE, 0x33333111, AttributeInfo.EnumAttributeType.NMTOKEN, null, null);
        atrInfoTable[13] = new AtrInfoTable(AttributeName.FLUTEDIRECTION, 0x33333111, AttributeInfo.EnumAttributeType.enumeration, EnumFluteDirection.getEnum(0), null);
        atrInfoTable[14] = new AtrInfoTable(AttributeName.FRONTCOATINGDETAIL, 0x33331111, AttributeInfo.EnumAttributeType.NMTOKEN, null, null);
        atrInfoTable[15] = new AtrInfoTable(AttributeName.FRONTCOATINGS, 0x33333333, AttributeInfo.EnumAttributeType.enumeration, EnumFrontCoatings.getEnum(0), null);
        atrInfoTable[16] = new AtrInfoTable(AttributeName.FRONTGLOSSVALUE, 0x33333311, AttributeInfo.EnumAttributeType.double_, null, null);
        atrInfoTable[17] = new AtrInfoTable(AttributeName.GRADE, 0x33333333, AttributeInfo.EnumAttributeType.integer, null, null);
        atrInfoTable[18] = new AtrInfoTable(AttributeName.GRAINDIRECTION, 0x33333331, AttributeInfo.EnumAttributeType.enumeration, EnumGrainDirection.getEnum(0), null);
        atrInfoTable[19] = new AtrInfoTable(AttributeName.HOLECOUNT, 0x44444443, AttributeInfo.EnumAttributeType.integer, null, null);
        atrInfoTable[20] = new AtrInfoTable(AttributeName.IMAGABLESIDE, 0x33333333, AttributeInfo.EnumAttributeType.enumeration, EnumImagableSide.getEnum(0), null);
        atrInfoTable[21] = new AtrInfoTable(AttributeName.INSIDELOSS, 0x33333111, AttributeInfo.EnumAttributeType.double_, null, null);
        atrInfoTable[22] = new AtrInfoTable(AttributeName.LABCOLORVALUE, 0x33333311, AttributeInfo.EnumAttributeType.LabColor, null, null);
        atrInfoTable[23] = new AtrInfoTable(AttributeName.MEDIACOLORNAME, 0x33333333, AttributeInfo.EnumAttributeType.string, null, null);
        atrInfoTable[24] = new AtrInfoTable(AttributeName.MEDIACOLORNAMEDETAILS, 0x33333311, AttributeInfo.EnumAttributeType.string, null, null);
        atrInfoTable[25] = new AtrInfoTable(AttributeName.MEDIAQUALITY, 0x33331111, AttributeInfo.EnumAttributeType.string, null, null);
        atrInfoTable[26] = new AtrInfoTable(AttributeName.MEDIASETCOUNT, 0x33333333, AttributeInfo.EnumAttributeType.integer, null, null);
        atrInfoTable[27] = new AtrInfoTable(AttributeName.MEDIATYPE, 0x33333333, AttributeInfo.EnumAttributeType.enumeration, EnumMediaType.getEnum(0), null);
        atrInfoTable[28] = new AtrInfoTable(AttributeName.MEDIATYPEDETAILS, 0x33333333, AttributeInfo.EnumAttributeType.NMTOKEN, null, null);
        atrInfoTable[29] = new AtrInfoTable(AttributeName.OPACITY, 0x33333333, AttributeInfo.EnumAttributeType.enumeration, EnumOpacity.getEnum(0), null);
        atrInfoTable[30] = new AtrInfoTable(AttributeName.OPACITYLEVEL, 0x33333311, AttributeInfo.EnumAttributeType.double_, null, null);
        atrInfoTable[31] = new AtrInfoTable(AttributeName.OUTERCOREDIAMETER, 0x33333111, AttributeInfo.EnumAttributeType.double_, null, null);
        atrInfoTable[32] = new AtrInfoTable(AttributeName.OUTSIDEGAIN, 0x33333111, AttributeInfo.EnumAttributeType.double_, null, null);
        atrInfoTable[33] = new AtrInfoTable(AttributeName.PLATETECHNOLOGY, 0x33333111, AttributeInfo.EnumAttributeType.enumeration, EnumPlateTechnology.getEnum(0), null);
        atrInfoTable[34] = new AtrInfoTable(AttributeName.POLARITY, 0x33333333, AttributeInfo.EnumAttributeType.enumeration, EnumPolarity.getEnum(0), null);
        atrInfoTable[35] = new AtrInfoTable(AttributeName.PRINTINGTECHNOLOGY, 0x33331111, AttributeInfo.EnumAttributeType.NMTOKEN, null, null);
        atrInfoTable[36] = new AtrInfoTable(AttributeName.RECYCLED, 0x44444433, AttributeInfo.EnumAttributeType.boolean_, null, null);
        atrInfoTable[37] = new AtrInfoTable(AttributeName.RECYCLEDPERCENTAGE, 0x33333311, AttributeInfo.EnumAttributeType.double_, null, null);
        atrInfoTable[38] = new AtrInfoTable(AttributeName.RELIEFTHICKNESS, 0x33331111, AttributeInfo.EnumAttributeType.double_, null, null);
        atrInfoTable[39] = new AtrInfoTable(AttributeName.ROLLDIAMETER, 0x33333333, AttributeInfo.EnumAttributeType.double_, null, null);
        atrInfoTable[40] = new AtrInfoTable(AttributeName.SHRINKINDEX, 0x33333331, AttributeInfo.EnumAttributeType.XYPair, null, null);
        atrInfoTable[41] = new AtrInfoTable(AttributeName.SLEEVEINTERLOCK, 0x33331111, AttributeInfo.EnumAttributeType.NMTOKEN, null, null);
        atrInfoTable[42] = new AtrInfoTable(AttributeName.STOCKTYPE, 0x33333331, AttributeInfo.EnumAttributeType.NMTOKEN, null, null);
        atrInfoTable[43] = new AtrInfoTable(AttributeName.TEXTURE, 0x33333331, AttributeInfo.EnumAttributeType.NMTOKEN, null, null);
        atrInfoTable[44] = new AtrInfoTable(AttributeName.THICKNESS, 0x33333333, AttributeInfo.EnumAttributeType.double_, null, null);
        atrInfoTable[45] = new AtrInfoTable(AttributeName.USERMEDIATYPE, 0x44444443, AttributeInfo.EnumAttributeType.NMTOKEN, null, null);
        atrInfoTable[46] = new AtrInfoTable(AttributeName.WEIGHT, 0x33333333, AttributeInfo.EnumAttributeType.double_, null, null);
        atrInfoTable[47] = new AtrInfoTable(AttributeName.WRAPPERWEIGHT, 0x33333111, AttributeInfo.EnumAttributeType.double_, null, null);
    }
    
    protected AttributeInfo getTheAttributeInfo()
    {
        return super.getTheAttributeInfo().updateReplace(atrInfoTable);
    }


    private static ElemInfoTable[] elemInfoTable = new ElemInfoTable[7];
    static
    {
        elemInfoTable[0] = new ElemInfoTable(ElementName.COLOR, 0x77777776);
        elemInfoTable[1] = new ElemInfoTable(ElementName.COLORMEASUREMENTCONDITIONS, 0x66666611);
        elemInfoTable[2] = new ElemInfoTable(ElementName.MEDIALAYERS, 0x66666111);
        elemInfoTable[3] = new ElemInfoTable(ElementName.HOLELIST, 0x66666611);
        elemInfoTable[4] = new ElemInfoTable(ElementName.TABDIMENSIONS, 0x33333333);
        elemInfoTable[5] = new ElemInfoTable(ElementName.CONTACT, 0x33333333);
        elemInfoTable[6] = new ElemInfoTable(ElementName.IDENTIFICATIONFIELD, 0x33333333);
    }
    
    protected ElementInfo getTheElementInfo()
    {
        return super.getTheElementInfo().updateReplace(elemInfoTable);
    }



    /**
     * Constructor for JDFAutoMedia
     * @param myOwnerDocument
     * @param qualifiedName
     */
    protected JDFAutoMedia(
        CoreDocumentImpl myOwnerDocument,
        String qualifiedName)
    {
        super(myOwnerDocument, qualifiedName);
    }

    /**
     * Constructor for JDFAutoMedia
     * @param myOwnerDocument
     * @param myNamespaceURI
     * @param qualifiedName
     */
    protected JDFAutoMedia(
        CoreDocumentImpl myOwnerDocument,
        String myNamespaceURI,
        String qualifiedName)
    {
        super(myOwnerDocument, myNamespaceURI, qualifiedName);
    }

    /**
     * Constructor for JDFAutoMedia
     * @param myOwnerDocument
     * @param myNamespaceURI
     * @param qualifiedName
     * @param myLocalName
     */
    protected JDFAutoMedia(
        CoreDocumentImpl myOwnerDocument,
        String myNamespaceURI,
        String qualifiedName,
        String myLocalName)
    {
        super(myOwnerDocument, myNamespaceURI, qualifiedName, myLocalName);
    }


    public String toString()
    {
        return " JDFAutoMedia[  --> " + super.toString() + " ]";
    }


    public boolean  init()
    {
        boolean bRet = super.init();
        setResourceClass(JDFResource.EnumResourceClass.Consumable);
        return bRet;
    }


    public EnumResourceClass getValidClass()
    {
        return JDFResource.EnumResourceClass.Consumable;
    }


        /**
        * Enumeration strings for HoleType
        */

        public static class EnumHoleType extends ValuedEnum
        {
            private static final long serialVersionUID = 1L;
            private static int m_startValue = 0;

            private EnumHoleType(String name)
            {
                super(name, m_startValue++);
            }

            public static EnumHoleType getEnum(String enumName)
            {
                return (EnumHoleType) getEnum(EnumHoleType.class, enumName);
            }

            public static EnumHoleType getEnum(int enumValue)
            {
                return (EnumHoleType) getEnum(EnumHoleType.class, enumValue);
            }

            public static Map getEnumMap()
            {
                return getEnumMap(EnumHoleType.class);
            }

            public static List getEnumList()
            {
                return getEnumList(EnumHoleType.class);
            }

            public static Iterator iterator()
            {
                return iterator(EnumHoleType.class);
            }

            public static final EnumHoleType None = new EnumHoleType("None");
            public static final EnumHoleType S1_generic = new EnumHoleType("S1-generic");
            public static final EnumHoleType S_generic = new EnumHoleType("S-generic");
            public static final EnumHoleType R2_generic = new EnumHoleType("R2-generic");
            public static final EnumHoleType R2m_DIN = new EnumHoleType("R2m-DIN");
            public static final EnumHoleType R2m_ISO = new EnumHoleType("R2m-ISO");
            public static final EnumHoleType R2m_MIB = new EnumHoleType("R2m-MIB");
            public static final EnumHoleType R2i_US_a = new EnumHoleType("R2i-US-a");
            public static final EnumHoleType R2i_US_b = new EnumHoleType("R2i-US-b");
            public static final EnumHoleType R3_generic = new EnumHoleType("R3-generic");
            public static final EnumHoleType R3i_US = new EnumHoleType("R3i-US");
            public static final EnumHoleType R4_generic = new EnumHoleType("R4-generic");
            public static final EnumHoleType R4m_DIN_A4 = new EnumHoleType("R4m-DIN-A4");
            public static final EnumHoleType R4m_DIN_A5 = new EnumHoleType("R4m-DIN-A5");
            public static final EnumHoleType R4m_swedish = new EnumHoleType("R4m-swedish");
            public static final EnumHoleType R4i_US = new EnumHoleType("R4i-US");
            public static final EnumHoleType R5_generic = new EnumHoleType("R5-generic");
            public static final EnumHoleType R5i_US_a = new EnumHoleType("R5i-US-a");
            public static final EnumHoleType R5i_US_b = new EnumHoleType("R5i-US-b");
            public static final EnumHoleType R5i_US_c = new EnumHoleType("R5i-US-c");
            public static final EnumHoleType R6_generic = new EnumHoleType("R6-generic");
            public static final EnumHoleType R6m_4h2s = new EnumHoleType("R6m-4h2s");
            public static final EnumHoleType R6m_DIN_A5 = new EnumHoleType("R6m-DIN-A5");
            public static final EnumHoleType R7_generic = new EnumHoleType("R7-generic");
            public static final EnumHoleType R7i_US_a = new EnumHoleType("R7i-US-a");
            public static final EnumHoleType R7i_US_b = new EnumHoleType("R7i-US-b");
            public static final EnumHoleType R7i_US_c = new EnumHoleType("R7i-US-c");
            public static final EnumHoleType R11m_7h4s = new EnumHoleType("R11m-7h4s");
            public static final EnumHoleType P16_9i_rect_0t = new EnumHoleType("P16_9i-rect-0t");
            public static final EnumHoleType P12m_rect_0t = new EnumHoleType("P12m-rect-0t");
            public static final EnumHoleType W2_1i_round_0t = new EnumHoleType("W2_1i-round-0t");
            public static final EnumHoleType W2_1i_square_0t = new EnumHoleType("W2_1i-square-0t");
            public static final EnumHoleType W3_1i_square_0t = new EnumHoleType("W3_1i-square-0t");
            public static final EnumHoleType C9_5m_round_0t = new EnumHoleType("C9.5m-round-0t");
            public static final EnumHoleType Explicit = new EnumHoleType("Explicit");
        }      



        /**
        * Enumeration strings for MediaUnit
        */

        public static class EnumMediaUnit extends ValuedEnum
        {
            private static final long serialVersionUID = 1L;
            private static int m_startValue = 0;

            private EnumMediaUnit(String name)
            {
                super(name, m_startValue++);
            }

            public static EnumMediaUnit getEnum(String enumName)
            {
                return (EnumMediaUnit) getEnum(EnumMediaUnit.class, enumName);
            }

            public static EnumMediaUnit getEnum(int enumValue)
            {
                return (EnumMediaUnit) getEnum(EnumMediaUnit.class, enumValue);
            }

            public static Map getEnumMap()
            {
                return getEnumMap(EnumMediaUnit.class);
            }

            public static List getEnumList()
            {
                return getEnumList(EnumMediaUnit.class);
            }

            public static Iterator iterator()
            {
                return iterator(EnumMediaUnit.class);
            }

            public static final EnumMediaUnit Continuous = new EnumMediaUnit("Continuous");
            public static final EnumMediaUnit Roll = new EnumMediaUnit("Roll");
            public static final EnumMediaUnit Sheet = new EnumMediaUnit("Sheet");
        }      



        /**
        * Enumeration strings for BackCoatings
        */

        public static class EnumBackCoatings extends ValuedEnum
        {
            private static final long serialVersionUID = 1L;
            private static int m_startValue = 0;

            private EnumBackCoatings(String name)
            {
                super(name, m_startValue++);
            }

            public static EnumBackCoatings getEnum(String enumName)
            {
                return (EnumBackCoatings) getEnum(EnumBackCoatings.class, enumName);
            }

            public static EnumBackCoatings getEnum(int enumValue)
            {
                return (EnumBackCoatings) getEnum(EnumBackCoatings.class, enumValue);
            }

            public static Map getEnumMap()
            {
                return getEnumMap(EnumBackCoatings.class);
            }

            public static List getEnumList()
            {
                return getEnumList(EnumBackCoatings.class);
            }

            public static Iterator iterator()
            {
                return iterator(EnumBackCoatings.class);
            }

            public static final EnumBackCoatings None = new EnumBackCoatings("None");
            public static final EnumBackCoatings Coated = new EnumBackCoatings("Coated");
            public static final EnumBackCoatings Glossy = new EnumBackCoatings("Glossy");
            public static final EnumBackCoatings HighGloss = new EnumBackCoatings("HighGloss");
            public static final EnumBackCoatings InkJet = new EnumBackCoatings("InkJet");
            public static final EnumBackCoatings Matte = new EnumBackCoatings("Matte");
            public static final EnumBackCoatings Polymer = new EnumBackCoatings("Polymer");
            public static final EnumBackCoatings Silver = new EnumBackCoatings("Silver");
            public static final EnumBackCoatings Satin = new EnumBackCoatings("Satin");
            public static final EnumBackCoatings Semigloss = new EnumBackCoatings("Semigloss");
        }      



        /**
        * Enumeration strings for FluteDirection
        */

        public static class EnumFluteDirection extends ValuedEnum
        {
            private static final long serialVersionUID = 1L;
            private static int m_startValue = 0;

            private EnumFluteDirection(String name)
            {
                super(name, m_startValue++);
            }

            public static EnumFluteDirection getEnum(String enumName)
            {
                return (EnumFluteDirection) getEnum(EnumFluteDirection.class, enumName);
            }

            public static EnumFluteDirection getEnum(int enumValue)
            {
                return (EnumFluteDirection) getEnum(EnumFluteDirection.class, enumValue);
            }

            public static Map getEnumMap()
            {
                return getEnumMap(EnumFluteDirection.class);
            }

            public static List getEnumList()
            {
                return getEnumList(EnumFluteDirection.class);
            }

            public static Iterator iterator()
            {
                return iterator(EnumFluteDirection.class);
            }

            public static final EnumFluteDirection LongEdge = new EnumFluteDirection("LongEdge");
            public static final EnumFluteDirection ShortEdge = new EnumFluteDirection("ShortEdge");
            public static final EnumFluteDirection XDirection = new EnumFluteDirection("XDirection");
            public static final EnumFluteDirection YDirection = new EnumFluteDirection("YDirection");
        }      



        /**
        * Enumeration strings for FrontCoatings
        */

        public static class EnumFrontCoatings extends ValuedEnum
        {
            private static final long serialVersionUID = 1L;
            private static int m_startValue = 0;

            private EnumFrontCoatings(String name)
            {
                super(name, m_startValue++);
            }

            public static EnumFrontCoatings getEnum(String enumName)
            {
                return (EnumFrontCoatings) getEnum(EnumFrontCoatings.class, enumName);
            }

            public static EnumFrontCoatings getEnum(int enumValue)
            {
                return (EnumFrontCoatings) getEnum(EnumFrontCoatings.class, enumValue);
            }

            public static Map getEnumMap()
            {
                return getEnumMap(EnumFrontCoatings.class);
            }

            public static List getEnumList()
            {
                return getEnumList(EnumFrontCoatings.class);
            }

            public static Iterator iterator()
            {
                return iterator(EnumFrontCoatings.class);
            }

            public static final EnumFrontCoatings None = new EnumFrontCoatings("None");
            public static final EnumFrontCoatings Coated = new EnumFrontCoatings("Coated");
            public static final EnumFrontCoatings Glossy = new EnumFrontCoatings("Glossy");
            public static final EnumFrontCoatings HighGloss = new EnumFrontCoatings("HighGloss");
            public static final EnumFrontCoatings InkJet = new EnumFrontCoatings("InkJet");
            public static final EnumFrontCoatings Matte = new EnumFrontCoatings("Matte");
            public static final EnumFrontCoatings Polymer = new EnumFrontCoatings("Polymer");
            public static final EnumFrontCoatings Silver = new EnumFrontCoatings("Silver");
            public static final EnumFrontCoatings Satin = new EnumFrontCoatings("Satin");
            public static final EnumFrontCoatings Semigloss = new EnumFrontCoatings("Semigloss");
        }      



        /**
        * Enumeration strings for GrainDirection
        */

        public static class EnumGrainDirection extends ValuedEnum
        {
            private static final long serialVersionUID = 1L;
            private static int m_startValue = 0;

            private EnumGrainDirection(String name)
            {
                super(name, m_startValue++);
            }

            public static EnumGrainDirection getEnum(String enumName)
            {
                return (EnumGrainDirection) getEnum(EnumGrainDirection.class, enumName);
            }

            public static EnumGrainDirection getEnum(int enumValue)
            {
                return (EnumGrainDirection) getEnum(EnumGrainDirection.class, enumValue);
            }

            public static Map getEnumMap()
            {
                return getEnumMap(EnumGrainDirection.class);
            }

            public static List getEnumList()
            {
                return getEnumList(EnumGrainDirection.class);
            }

            public static Iterator iterator()
            {
                return iterator(EnumGrainDirection.class);
            }

            public static final EnumGrainDirection LongEdge = new EnumGrainDirection("LongEdge");
            public static final EnumGrainDirection ShortEdge = new EnumGrainDirection("ShortEdge");
            public static final EnumGrainDirection XDirection = new EnumGrainDirection("XDirection");
            public static final EnumGrainDirection YDirection = new EnumGrainDirection("YDirection");
        }      



        /**
        * Enumeration strings for ImagableSide
        */

        public static class EnumImagableSide extends ValuedEnum
        {
            private static final long serialVersionUID = 1L;
            private static int m_startValue = 0;

            private EnumImagableSide(String name)
            {
                super(name, m_startValue++);
            }

            public static EnumImagableSide getEnum(String enumName)
            {
                return (EnumImagableSide) getEnum(EnumImagableSide.class, enumName);
            }

            public static EnumImagableSide getEnum(int enumValue)
            {
                return (EnumImagableSide) getEnum(EnumImagableSide.class, enumValue);
            }

            public static Map getEnumMap()
            {
                return getEnumMap(EnumImagableSide.class);
            }

            public static List getEnumList()
            {
                return getEnumList(EnumImagableSide.class);
            }

            public static Iterator iterator()
            {
                return iterator(EnumImagableSide.class);
            }

            public static final EnumImagableSide Front = new EnumImagableSide("Front");
            public static final EnumImagableSide Back = new EnumImagableSide("Back");
            public static final EnumImagableSide Both = new EnumImagableSide("Both");
            public static final EnumImagableSide Neither = new EnumImagableSide("Neither");
        }      



        /**
        * Enumeration strings for MediaType
        */

        public static class EnumMediaType extends ValuedEnum
        {
            private static final long serialVersionUID = 1L;
            private static int m_startValue = 0;

            private EnumMediaType(String name)
            {
                super(name, m_startValue++);
            }

            public static EnumMediaType getEnum(String enumName)
            {
                return (EnumMediaType) getEnum(EnumMediaType.class, enumName);
            }

            public static EnumMediaType getEnum(int enumValue)
            {
                return (EnumMediaType) getEnum(EnumMediaType.class, enumValue);
            }

            public static Map getEnumMap()
            {
                return getEnumMap(EnumMediaType.class);
            }

            public static List getEnumList()
            {
                return getEnumList(EnumMediaType.class);
            }

            public static Iterator iterator()
            {
                return iterator(EnumMediaType.class);
            }

            public static final EnumMediaType CorrugatedBoard = new EnumMediaType("CorrugatedBoard");
            public static final EnumMediaType Disc = new EnumMediaType("Disc");
            public static final EnumMediaType EndBoard = new EnumMediaType("EndBoard");
            public static final EnumMediaType EmbossingFoil = new EnumMediaType("EmbossingFoil");
            public static final EnumMediaType Film = new EnumMediaType("Film");
            public static final EnumMediaType Foil = new EnumMediaType("Foil");
            public static final EnumMediaType GravureCylinder = new EnumMediaType("GravureCylinder");
            public static final EnumMediaType ImagingCylinder = new EnumMediaType("ImagingCylinder");
            public static final EnumMediaType LaminatingFoil = new EnumMediaType("LaminatingFoil");
            public static final EnumMediaType MountingTape = new EnumMediaType("MountingTape");
            public static final EnumMediaType Other = new EnumMediaType("Other");
            public static final EnumMediaType Paper = new EnumMediaType("Paper");
            public static final EnumMediaType Plate = new EnumMediaType("Plate");
            public static final EnumMediaType SelfAdhesive = new EnumMediaType("SelfAdhesive");
            public static final EnumMediaType Sleeve = new EnumMediaType("Sleeve");
            public static final EnumMediaType ShrinkFoil = new EnumMediaType("ShrinkFoil");
            public static final EnumMediaType Transparency = new EnumMediaType("Transparency");
            public static final EnumMediaType Unknown = new EnumMediaType("Unknown");
        }      



        /**
        * Enumeration strings for Opacity
        */

        public static class EnumOpacity extends ValuedEnum
        {
            private static final long serialVersionUID = 1L;
            private static int m_startValue = 0;

            private EnumOpacity(String name)
            {
                super(name, m_startValue++);
            }

            public static EnumOpacity getEnum(String enumName)
            {
                return (EnumOpacity) getEnum(EnumOpacity.class, enumName);
            }

            public static EnumOpacity getEnum(int enumValue)
            {
                return (EnumOpacity) getEnum(EnumOpacity.class, enumValue);
            }

            public static Map getEnumMap()
            {
                return getEnumMap(EnumOpacity.class);
            }

            public static List getEnumList()
            {
                return getEnumList(EnumOpacity.class);
            }

            public static Iterator iterator()
            {
                return iterator(EnumOpacity.class);
            }

            public static final EnumOpacity Opaque = new EnumOpacity("Opaque");
            public static final EnumOpacity Translucent = new EnumOpacity("Translucent");
            public static final EnumOpacity Transparent = new EnumOpacity("Transparent");
        }      



        /**
        * Enumeration strings for PlateTechnology
        */

        public static class EnumPlateTechnology extends ValuedEnum
        {
            private static final long serialVersionUID = 1L;
            private static int m_startValue = 0;

            private EnumPlateTechnology(String name)
            {
                super(name, m_startValue++);
            }

            public static EnumPlateTechnology getEnum(String enumName)
            {
                return (EnumPlateTechnology) getEnum(EnumPlateTechnology.class, enumName);
            }

            public static EnumPlateTechnology getEnum(int enumValue)
            {
                return (EnumPlateTechnology) getEnum(EnumPlateTechnology.class, enumValue);
            }

            public static Map getEnumMap()
            {
                return getEnumMap(EnumPlateTechnology.class);
            }

            public static List getEnumList()
            {
                return getEnumList(EnumPlateTechnology.class);
            }

            public static Iterator iterator()
            {
                return iterator(EnumPlateTechnology.class);
            }

            public static final EnumPlateTechnology FlexoAnalogSolvent = new EnumPlateTechnology("FlexoAnalogSolvent");
            public static final EnumPlateTechnology FlexoAnalogThermal = new EnumPlateTechnology("FlexoAnalogThermal");
            public static final EnumPlateTechnology FlexoDigitalSolvent = new EnumPlateTechnology("FlexoDigitalSolvent");
            public static final EnumPlateTechnology FlexoDigitalThermal = new EnumPlateTechnology("FlexoDigitalThermal");
            public static final EnumPlateTechnology FlexoDirectEngraving = new EnumPlateTechnology("FlexoDirectEngraving");
            public static final EnumPlateTechnology InkJet = new EnumPlateTechnology("InkJet");
            public static final EnumPlateTechnology Thermal = new EnumPlateTechnology("Thermal");
            public static final EnumPlateTechnology UV = new EnumPlateTechnology("UV");
            public static final EnumPlateTechnology Visible = new EnumPlateTechnology("Visible");
        }      



        /**
        * Enumeration strings for Polarity
        */

        public static class EnumPolarity extends ValuedEnum
        {
            private static final long serialVersionUID = 1L;
            private static int m_startValue = 0;

            private EnumPolarity(String name)
            {
                super(name, m_startValue++);
            }

            public static EnumPolarity getEnum(String enumName)
            {
                return (EnumPolarity) getEnum(EnumPolarity.class, enumName);
            }

            public static EnumPolarity getEnum(int enumValue)
            {
                return (EnumPolarity) getEnum(EnumPolarity.class, enumValue);
            }

            public static Map getEnumMap()
            {
                return getEnumMap(EnumPolarity.class);
            }

            public static List getEnumList()
            {
                return getEnumList(EnumPolarity.class);
            }

            public static Iterator iterator()
            {
                return iterator(EnumPolarity.class);
            }

            public static final EnumPolarity Positive = new EnumPolarity("Positive");
            public static final EnumPolarity Negative = new EnumPolarity("Negative");
        }      



/* ************************************************************************
 * Attribute getter / setter
 * ************************************************************************
 */
        
        /* ---------------------------------------------------------------------
        Methods for Attribute HoleType
        --------------------------------------------------------------------- */
        /**
          * (5.2) set attribute HoleType
          * @param v vector of the enumeration values
          */
        public void setHoleType(Vector v)
        {
            setEnumerationsAttribute(AttributeName.HOLETYPE, v, null);
        }

        /**
          * (9.2) get HoleType attribute HoleType
          * @return Vector of the enumerations
          */
        public Vector getHoleType()
        {
            return getEnumerationsAttribute(AttributeName.HOLETYPE, null, EnumHoleType.None, false);
        }

        
        /* ---------------------------------------------------------------------
        Methods for Attribute MediaUnit
        --------------------------------------------------------------------- */
        /**
          * (5) set attribute MediaUnit
          * @param enumVar: the enumVar to set the attribute to
          */
        public void setMediaUnit(EnumMediaUnit enumVar)
        {
            setAttribute(AttributeName.MEDIAUNIT, enumVar==null ? null : enumVar.getName(), null);
        }

        /**
          * (9) get attribute MediaUnit
          * @return the value of the attribute
          */
        public EnumMediaUnit getMediaUnit()
        {
            return EnumMediaUnit.getEnum(getAttribute(AttributeName.MEDIAUNIT, null, "Sheet"));
        }

        
        /* ---------------------------------------------------------------------
        Methods for Attribute PrePrinted
        --------------------------------------------------------------------- */
        /**
          * (36) set attribute PrePrinted
          * @param value: the value to set the attribute to
          */
        public void setPrePrinted(boolean value)
        {
            setAttribute(AttributeName.PREPRINTED, value, null);
        }

        /**
          * (18) get boolean attribute PrePrinted
          * @return boolean the value of the attribute
          */
        public boolean getPrePrinted()
        {
            return getBoolAttribute(AttributeName.PREPRINTED, null, false);
        }

        
        /* ---------------------------------------------------------------------
        Methods for Attribute BackCoatingDetail
        --------------------------------------------------------------------- */
        /**
          * (36) set attribute BackCoatingDetail
          * @param value: the value to set the attribute to
          */
        public void setBackCoatingDetail(String value)
        {
            setAttribute(AttributeName.BACKCOATINGDETAIL, value, null);
        }

        /**
          * (23) get String attribute BackCoatingDetail
          * @return the value of the attribute
          */
        public String getBackCoatingDetail()
        {
            return getAttribute(AttributeName.BACKCOATINGDETAIL, null, JDFConstants.EMPTYSTRING);
        }

        
        /* ---------------------------------------------------------------------
        Methods for Attribute BackCoatings
        --------------------------------------------------------------------- */
        /**
          * (5) set attribute BackCoatings
          * @param enumVar: the enumVar to set the attribute to
          */
        public void setBackCoatings(EnumBackCoatings enumVar)
        {
            setAttribute(AttributeName.BACKCOATINGS, enumVar==null ? null : enumVar.getName(), null);
        }

        /**
          * (9) get attribute BackCoatings
          * @return the value of the attribute
          */
        public EnumBackCoatings getBackCoatings()
        {
            return EnumBackCoatings.getEnum(getAttribute(AttributeName.BACKCOATINGS, null, null));
        }

        
        /* ---------------------------------------------------------------------
        Methods for Attribute BackGlossValue
        --------------------------------------------------------------------- */
        /**
          * (36) set attribute BackGlossValue
          * @param value: the value to set the attribute to
          */
        public void setBackGlossValue(double value)
        {
            setAttribute(AttributeName.BACKGLOSSVALUE, value, null);
        }

        /**
          * (17) get double attribute BackGlossValue
          * @return double the value of the attribute
          */
        public double getBackGlossValue()
        {
            return getRealAttribute(AttributeName.BACKGLOSSVALUE, null, 0.0);
        }

        
        /* ---------------------------------------------------------------------
        Methods for Attribute Brightness
        --------------------------------------------------------------------- */
        /**
          * (36) set attribute Brightness
          * @param value: the value to set the attribute to
          */
        public void setBrightness(double value)
        {
            setAttribute(AttributeName.BRIGHTNESS, value, null);
        }

        /**
          * (17) get double attribute Brightness
          * @return double the value of the attribute
          */
        public double getBrightness()
        {
            return getRealAttribute(AttributeName.BRIGHTNESS, null, 0.0);
        }

        
        /* ---------------------------------------------------------------------
        Methods for Attribute CIETint
        --------------------------------------------------------------------- */
        /**
          * (36) set attribute CIETint
          * @param value: the value to set the attribute to
          */
        public void setCIETint(double value)
        {
            setAttribute(AttributeName.CIETINT, value, null);
        }

        /**
          * (17) get double attribute CIETint
          * @return double the value of the attribute
          */
        public double getCIETint()
        {
            return getRealAttribute(AttributeName.CIETINT, null, 0.0);
        }

        
        /* ---------------------------------------------------------------------
        Methods for Attribute CIEWhiteness
        --------------------------------------------------------------------- */
        /**
          * (36) set attribute CIEWhiteness
          * @param value: the value to set the attribute to
          */
        public void setCIEWhiteness(double value)
        {
            setAttribute(AttributeName.CIEWHITENESS, value, null);
        }

        /**
          * (17) get double attribute CIEWhiteness
          * @return double the value of the attribute
          */
        public double getCIEWhiteness()
        {
            return getRealAttribute(AttributeName.CIEWHITENESS, null, 0.0);
        }

        
        /* ---------------------------------------------------------------------
        Methods for Attribute ColorName
        --------------------------------------------------------------------- */
        /**
          * (36) set attribute ColorName
          * @param value: the value to set the attribute to
          */
        public void setColorName(String value)
        {
            setAttribute(AttributeName.COLORNAME, value, null);
        }

        /**
          * (23) get String attribute ColorName
          * @return the value of the attribute
          */
        public String getColorName()
        {
            return getAttribute(AttributeName.COLORNAME, null, JDFConstants.EMPTYSTRING);
        }

        
        /* ---------------------------------------------------------------------
        Methods for Attribute CoreWeight
        --------------------------------------------------------------------- */
        /**
          * (36) set attribute CoreWeight
          * @param value: the value to set the attribute to
          */
        public void setCoreWeight(double value)
        {
            setAttribute(AttributeName.COREWEIGHT, value, null);
        }

        /**
          * (17) get double attribute CoreWeight
          * @return double the value of the attribute
          */
        public double getCoreWeight()
        {
            return getRealAttribute(AttributeName.COREWEIGHT, null, 0.0);
        }

        
        /* ---------------------------------------------------------------------
        Methods for Attribute Dimension
        --------------------------------------------------------------------- */
        /**
          * (36) set attribute Dimension
          * @param value: the value to set the attribute to
          */
        public void setDimension(JDFXYPair value)
        {
            setAttribute(AttributeName.DIMENSION, value, null);
        }

        /**
          * (20) get JDFXYPair attribute Dimension
          * @return JDFXYPair the value of the attribute, null if a the
          *         attribute value is not a valid to create a JDFXYPair
          */
        public JDFXYPair getDimension()
        {
            String strAttrName = "";
            JDFXYPair nPlaceHolder = null;
            strAttrName = getAttribute(AttributeName.DIMENSION, null, JDFConstants.EMPTYSTRING);
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
        Methods for Attribute Flute
        --------------------------------------------------------------------- */
        /**
          * (36) set attribute Flute
          * @param value: the value to set the attribute to
          */
        public void setFlute(String value)
        {
            setAttribute(AttributeName.FLUTE, value, null);
        }

        /**
          * (23) get String attribute Flute
          * @return the value of the attribute
          */
        public String getFlute()
        {
            return getAttribute(AttributeName.FLUTE, null, JDFConstants.EMPTYSTRING);
        }

        
        /* ---------------------------------------------------------------------
        Methods for Attribute FluteDirection
        --------------------------------------------------------------------- */
        /**
          * (5) set attribute FluteDirection
          * @param enumVar: the enumVar to set the attribute to
          */
        public void setFluteDirection(EnumFluteDirection enumVar)
        {
            setAttribute(AttributeName.FLUTEDIRECTION, enumVar==null ? null : enumVar.getName(), null);
        }

        /**
          * (9) get attribute FluteDirection
          * @return the value of the attribute
          */
        public EnumFluteDirection getFluteDirection()
        {
            return EnumFluteDirection.getEnum(getAttribute(AttributeName.FLUTEDIRECTION, null, null));
        }

        
        /* ---------------------------------------------------------------------
        Methods for Attribute FrontCoatingDetail
        --------------------------------------------------------------------- */
        /**
          * (36) set attribute FrontCoatingDetail
          * @param value: the value to set the attribute to
          */
        public void setFrontCoatingDetail(String value)
        {
            setAttribute(AttributeName.FRONTCOATINGDETAIL, value, null);
        }

        /**
          * (23) get String attribute FrontCoatingDetail
          * @return the value of the attribute
          */
        public String getFrontCoatingDetail()
        {
            return getAttribute(AttributeName.FRONTCOATINGDETAIL, null, JDFConstants.EMPTYSTRING);
        }

        
        /* ---------------------------------------------------------------------
        Methods for Attribute FrontCoatings
        --------------------------------------------------------------------- */
        /**
          * (5) set attribute FrontCoatings
          * @param enumVar: the enumVar to set the attribute to
          */
        public void setFrontCoatings(EnumFrontCoatings enumVar)
        {
            setAttribute(AttributeName.FRONTCOATINGS, enumVar==null ? null : enumVar.getName(), null);
        }

        /**
          * (9) get attribute FrontCoatings
          * @return the value of the attribute
          */
        public EnumFrontCoatings getFrontCoatings()
        {
            return EnumFrontCoatings.getEnum(getAttribute(AttributeName.FRONTCOATINGS, null, null));
        }

        
        /* ---------------------------------------------------------------------
        Methods for Attribute FrontGlossValue
        --------------------------------------------------------------------- */
        /**
          * (36) set attribute FrontGlossValue
          * @param value: the value to set the attribute to
          */
        public void setFrontGlossValue(double value)
        {
            setAttribute(AttributeName.FRONTGLOSSVALUE, value, null);
        }

        /**
          * (17) get double attribute FrontGlossValue
          * @return double the value of the attribute
          */
        public double getFrontGlossValue()
        {
            return getRealAttribute(AttributeName.FRONTGLOSSVALUE, null, 0.0);
        }

        
        /* ---------------------------------------------------------------------
        Methods for Attribute Grade
        --------------------------------------------------------------------- */
        /**
          * (36) set attribute Grade
          * @param value: the value to set the attribute to
          */
        public void setGrade(int value)
        {
            setAttribute(AttributeName.GRADE, value, null);
        }

        /**
          * (15) get int attribute Grade
          * @return int the value of the attribute
          */
        public int getGrade()
        {
            return getIntAttribute(AttributeName.GRADE, null, 0);
        }

        
        /* ---------------------------------------------------------------------
        Methods for Attribute GrainDirection
        --------------------------------------------------------------------- */
        /**
          * (5) set attribute GrainDirection
          * @param enumVar: the enumVar to set the attribute to
          */
        public void setGrainDirection(EnumGrainDirection enumVar)
        {
            setAttribute(AttributeName.GRAINDIRECTION, enumVar==null ? null : enumVar.getName(), null);
        }

        /**
          * (9) get attribute GrainDirection
          * @return the value of the attribute
          */
        public EnumGrainDirection getGrainDirection()
        {
            return EnumGrainDirection.getEnum(getAttribute(AttributeName.GRAINDIRECTION, null, null));
        }

        
        /* ---------------------------------------------------------------------
        Methods for Attribute HoleCount
        --------------------------------------------------------------------- */
        /**
          * (36) set attribute HoleCount
          * @param value: the value to set the attribute to
          */
        public void setHoleCount(int value)
        {
            setAttribute(AttributeName.HOLECOUNT, value, null);
        }

        /**
          * (15) get int attribute HoleCount
          * @return int the value of the attribute
          */
        public int getHoleCount()
        {
            return getIntAttribute(AttributeName.HOLECOUNT, null, 0);
        }

        
        /* ---------------------------------------------------------------------
        Methods for Attribute ImagableSide
        --------------------------------------------------------------------- */
        /**
          * (5) set attribute ImagableSide
          * @param enumVar: the enumVar to set the attribute to
          */
        public void setImagableSide(EnumImagableSide enumVar)
        {
            setAttribute(AttributeName.IMAGABLESIDE, enumVar==null ? null : enumVar.getName(), null);
        }

        /**
          * (9) get attribute ImagableSide
          * @return the value of the attribute
          */
        public EnumImagableSide getImagableSide()
        {
            return EnumImagableSide.getEnum(getAttribute(AttributeName.IMAGABLESIDE, null, null));
        }

        
        /* ---------------------------------------------------------------------
        Methods for Attribute InsideLoss
        --------------------------------------------------------------------- */
        /**
          * (36) set attribute InsideLoss
          * @param value: the value to set the attribute to
          */
        public void setInsideLoss(double value)
        {
            setAttribute(AttributeName.INSIDELOSS, value, null);
        }

        /**
          * (17) get double attribute InsideLoss
          * @return double the value of the attribute
          */
        public double getInsideLoss()
        {
            return getRealAttribute(AttributeName.INSIDELOSS, null, 0.0);
        }

        
        /* ---------------------------------------------------------------------
        Methods for Attribute LabColorValue
        --------------------------------------------------------------------- */
        /**
          * (36) set attribute LabColorValue
          * @param value: the value to set the attribute to
          */
        public void setLabColorValue(JDFLabColor value)
        {
            setAttribute(AttributeName.LABCOLORVALUE, value, null);
        }

        /**
          * (20) get JDFLabColor attribute LabColorValue
          * @return JDFLabColor the value of the attribute, null if a the
          *         attribute value is not a valid to create a JDFLabColor
          */
        public JDFLabColor getLabColorValue()
        {
            String strAttrName = "";
            JDFLabColor nPlaceHolder = null;
            strAttrName = getAttribute(AttributeName.LABCOLORVALUE, null, JDFConstants.EMPTYSTRING);
            try
            {
                nPlaceHolder = new JDFLabColor(strAttrName);
            }
            catch(DataFormatException e)
            {
                return null;
            }
            return nPlaceHolder;
        }

        
        /* ---------------------------------------------------------------------
        Methods for Attribute MediaColorName
        --------------------------------------------------------------------- */
        /**
          * (13) set attribute MediaColorName
          * @param value: the value to set the attribute to
          */
        public void setMediaColorName(EnumNamedColor value)
        {
            setAttribute(AttributeName.MEDIACOLORNAME, value==null ? null : value.getName(), null);
        }

        /**
          * (19) get EnumNamedColor attribute MediaColorName
          * @return EnumNamedColor the value of the attribute
          */
        public EnumNamedColor getMediaColorName()
        {
            String strAttrName = "";
            EnumNamedColor nPlaceHolder = null;
            strAttrName = getAttribute(AttributeName.MEDIACOLORNAME, null, JDFConstants.EMPTYSTRING);
            nPlaceHolder = EnumNamedColor.getEnum(strAttrName);
            return nPlaceHolder;
        }

        
        /* ---------------------------------------------------------------------
        Methods for Attribute MediaColorNameDetails
        --------------------------------------------------------------------- */
        /**
          * (36) set attribute MediaColorNameDetails
          * @param value: the value to set the attribute to
          */
        public void setMediaColorNameDetails(String value)
        {
            setAttribute(AttributeName.MEDIACOLORNAMEDETAILS, value, null);
        }

        /**
          * (23) get String attribute MediaColorNameDetails
          * @return the value of the attribute
          */
        public String getMediaColorNameDetails()
        {
            return getAttribute(AttributeName.MEDIACOLORNAMEDETAILS, null, JDFConstants.EMPTYSTRING);
        }

        
        /* ---------------------------------------------------------------------
        Methods for Attribute MediaQuality
        --------------------------------------------------------------------- */
        /**
          * (36) set attribute MediaQuality
          * @param value: the value to set the attribute to
          */
        public void setMediaQuality(String value)
        {
            setAttribute(AttributeName.MEDIAQUALITY, value, null);
        }

        /**
          * (23) get String attribute MediaQuality
          * @return the value of the attribute
          */
        public String getMediaQuality()
        {
            return getAttribute(AttributeName.MEDIAQUALITY, null, JDFConstants.EMPTYSTRING);
        }

        
        /* ---------------------------------------------------------------------
        Methods for Attribute MediaSetCount
        --------------------------------------------------------------------- */
        /**
          * (36) set attribute MediaSetCount
          * @param value: the value to set the attribute to
          */
        public void setMediaSetCount(int value)
        {
            setAttribute(AttributeName.MEDIASETCOUNT, value, null);
        }

        /**
          * (15) get int attribute MediaSetCount
          * @return int the value of the attribute
          */
        public int getMediaSetCount()
        {
            return getIntAttribute(AttributeName.MEDIASETCOUNT, null, 0);
        }

        
        /* ---------------------------------------------------------------------
        Methods for Attribute MediaType
        --------------------------------------------------------------------- */
        /**
          * (5) set attribute MediaType
          * @param enumVar: the enumVar to set the attribute to
          */
        public void setMediaType(EnumMediaType enumVar)
        {
            setAttribute(AttributeName.MEDIATYPE, enumVar==null ? null : enumVar.getName(), null);
        }

        /**
          * (9) get attribute MediaType
          * @return the value of the attribute
          */
        public EnumMediaType getMediaType()
        {
            return EnumMediaType.getEnum(getAttribute(AttributeName.MEDIATYPE, null, null));
        }

        
        /* ---------------------------------------------------------------------
        Methods for Attribute MediaTypeDetails
        --------------------------------------------------------------------- */
        /**
          * (36) set attribute MediaTypeDetails
          * @param value: the value to set the attribute to
          */
        public void setMediaTypeDetails(String value)
        {
            setAttribute(AttributeName.MEDIATYPEDETAILS, value, null);
        }

        /**
          * (23) get String attribute MediaTypeDetails
          * @return the value of the attribute
          */
        public String getMediaTypeDetails()
        {
            return getAttribute(AttributeName.MEDIATYPEDETAILS, null, JDFConstants.EMPTYSTRING);
        }

        
        /* ---------------------------------------------------------------------
        Methods for Attribute Opacity
        --------------------------------------------------------------------- */
        /**
          * (5) set attribute Opacity
          * @param enumVar: the enumVar to set the attribute to
          */
        public void setOpacity(EnumOpacity enumVar)
        {
            setAttribute(AttributeName.OPACITY, enumVar==null ? null : enumVar.getName(), null);
        }

        /**
          * (9) get attribute Opacity
          * @return the value of the attribute
          */
        public EnumOpacity getOpacity()
        {
            return EnumOpacity.getEnum(getAttribute(AttributeName.OPACITY, null, null));
        }

        
        /* ---------------------------------------------------------------------
        Methods for Attribute OpacityLevel
        --------------------------------------------------------------------- */
        /**
          * (36) set attribute OpacityLevel
          * @param value: the value to set the attribute to
          */
        public void setOpacityLevel(double value)
        {
            setAttribute(AttributeName.OPACITYLEVEL, value, null);
        }

        /**
          * (17) get double attribute OpacityLevel
          * @return double the value of the attribute
          */
        public double getOpacityLevel()
        {
            return getRealAttribute(AttributeName.OPACITYLEVEL, null, 0.0);
        }

        
        /* ---------------------------------------------------------------------
        Methods for Attribute OuterCoreDiameter
        --------------------------------------------------------------------- */
        /**
          * (36) set attribute OuterCoreDiameter
          * @param value: the value to set the attribute to
          */
        public void setOuterCoreDiameter(double value)
        {
            setAttribute(AttributeName.OUTERCOREDIAMETER, value, null);
        }

        /**
          * (17) get double attribute OuterCoreDiameter
          * @return double the value of the attribute
          */
        public double getOuterCoreDiameter()
        {
            return getRealAttribute(AttributeName.OUTERCOREDIAMETER, null, 0.0);
        }

        
        /* ---------------------------------------------------------------------
        Methods for Attribute OutsideGain
        --------------------------------------------------------------------- */
        /**
          * (36) set attribute OutsideGain
          * @param value: the value to set the attribute to
          */
        public void setOutsideGain(double value)
        {
            setAttribute(AttributeName.OUTSIDEGAIN, value, null);
        }

        /**
          * (17) get double attribute OutsideGain
          * @return double the value of the attribute
          */
        public double getOutsideGain()
        {
            return getRealAttribute(AttributeName.OUTSIDEGAIN, null, 0.0);
        }

        
        /* ---------------------------------------------------------------------
        Methods for Attribute PlateTechnology
        --------------------------------------------------------------------- */
        /**
          * (5) set attribute PlateTechnology
          * @param enumVar: the enumVar to set the attribute to
          */
        public void setPlateTechnology(EnumPlateTechnology enumVar)
        {
            setAttribute(AttributeName.PLATETECHNOLOGY, enumVar==null ? null : enumVar.getName(), null);
        }

        /**
          * (9) get attribute PlateTechnology
          * @return the value of the attribute
          */
        public EnumPlateTechnology getPlateTechnology()
        {
            return EnumPlateTechnology.getEnum(getAttribute(AttributeName.PLATETECHNOLOGY, null, null));
        }

        
        /* ---------------------------------------------------------------------
        Methods for Attribute Polarity
        --------------------------------------------------------------------- */
        /**
          * (5) set attribute Polarity
          * @param enumVar: the enumVar to set the attribute to
          */
        public void setPolarity(EnumPolarity enumVar)
        {
            setAttribute(AttributeName.POLARITY, enumVar==null ? null : enumVar.getName(), null);
        }

        /**
          * (9) get attribute Polarity
          * @return the value of the attribute
          */
        public EnumPolarity getPolarity()
        {
            return EnumPolarity.getEnum(getAttribute(AttributeName.POLARITY, null, null));
        }

        
        /* ---------------------------------------------------------------------
        Methods for Attribute PrintingTechnology
        --------------------------------------------------------------------- */
        /**
          * (36) set attribute PrintingTechnology
          * @param value: the value to set the attribute to
          */
        public void setPrintingTechnology(String value)
        {
            setAttribute(AttributeName.PRINTINGTECHNOLOGY, value, null);
        }

        /**
          * (23) get String attribute PrintingTechnology
          * @return the value of the attribute
          */
        public String getPrintingTechnology()
        {
            return getAttribute(AttributeName.PRINTINGTECHNOLOGY, null, JDFConstants.EMPTYSTRING);
        }

        
        /* ---------------------------------------------------------------------
        Methods for Attribute Recycled
        --------------------------------------------------------------------- */
        /**
          * (36) set attribute Recycled
          * @param value: the value to set the attribute to
          */
        public void setRecycled(boolean value)
        {
            setAttribute(AttributeName.RECYCLED, value, null);
        }

        /**
          * (18) get boolean attribute Recycled
          * @return boolean the value of the attribute
          */
        public boolean getRecycled()
        {
            return getBoolAttribute(AttributeName.RECYCLED, null, false);
        }

        
        /* ---------------------------------------------------------------------
        Methods for Attribute RecycledPercentage
        --------------------------------------------------------------------- */
        /**
          * (36) set attribute RecycledPercentage
          * @param value: the value to set the attribute to
          */
        public void setRecycledPercentage(double value)
        {
            setAttribute(AttributeName.RECYCLEDPERCENTAGE, value, null);
        }

        /**
          * (17) get double attribute RecycledPercentage
          * @return double the value of the attribute
          */
        public double getRecycledPercentage()
        {
            return getRealAttribute(AttributeName.RECYCLEDPERCENTAGE, null, 0.0);
        }

        
        /* ---------------------------------------------------------------------
        Methods for Attribute ReliefThickness
        --------------------------------------------------------------------- */
        /**
          * (36) set attribute ReliefThickness
          * @param value: the value to set the attribute to
          */
        public void setReliefThickness(double value)
        {
            setAttribute(AttributeName.RELIEFTHICKNESS, value, null);
        }

        /**
          * (17) get double attribute ReliefThickness
          * @return double the value of the attribute
          */
        public double getReliefThickness()
        {
            return getRealAttribute(AttributeName.RELIEFTHICKNESS, null, 0.0);
        }

        
        /* ---------------------------------------------------------------------
        Methods for Attribute RollDiameter
        --------------------------------------------------------------------- */
        /**
          * (36) set attribute RollDiameter
          * @param value: the value to set the attribute to
          */
        public void setRollDiameter(double value)
        {
            setAttribute(AttributeName.ROLLDIAMETER, value, null);
        }

        /**
          * (17) get double attribute RollDiameter
          * @return double the value of the attribute
          */
        public double getRollDiameter()
        {
            return getRealAttribute(AttributeName.ROLLDIAMETER, null, 0.0);
        }

        
        /* ---------------------------------------------------------------------
        Methods for Attribute ShrinkIndex
        --------------------------------------------------------------------- */
        /**
          * (36) set attribute ShrinkIndex
          * @param value: the value to set the attribute to
          */
        public void setShrinkIndex(JDFXYPair value)
        {
            setAttribute(AttributeName.SHRINKINDEX, value, null);
        }

        /**
          * (20) get JDFXYPair attribute ShrinkIndex
          * @return JDFXYPair the value of the attribute, null if a the
          *         attribute value is not a valid to create a JDFXYPair
          */
        public JDFXYPair getShrinkIndex()
        {
            String strAttrName = "";
            JDFXYPair nPlaceHolder = null;
            strAttrName = getAttribute(AttributeName.SHRINKINDEX, null, JDFConstants.EMPTYSTRING);
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
        Methods for Attribute SleeveInterlock
        --------------------------------------------------------------------- */
        /**
          * (36) set attribute SleeveInterlock
          * @param value: the value to set the attribute to
          */
        public void setSleeveInterlock(String value)
        {
            setAttribute(AttributeName.SLEEVEINTERLOCK, value, null);
        }

        /**
          * (23) get String attribute SleeveInterlock
          * @return the value of the attribute
          */
        public String getSleeveInterlock()
        {
            return getAttribute(AttributeName.SLEEVEINTERLOCK, null, JDFConstants.EMPTYSTRING);
        }

        
        /* ---------------------------------------------------------------------
        Methods for Attribute StockType
        --------------------------------------------------------------------- */
        /**
          * (36) set attribute StockType
          * @param value: the value to set the attribute to
          */
        public void setStockType(String value)
        {
            setAttribute(AttributeName.STOCKTYPE, value, null);
        }

        /**
          * (23) get String attribute StockType
          * @return the value of the attribute
          */
        public String getStockType()
        {
            return getAttribute(AttributeName.STOCKTYPE, null, JDFConstants.EMPTYSTRING);
        }

        
        /* ---------------------------------------------------------------------
        Methods for Attribute Texture
        --------------------------------------------------------------------- */
        /**
          * (36) set attribute Texture
          * @param value: the value to set the attribute to
          */
        public void setTexture(String value)
        {
            setAttribute(AttributeName.TEXTURE, value, null);
        }

        /**
          * (23) get String attribute Texture
          * @return the value of the attribute
          */
        public String getTexture()
        {
            return getAttribute(AttributeName.TEXTURE, null, JDFConstants.EMPTYSTRING);
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

        
        /* ---------------------------------------------------------------------
        Methods for Attribute UserMediaType
        --------------------------------------------------------------------- */
        /**
          * (36) set attribute UserMediaType
          * @param value: the value to set the attribute to
          */
        public void setUserMediaType(String value)
        {
            setAttribute(AttributeName.USERMEDIATYPE, value, null);
        }

        /**
          * (23) get String attribute UserMediaType
          * @return the value of the attribute
          */
        public String getUserMediaType()
        {
            return getAttribute(AttributeName.USERMEDIATYPE, null, JDFConstants.EMPTYSTRING);
        }

        
        /* ---------------------------------------------------------------------
        Methods for Attribute Weight
        --------------------------------------------------------------------- */
        /**
          * (36) set attribute Weight
          * @param value: the value to set the attribute to
          */
        public void setWeight(double value)
        {
            setAttribute(AttributeName.WEIGHT, value, null);
        }

        /**
          * (17) get double attribute Weight
          * @return double the value of the attribute
          */
        public double getWeight()
        {
            return getRealAttribute(AttributeName.WEIGHT, null, 0.0);
        }

        
        /* ---------------------------------------------------------------------
        Methods for Attribute WrapperWeight
        --------------------------------------------------------------------- */
        /**
          * (36) set attribute WrapperWeight
          * @param value: the value to set the attribute to
          */
        public void setWrapperWeight(double value)
        {
            setAttribute(AttributeName.WRAPPERWEIGHT, value, null);
        }

        /**
          * (17) get double attribute WrapperWeight
          * @return double the value of the attribute
          */
        public double getWrapperWeight()
        {
            return getRealAttribute(AttributeName.WRAPPERWEIGHT, null, 0.0);
        }

/* ***********************************************************************
 * Element getter / setter
 * ***********************************************************************
 */

    /**
     * (24) const get element Color
     * @return JDFColor the element
     */
    public JDFColor getColor()
    {
        return (JDFColor) getElement(ElementName.COLOR, null, 0);
    }

    /** (25) getCreateColor
     * 
     * @return JDFColor the element
     */
    public JDFColor getCreateColor()
    {
        return (JDFColor) getCreateElement_KElement(ElementName.COLOR, null, 0);
    }

    /**
     * (29) append element Color
     */
    public JDFColor appendColor() throws JDFException
    {
        return (JDFColor) appendElementN(ElementName.COLOR, 1, null);
    }

    /**
      * (31) create inter-resource link to refTarget
      * @param refTarget the element that is referenced
      */
    public void refColor(JDFColor refTarget)
    {
        refElement(refTarget);
    }

    /**
     * (24) const get element ColorMeasurementConditions
     * @return JDFColorMeasurementConditions the element
     */
    public JDFColorMeasurementConditions getColorMeasurementConditions()
    {
        return (JDFColorMeasurementConditions) getElement(ElementName.COLORMEASUREMENTCONDITIONS, null, 0);
    }

    /** (25) getCreateColorMeasurementConditions
     * 
     * @return JDFColorMeasurementConditions the element
     */
    public JDFColorMeasurementConditions getCreateColorMeasurementConditions()
    {
        return (JDFColorMeasurementConditions) getCreateElement_KElement(ElementName.COLORMEASUREMENTCONDITIONS, null, 0);
    }

    /**
     * (29) append element ColorMeasurementConditions
     */
    public JDFColorMeasurementConditions appendColorMeasurementConditions() throws JDFException
    {
        return (JDFColorMeasurementConditions) appendElementN(ElementName.COLORMEASUREMENTCONDITIONS, 1, null);
    }

    /**
      * (31) create inter-resource link to refTarget
      * @param refTarget the element that is referenced
      */
    public void refColorMeasurementConditions(JDFColorMeasurementConditions refTarget)
    {
        refElement(refTarget);
    }

    /**
     * (24) const get element MediaLayers
     * @return JDFMediaLayers the element
     */
    public JDFMediaLayers getMediaLayers()
    {
        return (JDFMediaLayers) getElement(ElementName.MEDIALAYERS, null, 0);
    }

    /** (25) getCreateMediaLayers
     * 
     * @return JDFMediaLayers the element
     */
    public JDFMediaLayers getCreateMediaLayers()
    {
        return (JDFMediaLayers) getCreateElement_KElement(ElementName.MEDIALAYERS, null, 0);
    }

    /**
     * (29) append element MediaLayers
     */
    public JDFMediaLayers appendMediaLayers() throws JDFException
    {
        return (JDFMediaLayers) appendElementN(ElementName.MEDIALAYERS, 1, null);
    }

    /**
     * (24) const get element HoleList
     * @return JDFHoleList the element
     */
    public JDFHoleList getHoleList()
    {
        return (JDFHoleList) getElement(ElementName.HOLELIST, null, 0);
    }

    /** (25) getCreateHoleList
     * 
     * @return JDFHoleList the element
     */
    public JDFHoleList getCreateHoleList()
    {
        return (JDFHoleList) getCreateElement_KElement(ElementName.HOLELIST, null, 0);
    }

    /**
     * (29) append element HoleList
     */
    public JDFHoleList appendHoleList() throws JDFException
    {
        return (JDFHoleList) appendElementN(ElementName.HOLELIST, 1, null);
    }

    /** (26) getCreateTabDimensions
     * 
     * @param iSkip number of elements to skip
     * @return JDFTabDimensions the element
     */
    public JDFTabDimensions getCreateTabDimensions(int iSkip)
    {
        return (JDFTabDimensions)getCreateElement_KElement(ElementName.TABDIMENSIONS, null, iSkip);
    }

    /**
     * (27) const get element TabDimensions
     * @param iSkip number of elements to skip
     * @return JDFTabDimensions the element
     * default is getTabDimensions(0)     */
    public JDFTabDimensions getTabDimensions(int iSkip)
    {
        return (JDFTabDimensions) getElement(ElementName.TABDIMENSIONS, null, iSkip);
    }

    /**
     * Get all TabDimensions from the current element
     * 
     * @return Collection<JDFTabDimensions>, null if none are available
     */
    public Collection<JDFTabDimensions> getAllTabDimensions()
    {
        final VElement vc = getChildElementVector(ElementName.TABDIMENSIONS, null);
        if (vc == null || vc.size() == 0)
        {
            return null;
        }

        final Vector<JDFTabDimensions> v = new Vector<JDFTabDimensions>();
        for (int i = 0; i < vc.size(); i++)
        {
            v.add((JDFTabDimensions) vc.get(i));
        }

        return v;
    }

    /**
     * (30) append element TabDimensions
     */
    public JDFTabDimensions appendTabDimensions() throws JDFException
    {
        return (JDFTabDimensions) appendElement(ElementName.TABDIMENSIONS, null);
    }

    /** (26) getCreateContact
     * 
     * @param iSkip number of elements to skip
     * @return JDFContact the element
     */
    public JDFContact getCreateContact(int iSkip)
    {
        return (JDFContact)getCreateElement_KElement(ElementName.CONTACT, null, iSkip);
    }

    /**
     * (27) const get element Contact
     * @param iSkip number of elements to skip
     * @return JDFContact the element
     * default is getContact(0)     */
    public JDFContact getContact(int iSkip)
    {
        return (JDFContact) getElement(ElementName.CONTACT, null, iSkip);
    }

    /**
     * Get all Contact from the current element
     * 
     * @return Collection<JDFContact>, null if none are available
     */
    public Collection<JDFContact> getAllContact()
    {
        final VElement vc = getChildElementVector(ElementName.CONTACT, null);
        if (vc == null || vc.size() == 0)
        {
            return null;
        }

        final Vector<JDFContact> v = new Vector<JDFContact>();
        for (int i = 0; i < vc.size(); i++)
        {
            v.add((JDFContact) vc.get(i));
        }

        return v;
    }

    /**
     * (30) append element Contact
     */
    public JDFContact appendContact() throws JDFException
    {
        return (JDFContact) appendElement(ElementName.CONTACT, null);
    }

    /**
      * (31) create inter-resource link to refTarget
      * @param refTarget the element that is referenced
      */
    public void refContact(JDFContact refTarget)
    {
        refElement(refTarget);
    }

    /** (26) getCreateIdentificationField
     * 
     * @param iSkip number of elements to skip
     * @return JDFIdentificationField the element
     */
    public JDFIdentificationField getCreateIdentificationField(int iSkip)
    {
        return (JDFIdentificationField)getCreateElement_KElement(ElementName.IDENTIFICATIONFIELD, null, iSkip);
    }

    /**
     * (27) const get element IdentificationField
     * @param iSkip number of elements to skip
     * @return JDFIdentificationField the element
     * default is getIdentificationField(0)     */
    public JDFIdentificationField getIdentificationField(int iSkip)
    {
        return (JDFIdentificationField) getElement(ElementName.IDENTIFICATIONFIELD, null, iSkip);
    }

    /**
     * Get all IdentificationField from the current element
     * 
     * @return Collection<JDFIdentificationField>, null if none are available
     */
    public Collection<JDFIdentificationField> getAllIdentificationField()
    {
        final VElement vc = getChildElementVector(ElementName.IDENTIFICATIONFIELD, null);
        if (vc == null || vc.size() == 0)
        {
            return null;
        }

        final Vector<JDFIdentificationField> v = new Vector<JDFIdentificationField>();
        for (int i = 0; i < vc.size(); i++)
        {
            v.add((JDFIdentificationField) vc.get(i));
        }

        return v;
    }

    /**
     * (30) append element IdentificationField
     */
    public JDFIdentificationField appendIdentificationField() throws JDFException
    {
        return (JDFIdentificationField) appendElement(ElementName.IDENTIFICATIONFIELD, null);
    }

    /**
      * (31) create inter-resource link to refTarget
      * @param refTarget the element that is referenced
      */
    public void refIdentificationField(JDFIdentificationField refTarget)
    {
        refElement(refTarget);
    }

}// end namespace JDF
