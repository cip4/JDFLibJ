/*
 * The CIP4 Software License, Version 1.0
 *
 *
 * Copyright (c) 2001-2012 The International Cooperation for the Integration of
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
import org.cip4.jdflib.core.JDFCoreConstants;
import org.cip4.jdflib.core.VElement;
import org.cip4.jdflib.resource.JDFResource;
import org.cip4.jdflib.resource.process.postpress.JDFGlueApplication;
    /**
    *****************************************************************************
    class JDFAutoSpineTapingParams : public JDFResource

    *****************************************************************************
    */

public abstract class JDFAutoSpineTapingParams extends JDFResource
{

    private static final long serialVersionUID = 1L;

    private static AtrInfoTable[] atrInfoTable = new AtrInfoTable[8];
    static
    {
        atrInfoTable[0] = new AtrInfoTable(AttributeName.TOPEXCESS, 0x33333331, AttributeInfo.EnumAttributeType.double_, null, "0.0");
        atrInfoTable[1] = new AtrInfoTable(AttributeName.HORIZONTALEXCESS, 0x33333331, AttributeInfo.EnumAttributeType.double_, null, null);
        atrInfoTable[2] = new AtrInfoTable(AttributeName.HORIZONTALEXCESSBACK, 0x33331111, AttributeInfo.EnumAttributeType.double_, null, null);
        atrInfoTable[3] = new AtrInfoTable(AttributeName.STRIPBRAND, 0x33333331, AttributeInfo.EnumAttributeType.string, null, null);
        atrInfoTable[4] = new AtrInfoTable(AttributeName.STRIPCOLOR, 0x33333331, AttributeInfo.EnumAttributeType.string, null, null);
        atrInfoTable[5] = new AtrInfoTable(AttributeName.STRIPCOLORDETAILS, 0x33331111, AttributeInfo.EnumAttributeType.string, null, null);
        atrInfoTable[6] = new AtrInfoTable(AttributeName.STRIPLENGTH, 0x33333331, AttributeInfo.EnumAttributeType.double_, null, null);
        atrInfoTable[7] = new AtrInfoTable(AttributeName.STRIPMATERIAL, 0x33333331, AttributeInfo.EnumAttributeType.enumeration, EnumStripMaterial.getEnum(0), null);
    }
    
    protected AttributeInfo getTheAttributeInfo()
    {
        return super.getTheAttributeInfo().updateReplace(atrInfoTable);
    }


    private static ElemInfoTable[] elemInfoTable = new ElemInfoTable[1];
    static
    {
        elemInfoTable[0] = new ElemInfoTable(ElementName.GLUEAPPLICATION, 0x33333331);
    }
    
    protected ElementInfo getTheElementInfo()
    {
        return super.getTheElementInfo().updateReplace(elemInfoTable);
    }



    /**
     * Constructor for JDFAutoSpineTapingParams
     * @param myOwnerDocument
     * @param qualifiedName
     */
    protected JDFAutoSpineTapingParams(
        CoreDocumentImpl myOwnerDocument,
        String qualifiedName)
    {
        super(myOwnerDocument, qualifiedName);
    }

    /**
     * Constructor for JDFAutoSpineTapingParams
     * @param myOwnerDocument
     * @param myNamespaceURI
     * @param qualifiedName
     */
    protected JDFAutoSpineTapingParams(
        CoreDocumentImpl myOwnerDocument,
        String myNamespaceURI,
        String qualifiedName)
    {
        super(myOwnerDocument, myNamespaceURI, qualifiedName);
    }

    /**
     * Constructor for JDFAutoSpineTapingParams
     * @param myOwnerDocument
     * @param myNamespaceURI
     * @param qualifiedName
     * @param myLocalName
     */
    protected JDFAutoSpineTapingParams(
        CoreDocumentImpl myOwnerDocument,
        String myNamespaceURI,
        String qualifiedName,
        String myLocalName)
    {
        super(myOwnerDocument, myNamespaceURI, qualifiedName, myLocalName);
    }


    /**
     * @return  the string representation
     */
    @Override
    public String toString()
    {
        return " JDFAutoSpineTapingParams[  --> " + super.toString() + " ]";
    }


    /**
     * @return  true if ok
     */
    @Override
    public boolean  init()
    {
        boolean bRet = super.init();
        setResourceClass(JDFResource.EnumResourceClass.Parameter);
        return bRet;
    }


    /**
     * @return the resource Class
     */
    @Override
    public EnumResourceClass getValidClass()
    {
        return JDFResource.EnumResourceClass.Parameter;
    }


