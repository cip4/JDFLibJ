/*
 * The CIP4 Software License, Version 1.0
 *
 *
 * Copyright (c) 2001-2021 The International Cooperation for the Integration of Processes in Prepress, Press and Postpress (CIP4). All rights reserved.
 *
 * Redistribution and use in source and binary forms, with or without modification, are permitted provided that the following conditions are met:
 *
 * 1. Redistributions of source code must retain the above copyright notice, this list of conditions and the following disclaimer.
 *
 * 2. Redistributions in binary form must reproduce the above copyright notice, this list of conditions and the following disclaimer in the documentation and/or other materials provided with the
 * distribution.
 *
 * 3. The end-user documentation included with the redistribution, if any, must include the following acknowledgment: "This product includes software developed by the The International Cooperation for
 * the Integration of Processes in Prepress, Press and Postpress (www.cip4.org)" Alternately, this acknowledgment may appear in the software itself, if and wherever such third-party acknowledgments
 * normally appear.
 *
 * 4. The names "CIP4" and "The International Cooperation for the Integration of Processes in Prepress, Press and Postpress" must not be used to endorse or promote products derived from this software
 * without prior written permission. For written permission, please contact info@cip4.org.
 *
 * 5. Products derived from this software may not be called "CIP4", nor may "CIP4" appear in their name, without prior written permission of the CIP4 organization
 *
 * Usage of this software in commercial products is subject to restrictions. For details please consult info@cip4.org.
 *
 * THIS SOFTWARE IS PROVIDED ``AS IS'' AND ANY EXPRESSED OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO, THE IMPLIED WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE ARE
 * DISCLAIMED. IN NO EVENT SHALL THE INTERNATIONAL COOPERATION FOR THE INTEGRATION OF PROCESSES IN PREPRESS, PRESS AND POSTPRESS OR ITS CONTRIBUTORS BE LIABLE FOR ANY DIRECT, INDIRECT, INCIDENTAL,
 * SPECIAL, EXEMPLARY, OR CONSEQUENTIAL DAMAGES (INCLUDING, BUT NOT LIMITED TO, PROCUREMENT OF SUBSTITUTE GOODS OR SERVICES; LOSS OF USE, DATA, OR PROFITS; OR BUSINESS INTERRUPTION) HOWEVER CAUSED AND
 * ON ANY THEORY OF LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY, OR TORT (INCLUDING NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE OF THIS SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY
 * OF SUCH DAMAGE. ====================================================================
 *
 * This software consists of voluntary contributions made by many individuals on behalf of the The International Cooperation for the Integration of Processes in Prepress, Press and Postpress and was
 * originally based on software copyright (c) 1999-2001, Heidelberger Druckmaschinen AG copyright (c) 1999-2001, Agfa-Gevaert N.V.
 *
 * For more information on The International Cooperation for the Integration of Processes in Prepress, Press and Postpress , please see <http://www.cip4.org/>.
 *
 *
 */

package org.cip4.jdflib.resource.process;

import java.util.Iterator;
import java.util.zip.DataFormatException;

import org.cip4.jdflib.JDFTestCaseBase;
import org.cip4.jdflib.core.*;
import org.cip4.jdflib.core.JDFElement.EnumValidationLevel;
import org.cip4.jdflib.core.JDFResourceLink.EnumUsage;
import org.cip4.jdflib.datatypes.JDFAttributeMap;
import org.cip4.jdflib.datatypes.JDFIntegerRange;
import org.cip4.jdflib.datatypes.JDFIntegerRangeList;
import org.cip4.jdflib.node.JDFNode;
import org.cip4.jdflib.pool.JDFResourcePool;
import org.cip4.jdflib.resource.JDFPageList;
import org.cip4.jdflib.resource.JDFResource.EnumPartIDKey;
import org.cip4.jdflib.resource.devicecapability.JDFIntegerEvaluation;
import org.cip4.jdflib.resource.devicecapability.JDFNameEvaluation;
import org.cip4.jdflib.resource.devicecapability.JDFStringEvaluation;
import org.cip4.jdflib.resource.devicecapability.JDFTerm.EnumTerm;
import org.cip4.jdflib.resource.devicecapability.JDFand;
import org.cip4.jdflib.resource.process.JDFRunList.JDFRunData;
import org.cip4.jdflib.resource.process.prepress.JDFColorSpaceConversionOp;
import org.cip4.jdflib.resource.process.prepress.JDFColorSpaceConversionParams;
import org.cip4.jdflib.util.CPUTimer;
import org.cip4.jdflib.util.UrlUtil;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

/**
 * @author Rainer Prosi, Heidelberger Druckmaschinen
 *
 */
public class JDFRunListTest extends JDFTestCaseBase
{

	/**
	 *
	 */
	private static final String EXPR = "Expr";
	private static final String METADATA_MAP = "MetadataMap";
	private JDFDoc doc;
	private JDFNode root;
	private JDFRunList rl;

