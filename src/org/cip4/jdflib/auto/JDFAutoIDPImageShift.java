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
import org.cip4.jdflib.core.JDFElement;
    /*
    *****************************************************************************
    class JDFAutoIDPImageShift : public JDFElement

    *****************************************************************************
    */

public abstract class JDFAutoIDPImageShift extends JDFElement
{

    private static final long serialVersionUID = 1L;

    private static AtrInfoTable[] atrInfoTable = new AtrInfoTable[8];
    static
    {
        atrInfoTable[0] = new AtrInfoTable(AttributeName.POSITIONX, 0x33333333, AttributeInfo.EnumAttributeType.enumeration, EnumPositionX.getEnum(0), "None");
        atrInfoTable[1] = new AtrInfoTable(AttributeName.POSITIONY, 0x33333333, AttributeInfo.EnumAttributeType.enumeration, EnumPositionY.getEnum(0), "None");
        atrInfoTable[2] = new AtrInfoTable(AttributeName.SHIFTX, 0x33333333, AttributeInfo.EnumAttributeType.integer, null, null);
        atrInfoTable[3] = new AtrInfoTable(AttributeName.SHIFTY, 0x33333333, AttributeInfo.EnumAttributeType.integer, null, null);
        atrInfoTable[4] = new AtrInfoTable(AttributeName.SHIFTXSIDE1, 0x33333333, AttributeInfo.EnumAttributeType.integer, null, null);
        atrInfoTable[5] = new AtrInfoTable(AttributeName.SHIFTXSIDE2, 0x33333333, AttributeInfo.EnumAttributeType.integer, null, null);
        atrInfoTable[6] = new AtrInfoTable(AttributeName.SHIFTYSIDE1, 0x33333333, AttributeInfo.EnumAttributeType.integer, null, null);
        atrInfoTable[7] = new AtrInfoTable(AttributeName.SHIFTYSIDE2, 0x33333333, AttributeInfo.EnumAttributeType.integer, null, null);
    }
    
    protected AttributeInfo getTheAttributeInfo()
    {
        return super.getTheAttributeInfo().updateReplace(atrInfoTable);
    }



    /**
     * Constructor for JDFAutoIDPImageShift
     * @param myOwnerDocument
     * @param qualifiedName
     */
    protected JDFAutoIDPImageShift(
        CoreDocumentImpl myOwnerDocument,
        String qualifiedName)
    {
        super(myOwnerDocument, qualifiedName);
    }

    /**
     * Constructor for JDFAutoIDPImageShift
     * @param myOwnerDocument
     * @param myNamespaceURI
     * @param qualifiedName
     */
    protected JDFAutoIDPImageShift(
        CoreDocumentImpl myOwnerDocument,
        String myNamespaceURI,
        String qualifiedName)
    {
        super(myOwnerDocument, myNamespaceURI, qualifiedName);
    }

    /**
     * Constructor for JDFAutoIDPImageShift
     * @param myOwnerDocument
     * @param myNamespaceURI
     * @param qualifiedName
     * @param myLocalName
     */
    protected JDFAutoIDPImageShift(
        CoreDocumentImpl myOwnerDocument,
        String myNamespaceURI,
        String qualifiedName,
        String myLocalName)
    {
        super(myOwnerDocument, myNamespaceURI, qualifiedName, myLocalName);
    }


    public String toString()
    {
        return " JDFAutoIDPImageShift[  --> " + super.toString() + " ]";
    }


        /**
        * Enumeration strings for PositionX
        */

        public static class EnumPositionX extends ValuedEnum
        {
            private static final long serialVersionUID = 1L;
            private static int m_startValue = 0;

            private EnumPositionX(String name)
            {
                super(name, m_startValue++);
            }

            public static EnumPositionX getEnum(String enumName)
            {
                return (EnumPositionX) getEnum(EnumPositionX.class, enumName);
            }

            public static EnumPositionX getEnum(int enumValue)
            {
                return (EnumPositionX) getEnum(EnumPositionX.class, enumValue);
            }

            public static Map getEnumMap()
            {
                return getEnumMap(EnumPositionX.class);
            }

            public static List getEnumList()
            {
                return getEnumList(EnumPositionX.class);
            }

            public static Iterator iterator()
            {
                return iterator(EnumPositionX.class);
            }

            public static final EnumPositionX Center = new EnumPositionX("Center");
            public static final EnumPositionX Left = new EnumPositionX("Left");
            public static final EnumPositionX None = new EnumPositionX("None");
            public static final EnumPositionX Right = new EnumPositionX("Right");
        }      



        /**
        * Enumeration strings for PositionY
        */

        public static class EnumPositionY extends ValuedEnum
        {
            private static final long serialVersionUID = 1L;
            private static int m_startValue = 0;

            private EnumPositionY(String name)
            {
                super(name, m_startValue++);
            }

            public static EnumPositionY getEnum(String enumName)
            {
                return (EnumPositionY) getEnum(EnumPositionY.class, enumName);
            }

            public static EnumPositionY getEnum(int enumValue)
            {
                return (EnumPositionY) getEnum(EnumPositionY.class, enumValue);
            }

            public static Map getEnumMap()
            {
                return getEnumMap(EnumPositionY.class);
            }

