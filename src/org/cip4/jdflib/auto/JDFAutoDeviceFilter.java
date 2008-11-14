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
import org.cip4.jdflib.core.*;                      
import org.cip4.jdflib.resource.*;

public abstract class JDFAutoDeviceFilter extends JDFElement
{

    private static final long serialVersionUID = 1L;

    private static AtrInfoTable[] atrInfoTable = new AtrInfoTable[2];
    static
    {
        atrInfoTable[0] = new AtrInfoTable(AttributeName.DEVICEDETAILS, 0x33333331, AttributeInfo.EnumAttributeType.enumeration, EnumDeviceDetails.getEnum(0), "None");
        atrInfoTable[1] = new AtrInfoTable(AttributeName.LOCALIZATION, 0x33333311, AttributeInfo.EnumAttributeType.languages, null, null);
    }
    
    @Override
	protected AttributeInfo getTheAttributeInfo()
    {
        return super.getTheAttributeInfo().updateReplace(atrInfoTable);
    }


    private static ElemInfoTable[] elemInfoTable = new ElemInfoTable[1];
    static
    {
        elemInfoTable[0] = new ElemInfoTable(ElementName.DEVICE, 0x33333333);
    }
    
    @Override
	protected ElementInfo getTheElementInfo()
    {
        return super.getTheElementInfo().updateReplace(elemInfoTable);
    }



    /**
     * Constructor for JDFAutoDeviceFilter
     * @param myOwnerDocument
     * @param qualifiedName
     */
    protected JDFAutoDeviceFilter(
        CoreDocumentImpl myOwnerDocument,
        String qualifiedName)
    {
        super(myOwnerDocument, qualifiedName);
    }

    /**
     * Constructor for JDFAutoDeviceFilter
     * @param myOwnerDocument
     * @param myNamespaceURI
     * @param qualifiedName
     */
    protected JDFAutoDeviceFilter(
        CoreDocumentImpl myOwnerDocument,
        String myNamespaceURI,
        String qualifiedName)
    {
        super(myOwnerDocument, myNamespaceURI, qualifiedName);
    }

    /**
     * Constructor for JDFAutoDeviceFilter
     * @param myOwnerDocument
     * @param myNamespaceURI
     * @param qualifiedName
     * @param myLocalName
     */
    protected JDFAutoDeviceFilter(
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
        return " JDFAutoDeviceFilter[  --> " + super.toString() + " ]";
    }


        /**
        * Enumeration strings for DeviceDetails
        */

        public static class EnumDeviceDetails extends ValuedEnum
        {
            private static final long serialVersionUID = 1L;
            private static int m_startValue = 0;

            private EnumDeviceDetails(String name)
            {
                super(name, m_startValue++);
            }

            public static EnumDeviceDetails getEnum(String enumName)
            {
                return (EnumDeviceDetails) getEnum(EnumDeviceDetails.class, enumName);
            }

            public static EnumDeviceDetails getEnum(int enumValue)
            {
                return (EnumDeviceDetails) getEnum(EnumDeviceDetails.class, enumValue);
            }

            public static Map getEnumMap()
            {
                return getEnumMap(EnumDeviceDetails.class);
            }

            public static List getEnumList()
            {
                return getEnumList(EnumDeviceDetails.class);
            }

            public static Iterator iterator()
            {
                return iterator(EnumDeviceDetails.class);
            }

            public static final EnumDeviceDetails None = new EnumDeviceDetails("None");
            public static final EnumDeviceDetails Brief = new EnumDeviceDetails("Brief");
            public static final EnumDeviceDetails Modules = new EnumDeviceDetails("Modules");
            public static final EnumDeviceDetails Details = new EnumDeviceDetails("Details");
            public static final EnumDeviceDetails NamedFeature = new EnumDeviceDetails("NamedFeature");
            public static final EnumDeviceDetails Capability = new EnumDeviceDetails("Capability");
            public static final EnumDeviceDetails Full = new EnumDeviceDetails("Full");
        }      



/* ************************************************************************
 * Attribute getter / setter
 * ************************************************************************
 */
        
        /* ---------------------------------------------------------------------
        Methods for Attribute DeviceDetails
        --------------------------------------------------------------------- */
        /**
          * (5) set attribute DeviceDetails
          * @param enumVar: the enumVar to set the attribute to
          */
        public void setDeviceDetails(EnumDeviceDetails enumVar)
        {
            setAttribute(AttributeName.DEVICEDETAILS, enumVar==null ? null : enumVar.getName(), null);
        }

        /**
          * (9) get attribute DeviceDetails
          * @return the value of the attribute
          */
        public EnumDeviceDetails getDeviceDetails()
        {
            return EnumDeviceDetails.getEnum(getAttribute(AttributeName.DEVICEDETAILS, null, "None"));
        }

        
        /* ---------------------------------------------------------------------
        Methods for Attribute Localization
        --------------------------------------------------------------------- */
        /**
          * (36) set attribute Localization
          * @param value: the value to set the attribute to
          */
        public void setLocalization(String value)
        {
            setAttribute(AttributeName.LOCALIZATION, value, null);
        }

        /**
          * (23) get String attribute Localization
          * @return the value of the attribute
          */
        public String getLocalization()
        {
            return getAttribute(AttributeName.LOCALIZATION, null, JDFConstants.EMPTYSTRING);
        }

/* ***********************************************************************
 * Element getter / setter
 * ***********************************************************************
 */

    /** (26) getCreateDevice
     * 
     * @param iSkip number of elements to skip
     * @return JDFDevice the element
     */
    public JDFDevice getCreateDevice(int iSkip)
    {
        return (JDFDevice)getCreateElement_KElement(ElementName.DEVICE, null, iSkip);
    }

    /**
     * (27) const get element Device
     * @param iSkip number of elements to skip
     * @return JDFDevice the element
     * default is getDevice(0)     */
    public JDFDevice getDevice(int iSkip)
    {
        return (JDFDevice) getElement(ElementName.DEVICE, null, iSkip);
    }

    /**
     * Get all Device from the current element
     * 
     * @return Collection<JDFDevice>
     */
    public Collection<JDFDevice> getAllDevice()
    {
        Vector<JDFDevice> v = new Vector<JDFDevice>();

        JDFDevice kElem = (JDFDevice) getFirstChildElement(ElementName.DEVICE, null);

        while (kElem != null)
        {
            v.add(kElem);

            kElem = (JDFDevice) kElem.getNextSiblingElement(ElementName.DEVICE, null);
        }

        return v;
    }

    /**
     * (30) append element Device
     */
    public JDFDevice appendDevice() throws JDFException
    {
        return (JDFDevice) appendElement(ElementName.DEVICE, null);
    }

}// end namespace JDF
