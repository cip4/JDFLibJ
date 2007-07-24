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
 * JDFDeviceCapTest.java
 *
 * @author Elena Skobchenko
 *
 * Copyright (c) 2001-2004 The International Cooperation for the Integration
 * of Processes in  Prepress, Press and Postpress (CIP4).  All rights reserved.
 */
package org.cip4.jdflib.devicecapability;

import java.io.File;
import java.util.Vector;

import org.cip4.jdflib.JDFTestCaseBase;
import org.cip4.jdflib.auto.JDFAutoBasicPreflightTest.EnumListType;
import org.cip4.jdflib.auto.JDFAutoDevCaps.EnumContext;
import org.cip4.jdflib.auto.JDFAutoDeviceCap.EnumCombinedMethod;
import org.cip4.jdflib.core.AttributeName;
import org.cip4.jdflib.core.ElementName;
import org.cip4.jdflib.core.JDFDoc;
import org.cip4.jdflib.core.JDFParser;
import org.cip4.jdflib.core.JDFResourceLink;
import org.cip4.jdflib.core.KElement;
import org.cip4.jdflib.core.VElement;
import org.cip4.jdflib.core.VString;
import org.cip4.jdflib.core.XMLDoc;
import org.cip4.jdflib.core.JDFResourceLink.EnumUsage;
import org.cip4.jdflib.core.KElement.EnumValidationLevel;
import org.cip4.jdflib.datatypes.JDFMatrix;
import org.cip4.jdflib.datatypes.JDFBaseDataTypes.EnumFitsValue;
import org.cip4.jdflib.jmf.JDFJMF;
import org.cip4.jdflib.node.JDFNode;
import org.cip4.jdflib.node.JDFNode.EnumProcessUsage;
import org.cip4.jdflib.resource.JDFDevice;
import org.cip4.jdflib.resource.devicecapability.JDFAction;
import org.cip4.jdflib.resource.devicecapability.JDFActionPool;
import org.cip4.jdflib.resource.devicecapability.JDFDevCap;
import org.cip4.jdflib.resource.devicecapability.JDFDevCapPool;
import org.cip4.jdflib.resource.devicecapability.JDFDevCaps;
import org.cip4.jdflib.resource.devicecapability.JDFDeviceCap;
import org.cip4.jdflib.resource.devicecapability.JDFNameState;
import org.cip4.jdflib.resource.devicecapability.JDFNumberState;
import org.cip4.jdflib.resource.devicecapability.JDFTest;
import org.cip4.jdflib.resource.devicecapability.JDFTestPool;
import org.cip4.jdflib.resource.devicecapability.JDFTerm.EnumTerm;
import org.cip4.jdflib.resource.process.JDFContentObject;
import org.cip4.jdflib.resource.process.JDFLayout;
import org.cip4.jdflib.resource.process.JDFRunList;


public class JDFDeviceCapTest extends JDFTestCaseBase
{

    /////////////////////////////////////////////////////////////////
    /////////////////////////////////////////////////////////////////
    private JDFDeviceCap devicecap;
    private JDFDeviceCap devicecapProduct;
    private JDFDevice device;

