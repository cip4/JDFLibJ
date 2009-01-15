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
import org.cip4.jdflib.core.JDFElement;
import org.cip4.jdflib.core.JDFException;
import org.cip4.jdflib.core.VElement;
import org.cip4.jdflib.datatypes.JDFMatrix;
import org.cip4.jdflib.datatypes.JDFRectangle;
import org.cip4.jdflib.datatypes.JDFXYPair;
import org.cip4.jdflib.resource.JDFDeviceMark;
import org.cip4.jdflib.resource.JDFJobField;
import org.cip4.jdflib.resource.JDFMarkActivation;
import org.cip4.jdflib.resource.JDFRefAnchor;
import org.cip4.jdflib.resource.JDFScavengerArea;
import org.cip4.jdflib.resource.process.JDFCIELABMeasuringField;
import org.cip4.jdflib.resource.process.JDFColorControlStrip;
import org.cip4.jdflib.resource.process.JDFDensityMeasuringField;
import org.cip4.jdflib.resource.process.JDFDynamicField;
import org.cip4.jdflib.resource.process.JDFIdentificationField;
import org.cip4.jdflib.resource.process.JDFLayoutElement;
import org.cip4.jdflib.resource.process.JDFRegisterMark;
import org.cip4.jdflib.resource.process.postpress.JDFCutMark;

public abstract class JDFAutoMarkObject extends JDFElement
{

    private static final long serialVersionUID = 1L;

    private static AtrInfoTable[] atrInfoTable = new AtrInfoTable[20];
    static
    {
        atrInfoTable[0] = new AtrInfoTable(AttributeName.CONTENTREF, 0x33331111, AttributeInfo.EnumAttributeType.IDREF, null, null);
        atrInfoTable[1] = new AtrInfoTable(AttributeName.CLIPPATH, 0x33333111, AttributeInfo.EnumAttributeType.PDFPath, null, null);
        atrInfoTable[2] = new AtrInfoTable(AttributeName.CLIPBOXTEMPLATE, 0x33331111, AttributeInfo.EnumAttributeType.string, null, null);
        atrInfoTable[3] = new AtrInfoTable(AttributeName.TYPE, 0x44444443, AttributeInfo.EnumAttributeType.enumeration, EnumType.getEnum(0), null);
        atrInfoTable[4] = new AtrInfoTable(AttributeName.SOURCECLIPPATH, 0x33333333, AttributeInfo.EnumAttributeType.PDFPath, null, null);
        atrInfoTable[5] = new AtrInfoTable(AttributeName.COMPENSATIONCTMFORMAT, 0x33331111, AttributeInfo.EnumAttributeType.string, null, null);
        atrInfoTable[6] = new AtrInfoTable(AttributeName.HALFTONEPHASEORIGIN, 0x33333333, AttributeInfo.EnumAttributeType.XYPair, null, "0 0");
        atrInfoTable[7] = new AtrInfoTable(AttributeName.LAYERID, 0x33333331, AttributeInfo.EnumAttributeType.integer, null, null);
        atrInfoTable[8] = new AtrInfoTable(AttributeName.TRIMCTM, 0x33333331, AttributeInfo.EnumAttributeType.matrix, null, null);
        atrInfoTable[9] = new AtrInfoTable(AttributeName.TRIMSIZE, 0x33333311, AttributeInfo.EnumAttributeType.XYPair, null, null);
        atrInfoTable[10] = new AtrInfoTable(AttributeName.CLIPBOXFORMAT, 0x33331111, AttributeInfo.EnumAttributeType.string, null, null);
        atrInfoTable[11] = new AtrInfoTable(AttributeName.ORD, 0x33333333, AttributeInfo.EnumAttributeType.integer, null, null);
        atrInfoTable[12] = new AtrInfoTable(AttributeName.ANCHOR, 0x33331111, AttributeInfo.EnumAttributeType.enumeration, EnumAnchor.getEnum(0), null);
        atrInfoTable[13] = new AtrInfoTable(AttributeName.LOGICALSTACKORD, 0x33331111, AttributeInfo.EnumAttributeType.integer, null, null);
        atrInfoTable[14] = new AtrInfoTable(AttributeName.ORDID, 0x33333331, AttributeInfo.EnumAttributeType.integer, null, null);
        atrInfoTable[15] = new AtrInfoTable(AttributeName.CTM, 0x22222222, AttributeInfo.EnumAttributeType.matrix, null, null);
        atrInfoTable[16] = new AtrInfoTable(AttributeName.CLIPBOX, 0x33333333, AttributeInfo.EnumAttributeType.rectangle, null, null);
        atrInfoTable[17] = new AtrInfoTable(AttributeName.LAYOUTELEMENTPAGENUM, 0x44443331, AttributeInfo.EnumAttributeType.integer, null, "0");
        atrInfoTable[18] = new AtrInfoTable(AttributeName.TRIMCLIPPATH, 0x33331111, AttributeInfo.EnumAttributeType.PDFPath, null, null);
        atrInfoTable[19] = new AtrInfoTable(AttributeName.COMPENSATIONCTMTEMPLATE, 0x33331111, AttributeInfo.EnumAttributeType.string, null, null);
    }
    