	/**
	 *
	 */
	@Test
	public final void testUnPartitionNPage()
	{
		final JDFRunList rl1 = rl.addPDF("file:///file1.pdf", 0, 2);
		final JDFRunList rl2 = rl.addPDF("file:///file1.pdf", 3, 5);
		Assertions.assertEquals(rl.getNPage(), 6);
		rl.removeAttribute(AttributeName.NPAGE);
		Assertions.assertEquals(rl.getNPage(), 6);
		Assertions.assertEquals(rl1.getNPage(), 3);
		Assertions.assertEquals(rl2.getNPage(), 3);
		rl.unpartition(true);
		Assertions.assertEquals(rl.getNPage(), 6);
	}

	/**
	 *
	 */
	@Test
	public final void testExpand()
	{
		final JDFRunList rl1 = (JDFRunList) rl.addPartition(EnumPartIDKey.Run, "r1");
		rl.setNPage(4);
		rl.expand(true);
		Assertions.assertEquals(rl1.getNPage(), 4);
		Assertions.assertEquals(rl.getNPage(), 4);

	}

	/**
	 *
	 */
	@Test
	public final void testCollapseNPage()
	{
		final JDFRunList rl1 = rl.addPDF("file:///file1.pdf", 0, 2);
		final JDFRunList rl2 = rl.addPDF("file:///file2.pdf", 1, 3);
		Assertions.assertEquals(rl.getNPage(), 6);
		rl.removeAttribute(AttributeName.NPAGE);
		Assertions.assertEquals(rl.getNPage(), 6);
		Assertions.assertEquals(rl1.getNPage(), 3);
		Assertions.assertEquals(rl2.getNPage(), 3);
		rl.removeAttribute(AttributeName.NPAGE);
		rl1.removeAttribute(AttributeName.NPAGE);
		rl2.removeAttribute(AttributeName.NPAGE);

		rl.collapse(false, true);
		Assertions.assertEquals(rl.getNPage(), 6);
		Assertions.assertEquals(rl1.getNPage(), 3);
		Assertions.assertEquals(rl2.getNPage(), 3);
		final JDFRunList rl3 = rl.addPDF("file:///file3.pdf", 1, 3);
		Assertions.assertEquals(rl.getNPage(), 9);
		rl.expand(false);
		Assertions.assertEquals(rl.getNPage(), 9);
		Assertions.assertEquals(rl1.getNPage(), 3);
		Assertions.assertEquals(rl2.getNPage(), 3);
		rl.collapse(false, true);
		Assertions.assertEquals(rl.getNPage(), 9);
		Assertions.assertEquals(rl1.getNPage(), 3);
		Assertions.assertEquals(rl2.getNPage(), 3);
		Assertions.assertEquals(rl3.getNPage(), 3);
	}

	/**
	 *
	 */
	@Test
	public final void testCollapseLarge()
	{
		final JDFRunList rl = (JDFRunList) JDFNode.parseFile(sm_dirTestData + "collapse.jdf").getResource(ElementName.RUNLIST, EnumUsage.Input, 0);
		final long t0 = System.currentTimeMillis();
		rl.collapse(false, true);
		Assertions.assertEquals(System.currentTimeMillis() - t0, 42000, 42000);
	}

	/**
	 * @throws DataFormatException
	 *
	 */
	@Test
	public final void testCollapseNPageNoNPageLeaf() throws DataFormatException
	{
		rl.setNPage(4);
		final JDFRunList rl1 = (JDFRunList) rl.addPartition(EnumPartIDKey.Run, "r1");
		rl1.setAttribute("Pages", "0 ~ 1");
		final JDFRunList rl2 = (JDFRunList) rl.addPartition(EnumPartIDKey.Run, "r2");
		rl2.setAttribute("Pages", "2 ~ 3");
		Assertions.assertEquals(rl2.getNPage(), 2);
		rl.collapse(false, true);
		Assertions.assertEquals(rl2.getNPage(), 2);
	}

	/**
	 *
	 */
	@Test
	public final void testFixNPageOnly()
	{
		final JDFRunList rl1 = (JDFRunList) rl.addPartition(EnumPartIDKey.Run, "r1");
		rl1.setNPage(4);
		rl.fixNPage();
		Assertions.assertEquals(rl1.getNPage(), 4);
		Assertions.assertEquals(rl.getNPage(), 4);
	}

