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

public abstract class JDFAutoStripCellParams extends JDFElement
{

    private static final long serialVersionUID = 1L;

    private static AtrInfoTable[] atrInfoTable = new AtrInfoTable[19];
    static
    {
        atrInfoTable[0] = new AtrInfoTable(AttributeName.BLEEDFACE, 0x33333311, AttributeInfo.EnumAttributeType.double_, null, null);
        atrInfoTable[1] = new AtrInfoTable(AttributeName.BLEEDSPINE, 0x33333311, AttributeInfo.EnumAttributeType.double_, null, null);
        atrInfoTable[2] = new AtrInfoTable(AttributeName.BLEEDHEAD, 0x33333311, AttributeInfo.EnumAttributeType.double_, null, null);
        atrInfoTable[3] = new AtrInfoTable(AttributeName.BLEEDFOOT, 0x33333311, AttributeInfo.EnumAttributeType.double_, null, null);
        atrInfoTable[4] = new AtrInfoTable(AttributeName.TRIMFACE, 0x33333311, AttributeInfo.EnumAttributeType.double_, null, null);
        atrInfoTable[5] = new AtrInfoTable(AttributeName.SPINE, 0x33333311, AttributeInfo.EnumAttributeType.double_, null, null);
        atrInfoTable[6] = new AtrInfoTable(AttributeName.TRIMHEAD, 0x33333311, AttributeInfo.EnumAttributeType.double_, null, null);
        atrInfoTable[7] = new AtrInfoTable(AttributeName.TRIMFOOT, 0x33333311, AttributeInfo.EnumAttributeType.double_, null, null);
        atrInfoTable[8] = new AtrInfoTable(AttributeName.FRONTOVERFOLD, 0x33333311, AttributeInfo.EnumAttributeType.double_, null, null);
        atrInfoTable[9] = new AtrInfoTable(AttributeName.BACKOVERFOLD, 0x33333311, AttributeInfo.EnumAttributeType.double_, null, null);
        atrInfoTable[10] = new AtrInfoTable(AttributeName.MILLINGDEPTH, 0x33333311, AttributeInfo.EnumAttributeType.double_, null, null);
        atrInfoTable[11] = new AtrInfoTable(AttributeName.CUTWIDTHHEAD, 0x33333311, AttributeInfo.EnumAttributeType.double_, null, null);
        atrInfoTable[12] = new AtrInfoTable(AttributeName.CUTWIDTHFOOT, 0x33333311, AttributeInfo.EnumAttributeType.double_, null, null);
        atrInfoTable[13] = new AtrInfoTable(AttributeName.TRIMSIZE, 0x33333311, AttributeInfo.EnumAttributeType.XYPair, null, null);
        atrInfoTable[14] = new AtrInfoTable(AttributeName.CREEP, 0x33333311, AttributeInfo.EnumAttributeType.XYPair, null, null);
        atrInfoTable[15] = new AtrInfoTable(AttributeName.SIDES, 0x33333311, AttributeInfo.EnumAttributeType.enumeration, EnumSides.getEnum(0), null);
        atrInfoTable[16] = new AtrInfoTable(AttributeName.MASKBLEED, 0x33333111, AttributeInfo.EnumAttributeType.double_, null, null);
        atrInfoTable[17] = new AtrInfoTable(AttributeName.MASKSEPARATION, 0x33333111, AttributeInfo.EnumAttributeType.string, null, null);
        atrInfoTable[18] = new AtrInfoTable(AttributeName.MASK, 0x33333111, AttributeInfo.EnumAttributeType.enumeration, EnumMask.getEnum(0), null);
    }
    
    @Override
	protected AttributeInfo getTheAttributeInfo()
    {
        return super.getTheAttributeInfo().updateReplace(atrInfoTable);
    }



    /**
     * Constructor for JDFAutoStripCellParams
     * @param myOwnerDocument
     * @param qualifiedName
     */
    protected JDFAutoStripCellParams(
        CoreDocumentImpl myOwnerDocument,
        String qualifiedName)
    {
        super(myOwnerDocument, qualifiedName);
    }

    /**
     * Constructor for JDFAutoStripCellParams
     * @param myOwnerDocument
     * @param myNamespaceURI
     * @param qualifiedName
     */
    protected JDFAutoStripCellParams(
        CoreDocumentImpl myOwnerDocument,
        String myNamespaceURI,
        String qualifiedName)
    {
        super(myOwnerDocument, myNamespaceURI, qualifiedName);
    }

