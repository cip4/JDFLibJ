/*
 * The CIP4 Software License, Version 1.0
 *
 *
 * Copyright (c) 2001-2020 The International Cooperation for the Integration of Processes in Prepress, Press and Postpress (CIP4). All rights reserved.
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
package org.cip4.jdflib.elementwalker;

import java.io.File;

import org.cip4.jdflib.JDFTestCaseBase;
import org.cip4.jdflib.core.ElementName;
import org.cip4.jdflib.core.JDFDoc;
import org.cip4.jdflib.core.JDFElement;
import org.cip4.jdflib.core.KElement;
import org.cip4.jdflib.core.XMLDoc;
import org.cip4.jdflib.node.JDFNode;
import org.cip4.jdflib.resource.process.JDFGeneralID;
import org.cip4.jdflib.util.CPUTimer;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

/**
 * @author Rainer Prosi, Heidelberger Druckmaschinen *
 */
public class EnsureNSUriTest extends JDFTestCaseBase
{
	/**
	 *
	 */
	@Test
	void testEnsureNS()
	{
		final XMLDoc d = new XMLDoc("foo", "bar");
		final KElement root = d.getRoot();
		root.addNameSpace("n1", "n6");
		root.setAttribute("n1:gg", "gg");
		final KElement e = root.appendElement("n1:bar", "n66");
		e.appendElement("n1:gg").setAttribute("n1:test", "123");

		final EnsureNSUri ensure = new EnsureNSUri();
		ensure.addNS("n1", "www.n1.com");
		ensure.walk(root);

		Assertions.assertTrue(root.toXML().indexOf("n6") < 0);
	}

	/**
	 *
	 */
	@Test
	void testEnsureNSKeepUnknown()
	{
		final XMLDoc d = new XMLDoc("foo", "www.foo.com");
		final KElement root = d.getRoot();
		final KElement e = root.appendElement("n1:bar", "n66");
		e.setAttribute("a", "b");
		e.setAttribute("n1:aa", "b");
		final KElement e2 = root.appendElement("n1:bar", "n66");
		e2.setAttribute("a", "b");
		e2.setAttribute("n1:aa", "b");

		final EnsureNSUri ensure = new EnsureNSUri();
		ensure.walk(root);

		Assertions.assertEquals(e.getPrefix(), "n1");
		Assertions.assertEquals(e2.getPrefix(), "n1");
	}

	/**
	 *
	 */
	@Test
	void testEnsureNSNull()
	{
		final XMLDoc d = new XMLDoc("foo", null);
		final KElement root = d.getRoot();
		final KElement e = root.appendElement("bar", "n66");
		e.appendElement("gg", "n77").setAttribute("ntest", "123");
		e.appendElement("blub:bb", "www.blub.com").setAttribute("blub:ntest", "123");

		final EnsureNSUri ensure = new EnsureNSUri();
		ensure.addNS(null, "www.n1.com");
		ensure.addAlias("blub", null);
		ensure.walk(root);

		Assertions.assertTrue(root.toXML().indexOf("n6") < 0);
		Assertions.assertTrue(root.toXML().indexOf("n7") < 0);
		Assertions.assertTrue(root.toXML().indexOf("blub") < 0);
	}

	/**
	 *
	 */
	@Test
	void testEnsureNSDefault()
	{
		final XMLDoc d = new XMLDoc("foo", "foo.com");
		final KElement root = d.getRoot();
		final KElement e = root.appendElement("xxx:bar", "n66");
		e.appendElement("gg", "blah.com");
		root.appendElement("ccc");

		final EnsureNSUri ensure = new EnsureNSUri();
		ensure.addNS("xxx", "blah.com");
		ensure.walk(root);
		Assertions.assertEquals(root.toXML().indexOf("xxx:ccc"), -1);
	}

	/**
	 *
	 */
	@Test
	void testUpdate()
	{
		final XMLDoc d = new XMLDoc("foo", "foo");
		final KElement root = d.getRoot();

		final KElement e = root.appendElement("a");
		e.appendElement("gg").setAttribute("a:b", "val", "nsa");

		final String s = d.write2String(2);
		final XMLDoc d1 = XMLDoc.parseString(s);

		final EnsureNSUri ensure = new EnsureNSUri();
		ensure.addNS("a", "nsb");
		final KElement root2 = d1.getRoot();
		ensure.walk(root2);
		final KElement b = root2.copyElement(root2.getElement("a"), null);
	}

	/**
	 *
	 */
	@Test
	void testEnsureNS2Default()
	{
		final XMLDoc d = new XMLDoc("foo", "foo.com");
		final KElement root = d.getRoot();
		final KElement e = root.appendElement("bar", "bar.com");

		final EnsureNSUri ensure = new EnsureNSUri();
		ensure.addNS("xxx", "bar.com");
		ensure.walk(root);
		Assertions.assertTrue(root.toXML().indexOf("xxx:bar") > 0);
	}

