/*
 *
 * The CIP4 Software License, Version 1.0
 *
 *
 * Copyright (c) 2001-2024 The International Cooperation for the Integration of Processes in Prepress, Press and Postpress (CIP4). All rights reserved.
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

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.io.IOException;
import java.util.Collection;

import org.cip4.jdflib.JDFTestCaseBase;
import org.cip4.jdflib.auto.JDFAutoColorantControl.EnumInternalColorModel;
import org.cip4.jdflib.auto.JDFAutoColorantControl.EnumMappingSelection;
import org.cip4.jdflib.core.AttributeName;
import org.cip4.jdflib.core.ElementName;
import org.cip4.jdflib.core.JDFDoc;
import org.cip4.jdflib.core.JDFElement.EnumValidationLevel;
import org.cip4.jdflib.core.JDFElement.EnumVersion;
import org.cip4.jdflib.core.JDFResourceLink;
import org.cip4.jdflib.core.JDFResourceLink.EnumUsage;
import org.cip4.jdflib.core.JDFSeparationList;
import org.cip4.jdflib.core.KElement;
import org.cip4.jdflib.core.VElement;
import org.cip4.jdflib.core.VString;
import org.cip4.jdflib.datatypes.JDFCMYKColor;
import org.cip4.jdflib.node.JDFNode;
import org.cip4.jdflib.node.JDFNode.EnumType;
import org.cip4.jdflib.pool.JDFResourcePool;
import org.cip4.jdflib.resource.JDFResource;
import org.cip4.jdflib.resource.JDFResource.EnumPartIDKey;
import org.cip4.jdflib.resource.JDFResource.EnumResStatus;
import org.cip4.jdflib.resource.JDFResource.EnumResourceClass;
import org.cip4.jdflib.util.StringUtil;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.xml.sax.SAXException;

/**
 * @author Rainer Prosi, Heidelberger Druckmaschinen *
 */
class JDFColorantControlTest extends JDFTestCaseBase
{
	private JDFNode elem;
	private JDFColorantControl colControl;
	private JDFSeparationList colParams;
	private JDFColorPool colPool;
	private JDFDoc d;

	/**
	 * tests the separationlist class
	 *
	 */
	@Test
	public final void testColorantAlias()
	{
		elem.setType(EnumType.ColorSpaceConversion);
		final JDFColorantAlias ca = colControl.appendColorantAlias();
		ca.setXMLComment("ColorantAlias that maps the predefined separation Black", true);
		ca.setReplacementColorantName("Green");
		assertTrue(ca.isValid(EnumValidationLevel.Incomplete));
		assertFalse(ca.isValid(EnumValidationLevel.Complete));
		final VString vAlias = new VString("Gr�n gr�n", null);
		ca.setSeparations(vAlias);
		assertTrue(ca.isValid(EnumValidationLevel.Complete));
		byte[] b = vAlias.get(0).getBytes();
		String rawNames = StringUtil.setHexBinaryBytes(b, -1) + " ";
		b = vAlias.get(1).getBytes();
		rawNames += StringUtil.setHexBinaryBytes(b, -1);
		assertTrue(ca.isValid(EnumValidationLevel.Complete));
		ca.setAttribute("RawNames", rawNames);
		elem.ensureLink(colControl, EnumUsage.Input, null);
		writeTest(d.getRoot(), "resources/ColorantAlias.jdf", false, null);
	}

