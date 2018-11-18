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
import org.cip4.jdflib.auto.JDFAutoConventionalPrintingParams.EnumWorkStyle;
import org.cip4.jdflib.auto.JDFAutoMedia.EnumMediaType;
import org.cip4.jdflib.core.AttributeName;
import org.cip4.jdflib.core.ElementName;
import org.cip4.jdflib.core.JDFConstants;
import org.cip4.jdflib.core.JDFElement.EnumValidationLevel;
import org.cip4.jdflib.core.JDFResourceLink.EnumUsage;
import org.cip4.jdflib.core.KElement;
import org.cip4.jdflib.core.VString;
import org.cip4.jdflib.datatypes.JDFAttributeMap;
import org.cip4.jdflib.datatypes.JDFXYPair;
import org.cip4.jdflib.extensions.ResourceHelper;
import org.cip4.jdflib.extensions.SetHelper;
import org.cip4.jdflib.extensions.XJDFHelper;
import org.cip4.jdflib.node.JDFNode.EnumType;
import org.cip4.jdflib.resource.process.JDFComponent;
import org.cip4.jdflib.resource.process.JDFConventionalPrintingParams;
import org.cip4.jdflib.resource.process.JDFMedia;
import org.junit.Test;

/**
 *
 * @author rainer prosi
 *
 */
public class XJDFConventionalPrintingTest extends JDFTestCaseBase
{

	/**
	 *
	 */
	@Test
	public void testSimpleCP()
	{
		final XJDFHelper xjdfHelper = new XJDFHelper("SimpleCP", null);
		xjdfHelper.setTypes(EnumType.ConventionalPrinting.getName());

		final SetHelper shSheet = xjdfHelper.getCreateSet(ElementName.COMPONENT, EnumUsage.Input);
		final ResourceHelper rhSheet = shSheet.getCreatePartition(0, true);
		final SetHelper shMedia = xjdfHelper.getCreateSet(ElementName.MEDIA, EnumUsage.Input);
		final ResourceHelper rhMedia = shMedia.getCreatePartition(0, true);
		final SetHelper shCPP = xjdfHelper.getCreateSet(ElementName.CONVENTIONALPRINTINGPARAMS, EnumUsage.Input);
		final SetHelper shout = xjdfHelper.getCreateSet(ElementName.COMPONENT, EnumUsage.Output);
		shout.getCreatePartition(new JDFAttributeMap(AttributeName.SHEETNAME, "s1"), false).setAmount(444, null, true);
		((JDFConventionalPrintingParams) (shCPP.getCreatePartition(0, true).getResource())).setWorkStyle(EnumWorkStyle.Perfecting);
		final JDFComponent comp = (JDFComponent) rhSheet.getResource();
		rhMedia.ensureReference(comp, null);
		final JDFMedia m = (JDFMedia) rhMedia.getCreateResource();
		m.setDimensionCM(70, 50);
		m.setMediaType(EnumMediaType.Paper);

		writeRoundTripX(xjdfHelper, "simpleConventional", EnumValidationLevel.Complete);
	}

	/**
	 *
	 */
	@Test
	public void testNarrowWeb()
	{
		final XJDFHelper xjdfHelper = new XJDFHelper("NarrowWebCP", null);
		xjdfHelper.addType(EnumType.Feeding.getName()).addType(EnumType.ConventionalPrinting.getName()).addType(EnumType.Winding.getName());
		xjdfHelper.setDescriptiveName("CustomerJob 123");

		final SetHelper shSheet = xjdfHelper.getCreateSet(ElementName.COMPONENT, EnumUsage.Input);
		final ResourceHelper rhSheet = shSheet.getCreatePartition(0, true);

		final SetHelper shMedia = xjdfHelper.getCreateSet(ElementName.MEDIA, EnumUsage.Input);
		final ResourceHelper rhMedia = shMedia.getCreatePartition(0, true);
		final JDFMedia m = (JDFMedia) rhMedia.getCreateResource();
		m.setDimensionCM(87, 0);
		m.setMediaType(EnumMediaType.Paper);
		m.setMediaTypeDetails("Cardboard");
		m.setWeight(260);
		m.setThickness(350);
		rhMedia.setExternalID("123456");
		rhMedia.setBrand("Invercote");

		final JDFComponent comp = (JDFComponent) rhSheet.getResource();
		rhMedia.ensureReference(comp, null);

		final SetHelper shink = xjdfHelper.getCreateSet(ElementName.INK, EnumUsage.Input);
		final VString v = (VString) JDFConstants.SEPARATIONS_CMYK.clone();
		v.add("Spot1");
		v.add("Spot2");
		v.add("Varnish1");
		v.add("Varnish2");
		int n = 0;
		for (final String sep : JDFConstants.SEPARATIONS_CMYK)
		{
			final ResourceHelper rhink = shink.getCreatePartition(new JDFAttributeMap(AttributeName.SHEETNAME, "s1"), false);
			rhink.ensurePart(AttributeName.SEPARATION, sep);
			rhink.setAmount(0.09 * (25700 * ++n), null, true);
		}

		final SetHelper shout = xjdfHelper.getCreateSet(ElementName.COMPONENT, EnumUsage.Output);
		final ResourceHelper rhout = shout.getCreatePartition(new JDFAttributeMap(AttributeName.SHEETNAME, "s1"), true);
		final JDFComponent cout = (JDFComponent) rhout.getResource();
		cout.setDimensions((JDFXYPair) new JDFXYPair(870, 857.92).scaleFromMM());

		rhout.setAmount(25700, null, true);

		writeRoundTripX(xjdfHelper, "NarrowWebConventional", EnumValidationLevel.Complete);
	}

	/**
	 * @see org.cip4.jdflib.JDFTestCaseBase#setUp()
	 */
	@Override
	public void setUp() throws Exception
	{
		super.setUp();
		KElement.setLongID(false);
	}
}
