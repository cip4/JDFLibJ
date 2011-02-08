/*
 * The CIP4 Software License, Version 1.0
 *
 *
 * Copyright (c) 2001-2010 The International Cooperation for the Integration of
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
import java.util.Iterator;                          
import java.util.List;                              
import java.util.Map;                               
import java.util.Vector;                            
import java.util.zip.DataFormatException;           

import org.apache.commons.lang.enums.ValuedEnum;    
import org.w3c.dom.Element;                         
import org.apache.xerces.dom.CoreDocumentImpl;      
import org.cip4.jdflib.*;                           
import org.cip4.jdflib.auto.*;                      
import org.cip4.jdflib.core.*;                      
import org.cip4.jdflib.core.ElementInfo;                      
import org.cip4.jdflib.span.*;                      
import org.cip4.jdflib.node.*;                      
import org.cip4.jdflib.pool.*;                      
import org.cip4.jdflib.jmf.*;                       
import org.cip4.jdflib.datatypes.*;                 
import org.cip4.jdflib.resource.*;                  
import org.cip4.jdflib.resource.devicecapability.*; 
import org.cip4.jdflib.resource.intent.*;           
import org.cip4.jdflib.resource.process.*;          
import org.cip4.jdflib.resource.process.postpress.*;
import org.cip4.jdflib.resource.process.press.*;    
import org.cip4.jdflib.resource.process.prepress.*; 
import org.cip4.jdflib.util.*;           
    /**
    *****************************************************************************
    class JDFAutoPreflightParams : public JDFResource

    *****************************************************************************
    */

public abstract class JDFAutoPreflightParams extends JDFResource
{

    private static final long serialVersionUID = 1L;

    private static ElemInfoTable[] elemInfoTable = new ElemInfoTable[3];
    static
    {
        elemInfoTable[0] = new ElemInfoTable(ElementName.ACTIONPOOL, 0x33333311);
        elemInfoTable[1] = new ElemInfoTable(ElementName.FILESPEC, 0x66661111);
        elemInfoTable[2] = new ElemInfoTable(ElementName.TESTPOOL, 0x66666111);
    }
    
    protected ElementInfo getTheElementInfo()
    {
        return super.getTheElementInfo().updateReplace(elemInfoTable);
    }



    /**
     * Constructor for JDFAutoPreflightParams
     * @param myOwnerDocument
     * @param qualifiedName
     */
    protected JDFAutoPreflightParams(
        CoreDocumentImpl myOwnerDocument,
        String qualifiedName)
    {
        super(myOwnerDocument, qualifiedName);
    }

    /**
     * Constructor for JDFAutoPreflightParams
     * @param myOwnerDocument
     * @param myNamespaceURI
     * @param qualifiedName
     */
    protected JDFAutoPreflightParams(
        CoreDocumentImpl myOwnerDocument,
        String myNamespaceURI,
        String qualifiedName)
    {
        super(myOwnerDocument, myNamespaceURI, qualifiedName);
    }

    /**
     * Constructor for JDFAutoPreflightParams
     * @param myOwnerDocument
     * @param myNamespaceURI
     * @param qualifiedName
     * @param myLocalName
     */
    protected JDFAutoPreflightParams(
        CoreDocumentImpl myOwnerDocument,
        String myNamespaceURI,
        String qualifiedName,
        String myLocalName)
    {
        super(myOwnerDocument, myNamespaceURI, qualifiedName, myLocalName);
    }


    public String toString()
    {
        return " JDFAutoPreflightParams[  --> " + super.toString() + " ]";
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

    /**
     * (24) const get element FileSpec
     * @return JDFFileSpec the element
     */
    public JDFFileSpec getFileSpec()
    {
        return (JDFFileSpec) getElement(ElementName.FILESPEC, null, 0);
    }

    /** (25) getCreateFileSpec
     * 
     * @return JDFFileSpec the element
     */
    public JDFFileSpec getCreateFileSpec()
    {
        return (JDFFileSpec) getCreateElement_KElement(ElementName.FILESPEC, null, 0);
    }

    /**
     * (29) append element FileSpec
     */
    public JDFFileSpec appendFileSpec() throws JDFException
    {
        return (JDFFileSpec) appendElementN(ElementName.FILESPEC, 1, null);
    }

    /**
      * (31) create inter-resource link to refTarget
      * @param refTarget the element that is referenced
      */
    public void refFileSpec(JDFFileSpec refTarget)
    {
        refElement(refTarget);
    }

    /**
     * (24) const get element TestPool
     * @return JDFTestPool the element
     */
    public JDFTestPool getTestPool()
    {
        return (JDFTestPool) getElement(ElementName.TESTPOOL, null, 0);
    }

    /** (25) getCreateTestPool
     * 
     * @return JDFTestPool the element
     */
    public JDFTestPool getCreateTestPool()
    {
        return (JDFTestPool) getCreateElement_KElement(ElementName.TESTPOOL, null, 0);
    }

    /**
     * (29) append element TestPool
     */
    public JDFTestPool appendTestPool() throws JDFException
    {
        return (JDFTestPool) appendElementN(ElementName.TESTPOOL, 1, null);
    }

}// end namespace JDF
