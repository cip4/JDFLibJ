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
import org.cip4.jdflib.core.*;                      
import org.cip4.jdflib.datatypes.*;                 
import org.cip4.jdflib.resource.*;                  
import org.cip4.jdflib.resource.process.*;

public abstract class JDFAutoLayout extends JDFResource
{

    private static final long serialVersionUID = 1L;

    private static AtrInfoTable[] atrInfoTable = new AtrInfoTable[17];
    static
    {
        atrInfoTable[0] = new AtrInfoTable(AttributeName.AUTOMATED, 0x33333333, AttributeInfo.EnumAttributeType.boolean_, null, "false");
        atrInfoTable[1] = new AtrInfoTable(AttributeName.LOCKORIGINS, 0x33333111, AttributeInfo.EnumAttributeType.boolean_, null, "false");
        atrInfoTable[2] = new AtrInfoTable(AttributeName.MAXDOCORD, 0x44443331, AttributeInfo.EnumAttributeType.integer, null, null);
        atrInfoTable[3] = new AtrInfoTable(AttributeName.MAXSETORD, 0x44443331, AttributeInfo.EnumAttributeType.integer, null, null);
        atrInfoTable[4] = new AtrInfoTable(AttributeName.ORDRESET, 0x33331111, AttributeInfo.EnumAttributeType.enumeration, EnumOrdReset.getEnum(0), "Continue");
        atrInfoTable[5] = new AtrInfoTable(AttributeName.SHEETCOUNTRESET, 0x33331111, AttributeInfo.EnumAttributeType.enumeration, EnumSheetCountReset.getEnum(0), "Continue");
        atrInfoTable[6] = new AtrInfoTable(AttributeName.NAME, 0x44443331, AttributeInfo.EnumAttributeType.string, null, null);
        atrInfoTable[7] = new AtrInfoTable(AttributeName.BASEORDRESET, 0x33331111, AttributeInfo.EnumAttributeType.enumeration, EnumBaseOrdReset.getEnum(0), null);
        atrInfoTable[8] = new AtrInfoTable(AttributeName.MAXCOLLECT, 0x33331111, AttributeInfo.EnumAttributeType.integer, null, null);
        atrInfoTable[9] = new AtrInfoTable(AttributeName.MAXORD, 0x44443333, AttributeInfo.EnumAttributeType.integer, null, null);
        atrInfoTable[10] = new AtrInfoTable(AttributeName.MINCOLLECT, 0x33331111, AttributeInfo.EnumAttributeType.integer, null, null);
        atrInfoTable[11] = new AtrInfoTable(AttributeName.ORDSCONSUMED, 0x33331111, AttributeInfo.EnumAttributeType.IntegerRangeList, null, null);
        atrInfoTable[12] = new AtrInfoTable(AttributeName.SHEETNAMEFORMAT, 0x33331111, AttributeInfo.EnumAttributeType.string, null, null);
        atrInfoTable[13] = new AtrInfoTable(AttributeName.SHEETNAMETEMPLATE, 0x33331111, AttributeInfo.EnumAttributeType.string, null, null);
        atrInfoTable[14] = new AtrInfoTable(AttributeName.SOURCEWORKSTYLE, 0x33333111, AttributeInfo.EnumAttributeType.enumeration, EnumSourceWorkStyle.getEnum(0), null);
        atrInfoTable[15] = new AtrInfoTable(AttributeName.STACKDEPTH, 0x33331111, AttributeInfo.EnumAttributeType.integer, null, null);
        atrInfoTable[16] = new AtrInfoTable(AttributeName.SURFACECONTENTSBOX, 0x33333111, AttributeInfo.EnumAttributeType.rectangle, null, null);
    }
    
    @Override
	protected AttributeInfo getTheAttributeInfo()
    {
        return super.getTheAttributeInfo().updateReplace(atrInfoTable);
    }


    private static ElemInfoTable[] elemInfoTable = new ElemInfoTable[9];
    static
    {
        elemInfoTable[0] = new ElemInfoTable(ElementName.CONTENTOBJECT, 0x33333333);
        elemInfoTable[1] = new ElemInfoTable(ElementName.INSERTSHEET, 0x33333333);
        elemInfoTable[2] = new ElemInfoTable(ElementName.LAYERLIST, 0x66666661);
        elemInfoTable[3] = new ElemInfoTable(ElementName.MARKOBJECT, 0x33333333);
        elemInfoTable[4] = new ElemInfoTable(ElementName.MEDIA, 0x33333331);
        elemInfoTable[5] = new ElemInfoTable(ElementName.MEDIASOURCE, 0x77777776);
        elemInfoTable[6] = new ElemInfoTable(ElementName.PAGECONDITION, 0x33331111);
        elemInfoTable[7] = new ElemInfoTable(ElementName.SIGNATURE, 0x44444333);
        elemInfoTable[8] = new ElemInfoTable(ElementName.TRANSFERCURVEPOOL, 0x66666661);
    }
    
