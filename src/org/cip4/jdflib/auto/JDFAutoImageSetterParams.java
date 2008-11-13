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
import org.cip4.jdflib.datatypes.JDFTransferFunction;
import org.cip4.jdflib.datatypes.JDFXYPair;
import org.cip4.jdflib.resource.JDFFitPolicy;
import org.cip4.jdflib.resource.JDFResource;
import org.cip4.jdflib.resource.process.JDFMedia;

public abstract class JDFAutoImageSetterParams extends JDFResource
{

    private static final long serialVersionUID = 1L;

    private static AtrInfoTable[] atrInfoTable = new AtrInfoTable[18];
    static
    {
        atrInfoTable[0] = new AtrInfoTable(AttributeName.MIRRORAROUND, 0x33333333, AttributeInfo.EnumAttributeType.enumeration, EnumMirrorAround.getEnum(0), "None");
        atrInfoTable[1] = new AtrInfoTable(AttributeName.POLARITY, 0x33333333, AttributeInfo.EnumAttributeType.enumeration, EnumPolarity.getEnum(0), "Positive");
        atrInfoTable[2] = new AtrInfoTable(AttributeName.SIDES, 0x33333311, AttributeInfo.EnumAttributeType.enumeration, EnumSides.getEnum(0), "OneSidedFront");
        atrInfoTable[3] = new AtrInfoTable(AttributeName.ADVANCEDISTANCE, 0x33333333, AttributeInfo.EnumAttributeType.double_, null, null);
        atrInfoTable[4] = new AtrInfoTable(AttributeName.BURNOUTAREA, 0x33333331, AttributeInfo.EnumAttributeType.XYPair, null, null);
        atrInfoTable[5] = new AtrInfoTable(AttributeName.CENTERACROSS, 0x33333333, AttributeInfo.EnumAttributeType.enumeration, EnumCenterAcross.getEnum(0), null);
        atrInfoTable[6] = new AtrInfoTable(AttributeName.CUTMEDIA, 0x33333333, AttributeInfo.EnumAttributeType.boolean_, null, null);
        atrInfoTable[7] = new AtrInfoTable(AttributeName.MANUALFEED, 0x33333311, AttributeInfo.EnumAttributeType.boolean_, null, null);
        atrInfoTable[8] = new AtrInfoTable(AttributeName.NONPRINTABLEMARGINBOTTOM, 0x33333111, AttributeInfo.EnumAttributeType.double_, null, null);
        atrInfoTable[9] = new AtrInfoTable(AttributeName.NONPRINTABLEMARGINLEFT, 0x33333111, AttributeInfo.EnumAttributeType.double_, null, null);
        atrInfoTable[10] = new AtrInfoTable(AttributeName.NONPRINTABLEMARGINRIGHT, 0x33333111, AttributeInfo.EnumAttributeType.double_, null, null);
        atrInfoTable[11] = new AtrInfoTable(AttributeName.NONPRINTABLEMARGINTOP, 0x33333111, AttributeInfo.EnumAttributeType.double_, null, null);
        atrInfoTable[12] = new AtrInfoTable(AttributeName.PUNCH, 0x44444333, AttributeInfo.EnumAttributeType.boolean_, null, null);
        atrInfoTable[13] = new AtrInfoTable(AttributeName.PUNCHTYPE, 0x44444333, AttributeInfo.EnumAttributeType.string, null, null);
        atrInfoTable[14] = new AtrInfoTable(AttributeName.RESOLUTION, 0x33333333, AttributeInfo.EnumAttributeType.XYPair, null, null);
        atrInfoTable[15] = new AtrInfoTable(AttributeName.ROLLCUT, 0x33333333, AttributeInfo.EnumAttributeType.double_, null, null);
        atrInfoTable[16] = new AtrInfoTable(AttributeName.SOURCEWORKSTYLE, 0x33333311, AttributeInfo.EnumAttributeType.enumeration, EnumSourceWorkStyle.getEnum(0), null);
        atrInfoTable[17] = new AtrInfoTable(AttributeName.TRANSFERCURVE, 0x33333333, AttributeInfo.EnumAttributeType.TransferFunction, null, null);
    }
    
