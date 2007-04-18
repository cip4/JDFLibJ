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
import org.cip4.jdflib.core.JDFElement;
import org.cip4.jdflib.core.JDFException;
import org.cip4.jdflib.util.JDFDate;
import org.cip4.jdflib.util.JDFDuration;
    /*
    *****************************************************************************
    class JDFAutoDisposition : public JDFElement

    *****************************************************************************
    */

public abstract class JDFAutoDisposition extends JDFElement
{

    private static final long serialVersionUID = 1L;

    private static AtrInfoTable[] atrInfoTable = new AtrInfoTable[6];
    static
    {
        atrInfoTable[0] = new AtrInfoTable(AttributeName.DISPOSITIONACTION, 0x33333311, AttributeInfo.EnumAttributeType.enumeration, EnumDispositionAction.getEnum(0), "Delete");
        atrInfoTable[1] = new AtrInfoTable(AttributeName.PRIORITY, 0x33333311, AttributeInfo.EnumAttributeType.integer, null, "0");
        atrInfoTable[2] = new AtrInfoTable(AttributeName.DISPOSITIONUSAGE, 0x33333311, AttributeInfo.EnumAttributeType.enumeration, EnumDispositionUsage.getEnum(0), null);
        atrInfoTable[3] = new AtrInfoTable(AttributeName.EXTRADURATION, 0x33333311, AttributeInfo.EnumAttributeType.duration, null, null);
        atrInfoTable[4] = new AtrInfoTable(AttributeName.MINDURATION, 0x33333311, AttributeInfo.EnumAttributeType.duration, null, null);
        atrInfoTable[5] = new AtrInfoTable(AttributeName.UNTIL, 0x33333311, AttributeInfo.EnumAttributeType.dateTime, null, null);
    }
    
    protected AttributeInfo getTheAttributeInfo()
    {
        return super.getTheAttributeInfo().updateReplace(atrInfoTable);
    }



    /**
     * Constructor for JDFAutoDisposition
     * @param myOwnerDocument
     * @param qualifiedName
     */
    protected JDFAutoDisposition(
        CoreDocumentImpl myOwnerDocument,
        String qualifiedName)
    {
        super(myOwnerDocument, qualifiedName);
    }

    /**
     * Constructor for JDFAutoDisposition
     * @param myOwnerDocument
     * @param myNamespaceURI
     * @param qualifiedName
     */
    protected JDFAutoDisposition(
        CoreDocumentImpl myOwnerDocument,
        String myNamespaceURI,
        String qualifiedName)
    {
        super(myOwnerDocument, myNamespaceURI, qualifiedName);
    }

    /**
     * Constructor for JDFAutoDisposition
     * @param myOwnerDocument
     * @param myNamespaceURI
     * @param qualifiedName
     * @param myLocalName
     */
    protected JDFAutoDisposition(
        CoreDocumentImpl myOwnerDocument,
        String myNamespaceURI,
        String qualifiedName,
        String myLocalName)
    {
        super(myOwnerDocument, myNamespaceURI, qualifiedName, myLocalName);
    }


    public String toString()
    {
        return " JDFAutoDisposition[  --> " + super.toString() + " ]";
    }


        /**
        * Enumeration strings for DispositionAction
        */

        public static class EnumDispositionAction extends ValuedEnum
        {
            private static final long serialVersionUID = 1L;
            private static int m_startValue = 0;

            private EnumDispositionAction(String name)
            {
                super(name, m_startValue++);
            }

            public static EnumDispositionAction getEnum(String enumName)
            {
                return (EnumDispositionAction) getEnum(EnumDispositionAction.class, enumName);
            }

            public static EnumDispositionAction getEnum(int enumValue)
            {
                return (EnumDispositionAction) getEnum(EnumDispositionAction.class, enumValue);
            }

            public static Map getEnumMap()
            {
                return getEnumMap(EnumDispositionAction.class);
            }

            public static List getEnumList()
            {
                return getEnumList(EnumDispositionAction.class);
            }

            public static Iterator iterator()
            {
                return iterator(EnumDispositionAction.class);
            }

            public static final EnumDispositionAction Delete = new EnumDispositionAction("Delete");
            public static final EnumDispositionAction Archive = new EnumDispositionAction("Archive");
        }      



        /**
        * Enumeration strings for DispositionUsage
        */

        public static class EnumDispositionUsage extends ValuedEnum
        {
            private static final long serialVersionUID = 1L;
            private static int m_startValue = 0;

            private EnumDispositionUsage(String name)
            {
                super(name, m_startValue++);
            }

            public static EnumDispositionUsage getEnum(String enumName)
            {
                return (EnumDispositionUsage) getEnum(EnumDispositionUsage.class, enumName);
            }

            public static EnumDispositionUsage getEnum(int enumValue)
            {
                return (EnumDispositionUsage) getEnum(EnumDispositionUsage.class, enumValue);
            }

            public static Map getEnumMap()
            {
                return getEnumMap(EnumDispositionUsage.class);
            }

            public static List getEnumList()
            {
                return getEnumList(EnumDispositionUsage.class);
            }

            public static Iterator iterator()
            {
                return iterator(EnumDispositionUsage.class);
            }