    @Override
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


    @Override
	public String toString()
    {
        return " JDFAutoLayout[  --> " + super.toString() + " ]";
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
        * Enumeration strings for OrdReset
        */

        public static class EnumOrdReset extends ValuedEnum
        {
            private static final long serialVersionUID = 1L;
            private static int m_startValue = 0;

            private EnumOrdReset(String name)
            {
                super(name, m_startValue++);
            }

            public static EnumOrdReset getEnum(String enumName)
            {
                return (EnumOrdReset) getEnum(EnumOrdReset.class, enumName);
            }

            public static EnumOrdReset getEnum(int enumValue)
            {
                return (EnumOrdReset) getEnum(EnumOrdReset.class, enumValue);
            }

            public static Map getEnumMap()
            {
                return getEnumMap(EnumOrdReset.class);
            }

            public static List getEnumList()
            {
                return getEnumList(EnumOrdReset.class);
            }

            public static Iterator iterator()
            {
                return iterator(EnumOrdReset.class);
            }

            public static final EnumOrdReset Continue = new EnumOrdReset("Continue");
            public static final EnumOrdReset PagePool = new EnumOrdReset("PagePool");
            public static final EnumOrdReset PagePoolList = new EnumOrdReset("PagePoolList");
        }      



        /**
        * Enumeration strings for SheetCountReset
        */

        public static class EnumSheetCountReset extends ValuedEnum
        {
            private static final long serialVersionUID = 1L;
            private static int m_startValue = 0;

            private EnumSheetCountReset(String name)
            {
                super(name, m_startValue++);
            }

            public static EnumSheetCountReset getEnum(String enumName)
            {
                return (EnumSheetCountReset) getEnum(EnumSheetCountReset.class, enumName);
            }

            public static EnumSheetCountReset getEnum(int enumValue)
            {
                return (EnumSheetCountReset) getEnum(EnumSheetCountReset.class, enumValue);
            }

            public static Map getEnumMap()
            {
                return getEnumMap(EnumSheetCountReset.class);
            }

            public static List getEnumList()
            {
                return getEnumList(EnumSheetCountReset.class);
            }

            public static Iterator iterator()
            {
                return iterator(EnumSheetCountReset.class);
            }

            public static final EnumSheetCountReset Continue = new EnumSheetCountReset("Continue");
            public static final EnumSheetCountReset PagePool = new EnumSheetCountReset("PagePool");
            public static final EnumSheetCountReset PagePoolList = new EnumSheetCountReset("PagePoolList");
        }      



        /**
        * Enumeration strings for BaseOrdReset
        */

        public static class EnumBaseOrdReset extends ValuedEnum
        {
            private static final long serialVersionUID = 1L;
            private static int m_startValue = 0;

            private EnumBaseOrdReset(String name)
            {
                super(name, m_startValue++);
            }

            public static EnumBaseOrdReset getEnum(String enumName)
            {
                return (EnumBaseOrdReset) getEnum(EnumBaseOrdReset.class, enumName);
            }

            public static EnumBaseOrdReset getEnum(int enumValue)
            {
                return (EnumBaseOrdReset) getEnum(EnumBaseOrdReset.class, enumValue);
            }

            public static Map getEnumMap()
            {
                return getEnumMap(EnumBaseOrdReset.class);
            }

            public static List getEnumList()
            {
                return getEnumList(EnumBaseOrdReset.class);
            }

            public static Iterator iterator()
            {
                return iterator(EnumBaseOrdReset.class);
            }

            public static final EnumBaseOrdReset PagePool = new EnumBaseOrdReset("PagePool");
            public static final EnumBaseOrdReset PagePoolList = new EnumBaseOrdReset("PagePoolList");
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
            public static final EnumSourceWorkStyle Perfecting = new EnumSourceWorkStyle("Perfecting");
            public static final EnumSourceWorkStyle WorkAndBack = new EnumSourceWorkStyle("WorkAndBack");
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
            return getIntAttribute(AttributeName.MAXDOCORD, null, 0);
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
            return getIntAttribute(AttributeName.MAXSETORD, null, 0);
        }

        
        /* ---------------------------------------------------------------------
        Methods for Attribute OrdReset
        --------------------------------------------------------------------- */
        /**
          * (5) set attribute OrdReset
          * @param enumVar: the enumVar to set the attribute to
          */
        public void setOrdReset(EnumOrdReset enumVar)
        {
            setAttribute(AttributeName.ORDRESET, enumVar==null ? null : enumVar.getName(), null);
        }

        /**
          * (9) get attribute OrdReset
          * @return the value of the attribute
          */
        public EnumOrdReset getOrdReset()
        {
            return EnumOrdReset.getEnum(getAttribute(AttributeName.ORDRESET, null, "Continue"));
        }

        
        /* ---------------------------------------------------------------------
        Methods for Attribute SheetCountReset
        --------------------------------------------------------------------- */
        /**
          * (5) set attribute SheetCountReset
          * @param enumVar: the enumVar to set the attribute to
          */
        public void setSheetCountReset(EnumSheetCountReset enumVar)
        {
            setAttribute(AttributeName.SHEETCOUNTRESET, enumVar==null ? null : enumVar.getName(), null);
        }

        /**
          * (9) get attribute SheetCountReset
          * @return the value of the attribute
          */
        public EnumSheetCountReset getSheetCountReset()
        {
            return EnumSheetCountReset.getEnum(getAttribute(AttributeName.SHEETCOUNTRESET, null, "Continue"));
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
        Methods for Attribute BaseOrdReset
        --------------------------------------------------------------------- */
        /**
          * (5) set attribute BaseOrdReset
          * @param enumVar: the enumVar to set the attribute to
          */
        public void setBaseOrdReset(EnumBaseOrdReset enumVar)
        {
            setAttribute(AttributeName.BASEORDRESET, enumVar==null ? null : enumVar.getName(), null);
        }

        /**
          * (9) get attribute BaseOrdReset
          * @return the value of the attribute
          */
        public EnumBaseOrdReset getBaseOrdReset()
        {
            return EnumBaseOrdReset.getEnum(getAttribute(AttributeName.BASEORDRESET, null, null));
        }

        
        /* ---------------------------------------------------------------------
        Methods for Attribute MaxCollect
        --------------------------------------------------------------------- */
        /**
          * (36) set attribute MaxCollect
          * @param value: the value to set the attribute to
          */
        public void setMaxCollect(int value)
        {
            setAttribute(AttributeName.MAXCOLLECT, value, null);
        }

        /**
          * (15) get int attribute MaxCollect
          * @return int the value of the attribute
          */
        public int getMaxCollect()
        {
            return getIntAttribute(AttributeName.MAXCOLLECT, null, 0);
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
        Methods for Attribute MinCollect
        --------------------------------------------------------------------- */
        /**
          * (36) set attribute MinCollect
          * @param value: the value to set the attribute to
          */
        public void setMinCollect(int value)
        {
            setAttribute(AttributeName.MINCOLLECT, value, null);
        }

        /**
          * (15) get int attribute MinCollect
          * @return int the value of the attribute
          */
        public int getMinCollect()
        {
            return getIntAttribute(AttributeName.MINCOLLECT, null, 0);
        }

        
        /* ---------------------------------------------------------------------
        Methods for Attribute OrdsConsumed
        --------------------------------------------------------------------- */
        /**
          * (36) set attribute OrdsConsumed
          * @param value: the value to set the attribute to
          */
        public void setOrdsConsumed(JDFIntegerRangeList value)
        {
            setAttribute(AttributeName.ORDSCONSUMED, value, null);
        }

        /**
          * (20) get JDFIntegerRangeList attribute OrdsConsumed
          * @return JDFIntegerRangeList the value of the attribute, null if a the
          *         attribute value is not a valid to create a JDFIntegerRangeList
          */
        public JDFIntegerRangeList getOrdsConsumed()
        {
            String strAttrName = "";
            JDFIntegerRangeList nPlaceHolder = null;
            strAttrName = getAttribute(AttributeName.ORDSCONSUMED, null, JDFConstants.EMPTYSTRING);
            try
            {
                nPlaceHolder = new JDFIntegerRangeList(strAttrName);
            }
            catch(DataFormatException e)
            {
                return null;
            }
            return nPlaceHolder;
        }

        
        /* ---------------------------------------------------------------------
        Methods for Attribute SheetNameFormat
        --------------------------------------------------------------------- */
        /**
          * (36) set attribute SheetNameFormat
          * @param value: the value to set the attribute to
          */
        public void setSheetNameFormat(String value)
        {
            setAttribute(AttributeName.SHEETNAMEFORMAT, value, null);
        }

        /**
          * (23) get String attribute SheetNameFormat
          * @return the value of the attribute
          */
        public String getSheetNameFormat()
        {
            return getAttribute(AttributeName.SHEETNAMEFORMAT, null, JDFConstants.EMPTYSTRING);
        }

        
        /* ---------------------------------------------------------------------
        Methods for Attribute SheetNameTemplate
        --------------------------------------------------------------------- */
        /**
          * (36) set attribute SheetNameTemplate
          * @param value: the value to set the attribute to
          */
        public void setSheetNameTemplate(String value)
        {
            setAttribute(AttributeName.SHEETNAMETEMPLATE, value, null);
        }

        /**
          * (23) get String attribute SheetNameTemplate
          * @return the value of the attribute
          */
        public String getSheetNameTemplate()
        {
            return getAttribute(AttributeName.SHEETNAMETEMPLATE, null, JDFConstants.EMPTYSTRING);
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
            setAttribute(AttributeName.SOURCEWORKSTYLE, enumVar==null ? null : enumVar.getName(), null);
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
        Methods for Attribute StackDepth
        --------------------------------------------------------------------- */
        /**
          * (36) set attribute StackDepth
          * @param value: the value to set the attribute to
          */
        public void setStackDepth(int value)
        {
            setAttribute(AttributeName.STACKDEPTH, value, null);
        }

        /**
          * (15) get int attribute StackDepth
          * @return int the value of the attribute
          */
        public int getStackDepth()
        {
            return getIntAttribute(AttributeName.STACKDEPTH, null, 0);
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
     * Get all ContentObject from the current element
     * 
     * @return Collection<JDFContentObject>, null if none are available
     */
    public Collection<JDFContentObject> getAllContentObject()
    {
        final VElement vc = getChildElementVector(ElementName.CONTENTOBJECT, null);
        if (vc == null || vc.size() == 0)
        {
            return null;
        }

        final Vector<JDFContentObject> v = new Vector<JDFContentObject>();
        for (int i = 0; i < vc.size(); i++)
        {
            v.add((JDFContentObject) vc.get(i));
        }

        return v;
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
     * Get all InsertSheet from the current element
     * 
     * @return Collection<JDFInsertSheet>, null if none are available
     */
    public Collection<JDFInsertSheet> getAllInsertSheet()
    {
        final VElement vc = getChildElementVector(ElementName.INSERTSHEET, null);
        if (vc == null || vc.size() == 0)
        {
            return null;
        }

        final Vector<JDFInsertSheet> v = new Vector<JDFInsertSheet>();
        for (int i = 0; i < vc.size(); i++)
        {
            v.add((JDFInsertSheet) vc.get(i));
        }

        return v;
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
     * Get all MarkObject from the current element
     * 
     * @return Collection<JDFMarkObject>, null if none are available
     */
    public Collection<JDFMarkObject> getAllMarkObject()
    {
        final VElement vc = getChildElementVector(ElementName.MARKOBJECT, null);
        if (vc == null || vc.size() == 0)
        {
            return null;
        }

        final Vector<JDFMarkObject> v = new Vector<JDFMarkObject>();
        for (int i = 0; i < vc.size(); i++)
        {
            v.add((JDFMarkObject) vc.get(i));
        }

        return v;
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

    /** (26) getCreatePageCondition
     * 
     * @param iSkip number of elements to skip
     * @return JDFPageCondition the element
     */
    public JDFPageCondition getCreatePageCondition(int iSkip)
    {
        return (JDFPageCondition)getCreateElement_KElement(ElementName.PAGECONDITION, null, iSkip);
    }

    /**
     * (27) const get element PageCondition
     * @param iSkip number of elements to skip
     * @return JDFPageCondition the element
     * default is getPageCondition(0)     */
    public JDFPageCondition getPageCondition(int iSkip)
    {
        return (JDFPageCondition) getElement(ElementName.PAGECONDITION, null, iSkip);
    }

    /**
     * Get all PageCondition from the current element
     * 
     * @return Collection<JDFPageCondition>, null if none are available
     */
    public Collection<JDFPageCondition> getAllPageCondition()
    {
        final VElement vc = getChildElementVector(ElementName.PAGECONDITION, null);
        if (vc == null || vc.size() == 0)
        {
            return null;
        }

        final Vector<JDFPageCondition> v = new Vector<JDFPageCondition>();
        for (int i = 0; i < vc.size(); i++)
        {
            v.add((JDFPageCondition) vc.get(i));
        }

        return v;
    }

    /**
     * (30) append element PageCondition
     */
    public JDFPageCondition appendPageCondition() throws JDFException
    {
        return (JDFPageCondition) appendElement(ElementName.PAGECONDITION, null);
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
     * Get all Signature from the current element
     * 
     * @return Collection<JDFSignature>, null if none are available
     */
    public Collection<JDFSignature> getAllSignature()
    {
        final VElement vc = getChildElementVector(ElementName.SIGNATURE, null);
        if (vc == null || vc.size() == 0)
        {
            return null;
        }

        final Vector<JDFSignature> v = new Vector<JDFSignature>();
        for (int i = 0; i < vc.size(); i++)
        {
            v.add((JDFSignature) vc.get(i));
        }

        return v;
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
