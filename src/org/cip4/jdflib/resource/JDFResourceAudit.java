/*--------------------------------------------------------------------------------------------------
 * The CIP4 Software License, Version 1.0
 *
 *
 * Copyright (c) 2001-2007 The International Cooperation for the Integration of 
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
 * Copyright (c) 2001 Heidelberger Druckmaschinen AG, All Rights Reserved.
 *
 * JDFResource.java
 *
 * Last changes
 *
 * 2001-07-30   Torsten Kaehlert - delete isNull() and throwNull() methods in parent class KNode
 *              TKAE20010730
 * 2002-02-08   Kai Mattern - check added to GetCreator method
 * 2002-02-21   Kai Mattern - changed isValid in respect to C++ lib (refer to C++ for further details)
 * 2002-02-21   Kai Mattern - added ValidID in respect to C++ lib (refer to C++ for further details)
 * 2001-02-21   Kai Mattern - added ValidStatus in respect to C++ lib (refer to C++ for further details)
 * 2002-02-25   Kai Mattern - added method RemoveFromSpawnIDs
 * 2002-02-25   Kai Mattern - added Valid SpawnIDs
 * 2002-02-25   Kai Mattern - added SpawnIDs to optional Attributes
 * 2002-11-25   Kai Mattern - added UpdateLink()
 */
package org.cip4.jdflib.resource;

import java.util.Vector;

import org.apache.xerces.dom.CoreDocumentImpl;
import org.cip4.jdflib.auto.JDFAutoResourceAudit;
import org.cip4.jdflib.core.JDFConstants;
import org.cip4.jdflib.core.JDFElement;
import org.cip4.jdflib.core.JDFException;
import org.cip4.jdflib.core.JDFRefElement;
import org.cip4.jdflib.core.JDFResourceLink;
import org.cip4.jdflib.core.KElement;
import org.cip4.jdflib.core.VElement;
import org.cip4.jdflib.core.VString;
import org.cip4.jdflib.core.JDFResourceLink.EnumUsage;
import org.cip4.jdflib.datatypes.JDFAttributeMap;
import org.cip4.jdflib.datatypes.VJDFAttributeMap;
import org.cip4.jdflib.node.JDFNode;


public class JDFResourceAudit extends JDFAutoResourceAudit
{
    private static final long serialVersionUID = 1L;

    /**
     * Constructor for JDFResourceAudit
     * @param myOwnerDocument
     * @param qualifiedName
     */
    public JDFResourceAudit(
            CoreDocumentImpl myOwnerDocument,
            String qualifiedName)
    {
        super(myOwnerDocument, qualifiedName);
    }

    /**
     * Constructor for JDFResourceAudit
     * @param myOwnerDocument
     * @param myNamespaceURI
     * @param qualifiedName
     */
    public JDFResourceAudit(
            CoreDocumentImpl myOwnerDocument,
            String myNamespaceURI,
            String qualifiedName)
    {
        super(myOwnerDocument, myNamespaceURI, qualifiedName);
    }

    /**
     * Constructor for JDFResourceAudit
     * @param myOwnerDocument
     * @param myNamespaceURI
     * @param qualifiedName
     * @param myLocalName
     */
    public JDFResourceAudit(
            CoreDocumentImpl myOwnerDocument,
            String myNamespaceURI,
            String qualifiedName,
            String myLocalName)
    {
        super(myOwnerDocument, myNamespaceURI, qualifiedName, myLocalName);
    }

    /**
     * add a link to the new resource
     * @param r the resource that is valid after modification<br>
     *          if r is not specified, return the link that already exists
     * @return the ResourceLink object in the ResourceAudit that points to r
     * @deprecated use addNewOldLink(true,...)
     */
    public JDFResourceLink addNewLink(JDFResource r, boolean bInput)
    {
        return addNewOldLink(true, r, bInput);
    }

    /**
     * add a link to the new resource
     * @param r the resource that is valid after modification<br>
     *          if r is not specified, return the link that already exists
     * @return the ResourceLink object in the ResourceAudit that points to r
     * @deprecated use addNewOldLink(false,...)
     *
     */
    public JDFResourceLink addOldLink(JDFResource r, boolean bInput)
    {
        return addNewOldLink(false, r, bInput);
    }

    /**
     * add  a link to one of the resources 
     * @param bNew   new or original?
     * @param r      the resource that was valid before modification
     * @param bInput usage of the resource
     * @return the ResourceLink object in the ResourceAudit that points to r
     * @deprecated use addNewOldLink(bNew, r, bInput ? EnumUsage.Input : EnumUsage.Output);
     */

    public JDFResourceLink addNewOldLink( boolean bNew,JDFResource r, boolean bInput)
    {
        return addNewOldLink(bNew, r, bInput ? EnumUsage.Input : EnumUsage.Output);
    }
    /**
     * add  a link to one of the resources 
     * @param bNew  true - new link, false - original link
     * @param r     the resource that was valid before modification
     * @param usage usage of the resource
     * @return the ResourceLink object in the ResourceAudit that points to r
     */

