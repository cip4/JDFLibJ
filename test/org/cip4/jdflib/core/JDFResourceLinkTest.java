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
package org.cip4.jdflib.core;

import org.cip4.jdflib.JDFTestCaseBase;
import org.cip4.jdflib.core.JDFResourceLink.EnumUsage;
import org.cip4.jdflib.core.KElement.EnumValidationLevel;
import org.cip4.jdflib.datatypes.JDFAttributeMap;
import org.cip4.jdflib.datatypes.JDFIntegerList;
import org.cip4.jdflib.datatypes.VJDFAttributeMap;
import org.cip4.jdflib.jmf.JDFCommand;
import org.cip4.jdflib.jmf.JDFJMF;
import org.cip4.jdflib.jmf.JDFPipeParams;
import org.cip4.jdflib.node.JDFNode;
import org.cip4.jdflib.node.JDFNode.EnumProcessUsage;
import org.cip4.jdflib.node.JDFNode.EnumType;
import org.cip4.jdflib.pool.JDFResourceLinkPool;
import org.cip4.jdflib.resource.JDFResource;
import org.cip4.jdflib.resource.JDFResource.EnumPartIDKey;
import org.cip4.jdflib.resource.JDFResource.EnumPartUsage;
import org.cip4.jdflib.resource.JDFResource.EnumResStatus;
import org.cip4.jdflib.resource.JDFResource.EnumResourceClass;
import org.cip4.jdflib.resource.process.JDFComponent;
import org.cip4.jdflib.resource.process.JDFExposedMedia;
import org.cip4.jdflib.resource.process.JDFMedia;
import org.cip4.jdflib.resource.process.JDFRunList;

/**
 * @author MuchaD
 *
 * This implements the first fixture with unit tests for class JDFElement.
 */
public class JDFResourceLinkTest extends JDFTestCaseBase
{
    public void testAmount() throws Exception
    {
        JDFDoc d=new JDFDoc(ElementName.JDF);
        JDFNode n=d.getJDFRoot();
        n.setVersion(JDFElement.EnumVersion.Version_1_3);
        n.setType("ConventionalPrinting",true);
        JDFMedia m=(JDFMedia)n.addResource(ElementName.MEDIA, null, EnumUsage.Input, null, null, null, null);
        JDFResourceLink rl=n.getLink(m,null);

        m.setAmount(42);
        assertEquals("m amount!=42",m.getAttribute(AttributeName.AMOUNT),"42");
        assertTrue("rl amount!=42",rl.getAmount(null)==42);
        assertTrue("rl amount!=42",rl.getMinAmount(null)==42);
        assertTrue("rl amount!=42",rl.getMaxAmount(null)==42);

        rl.setAmount(44,null);
        assertEquals("ml amount!=44",rl.getAttribute(AttributeName.AMOUNT),"44");
        assertTrue("rl amount!=44",rl.getAmount(null)==44);
        assertTrue("rl amount!=44",rl.getMinAmount(null)==44);
        assertTrue("rl amount!=44",rl.getMaxAmount(null)==44);
    }

