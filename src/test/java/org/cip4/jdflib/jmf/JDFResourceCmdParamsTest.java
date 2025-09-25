/*
 * The CIP4 Software License, Version 1.0
 *
 *
 * Copyright (c) 2001-2025 The International Cooperation for the Integration of
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
package org.cip4.jdflib.jmf;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

import org.cip4.jdflib.auto.JDFAutoResourceCmdParams.EUpdateMethod;
import org.cip4.jdflib.core.ElementName;
import org.cip4.jdflib.core.JDFNodeInfo;
import org.cip4.jdflib.jmf.JDFMessage.EnumFamily;
import org.cip4.jdflib.jmf.JDFMessage.EnumType;
import org.cip4.jdflib.node.JDFNode;
import org.cip4.jdflib.util.JDFDate;
import org.junit.jupiter.api.Test;

class JDFResourceCmdParamsTest
{

	@Test
	void testApplyID()
	{
		JDFNode n = JDFNode.createRoot();
		n.setJobID("j1");
		n.setJobPartID("p1");
		JDFNodeInfo ni = n.getCreateNodeInfo();
		ni.setEnd(new JDFDate());

		JDFJMF jmf = JMFBuilderFactory.getJMFBuilder(null).createJMF(EnumFamily.Command, EnumType.Resource);
		JDFResourceCmdParams rcp = jmf.getCreateCommand().getCreateResourceCmdParams(0);
		rcp.setIdentifier(n.getIdentifier());
		rcp.setResourceName(ElementName.NODEINFO);
		rcp.setResourceID(ni.getID());
		rcp.setUpdateMethod(EUpdateMethod.Incremental);
		JDFNodeInfo ni2 = (JDFNodeInfo) rcp.getCreateResource();
		JDFDate offset = new JDFDate().addOffset(0, 0, 0, 42);
		ni2.setEnd(offset);
		rcp.applyResourceCommand(n);
		assertEquals(offset, n.getNodeInfo().getEnd());
	}

	@Test
	void testApplyNoID()
	{
		JDFNode n = JDFNode.createRoot();
		n.setJobID("j1");
		n.setJobPartID("p1");
		JDFNodeInfo ni = n.getCreateNodeInfo();
		ni.setEnd(new JDFDate());

		JDFJMF jmf = JMFBuilderFactory.getJMFBuilder(null).createJMF(EnumFamily.Command, EnumType.Resource);
		JDFResourceCmdParams rcp = jmf.getCreateCommand().getCreateResourceCmdParams(0);
		rcp.setIdentifier(n.getIdentifier());
		rcp.setResourceName(ElementName.NODEINFO);
		rcp.setUpdateMethod(EUpdateMethod.Incremental);
		JDFNodeInfo ni2 = (JDFNodeInfo) rcp.getCreateResource();
		JDFDate offset = new JDFDate().addOffset(0, 0, 0, 42);
		ni2.setEnd(offset);
		rcp.applyResourceCommand(n);
		assertEquals(offset, n.getNodeInfo().getEnd());
	}

	@Test
	void testApplyMultiID()
	{
		JDFNode n = JDFNode.createRoot();
		n.setJobID("j1");
		n.setJobPartID("p1");
		JDFNodeInfo ni = n.getCreateNodeInfo();
		JDFDate end = new JDFDate();
		ni.setEnd(end);
		n.setType(org.cip4.jdflib.node.JDFNode.EnumType.Product);

		JDFNode n2 = n.addJDFNode(org.cip4.jdflib.node.JDFNode.EnumType.Approval);
		JDFNodeInfo ni2 = n2.getCreateNodeInfo();

		JDFJMF jmf = JMFBuilderFactory.getJMFBuilder(null).createJMF(EnumFamily.Command, EnumType.Resource);
		JDFResourceCmdParams rcp = jmf.getCreateCommand().getCreateResourceCmdParams(0);
		rcp.setJobID("j1");
		rcp.setResourceName(ElementName.NODEINFO);
		rcp.setResourceID(ni2.getID());
		rcp.setUpdateMethod(EUpdateMethod.Incremental);
		JDFNodeInfo nic = (JDFNodeInfo) rcp.getCreateResource();
		JDFDate offset = new JDFDate(end).addOffset(0, 0, 0, 42);
		nic.setEnd(offset);
		rcp.applyResourceCommand(n);
		assertEquals(offset, ni2.getEnd());
		assertEquals(end, ni.getEnd());
		assertEquals(2, n.getChildArrayByClass(JDFNodeInfo.class, true, 0).size());
	}

	@Test
	void testApplyMultiIDBad()
	{
		JDFNode n = JDFNode.createRoot();
		n.setJobID("j1");
		n.setJobPartID("p1");
		JDFNodeInfo ni = n.getCreateNodeInfo();
		JDFDate end = new JDFDate();
		ni.setEnd(end);
		n.setType(org.cip4.jdflib.node.JDFNode.EnumType.Product);

		JDFNode n2 = n.addJDFNode(org.cip4.jdflib.node.JDFNode.EnumType.Approval);
		JDFNodeInfo ni2 = n2.getCreateNodeInfo();
		ni2.setEnd(end);
		JDFNode n3 = n.addJDFNode(org.cip4.jdflib.node.JDFNode.EnumType.Approval);

		JDFJMF jmf = JMFBuilderFactory.getJMFBuilder(null).createJMF(EnumFamily.Command, EnumType.Resource);
		JDFResourceCmdParams rcp = jmf.getCreateCommand().getCreateResourceCmdParams(0);
		rcp.setJobID("j1");
		rcp.setResourceName(ElementName.NODEINFO);
		rcp.setResourceID("foo");
		rcp.setUpdateMethod(EUpdateMethod.Incremental);
		JDFNodeInfo nic = (JDFNodeInfo) rcp.getCreateResource();
		JDFDate offset = new JDFDate(end).addOffset(0, 0, 0, 42);
		nic.setEnd(offset);
		rcp.applyResourceCommand(n);
		assertEquals(end, ni2.getEnd());
		assertEquals(end, ni.getEnd());
		assertEquals(2, n.getChildArrayByClass(JDFNodeInfo.class, true, 0).size());
		assertNull(n3.getNodeInfo());
	}

}
