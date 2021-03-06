/*
 *
 * The CIP4 Software License, Version 1.0
 *
 *
 * Copyright (c) 2001-2020 The International Cooperation for the Integration of
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

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNotSame;
import static org.junit.Assert.assertTrue;

import org.cip4.jdflib.JDFTestCaseBase;
import org.cip4.jdflib.core.AttributeName;
import org.cip4.jdflib.core.ElementName;
import org.cip4.jdflib.core.JDFConstants;
import org.cip4.jdflib.core.JDFDoc;
import org.cip4.jdflib.core.JDFElement.EnumValidationLevel;
import org.cip4.jdflib.core.XMLParser;
import org.cip4.jdflib.datatypes.JDFCMYKColor;
import org.cip4.jdflib.node.JDFNode;
import org.cip4.jdflib.node.JDFNode.EnumType;
import org.junit.Test;

/**
 * @author Dr. Rainer Prosi, Heidelberger Druckmaschinen AG
 *
 *         23.01.2009
 */
public class JDFColorTest extends JDFTestCaseBase
{

	private JDFColorPool cp;

	/**
	 *
	 */
	@Test
	public void testSet8BitNames()
	{
		final JDFColor c = cp.appendColor();
		final byte[] b = { (byte) 0xb8, (byte) 0xde, (byte) 0xd8, (byte) 0xb0, (byte) 0xdd };

		c.set8BitNames(b);
		assertNotSame("", c.getRawName());
		assertNotSame("", c.getName());
		assertNotNull(cp.toXML());
		assertNotNull("crap chars are parseable", new XMLParser().parseString(cp.toXML()));

		final JDFColor c2 = cp.appendColor();
		c2.set8BitNames(new byte[] { (byte) 233 });
		assertNotSame("", c2.getRawName());
		assertNotSame("", c2.getName());
		assertNotNull("crap chars are parseable", new XMLParser().parseString(cp.toXML()));
	}

	/**
	 *
	 */
	@Test
	public void testSetRawName()
	{
		final JDFColor c = cp.appendColor();
		final byte[] b = "grün".getBytes();
		c.set8BitNames(b);
		assertEquals(c.get8BitName(), "grün");
	}

	/**
	 *
	 */
	@Test
	public void testCMYK()
	{
		for (final String c : JDFColor.getCMYKSeparations())
		{
			final JDFColor co = cp.appendColorWithName(c, null);
			assertEquals(c, co.get8BitName());
		}
	}

	/**
	 *
	 */
	@Test
	public void testKCMY()
	{
		assertTrue(JDFColor.getKCMYSeparations().containsAll(JDFColor.getCMYKSeparations()));
		assertEquals(JDFConstants.SEPARATION_BLACK, JDFColor.getKCMYSeparations().get(0));
	}

	/**
	 *
	 */
	@Test
	public void testDuplicateName()
	{
		final JDFColor c = cp.appendColorWithName("a", "a");
		final JDFColor c1 = cp.appendColorWithName("a2", null);
		final JDFColor c2 = cp.appendColor();
		c2.setName("a");
		assertTrue(c2.getInvalidAttributes(EnumValidationLevel.Incomplete, false, 0).contains("Name"));
		assertFalse(c1.getInvalidAttributes(EnumValidationLevel.Incomplete, false, 0).contains("Name"));
		assertFalse(c.getInvalidAttributes(EnumValidationLevel.Incomplete, false, 0).contains("Name"));
		final JDFColor c3 = cp.appendColor();
		c3.setName("aa");
		c3.set8BitNames("a".getBytes());
		assertTrue(c3.getInvalidAttributes(EnumValidationLevel.Incomplete, false, 0).contains("RawName"));

	}

	/**
	 *
	 */
	@Test
	public void testDuplicateActualName()
	{
		final JDFColor c = cp.appendColorWithName("a", null);
		final JDFColor c1 = cp.appendColorWithName("a2", null);
		c.setActualColorName("act");
		c1.setActualColorName("act");
		assertTrue(c1.getInvalidAttributes(EnumValidationLevel.Incomplete, false, 0).contains(AttributeName.ACTUALCOLORNAME));
		assertFalse(c.getInvalidAttributes(EnumValidationLevel.Incomplete, false, 0).contains(AttributeName.ACTUALCOLORNAME));

	}

	/**
	 *
	 */
	@Test
	public void testGetHTMLColor()
	{
		final JDFColor c = cp.appendColor();
		c.setCMYK(new JDFCMYKColor(0, 0, 0, 1));
		assertEquals(c.getHTMLColor(), "#000000");
		c.setCMYK(new JDFCMYKColor(1, 1, 1, 1));
		assertEquals(c.getHTMLColor(), "#000000");
		c.setCMYK(new JDFCMYKColor(0, 0, 0, 0));
		assertEquals(c.getHTMLColor(), "#ffffff");
	}

	@Override
	public void setUp() throws Exception
	{
		super.setUp();
		final JDFDoc doc = new JDFDoc("JDF");
		final JDFNode n = doc.getJDFRoot();
		n.setType(EnumType.ColorSpaceConversion);
		cp = (JDFColorPool) n.addResource(ElementName.COLORPOOL, null);
	}

}
