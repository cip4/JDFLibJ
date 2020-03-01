/**
 * The CIP4 Software License, Version 1.0
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
package org.cip4.jdflib.extensions.examples;

import org.cip4.jdflib.auto.JDFAutoMedia.EnumMediaType;
import org.cip4.jdflib.auto.JDFAutoVarnishingParams.EnumVarnishMethod;
import org.cip4.jdflib.core.AttributeName;
import org.cip4.jdflib.core.ElementName;
import org.cip4.jdflib.core.JDFResourceLink.EnumUsage;
import org.cip4.jdflib.core.JDFSeparationList;
import org.cip4.jdflib.core.KElement;
import org.cip4.jdflib.core.VString;
import org.cip4.jdflib.core.XMLFormatter;
import org.cip4.jdflib.datatypes.JDFAttributeMap;
import org.cip4.jdflib.datatypes.JDFCMYKColor;
import org.cip4.jdflib.datatypes.VJDFAttributeMap;
import org.cip4.jdflib.extensions.ResourceHelper;
import org.cip4.jdflib.extensions.SetHelper;
import org.cip4.jdflib.extensions.XJDFConstants;
import org.cip4.jdflib.extensions.XJDFHelper;
import org.cip4.jdflib.node.JDFNode.EnumType;
import org.cip4.jdflib.resource.JDFVarnishingParams;
import org.cip4.jdflib.resource.process.JDFColor;
import org.cip4.jdflib.resource.process.JDFColorantControl;
import org.cip4.jdflib.resource.process.JDFComponent;
import org.cip4.jdflib.resource.process.JDFExposedMedia;
import org.cip4.jdflib.resource.process.JDFMedia;
import org.cip4.jdflib.resource.process.prepress.JDFInk;
import org.cip4.jdflib.util.JDFDate;
import org.junit.Test;

/**
 *
 * @author rainer prosi
 *
 */
public class XJDFChangeOrderTest extends ExampleTest
{
	/**
	 * @see org.cip4.jdflib.JDFTestCaseBase#setUp()
	 */
	@Override
	public void setUp() throws Exception
	{
		super.setUp();
		KElement.setLongID(false);
	}

	/**
	 *
	 */
	@Test
	public void testAmount()
	{
		final XJDFHelper xjdfHelper = new XJDFHelper("ChangeOrder", "Amount", null);
		xjdfHelper.setTypes("Folding");
		final SetHelper sh1 = xjdfHelper.getCreateSet(XJDFConstants.Resource, ElementName.COMPONENT, EnumUsage.Output);
		final ResourceHelper rh1 = sh1.appendPartition("SheetName", "Sheet1", false);
		rh1.setAmount(4000, null, true);
		final SetHelper sh2 = xjdfHelper.getSet(ElementName.NODEINFO, EnumUsage.Input, null);
		sh2.deleteNode();
		xjdfHelper.cleanUp();
		setSnippet(xjdfHelper, true);
		setSnippet(xjdfHelper.getAuditPool(), false);
		writeTest(xjdfHelper, "building/co_amount.xjdf");
	}

	/**
	 *
	 */
	@Test
	public void testSchedule()
	{
		final XJDFHelper xjdfHelper = new XJDFHelper("ChangeOrder", "Reschedule", new VJDFAttributeMap(new JDFAttributeMap("SheetName", "Sheet1")));
		xjdfHelper.setTypes("Folding");
		final JDFDate jdfDate = new JDFDate().setTime(13, 0, 0);
		final SetHelper sh2 = xjdfHelper.getSet(ElementName.NODEINFO, EnumUsage.Input, null);
		jdfDate.addOffset(0, 0, 4, 0);
		sh2.getCreatePartition("SheetName", "Sheet1", true).getResource().setAttribute(AttributeName.START, jdfDate.getDateTimeISO());
		xjdfHelper.cleanUp();
		setSnippet(xjdfHelper, true);
		setSnippet(xjdfHelper.getAuditPool(), false);
		writeTest(xjdfHelper, "building/co_schedule.xjdf");
	}