    @Override
	protected AttributeInfo getTheAttributeInfo()
    {
        return super.getTheAttributeInfo().updateReplace(atrInfoTable);
    }


    private static ElemInfoTable[] elemInfoTable = new ElemInfoTable[2];
    static
    {
        elemInfoTable[0] = new ElemInfoTable(ElementName.MEDIA, 0x66666661);
        elemInfoTable[1] = new ElemInfoTable(ElementName.FITPOLICY, 0x66666611);
    }
    
    @Override
	protected ElementInfo getTheElementInfo()
    {
        return super.getTheElementInfo().updateReplace(elemInfoTable);
    }



    /**
     * Constructor for JDFAutoImageSetterParams
     * @param myOwnerDocument
     * @param qualifiedName
     */
    protected JDFAutoImageSetterParams(
        CoreDocumentImpl myOwnerDocument,
        String qualifiedName)
    {
        super(myOwnerDocument, qualifiedName);
    }

    /**
     * Constructor for JDFAutoImageSetterParams
     * @param myOwnerDocument
     * @param myNamespaceURI
     * @param qualifiedName
     */
    protected JDFAutoImageSetterParams(
        CoreDocumentImpl myOwnerDocument,
        String myNamespaceURI,
        String qualifiedName)
    {
        super(myOwnerDocument, myNamespaceURI, qualifiedName);
    }

