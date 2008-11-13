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

import org.apache.commons.lang.enums.ValuedEnum;
import org.apache.xerces.dom.CoreDocumentImpl;
import org.cip4.jdflib.core.AtrInfoTable;
import org.cip4.jdflib.core.AttributeInfo;
import org.cip4.jdflib.core.AttributeName;
import org.cip4.jdflib.core.ElemInfoTable;
import org.cip4.jdflib.core.ElementInfo;
import org.cip4.jdflib.core.ElementName;
import org.cip4.jdflib.core.JDFElement;
import org.cip4.jdflib.core.JDFException;
import org.cip4.jdflib.resource.intent.JDFMediaIntent;
import org.cip4.jdflib.resource.process.JDFIDPFinishing;
import org.cip4.jdflib.resource.process.JDFIDPLayout;
import org.cip4.jdflib.resource.process.JDFMediaSource;

public abstract class JDFAutoIDPJobSheet extends JDFElement
{

    private static final long serialVersionUID = 1L;

    private static AtrInfoTable[] atrInfoTable = new AtrInfoTable[3];
    static
    {
        atrInfoTable[0] = new AtrInfoTable(AttributeName.SHEETFORMAT, 0x33333333, AttributeInfo.EnumAttributeType.NMTOKEN, null, "Standard");
        atrInfoTable[1] = new AtrInfoTable(AttributeName.SHEETOCCURENCE, 0x33333333, AttributeInfo.EnumAttributeType.enumeration, EnumSheetOccurence.getEnum(0), null);
        atrInfoTable[2] = new AtrInfoTable(AttributeName.SHEETTYPE, 0x33333333, AttributeInfo.EnumAttributeType.enumeration, EnumSheetType.getEnum(0), null);
    }
    
    protected AttributeInfo getTheAttributeInfo()
    {
        return super.getTheAttributeInfo().updateReplace(atrInfoTable);
    }


    private static ElemInfoTable[] elemInfoTable = new ElemInfoTable[4];
    static
    {
        elemInfoTable[0] = new ElemInfoTable(ElementName.IDPFINISHING, 0x33333333);
        elemInfoTable[1] = new ElemInfoTable(ElementName.IDPLAYOUT, 0x33333333);
        elemInfoTable[2] = new ElemInfoTable(ElementName.MEDIAINTENT, 0x33333333);
        elemInfoTable[3] = new ElemInfoTable(ElementName.MEDIASOURCE, 0x33333333);
    }
    
    protected ElementInfo getTheElementInfo()
    {
        return super.getTheElementInfo().updateReplace(elemInfoTable);
    }



    /**
     * Constructor for JDFAutoIDPJobSheet
     * @param myOwnerDocument
     * @param qualifiedName
     */
    protected JDFAutoIDPJobSheet(
        CoreDocumentImpl myOwnerDocument,
        String qualifiedName)
    {
        super(myOwnerDocument, qualifiedName);
    }

    /**
     * Constructor for JDFAutoIDPJobSheet
     * @param myOwnerDocument
     * @param myNamespaceURI
     * @param qualifiedName
     */
    protected JDFAutoIDPJobSheet(
        CoreDocumentImpl myOwnerDocument,
        String myNamespaceURI,
        String qualifiedName)
    {
        super(myOwnerDocument, myNamespaceURI, qualifiedName);
    }

    /**
     * Constructor for JDFAutoIDPJobSheet
     * @param myOwnerDocument
     * @param myNamespaceURI
     * @param qualifiedName
     * @param myLocalName
     */
    protected JDFAutoIDPJobSheet(
        CoreDocumentImpl myOwnerDocument,
        String myNamespaceURI,
        String qualifiedName,
        String myLocalName)
    {
        super(myOwnerDocument, myNamespaceURI, qualifiedName, myLocalName);
    }


    public String toString()
    {
        return " JDFAutoIDPJobSheet[  --> " + super.toString() + " ]";
    }


        /**
        * Enumeration strings for SheetOccurence
        */

        public static class EnumSheetOccurence extends ValuedEnum
        {
            private static final long serialVersionUID = 1L;
            private static int m_startValue = 0;

            private EnumSheetOccurence(String name)
            {
                super(name, m_startValue++);
            }

            public static EnumSheetOccurence getEnum(String enumName)
            {
                return (EnumSheetOccurence) getEnum(EnumSheetOccurence.class, enumName);
            }

            public static EnumSheetOccurence getEnum(int enumValue)
            {
                return (EnumSheetOccurence) getEnum(EnumSheetOccurence.class, enumValue);
            }

            public static Map getEnumMap()
            {
                return getEnumMap(EnumSheetOccurence.class);
            }

            public static List getEnumList()
            {
                return getEnumList(EnumSheetOccurence.class);
            }

            public static Iterator iterator()
            {
                return iterator(EnumSheetOccurence.class);
            }