	/**
	 * tests the proposed Color/@ActualColorName attribute
	 *
	 */
	@Test
	public final void testActualColorName()
	{

		colParams.setXMLComment("Note that all Strings in ColorantParams etc. use Color/@Name, NOT Color/@ActualColorName", true);
		colParams.setSeparations(new VString("Spot1,BlackText", ","));
		colControl.setXMLComment("Simple colorantcontrol from MIS: CMYK + 1 spot+ 1 black text version; knows no more", true);
		d.write2File(sm_dirTestDataTemp + "ActualColorName_MIS.jdf", 2, false);

		colControl.setXMLComment("ColorantControl after prepress has correctly set ActualColorName based on pdl content", true);
		JDFColor co = colPool.appendColorWithName("Black", null);
		co.setXMLComment(
				"Color that maps the predefined separation Black\n" + "ActualColorName is the new attribute that replaces ExposedMedia/@DescriptiveName as the \"Main\" PDL color",
				true);
		co.setCMYK(new JDFCMYKColor(0, 0, 0, 1));
		assertTrue(co.isValid(EnumValidationLevel.Incomplete));
		co.setAttribute("ActualColorName", "Schwarz");

		co = colPool.appendColorWithName("Yellow", null);
		co.setAttribute("ActualColorName", "Gelb");
		co.setCMYK(new JDFCMYKColor(0, 0, 1, 0));

		co = colPool.appendColorWithName("Cyan", null);
		co.setXMLComment("ActualColorName defaults to Name if not specified", true);
		co.setCMYK(new JDFCMYKColor(1, 0, 0, 0));

		co = colPool.appendColorWithName("Magenta", null);
		co = colPool.appendColorWithName("Spot1", null);
		co.setAttribute("ActualColorName", "Acme Aqua");
		co.setCMYK(new JDFCMYKColor(0.7, 0.2, 0.03, 0.1));

		co = colPool.appendColorWithName("BlackText", null);
		co.setAttribute("ActualColorName", "VersionsText");
		co.setCMYK(new JDFCMYKColor(0, 0, 0, 1));

		d.write2File(sm_dirTestDataTemp + "ActualColorName_Prepress.jdf", 2, false);

		final JDFColorantAlias ca = colControl.appendColorantAlias();
		ca.setXMLComment("ColorantAlias that maps the additional representation (noir) to the predefined separation Black", true);
		ca.setReplacementColorantName("Black");
		assertTrue(ca.isValid(EnumValidationLevel.Incomplete));
		assertFalse(ca.isValid(EnumValidationLevel.Complete));
		final VString vAlias = new VString("noir schw�rz", null);
		ca.setSeparations(vAlias);
		assertTrue(ca.isValid(EnumValidationLevel.Complete));
		byte[] b = vAlias.get(0).getBytes();
		String rawNames = StringUtil.setHexBinaryBytes(b, -1) + " ";
		b = vAlias.get(1).getBytes();
		rawNames += StringUtil.setHexBinaryBytes(b, -1);
		ca.setAttribute("RawNames", rawNames);
		d.write2File(sm_dirTestDataTemp + "ActualColorName_with_CA.jdf", 2, false);
	}

	/**
	 * tests the separationlist class
	 *
	 */
	@Test
	public final void testSeparationList()
	{
		final JDFDoc doc = new JDFDoc("JDF");
		final JDFNode root = doc.getJDFRoot();
		final JDFResourcePool resPool = root.getCreateResourcePool();
		final KElement kElem = resPool.appendResource(ElementName.COLORANTCONTROL, null, null);
		assertTrue(kElem instanceof JDFColorantControl);
		final JDFColorantControl cc = ((JDFColorantControl) kElem);
		final JDFSeparationList co = cc.appendColorantOrder();
		final VString seps = StringUtil.tokenize("Cyan Magenta Yellow Black", " ", false);

		co.setSeparations(seps);
		assertEquals(co.getSeparations(), seps);
		final VElement vSepSpec = co.getChildElementVector(ElementName.SEPARATIONSPEC, null, null, true, 0, true);
		assertEquals(vSepSpec.size(), seps.size());
		for (int i = 0; i < vSepSpec.size(); i++)
		{
			assertFalse(vSepSpec.item(i).hasAttribute(AttributeName.CLASS));
			assertFalse(vSepSpec.item(i) instanceof JDFResource);
		}

		assertEquals(co.getSeparation(0), "Cyan");
		co.removeSeparation("Magenta");
		assertEquals(co.getSeparation(0), "Cyan");
		assertEquals(co.getSeparation(1), "Yellow");
		assertEquals(co.getSeparation(2), "Black");
		assertNull(co.getSeparation(3));
	}

	/**
	 * tests the separationlist class
	 *
	 */
	@Test
	public final void testGetSeparations()
	{
		final JDFDoc doc = new JDFDoc("JDF");
		final JDFNode root = doc.getJDFRoot();
		final JDFResourcePool resPool = root.getCreateResourcePool();
		final KElement kElem = resPool.appendResource(ElementName.COLORANTCONTROL, null, null);
		assertTrue(kElem instanceof JDFColorantControl);
		final JDFColorantControl cc = ((JDFColorantControl) kElem);
		cc.setProcessColorModel("DeviceCMYK");
		assertTrue(cc.getSeparations().contains("Cyan"));
		cc.appendColorantParams().appendSeparation("Snarf Blue");
		assertTrue(cc.getSeparations().contains("Snarf Blue"));
		cc.setProcessColorModel("DeviceN");
		assertTrue(cc.getSeparations().contains("Snarf Blue"));
	}