    public void testAppendAmountPool() throws Exception
    {
        JDFDoc d=new JDFDoc("MediaLink");
        JDFResourceLink rl=(JDFResourceLink)d.getRoot();
        assertNotNull(rl.appendAmountPool());
        try
        {
            rl.appendAmountPool();
            fail("AmountPool #2");
        }
        catch(JDFException x)
        {
            // nop
        }

    }
    public void testDraftOK() throws Exception
    {
        JDFDoc d=new JDFDoc(ElementName.JDF);
        JDFNode n=d.getJDFRoot();
        n.setVersion(JDFElement.EnumVersion.Version_1_3);
        n.setType("ProcessGroup",true);
        JDFCustomerInfo ci=(JDFCustomerInfo)n.addResource(ElementName.CUSTOMERINFO, null, EnumUsage.Input, null, null, null, null);
        JDFComponent co=(JDFComponent)n.addResource(ElementName.COMPONENT, null, EnumUsage.Output, null, null, null, null);
		JDFResourceLink rl=n.getLink(ci,null);
        assertEquals("available",rl.getMinStatus(),JDFResource.EnumResStatus.Available);

		rl.setDraftOK(true);
        assertEquals("draft",rl.getMinStatus(),JDFResource.EnumResStatus.Draft);
		assertTrue("has no draft",!rl.hasAttribute(AttributeName.DRAFTOK));
		assertTrue("late draft",rl.getMinLateStatus()==JDFResource.EnumResStatus.Draft);
        assertTrue("1.3 valid",rl.isValid(EnumValidationLevel.Complete));
        rl.setDraftOK(false);
        assertEquals("draft",rl.getMinStatus(),JDFResource.EnumResStatus.Available);
        assertTrue("has no draft",!rl.hasAttribute(AttributeName.DRAFTOK));
        assertTrue("late draft",rl.getMinLateStatus()==JDFResource.EnumResStatus.Available);
        assertTrue("1.3 valid",rl.isValid(EnumValidationLevel.Complete));
		rl.removeAttribute(AttributeName.MINSTATUS);

		n.setVersion(JDFElement.EnumVersion.Version_1_2);
		rl.setDraftOK(true);
		assertTrue("draft",rl.getMinStatus()==JDFResource.EnumResStatus.Draft);
		assertTrue("has draft",rl.hasAttribute(AttributeName.DRAFTOK));
		assertTrue("has no MinStatus",!rl.hasAttribute(AttributeName.MINSTATUS));
		assertEquals("late draft",rl.getMinLateStatus(),JDFResource.EnumResStatus.Draft);
        assertTrue("1.2 valid",rl.isValid(EnumValidationLevel.Complete));
        rl.setDraftOK(false);
        assertTrue("draft",rl.getMinStatus()==JDFResource.EnumResStatus.Available);
        assertTrue("has draft",rl.hasAttribute(AttributeName.DRAFTOK));
        assertTrue("has no MinStatus",!rl.hasAttribute(AttributeName.MINSTATUS));
        assertEquals("late draft",rl.getMinLateStatus(),JDFResource.EnumResStatus.Available);
        assertTrue("1.2 valid",rl.isValid(EnumValidationLevel.Complete));

        rl=n.getLink(co,null);
        assertEquals("unavailable",rl.getMinStatus(),JDFResource.EnumResStatus.Unavailable);
	}
    public void testGetMinAmountPoolAttribute()
    {
        JDFDoc d=new JDFDoc("TestLink");
        JDFResourceLink rl=(JDFResourceLink) d.getRoot();
        rl.setActualAmount(12,new JDFAttributeMap("SignatureName","1"));
        rl.setActualAmount(14,new JDFAttributeMap("SignatureName","2"));
        assertEquals(rl.getMinAmountPoolAttribute("ActualAmount",null,null,42),12.,0.);

        JDFAttributeMap map=new JDFAttributeMap("SignatureName","3");
        map.put("SheetName","a");
        rl.setActualAmount(24,map);
        map.put("SheetName","b");
        rl.setActualAmount(26,map);
        assertEquals(rl.getMinAmountPoolAttribute("ActualAmount",null,new JDFAttributeMap("SignatureName","3"),42),24.,0.);
    }

    public void testExpandAmountPool()
    {
        JDFDoc d=new JDFDoc(ElementName.JDF);
        JDFNode n=d.getJDFRoot();
        n.setVersion(JDFElement.EnumVersion.Version_1_3);
        n.setType("ConventionalPrinting",true);
        JDFComponent comp=(JDFComponent)n.addResource(ElementName.COMPONENT, null, EnumUsage.Output, null, null, null, null);
        comp.addPartitions(EnumPartIDKey.SheetName, new VString("S1 S2 S3",null));
        JDFResourceLink rl=n.getLink(comp,null);
        rl.setAmount(42, null);
        rl.setPipeProtocol("JDF");
        assertEquals(rl.getAmount(null), 42.,0.1);
        assertNull(rl.getAmountPool());
        rl.expandAmountPool();
        assertNull(rl.getAttribute("Amount",null,null));
        assertEquals(rl.getAmount(new JDFAttributeMap(EnumPartIDKey.SheetName,"S2")), 42.,0.1);
        assertNotNull(rl.getAmountPool());
        assertEquals(rl.getPipeProtocol(),"JDF");

    }

    public void testGetAmountPoolDouble()
    {
        JDFDoc d=new JDFDoc("TestLink");
        JDFResourceLink rl=(JDFResourceLink) d.getRoot();
        rl.setActualAmount(12,new JDFAttributeMap("SignatureName","1"));
        rl.setActualAmount(14,new JDFAttributeMap("SignatureName","2"));
        assertEquals(rl.getActualAmount(null),26.,0.1);
        rl.setAmount(42., null);
        assertEquals("root attribute is incorrectly retrieved",rl.getAmount(new JDFAttributeMap("SignatureName","2")),42.,0.1);
        rl.removeChild(ElementName.AMOUNTPOOL, null, 0);
        rl.setActualAmount(33, null);
        assertEquals(rl.getActualAmount(null),33.,0.1);
     }
    
    public void testGetAmountPoolVector()
    {
        JDFDoc d=new JDFDoc("TestLink");
        JDFResourceLink rl=(JDFResourceLink) d.getRoot();
        VJDFAttributeMap vM=new VJDFAttributeMap();
        vM.add(new JDFAttributeMap("SignatureName","1"));
        vM.add(new JDFAttributeMap("SignatureName","2"));
        rl.setAmountPoolAttribute("Amount", "42", null, vM);
         
        assertEquals(rl.getAmountPoolAttribute("Amount", null, vM),"42");
        vM.add(new JDFAttributeMap("SignatureName","3"));
        assertNull(rl.getAmountPoolAttribute("Amount", null, vM));
               
    }

