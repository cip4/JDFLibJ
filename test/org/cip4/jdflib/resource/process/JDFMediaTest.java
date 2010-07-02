/*
 *
 * The CIP4 Software License, Version 1.0
 *
 *
 * Copyright (c) 2001-2010 The International Cooperation for the Integration of 
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

package org.cip4.jdflib.resource.process;

import java.util.Vector;

import junit.framework.TestCase;

import org.cip4.jdflib.auto.JDFAutoMedia.EnumBackCoatings;
import org.cip4.jdflib.auto.JDFAutoMedia.EnumHoleType;
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

/**
 * 
  * @author Rainer Prosi, Heidelberger Druckmaschinen *
 */
public class JDFMediaTest extends TestCase
{

	/**
	 * 
	 * @param arg0
	 */
	public JDFMediaTest(String arg0)
	{
		super(arg0);
	}

	/**
	 * Test method for
	 * 'org.cip4.jdflib.resource.process.JDFMedia.setDimensionCM(JDFXYPair)'
	 */
	public final void testSetGetDimension()
	{
		JDFDoc doc = new JDFDoc("JDF");
		JDFNode root = doc.getJDFRoot();
		JDFResourcePool resPool = root.getCreateResourcePool();
		KElement kElem = resPool.appendResource(ElementName.MEDIA, EnumResourceClass.Consumable, null);
		assertTrue(kElem instanceof JDFMedia);
		JDFMedia media = ((JDFMedia) kElem);

		media.setDimensionCM(new JDFXYPair(2.54, 2.54));

		JDFXYPair result = media.getDimension();
		assertEquals(new JDFXYPair(72, 72), result);

		result = media.getDimensionCM();
		assertEquals(new JDFXYPair(2.54, 2.54), result);

		result = media.getDimensionInch();
		assertEquals(new JDFXYPair(1, 1), result);

		media.setDimensionInch(new JDFXYPair(1, 1));

		result = media.getDimension();
		assertEquals(new JDFXYPair(72, 72), result);

		result = media.getDimensionCM();
		assertEquals(new JDFXYPair(2.54, 2.54), result);

		result = media.getDimensionInch();
		assertEquals(new JDFXYPair(1, 1), result);
	}

	// //////////////////////////////////////////////////////////////////

	/**
	 * 
	 */
	public final void testThicknessFromWeight()
	{
		JDFMedia m = (JDFMedia) new JDFDoc("Media").getRoot();
		m.setThicknessFromWeight(true, false);
		m.setMediaType(EnumMediaType.Paper);
		assertFalse(m.hasAttribute(AttributeName.THICKNESS));
		m.setWeight(80.);
		m.setThicknessFromWeight(true, false);
		assertEquals(m.getThickness(), 100., 1.);
		JDFMedia m2 = (JDFMedia) m.addPartition(EnumPartIDKey.Run, "r1");
		m2.setWeight(40.);
		m.setThicknessFromWeight(true, true);
		assertEquals(m2.getThickness(), 50., 1.);
		assertEquals(m.getThickness(), 100., 1.);
	}

	// //////////////////////////////////////////////////////////////////

	/**
	 * 
	 */
	public final void testGetBackGrade()
	{
		JDFMedia m = (JDFMedia) new JDFDoc(ElementName.MEDIA).getRoot();
		m.setGrade(5);
		assertEquals(m.getBackGrade(), 5, 0);
		m.setGrade(1);
		assertEquals(m.getBackGrade(), 1, 0);
		m.setBackCoatings(EnumBackCoatings.Matte);
		assertEquals(m.getBackGrade(), 2, 0);
		m.setBackCoatings(EnumBackCoatings.None);
		assertEquals(m.getBackGrade(), 4, 0);
	}

	/**
	 * 
	 */
	public final void testHoleType()
	{
		JDFDoc doc = new JDFDoc("JDF");
		JDFNode root = doc.getJDFRoot();
		JDFResourcePool resPool = root.getCreateResourcePool();

		// check HoleType for JDFMedia
		KElement kElem = resPool.appendResource(ElementName.MEDIA, EnumResourceClass.Consumable, null);
		assertTrue(kElem instanceof JDFMedia);
		JDFMedia media = ((JDFMedia) kElem);

		Vector<EnumHoleType> v = new Vector<EnumHoleType>();
		v.addElement(EnumHoleType.None);
		v.addElement(EnumHoleType.C9_5m_round_0t);
		assertEquals(EnumHoleType.C9_5m_round_0t.getName(), "C9.5m-round-0t");

		media.setHoleType(v);
		assertEquals(media.getHoleType(), v);
		assertEquals(((EnumHoleType) media.getHoleType().elementAt(1)).getName(), v.elementAt(1).getName());
		assertEquals(((EnumHoleType) media.getHoleType().elementAt(1)).getName(), "C9.5m-round-0t");

		// overwrite HoleType low level to check if conversion of DOT and HYPHEN
		// to UNDERSCORE was successful
		media.setAttribute(AttributeName.HOLETYPE, "C9.5m-round-0t");
		assertEquals((media.getHoleType().elementAt(0)), EnumHoleType.C9_5m_round_0t);

		// now check the same with JDFHoleMakingParams
		kElem = resPool.appendResource(ElementName.HOLEMAKINGPARAMS, EnumResourceClass.Consumable, null);
		assertTrue(kElem instanceof JDFHoleMakingParams);
		JDFHoleMakingParams holeMakingParams = ((JDFHoleMakingParams) kElem);

		holeMakingParams.setHoleType(v);
		assertEquals(holeMakingParams.getHoleType(), v);
		assertEquals(((EnumHoleType) holeMakingParams.getHoleType().elementAt(1)).getName(), v.elementAt(1).getName());
		assertEquals(((EnumHoleType) holeMakingParams.getHoleType().elementAt(1)).getName(), "C9.5m-round-0t");
	}
}
