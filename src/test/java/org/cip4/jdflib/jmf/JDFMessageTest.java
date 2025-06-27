/*
 *
 * The CIP4 Software License, Version 1.0
 *
 *
 * Copyright (c) 2001-2019 The International Cooperation for the Integration of Processes in Prepress, Press and Postpress (CIP4). All rights reserved.
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
package org.cip4.jdflib.jmf;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNotSame;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;

import org.cip4.jdflib.core.AttributeName;
import org.cip4.jdflib.core.ElementName;
import org.cip4.jdflib.core.JDFDoc;
import org.cip4.jdflib.core.JDFElement.EnumValidationLevel;
import org.cip4.jdflib.core.JDFElement.EnumVersion;
import org.cip4.jdflib.core.JDFException;
import org.cip4.jdflib.jmf.JDFMessage.EnumFamily;
import org.cip4.jdflib.jmf.JDFMessage.EnumType;
import org.cip4.jdflib.util.JDFDate;
import org.cip4.jdflib.util.StringUtil;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**
 * @author Dr. Rainer Prosi, Heidelberger Druckmaschinen AG
 *
 *         far before May 17, 2009
 */
class JDFMessageTest
{
	private JDFJMF jmf;

	/**
	 *
	 *
	 */
	@Test
	void testEnumFamily()
	{
		assertEquals(EnumFamily.getEnumMap().get("Signal"), EnumFamily.Signal);
		assertNotNull(EnumFamily.getEnumList().get(2));
	}

	/**
	 *
	 *
	 */
	@Test
	void testInit()
	{
		final JDFJMF jmf = JDFJMF.createJMF(EnumFamily.Signal, EnumType.Status);
		final JDFSignal s = jmf.getSignal(0);
		assertNotNull(StringUtil.getNonEmpty(s.getID()));
	}

	/**
	 *
	 */
	@Test
	void testIsValidMessageElement()
	{
		final JDFSignal sig = (JDFSignal) jmf.appendMessageElement(EnumFamily.Signal, EnumType.UpdateJDF);
		assertTrue(sig.isValidMessageElement(ElementName.UPDATEJDFCMDPARAMS, 0));
		assertFalse(sig.isValidMessageElement(ElementName.MODIFYNODECMDPARAMS, 0));
		assertTrue(sig.isValidMessageElement(ElementName.NOTIFICATION, 0));
		assertTrue(sig.isValidMessageElement(ElementName.GENERALID, 0));
		assertTrue(sig.isValidMessageElement(ElementName.COMMENT, 0));

		final JDFResponse resp = (JDFResponse) jmf.appendMessageElement(EnumFamily.Response, EnumType.RepeatMessages);
		assertTrue(resp.isValidMessageElement(ElementName.SIGNAL, 3));
		assertTrue(resp.isValidMessageElement(ElementName.REGISTRATION, 3));
		assertFalse(resp.isValidMessageElement(ElementName.QUEUE, 0));

		final JDFRegistration reg = (JDFRegistration) jmf.appendMessageElement(EnumFamily.Registration, EnumType.RepeatMessages);
		assertFalse(reg.isValidMessageElement(ElementName.SIGNAL, 3));

		final JDFQuery q = (JDFQuery) jmf.appendMessageElement(EnumFamily.Query, EnumType.KnownSubscriptions);
		assertTrue(q.isValidMessageElement(ElementName.SUBSCRIPTIONFILTER, 0));
		assertFalse(q.isValidMessageElement(ElementName.SUBSCRIPTIONINFO, 0));

		final JDFCommand c = (JDFCommand) jmf.appendMessageElement(EnumFamily.Command, EnumType.RequestForAuthentication);
		assertTrue(c.isValidMessageElement(ElementName.AUTHENTICATIONCMDPARAMS, 0));
		assertFalse(c.isValidMessageElement(ElementName.AUTHENTICATIONQUPARAMS, 0));

	}

	/**
	 *
	 */
	@Test
	void testAppendValidElement()
	{
		final JDFSignal sig = (JDFSignal) jmf.appendMessageElement(EnumFamily.Signal, EnumType.UpdateJDF);
		assertNotNull(sig.appendValidElement(ElementName.UPDATEJDFCMDPARAMS, null));
	}

	/**
	 *
	 */
	@Test
	void testAppendNotificationFilter()
	{
		final JDFSignal sig = (JDFSignal) jmf.appendMessageElement(EnumFamily.Signal, EnumType.Notification);
		assertNotNull(sig.appendValidElement(ElementName.NOTIFICATIONFILTER, null));
	}

