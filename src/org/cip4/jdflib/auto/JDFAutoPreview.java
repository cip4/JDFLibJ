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
import org.cip4.jdflib.core.JDFConstants;
import org.cip4.jdflib.datatypes.JDFMatrix;
import org.cip4.jdflib.resource.JDFResource;
    /*
    *****************************************************************************
    class JDFAutoPreview : public JDFResource

    *****************************************************************************
    */

public abstract class JDFAutoPreview extends JDFResource
{

    private static final long serialVersionUID = 1L;

    private static AtrInfoTable[] atrInfoTable = new AtrInfoTable[6];
    static
    {
        atrInfoTable[0] = new AtrInfoTable(AttributeName.PREVIEWFILETYPE, 0x33333311, AttributeInfo.EnumAttributeType.enumeration, EnumPreviewFileType.getEnum(0), "PNG");
        atrInfoTable[1] = new AtrInfoTable(AttributeName.PREVIEWUSAGE, 0x33333311, AttributeInfo.EnumAttributeType.enumeration, EnumPreviewUsage.getEnum(0), "Separation");
        atrInfoTable[2] = new AtrInfoTable(AttributeName.URL, 0x22222222, AttributeInfo.EnumAttributeType.URL, null, null);
        atrInfoTable[3] = new AtrInfoTable(AttributeName.COMPENSATION, 0x33333333, AttributeInfo.EnumAttributeType.enumeration, EnumCompensation.getEnum(0), null);
        atrInfoTable[4] = new AtrInfoTable(AttributeName.CTM, 0x33333331, AttributeInfo.EnumAttributeType.matrix, null, null);
        atrInfoTable[5] = new AtrInfoTable(AttributeName.DIRECTORY, 0x33333331, AttributeInfo.EnumAttributeType.URL, null, null);
    }
    
    protected AttributeInfo getTheAttributeInfo()
    {
        return super.getTheAttributeInfo().updateReplace(atrInfoTable);
    }



    /**
     * Constructor for JDFAutoPreview
     * @param myOwnerDocument
     * @param qualifiedName
     */
    protected JDFAutoPreview(
        CoreDocumentImpl myOwnerDocument,
        String qualifiedName)
    {
        super(myOwnerDocument, qualifiedName);
    }

    /**
     * Constructor for JDFAutoPreview
     * @param myOwnerDocument
     * @param myNamespaceURI
     * @param qualifiedName
     */
    protected JDFAutoPreview(
        CoreDocumentImpl myOwnerDocument,
        String myNamespaceURI,
        String qualifiedName)
    {
        super(myOwnerDocument, myNamespaceURI, qualifiedName);
    }

    /**
     * Constructor for JDFAutoPreview
     * @param myOwnerDocument
     * @param myNamespaceURI
     * @param qualifiedName
     * @param myLocalName
     */
    protected JDFAutoPreview(
        CoreDocumentImpl myOwnerDocument,
        String myNamespaceURI,
        String qualifiedName,
        String myLocalName)
    {
        super(myOwnerDocument, myNamespaceURI, qualifiedName, myLocalName);
    }


