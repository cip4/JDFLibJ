/**
 * The CIP4 Software License, Version 1.0
 *
 * Copyright (c) 2001-2017 The International Cooperation for the Integration of
 * Processes in  Prepress, Press and Postpress (CIP4).  All rights
 * reserved.
 *
 * Redistribution and use in source and binary forms, with or without
 * modification, are permitted provided that the following conditions
 * are met:
 *
 * 1. Redistributions of source code must retain the above copyright
 *    notice, this list of conditions and the following disclaimer.
 *
 * 2. Redistributions in binary form must reproduce the above copyright
 *    notice, this list of conditions and the following disclaimer in
 *    the documentation and/or other materials provided with the
 *    distribution.
 *
 * 3. The end-user documentation included with the redistribution,
 *    if any, must include the following acknowledgment:
 *       "This product includes software developed by the
 *        The International Cooperation for the Integration of
 *        Processes in  Prepress, Press and Postpress (www.cip4.org)"
 *    Alternately, this acknowledgment may appear in the software itself,
 *    if and wherever such third-party acknowledgments normally appear.
 *
 * 4. The names "CIP4" and "The International Cooperation for the Integration of
 *    Processes in  Prepress, Press and Postpress" must
 *    not be used to endorse or promote products derived from this
 *    software without prior written permission. For written
 *    permission, please contact info@cip4.org.
 *
 * 5. Products derived from this software may not be called "CIP4",
 *    nor may "CIP4" appear in their name, without prior written
 *    permission of the CIP4 organization
 *
 * Usage of this software in commercial products is subject to restrictions. For
 * details please consult info@cip4.org.
 *
 * THIS SOFTWARE IS PROVIDED ``AS IS'' AND ANY EXPRESSED OR IMPLIED
 * WARRANTIES, INCLUDING, BUT NOT LIMITED TO, THE IMPLIED WARRANTIES
 * OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE ARE
 * DISCLAIMED.  IN NO EVENT SHALL THE INTERNATIONAL COOPERATION FOR
 * THE INTEGRATION OF PROCESSES IN PREPRESS, PRESS AND POSTPRESS OR
 * ITS CONTRIBUTORS BE LIABLE FOR ANY DIRECT, INDIRECT, INCIDENTAL,
 * SPECIAL, EXEMPLARY, OR CONSEQUENTIAL DAMAGES (INCLUDING, BUT NOT
 * LIMITED TO, PROCUREMENT OF SUBSTITUTE GOODS OR SERVICES; LOSS OF
 * USE, DATA, OR PROFITS; OR BUSINESS INTERRUPTION) HOWEVER CAUSED AND
 * ON ANY THEORY OF LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY,
 * OR TORT (INCLUDING NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT
 * OF THE USE OF THIS SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY OF
 * SUCH DAMAGE.
 * ====================================================================
 *
 * This software consists of voluntary contributions made by many
 * individuals on behalf of the The International Cooperation for the Integration
 * of Processes in Prepress, Press and Postpress and was
 * originally based on software
 * copyright (c) 1999-2001, Heidelberger Druckmaschinen AG
 * copyright (c) 1999-2001, Agfa-Gevaert N.V.
 *
 * For more information on The International Cooperation for the
 * Integration of Processes in  Prepress, Press and Postpress , please see
 * <http://www.cip4.org/>.
 *
 *
 */
package org.cip4.jdflib.extensions.examples;

import org.cip4.jdflib.JDFTestCaseBase;
import org.cip4.jdflib.auto.JDFAutoMedia.EnumFluteDirection;
import org.cip4.jdflib.auto.JDFAutoMedia.EnumMediaType;
import org.cip4.jdflib.auto.JDFAutoMedia.EnumMediaUnit;
import org.cip4.jdflib.core.AttributeName;
import org.cip4.jdflib.core.ElementName;
import org.cip4.jdflib.core.JDFConstants;
import org.cip4.jdflib.core.JDFResourceLink.EnumUsage;
import org.cip4.jdflib.core.KElement;
import org.cip4.jdflib.datatypes.JDFXYPair;
import org.cip4.jdflib.extensions.ResourceHelper;
import org.cip4.jdflib.extensions.SetHelper;
import org.cip4.jdflib.extensions.XJDFHelper;
import org.cip4.jdflib.resource.process.JDFMedia;
import org.cip4.jdflib.resource.process.JDFMediaLayers;
import org.cip4.jdflib.resource.process.postpress.JDFGlue;
import org.junit.Before;
import org.junit.Test;

/**
 *
 * @author rainer prosi
 *
 */
