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

import java.util.zip.DataFormatException;

import org.apache.xerces.dom.CoreDocumentImpl;
import org.cip4.jdflib.core.AtrInfoTable;
import org.cip4.jdflib.core.AttributeInfo;
import org.cip4.jdflib.core.AttributeName;
import org.cip4.jdflib.core.JDFConstants;
import org.cip4.jdflib.core.JDFElement;
import org.cip4.jdflib.datatypes.JDFRectangle;
import org.cip4.jdflib.util.JDFDate;

public abstract class JDFAutoComment extends JDFElement
{

    private static final long serialVersionUID = 1L;

    private static AtrInfoTable[] atrInfoTable = new AtrInfoTable[10];
    static
    {
        atrInfoTable[0] = new AtrInfoTable(AttributeName.AGENTNAME, 0x33333111, AttributeInfo.EnumAttributeType.string, null, null);
        atrInfoTable[1] = new AtrInfoTable(AttributeName.AGENTVERSION, 0x33333111, AttributeInfo.EnumAttributeType.string, null, null);
        atrInfoTable[2] = new AtrInfoTable(AttributeName.ATTRIBUTE, 0x33333331, AttributeInfo.EnumAttributeType.NMTOKEN, null, null);
        atrInfoTable[3] = new AtrInfoTable(AttributeName.AUTHOR, 0x33333111, AttributeInfo.EnumAttributeType.string, null, null);
        atrInfoTable[4] = new AtrInfoTable(AttributeName.BOX, 0x33333333, AttributeInfo.EnumAttributeType.rectangle, null, null);
        atrInfoTable[5] = new AtrInfoTable(AttributeName.ID, 0x33333111, AttributeInfo.EnumAttributeType.ID, null, null);
        atrInfoTable[6] = new AtrInfoTable(AttributeName.LANGUAGE, 0x33333333, AttributeInfo.EnumAttributeType.language, null, null);
        atrInfoTable[7] = new AtrInfoTable(AttributeName.NAME, 0x33333333, AttributeInfo.EnumAttributeType.NMTOKEN, null, "Description");
        atrInfoTable[8] = new AtrInfoTable(AttributeName.PATH, 0x33333333, AttributeInfo.EnumAttributeType.PDFPath, null, null);
        atrInfoTable[9] = new AtrInfoTable(AttributeName.TIMESTAMP, 0x33333111, AttributeInfo.EnumAttributeType.dateTime, null, null);
    }
    
    protected AttributeInfo getTheAttributeInfo()
    {
        return super.getTheAttributeInfo().updateReplace(atrInfoTable);
    }



    /**
     * Constructor for JDFAutoComment
     * @param myOwnerDocument
     * @param qualifiedName
     */
    protected JDFAutoComment(
        CoreDocumentImpl myOwnerDocument,
        String qualifiedName)
    {
        super(myOwnerDocument, qualifiedName);
    }

    /**
     * Constructor for JDFAutoComment
     * @param myOwnerDocument
     * @param myNamespaceURI
     * @param qualifiedName
     */
    protected JDFAutoComment(
        CoreDocumentImpl myOwnerDocument,
        String myNamespaceURI,
        String qualifiedName)
    {
        super(myOwnerDocument, myNamespaceURI, qualifiedName);
    }

    /**
     * Constructor for JDFAutoComment
     * @param myOwnerDocument
     * @param myNamespaceURI
     * @param qualifiedName
     * @param myLocalName
     */
    protected JDFAutoComment(
        CoreDocumentImpl myOwnerDocument,
        String myNamespaceURI,
        String qualifiedName,
        String myLocalName)
    {
        super(myOwnerDocument, myNamespaceURI, qualifiedName, myLocalName);
    }


    public String toString()
    {
        return " JDFAutoComment[  --> " + super.toString() + " ]";
    }


/* ************************************************************************
 * Attribute getter / setter
 * ************************************************************************
 */
        
        /* ---------------------------------------------------------------------
        Methods for Attribute AgentName
        --------------------------------------------------------------------- */
        /**
          * (36) set attribute AgentName
          * @param value: the value to set the attribute to
          */
        public void setAgentName(String value)
        {
            setAttribute(AttributeName.AGENTNAME, value, null);
        }

        /**
          * (23) get String attribute AgentName
          * @return the value of the attribute
          */
        public String getAgentName()
        {
            return getAttribute(AttributeName.AGENTNAME, null, JDFConstants.EMPTYSTRING);
        }

        
        /* ---------------------------------------------------------------------
        Methods for Attribute AgentVersion
        --------------------------------------------------------------------- */
        /**
          * (36) set attribute AgentVersion
          * @param value: the value to set the attribute to
          */
        public void setAgentVersion(String value)
        {
            setAttribute(AttributeName.AGENTVERSION, value, null);
        }

        /**
          * (23) get String attribute AgentVersion
          * @return the value of the attribute
          */
        public String getAgentVersion()
        {
            return getAttribute(AttributeName.AGENTVERSION, null, JDFConstants.EMPTYSTRING);
        }

        
        /* ---------------------------------------------------------------------
        Methods for Attribute Attribute
        --------------------------------------------------------------------- */
        /**
          * (36) set attribute Attribute
          * @param value: the value to set the attribute to
          */
        public void setAttributeJDF(String value)
        {
            setAttribute(AttributeName.ATTRIBUTE, value, null);
        }