	/**
	 * tests whether the convoluted inheritence of partAmount and ResourceLink function correctly
	 * @throws Exception
	 */
	public void testPartAmount() throws Exception
	{

		JDFDoc d=new JDFDoc(ElementName.JDF);
		JDFNode n=d.getJDFRoot();
		n.setVersion(JDFElement.EnumVersion.Version_1_3);
		n.setType("ConventionalPrinting",true);
		JDFExposedMedia xm=(JDFExposedMedia)n.addResource(ElementName.EXPOSEDMEDIA, null, EnumUsage.Input, null, null, null, null);
		JDFExposedMedia xm1=(JDFExposedMedia)xm.addPartition(JDFResource.EnumPartIDKey.SheetName,"Sheet1");
		xm.addPartition(JDFResource.EnumPartIDKey.SheetName,"Sheet2");
        xm1.setAmount(1.);
		xm.setAmount(2.);
		JDFAttributeMap map1=new JDFAttributeMap(JDFResource.EnumPartIDKey.SheetName.getName(),"Sheet1");
		JDFAttributeMap map2=new JDFAttributeMap(JDFResource.EnumPartIDKey.SheetName.getName(),"Sheet2");

		JDFResourceLink rl=n.getLink(xm,null);
		JDFPartAmount pa=rl.getCreateAmountPool().getCreatePartAmount(new JDFAttributeMap("SheetName","Sheet1"));
		pa.setDraftOK(true);
		assertTrue("draft",pa.getMinStatus()==JDFResource.EnumResStatus.Draft);
		assertTrue("has no draft",!pa.hasAttribute(AttributeName.DRAFTOK));
		assertTrue("late draft",pa.getMinLateStatus()==JDFResource.EnumResStatus.Draft);
		pa.removeAttribute(AttributeName.MINSTATUS);
		n.setVersion(JDFElement.EnumVersion.Version_1_2);
		pa.setDraftOK(true);
		assertTrue("draft",pa.getMinStatus()==JDFResource.EnumResStatus.Draft);
		assertTrue("has draft",pa.hasAttribute(AttributeName.DRAFTOK));
		assertTrue("has no MinStatus",!pa.hasAttribute(AttributeName.MINSTATUS));
		assertTrue("late draft",pa.getMinLateStatus()==JDFResource.EnumResStatus.Draft);

		rl.setMinAmount(42.,map1);
		assertTrue("map1min",rl.getMinAmount(map1)==42.);
		assertEquals("map1max",rl.getMaxAmount(map1),1.,0.);
        assertEquals("map2min",rl.getMinAmount(map2),2.,0.); // last default
        assertEquals("map0min",rl.getMaxAmount(null),2.,0.); // last default
        pa.setAmount(55,null);
        assertEquals("pa amount",pa.getAmount(null),55.,0.);

	}

    /**
     * tests whether the convoluted inheritence of partAmount and ResourceLink function correctly
     * @throws Exception
     */
    public void testPartAmountVirtual() throws Exception
    {

        JDFDoc d=new JDFDoc(ElementName.JDF);
        JDFNode n=d.getJDFRoot();
        n.setVersion(JDFElement.EnumVersion.Version_1_3);
        n.setType("ConventionalPrinting",true);
        JDFComponent comp=(JDFComponent)n.appendMatchingResource(ElementName.COMPONENT,EnumProcessUsage.AnyOutput,null);

        JDFResourceLink cpLink=n.getLink(comp,null);
        JDFAttributeMap mapBad=new JDFAttributeMap("Condition","Waste");
        cpLink.setActualAmount(42,mapBad);
        assertEquals(cpLink.getActualAmount(mapBad),42.,0);
        assertTrue("allow partamounts to non-existing partitions",n.isValid(EnumValidationLevel.Incomplete));

        cpLink.removeChild(ElementName.AMOUNTPOOL,null,0);
        comp.addPartition(EnumPartIDKey.SheetName,"Sheet1");
        mapBad.put(EnumPartIDKey.SheetName.getName(),"Sheet1");
        cpLink.setActualAmount(42,mapBad);
        assertEquals(cpLink.getActualAmount(mapBad),42.,0);
        assertTrue("allow partamounts to non-existing partitions",n.isValid(EnumValidationLevel.Incomplete));
        mapBad.put(EnumPartIDKey.SheetName.getName(),"Sheet2");
        assertEquals(cpLink.getActualAmount(mapBad),0.,0);

    }

