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
import org.cip4.jdflib.datatypes.JDFNumberRangeList;
import org.cip4.jdflib.datatypes.JDFXYPair;
import org.cip4.jdflib.resource.JDFImageShift;

public abstract class JDFAutoIDPLayout extends JDFElement
{

    private static final long serialVersionUID = 1L;

    private static AtrInfoTable[] atrInfoTable = new AtrInfoTable[7];
    static
    {
        atrInfoTable[0] = new AtrInfoTable(AttributeName.BORDER, 0x33333333, AttributeInfo.EnumAttributeType.double_, null, "0");
        atrInfoTable[1] = new AtrInfoTable(AttributeName.FINISHEDPAGEORIENTATION, 0x33333333, AttributeInfo.EnumAttributeType.enumeration, EnumFinishedPageOrientation.getEnum(0), "Portrait");
        atrInfoTable[2] = new AtrInfoTable(AttributeName.FORCEFRONTSIDE, 0x33333333, AttributeInfo.EnumAttributeType.string, null, null);
        atrInfoTable[3] = new AtrInfoTable(AttributeName.NUMBERUP, 0x33333333, AttributeInfo.EnumAttributeType.XYPair, null, null);
        atrInfoTable[4] = new AtrInfoTable(AttributeName.PRESENTATIONDIRECTION, 0x33333333, AttributeInfo.EnumAttributeType.Any, null, null);
        atrInfoTable[5] = new AtrInfoTable(AttributeName.ROTATE, 0x33333333, AttributeInfo.EnumAttributeType.double_, null, "0");
        atrInfoTable[6] = new AtrInfoTable(AttributeName.SIDES, 0x33333333, AttributeInfo.EnumAttributeType.enumeration, EnumSides.getEnum(0), "OneSided");
    }
    
    @Override
	protected AttributeInfo getTheAttributeInfo()
    {
        return super.getTheAttributeInfo().updateReplace(atrInfoTable);
    }


    private static ElemInfoTable[] elemInfoTable = new ElemInfoTable[1];
    static
    {
        elemInfoTable[0] = new ElemInfoTable(ElementName.IMAGESHIFT, 0x33333333);
    }
    
    @Override
	protected ElementInfo getTheElementInfo()
    {
        return super.getTheElementInfo().updateReplace(elemInfoTable);
    }



    /**
     * Constructor for JDFAutoIDPLayout
     * @param myOwnerDocument
     * @param qualifiedName
     */
    protected JDFAutoIDPLayout(
        CoreDocumentImpl myOwnerDocument,
        String qualifiedName)
    {
        super(myOwnerDocument, qualifiedName);
    }

    /**
     * Constructor for JDFAutoIDPLayout
     * @param myOwnerDocument
     * @param myNamespaceURI
     * @param qualifiedName
     */
    protected JDFAutoIDPLayout(
        CoreDocumentImpl myOwnerDocument,
        String myNamespaceURI,
        String qualifiedName)
    {
        super(myOwnerDocument, myNamespaceURI, qualifiedName);
    }

    /**
     * Constructor for JDFAutoIDPLayout
     * @param myOwnerDocument
     * @param myNamespaceURI
     * @param qualifiedName
     * @param myLocalName
     */
    protected JDFAutoIDPLayout(
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
        return " JDFAutoIDPLayout[  --> " + super.toString() + " ]";
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
            public static final EnumSides TwoSidedLongEdge = new EnumSides("TwoSidedLongEdge");
            public static final EnumSides TwoSidedShortEdge = new EnumSides("TwoSidedShortEdge");
        }      



/* ************************************************************************
 * Attribute getter / setter
 * ************************************************************************
 */
        
        /* ---------------------------------------------------------------------
        Methods for Attribute Border
        --------------------------------------------------------------------- */
        /**
          * (36) set attribute Border
          * @param value: the value to set the attribute to
          */
        public void setBorder(double value)
        {
            setAttribute(AttributeName.BORDER, value, null);
        }

