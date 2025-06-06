/*
 * The CIP4 Software License, Version 1.0
 *
 *
 * Copyright (c) 2001-2022 The International Cooperation for the Integration of Processes in Prepress, Press and Postpress (CIP4). All rights reserved.
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

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.io.File;
import java.util.Map;

import org.cip4.jdflib.JDFTestCaseBase;
import org.cip4.jdflib.core.JDFDoc;
import org.cip4.jdflib.core.KElement;
import org.cip4.jdflib.jmf.JDFJMF;
import org.cip4.jdflib.jmf.JMFBuilder;
import org.cip4.jdflib.node.JDFNode;
import org.cip4.jdflib.util.MyPair;
import org.junit.jupiter.api.Test;

class XMLCompareWalkerTest extends JDFTestCaseBase
{

	@Test
	void testSimple()
	{
		final KElement e1 = KElement.createRoot("a", null);
		final KElement e2 = KElement.createRoot("a", null);
		final XMLCompareWalker w = new XMLCompareWalker(e1, e2);
		assertTrue(w.compare().isEmpty());
	}

	@Test
	void testSimpleChild()
	{
		final KElement e1 = KElement.createRoot("a", null);
		e1.setAttribute("b", "b1");
		final KElement e2 = KElement.createRoot("a", null);
		e2.setAttribute("b", "b2");
		final XMLCompareWalker w = new XMLCompareWalker(e1, e2);
		final Map<String, MyPair<String, String>> m = w.compare();
		assertEquals(1, m.size());
		assertEquals(new MyPair<>("b1", "b2"), m.get("/a/@b"));
	}

	@Test
	void testSimpleIgnore()
	{
		final KElement e1 = KElement.createRoot("a", null);
		e1.setAttribute("b", "b1");
		final KElement e2 = KElement.createRoot("a", null);
		e2.setAttribute("b", "b2");
		final XMLCompareWalker w = new XMLCompareWalker(e1, e2);
		w.addIgnore("b", true);
		assertTrue(w.compare().isEmpty());
	}

	@Test
	void testSimpleIgnoreStandard()
	{
		final JDFNode e1 = JDFNode.createRoot();
		final JDFNode e2 = JDFNode.createRoot();

		final XMLCompareWalker w = XMLCompareWalker.getStandardWalker(e1, e2);
		assertFalse(w.compare().isEmpty());

		e1.setJobPartID("p1");
		e2.setJobPartID("p1");
		assertTrue(w.compare().isEmpty());
	}

	@Test
	void testSimpleIgnoreStandardJMF()
	{
		final JDFJMF e1 = new JMFBuilder().buildAbortQueueEntry("q1");
		final JDFJMF e2 = new JMFBuilder().buildAbortQueueEntry("q1");

		final XMLCompareWalker w = XMLCompareWalker.getStandardWalker(e1, e2);
		assertTrue(w.compare().isEmpty());
	}

	@Test
	void testSimpleIgnoreValue()
	{
		final KElement e1 = KElement.createRoot("a", null);
		e1.setAttribute("b", "b1");
		final KElement e2 = KElement.createRoot("a", null);
		e2.setAttribute("b", "");
		final XMLCompareWalker w = new XMLCompareWalker(e1, e2);
		w.addIgnore("b", true);
		assertFalse(w.compare().isEmpty());
		w.addIgnore("b", false);
		assertTrue(w.compare().isEmpty());
	}

	@Test
	void testSimpleChildSame()
	{
		final KElement e1 = KElement.createRoot("a", null);
		e1.setAttribute("b", "b1");
		final KElement e2 = KElement.createRoot("a", null);
		e2.setAttribute("b", "b1");
		final XMLCompareWalker w = new XMLCompareWalker(e1, e2);
		assertTrue(w.compare().isEmpty());
	}

	@Test
	void testSimpleChildPrecision()
	{
		final KElement e1 = KElement.createRoot("a", null);
		e1.setAttribute("b", "1.123");
		final KElement e2 = KElement.createRoot("a", null);
		e2.setAttribute("b", "1.1234");
		final XMLCompareWalker w = new XMLCompareWalker(e1, e2);
		w.setPrecision(0);
		assertFalse(w.compare().isEmpty());
		w.setPrecision(0.001);
		assertTrue(w.compare().isEmpty());
	}

	@Test
	void testSimpleChildPrecisionIntDouble()
	{
		final KElement e1 = KElement.createRoot("a", null);
		e1.setAttribute("b", "1");
		final KElement e2 = KElement.createRoot("a", null);
		e2.setAttribute("b", "1.000");
		final XMLCompareWalker w = new XMLCompareWalker(e1, e2);
		w.setPrecision(0);
		assertTrue(w.compare().isEmpty());
	}

	@Test
	void testComplexSame()
	{
		final File[] fs = new File(sm_dirTestData + "SampleFiles").listFiles();
		for (final File f : fs)
		{
			final JDFDoc d = JDFDoc.parseFile(f);
			final XMLCompareWalker w = new XMLCompareWalker(d.getRoot(), d.getRoot());
			assertTrue(w.compare().isEmpty());
		}
	}

}
