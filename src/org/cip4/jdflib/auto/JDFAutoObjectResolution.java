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
import org.cip4.jdflib.core.*;                      
import org.cip4.jdflib.datatypes.*;                 
import org.cip4.jdflib.resource.*;

public abstract class JDFAutoObjectResolution extends JDFResource
{

    private static final long serialVersionUID = 1L;

    private static AtrInfoTable[] atrInfoTable = new AtrInfoTable[4];
    static
    {
        atrInfoTable[0] = new AtrInfoTable(AttributeName.RESOLUTION, 0x22222222, AttributeInfo.EnumAttributeType.XYPair, null, null);
        atrInfoTable[1] = new AtrInfoTable(AttributeName.SOURCEOBJECTS, 0x33333333, AttributeInfo.EnumAttributeType.enumerations, EnumSourceObjects.getEnum(0), "All");
        atrInfoTable[2] = new AtrInfoTable(AttributeName.ANTIALIASING, 0x33333311, AttributeInfo.EnumAttributeType.NMTOKEN, null, null);
        atrInfoTable[3] = new AtrInfoTable(AttributeName.OBJECTTAGS, 0x33331111, AttributeInfo.EnumAttributeType.NMTOKENS, null, null);
    }
    
    @Override
	protected AttributeInfo getTheAttributeInfo()
    {
        return super.getTheAttributeInfo().updateReplace(atrInfoTable);
    }



    /**
     * Constructor for JDFAutoObjectResolution
     * @param myOwnerDocument
     * @param qualifiedName
     */
    protected JDFAutoObjectResolution(
        CoreDocumentImpl myOwnerDocument,
        String qualifiedName)
    {
        super(myOwnerDocument, qualifiedName);
    }

    /**
     * Constructor for JDFAutoObjectResolution
     * @param myOwnerDocument
     * @param myNamespaceURI
     * @param qualifiedName
     */
    protected JDFAutoObjectResolution(
        CoreDocumentImpl myOwnerDocument,
        String myNamespaceURI,
        String qualifiedName)
    {
        super(myOwnerDocument, myNamespaceURI, qualifiedName);
    }

    /**
     * Constructor for JDFAutoObjectResolution
     * @param myOwnerDocument
     * @param myNamespaceURI
     * @param qualifiedName
     * @param myLocalName
     */
    protected JDFAutoObjectResolution(
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
        return " JDFAutoObjectResolution[  --> " + super.toString() + " ]";
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
        * Enumeration strings for SourceObjects
        */

        public static class EnumSourceObjects extends ValuedEnum
        {
            private static final long serialVersionUID = 1L;
            private static int m_startValue = 0;

            private EnumSourceObjects(String name)
            {
                super(name, m_startValue++);
            }

            public static EnumSourceObjects getEnum(String enumName)
            {
                return (EnumSourceObjects) getEnum(EnumSourceObjects.class, enumName);
            }

            public static EnumSourceObjects getEnum(int enumValue)
            {
                return (EnumSourceObjects) getEnum(EnumSourceObjects.class, enumValue);
            }

            public static Map getEnumMap()
            {
                return getEnumMap(EnumSourceObjects.class);
            }

            public static List getEnumList()
            {
                return getEnumList(EnumSourceObjects.class);
            }

            public static Iterator iterator()
            {
                return iterator(EnumSourceObjects.class);
            }

            public static final EnumSourceObjects All = new EnumSourceObjects("All");
            public static final EnumSourceObjects ImagePhotographic = new EnumSourceObjects("ImagePhotographic");
            public static final EnumSourceObjects ImageScreenShot = new EnumSourceObjects("ImageScreenShot");
            public static final EnumSourceObjects Text = new EnumSourceObjects("Text");
            public static final EnumSourceObjects LineArt = new EnumSourceObjects("LineArt");
            public static final EnumSourceObjects SmoothShades = new EnumSourceObjects("SmoothShades");
        }      



/* ************************************************************************
 * Attribute getter / setter
 * ************************************************************************
 */
        
        /* ---------------------------------------------------------------------
        Methods for Attribute Resolution
        --------------------------------------------------------------------- */
        /**
          * (36) set attribute Resolution
          * @param value: the value to set the attribute to
          */
        public void setResolution(JDFXYPair value)
        {
            setAttribute(AttributeName.RESOLUTION, value, null);
        }

        /**
          * (20) get JDFXYPair attribute Resolution
          * @return JDFXYPair the value of the attribute, null if a the
          *         attribute value is not a valid to create a JDFXYPair
          */
        public JDFXYPair getResolution()
        {
            String strAttrName = "";
            JDFXYPair nPlaceHolder = null;
            strAttrName = getAttribute(AttributeName.RESOLUTION, null, JDFConstants.EMPTYSTRING);
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
        Methods for Attribute SourceObjects
        --------------------------------------------------------------------- */
        /**
          * (5.2) set attribute SourceObjects
          * @param v vector of the enumeration values
          */
        public void setSourceObjects(Vector v)
        {
            setEnumerationsAttribute(AttributeName.SOURCEOBJECTS, v, null);
        }

        /**
          * (9.2) get SourceObjects attribute SourceObjects
          * @return Vector of the enumerations
          */
        public Vector getSourceObjects()
        {
            return getEnumerationsAttribute(AttributeName.SOURCEOBJECTS, null, EnumSourceObjects.All, false);
        }

        
        /* ---------------------------------------------------------------------
        Methods for Attribute AntiAliasing
        --------------------------------------------------------------------- */
        /**
          * (36) set attribute AntiAliasing
          * @param value: the value to set the attribute to
          */
        public void setAntiAliasing(String value)
        {
            setAttribute(AttributeName.ANTIALIASING, value, null);
        }

        /**
          * (23) get String attribute AntiAliasing
          * @return the value of the attribute
          */
        public String getAntiAliasing()
        {
            return getAttribute(AttributeName.ANTIALIASING, null, JDFConstants.EMPTYSTRING);
        }

        
        /* ---------------------------------------------------------------------
        Methods for Attribute ObjectTags
        --------------------------------------------------------------------- */
        /**
          * (36) set attribute ObjectTags
          * @param value: the value to set the attribute to
          */
        public void setObjectTags(VString value)
        {
            setAttribute(AttributeName.OBJECTTAGS, value, null);
        }

        /**
          * (21) get VString attribute ObjectTags
          * @return VString the value of the attribute
          */
        public VString getObjectTags()
        {
            VString vStrAttrib = new VString();
            String  s = getAttribute(AttributeName.OBJECTTAGS, null, JDFConstants.EMPTYSTRING);
            vStrAttrib.setAllStrings(s, " ");
            return vStrAttrib;
        }

}// end namespace JDF
