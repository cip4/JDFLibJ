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
package org.cip4.jdflib.core;

import junit.framework.TestCase;

import org.cip4.jdflib.auto.JDFAutoApprovalDetails.EnumApprovalState;
import org.cip4.jdflib.core.JDFAudit.EnumAuditType;
import org.cip4.jdflib.core.JDFElement.EnumVersion;
import org.cip4.jdflib.core.JDFResourceLink.EnumUsage;
import org.cip4.jdflib.node.JDFNode;
import org.cip4.jdflib.node.JDFNode.EnumProcessUsage;
import org.cip4.jdflib.pool.JDFAuditPool;
import org.cip4.jdflib.pool.JDFResourcePool;
import org.cip4.jdflib.resource.JDFCreated;
import org.cip4.jdflib.resource.JDFTool;
import org.cip4.jdflib.resource.JDFResource.EnumResStatus;
import org.cip4.jdflib.resource.process.JDFApprovalDetails;
import org.cip4.jdflib.resource.process.JDFApprovalSuccess;
import org.cip4.jdflib.resource.process.JDFMedia;
import org.cip4.jdflib.util.StringUtil;

public class FixVersionTest extends TestCase
{
	private JDFDoc mDoc;
	private JDFNode n;

	/*
	 * (non-Javadoc)
	 * 
	 * @see junit.framework.TestCase#setUp()
	 */
	protected void setUp() throws Exception
	{
		super.setUp();
		mDoc = new JDFDoc("JDF");
		n = mDoc.getJDFRoot();

	}

	// /////////////////////////////////////////////////////////////////////

	public void testApprovalSuccess()
	{
		n.setType("Approval", true);
		JDFApprovalSuccess as = (JDFApprovalSuccess) n.appendMatchingResource(
				ElementName.APPROVALSUCCESS, EnumProcessUsage.AnyOutput, null);
		n.setVersion(EnumVersion.Version_1_2);
		as.appendContact();
		as.appendFileSpec();
		boolean bRet = n.fixVersion(EnumVersion.Version_1_3);
		assertTrue("fix ok", bRet);
		assertNotNull("approvaldetails", as.getApprovalDetails(0));
		bRet = n.fixVersion(EnumVersion.Version_1_2);
		assertTrue("fix ok", bRet);
		assertNull("approvaldetails", as.getApprovalDetails(0));
		bRet = n.fixVersion(EnumVersion.Version_1_3);
		assertTrue("fix ok", bRet);
		as = (JDFApprovalSuccess) n.getMatchingResource(
				ElementName.APPROVALSUCCESS, EnumProcessUsage.AnyOutput, null,
				0);
		JDFApprovalDetails ad = as.getApprovalDetails(0);
		ad.setApprovalState(EnumApprovalState.Rejected);
		bRet = n.fixVersion(EnumVersion.Version_1_2);
		assertFalse("fix not ok", bRet);
	}

	// //////////////////////////////////////////////////////////////////////

	public void testRRefs()
	{
		JDFResourcePool rp = n.appendResourcePool();
		rp.setAttribute(AttributeName.RREFS, "a b", null);
		n.fixVersion(null);
		assertFalse(rp.hasAttribute(AttributeName.RREFS));

	}

	// //////////////////////////////////////////////////////////////////////

	public void testAudit()
	{
		JDFAuditPool ap = n.getAuditPool();
		assertNotNull(ap);
		JDFCreated crea = (JDFCreated) ap.getAudit(0, EnumAuditType.Created,
				null, null);
		String agent = crea.getAgentName();
		assertNotNull(agent);
		String author = crea.getAuthor();
		assertNotNull(author);

		n.fixVersion(EnumVersion.Version_1_1);
		author = crea.getAuthor();
		assertEquals(StringUtil.token(author, 1, "_|_"), agent);
		assertTrue(author.startsWith(agent));
		String agent2 = crea.getAgentName();
		assertEquals(agent2, "");

		n.fixVersion(EnumVersion.Version_1_3);
		author = crea.getAuthor();
		assertEquals(author.indexOf("_|_"), -1);
		agent2 = crea.getAgentName();
		assertEquals(agent, agent2);

		n.fixVersion(EnumVersion.Version_1_2);
		author = crea.getAuthor();
		assertEquals(author.indexOf("_|_"), -1);
		agent2 = crea.getAgentName();
		assertEquals(agent, agent2);

	}

	// //////////////////////////////////////////////////////////////////////
	// //////////////////////////////////////////////////////////////////////

	public void testResourceStatus()
	{
		JDFMedia m = (JDFMedia) n.addResource("Media", null, EnumUsage.Input,
				null, null, null, null);
		m.setResStatus(EnumResStatus.Available, true);
		assertEquals(m.getResStatus(true), EnumResStatus.Available);
		assertTrue(m.fixVersion(EnumVersion.Version_1_1));
		assertEquals(m.getResStatus(true), EnumResStatus.Available);
		assertTrue(m.fixVersion(EnumVersion.Version_1_3));
		assertEquals(m.getResStatus(true), EnumResStatus.Available);
	}

	// //////////////////////////////////////////////////////////////////////
	public void testTool()
	{
		JDFTool t = (JDFTool) n.addResource("Tool", null, EnumUsage.Input,
				null, null, null, null);
		t.setResStatus(EnumResStatus.Available, true);
		t.setProductID("toolID");
		assertTrue(t.fixVersion(EnumVersion.Version_1_1));
		assertEquals(t.getToolID(), "toolID");
		assertEquals(t.getProductID(), "toolID");
		assertTrue(t.fixVersion(EnumVersion.Version_1_3));
		assertEquals(t.getToolID(), "");
		assertEquals(t.getProductID(), "toolID");
	}
	// //////////////////////////////////////////////////////////////////////
	// //////////////////////////////////////////////////////////////////////
	// //////////////////////////////////////////////////////////////////////
	// //////////////////////////////////////////////////////////////////////

}
