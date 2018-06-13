/*
 * The CIP4 Software License, Version 1.0
 *
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
package org.cip4.jdflib.extensions;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import org.cip4.jdflib.JDFTestCaseBase;
import org.cip4.jdflib.core.AttributeName;
import org.cip4.jdflib.core.ElementName;
import org.cip4.jdflib.core.JDFElement.EnumValidationLevel;
import org.cip4.jdflib.core.KElement;
import org.cip4.jdflib.core.XMLDoc;
import org.cip4.jdflib.jmf.JDFKnownMsgQuParams;
import org.cip4.jdflib.jmf.JDFMessage.EnumFamily;
import org.cip4.jdflib.jmf.JDFMessage.EnumType;
import org.cip4.jdflib.jmf.JDFMessageService;
import org.cip4.jdflib.jmf.JDFSubscription;
import org.cip4.jdflib.resource.JDFNotification;
import org.junit.Before;
import org.junit.Test;

public class MessageHelperTest extends JDFTestCaseBase
{
	/**
	 * @see junit.framework.TestCase#setUp()
	 * @throws Exception if snafu
	 */
	@Before
	@Override
	public void setUp() throws Exception
	{
		super.setUp();
		KElement.setLongID(false);
	}

	/**
	 *
	 */
	@Test
	public void testSubscribe()
	{
		final XJMFHelper xjmfHelper = new XJMFHelper();
		final MessageHelper mh = xjmfHelper.appendMessage(EnumFamily.Query, EnumType.Status);
		final JDFSubscription sub = mh.subscribe("http://foo");
		assertNotNull(sub);
		writeRoundTripX(xjmfHelper.theElement, "subscribe.xjmf", EnumValidationLevel.Complete);
	}

	/**
	 *
	 */
	@Test
	public void testCleanupNotification()
	{
		final KElement resp = new XJMFHelper().getRoot().appendElement("ResponseKnownMessages");
		final JDFMessageService ms = (JDFMessageService) resp.appendElement(ElementName.MESSAGESERVICE);
		ms.setType("ResponseKnownMessages");
		final JDFNotification not = (JDFNotification) resp.appendElement(ElementName.NOTIFICATION);
		not.setType("bar");
		final MessageHelper ah = new MessageHelper(resp);
		final KElement header = resp.appendElement(XJDFConstants.Header);
		ah.cleanUp();
		assertEquals(not.getNextSiblingElement(), ms);
		assertEquals(header.getNextSiblingElement(), not);
	}

	/**
	 *
	 */
	@Test
	public void testCleanupSubscription()
	{
		final KElement resp = new XJMFHelper().getRoot().appendElement("ResponseKnownMessages");
		final JDFKnownMsgQuParams ms = (JDFKnownMsgQuParams) resp.appendElement(ElementName.KNOWNMSGQUPARAMS);
		final JDFSubscription sub = (JDFSubscription) resp.appendElement(ElementName.SUBSCRIPTION);
		sub.setURL("bar");
		final MessageHelper ah = new MessageHelper(resp);
		final KElement header = resp.appendElement(XJDFConstants.Header);
		ah.cleanUp();
		assertEquals(sub.getNextSiblingElement(), ms);
		assertEquals(header.getNextSiblingElement(), sub);
	}

	/**
	 *
	 */
	@Test
	public void testIsQuery()
	{
		final XJMFHelper xjmfHelper = new XJMFHelper();
		MessageHelper mh = xjmfHelper.appendMessage(EnumFamily.Query, EnumType.Status);
		assertTrue(mh.isQuery());
		mh = xjmfHelper.appendMessage(EnumFamily.Signal, EnumType.Status);
		assertFalse(mh.isQuery());
	}

	/**
	 *
	 */
	@Test
	public void testIsCommand()
	{
		final XJMFHelper xjmfHelper = new XJMFHelper();
		MessageHelper mh = xjmfHelper.appendMessage(EnumFamily.Command, EnumType.Status);
		assertTrue(mh.isCommand());
		mh = xjmfHelper.appendMessage(EnumFamily.Signal, EnumType.Status);
		assertFalse(mh.isCommand());
	}

	/**
	 *
	 */
	@Test
	public void testIsSignal()
	{
		final XJMFHelper xjmfHelper = new XJMFHelper();
		MessageHelper mh = xjmfHelper.appendMessage(EnumFamily.Signal, EnumType.Status);
		assertTrue(mh.isSignal());
		mh = xjmfHelper.appendMessage(EnumFamily.Command, EnumType.Status);
		assertFalse(mh.isSignal());
	}

	/**
	 *
	 */
	@Test
	public void testIsMessage()
	{
		final XJMFHelper xjmfHelper = new XJMFHelper();
		final MessageHelper mh = xjmfHelper.appendMessage(EnumFamily.Signal, EnumType.Status);
		assertTrue(MessageHelper.isMessage(mh.getRoot()));
	}

	/**
	 *
	 */
	@Test
	public void testIsMessageForeign()
	{
		final XMLDoc d = new XMLDoc("foo:CommandBar", "foo");
		assertTrue(MessageHelper.isMessage(d.getRoot()));
	}

	/**
	*
	*/
	@Test
	public void testIsResponse()
	{
		final XJMFHelper xjmfHelper = new XJMFHelper();
		MessageHelper mh = xjmfHelper.appendMessage(EnumFamily.Response, EnumType.Status);
		assertTrue(mh.isResponse());
		mh = xjmfHelper.appendMessage(EnumFamily.Command, EnumType.Status);
		assertFalse(mh.isResponse());
	}

	/**
	*
	*/
	@Test
	public void testGetReturnCode()
	{
		final XJMFHelper xjmfHelper = new XJMFHelper();
		final MessageHelper mh = xjmfHelper.appendMessage(EnumFamily.Response, EnumType.Status);
		assertEquals(0, mh.getReturnCode());
		mh.setAttribute(AttributeName.RETURNCODE, "6");
		assertEquals(6, mh.getReturnCode());
	}

	/**
	*
	*/
	@Test
	public void testSetQuery()
	{
		final XJMFHelper xjmfHelper = new XJMFHelper();
		final MessageHelper mh = xjmfHelper.appendMessage(EnumFamily.Response, EnumType.Status);
		final MessageHelper mhc = xjmfHelper.appendMessage(EnumFamily.Query, EnumType.Status);
		mh.setQuery(mhc);
		assertEquals(mhc.getHeader().getID(), mh.getHeader().getAttribute(AttributeName.REFID));
		writeRoundTripX(xjmfHelper.theElement, "setQuery.xjmf", EnumValidationLevel.Complete);
	}

	/**
	*
	*/
	@Test
	public void testSetQuerySignal()
	{
		final XJMFHelper xjmfHelper = new XJMFHelper();
		final MessageHelper mh = xjmfHelper.appendMessage(EnumFamily.Signal, EnumType.Status);
		mh.appendElement(ElementName.DEVICEINFO).setAttribute(AttributeName.STATUS, "Idle");

		final MessageHelper mhc = xjmfHelper.appendMessage(EnumFamily.Query, EnumType.Status);
		mh.setQuery(mhc);
		assertEquals(mhc.getHeader().getID(), mh.getHeader().getAttribute(AttributeName.REFID));
		writeRoundTripX(xjmfHelper.theElement, "setQuerySignal.xjmf", EnumValidationLevel.Complete);
	}

	/**
	*
	*/
	@Test
	public void testSchemaNotification()
	{
		final XJMFHelper xjmfHelper = new XJMFHelper();
		final MessageHelper mh = xjmfHelper.appendMessage(EnumFamily.Response, EnumType.KnownMessages);
		mh.appendElement(ElementName.MESSAGESERVICE).setAttribute(AttributeName.TYPE, "KnownMessages");
		mh.appendElement(ElementName.NOTIFICATION).setAttribute(AttributeName.CLASS, "Event");
		mh.cleanUp();
		writeRoundTripX(xjmfHelper.theElement, "knownMessagesResponse.xjmf", EnumValidationLevel.Complete);
	}

}