        /**
        * Enumeration strings for StripMaterial
        */

        public static class EnumStripMaterial extends ValuedEnum
        {
            private static final long serialVersionUID = 1L;
            private static int m_startValue = 0;

            private EnumStripMaterial(String name)
            {
                super(name, m_startValue++);
            }

    /**
     * @param enumName the string to convert
     * @return the enum
     */
            public static EnumStripMaterial getEnum(String enumName)
            {
                return (EnumStripMaterial) getEnum(EnumStripMaterial.class, enumName);
            }

    /**
     * @param enumValue the integer to convert
     * @return the enum
     */
            public static EnumStripMaterial getEnum(int enumValue)
            {
                return (EnumStripMaterial) getEnum(EnumStripMaterial.class, enumValue);
            }

    /**
     * @return the map of enums
     */
            public static Map getEnumMap()
            {
                return getEnumMap(EnumStripMaterial.class);
            }

    /**
     * @return the list of enums
     */
            public static List getEnumList()
            {
                return getEnumList(EnumStripMaterial.class);
            }

    /**
     * @return the iterator
     */
            public static Iterator iterator()
            {
                return iterator(EnumStripMaterial.class);
            }

            public static final EnumStripMaterial Calico = new EnumStripMaterial("Calico");
            public static final EnumStripMaterial Cardboard = new EnumStripMaterial("Cardboard");
            public static final EnumStripMaterial CrepePaper = new EnumStripMaterial("CrepePaper");
            public static final EnumStripMaterial Gauze = new EnumStripMaterial("Gauze");
            public static final EnumStripMaterial Paper = new EnumStripMaterial("Paper");
            public static final EnumStripMaterial PaperlinedMules = new EnumStripMaterial("PaperlinedMules");
            public static final EnumStripMaterial Tape = new EnumStripMaterial("Tape");
        }      



/* ************************************************************************
 * Attribute getter / setter
 * ************************************************************************
 */
        
        /* ---------------------------------------------------------------------
        Methods for Attribute TopExcess
        --------------------------------------------------------------------- */
        /**
          * (36) set attribute TopExcess
          * @param value the value to set the attribute to
          */
        public void setTopExcess(double value)
        {
            setAttribute(AttributeName.TOPEXCESS, value, null);
        }

        /**
          * (17) get double attribute TopExcess
          * @return double the value of the attribute
          */
        public double getTopExcess()
        {
            return getRealAttribute(AttributeName.TOPEXCESS, null, 0.0);
        }

        
        /* ---------------------------------------------------------------------
        Methods for Attribute HorizontalExcess
        --------------------------------------------------------------------- */
        /**
          * (36) set attribute HorizontalExcess
          * @param value the value to set the attribute to
          */
        public void setHorizontalExcess(double value)
        {
            setAttribute(AttributeName.HORIZONTALEXCESS, value, null);
        }

        /**
          * (17) get double attribute HorizontalExcess
          * @return double the value of the attribute
          */
        public double getHorizontalExcess()
        {
            return getRealAttribute(AttributeName.HORIZONTALEXCESS, null, 0.0);
        }

        
        /* ---------------------------------------------------------------------
        Methods for Attribute HorizontalExcessBack
        --------------------------------------------------------------------- */
        /**
          * (36) set attribute HorizontalExcessBack
          * @param value the value to set the attribute to
          */
        public void setHorizontalExcessBack(double value)
        {
            setAttribute(AttributeName.HORIZONTALEXCESSBACK, value, null);
        }

        /**
          * (17) get double attribute HorizontalExcessBack
          * @return double the value of the attribute
          */
        public double getHorizontalExcessBack()
        {
            return getRealAttribute(AttributeName.HORIZONTALEXCESSBACK, null, 0.0);
        }

        
        /* ---------------------------------------------------------------------
        Methods for Attribute StripBrand
        --------------------------------------------------------------------- */
        /**
          * (36) set attribute StripBrand
          * @param value the value to set the attribute to
          */
        public void setStripBrand(String value)
        {
            setAttribute(AttributeName.STRIPBRAND, value, null);
        }

        /**
          * (23) get String attribute StripBrand
          * @return the value of the attribute
          */
        public String getStripBrand()
        {
            return getAttribute(AttributeName.STRIPBRAND, null, JDFCoreConstants.EMPTYSTRING);
        }

        
        /* ---------------------------------------------------------------------
        Methods for Attribute StripColor
        --------------------------------------------------------------------- */
        /**
          * (13) set attribute StripColor
          * @param value the value to set the attribute to
          */
        public void setStripColor(EnumNamedColor value)
        {
            setAttribute(AttributeName.STRIPCOLOR, value==null ? null : value.getName(), null);
        }

