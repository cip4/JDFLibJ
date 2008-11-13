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
import org.cip4.jdflib.datatypes.JDFIntegerList;
import org.cip4.jdflib.datatypes.JDFNumberList;
import org.cip4.jdflib.resource.JDFResource;
import org.cip4.jdflib.resource.process.postpress.JDFGlueLine;

public abstract class JDFAutoThreadSewingParams extends JDFResource
{

    private static final long serialVersionUID = 1L;

    private static AtrInfoTable[] atrInfoTable = new AtrInfoTable[11];
    static
    {
        atrInfoTable[0] = new AtrInfoTable(AttributeName.BLINDSTITCH, 0x33333333, AttributeInfo.EnumAttributeType.boolean_, null, "false");
        atrInfoTable[1] = new AtrInfoTable(AttributeName.CASTINGMATERIAL, 0x33333333, AttributeInfo.EnumAttributeType.enumeration, EnumCastingMaterial.getEnum(0), null);
        atrInfoTable[2] = new AtrInfoTable(AttributeName.COREMATERIAL, 0x33333333, AttributeInfo.EnumAttributeType.enumeration, EnumCoreMaterial.getEnum(0), null);
        atrInfoTable[3] = new AtrInfoTable(AttributeName.GLUELINEREFSHEETS, 0x33333333, AttributeInfo.EnumAttributeType.IntegerList, null, null);
        atrInfoTable[4] = new AtrInfoTable(AttributeName.OFFSET, 0x33333331, AttributeInfo.EnumAttributeType.double_, null, null);
        atrInfoTable[5] = new AtrInfoTable(AttributeName.NEEDLEPOSITIONS, 0x33333333, AttributeInfo.EnumAttributeType.string, null, null);
        atrInfoTable[6] = new AtrInfoTable(AttributeName.NUMBEROFNEEDLES, 0x33333333, AttributeInfo.EnumAttributeType.integer, null, null);
        atrInfoTable[7] = new AtrInfoTable(AttributeName.SEALING, 0x33333333, AttributeInfo.EnumAttributeType.boolean_, null, null);
        atrInfoTable[8] = new AtrInfoTable(AttributeName.SEWINGPATTERN, 0x33333333, AttributeInfo.EnumAttributeType.enumeration, EnumSewingPattern.getEnum(0), null);
        atrInfoTable[9] = new AtrInfoTable(AttributeName.THREADTHICKNESS, 0x33333333, AttributeInfo.EnumAttributeType.double_, null, null);
        atrInfoTable[10] = new AtrInfoTable(AttributeName.THREADBRAND, 0x33333333, AttributeInfo.EnumAttributeType.string, null, null);
    }
    
    protected AttributeInfo getTheAttributeInfo()
    {
        return super.getTheAttributeInfo().updateReplace(atrInfoTable);
    }


    private static ElemInfoTable[] elemInfoTable = new ElemInfoTable[1];
    static
    {
        elemInfoTable[0] = new ElemInfoTable(ElementName.GLUELINE, 0x33333333);
    }
    
    protected ElementInfo getTheElementInfo()
    {
        return super.getTheElementInfo().updateReplace(elemInfoTable);
    }



    /**
     * Constructor for JDFAutoThreadSewingParams
     * @param myOwnerDocument
     * @param qualifiedName
     */
    protected JDFAutoThreadSewingParams(
        CoreDocumentImpl myOwnerDocument,
        String qualifiedName)
    {
        super(myOwnerDocument, qualifiedName);
    }

    /**
     * Constructor for JDFAutoThreadSewingParams
     * @param myOwnerDocument
     * @param myNamespaceURI
     * @param qualifiedName
     */
    protected JDFAutoThreadSewingParams(
        CoreDocumentImpl myOwnerDocument,
        String myNamespaceURI,
        String qualifiedName)
    {
        super(myOwnerDocument, myNamespaceURI, qualifiedName);
    }

    /**
     * Constructor for JDFAutoThreadSewingParams
     * @param myOwnerDocument
     * @param myNamespaceURI
     * @param qualifiedName
     * @param myLocalName
     */
    protected JDFAutoThreadSewingParams(
        CoreDocumentImpl myOwnerDocument,
        String myNamespaceURI,
        String qualifiedName,
        String myLocalName)
    {
        super(myOwnerDocument, myNamespaceURI, qualifiedName, myLocalName);
    }


