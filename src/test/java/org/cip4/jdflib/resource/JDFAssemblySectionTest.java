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

package org.cip4.jdflib.resource;

import java.util.Collection;

import org.cip4.jdflib.JDFTestCaseBase;
import org.cip4.jdflib.core.*;
import org.cip4.jdflib.core.JDFElement.EnumValidationLevel;
import org.cip4.jdflib.node.JDFNode;
import org.cip4.jdflib.node.JDFNode.EnumProcessUsage;
import org.cip4.jdflib.resource.process.JDFAssembly;
import org.cip4.jdflib.resource.process.JDFAssemblySection;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**
 * all kinds of fun tests around JDF 1.2 vs JDF 1.3 Layouts
 *
 */
class JDFAssemblySectionTest extends JDFTestCaseBase
{

	private JDFDoc doc = null;
	private JDFNode n = null;
	private JDFAssembly as = null;

	/**
	 * @see JDFTestCaseBase#setUp()
	 * @throws Exception
	 */
	@Override
	@BeforeEach
	public void setUp() throws Exception
	{
		super.setUp();
		doc = new JDFDoc("JDF");
		n = doc.getJDFRoot();
		n.setType("Stripping", true);
		as = (JDFAssembly) n.appendMatchingResource(ElementName.ASSEMBLY, EnumProcessUsage.AnyInput, null);
	}

	/**
	 *
	 */
	@Test
	void testSubAssemblySection()
	{
		final JDFAssemblySection ass = as.appendAssemblySection();
		final JDFAssemblySection asss = ass.appendAssemblySection();
		asss.setAssemblyIDs(new VString("a b c", " "));
		Assertions.assertTrue(as.isValid(EnumValidationLevel.Incomplete));
		doc.write2File(sm_dirTestDataTemp + "AssemblySection.jdf", 2, false);
	}

	/**
	 *
	 */
	@Test
	void testAssemblySectionCollection()
	{
		JDFAssemblySection ass = as.appendAssemblySection();
		ass = as.appendAssemblySection();
		final JDFAssemblySection asss = ass.appendAssemblySection();
		asss.setAssemblyIDs(new VString("a b c", " "));
		ass = as.getAssemblySection(0);
		ass = as.getAssemblySection(1);
		ass.setXMLComment("MyComment", true);
		ass = as.getAssemblySection(2);

		final Collection<JDFAssemblySection> vASS = as.getAllAssemblySection();

		Assertions.assertTrue(vASS.size() == 2);
	}

	/**
	 *
	 */
	@Test
	void testAllAssemblyIDs()
	{
		JDFAssemblySection ass = as.appendAssemblySection();
		ass = as.appendAssemblySection();
		ass.setAssemblyIDs(new VString("c d e", " "));
		as.setAssemblyID("aid");
		final JDFAssemblySection asss = ass.appendAssemblySection();
		asss.setAssemblyIDs(new VString("a b c", " "));
		final StringArray a = as.getAllAssemblyIDs();

		Assertions.assertTrue(a.containsAll(new StringArray("aid a b c d e")));
		Assertions.assertEquals(6, a.size());
	}
}