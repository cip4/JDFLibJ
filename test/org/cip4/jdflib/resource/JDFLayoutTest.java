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

package org.cip4.jdflib.resource;

import java.io.File;

import org.cip4.jdflib.JDFTestCaseBase;
import org.cip4.jdflib.auto.JDFAutoPart.EnumSide;
import org.cip4.jdflib.core.AttributeName;
import org.cip4.jdflib.core.ElementName;
import org.cip4.jdflib.core.JDFDoc;
import org.cip4.jdflib.core.JDFException;
import org.cip4.jdflib.core.JDFParser;
import org.cip4.jdflib.core.VElement;
import org.cip4.jdflib.core.JDFElement.EnumVersion;
import org.cip4.jdflib.core.JDFResourceLink.EnumUsage;
import org.cip4.jdflib.core.KElement.EnumValidationLevel;
import org.cip4.jdflib.node.JDFNode;
import org.cip4.jdflib.node.JDFNode.EnumProcessUsage;
import org.cip4.jdflib.resource.JDFResource.EnumPartIDKey;
import org.cip4.jdflib.resource.process.JDFContentObject;
import org.cip4.jdflib.resource.process.JDFLayout;
import org.cip4.jdflib.resource.process.JDFRunList;
import org.cip4.jdflib.resource.process.JDFSurface;
import org.cip4.jdflib.resource.process.postpress.JDFSheet;

/**
 * all kinds of fun tests around JDF 1.2 vs JDF 1.3 Layouts
 *
 */
public class JDFLayoutTest extends JDFTestCaseBase
{
    
    private JDFDoc doc=null;
    private JDFNode n=null;
    private JDFRunList rl=null;
    
    public void setUp() throws Exception
    {
        super.setUp();
        doc=new JDFDoc("JDF");
        n=doc.getJDFRoot();
        n.setType("Imposition",true);
        rl=(JDFRunList) n.appendMatchingResource(ElementName.RUNLIST,EnumProcessUsage.AnyInput,null);
    }

    //////////////////////////////////////////////////////////////////////////
    
    public void testIsNewLayout()
    {
        assertEquals("version ok",n.getVersion(false),EnumVersion.Version_1_3);
        JDFLayout lo=(JDFLayout) n.appendMatchingResource(ElementName.LAYOUT,EnumProcessUsage.AnyInput,null);
        assertTrue("lo 1.3",JDFLayout.isNewLayout(lo));
        n.setVersion(EnumVersion.Version_1_2);
        assertFalse("lo 1.3",JDFLayout.isNewLayout(lo));
        lo.addPartition(EnumPartIDKey.SheetName,"Sheet1");
        assertTrue("lo 1.3",JDFLayout.isNewLayout(lo));  
        assertFalse("l no layout",JDFLayout.isNewLayout(rl));
    }    
    
    /**
     * build a 1.2 layout using appendsignature etc
     *
     */
    public void testBuildOldLayout()
    {
        n.setVersion(EnumVersion.Version_1_2);
        JDFLayout lo=(JDFLayout) n.appendMatchingResource(ElementName.LAYOUT,EnumProcessUsage.AnyInput,null);
        assertFalse("lo 1.3",JDFLayout.isNewLayout(lo));
        JDFSignature si=lo.appendSignature();
        assertEquals("signature name",si.getLocalName(),ElementName.SIGNATURE);
        assertFalse(si.hasAttribute(AttributeName.CLASS));
        si=lo.appendSignature();
        assertEquals("num sigs",2,lo.numSignatures());
        assertEquals("signature name",si.getLocalName(),ElementName.SIGNATURE);
        
        JDFSheet sh=si.appendSheet();
        sh.makeRootResource(null,null,true); // see what happens with refelements
        sh=si.appendSheet();
        assertEquals("num sheets",2,si.numSheets());
        assertEquals("sheet name",sh.getLocalName(),ElementName.SHEET);
        sh=si.getCreateSheet(4);
        assertEquals("num sheets",3,si.numSheets());
        assertEquals("sheet name",sh.getLocalName(),ElementName.SHEET);
        sh=si.getSheet(2);
        assertEquals("num sheets",3,si.numSheets());
        assertEquals("sheet name",sh.getLocalName(),ElementName.SHEET);

        JDFSurface su=sh.appendFrontSurface();
        assertEquals("num surfaces",1,sh.numSurfaces());
        assertEquals("sheet name",su.getLocalName(),ElementName.SURFACE);
        assertTrue("hasfrontSurface",sh.hasFrontSurface());
        su.appendContentObject();
        su.appendMarkObject();
        su.appendContentObject();
        su.appendContentObject();
        
        su=sh.appendBackSurface();
        su.makeRootResource(null,null,true);
        su.appendContentObject();
        su.appendMarkObject();
        su.appendContentObject();
        su.appendContentObject();
        su.appendMarkObject();
        assertEquals("num surfaces",2,sh.numSurfaces());
        assertEquals("sheet name",su.getLocalName(),ElementName.SURFACE);
        assertTrue("hasBackSurface",sh.hasBackSurface());
 
        try{
            sh.appendBackSurface();  
            fail("append second surface");
        }
        catch (JDFException e)
        {/* nop */}
        
        si=lo.getCreateSignature(4);
        assertEquals("num sigs",3,lo.numSignatures());
        assertEquals("signature name",si.getLocalName(),ElementName.SIGNATURE);
        si=lo.getSignature(2);
        assertEquals("num sigs",3,lo.numSignatures());
        assertEquals("signature name",si.getLocalName(),ElementName.SIGNATURE);
        si=lo.getSignature(5);
        assertNull("si null",si);
        
    }    
    
