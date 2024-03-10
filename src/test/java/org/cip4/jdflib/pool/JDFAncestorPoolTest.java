/*
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
package org.cip4.jdflib.pool;

import org.cip4.jdflib.JDFTestCaseBase;
import org.cip4.jdflib.core.ElementName;
import org.cip4.jdflib.core.JDFCustomerInfo;
import org.cip4.jdflib.core.JDFDoc;
import org.cip4.jdflib.core.JDFNodeInfo;
import org.cip4.jdflib.core.JDFParser;
import org.cip4.jdflib.core.JDFResourceLink.EnumUsage;
import org.cip4.jdflib.jmf.JDFMessage;
import org.cip4.jdflib.node.JDFAncestor;
import org.cip4.jdflib.node.JDFNode;
import org.cip4.jdflib.node.JDFNode.EnumType;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
/**
 * @author RP
 * 
 *         This implements the first fixture with unit tests for class
 *         JDFAmountPool.
 */
public class JDFAncestorPoolTest extends JDFTestCaseBase
{
	/**
	 * @see junit.framework.TestCase#toString()
	 * @return the string
	 */
	@Override
	public String toString()
	{

		return n != null ? n.toString() : super.toString();
	}

	private JDFNode n;
	private JDFAncestorPool ap;

	@Override
	@BeforeEach
	void setUp() throws Exception
	{
		super.setUp();
		JDFDoc d = new JDFDoc("JDF");
		n = d.getJDFRoot();
		ap = n.appendAncestorPool();
	}

	// /////////////////////////////////////////////////////
	// /////////////////////////////////////////////////////
	/**
	 * Method testVirtualAmounts.
	 * 
	 * @throws Exception
	 */
	@Test
	void testCopyNodeData() throws Exception
	{
		n.setType(EnumType.Product);
		JDFNode n1 = n = n.addJDFNode(EnumType.BlockPreparation);
		n1.setAttribute("foo:bar", "fnarf", "www.foobar.com");

		JDFDoc dA = new JDFDoc("JDF");
		ap = dA.getJDFRoot().appendAncestorPool();
		ap.appendAncestor().setNodeID(n1.getID());
		ap.copyNodeData(n, true, true, false);
		JDFAncestor a0 = ap.getAncestor(0);
		Assertions.assertEquals(a0.getAttribute("foo:bar"), "fnarf");
		Assertions.assertEquals(a0.getNodeID(), n1.getID());
		String s = dA.write2String(2);
		JDFParser p = new JDFParser();
		JDFDoc test = p.parseString(s);
		Assertions.assertNotNull(test);
	}

	// /////////////////////////////////////////////////////

	/**
	 * @throws Exception
	 */
	@Test
	void testMakeRootResource() throws Exception
	{
		//		JDFAncestor a1 = 
		ap.appendAncestor();
		JDFAncestor a2 = ap.appendAncestor();
		//		JDFAncestor a3 = 
		ap.appendAncestor();
		JDFNodeInfo ni2 = a2.appendNodeInfo();
		ni2.makeRootResource(null, null, true);
		Assertions.assertNotNull(a2.getElement("NodeInfoRef", null, 0));
		n.linkResource(ni2, EnumUsage.Input, null);
		Assertions.assertEquals(n.getNodeInfo(), ni2);

	}

	// /////////////////////////////////////////////////////

	/**
	 * @throws Exception
	 */
	@Test
	void testgetAncestorElement() throws Exception
	{
		JDFAncestor a1 = ap.appendAncestor();
		JDFAncestor a2 = ap.appendAncestor();
		JDFAncestor a3 = ap.appendAncestor();
		JDFNodeInfo ni2 = a2.appendNodeInfo();
		ni2.appendJMF().appendQuery(JDFMessage.EnumType.Status).appendSubscription();
		Assertions.assertEquals(ap.getAncestorElement(ElementName.NODEINFO, null, "JMF/Query[@Type=\"Status\"]"), ni2);
		ni2.makeRootResource(null, null, true);
		Assertions.assertEquals(ap.getAncestorElement(ElementName.NODEINFO, null, "JMF/Query[@Type=\"Status\"]"), ni2);

		JDFNodeInfo ni3 = a3.appendNodeInfo();
		ni3.appendJMF().appendQuery(JDFMessage.EnumType.Status).appendSubscription();
		Assertions.assertEquals(ap.getAncestorElement(ElementName.NODEINFO, null, "JMF/Query[@Type=\"Status\"]"), ni3);

		JDFNodeInfo ni1 = a1.appendNodeInfo();
		ni1.appendJMF().appendQuery(JDFMessage.EnumType.Resource).appendSubscription();
		Assertions.assertEquals(ap.getAncestorElement(ElementName.NODEINFO, null, "JMF/Query[@Type=\"Resource\"]"), ni1);
	}

	/**
	 * @throws Exception
	 */
	@Test
	void testgetAncestorElementRef() throws Exception
	{
		JDFAncestor a1 = ap.appendAncestor();
		JDFCustomerInfo ci = n.getCreateCustomerInfo();
		n.getResourceLinkPool().deleteNode();
		a1.refElement(ci);
		Assertions.assertEquals(ap.getAncestorElement(ElementName.CUSTOMERINFO, null, null), ci);
	}

}