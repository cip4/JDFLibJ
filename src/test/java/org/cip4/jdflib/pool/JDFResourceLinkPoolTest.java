/*
 * The CIP4 Software License, Version 1.0
 *
 *
 * Copyright (c) 2001-2014 The International Cooperation for the Integration of
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
import org.cip4.jdflib.core.JDFDoc;
import org.cip4.jdflib.core.JDFElement;
import org.cip4.jdflib.core.JDFResourceLink;
import org.cip4.jdflib.core.JDFResourceLink.EnumUsage;
import org.cip4.jdflib.core.VElement;
import org.cip4.jdflib.datatypes.JDFAttributeMap;
import org.cip4.jdflib.datatypes.VJDFAttributeMap;
import org.cip4.jdflib.node.JDFNode;
import org.cip4.jdflib.node.JDFNode.EnumProcessUsage;
import org.cip4.jdflib.resource.JDFResource;
import org.cip4.jdflib.resource.JDFResource.EnumPartIDKey;
import org.cip4.jdflib.resource.JDFResource.EnumResourceClass;
import org.cip4.jdflib.resource.process.JDFComponent;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

/**
 * @author MuchaD
 *
 *         This implements the first fixture with unit tests for class
 *         JDFElement.
 */
public class JDFResourceLinkPoolTest extends JDFTestCaseBase
{

	/**
	 *
	 */
	@Test
	void testGetLink()
	{
		JDFDoc d = new JDFDoc("JDF");
		JDFNode n = d.getJDFRoot();
		JDFResource r = n.addResource("Component", null, null, null, null, null, null);
		Assertions.assertTrue(r instanceof JDFComponent);
		Assertions.assertFalse(n.hasChildElement("ResourceLinkPool", null));
		JDFResourceLinkPool rlp = n.getCreateResourceLinkPool();

		JDFResourceLink rl = rlp.linkResource(r, EnumUsage.Input, EnumProcessUsage.BookBlock);
		Assertions.assertNotNull(rl);
		Assertions.assertEquals(rl, rlp.getLink(r, null, null));

		JDFResource r2 = n.addResource("foo:bar", EnumResourceClass.Parameter, null, null, null, "www.foo.com", null);
		rl = rlp.linkResource(r2, EnumUsage.Input, null);
		Assertions.assertEquals(rl, rlp.getLink(r2, null, null));

	}

	/**
	 *
	 */
	@Test
	void testGetInOutLink()
	{
		JDFDoc d = new JDFDoc("JDF");
		JDFNode n = d.getJDFRoot();
		JDFResource bookBlock = n.addResource("Component", null, null, null, null, null, null);
		Assertions.assertFalse(n.hasChildElement("ResourceLinkPool", null));
		Assertions.assertTrue(bookBlock instanceof JDFComponent);
		JDFResourceLinkPool rlp = n.getCreateResourceLinkPool();

		JDFResourceLink rl = rlp.linkResource(bookBlock, EnumUsage.Input, EnumProcessUsage.BookBlock);
		Assertions.assertEquals(rlp.getInOutLinks(EnumUsage.Input, true, "Component", null).get(0), rl);
		Assertions.assertEquals(rlp.getInOutLinksExtended(EnumUsage.Input, true, "Component", "BookBlock", null, false).get(0), rl);
		Assertions.assertEquals(rlp.getInOutLinks(null, true, "Component", null).get(0), rl);
		JDFResource other2 = n.addResource("Component", null, EnumUsage.Output, EnumProcessUsage.BackEndSheet, null, null, null);
		JDFResource other = n.addResource("Component", EnumUsage.Output);
		JDFResource other3 = n.addResource("Component", null, EnumUsage.Output, EnumProcessUsage.SpineBoard, null, null, null);
		Assertions.assertEquals(rlp.getInOutLinksExtended(null, true, "Component", "BookBlock", null, false).get(0), rl);
		Assertions.assertEquals(rlp.getInOutLinksExtended(null, false, "Component", "BookBlock", null, false).get(0), bookBlock);
		Assertions.assertEquals(rlp.getInOutLinksExtended(null, false, "Component", "BookBlock2", null, false).size(), 0);
		Assertions.assertEquals(rlp.getInOutLinksExtended(EnumUsage.Output, false, "Component", null, null, false).get(0), other);
		Assertions.assertEquals(rlp.getInOutLinksExtended(EnumUsage.Output, false, "Component", EnumProcessUsage.BackEndSheet.getName(), null, false).get(0), other2);
		Assertions.assertEquals(rlp.getInOutLinksExtended(EnumUsage.Output, false, "Component", EnumProcessUsage.SpineBoard.getName(), null, false).get(0), other3);
	}

