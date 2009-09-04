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
import org.cip4.jdflib.core.JDFException;
import org.cip4.jdflib.datatypes.JDFXYPair;
import org.cip4.jdflib.resource.intent.JDFIntentResource;
import org.cip4.jdflib.resource.process.JDFLayout;
import org.cip4.jdflib.span.JDFIntegerSpan;
import org.cip4.jdflib.span.JDFShapeSpan;
import org.cip4.jdflib.span.JDFSpanFinishedGrainDirection;
import org.cip4.jdflib.span.JDFSpanSizePolicy;
import org.cip4.jdflib.span.JDFXYPairSpan;

public abstract class JDFAutoLayoutIntent extends JDFIntentResource
{

    private static final long serialVersionUID = 1L;

    private static AtrInfoTable[] atrInfoTable = new AtrInfoTable[5];
    static
    {
        atrInfoTable[0] = new AtrInfoTable(AttributeName.FOLIOCOUNT, 0x33333331, AttributeInfo.EnumAttributeType.enumeration, EnumFolioCount.getEnum(0), "Booklet");
        atrInfoTable[1] = new AtrInfoTable(AttributeName.NUMBERUP, 0x33333333, AttributeInfo.EnumAttributeType.XYPair, null, "1 1");
        atrInfoTable[2] = new AtrInfoTable(AttributeName.FINISHEDPAGEORIENTATION, 0x44444443, AttributeInfo.EnumAttributeType.enumeration, EnumFinishedPageOrientation.getEnum(0), null);
        atrInfoTable[3] = new AtrInfoTable(AttributeName.ROTATEPOLICY, 0x33333311, AttributeInfo.EnumAttributeType.enumeration, EnumRotatePolicy.getEnum(0), null);
        atrInfoTable[4] = new AtrInfoTable(AttributeName.SIDES, 0x33333333, AttributeInfo.EnumAttributeType.enumeration, EnumSides.getEnum(0), null);
    }
    
    @Override
	protected AttributeInfo getTheAttributeInfo()
    {
        return super.getTheAttributeInfo().updateReplace(atrInfoTable);
    }


    private static ElemInfoTable[] elemInfoTable = new ElemInfoTable[7];
    static
    {
        elemInfoTable[0] = new ElemInfoTable(ElementName.DIMENSIONS, 0x66666661);
        elemInfoTable[1] = new ElemInfoTable(ElementName.FINISHEDDIMENSIONS, 0x66666661);
        elemInfoTable[2] = new ElemInfoTable(ElementName.FINISHEDGRAINDIRECTION, 0x66666611);
        elemInfoTable[3] = new ElemInfoTable(ElementName.PAGES, 0x66666661);
        elemInfoTable[4] = new ElemInfoTable(ElementName.PAGEVARIANCE, 0x66666661);
        elemInfoTable[5] = new ElemInfoTable(ElementName.LAYOUT, 0x66666661);
        elemInfoTable[6] = new ElemInfoTable(ElementName.SIZEPOLICY, 0x66666611);
    }
    
    @Override
	protected ElementInfo getTheElementInfo()
    {
        return super.getTheElementInfo().updateReplace(elemInfoTable);
    }



    /**
     * Constructor for JDFAutoLayoutIntent
     * @param myOwnerDocument
     * @param qualifiedName
     */
    protected JDFAutoLayoutIntent(
        CoreDocumentImpl myOwnerDocument,
        String qualifiedName)
    {
        super(myOwnerDocument, qualifiedName);
    }

    /**
     * Constructor for JDFAutoLayoutIntent
     * @param myOwnerDocument
     * @param myNamespaceURI
     * @param qualifiedName
     */
    protected JDFAutoLayoutIntent(
        CoreDocumentImpl myOwnerDocument,
        String myNamespaceURI,
        String qualifiedName)
    {
        super(myOwnerDocument, myNamespaceURI, qualifiedName);
    }

    /**
     * Constructor for JDFAutoLayoutIntent
     * @param myOwnerDocument
     * @param myNamespaceURI
     * @param qualifiedName
     * @param myLocalName
     */
    protected JDFAutoLayoutIntent(
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
        return " JDFAutoLayoutIntent[  --> " + super.toString() + " ]";
    }


        /**
        * Enumeration strings for FolioCount
        */

        public static class EnumFolioCount extends ValuedEnum
        {
            private static final long serialVersionUID = 1L;
            private static int m_startValue = 0;

            private EnumFolioCount(String name)
            {
                super(name, m_startValue++);
            }

            public static EnumFolioCount getEnum(String enumName)
            {
                return (EnumFolioCount) getEnum(EnumFolioCount.class, enumName);
            }

