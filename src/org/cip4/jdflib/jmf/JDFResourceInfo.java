/*
 *
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
 class JDFResourceInfo extends JDFResource
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
import org.cip4.jdflib.auto.JDFAutoResourceInfo;
import org.cip4.jdflib.core.AttributeName;
import org.cip4.jdflib.core.JDFConstants;
import org.cip4.jdflib.core.JDFElement;
import org.cip4.jdflib.core.JDFException;
import org.cip4.jdflib.core.JDFResourceLink;
import org.cip4.jdflib.core.KElement;
import org.cip4.jdflib.core.VElement;
import org.cip4.jdflib.core.VString;
import org.cip4.jdflib.core.JDFResourceLink.EnumUsage;
import org.cip4.jdflib.datatypes.JDFAttributeMap;
import org.cip4.jdflib.datatypes.VJDFAttributeMap;
import org.cip4.jdflib.node.JDFNode.EnumProcessUsage;
import org.cip4.jdflib.pool.JDFAmountPool;
import org.cip4.jdflib.resource.JDFResource;
import org.cip4.jdflib.resource.JDFResource.EnumResStatus;


public class JDFResourceInfo extends JDFAutoResourceInfo
{
    private static final long serialVersionUID = 1L;

    /**
     * Constructor for JDFResourceInfo
     * @param myOwnerDocument
     * @param qualifiedName
     */
    public JDFResourceInfo(
            CoreDocumentImpl myOwnerDocument,
            String qualifiedName)
    {
        super(myOwnerDocument, qualifiedName);
    }

    /**
     * Constructor for JDFResourceInfo
     * @param myOwnerDocument
     * @param myNamespaceURI
     * @param qualifiedName
     */
    public JDFResourceInfo(
            CoreDocumentImpl myOwnerDocument,
            String myNamespaceURI,
            String qualifiedName)
    {
        super(myOwnerDocument, myNamespaceURI, qualifiedName);
    }

    /**
     * Constructor for JDFResourceInfo
     * @param myOwnerDocument
     * @param myNamespaceURI
     * @param qualifiedName
     * @param myLocalName
     */
    public JDFResourceInfo(
            CoreDocumentImpl myOwnerDocument,
            String myNamespaceURI,
            String qualifiedName,
            String myLocalName)
    {
        super(myOwnerDocument, myNamespaceURI, qualifiedName, myLocalName);
    }
    
    /**
     * toString()
     * @return String
     */
    public String toString()
    {
        return "JDFResourceInfo[  --> " + super.toString() + " ]";
    }

    /** 
     * get the resource defined by <code>resName</code>
     * @param resName name of the resource to get/create
     * @return JDFCostCenter The element
     */
    public JDFResource getCreateResource(String resName)
    {
        KElement e = getCreateElement(resName, JDFConstants.EMPTYSTRING, 0);
        if(e instanceof JDFResource)
        {
            return (JDFResource) e;
        }
        throw new JDFException(
        "JDFResouceInfo.getCreateResource tried to create a JDFElement instead of a JDFResource");
    }
    
    /**
     * get resource defined by <code>resName</code>
     * @param resName name of the resource to get
     * @return JDFResource: the element
     */
    public JDFResource getResource(String resName)
    {
        for(int i=0;true;i++)
        {
            KElement e = getElement(resName, null, i);
            if(e==null)
                return null;
            if(e instanceof JDFResource)
            {
                return(JDFResource) e;
            }
        }
    }

    /**
     * append resource
     * @param resName name of the resource to append
     */
    public JDFResource appendResource(String resName)
    {
        KElement e = appendElement(resName, null);
        if(e instanceof JDFResource)
        {
            return (JDFResource) e;
        }
        throw new JDFException(
        "JDFResouceInfo.appendResource tried to append a JDFElement instead of a JDFResource");
    }
    
    
    /**
     * return a vector of unknown element nodenames
     * <p>
     * default: getUnknownElements(true, 999999)
     * 
     * @param bIgnorePrivate used by JDFElement during the validation
     * @param nMax           maximum number of elements to get
     * 
     * @return Vector - vector of unknown element nodenames
     */
    public Vector getUnknownElements(boolean bIgnorePrivate, int nMax)
    {
    	/* !!! Do not change the signature of this method */
        if(bIgnorePrivate)
            bIgnorePrivate=false; // dummy to fool compiler
        return getUnknownPoolElements(JDFElement.EnumPoolType.ResourcePool, nMax);
    }
    
    /**
     * Method getInvalidElements
     * <p>
     * default: GetInvalidElements(level, true, 999999)
     * @param level          validation level
     * @param bIgnorePrivate
     * @param nMax           maximum number of elements to get
     * 
     * @return VString - vector of names of invalid elements
     */
    public VString getInvalidElements(EnumValidationLevel level,
            boolean bIgnorePrivate, int nMax)
    {
        VString s=getInvalidElements_JDFElement(level, bIgnorePrivate, nMax);
        if(s.size()>nMax)
            return s;
        
        VElement v=getChildElementVector(null, null, null, true, 0,false);
        int size = v.size();
        if(size>1)
        {
            // remove anything but resources
            for(int i=size-1;i>=0;i--)
            {
                if(!(v.elementAt(i)instanceof JDFResource))
                {
                    v.remove(i);
                }                   
            }
            size = v.size(); // must refresh size due to removes
            // more than one resource --> evil!
            if(size>1)
            {                
                for(int j=0;j<size;j++)
                {
                    s.appendUnique(v.item(j).getLocalName());
                }
            }
        }
        return s;
    }
   
    /**
     * get part map vector
     * @return VJDFAttributeMap: vector of attribute maps, one for each part
     */
    public VJDFAttributeMap getPartMapVector()
    {
        return super.getPartMapVector();
    }
    
    /**
     * set all parts to those defined by vParts
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
     * remove the part defined in mPart
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

    /**
     * sets all relevant parameters of this to the values specified 
     * in resourceLink or its linked resource or JDF node
     * 
     * @param resourceLink the resourceLink to extract the information from
     * @param rqp          parameters
     */
    public void setLink(JDFResourceLink resourceLink, JDFResourceQuParams rqp)
    {
        if(resourceLink==null)
            return;
        JDFAmountPool ap=resourceLink.getAmountPool();
        if(ap!=null)
        {
            copyElement(ap, null);
        }
        else
        {
            if(resourceLink.hasAttribute(AttributeName.ACTUALAMOUNT))
                setActualAmount(resourceLink.getActualAmount(null));
            if(resourceLink.hasAttribute(AttributeName.AMOUNT))
                setAmount(resourceLink.getAmount(null));
        }
        setProcessUsage(resourceLink.getEnumProcessUsage());
        
        final JDFResource r=resourceLink.getTarget();
        if(r==null && rqp!=null)
        {
            rqp.setExact(false);
        }
        
        boolean bExact= rqp==null ? false : rqp.getExact();
        if(!bExact || r==null) // if we do not have a resource let's limp along and provide as much as we have
        {
            setResourceName(resourceLink.getLinkedResourceName());
            setAttribute(AttributeName.RESOURCEID,resourceLink.getrRef());
            final EnumUsage usage = resourceLink.getUsage();
            if(usage!=null)
                setAttribute(AttributeName.USAGE,usage.getName());
            if(r!=null && r.hasAttribute(AttributeName.PRODUCTID))
                setProductID(r.getProductID());
        }
        else
        {
            // create a copy of the resource in the original jdf
            JDFResource rr=(JDFResource)r.getParentNode_KElement().copyElement(r, null);
            rr.inlineRefElements(null, null, true);
            // move resource copy from the original node into this document
            moveElement(rr, null);
        }
    }
    
    /**
     * set ProcessUsage to the enum processusage
     *
     * @param processUsage
     */
    public void setProcessUsage(EnumProcessUsage processUsage)
    {
        setAttribute(AttributeName.PROCESSUSAGE, processUsage==null ? null : processUsage.getName(), null);
    }

    /**
     * if a Resource is available, return it's ProductID
     * @see org.cip4.jdflib.auto.JDFAutoResourceInfo#getProductID()
     */
    public String getProductID()
    {
        String _name=super.getResourceName();
        if(isWildCard(_name))
        {
            JDFResource r=getResource(null);
            if(r==null)
                return null;
            _name= r.getProductID();
        }
        return _name;
    }

    /**
    * if a Resource is available, return it's ID
     * @see org.cip4.jdflib.auto.JDFAutoResourceInfo#getResourceID()
     */
    public String getResourceID()
    {
        String _name=super.getResourceID();
        if(isWildCard(_name))
        {
            JDFResource r=getResource(null);
            if(r==null)
                return null;
            _name= r.getID();
        }
        return _name;
    }

    /**
     * if a Resource is available, return it's name
     * @see org.cip4.jdflib.auto.JDFAutoResourceInfo#getResourceName()
     */
    public String getResourceName()
    {
        
       String _name=super.getResourceName();
       if(isWildCard(_name))
       {
           JDFResource r=getResource(null);
           if(r==null)
               return null;
           _name= r.getLocalName();
       }
       return _name;
    }

    /**
     *     
     * if a Resource is available, return it's status
     * @see org.cip4.jdflib.auto.JDFAutoResourceInfo#getResStatus()
     */
    public EnumResStatus getResStatus()
    {
        EnumResStatus s =super.getResStatus();
        if(s==null)
        {
            JDFResource r=getResource(null);
            if(r==null)
                return null;
            s= r.getResStatus(false);
        }
        return s;
    }

}