    /**
     * build a 1.3 layout using appendsignature etc
     *
     */
    public void testBuildNewLayout()
    {
        JDFLayout lo=(JDFLayout) n.appendMatchingResource(ElementName.LAYOUT,EnumProcessUsage.AnyInput,null);
        assertTrue("lo 1.3",JDFLayout.isNewLayout(lo));
        JDFSignature si=lo.appendSignature();
        assertEquals("signature name",si.getLocalName(),ElementName.LAYOUT);
        si=lo.appendSignature();
        assertEquals("num sigs",2,lo.numSignatures());
        assertEquals("signature name",si.getLocalName(),ElementName.LAYOUT);
        
        JDFSheet sh=si.appendSheet();
        sh=si.appendSheet();
        assertEquals("num sheets",2,si.numSheets());
        assertEquals("sheet name",sh.getLocalName(),ElementName.LAYOUT);
        sh=si.getCreateSheet(4);
        assertEquals("num sheets",3,si.numSheets());
        assertEquals("sheet name",sh.getLocalName(),ElementName.LAYOUT);
        sh=si.getSheet(2);
        assertEquals("num sheets",3,si.numSheets());
        assertEquals("sheet name",sh.getLocalName(),ElementName.LAYOUT);

        JDFSurface su=sh.appendFrontSurface();
        assertEquals("num surfaces",1,sh.numSurfaces());
        assertEquals("sheet name",su.getLocalName(),ElementName.LAYOUT);
        assertTrue("hasfrontSurface",sh.hasFrontSurface());
        
        su=sh.appendBackSurface();
        assertEquals("num surfaces",2,sh.numSurfaces());
        assertEquals("sheet name",su.getLocalName(),ElementName.LAYOUT);
        
        try{
            sh.appendBackSurface();   
            fail("no two back surfaces");
        }
        catch (JDFException e)
        {/* nop */}
        
        si=lo.getCreateSignature(4);
        assertEquals("num sigs",3,lo.numSignatures());
        assertEquals("signature name",si.getLocalName(),ElementName.LAYOUT);
        si=lo.getSignature(2);
        assertEquals("num sigs",3,lo.numSignatures());
        assertEquals("signature name",si.getLocalName(),ElementName.LAYOUT);
        si=lo.getSignature(5);
        assertNull("si null",si);
        assertTrue("layout valid",lo.isValid(EnumValidationLevel.Complete));
    }   
    
    /////////////////////////////////////////////////////
    
    public void testFixToNewLayout()
    {
        testBuildOldLayout();
        n.fixVersion(EnumVersion.Version_1_3);
        JDFLayout lo=(JDFLayout) n.getMatchingResource(ElementName.LAYOUT,EnumProcessUsage.AnyInput,null,0);
        assertTrue(JDFLayout.isNewLayout(lo));
        JDFSignature si=lo.getSignature(0);
        assertFalse(si.hasAttribute(AttributeName.CLASS));
    }
    /////////////////////////////////////////////////////
    
