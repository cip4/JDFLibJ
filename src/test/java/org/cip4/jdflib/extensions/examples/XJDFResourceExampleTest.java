/**
 * The CIP4 Software License, Version 1.0
 *
 * Copyright (c) 2001-2019 The International Cooperation for the Integration of Processes in Prepress, Press and Postpress (CIP4). All rights reserved.
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

import org.cip4.jdflib.auto.JDFAutoColorSpaceConversionOp.EnumOperation;
import org.cip4.jdflib.auto.JDFAutoMedia.EnumMediaType;
import org.cip4.jdflib.core.AttributeName;
import org.cip4.jdflib.core.ElementName;
import org.cip4.jdflib.core.JDFElement;
import org.cip4.jdflib.core.JDFElement.EnumVersion;
import org.cip4.jdflib.core.JDFResourceLink.EnumUsage;
import org.cip4.jdflib.core.JDFSeparationList;
import org.cip4.jdflib.core.VString;
import org.cip4.jdflib.datatypes.JDFAttributeMap;
import org.cip4.jdflib.datatypes.JDFRGBColor;
import org.cip4.jdflib.datatypes.VJDFAttributeMap;
import org.cip4.jdflib.extensions.ResourceHelper;
import org.cip4.jdflib.extensions.SetHelper;
import org.cip4.jdflib.extensions.XJDFConstants;
import org.cip4.jdflib.extensions.XJDFHelper;
import org.cip4.jdflib.node.JDFNode.EnumType;
import org.cip4.jdflib.resource.JDFResource;
import org.cip4.jdflib.resource.JDFResource.EnumPartIDKey;
import org.cip4.jdflib.resource.process.JDFColor;
import org.cip4.jdflib.resource.process.JDFColorantAlias;
import org.cip4.jdflib.resource.process.JDFColorantControl;
import org.cip4.jdflib.resource.process.JDFComponent;
import org.cip4.jdflib.resource.process.JDFFileSpec;
import org.cip4.jdflib.resource.process.JDFMedia;
import org.cip4.jdflib.resource.process.prepress.JDFColorSpaceConversionOp;
import org.cip4.jdflib.resource.process.prepress.JDFColorSpaceConversionParams;
import org.cip4.jdflib.resource.process.press.JDFPrintCondition;
import org.cip4.jdflib.util.StringUtil;
import org.junit.Test;

/**
 *
 * @author rainer prosi
 *
 */
public class XJDFResourceExampleTest extends ExampleTest
{
	/**
	 * @see org.cip4.jdflib.JDFTestCaseBase#setUp()
	 */
	@Override
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
	public final void testColorantAlias()
	{
		final XJDFHelper xjdfHelper = new XJDFHelper("RawName", null, null);
		xjdfHelper.setTypes(EnumType.ColorSpaceConversion.getName());
		final SetHelper cch = xjdfHelper.getCreateSet(ElementName.COLORANTCONTROL, EnumUsage.Input, null);
		final ResourceHelper ccrh = cch.appendPartition(null, true);
		final JDFColorantControl colControl = (JDFColorantControl) ccrh.getResource();

		JDFColorantAlias ca = colControl.appendColorantAlias();
		ca.setReplacementColorantName("Spot1");
		ca.setAttribute("ColorantName", "Grün");
		ca.setAttribute(AttributeName.RAWNAME, "4772FC6E");

		ca = (JDFColorantAlias) colControl.copyElement(ca, null);
		ca.setAttribute("ColorantName", "grün");
		ca.setAttribute(AttributeName.RAWNAME, "6772FC6E");

		final SetHelper colh = xjdfHelper.getCreateSet(ElementName.COLOR, EnumUsage.Input, null);
		final ResourceHelper colrh = colh.appendPartition(AttributeName.SEPARATION, "Spot1", true);
		final JDFColor color = (JDFColor) colrh.getResource();
		color.setsRGB(new JDFRGBColor(0, 1, 0));
		color.setActualColorName("Green");
		color.setRawName(StringUtil.setHexBinaryBytes("Green".getBytes(), -1));

		cleanSnippets(xjdfHelper);
		writeTest(xjdfHelper, "resources/ColorantAlias.xjdf");
	}

