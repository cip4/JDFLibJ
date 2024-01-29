/**
 * The CIP4 Software License, Version 1.0
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
package org.cip4.jdflib.extensions.examples;

import java.io.IOException;
import java.util.List;
import java.util.Vector;

import org.cip4.jdflib.auto.JDFAutoColor.EnumColorType;
import org.cip4.jdflib.auto.JDFAutoConventionalPrintingParams.EnumWorkStyle;
import org.cip4.jdflib.auto.JDFAutoMedia.EnumISOPaperSubstrate;
import org.cip4.jdflib.auto.JDFAutoMedia.EnumMediaType;
import org.cip4.jdflib.auto.JDFAutoVarnishingParams.EnumVarnishArea;
import org.cip4.jdflib.auto.JDFAutoVarnishingParams.EnumVarnishMethod;
import org.cip4.jdflib.core.AttributeName;
import org.cip4.jdflib.core.ElementName;
import org.cip4.jdflib.core.JDFElement;
import org.cip4.jdflib.core.JDFElement.EnumValidationLevel;
import org.cip4.jdflib.core.JDFElement.EnumVersion;
import org.cip4.jdflib.core.JDFResourceLink.EnumUsage;
import org.cip4.jdflib.core.VString;
import org.cip4.jdflib.datatypes.JDFAttributeMap;
import org.cip4.jdflib.datatypes.JDFXYPair;
import org.cip4.jdflib.datatypes.VJDFAttributeMap;
import org.cip4.jdflib.extensions.ResourceHelper;
import org.cip4.jdflib.extensions.SetHelper;
import org.cip4.jdflib.extensions.XJDFConstants;
import org.cip4.jdflib.extensions.XJDFHelper;
import org.cip4.jdflib.node.ICSVersion;
import org.cip4.jdflib.node.JDFNode.EnumType;
import org.cip4.jdflib.resource.JDFTool;
import org.cip4.jdflib.resource.JDFVarnishingParams;
import org.cip4.jdflib.resource.process.JDFColor;
import org.cip4.jdflib.resource.process.JDFExposedMedia;
import org.cip4.jdflib.resource.process.JDFMedia;
import org.cip4.jdflib.resource.process.prepress.JDFInk;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**
 *
 * @author rainer prosi
 *
 */
public class XJDFConvPrintExampleTest extends ExampleTest
{
	/**
	 * @see org.cip4.jdflib.JDFTestCaseBase#setUp()
	 */
	@Override
	@BeforeEach
	public void setUp() throws Exception
	{
		JDFElement.setLongID(false);
		super.setUp();
	}

	/**
	*
	*
	*/
	@Test
	public final void testConventionalPrinting()
	{
		final XJDFHelper xjdfHelper = new XJDFHelper("ConventionalPrintingExample", null, null);
		xjdfHelper.addType(EnumType.ConventionalPrinting);
		final SetHelper cpp = xjdfHelper.getCreateSet(ElementName.CONVENTIONALPRINTINGPARAMS, EnumUsage.Input);
		cpp.getCreateResource(0, true).setResourceAttribute(AttributeName.WORKSTYLE, EnumWorkStyle.Perfecting.getName());

		final SetHelper inComp = xjdfHelper.getCreateSet(ElementName.COMPONENT, EnumUsage.Input);
		final SetHelper outComp = xjdfHelper.getCreateSet(ElementName.COMPONENT, EnumUsage.Output);
		final SetHelper mediaSet = xjdfHelper.getCreateSet(ElementName.MEDIA, null);
		final ResourceHelper media = mediaSet.getCreateResource();
		media.ensureReference(inComp.getCreateResource(), null);
		media.ensureReference(outComp.getCreateResource(), null);
		media.setResourceEnum(AttributeName.MEDIATYPE, EnumMediaType.Paper);
		writeTest(xjdfHelper, "processes/ConventionalPrintingExample.xjdf");
	}

