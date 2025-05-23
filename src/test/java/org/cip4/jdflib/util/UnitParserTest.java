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
package org.cip4.jdflib.util;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.cip4.jdflib.JDFTestCaseBase;
import org.cip4.jdflib.core.AttributeName;
import org.cip4.jdflib.core.ElementName;
import org.cip4.jdflib.core.JDFDoc;
import org.cip4.jdflib.core.JDFResourceLink.EnumUsage;
import org.cip4.jdflib.core.KElement;
import org.cip4.jdflib.extensions.XJDFHelper;
import org.cip4.jdflib.node.JDFNode;
import org.cip4.jdflib.util.UnitParser.eParserUnit;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**
 * @author Rainer Prosi, Heidelberger Druckmaschinen *
 */
class UnitParserTest extends JDFTestCaseBase
{

	private UnitParser unitParser;

	/**
	 * @see JDFTestCaseBase#setUp()
	 * @throws Exception
	 */
	@Override
	@BeforeEach
	public void setUp() throws Exception
	{
		super.setUp();
		this.unitParser = new UnitParser();
	}

	/**
	 *
	 */
	@Test
	void testUnits()
	{
		assertEquals(unitParser.extractUnits("123"), "123");
		assertEquals(unitParser.extractUnits("0.4"), "0.4");
		assertEquals(unitParser.extractUnits("0.400"), "0.4");
		assertEquals(unitParser.extractUnits("0.1cm"), "2.8346");
		assertEquals(unitParser.extractUnits("0.1 cm"), "2.8346");
		assertEquals(unitParser.extractUnits("0.cm"), "0");
		assertEquals(unitParser.extractUnits("-00"), "-00");
		assertEquals(unitParser.extractUnits("abc def"), "abc def");
		assertEquals(unitParser.extractUnits("abc cm mm"), "abc cm mm");
		assertEquals(unitParser.extractUnits("10cm 10mm"), "283.4646 28.3465");
		assertEquals(unitParser.extractUnits("10in 10mm"), "720 28.3465");
		assertEquals(unitParser.extractUnits("10 in 10 mm"), "720 28.3465");
		assertEquals(unitParser.extractUnits("8.5in 11.in"), "612 792");
		assertEquals(unitParser.extractUnits("007 11in"), "007 792");
		assertEquals(unitParser.extractUnits("007 0011in"), "007 792");
	}

	/**
	 *
	 */
	@Test
	void testBlanks()
	{
		assertEquals(unitParser.extractUnits("0.1 cm"), "2.8346");
		assertEquals(unitParser.extractUnits("10 cm 10  mm"), "283.4646 28.3465");
		assertEquals(unitParser.extractUnits("10 in 10 mm"), "720 28.3465");
		assertEquals(unitParser.extractUnits("10 in mm"), "10 in mm");
		assertEquals(unitParser.extractUnits("10in mm"), "10in mm");
	}

	/**
	 *
	 */
	@Test
	void testCase()
	{
		assertEquals(unitParser.extractUnits("0.1CM"), "2.8346");
		assertEquals(unitParser.extractUnits("10Cm 10  mm"), "283.4646 28.3465");
		assertEquals(unitParser.extractUnits("10 In 10 MM"), "720 28.3465");
	}

	/**
	 *
	 */
	@Test
	void testNoUnitString()
	{
		assertEquals(14, StringUtil.parseDouble(StringUtil.leftStr(unitParser.getNoUnitString(eParserUnit.mm, 42), 2), -1), 1);
		assertEquals(42, StringUtil.parseDouble(StringUtil.leftStr(unitParser.getNoUnitString(eParserUnit.pt, 42), 2), -1), 1);
		assertEquals(1.4, StringUtil.parseDouble(StringUtil.leftStr(unitParser.getNoUnitString(eParserUnit.cm, 42), 2), -1), 1);
		assertEquals(5, StringUtil.parseDouble(StringUtil.leftStr(unitParser.getNoUnitString(eParserUnit.in, 420), 2), -1), 1);
	}

	/**
	 *
	 */
	@Test
	void testUnitString()
	{
		assertEquals("42 mm", unitParser.getUnitString(eParserUnit.mm, "42mm", " "));
		assertEquals("4.2 cm 4.2 cm", unitParser.getUnitString(eParserUnit.cm, "42mm 42mm", " "));
		assertEquals("4.2cm 4.2cm", unitParser.getUnitString(eParserUnit.cm, "42mm 42mm", ""));
	}

