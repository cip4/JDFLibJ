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
import org.cip4.jdflib.core.*;                      
import org.cip4.jdflib.datatypes.*;                 
import org.cip4.jdflib.resource.*;                  
import org.cip4.jdflib.resource.process.*;          
import org.cip4.jdflib.resource.process.postpress.*;

public abstract class JDFAutoComponent extends JDFResource
{

    private static final long serialVersionUID = 1L;

    private static AtrInfoTable[] atrInfoTable = new AtrInfoTable[19];
    static
    {
        atrInfoTable[0] = new AtrInfoTable(AttributeName.COMPONENTTYPE, 0x22222222, AttributeInfo.EnumAttributeType.enumerations, EnumComponentType.getEnum(0), null);
        atrInfoTable[1] = new AtrInfoTable(AttributeName.ASSEMBLYIDS, 0x33333111, AttributeInfo.EnumAttributeType.NMTOKENS, null, null);
        atrInfoTable[2] = new AtrInfoTable(AttributeName.CARTONTOPFLAPS, 0x33333111, AttributeInfo.EnumAttributeType.XYPair, null, null);
        atrInfoTable[3] = new AtrInfoTable(AttributeName.DIMENSIONS, 0x33333333, AttributeInfo.EnumAttributeType.shape, null, null);
        atrInfoTable[4] = new AtrInfoTable(AttributeName.ISWASTE, 0x44443333, AttributeInfo.EnumAttributeType.boolean_, null, null);
        atrInfoTable[5] = new AtrInfoTable(AttributeName.MAXHEAT, 0x33333333, AttributeInfo.EnumAttributeType.double_, null, null);
        atrInfoTable[6] = new AtrInfoTable(AttributeName.OVERFOLD, 0x33333331, AttributeInfo.EnumAttributeType.double_, null, null);
        atrInfoTable[7] = new AtrInfoTable(AttributeName.OVERFOLDSIDE, 0x33333331, AttributeInfo.EnumAttributeType.enumeration, EnumOverfoldSide.getEnum(0), null);
        atrInfoTable[8] = new AtrInfoTable(AttributeName.PAGELISTINDEX, 0x33333111, AttributeInfo.EnumAttributeType.IntegerRangeList, null, null);
        atrInfoTable[9] = new AtrInfoTable(AttributeName.PRODUCTTYPE, 0x33333333, AttributeInfo.EnumAttributeType.NMTOKEN, null, null);
        atrInfoTable[10] = new AtrInfoTable(AttributeName.PRODUCTTYPEDETAILS, 0x33333111, AttributeInfo.EnumAttributeType.string, null, null);
        atrInfoTable[11] = new AtrInfoTable(AttributeName.READERPAGECOUNT, 0x33333331, AttributeInfo.EnumAttributeType.integer, null, null);
        atrInfoTable[12] = new AtrInfoTable(AttributeName.SHEETPART, 0x33333333, AttributeInfo.EnumAttributeType.rectangle, null, null);
        atrInfoTable[13] = new AtrInfoTable(AttributeName.SOURCERIBBON, 0x44444333, AttributeInfo.EnumAttributeType.string, null, null);
        atrInfoTable[14] = new AtrInfoTable(AttributeName.SOURCESHEET, 0x44444333, AttributeInfo.EnumAttributeType.string, null, null);
        atrInfoTable[15] = new AtrInfoTable(AttributeName.SOURCEWEB, 0x44444333, AttributeInfo.EnumAttributeType.string, null, null);
        atrInfoTable[16] = new AtrInfoTable(AttributeName.SPINETHICKNESS, 0x33331111, AttributeInfo.EnumAttributeType.double_, null, null);
        atrInfoTable[17] = new AtrInfoTable(AttributeName.SURFACECOUNT, 0x33333331, AttributeInfo.EnumAttributeType.integer, null, null);
        atrInfoTable[18] = new AtrInfoTable(AttributeName.TRANSFORMATION, 0x44444443, AttributeInfo.EnumAttributeType.matrix, null, null);
    }
    
