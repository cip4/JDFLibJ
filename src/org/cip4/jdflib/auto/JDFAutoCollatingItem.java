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
import org.cip4.jdflib.datatypes.JDFMatrix;
import org.cip4.jdflib.resource.process.JDFComponent;
import org.cip4.jdflib.resource.process.JDFMedia;
    /*
    *****************************************************************************
    class JDFAutoCollatingItem : public JDFElement

    *****************************************************************************
    */

public abstract class JDFAutoCollatingItem extends JDFElement
{

    private static final long serialVersionUID = 1L;

    private static AtrInfoTable[] atrInfoTable = new AtrInfoTable[5];
    static
    {
        atrInfoTable[0] = new AtrInfoTable(AttributeName.AMOUNT, 0x33333311, AttributeInfo.EnumAttributeType.integer, null, "1");
        atrInfoTable[1] = new AtrInfoTable(AttributeName.BUNDLEDEPTH, 0x33333311, AttributeInfo.EnumAttributeType.integer, null, null);
        atrInfoTable[2] = new AtrInfoTable(AttributeName.ORIENTATION, 0x33333311, AttributeInfo.EnumAttributeType.enumeration, EnumOrientation.getEnum(0), null);
        atrInfoTable[3] = new AtrInfoTable(AttributeName.TRANSFORMATION, 0x33333311, AttributeInfo.EnumAttributeType.matrix, null, null);
        atrInfoTable[4] = new AtrInfoTable(AttributeName.TRANSFORMATIONCONTEXT, 0x33333311, AttributeInfo.EnumAttributeType.enumeration, EnumTransformationContext.getEnum(0), "StackItem");
    }
    
    protected AttributeInfo getTheAttributeInfo()
    {
        return super.getTheAttributeInfo().updateReplace(atrInfoTable);
    }


    private static ElemInfoTable[] elemInfoTable = new ElemInfoTable[2];
    static
    {
        elemInfoTable[0] = new ElemInfoTable(ElementName.COMPONENT, 0x66666611);
        elemInfoTable[1] = new ElemInfoTable(ElementName.MEDIA, 0x66666611);
    }
    
    protected ElementInfo getTheElementInfo()
    {
        return super.getTheElementInfo().updateReplace(elemInfoTable);
    }



    /**
     * Constructor for JDFAutoCollatingItem
     * @param myOwnerDocument
     * @param qualifiedName
     */
    protected JDFAutoCollatingItem(
        CoreDocumentImpl myOwnerDocument,
        String qualifiedName)
    {
        super(myOwnerDocument, qualifiedName);
    }

    /**
     * Constructor for JDFAutoCollatingItem
     * @param myOwnerDocument
     * @param myNamespaceURI
     * @param qualifiedName
     */
    protected JDFAutoCollatingItem(
        CoreDocumentImpl myOwnerDocument,
        String myNamespaceURI,
        String qualifiedName)
    {
        super(myOwnerDocument, myNamespaceURI, qualifiedName);
    }

    /**
     * Constructor for JDFAutoCollatingItem
     * @param myOwnerDocument
     * @param myNamespaceURI
     * @param qualifiedName
     * @param myLocalName
     */
    protected JDFAutoCollatingItem(
        CoreDocumentImpl myOwnerDocument,
        String myNamespaceURI,
        String qualifiedName,
        String myLocalName)
    {
        super(myOwnerDocument, myNamespaceURI, qualifiedName, myLocalName);
    }


    public String toString()
    {
        return " JDFAutoCollatingItem[  --> " + super.toString() + " ]";
    }


        /**
        * Enumeration strings for Orientation
        */

        public static class EnumOrientation extends ValuedEnum
        {
            private static final long serialVersionUID = 1L;
            private static int m_startValue = 0;

            private EnumOrientation(String name)
            {
                super(name, m_startValue++);
            }

            public static EnumOrientation getEnum(String enumName)
            {
                return (EnumOrientation) getEnum(EnumOrientation.class, enumName);
            }

            public static EnumOrientation getEnum(int enumValue)
            {
                return (EnumOrientation) getEnum(EnumOrientation.class, enumValue);
            }

            public static Map getEnumMap()
            {
                return getEnumMap(EnumOrientation.class);
            }

            public static List getEnumList()
            {
                return getEnumList(EnumOrientation.class);
            }

            public static Iterator iterator()
            {
                return iterator(EnumOrientation.class);
            }

            public static final EnumOrientation Rotate0 = new EnumOrientation("Rotate0");
            public static final EnumOrientation Rotate90 = new EnumOrientation("Rotate90");
            public static final EnumOrientation Rotate180 = new EnumOrientation("Rotate180");
            public static final EnumOrientation Rotate270 = new EnumOrientation("Rotate270");
            public static final EnumOrientation Flip0 = new EnumOrientation("Flip0");
            public static final EnumOrientation Flip90 = new EnumOrientation("Flip90");
            public static final EnumOrientation Flip180 = new EnumOrientation("Flip180");
            public static final EnumOrientation Flip270 = new EnumOrientation("Flip270");
        }      