	/**
	 *
	 */
	@Test
	public final void testPrintConditions()
	{
		final XJDFHelper h = new XJDFHelper("PrintCondition", null, null);
		h.addType(EnumType.ColorSpaceConversion).addType(EnumType.Interpreting).addType(EnumType.Rendering).addType(EnumType.DigitalPrinting);

		final SetHelper sint = h.getCreateSet(ElementName.PRINTCONDITION, EnumUsage.Input);
		final ResourceHelper rint = sint.getCreatePartition(EnumPartIDKey.PrintCondition.getName(), "7-Color-gloss", true);
		final JDFPrintCondition intp = (JDFPrintCondition) rint.getResource();
		//		intp.setAttribute("PrintQuality", EnumPrintQuality.High.getName());
		intp.setAttribute("Name", "7-Color");

		final SetHelper smed = h.getCreateSet(ElementName.MEDIA, null);
		final ResourceHelper rmed = smed.getCreatePartition(EnumPartIDKey.PrintCondition.getName(), "7-Color-gloss", true);
		final JDFMedia med = (JDFMedia) rmed.getResource();
		med.setMediaType(EnumMediaType.Paper);

		final SetHelper scomp = h.getCreateSet(ElementName.COMPONENT, EnumUsage.Input);
		final ResourceHelper rcomp = scomp.getCreatePartition(EnumPartIDKey.PrintCondition.getName(), "7-Color-gloss", true);
		final JDFComponent comp = (JDFComponent) rcomp.getResource();
		comp.setAttribute("MediaRef", rmed.ensureID());

		final SetHelper scol = h.getCreateSet(ElementName.COLOR, EnumUsage.Input);
		for (final String sep : JDFSeparationList.SEPARATIONS_CMYK)
		{
			final ResourceHelper rcol = scol.getCreatePartition(EnumPartIDKey.PrintCondition.getName(), "7-Color-gloss", true);
			rcol.ensurePart(AttributeName.SEPARATION, sep);
			final JDFColor col = (JDFColor) rcol.getResource();
			col.setAttribute(XJDFConstants.PrintStandard, "7-Color");
		}

		final SetHelper scsp = h.getCreateSet(ElementName.COLORSPACECONVERSIONPARAMS, EnumUsage.Input);
		final ResourceHelper rcsp = scsp.getCreatePartition(EnumPartIDKey.PrintCondition.getName(), "7-Color-gloss", true);
		final JDFColorSpaceConversionParams csp = (JDFColorSpaceConversionParams) rcsp.getResource();
		final JDFColorSpaceConversionOp op = csp.appendColorSpaceConversionOp();
		op.setOperation(EnumOperation.Convert);
		final JDFFileSpec filespec = csp.appendFileSpec();
		filespec.setURL("file://7-color-gloss.icc");

		cleanSnippets(h);
		writeTest(h, "resources/PrintCondition20.xjdf");

	}

	/**
	 *
	 */
	@Test
	public final void testPrintConditions21()
	{
		final XJDFHelper h = new XJDFHelper("PrintCondition", null, null);
		h.setVersion(EnumVersion.Version_2_1);
		h.addType(EnumType.ColorSpaceConversion).addType(EnumType.Interpreting).addType(EnumType.Rendering).addType(EnumType.DigitalPrinting);

		final SetHelper smed = h.getCreateSet(ElementName.MEDIA, null);
		final ResourceHelper rmed = smed.getCreatePartition(EnumPartIDKey.PrintCondition.getName(), "7-Color-gloss", true);
		final JDFMedia med = (JDFMedia) rmed.getResource();
		med.setMediaType(EnumMediaType.Paper);

		final SetHelper scomp = h.getCreateSet(ElementName.COMPONENT, EnumUsage.Input);
		final ResourceHelper rcomp = scomp.getCreatePartition(EnumPartIDKey.PrintCondition.getName(), "7-Color-gloss", true);
		final JDFComponent comp = (JDFComponent) rcomp.getResource();
		comp.setAttribute("MediaRef", rmed.ensureID());

		final SetHelper printCondition = h.getCreateSet(ElementName.PRINTCONDITION, EnumUsage.Input);
		final ResourceHelper rPC = printCondition.getCreatePartition(0, true);
		final JDFResource pc = (JDFResource) rPC.getResource();
		pc.setAttribute(AttributeName.NAME, "7-Color");
		pc.setAttribute(ElementName.COLORANTORDER, "Black Cyan Magenta Yellow Orange Green Violet");
		pc.setAttribute(AttributeName.PRINTQUALITY, "High");

		final SetHelper scsp = h.getCreateSet(ElementName.COLORSPACECONVERSIONPARAMS, EnumUsage.Input);
		final ResourceHelper rcsp = scsp.getCreatePartition(EnumPartIDKey.PrintCondition.getName(), "7-Color-gloss", true);
		final JDFColorSpaceConversionParams csp = (JDFColorSpaceConversionParams) rcsp.getResource();
		final JDFColorSpaceConversionOp op = csp.appendColorSpaceConversionOp();
		op.setOperation(EnumOperation.Convert);
		final JDFFileSpec filespec = csp.appendFileSpec();
		filespec.setURL("file://7-color-gloss.icc");

		cleanSnippets(h);
		writeTest(h, "resources/PrintCondition21.xjdf");

	}