        /**
          * (22) get String attribute Attribute
          * @return String the value of the attribute
          */
        public String getAttributeJDF()
        {
            return getAttribute(AttributeName.ATTRIBUTE, null, JDFConstants.EMPTYSTRING);
        }

        
        /* ---------------------------------------------------------------------
        Methods for Attribute Author
        --------------------------------------------------------------------- */
        /**
          * (36) set attribute Author
          * @param value: the value to set the attribute to
          */
        public void setAuthor(String value)
        {
            setAttribute(AttributeName.AUTHOR, value, null);
        }

        /**
          * (23) get String attribute Author
          * @return the value of the attribute
          */
        public String getAuthor()
        {
            return getAttribute(AttributeName.AUTHOR, null, JDFConstants.EMPTYSTRING);
        }

        
        /* ---------------------------------------------------------------------
        Methods for Attribute Box
        --------------------------------------------------------------------- */
        /**
          * (36) set attribute Box
          * @param value: the value to set the attribute to
          */
        public void setBox(JDFRectangle value)
        {
            setAttribute(AttributeName.BOX, value, null);
        }

        /**
          * (20) get JDFRectangle attribute Box
          * @return JDFRectangle the value of the attribute, null if a the
          *         attribute value is not a valid to create a JDFRectangle
          */
        public JDFRectangle getBox()
        {
            String strAttrName = "";
            JDFRectangle nPlaceHolder = null;
            strAttrName = getAttribute(AttributeName.BOX, null, JDFConstants.EMPTYSTRING);
            try
            {
                nPlaceHolder = new JDFRectangle(strAttrName);
            }
            catch(DataFormatException e)
            {
                return null;
            }
            return nPlaceHolder;
        }

        
        /* ---------------------------------------------------------------------
        Methods for Attribute ID
        --------------------------------------------------------------------- */
        /**
          * (36) set attribute ID
          * @param value: the value to set the attribute to
          */
        public void setID(String value)
        {
            setAttribute(AttributeName.ID, value, null);
        }

        /**
          * (23) get String attribute ID
          * @return the value of the attribute
          */
        public String getID()
        {
            return getAttribute(AttributeName.ID, null, JDFConstants.EMPTYSTRING);
        }

        
        /* ---------------------------------------------------------------------
        Methods for Attribute Language
        --------------------------------------------------------------------- */
        /**
          * (36) set attribute Language
          * @param value: the value to set the attribute to
          */
        public void setLanguage(String value)
        {
            setAttribute(AttributeName.LANGUAGE, value, null);
        }

        /**
          * (23) get String attribute Language
          * @return the value of the attribute
          */
        public String getLanguage()
        {
            return getAttribute(AttributeName.LANGUAGE, null, JDFConstants.EMPTYSTRING);
        }

        
        /* ---------------------------------------------------------------------
        Methods for Attribute Name
        --------------------------------------------------------------------- */
        /**
          * (36) set attribute Name
          * @param value: the value to set the attribute to
          */
        public void setName(String value)
        {
            setAttribute(AttributeName.NAME, value, null);
        }

        /**
          * (23) get String attribute Name
          * @return the value of the attribute
          */
        public String getName()
        {
            return getAttribute(AttributeName.NAME, null, "Description");
        }

        
        /* ---------------------------------------------------------------------
        Methods for Attribute Path
        --------------------------------------------------------------------- */
        /**
          * (36) set attribute Path
          * @param value: the value to set the attribute to
          */
        public void setPath(String value)
        {
            setAttribute(AttributeName.PATH, value, null);
        }

        /**
          * (23) get String attribute Path
          * @return the value of the attribute
          */
        public String getPath()
        {
            return getAttribute(AttributeName.PATH, null, JDFConstants.EMPTYSTRING);
        }

        
        /* ---------------------------------------------------------------------
        Methods for Attribute TimeStamp
        --------------------------------------------------------------------- */
        /**
          * (11) set attribute TimeStamp
          * @param value: the value to set the attribute to or null
          */
        public void setTimeStamp(JDFDate value)
        {
            JDFDate date = value;
            if (date == null) date = new JDFDate();
            setAttribute(AttributeName.TIMESTAMP, date.getDateTimeISO(), null);
        }

        /**
          * (12) get JDFDate attribute TimeStamp
          * @return JDFDate the value of the attribute
          */
        public JDFDate getTimeStamp()
        {
            JDFDate nMyDate = null;
            String str = JDFConstants.EMPTYSTRING;
            str = getAttribute(AttributeName.TIMESTAMP, null, JDFConstants.EMPTYSTRING);
            if (!JDFConstants.EMPTYSTRING.equals(str))
            {
                try
                {
                    nMyDate = new JDFDate(str);
                }
                catch(DataFormatException dfe)
                {
                    // throw new JDFException("not a valid date string. Malformed JDF - return null");
                }
            }
            return nMyDate;
        }

}// end namespace JDF
