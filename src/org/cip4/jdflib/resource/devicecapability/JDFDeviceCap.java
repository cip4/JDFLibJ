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
 * JDFDeviceCap.java
 *
 * @author Elena Skobchenko
 *
 */
package org.cip4.jdflib.resource.devicecapability;

import java.util.Vector;

import org.apache.xerces.dom.CoreDocumentImpl;
import org.cip4.jdflib.auto.JDFAutoDeviceCap;
import org.cip4.jdflib.auto.JDFAutoDevCaps.EnumContext;
import org.cip4.jdflib.auto.JDFAutoDeviceCap.EnumCombinedMethod;
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
import org.cip4.jdflib.util.StringUtil;

public class JDFDeviceCap extends JDFAutoDeviceCap 
{
    private static final long serialVersionUID = 1L;
    
    /**
     * Constructor for JDFDeviceCap
     * @param ownerDocument
     * @param qualifiedName
     */
    public JDFDeviceCap(
            CoreDocumentImpl myOwnerDocument,
            String qualifiedName)
    {
        super(myOwnerDocument, qualifiedName);
    }
    
    
    /**
     * Constructor for JDFDeviceCap
     * @param ownerDocument
     * @param namespaceURI
     * @param qualifiedName
     */
    public JDFDeviceCap(
            CoreDocumentImpl myOwnerDocument,
            String myNamespaceURI,
            String qualifiedName)
    {
        super(myOwnerDocument, myNamespaceURI, qualifiedName);
    }
    
    /**
     * Constructor for JDFDeviceCap
     * @param ownerDocument
     * @param namespaceURI
     * @param qualifiedName
     * @param localName
     */
    public JDFDeviceCap(
            CoreDocumentImpl myOwnerDocument,
            String myNamespaceURI,
            String qualifiedName,
            String myLocalName)
    {
        super(myOwnerDocument, myNamespaceURI, qualifiedName, myLocalName);
    }
    
    //**************************************** Methods *********************************************
    /**
     * toString - StringRepresentation of JDFNode
     *
     * @return String
     */
    public String toString()
    {
        return "JDFDeviceCap[  --> " + super.toString() + " ]";
    }
    
    
    /**
     * Gets of this string attribute TypeExpression if it exists,
     * otherwise returns the literal string defined in Types
     *
     * @return String - TypeExpression attribute value
     */
    public String getTypeExpression()
    {
        if (hasAttribute(AttributeName.TYPEEXPRESSION)) 
        {
            return super.getTypeExpression();
        }
        return getAttribute(AttributeName.TYPES);
    }
    