            public static final EnumDispositionUsage Input = new EnumDispositionUsage("Input");
            public static final EnumDispositionUsage Output = new EnumDispositionUsage("Output");
        }      



/* ************************************************************************
 * Attribute getter / setter
 * ************************************************************************
 */
        
        /* ---------------------------------------------------------------------
        Methods for Attribute DispositionAction
        --------------------------------------------------------------------- */
        /**
          * (5) set attribute DispositionAction
          * @param enumVar: the enumVar to set the attribute to
          */
        public void setDispositionAction(EnumDispositionAction enumVar)
        {
            setAttribute(AttributeName.DISPOSITIONACTION, enumVar.getName(), null);
        }

        /**
          * (9) get attribute DispositionAction
          * @return the value of the attribute
          */
        public EnumDispositionAction getDispositionAction()
        {
            return EnumDispositionAction.getEnum(getAttribute(AttributeName.DISPOSITIONACTION, null, "Delete"));
        }

        
        /* ---------------------------------------------------------------------
        Methods for Attribute Priority
        --------------------------------------------------------------------- */
        /**
          * (36) set attribute Priority
          * @param value: the value to set the attribute to
          */
        public void setPriority(int value)
        {
            setAttribute(AttributeName.PRIORITY, value, null);
        }

        /**
          * (15) get int attribute Priority
          * @return int the value of the attribute
          */
        public int getPriority()
        {
            return getIntAttribute(AttributeName.PRIORITY, null, 0);
        }

        
        /* ---------------------------------------------------------------------
        Methods for Attribute DispositionUsage
        --------------------------------------------------------------------- */
        /**
          * (5) set attribute DispositionUsage
          * @param enumVar: the enumVar to set the attribute to
          */
        public void setDispositionUsage(EnumDispositionUsage enumVar)
        {
            setAttribute(AttributeName.DISPOSITIONUSAGE, enumVar.getName(), null);
        }

        /**
          * (9) get attribute DispositionUsage
          * @return the value of the attribute
          */
        public EnumDispositionUsage getDispositionUsage()
        {
            return EnumDispositionUsage.getEnum(getAttribute(AttributeName.DISPOSITIONUSAGE, null, null));
        }

        
        /* ---------------------------------------------------------------------
        Methods for Attribute ExtraDuration
        --------------------------------------------------------------------- */
        /**
          * (36) set attribute ExtraDuration
          * @param value: the value to set the attribute to
          */
        public void setExtraDuration(JDFDuration value)
        {
            setAttribute(AttributeName.EXTRADURATION, value, null);
        }

        /**
          * (20) get JDFDuration attribute ExtraDuration
          * @return JDFDuration the value of the attribute, null if a the
          *         attribute value is not a valid to create a JDFDuration
          */
        public JDFDuration getExtraDuration()
        {
            String strAttrName = "";
            JDFDuration nPlaceHolder = null;
            strAttrName = getAttribute(AttributeName.EXTRADURATION, null, JDFConstants.EMPTYSTRING);
            try
            {
                nPlaceHolder = new JDFDuration(strAttrName);
            }
            catch(DataFormatException e)
            {
                return null;
            }
            return nPlaceHolder;
        }

        
        /* ---------------------------------------------------------------------
        Methods for Attribute MinDuration
        --------------------------------------------------------------------- */
        /**
          * (36) set attribute MinDuration
          * @param value: the value to set the attribute to
          */
        public void setMinDuration(JDFDuration value)
        {
            setAttribute(AttributeName.MINDURATION, value, null);
        }

        /**
          * (20) get JDFDuration attribute MinDuration
          * @return JDFDuration the value of the attribute, null if a the
          *         attribute value is not a valid to create a JDFDuration
          */
        public JDFDuration getMinDuration()
        {
            String strAttrName = "";
            JDFDuration nPlaceHolder = null;
            strAttrName = getAttribute(AttributeName.MINDURATION, null, JDFConstants.EMPTYSTRING);
            try
            {
                nPlaceHolder = new JDFDuration(strAttrName);
            }
            catch(DataFormatException e)
            {
                return null;
            }
            return nPlaceHolder;
        }

        
        /* ---------------------------------------------------------------------
        Methods for Attribute Until
        --------------------------------------------------------------------- */
        /**
          * (10) set attribute Until
          * @deprecated 2005-12-02 use setUntil(null)
          */
        public void setUntil()
        {
            setAttribute(AttributeName.UNTIL, new JDFDate().getDateTimeISO(), null);
        }

        /**
          * (11) set attribute Until
          * @param value: the value to set the attribute to or null
          */
        public void setUntil(JDFDate value)
        {
            if (value == null) value = new JDFDate();
            setAttribute(AttributeName.UNTIL, value.getDateTimeISO(), null);
        }

        /**
          * (12) get JDFDate attribute Until
          * @return JDFDate the value of the attribute
          */
        public JDFDate getUntil()
        {
            JDFDate nMyDate = null;
            String str = JDFConstants.EMPTYSTRING;
            str = getAttribute(AttributeName.UNTIL, null, JDFConstants.EMPTYSTRING);
            if (!JDFConstants.EMPTYSTRING.equals(str))
            {
                try
                {
                    nMyDate = new JDFDate(str);
                }
                catch(DataFormatException dfe)
                {
                    throw new JDFException("not a valid date string. Malformed JDF");
                }
            }
            return nMyDate;
        }

}// end namespace JDF
