/*
 * The CIP4 Software License, Version 1.0
 *
 *
 * Copyright (c) 2001-2012 The International Cooperation for the Integration of
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
import org.cip4.jdflib.core.JDFCoreConstants;
import org.cip4.jdflib.core.JDFElement;
import org.cip4.jdflib.core.VElement;
import org.cip4.jdflib.resource.JDFPart;
    /**
    *****************************************************************************
    class JDFAutoAuthenticationCmdParams : public JDFElement

    *****************************************************************************
    */

public abstract class JDFAutoAuthenticationCmdParams extends JDFElement
{

    private static final long serialVersionUID = 1L;

    private static AtrInfoTable[] atrInfoTable = new AtrInfoTable[4];
    static
    {
        atrInfoTable[0] = new AtrInfoTable(AttributeName.AUTHENTICATIONTYPE, 0x33333333, AttributeInfo.EnumAttributeType.enumeration, EnumAuthenticationType.getEnum(0), null);
        atrInfoTable[1] = new AtrInfoTable(AttributeName.REASON, 0x33333333, AttributeInfo.EnumAttributeType.enumeration, EnumReason.getEnum(0), null);
        atrInfoTable[2] = new AtrInfoTable(AttributeName.REASONDETAILS, 0x33333333, AttributeInfo.EnumAttributeType.string, null, null);
        atrInfoTable[3] = new AtrInfoTable(AttributeName.URL, 0x33333333, AttributeInfo.EnumAttributeType.URL, null, null);
    }
    
    protected AttributeInfo getTheAttributeInfo()
    {
        return super.getTheAttributeInfo().updateReplace(atrInfoTable);
    }


    private static ElemInfoTable[] elemInfoTable = new ElemInfoTable[2];
    static
    {
        elemInfoTable[0] = new ElemInfoTable(ElementName.PART, 0x33333333);
        elemInfoTable[1] = new ElemInfoTable(ElementName.CERTIFICATE, 0x33333333);
    }
    
    protected ElementInfo getTheElementInfo()
    {
        return super.getTheElementInfo().updateReplace(elemInfoTable);
    }



    /**
     * Constructor for JDFAutoAuthenticationCmdParams
     * @param myOwnerDocument
     * @param qualifiedName
     */
    protected JDFAutoAuthenticationCmdParams(
        CoreDocumentImpl myOwnerDocument,
        String qualifiedName)
    {
        super(myOwnerDocument, qualifiedName);
    }

    /**
     * Constructor for JDFAutoAuthenticationCmdParams
     * @param myOwnerDocument
     * @param myNamespaceURI
     * @param qualifiedName
     */
    protected JDFAutoAuthenticationCmdParams(
        CoreDocumentImpl myOwnerDocument,
        String myNamespaceURI,
        String qualifiedName)
    {
        super(myOwnerDocument, myNamespaceURI, qualifiedName);
    }

    /**
     * Constructor for JDFAutoAuthenticationCmdParams
     * @param myOwnerDocument
     * @param myNamespaceURI
     * @param qualifiedName
     * @param myLocalName
     */
    protected JDFAutoAuthenticationCmdParams(
        CoreDocumentImpl myOwnerDocument,
        String myNamespaceURI,
        String qualifiedName,
        String myLocalName)
    {
        super(myOwnerDocument, myNamespaceURI, qualifiedName, myLocalName);
    }


    /**
     * @return  the string representation
     */
    @Override
    public String toString()
    {
        return " JDFAutoAuthenticationCmdParams[  --> " + super.toString() + " ]";
    }


        /**
        * Enumeration strings for AuthenticationType
        */

        public static class EnumAuthenticationType extends ValuedEnum
        {
            private static final long serialVersionUID = 1L;
            private static int m_startValue = 0;

            private EnumAuthenticationType(String name)
            {
                super(name, m_startValue++);
            }