	/**
	 *
	 */
	@Test
	void testAppendGangInfoQu()
	{
		final JDFQuery sig = (JDFQuery) jmf.appendMessageElement(EnumFamily.Query, EnumType.GangStatus);
		assertNotNull(sig.appendGangQuFilter());
		try
		{
			sig.appendValidElement(ElementName.GANGINFO, null);
			fail("want exception");
		}
		catch (final JDFException x)
		{
			//
		}
	}

	/**
	 *
	 */
	@Test
	void testAppendGangInfo()
	{
		final JDFResponse sig = (JDFResponse) jmf.appendMessageElement(EnumFamily.Response, EnumType.GangStatus);
		sig.appendGangInfo();
		sig.appendGangInfo();
		try
		{
			assertNotNull(sig.appendGangQuFilter());
			sig.appendValidElement(ElementName.GANGINFO, null);
			fail("want exception");
		}
		catch (final JDFException x)
		{
			//
		}
		assertNotNull(sig.getGangInfo(1));
		assertNotNull(sig.getGangInfo(-1));
		assertNull(sig.getGangInfo(5));
	}

	/**
	 *
	 */
	@Test
	void testGetValidTypeVector()
	{
		final JDFMessage sig = jmf.appendMessageElement(EnumFamily.Signal, null);
		assertTrue(sig.getValidTypeVector(ElementName.NOTIFICATIONFILTER, 0).contains(EnumType.Notification));
	}

	/**
	 *
	 */
	@Test
	void testOptionalElements()
	{
		final JDFMessage sig = jmf.appendMessageElement(EnumFamily.Signal, EnumType.Notification);
		assertTrue(sig.optionalElements().contains(ElementName.NOTIFICATIONFILTER));
	}

	/**
	 *
	 */
	@Test
	void testAbortQEParams()
	{
		final JDFCommand m = (JDFCommand) jmf.appendMessageElement(EnumFamily.Command, EnumType.AbortQueueEntry);
		assertNotNull(m.appendValidElement(ElementName.ABORTQUEUEENTRYPARAMS, null));
	}

	/**
	 *
	 */
	@Test
	void testAbortQEParams2()
	{
		final JDFCommand m = (JDFCommand) jmf.appendMessageElement(EnumFamily.Command, EnumType.AbortQueueEntry);
		assertNotNull(m.appendAbortQueueEntryParams());
		try
		{
			m.appendAbortQueueEntryParams();
			fail("only one AbortQueueEntryParam");
		}
		catch (final Exception e)
		{
			// nop
		}
		assertEquals(m.getAbortQueueEntryParams(), m.getCreateAbortQueueEntryParams());
	}

	/**
	 *
	 */
	@Test
	void testGetDeviceInfo()
	{
		final JDFSignal sig = (JDFSignal) jmf.appendMessageElement(EnumFamily.Signal, EnumType.Status);
		assertNull(sig.getDeviceInfo("DevID"));
		final JDFDeviceInfo d1 = sig.getCreateDeviceInfo("DevID");
		final JDFDeviceInfo d2 = sig.getCreateDeviceInfo("DevID2");
		assertNotNull(d1);
		assertNotNull(d2);
		assertNotSame(d1, d2);
		assertEquals(d2, sig.getDeviceInfo("DevID2"));

		final JDFResponse res = (JDFResponse) jmf.appendMessageElement(EnumFamily.Response, EnumType.Status);
		assertNull(res.getDeviceInfo("DevID"));
		final JDFDeviceInfo d21 = res.getCreateDeviceInfo("DevID");
		final JDFDeviceInfo d22 = res.getCreateDeviceInfo("DevID2");
		assertNotNull(d21);
		assertNotNull(d22);
		assertNotSame(d21, d22);

		final JDFAcknowledge ack = (JDFAcknowledge) jmf.appendMessageElement(EnumFamily.Acknowledge, EnumType.Status);
		assertNull(ack.getDeviceInfo("DevID"));
		final JDFDeviceInfo d31 = ack.getCreateDeviceInfo("DevID");
		final JDFDeviceInfo d32 = ack.getCreateDeviceInfo("DevID2");
		assertNotNull(d31);
		assertNotNull(d32);
		assertNotSame(d31, d32);

		final JDFAcknowledge ack2 = (JDFAcknowledge) jmf.appendMessageElement(EnumFamily.Acknowledge, EnumType.Resource);

		try
		{
			assertNull(ack2.getDeviceInfo("DevID"));
			fail("nogo here");
		}
		catch (final JDFException x)
		{
			// nop
		}

	}

