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
import org.cip4.jdflib.core.*;                      
import org.cip4.jdflib.datatypes.*;                 
import org.cip4.jdflib.resource.*;                  
import org.cip4.jdflib.resource.process.*;

public abstract class JDFAutoShapeDef extends JDFResource
{

    private static final long serialVersionUID = 1L;

    private static AtrInfoTable[] atrInfoTable = new AtrInfoTable[8];
    static
    {
        atrInfoTable[0] = new AtrInfoTable(AttributeName.LOCKORIGINS, 0x33331111, AttributeInfo.EnumAttributeType.boolean_, null, "false");
        atrInfoTable[1] = new AtrInfoTable(AttributeName.AREA, 0x33331111, AttributeInfo.EnumAttributeType.double_, null, null);
        atrInfoTable[2] = new AtrInfoTable(AttributeName.CUTBOX, 0x33331111, AttributeInfo.EnumAttributeType.rectangle, null, null);
        atrInfoTable[3] = new AtrInfoTable(AttributeName.DIMENSIONS, 0x33331111, AttributeInfo.EnumAttributeType.shape, null, null);
        atrInfoTable[4] = new AtrInfoTable(AttributeName.FLUTEDIRECTION, 0x33331111, AttributeInfo.EnumAttributeType.enumeration, EnumFluteDirection.getEnum(0), null);
        atrInfoTable[5] = new AtrInfoTable(AttributeName.GRAINDIRECTION, 0x33331111, AttributeInfo.EnumAttributeType.enumeration, EnumGrainDirection.getEnum(0), null);
        atrInfoTable[6] = new AtrInfoTable(AttributeName.MEDIASIDE, 0x33331111, AttributeInfo.EnumAttributeType.enumeration, EnumMediaSide.getEnum(0), null);
        atrInfoTable[7] = new AtrInfoTable(AttributeName.RESOURCEWEIGHT, 0x33331111, AttributeInfo.EnumAttributeType.double_, null, null);
    }
    
    @Override
	protected AttributeInfo getTheAttributeInfo()
    {
        return super.getTheAttributeInfo().updateReplace(atrInfoTable);
    }


    private static ElemInfoTable[] elemInfoTable = new ElemInfoTable[3];
    static
    {
        elemInfoTable[0] = new ElemInfoTable(ElementName.FILESPEC, 0x66661111);
        elemInfoTable[1] = new ElemInfoTable(ElementName.MEDIA, 0x66661111);
        elemInfoTable[2] = new ElemInfoTable(ElementName.SHAPE, 0x66661111);
    }
    
    @Override
	protected ElementInfo getTheElementInfo()
    {
        return super.getTheElementInfo().updateReplace(elemInfoTable);
    }



    /**
     * Constructor for JDFAutoShapeDef
     * @param myOwnerDocument
     * @param qualifiedName
     */
    protected JDFAutoShapeDef(
        CoreDocumentImpl myOwnerDocument,
        String qualifiedName)
    {
        super(myOwnerDocument, qualifiedName);
    }

    /**
     * Constructor for JDFAutoShapeDef
     * @param myOwnerDocument
     * @param myNamespaceURI
     * @param qualifiedName
     */
    protected JDFAutoShapeDef(
        CoreDocumentImpl myOwnerDocument,
        String myNamespaceURI,
        String qualifiedName)
    {
        super(myOwnerDocument, myNamespaceURI, qualifiedName);
    }