    /**
     * (9.2) get CombinedMethod attribute CombinedMethod
     * @return Vector of the enumerations
     */
   public Vector getCombinedMethod()
   {
       Vector v=getEnumerationsAttribute(AttributeName.COMBINEDMETHOD, null, EnumCombinedMethod.None, false);
       if(v==null)
       {
           v=new Vector();
           v.add(EnumCombinedMethod.None);
       }
       return v;
   }

    
    /* ******************************************************
     // FitsValue Methods
      **************************************************************** */
    
    
    /**
     * Gets of jdfRoot a vector of all executable nodes  
     * (jdf root or children nodes - that this Device may execute)
     *
     * @param jdfRoot - the node we test
     * @param testlists - FitsValue_Allowed or FitsValue_Present testlists
     * that are specified for the State elements (Will be used in fitsValue method of the State class)
     * @param level - validation level
     * @return VElement - vector of executable JDFNodes
     */
    public final VElement getExecutableJDF(final JDFNode jdfRoot, EnumFitsValue testlists, EnumValidationLevel level)
    {
        VElement execNodes = new VElement();
        String typeNode = jdfRoot.getType();
        EnumExecutionPolicy execPolicy = getExecutionPolicy();
        boolean bExit = false;
        if (execPolicy.equals(EnumExecutionPolicy.RootNode))	
        {
            if (!typeNode.equals("Product")) 
            {
                XMLDoc doc = null;
                boolean bException = false;
                try 
                {
                    doc = report(jdfRoot,testlists, level); // if report throws exception - jdfRoot is non-executable Node 
                }
                catch (JDFException jdfe)
                {
                    bException = true;
                }
                if (doc == null && !bException)
                {
                    if (typeNode.equals("ProcessGroup")) 
                    {
                        Vector vNodes =jdfRoot.getvJDFNode(null, null,  false);
                        for (int i=0; i<vNodes.size()-1 && !bExit; i++) 
                        { // test if all children of ProcessGroup fit DevCaps. Type was already tested for a whole Node
                            if (devCapsReport((JDFNode)vNodes.elementAt(i), testlists, level)!=null)
                            {
                                bExit = true;
                            }
                        }
                    }
                    if (!bExit)
                        execNodes.addElement(jdfRoot);
                }
            }
        }
        else
        {
            // here vNodes is jdfRoot + all children
            Vector vNodes = jdfRoot.getvJDFNode(null, null,false);
            for (int i=0; i<vNodes.size(); i++) 
            {
                JDFNode n = (JDFNode)vNodes.elementAt(i);
                typeNode = n.getType();
                XMLDoc nOutput = null;
                boolean bException = false;
                try 
                {
                    nOutput = report(n,testlists,level); // if report throws exception - n is non-executable Node 
                }
                catch (JDFException jdfe)
                {
                    bException = true;
                }
                if (nOutput == null && !bException)
                {
                    if (typeNode.equals("Product") || typeNode.equals("ProcessGroup")) 
                    { // to the executable add only the "highest" executable product/processGroup node
                        JDFNode parent = n.getParentJDF();
                        if (parent==null || !parent.getType().equals(typeNode))
                        {    // 'n' is already the highest product/processGroup node
                            execNodes.addElement(n); 
                        }
                        else 
                        {
                            XMLDoc parentOutput = null;
                            boolean bCaughtException = false;
                            try 
                            {
                                parentOutput = report(parent, testlists, level);
                            }
                            catch (JDFException jdfe) 
                            {
                                bCaughtException = true;
                            }
                            if (parentOutput != null || bCaughtException) 
                            {// 'n' is executable, 'parent' is not - append 'n' to execNodes
                                execNodes.addElement(n);
                            }
                        }
                    }
                    else 
                    {
                        execNodes.addElement(n);
                    }
                }
            }
        }
        return execNodes;
    }
    
    
    /**
     * For the given JDFNode 'jdfRoot' composes a BugReport in XML form. 
     * For 'jdfRoot' and every child rejected Node gives a list of error messages
     * If there are no errors - returns null !
     *
     * @param JDFNode jdfRoot - the node we test
     * @param EnumFitsValue testlists - FitsValue_Allowed or FitsValue_Present testlists
     * that are specified for the State elements. (Will be used in fitsValue method of the State element)
     * @param EnumValidationLevel level - validation level
     * @return XMLDoc - XMLDoc output of the error messages. If XMLDoc equals null - there are no errors
     */
    public final XMLDoc getBadJDFInfo(final JDFNode jdfRoot, final EnumFitsValue testlists, 
            final EnumValidationLevel level)
    {
        XMLDoc bugReport = new XMLDoc("BugReport", null);
        KElement outputRoot = bugReport.getRoot();
        
        Vector vNodes = jdfRoot.getvJDFNode(null, null, false);
        
        final int size = vNodes.size();
        for (int i=0; i < size; i++) 
        {
            JDFNode n = (JDFNode)vNodes.elementAt(i);
            XMLDoc rejectedNodeReport = null;
            try
            {
                rejectedNodeReport = report(n, testlists, level);
            }
            catch (JDFException jdfe)
            {
                KElement e = outputRoot.appendElement("RejectedNode");
                e.setAttribute("CaughtException", jdfe.getMessage());
                e.setAttribute("ID", n.getID());
                e.setAttribute("XPath", n.buildXPath());
            }
            if (rejectedNodeReport!=null)
            {
                outputRoot.copyElement(rejectedNodeReport.getRoot(), null);
            }
        }
        
        if (!outputRoot.hasChildNodes())
            bugReport = null;
        
        return bugReport;
    }
    
    
    
    /**
     * Checks if Device can execute the given JDFNode 'jdfRoot'.
     * First validates 'jdfRoot' and checks if its Type/Types attributes  
     * fit the values of DeviceCap/@Types and DeviceCap/@CombinedMethod.
     * If Node is invalid or Type/Types don't fit - doesn't check it more detailed.
     * If Type/Types fit, tests a whole JDFNode - all elements and attributes - to define if Device can accept it.
     * Composes a detailed report in XML form of the found errors if JDFNode was rejected.
     * If XMLDoc equals null - there are no errors and 'jdfRoot' is accepted
     * 
     * @param JDFNode jdfRoot - the node we test
     * @param EnumFitsValue testlists - FitsValue_Allowed or FitsValue_Present testlists
     * that are specified for the State elements. (Will be used in fitsValue method of the State element)
     * @param EnumValidationLevel level - validation level
     * @return XMLDoc - XMLDoc output of the error messages.
     * If XMLDoc equals null - there are no errors, 'jdfRoot' is accepted
     * 
     * @throws JDFException if DeviceCapabilities file is invalid: illegal value of Types(TypeExpression) attribute
     * (if CombinedMethod is None and Types contains more than 1 process)
     * @throws JDFException if DeviceCapabilities file is invalid: illegal value of CombinedMethod attribute
     */
    private final XMLDoc report(final JDFNode jdfRoot, EnumFitsValue testlists, EnumValidationLevel level)
    {
        XMLDoc testResult = new XMLDoc("RejectedNode", null);
        KElement root = testResult.getRoot();
        root.setAttribute("XPath", jdfRoot.buildXPath());
        root.setAttribute("ID", jdfRoot.getID());
        
        if (!jdfRoot.isValid(level)) 
        {
            root.setAttribute("IsValid", false, null);
        }
        if(!matchesType(jdfRoot,false))
        {
            String typeNode = jdfRoot.getType();
            String typeExp = getTypeExpression();
            reportTypeMatch(root,false,typeNode,typeExp);
            return testResult;            
        }
        String typeNode = jdfRoot.getType();
        
        Vector vCombMethod = getCombinedMethod();
        for(int combi=0;combi<vCombMethod.size();combi++)
        {
            EnumCombinedMethod combMethod=(EnumCombinedMethod)vCombMethod.elementAt(combi);
            String typeExp = getTypeExpression();
            if (combMethod.equals(EnumCombinedMethod.None))  // node is an individual process
            {
                
                if (typeNode.equals("Product"))
                {
                    testResult = productReport(jdfRoot, testlists, level);
                }
                else
                {
                    XMLDoc devCapsTestResult = devCapsReport(jdfRoot,testlists,level);
                    if (devCapsTestResult != null) 
                    {
                        reportTypeMatch(root,true,typeNode,typeExp);
                        moveChildElementVector_Static(root,devCapsTestResult.getRoot());
                    }
                }
            }
            else if (combMethod.equals(EnumCombinedMethod.Combined))
            {
                testResult = combinedNodeReport(jdfRoot, testlists, level);
            }
            else if (combMethod.equals(EnumCombinedMethod.ProcessGroup))
            {
                testResult = processGroupReport(jdfRoot, testlists, level);
            } 
            else if (combMethod.equals(EnumCombinedMethod.CombinedProcessGroup))
            {
                if (typeNode.equals("ProcessGroup")) 
                {
                    testResult = processGroupReport(jdfRoot, testlists, level);
                }
                else 
                {
                    testResult = combinedNodeReport(jdfRoot, testlists, level);
                }
            }
            // __Lena__ if CombinedMethod_GrayBox: {return true;}
            else 
            {
                throw new JDFException ("JDFDeviceCap.report: Invalid DeviceCap: illegal value of CombinedMethod attribute"); 
            }
            if (testResult!=null)
                root = testResult.getRoot();
        }
        
        //TODO ???
        if (!root.hasChildElements() && !root.hasAttribute("FitsType"))
            testResult = null;
        
        return testResult;
    }
    
//////////////////////////////////////////////////////////////////////////    
    
