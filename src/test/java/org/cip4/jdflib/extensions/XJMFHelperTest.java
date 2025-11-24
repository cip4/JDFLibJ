/*
 * The CIP4 Software License, Version 1.0
 *
 *
 * Copyright (c) 2001-2023 The International Cooperation for the Integration of Processes in Prepress, Press and Postpress (CIP4). All rights reserved.
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

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNotSame;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.io.File;

import org.cip4.jdflib.JDFTestCaseBase;
import org.cip4.jdflib.core.KElement;
import org.cip4.jdflib.extensions.MessageHelper.EFamily;
import org.cip4.jdflib.extensions.MessageHelper.EHeader;
import org.cip4.jdflib.extensions.MessageHelper.EType;
import org.cip4.jdflib.jmf.JDFMessage.EnumFamily;
import org.cip4.jdflib.jmf.JDFMessage.EnumType;
import org.cip4.jdflib.util.JDFDate;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**
 * @author Rainer Prosi, Heidelberger Druckmaschinen *
 */
class XJMFHelperTest extends JDFTestCaseBase
{

	/**
	 * @see junit.framework.TestCase#setUp()
	 * @throws Exception if snafu
	 */
	@Override
	@BeforeEach
	public void setUp() throws Exception
	{
		super.setUp();
		KElement.setLongID(false);
	}

	/**
	 *
	 */
	@Test
	void testAddMessage()
	{
		final XJMFHelper theHelper = new XJMFHelper();
		final MessageHelper mh = theHelper.appendMessage(EnumFamily.Command, EnumType.SubmitQueueEntry);
		assertEquals("CommandSubmitQueueEntry", mh.getRoot().getLocalName());
	}

	/**
	 *
	 */
	@Test
	void testAddMessage2()
	{
		final XJMFHelper theHelper = new XJMFHelper();
		final MessageHelper mh = theHelper.appendMessage(EFamily.Command, EType.SubmitQueueEntry);
		assertEquals("CommandSubmitQueueEntry", mh.getRoot().getLocalName());
		assertEquals(mh, theHelper.getMessageHelpers().get(0));
	}

	/**
	 *
	 */
	@Test
	void testGetCreateMessage()
	{
		final XJMFHelper theHelper = new XJMFHelper();
		final MessageHelper mh = theHelper.getCreateMessage(EnumFamily.Command, EnumType.SubmitQueueEntry, 0);
		assertEquals("CommandSubmitQueueEntry", mh.getRoot().getLocalName());
		final MessageHelper mh2 = theHelper.getCreateMessage(EnumFamily.Command, EnumType.SubmitQueueEntry, 0);
		assertEquals(mh, mh2);
		final MessageHelper mh3 = theHelper.getCreateMessage(EnumFamily.Command, EnumType.SubmitQueueEntry, 1);
		assertNotSame(mh3, mh2);
	}

	/**
	 *
	 */
	@Test
	void testGetMessageName()
	{
		final XJMFHelper theHelper = new XJMFHelper();
		final String s = theHelper.getMessageName(EnumFamily.Command, "SubmitQueueEntry");
		assertEquals("CommandSubmitQueueEntry", s);
	}

	/**
	 *
	 */
	@Test
	void testGetMessageNameDeprecated()
	{
		final XJMFHelper theHelper = new XJMFHelper();
		assertThrows(IllegalArgumentException.class, () -> theHelper.getMessageName(EnumFamily.Registration, "SubmitQueueEntry"));
		assertThrows(IllegalArgumentException.class, () -> theHelper.getMessageName(EnumFamily.Acknowledge, "SubmitQueueEntry"));
	}

	/**
	 *
	 */
	@Test
	void testAddMessageNull()
	{
		final XJMFHelper theHelper = new XJMFHelper();
		assertThrows(IllegalArgumentException.class, () -> theHelper.appendMessage(EnumFamily.Command, ""));
	}

	/**
	 *
	 */
	@Test
	void testAddMessageDeprecated()
	{
		final XJMFHelper theHelper = new XJMFHelper();
		assertThrows(IllegalArgumentException.class, () -> theHelper.appendMessage(EnumFamily.Registration, "Resource"));
	}

	/**
	 *
	 */
	@Test
	void testAddAudit()
	{
		final XJMFHelper theHelper = new XJMFHelper();
		assertThrows(IllegalArgumentException.class, () -> theHelper.appendMessage(EFamily.Audit, EType.Resource));
	}

	/**
	 *
	 */
	@Test
	void testHeaderID()
	{
		final XJMFHelper theHelper = new XJMFHelper();
		assertNotNull(theHelper.getXPathValue("Header/@ID"));
	}

	/**
	 *
	 */
	@Test
	void testHeaderID2()
	{
		final XJMFHelper theHelper = new XJMFHelper();
		assertNull(theHelper.getXPathElement("Header/Header"));
	}

	/**
	 *
	 */
	@Test
	void testHeaderDate()
	{
		final XJMFHelper theHelper = new XJMFHelper();
		assertNotNull(JDFDate.createDate(theHelper.getXPathValue("Header/@Time")));
	}

	/**
	 *
	 */
	@Test
	void testHeader()
	{
		final XJMFHelper theHelper = new XJMFHelper();
		theHelper.setHeader(EHeader.refID, "b");
		assertEquals("b", theHelper.getHeader(EHeader.refID));
	}

	/**
	 *
	 */
	@Test
	void testParseFile()
	{
		final XJMFHelper theHelper = new XJMFHelper();
		theHelper.appendMessage(EnumFamily.Command, EnumType.SubmitQueueEntry);
		final File file = new File(sm_dirTestDataTemp + "xjmf.xjmf");
		file.delete();
		theHelper.writeToFile(sm_dirTestDataTemp + "xjmf.xjmf");
		assertTrue(file.exists());
		final XJMFHelper h2 = XJMFHelper.parseFile(file.getAbsolutePath());
		final XJMFHelper h3 = XJMFHelper.parseFile(file);
		assertTrue(h2.isEqual(h3));
	}

	/**
	 *
	 */
	@Test
	void testWriteToFile()
	{
		final XJMFHelper theHelper = new XJMFHelper();
		theHelper.appendMessage(EnumFamily.Command, EnumType.SubmitQueueEntry);
		final File file = new File(sm_dirTestDataTemp + "xjmf.xjmf");
		file.delete();
		theHelper.writeToFile(sm_dirTestDataTemp + "xjmf.xjmf");
		assertTrue(file.exists());
	}

}