	/**
	 *
	 */
	@Test
	public void testAddColor()
	{
		final JDFAttributeMap map = new JDFAttributeMap("SheetName", "Sheet1");
		final XJDFHelper xjdfHelper = new XJDFHelper("ChangeOrder", "AddColor", new VJDFAttributeMap(map));
		map.put("Side", "Front");
		map.put("Separation", "Spot1");

		xjdfHelper.setTypes(EnumType.ConventionalPrinting.getName());

		final SetHelper sh2 = xjdfHelper.getCreateSet(XJDFConstants.Resource, ElementName.MEDIA, null);
		final ResourceHelper rh2 = sh2.appendPartition(null, true);
		final JDFMedia m = (JDFMedia) rh2.getResource();
		m.setMediaType(EnumMediaType.Plate);

		final SetHelper sh1 = xjdfHelper.getCreateSet(ElementName.EXPOSEDMEDIA, EnumUsage.Input, null);
		final ResourceHelper rh1 = sh1.appendPartition(map, true);
		final JDFExposedMedia xm = (JDFExposedMedia) rh1.getResource();
		xm.setAttribute("MediaRef", rh2.getRoot().appendAnchor(null));

		final SetHelper sh3 = xjdfHelper.getCreateSet(ElementName.COLOR, EnumUsage.Input, null);
		final ResourceHelper rh3 = sh3.appendPartition("Separation", "Spot1", true);
		final JDFColor c = (JDFColor) rh3.getResource();
		c.setActualColorName("Acme ColorBook 42");
		c.setCMYK(new JDFCMYKColor(0.2, 0.3, 0.4, 0.1));

		final SetHelper sh4 = xjdfHelper.getCreateSet(ElementName.INK, EnumUsage.Input, null);
		final ResourceHelper rh4 = sh4.appendPartition("Separation", "Spot1", true);
		final JDFInk ink = (JDFInk) rh4.getResource();
		rh4.setBrand("Acme Ink 42");
		ink.setAttribute(XJDFConstants.InkType, "Ink");

		final SetHelper sh5 = xjdfHelper.getCreateSet(ElementName.COLORANTCONTROL, EnumUsage.Input, null);
		final JDFAttributeMap map2 = map.clone();
		map2.remove(AttributeName.SEPARATION);
		final ResourceHelper rh5 = sh5.appendPartition(map2, true);
		final JDFColorantControl cc = (JDFColorantControl) rh5.getResource();
		final VString seps = new VString(JDFSeparationList.SEPARATIONS_CMYK);
		seps.add("Spot1");
		cc.setAttribute(ElementName.COLORANTPARAMS, seps, null);
		cc.setAttribute(ElementName.COLORANTORDER, seps, null);
		XMLFormatter.getFormatter().setLineWidth(30);
		xjdfHelper.cleanUp();
		setSnippet(xjdfHelper, true);
		setSnippet(xjdfHelper.getAuditPool(), false);
		writeTest(xjdfHelper, "building/co_color.xjdf");
	}

