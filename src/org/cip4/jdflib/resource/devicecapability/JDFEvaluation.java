/*
 *
 * The CIP4 Software License, Version 1.0
 *
 *
 * Copyright (c) 2001-2004 The International Cooperation for the Integration of 
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
 * Copyright (c) 2001 Heidelberger Druckmaschinen AG, All Rights Reserved.
 * 
 * @author Elena Skobchenko
 *
 * JDFEvaluation.java
 *
 */

package org.cip4.jdflib.resource.devicecapability;

import java.util.zip.DataFormatException;

import org.apache.xerces.dom.CoreDocumentImpl;
import org.cip4.jdflib.auto.JDFAutoBasicPreflightTest.EnumListType;
import org.cip4.jdflib.core.AtrInfoTable;
import org.cip4.jdflib.core.AttributeInfo;
import org.cip4.jdflib.core.AttributeName;
import org.cip4.jdflib.core.ElemInfoTable;
import org.cip4.jdflib.core.ElementInfo;
import org.cip4.jdflib.core.ElementName;
import org.cip4.jdflib.core.JDFConstants;
import org.cip4.jdflib.core.JDFElement;
import org.cip4.jdflib.core.JDFException;
import org.cip4.jdflib.core.KElement;
import org.cip4.jdflib.core.VString;
import org.cip4.jdflib.datatypes.JDFAttributeMap;
import org.cip4.jdflib.datatypes.JDFBaseDataTypes;
import org.cip4.jdflib.datatypes.JDFXYPair;
import org.cip4.jdflib.resource.JDFResource;
import org.cip4.jdflib.util.StringUtil;



public abstract class JDFEvaluation extends JDFTerm implements JDFBaseDataTypes
{
    private static AtrInfoTable[] atrInfoTable = new AtrInfoTable[1];
    static 
    {
        atrInfoTable[0]  = new AtrInfoTable(AttributeName.RREF, 0x33333333, AttributeInfo.EnumAttributeType.IDREF, null, null);
    }
    
    protected AttributeInfo getTheAttributeInfo() 
    {
        return super.getTheAttributeInfo().updateReplace(atrInfoTable);
    }
    
    
    private static ElemInfoTable[] elemInfoTable = new ElemInfoTable[1];
    static
    {
        elemInfoTable[0] = new ElemInfoTable(ElementName.BASICPREFLIGHTTEST, 0x33333333);
    }
    
    protected ElementInfo getTheElementInfo()
    {
        return new ElementInfo(super.getTheElementInfo(), elemInfoTable);
    }

    /**
     * constructor for JDFEvaluation
     * @param myOwnerDocument
     * @param qualifiedName
     */
    public JDFEvaluation(CoreDocumentImpl myOwnerDocument, String qualifiedName)
    {
        super(myOwnerDocument, qualifiedName);
    }

    /**
     * constructor for JDFEvaluation
     * @param myOwnerDocument
     * @param myNamespaceURI
     * @param qualifiedName
     */
    public JDFEvaluation(
            CoreDocumentImpl myOwnerDocument,
            String myNamespaceURI,
            String qualifiedName)
    {
        super(myOwnerDocument, myNamespaceURI, qualifiedName);
    }