    /**
     * Constructor for JDFAutoShapeDef
     * @param myOwnerDocument
     * @param myNamespaceURI
     * @param qualifiedName
     * @param myLocalName
     */
    protected JDFAutoShapeDef(
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
        return " JDFAutoShapeDef[  --> " + super.toString() + " ]";
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
        * Enumeration strings for FluteDirection
        */

        public static class EnumFluteDirection extends ValuedEnum
        {
            private static final long serialVersionUID = 1L;
            private static int m_startValue = 0;

            private EnumFluteDirection(String name)
            {
                super(name, m_startValue++);
            }

            public static EnumFluteDirection getEnum(String enumName)
            {
                return (EnumFluteDirection) getEnum(EnumFluteDirection.class, enumName);
            }

            public static EnumFluteDirection getEnum(int enumValue)
            {
                return (EnumFluteDirection) getEnum(EnumFluteDirection.class, enumValue);
            }

            public static Map getEnumMap()
            {
                return getEnumMap(EnumFluteDirection.class);
            }

            public static List getEnumList()
            {
                return getEnumList(EnumFluteDirection.class);
            }

            public static Iterator iterator()
            {
                return iterator(EnumFluteDirection.class);
            }

            public static final EnumFluteDirection XDirection = new EnumFluteDirection("XDirection");
            public static final EnumFluteDirection YDirection = new EnumFluteDirection("YDirection");
        }      



        /**
        * Enumeration strings for GrainDirection
        */

        public static class EnumGrainDirection extends ValuedEnum
        {
            private static final long serialVersionUID = 1L;
            private static int m_startValue = 0;

            private EnumGrainDirection(String name)
            {
                super(name, m_startValue++);
            }

            public static EnumGrainDirection getEnum(String enumName)
            {
                return (EnumGrainDirection) getEnum(EnumGrainDirection.class, enumName);
            }

            public static EnumGrainDirection getEnum(int enumValue)
            {
                return (EnumGrainDirection) getEnum(EnumGrainDirection.class, enumValue);
            }

            public static Map getEnumMap()
            {
                return getEnumMap(EnumGrainDirection.class);
            }

            public static List getEnumList()
            {
                return getEnumList(EnumGrainDirection.class);
            }

            public static Iterator iterator()
            {
                return iterator(EnumGrainDirection.class);
            }

            public static final EnumGrainDirection XDirection = new EnumGrainDirection("XDirection");
            public static final EnumGrainDirection YDirection = new EnumGrainDirection("YDirection");
            public static final EnumGrainDirection Both = new EnumGrainDirection("Both");
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
        Methods for Attribute LockOrigins
        --------------------------------------------------------------------- */
        /**
          * (36) set attribute LockOrigins
          * @param value: the value to set the attribute to
          */
        public void setLockOrigins(boolean value)
        {
            setAttribute(AttributeName.LOCKORIGINS, value, null);
        }

        /**
          * (18) get boolean attribute LockOrigins
          * @return boolean the value of the attribute
          */
        public boolean getLockOrigins()
        {
            return getBoolAttribute(AttributeName.LOCKORIGINS, null, false);
        }

        
        /* ---------------------------------------------------------------------
        Methods for Attribute Area
        --------------------------------------------------------------------- */
        /**
          * (36) set attribute Area
          * @param value: the value to set the attribute to
          */
        public void setArea(double value)
        {
            setAttribute(AttributeName.AREA, value, null);
        }

        /**
          * (17) get double attribute Area
          * @return double the value of the attribute
          */
        public double getArea()
        {
            return getRealAttribute(AttributeName.AREA, null, 0.0);
        }

        
        /* ---------------------------------------------------------------------
        Methods for Attribute CutBox
        --------------------------------------------------------------------- */
        /**
          * (36) set attribute CutBox
          * @param value: the value to set the attribute to
          */
        public void setCutBox(JDFRectangle value)
        {
            setAttribute(AttributeName.CUTBOX, value, null);
        }

        /**
          * (20) get JDFRectangle attribute CutBox
          * @return JDFRectangle the value of the attribute, null if a the
          *         attribute value is not a valid to create a JDFRectangle
          */
        public JDFRectangle getCutBox()
        {
            String strAttrName = "";
            JDFRectangle nPlaceHolder = null;
            strAttrName = getAttribute(AttributeName.CUTBOX, null, JDFConstants.EMPTYSTRING);
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
        Methods for Attribute Dimensions
        --------------------------------------------------------------------- */
        /**
          * (36) set attribute Dimensions
          * @param value: the value to set the attribute to
          */
        public void setDimensions(JDFShape value)
        {
            setAttribute(AttributeName.DIMENSIONS, value, null);
        }

        /**
          * (20) get JDFShape attribute Dimensions
          * @return JDFShape the value of the attribute, null if a the
          *         attribute value is not a valid to create a JDFShape
          */
        public JDFShape getDimensions()
        {
            String strAttrName = "";
            JDFShape nPlaceHolder = null;
            strAttrName = getAttribute(AttributeName.DIMENSIONS, null, JDFConstants.EMPTYSTRING);
            try
            {
                nPlaceHolder = new JDFShape(strAttrName);
            }
            catch(DataFormatException e)
            {
                return null;
            }
            return nPlaceHolder;
        }

        
        /* ---------------------------------------------------------------------
        Methods for Attribute FluteDirection
        --------------------------------------------------------------------- */
        /**
          * (5) set attribute FluteDirection
          * @param enumVar: the enumVar to set the attribute to
          */
        public void setFluteDirection(EnumFluteDirection enumVar)
        {
            setAttribute(AttributeName.FLUTEDIRECTION, enumVar==null ? null : enumVar.getName(), null);
        }

        /**
          * (9) get attribute FluteDirection
          * @return the value of the attribute
          */
        public EnumFluteDirection getFluteDirection()
        {
            return EnumFluteDirection.getEnum(getAttribute(AttributeName.FLUTEDIRECTION, null, null));
        }

        
        /* ---------------------------------------------------------------------
        Methods for Attribute GrainDirection
        --------------------------------------------------------------------- */
        /**
          * (5) set attribute GrainDirection
          * @param enumVar: the enumVar to set the attribute to
          */
        public void setGrainDirection(EnumGrainDirection enumVar)
        {
            setAttribute(AttributeName.GRAINDIRECTION, enumVar==null ? null : enumVar.getName(), null);
        }

        /**
          * (9) get attribute GrainDirection
          * @return the value of the attribute
          */
        public EnumGrainDirection getGrainDirection()
        {
            return EnumGrainDirection.getEnum(getAttribute(AttributeName.GRAINDIRECTION, null, null));
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
            return EnumMediaSide.getEnum(getAttribute(AttributeName.MEDIASIDE, null, null));
        }

        
        /* ---------------------------------------------------------------------
        Methods for Attribute ResourceWeight
        --------------------------------------------------------------------- */
        /**
          * (36) set attribute ResourceWeight
          * @param value: the value to set the attribute to
          */
        @Override
		public void setResourceWeight(double value)
        {
            setAttribute(AttributeName.RESOURCEWEIGHT, value, null);
        }

        /**
          * (17) get double attribute ResourceWeight
          * @return double the value of the attribute
          */
        @Override
		public double getResourceWeight()
        {
            return getRealAttribute(AttributeName.RESOURCEWEIGHT, null, 0.0);
        }

/* ***********************************************************************
 * Element getter / setter
 * ***********************************************************************
 */

    /**
     * (24) const get element FileSpec
     * @return JDFFileSpec the element
     */
    public JDFFileSpec getFileSpec()
    {
        return (JDFFileSpec) getElement(ElementName.FILESPEC, null, 0);
    }

    /** (25) getCreateFileSpec
     * 
     * @return JDFFileSpec the element
     */
    public JDFFileSpec getCreateFileSpec()
    {
        return (JDFFileSpec) getCreateElement_KElement(ElementName.FILESPEC, null, 0);
    }

    /**
     * (29) append element FileSpec
     */
    public JDFFileSpec appendFileSpec() throws JDFException
    {
        return (JDFFileSpec) appendElementN(ElementName.FILESPEC, 1, null);
    }

    /**
      * (31) create inter-resource link to refTarget
      * @param refTarget the element that is referenced
      */
    public void refFileSpec(JDFFileSpec refTarget)
    {
        refElement(refTarget);
    }

    /**
     * (24) const get element Media
     * @return JDFMedia the element
     */
    public JDFMedia getMedia()
    {
        return (JDFMedia) getElement(ElementName.MEDIA, null, 0);
    }

    /** (25) getCreateMedia
     * 
     * @return JDFMedia the element
     */
    public JDFMedia getCreateMedia()
    {
        return (JDFMedia) getCreateElement_KElement(ElementName.MEDIA, null, 0);
    }

    /**
     * (29) append element Media
     */
    public JDFMedia appendMedia() throws JDFException
    {
        return (JDFMedia) appendElementN(ElementName.MEDIA, 1, null);
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
     * (24) const get element Shape
     * @return JDFShapeElement the element
     */
    public JDFShapeElement getShape()
    {
        return (JDFShapeElement) getElement(ElementName.SHAPE, null, 0);
    }

    /** (25) getCreateShape
     * 
     * @return JDFShapeElement the element
     */
    public JDFShapeElement getCreateShape()
    {
        return (JDFShapeElement) getCreateElement_KElement(ElementName.SHAPE, null, 0);
    }

    /**
     * (29) append element Shape
     */
    public JDFShapeElement appendShape() throws JDFException
    {
        return (JDFShapeElement) appendElementN(ElementName.SHAPE, 1, null);
    }

    /**
      * (31) create inter-resource link to refTarget
      * @param refTarget the element that is referenced
      */
    public void refShape(JDFShapeElement refTarget)
    {
        refElement(refTarget);
    }

}// end namespace JDF
