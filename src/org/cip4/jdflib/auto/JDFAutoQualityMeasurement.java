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
import org.cip4.jdflib.core.ElemInfoTable;
import org.cip4.jdflib.core.ElementInfo;
import org.cip4.jdflib.core.ElementName;
import org.cip4.jdflib.core.JDFConstants;
import org.cip4.jdflib.core.JDFElement;
import org.cip4.jdflib.core.JDFException;
import org.cip4.jdflib.resource.process.JDFBindingQualityParams;
import org.cip4.jdflib.util.JDFDate;

public abstract class JDFAutoQualityMeasurement extends JDFElement
{

    private static final long serialVersionUID = 1L;

    private static AtrInfoTable[] atrInfoTable = new AtrInfoTable[4];
    static
    {
        atrInfoTable[0] = new AtrInfoTable(AttributeName.END, 0x33333311, AttributeInfo.EnumAttributeType.dateTime, null, null);
        atrInfoTable[1] = new AtrInfoTable(AttributeName.FAILED, 0x33333311, AttributeInfo.EnumAttributeType.integer, null, null);
        atrInfoTable[2] = new AtrInfoTable(AttributeName.PASSED, 0x33333311, AttributeInfo.EnumAttributeType.integer, null, null);
        atrInfoTable[3] = new AtrInfoTable(AttributeName.START, 0x33333311, AttributeInfo.EnumAttributeType.dateTime, null, null);
    }
    
    @Override
	protected AttributeInfo getTheAttributeInfo()
    {
        return super.getTheAttributeInfo().updateReplace(atrInfoTable);
    }


    private static ElemInfoTable[] elemInfoTable = new ElemInfoTable[1];
    static
    {
        elemInfoTable[0] = new ElemInfoTable(ElementName.BINDINGQUALITYMEASUREMENT, 0x66666611);
    }
    
    @Override
	protected ElementInfo getTheElementInfo()
    {
        return super.getTheElementInfo().updateReplace(elemInfoTable);
    }



    /**
     * Constructor for JDFAutoQualityMeasurement
     * @param myOwnerDocument
     * @param qualifiedName
     */
    protected JDFAutoQualityMeasurement(
        CoreDocumentImpl myOwnerDocument,
        String qualifiedName)
    {
        super(myOwnerDocument, qualifiedName);
    }

    /**
     * Constructor for JDFAutoQualityMeasurement
     * @param myOwnerDocument
     * @param myNamespaceURI
     * @param qualifiedName
     */
    protected JDFAutoQualityMeasurement(
        CoreDocumentImpl myOwnerDocument,
        String myNamespaceURI,
        String qualifiedName)
    {
        super(myOwnerDocument, myNamespaceURI, qualifiedName);
    }

    /**
     * Constructor for JDFAutoQualityMeasurement
     * @param myOwnerDocument
     * @param myNamespaceURI
     * @param qualifiedName
     * @param myLocalName
     */
    protected JDFAutoQualityMeasurement(
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
        return " JDFAutoQualityMeasurement[  --> " + super.toString() + " ]";
    }


/* ************************************************************************
 * Attribute getter / setter
 * ************************************************************************
 */
        
        /* ---------------------------------------------------------------------
        Methods for Attribute End
        --------------------------------------------------------------------- */
        /**
          * (11) set attribute End
          * @param value: the value to set the attribute to or null
          */
        public void setEnd(JDFDate value)
        {
            JDFDate date = value;
            if (date == null) date = new JDFDate();
            setAttribute(AttributeName.END, date.getDateTimeISO(), null);
        }

        /**
          * (12) get JDFDate attribute End
          * @return JDFDate the value of the attribute
          */
        public JDFDate getEnd()
        {
            JDFDate nMyDate = null;
            String str = JDFConstants.EMPTYSTRING;
            str = getAttribute(AttributeName.END, null, JDFConstants.EMPTYSTRING);
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

        
        /* ---------------------------------------------------------------------
        Methods for Attribute Failed
        --------------------------------------------------------------------- */
        /**
          * (36) set attribute Failed
          * @param value: the value to set the attribute to
          */
        public void setFailed(int value)
        {
            setAttribute(AttributeName.FAILED, value, null);
        }

        /**
          * (15) get int attribute Failed
          * @return int the value of the attribute
          */
        public int getFailed()
        {
            return getIntAttribute(AttributeName.FAILED, null, 0);
        }

        
        /* ---------------------------------------------------------------------
        Methods for Attribute Passed
        --------------------------------------------------------------------- */
        /**
          * (36) set attribute Passed
          * @param value: the value to set the attribute to
          */
        public void setPassed(int value)
        {
            setAttribute(AttributeName.PASSED, value, null);
        }

        /**
          * (15) get int attribute Passed
          * @return int the value of the attribute
          */
        public int getPassed()
        {
            return getIntAttribute(AttributeName.PASSED, null, 0);
        }

        
        /* ---------------------------------------------------------------------
        Methods for Attribute Start
        --------------------------------------------------------------------- */
        /**
          * (11) set attribute Start
          * @param value: the value to set the attribute to or null
          */
        public void setStart(JDFDate value)
        {
            JDFDate date = value;
            if (date == null) date = new JDFDate();
            setAttribute(AttributeName.START, date.getDateTimeISO(), null);
        }

        /**
          * (12) get JDFDate attribute Start
          * @return JDFDate the value of the attribute
          */
        public JDFDate getStart()
        {
            JDFDate nMyDate = null;
            String str = JDFConstants.EMPTYSTRING;
            str = getAttribute(AttributeName.START, null, JDFConstants.EMPTYSTRING);
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

/* ***********************************************************************
 * Element getter / setter
 * ***********************************************************************
 */

    /**
     * (24) const get element BindingQualityMeasurement
     * @return JDFBindingQualityParams the element
     */
    public JDFBindingQualityParams getBindingQualityMeasurement()
    {
        return (JDFBindingQualityParams) getElement(ElementName.BINDINGQUALITYMEASUREMENT, null, 0);
    }

    /** (25) getCreateBindingQualityMeasurement
     * 
     * @return JDFBindingQualityParams the element
     */
    public JDFBindingQualityParams getCreateBindingQualityMeasurement()
    {
        return (JDFBindingQualityParams) getCreateElement_KElement(ElementName.BINDINGQUALITYMEASUREMENT, null, 0);
    }

    /**
     * (29) append element BindingQualityMeasurement
     */
    public JDFBindingQualityParams appendBindingQualityMeasurement() throws JDFException
    {
        return (JDFBindingQualityParams) appendElementN(ElementName.BINDINGQUALITYMEASUREMENT, 1, null);
    }

}// end namespace JDF
