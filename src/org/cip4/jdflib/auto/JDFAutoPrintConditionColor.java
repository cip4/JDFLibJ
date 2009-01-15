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
import org.cip4.jdflib.datatypes.JDFCMYKColor;
import org.cip4.jdflib.datatypes.JDFLabColor;
import org.cip4.jdflib.datatypes.JDFRGBColor;
import org.cip4.jdflib.resource.process.JDFDeviceNColor;
import org.cip4.jdflib.resource.process.JDFFileSpec;
import org.cip4.jdflib.resource.process.JDFMedia;
import org.cip4.jdflib.resource.process.JDFTransferCurve;

public abstract class JDFAutoPrintConditionColor extends JDFElement
{

    private static final long serialVersionUID = 1L;

    private static AtrInfoTable[] atrInfoTable = new AtrInfoTable[12];
    static
    {
        atrInfoTable[0] = new AtrInfoTable(AttributeName.CMYK, 0x33333311, AttributeInfo.EnumAttributeType.CMYKColor, null, null);
        atrInfoTable[1] = new AtrInfoTable(AttributeName.COLORBOOK, 0x33333311, AttributeInfo.EnumAttributeType.string, null, null);
        atrInfoTable[2] = new AtrInfoTable(AttributeName.COLORBOOKENTRY, 0x33333311, AttributeInfo.EnumAttributeType.string, null, null);
        atrInfoTable[3] = new AtrInfoTable(AttributeName.COLORBOOKPREFIX, 0x33333311, AttributeInfo.EnumAttributeType.string, null, null);
        atrInfoTable[4] = new AtrInfoTable(AttributeName.COLORBOOKSUFFIX, 0x33333311, AttributeInfo.EnumAttributeType.string, null, null);
        atrInfoTable[5] = new AtrInfoTable(AttributeName.DENSITY, 0x33333311, AttributeInfo.EnumAttributeType.double_, null, null);
        atrInfoTable[6] = new AtrInfoTable(AttributeName.LAB, 0x33333311, AttributeInfo.EnumAttributeType.LabColor, null, null);
        atrInfoTable[7] = new AtrInfoTable(AttributeName.MAPPINGSELECTION, 0x33333311, AttributeInfo.EnumAttributeType.enumeration, EnumMappingSelection.getEnum(0), null);
        atrInfoTable[8] = new AtrInfoTable(AttributeName.MEDIASIDE, 0x33333311, AttributeInfo.EnumAttributeType.enumeration, EnumMediaSide.getEnum(0), "Both");
        atrInfoTable[9] = new AtrInfoTable(AttributeName.NEUTRALDENSITY, 0x33333311, AttributeInfo.EnumAttributeType.double_, null, null);
        atrInfoTable[10] = new AtrInfoTable(AttributeName.PRINTCONDITIONNAME, 0x33333311, AttributeInfo.EnumAttributeType.NMTOKEN, null, null);
        atrInfoTable[11] = new AtrInfoTable(AttributeName.SRGB, 0x33333311, AttributeInfo.EnumAttributeType.string, null, null);
    }
    
    @Override
	protected AttributeInfo getTheAttributeInfo()
    {
        return super.getTheAttributeInfo().updateReplace(atrInfoTable);
    }


    private static ElemInfoTable[] elemInfoTable = new ElemInfoTable[4];
    static
    {
        elemInfoTable[0] = new ElemInfoTable(ElementName.FILESPEC, 0x33333311);
        elemInfoTable[1] = new ElemInfoTable(ElementName.DEVICENCOLOR, 0x33333311);
        elemInfoTable[2] = new ElemInfoTable(ElementName.MEDIA, 0x33333311);
        elemInfoTable[3] = new ElemInfoTable(ElementName.TRANSFERCURVE, 0x33333311);
    }
    
    @Override
	protected ElementInfo getTheElementInfo()
    {
        return super.getTheElementInfo().updateReplace(elemInfoTable);
    }



    /**
     * Constructor for JDFAutoPrintConditionColor
     * @param myOwnerDocument
     * @param qualifiedName
     */
    protected JDFAutoPrintConditionColor(
        CoreDocumentImpl myOwnerDocument,
        String qualifiedName)
    {
        super(myOwnerDocument, qualifiedName);
    }