	/**
	 *
	 */
	@Test
	public final void testFixNPage()
	{
		final JDFRunList rlSh1 = (JDFRunList) rl.addPartition(EnumPartIDKey.SheetName, "S1");
		final JDFRunList rlSet = (JDFRunList) rlSh1.addPartition(EnumPartIDKey.PageTags, "P1");
		final JDFRunList rl1 = rlSet.addPDF("file:///file1.pdf", 0, 2);
		final JDFRunList rl2 = rlSet.addPDF("file:///file2.pdf", 1, 3);
		rl1.setLogicalPage(0);
		rl2.setLogicalPage(3);
		Assertions.assertEquals(rlSet.getNPage(), 6);
		Assertions.assertEquals(rl.getNPage(), 6);
		final JDFRunList rlSh2 = (JDFRunList) rl.addPartition(EnumPartIDKey.SheetName, "S2");
		final JDFRunList rlSet2 = (JDFRunList) rlSh2.addPartition(EnumPartIDKey.PageTags, "P2");
		final JDFRunList rl21 = rlSet2.addPDF("file:///file3.pdf", 0, 2);
		final JDFRunList rl22 = rlSet2.addPDF("file:///file4.pdf", 1, 3);
		rl21.setLogicalPage(4);
		rl22.setLogicalPage(7);
		Assertions.assertEquals(rlSet2.getNPage(), 6);
		Assertions.assertEquals(rl.getNPage(), 12);
		rl.removeAttribute(AttributeName.NPAGE);
		rl1.removeAttribute(AttributeName.NPAGE);
		rlSh1.removeAttribute(AttributeName.NPAGE);
		rlSet.removeAttribute(AttributeName.NPAGE);
		rl1.removeAttribute(AttributeName.NPAGE);
		rl2.removeAttribute(AttributeName.NPAGE);
		rl.removeAttribute(AttributeName.NPAGE);
		rlSh2.removeAttribute(AttributeName.NPAGE);
		rlSet2.removeAttribute(AttributeName.NPAGE);
		rl21.removeAttribute(AttributeName.NPAGE);
		rl22.removeAttribute(AttributeName.NPAGE);
		rlSet2.removeAttribute(AttributeName.NPAGE);
		rl.fixNPage();
		Assertions.assertEquals(rlSh1.getNPage(), 6);
		Assertions.assertEquals(rlSh2.getNPage(), 6);
		Assertions.assertEquals(rlSet.getNPage(), 6);
		Assertions.assertEquals(rlSet2.getNPage(), 6);
		Assertions.assertEquals(rl.getNPage(), 12);
		Assertions.assertEquals(rl1.getNPage(), 3);
		Assertions.assertEquals(rl2.getNPage(), 3);

	}

	/**
	 *
	 */
	@Test
	public final void testAddRun()
	{
		final JDFRunList rl2 = rl.addRun("f1.pdf", 0, -1);
		Assertions.assertFalse(rl2.hasAttribute_KElement(AttributeName.NPAGE, null, false));
		Assertions.assertFalse(rl.hasAttribute_KElement(AttributeName.NPAGE, null, false));
	}

	/**
	 *
	 */
	@Test
	public final void testAppendElement()
	{
		final JDFRunList rl2 = rl.addRun("f1.pdf", 0, -1);
		final JDFRunList rl3 = (JDFRunList) rl2.appendElement(ElementName.RUNLIST);
		rl3.setRunPage(3);
		rl.addPartIDKey(EnumPartIDKey.RunPage);
		Assertions.assertEquals(rl3, rl.getPartition(new JDFAttributeMap(AttributeName.RUNPAGE, "3"), null));

	}

	/**
	 *
	 */
	@Test
	public final void testAddRunPerformance()
	{
		final CPUTimer ct = new CPUTimer(false);

		for (int i = 1; i < 1000; i++)
		{
			ct.start();
			final JDFRunList rl2 = rl.addRun("f1.pdf", 0, 3);
			Assertions.assertTrue(rl2.hasAttribute_KElement(AttributeName.NPAGE, null, false));
			Assertions.assertEquals(rl.getNPage(), 4 * i);
			if (i % 50 == 0)
				System.out.println(i + " " + ct.toString());
		}
	}

	/**
	 *
	 */
	@Test
	public final void testGetFileURL()
	{
		rl.setFileURL("./foo/bar.pdf");
		rl.setDirectory("File://c/fnarf");
		Assertions.assertEquals(rl.getFileURL(), "File://c/fnarf/foo/bar.pdf");
	}

	/**
	 *
	 */
	@Test
	public final void testGetFileSpecURL()
	{
		rl.setFileSpecURL("File://c/fnarf/foo/bar.pdf");
		Assertions.assertEquals(rl.getFileURL(), "File://c/fnarf/foo/bar.pdf");
		Assertions.assertEquals(rl.getFileMimeType(), UrlUtil.APPLICATION_PDF);
		Assertions.assertNotNull(rl.getFileSpec());
	}

	/**
	 *
	 */
	@Test
	public final void testGetFileSpec()
	{
		final JDFFileSpec fs = rl.getCreateFileSpec();
		Assertions.assertEquals(rl.getFileSpec(), fs);
		Assertions.assertEquals(rl.getFileSpec().getParentNode(), rl);
	}

	/**
	 *
	 */
	@Test
	public final void testGetCreateFileSpec()
	{
		final JDFFileSpec fs = rl.getCreateFileSpec();
		Assertions.assertEquals(rl.getCreateFileSpec(), fs);
	}

	/**
	 * @throws DataFormatException
	 *
	 */
	@Test
	public final void testSetPages() throws DataFormatException
	{
		final JDFIntegerRangeList integerRangeList = new JDFIntegerRangeList(new JDFIntegerRange(0, -1, 8));
		rl.setPages(integerRangeList);
		Assertions.assertEquals(rl.getPages(), integerRangeList);
		Assertions.assertEquals(rl.getNPage(), 8);
		final JDFRunList rl1 = (JDFRunList) rl.addPartition(EnumPartIDKey.Run, "r1");
		rl1.setPages(new JDFIntegerRangeList("4~-1"));
		Assertions.assertEquals(rl1.getNPage(), 4);
	}