    public void testFixFromNewLayout()
    {
        testBuildNewLayout();
        n.fixVersion(EnumVersion.Version_1_2);
        JDFLayout lo=(JDFLayout) n.getMatchingResource(ElementName.LAYOUT,EnumProcessUsage.AnyInput,null,0);
        assertFalse(JDFLayout.isNewLayout(lo));
        JDFSignature si=lo.getSignature(0);
        assertEquals(si.getLocalName(),ElementName.SIGNATURE);
    }
    /////////////////////////////////////////////////////
    public void testFixFromFlatNewLayout()
    {
        n.setVersion(EnumVersion.Version_1_3);
        JDFLayout loNew=(JDFLayout) n.appendMatchingResource(ElementName.LAYOUT,EnumProcessUsage.AnyInput,null);
        JDFContentObject co1=loNew.appendContentObject();
        
        n.fixVersion(EnumVersion.Version_1_2);
        JDFLayout lo=(JDFLayout) n.getMatchingResource(ElementName.LAYOUT,EnumProcessUsage.AnyInput,null,0);
        assertFalse(JDFLayout.isNewLayout(lo));
        JDFSignature si=lo.getSignature(0);
        assertEquals(si.getLocalName(),ElementName.SIGNATURE);
        JDFSheet sh=si.getSheet(0);
        JDFSurface su=sh.getSurface(EnumSide.Front);
        assertEquals(co1,su.getContentObject(0));
    }
    /////////////////////////////////////////////////////
    public void testFixFromSheetNewLayout()
    {
        n.setVersion(EnumVersion.Version_1_3);
        JDFLayout loNew=(JDFLayout) n.appendMatchingResource(ElementName.LAYOUT,EnumProcessUsage.AnyInput,null);
        loNew=(JDFLayout) loNew.addPartition(EnumPartIDKey.SheetName,"s1");
        JDFContentObject co1=loNew.appendContentObject();
        
        n.fixVersion(EnumVersion.Version_1_2);
        JDFLayout lo=(JDFLayout) n.getMatchingResource(ElementName.LAYOUT,EnumProcessUsage.AnyInput,null,0);
        assertFalse(JDFLayout.isNewLayout(lo));
        JDFSignature si=lo.getSignature(0);
        assertEquals(si.getLocalName(),ElementName.SIGNATURE);
        JDFSheet sh=si.getSheet(0);
        JDFSurface su=sh.getSurface(EnumSide.Front);
        assertEquals(co1,su.getContentObject(0));
    }
    /////////////////////////////////////////////////////
    public void testFixFromSurfaceNewLayout()
    {
        n.setVersion(EnumVersion.Version_1_3);
        JDFLayout loNew=(JDFLayout) n.appendMatchingResource(ElementName.LAYOUT,EnumProcessUsage.AnyInput,null);
        loNew=(JDFLayout) loNew.addPartition(EnumPartIDKey.Side,"Back");
        JDFContentObject co1=loNew.appendContentObject();
        
        n.fixVersion(EnumVersion.Version_1_2);
        JDFLayout lo=(JDFLayout) n.getMatchingResource(ElementName.LAYOUT,EnumProcessUsage.AnyInput,null,0);
        assertFalse(JDFLayout.isNewLayout(lo));
        JDFSignature si=lo.getSignature(0);
        assertEquals(si.getLocalName(),ElementName.SIGNATURE);
        JDFSheet sh=si.getSheet(0);
        JDFSurface su=sh.getSurface(EnumSide.Front);
        assertNull(su);
        su=sh.getSurface(EnumSide.Back);
        assertEquals(co1,su.getContentObject(0));
    }
    /////////////////////////////////////////////////////
    
    public void testGetPlacedObjectVector()
    {
        testBuildOldLayout();
        JDFLayout lo=(JDFLayout) n.getMatchingResource(ElementName.LAYOUT,EnumProcessUsage.AnyInput,null,0);
        JDFSurface su=lo.getSignature(1).getSheet(2).getSurface(EnumSide.Front);
        VElement v=su.getPlacedObjectVector();
        assertEquals(v.size(),4);
        assertTrue(v.elementAt(0) instanceof JDFContentObject);
        assertTrue(v.elementAt(1) instanceof JDFMarkObject);
        assertTrue(v.elementAt(2) instanceof JDFContentObject);
        assertTrue(v.elementAt(3) instanceof JDFContentObject);
        
     }
    /////////////////////////////////////////////////////
    public void testGetLayoutLeaves()
    {
        testBuildOldLayout();
        JDFLayout lo=(JDFLayout) n.getMatchingResource(ElementName.LAYOUT,EnumProcessUsage.AnyInput,null,0);
        VElement leaves=lo.getLayoutLeaves(false);
        assertEquals(leaves.size(), 2);
        JDFSignature si=lo.getSignature(1);
        leaves=si.getLayoutLeaves(false);
        assertEquals(leaves.size(), 2);
        JDFSheet sh=si.getSheet(2);
        leaves=sh.getLayoutLeaves(false);
        assertEquals(leaves.size(), 2);
 
     }
   
    public void testFixVersionProblem()
    {
        JDFParser p=new JDFParser();
        JDFDoc d=p.parseFile(sm_dirTestData+File.separator+"FixVersionProblem.jdf");
        assertNotNull("FixVersionProblem exists",d);
        n=d.getJDFRoot();
        n.fixVersion(EnumVersion.Version_1_2);
        JDFLayout lo=(JDFLayout) n.getResourcePool().getElement(ElementName.LAYOUT, null, 0);
        assertEquals(lo.numChildElements("Signature",null),1);       
    }
    /////////////////////////////////////////////////////
    
    
    /*
     * GeneratedObject

CTM or Position
Position: See ImageShift PositionX and PositionY, Shift (Margins) – See ShiftFront RelativeShift?

Anchor Point (same as position ll, ul, cc, spine…) (if CTM is given)
Orientation (rotation, matrix or ll, ul, …)
Contents  Format/Template
JobField (Replace, DynamicField?)
SeparationList
Mark References (FoldMark, CIE, …)

     */
    public void testGeneratedObject()
    {
        JDFDoc d=new JDFDoc("JDF");
        n=d.getJDFRoot();
        JDFLayout lo=(JDFLayout) n.addResource("Layout",null,EnumUsage.Input, null, null, null,null);
        
        JDFMarkObject mark=lo.appendMarkObject();
        JDFJobField jf=mark.appendJobField();
        
        
        d.write2File(sm_dirTestDataTemp+"generatedObject.jdf", 2, false);
        
    }
    /////////////////////////////////////////////////////
    /////////////////////////////////////////////////////
    
}