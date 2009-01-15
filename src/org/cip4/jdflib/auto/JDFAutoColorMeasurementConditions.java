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
import org.cip4.jdflib.core.JDFConstants;
import org.cip4.jdflib.resource.JDFResource;

public abstract class JDFAutoColorMeasurementConditions extends JDFResource
{

    private static final long serialVersionUID = 1L;

    private static AtrInfoTable[] atrInfoTable = new AtrInfoTable[8];
    static
    {
        atrInfoTable[0] = new AtrInfoTable(AttributeName.DENSITYSTANDARD, 0x33333331, AttributeInfo.EnumAttributeType.enumeration, EnumDensityStandard.getEnum(0), "ANSIT");
        atrInfoTable[1] = new AtrInfoTable(AttributeName.ILLUMINATION, 0x33333331, AttributeInfo.EnumAttributeType.enumeration, EnumIllumination.getEnum(0), "D50");
        atrInfoTable[2] = new AtrInfoTable(AttributeName.OBSERVER, 0x33333331, AttributeInfo.EnumAttributeType.integer, null, "2");
        atrInfoTable[3] = new AtrInfoTable(AttributeName.INSTRUMENTATION, 0x33333331, AttributeInfo.EnumAttributeType.string, null, null);
        atrInfoTable[4] = new AtrInfoTable(AttributeName.INKSTATE, 0x33333331, AttributeInfo.EnumAttributeType.enumeration, EnumInkState.getEnum(0), null);
        atrInfoTable[5] = new AtrInfoTable(AttributeName.MEASUREMENTFILTER, 0x33333331, AttributeInfo.EnumAttributeType.enumeration, EnumMeasurementFilter.getEnum(0), null);
        atrInfoTable[6] = new AtrInfoTable(AttributeName.SAMPLEBACKING, 0x33333331, AttributeInfo.EnumAttributeType.enumeration, EnumSampleBacking.getEnum(0), null);
        atrInfoTable[7] = new AtrInfoTable(AttributeName.WHITEBASE, 0x33333331, AttributeInfo.EnumAttributeType.enumeration, EnumWhiteBase.getEnum(0), null);
    }
    
    @Override
	protected AttributeInfo getTheAttributeInfo()
    {
        return super.getTheAttributeInfo().updateReplace(atrInfoTable);
    }



    /**
     * Constructor for JDFAutoColorMeasurementConditions
     * @param myOwnerDocument
     * @param qualifiedName
     */
    protected JDFAutoColorMeasurementConditions(
        CoreDocumentImpl myOwnerDocument,
        String qualifiedName)
    {
        super(myOwnerDocument, qualifiedName);
    }

    /**
     * Constructor for JDFAutoColorMeasurementConditions
     * @param myOwnerDocument
     * @param myNamespaceURI
     * @param qualifiedName
     */
    protected JDFAutoColorMeasurementConditions(
        CoreDocumentImpl myOwnerDocument,
        String myNamespaceURI,
        String qualifiedName)
    {
        super(myOwnerDocument, myNamespaceURI, qualifiedName);
    }

