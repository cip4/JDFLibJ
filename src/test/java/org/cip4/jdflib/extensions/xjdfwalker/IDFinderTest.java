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
/**
 *
 */
package org.cip4.jdflib.extensions.xjdfwalker;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

import java.util.Map;

import org.cip4.jdflib.JDFTestCaseBase;
import org.cip4.jdflib.core.AttributeName;
import org.cip4.jdflib.core.JDFDoc;
import org.cip4.jdflib.core.KElement;
import org.cip4.jdflib.datatypes.JDFAttributeMap;
import org.cip4.jdflib.datatypes.VJDFAttributeMap;
import org.cip4.jdflib.resource.JDFPart;
import org.cip4.jdflib.resource.JDFResource.EnumPartIDKey;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**
 * @author Rainer Prosi, Heidelberger Druckmaschinen
 *
 */
public class IDFinderTest extends JDFTestCaseBase
{

	KElement root;

	/**
	 * Test method for {@link IDFinder#getMap(KElement)}.
	 */
	@Test
	void testGetMap()
	{
		final IDFinder finder = new IDFinder();
		final Map<String, IDPart> m = finder.getMap(root);
		assertEquals(m.size(), 9 + 3);
	}

	/**
	 * Test method for {@link IDFinder#getMap(KElement)}.
	 */
	@Test
	void testSignatureName()
	{
		final IDFinder finder = new IDFinder();
		final Map<String, IDPart> m = finder.getMap(root);
		int n = 0;
		for (final IDPart idPart : m.values())
		{
			final VJDFAttributeMap partMap = idPart.getPartMap();
			if (partMap != null)
			{
				final JDFAttributeMap p = partMap.get(0);
				final String sh = p.get(AttributeName.SHEETNAME);
				if (sh != null)
				{
					assertEquals("Sig_" + sh, p.get(AttributeName.SIGNATURENAME));
					n++;
				}
			}
		}
		assertEquals(9, n);
	}

	/**
	 * Test method for {@link IDFinder#getMap(KElement)}.
	 */
	@Test
	void testContactType()
	{
		JDFPart part = (JDFPart) new JDFDoc("Part").getRoot();
		for (EnumPartIDKey e : EnumPartIDKey.getEnumList())
		{
			JDFPart p2 = (JDFPart) part.cloneNewDoc();
			p2.setAttribute(e.getName(), e.getName());
			if (e.isXJDF())
				assertNull(IDFinder.getPartMap(p2).get(e.getName()), e.getName());

		}
	}

	/**
	 * @see junit.framework.TestCase#setUp()
	 * @throws Exception
	 */
	@Override
	@BeforeEach
	void setUp() throws Exception
	{
		super.setUp();
		root = new JDFDoc("XJDF").getRoot();
		for (int i = 1; i < 4; i++)
		{
			root.setXPathAttribute("ResourceSet[" + i + "]/@ID", "R" + i);
			for (int j = 1; j < 4; j++)
			{
				root.setXPathAttribute("ResourceSet[" + i + "]/Resource[" + j + "]/@ID", "R" + i + "." + j + "");
				root.setXPathAttribute("ResourceSet[" + i + "]/Resource[" + j + "]/Part/@SheetName", "S" + j + "");
			}
		}
	}
}
