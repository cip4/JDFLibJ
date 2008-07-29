/*
 * The CIP4 Software License, Version 1.0
 *
 *
 * Copyright (c) 2001-2008 The International Cooperation for the Integration of
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
/**
 *
 * Copyright (c) 2001 Heidelberger Druckmaschinen AG, All Rights Reserved.
 *
 * JDFResourceLinkPool.java
 *
 * Last changes
 *
 * 2002-07-02   JG - added IsValid()
 * 2002-07-02   JG - now inherits from JDFPool
 * 2002-07-02   JG - added GetPoolChild, GetPoolChildren
 * 2002-07-02   JG - GetPartValues() first parameter is now JDFRessource::EnumPartIDKey
 *
 */
package org.cip4.jdflib.pool;

import java.util.HashSet;
import java.util.Vector;

import org.apache.xerces.dom.CoreDocumentImpl;
import org.cip4.jdflib.core.AttributeName;
import org.cip4.jdflib.core.ElementInfo;
import org.cip4.jdflib.core.JDFConstants;
import org.cip4.jdflib.core.JDFElement;
import org.cip4.jdflib.core.JDFException;
import org.cip4.jdflib.core.JDFPartAmount;
import org.cip4.jdflib.core.JDFResourceLink;
import org.cip4.jdflib.core.VElement;
import org.cip4.jdflib.core.VString;
import org.cip4.jdflib.core.JDFResourceLink.EnumUsage;
import org.cip4.jdflib.datatypes.JDFAttributeMap;
import org.cip4.jdflib.datatypes.VJDFAttributeMap;
import org.cip4.jdflib.node.JDFNode;
import org.cip4.jdflib.node.JDFNode.EnumProcessUsage;
import org.cip4.jdflib.resource.JDFResource;
import org.cip4.jdflib.resource.JDFResource.EnumPartIDKey;

/**
 *
 */
public class JDFResourceLinkPool extends JDFPool
{
    private static final long serialVersionUID = 1L;

    /**
     * Constructor for JDFResourceLinkPool
     * @param myOwnerDocument
     * @param qualifiedName
     */
    public JDFResourceLinkPool(
            CoreDocumentImpl myOwnerDocument,
            String qualifiedName)
    {
        super(myOwnerDocument, qualifiedName);
    }

    /**
     * Constructor for JDFResourceLinkPool
     * @param myOwnerDocument
     * @param myNamespaceURI
     * @param qualifiedName
     */
    public JDFResourceLinkPool(
            CoreDocumentImpl myOwnerDocument,
            String myNamespaceURI,
            String qualifiedName)
    {
        super(myOwnerDocument, myNamespaceURI, qualifiedName);
    }

    /**
     * Constructor for JDFResourceLinkPool
     * @param myOwnerDocument
     * @param myNamespaceURI
     * @param qualifiedName
     * @param myLocalName
     */
    public JDFResourceLinkPool(
            CoreDocumentImpl myOwnerDocument,
            String myNamespaceURI,
            String qualifiedName,
            String myLocalName)
    {
        super(myOwnerDocument, myNamespaceURI, qualifiedName, myLocalName);
    }

    @Override
    protected ElementInfo getTheElementInfo()
    {
        return super.getTheElementInfo().updateAdd(JDFResourcePool.getLinkInfoTable());
    }
    //**************************************** Methods *********************************************
    /**
     * toString
     *
     * @return String
     */
    @Override
    public String toString()
    {
        return "JDFResourceLinkPool[ --> " + super.toString() + " ]";
    }

    /**
     * GetLinks - get the links matching mLinkAtt out of the resource link pool
     * <p>
     * default: GetLinks(null)
     *
     * @param mLinkAtt the attributes to search for
     *
     * @return mLinkAtt vector all all elements matching the condition mLinkAtt
     * @deprecated use getPoolChildren() 
     */
    @Deprecated
    public VElement getLinks(JDFAttributeMap mLinkAtt)
    {
        return getPoolChildren(null, mLinkAtt, null);
    }