	/**
	*
	*
	*/
	@Test
	public final void testConventionalPrintingICSLvl1()
	{
		final XJDFHelper xjdfHelper = new XJDFHelper("ConventionalPrintingExample", null, null);
		xjdfHelper.addType(EnumType.ConventionalPrinting);
		final ICSVersion ics = new ICSVersion("MIS-CP", 1, XJDFHelper.getDefaultVersion());
		xjdfHelper.setICSVersions(ics, ics.getBase("MIS", 1));
		final SetHelper cpp = xjdfHelper.getCreateSet(ElementName.CONVENTIONALPRINTINGPARAMS, EnumUsage.Input);
		cpp.getCreateResource(0, true).setResourceAttribute(AttributeName.WORKSTYLE, EnumWorkStyle.Perfecting.getName());

		final SetHelper inComp = xjdfHelper.getCreateSet(ElementName.COMPONENT, EnumUsage.Input);
		final SetHelper outComp = xjdfHelper.getCreateSet(ElementName.COMPONENT, EnumUsage.Output);
		final SetHelper mediaSet = xjdfHelper.getCreateSet(ElementName.MEDIA, null);
		final ResourceHelper media = mediaSet.getCreateResource();
		media.ensureReference(inComp.getCreateResource(), null);
		media.ensureReference(outComp.getCreateResource(), null);
		media.setResourceEnum(AttributeName.MEDIATYPE, EnumMediaType.Paper);
		writeTest(xjdfHelper, "processes/ConventionalPrintingICS1.xjdf");
	}

	/**
	 * @throws IOException
	 * @throws JDF_AccessException
	 * @throws StorageAccessException
	 */
	@Test
	public void testSubmitVarnishXJDFFlood()
	{
		final long ct = System.currentTimeMillis();
		final String jobID = "Flood" + ct;
		final XJDFHelper xjdfHelper = new XJDFHelper(EnumVersion.Version_2_1, jobID);
		xjdfHelper.setTypes(new VString("ConventionalPrinting Varnishing"));

		final SetHelper cp = xjdfHelper.getCreateSet(ElementName.CONVENTIONALPRINTINGPARAMS, EnumUsage.Input);
		cp.getCreatePartition(null, true).getResource().setAttribute(AttributeName.WORKSTYLE, EnumWorkStyle.Simplex.getName());

		final SetHelper pm = xjdfHelper.getCreateSet(ElementName.MEDIA, null);
		final SetHelper pmPaper = xjdfHelper.appendSet(ElementName.MEDIA, null);
		final ResourceHelper mediaPartPaper = pmPaper.getCreatePartition(null, true);
		final JDFMedia paperMedia = (JDFMedia) mediaPartPaper.getResource();
		paperMedia.setDimensionCM(new JDFXYPair(120, 75));
		paperMedia.setMediaType(EnumMediaType.Paper);
		paperMedia.setISOPaperSubstrate(EnumISOPaperSubstrate.PS2);

		final String v1 = "varnish1";
		final ResourceHelper mediaPartV = pm.getCreatePartition(new JDFAttributeMap(AttributeName.SEPARATION, v1), true);
		final JDFMedia plateMediaV = (JDFMedia) mediaPartV.getResource();
		plateMediaV.setDimensionCM(new JDFXYPair(130, 80));
		plateMediaV.setMediaType(EnumMediaType.Blanket);

		final ResourceHelper mediaPart = pm.getCreatePartition(null, true);

		final JDFMedia plateMedia = (JDFMedia) mediaPart.getResource();
		plateMedia.setDimensionCM(new JDFXYPair(130, 80));
		plateMedia.setMediaType(EnumMediaType.Plate);

		final SetHelper compH = xjdfHelper.getCreateSet(ElementName.COMPONENT, EnumUsage.Output);
		final ResourceHelper compR = compH.getCreatePartition(new JDFAttributeMap(AttributeName.SHEETNAME, "S1"), true);
		compR.setAmount(1000, null, true);
		mediaPartPaper.ensureReference(compR, XJDFConstants.MediaRef);

		final SetHelper compIn = xjdfHelper.getCreateSet(ElementName.COMPONENT, EnumUsage.Input);
		final ResourceHelper compInR = compIn.getCreatePartition(new JDFAttributeMap(AttributeName.SHEETNAME, "S1"), true);
		compInR.setAmount(100, null, false);
		compInR.setAmount(1000, null, true);
		mediaPartPaper.ensureReference(compInR, XJDFConstants.MediaRef);

		final SetHelper xmH = xjdfHelper.getCreateSet(ElementName.EXPOSEDMEDIA, EnumUsage.Input);

		final SetHelper colorH = xjdfHelper.getCreateSet(ElementName.COLOR, EnumUsage.Input);

		final SetHelper inkH = xjdfHelper.getCreateSet(ElementName.INK, EnumUsage.Input);

		final SetHelper niH = xjdfHelper.getCreateSet(ElementName.NODEINFO, EnumUsage.Input);
		niH.removePartitions();

		final SetHelper vp = xjdfHelper.getCreateSet(ElementName.VARNISHINGPARAMS, EnumUsage.Input);
		final SetHelper tool = xjdfHelper.getCreateSet(ElementName.TOOL, EnumUsage.Input);

		final JDFAttributeMap surfaceMap = new JDFAttributeMap(AttributeName.SHEETNAME, "S1");
		surfaceMap.put(AttributeName.SIDE, "Front");

		final VJDFAttributeMap front1 = new VJDFAttributeMap(surfaceMap);
		final List<String> cmykSeparations = JDFColor.getCMYKSeparations();
		front1.extendMap(AttributeName.SEPARATION, cmykSeparations);

		final List<String> allSeparations = JDFColor.getCMYKSeparations();
		allSeparations.add(v1);
		final VJDFAttributeMap allColors = new VJDFAttributeMap(new JDFAttributeMap());
		allColors.extendMap(AttributeName.SEPARATION, allSeparations);

		final VJDFAttributeMap varnishes = new VJDFAttributeMap(surfaceMap);
		varnishes.extendMap(AttributeName.SEPARATION, new VString(v1, null));

		final JDFVarnishingParams vpr = (JDFVarnishingParams) vp.getCreatePartitions(varnishes, true).get(0).getResource();
		vpr.setVarnishArea(EnumVarnishArea.Full);
		vpr.setVarnishMethod(EnumVarnishMethod.Blanket);

		final ResourceHelper toolRH = tool.getCreatePartitions(varnishes, true).get(0);
		toolRH.setExternalID("R123");
		final JDFTool toolRes = (JDFTool) toolRH.getResource();
		toolRes.setToolType("ScreeningRoller");

		final Vector<ResourceHelper> vXM = xmH.getCreatePartitions(allColors, true);
		for (final ResourceHelper xm : vXM)
		{
			final JDFExposedMedia xmr = (JDFExposedMedia) xm.getResource();
			mediaPart.ensureReference(xmr, XJDFConstants.MediaRef);
		}
		final JDFExposedMedia xmv = (JDFExposedMedia) xmH.getPartition(varnishes.get(0)).getResource();
		mediaPartV.ensureReference(xmv, XJDFConstants.MediaRef);

		final Vector<ResourceHelper> vColors = colorH.getCreatePartitions(allColors, true);
		int i = 0;
		for (final ResourceHelper c : vColors)
		{
			((JDFColor) (c.getResource())).setColorType(i++ == 4 ? EnumColorType.Transparent : EnumColorType.Normal);
		}
		final Vector<ResourceHelper> vInks = inkH.getCreatePartitions(allColors, true);
		i = 0;
		for (final ResourceHelper ink : vInks)
		{
			((JDFInk) (ink.getResource())).setAttribute(XJDFConstants.InkType, (i++ == 4 ? "Varnish" : "Ink"));
		}

		cleanSnippets(xjdfHelper);
		writeRoundTripX(xjdfHelper.getRoot(), "floodvarnish", EnumValidationLevel.Complete);
	}

