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
import org.cip4.jdflib.core.VString;
import org.cip4.jdflib.resource.JDFResource;

public abstract class JDFAutoFontParams extends JDFResource
{

    private static final long serialVersionUID = 1L;

    private static AtrInfoTable[] atrInfoTable = new AtrInfoTable[6];
    static
    {
        atrInfoTable[0] = new AtrInfoTable(AttributeName.EMBEDALLFONTS, 0x33333333, AttributeInfo.EnumAttributeType.boolean_, null, "false");
        atrInfoTable[1] = new AtrInfoTable(AttributeName.CANNOTEMBEDFONTPOLICY, 0x33333333, AttributeInfo.EnumAttributeType.enumeration, EnumCannotEmbedFontPolicy.getEnum(0), "Warning");
        atrInfoTable[2] = new AtrInfoTable(AttributeName.ALWAYSEMBED, 0x33333333, AttributeInfo.EnumAttributeType.NMTOKENS, null, null);
        atrInfoTable[3] = new AtrInfoTable(AttributeName.MAXSUBSETPCT, 0x33333333, AttributeInfo.EnumAttributeType.integer, null, null);
        atrInfoTable[4] = new AtrInfoTable(AttributeName.NEVEREMBED, 0x33333333, AttributeInfo.EnumAttributeType.NMTOKENS, null, null);
        atrInfoTable[5] = new AtrInfoTable(AttributeName.SUBSETFONTS, 0x33333333, AttributeInfo.EnumAttributeType.boolean_, null, null);
    }
    
    protected AttributeInfo getTheAttributeInfo()
    {
        return super.getTheAttributeInfo().updateReplace(atrInfoTable);
    }



    /**
     * Constructor for JDFAutoFontParams
     * @param myOwnerDocument
     * @param qualifiedName
     */
    protected JDFAutoFontParams(
        CoreDocumentImpl myOwnerDocument,
        String qualifiedName)
    {
        super(myOwnerDocument, qualifiedName);
    }

    /**
     * Constructor for JDFAutoFontParams
     * @param myOwnerDocument
     * @param myNamespaceURI
     * @param qualifiedName
     */
    protected JDFAutoFontParams(
        CoreDocumentImpl myOwnerDocument,
        String myNamespaceURI,
        String qualifiedName)
    {
        super(myOwnerDocument, myNamespaceURI, qualifiedName);
    }

    /**
     * Constructor for JDFAutoFontParams
     * @param myOwnerDocument
     * @param myNamespaceURI
     * @param qualifiedName
     * @param myLocalName
     */
    protected JDFAutoFontParams(
        CoreDocumentImpl myOwnerDocument,
        String myNamespaceURI,
        String qualifiedName,
        String myLocalName)
    {
        super(myOwnerDocument, myNamespaceURI, qualifiedName, myLocalName);
    }


