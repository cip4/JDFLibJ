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

package org.cip4.jdflib.resource;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.zip.DataFormatException;

import org.cip4.jdflib.JDFTestCaseBase;
import org.cip4.jdflib.core.ElementName;
import org.cip4.jdflib.core.JDFComment;
import org.cip4.jdflib.core.JDFDoc;
import org.cip4.jdflib.core.JDFResourceLink.EnumUsage;
import org.cip4.jdflib.core.KElement;
import org.cip4.jdflib.core.VString;
import org.cip4.jdflib.datatypes.JDFIntegerRangeList;
import org.cip4.jdflib.node.JDFNode;
import org.cip4.jdflib.node.JDFNode.EnumType;
import org.cip4.jdflib.resource.JDFResource.EnumPartIDKey;
import org.cip4.jdflib.resource.process.JDFComponent;
import org.cip4.jdflib.resource.process.JDFContentData;
import org.cip4.jdflib.resource.process.JDFContentList;
import org.cip4.jdflib.resource.process.JDFEmployee;
import org.cip4.jdflib.resource.process.JDFPageData;
import org.cip4.jdflib.resource.process.JDFPageElement;
import org.cip4.jdflib.resource.process.JDFRunList;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**
 * @author Rainer Prosi, Heidelberger Druckmaschinen
 *
 */
class JDFPageListTest extends JDFTestCaseBase
{
	JDFContentList cl;
	JDFPageList pl;
	JDFDoc d;
	JDFNode n;

	/**
	 *
	 */
	@Test
	void testContentData()
	{
		final JDFContentData cd0 = cl.appendContentData();
		// ToDo cd0.setAttribute(AttributeName.CONTENTLISTINDEX, "1 2 3");
		final KElement book = cd0.appendElement(ElementName.CONTENTMETADATA);
		book.setAttribute("ISBN", "0123456789");
		final JDFComment abstrakt = (JDFComment) book.appendElement("Comment");
		abstrakt.setName("Abstract");
		abstrakt.setText("Abstract of the book\nin english");
		final JDFEmployee editor = (JDFEmployee) book.appendElement(ElementName.EMPLOYEE);
		editor.appendPerson().setFamilyName("authorName");
		editor.setRoles(new VString("Editor", null));
		book.setAttribute("Title", "book thing");
		int p = 1;
		for (int i = 1; i < 4; i++)
		{
			final JDFContentData cd = cl.appendContentData();
			cd.setAttribute("ID", "CD_" + i);
			final KElement chap = cd.appendElement(ElementName.CONTENTMETADATA);
			chap.setAttribute("Title", "Chapter " + i);
			final JDFEmployee localAuthor = (JDFEmployee) chap.appendElement(ElementName.EMPLOYEE);
			localAuthor.appendPerson().setFamilyName("authorName" + i);
			localAuthor.setRoles(new VString("Author", null));

			final JDFPageData pd = pl.appendPageData();
			final JDFIntegerRangeList integerRangeList = new JDFIntegerRangeList();
			integerRangeList.append(p, p + i);
			p += i + 1;
			pd.setAttribute("PageIndex", integerRangeList.toString());
			final JDFPageElement pe = pd.appendPageElement();
			pe.setAttribute("ContentDataRefs", cd.getID());
		}
		pl.setXMLComment("Note that multiple page elements may but need not be specified\nit is also possible to reference only on pageEleemnt that spans the entire book", true);

		writeTest(d.getRoot(), "resources/ContentMetaData.jdf", false, null);
	}

	/**
	 * test that creates a contentdata for a component with multiple blocks
	 * 
	 * @throws DataFormatException
	 */
	@Test
	void testContentDataFinishing() throws DataFormatException
	{
		testContentData();
		final JDFComponent c = (JDFComponent) n.addResource(ElementName.COMPONENT, EnumUsage.Output);
		final JDFComponent c1 = (JDFComponent) c.addPartition(EnumPartIDKey.BlockName, "Stack1");
		final JDFComponent c2 = (JDFComponent) c.addPartition(EnumPartIDKey.BlockName, "Stack2");
		c.refElement(pl);
		c1.setPageListIndex(new JDFIntegerRangeList("0~8"));
		c2.setPageListIndex(new JDFIntegerRangeList("9~16"));
		c.setXMLComment("this is the output component with two stacks\n the imposition engine is aware of the pagelist index and can set it appropriately", true);
		d.write2File(sm_dirTestDataTemp + "ContentMetaDataStack.jdf", 2, false);
	}

	/**
	 *
	 * @throws Exception
	 */
	@Test
	void testGetNPage() throws Exception
	{
		assertEquals(pl.getNPage(), 0);
		final JDFPageData pd1 = pl.appendPageData();
		assertEquals(pl.getNPage(), 1);
		final JDFPageData pd2 = pl.appendPageData();
		assertEquals(pl.getNPage(), 2);
		pd1.setPageIndex(new JDFIntegerRangeList("0 2 4"));
		pd2.setPageIndex(new JDFIntegerRangeList("1 3 5"));
		assertEquals(pl.getNPage(), 6);
		pd2.setPageIndex(new JDFIntegerRangeList("1 3 4 5"));
		assertEquals(pl.getNPage(), 6);
	}

