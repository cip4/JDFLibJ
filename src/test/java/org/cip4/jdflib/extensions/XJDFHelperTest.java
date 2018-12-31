/*
 * The CIP4 Software License, Version 1.0
 *
 *
 * Copyright (c) 2001-2018 The International Cooperation for the Integration of Processes in Prepress, Press and Postpress (CIP4). All rights reserved.
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

package org.cip4.jdflib.extensions;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNotSame;
import static org.junit.Assert.assertNull;

import java.io.File;

import org.cip4.jdflib.JDFTestCaseBase;
import org.cip4.jdflib.core.ElementName;
import org.cip4.jdflib.core.JDFDoc;
import org.cip4.jdflib.core.JDFElement;
import org.cip4.jdflib.core.JDFElement.EnumVersion;
import org.cip4.jdflib.core.JDFResourceLink.EnumUsage;
import org.cip4.jdflib.core.KElement;
import org.cip4.jdflib.core.XMLDoc;
import org.cip4.jdflib.datatypes.JDFAttributeMap;
import org.cip4.jdflib.datatypes.JDFIntegerList;
import org.cip4.jdflib.datatypes.VJDFAttributeMap;
import org.cip4.jdflib.node.JDFNode.EnumType;
import org.cip4.jdflib.resource.process.JDFRunList;
import org.cip4.jdflib.util.FileUtil;
import org.junit.Before;
import org.junit.Test;

/**
 * @author Rainer Prosi, Heidelberger Druckmaschinen *
 */
public class XJDFHelperTest extends JDFTestCaseBase
{
	XJDFHelper theHelper = null;

	/**
	 *
	 *
	 */
	@Test
	public void testGetSet()
	{
		final KElement rlSet = theHelper.appendResourceSet(ElementName.RUNLIST, null).getSet();
		assertEquals(rlSet, theHelper.getSet(ElementName.RUNLIST, 0).getSet());
	}

	/**
	 *
	 *
	 */
	@Test
	public void testAppendSet()
	{
		final KElement rlSet = theHelper.appendSet(null, ElementName.RUNLIST, null).getSet();
		assertEquals(rlSet.getLocalName(), XJDFConstants.ResourceSet);
	}

	/**
	 *
	 *
	 */
	@Test
	public void testAddType()
	{
		theHelper.setTypes("Folding");
		theHelper.addType("Cutting", 0);
		assertEquals(theHelper.getTypes().get(1), "Folding");
		assertEquals(theHelper.getTypes().get(0), "Cutting");
		theHelper.addType("Stitching", -1);
		assertEquals(theHelper.getTypes().get(1), "Folding");
		assertEquals(theHelper.getTypes().get(0), "Cutting");
		assertEquals(theHelper.getTypes().get(2), "Stitching");
		theHelper.addType("Imposition", 0);
		assertEquals(theHelper.getTypes().get(0), "Imposition");
		assertEquals(theHelper.getTypes().get(3), "Stitching");
	}

	/**
	 *
	 *
	 */
	@Test
	public void testIndexOfType()
	{
		theHelper.setTypes("Folding");
		theHelper.addType("Cutting", 0);
		assertEquals(0, theHelper.indexOfType("Cutting", 0));
		assertEquals(-1, theHelper.indexOfType("Cutting", 1));
		assertEquals(-1, theHelper.indexOfType("Cutting", 42));
		assertEquals(1, theHelper.indexOfType("Folding", 0));
		theHelper.addType("Cutting", -1);
		theHelper.addType("Cutting", -1);
		assertEquals(2, theHelper.indexOfType("Cutting", 1));
		assertEquals(3, theHelper.indexOfType("Cutting", 2));
		assertEquals(-1, theHelper.indexOfType("Cutting", 3));
	}

	/**
	 *
	 *
	 */
	@Test
	public void testAddTypeChain()
	{
		theHelper.addType("Cutting").addType("Folding");
		assertEquals(theHelper.getTypes().get(1), "Folding");
		assertEquals(theHelper.getTypes().get(0), "Cutting");
	}