	/**
	 *
	 */
	@Test
	void testAppendValidElementStrictLax()
	{
		final JDFSignal sig = (JDFSignal) jmf.appendMessageElement(EnumFamily.Signal, EnumType.UpdateJDF);
		assertNotNull(sig.appendValidElement(ElementName.UPDATEJDFCMDPARAMS, null));

		try
		{
			sig.appendStatusQuParams();
			fail("strict checking must fail");
		}
		catch (final Exception x)
		{
			// nop
		}
		try
		{
			sig.appendUpdateJDFCmdParams();
			fail("strict checking must fail on 2nd element");
		}
		catch (final Exception x)
		{
			// nop
		}
		// set to lax
		JDFMessage.setStrictValidation(false);
		assertNotNull(sig.appendStatusQuParams(), "appending anything goes");
		assertNotNull(sig.appendUpdateJDFCmdParams(), "appending anything goes");
		// now set back to strict
		JDFMessage.setStrictValidation(true);
		try
		{
			sig.appendStatusQuParams();
			fail("strict checking must fail");
		}
		catch (final Exception x)
		{
			// nop
		}
		try
		{
			sig.appendUpdateJDFCmdParams();
			fail("strict checking must fail on 2nd element");
		}
		catch (final Exception x)
		{
			// nop
		}
	}

	// //////////////////////////////////////////////////////////////////////////
	// /
	/**
	 *
	 */
	@Test
	void testGetInvalidAttributes()
	{
		final JDFSignal sig = (JDFSignal) jmf.appendMessageElement(EnumFamily.Signal, EnumType.UpdateJDF);
		assertNotNull(sig.appendValidElement(ElementName.UPDATEJDFCMDPARAMS, null));
		assertFalse(sig.getInvalidAttributes(EnumValidationLevel.Complete, true, 999).contains(AttributeName.XSITYPE));
		sig.setAttribute("Type", EnumType.AbortQueueEntry.getName());
		assertTrue(sig.getInvalidAttributes(EnumValidationLevel.Complete, true, 999).contains(AttributeName.XSITYPE));
	}

	// //////////////////////////////////////////////////////////////////////////
	// /

	/**
	 *
	 */
	@Test
	void testModifyNode()
	{
		final JDFSignal sig = (JDFSignal) jmf.appendMessageElement(EnumFamily.Signal, EnumType.ModifyNode);
		final JDFModifyNodeCmdParams mnp = sig.appendModifyNodeCmdParams();
		assertNotNull(mnp);
		JDFModifyNodeCmdParams mnp2 = sig.getModifyNodeCmdParams();
		assertEquals(mnp, mnp2);
		mnp2 = sig.getCreateModifyNodeCmdParams();
		assertEquals(mnp, mnp2);
		try
		{
			sig.appendModifyNodeCmdParams();
			fail("oops");
		}
		catch (final JDFException e)
		{
			// nop
		}
	}

	// //////////////////////////////////////////////////////////////////////////
	// /

	/**
	 *
	 */
	@Test
	void testUpdateJDF()
	{
		final JDFCommand command = (JDFCommand) jmf.appendMessageElement(EnumFamily.Command, EnumType.UpdateJDF);
		final JDFUpdateJDFCmdParams ujn = command.appendUpdateJDFCmdParams();
		assertNotNull(ujn);
		JDFUpdateJDFCmdParams ujn2 = command.getUpdateJDFCmdParams();
		assertEquals(ujn, ujn2);
		ujn2 = command.getCreateUpdateJDFCmdParams();
		assertEquals(ujn, ujn2);
		try
		{
			command.appendUpdateJDFCmdParams();
			fail("oops");
		}
		catch (final JDFException e)
		{
			// nop
		}
	}

	// //////////////////////////////////////////////////////////////////////////
	// /

	/**
	 * test for the validity checks of KnownSubscriptions (JDF 1.4)
	 */
	@Test
	void testKnownSubscriptions()
	{
		final JDFSignal sig = jmf.appendSignal(EnumType.KnownSubscriptions);
		final JDFCommand cmd = jmf.appendCommand(EnumType.KnownSubscriptions);
		assertNotNull(sig.appendSubscriptionFilter());
		assertNotNull(sig.appendSubscriptionInfo());
		assertNotNull(sig.appendSubscriptionInfo());
		assertNotSame(sig.appendSubscriptionInfo(), sig.appendSubscriptionInfo());
		try
		{
			sig.appendSubscriptionFilter();
			fail("one is enough");
		}
		catch (final JDFException x)
		{
			// nop
		}
		try
		{
			cmd.appendSubscriptionFilter();
			fail("not a command");
		}
		catch (final JDFException x)
		{
			// nop
		}
		try
		{
			cmd.appendSubscriptionInfo();
			fail("not a command");
		}
		catch (final JDFException x)
		{
			// nop
		}

	}

