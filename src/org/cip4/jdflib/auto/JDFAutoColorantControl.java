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
import org.cip4.jdflib.core.JDFException;
import org.cip4.jdflib.core.JDFSeparationList;
import org.cip4.jdflib.resource.JDFResource;
import org.cip4.jdflib.resource.process.JDFColorPool;
import org.cip4.jdflib.resource.process.JDFColorantAlias;
import org.cip4.jdflib.resource.process.JDFDeviceNSpace;
import org.cip4.jdflib.resource.process.prepress.JDFColorSpaceSubstitute;

public abstract class JDFAutoColorantControl extends JDFResource
{

    private static final long serialVersionUID = 1L;

    private static AtrInfoTable[] atrInfoTable = new AtrInfoTable[2];
    static
    {
        atrInfoTable[0] = new AtrInfoTable(AttributeName.FORCESEPARATIONS, 0x33333333, AttributeInfo.EnumAttributeType.boolean_, null, "false");
        atrInfoTable[1] = new AtrInfoTable(AttributeName.PROCESSCOLORMODEL, 0x33333333, AttributeInfo.EnumAttributeType.NMTOKEN, null, null);
    }
    
    protected AttributeInfo getTheAttributeInfo()
    {
        return super.getTheAttributeInfo().updateReplace(atrInfoTable);
    }


    private static ElemInfoTable[] elemInfoTable = new ElemInfoTable[7];
    static
    {
        elemInfoTable[0] = new ElemInfoTable(ElementName.COLORANTALIAS, 0x33333333);
        elemInfoTable[1] = new ElemInfoTable(ElementName.COLORANTORDER, 0x66666666);
        elemInfoTable[2] = new ElemInfoTable(ElementName.COLORANTPARAMS, 0x66666666);
        elemInfoTable[3] = new ElemInfoTable(ElementName.COLORPOOL, 0x66666666);
        elemInfoTable[4] = new ElemInfoTable(ElementName.COLORSPACESUBSTITUTE, 0x33333333);
        elemInfoTable[5] = new ElemInfoTable(ElementName.DEVICECOLORANTORDER, 0x66666666);
        elemInfoTable[6] = new ElemInfoTable(ElementName.DEVICENSPACE, 0x33333333);
    }
    
    protected ElementInfo getTheElementInfo()
    {
        return super.getTheElementInfo().updateReplace(elemInfoTable);
    }



    /**
     * Constructor for JDFAutoColorantControl
     * @param myOwnerDocument
     * @param qualifiedName
     */
    protected JDFAutoColorantControl(
        CoreDocumentImpl myOwnerDocument,
        String qualifiedName)
    {
        super(myOwnerDocument, qualifiedName);
    }

    /**
     * Constructor for JDFAutoColorantControl
     * @param myOwnerDocument
     * @param myNamespaceURI
     * @param qualifiedName
     */
    protected JDFAutoColorantControl(
        CoreDocumentImpl myOwnerDocument,
        String myNamespaceURI,
        String qualifiedName)
    {
        super(myOwnerDocument, myNamespaceURI, qualifiedName);
    }

    /**
     * Constructor for JDFAutoColorantControl
     * @param myOwnerDocument
     * @param myNamespaceURI
     * @param qualifiedName
     * @param myLocalName
     */
    protected JDFAutoColorantControl(
        CoreDocumentImpl myOwnerDocument,
        String myNamespaceURI,
        String qualifiedName,
        String myLocalName)
    {
        super(myOwnerDocument, myNamespaceURI, qualifiedName, myLocalName);
    }