	/**
	 *
	 */
	@Test
	public final void testGetMimeType()
	{
		final JDFResourcePool resPool = root.getCreateResourcePool();
		final KElement kElem = resPool.appendResource(ElementName.RUNLIST, null, null);
		Assertions.assertTrue(kElem instanceof JDFRunList);
		final JDFRunList ruli = (JDFRunList) kElem;
		Assertions.assertNull(ruli.getFileMimeType());
		kElem.setXPathAttribute("LayoutElement/FileSpec/@MimeType", "application/pdf");
		Assertions.assertEquals(ruli.getFileMimeType(), "application/pdf");
	}

	/**
	 *
	 */
	@Test
	public final void testGetTruePage()
	{
		final JDFResourcePool resPool = root.getCreateResourcePool();
		final JDFRunList ruli = (JDFRunList) resPool.appendResource(ElementName.RUNLIST, null, null);
		Assertions.assertEquals(ruli.getTruePage(), ruli);
		final JDFRunList ruli2 = ruli.addSepRun(new VString("c.pdf m.pdf y.pdf k.pdf", " "), new VString("Cyan Magenta Yellow Black", " "), 0, 4, true);
		Assertions.assertEquals(ruli.getTruePage(), ruli);
		Assertions.assertEquals(ruli2.getTruePage(), ruli2);
		final JDFRunList ruli2c = (JDFRunList) ruli2.getElement_KElement(ElementName.RUNLIST, null, 0);
		Assertions.assertEquals(ruli2.getTruePage(), ruli2);
		Assertions.assertEquals(ruli2c.getTruePage(), ruli2);
	}

	/**
	 * @throws Exception
	 */
	@Test
	public final void testGetPages() throws Exception
	{
		final JDFRunList rlp = (JDFRunList) rl.addPartition(EnumPartIDKey.Run, "r1");
		rlp.setPages(new JDFIntegerRangeList("0 ~ -1"));
		rlp.setNPage(7);
		final JDFIntegerRangeList pages = rlp.getPages();
		Assertions.assertEquals(pages.getDef(), 7);
		Assertions.assertEquals(pages.getElementCount(), 7);
	}

	/**
	 * @throws Exception
	 */
	@Test
	public final void testGetPages2() throws Exception
	{
		final JDFRunList rlp = (JDFRunList) rl.addPartition(EnumPartIDKey.Run, "r1");
		rlp.setNPage(7);
		final JDFIntegerRangeList pages = rlp.getPages();
		Assertions.assertEquals(pages.getDef(), 7);
		Assertions.assertEquals(pages.getElementCount(), 7);
	}

	/**
	 * @throws Exception
	 */
	@Test
	public final void testGetPagesLogicalPage() throws Exception
	{
		final JDFRunList rlp = (JDFRunList) rl.addPartition(EnumPartIDKey.Run, "r1");
		rlp.setNPage(7);
		rlp.setLogicalPage(2);
		final JDFIntegerRangeList pages = rlp.getPages();
		Assertions.assertEquals(pages.getDef(), 7);
		Assertions.assertEquals(pages.getElementCount(), 7);
		Assertions.assertEquals(0, pages.getElement(0));
		Assertions.assertEquals(2, pages.getElement(2));
		Assertions.assertEquals(6, pages.getElement(6));
	}

	/**
	 * @throws Exception
	 */
	@Test
	public final void testGetPagesNull() throws Exception
	{
		final JDFRunList rlp = (JDFRunList) rl.addPartition(EnumPartIDKey.Run, "r1");
		rlp.setNPage(7);
		final JDFIntegerRangeList pages = rlp.getPages();
		Assertions.assertEquals(pages.getDef(), 7);
		Assertions.assertEquals(pages.getElementCount(), 7);
	}

	/**
	 * Test method for 'org.cip4.jdflib.resource.process.JDFMedia.setDimensionCM(JDFXYPair)'
	 *
	 * @throws Exception
	 */
	@Test
	public final void testGetNPage() throws Exception
	{
		final JDFRunList rlp = (JDFRunList) rl.addPartition(EnumPartIDKey.Run, "r1");
		rlp.setPages(new JDFIntegerRangeList("1 3 5 7"));
		Assertions.assertEquals(rlp.getNPage(), 4);
		rlp.setNPage(3);
		Assertions.assertEquals(rlp.getNPage(), 3);
		final JDFRunList rlp2 = (JDFRunList) rl.addPartition(EnumPartIDKey.Run, "r2");
		rlp2.setPages(new JDFIntegerRangeList("0 2 4 6"));
		Assertions.assertEquals(rlp2.getNPage(), 4);
		rlp2.setNPage(3);
		Assertions.assertEquals(rlp2.getNPage(), 3);
		Assertions.assertEquals(rl.getNPage(), 6);
	}

	/**
	 * Test method for 'org.cip4.jdflib.resource.process.JDFMedia.setDimensionCM(JDFXYPair)'
	 *
	 * @throws Exception
	 */
	@Test
	public final void testGetIndexPartition() throws Exception
	{
		final JDFRunList rlp = (JDFRunList) rl.addPartition(EnumPartIDKey.Run, "r1");
		rlp.setPages(new JDFIntegerRangeList("1 3 5 7"));
		Assertions.assertEquals(rl.getIndexPartition(0), rlp);
		Assertions.assertEquals(rl.getIndexPartition(3), rlp);
		rlp.setNPage(3);
		final JDFRunList rlp2 = (JDFRunList) rl.addPartition(EnumPartIDKey.Run, "r2");
		rlp2.setPages(new JDFIntegerRangeList("0 2 4 6"));
		Assertions.assertEquals(rl.getIndexPartition(3), rlp2);
		Assertions.assertEquals(rl.getIndexPartition(6), rlp2);
		Assertions.assertNull(rl.getIndexPartition(7));
	}