	/**
	 *
	 */
	@Test
	public final void testGetProcessSeparations()
	{
		final JDFDoc doc = new JDFDoc("JDF");
		final JDFNode root = doc.getJDFRoot();
		final JDFResourcePool resPool = root.getCreateResourcePool();
		final KElement kElem = resPool.appendResource(ElementName.COLORANTCONTROL, null, null);
		assertTrue(kElem instanceof JDFColorantControl);
		final JDFColorantControl cc = ((JDFColorantControl) kElem);
		cc.setProcessColorModel("DeviceCMYK");
		assertEquals(cc.getSeparations(), cc.getProcessSeparations());
	}

	/**
	 *
	 */
	@Test
	public final void testRemoveProcessSeparations()
	{
		final JDFDoc doc = new JDFDoc("JDF");
		final JDFNode root = doc.getJDFRoot();
		final JDFResourcePool resPool = root.getCreateResourcePool();
		final KElement kElem = resPool.appendResource(ElementName.COLORANTCONTROL, null, null);
		assertTrue(kElem instanceof JDFColorantControl);
		final JDFColorantControl cc = ((JDFColorantControl) kElem);
		cc.setProcessColorModel("DeviceCMYK");
		cc.appendColorantParams().setSeparations(cc.getSeparations());
		assertEquals(cc.getSeparations(), cc.getProcessSeparations());
		cc.removeProcessColors();
		assertEquals(cc.getSeparations(), cc.getProcessSeparations());
		assertNull(cc.getColorantParams());
	}

	/**
	 * tests the separationlist class
	 *
	 */
	@Test
	public final void testImplicitPartitions()
	{
		final JDFNode root = new JDFDoc("JDF").getJDFRoot();
		final JDFResource res = root.addResource(ElementName.COLORANTCONTROL, EnumUsage.Input);
		assertTrue(res instanceof JDFColorantControl);
		final JDFResourceLink rl = root.getLink(res, null);
		rl.appendPart().setSeparation("Blue");
		rl.appendPart().setSeparation("Green");
		assertNotNull(rl.getTarget());
	}

	/**
	 * tests the separationlist class
	 *
	 */
	@Test
	public final void testGetAllSeparations()
	{
		final JDFDoc doc = new JDFDoc("JDF");
		final JDFNode root = doc.getJDFRoot();
		final JDFResourcePool resPool = root.getCreateResourcePool();
		final KElement kElem = resPool.appendResource(ElementName.COLORANTCONTROL, null, null);
		assertTrue(kElem instanceof JDFColorantControl);
		final JDFColorantControl cc = ((JDFColorantControl) kElem);
		cc.setProcessColorModel("DeviceCMYK");
		assertTrue(cc.getAllSeparations().contains("Cyan"));
		cc.appendColorantParams().appendSeparation("Snarf Blue");
		assertTrue(cc.getAllSeparations().contains("Snarf Blue"));
		cc.setProcessColorModel("DeviceN");
		assertTrue(cc.getAllSeparations().contains("Snarf Blue"));
	}

	/**
	 *
	 */
	@Test
	void testColorantParams()
	{
		assertTrue(colParams.isValid(EnumValidationLevel.RecursiveComplete));
	}

	/**
	 *
	 */
	// //////////////////////////////////////////////////////////////////////
	@Test
	void testGetAllColorantAlias()
	{
		final JDFColorantControl cBlatt = (JDFColorantControl) colControl.addPartition(EnumPartIDKey.SheetName, "s1");
		Collection<JDFColorantAlias> col = cBlatt.getAllColorantAlias();
		assertEquals(col.size(), 0);
		final JDFColorantAlias a1 = colControl.appendColorantAlias();
		final JDFColorantAlias a2 = colControl.appendColorantAlias();
		col = cBlatt.getAllColorantAlias();
		assertEquals(col.size(), 2);
		assertTrue(col.contains(a1));
		assertTrue(col.contains(a2));
		final JDFColorantAlias a3 = cBlatt.appendColorantAlias();
		col = cBlatt.getAllColorantAlias();
		assertEquals(col.size(), 1);
		assertTrue(col.contains(a3));
	}