    protected void setUp() throws Exception
    {
        JDFDoc doc=new JDFDoc("Device");
        device=(JDFDevice) doc.getRoot();
        super.setUp();
        {
            devicecap=device.appendDeviceCap();
            devicecap.setCombinedMethod(EnumCombinedMethod.None);
            devicecap.setTypeExpression("(fnarf)|(blub)");
            devicecap.setTypes(new VString("fnarf blub",null));
            devicecap.setGenericAttributes(new VString("*",null));

            JDFDevCapPool dcp=devicecap.appendDevCapPool();
            JDFDevCaps dcs=devicecap.appendDevCaps();
            dcs.setContext(EnumContext.Element);
            dcs.setName("AuditPool");
            dcs.setRequired(true);
            JDFDevCap dc=dcp.appendDevCap();
            dc.setID("dc_AuditPool");
            dcs.setDevCapRef(dc);
            dc=dc.appendDevCap();
            dc.setName("Created");
            dc.setID("dc_Created");

            dcs=devicecap.appendDevCaps();
            dcs.setContext(EnumContext.Element);
            dcs.setName("AncestorPool");
            dc=dcs.appendDevCap();
            dc.setMinOccurs(0);
            dc.setID("dc_AncestorPool");
            JDFDevCap dc2=dc.appendDevCap();
            dc2.setID("dc_Ancestor");
            dc2.setName("Ancestor");


            for(int pu=0;pu<2;pu++)
            {
                dcs=devicecap.appendDevCaps();
                dcs.setContext(EnumContext.Resource);
                dcs.setName("RunList");
                dcs.setLinkUsage(EnumUsage.Input);
                dcs.setProcessUsage(pu==0 ? "Document": "Marks");
                dc=dcs.appendDevCap();
                if(pu==0)
                {
                    dc.setID("dc_RL_Doc");
                    dc.setMinOccurs(1);
                }
                else
                {
                    dc.setID("dc_RL_Mark");
                    dc.setMinOccurs(0);
                }
            }

            dcs=devicecap.appendDevCaps();
            dcs.setContext(EnumContext.Resource);
            dcs.setName("Layout");
            dcs.setLinkUsage(EnumUsage.Input);
            dc=dcs.appendDevCap();
            dc.setID("dc_Layout");
            dc.setMinOccurs(1);
            JDFDevCap coDC=dc.appendDevCap();
            coDC.setName(ElementName.CONTENTOBJECT);
            coDC.setMinOccurs(2);
            JDFNumberState st=coDC.appendNumberState(AttributeName.CTM);
            st.setRequired(true);
            st.setListType(EnumListType.List);
            st.setMinOccurs(6);
            st.setMaxOccurs(6);
            st.setHasDefault(true);
            st.setAttribute("DefaultValue","1 0 0 1 1 1");

            dcs=devicecap.appendDevCaps();
            dcs.setContext(EnumContext.Link);
            dcs.setLinkUsage(EnumUsage.Input);
            dcs.setName("Layout");
            dc=dcs.appendDevCap();
            dc.setID("dc_LayoutLink");

            dcs=devicecap.appendDevCaps();
            dcs.setContext(EnumContext.Element);
            dcs.setName("JDF");
            dc=dcp.appendDevCap();
            dc.setID("dc_JDF");
            dcs.setDevCapRef(dc);
            doc.write2File(sm_dirTestDataTemp+File.separator+"devCapDefaults.jdf", 2, false);
        }
        {
            devicecapProduct=device.appendDeviceCap();

            Vector vMethods=new Vector();
            vMethods.add(EnumCombinedMethod.None);
            vMethods.add(EnumCombinedMethod.ProcessGroup);
            vMethods.add(EnumCombinedMethod.Combined);
            devicecapProduct.setCombinedMethod(vMethods);
            devicecapProduct.setTypeExpression("((fnarf)|(blub))( (fnarf)|(blub))*");
            devicecapProduct.setTypes(new VString("fnarf blub",null));

            JDFDevCapPool dcp=devicecapProduct.appendDevCapPool();
            JDFDevCaps dcs=devicecap.appendDevCaps();
            dcs.setContext(EnumContext.Element);
            dcs.setName("AuditPool");
            dcs.setRequired(true);
            JDFDevCap dc=dcp.appendDevCap();
            dc.setID("dc_AuditPool");
            dcs.setDevCapRef(dc);
            dc=dc.appendDevCap();
            dc.setName("Created");
            dc.setID("dc_Created");



            dcs=devicecap.appendDevCaps();
            dcs.setContext(EnumContext.Resource);
            dcs.setName("Layout");
            dcs.setLinkUsage(EnumUsage.Input);
            dc=dcs.appendDevCap();
            dc.setID("dc_Layout");
            dc.setMinOccurs(1);
            JDFDevCap coDC=dc.appendDevCap();
            coDC.setName(ElementName.CONTENTOBJECT);
            coDC.setMinOccurs(2);
            coDC.setMaxOccurs(999);
            JDFNumberState st=coDC.appendNumberState(AttributeName.CTM);
            st.setRequired(true);
            st.setListType(EnumListType.List);
            st.setMinOccurs(6);
            st.setMaxOccurs(6);
            st.setHasDefault(true);
            st.setAttribute("DefaultValue","1 0 0 1 1 1");

            dcs=devicecap.appendDevCaps();
            dcs.setContext(EnumContext.Link);
            dcs.setLinkUsage(EnumUsage.Input);
            dcs.setName("Layout");
            dc=dcs.appendDevCap();
            dc.setID("dc_LayoutLink");

            dcs=devicecap.appendDevCaps();
            dcs.setContext(EnumContext.Element);
            dcs.setName("JDF");
            dc=dcp.appendDevCap();
            dc.setID("dc_JDF");
            dcs.setDevCapRef(dc);
        }

    }

