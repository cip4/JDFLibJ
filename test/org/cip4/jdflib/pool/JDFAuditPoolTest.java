/*
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
package org.cip4.jdflib.pool;

import org.cip4.jdflib.JDFTestCaseBase;
import org.cip4.jdflib.core.ElementName;
import org.cip4.jdflib.core.JDFAudit;
import org.cip4.jdflib.core.JDFDoc;
import org.cip4.jdflib.datatypes.JDFAttributeMap;
import org.cip4.jdflib.node.JDFNode;
import org.cip4.jdflib.node.JDFSpawned;
import org.cip4.jdflib.resource.JDFCreated;
import org.cip4.jdflib.resource.JDFMerged;
import org.cip4.jdflib.resource.JDFResource;

/**
 * @author MuchaD
 *
 * This implements the first fixture with unit tests for class JDFElement.
 */
public class JDFAuditPoolTest extends JDFTestCaseBase
{
    private JDFDoc jdfDoc;        
    private JDFNode jdfRoot;
    private JDFAuditPool myAuditPool;

    /**
     * Method testIncludesAttribute.
     * @throws Exception
     */
    public void testAddCreated() throws Exception
    {
         // Test AddCreated with one parameter
        myAuditPool.addCreated("A_Test_Author", null);
        JDFAudit myAudit = 
            myAuditPool.getAudit(1, JDFAudit.EnumAuditType.Created, new JDFAttributeMap(),null);                  
        String myText  = myAudit.getAuthor();
        assertEquals("Error: Author should be \"A_Test_Author\"", myText, "A_Test_Author");
        // Test AddCreate with two Parameter
                        
        // Get Create a ResourcePool
        JDFResourcePool myResourcePool = jdfRoot.getCreateResourcePool();
        //Append a ResoureElement
        myResourcePool.appendElement("BindingIntent", "");
                
        //Get that element back
        JDFResource e = (JDFResource) myResourcePool.getElement("BindingIntent", "", 0);
        myAuditPool.addCreated("A Test Author for JUnitTest 2", e);
                
        String strResourceID = e.buildXPath("/JDF");
        JDFCreated kResourceAudit = 
            (JDFCreated) myAuditPool.getChildWithAttribute(null, "XPath", null, strResourceID, 0, true);

        assertNotNull("Error: Audit not found ", kResourceAudit);                
    }

    /* (non-Javadoc)
     * @see org.cip4.jdflib.JDFTestCaseBase#setUp()
     */
    protected void setUp() throws Exception
    {
        // TODO Auto-generated method stub
        super.setUp();
 
        jdfDoc = new JDFDoc(ElementName.JDF);        
        jdfRoot = (JDFNode) jdfDoc.getRoot();
        assertNotNull("No Root found", jdfRoot);

        myAuditPool =  jdfRoot.getCreateAuditPool();

    }    
    
    public void testAddMerged() throws Exception
    {
         // Test AddCreated with one parameter
        JDFDoc doc2=new JDFDoc("JDF");
        JDFNode node2=doc2.getJDFRoot();
        
        JDFMerged m1=myAuditPool.addMerged(node2, null, null, null);
        assertNotNull(m1);
    }
        
    public void testAddSpawned() throws Exception
    {
         // Test AddCreated with one parameter
        JDFDoc doc2=new JDFDoc("JDF");
        JDFNode node2=doc2.getJDFRoot();
        
        JDFSpawned m1=myAuditPool.addSpawned(node2, null, null, null, null);
        assertNotNull(m1);
    }
        
    
    
}