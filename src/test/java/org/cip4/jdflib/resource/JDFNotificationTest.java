/*
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
package org.cip4.jdflib.resource;

import org.cip4.jdflib.JDFTestCaseBase;
import org.cip4.jdflib.auto.JDFAutoNotification.EnumClass;
import org.cip4.jdflib.core.ElementName;
import org.cip4.jdflib.core.JDFDoc;
import org.cip4.jdflib.core.JDFElement.EnumValidationLevel;
import org.cip4.jdflib.jmf.JDFJMF;
import org.cip4.jdflib.resource.JDFNotification.EnumNotificationDetails;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

/**
 * @author Dr. Rainer Prosi, Heidelberger Druckmaschinen AG 13.11.2008
 */
public class JDFNotificationTest extends JDFTestCaseBase
{

	JDFNotification n;

	/**
	 *
	 */
	@Test
	public void testappendNotificationDetails()
	{
		final JDFMilestone ms = n.appendMilestone();
		Assertions.assertEquals(n.getNotificationDetailsType(), EnumNotificationDetails.Milestone);
		Assertions.assertNotNull(ms);
		Assertions.assertEquals(ms.getLocalName(), ElementName.MILESTONE);
		Assertions.assertNull(n.appendBarcode());
		Assertions.assertNull(n.getCreateBarcode());
	}

	/**
	 *
	 */
	@Test
	public void testgetNotificationDetails()
	{
		final JDFBarcode ms = n.appendBarcode();
		Assertions.assertEquals(n.getNotificationDetailsType(), EnumNotificationDetails.Barcode);
		Assertions.assertNotNull(ms);
		Assertions.assertEquals(ms.getLocalName(), ElementName.BARCODE);
		Assertions.assertEquals(n.getNotificationDetails(), ms);
	}

	/**
	 *
	 */
	@Test
	public void testToSignalJMF()
	{
		n.setClass(EnumClass.Information);
		n.getOwnerDocument_JDFElement().write2File(sm_dirTestDataTemp + "notification_in.xml", 2, false);
		Assertions.assertTrue(n.isValid(EnumValidationLevel.Complete));
		final JDFJMF jmf = n.toSignalJMF();
		jmf.getOwnerDocument_JDFElement().write2File(sm_dirTestDataTemp + "notification.xml", 2, false);
		Assertions.assertTrue(jmf.isValid(EnumValidationLevel.Complete));
	}

	/**
	 *
	 */
	@Test
	public void testMatches()
	{
		Assertions.assertTrue(n.matches(n));
		final JDFNotification n2 = (JDFNotification) new JDFDoc("Notification").getRoot();
		Assertions.assertTrue(n.matches(n2));
		n2.setModuleID("m1");
		Assertions.assertFalse(n.matches(n2));
	}

	/**
	 *
	 */
	@Test
	public void testgetCreateNotificationDetails()
	{
		final JDFSystemTimeSet ms = n.getCreateSystemTimeSet();
		Assertions.assertEquals(n.getNotificationDetailsType(), EnumNotificationDetails.SystemTimeSet);
		Assertions.assertNotNull(ms);
		Assertions.assertEquals(ms.getLocalName(), ElementName.SYSTEMTIMESET);
		Assertions.assertEquals(n.getNotificationDetails(), ms);
		Assertions.assertNull(n.getCreateBarcode());
	}

	/**
	 *
	 */
	@Test
	public void testSetCommentText()
	{
		n.setCommentText("fooBar");
		Assertions.assertEquals(n.getCommentText(), "fooBar");
		n.setCommentText("fooBar");
		Assertions.assertEquals(n.getCommentText(), "fooBar");
	}

	/**
	 *
	 */
	@Test
	public void testsetEvent()
	{
		JDFEvent e = n.setEvent("id", "value", "bullshit");
		Assertions.assertEquals(e.getEventID(), "id");
		Assertions.assertEquals(e.getEventValue(), "value");
		Assertions.assertEquals(n.getComment(0).getText(), "bullshit");
		final JDFEvent ee = n.setEvent("id2", "value2", "bullshit2");
		Assertions.assertEquals(ee, e);
		e = (JDFEvent) n.getNotificationDetails();
		Assertions.assertEquals(e.getEventID(), "id2");
		Assertions.assertEquals(e.getEventValue(), "value2");
		Assertions.assertEquals(n.getComment(0).getText(), "bullshit2");
		e = n.getCreateEvent();
		Assertions.assertEquals(e.getEventID(), "id2");
		Assertions.assertEquals(e.getEventValue(), "value2");
		Assertions.assertEquals(n.getComment(0).getText(), "bullshit2");
	}

	/**
	 *
	 */
	@Test
	public void testGetEvent()
	{
		final JDFEvent e = n.setEvent("id", "value", "bullshit");
		Assertions.assertEquals(e, n.getEvent());
	}

	/**
	 *
	 */
	@Test
	public void testValidEvent()
	{
		final JDFEvent e = n.setEvent("id", "value", "bullshit");
		Assertions.assertEquals(e, n.getEvent());
		Assertions.assertTrue(e.isValid(EnumValidationLevel.Complete));
	}

	/**
	 *
	 */
	@Test
	public void testGetError()
	{
		final JDFError e = n.appendError();
		Assertions.assertEquals(e, n.getError());
	}

	/**
	 *
	 */
	@Test
	public void testGetErrorBad()
	{
		n.appendFCNKey();
		Assertions.assertNull(n.getError());
	}

	/**
	 *
	 */
	@Test
	public void testGetMilestone()
	{
		final JDFMilestone ms = n.appendMilestone();
		ms.setMilestoneType("ms");
		Assertions.assertEquals(ms, n.getMilestone());
	}

	/**
	 *
	 */
	@Test
	public void testGetCreateEvent()
	{
		Assertions.assertNotNull(n.getCreateEvent());
	}

	/**
	 *
	 */
	@Test
	public void testGetCreateMilestone()
	{
		Assertions.assertNotNull(n.getCreateMilestone());
	}

	/**
	 *
	 */
	@Test
	public void testGetCreateMilestoneBad()
	{
		n.appendBarcode();
		Assertions.assertNull(n.getCreateMilestone());
	}

	/**
	 *
	 */
	@Test
	public void testGetMilestoneBad()
	{
		n.appendBarcode();
		Assertions.assertNull(n.getMilestone());
	}

	/**
	 *
	 */
	@Test
	public void testGetFCNBad()
	{
		n.appendBarcode();
		Assertions.assertNull(n.getFCNKey());
	}

	/**
	 *
	 */
	@Test
	public void testGetEventBad()
	{
		n.appendBarcode();
		Assertions.assertNull(n.getEvent());
	}

	/**
	 *
	 */
	@Test
	public void testGetCreateEventBad()
	{
		n.appendBarcode();
		Assertions.assertNull(n.getCreateEvent());
	}

	/**
	 * @see junit.framework.TestCase#setUp()
	 */
	@Override
	public void setUp() throws Exception
	{
		super.setUp();
		n = (JDFNotification) new JDFDoc("Notification").getRoot();
	}
}