	/**
	 *
	 */
	@Test
	public void testInlineVarnish()
	{
		final JDFAttributeMap map = new JDFAttributeMap("SheetName", "Sheet1");
		final XJDFHelper xjdfHelper = new XJDFHelper("ChangeOrder", "InlineVarnish", new VJDFAttributeMap(map));
		map.put("Side", "Front");
		map.put("Separation", "Varnish");

		xjdfHelper.setTypes("ConventionalPrinting Varnishing");

		final SetHelper sh2 = xjdfHelper.getCreateSet(XJDFConstants.Resource, ElementName.MEDIA, null);
		final ResourceHelper rh2 = sh2.appendPartition(null, true);
		final JDFMedia m = (JDFMedia) rh2.getResource();
		m.setMediaType(EnumMediaType.Plate);

		final SetHelper sh1 = xjdfHelper.getCreateSet(ElementName.EXPOSEDMEDIA, EnumUsage.Input, null);
		final ResourceHelper rh1 = sh1.appendPartition(map, true);
		final JDFExposedMedia xm = (JDFExposedMedia) rh1.getResource();
		xm.setAttribute("MediaRef", rh2.getRoot().appendAnchor(null));

		final SetHelper sh5 = xjdfHelper.getCreateSet(ElementName.VARNISHINGPARAMS, EnumUsage.Input, null);
		final JDFAttributeMap map2 = map.clone();
		map2.remove(AttributeName.SEPARATION);

		final ResourceHelper rh5 = sh5.appendPartition(map2, true);
		final JDFVarnishingParams vp = (JDFVarnishingParams) rh5.getResource();
		vp.setVarnishMethod(EnumVarnishMethod.Plate);

		final SetHelper sh4 = xjdfHelper.getCreateSet(ElementName.INK, EnumUsage.Input, null);
		final ResourceHelper rh4 = sh4.appendPartition("Separation", "Varnish", true);
		final JDFInk ink = (JDFInk) rh4.getResource();
		rh4.setBrand("Acme Varnish");
		ink.setAttribute(XJDFConstants.InkType, "Varnish");

		xjdfHelper.cleanUp();
		setSnippet(xjdfHelper, true);
		setSnippet(xjdfHelper.getAuditPool(), false);
		writeTest(xjdfHelper, "building/co_plateVarnish.xjdf");
	}

	/**
	 *
	 */
	@Test
	public void testRemoveVarnish()
	{
		final JDFAttributeMap map = new JDFAttributeMap("SheetName", "Body");
		final XJDFHelper xjdfHelper = new XJDFHelper("ChangeOrder", "RemoveVarnish", new VJDFAttributeMap(map));

		xjdfHelper.setTypes(EnumType.Varnishing.getName());
		xjdfHelper.getSet(ElementName.NODEINFO, 0).deleteNode();

		final SetHelper sh2 = xjdfHelper.getCreateSet(ElementName.VARNISHINGPARAMS, EnumUsage.Input);
		sh2.appendPartition(map, false);

		xjdfHelper.cleanUp();
		setSnippet(xjdfHelper, true);
		setSnippet(xjdfHelper.getAuditPool(), false);
		writeTest(xjdfHelper, "building/removeVarnish.xjdf");

	}

	/**
	 *
	 */
	@Test
	public void testAddVarnish()
	{
		final JDFAttributeMap map = new JDFAttributeMap("SheetName", "Sheet1");
		final XJDFHelper xjdfHelper = new XJDFHelper("ChangeOrder", "AddVarnish", new VJDFAttributeMap(map));
		map.put("Side", "Front");
		map.put("Separation", "Var");

		xjdfHelper.setTypes(EnumType.Varnishing.getName());

		final SetHelper sh2 = xjdfHelper.getCreateSet(XJDFConstants.Resource, ElementName.MEDIA, null);
		final ResourceHelper rh2 = sh2.appendPartition(null, true);
		final JDFMedia m = (JDFMedia) rh2.getResource();
		m.setMediaType(EnumMediaType.Blanket);

		final SetHelper sh1 = xjdfHelper.getCreateSet(ElementName.EXPOSEDMEDIA, EnumUsage.Input, null);
		final ResourceHelper rh1 = sh1.appendPartition(map, true);
		final JDFExposedMedia xm = (JDFExposedMedia) rh1.getResource();
		xm.setAttribute("MediaRef", rh2.getRoot().appendAnchor(null));

		final SetHelper sh3 = xjdfHelper.getCreateSet(ElementName.COLOR, EnumUsage.Input, null);
		final ResourceHelper rh3 = sh3.appendPartition("Separation", "Varnish", true);
		final JDFColor c = (JDFColor) rh3.getResource();
		c.setActualColorName("Acme Gloss Varnish");

		final SetHelper sh4 = xjdfHelper.getCreateSet(ElementName.INK, EnumUsage.Input, null);
		final ResourceHelper rh4 = sh4.appendPartition("Separation", "Varnish", true);
		final JDFInk ink = (JDFInk) rh4.getResource();
		rh4.setBrand("Acme Gloss Varnish");
		ink.setAttribute(XJDFConstants.InkType, "Gloss Varnish");

		final SetHelper sh5 = xjdfHelper.getCreateSet(ElementName.VARNISHINGPARAMS, EnumUsage.Input, null);
		final JDFAttributeMap map2 = map.clone();
		map2.remove(AttributeName.SEPARATION);

		final ResourceHelper rh5 = sh5.appendPartition(map2, true);
		final JDFVarnishingParams vp = (JDFVarnishingParams) rh5.getResource();
		vp.setVarnishMethod(EnumVarnishMethod.Blanket);

		final SetHelper sh6 = xjdfHelper.getCreateSet(ElementName.DEVICE, EnumUsage.Input, null);
		final ResourceHelper rh6 = sh6.getCreatePartition(map2, true);
		rh6.setAttribute(AttributeName.DESCRIPTIVENAME, "Offline Acme varnishiner");
		rh6.getResource().setAttribute(AttributeName.DEVICEID, "Var_1");

		xjdfHelper.cleanUp();
		setSnippet(xjdfHelper, true);
		setSnippet(xjdfHelper.getAuditPool(), false);
		writeTest(xjdfHelper, "building/co_varnish.xjdf");
	}