    public void testSetAmountPoolAttribute() throws Exception
    {
        JDFDoc d=new JDFDoc("ResourceLinkPool");
        JDFResourceLinkPool rlp=(JDFResourceLinkPool)d.getRoot();
        JDFResourceLink foo=(JDFResourceLink) rlp.appendElement("FooLink");
        VJDFAttributeMap vPart=new VJDFAttributeMap();
        vPart.add(new JDFAttributeMap());
        foo.setAmountPoolAttribute("blub", "123", null, vPart );
        assertEquals(foo.getAttribute("blub"), "123");
        vPart=new VJDFAttributeMap();
        final JDFAttributeMap map = new JDFAttributeMap("SheetName","b");
        vPart.add(map);
        foo.setAmountPoolAttribute("blub", "123", null, vPart );
        assertNull(foo.getAttribute("blub",null,null));
        assertEquals(foo.getAmountPoolAttribute("blub", null, map, 0), "123");

    }
    /**
     * Method testGetLinkRoot.
     * @throws Exception
     */
    public void testGetLinkRootJMF() throws Exception
    {
        JDFDoc d=new JDFDoc("JMF");
        JDFJMF jmf=d.getJMFRoot();
        jmf.setSenderID("Elvis");
        JDFCommand c=jmf.appendCommand();
        c.setType("PipePull");
        JDFPipeParams pp=c.appendPipeParams();
        pp.setAttribute(AttributeName.PIPEID,"foo",null);
        JDFRunList ruli=(JDFRunList) pp.appendResource(ElementName.RUNLIST);
        JDFResourceLink rl=pp.appendResourceLink("RunListLink",true);
        rl.setrRef(ruli.getID());
        assertTrue("valid param",jmf.isValid(EnumValidationLevel.Complete));
        assertEquals(ruli,rl.getTarget());
    }
    
    /**
     * Method testGetTarget
     * @throws Exception
     */
    public void testGetTargetVector() throws Exception
    {
        JDFDoc d=JDFTestCaseBase.creatXMDoc();
        JDFNode n=d.getJDFRoot();
        JDFAttributeMap mPart=new JDFAttributeMap("SignatureName","Sig1");
        mPart.put("SheetName","S1");
        JDFResourceLink rl=n.getMatchingLink("ExposedMedia",EnumProcessUsage.Plate,0);
        rl.setPartMap(mPart);
        VElement v=rl.getTargetVector(0);
        assertEquals("The target vector is the node with two leaves",v.size(),1);

     }
    /**
     * Method testGetTarget
     * @throws Exception
     */
    public void testGetTargetVectorNullPart() throws Exception
    {
        JDFDoc d=JDFTestCaseBase.creatXMDoc();
        JDFNode n=d.getJDFRoot();
         JDFResourceLink rl=n.getMatchingLink("ExposedMedia",EnumProcessUsage.Plate,0);
        rl.appendPart();
        VElement v=rl.getTargetVector(0);
        assertEquals("The target vector is the node with two leaves",v.size(),1);
        final JDFResource linkRoot = rl.getLinkRoot();
        assertEquals("The target vector is the node with two leaves",v.elementAt(0), linkRoot);
        linkRoot.setPartUsage(EnumPartUsage.Implicit);
        v=rl.getTargetVector(0);
        assertEquals("The target vector is the node with two leaves",v.size(),1);
        assertEquals("The target vector is the node with two leaves",v.elementAt(0), linkRoot);

        linkRoot.setPartUsage(EnumPartUsage.Explicit);

        JDFAttributeMap mPart=new JDFAttributeMap("SignatureName","Sig1");
        mPart.put("SheetName","S1");
        rl.setPartMap(mPart);
        rl.appendPart();


        v=rl.getTargetVector(0);
        assertEquals("The target vector is the node with two leaves",v.size(),2);
        assertTrue(v.contains(linkRoot));
        assertTrue(v.contains(linkRoot.getPartition(mPart, null)));
        linkRoot.setPartUsage(EnumPartUsage.Implicit);
        v=rl.getTargetVector(0);
        assertTrue(v.contains(linkRoot));
        assertTrue(v.contains(linkRoot.getPartition(mPart, null)));
     }

