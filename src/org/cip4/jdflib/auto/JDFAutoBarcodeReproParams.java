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

import org.apache.commons.lang.enums.ValuedEnum;
import org.apache.xerces.dom.CoreDocumentImpl;
import org.cip4.jdflib.core.AtrInfoTable;
import org.cip4.jdflib.core.AttributeInfo;
import org.cip4.jdflib.core.AttributeName;
import org.cip4.jdflib.core.ElemInfoTable;
import org.cip4.jdflib.core.ElementInfo;
import org.cip4.jdflib.core.ElementName;
import org.cip4.jdflib.core.JDFException;
import org.cip4.jdflib.core.VElement;
import org.cip4.jdflib.resource.JDFResource;
import org.cip4.jdflib.resource.process.JDFBarcodeCompParams;
    /**
    *****************************************************************************
    class JDFAutoBarcodeReproParams : public JDFResource

    *****************************************************************************
    */

public abstract class JDFAutoBarcodeReproParams extends JDFResource
{

    private static final long serialVersionUID = 1L;

    private static AtrInfoTable[] atrInfoTable = new AtrInfoTable[7];
    static
    {
        atrInfoTable[0] = new AtrInfoTable(AttributeName.BEARERBARS, 0x33333111, AttributeInfo.EnumAttributeType.enumeration, EnumBearerBars.getEnum(0), null);
        atrInfoTable[1] = new AtrInfoTable(AttributeName.HEIGHT, 0x33333111, AttributeInfo.EnumAttributeType.double_, null, null);
        atrInfoTable[2] = new AtrInfoTable(AttributeName.MAGNIFICATION, 0x33333111, AttributeInfo.EnumAttributeType.double_, null, null);
        atrInfoTable[3] = new AtrInfoTable(AttributeName.MASKING, 0x33333111, AttributeInfo.EnumAttributeType.enumeration, EnumMasking.getEnum(0), null);
        atrInfoTable[4] = new AtrInfoTable(AttributeName.MODULEHEIGHT, 0x33333111, AttributeInfo.EnumAttributeType.double_, null, null);
        atrInfoTable[5] = new AtrInfoTable(AttributeName.MODULEWIDTH, 0x33333111, AttributeInfo.EnumAttributeType.double_, null, null);
        atrInfoTable[6] = new AtrInfoTable(AttributeName.RATIO, 0x33333111, AttributeInfo.EnumAttributeType.double_, null, null);
    }
    
    protected AttributeInfo getTheAttributeInfo()
    {
        return super.getTheAttributeInfo().updateReplace(atrInfoTable);
    }


    private static ElemInfoTable[] elemInfoTable = new ElemInfoTable[1];
    static
    {
        elemInfoTable[0] = new ElemInfoTable(ElementName.BARCODECOMPPARAMS, 0x33333111);
    }
    
    protected ElementInfo getTheElementInfo()
    {
        return super.getTheElementInfo().updateReplace(elemInfoTable);
    }



    /**
     * Constructor for JDFAutoBarcodeReproParams
     * @param myOwnerDocument
     * @param qualifiedName
     */
    protected JDFAutoBarcodeReproParams(
        CoreDocumentImpl myOwnerDocument,
        String qualifiedName)
    {
        super(myOwnerDocument, qualifiedName);
    }

    /**
     * Constructor for JDFAutoBarcodeReproParams
     * @param myOwnerDocument
     * @param myNamespaceURI
     * @param qualifiedName
     */
    protected JDFAutoBarcodeReproParams(
        CoreDocumentImpl myOwnerDocument,
        String myNamespaceURI,
        String qualifiedName)
    {
        super(myOwnerDocument, myNamespaceURI, qualifiedName);
    }

    /**
     * Constructor for JDFAutoBarcodeReproParams
     * @param myOwnerDocument
     * @param myNamespaceURI
     * @param qualifiedName
     * @param myLocalName
     */
    protected JDFAutoBarcodeReproParams(
        CoreDocumentImpl myOwnerDocument,
        String myNamespaceURI,
        String qualifiedName,
        String myLocalName)
    {
        super(myOwnerDocument, myNamespaceURI, qualifiedName, myLocalName);
    }


