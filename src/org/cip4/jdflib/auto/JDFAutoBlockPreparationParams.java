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
import org.cip4.jdflib.resource.JDFRegisterRibbon;
import org.cip4.jdflib.resource.JDFResource;
    /*
    *****************************************************************************
    class JDFAutoBlockPreparationParams : public JDFResource

    *****************************************************************************
    */

public abstract class JDFAutoBlockPreparationParams extends JDFResource
{

    private static final long serialVersionUID = 1L;

    private static AtrInfoTable[] atrInfoTable = new AtrInfoTable[3];
    static
    {
        atrInfoTable[0] = new AtrInfoTable(AttributeName.BACKING, 0x33333331, AttributeInfo.EnumAttributeType.double_, null, null);
        atrInfoTable[1] = new AtrInfoTable(AttributeName.ROUNDING, 0x33333331, AttributeInfo.EnumAttributeType.double_, null, null);
        atrInfoTable[2] = new AtrInfoTable(AttributeName.TIGHTBACKING, 0x33333331, AttributeInfo.EnumAttributeType.enumeration, EnumTightBacking.getEnum(0), null);
    }
    
    protected AttributeInfo getTheAttributeInfo()
    {
        return super.getTheAttributeInfo().updateReplace(atrInfoTable);
    }


    private static ElemInfoTable[] elemInfoTable = new ElemInfoTable[1];
    static
    {
        elemInfoTable[0] = new ElemInfoTable(ElementName.REGISTERRIBBON, 0x33333331);
    }
    
    protected ElementInfo getTheElementInfo()
    {
        return super.getTheElementInfo().updateReplace(elemInfoTable);
    }



    /**
     * Constructor for JDFAutoBlockPreparationParams
     * @param myOwnerDocument
     * @param qualifiedName
     */
    protected JDFAutoBlockPreparationParams(
        CoreDocumentImpl myOwnerDocument,
        String qualifiedName)
    {
        super(myOwnerDocument, qualifiedName);
    }

    /**
     * Constructor for JDFAutoBlockPreparationParams
     * @param myOwnerDocument
     * @param myNamespaceURI
     * @param qualifiedName
     */
    protected JDFAutoBlockPreparationParams(
        CoreDocumentImpl myOwnerDocument,
        String myNamespaceURI,
        String qualifiedName)
    {
        super(myOwnerDocument, myNamespaceURI, qualifiedName);
    }

    /**
     * Constructor for JDFAutoBlockPreparationParams
     * @param myOwnerDocument
     * @param myNamespaceURI
     * @param qualifiedName
     * @param myLocalName
     */
    protected JDFAutoBlockPreparationParams(
        CoreDocumentImpl myOwnerDocument,
        String myNamespaceURI,
        String qualifiedName,
        String myLocalName)
    {
        super(myOwnerDocument, myNamespaceURI, qualifiedName, myLocalName);
    }


    public String toString()
    {
        return " JDFAutoBlockPreparationParams[  --> " + super.toString() + " ]";
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
        * Enumeration strings for TightBacking
        */

        public static class EnumTightBacking extends ValuedEnum
        {
            private static final long serialVersionUID = 1L;
            private static int m_startValue = 0;

            private EnumTightBacking(String name)
            {
                super(name, m_startValue++);
            }

            public static EnumTightBacking getEnum(String enumName)
            {
                return (EnumTightBacking) getEnum(EnumTightBacking.class, enumName);
            }

            public static EnumTightBacking getEnum(int enumValue)
            {
                return (EnumTightBacking) getEnum(EnumTightBacking.class, enumValue);
            }

            public static Map getEnumMap()
            {
                return getEnumMap(EnumTightBacking.class);
            }

            public static List getEnumList()
            {
                return getEnumList(EnumTightBacking.class);
            }

            public static Iterator iterator()
            {
                return iterator(EnumTightBacking.class);
            }

            public static final EnumTightBacking Flat = new EnumTightBacking("Flat");
            public static final EnumTightBacking Round = new EnumTightBacking("Round");
            public static final EnumTightBacking FlatBacked = new EnumTightBacking("FlatBacked");
            public static final EnumTightBacking RoundBacked = new EnumTightBacking("RoundBacked");
        }      



/* ************************************************************************
 * Attribute getter / setter
 * ************************************************************************
 */
        
        /* ---------------------------------------------------------------------
        Methods for Attribute Backing
        --------------------------------------------------------------------- */
        /**
          * (36) set attribute Backing
          * @param value: the value to set the attribute to
          */
        public void setBacking(double value)
        {
            setAttribute(AttributeName.BACKING, value, null);
        }



        /**
          * (17) get double attribute Backing
          * @return double the value of the attribute
          */
        public double getBacking()
        {
            return getRealAttribute(AttributeName.BACKING, null, 0.0);
        }



        
        /* ---------------------------------------------------------------------
        Methods for Attribute Rounding
        --------------------------------------------------------------------- */
        /**
          * (36) set attribute Rounding
          * @param value: the value to set the attribute to
          */
        public void setRounding(double value)
        {
            setAttribute(AttributeName.ROUNDING, value, null);
        }



        /**
          * (17) get double attribute Rounding
          * @return double the value of the attribute
          */
        public double getRounding()
        {
            return getRealAttribute(AttributeName.ROUNDING, null, 0.0);
        }



        
        /* ---------------------------------------------------------------------
        Methods for Attribute TightBacking
        --------------------------------------------------------------------- */
        /**
          * (5) set attribute TightBacking
          * @param enumVar: the enumVar to set the attribute to
          */
        public void setTightBacking(EnumTightBacking enumVar)
        {
            setAttribute(AttributeName.TIGHTBACKING, enumVar.getName(), null);
        }



        /**
          * (9) get attribute TightBacking
          * @return the value of the attribute
          */
        public EnumTightBacking getTightBacking()
        {
            return EnumTightBacking.getEnum(getAttribute(AttributeName.TIGHTBACKING, null, null));
        }



/* ***********************************************************************
 * Element getter / setter
 * ***********************************************************************
 */

    /** (26) getCreateRegisterRibbon
     * 
     * @param iSkip number of elements to skip
     * @return JDFRegisterRibbon the element
     */
    public JDFRegisterRibbon getCreateRegisterRibbon(int iSkip)
    {
        return (JDFRegisterRibbon)getCreateElement_KElement(ElementName.REGISTERRIBBON, null, iSkip);
    }



    /**
     * (27) const get element RegisterRibbon
     * @param iSkip number of elements to skip
     * @return JDFRegisterRibbon the element
     * default is getRegisterRibbon(0)     */
    public JDFRegisterRibbon getRegisterRibbon(int iSkip)
    {
        return (JDFRegisterRibbon) getElement(ElementName.REGISTERRIBBON, null, iSkip);
    }



    public JDFRegisterRibbon appendRegisterRibbon()
    {
        return (JDFRegisterRibbon) appendElement(ElementName.REGISTERRIBBON, null);
    }

    /**
      * (31) create inter-resource link to refTarget
      * @param refTarget the element that is referenced
      */
    public void refRegisterRibbon(JDFRegisterRibbon refTarget)
    {
        refElement(refTarget);
    }
}// end namespace JDF