    /**
     * Constructor for JDFAutoImageSetterParams
     * @param myOwnerDocument
     * @param myNamespaceURI
     * @param qualifiedName
     * @param myLocalName
     */
    protected JDFAutoImageSetterParams(
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
        return " JDFAutoImageSetterParams[  --> " + super.toString() + " ]";
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
        * Enumeration strings for MirrorAround
        */

        public static class EnumMirrorAround extends ValuedEnum
        {
            private static final long serialVersionUID = 1L;
            private static int m_startValue = 0;

            private EnumMirrorAround(String name)
            {
                super(name, m_startValue++);
            }

            public static EnumMirrorAround getEnum(String enumName)
            {
                return (EnumMirrorAround) getEnum(EnumMirrorAround.class, enumName);
            }

            public static EnumMirrorAround getEnum(int enumValue)
            {
                return (EnumMirrorAround) getEnum(EnumMirrorAround.class, enumValue);
            }

            public static Map getEnumMap()
            {
                return getEnumMap(EnumMirrorAround.class);
            }

            public static List getEnumList()
            {
                return getEnumList(EnumMirrorAround.class);
            }

            public static Iterator iterator()
            {
                return iterator(EnumMirrorAround.class);
            }

            public static final EnumMirrorAround None = new EnumMirrorAround("None");
            public static final EnumMirrorAround FeedDirection = new EnumMirrorAround("FeedDirection");
            public static final EnumMirrorAround MediaWidth = new EnumMirrorAround("MediaWidth");
            public static final EnumMirrorAround Both = new EnumMirrorAround("Both");
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



        /**
        * Enumeration strings for Sides
        */

        public static class EnumSides extends ValuedEnum
        {
            private static final long serialVersionUID = 1L;
            private static int m_startValue = 0;

            private EnumSides(String name)
            {
                super(name, m_startValue++);
            }

            public static EnumSides getEnum(String enumName)
            {
                return (EnumSides) getEnum(EnumSides.class, enumName);
            }

            public static EnumSides getEnum(int enumValue)
            {
                return (EnumSides) getEnum(EnumSides.class, enumValue);
            }

            public static Map getEnumMap()
            {
                return getEnumMap(EnumSides.class);
            }

            public static List getEnumList()
            {
                return getEnumList(EnumSides.class);
            }

            public static Iterator iterator()
            {
                return iterator(EnumSides.class);
            }

            public static final EnumSides OneSidedBackFlipX = new EnumSides("OneSidedBackFlipX");
            public static final EnumSides OneSidedBackFlipY = new EnumSides("OneSidedBackFlipY");
            public static final EnumSides OneSidedFront = new EnumSides("OneSidedFront");
            public static final EnumSides TwoSidedFlipX = new EnumSides("TwoSidedFlipX");
            public static final EnumSides TwoSidedFlipY = new EnumSides("TwoSidedFlipY");
        }      



        /**
        * Enumeration strings for CenterAcross
        */

        public static class EnumCenterAcross extends ValuedEnum
        {
            private static final long serialVersionUID = 1L;
            private static int m_startValue = 0;

            private EnumCenterAcross(String name)
            {
                super(name, m_startValue++);
            }

            public static EnumCenterAcross getEnum(String enumName)
            {
                return (EnumCenterAcross) getEnum(EnumCenterAcross.class, enumName);
            }

            public static EnumCenterAcross getEnum(int enumValue)
            {
                return (EnumCenterAcross) getEnum(EnumCenterAcross.class, enumValue);
            }

            public static Map getEnumMap()
            {
                return getEnumMap(EnumCenterAcross.class);
            }

            public static List getEnumList()
            {
                return getEnumList(EnumCenterAcross.class);
            }

            public static Iterator iterator()
            {
                return iterator(EnumCenterAcross.class);
            }

            public static final EnumCenterAcross None = new EnumCenterAcross("None");
            public static final EnumCenterAcross FeedDirection = new EnumCenterAcross("FeedDirection");
            public static final EnumCenterAcross MediaWidth = new EnumCenterAcross("MediaWidth");
            public static final EnumCenterAcross Both = new EnumCenterAcross("Both");
        }      



        /**
        * Enumeration strings for SourceWorkStyle
        */

        public static class EnumSourceWorkStyle extends ValuedEnum
        {
            private static final long serialVersionUID = 1L;
            private static int m_startValue = 0;

            private EnumSourceWorkStyle(String name)
            {
                super(name, m_startValue++);
            }

            public static EnumSourceWorkStyle getEnum(String enumName)
            {
                return (EnumSourceWorkStyle) getEnum(EnumSourceWorkStyle.class, enumName);
            }

            public static EnumSourceWorkStyle getEnum(int enumValue)
            {
                return (EnumSourceWorkStyle) getEnum(EnumSourceWorkStyle.class, enumValue);
            }

            public static Map getEnumMap()
            {
                return getEnumMap(EnumSourceWorkStyle.class);
            }

            public static List getEnumList()
            {
                return getEnumList(EnumSourceWorkStyle.class);
            }

            public static Iterator iterator()
            {
                return iterator(EnumSourceWorkStyle.class);
            }

            public static final EnumSourceWorkStyle Simplex = new EnumSourceWorkStyle("Simplex");
            public static final EnumSourceWorkStyle Perfecting = new EnumSourceWorkStyle("Perfecting");
            public static final EnumSourceWorkStyle WorkAndBack = new EnumSourceWorkStyle("WorkAndBack");
            public static final EnumSourceWorkStyle WorkAndTurn = new EnumSourceWorkStyle("WorkAndTurn");
            public static final EnumSourceWorkStyle WorkAndTumble = new EnumSourceWorkStyle("WorkAndTumble");
            public static final EnumSourceWorkStyle WorkAndTwist = new EnumSourceWorkStyle("WorkAndTwist");
        }      



/* ************************************************************************
 * Attribute getter / setter
 * ************************************************************************
 */
        
        /* ---------------------------------------------------------------------
        Methods for Attribute MirrorAround
        --------------------------------------------------------------------- */
        /**
          * (5) set attribute MirrorAround
          * @param enumVar: the enumVar to set the attribute to
          */
        public void setMirrorAround(EnumMirrorAround enumVar)
        {
            setAttribute(AttributeName.MIRRORAROUND, enumVar==null ? null : enumVar.getName(), null);
        }

        /**
          * (9) get attribute MirrorAround
          * @return the value of the attribute
          */
        public EnumMirrorAround getMirrorAround()
        {
            return EnumMirrorAround.getEnum(getAttribute(AttributeName.MIRRORAROUND, null, "None"));
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
            return EnumPolarity.getEnum(getAttribute(AttributeName.POLARITY, null, "Positive"));
        }

        
        /* ---------------------------------------------------------------------
        Methods for Attribute Sides
        --------------------------------------------------------------------- */
        /**
          * (5) set attribute Sides
          * @param enumVar: the enumVar to set the attribute to
          */
        public void setSides(EnumSides enumVar)
        {
            setAttribute(AttributeName.SIDES, enumVar==null ? null : enumVar.getName(), null);
        }

        /**
          * (9) get attribute Sides
          * @return the value of the attribute
          */
        public EnumSides getSides()
        {
            return EnumSides.getEnum(getAttribute(AttributeName.SIDES, null, "OneSidedFront"));
        }

        
        /* ---------------------------------------------------------------------
        Methods for Attribute AdvanceDistance
        --------------------------------------------------------------------- */
        /**
          * (36) set attribute AdvanceDistance
          * @param value: the value to set the attribute to
          */
        public void setAdvanceDistance(double value)
        {
            setAttribute(AttributeName.ADVANCEDISTANCE, value, null);
        }

        /**
          * (17) get double attribute AdvanceDistance
          * @return double the value of the attribute
          */
        public double getAdvanceDistance()
        {
            return getRealAttribute(AttributeName.ADVANCEDISTANCE, null, 0.0);
        }

        
        /* ---------------------------------------------------------------------
        Methods for Attribute BurnOutArea
        --------------------------------------------------------------------- */
        /**
          * (36) set attribute BurnOutArea
          * @param value: the value to set the attribute to
          */
        public void setBurnOutArea(JDFXYPair value)
        {
            setAttribute(AttributeName.BURNOUTAREA, value, null);
        }

        /**
          * (20) get JDFXYPair attribute BurnOutArea
          * @return JDFXYPair the value of the attribute, null if a the
          *         attribute value is not a valid to create a JDFXYPair
          */
        public JDFXYPair getBurnOutArea()
        {
            String strAttrName = "";
            JDFXYPair nPlaceHolder = null;
            strAttrName = getAttribute(AttributeName.BURNOUTAREA, null, JDFConstants.EMPTYSTRING);
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
        Methods for Attribute CenterAcross
        --------------------------------------------------------------------- */
        /**
          * (5) set attribute CenterAcross
          * @param enumVar: the enumVar to set the attribute to
          */
        public void setCenterAcross(EnumCenterAcross enumVar)
        {
            setAttribute(AttributeName.CENTERACROSS, enumVar==null ? null : enumVar.getName(), null);
        }

        /**
          * (9) get attribute CenterAcross
          * @return the value of the attribute
          */
        public EnumCenterAcross getCenterAcross()
        {
            return EnumCenterAcross.getEnum(getAttribute(AttributeName.CENTERACROSS, null, null));
        }

        
        /* ---------------------------------------------------------------------
        Methods for Attribute CutMedia
        --------------------------------------------------------------------- */
        /**
          * (36) set attribute CutMedia
          * @param value: the value to set the attribute to
          */
        public void setCutMedia(boolean value)
        {
            setAttribute(AttributeName.CUTMEDIA, value, null);
        }

        /**
          * (18) get boolean attribute CutMedia
          * @return boolean the value of the attribute
          */
        public boolean getCutMedia()
        {
            return getBoolAttribute(AttributeName.CUTMEDIA, null, false);
        }

        
        /* ---------------------------------------------------------------------
        Methods for Attribute ManualFeed
        --------------------------------------------------------------------- */
        /**
          * (36) set attribute ManualFeed
          * @param value: the value to set the attribute to
          */
        public void setManualFeed(boolean value)
        {
            setAttribute(AttributeName.MANUALFEED, value, null);
        }

        /**
          * (18) get boolean attribute ManualFeed
          * @return boolean the value of the attribute
          */
        public boolean getManualFeed()
        {
            return getBoolAttribute(AttributeName.MANUALFEED, null, false);
        }

        
        /* ---------------------------------------------------------------------
        Methods for Attribute NonPrintableMarginBottom
        --------------------------------------------------------------------- */
        /**
          * (36) set attribute NonPrintableMarginBottom
          * @param value: the value to set the attribute to
          */
        public void setNonPrintableMarginBottom(double value)
        {
            setAttribute(AttributeName.NONPRINTABLEMARGINBOTTOM, value, null);
        }

        /**
          * (17) get double attribute NonPrintableMarginBottom
          * @return double the value of the attribute
          */
        public double getNonPrintableMarginBottom()
        {
            return getRealAttribute(AttributeName.NONPRINTABLEMARGINBOTTOM, null, 0.0);
        }

        
        /* ---------------------------------------------------------------------
        Methods for Attribute NonPrintableMarginLeft
        --------------------------------------------------------------------- */
        /**
          * (36) set attribute NonPrintableMarginLeft
          * @param value: the value to set the attribute to
          */
        public void setNonPrintableMarginLeft(double value)
        {
            setAttribute(AttributeName.NONPRINTABLEMARGINLEFT, value, null);
        }

        /**
          * (17) get double attribute NonPrintableMarginLeft
          * @return double the value of the attribute
          */
        public double getNonPrintableMarginLeft()
        {
            return getRealAttribute(AttributeName.NONPRINTABLEMARGINLEFT, null, 0.0);
        }

        
        /* ---------------------------------------------------------------------
        Methods for Attribute NonPrintableMarginRight
        --------------------------------------------------------------------- */
        /**
          * (36) set attribute NonPrintableMarginRight
          * @param value: the value to set the attribute to
          */
        public void setNonPrintableMarginRight(double value)
        {
            setAttribute(AttributeName.NONPRINTABLEMARGINRIGHT, value, null);
        }

        /**
          * (17) get double attribute NonPrintableMarginRight
          * @return double the value of the attribute
          */
        public double getNonPrintableMarginRight()
        {
            return getRealAttribute(AttributeName.NONPRINTABLEMARGINRIGHT, null, 0.0);
        }

        
        /* ---------------------------------------------------------------------
        Methods for Attribute NonPrintableMarginTop
        --------------------------------------------------------------------- */
        /**
          * (36) set attribute NonPrintableMarginTop
          * @param value: the value to set the attribute to
          */
        public void setNonPrintableMarginTop(double value)
        {
            setAttribute(AttributeName.NONPRINTABLEMARGINTOP, value, null);
        }

        /**
          * (17) get double attribute NonPrintableMarginTop
          * @return double the value of the attribute
          */
        public double getNonPrintableMarginTop()
        {
            return getRealAttribute(AttributeName.NONPRINTABLEMARGINTOP, null, 0.0);
        }

        
        /* ---------------------------------------------------------------------
        Methods for Attribute Punch
        --------------------------------------------------------------------- */
        /**
          * (36) set attribute Punch
          * @param value: the value to set the attribute to
          */
        public void setPunch(boolean value)
        {
            setAttribute(AttributeName.PUNCH, value, null);
        }

        /**
          * (18) get boolean attribute Punch
          * @return boolean the value of the attribute
          */
        public boolean getPunch()
        {
            return getBoolAttribute(AttributeName.PUNCH, null, false);
        }

        
        /* ---------------------------------------------------------------------
        Methods for Attribute PunchType
        --------------------------------------------------------------------- */
        /**
          * (36) set attribute PunchType
          * @param value: the value to set the attribute to
          */
        public void setPunchType(String value)
        {
            setAttribute(AttributeName.PUNCHTYPE, value, null);
        }

        /**
          * (23) get String attribute PunchType
          * @return the value of the attribute
          */
        public String getPunchType()
        {
            return getAttribute(AttributeName.PUNCHTYPE, null, JDFConstants.EMPTYSTRING);
        }

        
        /* ---------------------------------------------------------------------
        Methods for Attribute Resolution
        --------------------------------------------------------------------- */
        /**
          * (36) set attribute Resolution
          * @param value: the value to set the attribute to
          */
        public void setResolution(JDFXYPair value)
        {
            setAttribute(AttributeName.RESOLUTION, value, null);
        }

        /**
          * (20) get JDFXYPair attribute Resolution
          * @return JDFXYPair the value of the attribute, null if a the
          *         attribute value is not a valid to create a JDFXYPair
          */
        public JDFXYPair getResolution()
        {
            String strAttrName = "";
            JDFXYPair nPlaceHolder = null;
            strAttrName = getAttribute(AttributeName.RESOLUTION, null, JDFConstants.EMPTYSTRING);
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
        Methods for Attribute RollCut
        --------------------------------------------------------------------- */
        /**
          * (36) set attribute RollCut
          * @param value: the value to set the attribute to
          */
        public void setRollCut(double value)
        {
            setAttribute(AttributeName.ROLLCUT, value, null);
        }

        /**
          * (17) get double attribute RollCut
          * @return double the value of the attribute
          */
        public double getRollCut()
        {
            return getRealAttribute(AttributeName.ROLLCUT, null, 0.0);
        }

        
        /* ---------------------------------------------------------------------
        Methods for Attribute SourceWorkStyle
        --------------------------------------------------------------------- */
        /**
          * (5) set attribute SourceWorkStyle
          * @param enumVar: the enumVar to set the attribute to
          */
        public void setSourceWorkStyle(EnumSourceWorkStyle enumVar)
        {
            setAttribute(AttributeName.SOURCEWORKSTYLE, enumVar==null ? null : enumVar.getName(), null);
        }

        /**
          * (9) get attribute SourceWorkStyle
          * @return the value of the attribute
          */
        public EnumSourceWorkStyle getSourceWorkStyle()
        {
            return EnumSourceWorkStyle.getEnum(getAttribute(AttributeName.SOURCEWORKSTYLE, null, null));
        }

        
        /* ---------------------------------------------------------------------
        Methods for Attribute TransferCurve
        --------------------------------------------------------------------- */
        /**
          * (36) set attribute TransferCurve
          * @param value: the value to set the attribute to
          */
        public void setTransferCurve(JDFTransferFunction value)
        {
            setAttribute(AttributeName.TRANSFERCURVE, value, null);
        }

        /**
          * (20) get JDFTransferFunction attribute TransferCurve
          * @return JDFTransferFunction the value of the attribute, null if a the
          *         attribute value is not a valid to create a JDFTransferFunction
          */
        public JDFTransferFunction getTransferCurve()
        {
            String strAttrName = "";
            JDFTransferFunction nPlaceHolder = null;
            strAttrName = getAttribute(AttributeName.TRANSFERCURVE, null, JDFConstants.EMPTYSTRING);
            try
            {
                nPlaceHolder = new JDFTransferFunction(strAttrName);
            }
            catch(DataFormatException e)
            {
                return null;
            }
            return nPlaceHolder;
        }

/* ***********************************************************************
 * Element getter / setter
 * ***********************************************************************
 */

    /**
     * (24) const get element Media
     * @return JDFMedia the element
     */
    public JDFMedia getMedia()
    {
        return (JDFMedia) getElement(ElementName.MEDIA, null, 0);
    }

    /** (25) getCreateMedia
     * 
     * @return JDFMedia the element
     */
    public JDFMedia getCreateMedia()
    {
        return (JDFMedia) getCreateElement_KElement(ElementName.MEDIA, null, 0);
    }

    /**
     * (29) append element Media
     */
    public JDFMedia appendMedia() throws JDFException
    {
        return (JDFMedia) appendElementN(ElementName.MEDIA, 1, null);
    }

    /**
      * (31) create inter-resource link to refTarget
      * @param refTarget the element that is referenced
      */
    public void refMedia(JDFMedia refTarget)
    {
        refElement(refTarget);
    }

    /**
     * (24) const get element FitPolicy
     * @return JDFFitPolicy the element
     */
    public JDFFitPolicy getFitPolicy()
    {
        return (JDFFitPolicy) getElement(ElementName.FITPOLICY, null, 0);
    }

    /** (25) getCreateFitPolicy
     * 
     * @return JDFFitPolicy the element
     */
    public JDFFitPolicy getCreateFitPolicy()
    {
        return (JDFFitPolicy) getCreateElement_KElement(ElementName.FITPOLICY, null, 0);
    }

    /**
     * (29) append element FitPolicy
     */
    public JDFFitPolicy appendFitPolicy() throws JDFException
    {
        return (JDFFitPolicy) appendElementN(ElementName.FITPOLICY, 1, null);
    }

    /**
      * (31) create inter-resource link to refTarget
      * @param refTarget the element that is referenced
      */
    public void refFitPolicy(JDFFitPolicy refTarget)
    {
        refElement(refTarget);
    }

}// end namespace JDF
