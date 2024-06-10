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

import org.cip4.jdflib.core.AttributeName;
import org.cip4.jdflib.core.ElementName;
import org.cip4.jdflib.core.JDFAudit;
import org.cip4.jdflib.core.JDFConstants;
import org.cip4.jdflib.core.JDFElement.EnumVersion;
import org.cip4.jdflib.core.JDFResourceLink.EnumUsage;
import org.cip4.jdflib.core.KElement;
import org.cip4.jdflib.datatypes.JDFMatrix;
import org.cip4.jdflib.datatypes.JDFRectangle;
import org.cip4.jdflib.datatypes.JDFTransferFunction;
import org.cip4.jdflib.datatypes.VJDFAttributeMap;
import org.cip4.jdflib.extensions.AuditResourceHelper;
import org.cip4.jdflib.extensions.ResourceHelper;
import org.cip4.jdflib.extensions.SetHelper;
import org.cip4.jdflib.extensions.XJDFConstants;
import org.cip4.jdflib.extensions.XJDFHelper;
import org.cip4.jdflib.extensions.XJMFHelper;
import org.cip4.jdflib.node.JDFNode;
import org.cip4.jdflib.resource.JDFColorMeasurementConditions;
import org.cip4.jdflib.resource.JDFMarkObject;
import org.cip4.jdflib.resource.JDFPatch;
import org.cip4.jdflib.resource.process.JDFColorControlStrip;
import org.cip4.jdflib.resource.process.JDFFileSpec;
import org.cip4.jdflib.resource.process.JDFLayout;
import org.cip4.jdflib.resource.process.JDFPreview;
import org.cip4.jdflib.resource.process.JDFQualityControlParams;
import org.cip4.jdflib.resource.process.JDFQualityControlResult;
import org.cip4.jdflib.util.JDFDate;
import org.cip4.jdflib.util.UrlUtil;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**
 *
 * @author rainer prosi
 *
 */
class XJDFQCExampleTest extends ExampleTest
{

	/**
	 * @see org.cip4.jdflib.JDFTestCaseBase#setUp()
	 */
	@Override
	@BeforeEach
	public void setUp() throws Exception
	{
		super.setUp();
		JDFAudit.setStaticAgentName(null);
		JDFAudit.setStaticAgentVersion(null);
		KElement.setLongID(false);
	}

	/**
	*
	*
	*/
	@Test
	public final void testColorQualityControlSpectrum()
	{
		final XJDFHelper h = new XJDFHelper(EnumVersion.Version_2_1, "qc1");
		h.addType(JDFNode.EnumType.ConventionalPrinting);
		h.addType(JDFNode.EnumType.QualityControl);
		final SetHelper sh = h.appendResourceSet(ElementName.QUALITYCONTROLPARAMS, EnumUsage.Input);
		final VJDFAttributeMap vMap = new VJDFAttributeMap();
		vMap.extendMap(AttributeName.SEPARATION, JDFConstants.SEPARATIONS_CMYK);
		final ResourceHelper rh = sh.getCreateVPartition(vMap, true);
		final JDFQualityControlParams qpr = (JDFQualityControlParams) rh.getResource();
		qpr.setAttribute(AttributeName.QUALITYCONTROLMETHODS, "ColorSpectrophotometry Colorimetry");
		final ResourceHelper resLO = h.getCreateSet(ElementName.LAYOUT, EnumUsage.Input).getCreatePartition(AttributeName.SHEETNAME, "S1", true);
		resLO.ensurePart(AttributeName.SIDE, "Front");
		final JDFLayout lo = (JDFLayout) resLO.getResource();
		final KElement po = lo.appendElement(XJDFConstants.PlacedObject);
		po.setAttribute(AttributeName.CTM, JDFMatrix.getUnitMatrix().shift(30, 40).getString(1));
		final JDFMarkObject mo = (JDFMarkObject) po.appendElement(ElementName.MARKOBJECT);
		final JDFColorControlStrip ccs = mo.appendColorControlStrip();
		final JDFColorMeasurementConditions cmc = (JDFColorMeasurementConditions) ccs.appendElement(ElementName.COLORMEASUREMENTCONDITIONS);
		ccs.setStripType("xxx");
		for (int i = 0; i < 10; i++)
		{
			final JDFPatch patch = ccs.appendPatch();
			patch.setAttribute(XJDFConstants.ExternalID, "Patch_" + i);
			patch.setAttribute(AttributeName.CENTER, "50 " + (i * 100));
			patch.setAttribute(AttributeName.SIZE, "30 30");
			patch.setAttribute(AttributeName.PATCHUSAGE, "Color");
			patch.setSpectrum(JDFTransferFunction.createTransferFunction("400 0 450 0.5 500 1.0 550 0.8 600 0.3 650 0.2 700 0"));

		}
		h.cleanUp();
		writeTest(h.getRoot(), "../QualityControlColorSpectrum.xjdf", true, null);

	}

