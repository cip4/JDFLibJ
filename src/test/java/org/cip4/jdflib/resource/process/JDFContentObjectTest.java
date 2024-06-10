/*
 *
 * The CIP4 Software License, Version 1.0
 *
 *
 * Copyright (c) 2001-2019 The International Cooperation for the Integration of
 * Processes in  Prepress, Press and Postpress (CIP4).  All rights
 * reserved.
 *
 * Redistribution and use in source and binary forms, with or without
 * modification, are permitted provided that the following conditions
 * are met:
 *
 * 1. Redistributions of source code must retain the above copyright
 *    notice, this list of conditions and the following disclaimer.
 *
 * 2. Redistributions in binary form must reproduce the above copyright
 *    notice, this list of conditions and the following disclaimer in
 *    the documentation and/or other materials provided with the
 *    distribution.
 *
 * 3. The end-user documentation included with the redistribution,
 *    if any, must include the following acknowledgment:
 *       "This product includes software developed by the
 *        The International Cooperation for the Integration of
 *        Processes in  Prepress, Press and Postpress (www.cip4.org)"
 *    Alternately, this acknowledgment may appear in the software itself,
 *    if and wherever such third-party acknowledgments normally appear.
 *
 * 4. The names "CIP4" and "The International Cooperation for the Integration of
 *    Processes in  Prepress, Press and Postpress" must
 *    not be used to endorse or promote products derived from this
 *    software without prior written permission. For written
 *    permission, please contact info@cip4.org.
 *
 * 5. Products derived from this software may not be called "CIP4",
 *    nor may "CIP4" appear in their name, without prior written
 *    permission of the CIP4 organization
 *
 * Usage of this software in commercial products is subject to restrictions. For
 * details please consult info@cip4.org.
 *
 * THIS SOFTWARE IS PROVIDED ``AS IS'' AND ANY EXPRESSED OR IMPLIED
 * WARRANTIES, INCLUDING, BUT NOT LIMITED TO, THE IMPLIED WARRANTIES
 * OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE ARE
 * DISCLAIMED.  IN NO EVENT SHALL THE INTERNATIONAL COOPERATION FOR
 * THE INTEGRATION OF PROCESSES IN PREPRESS, PRESS AND POSTPRESS OR
 * ITS CONTRIBUTORS BE LIABLE FOR ANY DIRECT, INDIRECT, INCIDENTAL,
 * SPECIAL, EXEMPLARY, OR CONSEQUENTIAL DAMAGES (INCLUDING, BUT NOT
 * LIMITED TO, PROCUREMENT OF SUBSTITUTE GOODS OR SERVICES; LOSS OF
 * USE, DATA, OR PROFITS; OR BUSINESS INTERRUPTION) HOWEVER CAUSED AND
 * ON ANY THEORY OF LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY,
 * OR TORT (INCLUDING NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT
 * OF THE USE OF THIS SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY OF
 * SUCH DAMAGE.
 * ====================================================================
 *
 * This software consists of voluntary contributions made by many
 * individuals on behalf of the The International Cooperation for the Integration
 * of Processes in Prepress, Press and Postpress and was
 * originally based on software
 * copyright (c) 1999-2001, Heidelberger Druckmaschinen AG
 * copyright (c) 1999-2001, Agfa-Gevaert N.V.
 *
 * For more information on The International Cooperation for the
 * Integration of Processes in  Prepress, Press and Postpress , please see
 * <http://www.cip4.org/>.
 *
 *
 */

package org.cip4.jdflib.resource.process;

import org.cip4.jdflib.JDFTestCaseBase;
import org.cip4.jdflib.core.ElementName;
import org.cip4.jdflib.core.JDFDoc;
import org.cip4.jdflib.datatypes.JDFMatrix;
import org.cip4.jdflib.datatypes.JDFRectangle;
import org.cip4.jdflib.node.JDFNode;
import org.cip4.jdflib.node.JDFNode.EnumType;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**
 *
 *
 * @author rainer prosi
 * @date before Sep 18, 2012
 */
class JDFContentObjectTest extends JDFTestCaseBase
{

	JDFLayout lo;

	/*
	 * (non-Javadoc)
	 *
	 * @see org.cip4.jdflib.JDFTestCaseBase#setUp()
	 */
	@Override
	@BeforeEach
	public void setUp() throws Exception
	{
		// TODO Auto-generated method stub
		super.setUp();
		final JDFDoc doc = new JDFDoc("JDF");
		final JDFNode n = doc.getJDFRoot();
		n.setType(EnumType.Imposition);
		lo = (JDFLayout) n.addResource(ElementName.LAYOUT, null);
	}

	/**
	 *
	 *
	 */
	@Test
	void testcalcOrd()
	{
		for (int i = 0; i < 4; i++)
		{
			Assertions.assertEquals(JDFContentObject.calcOrd(0, 10, 0, 2, 2, i), 0 + i);
			Assertions.assertEquals(JDFContentObject.calcOrd(-1, 10, 0, 2, 2, i), -1);
			Assertions.assertEquals(JDFContentObject.calcOrd(-1, 12, 0, 2, 2, i), 11 + i);
			Assertions.assertEquals(JDFContentObject.calcOrd(-1, 12, 1, 2, 2, i), 9 + i);
			Assertions.assertEquals(JDFContentObject.calcOrd(-2, 10, 2, 2, 2, i), 6 + i);
			Assertions.assertEquals(JDFContentObject.calcOrd(-2, 10, 3, 2, 2, i), -1);
			Assertions.assertEquals(JDFContentObject.calcOrd(0, 13, 3, 2, 2, i), 6 + i);
			Assertions.assertEquals(JDFContentObject.calcOrd(0, 12, 3, 2, 2, i), -1);

			Assertions.assertEquals(JDFContentObject.calcOrd(-2, 13, 3, 2, 2, i), 8 + i);
			Assertions.assertEquals(JDFContentObject.calcOrd(-2, 12, 3, 2, 2, i), -1);
			Assertions.assertEquals(JDFContentObject.calcOrd(-2, 12, 3, 2, 2, i), -1);
			Assertions.assertEquals(JDFContentObject.calcOrd(-1, 10, 1, 2, 2, i), 9 + i);
		}
	}

	/**
	*
	*
	*/
	@Test
	void testSetTrimSize()
	{
		final JDFContentObject co = lo.appendContentObject();
		co.setTrimSize(1.12345, 2.3456, 2);
		Assertions.assertTrue(co.toXML().indexOf("1.12 2.35\"") > 0);
	}

	/**
	*
	*
	*/
	@Test
	void testGetRect()
	{
		final JDFContentObject co = lo.appendContentObject();
		co.setTrimSize(1.2345, 2.3456);
		co.setCTM(JDFMatrix.getUnitMatrix());
		Assertions.assertEquals(new JDFRectangle(0, 0, 1.2345, 2.3456), co.getRect());
	}

	/**
	*
	*
	*/
	@Test
	void testSetCTM()
	{
		final JDFContentObject co = lo.appendContentObject();
		co.setTrimSize(1.12345, 2.3456, 2);
		Assertions.assertTrue(co.toXML().indexOf("1.12 2.35\"") > 0);
	}

	/**
	*
	*
	*/
	@Test
	void testSetClipPath()
	{
		final JDFContentObject co = lo.appendContentObject();
		co.setClipPath("1.0000004 1.2345678 l 1 2 3 g ds", 2);
		Assertions.assertEquals(co.getClipPath(), ("1 1.23 l 1 2 3 g ds"));
	}
}