	/**
	 *
	 */
	@Test
	void testEnsureNSMapToDefault()
	{
		final XMLDoc d = new XMLDoc("foo:foo", "foo.com");
		final KElement root = d.getRoot();
		final KElement e = root.appendElement("bar:bar", "foo.com");
		e.appendElement("gg:gg", "foo.com");
		root.appendElement("ccc");

		final EnsureNSUri ensure = new EnsureNSUri();
		ensure.addNS(null, "foo.com");
		ensure.walk(root);
		Assertions.assertEquals(root.toXML().indexOf("bar:"), -1);
		Assertions.assertEquals(root.toXML().indexOf("gg:"), -1);
	}

	/**
	 *
	 */
	@Test
	void testEnsureNSAlias()
	{
		final XMLDoc d = new XMLDoc("foo", "bar");
		final KElement root = d.getRoot();
		root.addNameSpace("n1", "n6");
		root.addNameSpace("n2", "n6");
		root.setAttribute("n1:gg", "gg");
		root.setAttribute("n3:gg", "other", "www.n3.com");
		final KElement e = root.appendElement("n2:bar", "n6");
		e.appendElement("n1:gg").setAttribute("n1:test", "123");
		e.appendElement("n2:gg").setAttribute("n2:test", "123");
		e.appendElement("n3:next").setAttribute("n3:test", "456");
		e.appendElement("n3:foofoo").setAttribute("n3:test", "456");

		final EnsureNSUri ensure = new EnsureNSUri();
		ensure.addNS("n1", "www.n1.com");
		ensure.addAlias("n2", "n1");
		ensure.walk(root);

		Assertions.assertTrue(root.toXML().indexOf("n2") < 0);
		Assertions.assertTrue(root.toXML().indexOf("n3:test") > 0, "undeclared n3 namespace is retained");
		Assertions.assertTrue(root.toXML().indexOf("<n3:next") > 0);
		Assertions.assertTrue(root.toXML().indexOf("<n3:foofoo") > 0);
	}

	/**
	 *
	 */
	@Test
	void testEnsureNSJDF()
	{
		final JDFDoc d = new JDFDoc(ElementName.JDF);
		final JDFNode jdfRoot = d.getJDFRoot();
		final JDFGeneralID gid = (JDFGeneralID) jdfRoot.appendElement("jdf:GeneralID", JDFElement.getSchemaURL());
		gid.setIDUsage("u");
		gid.setIDValue("v");
		final EnsureNSUri ensure = new EnsureNSUri();
		ensure.addNS("", JDFElement.getSchemaURL());
		ensure.addAlias("jdf", "");

		ensure.walk(jdfRoot);
		Assertions.assertTrue(jdfRoot.toXML().indexOf("jdf:") < 0);

	}

	/**
	 *
	 */
	@Test
	void testEnsureNSAliasBackwards()
	{
		final XMLDoc d = new XMLDoc("foo", "bar");
		final KElement root = d.getRoot();
		root.addNameSpace("n1", "www.n1.com");
		root.addNameSpace("n2", "www.n1.com");
		root.setAttribute("n1:gg", "gg");
		root.setAttribute("n3:gg", "other", "www.n3.com");
		final KElement e = root.appendElement("n2:bar", "www.n1.com");
		e.appendElement("n1:gg").setAttribute("n1:test", "123");
		e.appendElement("n2:gg").setAttribute("n2:test", "123");
		e.appendElement("n3:next").setAttribute("n3:test", "456");

		final EnsureNSUri ensure = new EnsureNSUri();
		ensure.addNS("n1", "www.n1.com");
		ensure.walk(root);

		Assertions.assertTrue(root.toXML().indexOf("n2:") < 0);
		Assertions.assertTrue(root.toXML().indexOf("n3:test") > 0, "undeclared n3 namespace is retained");
		Assertions.assertTrue(root.toXML().indexOf("<n3:next") > 0);
	}

	/**
	 *
	 */
	@Test
	void testBigEnsureNS()
	{
		final File f = new File(sm_dirTestData + "evilparts.jdf");
		Assertions.assertTrue(f.exists(), String.format("File %s not exists.", sm_dirTestData + "evilparts.jdf"));

		final CPUTimer ct = new CPUTimer(true);
		final XMLDoc d = XMLDoc.parseFile(sm_dirTestData + "evilparts.jdf");
		final KElement root = d.getRoot();
		Assertions.assertNotNull(root);
		final EnsureNSUri ensure = new EnsureNSUri();
		ensure.addNS("HDM", "www.hdm.com");
		ensure.walk(root);
		ct.stop();
		System.out.println(ct);

		Assertions.assertTrue(root.toXML().indexOf("schema/HDM") < 0);

	}
}
