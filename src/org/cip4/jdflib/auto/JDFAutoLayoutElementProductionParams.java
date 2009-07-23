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
import org.cip4.jdflib.resource.devicecapability.*; 
import org.cip4.jdflib.resource.process.*;

public abstract class JDFAutoLayoutElementProductionParams extends JDFResource
{

    private static final long serialVersionUID = 1L;

    private static ElemInfoTable[] elemInfoTable = new ElemInfoTable[4];
    static
    {
        elemInfoTable[0] = new ElemInfoTable(ElementName.ACTIONPOOL, 0x33331111);
        elemInfoTable[1] = new ElemInfoTable(ElementName.LAYOUTELEMENTPART, 0x33331111);
        elemInfoTable[2] = new ElemInfoTable(ElementName.SHAPEDEF, 0x66661111);
        elemInfoTable[3] = new ElemInfoTable(ElementName.TESTPOOL, 0x33331111);
    }
    
    @Override
	protected ElementInfo getTheElementInfo()
    {
        return super.getTheElementInfo().updateReplace(elemInfoTable);
    }



    /**
     * Constructor for JDFAutoLayoutElementProductionParams
     * @param myOwnerDocument
     * @param qualifiedName
     */
    protected JDFAutoLayoutElementProductionParams(
        CoreDocumentImpl myOwnerDocument,
        String qualifiedName)
    {
        super(myOwnerDocument, qualifiedName);
    }

    /**
     * Constructor for JDFAutoLayoutElementProductionParams
     * @param myOwnerDocument
     * @param myNamespaceURI
     * @param qualifiedName
     */
    protected JDFAutoLayoutElementProductionParams(
        CoreDocumentImpl myOwnerDocument,
        String myNamespaceURI,
        String qualifiedName)
    {
        super(myOwnerDocument, myNamespaceURI, qualifiedName);
    }

    /**
     * Constructor for JDFAutoLayoutElementProductionParams
     * @param myOwnerDocument
     * @param myNamespaceURI
     * @param qualifiedName
     * @param myLocalName
     */
    protected JDFAutoLayoutElementProductionParams(
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
        return " JDFAutoLayoutElementProductionParams[  --> " + super.toString() + " ]";
    }


    @Override
	public boolean  init()
    {
        boolean bRet = super.init();
        setResourceClass(JDFResource.EnumResourceClass.Parameter);
        return bRet;
    }


    @Override
	public EnumResourceClass getValidClass()
    {
        return JDFResource.EnumResourceClass.Parameter;
    }


/* ***********************************************************************
 * Element getter / setter
 * ***********************************************************************
 */

    /** (26) getCreateActionPool
     * 
     * @param iSkip number of elements to skip
     * @return JDFActionPool the element
     */
    public JDFActionPool getCreateActionPool(int iSkip)
    {
        return (JDFActionPool)getCreateElement_KElement(ElementName.ACTIONPOOL, null, iSkip);
    }

    /**
     * (27) const get element ActionPool
     * @param iSkip number of elements to skip
     * @return JDFActionPool the element
     * default is getActionPool(0)     */
    public JDFActionPool getActionPool(int iSkip)
    {
        return (JDFActionPool) getElement(ElementName.ACTIONPOOL, null, iSkip);
    }

    /**
     * Get all ActionPool from the current element
     * 
     * @return Collection<JDFActionPool>, null if none are available
     */
    public Collection<JDFActionPool> getAllActionPool()
    {
        final VElement vc = getChildElementVector(ElementName.ACTIONPOOL, null);
        if (vc == null || vc.size() == 0)
        {
            return null;
        }

        final Vector<JDFActionPool> v = new Vector<JDFActionPool>();
        for (int i = 0; i < vc.size(); i++)
        {
            v.add((JDFActionPool) vc.get(i));
        }

        return v;
    }

    /**
     * (30) append element ActionPool
     */
    public JDFActionPool appendActionPool() throws JDFException
    {
        return (JDFActionPool) appendElement(ElementName.ACTIONPOOL, null);
    }