	/**
	 * Test method for 'org.cip4.jdflib.resource.process.JDFMedia.setDimensionCM(JDFXYPair)'
	 *
	 * @throws Exception
	 */
	@Test
	public final void testGetPageListIndex() throws Exception
	{
		JDFIntegerRangeList pageListIndex = rl.getPageListIndex();
		Assertions.assertNotNull(pageListIndex);
		Assertions.assertEquals(pageListIndex.getElementCount(), 0);
		rl.setPageListIndex(new JDFIntegerRangeList("0 ~11"));
		pageListIndex = rl.getPageListIndex();
		Assertions.assertEquals(pageListIndex.getElementCount(), 12);
	}

	/**
	 * Test method for 'org.cip4.jdflib.resource.process.JDFMedia.setDimensionCM(JDFXYPair)'
	 *
	 * @throws Exception
	 */
	@Test
	public final void testGetPageListIndexPartition() throws Exception
	{
		JDFIntegerRangeList pageListIndex = rl.getPageListIndex();
		Assertions.assertNotNull(pageListIndex);
		Assertions.assertEquals(pageListIndex.getElementCount(), 0);
		rl.setPageListIndex(new JDFIntegerRangeList("0 ~11"));
		final JDFRunList rlp = (JDFRunList) rl.addPartition(EnumPartIDKey.Run, "test");
		pageListIndex = rlp.getPageListIndex();
		Assertions.assertEquals(pageListIndex.getElementCount(), 12);
	}

	/**
	 * Test method for 'org.cip4.jdflib.resource.process.JDFMedia.setDimensionCM(JDFXYPair)'
	 *
	 * @throws Exception
	 */
	@Test
	public final void testGetPageListIndexNegative() throws Exception
	{
		final JDFPageList pl = (JDFPageList) root.addResource(ElementName.PAGELIST, EnumUsage.Input);
		pl.appendPageData().setPageIndex(new JDFIntegerRangeList("0 ~11"));
		rl.refPageList(pl);
		rl.setPageListIndex(new JDFIntegerRangeList("0 ~ -1"));
		final JDFIntegerRangeList pageListIndex = rl.getPageListIndex();
		Assertions.assertNotNull(pageListIndex);
		Assertions.assertEquals(pageListIndex.getElementCount(), 12);
	}

	/**
	 * Test method for 'org.cip4.jdflib.resource.process.JDFMedia.setDimensionCM(JDFXYPair)'
	 *
	 * @throws Exception
	 */
	@Test
	public final void testGetPageListIndexNegativePartition() throws Exception
	{
		final JDFPageList pl = (JDFPageList) root.addResource(ElementName.PAGELIST, EnumUsage.Input);
		pl.appendPageData().setPageIndex(new JDFIntegerRangeList("0 ~11"));
		rl.refPageList(pl);
		rl.setPageListIndex(new JDFIntegerRangeList("0 ~ -1"));
		final JDFRunList rlp = (JDFRunList) rl.addPartition(EnumPartIDKey.Run, "test");
		final JDFIntegerRangeList pageListIndex = rlp.getPageListIndex();
		Assertions.assertNotNull(pageListIndex);
		Assertions.assertEquals(pageListIndex.getElementCount(), 12);
	}

	/**
	 * Test method for 'org.cip4.jdflib.resource.process.JDFMedia.setDimensionCM(JDFXYPair)'
	 *
	 * @throws Exception
	 */
	@Test
	public final void testGetPageInFile() throws Exception
	{
		final JDFRunList rlp = (JDFRunList) rl.addPartition(EnumPartIDKey.Run, "r1");
		rlp.setPages(new JDFIntegerRangeList("1 3 5 7"));
		Assertions.assertEquals(rlp.getPageInFile(0), 1);
		Assertions.assertEquals(rlp.getPageInFile(1), 3);
		Assertions.assertEquals(rlp.getPageInFile(3), 7);
		rlp.setNPage(3);
		Assertions.assertEquals(rlp.getPageInFile(3), -1);
		final JDFRunList rlp2 = (JDFRunList) rl.addPartition(EnumPartIDKey.Run, "r2");
		rlp2.setPages(new JDFIntegerRangeList("0 2 4 6"));
		Assertions.assertEquals(rlp2.getPageInFile(0), -1);
		Assertions.assertEquals(rlp2.getPageInFile(3), 0);
		Assertions.assertEquals(rlp2.getPageInFile(5), 4);
		Assertions.assertEquals(rlp2.getPageInFile(6), 6);
		rlp2.setNPage(3);
		Assertions.assertEquals(rlp2.getPageInFile(6), -1);
		rlp2.setNPage(4);
		Assertions.assertEquals(rlp2.getPageInFile(6), 6);
	}

