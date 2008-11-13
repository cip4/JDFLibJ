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

public abstract class JDFAutoRefAnchor extends JDFResource
{

    private static final long serialVersionUID = 1L;

    private static AtrInfoTable[] atrInfoTable = new AtrInfoTable[3];
    static
    {
        atrInfoTable[0] = new AtrInfoTable(AttributeName.ANCHOR, 0x33333333, AttributeInfo.EnumAttributeType.enumeration, EnumAnchor.getEnum(0), null);
        atrInfoTable[1] = new AtrInfoTable(AttributeName.ANCHORTYPE, 0x33333333, AttributeInfo.EnumAttributeType.enumeration, EnumAnchorType.getEnum(0), null);
        atrInfoTable[2] = new AtrInfoTable(AttributeName.RREF, 0x33333333, AttributeInfo.EnumAttributeType.IDREF, null, null);
    }
    
    @Override
	protected AttributeInfo getTheAttributeInfo()
    {
        return super.getTheAttributeInfo().updateReplace(atrInfoTable);
    }



    /**
     * Constructor for JDFAutoRefAnchor
     * @param myOwnerDocument
     * @param qualifiedName
     */
    protected JDFAutoRefAnchor(
        CoreDocumentImpl myOwnerDocument,
        String qualifiedName)
    {
        super(myOwnerDocument, qualifiedName);
    }

    /**
     * Constructor for JDFAutoRefAnchor
     * @param myOwnerDocument
     * @param myNamespaceURI
     * @param qualifiedName
     */
    protected JDFAutoRefAnchor(
        CoreDocumentImpl myOwnerDocument,
        String myNamespaceURI,
        String qualifiedName)
    {
        super(myOwnerDocument, myNamespaceURI, qualifiedName);
    }

    /**
     * Constructor for JDFAutoRefAnchor
     * @param myOwnerDocument
     * @param myNamespaceURI
     * @param qualifiedName
     * @param myLocalName
     */
    protected JDFAutoRefAnchor(
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
        return " JDFAutoRefAnchor[  --> " + super.toString() + " ]";
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

            public static final EnumAnchor TopLeft = new EnumAnchor("TopLeft");
            public static final EnumAnchor TopCenter = new EnumAnchor("TopCenter");
            public static final EnumAnchor TopRight = new EnumAnchor("TopRight");
            public static final EnumAnchor CenterLeft = new EnumAnchor("CenterLeft");
            public static final EnumAnchor Center = new EnumAnchor("Center");
            public static final EnumAnchor CenterRight = new EnumAnchor("CenterRight");
            public static final EnumAnchor BottomLeft = new EnumAnchor("BottomLeft");
            public static final EnumAnchor BottomCenter = new EnumAnchor("BottomCenter");
            public static final EnumAnchor BottomRight = new EnumAnchor("BottomRight");
        }      



        /**
        * Enumeration strings for AnchorType
        */

        public static class EnumAnchorType extends ValuedEnum
        {
            private static final long serialVersionUID = 1L;
            private static int m_startValue = 0;

            private EnumAnchorType(String name)
            {
                super(name, m_startValue++);
            }

            public static EnumAnchorType getEnum(String enumName)
            {
                return (EnumAnchorType) getEnum(EnumAnchorType.class, enumName);
            }

            public static EnumAnchorType getEnum(int enumValue)
            {
                return (EnumAnchorType) getEnum(EnumAnchorType.class, enumValue);
            }

            public static Map getEnumMap()
            {
                return getEnumMap(EnumAnchorType.class);
            }

            public static List getEnumList()
            {
                return getEnumList(EnumAnchorType.class);
            }

            public static Iterator iterator()
            {
                return iterator(EnumAnchorType.class);
            }

            public static final EnumAnchorType Parent = new EnumAnchorType("Parent");
            public static final EnumAnchorType Sibling = new EnumAnchorType("Sibling");
        }      



/* ************************************************************************
 * Attribute getter / setter
 * ************************************************************************
 */
        
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
        Methods for Attribute AnchorType
        --------------------------------------------------------------------- */
        /**
          * (5) set attribute AnchorType
          * @param enumVar: the enumVar to set the attribute to
          */
        public void setAnchorType(EnumAnchorType enumVar)
        {
            setAttribute(AttributeName.ANCHORTYPE, enumVar==null ? null : enumVar.getName(), null);
        }

        /**
          * (9) get attribute AnchorType
          * @return the value of the attribute
          */
        public EnumAnchorType getAnchorType()
        {
            return EnumAnchorType.getEnum(getAttribute(AttributeName.ANCHORTYPE, null, null));
        }

        
        /* ---------------------------------------------------------------------
        Methods for Attribute rRef
        --------------------------------------------------------------------- */
        /**
          * (36) set attribute rRef
          * @param value: the value to set the attribute to
          */
        public void setrRef(String value)
        {
            setAttribute(AttributeName.RREF, value, null);
        }

        /**
          * (23) get String attribute rRef
          * @return the value of the attribute
          */
        public String getrRef()
        {
            return getAttribute(AttributeName.RREF, null, JDFConstants.EMPTYSTRING);
        }

}// end namespace JDF
