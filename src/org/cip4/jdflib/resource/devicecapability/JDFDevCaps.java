/*
 *
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
 * 
 */

/**
 *
 * Copyright (c) 2001 Heidelberger Druckmaschinen AG, All Rights Reserved.
 *
 * JDFDevCaps.java
 *
 * @author Elena Skobchenko
 *
 */


package org.cip4.jdflib.resource.devicecapability;

import java.util.Vector;

import org.apache.xerces.dom.CoreDocumentImpl;
import org.cip4.jdflib.auto.JDFAutoDevCaps;
import org.cip4.jdflib.core.AttributeName;
import org.cip4.jdflib.core.ElementName;
import org.cip4.jdflib.core.JDFConstants;
import org.cip4.jdflib.core.JDFElement;
import org.cip4.jdflib.core.JDFException;
import org.cip4.jdflib.core.JDFResourceLink;
import org.cip4.jdflib.core.KElement;
import org.cip4.jdflib.core.VElement;
import org.cip4.jdflib.core.VString;
import org.cip4.jdflib.core.XMLDoc;
import org.cip4.jdflib.core.JDFResourceLink.EnumUsage;
import org.cip4.jdflib.datatypes.JDFAttributeMap;
import org.cip4.jdflib.datatypes.JDFBaseDataTypes.EnumFitsValue;
import org.cip4.jdflib.node.JDFNode;
import org.cip4.jdflib.node.JDFNode.EnumProcessUsage;
import org.cip4.jdflib.pool.JDFResourceLinkPool;
import org.cip4.jdflib.resource.JDFResource;


//----------------------------------
public class JDFDevCaps extends JDFAutoDevCaps
{
    private static final long serialVersionUID = 1L;
    
    /**
     * Constructor for JDFDevCaps
     * @param ownerDocument
     * @param qualifiedName
     */
    public JDFDevCaps(
            CoreDocumentImpl myOwnerDocument,
            String qualifiedName)
    {
        super(myOwnerDocument, qualifiedName);
    }
    
    
    /**
     * Constructor for JDFDevCaps
     * @param ownerDocument
     * @param namespaceURI
     * @param qualifiedName
     */
    public JDFDevCaps(
            CoreDocumentImpl myOwnerDocument,
            String myNamespaceURI,
            String qualifiedName)
    {
        super(myOwnerDocument, myNamespaceURI, qualifiedName);
    }
    
    /**
     * Constructor for JDFDevCaps
     * @param ownerDocument
     * @param namespaceURI
     * @param qualifiedName
     * @param localName
     */
    public JDFDevCaps(
            CoreDocumentImpl myOwnerDocument,
            String myNamespaceURI,
            String qualifiedName,
            String myLocalName)
    {
        super(myOwnerDocument, myNamespaceURI, qualifiedName, myLocalName);
    }
    
    public String toString()
    {
        return "JDFDevCaps[  --> " + super.toString() + " ]";
    }
    
    
    /**
     * set attribute DevCapRef
     * @param String value: the value to set the attribute to
     */
    public void setDevCapRef(String value)
    {
        setAttribute(AttributeName.DEVCAPREF, value, null);
    }
    /**
     * set rRef to the value of devCap/@ID
     * @param dc the devCap to set
     */
    public void setDevCapRef(JDFDevCap dc)
    {
        dc.appendAnchor(null); // just in case it is missing
        final String id2 = dc.getID();
        setDevCapRef(id2);       
    }
    
    /**
     * get IDREF attribute DevCapRef
     * @return String the value of the attribute
     */
    public String getDevCapRef()
    {
        return getAttribute(AttributeName.DEVCAPREF, null, JDFConstants.EMPTYSTRING);
    }
    
    /**
     * set attribute ModuleRefs
     * @param VString value: the value to set the attribute to
     */
    public void setModuleRefs(VString value)
    {
        setAttribute(AttributeName.MODULEREFS, value, null);
    }
    
    /**
     * get IDREFS attribute ModuleRefs
     * @return VString the value of the attribute
     */
    public VString getModuleRefs()
    {
        return new VString (getAttribute(AttributeName.MODULEREFS, null, JDFConstants.EMPTYSTRING), null);
    }
    
