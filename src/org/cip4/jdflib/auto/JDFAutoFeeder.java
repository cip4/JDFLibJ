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
import org.cip4.jdflib.datatypes.JDFIntegerList;
import org.cip4.jdflib.resource.process.JDFComponent;
import org.cip4.jdflib.resource.process.JDFFeederQualityParams;
import org.cip4.jdflib.resource.process.JDFMedia;
    /*
    *****************************************************************************
    class JDFAutoFeeder : public JDFElement

    *****************************************************************************
    */

public abstract class JDFAutoFeeder extends JDFElement
{

    private static final long serialVersionUID = 1L;

    private static AtrInfoTable[] atrInfoTable = new AtrInfoTable[6];
    static
    {
        atrInfoTable[0] = new AtrInfoTable(AttributeName.ALTERNATEPOSITIONS, 0x33333311, AttributeInfo.EnumAttributeType.IntegerList, null, null);
        atrInfoTable[1] = new AtrInfoTable(AttributeName.POSITION, 0x33333311, AttributeInfo.EnumAttributeType.integer, null, null);
        atrInfoTable[2] = new AtrInfoTable(AttributeName.FEEDERSYNCHRONIZATION, 0x33333311, AttributeInfo.EnumAttributeType.enumeration, EnumFeederSynchronization.getEnum(0), "Primary");
        atrInfoTable[3] = new AtrInfoTable(AttributeName.FEEDERTYPE, 0x33333311, AttributeInfo.EnumAttributeType.NMTOKEN, null, null);
        atrInfoTable[4] = new AtrInfoTable(AttributeName.LOADING, 0x33333311, AttributeInfo.EnumAttributeType.NMTOKEN, null, null);
        atrInfoTable[5] = new AtrInfoTable(AttributeName.OPENING, 0x33333311, AttributeInfo.EnumAttributeType.enumeration, EnumOpening.getEnum(0), "None");
    }
    
    protected AttributeInfo getTheAttributeInfo()
    {
        return super.getTheAttributeInfo().updateReplace(atrInfoTable);
    }


    private static ElemInfoTable[] elemInfoTable = new ElemInfoTable[3];
    static
    {
        elemInfoTable[0] = new ElemInfoTable(ElementName.COMPONENT, 0x66666611);
        elemInfoTable[1] = new ElemInfoTable(ElementName.FEEDERQUALITYPARAMS, 0x66666611);
        elemInfoTable[2] = new ElemInfoTable(ElementName.MEDIA, 0x66666611);
    }
    
    protected ElementInfo getTheElementInfo()
    {
        return super.getTheElementInfo().updateReplace(elemInfoTable);
    }



    /**
     * Constructor for JDFAutoFeeder
     * @param myOwnerDocument
     * @param qualifiedName
     */
    protected JDFAutoFeeder(
        CoreDocumentImpl myOwnerDocument,
        String qualifiedName)
    {
        super(myOwnerDocument, qualifiedName);
    }

    /**
     * Constructor for JDFAutoFeeder
     * @param myOwnerDocument
     * @param myNamespaceURI
     * @param qualifiedName
     */
    protected JDFAutoFeeder(
        CoreDocumentImpl myOwnerDocument,
        String myNamespaceURI,
        String qualifiedName)
    {
        super(myOwnerDocument, myNamespaceURI, qualifiedName);
    }

    /**
     * Constructor for JDFAutoFeeder
     * @param myOwnerDocument
     * @param myNamespaceURI
     * @param qualifiedName
     * @param myLocalName
     */
    protected JDFAutoFeeder(
        CoreDocumentImpl myOwnerDocument,
        String myNamespaceURI,
        String qualifiedName,
        String myLocalName)
    {
        super(myOwnerDocument, myNamespaceURI, qualifiedName, myLocalName);
    }


    public String toString()
    {
        return " JDFAutoFeeder[  --> " + super.toString() + " ]";
    }


        /**
        * Enumeration strings for FeederSynchronization
        */

        public static class EnumFeederSynchronization extends ValuedEnum
        {
            private static final long serialVersionUID = 1L;
            private static int m_startValue = 0;

            private EnumFeederSynchronization(String name)
            {
                super(name, m_startValue++);
            }

            public static EnumFeederSynchronization getEnum(String enumName)
            {
                return (EnumFeederSynchronization) getEnum(EnumFeederSynchronization.class, enumName);
            }