    /**
     * @param enumName the string to convert
     * @return the enum
     */
            public static EnumAuthenticationType getEnum(String enumName)
            {
                return (EnumAuthenticationType) getEnum(EnumAuthenticationType.class, enumName);
            }

    /**
     * @param enumValue the integer to convert
     * @return the enum
     */
            public static EnumAuthenticationType getEnum(int enumValue)
            {
                return (EnumAuthenticationType) getEnum(EnumAuthenticationType.class, enumValue);
            }

    /**
     * @return the map of enums
     */
            public static Map getEnumMap()
            {
                return getEnumMap(EnumAuthenticationType.class);
            }

    /**
     * @return the list of enums
     */
            public static List getEnumList()
            {
                return getEnumList(EnumAuthenticationType.class);
            }

    /**
     * @return the iterator
     */
            public static Iterator iterator()
            {
                return iterator(EnumAuthenticationType.class);
            }

            public static final EnumAuthenticationType AsClient = new EnumAuthenticationType("AsClient");
            public static final EnumAuthenticationType AsServer = new EnumAuthenticationType("AsServer");
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

    /**
     * @param enumName the string to convert
     * @return the enum
     */
            public static EnumReason getEnum(String enumName)
            {
                return (EnumReason) getEnum(EnumReason.class, enumName);
            }

    /**
     * @param enumValue the integer to convert
     * @return the enum
     */
            public static EnumReason getEnum(int enumValue)
            {
                return (EnumReason) getEnum(EnumReason.class, enumValue);
            }

    /**
     * @return the map of enums
     */
            public static Map getEnumMap()
            {
                return getEnumMap(EnumReason.class);
            }

    /**
     * @return the list of enums
     */
            public static List getEnumList()
            {
                return getEnumList(EnumReason.class);
            }

    /**
     * @return the iterator
     */
            public static Iterator iterator()
            {
                return iterator(EnumReason.class);
            }

            public static final EnumReason InitiateConnection = new EnumReason("InitiateConnection");
            public static final EnumReason ClientCertificateExpired = new EnumReason("ClientCertificateExpired");
            public static final EnumReason ServerCertificateExpired = new EnumReason("ServerCertificateExpired");
            public static final EnumReason ClientHostnameMismatch = new EnumReason("ClientHostnameMismatch");
            public static final EnumReason ServerHostnameMismatch = new EnumReason("ServerHostnameMismatch");
            public static final EnumReason ClientCertificateRevoked = new EnumReason("ClientCertificateRevoked");
            public static final EnumReason ServerCertificateRevoked = new EnumReason("ServerCertificateRevoked");
            public static final EnumReason Other = new EnumReason("Other");
        }      



/* ************************************************************************
 * Attribute getter / setter
 * ************************************************************************
 */
        
        /* ---------------------------------------------------------------------
        Methods for Attribute AuthenticationType
        --------------------------------------------------------------------- */
        /**
          * (5) set attribute AuthenticationType
          * @param enumVar the enumVar to set the attribute to
          */
        public void setAuthenticationType(EnumAuthenticationType enumVar)
        {
            setAttribute(AttributeName.AUTHENTICATIONTYPE, enumVar==null ? null : enumVar.getName(), null);
        }

        /**
          * (9) get attribute AuthenticationType
          * @return the value of the attribute
          */
        public EnumAuthenticationType getAuthenticationType()
        {
            return EnumAuthenticationType.getEnum(getAttribute(AttributeName.AUTHENTICATIONTYPE, null, null));
        }

        
        /* ---------------------------------------------------------------------
        Methods for Attribute Reason
        --------------------------------------------------------------------- */
        /**
          * (5) set attribute Reason
          * @param enumVar the enumVar to set the attribute to
          */
        public void setReason(EnumReason enumVar)
        {
            setAttribute(AttributeName.REASON, enumVar==null ? null : enumVar.getName(), null);
        }