	/**
	 *
	 */
	@Test
	public void testDevice()
	{
		final XJDFHelper xjdfHelper = new XJDFHelper("ChangeOrder", "Device", new VJDFAttributeMap(new JDFAttributeMap("SheetName", "Sheet1")));
		xjdfHelper.setTypes("Folding");
		final JDFDate jdfDate = new JDFDate().setTime(13, 0, 0);
		final SetHelper sh1 = xjdfHelper.getCreateSet(ElementName.DEVICE, EnumUsage.Input, null);
		sh1.getCreatePartition("SheetName", "Sheet1", true).getResource().setAttribute(AttributeName.DEVICEID, "Folder2");
		final SetHelper sh2 = xjdfHelper.getSet(ElementName.NODEINFO, EnumUsage.Input, null);
		jdfDate.addOffset(0, 0, 4, 0);
		sh2.getCreatePartition("SheetName", "Sheet1", true).getResource().setAttribute(AttributeName.START, jdfDate.getDateTimeISO());
		xjdfHelper.cleanUp();
		setSnippet(xjdfHelper, true);
		setSnippet(xjdfHelper.getAuditPool(), false);
		writeTest(xjdfHelper, "building/co_device.xjdf");
	}

	/**
	 *
	 */
	@Test
	public void testPaper()
	{
		final XJDFHelper xjdfHelper = new XJDFHelper("ChangeOrder", "Paper", new VJDFAttributeMap(new JDFAttributeMap("SheetName", "Sheet1")));
		xjdfHelper.setTypes("ConventionalPrinting");
		final SetHelper sh1 = xjdfHelper.getCreateSet(XJDFConstants.Resource, ElementName.COMPONENT, EnumUsage.Input);
		final ResourceHelper rh1 = sh1.appendPartition("SheetName", "Sheet1", true);
		final SetHelper sh2 = xjdfHelper.getCreateSet(XJDFConstants.Resource, ElementName.MEDIA, null);
		final ResourceHelper rh2 = sh2.appendPartition(null, true);
		final JDFMedia m = (JDFMedia) rh2.getResource();
		m.setWeight(120);
		m.setMediaType(EnumMediaType.Paper);
		final JDFComponent c = (JDFComponent) rh1.getResource();
		c.setAttribute("MediaRef", rh2.getRoot().appendAnchor(null));
		rh1.setAmount(4000, null, true);
		xjdfHelper.getSet(ElementName.NODEINFO, EnumUsage.Input, null).deleteNode();

		xjdfHelper.cleanUp();
		setSnippet(xjdfHelper, true);
		setSnippet(xjdfHelper.getAuditPool(), false);
		writeTest(xjdfHelper, "building/co_paper.xjdf");
	}

}
