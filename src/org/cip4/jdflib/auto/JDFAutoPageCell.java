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
import org.cip4.jdflib.core.JDFElement;
import org.cip4.jdflib.core.JDFException;
import org.cip4.jdflib.core.VString;
import org.cip4.jdflib.datatypes.JDFRectangle;
import org.cip4.jdflib.datatypes.JDFXYPair;
import org.cip4.jdflib.resource.JDFDeviceMark;
import org.cip4.jdflib.resource.JDFFitPolicy;
import org.cip4.jdflib.resource.JDFImageShift;
import org.cip4.jdflib.resource.process.JDFColor;
    /**
    *****************************************************************************
    class JDFAutoPageCell : public JDFElement

    *****************************************************************************
    */

public abstract class JDFAutoPageCell extends JDFElement
{

    private static final long serialVersionUID = 1L;

    private static AtrInfoTable[] atrInfoTable = new AtrInfoTable[5];
    static
    {
        atrInfoTable[0] = new AtrInfoTable(AttributeName.BORDER, 0x33333331, AttributeInfo.EnumAttributeType.double_, null, null);
        atrInfoTable[1] = new AtrInfoTable(AttributeName.CLIPBOX, 0x33333331, AttributeInfo.EnumAttributeType.rectangle, null, null);
        atrInfoTable[2] = new AtrInfoTable(AttributeName.MARKLIST, 0x33333331, AttributeInfo.EnumAttributeType.NMTOKENS, null, null);
        atrInfoTable[3] = new AtrInfoTable(AttributeName.ROTATE, 0x33333331, AttributeInfo.EnumAttributeType.enumeration, EnumRotate.getEnum(0), "Rotate0");
        atrInfoTable[4] = new AtrInfoTable(AttributeName.TRIMSIZE, 0x33333331, AttributeInfo.EnumAttributeType.XYPair, null, null);
    }
    
    protected AttributeInfo getTheAttributeInfo()
    {
        return super.getTheAttributeInfo().updateReplace(atrInfoTable);
    }


    private static ElemInfoTable[] elemInfoTable = new ElemInfoTable[4];
    static
    {
        elemInfoTable[0] = new ElemInfoTable(ElementName.COLOR, 0x66666661);
        elemInfoTable[1] = new ElemInfoTable(ElementName.DEVICEMARK, 0x66666661);
        elemInfoTable[2] = new ElemInfoTable(ElementName.FITPOLICY, 0x66666661);
        elemInfoTable[3] = new ElemInfoTable(ElementName.IMAGESHIFT, 0x66666661);
    }
    
    protected ElementInfo getTheElementInfo()
    {
        return super.getTheElementInfo().updateReplace(elemInfoTable);
    }



    /**
     * Constructor for JDFAutoPageCell
     * @param myOwnerDocument
     * @param qualifiedName
     */
    protected JDFAutoPageCell(
        CoreDocumentImpl myOwnerDocument,
        String qualifiedName)
    {
        super(myOwnerDocument, qualifiedName);
    }

    /**
     * Constructor for JDFAutoPageCell
     * @param myOwnerDocument
     * @param myNamespaceURI
     * @param qualifiedName
     */
    protected JDFAutoPageCell(
        CoreDocumentImpl myOwnerDocument,
        String myNamespaceURI,
        String qualifiedName)
    {
        super(myOwnerDocument, myNamespaceURI, qualifiedName);
    }

    /**
     * Constructor for JDFAutoPageCell
     * @param myOwnerDocument
     * @param myNamespaceURI
     * @param qualifiedName
     * @param myLocalName
     */
    protected JDFAutoPageCell(
        CoreDocumentImpl myOwnerDocument,
        String myNamespaceURI,
        String qualifiedName,
        String myLocalName)
    {
        super(myOwnerDocument, myNamespaceURI, qualifiedName, myLocalName);
    }


    public String toString()
    {
        return " JDFAutoPageCell[  --> " + super.toString() + " ]";
    }


        /**
        * Enumeration strings for Rotate
        */

        public static class EnumRotate extends ValuedEnum
        {
            private static final long serialVersionUID = 1L;
            private static int m_startValue = 0;

            private EnumRotate(String name)
            {
                super(name, m_startValue++);
            }

            public static EnumRotate getEnum(String enumName)
            {
                return (EnumRotate) getEnum(EnumRotate.class, enumName);
            }

            public static EnumRotate getEnum(int enumValue)
            {
                return (EnumRotate) getEnum(EnumRotate.class, enumValue);
            }

            public static Map getEnumMap()
            {
                return getEnumMap(EnumRotate.class);
            }

            public static List getEnumList()
            {
                return getEnumList(EnumRotate.class);
            }

            public static Iterator iterator()
            {
                return iterator(EnumRotate.class);
            }

            public static final EnumRotate Rotate0 = new EnumRotate("Rotate0");
            public static final EnumRotate Rotate90 = new EnumRotate("Rotate90");
            public static final EnumRotate Rotate180 = new EnumRotate("Rotate180");
            public static final EnumRotate Rotate270 = new EnumRotate("Rotate270");
        }      



/* ************************************************************************
 * Attribute getter / setter
 * ************************************************************************
 */
        
