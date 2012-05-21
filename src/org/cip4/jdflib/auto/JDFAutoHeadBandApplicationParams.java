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
import org.cip4.jdflib.resource.process.postpress.JDFGlueLine;
    /**
    *****************************************************************************
    class JDFAutoHeadBandApplicationParams : public JDFResource

    *****************************************************************************
    */

public abstract class JDFAutoHeadBandApplicationParams extends JDFResource
{

    private static final long serialVersionUID = 1L;

    private static AtrInfoTable[] atrInfoTable = new AtrInfoTable[10];
    static
    {
        atrInfoTable[0] = new AtrInfoTable(AttributeName.BOTTOMBRAND, 0x33333331, AttributeInfo.EnumAttributeType.string, null, null);
        atrInfoTable[1] = new AtrInfoTable(AttributeName.BOTTOMCOLOR, 0x33333331, AttributeInfo.EnumAttributeType.string, null, null);
        atrInfoTable[2] = new AtrInfoTable(AttributeName.BOTTOMCOLORDETAILS, 0x33333331, AttributeInfo.EnumAttributeType.string, null, null);
        atrInfoTable[3] = new AtrInfoTable(AttributeName.BOTTOMLENGTH, 0x33333331, AttributeInfo.EnumAttributeType.double_, null, null);
        atrInfoTable[4] = new AtrInfoTable(AttributeName.TOPBRAND, 0x33333331, AttributeInfo.EnumAttributeType.string, null, null);
        atrInfoTable[5] = new AtrInfoTable(AttributeName.TOPCOLOR, 0x33333331, AttributeInfo.EnumAttributeType.string, null, null);
        atrInfoTable[6] = new AtrInfoTable(AttributeName.TOPCOLORDETAILS, 0x33333331, AttributeInfo.EnumAttributeType.string, null, null);
        atrInfoTable[7] = new AtrInfoTable(AttributeName.TOPLENGTH, 0x33333331, AttributeInfo.EnumAttributeType.double_, null, null);
        atrInfoTable[8] = new AtrInfoTable(AttributeName.STRIPMATERIAL, 0x33333331, AttributeInfo.EnumAttributeType.enumeration, EnumStripMaterial.getEnum(0), null);
        atrInfoTable[9] = new AtrInfoTable(AttributeName.WIDTH, 0x33333331, AttributeInfo.EnumAttributeType.double_, null, null);
    }
    
    protected AttributeInfo getTheAttributeInfo()
    {
        return super.getTheAttributeInfo().updateReplace(atrInfoTable);
    }


    private static ElemInfoTable[] elemInfoTable = new ElemInfoTable[1];
    static
    {
        elemInfoTable[0] = new ElemInfoTable(ElementName.GLUELINE, 0x33333331);
    }
    
    protected ElementInfo getTheElementInfo()
    {
        return super.getTheElementInfo().updateReplace(elemInfoTable);
    }



    /**
     * Constructor for JDFAutoHeadBandApplicationParams
     * @param myOwnerDocument
     * @param qualifiedName
     */
    protected JDFAutoHeadBandApplicationParams(
        CoreDocumentImpl myOwnerDocument,
        String qualifiedName)
    {
        super(myOwnerDocument, qualifiedName);
    }

    /**
     * Constructor for JDFAutoHeadBandApplicationParams
     * @param myOwnerDocument
     * @param myNamespaceURI
     * @param qualifiedName
     */
    protected JDFAutoHeadBandApplicationParams(
        CoreDocumentImpl myOwnerDocument,
        String myNamespaceURI,
        String qualifiedName)
    {
        super(myOwnerDocument, myNamespaceURI, qualifiedName);
    }

