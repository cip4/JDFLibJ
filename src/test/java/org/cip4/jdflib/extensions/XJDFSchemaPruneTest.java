/*
 * The CIP4 Software License, Version 1.0
 *
 *
 * Copyright (c) 2001-2024 The International Cooperation for the Integration of Processes in Prepress, Press and Postpress (CIP4). All rights reserved.
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

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.io.File;

import org.cip4.jdflib.JDFTestCaseBase;
import org.cip4.jdflib.core.ElementName;
import org.cip4.jdflib.core.JDFComment;
import org.cip4.jdflib.core.KElement;
import org.cip4.jdflib.core.XMLDoc;
import org.cip4.jdflib.core.XMLParser;
import org.cip4.jdflib.jmf.JDFMessage.EnumFamily;
import org.cip4.jdflib.node.JDFNode.EnumType;
import org.cip4.jdflib.util.UrlUtil;
import org.junit.jupiter.api.Test;

class XJDFSchemaPruneTest extends JDFTestCaseBase
{

	@Test
	void testPruneSimple()
	{
		final XMLDoc schema = XMLDoc.parseFile(getXJDFSchema(2, 2));
		final XJDFSchemaPrune w = new XJDFSchemaPrune(schema);
		final XJDFHelper h = new XJDFHelper("j1", "p1");
		h.addType(EnumType.Product);
		final KElement ret = w.prune(h.getRoot());
		assertNotNull(ret);
		final String out = sm_dirTestDataTemp + "prune1.xsd";
		ret.write2File(out);
		final XMLParser p = new XMLParser();
		p.setSchemaLocation(XJDF20.getSchemaURL(), UrlUtil.fileToUrl(new File(out), true));
		final XMLDoc d = p.parseString(h.getRoot().toDisplayXML(2));
		assertTrue(d.isSchemaValid());
	}

	@Test
	void testPruneSimpleNoAtts()
	{
		final XMLDoc schema = XMLDoc.parseFile(getXJDFSchema(2, 2));
		final XJDFSchemaPrune prune = new XJDFSchemaPrune(schema);
		prune.setCheckAttributes(true);
		final XJDFHelper h = new XJDFHelper("j1", "p1");
		h.addType(EnumType.Product);
		h.removeSet(ElementName.NODEINFO);
		final KElement ret = prune.prune(h.getRoot());
		assertNotNull(ret);
		final String out = sm_dirTestDataTemp + "prune2.xsd";
		ret.write2File(out);
		final XMLParser p = new XMLParser();
		p.setSchemaLocation(XJDF20.getSchemaURL(), UrlUtil.fileToUrl(new File(out), true));
		final XMLDoc d = p.parseString(h.getRoot().toDisplayXML(2));
		assertTrue(d.isSchemaValid());
	}

	@Test
	void testPruneComment()
	{
		final XMLDoc schema = XMLDoc.parseFile(getXJDFSchema(2, 2));
		final XJDFSchemaPrune prune = new XJDFSchemaPrune(schema);
		prune.setCheckAttributes(true);
		final XJDFHelper h = new XJDFHelper("j1", "p1");
		h.addType(EnumType.Product);
		final JDFComment c = (JDFComment) h.appendElement(ElementName.COMMENT);
		c.setText("foobar");
		c.setLanguage("EN");
		final KElement ret = prune.prune(h.getRoot());
		assertNotNull(ret);
		final String out = sm_dirTestDataTemp + "comment.xsd";
		ret.write2File(out);
		final XMLParser p = new XMLParser();
		p.setSchemaLocation(XJDF20.getSchemaURL(), UrlUtil.fileToUrl(new File(out), true));
		h.cleanUp();
		final XMLDoc d = p.parseString(h.getRoot().toDisplayXML(2));
		assertTrue(d.isSchemaValid());
	}

	@Test
	void testPruneComplex()
	{
		final XMLDoc schema = XMLDoc.parseFile(getXJDFSchema(2, 2));
		final XJDFSchemaPrune w = new XJDFSchemaPrune(schema);

		final XJDFHelper h = XJDFHelper.parseFile(sm_dirTestData + "xjdf/sr09.xjdf");
		final KElement ret = w.prune(h.getRoot());
		assertNotNull(ret);
		final String out = sm_dirTestDataTemp + "sr09.xsd";
		ret.write2File(out);
		final XMLParser p = new XMLParser();
		p.setSchemaLocation(XJDF20.getSchemaURL(), UrlUtil.fileToUrl(new File(out), true));
		final XMLDoc d = p.parseString(h.getRoot().toDisplayXML(2));
		assertTrue(d.isSchemaValid());
	}

	@Test
	void testPruneSimpleXJMF()
	{
		final XMLDoc schema = XMLDoc.parseFile(getXJDFSchema(2, 2));
		final XJDFSchemaPrune w = new XJDFSchemaPrune(schema);
		final XJMFHelper h = new XJMFHelper();
		h.appendMessage(EnumFamily.Query, org.cip4.jdflib.jmf.JDFMessage.EnumType.KnownMessages);
		final KElement ret = w.prune(h.getRoot());
		assertNotNull(ret);
		final String out = sm_dirTestDataTemp + "knownmessages.xsd";
		ret.write2File(out);
		final XMLParser p = new XMLParser();
		p.setSchemaLocation(XJDF20.getSchemaURL(), UrlUtil.fileToUrl(new File(out), true));
		final XMLDoc d = p.parseString(h.getRoot().toDisplayXML(2));
		assertTrue(d.isSchemaValid());
	}

	@Test
	void testPruneSimpleXJMFNoAtts()
	{
		final XMLDoc schema = XMLDoc.parseFile(getXJDFSchema(2, 2));
		final XJDFSchemaPrune prune = new XJDFSchemaPrune(schema);
		prune.setCheckAttributes(true);
		final XJMFHelper h = new XJMFHelper();
		h.appendMessage(EnumFamily.Query, org.cip4.jdflib.jmf.JDFMessage.EnumType.KnownMessages);
		final KElement ret = prune.prune(h.getRoot());
		assertNotNull(ret);
		final String out = sm_dirTestDataTemp + "knownmessages2.xsd";
		ret.write2File(out);
		final XMLParser p = new XMLParser();
		p.setSchemaLocation(XJDF20.getSchemaURL(), UrlUtil.fileToUrl(new File(out), true));
		final XMLDoc d = p.parseString(h.getRoot().toDisplayXML(2));
		assertTrue(d.isSchemaValid());
	}

	@Test
	void testPruneSimpleMultiXJMFNoAtts()
	{
		final XMLDoc schema = XMLDoc.parseFile(getXJDFSchema(2, 2));
		final XJDFSchemaPrune prune = new XJDFSchemaPrune(schema);
		prune.setCheckAttributes(true);
		final XJMFHelper h = new XJMFHelper();
		h.appendMessage(EnumFamily.Query, org.cip4.jdflib.jmf.JDFMessage.EnumType.KnownMessages);
		final XJMFHelper h2 = new XJMFHelper();
		h2.appendMessage(EnumFamily.Query, org.cip4.jdflib.jmf.JDFMessage.EnumType.KnownDevices);
		final KElement ret = prune.prune(h.getRoot(), h2.getRoot());
		assertNotNull(ret);
		final String out = sm_dirTestDataTemp + "knownmessages3.xsd";
		ret.write2File(out);
		final XMLParser p = new XMLParser();
		p.setSchemaLocation(XJDF20.getSchemaURL(), UrlUtil.fileToUrl(new File(out), true));
		final XMLDoc d = p.parseString(h.getRoot().toDisplayXML(2));
		assertTrue(d.isSchemaValid());
		final XMLDoc d2 = p.parseString(h2.getRoot().toDisplayXML(2));
		assertTrue(d2.isSchemaValid());
	}

}
