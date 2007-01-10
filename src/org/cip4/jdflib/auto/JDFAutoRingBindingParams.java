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
import org.cip4.jdflib.core.JDFException;
import org.cip4.jdflib.resource.JDFResource;
import org.cip4.jdflib.resource.process.postpress.JDFHoleMakingParams;
    /*
    *****************************************************************************
    class JDFAutoRingBindingParams : public JDFResource

    *****************************************************************************
    */

public abstract class JDFAutoRingBindingParams extends JDFResource
{

    private static final long serialVersionUID = 1L;

    private static AtrInfoTable[] atrInfoTable = new AtrInfoTable[11];
    static
    {
        atrInfoTable[0] = new AtrInfoTable(AttributeName.BINDERCOLOR, 0x33333333, AttributeInfo.EnumAttributeType.string, null, null);
        atrInfoTable[1] = new AtrInfoTable(AttributeName.BINDERMATERIAL, 0x33333333, AttributeInfo.EnumAttributeType.NMTOKEN, null, null);
        atrInfoTable[2] = new AtrInfoTable(AttributeName.BINDERNAME, 0x33333333, AttributeInfo.EnumAttributeType.string, null, null);
        atrInfoTable[3] = new AtrInfoTable(AttributeName.RINGDIAMETER, 0x33333333, AttributeInfo.EnumAttributeType.double_, null, null);
        atrInfoTable[4] = new AtrInfoTable(AttributeName.RINGMECHANIC, 0x33333333, AttributeInfo.EnumAttributeType.boolean_, null, null);
        atrInfoTable[5] = new AtrInfoTable(AttributeName.RINGSHAPE, 0x33333333, AttributeInfo.EnumAttributeType.NMTOKEN, null, null);
        atrInfoTable[6] = new AtrInfoTable(AttributeName.RINGSYSTEM, 0x44444443, AttributeInfo.EnumAttributeType.enumeration, EnumRingSystem.getEnum(0), null);
        atrInfoTable[7] = new AtrInfoTable(AttributeName.RIVETSEXPOSED, 0x33333333, AttributeInfo.EnumAttributeType.boolean_, null, null);
        atrInfoTable[8] = new AtrInfoTable(AttributeName.SPINECOLOR, 0x33333333, AttributeInfo.EnumAttributeType.string, null, null);
        atrInfoTable[9] = new AtrInfoTable(AttributeName.SPINEWIDTH, 0x33333333, AttributeInfo.EnumAttributeType.double_, null, null);
        atrInfoTable[10] = new AtrInfoTable(AttributeName.VIEWBINDER, 0x33333333, AttributeInfo.EnumAttributeType.NMTOKEN, null, null);
    }
    
    protected AttributeInfo getTheAttributeInfo()
    {
        return super.getTheAttributeInfo().updateReplace(atrInfoTable);
    }


    private static ElemInfoTable[] elemInfoTable = new ElemInfoTable[1];
    static
    {
        elemInfoTable[0] = new ElemInfoTable(ElementName.HOLEMAKINGPARAMS, 0x66666611);
    }
    
    protected ElementInfo getTheElementInfo()
    {
        return super.getTheElementInfo().updateReplace(elemInfoTable);
    }



    /**
     * Constructor for JDFAutoRingBindingParams
     * @param myOwnerDocument
     * @param qualifiedName
     */
    protected JDFAutoRingBindingParams(
        CoreDocumentImpl myOwnerDocument,
        String qualifiedName)
    {
        super(myOwnerDocument, qualifiedName);
    }

    /**
     * Constructor for JDFAutoRingBindingParams
     * @param myOwnerDocument
     * @param myNamespaceURI
     * @param qualifiedName
     */
    protected JDFAutoRingBindingParams(
        CoreDocumentImpl myOwnerDocument,
        String myNamespaceURI,
        String qualifiedName)
    {
        super(myOwnerDocument, myNamespaceURI, qualifiedName);
    }

    /**
     * Constructor for JDFAutoRingBindingParams
     * @param myOwnerDocument
     * @param myNamespaceURI
     * @param qualifiedName
     * @param myLocalName
     */
    protected JDFAutoRingBindingParams(
        CoreDocumentImpl myOwnerDocument,
        String myNamespaceURI,
        String qualifiedName,
        String myLocalName)
    {
        super(myOwnerDocument, myNamespaceURI, qualifiedName, myLocalName);
    }


