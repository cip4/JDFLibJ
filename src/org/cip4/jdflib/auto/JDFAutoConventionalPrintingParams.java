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
import org.cip4.jdflib.datatypes.JDFIntegerRangeList;
import org.cip4.jdflib.resource.JDFResource;
import org.cip4.jdflib.resource.process.JDFApprovalParams;
import org.cip4.jdflib.resource.process.prepress.JDFInk;

public abstract class JDFAutoConventionalPrintingParams extends JDFResource
{

    private static final long serialVersionUID = 1L;

    private static AtrInfoTable[] atrInfoTable = new AtrInfoTable[18];
    static
    {
        atrInfoTable[0] = new AtrInfoTable(AttributeName.DIRECTPROOF, 0x33333333, AttributeInfo.EnumAttributeType.boolean_, null, "false");
        atrInfoTable[1] = new AtrInfoTable(AttributeName.DRYING, 0x33333333, AttributeInfo.EnumAttributeType.enumeration, EnumDrying.getEnum(0), null);
        atrInfoTable[2] = new AtrInfoTable(AttributeName.FIRSTSURFACE, 0x33333333, AttributeInfo.EnumAttributeType.enumeration, EnumFirstSurface.getEnum(0), null);
        atrInfoTable[3] = new AtrInfoTable(AttributeName.FOUNTAINSOLUTION, 0x33333333, AttributeInfo.EnumAttributeType.enumeration, EnumFountainSolution.getEnum(0), null);
        atrInfoTable[4] = new AtrInfoTable(AttributeName.MEDIALOCATION, 0x33333333, AttributeInfo.EnumAttributeType.string, null, null);
        atrInfoTable[5] = new AtrInfoTable(AttributeName.MODULEAVAILABLEINDEX, 0x44443331, AttributeInfo.EnumAttributeType.IntegerRangeList, null, null);
        atrInfoTable[6] = new AtrInfoTable(AttributeName.MODULEDRYING, 0x33333333, AttributeInfo.EnumAttributeType.enumeration, EnumModuleDrying.getEnum(0), null);
        atrInfoTable[7] = new AtrInfoTable(AttributeName.MODULEINDEX, 0x44443333, AttributeInfo.EnumAttributeType.IntegerRangeList, null, null);
        atrInfoTable[8] = new AtrInfoTable(AttributeName.NONPRINTABLEMARGINBOTTOM, 0x33333333, AttributeInfo.EnumAttributeType.double_, null, null);
        atrInfoTable[9] = new AtrInfoTable(AttributeName.NONPRINTABLEMARGINLEFT, 0x33333111, AttributeInfo.EnumAttributeType.double_, null, null);
        atrInfoTable[10] = new AtrInfoTable(AttributeName.NONPRINTABLEMARGINRIGHT, 0x33333111, AttributeInfo.EnumAttributeType.double_, null, null);
        atrInfoTable[11] = new AtrInfoTable(AttributeName.NONPRINTABLEMARGINTOP, 0x33333111, AttributeInfo.EnumAttributeType.double_, null, null);
        atrInfoTable[12] = new AtrInfoTable(AttributeName.PERFECTINGMODULE, 0x33333331, AttributeInfo.EnumAttributeType.integer, null, null);
        atrInfoTable[13] = new AtrInfoTable(AttributeName.POWDER, 0x33333333, AttributeInfo.EnumAttributeType.double_, null, null);
        atrInfoTable[14] = new AtrInfoTable(AttributeName.PRINTINGTYPE, 0x33333333, AttributeInfo.EnumAttributeType.enumeration, EnumPrintingType.getEnum(0), null);
        atrInfoTable[15] = new AtrInfoTable(AttributeName.SHEETLAY, 0x33333333, AttributeInfo.EnumAttributeType.enumeration, EnumSheetLay.getEnum(0), null);
        atrInfoTable[16] = new AtrInfoTable(AttributeName.SPEED, 0x33333333, AttributeInfo.EnumAttributeType.double_, null, null);
        atrInfoTable[17] = new AtrInfoTable(AttributeName.WORKSTYLE, 0x33333333, AttributeInfo.EnumAttributeType.enumeration, EnumWorkStyle.getEnum(0), null);
    }
    
    @Override
	protected AttributeInfo getTheAttributeInfo()
    {
        return super.getTheAttributeInfo().updateReplace(atrInfoTable);
    }


