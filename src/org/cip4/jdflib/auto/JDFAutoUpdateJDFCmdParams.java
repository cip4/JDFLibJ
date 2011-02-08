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
    class JDFAutoUpdateJDFCmdParams : public JDFElement

    *****************************************************************************
    */

public abstract class JDFAutoUpdateJDFCmdParams extends JDFElement
{

    private static final long serialVersionUID = 1L;

    private static ElemInfoTable[] elemInfoTable = new ElemInfoTable[4];
    static
    {
        elemInfoTable[0] = new ElemInfoTable(ElementName.CREATELINK, 0x33333333);
        elemInfoTable[1] = new ElemInfoTable(ElementName.CREATERESOURCE, 0x33333333);
        elemInfoTable[2] = new ElemInfoTable(ElementName.MOVERESOURCE, 0x33333333);
        elemInfoTable[3] = new ElemInfoTable(ElementName.REMOVELINK, 0x33333333);
    }
    
    protected ElementInfo getTheElementInfo()
    {
        return super.getTheElementInfo().updateReplace(elemInfoTable);
    }



    /**
     * Constructor for JDFAutoUpdateJDFCmdParams
     * @param myOwnerDocument
     * @param qualifiedName
     */
    protected JDFAutoUpdateJDFCmdParams(
        CoreDocumentImpl myOwnerDocument,
        String qualifiedName)
    {
        super(myOwnerDocument, qualifiedName);
    }

    /**
     * Constructor for JDFAutoUpdateJDFCmdParams
     * @param myOwnerDocument
     * @param myNamespaceURI
     * @param qualifiedName
     */
    protected JDFAutoUpdateJDFCmdParams(
        CoreDocumentImpl myOwnerDocument,
        String myNamespaceURI,
        String qualifiedName)
    {
        super(myOwnerDocument, myNamespaceURI, qualifiedName);
    }

    /**
     * Constructor for JDFAutoUpdateJDFCmdParams
     * @param myOwnerDocument
     * @param myNamespaceURI
     * @param qualifiedName
     * @param myLocalName
     */
    protected JDFAutoUpdateJDFCmdParams(
        CoreDocumentImpl myOwnerDocument,
        String myNamespaceURI,
        String qualifiedName,
        String myLocalName)
    {
        super(myOwnerDocument, myNamespaceURI, qualifiedName, myLocalName);
    }


    public String toString()
    {
        return " JDFAutoUpdateJDFCmdParams[  --> " + super.toString() + " ]";
    }


/* ***********************************************************************
 * Element getter / setter
 * ***********************************************************************
 */

    /** (26) getCreateCreateLink
     * 
     * @param iSkip number of elements to skip
     * @return JDFCreateLink the element
     */
    public JDFCreateLink getCreateCreateLink(int iSkip)
    {
        return (JDFCreateLink)getCreateElement_KElement(ElementName.CREATELINK, null, iSkip);
    }

    /**
     * (27) const get element CreateLink
     * @param iSkip number of elements to skip
     * @return JDFCreateLink the element
     * default is getCreateLink(0)     */
    public JDFCreateLink getCreateLink(int iSkip)
    {
        return (JDFCreateLink) getElement(ElementName.CREATELINK, null, iSkip);
    }

    /**
     * Get all CreateLink from the current element
     * 
     * @return Collection<JDFCreateLink>, null if none are available
     */
    public Collection<JDFCreateLink> getAllCreateLink()
    {
        final VElement vc = getChildElementVector(ElementName.CREATELINK, null);
        if (vc == null || vc.size() == 0)
        {
            return null;
        }

        final Vector<JDFCreateLink> v = new Vector<JDFCreateLink>();
        for (int i = 0; i < vc.size(); i++)
        {
            v.add((JDFCreateLink) vc.get(i));
        }

        return v;
    }

    /**
     * (30) append element CreateLink
     */
    public JDFCreateLink appendCreateLink() throws JDFException
    {
        return (JDFCreateLink) appendElement(ElementName.CREATELINK, null);
    }

    /** (26) getCreateCreateResource
     * 
     * @param iSkip number of elements to skip
     * @return JDFCreateResource the element
     */
    public JDFCreateResource getCreateCreateResource(int iSkip)
    {
        return (JDFCreateResource)getCreateElement_KElement(ElementName.CREATERESOURCE, null, iSkip);
    }