    /**
     * Constructor for JDFAutoPrintConditionColor
     * @param myOwnerDocument
     * @param myNamespaceURI
     * @param qualifiedName
     */
    protected JDFAutoPrintConditionColor(
        CoreDocumentImpl myOwnerDocument,
        String myNamespaceURI,
        String qualifiedName)
    {
        super(myOwnerDocument, myNamespaceURI, qualifiedName);
    }

    /**
     * Constructor for JDFAutoPrintConditionColor
     * @param myOwnerDocument
     * @param myNamespaceURI
     * @param qualifiedName
     * @param myLocalName
     */
    protected JDFAutoPrintConditionColor(
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
        return " JDFAutoPrintConditionColor[  --> " + super.toString() + " ]";
    }


        /**
        * Enumeration strings for MappingSelection
        */

        public static class EnumMappingSelection extends ValuedEnum
        {
            private static final long serialVersionUID = 1L;
            private static int m_startValue = 0;

            private EnumMappingSelection(String name)
            {
                super(name, m_startValue++);
            }

            public static EnumMappingSelection getEnum(String enumName)
            {
                return (EnumMappingSelection) getEnum(EnumMappingSelection.class, enumName);
            }

            public static EnumMappingSelection getEnum(int enumValue)
            {
                return (EnumMappingSelection) getEnum(EnumMappingSelection.class, enumValue);
            }

            public static Map getEnumMap()
            {
                return getEnumMap(EnumMappingSelection.class);
            }

            public static List getEnumList()
            {
                return getEnumList(EnumMappingSelection.class);
            }

            public static Iterator iterator()
            {
                return iterator(EnumMappingSelection.class);
            }

            public static final EnumMappingSelection UsePDLValues = new EnumMappingSelection("UsePDLValues");
            public static final EnumMappingSelection UseLocalPrinterValues = new EnumMappingSelection("UseLocalPrinterValues");
            public static final EnumMappingSelection UseProcessColorValues = new EnumMappingSelection("UseProcessColorValues");
        }      



        /**
        * Enumeration strings for MediaSide
        */

        public static class EnumMediaSide extends ValuedEnum
        {
            private static final long serialVersionUID = 1L;
            private static int m_startValue = 0;

            private EnumMediaSide(String name)
            {
                super(name, m_startValue++);
            }

            public static EnumMediaSide getEnum(String enumName)
            {
                return (EnumMediaSide) getEnum(EnumMediaSide.class, enumName);
            }

            public static EnumMediaSide getEnum(int enumValue)
            {
                return (EnumMediaSide) getEnum(EnumMediaSide.class, enumValue);
            }

            public static Map getEnumMap()
            {
                return getEnumMap(EnumMediaSide.class);
            }

            public static List getEnumList()
            {
                return getEnumList(EnumMediaSide.class);
            }

            public static Iterator iterator()
            {
                return iterator(EnumMediaSide.class);
            }

            public static final EnumMediaSide Front = new EnumMediaSide("Front");
            public static final EnumMediaSide Back = new EnumMediaSide("Back");
            public static final EnumMediaSide Both = new EnumMediaSide("Both");
        }      



/* ************************************************************************
 * Attribute getter / setter
 * ************************************************************************
 */
        
        /* ---------------------------------------------------------------------
        Methods for Attribute CMYK
        --------------------------------------------------------------------- */
        /**
          * (36) set attribute CMYK
          * @param value: the value to set the attribute to
          */
        public void setCMYK(JDFCMYKColor value)
        {
            setAttribute(AttributeName.CMYK, value, null);
        }

        /**
          * (20) get JDFCMYKColor attribute CMYK
          * @return JDFCMYKColor the value of the attribute, null if a the
          *         attribute value is not a valid to create a JDFCMYKColor
          */
        public JDFCMYKColor getCMYK()
        {
            String strAttrName = "";
            JDFCMYKColor nPlaceHolder = null;
            strAttrName = getAttribute(AttributeName.CMYK, null, JDFConstants.EMPTYSTRING);
            try
            {
                nPlaceHolder = new JDFCMYKColor(strAttrName);
            }
            catch(DataFormatException e)
            {
                return null;
            }
            return nPlaceHolder;
        }

        
        /* ---------------------------------------------------------------------
        Methods for Attribute ColorBook
        --------------------------------------------------------------------- */
        /**
          * (36) set attribute ColorBook
          * @param value: the value to set the attribute to
          */
        public void setColorBook(String value)
        {
            setAttribute(AttributeName.COLORBOOK, value, null);
        }