            public static final EnumSheetOccurence Always = new EnumSheetOccurence("Always");
            public static final EnumSheetOccurence End = new EnumSheetOccurence("End");
            public static final EnumSheetOccurence OnError = new EnumSheetOccurence("OnError");
            public static final EnumSheetOccurence Slip = new EnumSheetOccurence("Slip");
            public static final EnumSheetOccurence Start = new EnumSheetOccurence("Start");
            public static final EnumSheetOccurence Both = new EnumSheetOccurence("Both");
            public static final EnumSheetOccurence None = new EnumSheetOccurence("None");
        }      



        /**
        * Enumeration strings for SheetType
        */

        public static class EnumSheetType extends ValuedEnum
        {
            private static final long serialVersionUID = 1L;
            private static int m_startValue = 0;

            private EnumSheetType(String name)
            {
                super(name, m_startValue++);
            }

            public static EnumSheetType getEnum(String enumName)
            {
                return (EnumSheetType) getEnum(EnumSheetType.class, enumName);
            }

            public static EnumSheetType getEnum(int enumValue)
            {
                return (EnumSheetType) getEnum(EnumSheetType.class, enumValue);
            }

            public static Map getEnumMap()
            {
                return getEnumMap(EnumSheetType.class);
            }

            public static List getEnumList()
            {
                return getEnumList(EnumSheetType.class);
            }

            public static Iterator iterator()
            {
                return iterator(EnumSheetType.class);
            }

            public static final EnumSheetType AccountingSheet = new EnumSheetType("AccountingSheet");
            public static final EnumSheetType ErrorSheet = new EnumSheetType("ErrorSheet");
            public static final EnumSheetType JobSheet = new EnumSheetType("JobSheet");
            public static final EnumSheetType SeparatorSheet = new EnumSheetType("SeparatorSheet");
        }      



/* ************************************************************************
 * Attribute getter / setter
 * ************************************************************************
 */
        
        /* ---------------------------------------------------------------------
        Methods for Attribute SheetFormat
        --------------------------------------------------------------------- */
        /**
          * (36) set attribute SheetFormat
          * @param value: the value to set the attribute to
          */
        public void setSheetFormat(String value)
        {
            setAttribute(AttributeName.SHEETFORMAT, value, null);
        }

        /**
          * (23) get String attribute SheetFormat
          * @return the value of the attribute
          */
        public String getSheetFormat()
        {
            return getAttribute(AttributeName.SHEETFORMAT, null, "Standard");
        }

        
        /* ---------------------------------------------------------------------
        Methods for Attribute SheetOccurence
        --------------------------------------------------------------------- */
        /**
          * (5) set attribute SheetOccurence
          * @param enumVar: the enumVar to set the attribute to
          */
        public void setSheetOccurence(EnumSheetOccurence enumVar)
        {
            setAttribute(AttributeName.SHEETOCCURENCE, enumVar==null ? null : enumVar.getName(), null);
        }

        /**
          * (9) get attribute SheetOccurence
          * @return the value of the attribute
          */
        public EnumSheetOccurence getSheetOccurence()
        {
            return EnumSheetOccurence.getEnum(getAttribute(AttributeName.SHEETOCCURENCE, null, null));
        }

        
        /* ---------------------------------------------------------------------
        Methods for Attribute SheetType
        --------------------------------------------------------------------- */
        /**
          * (5) set attribute SheetType
          * @param enumVar: the enumVar to set the attribute to
          */
        public void setSheetType(EnumSheetType enumVar)
        {
            setAttribute(AttributeName.SHEETTYPE, enumVar==null ? null : enumVar.getName(), null);
        }

        /**
          * (9) get attribute SheetType
          * @return the value of the attribute
          */
        public EnumSheetType getSheetType()
        {
            return EnumSheetType.getEnum(getAttribute(AttributeName.SHEETTYPE, null, null));
        }

/* ***********************************************************************
 * Element getter / setter
 * ***********************************************************************
 */

    /** (26) getCreateIDPFinishing
     * 
     * @param iSkip number of elements to skip
     * @return JDFIDPFinishing the element
     */
    public JDFIDPFinishing getCreateIDPFinishing(int iSkip)
    {
        return (JDFIDPFinishing)getCreateElement_KElement(ElementName.IDPFINISHING, null, iSkip);
    }

    /**
     * (27) const get element IDPFinishing
     * @param iSkip number of elements to skip
     * @return JDFIDPFinishing the element
     * default is getIDPFinishing(0)     */
    public JDFIDPFinishing getIDPFinishing(int iSkip)
    {
        return (JDFIDPFinishing) getElement(ElementName.IDPFINISHING, null, iSkip);
    }

    /**
     * Get all IDPFinishing from the current element
     * 
     * @return Collection<JDFIDPFinishing>
     */
    public Collection<JDFIDPFinishing> getAllIDPFinishing()
    {
        Vector<JDFIDPFinishing> v = new Vector<JDFIDPFinishing>();

        JDFIDPFinishing kElem = (JDFIDPFinishing) getFirstChildElement(ElementName.IDPFINISHING, null);

        while (kElem != null)
        {
            v.add(kElem);

            kElem = (JDFIDPFinishing) kElem.getNextSiblingElement(ElementName.IDPFINISHING, null);
        }

        return v;
    }

