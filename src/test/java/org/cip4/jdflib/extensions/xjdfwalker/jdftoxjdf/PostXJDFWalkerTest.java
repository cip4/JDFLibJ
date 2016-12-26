/*
 * The CIP4 Software License, Version 1.0
 *
 *
 * Copyright (c) 2001-2016 The International Cooperation for the Integration of Processes in
 * Prepress, Press and Postpress (CIP4). All rights reserved.
 * 
 * Redistribution and use in source and binary forms, with or without modification, are permitted
 * provided that the following conditions are met:
 * 
 * 1. Redistributions of source code must retain the above copyright notice, this list of conditions
 * and the following disclaimer.
 * 
 * 2. Redistributions in binary form must reproduce the above copyright notice, this list of
 * conditions and the following disclaimer in the documentation and/or other materials provided with
 * the distribution.
 * 
 * 3. The end-user documentation included with the redistribution, if any, must include the
 * following acknowledgment: "This product includes software developed by the The International
 * Cooperation for the Integration of Processes in Prepress, Press and Postpress (www.cip4.org)"
 * Alternately, this acknowledgment may appear in the software itself, if and wherever such
 * third-party acknowledgments normally appear.
 * 
 * 4. The names "CIP4" and "The International Cooperation for the Integration of Processes in
 * Prepress, Press and Postpress" must not be used to endorse or promote products derived from this
 * software without prior written permission. For written permission, please contact info@cip4.org.
 * 
 * 5. Products derived from this software may not be called "CIP4", nor may "CIP4" appear in their
 * name, without prior written permission of the CIP4 organization
 * 
 * Usage of this software in commercial products is subject to restrictions. For details please
 * consult info@cip4.org.
 * 
 * THIS SOFTWARE IS PROVIDED ``AS IS'' AND ANY EXPRESSED OR IMPLIED WARRANTIES, INCLUDING, BUT NOT
 * LIMITED TO, THE IMPLIED WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE ARE
 * DISCLAIMED. IN NO EVENT SHALL THE INTERNATIONAL COOPERATION FOR THE INTEGRATION OF PROCESSES IN
 * PREPRESS, PRESS AND POSTPRESS OR ITS CONTRIBUTORS BE LIABLE FOR ANY DIRECT, INDIRECT, INCIDENTAL,
 * SPECIAL, EXEMPLARY, OR CONSEQUENTIAL DAMAGES (INCLUDING, BUT NOT LIMITED TO, PROCUREMENT OF
 * SUBSTITUTE GOODS OR SERVICES; LOSS OF USE, DATA, OR PROFITS; OR BUSINESS INTERRUPTION) HOWEVER
 * CAUSED AND ON ANY THEORY OF LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY, OR TORT (INCLUDING
 * NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE OF THIS SOFTWARE, EVEN IF ADVISED OF
 * THE POSSIBILITY OF SUCH DAMAGE.
 * ====================================================================
 *
 * This software consists of voluntary contributions made by many individuals on behalf of the The
 * International Cooperation for the Integration of Processes in Prepress, Press and Postpress and
 * was originally based on software copyright (c) 1999-2001, Heidelberger Druckmaschinen AG
 * copyright (c) 1999-2001, Agfa-Gevaert N.V.
 *
 * For more information on The International Cooperation for the Integration of Processes in
 * Prepress, Press and Postpress , please see <http://www.cip4.org/>.
 *
 *
 */
package org.cip4.jdflib.extensions.xjdfwalker.jdftoxjdf;

import org.cip4.jdflib.JDFTestCaseBase;
import org.cip4.jdflib.core.AttributeName;
import org.cip4.jdflib.core.ElementName;
import org.cip4.jdflib.core.JDFDoc;
import org.cip4.jdflib.core.JDFElement;
import org.cip4.jdflib.core.JDFElement.EnumVersion;
import org.cip4.jdflib.core.JDFResourceLink.EnumUsage;
import org.cip4.jdflib.core.KElement;
import org.cip4.jdflib.extensions.ResourceHelper;
import org.cip4.jdflib.extensions.SetHelper;
import org.cip4.jdflib.extensions.XJDFConstants;
import org.cip4.jdflib.extensions.XJDFHelper;
import org.junit.Test;

public class PostXJDFWalkerTest extends JDFTestCaseBase
{

	/**
	 * 
	 */
	@Test
	public void testAmountsNull()
	{
		XJDFHelper h = new XJDFHelper("a", "p", null);
		SetHelper sni = h.getCreateResourceSet(ElementName.NODEINFO, EnumUsage.Input);
		ResourceHelper pi = sni.getCreatePartition(null, true);
		pi.setAmount(42, null, true);
		pi.setAmount(2, null, false);

		PostXJDFWalker w = new PostXJDFWalker((JDFElement) h.getRoot());
		w.walkTree(h.getRoot(), null);
		assertNull(h.getRoot().getXPathAttribute("AuditPool/AuditResource/ResourceInfo/ResourceSet/Resource/AmountPool/PartAmount/@Amount", null));
	}