    /**
     * Constructor for JDFAutoColorMeasurementConditions
     * @param myOwnerDocument
     * @param myNamespaceURI
     * @param qualifiedName
     * @param myLocalName
     */
    protected JDFAutoColorMeasurementConditions(
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
        return " JDFAutoColorMeasurementConditions[  --> " + super.toString() + " ]";
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



        /**
        * Enumeration strings for Illumination
        */

        public static class EnumIllumination extends ValuedEnum
        {
            private static final long serialVersionUID = 1L;
            private static int m_startValue = 0;

            private EnumIllumination(String name)
            {
                super(name, m_startValue++);
            }

            public static EnumIllumination getEnum(String enumName)
            {
                return (EnumIllumination) getEnum(EnumIllumination.class, enumName);
            }

            public static EnumIllumination getEnum(int enumValue)
            {
                return (EnumIllumination) getEnum(EnumIllumination.class, enumValue);
            }

            public static Map getEnumMap()
            {
                return getEnumMap(EnumIllumination.class);
            }

            public static List getEnumList()
            {
                return getEnumList(EnumIllumination.class);
            }

            public static Iterator iterator()
            {
                return iterator(EnumIllumination.class);
            }

            public static final EnumIllumination D50 = new EnumIllumination("D50");
            public static final EnumIllumination D65 = new EnumIllumination("D65");
            public static final EnumIllumination Unknown = new EnumIllumination("Unknown");
        }      



        /**
        * Enumeration strings for InkState
        */

        public static class EnumInkState extends ValuedEnum
        {
            private static final long serialVersionUID = 1L;
            private static int m_startValue = 0;

            private EnumInkState(String name)
            {
                super(name, m_startValue++);
            }

            public static EnumInkState getEnum(String enumName)
            {
                return (EnumInkState) getEnum(EnumInkState.class, enumName);
            }

            public static EnumInkState getEnum(int enumValue)
            {
                return (EnumInkState) getEnum(EnumInkState.class, enumValue);
            }

            public static Map getEnumMap()
            {
                return getEnumMap(EnumInkState.class);
            }

            public static List getEnumList()
            {
                return getEnumList(EnumInkState.class);
            }

            public static Iterator iterator()
            {
                return iterator(EnumInkState.class);
            }

            public static final EnumInkState Dry = new EnumInkState("Dry");
            public static final EnumInkState Wet = new EnumInkState("Wet");
            public static final EnumInkState NA = new EnumInkState("NA");
        }      



        /**
        * Enumeration strings for MeasurementFilter
        */

        public static class EnumMeasurementFilter extends ValuedEnum
        {
            private static final long serialVersionUID = 1L;
            private static int m_startValue = 0;

            private EnumMeasurementFilter(String name)
            {
                super(name, m_startValue++);
            }

            public static EnumMeasurementFilter getEnum(String enumName)
            {
                return (EnumMeasurementFilter) getEnum(EnumMeasurementFilter.class, enumName);
            }

            public static EnumMeasurementFilter getEnum(int enumValue)
            {
                return (EnumMeasurementFilter) getEnum(EnumMeasurementFilter.class, enumValue);
            }

            public static Map getEnumMap()
            {
                return getEnumMap(EnumMeasurementFilter.class);
            }

            public static List getEnumList()
            {
                return getEnumList(EnumMeasurementFilter.class);
            }

            public static Iterator iterator()
            {
                return iterator(EnumMeasurementFilter.class);
            }

            public static final EnumMeasurementFilter None = new EnumMeasurementFilter("None");
            public static final EnumMeasurementFilter Pol = new EnumMeasurementFilter("Pol");
            public static final EnumMeasurementFilter UV = new EnumMeasurementFilter("UV");
        }      



        /**
        * Enumeration strings for SampleBacking
        */

        public static class EnumSampleBacking extends ValuedEnum
        {
            private static final long serialVersionUID = 1L;
            private static int m_startValue = 0;

            private EnumSampleBacking(String name)
            {
                super(name, m_startValue++);
            }

            public static EnumSampleBacking getEnum(String enumName)
            {
                return (EnumSampleBacking) getEnum(EnumSampleBacking.class, enumName);
            }

            public static EnumSampleBacking getEnum(int enumValue)
            {
                return (EnumSampleBacking) getEnum(EnumSampleBacking.class, enumValue);
            }

            public static Map getEnumMap()
            {
                return getEnumMap(EnumSampleBacking.class);
            }

            public static List getEnumList()
            {
                return getEnumList(EnumSampleBacking.class);
            }

            public static Iterator iterator()
            {
                return iterator(EnumSampleBacking.class);
            }

            public static final EnumSampleBacking Black = new EnumSampleBacking("Black");
            public static final EnumSampleBacking White = new EnumSampleBacking("White");
            public static final EnumSampleBacking NA = new EnumSampleBacking("NA");
        }      



        /**
        * Enumeration strings for WhiteBase
        */

        public static class EnumWhiteBase extends ValuedEnum
        {
            private static final long serialVersionUID = 1L;
            private static int m_startValue = 0;

            private EnumWhiteBase(String name)
            {
                super(name, m_startValue++);
            }

            public static EnumWhiteBase getEnum(String enumName)
            {
                return (EnumWhiteBase) getEnum(EnumWhiteBase.class, enumName);
            }

            public static EnumWhiteBase getEnum(int enumValue)
            {
                return (EnumWhiteBase) getEnum(EnumWhiteBase.class, enumValue);
            }

            public static Map getEnumMap()
            {
                return getEnumMap(EnumWhiteBase.class);
            }

            public static List getEnumList()
            {
                return getEnumList(EnumWhiteBase.class);
            }

            public static Iterator iterator()
            {
                return iterator(EnumWhiteBase.class);
            }

            public static final EnumWhiteBase Absolute = new EnumWhiteBase("Absolute");
            public static final EnumWhiteBase Paper = new EnumWhiteBase("Paper");
        }      



/* ************************************************************************
 * Attribute getter / setter
 * ************************************************************************
 */
        
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
            return EnumDensityStandard.getEnum(getAttribute(AttributeName.DENSITYSTANDARD, null, "ANSIT"));
        }

        
        /* ---------------------------------------------------------------------
        Methods for Attribute Illumination
        --------------------------------------------------------------------- */
        /**
          * (5) set attribute Illumination
          * @param enumVar: the enumVar to set the attribute to
          */
        public void setIllumination(EnumIllumination enumVar)
        {
            setAttribute(AttributeName.ILLUMINATION, enumVar==null ? null : enumVar.getName(), null);
        }