            public static EnumFolioCount getEnum(int enumValue)
            {
                return (EnumFolioCount) getEnum(EnumFolioCount.class, enumValue);
            }

            public static Map getEnumMap()
            {
                return getEnumMap(EnumFolioCount.class);
            }

            public static List getEnumList()
            {
                return getEnumList(EnumFolioCount.class);
            }

            public static Iterator iterator()
            {
                return iterator(EnumFolioCount.class);
            }

            public static final EnumFolioCount Booklet = new EnumFolioCount("Booklet");
            public static final EnumFolioCount Flat = new EnumFolioCount("Flat");
        }      



        /**
        * Enumeration strings for FinishedPageOrientation
        */

        public static class EnumFinishedPageOrientation extends ValuedEnum
        {
            private static final long serialVersionUID = 1L;
            private static int m_startValue = 0;

            private EnumFinishedPageOrientation(String name)
            {
                super(name, m_startValue++);
            }

            public static EnumFinishedPageOrientation getEnum(String enumName)
            {
                return (EnumFinishedPageOrientation) getEnum(EnumFinishedPageOrientation.class, enumName);
            }

            public static EnumFinishedPageOrientation getEnum(int enumValue)
            {
                return (EnumFinishedPageOrientation) getEnum(EnumFinishedPageOrientation.class, enumValue);
            }

            public static Map getEnumMap()
            {
                return getEnumMap(EnumFinishedPageOrientation.class);
            }

            public static List getEnumList()
            {
                return getEnumList(EnumFinishedPageOrientation.class);
            }

            public static Iterator iterator()
            {
                return iterator(EnumFinishedPageOrientation.class);
            }

            public static final EnumFinishedPageOrientation Portrait = new EnumFinishedPageOrientation("Portrait");
            public static final EnumFinishedPageOrientation Landscape = new EnumFinishedPageOrientation("Landscape");
        }      



        /**
        * Enumeration strings for RotatePolicy
        */

        public static class EnumRotatePolicy extends ValuedEnum
        {
            private static final long serialVersionUID = 1L;
            private static int m_startValue = 0;

            private EnumRotatePolicy(String name)
            {
                super(name, m_startValue++);
            }

            public static EnumRotatePolicy getEnum(String enumName)
            {
                return (EnumRotatePolicy) getEnum(EnumRotatePolicy.class, enumName);
            }

            public static EnumRotatePolicy getEnum(int enumValue)
            {
                return (EnumRotatePolicy) getEnum(EnumRotatePolicy.class, enumValue);
            }

            public static Map getEnumMap()
            {
                return getEnumMap(EnumRotatePolicy.class);
            }

            public static List getEnumList()
            {
                return getEnumList(EnumRotatePolicy.class);
            }

            public static Iterator iterator()
            {
                return iterator(EnumRotatePolicy.class);
            }

            public static final EnumRotatePolicy NoRotate = new EnumRotatePolicy("NoRotate");
            public static final EnumRotatePolicy RotateOrthogonal = new EnumRotatePolicy("RotateOrthogonal");
            public static final EnumRotatePolicy RotateClockwise = new EnumRotatePolicy("RotateClockwise");
            public static final EnumRotatePolicy RotateCounterClockwise = new EnumRotatePolicy("RotateCounterClockwise");
        }      



        /**
        * Enumeration strings for Sides
        */

        public static class EnumSides extends ValuedEnum
        {
            private static final long serialVersionUID = 1L;
            private static int m_startValue = 0;

            private EnumSides(String name)
            {
                super(name, m_startValue++);
            }

            public static EnumSides getEnum(String enumName)
            {
                return (EnumSides) getEnum(EnumSides.class, enumName);
            }

            public static EnumSides getEnum(int enumValue)
            {
                return (EnumSides) getEnum(EnumSides.class, enumValue);
            }

            public static Map getEnumMap()
            {
                return getEnumMap(EnumSides.class);
            }

            public static List getEnumList()
            {
                return getEnumList(EnumSides.class);
            }

            public static Iterator iterator()
            {
                return iterator(EnumSides.class);
            }

            public static final EnumSides OneSided = new EnumSides("OneSided");
            public static final EnumSides OneSidedBack = new EnumSides("OneSidedBack");
            public static final EnumSides TwoSidedHeadToHead = new EnumSides("TwoSidedHeadToHead");
            public static final EnumSides TwoSidedHeadToFoot = new EnumSides("TwoSidedHeadToFoot");
        }      



/* ************************************************************************
 * Attribute getter / setter
 * ************************************************************************
 */
        