    /**
     * constructor for JDFEvaluation
     * @param myOwnerDocument
     * @param myNamespaceURI
     * @param qualifiedName
     * @param myLocalName
     */
    public JDFEvaluation(
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
     * @return String
     */
    public String toString()
    {
        return "JDFEvaluation[ --> " + super.toString() + " ]";
    }
    
    /**
     * fitsMap - tests whether attribute map 'm' has a key 
     * specified by BasicPreflightTest/@Name. If this the case, it is checked whether its value
     * fits the testlist.
     *
     * @param m key-value pair attribute map to take the value from
     * @return boolean - true, if 'm' has a key specified by BasicPreflightTest/@Name 
     * and fitsValue(value) returns true
     */
    public final boolean fitsMap(JDFAttributeMap m)
    {
        final JDFBasicPreflightTest basicPreflightTest = getBasicPreflightTest();
        if(basicPreflightTest==null)
            return false;
        String n = basicPreflightTest.getName();
        
        if (m.containsKey(n)) 
        {
            String val = m.get(n);
            return fitsValue(val);
        }
        return false; // __Lena__ ? false or smth else
    }
    
    /**
     * fitsJDF - tests whether JDFNode 'jdf' can be accepted by the Device.
     * Tests if the value of resource attribute, 
     * decribed with this Evaluation, fits Evaluation/@ValueList
     *
     * @param jdf jdf node to test
     * @return boolean - true, if 'jdf' can be accepted by the Device
     */
    public boolean fitsJDF(KElement jdf, KElement reportRoot)
    {
        String xPath = getEvalXPath(jdf);
        if(reportRoot!=null)
            reportRoot=reportRoot.appendElement(getLocalName());
        boolean b=false;
        if(xPath!=null)
        {
             b=fitsPath(jdf,xPath,reportRoot);
        }
        if(reportRoot!=null){
            reportRoot.setAttribute("Value",b,null);
            reportRoot.setAttribute("CapXPath",getRefTargetNamePath());
            reportRoot.setAttribute("Name",getRefTargetName());
        }
        
        return b;
    }
    
    public boolean fitsContext(KElement jdf)
    {
        return getEvalXPath(jdf)!=null;
    }
    
    private boolean fitsPath(KElement e, String xPath, KElement reportRoot)
    {
        boolean b=false;
        KElement attr=null;
        String newPath=xPath;
        KElement pathElement = e.getXPathElement(xPath);
        if(xPath.indexOf("@")<0) //element
        {
            
            b=fitsValue(pathElement);
            if (b)
            {
                if(reportRoot!=null)
                {
                    if(pathElement!=null)
                        newPath=pathElement.buildXPath(null,1);
                    attr = reportRoot.appendElement("TestedElement");
                    attr.setAttribute("Name", StringUtil.token(xPath,-1,"/"));
                }
            }
        }
        else // attribute
        {
            
            final String attrVal=e.getXPathAttribute(xPath,null);
            b=fitsValue(attrVal);
            if(reportRoot!=null)
            {
                final String attName = xPath.substring(xPath.indexOf("@")+1);
                if(pathElement!=null)
                {
                    if(pathElement instanceof JDFResource)
                    {
                        JDFResource r=(JDFResource)pathElement;
                        JDFResource root=r.getResourceRoot();
                        while(r!=root)
                        {
                            if(!r.hasAttribute_KElement(attName,null,false))
                            {
                                r=(JDFResource) r.getParentNode_KElement();
                            }
                            else
                            {
                                break;
                            }
                        }
                        pathElement=r;
                    }
                    newPath=pathElement.buildXPath(null,1)+"/@"+attName;
                   
                }
                attr = reportRoot.appendElement("TestedAttribute");
                attr.setAttribute("Name", attName);
                attr.setAttribute("Value", attrVal);
            }
        } 
        
        if(attr!=null){
            attr.setAttribute("XPath", newPath);
        }
        return b;
    }
    
    
    /**
     * fitsValue - checks whether the <code>value</code> matches the testlists 
     * specified for this Evaluation
     *
     * @param value value to test
     * @return boolean - true, if the value matches testlists or if testlists are not specified
     */
    abstract public boolean fitsValue(String value);
    
    /**
     * fitsValue - checks whether <code>elem</code> matches the testlists 
     * specified for this Evaluation
     *
     * @param elem element to test
     * @return boolen - true, if the value matches testlists or if testlists are not specified
     */
    public boolean fitsValue(KElement elem)
    {
        //TODO implement in subclasses for spans
        return elem!=null;
    }       
    
    
    /**
     * gets the ListType from a corresponding State/BasicPreflightTest element
     *
     * @return JDFBasicPreflightTest::EnumListType - the value of ListType attribute
     */
    public JDFBasicPreflightTest.EnumListType getListType() 
    {
        final JDFAbstractState state = getState();
        if(state!=null)
        {
            return state.getListType();            
        }
        final JDFBasicPreflightTest basicPreflightTest = getBasicPreflightTest();
        return basicPreflightTest==null ? null : basicPreflightTest.getListType();
    }
    
    /**
     * gets the XPath to the attributes of a given JDF node
     *
     * @param jdf JDF node to test
     * @return String - the XPath to the attributes
     */
    protected String getEvalXPath(KElement jdf)
    {
        final JDFElement stateDC = getRefTarget();
        if(stateDC==null)
            return null;
        
        VString vPath = null;
        boolean bElement=false;
        String attName=null;
        
        if(stateDC instanceof JDFDevCap)
        {
            if (!(this instanceof JDFIsPresentEvaluation)) //  only ispresent may reference a devcap, all others must reference a state
                return null;
            bElement=true;
            final JDFDevCap dc=(JDFDevCap)stateDC;
            vPath=dc.getNamePathVector(true);             
        }
        else
        {
            // we have found our state -> take its xPath and look for corresponding elem in JDFNode, we test
            
            final JDFAbstractState state=(JDFAbstractState)stateDC;
            if(state.getListType().equals(EnumListType.Span)){
                vPath=state.getNamePathVector(true);
                bElement=true;
            } 
            else
            {
                final JDFDevCap dc=(JDFDevCap)state.getParentNode_KElement();
                vPath=dc.getNamePathVector(true);             
                attName=state.getName();
            }
        }
        if(vPath!=null)
        {
            for(int i=0;i<vPath.size();i++)
            {
                String xPath=vPath.stringAt(i);
                int slash=xPath.length();
                String finalS=null;
                do
                {
                    String xPath2=xPath.substring(0,slash);
                    if(jdf.matchesPath(xPath2,true))
                        finalS= "."+xPath.substring(slash);
                    slash=xPath2.lastIndexOf("/");
                }
                while(slash>=0 && finalS==null);
                
                if(finalS!=null && ! bElement)
                {
                    finalS+= "/@"+attName;            
                }
                if(finalS!=null)
                    return finalS;  
            }
        }
        return null;
    }
    
    /**
     * getRefTargetNamePath()
     * @return String
     */
    public String getRefTargetNamePath()
    {
        KElement e=getRefTarget();
        if(e instanceof JDFAbstractState){
            return ((JDFAbstractState)e).getNamePath();
        }
        else if(e instanceof JDFDevCap){
            return ((JDFDevCap)e).getNamePath(true);
        }
        return null;
    }
    
    /**
     * getRefTargetName()
     * @return String
     */
    public String getRefTargetName()
    {
        KElement e=getRefTarget();
        if(e instanceof JDFAbstractState){
            return ((JDFAbstractState)e).getName();
        }
        else if(e instanceof JDFDevCap){
            return ((JDFDevCap)e).getName();
        }
        return null;
    }
    
    /**
     * setRefTarget() set the target referencened in @rRef
     * @return JDFElement() the referenced element, either state or a devcap
     */
    public void setRefTarget(JDFElement e)
    {
        JDFDeviceCap deviceCap =(JDFDeviceCap)getDeepParent(ElementName.DEVICECAP,0);
        if(deviceCap==null)
            throw new JDFException("setRefTarget, called in dangling evaluation");
        
        if(!(e instanceof JDFAbstractState) && !(e instanceof JDFDevCap))
            throw new JDFException("setRefTarget, called for illegal target type");
             
       final String id=e.appendAnchor(null);
       setrRef(id);
    }
    
    
    /**
     * getRefTarget() get the target referencened in @rRef
     * @return JDFElement() the referenced element, either state or a devcap
     */
    public JDFElement getRefTarget()
    {
        JDFDeviceCap deviceCap =(JDFDeviceCap)getDeepParent(ElementName.DEVICECAP,0);
        if(deviceCap==null)
            return null;
        KElement e=deviceCap.getTarget(getrRef(),null);
        if(e instanceof JDFAbstractState){
            return (JDFAbstractState)e;
        }
        else if(e instanceof JDFDevCap){
            return (JDFDevCap)e;
        }
        return null;
    }
    
    
    /**
     * getState()
     * @return JDFAbstractState
     */
    public JDFAbstractState getState()
    {        
        KElement e=getRefTarget();
        if(e!=null && e instanceof JDFAbstractState)
            return (JDFAbstractState)e;
        return null;
    }
    
    /* ******************************************************
     // Attribute Getter / Setter
      **************************************************************** */
    
    /**
     * Sets String attribute <code>rRef</code><br>
     * Since rRef is independent of the data type of the State element, the setter is defined here
     *
     * @param String value: the value to set the attribute to
     */
    public void setrRef(String value)
    {
        setAttribute(AttributeName.RREF, value);
    }
    /**
     * Gets String attribute <code>rRef</code><br>
     * Since rRef is independent of the data type of the State element,the getter is defined here
     *
     * @return String: the attribute value
     */
    public String getrRef()
    {
        return getAttribute(AttributeName.RREF, null, "");
    }
    
    /* ******************************************************
     // Element Getter / Setter
      **************************************************************** */
    //@{
    /**
     * Get element <code>BasicPreflightTest</code>. Creates it if it doesn't exist
     * <p>
     * default: getCreateBasicPreflightTest(0
     *
     * @return JDFBasicPreflightTest: the matching element
     */
    public JDFBasicPreflightTest getCreateBasicPreflightTest()
    {
        return (JDFBasicPreflightTest)getCreateElement(ElementName.BASICPREFLIGHTTEST, null, 0);
    }    
    
   /**
    * Gets the iSkip-th element <code>BasicPreflightTest</code>. If doesn't exist, creates it
    * <p> default getCreateBasicPreflightTest(0)
    *
    * @param iSkip number of elements to skip
    * @return JDFBasicPreflightTest: the matching element
    * @deprecated use getCreateBasicPreflightTest()
    */
   public JDFBasicPreflightTest getCreateBasicPreflightTest(int iSkip)
   {
       return (JDFBasicPreflightTest)
       getCreateElement(ElementName.BASICPREFLIGHTTEST, JDFConstants.EMPTYSTRING, iSkip);
   }
    /**
     * Gets element <code>BasicPreflightTest</code>
     *
     * @return JDFBasicPreflightTest: the matching element or <code>null</code>
     */
    public JDFBasicPreflightTest getBasicPreflightTest()
    {
        return (JDFBasicPreflightTest)getElement(ElementName.BASICPREFLIGHTTEST, null,0);
    }
    /**
     * Gets the iSkip'th element <code>BasicPreflightTest</code>
     * <p>
     * default: getBasicPreflightTest(0)
     * 
     * @param iSkip number of elements to skip
     * @return JDFBasicPreflightTest: the matching element or null
     * @deprecated use getBasicPreflightTest()
     */
    public JDFBasicPreflightTest getBasicPreflightTest(int iSkip)
    {
        return (JDFBasicPreflightTest)getElement(ElementName.BASICPREFLIGHTTEST, JDFConstants.EMPTYSTRING, iSkip);
    }
    /**
     * Appends element <code>BasicPreflightTest</code> to the end of <code>this</code>
     *
     * @return JDFBasicPreflightTest: newly created BasicPreflightTest element
     * @deprecated use appendBasicPreflightTest(name)
     */
    public JDFBasicPreflightTest appendBasicPreflightTest()
    {
        return (JDFBasicPreflightTest)appendElementN(ElementName.BASICPREFLIGHTTEST, 1,null);
    }
    /**
     * Appends element <code>BasicPreflightTest</code> to the end of <code>this</code> and sets @Name to name
     * 
     * @param testName the new Name attribute of the BasicPreflightTest 
     * @return JDFBasicPreflightTest: newly created BasicPreflightTest element
     */
    public JDFBasicPreflightTest appendBasicPreflightTest(String testName)
    {
        JDFBasicPreflightTest pft= (JDFBasicPreflightTest)appendElementN(ElementName.BASICPREFLIGHTTEST, 1,null);
        if(pft==null) 
            return null;
        if(testName!=null)
            pft.setName(testName);
        return pft;
    }
    
    /**
     * tolerance is defined in all numeric evaluations - implement here!
     * @return
     */
    protected JDFXYPair getTolerance()
    {
        try
        {
            return new JDFXYPair(getAttribute(AttributeName.TOLERANCE,null,"0 0"));
        }
        catch (DataFormatException e)
        {
            throw new JDFException("JDFEvaluation.getTolerance: Attribute Tolerance is not applicable to create JDFXYPair");
        }
    }

    
}