    /**
     * (30) append element IDPFinishing
     */
    public JDFIDPFinishing appendIDPFinishing() throws JDFException
    {
        return (JDFIDPFinishing) appendElement(ElementName.IDPFINISHING, null);
    }

    /** (26) getCreateIDPLayout
     * 
     * @param iSkip number of elements to skip
     * @return JDFIDPLayout the element
     */
    public JDFIDPLayout getCreateIDPLayout(int iSkip)
    {
        return (JDFIDPLayout)getCreateElement_KElement(ElementName.IDPLAYOUT, null, iSkip);
    }

    /**
     * (27) const get element IDPLayout
     * @param iSkip number of elements to skip
     * @return JDFIDPLayout the element
     * default is getIDPLayout(0)     */
    public JDFIDPLayout getIDPLayout(int iSkip)
    {
        return (JDFIDPLayout) getElement(ElementName.IDPLAYOUT, null, iSkip);
    }

    /**
     * Get all IDPLayout from the current element
     * 
     * @return Collection<JDFIDPLayout>
     */
    public Collection<JDFIDPLayout> getAllIDPLayout()
    {
        Vector<JDFIDPLayout> v = new Vector<JDFIDPLayout>();

        JDFIDPLayout kElem = (JDFIDPLayout) getFirstChildElement(ElementName.IDPLAYOUT, null);

        while (kElem != null)
        {
            v.add(kElem);

            kElem = (JDFIDPLayout) kElem.getNextSiblingElement(ElementName.IDPLAYOUT, null);
        }

        return v;
    }

    /**
     * (30) append element IDPLayout
     */
    public JDFIDPLayout appendIDPLayout() throws JDFException
    {
        return (JDFIDPLayout) appendElement(ElementName.IDPLAYOUT, null);
    }

    /** (26) getCreateMediaIntent
     * 
     * @param iSkip number of elements to skip
     * @return JDFMediaIntent the element
     */
    public JDFMediaIntent getCreateMediaIntent(int iSkip)
    {
        return (JDFMediaIntent)getCreateElement_KElement(ElementName.MEDIAINTENT, null, iSkip);
    }

    /**
     * (27) const get element MediaIntent
     * @param iSkip number of elements to skip
     * @return JDFMediaIntent the element
     * default is getMediaIntent(0)     */
    public JDFMediaIntent getMediaIntent(int iSkip)
    {
        return (JDFMediaIntent) getElement(ElementName.MEDIAINTENT, null, iSkip);
    }

    /**
     * Get all MediaIntent from the current element
     * 
     * @return Collection<JDFMediaIntent>
     */
    public Collection<JDFMediaIntent> getAllMediaIntent()
    {
        Vector<JDFMediaIntent> v = new Vector<JDFMediaIntent>();

        JDFMediaIntent kElem = (JDFMediaIntent) getFirstChildElement(ElementName.MEDIAINTENT, null);

        while (kElem != null)
        {
            v.add(kElem);

            kElem = (JDFMediaIntent) kElem.getNextSiblingElement(ElementName.MEDIAINTENT, null);
        }

        return v;
    }

    /**
     * (30) append element MediaIntent
     */
    public JDFMediaIntent appendMediaIntent() throws JDFException
    {
        return (JDFMediaIntent) appendElement(ElementName.MEDIAINTENT, null);
    }

    /**
      * (31) create inter-resource link to refTarget
      * @param refTarget the element that is referenced
      */
    public void refMediaIntent(JDFMediaIntent refTarget)
    {
        refElement(refTarget);
    }

    /** (26) getCreateMediaSource
     * 
     * @param iSkip number of elements to skip
     * @return JDFMediaSource the element
     */
    public JDFMediaSource getCreateMediaSource(int iSkip)
    {
        return (JDFMediaSource)getCreateElement_KElement(ElementName.MEDIASOURCE, null, iSkip);
    }

    /**
     * (27) const get element MediaSource
     * @param iSkip number of elements to skip
     * @return JDFMediaSource the element
     * default is getMediaSource(0)     */
    public JDFMediaSource getMediaSource(int iSkip)
    {
        return (JDFMediaSource) getElement(ElementName.MEDIASOURCE, null, iSkip);
    }

    /**
     * Get all MediaSource from the current element
     * 
     * @return Collection<JDFMediaSource>
     */
    public Collection<JDFMediaSource> getAllMediaSource()
    {
        Vector<JDFMediaSource> v = new Vector<JDFMediaSource>();

        JDFMediaSource kElem = (JDFMediaSource) getFirstChildElement(ElementName.MEDIASOURCE, null);

        while (kElem != null)
        {
            v.add(kElem);

            kElem = (JDFMediaSource) kElem.getNextSiblingElement(ElementName.MEDIASOURCE, null);
        }

        return v;
    }

    /**
     * (30) append element MediaSource
     */
    public JDFMediaSource appendMediaSource() throws JDFException
    {
        return (JDFMediaSource) appendElement(ElementName.MEDIASOURCE, null);
    }

    /**
      * (31) create inter-resource link to refTarget
      * @param refTarget the element that is referenced
      */
    public void refMediaSource(JDFMediaSource refTarget)
    {
        refElement(refTarget);
    }

}// end namespace JDF