            public static EnumFeederSynchronization getEnum(int enumValue)
            {
                return (EnumFeederSynchronization) getEnum(EnumFeederSynchronization.class, enumValue);
            }

            public static Map getEnumMap()
            {
                return getEnumMap(EnumFeederSynchronization.class);
            }

            public static List getEnumList()
            {
                return getEnumList(EnumFeederSynchronization.class);
            }

            public static Iterator iterator()
            {
                return iterator(EnumFeederSynchronization.class);
            }

            public static final EnumFeederSynchronization Alternate = new EnumFeederSynchronization("Alternate");
            public static final EnumFeederSynchronization Backup = new EnumFeederSynchronization("Backup");
            public static final EnumFeederSynchronization Chain = new EnumFeederSynchronization("Chain");
            public static final EnumFeederSynchronization Primary = new EnumFeederSynchronization("Primary");
        }      



        /**
        * Enumeration strings for Opening
        */

        public static class EnumOpening extends ValuedEnum
        {
            private static final long serialVersionUID = 1L;
            private static int m_startValue = 0;

            private EnumOpening(String name)
            {
                super(name, m_startValue++);
            }

            public static EnumOpening getEnum(String enumName)
            {
                return (EnumOpening) getEnum(EnumOpening.class, enumName);
            }

            public static EnumOpening getEnum(int enumValue)
            {
                return (EnumOpening) getEnum(EnumOpening.class, enumValue);
            }

            public static Map getEnumMap()
            {
                return getEnumMap(EnumOpening.class);
            }

            public static List getEnumList()
            {
                return getEnumList(EnumOpening.class);
            }

            public static Iterator iterator()
            {
                return iterator(EnumOpening.class);
            }

            public static final EnumOpening Back = new EnumOpening("Back");
            public static final EnumOpening Front = new EnumOpening("Front");
            public static final EnumOpening None = new EnumOpening("None");
            public static final EnumOpening Sucker = new EnumOpening("Sucker");
        }      



/* ************************************************************************
 * Attribute getter / setter
 * ************************************************************************
 */
        
        /* ---------------------------------------------------------------------
        Methods for Attribute AlternatePositions
        --------------------------------------------------------------------- */
        /**
          * (36) set attribute AlternatePositions
          * @param value: the value to set the attribute to
          */
        public void setAlternatePositions(JDFIntegerList value)
        {
            setAttribute(AttributeName.ALTERNATEPOSITIONS, value, null);
        }

        /**
          * (20) get JDFIntegerList attribute AlternatePositions
          * @return JDFIntegerList the value of the attribute, null if a the
          *         attribute value is not a valid to create a JDFIntegerList
          */
        public JDFIntegerList getAlternatePositions()
        {
            String strAttrName = "";
            JDFIntegerList nPlaceHolder = null;
            strAttrName = getAttribute(AttributeName.ALTERNATEPOSITIONS, null, JDFConstants.EMPTYSTRING);
            try
            {
                nPlaceHolder = new JDFIntegerList(strAttrName);
            }
            catch(DataFormatException e)
            {
                return null;
            }
            return nPlaceHolder;
        }

        
        /* ---------------------------------------------------------------------
        Methods for Attribute Position
        --------------------------------------------------------------------- */
        /**
          * (36) set attribute Position
          * @param value: the value to set the attribute to
          */
        public void setPosition(int value)
        {
            setAttribute(AttributeName.POSITION, value, null);
        }

        /**
          * (15) get int attribute Position
          * @return int the value of the attribute
          */
        public int getPosition()
        {
            return getIntAttribute(AttributeName.POSITION, null, 0);
        }

        
        /* ---------------------------------------------------------------------
        Methods for Attribute FeederSynchronization
        --------------------------------------------------------------------- */
        /**
          * (5) set attribute FeederSynchronization
          * @param enumVar: the enumVar to set the attribute to
          */
        public void setFeederSynchronization(EnumFeederSynchronization enumVar)
        {
            setAttribute(AttributeName.FEEDERSYNCHRONIZATION, enumVar==null ? null : enumVar.getName(), null);
        }

        /**
          * (9) get attribute FeederSynchronization
          * @return the value of the attribute
          */
        public EnumFeederSynchronization getFeederSynchronization()
        {
            return EnumFeederSynchronization.getEnum(getAttribute(AttributeName.FEEDERSYNCHRONIZATION, null, "Primary"));
        }

        
        /* ---------------------------------------------------------------------
        Methods for Attribute FeederType
        --------------------------------------------------------------------- */
        /**
          * (36) set attribute FeederType
          * @param value: the value to set the attribute to
          */
        public void setFeederType(String value)
        {
            setAttribute(AttributeName.FEEDERTYPE, value, null);
        }