    /**
     * Method testGetTarget
     * @throws Exception
     */
    public void testGetTarget() throws Exception
    {
        JDFDoc d=JDFTestCaseBase.creatXMDoc();
        JDFNode n=d.getJDFRoot();
        JDFExposedMedia xm=(JDFExposedMedia)n.getMatchingResource("ExposedMedia",JDFNode.EnumProcessUsage.AnyInput,null,0);
        JDFAttributeMap mPart=new JDFAttributeMap("SignatureName","Sig1");
        mPart.put("SignatureName","S12234");
        mPart.put("SheetName","S12");
        mPart.put("Side","Front");

        JDFAttributeMap mPart2=new JDFAttributeMap("SignatureName","Sig1");
        mPart2.put("SignatureName","Sig1");
        mPart2.put("SheetName","S1");
        mPart2.put("Side","Front");
        JDFExposedMedia xmPart=(JDFExposedMedia)xm.getPartition(mPart2,null);
        assertNotNull(xmPart);

        JDFResourceLink rl=n.getMatchingLink("ExposedMedia",EnumProcessUsage.Plate,0);

        rl.setPartMap(mPart);
        assertNull(rl.getTarget());
        assertEquals(rl.getTargetVector(0).size(),0);

        xm.setPartUsage(EnumPartUsage.Explicit);
        assertNull(rl.getTarget());
        assertEquals(rl.getTargetVector(0).size(),0);

        xm.setPartUsage(EnumPartUsage.Implicit);
        assertEquals(rl.getTarget(),xm);
        assertEquals(rl.getTargetVector(0).size(),1);

        xm.setPartUsage(EnumPartUsage.Sparse);
        assertEquals(rl.getTarget(),null);
        assertEquals(rl.getTargetVector(0).size(),0);

        rl.setPartMap(mPart2);
        xm.removeAttribute("PartUsage");
        assertEquals(xmPart, rl.getTarget());
        assertEquals(rl.getTargetVector(0).size(),1);

        xm.setPartUsage(EnumPartUsage.Explicit);
        assertEquals(xmPart, rl.getTarget());
        assertEquals(rl.getTargetVector(0).size(),1);

        xm.setPartUsage(EnumPartUsage.Implicit);
        assertEquals(rl.getTarget(),xmPart);
        assertEquals(rl.getTargetVector(0).size(),1);

        xm.setPartUsage(EnumPartUsage.Sparse);
        assertEquals(rl.getTarget(),xmPart);
        assertEquals(rl.getTargetVector(0).size(),1);

        mPart2.put("PartVersion", "Fnarf");
        rl.setPartMap(mPart2);
        xm.removeAttribute("PartUsage");
        assertEquals(null, rl.getTarget());
        assertEquals(rl.getTargetVector(0).size(),0);

        xm.setPartUsage(EnumPartUsage.Explicit);
        assertEquals(null, rl.getTarget());
        assertEquals(rl.getTargetVector(0).size(),0);

        xm.setPartUsage(EnumPartUsage.Implicit);
        assertEquals(rl.getTarget(),xmPart);
        assertEquals(rl.getTargetVector(0).size(),1);

        xm.setPartUsage(EnumPartUsage.Sparse);
        assertEquals(rl.getTarget(),xmPart);
        assertEquals(rl.getTargetVector(0).size(),1);
    }
    public void testGetCombinedProcessTypes() throws Exception
    {
        JDFDoc d=new JDFDoc(ElementName.JDF);
        JDFNode n=d.getJDFRoot();
        n.setType("Combined",true);
        n.setTypes(new VString("a b c d e f e f"," "));
        JDFResource r=n.addResource(ElementName.ADHESIVEBINDINGPARAMS, EnumUsage.Input);
        JDFResourceLink rl=n.getLink(r, null);
        VString nodeTypes=n.getTypes();
        nodeTypes.unify();
        assertEquals(rl.getCombinedProcessTypes(), nodeTypes);
        rl.setCombinedProcessType("c");
        assertEquals(rl.getCombinedProcessTypes(), new VString("c"," "));
        rl.removeAttribute(AttributeName.COMBINEDPROCESSTYPE);
        assertEquals(rl.getCombinedProcessTypes(), nodeTypes);
        rl.setCombinedProcessIndex(new JDFIntegerList("0 2 4 6"));
        assertEquals(rl.getCombinedProcessTypes(), new VString("a c e"," "));
        rl.setCombinedProcessIndex(new JDFIntegerList("0 2 4 6 8 99"));
        assertEquals(rl.getCombinedProcessTypes(), new VString("a c e"," "));
     }
    /**
	 * Method testGetLinkRootJMF
	 * @throws Exception
	 */
	public void testGetLinkRoot() throws Exception
	{

		JDFDoc d=new JDFDoc(ElementName.JDF);
		JDFNode n=d.getJDFRoot();
		n.setType("ProcessGroup",true);
		JDFNode n2=n.addJDFNode("ConventionalPrinting");
		JDFCustomerInfo ci=(JDFCustomerInfo)n.addResource(ElementName.CUSTOMERINFO, null, EnumUsage.Input, null, null, null, null);
		JDFResourceLink ciLink=n.getLink(ci,null);
		assertTrue("getLinkRoot in same node",ci==ciLink.getLinkRoot());
		assertTrue("getLinkTarget in same node",ci==ciLink.getTarget());
		assertTrue("getTarget in same node",ci==ciLink.getTarget());

		JDFResourceLink ciLink2=n2.linkResource(ci, EnumUsage.Input ,null);
		assertTrue("getLinkRoot in child node",ci==ciLink2.getLinkRoot());
		assertTrue("getLinkTarget in child node",ci==ciLink2.getTarget());
		assertTrue("getTarget in child node",ci==ciLink2.getTarget());

		JDFNodeInfo ni=(JDFNodeInfo)n2.addResource(ElementName.NODEINFO, null, null, null, null, null, null);
		JDFResourceLink niLink=n2.linkResource(ni,true ? EnumUsage.Input : EnumUsage.Output,null);
		assertTrue("getLinkRoot both in child node",ni==niLink.getLinkRoot());
		assertTrue("getLinkTarget both in child node",ni==niLink.getTarget());
		assertTrue("getTarget both in child node",ni==niLink.getTarget());

		JDFResourceLink niLink2=(JDFResourceLink)n.getCreateResourceLinkPool().appendElement("NodeInfoLink",null);
		niLink2.setrRef(ni.getID());
		assertTrue("getLinkRoot illegal in child node",niLink2.getLinkRoot()==null);
		assertTrue("getLinkTarget illegal in child node",niLink2.getTarget()==null);
		assertTrue("getTarget illegal in child node",niLink2.getTarget()==null);

        JDFDoc d22=new JDFDoc(ElementName.JDF);
        JDFNode n22=d22.getJDFRoot();
        JDFResourceLinkPool rlp=n22.getCreateResourceLinkPool();
        boolean bCaught=false;
        try
        {
            rlp.linkResource(ni, true?EnumUsage.Input:EnumUsage.Output,null);
        }
        catch (JDFException e)
        {
             bCaught=true;
        }
        assertTrue("Resource from other document not linked",bCaught);
        assertNull("NI not linked",rlp.getElement("NodeInfoLink"));

	}

