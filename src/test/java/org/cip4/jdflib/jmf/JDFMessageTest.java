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
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

/**
 * @author Dr. Rainer Prosi, Heidelberger Druckmaschinen AG
 *
 *         far before May 17, 2009
 */
public class JDFMessageTest {
	private JDFJMF jmf;

	/**
	 *
	 *
	 */
	@Test
	public void testEnumFamily()
	{
		Assert.assertEquals(EnumFamily.getEnumMap().get("Signal"), EnumFamily.Signal);
		Assert.assertNotNull(EnumFamily.getEnumList().get(2));
	}

	/**
	 *
	 *
	 */
	@Test
	public void testInit()
	{
		final JDFJMF jmf = JDFJMF.createJMF(EnumFamily.Signal, EnumType.Status);
		final JDFSignal s = jmf.getSignal(0);
		Assert.assertNotNull(StringUtil.getNonEmpty(s.getID()));
	}

	/**
	 *
	 */
	@Test
	public void testIsValidMessageElement()
	{
		final JDFSignal sig = (JDFSignal) jmf.appendMessageElement(EnumFamily.Signal, EnumType.UpdateJDF);
		Assert.assertTrue(sig.isValidMessageElement(ElementName.UPDATEJDFCMDPARAMS, 0));
		Assert.assertFalse(sig.isValidMessageElement(ElementName.MODIFYNODECMDPARAMS, 0));

		final JDFResponse resp = (JDFResponse) jmf.appendMessageElement(EnumFamily.Response, EnumType.RepeatMessages);
		Assert.assertTrue(resp.isValidMessageElement(ElementName.SIGNAL, 3));
		Assert.assertTrue(resp.isValidMessageElement(ElementName.REGISTRATION, 3));
		Assert.assertFalse(resp.isValidMessageElement(ElementName.QUEUE, 0));

		final JDFRegistration reg = (JDFRegistration) jmf.appendMessageElement(EnumFamily.Registration, EnumType.RepeatMessages);
		Assert.assertFalse(reg.isValidMessageElement(ElementName.SIGNAL, 3));

		final JDFQuery q = (JDFQuery) jmf.appendMessageElement(EnumFamily.Query, EnumType.KnownSubscriptions);
		Assert.assertTrue(q.isValidMessageElement(ElementName.SUBSCRIPTIONFILTER, 0));
		Assert.assertFalse(q.isValidMessageElement(ElementName.SUBSCRIPTIONINFO, 0));

		final JDFCommand c = (JDFCommand) jmf.appendMessageElement(EnumFamily.Command, EnumType.RequestForAuthentication);
		Assert.assertTrue(c.isValidMessageElement(ElementName.AUTHENTICATIONCMDPARAMS, 0));
		Assert.assertFalse(c.isValidMessageElement(ElementName.AUTHENTICATIONQUPARAMS, 0));

	}

	/**
	 *
	 */
	@Test
	public void testAppendValidElement()
	{
		final JDFSignal sig = (JDFSignal) jmf.appendMessageElement(EnumFamily.Signal, EnumType.UpdateJDF);
		Assert.assertNotNull(sig.appendValidElement(ElementName.UPDATEJDFCMDPARAMS, null));
	}

	/**
	 *
	 */
	@Test
	public void testAppendNotificationFilter()
	{
		final JDFSignal sig = (JDFSignal) jmf.appendMessageElement(EnumFamily.Signal, EnumType.Notification);
		Assert.assertNotNull(sig.appendValidElement(ElementName.NOTIFICATIONFILTER, null));
	}

