/*
 * The CIP4 Software License, Version 1.0
 *
 *
 * Copyright (c) 2001-2008 The International Cooperation for the Integration of
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
import org.cip4.jdflib.core.KElement.EnumValidationLevel;
import org.cip4.jdflib.jmf.JDFJMF;
import org.cip4.jdflib.resource.JDFNotification.EnumNotificationDetails;

/**
 * @author Dr. Rainer Prosi, Heidelberger Druckmaschinen AG 13.11.2008
 */
public class JDFNotificationTest extends JDFTestCaseBase
{

	JDFNotification n;

	/**
	 * 
	 */
	public void testappendNotificationDetails()
	{
		final JDFMilestone ms = n.appendMilestone();
		assertEquals(n.getNotificationDetailsType(), EnumNotificationDetails.Milestone);
		assertNotNull(ms);
		assertEquals(ms.getLocalName(), ElementName.MILESTONE);
		assertNull(n.appendBarcode());
		assertNull(n.getCreateBarcode());
	}

	/**
	 * 
	 */
	public void testgetNotificationDetails()
	{
		final JDFBarcode ms = n.appendBarcode();
		assertEquals(n.getNotificationDetailsType(), EnumNotificationDetails.Barcode);
		assertNotNull(ms);
		assertEquals(ms.getLocalName(), ElementName.BARCODE);
		assertEquals(n.getNotificationDetails(), ms);
	}

	/**
	 * 
	 */
	public void testToSignalJMF()
	{
		n.setClass(EnumClass.Information);
		assertTrue(n.isValid(EnumValidationLevel.Complete));
		final JDFJMF jmf = n.toSignalJMF();
		jmf.getOwnerDocument_JDFElement().write2File(sm_dirTestDataTemp + "notification.xml", 2, false);
		assertTrue(jmf.isValid(EnumValidationLevel.Complete));
	}

	/**
	 * 
	 */
	public void testgetCreateNotificationDetails()
	{
		final JDFSystemTimeSet ms = n.getCreateSystemTimeSet();
		assertEquals(n.getNotificationDetailsType(), EnumNotificationDetails.SystemTimeSet);
		assertNotNull(ms);
		assertEquals(ms.getLocalName(), ElementName.SYSTEMTIMESET);
		assertEquals(n.getNotificationDetails(), ms);
		assertNull(n.getCreateBarcode());
	}

	/**
	 * 
	 */
	public void testSetCommentText()
	{
		n.setCommentText("fooBar");
		assertEquals(n.getCommentText(), "fooBar");
		n.setCommentText("fooBar");
		assertEquals(n.getCommentText(), "fooBar");
	}

	/**
	 * 
	 */
	public void testsetEvent()
	{
		JDFEvent e = n.setEvent("id", "value", "bullshit");
		assertEquals(e.getEventID(), "id");
		assertEquals(e.getEventValue(), "value");
		assertEquals(n.getComment(0).getText(), "bullshit");
		final JDFEvent ee = n.setEvent("id2", "value2", "bullshit2");
		assertEquals(ee, e);
		e = (JDFEvent) n.getNotificationDetails();
		assertEquals(e.getEventID(), "id2");
		assertEquals(e.getEventValue(), "value2");
		assertEquals(n.getComment(0).getText(), "bullshit2");
		e = n.getCreateEvent();
		assertEquals(e.getEventID(), "id2");
		assertEquals(e.getEventValue(), "value2");
		assertEquals(n.getComment(0).getText(), "bullshit2");
	}

	/**
	 * @see junit.framework.TestCase#setUp()
	 */
	@Override
	protected void setUp() throws Exception
	{
		super.setUp();
		n = (JDFNotification) new JDFDoc("Notification").getRoot();
	}
}
