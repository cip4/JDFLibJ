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
import org.cip4.jdflib.datatypes.JDFIntegerList;
import org.cip4.jdflib.resource.process.JDFIDPFolding;
import org.cip4.jdflib.resource.process.JDFIDPHoleMaking;
import org.cip4.jdflib.resource.process.JDFIDPStitching;
import org.cip4.jdflib.resource.process.JDFIDPTrimming;

public abstract class JDFAutoIDPFinishing extends JDFElement
{

    private static final long serialVersionUID = 1L;

    private static AtrInfoTable[] atrInfoTable = new AtrInfoTable[1];
    static
    {
        atrInfoTable[0] = new AtrInfoTable(AttributeName.FINISHINGS, 0x33333333, AttributeInfo.EnumAttributeType.IntegerList, null, null);
    }
    
    @Override
	protected AttributeInfo getTheAttributeInfo()
    {
        return super.getTheAttributeInfo().updateReplace(atrInfoTable);
    }


    private static ElemInfoTable[] elemInfoTable = new ElemInfoTable[4];
    static
    {
        elemInfoTable[0] = new ElemInfoTable(ElementName.IDPFOLDING, 0x33333333);
        elemInfoTable[1] = new ElemInfoTable(ElementName.IDPHOLEMAKING, 0x33333333);
        elemInfoTable[2] = new ElemInfoTable(ElementName.IDPSTITCHING, 0x33333333);
        elemInfoTable[3] = new ElemInfoTable(ElementName.IDPTRIMMING, 0x33333333);
    }
    
    @Override
	protected ElementInfo getTheElementInfo()
    {
        return super.getTheElementInfo().updateReplace(elemInfoTable);
    }



    /**
     * Constructor for JDFAutoIDPFinishing
     * @param myOwnerDocument
     * @param qualifiedName
     */
    protected JDFAutoIDPFinishing(
        CoreDocumentImpl myOwnerDocument,
        String qualifiedName)
    {
        super(myOwnerDocument, qualifiedName);
    }

    /**
     * Constructor for JDFAutoIDPFinishing
     * @param myOwnerDocument
     * @param myNamespaceURI
     * @param qualifiedName
     */
    protected JDFAutoIDPFinishing(
        CoreDocumentImpl myOwnerDocument,
        String myNamespaceURI,
        String qualifiedName)
    {
        super(myOwnerDocument, myNamespaceURI, qualifiedName);
    }

    /**
     * Constructor for JDFAutoIDPFinishing
     * @param myOwnerDocument
     * @param myNamespaceURI
     * @param qualifiedName
     * @param myLocalName
     */
    protected JDFAutoIDPFinishing(
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
        return " JDFAutoIDPFinishing[  --> " + super.toString() + " ]";
    }


/* ************************************************************************
 * Attribute getter / setter
 * ************************************************************************
 */
        
        /* ---------------------------------------------------------------------
        Methods for Attribute Finishings
        --------------------------------------------------------------------- */
        /**
          * (36) set attribute Finishings
          * @param value: the value to set the attribute to
          */
        public void setFinishings(JDFIntegerList value)
        {
            setAttribute(AttributeName.FINISHINGS, value, null);
        }

        /**
          * (20) get JDFIntegerList attribute Finishings
          * @return JDFIntegerList the value of the attribute, null if a the
          *         attribute value is not a valid to create a JDFIntegerList
          */
        public JDFIntegerList getFinishings()
        {
            String strAttrName = "";
            JDFIntegerList nPlaceHolder = null;
            strAttrName = getAttribute(AttributeName.FINISHINGS, null, JDFConstants.EMPTYSTRING);
            try
            {
                nPlaceHolder = new JDFIntegerList(strAttrName);
            }
            catch(DataFormatException e)
            {
                return null;
            }
            return nPlaceHolder;
        }

/* ***********************************************************************
 * Element getter / setter
 * ***********************************************************************
 */

    /** (26) getCreateIDPFolding
     * 
     * @param iSkip number of elements to skip
     * @return JDFIDPFolding the element
     */
    public JDFIDPFolding getCreateIDPFolding(int iSkip)
    {
        return (JDFIDPFolding)getCreateElement_KElement(ElementName.IDPFOLDING, null, iSkip);
    }

    /**
     * (27) const get element IDPFolding
     * @param iSkip number of elements to skip
     * @return JDFIDPFolding the element
     * default is getIDPFolding(0)     */
    public JDFIDPFolding getIDPFolding(int iSkip)
    {
        return (JDFIDPFolding) getElement(ElementName.IDPFOLDING, null, iSkip);
    }

    /**
     * Get all IDPFolding from the current element
     * 
     * @return Collection<JDFIDPFolding>
     */
    public Collection<JDFIDPFolding> getAllIDPFolding()
    {
        Vector<JDFIDPFolding> v = new Vector<JDFIDPFolding>();

        JDFIDPFolding kElem = (JDFIDPFolding) getFirstChildElement(ElementName.IDPFOLDING, null);

        while (kElem != null)
        {
            v.add(kElem);

            kElem = (JDFIDPFolding) kElem.getNextSiblingElement(ElementName.IDPFOLDING, null);
        }

        return v;
    }

