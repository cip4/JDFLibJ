/*
 *
 * The CIP4 Software License, Version 1.0
 *
 *
 * Copyright (c) 2001-2010 The International Cooperation for the Integration of 
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

import org.cip4.jdflib.core.ElementName;
import org.cip4.jdflib.core.JDFAudit;
import org.cip4.jdflib.core.JDFDoc;
import org.cip4.jdflib.core.JDFResourceLink;
import org.cip4.jdflib.core.JDFResourceLink.EnumUsage;
import org.cip4.jdflib.core.VString;
import org.cip4.jdflib.node.JDFNode;
import org.cip4.jdflib.resource.JDFResource.EnumResStatus;
import org.cip4.jdflib.resource.process.JDFFileSpec;
import org.cip4.jdflib.resource.process.JDFRunList;
import org.cip4.jdflib.util.UrlUtil;

/**
 * @author Rainer Prosi, Heidelberger Druckmaschinen
 * 
 */
public class IDPGoldenTicketTest extends BaseGoldenTicketTest
{

	// //////////////////////////////////////////////////////////////////////////
	// /
	IDPGoldenTicket idpGoldenTicket;

	/**
	 * 
	 */
	public void testIDPSimple()
	{
		write3GTFiles(idpGoldenTicket, "IDP_Simple");
	}

	/**
	 * 
	 */
	public void testIDPRunList()
	{
		JDFNode n = idpGoldenTicket.getNode();
		JDFRunList rl = (JDFRunList) n.getResource(ElementName.RUNLIST, EnumUsage.Input, null, 0);
		assertNotNull(rl);
		JDFRunList leaf = (JDFRunList) rl.getLeaves(false).elementAt(0);
		JDFFileSpec fs = leaf.getLayoutElement().getFileSpec();
		assertEquals(fs.getURL(), UrlUtil.normalize(idpGoldenTicket.m_pdfFile));

	}

	/**
	 * 
	 */
	public void testIDPHoldPipeRIP()
	{
		JDFDoc jdfDoc = new JDFDoc("JDF");
		JDFNode n = jdfDoc.getJDFRoot();
		n.setCombined(new VString("LayoutPreparation Interpreting Rendering DigitalPrinting", null));
		JDFRunList rl0 = (JDFRunList) n.getCreateResource(ElementName.RUNLIST, EnumUsage.Input, 0);
		JDFResourceLink rll0 = n.getLink(rl0, EnumUsage.Input);
		rll0.setCombinedProcessIndex(0);
		JDFRunList rl = (JDFRunList) n.addResource(ElementName.RUNLIST, EnumUsage.Output);
		rl.setResStatus(EnumResStatus.Unavailable, true);
		rl.setPipeProtocol("Internal");
		JDFResourceLink rll = n.getLink(rl, EnumUsage.Output);
		int pos = n.getTypes().index("Rendering");
		rll.setCombinedProcessIndex(pos);
		JDFResourceLink rll2 = n.ensureLink(rl, EnumUsage.Input, null);
		rll2.setCombinedProcessIndex(pos + 1);
		rll2.setMinStatus(EnumResStatus.Available);
		jdfDoc.write2File(sm_dirTestDataTemp + "HoldRIP.jdf", 2, false);
		rll2.setMinStatus(EnumResStatus.Unavailable);
		rll2.setPipeResume(5);
		jdfDoc.write2File(sm_dirTestDataTemp + "PipeRIP.jdf", 2, false);
	}

	/**
	 * TODO
	 */
	public void testIDPHoldQueueRIP()
	{
		JDFDoc jdfDoc = new JDFDoc("JDF");
		JDFNode n = jdfDoc.getJDFRoot();
		n.setCombined(new VString("LayoutPreparation Interpreting Rendering DigitalPrinting", null));
		JDFRunList rl0 = (JDFRunList) n.getCreateResource(ElementName.RUNLIST, EnumUsage.Input, 0);
		JDFResourceLink rll0 = n.getLink(rl0, EnumUsage.Input);
		rll0.setCombinedProcessIndex(0);
		JDFRunList rl = (JDFRunList) n.addResource(ElementName.RUNLIST, EnumUsage.Output);
		rl.setResStatus(EnumResStatus.Unavailable, true);
		rl.setPipeProtocol("Internal");
		JDFResourceLink rll = n.getLink(rl, EnumUsage.Output);
		int pos = n.getTypes().index("Rendering");
		rll.setCombinedProcessIndex(pos);
		JDFResourceLink rll2 = n.ensureLink(rl, EnumUsage.Input, null);
		rll2.setCombinedProcessIndex(pos + 1);
		rll2.setMinStatus(EnumResStatus.Available);
		jdfDoc.write2File(sm_dirTestDataTemp + "HoldRIP.jdf", 2, false);
		rll2.setMinStatus(EnumResStatus.Unavailable);
		rll2.setPipeResume(5);
		jdfDoc.write2File(sm_dirTestDataTemp + "PipeRIP.jdf", 2, false);
	}

	// //////////////////////////////////////////////////////////////////////////
	// /

	// //////////////////////////////////////////////////////////////////////////
	// /
	/*
	 * (non-Javadoc)
	 * 
	 * @see org.cip4.jdflib.JDFTestCaseBase#setUp()
	 */
	@Override
	protected void setUp() throws Exception
	{
		BaseGoldenTicket.misURL = "http://192.168.14.143:8010/FJC/Fiery";
		super.setUp();
		JDFAudit.setStaticAgentName("JDF IDP golden ticket generator");
		idpGoldenTicket = new IDPGoldenTicket(1);

		idpGoldenTicket.assign(null);
		final JDFNode node = idpGoldenTicket.getNode();
		assertTrue(node.getICSVersions(false).contains("Base_L2-1.4"));
		assertTrue(node.getICSVersions(false).contains("JMF_L2-1.4"));
		assertTrue(node.getICSVersions(false).contains("MIS_L1-1.4"));
		assertTrue(node.getICSVersions(false).contains("IDP_L1-1.4"));

		idpGoldenTicket.good = 10;
		idpGoldenTicket.waste = 0;
	}

}