	/**
	 * Test method for 'org.cip4.jdflib.resource.process.JDFMedia.setDimensionCM(JDFXYPair)'
	 */
	@Test
	public final void testGetPageLeaves()
	{
		final JDFRunList rlp = (JDFRunList) rl.addPartition(EnumPartIDKey.Run, "r1");
		final JDFRunList rlp2 = (JDFRunList) rl.addPartition(EnumPartIDKey.Run, "r2");
		VElement v = rl.getPageLeaves();
		Assertions.assertTrue(v.contains(rlp));
		Assertions.assertTrue(v.contains(rlp2));
		Assertions.assertEquals(v.size(), 2);
		final JDFRunList rlp21 = (JDFRunList) rlp2.addPartition(EnumPartIDKey.RunSet, "s1");
		final JDFRunList rlp22 = (JDFRunList) rlp2.addPartition(EnumPartIDKey.RunSet, "s2");
		v = rl.getPageLeaves();
		Assertions.assertTrue(v.contains(rlp));
		Assertions.assertFalse(v.contains(rlp2));
		Assertions.assertTrue(v.contains(rlp21));
		Assertions.assertTrue(v.contains(rlp22));
		Assertions.assertEquals(v.size(), 3);
		rlp21.setIsPage(false);
		rlp22.setIsPage(false);
		v = rl.getPageLeaves();
		Assertions.assertTrue(v.contains(rlp));
		Assertions.assertTrue(v.contains(rlp2));
		Assertions.assertEquals(v.size(), 2);
		v = rlp2.getPageLeaves();
		Assertions.assertTrue(v.contains(rlp2));
		Assertions.assertEquals(v.size(), 1);

	}

	/**
	 * Test method for 'org.cip4.jdflib.resource.process.JDFMedia.setDimensionCM(JDFXYPair)'
	 *
	 * @throws Exception
	 */
	@Test
	public final void testGetIndex() throws Exception
	{
		final JDFRunList rlp = (JDFRunList) rl.addPartition(EnumPartIDKey.Run, "r1");
		rlp.setPages(new JDFIntegerRangeList("1 3 5 7"));
		Assertions.assertEquals(rlp.getFirstIndex(), 0, "first partition starts at 0");
		Assertions.assertEquals(rlp.getLastIndex(), 3);
		rlp.setNPage(3);
		Assertions.assertEquals(rlp.getFirstIndex(), 0);
		Assertions.assertEquals(rlp.getLastIndex(), 2);
		final JDFRunList rlp2 = (JDFRunList) rl.addPartition(EnumPartIDKey.Run, "r2");
		rlp2.setPages(new JDFIntegerRangeList("0 2 4 6"));
		Assertions.assertEquals(rlp2.getFirstIndex(), 3);
		Assertions.assertEquals(rlp2.getLastIndex(), 6);
		rlp2.setNPage(2);
		Assertions.assertEquals(rlp2.getFirstIndex(), 3);
		Assertions.assertEquals(rlp2.getLastIndex(), 4);
		final JDFRunList rlp3 = (JDFRunList) rl.addPartition(EnumPartIDKey.Run, "r3");
		rlp2.setLogicalPage(11);
		rlp3.setPages(new JDFIntegerRangeList("0 2 4 6"));
		Assertions.assertEquals(rlp3.getFirstIndex(), 13);
		Assertions.assertEquals(rlp3.getLastIndex(), 16);
		rlp3.setNPage(2);
		rlp3.setLogicalPage(22);
		Assertions.assertEquals(rlp3.getFirstIndex(), 22);
		Assertions.assertEquals(rlp3.getLastIndex(), 23);
	}

	/**
	 * Test method for 'org.cip4.jdflib.resource.process.JDFMedia.setDimensionCM(JDFXYPair)'
	 *
	 * @throws Exception
	 */
	@Test
	public final void testPageIterator() throws Exception
	{
		final JDFRunList rlp = (JDFRunList) rl.addPartition(EnumPartIDKey.Run, "r1");
		rlp.setPages(new JDFIntegerRangeList("1 3 5 7"));
		rlp.setNPage(3);
		final JDFRunList rlp2 = (JDFRunList) rl.addPartition(EnumPartIDKey.Run, "r2");
		rlp2.setPages(new JDFIntegerRangeList("0 2 4 6"));
		final Iterator<JDFRunData> it = rl.getPageIterator();
		int n = 0;
		while (it.hasNext())
		{
			final JDFRunData ri = it.next();
			Assertions.assertEquals(n, ri.runIndex);
			Assertions.assertEquals(n < 3 ? rlp : rlp2, ri.runList);
			n++;
		}
		Assertions.assertEquals(n, 7);
	}

	/**
	 * performance check for the runlist iterator
	 *
	 * @throws Exception
	 */
	@Test
	public final void testPageIteratorSpeed() throws Exception
	{
		final int nMax = 3000;
		for (int i = 0; i < nMax; i++)
		{
			final JDFRunList rlp = (JDFRunList) rl.addPartition(EnumPartIDKey.Run, "r" + i);
			rlp.setPages(new JDFIntegerRangeList("1 3 5 7"));
			rlp.setFileURL("File://Test" + i + ".pdf");
		}
		final Iterator<JDFRunData> it = rl.getPageIterator();
		int n = 0;

		while (it.hasNext())
		{
			final JDFRunData ri = it.next();
			Assertions.assertEquals(n, ri.runIndex);
			Assertions.assertEquals(((ri.getPageInFile() - 1) / 2) % 4, n % 4);
			n++;
		}
		Assertions.assertEquals(n, 4 * nMax);
	}