        /* ---------------------------------------------------------------------
        Methods for Attribute FolioCount
        --------------------------------------------------------------------- */
        /**
          * (5) set attribute FolioCount
          * @param enumVar: the enumVar to set the attribute to
          */
        public void setFolioCount(EnumFolioCount enumVar)
        {
            setAttribute(AttributeName.FOLIOCOUNT, enumVar==null ? null : enumVar.getName(), null);
        }

        /**
          * (9) get attribute FolioCount
          * @return the value of the attribute
          */
        public EnumFolioCount getFolioCount()
        {
            return EnumFolioCount.getEnum(getAttribute(AttributeName.FOLIOCOUNT, null, "Booklet"));
        }

        
        /* ---------------------------------------------------------------------
        Methods for Attribute NumberUp
        --------------------------------------------------------------------- */
        /**
          * (36) set attribute NumberUp
          * @param value: the value to set the attribute to
          */
        public void setNumberUp(JDFXYPair value)
        {
            setAttribute(AttributeName.NUMBERUP, value, null);
        }

        /**
          * (20) get JDFXYPair attribute NumberUp
          * @return JDFXYPair the value of the attribute, null if a the
          *         attribute value is not a valid to create a JDFXYPair
          */
        public JDFXYPair getNumberUp()
        {
            String strAttrName = "";
            JDFXYPair nPlaceHolder = null;
            strAttrName = getAttribute(AttributeName.NUMBERUP, null, JDFConstants.EMPTYSTRING);
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
        Methods for Attribute FinishedPageOrientation
        --------------------------------------------------------------------- */
        /**
          * (5) set attribute FinishedPageOrientation
          * @param enumVar: the enumVar to set the attribute to
          */
        public void setFinishedPageOrientation(EnumFinishedPageOrientation enumVar)
        {
            setAttribute(AttributeName.FINISHEDPAGEORIENTATION, enumVar==null ? null : enumVar.getName(), null);
        }

        /**
          * (9) get attribute FinishedPageOrientation
          * @return the value of the attribute
          */
        public EnumFinishedPageOrientation getFinishedPageOrientation()
        {
            return EnumFinishedPageOrientation.getEnum(getAttribute(AttributeName.FINISHEDPAGEORIENTATION, null, null));
        }

        
        /* ---------------------------------------------------------------------
        Methods for Attribute RotatePolicy
        --------------------------------------------------------------------- */
        /**
          * (5) set attribute RotatePolicy
          * @param enumVar: the enumVar to set the attribute to
          */
        public void setRotatePolicy(EnumRotatePolicy enumVar)
        {
            setAttribute(AttributeName.ROTATEPOLICY, enumVar==null ? null : enumVar.getName(), null);
        }

        /**
          * (9) get attribute RotatePolicy
          * @return the value of the attribute
          */
        public EnumRotatePolicy getRotatePolicy()
        {
            return EnumRotatePolicy.getEnum(getAttribute(AttributeName.ROTATEPOLICY, null, null));
        }

        
        /* ---------------------------------------------------------------------
        Methods for Attribute Sides
        --------------------------------------------------------------------- */
        /**
          * (5) set attribute Sides
          * @param enumVar: the enumVar to set the attribute to
          */
        public void setSides(EnumSides enumVar)
        {
            setAttribute(AttributeName.SIDES, enumVar==null ? null : enumVar.getName(), null);
        }

        /**
          * (9) get attribute Sides
          * @return the value of the attribute
          */
        public EnumSides getSides()
        {
            return EnumSides.getEnum(getAttribute(AttributeName.SIDES, null, null));
        }

/* ***********************************************************************
 * Element getter / setter
 * ***********************************************************************
 */

    /**
     * (24) const get element Dimensions
     * @return JDFXYPairSpan the element
     */
    public JDFXYPairSpan getDimensions()
    {
        return (JDFXYPairSpan) getElement(ElementName.DIMENSIONS, null, 0);
    }

    /** (25) getCreateDimensions
     * 
     * @return JDFXYPairSpan the element
     */
    public JDFXYPairSpan getCreateDimensions()
    {
        return (JDFXYPairSpan) getCreateElement_KElement(ElementName.DIMENSIONS, null, 0);
    }

    /**
     * (29) append element Dimensions
     */
    public JDFXYPairSpan appendDimensions() throws JDFException
    {
        return (JDFXYPairSpan) appendElementN(ElementName.DIMENSIONS, 1, null);
    }

    /**
     * (24) const get element FinishedDimensions
     * @return JDFShapeSpan the element
     */
    public JDFShapeSpan getFinishedDimensions()
    {
        return (JDFShapeSpan) getElement(ElementName.FINISHEDDIMENSIONS, null, 0);
    }

    /** (25) getCreateFinishedDimensions
     * 
     * @return JDFShapeSpan the element
     */
    public JDFShapeSpan getCreateFinishedDimensions()
    {
        return (JDFShapeSpan) getCreateElement_KElement(ElementName.FINISHEDDIMENSIONS, null, 0);
    }

    /**
     * (29) append element FinishedDimensions
     */
    public JDFShapeSpan appendFinishedDimensions() throws JDFException
    {
        return (JDFShapeSpan) appendElementN(ElementName.FINISHEDDIMENSIONS, 1, null);
    }

    /**
     * (24) const get element FinishedGrainDirection
     * @return JDFSpanFinishedGrainDirection the element
     */
    public JDFSpanFinishedGrainDirection getFinishedGrainDirection()
    {
        return (JDFSpanFinishedGrainDirection) getElement(ElementName.FINISHEDGRAINDIRECTION, null, 0);
    }

    /** (25) getCreateFinishedGrainDirection
     * 
     * @return JDFSpanFinishedGrainDirection the element
     */
    public JDFSpanFinishedGrainDirection getCreateFinishedGrainDirection()
    {
        return (JDFSpanFinishedGrainDirection) getCreateElement_KElement(ElementName.FINISHEDGRAINDIRECTION, null, 0);
    }

    /**
     * (29) append element FinishedGrainDirection
     */
    public JDFSpanFinishedGrainDirection appendFinishedGrainDirection() throws JDFException
    {
        return (JDFSpanFinishedGrainDirection) appendElementN(ElementName.FINISHEDGRAINDIRECTION, 1, null);
    }

    /**
     * (24) const get element Pages
     * @return JDFIntegerSpan the element
     */
    public JDFIntegerSpan getPages()
    {
        return (JDFIntegerSpan) getElement(ElementName.PAGES, null, 0);
    }

    /** (25) getCreatePages
     * 
     * @return JDFIntegerSpan the element
     */
    public JDFIntegerSpan getCreatePages()
    {
        return (JDFIntegerSpan) getCreateElement_KElement(ElementName.PAGES, null, 0);
    }

    /**
     * (29) append element Pages
     */
    public JDFIntegerSpan appendPages() throws JDFException
    {
        return (JDFIntegerSpan) appendElementN(ElementName.PAGES, 1, null);
    }

    /**
     * (24) const get element PageVariance
     * @return JDFIntegerSpan the element
     */
    public JDFIntegerSpan getPageVariance()
    {
        return (JDFIntegerSpan) getElement(ElementName.PAGEVARIANCE, null, 0);
    }

    /** (25) getCreatePageVariance
     * 
     * @return JDFIntegerSpan the element
     */
    public JDFIntegerSpan getCreatePageVariance()
    {
        return (JDFIntegerSpan) getCreateElement_KElement(ElementName.PAGEVARIANCE, null, 0);
    }

    /**
     * (29) append element PageVariance
     */
    public JDFIntegerSpan appendPageVariance() throws JDFException
    {
        return (JDFIntegerSpan) appendElementN(ElementName.PAGEVARIANCE, 1, null);
    }

    /**
     * (24) const get element Layout
     * @return JDFLayout the element
     */
    public JDFLayout getLayout()
    {
        return (JDFLayout) getElement(ElementName.LAYOUT, null, 0);
    }

    /** (25) getCreateLayout
     * 
     * @return JDFLayout the element
     */
    public JDFLayout getCreateLayout()
    {
        return (JDFLayout) getCreateElement_KElement(ElementName.LAYOUT, null, 0);
    }

    /**
     * (29) append element Layout
     */
    public JDFLayout appendLayout() throws JDFException
    {
        return (JDFLayout) appendElementN(ElementName.LAYOUT, 1, null);
    }

    /**
      * (31) create inter-resource link to refTarget
      * @param refTarget the element that is referenced
      */
    public void refLayout(JDFLayout refTarget)
    {
        refElement(refTarget);
    }

    /**
     * (24) const get element SizePolicy
     * @return JDFSpanSizePolicy the element
     */
    public JDFSpanSizePolicy getSizePolicy()
    {
        return (JDFSpanSizePolicy) getElement(ElementName.SIZEPOLICY, null, 0);
    }

    /** (25) getCreateSizePolicy
     * 
     * @return JDFSpanSizePolicy the element
     */
    public JDFSpanSizePolicy getCreateSizePolicy()
    {
        return (JDFSpanSizePolicy) getCreateElement_KElement(ElementName.SIZEPOLICY, null, 0);
    }

    /**
     * (29) append element SizePolicy
     */
    public JDFSpanSizePolicy appendSizePolicy() throws JDFException
    {
        return (JDFSpanSizePolicy) appendElementN(ElementName.SIZEPOLICY, 1, null);
    }

}// end namespace JDF