    /**
     * test whether agiven node has the coreect Types and Type Attribute
     * 
     * @param testRoot the JDF or JMF to test
     * @param bLocal if true, only check the root of this, else also check children
     * 
     * @return boolean true if this DeviceCaps TypeExpression fits the testRoot/@Type and testRoot/@Types
     * 
     */
    public boolean matchesType(JDFNode testRoot, boolean bLocal)
    {
        VElement v=getMatchingTypeNodeVector(testRoot);
        if(v==null)
            return false;
        if(bLocal)
            return v.contains(testRoot);
        return true;
        
    }
    /**
     * test whether a given node has the coreect Types and Type Attribute
     * 
     * @param testRoot the JDF or JMF to test
     * 
     * @return boolean true if this DeviceCaps TypeExpression fits the testRoot/@Type and testRoot/@Types
     * 
     */
    public VElement getMatchingTypeNodeVector(JDFNode testRoot)
    {
        VElement v=new VElement();
        String typeNode = testRoot.getType();

        Vector vCombMethod = getCombinedMethod();
        final String typeExp = getTypeExpression();
        for(int j=0;j<vCombMethod.size();j++)
        {
            EnumCombinedMethod combMethod = (EnumCombinedMethod)vCombMethod.elementAt(j);

            if (combMethod.equals(EnumCombinedMethod.None))  // node is an individual process
            {            
                if (StringUtil.matches(typeNode,typeExp))
                {
                    v.add(testRoot);
                }
            }
            else if (combMethod.equals(EnumCombinedMethod.Combined))
            {
                if (fitsTypes(testRoot.getTypes()))
                {
                    v.add(testRoot);
                }
            }
            else if (combMethod.equals(EnumCombinedMethod.ProcessGroup))
            {
                VElement vNodes=testRoot.getvJDFNode(null,null,false);
                final int size = vNodes.size();
                for(int i=0;i<size-1;i++) // note the 1 which skips this
                {
                    JDFNode node=(JDFNode) vNodes.elementAt(i);
                    if(StringUtil.matches(node.getType(),typeExp))
                        v.add(node);                    
                } 
                if(v.size()>0 && ! v.contains(testRoot))
                    v.add(testRoot);
            }
            else if (combMethod.equals(EnumCombinedMethod.CombinedProcessGroup))
            {
                if (typeNode.equals("ProcessGroup")) 
                {
                    if(fitsTypes(testRoot.getAllTypes()))
                        v.add(testRoot);                    
                        
                }
                else if (typeNode.equals("Combined"))
                {
                    if( fitsTypes(testRoot.getTypes()))
                        v.add(testRoot);
                }            
                else
                {
 //                   return false;
                }
            }
            // TODO __Lena__ if CombinedMethod_GrayBox: {return true;}
            else 
            {
                throw new JDFException ("JDFDeviceCap.report: Invalid DeviceCap: illegal value of CombinedMethod attribute"); 
            }
        }
        return v.size()==0 ? null : v;
    }

////////////////////////////////////////////////////////////////////////////////////////////////////////
    
    private void reportTypeMatch(KElement report, boolean matches, String typeNode, String typeExp)
    {
        report.setAttribute("FitsType", matches, null);
        report.setAttribute("NodeType",typeNode);
        report.setAttribute("CapsType",typeExp);
        if(!matches)
            report.setAttribute("Message","Node Type: "+typeNode+" does not match capabilities type: "+typeExp);
     }