    public String toString()
    {
        return " JDFAutoColorantControl[  --> " + super.toString() + " ]";
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
        Methods for Attribute ForceSeparations
        --------------------------------------------------------------------- */
        /**
          * (36) set attribute ForceSeparations
          * @param value: the value to set the attribute to
          */
        public void setForceSeparations(boolean value)
        {
            setAttribute(AttributeName.FORCESEPARATIONS, value, null);
        }

        /**
          * (18) get boolean attribute ForceSeparations
          * @return boolean the value of the attribute
          */
        public boolean getForceSeparations()
        {
            return getBoolAttribute(AttributeName.FORCESEPARATIONS, null, false);
        }

        
        /* ---------------------------------------------------------------------
        Methods for Attribute ProcessColorModel
        --------------------------------------------------------------------- */
        /**
          * (36) set attribute ProcessColorModel
          * @param value: the value to set the attribute to
          */
        public void setProcessColorModel(String value)
        {
            setAttribute(AttributeName.PROCESSCOLORMODEL, value, null);
        }

        /**
          * (23) get String attribute ProcessColorModel
          * @return the value of the attribute
          */
        public String getProcessColorModel()
        {
            return getAttribute(AttributeName.PROCESSCOLORMODEL, null, JDFConstants.EMPTYSTRING);
        }

/* ***********************************************************************
 * Element getter / setter
 * ***********************************************************************
 */

    /** (26) getCreateColorantAlias
     * 
     * @param iSkip number of elements to skip
     * @return JDFColorantAlias the element
     */
    public JDFColorantAlias getCreateColorantAlias(int iSkip)
    {
        return (JDFColorantAlias)getCreateElement_KElement(ElementName.COLORANTALIAS, null, iSkip);
    }

    /**
     * (27) const get element ColorantAlias
     * @param iSkip number of elements to skip
     * @return JDFColorantAlias the element
     * default is getColorantAlias(0)     */
    public JDFColorantAlias getColorantAlias(int iSkip)
    {
        return (JDFColorantAlias) getElement(ElementName.COLORANTALIAS, null, iSkip);
    }

    /**
     * Get all ColorantAlias from the current element
     * 
     * @return Collection<JDFColorantAlias>
     */
    public Collection<JDFColorantAlias> getAllColorantAlias()
    {
        Vector<JDFColorantAlias> v = new Vector<JDFColorantAlias>();

        JDFColorantAlias kElem = (JDFColorantAlias) getFirstChildElement(ElementName.COLORANTALIAS, null);

        while (kElem != null)
        {
            v.add(kElem);

            kElem = (JDFColorantAlias) kElem.getNextSiblingElement(ElementName.COLORANTALIAS, null);
        }

        return v;
    }

    /**
     * (30) append element ColorantAlias
     */
    public JDFColorantAlias appendColorantAlias() throws JDFException
    {
        return (JDFColorantAlias) appendElement(ElementName.COLORANTALIAS, null);
    }

    /**
      * (31) create inter-resource link to refTarget
      * @param refTarget the element that is referenced
      */
    public void refColorantAlias(JDFColorantAlias refTarget)
    {
        refElement(refTarget);
    }

    /**
     * (24) const get element ColorantOrder
     * @return JDFSeparationList the element
     */
    public JDFSeparationList getColorantOrder()
    {
        return (JDFSeparationList) getElement(ElementName.COLORANTORDER, null, 0);
    }

    /** (25) getCreateColorantOrder
     * 
     * @return JDFSeparationList the element
     */
    public JDFSeparationList getCreateColorantOrder()
    {
        return (JDFSeparationList) getCreateElement_KElement(ElementName.COLORANTORDER, null, 0);
    }

    /**
     * (29) append element ColorantOrder
     */
    public JDFSeparationList appendColorantOrder() throws JDFException
    {
        return (JDFSeparationList) appendElementN(ElementName.COLORANTORDER, 1, null);
    }

    /**
     * (24) const get element ColorantParams
     * @return JDFSeparationList the element
     */
    public JDFSeparationList getColorantParams()
    {
        return (JDFSeparationList) getElement(ElementName.COLORANTPARAMS, null, 0);
    }

    /** (25) getCreateColorantParams
     * 
     * @return JDFSeparationList the element
     */
    public JDFSeparationList getCreateColorantParams()
    {
        return (JDFSeparationList) getCreateElement_KElement(ElementName.COLORANTPARAMS, null, 0);
    }

    /**
     * (29) append element ColorantParams
     */
    public JDFSeparationList appendColorantParams() throws JDFException
    {
        return (JDFSeparationList) appendElementN(ElementName.COLORANTPARAMS, 1, null);
    }

    /**
     * (24) const get element ColorPool
     * @return JDFColorPool the element
     */
    public JDFColorPool getColorPool()
    {
        return (JDFColorPool) getElement(ElementName.COLORPOOL, null, 0);
    }

    /** (25) getCreateColorPool
     * 
     * @return JDFColorPool the element
     */
    public JDFColorPool getCreateColorPool()
    {
        return (JDFColorPool) getCreateElement_KElement(ElementName.COLORPOOL, null, 0);
    }

    /**
     * (29) append element ColorPool
     */
    public JDFColorPool appendColorPool() throws JDFException
    {
        return (JDFColorPool) appendElementN(ElementName.COLORPOOL, 1, null);
    }

    /**
      * (31) create inter-resource link to refTarget
      * @param refTarget the element that is referenced
      */
    public void refColorPool(JDFColorPool refTarget)
    {
        refElement(refTarget);
    }

    /** (26) getCreateColorSpaceSubstitute
     * 
     * @param iSkip number of elements to skip
     * @return JDFColorSpaceSubstitute the element
     */
    public JDFColorSpaceSubstitute getCreateColorSpaceSubstitute(int iSkip)
    {
        return (JDFColorSpaceSubstitute)getCreateElement_KElement(ElementName.COLORSPACESUBSTITUTE, null, iSkip);
    }

    /**
     * (27) const get element ColorSpaceSubstitute
     * @param iSkip number of elements to skip
     * @return JDFColorSpaceSubstitute the element
     * default is getColorSpaceSubstitute(0)     */
    public JDFColorSpaceSubstitute getColorSpaceSubstitute(int iSkip)
    {
        return (JDFColorSpaceSubstitute) getElement(ElementName.COLORSPACESUBSTITUTE, null, iSkip);
    }

    /**
     * Get all ColorSpaceSubstitute from the current element
     * 
     * @return Collection<JDFColorSpaceSubstitute>
     */
    public Collection<JDFColorSpaceSubstitute> getAllColorSpaceSubstitute()
    {
        Vector<JDFColorSpaceSubstitute> v = new Vector<JDFColorSpaceSubstitute>();

        JDFColorSpaceSubstitute kElem = (JDFColorSpaceSubstitute) getFirstChildElement(ElementName.COLORSPACESUBSTITUTE, null);

        while (kElem != null)
        {
            v.add(kElem);

            kElem = (JDFColorSpaceSubstitute) kElem.getNextSiblingElement(ElementName.COLORSPACESUBSTITUTE, null);
        }

        return v;
    }

    /**
     * (30) append element ColorSpaceSubstitute
     */
    public JDFColorSpaceSubstitute appendColorSpaceSubstitute() throws JDFException
    {
        return (JDFColorSpaceSubstitute) appendElement(ElementName.COLORSPACESUBSTITUTE, null);
    }

    /**
     * (24) const get element DeviceColorantOrder
     * @return JDFSeparationList the element
     */
    public JDFSeparationList getDeviceColorantOrder()
    {
        return (JDFSeparationList) getElement(ElementName.DEVICECOLORANTORDER, null, 0);
    }

    /** (25) getCreateDeviceColorantOrder
     * 
     * @return JDFSeparationList the element
     */
    public JDFSeparationList getCreateDeviceColorantOrder()
    {
        return (JDFSeparationList) getCreateElement_KElement(ElementName.DEVICECOLORANTORDER, null, 0);
    }

    /**
     * (29) append element DeviceColorantOrder
     */
    public JDFSeparationList appendDeviceColorantOrder() throws JDFException
    {
        return (JDFSeparationList) appendElementN(ElementName.DEVICECOLORANTORDER, 1, null);
    }

    /** (26) getCreateDeviceNSpace
     * 
     * @param iSkip number of elements to skip
     * @return JDFDeviceNSpace the element
     */
    public JDFDeviceNSpace getCreateDeviceNSpace(int iSkip)
    {
        return (JDFDeviceNSpace)getCreateElement_KElement(ElementName.DEVICENSPACE, null, iSkip);
    }

    /**
     * (27) const get element DeviceNSpace
     * @param iSkip number of elements to skip
     * @return JDFDeviceNSpace the element
     * default is getDeviceNSpace(0)     */
    public JDFDeviceNSpace getDeviceNSpace(int iSkip)
    {
        return (JDFDeviceNSpace) getElement(ElementName.DEVICENSPACE, null, iSkip);
    }

    /**
     * Get all DeviceNSpace from the current element
     * 
     * @return Collection<JDFDeviceNSpace>
     */
    public Collection<JDFDeviceNSpace> getAllDeviceNSpace()
    {
        Vector<JDFDeviceNSpace> v = new Vector<JDFDeviceNSpace>();

        JDFDeviceNSpace kElem = (JDFDeviceNSpace) getFirstChildElement(ElementName.DEVICENSPACE, null);

        while (kElem != null)
        {
            v.add(kElem);

            kElem = (JDFDeviceNSpace) kElem.getNextSiblingElement(ElementName.DEVICENSPACE, null);
        }

        return v;
    }

    /**
     * (30) append element DeviceNSpace
     */
    public JDFDeviceNSpace appendDeviceNSpace() throws JDFException
    {
        return (JDFDeviceNSpace) appendElement(ElementName.DEVICENSPACE, null);
    }

    /**
      * (31) create inter-resource link to refTarget
      * @param refTarget the element that is referenced
      */
    public void refDeviceNSpace(JDFDeviceNSpace refTarget)
    {
        refElement(refTarget);
    }

}// end namespace JDF