	/**
	 *
	 */
	@Test
	public final void testPrintConditionCoverBody()
	{
		final XJDFHelper h = new XJDFHelper("PrintCondition", null, null);
		h.setVersion(EnumVersion.Version_2_1);
		h.addType(EnumType.ColorSpaceConversion).addType(EnumType.Interpreting).addType(EnumType.Rendering).addType(EnumType.DigitalPrinting);

		final VString pcs = new VString("7-Color-Gloss 4-Color-Matte", null);

		for (final String pc : pcs)
		{
			final SetHelper sint = h.getCreateSet(ElementName.PRINTCONDITION, EnumUsage.Input);
			final ResourceHelper rint = sint.getCreatePartition(EnumPartIDKey.PrintCondition.getName(), pc, true);
			final JDFResource rpc = (JDFResource) rint.getResource();
			if (pc.startsWith("7"))
			{
				rpc.setAttribute(AttributeName.NAME, "7-Color");
				rpc.setAttribute(ElementName.COLORANTORDER, "Black Cyan Magenta Yellow Orange Green Violet");
				rpc.setAttribute(AttributeName.PRINTQUALITY, "High");
			}
			else
			{
				rpc.setAttribute(AttributeName.NAME, "4-Color");
				rpc.setAttribute(ElementName.COLORANTORDER, "Black Cyan Magenta Yellow");
				rpc.setAttribute(AttributeName.PRINTQUALITY, "Normal");
			}
		}
		final SetHelper smed = h.getCreateSet(ElementName.MEDIA, null);
		final SetHelper scomp = h.getCreateSet(ElementName.COMPONENT, EnumUsage.Input);
		for (final String pc : pcs)
		{
			final ResourceHelper rmed = smed.getCreatePartition(EnumPartIDKey.PrintCondition.getName(), pc, true);
			final JDFMedia med = (JDFMedia) rmed.getResource();
			med.setMediaType(EnumMediaType.Paper);
			final String coating = StringUtil.token(pc, -1, "-");
			rmed.setExternalID("Media-" + coating);
			med.setAttribute(XJDFConstants.Coating, coating);

			final ResourceHelper rcomp = scomp.getCreatePartition(EnumPartIDKey.PrintCondition.getName(), pc, true);
			final JDFComponent comp = (JDFComponent) rcomp.getResource();
			comp.setAttribute("MediaRef", rmed.ensureID());

		}

		final SetHelper scsp = h.getCreateSet(ElementName.COLORSPACECONVERSIONPARAMS, EnumUsage.Input);
		for (final String pc : pcs)
		{
			final ResourceHelper rcsp = scsp.getCreatePartition(EnumPartIDKey.PrintCondition.getName(), pc, true);
			final JDFColorSpaceConversionParams csp = (JDFColorSpaceConversionParams) rcsp.getResource();
			final JDFColorSpaceConversionOp op = csp.appendColorSpaceConversionOp();
			op.setOperation(EnumOperation.Convert);
			final JDFFileSpec filespec = csp.appendFileSpec();
			filespec.setURL("file://" + pc + ".icc");
		}
		cleanSnippets(h);
		writeTest(h, "resources/PrintConditionCoverBody.xjdf");

	}

	/**
	 *
	 */
	@Test
	public void testResourceSparseParts()
	{
		for (int i = 0; i < 2; i++)
		{
			final XJDFHelper xjdfHelper = new XJDFHelper("Sparse", null, null);
			xjdfHelper.setTypes(EnumType.ManualLabor.getName());
			final SetHelper sh = xjdfHelper.appendResourceSet(ElementName.MANUALLABORPARAMS, EnumUsage.Input);
			final JDFAttributeMap sheetMap = new JDFAttributeMap(AttributeName.SHEETNAME, i == 0 ? "SGood" : "SBad");
			if (i == 0)
			{
				sh.appendPartition(sheetMap, true).setDescriptiveName("Good example with sparse Part elements");
			}
			else
			{
				final VJDFAttributeMap sideMaps = new VJDFAttributeMap(sheetMap);
				sideMaps.extendMap(AttributeName.SIDE, VString.getVString("Front Back", null));
				sh.appendResource(sideMaps, true).setDescriptiveName("Bad example with overspecified Part elements");
			}
			sh.getPartition(0).getResource().setAttribute(AttributeName.LABORTYPE, "PhoneCallToCustomer");
			xjdfHelper.cleanUp();
			setSnippet(sh.getPartition(0), true);
			writeTest(xjdfHelper, "resources/" + (i == 0 ? "good" : "bad") + "sparsepartition.xjdf");
		}
	}

}
