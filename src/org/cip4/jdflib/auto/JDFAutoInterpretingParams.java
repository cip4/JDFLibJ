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
import org.cip4.jdflib.datatypes.JDFXYPair;
import org.cip4.jdflib.resource.JDFFitPolicy;
import org.cip4.jdflib.resource.JDFPDFInterpretingParams;
import org.cip4.jdflib.resource.JDFResource;
import org.cip4.jdflib.resource.process.JDFMedia;
import org.cip4.jdflib.resource.process.JDFObjectResolution;
    /*
    *****************************************************************************
    class JDFAutoInterpretingParams : public JDFResource

    *****************************************************************************
    */

public abstract class JDFAutoInterpretingParams extends JDFResource
{

    private static final long serialVersionUID = 1L;

    private static AtrInfoTable[] atrInfoTable = new AtrInfoTable[9];
    static
    {
        atrInfoTable[0] = new AtrInfoTable(AttributeName.CENTER, 0x33333333, AttributeInfo.EnumAttributeType.boolean_, null, "false");
        atrInfoTable[1] = new AtrInfoTable(AttributeName.MIRRORAROUND, 0x33333333, AttributeInfo.EnumAttributeType.enumeration, EnumMirrorAround.getEnum(0), "None");
        atrInfoTable[2] = new AtrInfoTable(AttributeName.POLARITY, 0x33333333, AttributeInfo.EnumAttributeType.enumeration, EnumPolarity.getEnum(0), "Positive");
        atrInfoTable[3] = new AtrInfoTable(AttributeName.PRINTQUALITY, 0x33333331, AttributeInfo.EnumAttributeType.enumeration, EnumPrintQuality.getEnum(0), "Normal");
        atrInfoTable[4] = new AtrInfoTable(AttributeName.FITTOPAGE, 0x44444443, AttributeInfo.EnumAttributeType.boolean_, null, null);
        atrInfoTable[5] = new AtrInfoTable(AttributeName.POSTER, 0x33333333, AttributeInfo.EnumAttributeType.XYPair, null, null);
        atrInfoTable[6] = new AtrInfoTable(AttributeName.POSTEROVERLAP, 0x33333333, AttributeInfo.EnumAttributeType.XYPair, null, null);
        atrInfoTable[7] = new AtrInfoTable(AttributeName.SCALING, 0x33333333, AttributeInfo.EnumAttributeType.XYPair, null, null);
        atrInfoTable[8] = new AtrInfoTable(AttributeName.SCALINGORIGIN, 0x33333333, AttributeInfo.EnumAttributeType.XYPair, null, null);
    }
    
    protected AttributeInfo getTheAttributeInfo()
    {
        return super.getTheAttributeInfo().updateReplace(atrInfoTable);
    }


    private static ElemInfoTable[] elemInfoTable = new ElemInfoTable[4];
    static
    {
        elemInfoTable[0] = new ElemInfoTable(ElementName.OBJECTRESOLUTION, 0x33333333);
        elemInfoTable[1] = new ElemInfoTable(ElementName.FITPOLICY, 0x66666661);
        elemInfoTable[2] = new ElemInfoTable(ElementName.MEDIA, 0x33333331);
        elemInfoTable[3] = new ElemInfoTable(ElementName.PDFINTERPRETINGPARAMS, 0x66666661);
    }
    
    protected ElementInfo getTheElementInfo()
    {
        return super.getTheElementInfo().updateReplace(elemInfoTable);
    }



    /**
     * Constructor for JDFAutoInterpretingParams
     * @param myOwnerDocument
     * @param qualifiedName
     */
    protected JDFAutoInterpretingParams(
        CoreDocumentImpl myOwnerDocument,
        String qualifiedName)
    {
        super(myOwnerDocument, qualifiedName);
    }

    /**
     * Constructor for JDFAutoInterpretingParams
     * @param myOwnerDocument
     * @param myNamespaceURI
     * @param qualifiedName
     */
    protected JDFAutoInterpretingParams(
        CoreDocumentImpl myOwnerDocument,
        String myNamespaceURI,
        String qualifiedName)
    {
        super(myOwnerDocument, myNamespaceURI, qualifiedName);
    }