    /**
     * GetLinks - get the links matching elementName/nameSpaceURI from the resource pool<br>
     * if you need all links in the doc, call getLinks from JDFElement
     * <p>
     * default: GetLinks(null, null)
     * 
     * @param elementName  Name of the Linked resource
     * @param nameSpaceURI the namespace to search in
     *
     * @return VElement - vector all all elements matching the condition mLinkAtt
     * @deprecated use getPoolChildren()
     */
    @Deprecated
    public VElement getLinks(String elementName, String nameSpaceURI)
    {
        return getPoolChildren(elementName, null, nameSpaceURI);
    }

    /**
     * Get the linked resources matching some conditions
     * <p>
     * default: GetLinkedResources(null, null, null, false)
     *
     * @param resName     type(Name) of the resource to get
     * @param mLinkAtt    the link attribute to search for
     * @param mResAtt     attribute to search for
     * @param bFollowRefs if true search all HRefs and add them to the list
     * 
     * @return VElement - vector with all Resources matching the conditions
     */
    public VElement getLinkedResources(
            String resName,
            JDFAttributeMap mLinkAtt,
            JDFAttributeMap mResAtt,
            boolean bFollowRefs)
    {
        final VElement v  = getPoolChildren(null, mLinkAtt, null);
        final VElement vL = new VElement();
        if (resName!=null && resName.endsWith(JDFConstants.LINK))
            resName=resName.substring(0,resName.length()-4); // remove link

        final int size = (v==null) ? 0 : v.size();
        for (int i = 0; i < size; i++)
        {
            final JDFResourceLink l = (JDFResourceLink) v.elementAt(i);

            JDFResource linkRoot = l.getLinkRoot();
            if ((linkRoot != null) && 
                    ((resName==null) || resName.equals(JDFConstants.EMPTYSTRING) || linkRoot.getLocalName().equals(resName)))
            {
                if (linkRoot.includesAttributes(mResAtt, true))
                {
                    vL.addElement(linkRoot);
                    if (bFollowRefs)
                    {
                        vL.appendUnique(linkRoot.getvHRefRes(bFollowRefs,true));
                    }
                }
            }
        }
        return vL;
    }

    /**
     * GetInOutLinks - get the links from the pool (input or output)
     * <p>
     * default: GetInOutLinks(bInOut, true, JDFConstants.WILDCARD, JDFConstants.WILDCARD)
     *
     * @param bInOut  what kind of links you want to have (true for input)
     * @param bLink   if true, return the resource links. if false return the resources
     * @param resType type of the resource to get ( * for all)
     * @param resProcUsage process usage of the resource to get (* for all)
     * @deprecated use getInOutLinks with EnumUsage signature
     * @return VElement - Vector with the found resource links 
     */
    @Deprecated
    public VElement getInOutLinks(boolean bInOut, boolean bLink, String resName,String resProcUsage)
    {
        return getInOutLinks(bInOut ? EnumUsage.Input : EnumUsage.Output, bLink, resName,EnumProcessUsage.getEnum(resProcUsage));
    }

    /**
     * GetInOutLinks - get the links from the pool (input or output)
     * <p>
     * default: GetInOutLinks(null, true, null, null)
     *
     * @param usage     what kind of links you want to have (input, output). If null all are selected.
     * @param bLink     if true, return the resource links. if false return the resources
     * @param resName   name of the resource to get ( * for all)
     * @param procUsage process usage of the resource to get 
     * 
     * @return VElement - Vector with the found resource links
     */
    public VElement getInOutLinks(EnumUsage usage,boolean bLink,String resName, EnumProcessUsage procUsage)
    {
        final JDFAttributeMap mA = (usage!=null) ? new JDFAttributeMap(AttributeName.USAGE,usage.getName()) : null;

        VElement v = getPoolChildren(null, mA, null);
        final int size = (v==null) ? 0 : v.size();
        for (int i = size - 1; i >= 0; i--)
        {
            final JDFResourceLink li = (JDFResourceLink) v.elementAt(i);
            if (!isWildCard(resName))
            {
                if (!li.getLinkedResourceName().equals(resName))
                {
                    v.removeElementAt(i);
                    continue;
                }
            }
            if (procUsage!=null && !isWildCard(procUsage.getName()))
            {
                if (!procUsage.equals(li.getEnumProcessUsage()))
                {
                    v.removeElementAt(i);
                    continue;
                }
            }
        }

        if (!bLink)
        {
            v = resourceVector(v, resName);
        }

        return v;
    }

