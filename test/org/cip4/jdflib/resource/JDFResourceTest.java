/*
 * The CIP4 Software License, Version 1.0
 *
 *
 * Copyright (c) 2001-2005 The International Cooperation for the Integration of
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
 * JDFResourceTest.java
 * 
 * @author Dietrich Mucha
 *
 * Copyright (C) 2002 Heidelberger Druckmaschinen AG. All Rights Reserved.
 */
package org.cip4.jdflib.resource;

import java.util.Vector;

import org.apache.xerces.dom.CoreDocumentImpl;
import org.cip4.jdflib.JDFTestCaseBase;
import org.cip4.jdflib.auto.JDFAutoColorSpaceConversionOp.EnumSourceObjects;
import org.cip4.jdflib.auto.JDFAutoPart.EnumPreviewType;
import org.cip4.jdflib.core.AttributeName;
import org.cip4.jdflib.core.ElementName;
import org.cip4.jdflib.core.JDFAudit;
import org.cip4.jdflib.core.JDFConstants;
import org.cip4.jdflib.core.JDFDoc;
import org.cip4.jdflib.core.JDFElement;
import org.cip4.jdflib.core.JDFException;
import org.cip4.jdflib.core.JDFNodeInfo;
import org.cip4.jdflib.core.JDFParser;
import org.cip4.jdflib.core.JDFRefElement;
import org.cip4.jdflib.core.JDFResourceLink;
import org.cip4.jdflib.core.KElement;
import org.cip4.jdflib.core.VElement;
import org.cip4.jdflib.core.VString;
import org.cip4.jdflib.core.XMLDoc;
import org.cip4.jdflib.core.JDFElement.EnumVersion;
import org.cip4.jdflib.core.JDFResourceLink.EnumUsage;
import org.cip4.jdflib.core.KElement.EnumValidationLevel;
import org.cip4.jdflib.datatypes.JDFAttributeMap;
import org.cip4.jdflib.datatypes.JDFXYPair;
import org.cip4.jdflib.datatypes.VJDFAttributeMap;
import org.cip4.jdflib.node.JDFNode;
import org.cip4.jdflib.node.JDFNode.EnumProcessUsage;
import org.cip4.jdflib.node.JDFNode.EnumType;
import org.cip4.jdflib.pool.JDFResourcePool;
import org.cip4.jdflib.resource.JDFResource.EnumPartIDKey;
import org.cip4.jdflib.resource.JDFResource.EnumPartUsage;
import org.cip4.jdflib.resource.JDFResource.EnumResStatus;
import org.cip4.jdflib.resource.JDFResource.EnumResourceClass;
import org.cip4.jdflib.resource.process.JDFBinderySignature;
import org.cip4.jdflib.resource.process.JDFColorPool;
import org.cip4.jdflib.resource.process.JDFColorantControl;
import org.cip4.jdflib.resource.process.JDFComponent;
import org.cip4.jdflib.resource.process.JDFCutBlock;
import org.cip4.jdflib.resource.process.JDFDigitalPrintingParams;
import org.cip4.jdflib.resource.process.JDFExposedMedia;
import org.cip4.jdflib.resource.process.JDFFileSpec;
import org.cip4.jdflib.resource.process.JDFGeneralID;
import org.cip4.jdflib.resource.process.JDFLayoutElement;
import org.cip4.jdflib.resource.process.JDFMedia;
import org.cip4.jdflib.resource.process.JDFPreview;
import org.cip4.jdflib.resource.process.JDFRunList;
import org.cip4.jdflib.resource.process.JDFSeparationSpec;
import org.cip4.jdflib.resource.process.JDFStripCellParams;
import org.cip4.jdflib.resource.process.prepress.JDFColorSpaceConversionOp;
import org.cip4.jdflib.resource.process.prepress.JDFColorSpaceConversionParams;
import org.cip4.jdflib.util.StringUtil;

public class JDFResourceTest extends JDFTestCaseBase
{

    
    public void testGetCreator()
    {
        JDFDoc doc=creatXMDoc();
        JDFNode n=doc.getJDFRoot();
        JDFExposedMedia xm=(JDFExposedMedia)n.getMatchingResource("ExposedMedia",JDFNode.EnumProcessUsage.AnyInput,null,0);
        assertTrue(xm.getCreator(false).contains(n));
        
    }    
    public void testGetAttributeVector()
    {
        JDFDoc doc=creatXMDoc();
        JDFNode n=doc.getJDFRoot();
        JDFExposedMedia xm=(JDFExposedMedia)n.getMatchingResource("ExposedMedia",JDFNode.EnumProcessUsage.AnyInput,null,0);
        JDFAttributeMap mPart=new JDFAttributeMap("SignatureName","Sig1");
        mPart.put("SheetName","S1");
        mPart.put("Side","Front");       
        JDFExposedMedia xmPart=(JDFExposedMedia)xm.getPartition(mPart,null);
        xmPart.setAgentName("agent");
        xmPart.setAttribute("foo:bar","foobar","www.foo.com");
        VString partVector=xmPart.getAttributeVector();
        assertTrue("contains inherited attributes",partVector.contains("ID"));
        assertTrue("contains inherited attributes",partVector.contains("SignatureName"));
        assertTrue("contains inherited attributes",partVector.contains("SheetName"));
        assertTrue("contains inherited attributes",partVector.contains("Side"));
        assertTrue("contains inherited attributes",partVector.contains("AgentName"));
        assertTrue("contains inherited attributes",partVector.contains("foo:bar"));
    }   
    
    /**
     * test the the generalized partition matching
     *
     */
    public void testGetAttributeMap()
    {
          JDFDoc doc = new JDFDoc(ElementName.JDF);
        JDFNode root = doc.getJDFRoot();
        root.setType(JDFNode.EnumType.ConventionalPrinting.getName(),true);
        JDFExposedMedia xm=(JDFExposedMedia)root.appendMatchingResource(ElementName.EXPOSEDMEDIA,JDFNode.EnumProcessUsage.AnyInput,null);
        xm.setResolution(new JDFXYPair(300,300));
        xm.setPolarity(true);
        xm.setProofType(JDFExposedMedia.EnumProofType.Page);
 
        JDFMedia m=xm.appendMedia();
        m.setDimension(new JDFXYPair(200,300));
        JDFExposedMedia xm2=(JDFExposedMedia) xm.addPartition(EnumPartIDKey.SheetName,"S1");
        final JDFAttributeMap xm2Map = xm2.getAttributeMap();
        xm2Map.remove(EnumPartIDKey.SheetName);
        assertEquals(xm.getAttributeMap(), xm2Map);
        xm.setAttribute("foo:bar","foobar","www.foo.com");
        JDFAttributeMap am=xm.getAttributeMap();
        assertEquals(am.get("foo:bar"), "foobar");
        am=xm2.getAttributeMap();
        assertEquals(am.get("foo:bar"), "foobar");
       
    }


    /**
     * test the the generalized partition matching
     *
     */
    public void testOverlapPartMap()
    {
        JDFAttributeMap m1=new JDFAttributeMap("PartVersion","DE EN");
        m1.put("Run","r2");
        JDFAttributeMap m2=new JDFAttributeMap("PartVersion","DE");
        assertTrue(JDFPart.overlapPartMap(m1,m2));
        m2.put("Run","r2");
        assertTrue(JDFPart.overlapPartMap(m1,m2));
    }
    /**
     * test the the generalized partition matching
     *
     */
    public void testInit()
    {
        JDFAudit.setStaticAgentName(JDFAudit.software());
        JDFAudit.setStaticAgentVersion(JDFAudit.getDefaultJDFVersion().getName());
        JDFDoc doc=creatXMDoc();
        JDFNode n=doc.getJDFRoot();
        JDFExposedMedia xm=(JDFExposedMedia)n.getMatchingResource("ExposedMedia",JDFNode.EnumProcessUsage.AnyInput,null,0);
        assertTrue(xm.hasAttribute(AttributeName.AGENTNAME)); 
        assertTrue(xm.hasAttribute(AttributeName.AGENTVERSION)); 
        JDFAudit.setStaticAgentName("foo");
        xm.init();
        assertEquals(xm.getAgentName(),"foo");
    }

    /**
     * test the the generalized partition matching
     *
     */
    public void testMatchesPart()
    {
        assertTrue(JDFPart.matchesPart("PartVersion","DE EN FR","DE EN"));
        assertTrue(JDFPart.matchesPart("RunIndex","1 ~ 4","2 3"));
        assertTrue(JDFPart.matchesPart("RunIndex","1 ~ 3 5 ~ 6","3 5"));
        assertFalse(JDFPart.matchesPart("RunIndex","1 ~ 3 6 ~ 8","3 ~ 6"));
        assertTrue(JDFPart.matchesPart("PartVersion","DE EN","DE"));
        assertFalse(JDFPart.matchesPart("PartVersion","DE EN","DEU"));
        assertTrue(JDFPart.matchesPart("Run","R1","R1"));
        assertFalse(JDFPart.matchesPart("Run","R1 R2","R1"));
        assertFalse(JDFPart.matchesPart("Run","R2","R1"));
        assertFalse(JDFPart.matchesPart("RunIndex","1 ~ 4","5"));
    }

