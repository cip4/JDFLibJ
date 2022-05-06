/*
 * The CIP4 Software License, Version 1.0
 *
 *
 * Copyright (c) 2001-2016 The International Cooperation for the Integration of
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
/**
 *
 */
package org.cip4.jdflib.elementwalker;

import org.cip4.jdflib.JDFTestCaseBase;
import org.cip4.jdflib.core.ElementName;
import org.cip4.jdflib.core.JDFDoc;
import org.cip4.jdflib.core.JDFRefElement;
import org.cip4.jdflib.core.JDFResourceLink;
import org.cip4.jdflib.core.JDFResourceLink.EnumUsage;
import org.cip4.jdflib.core.VElement;
import org.cip4.jdflib.node.JDFNode;
import org.cip4.jdflib.resource.JDFResource;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

/**
 * @author Rainer Prosi, Heidelberger Druckmaschinen
 *
 */
public class UnlinkFinderTest extends JDFTestCaseBase
{

	private JDFNode n;
	private JDFResource r;
	private JDFResource xm;
	private JDFResource m;
	private JDFResourceLink rl;
	private JDFRefElement re;

	/**
	 *
	 */
	@Test
	public void testGetUlinkedRes()
	{
		final UnLinkFinder uf = new UnLinkFinder();
		final VElement v = uf.getUnlinkedResources(n);
		Assertions.assertEquals(v.size(), 1);
		Assertions.assertTrue(v.contains(r));
	}

	/**
	 *
	 */
	@Test
	public void testGetUlinkedAll()
	{
		final UnLinkFinder uf = new UnLinkFinder();
		final VElement v = uf.getAllUnlinked(n);
		Assertions.assertEquals(v.size(), 3);
		Assertions.assertTrue(v.contains(r));
		Assertions.assertTrue(v.contains(rl));
		Assertions.assertTrue(v.contains(re));
	}

	/**
	 *
	 */
	@Test
	public void testGetUlinkedRef()
	{
		final UnLinkFinder uf = new UnLinkFinder();
		final VElement v = uf.getUnlinkedRefs(n);
		Assertions.assertEquals(v.size(), 2);
		Assertions.assertTrue(v.contains(rl));
		Assertions.assertTrue(v.contains(re));
	}

	/**
	 *
	 */
	@Test
	public void testEraseUlinkedRes()
	{
		final UnLinkFinder uf = new UnLinkFinder();
		uf.eraseUnlinkedResources(n);
		VElement v = uf.getUnlinkedResources(n);
		Assertions.assertNull(v);
		v = uf.getUnlinkedRefs(n);
		Assertions.assertEquals(v.size(), 2);
		v = uf.getAllUnlinked(n);
		Assertions.assertEquals(v.size(), 2);

	}

	/**
	 *
	 */
	@Test
	public void testEraseUlinkedRefs()
	{
		final UnLinkFinder uf = new UnLinkFinder();
		uf.eraseUnlinkedRefs(n);
		VElement v = uf.getUnlinkedResources(n);
		Assertions.assertEquals(v.size(), 1);
		v = uf.getAllUnlinked(n);
		Assertions.assertEquals(v.size(), 1);
		v = uf.getUnlinkedRefs(n);
		Assertions.assertNull(v);
	}

	/**
	*
	*/
	@Test
	public void testIgnoreforeign()
	{
		n.getResourcePool().appendElement("foo:bar", "www.foo.org");
		n.getResourceLinkPool().appendElement("foo:blubLink", "www.foo.org");
		xm.appendElement("foo:blubRef", "www.foo.org");
		final UnLinkFinder uf = new UnLinkFinder();
		uf.setIgnoreForeign(true);
		uf.eraseUnlinkedRefs(n);
		VElement v = uf.getUnlinkedResources(n);
		Assertions.assertEquals(v.size(), 1);
		v = uf.getAllUnlinked(n);
		Assertions.assertEquals(v.size(), 1);
		v = uf.getUnlinkedRefs(n);
		Assertions.assertNull(v);
	}

	/**
	 *
	 */
	@Test
	public void testEraseUlinked()
	{
		final UnLinkFinder uf = new UnLinkFinder();
		uf.eraseUnlinked(n);
		VElement v = uf.getUnlinkedResources(n);
		Assertions.assertNull(v);
		v = uf.getUnlinkedRefs(n);
		Assertions.assertNull(v);
		v = uf.getAllUnlinked(n);
		Assertions.assertNull(v);
	}

	@Override
	public void setUp() throws Exception
	{
		super.setUp();
		n = new JDFDoc("JDF").getJDFRoot();
		r = n.appendResourcePool().appendResource("RunList", null, null);
		xm = n.addResource(ElementName.EXPOSEDMEDIA, EnumUsage.Input);
		m = (JDFResource) xm.appendElement("Media");
		m.makeRootResource(null, null, true);
		rl = (JDFResourceLink) n.getResourceLinkPool().appendElement("FooLink");
		re = (JDFRefElement) xm.appendElement("BarRef");
		re.setrRef("barf");
		rl.setAttribute("rRef", "barf");
	}
}