	public void testLinkRootDeadLoop() throws Exception
	{
	    JDFDoc jdfDoc = new JDFDoc("JDF");
	    JDFNode node = jdfDoc.getJDFRoot();
	    JDFResource r=node.addResource(ElementName.ADHESIVEBINDINGPARAMS, EnumUsage.Input);
	    node.getResourcePool().insertBefore(ElementName.ADHESIVEBINDINGPARAMS+"Ref",r,null).setAttribute("rRef", "badLink");
	    final JDFResourceLink link =node.getLink(r, null);
	    assertNotNull(link.getLinkRoot ());    // Endlos-Schleife !!!!
	}

    /**
     * Method testIncludesMatchingAttribute.
     * @throws Exception
     */
    public void testSetPartMap() throws Exception
    {
        JDFDoc d=new JDFDoc(ElementName.JDF);
        JDFNode n=d.getJDFRoot();
        n.setVersion(JDFElement.EnumVersion.Version_1_3);
        n.setType("ConventionalPrinting",true);
        JDFExposedMedia xm=(JDFExposedMedia)n.addResource(ElementName.EXPOSEDMEDIA, null, EnumUsage.Input, null, null, null, null);
        xm.addPartition(JDFResource.EnumPartIDKey.SheetName,"Sheet1");
        xm.addPartition(JDFResource.EnumPartIDKey.SheetName,"Sheet2");
        JDFAttributeMap map1=new JDFAttributeMap(JDFResource.EnumPartIDKey.SheetName.getName(),"Sheet1");
        JDFAttributeMap map2=new JDFAttributeMap(JDFResource.EnumPartIDKey.SheetName.getName(),"Sheet2");
        JDFResourceLink rl=n.getLink(xm,null);

        VJDFAttributeMap v=new VJDFAttributeMap();
        v.add(map1);
        v.add(map2);
        rl.setPartMapVector(v);
    }

    public void testSetTarget() throws Exception
    {
        JDFDoc d=new JDFDoc(ElementName.JDF);
        JDFNode n=d.getJDFRoot();
        n.setVersion(JDFElement.EnumVersion.Version_1_3);
        n.setType("ConventionalPrinting",true);
        JDFExposedMedia xm=(JDFExposedMedia)n.addResource(ElementName.EXPOSEDMEDIA, null, EnumUsage.Input, null, null, null, null);
        JDFExposedMedia xm1=(JDFExposedMedia)xm.addPartition(JDFResource.EnumPartIDKey.SignatureName,"Sig1");
        VJDFAttributeMap vSig1=new VJDFAttributeMap();
        vSig1.add(xm1.getPartMap());
        // want a lower leaf partition
        xm1.addPartition(JDFResource.EnumPartIDKey.SheetName,"Sheet1");
        JDFMedia m=xm.appendMedia();
        JDFResourceLink rl=n.linkResource(xm, EnumUsage.Input, null);
        try
        {
            rl.setTarget(m);
            fail("no link to subelem");
        }
        catch(JDFException x)
        {
            // nop
        }
        rl.setTarget(xm1);
        assertEquals(rl.getPartMapVector(), vSig1);

    }
    /////////////////////////////////////////////////////////////////////
    /**
     * Method testIncludesMatchingAttribute.
     * @throws Exception
     */
    public void testGetUsage() throws Exception
    {
        JDFDoc d=new JDFDoc(ElementName.JDF);
        JDFNode n=d.getJDFRoot();
        JDFResourceLinkPool rlp=n.appendResourceLinkPool();
        JDFResourceLink rl=(JDFResourceLink) rlp.appendElement("FooLink");
        assertNull(rl.getUsage());
    }