    /**
     * Checks if Device can execute the given Product JDFNode 'jdfRoot' (JDFNode/@Type=Product) .
     * If JDFNode/@Types fits DeviceCap/@Types tests a whole JDFNode and all children Product Nodes 
     * to define if Device can accept it.
     * Composes a detailed report in XML form of the found errors if JDFNode is rejected.  
     *
     * @param JDFNode jdfRoot - the node we test
     * @param EnumFitsValue testlists - FitsValue_Allowed or FitsValue_Present testlists
     * that are specified for the State elements (Will be used in fitsValue method of the State element)
     * @param EnumValidationLevel level - validation level
     * 
     * @return XMLDoc - XMLDoc output of the error messages. 
     * If XMLDoc equals null - there are no errors, 'jdfRoot' is accepted 
     */
    private final XMLDoc productReport(final JDFNode jdfRoot, EnumFitsValue testlists, EnumValidationLevel level) 
    {
        XMLDoc testResult = new XMLDoc("RejectedNode", null);
        KElement root = testResult.getRoot();
        root.setAttribute("XPath", jdfRoot.buildXPath());
        root.setAttribute("ID", jdfRoot.getID());
        root.setAttribute("FitsType", true, null);
        
        XMLDoc devCapsTestResult = null;
        
        // check the status of all child jdf intent product nodes
        Vector vNodes= jdfRoot.getvJDFNode("Product", null, false);
        for (int i=0; i < vNodes.size()-1; i++) 
        {
            JDFNode n = (JDFNode)vNodes.elementAt(i);
            devCapsTestResult = devCapsReport(n, testlists, level);
            if (devCapsTestResult != null) 
            {
                KElement childRoot = root.appendElement("RejectedChildNode");
                childRoot.setAttribute("XPath", n.buildXPath());
                childRoot.setAttribute("ID", n.getID());
                moveChildElementVector_Static(childRoot,devCapsTestResult.getRoot());
            }
        }
        
        devCapsTestResult = devCapsReport(jdfRoot,testlists,level);
        if (devCapsTestResult != null) 
        {
            moveChildElementVector_Static(root,devCapsTestResult.getRoot());
        }
        
        if (!root.hasChildElements())
            testResult = null;
        
        return testResult;
    }
    
    
    /**
     * Checks if Device can execute the given Combined JDFNode 'jdfRoot' (JDFNode/@Type=Combined).
     * If JDFNode/@Types fits DeviceCap/@Types tests a whole JDFNode - all elements and attributes 
     * to define if Device can accept it.
     * Composes a detailed report in XML form of the found errors if JDFNode is rejected.  
     *
     * @param JDFNode jdfRoot - the node we test
     * @param EnumFitsValue testlists - FitsValue_Allowed or FitsValue_Present testlists
     * that are specified for the State elements (Will be used in FitsValue method of the State element)
     * @param EnumValidationLevel level - validation level
     * 
     * @return XMLDoc - XMLDoc output of the error messages. 
     * If XMLDoc equals null - there are no errors, 'jdfRoot' is accepted 
     */
    private final XMLDoc combinedNodeReport(final JDFNode jdfRoot, EnumFitsValue testlists, EnumValidationLevel level)
    {
        XMLDoc testResult = new XMLDoc("RejectedNode", null);
        KElement root = testResult.getRoot();
        root.setAttribute("XPath", jdfRoot.buildXPath());
        root.setAttribute("ID", jdfRoot.getID());
        
        VString typesNode = jdfRoot.getTypes();
        boolean bWrongType = !fitsTypes(typesNode);
        if (bWrongType) 
        {
            root.setAttribute("FitsType", false, null);
        }
        else 
        {
            root.setAttribute("FitsType", true, null);
            
            XMLDoc devCapsTestResult = devCapsReport(jdfRoot,testlists,level);
            if (devCapsTestResult != null) 
            {
                moveChildElementVector_Static(root,devCapsTestResult.getRoot());
            }
        }
        
        if (!root.hasChildElements() && root.getBoolAttribute("FitsType", null, true))
            testResult = null;
        
        return testResult;
    }
    
    
    
