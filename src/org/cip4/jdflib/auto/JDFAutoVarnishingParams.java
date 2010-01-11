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
import org.cip4.jdflib.resource.JDFResource;

public abstract class JDFAutoVarnishingParams extends JDFResource
{

    private static final long serialVersionUID = 1L;

    private static AtrInfoTable[] atrInfoTable = new AtrInfoTable[4];
    static
    {
        atrInfoTable[0] = new AtrInfoTable(AttributeName.MODULEINDEX, 0x33331111, AttributeInfo.EnumAttributeType.integer, null, null);
        atrInfoTable[1] = new AtrInfoTable(AttributeName.ABSOLUTEWIDTH, 0x33331111, AttributeInfo.EnumAttributeType.NMTOKEN, null, null);
        atrInfoTable[2] = new AtrInfoTable(AttributeName.ANCHOR, 0x33331111, AttributeInfo.EnumAttributeType.enumeration, EnumAnchor.getEnum(0), null);
        atrInfoTable[3] = new AtrInfoTable(AttributeName.VARNISHMETHOD, 0x33331111, AttributeInfo.EnumAttributeType.enumeration, EnumVarnishMethod.getEnum(0), null);
    }
    
    @Override
	protected AttributeInfo getTheAttributeInfo()
    {
        return super.getTheAttributeInfo().updateReplace(atrInfoTable);
    }



    /**
     * Constructor for JDFAutoVarnishingParams
     * @param myOwnerDocument
     * @param qualifiedName
     */
    protected JDFAutoVarnishingParams(
        CoreDocumentImpl myOwnerDocument,
        String qualifiedName)
    {
        super(myOwnerDocument, qualifiedName);
    }

    /**
     * Constructor for JDFAutoVarnishingParams
     * @param myOwnerDocument
     * @param myNamespaceURI
     * @param qualifiedName
     */
    protected JDFAutoVarnishingParams(
        CoreDocumentImpl myOwnerDocument,
        String myNamespaceURI,
        String qualifiedName)
    {
        super(myOwnerDocument, myNamespaceURI, qualifiedName);
    }

    /**
     * Constructor for JDFAutoVarnishingParams
     * @param myOwnerDocument
     * @param myNamespaceURI
     * @param qualifiedName
     * @param myLocalName
     */
    protected JDFAutoVarnishingParams(
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
        return " JDFAutoVarnishingParams[  --> " + super.toString() + " ]";
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
        * Enumeration strings for Anchor
        */

        public static class EnumAnchor extends ValuedEnum
        {
            private static final long serialVersionUID = 1L;
            private static int m_startValue = 0;

            private EnumAnchor(String name)
            {
                super(name, m_startValue++);
            }

            public static EnumAnchor getEnum(String enumName)
            {
                return (EnumAnchor) getEnum(EnumAnchor.class, enumName);
            }

            public static EnumAnchor getEnum(int enumValue)
            {
                return (EnumAnchor) getEnum(EnumAnchor.class, enumValue);
            }

            public static Map getEnumMap()
            {
                return getEnumMap(EnumAnchor.class);
            }

            public static List getEnumList()
            {
                return getEnumList(EnumAnchor.class);
            }

            public static Iterator iterator()
            {
                return iterator(EnumAnchor.class);
            }

            public static final EnumAnchor Full = new EnumAnchor("Full");
            public static final EnumAnchor Spot = new EnumAnchor("Spot");
        }      



        /**
        * Enumeration strings for VarnishMethod
        */

        public static class EnumVarnishMethod extends ValuedEnum
        {
            private static final long serialVersionUID = 1L;
            private static int m_startValue = 0;

            private EnumVarnishMethod(String name)
            {
                super(name, m_startValue++);
            }

            public static EnumVarnishMethod getEnum(String enumName)
            {
                return (EnumVarnishMethod) getEnum(EnumVarnishMethod.class, enumName);
            }

            public static EnumVarnishMethod getEnum(int enumValue)
            {
                return (EnumVarnishMethod) getEnum(EnumVarnishMethod.class, enumValue);
            }

            public static Map getEnumMap()
            {
                return getEnumMap(EnumVarnishMethod.class);
            }

            public static List getEnumList()
            {
                return getEnumList(EnumVarnishMethod.class);
            }

            public static Iterator iterator()
            {
                return iterator(EnumVarnishMethod.class);
            }

            public static final EnumVarnishMethod Blanket = new EnumVarnishMethod("Blanket");
            public static final EnumVarnishMethod Plate = new EnumVarnishMethod("Plate");
            public static final EnumVarnishMethod Method = new EnumVarnishMethod("Method");
        }      



/* ************************************************************************
 * Attribute getter / setter
 * ************************************************************************
 */
        
        /* ---------------------------------------------------------------------
        Methods for Attribute ModuleIndex
        --------------------------------------------------------------------- */
        /**
          * (36) set attribute ModuleIndex
          * @param value: the value to set the attribute to
          */
        public void setModuleIndex(int value)
        {
            setAttribute(AttributeName.MODULEINDEX, value, null);
        }

        /**
          * (15) get int attribute ModuleIndex
          * @return int the value of the attribute
          */
        public int getModuleIndex()
        {
            return getIntAttribute(AttributeName.MODULEINDEX, null, 0);
        }

        
        /* ---------------------------------------------------------------------
        Methods for Attribute AbsoluteWidth
        --------------------------------------------------------------------- */
        /**
          * (36) set attribute AbsoluteWidth
          * @param value: the value to set the attribute to
          */
        public void setAbsoluteWidth(String value)
        {
            setAttribute(AttributeName.ABSOLUTEWIDTH, value, null);
        }

        /**
          * (23) get String attribute AbsoluteWidth
          * @return the value of the attribute
          */
        public String getAbsoluteWidth()
        {
            return getAttribute(AttributeName.ABSOLUTEWIDTH, null, JDFConstants.EMPTYSTRING);
        }

        
        /* ---------------------------------------------------------------------
        Methods for Attribute Anchor
        --------------------------------------------------------------------- */
        /**
          * (5) set attribute Anchor
          * @param enumVar: the enumVar to set the attribute to
          */
        public void setAnchor(EnumAnchor enumVar)
        {
            setAttribute(AttributeName.ANCHOR, enumVar==null ? null : enumVar.getName(), null);
        }

        /**
          * (9) get attribute Anchor
          * @return the value of the attribute
          */
        public EnumAnchor getAnchor()
        {
            return EnumAnchor.getEnum(getAttribute(AttributeName.ANCHOR, null, null));
        }

        
        /* ---------------------------------------------------------------------
        Methods for Attribute VarnishMethod
        --------------------------------------------------------------------- */
        /**
          * (5) set attribute VarnishMethod
          * @param enumVar: the enumVar to set the attribute to
          */
        public void setVarnishMethod(EnumVarnishMethod enumVar)
        {
            setAttribute(AttributeName.VARNISHMETHOD, enumVar==null ? null : enumVar.getName(), null);
        }

        /**
          * (9) get attribute VarnishMethod
          * @return the value of the attribute
          */
        public EnumVarnishMethod getVarnishMethod()
        {
            return EnumVarnishMethod.getEnum(getAttribute(AttributeName.VARNISHMETHOD, null, null));
        }

}// end namespace JDF