	/**
	 * @throws IOException
	 * @throws JDF_AccessException
	 * @throws StorageAccessException
	 */
	@Test
	public void testSubmitVarnishXJDFFlexo()
	{
		final long ct = System.currentTimeMillis();
		final String jobID = "Flexo" + ct;
		final XJDFHelper xjdfHelper = new XJDFHelper(EnumVersion.Version_2_1, jobID);
		xjdfHelper.setTypes(new VString("ConventionalPrinting Varnishing"));

		final SetHelper cp = xjdfHelper.getCreateSet(ElementName.CONVENTIONALPRINTINGPARAMS, EnumUsage.Input);
		cp.getCreatePartition(null, true).getResource().setAttribute(AttributeName.WORKSTYLE, EnumWorkStyle.Simplex.getName());

		final SetHelper pm = xjdfHelper.getCreateSet(ElementName.MEDIA, null);
		final SetHelper pmPaper = xjdfHelper.appendSet(ElementName.MEDIA, null);
		final ResourceHelper mediaPartPaper = pmPaper.getCreatePartition(null, true);
		final JDFMedia paperMedia = (JDFMedia) mediaPartPaper.getResource();
		paperMedia.setDimensionCM(new JDFXYPair(120, 75));
		paperMedia.setMediaType(EnumMediaType.Paper);
		paperMedia.setISOPaperSubstrate(EnumISOPaperSubstrate.PS2);

		final String v1 = "varnish1";
		final ResourceHelper mediaPartV = pm.getCreatePartition(new JDFAttributeMap(AttributeName.SEPARATION, v1), true);
		final JDFMedia plateMediaV = (JDFMedia) mediaPartV.getResource();
		plateMediaV.setDimensionCM(new JDFXYPair(130, 80));
		plateMediaV.setMediaType(EnumMediaType.Blanket);

		final ResourceHelper mediaPart = pm.getCreatePartition(null, true);

		final JDFMedia plateMedia = (JDFMedia) mediaPart.getResource();
		plateMedia.setDimensionCM(new JDFXYPair(130, 80));
		plateMedia.setMediaType(EnumMediaType.Plate);

		final SetHelper compH = xjdfHelper.getCreateSet(ElementName.COMPONENT, EnumUsage.Output);
		final ResourceHelper compR = compH.getCreatePartition(new JDFAttributeMap(AttributeName.SHEETNAME, "S1"), true);
		compR.setAmount(1000, null, true);
		mediaPartPaper.ensureReference(compR, XJDFConstants.MediaRef);

		final SetHelper compIn = xjdfHelper.getCreateSet(ElementName.COMPONENT, EnumUsage.Input);
		final ResourceHelper compInR = compIn.getCreatePartition(new JDFAttributeMap(AttributeName.SHEETNAME, "S1"), true);
		compInR.setAmount(100, null, false);
		compInR.setAmount(1000, null, true);
		mediaPartPaper.ensureReference(compInR, XJDFConstants.MediaRef);

		final SetHelper xmH = xjdfHelper.getCreateSet(ElementName.EXPOSEDMEDIA, EnumUsage.Input);

		final SetHelper colorH = xjdfHelper.getCreateSet(ElementName.COLOR, EnumUsage.Input);

		final SetHelper inkH = xjdfHelper.getCreateSet(ElementName.INK, EnumUsage.Input);

		final SetHelper niH = xjdfHelper.getCreateSet(ElementName.NODEINFO, EnumUsage.Input);
		niH.removePartitions();

		final SetHelper vp = xjdfHelper.getCreateSet(ElementName.VARNISHINGPARAMS, EnumUsage.Input);
		final SetHelper tool = xjdfHelper.getCreateSet(ElementName.TOOL, EnumUsage.Input);

		final JDFAttributeMap surfaceMap = new JDFAttributeMap(AttributeName.SHEETNAME, "S1");
		surfaceMap.put(AttributeName.SIDE, "Front");

		final VJDFAttributeMap front1 = new VJDFAttributeMap(surfaceMap);
		final List<String> cmykSeparations = JDFColor.getCMYKSeparations();
		front1.extendMap(AttributeName.SEPARATION, cmykSeparations);

		final List<String> allSeparations = JDFColor.getCMYKSeparations();
		allSeparations.add(v1);
		final VJDFAttributeMap allColors = new VJDFAttributeMap(new JDFAttributeMap());
		allColors.extendMap(AttributeName.SEPARATION, allSeparations);

		final VJDFAttributeMap varnishes = new VJDFAttributeMap(surfaceMap);
		varnishes.extendMap(AttributeName.SEPARATION, new VString(v1, null));

		final JDFVarnishingParams vpr = (JDFVarnishingParams) vp.getCreatePartitions(varnishes, true).get(0).getResource();
		vpr.setVarnishArea(EnumVarnishArea.Spot);
		vpr.setVarnishMethod(EnumVarnishMethod.Plate);
		vpr.setModuleType("CoatingModule");

		final ResourceHelper toolRH = tool.getCreatePartitions(varnishes, true).get(0);
		toolRH.setExternalID("R123");
		final JDFTool toolRes = (JDFTool) toolRH.getResource();
		toolRes.setToolType("ScreeningRoller");

		final Vector<ResourceHelper> vXM = xmH.getCreatePartitions(allColors, true);
		for (final ResourceHelper xm : vXM)
		{
			final JDFExposedMedia xmr = (JDFExposedMedia) xm.getResource();
			mediaPart.ensureReference(xmr, XJDFConstants.MediaRef);
		}
		final ResourceHelper xmvr = xmH.getPartition(varnishes.get(0));
		final JDFExposedMedia xmv = (JDFExposedMedia) xmvr.getResource();
		xmvr.setExternalID("FlexoPlate123");
		mediaPartV.ensureReference(xmv, XJDFConstants.MediaRef);

		final Vector<ResourceHelper> vColors = colorH.getCreatePartitions(allColors, true);
		int i = 0;
		for (final ResourceHelper c : vColors)
		{
			((JDFColor) (c.getResource())).setColorType(i++ == 4 ? EnumColorType.Transparent : EnumColorType.Normal);
		}
		final Vector<ResourceHelper> vInks = inkH.getCreatePartitions(allColors, true);
		i = 0;
		for (final ResourceHelper ink : vInks)
		{
			((JDFInk) (ink.getResource())).setAttribute(XJDFConstants.InkType, (i++ == 4 ? "Varnish" : "Ink"));
		}

		cleanSnippets(xjdfHelper);
		writeRoundTripX(xjdfHelper.getRoot(), "flexovarnish", EnumValidationLevel.Complete);
	}

}
