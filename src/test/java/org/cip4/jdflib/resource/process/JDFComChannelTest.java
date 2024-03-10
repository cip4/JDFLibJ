/*
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
import org.cip4.jdflib.auto.JDFAutoComChannel.EnumChannelType;
import org.cip4.jdflib.core.ElementName;
import org.cip4.jdflib.core.JDFDoc;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**
 * @author prosirai
 *
 */
public class JDFComChannelTest extends JDFTestCaseBase
{
	JDFComChannel cc;

	/**
	 *
	 */
	@Test
	void testSetEMailLocator()
	{
		cc.setEMailLocator(" test@cip4.org");
		Assertions.assertEquals(JDFComChannel.MAILTO + "test@cip4.org", cc.getLocator());
		Assertions.assertEquals("test@cip4.org", cc.getEMailAddress());
		cc.setLocator("test@cip4.org");
		Assertions.assertEquals("test@cip4.org", cc.getEMailAddress());
		cc.setLocator("test.2@cip4.org");
		Assertions.assertEquals("test.2@cip4.org", cc.getEMailAddress());
	}

	/**
	 *
	 */
	@Test
	void testSetPhoneNumber()
	{
		cc.setPhoneNumber(" +49 431 123456 ", ".", EnumChannelType.Phone);
		Assertions.assertEquals(JDFComChannel.TEL + "+49.431.123456", cc.getLocator());
		Assertions.assertEquals("+49431123456", cc.getPhoneNumber(true));
	}

	/**
	 *
	 */
	@Test
	void testGetComChannel()
	{
		JDFPerson p = (JDFPerson) JDFElementColorParams.createRoot(ElementName.PERSON);
		JDFComChannel.appendChannel(p, EnumChannelType.Phone, "1234");
		JDFComChannel.appendChannel(p, EnumChannelType.Mobile, "12345");
		Assertions.assertEquals("12345", JDFComChannel.getChannelByType(p, EnumChannelType.Mobile).getLocator());
	}

	/**
	 *
	 */
	@Test
	void testSetPhoneNumberMobile()
	{
		cc.setPhoneNumber(" +49 431 123456 ", ".", EnumChannelType.Mobile);
		Assertions.assertEquals(JDFComChannel.TEL + "+49.431.123456", cc.getLocator());
		Assertions.assertEquals("+49431123456", cc.getPhoneNumber(true));
	}

	/**
	 *
	 */
	@Test
	void testGetChannelType()
	{
		cc.setPhoneNumber(" +49 431 123456 ", ".", EnumChannelType.Phone);
		cc.setChannelTypeDetails("Mobile");
		Assertions.assertEquals(cc.getChannelType(), EnumChannelType.Mobile);
	}

	/**
	 *
	 */
	@Test
	void testMatchesString()
	{
		cc.setPhoneNumber(" +49 431 123456 ", ".", EnumChannelType.Phone);
		Assertions.assertTrue(cc.stringMatch("tel:+49 431 123456"));
		Assertions.assertTrue(cc.stringMatch("+49431123456"));
	}

	/**
	 *
	 */
	@Test
	void testMatchesComChannel()
	{
		cc.setPhoneNumber(" +49 431 123456 ", ".", EnumChannelType.Phone);
		JDFComChannel ccNew = (JDFComChannel) cc.cloneNewDoc();
		Assertions.assertTrue(ccNew.matchesComChannel(cc));
	}

	/**
	 *
	 */
	@Test
	void testMatches()
	{
		cc.setPhoneNumber(" +49 431 123456 ", ".", EnumChannelType.Phone);
		JDFComChannel ccNew = (JDFComChannel) cc.cloneNewDoc();
		Assertions.assertTrue(ccNew.matches(cc));
		cc.setPhoneNumber("+49 431 123456");
		Assertions.assertTrue(ccNew.matches(cc));
		cc.setPhoneNumber("+49 431 1234567");
		Assertions.assertFalse(ccNew.matches(cc));
		cc.setPhoneNumber("+49 431 123456");
		cc.setChannelType(EnumChannelType.Mobile);
		Assertions.assertFalse(ccNew.matches(cc));
	}

	/**
	 *
	 */
	@Test
	void testSetPhoneNumberShort()
	{
		cc.setChannelType(EnumChannelType.Phone);
		cc.setPhoneNumber(" +49 431 123456 ");
		Assertions.assertEquals(JDFComChannel.TEL + "+49.431.123456", cc.getLocator());
		Assertions.assertEquals("+49431123456", cc.getPhoneNumber(true));
		cc.setChannelType(EnumChannelType.PrivateDirectory);
		try
		{
			cc.setPhoneNumber(" +49 431 123456 ");
			Assertions.fail("illegal channel type");
		}
		catch (IllegalArgumentException x)
		{
			// nop
		}
	}

	/**
	 *
	 *
	 * @see JDFTestCaseBase#setUp()
	 */
	@Override
	@BeforeEach
	public void setUp() throws Exception
	{
		super.setUp();
		JDFDoc d = new JDFDoc(ElementName.COMCHANNEL);
		cc = (JDFComChannel) d.getRoot();
	}
}
