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

import java.util.Collection;
import java.util.Vector;

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
import org.cip4.jdflib.core.VString;
import org.cip4.jdflib.resource.process.JDFComChannel;

public abstract class JDFAutoCustomerMessage extends JDFElement
{

    private static final long serialVersionUID = 1L;

    private static AtrInfoTable[] atrInfoTable = new AtrInfoTable[3];
    static
    {
        atrInfoTable[0] = new AtrInfoTable(AttributeName.LANGUAGE, 0x33333311, AttributeInfo.EnumAttributeType.language, null, null);
        atrInfoTable[1] = new AtrInfoTable(AttributeName.MESSAGEEVENTS, 0x22222211, AttributeInfo.EnumAttributeType.NMTOKENS, null, null);
        atrInfoTable[2] = new AtrInfoTable(AttributeName.SHOWLIST, 0x33333311, AttributeInfo.EnumAttributeType.NMTOKENS, null, null);
    }
    
    @Override
	protected AttributeInfo getTheAttributeInfo()
    {
        return super.getTheAttributeInfo().updateReplace(atrInfoTable);
    }


    private static ElemInfoTable[] elemInfoTable = new ElemInfoTable[1];
    static
    {
        elemInfoTable[0] = new ElemInfoTable(ElementName.COMCHANNEL, 0x33333311);
    }
    
    @Override
	protected ElementInfo getTheElementInfo()
    {
        return super.getTheElementInfo().updateReplace(elemInfoTable);
    }



    /**
     * Constructor for JDFAutoCustomerMessage
     * @param myOwnerDocument
     * @param qualifiedName
     */
    protected JDFAutoCustomerMessage(
        CoreDocumentImpl myOwnerDocument,
        String qualifiedName)
    {
        super(myOwnerDocument, qualifiedName);
    }

    /**
     * Constructor for JDFAutoCustomerMessage
     * @param myOwnerDocument
     * @param myNamespaceURI
     * @param qualifiedName
     */
    protected JDFAutoCustomerMessage(
        CoreDocumentImpl myOwnerDocument,
        String myNamespaceURI,
        String qualifiedName)
    {
        super(myOwnerDocument, myNamespaceURI, qualifiedName);
    }

    /**
     * Constructor for JDFAutoCustomerMessage
     * @param myOwnerDocument
     * @param myNamespaceURI
     * @param qualifiedName
     * @param myLocalName
     */
    protected JDFAutoCustomerMessage(
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
        return " JDFAutoCustomerMessage[  --> " + super.toString() + " ]";
    }


/* ************************************************************************
 * Attribute getter / setter
 * ************************************************************************
 */
        
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
        Methods for Attribute MessageEvents
        --------------------------------------------------------------------- */
        /**
          * (36) set attribute MessageEvents
          * @param value: the value to set the attribute to
          */
        public void setMessageEvents(VString value)
        {
            setAttribute(AttributeName.MESSAGEEVENTS, value, null);
        }

        /**
          * (21) get VString attribute MessageEvents
          * @return VString the value of the attribute
          */
        public VString getMessageEvents()
        {
            VString vStrAttrib = new VString();
            String  s = getAttribute(AttributeName.MESSAGEEVENTS, null, JDFConstants.EMPTYSTRING);
            vStrAttrib.setAllStrings(s, " ");
            return vStrAttrib;
        }

        
        /* ---------------------------------------------------------------------
        Methods for Attribute ShowList
        --------------------------------------------------------------------- */
        /**
          * (36) set attribute ShowList
          * @param value: the value to set the attribute to
          */
        public void setShowList(VString value)
        {
            setAttribute(AttributeName.SHOWLIST, value, null);
        }

        /**
          * (21) get VString attribute ShowList
          * @return VString the value of the attribute
          */
        public VString getShowList()
        {
            VString vStrAttrib = new VString();
            String  s = getAttribute(AttributeName.SHOWLIST, null, JDFConstants.EMPTYSTRING);
            vStrAttrib.setAllStrings(s, " ");
            return vStrAttrib;
        }

/* ***********************************************************************
 * Element getter / setter
 * ***********************************************************************
 */

    /** (26) getCreateComChannel
     * 
     * @param iSkip number of elements to skip
     * @return JDFComChannel the element
     */
    public JDFComChannel getCreateComChannel(int iSkip)
    {
        return (JDFComChannel)getCreateElement_KElement(ElementName.COMCHANNEL, null, iSkip);
    }

    /**
     * (27) const get element ComChannel
     * @param iSkip number of elements to skip
     * @return JDFComChannel the element
     * default is getComChannel(0)     */
    public JDFComChannel getComChannel(int iSkip)
    {
        return (JDFComChannel) getElement(ElementName.COMCHANNEL, null, iSkip);
    }

    /**
     * Get all ComChannel from the current element
     * 
     * @return Collection<JDFComChannel>
     */
    public Collection<JDFComChannel> getAllComChannel()
    {
        Vector<JDFComChannel> v = new Vector<JDFComChannel>();

        JDFComChannel kElem = (JDFComChannel) getFirstChildElement(ElementName.COMCHANNEL, null);

        while (kElem != null)
        {
            v.add(kElem);

            kElem = (JDFComChannel) kElem.getNextSiblingElement(ElementName.COMCHANNEL, null);
        }

        return v;
    }

    /**
     * (30) append element ComChannel
     */
    public JDFComChannel appendComChannel() throws JDFException
    {
        return (JDFComChannel) appendElement(ElementName.COMCHANNEL, null);
    }

    /**
      * (31) create inter-resource link to refTarget
      * @param refTarget the element that is referenced
      */
    public void refComChannel(JDFComChannel refTarget)
    {
        refElement(refTarget);
    }

}// end namespace JDF