    /**
     * test the resource root stuff
     *
     */
    public void testGetResourcePoolNS()
    {
        // set up a test document
        JDFDoc jdfDoc = new JDFDoc("JDF");
        JDFNode root = jdfDoc.getJDFRoot();
        root.appendElement("foo:elem","www.foo.com");
        JDFResourcePool rp=root.appendResourcePool();
        JDFResource r=rp.appendResource("foo:res",EnumResourceClass.Parameter,"www.foo.com");
        JDFResource r2=(JDFResource)rp.appendElement("foo:res","www.foo.com");
        rp.appendElement("foo:elem","www.foo.com");
    }
    /**
     * test the resource root stuff
     *
     */
    public void testGetResourceRoot()
    {
        // set up a test document
        XMLDoc jdfDoc = new XMLDoc(ElementName.COLORPOOL,JDFElement.getSchemaURL());
        JDFElement root = (JDFElement)jdfDoc.getRoot();
        JDFResource resource;
        JDFResource resRoot;

        // !(parentName != null && !parentName.equals(JDFConstants.EMPTYSTRING)
        resRoot = ((JDFResource) root).getResourceRoot();
        assertTrue(resRoot == null);

        resource = (JDFResource) root.appendElement(ElementName.COLOR);

        // isResourceStatic((JDFElement) parentNode)
        // Rekursion : !(parentName != null && !parentName.equals(JDFConstants.EMPTYSTRING)
        resRoot = resource.getResourceRoot();
        assertTrue(resRoot == null);



        // set up a test document
        jdfDoc = new JDFDoc(ElementName.RESOURCEPOOL);
        root = (JDFElement)jdfDoc.getRoot();

        resource = (JDFResource) root.appendElement(ElementName.COLOR);

        // StringUtil.hasToken(validParentNodeNames(), parentName, JDFConstants.COMMA)
        resRoot = resource.getResourceRoot();
        assertTrue(resRoot == resource);



        // set up a test document
        JDFDoc jdfDoc2 = new JDFDoc(ElementName.JDF);
        root = jdfDoc2.getJDFRoot();

        resource = (JDFResource) root.appendElement(ElementName.COLOR);
        JDFResource elem = (JDFResource) root.appendElement(ElementName.NODEINFO);

        try
        {
            // "JDFResource.getResourceRoot ran into the JDF node while searching"
            resRoot = resource.getResourceRoot();
            fail("JDFResource.getResourceRoot ran into the JDF node while searching");
        }
        catch(JDFException e)
        {
            // this was expected
        }

        // localName.equals(ElementName.NODEINFO) || localName.equals(ElementName.CUSTOMERINFO)
        resRoot = elem.getResourceRoot();
        assertTrue(resRoot == elem);

        JDFNode rootNode=(JDFNode) root;
        JDFExposedMedia xm=(JDFExposedMedia) rootNode.addResource("ExposedMedia", null, EnumUsage.Input, null, null, null, null);
        JDFMedia m=xm.appendMedia();
        assertEquals("xm",xm.getResourceRoot(),xm);
        assertEquals("m",m.getResourceRoot(),xm);

        JDFColorantControl cc=(JDFColorantControl) rootNode.addResource("ColorantControl", null, EnumUsage.Input, null, null, null, null);
        JDFSeparationSpec ss=cc.appendColorantParams().appendSeparationSpec();
    }


    public void testIsResourceElement()
    {
        // get the JDF document root element
        JDFDoc jdfDoc = new JDFDoc(ElementName.JDF);
        JDFNode root  = (JDFNode) jdfDoc.getRoot();

        JDFNode trappingNode = 
            new JDFNode((CoreDocumentImpl) root.getOwnerDocument(), 
                    root.getNamespaceURI(), 
                    root.getNodeName());

        trappingNode.setType(JDFNode.EnumType.Trapping.getName(), false);

        // Add an intent resource
        JDFRunList runList = (JDFRunList)
        trappingNode.appendMatchingResource(ElementName.RUNLIST, JDFNode.EnumProcessUsage.AnyInput, null);

        JDFLayoutElement layoutElem = runList.appendLayoutElement();

        assertFalse (runList.isResourceElement());
        assertTrue  (layoutElem.isResourceElement());
    }

    public void testgetPartMap() 
    {
        String strFileName = sm_dirTestData+"partitioned_private_resources.jdf";
        JDFParser p = new JDFParser();

        JDFDoc myJDFDoc             = p.parseFile(strFileName);
        JDFNode myRoot              = myJDFDoc.getJDFRoot();
        JDFResourcePool myResPool   = myRoot.getResourcePool();
        JDFResource myPreview       = (JDFResource)myResPool.getElement("Preview", "", 0);
        JDFAttributeMap m           = new JDFAttributeMap();

//      m.put("SheetName", "Sheet 1");
        m.put("Side", "Front");
        m.put("Separation", "Black");
        m.put("PreviewType" , "Separation");

        myPreview = myPreview.getDeepPart(m, EnumPartUsage.Explicit);
        VElement vRes = myPreview.getLeaves(false);

        for (int i = 0; i < vRes.size(); i++)
        {
            JDFAttributeMap myMap = ((JDFResource) vRes.elementAt(i)).getPartMap();  
            if ("Black".equals(myMap.get("Separation")))
            {
                assertTrue(myMap.get("Side").equals("Front"));
                assertTrue(myMap.get("PreviewType").equals("Separation"));
                assertTrue(myMap.get("SheetName").startsWith("Sheet ")); // "Sheet 1" or "Sheet 2"
                assertTrue(myMap.get("Separation").equals("Black"));
            }
        }
    }

///////////////////////////////////////////////////////////////

    public void testgetPartValues()
    {
        String strFileName = sm_dirTestData+"partitioned_private_resources.jdf";
        JDFParser p = new JDFParser();

        JDFDoc myJDFDoc             = p.parseFile(strFileName);
        JDFNode myRoot              = myJDFDoc.getJDFRoot();
        JDFResourcePool myResPool   = myRoot.getResourcePool();
        JDFResource myPreview       = (JDFResource) myResPool.getElement("Preview", "", 0);
        JDFAttributeMap m           = new JDFAttributeMap();

//      m.put("Side", "Front");
        m.put("PreviewType", "Separation");
//      m.put("Separation" , "Black");

        myPreview = myPreview.getDeepPart(m, EnumPartUsage.Explicit);
        Vector vPartValues = myPreview.getPartValues(JDFResource.EnumPartIDKey.Separation);

        assertTrue( ((String)vPartValues.elementAt(0)).equals("Cyan") );
        assertTrue( ((String)vPartValues.elementAt(1)).equals("Magenta") );
        assertTrue( ((String)vPartValues.elementAt(2)).equals("Yellow") );
        assertTrue( ((String)vPartValues.elementAt(3)).equals("Black") );
    }

    public void testgetPartMapVector()
    {
        String strFileName = sm_dirTestData+"partitioned_private_resources.jdf";
        JDFParser p = new JDFParser();

        JDFDoc myJDFDoc = p.parseFile(strFileName);
        JDFNode myRoot = myJDFDoc.getJDFRoot();
        JDFResourcePool myResPool = myRoot.getResourcePool();
        JDFResource myPreview =
            (JDFResource) myResPool.getElement("Preview", "", 0);

        VJDFAttributeMap vJDFAttrMap = myPreview.getPartMapVector(false);

        //there must be 12 maps in the map vector
        assertTrue(vJDFAttrMap.size() == 12);

        for (int i = 0; i < vJDFAttrMap.size(); i++) 
        {
            JDFAttributeMap myMap = vJDFAttrMap.elementAt(i);

            assertTrue(myMap.containsKey("Side"));
            assertTrue(myMap.containsKey("PreviewType"));
            assertTrue(myMap.containsKey("SheetName"));
        }
    }

    public void testGetColorPool()
    {
        JDFDoc doc = new JDFDoc(ElementName.JDF);
        JDFNode root = doc.getJDFRoot();

        JDFResourcePool resPool = root.appendResourcePool();
        JDFColorantControl colControl = (JDFColorantControl) 
        resPool.appendElement(ElementName.COLORANTCONTROL, null);          
        colControl.setProcessColorModel("DeviceCMY");
        colControl.appendColorantParams().appendSeparationSpec().setName("Spot");

        JDFColorSpaceConversionParams cpp=(JDFColorSpaceConversionParams) root.addResource(ElementName.COLORSPACECONVERSIONPARAMS, null, EnumUsage.Input, null, null, null, null);
        JDFColorSpaceConversionOp cso=cpp.appendColorSpaceConversionOp();
        Vector sourceObjects=new Vector();
        sourceObjects.add(EnumSourceObjects.ImagePhotographic);
        sourceObjects.add(EnumSourceObjects.LineArt);
        cso.setSourceObjects(sourceObjects);


        // getseparations
        VString vSeps=colControl.getSeparations();
        assertTrue("seps 4",vSeps.size()==4);
        assertTrue("seps Cyan",vSeps.contains("Cyan"));
        assertTrue("seps Spot",vSeps.contains("Spot"));
        assertTrue("seps no black",!vSeps.contains("Black"));


        JDFColorPool colorPool = (JDFColorPool) 
        resPool.appendElement(ElementName.COLORPOOL, null);

        colControl.refElement(colorPool);
        // now colControl contains the ref element colPoolRef

        JDFColorPool colPool2 = colControl.getColorPool();

        // assert that we get the true color pool and not the ref element ...
        assertTrue(colorPool.equals(colPool2));
    }

