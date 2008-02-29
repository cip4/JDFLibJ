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
package org.cip4.jdflib.goldenticket;

import org.cip4.jdflib.JDFTestCaseBase;
import org.cip4.jdflib.auto.JDFAutoConventionalPrintingParams.EnumWorkStyle;
import org.cip4.jdflib.core.JDFAudit;
import org.cip4.jdflib.core.JDFDoc;
import org.cip4.jdflib.core.JDFElement;
import org.cip4.jdflib.core.VString;
import org.cip4.jdflib.core.JDFElement.EnumVersion;
import org.cip4.jdflib.core.KElement.EnumValidationLevel;
import org.cip4.jdflib.datatypes.JDFAttributeMap;
import org.cip4.jdflib.datatypes.VJDFAttributeMap;
import org.cip4.jdflib.node.JDFNode;
import org.cip4.jdflib.node.JDFNode.EnumType;
import org.cip4.jdflib.resource.JDFResource.EnumPartIDKey;


public class MISCPGoldenTicketTest extends JDFTestCaseBase
{
    String agentName;

    /////////////////////////////////////////////////////////////////////////////
    
    public void testMISCPGrayBox()
    {
        VJDFAttributeMap vMap=new VJDFAttributeMap();
        JDFAttributeMap map=new JDFAttributeMap();
        map.put(EnumPartIDKey.SignatureName,"Sig1");
        map.put(EnumPartIDKey.SheetName,"Sheet1");
        map.put(EnumPartIDKey.Side,"Front");
        vMap.add(new JDFAttributeMap(map));
        map.put(EnumPartIDKey.Side,"Back");
        vMap.add(new JDFAttributeMap(map));
        MISCPGoldenTicket cpGoldenTicket=new MISCPGoldenTicket(1,null,2,1,true,vMap);
        cpGoldenTicket.nCols=4;
        
        cpGoldenTicket.assign(null);
        JDFNode node = cpGoldenTicket.getNode();
        cpGoldenTicket.write2File(sm_dirTestDataTemp+"GoldenTicket_Manager_MISCPS_1_GB.jdf", 2);
        assertTrue(node.getICSVersions(false).contains("Base_L2-1.3"));
        assertTrue(node.getICSVersions(false).contains("JMF_L2-1.3"));
        assertTrue(node.getICSVersions(false).contains("MIS_L1-1.3"));
        assertTrue(node.getICSVersions(false).contains("MISCPS_L1-1.3"));
        assertTrue(node.isValid(EnumValidationLevel.Complete));
        
        
        cpGoldenTicket.execute(null,true,true,1000,90);
        node = cpGoldenTicket.getNode();
        cpGoldenTicket.write2File(sm_dirTestDataTemp+"GoldenTicket_Worker_MISCPS_1_GB.jdf", 2);
        assertTrue(node.getICSVersions(false).contains("Base_L2-1.3"));
        assertTrue(node.getICSVersions(false).contains("JMF_L2-1.3"));
        assertTrue(node.getICSVersions(false).contains("MIS_L1-1.3"));
        assertTrue(node.getICSVersions(false).contains("MISCPS_L1-1.3"));
        assertTrue(node.isValid(EnumValidationLevel.Complete));
        
        cpGoldenTicket.assign(null);
        VJDFAttributeMap mapSingle=new VJDFAttributeMap();
        map.put(EnumPartIDKey.Side,"Front");
        mapSingle.add(map);
        cpGoldenTicket.schedule(mapSingle, 6,2);
        map.put(EnumPartIDKey.Side,"Back");
        cpGoldenTicket.schedule(mapSingle, 8,2);
        
        cpGoldenTicket.write2File(sm_dirTestDataTemp+"GoldenTicket_Manager_MISCPS_1_GB_FrontBack.jdf", 2);
        map.put(EnumPartIDKey.Side,"Front");
        cpGoldenTicket.execute(mapSingle,false,true,1000,90);
        cpGoldenTicket.write2File(sm_dirTestDataTemp+"GoldenTicket_Manager_MISCPS_1_GB_FrontBack_xB.jdf", 2);
        map.put(EnumPartIDKey.Side,"Back");
        cpGoldenTicket.execute(mapSingle,true,false,900,30);
        cpGoldenTicket.write2File(sm_dirTestDataTemp+"GoldenTicket_Manager_MISCPS_1_GB_FrontBack_xBF.jdf", 2);
    }
    