    /////////////////////////////////////////////////////////////////

    public void testGetBadJDFInfo() throws Exception
    {
        JDFDoc d=JDFDoc.parseFile(sm_dirTestData+"Device_Elk_ConventionalPrinting2.xml");
        JDFDeviceCap dc=(JDFDeviceCap) d.getRoot().getXPathElement("/JMF/Response/DeviceList/DeviceInfo/Device/DeviceCap");
        assertNotNull(dc);
        JDFDoc d2=JDFDoc.parseFile(sm_dirTestData+"Elk_ConventionalPrinting.jdf");
        JDFNode cpNode=d2.getJDFRoot();
        assertNotNull(cpNode);
        XMLDoc outDoc=dc.getBadJDFInfo(cpNode, EnumFitsValue.Allowed, EnumValidationLevel.Complete);
        assertNull("devcaps are consistently evaluated",outDoc);
    }

    /////////////////////////////////////////////////////////////////


    public void testAction()
    {
        JDFDoc d=new JDFDoc(ElementName.DEVICECAP);
        JDFDeviceCap dc=(JDFDeviceCap)d.getRoot();
        final JDFTestPool tp=dc.appendTestPool();
        JDFTest test=tp.appendTest();
        final JDFActionPool ap=dc.appendActionPool();
        JDFAction a = ap.appendAction();
        a.setTest(test);
        assertEquals("",test,a.getTest());
        assertTrue("",a.hasAttribute("TestRef"));
    }
    /////////////////////////////////////////////////////////////////

    public void testGetDevCapsByName()
    {
        JDFDevCaps dcs=devicecap.getDevCapsByName("AuditPool",null,null,null,0);
        assertNotNull( dcs);
        assertEquals(dcs.getName(),"AuditPool");
        dcs=devicecap.getDevCapsByName("Layout",EnumContext.Resource,null,null,0);
        assertNotNull(dcs);
        assertEquals(dcs.getName(),"Layout");
        assertEquals(dcs.getDevCap().getID(),"dc_Layout");
        dcs=devicecap.getDevCapsByName("Layout",EnumContext.Link,null,null,0);
        assertNotNull( dcs);
        assertEquals(dcs.getName(),"Layout");
        assertEquals(dcs.getDevCap().getID(),"dc_LayoutLink");
        dcs=devicecap.getDevCapsByName("Layout",EnumContext.Element,null,null,0);
        assertNull( dcs);
        dcs=devicecap.getDevCapsByName("RunList",null,null,EnumProcessUsage.Marks,0);
        assertNotNull( dcs);
        dcs=devicecap.getDevCapsByName("RunList",null,null,EnumProcessUsage.Ancestor,0);
        assertNull( dcs);

    }

    /////////////////////////////////////////////////////////////////

    public void testDevCapsMinOccurs()
    {
        JDFDevCaps dcs=devicecap.getDevCapsByName("AuditPool",null,null,null,0);
        assertEquals(dcs.getMinOccurs(),1);
    }
    /////////////////////////////////////////////////////////////////

    public void testDevCapsMaxOccurs()
    {
        JDFDevCaps dcs=devicecap.getDevCapsByName("AuditPool",null,null,null,0);
        assertEquals(dcs.getMaxOccurs(),1);
    }

    /////////////////////////////////////////////////////////////////

    public void testLogic()
    {
        JDFDoc d=new JDFDoc(ElementName.DEVICECAP);
        JDFDeviceCap dc=(JDFDeviceCap)d.getRoot();
        dc.appendDevCapPool();

        final JDFTestPool tp=dc.appendTestPool();
        JDFTest test=tp.appendTest();
        test.appendTerm(EnumTerm.and);
        final JDFActionPool ap=dc.appendActionPool();
        JDFAction a = ap.appendAction();
        a.setTest(test);
//      TODO more
    }
/////////////////////////////////////////////////////////////////

