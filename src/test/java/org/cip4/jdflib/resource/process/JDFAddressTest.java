/*
 *
 * The CIP4 Software License, Version 1.0
 *
 *
 * Copyright (c) 2001-2021 The International Cooperation for the Integration of
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

import org.cip4.jdflib.JDFTestCaseBase;
import org.cip4.jdflib.core.AttributeName;
import org.cip4.jdflib.core.ElementName;
import org.cip4.jdflib.core.JDFComment;
import org.cip4.jdflib.core.JDFDoc;
import org.cip4.jdflib.core.StringArray;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

/**
 *
 *
 * @author rainer prosi
 * @date May 9, 2014
 */
public class JDFAddressTest extends JDFTestCaseBase
{
	/**
	 * tests the JDFAddress class
	 *
	 */
	@Test
	public final void testExtendedAddress()
	{
		final JDFDoc doc = new JDFDoc(ElementName.ADDRESS);
		final JDFAddress ad = (JDFAddress) doc.getRoot();
		final JDFComment c = (JDFComment) ad.appendExtendedAddress();
		Assertions.assertFalse(c.hasAttribute(AttributeName.ID));
	}

	/**
	 * tests the JDFAddress class
	 *
	 */
	@Test
	public final void testAddressLine()
	{
		final JDFDoc doc = new JDFDoc(ElementName.ADDRESS);
		final JDFAddress ad = (JDFAddress) doc.getRoot();
		for (int i = 0; i < 5; i++)
		{
			ad.appendAddressLine("Line" + i);
		}
		for (int i = 0; i < 5; i++)
		{
			Assertions.assertEquals("Line" + i, ad.getAddressLineText(i));
		}
		Assertions.assertNull(ad.getAddressLine(7));
	}

	/**
	 * tests the JDFAddress class
	 *
	 */
	@Test
	public final void testAddressLines()
	{
		final JDFDoc doc = new JDFDoc(ElementName.ADDRESS);
		final JDFAddress ad = (JDFAddress) doc.getRoot();
		for (int i = 0; i < 5; i++)
		{
			ad.appendAddressLine("Line" + i);
			ad.appendComment().setText("comment " + i);
		}

		final StringArray sa = ad.getAddressLines();
		for (int i = 0; i < 5; i++)
		{
			Assertions.assertEquals("Line" + i, sa.get(i));
		}
	}

	/**
	 * tests the JDFAddress class
	 *
	 */
	@Test
	public final void testExtendedAddressText()
	{
		final JDFDoc doc = new JDFDoc(ElementName.ADDRESS);
		final JDFAddress ad = (JDFAddress) doc.getRoot();
		ad.setExtendedAddressText("suite");
		Assertions.assertEquals("suite", ad.getExtendedAddressText());
	}

	/**
	 *
	 *
	 */
	@Test
	public final void testMatches()
	{
		final JDFDoc doc = new JDFDoc("Address");
		final JDFAddress ad = (JDFAddress) doc.getRoot();
		final JDFAddress ad2 = (JDFAddress) new JDFDoc("Address").getRoot();
		Assertions.assertTrue(ad.matches(ad));
		ad.setCity("cc1");
		ad2.setCity("cc2");
		Assertions.assertTrue(ad.matches(ad2));
		ad.setCountryCode("DE");
		Assertions.assertTrue(ad.matches(ad2));
		ad2.setCountryCode("de");
		Assertions.assertTrue(ad.matches(ad2));
		ad2.setCountryCode("dd");
		Assertions.assertFalse(ad.matches(ad2));
		ad2.setCountryCode("de");
		ad2.setCity("abcde");
		Assertions.assertFalse(ad.matches(ad2));
	}
}