    /**
     * get the one and only devCap - note that the spec allows * but recommends only one + additional actions
     * also search devCapPool for a matching element to DevCapRef
     * @return the devCap
     */
    public JDFDevCap getDevCap()
    {
        final String dcr=getAttribute(AttributeName.DEVCAPREF,null,null);
        if(dcr!=null){
            final JDFDevCapPool dcp=((JDFDeviceCap)getParentNode_KElement()).getDevCapPool();
            if(dcp==null)
                return null;
            return (JDFDevCap) dcp.getChildWithAttribute(ElementName.DEVCAP,AttributeName.ID,null,dcr,0,true);
        }
        return getDevCap(0);
    }
    
    /* ******************************************************
     // FitsValue Methods
      **************************************************************** */
    
    
    /**
     * getNamePath - gets the NamePath of this DevCaps in form 
     * "DevCapsName[Context=bbb, LinkUsage=ccc]/"
     *
     * @param boolean onlyNames - if true, returns only DevCapsName. Default=false
     * @return String - NamePath of this DevCaps
     * 
     * default: getNamePath(false) 
     * @deprecated use getNamePathVector 
     */
    public final String getNamePath(boolean onlyNames)
    {
        StringBuffer xPath = new StringBuffer(getName());
        if (!onlyNames) {
            xPath.append("[Context=").append(getContext().getName()).
            append(", LinkUsage=").append(getLinkUsage().getName()).
            append("]");
        }
        return xPath.toString();
    }
    
    /**
     * Gets the NamePath of this DevCap in form 
     * "DevCapsName/SubelemName1/SubelemName2/..."
     * If this DevCap is located in DevCapPool and not in a DevCaps - it describes the reusable resource 
     * and DevCap root will have the attribute "Name" = value of DevCaps/@Name 
     * but will have no info about DevCaps/@Context or DevCaps/@LinkUsage 
     *
     * @param bRecurse - if true, returns "DevCapsName/SubelemName1/SubelemName2/..."
     * @return String - NamePath of this DevCap
     * 
     * default: getNamePath(false)  
     */
    public final VString getNamePathVector()
    {
        String result = getDevCapsName();    
        VString vResult=new VString();
        vResult.add(result);
        return vResult;
    }       
    /////////////////////////////////////////////////// 
    private String getDevCapsName()
    {
        String nam=getName();
        EnumContext cont=getContext();
        if(cont.equals(EnumContext.Link))
        {
            nam+=JDFConstants.LINK;
        }
        else if(cont.equals(EnumContext.Element)&&!ElementName.JDF.equals(nam))
        {
            nam=ElementName.JDF+"/"+nam;
        }
        else if(cont.equals(EnumContext.JMF))
        {
            nam=ElementName.JMF+"/"+nam;
        }
        return nam; // default is nam for resources
    }
    
    public final VElement getDevCapVector()
    {
        VElement vDevCap = getChildElementVector(ElementName.DEVCAP, null, null, true, 0, false);
        final String dcr=getAttribute(AttributeName.DEVCAPREF,null,null);
        if(dcr!=null)
        {
            final JDFDevCapPool dcp=((JDFDeviceCap)getParentNode_KElement()).getDevCapPool();
            if(dcp!=null)
            {
                final KElement dcre= dcp.getChildWithAttribute(ElementName.DEVCAP,AttributeName.ID,null,dcr,0,true);
                vDevCap.appendUnique(dcre);
            }
        }
        return vDevCap;
    }
    
