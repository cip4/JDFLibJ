/**
 * The CIP4 Software License, Version 1.0
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

import org.cip4.jdflib.JDFTestCaseBase;
import org.cip4.jdflib.core.AttributeName;
import org.cip4.jdflib.core.ElementName;
import org.cip4.jdflib.core.JDFAudit;
import org.cip4.jdflib.core.JDFConstants;
import org.cip4.jdflib.core.JDFElement.EnumVersion;
import org.cip4.jdflib.core.JDFResourceLink.EnumUsage;
import org.cip4.jdflib.core.KElement;
import org.cip4.jdflib.datatypes.JDFMatrix;
import org.cip4.jdflib.datatypes.VJDFAttributeMap;
import org.cip4.jdflib.extensions.ResourceHelper;
import org.cip4.jdflib.extensions.SetHelper;
import org.cip4.jdflib.extensions.XJDFConstants;
import org.cip4.jdflib.extensions.XJDFHelper;
import org.cip4.jdflib.resource.JDFColorMeasurementConditions;
import org.cip4.jdflib.resource.JDFMarkObject;
import org.cip4.jdflib.resource.process.JDFColorControlStrip;
import org.cip4.jdflib.resource.process.JDFLayout;
import org.cip4.jdflib.resource.process.JDFQualityControlParams;
import org.junit.Test;

/**
 *
 * @author rainer prosi
 *
 */
public class XJDFQCExampleTest extends JDFTestCaseBase
{

	/**
	 * @see org.cip4.jdflib.JDFTestCaseBase#setUp()
	 */
	@Override
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
	public final void testColorQualityControl()
	{
		final XJDFHelper h = new XJDFHelper(EnumVersion.Version_2_1, "qc1");
		h.addType(org.cip4.jdflib.node.JDFNode.EnumType.ConventionalPrinting);
		h.addType(org.cip4.jdflib.node.JDFNode.EnumType.QualityControl);
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
			patch.setAttribute(AttributeName.DIAMETER, "30");

		}
		h.cleanUp();
		writeTest(h.getRoot(), "../QualityControlColor.xjdf", true, null);

	}

	/**
	*
	*
	*/
	@Test
	public final void testInspectionQualityControl()
	{
		final XJDFHelper h = new XJDFHelper(EnumVersion.Version_2_1, "qcinspection");
		h.addType(org.cip4.jdflib.node.JDFNode.EnumType.ConventionalPrinting);
		h.addType(org.cip4.jdflib.node.JDFNode.EnumType.QualityControl);
		final SetHelper sh = h.appendResourceSet(ElementName.QUALITYCONTROLPARAMS, EnumUsage.Input);
		final ResourceHelper rh = sh.getCreatePartition(0, true);
		final JDFQualityControlParams qpr = (JDFQualityControlParams) rh.getResource();
		qpr.setAttribute(AttributeName.QUALITYCONTROLMETHODS, "Inspection");
		writeTest(h.getRoot(), "../QualityControlInspect.xjdf", true, null);

	}

}
