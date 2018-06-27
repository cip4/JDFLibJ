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
package org.cip4.jdflib.core;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import java.io.File;

import org.cip4.jdflib.JDFTestCaseBase;
import org.cip4.jdflib.core.JDFElement.EnumValidationLevel;
import org.cip4.jdflib.node.JDFNode;
import org.cip4.jdflib.pool.JDFAuditPool;
import org.cip4.jdflib.resource.JDFNotification;
import org.junit.Test;

public class JDFCommentTest extends JDFTestCaseBase
{
	@Test
	public void testInit()
	{
		final JDFDoc doc = new JDFDoc(ElementName.JDF);
		final JDFNode root = doc.getJDFRoot();
		final JDFComment cRoot = root.appendComment();
		assertNotNull(cRoot.getAttribute(AttributeName.ID, null, null));
		assertTrue(cRoot.isValid(EnumValidationLevel.Complete));

		root.setVersion(JDFElement.EnumVersion.Version_1_2);
		final JDFAuditPool ap = root.getCreateAuditPool();
		final JDFNotification notif = ap.addNotification(JDFNotification.EnumClass.Information, "Me", null);
		final JDFComment c = notif.appendComment();
		c.setText("This element should have no ID attribute");
		final String id = c.getAttribute(AttributeName.ID, null, null);
		assertNull(id);
	}

	@Test
	public void testIDPrefix()
	{
		final JDFDoc doc = new JDFDoc(ElementName.JDF);
		final JDFNode root = doc.getJDFRoot();
		final JDFComment cRoot = root.appendComment();

		final String id = cRoot.getAttribute(AttributeName.ID, null, null);
		assertTrue(id.startsWith("c"));
	}

	@Test
	public void testFormat()
	{
		final JDFDoc doc = new JDFDoc(ElementName.JDF);
		final JDFNode root = doc.getJDFRoot();
		final JDFComment c11 = root.appendComment();
		String txt = "This element should have no ID attribute and is a rellly long line with many many characters.. asfihAFLKFKJGFaufksgUFGagfAFKJSG";
		txt += txt;
		txt += txt; // even longer...
		c11.setText(txt);
		final JDFComment c21 = root.appendComment();
		final String txt2 = "This element \n has \n crlf";
		c21.setText(txt2);
		assertEquals("text is equal in DOM", txt, c11.getText());
		assertEquals("text is equal in DOM", txt2, c21.getText());
		final String commentFile = sm_dirTestDataTemp + File.separator + "CommentTest.JDF";
		doc.write2File(commentFile, 2, true);
		final JDFParser p = new JDFParser();
		final JDFDoc doc2 = p.parseFile(commentFile);
		final JDFNode root2 = doc2.getJDFRoot();
		final JDFComment c12 = root2.getComment(0);
		final JDFComment c22 = root2.getComment(1);
		assertEquals("text is equal after parse", txt, c12.getText());
		assertEquals("text is equal after parse", txt2, c22.getText());
	}

	@Test
	public void testEraseEmptyNodes()
	{
		final JDFDoc doc = new JDFDoc(ElementName.JDF);
		final JDFNode root = doc.getJDFRoot();
		final JDFComment c11 = root.appendComment();
		final String txt = "      \n\n";
		c11.setText(txt);
		root.eraseEmptyNodes(true);
		assertEquals("whitespace is not removed", txt, c11.getText());
	}

	@Test
	public void testSetText()
	{
		final JDFDoc doc = new JDFDoc(ElementName.JDF);
		final JDFNode root = doc.getJDFRoot();
		final JDFComment c11 = root.appendComment();
		final String txt = "      \n\n";
		c11.setText(txt);
		assertEquals("whitespace is not removed", txt, c11.getText());
	}

}