    @Override
	protected AttributeInfo getTheAttributeInfo()
    {
        return super.getTheAttributeInfo().updateReplace(atrInfoTable);
    }


    private static ElemInfoTable[] elemInfoTable = new ElemInfoTable[9];
    static
    {
        elemInfoTable[0] = new ElemInfoTable(ElementName.ASSEMBLY, 0x66666111);
        elemInfoTable[1] = new ElemInfoTable(ElementName.BUNDLE, 0x66666661);
        elemInfoTable[2] = new ElemInfoTable(ElementName.DISJOINTING, 0x66666666);
        elemInfoTable[3] = new ElemInfoTable(ElementName.SHEET, 0x77777766);
        elemInfoTable[4] = new ElemInfoTable(ElementName.LAYOUT, 0x66666611);
        elemInfoTable[5] = new ElemInfoTable(ElementName.MEDIA, 0x66661111);
        elemInfoTable[6] = new ElemInfoTable(ElementName.PAGELIST, 0x66666111);
        elemInfoTable[7] = new ElemInfoTable(ElementName.CONTACT, 0x33333333);
        elemInfoTable[8] = new ElemInfoTable(ElementName.IDENTIFICATIONFIELD, 0x33333333);
    }
    
    @Override
	protected ElementInfo getTheElementInfo()
    {
        return super.getTheElementInfo().updateReplace(elemInfoTable);
    }



    /**
     * Constructor for JDFAutoComponent
     * @param myOwnerDocument
     * @param qualifiedName
     */
    protected JDFAutoComponent(
        CoreDocumentImpl myOwnerDocument,
        String qualifiedName)
    {
        super(myOwnerDocument, qualifiedName);
    }

    /**
     * Constructor for JDFAutoComponent
     * @param myOwnerDocument
     * @param myNamespaceURI
     * @param qualifiedName
     */
    protected JDFAutoComponent(
        CoreDocumentImpl myOwnerDocument,
        String myNamespaceURI,
        String qualifiedName)
    {
        super(myOwnerDocument, myNamespaceURI, qualifiedName);
    }

    /**
     * Constructor for JDFAutoComponent
     * @param myOwnerDocument
     * @param myNamespaceURI
     * @param qualifiedName
     * @param myLocalName
     */
    protected JDFAutoComponent(
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
        return " JDFAutoComponent[  --> " + super.toString() + " ]";
    }


    @Override
	public boolean  init()
    {
        boolean bRet = super.init();
        setResourceClass(JDFResource.EnumResourceClass.Quantity);
        return bRet;
    }


    @Override
	public EnumResourceClass getValidClass()
    {
        return JDFResource.EnumResourceClass.Quantity;
    }


        /**
        * Enumeration strings for ComponentType
        */

        public static class EnumComponentType extends ValuedEnum
        {
            private static final long serialVersionUID = 1L;
            private static int m_startValue = 0;

            private EnumComponentType(String name)
            {
                super(name, m_startValue++);
            }

            public static EnumComponentType getEnum(String enumName)
            {
                return (EnumComponentType) getEnum(EnumComponentType.class, enumName);
            }

            public static EnumComponentType getEnum(int enumValue)
            {
                return (EnumComponentType) getEnum(EnumComponentType.class, enumValue);
            }

            public static Map getEnumMap()
            {
                return getEnumMap(EnumComponentType.class);
            }

            public static List getEnumList()
            {
                return getEnumList(EnumComponentType.class);
            }

            public static Iterator iterator()
            {
                return iterator(EnumComponentType.class);
            }

            public static final EnumComponentType Block = new EnumComponentType("Block");
            public static final EnumComponentType Other = new EnumComponentType("Other");
            public static final EnumComponentType Ribbon = new EnumComponentType("Ribbon");
            public static final EnumComponentType Sheet = new EnumComponentType("Sheet");
            public static final EnumComponentType Web = new EnumComponentType("Web");
            public static final EnumComponentType FinalProduct = new EnumComponentType("FinalProduct");
            public static final EnumComponentType PartialProduct = new EnumComponentType("PartialProduct");
            public static final EnumComponentType Proof = new EnumComponentType("Proof");
        }      



