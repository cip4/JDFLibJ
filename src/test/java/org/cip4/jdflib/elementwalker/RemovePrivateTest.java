/*
 * The CIP4 Software License, Version 1.0
 *
 *
 * Copyright (c) 2001-2020 The International Cooperation for the Integration of Processes in Prepress, Press and Postpress (CIP4). All rights reserved.
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

import org.cip4.jdflib.core.JDFDoc;
import org.cip4.jdflib.core.KElement;
import org.cip4.jdflib.node.JDFNode;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

/**
 * @author Rainer Prosi, Heidelberger Druckmaschinen *
 */
public class RemovePrivateTest {
	/**
	 *
	 */
	@Test
	public void testRemove()
	{
		final JDFDoc d = new JDFDoc("JDF");
		final JDFNode n = d.getJDFRoot();
		n.setAttribute("foo:bar", "www.foo.com", "blub");
		n.appendElement("blah:e", "www.blah.com");
		n.getCreateAuditPool().setAttribute("foo:bar", "www.foo.com", "blub");
		n.getCreateAuditPool().appendElement("blah:e", "www.blah.com");
		final RemovePrivate rp = new RemovePrivate();
		rp.walkTree(n, null);
		Assertions.assertNull(n.getAttribute("foo:bar", "www.foo.com", null));
		Assertions.assertNull(n.getElement("e", "www.blah.com", 0));
		Assertions.assertNotNull(n.getAuditPool());
	}

	/**
	 *
	 */
	@Test
	public void testRemovePrefix()
	{
		final KElement e = KElement.createRoot("a:b", "www.a.com");
		e.setAttribute("xmlns:foo", "www.foo.com");
		e.setAttribute("foo:bar", "blub");
		final RemovePrivate rp = new RemovePrivate();
		rp.addPrefix("foo");
		rp.walkTree(e, null);
		Assertions.assertNull(e.getNonEmpty("foo:bar"));
		Assertions.assertNull(e.getNonEmpty("bar"));
		Assertions.assertNull(e.getNonEmpty("xmlns:foo"));
	}

	/**
	 *
	 */
	@Test
	public void testZappAttributes()
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
		Assertions.assertEquals("blub", n.getAttribute("foo:bar", "www.foo.com", null));
		Assertions.assertNull(n.getElement("e", "www.blah.com", 0));
		Assertions.assertNotNull(n.getAuditPool());
	}

	/**
	 *
	 */
	@Test
	public void testZappElements()
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
		Assertions.assertNull(n.getAttribute("foo:bar", "www.foo.com", null));
		Assertions.assertEquals(e1, n.getElement("e", "www.blah.com", 0));
		Assertions.assertNotNull(n.getAuditPool());
	}

	/**
	 *
	 */
	@Test
	public void testGeneralID()
	{
		final JDFDoc d = new JDFDoc("JDF");
		final JDFNode n = d.getJDFRoot();
		n.appendGeneralID("foo:key", "bar");
		final RemovePrivate rp = new RemovePrivate();
		rp.walkTree(n, null);
		Assertions.assertNull(n.getGeneralID(0));
		n.appendGeneralID("key", "bar");
		rp.walkTree(n, null);
		Assertions.assertEquals(n.getGeneralID("key", 0), "bar");
		n.appendGeneralID("foo:key", "bar");
		rp.addPrefix("blub");
		rp.walkTree(n, null);
		Assertions.assertEquals(n.getGeneralID("foo:key", 0), "bar");
		rp.addPrefix("foo");
		rp.setZappGeneralID(false);
		rp.walkTree(n, null);
		Assertions.assertEquals(n.getGeneralID("foo:key", 0), "bar");
		rp.setZappGeneralID(true);
		rp.walkTree(n, null);
		Assertions.assertNull(n.getGeneralID("foo:key", 0));
	}

	/**
	 *
	 */
	@Test
	public void testAddPrefix()
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
		Assertions.assertNull(n.getAttribute("bar", "www.foo.com", null));
		Assertions.assertNotNull(n.getAttribute("blah", "www.blah.com", null));
		Assertions.assertNotNull(n.getElement("e", "www.blah.com", 0));
		Assertions.assertNotNull(n.getAuditPool());

	}
}