    /**
     * ResourceVector - convert a link vector to a resource vector
     *
     * @param linkVector vector to convert
     * @param resType    kind of resType to add ( <code>*</code> for all)
     *
     * @return VElement - the converted vector
     */
    public static VElement resourceVector(VElement linkVector, String resType)
    {
        if(linkVector==null)
            return null;

        final VElement v = new VElement();
        final boolean bResTypeWildcard = isWildCard(resType);

        for (int i = 0; i < linkVector.size(); i++)
        {
            final JDFResourceLink l = (JDFResourceLink) linkVector.elementAt(i);

            // 120803 rp follow parts of resource links
            if (bResTypeWildcard || (l.getLinkedResourceName().equals(resType)))
            {
                final VElement vRes = l.getTargetVector(-1);
                v.addAll(vRes);
            }
        }

        return v;
    }

    /**
     * AppendResource - append resource r to this link pool
     * <p>
     * default: AppendResource(r, input, false)
     * 
     * @param r      the resource to append
     * @param input  usage of the link (true = inout, false = output)
     * @param bForce if true create the link, even though it already exists - now ignored since it is useless
     *
     * @return JDFResourceLink - link to appended resource
     * 
     * @throws JDFException if r is not in the same document as this
     * 
     * @deprecated
     */
    @Deprecated
    public JDFResourceLink appendResource(JDFResource r, boolean input, boolean bForce)
    {
        if(bForce)
            bForce=true; // fool compiler

        return linkResource(r, input?EnumUsage.Input:EnumUsage.Output,null);
    }


    /**
     * getLink - get the resourcelink that resides in the ResourceLinkPool of this node and references
     * the resource r
     * <p>
     * default: getLink(r, EnumUsage.Input, null)
     *
     * @param r             the resource you are searching a link for
     * @param usage         usage of the link (input/output)
     * @param processUsage  ProcessUsage of the link
     *
     * @return JDFResourceLink - the resource link you were searching for. If not found, a new
     *                            empty JDFResourceLink is returned.
     */
    public JDFResourceLink getLink(JDFResource r, EnumUsage usage, EnumProcessUsage processUsage)
    {

        if (r==null || !r.hasAttribute(AttributeName.ID))
        {
            return null;
        }

        // get any possible links
        final VElement v = getInOutLinks(usage, true, null,processUsage);

        // is it the right one?
        final int vSize = (v == null) ? 0 : v.size();
        for (int i = 0; i < vSize; i++)
        {
            final JDFResourceLink resLink = (JDFResourceLink) v.elementAt(i);

            if (resLink != null && 
                    resLink.getrRef().equals(r.getID()) && 
                    resLink.getNodeName().equals(r.getLinkString()))
            {
                return resLink;
            }
        }

        // nothing found
        return null;
    }

    /**
     * linkResource - link resource r to this link pool 
     * <p>
     * default: linkResource(r, usage, null)
     *
     * @param r      the resource to link
     * @param usage  usage of the link 
     * @param processUsage processUsage of the link, null if none
     *
     * @return JDFResourceLink - new link  resource
     * 
     * @throws JDFException - if <code>r</code> is not in the same document as <code>this</code>
     */
    public JDFResourceLink linkResource(JDFResource r, JDFResourceLink.EnumUsage usage, EnumProcessUsage processUsage)
    {
        final String s = r.getID();

        if (usage==null || s.equals(JDFConstants.EMPTYSTRING))
            return null;

        final JDFResourceLink rl = (JDFResourceLink) appendElement(r.getLinkString(), r.getNamespaceURI());
        rl.setTarget(r);
        rl.setUsage(usage);
        rl.setProcessUsage(processUsage);

        //move the resource to the closest common ancestor if it is not already an ancestor of this
        JDFNode parent=r.getParentJDF();

        //move the resource to the closest common ancestor if it is not already an ancestor of this
        while (parent!=null && !parent.isAncestor(this))
        {
            parent=r.getParentJDF();
            if(parent==null)
                break;
            parent=parent.getParentJDF();
            if(parent==null)
            {
                rl.deleteNode(); // cleanup
                throw new JDFException("JDFResourceLink appendResource resource is not in the same document");
            }

            r = (JDFResource)parent.getCreateResourcePool().moveElement(r, null);
        }  

        return rl;
    }