        /**
          * (9) get attribute Illumination
          * @return the value of the attribute
          */
        public EnumIllumination getIllumination()
        {
            return EnumIllumination.getEnum(getAttribute(AttributeName.ILLUMINATION, null, "D50"));
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
            return getIntAttribute(AttributeName.OBSERVER, null, 2);
        }

        
        /* ---------------------------------------------------------------------
        Methods for Attribute Instrumentation
        --------------------------------------------------------------------- */
        /**
          * (36) set attribute Instrumentation
          * @param value: the value to set the attribute to
          */
        public void setInstrumentation(String value)
        {
            setAttribute(AttributeName.INSTRUMENTATION, value, null);
        }

        /**
          * (23) get String attribute Instrumentation
          * @return the value of the attribute
          */
        public String getInstrumentation()
        {
            return getAttribute(AttributeName.INSTRUMENTATION, null, JDFConstants.EMPTYSTRING);
        }

        
        /* ---------------------------------------------------------------------
        Methods for Attribute InkState
        --------------------------------------------------------------------- */
        /**
          * (5) set attribute InkState
          * @param enumVar: the enumVar to set the attribute to
          */
        public void setInkState(EnumInkState enumVar)
        {
            setAttribute(AttributeName.INKSTATE, enumVar==null ? null : enumVar.getName(), null);
        }

        /**
          * (9) get attribute InkState
          * @return the value of the attribute
          */
        public EnumInkState getInkState()
        {
            return EnumInkState.getEnum(getAttribute(AttributeName.INKSTATE, null, null));
        }

        
        /* ---------------------------------------------------------------------
        Methods for Attribute MeasurementFilter
        --------------------------------------------------------------------- */
        /**
          * (5) set attribute MeasurementFilter
          * @param enumVar: the enumVar to set the attribute to
          */
        public void setMeasurementFilter(EnumMeasurementFilter enumVar)
        {
            setAttribute(AttributeName.MEASUREMENTFILTER, enumVar==null ? null : enumVar.getName(), null);
        }

        /**
          * (9) get attribute MeasurementFilter
          * @return the value of the attribute
          */
        public EnumMeasurementFilter getMeasurementFilter()
        {
            return EnumMeasurementFilter.getEnum(getAttribute(AttributeName.MEASUREMENTFILTER, null, null));
        }

        
        /* ---------------------------------------------------------------------
        Methods for Attribute SampleBacking
        --------------------------------------------------------------------- */
        /**
          * (5) set attribute SampleBacking
          * @param enumVar: the enumVar to set the attribute to
          */
        public void setSampleBacking(EnumSampleBacking enumVar)
        {
            setAttribute(AttributeName.SAMPLEBACKING, enumVar==null ? null : enumVar.getName(), null);
        }

        /**
          * (9) get attribute SampleBacking
          * @return the value of the attribute
          */
        public EnumSampleBacking getSampleBacking()
        {
            return EnumSampleBacking.getEnum(getAttribute(AttributeName.SAMPLEBACKING, null, null));
        }

        
        /* ---------------------------------------------------------------------
        Methods for Attribute WhiteBase
        --------------------------------------------------------------------- */
        /**
          * (5) set attribute WhiteBase
          * @param enumVar: the enumVar to set the attribute to
          */
        public void setWhiteBase(EnumWhiteBase enumVar)
        {
            setAttribute(AttributeName.WHITEBASE, enumVar==null ? null : enumVar.getName(), null);
        }

        /**
          * (9) get attribute WhiteBase
          * @return the value of the attribute
          */
        public EnumWhiteBase getWhiteBase()
        {
            return EnumWhiteBase.getEnum(getAttribute(AttributeName.WHITEBASE, null, null));
        }

}// end namespace JDF