    @Override
	protected AttributeInfo getTheAttributeInfo()
    {
        return super.getTheAttributeInfo().updateReplace(atrInfoTable);
    }


    private static ElemInfoTable[] elemInfoTable = new ElemInfoTable[13];
    static
    {
        elemInfoTable[0] = new ElemInfoTable(ElementName.CIELABMEASURINGFIELD, 0x33333333);
        elemInfoTable[1] = new ElemInfoTable(ElementName.COLORCONTROLSTRIP, 0x33333333);
        elemInfoTable[2] = new ElemInfoTable(ElementName.CUTMARK, 0x33333333);
        elemInfoTable[3] = new ElemInfoTable(ElementName.DENSITYMEASURINGFIELD, 0x33333333);
        elemInfoTable[4] = new ElemInfoTable(ElementName.DEVICEMARK, 0x66666661);
        elemInfoTable[5] = new ElemInfoTable(ElementName.DYNAMICFIELD, 0x33333333);
        elemInfoTable[6] = new ElemInfoTable(ElementName.IDENTIFICATIONFIELD, 0x33333333);
        elemInfoTable[7] = new ElemInfoTable(ElementName.JOBFIELD, 0x66666661);
        elemInfoTable[8] = new ElemInfoTable(ElementName.LAYOUTELEMENT, 0x77776666);
        elemInfoTable[9] = new ElemInfoTable(ElementName.MARKACTIVATION, 0x33331111);
        elemInfoTable[10] = new ElemInfoTable(ElementName.REFANCHOR, 0x66661111);
        elemInfoTable[11] = new ElemInfoTable(ElementName.REGISTERMARK, 0x33333333);
        elemInfoTable[12] = new ElemInfoTable(ElementName.SCAVENGERAREA, 0x33333331);
    }
    
    @Override
	protected ElementInfo getTheElementInfo()
    {
        return super.getTheElementInfo().updateReplace(elemInfoTable);
    }



    /**
     * Constructor for JDFAutoMarkObject
     * @param myOwnerDocument
     * @param qualifiedName
     */
    protected JDFAutoMarkObject(
        CoreDocumentImpl myOwnerDocument,
        String qualifiedName)
    {
        super(myOwnerDocument, qualifiedName);
    }

    /**
     * Constructor for JDFAutoMarkObject
     * @param myOwnerDocument
     * @param myNamespaceURI
     * @param qualifiedName
     */
    protected JDFAutoMarkObject(
        CoreDocumentImpl myOwnerDocument,
        String myNamespaceURI,
        String qualifiedName)
    {
        super(myOwnerDocument, myNamespaceURI, qualifiedName);
    }

    /**
     * Constructor for JDFAutoMarkObject
     * @param myOwnerDocument
     * @param myNamespaceURI
     * @param qualifiedName
     * @param myLocalName
     */
    protected JDFAutoMarkObject(
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
        return " JDFAutoMarkObject[  --> " + super.toString() + " ]";
    }


        /**
        * Enumeration strings for Type
        */

        public static class EnumType extends ValuedEnum
        {
            private static final long serialVersionUID = 1L;
            private static int m_startValue = 0;

            private EnumType(String name)
            {
                super(name, m_startValue++);
            }

            public static EnumType getEnum(String enumName)
            {
                return (EnumType) getEnum(EnumType.class, enumName);
            }

            public static EnumType getEnum(int enumValue)
            {
                return (EnumType) getEnum(EnumType.class, enumValue);
            }

            public static Map getEnumMap()
            {
                return getEnumMap(EnumType.class);
            }

            public static List getEnumList()
            {
                return getEnumList(EnumType.class);
            }

            public static Iterator iterator()
            {
                return iterator(EnumType.class);
            }

            public static final EnumType Content = new EnumType("Content");
            public static final EnumType Mark = new EnumType("Mark");
        }      



        /**
        * Enumeration strings for Anchor
        */

        public static class EnumAnchor extends ValuedEnum
        {
            private static final long serialVersionUID = 1L;
            private static int m_startValue = 0;

            private EnumAnchor(String name)
            {
                super(name, m_startValue++);
            }

            public static EnumAnchor getEnum(String enumName)
            {
                return (EnumAnchor) getEnum(EnumAnchor.class, enumName);
            }

            public static EnumAnchor getEnum(int enumValue)
            {
                return (EnumAnchor) getEnum(EnumAnchor.class, enumValue);
            }

            public static Map getEnumMap()
            {
                return getEnumMap(EnumAnchor.class);
            }

            public static List getEnumList()
            {
                return getEnumList(EnumAnchor.class);
            }

            public static Iterator iterator()
            {
                return iterator(EnumAnchor.class);
            }