    /**
     * devCapReport - tests if the elements in vElem fit any (logical OR) DevCap element 
     * that DevCaps consists of. Composes a detailed report in XML form of the found errors.
     * If XMLDoc equals null - there are no errors.
     * 
     * DevCap that will be checked are direct children of this and referenced DevCap in DevCapPool.
     * 
     * @param VElement vElem - vector of the elements to test
     * @param EnumFitsValue testlists - FitsValue_Allowed or FitsValue_Present testlists 
     * that are specified for the State elements. (Will be used in fitsValue method of the State element)
     * @param EnumValidationLevel level - validation level
     * @return XMLDoc - XMLDoc output of the error messages.
     * If XMLDoc equals null - there are no errors, every element of vElem fits any DevCap element of this DevCaps
     * @throws JDFException if DevCaps/@DevCapRef refers to the DevCap elements in a non-existent DevCapPool
     * @throws JDFException if DevCaps/@DevCapRef refers to the non-existent DevCap 
     */
    public final XMLDoc devCapReport(VElement vElem, EnumFitsValue testlists, EnumValidationLevel level)
    {
        if(vElem==null)
            return null;
        
        XMLDoc testResult = new XMLDoc("Temp", null); // temporary root for XML output
        KElement tempRoot = testResult.getRoot();
        
        final VElement dcV = getDevCapVector();
        if ( dcV== null || dcV.size()==0) 
        {
            throw new JDFException("JDFDevCaps.devCapReport: Invalid DeviceCap: DevCaps/@DevCapRef refers to the non-existent DevCap: "+getDevCapRef());
        }
        
        final int size = vElem.size();
        
        for (int i=0;i<dcV.size();i++)
        {
            final JDFDevCap dc=(JDFDevCap)dcV.elementAt(i);
            for (int j = 0; j < size; j++)
        {
            KElement e = (JDFElement)vElem.elementAt(j);
            XMLDoc stateTestResult = null;
            
                KElement r = tempRoot.appendElement("InvalidResource");
                    stateTestResult = dc.stateReport(e,testlists,level);
                    if (stateTestResult==null) 
                    {
                        tempRoot.removeChild(r);// first DevCap that fits found -> erase all error messages
                    return null;
                    }
                    r.setAttribute("XPath", e.buildXPath());
                    r.setAttribute("Name", getName());
                    r.setAttribute("CapXPath", dc.getName());
                    moveChildElementVector_Static(r,stateTestResult.getRoot());
                }
            }
        
        if (!tempRoot.hasChildElements()) 
        {
            testResult = null;
        }
        
        if (testResult != null) 
        {
            correction_Static(testResult.getRoot());
        }
        
        return testResult;
    }
    
    /**
     * For every one InvalidResource element of the given XMLDoc checks CapXPath-s 
     * of all chidren (of arbitrary depth) and if CapXPath is not complete appends the right ancestors.
     * The point is that in original XMLDoc the CapXPaths are built using geNamePath() method,
     * where the root element is DevCaps element. But from JDF1.3 DevCap can be located in DevCapPool and 
     * can be called from any DevCaps. So to set the right source of calling we correct CapXPaths in XMLDoc doc. 
     *  
     * @param KElement root - root of the XMLDoc document where the CapXPath-s must be corrected
     */
    private final static void correction_Static(KElement root)
    {
        Vector v = root.getChildElementVector("InvalidResource", null, null, true, 0,false);
        for (int i = 0; i < v.size(); i++) 
        {
            KElement invRes = (KElement)v.elementAt(i);
            Vector vv = invRes.getChildElementVector(null, null, null, true, 0,false);
            for (int j = vv.size()-1; j >= 0; j--) 
            {
                capXPathCorrection_Static((KElement)vv.elementAt(j),invRes.getAttribute("CapXPath"));
            }
            removePoolElements_Static(invRes);
        }
        return;
    }
    
    private final static void removePoolElements_Static(KElement root)
    {
        Vector v = root.getChildElementVector(null, null, null, true, 0,false);
        for (int j = v.size()-1; j >= 0; j--) 
        {
            KElement el = (KElement)v.elementAt(j);
            String nam = el.getNodeName();
            if (nam.equals("InvalidAttributes") || nam.equals("InvalidSubelements") ||
                    nam.equals("UnknownAttributes") || nam.equals("UnknownSubelements") || 
                    nam.equals("MissingAttributes"))
            {
                moveChildElementVector_Static(root,el);
                if (!el.hasChildElements()&&!el.hasAttributes())
                    root.removeChild(el);
            }
            
        }
        Vector vv = root.getChildElementVector(null, null, null, true, 0,false);
        for (int i = vv.size()-1; i >= 0; i--) 
        {
            removePoolElements_Static((KElement)vv.elementAt(i));
        }
        return;
    }
    