    /**
     * (30) append element IDPFolding
     */
    public JDFIDPFolding appendIDPFolding() throws JDFException
    {
        return (JDFIDPFolding) appendElement(ElementName.IDPFOLDING, null);
    }

    /** (26) getCreateIDPHoleMaking
     * 
     * @param iSkip number of elements to skip
     * @return JDFIDPHoleMaking the element
     */
    public JDFIDPHoleMaking getCreateIDPHoleMaking(int iSkip)
    {
        return (JDFIDPHoleMaking)getCreateElement_KElement(ElementName.IDPHOLEMAKING, null, iSkip);
    }

    /**
     * (27) const get element IDPHoleMaking
     * @param iSkip number of elements to skip
     * @return JDFIDPHoleMaking the element
     * default is getIDPHoleMaking(0)     */
    public JDFIDPHoleMaking getIDPHoleMaking(int iSkip)
    {
        return (JDFIDPHoleMaking) getElement(ElementName.IDPHOLEMAKING, null, iSkip);
    }

    /**
     * Get all IDPHoleMaking from the current element
     * 
     * @return Collection<JDFIDPHoleMaking>
     */
    public Collection<JDFIDPHoleMaking> getAllIDPHoleMaking()
    {
        Vector<JDFIDPHoleMaking> v = new Vector<JDFIDPHoleMaking>();

        JDFIDPHoleMaking kElem = (JDFIDPHoleMaking) getFirstChildElement(ElementName.IDPHOLEMAKING, null);

        while (kElem != null)
        {
            v.add(kElem);

            kElem = (JDFIDPHoleMaking) kElem.getNextSiblingElement(ElementName.IDPHOLEMAKING, null);
        }

        return v;
    }

    /**
     * (30) append element IDPHoleMaking
     */
    public JDFIDPHoleMaking appendIDPHoleMaking() throws JDFException
    {
        return (JDFIDPHoleMaking) appendElement(ElementName.IDPHOLEMAKING, null);
    }

    /** (26) getCreateIDPStitching
     * 
     * @param iSkip number of elements to skip
     * @return JDFIDPStitching the element
     */
    public JDFIDPStitching getCreateIDPStitching(int iSkip)
    {
        return (JDFIDPStitching)getCreateElement_KElement(ElementName.IDPSTITCHING, null, iSkip);
    }

    /**
     * (27) const get element IDPStitching
     * @param iSkip number of elements to skip
     * @return JDFIDPStitching the element
     * default is getIDPStitching(0)     */
    public JDFIDPStitching getIDPStitching(int iSkip)
    {
        return (JDFIDPStitching) getElement(ElementName.IDPSTITCHING, null, iSkip);
    }

    /**
     * Get all IDPStitching from the current element
     * 
     * @return Collection<JDFIDPStitching>
     */
    public Collection<JDFIDPStitching> getAllIDPStitching()
    {
        Vector<JDFIDPStitching> v = new Vector<JDFIDPStitching>();

        JDFIDPStitching kElem = (JDFIDPStitching) getFirstChildElement(ElementName.IDPSTITCHING, null);

        while (kElem != null)
        {
            v.add(kElem);

            kElem = (JDFIDPStitching) kElem.getNextSiblingElement(ElementName.IDPSTITCHING, null);
        }

        return v;
    }

    /**
     * (30) append element IDPStitching
     */
    public JDFIDPStitching appendIDPStitching() throws JDFException
    {
        return (JDFIDPStitching) appendElement(ElementName.IDPSTITCHING, null);
    }

    /** (26) getCreateIDPTrimming
     * 
     * @param iSkip number of elements to skip
     * @return JDFIDPTrimming the element
     */
    public JDFIDPTrimming getCreateIDPTrimming(int iSkip)
    {
        return (JDFIDPTrimming)getCreateElement_KElement(ElementName.IDPTRIMMING, null, iSkip);
    }

    /**
     * (27) const get element IDPTrimming
     * @param iSkip number of elements to skip
     * @return JDFIDPTrimming the element
     * default is getIDPTrimming(0)     */
    public JDFIDPTrimming getIDPTrimming(int iSkip)
    {
        return (JDFIDPTrimming) getElement(ElementName.IDPTRIMMING, null, iSkip);
    }

    /**
     * Get all IDPTrimming from the current element
     * 
     * @return Collection<JDFIDPTrimming>
     */
    public Collection<JDFIDPTrimming> getAllIDPTrimming()
    {
        Vector<JDFIDPTrimming> v = new Vector<JDFIDPTrimming>();

        JDFIDPTrimming kElem = (JDFIDPTrimming) getFirstChildElement(ElementName.IDPTRIMMING, null);

        while (kElem != null)
        {
            v.add(kElem);

            kElem = (JDFIDPTrimming) kElem.getNextSiblingElement(ElementName.IDPTRIMMING, null);
        }

        return v;
    }

    /**
     * (30) append element IDPTrimming
     */
    public JDFIDPTrimming appendIDPTrimming() throws JDFException
    {
        return (JDFIDPTrimming) appendElement(ElementName.IDPTRIMMING, null);
    }

}// end namespace JDF
