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

package org.cip4.jdflib.extensions;

import java.io.File;
import java.util.Vector;

import org.cip4.jdflib.JDFTestCaseBase;
import org.cip4.jdflib.core.*;
import org.cip4.jdflib.core.JDFElement.EnumVersion;
import org.cip4.jdflib.core.JDFResourceLink.EnumUsage;
import org.cip4.jdflib.datatypes.JDFAttributeMap;
import org.cip4.jdflib.datatypes.JDFIntegerList;
import org.cip4.jdflib.datatypes.VJDFAttributeMap;
import org.cip4.jdflib.node.JDFNode.EnumType;
import org.cip4.jdflib.resource.process.JDFRunList;
import org.cip4.jdflib.util.FileUtil;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

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
		Assertions.assertEquals(rlSet, theHelper.getSet(ElementName.RUNLIST, 0).getSet());
	}

	/**
	 *
	 *
	 */
	@Test
	public void testAppendSet()
	{
		final KElement rlSet = theHelper.appendSet(null, ElementName.RUNLIST, null).getSet();
		Assertions.assertEquals(rlSet.getLocalName(), XJDFConstants.ResourceSet);
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
		Assertions.assertEquals(theHelper.getTypes().get(1), "Folding");
		Assertions.assertEquals(theHelper.getTypes().get(0), "Cutting");
		theHelper.addType("Stitching", -1);
		Assertions.assertEquals(theHelper.getTypes().get(1), "Folding");
		Assertions.assertEquals(theHelper.getTypes().get(0), "Cutting");
		Assertions.assertEquals(theHelper.getTypes().get(2), "Stitching");
		theHelper.addType("Imposition", 0);
		Assertions.assertEquals(theHelper.getTypes().get(0), "Imposition");
		Assertions.assertEquals(theHelper.getTypes().get(3), "Stitching");
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
		Assertions.assertEquals(0, theHelper.indexOfType("Cutting", 0));
		Assertions.assertEquals(-1, theHelper.indexOfType("Cutting", 1));
		Assertions.assertEquals(-1, theHelper.indexOfType("Cutting", 42));
		Assertions.assertEquals(1, theHelper.indexOfType("Folding", 0));
		theHelper.addType("Cutting", -1);
		theHelper.addType("Cutting", -1);
		Assertions.assertEquals(2, theHelper.indexOfType("Cutting", 1));
		Assertions.assertEquals(3, theHelper.indexOfType("Cutting", 2));
		Assertions.assertEquals(-1, theHelper.indexOfType("Cutting", 3));
	}

	/**
	 *
	 *
	 */
	@Test
	public void testAddTypeChain()
	{
		theHelper.addType("Cutting").addType("Folding");
		Assertions.assertEquals(theHelper.getTypes().get(1), "Folding");
		Assertions.assertEquals(theHelper.getTypes().get(0), "Cutting");
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
		Assertions.assertEquals(theHelper.getTypes().get(1), "Folding");
		Assertions.assertEquals(theHelper.getTypes().get(0), "Cutting");
		theHelper.removeType("Cutting", 0);
		Assertions.assertEquals(theHelper.getTypes().get(0), "Folding");
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
		Assertions.assertEquals(1, rlSet.getCombinedProcessIndex().getInt(0));
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
		Assertions.assertEquals(1, rlSet.getCombinedProcessIndex().getInt(0));
		theHelper.removeType("Cutting", 0);
		Assertions.assertEquals(0, rlSet.getCombinedProcessIndex().getInt(0));
	}

	/**
	*
	*
	*/
	@Test
	public void testGetCreateResourceSet()
	{
		Assertions.assertNull(theHelper.getSet(ElementName.RUNLIST, EnumUsage.Input, null));
		final SetHelper sh1 = theHelper.getCreateSet(XJDFConstants.Resource, ElementName.RUNLIST, EnumUsage.Input);
		Assertions.assertNotNull(sh1);
		final SetHelper sh2 = theHelper.getCreateSet(XJDFConstants.Resource, ElementName.RUNLIST, EnumUsage.Output);
		Assertions.assertNotNull(sh2);
		Assertions.assertNotSame(sh1, sh2);

	}

	/**
	*
	*
	*/
	@Test
	public void testGetCreateResource()
	{
		Assertions.assertNull(theHelper.getSet(ElementName.RUNLIST, EnumUsage.Input, null));
		final JDFRunList rl = (JDFRunList) theHelper.getCreateResource(ElementName.RUNLIST, EnumUsage.Input, null);
		Assertions.assertNotNull(rl);
		final SetHelper sh = theHelper.getSet(ElementName.RUNLIST, EnumUsage.Input, null);
		Assertions.assertNotNull(sh);
		Assertions.assertEquals(1, sh.getPartitions().size());
	}

	/**
	*
	*
	*/
	@Test
	public void testGetCreateResourceSet2()
	{
		Assertions.assertNull(theHelper.getSet(ElementName.RUNLIST, EnumUsage.Input));
		final SetHelper sh1 = theHelper.getCreateSet(XJDFConstants.Resource, ElementName.RUNLIST, EnumUsage.Input);
		Assertions.assertNotNull(sh1);
		final SetHelper sh2 = theHelper.getCreateSet(XJDFConstants.Resource, ElementName.RUNLIST, EnumUsage.Output);
		Assertions.assertNotNull(sh2);
		Assertions.assertNotSame(sh1, sh2);

	}

	/**
	*
	*
	*/
	@Test
	public void testGetCreateSet4()
	{
		Assertions.assertNull(theHelper.getSet(ElementName.RUNLIST, EnumUsage.Input));
		final SetHelper sh1 = theHelper.getCreateSet(ElementName.RUNLIST, EnumUsage.Input, "foo", new JDFIntegerList(1));
		Assertions.assertNotNull(sh1);
		final SetHelper sh2 = theHelper.getCreateSet(ElementName.RUNLIST, EnumUsage.Input, "foo", new JDFIntegerList(2));
		Assertions.assertNotNull(sh2);
		Assertions.assertNotSame(sh1, sh2);

	}

	/**
	*
	*
	*/
	@Test
	public void testNumProduct()
	{
		final XJDFHelper h = new XJDFHelper("j", "root", null);
		Assertions.assertEquals(h.numProductHelpers(true), 0);
		Assertions.assertEquals(h.numProductHelpers(false), 0);
		final ProductHelper cover = h.appendProduct();
		Assertions.assertEquals(h.numProductHelpers(true), 1);
		Assertions.assertEquals(h.numProductHelpers(false), 1);
		final ProductHelper body = h.appendProduct();
		Assertions.assertEquals(h.numProductHelpers(true), 1);
		Assertions.assertEquals(h.numProductHelpers(false), 2);
		final ProductHelper book = h.appendProduct();
		book.setRoot();
		book.setChild(cover);
		book.setChild(body);
		Assertions.assertEquals(h.numProductHelpers(true), 1);
		Assertions.assertEquals(h.numProductHelpers(false), 3);
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
		Assertions.assertEquals(ph, h.getProduct("iii"));
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
		Assertions.assertEquals(ph, h.getProductByExternalID("xid"));
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

		Assertions.assertEquals(rlSet, theHelper.getSets(ElementName.RUNLIST, null).get(0));
		Assertions.assertEquals(rlSet2, theHelper.getSets(ElementName.MEDIA, null).get(0));
		Assertions.assertEquals(rlSet2, theHelper.getSets(ElementName.MEDIA, EnumUsage.Output).get(0));
		Assertions.assertEquals(rlSet, theHelper.getSets(ElementName.RUNLIST, EnumUsage.Input).get(0));
		Assertions.assertEquals(rlSet, theHelper.getSets(null, EnumUsage.Input).get(0));
		Assertions.assertEquals(1, theHelper.getSets(null, EnumUsage.Input).size());
		Assertions.assertEquals(rlSet2, theHelper.getSets(null, EnumUsage.Output).get(0));
	}

	/**
	 *
	 *
	 */
	@Test
	public void testGetTyp()
	{
		theHelper.setTypes("ConventionalPrinting");
		Assertions.assertEquals(theHelper.getType(), EnumType.ConventionalPrinting);
		theHelper.setTypes("PreviewGeneration ConventionalPrinting");
		Assertions.assertEquals(theHelper.getType(), EnumType.ProcessGroup);
	}

	/**
	 *
	 *
	 */
	@Test
	public void testGetSetUsage()
	{
		final SetHelper seta = theHelper.getCreateSet(XJDFConstants.Resource, ElementName.NODEINFO, EnumUsage.Input);
		Assertions.assertNull(theHelper.getSet(ElementName.NODEINFO, EnumUsage.Output));
		final SetHelper setb = theHelper.appendResourceSet(ElementName.NODEINFO, EnumUsage.Output);
		Assertions.assertEquals(theHelper.getSet(ElementName.NODEINFO, EnumUsage.Input), seta);
		Assertions.assertEquals(theHelper.getSet(ElementName.NODEINFO, EnumUsage.Output), setb);
		Assertions.assertEquals(theHelper.getSet(ElementName.NODEINFO, null), seta);
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
		Assertions.assertEquals(vp, v);
	}

	/**
	*
	*
	*/
	@Test
	public void testGetDependentPartIDKeys()
	{
		final SetHelper sh = theHelper.getCreateSet(XJDFConstants.Resource, ElementName.COMPONENT, EnumUsage.Input);
		Assertions.assertNull(theHelper.getDependentJobParts(null));
		Assertions.assertNull(theHelper.getDependentJobParts(EnumUsage.Output));
		sh.setXPathValue("Dependent[1]/@JobPartID", "p1");
		Assertions.assertEquals(theHelper.getDependentJobParts(EnumUsage.Input).get(0), "p1");
		Assertions.assertEquals(theHelper.getDependentJobParts(null).get(0), "p1");
		Assertions.assertNull(theHelper.getDependentJobParts(EnumUsage.Output));
		sh.setXPathValue("Dependent[3]/@JobPartID", "p2");
		Assertions.assertEquals(theHelper.getDependentJobParts(EnumUsage.Input).get(1), "p2");
		final SetHelper sh2 = theHelper.getCreateSet(XJDFConstants.Resource, ElementName.COMPONENT, EnumUsage.Output);
		Assertions.assertNull(theHelper.getDependentJobParts(EnumUsage.Output));
		sh2.setXPathValue("Dependent[1]/@JobPartID", "o1");
		Assertions.assertEquals(theHelper.getDependentJobParts(EnumUsage.Output).get(0), "o1");
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
		Assertions.assertEquals(rlSet, theHelper.getSet(rlSet.getID()));
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
		Assertions.assertEquals(XJDFHelper.getHelper(set), new XJDFHelper(root));
	}

	/**
	 *
	 */
	@Test
	public void testJobPartID()
	{
		theHelper = new XJDFHelper("j", "p", null);
		Assertions.assertEquals("p", theHelper.getJobPartID());
		theHelper.setJobPartID("p2");
		Assertions.assertEquals("p2", theHelper.getJobPartID());
	}

	/**
	 *
	 */
	@Test
	public void testJobID()
	{
		theHelper = new XJDFHelper("j", "p", null);
		Assertions.assertEquals("j", theHelper.getJobID());
		theHelper.setJobID("j2");
		Assertions.assertEquals("j2", theHelper.getJobID());
	}

	/**
	 *
	 *
	 */
	@Test
	public void testGetPartition()
	{
		final KElement rlSet = theHelper.getCreateSet(XJDFConstants.Resource, "RunList", null).getCreatePartition(0, true).getResource();
		Assertions.assertEquals(rlSet, theHelper.getResource("RunList", 0, 0));
		Assertions.assertNull(theHelper.getResource("RunList", 0, 1));
		Assertions.assertNull(theHelper.getResource("RunList", 1, 1));
		Assertions.assertNull(theHelper.getResource("RunList", 1, 0));
	}

	/**
	 *
	 *
	 */
	@Test
	public void testGetPartitionByID()
	{
		final ResourceHelper rlSet = theHelper.getCreateSet(XJDFConstants.Resource, "RunList", null).getCreatePartition(0, true);
		Assertions.assertEquals(rlSet, theHelper.getPartition("RunList", 0, 0));
		theHelper.cleanUp();
		rlSet.setID("id");
		Assertions.assertEquals(theHelper.getPartition(rlSet.getID()), rlSet);
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
		Assertions.assertNotNull(theHelper.getSet("NodeInfo", 0));
		Assertions.assertNotNull(root);
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
		Assertions.assertEquals(JDFElement.getSchemaURL(2, 1), root.getNamespaceURI());
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
		Assertions.assertNotNull(theHelper.getSet(ElementName.DIGITALPRINTINGPARAMS, 0));
		Assertions.assertNull(clone.getSet(ElementName.DIGITALPRINTINGPARAMS, 0));
	}

	/**
	 *
	 *
	 */
	@Test
	public void testFactory()
	{
		XMLDoc d = new XMLDoc(XJDFConstants.XJDF, null);
		Assertions.assertNotNull(XJDFHelper.getHelper(d));
		d = new XMLDoc("abc", null);
		Assertions.assertNull(XJDFHelper.getHelper(d));
	}

	/**
	 *
	 *
	 */
	@Test
	public void testFactoryElem()
	{
		XMLDoc d = new XMLDoc(XJDFConstants.XJDF, null);
		Assertions.assertNotNull(XJDFHelper.getHelper(d.getRoot()));
		d = new XMLDoc("abc", null);
		Assertions.assertNull(XJDFHelper.getHelper(d.getRoot()));
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
		Assertions.assertNull(theHelper.getRootProductHelpers());
		root.setXPathAttribute("ProductList/Product/@ID", "idproduct");
		root.setXPathAttribute("ProductList/@RootProducts", "idproduct");
		Assertions.assertNotNull(theHelper.getRootProductHelpers().get(0));
	}

	@Test
	public void testReorder()
	{
		theHelper = new XJDFHelper("jID", "jpID", null);
		theHelper.getCreateSet(XJDFConstants.Resource, "foo", null);
		theHelper.appendProduct().setRoot();
		theHelper.cleanUp();
		final KElement ap = theHelper.getRoot().getElement(ElementName.AUDITPOOL);
		Assertions.assertEquals(theHelper.getRoot().getFirstChildElement(), ap);
		Assertions.assertEquals(theHelper.getRootProduct(0).getRoot().getParentNode_KElement().getPreviousSiblingElement(), ap);
	}

	/**
	 *
	 */
	@Test
	public void testSortProducts()
	{
		final XJDFHelper theHelper = new XJDFHelper("jID", "jpID", null);

		final ProductHelper ph21 = theHelper.getCreateProduct("i21");
		ph21.setRoot(false);
		final ProductHelper ph22 = theHelper.getCreateProduct("i22");
		final ProductHelper ph = theHelper.getCreateProduct("i");
		ph.setRoot();
		final ProductHelper ph1 = theHelper.getCreateProduct("i1");
		final ProductHelper ph2 = theHelper.getCreateProduct("i2");

		ph.setChild(ph1);
		ph.setChild(ph2);
		ph2.setChild(ph21);
		ph2.setChild(ph22);

		theHelper.cleanUp();
		final Vector<ProductHelper> vp = theHelper.getProductHelpers();
		Assertions.assertEquals(0, vp.indexOf(ph));
		Assertions.assertTrue(vp.indexOf(ph2) < vp.indexOf(ph22));
		Assertions.assertTrue(vp.indexOf(ph2) < vp.indexOf(ph21));
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
		Assertions.assertNotNull(theHelper.getRootProduct(0));
		root.setXPathAttribute("ProductList/@RootProducts", "idproduct");
		Assertions.assertNotNull(theHelper.getRootProduct(0));
		Assertions.assertNotNull(theHelper.getRootProduct(-1));
		Assertions.assertNull(theHelper.getRootProduct(1));
		Assertions.assertNull(theHelper.getRootProduct(-2));
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
		Assertions.assertNotNull(parsed);
	}

	/**
	 *
	 *
	 */
	@Test
	public void testGetCreateRootProduct()
	{
		theHelper = new XJDFHelper("jID", "jpID", null);
		Assertions.assertNotNull(theHelper.getCreateRootProduct(0));
		Assertions.assertNotNull(theHelper.getRootProduct(0));
		Assertions.assertNotNull(theHelper.getRootProduct(-1));
		Assertions.assertNull(theHelper.getRootProduct(1));
		Assertions.assertNull(theHelper.getRootProduct(-2));
		final ProductHelper createRootProduct = theHelper.getCreateRootProduct(1);
		Assertions.assertNotNull(createRootProduct);
		Assertions.assertNotSame(createRootProduct, theHelper.getCreateRootProduct(0));

	}

	/**
	 * @see junit.framework.TestCase#setUp()
	 * @throws Exception if snafu
	 */
	@Override
	@BeforeEach
	public void setUp() throws Exception
	{
		super.setUp();
		KElement.setLongID(false);
		theHelper = new XJDFHelper(null);
	}
}