            public static final EnumAnchor TopLeft = new EnumAnchor("TopLeft");
            public static final EnumAnchor TopCenter = new EnumAnchor("TopCenter");
            public static final EnumAnchor TopRight = new EnumAnchor("TopRight");
            public static final EnumAnchor CenterLeft = new EnumAnchor("CenterLeft");
            public static final EnumAnchor Center = new EnumAnchor("Center");
            public static final EnumAnchor CenterRight = new EnumAnchor("CenterRight");
            public static final EnumAnchor BottomLeft = new EnumAnchor("BottomLeft");
            public static final EnumAnchor BottomCenter = new EnumAnchor("BottomCenter");
            public static final EnumAnchor BottomRight = new EnumAnchor("BottomRight");
        }      



/* ************************************************************************
 * Attribute getter / setter
 * ************************************************************************
 */
        
        /* ---------------------------------------------------------------------
        Methods for Attribute ContentRef
        --------------------------------------------------------------------- */
        /**
          * (36) set attribute ContentRef
          * @param value: the value to set the attribute to
          */
        public void setContentRef(String value)
        {
            setAttribute(AttributeName.CONTENTREF, value, null);
        }

        /**
          * (23) get String attribute ContentRef
          * @return the value of the attribute
          */
        public String getContentRef()
        {
            return getAttribute(AttributeName.CONTENTREF, null, JDFConstants.EMPTYSTRING);
        }

        
        /* ---------------------------------------------------------------------
        Methods for Attribute ClipPath
        --------------------------------------------------------------------- */
        /**
          * (36) set attribute ClipPath
          * @param value: the value to set the attribute to
          */
        public void setClipPath(String value)
        {
            setAttribute(AttributeName.CLIPPATH, value, null);
        }

        /**
          * (23) get String attribute ClipPath
          * @return the value of the attribute
          */
        public String getClipPath()
        {
            return getAttribute(AttributeName.CLIPPATH, null, JDFConstants.EMPTYSTRING);
        }

        
        /* ---------------------------------------------------------------------
        Methods for Attribute ClipBoxTemplate
        --------------------------------------------------------------------- */
        /**
          * (36) set attribute ClipBoxTemplate
          * @param value: the value to set the attribute to
          */
        public void setClipBoxTemplate(String value)
        {
            setAttribute(AttributeName.CLIPBOXTEMPLATE, value, null);
        }

        /**
          * (23) get String attribute ClipBoxTemplate
          * @return the value of the attribute
          */
        public String getClipBoxTemplate()
        {
            return getAttribute(AttributeName.CLIPBOXTEMPLATE, null, JDFConstants.EMPTYSTRING);
        }

        
        /* ---------------------------------------------------------------------
        Methods for Attribute Type
        --------------------------------------------------------------------- */
        /**
          * (5) set attribute Type
          * @param enumVar: the enumVar to set the attribute to
          */
        public void setType(EnumType enumVar)
        {
            setAttribute(AttributeName.TYPE, enumVar==null ? null : enumVar.getName(), null);
        }

        /**
          * (9) get attribute Type
          * @return the value of the attribute
          */
        public EnumType getType()
        {
            return EnumType.getEnum(getAttribute(AttributeName.TYPE, null, null));
        }

        
        /* ---------------------------------------------------------------------
        Methods for Attribute SourceClipPath
        --------------------------------------------------------------------- */
        /**
          * (36) set attribute SourceClipPath
          * @param value: the value to set the attribute to
          */
        public void setSourceClipPath(String value)
        {
            setAttribute(AttributeName.SOURCECLIPPATH, value, null);
        }

        /**
          * (23) get String attribute SourceClipPath
          * @return the value of the attribute
          */
        public String getSourceClipPath()
        {
            return getAttribute(AttributeName.SOURCECLIPPATH, null, JDFConstants.EMPTYSTRING);
        }

        
        /* ---------------------------------------------------------------------
        Methods for Attribute CompensationCTMFormat
        --------------------------------------------------------------------- */
        /**
          * (36) set attribute CompensationCTMFormat
          * @param value: the value to set the attribute to
          */
        public void setCompensationCTMFormat(String value)
        {
            setAttribute(AttributeName.COMPENSATIONCTMFORMAT, value, null);
        }

        /**
          * (23) get String attribute CompensationCTMFormat
          * @return the value of the attribute
          */
        public String getCompensationCTMFormat()
        {
            return getAttribute(AttributeName.COMPENSATIONCTMFORMAT, null, JDFConstants.EMPTYSTRING);
        }

        
        /* ---------------------------------------------------------------------
        Methods for Attribute HalfTonePhaseOrigin
        --------------------------------------------------------------------- */
        /**
          * (36) set attribute HalfTonePhaseOrigin
          * @param value: the value to set the attribute to
          */
        public void setHalfTonePhaseOrigin(JDFXYPair value)
        {
            setAttribute(AttributeName.HALFTONEPHASEORIGIN, value, null);
        }

