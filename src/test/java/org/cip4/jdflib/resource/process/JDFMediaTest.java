/*
 *
 * The CIP4 Software License, Version 1.0
 *
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

package org.cip4.jdflib.resource.process;

import java.util.Vector;

import org.cip4.jdflib.JDFTestCaseBase;
import org.cip4.jdflib.auto.JDFAutoMedia.EnumBackCoatings;
import org.cip4.jdflib.auto.JDFAutoMedia.EnumHoleType;
import org.cip4.jdflib.auto.JDFAutoMedia.EnumISOPaperSubstrate;
import org.cip4.jdflib.auto.JDFAutoMedia.EnumMediaType;
import org.cip4.jdflib.core.AttributeName;
import org.cip4.jdflib.core.ElementName;
import org.cip4.jdflib.core.JDFDoc;
import org.cip4.jdflib.core.KElement;
import org.cip4.jdflib.datatypes.JDFXYPair;
import org.cip4.jdflib.node.JDFNode;
import org.cip4.jdflib.pool.JDFResourcePool;
import org.cip4.jdflib.resource.JDFResource.EnumPartIDKey;
import org.cip4.jdflib.resource.JDFResource.EnumResourceClass;
import org.cip4.jdflib.resource.process.postpress.JDFHoleMakingParams;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

/**
 *
 * @author Rainer Prosi, Heidelberger Druckmaschinen *
 */
public class JDFMediaTest extends JDFTestCaseBase
{

	/**
	 * Test method for 'org.cip4.jdflib.resource.process.JDFMedia.setDimensionCM(JDFXYPair)'
	 */
	@Test
	public final void testSetGetDimension()
	{
		final JDFDoc doc = new JDFDoc("JDF");
		final JDFNode root = doc.getJDFRoot();
		final JDFResourcePool resPool = root.getCreateResourcePool();
		final KElement kElem = resPool.appendResource(ElementName.MEDIA, EnumResourceClass.Consumable, null);
		Assertions.assertTrue(kElem instanceof JDFMedia);
		final JDFMedia media = ((JDFMedia) kElem);
		Assertions.assertNull(media.getDimensionCM());
		Assertions.assertNull(media.getDimensionInch());
		media.setDimensionCM(new JDFXYPair(2.54, 2.54));

		JDFXYPair result = media.getDimension();
		Assertions.assertEquals(new JDFXYPair(72, 72), result);

		result = media.getDimensionCM();
		Assertions.assertEquals(new JDFXYPair(2.54, 2.54), result);

		result = media.getDimensionInch();
		Assertions.assertEquals(new JDFXYPair(1, 1), result);

		media.setDimensionInch(new JDFXYPair(1, 1));

		result = media.getDimension();
		Assertions.assertEquals(new JDFXYPair(72, 72), result);

		result = media.getDimensionCM();
		Assertions.assertEquals(new JDFXYPair(2.54, 2.54), result);

		result = media.getDimensionInch();
		Assertions.assertEquals(new JDFXYPair(1, 1), result);
	}

	// //////////////////////////////////////////////////////////////////

	/**
	 *
	 */
	@Test
	public final void testThicknessFromWeight()
	{
		final JDFMedia m = (JDFMedia) new JDFDoc("Media").getRoot();
		m.setThicknessFromWeight(true, false);
		m.setMediaType(EnumMediaType.Paper);
		Assertions.assertFalse(m.hasAttribute(AttributeName.THICKNESS));
		m.setWeight(80.);
		m.setThicknessFromWeight(true, false);
		Assertions.assertEquals(m.getThickness(), 100., 1.);
		final JDFMedia m2 = (JDFMedia) m.addPartition(EnumPartIDKey.Run, "r1");
		m2.setWeight(40.);
		m.setThicknessFromWeight(true, true);
		Assertions.assertEquals(m2.getThickness(), 50., 1.);
		Assertions.assertEquals(m.getThickness(), 100., 1.);
	}

	/**
	 *
	 */
	@Test
	public final void testGetBackGrade()
	{
		final JDFMedia m = (JDFMedia) new JDFDoc(ElementName.MEDIA).getRoot();
		m.setGrade(5);
		Assertions.assertEquals(m.getBackGrade(), 5);
		m.setGrade(1);
		Assertions.assertEquals(m.getBackGrade(), 1);
		m.setBackCoatings(EnumBackCoatings.Matte);
		Assertions.assertEquals(m.getBackGrade(), 2);
		m.setBackCoatings(EnumBackCoatings.None);
		Assertions.assertEquals(m.getBackGrade(), 4);
		m.setAttribute(AttributeName.BACKISOPAPERSUBSTRATE, "PS1");
		Assertions.assertEquals(m.getBackGrade(), 1);
	}

	/**
	 *
	 */
	@Test
	public final void testGetGrade()
	{
		final JDFMedia m = (JDFMedia) new JDFDoc(ElementName.MEDIA).getRoot();
		m.setISOPaperSubstrate(EnumISOPaperSubstrate.PS4);
		Assertions.assertEquals(2, m.getGrade(true));
		Assertions.assertEquals(0, m.getGrade(false));
	}

	/**
	 *
	 */
	@Test
	public final void testGradeFromISOGrade()
	{
		Assertions.assertEquals(1, JDFMedia.getGradeFromIsoPaper(EnumISOPaperSubstrate.PS1));
		Assertions.assertEquals(0, JDFMedia.getGradeFromIsoPaper(null));
		Assertions.assertEquals(3, JDFMedia.getGradeFromIsoPaper(EnumISOPaperSubstrate.PS2));
		Assertions.assertEquals(4, JDFMedia.getGradeFromIsoPaper(EnumISOPaperSubstrate.PS7));
		Assertions.assertEquals(5, JDFMedia.getGradeFromIsoPaper(EnumISOPaperSubstrate.PS8));
	}

