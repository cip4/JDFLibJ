/*--------------------------------------------------------------------------------------------------
 * The CIP4 Software License, Version 1.0
 *
 *
 * Copyright (c) 2001-2006 The International Cooperation for the Integration of 
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
 */
/**
 *
 * Copyright (c) 2005 Heidelberger Druckmaschinen AG, All Rights Reserved.
 *
 * JDFSourceResource.java
 *
 * Last changes
 *
 */
package org.cip4.jdflib.resource.process;

import java.util.Vector;

import org.apache.xerces.dom.CoreDocumentImpl;
import org.cip4.jdflib.core.JDFElement;
import org.cip4.jdflib.core.JDFRefElement;
import org.cip4.jdflib.core.KElement;
import org.cip4.jdflib.core.VString;
import org.cip4.jdflib.resource.JDFResource;


public class JDFSourceResource extends JDFElement
{
    private static final long serialVersionUID = 1L;

    /**
     * Constructor for JDFSourceResource
     * @param ownerDocument
     * @param qualifiedName
     */
     public JDFSourceResource(
        CoreDocumentImpl myOwnerDocument,
        String qualifiedName)
    {
        super(myOwnerDocument, qualifiedName);
    }


    /**
     * Constructor for JDFSourceResource
     * @param ownerDocument
     * @param namespaceURI
     * @param qualifiedName
     */
    public JDFSourceResource(
        CoreDocumentImpl myOwnerDocument,
        String myNamespaceURI,
        String qualifiedName)
    {
        super(myOwnerDocument, myNamespaceURI, qualifiedName);
    }

    /**
     * Constructor for JDFSourceResource
     * @param ownerDocument
     * @param namespaceURI
     * @param qualifiedName
     * @param localName
     */
    public JDFSourceResource(
        CoreDocumentImpl myOwnerDocument,
        String myNamespaceURI,
        String qualifiedName,
        String myLocalName)
    {
        super(myOwnerDocument, myNamespaceURI, qualifiedName, myLocalName);
    }     

    //**************************************** Methods *********************************************
    /**
     * toString
     *
     * @return String
     */
    public String toString()
    {
        return "JDFSourceResource[  --> " + super.toString() + " ]";
    }
    
    
    public JDFRefElement getRefElement ()
    {
        KElement e = getFirstChildElement ();
        while (true)
        {
            if (e instanceof JDFRefElement)
                return (JDFRefElement) e;
            if (e == null)
                return null;
            e = e.getNextSiblingElement ();
        }
    }

    /**
     * gets the link of the linked route
     * 
     * @return
     */
    public JDFResource getLinkRoot ()
    {
        final JDFRefElement refElement = getRefElement ();
        JDFResource r=null;
        if(refElement!=null){
            r = refElement.getLinkRoot(null);
        }
        return r;
    }
    

    public JDFResource getTarget()
    {
        final JDFRefElement refElement = getRefElement ();
        JDFResource r=null;
        if(refElement!=null){
            r = refElement.getTarget();
        }
        return r;
    }

    /**
     * return a vector of unknown element nodenames
     * @param boolean bIgnorePrivate - used by JDFElement during the validation
     * !!! Do not change the signature of this method
     * @param int nMax - maximum size of the returned vector
     * @return Vector - vector of unknown element nodenames
     * 
     * default: GetInvalidElements(true, 999999)
     */
    /**
     * return a vector of unknown element nodenames
     * 
     * @default getUnknownElements(bIgnorePrivate, 99999999)
     */
    public Vector getUnknownElements(boolean bIgnorePrivate, int nMax)
    {
        if(bIgnorePrivate)
            bIgnorePrivate=false; // dummy to fool compiler
        return getUnknownPoolElements(JDFElement.EnumPoolType.RefElement, nMax);
    }
      
    public VString getInvalidElements(EnumValidationLevel level, boolean bIgnorePrivate,int nMax)
    {
        if(bIgnorePrivate)
            bIgnorePrivate=false; // dummy to fool compiler
        VString v=super.getInvalidElements(level, bIgnorePrivate, nMax);
        if(v.size()>=nMax)
            return v;
            
        Vector v2=getChildElementVector_KElement(null,null,null,true,0);
        int n=0;
        final int size = v2.size();
        for(int i=0;i<size;i++)
        {
            if(v2.elementAt(i) instanceof JDFRefElement)
                n++;
        }
        if(n>1)
        {
            for(int i=0;i<size;i++)
            {
                if(v2.elementAt(i) instanceof JDFRefElement)
                    v.appendUnique(((KElement)v2.elementAt(i)).getLocalName());
            }
        }           
        return v;
    }
    /**
     * return the LocalName of the referenced resource
     * @return the LocalName of the referenced resource
     */
    public String getSourceLocalName()
    {
        final JDFRefElement refElement = getRefElement ();
        if(refElement!=null)
        {
            return refElement.getRefLocalName();
        }
        return null;    
    }
    /**
     * return the NodeName of the referenced resource
     * @return the nodename of the referenced resource
     */
    public String getSourcefNodeName()
    {
        final JDFRefElement refElement = getRefElement ();
        if(refElement!=null)
        {
            return refElement.getRefNodeName();
        }
        return null;    
    }
    /**
     * delete this sourceResource  and it's target
     * @param bool bCheckRefCount if true, check that no other element refers to the target before deleting<br>
     *   if bCheckRefCount=false, the target is force deleted
     * @return JDFElement the deleted targeelement
     * @since 290620
     */
    public JDFElement deleteRef(boolean bCheckRefCount)
    {
        final JDFRefElement refElement = getRefElement ();
        if(refElement!=null)
        {
            return refElement.deleteRef(bCheckRefCount);
        }
        return null;
    }
    
//////////////////////////////////////////////////////////////////////////////    
    /**
     * get list of missing elements
     * @param nMax maximum size of the returned vector
     */
    public VString getMissingElements(int nMax)
    {
        VString vs = getTheElementInfo().requiredElements();
        vs = getMissingElementVector(vs, nMax);
        Vector v2=getChildElementVector_KElement(null,null,null,true,0);
        int n=0;
        for(int i=0;i<v2.size();i++)
        {
            if(v2.elementAt(i) instanceof JDFRefElement)
                n++;
        }
        if(n==0)
            vs.add("RefElement");

        return vs;
    }
    
}