    /**
     * tests the correct inheritence of refelements, elements and attributes
     *
     */
    public void testPartitionedAttribute()
    {
        JDFDoc doc = new JDFDoc(ElementName.JDF);
        JDFNode root = doc.getJDFRoot();
        root.setType(JDFNode.EnumType.ConventionalPrinting.getName(),true);
        JDFExposedMedia xm=(JDFExposedMedia)root.appendMatchingResource(ElementName.EXPOSEDMEDIA,JDFNode.EnumProcessUsage.AnyInput,null);
        xm.setResolution(new JDFXYPair(300,300));
        xm.setPolarity(true);
        xm.setProofType(JDFExposedMedia.EnumProofType.Page);
        JDFMedia m=xm.appendMedia();
        m.setDimension(new JDFXYPair(200,300));
        JDFExposedMedia xm2=(JDFExposedMedia) xm.addPartition(EnumPartIDKey.SheetName,"S1");
        assertTrue("Media inline Dimension",xm2.getMedia().getDimension().getX()==200.);
        assertTrue("ExposedMedia direct Resolution",xm.getResolution().getX()==300.);
        assertTrue("ExposedMedia inherited Resolution",xm2.getResolution().getX()==300.);
        m=(JDFMedia)m.makeRootResource(null,null,true);
        assertTrue("Media referenced Dimension",xm2.getMedia().getDimension().getX()==200.);
        JDFRefElement re = (JDFRefElement)xm2.getElement("MediaRef");
        assertTrue("refElement found",re!=null);

        if (re!=null)
        {
            JDFMedia m2=(JDFMedia) re.getTarget();
            assertEquals("ref target",m,m2);
        }
        assertTrue("has Media",xm2.hasChildElement("Media",null));
        assertTrue("polarity true",xm2.getPolarity());
        xm2.setPolarity(false);
        assertFalse("polarity false",xm2.getPolarity());

        assertEquals("prooftype",xm2.getProofType(),JDFExposedMedia.EnumProofType.Page);
    }

    /**
     * tests the correct inheritence of refelements, elements and attributes
     *
     */
    public void testpartitionedElement()
    {
        JDFDoc doc = new JDFDoc(ElementName.JDF);
        JDFNode root = doc.getJDFRoot();
        root.setType(JDFNode.EnumType.ConventionalPrinting.getName(),true);
        JDFColorantControl cc=(JDFColorantControl)root.appendMatchingResource(ElementName.COLORANTCONTROL,JDFNode.EnumProcessUsage.AnyInput,null);
        cc.setPartUsage(EnumPartUsage.Implicit);
        VString seps=StringUtil.tokenize("Cyan Magenta Yellow Black"," ",false);
        cc.appendColorantOrder().setSeparations(seps);
        JDFColorantControl cc2=(JDFColorantControl) cc.addPartition(EnumPartIDKey.SheetName,"S1");
        seps.add("smurf blue");
        cc2.appendColorantOrder().setSeparations(seps);
        JDFColorantControl select=(JDFColorantControl)cc.getPartition(new JDFAttributeMap(EnumPartIDKey.SheetName.getName(),"s0"),null);
        assertEquals(cc,select);
        select=(JDFColorantControl)cc.getPartition(new JDFAttributeMap(EnumPartIDKey.SheetName.getName(),"S1"),null);
        assertEquals(cc2,select);
        seps.add("dragon red");
        cc.appendDeviceColorantOrder().setSeparations(seps);
        assertTrue(select.getColorantOrder().getSeparations().contains("smurf blue"));
        assertTrue(select.getDeviceColorantOrder().getSeparations().contains("dragon red"));        
    }    


    /////////////////////////////////////////////////////////////
    public void testCreatePartitions()
    {
        JDFDoc doc = new JDFDoc(ElementName.JDF);
        JDFElement root = doc.getJDFRoot();
        JDFResourcePool resPool = (JDFResourcePool)root.appendElement(ElementName.RESOURCEPOOL, null);
        JDFResource resPreview = resPool.appendResource("Preview", null,null);
        JDFAttributeMap partMap = new JDFAttributeMap();
        partMap.put(EnumPartIDKey.SignatureName, "Sig1");
        partMap.put(EnumPartIDKey.SheetName, "Sheet1");
        partMap.put(EnumPartIDKey.PartVersion, "De");
        JDFAttributeMap partMap2 = new JDFAttributeMap(partMap);
        partMap2.put(EnumPartIDKey.PartVersion, "En");
        VJDFAttributeMap v=new VJDFAttributeMap();
        v.add(partMap);
        v.add(partMap2);
        VString vs=new VString("SignatureName SheetName PartVersion"," ");
        VString vs2=new VString("PartVersion SignatureName SheetName"," ");

        VElement parts=resPreview.createPartitions(v, vs);
        assertEquals(parts.size(), 2);

        VElement parts2=resPreview.createPartitions(v, vs2);
        assertEquals(parts, parts2);

        // next test
        resPreview.deleteNode();
        resPreview = resPool.appendResource("Preview", null,null);

        vs=new VString("SignatureName SheetName"," ");
        VJDFAttributeMap vShort=new VJDFAttributeMap();

        JDFAttributeMap partMapShort = new JDFAttributeMap();
        partMapShort.put(EnumPartIDKey.SignatureName, "Sig1");
        partMapShort.put(EnumPartIDKey.SheetName, "Sheet1");
        vShort.add(partMapShort);

        parts=resPreview.createPartitions(vShort, vs);
        assertEquals(parts.size(), 1);

        parts2=resPreview.createPartitions(v, vs2);
        assertEquals("add partVersion at end anyhow",parts2.size(),2);

    }

    /////////////////////////////////////////////////////////////
    public void testGetCreatePartition()
    {
        JDFDoc doc = new JDFDoc(ElementName.JDF);
        JDFElement root = doc.getJDFRoot();
        JDFResourcePool resPool = (JDFResourcePool)root.appendElement(ElementName.RESOURCEPOOL, null);
        JDFResource resPreview = resPool.appendResource("Preview", JDFResource.EnumResourceClass.Parameter, JDFConstants.EMPTYSTRING);
        JDFAttributeMap partMap = new JDFAttributeMap();

        //create 2 PreviewType Partition
        partMap.put("PreviewType", "ThumbNail");
        JDFResource resPreviewType = resPreview.getCreatePartition(partMap, null);
        partMap.clear();
        partMap.put("PreviewType", "Viewable");
        resPreview.getCreatePartition(partMap, null);

        //create a partition with same name under the existing one (MUST FAIL)
        try
        {
            partMap.clear();
            partMap.put("PreviewType", "SeparatedThumbNail");
            resPreviewType.getCreatePartition(partMap, null);
            fail("create a partition with same name");
        }
        catch(JDFException jdfe)
        {
            //do nothing
        }

        //try to create the partition directly 
        //because we create a "SheetName" partition under the "PreviewType="Thumbnail"
        //we dont need to add PreviewType to the partmap
        partMap.clear();
        partMap.put("SheetName", "Cover");
        resPreviewType.getCreatePartition(partMap, null);

        //create subpartition under Preview Viewable 
        partMap.clear();
        partMap.put("PreviewType", "Viewable");
        partMap.put("SheetName", "Cover");
        resPreview.getCreatePartition(partMap, null);

        //create two new partitions in one (MUST FAIL) 
        try
        {
            partMap.clear();
            partMap.put("PreviewType", "Viewable");
            partMap.put("SheetName", "Cover");
            partMap.put("Side", "Front");
            partMap.put("WebName", "0001");
            resPreview.getCreatePartition(partMap, null);
            //failed
            assertTrue(false);
        }
        catch(JDFException jdfe)
        {
            //do nothing
        }

        //same as above but this we use the second parameter to create a structure of partitions
        VString struct = new VString();
        struct.add("PreviewType");
        struct.add("SheetName");
        struct.add("Side");
        struct.add("WebName");

        partMap.clear();
        partMap.put("PreviewType", "Viewable");
        partMap.put("SheetName", "Cover");
        partMap.put("Side", "Front");
        partMap.put("WebName", "0001");
        JDFResource resWebName = resPreview.getCreatePartition(partMap, struct);

        //create a partition while inside a partition
        partMap.clear();
        partMap.put("PreviewType", "Viewable");
        partMap.put("SheetName", "Cover");
        partMap.put("Side", "Front");
        partMap.put("WebName", "0001");
        partMap.put("DocRunIndex", "0001");

        resWebName.getCreatePartition(partMap, null);

        //Nächster Test
        partMap.clear();
        partMap.put("PreviewType", "Viewable");
        partMap.put("SheetName", "Cover");
        partMap.put("Side", "Front");
        partMap.put("WebName", "0001");
        partMap.put("DocRunIndex", "0001");

        struct = new VString();
        struct.add("PreviewType");
        struct.add("SheetName");
        struct.add("Side");
        struct.add("WebName");
        struct.add("DocRunIndex");

        resWebName.getCreatePartition(partMap, struct);

//      Nächster Test
        partMap.clear();
        partMap.put("PreviewType", "Viewable");
        partMap.put("SheetName", "Cover");
        partMap.put("Side", "Front");
        partMap.put("WebName", "0001");
        partMap.put("DocRunIndex", "0001");
        partMap.put("CellIndex", "0002");

        struct = new VString();
        struct.add("PreviewType");
        struct.add("SheetName");
        struct.add("Side");
        struct.add("WebName");
        struct.add("DocRunIndex");
        struct.add("CellIndex");

        resPreview.getCreatePartition(partMap, struct);

//      create a partition while inside a partition
        partMap.clear();
        partMap.put("PreviewType", "Viewable");
        partMap.put("SheetName", "Cover");
        partMap.put("Side", "Front");
        partMap.put("WebName", "0001");
        partMap.put("DocRunIndex", "0001");
        partMap.put("CellIndex", "0002");

        JDFResource resCellIndex = resPreview.getCreatePartition(partMap, null);

        partMap.clear();
        partMap.put("PreviewType", "Viewable");
        partMap.put("SheetName", "Cover");
        partMap.put("Side", "Front");
        partMap.put("WebName", "0001");
        partMap.put("DocRunIndex", "0001");
        partMap.put("CellIndex", "0002");
        partMap.put("PartVersion", "003");

        resCellIndex.getCreatePartition(partMap, null);

        //nächster Test
        JDFResource r = (JDFResource)resPool.getChildByTagName("Preview",null, 0, null, true, true);
        JDFAttributeMap map = new JDFAttributeMap();
        map.put("PreviewType", "Viewable");
        map.put("SheetName", "Cover");

        JDFResource part1 = r.getPartition(map, EnumPartUsage.Explicit);
        JDFResource part2 = r.getCreatePartition(map, null);
        assertTrue("part1 and part2 must be equal", part1.equals(part2));

        map.put("Side", "Front");
        JDFResource part11 = part1.getPartition(map, EnumPartUsage.Explicit);
        JDFResource part12 = part1.getCreatePartition(map, null);

        assertTrue("part11 and part12 must be equal", part11.equals(part12));

        doc.write2File(sm_dirTestDataTemp+"testGetCreatePartition.jdf", 0, true);
    }