    private static ElemInfoTable[] elemInfoTable = new ElemInfoTable[2];
    static
    {
        elemInfoTable[0] = new ElemInfoTable(ElementName.INK, 0x44443333);
        elemInfoTable[1] = new ElemInfoTable(ElementName.APPROVALPARAMS, 0x66666611);
    }
    
    @Override
	protected ElementInfo getTheElementInfo()
    {
        return super.getTheElementInfo().updateReplace(elemInfoTable);
    }



    /**
     * Constructor for JDFAutoConventionalPrintingParams
     * @param myOwnerDocument
     * @param qualifiedName
     */
    protected JDFAutoConventionalPrintingParams(
        CoreDocumentImpl myOwnerDocument,
        String qualifiedName)
    {
        super(myOwnerDocument, qualifiedName);
    }

    /**
     * Constructor for JDFAutoConventionalPrintingParams
     * @param myOwnerDocument
     * @param myNamespaceURI
     * @param qualifiedName
     */
    protected JDFAutoConventionalPrintingParams(
        CoreDocumentImpl myOwnerDocument,
        String myNamespaceURI,
        String qualifiedName)
    {
        super(myOwnerDocument, myNamespaceURI, qualifiedName);
    }

    /**
     * Constructor for JDFAutoConventionalPrintingParams
     * @param myOwnerDocument
     * @param myNamespaceURI
     * @param qualifiedName
     * @param myLocalName
     */
    protected JDFAutoConventionalPrintingParams(
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
        return " JDFAutoConventionalPrintingParams[  --> " + super.toString() + " ]";
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
        * Enumeration strings for Drying
        */

        public static class EnumDrying extends ValuedEnum
        {
            private static final long serialVersionUID = 1L;
            private static int m_startValue = 0;

            private EnumDrying(String name)
            {
                super(name, m_startValue++);
            }

            public static EnumDrying getEnum(String enumName)
            {
                return (EnumDrying) getEnum(EnumDrying.class, enumName);
            }

            public static EnumDrying getEnum(int enumValue)
            {
                return (EnumDrying) getEnum(EnumDrying.class, enumValue);
            }

            public static Map getEnumMap()
            {
                return getEnumMap(EnumDrying.class);
            }

            public static List getEnumList()
            {
                return getEnumList(EnumDrying.class);
            }

            public static Iterator iterator()
            {
                return iterator(EnumDrying.class);
            }

            public static final EnumDrying UV = new EnumDrying("UV");
            public static final EnumDrying Heatset = new EnumDrying("Heatset");
            public static final EnumDrying IR = new EnumDrying("IR");
            public static final EnumDrying On = new EnumDrying("On");
            public static final EnumDrying Off = new EnumDrying("Off");
        }      



        /**
        * Enumeration strings for FirstSurface
        */

        public static class EnumFirstSurface extends ValuedEnum
        {
            private static final long serialVersionUID = 1L;
            private static int m_startValue = 0;

            private EnumFirstSurface(String name)
            {
                super(name, m_startValue++);
            }

            public static EnumFirstSurface getEnum(String enumName)
            {
                return (EnumFirstSurface) getEnum(EnumFirstSurface.class, enumName);
            }

            public static EnumFirstSurface getEnum(int enumValue)
            {
                return (EnumFirstSurface) getEnum(EnumFirstSurface.class, enumValue);
            }

            public static Map getEnumMap()
            {
                return getEnumMap(EnumFirstSurface.class);
            }

            public static List getEnumList()
            {
                return getEnumList(EnumFirstSurface.class);
            }

            public static Iterator iterator()
            {
                return iterator(EnumFirstSurface.class);
            }

            public static final EnumFirstSurface Either = new EnumFirstSurface("Either");
            public static final EnumFirstSurface Front = new EnumFirstSurface("Front");
            public static final EnumFirstSurface Back = new EnumFirstSurface("Back");
        }      



        /**
        * Enumeration strings for FountainSolution
        */

        public static class EnumFountainSolution extends ValuedEnum
        {
            private static final long serialVersionUID = 1L;
            private static int m_startValue = 0;

            private EnumFountainSolution(String name)
            {
                super(name, m_startValue++);
            }

            public static EnumFountainSolution getEnum(String enumName)
            {
                return (EnumFountainSolution) getEnum(EnumFountainSolution.class, enumName);
            }

            public static EnumFountainSolution getEnum(int enumValue)
            {
                return (EnumFountainSolution) getEnum(EnumFountainSolution.class, enumValue);
            }

            public static Map getEnumMap()
            {
                return getEnumMap(EnumFountainSolution.class);
            }

            public static List getEnumList()
            {
                return getEnumList(EnumFountainSolution.class);
            }

            public static Iterator iterator()
            {
                return iterator(EnumFountainSolution.class);
            }

            public static final EnumFountainSolution On = new EnumFountainSolution("On");
            public static final EnumFountainSolution Off = new EnumFountainSolution("Off");
        }      



        /**
        * Enumeration strings for ModuleDrying
        */

        public static class EnumModuleDrying extends ValuedEnum
        {
            private static final long serialVersionUID = 1L;
            private static int m_startValue = 0;

            private EnumModuleDrying(String name)
            {
                super(name, m_startValue++);
            }

            public static EnumModuleDrying getEnum(String enumName)
            {
                return (EnumModuleDrying) getEnum(EnumModuleDrying.class, enumName);
            }

            public static EnumModuleDrying getEnum(int enumValue)
            {
                return (EnumModuleDrying) getEnum(EnumModuleDrying.class, enumValue);
            }

            public static Map getEnumMap()
            {
                return getEnumMap(EnumModuleDrying.class);
            }

            public static List getEnumList()
            {
                return getEnumList(EnumModuleDrying.class);
            }

            public static Iterator iterator()
            {
                return iterator(EnumModuleDrying.class);
            }

            public static final EnumModuleDrying UV = new EnumModuleDrying("UV");
            public static final EnumModuleDrying Heatset = new EnumModuleDrying("Heatset");
            public static final EnumModuleDrying IR = new EnumModuleDrying("IR");
            public static final EnumModuleDrying On = new EnumModuleDrying("On");
            public static final EnumModuleDrying Off = new EnumModuleDrying("Off");
        }      



        /**
        * Enumeration strings for PrintingType
        */

        public static class EnumPrintingType extends ValuedEnum
        {
            private static final long serialVersionUID = 1L;
            private static int m_startValue = 0;

            private EnumPrintingType(String name)
            {
                super(name, m_startValue++);
            }

            public static EnumPrintingType getEnum(String enumName)
            {
                return (EnumPrintingType) getEnum(EnumPrintingType.class, enumName);
            }

            public static EnumPrintingType getEnum(int enumValue)
            {
                return (EnumPrintingType) getEnum(EnumPrintingType.class, enumValue);
            }

            public static Map getEnumMap()
            {
                return getEnumMap(EnumPrintingType.class);
            }

            public static List getEnumList()
            {
                return getEnumList(EnumPrintingType.class);
            }

            public static Iterator iterator()
            {
                return iterator(EnumPrintingType.class);
            }

            public static final EnumPrintingType ContinuousFed = new EnumPrintingType("ContinuousFed");
            public static final EnumPrintingType SheetFed = new EnumPrintingType("SheetFed");
            public static final EnumPrintingType WebFed = new EnumPrintingType("WebFed");
            public static final EnumPrintingType WebMultiple = new EnumPrintingType("WebMultiple");
            public static final EnumPrintingType WebSingle = new EnumPrintingType("WebSingle");
        }      



        /**
        * Enumeration strings for SheetLay
        */

        public static class EnumSheetLay extends ValuedEnum
        {
            private static final long serialVersionUID = 1L;
            private static int m_startValue = 0;

            private EnumSheetLay(String name)
            {
                super(name, m_startValue++);
            }

            public static EnumSheetLay getEnum(String enumName)
            {
                return (EnumSheetLay) getEnum(EnumSheetLay.class, enumName);
            }

            public static EnumSheetLay getEnum(int enumValue)
            {
                return (EnumSheetLay) getEnum(EnumSheetLay.class, enumValue);
            }

            public static Map getEnumMap()
            {
                return getEnumMap(EnumSheetLay.class);
            }

            public static List getEnumList()
            {
                return getEnumList(EnumSheetLay.class);
            }

            public static Iterator iterator()
            {
                return iterator(EnumSheetLay.class);
            }

            public static final EnumSheetLay Left = new EnumSheetLay("Left");
            public static final EnumSheetLay Right = new EnumSheetLay("Right");
            public static final EnumSheetLay Center = new EnumSheetLay("Center");
        }      



        /**
        * Enumeration strings for WorkStyle
        */

        public static class EnumWorkStyle extends ValuedEnum
        {
            private static final long serialVersionUID = 1L;
            private static int m_startValue = 0;

            private EnumWorkStyle(String name)
            {
                super(name, m_startValue++);
            }

            public static EnumWorkStyle getEnum(String enumName)
            {
                return (EnumWorkStyle) getEnum(EnumWorkStyle.class, enumName);
            }

            public static EnumWorkStyle getEnum(int enumValue)
            {
                return (EnumWorkStyle) getEnum(EnumWorkStyle.class, enumValue);
            }

            public static Map getEnumMap()
            {
                return getEnumMap(EnumWorkStyle.class);
            }

            public static List getEnumList()
            {
                return getEnumList(EnumWorkStyle.class);
            }

            public static Iterator iterator()
            {
                return iterator(EnumWorkStyle.class);
            }

            public static final EnumWorkStyle Simplex = new EnumWorkStyle("Simplex");
            public static final EnumWorkStyle Perfecting = new EnumWorkStyle("Perfecting");
            public static final EnumWorkStyle WorkAndBack = new EnumWorkStyle("WorkAndBack");
            public static final EnumWorkStyle WorkAndTurn = new EnumWorkStyle("WorkAndTurn");
            public static final EnumWorkStyle WorkAndTumble = new EnumWorkStyle("WorkAndTumble");
            public static final EnumWorkStyle WorkAndTwist = new EnumWorkStyle("WorkAndTwist");
        }      



/* ************************************************************************
 * Attribute getter / setter
 * ************************************************************************
 */
        
        /* ---------------------------------------------------------------------
        Methods for Attribute DirectProof
        --------------------------------------------------------------------- */
        /**
          * (36) set attribute DirectProof
          * @param value: the value to set the attribute to
          */
        public void setDirectProof(boolean value)
        {
            setAttribute(AttributeName.DIRECTPROOF, value, null);
        }

        /**
          * (18) get boolean attribute DirectProof
          * @return boolean the value of the attribute
          */
        public boolean getDirectProof()
        {
            return getBoolAttribute(AttributeName.DIRECTPROOF, null, false);
        }

        
        /* ---------------------------------------------------------------------
        Methods for Attribute Drying
        --------------------------------------------------------------------- */
        /**
          * (5) set attribute Drying
          * @param enumVar: the enumVar to set the attribute to
          */
        public void setDrying(EnumDrying enumVar)
        {
            setAttribute(AttributeName.DRYING, enumVar==null ? null : enumVar.getName(), null);
        }

        /**
          * (9) get attribute Drying
          * @return the value of the attribute
          */
        public EnumDrying getDrying()
        {
            return EnumDrying.getEnum(getAttribute(AttributeName.DRYING, null, null));
        }

        
        /* ---------------------------------------------------------------------
        Methods for Attribute FirstSurface
        --------------------------------------------------------------------- */
        /**
          * (5) set attribute FirstSurface
          * @param enumVar: the enumVar to set the attribute to
          */
        public void setFirstSurface(EnumFirstSurface enumVar)
        {
            setAttribute(AttributeName.FIRSTSURFACE, enumVar==null ? null : enumVar.getName(), null);
        }

        /**
          * (9) get attribute FirstSurface
          * @return the value of the attribute
          */
        public EnumFirstSurface getFirstSurface()
        {
            return EnumFirstSurface.getEnum(getAttribute(AttributeName.FIRSTSURFACE, null, null));
        }

        
        /* ---------------------------------------------------------------------
        Methods for Attribute FountainSolution
        --------------------------------------------------------------------- */
        /**
          * (5) set attribute FountainSolution
          * @param enumVar: the enumVar to set the attribute to
          */
        public void setFountainSolution(EnumFountainSolution enumVar)
        {
            setAttribute(AttributeName.FOUNTAINSOLUTION, enumVar==null ? null : enumVar.getName(), null);
        }

        /**
          * (9) get attribute FountainSolution
          * @return the value of the attribute
          */
        public EnumFountainSolution getFountainSolution()
        {
            return EnumFountainSolution.getEnum(getAttribute(AttributeName.FOUNTAINSOLUTION, null, null));
        }

        
        /* ---------------------------------------------------------------------
        Methods for Attribute MediaLocation
        --------------------------------------------------------------------- */
        /**
          * (36) set attribute MediaLocation
          * @param value: the value to set the attribute to
          */
        public void setMediaLocation(String value)
        {
            setAttribute(AttributeName.MEDIALOCATION, value, null);
        }

        /**
          * (23) get String attribute MediaLocation
          * @return the value of the attribute
          */
        public String getMediaLocation()
        {
            return getAttribute(AttributeName.MEDIALOCATION, null, JDFConstants.EMPTYSTRING);
        }

        
        /* ---------------------------------------------------------------------
        Methods for Attribute ModuleAvailableIndex
        --------------------------------------------------------------------- */
        /**
          * (36) set attribute ModuleAvailableIndex
          * @param value: the value to set the attribute to
          */
        public void setModuleAvailableIndex(JDFIntegerRangeList value)
        {
            setAttribute(AttributeName.MODULEAVAILABLEINDEX, value, null);
        }

        /**
          * (20) get JDFIntegerRangeList attribute ModuleAvailableIndex
          * @return JDFIntegerRangeList the value of the attribute, null if a the
          *         attribute value is not a valid to create a JDFIntegerRangeList
          */
        public JDFIntegerRangeList getModuleAvailableIndex()
        {
            String strAttrName = "";
            JDFIntegerRangeList nPlaceHolder = null;
            strAttrName = getAttribute(AttributeName.MODULEAVAILABLEINDEX, null, JDFConstants.EMPTYSTRING);
            try
            {
                nPlaceHolder = new JDFIntegerRangeList(strAttrName);
            }
            catch(DataFormatException e)
            {
                return null;
            }
            return nPlaceHolder;
        }

        
        /* ---------------------------------------------------------------------
        Methods for Attribute ModuleDrying
        --------------------------------------------------------------------- */
        /**
          * (5) set attribute ModuleDrying
          * @param enumVar: the enumVar to set the attribute to
          */
        public void setModuleDrying(EnumModuleDrying enumVar)
        {
            setAttribute(AttributeName.MODULEDRYING, enumVar==null ? null : enumVar.getName(), null);
        }

        /**
          * (9) get attribute ModuleDrying
          * @return the value of the attribute
          */
        public EnumModuleDrying getModuleDrying()
        {
            return EnumModuleDrying.getEnum(getAttribute(AttributeName.MODULEDRYING, null, null));
        }

        
        /* ---------------------------------------------------------------------
        Methods for Attribute ModuleIndex
        --------------------------------------------------------------------- */
        /**
          * (36) set attribute ModuleIndex
          * @param value: the value to set the attribute to
          */
        public void setModuleIndex(JDFIntegerRangeList value)
        {
            setAttribute(AttributeName.MODULEINDEX, value, null);
        }

        /**
          * (20) get JDFIntegerRangeList attribute ModuleIndex
          * @return JDFIntegerRangeList the value of the attribute, null if a the
          *         attribute value is not a valid to create a JDFIntegerRangeList
          */
        public JDFIntegerRangeList getModuleIndex()
        {
            String strAttrName = "";
            JDFIntegerRangeList nPlaceHolder = null;
            strAttrName = getAttribute(AttributeName.MODULEINDEX, null, JDFConstants.EMPTYSTRING);
            try
            {
                nPlaceHolder = new JDFIntegerRangeList(strAttrName);
            }
            catch(DataFormatException e)
            {
                return null;
            }
            return nPlaceHolder;
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
        Methods for Attribute PerfectingModule
        --------------------------------------------------------------------- */
        /**
          * (36) set attribute PerfectingModule
          * @param value: the value to set the attribute to
          */
        public void setPerfectingModule(int value)
        {
            setAttribute(AttributeName.PERFECTINGMODULE, value, null);
        }

        /**
          * (15) get int attribute PerfectingModule
          * @return int the value of the attribute
          */
        public int getPerfectingModule()
        {
            return getIntAttribute(AttributeName.PERFECTINGMODULE, null, 0);
        }

        
        /* ---------------------------------------------------------------------
        Methods for Attribute Powder
        --------------------------------------------------------------------- */
        /**
          * (36) set attribute Powder
          * @param value: the value to set the attribute to
          */
        public void setPowder(double value)
        {
            setAttribute(AttributeName.POWDER, value, null);
        }

        /**
          * (17) get double attribute Powder
          * @return double the value of the attribute
          */
        public double getPowder()
        {
            return getRealAttribute(AttributeName.POWDER, null, 0.0);
        }

        
        /* ---------------------------------------------------------------------
        Methods for Attribute PrintingType
        --------------------------------------------------------------------- */
        /**
          * (5) set attribute PrintingType
          * @param enumVar: the enumVar to set the attribute to
          */
        public void setPrintingType(EnumPrintingType enumVar)
        {
            setAttribute(AttributeName.PRINTINGTYPE, enumVar==null ? null : enumVar.getName(), null);
        }

        /**
          * (9) get attribute PrintingType
          * @return the value of the attribute
          */
        public EnumPrintingType getPrintingType()
        {
            return EnumPrintingType.getEnum(getAttribute(AttributeName.PRINTINGTYPE, null, null));
        }

        
        /* ---------------------------------------------------------------------
        Methods for Attribute SheetLay
        --------------------------------------------------------------------- */
        /**
          * (5) set attribute SheetLay
          * @param enumVar: the enumVar to set the attribute to
          */
        public void setSheetLay(EnumSheetLay enumVar)
        {
            setAttribute(AttributeName.SHEETLAY, enumVar==null ? null : enumVar.getName(), null);
        }

        /**
          * (9) get attribute SheetLay
          * @return the value of the attribute
          */
        public EnumSheetLay getSheetLay()
        {
            return EnumSheetLay.getEnum(getAttribute(AttributeName.SHEETLAY, null, null));
        }

        
        /* ---------------------------------------------------------------------
        Methods for Attribute Speed
        --------------------------------------------------------------------- */
        /**
          * (36) set attribute Speed
          * @param value: the value to set the attribute to
          */
        public void setSpeed(double value)
        {
            setAttribute(AttributeName.SPEED, value, null);
        }

        /**
          * (17) get double attribute Speed
          * @return double the value of the attribute
          */
        public double getSpeed()
        {
            return getRealAttribute(AttributeName.SPEED, null, 0.0);
        }

        
        /* ---------------------------------------------------------------------
        Methods for Attribute WorkStyle
        --------------------------------------------------------------------- */
        /**
          * (5) set attribute WorkStyle
          * @param enumVar: the enumVar to set the attribute to
          */
        public void setWorkStyle(EnumWorkStyle enumVar)
        {
            setAttribute(AttributeName.WORKSTYLE, enumVar==null ? null : enumVar.getName(), null);
        }

        /**
          * (9) get attribute WorkStyle
          * @return the value of the attribute
          */
        public EnumWorkStyle getWorkStyle()
        {
            return EnumWorkStyle.getEnum(getAttribute(AttributeName.WORKSTYLE, null, null));
        }

/* ***********************************************************************
 * Element getter / setter
 * ***********************************************************************
 */

    /** (26) getCreateInk
     * 
     * @param iSkip number of elements to skip
     * @return JDFInk the element
     */
    public JDFInk getCreateInk(int iSkip)
    {
        return (JDFInk)getCreateElement_KElement(ElementName.INK, null, iSkip);
    }

    /**
     * (27) const get element Ink
     * @param iSkip number of elements to skip
     * @return JDFInk the element
     * default is getInk(0)     */
    public JDFInk getInk(int iSkip)
    {
        return (JDFInk) getElement(ElementName.INK, null, iSkip);
    }

    /**
     * Get all Ink from the current element
     * 
     * @return Collection<JDFInk>, null if none are available
     */
    public Collection<JDFInk> getAllInk()
    {
        final VElement vc = getChildElementVector(ElementName.INK, null);
        if (vc == null || vc.size() == 0)
        {
            return null;
        }

        final Vector<JDFInk> v = new Vector<JDFInk>();
        for (int i = 0; i < vc.size(); i++)
        {
            v.add((JDFInk) vc.get(i));
        }

        return v;
    }

    /**
     * (30) append element Ink
     */
    public JDFInk appendInk() throws JDFException
    {
        return (JDFInk) appendElement(ElementName.INK, null);
    }

    /**
      * (31) create inter-resource link to refTarget
      * @param refTarget the element that is referenced
      */
    public void refInk(JDFInk refTarget)
    {
        refElement(refTarget);
    }

    /**
     * (24) const get element ApprovalParams
     * @return JDFApprovalParams the element
     */
    public JDFApprovalParams getApprovalParams()
    {
        return (JDFApprovalParams) getElement(ElementName.APPROVALPARAMS, null, 0);
    }

    /** (25) getCreateApprovalParams
     * 
     * @return JDFApprovalParams the element
     */
    public JDFApprovalParams getCreateApprovalParams()
    {
        return (JDFApprovalParams) getCreateElement_KElement(ElementName.APPROVALPARAMS, null, 0);
    }

    /**
     * (29) append element ApprovalParams
     */
    public JDFApprovalParams appendApprovalParams() throws JDFException
    {
        return (JDFApprovalParams) appendElementN(ElementName.APPROVALPARAMS, 1, null);
    }

    /**
      * (31) create inter-resource link to refTarget
      * @param refTarget the element that is referenced
      */
    public void refApprovalParams(JDFApprovalParams refTarget)
    {
        refElement(refTarget);
    }

}// end namespace JDF