    /**
     * Constructor for JDFAutoHeadBandApplicationParams
     * @param myOwnerDocument
     * @param myNamespaceURI
     * @param qualifiedName
     * @param myLocalName
     */
    protected JDFAutoHeadBandApplicationParams(
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
        return " JDFAutoHeadBandApplicationParams[  --> " + super.toString() + " ]";
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
        Methods for Attribute BottomBrand
        --------------------------------------------------------------------- */
        /**
          * (36) set attribute BottomBrand
          * @param value the value to set the attribute to
          */
        public void setBottomBrand(String value)
        {
            setAttribute(AttributeName.BOTTOMBRAND, value, null);
        }

        /**
          * (23) get String attribute BottomBrand
          * @return the value of the attribute
          */
        public String getBottomBrand()
        {
            return getAttribute(AttributeName.BOTTOMBRAND, null, JDFCoreConstants.EMPTYSTRING);
        }

        
        /* ---------------------------------------------------------------------
        Methods for Attribute BottomColor
        --------------------------------------------------------------------- */
        /**
          * (13) set attribute BottomColor
          * @param value the value to set the attribute to
          */
        public void setBottomColor(EnumNamedColor value)
        {
            setAttribute(AttributeName.BOTTOMCOLOR, value==null ? null : value.getName(), null);
        }

        /**
          * (19) get EnumNamedColor attribute BottomColor
          * @return EnumNamedColor the value of the attribute
          */
        public EnumNamedColor getBottomColor()
        {
            String strAttrName = "";
            EnumNamedColor nPlaceHolder = null;
            strAttrName = getAttribute(AttributeName.BOTTOMCOLOR, null, JDFCoreConstants.EMPTYSTRING);
            nPlaceHolder = EnumNamedColor.getEnum(strAttrName);
            return nPlaceHolder;
        }

        
        /* ---------------------------------------------------------------------
        Methods for Attribute BottomColorDetails
        --------------------------------------------------------------------- */
        /**
          * (36) set attribute BottomColorDetails
          * @param value the value to set the attribute to
          */
        public void setBottomColorDetails(String value)
        {
            setAttribute(AttributeName.BOTTOMCOLORDETAILS, value, null);
        }

        /**
          * (23) get String attribute BottomColorDetails
          * @return the value of the attribute
          */
        public String getBottomColorDetails()
        {
            return getAttribute(AttributeName.BOTTOMCOLORDETAILS, null, JDFCoreConstants.EMPTYSTRING);
        }

        
        /* ---------------------------------------------------------------------
        Methods for Attribute BottomLength
        --------------------------------------------------------------------- */
        /**
          * (36) set attribute BottomLength
          * @param value the value to set the attribute to
          */
        public void setBottomLength(double value)
        {
            setAttribute(AttributeName.BOTTOMLENGTH, value, null);
        }

        /**
          * (17) get double attribute BottomLength
          * @return double the value of the attribute
          */
        public double getBottomLength()
        {
            return getRealAttribute(AttributeName.BOTTOMLENGTH, null, 0.0);
        }

        
        /* ---------------------------------------------------------------------
        Methods for Attribute TopBrand
        --------------------------------------------------------------------- */
        /**
          * (36) set attribute TopBrand
          * @param value the value to set the attribute to
          */
        public void setTopBrand(String value)
        {
            setAttribute(AttributeName.TOPBRAND, value, null);
        }

        /**
          * (23) get String attribute TopBrand
          * @return the value of the attribute
          */
        public String getTopBrand()
        {
            return getAttribute(AttributeName.TOPBRAND, null, JDFCoreConstants.EMPTYSTRING);
        }

        
        /* ---------------------------------------------------------------------
        Methods for Attribute TopColor
        --------------------------------------------------------------------- */
        /**
          * (13) set attribute TopColor
          * @param value the value to set the attribute to
          */
        public void setTopColor(EnumNamedColor value)
        {
            setAttribute(AttributeName.TOPCOLOR, value==null ? null : value.getName(), null);
        }

        /**
          * (19) get EnumNamedColor attribute TopColor
          * @return EnumNamedColor the value of the attribute
          */
        public EnumNamedColor getTopColor()
        {
            String strAttrName = "";
            EnumNamedColor nPlaceHolder = null;
            strAttrName = getAttribute(AttributeName.TOPCOLOR, null, JDFCoreConstants.EMPTYSTRING);
            nPlaceHolder = EnumNamedColor.getEnum(strAttrName);
            return nPlaceHolder;
        }

        
        /* ---------------------------------------------------------------------
        Methods for Attribute TopColorDetails
        --------------------------------------------------------------------- */
        /**
          * (36) set attribute TopColorDetails
          * @param value the value to set the attribute to
          */
        public void setTopColorDetails(String value)
        {
            setAttribute(AttributeName.TOPCOLORDETAILS, value, null);
        }

        /**
          * (23) get String attribute TopColorDetails
          * @return the value of the attribute
          */
        public String getTopColorDetails()
        {
            return getAttribute(AttributeName.TOPCOLORDETAILS, null, JDFCoreConstants.EMPTYSTRING);
        }

        
        /* ---------------------------------------------------------------------
        Methods for Attribute TopLength
        --------------------------------------------------------------------- */
        /**
          * (36) set attribute TopLength
          * @param value the value to set the attribute to
          */
        public void setTopLength(double value)
        {
            setAttribute(AttributeName.TOPLENGTH, value, null);
        }

        /**
          * (17) get double attribute TopLength
          * @return double the value of the attribute
          */
        public double getTopLength()
        {
            return getRealAttribute(AttributeName.TOPLENGTH, null, 0.0);
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

        
        /* ---------------------------------------------------------------------
        Methods for Attribute Width
        --------------------------------------------------------------------- */
        /**
          * (36) set attribute Width
          * @param value the value to set the attribute to
          */
        public void setWidth(double value)
        {
            setAttribute(AttributeName.WIDTH, value, null);
        }

        /**
          * (17) get double attribute Width
          * @return double the value of the attribute
          */
        public double getWidth()
        {
            return getRealAttribute(AttributeName.WIDTH, null, 0.0);
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
     * @return Collection<JDFGlueLine>, null if none are available
     */
    public Collection<JDFGlueLine> getAllGlueLine()
    {
        final VElement vc = getChildElementVector(ElementName.GLUELINE, null);
        if (vc == null || vc.size() == 0)
        {
            return null;
        }

        final Vector<JDFGlueLine> v = new Vector<JDFGlueLine>();
        for (int i = 0; i < vc.size(); i++)
        {
            v.add((JDFGlueLine) vc.get(i));
        }

        return v;
    }

    /**
     * (30) append element GlueLine
     * @return JDFGlueLine the element
     */
    public JDFGlueLine appendGlueLine()
    {
        return (JDFGlueLine) appendElement(ElementName.GLUELINE, null);
    }

    /**
      * (31) create inter-resource link to refTarget
      * @param refTarget the element that is referenced
      */
    public void refGlueLine(JDFGlueLine refTarget)
    {
        refElement(refTarget);
    }

}// end namespace JDF