    /**
     * Constructor for JDFAutoStripCellParams
     * @param myOwnerDocument
     * @param myNamespaceURI
     * @param qualifiedName
     * @param myLocalName
     */
    protected JDFAutoStripCellParams(
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
        return " JDFAutoStripCellParams[  --> " + super.toString() + " ]";
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

            public static final EnumSides OneSided = new EnumSides("OneSided");
            public static final EnumSides TwoSidedHeadToHead = new EnumSides("TwoSidedHeadToHead");
            public static final EnumSides TwoSidedHeadToFoot = new EnumSides("TwoSidedHeadToFoot");
        }      



        /**
        * Enumeration strings for Mask
        */

        public static class EnumMask extends ValuedEnum
        {
            private static final long serialVersionUID = 1L;
            private static int m_startValue = 0;

            private EnumMask(String name)
            {
                super(name, m_startValue++);
            }

            public static EnumMask getEnum(String enumName)
            {
                return (EnumMask) getEnum(EnumMask.class, enumName);
            }

            public static EnumMask getEnum(int enumValue)
            {
                return (EnumMask) getEnum(EnumMask.class, enumValue);
            }

            public static Map getEnumMap()
            {
                return getEnumMap(EnumMask.class);
            }

            public static List getEnumList()
            {
                return getEnumList(EnumMask.class);
            }

            public static Iterator iterator()
            {
                return iterator(EnumMask.class);
            }

            public static final EnumMask None = new EnumMask("None");
            public static final EnumMask TrimBox = new EnumMask("TrimBox");
            public static final EnumMask BleedBox = new EnumMask("BleedBox");
            public static final EnumMask SourceTrimBox = new EnumMask("SourceTrimBox");
            public static final EnumMask SourceBleedBox = new EnumMask("SourceBleedBox");
            public static final EnumMask PDL = new EnumMask("PDL");
            public static final EnumMask DieCut = new EnumMask("DieCut");
            public static final EnumMask DieBleed = new EnumMask("DieBleed");
        }      



/* ************************************************************************
 * Attribute getter / setter
 * ************************************************************************
 */
        
        /* ---------------------------------------------------------------------
        Methods for Attribute BleedFace
        --------------------------------------------------------------------- */
        /**
          * (36) set attribute BleedFace
          * @param value: the value to set the attribute to
          */
        public void setBleedFace(double value)
        {
            setAttribute(AttributeName.BLEEDFACE, value, null);
        }

        /**
          * (17) get double attribute BleedFace
          * @return double the value of the attribute
          */
        public double getBleedFace()
        {
            return getRealAttribute(AttributeName.BLEEDFACE, null, 0.0);
        }

        
        /* ---------------------------------------------------------------------
        Methods for Attribute BleedSpine
        --------------------------------------------------------------------- */
        /**
          * (36) set attribute BleedSpine
          * @param value: the value to set the attribute to
          */
        public void setBleedSpine(double value)
        {
            setAttribute(AttributeName.BLEEDSPINE, value, null);
        }

        /**
          * (17) get double attribute BleedSpine
          * @return double the value of the attribute
          */
        public double getBleedSpine()
        {
            return getRealAttribute(AttributeName.BLEEDSPINE, null, 0.0);
        }

        
        /* ---------------------------------------------------------------------
        Methods for Attribute BleedHead
        --------------------------------------------------------------------- */
        /**
          * (36) set attribute BleedHead
          * @param value: the value to set the attribute to
          */
        public void setBleedHead(double value)
        {
            setAttribute(AttributeName.BLEEDHEAD, value, null);
        }

        /**
          * (17) get double attribute BleedHead
          * @return double the value of the attribute
          */
        public double getBleedHead()
        {
            return getRealAttribute(AttributeName.BLEEDHEAD, null, 0.0);
        }

        
        /* ---------------------------------------------------------------------
        Methods for Attribute BleedFoot
        --------------------------------------------------------------------- */
        /**
          * (36) set attribute BleedFoot
          * @param value: the value to set the attribute to
          */
        public void setBleedFoot(double value)
        {
            setAttribute(AttributeName.BLEEDFOOT, value, null);
        }