    /**
     * For KElement 'elem' takes information from parent and children about original and corrected CapXPaths,
     * compare them and sets CapXPath as a comlete path to this element.
     * For every one InvalidResource element of the given XMLDoc checks CapXPath-s 
     * of all children (of arbitrary depth) and if CapXPath is not complete appends the right ancestors.
     *  
     * @param KElement elem - "pool" element like "InvalidElements" or "InvalidAttributes".
     * From this element we have access to its parent and children and can compare their CapXPath-s
     * @param String originalPath - parent CapXPath before correction.
     */
    private final static void capXPathCorrection_Static(KElement elem, String originalPath)
    {
        String parentPath = elem.getParentNode_KElement().getAttribute("CapXPath");
        
        Vector vEl = elem.getChildElementVector(null, null, null, true, 0,false);
        
        for (int i = 0; i < vEl.size(); i++) 
        {
            KElement child = (KElement)vEl.elementAt(i);
            String childPath = child.getAttribute("CapXPath");
            
            if (!parentPath.equals(JDFConstants.EMPTYSTRING) && !childPath.equals(JDFConstants.EMPTYSTRING))
            {
                String childPathPart = childPath;
                if (childPath.startsWith(originalPath))
                {
                    childPathPart = childPath.substring(originalPath.length()+1); // +1 removes "/"
                }
                child.setAttribute("CapXPath", parentPath + "/" + childPathPart);
                
                // recursion to set everywhere the right CapXPath
                Vector vSubEl = child.getChildElementVector(null, null, null, true, 0,false);
                for (int j = 0; j < vSubEl.size(); j++) 
                {
                    capXPathCorrection_Static((KElement)vSubEl.elementAt(j),childPath);
                }
            }
        }
        return;
    }
    
    
    /**
     * Moves ChildElementVector of the second element into the first
     * 
     * @param moveToElement - the first element - new parent for the children of the second element
     * @param moveFromElement -  the second element - element whose children will be removed
     */
    private final static void moveChildElementVector_Static(KElement moveToElement, KElement moveFromElement) 
    {
        if (moveToElement != null && moveFromElement != null)
        {
            Vector v = moveFromElement.getChildElementVector(null, null, null, true, 0,false);
            for (int i = 0; i < v.size(); i++) 
            {
                moveToElement.moveElement((KElement)v.elementAt(i), null);
            }
        }
        return;
    }
    
    
    /**
     * gets the matching elements in node that match this devcap
     * 
     * @param node the node to search in
     * @return VElement the element vector of matching elements, 
     * null if none were found
     */
    public VElement getMatchingElementsFromNode(JDFNode node)
    {
        VElement vElem = new VElement();
        final String nam = getName();
        final EnumContext context = getContext();
        JDFResourceLinkPool resLinkPool=node.getResourceLinkPool();
        
        if (context.equals(EnumContext.Element)) 
        { // vElem - for a common return type in all cases
            if(nam.equals(ElementName.JDF))
            {
                vElem.add(node);
            }
            else
            {
                vElem = node.getChildElementVector(nam, null, null, true, 0, false);
            }
        }
        else if (context.equals(EnumContext.Link) || context.equals(EnumContext.Resource))
        {
            if (resLinkPool != null)
            {
                VElement vElemLinks;
                final EnumUsage linkUsage = getLinkUsage();
                final String procUsage=getProcessUsage();
                boolean bLink=context.equals(EnumContext.Link);
                vElemLinks = resLinkPool.getInOutLinks(linkUsage, true, nam,null); 
                 
                for(int j=vElemLinks.size()-1;j>=0;j--)
                {
                    JDFResourceLink rl=(JDFResourceLink) vElemLinks.elementAt(j);
                    final String rlProcessUsage = rl.getProcessUsage();
                    if(!rlProcessUsage.equals(procUsage)){
                        vElemLinks.remove(j);
                    }
                }
                
                if (!bLink)
                {
                    vElem = JDFResourceLinkPool.resourceVector(vElemLinks, null);
                }
            }
                }
        else if (context.equals(EnumContext.JMF)) 
        {
            // TODO __Lena__ ...
        }
        else 
        {// Context_Unknown
            throw new JDFException("JDFDevCaps wrong attribute Context value");
        }
        
        if(vElem!=null && vElem.size()==0)
            vElem=null;
        
        return vElem;
    }
    
    
    /**
     * append elements that match this devCap to node, if they do not yet exist
     * 
     * @param node the node to append the elements to
     * 
     * @return KElement the last element that was appended
     */
    public KElement appendMatchingElementsToNode(JDFNode node)
    {
        final String nam = getName();
        final EnumContext context = getContext();
        final JDFDevCap devCap = getDevCap();
        if(devCap==null)
            return null;
        final int minOcc=devCap.getMinOccurs();
        
        KElement e=null;
        for(int i=0;i<minOcc;i++)
        {
            if (context.equals(EnumContext.Element)) 
            { // vElem - for a common return type in all cases
                if(nam.equals(ElementName.JDF))
                {
                    // nop - should actually never get here
                }
                else
                {
                    e=node.getCreateElement(nam,getDevNS(),i);
                }
            }
            else if (context.equals(EnumContext.Resource) || context.equals(EnumContext.Link)) 
            {
                final EnumUsage linkUsage = getLinkUsage();
                final String procUsage=getProcessUsage();
                JDFAttributeMap map=new JDFAttributeMap();
                EnumProcessUsage pu=null;
                
                if(procUsage!=null&&procUsage.length()>0)
                {
                    map.put(AttributeName.PROCESSUSAGE,procUsage);
                    pu=EnumProcessUsage.getEnum(procUsage);
                }
                if(linkUsage!=null)
                {
                    map.put(AttributeName.USAGE,linkUsage.getName());
                }
                VElement links=node.getLinks(nam,map,null);
                if(links==null || links.size()<=i)
                {
                    JDFResource r=node.addResource(nam, null, linkUsage, pu, null, getDevNS(), null);
                    e=node.getLink(r,null);
                }
            }
            else if (context.equals(EnumContext.JMF)) 
            {
                // TODO __Lena__ ...
            }
        }
        return e;
    }
    