    /**
     * Tests JDFNode/@Types (or equivalent of Types in the ProcessGroupNodes - 
     * the concatenated string of all Type attributes in the children Nodes) 
     * to define if it matches DeviceCap/@Types or DeviceCap/@TypeExpression  
     *
     * @param VString typesNode - attribute Types of the tested JDFNode
     * @return boolean - true if JDFNode/@Types fits DeviceCap/@Types or DeviceCap/@TypeExpression
     * @throws JDFException if DeviceCap is invalid: both @Types and @TypeExpression are missing
     */
    private final boolean fitsTypes(VString typesNode)
    {
        if(typesNode==null || typesNode.isEmpty())
            return false;
        
        String typesNodeStr = typesNode.getString(JDFConstants.BLANK, JDFConstants.EMPTYSTRING, JDFConstants.EMPTYSTRING);
        String typeExp = getTypeExpression();
        if (typeExp.length()==0) 
        {
            throw new JDFException ("JDFDeviceCap.fitsTypes: Invalid DeviceCap - missing attributes Types and TypeExpression");
        }
        if (hasAttribute(AttributeName.TYPEEXPRESSION)) 
        {
            if (!StringUtil.matches(typesNodeStr,typeExp))
                return false;
        }
        else 
        {
            VString dcTypes = getTypes();
            for (int i=0; i < typesNode.size(); i++) 
            {
                String type = (String) typesNode.elementAt(i);
                
                if (!dcTypes.contains(type)) 
                    return false;
            }
        }
        return true;
    }
    
    
    /**
     * Checks if Device can execute the given ProcessGroup JDFNode 'jdfRoot' (JDFNode/@Type=ProcessGroup).
     * If JDFNode/@Types fits DeviceCap/@Types tests a whole JDFNode - all elements and attributes 
     * to define if Device can accept it.
     * Composes a detailed report in XML form of the found errors if JDFNode is rejected. 
     *
     * @param JDFNode jdfRoot - the node we test
     * @param EnumFitsValue testlists - FitsValue_Allowed or FitsValue_Present testlists
     * that are specified for the State elements. (Will be used in FitsValue method of the State element)
     * @param EnumValidationLevel level - validation level
     * 
     * @return XMLDoc - XMLDoc output of the error messages. 
     * If XMLDoc equals null - there are no errors, 'jdfRoot' is accepted 
     */
    private final XMLDoc processGroupReport(final JDFNode jdfRoot, EnumFitsValue testlists, EnumValidationLevel level)
    {
        XMLDoc testResult = new XMLDoc("RejectedNode", null);
        KElement root = testResult.getRoot();
        root.setAttribute("XPath", jdfRoot.buildXPath());
        root.setAttribute("ID", jdfRoot.getID());
        
        VString typesNode;
        try 
        {
            typesNode = jdfRoot.getAllTypes();
        }
        catch (JDFException jdfe)
        {
            root.setAttribute("FitsType", false, null);
            root.setAttribute("Message", jdfe.getMessage());
            return testResult;
        }
        
        boolean bWrongType = !fitsTypes(typesNode);
        if (bWrongType)  
        {
            root.setAttribute("FitsType", false, null);
        }
        else 
        {
            root.setAttribute("FitsType", true, null);
            XMLDoc devCapsTestResult = null;
            
            // check the status of all child nodes
            Vector vNodes= jdfRoot.getvJDFNode(null, null, false);
            for (int i=0; i < vNodes.size()-1; i++) 
            {
                JDFNode n = (JDFNode)vNodes.elementAt(i);
                devCapsTestResult = devCapsReport(n, testlists, level);
                if (devCapsTestResult != null) 
                {
                    KElement childRoot = root.appendElement("RejectedChildNode");
                    childRoot.setAttribute("XPath", n.buildXPath());
                    childRoot.setAttribute("ID", n.getID());
                    moveChildElementVector_Static(childRoot,devCapsTestResult.getRoot());
                }
            }
            
            devCapsTestResult = devCapsReport(jdfRoot,testlists,level);
            if (devCapsTestResult != null) 
            {
                moveChildElementVector_Static(root,devCapsTestResult.getRoot());
            }
            
            if (!root.hasChildElements() && root.getBoolAttribute("FitsType", null, true))
                testResult = null;
            
        }
        return testResult;
        
    }
    
    
    