    public String toString()
    {
        return " JDFAutoPreview[  --> " + super.toString() + " ]";
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
        * Enumeration strings for PreviewFileType
        */

        public static class EnumPreviewFileType extends ValuedEnum
        {
            private static final long serialVersionUID = 1L;
            private static int m_startValue = 0;

            private EnumPreviewFileType(String name)
            {
                super(name, m_startValue++);
            }

            public static EnumPreviewFileType getEnum(String enumName)
            {
                return (EnumPreviewFileType) getEnum(EnumPreviewFileType.class, enumName);
            }

            public static EnumPreviewFileType getEnum(int enumValue)
            {
                return (EnumPreviewFileType) getEnum(EnumPreviewFileType.class, enumValue);
            }

            public static Map getEnumMap()
            {
                return getEnumMap(EnumPreviewFileType.class);
            }

            public static List getEnumList()
            {
                return getEnumList(EnumPreviewFileType.class);
            }

            public static Iterator iterator()
            {
                return iterator(EnumPreviewFileType.class);
            }

            public static final EnumPreviewFileType PNG = new EnumPreviewFileType("PNG");
            public static final EnumPreviewFileType CIP3Multiple = new EnumPreviewFileType("CIP3Multiple");
            public static final EnumPreviewFileType CIP3Single = new EnumPreviewFileType("CIP3Single");
        }      



        /**
        * Enumeration strings for PreviewUsage
        */

        public static class EnumPreviewUsage extends ValuedEnum
        {
            private static final long serialVersionUID = 1L;
            private static int m_startValue = 0;

            private EnumPreviewUsage(String name)
            {
                super(name, m_startValue++);
            }

            public static EnumPreviewUsage getEnum(String enumName)
            {
                return (EnumPreviewUsage) getEnum(EnumPreviewUsage.class, enumName);
            }

            public static EnumPreviewUsage getEnum(int enumValue)
            {
                return (EnumPreviewUsage) getEnum(EnumPreviewUsage.class, enumValue);
            }

            public static Map getEnumMap()
            {
                return getEnumMap(EnumPreviewUsage.class);
            }

            public static List getEnumList()
            {
                return getEnumList(EnumPreviewUsage.class);
            }

            public static Iterator iterator()
            {
                return iterator(EnumPreviewUsage.class);
            }

            public static final EnumPreviewUsage Separation = new EnumPreviewUsage("Separation");
            public static final EnumPreviewUsage SeparatedThumbNail = new EnumPreviewUsage("SeparatedThumbNail");
            public static final EnumPreviewUsage SeparationRaw = new EnumPreviewUsage("SeparationRaw");
            public static final EnumPreviewUsage ThumbNail = new EnumPreviewUsage("ThumbNail");
            public static final EnumPreviewUsage Viewable = new EnumPreviewUsage("Viewable");
        }      



        /**
        * Enumeration strings for Compensation
        */

        public static class EnumCompensation extends ValuedEnum
        {
            private static final long serialVersionUID = 1L;
            private static int m_startValue = 0;

            private EnumCompensation(String name)
            {
                super(name, m_startValue++);
            }

            public static EnumCompensation getEnum(String enumName)
            {
                return (EnumCompensation) getEnum(EnumCompensation.class, enumName);
            }

            public static EnumCompensation getEnum(int enumValue)
            {
                return (EnumCompensation) getEnum(EnumCompensation.class, enumValue);
            }

            public static Map getEnumMap()
            {
                return getEnumMap(EnumCompensation.class);
            }

            public static List getEnumList()
            {
                return getEnumList(EnumCompensation.class);
            }

            public static Iterator iterator()
            {
                return iterator(EnumCompensation.class);
            }

            public static final EnumCompensation Unknown = new EnumCompensation("Unknown");
            public static final EnumCompensation None = new EnumCompensation("None");
            public static final EnumCompensation Film = new EnumCompensation("Film");
            public static final EnumCompensation Plate = new EnumCompensation("Plate");
            public static final EnumCompensation Press = new EnumCompensation("Press");
        }      



/* ************************************************************************
 * Attribute getter / setter
 * ************************************************************************
 */
        
        /* ---------------------------------------------------------------------
        Methods for Attribute PreviewFileType
        --------------------------------------------------------------------- */
        /**
          * (5) set attribute PreviewFileType
          * @param enumVar: the enumVar to set the attribute to
          */
        public void setPreviewFileType(EnumPreviewFileType enumVar)
        {
            setAttribute(AttributeName.PREVIEWFILETYPE, enumVar.getName(), null);
        }



        /**
          * (9) get attribute PreviewFileType
          * @return the value of the attribute
          */
        public EnumPreviewFileType getPreviewFileType()
        {
            return EnumPreviewFileType.getEnum(getAttribute(AttributeName.PREVIEWFILETYPE, null, "PNG"));
        }



        
        /* ---------------------------------------------------------------------
        Methods for Attribute PreviewUsage
        --------------------------------------------------------------------- */
        /**
          * (5) set attribute PreviewUsage
          * @param enumVar: the enumVar to set the attribute to
          */
        public void setPreviewUsage(EnumPreviewUsage enumVar)
        {
            setAttribute(AttributeName.PREVIEWUSAGE, enumVar.getName(), null);
        }



        /**
          * (9) get attribute PreviewUsage
          * @return the value of the attribute
          */
        public EnumPreviewUsage getPreviewUsage()
        {
            return EnumPreviewUsage.getEnum(getAttribute(AttributeName.PREVIEWUSAGE, null, "Separation"));
        }



        
        /* ---------------------------------------------------------------------
        Methods for Attribute URL
        --------------------------------------------------------------------- */
        /**
          * (36) set attribute URL
          * @param value: the value to set the attribute to
          */
        public void setURL(String value)
        {
            setAttribute(AttributeName.URL, value, null);
        }



        /**
          * (23) get String attribute URL
          * @return the value of the attribute
          */
        public String getURL()
        {
            return getAttribute(AttributeName.URL, null, JDFConstants.EMPTYSTRING);
        }



        
        /* ---------------------------------------------------------------------
        Methods for Attribute Compensation
        --------------------------------------------------------------------- */
        /**
          * (5) set attribute Compensation
          * @param enumVar: the enumVar to set the attribute to
          */
        public void setCompensation(EnumCompensation enumVar)
        {
            setAttribute(AttributeName.COMPENSATION, enumVar.getName(), null);
        }



        /**
          * (9) get attribute Compensation
          * @return the value of the attribute
          */
        public EnumCompensation getCompensation()
        {
            return EnumCompensation.getEnum(getAttribute(AttributeName.COMPENSATION, null, null));
        }



        
        /* ---------------------------------------------------------------------
        Methods for Attribute CTM
        --------------------------------------------------------------------- */
        /**
          * (36) set attribute CTM
          * @param value: the value to set the attribute to
          */
        public void setCTM(JDFMatrix value)
        {
            setAttribute(AttributeName.CTM, value, null);
        }



        /**
          * (20) get JDFMatrix attribute CTM
          * @return JDFMatrix the value of the attribute, null if a the
          *         attribute value is not a valid to create a JDFMatrix
          */
        public JDFMatrix getCTM()
        {
            String strAttrName = "";
            JDFMatrix nPlaceHolder = null;
            strAttrName = getAttribute(AttributeName.CTM, null, JDFConstants.EMPTYSTRING);
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
        Methods for Attribute Directory
        --------------------------------------------------------------------- */
        /**
          * (36) set attribute Directory
          * @param value: the value to set the attribute to
          */
        public void setDirectory(String value)
        {
            setAttribute(AttributeName.DIRECTORY, value, null);
        }



        /**
          * (23) get String attribute Directory
          * @return the value of the attribute
          */
        public String getDirectory()
        {
            return getAttribute(AttributeName.DIRECTORY, null, JDFConstants.EMPTYSTRING);
        }



}// end namespace JDF
