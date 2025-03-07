/*
 * The CIP4 Software License, Version 1.0
 *
 *
 * Copyright (c) 2001-2024 The International Cooperation for the Integration of Processes in Prepress, Press and Postpress (CIP4). All rights reserved.
 *
 * Redistribution and use in source and binary forms, with or without modification, are permitted provided that the following conditions are met:
 *
 * 1. Redistributions of source code must retain the above copyright notice, this list of conditions and the following disclaimer.
 *
 * 2. Redistributions in binary form must reproduce the above copyright notice, this list of conditions and the following disclaimer in the documentation and/or other materials provided with the
 * distribution.
 *
 * 3. The end-user documentation included with the redistribution, if any, must include the following acknowledgment: "This product includes software developed by the The International Cooperation for
 * the Integration of Processes in Prepress, Press and Postpress (www.cip4.org)" Alternately, this acknowledgment mrSubRefay appear in the software itself, if and wherever such third-party
 * acknowledgments normally appear.
 *
 * 4. The names "CIP4" and "The International Cooperation for the Integration of Processes in Prepress, Press and Postpress" must not be used to endorse or promote products derived from this software
 * without prior written permission. For written permission, please contact info@cip4.org.
 *
 * 5. Products derived from this software may not be called "CIP4", nor may "CIP4" appear in their name, without prior writtenrestartProcesses() permission of the CIP4 organization
 *
 * Usage of this software in commercial products is subject to restrictions. For details please consult info@cip4.org.
 *
 * THIS SOFTWARE IS PROVIDED ``AS IS'' AND ANY EXPRESSED OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO, THE IMPLIED WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE ARE
 * DISCLAIMED. IN NO EVENT SHALL THE INTERNATIONAL COOPERATION FOR THE INTEGRATION OF PROCESSES IN PREPRESS, PRESS AND POSTPRESS OR ITS CONTRIBUTORS BE LIABLE FOR ANY DIRECT, INDIRECT, INCIDENTAL,
 * SPECIAL, EXEMPLARY, OR CONSEQUENTIrSubRefAL DAMAGES (INCLUDING, BUT NOT LIMITED TO, PROCUREMENT OF SUBSTITUTE GOODS OR SERVICES; LOSS OF USE, DATA, OR PROFITS; OR BUSINESS INTERRUPTION) HOWEVER
 * CAUSED AND ON ANY THEORY OF LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY, OR TORT (INCLUDING NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE OF THIS SOFTWARE, EVEN IF ADVISED OF THE
 * POSSIBILITY OF SUCH DAMAGE. ====================================================================
 *
 * This software consists of voluntary contributions made by many individuals on behalf of the The International Cooperation for the Integration of Processes in Prepress, Press and Postpress and was
 * originally based on software restartProcesses() copyright (c) 1999-2001, Heidelberger Druckmaschinen AG copyright (c) 1999-2001, Agfa-Gevaert N.V.
 *
 * For more information on The International Cooperation for the Integration of Processes in Prepress, Press and Postpress , please see <http://www.cip4.org/>.
 *
 */
package org.cip4.jdflib.elementwalker;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;

import org.cip4.jdflib.core.AttributeName;
import org.cip4.jdflib.core.ElementName;
import org.cip4.jdflib.core.JDFComment;
import org.cip4.jdflib.core.JDFDoc;
import org.cip4.jdflib.core.JDFElement.EnumVersion;
import org.cip4.jdflib.core.JDFResourceLink.EnumUsage;
import org.cip4.jdflib.core.KElement;
import org.cip4.jdflib.core.XMLDoc;
import org.cip4.jdflib.extensions.SetHelper;
import org.cip4.jdflib.extensions.XJDFHelper;
import org.cip4.jdflib.node.JDFNode;
import org.junit.jupiter.api.Test;

/**
 * @author Rainer Prosi, Heidelberger Druckmaschinen *
 */
