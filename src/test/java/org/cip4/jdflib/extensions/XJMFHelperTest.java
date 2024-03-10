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
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.io.File;

import org.cip4.jdflib.JDFTestCaseBase;
import org.cip4.jdflib.core.KElement;
import org.cip4.jdflib.jmf.JDFMessage.EnumFamily;
import org.cip4.jdflib.jmf.JDFMessage.EnumType;
import org.cip4.jdflib.util.JDFDate;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**
 * @author Rainer Prosi, Heidelberger Druckmaschinen *
 */
public class XJMFHelperTest extends JDFTestCaseBase
{
	XJMFHelper theHelper = null;

	/**
	 * @see junit.framework.TestCase#setUp()
	 * @throws Exception if snafu
	 */
	@Override
	@BeforeEach
	void setUp() throws Exception
	{
		super.setUp();
		KElement.setLongID(false);
		theHelper = new XJMFHelper();
	}

	/**
	 *
	 */
	@Test
	void testAddMessage()
	{
		final MessageHelper mh = theHelper.appendMessage(EnumFamily.Command, EnumType.SubmitQueueEntry);
		assertEquals("CommandSubmitQueueEntry", mh.getRoot().getLocalName());
	}

	/**
	 *
	 */
	@Test
	void testGetCreateMessage()
	{
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
		final String s = theHelper.getMessageName(EnumFamily.Command, "SubmitQueueEntry");
		assertEquals("CommandSubmitQueueEntry", s);
	}

	/**
	 *
	 */
	@Test
	void testGetMessageNameDeprecated()
	{
		final String s = theHelper.getMessageName(EnumFamily.Registration, "SubmitQueueEntry");
		assertNull(s);
		final String s2 = theHelper.getMessageName(EnumFamily.Acknowledge, "SubmitQueueEntry");
		assertNull(s2);
	}

	/**
	 *
	 */
	@Test
	void testAddMessageNull()
	{
		final MessageHelper mh = theHelper.appendMessage(EnumFamily.Command, "");
		assertNull(mh);
	}

	/**
	 *
	 */
	@Test
	void testAddMessageDeprecated()
	{
		final MessageHelper mh = theHelper.appendMessage(EnumFamily.Registration, "Resource");
		assertNull(mh);
	}

	/**
	 *
	 */
	@Test
	void testHeaderID()
	{
		assertNotNull(theHelper.getXPathValue("Header/@ID"));
	}

	/**
	 *
	 */
	@Test
	void testHeaderID2()
	{
		assertNull(theHelper.getXPathElement("Header/Header"));
	}

	/**
	 *
	 */
	@Test
	void testHeaderDate()
	{
		assertNotNull(JDFDate.createDate(theHelper.getXPathValue("Header/@Time")));
	}

	/**
	 *
	 */
	@Test
	void testParseFile()
	{
		theHelper.appendMessage(EnumFamily.Command, EnumType.SubmitQueueEntry);
		final File file = new File(sm_dirTestDataTemp + "xjmf.xjmf");
		file.delete();
		theHelper.writeToFile(sm_dirTestDataTemp + "xjmf.xjmf");
		assertTrue(file.exists());
		XJMFHelper h2 = XJMFHelper.parseFile(file.getAbsolutePath());
		XJMFHelper h3 = XJMFHelper.parseFile(file);
		assertTrue(h2.isEqual(h3));
	}

	/**
	 *
	 */
	@Test
	void testWriteToFile()
	{
		theHelper.appendMessage(EnumFamily.Command, EnumType.SubmitQueueEntry);
		final File file = new File(sm_dirTestDataTemp + "xjmf.xjmf");
		file.delete();
		theHelper.writeToFile(sm_dirTestDataTemp + "xjmf.xjmf");
		assertTrue(file.exists());
	}

	/**
	 * @see Object#toString()
	 */
	@Override
	public String toString()
	{
		return "XJMFHelperTest [theHelper=" + theHelper + "]";
	}
}
