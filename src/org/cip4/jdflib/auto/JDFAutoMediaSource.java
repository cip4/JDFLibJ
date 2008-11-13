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
import org.cip4.jdflib.core.ElemInfoTable;
import org.cip4.jdflib.core.ElementInfo;
import org.cip4.jdflib.core.ElementName;
import org.cip4.jdflib.core.JDFConstants;
import org.cip4.jdflib.core.JDFException;
import org.cip4.jdflib.resource.JDFResource;
import org.cip4.jdflib.resource.process.JDFComponent;
import org.cip4.jdflib.resource.process.JDFMedia;

public abstract class JDFAutoMediaSource extends JDFResource
{

    private static final long serialVersionUID = 1L;

    private static AtrInfoTable[] atrInfoTable = new AtrInfoTable[4];
    static
    {
        atrInfoTable[0] = new AtrInfoTable(AttributeName.MANUALFEED, 0x44444443, AttributeInfo.EnumAttributeType.boolean_, null, "false");
        atrInfoTable[1] = new AtrInfoTable(AttributeName.LEADINGEDGE, 0x44444443, AttributeInfo.EnumAttributeType.double_, null, null);
        atrInfoTable[2] = new AtrInfoTable(AttributeName.MEDIALOCATION, 0x44444443, AttributeInfo.EnumAttributeType.string, null, null);
        atrInfoTable[3] = new AtrInfoTable(AttributeName.SHEETLAY, 0x44444443, AttributeInfo.EnumAttributeType.enumeration, EnumSheetLay.getEnum(0), null);
    }
    
    protected AttributeInfo getTheAttributeInfo()
    {
        return super.getTheAttributeInfo().updateReplace(atrInfoTable);
    }


    private static ElemInfoTable[] elemInfoTable = new ElemInfoTable[2];
    static
    {
        elemInfoTable[0] = new ElemInfoTable(ElementName.MEDIA, 0x77777776);
        elemInfoTable[1] = new ElemInfoTable(ElementName.COMPONENT, 0x77777776);
    }
    
    protected ElementInfo getTheElementInfo()
    {
        return super.getTheElementInfo().updateReplace(elemInfoTable);
    }



    /**
     * Constructor for JDFAutoMediaSource
     * @param myOwnerDocument
     * @param qualifiedName
     */
    protected JDFAutoMediaSource(
        CoreDocumentImpl myOwnerDocument,
        String qualifiedName)
    {
        super(myOwnerDocument, qualifiedName);
    }

    /**
     * Constructor for JDFAutoMediaSource
     * @param myOwnerDocument
     * @param myNamespaceURI
     * @param qualifiedName
     */
    protected JDFAutoMediaSource(
        CoreDocumentImpl myOwnerDocument,
        String myNamespaceURI,
        String qualifiedName)
    {
        super(myOwnerDocument, myNamespaceURI, qualifiedName);
    }

    /**
     * Constructor for JDFAutoMediaSource
     * @param myOwnerDocument
     * @param myNamespaceURI
     * @param qualifiedName
     * @param myLocalName
     */
    protected JDFAutoMediaSource(
        CoreDocumentImpl myOwnerDocument,
        String myNamespaceURI,
        String qualifiedName,
        String myLocalName)
    {
        super(myOwnerDocument, myNamespaceURI, qualifiedName, myLocalName);
    }