	/**
	 * 
	 */
	@Test
	public void testAmounts()
	{
		XJDFHelper h = new XJDFHelper("a", "p", null);
		SetHelper sni = h.getCreateResourceSet(ElementName.NODEINFO, EnumUsage.Input);
		ResourceHelper pi = sni.getCreatePartition(null, true);
		pi.setAmount(42, null, true);
		pi.getAmountPool().getPartAmount(0).setActualAmount(66);

		PostXJDFWalker w = new PostXJDFWalker((JDFElement) h.getRoot());
		w.walkTree(h.getRoot(), null);
		assertEquals(h.getRoot().getXPathAttribute("AuditPool/AuditResource/ResourceInfo/ResourceSet/Resource/AmountPool/PartAmount/@Amount", null), "66");

	}

	/**
	 * 
	 */
	@Test
	public void testAuditPool()
	{
		XJDFHelper h = new XJDFHelper("a", "p", null);
		h.setXPathValue("AuditPool/AuditCreated/@ID", "42");
		PostXJDFWalker w = new PostXJDFWalker((JDFElement) h.getRoot());
		w.walkTree(h.getRoot(), null);
		assertNotNull(h.getRoot().getXPathElement("AuditPool/AuditCreated"));
		assertNotNull(h.getRoot().getXPathElement("AuditPool/AuditCreated/Header"));
		assertNull(h.getRoot().getXPathElement("AuditPool/Header"));
	}

	/**
	 * 
	 */
	@Test
	public void testHeadbandStrip()
	{
		XJDFHelper h = new XJDFHelper("a", "p", null);
		h.appendResourceSet(ElementName.HEADBANDAPPLICATIONPARAMS, EnumUsage.Input).appendPartition(null, true).getResource().setAttribute(AttributeName.STRIPMATERIAL, "b1");
		PostXJDFWalker w = new PostXJDFWalker((JDFElement) h.getRoot());
		w.walkTree(h.getRoot(), null);
		assertEquals(h.getSet(ElementName.MISCCONSUMABLE, EnumUsage.Input, "BackStrip").getPartition(0).getResource().getAttribute(XJDFConstants.TypeDetails), "b1");
	}

	/**
	 * 
	 */
	@Test
	public void testHeadbandColor()
	{
		XJDFHelper h = new XJDFHelper("a", "p", null);
		KElement band = h.appendResourceSet(ElementName.HEADBANDAPPLICATIONPARAMS, EnumUsage.Input).appendPartition(null, true).getResource();
		band.setAttribute(AttributeName.TOPCOLOR, "Black");
		band.setAttribute(AttributeName.TOPBRAND, "b1");
		PostXJDFWalker w = new PostXJDFWalker((JDFElement) h.getRoot());
		w.walkTree(h.getRoot(), null);
		assertEquals(h.getSet(ElementName.MISCCONSUMABLE, EnumUsage.Input, "HeadBand").getPartition(0).getResource().getAttribute(AttributeName.COLOR), "Black");
		assertEquals(h.getSet(ElementName.MISCCONSUMABLE, EnumUsage.Input, "HeadBand").getPartition(0).getBrand(), "b1");
	}

	/**
	 * 
	 */
	@Test
	public void testAuditOrder()
	{
		XJDFHelper h = new XJDFHelper("a", "p", null);
		h.setXPathValue("AuditPool/AuditCreated/@ID", "42");
		h.setXPathValue("AuditPool/AuditCreated/Foo/@Bar", "foo");
		PostXJDFWalker w = new PostXJDFWalker((JDFElement) h.getRoot());
		w.walkTree(h.getRoot(), null);
		assertNotNull(h.getRoot().getXPathElement("AuditPool/AuditCreated"));
		KElement head = h.getRoot().getXPathElement("AuditPool/AuditCreated/Header");
		KElement foo = h.getRoot().getXPathElement("AuditPool/AuditCreated/Foo");
		assertEquals(head.getNextSibling(), foo);
	}

	/**
	 * 
	 */
	@Test
	public void testHeaderXJMFOrder()
	{
		KElement x = new JDFDoc(XJDFConstants.XJMF, EnumVersion.Version_2_0).getRoot();
		KElement c = x.appendElement("CommandSubmitQueueEntry");
		KElement h = x.appendElement(XJDFConstants.HEADER);
		PostXJDFWalker w = new PostXJDFWalker((JDFElement) x);
		w.walkTree(x, null);
		assertEquals(x.getElement(null), h);
		assertEquals(h.getNextSibling(), c);
	}

	/**
	 * 
	 */
	@Test
	public void testHeaderMessageOrder()
	{
		KElement x = new JDFDoc(XJDFConstants.XJMF, EnumVersion.Version_2_0).getRoot();
		KElement c = x.appendElement("CommandSubmitQueueEntry");
		KElement h = c.appendElement(XJDFConstants.HEADER);
		KElement h2 = c.appendElement(XJDFConstants.AssemblingIntent);
		PostXJDFWalker w = new PostXJDFWalker((JDFElement) x);
		w.walkTree(x, null);
		assertEquals(c.getElement(null), h);
		assertEquals(h.getNextSibling(), h2);
	}

}
