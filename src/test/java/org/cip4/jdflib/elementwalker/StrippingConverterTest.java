/*
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
package org.cip4.jdflib.elementwalker;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.List;

import org.cip4.jdflib.JDFTestCaseBase;

/**
 * The CIP4 Software License, Version 1.0
 *
 * Copyright (c) 2001-2022 The International Cooperation for the Integration of Processes in Prepress, Press and Postpress (CIP4). All rights reserved.
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

import org.cip4.jdflib.auto.JDFAutoFitPolicy.EnumSizePolicy;
import org.cip4.jdflib.auto.JDFAutoLayoutPreparationParams.EnumBindingEdge;
import org.cip4.jdflib.auto.JDFAutoLayoutPreparationParams.EnumSides;
import org.cip4.jdflib.auto.JDFAutoPosition.EnumOrientation;
import org.cip4.jdflib.core.ElementName;
import org.cip4.jdflib.core.JDFResourceLink.EnumUsage;
import org.cip4.jdflib.node.JDFNode;
import org.cip4.jdflib.node.JDFNode.EnumType;
import org.cip4.jdflib.resource.JDFLayoutPreparationParams;
import org.cip4.jdflib.resource.JDFStrippingParams;
import org.cip4.jdflib.resource.process.JDFAssembly;
import org.cip4.jdflib.resource.process.JDFBinderySignature;
import org.cip4.jdflib.resource.process.JDFPosition;
import org.cip4.jdflib.resource.process.JDFStripCellParams;
import org.junit.jupiter.api.Test;

class StrippingConverterTest extends JDFTestCaseBase
{

	@Test
	void testBindingEdge()
	{
		final JDFLayoutPreparationParams lpp = createlpp();
		lpp.setBindingEdge(EnumBindingEdge.Bottom);
		final JDFNode parentJDF = lpp.getParentJDF();
		final StrippingConverter strippingConverter = new StrippingConverter(lpp, parentJDF);
		strippingConverter.convert();
		final JDFBinderySignature bs = strippingConverter.getBinderySignature();
		assertEquals(EnumBindingEdge.Bottom.getName(), bs.getBindingEdge().getName());
		final JDFAssembly ass = strippingConverter.getAssembly();
		assertEquals(EnumBindingEdge.Bottom.getName(), ass.getBindingSide().getName());
	}

	@Test
	void testSidesBack()
	{
		for (EnumSides s : new EnumSides[] { EnumSides.OneSidedBackFlipX, EnumSides.OneSidedBackFlipY })
		{
			final JDFLayoutPreparationParams lpp = createlpp();
			lpp.setSides(s);
			final JDFNode parentJDF = lpp.getParentJDF();
			final StrippingConverter strippingConverter = new StrippingConverter(lpp, parentJDF);
			strippingConverter.convert();
			final JDFStrippingParams sp = strippingConverter.getStrippingParams();
			JDFStripCellParams scp = sp.getStripCellParams();
			assertEquals(org.cip4.jdflib.auto.JDFAutoStripCellParams.EnumSides.OneSidedBack, scp.getSides());
		}
	}

	@Test
	void testLPPFitPolicy()
	{
		final JDFLayoutPreparationParams lpp = createlpp();
		lpp.appendFitPolicy().setSizePolicy(EnumSizePolicy.ReduceToFit);
		final JDFNode parentJDF = lpp.getParentJDF();
		final StrippingConverter strippingConverter = new StrippingConverter(lpp, parentJDF);
		strippingConverter.convert();
		final JDFBinderySignature bs = strippingConverter.getBinderySignature();
		final JDFStrippingParams sp = strippingConverter.getStrippingParams();
		assertNotNull(sp.getElement(ElementName.FITPOLICY));
	}

	@Test
	void testAutomated()
	{
		final JDFLayoutPreparationParams lpp = createlpp();
		lpp.setBindingEdge(EnumBindingEdge.Bottom);
		final JDFNode parentJDF = lpp.getParentJDF();
		final StrippingConverter strippingConverter = new StrippingConverter(lpp, parentJDF);
		strippingConverter.convert();
		final JDFStrippingParams spp = (JDFStrippingParams) parentJDF.getResource(ElementName.STRIPPINGPARAMS, EnumUsage.Input, 0);
		assertTrue(spp.getAutomated());
	}

	/**
	 *
	 * @return
	 */
	JDFLayoutPreparationParams createlpp()
	{
		final JDFNode n = JDFNode.createRoot();
		n.setType(EnumType.LayoutPreparation);
		return (JDFLayoutPreparationParams) n.addResource(ElementName.LAYOUTPREPARATIONPARAMS, EnumUsage.Input);
	}

	/**
	 *
	 */
	@Test
	void testLayoutPrepMultiBS2()
	{
		final JDFNode n = JDFNode.parseFile(sm_dirTestData + "xjdf/DigitalPrintingMultiPDF_IDP-ICS-1.5.jdf");
		JDFLayoutPreparationParams lpp = (JDFLayoutPreparationParams) n.getResource(ElementName.LAYOUTPREPARATIONPARAMS, EnumUsage.Input, 0);
		final StrippingConverter strippingConverter = new StrippingConverter(lpp, n);
		strippingConverter.convert();

		n.write2File(sm_dirTestDataTemp + "lpp2.jdf");
		final JDFStrippingParams spp = (JDFStrippingParams) n.getResource(ElementName.STRIPPINGPARAMS, EnumUsage.Input, 0);

		List<JDFPosition> pos0 = spp.getChildArrayByClass_KElement(JDFPosition.class, false, 0);
		List<JDFPosition> poss = spp.getChildArrayByClass_KElement(JDFPosition.class, true, 0);
		poss.removeAll(pos0);
		assertEquals(3, poss.size());
		assertEquals(EnumOrientation.Rotate0, poss.get(0).getOrientation());
		assertEquals(EnumOrientation.Flip180, poss.get(2).getOrientation());

	}

}
