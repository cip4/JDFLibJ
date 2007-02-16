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
import org.cip4.jdflib.core.JDFAudit;
import org.cip4.jdflib.core.JDFException;
import org.cip4.jdflib.resource.process.JDFMISDetails;

public abstract class JDFAutoResourceAudit extends JDFAudit
{

    private static final long serialVersionUID = 1L;

    private static AtrInfoTable[] atrInfoTable = new AtrInfoTable[2];
    static
    {
        atrInfoTable[0] = new AtrInfoTable(AttributeName.CONTENTSMODIFIED, 0x33333333, AttributeInfo.EnumAttributeType.boolean_, null, null);
        atrInfoTable[1] = new AtrInfoTable(AttributeName.REASON, 0x33333331, AttributeInfo.EnumAttributeType.enumeration, EnumReason.getEnum(0), "ProcessResult");
    }
    
    protected AttributeInfo getTheAttributeInfo()
    {
        return super.getTheAttributeInfo().updateReplace(atrInfoTable);
    }


    private static ElemInfoTable[] elemInfoTable = new ElemInfoTable[1];
    static
    {
        elemInfoTable[0] = new ElemInfoTable(ElementName.MISDETAILS, 0x66666111);
    }
    
    protected ElementInfo getTheElementInfo()
    {
        return super.getTheElementInfo().updateReplace(elemInfoTable);
    }



    /**
     * Constructor for JDFAutoResourceAudit
     * @param myOwnerDocument
     * @param qualifiedName
     */
    protected JDFAutoResourceAudit(
        CoreDocumentImpl myOwnerDocument,
        String qualifiedName)
    {
        super(myOwnerDocument, qualifiedName);
    }

    /**
     * Constructor for JDFAutoResourceAudit
     * @param myOwnerDocument
     * @param myNamespaceURI
     * @param qualifiedName
     */
    protected JDFAutoResourceAudit(
        CoreDocumentImpl myOwnerDocument,
        String myNamespaceURI,
        String qualifiedName)
    {
        super(myOwnerDocument, myNamespaceURI, qualifiedName);
    }

    /**
     * Constructor for JDFAutoResourceAudit
     * @param myOwnerDocument
     * @param myNamespaceURI
     * @param qualifiedName
     * @param myLocalName
     */
    protected JDFAutoResourceAudit(
        CoreDocumentImpl myOwnerDocument,
        String myNamespaceURI,
        String qualifiedName,
        String myLocalName)
    {
        super(myOwnerDocument, myNamespaceURI, qualifiedName, myLocalName);
    }


    public String toString()
    {
        return " JDFAutoResourceAudit[  --> " + super.toString() + " ]";
    }


        /**
        * Enumeration strings for Reason
        */

        public static class EnumReason extends ValuedEnum
        {
            private static final long serialVersionUID = 1L;
            private static int m_startValue = 0;

            private EnumReason(String name)
            {
                super(name, m_startValue++);
            }

            public static EnumReason getEnum(String enumName)
            {
                return (EnumReason) getEnum(EnumReason.class, enumName);
            }

            public static EnumReason getEnum(int enumValue)
            {
                return (EnumReason) getEnum(EnumReason.class, enumValue);
            }

            public static Map getEnumMap()
            {
                return getEnumMap(EnumReason.class);
            }

            public static List getEnumList()
            {
                return getEnumList(EnumReason.class);
            }

            public static Iterator iterator()
            {
                return iterator(EnumReason.class);
            }

            public static final EnumReason OperatorInput = new EnumReason("OperatorInput");
            public static final EnumReason PlanChange = new EnumReason("PlanChange");
            public static final EnumReason ProcessResult = new EnumReason("ProcessResult");
        }      



/* ************************************************************************
 * Attribute getter / setter
 * ************************************************************************
 */
        
        /* ---------------------------------------------------------------------
        Methods for Attribute ContentsModified
        --------------------------------------------------------------------- */
        /**
          * (36) set attribute ContentsModified
          * @param value: the value to set the attribute to
          */
        public void setContentsModified(boolean value)
        {
            setAttribute(AttributeName.CONTENTSMODIFIED, value, null);
        }



        /**
          * (18) get boolean attribute ContentsModified
          * @return boolean the value of the attribute
          */
        public boolean getContentsModified()
        {
            return getBoolAttribute(AttributeName.CONTENTSMODIFIED, null, false);
        }



        
        /* ---------------------------------------------------------------------
        Methods for Attribute Reason
        --------------------------------------------------------------------- */
        /**
          * (5) set attribute Reason
          * @param enumVar: the enumVar to set the attribute to
          */
        public void setReason(EnumReason enumVar)
        {
            setAttribute(AttributeName.REASON, enumVar.getName(), null);
        }



        /**
          * (9) get attribute Reason
          * @return the value of the attribute
          */
        public EnumReason getReason()
        {
            return EnumReason.getEnum(getAttribute(AttributeName.REASON, null, "ProcessResult"));
        }



/* ***********************************************************************
 * Element getter / setter
 * ***********************************************************************
 */

    /**
     * (24) const get element MISDetails
     * @return JDFMISDetails the element
     */
    public JDFMISDetails getMISDetails()
    {
        return (JDFMISDetails) getElement(ElementName.MISDETAILS, null, 0);
    }



    /** (25) getCreateMISDetails
     * 
     * @return JDFMISDetails the element
     */
    public JDFMISDetails getCreateMISDetails()
    {
        return (JDFMISDetails) getCreateElement_KElement(ElementName.MISDETAILS, null, 0);
    }





    /**
     * (29) append elementMISDetails
     */
    public JDFMISDetails appendMISDetails() throws JDFException
    {
        return (JDFMISDetails) appendElementN(ElementName.MISDETAILS, 1, null);
    }
}// end namespace JDF
