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
import org.cip4.jdflib.core.JDFConstants;
import org.cip4.jdflib.core.JDFElement;
    /*
    *****************************************************************************
    class JDFAutoMISDetails : public JDFElement

    *****************************************************************************
    */

public abstract class JDFAutoMISDetails extends JDFElement
{

    private static final long serialVersionUID = 1L;

    private static AtrInfoTable[] atrInfoTable = new AtrInfoTable[4];
    static
    {
        atrInfoTable[0] = new AtrInfoTable(AttributeName.COSTTYPE, 0x33333311, AttributeInfo.EnumAttributeType.enumeration, EnumCostType.getEnum(0), null);
        atrInfoTable[1] = new AtrInfoTable(AttributeName.DEVICEOPERATIONMODE, 0x33333311, AttributeInfo.EnumAttributeType.enumeration, EnumDeviceOperationMode.getEnum(0), null);
        atrInfoTable[2] = new AtrInfoTable(AttributeName.WORKTYPE, 0x33333311, AttributeInfo.EnumAttributeType.enumeration, EnumWorkType.getEnum(0), null);
        atrInfoTable[3] = new AtrInfoTable(AttributeName.WORKTYPEDETAILS, 0x33333311, AttributeInfo.EnumAttributeType.string, null, null);
    }
    
    protected AttributeInfo getTheAttributeInfo()
    {
        return super.getTheAttributeInfo().updateReplace(atrInfoTable);
    }



    /**
     * Constructor for JDFAutoMISDetails
     * @param myOwnerDocument
     * @param qualifiedName
     */
    protected JDFAutoMISDetails(
        CoreDocumentImpl myOwnerDocument,
        String qualifiedName)
    {
        super(myOwnerDocument, qualifiedName);
    }

    /**
     * Constructor for JDFAutoMISDetails
     * @param myOwnerDocument
     * @param myNamespaceURI
     * @param qualifiedName
     */
    protected JDFAutoMISDetails(
        CoreDocumentImpl myOwnerDocument,
        String myNamespaceURI,
        String qualifiedName)
    {
        super(myOwnerDocument, myNamespaceURI, qualifiedName);
    }

    /**
     * Constructor for JDFAutoMISDetails
     * @param myOwnerDocument
     * @param myNamespaceURI
     * @param qualifiedName
     * @param myLocalName
     */
    protected JDFAutoMISDetails(
        CoreDocumentImpl myOwnerDocument,
        String myNamespaceURI,
        String qualifiedName,
        String myLocalName)
    {
        super(myOwnerDocument, myNamespaceURI, qualifiedName, myLocalName);
    }


    public String toString()
    {
        return " JDFAutoMISDetails[  --> " + super.toString() + " ]";
    }


        /**
        * Enumeration strings for CostType
        */

        public static class EnumCostType extends ValuedEnum
        {
            private static final long serialVersionUID = 1L;
            private static int m_startValue = 0;

            private EnumCostType(String name)
            {
                super(name, m_startValue++);
            }

            public static EnumCostType getEnum(String enumName)
            {
                return (EnumCostType) getEnum(EnumCostType.class, enumName);
            }

            public static EnumCostType getEnum(int enumValue)
            {
                return (EnumCostType) getEnum(EnumCostType.class, enumValue);
            }

            public static Map getEnumMap()
            {
                return getEnumMap(EnumCostType.class);
            }

            public static List getEnumList()
            {
                return getEnumList(EnumCostType.class);
            }

            public static Iterator iterator()
            {
                return iterator(EnumCostType.class);
            }

            public static final EnumCostType Chargeable = new EnumCostType("Chargeable");
            public static final EnumCostType NonChargeable = new EnumCostType("NonChargeable");
        }      



        /**
        * Enumeration strings for DeviceOperationMode
        */

        public static class EnumDeviceOperationMode extends ValuedEnum
        {
            private static final long serialVersionUID = 1L;
            private static int m_startValue = 0;

            private EnumDeviceOperationMode(String name)
            {
                super(name, m_startValue++);
            }

            public static EnumDeviceOperationMode getEnum(String enumName)
            {
                return (EnumDeviceOperationMode) getEnum(EnumDeviceOperationMode.class, enumName);
            }

            public static EnumDeviceOperationMode getEnum(int enumValue)
            {
                return (EnumDeviceOperationMode) getEnum(EnumDeviceOperationMode.class, enumValue);
            }