	/**
	 *
	 *
	 */
	@Test
	public void testRemoveType()
	{
		theHelper.setTypes("Folding");
		theHelper.addType("Cutting", 0);
		theHelper.removeType("Foo", 0);
		assertEquals(theHelper.getTypes().get(1), "Folding");
		assertEquals(theHelper.getTypes().get(0), "Cutting");
		theHelper.removeType("Cutting", 0);
		assertEquals(theHelper.getTypes().get(0), "Folding");
	}

	/**
	 *
	 *
	 */
	@Test
	public void testAddTypeCPI()
	{
		theHelper.setTypes("Folding");
		final SetHelper rlSet = theHelper.appendSet(null, ElementName.FOLDINGPARAMS, null);
		rlSet.setCombinedProcessIndex(JDFIntegerList.createIntegerList("0"));
		theHelper.addType("Cutting", 0);
		assertEquals(1, rlSet.getCombinedProcessIndex().getInt(0));
	}

	/**
	 *
	 *
	 */
	@Test
	public void testRemoveTypeCPI()
	{
		theHelper.setTypes("Folding");
		final SetHelper rlSet = theHelper.appendSet(null, ElementName.FOLDINGPARAMS, null);
		rlSet.setCombinedProcessIndex(JDFIntegerList.createIntegerList("0"));
		theHelper.addType("Cutting", 0);
		assertEquals(1, rlSet.getCombinedProcessIndex().getInt(0));
		theHelper.removeType("Cutting", 0);
		assertEquals(0, rlSet.getCombinedProcessIndex().getInt(0));
	}

	/**
	*
	*
	*/
	@Test
	public void testGetCreateResourceSet()
	{
		assertNull(theHelper.getSet(ElementName.RUNLIST, EnumUsage.Input, null));
		final SetHelper sh1 = theHelper.getCreateSet(XJDFConstants.Resource, ElementName.RUNLIST, EnumUsage.Input);
		assertNotNull(sh1);
		final SetHelper sh2 = theHelper.getCreateSet(XJDFConstants.Resource, ElementName.RUNLIST, EnumUsage.Output);
		assertNotNull(sh2);
		assertNotSame(sh1, sh2);

	}

	/**
	*
	*
	*/
	@Test
	public void testGetCreateResource()
	{
		assertNull(theHelper.getSet(ElementName.RUNLIST, EnumUsage.Input, null));
		final JDFRunList rl = (JDFRunList) theHelper.getCreateResource(ElementName.RUNLIST, EnumUsage.Input, null);
		assertNotNull(rl);
		final SetHelper sh = theHelper.getSet(ElementName.RUNLIST, EnumUsage.Input, null);
		assertNotNull(sh);
		assertEquals(1, sh.getPartitions().size());
	}

	/**
	*
	*
	*/
	@Test
	public void testGetCreateResourceSet2()
	{
		assertNull(theHelper.getSet(ElementName.RUNLIST, EnumUsage.Input));
		final SetHelper sh1 = theHelper.getCreateSet(XJDFConstants.Resource, ElementName.RUNLIST, EnumUsage.Input);
		assertNotNull(sh1);
		final SetHelper sh2 = theHelper.getCreateSet(XJDFConstants.Resource, ElementName.RUNLIST, EnumUsage.Output);
		assertNotNull(sh2);
		assertNotSame(sh1, sh2);

	}

	/**
	*
	*
	*/
	@Test
	public void testGetCreateSet4()
	{
		assertNull(theHelper.getSet(ElementName.RUNLIST, EnumUsage.Input));
		final SetHelper sh1 = theHelper.getCreateSet(ElementName.RUNLIST, EnumUsage.Input, "foo", new JDFIntegerList(1));
		assertNotNull(sh1);
		final SetHelper sh2 = theHelper.getCreateSet(ElementName.RUNLIST, EnumUsage.Input, "foo", new JDFIntegerList(2));
		assertNotNull(sh2);
		assertNotSame(sh1, sh2);

	}

