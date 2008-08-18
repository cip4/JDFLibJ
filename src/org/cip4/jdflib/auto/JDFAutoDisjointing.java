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
import org.cip4.jdflib.datatypes.JDFXYPair;
import org.cip4.jdflib.resource.process.JDFIdentificationField;
import org.cip4.jdflib.resource.process.JDFInsertSheet;

public abstract class JDFAutoDisjointing extends JDFElement
{

    private static final long serialVersionUID = 1L;

    private static AtrInfoTable[] atrInfoTable = new AtrInfoTable[5];
    static
    {
        atrInfoTable[0] = new AtrInfoTable(AttributeName.NUMBER, 0x33333333, AttributeInfo.EnumAttributeType.integer, null, null);
        atrInfoTable[1] = new AtrInfoTable(AttributeName.OFFSET, 0x33333333, AttributeInfo.EnumAttributeType.XYPair, null, null);
        atrInfoTable[2] = new AtrInfoTable(AttributeName.OFFSETAMOUNT, 0x33333333, AttributeInfo.EnumAttributeType.integer, null, null);
        atrInfoTable[3] = new AtrInfoTable(AttributeName.OFFSETDIRECTION, 0x33333333, AttributeInfo.EnumAttributeType.enumeration, EnumOffsetDirection.getEnum(0), null);
        atrInfoTable[4] = new AtrInfoTable(AttributeName.OVERFOLD, 0x44444443, AttributeInfo.EnumAttributeType.double_, null, null);
    }
    
    protected AttributeInfo getTheAttributeInfo()
    {
        return super.getTheAttributeInfo().updateReplace(atrInfoTable);
    }


    private static ElemInfoTable[] elemInfoTable = new ElemInfoTable[2];
    static
    {
        elemInfoTable[0] = new ElemInfoTable(ElementName.IDENTIFICATIONFIELD, 0x33333333);
        elemInfoTable[1] = new ElemInfoTable(ElementName.INSERTSHEET, 0x66666666);
    }
    
    protected ElementInfo getTheElementInfo()
    {
        return super.getTheElementInfo().updateReplace(elemInfoTable);
    }



    /**
     * Constructor for JDFAutoDisjointing
     * @param myOwnerDocument
     * @param qualifiedName
     */
    protected JDFAutoDisjointing(
        CoreDocumentImpl myOwnerDocument,
        String qualifiedName)
    {
        super(myOwnerDocument, qualifiedName);
    }

    /**
     * Constructor for JDFAutoDisjointing
     * @param myOwnerDocument
     * @param myNamespaceURI
     * @param qualifiedName
     */
    protected JDFAutoDisjointing(
        CoreDocumentImpl myOwnerDocument,
        String myNamespaceURI,
        String qualifiedName)
    {
        super(myOwnerDocument, myNamespaceURI, qualifiedName);
    }

    /**
     * Constructor for JDFAutoDisjointing
     * @param myOwnerDocument
     * @param myNamespaceURI
     * @param qualifiedName
     * @param myLocalName
     */
    protected JDFAutoDisjointing(
        CoreDocumentImpl myOwnerDocument,
        String myNamespaceURI,
        String qualifiedName,
        String myLocalName)
    {
        super(myOwnerDocument, myNamespaceURI, qualifiedName, myLocalName);
    }


    public String toString()
    {
        return " JDFAutoDisjointing[  --> " + super.toString() + " ]";
    }


        /**
        * Enumeration strings for OffsetDirection
        */

        public static class EnumOffsetDirection extends ValuedEnum
        {
            private static final long serialVersionUID = 1L;
            private static int m_startValue = 0;

            private EnumOffsetDirection(String name)
            {
                super(name, m_startValue++);
            }

            public static EnumOffsetDirection getEnum(String enumName)
            {
                return (EnumOffsetDirection) getEnum(EnumOffsetDirection.class, enumName);
            }

            public static EnumOffsetDirection getEnum(int enumValue)
            {
                return (EnumOffsetDirection) getEnum(EnumOffsetDirection.class, enumValue);
            }

            public static Map getEnumMap()
            {
                return getEnumMap(EnumOffsetDirection.class);
            }

            public static List getEnumList()
            {
                return getEnumList(EnumOffsetDirection.class);
            }

            public static Iterator iterator()
            {
                return iterator(EnumOffsetDirection.class);
            }

            public static final EnumOffsetDirection Alternate = new EnumOffsetDirection("Alternate");
            public static final EnumOffsetDirection Left = new EnumOffsetDirection("Left");
            public static final EnumOffsetDirection None = new EnumOffsetDirection("None");
            public static final EnumOffsetDirection Right = new EnumOffsetDirection("Right");
            public static final EnumOffsetDirection Straight = new EnumOffsetDirection("Straight");
            public static final EnumOffsetDirection SystemSpecified = new EnumOffsetDirection("SystemSpecified");
        }      



/* ************************************************************************
 * Attribute getter / setter
 * ************************************************************************
 */
        