        /**
          * (23) get String attribute ColorBook
          * @return the value of the attribute
          */
        public String getColorBook()
        {
            return getAttribute(AttributeName.COLORBOOK, null, JDFConstants.EMPTYSTRING);
        }

        
        /* ---------------------------------------------------------------------
        Methods for Attribute ColorBookEntry
        --------------------------------------------------------------------- */
        /**
          * (36) set attribute ColorBookEntry
          * @param value: the value to set the attribute to
          */
        public void setColorBookEntry(String value)
        {
            setAttribute(AttributeName.COLORBOOKENTRY, value, null);
        }

        /**
          * (23) get String attribute ColorBookEntry
          * @return the value of the attribute
          */
        public String getColorBookEntry()
        {
            return getAttribute(AttributeName.COLORBOOKENTRY, null, JDFConstants.EMPTYSTRING);
        }

        
        /* ---------------------------------------------------------------------
        Methods for Attribute ColorBookPrefix
        --------------------------------------------------------------------- */
        /**
          * (36) set attribute ColorBookPrefix
          * @param value: the value to set the attribute to
          */
        public void setColorBookPrefix(String value)
        {
            setAttribute(AttributeName.COLORBOOKPREFIX, value, null);
        }

        /**
          * (23) get String attribute ColorBookPrefix
          * @return the value of the attribute
          */
        public String getColorBookPrefix()
        {
            return getAttribute(AttributeName.COLORBOOKPREFIX, null, JDFConstants.EMPTYSTRING);
        }

        
        /* ---------------------------------------------------------------------
        Methods for Attribute ColorBookSuffix
        --------------------------------------------------------------------- */
        /**
          * (36) set attribute ColorBookSuffix
          * @param value: the value to set the attribute to
          */
        public void setColorBookSuffix(String value)
        {
            setAttribute(AttributeName.COLORBOOKSUFFIX, value, null);
        }

        /**
          * (23) get String attribute ColorBookSuffix
          * @return the value of the attribute
          */
        public String getColorBookSuffix()
        {
            return getAttribute(AttributeName.COLORBOOKSUFFIX, null, JDFConstants.EMPTYSTRING);
        }

        
        /* ---------------------------------------------------------------------
        Methods for Attribute Density
        --------------------------------------------------------------------- */
        /**
          * (36) set attribute Density
          * @param value: the value to set the attribute to
          */
        public void setDensity(double value)
        {
            setAttribute(AttributeName.DENSITY, value, null);
        }

        /**
          * (17) get double attribute Density
          * @return double the value of the attribute
          */
        public double getDensity()
        {
            return getRealAttribute(AttributeName.DENSITY, null, 0.0);
        }

        
        /* ---------------------------------------------------------------------
        Methods for Attribute Lab
        --------------------------------------------------------------------- */
        /**
          * (36) set attribute Lab
          * @param value: the value to set the attribute to
          */
        public void setLab(JDFLabColor value)
        {
            setAttribute(AttributeName.LAB, value, null);
        }