    /**
     * Constructor for JDFAutoInterpretingParams
     * @param myOwnerDocument
     * @param myNamespaceURI
     * @param qualifiedName
     * @param myLocalName
     */
    protected JDFAutoInterpretingParams(
        CoreDocumentImpl myOwnerDocument,
        String myNamespaceURI,
        String qualifiedName,
        String myLocalName)
    {
        super(myOwnerDocument, myNamespaceURI, qualifiedName, myLocalName);
    }


    public String toString()
    {
        return " JDFAutoInterpretingParams[  --> " + super.toString() + " ]";
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
        * Enumeration strings for MirrorAround
        */

        public static class EnumMirrorAround extends ValuedEnum
        {
            private static final long serialVersionUID = 1L;
            private static int m_startValue = 0;

            private EnumMirrorAround(String name)
            {
                super(name, m_startValue++);
            }

            public static EnumMirrorAround getEnum(String enumName)
            {
                return (EnumMirrorAround) getEnum(EnumMirrorAround.class, enumName);
            }

            public static EnumMirrorAround getEnum(int enumValue)
            {
                return (EnumMirrorAround) getEnum(EnumMirrorAround.class, enumValue);
            }

            public static Map getEnumMap()
            {
                return getEnumMap(EnumMirrorAround.class);
            }

            public static List getEnumList()
            {
                return getEnumList(EnumMirrorAround.class);
            }

            public static Iterator iterator()
            {
                return iterator(EnumMirrorAround.class);
            }

            public static final EnumMirrorAround None = new EnumMirrorAround("None");
            public static final EnumMirrorAround FeedDirection = new EnumMirrorAround("FeedDirection");
            public static final EnumMirrorAround MediaWidth = new EnumMirrorAround("MediaWidth");
            public static final EnumMirrorAround Both = new EnumMirrorAround("Both");
        }      



        /**
        * Enumeration strings for Polarity
        */

        public static class EnumPolarity extends ValuedEnum
        {
            private static final long serialVersionUID = 1L;
            private static int m_startValue = 0;

            private EnumPolarity(String name)
            {
                super(name, m_startValue++);
            }

            public static EnumPolarity getEnum(String enumName)
            {
                return (EnumPolarity) getEnum(EnumPolarity.class, enumName);
            }

            public static EnumPolarity getEnum(int enumValue)
            {
                return (EnumPolarity) getEnum(EnumPolarity.class, enumValue);
            }

            public static Map getEnumMap()
            {
                return getEnumMap(EnumPolarity.class);
            }

            public static List getEnumList()
            {
                return getEnumList(EnumPolarity.class);
            }

            public static Iterator iterator()
            {
                return iterator(EnumPolarity.class);
            }

            public static final EnumPolarity Positive = new EnumPolarity("Positive");
            public static final EnumPolarity Negative = new EnumPolarity("Negative");
        }      



        /**
        * Enumeration strings for PrintQuality
        */

        public static class EnumPrintQuality extends ValuedEnum
        {
            private static final long serialVersionUID = 1L;
            private static int m_startValue = 0;

            private EnumPrintQuality(String name)
            {
                super(name, m_startValue++);
            }

            public static EnumPrintQuality getEnum(String enumName)
            {
                return (EnumPrintQuality) getEnum(EnumPrintQuality.class, enumName);
            }

            public static EnumPrintQuality getEnum(int enumValue)
            {
                return (EnumPrintQuality) getEnum(EnumPrintQuality.class, enumValue);
            }

            public static Map getEnumMap()
            {
                return getEnumMap(EnumPrintQuality.class);
            }

            public static List getEnumList()
            {
                return getEnumList(EnumPrintQuality.class);
            }

            public static Iterator iterator()
            {
                return iterator(EnumPrintQuality.class);
            }

            public static final EnumPrintQuality High = new EnumPrintQuality("High");
            public static final EnumPrintQuality Normal = new EnumPrintQuality("Normal");
            public static final EnumPrintQuality Draft = new EnumPrintQuality("Draft");
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
        public void setCenter(boolean value)
        {
            setAttribute(AttributeName.CENTER, value, null);
        }



        /**
          * (18) get boolean attribute Center
          * @return boolean the value of the attribute
          */
        public boolean getCenter()
        {
            return getBoolAttribute(AttributeName.CENTER, null, false);
        }



        
        /* ---------------------------------------------------------------------
        Methods for Attribute MirrorAround
        --------------------------------------------------------------------- */
        /**
          * (5) set attribute MirrorAround
          * @param enumVar: the enumVar to set the attribute to
          */
        public void setMirrorAround(EnumMirrorAround enumVar)
        {
            setAttribute(AttributeName.MIRRORAROUND, enumVar.getName(), null);
        }



        /**
          * (9) get attribute MirrorAround
          * @return the value of the attribute
          */
        public EnumMirrorAround getMirrorAround()
        {
            return EnumMirrorAround.getEnum(getAttribute(AttributeName.MIRRORAROUND, null, "None"));
        }



        
        /* ---------------------------------------------------------------------
        Methods for Attribute Polarity
        --------------------------------------------------------------------- */
        /**
          * (5) set attribute Polarity
          * @param enumVar: the enumVar to set the attribute to
          */
        public void setPolarity(EnumPolarity enumVar)
        {
            setAttribute(AttributeName.POLARITY, enumVar.getName(), null);
        }



        /**
          * (9) get attribute Polarity
          * @return the value of the attribute
          */
        public EnumPolarity getPolarity()
        {
            return EnumPolarity.getEnum(getAttribute(AttributeName.POLARITY, null, "Positive"));
        }



        
        /* ---------------------------------------------------------------------
        Methods for Attribute PrintQuality
        --------------------------------------------------------------------- */
        /**
          * (5) set attribute PrintQuality
          * @param enumVar: the enumVar to set the attribute to
          */
        public void setPrintQuality(EnumPrintQuality enumVar)
        {
            setAttribute(AttributeName.PRINTQUALITY, enumVar.getName(), null);
        }



        /**
          * (9) get attribute PrintQuality
          * @return the value of the attribute
          */
        public EnumPrintQuality getPrintQuality()
        {
            return EnumPrintQuality.getEnum(getAttribute(AttributeName.PRINTQUALITY, null, "Normal"));
        }



        
        /* ---------------------------------------------------------------------
        Methods for Attribute FitToPage
        --------------------------------------------------------------------- */
        /**
          * (36) set attribute FitToPage
          * @param value: the value to set the attribute to
          */
        public void setFitToPage(boolean value)
        {
            setAttribute(AttributeName.FITTOPAGE, value, null);
        }



        /**
          * (18) get boolean attribute FitToPage
          * @return boolean the value of the attribute
          */
        public boolean getFitToPage()
        {
            return getBoolAttribute(AttributeName.FITTOPAGE, null, false);
        }



        
        /* ---------------------------------------------------------------------
        Methods for Attribute Poster
        --------------------------------------------------------------------- */
        /**
          * (36) set attribute Poster
          * @param value: the value to set the attribute to
          */
        public void setPoster(JDFXYPair value)
        {
            setAttribute(AttributeName.POSTER, value, null);
        }



        /**
          * (20) get JDFXYPair attribute Poster
          * @return JDFXYPairthe value of the attribute, null if a the
          *         attribute value is not a valid to create a JDFXYPair
          */
        public JDFXYPair getPoster()
        {
            String strAttrName = "";
            JDFXYPair nPlaceHolder = null;
            strAttrName = getAttribute(AttributeName.POSTER, null, JDFConstants.EMPTYSTRING);
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
        Methods for Attribute PosterOverlap
        --------------------------------------------------------------------- */
        /**
          * (36) set attribute PosterOverlap
          * @param value: the value to set the attribute to
          */
        public void setPosterOverlap(JDFXYPair value)
        {
            setAttribute(AttributeName.POSTEROVERLAP, value, null);
        }



        /**
          * (20) get JDFXYPair attribute PosterOverlap
          * @return JDFXYPairthe value of the attribute, null if a the
          *         attribute value is not a valid to create a JDFXYPair
          */
        public JDFXYPair getPosterOverlap()
        {
            String strAttrName = "";
            JDFXYPair nPlaceHolder = null;
            strAttrName = getAttribute(AttributeName.POSTEROVERLAP, null, JDFConstants.EMPTYSTRING);
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
        Methods for Attribute Scaling
        --------------------------------------------------------------------- */
        /**
          * (36) set attribute Scaling
          * @param value: the value to set the attribute to
          */
        public void setScaling(JDFXYPair value)
        {
            setAttribute(AttributeName.SCALING, value, null);
        }



        /**
          * (20) get JDFXYPair attribute Scaling
          * @return JDFXYPairthe value of the attribute, null if a the
          *         attribute value is not a valid to create a JDFXYPair
          */
        public JDFXYPair getScaling()
        {
            String strAttrName = "";
            JDFXYPair nPlaceHolder = null;
            strAttrName = getAttribute(AttributeName.SCALING, null, JDFConstants.EMPTYSTRING);
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
        Methods for Attribute ScalingOrigin
        --------------------------------------------------------------------- */
        /**
          * (36) set attribute ScalingOrigin
          * @param value: the value to set the attribute to
          */
        public void setScalingOrigin(JDFXYPair value)
        {
            setAttribute(AttributeName.SCALINGORIGIN, value, null);
        }



        /**
          * (20) get JDFXYPair attribute ScalingOrigin
          * @return JDFXYPairthe value of the attribute, null if a the
          *         attribute value is not a valid to create a JDFXYPair
          */
        public JDFXYPair getScalingOrigin()
        {
            String strAttrName = "";
            JDFXYPair nPlaceHolder = null;
            strAttrName = getAttribute(AttributeName.SCALINGORIGIN, null, JDFConstants.EMPTYSTRING);
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

    /** (26) getCreateObjectResolution
     * 
     * @param iSkip number of elements to skip
     * @return JDFObjectResolution the element
     */
    public JDFObjectResolution getCreateObjectResolution(int iSkip)
    {
        return (JDFObjectResolution)getCreateElement_KElement(ElementName.OBJECTRESOLUTION, null, iSkip);
    }



    /**
     * (27) const get element ObjectResolution
     * @param iSkip number of elements to skip
     * @return JDFObjectResolution the element
     * default is getObjectResolution(0)     */
    public JDFObjectResolution getObjectResolution(int iSkip)
    {
        return (JDFObjectResolution) getElement(ElementName.OBJECTRESOLUTION, null, iSkip);
    }



    public JDFObjectResolution appendObjectResolution()
    {
        return (JDFObjectResolution) appendElement(ElementName.OBJECTRESOLUTION, null);
    }

    /**
      * (31) create inter-resource link to refTarget
      * @param refTarget the element that is referenced
      */
    public void refObjectResolution(JDFObjectResolution refTarget)
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
     * (29) append elementFitPolicy
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
    /** (26) getCreateMedia
     * 
     * @param iSkip number of elements to skip
     * @return JDFMedia the element
     */
    public JDFMedia getCreateMedia(int iSkip)
    {
        return (JDFMedia)getCreateElement_KElement(ElementName.MEDIA, null, iSkip);
    }



    /**
     * (27) const get element Media
     * @param iSkip number of elements to skip
     * @return JDFMedia the element
     * default is getMedia(0)     */
    public JDFMedia getMedia(int iSkip)
    {
        return (JDFMedia) getElement(ElementName.MEDIA, null, iSkip);
    }



    public JDFMedia appendMedia()
    {
        return (JDFMedia) appendElement(ElementName.MEDIA, null);
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
     * (24) const get element PDFInterpretingParams
     * @return JDFPDFInterpretingParams the element
     */
    public JDFPDFInterpretingParams getPDFInterpretingParams()
    {
        return (JDFPDFInterpretingParams) getElement(ElementName.PDFINTERPRETINGPARAMS, null, 0);
    }



    /** (25) getCreatePDFInterpretingParams
     * 
     * @return JDFPDFInterpretingParams the element
     */
    public JDFPDFInterpretingParams getCreatePDFInterpretingParams()
    {
        return (JDFPDFInterpretingParams) getCreateElement_KElement(ElementName.PDFINTERPRETINGPARAMS, null, 0);
    }





    /**
     * (29) append elementPDFInterpretingParams
     */
    public JDFPDFInterpretingParams appendPDFInterpretingParams() throws JDFException
    {
        return (JDFPDFInterpretingParams) appendElementN(ElementName.PDFINTERPRETINGPARAMS, 1, null);
    }
}// end namespace JDF
