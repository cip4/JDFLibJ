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
import org.cip4.jdflib.core.JDFElement;
import org.cip4.jdflib.core.JDFException;
import org.cip4.jdflib.resource.JDFJobField;
import org.cip4.jdflib.resource.process.JDFPosition;

public abstract class JDFAutoStripMark extends JDFElement
{

    private static final long serialVersionUID = 1L;

    private static AtrInfoTable[] atrInfoTable = new AtrInfoTable[3];
    static
    {
        atrInfoTable[0] = new AtrInfoTable(AttributeName.MARKNAME, 0x33333111, AttributeInfo.EnumAttributeType.NMTOKEN, null, null);
        atrInfoTable[1] = new AtrInfoTable(AttributeName.MARKSIDE, 0x33333111, AttributeInfo.EnumAttributeType.enumeration, EnumMarkSide.getEnum(0), null);
        atrInfoTable[2] = new AtrInfoTable(AttributeName.STRIPMARKDETAILS, 0x33333111, AttributeInfo.EnumAttributeType.string, null, null);
    }
    
    protected AttributeInfo getTheAttributeInfo()
    {
        return super.getTheAttributeInfo().updateReplace(atrInfoTable);
    }


    private static ElemInfoTable[] elemInfoTable = new ElemInfoTable[2];
    static
    {
        elemInfoTable[0] = new ElemInfoTable(ElementName.POSITION, 0x66666111);
        elemInfoTable[1] = new ElemInfoTable(ElementName.JOBFIELD, 0x66666111);
    }
    
    protected ElementInfo getTheElementInfo()
    {
        return super.getTheElementInfo().updateReplace(elemInfoTable);
    }



    /**
     * Constructor for JDFAutoStripMark
     * @param myOwnerDocument
     * @param qualifiedName
     */
    protected JDFAutoStripMark(
        CoreDocumentImpl myOwnerDocument,
        String qualifiedName)
    {
        super(myOwnerDocument, qualifiedName);
    }

    /**
     * Constructor for JDFAutoStripMark
     * @param myOwnerDocument
     * @param myNamespaceURI
     * @param qualifiedName
     */
    protected JDFAutoStripMark(
        CoreDocumentImpl myOwnerDocument,
        String myNamespaceURI,
        String qualifiedName)
    {
        super(myOwnerDocument, myNamespaceURI, qualifiedName);
    }

    /**
     * Constructor for JDFAutoStripMark
     * @param myOwnerDocument
     * @param myNamespaceURI
     * @param qualifiedName
     * @param myLocalName
     */
    protected JDFAutoStripMark(
        CoreDocumentImpl myOwnerDocument,
        String myNamespaceURI,
        String qualifiedName,
        String myLocalName)
    {
        super(myOwnerDocument, myNamespaceURI, qualifiedName, myLocalName);
    }


    public String toString()
    {
        return " JDFAutoStripMark[  --> " + super.toString() + " ]";
    }


        /**
        * Enumeration strings for MarkSide
        */

        public static class EnumMarkSide extends ValuedEnum
        {
            private static final long serialVersionUID = 1L;
            private static int m_startValue = 0;

            private EnumMarkSide(String name)
            {
                super(name, m_startValue++);
            }

            public static EnumMarkSide getEnum(String enumName)
            {
                return (EnumMarkSide) getEnum(EnumMarkSide.class, enumName);
            }

            public static EnumMarkSide getEnum(int enumValue)
            {
                return (EnumMarkSide) getEnum(EnumMarkSide.class, enumValue);
            }

            public static Map getEnumMap()
            {
                return getEnumMap(EnumMarkSide.class);
            }

            public static List getEnumList()
            {
                return getEnumList(EnumMarkSide.class);
            }

            public static Iterator iterator()
            {
                return iterator(EnumMarkSide.class);
            }

            public static final EnumMarkSide Front = new EnumMarkSide("Front");
            public static final EnumMarkSide Back = new EnumMarkSide("Back");
            public static final EnumMarkSide TwoSidedBackToBack = new EnumMarkSide("TwoSidedBackToBack");
            public static final EnumMarkSide TwoSidedIndependent = new EnumMarkSide("TwoSidedIndependent");
        }      



/* ************************************************************************
 * Attribute getter / setter
 * ************************************************************************
 */
        
        /* ---------------------------------------------------------------------
        Methods for Attribute MarkName
        --------------------------------------------------------------------- */
        /**
          * (36) set attribute MarkName
          * @param value: the value to set the attribute to
          */
        public void setMarkName(String value)
        {
            setAttribute(AttributeName.MARKNAME, value, null);
        }

        /**
          * (23) get String attribute MarkName
          * @return the value of the attribute
          */
        public String getMarkName()
        {
            return getAttribute(AttributeName.MARKNAME, null, JDFConstants.EMPTYSTRING);
        }

        
        /* ---------------------------------------------------------------------
        Methods for Attribute MarkSide
        --------------------------------------------------------------------- */
        /**
          * (5) set attribute MarkSide
          * @param enumVar: the enumVar to set the attribute to
          */
        public void setMarkSide(EnumMarkSide enumVar)
        {
            setAttribute(AttributeName.MARKSIDE, enumVar==null ? null : enumVar.getName(), null);
        }

        /**
          * (9) get attribute MarkSide
          * @return the value of the attribute
          */
        public EnumMarkSide getMarkSide()
        {
            return EnumMarkSide.getEnum(getAttribute(AttributeName.MARKSIDE, null, null));
        }

        
        /* ---------------------------------------------------------------------
        Methods for Attribute StripMarkDetails
        --------------------------------------------------------------------- */
        /**
          * (36) set attribute StripMarkDetails
          * @param value: the value to set the attribute to
          */
        public void setStripMarkDetails(String value)
        {
            setAttribute(AttributeName.STRIPMARKDETAILS, value, null);
        }

        /**
          * (23) get String attribute StripMarkDetails
          * @return the value of the attribute
          */
        public String getStripMarkDetails()
        {
            return getAttribute(AttributeName.STRIPMARKDETAILS, null, JDFConstants.EMPTYSTRING);
        }

/* ***********************************************************************
 * Element getter / setter
 * ***********************************************************************
 */

    /**
     * (24) const get element Position
     * @return JDFPosition the element
     */
    public JDFPosition getPosition()
    {
        return (JDFPosition) getElement(ElementName.POSITION, null, 0);
    }

    /** (25) getCreatePosition
     * 
     * @return JDFPosition the element
     */
    public JDFPosition getCreatePosition()
    {
        return (JDFPosition) getCreateElement_KElement(ElementName.POSITION, null, 0);
    }

    /**
     * (29) append element Position
     */
    public JDFPosition appendPosition() throws JDFException
    {
        return (JDFPosition) appendElementN(ElementName.POSITION, 1, null);
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

}// end namespace JDF