    /** (26) getCreateLayoutElementPart
     * 
     * @param iSkip number of elements to skip
     * @return JDFLayoutElementPart the element
     */
    public JDFLayoutElementPart getCreateLayoutElementPart(int iSkip)
    {
        return (JDFLayoutElementPart)getCreateElement_KElement(ElementName.LAYOUTELEMENTPART, null, iSkip);
    }

    /**
     * (27) const get element LayoutElementPart
     * @param iSkip number of elements to skip
     * @return JDFLayoutElementPart the element
     * default is getLayoutElementPart(0)     */
    public JDFLayoutElementPart getLayoutElementPart(int iSkip)
    {
        return (JDFLayoutElementPart) getElement(ElementName.LAYOUTELEMENTPART, null, iSkip);
    }

    /**
     * Get all LayoutElementPart from the current element
     * 
     * @return Collection<JDFLayoutElementPart>, null if none are available
     */
    public Collection<JDFLayoutElementPart> getAllLayoutElementPart()
    {
        final VElement vc = getChildElementVector(ElementName.LAYOUTELEMENTPART, null);
        if (vc == null || vc.size() == 0)
        {
            return null;
        }

        final Vector<JDFLayoutElementPart> v = new Vector<JDFLayoutElementPart>();
        for (int i = 0; i < vc.size(); i++)
        {
            v.add((JDFLayoutElementPart) vc.get(i));
        }

        return v;
    }

    /**
     * (30) append element LayoutElementPart
     */
    public JDFLayoutElementPart appendLayoutElementPart() throws JDFException
    {
        return (JDFLayoutElementPart) appendElement(ElementName.LAYOUTELEMENTPART, null);
    }

    /**
     * (24) const get element ShapeDef
     * @return JDFShapeDef the element
     */
    public JDFShapeDef getShapeDef()
    {
        return (JDFShapeDef) getElement(ElementName.SHAPEDEF, null, 0);
    }

    /** (25) getCreateShapeDef
     * 
     * @return JDFShapeDef the element
     */
    public JDFShapeDef getCreateShapeDef()
    {
        return (JDFShapeDef) getCreateElement_KElement(ElementName.SHAPEDEF, null, 0);
    }

    /**
     * (29) append element ShapeDef
     */
    public JDFShapeDef appendShapeDef() throws JDFException
    {
        return (JDFShapeDef) appendElementN(ElementName.SHAPEDEF, 1, null);
    }

    /**
      * (31) create inter-resource link to refTarget
      * @param refTarget the element that is referenced
      */
    public void refShapeDef(JDFShapeDef refTarget)
    {
        refElement(refTarget);
    }

    /** (26) getCreateTestPool
     * 
     * @param iSkip number of elements to skip
     * @return JDFTestPool the element
     */
    public JDFTestPool getCreateTestPool(int iSkip)
    {
        return (JDFTestPool)getCreateElement_KElement(ElementName.TESTPOOL, null, iSkip);
    }

    /**
     * (27) const get element TestPool
     * @param iSkip number of elements to skip
     * @return JDFTestPool the element
     * default is getTestPool(0)     */
    public JDFTestPool getTestPool(int iSkip)
    {
        return (JDFTestPool) getElement(ElementName.TESTPOOL, null, iSkip);
    }

    /**
     * Get all TestPool from the current element
     * 
     * @return Collection<JDFTestPool>, null if none are available
     */
    public Collection<JDFTestPool> getAllTestPool()
    {
        final VElement vc = getChildElementVector(ElementName.TESTPOOL, null);
        if (vc == null || vc.size() == 0)
        {
            return null;
        }

        final Vector<JDFTestPool> v = new Vector<JDFTestPool>();
        for (int i = 0; i < vc.size(); i++)
        {
            v.add((JDFTestPool) vc.get(i));
        }

        return v;
    }

    /**
     * (30) append element TestPool
     */
    public JDFTestPool appendTestPool() throws JDFException
    {
        return (JDFTestPool) appendElement(ElementName.TESTPOOL, null);
    }

}// end namespace JDF