	/**
	*
	*
	*/
	@Test
	public final void testColorQualityControl()
	{
		final XJDFHelper h = new XJDFHelper(EnumVersion.Version_2_1, "qc1");
		h.addType(JDFNode.EnumType.ConventionalPrinting);
		h.addType(JDFNode.EnumType.QualityControl);
		final SetHelper sh = h.appendResourceSet(ElementName.QUALITYCONTROLPARAMS, EnumUsage.Input);
		final VJDFAttributeMap vMap = new VJDFAttributeMap();
		vMap.extendMap(AttributeName.SEPARATION, JDFConstants.SEPARATIONS_CMYK);
		final ResourceHelper rh = sh.getCreateVPartition(vMap, true);
		final JDFQualityControlParams qpr = (JDFQualityControlParams) rh.getResource();
		qpr.setAttribute(AttributeName.QUALITYCONTROLMETHODS, "ColorSpectrophotometry Colorimetry");
		final ResourceHelper resLO = h.getCreateSet(ElementName.LAYOUT, EnumUsage.Input).getCreatePartition(AttributeName.SHEETNAME, "S1", true);
		resLO.ensurePart(AttributeName.SIDE, "Front");
		final JDFLayout lo = (JDFLayout) resLO.getResource();
		final KElement po = lo.appendElement(XJDFConstants.PlacedObject);
		po.setAttribute(AttributeName.CTM, JDFMatrix.getUnitMatrix().shift(30, 40).getString(1));
		final JDFMarkObject mo = (JDFMarkObject) po.appendElement(ElementName.MARKOBJECT);
		final JDFColorControlStrip ccs = mo.appendColorControlStrip();
		final JDFColorMeasurementConditions cmc = (JDFColorMeasurementConditions) ccs.appendElement(ElementName.COLORMEASUREMENTCONDITIONS);
		ccs.setStripType("xxx");
		for (int i = 0; i < 10; i++)
		{
			final KElement patch = ccs.appendElement(XJMFQCExampleTest.PATCH);
			patch.setAttribute(XJDFConstants.ExternalID, "Patch_" + i);
			patch.setAttribute(AttributeName.CENTER, "50 " + (i * 100));
			patch.setAttribute(AttributeName.SIZE, "30 30");
			patch.setAttribute(AttributeName.PATCHUSAGE, "Color");

		}
		h.cleanUp();
		writeTest(h.getRoot(), "../QualityControlColor.xjdf", true, null);

	}

	/**
	*
	*
	*/
	@Test
	public final void testInspectionQualityControlParams()
	{
		final XJDFHelper h = new XJDFHelper(EnumVersion.Version_2_1, "qcinspection");
		h.addType(JDFNode.EnumType.ConventionalPrinting);
		h.addType(JDFNode.EnumType.QualityControl);
		final SetHelper sh = h.appendResourceSet(ElementName.QUALITYCONTROLPARAMS, EnumUsage.Input);
		final ResourceHelper rh = sh.getCreatePartition(0, true);
		final JDFQualityControlParams qpr = (JDFQualityControlParams) rh.getResource();
		qpr.setAttribute(AttributeName.QUALITYCONTROLMETHODS, "Inspection");

		final SetHelper shPV = h.appendResourceSet(ElementName.PREVIEW, EnumUsage.Input);
		final ResourceHelper rhPV = shPV.getCreatePartition(0, true);
		final JDFPreview pv = (JDFPreview) rhPV.getResource();
		pv.setFileSpecURL("File://foo/images/myImage.png");

		writeTest(h.getRoot(), "../QualityControlInspect.xjdf", true, null);

	}