	/**
	 * Method testLinkResource.
	 *
	 *
	 */
	@Test
	void testLinkResource()
	{
		JDFDoc d = new JDFDoc("JDF");
		JDFNode n = d.getJDFRoot();
		JDFResource r = n.addResource("Component", null, null, null, null, null, null);
		Assertions.assertTrue(r instanceof JDFComponent);
		Assertions.assertFalse(n.hasChildElement("ResourceLinkPool", null));
		JDFResourceLinkPool rlp = n.getCreateResourceLinkPool();

		JDFResourceLink rl = rlp.linkResource(r, EnumUsage.Input, EnumProcessUsage.BookBlock);
		Assertions.assertNotNull(rl);
		Assertions.assertEquals(rl.getEnumProcessUsage(), EnumProcessUsage.BookBlock);
		Assertions.assertEquals(rl.getUsage(), EnumUsage.Input);
		rl = rlp.linkResource(r, EnumUsage.Input, EnumProcessUsage.BookBlock);
		Assertions.assertNotNull(rl, "no two");

		rl = rlp.linkResource(r, EnumUsage.Input, EnumProcessUsage.Cover);
		Assertions.assertNotNull(rl);
		Assertions.assertEquals(rl.getEnumProcessUsage(), EnumProcessUsage.Cover);
		Assertions.assertEquals(rl.getUsage(), EnumUsage.Input);

		rl = rlp.linkResource(r, EnumUsage.Output, EnumProcessUsage.Cover);
		Assertions.assertNotNull(rl);
		Assertions.assertEquals(rl.getEnumProcessUsage(), EnumProcessUsage.Cover);
		Assertions.assertEquals(rl.getUsage(), EnumUsage.Output);

		JDFResource r2 = n.addResource("foo:bar", EnumResourceClass.Parameter, null, null, null, "www.foo.com", null);
		rl = rlp.linkResource(r2, EnumUsage.Input, null);
		Assertions.assertNotNull(rl);
		Assertions.assertEquals(rl.getUsage(), EnumUsage.Input);

	}

	/**
	 * Method testLinkResource.
	 *
	 *
	 */
	@Test
	void testLinkResourcePartition()
	{
		JDFDoc d = new JDFDoc("JDF");
		JDFNode n = d.getJDFRoot();
		JDFResource r = n.addResource("Component", null, null, null, null, null, null);
		Assertions.assertTrue(r instanceof JDFComponent);
		r = r.addPartition(EnumPartIDKey.SignatureName, "Sig1");
		r.addPartition(EnumPartIDKey.SheetName, "S1");
		Assertions.assertFalse(n.hasChildElement("ResourceLinkPool", null));
		JDFResourceLinkPool rlp = n.getCreateResourceLinkPool();

		JDFResourceLink rl = rlp.linkResource(r, EnumUsage.Input, EnumProcessUsage.BookBlock);
		Assertions.assertNotNull(rl);
		Assertions.assertEquals(rl.getEnumProcessUsage(), EnumProcessUsage.BookBlock);
		Assertions.assertEquals(rl.getUsage(), EnumUsage.Input);
		VJDFAttributeMap v = new VJDFAttributeMap();
		v.add(new JDFAttributeMap(EnumPartIDKey.SignatureName, "Sig1"));
		Assertions.assertEquals(rl.getPartMapVector(), v);

	}

	// //////////////////////////////////////////////////////////////////