	/**
	*
	*
	*/
	@Test
	public void testNumProduct()
	{
		final XJDFHelper h = new XJDFHelper("j", "root", null);
		assertEquals(h.numProductHelpers(true), 0);
		assertEquals(h.numProductHelpers(false), 0);
		final ProductHelper cover = h.appendProduct();
		assertEquals(h.numProductHelpers(true), 1);
		assertEquals(h.numProductHelpers(false), 1);
		final ProductHelper body = h.appendProduct();
		assertEquals(h.numProductHelpers(true), 1);
		assertEquals(h.numProductHelpers(false), 2);
		final ProductHelper book = h.appendProduct();
		book.setRoot();
		book.setChild(cover, 1);
		book.setChild(body, 1);
		assertEquals(h.numProductHelpers(true), 1);
		assertEquals(h.numProductHelpers(false), 3);
	}

	/**
	 *
	 *
	 */
	@Test
	public void testGetproduct()
	{
		final XJDFHelper h = new XJDFHelper("j", "root", null);
		final ProductHelper ph = h.getCreateRootProduct(0);
		ph.setAmount(10);
		ph.setID("iii");
		assertEquals(ph, h.getProduct("iii"));
	}

	/**
	 *
	 *
	 */
	@Test
	public void testGetproductExternalID()
	{
		final XJDFHelper h = new XJDFHelper("j", "root", null);
		final ProductHelper ph = h.getCreateRootProduct(0);
		ph.setAmount(10);
		ph.setExternalID("xid");
		assertEquals(ph, h.getProductByExternalID("xid"));
	}

	/**
	 *
	 *
	 */
	@Test
	public void testGetSets()
	{
		final SetHelper rlSet = theHelper.appendResourceSet("RunList", EnumUsage.Input);
		final SetHelper rlSet2 = theHelper.appendResourceSet("Media", EnumUsage.Output);

		assertEquals(rlSet, theHelper.getSets(ElementName.RUNLIST, null).get(0));
		assertEquals(rlSet2, theHelper.getSets(ElementName.MEDIA, null).get(0));
		assertEquals(rlSet2, theHelper.getSets(ElementName.MEDIA, EnumUsage.Output).get(0));
		assertEquals(rlSet, theHelper.getSets(ElementName.RUNLIST, EnumUsage.Input).get(0));
		assertEquals(rlSet, theHelper.getSets(null, EnumUsage.Input).get(0));
		assertEquals(1, theHelper.getSets(null, EnumUsage.Input).size());
		assertEquals(rlSet2, theHelper.getSets(null, EnumUsage.Output).get(0));
	}

	/**
	 *
	 *
	 */
	@Test
	public void testGetTyp()
	{
		theHelper.setTypes("ConventionalPrinting");
		assertEquals(theHelper.getType(), EnumType.ConventionalPrinting);
		theHelper.setTypes("PreviewGeneration ConventionalPrinting");
		assertEquals(theHelper.getType(), EnumType.ProcessGroup);
	}

	/**
	 *
	 *
	 */
	@Test
	public void testGetSetUsage()
	{
		final SetHelper seta = theHelper.getCreateSet(XJDFConstants.Resource, ElementName.NODEINFO, EnumUsage.Input);
		assertNull(theHelper.getSet(ElementName.NODEINFO, EnumUsage.Output));
		final SetHelper setb = theHelper.appendResourceSet(ElementName.NODEINFO, EnumUsage.Output);
		assertEquals(theHelper.getSet(ElementName.NODEINFO, EnumUsage.Input), seta);
		assertEquals(theHelper.getSet(ElementName.NODEINFO, EnumUsage.Output), setb);
		assertEquals(theHelper.getSet(ElementName.NODEINFO, null), seta);
	}

	/**
	 *
	 *
	 */
	@Test
	public void testGetPartMapVector()
	{
		final SetHelper sh = theHelper.getCreateSet(XJDFConstants.Resource, ElementName.NODEINFO, EnumUsage.Input);
		final VJDFAttributeMap v = new VJDFAttributeMap();
		final JDFAttributeMap map1 = new JDFAttributeMap("SheetName", "S1");
		v.add(map1);
		sh.getCreatePartition(map1, true);
		final JDFAttributeMap map2 = new JDFAttributeMap("SheetName", "S2");
		v.add(map2);
		sh.getCreatePartition(map2, true);
		final VJDFAttributeMap vp = theHelper.getPartMapVector();
		assertEquals(vp, v);
	}

