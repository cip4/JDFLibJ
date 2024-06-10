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

import java.io.File;

import org.cip4.jdflib.JDFTestCaseBase;
import org.cip4.jdflib.auto.JDFAutoAssembly.EnumOrder;
import org.cip4.jdflib.auto.JDFAutoDigitalPrintingParams.EnumSides;
import org.cip4.jdflib.auto.JDFAutoStitchingParams.EnumStitchType;
import org.cip4.jdflib.auto.JDFAutoStrippingParams.EnumWorkStyle;
import org.cip4.jdflib.core.AttributeName;
import org.cip4.jdflib.core.ElementName;
import org.cip4.jdflib.core.JDFConstants;
import org.cip4.jdflib.core.JDFElement.EnumValidationLevel;
import org.cip4.jdflib.core.JDFResourceLink.EnumUsage;
import org.cip4.jdflib.core.KElement;
import org.cip4.jdflib.extensions.ResourceHelper;
import org.cip4.jdflib.extensions.SetHelper;
import org.cip4.jdflib.extensions.XJDFConstants;
import org.cip4.jdflib.extensions.XJDFHelper;
import org.cip4.jdflib.jmf.JMFBuilderFactory;
import org.cip4.jdflib.node.JDFNode;
import org.cip4.jdflib.node.JDFNode.EnumType;
import org.cip4.jdflib.resource.process.JDFAssembly;
import org.cip4.jdflib.resource.process.JDFBinderySignature;
import org.cip4.jdflib.resource.process.JDFComponent;
import org.cip4.jdflib.resource.process.JDFDigitalPrintingParams;
import org.cip4.jdflib.resource.process.JDFLayout;
import org.cip4.jdflib.resource.process.JDFRunList;
import org.cip4.jdflib.resource.process.postpress.JDFStitchingParams;
import org.cip4.jdflib.util.FileUtil;
import org.cip4.jdflib.util.UrlUtil;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**
 *
 * @author rainer prosi
 *
 */
class XJDFIDPTest extends JDFTestCaseBase
{
	/**
	 *
	 */
	@Test
	void testIDPSimplex()
	{
		final XJDFHelper xjdfHelper = new XJDFHelper(ElementName.LAYOUT, "Simplex", null);
		xjdfHelper.addType(EnumType.Stripping);
		xjdfHelper.addType(EnumType.Imposition);
		xjdfHelper.addType(EnumType.DigitalPrinting);

		final JDFLayout lo = (JDFLayout) xjdfHelper.getCreateResource(ElementName.LAYOUT, EnumUsage.Input, null);
		lo.setAttribute(AttributeName.WORKSTYLE, EnumWorkStyle.Simplex.getName());
		lo.setAutomated(true);
		lo.appendElement(ElementName.POSITION);

		final JDFRunList ruLi = (JDFRunList) xjdfHelper.getCreateResource(ElementName.RUNLIST, EnumUsage.Input, null);
		ruLi.setFileSpecURL("http://test.pdf");

		final JDFDigitalPrintingParams dpp = (JDFDigitalPrintingParams) xjdfHelper.getCreateResource(ElementName.DIGITALPRINTINGPARAMS, EnumUsage.Input, null);
		dpp.setSides(EnumSides.OneSidedFront);

		final JDFComponent comp = (JDFComponent) xjdfHelper.getCreateResource(ElementName.COMPONENT, EnumUsage.Output, null);
		ResourceHelper.getHelper(comp).setAmount(100, null, true);

		writeRoundTripX(xjdfHelper, "processes/IDPSimplex", EnumValidationLevel.Complete);
	}

	/**
	 *
	 */
	@Test
	void testIDPBooklet()
	{
		final XJDFHelper xjdfHelper = new XJDFHelper(ElementName.LAYOUT, "Booklet", null);
		xjdfHelper.addType(EnumType.Stripping);
		xjdfHelper.addType(EnumType.Imposition);
		xjdfHelper.addType(EnumType.DigitalPrinting);
		xjdfHelper.addType(EnumType.Collecting);
		xjdfHelper.addType(EnumType.Stitching);

		final JDFLayout lo = (JDFLayout) xjdfHelper.getCreateResource(ElementName.LAYOUT, EnumUsage.Input, null);
		lo.setAttribute(AttributeName.WORKSTYLE, EnumWorkStyle.Perfecting.getName());
		lo.setAutomated(true);
		lo.appendElement(ElementName.POSITION);

		final JDFBinderySignature bs = (JDFBinderySignature) xjdfHelper.getCreateResource(ElementName.BINDERYSIGNATURE, EnumUsage.Input, null);
		bs.setFoldCatalog("F4-1");

		final SetHelper shAss = xjdfHelper.getCreateSet(ElementName.ASSEMBLY, EnumUsage.Input);
		final ResourceHelper rhAss = shAss.appendPartition(null, true);
		final JDFAssembly ass = (JDFAssembly) rhAss.getResource();
		ass.setOrder(EnumOrder.Collecting);

		final JDFDigitalPrintingParams dpp = (JDFDigitalPrintingParams) xjdfHelper.getCreateResource(ElementName.DIGITALPRINTINGPARAMS, EnumUsage.Input, null);
		dpp.setSides(EnumSides.TwoSided);

		final JDFStitchingParams stp = (JDFStitchingParams) xjdfHelper.getCreateResource(ElementName.STITCHINGPARAMS, EnumUsage.Input, null);
		stp.setStitchType(EnumStitchType.Saddle);
		stp.setNumberOfStitches(3);

		final JDFComponent comp = (JDFComponent) xjdfHelper.getCreateResource(ElementName.COMPONENT, EnumUsage.Output, null);
		ResourceHelper.getHelper(comp).setAmount(42, null, true);

		writeRoundTripX(xjdfHelper, "processes/IDPBooklet", EnumValidationLevel.Complete);
	}

	/**
	 *
	 */
	@Test
	void testIDPSamples()
	{
		final File[] jdfs = FileUtil.listFilesWithExtension(new File(sm_dirTestData + "idpxjdf"), "jdf");
		for (final File jdf : jdfs)
		{
			if (jdf.getName().startsWith("Step___nope"))
				continue;
			log.info("Processing " + jdf);
			final JDFNode root = JDFNode.parseFile(jdf);
			if (root.getAllTypes().contains(JDFConstants.TYPE_SPINEPREPARATION))
			{
				root.getCreateResource(ElementName.SPINEPREPARATIONPARAMS, EnumUsage.Input, 0).setDescriptiveName("Spine preparation details");
			}
			if (root.getAllTypes().contains(JDFConstants.TYPE_GATHERING))
			{
				root.getCreateResource(ElementName.GATHERINGPARAMS, EnumUsage.Input, 0).setDescriptiveName("gathering details");
			}
			writeRoundTrip(root, "idpvjdf/" + UrlUtil.newExtension(jdf.getName(), null));
		}

	}

	/**
	 * @see JDFTestCaseBase#setUp()
	 */
	@Override
	@BeforeEach
	public void setUp() throws Exception
	{
		JMFBuilderFactory.getJMFBuilder(XJDFConstants.XJMF).setAgentName(null);
		JMFBuilderFactory.getJMFBuilder(XJDFConstants.XJMF).setAgentVersion(null);
		JMFBuilderFactory.getJMFBuilder(XJDFConstants.XJMF).setSenderID(null);
		KElement.setLongID(false);

		super.setUp();
	}

}
