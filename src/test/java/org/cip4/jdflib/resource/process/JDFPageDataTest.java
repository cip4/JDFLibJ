/*
 *
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

package org.cip4.jdflib.resource.process;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.cip4.jdflib.JDFTestCaseBase;
import org.cip4.jdflib.core.ElementName;
import org.cip4.jdflib.core.JDFDoc;
import org.cip4.jdflib.core.VString;
import org.cip4.jdflib.node.JDFNode;
import org.cip4.jdflib.node.JDFNode.EnumType;
import org.cip4.jdflib.resource.JDFPageList;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**
 *
 *
 * @author rainer prosi
 * @date Jan 9, 2012
 */
class JDFPageDataTest extends JDFTestCaseBase
{

	JDFContentList cl;
	JDFPageList pl;

	/*
	 * (non-Javadoc)
	 *
	 * @see org.cip4.jdflib.JDFTestCaseBase#setUp()
	 */
	@Override
	@BeforeEach
	public void setUp() throws Exception
	{
		super.setUp();
		final JDFDoc doc = new JDFDoc("JDF");
		final JDFNode n = doc.getJDFRoot();
		n.setType(EnumType.Imposition);
		cl = (JDFContentList) n.addResource(ElementName.CONTENTLIST, null);
		pl = (JDFPageList) n.addResource(ElementName.PAGELIST, null);
	}

	/**
	 *
	 */
	@Test
	void testRefContentData()
	{
		for (int i = 0; i < 10; i++)
			assertEquals(cl.appendContentData().getIndex(), i);
		final JDFContentData cd = cl.appendContentData();
		final JDFPageData pd = pl.appendPageData();
		pd.refContentData(cd);
		assertEquals(pd.getPageElement(0).getContentListIndex(), 10);
	}

	/**
	 *
	 */
	@Test
	void testGetPageIndex()
	{
		for (int i = 0; i < 10; i++)
		{
			pl.appendPageData();
			assertEquals(pl.getPageData(i).getPageIndex().getIntegerList().getInt(0), i);
			assertEquals(pl.getPageData(i).getPageIndex().getIntegerList().size(), 1);
		}
	}

	/**
	 *
	 */
	@Test
	void testGetPageIndex2()
	{
		final JDFPageData pd = pl.appendPageData();
		pd.setPageIndex(3);
		final JDFPageData pd4 = pl.appendPageData();
		assertEquals(4, pd4.getPageIndex().getIntegerList().getInt(0));

	}

	/**
	 *
	 */
	@Test
	void testGetPageIndex3()
	{
		final JDFPageData pd = pl.appendPageData();
		pd.setPageIndex(3);
		for (int i = 0; i < 10000; i++)
		{
			pl.appendPageData();
		}
		final JDFPageData pd4 = pl.appendPageData();
		assertEquals(10004, pd4.getPageIndex().getIntegerList().getInt(0));

	}

	/**
	 *
	 */
	@Test
	void testGetPageIndex4()
	{
		for (int i = 0; i < 1000; i++)
		{
			pl.appendPageData();
		}
		final JDFPageData pd = pl.appendPageData();
		pd.setPageIndex(20000);
		for (int i = 0; i < 10000; i++)
		{
			pl.appendPageData();
		}
		final JDFPageData pd4 = pl.appendPageData();
		assertEquals(30001, pd4.getPageIndex().getIntegerList().getInt(0));

	}

	/**
	 *
	 */
	@Test
	void testGetPageIndex5()
	{
		for (int i = 0; i < 1000; i++)
		{
			pl.appendPageData();
		}
		final JDFPageData pd = pl.appendPageData();
		pd.setPageIndex(1);
		for (int i = 0; i < 10000; i++)
		{
			pl.appendPageData();
		}
		final JDFPageData pd4 = pl.appendPageData();
		assertEquals(10002, pd4.getPageIndex().getIntegerList().getInt(0));

	}

	/**
	*
	*/
	@Test
	void testGetAssemblyID2()
	{
		final JDFPageData d = pl.appendPageData();
		assertEquals(d.getAssemblyID(), "");
		pl.setAssemblyID("foo");
		assertEquals(d.getAssemblyID(), "foo");
		d.setAssemblyID("bar");
		assertEquals(d.getAssemblyID(), "bar");
	}

	/**
	*
	*/
	@Test
	void testGetAssemblyIDs2()
	{
		final JDFPageData d = pl.appendPageData();
		assertEquals(d.getAssemblyIDs(), new VString());
		pl.setAssemblyIDs(new VString("foo"));
		assertEquals(d.getAssemblyID(), "foo");
	}

	/**
	 *
	 * @throws Exception
	 */
	@Test
	void testAssemblyIds() throws Exception
	{
		final JDFPageData d = pl.appendPageData();
		assertEquals("", d.getAssemblyID());
		assertEquals(new VString(), d.getAssemblyIDs());
		d.setAssemblyID("a");
		assertEquals("a", d.getAssemblyID());
		assertEquals(new VString("a"), d.getAssemblyIDs());
	}

	/**
	 *
	 * @throws Exception
	 */
	@Test
	void testAssemblyId() throws Exception
	{
		final JDFPageData d = pl.appendPageData();
		assertEquals("", d.getAssemblyID());
		assertEquals(new VString(), d.getAssemblyIDs());
		d.setAssemblyIDs(new VString("a"));
		assertEquals("a", d.getAssemblyID());
		assertEquals(new VString("a"), d.getAssemblyIDs());
	}

	/**
	*
	*/
	@Test
	void testSetPageIndex()
	{
		final JDFPageData d = pl.appendPageData();
		d.setPageIndex(1);
		assertEquals(d.getPageIndex().getElement(0), 1);
		assertEquals(d.getPageIndex().size(), 1);
	}
}