    public JDFResourceLink addNewOldLink(boolean bNew,
            JDFResource r, EnumUsage usage)
    {
        VElement v = getChildElementVector(null, null, null, true, 0, false);
        int iNew = bNew ? 0 : 1;
        for(int i=v.size()-1;i>=0;i--){
            if(!(v.elementAt(i) instanceof JDFResourceLink))
                v.removeElementAt(i);
        }

        if (v.size() != iNew)
        {
            throw new JDFException("JDFResourceLink::AddNewOldLink invalid  ResourceAudit");
        }
        JDFResourceLink l = (JDFResourceLink) appendElement(r.getLinkString(), JDFConstants.EMPTYSTRING);
        l.setTarget(r);
        l.setUsage(usage);
        return l;
    }

    /**
     * add  a link to the new resource
     * @return the ResourceLink object in the ResourceAudit 
     */
    public JDFResourceLink getNewLink()
    {
        return getNewOldLink(true);
    }

    /**
     * add  a link to the new resource
     * @return the ResourceLink object in the ResourceAudit 
     */
    public JDFResourceLink getOldLink()
    {
        return getNewOldLink(false);
    }

    /**
     * add  a link to one of the resources 
     * @param bNew new or original?
     * @param r    the resource that was valid before modification<br>
     *             if r is not specified, return the link that already exists
     * @return the ResourceLink object in the ResourceAudit that points to r
     */
    public JDFResourceLink getNewOldLink(boolean bNew)
    {
        VElement v = getChildElementVector(null, null, null, true, 0, false);
        int iNew = bNew ? 0 : 1;
        //remove any non-reslinks, e.g. comments
        for(int i=v.size()-1;i>=0;i--){
            if(!(v.elementAt(i) instanceof JDFResourceLink))
                v.removeElementAt(i);
        }

        if (v.size() < iNew)
        {
            return null;
        }

        return (JDFResourceLink) v.elementAt(iNew);
    }

    /**
     * replace
     * @param newLink node to insert
     * @return the updated element
     */
    public JDFResourceLink updateLink(JDFResourceLink newLink)
    {
        VElement v = getResourceLinkVector();

        if (v.size() > 2)
        {
            throw new JDFException("JDFResourceLink.UpdateLink invalid  ResourceAudit");
        }

        // update of an update, delete the first element and assume the second is the real original
        if (v.size() > 1)
        {
            ((KElement) v.elementAt(0)).deleteNode();
            v.remove(v.elementAt(0));
        }

        // the updated link is the first
        JDFResourceLink resLink = (JDFResourceLink)copyElement(newLink, null);

        if (v.size() > 0)
        {
            resLink = (JDFResourceLink) insertBefore(resLink, (JDFNode) v.elementAt(0));
        }
        return resLink;
    }


    /**
     * Get the vector of ResourceLinks
     * @return VElement: the resource links in this
     */
    public VElement getResourceLinkVector()
    {
        VElement v = getChildElementVector(null, null, null, true, 0, false);
        for (int i = v.size() - 1; i >= 0; i--)
        {
            JDFElement e = (JDFElement) v.elementAt(i);

            if (!(e instanceof JDFResourceLink))
            {
                v.remove(v.elementAt(i));
            }
        }
        return v;
    }

    /**
     * return a vector of unknown element nodenames
     * <p>
     * default: getUnknownElements(true, 999999)
     * 
     * @param bIgnorePrivate used by JDFElement during the validation
     * @param nMax           maximum size of the returned vector
     * @return Vector - vector of unknown element nodenames
     * 
     * !!! Do not change the signature of this method
     */
    public Vector getUnknownElements(boolean bIgnorePrivate, int nMax)
    {
        if(bIgnorePrivate)
            bIgnorePrivate=false; // dummy to fool compiler
        return getUnknownPoolElements(EnumPoolType.ResourceLinkPool,nMax);
    }
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
            if(v2.elementAt(i) instanceof JDFResourceLink)
                n++;
        }
        if(n==0)
            vs.add("ResourceLink");

        return vs;
    }
    // the following are prerelease errata in JDF 1.3
    
    /**
     * set all parts to those defined in vParts
     * @param vParts vector of attribute maps for the parts
     */
    public void setPartMapVector(VJDFAttributeMap vParts)
    {
        super.setPartMapVector(vParts);
    }

    /**
     * set all parts to those defined by mPart
     * @param mPart attribute map for the part to set
     */
    public void setPartMap(JDFAttributeMap mPart)
    {
        super.setPartMap(mPart);
    }

    /**
     * remove the part defined by mPart
     * @param mPart attribute map for the part to remove
     */
    public void removePartMap(JDFAttributeMap mPart)
    {
        super.removePartMap(mPart);
    }

    /**
     * check whether the part defined in mPart is included
     * @param mPart attribute map to look for
     * @return boolean - returns true if the part exists
     */
    public boolean hasPartMap(JDFAttributeMap mPart)
    {
        return super.hasPartMap(mPart);
    }
 
    
}
