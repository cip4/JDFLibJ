/**
 * The CIP4 Software License, Version 1.0
 *
 * Copyright (c) 2001-2025 The International Cooperation for the Integration of Processes in Prepress, Press and Postpress (CIP4). All rights reserved.
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
package org.cip4.jdflib.extensions.xjdfwalker.jdftoxjdf;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.cip4.jdflib.JDFTestCaseBase;
import org.cip4.jdflib.auto.JDFAutoMedia.EnumFluteDirection;
import org.cip4.jdflib.auto.JDFAutoMedia.EnumFrontCoatings;
import org.cip4.jdflib.auto.JDFAutoMedia.EnumGrainDirection;
import org.cip4.jdflib.auto.JDFAutoMedia.EnumHoleType;
import org.cip4.jdflib.auto.JDFAutoMedia.EnumISOPaperSubstrate;
import org.cip4.jdflib.auto.JDFAutoMedia.EnumMediaType;
import org.cip4.jdflib.core.ElementName;
import org.cip4.jdflib.core.JDFDoc;
import org.cip4.jdflib.core.KElement;
import org.cip4.jdflib.extensions.XJDFConstants;
import org.cip4.jdflib.extensions.XJDFHelper;
import org.cip4.jdflib.node.JDFNode;
import org.cip4.jdflib.resource.process.JDFMedia;
import org.junit.jupiter.api.Test;

class WalkMediaTest extends JDFTestCaseBase
{

	/**
	 *
	 */
	@Test
	void testGrade()
	{
		final JDFNode n = JDFNode.createRoot();
		final JDFMedia m = (JDFMedia) n.addResource(ElementName.MEDIA, null);
		m.setGrade(5);
		final KElement res = new JDFDoc("Resource").getRoot();
		final WalkMedia walkMedia = new WalkMedia();
		walkMedia.jdfToXJDF = new JDFToXJDF();
		walkMedia.walk(m, res);
		assertEquals(EnumISOPaperSubstrate.PS8, ((JDFMedia) res.getElement(ElementName.MEDIA)).getISOPaperSubstrate());
	}

	/**
	 *
	 */
	@Test
	void testCoating()
	{
		final JDFNode n = JDFNode.createRoot();
		final JDFMedia m = (JDFMedia) n.addResource(ElementName.MEDIA, null);
		m.setFrontCoatings(EnumFrontCoatings.Glossy);
		final KElement res = new JDFDoc("Resource").getRoot();
		final WalkMedia walkMedia = new WalkMedia();
		walkMedia.jdfToXJDF = new JDFToXJDF();
		walkMedia.walk(m, res);
		assertEquals("Gloss", res.getElement(ElementName.MEDIA).getAttribute(XJDFConstants.Coating));
	}

	/**
	*
	*/
	@Test
	void testMediaType()
	{
		final JDFNode n = JDFNode.createRoot();
		final JDFMedia m = (JDFMedia) n.addResource(ElementName.MEDIA, null);

		final KElement res = new JDFDoc("Resource").getRoot();
		final WalkMedia walkMedia = new WalkMedia();
		walkMedia.jdfToXJDF = new JDFToXJDF();
		walkMedia.walk(m, res);
		assertEquals(EnumMediaType.Other, ((JDFMedia) res.getElement(ElementName.MEDIA)).getMediaType());
	}

	/**
	*
	*/
	@Test
	void testHolePattern()
	{
		final JDFNode n = JDFNode.createRoot();
		final JDFMedia m = (JDFMedia) n.addResource(ElementName.MEDIA, null);
		m.setAttribute("HoleType", EnumHoleType.Explicit.getName());
		final KElement res = new JDFDoc("Resource").getRoot();
		final WalkMedia walkMedia = new WalkMedia();
		walkMedia.jdfToXJDF = new JDFToXJDF();
		walkMedia.walk(m, res);
		assertEquals(null, res.getXPathAttribute("Media/HolePattern/@Pattern", null));
		assertEquals(null, res.getXPathAttribute("Media/@HoleType", null));
	}

	/**
	*
	*/
	@Test
	void testHolePatternComplet()
	{
		final JDFNode n = JDFNode.parseFile(sm_dirTestData + "xjdf/Media_HoleType.jdf");
		final JDFToXJDF c = new JDFToXJDF();
		final KElement xjdf = c.convert(n);
		final XJDFHelper h = XJDFHelper.getHelper(xjdf);
		final KElement m = h.getSet(ElementName.MEDIA, 0).getPartition(0).getResource();
		assertEquals("R2-generic", m.getElement(XJDFConstants.HolePattern).getAttribute(XJDFConstants.Pattern));
	}

	/**
	*
	*/
	@Test
	void testHolePattern2()
	{
		final JDFNode n = JDFNode.createRoot();
		final JDFMedia m = (JDFMedia) n.addResource(ElementName.MEDIA, null);
		m.setAttribute("HoleType", EnumHoleType.R2_generic.getName());
		final KElement res = new JDFDoc("Resource").getRoot();
		final WalkMedia walkMedia = new WalkMedia();
		walkMedia.jdfToXJDF = new JDFToXJDF();
		walkMedia.walk(m, res);
		assertEquals("R2-generic", res.getXPathAttribute("Media/HolePattern/@Pattern", null));
		assertEquals(null, res.getXPathAttribute("Media/@HoleType", null));
	}

	/**
	*
	*/
	@Test
	void testMediaTypeUnknown()
	{
		final JDFNode n = JDFNode.createRoot();
		final JDFMedia m = (JDFMedia) n.addResource(ElementName.MEDIA, null);
		m.setMediaType(EnumMediaType.Unknown);
		final KElement res = new JDFDoc("Resource").getRoot();
		final WalkMedia walkMedia = new WalkMedia();
		walkMedia.jdfToXJDF = new JDFToXJDF();
		walkMedia.walk(m, res);
		assertEquals(EnumMediaType.Other, ((JDFMedia) res.getElement(ElementName.MEDIA)).getMediaType());
	}

	/**
	 *
	 */
	@Test
	void testGrain()
	{
		final JDFNode n = JDFNode.createRoot();
		final JDFMedia m = (JDFMedia) n.addResource(ElementName.MEDIA, null);
		m.setDimensionCM(100, 30);
		m.setGrainDirection(EnumGrainDirection.LongEdge);
		final KElement res = new JDFDoc("Resource").getRoot();
		final WalkMedia walkMedia = new WalkMedia();
		walkMedia.jdfToXJDF = new JDFToXJDF();
		walkMedia.walk(m, res);
		assertEquals(EnumGrainDirection.XDirection, ((JDFMedia) res.getElement(ElementName.MEDIA)).getGrainDirection());
	}

	/**
	 *
	 */
	@Test
	void testFlute()
	{
		final JDFNode n = JDFNode.createRoot();
		final JDFMedia m = (JDFMedia) n.addResource(ElementName.MEDIA, null);
		m.setDimensionCM(10, 30);
		m.setFluteDirection(EnumFluteDirection.LongEdge);
		final KElement res = new JDFDoc("Resource").getRoot();
		final WalkMedia walkMedia = new WalkMedia();
		walkMedia.jdfToXJDF = new JDFToXJDF();
		walkMedia.walk(m, res);
		assertEquals(EnumFluteDirection.YDirection, ((JDFMedia) res.getElement(ElementName.MEDIA)).getFluteDirection());
	}

}