        /**
          * (19) get EnumNamedColor attribute StripColor
          * @return EnumNamedColor the value of the attribute
          */
        public EnumNamedColor getStripColor()
        {
            String strAttrName = "";
            EnumNamedColor nPlaceHolder = null;
            strAttrName = getAttribute(AttributeName.STRIPCOLOR, null, JDFCoreConstants.EMPTYSTRING);
            nPlaceHolder = EnumNamedColor.getEnum(strAttrName);
            return nPlaceHolder;
        }

        
        /* ---------------------------------------------------------------------
        Methods for Attribute StripColorDetails
        --------------------------------------------------------------------- */
        /**
          * (36) set attribute StripColorDetails
          * @param value the value to set the attribute to
          */
        public void setStripColorDetails(String value)
        {
            setAttribute(AttributeName.STRIPCOLORDETAILS, value, null);
        }

        /**
          * (23) get String attribute StripColorDetails
          * @return the value of the attribute
          */
        public String getStripColorDetails()
        {
            return getAttribute(AttributeName.STRIPCOLORDETAILS, null, JDFCoreConstants.EMPTYSTRING);
        }

        
        /* ---------------------------------------------------------------------
        Methods for Attribute StripLength
        --------------------------------------------------------------------- */
        /**
          * (36) set attribute StripLength
          * @param value the value to set the attribute to
          */
        public void setStripLength(double value)
        {
            setAttribute(AttributeName.STRIPLENGTH, value, null);
        }

        /**
          * (17) get double attribute StripLength
          * @return double the value of the attribute
          */
        public double getStripLength()
        {
            return getRealAttribute(AttributeName.STRIPLENGTH, null, 0.0);
        }

        
        /* ---------------------------------------------------------------------
        Methods for Attribute StripMaterial
        --------------------------------------------------------------------- */
        /**
          * (5) set attribute StripMaterial
          * @param enumVar the enumVar to set the attribute to
          */
        public void setStripMaterial(EnumStripMaterial enumVar)
        {
            setAttribute(AttributeName.STRIPMATERIAL, enumVar==null ? null : enumVar.getName(), null);
        }

        /**
          * (9) get attribute StripMaterial
          * @return the value of the attribute
          */
        public EnumStripMaterial getStripMaterial()
        {
            return EnumStripMaterial.getEnum(getAttribute(AttributeName.STRIPMATERIAL, null, null));
        }

/* ***********************************************************************
 * Element getter / setter
 * ***********************************************************************
 */

    /** (26) getCreateGlueApplication
     * 
     * @param iSkip number of elements to skip
     * @return JDFGlueApplication the element
     */
    public JDFGlueApplication getCreateGlueApplication(int iSkip)
    {
        return (JDFGlueApplication)getCreateElement_KElement(ElementName.GLUEAPPLICATION, null, iSkip);
    }

    /**
     * (27) const get element GlueApplication
     * @param iSkip number of elements to skip
     * @return JDFGlueApplication the element
     * default is getGlueApplication(0)     */
    public JDFGlueApplication getGlueApplication(int iSkip)
    {
        return (JDFGlueApplication) getElement(ElementName.GLUEAPPLICATION, null, iSkip);
    }

    /**
     * Get all GlueApplication from the current element
     * 
     * @return Collection<JDFGlueApplication>, null if none are available
     */
    public Collection<JDFGlueApplication> getAllGlueApplication()
    {
        final VElement vc = getChildElementVector(ElementName.GLUEAPPLICATION, null);
        if (vc == null || vc.size() == 0)
        {
            return null;
        }

        final Vector<JDFGlueApplication> v = new Vector<JDFGlueApplication>();
        for (int i = 0; i < vc.size(); i++)
        {
            v.add((JDFGlueApplication) vc.get(i));
        }

        return v;
    }

    /**
     * (30) append element GlueApplication
     * @return JDFGlueApplication the element
     */
    public JDFGlueApplication appendGlueApplication()
    {
        return (JDFGlueApplication) appendElement(ElementName.GLUEAPPLICATION, null);
    }

    /**
      * (31) create inter-resource link to refTarget
      * @param refTarget the element that is referenced
      */
    public void refGlueApplication(JDFGlueApplication refTarget)
    {
        refElement(refTarget);
    }

}// end namespace JDF