    /**
     * devCapsReport - for every DevCaps element that DeviceCap consists of, 
     * looks for appropriate elements in JDFNode and tests them.
     * Composes a detailed report of the found errors in XML form. 
     * If XMLDoc equals null - there are no errors
     * 
     * @param JDFNode jdfRoot - the node we test
     * @param EnumFitsValue testlists - FitsValue_Allowed or FitsValue_Present testlists
     * that are specified for the State elements. (Will be used in fitsValue method of the State element)
     * @param EnumValidationLevel level - validation level
     * @return XMLDoc - XMLDoc output of the error messages. 
     * If XMLDoc equals null - there are no errors, 'jdfRoot' is accepted 
     */
    private final XMLDoc devCapsReport(final JDFNode jdfRoot, EnumFitsValue testlists, EnumValidationLevel level)
    {
        XMLDoc testResult = new XMLDoc("Temp", null); // root is a temporary Node
        KElement root = testResult.getRoot();
        
        // first test if there are in the JDFNode any ResourceLink or NodeInfo/CustomerInfo 
        // that are not described by DevCaps
        XMLDoc temp = missingDevCaps(jdfRoot);
        if (temp != null) 
        {
            root.copyElement(temp.getRoot(), null);
        }
        
        // if all resourceLinks and NodeInfo/CustomerInfo elements (optional) 
        // are specified as DevCaps, we may test them. 
        temp = invalidDevCaps(jdfRoot, testlists, level);
        
        if (temp != null)
        {
            moveChildElementVector_Static(root,temp.getRoot());
        }
        
        temp = actionPoolReport(jdfRoot);
        if (temp != null)
        {
            root.copyElement(temp.getRoot(), null);
        }
        
        if (!root.hasChildElements())
            testResult = null;
        
        return testResult;
    }
    
    
    
    
    /**
     * invalidDevCaps - tests if there are in the JDFNode any invalid or missing Resources 
     * or NodeInfo/CustomerInfo elements. Composes a detailed report of the found errors in XML form. 
     * If XMLDoc equals null - there are no errors
     * 
     * @param JDFNode jdfRoot - node we test
     * @return XMLDoc - XMLDoc output of the error messages. If XMLDoc equals null - there are no errors
     * @throws JDFException if DeviceCap is invalid: has a wrong attribute Context value 
     */
    private final XMLDoc invalidDevCaps(final JDFNode jdfRoot, EnumFitsValue testlists, EnumValidationLevel level)
    {
        XMLDoc testResult = new XMLDoc("Temp", null); // root is a temporary Node
        KElement root = testResult.getRoot();
        KElement mrp = root.appendElement("MissingResources");
        KElement irp = root.appendElement("InvalidResources");
        
        JDFElement resLinkPool = jdfRoot.getResourceLinkPool();
        VElement vDevCaps = getChildElementVector(ElementName.DEVCAPS, null, null, true, 0, false);
        for (int i=0; i < vDevCaps.size(); i++) 
        {
            JDFDevCaps devCaps = (JDFDevCaps) vDevCaps.elementAt(i);
            VElement vElemResources = devCaps.getMatchingElementsFromNode(jdfRoot);
            int svElemResources = vElemResources==null ? 0 : vElemResources.size();
            
            final EnumContext context = devCaps.getContext();
            KElement r;
            if (requiredLevel(level) && svElemResources<devCaps.getMinOccurs())
            {
                 if (context.equals(EnumContext.Element)) 
                {
                    r = mrp.appendElement("MissingElement");
                    r.setAttribute("XPath", jdfRoot.buildXPath()+ "/" + devCaps.getName());
                }
                else 
                {
                    final EnumUsage linkUsage = devCaps.getLinkUsage();
                    final String procUsage=devCaps.getProcessUsage();
                    r = mrp.appendElement("MissingResourceLink");
                    if (linkUsage!=null)
                    {
                        r.setAttribute("Usage", linkUsage.getName());
                    }
                    if (procUsage!=null && procUsage.length()>0)
                    {
                        r.setAttribute("ProcessUsage", procUsage);
                    }
                    if(resLinkPool==null)
                        resLinkPool=jdfRoot; // fudge against npe in next line
                    r.setAttribute("XPath", resLinkPool.buildXPath()+ "/" + devCaps.getName());
                }                    
                r.setAttribute("Name", devCaps.getName());
                r.setAttribute("CapXPath", devCaps.getName());                    
                r.setAttribute("Occurrences", svElemResources,null);
                r.setAttribute("MinOccurrs", devCaps.getMinOccurs(),null);
            }
            else if (svElemResources>devCaps.getMaxOccurs())
            {
                if (context.equals(EnumContext.Element)) 
                {
                    r = irp.appendElement("ManyElement");
                    r.setAttribute("XPath", jdfRoot.buildXPath()+ "/" + devCaps.getName());
                }
                else 
                {
                    final EnumUsage linkUsage = devCaps.getLinkUsage();
                    final String procUsage=devCaps.getProcessUsage();
                    r = irp.appendElement("ManyResourceLink");
                    if (linkUsage!=null)
                    {
                        r.setAttribute("Usage", linkUsage.getName());
                    }
                    if (procUsage!=null && procUsage.length()>0)
                    {
                        r.setAttribute("ProcessUsage", procUsage);
                    }
                    if(resLinkPool==null)
                        resLinkPool=jdfRoot; // fudge against npe in next line
                    r.setAttribute("XPath", resLinkPool.buildXPath()+ "/" + devCaps.getName());
                }                    
                r.setAttribute("Name", devCaps.getName());
                r.setAttribute("CapXPath", devCaps.getName()); 
                r.setAttribute("Occurrences", svElemResources,null);
                r.setAttribute("MaxOccurrs", devCaps.getMaxOccurs(),null);
            }
 
            XMLDoc devCapTestResult = devCaps.devCapReport(vElemResources,testlists,level); // InvalidResources
            if (devCapTestResult != null) 
            {
                moveChildElementVector_Static(irp,devCapTestResult.getRoot());
            }
        }
        
        if (!mrp.hasChildElements() && !irp.hasChildElements()) // XML output stuff
        { 
            testResult = null;
        } 
        else 
        {
            if (!mrp.hasChildElements())
                root.removeChild(mrp);
            
            if (!irp.hasChildElements())
                root.removeChild(irp);
        }
        
        return testResult;
    }


    
    /**
     * missingDevCaps - tests if there are in the JDFNode any Resources 
     * or NodeInfo/CustomerInfo elements that are not described by DevCaps.
     * If missing DevCaps are found it means that Node has unknown for this Devide resources or elements.
     * Composes a detailed report of the found errors in XML form. If XMLDoc equals null - there are no errors
     * 
     * @param JDFNode jdfRoot - node we test
     * @return XMLDoc - XMLDoc output of the error messages. If XMLDoc equals null - there are no errors 
     */
    private final XMLDoc missingDevCaps(final JDFNode jdfRoot)
    {
        XMLDoc testResult = new XMLDoc("UnknownResources", null);
        KElement root = testResult.getRoot();
        
        JDFResourceLinkPool resLinkPool = jdfRoot.getResourceLinkPool();
        if ((resLinkPool != null))
        {
            VElement vLinks = resLinkPool.getChildElementVector(null, null, null, true, 0, false);
            
            for (int j=0; j < vLinks.size(); j++) 
            {
                JDFResourceLink link = (JDFResourceLink) vLinks.elementAt(j);
                final String resName = link.getLinkedResourceName();
                final String processUsage=link.getProcessUsage();
                
                JDFAttributeMap map = new JDFAttributeMap(AttributeName.NAME,resName);
                VElement vDevCaps = getChildElementVector(ElementName.DEVCAPS,
                        null, map, true, 0, false);
                
                boolean bFound=false;
                final int size = vDevCaps.size();
                for (int k=0; k < size && !bFound; k++) 
                {
                    JDFDevCaps dc = (JDFDevCaps) vDevCaps.elementAt(k);
                    if ((!dc.hasAttribute(AttributeName.LINKUSAGE)||
                            dc.getLinkUsage().getName().equals(link.getUsage().getName()))
                            &&(dc.getProcessUsage().equals(processUsage)))
                    {
                        bFound=true;
                    }
                }
                if (!bFound) 
                { // no DevCaps with Name=resName and the corresponding LinkUsage were found
                    KElement r = root.appendElement("UnknownResource");
                    r.setAttribute("XPath", link.buildXPath());
                    r.setAttribute("Name", resName);
                    if (link.hasAttribute(AttributeName.USAGE, null, false) 
                            && !link.getUsage().getName().equals("Unknown"))
                    {
                        r.setAttribute("Usage", link.getUsage().getName());
                    }
                    r.setAttribute("Message", "Found no DevCaps description for this resource");
                }
            }
        }
        
        checkNodeInfoCustomerInfo(jdfRoot, root, ElementName.NODEINFO);
        checkNodeInfoCustomerInfo(jdfRoot, root, ElementName.CUSTOMERINFO);
        checkNodeInfoCustomerInfo(jdfRoot, root, ElementName.STATUSPOOL);
//        checkNodeInfoCustomerInfo(jdfRoot, root, ElementName.AUDITPOOL);
        
        if (!root.hasChildElements())
            testResult = null;
        
        return testResult;
    }
    
