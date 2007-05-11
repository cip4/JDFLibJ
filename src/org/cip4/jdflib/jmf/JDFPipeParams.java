/*
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
 *
 */
/**
 ==========================================================================
 class JDFPipeParams extends JDFResource
 ==========================================================================
 @COPYRIGHT Heidelberger Druckmaschinen AG, 1999-2001
 ALL RIGHTS RESERVED
 @Author: sabjon@topmail.de   using a code generator
 Warning! very preliminary test version. Interface subject to change without prior notice!
 Revision history:    ...
 **/





package org.cip4.jdflib.jmf;

import java.util.Vector;

import org.apache.xerces.dom.CoreDocumentImpl;
import org.cip4.jdflib.auto.JDFAutoPipeParams;
import org.cip4.jdflib.core.AttributeName;
import org.cip4.jdflib.core.JDFConstants;
import org.cip4.jdflib.core.JDFElement;
import org.cip4.jdflib.core.JDFException;
import org.cip4.jdflib.core.JDFResourceLink;
import org.cip4.jdflib.core.KElement;
import org.cip4.jdflib.core.VElement;
import org.cip4.jdflib.core.JDFResourceLink.EnumUsage;
import org.cip4.jdflib.datatypes.JDFAttributeMap;
import org.cip4.jdflib.datatypes.VJDFAttributeMap;
import org.cip4.jdflib.node.JDFNode;
import org.cip4.jdflib.resource.JDFResource;



//----------------------------------
public class JDFPipeParams extends JDFAutoPipeParams
{
    private static final long serialVersionUID = 1L;

    /**
     * Constructor for JDFPipeParams
     * @param myOwnerDocument
     * @param qualifiedName
     */
    public JDFPipeParams(
            CoreDocumentImpl myOwnerDocument,
            String qualifiedName)
    {
        super(myOwnerDocument, qualifiedName);
    }

    /**
     * Constructor for JDFPipeParams
     * @param myOwnerDocument
     * @param myNamespaceURI
     * @param qualifiedName
     */
    public JDFPipeParams(
            CoreDocumentImpl myOwnerDocument,
            String myNamespaceURI,
            String qualifiedName)
    {
        super(myOwnerDocument, myNamespaceURI, qualifiedName);
    }

    /**
     * Constructor for JDFPipeParams
     * @param myOwnerDocument
     * @param myNamespaceURI
     * @param qualifiedName
     * @param myLocalName
     */
    public JDFPipeParams(
            CoreDocumentImpl myOwnerDocument,
            String myNamespaceURI,
            String qualifiedName,
            String myLocalName)
    {
        super(myOwnerDocument, myNamespaceURI, qualifiedName, myLocalName);
    }
    
    public String toString()
    {
        return "JDFPipeParams[  --> " + super.toString() + " ]";
    }

    /**
     * Gets all ResourceLink children with the attribute name, mAttrib, nameSpaceURI from the pool
     * @param name name of the Child
     * @param mAttrib a attribute to search for
     * @return VElement: a vector with all resource links in the pool matching the conditions
     * @deprecated use getResourceLink()
     */
    final public VElement getResourceLinks(String nam, JDFAttributeMap mAttrib, String nameSpaceURI)
    {
        VElement v = getChildElementVector(nam,nameSpaceURI,mAttrib,true, 0, false);
        
        for(int i=v.size()-1; i>=0; i--)
        {
            JDFElement e = (JDFElement) v.elementAt(i);
            if(!(e instanceof JDFResourceLink)){
                v.remove(i);
            }
        }
        return v;
    }
    
    /**
     * Gets the ResourceLink from the PipeParams element
     * @return VElement: a vector with all resource links in the pool matching the conditions
     */
    final public JDFResourceLink getResourceLink()
    {
        VElement v = getChildElementVector(null,null,null,true, 0, false);
        
        for(int i=0;i<v.size(); i++)
        {
            KElement e =  v.item(i);
            if(e instanceof JDFResourceLink){
                return (JDFResourceLink) e;
            }
        }
        return null;
    }
    
    /**
     * get the vector of unknown element nodenames
     * <p>
     * default: GetUnknownElements(true, 999999)
     * @param  bIgnorePrivate used by JDFElement during the validation
     * !!! Do not change the signature of this method
     * @param nMax maximum size of the returned vector
     * @return Vector - vector of unknown element nodenames
     */
    
    public Vector getUnknownElements(boolean bIgnorePrivate, int nMax)
    {
        if(bIgnorePrivate) // dummy to soothe compiler warning
            bIgnorePrivate=false;
        
        return getUnknownPoolElements(JDFElement.EnumPoolType.PipeParams, nMax);
    }
    