	/**
	 * experimental mapping of tags to partition keys
	 */
	@Test
	public void testTagMapTiff()
	{
		final KElement tagMap = rl.appendElement(METADATA_MAP);
		tagMap.setAttribute("BoundaryKey", "EndOfDocument");
		final KElement tagSet = tagMap.appendElement(EXPR);
		tagSet.setAttribute("Path", "/x3141");
		tagMap.setXMLComment("This tagmap specifies which document structure corresponds to a Document\n"
				+ " thus incrementing DocIndex or forcing an implicit RunList/@EndofDocument=true\n D100 is the tiff tag 0x3141", true);

		rl.setFileURL("bigVariable.tiff");
		rl.setXMLComment("this runlist points to a tiff file with arbitrary structural tagging defined in the tiff tags", true);
	}

	/**
	 *
	 */
	// TODO @Stefan @Test
	// public void testMetadataMapSchema() {
	// JDFMetadataMap map = rl.appendMetadataMap();
	// map.getCreateXPathElement("Expr/and/StringEvaluation");
	// map.setDataType(EnumDataType.integer);
	// map.setName("foo");
	// root.setType(EnumType.PDLCreation);
	// String s = rl.getOwnerDocument_JDFElement().write2String(2);
	// JDFParser p = new JDFParser();
	// p.setSchemaLocation(JDFConstants.JDFNAMESPACE, sm_dirTestSchema + "JDF.xsd");
	// JDFDoc dNew = p.parseString(s);
	// XMLDoc dVal = dNew.getValidationResult();
	// assertEquals(dVal.getRoot().getAttribute("ValidationResult"), "Valid");
	// }

	/**
	 * experimental mapping of tags to partition keys
	 */
	@Test
	public void testObjectTagsMetadata()
	{
		final KElement tagMap = rl.appendElement(METADATA_MAP);
		tagMap.setXMLComment("This tagmap specifies The path for the NMTOKEN \"ObjectTag\"", true);
		tagMap.setAttribute("Name", "ObjectTags");
		tagMap.setAttribute(AttributeName.VALUEFORMAT, "%s");
		tagMap.setAttribute(AttributeName.CONTEXT, "Object");
		tagMap.setAttribute(AttributeName.DATATYPE, "NMTOKEN");
		tagMap.setAttribute(AttributeName.VALUETEMPLATE, "AnyName1");
		tagMap.addNameSpace("TIFFXMP", "http://ns.adobe.com/tiff/1.0");
		final String[] ss = new String[] { "Acme", "Bcme", "Ccme" };
		for (final String s : ss)
		{
			final KElement tagSet = tagMap.appendElement(EXPR);
			tagSet.setAttribute("Name", "AnyName1");
			tagSet.setAttribute("Value", s);

			final JDFStringEvaluation eval = (JDFStringEvaluation) tagSet.appendElement(ElementName.STRINGEVALUATION);
			eval.setAttribute("Path", "TIFFXMP:Make");
			eval.setRegExp("(.*)" + s + "(.*)");
			eval.setXMLComment("Any acme camera is mapped to \"" + s + "\"", true);
		}
		final JDFColorSpaceConversionParams csp = (JDFColorSpaceConversionParams) root.addResource(ElementName.COLORSPACECONVERSIONPARAMS, EnumUsage.Input);
		csp.setXMLComment("This ColorSpaceConversionParams treats Acme and Bcme cameras the same but differentiates for Ccme", true);
		final JDFColorSpaceConversionOp op1 = csp.appendColorSpaceConversionOp();
		op1.setAttribute("ObjectTags", "Acme Bcme");
		final JDFColorSpaceConversionOp op2 = csp.appendColorSpaceConversionOp();
		op2.setAttribute("ObjectTags", "Ccme");
		doc.write2File(sm_dirTestDataTemp + "objectTags.jdf", 2, false);
	}