    /**
     * Method testHasResourcePartMap.
     * @throws Exception
     */
    public void testHasResourcePartMap() throws Exception
    {
        JDFDoc d=new JDFDoc(ElementName.JDF);
        JDFNode n=d.getJDFRoot();
        JDFResource r=n.addResource(ElementName.SCREENINGINTENT, null, EnumUsage.Input, null, null, null, null);
        JDFResource rSig=r.addPartition(EnumPartIDKey.SignatureName, "sig1");
        JDFResourceLink rl=n.getLink(r, null);
        
        // the root always exists
        assertTrue(rl.hasResourcePartMap(null,false));
        assertTrue(rl.hasResourcePartMap(null,true));
        
        // link and resource match
        rl.setPart(EnumPartIDKey.SignatureName.getName(), "sig1");
        assertTrue(rl.hasResourcePartMap(null,false));
        assertTrue(rl.hasResourcePartMap(null,true));
        
        // resource is partitioned deeper than link
        rSig.addPartition(EnumPartIDKey.SheetName, "sh1");
        assertTrue(rl.hasResourcePartMap(null,false));
        final JDFAttributeMap attributeMap = new JDFAttributeMap(EnumPartIDKey.SignatureName, "sig1");
        assertTrue(rl.hasResourcePartMap(attributeMap,false));
        
        attributeMap.put(EnumPartIDKey.SheetName, "sh1");
        assertTrue(rl.hasResourcePartMap(attributeMap,true));
        assertFalse(rl.hasResourcePartMap(attributeMap,false));
        
        attributeMap.put(EnumPartIDKey.Side, "Front");
        assertFalse(rl.hasResourcePartMap(attributeMap,true));
        assertFalse(rl.hasResourcePartMap(attributeMap,false));

        assertFalse(rl.hasResourcePartMap(new JDFAttributeMap(EnumPartIDKey.SignatureName, "sig2"),false));
        assertFalse(rl.hasResourcePartMap(new JDFAttributeMap(EnumPartIDKey.SignatureName, "sig2"),true));

        r.setPartUsage(EnumPartUsage.Implicit);
        assertTrue(rl.hasResourcePartMap(attributeMap,true));
        assertTrue(rl.hasResourcePartMap(attributeMap,false));
      
        assertFalse(rl.hasResourcePartMap(new JDFAttributeMap(EnumPartIDKey.SignatureName, "sig2"),false));
        assertFalse(rl.hasResourcePartMap(new JDFAttributeMap(EnumPartIDKey.SignatureName, "sig2"),true));
   }
    
    
    /**
     * Method testIsExecutable().
     * @throws Exception
     */
    public void testIsExecutable() throws Exception
    {
        JDFDoc d=new JDFDoc(ElementName.JDF);
        JDFNode n=d.getJDFRoot();
        JDFResource r=n.addResource(ElementName.SCREENINGINTENT, null, EnumUsage.Input, null, null, null, null);
        JDFResourceLink rl=n.getLink(r, null);

        r.setResStatus(EnumResStatus.Available, true);
        assertTrue(rl.isExecutable(null, true));
        r.setResStatus(EnumResStatus.Unavailable, true);
        assertFalse(rl.isExecutable(null, true));
        r.setResStatus(EnumResStatus.Draft, true);
        assertFalse(rl.isExecutable(null, true));
        rl.setDraftOK(true);
        assertTrue(rl.isExecutable(null, true));

        rl.setUsage(EnumUsage.Output);
        r.setResStatus(EnumResStatus.Available, true);
        assertTrue(rl.isExecutable(null, true));
        r.setResStatus(EnumResStatus.Unavailable, true);
        assertFalse(rl.isExecutable(null, true));
        r.setResStatus(EnumResStatus.Draft, true);
        assertTrue(rl.isExecutable(null, true));
        rl.setDraftOK(true);
        assertTrue(rl.isExecutable(null, true));

        JDFResource rSig=r.addPartition(EnumPartIDKey.SignatureName, "sig1");
        JDFResource rSheet=rSig.addPartition(EnumPartIDKey.SheetName, "sh1");
        rSheet.setResStatus(EnumResStatus.Available, false);
        r.setResStatus(EnumResStatus.Unavailable, true);
        rSheet.setResStatus(EnumResStatus.Available, true);
        rl.setUsage(EnumUsage.Input);
        JDFAttributeMap map=new JDFAttributeMap(EnumPartIDKey.SignatureName, "sig1");
        assertFalse(rl.isExecutable(map, false));
        assertTrue(rl.isExecutable(map, true));
        JDFResource rSheet2=rSig.addPartition(EnumPartIDKey.SheetName, "sh2");
        rSheet2.setResStatus(EnumResStatus.Unavailable, false);
        assertFalse(rl.isExecutable(map, false));
        assertFalse(rl.isExecutable(map, true));
        map.put(EnumPartIDKey.SheetName, "sh1");
        assertTrue(rl.isExecutable(map, false));
        assertTrue(rl.isExecutable(map, true));
        rl.appendPart().setPartMap(map);
        map.put(EnumPartIDKey.SheetName, "sh2");
        rl.appendPart().setPartMap(map);
        map=new JDFAttributeMap(EnumPartIDKey.SignatureName, "sig1");
        rSheet2.setResStatus(EnumResStatus.Available, false);

        assertTrue(rl.isExecutable(map, false));
        assertTrue(rl.isExecutable(map, true));

    }
    /////////////////////////////////////////////////////////////////////
    /**
     * Method test if the node name matching works.
      */
    public void testValidName()
    {
        JDFDoc d=new JDFDoc(ElementName.JDF);
        JDFNode n=d.getJDFRoot();
         JDFResourceLinkPool rlp=n.appendResourceLinkPool();
        JDFResourceLink rl=(JDFResourceLink) rlp.appendElement("FooLink");
        JDFResource rBar=n.addResource("Bar", EnumResourceClass.Parameter, EnumUsage.Input, null, null, null, null);
        rl.setrRef(rBar.getID());
        assertFalse((rl.isValid(null)));
        rl=n.getLink(rBar,null);
        assertNotNull(rl);
        assertTrue((rl.isValid(null)));
    }
    /////////////////////////////////////////////////////////////////////
    /**
     * test that the position checking algorithm works
     *
     */
    public void testValidPosition()
    {
        JDFDoc d=new JDFDoc(ElementName.JDF);
        JDFNode n=d.getJDFRoot();
        n.setType(EnumType.ProcessGroup);
        JDFResource rBar=n.addResource("Bar", EnumResourceClass.Parameter, EnumUsage.Input, null, null, null, null);
        JDFResourceLink rl=n.getLink(rBar,null);
        assertNotNull(rl);
        assertTrue((rl.isValid(null)));
        JDFNode n2=n.addJDFNode("Whatever");
        n2.moveElement(n.getResourcePool(),null);
        assertFalse((rl.isValid(null)));
        assertFalse((rl.validResourcePosition()));
    }