            public static List getEnumList()
            {
                return getEnumList(EnumPositionY.class);
            }

            public static Iterator iterator()
            {
                return iterator(EnumPositionY.class);
            }

            public static final EnumPositionY Bottom = new EnumPositionY("Bottom");
            public static final EnumPositionY Center = new EnumPositionY("Center");
            public static final EnumPositionY None = new EnumPositionY("None");
            public static final EnumPositionY Top = new EnumPositionY("Top");
        }      



/* ************************************************************************
 * Attribute getter / setter
 * ************************************************************************
 */
        
        /* ---------------------------------------------------------------------
        Methods for Attribute PositionX
        --------------------------------------------------------------------- */
        /**
          * (5) set attribute PositionX
          * @param enumVar: the enumVar to set the attribute to
          */
        public void setPositionX(EnumPositionX enumVar)
        {
            setAttribute(AttributeName.POSITIONX, enumVar.getName(), null);
        }

        /**
          * (9) get attribute PositionX
          * @return the value of the attribute
          */
        public EnumPositionX getPositionX()
        {
            return EnumPositionX.getEnum(getAttribute(AttributeName.POSITIONX, null, "None"));
        }

        
        /* ---------------------------------------------------------------------
        Methods for Attribute PositionY
        --------------------------------------------------------------------- */
        /**
          * (5) set attribute PositionY
          * @param enumVar: the enumVar to set the attribute to
          */
        public void setPositionY(EnumPositionY enumVar)
        {
            setAttribute(AttributeName.POSITIONY, enumVar.getName(), null);
        }

        /**
          * (9) get attribute PositionY
          * @return the value of the attribute
          */
        public EnumPositionY getPositionY()
        {
            return EnumPositionY.getEnum(getAttribute(AttributeName.POSITIONY, null, "None"));
        }

        
        /* ---------------------------------------------------------------------
        Methods for Attribute ShiftX
        --------------------------------------------------------------------- */
        /**
          * (36) set attribute ShiftX
          * @param value: the value to set the attribute to
          */
        public void setShiftX(int value)
        {
            setAttribute(AttributeName.SHIFTX, value, null);
        }

        /**
          * (15) get int attribute ShiftX
          * @return int the value of the attribute
          */
        public int getShiftX()
        {
            return getIntAttribute(AttributeName.SHIFTX, null, 0);
        }

        
        /* ---------------------------------------------------------------------
        Methods for Attribute ShiftY
        --------------------------------------------------------------------- */
        /**
          * (36) set attribute ShiftY
          * @param value: the value to set the attribute to
          */
        public void setShiftY(int value)
        {
            setAttribute(AttributeName.SHIFTY, value, null);
        }

        /**
          * (15) get int attribute ShiftY
          * @return int the value of the attribute
          */
        public int getShiftY()
        {
            return getIntAttribute(AttributeName.SHIFTY, null, 0);
        }

        
        /* ---------------------------------------------------------------------
        Methods for Attribute ShiftXSide1
        --------------------------------------------------------------------- */
        /**
          * (36) set attribute ShiftXSide1
          * @param value: the value to set the attribute to
          */
        public void setShiftXSide1(int value)
        {
            setAttribute(AttributeName.SHIFTXSIDE1, value, null);
        }

        /**
          * (15) get int attribute ShiftXSide1
          * @return int the value of the attribute
          */
        public int getShiftXSide1()
        {
            return getIntAttribute(AttributeName.SHIFTXSIDE1, null, 0);
        }

        
        /* ---------------------------------------------------------------------
        Methods for Attribute ShiftXSide2
        --------------------------------------------------------------------- */
        /**
          * (36) set attribute ShiftXSide2
          * @param value: the value to set the attribute to
          */
        public void setShiftXSide2(int value)
        {
            setAttribute(AttributeName.SHIFTXSIDE2, value, null);
        }

        /**
          * (15) get int attribute ShiftXSide2
          * @return int the value of the attribute
          */
        public int getShiftXSide2()
        {
            return getIntAttribute(AttributeName.SHIFTXSIDE2, null, 0);
        }

        
        /* ---------------------------------------------------------------------
        Methods for Attribute ShiftYSide1
        --------------------------------------------------------------------- */
        /**
          * (36) set attribute ShiftYSide1
          * @param value: the value to set the attribute to
          */
        public void setShiftYSide1(int value)
        {
            setAttribute(AttributeName.SHIFTYSIDE1, value, null);
        }

        /**
          * (15) get int attribute ShiftYSide1
          * @return int the value of the attribute
          */
        public int getShiftYSide1()
        {
            return getIntAttribute(AttributeName.SHIFTYSIDE1, null, 0);
        }

        
        /* ---------------------------------------------------------------------
        Methods for Attribute ShiftYSide2
        --------------------------------------------------------------------- */
        /**
          * (36) set attribute ShiftYSide2
          * @param value: the value to set the attribute to
          */
        public void setShiftYSide2(int value)
        {
            setAttribute(AttributeName.SHIFTYSIDE2, value, null);
        }

        /**
          * (15) get int attribute ShiftYSide2
          * @return int the value of the attribute
          */
        public int getShiftYSide2()
        {
            return getIntAttribute(AttributeName.SHIFTYSIDE2, null, 0);
        }

}// end namespace JDF