	/**
	 *
	 */
	@Test
	public void testAppendGangInfoQu()
	{
		final JDFQuery sig = (JDFQuery) jmf.appendMessageElement(EnumFamily.Query, EnumType.GangStatus);
		Assert.assertNotNull(sig.appendGangQuFilter());
		try
		{
			sig.appendValidElement(ElementName.GANGINFO, null);
			Assert.fail("want exception");
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
	public void testAppendGangInfo()
	{
		final JDFResponse sig = (JDFResponse) jmf.appendMessageElement(EnumFamily.Response, EnumType.GangStatus);
		sig.appendGangInfo();
		sig.appendGangInfo();
		try
		{
			Assert.assertNotNull(sig.appendGangQuFilter());
			sig.appendValidElement(ElementName.GANGINFO, null);
			Assert.fail("want exception");
		}
		catch (final JDFException x)
		{
			//
		}
		Assert.assertNotNull(sig.getGangInfo(1));
		Assert.assertNotNull(sig.getGangInfo(-1));
		Assert.assertNull(sig.getGangInfo(5));
	}

	/**
	 *
	 */
	@Test
	public void testGetValidTypeVector()
	{
		final JDFMessage sig = jmf.appendMessageElement(EnumFamily.Signal, null);
		Assert.assertTrue(sig.getValidTypeVector(ElementName.NOTIFICATIONFILTER, 0).contains(EnumType.Notification));
	}

	/**
	 *
	 */
	@Test
	public void testOptionalElements()
	{
		final JDFMessage sig = jmf.appendMessageElement(EnumFamily.Signal, EnumType.Notification);
		Assert.assertTrue(sig.optionalElements().contains(ElementName.NOTIFICATIONFILTER));
	}

	/**
	 *
	 */
	@Test
	public void testAbortQEParams()
	{
		final JDFCommand m = (JDFCommand) jmf.appendMessageElement(EnumFamily.Command, EnumType.AbortQueueEntry);
		Assert.assertNotNull(m.appendValidElement(ElementName.ABORTQUEUEENTRYPARAMS, null));
	}

	/**
	 *
	 */
	@Test
	public void testAbortQEParams2()
	{
		final JDFCommand m = (JDFCommand) jmf.appendMessageElement(EnumFamily.Command, EnumType.AbortQueueEntry);
		Assert.assertNotNull(m.appendAbortQueueEntryParams());
		try
		{
			m.appendAbortQueueEntryParams();
			Assert.fail("only one bortQueueEntryParam");
		}
		catch (final Exception e)
		{
			// nop
		}
		Assert.assertEquals(m.getAbortQueueEntryParams(), m.getCreateAbortQueueEntryParams());
	}

	/**
	 *
	 */
	@Test
	public void testGetDeviceInfo()
	{
		final JDFSignal sig = (JDFSignal) jmf.appendMessageElement(EnumFamily.Signal, EnumType.Status);
		Assert.assertNull(sig.getDeviceInfo("DevID"));
		final JDFDeviceInfo d1 = sig.getCreateDeviceInfo("DevID");
		final JDFDeviceInfo d2 = sig.getCreateDeviceInfo("DevID2");
		Assert.assertNotNull(d1);
		Assert.assertNotNull(d2);
		Assert.assertNotSame(d1, d2);
		Assert.assertEquals(d2, sig.getDeviceInfo("DevID2"));

		final JDFResponse res = (JDFResponse) jmf.appendMessageElement(EnumFamily.Response, EnumType.Status);
		Assert.assertNull(res.getDeviceInfo("DevID"));
		final JDFDeviceInfo d21 = res.getCreateDeviceInfo("DevID");
		final JDFDeviceInfo d22 = res.getCreateDeviceInfo("DevID2");
		Assert.assertNotNull(d21);
		Assert.assertNotNull(d22);
		Assert.assertNotSame(d21, d22);

		final JDFAcknowledge ack = (JDFAcknowledge) jmf.appendMessageElement(EnumFamily.Acknowledge, EnumType.Status);
		Assert.assertNull(ack.getDeviceInfo("DevID"));
		final JDFDeviceInfo d31 = ack.getCreateDeviceInfo("DevID");
		final JDFDeviceInfo d32 = ack.getCreateDeviceInfo("DevID2");
		Assert.assertNotNull(d31);
		Assert.assertNotNull(d32);
		Assert.assertNotSame(d31, d32);

		final JDFAcknowledge ack2 = (JDFAcknowledge) jmf.appendMessageElement(EnumFamily.Acknowledge, EnumType.Resource);

		try
		{
			Assert.assertNull(ack2.getDeviceInfo("DevID"));
			Assert.fail("nogo here");
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
	public void testAppendValidElementStrictLax()
	{
		final JDFSignal sig = (JDFSignal) jmf.appendMessageElement(EnumFamily.Signal, EnumType.UpdateJDF);
		Assert.assertNotNull(sig.appendValidElement(ElementName.UPDATEJDFCMDPARAMS, null));

		try
		{
			sig.appendStatusQuParams();
			Assert.fail("strict checking must fail");
		}
		catch (final Exception x)
		{
			// nop
		}
		try
		{
			sig.appendUpdateJDFCmdParams();
			Assert.fail("strict checking must fail on 2nd element");
		}
		catch (final Exception x)
		{
			// nop
		}
		// set to lax
		JDFMessage.setStrictValidation(false);
		Assert.assertNotNull("appending anything goes", sig.appendStatusQuParams());
		Assert.assertNotNull("appending anything goes", sig.appendUpdateJDFCmdParams());
		// now set back to strict
		JDFMessage.setStrictValidation(true);
		try
		{
			sig.appendStatusQuParams();
			Assert.fail("strict checking must fail");
		}
		catch (final Exception x)
		{
			// nop
		}
		try
		{
			sig.appendUpdateJDFCmdParams();
			Assert.fail("strict checking must fail on 2nd element");
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
	public void testGetInvalidAttributes()
	{
		final JDFSignal sig = (JDFSignal) jmf.appendMessageElement(EnumFamily.Signal, EnumType.UpdateJDF);
		Assert.assertNotNull(sig.appendValidElement(ElementName.UPDATEJDFCMDPARAMS, null));
		Assert.assertFalse(sig.getInvalidAttributes(EnumValidationLevel.Complete, true, 999).contains(AttributeName.XSITYPE));
		sig.setAttribute("Type", EnumType.AbortQueueEntry.getName());
		Assert.assertTrue(sig.getInvalidAttributes(EnumValidationLevel.Complete, true, 999).contains(AttributeName.XSITYPE));
	}

	// //////////////////////////////////////////////////////////////////////////
	// /

	/**
	 *
	 */
	@Test
	public void testModifyNode()
	{
		final JDFSignal sig = (JDFSignal) jmf.appendMessageElement(EnumFamily.Signal, EnumType.ModifyNode);
		final JDFModifyNodeCmdParams mnp = sig.appendModifyNodeCmdParams();
		Assert.assertNotNull(mnp);
		JDFModifyNodeCmdParams mnp2 = sig.getModifyNodeCmdParams();
		Assert.assertEquals(mnp, mnp2);
		mnp2 = sig.getCreateModifyNodeCmdParams();
		Assert.assertEquals(mnp, mnp2);
		try
		{
			sig.appendModifyNodeCmdParams();
			Assert.fail("oops");
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
	public void testUpdateJDF()
	{
		final JDFCommand command = (JDFCommand) jmf.appendMessageElement(EnumFamily.Command, EnumType.UpdateJDF);
		final JDFUpdateJDFCmdParams ujn = command.appendUpdateJDFCmdParams();
		Assert.assertNotNull(ujn);
		JDFUpdateJDFCmdParams ujn2 = command.getUpdateJDFCmdParams();
		Assert.assertEquals(ujn, ujn2);
		ujn2 = command.getCreateUpdateJDFCmdParams();
		Assert.assertEquals(ujn, ujn2);
		try
		{
			command.appendUpdateJDFCmdParams();
			Assert.fail("oops");
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
	public void testKnownSubscriptions()
	{
		final JDFSignal sig = jmf.appendSignal(EnumType.KnownSubscriptions);
		final JDFCommand cmd = jmf.appendCommand(EnumType.KnownSubscriptions);
		Assert.assertNotNull(sig.appendSubscriptionFilter());
		Assert.assertNotNull(sig.appendSubscriptionInfo());
		Assert.assertNotNull(sig.appendSubscriptionInfo());
		Assert.assertNotSame(sig.appendSubscriptionInfo(), sig.appendSubscriptionInfo());
		try
		{
			sig.appendSubscriptionFilter();
			Assert.fail("one is enough");
		}
		catch (final JDFException x)
		{
			// nop
		}
		try
		{
			cmd.appendSubscriptionFilter();
			Assert.fail("not a command");
		}
		catch (final JDFException x)
		{
			// nop
		}
		try
		{
			cmd.appendSubscriptionInfo();
			Assert.fail("not a command");
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
	public void testSetType()
	{
		final JDFCommand command = (JDFCommand) jmf.appendMessageElement(EnumFamily.Command, EnumType.UpdateJDF);
		Assert.assertEquals(command.getXSIType(), "CommandUpdateJDF");
		command.setType("foo:bar");
		Assert.assertNull(command.getXSIType());
		Assert.assertEquals(command.getType(), "foo:bar");
	}

	/**
	 * @throws Exception
	 */
	@Before
    public void setUp() throws Exception
	{
        jmf = new JDFDoc(ElementName.JMF).getJMFRoot();
	}

	/**
	 *
	 */
	@Test
	public void testSenderID()
	{
		final JDFCommand command = (JDFCommand) jmf.appendMessageElement(EnumFamily.Command, EnumType.UpdateJDF);
		Assert.assertEquals(jmf.getSenderID(), command.getSenderID());
		command.setSenderID("foo:bar");
		Assert.assertEquals(command.getSenderID(), "foo:bar");
	}

	/**
	 *
	 */
	@Test
	public void testGetTime()
	{
		final JDFCommand command = (JDFCommand) jmf.appendMessageElement(EnumFamily.Command, EnumType.UpdateJDF);
		Assert.assertEquals(jmf.getTimeStamp(), command.getTime());
		final JDFDate newDate = new JDFDate(10000000);
		command.setTime(newDate);
		Assert.assertEquals(command.getTime(), newDate);
	}

	/**
	 * validation slows down append and get by ~ a factor of 2
	 */
	@Test
	public void testValidatePerformance()
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
					Assert.assertNotNull(s.getDeviceInfo(ii));
				}
				s.appendStatusQuParams();
			}
			final long t1 = System.currentTimeMillis();
			System.out.println((strict ? "strict t val: " : "loose t val: ") + ((t1 - t0) * 0.001));
			t0 = t1;
		}
		JDFMessage.setStrictValidation(true);

	}

	// //////////////////////////////////////////////////////////////////////////
	// /

	/**
	 *
	 */
	@Test
	public void testCreateResponse()
	{
		final JDFCommand command = (JDFCommand) jmf.appendMessageElement(EnumFamily.Command, EnumType.UpdateJDF);
		Assert.assertEquals(command.getXSIType(), "CommandUpdateJDF");
		command.setType("foo:bar");
		Assert.assertNull(command.getXSIType());
		Assert.assertEquals(command.getType(), "foo:bar");
		final JDFJMF resp = command.createResponse();
		final JDFResponse response = resp.getResponse(0);
		Assert.assertEquals(response, resp.getMessageElement(null, null, 0));
		Assert.assertEquals(response.getType(), "foo:bar");
		Assert.assertEquals(response.getrefID(), command.getID());

	}

	/**
	 *
	 */
	@Test
	public void testCreateSignal()
	{
		final JDFCommand command = (JDFCommand) jmf.appendMessageElement(EnumFamily.Command, EnumType.UpdateJDF);
		Assert.assertEquals(command.getXSIType(), "CommandUpdateJDF");
		command.setType("foo:bar");
		Assert.assertNull(command.getXSIType());
		Assert.assertEquals(command.getType(), "foo:bar");
		final JDFJMF sig = command.createSignal();
		final JDFSignal signal = sig.getSignal(0);
		Assert.assertEquals(signal, sig.getMessageElement(null, null, 0));
		Assert.assertEquals(signal.getType(), "foo:bar");
		Assert.assertEquals(signal.getrefID(), command.getID());

	}

	/**
	 *
	 */
	@Test
	public void testCreateSignalVersion()
	{
		final JDFCommand command = (JDFCommand) jmf.appendMessageElement(EnumFamily.Command, EnumType.UpdateJDF);
		jmf.setVersion(EnumVersion.Version_1_3);
		Assert.assertEquals(command.getXSIType(), "CommandUpdateJDF");
		command.setType("foo:bar");
		Assert.assertNull(command.getXSIType());
		Assert.assertEquals(command.getType(), "foo:bar");
		final JDFJMF sig = command.createSignal();
		final JDFSignal signal = sig.getSignal(0);
		Assert.assertEquals(EnumVersion.Version_1_3, signal.getVersion(false));
		Assert.assertEquals(EnumVersion.Version_1_3, signal.getJMFRoot().getVersion(true));

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