        /**
          * (17) get double attribute BleedFoot
          * @return double the value of the attribute
          */
        public double getBleedFoot()
        {
            return getRealAttribute(AttributeName.BLEEDFOOT, null, 0.0);
        }

        
        /* ---------------------------------------------------------------------
        Methods for Attribute TrimFace
        --------------------------------------------------------------------- */
        /**
          * (36) set attribute TrimFace
          * @param value: the value to set the attribute to
          */
        public void setTrimFace(double value)
        {
            setAttribute(AttributeName.TRIMFACE, value, null);
        }

        /**
          * (17) get double attribute TrimFace
          * @return double the value of the attribute
          */
        public double getTrimFace()
        {
            return getRealAttribute(AttributeName.TRIMFACE, null, 0.0);
        }

        
        /* ---------------------------------------------------------------------
        Methods for Attribute Spine
        --------------------------------------------------------------------- */
        /**
          * (36) set attribute Spine
          * @param value: the value to set the attribute to
          */
        public void setSpine(double value)
        {
            setAttribute(AttributeName.SPINE, value, null);
        }

        /**
          * (17) get double attribute Spine
          * @return double the value of the attribute
          */
        public double getSpine()
        {
            return getRealAttribute(AttributeName.SPINE, null, 0.0);
        }

        
        /* ---------------------------------------------------------------------
        Methods for Attribute TrimHead
        --------------------------------------------------------------------- */
        /**
          * (36) set attribute TrimHead
          * @param value: the value to set the attribute to
          */
        public void setTrimHead(double value)
        {
            setAttribute(AttributeName.TRIMHEAD, value, null);
        }

        /**
          * (17) get double attribute TrimHead
          * @return double the value of the attribute
          */
        public double getTrimHead()
        {
            return getRealAttribute(AttributeName.TRIMHEAD, null, 0.0);
        }

        
        /* ---------------------------------------------------------------------
        Methods for Attribute TrimFoot
        --------------------------------------------------------------------- */
        /**
          * (36) set attribute TrimFoot
          * @param value: the value to set the attribute to
          */
        public void setTrimFoot(double value)
        {
            setAttribute(AttributeName.TRIMFOOT, value, null);
        }

        /**
          * (17) get double attribute TrimFoot
          * @return double the value of the attribute
          */
        public double getTrimFoot()
        {
            return getRealAttribute(AttributeName.TRIMFOOT, null, 0.0);
        }

        
        /* ---------------------------------------------------------------------
        Methods for Attribute FrontOverfold
        --------------------------------------------------------------------- */
        /**
          * (36) set attribute FrontOverfold
          * @param value: the value to set the attribute to
          */
        public void setFrontOverfold(double value)
        {
            setAttribute(AttributeName.FRONTOVERFOLD, value, null);
        }

        /**
          * (17) get double attribute FrontOverfold
          * @return double the value of the attribute
          */
        public double getFrontOverfold()
        {
            return getRealAttribute(AttributeName.FRONTOVERFOLD, null, 0.0);
        }

        
        /* ---------------------------------------------------------------------
        Methods for Attribute BackOverfold
        --------------------------------------------------------------------- */
        /**
          * (36) set attribute BackOverfold
          * @param value: the value to set the attribute to
          */
        public void setBackOverfold(double value)
        {
            setAttribute(AttributeName.BACKOVERFOLD, value, null);
        }

        /**
          * (17) get double attribute BackOverfold
          * @return double the value of the attribute
          */
        public double getBackOverfold()
        {
            return getRealAttribute(AttributeName.BACKOVERFOLD, null, 0.0);
        }

        
        /* ---------------------------------------------------------------------
        Methods for Attribute MillingDepth
        --------------------------------------------------------------------- */
        /**
          * (36) set attribute MillingDepth
          * @param value: the value to set the attribute to
          */
        public void setMillingDepth(double value)
        {
            setAttribute(AttributeName.MILLINGDEPTH, value, null);
        }

        /**
          * (17) get double attribute MillingDepth
          * @return double the value of the attribute
          */
        public double getMillingDepth()
        {
            return getRealAttribute(AttributeName.MILLINGDEPTH, null, 0.0);
        }

        
        /* ---------------------------------------------------------------------
        Methods for Attribute CutWidthHead
        --------------------------------------------------------------------- */
        /**
          * (36) set attribute CutWidthHead
          * @param value: the value to set the attribute to
          */
        public void setCutWidthHead(double value)
        {
            setAttribute(AttributeName.CUTWIDTHHEAD, value, null);
        }

