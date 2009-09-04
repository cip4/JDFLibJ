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
import org.cip4.jdflib.core.VElement;
import org.cip4.jdflib.datatypes.JDFRectangle;
import org.cip4.jdflib.resource.process.JDFComponent;
import org.cip4.jdflib.resource.process.postpress.JDFGlueLine;

public abstract class JDFAutoBoxApplication extends JDFElement
{

    private static final long serialVersionUID = 1L;

    private static AtrInfoTable[] atrInfoTable = new AtrInfoTable[1];
    static
    {
        atrInfoTable[0] = new AtrInfoTable(AttributeName.APPLICATIONAREA, 0x44443111, AttributeInfo.EnumAttributeType.rectangle, null, null);
    }
    
    @Override
	protected AttributeInfo getTheAttributeInfo()
    {
        return super.getTheAttributeInfo().updateReplace(atrInfoTable);
    }


    private static ElemInfoTable[] elemInfoTable = new ElemInfoTable[2];
    static
    {
        elemInfoTable[0] = new ElemInfoTable(ElementName.COMPONENT, 0x44443333);
        elemInfoTable[1] = new ElemInfoTable(ElementName.GLUELINE, 0x44443333);
    }
    
    @Override
	protected ElementInfo getTheElementInfo()
    {
        return super.getTheElementInfo().updateReplace(elemInfoTable);
    }



    /**
     * Constructor for JDFAutoBoxApplication
     * @param myOwnerDocument
     * @param qualifiedName
     */
    protected JDFAutoBoxApplication(
        CoreDocumentImpl myOwnerDocument,
        String qualifiedName)
    {
        super(myOwnerDocument, qualifiedName);
    }

    /**
     * Constructor for JDFAutoBoxApplication
     * @param myOwnerDocument
     * @param myNamespaceURI
     * @param qualifiedName
     */
    protected JDFAutoBoxApplication(
        CoreDocumentImpl myOwnerDocument,
        String myNamespaceURI,
        String qualifiedName)
    {
        super(myOwnerDocument, myNamespaceURI, qualifiedName);
    }

    /**
     * Constructor for JDFAutoBoxApplication
     * @param myOwnerDocument
     * @param myNamespaceURI
     * @param qualifiedName
     * @param myLocalName
     */
    protected JDFAutoBoxApplication(
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
        return " JDFAutoBoxApplication[  --> " + super.toString() + " ]";
    }


/* ************************************************************************
 * Attribute getter / setter
 * ************************************************************************
 */
        
        /* ---------------------------------------------------------------------
        Methods for Attribute ApplicationArea
        --------------------------------------------------------------------- */
        /**
          * (36) set attribute ApplicationArea
          * @param value: the value to set the attribute to
          */
        public void setApplicationArea(JDFRectangle value)
        {
            setAttribute(AttributeName.APPLICATIONAREA, value, null);
        }

        /**
          * (20) get JDFRectangle attribute ApplicationArea
          * @return JDFRectangle the value of the attribute, null if a the
          *         attribute value is not a valid to create a JDFRectangle
          */
        public JDFRectangle getApplicationArea()
        {
            String strAttrName = "";
            JDFRectangle nPlaceHolder = null;
            strAttrName = getAttribute(AttributeName.APPLICATIONAREA, null, JDFConstants.EMPTYSTRING);
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

/* ***********************************************************************
 * Element getter / setter
 * ***********************************************************************
 */

    /** (26) getCreateComponent
     * 
     * @param iSkip number of elements to skip
     * @return JDFComponent the element
     */
    public JDFComponent getCreateComponent(int iSkip)
    {
        return (JDFComponent)getCreateElement_KElement(ElementName.COMPONENT, null, iSkip);
    }

    /**
     * (27) const get element Component
     * @param iSkip number of elements to skip
     * @return JDFComponent the element
     * default is getComponent(0)     */
    public JDFComponent getComponent(int iSkip)
    {
        return (JDFComponent) getElement(ElementName.COMPONENT, null, iSkip);
    }

    /**
     * Get all Component from the current element
     * 
     * @return Collection<JDFComponent>, null if none are available
     */
    public Collection<JDFComponent> getAllComponent()
    {
        final VElement vc = getChildElementVector(ElementName.COMPONENT, null);
        if (vc == null || vc.size() == 0)
        {
            return null;
        }

        final Vector<JDFComponent> v = new Vector<JDFComponent>();
        for (int i = 0; i < vc.size(); i++)
        {
            v.add((JDFComponent) vc.get(i));
        }

        return v;
    }

    /**
     * (30) append element Component
     */
    public JDFComponent appendComponent() throws JDFException
    {
        return (JDFComponent) appendElement(ElementName.COMPONENT, null);
    }

    /**
      * (31) create inter-resource link to refTarget
      * @param refTarget the element that is referenced
      */
    public void refComponent(JDFComponent refTarget)
    {
        refElement(refTarget);
    }

    /** (26) getCreateGlueLine
     * 
     * @param iSkip number of elements to skip
     * @return JDFGlueLine the element
     */
    public JDFGlueLine getCreateGlueLine(int iSkip)
    {
        return (JDFGlueLine)getCreateElement_KElement(ElementName.GLUELINE, null, iSkip);
    }

    /**
     * (27) const get element GlueLine
     * @param iSkip number of elements to skip
     * @return JDFGlueLine the element
     * default is getGlueLine(0)     */
    public JDFGlueLine getGlueLine(int iSkip)
    {
        return (JDFGlueLine) getElement(ElementName.GLUELINE, null, iSkip);
    }

    /**
     * Get all GlueLine from the current element
     * 
     * @return Collection<JDFGlueLine>, null if none are available
     */
    public Collection<JDFGlueLine> getAllGlueLine()
    {
        final VElement vc = getChildElementVector(ElementName.GLUELINE, null);
        if (vc == null || vc.size() == 0)
        {
            return null;
        }

        final Vector<JDFGlueLine> v = new Vector<JDFGlueLine>();
        for (int i = 0; i < vc.size(); i++)
        {
            v.add((JDFGlueLine) vc.get(i));
        }

        return v;
    }

    /**
     * (30) append element GlueLine
     */
    public JDFGlueLine appendGlueLine() throws JDFException
    {
        return (JDFGlueLine) appendElement(ElementName.GLUELINE, null);
    }

    /**
      * (31) create inter-resource link to refTarget
      * @param refTarget the element that is referenced
      */
    public void refGlueLine(JDFGlueLine refTarget)
    {
        refElement(refTarget);
    }

}// end namespace JDF