	/**
	 *
	 * @throws Exception
	 */
	@Test
	void testIsIndexed() throws Exception
	{
		assertTrue(pl.isIndexed());
		final JDFPageData pd1 = pl.appendPageData();
		assertFalse(pl.isIndexed());
		final JDFPageData pd2 = pl.appendPageData();
		assertFalse(pl.isIndexed());
		pd1.setPageIndex(0);
		assertFalse(pl.isIndexed());
		pd2.setPageIndex(1);
		assertTrue(pl.isIndexed());
		pd1.setPageIndex(new JDFIntegerRangeList("0 2 4"));
		pd2.setPageIndex(new JDFIntegerRangeList("1 3 5"));
		assertTrue(pl.isIndexed());
	}

	/**
	 *
	 * @throws Exception
	 */
	@Test
	void testIsNormal() throws Exception
	{
		assertTrue(pl.isNormal());
		final JDFPageData pd1 = pl.appendPageData();
		assertFalse(pl.isNormal());
		final JDFPageData pd2 = pl.appendPageData();
		assertFalse(pl.isNormal());
		pd1.setPageIndex(0);
		assertFalse(pl.isNormal());
		pd2.setPageIndex(1);
		assertTrue(pl.isNormal());
		pd1.setPageIndex(new JDFIntegerRangeList("0 2 4"));
		pd2.setPageIndex(new JDFIntegerRangeList("1 3 5"));
		assertFalse(pl.isNormal());
	}

	/**
	 *
	 * @throws Exception
	 */
	@Test
	void testUniqueIndex() throws Exception
	{
		assertTrue(pl.isNormal());
		final JDFPageData pd1 = pl.appendPageData();
		assertFalse(pl.isNormal());
		final JDFPageData pd2 = pl.appendPageData();

		pd1.setPageIndex(new JDFIntegerRangeList("0 2 4"));
		pd2.setPageIndex(new JDFIntegerRangeList("1 3 4 5 "));
		assertFalse(pl.isNormal());
		pl.uniqueIndex();
		assertTrue(pl.isNormal());
		assertEquals(pl.getChildArrayByClass(JDFPageData.class, false, 0).size(), 6);
	}

	/**
	 *
	 * @throws Exception
	 */
	@Test
	void testUniqueIndexMissing() throws Exception
	{
		assertTrue(pl.isNormal());
		final JDFPageData pd1 = pl.appendPageData();
		assertFalse(pl.isNormal());
		final JDFPageData pd2 = pl.appendPageData();

		pd1.setPageIndex(new JDFIntegerRangeList("0  4"));
		pd2.setPageIndex(new JDFIntegerRangeList("1 8"));
		assertFalse(pl.isNormal());
		pl.uniqueIndex();
		assertTrue(pl.isNormal());
		assertTrue(pl.isIndexed());
		assertEquals(pl.getChildArrayByClass(JDFPageData.class, false, 0).size(), 9);
	}

	/**
	 *
	 * @throws Exception
	 */
	@Test
	void testUniqueIndexPerformance() throws Exception
	{
		assertTrue(pl.isNormal());
		for (int i = 0; i <= 10000; i++)
		{
			final JDFPageData pd = pl.appendPageData();
			final JDFIntegerRangeList irl = new JDFIntegerRangeList();
			irl.append(i);
			irl.append(i * 4);
			irl.append(i * 16);
			pd.setPageIndex(irl);

		}
		assertFalse(pl.isNormal());
		pl.uniqueIndex();
		assertTrue(pl.isNormal());
		assertEquals(pl.getChildArrayByClass(JDFPageData.class, false, 0).size(), 160001);
	}

	/**
	 *
	 * @throws Exception
	 */
	@Test
	void testGetPageDataByIndex() throws Exception
	{
		assertEquals(pl.getNPage(), 0);
		final JDFPageData pd1 = pl.appendPageData();
		final JDFPageData pd2 = pl.appendPageData();
		assertEquals(pl.getPageDataByIndex(0), pd1);
		assertEquals(pl.getPageDataByIndex(-2), pd1);
		pd1.setPageIndex(new JDFIntegerRangeList("0 2 4"));
		pd2.setPageIndex(new JDFIntegerRangeList("1 3 5"));
		assertEquals(pl.getPageDataByIndex(0), pd1);
		assertEquals(pl.getPageDataByIndex(-2), pd1);
		assertEquals(pl.getPageDataByIndex(-5), pd2);
	}

	/**
	 *
	 * @throws Exception
	 */
	@Test
	void testAssemblyIds() throws Exception
	{
		assertEquals("", pl.getAssemblyID());
		assertEquals(new VString(), pl.getAssemblyIDs());
		pl.setAssemblyID("a");
		assertEquals("a", pl.getAssemblyID());
		assertEquals(new VString("a"), pl.getAssemblyIDs());
	}

	/**
	 *
	 * @throws Exception
	 */
	@Test
	void testAssemblyId() throws Exception
	{
		assertEquals("", pl.getAssemblyID());
		assertEquals(new VString(), pl.getAssemblyIDs());
		pl.setAssemblyIDs(new VString("a"));
		assertEquals("a", pl.getAssemblyID());
		assertEquals(new VString("a"), pl.getAssemblyIDs());
	}

	@Override
	@BeforeEach
	public void setUp() throws Exception
	{
		super.setUp();
		d = new JDFDoc("JDF");
		n = d.getJDFRoot();
		n.setType(EnumType.Approval);
		final JDFRunList rl = (JDFRunList) n.addResource(ElementName.RUNLIST, EnumUsage.Input);
		pl = rl.appendPageList();
		pl.makeRootResource("PageList", null, true);

		cl = pl.appendContentList();
		cl.makeRootResource("ContentList", null, true);
	}
}
