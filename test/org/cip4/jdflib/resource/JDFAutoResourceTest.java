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

/*
 * MediaColorTest.java
 * @author Dietrich Mucha
 * 
 * Copyright (C) 2004 Heidelberger Druckmaschinen AG. All Rights Reserved.
 */
package org.cip4.jdflib.resource;

import junit.framework.TestCase;

import org.cip4.jdflib.auto.JDFAutoInsertSheet.EnumSheetType;
import org.cip4.jdflib.auto.JDFAutoInsertSheet.EnumSheetUsage;
import org.cip4.jdflib.core.AttributeName;
import org.cip4.jdflib.core.ElementName;
import org.cip4.jdflib.core.JDFDoc;
import org.cip4.jdflib.core.JDFException;
import org.cip4.jdflib.core.VString;
import org.cip4.jdflib.core.JDFElement.EnumNamedColor;
import org.cip4.jdflib.core.JDFResourceLink.EnumUsage;
import org.cip4.jdflib.core.KElement.EnumValidationLevel;
import org.cip4.jdflib.datatypes.JDFXYPair;
import org.cip4.jdflib.node.JDFNode;
import org.cip4.jdflib.node.JDFNode.EnumProcessUsage;
import org.cip4.jdflib.resource.JDFResource.EnumResStatus;
import org.cip4.jdflib.resource.JDFResource.EnumResourceClass;
import org.cip4.jdflib.resource.devicecapability.JDFModule;
import org.cip4.jdflib.resource.intent.JDFMediaIntent;
import org.cip4.jdflib.resource.process.JDFColor;
import org.cip4.jdflib.resource.process.JDFColorPool;
import org.cip4.jdflib.resource.process.JDFFileSpec;
import org.cip4.jdflib.resource.process.JDFInsertSheet;
import org.cip4.jdflib.resource.process.JDFRunList;
import org.cip4.jdflib.resource.process.postpress.JDFScore;
import org.cip4.jdflib.resource.process.postpress.JDFScore.EnumScoreSide;
import org.cip4.jdflib.span.JDFStringSpan;


public class JDFAutoResourceTest extends TestCase
{
    public void testRunList()
    {
        JDFDoc d=new JDFDoc(ElementName.JDF);
        JDFNode r=d.getJDFRoot();
        JDFRunList rl=(JDFRunList) r.addResource("RunList", null, EnumUsage.Input, null, null, null, null);
        JDFInsertSheet is1=rl.appendInsertSheet();
        is1.setSheetType(EnumSheetType.SeparatorSheet);
        is1.setSheetUsage(EnumSheetUsage.Slip);
        JDFInsertSheet is2=rl.appendInsertSheet();
        is2.setSheetType(EnumSheetType.SeparatorSheet);
        is2.setSheetUsage(EnumSheetUsage.Slip);
        assertNotSame("two insert sheets",is1,is2);
        
        rl.appendLayoutElement(); //1
        assertTrue("runlist valid",rl.isValid(EnumValidationLevel.Complete));
        boolean b1=false;
        try{
            rl.appendLayoutElement();
        }
        catch(JDFException e){
            b1=true;
        }
        assertTrue("only one layoutelement possible",b1);
    }
    
    public void testEnumerations()
    {
        JDFDoc d=new JDFDoc(ElementName.JDF);
        JDFNode r=d.getJDFRoot();
        JDFColorPool cp=(JDFColorPool) r.addResource("ColorPool", null, EnumUsage.Input, null, null, null, null);
        JDFColor col=cp.appendColor();
        col.setColorName(EnumNamedColor.Red);
        assertTrue("named color get",col.getColorName()==EnumNamedColor.Red);
        assertTrue("named color get raw",col.getAttribute(AttributeName.COLORNAME)=="Red"); 
    }
    
    public void testBinderySignature()
    {
        JDFDoc d=new JDFDoc(ElementName.JDF);
        JDFNode n=d.getJDFRoot();
        JDFResource bs = n.addResource(ElementName.BINDERYSIGNATURE, null, null, null, null, null, null); 
        assertEquals("bs class",bs.getResourceClass(),EnumResourceClass.Parameter);
        bs=n.addResource(ElementName.BINDERYSIGNATURE, JDFResource.EnumResourceClass.Parameter, null, null, n, null, null);
        assertEquals("bs class old style",bs.getResourceClass(),EnumResourceClass.Parameter);
        assertTrue(bs.validClass());
    }
    
    public void testMediaIntent()
    {
        JDFDoc d=new JDFDoc(ElementName.JDF);
        JDFNode n=d.getJDFRoot();
        n.setType("Product",true);
        
        JDFMediaIntent mi = (JDFMediaIntent) n.appendMatchingResource(ElementName.MEDIAINTENT,EnumProcessUsage.AnyInput,null);
        JDFStringSpan sb=mi.appendStockBrand();
        sb.setActual("abc foo");
        sb.setPreferred("abc foo");
        assertTrue("valid StockBrand",sb.isValid(EnumValidationLevel.Complete));
        assertEquals(mi.getValidClass(),EnumResourceClass.Intent);
        assertTrue(mi.validClass());
    }
    
    public void testDevice()
    {
        JDFDoc d=new JDFDoc(ElementName.JDF);
        JDFNode n=d.getJDFRoot();
        n.setType("Stitching",true);
        JDFDevice dev=(JDFDevice) n.appendMatchingResource(ElementName.DEVICE,EnumProcessUsage.AnyInput,null);
        dev.setResStatus(EnumResStatus.Available,true);
        dev.setKnownLocalizations(new VString("De", null));
        dev.setSerialNumber("12345");
        dev.setSecureJMFURL("http://fififi");
        JDFModule m=dev.appendModule();
//      m.setModuleIndex(0);
        m.setModelDescription("1234");
        JDFIconList il=dev.appendIconList();
        assertFalse("empty iconlist",il.isValid(EnumValidationLevel.Complete));
        assertTrue("empty iconlist",il.isValid(EnumValidationLevel.Incomplete));
        JDFIcon ic=il.appendIcon();
        ic.setSize(new JDFXYPair(200,200));
        ic.setBitDepth(8);
        JDFFileSpec fs=ic.appendFileSpec();
        fs.setURL("file:///this.ico");
        
        assertTrue("icon valid",ic.isValid(EnumValidationLevel.Complete));
        assertTrue("iconlist valid",il.isValid(EnumValidationLevel.Complete));
        assertTrue("mod valid",m.isValid(EnumValidationLevel.Complete));
        assertTrue("dev valid",dev.isValid(EnumValidationLevel.Complete));
        assertTrue(dev.validClass());

    }
    
    // test coverapplication and score
    public void testScore()
    {
        
        JDFDoc d=new JDFDoc(ElementName.JDF);
        JDFNode n=d.getJDFRoot();
        n.setType("CoverApplication",true);
        JDFCoverApplicationParams cap=(JDFCoverApplicationParams) n.appendMatchingResource(ElementName.COVERAPPLICATIONPARAMS,EnumProcessUsage.AnyInput,null);
        JDFScore score=cap.appendScore();
        score.setSide(EnumScoreSide.FromInside);
        score.setOffset(1234.5);
        assertTrue("score 1",score.isValid(EnumValidationLevel.Complete));
        score=cap.appendScore();
        score.setSide(EnumScoreSide.FromOutside);
        assertTrue("score 2",score.isValid(EnumValidationLevel.Complete));
        assertTrue("cap",cap.isValid(EnumValidationLevel.Complete));
        // we know that we are incomplete since we have missing resources
        assertTrue("n",n.isValid(EnumValidationLevel.Incomplete));
        
    }
    
}