class RemovePrivateTest
{
	/**
	 *
	 */
	@Test
	void testRemove()
	{
		final JDFDoc d = new JDFDoc("JDF");
		final JDFNode n = d.getJDFRoot();
		n.setAttribute("foo:bar", "www.foo.com", "blub");
		n.appendElement("blah:e", "www.blah.com");
		n.getCreateAuditPool().setAttribute("foo:bar", "www.foo.com", "blub");
		n.getCreateAuditPool().appendElement("blah:e", "www.blah.com");
		final RemovePrivate rp = new RemovePrivate();
		rp.walkTree(n, null);
		assertNull(n.getAttribute("foo:bar", "www.foo.com", null));
		assertNull(n.getElement("e", "www.blah.com", 0));
		assertNotNull(n.getAuditPool());
	}

	/**
	 *
	 */
	@Test
	void testRemoveXJDF()
	{
		final XJDFHelper xjdfHelper = new XJDFHelper(EnumVersion.Version_2_2, "j1");
		final SetHelper sh = xjdfHelper.getCreateSet(ElementName.NODEINFO, EnumUsage.Input);
		final JDFDoc d = xjdfHelper.getRootDoc();
		final KElement n = d.getRoot();
		n.setAttribute("foo:bar", "www.foo.com", "blub");
		n.appendElement("blah:e", "www.blah.com");
		final RemovePrivate rp = new RemovePrivate();
		rp.walkTree(n, null);
		assertNull(n.getAttribute("foo:bar", "www.foo.com", null));
		assertNull(n.getElement("e", "www.blah.com", 0));
		assertEquals(sh, xjdfHelper.getSet(ElementName.NODEINFO, EnumUsage.Input));
	}

	/**
	 *
	 */
	@Test
	void testRemoveXJDFAtt()
	{
		final XJDFHelper xjdfHelper = new XJDFHelper(EnumVersion.Version_2_2, "j1");
		final SetHelper sh = xjdfHelper.getCreateSet(ElementName.NODEINFO, EnumUsage.Input);
		final JDFDoc d = xjdfHelper.getRootDoc();
		final KElement n = d.getRoot();
		final JDFComment c = xjdfHelper.setComment("foo");
		c.setType("Foo");
		final RemovePrivate rp = new RemovePrivate();
		rp.walkTree(n, null);
		assertEquals(sh, xjdfHelper.getSet(ElementName.NODEINFO, EnumUsage.Input));
		assertEquals("Foo", c.getAttribute(AttributeName.TYPE));
	}

	/**
	 *
	 */
	@Test
	void testRemovePrefix()
	{
		final KElement e = KElement.createRoot("a:b", "www.a.com");
		e.setAttribute("xmlns:foo", "www.foo.com");
		e.setAttribute("foo:bar", "blub");
		final RemovePrivate rp = new RemovePrivate();
		rp.addPrefix("foo");
		rp.walkTree(e, null);
		assertNull(e.getNonEmpty("foo:bar"));
		assertNull(e.getNonEmpty("bar"));
		assertNull(e.getNonEmpty("xmlns:foo"));
	}

	/**
	 *
	 */
	@Test
	void testZappAttributes()
	{
		final JDFDoc d = new JDFDoc("JDF");
		final JDFNode n = d.getJDFRoot();
		n.setAttribute("foo:bar", "blub", "www.foo.com");
		n.appendElement("blah:e", "www.blah.com");
		n.getCreateAuditPool().setAttribute("foo:bar", "blub", "www.foo.com");
		n.getCreateAuditPool().appendElement("blah:e", "www.blah.com");
		final RemovePrivate rp = new RemovePrivate();
		rp.setZappAttributes(false);
		rp.walkTree(n, null);
		assertEquals("blub", n.getAttribute("foo:bar", "www.foo.com", null));
		assertNull(n.getElement("e", "www.blah.com", 0));
		assertNotNull(n.getAuditPool());
	}