	/**
	 *
	 */
	@Test
	public final void testISOGrade()
	{
		for (int i = 1; i <= 5; i++)
		{
			Assertions.assertEquals(i, JDFMedia.getGradeFromIsoPaper(JDFMedia.getIsoPaperFromGrade(i)));
		}
		Assertions.assertNull(JDFMedia.getIsoPaperFromGrade(-2));
		Assertions.assertNull(JDFMedia.getIsoPaperFromGrade(42));
	}

	/**
	 * 1.5 enums...
	 */
	@Test
	public final void testMediaType()
	{
		final JDFMedia m = (JDFMedia) new JDFDoc(ElementName.MEDIA).getRoot();
		m.setMediaType(EnumMediaType.Vinyl);
		Assertions.assertEquals(m.getMediaType(), EnumMediaType.Vinyl);
		Assertions.assertEquals(m.getMediaType().getName(), "Vinyl");
	}

	/**
	 *
	 */
	@Test
	public final void testHoleType()
	{
		final JDFDoc doc = new JDFDoc("JDF");
		final JDFNode root = doc.getJDFRoot();
		final JDFResourcePool resPool = root.getCreateResourcePool();

		// check HoleType for JDFMedia
		KElement kElem = resPool.appendResource(ElementName.MEDIA, EnumResourceClass.Consumable, null);
		Assertions.assertTrue(kElem instanceof JDFMedia);
		final JDFMedia media = ((JDFMedia) kElem);

		final Vector<EnumHoleType> v = new Vector<>();
		v.addElement(EnumHoleType.None);
		v.addElement(EnumHoleType.C9_5m_round_0t);
		Assertions.assertEquals(EnumHoleType.C9_5m_round_0t.getName(), "C9.5m-round-0t");

		media.setHoleType(v);
		Assertions.assertEquals(media.getHoleType(), v);
		Assertions.assertEquals(((EnumHoleType) media.getHoleType().elementAt(1)).getName(), v.elementAt(1).getName());
		Assertions.assertEquals(((EnumHoleType) media.getHoleType().elementAt(1)).getName(), "C9.5m-round-0t");

		// overwrite HoleType low level to check if conversion of DOT and HYPHEN
		// to UNDERSCORE was successful
		media.setAttribute(AttributeName.HOLETYPE, "C9.5m-round-0t");
		Assertions.assertEquals((media.getHoleType().elementAt(0)), EnumHoleType.C9_5m_round_0t);

		// now check the same with JDFHoleMakingParams
		kElem = resPool.appendResource(ElementName.HOLEMAKINGPARAMS, EnumResourceClass.Consumable, null);
		Assertions.assertTrue(kElem instanceof JDFHoleMakingParams);
		final JDFHoleMakingParams holeMakingParams = ((JDFHoleMakingParams) kElem);

		holeMakingParams.setHoleType(v);
		Assertions.assertEquals(holeMakingParams.getHoleType(), v);
		Assertions.assertEquals(((EnumHoleType) holeMakingParams.getHoleType().elementAt(1)).getName(), v.elementAt(1).getName());
		Assertions.assertEquals(((EnumHoleType) holeMakingParams.getHoleType().elementAt(1)).getName(), "C9.5m-round-0t");
	}

	/**
	 *
	 *
	 */
	@Test
	public final void testMatches()
	{
		final JDFDoc doc = new JDFDoc("Media");
		final JDFMedia m = (JDFMedia) doc.getRoot();
		final JDFMedia m2 = (JDFMedia) new JDFDoc("Media").getRoot();
		Assertions.assertTrue(m.matches(m2));
		m.setGrade(4);
		m2.setGrade(4);
		Assertions.assertTrue(m.matches(m2));
		m2.setGrade(5);
		Assertions.assertFalse(m.matches(m2));
		m.setGrade(5);
		Assertions.assertTrue(m.matches(m2));
		m.setDimensionCM(new JDFXYPair(10, 20));
		m2.setDimensionCM(new JDFXYPair(10.1, 20));
		Assertions.assertTrue(m.matches(m2));
		m2.setDimensionCM(new JDFXYPair(100.1, 20));
		Assertions.assertFalse(m.matches(m2));

		m.setProductID("foo");
		Assertions.assertTrue(m.matches("foo"));
	}

	/**
	 *
	 *
	 */
	@Test
	public final void testMatchesBrand()
	{
		final JDFDoc doc = new JDFDoc("Media");
		final JDFMedia m = (JDFMedia) doc.getRoot();
		final JDFMedia m2 = (JDFMedia) new JDFDoc("Media").getRoot();
		Assertions.assertTrue(m.matches(m2));
		m.setBrand("B1");
		m2.setBrand("B2");
		m.setGrade(4);
		m2.setGrade(4);
		Assertions.assertFalse(m.matches(m2));
		m2.setGrade(5);
		Assertions.assertFalse(m.matches(m2));
		m.setGrade(5);
		Assertions.assertFalse(m.matches(m2));
		m.setDimensionCM(new JDFXYPair(10, 20));
		m2.setDimensionCM(new JDFXYPair(10.1, 20));
		Assertions.assertFalse(m.matches(m2));
	}

	/**
	 *
	 */
	@Test
	void testCert()
	{
		final JDFMedia ci = (JDFMedia) new JDFDoc(ElementName.MEDIA).getRoot();
		ci.appendCertification().setOrganization("o1");
		Assertions.assertEquals("o1", ci.getCertification(0).getOrganization());
	}

}