    public String toString()
    {
        return " JDFAutoBarcodeReproParams[  --> " + super.toString() + " ]";
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
        * Enumeration strings for BearerBars
        */

        public static class EnumBearerBars extends ValuedEnum
        {
            private static final long serialVersionUID = 1L;
            private static int m_startValue = 0;

            private EnumBearerBars(String name)
            {
                super(name, m_startValue++);
            }

            public static EnumBearerBars getEnum(String enumName)
            {
                return (EnumBearerBars) getEnum(EnumBearerBars.class, enumName);
            }

            public static EnumBearerBars getEnum(int enumValue)
            {
                return (EnumBearerBars) getEnum(EnumBearerBars.class, enumValue);
            }

            public static Map getEnumMap()
            {
                return getEnumMap(EnumBearerBars.class);
            }

            public static List getEnumList()
            {
                return getEnumList(EnumBearerBars.class);
            }

            public static Iterator iterator()
            {
                return iterator(EnumBearerBars.class);
            }

            public static final EnumBearerBars None = new EnumBearerBars("None");
            public static final EnumBearerBars TopBottom = new EnumBearerBars("TopBottom");
            public static final EnumBearerBars Box = new EnumBearerBars("Box");
            public static final EnumBearerBars BoxHMarks = new EnumBearerBars("BoxHMarks");
        }      



        /**
        * Enumeration strings for Masking
        */

        public static class EnumMasking extends ValuedEnum
        {
            private static final long serialVersionUID = 1L;
            private static int m_startValue = 0;

            private EnumMasking(String name)
            {
                super(name, m_startValue++);
            }

            public static EnumMasking getEnum(String enumName)
            {
                return (EnumMasking) getEnum(EnumMasking.class, enumName);
            }

            public static EnumMasking getEnum(int enumValue)
            {
                return (EnumMasking) getEnum(EnumMasking.class, enumValue);
            }

            public static Map getEnumMap()
            {
                return getEnumMap(EnumMasking.class);
            }

            public static List getEnumList()
            {
                return getEnumList(EnumMasking.class);
            }

            public static Iterator iterator()
            {
                return iterator(EnumMasking.class);
            }

            public static final EnumMasking None = new EnumMasking("None");
            public static final EnumMasking WhiteBox = new EnumMasking("WhiteBox");
        }      



/* ************************************************************************
 * Attribute getter / setter
 * ************************************************************************
 */
        
        /* ---------------------------------------------------------------------
        Methods for Attribute BearerBars
        --------------------------------------------------------------------- */
        /**
          * (5) set attribute BearerBars
          * @param enumVar: the enumVar to set the attribute to
          */
        public void setBearerBars(EnumBearerBars enumVar)
        {
            setAttribute(AttributeName.BEARERBARS, enumVar==null ? null : enumVar.getName(), null);
        }

        /**
          * (9) get attribute BearerBars
          * @return the value of the attribute
          */
        public EnumBearerBars getBearerBars()
        {
            return EnumBearerBars.getEnum(getAttribute(AttributeName.BEARERBARS, null, null));
        }

        
        /* ---------------------------------------------------------------------
        Methods for Attribute Height
        --------------------------------------------------------------------- */
        /**
          * (36) set attribute Height
          * @param value: the value to set the attribute to
          */
        public void setHeight(double value)
        {
            setAttribute(AttributeName.HEIGHT, value, null);
        }

        /**
          * (17) get double attribute Height
          * @return double the value of the attribute
          */
        public double getHeight()
        {
            return getRealAttribute(AttributeName.HEIGHT, null, 0.0);
        }

        
        /* ---------------------------------------------------------------------
        Methods for Attribute Magnification
        --------------------------------------------------------------------- */
        /**
          * (36) set attribute Magnification
          * @param value: the value to set the attribute to
          */
        public void setMagnification(double value)
        {
            setAttribute(AttributeName.MAGNIFICATION, value, null);
        }

        /**
          * (17) get double attribute Magnification
          * @return double the value of the attribute
          */
        public double getMagnification()
        {
            return getRealAttribute(AttributeName.MAGNIFICATION, null, 0.0);
        }

        
        /* ---------------------------------------------------------------------
        Methods for Attribute Masking
        --------------------------------------------------------------------- */
        /**
          * (5) set attribute Masking
          * @param enumVar: the enumVar to set the attribute to
          */
        public void setMasking(EnumMasking enumVar)
        {
            setAttribute(AttributeName.MASKING, enumVar==null ? null : enumVar.getName(), null);
        }

        /**
          * (9) get attribute Masking
          * @return the value of the attribute
          */
        public EnumMasking getMasking()
        {
            return EnumMasking.getEnum(getAttribute(AttributeName.MASKING, null, null));
        }

        
        /* ---------------------------------------------------------------------
        Methods for Attribute ModuleHeight
        --------------------------------------------------------------------- */
        /**
          * (36) set attribute ModuleHeight
          * @param value: the value to set the attribute to
          */
        public void setModuleHeight(double value)
        {
            setAttribute(AttributeName.MODULEHEIGHT, value, null);
        }

        /**
          * (17) get double attribute ModuleHeight
          * @return double the value of the attribute
          */
        public double getModuleHeight()
        {
            return getRealAttribute(AttributeName.MODULEHEIGHT, null, 0.0);
        }

        
        /* ---------------------------------------------------------------------
        Methods for Attribute ModuleWidth
        --------------------------------------------------------------------- */
        /**
          * (36) set attribute ModuleWidth
          * @param value: the value to set the attribute to
          */
        public void setModuleWidth(double value)
        {
            setAttribute(AttributeName.MODULEWIDTH, value, null);
        }

        /**
          * (17) get double attribute ModuleWidth
          * @return double the value of the attribute
          */
        public double getModuleWidth()
        {
            return getRealAttribute(AttributeName.MODULEWIDTH, null, 0.0);
        }

        
        /* ---------------------------------------------------------------------
        Methods for Attribute Ratio
        --------------------------------------------------------------------- */
        /**
          * (36) set attribute Ratio
          * @param value: the value to set the attribute to
          */
        public void setRatio(double value)
        {
            setAttribute(AttributeName.RATIO, value, null);
        }

        /**
          * (17) get double attribute Ratio
          * @return double the value of the attribute
          */
        public double getRatio()
        {
            return getRealAttribute(AttributeName.RATIO, null, 0.0);
        }

/* ***********************************************************************
 * Element getter / setter
 * ***********************************************************************
 */

    /** (26) getCreateBarcodeCompParams
     * 
     * @param iSkip number of elements to skip
     * @return JDFBarcodeCompParams the element
     */
    public JDFBarcodeCompParams getCreateBarcodeCompParams(int iSkip)
    {
        return (JDFBarcodeCompParams)getCreateElement_KElement(ElementName.BARCODECOMPPARAMS, null, iSkip);
    }

    /**
     * (27) const get element BarcodeCompParams
     * @param iSkip number of elements to skip
     * @return JDFBarcodeCompParams the element
     * default is getBarcodeCompParams(0)     */
    public JDFBarcodeCompParams getBarcodeCompParams(int iSkip)
    {
        return (JDFBarcodeCompParams) getElement(ElementName.BARCODECOMPPARAMS, null, iSkip);
    }

    /**
     * Get all BarcodeCompParams from the current element
     * 
     * @return Collection<JDFBarcodeCompParams>, null if none are available
     */
    public Collection<JDFBarcodeCompParams> getAllBarcodeCompParams()
    {
        final VElement vc = getChildElementVector(ElementName.BARCODECOMPPARAMS, null);
        if (vc == null || vc.size() == 0)
        {
            return null;
        }

        final Vector<JDFBarcodeCompParams> v = new Vector<JDFBarcodeCompParams>();
        for (int i = 0; i < vc.size(); i++)
        {
            v.add((JDFBarcodeCompParams) vc.get(i));
        }

        return v;
    }

    /**
     * (30) append element BarcodeCompParams
     */
    public JDFBarcodeCompParams appendBarcodeCompParams() throws JDFException
    {
        return (JDFBarcodeCompParams) appendElement(ElementName.BARCODECOMPPARAMS, null);
    }

    /**
      * (31) create inter-resource link to refTarget
      * @param refTarget the element that is referenced
      */
    public void refBarcodeCompParams(JDFBarcodeCompParams refTarget)
    {
        refElement(refTarget);
    }

}// end namespace JDF