        /**
          * (17) get double attribute CutWidthHead
          * @return double the value of the attribute
          */
        public double getCutWidthHead()
        {
            return getRealAttribute(AttributeName.CUTWIDTHHEAD, null, 0.0);
        }

        
        /* ---------------------------------------------------------------------
        Methods for Attribute CutWidthFoot
        --------------------------------------------------------------------- */
        /**
          * (36) set attribute CutWidthFoot
          * @param value: the value to set the attribute to
          */
        public void setCutWidthFoot(double value)
        {
            setAttribute(AttributeName.CUTWIDTHFOOT, value, null);
        }

        /**
          * (17) get double attribute CutWidthFoot
          * @return double the value of the attribute
          */
        public double getCutWidthFoot()
        {
            return getRealAttribute(AttributeName.CUTWIDTHFOOT, null, 0.0);
        }

        
        /* ---------------------------------------------------------------------
        Methods for Attribute TrimSize
        --------------------------------------------------------------------- */
        /**
          * (36) set attribute TrimSize
          * @param value: the value to set the attribute to
          */
        public void setTrimSize(JDFXYPair value)
        {
            setAttribute(AttributeName.TRIMSIZE, value, null);
        }

        /**
          * (20) get JDFXYPair attribute TrimSize
          * @return JDFXYPair the value of the attribute, null if a the
          *         attribute value is not a valid to create a JDFXYPair
          */
        public JDFXYPair getTrimSize()
        {
            String strAttrName = "";
            JDFXYPair nPlaceHolder = null;
            strAttrName = getAttribute(AttributeName.TRIMSIZE, null, JDFConstants.EMPTYSTRING);
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
        Methods for Attribute Creep
        --------------------------------------------------------------------- */
        /**
          * (36) set attribute Creep
          * @param value: the value to set the attribute to
          */
        public void setCreep(JDFXYPair value)
        {
            setAttribute(AttributeName.CREEP, value, null);
        }

        /**
          * (20) get JDFXYPair attribute Creep
          * @return JDFXYPair the value of the attribute, null if a the
          *         attribute value is not a valid to create a JDFXYPair
          */
        public JDFXYPair getCreep()
        {
            String strAttrName = "";
            JDFXYPair nPlaceHolder = null;
            strAttrName = getAttribute(AttributeName.CREEP, null, JDFConstants.EMPTYSTRING);
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
            return EnumSides.getEnum(getAttribute(AttributeName.SIDES, null, null));
        }

        
        /* ---------------------------------------------------------------------
        Methods for Attribute MaskBleed
        --------------------------------------------------------------------- */
        /**
          * (36) set attribute MaskBleed
          * @param value: the value to set the attribute to
          */
        public void setMaskBleed(double value)
        {
            setAttribute(AttributeName.MASKBLEED, value, null);
        }

        /**
          * (17) get double attribute MaskBleed
          * @return double the value of the attribute
          */
        public double getMaskBleed()
        {
            return getRealAttribute(AttributeName.MASKBLEED, null, 0.0);
        }

        
        /* ---------------------------------------------------------------------
        Methods for Attribute MaskSeparation
        --------------------------------------------------------------------- */
        /**
          * (36) set attribute MaskSeparation
          * @param value: the value to set the attribute to
          */
        public void setMaskSeparation(String value)
        {
            setAttribute(AttributeName.MASKSEPARATION, value, null);
        }

        /**
          * (23) get String attribute MaskSeparation
          * @return the value of the attribute
          */
        public String getMaskSeparation()
        {
            return getAttribute(AttributeName.MASKSEPARATION, null, JDFConstants.EMPTYSTRING);
        }

        
        /* ---------------------------------------------------------------------
        Methods for Attribute Mask
        --------------------------------------------------------------------- */
        /**
          * (5) set attribute Mask
          * @param enumVar: the enumVar to set the attribute to
          */
        public void setMask(EnumMask enumVar)
        {
            setAttribute(AttributeName.MASK, enumVar==null ? null : enumVar.getName(), null);
        }

        /**
          * (9) get attribute Mask
          * @return the value of the attribute
          */
        public EnumMask getMask()
        {
            return EnumMask.getEnum(getAttribute(AttributeName.MASK, null, null));
        }

}// end namespace JDF