	/**
	 *
	 */
	@Test
	void testUnitStringKey()
	{
		assertEquals("42 mm", unitParser.getUnitString("Center", eParserUnit.mm, "42mm", " "));
		assertEquals("abc", unitParser.getUnitString("Center", eParserUnit.mm, "abc", " "));
		assertEquals("42mm", unitParser.getUnitString("fff", eParserUnit.mm, "42mm", " "));
	}

	/**
	 *
	 */
	@Test
	void testAdd()
	{
		assertEquals(unitParser.extractUnits("0.1CM"), "2.8346");
		assertEquals(unitParser.extractUnits("10Cm 10  mm"), "283.4646 28.3465");
		assertEquals(unitParser.extractUnits("10 In 10 MM"), "720 28.3465");
	}

	/**
	 *
	 */
	@Test
	void testKey()
	{
		assertEquals(unitParser.extractUnits(AttributeName.LENGTH, "0.1CM"), "2.8346");
		assertEquals(unitParser.extractUnits(AttributeName.JOBID, "0.1CM"), "0.1CM");
	}

	/**
	 *
	 */
	@Test
	void testDataType()
	{
		final JDFNode n = new JDFDoc(ElementName.JDF).getJDFRoot();
		n.setJobID("10CM");
		unitParser.convertUnits(n);
		assertEquals("10CM", n.getJobID(true));
	}

	/**
	 *
	 */
	@Test
	void testSizeDataType()
	{
		final XJDFHelper h = new XJDFHelper("1cm", "2mm", null);
		final KElement li = h.appendProduct().appendIntent("LayoutIntent").getCreateResource();
		li.setAttribute("Dimensions", "10cm 5cm");
		li.setAttribute("Dimension", "10cm 5cm");
		li.setAttribute("FinishedDimensions", "10cm 5cm 0cm");
		unitParser.setPrecision(0);
		unitParser.convertUnits(li);
		assertEquals(li.getAttribute("Dimension"), "283 142");
		assertEquals(li.getAttribute("Dimensions"), "283 142");
		assertEquals(li.getAttribute("FinishedDimensions"), "283 142 0");
	}

	/**
	 *
	 */
	@Test
	void testSpanDataType()
	{
		final JDFNode n = new JDFDoc(ElementName.JDF).getJDFRoot();

		final KElement li = n.addResource(ElementName.LAYOUTINTENT, EnumUsage.Input);
		final KElement d = li.appendElement("Dimensions");
		d.setAttribute(AttributeName.ACTUAL, "10cm 5cm");
		final KElement fd = li.appendElement("FinishedDimensions");
		fd.setAttribute(AttributeName.ACTUAL, "10cm 5cm 0cm");
		fd.setAttribute(AttributeName.FACECELLS, "10cm 5cm 0cm");
		unitParser.setPrecision(0);
		unitParser.convertUnits(n, true);
		assertEquals(li.getXPathAttribute("Dimensions/@Actual", null), "283 142");
		assertEquals(li.getXPathAttribute("FinishedDimensions/@Actual", null), "283 142 0");
		assertEquals(li.getXPathAttribute("FinishedDimensions/@FaceCells", null), "283 142 0");
	}

	/**
	 *
	 */
	@Test
	void testGetFactor()
	{
		assertEquals(unitParser.getFactor("cm"), 72. / 2.54, 0.0001);
		assertEquals(unitParser.getFactor("MM"), 72. / 25.4, 0.0001);
		assertEquals(unitParser.getFactor("in "), 72., 0.0001);
		assertEquals(unitParser.getFactor("a"), 1., 0.0001);
	}

	/**
	 *
	 */
	@Test
	void testPrecision()
	{
		unitParser.setPrecision(0);
		assertEquals(unitParser.extractUnits("10cm 10mm"), "283 28");
		assertEquals(unitParser.extractUnits("10in 10mm"), "720 28");
		assertEquals(unitParser.extractUnits("10.4 10.6"), "10 11");
		assertEquals(unitParser.extractUnits("0.44"), "0");
		assertEquals(unitParser.extractUnits("-0.44"), "0");
		assertEquals(unitParser.extractUnits("-044"), "-044");
		unitParser.setPrecision(4);
		assertEquals(unitParser.extractUnits("10cm 10mm"), "283.4646 28.3465");
		assertEquals(unitParser.extractUnits("10in 10mm"), "720 28.3465");
		assertEquals(unitParser.extractUnits("8.5in 11.in"), "612 792");
	}
}