    /**
     * checkNodeInfoCustomerInfo - tests if there are JDFNode/NodeInfo or JDFNode/CustomerInfo 
     * that are not described by DevCaps.
     * If missing DevCaps are found = Node has unknown for this Devide resources or elements
     * 
     * @param JDFNode jdfRoot - node we test
     * @param KElement root - root of the XMLDoc output
     * @param String elementName - "NodeInfo" or "CustomerInfo" or "StatusPool"
     */
    private final void checkNodeInfoCustomerInfo(final JDFNode jdfRoot, KElement root, String elementName)
    {
        JDFAttributeMap map=new JDFAttributeMap();
        map.put(AttributeName.CONTEXT,EnumContext.Element.getName());
        map.put(AttributeName.NAME,elementName);
        final KElement devCaps = getChildByTagName(ElementName.DEVCAPS,null,0,map,true, true);
        if ((jdfRoot.getElement(elementName) != null) && 
            (devCaps  == null)) 
        {
            KElement ue = root.appendElement("UnknownElement");
            ue.setAttribute("XPath", jdfRoot.getElement(elementName).buildXPath());
            ue.setAttribute("Name", elementName);
            ue.setAttribute("Message", "Found no DevCaps description with Context=\"Element\" for: "+elementName);
        }
        return;
    } 
    
    
    
    /**
     * actionPoolReport - tests if the JDFNode fits Actions from ActionPool of this DeviceCap
     * Composes a detailed report of the found errors in XML form. If XMLDoc equals null - there are no errors 
     *
     * @param jdfRoot - node we test
     * @return XMLDoc - XMLDoc output of the error messages. If XMLDoc equals null - there are no errors,
     * JDFNode fits ActionPool of this DeviceCap and will be accepted by Device
     * @throws JDFException if DeviceCap is invalid: ActionPool refers to the non-existent TestPool
     * @throws JDFException if DeviceCap is invalid: Action refers to the non-existent Test
     */
    public final XMLDoc actionPoolReport(final JDFNode jdfRoot)
    {
        XMLDoc testResult = new XMLDoc("ActionPoolReport", null);
        KElement root = testResult.getRoot();
        
        JDFActionPool actionPool = getActionPool();
        if (actionPool != null) 
        {
            JDFTestPool testPool = getTestPool();
            if (testPool == null) 
            {
                throw new JDFException("JDFDeviceCap.actionPoolReport: TestPool is required but was not found. Attempt to operate on a null element");
            }
            VElement vActions = actionPool.getChildElementVector(ElementName.ACTION, null, null, true, 0, false);
            VElement allElms=jdfRoot.getChildrenByTagName(null,null,null,false,true,0);
            allElms.add(jdfRoot); // needed for local JDF test
            final int elmSize = allElms.size();
            for(int i=0;i<elmSize;i++)
            {
                KElement e=(KElement)allElms.elementAt(i);
                for (int j=0; j < vActions.size(); j++) 
                {
                    JDFAction action = (JDFAction) vActions.elementAt(j); 
                    JDFTest test = action.getTest();
                    if (test==null) 
                    {
                        continue;
                        // TODO add report of snafu
//                      throw new JDFException("JDFDeviceCap.actionPoolReport: Test with ID=" + action.getTestRef() + " was not found. Attempt to operate on a null element");
                    }
                    // loop to check whether the test even applies
                    if(!test.fitsContext(e))
                        continue;
                    
                    KElement ar = root.appendElement("ActionReport");
                    
                    if (test.fitsJDF(e,ar)) // If the Test referenced by TestRef evaluates to “true” the combination 
                    {                           // of processes and attribute values described is not allowed
                        KElement arl = root.getChildWithAttribute("ActionReportList","ID",null,action.getID(),0,true);
                        if(arl==null)
                        {
                            arl=root.appendElement("ActionReportList");
                            arl.setAttribute("ID", action.getID());
                            arl.setAttribute("Severity", action.getSeverity().getName());                            
                        }
                        
                        arl.moveElement(ar,null);
                        ar.setAttribute("XPath",e.buildXPath());
                        
                        // __Lena__ TBD choose Loc element according to the language settings
                        final JDFLoc loc = action.getLoc(0);
                        if(loc!=null)
                        {
                            ar.setAttribute("Message", loc.getValue());                       
                            String helpText = loc.getHelpText();
                            if (helpText.length()!=0) 
                            {
                                ar.setAttribute("Help", helpText);
                            }
                        }                   
                    }
                    else
                    {
                        ar.deleteNode(); // zapp it
                    }
                }
            }
        }
        testResult=cleanActionPoolReport(testResult);
        
        return testResult;
    }
    
