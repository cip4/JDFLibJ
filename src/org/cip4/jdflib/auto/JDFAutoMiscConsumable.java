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
import org.cip4.jdflib.core.*;                      
import org.cip4.jdflib.resource.*;                  
import org.cip4.jdflib.resource.process.*;

public abstract class JDFAutoMiscConsumable extends JDFResource
{

    private static final long serialVersionUID = 1L;

    private static AtrInfoTable[] atrInfoTable = new AtrInfoTable[1];
    static
    {
        atrInfoTable[0] = new AtrInfoTable(AttributeName.CONSUMABLETYPE, 0x33333111, AttributeInfo.EnumAttributeType.NMTOKEN, null, null);
    }
    
    @Override
	protected AttributeInfo getTheAttributeInfo()
    {
        return super.getTheAttributeInfo().updateReplace(atrInfoTable);
    }


    private static ElemInfoTable[] elemInfoTable = new ElemInfoTable[2];
    static
    {
        elemInfoTable[0] = new ElemInfoTable(ElementName.CONTACT, 0x33333111);
        elemInfoTable[1] = new ElemInfoTable(ElementName.IDENTIFICATIONFIELD, 0x33333111);
    }
    
    @Override
	protected ElementInfo getTheElementInfo()
    {
        return super.getTheElementInfo().updateReplace(elemInfoTable);
    }



    /**
     * Constructor for JDFAutoMiscConsumable
     * @param myOwnerDocument
     * @param qualifiedName
     */
    protected JDFAutoMiscConsumable(
        CoreDocumentImpl myOwnerDocument,
        String qualifiedName)
    {
        super(myOwnerDocument, qualifiedName);
    }

    /**
     * Constructor for JDFAutoMiscConsumable
     * @param myOwnerDocument
     * @param myNamespaceURI
     * @param qualifiedName
     */
    protected JDFAutoMiscConsumable(
        CoreDocumentImpl myOwnerDocument,
        String myNamespaceURI,
        String qualifiedName)
    {
        super(myOwnerDocument, myNamespaceURI, qualifiedName);
    }

    /**
     * Constructor for JDFAutoMiscConsumable
     * @param myOwnerDocument
     * @param myNamespaceURI
     * @param qualifiedName
     * @param myLocalName
     */
    protected JDFAutoMiscConsumable(
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
        return " JDFAutoMiscConsumable[  --> " + super.toString() + " ]";
    }


    @Override
	public boolean  init()
    {
        boolean bRet = super.init();
        setResourceClass(JDFResource.EnumResourceClass.Consumable);
        return bRet;
    }


    @Override
	public EnumResourceClass getValidClass()
    {
        return JDFResource.EnumResourceClass.Consumable;
    }


/* ************************************************************************
 * Attribute getter / setter
 * ************************************************************************
 */
        
        /* ---------------------------------------------------------------------
        Methods for Attribute ConsumableType
        --------------------------------------------------------------------- */
        /**
          * (36) set attribute ConsumableType
          * @param value: the value to set the attribute to
          */
        public void setConsumableType(String value)
        {
            setAttribute(AttributeName.CONSUMABLETYPE, value, null);
        }

        /**
          * (23) get String attribute ConsumableType
          * @return the value of the attribute
          */
        public String getConsumableType()
        {
            return getAttribute(AttributeName.CONSUMABLETYPE, null, JDFConstants.EMPTYSTRING);
        }

/* ***********************************************************************
 * Element getter / setter
 * ***********************************************************************
 */

    /** (26) getCreateContact
     * 
     * @param iSkip number of elements to skip
     * @return JDFContact the element
     */
    public JDFContact getCreateContact(int iSkip)
    {
        return (JDFContact)getCreateElement_KElement(ElementName.CONTACT, null, iSkip);
    }

    /**
     * (27) const get element Contact
     * @param iSkip number of elements to skip
     * @return JDFContact the element
     * default is getContact(0)     */
    public JDFContact getContact(int iSkip)
    {
        return (JDFContact) getElement(ElementName.CONTACT, null, iSkip);
    }

    /**
     * Get all Contact from the current element
     * 
     * @return Collection<JDFContact>
     */
    public Collection<JDFContact> getAllContact()
    {
        Vector<JDFContact> v = new Vector<JDFContact>();

        JDFContact kElem = (JDFContact) getFirstChildElement(ElementName.CONTACT, null);

        while (kElem != null)
        {
            v.add(kElem);

            kElem = (JDFContact) kElem.getNextSiblingElement(ElementName.CONTACT, null);
        }

        return v;
    }

    /**
     * (30) append element Contact
     */
    @Override
	public JDFContact appendContact() throws JDFException
    {
        return (JDFContact) appendElement(ElementName.CONTACT, null);
    }

    /**
      * (31) create inter-resource link to refTarget
      * @param refTarget the element that is referenced
      */
    public void refContact(JDFContact refTarget)
    {
        refElement(refTarget);
    }

    /** (26) getCreateIdentificationField
     * 
     * @param iSkip number of elements to skip
     * @return JDFIdentificationField the element
     */
    @Override
	public JDFIdentificationField getCreateIdentificationField(int iSkip)
    {
        return (JDFIdentificationField)getCreateElement_KElement(ElementName.IDENTIFICATIONFIELD, null, iSkip);
    }

    /**
     * (27) const get element IdentificationField
     * @param iSkip number of elements to skip
     * @return JDFIdentificationField the element
     * default is getIdentificationField(0)     */
    @Override
	public JDFIdentificationField getIdentificationField(int iSkip)
    {
        return (JDFIdentificationField) getElement(ElementName.IDENTIFICATIONFIELD, null, iSkip);
    }

    /**
     * Get all IdentificationField from the current element
     * 
     * @return Collection<JDFIdentificationField>
     */
    public Collection<JDFIdentificationField> getAllIdentificationField()
    {
        Vector<JDFIdentificationField> v = new Vector<JDFIdentificationField>();

        JDFIdentificationField kElem = (JDFIdentificationField) getFirstChildElement(ElementName.IDENTIFICATIONFIELD, null);

        while (kElem != null)
        {
            v.add(kElem);

            kElem = (JDFIdentificationField) kElem.getNextSiblingElement(ElementName.IDENTIFICATIONFIELD, null);
        }

        return v;
    }

    /**
     * (30) append element IdentificationField
     */
    @Override
	public JDFIdentificationField appendIdentificationField() throws JDFException
    {
        return (JDFIdentificationField) appendElement(ElementName.IDENTIFICATIONFIELD, null);
    }

    /**
      * (31) create inter-resource link to refTarget
      * @param refTarget the element that is referenced
      */
    public void refIdentificationField(JDFIdentificationField refTarget)
    {
        refElement(refTarget);
    }

}// end namespace JDF