    ////////////////////////////////////////////////////////////////////////

    public void testIdentical()
    {
        JDFDoc doc=new JDFDoc("JDF");
        JDFNode n=doc.getJDFRoot();
        n.setType("Product",true);

        JDFComponent c=(JDFComponent)n.appendMatchingResource("Component",JDFNode.EnumProcessUsage.AnyOutput,null);
        JDFResourceLink l=n.getMatchingLink("Component",JDFNode.EnumProcessUsage.AnyOutput,0);
        assertTrue("link exists",l!=null);
        JDFAttributeMap mPart1=new JDFAttributeMap("SheetName","S1");
        mPart1.put("Separation","Yellow");
        JDFAttributeMap mPart3=new JDFAttributeMap("SheetName","S3");
        mPart3.put("Separation","Yellow");

        JDFMedia med=(JDFMedia) n.addResource("Media", null, EnumUsage.Input, null, null, null, null);
        JDFExposedMedia xmed=(JDFExposedMedia) n.addResource("ExposedMedia", null, EnumUsage.Input, null, null, null, null);
        xmed.refElement(med);

        JDFComponent c1=(JDFComponent) c.addPartition(JDFResource.EnumPartIDKey.SheetName,"S1");
        c1.addPartition(JDFResource.EnumPartIDKey.Separation,"Cyan");
        JDFComponent c1y=(JDFComponent) c1.addPartition(JDFResource.EnumPartIDKey.Separation,"Yellow");
        c1.addPartition(JDFResource.EnumPartIDKey.Separation,"Magenta");

        JDFComponent c2=(JDFComponent) c.addPartition(JDFResource.EnumPartIDKey.SheetName,"S2");
        assertEquals("part ok",c2.getSheetName(),"S2");
        JDFComponent c3=(JDFComponent) c.addPartition(JDFResource.EnumPartIDKey.SheetName,"S3");
        c3.setAmount(42);
        JDFAttributeMap map=c3.getPartMap();
        c3.setIdentical(c1);
        assertEquals(map,c3.getPartMap());
        assertNotNull(c3.getIdenticalMap());
        assertEquals(c3.getIdenticalMap(),c1.getPartMap());

        c3.removeChild(ElementName.IDENTICAL,null,0);
        assertNull(c3.getIdenticalMap());
        VJDFAttributeMap vMap=new VJDFAttributeMap();
        vMap.add(c1.getPartMap());
        vMap.add(c3.getPartMap());
        c.setIdentical(vMap);
        assertEquals(map,c3.getPartMap());
        assertNotNull(c3.getIdenticalMap());
        assertEquals(c3.getIdenticalMap(),c1.getPartMap());

        JDFComponent c3y=(JDFComponent) c.getPartition(mPart3,null);
        assertEquals("identical",c1y,c3y);
        c1y=(JDFComponent) c.getPartition(mPart1,null);
        assertEquals("identical 2",c1y,c3y);
        try
        {
            c.setIdentical(c2);
            fail("root set identical");
        }
        catch(JDFException x)
        {
            //
        }
    }

    //////////////////////////////////////////////////////////////
    ////////////////////////////////////////////////////////////////////////

    public void testIdenticalValid()
    {
        JDFDoc doc=new JDFDoc("JDF");
        JDFNode n=doc.getJDFRoot();
        n.setType("Product",true);

        JDFComponent c=(JDFComponent)n.appendMatchingResource("Component",JDFNode.EnumProcessUsage.AnyOutput,null);
        JDFResourceLink l=n.getMatchingLink("Component",JDFNode.EnumProcessUsage.AnyOutput,0);
        assertTrue("link exists",l!=null);
        JDFAttributeMap mPart1=new JDFAttributeMap("SheetName","S1");
        mPart1.put("Separation","Yellow");
        JDFAttributeMap mPart3=new JDFAttributeMap("SheetName","S3");
        mPart3.put("Separation","Yellow");

        JDFMedia med=(JDFMedia) n.addResource("Media", null, EnumUsage.Input, null, null, null, null);
        JDFExposedMedia xmed=(JDFExposedMedia) n.addResource("ExposedMedia", null, EnumUsage.Input, null, null, null, null);
        xmed.refElement(med);

        JDFComponent c1=(JDFComponent) c.addPartition(JDFResource.EnumPartIDKey.SheetName,"S1");
        c1.addPartition(JDFResource.EnumPartIDKey.Separation,"Cyan");
        JDFComponent c1y=(JDFComponent) c1.addPartition(JDFResource.EnumPartIDKey.Separation,"Yellow");
        c1.addPartition(JDFResource.EnumPartIDKey.Separation,"Magenta");

        JDFComponent c2=(JDFComponent) c.addPartition(JDFResource.EnumPartIDKey.SheetName,"S2");
        assertEquals("part ok",c2.getSheetName(),"S2");
        JDFComponent c3=(JDFComponent) c.addPartition(JDFResource.EnumPartIDKey.SheetName,"S3");
        c3.setIdentical(c1);
        assertTrue(c3.isValid(EnumValidationLevel.Incomplete));

    }

    //////////////////////////////////////////////////////////////

    /**
     * test whether getpartition works for lists and ranges
     */
    public void testGetPartsPartition()
    {   
        JDFDoc doc=new JDFDoc("JDF");
        JDFNode n=doc.getJDFRoot();
        n.setType(EnumType.ConventionalPrinting);
        JDFExposedMedia xm=(JDFExposedMedia) n.appendMatchingResource(ElementName.EXPOSEDMEDIA,EnumProcessUsage.AnyInput,null);
        JDFExposedMedia xmp=(JDFExposedMedia) xm.addPartition(EnumPartIDKey.PartVersion,"DE FR");
        JDFMedia m=(JDFMedia) n.appendMatchingResource(ElementName.MEDIA,EnumProcessUsage.AnyInput,null);
        JDFMedia mp=(JDFMedia) m.addPartition(EnumPartIDKey.SheetIndex,"1 ~ 3");

        // tests for partition list 
        // DE from DE FR
        assertEquals(xm.getPartition(new JDFAttributeMap(EnumPartIDKey.PartVersion.getName(),"DE"),null),xmp);
        assertNull(xm.getPartition(new JDFAttributeMap(EnumPartIDKey.PartVersion.getName(),"GR"),null));

//      get 2 from 1~3        
        assertEquals(m.getPartition(new JDFAttributeMap(EnumPartIDKey.SheetIndex.getName(),"2"),null),mp);
        assertNull(m.getPartition(new JDFAttributeMap(EnumPartIDKey.SheetIndex.getName(),"42"),null));
    }

    //////////////////////////////////////////////////////////////