    /////////////////////////////////////////////////////////////////////
    /**
     * test that the position checking algorithm works
     *
     */
    public void testValidCombinedProcessIndex() throws Exception
    {
        JDFDoc d=new JDFDoc(ElementName.JDF);
        JDFNode n=d.getJDFRoot();
        n.setType(EnumType.Strapping);
        JDFResource rBar=n.addResource("Bar", EnumResourceClass.Parameter, EnumUsage.Input, null, null, null, null);
        JDFResourceLink rl=n.getLink(rBar,null);
        assertTrue(rl.validCombinedProcessIndex());
        rl.setCombinedProcessIndex(null);
        assertTrue(rl.validCombinedProcessIndex());
        JDFIntegerList il=new JDFIntegerList();
        rl.setCombinedProcessIndex(il);
        assertTrue(rl.validCombinedProcessIndex());
        il.add(0);
        rl.setCombinedProcessIndex(il);
        assertFalse(rl.validCombinedProcessIndex());
        n.setCombined(new VString("Approval ImageSetting"," "));
        assertTrue(rl.validCombinedProcessIndex());
        il.add(1);
        rl.setCombinedProcessIndex(il);
        assertTrue(rl.validCombinedProcessIndex());
        il.add(1);
        rl.setCombinedProcessIndex(il);
        assertTrue(rl.validCombinedProcessIndex());
        assertTrue(n.isValid(EnumValidationLevel.Incomplete));
        il.add(2);
        rl.setCombinedProcessIndex(il);
        assertFalse(rl.validCombinedProcessIndex());
        assertFalse(n.isValid(EnumValidationLevel.Incomplete));
        assertTrue(rl.getInvalidAttributes(EnumValidationLevel.Incomplete, true, -1).contains(AttributeName.COMBINEDPROCESSINDEX));

    }    /////////////////////////////////////////////////////////////////////
    /**
     * test that the position checking algorithm works
     *
     */
    public void testSetCombinedProcessIndex() throws Exception
    {
        JDFDoc d=new JDFDoc(ElementName.JDF);
        JDFNode n=d.getJDFRoot();
        n.setType(EnumType.Strapping);
        JDFResource rBar=n.addResource("Bar", EnumResourceClass.Parameter, EnumUsage.Input, null, null, null, null);
        JDFResourceLink rl=n.getLink(rBar,null);
        rl.setCombinedProcessIndex(null);
        assertFalse(rl.hasAttribute(AttributeName.COMBINEDPROCESSINDEX));
        JDFIntegerList il=new JDFIntegerList();
        rl.setCombinedProcessIndex(il);
        assertFalse(rl.hasAttribute(AttributeName.COMBINEDPROCESSINDEX));
        il.add(0);
        rl.setCombinedProcessIndex(il);
        assertEquals(rl.getCombinedProcessIndex(),il);
    }

}