    public String toString()
    {
        return " JDFAutoThreadSewingParams[  --> " + super.toString() + " ]";
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
        * Enumeration strings for CastingMaterial
        */

        public static class EnumCastingMaterial extends ValuedEnum
        {
            private static final long serialVersionUID = 1L;
            private static int m_startValue = 0;

            private EnumCastingMaterial(String name)
            {
                super(name, m_startValue++);
            }

            public static EnumCastingMaterial getEnum(String enumName)
            {
                return (EnumCastingMaterial) getEnum(EnumCastingMaterial.class, enumName);
            }

            public static EnumCastingMaterial getEnum(int enumValue)
            {
                return (EnumCastingMaterial) getEnum(EnumCastingMaterial.class, enumValue);
            }

            public static Map getEnumMap()
            {
                return getEnumMap(EnumCastingMaterial.class);
            }

            public static List getEnumList()
            {
                return getEnumList(EnumCastingMaterial.class);
            }

            public static Iterator iterator()
            {
                return iterator(EnumCastingMaterial.class);
            }

            public static final EnumCastingMaterial Cotton = new EnumCastingMaterial("Cotton");
            public static final EnumCastingMaterial Nylon = new EnumCastingMaterial("Nylon");
            public static final EnumCastingMaterial Polyester = new EnumCastingMaterial("Polyester");
        }      



        /**
        * Enumeration strings for CoreMaterial
        */

        public static class EnumCoreMaterial extends ValuedEnum
        {
            private static final long serialVersionUID = 1L;
            private static int m_startValue = 0;

            private EnumCoreMaterial(String name)
            {
                super(name, m_startValue++);
            }

            public static EnumCoreMaterial getEnum(String enumName)
            {
                return (EnumCoreMaterial) getEnum(EnumCoreMaterial.class, enumName);
            }

            public static EnumCoreMaterial getEnum(int enumValue)
            {
                return (EnumCoreMaterial) getEnum(EnumCoreMaterial.class, enumValue);
            }

            public static Map getEnumMap()
            {
                return getEnumMap(EnumCoreMaterial.class);
            }

            public static List getEnumList()
            {
                return getEnumList(EnumCoreMaterial.class);
            }

            public static Iterator iterator()
            {
                return iterator(EnumCoreMaterial.class);
            }

            public static final EnumCoreMaterial Cotton = new EnumCoreMaterial("Cotton");
            public static final EnumCoreMaterial Nylon = new EnumCoreMaterial("Nylon");
            public static final EnumCoreMaterial Polyester = new EnumCoreMaterial("Polyester");
        }      



        /**
        * Enumeration strings for SewingPattern
        */

        public static class EnumSewingPattern extends ValuedEnum
        {
            private static final long serialVersionUID = 1L;
            private static int m_startValue = 0;

            private EnumSewingPattern(String name)
            {
                super(name, m_startValue++);
            }

            public static EnumSewingPattern getEnum(String enumName)
            {
                return (EnumSewingPattern) getEnum(EnumSewingPattern.class, enumName);
            }

            public static EnumSewingPattern getEnum(int enumValue)
            {
                return (EnumSewingPattern) getEnum(EnumSewingPattern.class, enumValue);
            }

            public static Map getEnumMap()
            {
                return getEnumMap(EnumSewingPattern.class);
            }

            public static List getEnumList()
            {
                return getEnumList(EnumSewingPattern.class);
            }

            public static Iterator iterator()
            {
                return iterator(EnumSewingPattern.class);
            }

            public static final EnumSewingPattern Normal = new EnumSewingPattern("Normal");
            public static final EnumSewingPattern Staggered = new EnumSewingPattern("Staggered");
            public static final EnumSewingPattern CombinedStaggered = new EnumSewingPattern("CombinedStaggered");
            public static final EnumSewingPattern Side = new EnumSewingPattern("Side");
        }      



/* ************************************************************************
 * Attribute getter / setter
 * ************************************************************************
 */
        
        /* ---------------------------------------------------------------------
        Methods for Attribute BlindStitch
        --------------------------------------------------------------------- */
        /**
          * (36) set attribute BlindStitch
          * @param value: the value to set the attribute to
          */
        public void setBlindStitch(boolean value)
        {
            setAttribute(AttributeName.BLINDSTITCH, value, null);
        }

        /**
          * (18) get boolean attribute BlindStitch
          * @return boolean the value of the attribute
          */
        public boolean getBlindStitch()
        {
            return getBoolAttribute(AttributeName.BLINDSTITCH, null, false);
        }

        
        /* ---------------------------------------------------------------------
        Methods for Attribute CastingMaterial
        --------------------------------------------------------------------- */
        /**
          * (5) set attribute CastingMaterial
          * @param enumVar: the enumVar to set the attribute to
          */
        public void setCastingMaterial(EnumCastingMaterial enumVar)
        {
            setAttribute(AttributeName.CASTINGMATERIAL, enumVar==null ? null : enumVar.getName(), null);
        }

        /**
          * (9) get attribute CastingMaterial
          * @return the value of the attribute
          */
        public EnumCastingMaterial getCastingMaterial()
        {
            return EnumCastingMaterial.getEnum(getAttribute(AttributeName.CASTINGMATERIAL, null, null));
        }

        
        /* ---------------------------------------------------------------------
        Methods for Attribute CoreMaterial
        --------------------------------------------------------------------- */
        /**
          * (5) set attribute CoreMaterial
          * @param enumVar: the enumVar to set the attribute to
          */
        public void setCoreMaterial(EnumCoreMaterial enumVar)
        {
            setAttribute(AttributeName.COREMATERIAL, enumVar==null ? null : enumVar.getName(), null);
        }

        /**
          * (9) get attribute CoreMaterial
          * @return the value of the attribute
          */
        public EnumCoreMaterial getCoreMaterial()
        {
            return EnumCoreMaterial.getEnum(getAttribute(AttributeName.COREMATERIAL, null, null));
        }

        
        /* ---------------------------------------------------------------------
        Methods for Attribute GlueLineRefSheets
        --------------------------------------------------------------------- */
        /**
          * (36) set attribute GlueLineRefSheets
          * @param value: the value to set the attribute to
          */
        public void setGlueLineRefSheets(JDFIntegerList value)
        {
            setAttribute(AttributeName.GLUELINEREFSHEETS, value, null);
        }

        /**
          * (20) get JDFIntegerList attribute GlueLineRefSheets
          * @return JDFIntegerList the value of the attribute, null if a the
          *         attribute value is not a valid to create a JDFIntegerList
          */
        public JDFIntegerList getGlueLineRefSheets()
        {
            String strAttrName = "";
            JDFIntegerList nPlaceHolder = null;
            strAttrName = getAttribute(AttributeName.GLUELINEREFSHEETS, null, JDFConstants.EMPTYSTRING);
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
        Methods for Attribute Offset
        --------------------------------------------------------------------- */
        /**
          * (36) set attribute Offset
          * @param value: the value to set the attribute to
          */
        public void setOffset(double value)
        {
            setAttribute(AttributeName.OFFSET, value, null);
        }

        /**
          * (17) get double attribute Offset
          * @return double the value of the attribute
          */
        public double getOffset()
        {
            return getRealAttribute(AttributeName.OFFSET, null, 0.0);
        }

        
        /* ---------------------------------------------------------------------
        Methods for Attribute NeedlePositions
        --------------------------------------------------------------------- */
        /**
          * (36) set attribute NeedlePositions
          * @param value: the value to set the attribute to
          */
        public void setNeedlePositions(JDFNumberList value)
        {
            setAttribute(AttributeName.NEEDLEPOSITIONS, value, null);
        }

        /**
          * (20) get JDFNumberList attribute NeedlePositions
          * @return JDFNumberList the value of the attribute, null if a the
          *         attribute value is not a valid to create a JDFNumberList
          */
        public JDFNumberList getNeedlePositions()
        {
            String strAttrName = "";
            JDFNumberList nPlaceHolder = null;
            strAttrName = getAttribute(AttributeName.NEEDLEPOSITIONS, null, JDFConstants.EMPTYSTRING);
            try
            {
                nPlaceHolder = new JDFNumberList(strAttrName);
            }
            catch(DataFormatException e)
            {
                return null;
            }
            return nPlaceHolder;
        }

        
        /* ---------------------------------------------------------------------
        Methods for Attribute NumberOfNeedles
        --------------------------------------------------------------------- */
        /**
          * (36) set attribute NumberOfNeedles
          * @param value: the value to set the attribute to
          */
        public void setNumberOfNeedles(int value)
        {
            setAttribute(AttributeName.NUMBEROFNEEDLES, value, null);
        }

        /**
          * (15) get int attribute NumberOfNeedles
          * @return int the value of the attribute
          */
        public int getNumberOfNeedles()
        {
            return getIntAttribute(AttributeName.NUMBEROFNEEDLES, null, 0);
        }

        
        /* ---------------------------------------------------------------------
        Methods for Attribute Sealing
        --------------------------------------------------------------------- */
        /**
          * (36) set attribute Sealing
          * @param value: the value to set the attribute to
          */
        public void setSealing(boolean value)
        {
            setAttribute(AttributeName.SEALING, value, null);
        }

        /**
          * (18) get boolean attribute Sealing
          * @return boolean the value of the attribute
          */
        public boolean getSealing()
        {
            return getBoolAttribute(AttributeName.SEALING, null, false);
        }

        
        /* ---------------------------------------------------------------------
        Methods for Attribute SewingPattern
        --------------------------------------------------------------------- */
        /**
          * (5) set attribute SewingPattern
          * @param enumVar: the enumVar to set the attribute to
          */
        public void setSewingPattern(EnumSewingPattern enumVar)
        {
            setAttribute(AttributeName.SEWINGPATTERN, enumVar==null ? null : enumVar.getName(), null);
        }

        /**
          * (9) get attribute SewingPattern
          * @return the value of the attribute
          */
        public EnumSewingPattern getSewingPattern()
        {
            return EnumSewingPattern.getEnum(getAttribute(AttributeName.SEWINGPATTERN, null, null));
        }

        
        /* ---------------------------------------------------------------------
        Methods for Attribute ThreadThickness
        --------------------------------------------------------------------- */
        /**
          * (36) set attribute ThreadThickness
          * @param value: the value to set the attribute to
          */
        public void setThreadThickness(double value)
        {
            setAttribute(AttributeName.THREADTHICKNESS, value, null);
        }

        /**
          * (17) get double attribute ThreadThickness
          * @return double the value of the attribute
          */
        public double getThreadThickness()
        {
            return getRealAttribute(AttributeName.THREADTHICKNESS, null, 0.0);
        }

        
        /* ---------------------------------------------------------------------
        Methods for Attribute ThreadBrand
        --------------------------------------------------------------------- */
        /**
          * (36) set attribute ThreadBrand
          * @param value: the value to set the attribute to
          */
        public void setThreadBrand(String value)
        {
            setAttribute(AttributeName.THREADBRAND, value, null);
        }

        /**
          * (23) get String attribute ThreadBrand
          * @return the value of the attribute
          */
        public String getThreadBrand()
        {
            return getAttribute(AttributeName.THREADBRAND, null, JDFConstants.EMPTYSTRING);
        }

/* ***********************************************************************
 * Element getter / setter
 * ***********************************************************************
 */

    /** (26) getCreateGlueLine
     * 
     * @param iSkip number of elements to skip
     * @return JDFGlueLine the element
     */
    public JDFGlueLine getCreateGlueLine(int iSkip)
    {
        return (JDFGlueLine)getCreateElement_KElement(ElementName.GLUELINE, null, iSkip);
    }

    /**
     * (27) const get element GlueLine
     * @param iSkip number of elements to skip
     * @return JDFGlueLine the element
     * default is getGlueLine(0)     */
    public JDFGlueLine getGlueLine(int iSkip)
    {
        return (JDFGlueLine) getElement(ElementName.GLUELINE, null, iSkip);
    }

    /**
     * Get all GlueLine from the current element
     * 
     * @return Collection<JDFGlueLine>
     */
    public Collection<JDFGlueLine> getAllGlueLine()
    {
        Vector<JDFGlueLine> v = new Vector<JDFGlueLine>();

        JDFGlueLine kElem = (JDFGlueLine) getFirstChildElement(ElementName.GLUELINE, null);

        while (kElem != null)
        {
            v.add(kElem);

            kElem = (JDFGlueLine) kElem.getNextSiblingElement(ElementName.GLUELINE, null);
        }

        return v;
    }

    /**
     * (30) append element GlueLine
     */
    public JDFGlueLine appendGlueLine() throws JDFException
    {
        return (JDFGlueLine) appendElement(ElementName.GLUELINE, null);
    }

}// end namespace JDF