	/**
	*
	*
	*/
	@Test
	public final void testInspectionQualityControlResult()
	{
		final XJDFHelper h = new XJDFHelper(EnumVersion.Version_2_1, "qcinspection");
		h.addType(JDFNode.EnumType.ConventionalPrinting);
		h.addType(JDFNode.EnumType.QualityControl);
		final SetHelper sh = h.appendResourceSet(ElementName.QUALITYCONTROLPARAMS, EnumUsage.Input);
		final ResourceHelper rh = sh.getCreatePartition(0, true);
		final JDFQualityControlParams qpr = (JDFQualityControlParams) rh.getResource();
		qpr.setAttribute(AttributeName.QUALITYCONTROLMETHODS, "Inspection");

		final SetHelper shPV = h.appendResourceSet(ElementName.PREVIEW, EnumUsage.Input);
		final ResourceHelper rhPV = shPV.getCreatePartition(0, true);
		final JDFPreview pv = (JDFPreview) rhPV.getResource();
		pv.setFileSpecURL("File://foo/images/myImage.png");

		final SetHelper shRes = h.appendResourceSet(ElementName.QUALITYCONTROLRESULT, EnumUsage.Output);
		for (int i = 0; i < 3; i++)
		{
			final ResourceHelper rhRes = shRes.getCreatePartition(ElementName.QUALITYMEASUREMENT, "M" + i, true);
			rhRes.ensurePart(AttributeName.SHEETNAME, "S1");
			final JDFQualityControlResult qcr = (JDFQualityControlResult) rhRes.getResource();
			qcr.setAttribute(AttributeName.START, new JDFDate(), null);
			qcr.setAttribute(AttributeName.MEASUREMENTS, 1, null);
			qcr.setAttribute(AttributeName.MEASUREMENTUSAGE, "Standard", null);
			qcr.setAttribute(AttributeName.SAMPLE, (i * 100) + " " + (i * 100), null);
			final KElement inspection = qcr.appendElement(ElementName.INSPECTION);
			final JDFFileSpec fs = (JDFFileSpec) inspection.appendElement(ElementName.FILESPEC);
			fs.setURL("http://imagehost/getImage.php?Image=" + i);
			if (i == 1)
			{
				final KElement defect = inspection.appendElement(ElementName.DEFECT);
				defect.setAttribute(AttributeName.DEFECTTYPE, "ImageDefect");
				defect.setAttribute(AttributeName.DEFECTTYPEDETAILS, "ImageMismatch");
				defect.setAttribute(AttributeName.SIZE, 200, null);
				defect.setAttribute(AttributeName.SEVERITY, 20, null);
				final JDFRectangle box = new JDFRectangle(1000, 4000, 1008, 4050);
				defect.setAttribute(AttributeName.BOX, box.getString(0));
				fs.setURL("http://imagehost/getImageDetails.php?Image=" + i + "&Box=" + UrlUtil.escape(box.getString(0), false, false));
			}
		}
		h.getAuditPool().getCreateMessageResourceHelper(shRes).copySet(shRes, true);
		h.cleanUp();
		writeTest(h.getRoot(), "../QualityControlInspectRes.xjdf", true, null);

	}

	/**
	*
	*
	*/
	@Test
	public final void testInspectionQualityControlResultXJMF()
	{
		final XJDFHelper h = new XJDFHelper(EnumVersion.Version_2_1, "qcinspection");
		h.addType(JDFNode.EnumType.ConventionalPrinting);
		h.addType(JDFNode.EnumType.QualityControl);
		final SetHelper sh = h.appendResourceSet(ElementName.QUALITYCONTROLPARAMS, EnumUsage.Input);
		final ResourceHelper rh = sh.getCreatePartition(0, true);
		final JDFQualityControlParams qpr = (JDFQualityControlParams) rh.getResource();
		qpr.setAttribute(AttributeName.QUALITYCONTROLMETHODS, "Inspection");

		final SetHelper shPV = h.appendResourceSet(ElementName.PREVIEW, EnumUsage.Input);
		final ResourceHelper rhPV = shPV.getCreatePartition(0, true);
		final JDFPreview pv = (JDFPreview) rhPV.getResource();
		pv.setFileSpecURL("File://foo/images/myImage.png");

		final SetHelper shRes = h.appendResourceSet(ElementName.QUALITYCONTROLRESULT, EnumUsage.Output);
		for (int i = 1; i < 2; i++)
		{
			final ResourceHelper rhRes = shRes.getCreatePartition(ElementName.QUALITYMEASUREMENT, "M" + i, true);
			rhRes.ensurePart(AttributeName.SHEETNAME, "S1");
			final JDFQualityControlResult qcr = (JDFQualityControlResult) rhRes.getResource();
			qcr.setAttribute(AttributeName.START, new JDFDate(), null);
			qcr.setAttribute(AttributeName.MEASUREMENTS, 1, null);
			qcr.setAttribute(AttributeName.MEASUREMENTUSAGE, "Standard", null);
			qcr.setAttribute(AttributeName.SAMPLE, (i * 100) + " " + (i * 100), null);
			final KElement inspection = qcr.appendElement(ElementName.INSPECTION);
			final JDFFileSpec fs = (JDFFileSpec) inspection.appendElement(ElementName.FILESPEC);
			fs.setURL("http://imagehost/getImage.php?Image=" + i);
			if (i == 1)
			{
				final KElement defect = inspection.appendElement(ElementName.DEFECT);
				defect.setAttribute(AttributeName.DEFECTTYPE, "ImageDefect");
				defect.setAttribute(AttributeName.DEFECTTYPEDETAILS, "ImageMismatch");
				defect.setAttribute(AttributeName.SIZE, 200, null);
				defect.setAttribute(AttributeName.SEVERITY, 20, null);
				final JDFRectangle box = new JDFRectangle(1000, 4000, 1008, 4050);
				defect.setAttribute(AttributeName.BOX, box.getString(0));
				fs.setURL("http://imagehost/getImageDetails.php?Image=" + i + "&Box=" + UrlUtil.escape(box.getString(0), false, false));
			}
		}
		final SetHelper sn = h.getAuditPool().getCreateMessageResourceHelper(shRes).copySet(shRes, true);
		final AuditResourceHelper mrh = (AuditResourceHelper) h.getAuditPool().getMessageResourceHelper(sn);
		final XJMFHelper xjmf = mrh.makeXJMFSignal();
		xjmf.cleanUp();
		writeTest(xjmf.getRoot(), "../QualityControlInspectRes.xjmf", true, null);

	}

}