        /* ---------------------------------------------------------------------
        Methods for Attribute Number
        --------------------------------------------------------------------- */
        /**
          * (36) set attribute Number
          * @param value: the value to set the attribute to
          */
        public void setNumber(int value)
        {
            setAttribute(AttributeName.NUMBER, value, null);
        }

        /**
          * (15) get int attribute Number
          * @return int the value of the attribute
          */
        public int getNumber()
        {
            return getIntAttribute(AttributeName.NUMBER, null, 0);
        }

        
        /* ---------------------------------------------------------------------
        Methods for Attribute Offset
        --------------------------------------------------------------------- */
        /**
          * (36) set attribute Offset
          * @param value: the value to set the attribute to
          */
        public void setOffset(JDFXYPair value)
        {
            setAttribute(AttributeName.OFFSET, value, null);
        }

        /**
          * (20) get JDFXYPair attribute Offset
          * @return JDFXYPair the value of the attribute, null if a the
          *         attribute value is not a valid to create a JDFXYPair
          */
        public JDFXYPair getOffset()
        {
            String strAttrName = "";
            JDFXYPair nPlaceHolder = null;
            strAttrName = getAttribute(AttributeName.OFFSET, null, JDFConstants.EMPTYSTRING);
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
        Methods for Attribute OffsetAmount
        --------------------------------------------------------------------- */
        /**
          * (36) set attribute OffsetAmount
          * @param value: the value to set the attribute to
          */
        public void setOffsetAmount(int value)
        {
            setAttribute(AttributeName.OFFSETAMOUNT, value, null);
        }

        /**
          * (15) get int attribute OffsetAmount
          * @return int the value of the attribute
          */
        public int getOffsetAmount()
        {
            return getIntAttribute(AttributeName.OFFSETAMOUNT, null, 0);
        }

        
        /* ---------------------------------------------------------------------
        Methods for Attribute OffsetDirection
        --------------------------------------------------------------------- */
        /**
          * (5) set attribute OffsetDirection
          * @param enumVar: the enumVar to set the attribute to
          */
        public void setOffsetDirection(EnumOffsetDirection enumVar)
        {
            setAttribute(AttributeName.OFFSETDIRECTION, enumVar==null ? null : enumVar.getName(), null);
        }

        /**
          * (9) get attribute OffsetDirection
          * @return the value of the attribute
          */
        public EnumOffsetDirection getOffsetDirection()
        {
            return EnumOffsetDirection.getEnum(getAttribute(AttributeName.OFFSETDIRECTION, null, null));
        }

        
        /* ---------------------------------------------------------------------
        Methods for Attribute Overfold
        --------------------------------------------------------------------- */
        /**
          * (36) set attribute Overfold
          * @param value: the value to set the attribute to
          */
        public void setOverfold(double value)
        {
            setAttribute(AttributeName.OVERFOLD, value, null);
        }

        /**
          * (17) get double attribute Overfold
          * @return double the value of the attribute
          */
        public double getOverfold()
        {
            return getRealAttribute(AttributeName.OVERFOLD, null, 0.0);
        }

/* ***********************************************************************
 * Element getter / setter
 * ***********************************************************************
 */

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
     * @return Collection<JDFIdentificationField>
     */
    public Collection<JDFIdentificationField> getAllIdentificationField()
    {
        Vector<JDFIdentificationField> v = new Vector<JDFIdentificationField>();

        JDFIdentificationField kElem = (JDFIdentificationField) getFirstChildElement(ElementName.IDENTIFICATIONFIELD, null);

        while (kElem != null)
        {
            v.add(kElem);

            kElem = (JDFIdentificationField) kElem.getNextSiblingElement(ElementName.IDENTIFICATIONFIELD, null);
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
     * (24) const get element InsertSheet
     * @return JDFInsertSheet the element
     */
    public JDFInsertSheet getInsertSheet()
    {
        return (JDFInsertSheet) getElement(ElementName.INSERTSHEET, null, 0);
    }

    /** (25) getCreateInsertSheet
     * 
     * @return JDFInsertSheet the element
     */
    public JDFInsertSheet getCreateInsertSheet()
    {
        return (JDFInsertSheet) getCreateElement_KElement(ElementName.INSERTSHEET, null, 0);
    }

    /**
     * (29) append element InsertSheet
     */
    public JDFInsertSheet appendInsertSheet() throws JDFException
    {
        return (JDFInsertSheet) appendElementN(ElementName.INSERTSHEET, 1, null);
    }

    /**
      * (31) create inter-resource link to refTarget
      * @param refTarget the element that is referenced
      */
    public void refInsertSheet(JDFInsertSheet refTarget)
    {
        refElement(refTarget);
    }

}// end namespace JDF
