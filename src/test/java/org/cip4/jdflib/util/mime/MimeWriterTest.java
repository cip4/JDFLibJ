/*
 *
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

package org.cip4.jdflib.util.mime;

import java.io.ByteArrayInputStream;

import org.cip4.jdflib.core.ElementName;
import org.cip4.jdflib.core.JDFDoc;
import org.cip4.jdflib.jmf.JDFCommand;
import org.cip4.jdflib.jmf.JDFJMF;
import org.cip4.jdflib.jmf.JDFMessage;
import org.cip4.jdflib.jmf.JDFReturnQueueEntryParams;
import org.cip4.jdflib.util.ByteArrayIOStream;
import org.cip4.jdflib.util.mime.MimeWriter.FixSemiColonStream;
import org.junit.Test;

import junit.framework.TestCase;

/**
 * @author Rainer Prosi, Heidelberger Druckmaschinen *
 */
public class MimeWriterTest extends TestCase
{

	/**
	 * @throws Exception
	 */
	@Test
	public void testFixSemicolon1() throws Exception
	{
		final ByteArrayIOStream ios = new ByteArrayIOStream();
		final FixSemiColonStream fs = new FixSemiColonStream(ios);
		fs.write("AAAAAAAAAAAAAAAAAAAA   \n\ngdf".getBytes());
		fs.write("content-type: multipart/related; foo=bar\nBBBBB".getBytes());
		fs.close();
		final ByteArrayInputStream is = ios.getInputStream();
		final byte b[] = new byte[100];
		is.read(b);
		final String s = new String(b);
		assertTrue(s.indexOf("related;") < 0);
	}

	/**
	 * @throws Exception
	 */
	@Test
	public void testMessageID() throws Exception
	{
		final JDFDoc docJMF = new JDFDoc("JMF");
		final JDFJMF jmf = docJMF.getJMFRoot();
		jmf.setSenderID("DeviceID");
		final JDFCommand com = (JDFCommand) jmf.appendMessageElement(JDFMessage.EnumFamily.Command, JDFMessage.EnumType.ReturnQueueEntry);
		final JDFReturnQueueEntryParams returnQEParams = com.appendReturnQueueEntryParams();

		final String queueEntryID = "qe1";
		returnQEParams.setQueueEntryID(queueEntryID);
		final JDFDoc docJDF = new JDFDoc(ElementName.JDF);
		returnQEParams.setURL("cid:dummy"); // will be overwritten by buildMimePackage
		final MimeWriter mw = new MimeWriter();
		mw.buildMimePackage(docJMF, docJDF, false);
		final ByteArrayIOStream ios = new ByteArrayIOStream();
		mw.writeToStream(ios);
		final byte[] b = ios.getBuf();
		final String string = new String(b);
		assertTrue(string.startsWith("MIME-Version"));
		assertEquals(-1, string.indexOf("Message-ID"));
	}

	/**
	 * @throws Exception
	 */
	@Test
	public void testFixSemicolon2() throws Exception
	{
		final ByteArrayIOStream ios = new ByteArrayIOStream();
		final FixSemiColonStream fs = new FixSemiColonStream(ios);
		fs.write("AAAAAAAAAAAAAAAAAAAA   \n\ngdf".getBytes());
		fs.write("content-type: multipart/related;\nbbbb".getBytes());
		fs.close();
		final ByteArrayInputStream is = ios.getInputStream();
		final byte b[] = new byte[100];
		is.read(b);
		final String s = new String(b);
		assertTrue(s.indexOf("related;") < 0);
		assertTrue(s.indexOf("\nbbb") > 0);
	}

}
