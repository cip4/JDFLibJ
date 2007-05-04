/*
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

package org.cip4.jdflib.resource;

import java.io.File;

import org.cip4.jdflib.JDFTestCaseBase;
import org.cip4.jdflib.auto.JDFAutoPart.EnumSide;
import org.cip4.jdflib.auto.JDFAutoRegisterMark.EnumMarkUsage;
import org.cip4.jdflib.core.AttributeName;
import org.cip4.jdflib.core.ElementName;
import org.cip4.jdflib.core.JDFDoc;
import org.cip4.jdflib.core.JDFElement;
import org.cip4.jdflib.core.JDFException;
import org.cip4.jdflib.core.JDFParser;
import org.cip4.jdflib.core.VElement;
import org.cip4.jdflib.core.VString;
import org.cip4.jdflib.core.JDFElement.EnumVersion;
import org.cip4.jdflib.core.JDFResourceLink.EnumUsage;
import org.cip4.jdflib.core.KElement.EnumValidationLevel;
import org.cip4.jdflib.datatypes.JDFMatrix;
import org.cip4.jdflib.datatypes.JDFXYPair;
import org.cip4.jdflib.node.JDFNode;
import org.cip4.jdflib.node.JDFNode.EnumProcessUsage;
import org.cip4.jdflib.node.JDFNode.EnumType;
import org.cip4.jdflib.resource.JDFResource.EnumPartIDKey;
import org.cip4.jdflib.resource.process.JDFContentObject;
import org.cip4.jdflib.resource.process.JDFLayout;
import org.cip4.jdflib.resource.process.JDFMedia;
import org.cip4.jdflib.resource.process.JDFRegisterMark;
import org.cip4.jdflib.resource.process.JDFRunList;
import org.cip4.jdflib.resource.process.JDFSurface;
import org.cip4.jdflib.resource.process.postpress.JDFSheet;

/**
 * all kinds of fun tests around JDF 1.2 vs JDF 1.3 Layouts
 * also some tests for automated layout
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
        JDFElement.setLongID(false);       
        doc=new JDFDoc("JDF");
        n=doc.getJDFRoot();
        n.setType(EnumType.Imposition);
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
    //////////////////////////////////////////////////////////////////////////

    public void testMedia()
    {
        JDFLayout lo=(JDFLayout) n.appendMatchingResource(ElementName.LAYOUT,EnumProcessUsage.AnyInput,null);
        lo.appendMedia();
        JDFMedia m2=lo.appendMedia();
        assertNotNull("2. Media ok",m2);
        assertEquals(m2, lo.getMedia(1));
        assertEquals(m2, lo.getCreateMedia(1));
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
        si.setName("Sig1");
        assertEquals("signature name",si.getLocalName(),ElementName.SIGNATURE);
        assertFalse(si.hasAttribute(AttributeName.CLASS));
        si=lo.appendSignature();
        si.setName("Sig2");
        assertEquals("num sigs",2,lo.numSignatures());
        assertEquals("signature name",si.getLocalName(),ElementName.SIGNATURE);

        JDFSheet sh=si.appendSheet();
        sh.setName("Sheet2_1");
        sh.makeRootResource(null,null,true); // see what happens with refelements
        sh=si.appendSheet();
        sh.setName("Sheet2_2");
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
        assertEquals(lo.numSignatures(), lo.getSignatureVector().size());

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
        assertEquals(lo.numSignatures(), lo.getSignatureVector().size());
    }   

    /////////////////////////////////////////////////////

    public void testFixToNewLayout()
    {
        testBuildOldLayout();
        n.fixVersion(EnumVersion.Version_1_3);
        JDFLayout lo=(JDFLayout) n.getMatchingResource(ElementName.LAYOUT,EnumProcessUsage.AnyInput,null,0);
        assertTrue(JDFLayout.isNewLayout(lo));
        JDFSignature si=lo.getSignature(0);
        assertEquals(si.getSignatureName(), "Sig1");
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
    /////////////////////////////////////////////////////
    public void testGetSignatureVector_Old()
    {
        testBuildOldLayout();
        JDFLayout lo=(JDFLayout) n.getMatchingResource(ElementName.LAYOUT,EnumProcessUsage.AnyInput,null,0);
        VElement v=lo.getSignatureVector();
        JDFSignature sig=(JDFSignature) v.elementAt(0);
        assertEquals(sig.getSignatureName(), sig.getName());
        assertEquals(sig.getSignatureName(), "Sig1");
        JDFSignature sig2=(JDFSignature) v.elementAt(1);
        assertEquals(sig2.getSignatureName(), sig2.getName());
        assertEquals(sig2.getSignatureName(), "Sig2");
        VElement vSheet=sig2.getSheetVector();
        JDFSheet s1=(JDFSheet) vSheet.elementAt(1); // don't try 0 it will fail because it is referenced...
        assertEquals(s1.getSignatureName(), "Sig2");
        assertEquals(s1.getSheetName(), "Sheet2_2");
        JDFSurface su=s1.getCreateBackSurface();
        assertEquals(su.getSignatureName(), "Sig2");
        assertEquals(su.getSheetName(), "Sheet2_2");
        assertEquals(s1.getSurfaceVector().elementAt(0), su);

    }
    /////////////////////////////////////////////////////
    public void testGetSignatureName_Old()
    {
        testBuildOldLayout();
        JDFLayout lo=(JDFLayout) n.getMatchingResource(ElementName.LAYOUT,EnumProcessUsage.AnyInput,null,0);
        JDFSignature sig=lo.getSignature(0);
        assertEquals(sig.getSignatureName(), sig.getName());
        assertEquals(sig.getSignatureName(), "Sig1");
        JDFSignature sig2=lo.getSignature(1);
        assertEquals(sig2.getSignatureName(), sig2.getName());
        assertEquals(sig2.getSignatureName(), "Sig2");
        JDFSheet s1=sig2.getSheet(1); // don't try 0 it will fail because it is referenced...
        assertEquals(s1.getSignatureName(), "Sig2");
        assertEquals(s1.getSheetName(), "Sheet2_2");
        JDFSurface su=s1.getCreateBackSurface();
        assertEquals(su.getSignatureName(), "Sig2");
        assertEquals(su.getSheetName(), "Sheet2_2");

    }
    /////////////////////////////////////////////////////
    public void testGetSignatureName_New()
    {
        testBuildNewLayout();
        JDFLayout lo=(JDFLayout) n.getMatchingResource(ElementName.LAYOUT,EnumProcessUsage.AnyInput,null,0);
        JDFSignature sig=lo.getSignature(0);
        assertEquals(sig.getSignatureName(), "SignatureName1");
        JDFSignature sig2=lo.getSignature(1);
        assertEquals(sig2.getSignatureName(), "SignatureName2");
        JDFSheet s1=sig2.getSheet(1); // don't try 0 it will fail because it is referenced...
        assertEquals(s1.getSignatureName(), "SignatureName2");
        assertEquals(s1.getSheetName(), "SheetName2");
        JDFSurface su=s1.getCreateBackSurface();
        assertEquals(su.getSignatureName(), "SignatureName2");
        assertEquals(su.getSheetName(), "SheetName2");        
    }

    /////////////////////////////////////////////////////
    public void testGetSignatureVector_New()
    {
        testBuildNewLayout();
        JDFLayout lo=(JDFLayout) n.getMatchingResource(ElementName.LAYOUT,EnumProcessUsage.AnyInput,null,0);
        VElement v=lo.getSignatureVector();
        JDFSignature sig=(JDFSignature) v.elementAt(0);
        assertEquals(sig.getSignatureName(), "SignatureName1");
        JDFSignature sig2=(JDFSignature) v.elementAt(1);
        assertEquals(sig2.getSignatureName(), "SignatureName2");
        VElement vSheet=sig2.getSheetVector();
        JDFSheet s1=(JDFSheet) vSheet.elementAt(1); // don't try 0 it will fail because it is referenced...
        assertEquals(s1.getSignatureName(), "SignatureName2");
        assertEquals(s1.getSheetName(), "SheetName2");
        JDFSurface su=s1.getCreateBackSurface();
        assertEquals(su.getSignatureName(), "SignatureName2");
        assertEquals(su.getSheetName(), "SheetName2");        
        assertEquals(s1.getSurfaceVector().elementAt(0), su);
    }

    /////////////////////////////////////////////////////

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
    public void testGeneratedObject() throws Exception
    {
        n=doc.getJDFRoot();
        JDFLayout lo=(JDFLayout) n.addResource("Layout",null,EnumUsage.Input, null, null, null,null);
        JDFRunList rlo=(JDFRunList) n.addResource("RunList",null,EnumUsage.Output, null, null, null,null);
        rlo.setFileURL("output.pdf");

        lo.appendXMLComment("This is a simple horizontal slug line\nAnchor specifies which of the 9 coordinates is the 0 point for the coordinate system specified in the CTM\nThis slugline describes error and endtime in the lower left corner of the scb", null);
        JDFMarkObject mark=lo.appendMarkObject();
        mark.setCTM(new JDFMatrix("1 0 0 1 0 0"));
        JDFJobField jf=mark.appendJobField();
        jf.setShowList(new VString("Error EndTime"," "));

        lo.appendXMLComment("This is a simple vertical slug line\nAnchor specifies which of the 9 coordinates is the 0 point for the coordinate system specified in the CTM\nThis slugline describes the operator name along the right side of the sheet text from top to bottom\nthe slug line (top right of the slug cs) is anchored in the bottom right of the sheet.\nNote that the coordinates in the ctm are guess work. the real coordinates are left as an exercise for the reader;-)", null);
        mark=lo.appendMarkObject();
        mark.setCTM(new JDFMatrix("0 1 -1 0 555 444"));
        jf=mark.appendJobField();
        jf.setShowList(new VString("Operator"," "));
        JDFDeviceMark dm=jf.appendDeviceMark();
        dm.setAttribute("Anchor", "TopRight");
        dm.setFont("Arial");
        dm.setFontSize(10);

        lo.appendXMLComment("This is a formatted vertical slug line\nAnchor specifies which of the 9 coordinates is the 0 point for the coordinate system specified in the CTM\nThis slugline describes a formatted line along the left side of the sheet text from top to bottom\nthe slug line (top left) is anchored in the bottom left of the sheet.\nThe text is defined in @Format with the sequence in ShowList defining the 5 placeholders marked by %s or %i\nAn example instance would be: \"This Cyan plate of Sheet1 was proudly produced by Joe Cool! Resolution: 600 x 600\"\nNote that the coordinates in the ctm are guess work. the real coordinates are left as an exercise for the reader;-)", null);
        mark=lo.appendMarkObject();
        mark.setCTM(new JDFMatrix("0 1 -1 0 0 0"));
        jf=mark.appendJobField();
        jf.setShowList(new VString("Separation SheetName Operator ResolutionX ResolutionY"," "));
        jf.setAttribute("Format", "This %s plate of %s was proudly produced by %s!\nResolution: %i x %i");
        dm=jf.appendDeviceMark();
        dm.setAttribute("Anchor", "TopLeft");
        dm.setFont("Arial");
        dm.setFontSize(10);

        lo.appendXMLComment("This is a positioned registermark\nthe center of the mark is translated by 666 999\n the JobField is empty and serves aa a Marker that no external Content is requested", null);
        mark=lo.appendMarkObject();
        mark.setCTM(new JDFMatrix("1 0 0 1 666 999"));
        jf=mark.appendJobField();
        mark.appendXMLComment("The coordinate system origin is defined by the anchor in the center, i.e. 0 0\n the separartions are excluding black", null);
        JDFRegisterMark rm=mark.appendRegisterMark();
        rm.setMarkType("Arc Cross");
        rm.setMarkUsage(EnumMarkUsage.Color);
        rm.setCenter(new JDFXYPair("0 0"));
        rm.setSeparations(new VString("Cyan Magent Yellow"," "));
        dm=jf.appendDeviceMark();
        dm.setAttribute("Anchor", "CenterCenter");

        doc.write2File(sm_dirTestDataTemp+"generatedObject.jdf", 2, false);

    }
    /////////////////////////////////////////////////////


}