	@Test
	void testGetColorantAlias()
	{
		final JDFColorantControl cBlatt = (JDFColorantControl) colControl.addPartition(EnumPartIDKey.SheetName, "s1");
		final Collection<JDFColorantAlias> col = cBlatt.getAllColorantAlias();
		assertEquals(col.size(), 0);
		final JDFColorantAlias a1 = colControl.appendColorantAlias("s1", "t1");
		final JDFColorantAlias a2 = colControl.appendColorantAlias("s2", "t2");
		assertEquals(a1, colControl.getColorantAlias("s1"));
		assertEquals(a2, colControl.getColorantAlias("s2"));
		assertNull(colControl.getColorantAlias("t1"));
		assertEquals(a1, cBlatt.getColorantAlias("s1"));
		assertEquals(a2, cBlatt.getColorantAlias("s2"));
		assertNull(cBlatt.getColorantAlias("t1"));

	}

	@Test
	void testGetColorantAliasRep()
	{
		final JDFColorantControl cBlatt = (JDFColorantControl) colControl.addPartition(EnumPartIDKey.SheetName, "s1");
		final Collection<JDFColorantAlias> col = cBlatt.getAllColorantAlias();
		assertEquals(col.size(), 0);
		final JDFColorantAlias a1 = colControl.appendColorantAlias("s1", "t1");
		final JDFColorantAlias a2 = colControl.appendColorantAlias("s2", "t2");
		assertEquals(a1, colControl.getColorantAliasForReplacement("t1"));
		assertEquals(a2, colControl.getColorantAliasForReplacement("t2"));
		assertNull(colControl.getColorantAliasForReplacement("s1"));
		assertEquals(a1, cBlatt.getColorantAliasForReplacement("t1"));
		assertEquals(a2, cBlatt.getColorantAliasForReplacement("t2"));
		assertNull(cBlatt.getColorantAliasForReplacement("s1"));

	}

	@Test
	void testGetColorantAliasMap()
	{
		final JDFColorantAlias a1 = colControl.appendColorantAlias("s1", "t1");
		final JDFColorantAlias a2 = colControl.appendColorantAlias("s2", "t2");
		assertEquals("t2", colControl.getColorantAliasMap().get("s2"));
		assertEquals(2, colControl.getColorantAliasMap().size());

	}

	@Test
	void testAppendColorantAlias()
	{
		final JDFColorantAlias a1 = colControl.appendColorantAlias("s1", "t1");
		final JDFColorantAlias a2 = colControl.appendColorantAlias("s21", "t2");
		final JDFColorantAlias a22 = colControl.appendColorantAlias("s22", "t2");
		assertEquals(a2, a22);
		assertEquals(3, colControl.getColorantAliasMap().size());
	}

	@Test
	void testGetColorantAliasMapLeaf()
	{
		final JDFColorantAlias a1 = colControl.appendColorantAlias("s1", "t1");
		final JDFColorantAlias a2 = colControl.appendColorantAlias("s2", "t2");
		assertEquals("t2", colControl.getColorantAliasMap().get("s2"));
		final JDFColorantControl leaf = (JDFColorantControl) colControl.addPartition(EnumPartIDKey.SheetName, "s1");
		assertEquals(2, colControl.getColorantAliasMap().size());
		assertEquals(2, leaf.getColorantAliasMap().size());
	}

	/**
	 * we no longer check all leaves as these can be individualized
	 */
	@Test
	void testGetColorantAliasMapRec()
	{
		final JDFColorantControl cBlatt = (JDFColorantControl) colControl.addPartition(EnumPartIDKey.SheetName, "s1");
		final JDFColorantAlias a1 = cBlatt.appendColorantAlias("s1", "t1");
		final JDFColorantAlias a2 = cBlatt.appendColorantAlias("s2", "t2");
		assertEquals("t2", cBlatt.getColorantAliasMap().get("s2"));
		assertEquals(2, cBlatt.getColorantAliasMap().size());

	}

