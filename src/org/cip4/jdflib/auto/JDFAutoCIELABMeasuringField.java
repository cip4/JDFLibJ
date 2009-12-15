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
import org.cip4.jdflib.datatypes.JDFLabColor;
import org.cip4.jdflib.datatypes.JDFNumberList;
import org.cip4.jdflib.datatypes.JDFXYPair;
import org.cip4.jdflib.resource.JDFColorMeasurementConditions;
import org.cip4.jdflib.resource.JDFResource;

public abstract class JDFAutoCIELABMeasuringField extends JDFResource
{

    private static final long serialVersionUID = 1L;

    private static AtrInfoTable[] atrInfoTable = new AtrInfoTable[11];
    static
    {
        atrInfoTable[0] = new AtrInfoTable(AttributeName.CENTER, 0x22222222, AttributeInfo.EnumAttributeType.XYPair, null, null);
        atrInfoTable[1] = new AtrInfoTable(AttributeName.CIELAB, 0x22222222, AttributeInfo.EnumAttributeType.LabColor, null, null);
        atrInfoTable[2] = new AtrInfoTable(AttributeName.DENSITYSTANDARD, 0x44444443, AttributeInfo.EnumAttributeType.enumeration, EnumDensityStandard.getEnum(0), null);
        atrInfoTable[3] = new AtrInfoTable(AttributeName.DIAMETER, 0x33333333, AttributeInfo.EnumAttributeType.double_, null, null);
        atrInfoTable[4] = new AtrInfoTable(AttributeName.LIGHT, 0x44444443, AttributeInfo.EnumAttributeType.NMTOKEN, null, null);
        atrInfoTable[5] = new AtrInfoTable(AttributeName.OBSERVER, 0x44444443, AttributeInfo.EnumAttributeType.integer, null, null);
        atrInfoTable[6] = new AtrInfoTable(AttributeName.PERCENTAGES, 0x33333333, AttributeInfo.EnumAttributeType.string, null, null);
        atrInfoTable[7] = new AtrInfoTable(AttributeName.SCREENRULING, 0x33333333, AttributeInfo.EnumAttributeType.string, null, null);
        atrInfoTable[8] = new AtrInfoTable(AttributeName.SCREENSHAPE, 0x33333333, AttributeInfo.EnumAttributeType.string, null, null);
        atrInfoTable[9] = new AtrInfoTable(AttributeName.SETUP, 0x44444443, AttributeInfo.EnumAttributeType.string, null, null);
        atrInfoTable[10] = new AtrInfoTable(AttributeName.TOLERANCE, 0x33333333, AttributeInfo.EnumAttributeType.double_, null, null);
    }
    
    protected AttributeInfo getTheAttributeInfo()
    {
        return super.getTheAttributeInfo().updateReplace(atrInfoTable);
    }


    private static ElemInfoTable[] elemInfoTable = new ElemInfoTable[1];
    static
    {
        elemInfoTable[0] = new ElemInfoTable(ElementName.COLORMEASUREMENTCONDITIONS, 0x66666661);
    }
    
    protected ElementInfo getTheElementInfo()
    {
        return super.getTheElementInfo().updateReplace(elemInfoTable);
    }



    /**
     * Constructor for JDFAutoCIELABMeasuringField
     * @param myOwnerDocument
     * @param qualifiedName
     */
    protected JDFAutoCIELABMeasuringField(
        CoreDocumentImpl myOwnerDocument,
        String qualifiedName)
    {
        super(myOwnerDocument, qualifiedName);
    }

    /**
     * Constructor for JDFAutoCIELABMeasuringField
     * @param myOwnerDocument
     * @param myNamespaceURI
     * @param qualifiedName
     */
    protected JDFAutoCIELABMeasuringField(
        CoreDocumentImpl myOwnerDocument,
        String myNamespaceURI,
        String qualifiedName)
    {
        super(myOwnerDocument, myNamespaceURI, qualifiedName);
    }

    /**
     * Constructor for JDFAutoCIELABMeasuringField
     * @param myOwnerDocument
     * @param myNamespaceURI
     * @param qualifiedName
     * @param myLocalName
     */
    protected JDFAutoCIELABMeasuringField(
        CoreDocumentImpl myOwnerDocument,
        String myNamespaceURI,
        String qualifiedName,
        String myLocalName)
    {
        super(myOwnerDocument, myNamespaceURI, qualifiedName, myLocalName);
    }


