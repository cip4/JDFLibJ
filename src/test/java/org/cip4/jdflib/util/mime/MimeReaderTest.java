/*
 *
 * The CIP4 Software License, Version 1.0
 *
 *
 * Copyright (c) 2001-2025 The International Cooperation for the Integration of Processes in Prepress, Press and Postpress (CIP4). All rights reserved.
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

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;

import java.io.File;
import java.io.InputStream;

import org.cip4.jdflib.JDFTestCaseBase;
import org.cip4.jdflib.core.JDFDoc;
import org.cip4.jdflib.jmf.JDFJMF;
import org.cip4.jdflib.node.JDFNode;
import org.cip4.jdflib.util.MimeUtil;
import org.junit.jupiter.api.Test;

import jakarta.mail.BodyPart;
import jakarta.mail.Multipart;

class MimeReaderTest extends JDFTestCaseBase
{

	@Test
	void testGetURL()
	{

		final Multipart mp = MimeUtil.getMultiPart(sm_dirTestDataTemp + File.separator + "testMimePackageDoc.mjm");
		MimeUtil.writeToFile(mp, sm_dirTestDataTemp + File.separator + "testMimePackageReader.mjm", null);
		final MimeReader mr = new MimeReader(sm_dirTestDataTemp + File.separator + "testMimePackageReader.mjm");
		assertNull(mr.getURLInputStream("a"));
	}

	@Test
	void testGetByCID()
	{
		final MimeReader mr = new MimeReader(sm_dirTestData + File.separator + "evilchars.mjm");
		assertNotNull(mr.getPartByCID("@OtherJDF"));
	}

	@Test
	void testGetByCIDNet()
	{
		final MimeReader mr = new MimeReader(sm_dirTestData + File.separator + "evilchars.mjm");
		assertNull(mr.getPartByCID("http://foo/OtherJDF"));
	}

	@Test
	void testGetByCID2()
	{
		final MimeReader mr = new MimeReader(sm_dirTestData + File.separator + "evil.mjm");
		assertNotNull(mr.getPartByCID("../../OtherJDF.jdf"));
	}

	@Test
	void testRQE()
	{
		final MimeReader mr = new MimeReader("/data/unserstream0.txt");
		final BodyPart[] bps = mr.getBodyParts();
		assertEquals(2, bps.length);
		for (final BodyPart bp : bps)
		{
			final JDFDoc jdfDoc = MimeUtil.getJDFDoc(bp);
			assertNotNull(jdfDoc);
		}
	}

	@Test
	void testRQEUrl()
	{
		final MimeReader mr = new MimeReader("/data/unserstream0.txt");
		final BodyPartHelper bph = mr.getBodyPartHelper(0);
		final JDFJMF jmf = bph.getJDFDoc().getJMFRoot();
		final InputStream is = jmf.getMessage(0).getReturnQueueEntryParams(0).getURLInputStream();
		final JDFNode n = JDFNode.parseStream(is);
		assertNotNull(n);
	}

	@Test
	void testGetByCIDMany()
	{
		for (int i = 0; i < 42; i++)
		{
			final MimeReader mr = new MimeReader(sm_dirTestData + File.separator + "evil.mjm");
			for (int j = 0; j < 42; j++)
			{
				assertNotNull(mr.getPartByCID("../../OtherJDF.jdf"));
			}
		}
	}
}