    /**
     * getPartMapVector - get the part map vector from the actual element
     *
     * @param bComplete if true, expand all parts defined in PartIDKeys
     *
     * @return VJDFAttributeMap - map of all parts linked by this resourcelinkpool
     */
    public VJDFAttributeMap getPartMapVector(boolean bComplete)
    {
        final VJDFAttributeMap vMap = new VJDFAttributeMap();

        if (bComplete)
        {
            final Vector vKeys = getPartIDKeys();

            final int keySize = vKeys.size();
            final Vector vvValues = new Vector();
            final int pI[] = new int[keySize];
            final int pISize[] = new int[keySize];

            for (int i = 0; i < keySize; i++)
            {
                if (EnumPartIDKey.getEnum((String)vKeys.elementAt(i))!=null)
                {
                    vvValues.addElement(
                            getPartValues(JDFResource.EnumPartIDKey.getEnum(i)));

                    pI[i] = 0;
                    pISize[i] = ((Vector) vvValues.elementAt(i)).size();
                }
            }


            while (true)
            {
                final JDFAttributeMap m = new JDFAttributeMap();
                boolean bDone = false;

                for (int i = 0; i < keySize; i++)
                {
                    m.put(
                            (String) vKeys.elementAt(i),
                            (String) ((Vector) vvValues.elementAt(i)).elementAt(pI[i]));
                }

                vMap.addElement(m);

                for (int n = 0; n < keySize; n++)
                {
                    if (++pI[n] >= pISize[n])
                    {
                        pI[n] = 0;

                        if (n == keySize - 1)
                        {
                            bDone = true;
                        }
                    }
                    else
                    {
                        break;
                    }
                }

                if (bDone)
                {
                    break;
                }
            }
        }
        else
        {
            final VElement links = getPoolChildren(null, null, null);
            if (links != null)
            {
                for (int l = 0; l < links.size(); l++)
                {
                    final JDFResourceLink link = (JDFResourceLink) links.elementAt(l);
                    final VJDFAttributeMap tempMap = link.getPartMapVector();

                    for (int i = 0; i < tempMap.size(); i++)
                    {
                        final JDFAttributeMap mTmp = tempMap.elementAt(i);
                        boolean bFound = false;
                        boolean bReplace = false;

                        for (int j = vMap.size() - 1; j >= 0; j--)
                        {
                            // backwards because of potential erasing
                            final JDFAttributeMap mAtt = vMap.elementAt(j);

                            if (!bReplace && mAtt.subMap(mTmp))
                            {
                                bFound = true;
                                break;
                            }

                            if (mTmp.subMap( mAtt))
                            {
                                if (bReplace)
                                {
                                    vMap.setElementAt(mTmp, j);
                                }
                                else
                                { // already replaced one, clear all other matches
                                    vMap.clear();
                                }

                                bReplace = true;
                            }
                        }

                        if (!bFound)
                        {
                            vMap.add(mTmp);
                        }
                    }
                }
            }
        }

        return vMap;
    }

    /**
     * get a vector of all part id keys linked
     *
     * @return Vector
     */
    public Vector getPartIDKeys()
    {
        final VString vs = new VString();
        final VElement links = getPoolChildren(null, null, null);
        if (links != null)
        {
            for (int i = 0; i < links.size(); i++)
            {
                final JDFResourceLink link = (JDFResourceLink) links.elementAt(i);
                final VString keys = link.getLinkRoot().getPartIDKeys();

                for (int j = 0; j < keys.size(); j++)
                {
                    if (!vs.contains(keys.elementAt(j)))
                    {
                        vs.addElement(keys.elementAt(j));
                    }
                }
            }
        }

        return vs;
    }