public class XJDFMediaTest extends JDFTestCaseBase
{
	/**
	 *
	 */
	@Test
	public void testCorrugated()
	{
		XJDFHelper xjdfHelper = new XJDFHelper("Converting", "Corrugated", null);
		xjdfHelper.setTypes(JDFConstants.CONVENTIONALPRINTING);
		SetHelper shMedia = xjdfHelper.getCreateResourceSet(ElementName.MEDIA, EnumUsage.Input);
		ResourceHelper rh = shMedia.appendPartition(null, true);
		JDFMedia m = (JDFMedia) rh.getResource();
		m.setMediaType(EnumMediaType.CorrugatedBoard);
		m.setDimensionCM(new JDFXYPair(100, 70));
		m.setThickness(2382);
		m.setInsideLoss(1000);
		m.setOutsideGain(1380);
		m.setMediaTypeDetails("SingleWall");
		JDFMediaLayers ml = m.appendMediaLayers();
		JDFMedia m2 = ml.appendMedia();
		m2.setMediaType(EnumMediaType.Paper);
		m2.setWeight(190);
		m2 = ml.appendMedia();
		m2.setMediaType(EnumMediaType.Paper);
		m2.setWeight(180);
		m2.setFlute("B");
		m2.setFluteDirection(EnumFluteDirection.XDirection);
		m2.setMediaTypeDetails("Flute");
		m2 = ml.appendMedia();
		m2.setMediaType(EnumMediaType.Paper);
		m2.setWeight(180);

		writeTest(xjdfHelper, "resources/MediaCorrugated.xjdf");
	}

	/**
	 *
	 */
	@Test
	public void testFlexoPlate()
	{
		XJDFHelper xjdfHelper = new XJDFHelper("Converting", "Corrugated", null);
		xjdfHelper.setTypes(JDFConstants.CONVENTIONALPRINTING);
		SetHelper shMedia = xjdfHelper.getCreateResourceSet(ElementName.MEDIA, EnumUsage.Input);
		ResourceHelper rh = shMedia.appendPartition(null, true);
		JDFMedia m = (JDFMedia) rh.getResource();
		m.setMediaType(EnumMediaType.CorrugatedBoard);
		m.setDimensionCM(new JDFXYPair(100, 70));
		m.setThickness(2382);
		m.setInsideLoss(1000);
		m.setOutsideGain(1380);
		m.setMediaTypeDetails("SingleWall");
		JDFMediaLayers ml = m.appendMediaLayers();
		JDFMedia m2 = ml.appendMedia();
		m2.setMediaType(EnumMediaType.Paper);
		m2.setWeight(190);
		m2 = ml.appendMedia();
		m2.setMediaType(EnumMediaType.Paper);
		m2.setWeight(180);
		m2.setFlute("B");
		m2.setFluteDirection(EnumFluteDirection.XDirection);
		m2.setMediaTypeDetails("Flute");
		m2 = ml.appendMedia();
		m2.setMediaType(EnumMediaType.Paper);
		m2.setWeight(180);

		writeTest(xjdfHelper, "resources/MediaCorrugated.xjdf");
	}

	/**
	 *
	 */
	@Test
	public void testAdhesive()
	{
		XJDFHelper xjdfHelper = new XJDFHelper("Converting", "Corrugated", null);
		xjdfHelper.setTypes(JDFConstants.CONVENTIONALPRINTING);
		SetHelper shMedia = xjdfHelper.getCreateResourceSet(ElementName.MEDIA, EnumUsage.Input);
		ResourceHelper rh = shMedia.appendPartition(null, true);
		JDFMedia m = (JDFMedia) rh.getResource();
		m.setMediaType(EnumMediaType.SelfAdhesive);
		m.setDimensionCM(new JDFXYPair(42, 0));
		m.setMediaUnit(EnumMediaUnit.Roll);
		m.setThickness(900);
		JDFMediaLayers ml = m.appendMediaLayers();
		JDFMedia m2 = ml.appendMedia();
		m2.setMediaType(EnumMediaType.Paper);
		m2.setWeight(90);
		JDFGlue g = (JDFGlue) ml.appendElement(ElementName.GLUE);
		g.setAttribute(AttributeName.AREAGLUE, "" + true);
		g.setAttribute(AttributeName.GLUETYPE, "Removable");
		m2 = ml.appendMedia();
		m2.setMediaType(EnumMediaType.Paper);
		m2.setWeight(60);

		writeTest(xjdfHelper, "resources/MediaSelfAdhesive.xjdf");
	}

	/**
	 * @see org.cip4.jdflib.JDFTestCaseBase#setUp()
	 */
	@Before
	@Override
	protected void setUp() throws Exception
	{
		KElement.setLongID(false);
	}
}