    public String toString()
    {
        return " JDFAutoRingBindingParams[  --> " + super.toString() + " ]";
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
        * Enumeration strings for RingSystem
        */

        public static class EnumRingSystem extends ValuedEnum
        {
            private static final long serialVersionUID = 1L;
            private static int m_startValue = 0;

            private EnumRingSystem(String name)
            {
                super(name, m_startValue++);
            }

            public static EnumRingSystem getEnum(String enumName)
            {
                return (EnumRingSystem) getEnum(EnumRingSystem.class, enumName);
            }

            public static EnumRingSystem getEnum(int enumValue)
            {
                return (EnumRingSystem) getEnum(EnumRingSystem.class, enumValue);
            }

            public static Map getEnumMap()
            {
                return getEnumMap(EnumRingSystem.class);
            }

            public static List getEnumList()
            {
                return getEnumList(EnumRingSystem.class);
            }

            public static Iterator iterator()
            {
                return iterator(EnumRingSystem.class);
            }

            public static final EnumRingSystem         RingSystem_2HoleEuro = new EnumRingSystem("2HoleEuro");
            public static final EnumRingSystem         RingSystem_3HoleUS = new EnumRingSystem("3HoleUS");
            public static final EnumRingSystem         RingSystem_4HoleEuro = new EnumRingSystem("4HoleEuro");
        }      



/* ************************************************************************
 * Attribute getter / setter
 * ************************************************************************
 */
        
        /* ---------------------------------------------------------------------
        Methods for Attribute BinderColor
        --------------------------------------------------------------------- */
        /**
          * (13) set attribute BinderColor
          * @param value: the value to set the attribute to
          */
        public void setBinderColor(EnumNamedColor value)
        {
            setAttribute(AttributeName.BINDERCOLOR, value.getName(), null);
        }



        /**
          * (19) get EnumNamedColor attribute BinderColor
          * @return EnumNamedColor the value of the attribute
          */
        public EnumNamedColor getBinderColor()
        {
            String strAttrName = "";
            EnumNamedColor nPlaceHolder = null;
            strAttrName = getAttribute(AttributeName.BINDERCOLOR, null, JDFConstants.EMPTYSTRING);
            nPlaceHolder = EnumNamedColor.getEnum(strAttrName);
            return nPlaceHolder;
        }



        
        /* ---------------------------------------------------------------------
        Methods for Attribute BinderMaterial
        --------------------------------------------------------------------- */
        /**
          * (36) set attribute BinderMaterial
          * @param value: the value to set the attribute to
          */
        public void setBinderMaterial(String value)
        {
            setAttribute(AttributeName.BINDERMATERIAL, value, null);
        }



        /**
          * (23) get String attribute BinderMaterial
          * @return the value of the attribute
          */
        public String getBinderMaterial()
        {
            return getAttribute(AttributeName.BINDERMATERIAL, null, JDFConstants.EMPTYSTRING);
        }



        
        /* ---------------------------------------------------------------------
        Methods for Attribute BinderName
        --------------------------------------------------------------------- */
        /**
          * (36) set attribute BinderName
          * @param value: the value to set the attribute to
          */
        public void setBinderName(String value)
        {
            setAttribute(AttributeName.BINDERNAME, value, null);
        }



        /**
          * (23) get String attribute BinderName
          * @return the value of the attribute
          */
        public String getBinderName()
        {
            return getAttribute(AttributeName.BINDERNAME, null, JDFConstants.EMPTYSTRING);
        }



        
        /* ---------------------------------------------------------------------
        Methods for Attribute RingDiameter
        --------------------------------------------------------------------- */
        /**
          * (36) set attribute RingDiameter
          * @param value: the value to set the attribute to
          */
        public void setRingDiameter(double value)
        {
            setAttribute(AttributeName.RINGDIAMETER, value, null);
        }



        /**
          * (17) get double attribute RingDiameter
          * @return double the value of the attribute
          */
        public double getRingDiameter()
        {
            return getRealAttribute(AttributeName.RINGDIAMETER, null, 0.0);
        }



        
        /* ---------------------------------------------------------------------
        Methods for Attribute RingMechanic
        --------------------------------------------------------------------- */
        /**
          * (36) set attribute RingMechanic
          * @param value: the value to set the attribute to
          */
        public void setRingMechanic(boolean value)
        {
            setAttribute(AttributeName.RINGMECHANIC, value, null);
        }



        /**
          * (18) get boolean attribute RingMechanic
          * @return boolean the value of the attribute
          */
        public boolean getRingMechanic()
        {
            return getBoolAttribute(AttributeName.RINGMECHANIC, null, false);
        }



        
        /* ---------------------------------------------------------------------
        Methods for Attribute RingShape
        --------------------------------------------------------------------- */
        /**
          * (36) set attribute RingShape
          * @param value: the value to set the attribute to
          */
        public void setRingShape(String value)
        {
            setAttribute(AttributeName.RINGSHAPE, value, null);
        }



        /**
          * (23) get String attribute RingShape
          * @return the value of the attribute
          */
        public String getRingShape()
        {
            return getAttribute(AttributeName.RINGSHAPE, null, JDFConstants.EMPTYSTRING);
        }



        
        /* ---------------------------------------------------------------------
        Methods for Attribute RingSystem
        --------------------------------------------------------------------- */
        /**
          * (5) set attribute RingSystem
          * @param enumVar: the enumVar to set the attribute to
          */
        public void setRingSystem(EnumRingSystem enumVar)
        {
            setAttribute(AttributeName.RINGSYSTEM, enumVar.getName(), null);
        }



        /**
          * (9) get attribute RingSystem
          * @return the value of the attribute
          */
        public EnumRingSystem getRingSystem()
        {
            return EnumRingSystem.getEnum(getAttribute(AttributeName.RINGSYSTEM, null, null));
        }



        
        /* ---------------------------------------------------------------------
        Methods for Attribute RivetsExposed
        --------------------------------------------------------------------- */
        /**
          * (36) set attribute RivetsExposed
          * @param value: the value to set the attribute to
          */
        public void setRivetsExposed(boolean value)
        {
            setAttribute(AttributeName.RIVETSEXPOSED, value, null);
        }



        /**
          * (18) get boolean attribute RivetsExposed
          * @return boolean the value of the attribute
          */
        public boolean getRivetsExposed()
        {
            return getBoolAttribute(AttributeName.RIVETSEXPOSED, null, false);
        }



        
        /* ---------------------------------------------------------------------
        Methods for Attribute SpineColor
        --------------------------------------------------------------------- */
        /**
          * (13) set attribute SpineColor
          * @param value: the value to set the attribute to
          */
        public void setSpineColor(EnumNamedColor value)
        {
            setAttribute(AttributeName.SPINECOLOR, value.getName(), null);
        }



        /**
          * (19) get EnumNamedColor attribute SpineColor
          * @return EnumNamedColor the value of the attribute
          */
        public EnumNamedColor getSpineColor()
        {
            String strAttrName = "";
            EnumNamedColor nPlaceHolder = null;
            strAttrName = getAttribute(AttributeName.SPINECOLOR, null, JDFConstants.EMPTYSTRING);
            nPlaceHolder = EnumNamedColor.getEnum(strAttrName);
            return nPlaceHolder;
        }



        
        /* ---------------------------------------------------------------------
        Methods for Attribute SpineWidth
        --------------------------------------------------------------------- */
        /**
          * (36) set attribute SpineWidth
          * @param value: the value to set the attribute to
          */
        public void setSpineWidth(double value)
        {
            setAttribute(AttributeName.SPINEWIDTH, value, null);
        }



        /**
          * (17) get double attribute SpineWidth
          * @return double the value of the attribute
          */
        public double getSpineWidth()
        {
            return getRealAttribute(AttributeName.SPINEWIDTH, null, 0.0);
        }



        
        /* ---------------------------------------------------------------------
        Methods for Attribute ViewBinder
        --------------------------------------------------------------------- */
        /**
          * (36) set attribute ViewBinder
          * @param value: the value to set the attribute to
          */
        public void setViewBinder(String value)
        {
            setAttribute(AttributeName.VIEWBINDER, value, null);
        }



        /**
          * (23) get String attribute ViewBinder
          * @return the value of the attribute
          */
        public String getViewBinder()
        {
            return getAttribute(AttributeName.VIEWBINDER, null, JDFConstants.EMPTYSTRING);
        }



/* ***********************************************************************
 * Element getter / setter
 * ***********************************************************************
 */

    /**
     * (24) const get element HoleMakingParams
     * @return JDFHoleMakingParams the element
     */
    public JDFHoleMakingParams getHoleMakingParams()
    {
        return (JDFHoleMakingParams) getElement(ElementName.HOLEMAKINGPARAMS, null, 0);
    }



    /** (25) getCreateHoleMakingParams
     * 
     * @return JDFHoleMakingParams the element
     */
    public JDFHoleMakingParams getCreateHoleMakingParams()
    {
        return (JDFHoleMakingParams) getCreateElement_KElement(ElementName.HOLEMAKINGPARAMS, null, 0);
    }





    /**
     * (29) append elementHoleMakingParams
     */
    public JDFHoleMakingParams appendHoleMakingParams() throws JDFException
    {
        return (JDFHoleMakingParams) appendElementN(ElementName.HOLEMAKINGPARAMS, 1, null);
    }
    /**
      * (31) create inter-resource link to refTarget
      * @param refTarget the element that is referenced
      */
    public void refHoleMakingParams(JDFHoleMakingParams refTarget)
    {
        refElement(refTarget);
    }
}// end namespace JDF