            public static Map getEnumMap()
            {
                return getEnumMap(EnumDeviceOperationMode.class);
            }

            public static List getEnumList()
            {
                return getEnumList(EnumDeviceOperationMode.class);
            }

            public static Iterator iterator()
            {
                return iterator(EnumDeviceOperationMode.class);
            }

            public static final EnumDeviceOperationMode Productive = new EnumDeviceOperationMode("Productive");
            public static final EnumDeviceOperationMode NonProductive = new EnumDeviceOperationMode("NonProductive");
            public static final EnumDeviceOperationMode Maintenance = new EnumDeviceOperationMode("Maintenance");
        }      



        /**
        * Enumeration strings for WorkType
        */

        public static class EnumWorkType extends ValuedEnum
        {
            private static final long serialVersionUID = 1L;
            private static int m_startValue = 0;

            private EnumWorkType(String name)
            {
                super(name, m_startValue++);
            }

            public static EnumWorkType getEnum(String enumName)
            {
                return (EnumWorkType) getEnum(EnumWorkType.class, enumName);
            }

            public static EnumWorkType getEnum(int enumValue)
            {
                return (EnumWorkType) getEnum(EnumWorkType.class, enumValue);
            }

            public static Map getEnumMap()
            {
                return getEnumMap(EnumWorkType.class);
            }

            public static List getEnumList()
            {
                return getEnumList(EnumWorkType.class);
            }

            public static Iterator iterator()
            {
                return iterator(EnumWorkType.class);
            }

            public static final EnumWorkType Original = new EnumWorkType("Original");
            public static final EnumWorkType Alteration = new EnumWorkType("Alteration");
            public static final EnumWorkType Rework = new EnumWorkType("Rework");
        }      



/* ************************************************************************
 * Attribute getter / setter
 * ************************************************************************
 */
        
        /* ---------------------------------------------------------------------
        Methods for Attribute CostType
        --------------------------------------------------------------------- */
        /**
          * (5) set attribute CostType
          * @param enumVar: the enumVar to set the attribute to
          */
        public void setCostType(EnumCostType enumVar)
        {
            setAttribute(AttributeName.COSTTYPE, enumVar.getName(), null);
        }

        /**
          * (9) get attribute CostType
          * @return the value of the attribute
          */
        public EnumCostType getCostType()
        {
            return EnumCostType.getEnum(getAttribute(AttributeName.COSTTYPE, null, null));
        }

        
        /* ---------------------------------------------------------------------
        Methods for Attribute DeviceOperationMode
        --------------------------------------------------------------------- */
        /**
          * (5) set attribute DeviceOperationMode
          * @param enumVar: the enumVar to set the attribute to
          */
        public void setDeviceOperationMode(EnumDeviceOperationMode enumVar)
        {
            setAttribute(AttributeName.DEVICEOPERATIONMODE, enumVar.getName(), null);
        }

        /**
          * (9) get attribute DeviceOperationMode
          * @return the value of the attribute
          */
        public EnumDeviceOperationMode getDeviceOperationMode()
        {
            return EnumDeviceOperationMode.getEnum(getAttribute(AttributeName.DEVICEOPERATIONMODE, null, null));
        }

        
        /* ---------------------------------------------------------------------
        Methods for Attribute WorkType
        --------------------------------------------------------------------- */
        /**
          * (5) set attribute WorkType
          * @param enumVar: the enumVar to set the attribute to
          */
        public void setWorkType(EnumWorkType enumVar)
        {
            setAttribute(AttributeName.WORKTYPE, enumVar.getName(), null);
        }

        /**
          * (9) get attribute WorkType
          * @return the value of the attribute
          */
        public EnumWorkType getWorkType()
        {
            return EnumWorkType.getEnum(getAttribute(AttributeName.WORKTYPE, null, null));
        }

        
        /* ---------------------------------------------------------------------
        Methods for Attribute WorkTypeDetails
        --------------------------------------------------------------------- */
        /**
          * (36) set attribute WorkTypeDetails
          * @param value: the value to set the attribute to
          */
        public void setWorkTypeDetails(String value)
        {
            setAttribute(AttributeName.WORKTYPEDETAILS, value, null);
        }

        /**
          * (23) get String attribute WorkTypeDetails
          * @return the value of the attribute
          */
        public String getWorkTypeDetails()
        {
            return getAttribute(AttributeName.WORKTYPEDETAILS, null, JDFConstants.EMPTYSTRING);
        }

}// end namespace JDF
