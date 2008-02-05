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
 *
 * Copyright (c) 2001 Heidelberger Druckmaschinen AG, All Rights Reserved.
 *
 * JDFDevCaps.java
 *
 * @author Elena Skobchenko
 *
 */


package org.cip4.jdflib.resource.devicecapability;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Vector;

import org.apache.xerces.dom.CoreDocumentImpl;
import org.cip4.jdflib.auto.JDFAutoDevCaps;
import org.cip4.jdflib.auto.JDFAutoMessageService.EnumJMFRole;
import org.cip4.jdflib.core.AttributeName;
import org.cip4.jdflib.core.ElementName;
import org.cip4.jdflib.core.JDFConstants;
import org.cip4.jdflib.core.JDFException;
import org.cip4.jdflib.core.JDFResourceLink;
import org.cip4.jdflib.core.KElement;
import org.cip4.jdflib.core.VElement;
import org.cip4.jdflib.core.VString;
import org.cip4.jdflib.core.JDFResourceLink.EnumUsage;
import org.cip4.jdflib.datatypes.JDFAttributeMap;
import org.cip4.jdflib.datatypes.JDFIntegerList;
import org.cip4.jdflib.datatypes.JDFIntegerRangeList;
import org.cip4.jdflib.datatypes.JDFBaseDataTypes.EnumFitsValue;
import org.cip4.jdflib.ifaces.ICapabilityElement;
import org.cip4.jdflib.jmf.JDFMessage;
import org.cip4.jdflib.jmf.JDFMessageService;
import org.cip4.jdflib.jmf.JDFMessage.EnumFamily;
import org.cip4.jdflib.node.JDFNode;
import org.cip4.jdflib.node.JDFNode.EnumProcessUsage;
import org.cip4.jdflib.pool.JDFResourceLinkPool;
import org.cip4.jdflib.resource.JDFResource;
import org.cip4.jdflib.resource.devicecapability.JDFDeviceCap.EnumAvailability;
import org.cip4.jdflib.resource.devicecapability.JDFTerm.EnumTerm;
import org.cip4.jdflib.util.StringUtil;
import org.cip4.jdflib.util.VectorMap;


//----------------------------------
public class JDFDevCaps extends JDFAutoDevCaps implements ICapabilityElement
{
    private static final long serialVersionUID = 1L;

    /**
     * Constructor for JDFDevCaps
     * @param myOwnerDocument
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
     * @param myOwnerDocument
     * @param myNamespaceURI
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
     * @param myOwnerDocument
     * @param myNamespaceURI
     * @param qualifiedName
     * @param myLocalName
     */
    public JDFDevCaps(
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
        return "JDFDevCaps[  --> " + super.toString() + " ]";
    }


    /**
     * set attribute <code>DevCapRef</code>
     * @param value the value to set the attribute to
     */
    public void setDevCapRef(String value)
    {
        setAttribute(AttributeName.DEVCAPREF, value, null);
    }

    /**
     * set rRef to the value of devCap/@ID
     * @param dc the DevCap to set
     */
    public void setDevCapRef(JDFDevCap dc)
    {
        dc.appendAnchor(null); // just in case it is missing
        final String id2 = dc.getID();
        setDevCapRef(id2);       
    }
    /**
     * set rRef to the value of devCap/@ID
     * @param dc the DevCap to set
     */
    @Override
	public JDFDevCap appendDevCap()
    {
        JDFDevCap dc=super.appendDevCap();
        if(hasAttribute(AttributeName.NAME))
            dc.setName(getName());
        return dc;
    }
    /**
     * set rRef to the value of devCap/@ID
     * @param dc the DevCap to set
     */
    public JDFDevCap appendDevCapInPool()
    {
        JDFDevCapPool dcp = getCreateDevCapPool();
        JDFDevCap dc=dcp.appendDevCap();
        final String id2 = dc.getID();
        setDevCapRef(id2);      
        if(hasAttribute(AttributeName.NAME))
            dc.setName(getName());
        return dc;
    }

