/*
 * The CIP4 Software License, Version 1.0
 *
 *
 * Copyright (c) 2001-2013 The International Cooperation for the Integration of
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
 *    Alternately, this acknowledgment mrSubRefay appear in the software itself,
 *    if and wherever such third-party acknowledgments normally appear.
 *
 * 4. The names "CIP4" and "The International Cooperation for the Integration of
 *    Processes in  Prepress, Press and Postpress" must
 *    not be used to endorse or promote products derived from this
 *    software without prior written permission. For written
 *    permission, please contact info@cip4.org.
 *
 * 5. Products derived from this software may not be called "CIP4",
 *    nor may "CIP4" appear in their name, without prior writtenrestartProcesses()
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
 * SPECIAL, EXEMPLARY, OR CONSEQUENTIrSubRefAL DAMAGES (INCLUDING, BUT NOT
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
 * originally based on software restartProcesses()
 * copyright (c) 1999-2001, Heidelberger Druckmaschinen AG
 * copyright (c) 1999-2001, Agfa-Gevaert N.V.
 *
 * For more information on The International Cooperation for the
 * Integration of Processes in  Prepress, Press and Postpress , please see
 * <http://www.cip4.org/>.
 *
 */
package org.cip4.jdflib.elementwalker;

import junit.framework.TestCase;

import org.cip4.jdflib.core.JDFDoc;
import org.cip4.jdflib.core.KElement;
import org.cip4.jdflib.node.JDFNode;

/**
  * @author Rainer Prosi, Heidelberger Druckmaschinen *
 */
public class RemovePrivateTest extends TestCase
{
	/**
	 * 
	 */
	public void testRemove()
	{
		JDFDoc d = new JDFDoc("JDF");
		JDFNode n = d.getJDFRoot();
		n.setAttribute("foo:bar", "www.foo.com", "blub");
		n.appendElement("blah:e", "www.blah.com");
		n.getCreateAuditPool().setAttribute("foo:bar", "www.foo.com", "blub");
		n.getCreateAuditPool().appendElement("blah:e", "www.blah.com");
		RemovePrivate rp = new RemovePrivate();
		rp.walkTree(n, null);
		assertNull(n.getAttribute("foo:bar", "www.foo.com", null));
		assertNull(n.getElement("e", "www.blah.com", 0));
		assertNotNull(n.getAuditPool());
	}

	/**
	 * 
	 */
	public void testZappAttributes()
	{
		JDFDoc d = new JDFDoc("JDF");
		JDFNode n = d.getJDFRoot();
		n.setAttribute("foo:bar", "blub", "www.foo.com");
		n.appendElement("blah:e", "www.blah.com");
		n.getCreateAuditPool().setAttribute("foo:bar", "blub", "www.foo.com");
		n.getCreateAuditPool().appendElement("blah:e", "www.blah.com");
		RemovePrivate rp = new RemovePrivate();
		rp.setZappAttributes(false);
		rp.walkTree(n, null);
		assertEquals("blub", n.getAttribute("foo:bar", "www.foo.com", null));
		assertNull(n.getElement("e", "www.blah.com", 0));
		assertNotNull(n.getAuditPool());
	}

	/**
	 * 
	 */
	public void testZappElements()
	{
		JDFDoc d = new JDFDoc("JDF");
		JDFNode n = d.getJDFRoot();
		n.setAttribute("foo:bar", "blub", "www.foo.com");
		KElement e1 = n.appendElement("blah:e", "www.blah.com");
		n.getCreateAuditPool().setAttribute("foo:bar", "blub", "www.foo.com");
		n.getCreateAuditPool().appendElement("blah:e", "www.blah.com");
		RemovePrivate rp = new RemovePrivate();
		rp.setZappElements(false);
		rp.walkTree(n, null);
		assertNull(n.getAttribute("foo:bar", "www.foo.com", null));
		assertEquals(e1, n.getElement("e", "www.blah.com", 0));
		assertNotNull(n.getAuditPool());
	}

	/**
	 * 
	 */
	public void testGeneralID()
	{
		JDFDoc d = new JDFDoc("JDF");
		JDFNode n = d.getJDFRoot();
		n.appendGeneralID("foo:key", "bar");
		RemovePrivate rp = new RemovePrivate();
		rp.walkTree(n, null);
		assertNull(n.getGeneralID(0));
		n.appendGeneralID("key", "bar");
		rp.walkTree(n, null);
		assertEquals(n.getGeneralID("key", 0), "bar");
		n.appendGeneralID("foo:key", "bar");
		rp.addPrefix("blub");
		rp.walkTree(n, null);
		assertEquals(n.getGeneralID("foo:key", 0), "bar");
		rp.addPrefix("foo");
		rp.setZappGeneralID(false);
		rp.walkTree(n, null);
		assertEquals(n.getGeneralID("foo:key", 0), "bar");
		rp.setZappGeneralID(true);
		rp.walkTree(n, null);
		assertNull(n.getGeneralID("foo:key", 0));
	}

	/**
	 * 
	 */
	public void testAddPrefix()
	{
		JDFDoc d = new JDFDoc("JDF");
		JDFNode n = d.getJDFRoot();
		n.setAttribute("foo:bar", "blub", "www.foo.com");
		n.setAttribute("blah:blah", "blub", "www.blah.com");
		n.appendElement("blah:e", "www.blah.com");
		n.getCreateAuditPool().setAttribute("foo:bar", "blub", "www.foo.com");
		n.getCreateAuditPool().appendElement("blah:e", "www.blah.com");
		RemovePrivate rp = new RemovePrivate();
		rp.addPrefix("foo");
		rp.walkTree(n, null);
		assertNull(n.getAttribute("bar", "www.foo.com", null));
		assertNotNull(n.getAttribute("blah", "www.blah.com", null));
		assertNotNull(n.getElement("e", "www.blah.com", 0));
		assertNotNull(n.getAuditPool());

	}
}