        /**
        * Enumeration strings for TransformationContext
        */

        public static class EnumTransformationContext extends ValuedEnum
        {
            private static final long serialVersionUID = 1L;
            private static int m_startValue = 0;

            private EnumTransformationContext(String name)
            {
                super(name, m_startValue++);
            }

            public static EnumTransformationContext getEnum(String enumName)
            {
                return (EnumTransformationContext) getEnum(EnumTransformationContext.class, enumName);
            }

            public static EnumTransformationContext getEnum(int enumValue)
            {
                return (EnumTransformationContext) getEnum(EnumTransformationContext.class, enumValue);
            }

            public static Map getEnumMap()
            {
                return getEnumMap(EnumTransformationContext.class);
            }

            public static List getEnumList()
            {
                return getEnumList(EnumTransformationContext.class);
            }

            public static Iterator iterator()
            {
                return iterator(EnumTransformationContext.class);
            }

            public static final EnumTransformationContext StackItem = new EnumTransformationContext("StackItem");
            public static final EnumTransformationContext Component = new EnumTransformationContext("Component");
            public static final EnumTransformationContext CollateItem = new EnumTransformationContext("CollateItem");
        }      



/* ************************************************************************
 * Attribute getter / setter
 * ************************************************************************
 */
        
        /* ---------------------------------------------------------------------
        Methods for Attribute Amount
        --------------------------------------------------------------------- */
        /**
          * (36) set attribute Amount
          * @param value: the value to set the attribute to
          */
        public void setAmount(int value)
        {
            setAttribute(AttributeName.AMOUNT, value, null);
        }

        /**
          * (15) get int attribute Amount
          * @return int the value of the attribute
          */
        public int getAmount()
        {
            return getIntAttribute(AttributeName.AMOUNT, null, 1);
        }

        
        /* ---------------------------------------------------------------------
        Methods for Attribute BundleDepth
        --------------------------------------------------------------------- */
        /**
          * (36) set attribute BundleDepth
          * @param value: the value to set the attribute to
          */
        public void setBundleDepth(int value)
        {
            setAttribute(AttributeName.BUNDLEDEPTH, value, null);
        }

        /**
          * (15) get int attribute BundleDepth
          * @return int the value of the attribute
          */
        public int getBundleDepth()
        {
            return getIntAttribute(AttributeName.BUNDLEDEPTH, null, 0);
        }

        
        /* ---------------------------------------------------------------------
        Methods for Attribute Orientation
        --------------------------------------------------------------------- */
        /**
          * (5) set attribute Orientation
          * @param enumVar: the enumVar to set the attribute to
          */
        public void setOrientation(EnumOrientation enumVar)
        {
            setAttribute(AttributeName.ORIENTATION, enumVar.getName(), null);
        }

        /**
          * (9) get attribute Orientation
          * @return the value of the attribute
          */
        public EnumOrientation getOrientation()
        {
            return EnumOrientation.getEnum(getAttribute(AttributeName.ORIENTATION, null, null));
        }

        
        /* ---------------------------------------------------------------------
        Methods for Attribute Transformation
        --------------------------------------------------------------------- */
        /**
          * (36) set attribute Transformation
          * @param value: the value to set the attribute to
          */
        public void setTransformation(JDFMatrix value)
        {
            setAttribute(AttributeName.TRANSFORMATION, value, null);
        }

        /**
          * (20) get JDFMatrix attribute Transformation
          * @return JDFMatrix the value of the attribute, null if a the
          *         attribute value is not a valid to create a JDFMatrix
          */
        public JDFMatrix getTransformation()
        {
            String strAttrName = "";
            JDFMatrix nPlaceHolder = null;
            strAttrName = getAttribute(AttributeName.TRANSFORMATION, null, JDFConstants.EMPTYSTRING);
            try
            {
                nPlaceHolder = new JDFMatrix(strAttrName);
            }
            catch(DataFormatException e)
            {
                return null;
            }
            return nPlaceHolder;
        }

        
        /* ---------------------------------------------------------------------
        Methods for Attribute TransformationContext
        --------------------------------------------------------------------- */
        /**
          * (5) set attribute TransformationContext
          * @param enumVar: the enumVar to set the attribute to
          */
        public void setTransformationContext(EnumTransformationContext enumVar)
        {
            setAttribute(AttributeName.TRANSFORMATIONCONTEXT, enumVar.getName(), null);
        }

        /**
          * (9) get attribute TransformationContext
          * @return the value of the attribute
          */
        public EnumTransformationContext getTransformationContext()
        {
            return EnumTransformationContext.getEnum(getAttribute(AttributeName.TRANSFORMATIONCONTEXT, null, "StackItem"));
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