    /**
     * get the DevCapPool that contains devcap elements referenced by this
     * @return JDFDevCapPool the pool
     */
    public JDFDevCapPool getDevCapPool()
    {
        return (JDFDevCapPool) getParentPool(ElementName.DEVCAPPOOL);
    }
    /**
     * get the DEvCapPool that contains devcap elements referenced by this
     * @return JDFDevCapPool the pool
     */
    private KElement getParentPool(String poolName)
    {
        final KElement parent=getParentNode_KElement();
        if(!(parent instanceof JDFDeviceCap)&&!(parent instanceof JDFMessageService))
            throw new JDFException("JDFDevCap.getParentPool - invalid parent context");
        return parent.getElement(poolName);
    }
    /**
     * get the DEvCapPool that contains devcap elements referenced by this
     * @return JDFDevCapPool the pool
     */
    private KElement getCreateParentPool(String poolName)
    {
        final KElement parent=getParentNode_KElement();
        if(!(parent instanceof JDFDeviceCap)&&!(parent instanceof JDFMessageService))
            throw new JDFException("JDFDevCap.getParentPool - invalid parent context");
        return parent.getCreateElement(poolName);
    }
    /**
     * get the DEvCapPool that contains devcap elements referenced by this
     * @return JDFDevCapPool the pool
     */
    public JDFModulePool getModulePool()
    {
        return (JDFModulePool) getParentPool(ElementName.MODULEPOOL);
    }
    /**
     * get the DEvCapPool that contains devcap elements referenced by this
     * @return JDFDevCapPool the pool
     */
    public JDFModulePool getCreateModulePool()
    {
        return (JDFModulePool) getCreateParentPool(ElementName.MODULEPOOL);
    }
    /**
     * get the DevCapPool that contains devcap elements referenced by this
     * create one if it does not exist
     * @return JDFDevCapPool the pool
     */
    public JDFDevCapPool getCreateDevCapPool()
    {
        final KElement parent=getParentNode_KElement();
        if(!(parent instanceof JDFDeviceCap)&&!(parent instanceof JDFMessageService))
            throw new JDFException("JDFDevCap.getCreateDevCapPoll - invalid parent context");
        return (JDFDevCapPool) parent.getCreateElement(ElementName.DEVCAPPOOL);
    }