	/**
	 *
	 */
	@Test
	void testZappElements()
	{
		final JDFDoc d = new JDFDoc("JDF");
		final JDFNode n = d.getJDFRoot();
		n.setAttribute("foo:bar", "blub", "www.foo.com");
		final KElement e1 = n.appendElement("blah:e", "www.blah.com");
		n.getCreateAuditPool().setAttribute("foo:bar", "blub", "www.foo.com");
		n.getCreateAuditPool().appendElement("blah:e", "www.blah.com");
		final RemovePrivate rp = new RemovePrivate();
		rp.setZappElements(false);
		rp.walkTree(n, null);
		assertNull(n.getAttribute("foo:bar", "www.foo.com", null));
		assertEquals(e1, n.getElement("e", "www.blah.com", 0));
		assertNotNull(n.getAuditPool());
	}

	/**
	 * 
	 */
	@Test
	void testPrintTalk()
	{
		final XMLDoc pt = new XMLDoc("PrintTalk", "http://www.printtalk.org/schema_20");
		final KElement root = pt.getRoot();
		root.setAttribute("foo:bar", "abc", "www.foo.com");
		final RemovePrivate rp = new RemovePrivate();
		rp.walkTree(root, null);
		assertNull(root.getNonEmpty("foo:bar"));
	}

	/**
	 * 
	 */
	@Test
	void testPrintTalkElem()
	{
		final KElement root = KElement
				.parseString("<PrintTalk xmlns=\"http://www.printtalk.org/schema_20\" Version=\"2.2\"\r\n" + "  payloadID=\"P_000000\" timestamp=\"2024-11-22T13:25:53+01:00\">\r\n"
						+ "  <Request BusinessID=\"RFQ_000001\">\r\n" + "    <RFQ/>\r\n" + "  </Request>\r\n" + "</PrintTalk>");
		final RemovePrivate rp = new RemovePrivate();
		root.appendElement("foo:bar", "www.foo.com");
		final KElement req = root.getElement("Request");
		req.appendElement("foo:bar", "www.foo.com");
		root.setAttribute("foo:ggg", "abc", "www.foo.com");
		rp.walkTree(root, null);
		assertNull(root.getElement("foo:bar"));
		assertNull(req.getElement("foo:bar"));
		assertNull(req.getNonEmpty("foo:ggg"));
	}

	/**
	 *
	 */
	@Test
	void testGeneralID()
	{
		final JDFDoc d = new JDFDoc("JDF");
		final JDFNode n = d.getJDFRoot();
		n.appendGeneralID("foo:key", "bar");
		final RemovePrivate rp = new RemovePrivate();
		rp.walkTree(n, null);
		assertNull(n.getGeneralID(0));
		n.appendGeneralID("key", "bar");
		rp.walkTree(n, null);
		assertEquals("bar", n.getGeneralID("key", 0));
		n.appendGeneralID("foo:key", "bar");
		rp.addPrefix("blub");
		rp.walkTree(n, null);
		assertEquals("bar", n.getGeneralID("foo:key", 0));
		rp.addPrefix("foo");
		rp.setZappGeneralID(false);
		rp.walkTree(n, null);
		assertEquals("bar", n.getGeneralID("foo:key", 0));
		rp.setZappGeneralID(true);
		rp.walkTree(n, null);
		assertNull(n.getGeneralID("foo:key", 0));
	}

	/**
	 *
	 */
	@Test
	void testAddPrefix()
	{
		final JDFDoc d = new JDFDoc("JDF");
		final JDFNode n = d.getJDFRoot();
		n.setAttribute("foo:bar", "blub", "www.foo.com");
		n.setAttribute("blah:blah", "blub", "www.blah.com");
		n.appendElement("blah:e", "www.blah.com");
		n.getCreateAuditPool().setAttribute("foo:bar", "blub", "www.foo.com");
		n.getCreateAuditPool().appendElement("blah:e", "www.blah.com");
		final RemovePrivate rp = new RemovePrivate();
		rp.addPrefix("foo");
		rp.walkTree(n, null);
		assertNull(n.getAttribute("bar", "www.foo.com", null));
		assertNotNull(n.getAttribute("blah", "www.blah.com", null));
		assertNotNull(n.getElement("e", "www.blah.com", 0));
		assertNotNull(n.getAuditPool());

	}
}