    /////////////////////////////////////////////////////////////////////////////
    
    public void testMISCPGrayBoxSimplexPoster()
    {
        VJDFAttributeMap vMap=new VJDFAttributeMap();
        JDFAttributeMap map=new JDFAttributeMap();
        map.put(EnumPartIDKey.SignatureName,"Sig1");
        map.put(EnumPartIDKey.SheetName,"Sheet1");
        map.put(EnumPartIDKey.Side,"Front");
        vMap.add(new JDFAttributeMap(map));

        MISCPGoldenTicket cpGoldenTicket=new MISCPGoldenTicket(1,null,2,1,true,vMap);
        cpGoldenTicket.nCols=6;
        cpGoldenTicket.workStyle=EnumWorkStyle.Simplex;
        cpGoldenTicket.assign(null);
        
        write3GTFiles(cpGoldenTicket,"MISCPS_SimplexPoster",1000,90);
    }
    /////////////////////////////////////////////////////////////////////////////
    
    public void testMISCPProductGrayBoxBrochure() throws Exception
    {
        VJDFAttributeMap vMap=new VJDFAttributeMap();
        JDFAttributeMap map=new JDFAttributeMap();
        map.put(EnumPartIDKey.SignatureName,"Sig1");
        map.put(EnumPartIDKey.SheetName,"Cover");
        map.put(EnumPartIDKey.Side,"Front");
        vMap.add(new JDFAttributeMap(map));
        map.put(EnumPartIDKey.Side,"Back");
        vMap.add(new JDFAttributeMap(map));

        MISCPGoldenTicket cpGoldenTicket=new MISCPGoldenTicket(1,null,2,2,true,vMap);
        cpGoldenTicket.nCols=6;
        cpGoldenTicket.workStyle=EnumWorkStyle.WorkAndTurn;
     
        ProductGoldenTicket pgt=new ProductGoldenTicket(0,EnumVersion.Version_1_3,0,0);
        pgt.assign(null);
        pgt.createHDCity();
        JDFNode node = pgt.getNode();
        JDFNode nodeCP=node.addJDFNode(EnumType.ProcessGroup);

        cpGoldenTicket.assign(nodeCP);
        write3GTFiles(cpGoldenTicket,"MISCPS_ProductGrayBox",1000,90);
    }


    /**
     * test identical inks using black + text 
     */
    public void testIdenticalInk()
    {
        VString v=new VString("Cyan,Magenta,Yellow,Black,Text",",");
        VString vInk=new VString("Cyan,Magenta,Yellow,Black,Black",",");
        VString vInkProd=new VString("MIS-Ink-4711,MIS-Ink-4712,MIS-Ink-4713,MIS-Ink-4714,MIS-Ink-4714",",");
        VJDFAttributeMap vMap=new VJDFAttributeMap();
        JDFAttributeMap map=new JDFAttributeMap();
        map.put(EnumPartIDKey.SignatureName,"Sig1");
        map.put(EnumPartIDKey.SheetName,"Sheet1");
        map.put(EnumPartIDKey.Side,"Front");
        vMap.add(new JDFAttributeMap(map));

        JDFNode n=new JDFDoc("JDF").getJDFRoot();
        MISCPGoldenTicket cpGoldenTicket=new MISCPGoldenTicket(1,null,2,1,true,vMap);
        cpGoldenTicket.cols=v;
        cpGoldenTicket.inks=vInk;
        cpGoldenTicket.inkProductIDs=vInkProd;
        
        n.setType(JDFNode.EnumType.ConventionalPrinting);
        cpGoldenTicket.assign(n);
        write3GTFiles(cpGoldenTicket, "sameInk.jdf", 3000,200);
     }

    /////////////////////////////////////////////////////////////////////////////
    /* (non-Javadoc)
     * @see org.cip4.jdflib.JDFTestCaseBase#setUp()
     */
    protected void setUp() throws Exception
    {
        agentName=JDFAudit.getStaticAgentName();
        JDFElement.setLongID(false);
        JDFAudit.setStaticAgentName("JDF MISCP golden ticket generator");
        super.setUp();
    }
    /////////////////////////////////////////////////////////////////////////////
    /* (non-Javadoc)
     * @see org.cip4.jdflib.JDFTestCaseBase#tearDown()
     */
    protected void tearDown() throws Exception
    {
        JDFAudit.setStaticAgentName(agentName);
        JDFElement.setLongID(true);
        super.tearDown();
    }

}