    public void testGetCombinedMethod()
    {
        JDFDoc d=new JDFDoc(ElementName.DEVICECAP);
        JDFDeviceCap dc=(JDFDeviceCap)d.getRoot();
        Vector v=new Vector();
        v.add(EnumCombinedMethod.None);
        assertEquals("default is none",dc.getCombinedMethod(), v);

    }
/////////////////////////////////////////////////////////////////

    public void testDeviceCapIsValid ()
    {
        JDFParser p      = new JDFParser();
        String docDevCap = "DevCaps_Product_MISPrepress_ICS_Minimal.jdf";
        JDFDoc jmfDevCap = p.parseFile(sm_dirTestData + docDevCap);
        assertNotNull("Parse of file " + docDevCap + " failed", jmfDevCap);
        JDFJMF jmfRoot = jmfDevCap.getJMFRoot();
        assertNotNull("jmfRoot == null Can't start Test", jmfRoot);
        JDFDeviceCap deviceCap = (JDFDeviceCap) jmfRoot.getChildByTagName("DeviceCap", "", 0, null, false, true);
        assertTrue(deviceCap.isValid(KElement.EnumValidationLevel.Incomplete));
     }

    ///////////////////////////////////////////////////////

    public void testGetExecutableJDF () throws Exception
    {

        String docTest = "MISPrepress_ICS_Minimal.jdf";
        String docDevCap = "DevCaps_Product_MISPrepress_ICS_Minimal.jdf";

        // parse input file
        JDFParser p = new JDFParser();
        JDFDoc jmfDevCap = p.parseFile(sm_dirTestData + docDevCap);
        JDFJMF jmfRoot = null;
        assertNotNull("Parse of file " + docDevCap + " failed", jmfDevCap);
        jmfRoot = jmfDevCap.getJMFRoot();
        assertNotNull("jmfRoot == null Can't start Test", jmfRoot);
        XMLDoc docOutDevCap = jmfRoot.getOwnerDocument_KElement();
        docOutDevCap.write2File(sm_dirTestDataTemp + "_" + docDevCap, 0, true);

        JDFDoc jdfTest = p.parseFile(sm_dirTestData + docTest);
        JDFNode jdfRoot = jdfTest.getJDFRoot();
        assertTrue("jdfRoot is null", jdfRoot != null);

        if (jdfRoot != null) {
	        jdfRoot.getOwnerDocument_KElement();
	        JDFDeviceCap deviceCap = (JDFDeviceCap)
	        	jmfRoot.getChildByTagName("DeviceCap", null, 0, null, false, true);

	        EnumFitsValue testlists = EnumFitsValue.Allowed;
	        EnumValidationLevel level = KElement.EnumValidationLevel.Complete;
	        VElement vExecNodes = deviceCap.getExecutableJDF(jdfRoot, testlists, level);
	        if (vExecNodes==null)
	        {
	            System.out.println(docDevCap + ": found No matching JDFNode");
	        }
	        else
	        {
	            for (int n = 0; n < vExecNodes.size(); n++)
	            {
	                // XMLDoc docExecNodes = ((JDFNode)
	                // vExecNodes.elementAt(n)).getOwnerDocument_KElement();
	                // docExecNodes.write2File ("temp\\" + "_" + docTest +"_ExecNode" + (n+1) +
	                // ".jdf", 0);
	                System.out.println(vExecNodes.elementAt(n));

	            }
	        }

	        XMLDoc testResult = deviceCap.getBadJDFInfo(jdfRoot, testlists, level);
	        if (testResult != null)
	        {
	            testResult.write2File(sm_dirTestDataTemp + "_BugReport.xml", 0, true);
	        }
		}
    }

/////////////////////////////////////////////////////////////////

    public void testGetMatchingTypeNodeVector()
    {
        JDFDoc d=new JDFDoc("JDF");
        JDFNode n=d.getJDFRoot();
        n.setType("bar",false);
        assertNull(devicecap.getMatchingTypeNodeVector(n));

        n.setType("fnarf",false);
        assertTrue(devicecap.getMatchingTypeNodeVector(n).contains(n));

        devicecap.setCombinedMethod(EnumCombinedMethod.ProcessGroup);
        n.setType("ProcessGroup",true);
        JDFNode n2=n.addJDFNode("fnarf");
        assertTrue(devicecap.getMatchingTypeNodeVector(n).contains(n));
        assertTrue(devicecap.getMatchingTypeNodeVector(n).contains(n2));
        assertNull("want pg but have local node",devicecap.getMatchingTypeNodeVector(n2));

    }

/////////////////////////////////////////////////////////////////