    public void testGetPartitionVector()
    {        

        JDFDoc doc=creatXMDoc();
        JDFNode n=doc.getJDFRoot();
        JDFExposedMedia xm=(JDFExposedMedia)n.getMatchingResource("ExposedMedia",JDFNode.EnumProcessUsage.AnyInput,null,0);
        xm.setPartUsage(EnumPartUsage.Implicit);
        JDFAttributeMap m=new JDFAttributeMap("SignatureName","Sig1");
        m.put("RunIndex","3");
        VElement v=xm.getPartitionVector(m,null);
        JDFResource r=xm.getPartition(m,null);
        assertEquals("same as only sig1",r,xm.getPartition(new JDFAttributeMap("SignatureName","Sig1"),null));
        int i=0;
        assertTrue("implicit 4",v.size()==4);
        for(i=0;i<v.size();i++)
        {
            JDFExposedMedia xmp=(JDFExposedMedia) v.elementAt(i);
            assertTrue("overlap of maps",m.overlapMap(xmp.getPartMap()));
        }
        v=xm.getPartitionVector(m,EnumPartUsage.Sparse);
        for(i=0;i<v.size();i++)
        {
            JDFExposedMedia xmp=(JDFExposedMedia) v.elementAt(i);
            assertTrue("overlap of maps",m.overlapMap(xmp.getPartMap()));
        }
        assertTrue("sparse 4",v.size()==4);

        v=xm.getPartitionVector(m,EnumPartUsage.Explicit);
        assertTrue("no explicit",v.size()==0);

        m.clear();
        m.put("SignatureName","Sig1");
        m.put("SheetName","S3");
        v=xm.getPartitionVector(m,EnumPartUsage.Explicit);
        assertTrue("no explicit",v.size()==0);
        v=xm.getPartitionVector(m,EnumPartUsage.Sparse);
        assertTrue("no sparse",v.size()==0);
        v=xm.getPartitionVector(m,EnumPartUsage.Implicit);
        assertTrue("1 impl",v.size()==1);
        for(i=0;i<v.size();i++)
        {
            JDFExposedMedia xmp=(JDFExposedMedia) v.elementAt(i);
            assertTrue("overlap of maps",m.overlapMap(xmp.getPartMap()));
        }        
    }

    /////////////////////////////////////////////////////////////////////////////

    public void testDeleteUnlinked()
    {
        JDFDoc doc=creatXMDoc();
        JDFNode n=doc.getJDFRoot();
        JDFExposedMedia xm=(JDFExposedMedia)n.getMatchingResource("ExposedMedia",JDFNode.EnumProcessUsage.AnyInput,null,0);
        JDFMedia m=xm.getMedia();
        m=(JDFMedia) m.makeRootResource(null,null,true);
        JDFResourceLink rl=n.linkResource(m,EnumUsage.Input,null);  
        assertFalse(m.deleteUnLinked());
        xm.getElement_KElement("MediaRef",null,0).deleteNode();
        assertFalse(m.deleteUnLinked());
        xm.refElement(m);
        rl.deleteNode();
        assertFalse(m.deleteUnLinked());
        xm.getElement_KElement("MediaRef",null,0).deleteNode();
        assertTrue(m.deleteUnLinked());

    }
    /////////////////////////////////////////////////////////////////////////////

    public void testGetLinks()
    {        
        JDFDoc doc=creatXMDoc();
        JDFNode n=doc.getJDFRoot();
        JDFExposedMedia xm=(JDFExposedMedia)n.getMatchingResource("ExposedMedia",JDFNode.EnumProcessUsage.AnyInput,null,0);
        JDFMedia m=xm.getMedia();
        m=(JDFMedia) m.makeRootResource(null,null,true);
        VElement v=m.getLinks(null,null);
        assertEquals(v.size(),1);
        JDFResourceLink rl=n.linkResource(m,true ? EnumUsage.Input : EnumUsage.Output,null);    
        v=m.getLinks(null,null);
        assertEquals(v.size(),2);
        assertTrue(v.contains(rl));

    }

    ////////////////////////////////////////////////////////////////////////////   /////////////////////////////////////////////////////////////////////////////

    public void testGetLinksAndRefs()
    {        
        JDFDoc doc=creatXMDoc();
        JDFNode n=doc.getJDFRoot();
        JDFExposedMedia xm=(JDFExposedMedia)n.getMatchingResource("ExposedMedia",JDFNode.EnumProcessUsage.AnyInput,null,0);
        JDFMedia m=xm.getMedia();
        m=(JDFMedia) m.makeRootResource(null,null,true);
        VElement v=m.getLinksAndRefs(false,true);
        assertEquals(v.size(),1);
        JDFResourceLink rl=n.linkResource(m,EnumUsage.Input ,null);    
        v=m.getLinksAndRefs(false,true);
        assertEquals(v.size(),1);
        v=m.getLinksAndRefs(true,false);
        assertEquals(v.size(),1);
        assertTrue(v.contains(rl));
        
        v=m.getLinksAndRefs(true,true);
        assertEquals(v.size(),2);
        assertTrue(v.contains(rl));
               
        JDFMedia mPart=(JDFMedia) m.addPartition(EnumPartIDKey.SignatureName,"Sig1");
        v=mPart.getLinksAndRefs(true,true);
        assertEquals("partitioned resource has no links",v.size(),2);
        JDFAttributeMap myMap=new JDFAttributeMap();
        myMap.put("SignatureName","Sig2");
        rl.setPartMap(myMap);

        xm.getElement_KElement("MediaRef",null,0).deleteNode();

        v=mPart.getLinksAndRefs(true,true);
        assertNull("partitioned resource has no links",v);

        myMap.put("SignatureName","Sig1");
        rl.setPartMap(myMap);
        v=mPart.getLinksAndRefs(true,true);
        assertEquals("partitioned resource has one link",v.size(),1);

    }

    ////////////////////////////////////////////////////////////////////////////
    public void testInvalidPartIDKeysLeaves()
    {              
        JDFDoc doc=creatXMDoc();
        JDFNode n=doc.getJDFRoot();
        JDFExposedMedia xm=(JDFExposedMedia)n.getMatchingResource("ExposedMedia",JDFNode.EnumProcessUsage.AnyInput,null,0);
        xm.setPartIDKeys(new VString("fnarf"," "));
        assertTrue(xm.getInvalidAttributes(EnumValidationLevel.Incomplete, true, -1).contains(AttributeName.PARTIDKEYS));
    }
    ////////////////////////////////////////////////////////////////////////////

    public void testGetLeaves()
    {              
        JDFDoc doc=creatXMDoc();
            JDFNode n=doc.getJDFRoot();
        JDFExposedMedia xm=(JDFExposedMedia)n.getMatchingResource("ExposedMedia",JDFNode.EnumProcessUsage.AnyInput,null,0);

        VElement vL=xm.getLeaves(false);
        assertEquals("size false",vL.size(),8);
        for(int i=0;i<vL.size();i++)
        {
            JDFExposedMedia xm2=(JDFExposedMedia) vL.elementAt(i);
            assertEquals("map ok",xm2.getPartMap().size(),3);
        }

        vL=xm.getLeaves(true);
        assertEquals("size false",vL.size(),15);       
    }

    ////////////////////////////////////////////////////////
    public static JDFDoc creatRLDoc()
    {
        JDFDoc doc=new JDFDoc("JDF");
        JDFNode n=doc.getJDFRoot();
        n.setJobPartID("P1");
        n.setJobID("J1");
        n.setType("Interpreting",true);

        JDFRunList rl=(JDFRunList)n.appendMatchingResource("RunList",JDFNode.EnumProcessUsage.AnyInput,null);
        for(int i=1;i<3;i++)
        {
            JDFRunList rlset=(JDFRunList) rl.addPartition(EnumPartIDKey.RunSet,"Set"+String.valueOf(i));
            VString filNames=StringUtil.tokenize("FCyan.pdf FMagenta.pdf FYellow.pdf FBlack.pdf"," ",false);
            VString sepNames=StringUtil.tokenize("Cyan Magenta Yellow Black"," ",false);
            JDFRunList rlRun=rlset.addSepRun(filNames,sepNames,0,16,false);
            rlRun.setRun("Run"+String.valueOf(i));
            rlRun.setSorted(true);
            rlRun.appendElement("foo:bar","www.foobar.com");
        }


        return doc;
    }

    ////////////////////////////////////////////////////////
    public static JDFDoc creatXMDoc()
    {
        JDFElement.setDefaultJDFVersion(EnumVersion.Version_1_3);
        JDFDoc doc=new JDFDoc("JDF");
        JDFNode n=doc.getJDFRoot();
        n.setJobPartID("P1");
        n.setJobID("J1");
        n.setType("ConventionalPrinting",true);
        n.appendElement("NS:Foobar","www.foobar.com");

        JDFComponent comp=(JDFComponent)n.appendMatchingResource("Component",JDFNode.EnumProcessUsage.AnyOutput,null);
        JDFExposedMedia xm=(JDFExposedMedia)n.appendMatchingResource("ExposedMedia",JDFNode.EnumProcessUsage.Plate,null);
        JDFNodeInfo ni=n.appendNodeInfo();
        JDFMedia m=xm.appendMedia();
        m.appendElement("NS:FoobarMedia","www.foobar.com");

        assertEquals("m Class",m.getResourceClass(),EnumResourceClass.Consumable);


        VString vs=new VString();
        vs.add("SignatureName");
        vs.add("SheetName");
        vs.add("Side");

        JDFAttributeMap mPart1=new JDFAttributeMap("SignatureName","Sig1");
        mPart1.put("SheetName","S1");
        mPart1.put("Side","Front");       
        xm.getCreatePartition(mPart1,vs);
        ni.getCreatePartition(mPart1,vs);
        comp.getCreatePartition(mPart1,vs);

        mPart1.put("Side","Back");
        xm.getCreatePartition(mPart1,vs);
        ni.getCreatePartition(mPart1,vs);
        comp.getCreatePartition(mPart1,vs);

        mPart1.put("SheetName","S2");
        mPart1.put("Side","Front");
        xm.getCreatePartition(mPart1,vs);
        ni.getCreatePartition(mPart1,vs);
        comp.getCreatePartition(mPart1,vs);

        mPart1.put("Side","Back");
        xm.getCreatePartition(mPart1,vs);
        ni.getCreatePartition(mPart1,vs);
        comp.getCreatePartition(mPart1,vs);

        mPart1.put("SignatureName","Sig2");
        mPart1.put("SheetName","S1");
        mPart1.put("Side","Front");       
        xm.getCreatePartition(mPart1,vs);
        ni.getCreatePartition(mPart1,vs);
        comp.getCreatePartition(mPart1,vs);
        comp.appendElement("foo:bar","www.foobar.com");


        mPart1.put("Side","Back");
        xm.getCreatePartition(mPart1,vs);
        ni.getCreatePartition(mPart1,vs);
        comp.getCreatePartition(mPart1,vs);

        mPart1.put("SheetName","S2");
        mPart1.put("Side","Front");
        xm.getCreatePartition(mPart1,vs);
        ni.getCreatePartition(mPart1,vs);
        comp.getCreatePartition(mPart1,vs);

        mPart1.put("Side","Back");
        xm.getCreatePartition(mPart1,vs);
        ni.getCreatePartition(mPart1,vs);
        comp.getCreatePartition(mPart1,vs);
        return doc;       
    }