	/**
	 * experimental mapping of tags to partition keys
	 *
	 * @throws Exception
	 */
	@Test
	public void testTagMap() throws Exception
	{

		final JDFLayout lo = (JDFLayout) root.addResource(ElementName.LAYOUT, null, EnumUsage.Input, null, null, null, null);
		final JDFMedia med = (JDFMedia) root.addResource(ElementName.MEDIA, null, EnumUsage.Input, null, null, null, null);
		final JDFMedia medM = (JDFMedia) med.addPartition(EnumPartIDKey.RunTags, "MaleCover");
		final JDFMedia medF = (JDFMedia) med.addPartition(EnumPartIDKey.RunTags, "FemaleCover");
		final JDFMedia medB = (JDFMedia) med.addPartition(EnumPartIDKey.RunTags, "MaleBigBody MaleSmallBody FemaleBigBody FemaleSmallBody");

		final JDFLayout loM = (JDFLayout) lo.addPartition(EnumPartIDKey.RunTags, "MaleCover");
		loM.refElement(medM);
		final JDFLayout loF = (JDFLayout) lo.addPartition(EnumPartIDKey.RunTags, "FemaleCover");
		loF.refElement(medF);
		final JDFLayout loBB = (JDFLayout) lo.addPartition(EnumPartIDKey.RunTags, "MaleBigBody FemaleBigBody");
		loBB.refElement(medB);
		final JDFLayout loSB = (JDFLayout) lo.addPartition(EnumPartIDKey.RunTags, "MaleSmallBody FemaleSmallBody");
		loSB.refElement(medB);
		lo.setXMLComment("Layout for versioned product", true);

		final KElement metaMap = rl.appendElement(METADATA_MAP);
		metaMap.setXMLComment("The MetadataMap element maps arbitrary tags in the document to a structural RunTag partition key\n" + "Note that any partition key may be mapped.\n"
				+ "Note also that although an XPath syntax is used, this may be mapped to any hierarchical structure including but not limited to XML.\n", true);

		metaMap.setAttribute("Name", "RunTags");
		metaMap.setAttribute(AttributeName.DATATYPE, "PartIDKey");
		metaMap.setAttribute(AttributeName.VALUEFORMAT, "%s%s");
		metaMap.setAttribute(AttributeName.VALUETEMPLATE, "sex,section");

		KElement expr = metaMap.appendElement(EXPR);
		expr.setXMLComment("This expression maps the value of /Dokument/Rezipient/@Sex to a variable \"sex\"\n"
				+ "The Mapping is unconditional, therefore no Term is required", true);
		expr.setAttribute("Name", "sex");
		expr.setAttribute("Path", "/Dokument/Rezipient/@Sex");

		expr = metaMap.appendElement(EXPR);
		expr.setXMLComment("Maps all elements with /Dokument/Sektion=Einband to Cover", true);
		expr.setAttribute("Name", "section");
		expr.setAttribute("Value", "Cover");
		JDFNameEvaluation nev = (JDFNameEvaluation) expr.appendElement(ElementName.NAMEEVALUATION);
		nev.setAttribute("Path", "/Dokument/Sektion");
		nev.setRegExp("Einband");

		expr = metaMap.appendElement(EXPR);
		expr.setXMLComment("Maps all elements with /Dokument/Sektion=HauptTeil and >50 pages to BigBody", true);
		expr.setAttribute("Name", "section");
		expr.setAttribute("Value", "BigBody");

		JDFand and = (JDFand) expr.appendElement("and");
		nev = (JDFNameEvaluation) and.appendElement(ElementName.NAMEEVALUATION);
		nev.setAttribute("Path", "/Dokument/Sektion");
		nev.setRegExp("HauptTeil");
		JDFIntegerEvaluation iev = (JDFIntegerEvaluation) and.appendTerm(EnumTerm.IntegerEvaluation);
		iev.setAttribute("Path", "count(PAGE)");
		iev.setValueList(new JDFIntegerRangeList("51~INF"));

		expr = metaMap.appendElement(EXPR);
		expr.setXMLComment("Maps all elements with /Dokument/Sektion=HauptTeil and <=50 pages to SmallBody", true);
		expr.setAttribute("Name", "section");
		expr.setAttribute("Value", "SmallBody");

		and = (JDFand) expr.appendElement("and");
		nev = (JDFNameEvaluation) and.appendElement(ElementName.NAMEEVALUATION);
		nev.setAttribute("Path", "/Dokument/Sektion");
		nev.setRegExp("HauptTeil");
		iev = (JDFIntegerEvaluation) and.appendTerm(EnumTerm.IntegerEvaluation);
		iev.setAttribute("Path", "count(PAGE)");
		iev.setValueList(new JDFIntegerRangeList("0~INF"));

		rl.setFileURL("bigVariable.ppml");
		rl.setXMLComment("this runlist points to a ppml with arbitrary structural tagging", true);

		final JDFResourceLink rll = root.getLink(rl, null);
		rll.appendPart().setDocIndex(new JDFIntegerRangeList("10 ~ 20"));
		rll.setXMLComment("this link selects the 11-20 document", true);

		doc.write2File(sm_dirTestDataTemp + "metadataMap.jdf", 2, false);
	}

	/**
	 *
	 */
	@Test
	public void testSeparatedTiff()
	{
		final VString v1 = new VString("Cyan Magenta Yello Black", " ");
		final VString v2 = new VString();
		for (int i = 0; i < v1.size(); i++)
		{
			v2.add("File://device/dir/" + v1.get(i) + ".tif");
		}
		final JDFRunList rl2 = rl.addSepRun(v2, v1, 0, 0, false);
		Assertions.assertTrue(rl2.isValid(EnumValidationLevel.Complete));
	}

	/**
	 *
	 *
	 * @see junit.framework.TestCase#setUp()
	 */
	@Override
	public void setUp() throws Exception
	{
		KElement.setLongID(false);
		super.setUp();
		doc = new JDFDoc("JDF");
		root = doc.getJDFRoot();
		rl = (JDFRunList) root.addResource(ElementName.RUNLIST, EnumUsage.Input);
	}
}