	/**
	 *
	 */
	@Test
	void testGetAllColorantAliasVector()
	{
		final JDFColorantAlias a1 = colControl.appendColorantAlias();
		final JDFColorantAlias a2 = colControl.appendColorantAlias();
		final JDFColorantControl cBlatt = (JDFColorantControl) colControl.addPartition(EnumPartIDKey.SheetName, "s1");
		VElement col = cBlatt.getChildElementVector(ElementName.COLORANTALIAS, null);
		assertEquals(col.size(), 2);
		assertTrue(col.contains(a1));
		assertTrue(col.contains(a2));
		final JDFColorantAlias a3 = cBlatt.appendColorantAlias();
		col = cBlatt.getChildElementVector(ElementName.COLORANTALIAS, null);
		assertEquals(col.size(), 1);
		assertTrue(col.contains(a3));
	}

	/**
	 *
	 */
	@Test
	void testGetDeviceColorantOrderSeparations()
	{
		colParams.appendSeparation("Black");
		assertEquals(colControl.getDeviceColorantOrderSeparations(), colControl.getSeparations());
		assertEquals(colControl.getDeviceColorantOrderSeparations().size(), 4);
		colParams.appendSeparation("Green");
		assertEquals(colControl.getDeviceColorantOrderSeparations(), colControl.getSeparations());
		assertEquals(colControl.getDeviceColorantOrderSeparations().size(), 5);
		colControl.appendColorantOrder().appendSeparation("Green");
		assertEquals(colControl.getDeviceColorantOrderSeparations().size(), 1);
		assertEquals(colControl.getDeviceColorantOrderSeparations().get(0), "Green");
	}

	/**
	 *
	 */
	@Test
	void testGetDeviceColorantOrderSeparationsXJDF()
	{
		final JDFColorantControl cc = (JDFColorantControl) JDFElementColorParams.createRoot(ElementName.COLORANTCONTROL);
		cc.setAttribute(ElementName.COLORANTPARAMS, "a b c");
		cc.setProcessColorModel("DeviceCMYK");
		assertEquals(new VString("a b c"), cc.getDeviceColorantOrderSeparations());
		cc.setAttribute(ElementName.COLORANTORDER, "a b");
		assertEquals(new VString("a b"), cc.getDeviceColorantOrderSeparations());
		cc.setAttribute(ElementName.DEVICECOLORANTORDER, "b a");
		assertEquals(new VString("b a"), cc.getDeviceColorantOrderSeparations());
	}

	/**
	 *
	 */
	@Test
	void testGetColorantOrderSeparations()
	{
		colParams.appendSeparation("Black");
		assertEquals(colControl.getColorantOrderSeparations(), colControl.getSeparations());
		assertEquals(colControl.getColorantOrderSeparations().size(), 4);
		colParams.appendSeparation("Green");
		assertEquals(colControl.getColorantOrderSeparations(), colControl.getSeparations());
		assertEquals(colControl.getColorantOrderSeparations().size(), 5);
	}

	/**
	 *
	 */
	@Test
	void testMappingSelection()
	{
		elem.setVersion(EnumVersion.Version_1_5);
		colControl.setMappingSelection(EnumMappingSelection.UseLocalPrinterValues);
		assertEquals(colControl.getMappingSelection(), EnumMappingSelection.UseLocalPrinterValues);
		checkSchema(colControl, EnumValidationLevel.Incomplete);
	}

	/**
	 * @throws IOException
	 * @throws SAXException
	 *
	 */
	@Test
	void testInternalColorModel() throws SAXException, IOException
	{
		elem.setVersion(EnumVersion.Version_1_5);
		colControl.setInternalColorModel(EnumInternalColorModel.Enhanced);
		assertEquals(colControl.getInternalColorModel(), EnumInternalColorModel.Enhanced);
		checkSchema(colControl, EnumValidationLevel.Incomplete);
	}

	/**
	 *
	 */
	@Override
	@BeforeEach
	public void setUp() throws Exception
	{
		super.setUp();
		KElement.setLongID(false);
		d = new JDFDoc(ElementName.JDF);
		elem = d.getJDFRoot();
		elem.setType(EnumType.AdhesiveBinding);
		final JDFResourcePool rpool = elem.appendResourcePool();
		colControl = (JDFColorantControl) rpool.appendResource(ElementName.COLORANTCONTROL, EnumResourceClass.Parameter, null);
		colControl.setProcessColorModel("DeviceCMYK");
		colControl.setResStatus(EnumResStatus.Available, true);
		colParams = colControl.appendColorantParams();
		colPool = colControl.appendColorPool();
		colPool.makeRootResource(null, null, true);
	}

}
