/*
 * The CIP4 Software License, Version 1.0
 *
 *
 * Copyright (c) 2001-2010 The International Cooperation for the Integration of
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

import java.util.Iterator;

import org.cip4.jdflib.JDFTestCaseBase;
import org.cip4.jdflib.core.AttributeName;
import org.cip4.jdflib.core.ElementName;
import org.cip4.jdflib.core.JDFDoc;
import org.cip4.jdflib.core.JDFResourceLink;
import org.cip4.jdflib.core.KElement;
import org.cip4.jdflib.core.VElement;
import org.cip4.jdflib.core.VString;
import org.cip4.jdflib.core.JDFResourceLink.EnumUsage;
import org.cip4.jdflib.core.KElement.EnumValidationLevel;
import org.cip4.jdflib.datatypes.JDFIntegerRange;
import org.cip4.jdflib.datatypes.JDFIntegerRangeList;
import org.cip4.jdflib.node.JDFNode;
import org.cip4.jdflib.pool.JDFResourcePool;
import org.cip4.jdflib.resource.JDFResource.EnumPartIDKey;
import org.cip4.jdflib.resource.devicecapability.JDFIntegerEvaluation;
import org.cip4.jdflib.resource.devicecapability.JDFNameEvaluation;
import org.cip4.jdflib.resource.devicecapability.JDFStringEvaluation;
import org.cip4.jdflib.resource.devicecapability.JDFand;
import org.cip4.jdflib.resource.devicecapability.JDFTerm.EnumTerm;
import org.cip4.jdflib.resource.process.JDFRunList.JDFRunData;
import org.cip4.jdflib.resource.process.prepress.JDFColorSpaceConversionOp;
import org.cip4.jdflib.resource.process.prepress.JDFColorSpaceConversionParams;
import org.cip4.jdflib.util.CPUTimer;

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
	public final void testCollapseNPage()
	{
		final JDFRunList rl1 = rl.addPDF("file:///file1.pdf", 0, 2);
		final JDFRunList rl2 = rl.addPDF("file:///file2.pdf", 1, 3);
		assertEquals(rl.getNPage(), 6);
		assertEquals(rl1.getNPage(), 3);
		assertEquals(rl2.getNPage(), 3);

		rl.collapse(false, true);
		assertEquals(rl.getNPage(), 6);
		assertEquals(rl1.getNPage(), 3);
		assertEquals(rl2.getNPage(), 3);
		final JDFRunList rl3 = rl.addPDF("file:///file3.pdf", 1, 3);
		assertEquals(rl.getNPage(), 9);
		rl.expand(false);
		assertEquals(rl.getNPage(), 9);
		assertEquals(rl1.getNPage(), 3);
		assertEquals(rl2.getNPage(), 3);
		rl.collapse(false, true);
		assertEquals(rl.getNPage(), 9);
		assertEquals(rl1.getNPage(), 3);
		assertEquals(rl2.getNPage(), 3);
		assertEquals(rl3.getNPage(), 3);

	}

	/**
	 * 
	 */
	public final void testAddRun()
	{
		final JDFRunList rl2 = rl.addRun("f1.pdf", 0, -1);
		assertFalse(rl2.hasAttribute_KElement(AttributeName.NPAGE, null, false));
		assertFalse(rl.hasAttribute_KElement(AttributeName.NPAGE, null, false));
	}

	/**
	 * 
	 */
	public final void testAddRunPerformance()
	{
		final CPUTimer ct = new CPUTimer(false);

		for (int i = 1; i < 1000; i++)
		{
			ct.start();
			final JDFRunList rl2 = rl.addRun("f1.pdf", 0, 3);
			assertTrue(rl2.hasAttribute_KElement(AttributeName.NPAGE, null, false));
			assertEquals(rl.getNPage(), 4 * i);
			if (i % 50 == 0)
				System.out.println(i + " " + ct.toString());
		}
	}

	/**
	 * 
	 */
	public final void testGetFileURL()
	{
		rl.setFileURL("./foo/bar.pdf");
		rl.setDirectory("File://c/fnarf");
		assertEquals(rl.getFileURL(), "File://c/fnarf/foo/bar.pdf");
	}

	/**
	 * 
	 */
	public final void testSetPages()
	{
		final JDFIntegerRangeList integerRangeList = new JDFIntegerRangeList(new JDFIntegerRange(0, -1, 8));
		rl.setPages(integerRangeList);
		assertEquals(rl.getPages(), integerRangeList);
		assertEquals(rl.getNPage(), 8);
	}

	/**
	 * 
	 */
	public final void testGetMimeType()
	{
		final JDFResourcePool resPool = root.getCreateResourcePool();
		final KElement kElem = resPool.appendResource(ElementName.RUNLIST, null, null);
		assertTrue(kElem instanceof JDFRunList);
		final JDFRunList ruli = (JDFRunList) kElem;
		assertNull(ruli.getFileMimeType());
		kElem.setXPathAttribute("LayoutElement/FileSpec/@MimeType", "application/pdf");
		assertEquals(ruli.getFileMimeType(), "application/pdf");
	}

	/**
	 * 
	 */
	public final void testGetTruePage()
	{
		final JDFResourcePool resPool = root.getCreateResourcePool();
		final JDFRunList ruli = (JDFRunList) resPool.appendResource(ElementName.RUNLIST, null, null);
		assertEquals(ruli.getTruePage(), ruli);
		final JDFRunList ruli2 = ruli.addSepRun(new VString("c.pdf m.pdf y.pdf k.pdf", " "), new VString("Cyan Magenta Yellow Black", " "), 0, 4, true);
		assertEquals(ruli.getTruePage(), ruli);
		assertEquals(ruli2.getTruePage(), ruli2);
		final JDFRunList ruli2c = (JDFRunList) ruli2.getElement_KElement(ElementName.RUNLIST, null, 0);
		assertEquals(ruli2.getTruePage(), ruli2);
		assertEquals(ruli2c.getTruePage(), ruli2);
	}

	/**
	 * @throws Exception
	 */
	public final void testGetPages() throws Exception
	{
		final JDFRunList rlp = (JDFRunList) rl.addPartition(EnumPartIDKey.Run, "r1");
		rlp.setPages(new JDFIntegerRangeList("0 ~ -1"));
		rlp.setNPage(7);
		final JDFIntegerRangeList pages = rlp.getPages();
		assertEquals(pages.getDef(), 7);
		assertEquals(pages.getElementCount(), 7);
	}

	/**
	 * Test method for 'org.cip4.jdflib.resource.process.JDFMedia.setDimensionCM(JDFXYPair)'
	 * @throws Exception
	 */
	public final void testGetNPage() throws Exception
	{
		final JDFRunList rlp = (JDFRunList) rl.addPartition(EnumPartIDKey.Run, "r1");
		rlp.setPages(new JDFIntegerRangeList("1 3 5 7"));
		assertEquals(rlp.getNPage(), 4);
		rlp.setNPage(3);
		assertEquals(rlp.getNPage(), 3);
		final JDFRunList rlp2 = (JDFRunList) rl.addPartition(EnumPartIDKey.Run, "r2");
		rlp2.setPages(new JDFIntegerRangeList("0 2 4 6"));
		assertEquals(rlp2.getNPage(), 4);
		rlp2.setNPage(3);
		assertEquals(rlp2.getNPage(), 3);
		assertEquals(rl.getNPage(), 6);
	}

	/**
	 * Test method for 'org.cip4.jdflib.resource.process.JDFMedia.setDimensionCM(JDFXYPair)'
	 * @throws Exception 
	 */
	public final void testGetIndexPartition() throws Exception
	{
		final JDFRunList rlp = (JDFRunList) rl.addPartition(EnumPartIDKey.Run, "r1");
		rlp.setPages(new JDFIntegerRangeList("1 3 5 7"));
		assertEquals(rl.getIndexPartition(0), rlp);
		assertEquals(rl.getIndexPartition(3), rlp);
		rlp.setNPage(3);
		final JDFRunList rlp2 = (JDFRunList) rl.addPartition(EnumPartIDKey.Run, "r2");
		rlp2.setPages(new JDFIntegerRangeList("0 2 4 6"));
		assertEquals(rl.getIndexPartition(3), rlp2);
		assertEquals(rl.getIndexPartition(6), rlp2);
		assertNull(rl.getIndexPartition(7));
	}

	/**
	 * Test method for 'org.cip4.jdflib.resource.process.JDFMedia.setDimensionCM(JDFXYPair)'
	 * @throws Exception 
	 */
	public final void testGetPageInFile() throws Exception
	{
		final JDFRunList rlp = (JDFRunList) rl.addPartition(EnumPartIDKey.Run, "r1");
		rlp.setPages(new JDFIntegerRangeList("1 3 5 7"));
		assertEquals(rlp.getPageInFile(0), 1);
		assertEquals(rlp.getPageInFile(1), 3);
		assertEquals(rlp.getPageInFile(3), 7);
		rlp.setNPage(3);
		assertEquals(rlp.getPageInFile(3), -1);
		final JDFRunList rlp2 = (JDFRunList) rl.addPartition(EnumPartIDKey.Run, "r2");
		rlp2.setPages(new JDFIntegerRangeList("0 2 4 6"));
		assertEquals(rlp2.getPageInFile(0), -1);
		assertEquals(rlp2.getPageInFile(3), 0);
		assertEquals(rlp2.getPageInFile(5), 4);
		assertEquals(rlp2.getPageInFile(6), 6);
		rlp2.setNPage(3);
		assertEquals(rlp2.getPageInFile(6), -1);
		rlp2.setNPage(4);
		assertEquals(rlp2.getPageInFile(6), 6);
	}

	/**
	 * Test method for 'org.cip4.jdflib.resource.process.JDFMedia.setDimensionCM(JDFXYPair)'
	 */
	public final void testGetPageLeaves()
	{
		final JDFRunList rlp = (JDFRunList) rl.addPartition(EnumPartIDKey.Run, "r1");
		final JDFRunList rlp2 = (JDFRunList) rl.addPartition(EnumPartIDKey.Run, "r2");
		VElement v = rl.getPageLeaves();
		assertTrue(v.contains(rlp));
		assertTrue(v.contains(rlp2));
		assertEquals(v.size(), 2);
		final JDFRunList rlp21 = (JDFRunList) rlp2.addPartition(EnumPartIDKey.RunSet, "s1");
		final JDFRunList rlp22 = (JDFRunList) rlp2.addPartition(EnumPartIDKey.RunSet, "s2");
		v = rl.getPageLeaves();
		assertTrue(v.contains(rlp));
		assertFalse(v.contains(rlp2));
		assertTrue(v.contains(rlp21));
		assertTrue(v.contains(rlp22));
		assertEquals(v.size(), 3);
		rlp21.setIsPage(false);
		rlp22.setIsPage(false);
		v = rl.getPageLeaves();
		assertTrue(v.contains(rlp));
		assertTrue(v.contains(rlp2));
		assertEquals(v.size(), 2);
		v = rlp2.getPageLeaves();
		assertTrue(v.contains(rlp2));
		assertEquals(v.size(), 1);

	}

	/**
	 * Test method for 'org.cip4.jdflib.resource.process.JDFMedia.setDimensionCM(JDFXYPair)'
	 * @throws Exception 
	 */
	public final void testGetIndex() throws Exception
	{
		final JDFRunList rlp = (JDFRunList) rl.addPartition(EnumPartIDKey.Run, "r1");
		rlp.setPages(new JDFIntegerRangeList("1 3 5 7"));
		assertEquals("first partition starts at 0", rlp.getFirstIndex(), 0);
		assertEquals(rlp.getLastIndex(), 3);
		rlp.setNPage(3);
		assertEquals(rlp.getFirstIndex(), 0);
		assertEquals(rlp.getLastIndex(), 2);
		final JDFRunList rlp2 = (JDFRunList) rl.addPartition(EnumPartIDKey.Run, "r2");
		rlp2.setPages(new JDFIntegerRangeList("0 2 4 6"));
		assertEquals(rlp2.getFirstIndex(), 3);
		assertEquals(rlp2.getLastIndex(), 6);
		rlp2.setNPage(2);
		assertEquals(rlp2.getFirstIndex(), 3);
		assertEquals(rlp2.getLastIndex(), 4);
		final JDFRunList rlp3 = (JDFRunList) rl.addPartition(EnumPartIDKey.Run, "r3");
		rlp2.setLogicalPage(11);
		rlp3.setPages(new JDFIntegerRangeList("0 2 4 6"));
		assertEquals(rlp3.getFirstIndex(), 13);
		assertEquals(rlp3.getLastIndex(), 16);
		rlp3.setNPage(2);
		rlp3.setLogicalPage(22);
		assertEquals(rlp3.getFirstIndex(), 22);
		assertEquals(rlp3.getLastIndex(), 23);
	}

	/**
	 * Test method for 'org.cip4.jdflib.resource.process.JDFMedia.setDimensionCM(JDFXYPair)'
	 * @throws Exception 
	 */
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
			assertEquals(n, ri.runIndex);
			assertEquals(n < 3 ? rlp : rlp2, ri.runList);
			n++;
		}
		assertEquals(n, 7);
	}

	/**
	 * performance check for the runlist iterator
	 * 
	 * @throws Exception
	 */
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
			assertEquals(n, ri.runIndex);
			assertEquals(((ri.getPageInFile() - 1) / 2) % 4, n % 4);
			n++;
		}
		assertEquals(n, 4 * nMax);
	}

	/**
	 * experimental mapping of tags to partition keys
	 */
	public void testTagMapTiff()
	{
		final KElement tagMap = rl.appendElement(METADATA_MAP);
		tagMap.setAttribute("BoundaryKey", "EndOfDocument");
		final KElement tagSet = tagMap.appendElement(EXPR);
		tagSet.setAttribute("Path", "/x3141");
		tagMap.setXMLComment("This tagmap specifies which document structure corresponds to a Document\n"
				+ " thus incrementing DocIndex or forcing an implicit RunList/@EndofDocument=true\n D100 is the tiff tag 0x3141");

		rl.setFileURL("bigVariable.tiff");
		rl.setXMLComment("this runlist points to a tiff file with arbitrary structural tagging defined in the tiff tags");
	}

	/**
	 * experimental mapping of tags to partition keys
	 */
	public void testObjectTagsMetadata()
	{
		final KElement tagMap = rl.appendElement(METADATA_MAP);
		tagMap.setXMLComment("This tagmap specifies The path for the NMTOKEN \"ObjectTag\"");
		tagMap.setAttribute("Name", "ObjectTags");
		tagMap.setAttribute(AttributeName.VALUEFORMAT, "%s");
		tagMap.setAttribute(AttributeName.CONTEXT, "Object");
		tagMap.setAttribute(AttributeName.DATATYPE, "NMTOKEN");
		tagMap.setAttribute(AttributeName.VALUETEMPLATE, "AnyName1");
		tagMap.addNameSpace("TIFFXMP", "http://ns.adobe.com/tiff/1.0");
		final String[] ss = new String[] { "Acme", "Bcme", "Ccme" };
		for (int i = 0; i < ss.length; i++)
		{
			final String s = ss[i];
			final KElement tagSet = tagMap.appendElement(EXPR);
			tagSet.setAttribute("Name", "AnyName1");
			tagSet.setAttribute("Value", s);

			final JDFStringEvaluation eval = (JDFStringEvaluation) tagSet.appendElement(ElementName.STRINGEVALUATION);
			eval.setAttribute("Path", "TIFFXMP:Make");
			eval.setRegExp("(.*)" + s + "(.*)");
			eval.setXMLComment("Any acme camera is mapped to \"" + s + "\"");
		}
		final JDFColorSpaceConversionParams csp = (JDFColorSpaceConversionParams) root.addResource(ElementName.COLORSPACECONVERSIONPARAMS, EnumUsage.Input);
		csp.setXMLComment("This ColorSpaceConversionParams treats Acme and Bcme cameras the same but differentiates for Ccme");
		final JDFColorSpaceConversionOp op1 = csp.appendColorSpaceConversionOp();
		op1.setAttribute("ObjectTags", "Acme Bcme");
		final JDFColorSpaceConversionOp op2 = csp.appendColorSpaceConversionOp();
		op2.setAttribute("ObjectTags", "Ccme");
		doc.write2File(sm_dirTestDataTemp + "objectTags.jdf", 2, false);
	}

	/**
	 * experimental mapping of tags to partition keys
	 * @throws Exception
	 */
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
		lo.setXMLComment("Layout for versioned product");

		final KElement metaMap = rl.appendElement(METADATA_MAP);
		metaMap.setXMLComment("The MetadataMap element maps arbitrary tags in the document to a structural RunTag partition key\n" + "Note that any partition key may be mapped.\n"
				+ "Note also that although an XPath syntax is used, this may be mapped to any hierarchical structure including but not limited to XML.\n");

		metaMap.setAttribute("Name", "RunTags");
		metaMap.setAttribute(AttributeName.DATATYPE, "PartIDKey");
		metaMap.setAttribute(AttributeName.VALUEFORMAT, "%s%s");
		metaMap.setAttribute(AttributeName.VALUETEMPLATE, "sex,section");

		KElement expr = metaMap.appendElement(EXPR);
		expr.setXMLComment("This expression maps the value of /Dokument/Rezipient/@Sex to a variable \"sex\"\n" + "The Mapping is unconditional, therefore no Term is required");
		expr.setAttribute("Name", "sex");
		expr.setAttribute("Path", "/Dokument/Rezipient/@Sex");

		expr = metaMap.appendElement(EXPR);
		expr.setXMLComment("Maps all elements with /Dokument/Sektion=Einband to Cover");
		expr.setAttribute("Name", "section");
		expr.setAttribute("Value", "Cover");
		JDFNameEvaluation nev = (JDFNameEvaluation) expr.appendElement(ElementName.NAMEEVALUATION);
		nev.setAttribute("Path", "/Dokument/Sektion");
		nev.setRegExp("Einband");

		expr = metaMap.appendElement(EXPR);
		expr.setXMLComment("Maps all elements with /Dokument/Sektion=HauptTeil and >50 pages to BigBody");
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
		expr.setXMLComment("Maps all elements with /Dokument/Sektion=HauptTeil and <=50 pages to SmallBody");
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
		rl.setXMLComment("this runlist points to a ppml with arbitrary structural tagging");

		final JDFResourceLink rll = root.getLink(rl, null);
		rll.appendPart().setDocIndex(new JDFIntegerRangeList("10 ~ 20"));
		rll.setXMLComment("this link selects the 11-20 document");

		doc.write2File(sm_dirTestDataTemp + "metadataMap.jdf", 2, false);
	}

	/**
	 * 
	 */
	public void testSeparatedTiff()
	{
		final VString v1 = new VString("Cyan Magenta Yello Black", " ");
		final VString v2 = new VString();
		for (int i = 0; i < v1.size(); i++)
		{
			v2.add("File://device/dir/" + v1.stringAt(i) + ".tif");
		}
		final JDFRunList rl2 = rl.addSepRun(v2, v1, 0, 0, false);
		assertTrue(rl2.isValid(EnumValidationLevel.Complete));

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see junit.framework.TestCase#setUp()
	 */
	@Override
	protected void setUp() throws Exception
	{
		KElement.setLongID(false);
		super.setUp();
		doc = new JDFDoc("JDF");
		root = doc.getJDFRoot();
		rl = (JDFRunList) root.addResource(ElementName.RUNLIST, EnumUsage.Input);

	}

}
