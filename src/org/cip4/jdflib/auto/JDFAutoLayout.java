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
import org.cip4.jdflib.datatypes.JDFRectangle;
import org.cip4.jdflib.resource.JDFLayerList;
import org.cip4.jdflib.resource.JDFMarkObject;
import org.cip4.jdflib.resource.JDFResource;
import org.cip4.jdflib.resource.JDFSignature;
import org.cip4.jdflib.resource.process.JDFContentObject;
import org.cip4.jdflib.resource.process.JDFInsertSheet;
import org.cip4.jdflib.resource.process.JDFMedia;
import org.cip4.jdflib.resource.process.JDFMediaSource;
import org.cip4.jdflib.resource.process.JDFTransferCurvePool;
    /*
    *****************************************************************************
    class JDFAutoLayout : public JDFResource

    *****************************************************************************
    */

public abstract class JDFAutoLayout extends JDFResource
{

    private static final long serialVersionUID = 1L;

    private static AtrInfoTable[] atrInfoTable = new AtrInfoTable[8];
    static
    {
        atrInfoTable[0] = new AtrInfoTable(AttributeName.AUTOMATED, 0x33333333, AttributeInfo.EnumAttributeType.boolean_, null, "false");
        atrInfoTable[1] = new AtrInfoTable(AttributeName.LOCKORIGINS, 0x33333111, AttributeInfo.EnumAttributeType.boolean_, null, "false");
        atrInfoTable[2] = new AtrInfoTable(AttributeName.MAXDOCORD, 0x33333331, AttributeInfo.EnumAttributeType.integer, null, "1");
        atrInfoTable[3] = new AtrInfoTable(AttributeName.MAXSETORD, 0x33333331, AttributeInfo.EnumAttributeType.integer, null, "1");
        atrInfoTable[4] = new AtrInfoTable(AttributeName.NAME, 0x33333331, AttributeInfo.EnumAttributeType.string, null, null);
        atrInfoTable[5] = new AtrInfoTable(AttributeName.MAXORD, 0x33333333, AttributeInfo.EnumAttributeType.integer, null, null);
        atrInfoTable[6] = new AtrInfoTable(AttributeName.SOURCEWORKSTYLE, 0x33333111, AttributeInfo.EnumAttributeType.enumeration, EnumSourceWorkStyle.getEnum(0), null);
        atrInfoTable[7] = new AtrInfoTable(AttributeName.SURFACECONTENTSBOX, 0x33333111, AttributeInfo.EnumAttributeType.rectangle, null, null);
    }
    
    protected AttributeInfo getTheAttributeInfo()
    {
        return super.getTheAttributeInfo().updateReplace(atrInfoTable);
    }


    private static ElemInfoTable[] elemInfoTable = new ElemInfoTable[8];
    static
    {
        elemInfoTable[0] = new ElemInfoTable(ElementName.CONTENTOBJECT, 0x33333333);
        elemInfoTable[1] = new ElemInfoTable(ElementName.INSERTSHEET, 0x33333333);
        elemInfoTable[2] = new ElemInfoTable(ElementName.LAYERLIST, 0x66666661);
        elemInfoTable[3] = new ElemInfoTable(ElementName.MARKOBJECT, 0x33333333);
        elemInfoTable[4] = new ElemInfoTable(ElementName.MEDIA, 0x33333331);
        elemInfoTable[5] = new ElemInfoTable(ElementName.MEDIASOURCE, 0x77777776);
        elemInfoTable[6] = new ElemInfoTable(ElementName.SIGNATURE, 0x44444333);
        elemInfoTable[7] = new ElemInfoTable(ElementName.TRANSFERCURVEPOOL, 0x66666661);
    }
    
    protected ElementInfo getTheElementInfo()
    {
        return super.getTheElementInfo().updateReplace(elemInfoTable);
    }



    /**
     * Constructor for JDFAutoLayout
     * @param myOwnerDocument
     * @param qualifiedName
     */
    protected JDFAutoLayout(
        CoreDocumentImpl myOwnerDocument,
        String qualifiedName)
    {
        super(myOwnerDocument, qualifiedName);
    }

    /**
     * Constructor for JDFAutoLayout
     * @param myOwnerDocument
     * @param myNamespaceURI
     * @param qualifiedName
     */
    protected JDFAutoLayout(
        CoreDocumentImpl myOwnerDocument,
        String myNamespaceURI,
        String qualifiedName)
    {
        super(myOwnerDocument, myNamespaceURI, qualifiedName);
    }