	/**
	 *
	 */
	@Test
	void testSetType()
	{
		final JDFCommand command = (JDFCommand) jmf.appendMessageElement(EnumFamily.Command, EnumType.UpdateJDF);
		assertEquals(command.getXSIType(), "CommandUpdateJDF");
		command.setType("foo:bar");
		assertNull(command.getXSIType());
		assertEquals(command.getType(), "foo:bar");
	}

	/**
	 * @throws Exception
	 */
	@BeforeEach
	public void setUp() throws Exception
	{
		jmf = new JDFDoc(ElementName.JMF).getJMFRoot();
	}

	/**
	 *
	 */
	@Test
	void testSenderID()
	{
		final JDFCommand command = (JDFCommand) jmf.appendMessageElement(EnumFamily.Command, EnumType.UpdateJDF);
		assertEquals(jmf.getSenderID(), command.getSenderID());
		command.setSenderID("foo:bar");
		assertEquals(command.getSenderID(), "foo:bar");
	}

	/**
	 *
	 */
	@Test
	void testGetTime()
	{
		final JDFCommand command = (JDFCommand) jmf.appendMessageElement(EnumFamily.Command, EnumType.UpdateJDF);
		assertEquals(jmf.getTimeStamp(), command.getTime());
		final JDFDate newDate = new JDFDate(10000000);
		command.setTime(newDate);
		assertEquals(command.getTime(), newDate);
	}

	/**
	 * validation slows down append and get by ~ a factor of 2
	 */
	@Test
	void testValidatePerformance()
	{
		long t0 = System.currentTimeMillis();
		for (int l = 0; l < 2; l++)
		{
			final boolean strict = l == 0;
			JDFMessage.setStrictValidation(strict);
			for (int i = 0; i < 10000; i++)
			{
				jmf = JDFJMF.createJMF(EnumFamily.Signal, EnumType.Status);
				final JDFSignal s = jmf.getSignal(0);
				for (int ii = 0; ii < 10; ii++)
				{
					s.appendDeviceInfo();
					assertNotNull(s.getDeviceInfo(ii));
				}
				s.appendStatusQuParams();
			}
			final long t1 = System.currentTimeMillis();
			System.out.println((strict ? "strict t val: " : "loose t val: ") + ((t1 - t0) * 0.001));
			t0 = t1;
		}
		JDFMessage.setStrictValidation(true);

	}

	/**
	 *
	 */
	@Test
	void testCreateResponse()
	{
		final JDFCommand command = (JDFCommand) jmf.appendMessageElement(EnumFamily.Command, EnumType.UpdateJDF);
		assertEquals(command.getXSIType(), "CommandUpdateJDF");
		command.setType("foo:bar");
		assertNull(command.getXSIType());
		assertEquals(command.getType(), "foo:bar");
		final JDFJMF resp = command.createResponse();
		final JDFResponse response = resp.getResponse(0);
		assertEquals(response, resp.getMessageElement(null, null, 0));
		assertEquals(response.getType(), "foo:bar");
		assertEquals(response.getrefID(), command.getID());
		assertEquals(0, response.getReturnCode());
		assertEquals(0, response.getIntAttribute(AttributeName.RETURNCODE, null, -1));

	}

	/**
	 *
	 */
	@Test
	void testCreateSignal()
	{
		final JDFCommand command = (JDFCommand) jmf.appendMessageElement(EnumFamily.Command, EnumType.UpdateJDF);
		assertEquals(command.getXSIType(), "CommandUpdateJDF");
		command.setType("foo:bar");
		assertNull(command.getXSIType());
		assertEquals(command.getType(), "foo:bar");
		final JDFJMF sig = command.createSignal();
		final JDFSignal signal = sig.getSignal(0);
		assertEquals(signal, sig.getMessageElement(null, null, 0));
		assertEquals(signal.getType(), "foo:bar");
		assertEquals(signal.getrefID(), command.getID());

	}

	/**
	 *
	 */
	@Test
	void testCreateSignalVersion()
	{
		final JDFCommand command = (JDFCommand) jmf.appendMessageElement(EnumFamily.Command, EnumType.UpdateJDF);
		jmf.setVersion(EnumVersion.Version_1_3);
		assertEquals(command.getXSIType(), "CommandUpdateJDF");
		command.setType("foo:bar");
		assertNull(command.getXSIType());
		assertEquals(command.getType(), "foo:bar");
		final JDFJMF sig = command.createSignal();
		final JDFSignal signal = sig.getSignal(0);
		assertEquals(EnumVersion.Version_1_3, signal.getVersion(false));
		assertEquals(EnumVersion.Version_1_3, signal.getJMFRoot().getVersion(true));

	}

	/**
	 * @see junit.framework.TestCase#toString()
	 */
	@Override
	public String toString()
	{
		return jmf == null ? "JMFMessageTest - null" : "JMFMessageTest:\n" + jmf;
	}

}