        /**
          * (17) get double attribute Border
          * @return double the value of the attribute
          */
        public double getBorder()
        {
            return getRealAttribute(AttributeName.BORDER, null, 0.0);
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
            return EnumFinishedPageOrientation.getEnum(getAttribute(AttributeName.FINISHEDPAGEORIENTATION, null, "Portrait"));
        }

        
        /* ---------------------------------------------------------------------
        Methods for Attribute ForceFrontSide
        --------------------------------------------------------------------- */
        /**
          * (36) set attribute ForceFrontSide
          * @param value: the value to set the attribute to
          */
        public void setForceFrontSide(JDFNumberRangeList value)
        {
            setAttribute(AttributeName.FORCEFRONTSIDE, value, null);
        }

        /**
          * (20) get JDFNumberRangeList attribute ForceFrontSide
          * @return JDFNumberRangeList the value of the attribute, null if a the
          *         attribute value is not a valid to create a JDFNumberRangeList
          */
        public JDFNumberRangeList getForceFrontSide()
        {
            String strAttrName = "";
            JDFNumberRangeList nPlaceHolder = null;
            strAttrName = getAttribute(AttributeName.FORCEFRONTSIDE, null, JDFConstants.EMPTYSTRING);
            try
            {
                nPlaceHolder = new JDFNumberRangeList(strAttrName);
            }
            catch(DataFormatException e)
            {
                return null;
            }
            return nPlaceHolder;
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
        Methods for Attribute PresentationDirection
        --------------------------------------------------------------------- */
        /**
          * (36) set attribute PresentationDirection
          * @param value: the value to set the attribute to
          */
        public void setPresentationDirection(String value)
        {
            setAttribute(AttributeName.PRESENTATIONDIRECTION, value, null);
        }

        /**
          * (23) get String attribute PresentationDirection
          * @return the value of the attribute
          */
        public String getPresentationDirection()
        {
            return getAttribute(AttributeName.PRESENTATIONDIRECTION, null, JDFConstants.EMPTYSTRING);
        }

        
        /* ---------------------------------------------------------------------
        Methods for Attribute Rotate
        --------------------------------------------------------------------- */
        /**
          * (36) set attribute Rotate
          * @param value: the value to set the attribute to
          */
        public void setRotate(double value)
        {
            setAttribute(AttributeName.ROTATE, value, null);
        }

        /**
          * (17) get double attribute Rotate
          * @return double the value of the attribute
          */
        public double getRotate()
        {
            return getRealAttribute(AttributeName.ROTATE, null, 0.0);
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
            return EnumSides.getEnum(getAttribute(AttributeName.SIDES, null, "OneSided"));
        }

/* ***********************************************************************
 * Element getter / setter
 * ***********************************************************************
 */

    /** (26) getCreateImageShift
     * 
     * @param iSkip number of elements to skip
     * @return JDFImageShift the element
     */
    public JDFImageShift getCreateImageShift(int iSkip)
    {
        return (JDFImageShift)getCreateElement_KElement(ElementName.IMAGESHIFT, null, iSkip);
    }

    /**
     * (27) const get element ImageShift
     * @param iSkip number of elements to skip
     * @return JDFImageShift the element
     * default is getImageShift(0)     */
    public JDFImageShift getImageShift(int iSkip)
    {
        return (JDFImageShift) getElement(ElementName.IMAGESHIFT, null, iSkip);
    }

    /**
     * Get all ImageShift from the current element
     * 
     * @return Collection<JDFImageShift>
     */
    public Collection<JDFImageShift> getAllImageShift()
    {
        Vector<JDFImageShift> v = new Vector<JDFImageShift>();

        JDFImageShift kElem = (JDFImageShift) getFirstChildElement(ElementName.IMAGESHIFT, null);

        while (kElem != null)
        {
            v.add(kElem);

            kElem = (JDFImageShift) kElem.getNextSiblingElement(ElementName.IMAGESHIFT, null);
        }

        return v;
    }

    /**
     * (30) append element ImageShift
     */
    public JDFImageShift appendImageShift() throws JDFException
    {
        return (JDFImageShift) appendElement(ElementName.IMAGESHIFT, null);
    }

}// end namespace JDF