        /**
          * (20) get JDFLabColor attribute Lab
          * @return JDFLabColor the value of the attribute, null if a the
          *         attribute value is not a valid to create a JDFLabColor
          */
        public JDFLabColor getLab()
        {
            String strAttrName = "";
            JDFLabColor nPlaceHolder = null;
            strAttrName = getAttribute(AttributeName.LAB, null, JDFConstants.EMPTYSTRING);
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
        Methods for Attribute MappingSelection
        --------------------------------------------------------------------- */
        /**
          * (5) set attribute MappingSelection
          * @param enumVar: the enumVar to set the attribute to
          */
        public void setMappingSelection(EnumMappingSelection enumVar)
        {
            setAttribute(AttributeName.MAPPINGSELECTION, enumVar==null ? null : enumVar.getName(), null);
        }

        /**
          * (9) get attribute MappingSelection
          * @return the value of the attribute
          */
        public EnumMappingSelection getMappingSelection()
        {
            return EnumMappingSelection.getEnum(getAttribute(AttributeName.MAPPINGSELECTION, null, null));
        }

        
        /* ---------------------------------------------------------------------
        Methods for Attribute MediaSide
        --------------------------------------------------------------------- */
        /**
          * (5) set attribute MediaSide
          * @param enumVar: the enumVar to set the attribute to
          */
        public void setMediaSide(EnumMediaSide enumVar)
        {
            setAttribute(AttributeName.MEDIASIDE, enumVar==null ? null : enumVar.getName(), null);
        }

        /**
          * (9) get attribute MediaSide
          * @return the value of the attribute
          */
        public EnumMediaSide getMediaSide()
        {
            return EnumMediaSide.getEnum(getAttribute(AttributeName.MEDIASIDE, null, "Both"));
        }

        
        /* ---------------------------------------------------------------------
        Methods for Attribute NeutralDensity
        --------------------------------------------------------------------- */
        /**
          * (36) set attribute NeutralDensity
          * @param value: the value to set the attribute to
          */
        public void setNeutralDensity(double value)
        {
            setAttribute(AttributeName.NEUTRALDENSITY, value, null);
        }

        /**
          * (17) get double attribute NeutralDensity
          * @return double the value of the attribute
          */
        public double getNeutralDensity()
        {
            return getRealAttribute(AttributeName.NEUTRALDENSITY, null, 0.0);
        }

        
        /* ---------------------------------------------------------------------
        Methods for Attribute PrintConditionName
        --------------------------------------------------------------------- */
        /**
          * (36) set attribute PrintConditionName
          * @param value: the value to set the attribute to
          */
        public void setPrintConditionName(String value)
        {
            setAttribute(AttributeName.PRINTCONDITIONNAME, value, null);
        }

        /**
          * (23) get String attribute PrintConditionName
          * @return the value of the attribute
          */
        public String getPrintConditionName()
        {
            return getAttribute(AttributeName.PRINTCONDITIONNAME, null, JDFConstants.EMPTYSTRING);
        }

        
        /* ---------------------------------------------------------------------
        Methods for Attribute sRGB
        --------------------------------------------------------------------- */
        /**
          * (36) set attribute sRGB
          * @param value: the value to set the attribute to
          */
        public void setsRGB(JDFRGBColor value)
        {
            setAttribute(AttributeName.SRGB, value, null);
        }

        /**
          * (20) get JDFRGBColor attribute sRGB
          * @return JDFRGBColor the value of the attribute, null if a the
          *         attribute value is not a valid to create a JDFRGBColor
          */
        public JDFRGBColor getsRGB()
        {
            String strAttrName = "";
            JDFRGBColor nPlaceHolder = null;
            strAttrName = getAttribute(AttributeName.SRGB, null, JDFConstants.EMPTYSTRING);
            try
            {
                nPlaceHolder = new JDFRGBColor(strAttrName);
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

    /** (26) getCreateFileSpec
     * 
     * @param iSkip number of elements to skip
     * @return JDFFileSpec the element
     */
    public JDFFileSpec getCreateFileSpec(int iSkip)
    {
        return (JDFFileSpec)getCreateElement_KElement(ElementName.FILESPEC, null, iSkip);
    }

    /**
     * (27) const get element FileSpec
     * @param iSkip number of elements to skip
     * @return JDFFileSpec the element
     * default is getFileSpec(0)     */
    public JDFFileSpec getFileSpec(int iSkip)
    {
        return (JDFFileSpec) getElement(ElementName.FILESPEC, null, iSkip);
    }

    /**
     * Get all FileSpec from the current element
     * 
     * @return Collection<JDFFileSpec>, null if none are available
     */
    public Collection<JDFFileSpec> getAllFileSpec()
    {
        final VElement vc = getChildElementVector(ElementName.FILESPEC, null);
        if (vc == null || vc.size() == 0)
        {
            return null;
        }

        final Vector<JDFFileSpec> v = new Vector<JDFFileSpec>();
        for (int i = 0; i < vc.size(); i++)
        {
            v.add((JDFFileSpec) vc.get(i));
        }

        return v;
    }

    /**
     * (30) append element FileSpec
     */
    public JDFFileSpec appendFileSpec() throws JDFException
    {
        return (JDFFileSpec) appendElement(ElementName.FILESPEC, null);
    }

    /**
      * (31) create inter-resource link to refTarget
      * @param refTarget the element that is referenced
      */
    public void refFileSpec(JDFFileSpec refTarget)
    {
        refElement(refTarget);
    }

    /** (26) getCreateDeviceNColor
     * 
     * @param iSkip number of elements to skip
     * @return JDFDeviceNColor the element
     */
    public JDFDeviceNColor getCreateDeviceNColor(int iSkip)
    {
        return (JDFDeviceNColor)getCreateElement_KElement(ElementName.DEVICENCOLOR, null, iSkip);
    }

    /**
     * (27) const get element DeviceNColor
     * @param iSkip number of elements to skip
     * @return JDFDeviceNColor the element
     * default is getDeviceNColor(0)     */
    public JDFDeviceNColor getDeviceNColor(int iSkip)
    {
        return (JDFDeviceNColor) getElement(ElementName.DEVICENCOLOR, null, iSkip);
    }

    /**
     * Get all DeviceNColor from the current element
     * 
     * @return Collection<JDFDeviceNColor>, null if none are available
     */
    public Collection<JDFDeviceNColor> getAllDeviceNColor()
    {
        final VElement vc = getChildElementVector(ElementName.DEVICENCOLOR, null);
        if (vc == null || vc.size() == 0)
        {
            return null;
        }

        final Vector<JDFDeviceNColor> v = new Vector<JDFDeviceNColor>();
        for (int i = 0; i < vc.size(); i++)
        {
            v.add((JDFDeviceNColor) vc.get(i));
        }

        return v;
    }

    /**
     * (30) append element DeviceNColor
     */
    public JDFDeviceNColor appendDeviceNColor() throws JDFException
    {
        return (JDFDeviceNColor) appendElement(ElementName.DEVICENCOLOR, null);
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

    /**
     * Get all Media from the current element
     * 
     * @return Collection<JDFMedia>, null if none are available
     */
    public Collection<JDFMedia> getAllMedia()
    {
        final VElement vc = getChildElementVector(ElementName.MEDIA, null);
        if (vc == null || vc.size() == 0)
        {
            return null;
        }

        final Vector<JDFMedia> v = new Vector<JDFMedia>();
        for (int i = 0; i < vc.size(); i++)
        {
            v.add((JDFMedia) vc.get(i));
        }

        return v;
    }

    /**
     * (30) append element Media
     */
    public JDFMedia appendMedia() throws JDFException
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

    /** (26) getCreateTransferCurve
     * 
     * @param iSkip number of elements to skip
     * @return JDFTransferCurve the element
     */
    public JDFTransferCurve getCreateTransferCurve(int iSkip)
    {
        return (JDFTransferCurve)getCreateElement_KElement(ElementName.TRANSFERCURVE, null, iSkip);
    }

    /**
     * (27) const get element TransferCurve
     * @param iSkip number of elements to skip
     * @return JDFTransferCurve the element
     * default is getTransferCurve(0)     */
    public JDFTransferCurve getTransferCurve(int iSkip)
    {
        return (JDFTransferCurve) getElement(ElementName.TRANSFERCURVE, null, iSkip);
    }

    /**
     * Get all TransferCurve from the current element
     * 
     * @return Collection<JDFTransferCurve>, null if none are available
     */
    public Collection<JDFTransferCurve> getAllTransferCurve()
    {
        final VElement vc = getChildElementVector(ElementName.TRANSFERCURVE, null);
        if (vc == null || vc.size() == 0)
        {
            return null;
        }

        final Vector<JDFTransferCurve> v = new Vector<JDFTransferCurve>();
        for (int i = 0; i < vc.size(); i++)
        {
            v.add((JDFTransferCurve) vc.get(i));
        }

        return v;
    }

    /**
     * (30) append element TransferCurve
     */
    public JDFTransferCurve appendTransferCurve() throws JDFException
    {
        return (JDFTransferCurve) appendElement(ElementName.TRANSFERCURVE, null);
    }

    /**
      * (31) create inter-resource link to refTarget
      * @param refTarget the element that is referenced
      */
    public void refTransferCurve(JDFTransferCurve refTarget)
    {
        refElement(refTarget);
    }

}// end namespace JDF