        /* ---------------------------------------------------------------------
        Methods for Attribute Border
        --------------------------------------------------------------------- */
        /**
          * (36) set attribute Border
          * @param value: the value to set the attribute to
          */
        public void setBorder(double value)
        {
            setAttribute(AttributeName.BORDER, value, null);
        }

        /**
          * (17) get double attribute Border
          * @return double the value of the attribute
          */
        public double getBorder()
        {
            return getRealAttribute(AttributeName.BORDER, null, 0.0);
        }

        
        /* ---------------------------------------------------------------------
        Methods for Attribute ClipBox
        --------------------------------------------------------------------- */
        /**
          * (36) set attribute ClipBox
          * @param value: the value to set the attribute to
          */
        public void setClipBox(JDFRectangle value)
        {
            setAttribute(AttributeName.CLIPBOX, value, null);
        }

        /**
          * (20) get JDFRectangle attribute ClipBox
          * @return JDFRectangle the value of the attribute, null if a the
          *         attribute value is not a valid to create a JDFRectangle
          */
        public JDFRectangle getClipBox()
        {
            String strAttrName = "";
            JDFRectangle nPlaceHolder = null;
            strAttrName = getAttribute(AttributeName.CLIPBOX, null, JDFConstants.EMPTYSTRING);
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
        Methods for Attribute MarkList
        --------------------------------------------------------------------- */
        /**
          * (36) set attribute MarkList
          * @param value: the value to set the attribute to
          */
        public void setMarkList(VString value)
        {
            setAttribute(AttributeName.MARKLIST, value, null);
        }

        /**
          * (21) get VString attribute MarkList
          * @return VString the value of the attribute
          */
        public VString getMarkList()
        {
            VString vStrAttrib = new VString();
            String  s = getAttribute(AttributeName.MARKLIST, null, JDFConstants.EMPTYSTRING);
            vStrAttrib.setAllStrings(s, " ");
            return vStrAttrib;
        }

        
        /* ---------------------------------------------------------------------
        Methods for Attribute Rotate
        --------------------------------------------------------------------- */
        /**
          * (5) set attribute Rotate
          * @param enumVar: the enumVar to set the attribute to
          */
        public void setRotate(EnumRotate enumVar)
        {
            setAttribute(AttributeName.ROTATE, enumVar==null ? null : enumVar.getName(), null);
        }

        /**
          * (9) get attribute Rotate
          * @return the value of the attribute
          */
        public EnumRotate getRotate()
        {
            return EnumRotate.getEnum(getAttribute(AttributeName.ROTATE, null, "Rotate0"));
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

/* ***********************************************************************
 * Element getter / setter
 * ***********************************************************************
 */

    /**
     * (24) const get element Color
     * @return JDFColor the element
     */
    public JDFColor getColor()
    {
        return (JDFColor) getElement(ElementName.COLOR, null, 0);
    }

    /** (25) getCreateColor
     * 
     * @return JDFColor the element
     */
    public JDFColor getCreateColor()
    {
        return (JDFColor) getCreateElement_KElement(ElementName.COLOR, null, 0);
    }

    /**
     * (29) append element Color
     */
    public JDFColor appendColor() throws JDFException
    {
        return (JDFColor) appendElementN(ElementName.COLOR, 1, null);
    }

    /**
      * (31) create inter-resource link to refTarget
      * @param refTarget the element that is referenced
      */
    public void refColor(JDFColor refTarget)
    {
        refElement(refTarget);
    }

    /**
     * (24) const get element DeviceMark
     * @return JDFDeviceMark the element
     */
    public JDFDeviceMark getDeviceMark()
    {
        return (JDFDeviceMark) getElement(ElementName.DEVICEMARK, null, 0);
    }

    /** (25) getCreateDeviceMark
     * 
     * @return JDFDeviceMark the element
     */
    public JDFDeviceMark getCreateDeviceMark()
    {
        return (JDFDeviceMark) getCreateElement_KElement(ElementName.DEVICEMARK, null, 0);
    }

    /**
     * (29) append element DeviceMark
     */
    public JDFDeviceMark appendDeviceMark() throws JDFException
    {
        return (JDFDeviceMark) appendElementN(ElementName.DEVICEMARK, 1, null);
    }

    /**
      * (31) create inter-resource link to refTarget
      * @param refTarget the element that is referenced
      */
    public void refDeviceMark(JDFDeviceMark refTarget)
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

    /**
     * (24) const get element ImageShift
     * @return JDFImageShift the element
     */
    public JDFImageShift getImageShift()
    {
        return (JDFImageShift) getElement(ElementName.IMAGESHIFT, null, 0);
    }

    /** (25) getCreateImageShift
     * 
     * @return JDFImageShift the element
     */
    public JDFImageShift getCreateImageShift()
    {
        return (JDFImageShift) getCreateElement_KElement(ElementName.IMAGESHIFT, null, 0);
    }

    /**
     * (29) append element ImageShift
     */
    public JDFImageShift appendImageShift() throws JDFException
    {
        return (JDFImageShift) appendElementN(ElementName.IMAGESHIFT, 1, null);
    }

}// end namespace JDF