        /**
          * (20) get JDFXYPair attribute HalfTonePhaseOrigin
          * @return JDFXYPair the value of the attribute, null if a the
          *         attribute value is not a valid to create a JDFXYPair
          */
        public JDFXYPair getHalfTonePhaseOrigin()
        {
            String strAttrName = "";
            JDFXYPair nPlaceHolder = null;
            strAttrName = getAttribute(AttributeName.HALFTONEPHASEORIGIN, null, JDFConstants.EMPTYSTRING);
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
        Methods for Attribute LayerID
        --------------------------------------------------------------------- */
        /**
          * (36) set attribute LayerID
          * @param value: the value to set the attribute to
          */
        public void setLayerID(int value)
        {
            setAttribute(AttributeName.LAYERID, value, null);
        }

        /**
          * (15) get int attribute LayerID
          * @return int the value of the attribute
          */
        public int getLayerID()
        {
            return getIntAttribute(AttributeName.LAYERID, null, 0);
        }

        
        /* ---------------------------------------------------------------------
        Methods for Attribute TrimCTM
        --------------------------------------------------------------------- */
        /**
          * (36) set attribute TrimCTM
          * @param value: the value to set the attribute to
          */
        public void setTrimCTM(JDFMatrix value)
        {
            setAttribute(AttributeName.TRIMCTM, value, null);
        }

        /**
          * (20) get JDFMatrix attribute TrimCTM
          * @return JDFMatrix the value of the attribute, null if a the
          *         attribute value is not a valid to create a JDFMatrix
          */
        public JDFMatrix getTrimCTM()
        {
            String strAttrName = "";
            JDFMatrix nPlaceHolder = null;
            strAttrName = getAttribute(AttributeName.TRIMCTM, null, JDFConstants.EMPTYSTRING);
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
        Methods for Attribute ClipBoxFormat
        --------------------------------------------------------------------- */
        /**
          * (36) set attribute ClipBoxFormat
          * @param value: the value to set the attribute to
          */
        public void setClipBoxFormat(String value)
        {
            setAttribute(AttributeName.CLIPBOXFORMAT, value, null);
        }

        /**
          * (23) get String attribute ClipBoxFormat
          * @return the value of the attribute
          */
        public String getClipBoxFormat()
        {
            return getAttribute(AttributeName.CLIPBOXFORMAT, null, JDFConstants.EMPTYSTRING);
        }

        
        /* ---------------------------------------------------------------------
        Methods for Attribute Ord
        --------------------------------------------------------------------- */
        /**
          * (36) set attribute Ord
          * @param value: the value to set the attribute to
          */
        public void setOrd(int value)
        {
            setAttribute(AttributeName.ORD, value, null);
        }

        /**
          * (15) get int attribute Ord
          * @return int the value of the attribute
          */
        public int getOrd()
        {
            return getIntAttribute(AttributeName.ORD, null, 0);
        }

        
        /* ---------------------------------------------------------------------
        Methods for Attribute Anchor
        --------------------------------------------------------------------- */
        /**
          * (5) set attribute Anchor
          * @param enumVar: the enumVar to set the attribute to
          */
        public void setAnchor(EnumAnchor enumVar)
        {
            setAttribute(AttributeName.ANCHOR, enumVar==null ? null : enumVar.getName(), null);
        }

        /**
          * (9) get attribute Anchor
          * @return the value of the attribute
          */
        public EnumAnchor getAnchor()
        {
            return EnumAnchor.getEnum(getAttribute(AttributeName.ANCHOR, null, null));
        }

        
        /* ---------------------------------------------------------------------
        Methods for Attribute LogicalStackOrd
        --------------------------------------------------------------------- */
        /**
          * (36) set attribute LogicalStackOrd
          * @param value: the value to set the attribute to
          */
        public void setLogicalStackOrd(int value)
        {
            setAttribute(AttributeName.LOGICALSTACKORD, value, null);
        }

        /**
          * (15) get int attribute LogicalStackOrd
          * @return int the value of the attribute
          */
        public int getLogicalStackOrd()
        {
            return getIntAttribute(AttributeName.LOGICALSTACKORD, null, 0);
        }

        
        /* ---------------------------------------------------------------------
        Methods for Attribute OrdID
        --------------------------------------------------------------------- */
        /**
          * (36) set attribute OrdID
          * @param value: the value to set the attribute to
          */
        public void setOrdID(int value)
        {
            setAttribute(AttributeName.ORDID, value, null);
        }

        /**
          * (15) get int attribute OrdID
          * @return int the value of the attribute
          */
        public int getOrdID()
        {
            return getIntAttribute(AttributeName.ORDID, null, 0);
        }

        
        /* ---------------------------------------------------------------------
        Methods for Attribute CTM
        --------------------------------------------------------------------- */
        /**
          * (36) set attribute CTM
          * @param value: the value to set the attribute to
          */
        public void setCTM(JDFMatrix value)
        {
            setAttribute(AttributeName.CTM, value, null);
        }

        /**
          * (20) get JDFMatrix attribute CTM
          * @return JDFMatrix the value of the attribute, null if a the
          *         attribute value is not a valid to create a JDFMatrix
          */
        public JDFMatrix getCTM()
        {
            String strAttrName = "";
            JDFMatrix nPlaceHolder = null;
            strAttrName = getAttribute(AttributeName.CTM, null, JDFConstants.EMPTYSTRING);
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
        Methods for Attribute LayoutElementPageNum
        --------------------------------------------------------------------- */
        /**
          * (36) set attribute LayoutElementPageNum
          * @param value: the value to set the attribute to
          */
        public void setLayoutElementPageNum(int value)
        {
            setAttribute(AttributeName.LAYOUTELEMENTPAGENUM, value, null);
        }

        /**
          * (15) get int attribute LayoutElementPageNum
          * @return int the value of the attribute
          */
        public int getLayoutElementPageNum()
        {
            return getIntAttribute(AttributeName.LAYOUTELEMENTPAGENUM, null, 0);
        }

        
        /* ---------------------------------------------------------------------
        Methods for Attribute TrimClipPath
        --------------------------------------------------------------------- */
        /**
          * (36) set attribute TrimClipPath
          * @param value: the value to set the attribute to
          */
        public void setTrimClipPath(String value)
        {
            setAttribute(AttributeName.TRIMCLIPPATH, value, null);
        }

        /**
          * (23) get String attribute TrimClipPath
          * @return the value of the attribute
          */
        public String getTrimClipPath()
        {
            return getAttribute(AttributeName.TRIMCLIPPATH, null, JDFConstants.EMPTYSTRING);
        }

        
        /* ---------------------------------------------------------------------
        Methods for Attribute CompensationCTMTemplate
        --------------------------------------------------------------------- */
        /**
          * (36) set attribute CompensationCTMTemplate
          * @param value: the value to set the attribute to
          */
        public void setCompensationCTMTemplate(String value)
        {
            setAttribute(AttributeName.COMPENSATIONCTMTEMPLATE, value, null);
        }

        /**
          * (23) get String attribute CompensationCTMTemplate
          * @return the value of the attribute
          */
        public String getCompensationCTMTemplate()
        {
            return getAttribute(AttributeName.COMPENSATIONCTMTEMPLATE, null, JDFConstants.EMPTYSTRING);
        }

/* ***********************************************************************
 * Element getter / setter
 * ***********************************************************************
 */

    /** (26) getCreateCIELABMeasuringField
     * 
     * @param iSkip number of elements to skip
     * @return JDFCIELABMeasuringField the element
     */
    public JDFCIELABMeasuringField getCreateCIELABMeasuringField(int iSkip)
    {
        return (JDFCIELABMeasuringField)getCreateElement_KElement(ElementName.CIELABMEASURINGFIELD, null, iSkip);
    }

    /**
     * (27) const get element CIELABMeasuringField
     * @param iSkip number of elements to skip
     * @return JDFCIELABMeasuringField the element
     * default is getCIELABMeasuringField(0)     */
    public JDFCIELABMeasuringField getCIELABMeasuringField(int iSkip)
    {
        return (JDFCIELABMeasuringField) getElement(ElementName.CIELABMEASURINGFIELD, null, iSkip);
    }

    /**
     * Get all CIELABMeasuringField from the current element
     * 
     * @return Collection<JDFCIELABMeasuringField>, null if none are available
     */
    public Collection<JDFCIELABMeasuringField> getAllCIELABMeasuringField()
    {
        final VElement vc = getChildElementVector(ElementName.CIELABMEASURINGFIELD, null);
        if (vc == null || vc.size() == 0)
        {
            return null;
        }

        final Vector<JDFCIELABMeasuringField> v = new Vector<JDFCIELABMeasuringField>();
        for (int i = 0; i < vc.size(); i++)
        {
            v.add((JDFCIELABMeasuringField) vc.get(i));
        }

        return v;
    }

    /**
     * (30) append element CIELABMeasuringField
     */
    public JDFCIELABMeasuringField appendCIELABMeasuringField() throws JDFException
    {
        return (JDFCIELABMeasuringField) appendElement(ElementName.CIELABMEASURINGFIELD, null);
    }

    /**
      * (31) create inter-resource link to refTarget
      * @param refTarget the element that is referenced
      */
    public void refCIELABMeasuringField(JDFCIELABMeasuringField refTarget)
    {
        refElement(refTarget);
    }

    /** (26) getCreateColorControlStrip
     * 
     * @param iSkip number of elements to skip
     * @return JDFColorControlStrip the element
     */
    public JDFColorControlStrip getCreateColorControlStrip(int iSkip)
    {
        return (JDFColorControlStrip)getCreateElement_KElement(ElementName.COLORCONTROLSTRIP, null, iSkip);
    }

    /**
     * (27) const get element ColorControlStrip
     * @param iSkip number of elements to skip
     * @return JDFColorControlStrip the element
     * default is getColorControlStrip(0)     */
    public JDFColorControlStrip getColorControlStrip(int iSkip)
    {
        return (JDFColorControlStrip) getElement(ElementName.COLORCONTROLSTRIP, null, iSkip);
    }

    /**
     * Get all ColorControlStrip from the current element
     * 
     * @return Collection<JDFColorControlStrip>, null if none are available
     */
    public Collection<JDFColorControlStrip> getAllColorControlStrip()
    {
        final VElement vc = getChildElementVector(ElementName.COLORCONTROLSTRIP, null);
        if (vc == null || vc.size() == 0)
        {
            return null;
        }

        final Vector<JDFColorControlStrip> v = new Vector<JDFColorControlStrip>();
        for (int i = 0; i < vc.size(); i++)
        {
            v.add((JDFColorControlStrip) vc.get(i));
        }

        return v;
    }

    /**
     * (30) append element ColorControlStrip
     */
    public JDFColorControlStrip appendColorControlStrip() throws JDFException
    {
        return (JDFColorControlStrip) appendElement(ElementName.COLORCONTROLSTRIP, null);
    }

    /**
      * (31) create inter-resource link to refTarget
      * @param refTarget the element that is referenced
      */
    public void refColorControlStrip(JDFColorControlStrip refTarget)
    {
        refElement(refTarget);
    }

    /** (26) getCreateCutMark
     * 
     * @param iSkip number of elements to skip
     * @return JDFCutMark the element
     */
    public JDFCutMark getCreateCutMark(int iSkip)
    {
        return (JDFCutMark)getCreateElement_KElement(ElementName.CUTMARK, null, iSkip);
    }

    /**
     * (27) const get element CutMark
     * @param iSkip number of elements to skip
     * @return JDFCutMark the element
     * default is getCutMark(0)     */
    public JDFCutMark getCutMark(int iSkip)
    {
        return (JDFCutMark) getElement(ElementName.CUTMARK, null, iSkip);
    }

    /**
     * Get all CutMark from the current element
     * 
     * @return Collection<JDFCutMark>, null if none are available
     */
    public Collection<JDFCutMark> getAllCutMark()
    {
        final VElement vc = getChildElementVector(ElementName.CUTMARK, null);
        if (vc == null || vc.size() == 0)
        {
            return null;
        }

        final Vector<JDFCutMark> v = new Vector<JDFCutMark>();
        for (int i = 0; i < vc.size(); i++)
        {
            v.add((JDFCutMark) vc.get(i));
        }

        return v;
    }

    /**
     * (30) append element CutMark
     */
    public JDFCutMark appendCutMark() throws JDFException
    {
        return (JDFCutMark) appendElement(ElementName.CUTMARK, null);
    }

    /**
      * (31) create inter-resource link to refTarget
      * @param refTarget the element that is referenced
      */
    public void refCutMark(JDFCutMark refTarget)
    {
        refElement(refTarget);
    }

    /** (26) getCreateDensityMeasuringField
     * 
     * @param iSkip number of elements to skip
     * @return JDFDensityMeasuringField the element
     */
    public JDFDensityMeasuringField getCreateDensityMeasuringField(int iSkip)
    {
        return (JDFDensityMeasuringField)getCreateElement_KElement(ElementName.DENSITYMEASURINGFIELD, null, iSkip);
    }

    /**
     * (27) const get element DensityMeasuringField
     * @param iSkip number of elements to skip
     * @return JDFDensityMeasuringField the element
     * default is getDensityMeasuringField(0)     */
    public JDFDensityMeasuringField getDensityMeasuringField(int iSkip)
    {
        return (JDFDensityMeasuringField) getElement(ElementName.DENSITYMEASURINGFIELD, null, iSkip);
    }

    /**
     * Get all DensityMeasuringField from the current element
     * 
     * @return Collection<JDFDensityMeasuringField>, null if none are available
     */
    public Collection<JDFDensityMeasuringField> getAllDensityMeasuringField()
    {
        final VElement vc = getChildElementVector(ElementName.DENSITYMEASURINGFIELD, null);
        if (vc == null || vc.size() == 0)
        {
            return null;
        }

        final Vector<JDFDensityMeasuringField> v = new Vector<JDFDensityMeasuringField>();
        for (int i = 0; i < vc.size(); i++)
        {
            v.add((JDFDensityMeasuringField) vc.get(i));
        }

        return v;
    }

    /**
     * (30) append element DensityMeasuringField
     */
    public JDFDensityMeasuringField appendDensityMeasuringField() throws JDFException
    {
        return (JDFDensityMeasuringField) appendElement(ElementName.DENSITYMEASURINGFIELD, null);
    }

    /**
      * (31) create inter-resource link to refTarget
      * @param refTarget the element that is referenced
      */
    public void refDensityMeasuringField(JDFDensityMeasuringField refTarget)
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

    /** (26) getCreateDynamicField
     * 
     * @param iSkip number of elements to skip
     * @return JDFDynamicField the element
     */
    public JDFDynamicField getCreateDynamicField(int iSkip)
    {
        return (JDFDynamicField)getCreateElement_KElement(ElementName.DYNAMICFIELD, null, iSkip);
    }

    /**
     * (27) const get element DynamicField
     * @param iSkip number of elements to skip
     * @return JDFDynamicField the element
     * default is getDynamicField(0)     */
    public JDFDynamicField getDynamicField(int iSkip)
    {
        return (JDFDynamicField) getElement(ElementName.DYNAMICFIELD, null, iSkip);
    }

    /**
     * Get all DynamicField from the current element
     * 
     * @return Collection<JDFDynamicField>, null if none are available
     */
    public Collection<JDFDynamicField> getAllDynamicField()
    {
        final VElement vc = getChildElementVector(ElementName.DYNAMICFIELD, null);
        if (vc == null || vc.size() == 0)
        {
            return null;
        }

        final Vector<JDFDynamicField> v = new Vector<JDFDynamicField>();
        for (int i = 0; i < vc.size(); i++)
        {
            v.add((JDFDynamicField) vc.get(i));
        }

        return v;
    }

    /**
     * (30) append element DynamicField
     */
    public JDFDynamicField appendDynamicField() throws JDFException
    {
        return (JDFDynamicField) appendElement(ElementName.DYNAMICFIELD, null);
    }

    /** (26) getCreateIdentificationField
     * 
     * @param iSkip number of elements to skip
     * @return JDFIdentificationField the element
     */
    public JDFIdentificationField getCreateIdentificationField(int iSkip)
    {
        return (JDFIdentificationField)getCreateElement_KElement(ElementName.IDENTIFICATIONFIELD, null, iSkip);
    }

    /**
     * (27) const get element IdentificationField
     * @param iSkip number of elements to skip
     * @return JDFIdentificationField the element
     * default is getIdentificationField(0)     */
    public JDFIdentificationField getIdentificationField(int iSkip)
    {
        return (JDFIdentificationField) getElement(ElementName.IDENTIFICATIONFIELD, null, iSkip);
    }

    /**
     * Get all IdentificationField from the current element
     * 
     * @return Collection<JDFIdentificationField>, null if none are available
     */
    public Collection<JDFIdentificationField> getAllIdentificationField()
    {
        final VElement vc = getChildElementVector(ElementName.IDENTIFICATIONFIELD, null);
        if (vc == null || vc.size() == 0)
        {
            return null;
        }

        final Vector<JDFIdentificationField> v = new Vector<JDFIdentificationField>();
        for (int i = 0; i < vc.size(); i++)
        {
            v.add((JDFIdentificationField) vc.get(i));
        }

        return v;
    }

    /**
     * (30) append element IdentificationField
     */
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

    /**
     * (24) const get element JobField
     * @return JDFJobField the element
     */
    public JDFJobField getJobField()
    {
        return (JDFJobField) getElement(ElementName.JOBFIELD, null, 0);
    }

    /** (25) getCreateJobField
     * 
     * @return JDFJobField the element
     */
    public JDFJobField getCreateJobField()
    {
        return (JDFJobField) getCreateElement_KElement(ElementName.JOBFIELD, null, 0);
    }

    /**
     * (29) append element JobField
     */
    public JDFJobField appendJobField() throws JDFException
    {
        return (JDFJobField) appendElementN(ElementName.JOBFIELD, 1, null);
    }

    /**
      * (31) create inter-resource link to refTarget
      * @param refTarget the element that is referenced
      */
    public void refJobField(JDFJobField refTarget)
    {
        refElement(refTarget);
    }

    /**
     * (24) const get element LayoutElement
     * @return JDFLayoutElement the element
     */
    public JDFLayoutElement getLayoutElement()
    {
        return (JDFLayoutElement) getElement(ElementName.LAYOUTELEMENT, null, 0);
    }

    /** (25) getCreateLayoutElement
     * 
     * @return JDFLayoutElement the element
     */
    public JDFLayoutElement getCreateLayoutElement()
    {
        return (JDFLayoutElement) getCreateElement_KElement(ElementName.LAYOUTELEMENT, null, 0);
    }

    /**
     * (29) append element LayoutElement
     */
    public JDFLayoutElement appendLayoutElement() throws JDFException
    {
        return (JDFLayoutElement) appendElementN(ElementName.LAYOUTELEMENT, 1, null);
    }

    /**
      * (31) create inter-resource link to refTarget
      * @param refTarget the element that is referenced
      */
    public void refLayoutElement(JDFLayoutElement refTarget)
    {
        refElement(refTarget);
    }

    /** (26) getCreateMarkActivation
     * 
     * @param iSkip number of elements to skip
     * @return JDFMarkActivation the element
     */
    public JDFMarkActivation getCreateMarkActivation(int iSkip)
    {
        return (JDFMarkActivation)getCreateElement_KElement(ElementName.MARKACTIVATION, null, iSkip);
    }

    /**
     * (27) const get element MarkActivation
     * @param iSkip number of elements to skip
     * @return JDFMarkActivation the element
     * default is getMarkActivation(0)     */
    public JDFMarkActivation getMarkActivation(int iSkip)
    {
        return (JDFMarkActivation) getElement(ElementName.MARKACTIVATION, null, iSkip);
    }

    /**
     * Get all MarkActivation from the current element
     * 
     * @return Collection<JDFMarkActivation>, null if none are available
     */
    public Collection<JDFMarkActivation> getAllMarkActivation()
    {
        final VElement vc = getChildElementVector(ElementName.MARKACTIVATION, null);
        if (vc == null || vc.size() == 0)
        {
            return null;
        }

        final Vector<JDFMarkActivation> v = new Vector<JDFMarkActivation>();
        for (int i = 0; i < vc.size(); i++)
        {
            v.add((JDFMarkActivation) vc.get(i));
        }

        return v;
    }

    /**
     * (30) append element MarkActivation
     */
    public JDFMarkActivation appendMarkActivation() throws JDFException
    {
        return (JDFMarkActivation) appendElement(ElementName.MARKACTIVATION, null);
    }

    /**
     * (24) const get element RefAnchor
     * @return JDFRefAnchor the element
     */
    public JDFRefAnchor getRefAnchor()
    {
        return (JDFRefAnchor) getElement(ElementName.REFANCHOR, null, 0);
    }

    /** (25) getCreateRefAnchor
     * 
     * @return JDFRefAnchor the element
     */
    public JDFRefAnchor getCreateRefAnchor()
    {
        return (JDFRefAnchor) getCreateElement_KElement(ElementName.REFANCHOR, null, 0);
    }

    /**
     * (29) append element RefAnchor
     */
    public JDFRefAnchor appendRefAnchor() throws JDFException
    {
        return (JDFRefAnchor) appendElementN(ElementName.REFANCHOR, 1, null);
    }

    /** (26) getCreateRegisterMark
     * 
     * @param iSkip number of elements to skip
     * @return JDFRegisterMark the element
     */
    public JDFRegisterMark getCreateRegisterMark(int iSkip)
    {
        return (JDFRegisterMark)getCreateElement_KElement(ElementName.REGISTERMARK, null, iSkip);
    }

    /**
     * (27) const get element RegisterMark
     * @param iSkip number of elements to skip
     * @return JDFRegisterMark the element
     * default is getRegisterMark(0)     */
    public JDFRegisterMark getRegisterMark(int iSkip)
    {
        return (JDFRegisterMark) getElement(ElementName.REGISTERMARK, null, iSkip);
    }

    /**
     * Get all RegisterMark from the current element
     * 
     * @return Collection<JDFRegisterMark>, null if none are available
     */
    public Collection<JDFRegisterMark> getAllRegisterMark()
    {
        final VElement vc = getChildElementVector(ElementName.REGISTERMARK, null);
        if (vc == null || vc.size() == 0)
        {
            return null;
        }

        final Vector<JDFRegisterMark> v = new Vector<JDFRegisterMark>();
        for (int i = 0; i < vc.size(); i++)
        {
            v.add((JDFRegisterMark) vc.get(i));
        }

        return v;
    }

    /**
     * (30) append element RegisterMark
     */
    public JDFRegisterMark appendRegisterMark() throws JDFException
    {
        return (JDFRegisterMark) appendElement(ElementName.REGISTERMARK, null);
    }

    /**
      * (31) create inter-resource link to refTarget
      * @param refTarget the element that is referenced
      */
    public void refRegisterMark(JDFRegisterMark refTarget)
    {
        refElement(refTarget);
    }

    /** (26) getCreateScavengerArea
     * 
     * @param iSkip number of elements to skip
     * @return JDFScavengerArea the element
     */
    public JDFScavengerArea getCreateScavengerArea(int iSkip)
    {
        return (JDFScavengerArea)getCreateElement_KElement(ElementName.SCAVENGERAREA, null, iSkip);
    }

    /**
     * (27) const get element ScavengerArea
     * @param iSkip number of elements to skip
     * @return JDFScavengerArea the element
     * default is getScavengerArea(0)     */
    public JDFScavengerArea getScavengerArea(int iSkip)
    {
        return (JDFScavengerArea) getElement(ElementName.SCAVENGERAREA, null, iSkip);
    }

    /**
     * Get all ScavengerArea from the current element
     * 
     * @return Collection<JDFScavengerArea>, null if none are available
     */
    public Collection<JDFScavengerArea> getAllScavengerArea()
    {
        final VElement vc = getChildElementVector(ElementName.SCAVENGERAREA, null);
        if (vc == null || vc.size() == 0)
        {
            return null;
        }

        final Vector<JDFScavengerArea> v = new Vector<JDFScavengerArea>();
        for (int i = 0; i < vc.size(); i++)
        {
            v.add((JDFScavengerArea) vc.get(i));
        }

        return v;
    }

    /**
     * (30) append element ScavengerArea
     */
    public JDFScavengerArea appendScavengerArea() throws JDFException
    {
        return (JDFScavengerArea) appendElement(ElementName.SCAVENGERAREA, null);
    }

    /**
      * (31) create inter-resource link to refTarget
      * @param refTarget the element that is referenced
      */
    public void refScavengerArea(JDFScavengerArea refTarget)
    {
        refElement(refTarget);
    }

}// end namespace JDF