	/**
	*
	*
	*/
	@Test
	public void testGetDependentPartIDKeys()
	{
		final SetHelper sh = theHelper.getCreateSet(XJDFConstants.Resource, ElementName.COMPONENT, EnumUsage.Input);
		assertNull(theHelper.getDependentJobParts(null));
		assertNull(theHelper.getDependentJobParts(EnumUsage.Output));
		sh.setXPathValue("Dependent[1]/@JobPartID", "p1");
		assertEquals(theHelper.getDependentJobParts(EnumUsage.Input).get(0), "p1");
		assertEquals(theHelper.getDependentJobParts(null).get(0), "p1");
		assertNull(theHelper.getDependentJobParts(EnumUsage.Output));
		sh.setXPathValue("Dependent[3]/@JobPartID", "p2");
		assertEquals(theHelper.getDependentJobParts(EnumUsage.Input).get(1), "p2");
		final SetHelper sh2 = theHelper.getCreateSet(XJDFConstants.Resource, ElementName.COMPONENT, EnumUsage.Output);
		assertNull(theHelper.getDependentJobParts(EnumUsage.Output));
		sh2.setXPathValue("Dependent[1]/@JobPartID", "o1");
		assertEquals(theHelper.getDependentJobParts(EnumUsage.Output).get(0), "o1");
	}

	/**
	 *
	 *
	 */
	@Test
	public void testGetSetByID()
	{
		final SetHelper rlSet = theHelper.appendResourceSet("RunList", null);
		theHelper.cleanUp();
		rlSet.setID("id");
		assertEquals(rlSet, theHelper.getSet(rlSet.getID()));
	}

	/**
	 *
	 */
	@Test
	public void testGetXJDF()
	{
		final JDFDoc d = new JDFDoc("XJDF");
		final KElement root = d.getRoot();

		final KElement set = root.getCreateXPathElement("ResourceSet");
		set.getCreateXPathElement("Resource/Part");
		assertEquals(XJDFHelper.getHelper(set), new XJDFHelper(root));
	}

	/**
	 *
	 */
	@Test
	public void testJobPartID()
	{
		theHelper = new XJDFHelper("j", "p", null);
		assertEquals("p", theHelper.getJobPartID());
		theHelper.setJobPartID("p2");
		assertEquals("p2", theHelper.getJobPartID());
	}

	/**
	 *
	 */
	@Test
	public void testJobID()
	{
		theHelper = new XJDFHelper("j", "p", null);
		assertEquals("j", theHelper.getJobID());
		theHelper.setJobID("j2");
		assertEquals("j2", theHelper.getJobID());
	}

	/**
	 *
	 *
	 */
	@Test
	public void testGetPartition()
	{
		final KElement rlSet = theHelper.getCreateSet(XJDFConstants.Resource, "RunList", null).getCreatePartition(0, true).getResource();
		assertEquals(rlSet, theHelper.getResource("RunList", 0, 0));
		assertNull(theHelper.getResource("RunList", 0, 1));
		assertNull(theHelper.getResource("RunList", 1, 1));
		assertNull(theHelper.getResource("RunList", 1, 0));
	}

	/**
	 *
	 *
	 */
	@Test
	public void testGetPartitionByID()
	{
		final ResourceHelper rlSet = theHelper.getCreateSet(XJDFConstants.Resource, "RunList", null).getCreatePartition(0, true);
		assertEquals(rlSet, theHelper.getPartition("RunList", 0, 0));
		theHelper.cleanUp();
		rlSet.setID("id");
		assertEquals(theHelper.getPartition(rlSet.getID()), rlSet);
	}

	/**
	 *
	 *
	 */
	@Test
	public void testCreate()
	{
		theHelper = new XJDFHelper("jID", "jpID", null);
		final KElement root = theHelper.getRoot();
		assertNotNull(theHelper.getSet("NodeInfo", 0));
		assertNotNull(root);
	}

	/**
	 *
	 *
	 */
	@Test
	public void testCreate21()
	{
		theHelper = new XJDFHelper(EnumVersion.Version_2_1, "j1");
		final KElement root = theHelper.getRoot();
		assertEquals(JDFElement.getSchemaURL(2, 1), root.getNamespaceURI());
	}