    /**
     * remove duplicate entries that are parents of lower level entries
     * 
     */ 
    private XMLDoc cleanActionPoolReport(XMLDoc testResult)
    {
        KElement actionPoolReport = testResult.getRoot();
        if (actionPoolReport != null)
        {
            VElement vARL = actionPoolReport.getChildElementVector("ActionReportList", null, null, true, 0, false);
            for (int i = 0; i < vARL.size(); i++)
            {
                VElement actionReportList = vARL.item(i).getChildElementVector("ActionReport", null, null, true, 0, false);
                for (int j = 1; j < actionReportList.size(); j++)
                {
                    KElement e1 = actionReportList.item(j);
                    for (int k = 0; k < j; k++)
                    {
                        KElement e2 = actionReportList.item(k);
                        if (e2 == null)
                            continue;
                        if (e2.getAttribute("XPath").startsWith(e1.getAttribute("XPath")))
                        {
                            e1.deleteNode();
                            break;
                        }
                        else if (e1.getAttribute("XPath").startsWith(e2.getAttribute("XPath")))
                        {
                            e2.deleteNode();
                            actionReportList.setElementAt(null, k);
                        }
                    }
                }
            }

            if (!actionPoolReport.hasChildElements())
                return null;
        }

        return testResult;
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
    
    ////////////////////////////////////////////////////
    
    /**
     * set the defaults of node to the values defined in the child DevCap and State elements
     * @param node the JDFNode in which to set defults
     * @param bLocal if true, set only in the local node, else recurse children
     */
    public boolean setDefaultsFromCaps(JDFNode node, boolean bLocal)
    {
        boolean success=false;
        if(bLocal==false)
        {
            VElement vNode=node.getvJDFNode(null,null,false);
            for(int i=0;i<vNode.size();i++)
            {
                JDFNode nod=(JDFNode)vNode.elementAt(i);
                success = setDefaultsFromCaps(nod,true) || success;
            }
            return success;
        }
        if(!matchesType(node,true))
            return false;
        addResourcesFromDevCaps(node);
        int i;
        VElement vDevCaps=getChildElementVector(ElementName.DEVCAPS,null,null,true,99999,false);
//      step 1, create all missing resources etc
        final int size = vDevCaps.size();
        for(i=0;i<size;i++)
        {
            JDFDevCaps dcs=(JDFDevCaps)vDevCaps.elementAt(i);
            success =  dcs.setDefaultsFromCaps(node) || success;
        }

        return success;
   }


    /**
     * add any missing resources, links or elements that are described by devcaps elements
     * @param node
     */
    private void addResourcesFromDevCaps(JDFNode node)
    {
        VElement vDevCaps=getChildElementVector(ElementName.DEVCAPS,null,null,true,99999,false);
// step 1, create all missing resources etc
        final int size = vDevCaps.size();
        for(int i=0;i<size;i++)
        {
            JDFDevCaps dcs=(JDFDevCaps)vDevCaps.elementAt(i);
            dcs.appendMatchingElementsToNode(node);
        }
    }


    /**
     * get a DevCaps element by name and further restrictions.
     * if an Enumerative restriction is null, the restriction is not checked
     * 
     * @param devCapsName the Name attribute of the DevCaps
     * @param context the Context attribute of the DevCaps
     * @param linkUsage the LinkUsage attribute of the DevCaps
     * @param processUsage the ProcessUsage attribute of the DevCaps
     * @param iSkip the iSkipth matching DevCaps
     * @return JDFDevCaps the matching DevCaps, null if not there
     */
    public JDFDevCaps getDevCapsByName(String devCapsName, EnumContext context, EnumUsage linkUsage, EnumProcessUsage processUsage, int iSkip)
    {
        JDFAttributeMap map=new JDFAttributeMap(AttributeName.NAME,devCapsName);
        if(context!=null)
            map.put(AttributeName.CONTEXT,context.getName());
        if(linkUsage!=null)
            map.put(AttributeName.LINKUSAGE,linkUsage.getName());
        if(processUsage!=null)
            map.put(AttributeName.PROCESSUSAGE,processUsage.getName());
        return (JDFDevCaps)getChildByTagName(ElementName.DEVCAPS,null,iSkip,map,true,true);
    }
    
    /**
     * set attribute CombinedMethod to an individual method
     * 
     * @param method the individual combined method to set
     */
    public void setCombinedMethod(EnumCombinedMethod method)
    {
        setAttribute(AttributeName.COMBINEDMETHOD, method.getName(), null);
    }
    
}
