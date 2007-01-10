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

import org.apache.xerces.dom.CoreDocumentImpl;
import org.cip4.jdflib.core.AtrInfoTable;
import org.cip4.jdflib.core.AttributeInfo;
import org.cip4.jdflib.core.AttributeName;
import org.cip4.jdflib.core.ElemInfoTable;
import org.cip4.jdflib.core.ElementInfo;
import org.cip4.jdflib.core.ElementName;
import org.cip4.jdflib.core.JDFElement;
import org.cip4.jdflib.core.JDFException;
import org.cip4.jdflib.jmf.JDFIDInfo;
    /*
    *****************************************************************************
    class JDFAutoNewJDFCmdParams : public JDFElement

    *****************************************************************************
    */

public abstract class JDFAutoNewJDFCmdParams extends JDFElement
{

    private static final long serialVersionUID = 1L;

    private static AtrInfoTable[] atrInfoTable = new AtrInfoTable[1];
    static
    {
        atrInfoTable[0] = new AtrInfoTable(AttributeName.JDFDETAILS, 0x33333311, AttributeInfo.EnumAttributeType.string, null, "Brief");
    }
    
    protected AttributeInfo getTheAttributeInfo()
    {
        return super.getTheAttributeInfo().updateReplace(atrInfoTable);
    }


    private static ElemInfoTable[] elemInfoTable = new ElemInfoTable[1];
    static
    {
        elemInfoTable[0] = new ElemInfoTable(ElementName.IDINFO, 0x55555511);
    }
    
    protected ElementInfo getTheElementInfo()
    {
        return super.getTheElementInfo().updateReplace(elemInfoTable);
    }



    /**
     * Constructor for JDFAutoNewJDFCmdParams
     * @param myOwnerDocument
     * @param qualifiedName
     */
    protected JDFAutoNewJDFCmdParams(
        CoreDocumentImpl myOwnerDocument,
        String qualifiedName)
    {
        super(myOwnerDocument, qualifiedName);
    }

    /**
     * Constructor for JDFAutoNewJDFCmdParams
     * @param myOwnerDocument
     * @param myNamespaceURI
     * @param qualifiedName
     */
    protected JDFAutoNewJDFCmdParams(
        CoreDocumentImpl myOwnerDocument,
        String myNamespaceURI,
        String qualifiedName)
    {
        super(myOwnerDocument, myNamespaceURI, qualifiedName);
    }

    /**
     * Constructor for JDFAutoNewJDFCmdParams
     * @param myOwnerDocument
     * @param myNamespaceURI
     * @param qualifiedName
     * @param myLocalName
     */
    protected JDFAutoNewJDFCmdParams(
        CoreDocumentImpl myOwnerDocument,
        String myNamespaceURI,
        String qualifiedName,
        String myLocalName)
    {
        super(myOwnerDocument, myNamespaceURI, qualifiedName, myLocalName);
    }


    public String toString()
    {
        return " JDFAutoNewJDFCmdParams[  --> " + super.toString() + " ]";
    }


/* ************************************************************************
 * Attribute getter / setter
 * ************************************************************************
 */
        
        /* ---------------------------------------------------------------------
        Methods for Attribute JDFDetails
        --------------------------------------------------------------------- */
        /**
          * (36) set attribute JDFDetails
          * @param value: the value to set the attribute to
          */
        public void setJDFDetails(String value)
        {
            setAttribute(AttributeName.JDFDETAILS, value, null);
        }



        /**
          * (23) get String attribute JDFDetails
          * @return the value of the attribute
          */
        public String getJDFDetails()
        {
            return getAttribute(AttributeName.JDFDETAILS, null, "Brief");
        }



/* ***********************************************************************
 * Element getter / setter
 * ***********************************************************************
 */

    /**
     * (24) const get element IDInfo
     * @return JDFIDInfo the element
     */
    public JDFIDInfo getIDInfo()
    {
        return (JDFIDInfo) getElement(ElementName.IDINFO, null, 0);
    }



    /** (25) getCreateIDInfo
     * 
     * @return JDFIDInfo the element
     */
    public JDFIDInfo getCreateIDInfo()
    {
        return (JDFIDInfo) getCreateElement_KElement(ElementName.IDINFO, null, 0);
    }





    /**
     * (29) append elementIDInfo
     */
    public JDFIDInfo appendIDInfo() throws JDFException
    {
        return (JDFIDInfo) appendElementN(ElementName.IDINFO, 1, null);
    }
}// end namespace JDF