	/**
	 *
	 */
	@Test
	void testGetLinkedResources()
	{
		JDFDoc d = new JDFDoc("JDF");
		JDFNode n = d.getJDFRoot();
		JDFResource r = n.addResource("Component", null, EnumUsage.Input, null, null, null, null);
		JDFResourceLinkPool rlp = n.getResourceLinkPool();
		Assertions.assertNotNull(rlp);
		Assertions.assertEquals(rlp.getLinkedResources(null, null, null, false, null).elementAt(0), r);
		Assertions.assertEquals(rlp.getLinkedResources("Component", null, null, false, null).elementAt(0), r);
		Assertions.assertEquals(rlp.getLinkedResources("ComponentLink", null, null, false, null).elementAt(0), r);
	}

	/**
	 * tests the various combinations of links and resources + namespaces
	 */
	@Test
	void testGetLinkedResourcesNS()
	{
		JDFDoc d = new JDFDoc("JDF");
		JDFNode n = d.getJDFRoot();
		JDFResource r = n.addResource("Component", null, EnumUsage.Input, null, null, null, null);
		JDFResource r2 = n.addResource("Component", EnumResourceClass.Quantity, EnumUsage.Input, null, null, "www.foo.com", null);
		JDFResource r3 = n.addResource("foo:Component", EnumResourceClass.Quantity, EnumUsage.Input, null, null, "www.foo.com", null);
		JDFResource r4 = n.addResource("foo:Component", EnumResourceClass.Quantity, EnumUsage.Input, null, null, "www.bar.com", null);
		JDFResource r5 = n.addResource("jdf:Component", EnumResourceClass.Quantity, EnumUsage.Input, null, null, JDFElement.getSchemaURL(), null);
		JDFResourceLinkPool rlp = n.getResourceLinkPool();
		Assertions.assertNotNull(rlp);
		VElement allLinkedResources = rlp.getLinkedResources(null, null, null, false, null);
		Assertions.assertEquals(allLinkedResources.elementAt(0), r);
		Assertions.assertEquals(allLinkedResources.elementAt(1), r2);
		Assertions.assertEquals(allLinkedResources.elementAt(2), r3);
		Assertions.assertEquals(allLinkedResources.elementAt(3), r4);
		Assertions.assertEquals(allLinkedResources.elementAt(4), r5);
		VElement jdfLinkedResources = rlp.getLinkedResources("Component", null, null, false, null);
		Assertions.assertEquals(jdfLinkedResources.elementAt(0), r);
		Assertions.assertEquals(jdfLinkedResources.elementAt(1), r5);
		Assertions.assertNull(jdfLinkedResources.elementAt(2));
		Assertions.assertEquals(rlp.getLinkedResources("ComponentLink", null, null, false, null), jdfLinkedResources);

		VElement allFooLinkedResources = rlp.getLinkedResources("Component", null, null, false, "www.foo.com");
		Assertions.assertEquals(allFooLinkedResources.elementAt(0), r2);
		Assertions.assertEquals(allFooLinkedResources.elementAt(1), r3);
		Assertions.assertEquals(rlp.getLinkedResources("ComponentLink", null, null, false, null), jdfLinkedResources);
		VElement fooFooLinkedResources = rlp.getLinkedResources("foo:Component", null, null, false, "www.foo.com");
		Assertions.assertEquals(fooFooLinkedResources.elementAt(0), r3);
		Assertions.assertEquals(fooFooLinkedResources.size(), 1);
		VElement fooBarLinkedResources = rlp.getLinkedResources("foo:Component", null, null, false, "www.bar.com");
		Assertions.assertEquals(fooBarLinkedResources.elementAt(0), r4);
		Assertions.assertEquals(fooBarLinkedResources.size(), 1);
		VElement fooSnafuLinkedResources = rlp.getLinkedResources("snafu:Component", null, null, false, "www.bar.com");
		Assertions.assertEquals(fooSnafuLinkedResources.size(), 0);
		VElement fooSnafu2LinkedResources = rlp.getLinkedResources("foo:Component", null, null, false, "www.snafu.com");
		Assertions.assertEquals(fooSnafu2LinkedResources.size(), 0);
		VElement fooNullLinkedResources = rlp.getLinkedResources("foo:Component", null, null, false, null);
		Assertions.assertEquals(fooNullLinkedResources.elementAt(0), r3);
		Assertions.assertEquals(fooNullLinkedResources.elementAt(1), r4);
		Assertions.assertEquals(fooNullLinkedResources.size(), 2);
	}
}