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
import org.cip4.jdflib.core.JDFException;
import org.cip4.jdflib.datatypes.JDFIntegerList;
import org.cip4.jdflib.datatypes.JDFXYPair;
import org.cip4.jdflib.resource.JDFHoleLine;
import org.cip4.jdflib.resource.JDFResource;
import org.cip4.jdflib.resource.process.JDFMedia;
import org.cip4.jdflib.resource.process.JDFRegisterMark;
import org.cip4.jdflib.resource.process.postpress.JDFHole;
    /*
    *****************************************************************************
    class JDFAutoHoleMakingParams : public JDFResource

    *****************************************************************************
    */

public abstract class JDFAutoHoleMakingParams extends JDFResource
{

    private static final long serialVersionUID = 1L;

    private static AtrInfoTable[] atrInfoTable = new AtrInfoTable[7];
    static
    {
        atrInfoTable[0] = new AtrInfoTable(AttributeName.CENTERREFERENCE, 0x33333331, AttributeInfo.EnumAttributeType.enumeration, EnumCenterReference.getEnum(0), "TrailingEdge");
        atrInfoTable[1] = new AtrInfoTable(AttributeName.HOLETYPE, 0x22222221, AttributeInfo.EnumAttributeType.enumerations, JDFMedia.EnumHoleType.getEnum(0), null);
        atrInfoTable[2] = new AtrInfoTable(AttributeName.CENTER, 0x33333333, AttributeInfo.EnumAttributeType.XYPair, null, null);
        atrInfoTable[3] = new AtrInfoTable(AttributeName.EXTENT, 0x33333333, AttributeInfo.EnumAttributeType.XYPair, null, null);
        atrInfoTable[4] = new AtrInfoTable(AttributeName.HOLECOUNT, 0x33333311, AttributeInfo.EnumAttributeType.IntegerList, null, null);
        atrInfoTable[5] = new AtrInfoTable(AttributeName.HOLEREFERENCEEDGE, 0x44444431, AttributeInfo.EnumAttributeType.enumeration, EnumHoleReferenceEdge.getEnum(0), null);
        atrInfoTable[6] = new AtrInfoTable(AttributeName.SHAPE, 0x33333333, AttributeInfo.EnumAttributeType.enumeration, EnumShape.getEnum(0), null);
    }
    
    protected AttributeInfo getTheAttributeInfo()
    {
        return super.getTheAttributeInfo().updateReplace(atrInfoTable);
    }


    private static ElemInfoTable[] elemInfoTable = new ElemInfoTable[3];
    static
    {
        elemInfoTable[0] = new ElemInfoTable(ElementName.HOLE, 0x33333333);
        elemInfoTable[1] = new ElemInfoTable(ElementName.HOLELINE, 0x33333331);
        elemInfoTable[2] = new ElemInfoTable(ElementName.REGISTERMARK, 0x66666661);
    }
    
    protected ElementInfo getTheElementInfo()
    {
        return super.getTheElementInfo().updateReplace(elemInfoTable);
    }



    /**
     * Constructor for JDFAutoHoleMakingParams
     * @param myOwnerDocument
     * @param qualifiedName
     */
    protected JDFAutoHoleMakingParams(
        CoreDocumentImpl myOwnerDocument,
        String qualifiedName)
    {
        super(myOwnerDocument, qualifiedName);
    }

    /**
     * Constructor for JDFAutoHoleMakingParams
     * @param myOwnerDocument
     * @param myNamespaceURI
     * @param qualifiedName
     */
    protected JDFAutoHoleMakingParams(
        CoreDocumentImpl myOwnerDocument,
        String myNamespaceURI,
        String qualifiedName)
    {
        super(myOwnerDocument, myNamespaceURI, qualifiedName);
    }