    public String toString()
    {
        return " JDFAutoMediaSource[  --> " + super.toString() + " ]";
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
        * Enumeration strings for SheetLay
        */

        public static class EnumSheetLay extends ValuedEnum
        {
            private static final long serialVersionUID = 1L;
            private static int m_startValue = 0;

            private EnumSheetLay(String name)
            {
                super(name, m_startValue++);
            }

            public static EnumSheetLay getEnum(String enumName)
            {
                return (EnumSheetLay) getEnum(EnumSheetLay.class, enumName);
            }

            public static EnumSheetLay getEnum(int enumValue)
            {
                return (EnumSheetLay) getEnum(EnumSheetLay.class, enumValue);
            }

            public static Map getEnumMap()
            {
                return getEnumMap(EnumSheetLay.class);
            }

            public static List getEnumList()
            {
                return getEnumList(EnumSheetLay.class);
            }

            public static Iterator iterator()
            {
                return iterator(EnumSheetLay.class);
            }

            public static final EnumSheetLay Left = new EnumSheetLay("Left");
            public static final EnumSheetLay Right = new EnumSheetLay("Right");
            public static final EnumSheetLay Center = new EnumSheetLay("Center");
        }      



/* ************************************************************************
 * Attribute getter / setter
 * ************************************************************************
 */
        
        /* ---------------------------------------------------------------------
        Methods for Attribute ManualFeed
        --------------------------------------------------------------------- */
        /**
          * (36) set attribute ManualFeed
          * @param value: the value to set the attribute to
          */
        public void setManualFeed(boolean value)
        {
            setAttribute(AttributeName.MANUALFEED, value, null);
        }

        /**
          * (18) get boolean attribute ManualFeed
          * @return boolean the value of the attribute
          */
        public boolean getManualFeed()
        {
            return getBoolAttribute(AttributeName.MANUALFEED, null, false);
        }

        
        /* ---------------------------------------------------------------------
        Methods for Attribute LeadingEdge
        --------------------------------------------------------------------- */
        /**
          * (36) set attribute LeadingEdge
          * @param value: the value to set the attribute to
          */
        public void setLeadingEdge(double value)
        {
            setAttribute(AttributeName.LEADINGEDGE, value, null);
        }

        /**
          * (17) get double attribute LeadingEdge
          * @return double the value of the attribute
          */
        public double getLeadingEdge()
        {
            return getRealAttribute(AttributeName.LEADINGEDGE, null, 0.0);
        }

        
        /* ---------------------------------------------------------------------
        Methods for Attribute MediaLocation
        --------------------------------------------------------------------- */
        /**
          * (36) set attribute MediaLocation
          * @param value: the value to set the attribute to
          */
        public void setMediaLocation(String value)
        {
            setAttribute(AttributeName.MEDIALOCATION, value, null);
        }

        /**
          * (23) get String attribute MediaLocation
          * @return the value of the attribute
          */
        public String getMediaLocation()
        {
            return getAttribute(AttributeName.MEDIALOCATION, null, JDFConstants.EMPTYSTRING);
        }

        
        /* ---------------------------------------------------------------------
        Methods for Attribute SheetLay
        --------------------------------------------------------------------- */
        /**
          * (5) set attribute SheetLay
          * @param enumVar: the enumVar to set the attribute to
          */
        public void setSheetLay(EnumSheetLay enumVar)
        {
            setAttribute(AttributeName.SHEETLAY, enumVar==null ? null : enumVar.getName(), null);
        }

        /**
          * (9) get attribute SheetLay
          * @return the value of the attribute
          */
        public EnumSheetLay getSheetLay()
        {
            return EnumSheetLay.getEnum(getAttribute(AttributeName.SHEETLAY, null, null));
        }

/* ***********************************************************************
 * Element getter / setter
 * ***********************************************************************
 */

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
     * (24) const get element Component
     * @return JDFComponent the element
     */
    public JDFComponent getComponent()
    {
        return (JDFComponent) getElement(ElementName.COMPONENT, null, 0);
    }

    /** (25) getCreateComponent
     * 
     * @return JDFComponent the element
     */
    public JDFComponent getCreateComponent()
    {
        return (JDFComponent) getCreateElement_KElement(ElementName.COMPONENT, null, 0);
    }

    /**
     * (29) append element Component
     */
    public JDFComponent appendComponent() throws JDFException
    {
        return (JDFComponent) appendElementN(ElementName.COMPONENT, 1, null);
    }

    /**
      * (31) create inter-resource link to refTarget
      * @param refTarget the element that is referenced
      */
    public void refComponent(JDFComponent refTarget)
    {
        refElement(refTarget);
    }

}// end namespace JDF