        /**
          * (9) get attribute Reason
          * @return the value of the attribute
          */
        public EnumReason getReason()
        {
            return EnumReason.getEnum(getAttribute(AttributeName.REASON, null, null));
        }

        
        /* ---------------------------------------------------------------------
        Methods for Attribute ReasonDetails
        --------------------------------------------------------------------- */
        /**
          * (36) set attribute ReasonDetails
          * @param value the value to set the attribute to
          */
        public void setReasonDetails(String value)
        {
            setAttribute(AttributeName.REASONDETAILS, value, null);
        }

        /**
          * (23) get String attribute ReasonDetails
          * @return the value of the attribute
          */
        public String getReasonDetails()
        {
            return getAttribute(AttributeName.REASONDETAILS, null, JDFCoreConstants.EMPTYSTRING);
        }

        
        /* ---------------------------------------------------------------------
        Methods for Attribute URL
        --------------------------------------------------------------------- */
        /**
          * (36) set attribute URL
          * @param value the value to set the attribute to
          */
        public void setURL(String value)
        {
            setAttribute(AttributeName.URL, value, null);
        }

        /**
          * (23) get String attribute URL
          * @return the value of the attribute
          */
        public String getURL()
        {
            return getAttribute(AttributeName.URL, null, JDFCoreConstants.EMPTYSTRING);
        }

/* ***********************************************************************
 * Element getter / setter
 * ***********************************************************************
 */

    /** (26) getCreatePart
     * 
     * @param iSkip number of elements to skip
     * @return JDFPart the element
     */
    public JDFPart getCreatePart(int iSkip)
    {
        return (JDFPart)getCreateElement_KElement(ElementName.PART, null, iSkip);
    }

    /**
     * (27) const get element Part
     * @param iSkip number of elements to skip
     * @return JDFPart the element
     * default is getPart(0)     */
    public JDFPart getPart(int iSkip)
    {
        return (JDFPart) getElement(ElementName.PART, null, iSkip);
    }

    /**
     * Get all Part from the current element
     * 
     * @return Collection<JDFPart>, null if none are available
     */
    public Collection<JDFPart> getAllPart()
    {
        final VElement vc = getChildElementVector(ElementName.PART, null);
        if (vc == null || vc.size() == 0)
        {
            return null;
        }

        final Vector<JDFPart> v = new Vector<JDFPart>();
        for (int i = 0; i < vc.size(); i++)
        {
            v.add((JDFPart) vc.get(i));
        }

        return v;
    }

    /**
     * (30) append element Part
     * @return JDFPart the element
     */
    public JDFPart appendPart()
    {
        return (JDFPart) appendElement(ElementName.PART, null);
    }

    /** (26) getCreateCertificate
     * 
     * @param iSkip number of elements to skip
     * @return JDFElement the element
     */
    public JDFElement getCreateCertificate(int iSkip)
    {
        return (JDFElement)getCreateElement_KElement(ElementName.CERTIFICATE, null, iSkip);
    }

    /**
     * (27) const get element Certificate
     * @param iSkip number of elements to skip
     * @return JDFElement the element
     * default is getCertificate(0)     */
    public JDFElement getCertificate(int iSkip)
    {
        return (JDFElement) getElement(ElementName.CERTIFICATE, null, iSkip);
    }

    /**
     * Get all Certificate from the current element
     * 
     * @return Collection<JDFElement>, null if none are available
     */
    public Collection<JDFElement> getAllCertificate()
    {
        final VElement vc = getChildElementVector(ElementName.CERTIFICATE, null);
        if (vc == null || vc.size() == 0)
        {
            return null;
        }

        final Vector<JDFElement> v = new Vector<JDFElement>();
        for (int i = 0; i < vc.size(); i++)
        {
            v.add((JDFElement) vc.get(i));
        }

        return v;
    }

    /**
     * (30) append element Certificate
     * @return JDFElement the element
     */
    public JDFElement appendCertificate()
    {
        return (JDFElement) appendElement(ElementName.CERTIFICATE, null);
    }

}// end namespace JDF