    /**
     * GetPartValues - get a list of the values for attribute partType within the leaves of all
     * linked resources
     *
     * @param partType the attribute name you which to get the values
     *
     * @return Vector - vector with all values of the attribute partType
     */
    public Vector getPartValues(JDFResource.EnumPartIDKey partType)
    {
        final Vector vs = new Vector();
        final VElement links = getPoolChildren(null, null, null);
        if (links != null)
        {
            for (int i = 0; i < links.size(); i++)
            {
                final JDFResourceLink link = (JDFResourceLink) links.elementAt(i);
                final Vector keys = link.getLinkRoot().getPartValues(partType);

                for (int j = 0; j < keys.size(); j++)
                {
                    if (!vs.contains(keys.elementAt(j)))
                    {
                        vs.addElement(keys.elementAt(j));
                    }
                }
            }
        }

        return vs;
    }

    /**
     * Gets all children with the attribute <code>name, mAttrib, nameSpaceURI</code> out of the pool
     * 
     * @param name         name of the Child
     * @param mAttrib     a attribute to search for
     * @param nameSpaceURI the namespace uri
     * 
     * @return VElement: a vector with all elements in the pool matching the conditions
     * 
     * default: getPoolChildren(null, null, null)
     */
    public VElement getPoolChildren( String strName,  JDFAttributeMap mAttrib, String nameSpaceURI)
    {
        final VElement v = getPoolChildrenGeneric(strName, mAttrib, nameSpaceURI);
        if(v==null)
            return null;
        for (int i = v.size() - 1; i >= 0; i--)
        {
            if (!(v.elementAt(i) instanceof JDFResourceLink))
            {
                v.removeElementAt(i);
            }
        }    
        return v.size()==0 ? null : v;
    }

    /**
     * get a child resource from the pool matching the parameters
     *
     * @param i            the index of the child, or -1 to make a new one.
     * @param strName      the name of the element
     * @param mAttrib      the attribute of the element
     * @param nameSpaceURI the namespace to search in
     * 
     * @return JDFElement: the pool child matching the above conditions
     */
    public JDFResourceLink getPoolChild(
            int i,
            String strName,
            JDFAttributeMap mAttrib,
            String nameSpaceURI)
    {
        final VElement v = getPoolChildren(strName, mAttrib, nameSpaceURI);
        final int size = (v==null) ? 0 : v.size();
        if (i < 0)
        {
            i = size + i;
            if (i < 0)
            {
                return null;
            }
        }

        if (size <= i)
        {
            return null;
        }

        return (JDFResourceLink) v.elementAt(i);
    }

    /**
     * return a vector of unknown element nodenames
     * <p>
     * default: GetInvalidElements(true, 999999)
     * 
     * @param bIgnorePrivate used by JDFElement during the validation
     * @param nMax           maximum size of the returned vector
     * @return Vector - vector of unknown element nodenames
     * 
     * !!! Do not change the signature of this method
     */
    @Override
    public Vector getUnknownElements(boolean bIgnorePrivate, int nMax)
    {
        bIgnorePrivate=!bIgnorePrivate; // nop to kill compiler warning
        return getUnknownPoolElements(JDFElement.EnumPoolType.ResourceLinkPool, nMax);
    }

    /**
     * get inter-resource linked resource ids
     * @param vDoneRefs (null, used for recursion)
     * @param bRecurse  if true, also return recursively linked IDS
     * @return vElement: the vector of referenced resource ids
     */
    @Override
    public HashSet getAllRefs(HashSet vDoneRefs, boolean bRecurse)
    {
        VElement vResourceLinks = getPoolChildren(null, null, null);
        final int size = (vResourceLinks == null) ? 0 : vResourceLinks.size();
        for (int i = 0; i < size; i++)
        {
            JDFResourceLink rl = (JDFResourceLink) vResourceLinks.elementAt(i);
            if(!vDoneRefs.contains(rl))
            {
                vDoneRefs.add(rl);
                if(bRecurse)
                {
                    JDFResource r=rl.getLinkRoot();
                    if(r!=null && !vDoneRefs.contains(r))
                    {
                        vDoneRefs=r.getAllRefs(vDoneRefs, bRecurse);                                  	
                    }
                }
            }
        }

        return vDoneRefs;
    }

}