    public String toString()
    {
        return " JDFAutoCIELABMeasuringField[  --> " + super.toString() + " ]";
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
        * Enumeration strings for DensityStandard
        */

        public static class EnumDensityStandard extends ValuedEnum
        {
            private static final long serialVersionUID = 1L;
            private static int m_startValue = 0;

            private EnumDensityStandard(String name)
            {
                super(name, m_startValue++);
            }

            public static EnumDensityStandard getEnum(String enumName)
            {
                return (EnumDensityStandard) getEnum(EnumDensityStandard.class, enumName);
            }

            public static EnumDensityStandard getEnum(int enumValue)
            {
                return (EnumDensityStandard) getEnum(EnumDensityStandard.class, enumValue);
            }

            public static Map getEnumMap()
            {
                return getEnumMap(EnumDensityStandard.class);
            }

            public static List getEnumList()
            {
                return getEnumList(EnumDensityStandard.class);
            }

            public static Iterator iterator()
            {
                return iterator(EnumDensityStandard.class);
            }

            public static final EnumDensityStandard ANSIA = new EnumDensityStandard("ANSIA");
            public static final EnumDensityStandard ANSIE = new EnumDensityStandard("ANSIE");
            public static final EnumDensityStandard ANSII = new EnumDensityStandard("ANSII");
            public static final EnumDensityStandard ANSIT = new EnumDensityStandard("ANSIT");
            public static final EnumDensityStandard DIN16536 = new EnumDensityStandard("DIN16536");
            public static final EnumDensityStandard DIN16536NB = new EnumDensityStandard("DIN16536NB");
        }      



/* ************************************************************************
 * Attribute getter / setter
 * ************************************************************************
 */
        
        /* ---------------------------------------------------------------------
        Methods for Attribute Center
        --------------------------------------------------------------------- */
        /**
          * (36) set attribute Center
          * @param value: the value to set the attribute to
          */
        public void setCenter(JDFXYPair value)
        {
            setAttribute(AttributeName.CENTER, value, null);
        }

        /**
          * (20) get JDFXYPair attribute Center
          * @return JDFXYPair the value of the attribute, null if a the
          *         attribute value is not a valid to create a JDFXYPair
          */
        public JDFXYPair getCenter()
        {
            String strAttrName = "";
            JDFXYPair nPlaceHolder = null;
            strAttrName = getAttribute(AttributeName.CENTER, null, JDFConstants.EMPTYSTRING);
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
        Methods for Attribute CIELab
        --------------------------------------------------------------------- */
        /**
          * (36) set attribute CIELab
          * @param value: the value to set the attribute to
          */
        public void setCIELab(JDFLabColor value)
        {
            setAttribute(AttributeName.CIELAB, value, null);
        }

        /**
          * (20) get JDFLabColor attribute CIELab
          * @return JDFLabColor the value of the attribute, null if a the
          *         attribute value is not a valid to create a JDFLabColor
          */
        public JDFLabColor getCIELab()
        {
            String strAttrName = "";
            JDFLabColor nPlaceHolder = null;
            strAttrName = getAttribute(AttributeName.CIELAB, null, JDFConstants.EMPTYSTRING);
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
        Methods for Attribute DensityStandard
        --------------------------------------------------------------------- */
        /**
          * (5) set attribute DensityStandard
          * @param enumVar: the enumVar to set the attribute to
          */
        public void setDensityStandard(EnumDensityStandard enumVar)
        {
            setAttribute(AttributeName.DENSITYSTANDARD, enumVar==null ? null : enumVar.getName(), null);
        }

        /**
          * (9) get attribute DensityStandard
          * @return the value of the attribute
          */
        public EnumDensityStandard getDensityStandard()
        {
            return EnumDensityStandard.getEnum(getAttribute(AttributeName.DENSITYSTANDARD, null, null));
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
        Methods for Attribute Light
        --------------------------------------------------------------------- */
        /**
          * (36) set attribute Light
          * @param value: the value to set the attribute to
          */
        public void setLight(String value)
        {
            setAttribute(AttributeName.LIGHT, value, null);
        }

        /**
          * (23) get String attribute Light
          * @return the value of the attribute
          */
        public String getLight()
        {
            return getAttribute(AttributeName.LIGHT, null, JDFConstants.EMPTYSTRING);
        }

        
        /* ---------------------------------------------------------------------
        Methods for Attribute Observer
        --------------------------------------------------------------------- */
        /**
          * (36) set attribute Observer
          * @param value: the value to set the attribute to
          */
        public void setObserver(int value)
        {
            setAttribute(AttributeName.OBSERVER, value, null);
        }

        /**
          * (15) get int attribute Observer
          * @return int the value of the attribute
          */
        public int getObserver()
        {
            return getIntAttribute(AttributeName.OBSERVER, null, 0);
        }

        
        /* ---------------------------------------------------------------------
        Methods for Attribute Percentages
        --------------------------------------------------------------------- */
        /**
          * (36) set attribute Percentages
          * @param value: the value to set the attribute to
          */
        public void setPercentages(JDFNumberList value)
        {
            setAttribute(AttributeName.PERCENTAGES, value, null);
        }

        /**
          * (20) get JDFNumberList attribute Percentages
          * @return JDFNumberList the value of the attribute, null if a the
          *         attribute value is not a valid to create a JDFNumberList
          */
        public JDFNumberList getPercentages()
        {
            String strAttrName = "";
            JDFNumberList nPlaceHolder = null;
            strAttrName = getAttribute(AttributeName.PERCENTAGES, null, JDFConstants.EMPTYSTRING);
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
        Methods for Attribute ScreenRuling
        --------------------------------------------------------------------- */
        /**
          * (36) set attribute ScreenRuling
          * @param value: the value to set the attribute to
          */
        public void setScreenRuling(JDFNumberList value)
        {
            setAttribute(AttributeName.SCREENRULING, value, null);
        }

        /**
          * (20) get JDFNumberList attribute ScreenRuling
          * @return JDFNumberList the value of the attribute, null if a the
          *         attribute value is not a valid to create a JDFNumberList
          */
        public JDFNumberList getScreenRuling()
        {
            String strAttrName = "";
            JDFNumberList nPlaceHolder = null;
            strAttrName = getAttribute(AttributeName.SCREENRULING, null, JDFConstants.EMPTYSTRING);
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
        Methods for Attribute ScreenShape
        --------------------------------------------------------------------- */
        /**
          * (36) set attribute ScreenShape
          * @param value: the value to set the attribute to
          */
        public void setScreenShape(String value)
        {
            setAttribute(AttributeName.SCREENSHAPE, value, null);
        }

        /**
          * (23) get String attribute ScreenShape
          * @return the value of the attribute
          */
        public String getScreenShape()
        {
            return getAttribute(AttributeName.SCREENSHAPE, null, JDFConstants.EMPTYSTRING);
        }

        
        /* ---------------------------------------------------------------------
        Methods for Attribute Setup
        --------------------------------------------------------------------- */
        /**
          * (36) set attribute Setup
          * @param value: the value to set the attribute to
          */
        public void setSetup(String value)
        {
            setAttribute(AttributeName.SETUP, value, null);
        }

        /**
          * (23) get String attribute Setup
          * @return the value of the attribute
          */
        public String getSetup()
        {
            return getAttribute(AttributeName.SETUP, null, JDFConstants.EMPTYSTRING);
        }

        
        /* ---------------------------------------------------------------------
        Methods for Attribute Tolerance
        --------------------------------------------------------------------- */
        /**
          * (36) set attribute Tolerance
          * @param value: the value to set the attribute to
          */
        public void setTolerance(double value)
        {
            setAttribute(AttributeName.TOLERANCE, value, null);
        }

        /**
          * (17) get double attribute Tolerance
          * @return double the value of the attribute
          */
        public double getTolerance()
        {
            return getRealAttribute(AttributeName.TOLERANCE, null, 0.0);
        }

/* ***********************************************************************
 * Element getter / setter
 * ***********************************************************************
 */

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

}// end namespace JDF