    /////////////////////////////////////////////////////////

    public void testSubElement()
    {        
        JDFDoc doc=creatXMDoc();
        JDFNode n=doc.getJDFRoot();
        JDFExposedMedia xm=(JDFExposedMedia)n.getMatchingResource("ExposedMedia",JDFNode.EnumProcessUsage.AnyInput,null,0);
        JDFMedia m=xm.getMedia();
        assertEquals("Media in XM class",m.getResourceClass(),EnumResourceClass.Consumable);
        m.setBrand("fooBrand");
        assertTrue("xm valid",xm.isValid(EnumValidationLevel.Complete));
        assertTrue("m valid",m.isValid(EnumValidationLevel.Complete));
        m.deleteNode();
        m=xm.appendMedia();
        m.setBrand("barBrand");
        assertTrue("xm valid",xm.isValid(EnumValidationLevel.Complete));
        assertTrue("m valid",m.isValid(EnumValidationLevel.Complete));

    }

    public void testSetLocked()
    {        
        JDFDoc doc=creatXMDoc();
        JDFNode n=doc.getJDFRoot();
        JDFExposedMedia xm=(JDFExposedMedia)n.getMatchingResource("ExposedMedia",JDFNode.EnumProcessUsage.AnyInput,null,0);
        xm.setLocked(false);
        assertFalse(xm.getLocked());
        assertFalse(xm.hasAttribute(AttributeName.LOCKED));
        VElement vL=xm.getLeaves(false);
        for(int i=0;i<1;i++)
        {
            xm.setLocked(false);
            JDFExposedMedia xm3=(JDFExposedMedia) vL.elementAt(i);
            JDFExposedMedia xm2=(JDFExposedMedia)xm3.getParentNode();
            assertFalse(xm2.getLocked());
            assertFalse(xm3.getLocked());
            assertFalse(xm2.hasAttribute(AttributeName.LOCKED));
            assertFalse(xm3.hasAttribute(AttributeName.LOCKED));
            xm.setLocked(true);
            assertTrue(xm.getLocked());
            assertTrue(xm2.getLocked());
            assertTrue(xm3.getLocked());
            assertFalse(xm2.hasAttribute(AttributeName.LOCKED));
            assertFalse(xm3.hasAttribute(AttributeName.LOCKED));
            xm2.setLocked(false);
            assertFalse(xm2.getLocked());
            assertFalse(xm3.getLocked());
            assertTrue(xm2.hasAttribute(AttributeName.LOCKED));
            assertFalse(xm3.hasAttribute(AttributeName.LOCKED));
            xm3.setLocked(true);
            assertFalse(xm2.getLocked());
            assertTrue(xm3.getLocked());
            assertTrue(xm2.hasAttribute(AttributeName.LOCKED));
            assertTrue(xm3.hasAttribute(AttributeName.LOCKED));


        }
    }

    //////////////////////////////////////////////////////////////////////////

    public void testImplicitPartitions(){
        JDFDoc doc=creatXMDoc();
        JDFNode n=doc.getJDFRoot();
        JDFExposedMedia xm=(JDFExposedMedia)n.getMatchingResource("ExposedMedia",JDFNode.EnumProcessUsage.AnyInput,null,0);
        assertNull("xm no impicit part",xm.getImplicitPartitions());
        JDFRunList ruli=(JDFRunList) n.addResource(ElementName.RUNLIST, null, EnumUsage.Input, null, null, null, null);
        JDFCutBlock cb=(JDFCutBlock) n.addResource(ElementName.CUTBLOCK, null, EnumUsage.Input, null, null, null, null);
        try
        {
            ruli.addPartition(EnumPartIDKey.RunIndex,"1");
            fail("no go here");
        }
        catch (JDFException e) {
            // nop
        }
        try
        {
            cb.addPartition(EnumPartIDKey.BlockName,"1");
            fail("no go here");
        }
        catch (JDFException e) {
            // nop
        }
        assertFalse("pidk",ruli.hasAttribute(AttributeName.PARTIDKEYS));
        assertFalse("pidk",cb.hasAttribute(AttributeName.PARTIDKEYS));
        ruli.addPartition(EnumPartIDKey.SheetName,"S1");
        assertEquals("pidk",ruli.getAttribute(AttributeName.PARTIDKEYS),EnumPartIDKey.SheetName.getName());        
    }

    ////////////////////////////////////////////////////////////////////

    public void testRemoveImplicitPartions()
    {
        JDFDoc doc=new JDFDoc("JDF");
        JDFNode n=doc.getJDFRoot();
        n.setType(EnumType.Interpreting);
        JDFRunList rul=(JDFRunList) n.appendMatchingResource(ElementName.RUNLIST,EnumProcessUsage.AnyInput,null);

        // tests for partition list 
        assertEquals(rul.getPartition(new JDFAttributeMap(EnumPartIDKey.RunIndex.getName(),"2~5"),null),rul);
        assertNull(rul.getPartition(new JDFAttributeMap(EnumPartIDKey.PartVersion.getName(),"GR"),null));

    }