    public void testGetMatchingDeviceCapVector()
    {
        JDFDoc d=new JDFDoc("JDF");
        JDFNode n=d.getJDFRoot();
        n.setType("bar",false);
        assertNull(device.getMatchingDeviceCapVector(n, true));
        assertNull(device.getMatchingDeviceCapVector(n, false));
        n.setType("fnarf",false);
        assertEquals(device.getMatchingDeviceCapVector(n, true).size(),2);
        assertEquals(device.getMatchingDeviceCapVector(n, false).size(),2);
        n.setType("ProcessGroup",true);
        JDFNode n2=n.addJDFNode("fnarf");
        assertEquals(device.getMatchingDeviceCapVector(n, true).size(),1);
        assertEquals(device.getMatchingDeviceCapVector(n, false).elementAt(0),devicecapProduct);
        assertEquals(device.getMatchingDeviceCapVector(n2, false).size(),2);

    }
/////////////////////////////////////////////////////////////////

    public void testProcessUsage() throws Exception
    {
        JDFDoc d=new JDFDoc("JDF");
        JDFNode n=d.getJDFRoot();
        n.setType("fnarf",false);

        EnumFitsValue testlists = EnumFitsValue.Allowed;
        EnumValidationLevel level = KElement.EnumValidationLevel.Complete;
        VElement vExecNodes = devicecap.getExecutableJDF(n, testlists, level);
        assertNull("missing resources",vExecNodes);


        JDFLayout lo=(JDFLayout)n.addResource(ElementName.LAYOUT, null, EnumUsage.Input, null, null, null, null);
        lo.appendContentObject().setCTM(new JDFMatrix("1 0 0 1 0 0"));
        lo.appendContentObject().setCTM(new JDFMatrix("1 0 0 1 10 20"));

        JDFRunList rlDoc=(JDFRunList)n.addResource(ElementName.RUNLIST, null, EnumUsage.Input, EnumProcessUsage.Document, null, null, null);
        vExecNodes = devicecap.getExecutableJDF(n, testlists, level);
        assertNotNull("no missing resources",vExecNodes);

        n.addResource(ElementName.RUNLIST, null, EnumUsage.Input, EnumProcessUsage.Marks, null, null, null);
        vExecNodes = devicecap.getExecutableJDF(n, testlists, level);
        assertNotNull("no missing resources",vExecNodes);

        JDFResourceLink rl=n.getLink(rlDoc, null);
        rl.setUsage(EnumUsage.Output);
        vExecNodes = devicecap.getExecutableJDF(n, testlists, level);
        assertNull("no required runlist doc",vExecNodes);

        rl.setUsage(EnumUsage.Input);
        vExecNodes = devicecap.getExecutableJDF(n, testlists, level);
        assertNotNull("no required runlist doc",vExecNodes);

        JDFDevCaps dcsRLDoc=devicecap.getDevCapsByName("RunList", null, null, EnumProcessUsage.Document, 0);
        JDFNameState ns=dcsRLDoc.getDevCap().appendNameState("RunTag");
        ns.setRequired(true);

        vExecNodes = devicecap.getExecutableJDF(n, testlists, level);
        assertNull("incomplete required runlist doc",vExecNodes);

        ns.setRequired(false);
        vExecNodes = devicecap.getExecutableJDF(n, testlists, level);
        assertNotNull("incomplete required runlist doc",vExecNodes);

        JDFDevCaps dcsRLMarks=devicecap.getDevCapsByName("RunList", null, null, EnumProcessUsage.Marks, 0);
        JDFNameState nsMarks=dcsRLMarks.getDevCap().appendNameState("PageNames");
        nsMarks.setRequired(true);

        vExecNodes = devicecap.getExecutableJDF(n, testlists, level);
        assertNull("incomplete required runlist marks",vExecNodes);

    }
/////////////////////////////////////////////////////////////////