        /**
          * (23) get String attribute FeederType
          * @return the value of the attribute
          */
        public String getFeederType()
        {
            return getAttribute(AttributeName.FEEDERTYPE, null, JDFConstants.EMPTYSTRING);
        }

        
        /* ---------------------------------------------------------------------
        Methods for Attribute Loading
        --------------------------------------------------------------------- */
        /**
          * (36) set attribute Loading
          * @param value: the value to set the attribute to
          */
        public void setLoading(String value)
        {
            setAttribute(AttributeName.LOADING, value, null);
        }

        /**
          * (23) get String attribute Loading
          * @return the value of the attribute
          */
        public String getLoading()
        {
            return getAttribute(AttributeName.LOADING, null, JDFConstants.EMPTYSTRING);
        }

        
        /* ---------------------------------------------------------------------
        Methods for Attribute Opening
        --------------------------------------------------------------------- */
        /**
          * (5) set attribute Opening
          * @param enumVar: the enumVar to set the attribute to
          */
        public void setOpening(EnumOpening enumVar)
        {
            setAttribute(AttributeName.OPENING, enumVar==null ? null : enumVar.getName(), null);
        }

        /**
          * (9) get attribute Opening
          * @return the value of the attribute
          */
        public EnumOpening getOpening()
        {
            return EnumOpening.getEnum(getAttribute(AttributeName.OPENING, null, "None"));
        }

/* ***********************************************************************
 * Element getter / setter
 * ***********************************************************************
 */

    /**
     * (24) const get element Component
     * @return JDFComponent the element
     */
    public JDFComponent getComponent()
    {
        return (JDFComponent) getElement(ElementName.COMPONENT, null, 0);
    }

    /** (25) getCreateComponent
     * 
     * @return JDFComponent the element
     */
    public JDFComponent getCreateComponent()
    {
        return (JDFComponent) getCreateElement_KElement(ElementName.COMPONENT, null, 0);
    }

    /**
     * (29) append element Component
     */
    public JDFComponent appendComponent() throws JDFException
    {
        return (JDFComponent) appendElementN(ElementName.COMPONENT, 1, null);
    }

    /**
      * (31) create inter-resource link to refTarget
      * @param refTarget the element that is referenced
      */
    public void refComponent(JDFComponent refTarget)
    {
        refElement(refTarget);
    }

    /**
     * (24) const get element FeederQualityParams
     * @return JDFFeederQualityParams the element
     */
    public JDFFeederQualityParams getFeederQualityParams()
    {
        return (JDFFeederQualityParams) getElement(ElementName.FEEDERQUALITYPARAMS, null, 0);
    }

    /** (25) getCreateFeederQualityParams
     * 
     * @return JDFFeederQualityParams the element
     */
    public JDFFeederQualityParams getCreateFeederQualityParams()
    {
        return (JDFFeederQualityParams) getCreateElement_KElement(ElementName.FEEDERQUALITYPARAMS, null, 0);
    }

    /**
     * (29) append element FeederQualityParams
     */
    public JDFFeederQualityParams appendFeederQualityParams() throws JDFException
    {
        return (JDFFeederQualityParams) appendElementN(ElementName.FEEDERQUALITYPARAMS, 1, null);
    }

    /**
     * (24) const get element Media
     * @return JDFMedia the element
     */
    public JDFMedia getMedia()
    {
        return (JDFMedia) getElement(ElementName.MEDIA, null, 0);
    }

    /** (25) getCreateMedia
     * 
     * @return JDFMedia the element
     */
    public JDFMedia getCreateMedia()
    {
        return (JDFMedia) getCreateElement_KElement(ElementName.MEDIA, null, 0);
    }

    /**
     * (29) append element Media
     */
    public JDFMedia appendMedia() throws JDFException
    {
        return (JDFMedia) appendElementN(ElementName.MEDIA, 1, null);
    }

    /**
      * (31) create inter-resource link to refTarget
      * @param refTarget the element that is referenced
      */
    public void refMedia(JDFMedia refTarget)
    {
        refElement(refTarget);
    }

}// end namespace JDF