    ////////////////////////////////////////////////////////////////////
    /**
     * test expand and collapse methods
     */
    public void testCollapse()
    {        
        JDFDoc doc=creatRLDoc();
        JDFNode n=doc.getJDFRoot();

        JDFDigitalPrintingParams dpp=(JDFDigitalPrintingParams) n.addResource(ElementName.DIGITALPRINTINGPARAMS, null, EnumUsage.Input, null, null, null, null);
        dpp.collapse(true);
        dpp.collapse(false);

        JDFRunList rl=(JDFRunList)n.getMatchingResource("RunList",JDFNode.EnumProcessUsage.AnyInput,null,0);
        JDFAttributeMap map=new JDFAttributeMap();
        map.put("RunSet","Set2");
        JDFRunList rlSet=(JDFRunList) rl.getPartition(map,null);        
        assertNotNull(rlSet);
        map.put("Run","Run2");
        JDFRunList rlRun=(JDFRunList) rl.getPartition(map,null);
        assertNotNull(rlRun);
        map.put("Separation","Cyan");
        JDFRunList rlSep=(JDFRunList) rl.getPartition(map,null);
        assertNotNull(rlSep);
        assertTrue(rlRun.getIsPage());
        assertFalse(rlSep.getIsPage());
        rlRun.collapse(true);
        assertTrue(rlRun.getIsPage());
        assertFalse(rlSep.getIsPage());
        assertTrue(rlSet.getIsPage());
        assertTrue(rl.getIsPage());
        rlRun.collapse(false);
        assertTrue(rlRun.getIsPage());
        assertFalse(rlSep.getIsPage());
        assertTrue(rlSet.getIsPage());
        assertTrue(rl.getIsPage());
        rlRun.setRunTag("foo");
        rlRun.expand(true);
        rlRun.collapse(false);
        assertTrue(rlRun.hasAttribute(AttributeName.RUNTAG));
        assertFalse(rlSep.hasAttribute(AttributeName.RUNTAG));
        assertFalse(rlSet.hasAttribute(AttributeName.RUNTAG));
        rlRun.expand(true);
        rlRun.collapse(true);
        assertFalse(rlRun.hasAttribute(AttributeName.RUNTAG));
        assertTrue(rlSep.hasAttribute(AttributeName.RUNTAG));
        assertFalse(rlSet.hasAttribute(AttributeName.RUNTAG));
    }
    ////////////////////////////////////////////////////////////////////
    /**
     * test expand and collapse methods
     */
    public void testFixVersion()
    { 
        JDFDoc doc=creatXMDoc();
        JDFNode n=doc.getJDFRoot();
        JDFExposedMedia xm=(JDFExposedMedia)n.getMatchingResource("ExposedMedia",JDFNode.EnumProcessUsage.AnyInput,null,0);
        JDFExposedMedia xm2=(JDFExposedMedia)xm.getPartition(new JDFAttributeMap(EnumPartIDKey.SignatureName, "Sig1"), null);
        assertTrue(xm.isValid(EnumValidationLevel.Complete));
        xm2.setAttribute("Class",EnumResourceClass.Handling.getName());
        assertFalse(xm.isValid(EnumValidationLevel.Complete));
        xm.fixVersion(null);
        assertNull(xm2.getAttribute_KElement("Class", null, null));
        assertTrue(xm.isValid(EnumValidationLevel.Complete));
    }
    ////////////////////////////////////////////////////////////////////
    /**
     * test expand and collapse methods
     */
    public void testExpand()
    {        
        JDFDoc doc=creatXMDoc();
        JDFNode n=doc.getJDFRoot();
        JDFExposedMedia xm=(JDFExposedMedia)n.getMatchingResource("ExposedMedia",JDFNode.EnumProcessUsage.AnyInput,null,0);
        xm.setBrand("rootBrand");
        xm.setGeneralID("testID","rootValue");
        xm.expand(false);
        xm.collapse(true);
        xm.expand(true);
        xm.collapse(false);

        JDFAttributeMap mPart=new JDFAttributeMap("SignatureName","Sig1");
        mPart.put("SheetName","S1");
        mPart.put("Side","Front");       
        JDFExposedMedia xmPart=(JDFExposedMedia)xm.getPartition(mPart,null);
        mPart.put("SheetName","S2");
        JDFExposedMedia xmPart2=(JDFExposedMedia)xm.getPartition(mPart,null);

        xmPart.setBrand("PartBrand");
        xmPart.setGeneralID("testID","partValue");

        xm.expand(false);
        assertEquals("expanded sub",xmPart.getBrand(),"PartBrand");
        assertEquals("expanded sub",xmPart.getGeneralID("testID"),"partValue");
        assertEquals("expanded sub2",xmPart2.getBrand(),"rootBrand");
        assertEquals("expanded sub2",xmPart2.getGeneralID("testID"),"rootValue");
        assertTrue("hasBrand",xmPart2.hasAttribute_KElement("Brand",null,false));
        assertTrue("hasID",xmPart2.getElement_KElement("GeneralID",null,0)!=null);
        assertFalse("has part Key",xmPart.hasAttribute_KElement(AttributeName.SHEETNAME,null,false));
        assertFalse("has part Key",xmPart2.hasAttribute_KElement(AttributeName.SHEETNAME,null,false));

        xm.collapse(false);
        assertEquals("expanded sub after collapse",xmPart.getBrand(),"PartBrand");
        assertEquals("expanded sub after collapse",xmPart.getGeneralID("testID"),"partValue");
        assertEquals("expanded sub2 after collapse",xmPart2.getBrand(),"rootBrand");
        assertEquals("expanded sub2 after collapse",xmPart2.getGeneralID("testID"),"rootValue");
        assertFalse("hasBrand",xmPart2.hasAttribute_KElement("Brand",null,false));
        assertTrue("hasID",xmPart2.getElement_KElement("GeneralID",null,0)==null);
        assertFalse("has part Key",xmPart.hasAttribute_KElement(AttributeName.SHEETNAME,null,false));
        assertFalse("has part Key",xmPart2.hasAttribute_KElement(AttributeName.SHEETNAME,null,false));

        JDFExposedMedia xmPart3=(JDFExposedMedia)xmPart2.getParentNode_KElement().getParentNode_KElement();
        mPart.put("SignatureName","Sig2");
        JDFExposedMedia xmPart4=(JDFExposedMedia)xm.getPartition(mPart,null);

        xmPart3.expand(true);
        assertTrue("hasBrand",xmPart2.hasAttribute_KElement("Brand",null,false));
        assertFalse("hasBrand",xmPart4.hasAttribute_KElement("Brand",null,false));
        assertTrue("hasID",xmPart2.getElement_KElement("GeneralID",null,0)!=null);
        assertFalse("hasID",xmPart4.getElement_KElement("GeneralID",null,0)!=null);
        assertFalse("has part Key",xmPart.hasAttribute_KElement(AttributeName.SHEETNAME,null,false));
        assertFalse("has part Key",xmPart2.hasAttribute_KElement(AttributeName.SHEETNAME,null,false));

        xmPart3.collapse(false);
        assertFalse("hasBrand",xmPart2.hasAttribute_KElement("Brand",null,false));
        assertTrue("hasBrand",xmPart3.hasAttribute_KElement("Brand",null,false));
        assertFalse("hasID",xmPart2.getElement_KElement("GeneralID",null,0)!=null);

        xmPart3=(JDFExposedMedia)xmPart4.getParentNode_KElement().getParentNode_KElement();
        xmPart3.expand(true);
        assertTrue("hasBrand",xmPart4.hasAttribute_KElement("Brand",null,false));
        assertFalse("hasBrand",xmPart2.hasAttribute_KElement("Brand",null,false));
        assertTrue("hasID",xmPart4.getElement_KElement("GeneralID",null,0)!=null);
        assertFalse("hasID",xmPart2.getElement_KElement("GeneralID",null,0)!=null);
        xmPart3.collapse(false);
        assertFalse("hasBrand",xmPart4.hasAttribute_KElement("Brand",null,false));
        assertTrue("hasBrand",xmPart3.hasAttribute_KElement("Brand",null,false));
        assertFalse("hasID",xmPart4.getElement_KElement("GeneralID",null,0)!=null);
        assertTrue("hasID",xmPart3.getElement("GeneralID",null,0)!=null);

        JDFDigitalPrintingParams dpp=(JDFDigitalPrintingParams) n.addResource(ElementName.DIGITALPRINTINGPARAMS, null, EnumUsage.Input, null, null, null, null);
        dpp.expand(true);
        dpp.expand(false);
        assertTrue(dpp.hasAttribute("ID"));

    }

    //////////////////////////////////////////////////////////////////

    public void testGeneralID()
    {
        JDFDoc doc=creatXMDoc();
        JDFNode n=doc.getJDFRoot();
        JDFExposedMedia xm=(JDFExposedMedia)n.getMatchingResource("ExposedMedia",JDFNode.EnumProcessUsage.AnyInput,null,0);
        JDFExposedMedia xm2=(JDFExposedMedia) xm.getPartition(new JDFAttributeMap("SignatureName","Sig1"),EnumPartUsage.Explicit);  
        xm.setGeneralID("foo","bar");
        assertEquals("",xm.getGeneralID("foo"),"bar");
        assertEquals("",xm2.getGeneralID("foo"),"bar");
        assertEquals("",xm.numChildElements(ElementName.GENERALID,null),1);
        xm.setGeneralID("foo","bar2");
        assertEquals("",xm.getGeneralID("foo"),"bar2");
        assertEquals("",xm.numChildElements(ElementName.GENERALID,null),1);
        assertEquals("",xm2.numChildElements(ElementName.GENERALID,null),1);
        xm2.setGeneralID("foo","bar4");
        xm.setGeneralID("foo2","bar3");
        assertEquals("",xm.getGeneralID("foo"),"bar2");
        assertEquals("",xm2.getGeneralID("foo"),"bar4");
        assertEquals("",xm.getGeneralID("foo2"),"bar3");
        assertEquals("",xm.numChildElements(ElementName.GENERALID,null),2);
        xm.removeGeneralID("foo");
        assertEquals("",xm.getGeneralID("foo"),"");
        assertEquals("",xm.getGeneralID("foo2"),"bar3");
        assertEquals("",xm.numChildElements(ElementName.GENERALID,null),1);  
        xm.setGeneralID("foo3","bar33");
        JDFGeneralID gi=xm.getGeneralID(0);
        assertEquals("",gi.getIDUsage(),"foo2");
        xm.removeGeneralID(null);
        assertEquals("",xm.numChildElements(ElementName.GENERALID,null),0);  
    }

    /////////////////////////////////////////////////////////////////////////////

    public void testGeneralIDEmptyNamespace()
    {
        JDFDoc doc = creatXMDoc();
        JDFNode n = doc.getJDFRoot();
        JDFExposedMedia xm = (JDFExposedMedia) n.getMatchingResource("ExposedMedia", JDFNode.EnumProcessUsage.AnyInput, null, 0);

        JDFGeneralID generalID = (JDFGeneralID) xm.appendElement(ElementName.GENERALID);
        assertEquals(JDFConstants.EMPTYSTRING, generalID.getIDUsage());
        assertEquals(JDFConstants.EMPTYSTRING, generalID.getIDValue());
    }


    /////////////////////////////////////////////////////////////////////////////

    public void testInstantiations()
    {
        JDFDoc doc = new JDFDoc("JDF");
        JDFNode root = doc.getJDFRoot();
        JDFResourcePool resPool = root.getCreateResourcePool();
        KElement kElem = resPool.appendElement(ElementName.STRIPPINGPARAMS);
        assertTrue(kElem instanceof JDFStrippingParams);

        kElem = resPool.appendElement(ElementName.STRIPCELLPARAMS);
        assertTrue(kElem instanceof JDFStripCellParams);

        kElem = resPool.appendElement(ElementName.BINDERYSIGNATURE);
        assertTrue(kElem instanceof JDFBinderySignature);

    }

    /////////////////////////////////////////////////////////////////////////////

    public void testGetElement()
    {
        JDFDoc doc=creatXMDoc();
        JDFNode n=doc.getJDFRoot();
        JDFExposedMedia xm=(JDFExposedMedia)n.getMatchingResource("ExposedMedia",JDFNode.EnumProcessUsage.AnyInput,null,0);
        JDFMedia med=xm.getMedia();

        JDFAttributeMap mPart=new JDFAttributeMap("SignatureName","Sig1");
        mPart.put("SheetName","S1");
        mPart.put("Side","Front");       
        JDFExposedMedia xmPart=(JDFExposedMedia)xm.getPartition(mPart,null);
        assertEquals(xm.getMedia(),med);
        assertEquals(xmPart.getMedia(),med);
        JDFExposedMedia xmPartSig=(JDFExposedMedia)xm.getPartition(new JDFAttributeMap("SignatureName","Sig1"),null);
        JDFMedia med2=xmPartSig.appendMedia();
        assertEquals(xm.getMedia(),med);
        assertEquals(xmPart.getMedia(),med2);
        assertEquals(xmPartSig.getMedia(),med2);

        med=(JDFMedia) med.makeRootResource(null,null,true);
        assertEquals(xm.getMedia(),med);
        assertEquals(xmPart.getMedia(),med2);
        assertEquals(xmPartSig.getMedia(),med2);


    }