    /**
     * (27) const get element CreateResource
     * @param iSkip number of elements to skip
     * @return JDFCreateResource the element
     * default is getCreateResource(0)     */
    public JDFCreateResource getCreateResource(int iSkip)
    {
        return (JDFCreateResource) getElement(ElementName.CREATERESOURCE, null, iSkip);
    }

    /**
     * Get all CreateResource from the current element
     * 
     * @return Collection<JDFCreateResource>, null if none are available
     */
    public Collection<JDFCreateResource> getAllCreateResource()
    {
        final VElement vc = getChildElementVector(ElementName.CREATERESOURCE, null);
        if (vc == null || vc.size() == 0)
        {
            return null;
        }

        final Vector<JDFCreateResource> v = new Vector<JDFCreateResource>();
        for (int i = 0; i < vc.size(); i++)
        {
            v.add((JDFCreateResource) vc.get(i));
        }

        return v;
    }

    /**
     * (30) append element CreateResource
     */
    public JDFCreateResource appendCreateResource() throws JDFException
    {
        return (JDFCreateResource) appendElement(ElementName.CREATERESOURCE, null);
    }

    /** (26) getCreateMoveResource
     * 
     * @param iSkip number of elements to skip
     * @return JDFMoveResource the element
     */
    public JDFMoveResource getCreateMoveResource(int iSkip)
    {
        return (JDFMoveResource)getCreateElement_KElement(ElementName.MOVERESOURCE, null, iSkip);
    }

    /**
     * (27) const get element MoveResource
     * @param iSkip number of elements to skip
     * @return JDFMoveResource the element
     * default is getMoveResource(0)     */
    public JDFMoveResource getMoveResource(int iSkip)
    {
        return (JDFMoveResource) getElement(ElementName.MOVERESOURCE, null, iSkip);
    }

    /**
     * Get all MoveResource from the current element
     * 
     * @return Collection<JDFMoveResource>, null if none are available
     */
    public Collection<JDFMoveResource> getAllMoveResource()
    {
        final VElement vc = getChildElementVector(ElementName.MOVERESOURCE, null);
        if (vc == null || vc.size() == 0)
        {
            return null;
        }

        final Vector<JDFMoveResource> v = new Vector<JDFMoveResource>();
        for (int i = 0; i < vc.size(); i++)
        {
            v.add((JDFMoveResource) vc.get(i));
        }

        return v;
    }

    /**
     * (30) append element MoveResource
     */
    public JDFMoveResource appendMoveResource() throws JDFException
    {
        return (JDFMoveResource) appendElement(ElementName.MOVERESOURCE, null);
    }

    /** (26) getCreateRemoveLink
     * 
     * @param iSkip number of elements to skip
     * @return JDFRemoveLink the element
     */
    public JDFRemoveLink getCreateRemoveLink(int iSkip)
    {
        return (JDFRemoveLink)getCreateElement_KElement(ElementName.REMOVELINK, null, iSkip);
    }

    /**
     * (27) const get element RemoveLink
     * @param iSkip number of elements to skip
     * @return JDFRemoveLink the element
     * default is getRemoveLink(0)     */
    public JDFRemoveLink getRemoveLink(int iSkip)
    {
        return (JDFRemoveLink) getElement(ElementName.REMOVELINK, null, iSkip);
    }

    /**
     * Get all RemoveLink from the current element
     * 
     * @return Collection<JDFRemoveLink>, null if none are available
     */
    public Collection<JDFRemoveLink> getAllRemoveLink()
    {
        final VElement vc = getChildElementVector(ElementName.REMOVELINK, null);
        if (vc == null || vc.size() == 0)
        {
            return null;
        }

        final Vector<JDFRemoveLink> v = new Vector<JDFRemoveLink>();
        for (int i = 0; i < vc.size(); i++)
        {
            v.add((JDFRemoveLink) vc.get(i));
        }

        return v;
    }

    /**
     * (30) append element RemoveLink
     */
    public JDFRemoveLink appendRemoveLink() throws JDFException
    {
        return (JDFRemoveLink) appendElement(ElementName.REMOVELINK, null);
    }

}// end namespace JDF