        /**
        * Enumeration strings for OverfoldSide
        */

        public static class EnumOverfoldSide extends ValuedEnum
        {
            private static final long serialVersionUID = 1L;
            private static int m_startValue = 0;

            private EnumOverfoldSide(String name)
            {
                super(name, m_startValue++);
            }

            public static EnumOverfoldSide getEnum(String enumName)
            {
                return (EnumOverfoldSide) getEnum(EnumOverfoldSide.class, enumName);
            }

            public static EnumOverfoldSide getEnum(int enumValue)
            {
                return (EnumOverfoldSide) getEnum(EnumOverfoldSide.class, enumValue);
            }

            public static Map getEnumMap()
            {
                return getEnumMap(EnumOverfoldSide.class);
            }

            public static List getEnumList()
            {
                return getEnumList(EnumOverfoldSide.class);
            }

            public static Iterator iterator()
            {
                return iterator(EnumOverfoldSide.class);
            }

            public static final EnumOverfoldSide Front = new EnumOverfoldSide("Front");
            public static final EnumOverfoldSide Back = new EnumOverfoldSide("Back");
        }      



/* ************************************************************************
 * Attribute getter / setter
 * ************************************************************************
 */
        
        /* ---------------------------------------------------------------------
        Methods for Attribute ComponentType
        --------------------------------------------------------------------- */
        /**
          * (5.2) set attribute ComponentType
          * @param v vector of the enumeration values
          */
        public void setComponentType(Vector v)
        {
            setEnumerationsAttribute(AttributeName.COMPONENTTYPE, v, null);
        }

        /**
          * (9.2) get ComponentType attribute ComponentType
          * @return Vector of the enumerations
          */
        public Vector getComponentType()
        {
            return getEnumerationsAttribute(AttributeName.COMPONENTTYPE, null, EnumComponentType.getEnum(0), false);
        }

        
        /* ---------------------------------------------------------------------
        Methods for Attribute AssemblyIDs
        --------------------------------------------------------------------- */
        /**
          * (36) set attribute AssemblyIDs
          * @param value: the value to set the attribute to
          */
        public void setAssemblyIDs(VString value)
        {
            setAttribute(AttributeName.ASSEMBLYIDS, value, null);
        }

        /**
          * (21) get VString attribute AssemblyIDs
          * @return VString the value of the attribute
          */
        public VString getAssemblyIDs()
        {
            VString vStrAttrib = new VString();
            String  s = getAttribute(AttributeName.ASSEMBLYIDS, null, JDFConstants.EMPTYSTRING);
            vStrAttrib.setAllStrings(s, " ");
            return vStrAttrib;
        }

        
        /* ---------------------------------------------------------------------
        Methods for Attribute CartonTopFlaps
        --------------------------------------------------------------------- */
        /**
          * (36) set attribute CartonTopFlaps
          * @param value: the value to set the attribute to
          */
        public void setCartonTopFlaps(JDFXYPair value)
        {
            setAttribute(AttributeName.CARTONTOPFLAPS, value, null);
        }