    /** 
     * get resource defined by <code>resName</code>, create if it doesn't exist
     * @param resName name of the resource to get/create
     * @return JDFResource: the element
     */
    public JDFResource getCreateResource(String resName)
    {
        JDFResource r = null;
        KElement e = getCreateElement(resName, JDFConstants.EMPTYSTRING, 0);
        if(e instanceof JDFResource)
        {
            r = (JDFResource) e;
            r.init();
        }
        else
        {
            throw new JDFException(
            "JDFPipeParams.getCreateResource tried to create a JDFElement instead of a JDFResource");
        }
        return r;
    }
    
    /**
     * get resource defined by <code>resName</code>
     * @param resName name of the resource to get; if null get the resource that is linked by the reslink
     * @return JDFResource: the element
     */
    public JDFResource getResource(String resName)
    {
        if(resName==null)
        {
            JDFResourceLink rl=getResourceLink();
            if(rl!=null)
                return rl.getTarget();
            VElement v=getChildElementVector(null, null, null, true, 0, false);
            
            for(int i=0;i<v.size(); i++)
            {
                KElement e =  v.item(i);
                if(e instanceof JDFResource){
                    return (JDFResource) e;
                }
            }
            return null;
        }
        JDFResource r = null;
        KElement e = getElement(resName, null, 0);
        if(e instanceof JDFResource)
        { 
            r = (JDFResource) e;
        }
        else
        {
            throw new JDFException("JDFPipeParams.getResource tried to return a JDFElement instead of a JDFResource"); 
        }
        return r;
    }
    
    /**
     * append  Resource
     * @param resName name of the resource to append
     */
    public JDFResource appendResource(String resName)
    {
        KElement   e = appendElement(resName, null);
        if(!(e instanceof JDFResource))
        {
            throw new JDFException("JDFpipeParams.appendResource tried to return a JDFElement instead of a JDFResource: "+resName); 
        }
        return (JDFResource)e;
    }
    
    /**
     * append  ResourceLink
     * @param linkName name of the ResourceLink to append a link for
     * @param bInput if true, the link is an input link
     * @return JDFResourceLink: the appended element 
     */
    public JDFResourceLink appendResourceLink(String linkName, boolean bInput)
    {
        if(!linkName.endsWith("Link"))
            linkName+="Link";
        
        JDFResourceLink rl = null;
        if(getResourceLink()!=null)
            throw new JDFException("JDFpipeParams.appendResourceLink tried to append an additional link"); 
            
        KElement   e = appendElement(linkName, null);
        if(e instanceof JDFResourceLink)
        {
            rl = (JDFResourceLink) e;
            rl.setUsage(bInput ? EnumUsage.Input : EnumUsage.Output);
        }
        else
        {
            throw new JDFException("JDFpipeParams.appendResourceLink tried to return a JDFElement instead of a JDFResourceLink: "+linkName); 
        }
        return rl;
    }
    
    /**
     * apply the parameters in this to all appropriate resources in parentNode or one of parentNode's children
     * @param parentNode the node to search in
     * TODO implement resource handling
     */
    public void applyPipeToNode(JDFNode parentNode)
    {
        if(parentNode==null)
            return;
        Vector vNodes=parentNode.getvJDFNode(null,null,false);
        
        final int size = vNodes.size();
        for(int i=0;i<size;i++)
        {
            final JDFNode node=(JDFNode) vNodes.elementAt(i);
            if(!matchesNode(node))
                continue;
            final JDFElement.EnumNodeStatus status = getStatus(); // TODO: set Status
            node.setStatus(status);
            

//            final boolean isIncremental = (getUpdateMethod () == EnumUpdateMethod.Incremental);
            double dAmount = -1.0;
            JDFResourceLink rl=getResourceLink();
            
            if(rl!=null)
            {        
                JDFResourceLink rlNode=node.getLink(0,rl.getNodeName(),new JDFAttributeMap(AttributeName.RREF,rl.getrRef()),null);
                if(rlNode==null)
                    throw new JDFException("Applying pipeparams to inconsistent node: missing resourcelink: "+rl.getNodeName());

                VJDFAttributeMap vMap=rl.getPartMapVector();
                if(vMap==null)
                {
                    vMap=new VJDFAttributeMap();
                    vMap.add(null);
                }
                for(int j=0;j<vMap.size();j++)
                {
                    JDFAttributeMap map=vMap.elementAt(j);
                    dAmount = rl.getActualAmount(map);
                    rlNode.setActualAmount(dAmount, map);
                }
            }
        }
    }
    
    /**
     * @param node
     * @return
     */
    private boolean matchesNode(JDFNode node)
    {
        if(node==null)
            return false;
        if(hasAttribute(AttributeName.JOBID) && !getJobID().equals(node.getJobID(true)))
            return false;
        if(hasAttribute(AttributeName.JOBPARTID) && !getJobPartID().equals(node.getJobPartID(false)))
            return false;
        return true;
    }
    
}

