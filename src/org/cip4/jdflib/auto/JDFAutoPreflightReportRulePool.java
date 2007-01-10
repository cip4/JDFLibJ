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
import org.cip4.jdflib.core.JDFConstants;
import org.cip4.jdflib.core.JDFException;
import org.cip4.jdflib.core.VString;
import org.cip4.jdflib.resource.JDFResource;
import org.cip4.jdflib.resource.process.JDFPRRule;
import org.cip4.jdflib.resource.process.JDFPRRuleAttr;
    /*
    *****************************************************************************
    class JDFAutoPreflightReportRulePool : public JDFResource

    *****************************************************************************
    */

public abstract class JDFAutoPreflightReportRulePool extends JDFResource
{

    private static final long serialVersionUID = 1L;

    private static AtrInfoTable[] atrInfoTable = new AtrInfoTable[2];
    static
    {
        atrInfoTable[0] = new AtrInfoTable(AttributeName.ACTIONPOOLS, 0x22222211, AttributeInfo.EnumAttributeType.IDREFS, null, null);
        atrInfoTable[1] = new AtrInfoTable(AttributeName.MAXOCCURRENCES, 0x33333311, AttributeInfo.EnumAttributeType.integer, null, null);
    }
    
    protected AttributeInfo getTheAttributeInfo()
    {
        return super.getTheAttributeInfo().updateReplace(atrInfoTable);
    }


    private static ElemInfoTable[] elemInfoTable = new ElemInfoTable[2];
    static
    {
        elemInfoTable[0] = new ElemInfoTable(ElementName.PRRULE, 0x33333311);
        elemInfoTable[1] = new ElemInfoTable(ElementName.PRRULEATTR, 0x66666611);
    }
    
    protected ElementInfo getTheElementInfo()
    {
        return super.getTheElementInfo().updateReplace(elemInfoTable);
    }



    /**
     * Constructor for JDFAutoPreflightReportRulePool
     * @param myOwnerDocument
     * @param qualifiedName
     */
    protected JDFAutoPreflightReportRulePool(
        CoreDocumentImpl myOwnerDocument,
        String qualifiedName)
    {
        super(myOwnerDocument, qualifiedName);
    }

    /**
     * Constructor for JDFAutoPreflightReportRulePool
     * @param myOwnerDocument
     * @param myNamespaceURI
     * @param qualifiedName
     */
    protected JDFAutoPreflightReportRulePool(
        CoreDocumentImpl myOwnerDocument,
        String myNamespaceURI,
        String qualifiedName)
    {
        super(myOwnerDocument, myNamespaceURI, qualifiedName);
    }

    /**
     * Constructor for JDFAutoPreflightReportRulePool
     * @param myOwnerDocument
     * @param myNamespaceURI
     * @param qualifiedName
     * @param myLocalName
     */
    protected JDFAutoPreflightReportRulePool(
        CoreDocumentImpl myOwnerDocument,
        String myNamespaceURI,
        String qualifiedName,
        String myLocalName)
    {
        super(myOwnerDocument, myNamespaceURI, qualifiedName, myLocalName);
    }


    public String toString()
    {
        return " JDFAutoPreflightReportRulePool[  --> " + super.toString() + " ]";
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


/* ************************************************************************
 * Attribute getter / setter
 * ************************************************************************
 */
        
        /* ---------------------------------------------------------------------
        Methods for Attribute ActionPools
        --------------------------------------------------------------------- */
        /**
          * (36) set attribute ActionPools
          * @param value: the value to set the attribute to
          */
        public void setActionPools(VString value)
        {
            setAttribute(AttributeName.ACTIONPOOLS, value, null);
        }



        /**
          * (21) get VString attribute ActionPools
          * @return VString the value of the attribute
          */
        public VString getActionPools()
        {
            VString vStrAttrib = new VString();
            String  s = getAttribute(AttributeName.ACTIONPOOLS, null, JDFConstants.EMPTYSTRING);
            vStrAttrib.setAllStrings(s, " ");
            return vStrAttrib;
        }



        
        /* ---------------------------------------------------------------------
        Methods for Attribute MaxOccurrences
        --------------------------------------------------------------------- */
        /**
          * (36) set attribute MaxOccurrences
          * @param value: the value to set the attribute to
          */
        public void setMaxOccurrences(int value)
        {
            setAttribute(AttributeName.MAXOCCURRENCES, value, null);
        }



        /**
          * (15) get int attribute MaxOccurrences
          * @return int the value of the attribute
          */
        public int getMaxOccurrences()
        {
            return getIntAttribute(AttributeName.MAXOCCURRENCES, null, 0);
        }



/* ***********************************************************************
 * Element getter / setter
 * ***********************************************************************
 */

    /** (26) getCreatePRRule
     * 
     * @param iSkip number of elements to skip
     * @return JDFPRRule the element
     */
    public JDFPRRule getCreatePRRule(int iSkip)
    {
        return (JDFPRRule)getCreateElement_KElement(ElementName.PRRULE, null, iSkip);
    }



    /**
     * (27) const get element PRRule
     * @param iSkip number of elements to skip
     * @return JDFPRRule the element
     * default is getPRRule(0)     */
    public JDFPRRule getPRRule(int iSkip)
    {
        return (JDFPRRule) getElement(ElementName.PRRULE, null, iSkip);
    }



    public JDFPRRule appendPRRule()
    {
        return (JDFPRRule) appendElement(ElementName.PRRULE, null);
    }

    /**
     * (24) const get element PRRuleAttr
     * @return JDFPRRuleAttr the element
     */
    public JDFPRRuleAttr getPRRuleAttr()
    {
        return (JDFPRRuleAttr) getElement(ElementName.PRRULEATTR, null, 0);
    }



    /** (25) getCreatePRRuleAttr
     * 
     * @return JDFPRRuleAttr the element
     */
    public JDFPRRuleAttr getCreatePRRuleAttr()
    {
        return (JDFPRRuleAttr) getCreateElement_KElement(ElementName.PRRULEATTR, null, 0);
    }





    /**
     * (29) append elementPRRuleAttr
     */
    public JDFPRRuleAttr appendPRRuleAttr() throws JDFException
    {
        return (JDFPRRuleAttr) appendElementN(ElementName.PRRULEATTR, 1, null);
    }
}// end namespace JDF