    /**
     * sets default elements and adds them if therw are less than minorrurs
     */
    public boolean setDefaultsFromCaps(JDFNode node)
    {
        boolean modified = false;
        final JDFDevCap dc = getDevCap();
        if (dc != null)
        {
            VElement v = getMatchingElementsFromNode(node);
            final int size = v == null ? 0 : v.size();
            for (int i = 0; i < size; i++)
            {
                modified = dc.setDefaultsFromCaps((KElement) v.elementAt(i)) || modified;
            }
        }
        
        return modified;    
    }
        

    /**
     * return the highest maxOccurs of all devCap elements
     * @return int the highest maxOccurs of all devCap elements
     */
    public int getMaxOccurs()
    {
        int m=0;
        VElement vDC=getDevCapVector();
        int svDC= vDC==null ? 0 : vDC.size();
        for(int i=0;i<svDC;i++)
        {
            JDFDevCap dc=(JDFDevCap)vDC.elementAt(i);
            if(m<dc.getMaxOccurs())
                m=dc.getMaxOccurs();
        }
        return m;
 
    }

    /**
     * return the lowest minOccurs of all devCap elements
     * @return int the lowest minOccurs of all devCap elements
     */
   public int getMinOccurs()
    {
        int m=Integer.MAX_VALUE;
        VElement vDC=getDevCapVector();
        int svDC= vDC==null ? 0 : vDC.size();
        for(int i=0;i<svDC;i++)
        {
            JDFDevCap dc=(JDFDevCap)vDC.elementAt(i);
            if(m>dc.getMinOccurs())
                m=dc.getMinOccurs();
        }
        if(m<=0&&getRequired())
            m=1;
        return m;
 
    }

//////////////////////////////////////////////
//////////////////////////////////////////////
    
}



