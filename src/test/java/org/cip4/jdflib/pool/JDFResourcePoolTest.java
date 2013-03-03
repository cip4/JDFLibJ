/*
 * The CIP4 Software License, Version 1.0
 *
 *
 * Copyright (c) 2001-2011 The International Cooperation for the Integration of
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
import org.cip4.jdflib.core.JDFComment;
import org.cip4.jdflib.core.JDFCustomerInfo;
import org.cip4.jdflib.core.JDFDoc;
import org.cip4.jdflib.core.JDFElement.EnumVersion;
import org.cip4.jdflib.core.JDFResourceLink;
import org.cip4.jdflib.core.JDFResourceLink.EnumUsage;
import org.cip4.jdflib.node.JDFNode;
import org.cip4.jdflib.node.JDFNode.EnumProcessUsage;
import org.cip4.jdflib.resource.JDFResource;
import org.cip4.jdflib.resource.process.JDFComponent;
import org.cip4.jdflib.resource.process.JDFContact;
import org.junit.Assert;
import org.junit.Test;
/**
 * @author MuchaD
 * 
 *         This implements the first fixture with unit tests for class
 *         JDFElement.
 */
public class JDFResourcePoolTest extends JDFTestCaseBase
{

	/**
	 * 
	 * make sure that coomments are not cast as resources
	 * @throws Exception
	 */
	@Test
	public void testaddComment() throws Exception
	{
		JDFNode n = new JDFDoc("JDF").getJDFRoot();
		JDFComment c = n.appendResourcePool().appendComment();
		Assert.assertTrue(c instanceof JDFComment);
	}

	/**
	 * Method testLinkResource.
	 * 
	 * @throws Exception
	 */
	@Test
	public void testgetUnlinkedResources() throws Exception
	{
		JDFDoc d = new JDFDoc("JDF");
		JDFNode n = d.getJDFRoot();
		JDFResource r = n.addResource("Component", null, null, null, null, null, null);
		JDFResourcePool rp = n.getResourcePool();
		Assert.assertTrue(r instanceof JDFComponent);
		Assert.assertFalse(n.hasChildElement("ResourceLinkPool", null));
		JDFResourceLinkPool rlp = n.getCreateResourceLinkPool();
		Assert.assertEquals(rp.getUnlinkedResources().elementAt(0), r);

		JDFResourceLink rl = rlp.linkResource(r, EnumUsage.Input, EnumProcessUsage.BookBlock);
		Assert.assertNotNull(rl);
		Assert.assertNull(rp.getUnlinkedResources());
		JDFResource rx = n.addResource("ExposedMedia", null, null, null, null, null, null);
		Assert.assertEquals(rp.getUnlinkedResources().elementAt(0), rx);

		n.setVersion(EnumVersion.Version_1_2);
		JDFCustomerInfo ci = n.appendCustomerInfo();
		JDFContact co = ci.appendContact();
		co = (JDFContact) co.makeRootResource(null, null, true);
		Assert.assertEquals(rp.getUnlinkedResources().elementAt(0), rx);
		Assert.assertEquals(rp.getUnlinkedResources().size(), 1);

		ci.deleteNode();
		Assert.assertEquals(rp.getUnlinkedResources().elementAt(1), co);
		Assert.assertEquals(rp.getUnlinkedResources().size(), 2);

	}

	// //////////////////////////////////////////////////////////////////
	// //////////////////////////////////////////////////////////////////
	// //////////////////////////////////////////////////////////////////

}