	/**
	 *
	 *
	 */
	@Test
	public void testClone()
	{
		final XJDFHelper clone = theHelper.clone();
		theHelper.appendResourceSet(ElementName.DIGITALPRINTINGPARAMS, EnumUsage.Input);
		assertNotNull(theHelper.getSet(ElementName.DIGITALPRINTINGPARAMS, 0));
		assertNull(clone.getSet(ElementName.DIGITALPRINTINGPARAMS, 0));
	}

	/**
	 *
	 *
	 */
	@Test
	public void testFactory()
	{
		XMLDoc d = new XMLDoc(XJDFConstants.XJDF, null);
		assertNotNull(XJDFHelper.getHelper(d));
		d = new XMLDoc("abc", null);
		assertNull(XJDFHelper.getHelper(d));
	}

	/**
	 *
	 *
	 */
	@Test
	public void testFactoryElem()
	{
		XMLDoc d = new XMLDoc(XJDFConstants.XJDF, null);
		assertNotNull(XJDFHelper.getHelper(d.getRoot()));
		d = new XMLDoc("abc", null);
		assertNull(XJDFHelper.getHelper(d.getRoot()));
	}

	/**
	 *
	 *
	 */
	@Test
	public void testRootProducts()
	{
		theHelper = new XJDFHelper("jID", "jpID", null);
		final KElement root = theHelper.getRoot();
		assertNull(theHelper.getRootProductHelpers());
		root.setXPathAttribute("ProductList/Product/@ID", "idproduct");
		root.setXPathAttribute("ProductList/@RootProducts", "idproduct");
		assertNotNull(theHelper.getRootProductHelpers().get(0));
	}

	public void testReorder()
	{
		theHelper = new XJDFHelper("jID", "jpID", null);
		theHelper.reorder();
		theHelper.getCreateSet(XJDFConstants.Resource, "foo", null);
		theHelper.appendProduct().setRoot();
		final KElement ap = theHelper.getRoot().getElement(ElementName.AUDITPOOL);
		assertEquals(theHelper.getRoot().getFirstChildElement(), ap);
		assertEquals(theHelper.getRootProduct(0).getRoot().getParentNode_KElement().getPreviousSiblingElement(), ap);
	}

	/**
	 *
	 *
	 */
	@Test
	public void testRootProduct()
	{
		theHelper = new XJDFHelper("jID", "jpID", null);
		final KElement root = theHelper.getRoot();
		root.setXPathAttribute("ProductList/Product/@ID", "idproduct");
		assertNotNull(theHelper.getRootProduct(0));
		root.setXPathAttribute("ProductList/@RootProducts", "idproduct");
		assertNotNull(theHelper.getRootProduct(0));
		assertNotNull(theHelper.getRootProduct(-1));
		assertNull(theHelper.getRootProduct(1));
		assertNull(theHelper.getRootProduct(-2));
	}

	/**
	 *
	 *
	 */
	@Test
	public void testWriteToDir()
	{
		theHelper = new XJDFHelper("jID", "jpID", null);
		final String foo = sm_dirTestDataTemp + "foo";
		FileUtil.deleteAll(new File(foo));
		theHelper.writeToDir(foo);
		final XMLDoc parsed = XMLDoc.parseFile(foo + "/jID.jpID.xjdf");
		assertNotNull(parsed);
	}

	/**
	 *
	 *
	 */
	@Test
	public void testGetCreateRootProduct()
	{
		theHelper = new XJDFHelper("jID", "jpID", null);
		assertNotNull(theHelper.getCreateRootProduct(0));
		assertNotNull(theHelper.getRootProduct(0));
		assertNotNull(theHelper.getRootProduct(-1));
		assertNull(theHelper.getRootProduct(1));
		assertNull(theHelper.getRootProduct(-2));
		final ProductHelper createRootProduct = theHelper.getCreateRootProduct(1);
		assertNotNull(createRootProduct);
		assertNotSame(createRootProduct, theHelper.getCreateRootProduct(0));

	}

	/**
	 * @see junit.framework.TestCase#setUp()
	 * @throws Exception if snafu
	 */
	@Before
	@Override
	public void setUp() throws Exception
	{
		super.setUp();
		KElement.setLongID(false);
		theHelper = new XJDFHelper(null);
	}
}