    public String toString()
    {
        return " JDFAutoFontParams[  --> " + super.toString() + " ]";
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
        * Enumeration strings for CannotEmbedFontPolicy
        */

        public static class EnumCannotEmbedFontPolicy extends ValuedEnum
        {
            private static final long serialVersionUID = 1L;
            private static int m_startValue = 0;

            private EnumCannotEmbedFontPolicy(String name)
            {
                super(name, m_startValue++);
            }

            public static EnumCannotEmbedFontPolicy getEnum(String enumName)
            {
                return (EnumCannotEmbedFontPolicy) getEnum(EnumCannotEmbedFontPolicy.class, enumName);
            }

            public static EnumCannotEmbedFontPolicy getEnum(int enumValue)
            {
                return (EnumCannotEmbedFontPolicy) getEnum(EnumCannotEmbedFontPolicy.class, enumValue);
            }

            public static Map getEnumMap()
            {
                return getEnumMap(EnumCannotEmbedFontPolicy.class);
            }

            public static List getEnumList()
            {
                return getEnumList(EnumCannotEmbedFontPolicy.class);
            }

            public static Iterator iterator()
            {
                return iterator(EnumCannotEmbedFontPolicy.class);
            }

            public static final EnumCannotEmbedFontPolicy Warning = new EnumCannotEmbedFontPolicy("Warning");
            public static final EnumCannotEmbedFontPolicy Error = new EnumCannotEmbedFontPolicy("Error");
            public static final EnumCannotEmbedFontPolicy OK = new EnumCannotEmbedFontPolicy("OK");
        }      



/* ************************************************************************
 * Attribute getter / setter
 * ************************************************************************
 */
        
        /* ---------------------------------------------------------------------
        Methods for Attribute EmbedAllFonts
        --------------------------------------------------------------------- */
        /**
          * (36) set attribute EmbedAllFonts
          * @param value: the value to set the attribute to
          */
        public void setEmbedAllFonts(boolean value)
        {
            setAttribute(AttributeName.EMBEDALLFONTS, value, null);
        }

        /**
          * (18) get boolean attribute EmbedAllFonts
          * @return boolean the value of the attribute
          */
        public boolean getEmbedAllFonts()
        {
            return getBoolAttribute(AttributeName.EMBEDALLFONTS, null, false);
        }

        
        /* ---------------------------------------------------------------------
        Methods for Attribute CannotEmbedFontPolicy
        --------------------------------------------------------------------- */
        /**
          * (5) set attribute CannotEmbedFontPolicy
          * @param enumVar: the enumVar to set the attribute to
          */
        public void setCannotEmbedFontPolicy(EnumCannotEmbedFontPolicy enumVar)
        {
            setAttribute(AttributeName.CANNOTEMBEDFONTPOLICY, enumVar==null ? null : enumVar.getName(), null);
        }

        /**
          * (9) get attribute CannotEmbedFontPolicy
          * @return the value of the attribute
          */
        public EnumCannotEmbedFontPolicy getCannotEmbedFontPolicy()
        {
            return EnumCannotEmbedFontPolicy.getEnum(getAttribute(AttributeName.CANNOTEMBEDFONTPOLICY, null, "Warning"));
        }

        
        /* ---------------------------------------------------------------------
        Methods for Attribute AlwaysEmbed
        --------------------------------------------------------------------- */
        /**
          * (36) set attribute AlwaysEmbed
          * @param value: the value to set the attribute to
          */
        public void setAlwaysEmbed(VString value)
        {
            setAttribute(AttributeName.ALWAYSEMBED, value, null);
        }

        /**
          * (21) get VString attribute AlwaysEmbed
          * @return VString the value of the attribute
          */
        public VString getAlwaysEmbed()
        {
            VString vStrAttrib = new VString();
            String  s = getAttribute(AttributeName.ALWAYSEMBED, null, JDFConstants.EMPTYSTRING);
            vStrAttrib.setAllStrings(s, " ");
            return vStrAttrib;
        }

        
        /* ---------------------------------------------------------------------
        Methods for Attribute MaxSubsetPct
        --------------------------------------------------------------------- */
        /**
          * (36) set attribute MaxSubsetPct
          * @param value: the value to set the attribute to
          */
        public void setMaxSubsetPct(int value)
        {
            setAttribute(AttributeName.MAXSUBSETPCT, value, null);
        }

        /**
          * (15) get int attribute MaxSubsetPct
          * @return int the value of the attribute
          */
        public int getMaxSubsetPct()
        {
            return getIntAttribute(AttributeName.MAXSUBSETPCT, null, 0);
        }

        
        /* ---------------------------------------------------------------------
        Methods for Attribute NeverEmbed
        --------------------------------------------------------------------- */
        /**
          * (36) set attribute NeverEmbed
          * @param value: the value to set the attribute to
          */
        public void setNeverEmbed(VString value)
        {
            setAttribute(AttributeName.NEVEREMBED, value, null);
        }

        /**
          * (21) get VString attribute NeverEmbed
          * @return VString the value of the attribute
          */
        public VString getNeverEmbed()
        {
            VString vStrAttrib = new VString();
            String  s = getAttribute(AttributeName.NEVEREMBED, null, JDFConstants.EMPTYSTRING);
            vStrAttrib.setAllStrings(s, " ");
            return vStrAttrib;
        }

        
        /* ---------------------------------------------------------------------
        Methods for Attribute SubsetFonts
        --------------------------------------------------------------------- */
        /**
          * (36) set attribute SubsetFonts
          * @param value: the value to set the attribute to
          */
        public void setSubsetFonts(boolean value)
        {
            setAttribute(AttributeName.SUBSETFONTS, value, null);
        }

        /**
          * (18) get boolean attribute SubsetFonts
          * @return boolean the value of the attribute
          */
        public boolean getSubsetFonts()
        {
            return getBoolAttribute(AttributeName.SUBSETFONTS, null, false);
        }

}// end namespace JDF