    /**
     * Constructor for JDFAutoLayout
     * @param myOwnerDocument
     * @param myNamespaceURI
     * @param qualifiedName
     * @param myLocalName
     */
    protected JDFAutoLayout(
        CoreDocumentImpl myOwnerDocument,
        String myNamespaceURI,
        String qualifiedName,
        String myLocalName)
    {
        super(myOwnerDocument, myNamespaceURI, qualifiedName, myLocalName);
    }


    public String toString()
    {
        return " JDFAutoLayout[  --> " + super.toString() + " ]";
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
        * Enumeration strings for SourceWorkStyle
        */

        public static class EnumSourceWorkStyle extends ValuedEnum
        {
            private static final long serialVersionUID = 1L;
            private static int m_startValue = 0;

            private EnumSourceWorkStyle(String name)
            {
                super(name, m_startValue++);
            }

            public static EnumSourceWorkStyle getEnum(String enumName)
            {
                return (EnumSourceWorkStyle) getEnum(EnumSourceWorkStyle.class, enumName);
            }

            public static EnumSourceWorkStyle getEnum(int enumValue)
            {
                return (EnumSourceWorkStyle) getEnum(EnumSourceWorkStyle.class, enumValue);
            }

            public static Map getEnumMap()
            {
                return getEnumMap(EnumSourceWorkStyle.class);
            }

            public static List getEnumList()
            {
                return getEnumList(EnumSourceWorkStyle.class);
            }

            public static Iterator iterator()
            {
                return iterator(EnumSourceWorkStyle.class);
            }

            public static final EnumSourceWorkStyle Simplex = new EnumSourceWorkStyle("Simplex");
            public static final EnumSourceWorkStyle WorkAndBack = new EnumSourceWorkStyle("WorkAndBack");
            public static final EnumSourceWorkStyle Perfecting = new EnumSourceWorkStyle("Perfecting");
            public static final EnumSourceWorkStyle WorkAndTurn = new EnumSourceWorkStyle("WorkAndTurn");
            public static final EnumSourceWorkStyle WorkAndTumble = new EnumSourceWorkStyle("WorkAndTumble");
            public static final EnumSourceWorkStyle WorkAndTwist = new EnumSourceWorkStyle("WorkAndTwist");
        }      



/* ************************************************************************
 * Attribute getter / setter
 * ************************************************************************
 */
        
        /* ---------------------------------------------------------------------
        Methods for Attribute Automated
        --------------------------------------------------------------------- */
        /**
          * (36) set attribute Automated
          * @param value: the value to set the attribute to
          */
        public void setAutomated(boolean value)
        {
            setAttribute(AttributeName.AUTOMATED, value, null);
        }

        /**
          * (18) get boolean attribute Automated
          * @return boolean the value of the attribute
          */
        public boolean getAutomated()
        {
            return getBoolAttribute(AttributeName.AUTOMATED, null, false);
        }

        
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
        Methods for Attribute MaxDocOrd
        --------------------------------------------------------------------- */
        /**
          * (36) set attribute MaxDocOrd
          * @param value: the value to set the attribute to
          */
        public void setMaxDocOrd(int value)
        {
            setAttribute(AttributeName.MAXDOCORD, value, null);
        }

        /**
          * (15) get int attribute MaxDocOrd
          * @return int the value of the attribute
          */
        public int getMaxDocOrd()
        {
            return getIntAttribute(AttributeName.MAXDOCORD, null, 1);
        }

        
        /* ---------------------------------------------------------------------
        Methods for Attribute MaxSetOrd
        --------------------------------------------------------------------- */
        /**
          * (36) set attribute MaxSetOrd
          * @param value: the value to set the attribute to
          */
        public void setMaxSetOrd(int value)
        {
            setAttribute(AttributeName.MAXSETORD, value, null);
        }

        /**
          * (15) get int attribute MaxSetOrd
          * @return int the value of the attribute
          */
        public int getMaxSetOrd()
        {
            return getIntAttribute(AttributeName.MAXSETORD, null, 1);
        }

        
        /* ---------------------------------------------------------------------
        Methods for Attribute Name
        --------------------------------------------------------------------- */
        /**
          * (36) set attribute Name
          * @param value: the value to set the attribute to
          */
        public void setName(String value)
        {
            setAttribute(AttributeName.NAME, value, null);
        }

        /**
          * (23) get String attribute Name
          * @return the value of the attribute
          */
        public String getName()
        {
            return getAttribute(AttributeName.NAME, null, JDFConstants.EMPTYSTRING);
        }

        
        /* ---------------------------------------------------------------------
        Methods for Attribute MaxOrd
        --------------------------------------------------------------------- */
        /**
          * (36) set attribute MaxOrd
          * @param value: the value to set the attribute to
          */
        public void setMaxOrd(int value)
        {
            setAttribute(AttributeName.MAXORD, value, null);
        }

        /**
          * (15) get int attribute MaxOrd
          * @return int the value of the attribute
          */
        public int getMaxOrd()
        {
            return getIntAttribute(AttributeName.MAXORD, null, 0);
        }

        
        /* ---------------------------------------------------------------------
        Methods for Attribute SourceWorkStyle
        --------------------------------------------------------------------- */
        /**
          * (5) set attribute SourceWorkStyle
          * @param enumVar: the enumVar to set the attribute to
          */
        public void setSourceWorkStyle(EnumSourceWorkStyle enumVar)
        {
            setAttribute(AttributeName.SOURCEWORKSTYLE, enumVar.getName(), null);
        }

        /**
          * (9) get attribute SourceWorkStyle
          * @return the value of the attribute
          */
        public EnumSourceWorkStyle getSourceWorkStyle()
        {
            return EnumSourceWorkStyle.getEnum(getAttribute(AttributeName.SOURCEWORKSTYLE, null, null));
        }

        
        /* ---------------------------------------------------------------------
        Methods for Attribute SurfaceContentsBox
        --------------------------------------------------------------------- */
        /**
          * (36) set attribute SurfaceContentsBox
          * @param value: the value to set the attribute to
          */
        public void setSurfaceContentsBox(JDFRectangle value)
        {
            setAttribute(AttributeName.SURFACECONTENTSBOX, value, null);
        }

        /**
          * (20) get JDFRectangle attribute SurfaceContentsBox
          * @return JDFRectangle the value of the attribute, null if a the
          *         attribute value is not a valid to create a JDFRectangle
          */
        public JDFRectangle getSurfaceContentsBox()
        {
            String strAttrName = "";
            JDFRectangle nPlaceHolder = null;
            strAttrName = getAttribute(AttributeName.SURFACECONTENTSBOX, null, JDFConstants.EMPTYSTRING);
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

/* ***********************************************************************
 * Element getter / setter
 * ***********************************************************************
 */

    /** (26) getCreateContentObject
     * 
     * @param iSkip number of elements to skip
     * @return JDFContentObject the element
     */
    public JDFContentObject getCreateContentObject(int iSkip)
    {
        return (JDFContentObject)getCreateElement_KElement(ElementName.CONTENTOBJECT, null, iSkip);
    }

    /**
     * (27) const get element ContentObject
     * @param iSkip number of elements to skip
     * @return JDFContentObject the element
     * default is getContentObject(0)     */
    public JDFContentObject getContentObject(int iSkip)
    {
        return (JDFContentObject) getElement(ElementName.CONTENTOBJECT, null, iSkip);
    }

    /**
     * (30) append element ContentObject
     */
    public JDFContentObject appendContentObject() throws JDFException
    {
        return (JDFContentObject) appendElement(ElementName.CONTENTOBJECT, null);
    }

    /** (26) getCreateInsertSheet
     * 
     * @param iSkip number of elements to skip
     * @return JDFInsertSheet the element
     */
    public JDFInsertSheet getCreateInsertSheet(int iSkip)
    {
        return (JDFInsertSheet)getCreateElement_KElement(ElementName.INSERTSHEET, null, iSkip);
    }

    /**
     * (27) const get element InsertSheet
     * @param iSkip number of elements to skip
     * @return JDFInsertSheet the element
     * default is getInsertSheet(0)     */
    public JDFInsertSheet getInsertSheet(int iSkip)
    {
        return (JDFInsertSheet) getElement(ElementName.INSERTSHEET, null, iSkip);
    }

    /**
     * (30) append element InsertSheet
     */
    public JDFInsertSheet appendInsertSheet() throws JDFException
    {
        return (JDFInsertSheet) appendElement(ElementName.INSERTSHEET, null);
    }

    /**
      * (31) create inter-resource link to refTarget
      * @param refTarget the element that is referenced
      */
    public void refInsertSheet(JDFInsertSheet refTarget)
    {
        refElement(refTarget);
    }

    /**
     * (24) const get element LayerList
     * @return JDFLayerList the element
     */
    public JDFLayerList getLayerList()
    {
        return (JDFLayerList) getElement(ElementName.LAYERLIST, null, 0);
    }

    /** (25) getCreateLayerList
     * 
     * @return JDFLayerList the element
     */
    public JDFLayerList getCreateLayerList()
    {
        return (JDFLayerList) getCreateElement_KElement(ElementName.LAYERLIST, null, 0);
    }

    /**
     * (29) append element LayerList
     */
    public JDFLayerList appendLayerList() throws JDFException
    {
        return (JDFLayerList) appendElementN(ElementName.LAYERLIST, 1, null);
    }

    /** (26) getCreateMarkObject
     * 
     * @param iSkip number of elements to skip
     * @return JDFMarkObject the element
     */
    public JDFMarkObject getCreateMarkObject(int iSkip)
    {
        return (JDFMarkObject)getCreateElement_KElement(ElementName.MARKOBJECT, null, iSkip);
    }

    /**
     * (27) const get element MarkObject
     * @param iSkip number of elements to skip
     * @return JDFMarkObject the element
     * default is getMarkObject(0)     */
    public JDFMarkObject getMarkObject(int iSkip)
    {
        return (JDFMarkObject) getElement(ElementName.MARKOBJECT, null, iSkip);
    }

    /**
     * (30) append element MarkObject
     */
    public JDFMarkObject appendMarkObject() throws JDFException
    {
        return (JDFMarkObject) appendElement(ElementName.MARKOBJECT, null);
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

    /**
     * (24) const get element MediaSource
     * @return JDFMediaSource the element
     */
    public JDFMediaSource getMediaSource()
    {
        return (JDFMediaSource) getElement(ElementName.MEDIASOURCE, null, 0);
    }

    /** (25) getCreateMediaSource
     * 
     * @return JDFMediaSource the element
     */
    public JDFMediaSource getCreateMediaSource()
    {
        return (JDFMediaSource) getCreateElement_KElement(ElementName.MEDIASOURCE, null, 0);
    }

    /**
     * (29) append element MediaSource
     */
    public JDFMediaSource appendMediaSource() throws JDFException
    {
        return (JDFMediaSource) appendElementN(ElementName.MEDIASOURCE, 1, null);
    }

    /**
      * (31) create inter-resource link to refTarget
      * @param refTarget the element that is referenced
      */
    public void refMediaSource(JDFMediaSource refTarget)
    {
        refElement(refTarget);
    }

    /** (26) getCreateSignature
     * 
     * @param iSkip number of elements to skip
     * @return JDFSignature the element
     */
    public JDFSignature getCreateSignature(int iSkip)
    {
        return (JDFSignature)getCreateElement_KElement(ElementName.SIGNATURE, null, iSkip);
    }

    /**
     * (27) const get element Signature
     * @param iSkip number of elements to skip
     * @return JDFSignature the element
     * default is getSignature(0)     */
    public JDFSignature getSignature(int iSkip)
    {
        return (JDFSignature) getElement(ElementName.SIGNATURE, null, iSkip);
    }

    /**
     * (30) append element Signature
     */
    public JDFSignature appendSignature() throws JDFException
    {
        return (JDFSignature) appendElement(ElementName.SIGNATURE, null);
    }

    /**
     * (24) const get element TransferCurvePool
     * @return JDFTransferCurvePool the element
     */
    public JDFTransferCurvePool getTransferCurvePool()
    {
        return (JDFTransferCurvePool) getElement(ElementName.TRANSFERCURVEPOOL, null, 0);
    }

    /** (25) getCreateTransferCurvePool
     * 
     * @return JDFTransferCurvePool the element
     */
    public JDFTransferCurvePool getCreateTransferCurvePool()
    {
        return (JDFTransferCurvePool) getCreateElement_KElement(ElementName.TRANSFERCURVEPOOL, null, 0);
    }

    /**
     * (29) append element TransferCurvePool
     */
    public JDFTransferCurvePool appendTransferCurvePool() throws JDFException
    {
        return (JDFTransferCurvePool) appendElementN(ElementName.TRANSFERCURVEPOOL, 1, null);
    }

    /**
      * (31) create inter-resource link to refTarget
      * @param refTarget the element that is referenced
      */
    public void refTransferCurvePool(JDFTransferCurvePool refTarget)
    {
        refElement(refTarget);
    }

}// end namespace JDF