    /**
     * Constructor for JDFAutoHoleMakingParams
     * @param myOwnerDocument
     * @param myNamespaceURI
     * @param qualifiedName
     * @param myLocalName
     */
    protected JDFAutoHoleMakingParams(
        CoreDocumentImpl myOwnerDocument,
        String myNamespaceURI,
        String qualifiedName,
        String myLocalName)
    {
        super(myOwnerDocument, myNamespaceURI, qualifiedName, myLocalName);
    }


    public String toString()
    {
        return " JDFAutoHoleMakingParams[  --> " + super.toString() + " ]";
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
        * Enumeration strings for CenterReference
        */

        public static class EnumCenterReference extends ValuedEnum
        {
            private static final long serialVersionUID = 1L;
            private static int m_startValue = 0;

            private EnumCenterReference(String name)
            {
                super(name, m_startValue++);
            }

            public static EnumCenterReference getEnum(String enumName)
            {
                return (EnumCenterReference) getEnum(EnumCenterReference.class, enumName);
            }

            public static EnumCenterReference getEnum(int enumValue)
            {
                return (EnumCenterReference) getEnum(EnumCenterReference.class, enumValue);
            }

            public static Map getEnumMap()
            {
                return getEnumMap(EnumCenterReference.class);
            }

            public static List getEnumList()
            {
                return getEnumList(EnumCenterReference.class);
            }

            public static Iterator iterator()
            {
                return iterator(EnumCenterReference.class);
            }

            public static final EnumCenterReference TrailingEdge = new EnumCenterReference("TrailingEdge");
            public static final EnumCenterReference RegistrationMark = new EnumCenterReference("RegistrationMark");
        }      



        /**
        * Enumeration strings for HoleReferenceEdge
        */

        public static class EnumHoleReferenceEdge extends ValuedEnum
        {
            private static final long serialVersionUID = 1L;
            private static int m_startValue = 0;

            private EnumHoleReferenceEdge(String name)
            {
                super(name, m_startValue++);
            }

            public static EnumHoleReferenceEdge getEnum(String enumName)
            {
                return (EnumHoleReferenceEdge) getEnum(EnumHoleReferenceEdge.class, enumName);
            }

            public static EnumHoleReferenceEdge getEnum(int enumValue)
            {
                return (EnumHoleReferenceEdge) getEnum(EnumHoleReferenceEdge.class, enumValue);
            }

            public static Map getEnumMap()
            {
                return getEnumMap(EnumHoleReferenceEdge.class);
            }

            public static List getEnumList()
            {
                return getEnumList(EnumHoleReferenceEdge.class);
            }

            public static Iterator iterator()
            {
                return iterator(EnumHoleReferenceEdge.class);
            }

            public static final EnumHoleReferenceEdge Left = new EnumHoleReferenceEdge("Left");
            public static final EnumHoleReferenceEdge Right = new EnumHoleReferenceEdge("Right");
            public static final EnumHoleReferenceEdge Top = new EnumHoleReferenceEdge("Top");
            public static final EnumHoleReferenceEdge Bottom = new EnumHoleReferenceEdge("Bottom");
            public static final EnumHoleReferenceEdge Pattern = new EnumHoleReferenceEdge("Pattern");
        }      



        /**
        * Enumeration strings for Shape
        */

        public static class EnumShape extends ValuedEnum
        {
            private static final long serialVersionUID = 1L;
            private static int m_startValue = 0;

            private EnumShape(String name)
            {
                super(name, m_startValue++);
            }

            public static EnumShape getEnum(String enumName)
            {
                return (EnumShape) getEnum(EnumShape.class, enumName);
            }

            public static EnumShape getEnum(int enumValue)
            {
                return (EnumShape) getEnum(EnumShape.class, enumValue);
            }

            public static Map getEnumMap()
            {
                return getEnumMap(EnumShape.class);
            }

            public static List getEnumList()
            {
                return getEnumList(EnumShape.class);
            }

            public static Iterator iterator()
            {
                return iterator(EnumShape.class);
            }

            public static final EnumShape Eliptical = new EnumShape("Eliptical");
            public static final EnumShape Round = new EnumShape("Round");
            public static final EnumShape Rectangular = new EnumShape("Rectangular");
        }      



/* ************************************************************************
 * Attribute getter / setter
 * ************************************************************************
 */
        
        /* ---------------------------------------------------------------------
        Methods for Attribute CenterReference
        --------------------------------------------------------------------- */
        /**
          * (5) set attribute CenterReference
          * @param enumVar: the enumVar to set the attribute to
          */
        public void setCenterReference(EnumCenterReference enumVar)
        {
            setAttribute(AttributeName.CENTERREFERENCE, enumVar.getName(), null);
        }



        /**
          * (9) get attribute CenterReference
          * @return the value of the attribute
          */
        public EnumCenterReference getCenterReference()
        {
            return EnumCenterReference.getEnum(getAttribute(AttributeName.CENTERREFERENCE, null, "TrailingEdge"));
        }



        
        /* ---------------------------------------------------------------------
        Methods for Attribute HoleType
        --------------------------------------------------------------------- */
        /**
          * (5.2) set attribute HoleType
          * @param v vector of the enumeration values
          */
        public void setHoleType(Vector v)
        {
            setEnumerationsAttribute(AttributeName.HOLETYPE, v, null);
        }



        /**
          * (9.2) get HoleType attribute HoleType
          * @return Vector of the enumerations
          */
        public Vector getHoleType()
        {
            return getEnumerationsAttribute(AttributeName.HOLETYPE, null, JDFMedia.EnumHoleType.getEnum(0), false);
        }



        
        /* ---------------------------------------------------------------------
        Methods for Attribute Center
        --------------------------------------------------------------------- */
        /**
          * (36) set attribute Center
          * @param value: the value to set the attribute to
          */
        public void setCenter(JDFXYPair value)
        {
            setAttribute(AttributeName.CENTER, value, null);
        }



        /**
          * (20) get JDFXYPair attribute Center
          * @return JDFXYPairthe value of the attribute, null if a the
          *         attribute value is not a valid to create a JDFXYPair
          */
        public JDFXYPair getCenter()
        {
            String strAttrName = "";
            JDFXYPair nPlaceHolder = null;
            strAttrName = getAttribute(AttributeName.CENTER, null, JDFConstants.EMPTYSTRING);
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
        Methods for Attribute Extent
        --------------------------------------------------------------------- */
        /**
          * (36) set attribute Extent
          * @param value: the value to set the attribute to
          */
        public void setExtent(JDFXYPair value)
        {
            setAttribute(AttributeName.EXTENT, value, null);
        }



        /**
          * (20) get JDFXYPair attribute Extent
          * @return JDFXYPairthe value of the attribute, null if a the
          *         attribute value is not a valid to create a JDFXYPair
          */
        public JDFXYPair getExtent()
        {
            String strAttrName = "";
            JDFXYPair nPlaceHolder = null;
            strAttrName = getAttribute(AttributeName.EXTENT, null, JDFConstants.EMPTYSTRING);
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
        Methods for Attribute HoleCount
        --------------------------------------------------------------------- */
        /**
          * (36) set attribute HoleCount
          * @param value: the value to set the attribute to
          */
        public void setHoleCount(JDFIntegerList value)
        {
            setAttribute(AttributeName.HOLECOUNT, value, null);
        }



        /**
          * (20) get JDFIntegerList attribute HoleCount
          * @return JDFIntegerListthe value of the attribute, null if a the
          *         attribute value is not a valid to create a JDFIntegerList
          */
        public JDFIntegerList getHoleCount()
        {
            String strAttrName = "";
            JDFIntegerList nPlaceHolder = null;
            strAttrName = getAttribute(AttributeName.HOLECOUNT, null, JDFConstants.EMPTYSTRING);
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
        Methods for Attribute HoleReferenceEdge
        --------------------------------------------------------------------- */
        /**
          * (5) set attribute HoleReferenceEdge
          * @param enumVar: the enumVar to set the attribute to
          */
        public void setHoleReferenceEdge(EnumHoleReferenceEdge enumVar)
        {
            setAttribute(AttributeName.HOLEREFERENCEEDGE, enumVar.getName(), null);
        }



        /**
          * (9) get attribute HoleReferenceEdge
          * @return the value of the attribute
          */
        public EnumHoleReferenceEdge getHoleReferenceEdge()
        {
            return EnumHoleReferenceEdge.getEnum(getAttribute(AttributeName.HOLEREFERENCEEDGE, null, null));
        }



        
        /* ---------------------------------------------------------------------
        Methods for Attribute Shape
        --------------------------------------------------------------------- */
        /**
          * (5) set attribute Shape
          * @param enumVar: the enumVar to set the attribute to
          */
        public void setShape(EnumShape enumVar)
        {
            setAttribute(AttributeName.SHAPE, enumVar.getName(), null);
        }



        /**
          * (9) get attribute Shape
          * @return the value of the attribute
          */
        public EnumShape getShape()
        {
            return EnumShape.getEnum(getAttribute(AttributeName.SHAPE, null, null));
        }



/* ***********************************************************************
 * Element getter / setter
 * ***********************************************************************
 */

    /** (26) getCreateHole
     * 
     * @param iSkip number of elements to skip
     * @return JDFHole the element
     */
    public JDFHole getCreateHole(int iSkip)
    {
        return (JDFHole)getCreateElement_KElement(ElementName.HOLE, null, iSkip);
    }



    /**
     * (27) const get element Hole
     * @param iSkip number of elements to skip
     * @return JDFHole the element
     * default is getHole(0)     */
    public JDFHole getHole(int iSkip)
    {
        return (JDFHole) getElement(ElementName.HOLE, null, iSkip);
    }



    public JDFHole appendHole()
    {
        return (JDFHole) appendElement(ElementName.HOLE, null);
    }

    /** (26) getCreateHoleLine
     * 
     * @param iSkip number of elements to skip
     * @return JDFHoleLine the element
     */
    public JDFHoleLine getCreateHoleLine(int iSkip)
    {
        return (JDFHoleLine)getCreateElement_KElement(ElementName.HOLELINE, null, iSkip);
    }



    /**
     * (27) const get element HoleLine
     * @param iSkip number of elements to skip
     * @return JDFHoleLine the element
     * default is getHoleLine(0)     */
    public JDFHoleLine getHoleLine(int iSkip)
    {
        return (JDFHoleLine) getElement(ElementName.HOLELINE, null, iSkip);
    }



    public JDFHoleLine appendHoleLine()
    {
        return (JDFHoleLine) appendElement(ElementName.HOLELINE, null);
    }

    /**
     * (24) const get element RegisterMark
     * @return JDFRegisterMark the element
     */
    public JDFRegisterMark getRegisterMark()
    {
        return (JDFRegisterMark) getElement(ElementName.REGISTERMARK, null, 0);
    }



    /** (25) getCreateRegisterMark
     * 
     * @return JDFRegisterMark the element
     */
    public JDFRegisterMark getCreateRegisterMark()
    {
        return (JDFRegisterMark) getCreateElement_KElement(ElementName.REGISTERMARK, null, 0);
    }





    /**
     * (29) append elementRegisterMark
     */
    public JDFRegisterMark appendRegisterMark() throws JDFException
    {
        return (JDFRegisterMark) appendElementN(ElementName.REGISTERMARK, 1, null);
    }
    /**
      * (31) create inter-resource link to refTarget
      * @param refTarget the element that is referenced
      */
    public void refRegisterMark(JDFRegisterMark refTarget)
    {
        refElement(refTarget);
    }
}// end namespace JDF