        /**
          * (20) get JDFXYPair attribute CartonTopFlaps
          * @return JDFXYPair the value of the attribute, null if a the
          *         attribute value is not a valid to create a JDFXYPair
          */
        public JDFXYPair getCartonTopFlaps()
        {
            String strAttrName = "";
            JDFXYPair nPlaceHolder = null;
            strAttrName = getAttribute(AttributeName.CARTONTOPFLAPS, null, JDFConstants.EMPTYSTRING);
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
        Methods for Attribute Dimensions
        --------------------------------------------------------------------- */
        /**
          * (36) set attribute Dimensions
          * @param value: the value to set the attribute to
          */
        public void setDimensions(JDFShape value)
        {
            setAttribute(AttributeName.DIMENSIONS, value, null);
        }

        /**
          * (20) get JDFShape attribute Dimensions
          * @return JDFShape the value of the attribute, null if a the
          *         attribute value is not a valid to create a JDFShape
          */
        public JDFShape getDimensions()
        {
            String strAttrName = "";
            JDFShape nPlaceHolder = null;
            strAttrName = getAttribute(AttributeName.DIMENSIONS, null, JDFConstants.EMPTYSTRING);
            try
            {
                nPlaceHolder = new JDFShape(strAttrName);
            }
            catch(DataFormatException e)
            {
                return null;
            }
            return nPlaceHolder;
        }

        
        /* ---------------------------------------------------------------------
        Methods for Attribute IsWaste
        --------------------------------------------------------------------- */
        /**
          * (36) set attribute IsWaste
          * @param value: the value to set the attribute to
          */
        public void setIsWaste(boolean value)
        {
            setAttribute(AttributeName.ISWASTE, value, null);
        }

        /**
          * (18) get boolean attribute IsWaste
          * @return boolean the value of the attribute
          */
        public boolean getIsWaste()
        {
            return getBoolAttribute(AttributeName.ISWASTE, null, false);
        }

        
        /* ---------------------------------------------------------------------
        Methods for Attribute MaxHeat
        --------------------------------------------------------------------- */
        /**
          * (36) set attribute MaxHeat
          * @param value: the value to set the attribute to
          */
        public void setMaxHeat(double value)
        {
            setAttribute(AttributeName.MAXHEAT, value, null);
        }

        /**
          * (17) get double attribute MaxHeat
          * @return double the value of the attribute
          */
        public double getMaxHeat()
        {
            return getRealAttribute(AttributeName.MAXHEAT, null, 0.0);
        }

        
        /* ---------------------------------------------------------------------
        Methods for Attribute Overfold
        --------------------------------------------------------------------- */
        /**
          * (36) set attribute Overfold
          * @param value: the value to set the attribute to
          */
        public void setOverfold(double value)
        {
            setAttribute(AttributeName.OVERFOLD, value, null);
        }

        /**
          * (17) get double attribute Overfold
          * @return double the value of the attribute
          */
        public double getOverfold()
        {
            return getRealAttribute(AttributeName.OVERFOLD, null, 0.0);
        }

        
        /* ---------------------------------------------------------------------
        Methods for Attribute OverfoldSide
        --------------------------------------------------------------------- */
        /**
          * (5) set attribute OverfoldSide
          * @param enumVar: the enumVar to set the attribute to
          */
        public void setOverfoldSide(EnumOverfoldSide enumVar)
        {
            setAttribute(AttributeName.OVERFOLDSIDE, enumVar==null ? null : enumVar.getName(), null);
        }

        /**
          * (9) get attribute OverfoldSide
          * @return the value of the attribute
          */
        public EnumOverfoldSide getOverfoldSide()
        {
            return EnumOverfoldSide.getEnum(getAttribute(AttributeName.OVERFOLDSIDE, null, null));
        }

        
        /* ---------------------------------------------------------------------
        Methods for Attribute PageListIndex
        --------------------------------------------------------------------- */
        /**
          * (36) set attribute PageListIndex
          * @param value: the value to set the attribute to
          */
        public void setPageListIndex(JDFIntegerRangeList value)
        {
            setAttribute(AttributeName.PAGELISTINDEX, value, null);
        }

        /**
          * (20) get JDFIntegerRangeList attribute PageListIndex
          * @return JDFIntegerRangeList the value of the attribute, null if a the
          *         attribute value is not a valid to create a JDFIntegerRangeList
          */
        public JDFIntegerRangeList getPageListIndex()
        {
            String strAttrName = "";
            JDFIntegerRangeList nPlaceHolder = null;
            strAttrName = getAttribute(AttributeName.PAGELISTINDEX, null, JDFConstants.EMPTYSTRING);
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
        Methods for Attribute ProductType
        --------------------------------------------------------------------- */
        /**
          * (36) set attribute ProductType
          * @param value: the value to set the attribute to
          */
        public void setProductType(String value)
        {
            setAttribute(AttributeName.PRODUCTTYPE, value, null);
        }

        /**
          * (23) get String attribute ProductType
          * @return the value of the attribute
          */
        public String getProductType()
        {
            return getAttribute(AttributeName.PRODUCTTYPE, null, JDFConstants.EMPTYSTRING);
        }

        
        /* ---------------------------------------------------------------------
        Methods for Attribute ProductTypeDetails
        --------------------------------------------------------------------- */
        /**
          * (36) set attribute ProductTypeDetails
          * @param value: the value to set the attribute to
          */
        public void setProductTypeDetails(String value)
        {
            setAttribute(AttributeName.PRODUCTTYPEDETAILS, value, null);
        }

        /**
          * (23) get String attribute ProductTypeDetails
          * @return the value of the attribute
          */
        public String getProductTypeDetails()
        {
            return getAttribute(AttributeName.PRODUCTTYPEDETAILS, null, JDFConstants.EMPTYSTRING);
        }

        
        /* ---------------------------------------------------------------------
        Methods for Attribute ReaderPageCount
        --------------------------------------------------------------------- */
        /**
          * (36) set attribute ReaderPageCount
          * @param value: the value to set the attribute to
          */
        public void setReaderPageCount(int value)
        {
            setAttribute(AttributeName.READERPAGECOUNT, value, null);
        }

        /**
          * (15) get int attribute ReaderPageCount
          * @return int the value of the attribute
          */
        public int getReaderPageCount()
        {
            return getIntAttribute(AttributeName.READERPAGECOUNT, null, 0);
        }

        
        /* ---------------------------------------------------------------------
        Methods for Attribute SheetPart
        --------------------------------------------------------------------- */
        /**
          * (36) set attribute SheetPart
          * @param value: the value to set the attribute to
          */
        public void setSheetPart(JDFRectangle value)
        {
            setAttribute(AttributeName.SHEETPART, value, null);
        }

        /**
          * (20) get JDFRectangle attribute SheetPart
          * @return JDFRectangle the value of the attribute, null if a the
          *         attribute value is not a valid to create a JDFRectangle
          */
        public JDFRectangle getSheetPart()
        {
            String strAttrName = "";
            JDFRectangle nPlaceHolder = null;
            strAttrName = getAttribute(AttributeName.SHEETPART, null, JDFConstants.EMPTYSTRING);
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

        
        /* ---------------------------------------------------------------------
        Methods for Attribute SourceRibbon
        --------------------------------------------------------------------- */
        /**
          * (36) set attribute SourceRibbon
          * @param value: the value to set the attribute to
          */
        public void setSourceRibbon(String value)
        {
            setAttribute(AttributeName.SOURCERIBBON, value, null);
        }

        /**
          * (23) get String attribute SourceRibbon
          * @return the value of the attribute
          */
        public String getSourceRibbon()
        {
            return getAttribute(AttributeName.SOURCERIBBON, null, JDFConstants.EMPTYSTRING);
        }

        
        /* ---------------------------------------------------------------------
        Methods for Attribute SourceSheet
        --------------------------------------------------------------------- */
        /**
          * (36) set attribute SourceSheet
          * @param value: the value to set the attribute to
          */
        public void setSourceSheet(String value)
        {
            setAttribute(AttributeName.SOURCESHEET, value, null);
        }

        /**
          * (23) get String attribute SourceSheet
          * @return the value of the attribute
          */
        public String getSourceSheet()
        {
            return getAttribute(AttributeName.SOURCESHEET, null, JDFConstants.EMPTYSTRING);
        }

        
        /* ---------------------------------------------------------------------
        Methods for Attribute SourceWeb
        --------------------------------------------------------------------- */
        /**
          * (36) set attribute SourceWeb
          * @param value: the value to set the attribute to
          */
        public void setSourceWeb(String value)
        {
            setAttribute(AttributeName.SOURCEWEB, value, null);
        }

        /**
          * (23) get String attribute SourceWeb
          * @return the value of the attribute
          */
        public String getSourceWeb()
        {
            return getAttribute(AttributeName.SOURCEWEB, null, JDFConstants.EMPTYSTRING);
        }

        
        /* ---------------------------------------------------------------------
        Methods for Attribute SpineThickness
        --------------------------------------------------------------------- */
        /**
          * (36) set attribute SpineThickness
          * @param value: the value to set the attribute to
          */
        public void setSpineThickness(double value)
        {
            setAttribute(AttributeName.SPINETHICKNESS, value, null);
        }

        /**
          * (17) get double attribute SpineThickness
          * @return double the value of the attribute
          */
        public double getSpineThickness()
        {
            return getRealAttribute(AttributeName.SPINETHICKNESS, null, 0.0);
        }

        
        /* ---------------------------------------------------------------------
        Methods for Attribute SurfaceCount
        --------------------------------------------------------------------- */
        /**
          * (36) set attribute SurfaceCount
          * @param value: the value to set the attribute to
          */
        public void setSurfaceCount(int value)
        {
            setAttribute(AttributeName.SURFACECOUNT, value, null);
        }

        /**
          * (15) get int attribute SurfaceCount
          * @return int the value of the attribute
          */
        public int getSurfaceCount()
        {
            return getIntAttribute(AttributeName.SURFACECOUNT, null, 0);
        }

        
        /* ---------------------------------------------------------------------
        Methods for Attribute Transformation
        --------------------------------------------------------------------- */
        /**
          * (36) set attribute Transformation
          * @param value: the value to set the attribute to
          */
        public void setTransformation(JDFMatrix value)
        {
            setAttribute(AttributeName.TRANSFORMATION, value, null);
        }

        /**
          * (20) get JDFMatrix attribute Transformation
          * @return JDFMatrix the value of the attribute, null if a the
          *         attribute value is not a valid to create a JDFMatrix
          */
        public JDFMatrix getTransformation()
        {
            String strAttrName = "";
            JDFMatrix nPlaceHolder = null;
            strAttrName = getAttribute(AttributeName.TRANSFORMATION, null, JDFConstants.EMPTYSTRING);
            try
            {
                nPlaceHolder = new JDFMatrix(strAttrName);
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
     * (24) const get element Assembly
     * @return JDFAssembly the element
     */
    public JDFAssembly getAssembly()
    {
        return (JDFAssembly) getElement(ElementName.ASSEMBLY, null, 0);
    }

    /** (25) getCreateAssembly
     * 
     * @return JDFAssembly the element
     */
    public JDFAssembly getCreateAssembly()
    {
        return (JDFAssembly) getCreateElement_KElement(ElementName.ASSEMBLY, null, 0);
    }

    /**
     * (29) append element Assembly
     */
    public JDFAssembly appendAssembly() throws JDFException
    {
        return (JDFAssembly) appendElementN(ElementName.ASSEMBLY, 1, null);
    }

    /**
      * (31) create inter-resource link to refTarget
      * @param refTarget the element that is referenced
      */
    public void refAssembly(JDFAssembly refTarget)
    {
        refElement(refTarget);
    }

    /**
     * (24) const get element Bundle
     * @return JDFBundle the element
     */
    public JDFBundle getBundle()
    {
        return (JDFBundle) getElement(ElementName.BUNDLE, null, 0);
    }

    /** (25) getCreateBundle
     * 
     * @return JDFBundle the element
     */
    public JDFBundle getCreateBundle()
    {
        return (JDFBundle) getCreateElement_KElement(ElementName.BUNDLE, null, 0);
    }

    /**
     * (29) append element Bundle
     */
    public JDFBundle appendBundle() throws JDFException
    {
        return (JDFBundle) appendElementN(ElementName.BUNDLE, 1, null);
    }

    /**
      * (31) create inter-resource link to refTarget
      * @param refTarget the element that is referenced
      */
    public void refBundle(JDFBundle refTarget)
    {
        refElement(refTarget);
    }

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

    /**
     * (24) const get element Sheet
     * @return JDFSheet the element
     */
    public JDFSheet getSheet()
    {
        return (JDFSheet) getElement(ElementName.SHEET, null, 0);
    }

    /** (25) getCreateSheet
     * 
     * @return JDFSheet the element
     */
    public JDFSheet getCreateSheet()
    {
        return (JDFSheet) getCreateElement_KElement(ElementName.SHEET, null, 0);
    }

    /**
     * (29) append element Sheet
     */
    public JDFSheet appendSheet() throws JDFException
    {
        return (JDFSheet) appendElementN(ElementName.SHEET, 1, null);
    }

    /**
      * (31) create inter-resource link to refTarget
      * @param refTarget the element that is referenced
      */
    public void refSheet(JDFSheet refTarget)
    {
        refElement(refTarget);
    }

    /**
     * (24) const get element Layout
     * @return JDFLayout the element
     */
    public JDFLayout getLayout()
    {
        return (JDFLayout) getElement(ElementName.LAYOUT, null, 0);
    }

    /** (25) getCreateLayout
     * 
     * @return JDFLayout the element
     */
    public JDFLayout getCreateLayout()
    {
        return (JDFLayout) getCreateElement_KElement(ElementName.LAYOUT, null, 0);
    }

    /**
     * (29) append element Layout
     */
    public JDFLayout appendLayout() throws JDFException
    {
        return (JDFLayout) appendElementN(ElementName.LAYOUT, 1, null);
    }

    /**
      * (31) create inter-resource link to refTarget
      * @param refTarget the element that is referenced
      */
    public void refLayout(JDFLayout refTarget)
    {
        refElement(refTarget);
    }

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
     * (24) const get element PageList
     * @return JDFPageList the element
     */
    public JDFPageList getPageList()
    {
        return (JDFPageList) getElement(ElementName.PAGELIST, null, 0);
    }

    /** (25) getCreatePageList
     * 
     * @return JDFPageList the element
     */
    public JDFPageList getCreatePageList()
    {
        return (JDFPageList) getCreateElement_KElement(ElementName.PAGELIST, null, 0);
    }

    /**
     * (29) append element PageList
     */
    public JDFPageList appendPageList() throws JDFException
    {
        return (JDFPageList) appendElementN(ElementName.PAGELIST, 1, null);
    }

    /**
      * (31) create inter-resource link to refTarget
      * @param refTarget the element that is referenced
      */
    public void refPageList(JDFPageList refTarget)
    {
        refElement(refTarget);
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
     * @return Collection<JDFContact>
     */
    public Collection<JDFContact> getAllContact()
    {
        Vector<JDFContact> v = new Vector<JDFContact>();

        JDFContact kElem = (JDFContact) getFirstChildElement(ElementName.CONTACT, null);

        while (kElem != null)
        {
            v.add(kElem);

            kElem = (JDFContact) kElem.getNextSiblingElement(ElementName.CONTACT, null);
        }

        return v;
    }

    /**
     * (30) append element Contact
     */
    @Override
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
    @Override
	public JDFIdentificationField getCreateIdentificationField(int iSkip)
    {
        return (JDFIdentificationField)getCreateElement_KElement(ElementName.IDENTIFICATIONFIELD, null, iSkip);
    }

    /**
     * (27) const get element IdentificationField
     * @param iSkip number of elements to skip
     * @return JDFIdentificationField the element
     * default is getIdentificationField(0)     */
    @Override
	public JDFIdentificationField getIdentificationField(int iSkip)
    {
        return (JDFIdentificationField) getElement(ElementName.IDENTIFICATIONFIELD, null, iSkip);
    }

    /**
     * Get all IdentificationField from the current element
     * 
     * @return Collection<JDFIdentificationField>
     */
    public Collection<JDFIdentificationField> getAllIdentificationField()
    {
        Vector<JDFIdentificationField> v = new Vector<JDFIdentificationField>();

        JDFIdentificationField kElem = (JDFIdentificationField) getFirstChildElement(ElementName.IDENTIFICATIONFIELD, null);

        while (kElem != null)
        {
            v.add(kElem);

            kElem = (JDFIdentificationField) kElem.getNextSiblingElement(ElementName.IDENTIFICATIONFIELD, null);
        }

        return v;
    }

    /**
     * (30) append element IdentificationField
     */
    @Override
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