    /////////////////////////////////////////////////////////////////////////////

    public void testGetResStatus()
    {
        JDFDoc doc=creatXMDoc();
        JDFNode n=doc.getJDFRoot();
        JDFExposedMedia xm=(JDFExposedMedia)n.getMatchingResource("ExposedMedia",JDFNode.EnumProcessUsage.AnyInput,null,0);
        JDFAttributeMap mPart=new JDFAttributeMap("SignatureName","Sig1");
        mPart.put("SheetName","S1");
        mPart.put("Side","Front");       
        JDFExposedMedia xmPart=(JDFExposedMedia)xm.getPartition(mPart,null);
        xm.setResStatus(EnumResStatus.Unavailable,false);
        JDFMedia med=xm.getMedia();
        med.setResStatus(EnumResStatus.Unavailable,false);
        med.makeRootResource(null,null,true);

        assertEquals(xm.getResStatus(false),EnumResStatus.Unavailable);
        assertEquals(xmPart.getResStatus(false),EnumResStatus.Unavailable);
        assertEquals(xm.getResStatus(true),EnumResStatus.Unavailable);
        assertEquals(xmPart.getResStatus(true),EnumResStatus.Unavailable);

        xmPart.setResStatus(EnumResStatus.Available,false);
        assertEquals(xm.getResStatus(false),EnumResStatus.Unavailable);
        assertEquals(xmPart.getResStatus(false),EnumResStatus.Available);
        assertEquals(xm.getResStatus(true),EnumResStatus.Unavailable);
        assertEquals(xmPart.getResStatus(true),EnumResStatus.Unavailable);

        med.setResStatus(EnumResStatus.Available,false);
        assertEquals(xm.getResStatus(false),EnumResStatus.Unavailable);
        assertEquals(xmPart.getResStatus(false),EnumResStatus.Available);
        assertEquals(xm.getResStatus(true),EnumResStatus.Unavailable);
        assertEquals(xmPart.getResStatus(true),EnumResStatus.Available);

        xmPart.removeAttribute(AttributeName.STATUS);
        assertEquals(xm.getResStatus(false),EnumResStatus.Unavailable);
        assertEquals(xmPart.getResStatus(false),EnumResStatus.Unavailable);
        assertEquals(xm.getResStatus(true),EnumResStatus.Unavailable);
        assertEquals(xmPart.getResStatus(true),EnumResStatus.Unavailable);

        xm.setResStatus(EnumResStatus.Available,false);
        assertEquals(xm.getResStatus(false),EnumResStatus.Available);
        assertEquals(xmPart.getResStatus(false),EnumResStatus.Available);
        assertEquals(xm.getResStatus(true),EnumResStatus.Available);
        assertEquals(xmPart.getResStatus(true),EnumResStatus.Available);

        med.setResStatus(EnumResStatus.Unavailable,false);
        assertEquals(xm.getResStatus(false),EnumResStatus.Available);
        assertEquals(xmPart.getResStatus(false),EnumResStatus.Available);
        assertEquals(xm.getResStatus(true),EnumResStatus.Unavailable);
        assertEquals(xmPart.getResStatus(true),EnumResStatus.Unavailable);

    }
    /////////////////////////////////////////////////////////////////////////////

    public void testGetCreatePartition2()
    {
        JDFDoc doc =new JDFDoc(ElementName.JDF);
        JDFNode n=doc.getJDFRoot();
        JDFResource media=n.addResource("Media", null, EnumUsage.Input, null, null, null, null);

        JDFResource sig=media.addPartition(EnumPartIDKey.SignatureName, "sig1");
        sig=media.addPartition(EnumPartIDKey.SignatureName, "sig2");
        try
        {
            media.getCreatePartition(EnumPartIDKey.SheetName, "sh11",new VString("SignatureName SheetName"," "));
            fail("no parallel");
        }
        catch (JDFException x)
        {
            // nop
        }
    }
    /////////////////////////////////////////////////////////////////////////////

    public void testAddpartition()
    {
        JDFDoc doc =new JDFDoc(ElementName.JDF);
        JDFNode n=doc.getJDFRoot();
        JDFResource media=n.addResource("Media", null, EnumUsage.Input, null, null, null, null);

        JDFResource sig=media.addPartition(EnumPartIDKey.SignatureName, "sig1");
        media.addPartition(EnumPartIDKey.SignatureName, "sig2");
        try
        {
            media.addPartition(EnumPartIDKey.SignatureName, "sig1");
            fail("no identical key");
        }
        catch (JDFException x)
        {
            // nop
        }
        
        try
        {
            media.addPartition(EnumPartIDKey.SheetName, "sh11");
            fail("no parallel");
        }
        catch (JDFException x)
        {
            // nop
        }

        try
        {
            sig.addPartition(EnumPartIDKey.SignatureName, "sig2");
            fail("no existing");
        }
        catch (JDFException x)
        {
            // nop
        }

        JDFResource sheet=sig.addPartition(EnumPartIDKey.SheetName, "sh1");
        try
        {
            JDFResource side=sig.addPartition(EnumPartIDKey.Side, "Front");
            fail("no existing other parallel");
        }
        catch (JDFException x)
        {
            // nop
        }
        try
        {
            sheet.addPartition(EnumPartIDKey.SignatureName, "Sig3");
            fail("no existing lower");
        }
        catch (JDFException x)
        {
            // nop
        }
        sheet.addPartition(EnumPartIDKey.Side, "Front");        
        sheet.addPartition(EnumPartIDKey.Side, "Back");        
    }
    /////////////////////////////////////////////////////////////////////////////

    public void testConsistentPartIDKeys()
    {
        JDFDoc doc=creatXMDoc();
        JDFNode n=doc.getJDFRoot();
        JDFExposedMedia xm=(JDFExposedMedia)n.getMatchingResource("ExposedMedia",JDFNode.EnumProcessUsage.AnyInput,null,0);
        JDFAttributeMap mPart=new JDFAttributeMap("SignatureName","Sig1");
        mPart.put("SheetName","S1");
        mPart.put("Side","Front");       
        JDFExposedMedia xmPart=(JDFExposedMedia)xm.getPartition(mPart,null);
        assertTrue(xmPart.consistentPartIDKeys(EnumPartIDKey.BinderySignatureName));
        assertTrue(xmPart.consistentPartIDKeys(EnumPartIDKey.Side));
        xmPart.removeAttribute("Side");
        assertFalse(xmPart.consistentPartIDKeys(EnumPartIDKey.Side));
        assertTrue(xmPart.getInvalidAttributes(EnumValidationLevel.Complete,false,999).contains("Side"));
        xm.setAttribute("Side","Front");
        assertFalse(xmPart.consistentPartIDKeys(EnumPartIDKey.Side));
        xmPart.setAttribute("Side","Front");
        assertFalse(xmPart.consistentPartIDKeys(EnumPartIDKey.Side));
        xm.removeAttribute("Side");
        assertTrue(xmPart.consistentPartIDKeys(EnumPartIDKey.Side));
        assertTrue(xmPart.consistentPartIDKeys(EnumPartIDKey.SheetName));
        xmPart.getParentNode_KElement().removeAttribute("SheetName");
        assertFalse(xmPart.consistentPartIDKeys(EnumPartIDKey.SheetName));
        xmPart.getParentNode_KElement().setAttribute("SignatureName","foo");
        assertFalse(xmPart.consistentPartIDKeys(EnumPartIDKey.SheetName));
        assertTrue(xmPart.getInvalidAttributes(EnumValidationLevel.Complete,false,999).contains("SignatureName"));
    }
/////////////////////////////////////////////////////////////////////////////
    /////////////////////////////////////////////////////////////////////////////

    /**
     * jdf 1.4 preview anywhere example
     */
    public void testPreview14()
    {
        JDFDoc doc=new JDFDoc("JDF");
        JDFNode n=doc.getJDFRoot();
        n.setJobPartID("P1");
        n.setJobID("J1");
        n.setType("ConventionalPrinting",true);

        JDFComponent comp=(JDFComponent)n.appendMatchingResource("Component",JDFNode.EnumProcessUsage.AnyOutput,null);
        JDFPreview pvc=(JDFPreview)comp.appendElement(ElementName.PREVIEW);
        pvc.setURL("http://somehost/pvComponent.png");
        pvc.setPreviewType(EnumPreviewType.ThumbNail);
        JDFExposedMedia xm=(JDFExposedMedia)n.appendMatchingResource("ExposedMedia",JDFNode.EnumProcessUsage.Plate,null);
        xm.appendMedia();
        JDFPreview pv=(JDFPreview)xm.appendElement(ElementName.PREVIEW);
        pv.setURL("http://somehost/pvExposedMedia.png");
        pv.setPreviewType(EnumPreviewType.ThumbNail);
        doc.write2File(sm_dirTestDataTemp+"pv14.jdf", 2,false);
    }
}