    /**
     * get the one and only devCap - note that the spec allows <code>*</code>, 
     * but recommends only one <code>+</code> additional actions.<br>
     * Also search devCapPool for a matching element to DevCapRef.
     * @return the DevCap
     */
    public JDFDevCap getDevCap()
    {
        final String dcr=getAttribute(AttributeName.DEVCAPREF,null,null);
        if(dcr!=null){
            final JDFDevCapPool dcp=getDevCapPool();
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
     * <p>
     * default: getNamePath(false)
     * 
     * @param onlyNames if true, returns only DevCapsName. Default=false
     * @return String - NamePath of this DevCaps
     * 
     * @deprecated use getNamePathVector 
     */
    @Deprecated
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
     * "<code>DevCapsName/SubelemName1/SubelemName2/...</code>"<br>
     * If this DevCap is located in DevCapPool and not in a DevCaps, it describes the reusable resource. 
     * In this case DevCap root will have the attribute "Name" = value of DevCaps/@Name, 
     * but will have no info about <code>DevCaps/@Context</code> or <code>DevCaps/@LinkUsage</code>.
     * <p>
     * default: getNamePath(false)
     *
     * @param bRecurse if true, returns "<code>DevCapsName/SubelemName1/SubelemName2/...</code>"
     * @return String - NamePath of this DevCap
     */
    public final VString getNamePathVector()
    {
        String result = getDevCapsName(); 
        final EnumContext cont=getContext();
        VString vResult=new VString();
        if(cont.equals(EnumContext.JMF) && (getParentNode() instanceof JDFMessageService) && result.length()>4)
        {
            JDFMessageService serv=(JDFMessageService)getParentNode();
            Vector<EnumFamily> vf=serv.getFamilies();
            final int size = vf==null ? 0 : vf.size();
            for(int i=0;i<size;i++)
            {
                vResult.add("JMF/"+vf.elementAt(i).getName()+"/"+result.substring(4));
            }
            if(EnumJMFRole.Sender.equals(serv.getJMFRole()))
                    vResult.add("JMF/Response/"+result.substring(4));
        }
        else
        {
            if(cont.equals(EnumContext.Link))
            {
                result=ElementName.JDF+"/"+ElementName.RESOURCELINKPOOL+"/"+result;
            }
            else if(cont.equals(EnumContext.Resource))
            {
                result=ElementName.JDF+"/"+ElementName.RESOURCEPOOL+"/"+result;
            }

            vResult.add(result);
        }
        return vResult;
    }       
    /////////////////////////////////////////////////// 
    private String getDevCapsName()
    {
        String nam=getName();
        final EnumContext cont=getContext();
        if(cont.equals(EnumContext.Link))
        {
            nam+=JDFConstants.LINK;
        }
        else if(cont.equals(EnumContext.Element)&&!ElementName.JDF.equals(nam))
        {
            nam=ElementName.JDF+"/"+nam;
        }
        else if(cont.equals(EnumContext.JMF) &&!ElementName.JMF.equals(nam))
        {
            nam=ElementName.JMF+"/"+nam;
        }
        return nam; // default is nam for resources
    }

    /**
     * getDevCapVector()
     * @return VElement
     */
    public final VElement getDevCapVector()
    {
        VElement vDevCap = getChildElementVector(ElementName.DEVCAP, null, null, true, 0, false);
        final String dcr=getAttribute(AttributeName.DEVCAPREF,null,null);
        if(dcr!=null)
        {
            final JDFDevCapPool dcp=getDevCapPool();
            if(dcp!=null)
            {
                VString v=StringUtil.tokenize(dcr, " ", false);
                for(int i=0;i<v.size();i++)
                {
                    String s=v.stringAt(i);
                    final KElement dcre= dcp.getChildWithAttribute(ElementName.DEVCAP,AttributeName.ID,null,s,0,true);
                    vDevCap.appendUnique(dcre);
                }
            }
        }
        return vDevCap;
    }

    /**
     * devCapReport - tests if the elements in vElem fit any (logical OR) DevCap element 
     * that DevCaps consists of. Composes a detailed report in XML form of the errors found.
     * If XMLDoc is null there are no errors.<br>
     * 
     * DevCaps will be checked if they are direct children of <code>this</code> and 
     * referenced in DevCapPool.
     * 
     * @param vElem     vector of the elements to test
     * @param testlists testlists that are specified for the State elements 
     *                  (FitsValue_Allowed or FitsValue_Present)<br> 
     *                  Will be used in fitsValue method of the State element.
     * @param level     validation level
     * @return XMLDoc - XMLDoc output of the error messages.<br>
     * If XMLDoc is null there are no errors, every element of vElem fits any DevCap element of <code>this</code>.
     * @throws JDFException if DevCaps/@DevCapRef refers to the DevCap elements in a non-existent DevCapPool
     * @throws JDFException if DevCaps/@DevCapRef refers to the non-existent DevCap 
     */
    public final KElement devCapReport(KElement elem, EnumFitsValue testlists, EnumValidationLevel level, boolean ignoreExtensions, KElement parentReport)
    {
        if(elem==null)
            return null;

        final VElement dcV = getDevCapVector();
        if ( dcV== null || dcV.size()==0) 
        {
            throw new JDFException("JDFDevCaps.devCapReport: Invalid DeviceCap: DevCaps/@DevCapRef refers to the non-existent DevCap: "+getDevCapRef());
        }

        KElement r = parentReport.appendElement("Invalid"+getContext().getName());
        for (int i=0;i<dcV.size();i++)
        {
            final JDFDevCap dc=(JDFDevCap)dcV.elementAt(i);
            KElement stateTestResult = dc.stateReport(elem,testlists,level, ignoreExtensions,true,r);
            if (stateTestResult==null) 
            {
                r.deleteNode();
                return null;// first DevCap that fits found -> erase all error messages
            }
            r.setAttribute("XPath", elem.buildXPath(null,1));
            r.setAttribute("Name", getContextName());
            r.setAttribute("CapXPath", dc.getName());
        }

        correction_Static(r);
        return r;
    }

    /**
     * same as getName, except that "Link" is appended in case of @Context="Link"
     * @return the element name mangled by context
     */
    public String getContextName()
    {
        String s=getName();
        if(s==null)
            return null;
        EnumContext context=getContext();
        if(EnumContext.Link.equals(context))
            s+=JDFConstants.LINK;
        return s;
    }

    /**
     * Checks XPaths for every InvalidResource element of the given XMLDoc and 
     * all chidren (of arbitrary depth). Appends right ancestors if CapXPath is not complete.<br>
     * The point is that that the CapXPath's are created by using getNamePath() method,
     * where the root element is a DevCaps element. But starting with JDF 1.3, DevCap can be located in DevCapPool and 
     * can be called from any DevCaps. So the CapXPaths in XMLDoc doc are being fixed by setting 
     * the right source of calling. 
     *  
     * @param root root of the XMLDoc document where the CapXPaths must be corrected
     */
    private final static void correction_Static(KElement root)
    {
        VElement v = root.getChildElementVector("InvalidResource", null, null, true, 0,false);
        for (int i = 0; i < v.size(); i++) 
        {
            KElement invRes = v.elementAt(i);
            VElement vv = invRes.getChildElementVector(null, null, null, true, 0,false);
            for (int j = vv.size()-1; j >= 0; j--) 
            {
                capXPathCorrection_Static(vv.elementAt(j),invRes.getAttribute("CapXPath"));
            }
            removePoolElements_Static(invRes);
        }
        return;
    }

    private final static void removePoolElements_Static(KElement root)
    {
    	VElement v = root.getChildElementVector(null, null, null, true, 0,false);
        for (int j = v.size()-1; j >= 0; j--) 
        {
            KElement el = v.elementAt(j);
            String nam = el.getNodeName();
            if (nam.equals("InvalidAttributes") || nam.equals("InvalidElements") ||
                    nam.equals("UnknownAttributes") || nam.equals("UnknownElements") || 
                    nam.equals("MissingAttributes"))
            {
                moveChildElementVector_Static(root,el);
                if (!el.hasChildElements()&&!el.hasAttributes())
                    root.removeChild(el);
            }

        }
        VElement vv = root.getChildElementVector(null, null, null, true, 0,false);
        for (int i = vv.size()-1; i >= 0; i--) 
        {
            removePoolElements_Static(vv.elementAt(i));
        }
        return;
    }

    /**
     * For KElement 'elem' takes information from parent and children about original and corrected CapXPaths,
     * compare them and set CapXPath as a complete path to this element.<br>
     * Checks CapXPath's for every InvalidResource element of the given XMLDoc and 
     * all children (of arbitrary depth). Appends right ancestors if CapXPath is not complete.<br>
     *  
     * @param elem "pool" element like "InvalidElements" or "InvalidAttributes".<br>
     * From this element we have access to its parent and children and can compare their CapXPath's
     * @param originalPath parent CapXPath before correction.
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
                VElement vSubEl = child.getChildElementVector(null, null, null, true, 0,false);
                for (int j = 0; j < vSubEl.size(); j++) 
                {
                    capXPathCorrection_Static(vSubEl.elementAt(j),childPath);
                }
            }
        }
        return;
    }


    /**
     * Moves the ChildElementVector of the second element into the first
     * 
     * @param moveToElement   the first element - new parent for the children of the second element
     * @param moveFromElement the second element - element whose children will be removed
     */
    private final static void moveChildElementVector_Static(KElement moveToElement, KElement moveFromElement) 
    {
        if (moveToElement != null && moveFromElement != null)
        {
        	VElement v = moveFromElement.getChildElementVector(null, null, null, true, 0,false);
            for (int i = 0; i < v.size(); i++) 
            {
                moveToElement.moveElement(v.elementAt(i), null);
            }
        }
        return;
    }


    /**
     * gets the matching elements in node that match this devcap
     * 
     * @param node the node to search in
     * @return VElement - the element vector of matching elements, 
     *                    <code>null</code> if none were found
     */
    private VElement getMatchingElementsFromNode(JDFNode node)
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
                final EnumUsage linkUsage = getLinkUsage();
                final String procUsage=getProcessUsage();
                boolean bLink=context.equals(EnumContext.Link);
                VElement vElemLinks = resLinkPool.getInOutLinks(linkUsage, true, nam,null); 

                final int linkSize = (vElemLinks==null) ? -1 : vElemLinks.size()-1;
                for (int j=linkSize; j>=0; j--)
                {
                    JDFResourceLink rl = (JDFResourceLink) vElemLinks.elementAt(j);
                    final String rlProcessUsage = rl.getProcessUsage();
                    if(!rlProcessUsage.equals(procUsage)){
                        vElemLinks.remove(j);
                    }
                }

                if (!bLink)
                {
                    vElem = JDFResourceLinkPool.resourceVector(vElemLinks, null);
                }
                else
                {
                    vElem = vElemLinks;
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
     * gets the matching elements in node that match this devcaps
     * 
     * @param node the node to search in
     * @return VElement - the element vector of matching elements, 
     *                    <code>null</code> if none were found
     */
    public VElement getMatchingElementsFromJMF(JDFMessage jmf)
    {
        final String nam = getName();
        final EnumContext context = getContext();

        if (!context.equals(EnumContext.JMF)) 
        { // vElem - for a common return type in all cases
            return null;
        }
        VElement vElem = jmf.getChildElementVector(nam, null, null, true, 0, false);

        if(vElem!=null && vElem.size()==0)
            vElem=null;

        return vElem;
    }


    /**
     * append elements to the node that match this DevCap, if they do not exist yet
     * 
     * @param node the node to append the elements to
     * @param bAll if false, only add if minOccurs>=1 and required=true or a default exists
     * 
     * @return KElement - the last element that was appended
     */
    public KElement appendMatchingElementsToNode(JDFNode node, boolean bAll, VectorMap indexResMap, boolean bLink)
    {
        final String nam = getName();
        final EnumContext context = getContext();
        if(!bLink && EnumContext.Link.equals(context))
            return null;
        if(bLink && !EnumContext.Link.equals(context))
            return null;
        
        final JDFDevCap devCap = getDevCap();
        if(devCap==null)
            return null;
        int minOcc=devCap.getMinOccurs();
        if(minOcc==0 &&  bAll)
            minOcc=1;

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
                VElement links=node.getResourceLinks(nam, map, null);
                // now look for the correct combinedprocessindex - remove all non-matching
                JDFIntegerRangeList tocNum=getTypeOccurrenceNum();
                JDFIntegerList tocNum2=tocNum==null ? null : tocNum.getIntegerList();
                if(links!=null && tocNum!=null)
                {
                    for(int ll=links.size()-1;ll>=0;ll--)
                    {
                        JDFResourceLink rl=(JDFResourceLink) links.elementAt(ll);
                        JDFIntegerList il=rl.getCombinedProcessIndex();
                        if(il==null || !il.contains(tocNum2))
                            links.remove(ll);
                    }
                }
                if(links==null || links.size()<=i)
                {
                    JDFResource r=null;
                    // get a link hook for the matching combinedprocessindex
                    if(bLink)
                    {
                        int kk=(tocNum2==null|| tocNum2.size()==0) ?-1: tocNum2.getInt(0);
                        if(EnumUsage.Input.equals(linkUsage))
                            kk--;
                        Vector<JDFResource> v=(Vector) indexResMap.get(new Integer(kk));
                        int sv= v==null ? 0 : v.size();
                        for(int kkk=0;kkk<sv;kkk++)
                        {
                            JDFResource rr=v.elementAt(kkk);
                            if(rr.getLocalName().equals(nam))
                            {
                                r=rr;
                                break;
                            }
                        }
                    }    
                    // we found no matching existing res - make a new one
                    if(r==null)
                    {
                        r=node.addResource(nam, null, linkUsage, pu, null, getDevNS(), null);
                        final String id=devCap.getAttribute(AttributeName.ID,null,null);
                        if(id!=null)
                        {
                            JDFResourceLink rl=node.getLink(r,linkUsage);

                            r.setID(id);
                            if(rl!=null)
                            {
                                rl.setrRef(id);
                            }
                        }

                        if(tocNum2==null|| tocNum2.size()==0)
                            indexResMap.putOne(new Integer(-1), r);
                        else
                            indexResMap.putOne(tocNum2.elementAt(0), r); // only support 1 now
                    }
                    else // preexisting resource - just link it
                    {
                        e=node.linkResource(r, linkUsage, pu);
                    }
                    e=node.getLink(r,linkUsage);
                    if(e!=null)
                    {
                        JDFResourceLink rl=(JDFResourceLink)e;
                        rl.setCombinedProcessIndex(tocNum2);
                    }
                    
                    // update partititons
                    final JDFEnumerationState pidKeys = devCap.getEnumerationState(AttributeName.PARTIDKEYS);
                    if(pidKeys!=null)
                    {
                        VString keys=pidKeys.getAllowedValueList();
                        if(keys!=null && keys.size()>0)
                        {
                            JDFAttributeMap keyMap=new JDFAttributeMap();
                            for(int k=0;k<keys.size();k++)
                            {
                                String sk="PartKey"+k;
                                final String key = keys.stringAt(k);
                                if(key.equals("RunIndex"))
                                    sk="0~-1";
                                keyMap.put(key, sk);
                            }
                            r.getCreatePartition(keyMap, keys);
                        }
                    }
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
     * sets default elements and adds them, if there are less than minOccurs
     * @param node the node to set
     * @param bAll if false, only add if minOccurs>=1 and required=true or a default exists,
     * if true, always create one
     * 
     * @return boolean true if a default element was created, else false
     */
    public boolean setDefaultsFromCaps(JDFNode node, boolean bAll)
    {
        boolean modified = false;
        final JDFDevCap dc = getDevCap();
        if (dc != null)
        {
            VElement v = getMatchingElementsFromNode(node);
            final int size = v == null ? 0 : v.size();
            for (int i = 0; i < size; i++)
            {
                modified = dc.setDefaultsFromCaps(v.item(i),bAll) || modified;
            }
        }

        return modified;    
    }


    /**
     * return the highest maxOccurs of all devCap elements
     * @return int - the highest maxOccurs of all devCap elements
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
     * @return int - the lowest minOccurs of all devCap elements
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

    /**
     * @param testRoot
     * @param testlists
     * @param level
     * @param mrp
     * @param irp
     * @param resLinkPool
     * @param goodElems
     * @param badElems
     * @param devCaps
     * @return
     */
    public void analyzeDevCaps(final KElement testRoot, EnumFitsValue testlists, EnumValidationLevel level, KElement mrp, KElement irp, HashSet goodElems, HashMap badElems, boolean ignoreExtensions)
    {
        EnumAvailability av=getModuleAvailability();
        KElement xpathRoot=testRoot;
        VElement vElemResources = null;
        if(testRoot instanceof JDFNode)
        {
            final JDFNode jdfNode = (JDFNode)testRoot;
            vElemResources=getMatchingElementsFromNode(jdfNode);
            xpathRoot=jdfNode.getResourceLinkPool();
            if(xpathRoot==null)
                xpathRoot=testRoot;
        }
        else
        {
            vElemResources = getMatchingElementsFromJMF((JDFMessage)testRoot);
        }
        int svElemResources = vElemResources==null ? 0 : vElemResources.size();

        final EnumContext context = getContext();
        KElement r=null;
        if (EnumValidationLevel.isRequired(level) && svElemResources<getMinOccurs() && EnumAvailability.Installed.equals(av))
        {
            if (EnumContext.Element.equals(context)||EnumContext.JMF.equals(context)) 
            {
                r = mrp.appendElement("MissingElement");
                r.setAttribute("XPath", xpathRoot.buildXPath(null,1)+ "/" + getName());
            }
            else 
            {
                final EnumUsage linkUsage = getLinkUsage();
                final String procUsage=getProcessUsage();
                r = mrp.appendElement("MissingResourceLink");
                if (linkUsage!=null)
                {
                    r.setAttribute("Usage", linkUsage.getName());
                }
                if (procUsage!=null && procUsage.length()>0)
                {
                    r.setAttribute("ProcessUsage", procUsage);
                }
                r.setAttribute("XPath", xpathRoot.buildXPath(null,1)+ "/" + getName());
            }                    
            r.setAttribute("Name", getName());
            r.setAttribute("CapXPath", getName());                    
            r.setAttribute("Occurrences", svElemResources,null);
            r.setAttribute("MinOccurs", getMinOccurs(),null);
        }
        else if (svElemResources>getMaxOccurs() || !EnumAvailability.Installed.equals(av))
        {
            if (context.equals(EnumContext.Element) || context.equals(EnumContext.JMF)) 
            {
                r = irp.appendElement("ManyElement");
                r.setAttribute("XPath", testRoot.buildXPath(null,1)+ "/" + getName());
            }
            else 
            {
                final EnumUsage linkUsage = getLinkUsage();
                final String procUsage=getProcessUsage();
                r = irp.appendElement("ManyResourceLink");
                if (linkUsage!=null)
                {
                    r.setAttribute("Usage", linkUsage.getName());
                }
                if (procUsage!=null && procUsage.length()>0)
                {
                    r.setAttribute("ProcessUsage", procUsage);
                }
                r.setAttribute("XPath", xpathRoot.buildXPath(null,1)+ "/" + getName());
            }                    
            r.setAttribute("Name", getName());
            r.setAttribute("CapXPath", getName()); 
            r.setAttribute("Occurrences", svElemResources,null);
            r.setAttribute("MaxOccurs", getMaxOccurs(),null);
            r.setAttribute("Availability", av==null ? "None" : av.getName());
        }

        for(int j=0;j<svElemResources;j++)
        {
            final KElement elem = vElemResources.item(j);
            if(!goodElems.contains(elem))
            {
                KElement report=devCapReport(elem,testlists,level,ignoreExtensions,irp); // InvalidResources
                if(report==null)
                {
                    goodElems.add(elem);
                    KElement badReport=(KElement)badElems.get(elem);
                    if(badReport!=null)
                        badReport.deleteNode();
                }
                else
                {
                    badElems.put(elem,report);
                }
            }
        }
    }

    /** 
     * get the availability of this devcaps based on the list of installed modules in ModuleRefs and ModulePool
     * @return
     */
    public EnumAvailability getModuleAvailability()
    {
        return JDFModulePool.getModuleAvailability(this); 
    }
    /* (non-Javadoc)
     * @see org.cip4.jdflib.core.JDFElement#getInvalidAttributes(org.cip4.jdflib.core.KElement.EnumValidationLevel, boolean, int)
     */
    @Override
	public VString getInvalidAttributes(EnumValidationLevel level, boolean bIgnorePrivate, int nMax)
    {
        VString vs= super.getInvalidAttributes(level, bIgnorePrivate, nMax);
        if(nMax>0 && vs.size()>nMax)
            return vs;
        if(!EnumValidationLevel.RecursiveComplete.equals(level) && !EnumValidationLevel.RecursiveIncomplete.equals(level))
            return vs;
        if(vs.contains(AttributeName.DEVCAPREF))
            return vs;

        if(hasAttribute(AttributeName.DEVCAPREF))
        {
            JDFDevCapPool devCapPool = getDevCapPool();
            if (devCapPool == null)
            {
                vs.add(AttributeName.DEVCAPREF);
                return vs;
            }
            VString idRefs=getDevCapRef();
            for (int i=0; i < idRefs.size(); i++) 
            {
                JDFDevCap devCap = devCapPool.getDevCap(idRefs.stringAt(i));
                if (devCap==null)
                {
                    vs.add(AttributeName.DEVCAPREF);
                    return vs;
                }
            }
        }           
        return vs;
    }

    /* (non-Javadoc)
     * @see org.cip4.jdflib.ifaces.IModuleCapability#appendModuleRef(java.lang.String)
     */
    public JDFModuleCap appendModuleRef(String id)
    {
        return JDFModulePool.appendModuleRef(this, id);     
    }

    /* (non-Javadoc)
     * @see org.cip4.jdflib.ifaces.ICapabilityElement#getEvaluationType()
     */
    public EnumTerm getEvaluationType()
    {
        return EnumTerm.IsPresentEvaluation;
    }

  }