    public void testMatchesType()
    {
        JDFDoc d=new JDFDoc("JDF");
        JDFNode n=d.getJDFRoot();
        n.setType("bar",false);
        assertFalse(devicecap.matchesType(n,true));
        assertFalse(devicecap.matchesType(n,false));
        assertFalse(device.matchesType(n,true));
        assertFalse(device.matchesType(n,false));

        n.setType("fnarf",false);
        assertTrue(devicecap.matchesType(n,true));
        assertTrue(devicecap.matchesType(n,false));
        assertTrue(device.matchesType(n,true));
        assertTrue(device.matchesType(n,false));

        n.setType("blub",false);
        assertTrue(devicecap.matchesType(n,true));
        assertTrue(devicecap.matchesType(n,false));
        assertTrue(device.matchesType(n,true));
        assertTrue(device.matchesType(n,false));

        n.setType("Combined",false);
        n.setTypes(new VString("blub fnarf"," "));
        assertFalse(devicecap.matchesType(n,true));
        assertFalse(devicecap.matchesType(n,false));
        assertTrue(devicecapProduct.matchesType(n,true));
        assertTrue(devicecapProduct.matchesType(n,false));
        assertTrue(device.matchesType(n,false));

        devicecap.setCombinedMethod(EnumCombinedMethod.ProcessGroup);
        n.setType("ProcessGroup",true);
        JDFNode n2=n.addJDFNode("fnarf");

        assertTrue(devicecap.matchesType(n,true));
        assertTrue(devicecap.matchesType(n,false));
        assertTrue(device.matchesType(n,true));
        assertTrue(device.matchesType(n,false));
        assertFalse("method pg for local individual process",devicecap.matchesType(n2,true));
        assertFalse(devicecap.matchesType(n2,false));
        assertTrue(devicecapProduct.matchesType(n2,false));
        assertTrue(device.matchesType(n2,false));
    }

    /////////////////////////////////////////////////////////////////
    public void testSetDefaultsFromCaps() throws Exception
    {
        JDFDoc d=new JDFDoc("JDF");
        JDFNode n=d.getJDFRoot();
        n.setType("fnarf",false);
        devicecap.setDefaultsFromCaps(n,true,false);
        assertNotNull(n.getResourceLinks("Layout", null, null));
        JDFLayout lo=(JDFLayout) n.getResourcePool().getPoolChild(0,"Layout",null,null);
        final JDFContentObject contentObject = lo.getContentObject(0);
        assertNotNull(contentObject);
        assertEquals(contentObject.getCTM(), new JDFMatrix("1 0 0 1 1 1"));
        assertNotNull(lo.getContentObject(1));
    }
    /////////////////////////////////////////////////////////////////
    public void testCreateModuleCaps() throws Exception
    {
       devicecap.createModuleCaps(null);
       assertNotNull(devicecap.getModulePool());
    }
/////////////////////////////////////////////////////////////////

/////////////////////////////////////////////////////////////////
/////////////////////////////////////////////////////////////////

    public void testGetNamePathVector ()
    {
        {
            JDFDevCap dc=(JDFDevCap) devicecap.getChildWithAttribute(null, AttributeName.ID, null, "dc_Ancestor", 0, false);
            VString v=dc.getNamePathVector(true);
            assertEquals(v.size(),1);
            assertEquals(v.stringAt(0),"JDF/AncestorPool/Ancestor");
        }
        {
            JDFDevCap dc=(JDFDevCap) devicecap.getChildWithAttribute(null, AttributeName.ID, null, "dc_Layout", 0, false);
            VString v=dc.getNamePathVector(true);
            assertEquals(v.size(),1);
            assertEquals(v.stringAt(0),"Layout");
        }
        {
            JDFDevCap dc=(JDFDevCap) devicecap.getChildWithAttribute(null, AttributeName.ID, null, "dc_LayoutLink", 0, false);
            VString v=dc.getNamePathVector(true);
            assertEquals(v.size(),1);
            assertEquals(v.stringAt(0),"LayoutLink");
        }
        {
            JDFDevCap dc=(JDFDevCap) devicecap.getChildWithAttribute(null, AttributeName.ID, null, "dc_Created", 0, false);
            VString v=dc.getNamePathVector(true);
            assertEquals(v.size(),1);
            assertEquals(v.stringAt(0),"JDF/AuditPool/Created");
        }
        {
            JDFDevCap dc=(JDFDevCap) devicecap.getChildWithAttribute(null, AttributeName.ID, null, "dc_JDF", 0, false);
            VString v=dc.getNamePathVector(true);
            assertEquals(v.size(),1);
            assertEquals(v.stringAt(0),"JDF");
        }
    }

}
